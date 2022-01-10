package com.project.safetychain.pcapp.testcases;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.project.safetychain.api.models.support.TCG_ChartBuilder_Wrapper;
import com.project.safetychain.api.models.support.TCG_FormCreation_Wrapper;
import com.project.safetychain.api.models.support.TCG_TaskCreation_Wrapper;
import com.project.safetychain.api.models.support.TCG_UserFlows_Wrapper;
import com.project.safetychain.api.models.support.Support.ChartBuilder;
import com.project.safetychain.api.models.support.Support.Chart_Template_TYPES;
import com.project.safetychain.api.models.support.Support.DPT_FIELD_TYPES;
import com.project.safetychain.api.models.support.Support.DPT_FORM_TYPES;
import com.project.safetychain.api.models.support.Support.DefaultChart;
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
import com.project.safetychain.pcapp.pageobjects.SubmissionsScreen;
import com.project.safetychain.pcapp.pageobjects.FormViewScreen.FormDetailsPC;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FORMRESOURCETYPES;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.utilities.ApiUtils;
import com.project.utilities.CommonMethods;
import com.project.utilities.ControlActions_PCApp;

public class TCG_REG_Submissions_SubmissionFlows1 extends TestBase{
	ApiUtils apiUtils = null;
	FormDesignerPage formDesignerPage;

	ControlActions_PCApp controlActionsPC;
	CommonScreen commonScreen;
	LoginScreen loginScreen;
	FormsScreen forms;
	FormViewScreen formview; 
	SavedScreen savedScreen;

	FormDetailsPC formDetailsPC;

	public static String locationCategoryValue1;
	public static String locationInstanceValue1;
	public static String resourceCategoryType1 = FORMRESOURCETYPES.ITEMS;
	public static String resourceInstanceValue1;
	public static String resourceCategoryValue1;
	String locInstId, resInstId;

	public static String checkFormName1;
	String NumericFieldName1 = "Num 1";

	public static String checkFormName2;
	String TextFieldName1 = "Text 1";
	String ParagraphFieldName1 = "Paragraph 1";
	String SelectOneFieldName1 = "Select One 1";
	String DateFieldName1 = "Date 1";

	DefaultChart defaultChart = new DefaultChart();
	public static String  sum_ChartName, SumChart_LinkName;

	String submissionTaskName;

	String fileName = "upload.png";

	public static String newUserName, newUserPassword, newWGName;

