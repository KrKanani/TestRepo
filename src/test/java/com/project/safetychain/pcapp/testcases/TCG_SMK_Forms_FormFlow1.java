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
import com.project.safetychain.pcapp.pageobjects.FormsScreen;
import com.project.safetychain.pcapp.pageobjects.LoginScreen;
import com.project.safetychain.pcapp.pageobjects.SubmissionsScreen;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage;
import com.project.safetychain.webapp.pageobjects.HomePage;
import com.project.safetychain.webapp.pageobjects.LoginPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.COLUMNHEADER;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.COLUMNSETTING;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.FSQARESOURCE;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.FSQATAB;
import com.project.safetychain.pcapp.pageobjects.FileOperations.AvailableFormDetails;
import com.project.safetychain.pcapp.pageobjects.FileOperations.AvailableUserDetails;
import com.project.safetychain.pcapp.pageobjects.FormViewScreen.FormDetailsPC;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.utilities.ControlActions;
import com.project.utilities.ControlActions_PCApp;


public class TCG_SMK_Forms_FormFlow1 extends TestBase{
	//PC App
	ControlActions_PCApp controlActionsPC;
	CommonScreen commonScreen;
	FormsScreen forms;
	FormViewScreen formview; 
	FormDetailsPC formDetailsPC;


	LoginPage webLoginPage;
	HomePage webHomePage;
	FSQABrowserPage webfsqaFormsPage;
	ControlActions controlActionsWeb;


	public static String locationInstanceValue1;
	public static String resourceInstanceValue1,resourceInstanceValue2;

	String TextFieldName = "Text 1",
			ParagraphFieldName = "Para 1",
			SelectOneFieldName = "SO 1",
			selectMultipleFieldName = "SM 1 ",
			NumericFieldName = "Num 1",
			DateFieldName = "DT 1",
			TimeFieldName = "TM 1",
			DateTimeFieldName = "DTM 1";

	public static String checkFormName;
	public static String questionnaireFormName;
	public static String auditFormName1;

	List<String> selectOneMultipleFieldOptions = Arrays.asList("1","2","3","4");

	String AggSrcNumericFieldName = "Num 1",
			NumericFieldName2 = "Num 2",
			AggregateFieldName = "Agg 1",
			IdentifierldName = "IDN 1";

	String groupName = "Group 1";

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

			PCAppDriver = launchPCApp();
			controlActionsPC = new ControlActions_PCApp(PCAppDriver);
			forms = new FormsScreen(PCAppDriver);
			formview = new FormViewScreen(PCAppDriver);

			LoginScreen loginScreen = new LoginScreen(PCAppDriver);
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

	@Test(description="Forms || Verify user should be able to select the location & resource")
	public void TestCase_34838() {
		FormsScreen selectFormsTab  = commonScreen.selectForms();
		TestValidation.IsFalse(selectFormsTab.error,
				"OPENED Forms Tab", 
				"COULD not open Forms Tab");

		boolean openForm = selectFormsTab.selectOpenForm(checkFormName);
		TestValidation.IsTrue(openForm, 
				"SELECTED & opened form - "+checkFormName, 
				"FAILED open form  - "+checkFormName);

		boolean verifySelectedLocationResource = formview.selectLocationResource(locationInstanceValue1, resourceInstanceValue2);
		TestValidation.IsTrue(verifySelectedLocationResource, 
				"VERIFIED selected Location - '"+locationInstanceValue1+"' & Resource - '"+resourceInstanceValue2+"'", 
				"FAILED to veify selected location - '"+locationInstanceValue1+"' & Resource - '"+resourceInstanceValue2+"'");

		boolean navigateToMainScren = formview.saveForm();
		TestValidation.IsTrue(navigateToMainScren, 
				"NAVIGATED to main screen(after save)", 
				"FAILED to navigate to main screen(after save)");
	}

