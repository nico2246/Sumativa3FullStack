package com.semana6.facturas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.semana6.facturas.model.Factura;
import com.semana6.facturas.repository.FacturaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class FacturaServiceImpl implements FacturaService{
    @Autowired
    private FacturaRepository facturaRepository;

    @Override
    public List<Factura> getAllFacturas(){
        return facturaRepository.findAll();
    }

    @Override
    public Optional<Factura> getFacturaById(Long id){
        return facturaRepository.findById(id);
    }
    

    // continuacion nueva logica CRUD

    @Override
    public Factura createFactura(Factura Factura){
        return facturaRepository.save(Factura);
    }

    @Override
    public Factura updateFactura(Long id, Factura Factura){
        if(facturaRepository.existsById(id)){
            Factura.setId(id);
            return facturaRepository.save(Factura);
        } else {
            return null;
        }
    }

    @Override
    public void deleteFactura(Long id){
        facturaRepository.deleteById(id);
    }

    
}

