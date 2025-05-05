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
        factura1.setNombreMascota("Lugar1");
        factura1.setNombreMedico("Nicolas Escobar");
        factura1.setDetalleServicio("Accion");
        factura1.setValorTotalServicio(20000);
     

        Factura factura2 = new Factura();
        factura2.setNombreMascota("Lugar2");
        factura2.setNombreMedico("Barbara Lillo");
        factura2.setDetalleServicio("Accion 2");
        factura2.setValorTotalServicio(30000);
    

        
        List<Factura> facturas = Arrays.asList(factura1, factura2);
        when(facturaServicioMock.getAllFacturas()).thenReturn(facturas);

        //Act & Assert

        mockMvc.perform(MockMvcRequestBuilders.get("/facturas"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.aMapWithSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.facturaList[0].nombreMascota", Matchers.is("Lugar1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.facturaList[1].nombreMascota", Matchers.is("Lugar2")));
                
    }

      @Test
    public void obtenerFacturaPorIdTest() throws Exception {
        // Arrange
        Factura factura = new Factura();
        factura.setNombreMascota("Lugar1");
        factura.setNombreMedico("Nicolas Escobar");
        factura.setDetalleServicio("Accion");
        factura.setValorTotalServicio(20000);
    
        when(facturaServicioMock.getFacturaById(1L)).thenReturn(Optional.of(factura));
    
        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/facturas/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nombreMascota").value("Lugar1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nombreMedico").value("Nicolas Escobar"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.detalleServicio").value("Accion"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.valorTotalServicio").value(20000));
    }

    //Otras  pruebas...
    
}