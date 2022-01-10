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
import com.project.safetychain.api.models.support.Support.RESOURCE_TYPES;
import com.project.safetychain.webapp.pageobjects.CommonPage;
import com.project.safetychain.webapp.pageobjects.CommonPage.TIMEZONE;
import com.project.safetychain.webapp.pageobjects.DocumentManagementPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserFormsPage;
import com.project.safetychain.webapp.pageobjects.HomePage;
import com.project.safetychain.webapp.pageobjects.LoginPage;

import com.project.safetychain.webapp.pageobjects.TaskSchedulerPage;
import com.project.safetychain.webapp.pageobjects.WorkGroupsPage;
import com.project.safetychain.webapp.pageobjects.TaskSchedulerPage.TaskScheduleDetails;
import com.project.safetychain.webapp.pageobjects.TaskSchedulerPage.Task_Status;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.utilities.CommonMethods;
import com.project.utilities.ControlActions;
import com.project.utilities.DateTime;
import com.project.utilities.FileOperations;

import com.project.safetychain.webapp.pageobjects.FSQABrowserPage;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage;
import com.project.safetychain.webapp.pageobjects.ManageLocationPage;
import com.project.safetychain.webapp.pageobjects.ManageResourcePage;
import com.project.safetychain.webapp.pageobjects.ResourceDesignerPage;

import com.project.safetychain.webapp.pageobjects.InboxPage;	

public class TCG_REG_TaskSchedulerFlow extends TestBase {
	ControlActions controlActions;
	CommonPage common;	
	LoginPage login;
	HomePage home;
	TaskSchedulerPage taskScheduler;
	TaskScheduleDetails tsd;	
	FormDesignerPage fdp;
	FSQABrowserPage fbp;
	WorkGroupsPage wgp;
	ResourceDesignerPage rdp;
	ManageLocationPage locations;
	InboxPage inp;	
	ManageResourcePage manageResource;
	FSQABrowserFormsPage fsqaFormsPage;
	DocumentManagementPage dmp;
	FileOperations fo;

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
	public static String doctype;
	public static String doctaskname;
	public static String wgName;
	public static String DailyInboxTask;	
	public static String numericFN = "Numeric";	
	public static String uploadlinkdoc;
	public static String linkdoc;
	public static String linkdoc1;
	public static String SkipTaskName;
	public static String UpdateTaskWG;


