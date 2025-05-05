package com.semana6.facturas.controller;
import static org.mockito.Mockito.when;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.semana6.facturas.model.Factura;

import com.semana6.facturas.service.FacturaServiceImpl;


@WebMvcTest(FacturaController.class)
public class FacturaControllerTest {
    @Autowired
    private MockMvc mockMvc;

    
    @MockBean
    private FacturaServiceImpl facturaServicioMock;

    @Test
    public void obtenerTodosTest() throws Exception {
        //Arrange
        //Creacion de facturas
        Factura factura1 = new Factura();
        factura1.setNombreMascota("Firulais");
        factura1.setNombreMedico("Nicolas Escobar");
        factura1.setDetalleServicio("Consulta medica");
        factura1.setValorTotalServicio(20000);
     

        Factura factura2 = new Factura();
        factura2.setNombreMascota("Mishishi");
        factura2.setNombreMedico("Barbara Lillo");
        factura2.setDetalleServicio("Vacunas");
        factura2.setValorTotalServicio(30000);
    

        
        List<Factura> facturas = Arrays.asList(factura1, factura2);
        when(facturaServicioMock.getAllFacturas()).thenReturn(facturas);

        //Act & Assert

        mockMvc.perform(MockMvcRequestBuilders.get("/facturas"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.aMapWithSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.facturaList[0].nombreMascota", Matchers.is("Firulais")))
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.facturaList[1].nombreMascota", Matchers.is("Mishishi")));
                
    }

      @Test
    public void obtenerFacturaPorIdTest() throws Exception {
        // Arrange
        Factura factura = new Factura();
        factura.setNombreMascota("Firulais");
        factura.setNombreMedico("Nicolas Escobar");
        factura.setDetalleServicio("Consulta medica");
        factura.setValorTotalServicio(20000);
    
        when(facturaServicioMock.getFacturaById(1L)).thenReturn(Optional.of(factura));
    
        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/facturas/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nombreMascota").value("Firulais"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nombreMedico").value("Nicolas Escobar"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.detalleServicio").value("Consulta medica"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.valorTotalServicio").value(20000));
    }

    //Otras  pruebas...
    
}