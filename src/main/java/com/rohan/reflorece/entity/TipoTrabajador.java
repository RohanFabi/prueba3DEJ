package com.rohan.reflorece.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

/**
 *
 * @author Fabi
 */
@Entity
public class TipoTrabajador implements Serializable {

    private static final long serialVersionUID = 4L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Size(min = 2, max = 20)
    @Column (nullable = false, unique=true)
    private String nombre;

    public TipoTrabajador() {
    }

    public TipoTrabajador(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public TipoTrabajador(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
