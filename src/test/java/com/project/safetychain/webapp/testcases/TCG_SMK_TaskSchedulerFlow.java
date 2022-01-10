package com.project.safetychain.webapp.testcases;
import java.text.ParseException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.project.safetychain.api.models.support.TCG_FormCreation_Wrapper;
import com.project.safetychain.api.models.support.Support.DPT_FIELD_TYPES;
import com.project.safetychain.api.models.support.Support.DPT_FORM_TYPES;
import com.project.safetychain.api.models.support.Support.FormFieldParams;
import com.project.safetychain.api.models.support.Support.FormParams;
import com.project.safetychain.api.models.support.Support.RESOURCE_TYPES;
import com.project.safetychain.webapp.pageobjects.CommonPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserFormsPage;
import com.project.safetychain.webapp.pageobjects.HomePage;
import com.project.safetychain.webapp.pageobjects.LoginPage;

import com.project.safetychain.webapp.pageobjects.TaskSchedulerPage;
import com.project.safetychain.webapp.pageobjects.WorkGroupsPage;
import com.project.safetychain.webapp.pageobjects.TaskSchedulerPage.TaskScheduleDetails;

import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.utilities.ApiUtils;
import com.project.utilities.CommonMethods;
import com.project.utilities.ControlActions;
import com.project.utilities.DateTime;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage;
import com.project.safetychain.webapp.pageobjects.ManageLocationPage;
import com.project.safetychain.webapp.pageobjects.ManageResourcePage;
import com.project.safetychain.webapp.pageobjects.ResourceDesignerPage;

import com.project.safetychain.webapp.pageobjects.InboxPage;	


public class TCG_SMK_TaskSchedulerFlow extends TestBase {
	ControlActions controlActions;
	CommonPage common;	
	LoginPage login;
	HomePage home;
	TaskSchedulerPage taskScheduler;
	TaskScheduleDetails tsd;	
	DateTime dateTime;	
	FormDesignerPage fdp;
	FSQABrowserPage fbp;
	WorkGroupsPage wgp;
	ResourceDesignerPage rdp;
	ManageLocationPage locations;
	InboxPage inp;	
	ManageResourcePage manageResource;
	FSQABrowserFormsPage fsqaFormsPage;

	public static String DailyTaskName;
	public static String DailyRecallTask;
	public static String OneTimeTaskName;
	public static String WeeklyTaskName;
	public static String WeeklyEditTask;
	public static String MonthlyTaskName;
	public static String YearlyTaskName;
	public static String IntervalTaskName;
	public static String RandomIntervalTask;
	public static String TaskDescription;
	public static String dateFormat;
	public static String dueDate;		
	public static String formName;	
	public static String locationCategoryValue;
	public static String customersCategoryValue;
	public static String customersInstanceValue;
	public static String locationInstanceValue;
	public static String formname;
	public static String wgName;
	public static String DailyInboxTask;	
	public static String numericFN = "Numeric";	


