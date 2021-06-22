package com.example.demo.entity;

import javax.persistence.*;

@Entity
@Table(name = "actividades")
public class Actividad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idactividad")
    private int idactividad;

    @Column(name = "nombreactividad")
    private String nombreactividad;

    private String descripcion;
    private int idproyecto;

    @Column(name = "usuario_owner")
    private String usuarioOwner;
    private Float peso;
    private int estado;

    public int getIdactividad() {
        return idactividad;
    }

    public void setIdactividad(int idactividad) {
        this.idactividad = idactividad;
    }

    public String getNombreActividad() {
        return nombreactividad;
    }

    public void setNombreActividad(String nombreActividad) {
        this.nombreactividad = nombreActividad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdproyecto() {
        return idproyecto;
    }

    public void setIdproyecto(int idproyecto) {
        this.idproyecto = idproyecto;
    }

    public String getUsuarioOwner() {
        return usuarioOwner;
    }

    public void setUsuarioOwner(String usuarioOwner) {
        this.usuarioOwner = usuarioOwner;
    }

    public Float getPeso() {
        return peso;
    }

    public void setPeso(Float peso) {
        this.peso = peso;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
}