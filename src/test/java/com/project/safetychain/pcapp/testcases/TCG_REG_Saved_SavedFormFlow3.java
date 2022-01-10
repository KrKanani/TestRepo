package com.project.safetychain.pcapp.testcases;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.project.safetychain.api.models.support.TCG_FormCreation_Wrapper;
import com.project.safetychain.api.models.support.TCG_TaskCreation_Wrapper;
import com.project.safetychain.api.models.support.TCG_UserFlows_Wrapper;
import com.project.safetychain.api.models.support.Support.DPT_FIELD_TYPES;
import com.project.safetychain.api.models.support.Support.DPT_FORM_TYPES;
import com.project.safetychain.api.models.support.Support.Element_Types;
import com.project.safetychain.api.models.support.Support.FormFieldParams;
import com.project.safetychain.api.models.support.Support.FormParams;
import com.project.safetychain.api.models.support.Support.TaskParams;
import com.project.safetychain.api.models.support.Support.UserParams;
import com.project.safetychain.pcapp.pageobjects.CommonScreen;
import com.project.safetychain.pcapp.pageobjects.FormViewScreen;
import com.project.safetychain.pcapp.pageobjects.FormsScreen;
import com.project.safetychain.pcapp.pageobjects.InboxScreen;
import com.project.safetychain.pcapp.pageobjects.LoginScreen;
import com.project.safetychain.pcapp.pageobjects.SavedScreen;
import com.project.safetychain.pcapp.pageobjects.SettingsScreen;
import com.project.safetychain.pcapp.pageobjects.FormViewScreen.FormDetailsPC;
import com.project.safetychain.pcapp.pageobjects.SavedScreen.SavedFormDetails;
import com.project.safetychain.pcapp.pageobjects.SavedScreen.SavedOnDetails;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage;
import com.project.safetychain.webapp.pageobjects.HomePage;
import com.project.safetychain.webapp.pageobjects.InboxPage;
import com.project.safetychain.webapp.pageobjects.LoginPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.COLUMNHEADER;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.COLUMNSETTING;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.FSQARESOURCE;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.FSQATAB;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FORMRESOURCETYPES;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FORMTYPES;
import com.project.safetychain.webapp.testcases.support.FormOperations;
import com.project.safetychain.webapp.testcases.support.FormOperations.FormDetails;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.utilities.ApiUtils;
import com.project.utilities.CommonMethods;
import com.project.utilities.ControlActions;
import com.project.utilities.ControlActions_PCApp;
import com.project.utilities.DateTime;

public class TCG_REG_Saved_SavedFormFlow3 extends TestBase{

	ApiUtils apiUtils = null;

	DateTime dateTimeDetails;

	public static String locationCategoryValue1;
	public static String locationInstanceValue1;
	public static String resourceCategoryType1 = FORMRESOURCETYPES.ITEMS;
	public static String resourceCategoryValue1;
	public static String resourceInstanceValue1;

	public static String formType1 = FORMTYPES.AUDIT;
	public static String auditFormName1;

	public static String newWGName;
	public static String saveTask1, saveTask2, saveTask3, deleteTask1;

	public static String newUserName;
	public static String newUserPassword, tempPassword;
	public static String userPin;

	String locInstId, ResInstId1;

	String SelectOneFieldName = "SO 1",
			SelectMultipleFieldName = "SM 1",
			NumericFieldName1 = "Num 1",
			NumericFieldName2 = "Num 2";

	String SectionName = "Section 1", GroupName = "Group 1";

	ControlActions_PCApp controlActionsPC;
	CommonScreen commonScreen;
	LoginScreen loginScreen;
	InboxScreen inboxScreen;
	FormsScreen formsScreen;
	SavedScreen savedScreen;
	FormViewScreen formview; 
	InboxPage webInboxPage;
	FormDetailsPC formDetailsPC;

	LoginPage webLoginPage;
	HomePage webHomePage;
	FSQABrowserPage webfsqaFormsPage;
	ControlActions controlActionsWeb;

