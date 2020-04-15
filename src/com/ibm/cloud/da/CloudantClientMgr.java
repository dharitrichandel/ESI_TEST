package com.ibm.cloud.da;

import java.util.Set;
import java.util.List;
import java.util.Map.Entry;

import com.cloudant.client.api.ClientBuilder;
import com.cloudant.client.api.CloudantClient;
import com.cloudant.client.api.Database;
import com.cloudant.client.org.lightcouch.CouchDbException;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class CloudantClientMgr {

	private static CloudantClient cloudant = null;
	private static Database db = null;

	private static String user = "a0587177-fc70-4389-92f2-ed2fad1ed413-bluemix";
	private static String password = "616a6f07af660c8d37c27446334cf29c76fed73b78fcfcfe41ba5ca274eb415f";
	
	private CloudantClientMgr() {
		
	}
	
	private static void initClient() {
		if (cloudant == null) {
			synchronized (CloudantClientMgr.class) {
				if (cloudant != null) {
					return;
				}
				cloudant = createClient();

			}
		}
	}
	
	private static CloudantClient createClient() {
		String VCAP_SERVICES = System.getenv("VCAP_SERVICES");
		String serviceName = null;

		if (VCAP_SERVICES != null) {
			// When running in Bluemix, the VCAP_SERVICES env var will have the credentials for all bound/connected services
			// Parse the VCAP JSON structure looking for cloudant.
			JsonObject obj = (JsonObject) new JsonParser().parse(VCAP_SERVICES);
			Entry<String, JsonElement> dbEntry = null;
			Set<Entry<String, JsonElement>> entries = obj.entrySet();
			// Look for the VCAP key that holds the cloudant no sql db information
			for (Entry<String, JsonElement> eachEntry : entries) {
				if (eachEntry.getKey().toLowerCase().contains("cloudant")) {
					dbEntry = eachEntry;
					break;
				}
			}
			if (dbEntry == null) {
				throw new RuntimeException("Could not find cloudantNoSQLDB key in VCAP_SERVICES env variable");
			}

			obj = (JsonObject) ((JsonArray) dbEntry.getValue()).get(0);
			serviceName = (String) dbEntry.getKey();
			System.out.println("Service Name - " + serviceName);

			obj = (JsonObject) obj.get("credentials");

			user = obj.get("username").getAsString();
			password = obj.get("password").getAsString();

		} else {
			System.out.println("VCAP_SERVICES env var doesn't exist: running locally.");
		}

		try {
			System.out.println("Connecting to Cloudant : " + user);
			CloudantClient client = ClientBuilder.account(user)
					.username(user)
					.password(password)
					.build();
			return client;
		} catch (CouchDbException e) {
			throw new RuntimeException("Unable to connect to repository", e);
		}
	}
	
	public static Database getDB(String databaseName) {
		if (cloudant == null) {
			initClient();
		}

		System.out.println("Database : "+db +"("+databaseName+")");
		
		if (db == null) {
			try {
				db = cloudant.database(databaseName, false);
			} catch (Exception e) {
				throw new RuntimeException("Database ["+databaseName+"] Not found", e);
			}
		}
		System.out.println("Database : "+db.getClass().getName());
		return db;
	}
	
	public static Database getDB(String databaseName, boolean create) {
		if (cloudant == null) {
			initClient();
		}

		if (db == null) {
			try {
				db = cloudant.database(databaseName, create);
			} catch (Exception e) {
				throw new RuntimeException("Database ["+databaseName+"] Not found", e);
			}
		}
		return db;
	}
	
	public static void deleteDB(String databaseName) {
		if (cloudant == null) {
			initClient();
		}

		if (db == null) {
			try {
				db = getDB(databaseName);
			} catch (Exception e) {
				throw new RuntimeException("Database ["+databaseName+"] Not found", e);
			}
		}
		
		try {
			if (db != null) {
				cloudant.deleteDB(databaseName);
			}else {
				System.out.println("Unable To Delete Database ["+databaseName+"], Reason: Database Not Found On Cloudant");
			}
		} catch (Exception e) {
			throw new RuntimeException("Database ["+databaseName+"] Not found", e);
		}
	}
	
	public static List<String> getAllDB() {
		if (cloudant == null) {
			initClient();
		}

		List<String> db = null;
		try {
			db = cloudant.getAllDbs();
		} catch (Exception e) {
			throw new RuntimeException("DB Not found", e);
		}
		return db;
	}

	
}
