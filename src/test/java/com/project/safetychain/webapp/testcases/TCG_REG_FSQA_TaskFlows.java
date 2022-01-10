package com.project.safetychain.webapp.testcases;

import java.util.ArrayList;
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
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.COLUMNHEADER;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.COLUMNSETTING;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.FSQATAB;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.TASKDETAILS;
import com.project.safetychain.webapp.pageobjects.FSQABrowserTaskPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserTaskPage.HISTORY_ACTIONS;
import com.project.safetychain.webapp.pageobjects.FSQABrowserTaskPage.HISTORY_DATA;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FIELD_TYPES;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FORMRESOURCETYPES;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FORMTYPES;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FormDesignParams;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FormFieldParams;
import com.project.safetychain.webapp.pageobjects.ResourceDesignerPage.CATEGORYTYPES;
import com.project.safetychain.webapp.pageobjects.TaskSchedulerPage.TaskScheduleDetails;
import com.project.safetychain.webapp.pageobjects.TaskSchedulerPage;
import com.project.safetychain.webapp.pageobjects.UserManagerPage.UsrDetailParams;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.utilities.CommonMethods;
import com.project.utilities.ControlActions;
import com.project.utilities.DateTime;

public class TCG_REG_FSQA_TaskFlows extends TestBase{
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
	public static String formname;
	public static String wgName;
	public static String wgName1;
	public static String cformName,aformName,qformName;	
	public static String numericFN = "Numeric";	
	public static String cctask,catask,cqtask,ectask,eatask,eqtask,ictask,iatask,iqtask,sctask,satask,sqtask,duebyTask,locTask,locTask1 ;
	public static String displayName = null;
	public static String timezoneCode = null;
	public static String usrTimezone = null;

	public static String roleName;
	public static String userUN;
	public static String userFN;
	public static String userLN;
	public static List<String> userLocation;
	public static List<String> userRole , workgroup;
	public static String userPassword, userNewPassword;
	UsrDetailParams udp;
	public static String schedulename;
	public static String filtertask;


