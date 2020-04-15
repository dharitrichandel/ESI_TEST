package com.ibm.cloud.exception;

import com.ibm.cloud.framework.exception.VCXException;

public class DataIntegrityException extends VCXException {
		
	private static final long serialVersionUID = 1L;
		
	/**
	 * DataIntegrityException constructor.
	 */
	public DataIntegrityException() {
		super();
	}
	
	/**
	 * DataIntegrityException constructor.
	 * @param s string message
	 */
	public DataIntegrityException(String s) {
		super(s);
	}
}
