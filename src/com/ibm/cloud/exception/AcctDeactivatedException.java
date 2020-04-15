package com.ibm.cloud.exception;

import com.ibm.cloud.framework.exception.VCXException;

/**
 * This exception is thrown when the authenication function is called for a user with a status of "deactivated" 
 */
public class AcctDeactivatedException extends VCXException {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * AcctDeactivatedException constructor.
	 */
	public AcctDeactivatedException() {
		super();
	}
	
	/**
	 * AcctDeactivatedException constructor.
	 * @param s string message
	 */
	public AcctDeactivatedException(String s) {
		super(s);
	}
}
