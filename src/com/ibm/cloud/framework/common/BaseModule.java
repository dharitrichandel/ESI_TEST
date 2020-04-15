package com.ibm.cloud.framework.common;

import java.io.Serializable;

public class BaseModule implements Serializable {
    
	private static final long serialVersionUID = 1L;

	private final String name;

	public BaseModule(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public String toString() {
		return name;
	}

	public static final BaseModule GENERAL 	= new BaseModule("General");

	public static final BaseModule CONFIG 	= new BaseModule("Configuration");

	public static final BaseModule DIAGNOSTICS 	= new BaseModule("Diagnostics");
	
	public static final BaseModule DATABASE = new BaseModule("DataBase");

}