	@BeforeClass(alwaysRun = true)
	public void groupInit() throws InterruptedException {

		TaskDescription = CommonMethods.dynamicText("Task Description");
		locationCategoryValue	= CommonMethods.dynamicText("Loc_Cat");
		customersCategoryValue	= CommonMethods.dynamicText("Cust_Cat");
		customersInstanceValue = CommonMethods.dynamicText("Cust_Inst");
		locationInstanceValue	= CommonMethods.dynamicText("Loc_Inst1");
		wgName = CommonMethods.dynamicText("Auto_WG");
		formName= CommonMethods.dynamicText("TaskScheduler01234");
		doctype = CommonMethods.dynamicText("Doc");
		SkipTaskName = CommonMethods.dynamicText("SkipTask");
		linkdoc = "upload.png";
		linkdoc1= CommonMethods.dynamicText("Test01")+".png";
		UpdateTaskWG= CommonMethods.dynamicText("UpdateTaskWG");

		// ------------------------------------------------------------------------------------------------
		// API Implementation
		TCG_FormCreation_Wrapper formCreationWrapper = new TCG_FormCreation_Wrapper();
		//ApiUtils apiUtils = new ApiUtils();

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
		// WEB Application code starts here
		
		// ------------------------------------------------------------------------------------------------

		driver = launchbrowser();
		controlActions = new ControlActions(driver);
		controlActions.getUrl(applicationUrl);
		taskScheduler = new TaskSchedulerPage(driver);
		home = new HomePage(driver);
		login = new LoginPage(driver);
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
		
//				// API - Form layout details
//				FormParams fp = new FormParams();
//				fp.FormId = formId;
//				fp.FormName = formName;
//				fp.type = DPT_FORM_TYPES.CHECK;
//				fp.ResourceType = RESOURCE_TYPES.CUSTOMERS;
//				fp.ResourceCategoryNm = customersCategoryValue;
//				fp.ResourceInstanceNm = customersInstanceValue;
//				fp.formElements = Arrays.asList(numericField);
//				fp.CustomerResources = resourceCatMap;
//
//				formCreationWrapper.API_Wrapper_Forms(fp);

		
//		//real code
				if(!fdp.createAndReleaseForm("Check", formName, RESOURCE_TYPES.CUSTOMERS, customersCategoryValue,
						customersInstanceValue)) {
					TCGFailureMsg = "Could NOT create and release form - " + formName;
					logFatal(TCGFailureMsg);
					throw new SkipException(TCGFailureMsg);
				}
				
				
				
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

	@Test(description="Task Scheduler|| Proper and correct date should populate in Task Due column")
	public void TestCase_22742() throws ParseException, InterruptedException {

		try {
			
		tsd.taskoccure = "Daily";		
		tsd.haveEndDate = true;
		tsd.taskname =CommonMethods.dynamicText("Daily_");
		//tsd.locationvalue="Loc_Inst1_01.12_10.05.40";
		int day=15;
		int interval=4;
		DateTime dt = new DateTime();
		String currentDate = dt.getTodayDate("MM/dd/YYYY", dt.getTimezone(adminTimezone));

		home.clickTaskSchedulerMenu();

		boolean viewByTaskBtnDisplayed= controlActions.isElementDisplayed(taskScheduler.ViewByTaskBtn);
		TestValidation.IsTrue(viewByTaskBtnDisplayed, 
				"VERIFIED 'View By Task' button is displayed", 
				"Failed to verify 'View By Task' button is displayed");
		controlActions.refreshPage();

		boolean fillData1 = taskScheduler.fillstepOneData(tsd);
		TestValidation.IsTrue(fillData1, 
				"SELECTED Location and Form for Schedule " + tsd.taskname,
				"Could NOT select Location and Form for Schedule " + tsd.taskname);

		boolean fillData2 = taskScheduler.fillstepTwoData(tsd);
		TestValidation.IsTrue(fillData2, 
				"SELECTED Resource and Workgroup for Schedule " + tsd.taskname,
				"Could NOT select Resource and Workgroup for Schedule " + tsd.taskname);

		boolean fillData3 = taskScheduler.fillStepThreeDataForDaily(tsd, day, interval);
		TestValidation.IsTrue(fillData3, 
				"CREATED Daily Schedule " + tsd.taskname,
				"Could NOT create Daily schedule" + tsd.taskname);
		
		boolean setLoc = taskScheduler.selectLocationForGrid(tsd.locationvalue);
		TestValidation.IsTrue(setLoc, 
				"SELECTED Location " + tsd.locationvalue + " for Task Scheduler grid",
				"Could NOT select Location " + tsd.locationvalue + " for Task Scheduler grid");

		
		boolean verifyDaily = taskScheduler.verifyScheduleInTaskView(tsd.taskname);
		TestValidation.IsTrue(verifyDaily, 
				"VERIFIED Daily Schedule " + tsd.taskname,
				"Could NOT verify Daily schedule " + tsd.taskname);
		
		
		boolean verifyDueDate = taskScheduler.verifyTaskDueDate(tsd, day, interval, currentDate);
		TestValidation.IsTrue(verifyDueDate, 
				"VERIFIED, correct Due date is displayed for given " + tsd.taskname,
				"Failed,  incorrect Due date is displayed for given " + tsd.taskname);
		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				TestValidation.Fail(TCGFailureMsg);
			}
		}
		
	}