	@BeforeTest(alwaysRun = true)
	public void groupinit() {

		apiUtils = new ApiUtils();

		dateTimeDetails = new DateTime();

		TCG_FormCreation_Wrapper formCreationWrapper = new TCG_FormCreation_Wrapper();
		TCG_TaskCreation_Wrapper taskCreationWrapper = new TCG_TaskCreation_Wrapper();
		TCG_UserFlows_Wrapper userCreationWrapper = new TCG_UserFlows_Wrapper();

		locationCategoryValue1 = CommonMethods.dynamicText("PC_SaveLCat");
		locationInstanceValue1 = CommonMethods.dynamicText("PC_SaveLInst1");
		resourceCategoryValue1 = CommonMethods.dynamicText("PC_SaveRCat");
		resourceInstanceValue1 = CommonMethods.dynamicText("PC_SaveRInst1");

		auditFormName1 = CommonMethods.dynamicText("PC_SaveForm1");

		saveTask1 = CommonMethods.dynamicText("PC_SaveTask1");
		saveTask2  = CommonMethods.dynamicText("PC_SaveTask2");
		saveTask3  = CommonMethods.dynamicText("PC_SaveTask3");
		deleteTask1 = CommonMethods.dynamicText("PC_SavedDeleteTask1");

		newUserName =  CommonMethods.dynamicText("PC_SaveFormFlowUser");
		newUserPassword = "Test";
		tempPassword = "Test12";
		newWGName =  CommonMethods.dynamicText("PC_SaveFormFlowFlowWG");
		userPin = "1234";

		HashMap<String, List<String>> resourceCatMap = new HashMap<String, List<String>>();
		resourceCatMap.put(resourceCategoryValue1, Arrays.asList(resourceInstanceValue1));

		HashMap<String, List<String>> LocationMap = new HashMap<String, List<String>>();
		LocationMap.put(locationCategoryValue1, Arrays.asList(locationInstanceValue1));

		List<String> userList = Arrays.asList(pcAppUsername);

		HashMap<String, String> locResMap = formCreationWrapper.Resource_Location_CreationWrapper(resourceCatMap,
				LocationMap, true, userList, resourceCategoryType1);

		locInstId = locResMap.get(locationInstanceValue1);
		ResInstId1 = locResMap.get(resourceInstanceValue1);

		Element_Types Section1 = new Element_Types();
		Section1.ElementType = DPT_FIELD_TYPES.SECTION;
		Section1.Name = SectionName;

		FormFieldParams NumericField1 = new FormFieldParams();
		NumericField1.DPTFieldType = DPT_FIELD_TYPES.NUMERIC;
		NumericField1.Name = NumericFieldName1;
		NumericField1.RepeatField = "true";

		FormFieldParams SelectOneField = new FormFieldParams();
		SelectOneField.DPTFieldType = DPT_FIELD_TYPES.SELECT_ONE;
		SelectOneField.Name = SelectOneFieldName;
		SelectOneField.QuestionWeight = "200";
		SelectOneField.SelectOptions = Arrays.asList("1,100","2,200");
		SelectOneField.RepeatField = "true";

		Section1.formFieldParams = Arrays.asList(NumericField1, SelectOneField);

		Element_Types fieldGroup1 = new Element_Types();
		fieldGroup1.ElementType = DPT_FIELD_TYPES.FIELD_GROUP;
		fieldGroup1.Name = GroupName;

		FormFieldParams SelectMultipleField = new FormFieldParams();
		SelectMultipleField = new FormFieldParams();
		SelectMultipleField.DPTFieldType = DPT_FIELD_TYPES.SELECT_MULTIPLE;
		SelectMultipleField.SelectOptions = Arrays.asList("1,100","2,200");
		SelectMultipleField.Name = SelectMultipleFieldName;
		SelectMultipleField.QuestionWeight = "300";
		SelectMultipleField.RepeatField = "true";

		FormFieldParams NumericField2 = new FormFieldParams();
		NumericField2 = new FormFieldParams();
		NumericField2.DPTFieldType = DPT_FIELD_TYPES.NUMERIC;
		NumericField2.Name = NumericFieldName2;
		NumericField2.RepeatField = "true";

		fieldGroup1.formFieldParams = Arrays.asList(SelectMultipleField, NumericField2);

		Section1.FieldGroupParams = Arrays.asList(fieldGroup1);

		String formId = apiUtils.getUUID();

		FormParams fp1 = new FormParams();
		fp1.FormId = formId;
		fp1.FormName = auditFormName1;
		fp1.type = DPT_FORM_TYPES.AUDIT;
		fp1.ResourceType = resourceCategoryType1;
		fp1.ResourceCategoryNm = resourceCategoryValue1;
		fp1.ItemsResources = resourceCatMap;
		fp1.SectionElements = Arrays.asList(Section1);

		boolean formcreation = false;
		try {
			formCreationWrapper.API_Wrapper_Forms(fp1);
			formcreation = true;
			logInfo("'"+auditFormName1+"' Form is created");
		} catch (InterruptedException e) {
			logError("Error while '"+auditFormName1+"' form creation");
			formcreation = false;
		}
		TestValidation.IsTrue(formcreation, 
				"CREATED form - " + auditFormName1, 
				"Could NOT create form - " + auditFormName1);

		UserParams userDetails = new UserParams();
		userDetails.Username = newUserName;
		userDetails.ClearPassword = GenericPassword;
		userDetails.FirstName = newUserName;
		userDetails.LastName = "User";
		userDetails.Email = "saveformflowuser.pcapp@s.com";
		userDetails.TimeZone = "U.S. Pacific";
		userDetails.LocationCat = locationCategoryValue1;
		userDetails.LocationNmIds = Arrays.asList(locationInstanceValue1);
		userDetails.Roles = Arrays.asList("SuperAdmin");
		userDetails.Pin = userPin;

		boolean userCreation = false;

		try {
			userCreationWrapper.createUser_Wrapper(userDetails);
			userCreation = true;
			logInfo("'"+newUserName+"' User is created");
		} catch (InterruptedException e) {
			logError("Error while '"+newUserName+"' user creation");
			userCreation = false;
		}

		TestValidation.IsTrue(userCreation, 
				"CREATED User - " + newUserName, 
				"Could NOT create user - " + newUserName);


		TaskParams tp = new TaskParams();
		tp.FormId = formId;
		tp.FormName = auditFormName1;
		tp.LocationInstanceNm = locationInstanceValue1;
		tp.ResourceInstanceNm = resourceInstanceValue1;
		tp.WorkGroupName = newWGName;
		tp.LocationInstanceId = locInstId;
		tp.ResourceInstanceId = ResInstId1;
		tp.UserName = newUserName;

		boolean taskCreationStatus = true;
		try {

			taskCreationWrapper.create_Link_workGroup_Wrapper(tp);

			tp.DueBy = "";
			tp.TaskName = saveTask1;
			taskCreationWrapper.create_Task_Wrapper(tp);

			tp.TaskName = saveTask2;
			taskCreationWrapper.create_Task_Wrapper(tp);

			tp.TaskName = saveTask3;
			taskCreationWrapper.create_Task_Wrapper(tp);

			tp.TaskName = deleteTask1;
			taskCreationWrapper.create_Task_Wrapper(tp);

			taskCreationStatus = true;
			logInfo("Created Workgroup & Tasks");
		} catch (InterruptedException e) {
			taskCreationStatus = false;
			logError("Failed to Create Workgroup & Tasks - "+e.getMessage());
		}

		TestValidation.IsTrue(taskCreationStatus, 
				"CREATED workgroup & tasks", 
				"Could NOT create workgroup & tasks");

		try {

			driver = launchbrowser();
			controlActionsWeb = new ControlActions(driver);
			controlActionsWeb.getUrl(pcAppTenantURL);
			webLoginPage = new LoginPage(driver);			

			if(!webLoginPage.performLogin(newUserName,GenericPassword,newUserPassword)) {
				TCGFailureMsg = "Could NOT login to application";
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}

		}catch (Exception e) {
			logError("Failed to perorm login with new password - "+e.getMessage());	
		}finally {
			driver.close();
		}

		PCAppDriver = launchPCApp();
		controlActionsPC = new ControlActions_PCApp(PCAppDriver);

		loginScreen = new LoginScreen(PCAppDriver);
		commonScreen = loginScreen.Login(pcAppTenantname, newUserName, newUserPassword);
		formsScreen = new FormsScreen(PCAppDriver);
		savedScreen = new SavedScreen(PCAppDriver);
		formview = new FormViewScreen(PCAppDriver);

	}


