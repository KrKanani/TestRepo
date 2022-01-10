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
import com.project.safetychain.api.models.support.Support.Compliance;
import com.project.safetychain.api.models.support.Support.DPT_FIELD_TYPES;
import com.project.safetychain.api.models.support.Support.DPT_FORM_TYPES;
import com.project.safetychain.api.models.support.Support.Element_Types;
import com.project.safetychain.api.models.support.Support.FormFieldParams;
import com.project.safetychain.api.models.support.Support.FormFieldParamsCompliance;
import com.project.safetychain.api.models.support.Support.FormParams;
import com.project.safetychain.api.models.support.Support.IDENTIFIER_TYPES;
import com.project.safetychain.api.models.support.Support.TaskParams;
import com.project.safetychain.api.models.support.Support.UserParams;
import com.project.safetychain.pcapp.pageobjects.CommonScreen;
import com.project.safetychain.pcapp.pageobjects.FormViewScreen;
import com.project.safetychain.pcapp.pageobjects.FormsScreen;
import com.project.safetychain.pcapp.pageobjects.InboxScreen;
import com.project.safetychain.pcapp.pageobjects.LoginScreen;
import com.project.safetychain.pcapp.pageobjects.SavedScreen;
import com.project.safetychain.pcapp.pageobjects.FormViewScreen.FormDetailsPC;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage;
import com.project.safetychain.webapp.pageobjects.HomePage;
import com.project.safetychain.webapp.pageobjects.InboxPage;
import com.project.safetychain.webapp.pageobjects.LoginPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.COLUMNHEADER;
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

public class TCG_REG_Saved_SavedFormFlow1 extends TestBase{

	ApiUtils apiUtils = null;

	DateTime dateTimeDetails;

	public static String locationCategoryValue1;
	public static String locationInstanceValue1;
	public static String resourceCategoryType1 = FORMRESOURCETYPES.ITEMS;
	public static String resourceCategoryValue1;
	public static String resourceInstanceValue1, resourceInstanceValue2;

	public static String formType1 = FORMTYPES.CHECK;
	public static String checkFormName;

	public static String formType2 = FORMTYPES.QUESTIONNAIRE;
	public static String questionnaireFormName;

	public static String formType3 = FORMTYPES.AUDIT;
	public static String auditFormName;

	public static String newWGName;
	public static String task1, task2;
	public static String checkSavedTask1, auditSavedTask1, questionnaireSavedTask1;

	public static String newUserName;
	public static String newUserPassword;
	public static String userPin;

	String locInstId, ResInstId1, ResInstId2;

	String TextFieldName = "Text 1",
			ParagraphFieldName = "Para 1",
			SelectOneFieldName = "SO 1",
			SelectMultipleFieldName = "SM 1",
			NumericFieldName1 = "Num 1",
			NumericFieldName2 = "Num 2",
			DateFieldName = "DT 1",
			TimeFieldName = "TM 1",
			DateTimeFieldName = "DTM 1";

	String AggSrcNumericFieldName1 = "Agg Num 1",
			AggSrcNumericFieldName2 = "Agg Num 2",
			AggregateFieldName = "Agg 1",
			IdentifierFieldName = "IDN 1",
			PropertyFieldName = "Num 2";

	List<String> selectOneMultipleFieldOptions = Arrays.asList("1","2","3","4");

	public static String fieldMin = "1", fieldTar = "2", fieldMax = "3", fieldUOM = "KG";

	String SectionName = "Section 1", GroupName = "Group 1";


	ControlActions_PCApp controlActionsPC;
	CommonScreen commonScreen;
	LoginScreen loginScreen;
	InboxScreen inboxScreen;
	FormsScreen formsScreen;
	SavedScreen savedScreen;
	FormViewScreen formview; 
	FormDetailsPC formDetailsPC;

	LoginPage webLoginPage;
	HomePage webHomePage;
	FSQABrowserPage webfsqaFormsPage;
	InboxPage webInboxPage;
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
		resourceInstanceValue2 = CommonMethods.dynamicText("PC_SaveRInst2");

		checkFormName = CommonMethods.dynamicText("PC_SaveForm1");
		questionnaireFormName = CommonMethods.dynamicText("PC_SaveForm2");
		auditFormName = CommonMethods.dynamicText("PC_SaveForm3");

		task1 = CommonMethods.dynamicText("PC_SaveTask1");
		task2 = CommonMethods.dynamicText("PC_SaveTask2");
		checkSavedTask1 = CommonMethods.dynamicText("PC_CheckSaveTask1");
		auditSavedTask1 = CommonMethods.dynamicText("PC_AuditSaveTask1");
		questionnaireSavedTask1 = CommonMethods.dynamicText("PC_QuestSaveTask1");

		newUserName =  CommonMethods.dynamicText("PC_SaveFormFlowUser");
		newUserPassword = "Test";
		newWGName =  CommonMethods.dynamicText("PC_SaveFormFlowFlowWG");

		HashMap<String, List<String>> resourceCatMap = new HashMap<String, List<String>>();
		resourceCatMap.put(resourceCategoryValue1, Arrays.asList(resourceInstanceValue1, resourceInstanceValue2));

		HashMap<String, List<String>> LocationMap = new HashMap<String, List<String>>();
		LocationMap.put(locationCategoryValue1, Arrays.asList(locationInstanceValue1));

		List<String> userList = Arrays.asList(pcAppUsername);

		HashMap<String, String> locResMap = formCreationWrapper.Resource_Location_CreationWrapper(resourceCatMap,
				LocationMap, true, userList, resourceCategoryType1);

		locInstId = locResMap.get(locationInstanceValue1);
		ResInstId1 = locResMap.get(resourceInstanceValue1);
		ResInstId2 = locResMap.get(resourceInstanceValue2);

		FormFieldParams FreeTextField = new FormFieldParams();
		FreeTextField.DPTFieldType = DPT_FIELD_TYPES.FREE_TEXT;
		FreeTextField.Name = TextFieldName;

		FormFieldParams ParagraphField = new FormFieldParams();
		ParagraphField.DPTFieldType = DPT_FIELD_TYPES.PARAGRAPH_TEXT;
		ParagraphField.Name = ParagraphFieldName;

		FormFieldParams SelectOneField = new FormFieldParams();
		SelectOneField.DPTFieldType = DPT_FIELD_TYPES.SELECT_ONE;
		SelectOneField.Name = SelectOneFieldName;
		SelectOneField.SelectOptions = selectOneMultipleFieldOptions;

		FormFieldParams SelectMultipleField = new FormFieldParams();
		SelectMultipleField.DPTFieldType = DPT_FIELD_TYPES.SELECT_MULTIPLE;
		SelectMultipleField.SelectOptions = selectOneMultipleFieldOptions;
		SelectMultipleField.Name = SelectMultipleFieldName;

		FormFieldParams NumericField1 = new FormFieldParams();
		NumericField1.DPTFieldType = DPT_FIELD_TYPES.NUMERIC;
		NumericField1.Name = NumericFieldName1;

		FormFieldParams DateField = new FormFieldParams();
		DateField.DPTFieldType = DPT_FIELD_TYPES.DATE;
		DateField.Name = DateFieldName;

		FormFieldParams TimeField = new FormFieldParams();
		TimeField.DPTFieldType = DPT_FIELD_TYPES.TIME;
		TimeField.Name = TimeFieldName;

		FormFieldParams DateTimeField = new FormFieldParams();
		DateTimeField.DPTFieldType = DPT_FIELD_TYPES.DATE_TIME;
		DateTimeField.Name = DateTimeFieldName;

