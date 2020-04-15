package com.ibm.cloud.da;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

public class WebDbKey implements java.io.Serializable  {
	
    private static final long serialVersionUID = 1L;
	public String uniqueId;

    public  WebDbKey() {
		
	}
    
    /**
	 * Creates an instance of <code>WebDbKey</code> from an unique long value
	 * @param inputDbKeyValue a unique value
	 */
	public WebDbKey(String inputDbKeyValue) {
		this.setUniqueId(inputDbKeyValue);
	}
    
    /**
     * Create a unique ID.
     *
     * @throws Exception 
     */
	public  WebDbKey(boolean randomKey) throws Exception {
		super();
		if(randomKey){
			String nextGeneratedId = WebDbKey.getRandomUUID();
			this.setUniqueId(nextGeneratedId);
		}else{
			String nextGeneratedId = WebDbKey.getRandomUUID();
			this.setUniqueId(nextGeneratedId);
		}
	}
	
	/**
	 * Create a unique ID.
	 *
	 * @throws Exception
	 */
	public WebDbKey(boolean createKey, String signature) throws Exception {
		super();
		if(createKey){
			if(signature != null){
				String generatedId = WebDbKey.getNameUUID(signature);
				this.setUniqueId(generatedId);
			}else{
				String generatedId = WebDbKey.getRandomUUID();
				this.setUniqueId(generatedId);
			}
		}else{
			this.setUniqueId(signature);
		}
	}
	
	public static String getRandomUUID() {
        try {
            String md5 = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
            return md5;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
	
	public static String getNameUUID(final String signature) {
        try {
            String md5 = UUID.nameUUIDFromBytes(signature.getBytes("UTF8")).toString().replaceAll("-", "").toUpperCase();
            return md5;
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
	
	/**
	 * 
	 * @return the stored value in the <code>uniqueId</code> variable
	 */
	public String getUniqueId() {
		return uniqueId;
	}
	
	/**
	 * Sets value of the <code>uniqueId</code> variable
	 *  
	 * @param newUniqueId a new value of <code>uniqueId</code> varialbe 
	 */
	public void setUniqueId(String newUniqueId) {
		uniqueId = newUniqueId;
	}
}
