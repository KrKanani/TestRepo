package com.project.safetychain.apiproject.models;


import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.project.safetychain.apiauto.pageobjects.AuthUrls;
import com.project.testbase.TestBase;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Auth extends TestBase{

	public static String LoginUrl = baseURI + AuthUrls.LOGIN_URL;
	


	public void setAccessToken() {
		Response response = null;
		try {
			RequestSpecification request = RestAssured.given();
			String loginJson = "{\r\n" + "  \"clientId\": \"scsTestUser_" + tenantName + "\",\r\n"
					+ "  \"username\": \"" + username + "\",\r\n" + "  \"password\": \"" + password + "\"\r\n" + "}";
			request.header("Content-Type", "application/json");
			request.header("Accept", "application/json, text/plain, /");
			request.body(loginJson);
			response = request.post(LoginUrl);
			Gson gson = new GsonBuilder().create();
			LoginResponse login = gson.fromJson(response.asString(), LoginResponse.class);
			accessToken = login.Data.Token;
			sessionId = response.getCookies().get("ASP.NET_SessionId");
			affinityId = response.getCookies().get("ARRAffinity");
			logInfo("LOGGED IN and Access Token IS SET");
		} catch (Exception e) {
			logError("Failed to set Access Token " + e.getMessage());	
		}
	}

	public void setSupplierAccessToken(String username,String password) {
		Response response = null;
		try {
			RequestSpecification request = RestAssured.given();
			String loginJson = "{\r\n" + "  \"clientId\": \"scsTestUser_" + tenantName + "\",\r\n"
					+ "  \"username\": \"" + username + "\",\r\n" + "  \"password\": \"" + password + "\"\r\n" + "}";
			request.header("Content-Type", "application/json");
			request.header("Accept", "application/json, text/plain, /");
			request.body(loginJson);
			response = request.post(LoginUrl);
			Gson gson = new GsonBuilder().create();
			LoginResponse login = gson.fromJson(response.asString(), LoginResponse.class);
			accessToken = login.Data.Token;
			sessionId = response.getCookies().get("ASP.NET_SessionId");
			affinityId = response.getCookies().get("ARRAffinity");

			logInfo("LOGGED IN to supplier Portal and Access Token IS SET");
		} catch (Exception e) {
			logError("Failed to login supplier Portal" + e.getMessage());	
		}
	}


	public class SimpleDataResponse1 {
		public Data Data;
		public boolean Status;
		public boolean RefreshToken;
		public String Message;
	}

	public static class MultipleDataResponse {
		public List<Data> Data;
		public List<com.project.safetychain.apiauto.pageobjects.ResourceDesigner.FormDetails> FormDetails;
		public boolean Status;
		public boolean RefreshToken;
		public String Message;
		public String HasPermission;
	}

	public class LoginResponse {
		public Data Data;
		public boolean Status;
		public boolean RefreshToken;
		public String Message;
	}

	public class Data {
		public String IsSuccessfull;
		public String RemainingLoginAttempts;
		public String Token;
		public boolean IsFirstLogin;
		public boolean IsPartnerPortalUser;
		public String PartnerInfo;
		public String UserId;
		public boolean PasswordExpired;
		public String UserCurrentStatus;
		public String UserType;
		public List<FormDetails> FormDetails;
		public String ID;
	}

	public class FormDetails{		
		public List<SectionElements> SectionElements;
		public String Id;
		public String ShortName;
		public String Name;
	}

	public class SectionElements{
		public String ShortName;
		public String Type;
		public String Id;
		public String Name;
		public List<Options> Options;
	}

	public class Options{
		public String Id;
		public String Name;
	}

	public class SingleDataResponse {
		public String Data;
		public boolean Status;
	}

}
