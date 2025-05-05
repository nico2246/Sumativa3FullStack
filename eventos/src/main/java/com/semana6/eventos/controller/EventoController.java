package com.semana6.eventos.controller;

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

import com.semana6.eventos.model.Evento;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.semana6.eventos.service.EventoService;

@RestController
@RequestMapping("/eventos")
@CrossOrigin(origins = "*")
public class EventoController {
    @Autowired
    private EventoService eventoService;

    @GetMapping
    public CollectionModel<EntityModel<Evento>> getAllEventos(){
        List<Evento> eventos = eventoService.getAllEventos();

        List<EntityModel<Evento>> eventoResources = eventos.stream()
        .map(evento -> EntityModel.of(evento,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getEventoById(evento.getId())).withSelfRel()
                ))
        .collect(Collectors.toList());

        WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllEventos());
        CollectionModel<EntityModel<Evento>> resources = CollectionModel.of(eventoResources, linkTo.withRel("eventos"));

        return resources;

    }

    @GetMapping("/{id}")
    public EntityModel<Evento> getEventoById(@PathVariable Long id){
        Optional<Evento> evento = eventoService.getEventoById(id);
        //Verifica si existe el evento
        if (evento.isPresent()) {
            return EntityModel.of(evento.get(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getEventoById(id)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllEventos()).withRel("all-eventos"));
        } else {
            throw new EventoNotFoundException("Evento not found" + id);
        }   

    }


    //nuevos controller para el CRUD

     @PostMapping
    public EntityModel<Evento> createEvento(@RequestBody Evento evento){
        Evento createdEvento = eventoService.createEvento(evento);
        return EntityModel.of(createdEvento,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getEventoById(createdEvento.getId())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllEventos()).withRel("all-eventos"));
    }

    @PutMapping("/{id}")
    public EntityModel<Evento> updateEvento(@PathVariable Long id, @RequestBody Evento evento){
        Evento updatedEvento = eventoService.updateEvento(id, evento);
        return EntityModel.of(updatedEvento,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getEventoById(id)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllEventos()).withRel("all-eventos"));
    }

    @DeleteMapping("{id}")
    public void deleteEvento(@PathVariable Long id){
        eventoService.deleteEvento(id);
    }
    
}