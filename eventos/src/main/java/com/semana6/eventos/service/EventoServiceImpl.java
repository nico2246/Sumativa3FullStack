package com.semana6.eventos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.semana6.eventos.model.Evento;
import com.semana6.eventos.repository.EventoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EventoServiceImpl implements EventoService{
    @Autowired
    private EventoRepository eventoRepository;

    @Override
    public List<Evento> getAllEventos(){
        return eventoRepository.findAll();
    }

    @Override
    public Optional<Evento> getEventoById(Long id){
        return eventoRepository.findById(id);
    }
    

    // continuacion nueva logica CRUD

    @Override
    public Evento createEvento(Evento Evento){
        return eventoRepository.save(Evento);
    }

    @Override
    public Evento updateEvento(Long id, Evento Evento){
        if(eventoRepository.existsById(id)){
            Evento.setId(id);
            return eventoRepository.save(Evento);
        } else {
            return null;
        }
    }

    @Override
    public void deleteEvento(Long id){
        eventoRepository.deleteById(id);
    }

    
}

