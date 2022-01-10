package com.project.safetychain.apiproject.testcases;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.project.safetychain.apiproject.models.Auth;
import com.project.safetychain.apiproject.models.FormDesigner;
import com.project.safetychain.apiproject.models.ResourceDesigner;
import com.project.safetychain.apiproject.models.Resources;
import com.project.safetychain.apiproject.models.TaskScheduler;
import com.project.safetychain.apiproject.models.TaskScheduler.ScheduleDetails;
import com.project.safetychain.apiproject.models.WorkGroups;
import com.project.safetychain.apiproject.models.FormDesigner.FormFields;
import com.project.safetychain.apiproject.models.FormDesigner.FormTypes;
import com.project.safetychain.apiproject.models.ResourceDesigner.ResourceTypes;
import com.project.safetychain.apiproject.models.Resources.ResourcesData;
import com.project.safetychain.apiproject.models.WorkGroups.WorkGroupDetails;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.utilities.CommonMethods;
import com.project.utilities.DateTime;

public class TCG_TaskSchedulerFlows extends TestBase{

	Auth auth = null;
	ResourceDesigner resourcedesigner = null;
	Resources resources = null;
	WorkGroups wg = null;
	FormDesigner designer =null;
	FormFields fields = null;
	TaskScheduler schedule = null;
	ScheduleDetails details = null;
	DateTime datetime = null;

	public String resourceInstanceId = null;
	public String resourceInstance = null;
	public String wgName = null;
	public String wgId = null;
	public String locationCat = null;
	public String locationInstance = null;
	public String locationInstanceId = null;
	public String formEntityId = null;
	public String resourceCat = null;
	public String formname = null;

	String onetimeschedule = CommonMethods.dynamicText("OT");
	String intervalschedule = CommonMethods.dynamicText("I");
	String dailyschedule = CommonMethods.dynamicText("D");
	String weeklyschedule = CommonMethods.dynamicText("W");
	String monthlyschedule = CommonMethods.dynamicText("M");
	String yearlyschedule = CommonMethods.dynamicText("Y");

	@BeforeClass(alwaysRun = true)
	public void groupInit() {

		auth = new Auth();
		auth.setAccessToken();
		resourcedesigner = new ResourceDesigner();
		resources = new Resources();
		wg = new WorkGroups();
		designer = new FormDesigner();
		schedule = new TaskScheduler();

		resourceInstance = CommonMethods.dynamicText("EquipI");
		resourceCat = CommonMethods.dynamicText("EquipC");
		locationInstance = CommonMethods.dynamicText("LocI");
		locationCat = CommonMethods.dynamicText("LocC");
		formname = CommonMethods.dynamicText("CForm");
		wgName = CommonMethods.dynamicText("AW");

		String locationTypeId = resourcedesigner.getResourceTypeId(ResourceTypes.LOCATIONS);

		resourcedesigner.createResourceCategory(locationTypeId,locationTypeId,locationCat);

		String locationCatId = resources.getLocCatId(locationCat);

		List <String> enalbledFieldIds = resourcedesigner.getEnabledResourceTypeFields(locationTypeId);

		List <String> fieldValues = resourcedesigner.getFieldValue(enalbledFieldIds, ResourceTypes.LOCATIONS);

		String resourceTypeFields = resources.getResourceTypeFields(fieldValues,locationInstance);

		locationInstanceId = resources.createLocInstance(locationCatId,locationCat, locationInstance, resourceTypeFields);

		resourcedesigner = new ResourceDesigner();
		resources = new Resources();

		String resourceTypeId = resourcedesigner.getResourceTypeId(ResourceTypes.EQUIPMENT);

		resourcedesigner.getResourceDesignerFields(resourceTypeId);

		resourcedesigner.createResourceCategory(resourceTypeId,resourceTypeId,resourceCat);

		String resourceCatId = resources.getResourceCategoryId(resourceTypeId,resourceCat);

		List <String> enabledTypeFieldId1 = resourcedesigner.getEnabledResourceTypeFields(resourceTypeId);

		List <String> fieldValues1 = resourcedesigner.getFieldValue(enabledTypeFieldId1, ResourceTypes.EQUIPMENT);

		String fieldDetails = resources.getResourceTypeFields(fieldValues1, resourceInstance);

		resourceInstanceId = resources.createResourceInstance(fieldDetails,resourceTypeId,resourceInstance, resourceCatId, "true");

		ResourcesData data = new ResourcesData();

		data.locationCatName = locationCat;
		data.locationTypeId = locationTypeId;
		data.locationInstanceId = locationInstanceId;
		data.locationInstname = locationInstance;
		data.resourceTypeId = resourceTypeId;
		data.resourceInstanceId = resourceInstanceId;

		boolean link = resources.linkResourceLocation(data);
		TestValidation.IsTrue(link, 
				"Location Resource linked",
				"FAILED to link Location Resource");

		auth.setAccessToken();

		boolean resourcetree = designer.getResourceTree(resourceInstanceId,resourceCat,resourceInstance);
		TestValidation.IsTrue(resourcetree, 
				"Resource Tree retrived",
				"FAILED to get resource tree details");		

		List<String> formfield = Arrays.asList(FormFields.NUMERIC,FormFields.FREETEXT,FormFields.SELECTONE);

		HashMap<String,String> fieldnames = new LinkedHashMap<String,String>();

		fieldnames.put("Number", "Number");
		fieldnames.put("Text", "Text");
		fieldnames.put("SelectOne", "One");

		designer.createForm(formname, FormTypes.CHECK, formfield, fieldnames, false, false);

		WorkGroupDetails workGroupDetails = new WorkGroupDetails();
		workGroupDetails.workgroupName = wgName;
		workGroupDetails.userIDs = Arrays.asList(WorkGroups.SUPERADMIN_USER_ID);

		wg.createWorkGroup(workGroupDetails);
		wgId = wg.getWorkGroupDetails(wgName, "Id");

	}