		String formId = apiUtils.getUUID();
		String checkFormId = formId;
		FormParams fp1 = new FormParams();
		fp1.FormId = formId;
		fp1.type = DPT_FORM_TYPES.CHECK;
		fp1.ResourceType = resourceCategoryType1;
		fp1.ResourceCategoryNm = resourceCategoryValue1;
		fp1.ItemsResources = resourceCatMap;
		fp1.FormName = checkFormName;
		fp1.formElements = Arrays.asList(FreeTextField, ParagraphField, SelectOneField, SelectMultipleField, NumericField1, DateField, TimeField, DateTimeField);

		boolean formcreation = false;

		try {
			formCreationWrapper.API_Wrapper_Forms(fp1);
			formcreation = true;
			logInfo("'"+checkFormName+"' Form is created");
		} catch (InterruptedException e) {
			logError("Error while '"+checkFormName+"' form creation");
			formcreation = false;
		}
		TestValidation.IsTrue(formcreation, 
				"CREATED form - " + checkFormName, 
				"Could NOT create form - " + checkFormName);

		FormFieldParams AGGNumericField1 = new FormFieldParams();
		AGGNumericField1.DPTFieldType = DPT_FIELD_TYPES.NUMERIC;
		AGGNumericField1.Name = AggSrcNumericFieldName1;

		FormFieldParams AGGNumericField2 = new FormFieldParams();
		AGGNumericField2.DPTFieldType = DPT_FIELD_TYPES.NUMERIC;
		AGGNumericField2.Name = AggSrcNumericFieldName2;

		FormFieldParams NumericField2 = new FormFieldParams();
		NumericField2.DPTFieldType = DPT_FIELD_TYPES.NUMERIC;
		NumericField2.Name = PropertyFieldName;
		NumericField2.ShowMinMax = "true";
		NumericField2.ShowTarget = "true";
		NumericField2.ShowHint = "true";
		NumericField2.Hint = "PC App Automation field";
		NumericField2.AllowAttachments = "true";
		NumericField2.AllowComments = "true";
		NumericField2.AllowCorrection = "true"; 
		NumericField2.RepeatField  = "true";

		HashMap<String, Compliance> complianceValuesMap = new HashMap<String, Compliance>();
		Compliance compliance = new Compliance();
		compliance.fieldType = DPT_FIELD_TYPES.NUMERIC;
		compliance.Name = PropertyFieldName;
		compliance.Min = fieldMin;
		compliance.Target = fieldTar;
		compliance.Max = fieldMax;
		compliance.UOM = fieldUOM;
		compliance.IsDefault = "true";

		complianceValuesMap.put("Default", compliance);

		String resource = resourceCategoryType1+" > " + resourceCategoryValue1 + " > " + resourceInstanceValue1;
		complianceValuesMap.put(resource, compliance);

		resource = resourceCategoryType1+" > " + resourceCategoryValue1 + " > " + resourceInstanceValue2;
		complianceValuesMap.put(resource, compliance);

		FormFieldParamsCompliance ffpc = new FormFieldParamsCompliance();
		ffpc.fieldNames = Arrays.asList(PropertyFieldName);
		ffpc.complianceList = complianceValuesMap;

		FormFieldParams IdentifierField1 = new FormFieldParams();
		IdentifierField1.DPTFieldType = DPT_FIELD_TYPES.IDENTIFIER;
		IdentifierField1.identifierId = IDENTIFIER_TYPES.FREE_TEXT;
		IdentifierField1.Name = IdentifierFieldName;

		FormFieldParams AggregateField1 = new FormFieldParams();
		AggregateField1.DPTFieldType = DPT_FIELD_TYPES.AGGREGATE;
		AggregateField1.Name = AggregateFieldName;
		AggregateField1.SelectedFunction = "Sum Range";
		AggregateField1.SelectedSourceCollection = Arrays.asList(AggSrcNumericFieldName1, AggSrcNumericFieldName2);
		AggregateField1.AGG_MIN = "1";
		AggregateField1.AGG_MAX = "4";

		formId = apiUtils.getUUID();
		String questFormId = formId;
		FormParams fp2 = new FormParams();
		fp2.FormId = formId;
		fp2.FormName = questionnaireFormName;
		fp2.type = DPT_FORM_TYPES.QUESTIONNAIRE;
		fp2.ResourceType =resourceCategoryType1;
		fp2.ResourceCategoryNm = resourceCategoryValue1;
		fp2.ItemsResources = resourceCatMap;

		fp2.formElements = Arrays.asList(AGGNumericField1, AGGNumericField2, NumericField2, IdentifierField1, AggregateField1);

		try {
			formCreationWrapper.API_Wrapper_Forms(fp2);
			formCreationWrapper.API_Wrapper_Forms_Compliance(fp2, ffpc);
			formcreation = true;
			logInfo("'"+questionnaireFormName+"' Form is created");
		} catch (InterruptedException e) {
			logError("Error while '"+questionnaireFormName+"' form creation");
			formcreation = false;
		}

		TestValidation.IsTrue(formcreation, 
				"CREATED form - " + questionnaireFormName, 
				"Could NOT create form - " + questionnaireFormName);

		Element_Types Section1 = new Element_Types();
		Section1.ElementType = DPT_FIELD_TYPES.SECTION;
		Section1.Name = SectionName;

		NumericField1 = new FormFieldParams();
		NumericField1.DPTFieldType = DPT_FIELD_TYPES.NUMERIC;
		NumericField1.Name = NumericFieldName1;

		SelectOneField = new FormFieldParams();
		SelectOneField.DPTFieldType = DPT_FIELD_TYPES.SELECT_ONE;
		SelectOneField.Name = SelectOneFieldName;
		SelectOneField.QuestionWeight = "200";
		SelectOneField.SelectOptions = Arrays.asList("1,100","2,200");

		Section1.formFieldParams = Arrays.asList(NumericField1, SelectOneField);

		Element_Types fieldGroup1 = new Element_Types();
		fieldGroup1.ElementType = DPT_FIELD_TYPES.FIELD_GROUP;
		fieldGroup1.Name = GroupName;

		SelectMultipleField = new FormFieldParams();
		SelectMultipleField.DPTFieldType = DPT_FIELD_TYPES.SELECT_MULTIPLE;
		SelectMultipleField.SelectOptions = Arrays.asList("1,100","2,200");
		SelectMultipleField.Name = SelectMultipleFieldName;
		SelectMultipleField.QuestionWeight = "300";

		NumericField2 = new FormFieldParams();
		NumericField2.DPTFieldType = DPT_FIELD_TYPES.NUMERIC;
		NumericField2.Name = NumericFieldName2;

		fieldGroup1.formFieldParams = Arrays.asList(SelectMultipleField, NumericField2);

		Section1.FieldGroupParams = Arrays.asList(fieldGroup1);

		formId = apiUtils.getUUID();
		String audittFormId = formId;

		FormParams fp3 = new FormParams();
		fp3.FormId = audittFormId;
		fp3.FormName = auditFormName;
		fp3.type = DPT_FORM_TYPES.AUDIT;
		fp3.ResourceType = resourceCategoryType1;
		fp3.ResourceCategoryNm = resourceCategoryValue1;
		fp3.ItemsResources = resourceCatMap;
		fp3.SectionElements = Arrays.asList(Section1);

		try {
			formCreationWrapper.API_Wrapper_Forms(fp3);
			formcreation = true;
			logInfo("'"+auditFormName+"' Form is created");
		} catch (InterruptedException e) {
			logError("Error while '"+auditFormName+"' form creation");
			formcreation = false;
		}
		TestValidation.IsTrue(formcreation, 
				"CREATED form - " + auditFormName, 
				"Could NOT create form - " + auditFormName);

