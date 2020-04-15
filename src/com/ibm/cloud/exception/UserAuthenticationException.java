package com.ibm.cloud.exception;

import com.ibm.cloud.framework.exception.VCXException;

public class UserAuthenticationException extends VCXException {
	
	private static final long serialVersionUID = 1L;

	/**
	 * UserAuthenticationException constructor.
	 */
	public UserAuthenticationException(){
		super();
	}
	
	/**
	 * UserAuthenticationException constructor.
	 * @param e string message
	 */
	public UserAuthenticationException(String e){
		super(e);
	}
}
