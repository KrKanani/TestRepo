package com.project.safetychain.pcapp.testcases;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.project.safetychain.pcapp.pageobjects.CommonScreen;
import com.project.safetychain.pcapp.pageobjects.FileOperations;
import com.project.safetychain.pcapp.pageobjects.FormViewScreen;
import com.project.safetychain.pcapp.pageobjects.InboxScreen;
import com.project.safetychain.pcapp.pageobjects.LoginScreen;
import com.project.safetychain.pcapp.pageobjects.SavedScreen;
import com.project.safetychain.pcapp.pageobjects.SubmissionsScreen;
import com.project.safetychain.pcapp.pageobjects.FileOperations.AvailableFormDetails;
import com.project.safetychain.pcapp.pageobjects.FileOperations.AvailableTaskDetails;
import com.project.safetychain.pcapp.pageobjects.FileOperations.AvailableUserDetails;
import com.project.safetychain.pcapp.pageobjects.FormViewScreen.FormDetailsPC;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.utilities.ControlActions_PCApp;


public class TCG_SMK_Inbox_TaskFlow extends TestBase{
	ControlActions_PCApp controlActionsPC;
	CommonScreen commonScreen;
	InboxScreen inboxScreen;
	FormViewScreen formview; 
	FormDetailsPC formDetailsPC;
	SavedScreen savedScreen;
	SubmissionsScreen submissionScreen;

	public static String locationInstanceValue1;
	public static String resourceInstanceValue2;

	static int totalTaskCount = 6;

	String NumericFieldName = "Num 1";

	public static String auditFormName2;	

	FileOperations storeData, fetchData;
	AvailableFormDetails availableData;
	AvailableTaskDetails taskData;
	AvailableUserDetails userData;


	public static String pastDueTask, dueTodayTask, dueLaterTask, noDueDateTask;
	public static String auditSubmitTask, auditSaveTask;
	public static String workGroupName;

