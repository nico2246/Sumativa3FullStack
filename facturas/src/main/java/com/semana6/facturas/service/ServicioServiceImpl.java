package com.semana6.facturas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.semana6.facturas.model.Servicio;
import com.semana6.facturas.repository.ServicioRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioServiceImpl implements ServicioService{
    @Autowired
    private ServicioRepository servicioRepository;

    @Override
    public List<Servicio> getAllServicios(){
        return servicioRepository.findAll();
    }

    @Override
    public Optional<Servicio> getServicioById(Long id){
        return servicioRepository.findById(id);
    }
    

    // continuacion nueva logica CRUD

    @Override
    public Servicio createServicio(Servicio Servicio){
        return servicioRepository.save(Servicio);
    }

    @Override
    public Servicio updateServicio(Long id, Servicio Servicio){
        if(servicioRepository.existsById(id)){
            Servicio.setId(id);
            return servicioRepository.save(Servicio);
        } else {
            return null;
        }
    }

    @Override
    public void deleteServicio(Long id){
        servicioRepository.deleteById(id);
    }

    
}

