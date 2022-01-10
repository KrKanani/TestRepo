package com.project.safetychain.webapp.testcases;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.project.safetychain.webapp.pageobjects.FSQABrowserPage;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage;
import com.project.safetychain.webapp.pageobjects.HomePage;
import com.project.safetychain.webapp.pageobjects.InboxPage;
import com.project.safetychain.webapp.pageobjects.LoginPage;
import com.project.safetychain.webapp.pageobjects.ManageLocationPage;
import com.project.safetychain.webapp.pageobjects.ManageResourcePage;
import com.project.safetychain.webapp.pageobjects.ResourceDesignerPage;
import com.project.safetychain.webapp.pageobjects.UserManagerPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.COLUMNHEADER;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.COLUMNSETTING;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.FSQARESOURCE;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.FSQATAB;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.TASKDETAILS;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.TASK_TYPE;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.TASK_VALUE;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FIELD_TYPES;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FORMRESOURCETYPES;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FORMTYPES;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FormDesignParams;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FormFieldParams;
import com.project.safetychain.webapp.pageobjects.FSQABrowserRecordsPage;
import com.project.safetychain.webapp.pageobjects.ManageLocationPage.LocationInstanceParams;
import com.project.safetychain.webapp.pageobjects.ManageResourcePage.RESOURCETYPES;
import com.project.safetychain.webapp.pageobjects.ManageResourcePage.ResourceDetailParams;
import com.project.safetychain.webapp.pageobjects.ResourceDesignerPage.CATEGORYTYPES;
import com.project.safetychain.webapp.pageobjects.UserManagerPage.USERFIELDS;
import com.project.safetychain.webapp.testcases.support.FormOperations;
import com.project.safetychain.webapp.testcases.support.FormOperations.FormDetails;
import com.project.safetychain.webapp.pageobjects.WorkGroupsPage;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.utilities.CommonMethods;
import com.project.utilities.ControlActions;
import com.project.utilities.DateTime;


public class TCG_REG_Record_TaskHistory extends TestBase{
	
	ControlActions controlActions;
	public static String formName;
	public static String workGroupName;
	public static String newWorkGroupName;
	public static String locationCategoryValue;	
	public static String locationInstanceValue;	
	public static String customersCategoryValue;
	public static String customersInstanceValue;
	public static String displayName = null;
	public static String usrTimezone = null;
	public static String timezoneCode = null;
	public static String currentDate;
	public static String formTaskName1;
	public static String formTaskName2;
	public static String formTaskName3;
	public static String newFormTaskName3;
	public static final String PARA_VALUE_TASK_1 = "Task 1";
	public static final String PARA_VALUE_TASK_2 = "Task 2";
	public static final String PARA_VALUE_TASK_3 = "Task 3";
	public static final String NOTE_TASK = "Note Test";
	public static String paraFieldName = "Paragraph";

