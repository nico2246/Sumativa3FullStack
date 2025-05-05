package com.semana6.eventos.model;
import org.springframework.hateoas.RepresentationModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;




@Entity
@Table(name = "evento")
public class Evento extends RepresentationModel<Evento>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "tipo")
    private String tipo ;
    @Column(name = "lugar")
    private String lugar;
    @Column(name = "fecha")
    private String fecha;
   




    //Getters

    public Long getId(){
        return id;
    }

    public String getTipo(){
        return tipo;
    }

    public String getLugar(){
        return lugar;
    }

    public String getFecha(){
        return fecha;
    }

    
    
    //Setters

    public void setId(Long id){
        this.id = id;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }



}

