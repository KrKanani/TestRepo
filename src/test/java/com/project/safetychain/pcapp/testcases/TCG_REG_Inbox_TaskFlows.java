package com.project.safetychain.pcapp.testcases;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.testng.SkipException;
import com.project.safetychain.api.models.support.TCG_FormCreation_Wrapper;
import com.project.safetychain.api.models.support.TCG_TaskCreation_Wrapper;
import com.project.safetychain.api.models.support.TCG_UserFlows_Wrapper;
import com.project.safetychain.api.models.TaskFlow.TaskDetails;
import com.project.safetychain.api.models.support.Support.AGGREGATE_FUNC_TYPES;
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
import com.project.safetychain.apiproject.models.Auth;
import com.project.safetychain.pcapp.pageobjects.CommonScreen;
import com.project.safetychain.pcapp.pageobjects.FormViewScreen;
import com.project.safetychain.pcapp.pageobjects.InboxScreen;
import com.project.safetychain.pcapp.pageobjects.InboxScreen.Action;
import com.project.safetychain.pcapp.pageobjects.InboxScreen.Status;
import com.project.safetychain.pcapp.pageobjects.LoginScreen;
import com.project.safetychain.pcapp.pageobjects.SavedScreen;
import com.project.safetychain.pcapp.pageobjects.SettingsScreen;
import com.project.safetychain.pcapp.pageobjects.SubmissionsScreen;
import com.project.safetychain.pcapp.pageobjects.FormViewScreen.FormDetailsPC;
import com.project.safetychain.webapp.pageobjects.FSQABrowserFormsPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserTaskPage;
import com.project.safetychain.webapp.pageobjects.HomePage;
import com.project.safetychain.webapp.pageobjects.InboxPage;
import com.project.safetychain.webapp.pageobjects.LoginPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.COLUMNHEADER;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.COLUMNSETTING;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.FSQARESOURCE;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.FSQATAB;
import com.project.safetychain.webapp.pageobjects.FSQABrowserRecordsPage;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FORMRESOURCETYPES;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.utilities.ApiUtils;
import com.project.utilities.CommonMethods;
import com.project.utilities.ControlActions;
import com.project.utilities.ControlActions_PCApp;
import com.project.utilities.DateTime;

public class TCG_REG_Inbox_TaskFlows extends TestBase{

	ControlActions_PCApp controlActionsPC;
	CommonScreen commonScreen;
	InboxScreen inboxScreen;
	FormViewScreen formview; 
	FormDetailsPC formDetailsPC;
	SavedScreen savedScreen;
	SubmissionsScreen submissionScreen;

	ApiUtils apiUtils =  null;
	Auth auth = null;
	TCG_FormCreation_Wrapper formCreationWrapper = null;
	TCG_TaskCreation_Wrapper taskCreationWrapper = null;
	TCG_UserFlows_Wrapper userWrapper = null;

	LoginPage login;
	HomePage home;
	FSQABrowserPage fbp;
	FSQABrowserRecordsPage fbrp;
	FSQABrowserTaskPage fsqaTasksPage;
	ControlActions controlActions;
	DateTime datetime;
	InboxPage inbox;
	FSQABrowserFormsPage fbfp;

	static int totalTaskCount = 7;

	String NumericFieldName = "Num 1";

	public static String resourceCategoryType;

	public static String pastDueTask, dueTodayTask, dueLaterTask, noDueDateTask,refreshTask, recallTask, reassignTask;
	public static String auditSubmitTask, newWgTask;
	public static String workGroupName,newWgName1, newWgName2;
	public static String checkFormTask1, checkFormTask2, questionnaireFormTask1, questionnaireFormTask2, auditFormTask1, auditFormTask2;
	public static String checkFormName1,checkFormName2, questionnaireFormName,auditFormName;
	public static String chkFreeTxt, ParaTxt, chkSelectOne, SelectMultiple, Numeric, chkDate, chkTime, chkDateTime, AggSumRange;

	public static String NumericSRC1, NumericSRC2;

	public static String locationCategoryValue;
	public static String resourceCategoryValue;
	public static String resourceInstanceValue,resourceInstanceValue1;
	public static String locationInstanceValue;

	public static String formId,formId1,formId2;
	public static String locInstId,ResInstId,ResInstId1;
	public static String workgroupId,newWgId;
	public static String newpassword,username,password,changedpassword;
	public static String taskId,ReassigntaskId;
	public static HashMap<String, List<String>> resCatMap;

