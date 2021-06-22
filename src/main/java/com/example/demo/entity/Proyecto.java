package com.example.demo.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "proyectos")
public class Proyecto {

    @Id
    private Integer idproyecto;
    @Column(nullable = true, name = "nombreProyecto")
    private String nombreproyecto;
    @Column(name = "usuario_owner")
    private String usuarioowner;

    public Integer getIdproyecto() {
        return idproyecto;
    }

    public void setIdproyecto(Integer idproyecto) {
        this.idproyecto = idproyecto;
    }

    public String getNombreproyecto() {
        return nombreproyecto;
    }

    public void setNombreproyecto(String nombreproyecto) {
        this.nombreproyecto = nombreproyecto;
    }

    public String getUsuarioowner() {
        return usuarioowner;
    }

    public void setUsuarioowner(String usuarioowner) {
        this.usuarioowner = usuarioowner;
    }

}
