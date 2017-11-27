/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rohan.reflorece.exception;

/**
 *
 * @author Fabi
 */
public class TipoTrabajadorNoEncontradoException extends Exception {

    /**
     * Creates a new instance of
     * <code>TipoTrabajadorNoEncontradoException</code> without detail message.
     */
    public TipoTrabajadorNoEncontradoException() {
    }

    /**
     * Constructs an instance of
     * <code>TipoTrabajadorNoEncontradoException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public TipoTrabajadorNoEncontradoException(String msg) {
        super(msg);
    }
}
