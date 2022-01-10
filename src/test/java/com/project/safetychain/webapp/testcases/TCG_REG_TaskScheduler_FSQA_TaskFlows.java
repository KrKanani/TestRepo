package com.project.safetychain.webapp.testcases;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.project.safetychain.webapp.pageobjects.CommonPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserFormsPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage;
import com.project.safetychain.webapp.pageobjects.HomePage;
import com.project.safetychain.webapp.pageobjects.LoginPage;
import com.project.safetychain.webapp.pageobjects.ManageLocationPage;
import com.project.safetychain.webapp.pageobjects.ManageResourcePage;
import com.project.safetychain.webapp.pageobjects.ResourceDesignerPage;
import com.project.safetychain.webapp.pageobjects.UserManagerPage;
import com.project.safetychain.webapp.pageobjects.WorkGroupsPage;
import com.project.safetychain.webapp.pageobjects.CommonPage.TIMEZONE;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.COLUMNHEADER;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.COLUMNSETTING;
import com.project.safetychain.webapp.pageobjects.FSQABrowserTaskPage;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FIELD_TYPES;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FORMRESOURCETYPES;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FORMTYPES;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FormDesignParams;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FormFieldParams;
import com.project.safetychain.webapp.pageobjects.ResourceDesignerPage.CATEGORYTYPES;
import com.project.safetychain.webapp.pageobjects.TaskSchedulerPage.TaskScheduleDetails;
import com.project.safetychain.webapp.pageobjects.TaskSchedulerPage.Task_Status;
import com.project.safetychain.webapp.pageobjects.TaskSchedulerPage.ViewBySchedule_Menu;
import com.project.safetychain.webapp.pageobjects.TaskSchedulerPage;
import com.project.safetychain.webapp.pageobjects.UserManagerPage.UsrDetailParams;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.utilities.CommonMethods;
import com.project.utilities.ControlActions;
import com.project.utilities.DateTime;

public class TCG_REG_TaskScheduler_FSQA_TaskFlows extends TestBase{
	ControlActions controlActions;
	CommonPage common;	
	LoginPage login;
	HomePage home;
	DateTime dateTime;	
	FormDesignerPage fdp;
	FSQABrowserPage fbp;
	WorkGroupsPage wgp;
	ResourceDesignerPage rdp;
	ManageLocationPage locations;
	ManageResourcePage manageResource;
	FSQABrowserFormsPage fsqaFormsPage;
	FSQABrowserTaskPage fsqaTasksPage;
	DateTime datetime;
	UserManagerPage manageUser;
	TaskSchedulerPage tsp;

	public static String locationCategoryValue;
	public static String locationInstanceValue;
	public static String locationInstanceValue2;
	public static String customersCategoryValue;
	public static String customersInstanceValue;
	public static String equipmentCategoryValue;
	public static String equipmentInstanceValue;
	public static String itemsCategoryValue;
	public static String itemsInstanceValue;
	public static String suppliersCategoryValue;
	public static String suppliersInstanceValue;
	public static String formName;
	public static String wgName;
	public static String cformName,aformName,qformName;	
	public static String numericFN = "Numeric";	
	public static String cctask,catask,cqtask,ectask,eatask,eqtask,ictask,iatask,iqtask,sctask,satask,sqtask,duebyTask,locTask;
	public static String displayName = null;
	public static String timezoneCode = null;
	public static String usrTimezone = null;
	public static String TaskDescription;

	public static String roleName;
	public static String userUN;
	public static String userFN;
	public static String userLN;
	public static List<String> userLocation;
	public static List<String> userRole , workgroup;
	public static String userPassword, userNewPassword;
	UsrDetailParams udp;
	public static String schedulename, schedulename1;
	public static String filtertask;

