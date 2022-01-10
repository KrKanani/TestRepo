package com.project.safetychain.apiproject.models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.project.safetychain.api.models.Auth;
import com.project.utilities.ApiUtils;
import com.project.utilities.ApiUtils.METHOD_TYPE;

import io.restassured.response.Response;

public class WorkGroups extends Auth {

	public static final String SUPERADMIN_USER_NAME = "SuperAdmin";
	public static final String SUPERADMIN_USER_ID = "acabe4ef-9e50-4a6f-81b8-7e6ff789eb6c";

	public String GetWorkGroupListUrl = baseURI + WorkGroupsURLs.GET_ALL_WORKGROUPS_URL;
	public String GetUsersUrl = baseURI + WorkGroupsURLs.GET_WORKGROUP_USERS_URL;

	public String AddWorkGroupUrl = baseURI + WorkGroupsURLs.SAVE_WORKGROUP_URL;
	public String EnableWorkGroupUrl = baseURI + WorkGroupsURLs.ENABLE_DISABLE_WORKGROUP_URL;
	public String CopyWorkGroupUrl = baseURI + WorkGroupsURLs.COPY_WORKGROUP_URL;

	/**
	 * This method is used to create WorkGroup
	 * @author pashine_a
	 * @param workgroupDetails (WorkGroup configuration details)
	 * @return boolean. It will return boolean result true if new WorkGroup has been added
	 */
	public boolean createWorkGroup(WorkGroupDetails workgroupDetails) {
		try {

			String createWorkGroupJSON = null;

			ApiUtils apiUtils = new ApiUtils();
			Gson gson = new GsonBuilder().create();

			Map<String, String> headers = new HashMap<String, String>();
			headers.put("Content-Type", "application/json");
			headers.put("Accept", "application/json, text/plain, /");
			headers.put("Authorization", "Bearer " + accessToken);

			createWorkGroupJSON = getWorkGroupJSON(workgroupDetails);

			Response createWorkGroupResponse = apiUtils.submitRequest(METHOD_TYPE.POST, createWorkGroupJSON, headers,
					AddWorkGroupUrl);			

			SingleDataResponse response = gson.fromJson(createWorkGroupResponse.asString(),
					SingleDataResponse.class);

			if(!response.Status) {
				logError("Failed to add WorkGroup - "+workgroupDetails.workgroupName);
				return false;
			}

			logInfo("Created WorkGroup - "+workgroupDetails.workgroupName);
			return true;
		} catch (Exception e) {
			logError("Failed to create WorkGroup - '"+workgroupDetails.workgroupName+" ' - "+ e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to create enable/disable WorkGroup
	 * @author pashine_a
	 * @param workgroupID & isEnable
	 * @return boolean. It will return boolean result true if WorkGroup is enabled/disabled
	 */
	public boolean enableWorkGroup(String workgroupID, boolean isEnable) {
		try {

			String enableDisableWorkGroupJSON = null;

			ApiUtils apiUtils = new ApiUtils();
			Gson gson = new GsonBuilder().create();

			Map<String, String> headers = new HashMap<String, String>();
			headers.put("Content-Type", "application/json");
			headers.put("Accept", "application/json, text/plain, /");
			headers.put("Authorization", "Bearer " + accessToken);

			enableDisableWorkGroupJSON = "{"
					+ "    \"Id\": \""+workgroupID+"\","
					+ "    \"IsEnable\": "+isEnable
					+ "}";

			Response enableDisableWorkGroupResponse = apiUtils.submitRequest(METHOD_TYPE.POST, enableDisableWorkGroupJSON, headers,
					EnableWorkGroupUrl);

			SimpleDataResponse response = gson.fromJson(enableDisableWorkGroupResponse.asString(),
					SimpleDataResponse.class);

			if(!response.Status) {
				logError("Failed to enable/disable WorkGroup");
				return false;
			}

			logInfo("Enabled/Disabled WorkGroup");
			return true;
		} catch (Exception e) {
			logError("Failed to enable/disable WorkGroup - "+ e.getMessage());
			return false;
		}
	}


	/**
	 * This method is used to create WorkGroup using Copy API
	 * @author pashine_a
	 * @param workgroupDetails (WorkGroup configuration details)
	 * @return boolean. It will return boolean result true if new WorkGroup has been added
	 */
	public boolean copyWorkGroup(WorkGroupDetails workgroupDetails) {
		try {

			String copyWorkGroupJSON = null;

			ApiUtils apiUtils = new ApiUtils();
			Gson gson = new GsonBuilder().create();

			Map<String, String> headers = new HashMap<String, String>();
			headers.put("Content-Type", "application/json");
			headers.put("Accept", "application/json, text/plain, /");
			headers.put("Authorization", "Bearer " + accessToken);

			copyWorkGroupJSON = getWorkGroupJSON(workgroupDetails);

			Response createWorkGroupResponse = apiUtils.submitRequest(METHOD_TYPE.POST, copyWorkGroupJSON, headers,
					CopyWorkGroupUrl);			

			SingleDataResponse response = gson.fromJson(createWorkGroupResponse.asString(),
					SingleDataResponse.class);

			if(!response.Status) {
				logError("Failed to add WorkGroup(Copy) - "+workgroupDetails.workgroupName);
				return false;
			}

			logInfo("Created WorkGroup(Copy) - "+workgroupDetails.workgroupName);
			return true;
		} catch (Exception e) {
			logError("Failed to create WorkGroup(Copy) - '"+workgroupDetails.workgroupName+" ' - "+ e.getMessage());
			return false;
		}
	}


	/**
	 * This method is used to get WorkGroup details for provided WorkGroup name
	 * @author pashine_a
	 * @param workgroupName
	 * @param proprtyName
	 * @return String. It will return property value for given WorkGroup name & property name(ID/ISENABLE)
	 */
	public String getWorkGroupDetails(String workgroupName, String proprtyName) {
		String workGroupDetail = null;

		ApiUtils apiUtils = new ApiUtils();
		Gson gson = new GsonBuilder().create();

		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("Accept", "application/json, text/plain, /");
		headers.put("Authorization", "Bearer " + accessToken);

		String request = "{\"GetEnabledWorkgroup\":false}";

		Response getWorkGroupResponse = apiUtils.submitRequest(METHOD_TYPE.POST, request, headers,
				GetWorkGroupListUrl);

		MultipleDataResponse dataResponse = gson.fromJson(getWorkGroupResponse.asString(), MultipleDataResponse.class);

		if(!dataResponse.Status) {
			logError("Failed to get workgroup - "+workgroupName);
			return workGroupDetail;
		}

		switch(proprtyName.toUpperCase()) {
		case "ID":
			for (int i = 0; i < dataResponse.Data.size(); i++) {
				if (dataResponse.Data.get(i).Name.equalsIgnoreCase(workgroupName)) {
					workGroupDetail = dataResponse.Data.get(i).Id;
					break;
				}
			}
			break;

		case "ISENABLE":
			for (int i = 0; i < dataResponse.Data.size(); i++) {
				if (dataResponse.Data.get(i).Name.equalsIgnoreCase(workgroupName)) {
					workGroupDetail = Boolean.toString(dataResponse.Data.get(i).IsEnable);
					break;
				}
			}
			break;
		default :

			logError("Property type is incorrect");
			return workGroupDetail;
		}

		logInfo("'"+proprtyName+"' value for Workgroup - "+workgroupName+" is " +workGroupDetail);
		return workGroupDetail;
	}

	/**
	 * This method is used to get user property for provided user name for given WorkGroup
	 * @author pashine_a
	 * @param workgroupName
	 * @param username
	 * @param proprtyName
	 * @return String. It will return provided property value(ID/ISCHECKED) for given username
	 */
	public String getUserDetails(String workgroupName, String username, String proprtyName) {

		String userDetail = null;

		ApiUtils apiUtils = new ApiUtils();
		Gson gson = new GsonBuilder().create();

		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("Accept", "application/json, text/plain, /");
		headers.put("Authorization", "Bearer " + accessToken);

		String workGroupID = getWorkGroupDetails(workgroupName, "Id");

		String request = "{"
				+ "  "
				+ "    \"take\": 1000,"
				+ "    \"Parameters\": {"
				+ "        \"WorkGroupId\": \""+workGroupID+"\""
				+ "    },"
				+ "    \"pageSize\": 1000"
				+ "}";

		Response getWorkGroupUserResponse = apiUtils.submitRequest(METHOD_TYPE.POST, request, headers,
				GetUsersUrl);

		SingleDataResponse dataResponse = gson.fromJson(getWorkGroupUserResponse.asString(), SingleDataResponse.class);

		if(!dataResponse.Status) {
			logError("Failed to find username  - "+username);
			return userDetail;
		}

		switch(proprtyName.toUpperCase()) {
		case "ID":
			for (int i = 0; i < dataResponse.Data.Rows.size(); i++) {
				if (dataResponse.Data.Rows.get(i).UserName.equalsIgnoreCase(username)) {
					userDetail = dataResponse.Data.Rows.get(i).Id;
					break;
				}
			}
			break;

		case "ISCHECKED":
			for (int i = 0; i < dataResponse.Data.Rows.size(); i++) {
				if (dataResponse.Data.Rows.get(i).UserName.equalsIgnoreCase(username)) {
					userDetail = Boolean.toString(dataResponse.Data.Rows.get(i).IsChecked);
					break;
				}
			}
			break;

		default :
			logError("Property type is incorrect");
			return userDetail;
		}

		logInfo("'"+proprtyName+"' value for Workgroup - "+workgroupName+" is " +userDetail);
		return userDetail;

	}

	/**
	 * This method is used to get workgroup template(JSON payload)
	 * @author pashine_a
	 * @param workgroupDetails (WorkGroup configuration details)
	 * @return String. It will return create workgroup payload
	 */
	public String getWorkGroupJSON(WorkGroupDetails workgroupDetails) {
		String workGroupDetails =  "{"
				+ "    \"Id\": null,"
				+ "    \"Name\": \""+workgroupDetails.workgroupName+"\","
				+ "    \"WorkGroupUsers\": ["
				+ geUserIDArray(workgroupDetails.userIDs)
				+ "    ],"
				+ "    \"AddedWorkGroupUsers\": ["
				+ geUserIDArray(workgroupDetails.userIDs)
				+ "    ],"
				+ "    \"RemovedWorkGroupUsers\": []"
				+ "}";
		return workGroupDetails;
	}


	/**
	 * This method is used to get array JSON of userIDs
	 * @author pashine_a
	 * @param userIDs (userIDs)
	 * @return String. It will return userID payload
	 */
	public String geUserIDArray(List<String> userIDs) {
		String template = "";
		if(userIDs==null) {
			return template;
		}
		for(int i=0;i<userIDs.size();i++) {
			template = template + "{"
					+ "               \"Id\":\""+userIDs.get(i)+"\""
					+ "            }";

			if(i<userIDs.size()-1) {
				template = template + ",";
			}
		}
		return template;
	}

	public static class WorkGroupDetails {

		public String workgroupName;
		public List<String> userIDs;

	}
}
