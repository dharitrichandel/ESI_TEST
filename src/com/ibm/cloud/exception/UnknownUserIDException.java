package com.ibm.cloud.exception;

import com.ibm.cloud.framework.exception.VCXException;

/**
 * This exception is thrown by the constructor for the UserAccount class when a user is not found in the database
 */
public class UnknownUserIDException extends VCXException {
	
	private static final long serialVersionUID = 1L;

	/**
	 * UnknownUserIDException constructor.
	 */
	public UnknownUserIDException() {
		super();
	}
	
	/**
	 * UnknownUserIDException constructor.
	 * @param s string message
	 */
	public UnknownUserIDException(String s) {
		super(s);
	}
}
