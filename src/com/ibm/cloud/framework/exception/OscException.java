package com.ibm.cloud.framework.exception;

import com.ibm.cloud.framework.common.BaseModule;

/**
 * <strong>OscException</strong> serves as the base exception class of OSC framework.
 * Application can throw this exception directly or indirectly by sub-classing
 * it. Some convenience methods have been added to identify the module and error-codes.
 * <p>
 * To Note: JDK 1.3 does not support Chained Exceptions but we can still safely use it 
 * as the chained exception is just passed as java.lang.String message. Once the 
 * application is migrated to 1.4, the class can be altered later to chain the exception 
 * to super class 
 */
public class OscException extends Exception {

	private static final long serialVersionUID = 1L;

	private Throwable source = null;

    private ErrorCode errorCode = null;

    private BaseModule module = null;

    public OscException() {
        super();
    }

    /**
     * Constructor that accepts error in any form of throwable. This 
     * method can be used to wrap the original exception.
     * 
     * @param cause
     *            The original exception
     */
    public OscException(Throwable cause) {
        super(cause.getMessage());
    }
    
    /**
     * Constructor that accepts error in any form of throwable with Module. This 
     * method can be used to wrap the original exception.
     * 
     * @param cause
     *            The original exception
     * @param module
     *            The Module where error happened
     */
    public OscException(Throwable cause, BaseModule module) {
        super(cause.getMessage());
        this.module = module;
    }
    
    /**
     * Constructor that accepts error message in the form of String
     * 
     * @param message
     *            The error message
     */
    public OscException(String message) {
        super(message);
    }
    
   /**
     * Constructor that accepts error message in the form of String
     * and Throwable
     * 
     * @param message
     *            The error message
     * @param cause
     *            The original exception
     */
    public OscException(String message, Throwable cause) {
        super(message,cause);
    }
    
    /**
     * Constructor that accepts error message in the form of String with Module
     * 
     * @param message
     *            The error message
     * @param module
     *            The Module where error happened
     */
    public OscException(String message,BaseModule module) {
        super(message);
        this.module = module;
    }

    /**
     * Constructor that accepts predefined error codes
     * 
     * @param message
     *            The error message
     * @param errorCode
     *            The error code describing the error. Error code must be 
     *            system pre-defined error codes.
     * @param source
     *            the original exception if any.
     */
    public OscException(ErrorCode errorCodeObj) {
        this(errorCodeObj,null);
    }

  /**
   * Constructor that accepts predefined error codes and BaseModules
   * 
   * @param errorCode
   *            The pre-defined error code
   * @param module
   *            The Module where error happened
   */   
  public OscException(ErrorCode errorCodeObj, BaseModule module) {
      this(errorCodeObj,module,null);
  }
  
  /**
   * Constructor that provides comprehensive information of the error message.
   * Highly recommeded to use this constructor in place of others. The original 
   * exception is not lost.
   * 
   * @param errorCode
   *            The error code describing the error. Error code must be 
   *            system pre-defined error codes.
   * @param module
   *            The Module where error happened
   * @param throwable
   *            The original exception if any.
   */
  public OscException(ErrorCode errorCodeObj, BaseModule module, Throwable throwable) {
        this(throwable);
        this.source = throwable;
        this.errorCode = errorCodeObj;
        this.module = module;
  }
  
    /**
     * Convenience method to return the BaseModule in string format
     * 
     * @return String The Module where error happened or null if no BaseModule info could be found 
     */
    public String getModuleAsString() {
        return module==null?null:module.toString();
    }

    /**
     * Convenience method to set the BaseModule 
     * 
     * @return String the Module where error happened or null if no BaseModule info could be found 
     */
     public void setModule(BaseModule module) {
        this.module = module;
    }

    /**
     * Convenience method to return the error code in string format
     * 
     * @return String The error code or null if no error code could be found 
     */
    public String getErrorCodeAsString() {
        return errorCode==null?null:errorCode.toString();
    }

    /**
     * Convenience method to return the original exception
     * 
     * @return Throwable The original exception that was thrown by the application or null 
     * 				if no exception could be found 
     */
    public Throwable getSource() {
        return source;
    }
    
    /**
     * Method that returns a short description of this exception. If this object was created 
     * with a non-null error code, BaseModule and a throwable, then the result is the concatenation 
     * of Error code, BaseModule name and the root cause in the form of original exception.
     * 
     * @return String Description of exception
   	 */
    public String toString() {
        final String lineSep = System.getProperty("line.separator");

        final StringBuffer str = new StringBuffer();
        str.append("OscException [");
        str.append(getErrorCodeAsString()==null?"":getErrorCodeAsString());
        if(module!=null){
            str.append(getModuleAsString()==null?"":getModuleAsString());
        }
        if(source!=null){
            str.append(source.getMessage());
        }
        str.append("]");
        str.append(lineSep);
        
        if(str.length()<20)
        	return "";

        return str.toString();
    }
}

