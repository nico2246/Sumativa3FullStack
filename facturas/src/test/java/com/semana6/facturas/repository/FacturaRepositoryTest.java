package com.semana6.facturas.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

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
    public void guardarFacturaTest(){
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

    @Test
    public void buscarFacturaPorNombreMascotaTest() {
        // Arrange
        Factura factura1 = new Factura();
        factura1.setNombreMascota("Pelusa");
        factura1.setNombreMedico("Dr. Ramirez");
        factura1.setDetalleServicio("Vacuna anual");
        factura1.setValorTotalServicio(15000);

        Factura factura2 = new Factura();
        factura2.setNombreMascota("Pelusa");
        factura2.setNombreMedico("Dra. Sanchez");
        factura2.setDetalleServicio("Desparasitacion");
        factura2.setValorTotalServicio(12000);

        facturaRepository.save(factura1);
        facturaRepository.save(factura2);

        // Act
        List<Factura> facturas = facturaRepository.findByNombreMascota("Pelusa");

        // Assert
        assertEquals(2, facturas.size());
        assertEquals("Vacuna anual", facturas.get(0).getDetalleServicio());
        assertEquals("Desparasitacion", facturas.get(1).getDetalleServicio());
    }

    @Test
    public void eliminarFacturaTest() {
        // Arrange
        Factura factura = new Factura();
        factura.setNombreMascota("Rocky");
        factura.setNombreMedico("Dra. Gomez");
        factura.setDetalleServicio("Limpieza dental");
        factura.setValorTotalServicio(35000);

        Factura guardado = facturaRepository.save(factura);

        // Act
        facturaRepository.deleteById(guardado.getId());
        Optional<Factura> eliminado = facturaRepository.findById(guardado.getId());

        // Assert
        assertTrue(eliminado.isEmpty());
    }

    
    
    //  otras pruebas...
}