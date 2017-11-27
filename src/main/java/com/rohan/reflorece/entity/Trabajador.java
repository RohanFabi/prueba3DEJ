package com.rohan.reflorece.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Fabi
 */
@Entity
public class Trabajador implements Serializable {

    private static final long serialVersionUID = 2L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column (nullable = false)
    @NotNull
    private int rut;
    @Column (nullable = false)
    @NotNull
    private String nombre;
    @Column (nullable = false)
    @NotNull
    @Size(min=7, max=12)
    private String telefono;
    
    @ManyToOne
    private TipoTrabajador tipoTrabajador;
    
    @Column (nullable = false, unique = true)
    @Size(min=4, max=20)
    private String usuario;
    @Column (nullable = false)
    @Size(min=4, max=10)
    private String contrasena;

    public Trabajador() {
    }

    public Trabajador(int rut, String nombre, String telefono, TipoTrabajador tipoTrabajador, String usuario, String contrasena) {
        this.rut = rut;
        this.nombre = nombre;
        this.telefono = telefono;
        this.tipoTrabajador = tipoTrabajador;
        this.usuario = usuario;
        this.contrasena = contrasena;
    }

    public int getRut() {
        return rut;
    }

    public void setRut(int rut) {
        this.rut = rut;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public TipoTrabajador getTipoTrabajador() {
        return tipoTrabajador;
    }

    public void setTipoTrabajador(TipoTrabajador tipoTrabajador) {
        this.tipoTrabajador = tipoTrabajador;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
