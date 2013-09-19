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
package com.stf.exception;

/**
 * Exception for not removed entities
 * 
 * @author David Cisneros
 * @version $Revision: 1.1 $
 */
public class NotPermissionException extends RuntimeException {

	/**
     * 
     */
	private static final long serialVersionUID = -1694688781630189881L;

	/**
     * 
     */
	public NotPermissionException() {
		super();
	}

	/**
	 * @param message
	 * @param cause
	 */
	public NotPermissionException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public NotPermissionException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public NotPermissionException(Throwable cause) {
		super(cause);
	}

}
