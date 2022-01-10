package com.project.safetychain.api.models;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.project.safetychain.api.models.Login.LoginResponse;
import com.project.safetychain.api.models.ResourceDesigner.Data;
import com.project.testbase.TestBase;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Auth extends TestBase {

	public static String LoginUrl = baseURI + AuthUrls.LOGIN_URL;
	public String otherLoginUrl = otherBaseURI + AuthUrls.LOGIN_URL;


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


	// For other APIs
	public void setAccessToken(String url) {
		Response response = null;
		try {
			RequestSpecification request = RestAssured.given();

			String loginJson = "{\r\n" + "  \"clientId\": \"scsTestUser_" + otherTenantName + "\",\r\n"
					+ "  \"username\": \"" + username + "\",\r\n" + "  \"password\": \"" + password + "\"\r\n" + "}";

			// Add a header stating the Request body is a JSON
			request.header("Content-Type", "application/json");
			request.header("Accept", "application/json, text/plain, /");

			// Add the Json to the body of the request
			request.body(loginJson);

			// Post the request and check the response
			response = request.post(url);

			Gson gson = new GsonBuilder().create();
			LoginResponse login = gson.fromJson(response.asString(), LoginResponse.class);
			otherAccessToken = login.Data.Token;
			logInfo("LOGGED IN and Access Token IS SET");
		} catch (Exception e) {
			logError("Failed to set Access Token " + e.getMessage());
		}
	}

	public class SimpleDataResponse {
		public String Data;
		public boolean Status;
		public Errors Errors;
		public boolean RefreshToken;
		public String Message;
		public String RequestStatus;
	}

	public class SingleDataResponse {
		public Data Data;
		public boolean Status;
		public Errors Errors;
		public boolean RefreshToken;
		public String Message;
	}

	public static class MultipleDataResponse {
		public List<Data> Data;
		public List<com.project.safetychain.apiauto.pageobjects.ResourceDesigner.FormDetails> FormDetails;
		public boolean Status;
		public Errors Errors;
		public boolean RefreshToken;
		public String Message;
		public String HasPermission;
	}

	public class Errors {
		public int ErrorCode;
		public String ErrorMessage;
	}
}
