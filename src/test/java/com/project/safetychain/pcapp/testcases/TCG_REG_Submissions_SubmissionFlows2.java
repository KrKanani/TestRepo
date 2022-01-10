package com.project.safetychain.pcapp.testcases;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
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
import com.project.safetychain.pcapp.pageobjects.SubmissionsScreen;
import com.project.safetychain.pcapp.pageobjects.FormViewScreen.FormDetailsPC;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FORMRESOURCETYPES;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.utilities.ApiUtils;
import com.project.utilities.CommonMethods;
import com.project.utilities.ControlActions_PCApp;

public class TCG_REG_Submissions_SubmissionFlows2 extends TestBase{
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

	public static String auditFormName1;
	String SectionName = "Section 1";
	String NumericFieldName1 = "Num 1";

	String onlineAttachmentsubmissionTaskName, offlineAttachmentsubmissionTaskName;

	String fileName = "upload.png";

	public static String newUserName, newUserPassword, newWGName;

	@BeforeClass(alwaysRun = true)
	public void groupInit() {

		apiUtils = new ApiUtils();

		TCG_FormCreation_Wrapper formCreationWrapper = new TCG_FormCreation_Wrapper();
		TCG_TaskCreation_Wrapper taskCreationWrapper = new TCG_TaskCreation_Wrapper();
		TCG_UserFlows_Wrapper userCreationWrapper = new TCG_UserFlows_Wrapper();

		locationCategoryValue1 = CommonMethods.dynamicText("PC_LCat");
		locationInstanceValue1 = CommonMethods.dynamicText("PC_LInst1");
		resourceCategoryValue1 = CommonMethods.dynamicText("PC_RCat");
		resourceInstanceValue1 = CommonMethods.dynamicText("PC_RInst1");

		onlineAttachmentsubmissionTaskName =  CommonMethods.dynamicText("PC_OnlineAttachSubmitTask");
		offlineAttachmentsubmissionTaskName =  CommonMethods.dynamicText("PC_OfflineAttachSubmitTask");

		auditFormName1 = CommonMethods.dynamicText("PC_SubmissionForm3");

		newUserName =  CommonMethods.dynamicText("PC_SubmissionFlowUser2");
		newUserPassword = "Test";
		newWGName =  CommonMethods.dynamicText("PC_SubmissionFlowWG2");

		HashMap<String, List<String>> resourceCatMap = new HashMap<String, List<String>>();
		resourceCatMap.put(resourceCategoryValue1, Arrays.asList(resourceInstanceValue1));

		HashMap<String, List<String>> LocationMap = new HashMap<String, List<String>>();
		LocationMap.put(locationCategoryValue1, Arrays.asList(locationInstanceValue1));

		List<String> userList = Arrays.asList(pcAppUsername);

		HashMap<String, String> locResMap = formCreationWrapper.Resource_Location_CreationWrapper(resourceCatMap,
				LocationMap, true, userList, resourceCategoryType1);

		locInstId = locResMap.get(locationInstanceValue1);
		resInstId = locResMap.get(resourceInstanceValue1);

		Element_Types Section1 = new Element_Types();
		Section1.ElementType = DPT_FIELD_TYPES.SECTION;
		Section1.Name = SectionName;

		FormFieldParams NumericField1 = new FormFieldParams();
		NumericField1.DPTFieldType = DPT_FIELD_TYPES.NUMERIC;
		NumericField1.Name = NumericFieldName1;
		NumericField1.AllowAttachments = "true";
		NumericField1.RepeatField = "true";

		Section1.formFieldParams = Arrays.asList(NumericField1);

		String formId1 = apiUtils.getUUID();

		FormParams fp1 = new FormParams();
		fp1.FormId = formId1;
		fp1.type = DPT_FORM_TYPES.AUDIT;
		fp1.ResourceType = resourceCategoryType1;
		fp1.ResourceCategoryNm = resourceCategoryValue1;
		fp1.ItemsResources = resourceCatMap;
		fp1.FormName = auditFormName1;
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
		tp.FormName = auditFormName1;
		tp.LocationInstanceNm = locationInstanceValue1;
		tp.ResourceInstanceNm = resourceInstanceValue1;
		tp.WorkGroupName = newWGName;
		tp.LocationInstanceId = locInstId;
		tp.ResourceInstanceId = resInstId;
		tp.DueBy =  "";
		tp.UserName = newUserName;
		try {

			taskCreationWrapper.create_Link_workGroup_Wrapper(tp);

			tp.TaskName = onlineAttachmentsubmissionTaskName;
			taskCreationWrapper.create_Task_Wrapper(tp);

			tp.TaskName = offlineAttachmentsubmissionTaskName;
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

	@Test(description="Submissions || Verify the pending submissions details")
	public void TestCase_33583() {

		HashMap<String, List<String>> allFields = new LinkedHashMap<String, List<String>>();
		allFields.put(NumericFieldName1,Arrays.asList("2"));

		formDetailsPC = new FormDetailsPC();
		formDetailsPC.locationName = locationInstanceValue1;
		formDetailsPC.resourceName = resourceInstanceValue1;
		formDetailsPC.fieldDetails = allFields;
		formDetailsPC.selectLocationResource = false;
		formDetailsPC.propertyfieldName = NumericFieldName1;
		formDetailsPC.allowAttachmentCheck = true;
		formDetailsPC.fileName = "AttachmentsFolder";

		FormsScreen selectFormsTab  = commonScreen.selectForms();
		TestValidation.IsFalse(selectFormsTab.error,
				"OPENED Forms Tab", 
				"COULD not open Forms Tab");

		boolean openForm = selectFormsTab.selectOpenForm(auditFormName1);
		TestValidation.IsTrue(openForm, 
				"SELECTED & opened form - "+auditFormName1, 
				"FAILED open form - "+auditFormName1);

		boolean submitData = formview.fillDataInForm1(formDetailsPC);
		TestValidation.IsTrue(submitData, 
				"SUBMITTED data for form - "+auditFormName1, 
				"FAILED to submit data for form - "+auditFormName1);

		SubmissionsScreen selectSubmissionTab  = commonScreen.selectSubmissions();
		TestValidation.IsFalse(selectSubmissionTab.error,
				"OPENED Submissions Tab", 
				"COULD not open Submissions Tab");

		boolean verifyPendingSubmissionCount = selectSubmissionTab.verifySubmissionCount(1);
		TestValidation.IsTrue(verifyPendingSubmissionCount, 
				"VERIFIED Pending submission count - 1", 
				"FAILED to verify Pending submission count - 1");

		threadsleep(40000);

		verifyPendingSubmissionCount = selectSubmissionTab.verifySubmissionCount(0);
		TestValidation.IsTrue(verifyPendingSubmissionCount, 
				"VERIFIED Pending submission gets successful", 
				"FAILED to verify submission of Pending submission");

	}


	@Test(description="Submissions || Submission having attachment should get successfully submitted to the server if multiple submissions are in queue/pending state")
	public void TestCase_38087() {

		HashMap<String, List<String>> allFields = new LinkedHashMap<String, List<String>>();
		allFields.put(NumericFieldName1,Arrays.asList("2"));

		formDetailsPC = new FormDetailsPC();
		formDetailsPC.locationName = locationInstanceValue1;
		formDetailsPC.resourceName = resourceInstanceValue1;
		formDetailsPC.fieldDetails = allFields;
		formDetailsPC.selectLocationResource = false;
		formDetailsPC.propertyfieldName = NumericFieldName1;
		formDetailsPC.allowAttachmentCheck = true;
		formDetailsPC.submitRepeatCheck = true;
		formDetailsPC.submitRepeatCount = 4;
		formDetailsPC.isSubmit = true;


		FormsScreen selectFormsTab  = commonScreen.selectForms();
		TestValidation.IsFalse(selectFormsTab.error,
				"OPENED Forms Tab", 
				"COULD not open Forms Tab");

		boolean openForm = selectFormsTab.selectOpenForm(auditFormName1);
		TestValidation.IsTrue(openForm, 
				"SELECTED & opened form - "+auditFormName1, 
				"FAILED open form - "+auditFormName1);

		boolean submitData = formview.fillDataInForm1(formDetailsPC);
		TestValidation.IsTrue(submitData, 
				"SUBMITTED data for form - "+auditFormName1, 
				"FAILED to submit data for form - "+auditFormName1);

		SubmissionsScreen selectSubmissionTab  = commonScreen.selectSubmissions();
		TestValidation.IsFalse(selectSubmissionTab.error,
				"OPENED Submissions Tab", 
				"COULD not open Submissions Tab");

		boolean openSubmission = selectSubmissionTab.openSubmission(auditFormName1);
		TestValidation.IsTrue(openSubmission, 
				"OPENED Form submission - "+auditFormName1, 
				"FAILED to open form submission for form - "+auditFormName1);

		boolean verifyAttachment = selectSubmissionTab.verifySubmissionAttachment(NumericFieldName1,fileName);
		TestValidation.IsTrue(verifyAttachment, 
				"VERIFIED attachment - "+fileName+"' in the submission - "+auditFormName1, 
				"FAILED to verify attachment -"+fileName+"' in the submission for form - "+auditFormName1);

	}


	@Test(description="Submissions || Verify form submission having attachment in online mode")
	public void TestCase_45313() {

		HashMap<String, List<String>> allFields = new LinkedHashMap<String, List<String>>();
		allFields.put(NumericFieldName1,Arrays.asList("2"));

		formDetailsPC = new FormDetailsPC();
		formDetailsPC.locationName = locationInstanceValue1;
		formDetailsPC.resourceName = resourceInstanceValue1;
		formDetailsPC.fieldDetails = allFields;
		formDetailsPC.selectLocationResource = false;
		formDetailsPC.propertyfieldName = NumericFieldName1;
		formDetailsPC.allowAttachmentCheck = true;

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

		boolean openForm = selectFormsTab.selectOpenForm(auditFormName1);
		TestValidation.IsTrue(openForm, 
				"SELECTED & opened form - "+auditFormName1, 
				"FAILED open form - "+auditFormName1);

		boolean submitData = formview.fillDataInForm1(formDetailsPC);
		TestValidation.IsTrue(submitData, 
				"SUBMITTED data for form - "+auditFormName1, 
				"FAILED to submit data for form - "+auditFormName1);

		selectSubmissionTab  = commonScreen.selectSubmissions();
		TestValidation.IsFalse(selectSubmissionTab.error,
				"OPENED Submissions Tab", 
				"COULD not open Submissions Tab");

		boolean openSubmission = selectSubmissionTab.openSubmission(auditFormName1);
		TestValidation.IsTrue(openSubmission, 
				"OPENED Form submission - "+auditFormName1, 
				"FAILED to open form submission for form - "+auditFormName1);

		boolean verifyAttachment = selectSubmissionTab.verifySubmissionAttachment(NumericFieldName1,fileName);
		TestValidation.IsTrue(verifyAttachment, 
				"VERIFIED attachment - "+fileName+"' in the submission - "+auditFormName1, 
				"FAILED to verify attachment -"+fileName+"' in the submission for form - "+auditFormName1);

	}

	@Test(description="Submissions || Verify task submission having attachment in online mode")
	public void TestCase_45314() {

		HashMap<String, List<String>> allFields = new LinkedHashMap<String, List<String>>();
		allFields.put(NumericFieldName1,Arrays.asList("2"));

		formDetailsPC = new FormDetailsPC();
		formDetailsPC.locationName = locationInstanceValue1;
		formDetailsPC.resourceName = resourceInstanceValue1;
		formDetailsPC.fieldDetails = allFields;
		formDetailsPC.selectLocationResource = false;
		formDetailsPC.propertyfieldName = NumericFieldName1;
		formDetailsPC.allowAttachmentCheck = true;

		SubmissionsScreen selectSubmissionTab  = commonScreen.selectSubmissions();
		TestValidation.IsFalse(selectSubmissionTab.error,
				"OPENED Submissions Tab", 
				"COULD not open Submissions Tab");

		boolean clearSubmission = selectSubmissionTab.clearVerifySubmission(false);
		TestValidation.IsTrue(clearSubmission, 
				"CLEARED Submissions", 
				"FAILED to Clear Submissions");

		InboxScreen selectInboxTab  = commonScreen.selectInbox();
		TestValidation.IsFalse(selectInboxTab.error,
				"OPENED Inbox Tab", 
				"COULD not open Inbox Tab");

		boolean openTask = selectInboxTab.selectOpenTask(onlineAttachmentsubmissionTaskName, false);
		TestValidation.IsTrue(openTask, 
				"SELECTED & opened task - "+onlineAttachmentsubmissionTaskName, 
				"FAILED to open task - "+onlineAttachmentsubmissionTaskName);

		boolean submitData = formview.fillDataInForm1(formDetailsPC);
		TestValidation.IsTrue(submitData, 
				"SUBMITTED data for task - "+onlineAttachmentsubmissionTaskName, 
				"FAILED to submit data for task - "+onlineAttachmentsubmissionTaskName);

		selectSubmissionTab  = commonScreen.selectSubmissions();
		TestValidation.IsFalse(selectSubmissionTab.error,
				"OPENED Submissions Tab", 
				"COULD not open Submissions Tab");

		boolean openSubmission = selectSubmissionTab.openSubmission(auditFormName1);
		TestValidation.IsTrue(openSubmission, 
				"OPENED Form submission - "+auditFormName1, 
				"FAILED to open form submission for form - "+auditFormName1);

		boolean verifyAttachment = selectSubmissionTab.verifySubmissionAttachment(NumericFieldName1,fileName);
		TestValidation.IsTrue(verifyAttachment, 
				"VERIFIED attachment - "+fileName+"' in the submission - "+auditFormName1, 
				"FAILED to verify attachment -"+fileName+"' in the submission for form - "+auditFormName1);

	}

	@Test(description="Submissions || Verify saved form submission having attachment in online mode")
	public void TestCase_45315() {

		HashMap<String, List<String>> allFields = new LinkedHashMap<String, List<String>>();
		allFields.put(NumericFieldName1,Arrays.asList("2"));

		formDetailsPC = new FormDetailsPC();
		formDetailsPC.locationName = locationInstanceValue1;
		formDetailsPC.resourceName = resourceInstanceValue1;
		formDetailsPC.fieldDetails = allFields;
		formDetailsPC.selectLocationResource = false;
		formDetailsPC.propertyfieldName = NumericFieldName1;
		formDetailsPC.allowAttachmentCheck = true;

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

		boolean openForm = selectFormsTab.selectOpenForm(auditFormName1);
		TestValidation.IsTrue(openForm, 
				"SELECTED & opened form - "+auditFormName1, 
				"FAILED open form - "+auditFormName1);

		boolean selectLocationResource = formview.selectLocationResource(locationInstanceValue1, resourceInstanceValue1);
		TestValidation.IsTrue(selectLocationResource, 
				"SELECTED Location & Resource", 
				"FAILED to select Location & Resource");

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

		boolean submitData = formview.fillDataInForm1(formDetailsPC);
		TestValidation.IsTrue(submitData, 
				"SUBMITTED data for form - "+auditFormName1, 
				"FAILED to submit data for form - "+auditFormName1);

		selectSubmissionTab  = commonScreen.selectSubmissions();
		TestValidation.IsFalse(selectSubmissionTab.error,
				"OPENED Submissions Tab", 
				"COULD not open Submissions Tab");

		boolean openSubmission = selectSubmissionTab.openSubmission(auditFormName1);
		TestValidation.IsTrue(openSubmission, 
				"OPENED Form submission - "+auditFormName1, 
				"FAILED to open form submission for form - "+auditFormName1);

		boolean verifyAttachment = selectSubmissionTab.verifySubmissionAttachment(NumericFieldName1,fileName);
		TestValidation.IsTrue(verifyAttachment, 
				"VERIFIED attachment - "+fileName+"' in the submission - "+auditFormName1, 
				"FAILED to verify attachment -"+fileName+"' in the submission for form - "+auditFormName1);

	}


	@Test(description="Submissions || Verify form submission having attachment in offline mode")
	public void TestCase_45316() {

		HashMap<String, List<String>> allFields = new LinkedHashMap<String, List<String>>();
		allFields.put(NumericFieldName1,Arrays.asList("2"));

		formDetailsPC = new FormDetailsPC();
		formDetailsPC.locationName = locationInstanceValue1;
		formDetailsPC.resourceName = resourceInstanceValue1;
		formDetailsPC.fieldDetails = allFields;
		formDetailsPC.selectLocationResource = false;
		formDetailsPC.propertyfieldName = NumericFieldName1;
		formDetailsPC.allowAttachmentCheck = true;

		SubmissionsScreen selectSubmissionTab  = commonScreen.selectSubmissions();
		TestValidation.IsFalse(selectSubmissionTab.error,
				"OPENED Submissions Tab", 
				"COULD not open Submissions Tab");

		boolean clearSubmission = selectSubmissionTab.clearVerifySubmission(false);
		TestValidation.IsTrue(clearSubmission, 
				"CLEARED Submissions", 
				"FAILED to Clear Submissions");

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

		boolean openForm = selectFormsTab.selectOpenForm(auditFormName1);
		TestValidation.IsTrue(openForm, 
				"SELECTED & opened form - "+auditFormName1, 
				"FAILED open form - "+auditFormName1);

		boolean submitData = formview.fillDataInForm1(formDetailsPC);
		TestValidation.IsTrue(submitData, 
				"SUBMITTED data for form - "+auditFormName1, 
				"FAILED to submit data for form - "+auditFormName1);

		selectSubmissionTab  = commonScreen.selectSubmissions();
		TestValidation.IsFalse(selectSubmissionTab.error,
				"OPENED Submissions Tab", 
				"COULD not open Submissions Tab");

		boolean openSubmission = selectSubmissionTab.openSubmission(auditFormName1);
		TestValidation.IsTrue(openSubmission, 
				"OPENED Form submission - "+auditFormName1, 
				"FAILED to open form submission for form - "+auditFormName1);

		boolean verifyAttachment = selectSubmissionTab.verifySubmissionAttachment(NumericFieldName1,fileName);
		TestValidation.IsTrue(verifyAttachment, 
				"VERIFIED attachment - "+fileName+"' in the submission - "+auditFormName1, 
				"FAILED to verify attachment -"+fileName+"' in the submission for form - "+auditFormName1);

		selectSettingsTab  = commonScreen.selectSettings();
		TestValidation.IsFalse(selectSettingsTab.error,
				"OPENED 'Settings' Tab", 
				"COULD not open 'Settings' Tab");

		changeOfflineModeToggle = selectSettingsTab.changeOfflineModeToggle(false);
		TestValidation.IsTrue(changeOfflineModeToggle, 
				"CHANGED 'Offline Mode' toggle - OFF", 
				"FAILED to change''Offline Mode' toggle - OFF");

		threadsleep(5000);

	}

	@Test(description="Submissions || Verify task submission having attachment in offline mode")
	public void TestCase_45317() {

		HashMap<String, List<String>> allFields = new LinkedHashMap<String, List<String>>();
		allFields.put(NumericFieldName1,Arrays.asList("2"));

		formDetailsPC = new FormDetailsPC();
		formDetailsPC.locationName = locationInstanceValue1;
		formDetailsPC.resourceName = resourceInstanceValue1;
		formDetailsPC.fieldDetails = allFields;
		formDetailsPC.selectLocationResource = false;
		formDetailsPC.propertyfieldName = NumericFieldName1;
		formDetailsPC.allowAttachmentCheck = true;

		SubmissionsScreen selectSubmissionTab  = commonScreen.selectSubmissions();
		TestValidation.IsFalse(selectSubmissionTab.error,
				"OPENED Submissions Tab", 
				"COULD not open Submissions Tab");

		boolean clearSubmission = selectSubmissionTab.clearVerifySubmission(false);
		TestValidation.IsTrue(clearSubmission, 
				"CLEARED Submissions", 
				"FAILED to Clear Submissions");

		SettingsScreen selectSettingsTab  = commonScreen.selectSettings();
		TestValidation.IsFalse(selectSettingsTab.error,
				"OPENED 'Settings' Tab", 
				"COULD not open 'Settings' Tab");

		boolean changeOfflineModeToggle = selectSettingsTab.changeOfflineModeToggle(true);
		TestValidation.IsTrue(changeOfflineModeToggle, 
				"CHANGED 'Offline Mode' toggle - ON", 
				"FAILED to change''Offline Mode' toggle - ON");

		InboxScreen selectInboxTab  = commonScreen.selectInbox();
		TestValidation.IsFalse(selectInboxTab.error,
				"OPENED Inbox Tab", 
				"COULD not open Inbox Tab");

		boolean openTask = selectInboxTab.selectOpenTask(offlineAttachmentsubmissionTaskName, false);
		TestValidation.IsTrue(openTask, 
				"SELECTED & opened task - "+offlineAttachmentsubmissionTaskName, 
				"FAILED to open task - "+offlineAttachmentsubmissionTaskName);

		boolean submitData = formview.fillDataInForm1(formDetailsPC);
		TestValidation.IsTrue(submitData, 
				"SUBMITTED data for form - "+offlineAttachmentsubmissionTaskName, 
				"FAILED to submit data for form - "+offlineAttachmentsubmissionTaskName);

		selectSubmissionTab  = commonScreen.selectSubmissions();
		TestValidation.IsFalse(selectSubmissionTab.error,
				"OPENED Submissions Tab", 
				"COULD not open Submissions Tab");

		boolean openSubmission = selectSubmissionTab.openSubmission(auditFormName1);
		TestValidation.IsTrue(openSubmission, 
				"OPENED Form submission - "+auditFormName1, 
				"FAILED to open form submission for form - "+auditFormName1);

		boolean verifyAttachment = selectSubmissionTab.verifySubmissionAttachment(NumericFieldName1,fileName);
		TestValidation.IsTrue(verifyAttachment, 
				"VERIFIED attachment - "+fileName+"' in the submission - "+auditFormName1, 
				"FAILED to verify attachment -"+fileName+"' in the submission for form - "+auditFormName1);

		selectSettingsTab  = commonScreen.selectSettings();
		TestValidation.IsFalse(selectSettingsTab.error,
				"OPENED 'Settings' Tab", 
				"COULD not open 'Settings' Tab");

		changeOfflineModeToggle = selectSettingsTab.changeOfflineModeToggle(false);
		TestValidation.IsTrue(changeOfflineModeToggle, 
				"CHANGED 'Offline Mode' toggle - OFF", 
				"FAILED to change''Offline Mode' toggle - OFF");

		threadsleep(5000);


	}

	@Test(description="Submissions || Submissions || Verify saved form submission having attachment in offline mode")
	public void TestCase_45318() {

		HashMap<String, List<String>> allFields = new LinkedHashMap<String, List<String>>();
		allFields.put(NumericFieldName1,Arrays.asList("2"));

		formDetailsPC = new FormDetailsPC();
		formDetailsPC.locationName = locationInstanceValue1;
		formDetailsPC.resourceName = resourceInstanceValue1;
		formDetailsPC.fieldDetails = allFields;
		formDetailsPC.selectLocationResource = false;
		formDetailsPC.propertyfieldName = NumericFieldName1;
		formDetailsPC.allowAttachmentCheck = true;

		SubmissionsScreen selectSubmissionTab  = commonScreen.selectSubmissions();
		TestValidation.IsFalse(selectSubmissionTab.error,
				"OPENED Submissions Tab", 
				"COULD not open Submissions Tab");

		boolean clearSubmission = selectSubmissionTab.clearVerifySubmission(false);
		TestValidation.IsTrue(clearSubmission, 
				"CLEARED Submissions", 
				"FAILED to Clear Submissions");

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

		boolean openForm = selectFormsTab.selectOpenForm(auditFormName1);
		TestValidation.IsTrue(openForm, 
				"SELECTED & opened form - "+auditFormName1, 
				"FAILED open form - "+auditFormName1);

		boolean selectLocationResource = formview.selectLocationResource(locationInstanceValue1, resourceInstanceValue1);
		TestValidation.IsTrue(selectLocationResource, 
				"SELECTED Location & Resource", 
				"FAILED to select Location & Resource");

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

		boolean submitData = formview.fillDataInForm1(formDetailsPC);
		TestValidation.IsTrue(submitData, 
				"SUBMITTED data for form - "+auditFormName1, 
				"FAILED to submit data for form - "+auditFormName1);

		selectSubmissionTab  = commonScreen.selectSubmissions();
		TestValidation.IsFalse(selectSubmissionTab.error,
				"OPENED Submissions Tab", 
				"COULD not open Submissions Tab");

		boolean openSubmission = selectSubmissionTab.openSubmission(auditFormName1);
		TestValidation.IsTrue(openSubmission, 
				"OPENED Form submission - "+auditFormName1, 
				"FAILED to open form submission for form - "+auditFormName1);

		boolean verifyAttachment = selectSubmissionTab.verifySubmissionAttachment(NumericFieldName1,fileName);
		TestValidation.IsTrue(verifyAttachment, 
				"VERIFIED attachment - "+fileName+"' in the submission - "+auditFormName1, 
				"FAILED to verify attachment -"+fileName+"' in the submission for form - "+auditFormName1);

		selectSettingsTab  = commonScreen.selectSettings();
		TestValidation.IsFalse(selectSettingsTab.error,
				"OPENED 'Settings' Tab", 
				"COULD not open 'Settings' Tab");

		changeOfflineModeToggle = selectSettingsTab.changeOfflineModeToggle(false);
		TestValidation.IsTrue(changeOfflineModeToggle, 
				"CHANGED 'Offline Mode' toggle - OFF", 
				"FAILED to change''Offline Mode' toggle - OFF");

		threadsleep(5000);
	}


	@AfterClass(alwaysRun = true)
	public void closeBrowser() throws InterruptedException {
		PCAppDriver.close();
	}

}
