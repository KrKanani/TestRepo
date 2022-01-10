package com.project.safetychain.webapp.testcases;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.project.safetychain.webapp.pageobjects.*;
import com.project.safetychain.webapp.pageobjects.CommonPage.TIMEZONE;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FIELD_TYPES;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FORMRESOURCETYPES;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FORMTYPES;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FormDesignParams;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FormFieldParams;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.IDENTIFIER_INPUT_TYPE;
import com.project.safetychain.webapp.pageobjects.ManageLocationPage.LocationInstanceParams;
import com.project.safetychain.webapp.pageobjects.ManageResourcePage.RESOURCETYPES;
import com.project.safetychain.webapp.pageobjects.ManageResourcePage.ResourceDetailParams;
import com.project.safetychain.webapp.pageobjects.ResourceDesignerPage.CATEGORYTYPES;
import com.project.safetychain.webapp.pageobjects.RolesManagerPage.COLUMN_HEADER;
import com.project.safetychain.webapp.pageobjects.RolesManagerPage.ROLE_ACTION;
import com.project.safetychain.webapp.pageobjects.RolesManagerPage.ROLE_PERMISSION;
import com.project.safetychain.webapp.pageobjects.TaskSchedulerPage.TaskScheduleDetails;
import com.project.safetychain.webapp.pageobjects.UserManagerPage.UsrDetailParams;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.utilities.CommonMethods;
import com.project.utilities.ControlActions;
import com.project.utilities.DateTime;

public class TCG_REG_TaskScheduler_PermissionFlows extends TestBase{

	ControlActions controlActions;
	CommonPage cp;
	HomePage hp;
	LoginPage lp;
	DateTime dt = new DateTime();
	TaskScheduleDetails tsd;	
	TaskSchedulerPage tsp;
	RolesManagerPage rmp;
	public static String workGroupName;
	public static String roleName;
	public static String userUN;
	public static String userFN;
	public static String userLN;
	public static List<String> userLocation;
	public static List<String> userRole;
	public static String username, firstName, lastName;
	public static String locationCategoryValue;
	public static String locationInstanceValue;
	public static String customerCategoryValue;
	public static String customerInstanceValue;
	public static String formName;
	public static String numericFN = "Number";
	public static String identifierFN = "ID";
	public static String vldtnName;
	public static String vldtnSummary;
	public static String OneTimeTaskName;
	public static String TaskDescription;
	public static String dateFormat;
	public static String dueDate;		
	public static String customersCategoryValue;
	public static String customersInstanceValue;
	public static String wgName;
	public static String DailyInboxTask;	
	public static String WeeklyEditTask;	

	HashMap<String,String> identifierDets;


