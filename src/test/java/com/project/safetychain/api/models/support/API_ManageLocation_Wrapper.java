package com.project.safetychain.api.models.support;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.project.safetychain.api.models.Auth;
import com.project.safetychain.api.models.Auth.SingleDataResponse;
import com.project.safetychain.api.models.FormDesigner;
import com.project.safetychain.api.models.ManageLocation;
import com.project.safetychain.api.models.ResourceDesigner;
import com.project.safetychain.api.models.support.Support.RESOURCE_TYPES;
import com.project.testbase.TestBase;
import com.project.utilities.ApiUtils;
import com.project.utilities.ApiUtils.METHOD_TYPE;

import io.restassured.response.Response;

public class API_ManageLocation_Wrapper extends TestBase {

	Auth auth = null;
	ApiUtils apiUtils = null;
	ResourceDesigner resourceDesigner = null;
	ManageLocation manageLocation = null;
	FormDesigner formDesigner = null;
	public static String locCatName = null;
	public static String locInstName = null;
	public static String createdLocCatId = null;
	public static String createdCustCatId = null;
	public static String createdLocInstId = null;
	public static String modifiedById = null;
	public static String lastModifiedId = null;
	public static String statusId = null;
	public static String text = "abc", number = "3";


	public API_ManageLocation_Wrapper() {

		manageLocation = new ManageLocation();
		formDesigner = new FormDesigner();

		auth = new Auth();
		auth.setAccessToken();

		resourceDesigner = new ResourceDesigner();
		resourceDesigner.setResourceCatIDs();

	}

	/**
	 * This method is used to create Location resource category
	 * @author hingorani_a
	 * @param locCatName Name of the Location category
	 * @return String Returns a value of ID of the created Location category
	 */
	public String createResourceCategory_Location(String locCatName) {
		try {
			apiUtils = new ApiUtils();
			Map<String, String> headers = new HashMap<String, String>();
			headers.put("Content-Type", "application/json");
			headers.put("Accept", "application/json, text/plain, /");
			headers.put("Authorization", "Bearer " + accessToken);

			String addCatJson = "{\r\n" + "    \"BaseTypeID\": \"" + locationCatID + "\",\r\n" + "    \"Name\": \""
					+ locCatName + "\",\r\n" + "    \"ID\": null,\r\n" + "    \"ResourceTypeID\": \"" + locationCatID
					+ "\"\r\n" + "}";
			Response response = apiUtils.submitRequest(METHOD_TYPE.POST, addCatJson, headers,
					resourceDesigner.AddResourceCatUrl);
			Gson gson = new GsonBuilder().create();
			SingleDataResponse sdr = gson.fromJson(response.asString(), SingleDataResponse.class);
			createdLocCatId = sdr.Data.Id;
			logInfo("Location Cat Id =   " + createdLocCatId);
		}
		catch (Exception e) {
			logError("Failed to create location category " + locCatName +
					e.getMessage());
		}
		return createdLocCatId;
	}
	
	/**
	 * This method is used to create Location resource instance
	 * @author hingorani_a
	 * @param locInstName Name of the Location instance
	 * @return String Returns a value of ID of the created Location instance
	 */
	public String createResourceInstance_Location(String locInstName) {
		try {
			List<String> listOfEnabledFields = new ArrayList<String>();
			listOfEnabledFields = resourceDesigner.createResFieldsJson(RESOURCE_TYPES.LOCATION);
			String fieldDets = resourceDesigner.updateResFieldsJson(listOfEnabledFields, locInstName, number, text);
	
			apiUtils = new ApiUtils();
			Map<String, String> headers = new HashMap<String, String>();
			headers.put("Content-Type", "application/json");
			headers.put("Accept", "application/json, text/plain, /");
			headers.put("Authorization", "Bearer " + accessToken);
	
			String addInstJson = "{\r\n" + "    \"ParentBusinessObjectId\": \"" + createdLocCatId + "\",\r\n"
					+ "    \"Name\": \"" + locInstName + "\",\r\n" + "    \"ResourceTypeId\": \"" + locationCatID
					+ "\",\r\n" + "    \"FieldValues\": [\r\n" + "{\r\n"
					+ "         \"FieldId\":\"422052e7-1b84-4d1c-81f1-950149d90512\",\r\n" + "         \"Value\":null,\r\n"
					+ "         \"Type\":\"Label\",\r\n" + "         \"FieldName\":\"SCSLastModified\"\r\n" + "      },"
					+ "{\r\n" + "         \"FieldId\":\"d7ebfab1-eb2f-48d6-8514-8da3ec2d8a28\",\r\n"
					+ "         \"Value\":null,\r\n" + "         \"Type\":\"Label\",\r\n"
					+ "         \"FieldName\":\"SCSModifiedBy\"\r\n" + "      }," + fieldDets + "    ],\r\n"
					+ "    \"Id\": null,\r\n" + "    \"IsEnabled\": true,\r\n"
					+ "    \"BusinessObjectRefsWithResourceType\": [],\r\n"
					+ "    \"RemovedBusinessObjectRefsWithResourceType\": [],\r\n" + "    \"AddedUsers\": [],\r\n"
					+ "    \"RemovedUsers\": []\r\n" + "}";
			Response response = apiUtils.submitRequest(METHOD_TYPE.POST, addInstJson, headers,
					manageLocation.AddLocationUrl);
			Gson gson = new GsonBuilder().create();
			SingleDataResponse sdr = gson.fromJson(response.asString(), SingleDataResponse.class);
			createdLocInstId = sdr.Data.Id;
		}
		catch (Exception e) {
			logError("Failed to create location instance " + locInstName +
					e.getMessage());
		}
		return createdLocInstId;
	}

	/**
	 * This method is used to create Location resource category and instance, if it does not exist
	 * @author hingorani_a
	 * @param locCatName Name of the Location category
	 * @param locInstName Name of the Location instance
	 * @param username Name of the User to whom you want to link this Location instance with
	 * @return boolean Returns true if Location category and instance is created successfully
	 */
	public boolean prerequisite_Location_CreationWrapper(String locCatName, String locInstName, String username) {
		try {
			String locId = formDesigner.verifylocationCategory(locationCatID, locCatName);
			String locInstId = formDesigner.verifylocationInstance(locationCatID, locId, locInstName);
			
			if(locId!="" && locInstId == "") {
				createdLocCatId = locId;
				createdLocInstId = createResourceInstance_Location(locInstName);
				logInfo("CREATED location '" + locCatName + " > " + locInstName + "'");
			}
			else if (locId.equalsIgnoreCase("") && locInstId.equalsIgnoreCase("")) {
				createdLocCatId = createResourceCategory_Location(locCatName);
				createdLocInstId = createResourceInstance_Location(locInstName);
				logInfo("CREATED location '" + locCatName + " > " + locInstName + "'");
			}
			else {
				logInfo("The location '" + locCatName + " > " + locInstName + "' EXISTS");
			}
			
			return true;
		}
		catch (Exception e) {
			logError("Failed to create/verify existence of location '" + locCatName + " > " + locInstName +
					e.getMessage());
			return false;
		}
	}

}
