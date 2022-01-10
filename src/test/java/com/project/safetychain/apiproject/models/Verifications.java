package com.project.safetychain.apiproject.models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.project.utilities.ApiUtils;
import com.project.utilities.ApiUtils.METHOD_TYPE;
import io.restassured.response.Response;

public class Verifications extends Auth{

	public String AddVerificationUrl = baseURI + VerificationsURL.ADD_UPDATE_VERIFICATION_URL;
	public String GetAllVerificationUrl = baseURI + VerificationsURL.GET_ALL_VERIFICATIONS_URL;
	public String GetVerificationDetailsUrl = baseURI + VerificationsURL.GET_ROLES_FOR_VERIFICATION_URL;

	public static final String SUPERADMIN_ROLE_NAME = "SuperAdmin";
	public static final String SUPERADMIN_ROLE_ID = "306d1ef8-298c-4fdc-95d1-6dc41ee612d6";

	/**
	 * This method is used to create Verification
	 * @author pashine_a
	 * @param verificationDetails (Verification details)
	 * @return SimpleDataResponse. It will return SimpleDataResponse response result
	 */
	public SingleDataResponse1 createVerification(VerificationDetails verificationDetails) {
		SingleDataResponse1 response = null;
		try {

			String createVerificationJSON = null;

			ApiUtils apiUtils = new ApiUtils();
			Gson gson = new GsonBuilder().create();

			Map<String, String> headers = new HashMap<String, String>();
			headers.put("Content-Type", "application/json");
			headers.put("Accept", "application/json, text/plain, /");
			headers.put("Authorization", "Bearer " + accessToken);

			createVerificationJSON = getCreateVerificationJSON(verificationDetails);

			Response createVerificationResponse = apiUtils.submitRequest(METHOD_TYPE.POST, createVerificationJSON, headers,
					AddVerificationUrl);

			response = gson.fromJson(createVerificationResponse.asString(),
					SingleDataResponse1.class);

			return response;

		} catch (Exception e) {
			logError("Failed to add Verification - '"+verificationDetails.VerificationName+" ' - "+ e.getMessage());
			return response;
		}
	}

	/**
	 * This method is used to get add verification template(JSON payload)
	 * @author pashine_a
	 * @param verificationDetails (Verification details)
	 * @return String. It will return create verification payload
	 */
	public String getCreateVerificationJSON(VerificationDetails verificationDetails) {
		String addVerificationDetails =  "{"
				+ "  \"Id\": null,"
				+ "  \"Name\": \""+verificationDetails.VerificationName+"\","
				+ "  \"IsEnable\": "+verificationDetails.IsEnable+","
				+ "  \"IsCommentsEnabled\": "+verificationDetails.IsCommentsEnabled+","
				+ "  \"VerificationComments\": ["
				+ getComments(verificationDetails.comments)
				+ "  ],"
				+ "  \"AddVerificationRoleMappings\": ["
				+ getRoleDetails(verificationDetails.roles)
				+ "  ],"
				+ "  \"AddVerificationComments\": ["
				+ getComments(verificationDetails.comments)
				+ "  ]"
				+ "}";

		return addVerificationDetails;

	}

	/**
	 * This method is used to get 'Name' & 'RoleId' JSON
	 * @author pashine_a
	 * @param instances (Name & RoleId pair)
	 * @return String. It will return Name & RoleId payload
	 */
	public String getRoleDetails(String instances[][]) {
		String template = "";
		if(instances==null) {
			return template;
		}
		for(int i=0;i<instances.length;i++) {

			template = template + "{"
					+ "               \"Name\":\""+instances[i][0]+"\","
					+ "               \"RoleId\":\""+instances[i][1]+"\""
					+ "            }";

			if(i<instances.length-1) {
				template = template + ",";
			}
		}
		return template;
	}

	/**
	 * This method is used to get VerificationComments JSON
	 * @author pashine_a
	 * @param comments
	 * @return String. It will return comments payload
	 */
	public String getComments(List<String> comments) {
		String template = "";
		if(comments==null) {
			return template;
		}
		for(int i=0;i<comments.size();i++) {

			template = template + "{"
					+ "               \"Comment\":\""+comments.get(i)+"\","
					+ "            }";

			if(i<comments.size()-1) {
				template = template + ",";
			}
		}
		return template;
	}


