package com.semana6.facturas.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ServicioNotFoundException extends RuntimeException {

    public ServicioNotFoundException(String message) {
        super(message);
    }    
}