	@BeforeClass(alwaysRun = true)
	public void groupInit() throws InterruptedException {

		locationCategoryValue = CommonMethods.dynamicText("LC");//"LC12_pr1";//
		locationInstanceValue = CommonMethods.dynamicText("LI");//"LI12_pr1";//
		locationInstanceValue2 = CommonMethods.dynamicText("LI2");//"LI02_pr12";
		customersCategoryValue = CommonMethods.dynamicText("CC");//"CC12_pr1";
		customersInstanceValue = CommonMethods.dynamicText("CI");//"CI12_pr1";
		equipmentCategoryValue = CommonMethods.dynamicText("EC");//"EC12_pr1";
		equipmentInstanceValue = CommonMethods.dynamicText("EI");//"EI12_pr1";
		itemsCategoryValue = CommonMethods.dynamicText("IC");//"IC12_pr1";
		itemsInstanceValue = CommonMethods.dynamicText("II");//"II12_pr1";
		suppliersCategoryValue = CommonMethods.dynamicText("SC");//"SC12_pr1";
		suppliersInstanceValue = CommonMethods.dynamicText("SI");//"SI12_pr1";
		wgName = CommonMethods.dynamicText("WG");//"WG01_pr01" ;
		wgName1 = CommonMethods.dynamicText("WG1");//"WG2_pr2";
		cformName =CommonMethods.dynamicText("Check");//"Check1_pr1";
		aformName = CommonMethods.dynamicText("Audit");//"Ack1_pr1";
		qformName =CommonMethods.dynamicText("Quest");//"Quest1_pr1" ;
		cctask =CommonMethods.dynamicText("CCTask");//"CCTask1_pr1";
		catask =CommonMethods.dynamicText("CATask");//"CATask1_pr1" ;
		cqtask = CommonMethods.dynamicText("CQTask");//"CQTask1_pr1";
		ectask = CommonMethods.dynamicText("ECTask");//"ECTask1_pr1";
		eatask = CommonMethods.dynamicText("EATask");//"EATask1_pr1";
		eqtask =CommonMethods.dynamicText("EQTask");//"EQTask1_pr1" ;
		ictask= CommonMethods.dynamicText("ICTask");//"ICTask1_pr1";
		iatask =CommonMethods.dynamicText("IATask");//"IATask1_pr1";
		iqtask = CommonMethods.dynamicText("IQTask");//"IQTask1_pr1";
		sctask =CommonMethods.dynamicText("SCTask");//"SCTask1_pr1" ;
		satask = CommonMethods.dynamicText("SATask");//"SATask1_pr1";
		sqtask = CommonMethods.dynamicText("SQTask");//"SQTask1_pr1";
		duebyTask =CommonMethods.dynamicText("TaskDueBy");//"TaskDueBy1_pr1" ;
		locTask = CommonMethods.dynamicText("LocTask");//"LocTask1_pr1";
		locTask1 = CommonMethods.dynamicText("LocTask1");//"LocTask2_pr2";
		schedulename = CommonMethods.dynamicText("Schedule");//"Sched1_pr1";
		filtertask =CommonMethods.dynamicText("FilterTask");//"FilterTask1_pr1" ;

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

		if(!manageResource.addResourceInstances("Items", itemsCategoryValue, itemInstances, true, locationInstanceValue)) {
			TCGFailureMsg = "Could NOT create resource " + itemsCategoryValue + " & link Location - "+ locationInstanceValue;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		if(!manageResource.addResourceInstances("Suppliers", suppliersCategoryValue, supplierInstances,true, locationInstanceValue)) {
			TCGFailureMsg = "Could NOT create resource " + suppliersCategoryValue + " & link Location - "+ locationInstanceValue;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		if(!manageResource.linkItemSupplier(itemsInstanceValue)) {
			TCGFailureMsg = "Could NOT link supplier resource " + suppliersInstanceValue + " with item resource - "+ itemsInstanceValue;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}


		if(!manageResource.addResourceInstances("Equipment", equipmentCategoryValue, equipmentInstances,true, locationInstanceValue)) {
			TCGFailureMsg = "Could NOT create resource " + equipmentCategoryValue + " & link Location - "+ locationInstanceValue;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		if(!manageResource.addResourceInstances("Customers", customersCategoryValue , customerInstances,true, locationInstanceValue)) {
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
		resource.put(FORMRESOURCETYPES.EQUIPMENT, Arrays.asList(equipmentCategoryValue));
		resource.put(FORMRESOURCETYPES.SUPPLIERS, Arrays.asList(suppliersCategoryValue));
		resource.put(FORMRESOURCETYPES.ITEMS, Arrays.asList(itemsCategoryValue));

		FormDesignParams fdpDets = new FormDesignParams();
		fdpDets.FormType = FORMTYPES.CHECK;
		fdpDets.FormName = cformName;
		fdpDets.SelectResources = resource;
		fdpDets.DesignFields = Arrays.asList(ffp);
		fdpDets.ReleaseForm = true;

		FormDesignerPage fdp = home.clickFormDesignerMenu();
		if(!fdp.createAndReleaseForm(fdpDets)) {
			TCGFailureMsg = "Could NOT create unreleased form - " + cformName;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		//FORM - Creation and Release
		HashMap<String, List<String>> fields1 = new HashMap<String, List<String>>();
		fields1.put(FIELD_TYPES.NUMERIC, Arrays.asList("Number"));
		fields1.put(FIELD_TYPES.SINGLE_LINE_TEXT, Arrays.asList("SText"));
		fields1.put(FIELD_TYPES.DATE_TIME, Arrays.asList("D&T"));

		FormFieldParams ffp1 = new FormFieldParams();
		ffp1.FieldDetails = fields1;

		FormDesignParams fdpDets1 = new FormDesignParams();
		fdpDets1.FormType = FORMTYPES.QUESTIONNAIRE;
		fdpDets1.FormName = qformName;
		fdpDets1.SelectResources = resource;
		fdpDets1.DesignFields = Arrays.asList(ffp1);
		fdpDets1.ReleaseForm = true;

		FormDesignerPage fdp1 = home.clickFormDesignerMenu();
		if(!fdp1.createAndReleaseForm(fdpDets1)) {
			TCGFailureMsg = "Could NOT create unreleased form - " + qformName;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		//FORM - Creation and Release

		FormFieldParams ffp2 = new FormFieldParams();
		ffp2.SectionName = "Section";
		ffp2.FieldDetails = fields1;

		FormDesignParams fdpDets2 = new FormDesignParams();
		fdpDets2.FormType = FORMTYPES.AUDIT;
		fdpDets2.FormName = aformName;
		fdpDets2.SelectResources = resource;
		fdpDets2.DesignFields = Arrays.asList(ffp2);
		fdpDets2.ReleaseForm = true;

		FormDesignerPage fdp2 = home.clickFormDesignerMenu();
		if(!fdp2.createAndReleaseForm(fdpDets2)) {
			TCGFailureMsg = "Could NOT create unreleased form - " + aformName;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		if(!wgp.createWorkGroup(wgName, adminUsername)) {
			TCGFailureMsg = "Could not create workgroup - " + wgName;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		roleName = "superadmin";
		userUN = CommonMethods.dynamicText("UN");//"user1_pr1" ;
		userFN = CommonMethods.dynamicText("FN");//"pr1";
		userLN = CommonMethods.dynamicText("LN"); //"ped1";
		userLocation = new ArrayList<String>();
		userRole = new ArrayList<String>();
		workgroup = new ArrayList<String>();
		userPassword = GenericPassword;
		userNewPassword = GenericNewPassword;
		userLocation.add(locationInstanceValue2);
		userRole.add(roleName);
		workgroup.add(wgName);

		udp = new UsrDetailParams();
		udp.Username = userUN;
		udp.Password = userPassword;
		udp.FirstName = userFN;
		udp.LastName = userLN;
		udp.Email = "task@test.com";
		udp.Timezone = "Republic of India";
		udp.Locations = userLocation;
		udp.Roles = userRole;
		udp.WorkGroups = workgroup;

		UserManagerPage ump = home.clickUsersMenu();
		if(ump.error) {
			TCGFailureMsg = "Could NOT navigate to Admin Tools - Manage Users ";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
		boolean userCreation = ump.createInternalUser(udp);
		if(!userCreation) {
			TCGFailureMsg = "Could NOT create user - " + userUN;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		home.clickTaskSchedulerMenu();
		controlActions.refreshPage();
		TaskScheduleDetails tsd = new TaskScheduleDetails();
		tsd.taskoccure = "One Time Only";
		tsd.resource = customersInstanceValue;
		tsd.workGroup = wgName;
		tsd.haveEndDate = true;
		tsd.formORdocument = "form";
		tsd.formORdocumentvalue =cformName;// "8 April PD check form";
		tsd.taskDescription = "FSQA - Browser > Task";
		tsd.locationvalue = locationInstanceValue;//"febtest_1";
		tsd.taskname = schedulename;

		if(!tsp.createTaskSchedule(tsd,tsd.taskname,tsd.locationvalue)) {
			TCGFailureMsg = "Could NOT create schedule - " + schedulename;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

	}

	@Test(description = "All three form task type of customer resource", priority = -1)
	public void TestCase_14531() throws InterruptedException {

		home.clickFSQABrowserMenu();

		boolean navigate = fbp.selectResourceDropDownandNavigate("Customers","Forms");
		TestValidation.IsTrue(navigate, 
				"Navigated to FSQABrowser>FormsTab", 
				"Failed to navigate to FSQABrowser>Forms Tab");

		boolean applyfilter = fbp.openAndApplySettingsForColumn(COLUMNHEADER.FORMNAME, COLUMNSETTING.FILTER,cformName);
		TestValidation.IsTrue(applyfilter, 
				"Applied Filter on" + COLUMNHEADER.FORMNAME, 
				"Failed to apply filter on" + COLUMNHEADER.FORMNAME);

		TASKDETAILS tsd = new TASKDETAILS();
		tsd.Location = locationInstanceValue;
		tsd.Resource = customersInstanceValue;
		tsd.TaskName = cctask;
		tsd.Workgroup = wgName;

		boolean assigntask = fbp.assignFormTask(tsd);
		TestValidation.IsTrue(assigntask, 
				"Form task assigned" + cctask, 
				"Failed to assign form task" + cctask);

		boolean applyfilter1 = fbp.openAndApplySettingsForColumn(COLUMNHEADER.FORMNAME, COLUMNSETTING.FILTER,qformName);
		TestValidation.IsTrue(applyfilter1, 
				"Applied Filter on" + COLUMNHEADER.FORMNAME, 
				"Failed to apply filter on" + COLUMNHEADER.FORMNAME);

		TASKDETAILS tsd1 = new TASKDETAILS();
		tsd1.Location = locationInstanceValue;
		tsd1.Resource = customersInstanceValue;
		tsd1.TaskName = cqtask;
		tsd1.Workgroup = wgName;

		boolean assigntask1 = fbp.assignFormTask(tsd1);
		TestValidation.IsTrue(assigntask1, 
				"Form task assigned" + cqtask, 
				"Failed to assign form task" + cqtask);

		boolean applyfilter2 = fbp.openAndApplySettingsForColumn(COLUMNHEADER.FORMNAME, COLUMNSETTING.FILTER,aformName);
		TestValidation.IsTrue(applyfilter2, 
				"Applied Filter on" + COLUMNHEADER.FORMNAME, 
				"Failed to apply filter on" + COLUMNHEADER.FORMNAME);

		TASKDETAILS tsd2 = new TASKDETAILS();
		tsd2.Location = locationInstanceValue;
		tsd2.Resource = customersInstanceValue;
		tsd2.TaskName = catask;
		tsd2.Workgroup = wgName;

		boolean assigntask2 = fbp.assignFormTask(tsd2);
		TestValidation.IsTrue(assigntask2, 
				"Form task assigned" + catask, 
				"Failed to assign form task" + catask);

		boolean navigate1 = fbp.navigateToFSQATab(FSQATAB.TASKS);
		TestValidation.IsTrue(navigate1, 
				"Navigated to FSQABrowser>Tasks Tab", 
				"Failed to navigate to FSQABrowser>Tasks Tab");

		String taskname[] = {cctask,cqtask,catask};

		boolean viewtask = fbp.openAndApplySettingsForTaskColumn(COLUMNHEADER.TASKNAME, COLUMNSETTING.FILTER,taskname);
		TestValidation.IsTrue(viewtask, 
				"All tasks are visible", 
				"Failed to verify all tasks");

	}

	@Test(description = "All three form task type of equipment resource", priority = 0)
	public void TestCase_14553() throws InterruptedException {

		home.clickFSQABrowserMenu();

		boolean navigate = fbp.selectResourceDropDownandNavigate("Equipment","Forms");
		TestValidation.IsTrue(navigate, 
				"Navigated to FSQABrowser>FormsTab", 
				"Failed to navigate to FSQABrowser>Forms Tab");

		boolean applyfilter = fbp.openAndApplySettingsForColumn(COLUMNHEADER.FORMNAME, COLUMNSETTING.FILTER,cformName);
		TestValidation.IsTrue(applyfilter, 
				"Applied Filter on" + COLUMNHEADER.FORMNAME, 
				"Failed to apply filter on" + COLUMNHEADER.FORMNAME);

		TASKDETAILS tsd = new TASKDETAILS();
		tsd.Location = locationInstanceValue;
		tsd.Resource = equipmentInstanceValue;
		tsd.TaskName = ectask;
		tsd.Workgroup = wgName;

		boolean assigntask = fbp.assignFormTask(tsd);
		TestValidation.IsTrue(assigntask, 
				"Form task assigned" + ectask, 
				"Failed to assign form task" + ectask);

		boolean applyfilter1 = fbp.openAndApplySettingsForColumn(COLUMNHEADER.FORMNAME, COLUMNSETTING.FILTER,qformName);
		TestValidation.IsTrue(applyfilter1, 
				"Applied Filter on" + COLUMNHEADER.FORMNAME, 
				"Failed to apply filter on" + COLUMNHEADER.FORMNAME);

		TASKDETAILS tsd1 = new TASKDETAILS();
		tsd1.Location = locationInstanceValue;
		tsd1.Resource = equipmentInstanceValue;
		tsd1.TaskName = eqtask;
		tsd1.Workgroup = wgName;

		boolean assigntask1 = fbp.assignFormTask(tsd1);
		TestValidation.IsTrue(assigntask1, 
				"Form task assigned" + eqtask, 
				"Failed to assign form task" + eqtask);

		boolean applyfilter2 = fbp.openAndApplySettingsForColumn(COLUMNHEADER.FORMNAME, COLUMNSETTING.FILTER,aformName);
		TestValidation.IsTrue(applyfilter2, 
				"Applied Filter on" + COLUMNHEADER.FORMNAME, 
				"Failed to apply filter on" + COLUMNHEADER.FORMNAME);

		TASKDETAILS tsd2 = new TASKDETAILS();
		tsd2.Location = locationInstanceValue;
		tsd2.Resource = equipmentInstanceValue;
		tsd2.TaskName = eatask;
		tsd2.Workgroup = wgName;

		boolean assigntask2 = fbp.assignFormTask(tsd2);
		TestValidation.IsTrue(assigntask2, 
				"Form task assigned" + eatask, 
				"Failed to assign form task" + eatask);

		boolean navigate1 = fbp.navigateToFSQATab(FSQATAB.TASKS);
		TestValidation.IsTrue(navigate1, 
				"Navigated to FSQABrowser>Tasks Tab", 
				"Failed to navigate to FSQABrowser>Tasks Tab");

		String taskname[] = {ectask,eqtask,eatask};

		boolean viewtask = fbp.openAndApplySettingsForTaskColumn(COLUMNHEADER.TASKNAME, COLUMNSETTING.FILTER,taskname);
		TestValidation.IsTrue(viewtask, 
				"All tasks are visible", 
				"Failed to verify all tasks");


	}

	@Test(description = "All three form task type of items resource", priority = 1)
	public void TestCase_14579() throws InterruptedException {

		home.clickFSQABrowserMenu();

		boolean navigate = fbp.selectResourceDropDownandNavigate("Items","Forms");
		TestValidation.IsTrue(navigate, 
				"Navigated to FSQABrowser>FormsTab", 
				"Failed to navigate to FSQABrowser>Forms Tab");

		boolean applyfilter = fbp.openAndApplySettingsForColumn(COLUMNHEADER.FORMNAME, COLUMNSETTING.FILTER,cformName);
		TestValidation.IsTrue(applyfilter, 
				"Applied Filter on" + COLUMNHEADER.FORMNAME, 
				"Failed to apply filter on" + COLUMNHEADER.FORMNAME);

		TASKDETAILS tsd = new TASKDETAILS();
		tsd.Location = locationInstanceValue;
		tsd.Resource = itemsInstanceValue;
		tsd.TaskName = ictask;
		tsd.Workgroup = wgName;

		boolean assigntask = fbp.assignFormTask(tsd);
		TestValidation.IsTrue(assigntask, 
				"Form task assigned" + ictask, 
				"Failed to assign form task" + ictask);

		boolean applyfilter1 = fbp.openAndApplySettingsForColumn(COLUMNHEADER.FORMNAME, COLUMNSETTING.FILTER,qformName);
		TestValidation.IsTrue(applyfilter1, 
				"Applied Filter on" + COLUMNHEADER.FORMNAME, 
				"Failed to apply filter on" + COLUMNHEADER.FORMNAME);

		TASKDETAILS tsd1 = new TASKDETAILS();
		tsd1.Location = locationInstanceValue;
		tsd1.Resource = itemsInstanceValue;
		tsd1.TaskName = iqtask;
		tsd1.Workgroup = wgName;

		boolean assigntask1 = fbp.assignFormTask(tsd1);
		TestValidation.IsTrue(assigntask1, 
				"Form task assigned" + iqtask, 
				"Failed to assign form task" + iqtask);

		boolean applyfilter2 = fbp.openAndApplySettingsForColumn(COLUMNHEADER.FORMNAME, COLUMNSETTING.FILTER,aformName);
		TestValidation.IsTrue(applyfilter2, 
				"Applied Filter on" + COLUMNHEADER.FORMNAME, 
				"Failed to apply filter on" + COLUMNHEADER.FORMNAME);

		TASKDETAILS tsd2 = new TASKDETAILS();
		tsd2.Location = locationInstanceValue;
		tsd2.Resource = itemsInstanceValue;
		tsd2.TaskName = iatask;
		tsd2.Workgroup = wgName;

		boolean assigntask2 = fbp.assignFormTask(tsd2);
		TestValidation.IsTrue(assigntask2, 
				"Form task assigned" + iatask, 
				"Failed to assign form task" + iatask);

		boolean navigate1 = fbp.navigateToFSQATab(FSQATAB.TASKS);
		TestValidation.IsTrue(navigate1, 
				"Navigated to FSQABrowser>Tasks Tab", 
				"Failed to navigate to FSQABrowser>Tasks Tab");

		String taskname[] = {ictask,iqtask,iatask};

		boolean viewtask = fbp.openAndApplySettingsForTaskColumn(COLUMNHEADER.TASKNAME, COLUMNSETTING.FILTER,taskname);
		TestValidation.IsTrue(viewtask, 
				"All tasks are visible", 
				"Failed to verify all tasks");


	}

	@Test(description = "All three form task type of suppliers resource", priority = 2)
	public void TestCase_14555() throws InterruptedException {

		home.clickFSQABrowserMenu();

		boolean navigate = fbp.selectResourceDropDownandNavigate("Suppliers","Forms");
		TestValidation.IsTrue(navigate, 
				"Navigated to FSQABrowser>FormsTab", 
				"Failed to navigate to FSQABrowser>Forms Tab");

		boolean applyfilter = fbp.openAndApplySettingsForColumn(COLUMNHEADER.FORMNAME, COLUMNSETTING.FILTER,cformName);
		TestValidation.IsTrue(applyfilter, 
				"Applied Filter on" + COLUMNHEADER.FORMNAME, 
				"Failed to apply filter on" + COLUMNHEADER.FORMNAME);

		TASKDETAILS tsd = new TASKDETAILS();
		tsd.Location = locationInstanceValue;
		tsd.Resource = suppliersInstanceValue;
		tsd.TaskName = sctask;
		tsd.Workgroup = wgName;

		boolean assigntask = fbp.assignFormTask(tsd);
		TestValidation.IsTrue(assigntask, 
				"Form task assigned" + sctask, 
				"Failed to assign form task" + sctask);

		boolean applyfilter1 = fbp.openAndApplySettingsForColumn(COLUMNHEADER.FORMNAME, COLUMNSETTING.FILTER,qformName);
		TestValidation.IsTrue(applyfilter1, 
				"Applied Filter on" + COLUMNHEADER.FORMNAME, 
				"Failed to apply filter on" + COLUMNHEADER.FORMNAME);

		TASKDETAILS tsd1 = new TASKDETAILS();
		tsd1.Location = locationInstanceValue;
		tsd1.Resource = suppliersInstanceValue;
		tsd1.TaskName = sqtask;
		tsd1.Workgroup = wgName;

		boolean assigntask1 = fbp.assignFormTask(tsd1);
		TestValidation.IsTrue(assigntask1, 
				"Form task assigned" + sqtask, 
				"Failed to assign form task" + sqtask);

		boolean applyfilter2 = fbp.openAndApplySettingsForColumn(COLUMNHEADER.FORMNAME, COLUMNSETTING.FILTER,aformName);
		TestValidation.IsTrue(applyfilter2, 
				"Applied Filter on" + COLUMNHEADER.FORMNAME, 
				"Failed to apply filter on" + COLUMNHEADER.FORMNAME);

		TASKDETAILS tsd2 = new TASKDETAILS();
		tsd2.Location = locationInstanceValue;
		tsd2.Resource = suppliersInstanceValue;
		tsd2.TaskName = satask;
		tsd2.Workgroup = wgName;

		boolean assigntask2 = fbp.assignFormTask(tsd2);
		TestValidation.IsTrue(assigntask2, 
				"Form task assigned" + satask, 
				"Failed to assign form task" + satask);

		boolean navigate1 = fbp.navigateToFSQATab(FSQATAB.TASKS);
		TestValidation.IsTrue(navigate1, 
				"Navigated to FSQABrowser>Tasks Tab", 
				"Failed to navigate to FSQABrowser>Tasks Tab");

		String taskname[] = {sctask,sqtask,satask};

		boolean viewtask = fbp.openAndApplySettingsForTaskColumn(COLUMNHEADER.TASKNAME, COLUMNSETTING.FILTER,taskname);
		TestValidation.IsTrue(viewtask, 
				"All tasks are visible", 
				"Failed to verify all tasks");

	}

	@Test(description = "Sorting of all columns in FSQA Browser > Tasks", priority = 3)
	public void TestCase_33111() throws InterruptedException {

		home.clickFSQABrowserMenu();

		boolean navigate = fbp.selectResourceDropDownandNavigate("Customers","Tasks");
		TestValidation.IsTrue(navigate, 
				"Navigated to FSQABrowser>FormsTab", 
				"Failed to navigate to FSQABrowser>Forms Tab");

		fbp.sortColumn(COLUMNHEADER.TASKNAME);
		fbp.sortColumn(COLUMNHEADER.RESOURCE);
		fbp.sortColumn(COLUMNHEADER.LOCATION);
		fbp.sortColumn(COLUMNHEADER.ASSIGNEDTO);
		fbp.sortColumn(COLUMNHEADER.DUEBY);			

	}

	@Test(description = "Change due date of internal task", priority = 4)
	public void TestCase_42940() throws InterruptedException {

		home.clickFSQABrowserMenu();

		boolean navigate = fbp.selectResourceDropDownandNavigate("Customers","Forms");
		TestValidation.IsTrue(navigate, 
				"Navigated to FSQABrowser>FormsTab", 
				"Failed to navigate to FSQABrowser>Forms Tab");

		boolean applyfilter = fbp.openAndApplySettingsForColumn(COLUMNHEADER.FORMNAME, COLUMNSETTING.FILTER,cformName);
		TestValidation.IsTrue(applyfilter, 
				"Applied Filter on" + COLUMNHEADER.FORMNAME, 
				"Failed to apply filter on" + COLUMNHEADER.FORMNAME);

		TASKDETAILS tsd = new TASKDETAILS();
		tsd.Location = locationInstanceValue;
		tsd.Resource = customersInstanceValue;
		tsd.TaskName = duebyTask;
		tsd.Workgroup = wgName;

		boolean assigntask = fbp.assignFormTask(tsd);
		TestValidation.IsTrue(assigntask, 
				"Form task assigned" + sctask, 
				"Failed to assign form task" + sctask);

		boolean navigate1 = fbp.navigateToFSQATab(FSQATAB.TASKS);
		TestValidation.IsTrue(navigate1, 
				"Navigated to FSQABrowser>Tasks Tab", 
				"Failed to navigate to FSQABrowser>Tasks Tab");

		boolean viewtask = fbp.openAndApplySettingsForColumn(COLUMNHEADER.TASKNAME, COLUMNSETTING.FILTER,duebyTask);
		TestValidation.IsTrue(viewtask, 
				"All tasks are visible", 
				"Failed to verify all tasks");

		DateTime dt = new DateTime(driver);
		String Date = dt.getDateTime("Day", 1, 2, 10, true, false);
		boolean changeDueDate = fbp.changeDueByOfTask(duebyTask, Date, null);
		TestValidation.IsTrue(changeDueDate, 
				"CHANGED due date for Task to - " + Date, 
				"Failed to change due date for Task to - " + Date);

		boolean verifyDueBy = fbp.verifyDueDate(Date);
		TestValidation.IsTrue(verifyDueBy, 
				"Verified due by date - " + Date, 
				"Failed to verify due By - " + Date);

	}

	@Test(description = "Task History of internal Task Task Due By Change", priority = 5)
	public void TestCase_16276() throws InterruptedException {

		home.clickFSQABrowserMenu();

		boolean navigate = fbp.selectResourceDropDownandNavigate("Equipment","Forms");
		TestValidation.IsTrue(navigate, 
				"Navigated to FSQABrowser>FormsTab", 
				"Failed to navigate to FSQABrowser>Forms Tab");

		boolean applyfilter = fbp.openAndApplySettingsForColumn(COLUMNHEADER.FORMNAME, COLUMNSETTING.FILTER,aformName);
		TestValidation.IsTrue(applyfilter, 
				"Applied Filter on" + COLUMNHEADER.FORMNAME, 
				"Failed to apply filter on" + COLUMNHEADER.FORMNAME);

		TASKDETAILS tsd = new TASKDETAILS();
		tsd.Location = locationInstanceValue;
		tsd.Resource = equipmentInstanceValue;
		tsd.TaskName = duebyTask;
		tsd.Workgroup = wgName;

		boolean assigntask = fbp.assignFormTask(tsd);
		TestValidation.IsTrue(assigntask, 
				"Form task assigned" + sctask, 
				"Failed to assign form task" + sctask);

		boolean navigate1 = fbp.navigateToFSQATab(FSQATAB.TASKS);
		TestValidation.IsTrue(navigate1, 
				"Navigated to FSQABrowser>Tasks Tab", 
				"Failed to navigate to FSQABrowser>Tasks Tab");

		boolean viewtask = fbp.openAndApplySettingsForColumn(COLUMNHEADER.TASKNAME, COLUMNSETTING.FILTER,duebyTask);
		TestValidation.IsTrue(viewtask, 
				"All tasks are visible", 
				"Failed to verify all tasks");

		DateTime dt = new DateTime(driver);
		String Date = dt.getDateTime("Day", 1, 2, 10, false, false);
		boolean changeDueDate = fbp.changeDueByOfTask(duebyTask, Date, null);
		TestValidation.IsTrue(changeDueDate, 
				"CHANGED due date for Task to - " + Date, 
				"Failed to change due date for Task to - " + Date);

		String date = dt.getDate("Day", 1);
		String time = dt.getTime(2, 10, true, true);		

		HISTORY_DATA data = new HISTORY_DATA();
		data.Workgroup = wgName;
		data.Username = displayName;
		data.AssignedDueBy = "\r\n" + 
				"                            Due By:   No Due Date\r\n" + 
				"                        ";

		data.DueBy = time+" "+"IST"+" on "+date;

		boolean verify = fsqaTasksPage.verifyTaskHistory(duebyTask,HISTORY_ACTIONS.DUEBY, data);
		TestValidation.IsTrue(verify, 
				"Verified Due By Chnaged task details", 
				"Failed to verify due by changed task history detials");
	}

	@Test(description = "Task having no resource should not be visible on FSQA Browser > Task", priority = 6)
	public void TestCase_38186() throws InterruptedException {

		home.clickFSQABrowserMenu();

		boolean navigate = fbp.selectResourceDropDownandNavigate("Customers","Forms");
		TestValidation.IsTrue(navigate, 
				"Navigated to FSQABrowser>FormsTab", 
				"Failed to navigate to FSQABrowser>Forms Tab");

		boolean applyfilter = fbp.openAndApplySettingsForColumn(COLUMNHEADER.FORMNAME, COLUMNSETTING.FILTER,cformName);
		TestValidation.IsTrue(applyfilter, 
				"Applied Filter on" + COLUMNHEADER.FORMNAME, 
				"Failed to apply filter on" + COLUMNHEADER.FORMNAME);

		TASKDETAILS tsd = new TASKDETAILS();
		tsd.Location = locationInstanceValue;
		tsd.Resource = customersInstanceValue;
		tsd.TaskName = duebyTask;

		boolean assigntask = fbp.assignFormTask(tsd);
		TestValidation.IsTrue(assigntask, 
				"Form task assigned" + sctask, 
				"Failed to assign form task" + sctask);

		boolean navigate1 = fbp.navigateToFSQATab(FSQATAB.TASKS);
		TestValidation.IsTrue(navigate1, 
				"Navigated to FSQABrowser>Tasks Tab", 
				"Failed to navigate to FSQABrowser>Tasks Tab");

		boolean viewtask = fbp.openAndApplySettingsForColumn(COLUMNHEADER.TASKNAME, COLUMNSETTING.FILTER,duebyTask);
		TestValidation.IsTrue(viewtask, 
				"All tasks are visible", 
				"Failed to verify all tasks");

		boolean verify = fsqaTasksPage.verifytaskonGrid(false,duebyTask);
		TestValidation.IsTrue(verify, 
				"Task having no resource is not visible on FSQA Browser > Task tab", 
				"Failed to verify as Task having no resource is visible on FSQA Browser > Task tab");		

	}

	@Test(description = "Task created from scheduler should be visible on FSQA Browser > Tasks", priority = 7)
	public void TestCase_32227() throws InterruptedException {

		home.clickFSQABrowserMenu();

		boolean navigate = fbp.selectResourceDropDownandNavigate("Customers","Tasks");
		TestValidation.IsTrue(navigate, 
				"Navigated to FSQABrowser>FormsTab", 
				"Failed to navigate to FSQABrowser>Forms Tab");

		boolean viewtask = fbp.openAndApplySettingsForColumn(COLUMNHEADER.TASKNAME, COLUMNSETTING.FILTER,schedulename);
		TestValidation.IsTrue(viewtask, 
				"All tasks are visible", 
				"Failed to verify all tasks");

		boolean verify = fsqaTasksPage.verifytaskonGrid(true,schedulename);
		TestValidation.IsTrue(verify, 
				"Task created via Task Scheduler is visible", 
				"Task created via Task Scheduler is not visible");					

	}

	@Test(description = "Functionality of grid filters", priority = 8)
	public void TestCase_33116() throws InterruptedException {

		home.clickFSQABrowserMenu();

		boolean navigate = fbp.selectResourceDropDownandNavigate("Customers","Forms");
		TestValidation.IsTrue(navigate, 
				"Navigated to FSQABrowser>FormsTab", 
				"Failed to navigate to FSQABrowser>Forms Tab");

		boolean applyfilter = fbp.openAndApplySettingsForColumn(COLUMNHEADER.FORMNAME, COLUMNSETTING.FILTER,cformName);
		TestValidation.IsTrue(applyfilter, 
				"Applied Filter on" + COLUMNHEADER.FORMNAME, 
				"Failed to apply filter on" + COLUMNHEADER.FORMNAME);

		TASKDETAILS tsd = new TASKDETAILS();
		tsd.Location = locationInstanceValue;
		tsd.Resource = customersInstanceValue;
		tsd.Workgroup = wgName;
		tsd.TaskName = filtertask;

		boolean assigntask = fbp.assignFormTask(tsd);
		TestValidation.IsTrue(assigntask, 
				"Form task assigned" + filtertask, 
				"Failed to assign form task" + filtertask);

		boolean navigate1 = fbp.navigateToFSQATab(FSQATAB.TASKS);
		TestValidation.IsTrue(navigate1, 
				"Navigated to FSQABrowser>Tasks Tab", 
				"Failed to navigate to FSQABrowser>Tasks Tab");

		boolean viewtask = fbp.openAndApplySettingsForColumn(COLUMNHEADER.TASKNAME, COLUMNSETTING.FILTER,filtertask);
		TestValidation.IsTrue(viewtask, 
				"Task name filtered", 
				"Failed to filter task");

		boolean viewtask1 = fbp.openAndApplySettingsForColumn(COLUMNHEADER.LOCATION, COLUMNSETTING.FILTER,locationInstanceValue);
		TestValidation.IsTrue(viewtask1, 
				"Locations is filtered", 
				"Failed to filter location");

		boolean viewtask2 = fbp.openAndApplySettingsForColumn(COLUMNHEADER.ASSIGNEDTO, COLUMNSETTING.FILTER,wgName);
		TestValidation.IsTrue(viewtask2, 
				"Workgroup is filtered", 
				"Failed filter workgroup");

		boolean viewtask3 = fbp.openAndApplySettingsForColumn(COLUMNHEADER.RESOURCE, COLUMNSETTING.FILTER,customersInstanceValue);
		TestValidation.IsTrue(viewtask3, 
				"Resource is filtered", 
				"Failed to filter resource");

		boolean verify = fsqaTasksPage.verifytaskonGrid(true,filtertask);
		TestValidation.IsTrue(verify, 
				"All filters are working", 
				"FSQA Browsers > Taks filters are working");		


	}

	@Test(description = "Task with logged in user location will only be visible in FSQA Browser > Task", priority = 9)
	public void TestCase_38206() throws InterruptedException {

		home.clickFSQABrowserMenu();

		boolean navigate = fbp.selectResourceDropDownandNavigate("Customers","Forms");
		TestValidation.IsTrue(navigate, 
				"Navigated to FSQABrowser>FormsTab", 
				"Failed to navigate to FSQABrowser>Forms Tab");

		boolean applyfilter = fbp.openAndApplySettingsForColumn(COLUMNHEADER.FORMNAME, COLUMNSETTING.FILTER,cformName);
		TestValidation.IsTrue(applyfilter, 
				"Applied Filter on" + COLUMNHEADER.FORMNAME, 
				"Failed to apply filter on" + COLUMNHEADER.FORMNAME);

		TASKDETAILS tsd = new TASKDETAILS();
		tsd.Location = locationInstanceValue;
		tsd.Resource = customersInstanceValue;
		tsd.Workgroup = wgName;
		tsd.TaskName = locTask;

		boolean assigntask = fbp.assignFormTask(tsd);
		TestValidation.IsTrue(assigntask, 
				"Form task assigned" + sctask, 
				"Failed to assign form task" + sctask);

		home.userLogout();
		if(!(login.performLogin(userUN, userPassword, userNewPassword))) {
			TCGFailureMsg = "Could NOT login to application";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		boolean viewtask = fbp.openAndApplySettingsForColumn(COLUMNHEADER.TASKNAME, COLUMNSETTING.FILTER,locTask);
		TestValidation.IsTrue(viewtask, 
				"All tasks are visible", 
				"Failed to verify all tasks");		

		boolean verify = fsqaTasksPage.verifytaskonGrid(false,locTask);
		TestValidation.IsTrue(verify, 
				"Task having no resource is not visible on FSQA Browser > Task tab", 
				"Failed to verify as Task having no resource is visible on FSQA Browser > Task tab");			
	}


	@Test(description = "Task Monitoring || FSQA Browser - The Task Summary panel contains a Task Status analytic chart")
	public void TestCase_15420() throws InterruptedException {
		//duebyTask="Task_11.11_22.25.44";

		//navigate to FSQA->Tasks
		home.clickFSQABrowserMenu();

		boolean navigate = fbp.selectResourceDropDownandNavigate("Customers","Tasks");
		TestValidation.IsTrue(navigate, 
				"Navigated to FSQABrowser>FormsTab", 
				"Failed to navigate to FSQABrowser>Forms Tab");
//
//		//verify task status anlaytical chart
//
		boolean verifyLocationOfAnalyticalChart = fbp.verifyLocationOfAnalyticalChart();
		TestValidation.IsTrue(verifyLocationOfAnalyticalChart, 
				"Task Status Analytical Chart is shown in Task Summary Panel", 
				"Failed, Analytical Chart is not displayed undr Task Summary Panel ");

		// verify task status % and order of task in  outerdonut
		boolean verifyOuterDonut = fbp.verifyOuterDonut();
		TestValidation.IsTrue(verifyOuterDonut, 
				"Verified Outer Donut", 
				"Failed, to verify Outer Donut");

		//verify task status % shown in various color & order of task in inner donut
		boolean verifyInnerDonut = fbp.verifyInnerDonut();
		TestValidation.IsTrue(verifyInnerDonut, 
				"Verified Inner Donut", 
				"Failed, to verify Inner Donut");

		//verify title

		boolean verifyTitleOfAnalyticalChart = fbp.verifyTitleOfAnalyticalChart();
		TestValidation.IsTrue(verifyTitleOfAnalyticalChart, 
				"Verified Title of Analytical charts is Task Status", 
				"Failed, to verify Analytical charts is Task Status");
		
		//verify structure of analytical chart

		boolean verifyStructureOfAnalyticalChart = fbp.verifyStructureOfAnalyticalChart();
		TestValidation.IsTrue(verifyStructureOfAnalyticalChart, 
				"Task Status Analytical Chart has 2 donut shapes", 
				"Failed, Analytical Chart don't have donut shape ");
		
		//change resource category & verify % based on task

		navigate = fbp.selectResourceDropDownandNavigate("Customers","Tasks");
		TestValidation.IsTrue(navigate, 
				"Navigated to FSQABrowser>Tasks Tab & Supplier Resource", 
				"Failed to navigate to FSQABrowser>Tasks Tab & Supplier Resource");

		boolean viewChartValues = fbp.viewChartValues();
		TestValidation.IsTrue(viewChartValues, 
				"Chart values are verified", 
				"Failed, chart values aren't verified");

		//filter using taskname & chart should get updated

		boolean applyfilter1 = fbp.openAndApplySettingsForColumn(COLUMNHEADER.TASKNAME, COLUMNSETTING.FILTER,duebyTask);
		TestValidation.IsTrue(applyfilter1, 
				"Applied Filter on" + COLUMNHEADER.TASKNAME, 
				"Failed to apply filter on" + COLUMNHEADER.TASKNAME);

		viewChartValues = fbp.viewChartValues();
		TestValidation.IsTrue(viewChartValues, 
				"Chart values are verified", 
				"Failed, chart values aren't verified");


	}


	@AfterClass(alwaysRun = true)
	public void closeBrowser() throws InterruptedException {
		driver.close();
	}
}