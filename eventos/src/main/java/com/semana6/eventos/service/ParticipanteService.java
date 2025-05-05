package com.semana6.eventos.service;

import java.util.List;
import java.util.Optional;

import com.semana6.eventos.model.Participante;

public interface ParticipanteService {
    List<Participante> getAllParticipantes();
    Optional<Participante> getParticipanteById(Long id);

    // crear, modificar o actualizar y eliminar... para terminar CRUD
    Participante createParticipante(Participante participante);
    Participante updateParticipante(Long id, Participante participante);
    void deleteParticipante(Long id);
    
}