	@BeforeClass(alwaysRun = true)
	public void groupInit() throws InterruptedException {

		formName = CommonMethods.dynamicText("RTaskHist");
		formTaskName1 = CommonMethods.dynamicText("Task1");
		formTaskName2 = CommonMethods.dynamicText("Task2");
		formTaskName3 = CommonMethods.dynamicText("Task2");
		newFormTaskName3 = CommonMethods.dynamicText("New_Task");
		workGroupName = CommonMethods.dynamicText("WG");
		newWorkGroupName = CommonMethods.dynamicText("NEW_WG");
		locationCategoryValue = CommonMethods.dynamicText("Loc_Cat");	
		locationInstanceValue = CommonMethods.dynamicText("Loc_Inst");	
		customersCategoryValue = CommonMethods.dynamicText("Cust_Cat");
		customersInstanceValue = CommonMethods.dynamicText("Cust_Inst");

		driver = launchbrowser();
		controlActions = new ControlActions(driver);
		controlActions.getUrl(applicationUrl);
		
		LoginPage lp = new LoginPage(driver);
		HomePage hp = lp.performLogin(adminUsername, adminPassword);
		if(hp.error) {
			TCGFailureMsg = "Could NOT login to application";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
		
		displayName = hp.getLoggedInUserDetails();
		if(displayName == ""){
			TCGFailureMsg = "Could NOT get display name for user - " + adminUsername;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
		
		UserManagerPage ump = hp.clickUsersMenu();
		if(!ump.searchUsrWithDetails(USERFIELDS.USERNAME, adminUsername)) {
			TCGFailureMsg = "Failed to search user - " + adminUsername;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
		
		usrTimezone = ump.getUsrDetails(USERFIELDS.TIMEZONE);
		if(usrTimezone == ""){
			TCGFailureMsg = "Failed to get timezone for user - " + adminUsername;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
		
		timezoneCode = ump.getTimezoneCode(usrTimezone);
		if(timezoneCode == ""){
			TCGFailureMsg = "Failed to convert timezone - " + usrTimezone+ " for user - " + adminUsername;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
		
		hp.clickFSQABrowserMenu();
		WorkGroupsPage wgp = new WorkGroupsPage(driver);
		if(!wgp.createWorkGroup(workGroupName, adminUsername)) {
			TCGFailureMsg = "Could not create workgroup - " + workGroupName;
			logFatal(TCGFailureMsg);

		}
		
		//Category creation
		HashMap<String,String> categories = new HashMap<String, String>();
		categories.put(CATEGORYTYPES.CUSTOMERS, customersCategoryValue);
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
		instances.put(customersInstanceValue, true);
		ResourceDetailParams rd = new ResourceDetailParams();
		rd.CategoryName = customersCategoryValue;
		rd.CategoryType = RESOURCETYPES.CUSTOMERS;
		rd.NumberFieldValue = 30;
		rd.TextFieldValue = "ABC";
		rd.InstanceNames = instances;
		rd.LocationInstanceValue = locationInstanceValue;	
		ManageResourcePage mrp = hp.clickResourcesMenu();
		if(!mrp.createResourceLinkLocation(rd)) {
			TCGFailureMsg = "Could NOT create Instances for resource - " + customersCategoryValue;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
		
		//FORM - Creation and Release
		HashMap<String, List<String>> fields = new HashMap<String, List<String>>();
		fields.put(FIELD_TYPES.PARAGRAPH_TEXT, Arrays.asList(paraFieldName));

		FormFieldParams ffp = new FormFieldParams();
		ffp.FieldDetails = fields;

		HashMap<String, List<String>> resource = new HashMap<String, List<String>>();
		resource.put(FORMRESOURCETYPES.CUSTOMERS, Arrays.asList(customersCategoryValue));

		FormDesignParams fdpDets = new FormDesignParams();
		fdpDets.FormType = FORMTYPES.CHECK;
		fdpDets.FormName = formName;
		fdpDets.SelectResources = resource;
		fdpDets.DesignFields = Arrays.asList(ffp);

		FormDesignerPage fdp = hp.clickFormDesignerMenu();
		if(!fdp.createAndReleaseForm(fdpDets)) {
			TCGFailureMsg = "Could NOT create unreleased form - " + formName;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

	}

	@Test(description = "Records Management - Task History - Verify An event is shown in the record's "
			+ "Task History whenever a task is assigned")
	public void TestCase_32251() {
		
		HomePage hp = new HomePage(driver);	
		FSQABrowserPage fbp = hp.clickFSQABrowserMenu();
		boolean navigateToRecords1 = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.CUSTOMERS,FSQATAB.FORMS);
		TestValidation.IsTrue(navigateToRecords1, 
							  "For customer category, NAVIGATED to FSQABrowser > Forms tab", 
							  "Failed to navigate to FSQABrowser > Forms tab");
		
		boolean applyfilter1 = fbp.openAndApplySettingsForColumn(COLUMNHEADER.FORMNAME, COLUMNSETTING.FILTER,formName);
		TestValidation.IsTrue(applyfilter1, 
							  "Applied Filter on" + COLUMNHEADER.FORMNAME, 
							  "Failed to apply filter on" + COLUMNHEADER.FORMNAME);

		TASKDETAILS tsd = new TASKDETAILS();
		tsd.Location = locationInstanceValue;
		tsd.Resource = customersInstanceValue;
		tsd.TaskName = formTaskName1;
		tsd.Workgroup = workGroupName;
		tsd.TaskType = TASK_TYPE.NO_DUE_DATE;
		tsd.Notes = NOTE_TASK;
		boolean assigntask = fbp.assignFormTask(tsd);
		TestValidation.IsTrue(assigntask, 
							  "Form task assigned" + formTaskName1, 
							  "Failed to assign form task" + formTaskName1);
		
		InboxPage ip = hp.clickInboxMenu();
		boolean completetask = ip.selectFormTask(formTaskName1);		
		TestValidation.IsTrue(completetask, 
							  "Form task selected" + formTaskName1, 
							  "Failed to select form task" + formTaskName1);
		
		HashMap<String, List<String>> fillDetails = new HashMap<String, List<String>>();
		fillDetails.put(paraFieldName, Arrays.asList(PARA_VALUE_TASK_1));

		FormDetails fd = new FormDetails();
		fd.fieldDetails = fillDetails;
		FormOperations fo = new FormOperations(driver);
		boolean submit = fo.submitData(fd);
		TestValidation.IsTrue(submit, 
							  "Form submitted" + formName, 
							  "Failed to submit form" + formName);
		
		fbp = hp.clickFSQABrowserMenu();
		boolean navigateToRecords2 = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.CUSTOMERS,FSQATAB.RECORDS);
		TestValidation.IsTrue(navigateToRecords2, 
							  "For customer category, NAVIGATED to FSQABrowser > Records tab", 
							  "Failed to navigate to FSQABrowser > Records tab");
		
		boolean applyfilter2 = fbp.openAndApplySettingsForColumn(COLUMNHEADER.FORMNAME, COLUMNSETTING.FILTER,formName);
		TestValidation.IsTrue(applyfilter2, 
							  "Applied Filter on" + COLUMNHEADER.FORMNAME, 
							  "Failed to apply filter on" + COLUMNHEADER.FORMNAME);
		
		boolean openRecord = fbp.openForDetails(1);
		TestValidation.IsTrue(openRecord, 
							  "OPENED record for form - " + formName, 
							  "Failed to open record for form - " + formName);
		
		DateTime dt = new DateTime(driver);
		currentDate = dt.getTodayDate("MM/dd/YYYY", dt.getTimezone(usrTimezone));
		
		FSQABrowserRecordsPage frp = new FSQABrowserRecordsPage(driver);
		List<String> details = new ArrayList<String>();
		details.add("Assigned to " + workGroupName + " by " + displayName + "|" +
					"No Due Date" + "|" + timezoneCode  + "|" + currentDate  + "|" + NOTE_TASK);
		details.add("Submitted by " + displayName + "|" +
					"No Due Date" + "|" + timezoneCode + "|" + currentDate);
		boolean verifyHistory = frp.verifyTaskHistoryPopupDetails(details);
		TestValidation.IsTrue(verifyHistory, 
				  			  "VERIFIED the Task History details whenever a task is assigned", 
				  			  "Failed to verify the Task History details whenever a task is assigned");
		
	}

	@Test(description = "Records Management - Task History - Verify An event is shown in the record's Task History "
			+ "whenever a task's Due By date/time is changed")
	public void TestCase_32254() {
		
		HomePage hp = new HomePage(driver);	
		FSQABrowserPage fbp = hp.clickFSQABrowserMenu();
		boolean navigateToRecords1 = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.CUSTOMERS,FSQATAB.FORMS);
		TestValidation.IsTrue(navigateToRecords1, 
							  "For customer category, NAVIGATED to FSQABrowser > Forms tab", 
							  "Failed to navigate to FSQABrowser > Forms tab");
		
		boolean applyfilter1 = fbp.openAndApplySettingsForColumn(COLUMNHEADER.FORMNAME, COLUMNSETTING.FILTER,formName);
		TestValidation.IsTrue(applyfilter1, 
							  "Applied Filter on" + COLUMNHEADER.FORMNAME, 
							  "Failed to apply filter on" + COLUMNHEADER.FORMNAME);


		DateTime dt = new DateTime(driver);
		String oldDate = dt.getDateTime("Day", TASK_VALUE.PAST_DUE, TASK_VALUE.PAST_DUE_HOUR, 
				TASK_VALUE.PAST_DUE_MIN, false, false);
		
		TASKDETAILS tsd = new TASKDETAILS();
		tsd.Location = locationInstanceValue;
		tsd.Resource = customersInstanceValue;
		tsd.TaskName = formTaskName2;
		tsd.Workgroup = workGroupName;
		tsd.TaskType = TASK_TYPE.PAST_DUE;
		tsd.Notes = NOTE_TASK;
		boolean assigntask = fbp.assignFormTask(tsd);
		TestValidation.IsTrue(assigntask, 
							  "Form task assigned" + formTaskName2, 
							  "Failed to assign form task" + formTaskName2);
		
		//change due date
		boolean navigateToTasks1 = fbp.navigateToFSQATab(FSQATAB.TASKS);
		TestValidation.IsTrue(navigateToTasks1, 
							  "Navigated to FSQABrowser>Tasks Tab", 
							  "Failed to navigate to FSQABrowser>Tasks Tab");			

		boolean applyfilter2 = fbp.openAndApplySettingsForColumn(COLUMNHEADER.TASKNAME, COLUMNSETTING.FILTER,formTaskName2);
		TestValidation.IsTrue(applyfilter2, 
							  "Applied Filter on" + COLUMNHEADER.TASKNAME, 
							  "Failed to apply filter on" + COLUMNHEADER.TASKNAME);

		String updatedDate = dt.getDateTime("Day", 1, 2, 10, true, false);
		boolean changeDueDate = fbp.changeDueByOfTask(formTaskName2, updatedDate, null);
		TestValidation.IsTrue(changeDueDate, 
							  "CHANGED due date for Task to - " + updatedDate, 
							  "Failed to change due date for Task to - " + updatedDate);
		
		InboxPage ip = hp.clickInboxMenu();
		boolean completetask = ip.selectFormTask(formTaskName2);		
		TestValidation.IsTrue(completetask, 
							  "Form task selected" + formTaskName2, 
							  "Failed to select form task" + formTaskName2);
		
		HashMap<String, List<String>> fillDetails = new HashMap<String, List<String>>();
		fillDetails.put(paraFieldName, Arrays.asList(PARA_VALUE_TASK_2));

		FormDetails fd = new FormDetails();
		fd.fieldDetails = fillDetails;
		FormOperations fo = new FormOperations(driver);
		boolean submit = fo.submitData(fd);
		TestValidation.IsTrue(submit, 
							  "Form submitted" + formName, 
							  "Failed to submit form" + formName);
		
		fbp = hp.clickFSQABrowserMenu();
		boolean navigateToRecords2 = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.CUSTOMERS,FSQATAB.RECORDS);
		TestValidation.IsTrue(navigateToRecords2, 
							  "For customer category, NAVIGATED to FSQABrowser > Records tab", 
							  "Failed to navigate to FSQABrowser > Records tab");
		
		boolean applyfilter3 = fbp.openAndApplySettingsForColumn(COLUMNHEADER.FORMNAME, COLUMNSETTING.FILTER,formName);
		TestValidation.IsTrue(applyfilter3, 
							  "Applied Filter on" + COLUMNHEADER.FORMNAME, 
							  "Failed to apply filter on" + COLUMNHEADER.FORMNAME);
		
		boolean openRecord = fbp.openForDetails(1);
		TestValidation.IsTrue(openRecord, 
							  "OPENED record for form - " + formName, 
							  "Failed to open record for form - " + formName);
		
		currentDate = dt.getTodayDate("MM/dd/YYYY", dt.getTimezone(usrTimezone));
		String oldDateTime[] = CommonMethods.splitAndGetString(oldDate, " ");
		String newDateTime[] = CommonMethods.splitAndGetString(updatedDate, " ");
		FSQABrowserRecordsPage frp = new FSQABrowserRecordsPage(driver);
		List<String> details = new ArrayList<String>();
		details.add("Assigned to " + workGroupName + " by " + displayName + "|" +
				NOTE_TASK + "|" + timezoneCode  + "|" + currentDate  + "|" + oldDateTime[0]);
		details.add("Due By changed by " + displayName + "|" +
				timezoneCode  + "|" + currentDate  + "|" + newDateTime[0] + "|" + newDateTime[1]);
		details.add("Submitted by " + displayName + "|" +
				timezoneCode  + "|" + currentDate  + "|" + newDateTime[0] + "|" + newDateTime[1]);
		boolean verifyHistory = frp.verifyTaskHistoryPopupDetails(details);
		TestValidation.IsTrue(verifyHistory, 
				  			  "VERIFIED the Task History details whenever a task is assigned", 
				  			  "Failed to verify the Task History details whenever a task is assigned");
		
	}
	
	@Test(description = "Records Management - Task History - Verify An event is shown in the record's "
			+ "Task History whenever a task is reassigned")
	public void TestCase_32253() {

		WorkGroupsPage wgp = new WorkGroupsPage(driver);
		boolean wgCreated = wgp.createWorkGroup(newWorkGroupName, adminUsername);
		TestValidation.IsTrue(wgCreated, 
							  "Workgroup CREATED with name - " + newWorkGroupName, 
							  "Failed to create Workgroup with name - " + newWorkGroupName);
		
		HomePage hp = new HomePage(driver);	
		FSQABrowserPage fbp = hp.clickFSQABrowserMenu();
		boolean navigateToRecords1 = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.CUSTOMERS,FSQATAB.FORMS);
		TestValidation.IsTrue(navigateToRecords1, 
							  "For customer category, NAVIGATED to FSQABrowser > Forms tab", 
							  "Failed to navigate to FSQABrowser > Forms tab");
		
		boolean applyfilter1 = fbp.openAndApplySettingsForColumn(COLUMNHEADER.FORMNAME, COLUMNSETTING.FILTER,formName);
		TestValidation.IsTrue(applyfilter1, 
							  "Applied Filter on" + COLUMNHEADER.FORMNAME, 
							  "Failed to apply filter on" + COLUMNHEADER.FORMNAME);

		DateTime dt = new DateTime(driver);
		String oldDate = dt.getDateTime("Day", TASK_VALUE.DUE_LATER, TASK_VALUE.DUE_LATER_HOUR, 
				TASK_VALUE.DUE_LATER_MIN, false, false);
		
		
		TASKDETAILS tsd = new TASKDETAILS();
		tsd.Location = locationInstanceValue;
		tsd.Resource = customersInstanceValue;
		tsd.TaskName = formTaskName3;
		tsd.Workgroup = workGroupName;
		tsd.TaskType = TASK_TYPE.DUE_LATER;
		tsd.Notes = NOTE_TASK;
		boolean assigntask = fbp.assignFormTask(tsd);
		TestValidation.IsTrue(assigntask, 
							  "Form task assigned" + formTaskName3, 
							  "Failed to assign form task" + formTaskName3);
		
		boolean navigateToTasks1 = fbp.navigateToFSQATab(FSQATAB.TASKS);
		TestValidation.IsTrue(navigateToTasks1, 
							  "Navigated to FSQABrowser>Tasks Tab", 
							  "Failed to navigate to FSQABrowser>Tasks Tab");			

		boolean applyfilter2 = fbp.openAndApplySettingsForColumn(COLUMNHEADER.TASKNAME, COLUMNSETTING.FILTER,formTaskName2);
		TestValidation.IsTrue(applyfilter2, 
							  "Applied Filter on" + COLUMNHEADER.TASKNAME, 
							  "Failed to apply filter on" + COLUMNHEADER.TASKNAME);

		String updatedDate = dt.getDateTime("Day", TASK_VALUE.PAST_DUE, TASK_VALUE.PAST_DUE_HOUR, 
											TASK_VALUE.PAST_DUE_MIN, true, false);
		
		TASKDETAILS reassignTsd = new TASKDETAILS();
		reassignTsd.TaskName = formTaskName3;
		reassignTsd.UpdatedTaskName = newFormTaskName3;
		reassignTsd.Workgroup = newWorkGroupName;
		reassignTsd.DueByDate = updatedDate;
		boolean reassignTask = fbp.reassignFormTask(reassignTsd);
		TestValidation.IsTrue(reassignTask, 
							  "REASSIGNED form task - " + newFormTaskName3, 
							  "Failed to reassign form task - " + newFormTaskName3);
		
		InboxPage ip = hp.clickInboxMenu();
		boolean completetask = ip.selectFormTask(newFormTaskName3);		
		TestValidation.IsTrue(completetask, 
							  "Form task selected" + newFormTaskName3, 
							  "Failed to select form task" + newFormTaskName3);
		
		HashMap<String, List<String>> fillDetails = new HashMap<String, List<String>>();
		fillDetails.put(paraFieldName, Arrays.asList(PARA_VALUE_TASK_3));

		FormDetails fd = new FormDetails();
		fd.fieldDetails = fillDetails;
		FormOperations fo = new FormOperations(driver);
		boolean submit = fo.submitData(fd);
		TestValidation.IsTrue(submit, 
							  "Form submitted" + formName, 
							  "Failed to submit form" + formName);
		
		fbp = hp.clickFSQABrowserMenu();
		boolean navigateToRecords2 = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.CUSTOMERS,FSQATAB.RECORDS);
		TestValidation.IsTrue(navigateToRecords2, 
							  "For customer category, NAVIGATED to FSQABrowser > Records tab", 
							  "Failed to navigate to FSQABrowser > Records tab");
		
		boolean applyfilter3 = fbp.openAndApplySettingsForColumn(COLUMNHEADER.FORMNAME, COLUMNSETTING.FILTER,formName);
		TestValidation.IsTrue(applyfilter3, 
							  "Applied Filter on" + COLUMNHEADER.FORMNAME, 
							  "Failed to apply filter on" + COLUMNHEADER.FORMNAME);
		
		boolean openRecord = fbp.openForDetails(1);
		TestValidation.IsTrue(openRecord, 
							  "OPENED record for form - " + formName, 
							  "Failed to open record for form - " + formName);
		
		currentDate = dt.getTodayDate("MM/dd/YYYY", dt.getTimezone(usrTimezone));
		String oldDateTime[] = CommonMethods.splitAndGetString(oldDate, " ");
		String newDateTime[] = CommonMethods.splitAndGetString(updatedDate, " ");
		FSQABrowserRecordsPage frp = new FSQABrowserRecordsPage(driver);
		List<String> details = new ArrayList<String>();
		details.add("Assigned to " + workGroupName + " by " + displayName + "|" +
				NOTE_TASK + "|" + timezoneCode  + "|" + currentDate  + "|" + oldDateTime[0]);
		details.add("Reassigned to " + newWorkGroupName + " by " + displayName + "|" +
				NOTE_TASK + "|" + timezoneCode  + "|" + currentDate  + "|" + newDateTime[0] + "|" + newDateTime[1]);
		details.add("Submitted by " + displayName + "|" +
				timezoneCode  + "|" + currentDate  + "|" + newDateTime[0] + "|" + newDateTime[1]);
		boolean verifyHistory = frp.verifyTaskHistoryPopupDetails(details);
		TestValidation.IsTrue(verifyHistory, 
				  			  "VERIFIED the Task History details whenever a task is reassigned", 
				  			  "Failed to verify the Task History details whenever a task is reassigned");
		
	}
	
	@AfterClass(alwaysRun = true)
	public void closeBrowser() throws InterruptedException {
		driver.close();
	}
}

