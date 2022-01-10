package com.project.safetychain.pcapp.testcases;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.project.safetychain.pcapp.pageobjects.ChartScreen;
import com.project.safetychain.pcapp.pageobjects.CommonScreen;
import com.project.safetychain.pcapp.pageobjects.DeviceManagementScreen;
import com.project.safetychain.pcapp.pageobjects.FileOperations;
import com.project.safetychain.pcapp.pageobjects.FormViewScreen;
import com.project.safetychain.pcapp.pageobjects.FormsScreen;
import com.project.safetychain.pcapp.pageobjects.InboxScreen;
import com.project.safetychain.pcapp.pageobjects.LINKScreen;
import com.project.safetychain.pcapp.pageobjects.LoginScreen;
import com.project.safetychain.pcapp.pageobjects.SettingsScreen;
import com.project.safetychain.pcapp.pageobjects.SubmissionsScreen;
import com.project.safetychain.pcapp.pageobjects.FileOperations.AvailableFormDetails;
import com.project.safetychain.pcapp.pageobjects.FileOperations.AvailableOtherDetails;
import com.project.safetychain.pcapp.pageobjects.FileOperations.AvailableUserDetails;
import com.project.safetychain.pcapp.pageobjects.FormViewScreen.FormDetailsPC;

import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.utilities.ControlActions_PCApp;

public class TCG_SMK_App_Flows extends TestBase{
	ControlActions_PCApp controlActionsPC;
	LoginScreen loginScreen;
	CommonScreen commonScreen;
	FormsScreen forms;
	FormViewScreen formview; 
	FormDetailsPC formDetailsPC;
	LINKScreen linkScreen;
	DeviceManagementScreen devices;
	InboxScreen inbox;
	ChartScreen chartScreen;

	public static String locationInstanceValue1;
	public static String resourceInstanceValue1,resourceInstanceValue2;

	String TextFieldName = "Text 1",
			ParagraphFieldName = "Para 1",
			SelectOneFieldName = "SO 1",
			selectMultipleFieldName = "SM1",
			NumericFieldName = "Num 1",
			DateFieldName = "DT 1",
			TimeFieldName = "TM 1",
			DateTimeFieldName = "DTM 1";

	List<String> selectOneMultipleFieldOptions = Arrays.asList("1","2","3","4");

	String AggSrcNumericFieldName = "Num 1",
			NumericFieldName2 = "Num 2",
			AggregateFieldName = "Agg 1",
			IdentifierldName = "IDN 1";

	public static String checkFormName;
	public static String questionnaireFormName;
	public static String auditFormName1, auditFormName2;

	public static String deviceName;

	FileOperations storeData, fetchData;
	AvailableFormDetails availableData;
	AvailableOtherDetails otherData;
	AvailableUserDetails userData;