		UserParams userDetails = new UserParams();
		userDetails.Username = newUserName;
		userDetails.ClearPassword = GenericPassword;
		userDetails.FirstName = "UWP LOGIN";
		userDetails.LastName = "User";
		userDetails.Email = "saveformflowuser.pcapp@s.com";
		userDetails.TimeZone = "U.S. Pacific";
		userDetails.LocationCat = locationCategoryValue1;
		userDetails.LocationNmIds = Arrays.asList(locationInstanceValue1);
		userDetails.Roles = Arrays.asList("SuperAdmin");

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


		TaskParams tpAuditForm = new TaskParams();
		tpAuditForm.FormId = audittFormId;
		tpAuditForm.FormName = auditFormName;
		tpAuditForm.LocationInstanceNm = locationInstanceValue1;
		tpAuditForm.ResourceInstanceNm = resourceInstanceValue1;
		tpAuditForm.WorkGroupName = newWGName;
		tpAuditForm.LocationInstanceId = locInstId;
		tpAuditForm.ResourceInstanceId = ResInstId1;
		tpAuditForm.UserName = newUserName;

		TaskParams tpCheckForm = new TaskParams();
		tpCheckForm.FormId = checkFormId;
		tpCheckForm.FormName = checkFormName;
		tpCheckForm.LocationInstanceNm = locationInstanceValue1;
		tpCheckForm.ResourceInstanceNm = resourceInstanceValue1;
		tpCheckForm.WorkGroupName = newWGName;
		tpCheckForm.LocationInstanceId = locInstId;
		tpCheckForm.ResourceInstanceId = ResInstId1;
		tpCheckForm.UserName = newUserName;

		TaskParams tpQuestForm = new TaskParams();
		tpQuestForm.FormId = questFormId;
		tpQuestForm.FormName = questionnaireFormName;
		tpQuestForm.LocationInstanceNm = locationInstanceValue1;
		tpQuestForm.ResourceInstanceNm = resourceInstanceValue1;
		tpQuestForm.WorkGroupName = newWGName;
		tpQuestForm.LocationInstanceId = locInstId;
		tpQuestForm.ResourceInstanceId = ResInstId1;
		tpQuestForm.UserName = newUserName;