	@BeforeClass(alwaysRun = true)
	public void groupInit() throws InterruptedException {

		DailyTaskName = CommonMethods.dynamicText("Daily");
		DailyRecallTask = CommonMethods.dynamicText("DailyTask");
		OneTimeTaskName = CommonMethods.dynamicText("OneTime");
		WeeklyTaskName = CommonMethods.dynamicText("Weekly");
		WeeklyEditTask = CommonMethods.dynamicText("WeeklyTask");
		MonthlyTaskName = CommonMethods.dynamicText("Monthly");
		YearlyTaskName = CommonMethods.dynamicText("Yearly");
		IntervalTaskName = CommonMethods.dynamicText("Interval");
		RandomIntervalTask = CommonMethods.dynamicText("RandomInterval");
		TaskDescription = CommonMethods.dynamicText("Task Description");
		locationCategoryValue	= CommonMethods.dynamicText("Loc_Cat");
		customersCategoryValue	= CommonMethods.dynamicText("Cust_Cat");
		customersInstanceValue = CommonMethods.dynamicText("Cust_Inst");
		locationInstanceValue	= CommonMethods.dynamicText("Loc_Inst");
		wgName = CommonMethods.dynamicText("Auto_WG");
		formName= CommonMethods.dynamicText("TaskSchedlr");

		// ------------------------------------------------------------------------------------------------
		// API Implementation
		TCG_FormCreation_Wrapper formCreationWrapper = new TCG_FormCreation_Wrapper();
		ApiUtils apiUtils = new ApiUtils();

		// ------------------------------------------------------------------------------------------------
		// API - Location & Resource Creation and Linking
		List<String> userList = Arrays.asList(adminUsername);

		HashMap<String, List<String>> LocationMap = new HashMap<String, List<String>>();
		LocationMap.put(locationCategoryValue, Arrays.asList(locationInstanceValue));
		
		HashMap<String, List<String>> resourceCatMap = new HashMap<String, List<String>>();
		resourceCatMap.put(customersCategoryValue, Arrays.asList(customersInstanceValue));
		
		formCreationWrapper.Resource_Location_CreationWrapper(resourceCatMap, LocationMap, true, userList,
				RESOURCE_TYPES.CUSTOMERS);

		// ------------------------------------------------------------------------------------------------
		// API - Form Creation - Check
		// API - Add fields to form
		FormFieldParams numericField = new FormFieldParams();
		numericField.DPTFieldType = DPT_FIELD_TYPES.NUMERIC;
		numericField.Name = numericFN;

		// API - Form details
		// API - Generate unique ID for form to be created
		String formId = apiUtils.getUUID();
		
		// API - Form layout details
		FormParams fp = new FormParams();
		fp.FormId = formId;
		fp.FormName = formName;
		fp.type = DPT_FORM_TYPES.CHECK;
		fp.ResourceType = RESOURCE_TYPES.CUSTOMERS;
		fp.ResourceCategoryNm = customersCategoryValue;
		fp.ResourceInstanceNm = customersInstanceValue;
		fp.formElements = Arrays.asList(numericField);
		fp.CustomerResources = resourceCatMap;
		
		formCreationWrapper.API_Wrapper_Forms(fp);

		// ------------------------------------------------------------------------------------------------
		// WEB Application code starts here
		
		driver = launchbrowser();
		controlActions = new ControlActions(driver);
		controlActions.getUrl(applicationUrl);
		taskScheduler = new TaskSchedulerPage(driver);
		home = new HomePage(driver);
		login = new LoginPage(driver);
		dateTime = new DateTime(driver);
		fdp = new FormDesignerPage(driver);
		wgp = new WorkGroupsPage(driver);
		fbp = new FSQABrowserPage(driver);
		rdp = new ResourceDesignerPage(driver);
		locations = new ManageLocationPage(driver);
		manageResource = new ManageResourcePage(driver);
		inp = new InboxPage(driver);
		tsd = new TaskScheduleDetails();
		fsqaFormsPage = new FSQABrowserFormsPage(driver);
		login.performLogin(adminUsername, adminPassword);
		if(home.error) {
			TCGFailureMsg = "Could NOT login to application";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}	

		
//		String customerInstances[][] = {{customersInstanceValue,"true"}};
//
//		HashMap<String,String> resourceCategories = new HashMap<String,String>();
//		resourceCategories.put(CATEGORYTYPES.LOCATION, locationCategoryValue);
//		resourceCategories.put(CATEGORYTYPES.CUSTOMERS, customersCategoryValue);
//		
//		if(!rdp.createCategory(resourceCategories)) {
//			TCGFailureMsg = "Could NOT create Locations/Customer category - ";
//			logFatal(TCGFailureMsg);
//			throw new SkipException(TCGFailureMsg);
//		}
//
//		if(!locations.createLocationInstanceLinkUser(locationCategoryValue,locationInstanceValue,adminUsername)) {
//			TCGFailureMsg = "Could NOT create location instance - " + locationCategoryValue;
//			logFatal(TCGFailureMsg);
//			throw new SkipException(TCGFailureMsg);
//		}
//
//		if(!manageResource.addResourceInstances("Customers", customersCategoryValue, customerInstances, true, locationInstanceValue)) {
//			TCGFailureMsg = "Could NOT create resource " + customersInstanceValue + " & link Location - "+ locationInstanceValue;
//			logFatal(TCGFailureMsg);
//			throw new SkipException(TCGFailureMsg);
//		}
//
//		if(!fdp.createAndReleaseForm("Check", formName,"Customers", customersCategoryValue,
//				customersInstanceValue)) {
//			TCGFailureMsg = "Could NOT create and release form - " + formName;
//			logFatal(TCGFailureMsg);
//			throw new SkipException(TCGFailureMsg);
//		}

		if(!wgp.createWorkGroup(wgName, adminUsername)) {
			TCGFailureMsg = "Could not create workgroup - " + wgName;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}


		tsd.locationvalue = locationInstanceValue;
		tsd.formORdocument = "form";
		tsd.formORdocumentvalue = formName;
		tsd.resource = customersInstanceValue;
		tsd.workGroup = wgName;
		tsd.taskDescription = TaskDescription;

	}


