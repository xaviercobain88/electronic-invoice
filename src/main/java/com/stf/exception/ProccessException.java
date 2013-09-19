package com.stf.exception;


public class ProccessException extends Exception {

	private static final long serialVersionUID = 207554353363239617L;

	/**
     * 
     */
	public ProccessException() {
	}

	/**
	 * @param message
	 */
	public ProccessException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public ProccessException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public ProccessException(String message, Throwable cause) {
		super(message, cause);
	}

}