	@BeforeClass(alwaysRun = true)
	public void groupInit() {

		apiUtils = new ApiUtils();

		TCG_FormCreation_Wrapper formCreationWrapper = new TCG_FormCreation_Wrapper();
		TCG_TaskCreation_Wrapper taskCreationWrapper = new TCG_TaskCreation_Wrapper();
		TCG_ChartBuilder_Wrapper chartBuilderWrapper = new TCG_ChartBuilder_Wrapper();
		TCG_UserFlows_Wrapper userCreationWrapper = new TCG_UserFlows_Wrapper();

		locationCategoryValue1 = CommonMethods.dynamicText("PC_LCat");
		locationInstanceValue1 = CommonMethods.dynamicText("PC_LInst1");
		resourceCategoryValue1 = CommonMethods.dynamicText("PC_RCat");
		resourceInstanceValue1 = CommonMethods.dynamicText("PC_RInst1");
		submissionTaskName =  CommonMethods.dynamicText("PC_SubmitTask");

		checkFormName1 = CommonMethods.dynamicText("PC_SubmissionForm1");
		checkFormName2 = CommonMethods.dynamicText("PC_SubmissionForm2");

		sum_ChartName = CommonMethods.dynamicText("PC_Auto_Sum Template");
		SumChart_LinkName = CommonMethods.dynamicText("SumChart type Chart");

		newUserName =  CommonMethods.dynamicText("PC_SubmissionFlowUser1");
		newUserPassword = "Test";
		newWGName =  CommonMethods.dynamicText("PC_SubmissionFlowWG1");


		HashMap<String, List<String>> resourceCatMap = new HashMap<String, List<String>>();
		resourceCatMap.put(resourceCategoryValue1, Arrays.asList(resourceInstanceValue1));

		HashMap<String, List<String>> LocationMap = new HashMap<String, List<String>>();
		LocationMap.put(locationCategoryValue1, Arrays.asList(locationInstanceValue1));

		List<String> userList = Arrays.asList(pcAppUsername);

		HashMap<String, String> locResMap = formCreationWrapper.Resource_Location_CreationWrapper(resourceCatMap,
				LocationMap, true, userList, resourceCategoryType1);

		locInstId = locResMap.get(locationInstanceValue1);
		resInstId = locResMap.get(resourceInstanceValue1);

		FormFieldParams NumericField1 = new FormFieldParams();
		NumericField1.DPTFieldType = DPT_FIELD_TYPES.NUMERIC;
		NumericField1.Name = NumericFieldName1;
		NumericField1.AllowAttachments = "true";
		NumericField1.RepeatField = "true";

		String formId1 = apiUtils.getUUID();

		FormParams fp1 = new FormParams();
		fp1.FormId = formId1;
		fp1.type = DPT_FORM_TYPES.CHECK;
		fp1.ResourceType = resourceCategoryType1;
		fp1.ResourceCategoryNm = resourceCategoryValue1;
		fp1.ItemsResources = resourceCatMap;
		fp1.FormName = checkFormName1;
		fp1.formElements = Arrays.asList(NumericField1);

		boolean formcreation = false;

		try {
			formCreationWrapper.API_Wrapper_Forms(fp1);
			formcreation = true;
			logInfo("'"+checkFormName1+"' Form is created");
		} catch (InterruptedException e) {
			logError("Error while '"+checkFormName1+"' form creation");
			formcreation = false;
		}

		TestValidation.IsTrue(formcreation, 
				"CREATED form - " + checkFormName1, 
				"Could NOT create form - " + checkFormName1);

		FormFieldParams FreeTextField1 = new FormFieldParams();
		FreeTextField1.DPTFieldType = DPT_FIELD_TYPES.FREE_TEXT;
		FreeTextField1.Name = TextFieldName1;

		FormFieldParams ParagraphField1 = new FormFieldParams();
		ParagraphField1.DPTFieldType = DPT_FIELD_TYPES.PARAGRAPH_TEXT;
		ParagraphField1.Name = ParagraphFieldName1;

		FormFieldParams SelectOneField1 = new FormFieldParams();
		SelectOneField1.DPTFieldType = DPT_FIELD_TYPES.SELECT_ONE;
		SelectOneField1.Name = SelectOneFieldName1;
		SelectOneField1.SelectOptions = Arrays.asList("Option 1","Option 2","Option 3","Option 4");

		FormFieldParams DateField1 = new FormFieldParams();
		DateField1.DPTFieldType = DPT_FIELD_TYPES.DATE;
		DateField1.Name = DateFieldName1;

		String formId2 = apiUtils.getUUID();
		FormParams fp2 = new FormParams();
		fp2.FormId = formId2;
		fp2.type = DPT_FORM_TYPES.CHECK;
		fp2.ResourceType = resourceCategoryType1;
		fp2.ResourceCategoryNm = resourceCategoryValue1;
		fp2.ItemsResources = resourceCatMap;
		fp2.FormName = checkFormName2;
		fp2.formElements = Arrays.asList(FreeTextField1, ParagraphField1, SelectOneField1, NumericField1, DateField1);

		formcreation = false;

		try {
			formCreationWrapper.API_Wrapper_Forms(fp2);
			formcreation = true;
			logInfo("'"+checkFormName2+"' Form is created");
		} catch (InterruptedException e) {
			logError("Error while '"+checkFormName2+"' form creation");
			formcreation = false;
		}

		TestValidation.IsTrue(formcreation, 
				"CREATED form - " + checkFormName2, 
				"Could NOT create form - " + checkFormName2);

		ChartBuilder chartBuilder = new ChartBuilder();
		DefaultChart defaultChart = new DefaultChart();

		try {
			chartBuilder = new ChartBuilder();
			chartBuilder.defaultChart = defaultChart;
			chartBuilder.chartTemplateType = Chart_Template_TYPES.SumChart;
			chartBuilder.Name = sum_ChartName;
			chartBuilder.formId = formId1;
			chartBuilder.formName = checkFormName1;
			chartBuilder.chartLinkingName = SumChart_LinkName;
			chartBuilder.ChartFieldsList = Arrays.asList(NumericFieldName1);

			chartBuilderWrapper.create_Chart_Wrapper(chartBuilder);

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		UserParams userDetails = new UserParams();
		userDetails.Username = newUserName;
		userDetails.ClearPassword = newUserPassword;
		userDetails.FirstName = "UWP LOGIN";
		userDetails.LastName = "User";
		userDetails.Email = "submissionflowuser.pcapp@s.com";
		userDetails.TimeZone = "U.S. Pacific";
		userDetails.LocationCat = locationCategoryValue1;
		userDetails.LocationNmIds = Arrays.asList(locationInstanceValue1);
		userDetails.Roles = Arrays.asList("SuperAdmin");
		//userDetails.WorkGroupNames = Arrays.asList(newWGName);

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

		boolean taskCreationStatus = true;

		TaskParams tp = new TaskParams();
		tp.FormId = formId1;
		tp.FormName = checkFormName1;
		tp.LocationInstanceNm = locationInstanceValue1;
		tp.ResourceInstanceNm = resourceInstanceValue1;
		tp.WorkGroupName = newWGName;
		tp.LocationInstanceId = locInstId;
		tp.ResourceInstanceId = resInstId;
		tp.DueBy =  "";
		tp.UserName = newUserName;
		try {

			taskCreationWrapper.create_Link_workGroup_Wrapper(tp);

			tp.TaskName = submissionTaskName;
			taskCreationWrapper.create_Task_Wrapper(tp);

			threadsleep(5000);
			taskCreationStatus = true;
			logInfo("Created Tasks");
		} catch (InterruptedException e) {
			taskCreationStatus = false;
			logError("Failed to Tasks - "+e.getMessage());
		}

		TestValidation.IsTrue(taskCreationStatus, 
				"CREATED tasks", 
				"Could NOT create tasks");


		try {

			PCAppDriver = launchPCApp();

			controlActionsPC = new ControlActions_PCApp(PCAppDriver);
			forms = new FormsScreen(PCAppDriver);
			formview = new FormViewScreen(PCAppDriver);
			savedScreen = new SavedScreen(PCAppDriver);
			loginScreen = new LoginScreen(PCAppDriver);

			commonScreen = loginScreen.Login(pcAppTenantname, newUserName, newUserPassword);
			if(commonScreen.error) {
				TCGFailureMsg = "COULD not login to the application";
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}

		}catch(Exception e){
			e.printStackTrace();
		}
	}


	@Test(description="Submissions || Verify the submitted record in Submissions should be visible with details")
	public void TestCase_33579() {

		HashMap<String, List<String>> allFields = new LinkedHashMap<String, List<String>>();
		allFields.put(NumericFieldName1,Arrays.asList("2"));

		formDetailsPC = new FormDetailsPC();
		formDetailsPC.locationName = locationInstanceValue1;
		formDetailsPC.resourceName = resourceInstanceValue1;
		formDetailsPC.fieldDetails = allFields;
		formDetailsPC.selectLocationResource = false;
		formDetailsPC.chartClose = true;

		SubmissionsScreen selectSubmissionTab  = commonScreen.selectSubmissions();
		TestValidation.IsFalse(selectSubmissionTab.error,
				"OPENED Submissions Tab", 
				"COULD not open Submissions Tab");

		boolean clearSubmission = selectSubmissionTab.clearVerifySubmission(false);
		TestValidation.IsTrue(clearSubmission, 
				"CLEARED Submissions", 
				"FAILED to Clear Submissions");

		FormsScreen selectFormsTab  = commonScreen.selectForms();
		TestValidation.IsFalse(selectFormsTab.error,
				"OPENED Forms Tab", 
				"COULD not open Forms Tab");

		boolean openForm = selectFormsTab.selectOpenForm(checkFormName1);
		TestValidation.IsTrue(openForm, 
				"SELECTED & opened form - "+checkFormName1, 
				"FAILED open form - "+checkFormName1);

		boolean submitData = formview.fillDataInForm1(formDetailsPC);
		TestValidation.IsTrue(submitData, 
				"SUBMITTED data for form - "+checkFormName1, 
				"FAILED to submit data for form - "+checkFormName1);

		selectSubmissionTab  = commonScreen.selectSubmissions();
		TestValidation.IsFalse(selectSubmissionTab.error,
				"OPENED Submissions Tab", 
				"COULD not open Submissions Tab");

		boolean verifySubmission = selectSubmissionTab.verifySubmissionAvailability(checkFormName1);
		TestValidation.IsTrue(verifySubmission, 
				"Form submission is shown for form - "+checkFormName1, 
				"FAILED verify form submission for form - "+checkFormName1);

		clearSubmission = selectSubmissionTab.clearVerifySubmission(false);
		TestValidation.IsTrue(clearSubmission, 
				"CLEARED Submissions", 
				"FAILED to Clear Submissions");

		selectFormsTab  = commonScreen.selectForms();
		TestValidation.IsFalse(selectFormsTab.error,
				"OPENED Forms Tab", 
				"COULD not open Forms Tab");

		openForm = selectFormsTab.selectOpenForm(checkFormName1);
		TestValidation.IsTrue(openForm, 
				"SELECTED & opened form - "+checkFormName1, 
				"FAILED open form - "+checkFormName1);

		//		boolean selectLocationResource = formview.selectLocationResource(locationInstanceValue1, resourceInstanceValue1);
		//		TestValidation.IsTrue(selectLocationResource, 
		//				"SELECTED Location & Resource", 
		//				"FAILED to select Location & Resource");

		boolean saveForm = formview.saveForm();
		TestValidation.IsTrue(saveForm, 
				"SAVED form - "+checkFormName1, 
				"FAILED to save the form - "+checkFormName1);

		savedScreen = commonScreen.selectSaved();
		TestValidation.IsFalse(savedScreen.error,
				"OPENED Saved Tab", 
				"COULD not open Saved Tab");

		boolean openSavedForm = savedScreen.selectOpenForm(checkFormName1);
		TestValidation.IsTrue(openSavedForm, 
				"SELECTED & opened saved form - "+checkFormName1, 
				"FAILED to open saved form - "+checkFormName1);

		submitData = formview.fillDataInForm1(formDetailsPC);
		TestValidation.IsTrue(submitData, 
				"SUBMITTED data for saved form - "+checkFormName1, 
				"FAILED to submit data for saved form - "+checkFormName1);

		selectSubmissionTab  = commonScreen.selectSubmissions();
		TestValidation.IsFalse(selectSubmissionTab.error,
				"OPENED Submissions Tab", 
				"COULD not open Submissions Tab");

		verifySubmission = selectSubmissionTab.verifySubmissionAvailability(checkFormName1);
		TestValidation.IsTrue(verifySubmission, 
				"SAVED Form submission is shown for form - "+checkFormName1, 
				"FAILED verify saved form submission for form - "+checkFormName1);

		clearSubmission = selectSubmissionTab.clearVerifySubmission(false);
		TestValidation.IsTrue(clearSubmission, 
				"CLEARED Submissions", 
				"FAILED to Clear Submissions");

		InboxScreen selectInboxTab  = commonScreen.selectInbox();
		TestValidation.IsFalse(selectInboxTab.error,
				"OPENED Inbox Tab", 
				"COULD not open Inbox Tab");

		boolean openTask = selectInboxTab.selectOpenTask(submissionTaskName, false);
		TestValidation.IsTrue(openTask, 
				"SELECTED & opened task - "+submissionTaskName, 
				"FAILED to open task - "+submissionTaskName);

		submitData = formview.fillDataInForm1(formDetailsPC);
		TestValidation.IsTrue(submitData, 
				"SUBMITTED data for task - "+submissionTaskName, 
				"FAILED to submit data fo task "+submissionTaskName);

		selectSubmissionTab  = commonScreen.selectSubmissions();
		TestValidation.IsFalse(selectSubmissionTab.error,
				"OPENED Submissions Tab", 
				"COULD not open Submissions Tab");

		verifySubmission = selectSubmissionTab.verifySubmissionAvailability(checkFormName1);
		TestValidation.IsTrue(verifySubmission, 
				"TASK submission is shown for form - "+checkFormName1, 
				"FAILED verify task submission for form - "+checkFormName1);

	}

	@Test(description="Submissions || Verify the results after searching a submissions")
	public void TestCase_33580() {

		HashMap<String, List<String>> allFields = new LinkedHashMap<String, List<String>>();
		allFields.put(NumericFieldName1,Arrays.asList("2"));

		formDetailsPC = new FormDetailsPC();
		formDetailsPC.locationName = locationInstanceValue1;
		formDetailsPC.resourceName = resourceInstanceValue1;
		formDetailsPC.fieldDetails = allFields;
		formDetailsPC.selectLocationResource = false;
		formDetailsPC.chartClose = true;

		FormsScreen selectFormsTab  = commonScreen.selectForms();
		TestValidation.IsFalse(selectFormsTab.error,
				"OPENED Forms Tab", 
				"COULD not open Forms Tab");

		boolean openForm = selectFormsTab.selectOpenForm(checkFormName1);
		TestValidation.IsTrue(openForm, 
				"SELECTED & opened form - "+checkFormName1, 
				"FAILED open form - "+checkFormName1);

		boolean submitData = formview.fillDataInForm1(formDetailsPC);
		TestValidation.IsTrue(submitData, 
				"SUBMITTED data for form - "+checkFormName1, 
				"FAILED to submit data for form - "+checkFormName1);

		SubmissionsScreen selectSubmissionTab  = commonScreen.selectSubmissions();
		TestValidation.IsFalse(selectSubmissionTab.error,
				"OPENED Submissions Tab", 
				"COULD not open Submissions Tab");

		boolean verifySubmission = selectSubmissionTab.verifySubmissionAvailability(checkFormName1);
		TestValidation.IsTrue(verifySubmission, 
				"Form submission is shown for form - "+checkFormName1, 
				"FAILED verify form submission for form - "+checkFormName1);

	}


	@Test(description="Submissions || Verify the 'CLEAR ALL' functionality")
	public void TestCase_33582() {

		HashMap<String, List<String>> allFields = new LinkedHashMap<String, List<String>>();
		allFields.put(NumericFieldName1,Arrays.asList("2"));

		formDetailsPC = new FormDetailsPC();
		formDetailsPC.locationName = locationInstanceValue1;
		formDetailsPC.resourceName = resourceInstanceValue1;
		formDetailsPC.fieldDetails = allFields;
		formDetailsPC.selectLocationResource = false;

		SettingsScreen selectSettingsTab  = commonScreen.selectSettings();
		TestValidation.IsFalse(selectSettingsTab.error,
				"OPENED 'Settings' Tab", 
				"COULD not open 'Settings' Tab");

		boolean changeOfflineModeToggle = selectSettingsTab.changeOfflineModeToggle(true);
		TestValidation.IsTrue(changeOfflineModeToggle, 
				"CHANGED 'Offline Mode' toggle - ON", 
				"FAILED to change''Offline Mode' toggle - ON");

		FormsScreen selectFormsTab  = commonScreen.selectForms();
		TestValidation.IsFalse(selectFormsTab.error,
				"OPENED Forms Tab", 
				"COULD not open Forms Tab");

		boolean openForm = selectFormsTab.selectOpenForm(checkFormName1);
		TestValidation.IsTrue(openForm, 
				"SELECTED & opened form - "+checkFormName1, 
				"FAILED open form - "+checkFormName1);

		boolean submitData = formview.fillDataInForm1(formDetailsPC);
		TestValidation.IsTrue(submitData, 
				"SUBMITTED data for form - "+checkFormName1, 
				"FAILED to submit data for form - "+checkFormName1);

		SubmissionsScreen selectSubmissionTab  = commonScreen.selectSubmissions();
		TestValidation.IsFalse(selectSubmissionTab.error,
				"OPENED Submissions Tab", 
				"COULD not open Submissions Tab");

		boolean clearSubmission = selectSubmissionTab.clearVerifySubmission(true);
		TestValidation.IsFalse(clearSubmission, 
				"CLEARED Submissions & verified available pending submissions", 
				"FAILED to Clear Submissions & verify Pending submissions");

		selectSettingsTab  = commonScreen.selectSettings();
		TestValidation.IsFalse(selectSettingsTab.error,
				"OPENED 'Settings' Tab", 
				"COULD not open 'Settings' Tab");

		changeOfflineModeToggle = selectSettingsTab.changeOfflineModeToggle(false);
		TestValidation.IsTrue(changeOfflineModeToggle, 
				"CHANGED 'Offline Mode' toggle - OFF", 
				"FAILED to change''Offline Mode' toggle - OFF");

		formDetailsPC.chartClose = true;

		selectFormsTab  = commonScreen.selectForms();
		TestValidation.IsFalse(selectFormsTab.error,
				"OPENED Forms Tab", 
				"COULD not open Forms Tab");

		openForm = selectFormsTab.selectOpenForm(checkFormName1);
		TestValidation.IsTrue(openForm, 
				"SELECTED & opened form - "+checkFormName1, 
				"FAILED open form - "+checkFormName1);

		submitData = formview.fillDataInForm1(formDetailsPC);
		TestValidation.IsTrue(submitData, 
				"SUBMITTED data for form - "+checkFormName1, 
				"FAILED to submit data for form - "+checkFormName1);

		selectSubmissionTab  = commonScreen.selectSubmissions();
		TestValidation.IsFalse(selectSubmissionTab.error,
				"OPENED Submissions Tab", 
				"COULD not open Submissions Tab");

		clearSubmission = selectSubmissionTab.clearVerifySubmission(true);
		TestValidation.IsTrue(clearSubmission, 
				"CLEARED Submissions & verified", 
				"FAILED to Clear Submissions & verify");
	}


	@Test(description="Submission || Verify the submitted data in Submissions")
	public void TestCase_33584() {

		HashMap<String, List<String>> allFields = new LinkedHashMap<String, List<String>>();
		allFields.put(TextFieldName1,Arrays.asList("Unique Text"));
		allFields.put(ParagraphFieldName1,Arrays.asList("Unique Paragraph"));
		allFields.put(SelectOneFieldName1,Arrays.asList("Option 2"));
		allFields.put(NumericFieldName1,Arrays.asList("1234"));
		allFields.put(DateFieldName1,Arrays.asList(controlActionsPC.getDayDate(0)));

		String DateFieldValue = controlActionsPC.getDate("M/dd/yyyy", "Day", 0);

		HashMap<String, String> allFieldsDetails = new LinkedHashMap<String, String>();		
		allFieldsDetails.put(TextFieldName1,"Unique Text");
		allFieldsDetails.put(ParagraphFieldName1,"Unique Paragraph");
		allFieldsDetails.put(SelectOneFieldName1,"Option 2");
		allFieldsDetails.put(NumericFieldName1,"1234");
		allFieldsDetails.put(DateFieldName1,DateFieldValue);

		formDetailsPC = new FormDetailsPC();
		formDetailsPC.locationName = locationInstanceValue1;
		formDetailsPC.resourceName = resourceInstanceValue1;
		formDetailsPC.fieldDetails = allFields;

		FormsScreen selectFormsTab  = commonScreen.selectForms();
		TestValidation.IsFalse(selectFormsTab.error,
				"OPENED Forms Tab", 
				"COULD not open Forms Tab");

		boolean openForm = selectFormsTab.selectOpenForm(checkFormName2);
		TestValidation.IsTrue(openForm, 
				"SELECTED & opened form - "+checkFormName2, 
				"FAILED to open form - "+checkFormName2);

		boolean submitData = formview.fillDataInForm1(formDetailsPC);
		TestValidation.IsTrue(submitData, 
				"SUBMITTED data for form - "+checkFormName2, 
				"FAILED submit data fo form - "+checkFormName2);

		SubmissionsScreen selectSubmissionTab  = commonScreen.selectSubmissions();
		TestValidation.IsFalse(selectSubmissionTab.error,
				"OPENED Submissions Tab", 
				"COULD not open Submissions Tab");

		boolean verifySubmission = selectSubmissionTab.selectOpenSubmissionVerifyDetails(checkFormName2, allFieldsDetails);
		TestValidation.IsTrue(verifySubmission, 
				"FORM submission details are shown for form - "+checkFormName2, 
				"FAILED to verify form submission details for form - "+checkFormName2);
	}

	@Test(description="Submissions || Verify user is able to render the field(s) of submitted record")
	public void TestCase_33585() {

		HashMap<String, List<String>> allFields = new LinkedHashMap<String, List<String>>();
		allFields.put(TextFieldName1,Arrays.asList("Unique Text"));
		allFields.put(ParagraphFieldName1,Arrays.asList("Unique Paragraph"));
		allFields.put(SelectOneFieldName1,Arrays.asList("Option 2"));
		allFields.put(NumericFieldName1,Arrays.asList("1234"));
		allFields.put(DateFieldName1,Arrays.asList(controlActionsPC.getDayDate(0)));

		String dateFieldValue = controlActionsPC.getDate("MM/dd/yyyy", "Day", 0);

		HashMap<String, String> allFieldsDetails = new LinkedHashMap<String, String>();		
		allFieldsDetails.put(TextFieldName1,"Unique Text");
		allFieldsDetails.put(ParagraphFieldName1,"Unique Paragraph");
		allFieldsDetails.put(SelectOneFieldName1,"Option 2");
		allFieldsDetails.put(NumericFieldName1,"1234");
		allFieldsDetails.put(DateFieldName1,dateFieldValue);

		formDetailsPC = new FormDetailsPC();
		formDetailsPC.locationName = locationInstanceValue1;
		formDetailsPC.resourceName = resourceInstanceValue1;
		formDetailsPC.fieldDetails = allFields;

		FormsScreen selectFormsTab  = commonScreen.selectForms();
		TestValidation.IsFalse(selectFormsTab.error,
				"OPENED Forms Tab", 
				"COULD not open Forms Tab");

		boolean openForm = selectFormsTab.selectOpenForm(checkFormName2);
		TestValidation.IsTrue(openForm, 
				"SELECTED & opened form - "+checkFormName2, 
				"FAILED to open form - "+checkFormName2);

		boolean submitData = formview.fillDataInForm1(formDetailsPC);
		TestValidation.IsTrue(submitData, 
				"SUBMITTED data for form - "+checkFormName2, 
				"FAILED submit data fo form - "+checkFormName2);

		SubmissionsScreen selectSubmissionTab  = commonScreen.selectSubmissions();
		TestValidation.IsFalse(selectSubmissionTab.error,
				"OPENED Submissions Tab", 
				"COULD not open Submissions Tab");

		boolean verifySubmission = selectSubmissionTab.selectOpenSubmissionVerifyFieldDetails(checkFormName2, allFieldsDetails);
		TestValidation.IsTrue(verifySubmission, 
				"FORM submission is shown & fields are accesible for form - "+checkFormName2, 
				"FAILED to acesss fields in submission for form - "+checkFormName2);
	}

	@Test(description="Submissions || Verify the chart for a submission")
	public void TestCase_33586() {

		HashMap<String, List<String>> allFields = new LinkedHashMap<String, List<String>>();
		allFields.put(NumericFieldName1,Arrays.asList("2"));

		formDetailsPC = new FormDetailsPC();
		formDetailsPC.locationName = locationInstanceValue1;
		formDetailsPC.resourceName = resourceInstanceValue1;
		formDetailsPC.fieldDetails = allFields;
		formDetailsPC.selectLocationResource = false;
		formDetailsPC.chartClose = true;

		FormsScreen selectFormsTab  = commonScreen.selectForms();
		TestValidation.IsFalse(selectFormsTab.error,
				"OPENED Forms Tab", 
				"COULD not open Forms Tab");

		boolean openForm = selectFormsTab.selectOpenForm(checkFormName1);
		TestValidation.IsTrue(openForm, 
				"SELECTED & opened form - "+checkFormName1, 
				"FAILED open form - "+checkFormName1);

		boolean submitData = formview.fillDataInForm1(formDetailsPC);
		TestValidation.IsTrue(submitData, 
				"SUBMITTED data for form - "+checkFormName1, 
				"FAILED to submit data for form - "+checkFormName1);

		SubmissionsScreen selectSubmissionTab  = commonScreen.selectSubmissions();
		TestValidation.IsFalse(selectSubmissionTab.error,
				"OPENED Submissions Tab", 
				"COULD not open Submissions Tab");

		boolean openSubmission = selectSubmissionTab.openSubmission(checkFormName1);
		TestValidation.IsTrue(openSubmission, 
				"OPENED Form submission - "+checkFormName1, 
				"FAILED to open form submission for form - "+checkFormName1);

		boolean viewChart = selectSubmissionTab.viewChart(false);
		TestValidation.IsTrue(viewChart, 
				"OPENED Form submission & Viewed Chart - "+checkFormName1, 
				"FAILED to open form submission & view chart for form - "+checkFormName1);
	}

	@Test(description="Submissions || Verify 'RESUBMIT ALL' functionality")
	public void TestCase_38017() {

		byte submitRepeatCount = 15;


		HashMap<String, List<String>> allFields = new LinkedHashMap<String, List<String>>();
		allFields.put(NumericFieldName1,Arrays.asList("2"));

		formDetailsPC = new FormDetailsPC();
		formDetailsPC.locationName = locationInstanceValue1;
		formDetailsPC.resourceName = resourceInstanceValue1;
		formDetailsPC.fieldDetails = allFields;
		formDetailsPC.selectLocationResource = false;
		formDetailsPC.submitRepeatCheck = true;
		formDetailsPC.submitRepeatCount = submitRepeatCount;
		formDetailsPC.discardCheck = true;
		formDetailsPC.isSubmit = false;

		SettingsScreen selectSettingsTab  = commonScreen.selectSettings();
		TestValidation.IsFalse(selectSettingsTab.error,
				"OPENED 'Settings' Tab", 
				"COULD not open 'Settings' Tab");

		boolean changeOfflineModeToggle = selectSettingsTab.changeOfflineModeToggle(true);
		TestValidation.IsTrue(changeOfflineModeToggle, 
				"CHANGED 'Offline Mode' toggle - ON", 
				"FAILED to change''Offline Mode' toggle - ON");

		FormsScreen selectFormsTab  = commonScreen.selectForms();
		TestValidation.IsFalse(selectFormsTab.error,
				"OPENED Forms Tab", 
				"COULD not open Forms Tab");

		boolean openForm = selectFormsTab.selectOpenForm(checkFormName1);
		TestValidation.IsTrue(openForm, 
				"SELECTED & opened form - "+checkFormName1, 
				"FAILED open form - "+checkFormName1);

		boolean submitData = formview.fillDataInForm1(formDetailsPC);
		TestValidation.IsTrue(submitData, 
				"SUBMITTED data for form - "+checkFormName1, 
				"FAILED to submit data for form - "+checkFormName1);

		SubmissionsScreen selectSubmissionTab  = commonScreen.selectSubmissions();
		TestValidation.IsFalse(selectSubmissionTab.error,
				"OPENED Submissions Tab", 
				"COULD not open Submissions Tab");

		boolean submissionCount = selectSubmissionTab.verifySubmissionCount(submitRepeatCount);
		TestValidation.IsTrue(submissionCount, 
				"VERIFIED Pending submission count", 
				"FAILED to pending submission count");

		selectSettingsTab  = commonScreen.selectSettings();
		TestValidation.IsFalse(selectSettingsTab.error,
				"OPENED 'Settings' Tab", 
				"COULD not open 'Settings' Tab");

		changeOfflineModeToggle = selectSettingsTab.changeOfflineModeToggle(false);
		TestValidation.IsTrue(changeOfflineModeToggle, 
				"CHANGED 'Offline Mode' toggle - OFF", 
				"FAILED to change''Offline Mode' toggle - OFF");

		formDetailsPC.chartClose = true;

		selectSubmissionTab  = commonScreen.selectSubmissions();
		TestValidation.IsFalse(selectSubmissionTab.error,
				"OPENED Submissions Tab", 
				"COULD not open Submissions Tab");

		boolean reSubmitAll = selectSubmissionTab.performResubmitAll();
		TestValidation.IsTrue(reSubmitAll, 
				"PERFORMED 'RESUBMIT ALL' action", 
				"FAILED to perform 'RESUBMIT ALL' action");

		threadsleep(20000);

		submissionCount = selectSubmissionTab.verifySubmissionCount(0);
		TestValidation.IsTrue(submissionCount, 
				"VERIFIED Pending submission count", 
				"FAILED to pending submission count");
	}

	@Test(description="Submissions || Verify user should get chart only one time after clicking multiple times on 'View Chart' button.")
	public void TestCase_42954() {

		HashMap<String, List<String>> allFields = new LinkedHashMap<String, List<String>>();
		allFields.put(NumericFieldName1,Arrays.asList("2"));

		formDetailsPC = new FormDetailsPC();
		formDetailsPC.locationName = locationInstanceValue1;
		formDetailsPC.resourceName = resourceInstanceValue1;
		formDetailsPC.fieldDetails = allFields;
		formDetailsPC.selectLocationResource = false;
		formDetailsPC.chartClose = true;

		FormsScreen selectFormsTab  = commonScreen.selectForms();
		TestValidation.IsFalse(selectFormsTab.error,
				"OPENED Forms Tab", 
				"COULD not open Forms Tab");

		boolean openForm = selectFormsTab.selectOpenForm(checkFormName1);
		TestValidation.IsTrue(openForm, 
				"SELECTED & opened form - "+checkFormName1, 
				"FAILED open form - "+checkFormName1);

		boolean submitData = formview.fillDataInForm1(formDetailsPC);
		TestValidation.IsTrue(submitData, 
				"SUBMITTED data for form - "+checkFormName1, 
				"FAILED to submit data for form - "+checkFormName1);

		SubmissionsScreen selectSubmissionTab  = commonScreen.selectSubmissions();
		TestValidation.IsFalse(selectSubmissionTab.error,
				"OPENED Submissions Tab", 
				"COULD not open Submissions Tab");

		boolean openSubmission = selectSubmissionTab.openSubmission(checkFormName1);
		TestValidation.IsTrue(openSubmission, 
				"OPENED Form submission - "+checkFormName1, 
				"FAILED to open form submission for form - "+checkFormName1);

		boolean viewChart = selectSubmissionTab.viewChart(true);
		TestValidation.IsTrue(viewChart, 
				"OPENED Form submission & Viewed Chart - "+checkFormName1, 
				"FAILED to open form submission & view chart for form - "+checkFormName1);
	}

	@AfterClass(alwaysRun = true)
	public void closeBrowser() throws InterruptedException {
		PCAppDriver.close();
	}

}