	@Test(description="Forms || Verify user should be able to submit the form having all field types")
	public void TestCase_34839() {		
		HashMap<String, List<String>> allFields = new LinkedHashMap<String, List<String>>();
		allFields.put(TextFieldName,Arrays.asList("Test 1"));
		allFields.put(ParagraphFieldName,Arrays.asList("Test 2"));
		allFields.put(SelectOneFieldName,Arrays.asList(selectOneMultipleFieldOptions.get(selectOneMultipleFieldOptions.size()-1)));
		String selectMultipleOptions;
		if(selectOneMultipleFieldOptions.size()>1) {
			selectMultipleOptions = selectOneMultipleFieldOptions.get(0);
			selectMultipleOptions = selectMultipleOptions+","+selectOneMultipleFieldOptions.get(selectOneMultipleFieldOptions.size()-1);
		}else {
			selectMultipleOptions = selectOneMultipleFieldOptions.get(0);
		}
		allFields.put(selectMultipleFieldName,Arrays.asList(selectMultipleOptions));
		allFields.put(NumericFieldName,Arrays.asList("8"));
		allFields.put(DateFieldName,Arrays.asList(controlActionsPC.getDayDate(0)));
		allFields.put(TimeFieldName,Arrays.asList("NA"));
		allFields.put(DateTimeFieldName,Arrays.asList("NA"));

		formDetailsPC = new FormDetailsPC();
		formDetailsPC.locationName = locationInstanceValue1;
		formDetailsPC.resourceName = resourceInstanceValue1;
		formDetailsPC.fieldDetails = allFields;

		FormsScreen selectFormsTab  = commonScreen.selectForms();
		TestValidation.IsFalse(selectFormsTab.error,
				"OPENED Forms Tab", 
				"COULD not open Forms Tab");

		boolean openForm = selectFormsTab.selectOpenForm(checkFormName);
		TestValidation.IsTrue(openForm, 
				"SELECTED & opened form - "+checkFormName, 
				"FAILED open form - "+checkFormName);

		boolean submitData = formview.fillDataInForm1(formDetailsPC);
		TestValidation.IsTrue(submitData, 
				"SUBMITTED data for form having all field types - "+checkFormName, 
				"FAILED to submit data for form having all field types - "+checkFormName);
	}

	@Test(description="Forms || Verify user should be able to submit the form having Aggrgate & Identifier type fields")
	public void TestCase_34840() {
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
				"FAILED open form  - "+questionnaireFormName);