	@Test(description = "One Time Schedule Task")
	public void TestCase_31112() throws ParseException, InterruptedException {

		home.clickTaskSchedulerMenu();
		controlActions.refreshPage();
		tsd.taskoccure = "One Time Only";		
		tsd.haveEndDate = true;
		tsd.taskname = OneTimeTaskName;
		
		boolean fillData1 = taskScheduler.fillstepOneData(tsd);
		TestValidation.IsTrue(fillData1, 
							  "SELECTED Location and Form for Schedule " + OneTimeTaskName,
							  "Could NOT select Location and Form for Schedule " + OneTimeTaskName);
		
		boolean fillData2 = taskScheduler.fillstepTwoData(tsd);
		TestValidation.IsTrue(fillData2, 
				  			  "SELECTED Resource and Workgroup for Schedule " + OneTimeTaskName,
				  			  "Could NOT select Resource and Workgroup for Schedule " + OneTimeTaskName);
		
		boolean fillData3 = taskScheduler.fillstepThreeData(tsd);
		TestValidation.IsTrue(fillData3, 
	  			   			  "CREATED One Time Schedule " + OneTimeTaskName,
	  			   			  "Could NOT create One Time Schedule" + OneTimeTaskName);
		
		boolean setLoc = taskScheduler.selectLocationForGrid(tsd.locationvalue);
		TestValidation.IsTrue(setLoc, 
							  "SELECTED Location " + tsd.locationvalue + " for Task Scheduler grid",
							  "Could NOT select Location " + tsd.locationvalue + " for Task Scheduler grid");
		
		boolean clickedAllBtn = taskScheduler.clickAllTasks();
		TestValidation.IsTrue(clickedAllBtn, 
				  			  "CLICKED on All button to display all Schedules",
				  			  "Could NOT click on All button to display all Schedules");
		
		boolean verifyOneTime = taskScheduler.verifyInScheduleView(OneTimeTaskName);
		TestValidation.IsTrue(verifyOneTime, 
							  "VERIFIED One Time Schedule " + OneTimeTaskName,
							  "Could NOT verify One Time schedule" + OneTimeTaskName);

	}

