package com.semana6.eventos.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.semana6.eventos.model.Evento;

public interface EventoRepository extends JpaRepository<Evento, Long> {
    
    
}