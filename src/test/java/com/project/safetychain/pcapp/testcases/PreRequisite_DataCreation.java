package com.project.safetychain.pcapp.testcases;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.testng.SkipException;
import org.testng.annotations.Test;

import com.project.safetychain.pcapp.pageobjects.FileOperations;
import com.project.safetychain.pcapp.pageobjects.FileOperations.AvailableFormDetails;
import com.project.safetychain.pcapp.pageobjects.FileOperations.AvailableOtherDetails;
import com.project.safetychain.pcapp.pageobjects.FileOperations.AvailableTaskDetails;
import com.project.safetychain.pcapp.pageobjects.FileOperations.AvailableUserDetails;
import com.project.safetychain.webapp.pageobjects.ChartBuilderPage;
import com.project.safetychain.webapp.pageobjects.CommonPage;
import com.project.safetychain.webapp.pageobjects.DevicesPage;
import com.project.safetychain.webapp.pageobjects.DocumentManagementPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.COLUMNHEADER;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.COLUMNSETTING;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.FSQATAB;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.TASKDETAILS;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage;
import com.project.safetychain.webapp.pageobjects.FormsManagerChartsPage;
import com.project.safetychain.webapp.pageobjects.FormsManagerPage;
import com.project.safetychain.webapp.pageobjects.HomePage;
import com.project.safetychain.webapp.pageobjects.LoginPage;
import com.project.safetychain.webapp.pageobjects.ManageLocationPage;
import com.project.safetychain.webapp.pageobjects.ManageResourcePage;
import com.project.safetychain.webapp.pageobjects.ResourceDesignerPage;
import com.project.safetychain.webapp.pageobjects.UserManagerPage;
import com.project.safetychain.webapp.pageobjects.WorkGroupsPage;
import com.project.safetychain.webapp.pageobjects.ChartBuilderPage.ChartTypes;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.AGGREGATE_FUNCTION;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.ComplianceRuleConfig;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.DependencyRuleConfig;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.ElementProperties;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.ExpressionRuleConfig;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FIELD_TYPES;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FORMRESOURCETYPES;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FORMTYPES;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FormDesignParams;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FormFieldParams;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.IDENTIFIER_TYPE;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.RuleProperties;
import com.project.safetychain.webapp.pageobjects.FormsManagerChartsPage.FieldSelection;
import com.project.safetychain.webapp.pageobjects.FormsManagerChartsPage.ResourceSelection;
import com.project.safetychain.webapp.pageobjects.ManageLocationPage.LocationInstanceParams;
import com.project.safetychain.webapp.pageobjects.ResourceDesignerPage.CATEGORYTYPES;
import com.project.safetychain.webapp.pageobjects.UserManagerPage.UsrDetailParams;
import com.project.safetychain.webapp.testcases.support.FormOperations.FormDetails;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.utilities.CommonMethods;
import com.project.utilities.ControlActions;
import com.project.utilities.DateTime;

public class PreRequisite_DataCreation extends TestBase{

	//Web App
	ControlActions controlActionsWeb;
	LoginPage login;
	HomePage homePage;
	CommonPage mainPage;
	ResourceDesignerPage resourceDesigner;
	ManageResourcePage manageResource;
	ManageLocationPage locations;
	FormDesignerPage formDesignerPage;
	WorkGroupsPage workgroup;
	FSQABrowserPage fsqaBrowser;
	DevicesPage devicePage;
	DocumentManagementPage documentPage;
	ChartBuilderPage chartBuilderPage;
	FormsManagerPage formsManagerPage;
	FormsManagerChartsPage formsManagerChartPage;

	FieldSelection fieldSelect;
	ResourceSelection resourceSelect;

	DateTime dateTimeDetails;


	LocationInstanceParams locationInstance1, locationInstance2;
	FormFieldParams ffp1,ffp2;
	FormDesignParams fp;
	FormDetails formDetails;
	UsrDetailParams udp;
	TASKDETAILS taskDetails;

