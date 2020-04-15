package com.ibm.cloud.framework.exception;

public class VCXException extends OscException {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Creates an instance of <code>VCXException</code>
	 */
	public VCXException() {
		super();
	}
	
	/**
	 * Creates an instance of <code>VCXException</code>
	 * @param s an error message
	 */
	public VCXException(String s) {
		super(s);
		printToDiagLog(s);
	}
	
	/**
	 * writes error message to file if this exception comes from DA layer,
	 * else writes it to database
	 * @param errmsg an error message
	 */
	public void printToDiagLog(String errmsg){
		String functionName = "VCXException - printToDiagLog - ";
		
		try {
			System.out.println(functionName + " " + errmsg);
		}catch (Exception e){
			System.out.println(functionName + " " + errmsg + e.getMessage());	
		}
	}
}
