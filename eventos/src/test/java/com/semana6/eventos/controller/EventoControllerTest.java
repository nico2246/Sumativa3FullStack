package com.semana6.eventos.controller;
import static org.mockito.Mockito.when;
import java.util.Arrays;
import java.util.List;


import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.util.Optional; 

import com.semana6.eventos.model.Evento;

import com.semana6.eventos.service.EventoServiceImpl;


@WebMvcTest(EventoController.class)
public class EventoControllerTest {
    @Autowired
    private MockMvc mockMvc;

    
    @MockBean
    private EventoServiceImpl eventoServicioMock;

    @Test
    public void obtenerTodosTest() throws Exception {
        //Arrange
        //Creacion de eventos
        Evento evento1 = new Evento();
        evento1.setTipo("Competencia");
        evento1.setLugar("Parque Bicentenario");
        evento1.setFecha("10-04-25");
     

        Evento evento2 = new Evento();
        evento2.setTipo("Concurso Belleza");
        evento2.setLugar("Centro civico");
        evento2.setFecha("11-04-25");
    

        
        List<Evento> eventos = Arrays.asList(evento1, evento2);
        when(eventoServicioMock.getAllEventos()).thenReturn(eventos);

        //Act & Assert

        mockMvc.perform(MockMvcRequestBuilders.get("/eventos"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.aMapWithSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.eventoList[0].tipo", Matchers.is("Competencia")))
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.eventoList[1].tipo", Matchers.is("Concurso Belleza")));
                
    }


    //Buscar evento por id
    @Test
    public void obtenerEventoPorIdTest() throws Exception {
        // Arrange
        Evento evento = new Evento();
        evento.setId(1L);
        evento.setTipo("Carrera");
        evento.setLugar("Santiago");
        evento.setFecha("10-04-25");
    
        when(eventoServicioMock.getEventoById(1L)).thenReturn(Optional.of(evento));
    
        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/eventos/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.tipo").value("Carrera"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lugar").value("Santiago"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.fecha").value("10-04-25"));
    }
    


    //Otras  pruebas...
    
}