	@Test(description="Task Scheduler || The required details should get fetched while searching.")
	public void TestCase_22743() throws ParseException, InterruptedException {

		try {
			tsd.taskoccure = "Daily";		
			tsd.haveEndDate = true;
			tsd.taskname = CommonMethods.dynamicText("Link_Daily_");
			//linkdoc1="TstCas000001_22743.jpg";
			int day=8;
			int interval=4;
			fo=new FileOperations();

			boolean renameDocument = fo.renameFile(linkdoc,linkdoc1);
			TestValidation.IsTrue(renameDocument, 
					"Succesfully renamed Document name under testdatafiles->UploadDocuments folder" , 
					"Failed to rename Document in user directory");
			uploadlinkdoc = System.getProperty("user.dir") + "\\test-data-files\\UploadDocuments\\"+linkdoc1;

			dmp = home.clickdocumentsmenu();
			boolean doctypecreation = dmp.createDocType(doctype);
			TestValidation.IsTrue(doctypecreation, 
					"Navigated to Home-> Document Page & Created document type - " + doctype , 
					"Failed to create document type");

			boolean assign = dmp.manageLocationLink(uploadlinkdoc,doctype,linkdoc1,4,locationInstanceValue);
			TestValidation.IsTrue(assign, 
					"Succesfully linked document "+doctype+" to location"+locationInstanceValue ,
					"Failed to link document to location");

			home.clickTaskSchedulerMenu();

			boolean viewByTaskBtnDisplayed= controlActions.isElementDisplayed(taskScheduler.ViewByTaskBtn);
			TestValidation.IsTrue(viewByTaskBtnDisplayed, 
					"Navigated to Task Scheduler Page !!", 
					"Failed to navigate to Task Scheduler Page");

			controlActions.refreshPage();

			boolean searchForm = taskScheduler.searchData(tsd);
			TestValidation.IsTrue(searchForm, 
					"SEARCHED form " + formName + " succesfully !!",
					"Failed to SEARCH form " + formName);
			
			tsd.formORdocument = "doc";
			tsd.formORdocumentvalue=linkdoc1;

			boolean searchDocument = taskScheduler.searchData(tsd);
			TestValidation.IsTrue(searchDocument, 
					"SEARCHED document and succesfully filled step1 succesfully !!",
					"Failed to SEARCH document ");

			boolean fillData2 = taskScheduler.fillstepTwoDocData(tsd);
			TestValidation.IsTrue(fillData2, 
					"SELECTED Resource and Workgroup for Schedule " + DailyTaskName,
					"Could NOT select Resource and Workgroup for Schedule " + DailyTaskName);

			boolean fillData3 = taskScheduler.fillStepThreeDataForDaily(tsd, day, interval);
			TestValidation.IsTrue(fillData3, 
					"Succesfully Created Daily Schedule after searching document succesfully" + DailyTaskName,
					"Could NOT create Daily schedule" + DailyTaskName);
		}
		finally {
			if(!fo.renameFile(linkdoc1,linkdoc)){
				TCGFailureMsg = "Failed to rename Document in user directory";
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}
				if(!controlActions.refreshDisplayedPage()){
					TCGFailureMsg = "Could NOT refresh application's page";
					TestValidation.Fail(TCGFailureMsg);
				}
			
			
		}
	}

