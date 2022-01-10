package com.project.safetychain.api.models;

public class TaskUrls {

	/** Web APIs */
	public static final String SAVE_WORK_GROUP_URL = "api/api/WorkGroup/SaveWorkGroup";
	public static final String ADD_FORM_TASK_URL = "api/api/Forms/AddFormTask";
	
	public static final String ADD_SCHEDULER_TASK_URL = "api/api/TaskScheduler/CreateTaskSchedule";
	public static final String GET_SCHEDULE_INTERVAL_URL ="api/api/TaskScheduler/GetScheduleInterval";
	public static final String GET_EXPIRATION_INTERVAL_URL ="api/api/Security/GetExpirationInterval";
	public static final String RECALL_TASK = "api/api/Task/RecallTask";
	public static final String REASSIGN_TASK = "api/api/Task/ReassignTask";
	
	

	/** DPT APIs */
	public static final String DPT_SAVE_FORM_DESIGN_URL = "api/integration/saveformdesign";

}
