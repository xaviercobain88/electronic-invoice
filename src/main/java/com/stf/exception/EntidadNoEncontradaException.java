package com.stf.exception;


public class EntidadNoEncontradaException extends Exception {

	private static final long serialVersionUID = 207554353363239617L;

	/**
     * 
     */
	public EntidadNoEncontradaException() {
	}

	/**
	 * @param message
	 */
	public EntidadNoEncontradaException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public EntidadNoEncontradaException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public EntidadNoEncontradaException(String message, Throwable cause) {
		super(message, cause);
	}

}