	@BeforeClass(alwaysRun = true)
	public void groupInit() throws InterruptedException {

		auth = new Auth();
		auth.setAccessToken();

		resourceCategoryType = FORMRESOURCETYPES.EQUIPMENT;

		pastDueTask = CommonMethods.dynamicText("PastDue");
		dueTodayTask = CommonMethods.dynamicText("DueToday");
		dueLaterTask = CommonMethods.dynamicText("DueLater");
		noDueDateTask = CommonMethods.dynamicText("NoDue");
		auditSubmitTask = CommonMethods.dynamicText("AuditSubmit");
		refreshTask = CommonMethods.dynamicText("Refresh");
		recallTask = CommonMethods.dynamicText("Recall");
		reassignTask = CommonMethods.dynamicText("Reassign");

		checkFormName1 = CommonMethods.dynamicText("PC_CheckForm1");
		checkFormName2 = CommonMethods.dynamicText("PC_CheckForm2");
		questionnaireFormName = CommonMethods.dynamicText("PC_QuestForm");
		auditFormName = CommonMethods.dynamicText("PC_AuditForm");

		checkFormTask1 = CommonMethods.dynamicText("CheckTask1");
		checkFormTask2 = CommonMethods.dynamicText("CheckTask2");

		questionnaireFormTask1 = CommonMethods.dynamicText("QuestTask1");
		questionnaireFormTask2 = CommonMethods.dynamicText("QuestTask2");

		auditFormTask1 = CommonMethods.dynamicText("AuditTask1");
		auditFormTask2 = CommonMethods.dynamicText("AuditTask2");

		locationCategoryValue = CommonMethods.dynamicText("Loc_Cat");
		resourceCategoryValue = CommonMethods.dynamicText("Equip_Cat");
		resourceInstanceValue = CommonMethods.dynamicText("Equip_Inst");
		resourceInstanceValue1 = CommonMethods.dynamicText("Equip_Inst1");
		locationInstanceValue = CommonMethods.dynamicText("Loc_Inst");

		workGroupName = CommonMethods.dynamicText("WG");
		newWgName1 = CommonMethods.dynamicText("NWG1");
		newWgName2 = CommonMethods.dynamicText("NWG2");
		newWgTask = CommonMethods.dynamicText("NewWgTask");
		password = "SafetyChain123#";
		newpassword = "SafetyChain1234#";
		changedpassword = "SafetyChain12345#";
		datetime = new DateTime();
		username = CommonMethods.dynamicText("PC_InboxFlowUser");

		// Field Short Names
		chkFreeTxt = "FreeText";
		ParaTxt = "Paragraph";
		chkSelectOne = "SelectOne";
		Numeric = "Numeric";
		chkDate = "Date";
		chkTime = "Time";
		chkDateTime = "Date&Time";
		SelectMultiple = "MultiSelect";	

		NumericSRC1 = "Numeric F1";
		NumericSRC2 = "Numeric F2";
		AggSumRange = "Aggregate Sum Range";

		// ------------------------------------------------------------------------------------------------
		// API Implementation

		apiUtils =  new ApiUtils();
		formCreationWrapper = new TCG_FormCreation_Wrapper();
		taskCreationWrapper = new TCG_TaskCreation_Wrapper();
		userWrapper = new TCG_UserFlows_Wrapper();

		// ------------------------------------------------------------------------------------------------
		// API - User , Location & Resource Creation and Linking

		List<String> userList = Arrays.asList(pcAppUsername);	

		HashMap<String, List<String>> LocationMap = new HashMap<String, List<String>>();
		LocationMap.put(locationCategoryValue, Arrays.asList(locationInstanceValue));

		resCatMap = new HashMap<String, List<String>>();
		resCatMap.put(resourceCategoryValue, Arrays.asList(resourceInstanceValue,resourceInstanceValue1));		

		HashMap<String, String> locResMap = formCreationWrapper.Resource_Location_CreationWrapper(resCatMap, LocationMap, true, userList,
				RESOURCE_TYPES.EQUIPMENT);

		locInstId = locResMap.get(locationInstanceValue);
		ResInstId = locResMap.get(resourceInstanceValue);
		ResInstId1 = locResMap.get(resourceInstanceValue1);

		// ------------------------------------------------------------------------------------------------
		// User Creation

		UserParams user = new UserParams();
		user.ClearPassword = password;
		user.Email = "pcappinboxuser@pc.com";
		user.FirstName = username;
		user.LastName = "User";
		user.LocationCat = locationCategoryValue;
		user.LocationNmIds = Arrays.asList(locationInstanceValue);
		user.Roles = Arrays.asList("Superadmin");
		user.TimeZone = "Republic of India";
		user.Username = username;
		user.Pin = "12345";
		user.EmployeeId = "X12345";

		userWrapper.createUser_Wrapper(user);		

		// ------------------------------------------------------------------------------------------------
		// API - Form Creation - Audit

		Element_Types Section1 = new Element_Types();
		Section1.ElementType = DPT_FIELD_TYPES.SECTION;
		Section1.Name = "Section1";

		FormFieldParams Paragraph = new FormFieldParams();
		Paragraph.DPTFieldType = DPT_FIELD_TYPES.PARAGRAPH_TEXT;
		Paragraph.Name = "ParaGraph1";

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
		fp.ResourceType = RESOURCE_TYPES.EQUIPMENT;
		fp.ResourceCategoryNm = resourceCategoryValue;
		fp.ResourceInstanceNm = resourceInstanceValue;
		fp.SectionElements = Arrays.asList(Section1);
		fp.EquipmentResources = resCatMap;

		logInfo("fp.SectionElements = " + fp.SectionElements);

		formCreationWrapper.API_Wrapper_Forms(fp);

		// ------------------------------------------------------------------------------------------------
		// API - Form Creation - Check


		FormFieldParams numericField = new FormFieldParams();
		numericField.DPTFieldType = DPT_FIELD_TYPES.NUMERIC;
		numericField.Name = "Number";
		numericField.ShowMinMax = "true";
		numericField.ShowTarget = "true";


		formId1= apiUtils.getUUID();
		logInfo("FormId = " + formId1);
		FormParams fp1 = new FormParams();		
		fp1.FormId = formId1;
		fp1.FormName = checkFormName1;
		logInfo("Form Name = " + checkFormName1);
		fp1.type = DPT_FORM_TYPES.CHECK;
		fp1.ResourceType = RESOURCE_TYPES.EQUIPMENT;
		fp1.ResourceCategoryNm = resourceCategoryValue;
		fp1.ResourceInstanceNm = resourceInstanceValue;
		fp1.formElements = Arrays.asList(numericField);
		fp1.EquipmentResources = resCatMap;
		logInfo("fp1.formElements = " + fp1.formElements);	

		formCreationWrapper.API_Wrapper_Forms(fp1);		

		// ------------------------------------------------------------------------------------------------
		// API - Compliance Addition 

		HashMap<String, Compliance> complianceValuesMap = new HashMap<String, Compliance>();
		String resource = "Equipment > " + resourceCategoryValue + " > " + resourceInstanceValue;
		Compliance compliance = new Compliance();
		compliance.Min = "10";
		compliance.Max = "100";
		compliance.Target = "50";
		compliance.UOM = "KG";
		compliance.Name = numericField.Name;
		compliance.fieldType = DPT_FIELD_TYPES.NUMERIC;

		complianceValuesMap.put(resource, compliance);
		complianceValuesMap.put("Default", compliance);

		logInfo("resource List " + resource);
		logInfo("Compliance Values " + complianceValuesMap);

		FormFieldParamsCompliance ffpc = new FormFieldParamsCompliance();
		ffpc.fieldNames = Arrays.asList(numericField.Name);
		ffpc.complianceList = complianceValuesMap;

		formCreationWrapper.API_Wrapper_Forms_Compliance(fp1, ffpc);

		// ------------------------------------------------------------------------------------------------
		// API - Form Creation - Questionnaire

		FormFieldParams numericField2 = new FormFieldParams();
		numericField2.DPTFieldType = DPT_FIELD_TYPES.NUMERIC;
		numericField2.Name = Numeric;
		numericField2.ShowHint = "true";
		numericField2.Hint = "Numeric";
		numericField2.AllowAttachments = "true";
		numericField2.RepeatField = "true";
		numericField2.ShowMinMax = "true";
		numericField2.ShowTarget = "true";

		formId2 = apiUtils.getUUID();
		logInfo("FormId = " + formId2);
		FormParams fp2 = new FormParams();
		fp2.FormId = formId2;
		fp2.FormName = questionnaireFormName;
		logInfo("Form Name = " + questionnaireFormName);
		fp2.type = DPT_FORM_TYPES.QUESTIONNAIRE;
		fp2.ResourceType = RESOURCE_TYPES.EQUIPMENT;
		fp2.EquipmentResources = resCatMap;
		fp2.ResourceCategoryNm = resourceCategoryValue;
		fp2.ResourceInstanceNm = resourceInstanceValue;
		fp2.formElements = Arrays.asList(numericField2);
		logInfo("fp2.formElements = " + fp2.formElements);

		formCreationWrapper.API_Wrapper_Forms(fp2);

		// Task Creation using audit form
		String date = datetime.getDateTime("Day", 1, 0, 0, false, false);

		TaskParams tp = new TaskParams();
		tp.FormId = formId;
		tp.DueBy = date;
		tp.FormName = auditFormName;
		tp.LocationInstanceNm = locationInstanceValue;
		tp.ResourceInstanceNm = resourceInstanceValue;
		tp.WorkGroupName = workGroupName;
		tp.LocationInstanceId = locInstId;
		tp.ResourceInstanceId = ResInstId;
		tp.UserName= username;

		workgroupId = taskCreationWrapper.create_Link_workGroup_Wrapper(tp);

		//		tp.WorkGroupName = newWgName1;
		//		newWgId = taskCreationWrapper.create_Link_workGroup_Wrapper(tp);					

		List<String> taskName = new ArrayList<String>();
		taskName.add(auditFormTask1);
		taskName.add(auditSubmitTask);

		for (int i = 0; i < taskName.size(); i++) {
			tp.TaskName = taskName.get(i);
			taskCreationWrapper.create_Task_Wrapper(tp);
		}

		// Task Creation using check form 

		TaskParams tp1 = new TaskParams();
		tp1.FormId = formId1;
		tp1.FormName = checkFormName1;
		tp1.LocationInstanceNm = locationInstanceValue;
		tp1.ResourceInstanceNm = resourceInstanceValue;
		tp1.WorkGroupName = workGroupName;
		tp1.LocationInstanceId = locInstId;
		tp1.ResourceInstanceId = ResInstId;
		tp1.Timezone = "Republic of India";
		tp1.WorkGroupID = workgroupId;

		List<String> taskName1 = new ArrayList<String>();
		taskName1.add(checkFormTask1);
		taskName1.add(pastDueTask);
		taskName1.add(dueTodayTask);
		taskName1.add(dueLaterTask);
		taskName1.add(noDueDateTask);


		List<String> dueByLst = new ArrayList<String>();

		dueByLst.add(datetime.getDateTime("Day", 1, 0, 0, false, false));
		dueByLst.add(datetime.getDateTime("Day", -1, 0, 0, false, false));
		dueByLst.add(datetime.getDateTime("HOUR", 0, 3, 0, false, false));
		dueByLst.add(datetime.getDateTime("Day", 1, 0, 0, false, false));
		dueByLst.add("");

		for (int i = 0; i < taskName1.size(); i++) {
			tp1.TaskName = taskName1.get(i);			
			tp1.DueBy = dueByLst.get(i);
			taskCreationWrapper.create_Task_Wrapper(tp1);
		}

		try {

			driver = launchbrowser();
			controlActions = new ControlActions(driver);
			controlActions.getUrl(pcAppTenantURL);
			login = new LoginPage(driver);			

			if(!login.performLogin(username,password,newpassword)) {
				TCGFailureMsg = "Could NOT login to application";
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}

		}catch (Exception e) {
			logError("Failed to perform login with new password - "+e.getMessage());	
		}finally {
			driver.close();
		}

		PCAppDriver = launchPCApp();
		controlActionsPC = new ControlActions_PCApp(PCAppDriver);
		commonScreen = new CommonScreen(PCAppDriver);
		inboxScreen = new InboxScreen(PCAppDriver);
		formview = new FormViewScreen(PCAppDriver);
		savedScreen = new SavedScreen(PCAppDriver);
		submissionScreen = new SubmissionsScreen(PCAppDriver);

		LoginScreen loginScreen = new LoginScreen(PCAppDriver);
		CommonScreen commonScreen = loginScreen.Login(pcAppTenantname, username, newpassword);
		if(commonScreen.error) {
			TCGFailureMsg = "COULD not login to the application";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

	}


	@Test(description = "Inbox || Verify task count", priority= -1)
	public void TestCase_30286() throws InterruptedException {

		auth.setAccessToken();

		InboxScreen selectInboxTab  = commonScreen.selectInbox();
		TestValidation.IsFalse(selectInboxTab.error,
				"OPENED Inbox Tab", 
				"COULD not open Inbox Tab");

		boolean refresh  = selectInboxTab.refreshInbox();
		TestValidation.IsTrue(refresh,
				"Refreshed Inbox", 
				"COULD not refresh Inbox");

		boolean verifyInboxTaskCount  = inboxScreen.verifyTaskCount(totalTaskCount);
		TestValidation.IsTrue(verifyInboxTaskCount, 
				"VERIFIED task count - "+totalTaskCount, 
				"FAILED to verify task count "+totalTaskCount);
	}

	@Test(description = "Inbox || Verify the results after searching a task", priority= 0)
	public void TestCase_30287() {
		InboxScreen selectInboxTab  = commonScreen.selectInbox();
		TestValidation.IsFalse(selectInboxTab.error,
				"OPENED Inbox Tab", 
				"COULD not open Inbox Tab");

		boolean verifyInboxTaskSearch  = inboxScreen.searchTask(dueTodayTask);
		TestValidation.IsTrue(verifyInboxTaskSearch, 
				"VERIFIED task search - "+dueTodayTask, 
				"FAILED to verify task search - "+dueTodayTask);
	}

	@Test(description = "Inbox || Verify the tasks details", priority= 1)
	public void TestCase_30285() {
		InboxScreen selectInboxTab  = commonScreen.selectInbox();
		TestValidation.IsFalse(selectInboxTab.error,
				"OPENED Inbox Tab", 
				"COULD not open Inbox Tab");

		boolean verifyInboxTaskDetails  = inboxScreen.verifyTaskDetails(pastDueTask, workGroupName, resourceInstanceValue, "Past Due");
		TestValidation.IsTrue(verifyInboxTaskDetails, 
				"VERIFIED task details for task - "+pastDueTask, 
				"FAILED to verify task details for task - "+pastDueTask);
	}

	@Test(description = "PC || Inbox || Verify starting of Check & Audit form tasks", priority= 2)
	public void TestCase_40340() throws InterruptedException {

		try {

			InboxScreen selectInboxTab  = commonScreen.selectInbox();
			TestValidation.IsFalse(selectInboxTab.error,
					"OPENED Inbox Tab", 
					"COULD not open Inbox Tab");

			boolean openTask = selectInboxTab.selectOpenTask(checkFormTask1, false);
			TestValidation.IsTrue(openTask, 
					"SELECTED & opened task - "+checkFormTask1, 
					"FAILED to open task - "+checkFormTask1);

			boolean saveTask = selectInboxTab.saveTask();
			TestValidation.IsTrue(saveTask, 
					"SAVED task - "+checkFormTask1, 
					"FAILED to save the task - "+checkFormTask1);

			boolean openTask1 = selectInboxTab.selectOpenTask(auditFormTask1, false);
			TestValidation.IsTrue(openTask1, 
					"SELECTED & opened task - "+auditFormTask1, 
					"FAILED to open task - "+auditFormTask1);

			boolean saveTask1 = selectInboxTab.saveTask();
			TestValidation.IsTrue(saveTask1, 
					"SAVED task - "+auditFormTask1, 
					"FAILED to save the task - "+auditFormTask1);


			driver = launchbrowser();
			controlActions = new ControlActions(driver);
			controlActions.getUrl(pcAppTenantURL);
			home = new HomePage(driver);
			login = new LoginPage(driver);
			fbp = new FSQABrowserPage(driver);
			fsqaTasksPage = new FSQABrowserTaskPage(driver);

			if(login.performLogin(username,newpassword).error) {
				TCGFailureMsg = "Could NOT login to application";
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}

			home.clickFSQABrowserMenu();

			boolean navigate = fbp.selectResourceDropDownandNavigate("Equipment","Tasks");
			TestValidation.IsTrue(navigate, 
					"Navigated to FSQABrowser>TaskTab", 
					"Failed to navigate to FSQABrowser>Task Tab");

			boolean viewtask = fbp.openAndApplySettingsForColumn(COLUMNHEADER.TASKNAME, COLUMNSETTING.FILTER,checkFormTask1);
			TestValidation.IsTrue(viewtask, 
					"All tasks are visible", 
					"Failed to verify all tasks");

			boolean verify = fsqaTasksPage.verifytaskStartedColumn(checkFormTask1);
			TestValidation.IsTrue(verify, 
					"Task is started", 
					"Task is not started");

			boolean viewtask1 = fbp.openAndApplySettingsForColumn(COLUMNHEADER.TASKNAME, COLUMNSETTING.FILTER,auditFormTask1);
			TestValidation.IsTrue(viewtask1, 
					"All tasks are visible", 
					"Failed to verify all tasks");

			boolean verify1 = fsqaTasksPage.verifytaskStartedColumn(auditFormTask1);
			TestValidation.IsTrue(verify1, 
					"Task is started", 
					"Task is not started");

			driver.close();
		}

		finally {

			LoginScreen pclogin = new LoginScreen(PCAppDriver);
			boolean login1 = pclogin.reLogin(username, newpassword);
			TestValidation.IsTrue(login1,
					"Loggin out",
					"COULD not login");	

		}
	}

	@Test(description = "PC || Inbox || Starting of Questionnaire form tasks", priority= 3)
	public void TestCase_40345() throws InterruptedException {

		String date = datetime.getDateTime("Day", 1, 0, 0, false, false);
		TaskParams tp2 = new TaskParams();
		taskCreationWrapper = new TCG_TaskCreation_Wrapper();
		tp2.FormId = formId2;
		tp2.DueBy = date;
		tp2.FormName = questionnaireFormName;
		tp2.LocationInstanceNm = locationInstanceValue;
		tp2.ResourceInstanceNm = resourceInstanceValue;
		tp2.WorkGroupName = workGroupName;
		tp2.LocationInstanceId = locInstId;
		tp2.ResourceInstanceId = ResInstId;
		tp2.TaskName = questionnaireFormTask1;

		//taskCreationWrapper.link_WG_User(Arrays.asList(workGroupName), Arrays.asList(username));
		taskCreationWrapper.create_Task_Wrapper(tp2);
		try{

			InboxScreen selectInboxTab  = commonScreen.selectInbox();

			TestValidation.IsFalse(selectInboxTab.error,
					"OPENED Inbox Tab", 
					"COULD not open Inbox Tab");

			boolean refresh = selectInboxTab.refreshInbox();
			TestValidation.IsTrue(refresh,
					"Refreshed Inbox",
					"COULD not refresh Inbox");

			boolean openTask = selectInboxTab.selectOpenTask(questionnaireFormTask1, false);
			TestValidation.IsTrue(openTask, 
					"SELECTED & opened task - "+questionnaireFormTask1, 
					"FAILED to open task - "+questionnaireFormTask1);

			boolean saveTask = selectInboxTab.saveTask();
			TestValidation.IsTrue(saveTask, 
					"SAVED task - "+questionnaireFormTask1, 
					"FAILED to save the task - "+questionnaireFormTask1);


			driver = launchbrowser();
			controlActions = new ControlActions(driver);
			controlActions.getUrl(pcAppTenantURL);
			login = new LoginPage(driver);
			fbp = new FSQABrowserPage(driver);
			fsqaTasksPage = new FSQABrowserTaskPage(driver);

			home = login.performLogin(username, newpassword);
			if(home.error) {
				TCGFailureMsg = "Could NOT login to application";
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}

			home.clickFSQABrowserMenu();

			boolean navigate = fbp.selectResourceDropDownandNavigate("Equipment","Tasks");
			TestValidation.IsTrue(navigate, 
					"Navigated to FSQABrowser>TaskTab", 
					"Failed to navigate to FSQABrowser>Task Tab");

			boolean viewtask = fbp.openAndApplySettingsForColumn(COLUMNHEADER.TASKNAME, COLUMNSETTING.FILTER,questionnaireFormTask1);
			TestValidation.IsTrue(viewtask, 
					"All tasks are visible", 
					"Failed to verify all tasks");

			boolean verify = fsqaTasksPage.verifytaskStartedColumn(questionnaireFormTask1);
			TestValidation.IsTrue(verify, 
					"Task is started", 
					"Task is not started");
		}
		finally {
			driver.close();
		}
	}


	@Test(description = "Inbox || Verify the task Save task and Submit task functionality", priority= 4)
	public void TestCase_31317() {

		auth.setAccessToken();

		InboxScreen selectInboxTab  = commonScreen.selectInbox();
		TestValidation.IsFalse(selectInboxTab.error,
				"OPENED Inbox Tab", 
				"COULD not open Inbox Tab");

		boolean openTask = selectInboxTab.selectOpenTask(auditSubmitTask, false);
		TestValidation.IsTrue(openTask, 
				"SELECTED & opened task - "+auditFormTask1, 
				"FAILED to open task - "+auditFormTask1);


		boolean saveTask = selectInboxTab.saveTask();
		TestValidation.IsTrue(saveTask, 
				"SAVED task - "+auditFormTask1, 
				"FAILED to save the task - "+auditFormTask1);

		savedScreen = commonScreen.selectSaved();
		TestValidation.IsFalse(savedScreen.error,
				"OPENED Saved Tab", 
				"COULD not open Saved Tab");

		boolean openSavedForm = savedScreen.selectOpenForm(auditFormName);
		TestValidation.IsTrue(openSavedForm, 
				"SELECTED & opened saved form - "+auditFormTask1, 
				"FAILED to open saved form - "+auditFormTask1);

		HashMap<String, List<String>> allFields = new LinkedHashMap<String, List<String>>();
		allFields.put("ParaGraph1",Arrays.asList("Automation of Paragraph"));
		allFields.put("SingleLineText1",Arrays.asList("Automation of Single Line Text"));
		allFields.put("SelectOne1",Arrays.asList("2"));
		allFields.put("Numeric1",Arrays.asList("12.5"));
		formDetailsPC = new FormDetailsPC();
		formDetailsPC.selectLocationResource = false;
		formDetailsPC.locationName = locationInstanceValue;
		formDetailsPC.resourceName = resourceInstanceValue;
		formDetailsPC.fieldDetails = allFields;

		boolean submitData = formview.fillDataInForm1(formDetailsPC);
		TestValidation.IsTrue(submitData, 
				"SUBMITTED data for saved form - "+auditFormTask1, 
				"FAILED to submit data for saved form - "+auditFormTask1);

		SubmissionsScreen selectSubmissionTab  = commonScreen.selectSubmissions();
		TestValidation.IsFalse(selectSubmissionTab.error,
				"OPENED Submissions Tab", 
				"COULD not open Submissions Tab");

		boolean verifySubmission = selectSubmissionTab.verifySubmissionAvailability(auditFormName);
		TestValidation.IsTrue(verifySubmission, 
				"TASK submission is shown for form - "+auditFormTask1, 
				"FAILED to verify task submission for form - "+auditFormTask1);
	}

	@Test(description = "PC || Inbox || Submission of Check form task", priority= 5)
	public void TestCase_40335() {
		formDetailsPC = new FormDetailsPC();
		formDetailsPC.selectLocationResource = false;
		formDetailsPC.locationName = locationInstanceValue;
		formDetailsPC.resourceName = resourceInstanceValue;

		InboxScreen selectInboxTab  = commonScreen.selectInbox();
		TestValidation.IsFalse(selectInboxTab.error,
				"OPENED Inbox Tab", 
				"COULD not open Inbox Tab");

		boolean openTask = selectInboxTab.selectOpenTask(checkFormTask1, false);
		TestValidation.IsTrue(openTask, 
				"SELECTED & opened task - "+checkFormTask1, 
				"FAILED to open task - "+checkFormTask1);

		HashMap<String, List<String>> allFields = new LinkedHashMap<String, List<String>>();
		allFields.put("Num 1",Arrays.asList("34"));
		formDetailsPC.fieldDetails = allFields;

		boolean submitData = formview.fillDataInForm1(formDetailsPC);
		TestValidation.IsTrue(submitData, 
				"SUBMITTED data for task - "+checkFormTask1, 
				"FAILED to submit data fo task "+checkFormTask1);

		SubmissionsScreen selectSubmissionTab  = commonScreen.selectSubmissions();
		TestValidation.IsFalse(selectSubmissionTab.error,
				"OPENED Submissions Tab", 
				"COULD not open Submissions Tab");

		boolean verifySubmission = selectSubmissionTab.verifySubmissionAvailability(checkFormName1);
		TestValidation.IsTrue(verifySubmission, 
				"TASK submission is shown for form - "+checkFormTask1, 
				"FAILED to verify task submission for form - "+checkFormTask1);

	}

	@Test(description = "PC || Inbox || Submission of Questionnaire form tasks", priority= 6)
	public void TestCase_40343() {

		formDetailsPC = new FormDetailsPC();
		formDetailsPC.selectLocationResource = false;
		formDetailsPC.locationName = locationInstanceValue;
		formDetailsPC.resourceName = resourceInstanceValue;

		InboxScreen selectInboxTab  = commonScreen.selectInbox();
		TestValidation.IsFalse(selectInboxTab.error,
				"OPENED Inbox Tab", 
				"COULD not open Inbox Tab");

		boolean openTask = selectInboxTab.selectOpenTask(questionnaireFormTask1, false);
		TestValidation.IsTrue(openTask, 
				"SELECTED & opened task - "+questionnaireFormTask1, 
				"FAILED to open task - "+questionnaireFormTask1);

		HashMap<String, List<String>> allFields = new LinkedHashMap<String, List<String>>();
		allFields.put("Num 1",Arrays.asList("34"));
		formDetailsPC.fieldDetails = allFields;

		boolean submitData = formview.fillDataInForm1(formDetailsPC);
		TestValidation.IsTrue(submitData, 
				"SUBMITTED data for task - "+questionnaireFormTask1, 
				"FAILED to submit data fo task - "+questionnaireFormTask1);


		SubmissionsScreen selectSubmissionTab  = commonScreen.selectSubmissions();
		TestValidation.IsFalse(selectSubmissionTab.error,
				"OPENED Submissions Tab", 
				"COULD not open Submissions Tab");

		boolean verifySubmission = selectSubmissionTab.verifySubmissionAvailability(questionnaireFormName);
		TestValidation.IsTrue(verifySubmission, 
				"TASK submission is shown for form - "+questionnaireFormName, 
				"FAILED to verify task submission for form - "+questionnaireFormName);
	}

	@Test(description = "Inbox || Verify Workgroup filter", priority= 7)
	public void TestCase_30294() {

		try {
			InboxScreen selectInboxTab  = commonScreen.selectInbox();
			TestValidation.IsFalse(selectInboxTab.error,
					"OPENED Inbox Tab", 
					"COULD not open Inbox Tab");

			boolean wgselect = selectInboxTab.selectWorkgroup(workGroupName);
			TestValidation.IsTrue(wgselect,
					"Workgroup selected", 
					"COULD not select workgroup");
		}

		finally {
			InboxScreen selectInboxTab  = commonScreen.selectInbox();
			boolean status = selectInboxTab.clickStatus();
			TestValidation.IsTrue(status,
					"Status button is clicked", 
					"COULD not clcik on Status button");
		}
	}

	@Test(description = "Inbox || Verify Status filter", priority= 8)
	public void TestCase_30295() {

		try {

			InboxScreen selectInboxTab  = commonScreen.selectInbox();
			TestValidation.IsFalse(selectInboxTab.error,
					"OPENED Inbox Tab", 
					"COULD not open Inbox Tab");

			boolean status = selectInboxTab.clickStatus();
			TestValidation.IsTrue(status,
					"Status button is clicked", 
					"COULD not clcik on Status button");

			boolean search3 = selectInboxTab.searchTask(noDueDateTask);
			TestValidation.IsTrue(search3,
					"No Due task is visible", 
					"COULD not find no due task");

			boolean statusselect = selectInboxTab.selectStatus(Status.DUELATER);
			TestValidation.IsTrue(statusselect,
					"Due later status selected", 
					"COULD not select due later status");

			boolean search1 = selectInboxTab.searchTask(dueLaterTask);
			TestValidation.IsTrue(search1,
					"Due Later task is visible", 
					"COULD not find due later task");

			boolean statusselect1 = selectInboxTab.selectStatus(Status.DUETODAY);
			TestValidation.IsTrue(statusselect1,
					"Due Today status selected", 
					"COULD not select due today status");

			boolean search = selectInboxTab.searchTask(dueTodayTask);
			TestValidation.IsTrue(search,
					"Due Today task is visible", 
					"COULD not find due today task");

			boolean statusselect2 = selectInboxTab.selectStatus(Status.PASTDUE);
			TestValidation.IsTrue(statusselect2,
					"Past Due status selected", 
					"COULD not select past due status");

			boolean search2 = selectInboxTab.searchTask(pastDueTask);
			TestValidation.IsTrue(search2,
					"Past Due task is visible", 
					"COULD not find past due task");

		}

		finally {

			InboxScreen selectInboxTab  = commonScreen.selectInbox();			
			boolean clear =selectInboxTab.clearSearchTask();
			TestValidation.IsTrue(clear,
					"Cleared searched task", 
					"COULD not clear searched task");
		}

	}

	@Test(description = "Inbox || Verify Due By filter", priority= 9)
	public void TestCase_30296() {

		try {

			auth.setAccessToken();

			String pastdue = datetime.AddDaystoToddaysDate(-1,"d");
			String duetoday = datetime.AddDaystoToddaysDate(0,"d");
			String duelater = datetime.AddDaystoToddaysDate(1,"d");

			InboxScreen selectInboxTab  = commonScreen.selectInbox();
			TestValidation.IsFalse(selectInboxTab.error,
					"OPENED Inbox Tab", 
					"COULD not open Inbox Tab");

			boolean duebyselect = selectInboxTab.dueBySelect(duetoday);
			TestValidation.IsTrue(duebyselect,
					"Due By selected", 
					"COULD not select due by");

			boolean search = selectInboxTab.searchTask(dueTodayTask);
			TestValidation.IsTrue(search,
					"Due Today task is visible", 
					"COULD not find due today task");

			boolean duebyselect1 = selectInboxTab.dueBySelect(duelater);
			TestValidation.IsTrue(duebyselect1,
					"Due By selected", 
					"COULD not select due by");

			boolean search1 = selectInboxTab.searchTask(dueLaterTask);
			TestValidation.IsTrue(search1,
					"Due Later task is visible", 
					"COULD not find due later task");

			boolean duebyselect2 = selectInboxTab.dueBySelect(pastdue);
			TestValidation.IsTrue(duebyselect2,
					"Past Due selected", 
					"COULD not select past due");

			boolean search2 = selectInboxTab.searchTask(pastDueTask);
			TestValidation.IsTrue(search2,
					"Past Due task is visible", 
					"COULD not find past due task");

			boolean status = selectInboxTab.clickStatus();
			TestValidation.IsTrue(status,
					"Status button is clicked", 
					"COULD not clcik on Status button");

		}
		finally {

			InboxScreen selectInboxTab  = commonScreen.selectInbox();

			boolean clear =selectInboxTab.clearSearchTask();
			TestValidation.IsTrue(clear,
					"Cleared searched task", 
					"COULD not clear searched task");

			boolean status = selectInboxTab.clickStatus();
			TestValidation.IsTrue(status,
					"Status button is clicked", 
					"COULD not clcik on Status button");
		}

	}

	@Test(description = "Inbox || Verify the refresh data functionality", priority= 10)
	public void TestCase_30290() throws InterruptedException {

		try{
			InboxScreen selectInboxTab  = commonScreen.selectInbox();

			TestValidation.IsFalse(selectInboxTab.error,
					"OPENED Inbox Tab", 
					"COULD not open Inbox Tab");			

			apiUtils = new ApiUtils();
			TCG_TaskCreation_Wrapper taskCreationWrapper = new TCG_TaskCreation_Wrapper();
			String date = datetime.getDateTime("Day", 1, 0, 0, false, false);
			TaskParams tp3 = new TaskParams();
			tp3.FormId = formId1;
			tp3.DueBy = date;
			tp3.FormName = checkFormName1;
			tp3.TaskName = refreshTask;
			tp3.LocationInstanceNm = locationInstanceValue;
			tp3.ResourceInstanceNm = resourceInstanceValue;
			tp3.WorkGroupName = workGroupName;
			tp3.LocationInstanceId = locInstId;
			tp3.ResourceInstanceId = ResInstId;
			taskCreationWrapper.create_Task_Wrapper(tp3);

			boolean refresh  = selectInboxTab.refreshInbox();
			TestValidation.IsTrue(refresh,
					"Refreshed Inbox", 
					"COULD not refresh Inbox");	

			boolean search = selectInboxTab.searchTask(tp3.TaskName);
			TestValidation.IsTrue(search,
					"Refreshed task is visible", 
					"COULD not find refresh task");	
		}

		finally {
			InboxScreen selectInboxTab  = commonScreen.selectInbox();

			boolean clear =selectInboxTab.clearSearchTask();
			TestValidation.IsTrue(clear,
					"Cleared searched task", 
					"COULD not clear searched task");
		}

	}

	@Test(description = "Inbox || Verify user is able to view saved task with details in web app(Inbox) which is saved from PC APP", priority= 11)
	public void TestCase_45891() throws InterruptedException {

		try{
			InboxScreen selectInboxTab  = commonScreen.selectInbox();

			TestValidation.IsFalse(selectInboxTab.error,
					"OPENED Inbox Tab", 
					"COULD not open Inbox Tab");

			boolean openTask = selectInboxTab.selectOpenTask(auditFormTask1, false);
			TestValidation.IsTrue(openTask, 
					"SELECTED & opened task - "+auditFormTask1, 
					"FAILED to open task - "+auditFormTask1);


			HashMap<String, List<String>> allFields = new LinkedHashMap<String, List<String>>();
			allFields.put("ParaGraph1",Arrays.asList("Automation of Paragraph"));
			allFields.put("SingleLineText1",Arrays.asList("Automation of Single Line Text"));
			allFields.put("SelectOne1",Arrays.asList("2"));
			allFields.put("Numeric1",Arrays.asList("12.5"));
			formDetailsPC = new FormDetailsPC();
			formDetailsPC.selectLocationResource = false;
			formDetailsPC.locationName = locationInstanceValue;
			formDetailsPC.resourceName = resourceInstanceValue;
			formDetailsPC.isSubmit = false;
			formDetailsPC.fieldDetails = allFields;

			boolean submitData = formview.fillDataInForm1(formDetailsPC);
			TestValidation.IsTrue(submitData, 
					"SUBMITTED data for saved form - "+auditFormTask1, 
					"FAILED to submit data for saved form - "+auditFormTask1);


			boolean saveTask = selectInboxTab.saveTask();
			TestValidation.IsTrue(saveTask, 
					"SAVED task - "+auditFormTask1, 
					"FAILED to save the task - "+auditFormTask1);

			driver = launchbrowser();
			controlActions = new ControlActions(driver);
			controlActions.getUrl(pcAppTenantURL);
			home = new HomePage(driver);
			login = new LoginPage(driver);
			inbox = new InboxPage(driver);
			fbfp = new FSQABrowserFormsPage(driver);

			login.performLogin(username,newpassword);
			if(home.error) {
				TCGFailureMsg = "Could NOT login to application";
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}

			home.clickInboxMenu();

			boolean form = inbox.selectFormTask(auditFormTask1);
			TestValidation.IsTrue(form, 
					"Openend form task - "+auditFormTask1, 
					"Not able to open form task -"+auditFormTask1);

			boolean verify = fbfp.verifyISavedFormDetails(auditFormName, locationInstanceValue, resourceInstanceValue, "Numeric1", "12.5");
			TestValidation.IsTrue(verify, 
					"Verified saved form details - "+auditFormTask1, 
					"Not able to verify form details -"+auditFormTask1);
		}
		finally {
			driver.close();
		}

	}

	@Test(description = "Inbox || Verify Recalled Task", priority= 12)
	public void TestCase_48926() throws InterruptedException {

		try {
			InboxScreen selectInboxTab  = commonScreen.selectInbox();
			TestValidation.IsFalse(selectInboxTab.error,
					"OPENED Inbox Tab", 
					"COULD not open Inbox Tab");

			apiUtils = new ApiUtils();
			taskCreationWrapper = new TCG_TaskCreation_Wrapper();
			String date = datetime.getDateTime("Day", 1, 0, 0, false, false);
			TaskParams tp3 = new TaskParams();
			tp3.FormId = formId1;
			tp3.DueBy = date;
			tp3.FormName = checkFormName1;
			tp3.TaskName = recallTask;
			tp3.LocationInstanceNm = locationInstanceValue;
			tp3.ResourceInstanceNm = resourceInstanceValue;
			tp3.WorkGroupName = workGroupName;
			tp3.LocationInstanceId = locInstId;
			tp3.ResourceInstanceId = ResInstId;
			taskId = taskCreationWrapper.create_TaskId_Wrapper(tp3,workgroupId);

			boolean refresh  = selectInboxTab.refreshInbox();
			TestValidation.IsTrue(refresh,
					"Refreshed Inbox",
					"COULD not refresh Inbox");

			boolean search = selectInboxTab.searchTask(tp3.TaskName);
			TestValidation.IsTrue(search,
					"Refreshed task is visible", 
					"COULD not find refresh task");		

			taskCreationWrapper.recallTask_Wrapper(taskId);

			boolean openTask = selectInboxTab.openTask(tp3.TaskName, false);
			TestValidation.IsTrue(openTask, 
					"SELECTED & opened task - "+tp3.TaskName, 
					"FAILED to open task - "+tp3.TaskName);	


			boolean clickOk = selectInboxTab.actionOnTaskPopUp(Action.RECALL);
			TestValidation.IsTrue(clickOk, 
					"Click on recall task pop up", 
					"FAILED to click on recall task pop up");	
		}

		finally {
			InboxScreen selectInboxTab  = commonScreen.selectInbox();

			boolean clear =selectInboxTab.clearSearchTask();
			TestValidation.IsTrue(clear,
					"Cleared searched task", 
					"COULD not clear searched task");
		}

	}

	@Test(description = "Inbox || Verify the task selected should be accessible", priority = 13)
	public void TestCase_30289() throws InterruptedException {

		InboxScreen selectInboxTab  = commonScreen.selectInbox();
		TestValidation.IsFalse(selectInboxTab.error,
				"OPENED Inbox Tab", 
				"COULD not open Inbox Tab");		

		apiUtils = new ApiUtils();
		taskCreationWrapper = new TCG_TaskCreation_Wrapper();
		String date = datetime.getDateTime("Day", 1, 0, 0, false, false);
		TaskParams tp = new TaskParams();
		tp.FormId = formId;
		tp.DueBy = date;
		tp.FormName = auditFormName;
		tp.TaskName = "With Resource Task";
		tp.LocationInstanceNm = locationInstanceValue;
		tp.ResourceInstanceNm = resourceInstanceValue;
		tp.WorkGroupName = workGroupName;
		tp.LocationInstanceId = locInstId;
		tp.ResourceInstanceId = ResInstId;
		taskCreationWrapper.create_Task_Wrapper(tp);

		TaskParams tp1 = new TaskParams();
		tp1.FormId = formId;
		tp1.DueBy = date;
		tp1.FormName = auditFormName;
		tp1.TaskName = "Without Resource Task";
		tp1.LocationInstanceNm = locationInstanceValue;
		tp1.WorkGroupName = workGroupName;
		tp1.LocationInstanceId = locInstId;
		taskCreationWrapper.create_Task_Wrapper(tp1);

		boolean refresh  = selectInboxTab.refreshInbox();
		TestValidation.IsTrue(refresh,
				"Refreshed Inbox",
				"COULD not refresh Inbox");

		boolean openTask = selectInboxTab.openTask(tp.TaskName, false);
		TestValidation.IsTrue(openTask, 
				"SELECTED & opened task - "+tp.TaskName, 
				"FAILED to open task - "+tp.TaskName);

		boolean discard = selectInboxTab.discardOpenForm();
		TestValidation.IsTrue(discard, 
				"Discard opened task - "+tp.TaskName, 
				"FAILED to discard open task - "+tp.TaskName);

		boolean openTask1 = selectInboxTab.openTask(tp1.TaskName, false);
		TestValidation.IsTrue(openTask1, 
				"SELECTED & opened task - "+tp1.TaskName, 
				"FAILED to open task - "+tp1.TaskName);

		FormViewScreen fvs = new FormViewScreen(PCAppDriver);
		boolean fillDetails = fvs.selectLocationResource(locationInstanceValue, resourceInstanceValue1);
		TestValidation.IsTrue(fillDetails, 
				"Selected resource and location", 
				"FAILED to select location and resource ");

		boolean discard1 = selectInboxTab.discardFormWithoutResource();
		TestValidation.IsTrue(discard1, 
				"Discard opened task - "+tp1.TaskName, 
				"FAILED to discard open task - "+tp1.TaskName);
	}

	@Test(description = "Inbox || Verify user should be able to delete saved task in offline mode", priority= 14)
	public void TestCase_41933() throws InterruptedException {

		auth.setAccessToken();

		try {

			InboxScreen selectInboxTab  = commonScreen.selectInbox();
			TestValidation.IsFalse(selectInboxTab.error,
					"OPENED Inbox Tab", 
					"COULD not open Inbox Tab");		

			apiUtils = new ApiUtils();
			taskCreationWrapper = new TCG_TaskCreation_Wrapper();
			String date = datetime.getDateTime("Day", 1, 0, 0, false, false);
			TaskParams tp = new TaskParams();
			tp.FormId = formId1;
			tp.DueBy = date;
			tp.FormName = checkFormName1;
			tp.LocationInstanceNm = locationInstanceValue;
			tp.ResourceInstanceNm = resourceInstanceValue;
			tp.WorkGroupName = workGroupName;
			tp.LocationInstanceId = locInstId;
			tp.ResourceInstanceId = ResInstId;

			List<String> taskName = new ArrayList<String>();
			taskName.add("DeleteSavedTask");
			taskName.add("DeleteTask");

			for (int i = 0; i < taskName.size(); i++) {
				tp.TaskName = taskName.get(i);
				taskCreationWrapper.create_Task_Wrapper(tp);
			}

			boolean refresh  = selectInboxTab.refreshInbox();
			TestValidation.IsTrue(refresh,
					"Refreshed Inbox",
					"COULD not refresh Inbox");

			SettingsScreen settings = commonScreen.selectSettings();
			TestValidation.IsFalse(settings.error,
					"OPENED Settings Tab", 
					"COULD not open Settings Tab");	

			boolean toggle = settings.changeOfflineModeToggle(true);
			TestValidation.IsTrue(toggle,
					"Switched on offline mode",
					"COULD not switch on offline mode");

			commonScreen.selectInbox();

			boolean openTask = selectInboxTab.openTask("DeleteSavedTask", false);
			TestValidation.IsTrue(openTask, 
					"SELECTED & opened task - DeleteSavedTask", 
					"FAILED to open task - DeleteSavedTask");

			boolean saveTask = selectInboxTab.saveTask();
			TestValidation.IsTrue(saveTask, 
					"SAVED task - DeleteSavedTask", 
					"FAILED to save the task - DeleteSavedTask");		

			boolean openTask1 = selectInboxTab.openTask("DeleteTask", false);
			TestValidation.IsTrue(openTask1, 
					"SELECTED & opened task - DeleteTask", 
					"FAILED to open task - DeleteTask");

			boolean saveTask1 = selectInboxTab.saveTask();
			TestValidation.IsTrue(saveTask1, 
					"SAVED task - DeleteTask", 
					"FAILED to save the task - DeleteTask");

			SavedScreen savedscreen  = new SavedScreen(PCAppDriver);

			boolean verifycount2 = savedscreen.verifyCount(3);
			TestValidation.IsTrue(verifycount2, 
					"Verified count", 
					"FAILED to verify count");

			boolean openTask2 = selectInboxTab.openTask("DeleteTask", false);
			TestValidation.IsTrue(openTask2, 
					"SELECTED & opened task - DeleteTask", 
					"FAILED to open task - DeleteTask");

			boolean delete = selectInboxTab.deleteSavedTask();
			TestValidation.IsTrue(delete, 
					"Deleted saved task in Inbox Screen", 
					"FAILED to delete saved task in Inbox Screen");

			commonScreen.selectSaved();

			boolean verifycount = savedscreen.verifyCount(2);
			TestValidation.IsTrue(verifycount, 
					"Verified count", 
					"FAILED to verify count");

			boolean deletesaved = savedscreen.deleteUnopenendForm(checkFormName1);
			TestValidation.IsTrue(deletesaved, 
					"Saved form deleted from saved screen", 
					"FAILED to delete saved form");

			boolean verifycount1 = savedscreen.verifyCount(1);
			TestValidation.IsTrue(verifycount1, 
					"Verified count", 
					"FAILED to verify count");

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

			commonScreen.selectInbox();

		}

	}

	@Test(description = "Verify Reassign task functionality", priority= 15)
	public void TestCase_45284() throws InterruptedException {

		try {
			InboxScreen selectInboxTab  = commonScreen.selectInbox();
			TestValidation.IsFalse(selectInboxTab.error,
					"OPENED Inbox Tab", 
					"COULD not open Inbox Tab");

			apiUtils = new ApiUtils();
			taskCreationWrapper = new TCG_TaskCreation_Wrapper();
			String date = datetime.getDateTime("Day", 1, 0, 0, false, false);
			TaskParams tp3 = new TaskParams();
			tp3.FormId = formId;
			tp3.DueBy = date;
			tp3.FormName = auditFormName;
			tp3.TaskName = reassignTask;
			tp3.LocationInstanceNm = locationInstanceValue;
			tp3.ResourceInstanceNm = resourceInstanceValue;
			tp3.WorkGroupName = workGroupName;
			tp3.LocationInstanceId = locInstId;
			tp3.ResourceInstanceId = ResInstId;
			ReassigntaskId = taskCreationWrapper.create_TaskId_Wrapper(tp3,workgroupId);

			boolean refresh  = selectInboxTab.refreshInbox();
			TestValidation.IsTrue(refresh,
					"Refreshed Inbox",
					"COULD not refresh Inbox");

			boolean search = selectInboxTab.searchTask(tp3.TaskName);
			TestValidation.IsTrue(search,
					"Refreshed task is visible", 
					"COULD not find refresh task");


			TaskDetails tsd = new TaskDetails();
			tsd.TaskName = reassignTask;
			tsd.TaskId = ReassigntaskId;
			tsd.WorkGroupName = workGroupName;
			tsd.WorkgroupId = workgroupId;
			tsd.ChangedWorkGroupName = newWgName1;
			//tsd.ChangedWorkgroupId = newWgId;
			newWgId = taskCreationWrapper.reassignTask_Wrapper(tsd);

			boolean openTask = selectInboxTab.openTask(tp3.TaskName, false);
			TestValidation.IsTrue(openTask, 
					"SELECTED & opened task - "+tp3.TaskName, 
					"FAILED to open task - "+tp3.TaskName);	

			boolean clickOk = selectInboxTab.actionOnTaskPopUp(Action.REASSIGN);
			TestValidation.IsTrue(clickOk, 
					"Click on recall task pop up", 
					"FAILED to click on recall task pop up");	
		}

		finally {

			threadsleep(4000);

			InboxScreen selectInboxTab  = commonScreen.selectInbox();

			threadsleep(4000);
			boolean clear =selectInboxTab.clearSearchTask();
			TestValidation.IsTrue(clear,
					"Cleared searched task", 
					"COULD not clear searched task");			
		}
	}


	@Test(description = "Inbox || Verify that all the tasks related to workgroup (user specific) should be visible" , priority = 16)
	public void TestCase_30284() throws InterruptedException {

		try {

			InboxScreen selectInboxTab  = commonScreen.selectInbox();
			TestValidation.IsFalse(selectInboxTab.error,
					"OPENED Inbox Tab", 
					"COULD not open Inbox Tab");

			String date =datetime.getDateTime("Day", 1, 0, 0, false, false);
			TaskParams tp = new TaskParams();
			tp.FormId = formId;
			tp.DueBy = date;
			tp.FormName = auditFormName;
			tp.TaskName = newWgTask;
			tp.LocationInstanceNm = locationInstanceValue;
			tp.ResourceInstanceNm = resourceInstanceValue;
			tp.WorkGroupName = newWgName2;
			tp.LocationInstanceId = locInstId;
			tp.ResourceInstanceId = ResInstId;
			List<String> newWgId1 = taskCreationWrapper.link_WG_User(Arrays.asList(newWgName2), Arrays.asList(username));
			taskCreationWrapper.create_Task_Wrapper(tp);

			boolean refresh  = selectInboxTab.refreshInbox();
			TestValidation.IsTrue(refresh,
					"Refreshed Inbox",
					"COULD not refresh Inbox");

			boolean wgselect = selectInboxTab.selectWorkgroup(newWgName2);
			TestValidation.IsTrue(wgselect,
					"Workgroup selected", 
					"COULD not select workgroup");	

			boolean search = selectInboxTab.searchTask(newWgTask);
			TestValidation.IsTrue(search,
					"Refreshed task is visible", 
					"COULD not find refresh task");

			taskCreationWrapper.Unlink_WGUser(newWgName2, newWgId1.get(0),username);

			boolean refresh1 = selectInboxTab.refreshInbox();
			TestValidation.IsTrue(refresh1,
					"Refreshed Inbox",
					"COULD not refresh Inbox");

			boolean verify = selectInboxTab.verifyWorkgroup(newWgName2);
			TestValidation.IsFalse(verify,
					"Workgroup has been unlinked from the logged in user", 
					"Workgroup is still linked to the logged in user");
		}
		finally {
			InboxScreen selectInboxTab  = commonScreen.selectInbox();

			boolean clear =selectInboxTab.clearSearchTask();
			TestValidation.IsTrue(clear,
					"Cleared searched task", 
					"COULD not clear searched task");			

			boolean status = selectInboxTab.clickStatus();
			TestValidation.IsTrue(status,
					"Status button is clicked", 
					"COULD not clcik on Status button");

		}

	}

	@Test(description = "Password change dialog || Verify dialog box on password change from logged in user " , priority = 17)
	public void TestCase_45285() throws InterruptedException {

		InboxScreen selectInboxTab  = commonScreen.selectInbox();
		TestValidation.IsFalse(selectInboxTab.error,
				"OPENED Inbox Tab", 
				"COULD not open Inbox Tab");

		SettingsScreen settings = commonScreen.selectSettings();
		TestValidation.IsFalse(settings.error,
				"OPENED Settings Tab", 
				"COULD not open Settings Tab");	

		boolean toggle = settings.changeOfflineModeToggle(true);
		TestValidation.IsTrue(toggle,
				"Switched on offline mode",
				"COULD not switch on offline mode");


		LoginScreen pclogin = new LoginScreen(PCAppDriver);
		boolean login1 = pclogin.reLogin(username, newpassword);
		TestValidation.IsTrue(login1,
				"Loggin out",
				"COULD not login");	

		SettingsScreen settings1 = commonScreen.selectSettings();
		TestValidation.IsFalse(settings1.error,
				"OPENED Settings Tab", 
				"COULD not open Settings Tab");	

		boolean toggle1 = settings.changeOfflineModeToggle(false);
		TestValidation.IsTrue(toggle1,
				"Switched off offline mode",
				"COULD not switch off offline mode");

		TCG_UserFlows_Wrapper userflow = new TCG_UserFlows_Wrapper();		
		userflow.resetPassword(username,changedpassword);

		InboxScreen selectInboxTab1  = commonScreen.selectInbox();
		TestValidation.IsFalse(selectInboxTab1.error,
				"OPENED Inbox Tab", 
				"COULD not open Inbox Tab");

		Thread.sleep(5000);

		boolean refresh = selectInboxTab.clickrefresh();
		TestValidation.IsTrue(refresh,
				"Refreshed Inbox",
				"COULD not refresh Inbox");

		boolean password = selectInboxTab.newPasswordChange(changedpassword);
		TestValidation.IsTrue(password,
				"New password provided",
				"COULD not provide new password");

	}


	@Test(description = "View attached image || Verify user is able to view image attachment while task submission", priority= 18)
	public void TestCase_48669() throws InterruptedException {

		String date = datetime.getDateTime("Day", 1, 0, 0, false, false);
		TaskParams tp2 = new TaskParams();
		taskCreationWrapper = new TCG_TaskCreation_Wrapper();
		tp2.FormId = formId2;
		tp2.DueBy = date;
		tp2.FormName = questionnaireFormName;
		tp2.LocationInstanceNm = locationInstanceValue;
		tp2.ResourceInstanceNm = resourceInstanceValue;
		tp2.WorkGroupName = workGroupName;
		tp2.LocationInstanceId = locInstId;
		tp2.ResourceInstanceId = ResInstId;
		tp2.TaskName = questionnaireFormTask2;
		tp2.WorkGroupID = workgroupId;
		//	taskCreationWrapper.link_WG_User(Arrays.asList(workGroupName), Arrays.asList(username));
		taskCreationWrapper.create_Task_Wrapper(tp2);

		formDetailsPC = new FormDetailsPC();

		HashMap<String, List<String>> allFields = new LinkedHashMap<String, List<String>>();
		allFields.put(Numeric,Arrays.asList("34"));
		formDetailsPC.fieldDetails = allFields;

		formDetailsPC.selectLocationResource = false;
		formDetailsPC.propertyfieldName = Numeric;
		formDetailsPC.allowAttachmentCheck = true;
		formDetailsPC.isSubmit = false;

		InboxScreen selectInboxTab  = commonScreen.selectInbox();
		TestValidation.IsFalse(selectInboxTab.error,
				"OPENED Inbox Tab", 
				"COULD not open Inbox Tab");

		boolean refresh = selectInboxTab.refreshInbox();
		TestValidation.IsTrue(refresh,
				"Refreshed Inbox",
				"COULD not refresh Inbox");

		boolean openTask = selectInboxTab.selectOpenTask(questionnaireFormTask2, false);
		TestValidation.IsTrue(openTask, 
				"SELECTED & opened task - "+questionnaireFormTask2, 
				"FAILED to open task - "+questionnaireFormTask2);

		boolean submitData = formview.fillDataInForm1(formDetailsPC);
		TestValidation.IsTrue(submitData, 
				"FILLED data for task with attachment at field level", 
				"FAILED to fill data for task with attachment at field level");

		boolean addFormLevelAttachment = formview.addFormLevelAttachment();
		TestValidation.IsTrue(addFormLevelAttachment, 
				"ADDED attachment at form level", 
				"FAILED to add attachment at form level");

		boolean submitForm = formview.submitForm(formDetailsPC.chartClose);
		TestValidation.IsTrue(submitForm, 
				"SUBMISSION is successfull", 
				"FAILED to submit form");

	}


	@Test(description = "Inbox || Verify short date after opening task", priority= 19)
	public void TestCase_51236() throws InterruptedException {

		auth.setAccessToken();

		String date = datetime.getDateTime("Day", 1, 0, 0, false, false);
		TaskParams tp = new TaskParams();
		taskCreationWrapper = new TCG_TaskCreation_Wrapper();
		tp.FormId = formId;
		tp.DueBy = date;
		tp.FormName = auditFormName;
		tp.LocationInstanceNm = locationInstanceValue;
		tp.ResourceInstanceNm = resourceInstanceValue;
		tp.WorkGroupName = workGroupName;
		tp.LocationInstanceId = locInstId;
		tp.ResourceInstanceId = ResInstId;
		tp.UserName= username;
		tp.TaskName = auditFormTask2;
		tp.WorkGroupID = workgroupId;

		taskCreationWrapper.create_Task_Wrapper(tp);

		InboxScreen selectInboxTab  = commonScreen.selectInbox();
		TestValidation.IsFalse(selectInboxTab.error,
				"OPENED Inbox Tab", 
				"COULD not open Inbox Tab");

		boolean refresh  = selectInboxTab.refreshInbox();
		TestValidation.IsTrue(refresh,
				"Refreshed Inbox", 
				"COULD not refresh Inbox");

		boolean openTask = selectInboxTab.selectOpenTask(auditFormTask2, false);
		TestValidation.IsTrue(openTask, 
				"SELECTED & opened task - "+auditFormTask2, 
				"FAILED to open task - "+auditFormTask2);

		String dueDate =  selectInboxTab.getDueDate(auditFormName);

		boolean verifyDateTimeFormat = datetime.verifyDateTimeFormat("MM/dd/yyyy HH:mm", dueDate);
		TestValidation.IsTrue(verifyDateTimeFormat, 
				"VERIFED task due date format for - "+dueDate, 
				"FAILED to verify task due date format for - "+dueDate);

		boolean discardTask = selectInboxTab.discardOpenForm();
		TestValidation.IsTrue(discardTask, 
				"Discarded task - "+auditFormTask2, 
				"FAILED to discard task - "+auditFormTask2);

	}

	@Test(description = "Inbox || Verify Aggregate field value in task for which 'Sum Range' field is configured" , priority= 20)
	public void TestCase_48590() throws InterruptedException {

		FormFieldParams NumericField1 = new FormFieldParams();
		NumericField1.DPTFieldType = DPT_FIELD_TYPES.NUMERIC;
		NumericField1.Name = NumericSRC1;

		FormFieldParams NumericField2 = new FormFieldParams();
		NumericField2.DPTFieldType = DPT_FIELD_TYPES.NUMERIC;
		NumericField2.Name = NumericSRC2;

		FormFieldParams AggregateField1 = new FormFieldParams();
		AggregateField1.DPTFieldType = DPT_FIELD_TYPES.AGGREGATE;
		AggregateField1.Name = AggSumRange;
		AggregateField1.SelectedFunction = AGGREGATE_FUNC_TYPES.SUM_RANGE;
		AggregateField1.SelectedSourceCollection = Arrays.asList(NumericSRC1, NumericSRC2);
		AggregateField1.AGG_MIN = "8";
		AggregateField1.AGG_MAX = "40";

		formId = apiUtils.getUUID();

		FormParams fp4 = new FormParams();
		fp4.FormId = formId;
		fp4.FormName = checkFormName2;
		fp4.type = DPT_FORM_TYPES.CHECK;
		fp4.ResourceType = resourceCategoryType;
		fp4.ResourceCategoryNm = resourceCategoryValue;
		fp4.EquipmentResources = resCatMap;
		fp4.ResourceInstanceNm = resourceInstanceValue;


		fp4.formElements = Arrays.asList(NumericField1,NumericField2, AggregateField1);

		boolean formcreation = false;

		try {
			formCreationWrapper.API_Wrapper_Forms(fp4);
			formcreation = true;
			logInfo("'"+checkFormName2+"' Form is created");
		} catch (InterruptedException e) {
			logError("Error while '"+checkFormName2+"' form creation");
			formcreation = false;
		}

		TestValidation.IsTrue(formcreation, 
				"CREATED form - " + checkFormName2, 
				"Could NOT create form - " + checkFormName2);

		TaskParams tp4 = new TaskParams();
		tp4.FormId = formId;
		tp4.FormName = checkFormName2;
		tp4.LocationInstanceNm = locationInstanceValue;
		tp4.ResourceInstanceNm = resourceInstanceValue1;
		tp4.WorkGroupName = workGroupName;
		tp4.LocationInstanceId = locInstId;
		tp4.ResourceInstanceId = ResInstId1;
		tp4.UserName = username;
		tp4.DueBy =  "";
		tp4.TaskName = checkFormTask2;
		tp4.WorkGroupID = workgroupId;


		taskCreationWrapper.create_Task_Wrapper(tp4);


		int numeric1 = 10, numeric2 = 20;
		int sum = numeric1+numeric2;

		String numericField1 = Integer.valueOf(numeric1).toString();
		String numericField2 = Integer.valueOf(numeric2).toString();
		String sumField = Integer.valueOf(sum).toString();

		HashMap<String, List<String>> allFields = new LinkedHashMap<String, List<String>>();
		allFields.put(Numeric,Arrays.asList(numericField1,numericField2));

		formDetailsPC = new FormDetailsPC();
		formDetailsPC.locationName = locationInstanceValue;
		formDetailsPC.resourceName = resourceInstanceValue1;
		formDetailsPC.selectLocationResource = false;
		formDetailsPC.fieldDetails = allFields;

		InboxScreen selectInboxTab  = commonScreen.selectInbox();
		TestValidation.IsFalse(selectInboxTab.error,
				"OPENED Inbox Tab", 
				"COULD not open Inbox Tab");

		boolean refresh  = selectInboxTab.refreshInbox();
		TestValidation.IsTrue(refresh,
				"Refreshed Inbox", 
				"COULD not refresh Inbox");

		boolean openTask = selectInboxTab.selectOpenTask(checkFormTask2, false);
		TestValidation.IsTrue(openTask, 
				"SELECTED & opened task - "+checkFormTask2, 
				"FAILED to open task - "+checkFormTask2);

		boolean submitData = formview.fillDataInForm1(formDetailsPC);
		TestValidation.IsTrue(submitData, 
				"SUBMITTED data for task - "+checkFormTask2, 
				"FAILED ro submit data for task - "+checkFormTask2);

		try {

			driver = launchbrowser();
			controlActions = new ControlActions(driver);
			controlActions.getUrl(pcAppTenantURL);
			login = new LoginPage(driver);	
			home = new HomePage(driver);
			fbp = new FSQABrowserPage(driver);
			fbrp = new FSQABrowserRecordsPage(driver);

			if(!login.performLogin(username,changedpassword,newpassword)) {
				TCGFailureMsg = "Could NOT login to application";
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}

			home.clickFSQABrowserMenu();

			boolean navigate = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.EQUIPMENT,FSQATAB.RECORDS);
			TestValidation.IsTrue(navigate, 
					"Navigated to FSQABrowser > Records ", 
					"Failed to navigate to FSQABrowser > Records Tab");

			fbp.openAndApplySettingsForColumn(COLUMNHEADER.FORMNAME, COLUMNSETTING.FILTER,checkFormName2);
			fbp.openAndApplySettingsForColumn(COLUMNHEADER.COMPLETEDBY, COLUMNSETTING.FILTER,username);

			boolean verifySubmissionOnWeb = fbp.openForDetails(1);
			TestValidation.IsTrue(verifySubmissionOnWeb, 
					"Task submission is shown in web app records - "+checkFormName2, 
					"FAILED to verify task submission at web app records -"+checkFormName2);

			boolean verifyNumValue = fbrp.verifyFieldValues(AggSumRange, Arrays.asList(sumField));
			TestValidation.IsTrue(verifyNumValue, 
					"VERIFIED value for Aggregate (Sum Range) field type",
					"FAILED to verify value for Aggregate (Sum Range) field type");

		} catch (Exception e) {
			logError("Failed to verify record on web app - "+e.getMessage());	
		}finally {
			driver.close();
		}	

	}