	@Test(description = "Daily Schedule Task")
	public void TestCase_31113() throws ParseException, InterruptedException {

		home.clickTaskSchedulerMenu();
		controlActions.refreshPage();
		tsd.taskoccure = "Daily";		
		tsd.haveEndDate = true;
		tsd.taskname = DailyTaskName;
		
		boolean fillData1 = taskScheduler.fillstepOneData(tsd);
		TestValidation.IsTrue(fillData1, 
							  "SELECTED Location and Form for Schedule " + DailyTaskName,
							  "Could NOT select Location and Form for Schedule " + DailyTaskName);
		
		boolean fillData2 = taskScheduler.fillstepTwoData(tsd);
		TestValidation.IsTrue(fillData2, 
				  			  "SELECTED Resource and Workgroup for Schedule " + DailyTaskName,
				  			  "Could NOT select Resource and Workgroup for Schedule " + DailyTaskName);
		
		boolean fillData3 = taskScheduler.fillstepThreeData(tsd);
		TestValidation.IsTrue(fillData3, 
	  			   			  "CREATED Daily Schedule " + DailyTaskName,
	  			   			  "Could NOT create Daily schedule" + DailyTaskName);
		
		boolean setLoc = taskScheduler.selectLocationForGrid(tsd.locationvalue);
		TestValidation.IsTrue(setLoc, 
							  "SELECTED Location " + tsd.locationvalue + " for Task Scheduler grid",
							  "Could NOT select Location " + tsd.locationvalue + " for Task Scheduler grid");
		
		boolean verifyDaily = taskScheduler.verifyScheduleInTaskView(DailyTaskName);
		TestValidation.IsTrue(verifyDaily, 
							  "VERIFIED Daily Schedule " + DailyTaskName,
							  "Could NOT verify Daily schedule " + DailyTaskName);
		
		inp = home.clickInboxMenu();
		boolean viewTask = inp.selectFormTask(DailyTaskName);		
		TestValidation.IsTrue(viewTask, 
							  "Form task selected" + DailyTaskName, 
							  "Could NOT select form task" + DailyTaskName);
		
		boolean submit = fsqaFormsPage.submitData(false, false, false, true,false);
		TestValidation.IsTrue(submit, 
						  	  "Task submitted" + DailyTaskName, 
						  	  "Could NOT submit Task" + DailyTaskName);
	}


	@Test(description = "Weekly Schedule Task")
	public void TestCase_31114() throws ParseException, InterruptedException {

		home.clickTaskSchedulerMenu();
		controlActions.refreshPage();
		tsd.taskoccure = "Weekly";
		tsd.haveEndDate = true;
		tsd.taskname = WeeklyTaskName;
		
		boolean fillData1 = taskScheduler.fillstepOneData(tsd);
		TestValidation.IsTrue(fillData1, 
							  "SELECTED Location and Form for Schedule " + WeeklyTaskName,
							  "Could NOT select Location and Form for Schedule " + WeeklyTaskName);
		
		boolean fillData2 = taskScheduler.fillstepTwoData(tsd);
		TestValidation.IsTrue(fillData2, 
				  			  "SELECTED Resource and Workgroup for Schedule " + WeeklyTaskName,
				  			  "Could NOT select Resource and Workgroup for Schedule " + WeeklyTaskName);
		
		boolean fillData3 = taskScheduler.fillstepThreeData(tsd);
		TestValidation.IsTrue(fillData3, 
	  			   			  "CREATED Weekly Schedule " + WeeklyTaskName,
	  			   			  "Could NOT create Weekly schedule" + WeeklyTaskName);
		
		boolean setLoc = taskScheduler.selectLocationForGrid(tsd.locationvalue);
		TestValidation.IsTrue(setLoc, 
							  "SELECTED Location " + tsd.locationvalue + " for Task Scheduler grid",
							  "Could NOT select Location " + tsd.locationvalue + " for Task Scheduler grid");
		
		boolean verifyWeekly = taskScheduler.verifyScheduleInTaskView(WeeklyTaskName);
		TestValidation.IsTrue(verifyWeekly, 
							  "VERIFIED Weekly Schedule " + WeeklyTaskName,
							  "Could NOT verify Weekly schedule " + WeeklyTaskName);

	}