		boolean taskCreationStatus = true;
		try {

			taskCreationWrapper.create_Link_workGroup_Wrapper(tpAuditForm);

			tpAuditForm.DueBy = "";
			tpAuditForm.TaskName = task1;
			taskCreationWrapper.create_Task_Wrapper(tpAuditForm);

			tpAuditForm.TaskName = task2;
			taskCreationWrapper.create_Task_Wrapper(tpAuditForm);

			tpAuditForm.TaskName = auditSavedTask1;
			taskCreationWrapper.create_Task_Wrapper(tpAuditForm);

			tpQuestForm.DueBy = "";
			tpQuestForm.TaskName = questionnaireSavedTask1;
			taskCreationWrapper.create_Task_Wrapper(tpQuestForm);

			tpCheckForm.DueBy = "";
			tpCheckForm.TaskName = checkSavedTask1;
			taskCreationWrapper.create_Task_Wrapper(tpCheckForm);

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

	@Test(description="Saved || Verify the Saved Form count", priority=0)
	public void TestCase_33592() {

		byte savedFormCount = 3;

		formDetailsPC = new FormDetailsPC();
		formDetailsPC.locationName = locationInstanceValue1;
		formDetailsPC.resourceName = resourceInstanceValue1;

		FormsScreen selectFormsTab  = commonScreen.selectForms();
		TestValidation.IsFalse(selectFormsTab.error,
				"OPENED 'Forms' Tab", 
				"COULD not open 'Forms' Tab");

		boolean openForm = selectFormsTab.selectOpenForm(checkFormName);
		TestValidation.IsTrue(openForm, 
				"SELECTED & opened form - "+checkFormName, 
				"FAILED open form - "+checkFormName);

		boolean selectLocationResource = formview.selectLocationResource(locationInstanceValue1, resourceInstanceValue1);
		TestValidation.IsTrue(selectLocationResource, 
				"SELECTED Location & Resource", 
				"FAILED to select Location & Resource");

		boolean saveForm = formview.saveForm();
		TestValidation.IsTrue(saveForm, 
				"SAVED form - "+checkFormName, 
				"FAILED to save the form - "+checkFormName);

		openForm = selectFormsTab.selectOpenForm(questionnaireFormName);
		TestValidation.IsTrue(openForm, 
				"SELECTED & opened form - "+questionnaireFormName, 
				"FAILED open form - "+questionnaireFormName);

		selectLocationResource = formview.selectLocationResource(locationInstanceValue1, resourceInstanceValue1);
		TestValidation.IsTrue(selectLocationResource, 
				"SELECTED Location & Resource", 
				"FAILED to select Location & Resource");

		saveForm = formview.saveForm();
		TestValidation.IsTrue(saveForm, 
				"SAVED form - "+questionnaireFormName, 
				"FAILED to save the form - "+questionnaireFormName);

		openForm = selectFormsTab.selectOpenForm(auditFormName);
		TestValidation.IsTrue(openForm, 
				"SELECTED & opened form - "+auditFormName, 
				"FAILED open form - "+auditFormName);

		selectLocationResource = formview.selectLocationResource(locationInstanceValue1, resourceInstanceValue1);
		TestValidation.IsTrue(selectLocationResource, 
				"SELECTED Location & Resource", 
				"FAILED to select Location & Resource");

		saveForm = formview.saveForm();
		TestValidation.IsTrue(saveForm, 
				"SAVED form - "+auditFormName, 
				"FAILED to save the form - "+auditFormName);

		savedScreen = commonScreen.selectSaved();
		TestValidation.IsFalse(savedScreen.error,
				"OPENED Saved Tab", 
				"COULD not open Saved Tab");

		boolean verifySavedFormCount = savedScreen.verifySavedCount(savedFormCount);
		TestValidation.IsTrue(verifySavedFormCount, 
				"VERIFIED saved form count - "+savedFormCount, 
				"FAILED to verify saved form count - "+savedFormCount);
	}

	@Test(description="Saved || Verify the results after searching a saved form", dependsOnMethods = { "TestCase_33592" })
	public void TestCase_33593() {

		savedScreen = commonScreen.selectSaved();
		TestValidation.IsFalse(savedScreen.error,
				"OPENED Saved Tab", 
				"COULD not open Saved Tab");

		boolean verifySavedFormAvailability = savedScreen.verifySavedFormAvailability(checkFormName);
		TestValidation.IsTrue(verifySavedFormAvailability, 
				"VERIFIED saved form availability after searching form - "+checkFormName, 
				"FAILED to verify saved form availability after searching form - "+checkFormName);

		boolean deleteSavedForm = savedScreen.deleteUnopenendForm(checkFormName);
		TestValidation.IsTrue(deleteSavedForm, 
				"Saved form deleted from saved screen", 
				"FAILED to delete saved form");

		verifySavedFormAvailability = savedScreen.verifySavedFormAvailability(questionnaireFormName);
		TestValidation.IsTrue(verifySavedFormAvailability, 
				"VERIFIED saved form availability after searching form - "+questionnaireFormName, 
				"FAILED to verify saved form availability after searching form - "+questionnaireFormName);

		deleteSavedForm = savedScreen.deleteUnopenendForm(questionnaireFormName);
		TestValidation.IsTrue(deleteSavedForm, 
				"Saved form deleted from saved screen", 
				"FAILED to delete saved form");

		verifySavedFormAvailability = savedScreen.verifySavedFormAvailability(auditFormName);
		TestValidation.IsTrue(verifySavedFormAvailability, 
				"VERIFIED saved form availability after searching form - "+auditFormName, 
				"FAILED to verify saved form availability after searching form - "+auditFormName);

		deleteSavedForm = savedScreen.deleteUnopenendForm(auditFormName);
		TestValidation.IsTrue(deleteSavedForm, 
				"Saved form deleted from saved screen", 
				"FAILED to delete saved form");

	}

	@Test(description="PC || Saved || Deletion of Questionnaire saved forms")
	public void TestCase_40347() {
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


	@Test(description="PC || Saved || Submission of Questionnaire saved forms")
	public void TestCase_40348() {

		HashMap<String, List<String>> allFields = new LinkedHashMap<String, List<String>>();
		allFields.put(AggSrcNumericFieldName1,Arrays.asList("4"));
		allFields.put(AggSrcNumericFieldName2,Arrays.asList("4"));
		allFields.put(NumericFieldName2,Arrays.asList("2"));
		allFields.put(IdentifierFieldName,Arrays.asList("1234"));

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

	@Test(description="PC || Forms || Verify saved Questionnaire forms on PC App")
	public void TestCase_40342() {

		formDetailsPC = new FormDetailsPC();
		formDetailsPC.locationName = locationInstanceValue1;
		formDetailsPC.resourceName = resourceInstanceValue1;

		FormsScreen selectFormsTab  = commonScreen.selectForms();
		TestValidation.IsFalse(selectFormsTab.error,
				"OPENED 'Forms' Tab", 
				"COULD not open 'Forms' Tab");

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

		boolean	verifySavedFormAvailability = savedScreen.verifySavedFormAvailability(questionnaireFormName);
		TestValidation.IsTrue(verifySavedFormAvailability, 
				"VERIFIED saved form availability after searching form - "+questionnaireFormName, 
				"FAILED to verify saved form availability after searching form - "+questionnaireFormName);

		boolean saveformFromWeb, verifySavedFormAvailabityOnWeb, deleteSavedFormFromWeb;
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

			verifySavedFormAvailabityOnWeb = webfsqaFormsPage.verifySavedFormAvailability(questionnaireFormName);
			TestValidation.IsTrue(verifySavedFormAvailabityOnWeb, 
					"Saved form - '"+questionnaireFormName+"' IS AVAILABLE in 'FSQA Browser - Forms Tab' - 'Saved Forms'", 
					"Saved form - '"+questionnaireFormName+"' IS NOT AVAILABLE in 'FSQA Browser - Forms Tab' - 'Saved Forms'");

			deleteSavedFormFromWeb = webfsqaFormsPage.deleteSavedForm(questionnaireFormName);
			TestValidation.IsTrue(deleteSavedFormFromWeb, 
					"Saved form - '"+questionnaireFormName+"' IS DELETED", 
					"Saved form - '"+questionnaireFormName+"' IS NOT DELETED");

			webfsqaFormsPage.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, questionnaireFormName);

			FormDetails formDetailsWeb = new FormDetails();
			HashMap<String, List<String>> formFieldDetailsWeb = new HashMap<String, List<String>>();
			//			formFieldDetailsWeb.put(AggSrcNumericFieldName, Arrays.asList("1","2"));
			//			formFieldDetailsWeb.put(PropertyFieldName, Arrays.asList("4"));
			//			formFieldDetailsWeb.put(IdentifierFieldName, Arrays.asList("1234"));		

			formDetailsWeb.fieldDetails = formFieldDetailsWeb;
			formDetailsWeb.resourceName = resourceInstanceValue1;
			formDetailsWeb.locationName = locationInstanceValue1;
			formDetailsWeb.isSubmit = false;

			saveformFromWeb = formOperationsWeb.submitData(formDetailsWeb);
			TestValidation.IsTrue(saveformFromWeb, 
					"SAVED FORM - '"+questionnaireFormName+"' from Web App", 
					"UNABLE TO SAVE FORM - '"+questionnaireFormName+"' from Web App");

			threadsleep(5000);

			verifySavedFormAvailabityOnWeb = webfsqaFormsPage.verifySavedFormAvailability(questionnaireFormName);
			TestValidation.IsTrue(verifySavedFormAvailabityOnWeb, 
					"Saved form - '"+questionnaireFormName+"' IS AVAILABLE in 'FSQA Browser - Forms Tab' - 'Saved Forms'", 
					"Saved form - '"+questionnaireFormName+"' IS NOT AVAILABLE in 'FSQA Browser - Forms Tab' - 'Saved Forms'");

		} catch (Exception e) {
			saveformFromWeb = false;
			TestValidation.IsTrue(saveformFromWeb, 
					"SAVED FORM - '"+questionnaireFormName+"' from Web App", 
					"FAILED TO SAVE FORM - '"+questionnaireFormName+"' from Web App");
		}

		savedScreen = commonScreen.selectSaved();
		TestValidation.IsFalse(savedScreen.error,
				"OPENED Saved Tab", 
				"COULD not open Saved Tab");

		boolean	refreshSavedForms = savedScreen.refreshSavedForm();
		TestValidation.IsTrue(refreshSavedForms, 
				"REFRESHED saved form", 
				"FAILED to refresh saved form");

		verifySavedFormAvailability = savedScreen.verifySavedFormAvailability(questionnaireFormName);
		TestValidation.IsTrue(verifySavedFormAvailability, 
				"VERIFIED saved form availability after searching form - "+questionnaireFormName, 
				"FAILED to verify saved form availability after searching form - "+questionnaireFormName);

		boolean deleteForm = savedScreen.deleteForm(questionnaireFormName);
		TestValidation.IsTrue(deleteForm, 
				"DELETED saved form - "+questionnaireFormName, 
				"FAILED to delete saved form - "+questionnaireFormName);

	}

	@Test(description="PC || Saved || Saving of Questionnaire saved forms",  dependsOnMethods = { "TestCase_40342" })
	public void TestCase_40346() {

		formDetailsPC = new FormDetailsPC();
		formDetailsPC.locationName = locationInstanceValue1;
		formDetailsPC.resourceName = resourceInstanceValue1;

		FormsScreen selectFormsTab  = commonScreen.selectForms();
		TestValidation.IsFalse(selectFormsTab.error,
				"OPENED 'Forms' Tab", 
				"COULD not open 'Forms' Tab");

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

		boolean openSavedForm = savedScreen.selectOpenForm(questionnaireFormName);
		TestValidation.IsTrue(openSavedForm, 
				"SELECTED & opened saved form - "+questionnaireFormName, 
				"FAILED to open saved form - "+questionnaireFormName);

		saveForm = savedScreen.saveForm();
		TestValidation.IsTrue(saveForm, 
				"SAVED form - "+questionnaireFormName, 
				"FAILED to save the form - "+questionnaireFormName);

		boolean	verifySavedFormAvailability = savedScreen.verifySavedFormAvailability(questionnaireFormName);
		TestValidation.IsTrue(verifySavedFormAvailability, 
				"VERIFIED saved form availability after searching form - "+questionnaireFormName, 
				"FAILED to verify saved form availability after searching form - "+questionnaireFormName);

		boolean verifySavedFormAvailabityOnWeb;
		try {

			webfsqaFormsPage.clickFSQABrowserMenu();
			webfsqaFormsPage.selectResourceDropDownandNavigate(FSQARESOURCE.ITEMS,FSQATAB.FORMS);

			verifySavedFormAvailabityOnWeb = webfsqaFormsPage.verifySavedFormAvailability(questionnaireFormName);
			TestValidation.IsTrue(verifySavedFormAvailabityOnWeb, 
					"Saved form - '"+questionnaireFormName+"' IS AVAILABLE in 'FSQA Browser - Forms Tab' - 'Saved Forms'", 
					"Saved form - '"+questionnaireFormName+"' IS NOT AVAILABLE in 'FSQA Browser - Forms Tab' - 'Saved Forms'");

		} catch (Exception e) {
			verifySavedFormAvailabityOnWeb = false;
			TestValidation.IsTrue(verifySavedFormAvailabityOnWeb, 
					"Saved form - '"+questionnaireFormName+"' IS AVAILABLE in 'FSQA Browser - Forms Tab' - 'Saved Forms'", 
					"FAILED TO VERIFY Saved form - '"+questionnaireFormName+"' availability in 'FSQA Browser - Forms Tab' - 'Saved Forms'");
		}

		boolean deleteForm = savedScreen.deleteForm(questionnaireFormName);
		TestValidation.IsTrue(deleteForm, 
				"DELETED saved form - "+questionnaireFormName, 
				"FAILED to delete saved form - "+questionnaireFormName);
	}

	@Test(description="PC || Saved || Submission of Check & Audit saved forms")
	public void TestCase_40339() {

		formDetailsPC = new FormDetailsPC();
		formDetailsPC.locationName = locationInstanceValue1;
		formDetailsPC.resourceName = resourceInstanceValue1;
		HashMap<String, List<String>> allFields;

		formDetailsPC.selectLocationResource = false;

		FormsScreen selectFormsTab  = commonScreen.selectForms();
		TestValidation.IsFalse(selectFormsTab.error,
				"OPENED 'Forms' Tab", 
				"COULD not open 'Forms' Tab");

		boolean openForm = selectFormsTab.selectOpenForm(checkFormName);
		TestValidation.IsTrue(openForm, 
				"SELECTED & opened form - "+checkFormName, 
				"FAILED open form - "+checkFormName);

		boolean selectLocationResource = formview.selectLocationResource(locationInstanceValue1, resourceInstanceValue1);
		TestValidation.IsTrue(selectLocationResource, 
				"SELECTED Location & Resource", 
				"FAILED to select Location & Resource");

		boolean saveForm = formview.saveForm();
		TestValidation.IsTrue(saveForm, 
				"SAVED form - "+checkFormName, 
				"FAILED to save the form - "+checkFormName);

		savedScreen = commonScreen.selectSaved();
		TestValidation.IsFalse(savedScreen.error,
				"OPENED Saved Tab", 
				"COULD not open Saved Tab");

		allFields = new LinkedHashMap<String, List<String>>();
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
		allFields.put(SelectMultipleFieldName,Arrays.asList(selectMultipleOptions));
		allFields.put(NumericFieldName1,Arrays.asList("8"));
		allFields.put(DateFieldName,Arrays.asList(controlActionsPC.getDayDate(0)));
		allFields.put(TimeFieldName,Arrays.asList("NA"));
		allFields.put(DateTimeFieldName,Arrays.asList("NA"));

		formDetailsPC.fieldDetails = allFields;

		openForm = savedScreen.selectOpenForm(checkFormName);
		TestValidation.IsTrue(openForm, 
				"SELECTED & opened form - "+checkFormName, 
				"FAILED open form - "+checkFormName);

		boolean submitData = formview.fillDataInForm1(formDetailsPC);
		TestValidation.IsTrue(submitData, 
				"SUBMITTED data for form - "+checkFormName, 
				"FAILED to submit data for form - "+checkFormName);

		selectFormsTab  = commonScreen.selectForms();
		TestValidation.IsFalse(selectFormsTab.error,
				"OPENED 'Forms' Tab", 
				"COULD not open 'Forms' Tab");

		openForm = selectFormsTab.selectOpenForm(auditFormName);
		TestValidation.IsTrue(openForm, 
				"SELECTED & opened form - "+auditFormName, 
				"FAILED open form - "+auditFormName);

		selectLocationResource = formview.selectLocationResource(locationInstanceValue1, resourceInstanceValue1);
		TestValidation.IsTrue(selectLocationResource, 
				"SELECTED Location & Resource", 
				"FAILED to select Location & Resource");

		saveForm = formview.saveForm();
		TestValidation.IsTrue(saveForm, 
				"SAVED form - "+auditFormName, 
				"FAILED to save the form - "+auditFormName);

		savedScreen = commonScreen.selectSaved();
		TestValidation.IsFalse(savedScreen.error,
				"OPENED Saved Tab", 
				"COULD not open Saved Tab");

		openForm = savedScreen.selectOpenForm(auditFormName);
		TestValidation.IsTrue(openForm, 
				"SELECTED & opened form - "+auditFormName, 
				"FAILED open form - "+auditFormName);

		allFields = new LinkedHashMap<String, List<String>>();
		allFields.put(NumericFieldName1,Arrays.asList("2"));
		allFields.put(SelectOneFieldName,Arrays.asList("2"));
		allFields.put(SelectMultipleFieldName,Arrays.asList("1,2"));
		allFields.put(NumericFieldName2,Arrays.asList("4"));

		formDetailsPC.fieldDetails = allFields;

		submitData = formview.fillDataInForm1(formDetailsPC);
		TestValidation.IsTrue(submitData, 
				"SUBMITTED data for form - "+auditFormName, 
				"FAILED to submit data for form - "+auditFormName);
	}


	@Test(description="Verify that the all the saved form/task should be visible in Saved")
	public void TestCase_33589() {

		FormsScreen selectFormsTab  = commonScreen.selectForms();
		TestValidation.IsFalse(selectFormsTab.error,
				"OPENED 'Forms' Tab", 
				"COULD not open 'Forms' Tab");

		boolean openForm = selectFormsTab.selectOpenForm(checkFormName);
		TestValidation.IsTrue(openForm, 
				"SELECTED & opened form - "+checkFormName, 
				"FAILED open form - "+checkFormName);

		boolean selectLocationResource = formview.selectLocationResource(locationInstanceValue1, resourceInstanceValue1);
		TestValidation.IsTrue(selectLocationResource, 
				"SELECTED Location & Resource", 
				"FAILED to select Location & Resource");

		boolean saveForm = formview.saveForm();
		TestValidation.IsTrue(saveForm, 
				"SAVED form - "+checkFormName, 
				"FAILED to save the form - "+checkFormName);

		savedScreen = commonScreen.selectSaved();
		TestValidation.IsFalse(savedScreen.error,
				"OPENED Saved Tab", 
				"COULD not open Saved Tab");

		boolean verifySavedFormAvailability = savedScreen.verifySavedFormAvailability(checkFormName);
		TestValidation.IsTrue(verifySavedFormAvailability, 
				"VERIFIED saved form availability after searching form - "+checkFormName, 
				"FAILED to verify saved form availability after searching form - "+checkFormName);

		InboxScreen selectInboxTab  = commonScreen.selectInbox();
		TestValidation.IsFalse(selectInboxTab.error,
				"OPENED 'Inbox' Tab", 
				"COULD not open 'Inbox' Tab");

		boolean openTask = selectInboxTab.selectOpenTask(task1, false);
		TestValidation.IsTrue(openTask, 
				"SELECTED & opened task - "+task1, 
				"FAILED open task - "+task1);


		saveForm = formview.saveForm();
		TestValidation.IsTrue(saveForm, 
				"SAVED task - "+task1, 
				"FAILED to save the task - "+task1);

		savedScreen = commonScreen.selectSaved();
		TestValidation.IsFalse(savedScreen.error,
				"OPENED Saved Tab", 
				"COULD not open Saved Tab");

		verifySavedFormAvailability = savedScreen.verifySavedFormAvailability(auditFormName);
		TestValidation.IsTrue(verifySavedFormAvailability, 
				"VERIFIED saved form availability after searching form - "+auditFormName, 
				"FAILED to verify saved form availability after searching form - "+auditFormName);

	}


	@Test(description="PC || Saved || Saving of Check & Audit saved forms")
	public void TestCase_40337() {


		FormsScreen selectFormsTab  = commonScreen.selectForms();
		TestValidation.IsFalse(selectFormsTab.error,
				"OPENED 'Forms' Tab", 
				"COULD not open 'Forms' Tab");

		boolean openForm = selectFormsTab.selectOpenForm(checkFormName);
		TestValidation.IsTrue(openForm, 
				"SELECTED & opened form - "+checkFormName, 
				"FAILED open form - "+checkFormName);

		boolean selectLocationResource = formview.selectLocationResource(locationInstanceValue1, resourceInstanceValue1);
		TestValidation.IsTrue(selectLocationResource, 
				"SELECTED Location & Resource", 
				"FAILED to select Location & Resource");

		boolean saveForm = formview.saveForm();
		TestValidation.IsTrue(saveForm, 
				"SAVED form - "+checkFormName, 
				"FAILED to save the form - "+checkFormName);

		openForm = selectFormsTab.selectOpenForm(auditFormName);
		TestValidation.IsTrue(openForm, 
				"SELECTED & opened form - "+auditFormName, 
				"FAILED open form - "+auditFormName);

		selectLocationResource = formview.selectLocationResource(locationInstanceValue1, resourceInstanceValue1);
		TestValidation.IsTrue(selectLocationResource, 
				"SELECTED Location & Resource", 
				"FAILED to select Location & Resource");

		saveForm = formview.saveForm();
		TestValidation.IsTrue(saveForm, 
				"SAVED form - "+auditFormName, 
				"FAILED to save the form - "+auditFormName);

		savedScreen = commonScreen.selectSaved();
		TestValidation.IsFalse(savedScreen.error,
				"OPENED Saved Tab", 
				"COULD not open Saved Tab");

		boolean openSavedForm = savedScreen.selectOpenForm(checkFormName);
		TestValidation.IsTrue(openSavedForm, 
				"SELECTED & opened saved form - "+checkFormName, 
				"FAILED to open saved form - "+checkFormName);

		saveForm = savedScreen.saveForm();
		TestValidation.IsTrue(saveForm, 
				"SAVED form - "+checkFormName, 
				"FAILED to save the form - "+checkFormName);

		openSavedForm = savedScreen.selectOpenForm(auditFormName);
		TestValidation.IsTrue(openSavedForm, 
				"SELECTED & opened saved form - "+auditFormName, 
				"FAILED to open saved form - "+auditFormName);

		saveForm = savedScreen.saveForm();
		TestValidation.IsTrue(saveForm, 
				"SAVED form - "+auditFormName, 
				"FAILED to save the form - "+auditFormName);

		boolean	verifySavedFormAvailability = savedScreen.verifySavedFormAvailability(checkFormName);
		TestValidation.IsTrue(verifySavedFormAvailability, 
				"VERIFIED saved form availability after searching form - "+checkFormName, 
				"FAILED to verify saved form availability after searching form - "+checkFormName);

		verifySavedFormAvailability = savedScreen.verifySavedFormAvailability(auditFormName);
		TestValidation.IsTrue(verifySavedFormAvailability, 
				"VERIFIED saved form availability after searching form - "+auditFormName, 
				"FAILED to verify saved form availability after searching form - "+auditFormName);


		boolean verifySavedFormAvailabityOnWeb;
		try {

			driver = launchbrowser();
			webLoginPage = new LoginPage(driver);
			controlActionsWeb = new ControlActions(driver);

			controlActionsWeb.getUrl(pcAppTenantURL);
			webHomePage = webLoginPage.performLogin(newUserName, newUserPassword);
			webfsqaFormsPage = new FSQABrowserPage(driver);

			webfsqaFormsPage.clickFSQABrowserMenu();
			webfsqaFormsPage.selectResourceDropDownandNavigate(FSQARESOURCE.ITEMS,FSQATAB.FORMS);

			verifySavedFormAvailabityOnWeb = webfsqaFormsPage.verifySavedFormAvailability(checkFormName);
			TestValidation.IsTrue(verifySavedFormAvailabityOnWeb, 
					"Saved form - '"+checkFormName+"' IS AVAILABLE in 'FSQA Browser - Forms Tab' - 'Saved Forms'", 
					"Saved form - '"+checkFormName+"' IS NOT AVAILABLE in 'FSQA Browser - Forms Tab' - 'Saved Forms'");

			verifySavedFormAvailabityOnWeb = webfsqaFormsPage.verifySavedFormAvailability(auditFormName);
			TestValidation.IsTrue(verifySavedFormAvailabityOnWeb, 
					"Saved form - '"+auditFormName+"' IS AVAILABLE in 'FSQA Browser - Forms Tab' - 'Saved Forms'", 
					"Saved form - '"+auditFormName+"' IS NOT AVAILABLE in 'FSQA Browser - Forms Tab' - 'Saved Forms'");

		} catch (Exception e) {
			verifySavedFormAvailabityOnWeb = false;
			TestValidation.IsTrue(verifySavedFormAvailabityOnWeb, 
					"Saved forms ARE AVAILABLE in 'FSQA Browser - Forms Tab' - 'Saved Forms'", 
					"FAILED TO VERIFY Saved form availability in 'FSQA Browser - Forms Tab' - 'Saved Forms'");
		}

		boolean deleteForm = savedScreen.deleteUnopenendForm(checkFormName);
		TestValidation.IsTrue(deleteForm, 
				"DELETED saved form - "+checkFormName, 
				"FAILED to delete saved form - "+checkFormName);

		deleteForm = savedScreen.deleteUnopenendForm(auditFormName);
		TestValidation.IsTrue(deleteForm, 
				"DELETED saved form - "+auditFormName, 
				"FAILED to delete saved form - "+auditFormName);
	}


	@Test(description="PC || Saved || Deletion of Check & Audit saved forms")
	public void TestCase_40338() {

		FormsScreen selectFormsTab  = commonScreen.selectForms();
		TestValidation.IsFalse(selectFormsTab.error,
				"OPENED 'Forms' Tab", 
				"COULD not open 'Forms' Tab");

		boolean openForm = selectFormsTab.selectOpenForm(checkFormName);
		TestValidation.IsTrue(openForm, 
				"SELECTED & opened form - "+checkFormName, 
				"FAILED open form - "+checkFormName);

		boolean selectLocationResource = formview.selectLocationResource(locationInstanceValue1, resourceInstanceValue1);
		TestValidation.IsTrue(selectLocationResource, 
				"SELECTED Location & Resource", 
				"FAILED to select Location & Resource");

		boolean saveForm = formview.saveForm();
		TestValidation.IsTrue(saveForm, 
				"SAVED form - "+checkFormName, 
				"FAILED to save the form - "+checkFormName);

		openForm = selectFormsTab.selectOpenForm(auditFormName);
		TestValidation.IsTrue(openForm, 
				"SELECTED & opened form - "+auditFormName, 
				"FAILED open form - "+auditFormName);

		selectLocationResource = formview.selectLocationResource(locationInstanceValue1, resourceInstanceValue1);
		TestValidation.IsTrue(selectLocationResource, 
				"SELECTED Location & Resource", 
				"FAILED to select Location & Resource");

		saveForm = formview.saveForm();
		TestValidation.IsTrue(saveForm, 
				"SAVED form - "+auditFormName, 
				"FAILED to save the form - "+auditFormName);

		savedScreen = commonScreen.selectSaved();
		TestValidation.IsFalse(savedScreen.error,
				"OPENED Saved Tab", 
				"COULD not open Saved Tab");

		boolean deleteSavedForm = savedScreen.deleteUnopenendForm(checkFormName);
		TestValidation.IsTrue(deleteSavedForm, 
				"DELETED saved form - "+checkFormName, 
				"FAILED to delete saved form - "+checkFormName);

		deleteSavedForm = savedScreen.deleteUnopenendForm(auditFormName);
		TestValidation.IsTrue(deleteSavedForm, 
				"DELETED saved form - "+auditFormName, 
				"FAILED to delete saved form - "+auditFormName);

	}

	@Test(description="PC || Forms || Saving of Check & Audit forms from Web App & Verify in PC App")
	public void TestCase_40334() {
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

			webfsqaFormsPage.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, checkFormName);

			FormDetails formDetailsWeb = new FormDetails();

			formDetailsWeb.resourceName = resourceInstanceValue1;
			formDetailsWeb.locationName = locationInstanceValue1;
			formDetailsWeb.isSubmit = false;

			saveformFromWeb = formOperationsWeb.submitData(formDetailsWeb);
			TestValidation.IsTrue(saveformFromWeb, 
					"SAVED FORM - '"+checkFormName+"' from Web App", 
					"UNABLE TO SAVE FORM - '"+checkFormName+"' from Web App");

			webfsqaFormsPage.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, auditFormName);

