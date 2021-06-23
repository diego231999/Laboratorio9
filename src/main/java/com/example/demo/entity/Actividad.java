package com.example.demo.entity;

import javax.persistence.*;

@Entity
@Table(name = "actividades")
public class Actividad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idactividad")
    private Integer idactividad;

    @Column(name = "nombreactividad")
    private String nombreactividad;

    private String descripcion;
    private Integer idproyecto;

    @Column(name = "usuario_owner")
    private String usuarioOwner;
    private Float peso;
    private Integer estado;

    public Integer getIdactividad() {
        return idactividad;
    }

    public void setIdactividad(Integer idactividad) {
        this.idactividad = idactividad;
    }

    public String getNombreactividad() {
        return nombreactividad;
    }

    public void setNombreactividad(String nombreactividad) {
        this.nombreactividad = nombreactividad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getIdproyecto() {
        return idproyecto;
    }

    public void setIdproyecto(Integer idproyecto) {
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

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }
}