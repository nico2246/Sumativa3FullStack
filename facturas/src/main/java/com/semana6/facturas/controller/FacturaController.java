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

import com.semana6.facturas.model.Factura;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.semana6.facturas.service.FacturaService;

@RestController
@RequestMapping("/facturas")
@CrossOrigin(origins = "*")
public class FacturaController {
    @Autowired
    private FacturaService facturaService;

    @GetMapping
    public CollectionModel<EntityModel<Factura>> getAllFacturas(){
        List<Factura> facturas = facturaService.getAllFacturas();

        List<EntityModel<Factura>> facturaResources = facturas.stream()
        .map(factura -> EntityModel.of(factura,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getFacturaById(factura.getId())).withSelfRel()
                ))
        .collect(Collectors.toList());

        WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllFacturas());
        CollectionModel<EntityModel<Factura>> resources = CollectionModel.of(facturaResources, linkTo.withRel("facturas"));

        return resources;

    }

    @GetMapping("/{id}")
    public EntityModel<Factura> getFacturaById(@PathVariable Long id){
        Optional<Factura> factura = facturaService.getFacturaById(id);
        //Verifica si existe la Factura
        if (factura.isPresent()) {
            return EntityModel.of(factura.get(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getFacturaById(id)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllFacturas()).withRel("all-facturas"));
        } else {
            throw new FacturaNotFoundException("Factura not found" + id);
        }   

    }

    //nuevos controller para el CRUD


    @PostMapping
    public EntityModel<Factura> createFactura(@RequestBody Factura factura){
        Factura createdFactura = facturaService.createFactura(factura);
        return EntityModel.of(createdFactura,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getFacturaById(createdFactura.getId())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllFacturas()).withRel("all-facturas"));
    }

    @PutMapping("/{id}")
    public EntityModel<Factura> updateFactura(@PathVariable Long id, @RequestBody Factura factura){
        Factura updatedFactura = facturaService.updateFactura(id, factura);
        return EntityModel.of(updatedFactura,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getFacturaById(id)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllFacturas()).withRel("all-facturas"));
    }

    @DeleteMapping("{id}")
    public void deleteFactura(@PathVariable Long id){
        facturaService.deleteFactura(id);
    }
    
}