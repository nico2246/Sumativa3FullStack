package com.semana6.eventos.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EventoNotFoundException extends RuntimeException{

    public EventoNotFoundException(String message) {
        super(message);
    }
    
}
