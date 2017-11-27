package com.rohan.reflorece.services;

import com.rohan.reflorece.entity.TipoTrabajador;
import com.rohan.reflorece.exception.TipoTrabajadorNoEncontradoException;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Fabi
 */
@Stateless
public class TipoTrabajadorService implements Serializable{
     static final long serialVersionUID = 44L;
    
    @PersistenceContext
    EntityManager em;    
    
    Logger logger = Logger.getLogger(this.getClass().getSimpleName());

    // Constructores
    public TipoTrabajadorService() {
    }

    // métodos
    public TipoTrabajador crearTipoTrabajador(TipoTrabajador tipoTrabajador) {
        em.persist(tipoTrabajador);
        return tipoTrabajador;
    }
    
    public TipoTrabajador getTipoTrabajadorById(Long tipoTrabajadorId) {
        TipoTrabajador tipoTrabajador = em.find(TipoTrabajador.class, tipoTrabajadorId);
        return tipoTrabajador;
    }
    
    public List<TipoTrabajador> getTipoTrabajadors() {
        TypedQuery<TipoTrabajador> query = em.createQuery("SELECT c FROM TipoTrabajador c", TipoTrabajador.class);
        return query.getResultList();
    }
    
    public void eliminarTipoTrabajador(Long tipoTrabajadorId) throws TipoTrabajadorNoEncontradoException {
        TipoTrabajador c = getTipoTrabajadorById(tipoTrabajadorId);
        if (c == null) {
            String mensajeException = String.format("TipoTrabajador con ID %s no encontrada para ser eliminada", tipoTrabajadorId);
            logger.log(Level.SEVERE, mensajeException);
            throw new TipoTrabajadorNoEncontradoException(mensajeException);
        }
        em.remove(c);
    }
}
