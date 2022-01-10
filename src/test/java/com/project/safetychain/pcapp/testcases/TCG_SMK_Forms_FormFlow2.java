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
import com.project.safetychain.pcapp.pageobjects.FavoritesScreen;
import com.project.safetychain.pcapp.pageobjects.FileOperations;
import com.project.safetychain.pcapp.pageobjects.FormViewScreen;
import com.project.safetychain.pcapp.pageobjects.FormsScreen;
import com.project.safetychain.pcapp.pageobjects.InboxScreen;
import com.project.safetychain.pcapp.pageobjects.LoginScreen;
import com.project.safetychain.pcapp.pageobjects.SavedScreen;
import com.project.safetychain.pcapp.pageobjects.SubmissionsScreen;
import com.project.safetychain.pcapp.pageobjects.FileOperations.AvailableFormDetails;
import com.project.safetychain.pcapp.pageobjects.FileOperations.AvailableUserDetails;
import com.project.safetychain.pcapp.pageobjects.FormViewScreen.FormDetailsPC;
import com.project.safetychain.pcapp.pageobjects.FormViewScreen.OEEeventType;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage;
import com.project.safetychain.webapp.pageobjects.HomePage;
import com.project.safetychain.webapp.pageobjects.LoginPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.COLUMNHEADER;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.COLUMNSETTING;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.FSQARESOURCE;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.FSQATAB;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.utilities.CommonMethods;
import com.project.utilities.ControlActions;
import com.project.utilities.ControlActions_PCApp;


public class TCG_SMK_Forms_FormFlow2 extends TestBase {
	ControlActions_PCApp controlActionsPC;
	CommonScreen commonScreen;
	LoginScreen loginScreen;
	FormsScreen forms;
	FormViewScreen formview; 
	SavedScreen savedScreen;

	FormDetailsPC formDetailsPC;
	InboxScreen inboxScreen;

	LoginPage webLoginPage;
	HomePage webHomePage;
	FSQABrowserPage webfsqaFormsPage;
	ControlActions controlActionsWeb;


	public static String batchID;

	public static String locationInstanceValue1;
	public static String resourceInstanceValue1,resourceInstanceValue2;

	FormDesignerPage formDesignerPage;

	String 
	SelectOneFieldName = "SO 1",
	selectMultipleFieldName = "SM 1",
	NumericFieldName1 = "Num 1",
	NumericFieldName2 = "Num 2";

	//List<String> selectOneMultipleFieldOptions = Arrays.asList("1","2");

	String AggSrcNumericFieldName = "Num 1",
			AggregateFieldName = "Agg 1",
			IdentifierldName = "IDN 1";

	public static String checkFormName;
	public static String questionnaireFormName;
	public static String auditFormName1, auditFormName2;

	public static String dynamicFlowOEEFormName;

	FileOperations storeData, fetchData;
	AvailableFormDetails availableData;
	AvailableUserDetails userData;

