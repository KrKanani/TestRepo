package com.project.safetychain.apiproject.models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.project.utilities.ApiUtils;
import com.project.utilities.ApiUtils.METHOD_TYPE;

import io.restassured.response.Response;

public class Inbox extends Auth{

	public String getTasks = baseURI + InboxURLs.GET_TASKS;
	public String approvereject = baseURI + InboxURLs.APPROVE_REJECT;

	ApiUtils apiUtils = new ApiUtils();
	Gson gson = new GsonBuilder().create();
	
	
	/**
	 * This method is used to get Task and Doc Id of a task
	 * @author choubey_a
	 * @param wgId -  Id of the workgroup in whcih task is present
	 * @param taskname - name of the taskname
	 * @return Task and Doc Id of the given task
	 */

	public String getTasks(String wgId,String taskname) {

		String taskId = null;
		String docId = null;

		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("Accept", "application/json, text/plain, /");
		headers.put("Authorization", "Bearer " + accessToken);

		try {
			String request = "{\r\n" + 
					"	\"take\": 100,\r\n" + 
					"	\"skip\": 0,\r\n" + 
					"	\"page\": 1,\r\n" + 
					"	\"pageSize\": 100,\r\n" + 
					"	\"sort\": [\r\n" + 
					"		{\r\n" + 
					"			\"field\": \"DueBy\",\r\n" + 
					"			\"dir\": \"desc\"\r\n" + 
					"		}\r\n" + 
					"	],\r\n" + 
					"	\"Parameters\": {\r\n" + 
					"		\"DueByFromDate\": null,\r\n" + 
					"		\"DueByToDate\": null,\r\n" + 
					"		\"AssignedTo\": [\r\n" + 
					"			\""+wgId+"\"\r\n" + 
					"		],\r\n" + 
					"		\"TaskStatus\": null,\r\n" + 
					"		\"Search\": null,\r\n" + 
					"		\"FirstRowId\": null,\r\n" + 
					"		\"LastRowId\": null,\r\n" + 
					"		\"TaskType\": null\r\n" + 
					"	},\r\n" + 
					"	\"FirstRowId\": null,\r\n" + 
					"	\"LastRowId\": null\r\n" + 
					"}";
			Response response = apiUtils.submitRequest(METHOD_TYPE.POST, request, headers, getTasks);		
			SingleResponseData sdr = gson.fromJson(response.asString(),
					SingleResponseData.class);

			for(int i =0; i <sdr.Data.tasks.size();i++) {
				if(taskname.equals(sdr.Data.tasks.get(i).Name)) {
					taskId = sdr.Data.tasks.get(i).Id;
					docId = sdr.Data.tasks.get(i).DocumentInfo.Id;
				}
			}
			logInfo("Able to get tasks details");
			return taskId+" "+docId;

		} catch (Exception e) {
			logError("Failed to get task details" + e.getMessage());	
			return null;
		}
	}
	
	/**
	 * This method is used to approve/reject task
	 * @author choubey_a
	 * @param details - details of the Task
	 * @param isApproved - boolean regarding the task is accept or reject
	 * @return true if task is accepted/rejected
	 */

	public boolean approveRejectTask(TaskDetails details, boolean isApproved) {

		String request = null;

		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("Accept", "application/json, text/plain, /");
		headers.put("Authorization", "Bearer " + accessToken);

		try {
			if(isApproved) {
				request = "{\"TaskId\":\""+details.TaskId+"\","
						+ "\"MasterTaskId\":\""+details.MasterTaskId+"\","
						+ "\"DocumentId\":\""+details.docTypeId+"\","
						+ "\"IsApproved\":true,\"Type\":\"Document\"}";
			}
			else {
				request = "{\"TaskId\":\""+details.TaskId+"\","
						+ "\"MasterTaskId\":\""+details.MasterTaskId+"\","
						+ "\"DocumentId\":\""+details.docTypeId+"\",\"IsApproved\":false,"
						+ "\"Type\":\"Document\",\"RejectionComment\":\"Task Rejected\"}";
			}

			Response response = apiUtils.submitRequest(METHOD_TYPE.POST, request, headers, approvereject);		
			SimpleResponseData sdr = gson.fromJson(response.asString(),
					SimpleResponseData.class);			

			if(!sdr.Status) {
				return false;
			}
			logInfo("Task Aaccepted/rejected");
			return true;

		} catch (Exception e) {
			logError("Failed to accept/reject task" + e.getMessage());	
			return false;
		}
	}

	public class SingleResponseData{
		public Data Data;
	}

	public class SimpleResponseData{
		public boolean Status;
	}

	public class Data{
		public List<tasks> tasks;
	}

	public class tasks{
		public String Name;
		public String Id;
		public DocumentInfo DocumentInfo;
	}
	
	public class DocumentInfo{
		public String Id;
	}

	public static class TaskDetails{
		public String docTypeId;
		public String TaskId;
		public String MasterTaskId;
	}

}
