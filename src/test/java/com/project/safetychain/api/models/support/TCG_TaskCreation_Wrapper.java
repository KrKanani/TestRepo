package com.project.safetychain.api.models.support;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.project.safetychain.api.models.Auth;
import com.project.safetychain.api.models.Auth.SingleDataResponse;
import com.project.safetychain.api.models.TaskFlow.TaskDetails;
import com.project.safetychain.api.models.FormDesigner;
import com.project.safetychain.api.models.ManageLocation;
import com.project.safetychain.api.models.ManageResources;
import com.project.safetychain.api.models.ResourceDesigner;
import com.project.safetychain.api.models.TaskFlow;
import com.project.safetychain.api.models.support.Support.TaskParams;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.utilities.ApiUtils;
import com.project.utilities.ApiUtils.METHOD_TYPE;
import com.project.utilities.CommonMethods;

import io.restassured.response.Response;

public class TCG_TaskCreation_Wrapper extends TestBase {

	Auth auth = null;
	ApiUtils apiUtils = null;
	TaskFlow taskFlow = null;
	ResourceDesigner resourceDesigner = null;
	ManageLocation manageLocation = null;
	ManageResources manageResources = null;
	FormDesigner formDesigner = null;
	public static String locCatName = null;
	public static String custCatName = null;
	public static String locInstName = null;
	public static String custInstName = null;
	public static String createdLocCatId = null;
	public static String createdCustCatId = null;
	public static String createdLocInstId = null;
	public static String createdInstId = null;
	public static String createdFormId = null;
	public static String formName = null;
	public static String nameId = null;
	public static String modifiedById = null;
	public static String lastModifiedId = null;
	public static String statusId = null;
	public static String workGroupId = null;
	public static String text = "abc", number = "3";
	private List<String> userId;

	public TCG_TaskCreation_Wrapper() {

		manageLocation = new ManageLocation();
		manageResources = new ManageResources();
		formDesigner = new FormDesigner();

		taskFlow = new TaskFlow();
		locCatName = CommonMethods.dynamicText("API Loc_Cat");
		custCatName = CommonMethods.dynamicText("API Cust_Cat");
		locInstName = CommonMethods.dynamicText("API Loc_Inst");
		custInstName = CommonMethods.dynamicText("API Cust_Inst");
		formName = CommonMethods.dynamicText("API Chk");

		auth = new Auth();
		auth.setAccessToken();

		resourceDesigner = new ResourceDesigner();
		resourceDesigner.setResourceCatIDs();
		//
		//		GetResourceCreationInputFieldIds();

	}

	// @Test(description = "Create WorkGroup ")
	public String create_Link_workGroup_Wrapper(TaskParams fp) throws InterruptedException {
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
				+ "    \"Name\": \"" + fp.WorkGroupName + "\",\r\n" + "    \"isCreate\": true\r\n" + "}";

		Response validateResponse = apiUtils.submitRequest(METHOD_TYPE.POST, createWGFormJson, headers,
				taskFlow.SaveWorkGroupUrl);
		SingleDataResponse sdr = gson.fromJson(validateResponse.asString(), SingleDataResponse.class);
		workGroupId = sdr.Data.Id;
		logInfo("Work Group Id = " + workGroupId);

		// getUserId

		String userId = resourceDesigner.getUserFieldId(fp.UserName);

		// 4. Link WG to User
		String linkWGToUsersJson = "{\r\n" + "    \"Id\": \"" + workGroupId + "\",\r\n" + "    \"Name\": \""
				+ fp.WorkGroupName + "\",\r\n" + "    \"WorkGroupUsers\": [\r\n" + "        { \"Id\": \"" + userId
				+ "\"\r\n" + "        }\r\n" + "    ],\r\n" + "    \"AddedWorkGroupUsers\": [\r\n"
				+ "        { \"Id\": \"" + userId + "\"         \r\n" + "        }\r\n" + "    ],\r\n"
				+ "    \"RemovedWorkGroupUsers\": []\r\n" + "}";

		Response linkWGResponse = apiUtils.submitRequest(METHOD_TYPE.POST, linkWGToUsersJson, headers,
				taskFlow.SaveWorkGroupUrl);
		SingleDataResponse validateRes = gson.fromJson(linkWGResponse.asString(), SingleDataResponse.class);
		TestValidation.IsTrue((validateRes.Status),
				"Work Group named " + fp.WorkGroupName + " is Linked with User " + userId,
				"Could NOT validate form " + fp.WorkGroupName);

		// logInfo("WorkGroup Linked with User = ");
		return workGroupId;

	}

