package com.ibm.cloud.exception;

import com.ibm.cloud.framework.exception.VCXException;

public class InvalidUserIDException extends VCXException {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * InvalidUserIDException constructor.
	 */
	public InvalidUserIDException() {
		super();
	}
	
	/**
	 * InvalidUserIDException constructor.
	 * @param s string message
	 */
	public InvalidUserIDException(String s) {
		super(s);
	}
}