	@Test(description = "One Times Schedule Creation")
	public void One_Time_Schedule_Creation() throws InterruptedException {

		datetime = new DateTime();
		String date = datetime.getDate("Day", 0);
		String date1 = datetime.getDate("Day", 1);
		String time = datetime.getTime(0, 1, false, false);		
		String datetime = date+" "+time;

		String entityId = schedule.getFormsbyLocation(locationInstanceId,formname);

		ScheduleDetails details = new ScheduleDetails();		
		details.FirstDueDate = datetime;
		details.FormEntityId = entityId;
		details.LocationId = locationInstanceId;
		details.WorkGroupId = wgId;
		details.TaskName = onetimeschedule;

		String scheduleId = schedule.createOneTimeSchedule(details);

		Thread.sleep(40000);
		
		auth.setAccessToken();

		boolean Schedule = schedule.getSchedule(onetimeschedule, locationInstanceId, date1, date1);
		TestValidation.IsTrue(Schedule, "One time schedule created","One time schedule not created");


		boolean task = schedule.getScheduleTask(scheduleId, locationInstanceId, date, date);
		TestValidation.IsTrue(task, "One time schedule task assigned","One time task not assigned");

	}

	@Test(description = "Interval Schedule Creation")
	public void Interval_Schedule_Creation() throws InterruptedException {

		datetime = new DateTime();
		String startdate = datetime.getDate("Day", 0);
		String enddate = datetime.getDate("Day", 10);
		String starttime = datetime.getTime(0, 1, false, false);		
		String endtime = datetime.getTime(2, 0, false, false);		

		String entityId = schedule.getFormsbyLocation(locationInstanceId,formname);

		ScheduleDetails details = new ScheduleDetails();		
		details.StartDate = startdate;
		details.StartTime = starttime;
		details.EndTime = endtime;
		details.EndDate = enddate;
		details.FormEntityId = entityId;
		details.LocationId = locationInstanceId;
		details.WorkGroupId = wgId;
		details.TaskName = intervalschedule;

		String scheduleId = schedule.createIntervalSchedule(details);

		Thread.sleep(40000);
		
		auth.setAccessToken();

		boolean Schedule = schedule.getSchedule(intervalschedule, locationInstanceId, startdate, startdate);
		TestValidation.IsTrue(Schedule, "Interval schedule created","Interval schedule not created");	

		boolean task = schedule.getScheduleTask(scheduleId, locationInstanceId, startdate, startdate);
		TestValidation.IsTrue(task, "Interval schedule task assigned","Interval task not assigned");

	}

