package com.semana6.eventos.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.semana6.eventos.model.Evento;



@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class EventoRepositoryTest {
    @Autowired
    private EventoRepository eventoRepository;

    @Test
    public void guardarEventoTest(){
        //Arrange
        Evento evento = new Evento();
        evento.setTipo("Competencia");
        evento.setLugar("Parque");
        evento.setFecha("Lunes");
       

        //Act
        Evento resultado = eventoRepository.save(evento);

        //Assert
        assertNotNull(resultado.getId());
        assertEquals("Competencia", resultado.getTipo());
    }

    //guardar evento y luego buscarlo por id

    @Test
    public void buscarEventoPorIdTest() {
        // Arrange
        Evento evento = new Evento();
        evento.setTipo("Concurso Belleza");
        evento.setLugar("Auditorio Central");
        evento.setFecha("2025-06-01");

        Evento guardado = eventoRepository.save(evento);

        // Act
        Evento encontrado = eventoRepository.findById(guardado.getId()).orElse(null);

        // Assert
        assertNotNull(encontrado);
        assertEquals("Concurso Belleza", encontrado.getTipo());
        assertEquals("Auditorio Central", encontrado.getLugar());
        assertEquals("2025-06-01", encontrado.getFecha());
    }

    @Test
    public void buscarEventosPorTipoTest() {
        // Arrange
        Evento evento1 = new Evento();
        evento1.setTipo("Carrera");
        evento1.setLugar("parque bicentenario");
        evento1.setFecha("2025-07-01");

        Evento evento2 = new Evento();
        evento2.setTipo("Carrera");
        evento2.setLugar("parque ohiggins");
        evento2.setFecha("2025-07-02");

        eventoRepository.save(evento1);
        eventoRepository.save(evento2);

        // Act
        List<Evento> carrera = eventoRepository.findByTipo("Carrera");

        // Assert
        assertEquals(2, carrera.size());
        assertEquals("parque bicentenario", carrera.get(0).getLugar());
        assertEquals("parque ohiggins", carrera.get(1).getLugar());
    }

    @Test
    public void eliminarEventoTest() {
        // Arrange
        Evento evento = new Evento();
        evento.setTipo("Taller");
        evento.setLugar("Laboratorio");
        evento.setFecha("2025-08-01");

        Evento guardado = eventoRepository.save(evento);

        // Act
        eventoRepository.deleteById(guardado.getId());
        Optional<Evento> eliminado = eventoRepository.findById(guardado.getId());

        // Assert
        assertTrue(eliminado.isEmpty());
    }

    //  otras pruebas...
}


    
    

