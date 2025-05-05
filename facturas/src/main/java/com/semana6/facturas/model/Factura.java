package com.semana6.facturas.model;
import org.springframework.hateoas.RepresentationModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;




@Entity
@Table(name = "factura")
public class Factura extends RepresentationModel<Factura>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "Nombre Mascota")
    private String nombreMascota ;
    @Column(name = "Nombre Medico")
    private String nombreMedico;
    @Column(name = "Detalle Servicio")
    private String detalleServicio;
    @Column(name = "Valor total servicio")
    private int valorTotalServicio;
    




    //Getters

    public Long getId(){
        return id;
    }

    public String getNombreMascota(){
        return nombreMascota;
    }

    public String getNombreMedico(){
        return nombreMedico;
    }

    public String getDetalleServicio(){
        return detalleServicio;
    }

    public int getValorTotalServicio(){
        return valorTotalServicio;
    }
    
    //Setters

    public void setId(Long id){
        this.id = id;
    }

    public void setNombreMascota(String nombreMascota) {
        this.nombreMascota = nombreMascota;
    }

    public void setNombreMedico(String nombreMedico) {
        this.nombreMedico = nombreMedico;
    }

    public void setDetalleServicio(String detalleServicio) {
        this.detalleServicio = detalleServicio;
    }

    public void setValorTotalServicio(int valorTotalServicio) {
        this.valorTotalServicio = valorTotalServicio;
    }

}