	@Test(description="Verify user should be able to save values in saved form")
	public void TestCase_33590() {

		formDetailsPC = new FormDetailsPC();

		String NumericField1Data = "2";
		String SelectOneFieldData = "1";
		String SelectMultipleField1Data = "1";
		String NumericField2Data = "4";

		String NumericField1Data_New = "4";
		String SelectOneFieldData_New = "2";
		String SelectMultipleField1Data_Option1 = "1";
		String SelectMultipleField1Data_Option2 = "2";
		String SelectMultipleField1Data_New = SelectMultipleField1Data_Option1+","+SelectMultipleField1Data_Option2;
		String NumericField2Data_New = "8";

		HashMap<String, List<String>> allFields1 = new LinkedHashMap<String, List<String>>();
		allFields1.put(NumericFieldName1,Arrays.asList(NumericField1Data));
		allFields1.put(SelectOneFieldName,Arrays.asList(SelectOneFieldData));
		allFields1.put(SelectMultipleFieldName,Arrays.asList(SelectMultipleField1Data));
		allFields1.put(NumericFieldName2,Arrays.asList(NumericField2Data));

		HashMap<String, List<String>> allFields2 = new LinkedHashMap<String, List<String>>();
		allFields2 = new LinkedHashMap<String, List<String>>();
		allFields2.put(NumericFieldName1,Arrays.asList(NumericField1Data_New));
		allFields2.put(SelectOneFieldName,Arrays.asList(SelectOneFieldData_New));
		allFields2.put(SelectMultipleFieldName,Arrays.asList(SelectMultipleField1Data_Option2));
		allFields2.put(NumericFieldName2,Arrays.asList(NumericField2Data_New));

		HashMap<String, String> allFieldsData1 = new LinkedHashMap<String, String>();
		allFieldsData1.put(NumericFieldName1,NumericField1Data);
		allFieldsData1.put(SelectOneFieldName,SelectOneFieldData);
		allFieldsData1.put(SelectMultipleFieldName,SelectMultipleField1Data);
		allFieldsData1.put(NumericFieldName2,NumericField2Data);

		HashMap<String, String> allFieldsData2 = new LinkedHashMap<String, String>();
		allFieldsData2 = new LinkedHashMap<String, String>();
		allFieldsData2.put(NumericFieldName1,NumericField1Data_New);
		allFieldsData2.put(SelectOneFieldName,SelectOneFieldData_New);
		allFieldsData2.put(SelectMultipleFieldName,SelectMultipleField1Data_New);
		allFieldsData2.put(NumericFieldName2,NumericField2Data_New);

		formDetailsPC.selectLocationResource = false;
		formDetailsPC.locationName = locationInstanceValue1;
		formDetailsPC.resourceName = resourceInstanceValue1;
		formDetailsPC.isSubmit = false;


		formDetailsPC.fieldDetails = allFields1;

		InboxScreen selectInboxTab  = commonScreen.selectInbox();
		TestValidation.IsFalse(selectInboxTab.error,
				"OPENED Inbox Tab", 
				"COULD not open Inbox Tab");

		boolean openTask = selectInboxTab.selectOpenTask(saveTask1, false);
		TestValidation.IsTrue(openTask, 
				"SELECTED & opened task - "+saveTask1, 
				"FAILED open task - "+saveTask1);

		boolean fillData = formview.fillDataInForm1(formDetailsPC);
		TestValidation.IsTrue(fillData, 
				"FILLED data in task - "+saveTask1, 
				"FAILED to fill data in task - "+saveTask1);

		boolean saveForm = formview.saveForm();
		TestValidation.IsTrue(saveForm, 
				"SAVED task - "+saveTask1, 
				"FAILED to save the task - "+saveTask1);

		savedScreen = commonScreen.selectSaved();
		TestValidation.IsFalse(savedScreen.error,
				"OPENED Saved Tab", 
				"COULD not open Saved Tab");

		boolean openSavedForm = savedScreen.selectOpenForm(auditFormName1);
		TestValidation.IsTrue(openSavedForm, 
				"SELECTED & opened saved form(task) - "+auditFormName1, 
				"FAILED to open saved form(task) - "+auditFormName1);

		boolean verifySavedFormata = savedScreen.verifySavedFormData(allFieldsData1);
		TestValidation.IsTrue(verifySavedFormata, 
				"Verified data in saved form(task) - "+auditFormName1, 
				"FAILED to verify data in saved form(task) - "+auditFormName1);

		saveForm = formview.saveForm();
		TestValidation.IsTrue(saveForm, 
				"SAVED form(task)  - "+auditFormName1, 
				"FAILED to save the form(task) - "+auditFormName1);

		formDetailsPC.fieldDetails = allFields2;

		openSavedForm = savedScreen.selectOpenForm(auditFormName1);
		TestValidation.IsTrue(openSavedForm, 
				"SELECTED & opened saved form(task) - "+auditFormName1, 
				"FAILED to open saved form(task) - "+auditFormName1);

		fillData = formview.fillDataInForm1(formDetailsPC);
		TestValidation.IsTrue(fillData, 
				"FILLED new data in saved form(task) - "+auditFormName1, 
				"FAILED to fill new data in saved for (task) - "+auditFormName1);

		saveForm = formview.saveForm();
		TestValidation.IsTrue(saveForm, 
				"SAVED form(task)  - "+auditFormName1, 
				"FAILED to save the form(task) - "+auditFormName1);

		openSavedForm = savedScreen.selectOpenForm(auditFormName1);
		TestValidation.IsTrue(openSavedForm, 
				"SELECTED & opened saved form(task) - "+auditFormName1, 
				"FAILED to open saved form(task) - "+auditFormName1);

		verifySavedFormata = savedScreen.verifySavedFormData(allFieldsData2);
		TestValidation.IsTrue(verifySavedFormata, 
				"Verified data in saved form(task) - "+auditFormName1, 
				"FAILED to verify data in saved form(task) - "+auditFormName1);

		boolean deleteForm = savedScreen.deleteForm(auditFormName1);
		TestValidation.IsTrue(deleteForm, 
				"DELETED saved form(task) - "+auditFormName1, 
				"FAILED todelete saved form(task) - "+auditFormName1);

		formDetailsPC.fieldDetails = allFields1;
		FormsScreen selectFormsTab  = commonScreen.selectForms();
		TestValidation.IsFalse(selectFormsTab.error,
				"OPENED Forms Tab", 
				"COULD not open Forms Tab");

		boolean openForm = selectFormsTab.selectOpenForm(auditFormName1);
		TestValidation.IsTrue(openForm, 
				"SELECTED & opened form - "+auditFormName1, 
				"FAILED open form - "+auditFormName1);

		fillData = formview.fillDataInForm1(formDetailsPC);
		TestValidation.IsTrue(fillData, 
				"FILLED data in form - "+auditFormName1, 
				"FAILED to fill data in form - "+auditFormName1);

		saveForm = formview.saveForm();
		TestValidation.IsTrue(saveForm, 
				"SAVED form - "+auditFormName1, 
				"FAILED to save the form - "+auditFormName1);

		savedScreen = commonScreen.selectSaved();
		TestValidation.IsFalse(savedScreen.error,
				"OPENED Saved Tab", 
				"COULD not open Saved Tab");

		openSavedForm = savedScreen.selectOpenForm(auditFormName1);
		TestValidation.IsTrue(openSavedForm, 
				"SELECTED & opened saved form - "+auditFormName1, 
				"FAILED to open saved form - "+auditFormName1);

		verifySavedFormata = savedScreen.verifySavedFormData(allFieldsData1);
		TestValidation.IsTrue(verifySavedFormata, 
				"Verified data in saved form - "+auditFormName1, 
				"FAILED to verify data in saved form - "+auditFormName1);

		saveForm = formview.saveForm();
		TestValidation.IsTrue(saveForm, 
				"SAVED task  - "+saveTask1, 
				"FAILED to save the task - "+saveTask1);

		formDetailsPC.fieldDetails = allFields2;

		openSavedForm = savedScreen.selectOpenForm(auditFormName1);
		TestValidation.IsTrue(openSavedForm, 
				"SELECTED & opened saved form - "+auditFormName1, 
				"FAILED to open saved form - "+auditFormName1);

		fillData = formview.fillDataInForm1(formDetailsPC);
		TestValidation.IsTrue(fillData, 
				"FILLED new data for form - "+auditFormName1, 
				"FAILED to fill new data for form - "+auditFormName1);

		saveForm = formview.saveForm();
		TestValidation.IsTrue(saveForm, 
				"SAVED form - "+auditFormName1, 
				"FAILED to save the form - "+auditFormName1);

		openSavedForm = savedScreen.selectOpenForm(auditFormName1);
		TestValidation.IsTrue(openSavedForm, 
				"SELECTED & opened saved form - "+auditFormName1, 
				"FAILED to open saved form - "+auditFormName1);

		verifySavedFormata = savedScreen.verifySavedFormData(allFieldsData2);
		TestValidation.IsTrue(verifySavedFormata, 
				"Verified data in saved form - "+auditFormName1, 
				"FAILED to verify data in saved form- "+auditFormName1);

		deleteForm = savedScreen.deleteForm(auditFormName1);
		TestValidation.IsTrue(deleteForm, 
				"DELETED saved form - "+auditFormName1, 
				"FAILED to delete saved form - "+auditFormName1);
	}

