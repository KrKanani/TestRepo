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
import com.project.safetychain.api.models.support.Support.Compliance;
import com.project.safetychain.api.models.support.Support.DPT_FIELD_TYPES;
import com.project.safetychain.api.models.support.Support.DPT_FORM_TYPES;
import com.project.safetychain.api.models.support.Support.Element_Types;
import com.project.safetychain.api.models.support.Support.FormFieldParams;
import com.project.safetychain.api.models.support.Support.FormFieldParamsCompliance;
import com.project.safetychain.api.models.support.Support.FormParams;
import com.project.safetychain.api.models.support.Support.RESOURCE_TYPES;
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
import com.project.safetychain.webapp.pageobjects.CommonPage;
import com.project.safetychain.webapp.pageobjects.DocumentManagementPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage;
import com.project.safetychain.webapp.pageobjects.LoginPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.COLUMNHEADER;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.COLUMNSETTING;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.FSQATAB;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FORMRESOURCETYPES;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.utilities.ApiUtils;
import com.project.utilities.CommonMethods;
import com.project.utilities.ControlActions;
import com.project.utilities.ControlActions_MobileApp;
import com.project.utilities.ControlActions_PCApp;

public class TCG_REG_Saved_SavedFormFlow2 extends TestBase{

	ApiUtils apiUtils = null;
	FormDesignerPage formDesignerPage;

	ControlActions_PCApp controlActionsPC;
	CommonScreen commonScreen;
	LoginScreen loginScreen;
	FormsScreen forms;
	FormViewScreen formview; 
	SavedScreen savedScreen;

	ControlActions controlActionsWeb;
	CommonPage mainPage;
	LoginPage login;
	DocumentManagementPage documentPage;
	FSQABrowserPage fsqaBrowser;

	FormDetailsPC formDetailsPC;

	public static String locationCategoryValue1;
	public static String locationInstanceValue1;
	public static String resourceCategoryValue1;
	public static String resourceInstanceValue1;

	public static String locInstId, resInstId;

	public static String checkFormName;
	String NumericFieldName1 = "Num 1";

	public static String auditFormName;

	public static String newUserName, newUserPassword;
	public static String workGroupName,taskname,taskname1;

	public static String noteText = "Test PC Form", documentTypeName, documentName = "upload.png";
	public static String filePath = System.getProperty("user.dir") + "\\test-data-files\\UploadDocuments\\"+documentName;
	public static String newpassword = "SafetyChain1234#";