	@Test(description = "Daily Schedule Creation")
	public void Daily_Schedule_Creation() throws InterruptedException {

		datetime = new DateTime();
		String startdate = datetime.getDate("Day", 0);
		String enddate = datetime.getDate("Day", 10);
		String starttime = datetime.getTime(0, 1, false, false);

		String entityId = schedule.getFormsbyLocation(locationInstanceId,formname);

		ScheduleDetails details = new ScheduleDetails();		
		details.StartDate = startdate;
		details.StartTime = starttime;
		details.EndDate = enddate;
		details.FormEntityId = entityId;
		details.LocationId = locationInstanceId;
		details.WorkGroupId = wgId;
		details.TaskName = dailyschedule;

		String scheduleId = schedule.createDailySchedule(details);

		Thread.sleep(40000);
		
		auth.setAccessToken();

		boolean Schedule = schedule.getSchedule(dailyschedule, locationInstanceId, startdate, startdate);
		TestValidation.IsTrue(Schedule, "Daily schedule created","Daily schedule not created");	

		boolean task = schedule.getScheduleTask(scheduleId, locationInstanceId, startdate, startdate);
		TestValidation.IsTrue(task, "Daily schedule task assigned","Daily task not assigned");

	}

	@Test(description = "Weekly Schedule Creation")
	public void Weekly_Schedule_Creation() throws InterruptedException {

		datetime = new DateTime();
		String startdate = datetime.getDate("Day", 0);
		String enddate = datetime.getDate("Day", 10);
		String starttime = datetime.getTime(0, 1, false, false);

		String entityId = schedule.getFormsbyLocation(locationInstanceId,formname);

		ScheduleDetails details = new ScheduleDetails();		
		details.FirstDueDate = startdate;
		details.StartTime = starttime;
		details.EndDate = enddate;
		details.FormEntityId = entityId;
		details.LocationId = locationInstanceId;
		details.WorkGroupId = wgId;
		details.TaskName = weeklyschedule;

		String scheduleId = schedule.createWeeklySchedule(details);

		Thread.sleep(40000);
		
		auth.setAccessToken();

		boolean Schedule = schedule.getSchedule(weeklyschedule, locationInstanceId, startdate, startdate);
		TestValidation.IsTrue(Schedule, "Weekly schedule created","Weekly schedule not created");	

		boolean task = schedule.getScheduleTask(scheduleId, locationInstanceId, startdate, startdate);
		TestValidation.IsTrue(task, "Weekly schedule task assigned","Weekly task not assigned");

	}

	@Test(description = "Monthly Schedule Creation")
	public void Monthly_Schedule_Creation() throws InterruptedException {

		datetime = new DateTime();
		String startdate = datetime.getDate("Day", 0);
		String enddate = datetime.getDate("Day", 10);
		String starttime = datetime.getTime(0, 1, false, false);

		String entityId = schedule.getFormsbyLocation(locationInstanceId,formname);

		ScheduleDetails details = new ScheduleDetails();		
		details.FirstDueDate = startdate;
		details.StartTime = starttime;
		details.EndDate = enddate;
		details.FormEntityId = entityId;
		details.LocationId = locationInstanceId;
		details.WorkGroupId = wgId;
		details.TaskName = monthlyschedule;

		String scheduleId = schedule.createMonthlySchedule(details);

		Thread.sleep(40000);
		
		auth.setAccessToken();

		boolean Schedule = schedule.getSchedule(monthlyschedule, locationInstanceId, startdate, startdate);
		TestValidation.IsTrue(Schedule, "Monthly schedule created","Monthly schedule not created");	

		boolean task = schedule.getScheduleTask(scheduleId, locationInstanceId, startdate, startdate);
		TestValidation.IsTrue(task, "Monthly schedule task assigned","Monthly task not assigned");

	}

	@Test(description = "Yearly Schedule Creation")
	public void Yearly_Schedule_Creation() throws InterruptedException {

		datetime = new DateTime();
		String startdate = datetime.getDate("Day", 0);
		String starttime = datetime.getTime(0, 1, false, false);

		String entityId = schedule.getFormsbyLocation(locationInstanceId,formname);

		ScheduleDetails details = new ScheduleDetails();		
		details.FirstDueDate = startdate;
		details.StartTime = starttime;
		details.EndYear = "2025";
		details.FormEntityId = entityId;
		details.LocationId = locationInstanceId;
		details.WorkGroupId = wgId;
		details.TaskName = yearlyschedule;

		String scheduleId = schedule.createYearlySchedule(details);

		Thread.sleep(40000);
		
		auth.setAccessToken();

		boolean Schedule = schedule.getSchedule(yearlyschedule, locationInstanceId, startdate, startdate);
		TestValidation.IsTrue(Schedule, "Yearly schedule created","Yearly schedule not created");	

		boolean task = schedule.getScheduleTask(scheduleId, locationInstanceId, startdate, startdate);
		TestValidation.IsTrue(task, "Yearly schedule task assigned","Yearly task not assigned");

	}

}

