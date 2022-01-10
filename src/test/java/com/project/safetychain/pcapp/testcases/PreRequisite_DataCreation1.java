package com.project.safetychain.pcapp.testcases;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.testng.SkipException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import com.project.safetychain.api.models.support.TCG_ChartBuilder_Wrapper;
import com.project.safetychain.api.models.support.TCG_DeviceManagementFlows_Wrapper;
import com.project.safetychain.api.models.support.TCG_FormCreation_Wrapper;
import com.project.safetychain.api.models.support.TCG_TaskCreation_Wrapper;
import com.project.safetychain.api.models.support.TCG_UserFlows_Wrapper;
import com.project.safetychain.api.models.support.Support.ChartBuilder;
import com.project.safetychain.api.models.support.Support.ChartResources;
import com.project.safetychain.api.models.support.Support.Chart_Rules;
import com.project.safetychain.api.models.support.Support.Chart_Template_TYPES;
import com.project.safetychain.api.models.support.Support.Compliance;
import com.project.safetychain.api.models.support.Support.DPT_FIELD_TYPES;
import com.project.safetychain.api.models.support.Support.DPT_FORM_TYPES;
import com.project.safetychain.api.models.support.Support.DefaultChart;
import com.project.safetychain.api.models.support.Support.Element_Types;
import com.project.safetychain.api.models.support.Support.FormFieldParams;
import com.project.safetychain.api.models.support.Support.FormFieldParamsCompliance;
import com.project.safetychain.api.models.support.Support.FormParams;
import com.project.safetychain.api.models.support.Support.IDENTIFIER_TYPES;
import com.project.safetychain.api.models.support.Support.TaskParams;
import com.project.safetychain.api.models.support.Support.UserParams;
import com.project.safetychain.pcapp.pageobjects.FileOperations;
import com.project.safetychain.pcapp.pageobjects.FileOperations.AvailableFormDetails;
import com.project.safetychain.pcapp.pageobjects.FileOperations.AvailableOtherDetails;
import com.project.safetychain.pcapp.pageobjects.FileOperations.AvailableTaskDetails;
import com.project.safetychain.pcapp.pageobjects.FileOperations.AvailableUserDetails;
import com.project.safetychain.webapp.pageobjects.CommonPage;
import com.project.safetychain.webapp.pageobjects.DevicesPage;
import com.project.safetychain.webapp.pageobjects.DocumentManagementPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage;
import com.project.safetychain.webapp.pageobjects.HomePage;
import com.project.safetychain.webapp.pageobjects.LoginPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.COLUMNHEADER;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.COLUMNSETTING;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.FSQATAB;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.ComplianceRuleConfig;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.DependencyRuleConfig;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.ExpressionRuleConfig;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FORMRESOURCETYPES;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FORMTYPES;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.RuleProperties;
import com.project.safetychain.webapp.pageobjects.UserManagerPage.UsrDetailParams;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.utilities.ApiUtils;
import com.project.utilities.CommonMethods;
import com.project.utilities.ControlActions;
import com.project.utilities.DateTime;

public class PreRequisite_DataCreation1 extends TestBase{

	ApiUtils apiUtils = null;

	DateTime dateTimeDetails;

	public static String locationCategoryValue1;
	public static String locationInstanceValue1;
	public static String resourceCategoryType1 = FORMRESOURCETYPES.ITEMS;
	public static String resourceCategoryValue1;
	public static String resourceInstanceValue1,resourceInstanceValue2;

	public static String formType1 = FORMTYPES.CHECK;
	public static String checkFormName;

	public static String formType2 = FORMTYPES.QUESTIONNAIRE;
	public static String questionnaireFormName;

	public static String formType3 = FORMTYPES.AUDIT;
	public static String auditFormName1;

	public static String formType4 = FORMTYPES.AUDIT;
	public static String auditFormName2;

	public static String noteText = "Test PC Form", documentTypeName, documentName = "upload.png";

	public static String filePath = System.getProperty("user.dir") + "\\test-data-files\\UploadDocuments\\"+documentName;

