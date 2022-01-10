package com.project.safetychain.apiproject.models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.project.utilities.ApiUtils;
import com.project.utilities.ApiUtils.METHOD_TYPE;

import io.restassured.response.Response;

public class TaskScheduler extends Auth{

	public String GetSchedules = baseURI + TaskSchedulerURLs.GET_SCHEDULES;
	public String CreateSchedule = baseURI + TaskSchedulerURLs.CREATE_SCHEDULE;
	public String GetScheduleTask = baseURI + TaskSchedulerURLs.GET_SCHEDULE_TASKS;
	public String GetFormsByLocation = baseURI + TaskSchedulerURLs.GET_FORMS_BY_LOCATION;

	ApiUtils apiUtils = new ApiUtils();
	Gson gson = new GsonBuilder().create();


	/**
	 * This method is used to get all the roles details
	 * @author choubey_a
	 * @param role name 
	 * @return String. It will return the role Id of the role name provided in parameters
	 */

	public boolean getSchedule(String schedulename, String locationId, String startdate, String enddate) {
		try {
			apiUtils = new ApiUtils();
			Gson gson = new GsonBuilder().create();
			Map<String, String> headers = new HashMap<String, String>();
			headers.put("Content-Type", "application/json");
			headers.put("Accept", "application/json, text/plain, /");
			headers.put("Authorization", "Bearer " + accessToken);

			String request = "{\"take\":100,\"skip\":0,\"page\":1,\"pageSize\":100,"
					+ "\"Parameters\":{\"LocationId\":\""+locationId+"\","
					+ "\"StartDate\":\""+startdate+"\",\"EndDate\":\""+enddate+"\",\"FirstRowId\":null,"
					+ "\"LastRowId\":null},\"FirstRowId\":null,\"LastRowId\":null}";

			Response response = apiUtils.submitRequest(METHOD_TYPE.POST,request,headers, GetSchedules);

			SingleDataResponse sdr = gson.fromJson(response.asString(), SingleDataResponse.class);

			for (int i = 0; i < sdr.Data.Rows.size(); i++) {
				if (sdr.Data.Rows.get(i).Name.equals(schedulename)) {	
					logInfo("Schedule created");
					break;
				}
			}
			return true;
		}catch (Exception e) {			
			logError("Failed to verify schedule - '"+schedulename+"' - "+ e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to create One Time Schedule
	 * @author choubey_a
	 * @param details  
	 * @return one time schedule Id
	 */

	public String createOneTimeSchedule(ScheduleDetails details) {
		try {
			apiUtils = new ApiUtils();
			Gson gson = new GsonBuilder().create();
			Map<String, String> headers = new HashMap<String, String>();
			headers.put("Content-Type", "application/json");
			headers.put("Accept", "application/json, text/plain, /");
			headers.put("Authorization", "Bearer " + accessToken);

			String request = "{\r\n" + 
					"   \"Template\":{\r\n" + 
					"      \"TaskName\":\""+details.TaskName+"\",\r\n" + 
					"      \"TaskType\":\"Form\",\r\n" + 
					"      \"FormEntityId\":\""+details.FormEntityId+"\",\r\n" + 
					"      \"DocumentId\":null,\r\n" + 
					"      \"LocationId\":\""+details.LocationId+"\",\r\n" + 
					"      \"TaskExpiration\":{\r\n" + 
					"         \"ExpirationIntervalId\":\"7eabf2a0-07fc-4ce7-a251-044ee40d2d53\",\r\n" + 
					"         \"ExpirationIntervalType\":\"Never\"\r\n" + 
					"      }\r\n" + 
					"   },\r\n" + 
					"   \"Schedule\":{\r\n" + 
					"      \"FirstDueDate\":\""+details.FirstDueDate+"\",\r\n" + 
					"      \"ResourceId\":\""+details.ResourceId+"\",\r\n" + 
					"      \"AssignedToWorkGroupId\":\""+details.WorkGroupId+"\",\r\n" + 
					"      \"AssignTimeBeforeTaskIsDue\":{\r\n" + 
					"         \"Months\":0,\r\n" + 
					"         \"Weeks\":0,\r\n" + 
					"         \"Days\":0,\r\n" + 
					"         \"Hours\":0,\r\n" + 
					"         \"Minutes\":0\r\n" + 
					"      },\r\n" + 
					"      \"TimeZone\":{\r\n" + 
					"         \"Id\":\"Asia/Kolkata\",\r\n" + 
					"         \"DisplayName\":\"Republic of India\"\r\n" + 
					"      },\r\n" + 
					"      \"DayOfMonthTaskOccures\":{\r\n" + 
					"         \r\n" + 
					"      },\r\n" + 
					"      \"DaysOfWeek\":[\r\n" + 
					"         \r\n" + 
					"      ],\r\n" + 
					"      \"IsResourceRandomize\":false\r\n" + 
					"   },\r\n" + 
					"   \"ParentSchedule\":{\r\n" + 
					"      \"Occurrence\":\"OneTimeOnly\",\r\n" + 
					"      \"Repetition\":1\r\n" + 
					"   }\r\n" + 
					"}";

			Response response = apiUtils.submitRequest(METHOD_TYPE.POST,request,headers, CreateSchedule);

			SimpleDataResponse sdr = gson.fromJson(response.asString(), SimpleDataResponse.class);

			String ScheduleId = sdr.Data;			
			logInfo("One Time Schedule Created");
			return ScheduleId;
		}catch (Exception e) {			
			logError("Failed to create one time schedule - "+ e.getMessage());
			return null;
		}
	}

	public String createIntervalSchedule (ScheduleDetails details) {
		try {
			apiUtils = new ApiUtils();
			Gson gson = new GsonBuilder().create();
			Map<String, String> headers = new HashMap<String, String>();
			headers.put("Content-Type", "application/json");
			headers.put("Accept", "application/json, text/plain, /");
			headers.put("Authorization", "Bearer " + accessToken);
			String request = "{\r\n" + 
					"    \"ParentSchedule\": {\r\n" + 
					"        \"Occurrence\": \"Interval\",\r\n" + 
					"        \"Repetition\": 1\r\n" + 
					"    },\r\n" + 
					"    \"Schedule\": {\r\n" + 
					"        \"AssignedToWorkGroupId\": \""+details.WorkGroupId+"\",\r\n" + 
					"        \"AssignTimeBeforeTaskIsDue\": {\r\n" + 
					"            \"Days\": 0,\r\n" + 
					"            \"Hours\": 0,\r\n" + 
					"            \"Minutes\": 0,\r\n" + 
					"            \"Months\": 0,\r\n" + 
					"            \"Weeks\": 0\r\n" + 
					"        },\r\n" + 
					"        \"DayOfMonthTaskOccures\": {\r\n" + 
					"        },\r\n" + 
					"        \"DaysOfWeek\": [\r\n" + 
					"            \"Monday\",\r\n" + 
					"            \"Tuesday\",\r\n" + 
					"            \"Wednesday\",\r\n" + 
					"            \"Thursday\",\r\n" + 
					"            \"Friday\"\r\n" + 
					"        ],\r\n" + 
					"        \"Interval\": \"15\",\r\n" + 
					"        \"IntervalEndDate\": \""+details.EndDate+"\",\r\n" + 
					"        \"IntervalEndTime\": \""+details.EndTime+"\",\r\n" + 
					"        \"IntervalId\": \"9c1c8a8d-0804-40de-a9c9-6691734a0c38\",\r\n" + 
					"        \"IntervalStartDate\": \""+details.StartDate+"\",\r\n" + 
					"        \"IntervalStartTime\": \""+details.StartTime+"\",\r\n" + 
					"        \"IntervalUnit\": \"Minute\",\r\n" + 
					"        \"IsCarryOver\": false,\r\n" + 
					"        \"IsRandom\": false,\r\n" + 
					"        \"IsResourceRandomize\": false,\r\n" + 
					"        \"IsSchedulePreRandomize\": true,\r\n" + 
					"        \"ResourceId\": \""+details.ResourceId+"\",\r\n" + 
					"        \"TimeZone\": {\r\n" + 
					"            \"DisplayName\": \"Republic of India\",\r\n" + 
					"            \"Id\": \"Asia/Kolkata\"\r\n" + 
					"        }\r\n" + 
					"    },\r\n" + 
					"    \"Template\": {\r\n" + 
					"        \"DocumentId\": null,\r\n" + 
					"        \"FormEntityId\": \""+details.FormEntityId+"\",\r\n" + 
					"        \"LocationId\": \""+details.LocationId+"\",\r\n" + 
					"        \"TaskExpiration\": {\r\n" + 
					"            \"ExpirationIntervalId\": \"7eabf2a0-07fc-4ce7-a251-044ee40d2d53\",\r\n" + 
					"            \"ExpirationIntervalType\": \"Never\",\r\n" + 
					"            \"ExpirationIntervalValue\": null\r\n" + 
					"        },\r\n" + 
					"        \"TaskName\": \""+details.TaskName+"\",\r\n" + 
					"        \"TaskType\": \"Form\"\r\n" + 
					"    }\r\n" + 
					"}";

			Response response = apiUtils.submitRequest(METHOD_TYPE.POST,request,headers, CreateSchedule);

			SimpleDataResponse sdr = gson.fromJson(response.asString(), SimpleDataResponse.class);

			String ScheduleId = sdr.Data;			
			logInfo("Interval Schedule Created");
			return ScheduleId;
		}catch (Exception e) {			
			logError("Failed to create interval schedule - "+ e.getMessage());
			return null;
		}
	}

	public boolean getScheduleTask (String scheduleId, String locationId, String startdate, String enddate) {
		try {

			apiUtils = new ApiUtils();
			Gson gson = new GsonBuilder().create();
			Map<String, String> headers = new HashMap<String, String>();
			headers.put("Content-Type", "application/json");
			headers.put("Accept", "application/json, text/plain, /");
			headers.put("Authorization", "Bearer " + accessToken);
			String request = "{\"take\":20,\"skip\":0,\"page\":1,\"pageSize\":20,"
					+ "\"Parameters\":{\"FromDate\":\""+startdate+"\",\"ToDate\":\""+enddate+"\","
					+ "\"LocationId\":\""+locationId+"\",\"FirstRowId\":null,\"LastRowId\":null},"
					+ "\"FirstRowId\":null,\"LastRowId\":null}";

			Response response = apiUtils.submitRequest(METHOD_TYPE.POST,request,headers, GetScheduleTask);

			SingleDataResponse sdr = gson.fromJson(response.asString(), SingleDataResponse.class);

			int i = 0;
			if (sdr.Data.Rows.get(i).ParentScheduleId.equals(scheduleId) && sdr.Data.Rows.get(i).Status.equals("PastDue")) {
				logInfo("Able to verify schedule task assignement");
				return true;
			}
			else {
				i++;
			}
			return true;
		}catch(Exception e) {
			logInfo("Not able to verify schedule task assignement -"+e.getMessage());
			return false;
		}
	}

	public String getFormsbyLocation (String locationId, String formname) {

		String formEntityId = null;
		try {

			apiUtils = new ApiUtils();
			Gson gson = new GsonBuilder().create();
			Map<String, String> headers = new HashMap<String, String>();
			headers.put("Content-Type", "application/json");
			headers.put("Accept", "application/json, text/plain, /");
			headers.put("Authorization", "Bearer " + accessToken);
			String request = "{\"LocationId\":\""+locationId+"\"}";

			Response response = apiUtils.submitRequest(METHOD_TYPE.POST,request,headers, GetFormsByLocation);

			MultipleDataResponse mdr = gson.fromJson(response.asString(), MultipleDataResponse.class);

			for (int i = 0; i < mdr.Data.size(); i++) {
				if (mdr.Data.get(i).Name.equals(formname)) {	
					formEntityId = mdr.Data.get(i).Id;
				}
			}
			return formEntityId;
		}catch(Exception e) {
			logInfo("Not able to get form entity Id -"+e.getMessage());
			return null;
		}
	}

	/**
	 * This method is used to create Daily Schedule
	 * @author choubey_a
	 * @param details  
	 * @return one time schedule Id
	 */

	public String createDailySchedule(ScheduleDetails details) {
		try {
			apiUtils = new ApiUtils();
			Gson gson = new GsonBuilder().create();
			Map<String, String> headers = new HashMap<String, String>();
			headers.put("Content-Type", "application/json");
			headers.put("Accept", "application/json, text/plain, /");
			headers.put("Authorization", "Bearer " + accessToken);

			String request = "{\r\n" + 
					"   \"Template\":{\r\n" + 
					"      \"TaskName\":\""+details.TaskName+"\",\r\n" + 
					"      \"TaskType\":\"Form\",\r\n" + 
					"      \"FormEntityId\":\""+details.FormEntityId+"\",\r\n" + 
					"      \"DocumentId\":null,\r\n" + 
					"      \"LocationId\":\""+details.LocationId+"\",\r\n" + 
					"      \"TaskExpiration\":{\r\n" + 
					"         \"ExpirationIntervalId\":\"7eabf2a0-07fc-4ce7-a251-044ee40d2d53\",\r\n" + 
					"         \"ExpirationIntervalValue\":null,\r\n" + 
					"         \"ExpirationIntervalType\":\"Never\"\r\n" + 
					"      }\r\n" + 
					"   },\r\n" + 
					"   \"Schedule\":{\r\n" + 
					"      \"FirstDueDate\":\""+details.StartDate+"\",\r\n" + 
					"      \"TaskDueTime\":\""+details.StartTime+"\",\r\n" + 
					"      \"ResourceId\":\""+details.ResourceId+"\",\r\n" + 
					"      \"AssignedToWorkGroupId\":\""+details.WorkGroupId+"\",\r\n" + 
					"      \"TaskEndDate\":\""+details.EndDate+"\",\r\n" + 
					"      \"AssignTimeBeforeTaskIsDue\":{\r\n" + 
					"         \"Months\":0,\r\n" + 
					"         \"Weeks\":0,\r\n" + 
					"         \"Days\":0,\r\n" + 
					"         \"Hours\":0,\r\n" + 
					"         \"Minutes\":0\r\n" + 
					"      },\r\n" + 
					"      \"TimeZone\":{\r\n" + 
					"         \"Id\":\"Asia/Kolkata\",\r\n" + 
					"         \"DisplayName\":\"Republic of India\"\r\n" + 
					"      },\r\n" + 
					"      \"DayOfMonthTaskOccures\":{\r\n" + 
					"         \r\n" + 
					"      },\r\n" + 
					"      \"DaysOfWeek\":[\r\n" + 
					"         \r\n" + 
					"      ],\r\n" + 
					"      \"IsRandom\":false,\r\n" + 
					"      \"IsCarryOver\":false,\r\n" + 
					"      \"IsSchedulePreRandomize\":false,\r\n" + 
					"      \"IsResourceRandomize\":false\r\n" + 
					"   },\r\n" + 
					"   \"ParentSchedule\":{\r\n" + 
					"      \"Occurrence\":\"Daily\",\r\n" + 
					"      \"Repetition\":1\r\n" + 
					"   }\r\n" + 
					"}";

			Response response = apiUtils.submitRequest(METHOD_TYPE.POST,request,headers, CreateSchedule);

			SimpleDataResponse sdr = gson.fromJson(response.asString(), SimpleDataResponse.class);

			String ScheduleId = sdr.Data;			
			logInfo("Daily Schedule Created");
			return ScheduleId;
		}catch (Exception e) {			
			logError("Failed to create daily schedule - "+ e.getMessage());
			return null;
		}
	}

	/**
	 * This method is used to create Weekly Schedule
	 * @author choubey_a
	 * @param details  
	 * @return one time schedule Id
	 */

	public String createWeeklySchedule(ScheduleDetails details) {
		try {
			apiUtils = new ApiUtils();
			Gson gson = new GsonBuilder().create();
			Map<String, String> headers = new HashMap<String, String>();
			headers.put("Content-Type", "application/json");
			headers.put("Accept", "application/json, text/plain, /");
			headers.put("Authorization", "Bearer " + accessToken);

			String request = "{\r\n" + 
					"   \"Template\":{\r\n" + 
					"      \"TaskName\":\""+details.TaskName+"\",\r\n" + 
					"      \"TaskType\":\"Form\",\r\n" + 
					"      \"FormEntityId\":\""+details.FormEntityId+"\",\r\n" + 
					"      \"DocumentId\":null,\r\n" + 
					"      \"LocationId\":\""+details.LocationId+"\",\r\n" + 
					"      \"TaskExpiration\":{\r\n" + 
					"         \"ExpirationIntervalId\":\"7eabf2a0-07fc-4ce7-a251-044ee40d2d53\",\r\n" + 
					"         \"ExpirationIntervalValue\":null,\r\n" + 
					"         \"ExpirationIntervalType\":\"Never\"\r\n" + 
					"      }\r\n" + 
					"   },\r\n" + 
					"   \"Schedule\":{\r\n" + 
					"      \"FirstDueDate\":\""+details.FirstDueDate+"\",\r\n" + 
					"      \"TaskDueTime\":\""+details.StartTime+"\",\r\n" + 
					"      \"ResourceId\":\""+details.ResourceId+"\",\r\n" + 
					"      \"AssignedToWorkGroupId\":\""+details.WorkGroupId+"\",\r\n" + 
					"      \"TaskEndDate\":\""+details.EndDate+"\",\r\n" + 
					"      \"AssignTimeBeforeTaskIsDue\":{\r\n" + 
					"         \"Months\":0,\r\n" + 
					"         \"Weeks\":0,\r\n" + 
					"         \"Days\":0,\r\n" + 
					"         \"Hours\":0,\r\n" + 
					"         \"Minutes\":0\r\n" + 
					"      },\r\n" + 
					"      \"TimeZone\":{\r\n" + 
					"         \"Id\":\"Asia/Kolkata\",\r\n" + 
					"         \"DisplayName\":\"Republic of India\"\r\n" + 
					"      },\r\n" + 
					"      \"DayOfMonthTaskOccures\":{\r\n" + 
					"         \r\n" + 
					"      },\r\n" + 
					"      \"DaysOfWeek\":[\r\n" + 
					"         \"Sunday\",\r\n" + 
					"         \"Monday\",\r\n" + 
					"         \"Tuesday\",\r\n" + 
					"         \"Wednesday\",\r\n" + 
					"         \"Thursday\",\r\n" + 
					"         \"Friday\",\r\n" + 
					"         \"Saturday\"\r\n" + 
					"      ],\r\n" + 
					"      \"IsRandom\":false,\r\n" + 
					"      \"IsCarryOver\":false,\r\n" + 
					"      \"IsSchedulePreRandomize\":false,\r\n" + 
					"      \"IsResourceRandomize\":false\r\n" + 
					"   },\r\n" + 
					"   \"ParentSchedule\":{\r\n" + 
					"      \"Occurrence\":\"Weekly\",\r\n" + 
					"      \"Repetition\":1\r\n" + 
					"   }\r\n" + 
					"}";

			Response response = apiUtils.submitRequest(METHOD_TYPE.POST,request,headers, CreateSchedule);

			SimpleDataResponse sdr = gson.fromJson(response.asString(), SimpleDataResponse.class);

			String ScheduleId = sdr.Data;			
			logInfo("Weekly Schedule Created");
			return ScheduleId;
		}catch (Exception e) {			
			logError("Failed to create weekly schedule - "+ e.getMessage());
			return null;
		}
	}

	/**
	 * This method is used to create Monthly Schedule
	 * @author choubey_a
	 * @param details  
	 * @return one time schedule Id
	 */

	public String createMonthlySchedule(ScheduleDetails details) {
		try {
			apiUtils = new ApiUtils();
			Gson gson = new GsonBuilder().create();
			Map<String, String> headers = new HashMap<String, String>();
			headers.put("Content-Type", "application/json");
			headers.put("Accept", "application/json, text/plain, /");
			headers.put("Authorization", "Bearer " + accessToken);

			String request = "{\r\n" + 
					"   \"Template\":{\r\n" + 
					"      \"TaskName\":\"Monthly\",\r\n" + 
					"      \"TaskType\":\"Form\",\r\n" + 
					"      \"FormEntityId\":\""+details.FormEntityId+"\",\r\n" + 
					"      \"DocumentId\":null,\r\n" + 
					"      \"LocationId\":\""+details.LocationId+"\",\r\n" + 
					"      \"TaskExpiration\":{\r\n" + 
					"         \"ExpirationIntervalId\":\"7eabf2a0-07fc-4ce7-a251-044ee40d2d53\",\r\n" + 
					"         \"ExpirationIntervalValue\":null,\r\n" + 
					"         \"ExpirationIntervalType\":\"Never\"\r\n" + 
					"      }\r\n" + 
					"   },\r\n" + 
					"   \"Schedule\":{\r\n" + 
					"      \"FirstDueDate\":\""+details.FirstDueDate+"\",\r\n" + 
					"      \"TaskDueTime\":\""+details.StartTime+"\",\r\n" + 
					"      \"ResourceId\":\""+details.ResourceId+"\",\r\n" + 
					"      \"AssignedToWorkGroupId\":\""+details.WorkGroupId+"\",\r\n" + 
					"      \"TaskEndDate\":\""+details.EndDate+"\",\r\n" + 
					"      \"AssignTimeBeforeTaskIsDue\":{\r\n" + 
					"         \"Months\":0,\r\n" + 
					"         \"Weeks\":0,\r\n" + 
					"         \"Days\":0,\r\n" + 
					"         \"Hours\":0,\r\n" + 
					"         \"Minutes\":0\r\n" + 
					"      },\r\n" + 
					"      \"TimeZone\":{\r\n" + 
					"         \"Id\":\"Asia/Kolkata\",\r\n" + 
					"         \"DisplayName\":\"Republic of India\"\r\n" + 
					"      },\r\n" + 
					"      \"DayOfMonthTaskOccures\":{\r\n" + 
					"         \r\n" + 
					"      },\r\n" + 
					"      \"DaysOfWeek\":[\r\n" + 
					"         \r\n" + 
					"      ],\r\n" + 
					"      \"IsRandom\":false,\r\n" + 
					"      \"IsCarryOver\":false,\r\n" + 
					"      \"IsSchedulePreRandomize\":false,\r\n" + 
					"      \"IsResourceRandomize\":false\r\n" + 
					"   },\r\n" + 
					"   \"ParentSchedule\":{\r\n" + 
					"      \"Occurrence\":\"Monthly\",\r\n" + 
					"      \"Repetition\":1\r\n" + 
					"   }\r\n" + 
					"}";

			Response response = apiUtils.submitRequest(METHOD_TYPE.POST,request,headers, CreateSchedule);

			SimpleDataResponse sdr = gson.fromJson(response.asString(), SimpleDataResponse.class);

			String ScheduleId = sdr.Data;			
			logInfo("Monthly Schedule Created");
			return ScheduleId;
		}catch (Exception e) {			
			logError("Failed to create monthly schedule - "+ e.getMessage());
			return null;
		}
	}

	/**
	 * This method is used to create Yearly Schedule
	 * @author choubey_a
	 * @param details  
	 * @return one time schedule Id
	 */

	public String createYearlySchedule(ScheduleDetails details) {
		try {
			apiUtils = new ApiUtils();
			Gson gson = new GsonBuilder().create();
			Map<String, String> headers = new HashMap<String, String>();
			headers.put("Content-Type", "application/json");
			headers.put("Accept", "application/json, text/plain, /");
			headers.put("Authorization", "Bearer " + accessToken);

			String request = "{\r\n" + 
					"   \"Template\":{\r\n" + 
					"      \"TaskName\":\""+details.TaskName+"\",\r\n" + 
					"      \"TaskType\":\"Form\",\r\n" + 
					"      \"FormEntityId\":\""+details.FormEntityId+"\",\r\n" + 
					"      \"DocumentId\":null,\r\n" + 
					"      \"LocationId\":\""+details.LocationId+"\",\r\n" + 
					"      \"TaskExpiration\":{\r\n" + 
					"         \"ExpirationIntervalId\":\"7eabf2a0-07fc-4ce7-a251-044ee40d2d53\",\r\n" + 
					"         \"ExpirationIntervalValue\":null,\r\n" + 
					"         \"ExpirationIntervalType\":\"Never\"\r\n" + 
					"      }\r\n" + 
					"   },\r\n" + 
					"   \"Schedule\":{\r\n" + 
					"      \"FirstDueDate\":\""+details.FirstDueDate+"\",\r\n" + 
					"      \"TaskDueTime\":\""+details.StartTime+"\",\r\n" + 
					"      \"ResourceId\":\""+details.ResourceId+"\",\r\n" + 
					"      \"AssignedToWorkGroupId\":\""+details.WorkGroupId+"\",\r\n" + 
					"      \"TaskEndYear\":"+details.EndYear+",\r\n" + 
					"      \"AssignTimeBeforeTaskIsDue\":{\r\n" + 
					"         \"Months\":0,\r\n" + 
					"         \"Weeks\":0,\r\n" + 
					"         \"Days\":0,\r\n" + 
					"         \"Hours\":0,\r\n" + 
					"         \"Minutes\":0\r\n" + 
					"      },\r\n" + 
					"      \"TimeZone\":{\r\n" + 
					"         \"Id\":\"Asia/Kolkata\",\r\n" + 
					"         \"DisplayName\":\"Republic of India\"\r\n" + 
					"      },\r\n" + 
					"      \"DayOfMonthTaskOccures\":{\r\n" + 
					"         \r\n" + 
					"      },\r\n" + 
					"      \"DaysOfWeek\":[\r\n" + 
					"         \r\n" + 
					"      ],\r\n" + 
					"      \"IsRandom\":false,\r\n" + 
					"      \"IsCarryOver\":false,\r\n" + 
					"      \"IsSchedulePreRandomize\":false,\r\n" + 
					"      \"IsResourceRandomize\":false\r\n" + 
					"   },\r\n" + 
					"   \"ParentSchedule\":{\r\n" + 
					"      \"Occurrence\":\"Yearly\",\r\n" + 
					"      \"Repetition\":1\r\n" + 
					"   }\r\n" + 
					"}";

			Response response = apiUtils.submitRequest(METHOD_TYPE.POST,request,headers, CreateSchedule);

			SimpleDataResponse sdr = gson.fromJson(response.asString(), SimpleDataResponse.class);

			String ScheduleId = sdr.Data;			
			logInfo("Yearly Schedule Created");
			return ScheduleId;
		}catch (Exception e) {			
			logError("Failed to create yearly schedule - "+ e.getMessage());
			return null;
		}
	}


	public class Rows{
		public String Name;
		public String ParentScheduleId;
		public String Status;
	}

	public class Data {
		public List<Rows> Rows;
		public String Name;
		public String Id;
	}

	public class SingleDataResponse {
		public Data Data;
		public boolean Status;
	}

	public class SimpleDataResponse{
		public String Data;
	}

	public class MultipleDataResponse {		
		public List<Data> Data;
	}

	public static class ScheduleDetails{
		public String TaskName;
		public String FormEntityId;
		public String LocationId;
		public String FirstDueDate;
		public String ResourceId;
		public String WorkGroupId;
		public String EndDate;
		public String EndTime;
		public String StartDate;
		public String StartTime;
		public String EndYear;
	}


}