	@Test(description="Saved || Verify the saved forms details")
	public void TestCase_33591() {

		FormsScreen selectFormsTab  = commonScreen.selectForms();
		TestValidation.IsFalse(selectFormsTab.error,
				"OPENED Forms Tab", 
				"COULD not open Forms Tab");

		boolean openForm = selectFormsTab.selectOpenForm(auditFormName1);
		TestValidation.IsTrue(openForm, 
				"SELECTED & opened form - "+auditFormName1, 
				"FAILED open form - "+auditFormName1);

		boolean saveForm = formview.saveForm();
		TestValidation.IsTrue(saveForm, 
				"SAVED form - "+auditFormName1, 
				"FAILED to save the form - "+auditFormName1);

		savedScreen = commonScreen.selectSaved();
		TestValidation.IsFalse(savedScreen.error,
				"OPENED Saved Tab", 
				"COULD not open Saved Tab");

		SavedFormDetails savedFormDetails = new SavedFormDetails();
		SavedOnDetails savedFormSavedOnDetails = new SavedOnDetails();

		savedFormSavedOnDetails.currentDay = dateTimeDetails.getDateTime("M/d/yyyy");
		savedFormSavedOnDetails.currentHour = dateTimeDetails.getDateTime("h");
		savedFormSavedOnDetails.format = "MM/dd/yyyy HH:mm";

		savedFormDetails.formName = auditFormName1;
		savedFormDetails.location = locationInstanceValue1;
		savedFormDetails.resource = resourceInstanceValue1;
		savedFormDetails.date = dateTimeDetails.getDateTime("MM/dd/yyyy");
		savedFormDetails.savedOnDetails = savedFormSavedOnDetails;

		boolean verifySavedFormDeails = savedScreen.verifySavedFormDetails(savedFormDetails, savedFormSavedOnDetails);
		TestValidation.IsTrue(verifySavedFormDeails, 
				"VERIFIED saved form details - "+auditFormName1, 
				"FAILED to verify saved form details - "+auditFormName1);

		boolean deleteForm = savedScreen.deleteUnopenendForm(auditFormName1);
		TestValidation.IsTrue(deleteForm, 
				"DELETED saved form - "+auditFormName1, 
				"FAILED to delete saved form - "+auditFormName1);
	}