	@Test(description = "Monthly Schedule Task")
	public void TestCase_31115() throws ParseException, InterruptedException {

		home.clickTaskSchedulerMenu();
		controlActions.refreshPage();
		tsd.taskoccure = "Monthly";
		tsd.haveEndDate = true;
		tsd.taskname = MonthlyTaskName;
		
		boolean fillData1 = taskScheduler.fillstepOneData(tsd);
		TestValidation.IsTrue(fillData1, 
							  "SELECTED Location and Form for Schedule " + MonthlyTaskName,
							  "Could NOT select Location and Form for Schedule " + MonthlyTaskName);
		
		boolean fillData2 = taskScheduler.fillstepTwoData(tsd);
		TestValidation.IsTrue(fillData2, 
				  			  "SELECTED Resource and Workgroup for Schedule " + MonthlyTaskName,
				  			  "Could NOT select Resource and Workgroup for Schedule " + MonthlyTaskName);
		
		boolean fillData3 = taskScheduler.fillstepThreeData(tsd);
		TestValidation.IsTrue(fillData3, 
	  			   			  "CREATED Monthly Schedule " + MonthlyTaskName,
	  			   			  "Could NOT create Monthly schedule" + MonthlyTaskName);
		
		boolean setLoc = taskScheduler.selectLocationForGrid(tsd.locationvalue);
		TestValidation.IsTrue(setLoc, 
							  "SELECTED Location " + tsd.locationvalue + " for Task Scheduler grid",
							  "Could NOT select Location " + tsd.locationvalue + " for Task Scheduler grid");
		
		boolean verifyWeekly = taskScheduler.verifyScheduleInTaskView(MonthlyTaskName);
		TestValidation.IsTrue(verifyWeekly, 
							  "VERIFIED Monthly Schedule " + MonthlyTaskName,
							  "Could NOT verify Monthly schedule " + MonthlyTaskName);

	}

	@Test(description = "Yearly Schedule Task")
	public void TestCase_31116() throws ParseException, InterruptedException {

		home.clickTaskSchedulerMenu();
		controlActions.refreshPage();
		tsd.taskoccure = "Yearly";
		tsd.haveEndDate = true;
		tsd.taskname = YearlyTaskName;
		
		boolean fillData1 = taskScheduler.fillstepOneData(tsd);
		TestValidation.IsTrue(fillData1, 
							  "SELECTED Location and Form for Schedule " + YearlyTaskName,
							  "Could NOT select Location and Form for Schedule " + YearlyTaskName);
		
		boolean fillData2 = taskScheduler.fillstepTwoData(tsd);
		TestValidation.IsTrue(fillData2, 
				  			  "SELECTED Resource and Workgroup for Schedule " + YearlyTaskName,
				  			  "Could NOT select Resource and Workgroup for Schedule " + YearlyTaskName);
		
		boolean fillData3 = taskScheduler.fillstepThreeData(tsd);
		TestValidation.IsTrue(fillData3, 
	  			   			  "CREATED Yearly Schedule " + YearlyTaskName,
	  			   			  "Could NOT create Yearly schedule" + YearlyTaskName);
		
		boolean setLoc = taskScheduler.selectLocationForGrid(tsd.locationvalue);
		TestValidation.IsTrue(setLoc, 
							  "SELECTED Location " + tsd.locationvalue + " for Task Scheduler grid",
							  "Could NOT select Location " + tsd.locationvalue + " for Task Scheduler grid");
		
		boolean verifyWeekly = taskScheduler.verifyScheduleInTaskView(YearlyTaskName);
		TestValidation.IsTrue(verifyWeekly, 
							  "VERIFIED Yearly Schedule " + YearlyTaskName,
							  "Could NOT verify Yearly schedule " + YearlyTaskName);

	}