	public static String xBARR_ChartName, sum_ChartName;
	public static String XBarR_ChartLinkName, SumChart_LinkName;

	public static String workGroupName;
	public static String pastDueTask, dueTodayTask, dueLaterTask, noDueDateTask;
	public static String saveTask, submitTask;

	public static String deviceName;

	public static String roleName;
	public static String userUN;
	public static String userFN;
	public static String userLN;
	public static List<String> userLocation;
	public static List<String> userRole;
	public static List<String> userWorkgroup;
	public static String userPin;

	public static String userPassword, userNewPassword;

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

	String AggSrcNumericFieldName = "Num 1",
			AggregateFieldName = "Agg 1",
			IdentifierFieldName = "IDN 1",
			PropertyFieldName = "Num 2";

	public static String fieldMin = "1", fieldTar = "2", fieldMax = "3", fieldUOM = "KG";

	String SectionName = "Section 1", GroupName = "Group 1";


	//Dynamic Short Names
	String formGroupName1 = null,
			formNumericFieldName1 = null,
			formDateFieldName1 = null;

	//Web App
	ControlActions controlActionsWeb;
	CommonPage mainPage;
	LoginPage login;
	HomePage homePage;
	DocumentManagementPage documentPage;
	DevicesPage devicePage;
	FSQABrowserPage fsqaBrowser;
	FormDesignerPage formDesignerPage;
	UsrDetailParams udp;

	FileOperations storeData, fetchData;
	AvailableFormDetails availableData;
	AvailableTaskDetails taskData;
	AvailableUserDetails userData;
	AvailableOtherDetails otherData;

