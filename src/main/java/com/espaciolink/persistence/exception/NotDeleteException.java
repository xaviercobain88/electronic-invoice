/*
 * EntityNotRemovedException.java
 *
 * Created on Apr 15, 2008
 *
 * Copyright © ndeveloper. All Rights Reserved.
 *
 * NDEVELOPER cia ltda
 * Pradera N30-258 y Mariano Aguilera.
 * Edificio Santorini Piso 3
 * Quito-Ecuador
 * www.ndeveloper.com
 * www.ndeveloper.net
 */
package com.espaciolink.persistence.exception;

/**
 * Exception for not removed entities
 * 
 * @author David Cisneros
 * @version $Revision: 1.1 $
 */
public class NotDeleteException extends Exception{

    /**
     * 
     */
    private static final long serialVersionUID = -1694688781630189881L;

    /**
     * 
     */
    public NotDeleteException() {
	super();
    }

    /**
     * @param message
     * @param cause
     */
    public NotDeleteException(String message, Throwable cause) {
	super(message, cause);
    }

    /**
     * @param message
     */
    public NotDeleteException(String message) {
	super(message);
    }

    /**
     * @param cause
     */
    public NotDeleteException(Throwable cause) {
	super(cause);
    }

}