	FileOperations storeData, fetchData;
	AvailableFormDetails availableData;
	AvailableTaskDetails taskData;
	AvailableUserDetails userData;
	AvailableOtherDetails otherData;

	public static String formType1 = FORMTYPES.CHECK;
	public static String checkFormName;

	public static String formType2 = FORMTYPES.QUESTIONNAIRE;
	public static String questionnaireFormName;

	public static String formType3 = FORMTYPES.AUDIT;
	public static String auditFormName1;

	public static String formType4 = FORMTYPES.AUDIT;
	public static String auditFormName2;

	public static String locationCategoryValue1;
	public static String locationInstanceValue1;
	public static String resourceCategoryType1 = FORMRESOURCETYPES.ITEMS;
	public static String resourceCategoryValue1;
	public static String resourceInstanceValue1,resourceInstanceValue2;
	public static String deviceName;

	public static String noteText = "Test PC Form", documentTypeName, documentName = "upload.png";
	public static String filePath = System.getProperty("user.dir") + "\\test-data-files\\UploadDocuments\\"+documentName;

	public static String roleName;
	public static String userUN;
	public static String userFN;
	public static String userLN;
	public static List<String> userLocation;
	public static List<String> userRole;
	public static List<String> userWorkgroup;
	public static String userPin;

	public static String workGroupName;

	public static String userPassword, userNewPassword;

	public static String pastDueTask, dueTodayTask, dueLaterTask, noDueDateTask;
	public static String saveTask, submitTask;
	public static String xBARR_ChartName, sumChartName;



	String TextFieldName = "Text 1",
			ParagraphFieldName = "Para 1",
			SelectOneFieldName = "SO 1",
			selectMultipleFieldName = "SM 1",
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

	String sectionName = "Section 1", groupName = "Group 1";


