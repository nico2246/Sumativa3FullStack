package com.semana6.facturas.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.semana6.facturas.model.Factura;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class FacturaRepositoryTest {
    @Autowired
    private FacturaRepository facturaRepository;

    @Test
    public void guardarEventoTest(){
        //Arrange
        Factura factura = new Factura();
        factura.setNombreMascota("Firulais");
        factura.setNombreMedico("Nicolas Escobar");
        factura.setDetalleServicio("Consulta medica");
        factura.setValorTotalServicio(20000);
       

        //Act
        Factura resultado = facturaRepository.save(factura);

        //Assert
        assertNotNull(resultado.getId());
        assertEquals("Firulais", resultado.getNombreMascota());
    }

    // guarda una factura y luego la busca por id
    @Test
    public void buscarFacturaPorIdTest() {
        // Arrange
        Factura factura = new Factura();
        factura.setId(2L);
        factura.setNombreMascota("Mishishi");
        factura.setNombreMedico("Nicolas Escobar");
        factura.setDetalleServicio("Consulta medica");
        factura.setValorTotalServicio(20000);

        Factura guardado = facturaRepository.save(factura);

        // Act
        Factura encontrado = facturaRepository.findById(guardado.getId()).orElse(null);

        // Assert
        assertNotNull(encontrado);
        assertEquals(2L, encontrado.getId());
        assertEquals("Mishishi", encontrado.getNombreMascota());
        assertEquals("Nicolas Escobar", encontrado.getNombreMedico());
        assertEquals("Consulta medica", encontrado.getDetalleServicio());
        assertEquals(20000, encontrado.getValorTotalServicio());
    }
    
    //  otras pruebas...
}