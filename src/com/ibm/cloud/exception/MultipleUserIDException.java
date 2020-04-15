package com.ibm.cloud.exception;

import com.ibm.cloud.framework.exception.VCXException;

public class MultipleUserIDException extends VCXException {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * MultipleUserIDException constructor.
	 */
	public MultipleUserIDException() {
		super();
	}
	
	/**
	 * MultipleUserIDException constructor.
	 * @param s string message
	 */
	public MultipleUserIDException(String s) {
		super(s);
	}
}