	@Test(description = "Inbox || Verify 'No Due Date' field on Inbox", priority= 21)
	public void TestCase_51302() {

		InboxScreen selectInboxTab  = commonScreen.selectInbox();
		TestValidation.IsFalse(selectInboxTab.error,
				"OPENED Inbox Tab", 
				"COULD not open Inbox Tab");

		boolean search3 = selectInboxTab.searchTask(noDueDateTask);
		TestValidation.IsTrue(search3,
				"No Due task is visible", 
				"COULD not find no due task");

	}

	@Test(description = "Inbox || User should get task count as 0 if task is not available", priority= 22)
	public void TestCase_37957() throws InterruptedException {

		auth.setAccessToken();

		InboxScreen selectInboxTab  = commonScreen.selectInbox();
		TestValidation.IsFalse(selectInboxTab.error,
				"OPENED Inbox Tab", 
				"COULD not open Inbox Tab");

		taskCreationWrapper.Unlink_WGUser(workGroupName,workgroupId,username);

		Thread.sleep(5000);

		boolean refresh = selectInboxTab.refreshInbox();
		TestValidation.IsTrue(refresh,
				"Refreshed Inbox",
				"COULD not refresh Inbox");

		boolean verifyInboxTaskCount  = inboxScreen.verifyTaskCount(0);
		TestValidation.IsTrue(verifyInboxTaskCount, 
				"VERIFIED task count - "+totalTaskCount, 
				"FAILED to verify task count "+totalTaskCount);

		boolean gridverify = inboxScreen.listView();
		TestValidation.IsTrue(gridverify, 
				"No task is there in Inbox", 
				"Tasks are present");

	}

	@AfterClass(alwaysRun = true)
	public void closeBrowser() throws InterruptedException {
		PCAppDriver.close();
	}

}