	@BeforeClass(alwaysRun = true)
	public void groupInit() throws InterruptedException {

		workGroupName = CommonMethods.dynamicText("WG");
		roleName = CommonMethods.dynamicText("RL");
		userUN = CommonMethods.dynamicText("UN");
		userFN = CommonMethods.dynamicText("FN");
		userLN = CommonMethods.dynamicText("LN");
		userLocation = new ArrayList<String>();
		userRole = new ArrayList<String>();
		locationCategoryValue = CommonMethods.dynamicText("Loc_Cat");
		locationInstanceValue = CommonMethods.dynamicText("Loc_Inst");
		customerCategoryValue = CommonMethods.dynamicText("Cust_Cat");
		customerInstanceValue = CommonMethods.dynamicText("Cust_Inst");
		formName = CommonMethods.dynamicText("VldtnPermssn");
		vldtnName = CommonMethods.dynamicText("Vldtn");
		vldtnSummary = CommonMethods.dynamicText("Summary");
		OneTimeTaskName = CommonMethods.dynamicText("OneTime");
		TaskDescription = CommonMethods.dynamicText("Task Description");
		locationCategoryValue	= CommonMethods.dynamicText("Loc_Cat");
		customersCategoryValue	= CommonMethods.dynamicText("Cust_Cat");
		customersInstanceValue = CommonMethods.dynamicText("Cust_Inst");
		locationInstanceValue	= CommonMethods.dynamicText("Loc_Inst");

		//formName= "TaskSchedlr_28.09_19.35.08";//CommonMethods.dynamicText("TaskSchedlr");
		WeeklyEditTask = CommonMethods.dynamicText("WeeklyTask");

		driver = launchbrowser();
		controlActions = new ControlActions(driver);
		controlActions.getUrl(applicationUrl);
		hp = new HomePage(driver);
		lp = new LoginPage(driver);

		hp = lp.performLogin(adminUsername, adminPassword);
		if(hp.error) {
			TCGFailureMsg = "Could NOT login to application";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}



		RolesManagerPage rmp = hp.clickRolesMenu();
		if(!rmp.createRole(roleName)) {
			TCGFailureMsg = "Could not create role - " + roleName;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		userLocation.add("ALL");
		userRole.add(roleName);
		UserManagerPage ump = hp.clickUsersMenu();
		UsrDetailParams udp = new UsrDetailParams();
		udp.Username = userUN;
		udp.Password = GenericPassword;
		udp.FirstName = userFN;
		udp.LastName = userLN;
		udp.Email = "test@test.com";
		udp.Timezone = TIMEZONE.REPUBLICOFINDIA;
		udp.Locations = userLocation;
		udp.Roles = userRole;
		boolean userCreation = ump.createInternalUser(udp);
		if(!userCreation) {
			TCGFailureMsg = "Could NOT create user - " + userUN;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}


		if(!lp.loginAfterUserCreation(userUN, GenericPassword, GenericNewPassword)) {
			TCGFailureMsg = "Could NOT login with user - " + userUN;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

//		cp.Sync();
//		if(!hp.userLogout()){
//			TCGFailureMsg = "Could NOT logout from application";
//			logFatal(TCGFailureMsg);
//			throw new SkipException(TCGFailureMsg);
//		}
//
//		hp = lp.performLogin(userUN, GenericNewPassword);

		WorkGroupsPage wgp = new WorkGroupsPage(driver);
		if(!wgp.createWorkGroup(workGroupName)) {
			TCGFailureMsg = "Could not create workgroup - " + workGroupName;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		hp.clickFSQABrowserMenu();
		//Category creation
		HashMap<String,String> categories = new HashMap<String, String>();
		categories.put(CATEGORYTYPES.CUSTOMERS, customerCategoryValue);
		categories.put(CATEGORYTYPES.LOCATION, locationCategoryValue);
		ResourceDesignerPage rdp = hp.clickResourceDesignerMenu();
		if(!rdp.createCategory(categories)) {
			TCGFailureMsg = "Could NOT create Resource categories";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		//Location instance
		HashMap<String,Boolean> locInstance = new HashMap<String, Boolean>();
		locInstance.put(locationInstanceValue, true);
		LocationInstanceParams lip = new LocationInstanceParams();
		lip.CategoryName = locationCategoryValue;
		lip.NumberFieldValue = 10;
		lip.TextFieldValue = "XYZ";
		lip.InstanceName = locInstance;
		ManageLocationPage mlp = hp.clickLocationsMenu();
		if(!mlp.createLocation(lip)) {
			TCGFailureMsg = "Could NOT create Location instance";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		//Resource creation
		HashMap<String,Boolean> instances = new HashMap<String, Boolean>();
		instances.put(customerInstanceValue, true);
		ResourceDetailParams rd = new ResourceDetailParams();
		rd.CategoryName = customerCategoryValue;
		rd.CategoryType = RESOURCETYPES.CUSTOMERS;
		rd.NumberFieldValue = 30;
		rd.TextFieldValue = "ABC";
		rd.InstanceNames = instances;
		rd.LocationInstanceValue = locationInstanceValue;	
		ManageResourcePage mrp = hp.clickResourcesMenu();
		if(!mrp.createResourceLinkLocation(rd)) {
			TCGFailureMsg = "Could NOT create Instances for resource - " + customerInstanceValue;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		HashMap<String, List<String>> selectResources = new HashMap<String, List<String>>();
		selectResources.put(FORMRESOURCETYPES.CUSTOMERS,Arrays.asList(customerCategoryValue));

		HashMap<String,List<String>> fieldDetails = new HashMap<String,List<String>>();
		fieldDetails.put(FIELD_TYPES.NUMERIC, Arrays.asList(numericFN));
		fieldDetails.put(FIELD_TYPES.IDENTIFIER, Arrays.asList(identifierFN));

		FormFieldParams ffparams = new FormFieldParams();
		ffparams.FieldDetails = fieldDetails;		
		ffparams.IdentiferType = IDENTIFIER_INPUT_TYPE.SELECT_ONE;
		ffparams.IdentifierValue = Arrays.asList("LINE1","LINE2");

		FormDesignParams fdparams = new FormDesignParams();
		fdparams.FormType = FORMTYPES.CHECK;
		fdparams.FormName = formName;
		fdparams.SelectResources = selectResources;
		fdparams.DesignFields = Arrays.asList(ffparams);

		FormDesignerPage fdp = hp.clickFormDesignerMenu();
		if(!fdp.createAndReleaseForm(fdparams)) {
			TCGFailureMsg = "Could NOT create and release form - " + formName;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		hp.clickTaskSchedulerMenu();
		tsp= new TaskSchedulerPage(driver);
		TaskScheduleDetails tsd = new TaskScheduleDetails();
		tsd.resource = customersInstanceValue;
		tsd.workGroup = workGroupName;
		tsd.haveEndDate = true;
		tsd.formORdocument = "form";
		tsd.formORdocumentvalue = formName;
		tsd.taskDescription = TaskDescription;
		tsd.locationvalue =locationInstanceValue;
		tsd.taskoccure = "Weekly";
		tsd.haveEndDate = true;
		tsd.taskname = WeeklyEditTask;

		if(!tsp.createTaskSchedule(tsd,tsd.taskname,tsd.locationvalue)) {

			TCGFailureMsg = "Could NOT create schedule - " + WeeklyEditTask;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
		
		if(!hp.userLogout()){
			TCGFailureMsg = "Could NOT logout from application";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

	}

	@Test(description="Task Scheduler - Permissions - As an admin user, I want to be able to set "
			+ "various permissions for Task Scheduler to a set of roles.(15792)")
	public void TestCase_22203() { 	
	
		hp = new HomePage(driver);
		lp = new LoginPage(driver);
		tsp= new TaskSchedulerPage(driver);
		cp=new CommonPage(driver);
		TaskScheduleDetails tsd = new TaskScheduleDetails();
		rmp= new RolesManagerPage(driver);
		tsd.locationvalue =locationInstanceValue;//"Loc_Inst_28.09_19.35.08";//;
		tsd.taskname =WeeklyEditTask;//"WeeklyTask_28.09_19.35.08"; //
		//roleName="RL_28.09_19.35.08";
		//userUN="UN_28.09_19.35.08";
		//GenericNewPassword="SafetyChain1234#";
		
		hp = lp.performLogin(userUN, GenericNewPassword);
		TestValidation.IsTrue(!hp.error, 
				"LOGGED IN with Admin user " + userUN, 
				"Failed to login with Admin user " + userUN);

		tsp =hp.clickTaskSchedulerMenu();
		TestValidation.Equals(tsp.error, 
				false,
				"TaskScheduler tab was displayed in Hamburger and sucesfully clicked ", 
				"Failed to click TaskScheduler");

		boolean viewScheduleTaskBtnDisplayed= controlActions.isElementDisplayed(tsp.ScheduleTaskBtn);
		TestValidation.IsTrue(viewScheduleTaskBtnDisplayed, 
				"Schedule Task button is displayed", 
				"Failed to display 'Schedule Task' button ");

		boolean viewEditOptnDisplay= tsp.isScheduleEditable(tsd.locationvalue ,tsd.taskname);
		TestValidation.IsTrue(viewEditOptnDisplay , 
				"Task is editable", 
				"Failed to edit task");

		boolean viewDeleteBtnDisplayed= tsp.isScheduleDeletable(tsd.locationvalue ,tsd.taskname);
		TestValidation.IsTrue(viewDeleteBtnDisplayed, 
				"Delete Btn is displayed", 
				"Failed to display 'Delete Btn'");

		// Delete task visibility hidden

		rmp = hp.clickRolesMenu();
		boolean  DeleteTaskSchedule=rmp.searchAndSetRoleForModule(roleName, "Task Scheduler > Task Details", COLUMN_HEADER.ACTION,
				ROLE_ACTION.DELETE_TASK_SCHEDULE, ROLE_PERMISSION.NO);
		TestValidation.IsTrue(DeleteTaskSchedule, 
				"Delete Task access is set to NO", 
				"Failed to set delete task to NO");

		boolean logout = hp.userLogout();
		TestValidation.IsTrue(logout, 
				"Log out from application", 
				"Failed to Log out from application");
		// login with new user to see reflected changes
		hp = lp.performLogin(userUN, GenericNewPassword);
		TestValidation.IsTrue(!hp.error, 
				"LOGGED IN with Admin user " + userUN, 
				"Failed to login with Admin user " + userUN);

		hp.clickTaskSchedulerMenu();
		viewEditOptnDisplay= tsp.isScheduleEditable(tsd.locationvalue ,tsd.taskname);
		TestValidation.IsTrue(viewEditOptnDisplay, 
				"Task is editable", 
				"Failed to edit task");

		boolean viewDeleteBtnDisplay= controlActions.isElementDisplay(tsp.DeleteBtn);
		TestValidation.IsFalse(viewDeleteBtnDisplay, 
				"Delete Btn is not displayed", 
				"Failed to verify whether 'Delete Btn' is displayed or not");
		controlActions.click(tsp.EditCancelBtn);


		//		// edit task unaccesiable

		rmp = hp.clickRolesMenu();
		boolean  EditTaskSchedule=rmp.searchAndSetRoleForModule(roleName, "task scheduler", COLUMN_HEADER.ACTION,
				ROLE_ACTION.EDIT_TASK_SCHEDULE, ROLE_PERMISSION.NO);
		TestValidation.IsTrue(EditTaskSchedule, 
				"Edit Task access is set to NO", 
				"Failed to set edit task to NO");

		logout = hp.userLogout();
		TestValidation.IsTrue(logout, 
				"Log out from application", 
				"Failed to Log out from application");
		//		
		hp = lp.performLogin(userUN, GenericNewPassword);
		TestValidation.IsTrue(!hp.error, 
				"LOGGED IN with Admin user " + userUN, 
				"Failed to login with Admin user " + userUN);
		//	
		tsp = hp.clickTaskSchedulerMenu();
		// viewEditOptnDisplay= controlActions.performGetElementByXPath(TaskSchedulerPageLocators.SCHEDULETASK_BTN);
		boolean viewEditOptnDisplay1= tsp.isScheduleEditable(tsd.locationvalue ,tsd.taskname);
		TestValidation.IsFalse(viewEditOptnDisplay1, 
				"Task is not editable", 
				"Failed, Task can be editted");

		//hide schedule task btn
		rmp = hp.clickRolesMenu();
		boolean  ScheduleTask=rmp.searchAndSetRoleForModule(roleName, "task scheduler", COLUMN_HEADER.ACTION,
				ROLE_ACTION.SCHEDULE_TASK, ROLE_PERMISSION.NO);
		TestValidation.IsTrue(ScheduleTask, 
				"Schedule Task access is set to NO", 
				"Failed to set Schedule task to NO");

		logout = hp.userLogout();
		TestValidation.IsTrue(logout, 
				"Log out from application", 
				"Failed to Log out from application");

		hp = lp.performLogin(userUN, GenericNewPassword);
		TestValidation.IsTrue(!hp.error, 
				"LOGGED IN with Admin user " + userUN, 
				"Failed to login with Admin user " + userUN);

		hp.clickTaskSchedulerMenu();

		boolean viewScheduleTaskBtnDisplay= controlActions.isElementDisplayed(TaskSchedulerPageLocators.SCHEDULETASK_BTN);
		TestValidation.IsFalse(viewScheduleTaskBtnDisplay, 
				"Schedule Task button is not displayed", 
				"Failed, 'Schedule Task' button is displayed");


		//hide task scheduler tab from hamburger
		rmp = hp.clickRolesMenu();
		boolean  AccessSubsytem=rmp.searchAndSetRoleForModule(roleName, "task scheduler", COLUMN_HEADER.ACTION,
				ROLE_ACTION.ACCESS_SUBSYSTEM, ROLE_PERMISSION.NO);
		TestValidation.IsTrue(AccessSubsytem, 
				"Access Subsystem  is set to NO", 
				"Failed to set Access Subsystem to NO");

		 logout = hp.userLogout();
		TestValidation.IsTrue(logout, 
				"Log out from application", 
				"Failed to Log out from application");

		hp = lp.performLogin(userUN, GenericNewPassword);
		TestValidation.IsTrue(!hp.error, 
				"LOGGED IN with Admin user " + userUN, 
				"Failed to login with Admin user " + userUN);

		boolean  verifyClickHamburger= hp.clickHamburgerMenu();
		TestValidation.IsTrue(verifyClickHamburger, 
				"Clicked Hamburger succesfully ", 
				"Failed to click Hamburger");

		boolean verifyLabelTaskScheduler=controlActions.isElementDisplay(cp.TaskSchedulerMnu);
		TestValidation.IsFalse(verifyLabelTaskScheduler, 
				"TaskScheduler label is not visible ", 
				"Failed, TaskScheduler label is  visible");

	}



	@AfterClass(alwaysRun = true)

	public void closeBrowser() throws InterruptedException {
		driver.close();
	}

}
