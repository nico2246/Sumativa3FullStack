package com.semana6.facturas.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class FacturaNotFoundException extends RuntimeException{
    public FacturaNotFoundException(String message){
        super(message);
    }
    
}
