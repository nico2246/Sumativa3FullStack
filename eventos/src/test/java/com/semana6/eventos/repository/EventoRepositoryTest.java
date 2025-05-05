package com.semana6.eventos.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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


    
    //  otras pruebas...
}
