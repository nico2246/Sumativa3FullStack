package com.semana6.eventos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.semana6.eventos.model.Participante;
import com.semana6.eventos.repository.ParticipanteRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ParticipanteServiceImpl implements ParticipanteService{
    @Autowired
    private ParticipanteRepository participanteRepository;

    @Override
    public List<Participante> getAllParticipantes(){
        return participanteRepository.findAll();
    }

    @Override
    public Optional<Participante> getParticipanteById(Long id){
        return participanteRepository.findById(id);
    }
    

    // continuacion nueva logica CRUD

    @Override
    public Participante createParticipante(Participante Participante){
        return participanteRepository.save(Participante);
    }

    @Override
    public Participante updateParticipante(Long id, Participante Participante){
        if(participanteRepository.existsById(id)){
            Participante.setId(id);
            return participanteRepository.save(Participante);
        } else {
            return null;
        }
    }

    @Override
    public void deleteParticipante(Long id){
        participanteRepository.deleteById(id);
    }

    
}

