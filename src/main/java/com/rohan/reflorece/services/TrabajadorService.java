/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rohan.reflorece.services;

import com.rohan.reflorece.entity.Trabajador;
import com.rohan.reflorece.exception.TrabajadorNoEncontradoException;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Fabi
 */
@Stateless
public class TrabajadorService implements Serializable{
    
    static final long serialVersionUID = 22L;

    @PersistenceContext
    private EntityManager em;

    Logger logger = Logger.getLogger(this.getClass().getSimpleName());

    public Trabajador crearTrabajador(Trabajador trabajador) {
        em.persist(trabajador);
        return trabajador;
    }

    public Trabajador getTrabajadoryById(Long id) {
        return em.find(Trabajador.class, id);
    }

    public Trabajador findTrabajador(String usuario, String password) {
        String jpql = "SELECT v FROM Trabajador v WHERE v.usuario = :usuario AND v.contrasena = :contrasena";
        TypedQuery<Trabajador> query = em.createQuery(jpql, Trabajador.class);
        query.setParameter("usuario", usuario);
        query.setParameter("contrasena", password);
        try {
            Trabajador trabajador = query.getSingleResult();
            return trabajador;
        } catch (NoResultException nre) {
            return null;
        }
    }

     public Trabajador getTrabajadorByRut(int rut) {
        String jpql = "SELECT c FROM Trabajador c WHERE c.rut = :rut";
        TypedQuery<Trabajador> query = em.createQuery(jpql, Trabajador.class);
        query.setParameter("rut", rut);
        Trabajador trabajador = null;
        try {
            trabajador = query.getSingleResult();
        } catch (NoResultException nre) {
            logger.log(Level.SEVERE, "No se encontró ningún trabajador con el RUT especificado");
        }
        return trabajador;
    }
     
    public List<Trabajador> getTrabajadores() {
        String jpql = "SELECT v FROM Trabajador v";
        TypedQuery<Trabajador> query = em.createQuery(jpql, Trabajador.class);
        return query.getResultList();
    }

    public void eliminarTrabajador(Long trabajadorId) throws TrabajadorNoEncontradoException {
        Trabajador trabajador = getTrabajadoryById(trabajadorId);
        if (trabajador == null) {
            String mensajeException = String.format("Trabajador con ID %s no encontrado para ser eliminado", trabajadorId);
            logger.log(Level.SEVERE, mensajeException);
            throw new TrabajadorNoEncontradoException(mensajeException);
        }
        em.remove(trabajador);
    }
}
