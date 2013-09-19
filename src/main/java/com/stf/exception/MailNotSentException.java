package com.stf.exception;

/**
 * @author fausto
 *
 */
public class MailNotSentException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1590729965569407291L;

	/**
	 * 
	 */
	public MailNotSentException() {
		super();
	}

	/**
	 * @param message
	 * @param cause
	 */
	public MailNotSentException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public MailNotSentException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public MailNotSentException(Throwable cause) {
		super(cause);
	}
}
