package com.semana6.facturas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.semana6.facturas.model.Factura;

public interface FacturaRepository extends JpaRepository<Factura, Long> {

    List<Factura> findByNombreMascota(String string);
    
}