	/**
	 * This method is used to enable/disable Verification settings
	 * @author pashine_a
	 * @param verificationDetails (Verification details)
	 * @return boolean. It will return true if setting is configured
	 */
	public boolean updateVerificationSettings(VerificationDetails verificationDetails) {
		try {

			String updateVerificationJSON = null;

			ApiUtils apiUtils = new ApiUtils();
			Gson gson = new GsonBuilder().create();

			Map<String, String> headers = new HashMap<String, String>();
			headers.put("Content-Type", "application/json");
			headers.put("Accept", "application/json, text/plain, /");
			headers.put("Authorization", "Bearer " + accessToken);

			updateVerificationJSON = "{"
					+ "  \"Id\": \""+verificationDetails.VerificationID+"\","
					+ "  \"IsEnable\": "+verificationDetails.IsEnable+","
					+ "  \"IsCommentsEnabled\": "+verificationDetails.IsCommentsEnabled+","
					+ "  \"Name\": \""+verificationDetails.VerificationName+"\""
					+ "}";

			Response createVerificationResponse = apiUtils.submitRequest(METHOD_TYPE.POST, updateVerificationJSON, headers,
					AddVerificationUrl);

			SingleDataResponse1 response = gson.fromJson(createVerificationResponse.asString(),
					SingleDataResponse1.class);

			if(!response.Status == true) {
				logError("Failed to update Verification - '"+verificationDetails.VerificationName);
				return false;
			}

			logInfo("Updated verification status for Verification - "+verificationDetails.VerificationName);
			return true;

		} catch (Exception e) {
			logError("Failed to update Verification - '"+verificationDetails.VerificationName+" ' - "+ e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to get ID of the provided Verification
	 * @author pashine_a
	 * @param verificationName 
	 * @return String. It will return the verification Id of the Verification name provided
	 */
	public String getVerification(String verificationName) {
		try {

			String verificationID = null;

			ApiUtils apiUtils = new ApiUtils();
			Gson gson = new GsonBuilder().create();
			Map<String, String> headers = new HashMap<String, String>();
			headers.put("Content-Type", "application/json");
			headers.put("Accept", "application/json, text/plain, /");
			headers.put("Authorization", "Bearer " + accessToken);

			String request = "{\"IsEnable\":false}";

			Response response = apiUtils.submitRequest(METHOD_TYPE.POST,request,headers, GetAllVerificationUrl);

			MultipleDataResponse dataResponse = gson.fromJson(response.asString(), MultipleDataResponse.class);

			for (int i = 0; i < dataResponse.Data.size(); i++) {
				if (dataResponse.Data.get(i).Name.equalsIgnoreCase(verificationName)) {
					verificationID = dataResponse.Data.get(i).Id;
					break;
				}
			}

			logInfo("Verification ID - " + verificationID+" for "+verificationName);
			return verificationID;

		}catch (Exception e) {
			logError("Failed to get verification Id for '"+verificationName+"' - "+ e.getMessage());
			return null;
		}

	}

	/**
	 * This method is used to get all the Verifications details
	 * @author pashine_a
	 * @param verificationID 
	 * @param propertyName
	 * @return String. It will return the verification property status
	 */
	public boolean getVerificationDetail(String verificationID, String propertyName) {
		try {

			boolean propertyValue = false;

			ApiUtils apiUtils = new ApiUtils();
			Gson gson = new GsonBuilder().create();
			Map<String, String> headers = new HashMap<String, String>();
			headers.put("Content-Type", "application/json");
			headers.put("Accept", "application/json, text/plain, /");
			headers.put("Authorization", "Bearer " + accessToken);

			String request = "{\"Id\":\""+verificationID+"\"}";

			Response response = apiUtils.submitRequest(METHOD_TYPE.POST,request,headers, GetVerificationDetailsUrl);

			SimpleDataResponse dataResponse = gson.fromJson(response.asString(), SimpleDataResponse.class);

			if(propertyName.equalsIgnoreCase("IsEnable")) {
				propertyValue = dataResponse.Data.IsEnable;	
			}else {
				propertyValue = dataResponse.Data.IsCommentsEnabled;
			}

			logInfo(propertyName+" Status - "+ propertyValue+" for "+verificationID);
			return propertyValue;
		}catch (Exception e) {
			logError("Failed to get verification details for Verification ID '"+verificationID+"' - "+ e.getMessage());
			return false;
		}

	}


	public static class VerificationDetails {

		public String VerificationName;
		public boolean IsEnable = true;
		public boolean IsCommentsEnabled = false;
		public List<String> comments;
		public String roles[][];
		public String VerificationID;

	}

	public class Data {
		public boolean Status;
		public String Name;
		public String Id;
		public boolean IsEnable;
		public boolean IsCommentsEnabled;

	}

	public class SimpleDataResponse {
		public Data Data;
		public boolean Status;
	}

	public class MultipleDataResponse{
		public List<Data> Data;
	}

	public class SingleDataResponse1 {
		public boolean Status;
		public String Data;
	}
}
