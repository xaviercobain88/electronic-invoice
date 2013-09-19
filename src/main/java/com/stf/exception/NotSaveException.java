package com.stf.exception;

public class NotSaveException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 5142152685073063059L;

    /**
     * 
     */
    public NotSaveException() {
    }

    /**
     * @param message
     */
    public NotSaveException(String message) {
	super(message);
    }

    /**
     * @param cause
     */
    public NotSaveException(Throwable cause) {
	super(cause);
    }

    /**
     * @param message
     * @param cause
     */
    public NotSaveException(String message, Throwable cause) {
	super(message, cause);
    }

}