	@Test(alwaysRun = true, description= "Pre-Requisite Data Creation For PC App Testing")
	public void TestCase_41441() {

		apiUtils = new ApiUtils();

		dateTimeDetails = new DateTime();

		storeData = new FileOperations();
		availableData = new AvailableFormDetails();
		userData = new AvailableUserDetails();
		taskData = new AvailableTaskDetails();
		otherData = new AvailableOtherDetails();

		TCG_FormCreation_Wrapper formCreationWrapper = new TCG_FormCreation_Wrapper();
		TCG_ChartBuilder_Wrapper chartBuilderWrapper = new TCG_ChartBuilder_Wrapper();
		TCG_TaskCreation_Wrapper taskCreationWrapper = new TCG_TaskCreation_Wrapper();

		locationCategoryValue1 = CommonMethods.dynamicText("LCat");
		locationInstanceValue1 = CommonMethods.dynamicText("LInst1");
		resourceCategoryValue1 = CommonMethods.dynamicText("RCat");
		resourceInstanceValue1 = CommonMethods.dynamicText("RInst1");
		resourceInstanceValue2 = CommonMethods.dynamicText("RInst2");

		checkFormName = CommonMethods.dynamicText("PC_Form1");
		questionnaireFormName = CommonMethods.dynamicText("PC_Form2");
		auditFormName1 = CommonMethods.dynamicText("PC_Form3");
		auditFormName2 = CommonMethods.dynamicText("PC_Form4");

		xBARR_ChartName = CommonMethods.dynamicText("PC_Auto_XBarR Template");
		XBarR_ChartLinkName = CommonMethods.dynamicText("XBarR type Chart");

		sum_ChartName = CommonMethods.dynamicText("PC_Auto_Sum Template");
		SumChart_LinkName = CommonMethods.dynamicText("SumChart type Chart");

		workGroupName = CommonMethods.dynamicText("PC_WG");
		pastDueTask = CommonMethods.dynamicText("PastDue");
		dueTodayTask = CommonMethods.dynamicText("DueToday");
		dueLaterTask = CommonMethods.dynamicText("DueLater");
		noDueDateTask = CommonMethods.dynamicText("NoDueDate");
		saveTask = CommonMethods.dynamicText("SaveTask");
		submitTask  = CommonMethods.dynamicText("SubmitTask");

		documentTypeName = CommonMethods.dynamicStrictText("PC_Doc");

		deviceName = CommonMethods.dynamicStrictText("PCDevice");

		userUN = CommonMethods.dynamicText("UWP");
		userFN = CommonMethods.dynamicText("UWP");

		availableData.Location_Resource = new LinkedHashMap<String,List<String>>();
		availableData.CheckForm = checkFormName;
		availableData.QuestionnaireForm = questionnaireFormName;
		availableData.Audit_Form1 = auditFormName1;
		availableData.Audit_Form2 = auditFormName2;
		availableData.Location_Resource.put(locationInstanceValue1, Arrays.asList(resourceInstanceValue1,resourceInstanceValue2));

		userData.username = userUN;
		userData.password = GenericNewPassword;

		taskData.pastDueTask = pastDueTask;
		taskData.dueTodayTask = dueTodayTask;
		taskData.dueLaterTask = dueLaterTask;
		taskData.noDueDateTask = noDueDateTask;
		taskData.saveTask = saveTask;
		taskData.submitTask = submitTask;
		taskData.workGroupName = workGroupName;

		otherData.deviceName = deviceName;

		TestValidation.IsTrue(storeData.setCreatedData(availableData, taskData, userData, otherData), 
				"SAVED new prerequisite data", 
				"Could NOT save prerequisite data");

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
		SelectOneField.SelectOptions = Arrays.asList("1","2","3","4");

		FormFieldParams SelectMultipleField = new FormFieldParams();
		SelectMultipleField.DPTFieldType = DPT_FIELD_TYPES.SELECT_MULTIPLE;
		SelectMultipleField.SelectOptions = Arrays.asList("1","2","3","4");
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

		FormFieldParams NumericField11 = new FormFieldParams();
		NumericField11.DPTFieldType = DPT_FIELD_TYPES.NUMERIC;
		NumericField11.Name = AggSrcNumericFieldName;
		NumericField11.Repeat = "2";

		//		FormFieldParams NumericField12 = new FormFieldParams();
		//		NumericField12.DPTFieldType = DPT_FIELD_TYPES.NUMERIC;
		//		NumericField12.Name = AggSrcNumericFieldName;

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
		IdentifierField1.InputMask = "9009";
		IdentifierField1.Name = IdentifierFieldName;

		FormFieldParams AggregateField1 = new FormFieldParams();
		AggregateField1.DPTFieldType = DPT_FIELD_TYPES.AGGREGATE;
		AggregateField1.Name = AggregateFieldName;
		AggregateField1.SelectedFunction = "Sum";
		AggregateField1.SelectedSource = AggSrcNumericFieldName;

		Element_Types Group1 = new Element_Types();
		Group1.ElementType = DPT_FIELD_TYPES.FIELD_GROUP;
		Group1.Name = GroupName;

		DateField = new FormFieldParams();
		DateField.DPTFieldType = DPT_FIELD_TYPES.DATE;
		DateField.Name = DateFieldName;

		Group1.formFieldParams = Arrays.asList(DateField);

		formId = apiUtils.getUUID();

		FormParams fp2 = new FormParams();
		fp2.FormId = formId;
		fp2.FormName = questionnaireFormName;
		fp2.type = DPT_FORM_TYPES.QUESTIONNAIRE;
		fp2.ResourceType =resourceCategoryType1;
		fp2.ResourceCategoryNm = resourceCategoryValue1;
		fp2.ItemsResources = resourceCatMap;
		fp2.SectionElements = Arrays.asList(Group1);

		fp2.formElements = Arrays.asList(NumericField11, NumericField2, IdentifierField1, AggregateField1);

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

		FormParams fp3 = new FormParams();
		fp3.FormId = formId;
		fp3.FormName = auditFormName1;
		fp3.type = DPT_FORM_TYPES.AUDIT;
		fp3.ResourceType = resourceCategoryType1;
		fp3.ResourceCategoryNm = resourceCategoryValue1;
		fp3.ItemsResources = resourceCatMap;
		fp3.SectionElements = Arrays.asList(Section1);

		try {
			formCreationWrapper.API_Wrapper_Forms(fp3);
			formcreation = true;
			logInfo("'"+auditFormName1+"' Form is created");
		} catch (InterruptedException e) {
			logError("Error while '"+auditFormName1+"' form creation");
			formcreation = false;
		}
		TestValidation.IsTrue(formcreation, 
				"CREATED form - " + auditFormName1, 
				"Could NOT create form - " + auditFormName1);

		DefaultChart defaultChart = new DefaultChart();

		ChartResources chartResources1 = new ChartResources();
		chartResources1.ResourceName = resourceInstanceValue1;
		chartResources1.ResourceId = ResInstId1;

		ChartResources chartResources2 = new ChartResources();
		chartResources2.ResourceName = resourceInstanceValue2;
		chartResources2.ResourceId = ResInstId2;
		try {
			ChartBuilder chartBuilder = new ChartBuilder();
			chartBuilder.chartConfList = Arrays.asList(Chart_Rules.Rule_2, Chart_Rules.Rule_1);
			chartBuilder.Name = xBARR_ChartName;
			chartBuilder.chartLinkingName = XBarR_ChartLinkName;
			chartBuilder.formId = formId;
			chartBuilder.formName = auditFormName1;
			chartBuilder.locationId = locInstId;
			chartBuilder.chartTemplateType = Chart_Template_TYPES.XBarR;
			chartBuilder.chartResourceList = Arrays.asList(chartResources1, chartResources2);
			chartBuilder.ChartFieldsList = Arrays.asList(NumericFieldName1, NumericFieldName2);
			chartBuilder.defaultChart = defaultChart;
			chartBuilderWrapper.create_Chart_Wrapper(chartBuilder);

			chartBuilder = new ChartBuilder();
			chartBuilder.Name = sum_ChartName;
			chartBuilder.chartLinkingName = SumChart_LinkName;
			chartBuilder.formId = formId;
			chartBuilder.formName = auditFormName1;
			chartBuilder.chartTemplateType = Chart_Template_TYPES.SumChart;
			chartBuilder.ChartFieldsList = Arrays.asList(NumericFieldName1, NumericFieldName2);
			chartBuilder.defaultChart = defaultChart;

			chartBuilderWrapper.create_Chart_Wrapper(chartBuilder);

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Section1 = new Element_Types();
		Section1.ElementType = DPT_FIELD_TYPES.SECTION;
		Section1.Name = SectionName;

		NumericField1 = new FormFieldParams();
		NumericField1.DPTFieldType = DPT_FIELD_TYPES.NUMERIC;
		NumericField1.Name = NumericFieldName1;

		Section1.formFieldParams = Arrays.asList(NumericField1);

		formId = apiUtils.getUUID();

		FormParams fp4 = new FormParams();
		fp4.FormId = formId;
		fp4.FormName = auditFormName2;
		fp4.type = DPT_FORM_TYPES.AUDIT;
		fp4.ResourceType = resourceCategoryType1;
		fp4.ResourceCategoryNm = resourceCategoryValue1;
		fp4.ItemsResources = resourceCatMap;
		fp4.SectionElements = Arrays.asList(Section1);

		logInfo("fp.formElements = " + fp4.formElements);

		try {
			formCreationWrapper.API_Wrapper_Forms(fp4);
			formcreation = true;
			logInfo("'"+auditFormName2+"' Form is created");
		} catch (InterruptedException e) {
			logError("Error while '"+auditFormName2+"' form creation");
			formcreation = false;
		}

		TestValidation.IsTrue(formcreation, 
				"CREATED form - " + auditFormName2, 
				"Could NOT create form - " + auditFormName2);
		userRole = new ArrayList<String>();
		userWorkgroup = new ArrayList<String>();

		roleName = "Superadmin";
		userLN = "User";
		userPassword = GenericPassword;
		userNewPassword = GenericNewPassword;
		userRole.add(roleName);
		userPin = "1234";

		TCG_UserFlows_Wrapper userCreationWrapper = new TCG_UserFlows_Wrapper();

		UserParams userDetails = new UserParams();
		userDetails.Username = userUN;
		userDetails.ClearPassword = userPassword;
		userDetails.FirstName = userFN;
		userDetails.LastName = userLN;
		userDetails.Email = "sc@pcappm.com";
		userDetails.TimeZone = "U.S. Pacific";
		userDetails.LocationCat = locationCategoryValue1;
		userDetails.LocationNmIds = Arrays.asList(locationInstanceValue1);
		userDetails.Roles = userRole;
		userDetails.Pin = "1234";

		boolean userCreation = false;

		try {
			userCreationWrapper.createUser_Wrapper(userDetails);
			userCreation = true;
			logInfo("'"+userUN+"' User is created");
		} catch (InterruptedException e) {
			logError("Error while '"+userUN+"' user creation");
			userCreation = false;
		}

		TestValidation.IsTrue(userCreation, 
				"CREATED User - " + userUN, 
				"Could NOT create user - " + userUN);

		TaskParams tp = new TaskParams();
		tp.FormId = formId;
		tp.FormName = auditFormName2;
		tp.LocationInstanceNm = locationInstanceValue1;
		tp.ResourceInstanceNm = resourceInstanceValue1;
		tp.TaskName = auditFormName2;
		tp.WorkGroupName = workGroupName;
		tp.LocationInstanceId = locInstId;
		tp.ResourceInstanceId = ResInstId1;
		tp.UserName = userUN;

		boolean taskCreationStatus = true;

		try {

			taskCreationWrapper.create_Link_workGroup_Wrapper(tp);

			tp.DueBy =  "";
			tp.TaskName = noDueDateTask;
			taskCreationWrapper.create_Task_Wrapper(tp);

			tp.TaskName = saveTask;
			taskCreationWrapper.create_Task_Wrapper(tp);

			tp.TaskName = submitTask;
			taskCreationWrapper.create_Task_Wrapper(tp);

			tp.TaskName = pastDueTask;
			tp.DueBy = dateTimeDetails.getDateTime("Day", -4, 0, 0, false, false);
			taskCreationWrapper.create_Task_Wrapper(tp);

			tp.TaskName = dueTodayTask;
			tp.DueBy = dateTimeDetails.getDateTime("Day", 0, 2, 0, false, false);
			taskCreationWrapper.create_Task_Wrapper(tp);

			tp.TaskName = dueLaterTask;
			tp.DueBy = dateTimeDetails.getDateTime("Day", 2, 0, 0, false, false);
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

		apiUtils = new ApiUtils();

		TCG_DeviceManagementFlows_Wrapper deviceCreationWrapper = new TCG_DeviceManagementFlows_Wrapper();
		boolean	deviceCreation = false;

		try {
			if(deviceCreationWrapper.createDevice(deviceName)) {
				deviceCreation = true;
			}else {
				deviceCreation = false;
			}
		}catch(Exception e) {
			deviceCreation = false;
		}

		TestValidation.IsTrue(deviceCreation, 
				"ADDED new device - " + deviceName, 
				"FAILED to add new device - " + deviceName);

		try {
			formGroupName1 = chartBuilderWrapper.getFormFieldShortName(questionnaireFormName, GroupName);
			formNumericFieldName1 = chartBuilderWrapper.getFormFieldShortName(questionnaireFormName, PropertyFieldName);
			formDateFieldName1 = chartBuilderWrapper.getFormFieldShortName(questionnaireFormName, DateFieldName);
			driver = launchbrowser();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		controlActionsWeb = new ControlActions(driver);
		mainPage = new CommonPage(driver);
		login = new LoginPage(driver);
		documentPage =  new DocumentManagementPage(driver);
		fsqaBrowser = new FSQABrowserPage(driver);
		formDesignerPage = new FormDesignerPage(driver);
		devicePage = new DevicesPage(driver);
		udp = new UsrDetailParams();

		controlActionsWeb.getUrl(pcAppTenantURL);

		boolean userLogin = login.loginAfterUserCreation(userUN, userPassword, userNewPassword);
		TestValidation.IsTrue(userLogin, "LOGGED IN with new user - "+userUN, "Could NOT login with new user - "+userUN);

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

		boolean applyfilter = fsqaBrowser.openAndApplySettingsForColumn(COLUMNHEADER.FORMNAME, COLUMNSETTING.FILTER,questionnaireFormName);
		if(!applyfilter) {
			TCGFailureMsg = "FAILED to apply filter on " + COLUMNHEADER.FORMNAME;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		boolean editForm = fsqaBrowser.editSelectForm(questionnaireFormName);
		if(!applyfilter) {
			TCGFailureMsg = "FAILED to open form - "+questionnaireFormName+" in edit mode ";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		HashMap<String, RuleProperties> fieldRuleSettings = new LinkedHashMap<String, RuleProperties>(); 
		RuleProperties ruleSettings = new RuleProperties();
		ruleSettings.depdendencyRuleConfig = new DependencyRuleConfig();
		ruleSettings.depdendencyRuleConfig.IF_FIELD = formNumericFieldName1;
		ruleSettings.depdendencyRuleConfig.VALUE_VALUE = "2";
		fieldRuleSettings.put(formGroupName1, ruleSettings);
		ruleSettings = new RuleProperties();
		ruleSettings.depdendencyRuleConfig = new DependencyRuleConfig();
		ruleSettings.depdendencyRuleConfig.IF_FIELD = formNumericFieldName1;
		ruleSettings.depdendencyRuleConfig.VALUE_VALUE = "2";
		ruleSettings.expressionRuleConfig = new ExpressionRuleConfig();
		ruleSettings.expressionRuleConfig.IF_FIELD = formNumericFieldName1;
		ruleSettings.expressionRuleConfig.VALUE_VALUE = "2";
		ruleSettings.expressionRuleConfig.THEN_VALUE = dateTimeDetails.getDate("Day", 0);
		ruleSettings.expressionRuleConfig.ELSE_VALUE = dateTimeDetails.getDate("Day", -1);
		ruleSettings.complianceRuleConfig = new ComplianceRuleConfig();
		ruleSettings.complianceRuleConfig.IF_FIELD = formDateFieldName1;
		ruleSettings.complianceRuleConfig.VALUE_VALUE = dateTimeDetails.getDate("Day", 0);
		ruleSettings.complianceRuleConfig.RESOURCE = resourceInstanceValue1;
		fieldRuleSettings.put(formDateFieldName1, ruleSettings);

		if(!formDesignerPage.setFieldRules(fieldRuleSettings)) {
			TCGFailureMsg = "Could NOT set rules for field - " + DateFieldName;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}	

		TestValidation.IsTrue(formDesignerPage.releaseForm(questionnaireFormName), 
				"RELEASED form - " +questionnaireFormName+ " with rules from Web App", 
				"Could NOT release form - " + questionnaireFormName+" with rules from Web App");

		if(mainPage.clickFSQABrowserMenu().error) {
			TCGFailureMsg = "Could NOT Navigate to 'FSQA Browser";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}	

		itemsFormTab = fsqaBrowser.selectResourceDropDownandNavigate(FORMRESOURCETYPES.ITEMS,FSQATAB.FORMS);
		if(!itemsFormTab) {
			TCGFailureMsg = "FAILED to navigate to Items - FSQABrowser > Forms Tab";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		applyfilter = fsqaBrowser.openAndApplySettingsForColumn(COLUMNHEADER.FORMNAME, COLUMNSETTING.FILTER,auditFormName1);
		if(!applyfilter) {
			TCGFailureMsg = "FAILED to apply filter on " + COLUMNHEADER.FORMNAME;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		editForm = fsqaBrowser.editSelectForm(auditFormName1);
		if(!editForm) {
			TCGFailureMsg = "FAILED to open form - "+auditFormName1+" in edit mode ";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		if(!formDesignerPage.addFormElements(noteText,documentTypeName, documentName)) {
			TCGFailureMsg = "Could NOT add form elements in form - " + auditFormName1;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		TestValidation.IsTrue(formDesignerPage.releaseForm(auditFormName1), 
				"RELEASED form - " +auditFormName1+" with form elements from Web App", 
				"Could NOT release form - " + auditFormName1+" with form elements from Web App");
	}

	@AfterTest(alwaysRun = true)
	public void closeBrowser() throws InterruptedException {
		driver.close();
	}

}