	@Test(description="Verify the refresh data functionality")
	public void TestCase_33595() {

		String NumericField1Data = "2";
		String SelectOneFieldData = "1";
		String SelectMultipleField1Data = "1";
		String NumericField2Data = "4";

		boolean saveformFromWeb = false;
		try {

			driver = launchbrowser();
			webLoginPage = new LoginPage(driver);
			controlActionsWeb = new ControlActions(driver);

			FormOperations formOperationsWeb = new FormOperations(driver);

			controlActionsWeb.getUrl(pcAppTenantURL);
			webHomePage = webLoginPage.performLogin(newUserName, newUserPassword);
			webfsqaFormsPage = new FSQABrowserPage(driver);
			webfsqaFormsPage.clickFSQABrowserMenu();
			webfsqaFormsPage.selectResourceDropDownandNavigate(FSQARESOURCE.ITEMS,FSQATAB.FORMS);

			webfsqaFormsPage.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, auditFormName1);

			FormDetails formDetailsWeb = new FormDetails();
			HashMap<String, List<String>> formFieldDetailsWeb = new HashMap<String, List<String>>();
			formFieldDetailsWeb.put(NumericFieldName1, Arrays.asList(NumericField1Data));
			formFieldDetailsWeb.put(SelectOneFieldName, Arrays.asList(SelectOneFieldData));
			formFieldDetailsWeb.put(SelectMultipleFieldName, Arrays.asList(SelectMultipleField1Data));	
			formFieldDetailsWeb.put(NumericFieldName2, Arrays.asList(NumericField2Data));

			formDetailsWeb.fieldDetails = formFieldDetailsWeb;
			formDetailsWeb.resourceName = resourceInstanceValue1;
			formDetailsWeb.locationName = locationInstanceValue1;
			formDetailsWeb.isSubmit = false;

			saveformFromWeb = formOperationsWeb.submitData(formDetailsWeb);
			TestValidation.IsTrue(saveformFromWeb, 
					"SAVED FORM - '"+auditFormName1+"' from Web App", 
					"UNABLE TO SAVE FORM - '"+auditFormName1+"' from Web App");

		} catch (Exception e) {
			saveformFromWeb = false;
			TestValidation.IsTrue(saveformFromWeb, 
					"SAVED FORM - '"+auditFormName1+"' from Web App", 
					"FAILED TO SAVE FORM - '"+auditFormName1+"' from Web App");
		}

		savedScreen = commonScreen.selectSaved();
		TestValidation.IsFalse(savedScreen.error,
				"OPENED Saved Tab", 
				"COULD not open Saved Tab");

		boolean	refreshSavedForms = savedScreen.refreshSavedForm();
		TestValidation.IsTrue(refreshSavedForms, 
				"REFRESHED saved form", 
				"FAILED to refresh saved form");

		HashMap<String, String> allFieldsData1 = new LinkedHashMap<String, String>();
		allFieldsData1.put(NumericFieldName1,NumericField1Data);
		allFieldsData1.put(SelectOneFieldName,SelectOneFieldData);
		allFieldsData1.put(SelectMultipleFieldName,SelectMultipleField1Data);
		allFieldsData1.put(NumericFieldName2,NumericField2Data);

		boolean	verifySavedFormAvailability = savedScreen.verifySavedFormAvailability(auditFormName1);
		TestValidation.IsTrue(verifySavedFormAvailability, 
				"VERIFIED saved form availability after searching form - "+auditFormName1, 
				"FAILED to verify saved form availability after searching form - "+auditFormName1);

		boolean openSavedForm = savedScreen.selectOpenForm(auditFormName1);
		TestValidation.IsTrue(openSavedForm, 
				"SELECTED & opened saved form - "+auditFormName1, 
				"FAILED to open saved form - "+auditFormName1);

		boolean verifySavedFormata = savedScreen.verifySavedFormData(allFieldsData1);
		TestValidation.IsTrue(verifySavedFormata, 
				"Verified data in saved form - "+auditFormName1, 
				"FAILED to verify data in saved form - "+auditFormName1);