	@BeforeClass(alwaysRun = true)
	public void groupInit() throws InterruptedException {

		locationCategoryValue = CommonMethods.dynamicText("LC");
		locationInstanceValue = CommonMethods.dynamicText("LI");
		locationInstanceValue2 = CommonMethods.dynamicText("LI2");
		customersCategoryValue = CommonMethods.dynamicText("CC");
		customersInstanceValue = CommonMethods.dynamicText("CI");
		equipmentCategoryValue = CommonMethods.dynamicText("EC");
		equipmentInstanceValue = CommonMethods.dynamicText("EI");
		itemsCategoryValue = CommonMethods.dynamicText("IC");
		itemsInstanceValue = CommonMethods.dynamicText("II");
		suppliersCategoryValue = CommonMethods.dynamicText("SC");
		suppliersInstanceValue = CommonMethods.dynamicText("SI");
		TaskDescription = CommonMethods.dynamicText("Task Description");
		wgName = CommonMethods.dynamicText("WG");;
		cformName = CommonMethods.dynamicText("Check");
		aformName = CommonMethods.dynamicText("Audit");
		qformName = CommonMethods.dynamicText("Quest");
		cctask = CommonMethods.dynamicText("CCTask");
		catask = CommonMethods.dynamicText("CATask");
		cqtask = CommonMethods.dynamicText("CQTask");
		ectask = CommonMethods.dynamicText("ECTask");
		eatask = CommonMethods.dynamicText("EATask");
		eqtask = CommonMethods.dynamicText("EQTask");
		ictask= CommonMethods.dynamicText("ICTask");
		iatask =CommonMethods.dynamicText("IATask");
		iqtask = CommonMethods.dynamicText("IQTask");
		sctask = CommonMethods.dynamicText("SCTask");
		satask = CommonMethods.dynamicText("SATask");
		sqtask = CommonMethods.dynamicText("SQTask");
		duebyTask = CommonMethods.dynamicText("TaskDueBy");
		locTask = CommonMethods.dynamicText("LocTask");
		schedulename = CommonMethods.dynamicText("Schedule");
		schedulename1 = CommonMethods.dynamicText("Schedule1");
		filtertask = CommonMethods.dynamicText("FilterTask");
		formName= CommonMethods.dynamicText("TaskSchedlr");

		driver = launchbrowser();
		controlActions = new ControlActions(driver);
		controlActions.getUrl(applicationUrl);
		home = new HomePage(driver);
		login = new LoginPage(driver);
		dateTime = new DateTime(driver);
		fdp = new FormDesignerPage(driver);
		wgp = new WorkGroupsPage(driver);
		fbp = new FSQABrowserPage(driver);
		rdp = new ResourceDesignerPage(driver);
		locations = new ManageLocationPage(driver);
		manageResource = new ManageResourcePage(driver);		
		fsqaFormsPage = new FSQABrowserFormsPage(driver);
		fsqaTasksPage = new FSQABrowserTaskPage(driver);
		tsp = new TaskSchedulerPage(driver);
		
		login.performLogin(adminUsername, adminPassword);
		if(home.error) {
			TCGFailureMsg = "Could NOT login to application";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		displayName = home.getLoggedInUserDetails();
		if(displayName == ""){
			TCGFailureMsg = "Could NOT get display name for user - " + adminUsername;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		String supplierInstances[][] = {{suppliersInstanceValue,"true"}};
		String itemInstances[][] = {{itemsInstanceValue,"true"}};
		String customerInstances[][]= {{customersInstanceValue,"true"}};
		String equipmentInstances[][]= {{equipmentInstanceValue,"true"}};

		HashMap<String,String> resourceCategories = new HashMap<String,String>();
		resourceCategories.put(CATEGORYTYPES.LOCATION, locationCategoryValue);
		resourceCategories.put(CATEGORYTYPES.ITEMS, itemsCategoryValue);
		resourceCategories.put(CATEGORYTYPES.SUPPLIERS, suppliersCategoryValue);
		resourceCategories.put(CATEGORYTYPES.EQUIPMENT, equipmentCategoryValue);
		resourceCategories.put(CATEGORYTYPES.CUSTOMERS, customersCategoryValue);

		if(!rdp.createCategory(resourceCategories)) {
			TCGFailureMsg = "Could NOT create Locations/Items/Suppliers/Customers/Equipments category - ";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		if(!locations.createLocationInstanceLinkUser(locationCategoryValue,locationInstanceValue,adminUsername)) {
			TCGFailureMsg = "Could NOT create location instance - " + locationCategoryValue;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		if(!locations.createLocationInstance(locationCategoryValue,locationInstanceValue2)) {
			TCGFailureMsg = "Could NOT create location instance - " + locationCategoryValue;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
		
		if(!manageResource.addResourceInstances(FORMRESOURCETYPES.ITEMS, itemsCategoryValue, itemInstances, true, locationInstanceValue)) {
			TCGFailureMsg = "Could NOT create resource " + itemsCategoryValue + " & link Location - "+ locationInstanceValue;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		if(!manageResource.addResourceInstances(FORMRESOURCETYPES.SUPPLIERS, suppliersCategoryValue, supplierInstances,true, locationInstanceValue)) {
			TCGFailureMsg = "Could NOT create resource " + suppliersCategoryValue + " & link Location - "+ locationInstanceValue;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		if(!manageResource.linkItemSupplier(itemsInstanceValue)) {
			TCGFailureMsg = "Could NOT link supplier resource " + suppliersInstanceValue + " with item resource - "+ itemsInstanceValue;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}


		if(!manageResource.addResourceInstances(FORMRESOURCETYPES.EQUIPMENT, equipmentCategoryValue, equipmentInstances,true, locationInstanceValue)) {
			TCGFailureMsg = "Could NOT create resource " + equipmentCategoryValue + " & link Location - "+ locationInstanceValue;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		if(!manageResource.addResourceInstances(FORMRESOURCETYPES.CUSTOMERS, customersCategoryValue , customerInstances,true, locationInstanceValue)) {
			TCGFailureMsg = "Could NOT create resource " + customersCategoryValue + " & link Location - "+ locationInstanceValue;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		if(!manageResource.linkToLocation(locationInstanceValue2)) {
			TCGFailureMsg = "Could NOT link resource" + customersInstanceValue + " & Location - "+ locationInstanceValue2;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		//FORM - Creation and Release
		HashMap<String, List<String>> fields = new HashMap<String, List<String>>();
		fields.put(FIELD_TYPES.NUMERIC, Arrays.asList("Number"));
		fields.put(FIELD_TYPES.FREE_TEXT, Arrays.asList("FText"));
		fields.put(FIELD_TYPES.DATE_TIME, Arrays.asList("D&T"));

		FormFieldParams ffp = new FormFieldParams();
		ffp.FieldDetails = fields;

		HashMap<String, List<String>> resource = new HashMap<String, List<String>>();
		resource.put(FORMRESOURCETYPES.CUSTOMERS, Arrays.asList(customersCategoryValue));

		FormDesignParams fdpDets = new FormDesignParams();
		fdpDets.FormType = FORMTYPES.CHECK;
		fdpDets.FormName = formName;
		fdpDets.SelectResources = resource;//FSQARESOURCE.CUSTOMERS;
		fdpDets.DesignFields = Arrays.asList(ffp);
		fdpDets.ReleaseForm = true;

		FormDesignerPage fdp = home.clickFormDesignerMenu();
		if(!fdp.createAndReleaseForm(fdpDets)) {
			TCGFailureMsg = "Could NOT create unreleased form - " + cformName;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		if(!wgp.createWorkGroup(wgName, adminUsername)) {
			TCGFailureMsg = "Could not create workgroup - " + wgName;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		home.clickTaskSchedulerMenu();
		TaskScheduleDetails tsd = new TaskScheduleDetails();
		tsd.taskoccure = "Daily";
		tsd.resource = customersInstanceValue;
		tsd.workGroup = wgName;
		tsd.haveEndDate = true;
		tsd.formORdocument = "form";
		tsd.formORdocumentvalue = formName;
		tsd.taskDescription = TaskDescription;
		tsd.locationvalue = locationInstanceValue;
		tsd.taskname = schedulename;

		if(!tsp.createTaskSchedule(tsd,tsd.taskname,tsd.locationvalue)) {
			TCGFailureMsg = "Could NOT create schedule - " + schedulename;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
		
		controlActions.refreshPage();

		TaskScheduleDetails tsd1 = new TaskScheduleDetails();
		tsd1.taskoccure = "Daily";
		tsd1.resource = customersInstanceValue;
		tsd1.workGroup = wgName;
		tsd1.haveEndDate = true;
		tsd1.formORdocument = "form";
		tsd1.formORdocumentvalue = formName;
		tsd1.taskDescription = TaskDescription;
		tsd1.locationvalue = locationInstanceValue2;
		tsd1.taskname = schedulename1;

		if(!tsp.createTaskSchedule(tsd1,tsd1.taskname,tsd1.locationvalue)) {
			TCGFailureMsg = "Could NOT create schedule - " + schedulename;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
	}

	@Test(description="Task Scheduler - Tasks created from the Task Scheduler "
			+ "module should also be displayed in the Task Monitoring module of the FSQA Browser (20454)")
	public void TestCase_22316()  throws InterruptedException {

		try {
		TaskScheduleDetails tsd = new TaskScheduleDetails();
		tsd.locationvalue = locationInstanceValue;//"Akola";
		tsp=home.clickTaskSchedulerMenu();
		
		
		//schedulename="Schedule_due";//"test sched";

		boolean viewByTaskBtnDisplayed= controlActions.isElementDisplayed(tsp.ViewByTaskBtn);
		TestValidation.IsTrue(viewByTaskBtnDisplayed, 
				"Navigated to Task Scheduler Page", 
				"Failed to navigate to Task Scheduler Page");

		tsp.selectLocationForGrid(tsd.locationvalue);
		logInfo("Succesfully selected location"+tsd.locationvalue);

		boolean taskStatus=tsp.verifyTaskStatusColumn(schedulename,ViewBySchedule_Menu.STATUS, Task_Status.ACTIVE );
		TestValidation.IsTrue(taskStatus, 
				schedulename+" Task is Active", 
				"Failed, "+schedulename +" task is not Active ");

		home.clickFSQABrowserMenu();

		boolean navigateFSQABrowser = fbp.selectResourceDropDownandNavigate(FORMRESOURCETYPES.CUSTOMERS,"Tasks");
		TestValidation.IsTrue(navigateFSQABrowser, 
				"Navigated to FSQABrowser>TaskTab", 
				"Failed to navigate to FSQABrowser>Task Tab");

		boolean viewTask = fbp.openAndApplySettingsForColumn(COLUMNHEADER.TASKNAME, COLUMNSETTING.FILTER,schedulename);
		TestValidation.IsTrue(viewTask, 
				"All tasks are visible", 
				"Failed to verify all tasks");

		boolean verifyTaskGrid = fsqaTasksPage.verifytaskonGrid(true,schedulename);
		TestValidation.IsTrue(verifyTaskGrid, 
				"Task created via Task Scheduler is visible", 
				"Task created via Task Scheduler is not visible");

		boolean viewCaretDropDwn=fsqaTasksPage.verifyCaretDropDownOptions(schedulename);
		TestValidation.IsTrue(viewCaretDropDwn, 
				"All 4 menus are present in Dropdown", 
				"Failed, to view all 4 options in Dropdown ");
		}
		finally {
			controlActions.refreshPage();
		}
		
	}

	@Test(description="Task Scheduler - Task Monitoring - Task Monitoring Actions (Recall, Reassign, Change Due Date, View Task History) "
			+ "should be visible only for tasks that have a status other than \"Scheduled\" (20353)")
	public void TestCase_22318()  throws InterruptedException {

		try {
		String dateFormat = "MM/dd/yyyy";
		String currentDate = dateTime.getTodayDate(dateFormat, TIMEZONE.REPUBLICOFINDIA);
		//String updatedDate = dateTime.addSubtractDaysFromDate(dateTime.getDate(currentDate, dateFormat),+0, dateFormat);
		
		TaskScheduleDetails tsd = new TaskScheduleDetails();
		tsp=home.clickTaskSchedulerMenu();

		boolean viewByTaskBtnDisplayed= controlActions.isElementDisplayed(tsp.ViewByTaskBtn);
		TestValidation.IsTrue(viewByTaskBtnDisplayed, 
				"Navigated to Task Scheduler Page", 
				"Failed to navigate to Task Scheduler Page");

		tsd.locationvalue=locationInstanceValue;//"Akola";

		tsp.selectLocationForGrid(tsd.locationvalue);
		logInfo("Succesfully selected location"+tsd.locationvalue);

		tsp.clickAllTasks();
		logInfo("Succesfully clicked ALL toggle button");		
		//schedulename="Schedule_due";//"test sched";

		boolean taskStatus=tsp.verifyTaskStatusColumn(schedulename,ViewBySchedule_Menu.STATUS, Task_Status.SCHEDULED );
		TestValidation.IsTrue(taskStatus, 
				schedulename+"Task  have Scheduled due", 
				"Failed,"+schedulename+" task don't have Scheduled due ");

		home.clickFSQABrowserMenu();

		//value of res parameter in selectResourceDropdownandNavigate equals to selectresource value  
		boolean navigateFSQABrowser = fbp.selectResourceDropDownandNavigate(FORMRESOURCETYPES.CUSTOMERS,"Tasks");
		TestValidation.IsTrue(navigateFSQABrowser, 
				"Navigated to FSQABrowser>Task Tab", 
				"Failed to navigate to FSQABrowser>Task Tab");

		boolean viewAfterTaskNameFilter = fbp.openAndApplySettingsForColumn(COLUMNHEADER.TASKNAME, COLUMNSETTING.FILTER,schedulename);
		TestValidation.IsTrue(viewAfterTaskNameFilter, 
				"Applied"+COLUMNHEADER.TASKNAME+" filter", 
				"Failed, to apply" +COLUMNHEADER.TASKNAME +"filter");

		// compare date
		boolean viewTaskGrid = fsqaTasksPage.viewTaskOnGrid(schedulename,currentDate);
		TestValidation.IsTrue(viewTaskGrid, 
				schedulename+"Scheduled due Task created via Task Scheduler is not visible for "+currentDate,
				schedulename+"Scheduled due tasks created via Task Scheduler is visible for "+currentDate);
	}
	finally {
		controlActions.refreshPage();
	}
	}	

	@Test(description="Task Scheduler - Index View - As a user, I want to be able to view all"
			+ "scheduled tasks by day or week specific to a location.(15800)")
	public void TestCase_22217() {

		try {
		TaskScheduleDetails tsd = new TaskScheduleDetails();
		tsp=home.clickTaskSchedulerMenu();

		boolean viewByTaskBtnDisplayed= controlActions.isElementDisplayed(tsp.ViewByTaskBtn);
		TestValidation.IsTrue(viewByTaskBtnDisplayed, 
				"Navigated to Task Scheduler Page", 
				"Failed to navigate to Task Scheduler Page");

		boolean checkIndexLook= tsp.Indexview(tsd);
		TestValidation.IsTrue(checkIndexLook, 
				"VERIFIED Weekly & Daily Tab", 
				"Failed to verify 'View By Task' button is displayed");
		}
		finally {
			controlActions.refreshPage();
		}
	}

	@Test(description="Task Scheduler|| Verify the behavior on changing the location from"
			+ "Grid view page")
	public void TestCase_38476() {
		try {
		TaskScheduleDetails tsd = new TaskScheduleDetails();
		locations = new ManageLocationPage(driver);	
		tsp=home.clickTaskSchedulerMenu();

		boolean viewByTaskBtnDisplayed= controlActions.isElementDisplayed(tsp.ViewByTaskBtn);
		TestValidation.IsTrue(viewByTaskBtnDisplayed, 
				"Navigated succesfully to Task Scheduler Page", 
				"Failed to verify 'View By Task' button is displayed");
		
		boolean setLoc = tsp.selectLocationForGrid(locationInstanceValue2);
		TestValidation.IsTrue(setLoc, 
				"SELECTED Location " + tsd.locationvalue + " for Task Scheduler grid",
				"Could NOT select Location " + tsd.locationvalue + " for Task Scheduler grid");

		boolean verifyDaily = tsp.verifyScheduleInTaskView(schedulename1);
		TestValidation.IsTrue(verifyDaily, 
				"VERIFIED Task Schedule " + tsd.taskname,
				"Could NOT verify Task schedule " + schedulename1);
		
		controlActions.refreshPage();

		 setLoc = tsp.selectLocationForGrid(locationInstanceValue);
		TestValidation.IsTrue(setLoc, 
				"SELECTED Location " + locationInstanceValue + " for Task Scheduler grid",
				"Could NOT select Location " + locationInstanceValue + " for Task Scheduler grid");

		 verifyDaily = tsp.verifyScheduleInTaskView(schedulename);
		TestValidation.IsTrue(verifyDaily, 
				"VERIFIED Daily Schedule " + schedulename,
				"Could NOT verify Daily schedule " + schedulename);
		}
		finally {
			controlActions.refreshPage();
		}
	}

	
	
	@AfterClass(alwaysRun = true)
	public void closeBrowser() throws InterruptedException {
		driver.close();
	}
}