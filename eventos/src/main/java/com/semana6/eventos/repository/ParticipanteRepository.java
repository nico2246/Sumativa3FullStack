package com.semana6.eventos.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.semana6.eventos.model.Participante;

public interface ParticipanteRepository extends JpaRepository<Participante, Long> {
    
}