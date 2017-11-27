package com.rohan.reflorece.services;

import com.rohan.reflorece.entity.FichaAtencion;
import com.rohan.reflorece.exception.FichaAtencionNoEncontradaException;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Startup;
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
public class FichaAtencionService implements Serializable{
    static final long serialVersionUID = 33L;

    @PersistenceContext
    private EntityManager em;

    Logger logger = Logger.getLogger(this.getClass().getSimpleName());

    public FichaAtencion crearFichaAtencion(FichaAtencion fichaAtencion) {
         if(fichaAtencion.getId() == null) {
            em.persist(fichaAtencion);    
        } else {
            em.merge(fichaAtencion);
        }
        return fichaAtencion;
    }

    public FichaAtencion getFichaAtencionyById(Long id) {
        return em.find(FichaAtencion.class, id);
    }

    public List<FichaAtencion> findFichaAtencionUsuario(int rutBuscar) {
        String jpql = "SELECT f FROM FichaAtencion f WHERE f.usuario.rut = :rut";
        TypedQuery<FichaAtencion> query = em.createQuery(jpql, FichaAtencion.class);
        query.setParameter("rut", rutBuscar);
        try {
            return query.getResultList();
        } catch (NoResultException nre) {
            return null;
        }
    }

    public List<FichaAtencion> getFichaAtenciones() {
        String jpql = "SELECT f FROM FichaAtencion f";
        TypedQuery<FichaAtencion> query = em.createQuery(jpql, FichaAtencion.class);
        return query.getResultList();
    }

}