	@Test(description = "Interval Schedule Task")
	public void TestCase_31117() throws ParseException, InterruptedException {

		home.clickTaskSchedulerMenu();
		controlActions.refreshPage();
		tsd.taskoccure = "Interval";
		tsd.haveEndDate = false;
		tsd.ramdomize = false;
		tsd.taskname = IntervalTaskName;
		tsd.interval = "15 Min";
		
		boolean fillData1 = taskScheduler.fillstepOneData(tsd);
		TestValidation.IsTrue(fillData1, 
							  "SELECTED Location and Form for Schedule " + IntervalTaskName,
							  "Could NOT select Location and Form for Schedule " + IntervalTaskName);
		
		boolean fillData2 = taskScheduler.fillstepTwoData(tsd);
		TestValidation.IsTrue(fillData2, 
				  			  "SELECTED Resource and Workgroup for Schedule " + IntervalTaskName,
				  			  "Could NOT select Resource and Workgroup for Schedule " + IntervalTaskName);
		
		boolean fillData3 = taskScheduler.fillstepThreeData(tsd);
		TestValidation.IsTrue(fillData3, 
	  			   			  "CREATED Interval Schedule " + IntervalTaskName,
	  			   			  "Could NOT create Interval schedule" + IntervalTaskName);
		
		boolean setLoc = taskScheduler.selectLocationForGrid(tsd.locationvalue);
		TestValidation.IsTrue(setLoc, 
							  "SELECTED Location " + tsd.locationvalue + " for Task Scheduler grid",
							  "Could NOT select Location " + tsd.locationvalue + " for Task Scheduler grid");
		
		boolean verifyInterval = taskScheduler.verifyScheduleInTaskView(IntervalTaskName);
		TestValidation.IsTrue(verifyInterval, 
							  "VERIFIED Interval Schedule " + IntervalTaskName,
							  "Could NOT verify Interval schedule " + IntervalTaskName);
		
	}

	@Test(description = "Randomized Interval Task")
	public void TestCase_31119() throws ParseException, InterruptedException {

		home.clickTaskSchedulerMenu();
		controlActions.refreshPage();
		tsd.taskoccure = "Interval";
		tsd.haveEndDate = false;
		tsd.ramdomize = true;
		tsd.taskname = RandomIntervalTask;
		tsd.interval = "15 Min";
		boolean fillData1 = taskScheduler.fillstepOneData(tsd);
		TestValidation.IsTrue(fillData1, 
							  "SELECTED Location and Form for Schedule " + IntervalTaskName,
							  "Could NOT select Location and Form for Schedule " + IntervalTaskName);
		
		boolean fillData2 = taskScheduler.fillstepTwoData(tsd);
		TestValidation.IsTrue(fillData2, 
				  			  "SELECTED Resource and Workgroup for Schedule " + IntervalTaskName,
				  			  "Could NOT select Resource and Workgroup for Schedule " + IntervalTaskName);
		
		boolean fillData3 = taskScheduler.fillstepThreeData(tsd);
		TestValidation.IsTrue(fillData3, 
	  			   			  "CREATED Interval Schedule " + IntervalTaskName,
	  			   			  "Could NOT create Interval schedule" + IntervalTaskName);
		
		boolean setLoc = taskScheduler.selectLocationForGrid(tsd.locationvalue);
		TestValidation.IsTrue(setLoc, 
							  "SELECTED Location " + tsd.locationvalue + " for Task Scheduler grid",
							  "Could NOT select Location " + tsd.locationvalue + " for Task Scheduler grid");
		
		boolean verifyInterval = taskScheduler.verifyScheduleInTaskView(IntervalTaskName);
		TestValidation.IsTrue(verifyInterval, 
							  "VERIFIED Interval Schedule " + IntervalTaskName,
							  "Could NOT verify Interval schedule " + IntervalTaskName);
	}

