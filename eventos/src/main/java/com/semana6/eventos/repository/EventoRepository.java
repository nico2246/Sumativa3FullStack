package com.semana6.eventos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.semana6.eventos.model.Evento;

public interface EventoRepository extends JpaRepository<Evento, Long> {

    List<Evento> findByTipo(String string);
    
    
}