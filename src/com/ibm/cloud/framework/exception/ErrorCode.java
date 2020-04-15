package com.ibm.cloud.framework.exception;

public class ErrorCode {

    public final String errorCode;
    
    public ErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

   
    public String toString() {
        if (errorCode == null)
            return "UnKnown";
        return this.errorCode + ": " + this.getDescription();
    }

    public String getErrorCode() {
        return this.errorCode;
    }

   
    public String getDescription() {
        return "Undefined error";
    }

    public static final ErrorCode NOERROR = new ErrorCode("ERR-00000");

    public static final ErrorCode UNKNOWN = new ErrorCode("ERR-00010");

    public static final ErrorCode UNEXPECTED = new ErrorCode("ERR-00011");

    public static final ErrorCode UNDEFINED = new ErrorCode("ERR-00012");

    public static final ErrorCode RESOURCE_MISSING = new ErrorCode("ERR-00021");

    public static final ErrorCode RESOURCE_UNAVAILABLE = new ErrorCode("ERR-00022");
    
    public static final ErrorCode RECORD_NOT_FOUND = new ErrorCode("ERR-00023");
    
    public static final ErrorCode INTERNAL_SERVER = new ErrorCode("ERR-00201");

    public static final ErrorCode SERVER_BUSY = new ErrorCode("ERR-00202");

    public static final ErrorCode SERVER_UNAVAILABLE = new ErrorCode("ERR-00203");

    public static final ErrorCode SERVER_GOING_DOWN = new ErrorCode("ERR-00204");

    public static final ErrorCode DB_SERVER = new ErrorCode("ERR-00204");

    public static final ErrorCode DB_CONNECTION = new ErrorCode("ERR-00206");

    public static final ErrorCode DB_CREATE_CONNECTION = new ErrorCode("ERR-00207");

    public static final ErrorCode DB_SQL = new ErrorCode("ERR-00208");
    
    public static final ErrorCode OS_NOT_SUPPORTED = new ErrorCode("ERR-00209");

    public static final ErrorCode LOG4J_FILE_MISSING = new ErrorCode("ERR-00220");

    public static final ErrorCode NULL_POINTER = new ErrorCode("ERR-00301");

    public static final ErrorCode ILLEGAL_ARGUMENT = new ErrorCode("ERR-00302");

    public static final ErrorCode SYNTAX_ERROR = new ErrorCode("ERR-00303");

    public static final ErrorCode INVALID_PARAMETER = new ErrorCode("ERR-00304");

    public static final ErrorCode REQUIRED_FIELDS_MISSING = new ErrorCode("ERR-00305");

    public static final ErrorCode INVALID_USER = new ErrorCode("ERR-00306");

    public static final ErrorCode INVALID_USERNAME = new ErrorCode("ERR-00307");

    public static final ErrorCode AUTHENTICATION_FAILED = new ErrorCode("ERR-00308");

    public static final ErrorCode NOT_LOGGED_IN = new ErrorCode("ERR-00309");
    
    public static final ErrorCode UNKNOWN_USER = new ErrorCode("ERR-00310");
    
    public static final ErrorCode DUPLICATE_USER = new ErrorCode("ERR-00311");
    
    public static final ErrorCode OPERATION_NOT_SUPPORTED = new ErrorCode("ERR-00312");
    
    public static final ErrorCode UNACTIVATED_PERSON = new ErrorCode("ERR-00313");
}