		boolean submitData = formview.fillDataInForm1(formDetailsPC);
		TestValidation.IsTrue(submitData, 
				"SUBMITTED data for form having Aggrgate & Identifier fields- "+questionnaireFormName, 
				"FAILED to submit data for form having Aggrgate & Identifier fields - "+questionnaireFormName);
	}

	@Test(description="Forms || Verify user should be able to submit the form having field properties")
	public void TestCase_34842() {
		HashMap<String, List<String>> allFields = new LinkedHashMap<String, List<String>>();
		allFields.put(AggSrcNumericFieldName,Arrays.asList("2","4"));
		allFields.put(NumericFieldName2,Arrays.asList("4"));
		allFields.put(IdentifierldName,Arrays.asList("1234"));

		formDetailsPC = new FormDetailsPC();
		formDetailsPC.locationName = locationInstanceValue1;
		formDetailsPC.resourceName = resourceInstanceValue1;
		formDetailsPC.fieldDetails = allFields;
		formDetailsPC.propertyfieldName = NumericFieldName2;
		formDetailsPC.allowCommentCheck = true;
		formDetailsPC.showHintCheck = true;
		formDetailsPC.allowCorrectionsCheck = true;
		formDetailsPC.isSubmit = false;
		formDetailsPC.submitRepeatCheck = true; 
		formDetailsPC.discardCheck = true;

		FormsScreen selectFormsTab  = commonScreen.selectForms();
		TestValidation.IsFalse(selectFormsTab.error,
				"OPENED Forms Tab", 
				"COULD not open Forms Tab");

		boolean openForm = selectFormsTab.selectOpenForm(questionnaireFormName);
		TestValidation.IsTrue(openForm, 
				"SELECTED & opened form - "+questionnaireFormName, 
				"FAILED open form - "+questionnaireFormName);

		boolean submitData = formview.fillDataInForm1(formDetailsPC);
		TestValidation.IsTrue(submitData, 
				"SUBMITTED data for form having field properties - "+questionnaireFormName, 
				"FAILED to submit data for form having field properties - "+questionnaireFormName);
	}

	@Test(description="Forms || Verify user should be able to submit the form having attachment at form level")
	public void TestCase_37265() {
		HashMap<String, List<String>> allFields = new LinkedHashMap<String, List<String>>();
		allFields.put(AggSrcNumericFieldName,Arrays.asList("2","4"));
		allFields.put(NumericFieldName2,Arrays.asList("2"));
		allFields.put(IdentifierldName,Arrays.asList("1234"));

		formDetailsPC = new FormDetailsPC();
		formDetailsPC.locationName = locationInstanceValue1;
		formDetailsPC.resourceName = resourceInstanceValue1;
		formDetailsPC.fieldDetails = allFields;
		formDetailsPC.propertyfieldName = NumericFieldName2;
		formDetailsPC.allowAttachmentCheck = true;
		formDetailsPC.isSubmit = false;

		FormsScreen selectFormsTab  = commonScreen.selectForms();
		TestValidation.IsFalse(selectFormsTab.error,
				"OPENED Forms Tab", 
				"COULD not open Forms Tab");

		boolean openForm = selectFormsTab.selectOpenForm(questionnaireFormName);
		TestValidation.IsTrue(openForm, 
				"SELECTED & opened form - "+questionnaireFormName, 
				"FAILED open form - "+questionnaireFormName);

		boolean submitData = formview.fillDataInForm1(formDetailsPC);
		TestValidation.IsTrue(submitData, 
				"FILLED data for form with attachment at field level - "+questionnaireFormName, 
				"FAILED to fill data for form with attachment at field level - "+questionnaireFormName);

		boolean addFormLevelAttachment = formview.addFormLevelAttachment();
		TestValidation.IsTrue(addFormLevelAttachment, 
				"ADDED attachment at form level", 
				"FAILED to add attachment at form level");

		boolean submitForm = formview.submitForm(formDetailsPC.chartClose);
		TestValidation.IsTrue(submitForm, 
				"SUBMISSION is successfull", 
				"FAILED to submit form");
	}

	@Test(description="Submission || Verify the form submission should be shown")
	public void TestCase_34825() {
		HashMap<String, List<String>> allFields = new LinkedHashMap<String, List<String>>();

		allFields.put(TextFieldName,Arrays.asList("Test 1"));
		allFields.put(ParagraphFieldName,Arrays.asList("Test 2"));
		allFields.put(SelectOneFieldName,Arrays.asList(selectOneMultipleFieldOptions.get(selectOneMultipleFieldOptions.size()-1)));
		String selectMultipleOptions;
		if(selectOneMultipleFieldOptions.size()>1) {
			selectMultipleOptions = selectOneMultipleFieldOptions.get(0);
			selectMultipleOptions = selectMultipleOptions+","+selectOneMultipleFieldOptions.get(selectOneMultipleFieldOptions.size()-1);
		}else {
			selectMultipleOptions = selectOneMultipleFieldOptions.get(0);
		}
		System.out.println(selectMultipleOptions);
		allFields.put(selectMultipleFieldName,Arrays.asList(selectMultipleOptions));
		allFields.put(NumericFieldName,Arrays.asList("8"));
		allFields.put(DateFieldName,Arrays.asList(controlActionsPC.getDayDate(0)));
		allFields.put(TimeFieldName,Arrays.asList("NA"));
		allFields.put(DateTimeFieldName,Arrays.asList("NA"));

		formDetailsPC = new FormDetailsPC();
		formDetailsPC.locationName = locationInstanceValue1;
		formDetailsPC.resourceName = resourceInstanceValue1;
		formDetailsPC.fieldDetails = allFields;

		FormsScreen selectFormsTab  = commonScreen.selectForms();
		TestValidation.IsFalse(selectFormsTab.error,
				"OPENED Forms Tab", 
				"COULD not open Forms Tab");

		boolean openForm = selectFormsTab.selectOpenForm(checkFormName);
		TestValidation.IsTrue(openForm, 
				"SELECTED & opened form - "+checkFormName, 
				"FAILED open form  - "+checkFormName);

		boolean submitData = formview.fillDataInForm1(formDetailsPC);
		TestValidation.IsTrue(submitData, 
				"SUBMITTED data for form - "+checkFormName, 
				"FAILED ro submit data for form - "+checkFormName);

		SubmissionsScreen selectSubmissionTab  = commonScreen.selectSubmissions();
		TestValidation.IsFalse(selectSubmissionTab.error,
				"OPENED Submissions Tab", 
				"COULD not open Submissions Tab");

		boolean verifySubmission = selectSubmissionTab.verifySubmissionAvailability(checkFormName);
		TestValidation.IsTrue(verifySubmission, 
				"Form submission is shown - "+checkFormName, 
				"FAILED to verify form submission -"+checkFormName);

		try {

			driver = launchbrowser();
			webLoginPage = new LoginPage(driver);
			controlActionsWeb = new ControlActions(driver);

			controlActionsWeb.getUrl(applicationUrl);
			webHomePage = webLoginPage.performLogin(adminUsername, adminPassword);
			webfsqaFormsPage = new FSQABrowserPage(driver);
			webfsqaFormsPage.selectResourceDropDownandNavigate(FSQARESOURCE.ITEMS,FSQATAB.RECORDS);
			webfsqaFormsPage.openAndApplySettingsForColumn(COLUMNHEADER.FORMNAME, COLUMNSETTING.FILTER,checkFormName);
			webfsqaFormsPage.openAndApplySettingsForColumn(COLUMNHEADER.COMPLETEDBY, COLUMNSETTING.FILTER,userData.username);

			boolean verifySubmissionOnWeb = webfsqaFormsPage.openForDetails(1);
			TestValidation.IsTrue(verifySubmissionOnWeb, 
					"Form submission is shown in web app records - "+checkFormName, 
					"FAILED to verify form submission at web app records -"+checkFormName);

		} catch (Exception e) {
			logError("Failed to verify record on web app - "+e.getMessage());	
		}finally {
			driver.close();
		}

	}

	@Test(description="Forms || Verify user should be able to submit the form having rules")
	public void TestCase_34843() {
		HashMap<String, List<String>> allFields = new LinkedHashMap<String, List<String>>();
		allFields.put(AggSrcNumericFieldName,Arrays.asList("2","4"));
		allFields.put(NumericFieldName2,Arrays.asList("2"));
		allFields.put(IdentifierldName,Arrays.asList("1234"));

		formDetailsPC = new FormDetailsPC();
		formDetailsPC.locationName = locationInstanceValue1;
		formDetailsPC.resourceName = resourceInstanceValue1;
		formDetailsPC.fieldDetails = allFields;
		formDetailsPC.isSubmit = false;

		FormsScreen selectFormsTab  = commonScreen.selectForms();
		TestValidation.IsFalse(selectFormsTab.error,
				"OPENED Forms Tab", 
				"COULD not open Forms Tab");

		boolean openForm = selectFormsTab.selectOpenForm(questionnaireFormName);
		TestValidation.IsTrue(openForm, 
				"SELECTED & opened form - "+questionnaireFormName, 
				"FAILED open form  - "+questionnaireFormName);

		boolean submitData = formview.fillDataInForm1(formDetailsPC);
		TestValidation.IsTrue(submitData, 
				"FILLED data for form - "+questionnaireFormName, 
				"FAILED to fill data for form - "+questionnaireFormName);

		boolean verifyFormElements = formview.verifyFieldVisibility(groupName);
		TestValidation.IsTrue(verifyFormElements, 
				"VERIFIED form element - "+groupName, 
				"FAILED to verify form element - "+groupName);

		verifyFormElements = formview.verifyFieldVisibility(DateFieldName+" *");
		TestValidation.IsTrue(verifyFormElements, 
				"VERIFIED form elements - "+DateFieldName, 
				"FAILED to verify form element - "+DateFieldName);

		boolean submitForm = formview.submitForm(formDetailsPC.chartClose);
		TestValidation.IsTrue(submitForm, 
				"SUBMISSION is successfull", 
				"FAILED to submit form");

	}


	@AfterClass(alwaysRun = true)
	public void closeBrowser() throws InterruptedException {
		PCAppDriver.close();
	}

}
