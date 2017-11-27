package com.rohan.reflorece.entity;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Fabi
 */
@Entity
public class FichaAtencion implements Serializable {

    private static final long serialVersionUID = 3L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Cliente cliente;

    @ManyToOne
    private Trabajador trabajador;
    @Column(nullable = false)
    @NotNull
    private String motivoConsulta;
    @Column(nullable = false)
    @NotNull
    private String indicaciones;
    @Column(nullable = false)
    @NotNull
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Calendar fechaAtencion = Calendar.getInstance();
    @NotNull
    private int valorAtencion;

    public FichaAtencion() {
    }

    public FichaAtencion(Cliente cliente, Trabajador trabajador, String motivoConsulta, String indicaciones, int valorAtencion, Calendar fechaAtencion) {
        this.cliente = cliente;
        this.trabajador = trabajador;
        this.motivoConsulta = motivoConsulta;
        this.indicaciones = indicaciones;
        this.valorAtencion = valorAtencion;
        this.fechaAtencion = fechaAtencion;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Trabajador getTrabajador() {
        return trabajador;
    }

    public void setTrabajador(Trabajador trabajador) {
        this.trabajador = trabajador;
    }

    public String getMotivoConsulta() {
        return motivoConsulta;
    }

    public void setMotivoConsulta(String motivoConsulta) {
        this.motivoConsulta = motivoConsulta;
    }

    public String getIndicaciones() {
        return indicaciones;
    }

    public void setIndicaciones(String indicaciones) {
        this.indicaciones = indicaciones;
    }

    public Calendar getFechaAtencion() {
        return fechaAtencion;
    }

    public void setFechaAtencion(Calendar fechaAtencion) {
        this.fechaAtencion = fechaAtencion;
    }

    public int getValorAtencion() {
        return valorAtencion;
    }

    public void setValorAtencion(int valorAtencion) {
        this.valorAtencion = valorAtencion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