		boolean deleteForm = savedScreen.deleteForm(auditFormName1);
		TestValidation.IsTrue(deleteForm, 
				"DELETED saved form - "+auditFormName1, 
				"FAILED to delete saved form - "+auditFormName1);
	}

	@Test(description="Saved || Verify Delete saved form functionality")
	public void TestCase_33596() {

		FormsScreen selectFormsTab  = commonScreen.selectForms();
		TestValidation.IsFalse(selectFormsTab.error,
				"OPENED Forms Tab", 
				"COULD not open Forms Tab");

		boolean openForm = selectFormsTab.selectOpenForm(auditFormName1);
		TestValidation.IsTrue(openForm, 
				"SELECTED & opened form - "+auditFormName1, 
				"FAILED open form - "+auditFormName1);

		boolean saveForm = formview.saveForm();
		TestValidation.IsTrue(saveForm, 
				"SAVED form - "+auditFormName1, 
				"FAILED to save the form - "+auditFormName1);

		savedScreen = commonScreen.selectSaved();
		TestValidation.IsFalse(savedScreen.error,
				"OPENED Saved Tab", 
				"COULD not open Saved Tab");

		boolean openSavedForm = savedScreen.selectOpenForm(auditFormName1);
		TestValidation.IsTrue(openSavedForm, 
				"SELECTED & opened saved form - "+auditFormName1, 
				"FAILED open saved form - "+auditFormName1);

		boolean deleteForm = savedScreen.deleteForm(auditFormName1);
		TestValidation.IsTrue(deleteForm, 
				"DELETED saved form - "+auditFormName1, 
				"FAILED to delete saved form - "+auditFormName1);

	}

	@Test(description="Saved || Verify saved form count after submission of all saved forms")
	public void TestCase_38096() {

		formDetailsPC = new FormDetailsPC();

		String NumericField1Data = "2";
		String SelectOneFieldData = "1";
		String SelectMultipleField1Data = "1";
		String NumericField2Data = "4";

		HashMap<String, List<String>> allFields1 = new LinkedHashMap<String, List<String>>();
		allFields1.put(NumericFieldName1,Arrays.asList(NumericField1Data));
		allFields1.put(SelectOneFieldName,Arrays.asList(SelectOneFieldData));
		allFields1.put(SelectMultipleFieldName,Arrays.asList(SelectMultipleField1Data));
		allFields1.put(NumericFieldName2,Arrays.asList(NumericField2Data));

		formDetailsPC.locationName = locationInstanceValue1;
		formDetailsPC.resourceName = resourceInstanceValue1;
		formDetailsPC.selectLocationResource = false;
		formDetailsPC.allowAttachmentCheck = true;
		formDetailsPC.fieldDetails = allFields1;

		FormsScreen selectFormsTab  = commonScreen.selectForms();
		TestValidation.IsFalse(selectFormsTab.error,
				"OPENED Forms Tab", 
				"COULD not open Forms Tab");

		boolean openForm = selectFormsTab.selectOpenForm(auditFormName1);
		TestValidation.IsTrue(openForm, 
				"SELECTED & opened form - "+auditFormName1, 
				"FAILED open form - "+auditFormName1);

		boolean saveForm = formview.saveForm();
		TestValidation.IsTrue(saveForm, 
				"SAVED form - "+auditFormName1, 
				"FAILED to save the form - "+auditFormName1);

		savedScreen = commonScreen.selectSaved();
		TestValidation.IsFalse(savedScreen.error,
				"OPENED Saved Tab", 
				"COULD not open Saved Tab");

		boolean openSavedForm = savedScreen.selectOpenForm(auditFormName1);
		TestValidation.IsTrue(openSavedForm, 
				"SELECTED & opened saved form - "+auditFormName1, 
				"FAILED to open saved form - "+auditFormName1);

		boolean addFormLevelAttachment = formview.addFormLevelAttachment();
		TestValidation.IsTrue(addFormLevelAttachment, 
				"ADDED form level attachment", 
				"FAILED to add form level attachment");

		boolean fillData = formview.fillDataInForm1(formDetailsPC);
		TestValidation.IsTrue(fillData, 
				"SUBMITTED saved form - "+auditFormName1, 
				"FAILED to submit saved form - "+auditFormName1);

		boolean verifySavedFormCount = savedScreen.verifyCount(0);
		TestValidation.IsTrue(verifySavedFormCount, 
				"VERIFIED saved form count after submission", 
				"FAILED to verify saved form count after submission");

	}

	@Test(description="View attached images || Verify user is able to view image attachment while saving form in saved tab")
	public void TestCase_48668() {

		FormsScreen selectFormsTab  = commonScreen.selectForms();
		TestValidation.IsFalse(selectFormsTab.error,
				"OPENED Forms Tab", 
				"COULD not open Forms Tab");

		boolean openForm = selectFormsTab.selectOpenForm(auditFormName1);
		TestValidation.IsTrue(openForm, 
				"SELECTED & opened form - "+auditFormName1, 
				"FAILED open form - "+auditFormName1);

		boolean saveForm = formview.saveForm();
		TestValidation.IsTrue(saveForm, 
				"SAVED form - "+auditFormName1, 
				"FAILED to save the form - "+auditFormName1);

		savedScreen = commonScreen.selectSaved();
		TestValidation.IsFalse(savedScreen.error,
				"OPENED Saved Tab", 
				"COULD not open Saved Tab");

		boolean openSavedForm = savedScreen.selectOpenForm(auditFormName1);
		TestValidation.IsTrue(openSavedForm, 
				"SELECTED & opened saved form - "+auditFormName1, 
				"FAILED to open saved form - "+auditFormName1);

		boolean addFormLevelAttachment = formview.addFormLevelAttachment();
		TestValidation.IsTrue(addFormLevelAttachment, 
				"ADDED & VIEWED form level attachment", 
				"FAILED to add & view form level attachment");

		boolean deleteForm = savedScreen.deleteForm(auditFormName1);
		TestValidation.IsTrue(deleteForm, 
				"DELETED saved form - "+auditFormName1, 
				"FAILED to delete saved form - "+auditFormName1);

	}

	@Test(description="Saved || Perform 'Direct Observation' on saved form & verify submissions in web records")
	public void TestCase_42992() {

		formDetailsPC = new FormDetailsPC();

		String NumericField1Data = "2";
		String SelectOneFieldData = "1";
		String SelectMultipleField1Data = "1";
		String NumericField2Data = "4";

		HashMap<String, List<String>> allFields1 = new LinkedHashMap<String, List<String>>();
		allFields1.put(NumericFieldName1,Arrays.asList(NumericField1Data));
		allFields1.put(SelectOneFieldName,Arrays.asList(SelectOneFieldData));
		allFields1.put(SelectMultipleFieldName,Arrays.asList(SelectMultipleField1Data));
		allFields1.put(NumericFieldName2,Arrays.asList(NumericField2Data));

		formDetailsPC.locationName = locationInstanceValue1;
		formDetailsPC.resourceName = resourceInstanceValue1;
		formDetailsPC.selectLocationResource = false;
		formDetailsPC.allowAttachmentCheck = true;
		formDetailsPC.fieldDetails = allFields1;

		FormsScreen selectFormsTab  = commonScreen.selectForms();
		TestValidation.IsFalse(selectFormsTab.error,
				"OPENED Forms Tab", 
				"COULD not open Forms Tab");

		boolean openForm = selectFormsTab.selectOpenForm(auditFormName1);
		TestValidation.IsTrue(openForm, 
				"SELECTED & opened form - "+auditFormName1, 
				"FAILED to open form - "+auditFormName1);

		boolean saveForm = formview.saveForm();
		TestValidation.IsTrue(saveForm, 
				"SAVED form - "+auditFormName1, 
				"FAILED to save the form - "+auditFormName1);

		savedScreen = commonScreen.selectSaved();
		TestValidation.IsFalse(savedScreen.error,
				"OPENED Saved Tab", 
				"COULD not open Saved Tab");

		boolean openSavedForm = savedScreen.selectOpenForm(auditFormName1);
		TestValidation.IsTrue(openSavedForm, 
				"SELECTED & opened saved form - "+auditFormName1, 
				"FAILED to open saved form - "+auditFormName1);

		boolean performVerification = formview.performDirectObservation(newUserName, userPin);
		TestValidation.IsTrue(performVerification, 
				"PERFORMED Verification(Direct Verification)", 
				"FAILED to perform Verification(Direct Observation)");

		boolean fillData = formview.fillDataInForm1(formDetailsPC);
		TestValidation.IsTrue(fillData, 
				"SUBMITTED saved form - "+auditFormName1, 
				"FAILED to submit saved form - "+auditFormName1);

		try {

			driver = launchbrowser();
			webLoginPage = new LoginPage(driver);
			controlActionsWeb = new ControlActions(driver);

			controlActionsWeb.getUrl(applicationUrl);
			webHomePage = webLoginPage.performLogin(newUserName, newUserPassword);
			webfsqaFormsPage = new FSQABrowserPage(driver);
			webfsqaFormsPage.selectResourceDropDownandNavigate(FSQARESOURCE.ITEMS,FSQATAB.RECORDS);
			webfsqaFormsPage.openAndApplySettingsForColumn(COLUMNHEADER.FORMNAME, COLUMNSETTING.FILTER,auditFormName1);
			webfsqaFormsPage.openAndApplySettingsForColumn(COLUMNHEADER.COMPLETEDBY, COLUMNSETTING.FILTER,newUserName);
			webfsqaFormsPage.openAndApplySettingsForColumn(COLUMNHEADER.SIGNOFF, COLUMNSETTING.FILTER,"0");

			boolean verifySubmissionOnWeb = webfsqaFormsPage.openForDetails(1);
			TestValidation.IsTrue(verifySubmissionOnWeb, 
					"Form submission is shown in web app records(Direct Observation) - "+auditFormName1, 
					"FAILED to verify form submission at web app records(Direct Observation) -"+auditFormName1);

		} catch (Exception e) {
			logError("Failed to verify record(Direct Observation) on web app - "+e.getMessage());	
		}finally {
			driver.close();
		}

	}

	@Test(description="Saved || Inbox || Verify saving of form on resubmitted saved form(saved task)")
	public void TestCase_41010() {
		formDetailsPC = new FormDetailsPC();

		String NumericField1Data = "2";
		String SelectOneFieldData = "1";
		String SelectMultipleField1Data = "1";
		String NumericField2Data = "4";

		HashMap<String, List<String>> allFields1 = new LinkedHashMap<String, List<String>>();
		allFields1.put(NumericFieldName1,Arrays.asList(NumericField1Data));
		allFields1.put(SelectOneFieldName,Arrays.asList(SelectOneFieldData));
		allFields1.put(SelectMultipleFieldName,Arrays.asList(SelectMultipleField1Data));
		allFields1.put(NumericFieldName2,Arrays.asList(NumericField2Data));

		formDetailsPC.locationName = locationInstanceValue1;
		formDetailsPC.resourceName = resourceInstanceValue1;
		formDetailsPC.selectLocationResource = false;
		formDetailsPC.fieldDetails = allFields1;
		formDetailsPC.isSubmit = false;
		formDetailsPC.submitRepeatCheck = true;

		InboxScreen selectInboxTab  = commonScreen.selectInbox();
		TestValidation.IsFalse(selectInboxTab.error,
				"OPENED Inbox Tab", 
				"COULD not open Inbox Tab");

		boolean openTask = selectInboxTab.selectOpenTask(saveTask3, false);
		TestValidation.IsTrue(openTask, 
				"SELECTED & opened task - "+saveTask3, 
				"FAILED open task - "+saveTask3);

		boolean saveForm = formview.saveForm();
		TestValidation.IsTrue(saveForm, 
				"SAVED task - "+saveTask3, 
				"FAILED to save the task - "+saveTask3);

		savedScreen = commonScreen.selectSaved();
		TestValidation.IsFalse(savedScreen.error,
				"OPENED Saved Tab", 
				"COULD not open Saved Tab");

		boolean openSavedTask = savedScreen.selectOpenForm(auditFormName1);
		TestValidation.IsTrue(openSavedTask, 
				"SELECTED & opened saved form(task) - "+auditFormName1, 
				"FAILED open saved form(task) - "+auditFormName1);

		boolean fillData = formview.fillDataInForm1(formDetailsPC);
		TestValidation.IsTrue(fillData, 
				"SUBMIT REPEAT saved form(task) - "+auditFormName1, 
				"FAILED to SUBMIT REPEAT saved form(task) - "+auditFormName1);

		boolean saveSubmiitedSavedForm = formview.saveForm();
		TestValidation.IsTrue(saveSubmiitedSavedForm, 
				"SAVED submitted saved form(task) - "+auditFormName1, 
				"FAILED to save submitted saved form(task) - "+auditFormName1);

		boolean delteSaveForm = savedScreen.deleteUnopenendForm(auditFormName1);
		TestValidation.IsTrue(delteSaveForm, 
				"DELETED  saved form - "+auditFormName1, 
				"FAILED to deleted submitted saved form - "+auditFormName1);

	}

	@Test(description="Saved || Verify save form validation on deleted saved form")
	public void TestCase_48419() {

		InboxScreen selectInboxTab  = commonScreen.selectInbox();
		TestValidation.IsFalse(selectInboxTab.error,
				"OPENED Inbox Tab", 
				"COULD not open Inbox Tab");

		boolean openTask = selectInboxTab.selectOpenTask(deleteTask1, false);
		TestValidation.IsTrue(openTask, 
				"SELECTED & opened task - "+deleteTask1, 
				"FAILED open task - "+deleteTask1);

		boolean saveForm = formview.saveForm();
		TestValidation.IsTrue(saveForm, 
				"SAVED task - "+deleteTask1, 
				"FAILED to save the task - "+deleteTask1);

		savedScreen = commonScreen.selectSaved();
		TestValidation.IsFalse(savedScreen.error,
				"OPENED Saved Tab", 
				"COULD not open Saved Tab");

		boolean deleteTaskFromWeb = false;
		try {

			driver = launchbrowser();
			webLoginPage = new LoginPage(driver);
			controlActionsWeb = new ControlActions(driver);

			controlActionsWeb.getUrl(pcAppTenantURL);
			webHomePage = webLoginPage.performLogin(newUserName, newUserPassword);

			webInboxPage = new InboxPage(driver);
			webInboxPage.clickInboxMenu();

			webInboxPage.selectFormTask(deleteTask1);

			deleteTaskFromWeb = webInboxPage.deleteSavedTask();
			TestValidation.IsTrue(deleteTaskFromWeb, 
					"DELETED saved Task - '"+deleteTask1+" from Web App Inbox", 
					"UNABLE TO delete saved task - '"+deleteTask1+"' from Web App Inbox");

		}catch(Exception e) {
			deleteTaskFromWeb = false;
			TestValidation.IsTrue(deleteTaskFromWeb, 
					"DELETED task on Web App Inbox", 
					"UNABLE to delete the saved task on Web App Inbox");
		}finally {
			driver.close();
		}

		boolean openSavedForm = savedScreen.selectOpenForm(auditFormName1);
		TestValidation.IsTrue(openSavedForm, 
				"SELECTED & opened saved form - "+auditFormName1, 
				"FAILED to open saved form - "+auditFormName1);

		boolean verifyErrorMsg = formview.verifyRecordID_DeletedMsg();
		TestValidation.IsTrue(verifyErrorMsg, 
				"VERIFIED 'RecordId Already Deleted' error message'", 
				"FAILED to verify 'RecordId Already Deleted' error message");

		boolean deleteForm = savedScreen.deleteUnopenendForm(auditFormName1);
		TestValidation.IsTrue(deleteForm, 
				"DELETED saved form entry from PC App - "+auditFormName1, 
				"FAILED to delete saved form entry from PC App - "+auditFormName1);

	}

	@Test(description = "Password change Dialog || Verify user is able to delete saved form from saved tab when password is changed from web App ")
	public void TestCase_45624() throws InterruptedException {

		SettingsScreen settings = commonScreen.selectSettings();
		TestValidation.IsFalse(settings.error,
				"OPENED Settings Tab", 
				"COULD not open Settings Tab");	

		boolean toggle = settings.changeOfflineModeToggle(true);
		TestValidation.IsTrue(toggle,
				"Switched ON offline mode",
				"COULD not switch on offline mode");

		TCG_UserFlows_Wrapper userflow = new TCG_UserFlows_Wrapper();		
		userflow.resetPassword(newUserName,tempPassword);

		boolean reLogin = loginScreen.reLogin(newUserName, newUserPassword);
		TestValidation.IsTrue(reLogin,
				"Log out & logged in in Offline Mode",
				"COULD not login in offline mode");	

		settings = commonScreen.selectSettings();
		TestValidation.IsFalse(settings.error,
				"OPENED Settings Tab", 
				"COULD not open Settings Tab");	

		boolean toggle1 = settings.changeOfflineModeToggle(false);
		TestValidation.IsTrue(toggle1,
				"Switched off offline mode",
				"COULD not switch off offline mode");

		Thread.sleep(5000);

		savedScreen = commonScreen.selectSaved();
		TestValidation.IsFalse(savedScreen.error,
				"OPENED Saved Tab", 
				"COULD not open Saved Tab");

		boolean refreshSavedForms = savedScreen.refreshSavedForm();
		TestValidation.IsFalse(refreshSavedForms,
				"UNSUCCESSFULL Refresh",
				"SUCCESSFULL Refresh. User haven't asked for new password");

		boolean password = savedScreen.loginOnPasswordChange(tempPassword);
		TestValidation.IsTrue(password,
				"PROVIDED New password",
				"COULD not provide new password");

		refreshSavedForms = savedScreen.refreshSavedForm();
		TestValidation.IsTrue(refreshSavedForms,
				"REFRESHED Saved forms",
				"COULD not refresh saved form");

		userflow.resetPassword(newUserName, newUserPassword);

		try {

			driver = launchbrowser();
			webLoginPage = new LoginPage(driver);		

			controlActionsWeb = new ControlActions(driver);
			controlActionsWeb.getUrl(pcAppTenantURL);
			webLoginPage = new LoginPage(driver);		

			if(!webLoginPage.performLogin(newUserName,tempPassword,newUserPassword)) {
				TCGFailureMsg = "Could NOT login to application";
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}
		}catch (Exception e) {
			logError("Failed to perorm login with new password - "+e.getMessage());	
		}finally {
			driver.close();
		}

		reLogin = loginScreen.reLogin(newUserName, newUserPassword);
		TestValidation.IsTrue(reLogin,
				"Log out & logged in in Online Mode",
				"COULD not login in Online mode");	
	}

	@AfterClass(alwaysRun = true)
	public void closeBrowser() throws InterruptedException {
		PCAppDriver.close();
	}

}
