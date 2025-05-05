package com.semana6.eventos.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ParticipanteNotFoundException extends RuntimeException {

    public ParticipanteNotFoundException(String message) {
        super(message);
    }
    
}
