package com.semana6.facturas.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.semana6.facturas.model.Servicio;

public interface ServicioRepository extends JpaRepository<Servicio, Long> {
    
}