	@BeforeClass(alwaysRun = true)
	public void groupInit() {

		apiUtils = new ApiUtils();

		TCG_FormCreation_Wrapper formCreationWrapper = new TCG_FormCreation_Wrapper();
		TCG_UserFlows_Wrapper userCreationWrapper = new TCG_UserFlows_Wrapper();
		TCG_TaskCreation_Wrapper taskCreationWrapper = new TCG_TaskCreation_Wrapper();

		locationCategoryValue1 = CommonMethods.dynamicText("PC_LCat");
		locationInstanceValue1 = CommonMethods.dynamicText("PC_LInst");
		resourceInstanceValue1 = CommonMethods.dynamicText("PC_RInst");
		resourceCategoryValue1 = CommonMethods.dynamicText("PC_RCat");

		checkFormName = CommonMethods.dynamicText("PC_SubmissionForm1");
		auditFormName = CommonMethods.dynamicText("PC_SubmissionForm2");

		newUserName =  CommonMethods.dynamicText("PC_SavedFormFlow2");
		newUserPassword = "Test";
		workGroupName = CommonMethods.dynamicText("PCWG");
		taskname = CommonMethods.dynamicText("CheckTask");
		taskname1 = CommonMethods.dynamicText("AuditTask");
		documentTypeName = CommonMethods.dynamicText("DocType");


		List<String> userList = Arrays.asList(pcAppUsername);	

		HashMap<String, List<String>> LocationMap = new HashMap<String, List<String>>();
		LocationMap.put(locationCategoryValue1, Arrays.asList(locationInstanceValue1));

		HashMap<String, List<String>> resCatMap = new HashMap<String, List<String>>();
		resCatMap.put(resourceCategoryValue1, Arrays.asList(resourceInstanceValue1));		

		HashMap<String, String> locResMap = formCreationWrapper.Resource_Location_CreationWrapper(resCatMap, LocationMap, true, userList,
				RESOURCE_TYPES.ITEMS);


		locInstId = locResMap.get(locationInstanceValue1);
		resInstId = locResMap.get(resourceInstanceValue1);


		FormFieldParams numericField = new FormFieldParams();
		numericField.DPTFieldType = DPT_FIELD_TYPES.NUMERIC;
		numericField.Name = NumericFieldName1;
		numericField.RepeatField = "true";
		numericField.ShowMinMax = "true";
		numericField.ShowTarget = "true";
		numericField.AllowAttachments = "true";

		String formId1 = apiUtils.getUUID();
		String formId = apiUtils.getUUID();


		formId1= apiUtils.getUUID();
		logInfo("FormId = " + formId1);
		FormParams fp1 = new FormParams();		
		fp1.FormId = formId1;
		fp1.FormName = checkFormName;
		logInfo("Form Name = " + checkFormName);
		fp1.type = DPT_FORM_TYPES.CHECK;
		fp1.ResourceType = RESOURCE_TYPES.ITEMS;
		fp1.ResourceCategoryNm = resourceCategoryValue1;
		fp1.ResourceInstanceNm = resourceInstanceValue1;
		fp1.formElements = Arrays.asList(numericField);
		fp1.ItemsResources = resCatMap;
		logInfo("fp1.formElements = " + fp1.formElements);	

		try {
			formCreationWrapper.API_Wrapper_Forms(fp1);
		} catch (InterruptedException e1) {
			logError("Error while '"+checkFormName+"' form creation");
			e1.printStackTrace();
		}	

		// ------------------------------------------------------------------------------------------------
		// API - Compliance Addition 

		HashMap<String, Compliance> complianceValuesMap = new HashMap<String, Compliance>();
		String resource = "Items > " + resourceCategoryValue1 + " > " + resourceInstanceValue1;
		Compliance compliance = new Compliance();
		compliance.Min = "10";
		compliance.Max = "100";
		compliance.Target = "50";
		compliance.UOM = "KG";
		compliance.Name = NumericFieldName1;
		compliance.fieldType = DPT_FIELD_TYPES.NUMERIC;

		complianceValuesMap.put(resource, compliance);
		complianceValuesMap.put("Default", compliance);

		logInfo("resource List " + resource);
		logInfo("Compliance Values " + complianceValuesMap);

		FormFieldParamsCompliance ffpc = new FormFieldParamsCompliance();
		ffpc.fieldNames = Arrays.asList(numericField.Name);
		ffpc.complianceList = complianceValuesMap;

		try {
			formCreationWrapper.API_Wrapper_Forms_Compliance(fp1, ffpc);
		} catch (InterruptedException e1) {
			logError("Error while '"+checkFormName+"' compliance setting");
			e1.printStackTrace();
		}

		// ------------------------------------------------------------------------------------------------
		// API - Form Creation - Audit

		Element_Types Section1 = new Element_Types();
		Section1.ElementType = DPT_FIELD_TYPES.SECTION;
		Section1.Name = "Section1";

		FormFieldParams Paragraph = new FormFieldParams();
		Paragraph.DPTFieldType = DPT_FIELD_TYPES.PARAGRAPH_TEXT;
		Paragraph.Name = "ParaGraph1";
		Paragraph.AllowAttachments = "true";

		FormFieldParams singleLineText = new FormFieldParams();
		singleLineText.DPTFieldType = DPT_FIELD_TYPES.SINGLE_LINE_TEXT;
		singleLineText.Name = "SingleLineText1";
		singleLineText.ShowHint = "true";
		singleLineText.Hint = "Single Line Text";

		Element_Types fieldGroup1 = new Element_Types();
		fieldGroup1.ElementType = DPT_FIELD_TYPES.FIELD_GROUP;
		fieldGroup1.Name = "QuestionGroup1";

		FormFieldParams SelectOne1 = new FormFieldParams();
		SelectOne1.DPTFieldType = DPT_FIELD_TYPES.SELECT_ONE;
		SelectOne1.Name = "SelectOne1";
		SelectOne1.SelectOptions = Arrays.asList("1,50", "2,100", "3,45", "4");
		SelectOne1.QuestionWeight = "100";

		fieldGroup1.formFieldParams = Arrays.asList(SelectOne1);

		Element_Types fieldGroup2 = new Element_Types();
		fieldGroup2.ElementType = DPT_FIELD_TYPES.FIELD_GROUP;
		fieldGroup2.Name = "QuestionGroup2";
		fieldGroup2.DependencyRule = "if(SelectOne1=2;Show)else(Hide)";

		FormFieldParams Numeric1 = new FormFieldParams();
		Numeric1.DPTFieldType = DPT_FIELD_TYPES.NUMERIC;
		Numeric1.Name = "Numeric1";

		fieldGroup2.formFieldParams = Arrays.asList(Numeric1);

		Section1.FieldGroupParams = Arrays.asList(fieldGroup1, fieldGroup2);
		Section1.formFieldParams = Arrays.asList(Paragraph, singleLineText);


		formId = apiUtils.getUUID();
		// Form details
		logInfo("FormId = " + formId);
		FormParams fp = new FormParams();
		fp.FormId = formId;
		fp.FormName = auditFormName;
		logInfo("Form Name = " + auditFormName);
		fp.type = DPT_FORM_TYPES.AUDIT;
		fp.ResourceType = RESOURCE_TYPES.ITEMS;
		fp.ResourceCategoryNm = resourceCategoryValue1;
		fp.ResourceInstanceNm = resourceInstanceValue1;
		fp.SectionElements = Arrays.asList(Section1);
		fp.ItemsResources = resCatMap;


		logInfo("fp.SectionElements = " + fp.SectionElements);

		try {
			formCreationWrapper.API_Wrapper_Forms(fp);
		} catch (InterruptedException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
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

		String date = ControlActions_MobileApp.AddDaystoToddaysDateInGivenFormat("MM/dd/YYYY HH:mm", 1);
		TaskParams tp = new TaskParams();
		tp.FormId = formId1;
		tp.DueBy = date;
		tp.FormName = checkFormName;
		tp.LocationInstanceNm = locationInstanceValue1;
		tp.ResourceInstanceNm = resourceInstanceValue1;
		tp.WorkGroupName = workGroupName;
		tp.LocationInstanceId = locInstId;
		tp.ResourceInstanceId = resInstId;
		tp.UserName= newUserName;
		tp.TaskName = taskname;
		try {
			taskCreationWrapper.create_Link_workGroup_Wrapper(tp);
			taskCreationWrapper.create_Task_Wrapper(tp);			
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {

			driver = launchbrowser();
			controlActionsWeb = new ControlActions(driver);
			mainPage = new CommonPage(driver);
			login = new LoginPage(driver);
			documentPage =  new DocumentManagementPage(driver);
			fsqaBrowser = new FSQABrowserPage(driver);
			formDesignerPage = new FormDesignerPage(driver);

			controlActionsWeb.getUrl(pcAppTenantURL);
			login.performLogin(newUserName, newUserPassword, newpassword);

			documentPage = mainPage.clickdocumentsmenu();
			if(documentPage.error) {
				TCGFailureMsg = "Could NOT Navigate to 'Documents'(DMS)";
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}	

			TestValidation.IsTrue(documentPage.docUploadinDocType(documentTypeName,filePath), 
					"ADDED document type - "+documentTypeName, 
					"Could NOT add document type - "+documentTypeName);

			if(mainPage.clickFSQABrowserMenu().error) {
				TCGFailureMsg = "Could NOT Navigate to 'FSQA Browser";
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}	

			boolean itemsFormTab = fsqaBrowser.selectResourceDropDownandNavigate(FORMRESOURCETYPES.ITEMS,FSQATAB.FORMS);
			if(!itemsFormTab) {
				TCGFailureMsg = "FAILED to navigate to Items - FSQABrowser > Forms Tab";
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}

			boolean applyfilter = fsqaBrowser.openAndApplySettingsForColumn(COLUMNHEADER.FORMNAME, COLUMNSETTING.FILTER,auditFormName);
			if(!applyfilter) {
				TCGFailureMsg = "FAILED to apply filter on " + COLUMNHEADER.FORMNAME;
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}

			fsqaBrowser.editSelectForm(auditFormName);
			if(!applyfilter) {
				TCGFailureMsg = "FAILED to open form - "+auditFormName+" in edit mode ";
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}

			if(!formDesignerPage.addRelatedDocument(documentTypeName, documentName)) {
				TCGFailureMsg = "Could NOT add related documents in form - " + auditFormName;
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}

			TestValidation.IsTrue(formDesignerPage.releaseForm(auditFormName), 
					"RELEASED form - " +auditFormName+" with form elements from Web App", 
					"Could NOT release form - " + auditFormName+" with form elements from Web App");

		}catch(Exception e){
			e.printStackTrace();
		}

		try {

			PCAppDriver = launchPCApp();

			controlActionsPC = new ControlActions_PCApp(PCAppDriver);
			forms = new FormsScreen(PCAppDriver);
			formview = new FormViewScreen(PCAppDriver);
			savedScreen = new SavedScreen(PCAppDriver);
			loginScreen = new LoginScreen(PCAppDriver);

			commonScreen = loginScreen.Login(pcAppTenantname, newUserName, newpassword);
			if(commonScreen.error) {
				TCGFailureMsg = "COULD not login to the application";
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);	
			}

		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Test(description="Saved || Verify Delete button should not shown after performing SUBMIT REPEAT on saved form")
	public void TestCase_48712() {

		FormsScreen selectFormsTab  = commonScreen.selectForms();
		TestValidation.IsFalse(selectFormsTab.error,
				"OPENED Forms Tab", 
				"COULD not open Forms Tab");

		boolean openForm = selectFormsTab.selectOpenForm(checkFormName);
		TestValidation.IsTrue(openForm, 
				"SELECTED & opened form - "+checkFormName, 
				"FAILED open form - "+checkFormName);

		HashMap<String, List<String>> allFields = new LinkedHashMap<String, List<String>>();
		allFields.put(NumericFieldName1,Arrays.asList("2"));

		formDetailsPC = new FormDetailsPC();
		formDetailsPC.locationName = locationInstanceValue1;
		formDetailsPC.resourceName = resourceInstanceValue1;
		formDetailsPC.fieldDetails = allFields;
		formDetailsPC.selectLocationResource = false;
		formDetailsPC.isSubmit = false;


		boolean submitData = formview.fillDataInForm1(formDetailsPC);
		TestValidation.IsTrue(submitData, 
				"SUBMITTED data for form - "+checkFormName, 
				"FAILED to submit data for form - "+checkFormName);

		boolean saveForm = formview.saveForm();
		TestValidation.IsTrue(saveForm, 
				"SAVED form - "+checkFormName, 
				"FAILED to save the form - "+checkFormName);

		savedScreen = commonScreen.selectSaved();
		TestValidation.IsFalse(savedScreen.error,
				"OPENED Saved Tab", 
				"COULD not open Saved Tab");

		boolean openSavedForm = savedScreen.selectOpenForm(checkFormName);
		TestValidation.IsTrue(openSavedForm, 
				"SELECTED & opened saved form - "+checkFormName, 
				"FAILED to open saved form - "+checkFormName);


		boolean verify = formview.verifyDeleteBtn();
		TestValidation.IsTrue(verify,
				"Verified delete button", 
				"FAILED to verify selete buton");


	}

	@Test(description="Saved || Verify saved form save having attachment in offline mode")
	public void TestCase_48607() {

		try {

			SettingsScreen settings = commonScreen.selectSettings();
			TestValidation.IsFalse(settings.error,
					"OPENED Settings Tab", 
					"COULD not open Settings Tab");	

			boolean toggle = settings.changeOfflineModeToggle(true);
			TestValidation.IsTrue(toggle,
					"Switched on offline mode",
					"COULD not switch on offline mode");

			FormsScreen selectFormsTab  = commonScreen.selectForms();
			TestValidation.IsFalse(selectFormsTab.error,
					"OPENED Forms Tab", 
					"COULD not open Forms Tab");

			boolean openForm = selectFormsTab.selectOpenForm(checkFormName);
			TestValidation.IsTrue(openForm, 
					"SELECTED & opened form - "+checkFormName, 
					"FAILED open form - "+checkFormName);

			HashMap<String, List<String>> allFields = new LinkedHashMap<String, List<String>>();
			allFields.put(NumericFieldName1,Arrays.asList("2"));

			formDetailsPC = new FormDetailsPC();
			formDetailsPC.locationName = locationInstanceValue1;
			formDetailsPC.resourceName = resourceInstanceValue1;
			formDetailsPC.fieldDetails = allFields;
			formDetailsPC.selectLocationResource = false;
			formDetailsPC.isSubmit = false;


			boolean submitData = formview.fillDataInForm1(formDetailsPC);
			TestValidation.IsTrue(submitData, 
					"SUBMITTED data for form - "+checkFormName, 
					"FAILED to submit data for form - "+checkFormName);

			boolean addFormLevelAttachment = formview.addFormLevelAttachment();
			TestValidation.IsTrue(addFormLevelAttachment, 
					"ADDED attachment at form level", 
					"FAILED to add attachment at form level");

			boolean saveForm = formview.saveForm();
			TestValidation.IsTrue(saveForm, 
					"SAVED form - "+checkFormName, 
					"FAILED to save the form - "+checkFormName);

			savedScreen = commonScreen.selectSaved();
			TestValidation.IsFalse(savedScreen.error,
					"OPENED Saved Tab", 
					"COULD not open Saved Tab");

			boolean openSavedForm = savedScreen.selectOpenForm(checkFormName);
			TestValidation.IsTrue(openSavedForm, 
					"SELECTED & opened saved form - "+checkFormName, 
					"FAILED to open saved form - "+checkFormName);


			boolean addFieldLevelAttachment = formview.addFieldLevelAttachment();
			TestValidation.IsTrue(addFieldLevelAttachment, 
					"ADDED attachment at field level", 
					"FAILED to add attachment at field level");

			boolean saveForm1 = formview.saveForm();
			TestValidation.IsTrue(saveForm1, 
					"SAVED form - "+checkFormName, 
					"FAILED to save the form - "+checkFormName);
		}

		finally {

			SettingsScreen settings = commonScreen.selectSettings();
			TestValidation.IsFalse(settings.error,
					"OPENED Settings Tab", 
					"COULD not open Settings Tab");	

			boolean toggle = settings.changeOfflineModeToggle(false);
			TestValidation.IsTrue(toggle,
					"Switched on offline mode",
					"COULD not switch on offline mode");

		}		

	}

	@Test(description="Saved || Verify saved form save having attachment in online mode")
	public void TestCase_48604() {

		FormsScreen selectFormsTab  = commonScreen.selectForms();
		TestValidation.IsFalse(selectFormsTab.error,
				"OPENED Forms Tab", 
				"COULD not open Forms Tab");

		boolean openForm = selectFormsTab.selectOpenForm(checkFormName);
		TestValidation.IsTrue(openForm, 
				"SELECTED & opened form - "+checkFormName, 
				"FAILED open form - "+checkFormName);

		HashMap<String, List<String>> allFields = new LinkedHashMap<String, List<String>>();
		allFields.put(NumericFieldName1,Arrays.asList("2"));

		formDetailsPC = new FormDetailsPC();
		formDetailsPC.locationName = locationInstanceValue1;
		formDetailsPC.resourceName = resourceInstanceValue1;
		formDetailsPC.fieldDetails = allFields;
		formDetailsPC.selectLocationResource = false;
		formDetailsPC.isSubmit = false;


		boolean submitData = formview.fillDataInForm1(formDetailsPC);
		TestValidation.IsTrue(submitData, 
				"SUBMITTED data for form - "+checkFormName, 
				"FAILED to submit data for form - "+checkFormName);

		boolean addFormLevelAttachment = formview.addFormLevelAttachment();
		TestValidation.IsTrue(addFormLevelAttachment, 
				"ADDED attachment at form level", 
				"FAILED to add attachment at form level");

		boolean saveForm = formview.saveForm();
		TestValidation.IsTrue(saveForm, 
				"SAVED form - "+checkFormName, 
				"FAILED to save the form - "+checkFormName);

		savedScreen = commonScreen.selectSaved();
		TestValidation.IsFalse(savedScreen.error,
				"OPENED Saved Tab", 
				"COULD not open Saved Tab");

		boolean openSavedForm = savedScreen.selectOpenForm(checkFormName);
		TestValidation.IsTrue(openSavedForm, 
				"SELECTED & opened saved form - "+checkFormName, 
				"FAILED to open saved form - "+checkFormName);


		boolean addFieldLevelAttachment = formview.addFieldLevelAttachment();
		TestValidation.IsTrue(addFieldLevelAttachment, 
				"ADDED attachment at field level", 
				"FAILED to add attachment at field level");

		boolean saveForm1 = formview.saveForm();
		TestValidation.IsTrue(saveForm1, 
				"SAVED form - "+checkFormName, 
				"FAILED to save the form - "+checkFormName);
	}

	@Test(description="Saved || Verify form save having attachment in offline mode")
	public void TestCase_48605() {

		try {

			SettingsScreen settings = commonScreen.selectSettings();
			TestValidation.IsFalse(settings.error,
					"OPENED Settings Tab", 
					"COULD not open Settings Tab");	

			boolean toggle = settings.changeOfflineModeToggle(true);
			TestValidation.IsTrue(toggle,
					"Switched on offline mode",
					"COULD not switch on offline mode");

			FormsScreen selectFormsTab  = commonScreen.selectForms();
			TestValidation.IsFalse(selectFormsTab.error,
					"OPENED Forms Tab", 
					"COULD not open Forms Tab");

			boolean openForm = selectFormsTab.selectOpenForm(auditFormName);
			TestValidation.IsTrue(openForm, 
					"SELECTED & opened form - "+auditFormName, 
					"FAILED open form - "+auditFormName);

			HashMap<String, List<String>> allFields = new LinkedHashMap<String, List<String>>();
			allFields.put("ParaGraph1",Arrays.asList("Automation of Paragraph"));
			allFields.put("SingleLineText1",Arrays.asList("Automation of Single Line Text"));
			allFields.put("SelectOne1",Arrays.asList("2"));
			allFields.put("Numeric1",Arrays.asList("12.5"));
			formDetailsPC = new FormDetailsPC();
			formDetailsPC.selectLocationResource = false;
			formDetailsPC.locationName = locationInstanceValue1;
			formDetailsPC.resourceName = resourceInstanceValue1;
			formDetailsPC.fieldDetails = allFields;
			formDetailsPC.isSubmit = false;

			boolean submitData = formview.fillDataInForm1(formDetailsPC);
			TestValidation.IsTrue(submitData, 
					"SUBMITTED data for form - "+auditFormName, 
					"FAILED to submit data for form - "+auditFormName);

			boolean addFormLevelAttachment = formview.addFormLevelAttachment();
			TestValidation.IsTrue(addFormLevelAttachment, 
					"ADDED attachment at form level", 
					"FAILED to add attachment at form level");

			boolean saveForm = formview.saveForm();
			TestValidation.IsTrue(saveForm, 
					"SAVED form - "+auditFormName, 
					"FAILED to save the form - "+auditFormName);

			savedScreen = commonScreen.selectSaved();
			TestValidation.IsFalse(savedScreen.error,
					"OPENED Saved Tab", 
					"COULD not open Saved Tab");

			boolean openSavedForm = savedScreen.selectOpenForm(auditFormName);
			TestValidation.IsTrue(openSavedForm, 
					"SELECTED & opened saved form - "+auditFormName, 
					"FAILED to open saved form - "+auditFormName);


			boolean addFieldLevelAttachment = formview.addFieldLevelAttachment();
			TestValidation.IsTrue(addFieldLevelAttachment, 
					"ADDED attachment at field level", 
					"FAILED to add attachment at field level");

			boolean saveForm1 = formview.saveForm();
			TestValidation.IsTrue(saveForm1, 
					"SAVED form - "+auditFormName, 
					"FAILED to save the form - "+auditFormName);
		}

		finally {

			SettingsScreen settings = commonScreen.selectSettings();
			TestValidation.IsFalse(settings.error,
					"OPENED Settings Tab", 
					"COULD not open Settings Tab");	

			boolean toggle = settings.changeOfflineModeToggle(false);
			TestValidation.IsTrue(toggle,
					"Switched on offline mode",
					"COULD not switch on offline mode");

		}		

	}


	@Test(description="Saved || Verify task form save having attachment in offline mode")
	public void TestCase_48606() {
		try {

			SettingsScreen settings = commonScreen.selectSettings();
			TestValidation.IsFalse(settings.error,
					"OPENED Settings Tab", 
					"COULD not open Settings Tab");	

			boolean toggle = settings.changeOfflineModeToggle(true);
			TestValidation.IsTrue(toggle,
					"Switched on offline mode",
					"COULD not switch on offline mode");

			InboxScreen inbox = commonScreen.selectInbox();
			boolean openTask = inbox.selectOpenTask(taskname, false);
			TestValidation.IsTrue(openTask, 
					"SELECTED & opened task - "+taskname, 
					"FAILED to open task - "+taskname);

			HashMap<String, List<String>> allFields = new LinkedHashMap<String, List<String>>();
			allFields.put(NumericFieldName1,Arrays.asList("2"));

			formDetailsPC = new FormDetailsPC();
			formDetailsPC.locationName = locationInstanceValue1;
			formDetailsPC.resourceName = resourceInstanceValue1;
			formDetailsPC.fieldDetails = allFields;
			formDetailsPC.selectLocationResource = false;
			formDetailsPC.isSubmit = false;


			boolean submitData = formview.fillDataInForm1(formDetailsPC);
			TestValidation.IsTrue(submitData, 
					"SUBMITTED data for form - "+checkFormName, 
					"FAILED to submit data for form - "+checkFormName);

			boolean addFormLevelAttachment = formview.addFormLevelAttachment();
			TestValidation.IsTrue(addFormLevelAttachment, 
					"ADDED attachment at form level", 
					"FAILED to add attachment at form level");

			boolean saveTask = inbox.saveTask();
			TestValidation.IsTrue(saveTask, 
					"SAVED task - "+taskname, 
					"FAILED to save the task - "+taskname);

			savedScreen = commonScreen.selectSaved();
			TestValidation.IsFalse(savedScreen.error,
					"OPENED Saved Tab", 
					"COULD not open Saved Tab");

			boolean openSavedForm = savedScreen.selectOpenForm(checkFormName);
			TestValidation.IsTrue(openSavedForm, 
					"SELECTED & opened saved form - "+checkFormName, 
					"FAILED to open saved form - "+checkFormName);


			boolean addFieldLevelAttachment = formview.addFieldLevelAttachment();
			TestValidation.IsTrue(addFieldLevelAttachment, 
					"ADDED attachment at field level", 
					"FAILED to add attachment at field level");

			boolean saveForm1 = formview.saveForm();
			TestValidation.IsTrue(saveForm1, 
					"SAVED form - "+checkFormName, 
					"FAILED to save the form - "+checkFormName);
		}

		finally {

			SettingsScreen settings = commonScreen.selectSettings();
			TestValidation.IsFalse(settings.error,
					"OPENED Settings Tab", 
					"COULD not open Settings Tab");	

			boolean toggle = settings.changeOfflineModeToggle(false);
			TestValidation.IsTrue(toggle,
					"Switched on offline mode",
					"COULD not switch on offline mode");

		}		

	}

	@Test(description="Saved || Verify task save having attachment in online mode")
	public void TestCase_48603() {

		InboxScreen inbox = commonScreen.selectInbox();
		boolean openTask = inbox.selectOpenTask(taskname, false);
		TestValidation.IsTrue(openTask, 
				"SELECTED & opened task - "+taskname, 
				"FAILED to open task - "+taskname);

		HashMap<String, List<String>> allFields = new LinkedHashMap<String, List<String>>();
		allFields.put(NumericFieldName1,Arrays.asList("2"));

		formDetailsPC = new FormDetailsPC();
		formDetailsPC.locationName = locationInstanceValue1;
		formDetailsPC.resourceName = resourceInstanceValue1;
		formDetailsPC.fieldDetails = allFields;
		formDetailsPC.selectLocationResource = false;
		formDetailsPC.isSubmit = false;


		boolean submitData = formview.fillDataInForm1(formDetailsPC);
		TestValidation.IsTrue(submitData, 
				"SUBMITTED data for form - "+checkFormName, 
				"FAILED to submit data for form - "+checkFormName);

		boolean addFormLevelAttachment = formview.addFormLevelAttachment();
		TestValidation.IsTrue(addFormLevelAttachment, 
				"ADDED attachment at form level", 
				"FAILED to add attachment at form level");

		boolean saveTask = inbox.saveTask();
		TestValidation.IsTrue(saveTask, 
				"SAVED task - "+taskname, 
				"FAILED to save the task - "+taskname);

		savedScreen = commonScreen.selectSaved();
		TestValidation.IsFalse(savedScreen.error,
				"OPENED Saved Tab", 
				"COULD not open Saved Tab");

		boolean openSavedForm = savedScreen.selectOpenForm(checkFormName);
		TestValidation.IsTrue(openSavedForm, 
				"SELECTED & opened saved form - "+checkFormName, 
				"FAILED to open saved form - "+checkFormName);


		boolean addFieldLevelAttachment = formview.addFieldLevelAttachment();
		TestValidation.IsTrue(addFieldLevelAttachment, 
				"ADDED attachment at field level", 
				"FAILED to add attachment at field level");

		boolean saveForm1 = formview.saveForm();
		TestValidation.IsTrue(saveForm1, 
				"SAVED form - "+checkFormName, 
				"FAILED to save the form - "+checkFormName);
	}	

	@Test(description="Saved || Verify form save having attachment in online mode")
	public void TestCase_48602() {

		FormsScreen selectFormsTab  = commonScreen.selectForms();
		TestValidation.IsFalse(selectFormsTab.error,
				"OPENED Forms Tab", 
				"COULD not open Forms Tab");

		boolean openForm = selectFormsTab.selectOpenForm(auditFormName);
		TestValidation.IsTrue(openForm, 
				"SELECTED & opened form - "+auditFormName, 
				"FAILED open form - "+auditFormName);

		HashMap<String, List<String>> allFields = new LinkedHashMap<String, List<String>>();
		allFields.put("ParaGraph1",Arrays.asList("Automation of Paragraph"));
		allFields.put("SingleLineText1",Arrays.asList("Automation of Single Line Text"));
		allFields.put("SelectOne1",Arrays.asList("2"));
		allFields.put("Numeric1",Arrays.asList("12.5"));
		formDetailsPC = new FormDetailsPC();
		formDetailsPC.selectLocationResource = false;
		formDetailsPC.locationName = locationInstanceValue1;
		formDetailsPC.resourceName = resourceInstanceValue1;
		formDetailsPC.fieldDetails = allFields;
		formDetailsPC.isSubmit = false;

		boolean submitData = formview.fillDataInForm1(formDetailsPC);
		TestValidation.IsTrue(submitData, 
				"SUBMITTED data for form - "+auditFormName, 
				"FAILED to submit data for form - "+auditFormName);

		boolean addFormLevelAttachment = formview.addFormLevelAttachment();
		TestValidation.IsTrue(addFormLevelAttachment, 
				"ADDED attachment at form level", 
				"FAILED to add attachment at form level");

		boolean saveForm = formview.saveForm();
		TestValidation.IsTrue(saveForm, 
				"SAVED form - "+auditFormName, 
				"FAILED to save the form - "+auditFormName);

		savedScreen = commonScreen.selectSaved();
		TestValidation.IsFalse(savedScreen.error,
				"OPENED Saved Tab", 
				"COULD not open Saved Tab");

		boolean openSavedForm = savedScreen.selectOpenForm(auditFormName);
		TestValidation.IsTrue(openSavedForm, 
				"SELECTED & opened saved form - "+auditFormName, 
				"FAILED to open saved form - "+auditFormName);

		boolean addFieldLevelAttachment = formview.addFieldLevelAttachment();
		TestValidation.IsTrue(addFieldLevelAttachment, 
				"ADDED attachment at field level", 
				"FAILED to add attachment at field level");

		boolean saveForm1 = formview.saveForm();
		TestValidation.IsTrue(saveForm1, 
				"SAVED form - "+auditFormName, 
				"FAILED to save the form - "+auditFormName);

	}


	@AfterClass(alwaysRun = true)
	public void closeBrowser() throws InterruptedException {
		PCAppDriver.close();
	}


}
