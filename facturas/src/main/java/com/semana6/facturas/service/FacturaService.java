package com.semana6.facturas.service;

import java.util.List;
import java.util.Optional;

import com.semana6.facturas.model.Factura;

public interface FacturaService {
    List<Factura> getAllFacturas();
    Optional<Factura> getFacturaById(Long id);

    // crear, modificar o actualizar y eliminar... para terminar CRUD
    Factura createFactura(Factura factura);
    Factura updateFactura(Long id, Factura factura);
    void deleteFactura(Long id);
    
}