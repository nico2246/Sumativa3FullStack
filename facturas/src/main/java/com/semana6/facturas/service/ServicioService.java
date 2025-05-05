package com.semana6.facturas.service;

import java.util.List;
import java.util.Optional;

import com.semana6.facturas.model.Servicio;

public interface ServicioService {
    List<Servicio> getAllServicios();
    Optional<Servicio> getServicioById(Long id);

    // crear, modificar o actualizar y eliminar... para terminar CRUD
    Servicio createServicio(Servicio servicio);
    Servicio updateServicio(Long id, Servicio servicio);
    void deleteServicio(Long id);
    
}