	@Test(description = "Recall Task")	
	public void TestCase_35143() {

		home.clickTaskSchedulerMenu();
		controlActions.refreshPage();
		tsd.taskoccure = "Daily";		
		tsd.haveEndDate = true;
		tsd.taskname = DailyRecallTask;
		boolean fillData1 = taskScheduler.fillstepOneData(tsd);
		TestValidation.IsTrue(fillData1, 
							  "SELECTED Location and Form for Schedule " + DailyRecallTask,
							  "Could NOT select Location and Form for Schedule " + DailyRecallTask);
		
		boolean fillData2 = taskScheduler.fillstepTwoData(tsd);
		TestValidation.IsTrue(fillData2, 
				  			  "SELECTED Resource and Workgroup for Schedule " + DailyRecallTask,
				  			  "Could NOT select Resource and Workgroup for Schedule " + DailyRecallTask);
		
		boolean fillData3 = taskScheduler.fillstepThreeData(tsd);
		TestValidation.IsTrue(fillData3, 
	  			   			  "CREATED Daily Schedule " + DailyRecallTask,
	  			   			  "Could NOT create Daily schedule" + DailyRecallTask);
		threadsleep(60000);
		controlActions.refreshPage();
		
		boolean setLoc = taskScheduler.selectLocationForGrid(tsd.locationvalue);
		TestValidation.IsTrue(setLoc, 
							  "SELECTED Location " + tsd.locationvalue + " for Task Scheduler grid",
							  "Could NOT select Location " + tsd.locationvalue + " for Task Scheduler grid");
		
		boolean openTaskView = taskScheduler.taskView();
		boolean recall = taskScheduler.recallTask(DailyRecallTask);	
		TestValidation.IsTrue(openTaskView && recall, 
							  "Task recalled " + DailyRecallTask,
							  "Could NOT recall Task " +DailyRecallTask);
		
	}

	@Test(description = "Schedule Edit")	
	public void TestCase_34928() {

		home.clickTaskSchedulerMenu();
		controlActions.refreshPage();
		tsd.taskoccure = "Weekly";
		tsd.haveEndDate = true;
		tsd.taskname = WeeklyEditTask;
		boolean fillData1 = taskScheduler.fillstepOneData(tsd);
		TestValidation.IsTrue(fillData1, 
							  "SELECTED Location and Form for Schedule " + WeeklyEditTask,
							  "Could NOT select Location and Form for Schedule " + WeeklyEditTask);
		
		boolean fillData2 = taskScheduler.fillstepTwoData(tsd);
		TestValidation.IsTrue(fillData2, 
				  			  "SELECTED Resource and Workgroup for Schedule " + WeeklyEditTask,
				  			  "Could NOT select Resource and Workgroup for Schedule " + WeeklyEditTask);
		
		boolean fillData3 = taskScheduler.fillstepThreeData(tsd);
		TestValidation.IsTrue(fillData3, 
	  			   			  "CREATED Weekly Schedule " + WeeklyEditTask,
	  			   			  "Could NOT create Weekly schedule" + WeeklyEditTask);
		
		boolean setLoc = taskScheduler.selectLocationForGrid(tsd.locationvalue);
		TestValidation.IsTrue(setLoc, 
							  "SELECTED Location " + tsd.locationvalue + " for Task Scheduler grid",
							  "Could NOT select Location " + tsd.locationvalue + " for Task Scheduler grid");
		
		boolean edit = taskScheduler.editSchedule(tsd.taskname);
		TestValidation.IsTrue(edit, "Weekly Schedule Edited"+ WeeklyEditTask,
				"Could NOT edit Weekly schedule" + WeeklyEditTask);
		
		boolean verifyedit = taskScheduler.verifyEditedSchedule(tsd.taskname);
		TestValidation.IsTrue(verifyedit, "Weekly Schedule Edit verified"+ WeeklyEditTask,
				"Could NOT verify Weekly schedule edit" + WeeklyEditTask);
		
	}

	@AfterClass(alwaysRun = true)
	public void closeBrowser() throws InterruptedException {
		driver.close();
	}


}
