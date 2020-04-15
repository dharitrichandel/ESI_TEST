package com.ibm.cloud.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cloudant.client.api.Database;
import com.cloudant.client.api.Search;
import com.cloudant.client.api.model.Document;
import com.ibm.cloud.common.UserAccount;
import com.ibm.cloud.da.CloudantClientMgr;
import com.ibm.cloud.da.DocumentDetails;
import com.ibm.cloud.da.WebDbKey;
import com.ibm.cloud.util.DateUtil;

public class TestCloudant {

	private String databaseName = "testdb";
	
	public static void main(String[] args) {
		TestCloudant testCloudant = new TestCloudant();
		testCloudant.main();
	}
	
	public void main() {
		//getAllDatabase();
		//getDatabase(databaseName);
		//getOrCreateDatabase(databaseName, true);
		//getAllDocumentInDatabase(databaseName);
		
		//createUserAccount();
		//updateUserAccount("0C3C6D1D167C44C3AE6986214F61B0C2");
		//updateUserAccountPassword("0C3C6D1D167C44C3AE6986214F61B0C2");
		//deleteUserAccount("0C3C6D1D167C44C3AE6986214F61B0C2");
		getUserAccount("0C3C6D1D167C44C3AE6986214F61B0C2");
		
	}
	
	public void getUserAccount(String id) {
		String documentId = id;
		Map<String, Object> doc = getDocumentInDatabase(databaseName, documentId);
		UserAccount userAccount = null;
		if(doc != null) {
			userAccount = new UserAccount();
			try {
				UserAccount.setUserAccountResultSet(userAccount, doc);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		try {
			UserAccount.authenticate(userAccount);
		} catch (Exception e) {
			System.out.println(e.getCause());
			e.printStackTrace();
		}
	}
	
	public void createUserAccount() {
		UserAccount userAccount = null;
		Map<String, Object> data = new HashMap<String, Object>();
		String documentId = null;
		try {
			userAccount = new UserAccount();
			userAccount.setId(new WebDbKey(true).getUniqueId());
			userAccount.setUserId("test1@test.com");
			userAccount.setUserPassword("1234");
			userAccount.setUserName("Test User");
			userAccount.setUserStatus(UserAccount.Activated);
			userAccount.setUserCreatedDate(DateUtil.getDefaultDateFormat_yyyy_MM_dd_hh_mm_ss());
			UserAccount.setUserAccountStatementInsert(userAccount, data);
			//
			documentId = userAccount.getId();
			createDocumentInDatabase(databaseName, documentId, data);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updateUserAccount(String id) {
		UserAccount userAccount = null;
		HashMap<String, Object> dataUpdated = new HashMap<String, Object>();
		String documentId = null;
		try {
			userAccount = new UserAccount();
			userAccount.setId(id);
			userAccount.setUserName("Test User1");
			userAccount.setUserUpdatedDate(DateUtil.getDefaultDateFormat_yyyy_MM_dd_hh_mm_ss());
			UserAccount.setUserAccountStatementUpdate(userAccount, dataUpdated);
			//
			documentId = userAccount.getId();
			updateDocumentInDatabase(databaseName, documentId, dataUpdated);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updateUserAccountPassword(String id) {
		UserAccount userAccount = null;
		HashMap<String, Object> dataUpdated = new HashMap<String, Object>();
		String documentId = null;
		try {
			userAccount = new UserAccount();
			userAccount.setId(id);
			userAccount.setUserPassword("12345");
			userAccount.setUserUpdatedDate(DateUtil.getDefaultDateFormat_yyyy_MM_dd_hh_mm_ss());
			UserAccount.setUserAccountStatementUpdatePassword(userAccount, dataUpdated);
			//
			documentId = userAccount.getId();
			updateDocumentInDatabase(databaseName, documentId, dataUpdated);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteUserAccount(String id) {
		UserAccount userAccount = null;
		HashMap<String, Object> dataUpdated = new HashMap<String, Object>();
		String documentId = null;
		try {
			userAccount = new UserAccount();
			userAccount.setId(id);
			userAccount.setUserStatus(UserAccount.Deactivated);
			userAccount.setUserUpdatedDate(DateUtil.getDefaultDateFormat_yyyy_MM_dd_hh_mm_ss());
			UserAccount.setUserAccountStatementDelete(userAccount, dataUpdated);
			//
			documentId = userAccount.getId();
			updateDocumentInDatabase(databaseName, documentId, dataUpdated);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void createDocumentInDatabase(String databaseName, String documentId, Map<String, Object> doc) {
		Database db = null;
		try {
			db = CloudantClientMgr.getDB(databaseName);
			db.save(doc);
			System.out.println("Created New Document With ID : " + documentId+" In Database : "+databaseName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void updateDocumentInDatabase(String databaseName, String documentId, HashMap<String, Object> docUpdated) {
		Database db = null;
		try {
			db = CloudantClientMgr.getDB(databaseName);
			// check if document exist
			HashMap<String, Object> doc = (documentId == null) ? null : db.find(HashMap.class, documentId);
			if (doc != null) {
				System.out.println(doc.get("_id")+" => "+doc);
				for (Map.Entry<String, Object> entry : docUpdated.entrySet()) {  
					doc.put(entry.getKey(), entry.getValue());
				} 
				db.update(doc);
				System.out.println("Document : "+documentId+" Update Successful In Database : "+databaseName);
			}else {
				System.out.println("Document : "+documentId+" Not Found In Database : "+databaseName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void deleteDocumentInDatabase(String databaseName, String documentId) {
		Database db = null;
		try {
			db = CloudantClientMgr.getDB(databaseName);
			// check if document exist
			HashMap<String, Object> doc = (documentId == null) ? null : db.find(HashMap.class, documentId);
			if (doc != null) {
				System.out.println(doc.get("_id")+" => "+doc);
				db.remove(doc);
				System.out.println("Document : "+documentId+" Deleted Successful From Database : "+databaseName);
			}else {
				System.out.println("Document : "+documentId+" Not Found In Database : "+databaseName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void getAllDocumentInDatabase(String databaseName) {
		Database db = null;
		try {
			db = CloudantClientMgr.getDB(databaseName);
			
			// get all the document present in database (Option 1)
			List<Document> allDocs1 = db.getAllDocsRequestBuilder().includeDocs(true).build().getResponse().getDocs();
			if (allDocs1.size() != 0) {
				for (Document doc : allDocs1) {
					doc.getId();
					System.out.println("doc.getId => "+doc.getId());
				}
			}
			
			// get all the document present in database (Option 2)
			List<HashMap> allDocs = db.getAllDocsRequestBuilder().includeDocs(true).build().getResponse().getDocsAs(HashMap.class);
			System.out.println("allDocs => "+allDocs);
			if (allDocs != null && allDocs.size() > 0) {
				for (HashMap doc : allDocs) {
					HashMap<String, Object> obj = db.find(HashMap.class, doc.get("_id") + "");
					System.out.println(doc.get("_id")+" => "+obj);
				}
			}else {
				System.out.println("Documents Not Found In Database : "+databaseName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public HashMap<String, Object> getDocumentInDatabase(String databaseName, String documentId) {
		Database db = null;
		HashMap<String, Object> doc = null;
		try {
			db = CloudantClientMgr.getDB(databaseName);
			
			// check if document exists
			doc = (documentId == null) ? null : db.find(HashMap.class, documentId);
			if (doc != null) {
				System.out.println(doc.get("_id")+" => "+doc);
			}else {
				System.out.println("Document : "+documentId+" Not Found In Database : "+databaseName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return doc;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void searchDocumentInDatabase(String databaseName, String searchIndex, String searchString) {
		Database db = null;
		try {
			db = CloudantClientMgr.getDB(databaseName);
			
			Search search = db.search(searchIndex).includeDocs(true);
			List<HashMap> searchDoc = (searchString == null) ? null : search.query(searchString, HashMap.class);
			if (searchDoc != null && searchDoc.size() > 0) {
				for (HashMap doc : searchDoc) {
					HashMap<String, Object> obj = db.find(HashMap.class, doc.get("_id") + "");
					System.out.println(doc.get("_id")+" => "+obj);
				}
			}else {
				System.out.println("Document For searchString ["+searchString+"] In searchIndex ["+searchIndex+"] Not Found In Database : "+databaseName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void getAllDatabase() {
		List<String> db = null;
		try {
			db = CloudantClientMgr.getAllDB();
			System.out.println(db);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void getDatabase(String databaseName) {
		Database db = null;
		try {
			db = CloudantClientMgr.getDB(databaseName);
			if(db != null) {
				System.out.println("Database : "+databaseName+" exist.");
				System.out.println("Database : "+db);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void getOrCreateDatabase(String databaseName, boolean create) {
		Database db = null;
		try {
			db = CloudantClientMgr.getDB(databaseName, create);
			if(db != null) {
				System.out.println("Database : "+databaseName+" exist.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteDatabase(String databaseName) {
		Database db = null;
		try {
			db = CloudantClientMgr.getDB(databaseName);
			if(db != null) {
				System.out.println("Database : "+databaseName+" exist.");
				CloudantClientMgr.deleteDB(databaseName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	

}
