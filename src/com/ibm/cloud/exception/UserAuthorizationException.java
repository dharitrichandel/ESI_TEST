package com.ibm.cloud.exception;

import com.ibm.cloud.framework.exception.VCXException;

public class UserAuthorizationException extends VCXException{
	
	private static final long serialVersionUID = 1L;

	/**
	 * UserAuthorizationException constructor.
	 */
	public UserAuthorizationException() {
		super();
	}
	
	/**
	 * UserAuthorizationException constructor.
	 * @param param string message
	 */
	public UserAuthorizationException(String param) {
		super(param);
	}
}