	@BeforeClass(alwaysRun = true)
	public void groupInit() {
		fetchData = new FileOperations();
		availableData = fetchData.getCreatedData();
		taskData  = fetchData.getCreatedTaskData();
		userData = fetchData.getCreatedUserData();

		locationInstanceValue1 = availableData.locationInstanceValue1;
		resourceInstanceValue2 = availableData.resourceInstanceValue2;
		auditFormName2 = availableData.Audit_Form2;

		pastDueTask = taskData.pastDueTask;
		dueTodayTask = taskData.dueTodayTask; 
		dueLaterTask = taskData.dueLaterTask;
		noDueDateTask = taskData.dueLaterTask;
		auditSubmitTask = taskData.submitTask;
		auditSaveTask = taskData.saveTask;
		workGroupName = taskData.workGroupName;

		PCAppDriver = launchPCApp();
		controlActionsPC = new ControlActions_PCApp(PCAppDriver);
		commonScreen = new CommonScreen(PCAppDriver);
		inboxScreen = new InboxScreen(PCAppDriver);
		formview = new FormViewScreen(PCAppDriver);
		savedScreen = new SavedScreen(PCAppDriver);
		submissionScreen = new SubmissionsScreen(PCAppDriver);

		LoginScreen loginScreen = new LoginScreen(PCAppDriver);
		CommonScreen commonScreen = loginScreen.Login(pcAppTenantname, userData.username, userData.password);
		if(commonScreen.error) {
			TCGFailureMsg = "COULD not login to the application";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
	}

	@Test(description="Inbox || Verify the task count in 'Inbox'", priority=0)
	public void TestCase_34816() {
		InboxScreen selectInboxTab  = commonScreen.selectInbox();
		TestValidation.IsFalse(selectInboxTab.error,
				"OPENED Inbox Tab", 
				"COULD not open Inbox Tab");

		boolean verifyInboxTaskCount  = inboxScreen.verifyTaskCount(totalTaskCount);
		TestValidation.IsTrue(verifyInboxTaskCount, 
				"VERIFIED task count - "+totalTaskCount, 
				"FAILED to verify task count "+totalTaskCount);
	}

	@Test(description="Inbox || Verify user should be able to submit the task")
	public void TestCase_34817() {
		formDetailsPC = new FormDetailsPC();
		formDetailsPC.selectLocationResource = false;
		formDetailsPC.locationName = locationInstanceValue1;
		formDetailsPC.resourceName = resourceInstanceValue2;

		InboxScreen selectInboxTab  = commonScreen.selectInbox();
		TestValidation.IsFalse(selectInboxTab.error,
				"OPENED Inbox Tab", 
				"COULD not open Inbox Tab");

		boolean openTask = selectInboxTab.selectOpenTask(auditSubmitTask, false);
		TestValidation.IsTrue(openTask, 
				"SELECTED & opened task - "+auditSubmitTask, 
				"FAILED to open task - "+auditSubmitTask);

		HashMap<String, List<String>> allFields = new LinkedHashMap<String, List<String>>();
		allFields.put("Num 1",Arrays.asList("34"));
		formDetailsPC.fieldDetails = allFields;

		boolean submitData = formview.fillDataInForm1(formDetailsPC);
		TestValidation.IsTrue(submitData, 
				"SUBMITTED data for task - "+auditSubmitTask, 
				"FAILED to submit data fo task "+auditSubmitTask);

		totalTaskCount--;
	}

	@Test(description="Inbox || Verify user should be able to save the task")
	public void TestCase_34818() {
		InboxScreen selectInboxTab  = commonScreen.selectInbox();
		TestValidation.IsFalse(selectInboxTab.error,
				"OPENED Inbox Tab", 
				"COULD not open Inbox Tab");

		boolean openTask = selectInboxTab.selectOpenTask(auditSaveTask, false);
		TestValidation.IsTrue(openTask, 
				"SELECTED & opened task - "+auditSaveTask, 
				"FAILED to open task - "+auditSaveTask);


		boolean saveTask = selectInboxTab.saveTask();
		TestValidation.IsTrue(saveTask, 
				"SAVED task - "+auditSaveTask, 
				"FAILED to save the task - "+auditSaveTask);

		savedScreen = commonScreen.selectSaved();
		TestValidation.IsFalse(savedScreen.error,
				"OPENED Saved Tab", 
				"COULD not open Saved Tab");

		boolean verifySavedTask = savedScreen.verifySavedFormAvailability(auditFormName2);
		TestValidation.IsTrue(verifySavedTask, 
				"VERIFIED in saved form(task) - "+auditFormName2, 
				"FAILED to verify saved form(task) - "+auditFormName2);
	}

	@Test(description="Inbox || Verify user should be able search the task")
	public void TestCase_34823() {
		InboxScreen selectInboxTab  = commonScreen.selectInbox();
		TestValidation.IsFalse(selectInboxTab.error,
				"OPENED Inbox Tab", 
				"COULD not open Inbox Tab");

		boolean verifyInboxTaskSearch  = inboxScreen.searchTask(dueTodayTask);
		TestValidation.IsTrue(verifyInboxTaskSearch, 
				"VERIFIED task search - "+dueTodayTask, 
				"FAILED to verify task search - "+dueTodayTask);
	}

	@Test(description="Inbox || Verify task details")
	public void TestCase_34824() {
		InboxScreen selectInboxTab  = commonScreen.selectInbox();
		TestValidation.IsFalse(selectInboxTab.error,
				"OPENED Inbox Tab", 
				"COULD not open Inbox Tab");

		boolean verifyInboxTaskDetails  = inboxScreen.verifyTaskDetails(pastDueTask, workGroupName, resourceInstanceValue2, "Past Due");
		TestValidation.IsTrue(verifyInboxTaskDetails, 
				"VERIFIED task details for task - "+pastDueTask, 
				"FAILED to verify task details for task - "+pastDueTask);
	}

	@Test(description="Submission || Verify the task submission should be shown")
	public void TestCase_34826() {
		formDetailsPC = new FormDetailsPC();
		formDetailsPC.selectLocationResource = false;
		formDetailsPC.locationName = locationInstanceValue1;
		formDetailsPC.resourceName = resourceInstanceValue2;

		InboxScreen selectInboxTab  = commonScreen.selectInbox();
		TestValidation.IsFalse(selectInboxTab.error,
				"OPENED Inbox Tab", 
				"COULD not open Inbox Tab");

		boolean openTask = selectInboxTab.selectOpenTask(dueLaterTask, false);
		TestValidation.IsTrue(openTask, 
				"SELECTED & opened task - "+dueLaterTask, 
				"FAILED to open task - "+dueLaterTask);

		HashMap<String, List<String>> allFields = new LinkedHashMap<String, List<String>>();
		allFields.put("Num 1",Arrays.asList("34"));
		formDetailsPC.fieldDetails = allFields;

		boolean submitData = formview.fillDataInForm1(formDetailsPC);
		TestValidation.IsTrue(submitData, 
				"SUBMITTED data for task - "+dueLaterTask, 
				"FAILED to submit data fo task - "+dueLaterTask);

		totalTaskCount--;

		SubmissionsScreen selectSubmissionTab  = commonScreen.selectSubmissions();
		TestValidation.IsFalse(selectSubmissionTab.error,
				"OPENED Submissions Tab", 
				"COULD not open Submissions Tab");

		boolean verifySubmission = selectSubmissionTab.verifySubmissionAvailability(auditFormName2);
		TestValidation.IsTrue(verifySubmission, 
				"TASK submission is shown for form - "+auditFormName2, 
				"FAILED to verify task submission for form - "+auditFormName2);
	}

	@AfterClass(alwaysRun = true)
	public void closeBrowser() throws InterruptedException {
		PCAppDriver.close();
	}

}
