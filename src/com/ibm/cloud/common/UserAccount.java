/* 
 *    OSC Web Interface 
 *    
 *    (c) Copyright IBM Corp. 2006
 *   
 *    Name:  UserAccount.java
 *    Package: com.ibm.procurement.osc.common
 *    Author: 
 *    Date:  
 *                      
 **************************************************************************************
 * Change Log:                                                                        *
 * Date:      Pgmr  PTR/CR#         Description                                       *
 *                            *             
 **************************************************************************************
 */
package com.ibm.cloud.common;

import java.util.Map;

import com.ibm.cloud.exception.AcctDeactivatedException;
import com.ibm.cloud.exception.DataIntegrityException;
import com.ibm.cloud.exception.UnknownUserIDException;
import com.ibm.cloud.exception.UserAuthorizationException;


/**
 * This class is a container consisting mainly all the setters and getters for the USERACCOUNT table
 * It contains methods to retrieve records based on accountID & PersonId
 *
 * @author 
 * 
 */
public class UserAccount {
	
	public static String userAccountDocumentFields = "ID#USER_ID#PASSWORD#NAME#STATUS#CREATED_DATE#UPDATED_DATE";
	
	private String  id;
	
	private String userId;
	
	private String userPassword;
	
	private String userName;
	
	private String userStatus;
	
	private String userCreatedDate;
	
	private String userUpdatedDate;
	
	public static String Activated = "Activated";
	
	public static String Deactivated = "Deactivated";
	
	/**
	 * UserAccount constructor comment.
	 *
	 * @throws com.ibm.procurement.osc.framework.exception.VCXException 
	 */
	public UserAccount() {
		super();
	}
	
	public static void setUserAccountResultSet(UserAccount userAccount, Map<String, Object> data) throws Exception {
		userAccount.setId((String)data.get("ID"));
		userAccount.setUserId((String)data.get("PASSWORD"));
		userAccount.setUserPassword((String)data.get("USER_ID"));
		userAccount.setUserName((String)data.get("NAME"));
		userAccount.setUserStatus((String)data.get("STATUS"));
		userAccount.setUserCreatedDate((String)data.get("CREATED_DATE"));
		userAccount.setUserUpdatedDate((String)data.get("UPDATED_DATE"));
	}
	
	public static void setUserAccountStatementInsert(UserAccount userAccount, Map<String, Object> data) throws Exception {
		data.put("_id", userAccount.getId());
		data.put("ID", userAccount.getId());
		data.put("USER_ID", userAccount.getUserId());
		data.put("PASSWORD", userAccount.getUserPassword());
		data.put("NAME", userAccount.getUserName());
		data.put("STATUS", userAccount.getUserStatus());
		data.put("CREATED_DATE", userAccount.getUserCreatedDate());
		data.put("UPDATED_DATE", "");
	}
	
	public static void setUserAccountStatementUpdate(UserAccount userAccount, Map<String, Object> data) throws Exception {
		data.put("NAME", userAccount.getUserName());
		data.put("UPDATED_DATE", userAccount.getUserUpdatedDate());
	}
	
	public static void setUserAccountStatementUpdatePassword(UserAccount userAccount, Map<String, Object> data) throws Exception {
		data.put("PASSWORD", userAccount.getUserPassword());
		data.put("UPDATED_DATE", userAccount.getUserUpdatedDate());
	}
	
	public static void setUserAccountStatementDelete(UserAccount userAccount, Map<String, Object> data) throws Exception {
		data.put("STATUS", userAccount.getUserStatus());
		data.put("UPDATED_DATE", userAccount.getUserUpdatedDate());
	}
	
	public static void authenticate(UserAccount userAccount) throws DataIntegrityException, AcctDeactivatedException, UnknownUserIDException, UserAuthorizationException{
		if (userAccount == null ) {
			throw new UnknownUserIDException();
		}else if (userAccount.getUserStatus().equals(Deactivated)) {
			throw new AcctDeactivatedException();
		}else if (!userAccount.getUserStatus().equals(Activated) ) {
			throw new DataIntegrityException();
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	public String getUserCreatedDate() {
		return userCreatedDate;
	}

	public void setUserCreatedDate(String userCreatedDate) {
		this.userCreatedDate = userCreatedDate;
	}

	public String getUserUpdatedDate() {
		return userUpdatedDate;
	}

	public void setUserUpdatedDate(String userUpdatedDate) {
		this.userUpdatedDate = userUpdatedDate;
	}

	

}
