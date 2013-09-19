package com.stf.exception;

public class EntidadNoGuardadaException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 5142152685073063059L;

    /**
     * 
     */
    public EntidadNoGuardadaException() {
    }

    /**
     * @param message
     */
    public EntidadNoGuardadaException(String message) {
	super(message);
    }

    /**
     * @param cause
     */
    public EntidadNoGuardadaException(Throwable cause) {
	super(cause);
    }

    /**
     * @param message
     * @param cause
     */
    public EntidadNoGuardadaException(String message, Throwable cause) {
	super(message, cause);
    }

}