	@Test(alwaysRun = true, description= "Pre-Requisite Data Creation For PC App Testing")
	public void TestCase_41441() {
		List<String> selectOneMultipleFieldOptions = Arrays.asList("1","2","3","4");
		String selectOneFieldOptions[][] = {{"1","100"},{"2","200"}};
		String selectMultipleFieldOptions[][] = {{"1","100"},{"2","200"}};
		String questionWeight1 = "200",questionWeight2 = "300";
		HashMap<String, List<String>> resourceInstances = new LinkedHashMap<String, List<String>>();
		createPCPreReqData = false;

		try {

			driver = launchbrowser();
			controlActionsWeb = new ControlActions(driver);
			mainPage = new CommonPage(driver);
			login = new LoginPage(driver);
			resourceDesigner = new ResourceDesignerPage(driver);
			locations = new ManageLocationPage(driver);
			manageResource = new ManageResourcePage(driver);
			formDesignerPage = new FormDesignerPage(driver);
			workgroup = new WorkGroupsPage(driver);
			fsqaBrowser = new FSQABrowserPage(driver);
			devicePage = new DevicesPage(driver);
			chartBuilderPage = new ChartBuilderPage(driver);
			formsManagerPage = new FormsManagerPage(driver);
			formsManagerChartPage  = new FormsManagerChartsPage(driver);
			dateTimeDetails = new DateTime(driver);
			fieldSelect = new FieldSelection();
			resourceSelect = new ResourceSelection();

			storeData = new FileOperations();
			availableData = new AvailableFormDetails();
			userData = new AvailableUserDetails();
			taskData = new AvailableTaskDetails();
			otherData = new AvailableOtherDetails();

			controlActionsWeb.getUrl(applicationUrl);
			homePage = login.performLogin(adminUsername, adminPassword);
			if(homePage.error) {
				TCGFailureMsg = "Could NOT login to application";
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}

			userUN = CommonMethods.dynamicText("UWP");
			userFN = CommonMethods.dynamicText("UWP");
			workGroupName = CommonMethods.dynamicText("PC_WG");
			pastDueTask = CommonMethods.dynamicText("PastDue");
			dueTodayTask = CommonMethods.dynamicText("DueToday");
			dueLaterTask = CommonMethods.dynamicText("DueLater");
			noDueDateTask = CommonMethods.dynamicText("NoDueDate");
			saveTask = CommonMethods.dynamicText("SaveTask");
			submitTask  = CommonMethods.dynamicText("SubmitTask");

			deviceName = CommonMethods.dynamicStrictText("PCDevice");

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

			if(createPCPreReqData) {

				locationCategoryValue1 = CommonMethods.dynamicStrictText("PCLocCat");
				locationInstanceValue1 = CommonMethods.dynamicStrictText("PCLocInst1");
				resourceCategoryValue1 = CommonMethods.dynamicStrictText("PCResCat");
				resourceInstanceValue1 = CommonMethods.dynamicStrictText("PCResInst1");
				resourceInstanceValue2 = CommonMethods.dynamicStrictText("PCResInst2");

				documentTypeName = CommonMethods.dynamicStrictText("PC_Doc");
				xBARR_ChartName = CommonMethods.dynamicStrictText("PC_XBARR");
				sumChartName = CommonMethods.dynamicStrictText("PC_SUM");

				String resourceInstances1[][] = {{resourceInstanceValue1,"true"},{resourceInstanceValue2,"true"}};
				resourceInstances.put(resourceCategoryType1, Arrays.asList(resourceInstanceValue1, resourceInstanceValue2));

				checkFormName = CommonMethods.dynamicText("PC_CheckForm1");
				questionnaireFormName = CommonMethods.dynamicText("PC_QuestionnaireForm1");
				auditFormName1 = CommonMethods.dynamicText("PC_AuditForm1");
				auditFormName2 = CommonMethods.dynamicText("PC_AuditForm2");

				HashMap<String,String> resourceCategories = new HashMap<String,String>();
				resourceCategories.put(CATEGORYTYPES.LOCATION, locationCategoryValue1);
				resourceCategories.put(CATEGORYTYPES.ITEMS, resourceCategoryValue1);

				availableData.Location_Resource = new LinkedHashMap<String,List<String>>();
				availableData.CheckForm = checkFormName;
				availableData.QuestionnaireForm = questionnaireFormName;
				availableData.Audit_Form1 = auditFormName1;
				availableData.Audit_Form2 = auditFormName2;

				availableData.Location_Resource.put(locationInstanceValue1, Arrays.asList(resourceInstanceValue1,resourceInstanceValue2));


				//				TestValidation.IsTrue(storeData.setCreatedData(availableData, taskData, userData, otherData), 
				//						"SAVED new prerequisite data", 
				//						"Could NOT save prerequisite data");

				documentPage = mainPage.clickdocumentsmenu();
				if(documentPage.error) {
					TCGFailureMsg = "Could NOT open 'Documents'";
					logFatal(TCGFailureMsg);
					throw new SkipException(TCGFailureMsg);
				}

				TestValidation.IsTrue(documentPage.docUploadinDocType(documentTypeName,filePath), 
						"ADDED document type", 
						"Could NOT add document type");

				TestValidation.IsTrue(resourceDesigner.createCategory(resourceCategories), 
						"CREATED Location & Resource category - "+locationCategoryValue1+" & "+resourceCategoryValue1, 
						"Could NOT create Location & Resource category - "+locationCategoryValue1+" & "+resourceCategoryValue1);

				TestValidation.IsTrue(manageResource.addResourceInstances(resourceCategoryType1, resourceCategoryValue1, resourceInstances1, false, locationInstanceValue1), 
						"CREATED resource instances " + resourceInstanceValue1 + " &  "+ resourceInstanceValue2, 
						"Could NOT create resource instances " + resourceInstanceValue1 + " &  "+ resourceInstanceValue2);

				TestValidation.IsTrue(locations.createLocationInstanceLinkUser(locationCategoryValue1,locationInstanceValue1,adminUsername), 
						"CREATED location instance - " + locationInstanceValue1, 
						"Could NOT create location instance - " + locationInstanceValue1);

				TestValidation.IsTrue(locations.linkResourceToLocation(resourceInstances), 
						"LINKED resource instances for - '" + locationCategoryValue1+"' category", 
						"Could NOT link resource instances for - '" + locationCategoryValue1+"' category");

				HashMap<String, List<String>> fields = new LinkedHashMap<String, List<String>>();
				fields.put(FIELD_TYPES.FREE_TEXT, Arrays.asList(TextFieldName));
				fields.put(FIELD_TYPES.PARAGRAPH_TEXT, Arrays.asList(ParagraphFieldName));
				fields.put(FIELD_TYPES.SELECT_ONE, Arrays.asList(SelectOneFieldName));
				fields.put(FIELD_TYPES.SELECT_MULTIPLE, Arrays.asList(selectMultipleFieldName));
				fields.put(FIELD_TYPES.NUMERIC, Arrays.asList(NumericFieldName1));
				fields.put(FIELD_TYPES.DATE, Arrays.asList(DateFieldName));
				fields.put(FIELD_TYPES.TIME, Arrays.asList(TimeFieldName));
				fields.put(FIELD_TYPES.DATE_TIME, Arrays.asList(DateTimeFieldName));

				ffp1 = new FormFieldParams();
				ffp1.FieldDetails = fields;
				ffp1.SelectOneMultipleOptions = selectOneMultipleFieldOptions;

				HashMap<String, List<String>> resource = new LinkedHashMap<String, List<String>>();
				resource.put(resourceCategoryType1, Arrays.asList(resourceCategoryValue1));

				fp = new FormDesignParams();
				fp.FormType = formType1;
				fp.FormName = checkFormName;
				fp.SelectResources = resource;
				fp.DesignFields = Arrays.asList(ffp1);
				fp.ReleaseForm = true;

				formDesignerPage = homePage.clickFormDesignerMenu();
				if(formDesignerPage.error) {
					TCGFailureMsg = "Could NOT navigate to Admin Tools - Form Designer";
					logFatal(TCGFailureMsg);
					throw new SkipException(TCGFailureMsg);
				}

				TestValidation.IsTrue(formDesignerPage.createAndReleaseForm(fp), 
						"CREATED & release form - " + checkFormName, 
						"Could NOT create & released form - " + checkFormName);

				fields = new HashMap<String, List<String>>();
				fields.put(FIELD_TYPES.NUMERIC, Arrays.asList(AggSrcNumericFieldName, AggSrcNumericFieldName, PropertyFieldName));
				fields.put(FIELD_TYPES.AGGREGATE, Arrays.asList(AggregateFieldName));
				fields.put(FIELD_TYPES.IDENTIFIER, Arrays.asList(IdentifierFieldName));
				ffp1 = new FormFieldParams();
				ffp1.FieldDetails = fields;
				ffp1.AgrregateFunction = AGGREGATE_FUNCTION.SUM;
				ffp1.AgrregateSource = AggSrcNumericFieldName;
				ffp1.IdentiferType = IDENTIFIER_TYPE.FREETEXT;
				ffp1.IdentifierValue = Arrays.asList("9009");

				ffp2 = new FormFieldParams();
				fields = new HashMap<String, List<String>>();
				fields.put(FIELD_TYPES.DATE, Arrays.asList(DateFieldName));
				ffp2.GroupName = groupName;
				ffp2.FieldDetails = fields;
				HashMap<String, ElementProperties> fieldSettings = new LinkedHashMap<String, ElementProperties>(); 
				ElementProperties settings = new ElementProperties();
				settings.ALLOW_COMMENTS = true;
				settings.ALLOW_ATTATHMENTS = true;
				settings.SHOW_HINT = true;
				settings.HINT = "PC App Automation field";
				settings.ALLOW_CORRECTION = true;
				settings.CARRYOVER_FIELD = true;
				settings.COMPLIANCE_CONFIG = true;
				settings.COMPLIANCE_VALUES = new String[]{fieldMin, fieldTar, fieldMax, null};
				fieldSettings.put(PropertyFieldName, settings);

				HashMap<String, RuleProperties> fieldRuleSettings = new LinkedHashMap<String, RuleProperties>(); 
				RuleProperties ruleSettings = new RuleProperties();
				ruleSettings.depdendencyRuleConfig = new DependencyRuleConfig();
				ruleSettings.depdendencyRuleConfig.IF_FIELD = NumericFieldName1;
				ruleSettings.depdendencyRuleConfig.VALUE_VALUE = "2";
				fieldRuleSettings.put(groupName, ruleSettings);
				ruleSettings = new RuleProperties();
				ruleSettings.depdendencyRuleConfig = new DependencyRuleConfig();
				ruleSettings.depdendencyRuleConfig.IF_FIELD = NumericFieldName1;
				ruleSettings.depdendencyRuleConfig.VALUE_VALUE = "2";
				ruleSettings.expressionRuleConfig = new ExpressionRuleConfig();
				ruleSettings.expressionRuleConfig.IF_FIELD = NumericFieldName1;
				ruleSettings.expressionRuleConfig.VALUE_VALUE = "2";
				ruleSettings.expressionRuleConfig.THEN_VALUE = dateTimeDetails.getDate("Day", 0);
				ruleSettings.expressionRuleConfig.ELSE_VALUE = dateTimeDetails.getDate("Day", -1);
				ruleSettings.complianceRuleConfig = new ComplianceRuleConfig();
				ruleSettings.complianceRuleConfig.IF_FIELD = DateFieldName;
				ruleSettings.complianceRuleConfig.VALUE_VALUE = dateTimeDetails.getDate("Day", 0);
				ruleSettings.complianceRuleConfig.RESOURCE = resourceInstanceValue1;
				fieldRuleSettings.put(DateFieldName, ruleSettings);

				resource = new HashMap<String, List<String>>();
				resource.put(resourceCategoryType1, Arrays.asList(resourceCategoryValue1));

				fp = new FormDesignParams();
				fp.FormType = formType2;
				fp.FormName = questionnaireFormName;
				fp.SelectResources = resource;
				fp.DesignFields = Arrays.asList(ffp1, ffp2);
				fp.ReleaseForm = false;

				formDesignerPage = homePage.clickFormDesignerMenu();
				if(formDesignerPage.error) {
					TCGFailureMsg = "Could NOT navigate to Admin Tools - Form Designer";
					logFatal(TCGFailureMsg);
					throw new SkipException(TCGFailureMsg);
				}

				TestValidation.IsTrue(formDesignerPage.createAndReleaseForm(fp), 
						"CREATED & saved form - " + questionnaireFormName, 
						"Could NOT save form - " + questionnaireFormName);

				if(!formDesignerPage.navigateToReleaseForm_EditForm(questionnaireFormName)) {
					TCGFailureMsg = "Could NOT open edit form - " + questionnaireFormName;
					logFatal(TCGFailureMsg);
					throw new SkipException(TCGFailureMsg);
				}
				if(!formDesignerPage.setFieldProperties(fieldSettings)) {
					TCGFailureMsg = "Could NOT set properties for field - " + PropertyFieldName;
					logFatal(TCGFailureMsg);
					throw new SkipException(TCGFailureMsg);
				}
				if(!formDesignerPage.navigateToReleaseForm_EditForm(questionnaireFormName)) {
					TCGFailureMsg = "Could NOT open edit form - " + questionnaireFormName;
					logFatal(TCGFailureMsg);
					throw new SkipException(TCGFailureMsg);
				}
				if(!formDesignerPage.setFieldRules(fieldRuleSettings)) {
					TCGFailureMsg = "Could NOT set rules for field - " + DateFieldName;
					logFatal(TCGFailureMsg);
					throw new SkipException(TCGFailureMsg);
				}	
				TestValidation.IsTrue(formDesignerPage.releaseForm(questionnaireFormName), 
						"RELEASED form - " + questionnaireFormName, 
						"Could NOT release form - " + questionnaireFormName);

				fields = new LinkedHashMap<String, List<String>>();
				fields.put(FIELD_TYPES.NUMERIC, Arrays.asList(NumericFieldName1));
				fields.put(FIELD_TYPES.SELECT_ONE, Arrays.asList(SelectOneFieldName));
				ffp1 = new FormFieldParams();
				ffp1.SectionName = sectionName;
				ffp1.FieldDetails = fields;
				ffp1.SelectOneMultipleOptionsWeight = selectOneFieldOptions;
				ffp1.QuestionWeight = questionWeight1;
				fields = new LinkedHashMap<String, List<String>>();
				fields.put(FIELD_TYPES.SELECT_MULTIPLE, Arrays.asList(selectMultipleFieldName));
				fields.put(FIELD_TYPES.NUMERIC, Arrays.asList(NumericFieldName2));
				ffp2 = new FormFieldParams();
				ffp2.GroupName = groupName;
				ffp2.FieldDetails = fields;
				ffp2.SelectOneMultipleOptionsWeight = selectMultipleFieldOptions;
				ffp2.QuestionWeight = questionWeight2;

				resource = new LinkedHashMap<String, List<String>>();
				resource.put(resourceCategoryType1, Arrays.asList(resourceCategoryValue1));

				fp = new FormDesignParams();
				fp.FormType = formType3;
				fp.FormName = auditFormName1;
				fp.SelectResources = resource;
				fp.DesignFields = Arrays.asList(ffp1,ffp2);
				fp.ReleaseForm = false;

				formDesignerPage = homePage.clickFormDesignerMenu();
				if(formDesignerPage.error) {
					TCGFailureMsg = "Could NOT navigate to Admin Tools - Form Designer";
					logFatal(TCGFailureMsg);
					throw new SkipException(TCGFailureMsg);
				}

				TestValidation.IsTrue(formDesignerPage.createAndReleaseForm(fp), 
						"CREATED & saved form - " + auditFormName1, 
						"Could NOT save form - " + auditFormName1);


				if(!formDesignerPage.navigateToReleaseForm_EditForm(auditFormName1)) {
					TCGFailureMsg = "Could NOT open edit form - " + auditFormName1;
					logFatal(TCGFailureMsg);
					throw new SkipException(TCGFailureMsg);
				}
				if(!formDesignerPage.addFormElements(noteText,documentTypeName, documentName)) {
					TCGFailureMsg = "Could NOT add form elements in form - " + auditFormName1;
					logFatal(TCGFailureMsg);
					throw new SkipException(TCGFailureMsg);
				}

				TestValidation.IsTrue(formDesignerPage.releaseForm(auditFormName1), 
						"RELEASED form - " + auditFormName1, 
						"Could NOT release form - " + auditFormName1);


				fields = new LinkedHashMap<String, List<String>>();
				fields.put(FIELD_TYPES.NUMERIC, Arrays.asList(NumericFieldName1));
				ffp1 = new FormFieldParams();
				ffp1.SectionName = sectionName;
				ffp1.FieldDetails = fields;

				resource = new LinkedHashMap<String, List<String>>();
				resource.put(resourceCategoryType1, Arrays.asList(resourceCategoryValue1));

				fp = new FormDesignParams();
				fp.FormType = formType4;
				fp.FormName = auditFormName2;
				fp.SelectResources = resource;
				fp.DesignFields = Arrays.asList(ffp1);
				fp.ReleaseForm = true;

				formDesignerPage = homePage.clickFormDesignerMenu();
				if(formDesignerPage.error) {
					TCGFailureMsg = "Could NOT navigate to Admin Tools - Form Designer";
					logFatal(TCGFailureMsg);
					throw new SkipException(TCGFailureMsg);
				}

				TestValidation.IsTrue(formDesignerPage.createAndReleaseForm(fp), 
						"CREATED & released form - " + auditFormName2, 
						"Could NOT create & release form - " + auditFormName2);

				if(homePage.clickChartBuilderMenu().error) {
					TCGFailureMsg = "Could NOT navigate to Admin Tools - Chart Builder";
					logFatal(TCGFailureMsg);
					throw new SkipException(TCGFailureMsg);
				}

				boolean createchart = chartBuilderPage.addNewChart(sumChartName,ChartTypes.SUMCHART);
				TestValidation.IsTrue(createchart, 
						"Chart Created", 
						"Failed to create Chart");

				createchart = chartBuilderPage.addNewChart(xBARR_ChartName,ChartTypes.XBARR);
				TestValidation.IsTrue(createchart, 
						"Chart Created", 
						"Failed to create Chart");

				if(homePage.clickFormsManagerMenu().error) {
					TCGFailureMsg = "Could NOT navigate to Admin Tools - Forms Manager";
					logFatal(TCGFailureMsg);
					throw new SkipException(TCGFailureMsg);
				}

				TestValidation.IsTrue(formsManagerPage.selectFormAndNavigaToCharts(auditFormName1), 
						"CHART tab is selcted for form - "+auditFormName1, 
						"FAILED to select chart tab for form - "+auditFormName1);

				boolean associatedchart = formsManagerChartPage.createAssociateChart(sumChartName);
				TestValidation.IsTrue(associatedchart, 
						"Associated Chart Created", 
						"Failed to create Associated Chart");

				FieldSelection.MultiSelect = Arrays.asList(NumericFieldName1,NumericFieldName2);
				boolean linkfields = formsManagerChartPage.linkFieldsToCharts(fieldSelect);
				TestValidation.IsTrue(linkfields, 
						"Linked fields to charts", 
						"Failed to Link fields to charts");

				associatedchart = formsManagerChartPage.createAssociateChart(xBARR_ChartName);
				TestValidation.IsTrue(associatedchart, 
						"Associated Chart Created", 
						"Failed to create Associated Chart");

				linkfields = formsManagerChartPage.linkFieldsToCharts(fieldSelect);
				TestValidation.IsTrue(linkfields, 
						"Linked fields to charts", 
						"Failed to Link fields to charts");

				ResourceSelection.resource = resourceInstanceValue1;

				boolean resourceselect = formsManagerChartPage.selectResourceforCharts();
				TestValidation.IsTrue(resourceselect, 
						"Linked fields to charts", 
						"Failed to Link fields to charts");

				logInfo("Created basic Prerequiste data successfully");

			}else {

				fetchData = new FileOperations();
				availableData = fetchData.getCreatedData();

				checkFormName = availableData.CheckForm;
				questionnaireFormName = availableData.QuestionnaireForm;
				auditFormName1 = availableData.Audit_Form1;
				auditFormName2 = availableData.Audit_Form2;
				locationInstanceValue1 = availableData.locationInstanceValue1;
				resourceInstanceValue1 = availableData.resourceInstanceValue1;
				resourceInstanceValue2 = availableData.resourceInstanceValue2;
			}

			TestValidation.IsTrue(storeData.setCreatedData(availableData, taskData, userData, otherData), 
					"SAVED new prerequisite data", 
					"Could NOT save prerequisite data");

			roleName = "superadmin";
			userLN = "User";
			userLocation = new ArrayList<String>();
			userRole = new ArrayList<String>();
			userPassword = GenericPassword;
			userNewPassword = GenericNewPassword;
			userLocation.add(locationInstanceValue1);
			userRole.add(roleName);
			userPin = "1234";

			udp = new UsrDetailParams();
			udp.Username = userUN;
			udp.Password = userPassword;
			udp.FirstName = userFN;
			udp.LastName = userLN;
			udp.Email = "sc@pcappm.com";
			udp.Timezone = "U.S. Pacific";
			udp.Locations = userLocation;
			udp.Roles = userRole;
			udp.Pin = userPin;


			boolean deviceCreation = devicePage.addNewDevice(deviceName);
			TestValidation.IsTrue(deviceCreation, 
					"ADDED new device - " + deviceName, 
					"FAILED to add new device - " + deviceName);

			UserManagerPage ump = homePage.clickUsersMenu();
			if(ump.error) {
				TCGFailureMsg = "Could NOT navigate to 'Manage User'";
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}

			boolean userCreation = ump.createInternalUser(udp);
			TestValidation.IsTrue(userCreation, "CREATED user - "+userUN, "Could NOT create user - "+userUN);

			boolean userLogin = login.loginAfterUserCreation(userUN, userPassword, userNewPassword);
			TestValidation.IsTrue(userLogin, "LOGGED IN with new user - "+userUN, "Could NOT login with new user - "+userUN);

			boolean workgroupCreation = workgroup.createWorkGroup(workGroupName, userUN);
			TestValidation.IsTrue(workgroupCreation, "CREATED workgroup - "+workGroupName, "Could NOT create workgroup - "+workGroupName);

			fsqaBrowser = homePage.clickFSQABrowserMenu();
			if(fsqaBrowser.error) {
				TCGFailureMsg = "FAILED to navigate to the 'FSQA Browser' page";
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}

			boolean itemsFormTab = fsqaBrowser.selectResourceDropDownandNavigate(FORMRESOURCETYPES.ITEMS,FSQATAB.FORMS);
			if(!itemsFormTab) {
				TCGFailureMsg = "FAILED to navigate to Items - FSQABrowser > Forms Tab";
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}

			boolean applyfilter = fsqaBrowser.openAndApplySettingsForColumn(COLUMNHEADER.FORMNAME, COLUMNSETTING.FILTER,auditFormName2);
			if(!applyfilter) {
				TCGFailureMsg = "FAILED to apply filter on " + COLUMNHEADER.FORMNAME;
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}

			threadsleep(5000);

			taskDetails = new TASKDETAILS();
			taskDetails.Location = locationInstanceValue1;
			taskDetails.Workgroup = workGroupName;
			taskDetails.Resource = resourceInstanceValue2;

			taskDetails.TaskName = pastDueTask;
			taskDetails.TaskType = "PASTDUE";
			TestValidation.IsTrue(fsqaBrowser.assignFormTask(taskDetails), 
					"CREATED task - " + taskDetails.TaskName, 
					"FAILED to create task - " + taskDetails.TaskName);

			taskDetails.TaskName = dueTodayTask;
			taskDetails.TaskType = "DUETODAY";
			TestValidation.IsTrue(fsqaBrowser.assignFormTask(taskDetails), 
					"CREATED task - " + taskDetails.TaskName, 
					"FAILED to create task - " + taskDetails.TaskName);

			taskDetails.TaskName = dueLaterTask;
			taskDetails.TaskType = "DUELATER";
			TestValidation.IsTrue(fsqaBrowser.assignFormTask(taskDetails), 
					"CREATED task - " + taskDetails.TaskName, 
					"FAILED to create task - " + taskDetails.TaskName);

			taskDetails.TaskName = noDueDateTask;
			taskDetails.TaskType = "NODUEDATE";
			TestValidation.IsTrue(fsqaBrowser.assignFormTask(taskDetails), 
					"CREATED task - " + taskDetails.TaskName, 
					"FAILED to create task - " + taskDetails.TaskName);

			taskDetails.TaskName = saveTask;
			taskDetails.TaskType = "NODUEDATE";
			TestValidation.IsTrue(fsqaBrowser.assignFormTask(taskDetails), 
					"CREATED task - " + taskDetails.TaskName, 
					"FAILED to create task - " + taskDetails.TaskName);

			taskDetails.TaskName = submitTask;
			taskDetails.TaskType = "NODUEDATE";
			TestValidation.IsTrue(fsqaBrowser.assignFormTask(taskDetails), 
					"CREATED task - " + taskDetails.TaskName, 
					"FAILED to create task - " + taskDetails.TaskName);

			logInfo("Created required Prerequiste data successfully");

		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