	public String create_WorkGroup(String WorkGroupName) throws InterruptedException {
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

		return workGroupId;
	}

	public List<String> link_WG_User(List<String> WorkGroupName, List<String> UserName) throws InterruptedException {
		// Numeric, Free Text, 2 Identifiers
		apiUtils = new ApiUtils();
		Gson gson = new GsonBuilder().create();
		resourceDesigner=new ResourceDesigner();
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("Accept", "application/json, text/plain, /");
		headers.put("Authorization", "Bearer " + accessToken);
		List<String> userIds = new ArrayList<String>();
		HashMap<String, String> WGUsers = new HashMap<String, String>();
		String linkWGToUsersJson = "";

		for (int i = 0; i < UserName.size(); i++) {
			String userId= resourceDesigner.getUserFieldId(UserName.get(i));

			userIds.add(userId);
		}

		List<String> wgIDs = new ArrayList<String>();
		for (int i = 0; i < WorkGroupName.size(); i++) {
			String WGId = create_WorkGroup(WorkGroupName.get(i));
			wgIDs.add(WGId);
			WGUsers.put(WorkGroupName.get(i), WGId);
			// WGId.add(create_WorkGroup(WorkGroupName.get(i)));
		}
		logInfo("User Ids = " + userIds + "Work Group Ids = " + WGUsers);

		// 4. Link WG to User

		for (Map.Entry<String, String> entry : WGUsers.entrySet()) {
			String WGName = entry.getKey();
			String WGId = entry.getValue();
			// fields.get("");
			logInfo("WorkGroup Name "+WGName);

			for (int i = 0; i < userIds.size(); i++) {
				logInfo("Inner For loop "+userIds.size());

				linkWGToUsersJson = "{\r\n" + "    \"Id\": \"" + WGId + "\",\r\n" + "    \"Name\": \""
						+ WGName + "\",\r\n" + "    \"WorkGroupUsers\": [\r\n" + "        { \"Id\": \""
						+ userIds.get(i) + "\"\r\n" + "        }\r\n" + "    ],\r\n"
						+ "    \"AddedWorkGroupUsers\": [\r\n" + "        { \"Id\": \"" + userIds.get(i)
						+ "\"         \r\n" + "        }\r\n" + "    ],\r\n" + "    \"RemovedWorkGroupUsers\": []\r\n"
						+ "}";

				Response linkWGResponse = apiUtils.submitRequest(METHOD_TYPE.POST, linkWGToUsersJson, headers,
						taskFlow.SaveWorkGroupUrl);
				SingleDataResponse validateRes = gson.fromJson(linkWGResponse.asString(), SingleDataResponse.class);
				TestValidation.IsTrue((validateRes.Status),
						"Work Group named " + WorkGroupName + " is Linked with User " + userIds,
						"Could NOT validate form " + WorkGroupName);


			}
		}

		return wgIDs;
		// logInfo("WorkGroup Linked with User = ");

	}


	public void Unlink_WG_User(List<String> WorkGroupName, List<String> UserName) throws InterruptedException {
		// Numeric, Free Text, 2 Identifiers
		apiUtils = new ApiUtils();
		Gson gson = new GsonBuilder().create();
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("Accept", "application/json, text/plain, /");
		headers.put("Authorization", "Bearer " + accessToken);
		userId = null;
		HashMap<String, String> WGUsers = new HashMap<String, String>();
		String linkWGToUsersJson = null;

		for (int i = 0; i < UserName.size(); i++) {
			userId.add(resourceDesigner.getUserFieldId(UserName.get(i)));
		}

		for (int i = 0; i < WorkGroupName.size(); i++) {
			String WGId = create_WorkGroup(WorkGroupName.get(i));
			WGUsers.put(WorkGroupName.get(i), WGId);
			// WGId.add(create_WorkGroup(WorkGroupName.get(i)));
		}
		logInfo("User Ids = " + userId + "Work Group Ids = " + WGUsers);

		// 4. Link WG to User

		for (Map.Entry<String, String> entry : WGUsers.entrySet()) {
			String WGName = entry.getKey();
			String WGId = entry.getValue();
			// fields.get("");

			for (int i = 0; i < userId.size(); i++) {

				linkWGToUsersJson = "{\r\n" + "    \"Id\": \"" + WGId + "\",\r\n" + "    \"Name\": \""
						+ WGName + "\",\r\n" + "    \"WorkGroupUsers\": [\r\n" + "        { \"Id\": \""
						+ userId.get(i) + "\"\r\n" + "        }\r\n" + "    ],\r\n"
						+ "    \"AddedWorkGroupUsers\": [],\r\n" + "    \"RemovedWorkGroupUsers\": [\r\n" + "{ \"Id\": \"" + userId.get(i)
						+ "\"         \r\n" + "        }\r\n" + " ]\r\n"
						+ "}";

			}
		}

		Response linkWGResponse = apiUtils.submitRequest(METHOD_TYPE.POST, linkWGToUsersJson, headers,
				taskFlow.SaveWorkGroupUrl);
		SingleDataResponse validateRes = gson.fromJson(linkWGResponse.asString(), SingleDataResponse.class);
		TestValidation.IsTrue((validateRes.Status),
				"Work Group named " + WorkGroupName + " is Linked with User " + userId,
				"Could NOT validate form " + WorkGroupName);

		// logInfo("WorkGroup Linked with User = ");

	}

