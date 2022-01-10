package com.project.safetychain.api.models.support;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.project.safetychain.api.models.Auth;
import com.project.safetychain.api.models.ResourceDesigner;
import com.project.safetychain.api.models.TaskFlow;
import com.project.safetychain.api.models.UsersManagement;
import com.project.safetychain.api.models.Auth.SingleDataResponse;
import com.project.safetychain.api.models.UsersManagement.UM_SingleDataResponse;
import com.project.safetychain.api.models.support.Support.USER_INFO;
import com.project.safetychain.api.models.support.Support.UserParams;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.utilities.ApiUtils;
import com.project.utilities.ApiUtils.METHOD_TYPE;

import io.restassured.response.Response;

public class TCG_UserFlows_Wrapper extends TestBase {

	Auth auth = null;
	ApiUtils apiUtils = null;
	UsersManagement usersManagement = null;
	public static String workGroupId = null;
	ResourceDesigner resourceDesigner = null;
	TaskFlow taskFlow = null;

	public TCG_UserFlows_Wrapper() {
		taskFlow = new TaskFlow();
		usersManagement = new UsersManagement();

		auth = new Auth();
		auth.setAccessToken();

		resourceDesigner = new ResourceDesigner();
		resourceDesigner.setResourceCatIDs();

	}

	public String getUserInfoByUsername(String username, String detailName) {
		String detail = null;
		Gson gson = new GsonBuilder().create();
		apiUtils = new ApiUtils();
		try {

			Map<String, String> headers = new HashMap<String, String>();
			headers.put("Content-Type", "application/json");
			headers.put("Accept", "application/json, text/plain, /");
			headers.put("Authorization", "Bearer " + accessToken);

			String usersJson = "{\r\n" + "	\"filter\": {\r\n" + "		\"filters\": [{\r\n"
					+ "			\"field\": \"Username\",\r\n" + "			\"operator\": \"contains\",\r\n"
					+ "			\"value\": \"" + username + "\"\r\n" + "		}],\r\n"
					+ "		\"logic\": \"and\"\r\n" + "	},\r\n" + "	\"group\": [],\r\n" + "	\"skip\": 0,\r\n"
					+ "	\"sort\": [],\r\n" + "	\"take\": 100,\r\n" + "	\"Parameters\": {\r\n"
					+ "		\"LocationName\": \"\",\r\n" + "		\"SearchString\": null,\r\n"
					+ "		\"Columns\": [\"Status\",\r\n" + "		\"Username\",\r\n" + "		\"FirstName\",\r\n"
					+ "		\"LastName\",\r\n" + "		\"Email\",\r\n" + "		\"TimeZone\"]\r\n" + "	},\r\n"
					+ "	\"pageSize\": 100\r\n" + "}";

			Response response = apiUtils.submitRequest(METHOD_TYPE.POST, usersJson, headers, usersManagement.UsersUrl);
			UM_SingleDataResponse sdr = gson.fromJson(response.asString(), UM_SingleDataResponse.class);
			if (sdr.Data.Count == 1) {
				switch (detailName) {
				case USER_INFO.FIRST_NAME:
					detail = sdr.Data.Rows.get(0).FirstName;
					return detail;
				case USER_INFO.TIMEZONE:
					detail = sdr.Data.Rows.get(0).TimeZone.DisplayName;
					return detail;
				default:
					return detail;
				}
			} else {
				return detail;
			}

		} catch (Exception e) {
			logError("Failed to get " + detailName + " value for " + username + e.getMessage());
			return detail;
		}
	}


	public String create_Link_workGroup_Wrapper(String WorkGroupName,String UserName) throws InterruptedException {
		// Numeric, Free Text, 2 Identifiers
		apiUtils = new ApiUtils();
		Gson gson = new GsonBuilder().create();
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("Accept", "application/json, text/plain, /");
		headers.put("Authorization", "Bearer " + accessToken);

		// 4. Create Work Group
		String createWGFormJson = "{\r\n" + "    \"WorkGroupUsers\": null,\r\n" + "    \"IsEnable\": true,\r\n"
				+ "    \"GetEnabledWorkgroup\": null,\r\n" + "    \"AddedWorkGroupUsers\": null,\r\n"
				+ "    \"RemovedWorkGroupUsers\": null,\r\n" + "    \"IntegerId\": null,\r\n" + "    \"Id\": null,\r\n"
				+ "    \"Name\": \"" + WorkGroupName + "\",\r\n" + "    \"isCreate\": true\r\n" + "}";

		Response validateResponse = apiUtils.submitRequest(METHOD_TYPE.POST, createWGFormJson, headers,
				taskFlow.SaveWorkGroupUrl);
		SingleDataResponse sdr = gson.fromJson(validateResponse.asString(), SingleDataResponse.class);
		workGroupId = sdr.Data.Id;
		logInfo("Work Group Id = " + workGroupId);

		// getUserId

		//	String userId = resourceDesigner.getUserFieldId(UserName);

		// 4. Link WG to User
		//		String linkWGToUsersJson = "{\r\n" + "    \"Id\": \"" + workGroupId + "\",\r\n" + "    \"Name\": \""
		//				+ WorkGroupName + "\",\r\n" + "    \"WorkGroupUsers\": [\r\n" + "        { \"Id\": \"" + userId
		//				+ "\"\r\n" + "        }\r\n" + "    ],\r\n" + "    \"AddedWorkGroupUsers\": [\r\n"
		//				+ "        { \"Id\": \"" + userId + "\"         \r\n" + "        }\r\n" + "    ],\r\n"
		//				+ "    \"RemovedWorkGroupUsers\": []\r\n" + "}";
		//
		//		Response linkWGResponse = apiUtils.submitRequest(METHOD_TYPE.POST, linkWGToUsersJson, headers,
		//				taskFlow.SaveWorkGroupUrl);
		//		SingleDataResponse validateRes = gson.fromJson(linkWGResponse.asString(), SingleDataResponse.class);
		//		TestValidation.IsTrue((validateRes.Status),
		//				"Work Group named " + WorkGroupName + " is Linked with User " + userId,
		//				"Could NOT validate form " + WorkGroupName);
		return workGroupId;
	}




	public void create_User_Wrapper(UserParams up) throws InterruptedException {
		HashMap<String,String> WGMap=new HashMap<String,String>();

		for(int i=0;i<up.WorkGroupNames.size();i++)
		{

			String WGId=create_Link_workGroup_Wrapper(up.WorkGroupNames.get(i),up.Username);
			WGMap.put(up.WorkGroupNames.get(i), WGId);

		}
		boolean createTask = usersManagement.createUser(up, WGMap);

		TestValidation.IsTrue((createTask), "User Has Created with UserName " + up.Username , "Could NOT create User with UserName " + up.Username);
	}

	public void createUser_Wrapper(UserParams up) throws InterruptedException {

		boolean createTask = usersManagement.createUser(up);

		TestValidation.IsTrue((createTask), "User Has Created with UserName " + up.Username , "Could NOT create User with UserName " + up.Username);
	}
	
	public void resetPassword(String username,String password) throws InterruptedException{

		boolean reset = usersManagement.resetPassword(username, password);
		threadsleep(3000);
		TestValidation.IsTrue((reset), "Password reset" , "Could NOT reset password");

	}

}