	@Test(description = "Task Scheduler - Schedule a task Daily repetition-(Daily)(15808)")
	public void TestCase_22960() throws ParseException, InterruptedException {

		try {
		controlActions.refreshPage();
		tsd.taskoccure = "Daily";		
		tsd.haveEndDate = true;
		tsd.taskname = CommonMethods.dynamicText("Daily_");//"Daily_28.10_12.10.38";
		tsd.formORdocument = "form";
		tsd.formORdocumentvalue =formName;//"TaskScheduler01234_28.10_12.10.38";//
		int day=8;
		int interval=4;
		DateTime dt = new DateTime();
		String currentDate = dt.getTodayDate("MM/dd/YYYY", dt.getTimezone(adminTimezone));

		home.clickTaskSchedulerMenu();
		boolean viewByTaskBtnDisplayed= controlActions.isElementDisplayed(taskScheduler.ViewByTaskBtn);
		TestValidation.IsTrue(viewByTaskBtnDisplayed, 
				"Navigated to Task Scheduler Page !!", 
				"Failed to navigate to Task Scheduler Page");

		boolean fillData1 = taskScheduler.fillstepOneData(tsd);
		TestValidation.IsTrue(fillData1, 
				"SELECTED Location and Form for Schedule " + tsd.taskname,
				"Could NOT select Location and Form for Schedule " + tsd.taskname);

		boolean fillData2 = taskScheduler.fillstepTwoData(tsd);
		TestValidation.IsTrue(fillData2, 
				"SELECTED Resource and Workgroup for Schedule " + tsd.taskname,
				"Could NOT select Resource and Workgroup for Schedule " + tsd.taskname);

		boolean fillData3 = taskScheduler.fillStepThreeDataForDaily(tsd, day, interval);
		TestValidation.IsTrue(fillData3, 
				"CREATED Daily Schedule " + DailyTaskName,
				"Could NOT create Daily schedule" + DailyTaskName);
		

		boolean setLoc = taskScheduler.selectLocationForGrid(tsd.locationvalue);
		TestValidation.IsTrue(setLoc, 
				"SELECTED Location " + tsd.locationvalue + " for Task Scheduler grid",
				"Could NOT select Location " + tsd.locationvalue + " for Task Scheduler grid");

		boolean verifyDaily = taskScheduler.verifyScheduleInTaskView(tsd.taskname );
		TestValidation.IsTrue(verifyDaily, 
				"VERIFIED Daily Schedule " + tsd.taskname,
				"Could NOT verify Daily schedule " + tsd.taskname);

		boolean verifyDueDate = taskScheduler.verifyTaskDueDate(tsd, day, interval, currentDate);
		TestValidation.IsTrue(verifyDueDate, 
				"VERIFIED, "+ tsd.taskname +" task is active till given enddate "  ,
				"Failed,  to verify if given task " + tsd.taskname + " is active till end date");

		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				TestValidation.Fail(TCGFailureMsg);
			}
		}
	}

	@Test(description = "Task Scheduler - Schedule a task without any repetition-(One Time Only)(15807)")
	public void TestCase_22961() throws ParseException, InterruptedException {

try {
		controlActions.refreshPage();
		tsd.taskoccure = "One Time Only";		
		tsd.haveEndDate = true;
		tsd.taskname = CommonMethods.dynamicText("One_Time_Only_");
		tsd.formORdocument = "form";
		tsd.formORdocumentvalue = formName;

		home.clickTaskSchedulerMenu();
		boolean viewByTaskBtnDisplayed= controlActions.isElementDisplayed(taskScheduler.ViewByTaskBtn);
		TestValidation.IsTrue(viewByTaskBtnDisplayed, 
				"Navigated to Task Scheduler Page !!", 
				"Failed to navigate to Task Scheduler Page");

		boolean fillData1 = taskScheduler.fillstepOneData(tsd);
		TestValidation.IsTrue(fillData1, 
				"SELECTED Location and Form for Schedule " + tsd.taskname,
				"Could NOT select Location and Form for Schedule " + tsd.taskname);

		boolean fillData2 = taskScheduler.fillstepTwoData(tsd);
		TestValidation.IsTrue(fillData2, 
				"SELECTED Resource and Workgroup for Schedule " + tsd.taskname,
				"Could NOT select Resource and Workgroup for Schedule " + tsd.taskname);

		boolean fillData3 = taskScheduler.fillstepThreeData(tsd);
		TestValidation.IsTrue(fillData3, 
				"CREATED One Time Schedule " + tsd.taskname,
				"Could NOT create One Time Schedule" + tsd.taskname);

		boolean setLoc = taskScheduler.selectLocationForGrid(tsd.locationvalue);
		TestValidation.IsTrue(setLoc, 
				"SELECTED Location " + tsd.locationvalue + " for Task Scheduler grid",
				"Could NOT select Location " + tsd.locationvalue + " for Task Scheduler grid");

		boolean clickedAllBtn = taskScheduler.clickAllTasks();
		TestValidation.IsTrue(clickedAllBtn, 
				"CLICKED on All button to display all Schedules",
				"Could NOT click on All button to display all Schedules");

		boolean verifyOneTime = taskScheduler.verifyInScheduleView(tsd.taskname);
		TestValidation.IsTrue(verifyOneTime, 
				"VERIFIED One Time Schedule " + tsd.taskname,
				"Could NOT verify One Time schedule" + tsd.taskname);
}
finally {
	if(!controlActions.refreshDisplayedPage()){
		TCGFailureMsg = "Could NOT refresh application's page";
		TestValidation.Fail(TCGFailureMsg);
	}
}
	}

	@Test(description = "Task Scheduler - Schedule a task Monthly repetition-(Monthly )(15810)")
	public void TestCase_22963() throws ParseException, InterruptedException {

		try {
		controlActions.refreshPage();
		tsd.taskoccure = "Monthly";
		tsd.haveEndDate = true;
		tsd.taskname = CommonMethods.dynamicText("Monthly_Task_");
	tsd.formORdocument = "form";
	tsd.formORdocumentvalue = formName;
		int day=90;
		int interval=1;

		home.clickTaskSchedulerMenu();
		boolean viewByTaskBtnDisplayed= controlActions.isElementDisplayed(taskScheduler.ViewByTaskBtn);
		TestValidation.IsTrue(viewByTaskBtnDisplayed, 
				"Navigated to Task Scheduler Page !!", 
				"Failed to navigate to Task Scheduler Page");

		boolean fillData1 = taskScheduler.fillstepOneData(tsd);
		TestValidation.IsTrue(fillData1, 
				"SELECTED Location and Form for Schedule " + tsd.taskname,
				"Could NOT select Location and Form for Schedule " + tsd.taskname);

		boolean fillData2 = taskScheduler.fillstepTwoData(tsd);
		TestValidation.IsTrue(fillData2, 
				"SELECTED Resource and Workgroup for Schedule " + tsd.taskname,
				"Could NOT select Resource and Workgroup for Schedule " + tsd.taskname);

		boolean fillData3 = taskScheduler.fillStepThreeMonthlyData(tsd, day, interval);
		TestValidation.IsTrue(fillData3, 
				"CREATED Monthly Schedule " + tsd.taskname,
				"Could NOT create Monthly schedule" + tsd.taskname);

		boolean setLoc = taskScheduler.selectLocationForGrid(tsd.locationvalue);
		TestValidation.IsTrue(setLoc, 
				"SELECTED Location " + tsd.locationvalue + " for Task Scheduler grid",
				"Could NOT select Location " + tsd.locationvalue + " for Task Scheduler grid");

		boolean verifyMonthly = taskScheduler.verifyScheduleInTaskView(tsd.taskname);
		TestValidation.IsTrue(verifyMonthly, 
				"VERIFIED Monthly Schedule " + tsd.taskname,
				"Could NOT verify Monthly schedule " + tsd.taskname);
		inp = home.clickInboxMenu();
		boolean viewTask = inp.selectFormTask(tsd.taskname);		
		TestValidation.IsTrue(viewTask, 
				"Form task selected" + tsd.taskname, 
				"Could NOT select form task" + tsd.taskname);
		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				TestValidation.Fail(TCGFailureMsg);
			}
		}
	}

	@Test(description = "Task Scheduler - Schedule a task Yearlyrepetition-(Yearly)(15811)")
	public void TestCase_22964() throws ParseException, InterruptedException {
try {
		controlActions.refreshPage();
		tsd.taskoccure = "Yearly";
		tsd.haveEndDate = true;
		tsd.taskname = CommonMethods.dynamicText("Yearly_");
	tsd.formORdocument = "form";
	tsd.formORdocumentvalue = formName;
		int year=3;
		int interval=1;

		home.clickTaskSchedulerMenu();
		boolean viewByTaskBtnDisplayed= controlActions.isElementDisplayed(taskScheduler.ViewByTaskBtn);
		TestValidation.IsTrue(viewByTaskBtnDisplayed, 
				"Navigated to Task Scheduler Page !!", 
				"Failed to navigate to Task Scheduler Page");

		boolean fillData1 = taskScheduler.fillstepOneData(tsd);
		TestValidation.IsTrue(fillData1, 
				"SELECTED Location and Form for Schedule " + tsd.taskname,
				"Could NOT select Location and Form for Schedule " + tsd.taskname);

		boolean fillData2 = taskScheduler.fillstepTwoData(tsd);
		TestValidation.IsTrue(fillData2, 
				"SELECTED Resource and Workgroup for Schedule " + tsd.taskname,
				"Could NOT select Resource and Workgroup for Schedule " + tsd.taskname);

		boolean fillData3 = taskScheduler.fillStepThreeYearlyData(tsd, year, interval);
		TestValidation.IsTrue(fillData3, 
				"CREATED Yearly Schedule " + tsd.taskname,
				"Could NOT create Yearly schedule" + tsd.taskname);

		boolean setLoc = taskScheduler.selectLocationForGrid(tsd.locationvalue);
		TestValidation.IsTrue(setLoc, 
				"SELECTED Location " + tsd.locationvalue + " for Task Scheduler grid",
				"Could NOT select Location " + tsd.locationvalue + " for Task Scheduler grid");

		boolean verifyYearly = taskScheduler.verifyScheduleInTaskView(tsd.taskname);
		TestValidation.IsTrue(verifyYearly, 
				"VERIFIED Yearly Schedule " + tsd.taskname,
				"Could NOT verify Yearly schedule " + tsd.taskname);
		inp = home.clickInboxMenu();
		boolean viewTask = inp.selectFormTask(tsd.taskname);		
		TestValidation.IsTrue(viewTask, 
				"Form task selected" + tsd.taskname, 
				"Could NOT select form task" + tsd.taskname);
}
finally {
	if(!controlActions.refreshDisplayedPage()){
		TCGFailureMsg = "Could NOT refresh application's page";
		TestValidation.Fail(TCGFailureMsg);
	}
}
	}
	
	@Test(description = "Task Scheduler|| Verify the functionality for single task in detail view when clicked on Recall ")	
	public void TestCase_38380() {
try {
	
		controlActions.refreshPage();
		tsd.taskoccure = "Daily";		
		tsd.haveEndDate = true;
		tsd.taskname = CommonMethods.dynamicText("Recall_Task_");
	tsd.formORdocument = "form";
	tsd.formORdocumentvalue = formName;
		
		home.clickTaskSchedulerMenu();
		boolean fillData1 = taskScheduler.fillstepOneData(tsd);
		TestValidation.IsTrue(fillData1, 
				"SELECTED Location and Form for Schedule " + tsd.taskname,
				"Could NOT select Location and Form for Schedule " + tsd.taskname);

		boolean fillData2 = taskScheduler.fillstepTwoData(tsd);
		TestValidation.IsTrue(fillData2, 
				"SELECTED Resource and Workgroup for Schedule " + tsd.taskname,
				"Could NOT select Resource and Workgroup for Schedule " + tsd.taskname);

		boolean fillData3 = taskScheduler.fillDuetime(tsd, 3);
		TestValidation.IsTrue(fillData3, 
				"CREATED Daily Schedule " + tsd.taskname,
				"Could NOT create Daily schedule" + tsd.taskname);
		//threadsleep(60000);
		controlActions.refreshPage();

		boolean openTaskView = taskScheduler.taskView();
		TestValidation.IsTrue(openTaskView , 
				"Navigated to Task Scheduler Page and select View By Task " ,
				"Navigate to Task Scheduler Page but Failed to select View By Task ");

		boolean setLoc = taskScheduler.selectLocationForGrid(tsd.locationvalue);
		TestValidation.IsTrue(setLoc, 
				"SELECTED Location " + tsd.locationvalue + " for Task Scheduler grid",
				"Could NOT select Location " + tsd.locationvalue + " for Task Scheduler grid");

		boolean recall = taskScheduler.recallTask(tsd.taskname);	
		TestValidation.IsTrue(recall, 
				"Task recalled " + tsd.taskname,
				"Could NOT recall Task " +tsd.taskname);

		boolean verifyRecallTask=taskScheduler.verifyTaskStatusInDetailView(tsd.taskname, Task_Status.RECALLED);
		TestValidation.IsTrue(verifyRecallTask, 
				"Task status is "+ Task_Status.RECALLED,
				"Failed, task status is not "+Task_Status.RECALLED);

		boolean checkRecallTaskLinkIsctive=taskScheduler.checkLinkIsActive(tsd.taskname);
		TestValidation.IsFalse(checkRecallTaskLinkIsctive, 
				"Recall Task link is inactive ",
				"Failed, Recall Task Link is active ");

		inp = home.clickInboxMenu();

		boolean enterTask = inp.enterTaskName(tsd.taskname);
		TestValidation.IsTrue(enterTask,
				"Navigated to Inbox Page and succesfully ENTERED Task -> " + tsd.taskname ,
				"Navigated to Inbox Page but COULD NOT ENTER Task -> " + tsd.taskname );

		boolean searchTask = inp.clickSearchButton();
		TestValidation.IsTrue(searchTask,
				"SEARCHED Task -> " + tsd.taskname ,
				"COULD NOT SEARCH Task -> " + tsd.taskname );

		boolean verifytask = inp.verifySingleTask(tsd.taskname);
		TestValidation.IsFalse(verifytask,
				"VERIFIED-> " + tsd.taskname + " task is not displayed in Inbox" ,
				"Failed, " + tsd.taskname + " task is displayed in Inbox" );
}
finally {
	if(!controlActions.refreshDisplayedPage()){
		TCGFailureMsg = "Could NOT refresh application's page";
		TestValidation.Fail(TCGFailureMsg);
	}
}
	}

	@Test(description=" Task Scheduler|| Verify the functionality for single task in detail view "
			+ "when clicked on Skip")
	public void TestCase_38381() {
		try {
			
		tsd.taskoccure = "Daily";
		tsd.haveEndDate = true;
		tsd.taskname = CommonMethods.dynamicText("Skip_Task_");
	tsd.formORdocument = "form";
	tsd.formORdocumentvalue = formName;

		TaskSchedulerPage tsp =home.clickTaskSchedulerMenu();

		boolean viewByTaskBtnDisplayed= controlActions.isElementDisplayed(tsp.ViewByTaskBtn);
		TestValidation.IsTrue(viewByTaskBtnDisplayed, 
				"Navigated succesfully to Task Scheduler Page", 
				"Failed to verify 'View By Task' button is displayed");

		boolean clickTaskView = taskScheduler.taskView();
		TestValidation.IsTrue(clickTaskView, 
				"Clicked ViewbyTask Btn succesfully" ,
				"Failed to click ViewbyTask Btn ");

		boolean fillData1 = taskScheduler.fillstepOneData(tsd);
		TestValidation.IsTrue(fillData1, 
				"SELECTED Location and Form for Schedule " + tsd.taskname,
				"Could NOT select Location and Form for Schedule " + tsd.taskname);

		boolean fillData2 = taskScheduler.fillstepTwoData(tsd);
		TestValidation.IsTrue(fillData2, 
				"SELECTED Resource and Workgroup for Schedule " + tsd.taskname,
				"Could NOT select Resource and Workgroup for Schedule " + tsd.taskname);

		boolean fillData3 = taskScheduler.fillDuetime(tsd, 4);
		TestValidation.IsTrue(fillData3, 
				"CREATED Daily Schedule " + tsd.taskname,
				"Could NOT create Daily schedule" + tsd.taskname);

		boolean setLoc = taskScheduler.selectLocationForGrid(tsd.locationvalue);
		TestValidation.IsTrue(setLoc, 
				"SELECTED Location " + tsd.locationvalue + " for Task Scheduler grid",
				"Could NOT select Location " + tsd.locationvalue + " for Task Scheduler grid");

		boolean skipTask=taskScheduler.skipTask(tsd.taskname);
		TestValidation.IsTrue(skipTask, 
				"Task skipped succesfully ",
				"Failed to skip the task");

		boolean verifyTaskStatusInGridView=taskScheduler.verifyTaskStatusInGridView(Task_Status.TOBESKIPPED);
		TestValidation.IsTrue(verifyTaskStatusInGridView, 
				"Task status is 'Skipped' in Grid View ",
				"Failed, Task status is not 'Skipped'in Grid View");

		boolean verifyTaskStatus1=taskScheduler.verifyTaskStatusInDetailView(tsd.taskname, Task_Status.TOBESKIPPED);
		TestValidation.IsTrue(verifyTaskStatus1, 
				"Task status is "+ Task_Status.TOBESKIPPED,
				"Failed, task status is not "+Task_Status.TOBESKIPPED);

		boolean verifyTaskStatus=taskScheduler.verifyTaskStatusInDetailView(tsd.taskname, Task_Status.SKIPPED);
		TestValidation.IsTrue(verifyTaskStatus, 
				"Task status is "+ Task_Status.SKIPPED,
				"Failed, task status is not "+Task_Status.SKIPPED);
		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				TestValidation.Fail(TCGFailureMsg);
			}
		}
	}	

	@Test(description=" Task Scheduler|| Verify the functionality for single task in detail view\r\n"
			+ "when clicked on Update")
	public void TestCase_38382() {
		try {
			
		tsd.taskoccure = "Daily";
		tsd.haveEndDate = true;
		tsd.taskname = CommonMethods.dynamicText("Update_TestWG_");
	tsd.formORdocument = "form";
	tsd.formORdocumentvalue = formName;
		String wgName1=CommonMethods.dynamicText("Update_WG");
		
		boolean createWorkGroup =wgp.createWorkGroup(wgName1, adminUsername);
		TestValidation.IsTrue(createWorkGroup, 
				"Created new Workgroup  " + wgName1 ,
				"Could NOT create new Workgroup  " + wgName1);

		TaskSchedulerPage tsp =home.clickTaskSchedulerMenu();

		boolean viewByTaskBtnDisplayed= controlActions.isElementDisplayed(tsp.ViewByTaskBtn);
		TestValidation.IsTrue(viewByTaskBtnDisplayed, 
				"Navigated succesfully to Task Scheduler Page", 
				"Failed to verify 'View By Task' button is displayed");

		boolean fillData1 = taskScheduler.fillstepOneData(tsd);
		TestValidation.IsTrue(fillData1, 
				"SELECTED Location and Form for Schedule " + tsd.taskname,
				"Could NOT select Location and Form for Schedule " + tsd.taskname);

		boolean fillData2 = taskScheduler.fillstepTwoData(tsd);
		TestValidation.IsTrue(fillData2, 
				"SELECTED Resource and Workgroup for Schedule " + tsd.taskname,
				"Could NOT select Resource and Workgroup for Schedule " + tsd.taskname);

		boolean fillData3 = taskScheduler.fillDuetime(tsd, 3);
		TestValidation.IsTrue(fillData3, 
				"CREATED Daily Schedule " + tsd.taskname,
				"Could NOT create Daily schedule" + tsd.taskname);
	
		boolean viewEditOptnDisplay= tsp.isScheduleEditable(tsd.locationvalue ,tsd.taskname);
		TestValidation.IsTrue(viewEditOptnDisplay , 
				"Task is editable", 
				"Failed to edit task");
		
		// update workgroup	 
		boolean updateWorkGroup = taskScheduler.updateWorkGroup(tsd.workGroup, wgName1);
		TestValidation.IsTrue(updateWorkGroup, 
				"Updated workgroup from" + tsd.workGroup + " to" +wgName1,
				"FAILED to Update work group to " + wgName1 );
		

		// click update btn
		boolean clickUpdateBtn = taskScheduler.clickUpdateBtn();
		TestValidation.IsTrue(clickUpdateBtn, 
				"Clicked Update Button",
				"Failed to click Update Button");
		
		// check updated workgroup  & resource visible in view by grid and view by task
		boolean verifyUpdatedWorkGroupValue = taskScheduler.verifyUpdatedWorkGroupValue(wgName1, tsd.taskname);
		TestValidation.IsTrue(verifyUpdatedWorkGroupValue, 
				"Verified Updated WorkGroup",
				"Failed to verify Updated Work Group");
		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				TestValidation.Fail(TCGFailureMsg);
			}
		}
	}	


	@AfterClass(alwaysRun = true)
	public void closeBrowser() throws InterruptedException {
		driver.close();
	}

}
