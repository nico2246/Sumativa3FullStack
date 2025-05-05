package com.semana6.facturas.model;
import org.springframework.hateoas.RepresentationModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;




@Entity
@Table(name = "servicio")
public class Servicio extends RepresentationModel<Servicio> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "Nombre")
    private String nombre ;
    @Column(name = "Descripcion")
    private String descripcion;
    @Column(name = "Servicio")
    private String servicio;
   
    




    //Getters

    public Long getId(){
        return id;
    }

    public String getNombre(){
        return nombre;
    }

    public String getDescripcion(){
        return descripcion;
    }

    public String getServicio(){
        return servicio;
    }

    
    //Setters

    public void setId(Long id){
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

  
}