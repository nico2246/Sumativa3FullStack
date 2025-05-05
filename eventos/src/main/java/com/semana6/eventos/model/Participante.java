package com.semana6.eventos.model;
import org.springframework.hateoas.RepresentationModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;




@Entity
@Table(name = "participante")
public class Participante extends RepresentationModel<Participante> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "Nombre")
    private String nombre ;
    @Column(name = "Edad")
    private int edad;
    @Column(name = "Tipo Animal")
    private String tipoAnimal;
    @Column(name = "Nombre dueño")
    private String nombreDueno;
    




    //Getters

    public Long getId(){
        return id;
    }

    public String getNombre(){
        return nombre;
    }

    public int getEdad(){
        return edad;
    }

    public String getTipoAnimal(){
        return tipoAnimal;
    }

    public String getNombreDueno(){
        return nombreDueno;
    }
    
    //Setters

    public void setId(Long id){
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setTipoAnimal(String tipoAnimal) {
        this.tipoAnimal = tipoAnimal;
    }

    public void setNombreDueno(String nombreDueno) {
        this.nombreDueno = nombreDueno;
    }

}
