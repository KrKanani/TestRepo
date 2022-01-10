package com.project.safetychain.apiauto.pageobjects;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.project.safetychain.api.models.Login.LoginResponse;
import com.project.safetychain.apiauto.pageobjects.ResourceDesigner.Data;
import com.project.testbase.TestBase;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Auth extends TestBase {

	public static String LoginUrl = baseURI + AuthUrls.LOGIN_URL;
	public static String DPTLoginUrl = baseURI + AuthUrls.DPT_LOGIN_URL;

	public void setAccessToken() {
		Response response = null;
		try {
			RequestSpecification request = RestAssured.given();

			String loginJson = "{\r\n" + "  \"clientId\": \"scsTestUser_" + tenantName + "\",\r\n"
					+ "  \"username\": \"" + username + "\",\r\n" + "  \"password\": \"" + password + "\"\r\n" + "}";

			// Add a header stating the Request body is a JSON
			request.header("Content-Type", "application/json");
			request.header("Accept", "application/json, text/plain, /");

			// Add the Json to the body of the request
			request.body(loginJson);

			// Post the request and check the response
			response = request.post(LoginUrl);

			logInfo("Response Body = " + response.asString());

			Gson gson = new GsonBuilder().create();
			LoginResponse login = gson.fromJson(response.asString(), LoginResponse.class);
			accessToken = login.Data.Token;
			logInfo("Access Token set " + accessToken);
		} catch (Exception e) {
			logError("Failed to set Access Token " + e.getMessage());
		}
	}

	public void setAccessToken(boolean dptLogin) {
		Response response = null;
		try {
			if (dptLogin) {
				RequestSpecification request = RestAssured.given();

				String loginJson = "{\r\n" + "  \"username\": \"" + username + "\",\r\n" + "  \"password\": \""
						+ password + "\"\r\n" + "}";

				// Add a header stating the Request body is a JSON
				request.header("Content-Type", "application/json");
				request.header("Accept", "application/json, text/plain, /");

				// Add the Json to the body of the request
				request.body(loginJson);

				// Post the request and check the response
				response = request.post(DPTLoginUrl);

				Gson gson = new GsonBuilder().create();
				LoginResponse login = gson.fromJson(response.asString(), LoginResponse.class);
				accessToken = login.Data.Token;
				logInfo("Access Token set " + accessToken);
			}
		} catch (Exception e) {
			logError("Failed to set Access Token " + e.getMessage());
		}
	}

//	{
//	    // scsTestUser_test1 - scsTestUser_---TENANTNAME---- and for user autouser01
//	    // "clientId" :"scsTestUser_test1",
//	    "username": "superadmin",
//	    "password": "sliptest23772five"
//	}

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
		public List<com.project.safetychain.api.models.ResourceDesigner.FormDetails> FormDetails;
		public boolean Status;
		public Errors Errors;
		public boolean RefreshToken;
		public String Message;
	}

	public class Errors {
		public int ErrorCode;
		public String ErrorMessage;
	}
}