			saveformFromWeb = formOperationsWeb.submitData(formDetailsWeb);
			TestValidation.IsTrue(saveformFromWeb, 
					"SAVED FORM - '"+auditFormName+"' from Web App", 
					"UNABLE TO SAVE FORM - '"+auditFormName+"' from Web App");


		} catch (Exception e) {
			saveformFromWeb = false;
			TestValidation.IsTrue(saveformFromWeb, 
					"SAVED FORMS from Web App", 
					"FAILED TO SAVE FORMS from Web App");
		}

		savedScreen = commonScreen.selectSaved();
		TestValidation.IsFalse(savedScreen.error,
				"OPENED Saved Tab", 
				"COULD not open Saved Tab");

		boolean	refreshSavedForms = savedScreen.refreshSavedForm();
		TestValidation.IsTrue(refreshSavedForms, 
				"REFRESHED saved form", 
				"FAILED to refresh saved form");

		boolean	verifySavedFormAvailability = savedScreen.verifySavedFormAvailability(checkFormName);
		TestValidation.IsTrue(verifySavedFormAvailability, 
				"VERIFIED saved form availability after searching form - "+checkFormName, 
				"FAILED to verify saved form availability after searching form - "+checkFormName);

		boolean deleteForm = savedScreen.deleteUnopenendForm(checkFormName);
		TestValidation.IsTrue(deleteForm, 
				"DELETED saved form - "+checkFormName, 
				"FAILED to delete saved form - "+checkFormName);

		verifySavedFormAvailability = savedScreen.verifySavedFormAvailability(auditFormName);
		TestValidation.IsTrue(verifySavedFormAvailability, 
				"VERIFIED saved form availability after searching form - "+auditFormName, 
				"FAILED to verify saved form availability after searching form - "+auditFormName);

		deleteForm = savedScreen.deleteUnopenendForm(auditFormName);
		TestValidation.IsTrue(deleteForm, 
				"DELETED saved form - "+auditFormName, 
				"FAILED to delete saved form - "+auditFormName);
	}

	@Test(description="PC || Inbox || Saving of Questionnaire form tasks", dependsOnMethods = { "TestCase_40334" })
	public void TestCase_40344() {

		InboxScreen selectInboxTab  = commonScreen.selectInbox();
		TestValidation.IsFalse(selectInboxTab.error,
				"OPENED Inbox Tab", 
				"COULD not open Inbox Tab");

		boolean openTask = selectInboxTab.selectOpenTask(questionnaireSavedTask1, false);
		TestValidation.IsTrue(openTask, 
				"SELECTED & opened task - "+questionnaireSavedTask1, 
				"FAILED open task - "+questionnaireSavedTask1);

		boolean saveForm = formview.saveForm();
		TestValidation.IsTrue(saveForm, 
				"SAVED task - "+questionnaireSavedTask1, 
				"FAILED to save the task - "+questionnaireSavedTask1);

		savedScreen = commonScreen.selectSaved();
		TestValidation.IsFalse(savedScreen.error,
				"OPENED Saved Tab", 
				"COULD not open Saved Tab");

		boolean verifyPC_SavedTaskOnWeb = false;
		try {

			webInboxPage = new InboxPage(driver);
			webInboxPage.clickInboxMenu();

			verifyPC_SavedTaskOnWeb = webInboxPage.verifySavedTaskStatus(questionnaireSavedTask1);
			TestValidation.IsTrue(verifyPC_SavedTaskOnWeb, 
					"SAVED Task is visible - '"+questionnaireSavedTask1+"' on Web App Inbox", 
					"UNABLE TO find saved task - '"+questionnaireSavedTask1+"' on Web App Inbox");

		}catch(Exception e) {
			verifyPC_SavedTaskOnWeb = false;
			TestValidation.IsTrue(verifyPC_SavedTaskOnWeb, 
					"SAVED Task is visible - '"+questionnaireSavedTask1+"' on Web App Inbox", 
					"UNABLE TO find saved task - '"+questionnaireSavedTask1+"' on Web App Inbox");
		}finally {
			driver.close();
		}

		boolean deleteForm = savedScreen.deleteUnopenendForm(questionnaireFormName);
		TestValidation.IsTrue(deleteForm, 
				"DELETED saved form - "+questionnaireFormName, 
				"FAILED to delete saved form - "+questionnaireFormName);
	}


	@Test(description="PC || Inbox || Saving of Check & Audit form tasks")
	public void TestCase_40336() {

		InboxScreen selectInboxTab  = commonScreen.selectInbox();
		TestValidation.IsFalse(selectInboxTab.error,
				"OPENED Inbox Tab", 
				"COULD not open Inbox Tab");

		boolean openTask = selectInboxTab.selectOpenTask(checkSavedTask1, false);
		TestValidation.IsTrue(openTask, 
				"SELECTED & opened task - "+checkSavedTask1, 
				"FAILED open task - "+checkSavedTask1);

		boolean saveForm = formview.saveForm();
		TestValidation.IsTrue(saveForm, 
				"SAVED 'Check' form task - "+checkSavedTask1, 
				"FAILED to save 'Check' form task - "+checkSavedTask1);

		openTask = selectInboxTab.selectOpenTask(auditSavedTask1, false);
		TestValidation.IsTrue(openTask, 
				"SELECTED & opened task - "+auditSavedTask1, 
				"FAILED open task - "+auditSavedTask1);

		saveForm = formview.saveForm();
		TestValidation.IsTrue(saveForm, 
				"SAVED 'Audit form task - "+auditSavedTask1, 
				"FAILED to save 'Audit' form task - "+auditSavedTask1);

		savedScreen = commonScreen.selectSaved();
		TestValidation.IsFalse(savedScreen.error,
				"OPENED Saved Tab", 
				"COULD not open Saved Tab");

		boolean verifyPC_SavedTaskOnWeb = false;
		try {
			driver = launchbrowser();
			webLoginPage = new LoginPage(driver);
			controlActionsWeb = new ControlActions(driver);
			webInboxPage = new InboxPage(driver);

			controlActionsWeb.getUrl(pcAppTenantURL);
			webHomePage = webLoginPage.performLogin(newUserName, newUserPassword);

			webfsqaFormsPage = new FSQABrowserPage(driver);

			webfsqaFormsPage.clickFSQABrowserMenu();
			webInboxPage.clickInboxMenu();

			verifyPC_SavedTaskOnWeb = webInboxPage.verifySavedTaskStatus(checkSavedTask1);
			TestValidation.IsTrue(verifyPC_SavedTaskOnWeb, 
					"SAVED Task is visible - '"+checkSavedTask1+"' on Web App Inbox", 
					"UNABLE TO find saved task - '"+checkSavedTask1+"' on Web App Inbox");

			webfsqaFormsPage.clickFSQABrowserMenu();
			webInboxPage.clickInboxMenu();

			verifyPC_SavedTaskOnWeb = webInboxPage.verifySavedTaskStatus(auditSavedTask1);
			TestValidation.IsTrue(verifyPC_SavedTaskOnWeb, 
					"SAVED Task is visible - '"+auditSavedTask1+"' on Web App Inbox", 
					"UNABLE TO find saved task - '"+auditSavedTask1+"' on Web App Inbox");

		}catch(Exception e) {
			verifyPC_SavedTaskOnWeb = false;
			TestValidation.IsTrue(verifyPC_SavedTaskOnWeb, 
					"SAVED Tasks are visible on Web App Inbox", 
					"UNABLE TO find saved tassk on Web App Inbox");

		}finally {
			driver.close();
		}

		boolean deleteForm = savedScreen.deleteUnopenendForm(checkFormName);
		TestValidation.IsTrue(deleteForm, 
				"DELETED saved form - "+checkFormName, 
				"FAILED to delete saved form - "+checkFormName);

		deleteForm = savedScreen.deleteUnopenendForm(auditFormName);
		TestValidation.IsTrue(deleteForm, 
				"DELETED saved form - "+auditFormName, 
				"FAILED to delete saved form - "+auditFormName);
	}

	@Test(description="Saved || Verify Aggregate field value in saved form for which 'Sum Range' field is configured")
	public void TestCase_48591() {

		String AggSrcNumericField1Data = "2";
		String AggSrcNumericField2Data = "4";
		String NumericFieldData= "2";
		String IdentifierFieldData = "1234";
		String AggregateFieldData = Integer.toString(Integer.parseInt(AggSrcNumericField1Data) + Integer.parseInt(AggSrcNumericField2Data));

		String UpdatedAggSrcNumericField2Data = "8";

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

			webfsqaFormsPage.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, questionnaireFormName);

			FormDetails formDetailsWeb = new FormDetails();
			HashMap<String, List<String>> formFieldDetailsWeb = new LinkedHashMap<String, List<String>>();
			formFieldDetailsWeb.put(AggSrcNumericFieldName1,Arrays.asList(AggSrcNumericField1Data));
			formFieldDetailsWeb.put(AggSrcNumericFieldName2,Arrays.asList(AggSrcNumericField2Data));
			formFieldDetailsWeb.put(NumericFieldName2,Arrays.asList(NumericFieldData));
			formFieldDetailsWeb.put(IdentifierFieldName,Arrays.asList(IdentifierFieldData));

			formDetailsWeb.fieldDetails = formFieldDetailsWeb;
			formDetailsWeb.resourceName = resourceInstanceValue1;
			formDetailsWeb.locationName = locationInstanceValue1;
			formDetailsWeb.isSubmit = false;

			saveformFromWeb = formOperationsWeb.submitData(formDetailsWeb);
			TestValidation.IsTrue(saveformFromWeb, 
					"SAVED FORM - '"+questionnaireFormName+"' from Web App", 
					"UNABLE TO SAVE FORM - '"+questionnaireFormName+"' from Web App");

		} catch (Exception e) {
			saveformFromWeb = false;
			TestValidation.IsTrue(saveformFromWeb, 
					"SAVED FORM - '"+questionnaireFormName+"' from Web App", 
					"FAILED TO SAVE FORM - '"+questionnaireFormName+"' from Web App");
		}
		finally {
			driver.close();
			threadsleep(2000);
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
		allFieldsData1.put(AggSrcNumericFieldName1,AggSrcNumericField1Data);
		allFieldsData1.put(AggSrcNumericFieldName2,AggSrcNumericField2Data);
		allFieldsData1.put(NumericFieldName2,NumericFieldData);
		allFieldsData1.put(IdentifierFieldName,IdentifierFieldData);
		allFieldsData1.put(AggregateFieldName,AggregateFieldData);

		boolean openSavedForm = savedScreen.selectOpenForm(questionnaireFormName);
		TestValidation.IsTrue(openSavedForm, 
				"SELECTED & opened saved form - "+questionnaireFormName, 
				"FAILED to open saved form - "+questionnaireFormName);

		boolean verifySavedFormata = savedScreen.verifySavedFormData(allFieldsData1);
		TestValidation.IsTrue(verifySavedFormata, 
				"VERIFIED data(filled from Web App) in saved form - "+questionnaireFormName, 
				"FAILED to verify data(filled from Web App) in saved form - "+questionnaireFormName);

		boolean saveForm = savedScreen.saveForm();
		TestValidation.IsTrue(saveForm, 
				"Saved form - "+questionnaireFormName, 
				"FAILED to save form - "+questionnaireFormName);

		HashMap<String, List<String>> allFields = new LinkedHashMap<String, List<String>>();
		allFields.put(AggSrcNumericFieldName1,Arrays.asList(AggSrcNumericField1Data));
		allFields.put(AggSrcNumericFieldName2,Arrays.asList(UpdatedAggSrcNumericField2Data));
		allFields.put(NumericFieldName2,Arrays.asList(NumericFieldData));
		allFields.put(IdentifierFieldName,Arrays.asList(IdentifierFieldData));

		formDetailsPC = new FormDetailsPC();
		formDetailsPC.selectLocationResource = false;
		formDetailsPC.locationName = locationInstanceValue1;
		formDetailsPC.resourceName = resourceInstanceValue1;
		formDetailsPC.fieldDetails = allFields;
		formDetailsPC.isSubmit = false;

		openSavedForm = savedScreen.selectOpenForm(questionnaireFormName);
		TestValidation.IsTrue(openSavedForm, 
				"SELECTED & opened saved form - "+questionnaireFormName, 
				"FAILED to open saved form - "+questionnaireFormName);

		boolean fillData = formview.fillDataInForm1(formDetailsPC);
		TestValidation.IsTrue(fillData, 
				"FILLED data in saved form - "+questionnaireFormName, 
				"FAILED to fill data in saved form - "+questionnaireFormName);

		saveForm = savedScreen.saveForm();
		TestValidation.IsTrue(saveForm, 
				"Saved form - "+questionnaireFormName, 
				"FAILED to save form - "+questionnaireFormName);

		openSavedForm = savedScreen.selectOpenForm(questionnaireFormName);
		TestValidation.IsTrue(openSavedForm, 
				"SELECTED & opened saved form - "+questionnaireFormName, 
				"FAILED to open saved form - "+questionnaireFormName);

		allFieldsData1 = new LinkedHashMap<String, String>();
		allFieldsData1.put(AggSrcNumericFieldName1,AggSrcNumericField1Data);
		allFieldsData1.put(AggSrcNumericFieldName2,UpdatedAggSrcNumericField2Data);
		allFieldsData1.put(NumericFieldName2,NumericFieldData);
		allFieldsData1.put(IdentifierFieldName,IdentifierFieldData);
		allFieldsData1.put(AggregateFieldName,AggSrcNumericField1Data);

		verifySavedFormata = savedScreen.verifySavedFormData(allFieldsData1);
		TestValidation.IsTrue(verifySavedFormata, 
				"VERIFIED data(filled on PC App) in saved form - "+questionnaireFormName, 
				"FAILED to verify data(filled on PC App) in saved form - "+questionnaireFormName);

		boolean deleteForm = savedScreen.deleteForm(questionnaireFormName);
		TestValidation.IsTrue(deleteForm, 
				"DELETED saved form - "+questionnaireFormName, 
				"FAILED to delete saved form - "+questionnaireFormName);
	}

	@AfterClass(alwaysRun = true)
	public void closeBrowser() throws InterruptedException {
		PCAppDriver.close();
	}

}