	@BeforeClass(alwaysRun = true)
	public void groupInit() {
		try {
			fetchData = new FileOperations();
			availableData = new AvailableFormDetails();
			availableData = fetchData.getCreatedData();
			otherData = fetchData.getOtherData();
			userData = fetchData.getCreatedUserData();

			locationInstanceValue1 = availableData.locationInstanceValue1;
			resourceInstanceValue1 = availableData.resourceInstanceValue1;
			resourceInstanceValue2 = availableData.resourceInstanceValue2;

			checkFormName = availableData.CheckForm;
			questionnaireFormName = availableData.QuestionnaireForm;
			auditFormName1 = availableData.Audit_Form1;
			auditFormName2 = availableData.Audit_Form2;

			deviceName = otherData.deviceName;

			PCAppDriver = launchPCApp();
			controlActionsPC = new ControlActions_PCApp(PCAppDriver);
			forms = new FormsScreen(PCAppDriver);
			formview = new FormViewScreen(PCAppDriver);
			linkScreen = new LINKScreen(PCAppDriver);
			devices = new DeviceManagementScreen(PCAppDriver);
			inbox = new InboxScreen(PCAppDriver);
			chartScreen = new ChartScreen(PCAppDriver);

			loginScreen = new LoginScreen(PCAppDriver);
			commonScreen = loginScreen.Login(pcAppTenantname, userData.username, userData.password);
			if(commonScreen.error) {
				TCGFailureMsg = "COULD not login to the application";
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}

		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Test(description="Link || Link should be accessible on PC App")
	public void TestCase_34833() {
		LINKScreen selectLINKTab  = commonScreen.selectLink();
		TestValidation.IsFalse(selectLINKTab.error,
				"OPENED LINKS Tab", 
				"COULD not open LINKS Tab");

		boolean verifyLINKPageElements = selectLINKTab.verifyLINKElements();
		TestValidation.IsTrue(verifyLINKPageElements, 
				"VERIFIED LINK elements", 
				"FAILED to verify LINK elements");
	}

	@Test(description="Device Management || User is able to add device")
	public void TestCase_34832() {
		DeviceManagementScreen selectDeviceManagementTab  = commonScreen.selectDeviceManagement();
		TestValidation.IsFalse(selectDeviceManagementTab.error,
				"OPENED 'Device Management' Tab", 
				"COULD not open 'Device Management' Tab");

		boolean downloadDevice = selectDeviceManagementTab.downloadDevice(deviceName);
		TestValidation.IsTrue(downloadDevice, 
				"DOWNLOADED device", 
				"FAILED to download device");

		boolean verifyDownloadedDevice = selectDeviceManagementTab.verifyDownloadedDeviceAvailability(deviceName);
		TestValidation.IsTrue(verifyDownloadedDevice, 
				"VERIFIED downloaded device", 
				"FAILED to verify downloaded device");
	}

	@Test(description="Settings || Accessibility || Verify 'Show Numeric Keypad' functionality")
	public void TestCase_34834() {
		SettingsScreen selectSettingsTab  = commonScreen.selectSettings();
		TestValidation.IsFalse(selectSettingsTab.error,
				"OPENED 'Settings' Tab", 
				"COULD not open 'Settings' Tab");

		boolean changeNumericKeyPadToggle = selectSettingsTab.changeShowNumericKeyadToggle();
		TestValidation.IsTrue(changeNumericKeyPadToggle, 
				"CHANGED 'Show Numeric Keypad' toggle - ON", 
				"FAILED to change'Show Numeric Keypad' toggle - ON");

		TestValidation.IsFalse(commonScreen.selectForms().error, 
				"OPENED 'Forms' Tab", 
				"COULD not open 'Forms' Tab");

		TestValidation.IsTrue(forms.selectOpenForm(auditFormName2),
				"OPENED form - "+auditFormName2, 
				"COULD not open form - "+auditFormName2);

		TestValidation.IsTrue(formview.selectLocationResource(locationInstanceValue1, resourceInstanceValue1),
				"SELECTED 'Location & Resource' Tab", 
				"COULD not select 'Location & Resource' Tab");

		TestValidation.IsTrue(formview.verifyNumericKeyPad(),
				"VERFIED 'Numeric keypad' Tab", 
				"COULD not verify 'Numeric Keypad'");

		TestValidation.IsTrue(formview.saveForm(),
				"SAVED form", 
				"COULD not save form");

		selectSettingsTab  = commonScreen.selectSettings();
		TestValidation.IsFalse(selectSettingsTab.error,
				"OPENED 'Settings' Tab", 
				"COULD not open 'Settings' Tab");

		changeNumericKeyPadToggle = selectSettingsTab.changeShowNumericKeyadToggle();
		TestValidation.IsTrue(changeNumericKeyPadToggle, 
				"CHANGED 'Show Numeric Keypad' toggle - OFF", 
				"FAILED to change'Show Numeric Keypad' toggle - OFF");
	}

	@Test(description="Settings || Connectivity || Verify 'Offline Mode' functionality")
	public void TestCase_34835() {
		SettingsScreen selectSettingsTab  = commonScreen.selectSettings();
		TestValidation.IsFalse(selectSettingsTab.error,
				"OPENED 'Settings' Tab", 
				"COULD not open 'Settings' Tab");

		boolean changeOfflineModeToggle = selectSettingsTab.changeOfflineModeToggle(true);
		TestValidation.IsTrue(changeOfflineModeToggle, 
				"CHANGED 'Offline Mode' toggle - ON", 
				"FAILED to change''Offline Mode' toggle - ON");

		HashMap<String, List<String>> allFields = new LinkedHashMap<String, List<String>>();
		allFields.put(NumericFieldName,Arrays.asList("2"));
		formDetailsPC = new FormDetailsPC();
		formDetailsPC.locationName = locationInstanceValue1;
		formDetailsPC.resourceName = resourceInstanceValue1;
		formDetailsPC.fieldDetails = allFields;

		FormsScreen selectFormsTab  = commonScreen.selectForms();
		TestValidation.IsFalse(selectFormsTab.error,
				"OPENED Forms Tab", 
				"COULD not open Forms Tab");

		boolean openForm = selectFormsTab.selectOpenForm(auditFormName2);
		TestValidation.IsTrue(openForm, 
				"SELECTED & opened form - "+auditFormName2, 
				"FAILED to open form - "+auditFormName2);

		boolean submitData = formview.fillDataInForm1(formDetailsPC);
		TestValidation.IsTrue(submitData, 
				"SUBMITTED data for form - "+auditFormName2, 
				"FAILED submit data fo form - "+auditFormName2);

		SubmissionsScreen selectSubmissionsTab  = commonScreen.selectSubmissions();
		TestValidation.IsFalse(selectSubmissionsTab.error,
				"OPENED Submissions Tab", 
				"COULD not open Submissions Tab");

		boolean verifyPendingSubmissionCount  = selectSubmissionsTab.verifySubmissionCount(1);
		TestValidation.IsTrue(verifyPendingSubmissionCount, 
				"VERIFIED task count - 1",
				"FAILED to verify task count 1");

		selectSettingsTab  = commonScreen.selectSettings();
		TestValidation.IsFalse(selectSettingsTab.error,
				"OPENED 'Settings' Tab", 
				"COULD not open 'Settings' Tab");

		changeOfflineModeToggle = selectSettingsTab.changeOfflineModeToggle(false);
		TestValidation.IsTrue(changeOfflineModeToggle, 
				"CHANGED 'Offline Mode' toggle - OFF", 
				"FAILED to change''Offline Mode' toggle - OFF");
	}

	@Test(description="Settings || Connectivity || Verify 'Limit Data' functionality")
	public void TestCase_34836() {
		int oldTaskCount = 0, newTaskCount = 0;
		oldTaskCount = inbox.getTaskCount();
		SettingsScreen selectSettingsTab  = commonScreen.selectSettings();
		TestValidation.IsFalse(selectSettingsTab.error,
				"OPENED 'Settings' Tab", 
				"COULD not open 'Settings' Tab");

		boolean changeLimitDataToggle = selectSettingsTab.changeLimitDataToggle();
		TestValidation.IsTrue(changeLimitDataToggle, 
				"CHANGED 'Limit Data' toggle - ON", 
				"FAILED to change'Limit Data' toggle - ON");

		TestValidation.IsFalse(commonScreen.selectInbox().error,
				"OPENED 'Inbox' Tab", 
				"COULD not open 'Inbox' Tab");

		TestValidation.IsTrue(inbox.refreshInbox(), 
				"REFRESHED inbox", 
				"FAILED to refresh Inbox");

		newTaskCount = inbox.getTaskCount();

		boolean verifyTaskCount = oldTaskCount>newTaskCount;
		TestValidation.IsTrue(verifyTaskCount, 
				"VERFIED task count after data limit", 
				"FAILED to verify task count after data limit");

		selectSettingsTab  = commonScreen.selectSettings();
		TestValidation.IsFalse(selectSettingsTab.error,
				"OPENED 'Settings' Tab", 
				"COULD not open 'Settings' Tab");

		changeLimitDataToggle = selectSettingsTab.changeLimitDataToggle();
		TestValidation.IsTrue(changeLimitDataToggle, 
				"CHANGED 'Limit Data' toggle - OFF", 
				"FAILED to change'Limit Data' toggle - OFF");
	}

	@Test(description="Settings || Clear Submissions || Verify 'Clear Submissions for All Users' functionality")
	public void TestCase_34837() {
		SettingsScreen selectSettingsTab  = commonScreen.selectSettings();
		TestValidation.IsFalse(selectSettingsTab.error,
				"OPENED 'Settings' Tab", 
				"COULD not open 'Settings' Tab");

		boolean clearSubmission = selectSettingsTab.performClearSubmission();
		TestValidation.IsTrue(clearSubmission, 
				"CLEARED submissions", 
				"FAILED to clear submissions");

		SubmissionsScreen selectSubmissionsTab  = commonScreen.selectSubmissions();
		TestValidation.IsFalse(selectSubmissionsTab.error,
				"OPENED 'Submissions' Tab", 
				"COULD not open 'Submissions' Tab");

		boolean verifyNoSubmission = selectSubmissionsTab.verifyNOSubmissions();
		TestValidation.IsTrue(verifyNoSubmission, 
				"VERIFIED - No submissions found", 
				"FAILED to clear submission. Found submissions");

		boolean loginWithSuperadminTypeUser = loginScreen.reLogin(pcAppUsername, pcAppPassword);
		TestValidation.IsTrue(loginWithSuperadminTypeUser, 
				"LOGGED in with user - "+pcAppUsername , 
				"FAILED to login with user - "+pcAppUsername);

		selectSubmissionsTab  = commonScreen.selectSubmissions();
		TestValidation.IsFalse(selectSubmissionsTab.error,
				"OPENED 'Submissions' Tab", 
				"COULD not open 'Submissions' Tab");

		verifyNoSubmission = selectSubmissionsTab.verifyNOSubmissions();
		TestValidation.IsTrue(verifyNoSubmission, 
				"VERIFIED - No submissions found", 
				"FAILED to clear submission. Found submissions");

		boolean loginWithTestingUser = loginScreen.reLogin(userData.username, userData.password);
		TestValidation.IsTrue(loginWithTestingUser, 
				"LOGGED in with user - "+userData.username , 
				"FAILED to login with user - "+userData.username);

	}

	@Test(description="Forms || Verify chart generation for 'XBARR' & 'SumChart' charts")
	public void TestCase_38527() {
		HashMap<String, List<String>> allFields = new LinkedHashMap<String, List<String>>();
		allFields.put(NumericFieldName,Arrays.asList("2"));
		allFields.put(SelectOneFieldName,Arrays.asList("2"));
		allFields.put(selectMultipleFieldName,Arrays.asList("1,2"));
		allFields.put(NumericFieldName2,Arrays.asList("4"));

		formDetailsPC = new FormDetailsPC();
		formDetailsPC.locationName = locationInstanceValue1;
		formDetailsPC.resourceName = resourceInstanceValue1;
		formDetailsPC.fieldDetails = allFields;
		formDetailsPC.isSubmit = false;
		formDetailsPC.chartClose = false;

		FormsScreen selectFormsTab  = commonScreen.selectForms();
		TestValidation.IsFalse(selectFormsTab.error,
				"OPENED Forms Tab", 
				"COULD not open Forms Tab");

		boolean openForm = selectFormsTab.selectOpenForm(auditFormName1);
		TestValidation.IsTrue(openForm, 
				"SELECTED & opened form - "+auditFormName1, 
				"FAILED to open form - "+auditFormName1);

		boolean submitData = formview.fillDataInForm1(formDetailsPC);
		TestValidation.IsTrue(submitData, 
				"FILLED data for form - "+auditFormName1, 
				"FAILED fill data fo form - "+auditFormName1);

		boolean submitForm = formview.submitForm();
		TestValidation.IsTrue(submitForm, 
				"SUBMITTED form", 
				"FAILED to submit form"); 

		boolean verifyFormElements = chartScreen.verifyChartElements();
		TestValidation.IsTrue(verifyFormElements, 
				"VERIFIED chart elements - Chart is visible", 
				"FAILED to verify chart elements");

		TestValidation.IsTrue(commonScreen.moveToNextChart(), 
				"MOVED to next chart", 
				"FAILED to move to next chart");

		verifyFormElements = chartScreen.verifyChartElements();
		TestValidation.IsTrue(verifyFormElements, 
				"VERIFIED chart elements - Chart is visible", 
				"FAILED to verify chart elements");

		TestValidation.IsTrue(commonScreen.closeChartScreen(), 
				"CLOSED chart screen", 
				"FAILED to close chart screen");
	}

	@AfterClass(alwaysRun = true)
	public void closeBrowser() throws InterruptedException {
		PCAppDriver.close();
	} 

}
