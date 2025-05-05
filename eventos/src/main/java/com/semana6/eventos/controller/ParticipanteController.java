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

import com.semana6.eventos.model.Participante;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.semana6.eventos.service.ParticipanteService;

@RestController
@RequestMapping("/participantes")
@CrossOrigin(origins = "*")
public class ParticipanteController {
    @Autowired
    private ParticipanteService participanteService;

    @GetMapping
    public CollectionModel<EntityModel<Participante>> getAllParticipantes(){
        List<Participante> participantes = participanteService.getAllParticipantes();

        List<EntityModel<Participante>> participanteResources = participantes.stream()
        .map(participante -> EntityModel.of(participante,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getParticipanteById(participante.getId())).withSelfRel()
                ))
        .collect(Collectors.toList());

        WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllParticipantes());
        CollectionModel<EntityModel<Participante>> resources = CollectionModel.of(participanteResources, linkTo.withRel("participantes"));

        return resources;

    }

    @GetMapping("/{id}")
    public EntityModel<Participante> getParticipanteById(@PathVariable Long id){
        Optional<Participante> participante = participanteService.getParticipanteById(id);
        //Verifica si existe la participante
        if (participante.isPresent()) {
            return EntityModel.of(participante.get(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getParticipanteById(id)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllParticipantes()).withRel("all-participantes"));
        } else {
            throw new ParticipanteNotFoundException("Participante not found" + id);
        }   

    }

    //nuevos controller para el CRUD


    @PostMapping
    public EntityModel<Participante> createParticipante(@RequestBody Participante participante){
        Participante createdParticipante = participanteService.createParticipante(participante);
        return EntityModel.of(createdParticipante,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getParticipanteById(createdParticipante.getId())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllParticipantes()).withRel("all-participantes"));
    }

    @PutMapping("/{id}")
    public EntityModel<Participante> updateParticipante(@PathVariable Long id, @RequestBody Participante participante){
        Participante updatedParticipante = participanteService.updateParticipante(id, participante);
        return EntityModel.of(updatedParticipante,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getParticipanteById(id)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllParticipantes()).withRel("all-participantes"));
    }

    @DeleteMapping("{id}")
    public void deleteParticipante(@PathVariable Long id){
        participanteService.deleteParticipante(id);
    }
    
}