	//	@Test(description = "Create Task")
	public void create_Task_Wrapper(TaskParams fp) throws InterruptedException {
		boolean createTask;
		if(fp.WorkGroupID!=null) {
			createTask = taskFlow.createTask(fp, fp.WorkGroupID);
		}else {
			createTask = taskFlow.createTask(fp, workGroupId);
		}

		TestValidation.IsTrue((createTask), "Task Has Created with Name " + fp.TaskName + " WorkGroupName "
				+ fp.WorkGroupName + "Due By = " + fp.DueBy, "Could NOT create Task " + fp.TaskName);
	}

	// @Test(description = "Create Task")
	public void create_Task_TaskScheduler_Wrapper(TaskParams fp) throws InterruptedException {
		boolean createTask = taskFlow.createSchedulerTask(fp, workGroupId);

		TestValidation.IsTrue((createTask), "Task Has Created with Name " + fp.TaskName + " WorkGroupName "
				+ fp.WorkGroupName + "Due By = " + fp.DueBy, "Could NOT create Task " + fp.TaskName);
	}

	//@Test(description = "Create Task Id")
	public String create_TaskId_Wrapper(TaskParams fp, String workgroupId) throws InterruptedException {
		String taskId = taskFlow.createTaskId(fp, workgroupId);
		return taskId;
	}

	// @Test(description = "Recall Task)
	public void recallTask_Wrapper(String taskId) throws InterruptedException {
		boolean recall = taskFlow.recallTask(taskId);	
		TestValidation.IsTrue((recall), "Task is recalled "
				+ "Could NOT recall Task", taskId);
	}

	// @Test(description = "Reassign Task)
	public String reassignTask_Wrapper(TaskDetails tsd) throws InterruptedException {
		String newWgId = null;
		if(tsd.ChangedWorkgroupId!=null) {
			newWgId = tsd.ChangedWorkgroupId;
		}else {
			newWgId = create_WorkGroup(tsd.ChangedWorkGroupName);	
		}
		boolean reassign = taskFlow.reassignTask(tsd,newWgId);	
		Thread.sleep(3000);
		TestValidation.IsTrue((reassign), "Task is reassigned "
				+ "Could NOT reassign Task", tsd.TaskName);
		return newWgId;
	}

	public void Unlink_WGUser(String WorkGroupName,String WorkgroupID, String Username) throws InterruptedException {

		apiUtils = new ApiUtils();
		Gson gson = new GsonBuilder().create();
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("Accept", "application/json, text/plain, /");
		headers.put("Authorization", "Bearer " + accessToken);
		String unlinkWGToUsersJson = null;

		String UserId = resourceDesigner.getUserFieldId(Username);

		unlinkWGToUsersJson = "{\"Id\":\""+WorkgroupID+"\","
				+ "\"Name\":\""+WorkGroupName+"\",\"WorkGroupUsers\":[],\"AddedWorkGroupUsers\":[],"
				+ "\"RemovedWorkGroupUsers\":[{\"Id\":\""+UserId+"\"}]}";

		Response unlinkWGResponse = apiUtils.submitRequest(METHOD_TYPE.POST, unlinkWGToUsersJson, headers,
				taskFlow.SaveWorkGroupUrl);
		SingleDataResponse validateRes = gson.fromJson(unlinkWGResponse.asString(), SingleDataResponse.class);
		TestValidation.IsTrue((validateRes.Status),
				"Work Group named " + WorkGroupName + " is unlinked with User " + UserId,
				"Could NOT validate form " + WorkGroupName);

	}

}