	@BeforeClass(alwaysRun = true)
	public void groupInit() {
		try {
			fetchData = new FileOperations();
			availableData = fetchData.getCreatedData();
			userData = fetchData.getCreatedUserData();

			locationInstanceValue1 = availableData.locationInstanceValue1;
			resourceInstanceValue1 = availableData.resourceInstanceValue1;
			resourceInstanceValue2 = availableData.resourceInstanceValue2;

			checkFormName = availableData.CheckForm;
			questionnaireFormName = availableData.QuestionnaireForm;
			auditFormName1 = availableData.Audit_Form1;
			auditFormName2 = availableData.Audit_Form2;


			dynamicFlowOEEFormName = "OEE Events";

			PCAppDriver = launchPCApp();
			controlActionsPC = new ControlActions_PCApp(PCAppDriver);
			forms = new FormsScreen(PCAppDriver);
			formview = new FormViewScreen(PCAppDriver);
			inboxScreen = new InboxScreen(PCAppDriver);
			savedScreen = new SavedScreen(PCAppDriver);

			loginScreen = new LoginScreen(PCAppDriver);
			commonScreen = loginScreen.Login(pcAppTenantname, userData.username,userData.password);
			if(commonScreen.error) {
				TCGFailureMsg = "COULD not login to the application";
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Test(description="Forms || Refresh & Verify the form count", priority=2)
	public void TestCase_37261() {
		FormsScreen selectFormsTab  = commonScreen.selectForms();
		TestValidation.IsFalse(selectFormsTab.error,
				"OPENED Forms Tab", 
				"COULD not open Forms Tab");

		boolean openForm = selectFormsTab.verifyFormCountOnRefresh();
		TestValidation.IsTrue(openForm, 
				"VERIFIED form count", 
				"FAILED to verify form count");
	}

	@Test(description="Forms || Verify user should be able to submit the form having the field elements")
	public void TestCase_34841() {
		HashMap<String, List<String>> allFields = new LinkedHashMap<String, List<String>>();
		allFields.put(NumericFieldName1,Arrays.asList("2"));
		allFields.put(SelectOneFieldName,Arrays.asList("2"));
		allFields.put(selectMultipleFieldName,Arrays.asList("1,2"));
		allFields.put(NumericFieldName2,Arrays.asList("4"));

		formDetailsPC = new FormDetailsPC();
		formDetailsPC.locationName = locationInstanceValue1;
		formDetailsPC.resourceName = resourceInstanceValue1;
		formDetailsPC.fieldDetails = allFields;
		formDetailsPC.isSubmit = false;
		formDetailsPC.chartClose = true;

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
				"SUBMITTED data for form - "+auditFormName1, 
				"FAILED submit data fo form - "+auditFormName1);

		boolean verifyFormElements = formview.verifyFormElements("Test PC Form","upload.png");
		TestValidation.IsTrue(verifyFormElements, 
				"VERIFIED form elements", 
				"FAILED to verify form element");

		boolean submitForm = formview.submitForm(formDetailsPC.chartClose);
		TestValidation.IsTrue(submitForm, 
				"SUBMISSION is successfull", 
				"FAILED to submit form");
	}

	@Test(description="Forms || Verify 'Direct Observation' functionality")
	public void TestCase_38953() {
		HashMap<String, List<String>> allFields = new LinkedHashMap<String, List<String>>();
		allFields.put(NumericFieldName1,Arrays.asList("2"));
		//		allFields.put(SelectOneFieldName,Arrays.asList("1"));
		//		allFields.put(selectMultipleFieldName,Arrays.asList("2"));
		//		allFields.put(NumericFieldName2,Arrays.asList("4"));

		formDetailsPC = new FormDetailsPC();
		formDetailsPC.locationName = locationInstanceValue1;
		formDetailsPC.resourceName = resourceInstanceValue1;
		formDetailsPC.isSubmit = false;
		//formDetailsPC.chartClose = true;

		FormsScreen selectFormsTab  = commonScreen.selectForms();
		TestValidation.IsFalse(selectFormsTab.error,
				"OPENED Forms Tab", 
				"COULD not open Forms Tab");

		boolean openForm = selectFormsTab.selectOpenForm(auditFormName2);
		TestValidation.IsTrue(openForm, 
				"SELECTED & opened form - "+auditFormName2, 
				"FAILED to open form - "+auditFormName2);

		formDetailsPC.fieldDetails = allFields;
		boolean submitData = formview.fillDataInForm1(formDetailsPC);
		TestValidation.IsTrue(submitData, 
				"SUBMITTED data for form - "+auditFormName2, 
				"FAILED submit data fo form - "+auditFormName2);

		boolean performVerification = formview.performDirectObservation(userData.username, "1234");
		TestValidation.IsTrue(performVerification, 
				"PERFORMED Verification(Direct Verification)", 
				"FAILED to perform Verification(Direct Observation)");

		boolean submitForm = formview.submitForm(formDetailsPC.chartClose);
		TestValidation.IsTrue(submitForm, 
				"SUBMISSION is successfull", 
				"FAILED to submit form");

	}

	@Test(description="Dynamic Flow || Verify new task should get generate after the form submission")
	public void TestCase_37256() {
		String dynamicFlowTaskName;
		boolean loginWithSuperadminTypeUser = loginScreen.reLogin(pcAppTenantname, pcAppUsername, pcAppPassword);
		TestValidation.IsTrue(loginWithSuperadminTypeUser, 
				"LOGGED in with user - "+pcAppUsername , 
				"FAILED to login with user - "+pcAppUsername);

		formDetailsPC = new FormDetailsPC();
		formDetailsPC.locationName = "OEE";
		formDetailsPC.resourceName = "OEE Equipment Migration resource";
		formDetailsPC.isSubmit = true;

		try {
			//Form Submission
			FormsScreen selectFormsTab  = commonScreen.selectForms();
			TestValidation.IsFalse(selectFormsTab.error,
					"OPENED Forms Tab", 
					"COULD not open Forms Tab");

			boolean openForm = forms.selectOpenForm(dynamicFlowOEEFormName);
			TestValidation.IsTrue(openForm, 
					"SELECTED & opened form - "+dynamicFlowOEEFormName, 
					"FAILED open form - "+dynamicFlowOEEFormName);

			batchID = CommonMethods.dynamicStrictText("ID1");
			boolean submitData = formview.submitOEEEventsForm(OEEeventType.ACTUAL_PRODUCTION_START,batchID,formDetailsPC);
			TestValidation.IsTrue(submitData, 
					"SUBMITTED dynamic flow configured form - "+dynamicFlowOEEFormName, 
					"FAILED to submit dynamic flow configured form - "+dynamicFlowOEEFormName);

			//Task Submission - 1
			formDetailsPC.selectLocationResource = false;

			InboxScreen selectInboxTab  = commonScreen.selectInbox();
			TestValidation.IsFalse(selectInboxTab.error,
					"OPENED Inbox Tab", 
					"COULD not open Inbox Tab");

			dynamicFlowTaskName = "OEEEvent_"+batchID;
			boolean openTask = selectInboxTab.selectOpenTask(dynamicFlowTaskName, true);
			TestValidation.IsTrue(openTask, 
					"SELECTED & opened task 1 - "+dynamicFlowTaskName, 
					"FAILED open task 1 - "+dynamicFlowTaskName);

			batchID = CommonMethods.dynamicStrictText("ID2");
			submitData = formview.submitOEEEventsForm(OEEeventType.THROUGHPUT_QUANTITY,batchID,formDetailsPC);
			TestValidation.IsTrue(submitData, 
					"SUBMITTED dynamic flow configured task 1 - "+dynamicFlowTaskName, 
					"FAILED to submit dynamic flow configured task 1 - "+dynamicFlowTaskName);

			//Task Submission - 2
			dynamicFlowTaskName = "OEEEvent_"+batchID;
			openTask = selectInboxTab.selectOpenTask(dynamicFlowTaskName, true);
			TestValidation.IsTrue(openTask, 
					"SELECTED & opened task 2 - "+dynamicFlowTaskName, 
					"FAILED open task 2 - "+dynamicFlowTaskName);

			batchID = CommonMethods.dynamicStrictText("ID3");
			submitData = formview.submitOEEEventsForm(OEEeventType.UNPLANNED_DOWNTIME,batchID,formDetailsPC);
			TestValidation.IsTrue(submitData, 
					"SUBMITTED dynamic flow configured task 2 - "+dynamicFlowTaskName, 
					"FAILED to submit dynamic flow configured task 2 - "+dynamicFlowTaskName);

			//Task Submission - 3
			dynamicFlowTaskName = "OEEEvent_"+batchID;
			openTask = selectInboxTab.selectOpenTask(dynamicFlowTaskName, true);
			TestValidation.IsTrue(openTask, 
					"SELECTED & opened task 3 - "+dynamicFlowTaskName, 
					"FAILED open task 3 - "+dynamicFlowTaskName);

			batchID = CommonMethods.dynamicStrictText("ID4");
			submitData = formview.submitOEEEventsForm(OEEeventType.QUALITY,batchID,formDetailsPC);
			TestValidation.IsTrue(submitData, 
					"SUBMITTED dynamic flow configured task 3 - "+dynamicFlowTaskName, 
					"FAILED to submit dynamic flow configured task 3 - "+dynamicFlowTaskName);

			//Task Submission - 4
			dynamicFlowTaskName = "OEEEvent_"+batchID;
			openTask = selectInboxTab.selectOpenTask(dynamicFlowTaskName, true);
			TestValidation.IsTrue(openTask, 
					"SELECTED & opened task 4 - "+dynamicFlowTaskName, 
					"FAILED open task 4 - "+dynamicFlowTaskName);

			batchID = CommonMethods.dynamicStrictText("ID5");
			submitData = formview.submitOEEEventsForm(OEEeventType.ACTUAL_PRODUCTION_END,batchID,formDetailsPC);
			TestValidation.IsTrue(submitData, 
					"SUBMITTED dynamic flow configured task 4 - "+dynamicFlowTaskName, 
					"FAILED to submit dynamic flow configured task 4 - "+dynamicFlowTaskName);
		}finally {
			boolean loginWithUWPUser = loginScreen.reLogin(pcAppTenantname, userData.username, userData.password);
			TestValidation.IsTrue(loginWithUWPUser, 
					"LOGGED in with user - "+pcAppUsername , 
					"FAILED to login with user - "+pcAppUsername);
		}

	}

	@Test(description="Saved || Verify user is able to submit the saved form")
	public void TestCase_34829() {

		HashMap<String, List<String>> allFields = new LinkedHashMap<String, List<String>>();
		allFields.put(AggSrcNumericFieldName,Arrays.asList("2","4"));
		allFields.put(NumericFieldName2,Arrays.asList("2"));
		allFields.put(IdentifierldName,Arrays.asList("1234"));

		formDetailsPC = new FormDetailsPC();
		formDetailsPC.locationName = locationInstanceValue1;
		formDetailsPC.resourceName = resourceInstanceValue1;

		FormsScreen selectFormsTab  = commonScreen.selectForms();
		TestValidation.IsFalse(selectFormsTab.error,
				"OPENED Forms Tab", 
				"COULD not open Forms Tab");

		boolean openForm = selectFormsTab.selectOpenForm(questionnaireFormName);
		TestValidation.IsTrue(openForm, 
				"SELECTED & opened form - "+questionnaireFormName, 
				"FAILED open form - "+questionnaireFormName);

		boolean selectLocationResource = formview.selectLocationResource(locationInstanceValue1, resourceInstanceValue1);
		TestValidation.IsTrue(selectLocationResource, 
				"SELECTED Location & Resource", 
				"FAILED to select Location & Resource");

		boolean saveForm = formview.saveForm();
		TestValidation.IsTrue(saveForm, 
				"SAVED form - "+questionnaireFormName, 
				"FAILED to save the form - "+questionnaireFormName);

		savedScreen = commonScreen.selectSaved();
		TestValidation.IsFalse(savedScreen.error,
				"OPENED Saved Tab", 
				"COULD not open Saved Tab");

		formDetailsPC.selectLocationResource = false;

		boolean openSavedForm = savedScreen.selectOpenForm(questionnaireFormName);
		TestValidation.IsTrue(openSavedForm, 
				"SELECTED & opened saved form - "+questionnaireFormName, 
				"FAILED to open saved form - "+questionnaireFormName);

		formDetailsPC.fieldDetails = allFields;
		boolean submitData = formview.fillDataInForm1(formDetailsPC);
		TestValidation.IsTrue(submitData, 
				"SUBMITTED data for saved form - "+questionnaireFormName, 
				"FAILED to submit data for saved form - "+questionnaireFormName);

	}


	@Test(description="Saved || Verify user is able to delete the saved form")
	public void TestCase_34830() {
		FormsScreen selectFormsTab  = commonScreen.selectForms();
		TestValidation.IsFalse(selectFormsTab.error,
				"OPENED Forms Tab", 
				"COULD not open Forms Tab");

		boolean openForm = selectFormsTab.selectOpenForm(questionnaireFormName);
		TestValidation.IsTrue(openForm, 
				"SELECTED & opened form - "+questionnaireFormName, 
				"FAILED to open form - "+questionnaireFormName);

		boolean selectLocationResource = formview.selectLocationResource(locationInstanceValue1, resourceInstanceValue1);
		TestValidation.IsTrue(selectLocationResource, 
				"SELECTED Location & Resource", 
				"FAILED to select Location & Resource");

		boolean saveForm = formview.saveForm();
		TestValidation.IsTrue(saveForm, 
				"SAVED form - "+questionnaireFormName, 
				"FAILED to save the form - "+questionnaireFormName);

		savedScreen = commonScreen.selectSaved();
		TestValidation.IsFalse(savedScreen.error,
				"OPENED Saved Tab", 
				"COULD not open Saved Tab");

		boolean openSavedForm = savedScreen.selectOpenForm(questionnaireFormName);
		TestValidation.IsTrue(openSavedForm, 
				"SELECTED & opened saved form - "+questionnaireFormName, 
				"FAILED to open saved form - "+questionnaireFormName);

		boolean deleteForm = savedScreen.deleteForm(questionnaireFormName);
		TestValidation.IsTrue(deleteForm, 
				"DELETED saved form - "+questionnaireFormName, 
				"FAILED to delete saved form - "+questionnaireFormName);
	}

	@Test(description="Submission || Verify the saved form submission should be shown")
	public void TestCase_34827() {
		HashMap<String, List<String>> allFields = new LinkedHashMap<String, List<String>>();
		allFields.put(AggSrcNumericFieldName,Arrays.asList("2","4"));
		allFields.put(NumericFieldName2,Arrays.asList("2"));
		allFields.put(IdentifierldName,Arrays.asList("1234"));

		formDetailsPC = new FormDetailsPC();
		formDetailsPC.locationName = locationInstanceValue1;
		formDetailsPC.resourceName = resourceInstanceValue1;
		formDetailsPC.fieldDetails = allFields;

		FormsScreen selectFormsTab  = commonScreen.selectForms();
		TestValidation.IsFalse(selectFormsTab.error,
				"OPENED Forms Tab", 
				"COULD not open Forms Tab");

		boolean openForm = selectFormsTab.selectOpenForm(questionnaireFormName);
		TestValidation.IsTrue(openForm, 
				"SELECTED & opened form - "+questionnaireFormName, 
				"FAILED open form - "+questionnaireFormName);

		boolean selectLocationResource = formview.selectLocationResource(locationInstanceValue1, resourceInstanceValue1);
		TestValidation.IsTrue(selectLocationResource, 
				"SELECTED Location & Resource", 
				"FAILED to select Location & Resource");

		boolean saveForm = formview.saveForm();
		TestValidation.IsTrue(saveForm, 
				"SAVED form - "+questionnaireFormName, 
				"FAILED to save the form - "+questionnaireFormName);

		savedScreen = commonScreen.selectSaved();
		TestValidation.IsFalse(savedScreen.error,
				"OPENED Saved Tab", 
				"COULD not open Saved Tab");

		formDetailsPC.selectLocationResource = false;

		boolean openSavedForm = savedScreen.selectOpenForm(questionnaireFormName);
		TestValidation.IsTrue(openSavedForm, 
				"SELECTED & opened saved form - "+questionnaireFormName, 
				"FAILED to open saved form - "+questionnaireFormName);

		boolean submitData = formview.fillDataInForm1(formDetailsPC);
		TestValidation.IsTrue(submitData, 
				"SUBMITTED data for form - "+questionnaireFormName, 
				"FAILED to submit data for form - "+questionnaireFormName);

		SubmissionsScreen selectSubmissionTab  = commonScreen.selectSubmissions();
		TestValidation.IsFalse(selectSubmissionTab.error,
				"OPENED Submissions Tab", 
				"COULD not open Submissions Tab");

		boolean verifySubmission = selectSubmissionTab.verifySubmissionAvailability(questionnaireFormName);
		TestValidation.IsTrue(verifySubmission, 
				"Saved form submission is shown for form - "+questionnaireFormName, 
				"FAILED verify saved form submission for form - "+questionnaireFormName);

		try {

			driver = launchbrowser();
			webLoginPage = new LoginPage(driver);
			controlActionsWeb = new ControlActions(driver);

			controlActionsWeb.getUrl(applicationUrl);
			webHomePage = webLoginPage.performLogin(adminUsername, adminPassword);
			webfsqaFormsPage = new FSQABrowserPage(driver);
			webfsqaFormsPage.selectResourceDropDownandNavigate(FSQARESOURCE.ITEMS,FSQATAB.DOCUMENTS);
			webfsqaFormsPage.openAndApplySettingsForColumn(COLUMNHEADER.DOCUMENTNAME, COLUMNSETTING.FILTER,questionnaireFormName);
			webfsqaFormsPage.openAndApplySettingsForColumn(COLUMNHEADER.MODIFIEDBY, COLUMNSETTING.FILTER,userData.username);

			boolean verifySubmissionOnWeb = webfsqaFormsPage.openForDetails(1);
			TestValidation.IsTrue(verifySubmissionOnWeb, 
					"Form submission is shown in web app documents - "+questionnaireFormName, 
					"FAILED to verify form submission at web app documents -"+questionnaireFormName);

		} catch (Exception e) {
			logError("Failed to verify document(record) on web app - "+e.getMessage());	
		}finally {
			driver.close();
		}

	}

	@Test(description="Submission || Verify user should be able to open & view the submission")
	public void TestCase_34828() {
		HashMap<String, List<String>> allFields = new LinkedHashMap<String, List<String>>();
		allFields.put(NumericFieldName1,Arrays.asList("2"));
		//		allFields.put(SelectOneFieldName,Arrays.asList("2"));
		//		allFields.put(selectMultipleFieldName,Arrays.asList("1,2"));
		//		allFields.put(NumericFieldName2,Arrays.asList("4"));

		formDetailsPC = new FormDetailsPC();
		formDetailsPC.locationName = locationInstanceValue1;
		formDetailsPC.resourceName = resourceInstanceValue1;
		formDetailsPC.fieldDetails = allFields;
		//		formDetailsPC.chartClose = true;

		FormsScreen selectFormsTab  = commonScreen.selectForms();
		TestValidation.IsFalse(selectFormsTab.error,
				"OPENED Forms Tab", 
				"COULD not open Forms Tab");

		boolean openForm = selectFormsTab.selectOpenForm(auditFormName2);
		TestValidation.IsTrue(openForm, 
				"SELECTED & opened form - "+auditFormName2, 
				"FAILED open form "+auditFormName2);

		boolean submitData = formview.fillDataInForm1(formDetailsPC);
		TestValidation.IsTrue(submitData, 
				"SUBMITTED data for form - "+auditFormName2, 
				"FAILED to submit data for form - "+auditFormName2);

		SubmissionsScreen selectSubmissionTab  = commonScreen.selectSubmissions();
		TestValidation.IsFalse(selectSubmissionTab.error,
				"OPENED Submissions Tab", 
				"COULD not open Submissions Tab");

		boolean verifySubmission = selectSubmissionTab.selectOpenSubmission(auditFormName2);
		TestValidation.IsTrue(verifySubmission, 
				"FORM submission details are shown for form - "+auditFormName2, 
				"FAILED to verify form submission details for form - "+auditFormName2);
	}

	@Test(description="Favorites || User should be able to see the favorite form")
	public void TestCase_34831() {
		FormsScreen selectFormsTab  = commonScreen.selectForms();
		TestValidation.IsFalse(selectFormsTab.error,
				"OPENED Forms Tab", 
				"COULD not open Forms Tab");

		boolean navigateToMainScren = selectFormsTab.selectFormFavorite(checkFormName);
		TestValidation.IsTrue(navigateToMainScren, 
				"FAVORITE the form'", 
				"FAILED to select form for Favorites");

		FavoritesScreen selectFavoritesTab = commonScreen.selectFavorites();
		TestValidation.IsFalse(selectFavoritesTab.error,
				"OPENED Favorites Tab", 
				"COULD not open Favorites Tab");

		boolean verifyFavoriteForm = selectFavoritesTab.verifyFavoriteFormAvailability(checkFormName);
		TestValidation.IsTrue(verifyFavoriteForm, 
				"FORM is avaiable in Favorite screen - "+checkFormName, 
				"FAILED to get form in Favorites screen - "+checkFormName);
	}

	@AfterClass(alwaysRun = true)
	public void closeBrowser() throws InterruptedException {
		PCAppDriver.close();
	}

}
