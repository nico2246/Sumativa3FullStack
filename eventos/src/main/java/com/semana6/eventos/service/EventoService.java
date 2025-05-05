package com.semana6.eventos.service;

import java.util.List;
import java.util.Optional;

import com.semana6.eventos.model.Evento;

public interface EventoService {
    List<Evento> getAllEventos();
    Optional<Evento> getEventoById(Long id);

    // crear, modificar o actualizar y eliminar... para terminar CRUD
    Evento createEvento(Evento evento);
    Evento updateEvento(Long id, Evento evento);
    void deleteEvento(Long id);
    
}
