package com.semana6.facturas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.semana6.facturas.model.Servicio;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.semana6.facturas.service.ServicioService;

@RestController
@RequestMapping("/servicios")
@CrossOrigin(origins = "*")
public class ServicioController {
    @Autowired
    private ServicioService servicioService;

    @GetMapping
    public CollectionModel<EntityModel<Servicio>> getAllServicios(){
        List<Servicio> servicios = servicioService.getAllServicios();

        List<EntityModel<Servicio>> servicioResources = servicios.stream()
        .map(servicio -> EntityModel.of(servicio,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getServicioById(servicio.getId())).withSelfRel()
                ))
        .collect(Collectors.toList());

        WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllServicios());
        CollectionModel<EntityModel<Servicio>> resources = CollectionModel.of(servicioResources, linkTo.withRel("servicios"));

        return resources;

    }

    @GetMapping("/{id}")
    public EntityModel<Servicio> getServicioById(@PathVariable Long id){
        Optional<Servicio> servicio = servicioService.getServicioById(id);
        //Verifica si existe la Servicio
        if (servicio.isPresent()) {
            return EntityModel.of(servicio.get(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getServicioById(id)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllServicios()).withRel("all-servicios"));
        } else {
            throw new ServicioNotFoundException("Servicio not found" + id);
        }   

    }

    //nuevos controller para el CRUD


    @PostMapping
    public EntityModel<Servicio> createServicio(@RequestBody Servicio servicio){
        Servicio createdServicio = servicioService.createServicio(servicio);
        return EntityModel.of(createdServicio,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getServicioById(createdServicio.getId())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllServicios()).withRel("all-servicios"));
    }

    @PutMapping("/{id}")
    public EntityModel<Servicio> updateServicio(@PathVariable Long id, @RequestBody Servicio servicio){
        Servicio updatedServicio = servicioService.updateServicio(id, servicio);
        return EntityModel.of(updatedServicio,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getServicioById(id)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllServicios()).withRel("all-servicios"));
    }
    @DeleteMapping("{id}")
    public void deleteServicio(@PathVariable Long id){
        servicioService.deleteServicio(id);
    }
    
}