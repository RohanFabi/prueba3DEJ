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
public class FichaAtencionNoEncontradaException extends Exception {

    /**
     * Creates a new instance of <code>FichaAtencionNoEncontradaException</code>
     * without detail message.
     */
    public FichaAtencionNoEncontradaException() {
    }

    /**
     * Constructs an instance of <code>FichaAtencionNoEncontradaException</code>
     * with the specified detail message.
     *
     * @param msg the detail message.
     */
    public FichaAtencionNoEncontradaException(String msg) {
        super(msg);
    }
}
