package com.project.safetychain.mobileapp.testcases;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List; 
import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.project.safetychain.api.models.support.Support.AGGREGATE_FUNC_TYPES;
import com.project.safetychain.api.models.support.Support.DPT_FIELD_TYPES;
import com.project.safetychain.api.models.support.Support.DPT_FORM_TYPES;
import com.project.safetychain.api.models.support.Support.Element_Types;
import com.project.safetychain.api.models.support.Support.FormFieldParams;
import com.project.safetychain.api.models.support.Support.FormParams;
import com.project.safetychain.api.models.support.Support.IDENTIFIER_TYPES;
import com.project.safetychain.api.models.support.Support.RESOURCE_TYPES;
import com.project.safetychain.api.models.support.TCG_FormCreation_Wrapper;
import com.project.safetychain.mobileapp.pageobjects.HomePage;
import com.project.safetychain.mobileapp.pageobjects.LoginPage;
import com.project.safetychain.mobileapp.pageobjects.REG_SubmissionsPage;
import com.project.safetychain.mobileapp.pageobjects.SavedPage;
import com.project.safetychain.mobileapp.pageobjects.UploadImage;
import com.project.safetychain.webapp.pageobjects.CommonPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserDocumentsPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserFormsPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserRecordsPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.COLUMNHEADER;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.COLUMNSETTING;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.FSQARESOURCE;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.FSQATAB;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FormDesignParams;
import com.project.safetychain.webapp.pageobjects.ManageLocationPage;
import com.project.safetychain.webapp.pageobjects.ManageResourcePage;
import com.project.safetychain.webapp.pageobjects.ResourceDesignerPage;
import com.project.safetychain.webapp.testcases.support.FormOperations;
import com.project.safetychain.webapp.testcases.support.FormOperations.FormDetails;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.utilities.ApiUtils;
import com.project.utilities.CommonMethods;
import com.project.utilities.ControlActions;
import com.project.utilities.ControlActions_MobileApp;

import io.appium.java_client.pagefactory.AndroidFindBy;

public class TCG_REG_QuestionareFormSubmissionsFlow extends TestBase {
	ControlActions_MobileApp mobControlActions;
	ApiUtils apiUtils = null;
	FormDesignerPage formDesignerPage;
	ControlActions controlActions;
	FSQABrowserPage fbp;
	FSQABrowserFormsPage fbForms;
	FSQABrowserDocumentsPage fbDocuments;
	CommonPage mainPage;
	FormFieldParams ffp;
	FormDesignParams fp;
	FormDetails formDetails;
	com.project.safetychain.webapp.pageobjects.HomePage hp;
	com.project.safetychain.webapp.pageobjects.LoginPage lp;
	ManageResourcePage manageResource;
	ManageLocationPage locations;
	ResourceDesignerPage resourceDesigner;
	SavedPage SavedPageObj;
	FormOperations fop;

	public static String locationCategoryValue;
	public static String locationInstanceValue1;
	public static String locationInstanceValue2;
	public static String resourceCategoryValue;
	public static String resourceInstanceValue1;
	public static String resourceInstanceValue2;
	public static String chkFreeTxt, ParaTxt, chkSelectOne, SelectMultiple, Numeric, chkDate, chkTime, chkDateTime;

	public static String formName = "API_Quest_15.10_17.19.19";
	public static String resourceName = "RInst1_15.10_13.47.04";
	public static String LocationName = "LInst1_15.10_13.47.04";
	String formtype = "Check";
	String modifiedby;
	public static String FormName;

	public static final String fresult = "com.safetychain.SCM.M2:id/resultView_num";
	@AndroidFindBy(id = fresult)
	public WebElement NTxt;

	@BeforeClass(alwaysRun = true)
	public void groupInit() {
		try {
			FormName = CommonMethods.dynamicText("API_QuestForm");

			// Field Short Names
			chkFreeTxt = "FreeText";
			ParaTxt = "Paragraph";
			chkSelectOne = "SelectOne";
			Numeric = "Numeric";
			chkDate = "Date";
			chkTime = "Time";
			chkDateTime = "Date&Time";
			SelectMultiple = "MultiSelect";

			TCG_FormCreation_Wrapper formCreationWrapper = new TCG_FormCreation_Wrapper();

			apiUtils = new ApiUtils();

			locationCategoryValue = CommonMethods.dynamicText("LCat");
			locationInstanceValue1 = CommonMethods.dynamicText("LInst1");
			locationInstanceValue2 = CommonMethods.dynamicText("LInst2");
			resourceCategoryValue = CommonMethods.dynamicText("RCat");
			resourceInstanceValue1 = CommonMethods.dynamicText("RInst1");
			resourceInstanceValue2 = CommonMethods.dynamicText("RInst2");

			// API Implementation

			FormFieldParams numericField = new FormFieldParams();
			numericField.DPTFieldType = DPT_FIELD_TYPES.NUMERIC;
			numericField.Name = Numeric;
			numericField.ShowHint = "true";
			numericField.Hint = "Numeric";
			numericField.AllowAttachments = "true";
			numericField.RepeatField = "true";
			numericField.ShowMinMax = "true";
			numericField.ShowTarget = "true";

			FormFieldParams dateField1 = new FormFieldParams();
			dateField1.DPTFieldType = DPT_FIELD_TYPES.DATE;
			dateField1.Name = "Date1";
			dateField1.RepeatField = "true";

			FormFieldParams dateField2 = new FormFieldParams();
			dateField2.DPTFieldType = DPT_FIELD_TYPES.DATE;
			dateField2.Name = "Date2";
			dateField2.RepeatField = "true";

			FormFieldParams Identifier = new FormFieldParams();
			Identifier.DPTFieldType = DPT_FIELD_TYPES.IDENTIFIER;
			Identifier.Name = "Identifier1";
			Identifier.identifierId = IDENTIFIER_TYPES.SELECT_ONE;
			Identifier.IdentifierOption = Arrays.asList("1", "2", "3", "4");
			Identifier.RepeatField = "true";

			FormFieldParams selectOneField = new FormFieldParams();
			selectOneField.DPTFieldType = DPT_FIELD_TYPES.SELECT_ONE;
			selectOneField.Name = chkSelectOne;
			selectOneField.SelectOptions = Arrays.asList("a", "b", "c", "d");
			selectOneField.RepeatField = "true";

			FormFieldParams Paragraph = new FormFieldParams();
			Paragraph.DPTFieldType = DPT_FIELD_TYPES.PARAGRAPH_TEXT;
			Paragraph.Name = "ParaGraph1";
			Paragraph.RepeatField = "true";

			FormFieldParams SingleLineText = new FormFieldParams();
			SingleLineText.DPTFieldType = DPT_FIELD_TYPES.SINGLE_LINE_TEXT;
			SingleLineText.Name = "SingleLineText1";
			SingleLineText.RepeatField = "true";

			// Field Group 1-->ID1

			Element_Types fieldGroup = new Element_Types();
			fieldGroup.ElementType = DPT_FIELD_TYPES.FIELD_GROUP;
			fieldGroup.Name = "FieldGroup";
			fieldGroup.DependencyRule = "if(SelectOne=a;Show)else(Hide)";

			FormFieldParams Identifier2 = new FormFieldParams();
			Identifier2.DPTFieldType = DPT_FIELD_TYPES.IDENTIFIER;
			Identifier2.Name = "IDNFieldGroup";
			Identifier2.identifierId = IDENTIFIER_TYPES.FREE_TEXT;
			Identifier2.InputMask = "0000";
			Identifier2.RepeatField = "true";
			fieldGroup.formFieldParams = Arrays.asList(Identifier2);

			// Field Group 2-->ID2

			Element_Types fieldGroup2 = new Element_Types();
			fieldGroup2.ElementType = DPT_FIELD_TYPES.FIELD_GROUP;
			fieldGroup2.Name = "FieldGroup2";
			fieldGroup2.DependencyRule = "if(SelectOne=b;Show)else(Hide)";

			FormFieldParams Identifier3 = new FormFieldParams();
			Identifier3.DPTFieldType = DPT_FIELD_TYPES.IDENTIFIER;
			Identifier3.Name = "IDNFieldGroup2";
			Identifier3.identifierId = IDENTIFIER_TYPES.SELECT_ONE;
			Identifier3.IdentifierOption = Arrays.asList("Test 1", "Test 2");
			// Identifier2.RepeatField = "true";

			fieldGroup2.formFieldParams = Arrays.asList(Identifier3);

			Element_Types Section1 = new Element_Types();
			Section1.ElementType = DPT_FIELD_TYPES.SECTION;
			Section1.Name = "Section1";

			Element_Types fieldGroup3 = new Element_Types();
			fieldGroup3.ElementType = DPT_FIELD_TYPES.FIELD_GROUP;
			fieldGroup3.Name = "FieldGroup1";
			fieldGroup3.DependencyRule = "if(Identifier1=1;Show)elseif(Identifier1=3;Show)else(Hide)";

			FormFieldParams Numeric1 = new FormFieldParams();
			Numeric1.DPTFieldType = DPT_FIELD_TYPES.NUMERIC;
			Numeric1.Name = "Numeric1";

			fieldGroup3.formFieldParams = Arrays.asList(Numeric1);

			Element_Types fieldGroup4 = new Element_Types();
			fieldGroup4.ElementType = DPT_FIELD_TYPES.FIELD_GROUP;
			fieldGroup4.Name = "FieldGroup3";
			fieldGroup4.DependencyRule = "if(Identifier1=2;Show)elseif(Identifier1=4;Show)else(Hide)";

			FormFieldParams Numeric2 = new FormFieldParams();
			Numeric2.DPTFieldType = DPT_FIELD_TYPES.NUMERIC;
			Numeric2.Name = "Numeric2";

			FormFieldParams selectMultipleField = new FormFieldParams();
			selectMultipleField.DPTFieldType = DPT_FIELD_TYPES.SELECT_MULTIPLE;
			selectMultipleField.Name = SelectMultiple;
			selectMultipleField.SelectOptions = Arrays.asList("1", "a", "2", "b");

			fieldGroup4.formFieldParams = Arrays.asList(Numeric2);
			Section1.FieldGroupParams = Arrays.asList(fieldGroup3, fieldGroup4);
			Section1.formFieldParams = Arrays.asList(selectMultipleField);

			Element_Types Section2 = new Element_Types();
			Section2.ElementType = DPT_FIELD_TYPES.SECTION;
			Section2.Name = "Section2";

			FormFieldParams TimeField1 = new FormFieldParams();
			TimeField1.DPTFieldType = DPT_FIELD_TYPES.TIME;
			TimeField1.Name = "Time1";

			FormFieldParams TimeField2 = new FormFieldParams();
			TimeField2.DPTFieldType = DPT_FIELD_TYPES.TIME;
			TimeField2.Name = "Time2";

			Element_Types fieldGroup5 = new Element_Types();
			fieldGroup5.ElementType = DPT_FIELD_TYPES.FIELD_GROUP;
			fieldGroup5.Name = "FieldGroup4";

			FormFieldParams DateTimeField1 = new FormFieldParams();
			DateTimeField1.DPTFieldType = DPT_FIELD_TYPES.DATE_TIME;
			DateTimeField1.Name = "DateTime1";

			FormFieldParams DateTimeField2 = new FormFieldParams();
			DateTimeField2.DPTFieldType = DPT_FIELD_TYPES.DATE_TIME;
			DateTimeField2.Name = "DateTime2";

			fieldGroup5.formFieldParams = Arrays.asList(DateTimeField1, DateTimeField2);

			FormFieldParams Numeric3 = new FormFieldParams();
			Numeric3.DPTFieldType = DPT_FIELD_TYPES.NUMERIC;
			Numeric3.Name = "Numeric3";

			FormFieldParams Numeric4 = new FormFieldParams();
			Numeric4.DPTFieldType = DPT_FIELD_TYPES.NUMERIC;
			Numeric4.Name = "Numeric4";
			Numeric4.Repeat = "3";

			FormFieldParams Numeric5 = new FormFieldParams();
			Numeric5.DPTFieldType = DPT_FIELD_TYPES.NUMERIC;
			Numeric5.Name = "Numeric5";
			Section2.FieldGroupParams = Arrays.asList(fieldGroup5);
			Section2.formFieldParams = Arrays.asList(TimeField1, TimeField2, Numeric3, Numeric4, Numeric5);

			FormFieldParams AGG1 = new FormFieldParams();
			AGG1.DPTFieldType = DPT_FIELD_TYPES.AGGREGATE;
			AGG1.Name = "AGG1-SUM";
			AGG1.SelectedFunction = AGGREGATE_FUNC_TYPES.SUM;
			AGG1.SelectedSource = "Numeric4";

			FormFieldParams AGG2 = new FormFieldParams();
			AGG2.DPTFieldType = DPT_FIELD_TYPES.AGGREGATE;
			AGG2.Name = "AGG1-SumRange";
			AGG2.SelectedFunction = AGGREGATE_FUNC_TYPES.SUM_RANGE;
			AGG2.AGG_MIN = "1";
			AGG2.AGG_MAX = "1000";
			AGG2.SelectedSourceCollection = Arrays.asList("Numeric5", "Numeric3");

			String formId = apiUtils.getUUID();
			// Form details
			logInfo("FormId = " + formId);
			FormParams fp = new FormParams();
			logInfo("Form Name = " + formId);
			fp.FormId = formId;
			fp.FormName = FormName;
			logInfo("Form Name 2 = " + FormName);
			fp.type = DPT_FORM_TYPES.QUESTIONNAIRE;
			fp.ResourceType = RESOURCE_TYPES.CUSTOMERS;

			fp.ResourceCategoryNm = resourceCategoryValue;
			fp.ResourceInstanceNm = resourceInstanceValue1;

			fp.formElements = Arrays.asList(numericField, dateField1, dateField2, Identifier, selectOneField, AGG1,
					AGG2, Paragraph, SingleLineText);

			fp.SectionElements = Arrays.asList(fieldGroup, fieldGroup2, Section1, Section2);

			logInfo("fp.formElements = " + fp.formElements);

			HashMap<String, List<String>> resourceCatMap = new HashMap<String, List<String>>();
			resourceCatMap.put(resourceCategoryValue, Arrays.asList(resourceInstanceValue1, resourceInstanceValue2));
			fp.CustomerResources = resourceCatMap;

			HashMap<String, List<String>> LocationMap = new HashMap<String, List<String>>();
			LocationMap.put(locationCategoryValue, Arrays.asList(locationInstanceValue1, locationInstanceValue2));
			List<String> userList = Arrays.asList(mobileAppUsername02, mobileAppUsername);

			formCreationWrapper.Resource_Location_CreationWrapper(resourceCatMap, LocationMap, true, userList,
					fp.ResourceType);
			formCreationWrapper.API_Wrapper_Forms(fp);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test(priority = 1,description = "37361 || Verify Form (check , audit & Questionnaire )submissions on Online without attachment")
	public void TestCase_37361() throws Exception {
		appiumDriver = launchMobileApp();
		mobControlActions = new ControlActions_MobileApp(appiumDriver);
		REG_SubmissionsPage SavedPageObj = new REG_SubmissionsPage(appiumDriver);
		LoginPage loginPage = new LoginPage(appiumDriver);
		HomePage homePage = new HomePage(appiumDriver);
		if (mobileApp_Tenant.equals("test1.stage")) {
			boolean SSOLogin = loginPage.SSO_Login(mobileAppUsername, mobileAppPassword);
			TestValidation.IsTrue(SSOLogin, "logged into the application with User " + mobileAppUsername,
					"Failed to log in");

		} else {
			boolean login = loginPage.Login(mobileAppUsername, mobileAppPassword);
			TestValidation.IsTrue(login, "logged into the application with User " + mobileAppUsername,
					"Failed to log in");

		}
		Boolean searchFrms = homePage.SearchForm(FormName);

		TestValidation.IsTrue(searchFrms, "Form Searched successfully", "Failed to search Form");

		Boolean clickFrms = homePage.ClickForm(FormName);
		TestValidation.IsTrue(clickFrms, "Form Clicked successfully", "Failed to click Form");
		Boolean searchLocation = SavedPageObj.SearchResource(locationInstanceValue1);
		TestValidation.IsTrue(searchLocation, "Location Searched successfully", "Failed to search Location");
		Boolean searchResource = SavedPageObj.SearchResource(resourceInstanceValue1);
		TestValidation.IsTrue(searchResource, "Resource Searched successfully", "Failed to Resource Form");

		Boolean fillFormDetails = SavedPageObj.QuestionareForm_fillFormDetailsWithoutAttachment();
		TestValidation.IsTrue(fillFormDetails, "Form Details filled successfully", "Failed to fill form details");

		Boolean submitForm = SavedPageObj.submitForm();
		TestValidation.IsTrue(submitForm, "Form Submitted Successfully", "Failed to Submit Form");
		Boolean clickSubmissions = homePage.ClickSubMenu(homePage.submissionsSubMenu);
		TestValidation.IsTrue(clickSubmissions, "Clicked on submissions subMenu Successfully",
				"Failed to click Submissions subMenu");
		ControlActions_MobileApp.click(SavedPageObj.sortBtn);
		Boolean searchForm = homePage.SearchForm(FormName);
		TestValidation.IsTrue(searchForm, "form Searched Successfully",
				"Failed to search form from submissions screen");
		Boolean chkStatus = SavedPageObj.checkFormStatusFromSubmissionsSubMenu(FormName, "RECEIVED");
		TestValidation.IsTrue(chkStatus, "Verified form Status Successfully", "Failed to verify Form status");

		SavedPageObj.clickFormFromSubmissionsScreen(FormName);
		LinkedHashMap<String, String> FieldTypeValues = new LinkedHashMap<String, String>();
		FieldTypeValues.put("Numeric", "10");
		FieldTypeValues.put("Identifier1", "2");
		FieldTypeValues.put("SelectOne", "C");
		FieldTypeValues.put("AGG1-SUM", "30");
		FieldTypeValues.put("AGG1-SumRange", "90");
		FieldTypeValues.put("ParaGraph1",
				"This is Paragraph Text and has to be checked with Maxixmun Field Values!@#$%^&*()");
		FieldTypeValues.put("SingleLineText1", "Single Line Text Value Has Entered Successsfully");
		FieldTypeValues.put("Numeric2", "100");
		FieldTypeValues.put("Numeric3", "10");
		FieldTypeValues.put("Numeric4", "10");
		FieldTypeValues.put("Numeric5", "80");

		boolean verifyUpdatedValue = SavedPageObj.verifyFieldValues(FieldTypeValues);

		logInfo("Field Values Map" + FieldTypeValues);

		TestValidation.IsTrue(verifyUpdatedValue, "VERIFIED updated value for field type Numeric as " + FieldTypeValues,
				"Failed to verify updated value for field type Numeric as " + FieldTypeValues);

		driver = launchbrowser();
		fbp = new FSQABrowserPage(driver);
		controlActions = new ControlActions(driver);
		lp = new com.project.safetychain.webapp.pageobjects.LoginPage(driver);
		controlActions.getUrl(applicationUrl);
		hp = lp.performLogin(adminUsername, adminPassword);
		if (hp.error) {
			TCGFailureMsg = "Could NOT login to application";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		hp.clickFSQABrowserMenu();

		boolean navigate = fbp.selectResourceDropDownandNavigate("CUSTOMERS", "Documents");
		TestValidation.IsTrue(navigate, "Navigated to FSQABrowser>RecordsTab",
				"Failed to navigate to FSQABrowser>Records Tab");

		boolean openRecord = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.DOCUMENTNAME, FormName);
		TestValidation.IsTrue(openRecord, "OPENED record for form - " + FormName,
				"Failed to open record for form - " + FormName);

		FSQABrowserRecordsPage frp = new FSQABrowserRecordsPage(driver);

		HashMap<String, List<String>> WebFieldTypeValues = new HashMap<String, List<String>>();
		WebFieldTypeValues.put("Numeric", Arrays.asList("10"));
		WebFieldTypeValues.put("Identifier1", Arrays.asList("2"));
		WebFieldTypeValues.put("SelectOne", Arrays.asList("c"));
		WebFieldTypeValues.put("Numeric3", Arrays.asList("10"));
		WebFieldTypeValues.put("Numeric4", Arrays.asList("10"));
		WebFieldTypeValues.put("Numeric5", Arrays.asList("80"));
		WebFieldTypeValues.put("AGG1-SUM", Arrays.asList("30"));
		WebFieldTypeValues.put("AGG1-SumRange", Arrays.asList("90"));
		WebFieldTypeValues.put("Numeric2", Arrays.asList("100"));
		boolean verifyWebUpdatedValue = frp.verifyFieldValues(WebFieldTypeValues);
		TestValidation.IsTrue(verifyWebUpdatedValue, "VERIFIED updated value on Web as " + WebFieldTypeValues,
				"Failed to verify updated value on Web as " + WebFieldTypeValues);

	}

	@Test(description = " Verify Form (check , audit & Questionnaire )submissions on Offline without attachment")
	public void TestCase_37362() throws Exception {
		appiumDriver = launchMobileApp();
		mobControlActions = new ControlActions_MobileApp(appiumDriver);
		REG_SubmissionsPage SavedPageObj = new REG_SubmissionsPage(appiumDriver);
		LoginPage loginPage = new LoginPage(appiumDriver);
		HomePage homePage = new HomePage(appiumDriver);
		if (mobileApp_Tenant.equals("test1.stage")) {
			boolean SSOLogin = loginPage.SSO_Login(mobileAppUsername, mobileAppPassword);
			TestValidation.IsTrue(SSOLogin, "logged into the application with User " + mobileAppUsername,
					"Failed to log in");

		} else {
			boolean login = loginPage.Login(mobileAppUsername, mobileAppPassword);
			TestValidation.IsTrue(login, "logged into the application with User " + mobileAppUsername,
					"Failed to log in");

		}

		boolean isOffline1 = SavedPageObj.clickofflineToggleButton();
		TestValidation.IsTrue(isOffline1, "Verified offline Toggle button click Successfully",
				"Failed to click offline toggle button from Settings subMenu");

		Boolean clickForms = homePage.ClickMenu(homePage.formsSubMenu);
		TestValidation.IsTrue(clickForms, "Clicked on Forms subMenu Successfully", "Failed to click Forms subMenu");
		Boolean searchFrms = homePage.SearchForm(FormName);

		TestValidation.IsTrue(searchFrms, "Form Searched successfully", "Failed to search Form");

		Boolean clickFrms = homePage.ClickForm(FormName);
		TestValidation.IsTrue(clickFrms, "Form Clicked successfully", "Failed to click Form");
		Boolean searchLocation = SavedPageObj.SearchResource(locationInstanceValue1);
		TestValidation.IsTrue(searchLocation, "Location Searched successfully", "Failed to search Location");
		Boolean searchResource = SavedPageObj.SearchResource(resourceInstanceValue1);
		TestValidation.IsTrue(searchResource, "Resource Searched successfully", "Failed to Resource Form");

		Boolean fillFormDetails = SavedPageObj.QuestionareForm_fillFormDetailsWithoutAttachment();
		TestValidation.IsTrue(fillFormDetails, "Form Details filled successfully", "Failed to fill form details");

		Boolean submitForm = SavedPageObj.submitForm();
		TestValidation.IsTrue(submitForm, "Form Submitted Successfully", "Failed to Submit Form");
		Boolean clickSubmissions = homePage.ClickSubMenu(homePage.submissionsSubMenu);
		TestValidation.IsTrue(clickSubmissions, "Clicked on submissions subMenu Successfully",
				"Failed to click Submissions subMenu");
		ControlActions_MobileApp.click(SavedPageObj.sortBtn);
		Boolean searchForm = homePage.SearchForm(FormName);
		TestValidation.IsTrue(searchForm, "form Searched Successfully",
				"Failed to search form from submissions screen");
		Boolean chkStatus = SavedPageObj.checkFormStatusFromSubmissionsSubMenu(FormName, "PENDING");
		TestValidation.IsTrue(chkStatus, "Verified form Status as Pending Successfully",
				"Failed to verify Form status");
		appiumDriver.hideKeyboard();

		boolean isOffline2 = SavedPageObj.clickofflineToggleButton();
		TestValidation.IsTrue(isOffline2, "Verified offline Toggle button click Successfully",
				"Failed to click offline toggle button from Settings subMenu");

		boolean verifyStatus = SavedPageObj.verifyStatusAfterclickingResubmitAllButton(FormName, "RECEIVED");
		TestValidation.IsTrue(verifyStatus, "Verified form Status as Received Successfully",
				"Failed to verify Form status");

		SavedPageObj.clickFormFromSubmissionsScreen(FormName);
		LinkedHashMap<String, String> FieldTypeValues = new LinkedHashMap<String, String>();
		FieldTypeValues.put("Numeric", "10");
		FieldTypeValues.put("Identifier1", "2");
		FieldTypeValues.put("SelectOne", "C");
		FieldTypeValues.put("AGG1-SUM", "30");
		FieldTypeValues.put("AGG1-SumRange", "90");
		FieldTypeValues.put("ParaGraph1",
				"This is Paragraph Text and has to be checked with Maxixmun Field Values!@#$%^&*()");
		FieldTypeValues.put("SingleLineText1", "Single Line Text Value Has Entered Successsfully");
		FieldTypeValues.put("Numeric2", "100");
		FieldTypeValues.put("MultiSelect", "A");
		FieldTypeValues.put("Numeric3", "10");
		FieldTypeValues.put("Numeric4", "10");
		FieldTypeValues.put("Numeric5", "80");

		boolean verifyUpdatedValue = SavedPageObj.verifyFieldValues(FieldTypeValues);

		logInfo("Field Values Map" + FieldTypeValues);

		TestValidation.IsTrue(verifyUpdatedValue, "VERIFIED updated value for field type Numeric as " + FieldTypeValues,
				"Failed to verify updated value for field type Numeric as " + FieldTypeValues);

		driver = launchbrowser();
		fbp = new FSQABrowserPage(driver);
		controlActions = new ControlActions(driver);
		lp = new com.project.safetychain.webapp.pageobjects.LoginPage(driver);
		controlActions.getUrl(applicationUrl);
		hp = lp.performLogin(adminUsername, adminPassword);
		if (hp.error) {
			TCGFailureMsg = "Could NOT login to application";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		hp.clickFSQABrowserMenu();

		boolean navigate = fbp.selectResourceDropDownandNavigate("CUSTOMERS", "Documents");
		TestValidation.IsTrue(navigate, "Navigated to FSQABrowser>RecordsTab",
				"Failed to navigate to FSQABrowser>Records Tab");

		boolean openRecord = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.DOCUMENTNAME, FormName);
		TestValidation.IsTrue(openRecord, "OPENED record for form - " + FormName,
				"Failed to open record for form - " + FormName);

		FSQABrowserRecordsPage frp = new FSQABrowserRecordsPage(driver);

		HashMap<String, List<String>> WebFieldTypeValues = new HashMap<String, List<String>>();
		WebFieldTypeValues.put("Numeric", Arrays.asList("10"));
		WebFieldTypeValues.put("Identifier1", Arrays.asList("2"));
		WebFieldTypeValues.put("SelectOne", Arrays.asList("c"));
		WebFieldTypeValues.put("Numeric3", Arrays.asList("10"));
		WebFieldTypeValues.put("Numeric4", Arrays.asList("10"));
		WebFieldTypeValues.put("Numeric5", Arrays.asList("80"));
		WebFieldTypeValues.put("AGG1-SUM", Arrays.asList("30"));
		WebFieldTypeValues.put("AGG1-SumRange", Arrays.asList("90"));
		WebFieldTypeValues.put("Numeric2", Arrays.asList("100"));
		WebFieldTypeValues.put("MultiSelect", Arrays.asList("a"));

		boolean verifyWebUpdatedValue = frp.verifyFieldValues(WebFieldTypeValues);
		TestValidation.IsTrue(verifyWebUpdatedValue, "VERIFIED updated value on Web as " + WebFieldTypeValues,
				"Failed to verify updated value on Web as " + WebFieldTypeValues);

	}

	@Test(description = "Verify user is able to submit Questionnaire form with all field types and attachments in Offline mode")
	public void TestCase_37368() throws Exception {
		appiumDriver = launchMobileApp();
		mobControlActions = new ControlActions_MobileApp(appiumDriver);
		REG_SubmissionsPage SavedPageObj = new REG_SubmissionsPage(appiumDriver);
		LoginPage loginPage = new LoginPage(appiumDriver);
		HomePage homePage = new HomePage(appiumDriver);
		if (mobileApp_Tenant.equals("test1.stage")) {
			boolean SSOLogin = loginPage.SSO_Login(mobileAppUsername, mobileAppPassword);
			TestValidation.IsTrue(SSOLogin, "logged into the application with User " + mobileAppUsername,
					"Failed to log in");

		} else {
			boolean login = loginPage.Login(mobileAppUsername, mobileAppPassword);
			TestValidation.IsTrue(login, "logged into the application with User " + mobileAppUsername,
					"Failed to log in");

		}

		boolean isOffline1 = SavedPageObj.clickofflineToggleButton();
		TestValidation.IsTrue(isOffline1, "Verified offline Toggle button click Successfully",
				"Failed to click offline toggle button from Settings subMenu");

		Boolean clickForms = homePage.ClickMenu(homePage.formsSubMenu);
		TestValidation.IsTrue(clickForms, "Clicked on Forms subMenu Successfully", "Failed to click Forms subMenu");
		Boolean searchFrms = homePage.SearchForm(FormName);

		TestValidation.IsTrue(searchFrms, "Form Searched successfully", "Failed to search Form");

		Boolean clickFrms = homePage.ClickForm(FormName);
		TestValidation.IsTrue(clickFrms, "Form Clicked successfully", "Failed to click Form");
		Boolean searchLocation = SavedPageObj.SearchResource(locationInstanceValue1);
		TestValidation.IsTrue(searchLocation, "Location Searched successfully", "Failed to search Location");
		Boolean searchResource = SavedPageObj.SearchResource(resourceInstanceValue1);
		TestValidation.IsTrue(searchResource, "Resource Searched successfully", "Failed to Resource Form");

		Boolean fillFormDetails = SavedPageObj.QuestionareForm_fillFormDetailsWithAttachment();
		TestValidation.IsTrue(fillFormDetails, "Form Details filled successfully", "Failed to fill form details");

		Boolean submitForm = SavedPageObj.submitForm();
		TestValidation.IsTrue(submitForm, "Form Submitted Successfully", "Failed to Submit Form");
		Boolean clickSubmissions = homePage.ClickSubMenu(homePage.submissionsSubMenu);
		TestValidation.IsTrue(clickSubmissions, "Clicked on submissions subMenu Successfully",
				"Failed to click Submissions subMenu");
		ControlActions_MobileApp.click(SavedPageObj.sortBtn);
		Boolean searchForm = homePage.SearchForm(FormName);
		TestValidation.IsTrue(searchForm, "form Searched Successfully",
				"Failed to search form from submissions screen");
		appiumDriver.hideKeyboard();
		Boolean chkStatus = SavedPageObj.checkFormStatusFromSubmissionsSubMenu(FormName, "PENDING");
		TestValidation.IsTrue(chkStatus, "Verified form Status as Pending Successfully",
				"Failed to verify Form status");
		appiumDriver.hideKeyboard();
		boolean isOffline2 = SavedPageObj.clickofflineToggleButton();
		TestValidation.IsTrue(isOffline2, "Verified offline Toggle button click Successfully",
				"Failed to click offline toggle button from Settings subMenu");

		boolean verifyStatus = SavedPageObj.verifyStatusAfterclickingResubmitAllButton(FormName, "RECEIVED");
		TestValidation.IsTrue(verifyStatus, "Verified form Status as Received Successfully",
				"Failed to verify Form status");

		SavedPageObj.clickFormFromSubmissionsScreen(FormName);
		LinkedHashMap<String, String> FieldTypeValues = new LinkedHashMap<String, String>();
		FieldTypeValues.put("Numeric", "10");
		FieldTypeValues.put("Identifier1", "2");
		FieldTypeValues.put("SelectOne", "C");
		FieldTypeValues.put("AGG1-SUM", "30");
		FieldTypeValues.put("AGG1-SumRange", "90");
		FieldTypeValues.put("ParaGraph1",
				"This is Paragraph Text and has to be checked with Maxixmun Field Values!@#$%^&*()");
		FieldTypeValues.put("SingleLineText1", "Single Line Text Value Has Entered Successsfully");
		FieldTypeValues.put("Numeric2", "100");
		FieldTypeValues.put("MultiSelect", "A");
		FieldTypeValues.put("Numeric3", "10");
		FieldTypeValues.put("Numeric4", "10");
		FieldTypeValues.put("Numeric5", "80");

		boolean verifyUpdatedValue = SavedPageObj.verifyFieldValues(FieldTypeValues);

		logInfo("Field Values Map" + FieldTypeValues);

		TestValidation.IsTrue(verifyUpdatedValue, "VERIFIED updated value for field type Numeric as " + FieldTypeValues,
				"Failed to verify updated value for field type Numeric as " + FieldTypeValues);

		driver = launchbrowser();
		fbp = new FSQABrowserPage(driver);
		controlActions = new ControlActions(driver);
		lp = new com.project.safetychain.webapp.pageobjects.LoginPage(driver);
		controlActions.getUrl(applicationUrl);
		hp = lp.performLogin(adminUsername, adminPassword);
		if (hp.error) {
			TCGFailureMsg = "Could NOT login to application";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		hp.clickFSQABrowserMenu();

		boolean navigate = fbp.selectResourceDropDownandNavigate("CUSTOMERS", "Documents");
		TestValidation.IsTrue(navigate, "Navigated to FSQABrowser>RecordsTab",
				"Failed to navigate to FSQABrowser>Records Tab");

		boolean openRecord = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.DOCUMENTNAME, FormName);
		TestValidation.IsTrue(openRecord, "OPENED record for form - " + FormName,
				"Failed to open record for form - " + FormName);

		FSQABrowserRecordsPage frp = new FSQABrowserRecordsPage(driver);

		HashMap<String, List<String>> WebFieldTypeValues = new HashMap<String, List<String>>();
		WebFieldTypeValues.put("Numeric", Arrays.asList("10"));
		WebFieldTypeValues.put("Identifier1", Arrays.asList("2"));
		WebFieldTypeValues.put("SelectOne", Arrays.asList("c"));
		WebFieldTypeValues.put("Numeric3", Arrays.asList("10"));
		WebFieldTypeValues.put("Numeric4", Arrays.asList("10"));
		WebFieldTypeValues.put("Numeric5", Arrays.asList("80"));
		WebFieldTypeValues.put("AGG1-SUM", Arrays.asList("30"));
		WebFieldTypeValues.put("AGG1-SumRange", Arrays.asList("90"));
		WebFieldTypeValues.put("Numeric2", Arrays.asList("100"));
		WebFieldTypeValues.put("MultiSelect", Arrays.asList("a"));

		boolean verifyWebUpdatedValue = frp.verifyFieldValues(WebFieldTypeValues);
		TestValidation.IsTrue(verifyWebUpdatedValue, "VERIFIED updated value on Web as " + WebFieldTypeValues,
				"Failed to verify updated value on Web as " + WebFieldTypeValues);

	}

	@Test(description = "37365 || Verify user is able to submit Questionnaire form with all field types and attachments in Online mode")
	public void TestCase_37365() throws Exception {
		appiumDriver = launchMobileApp();
		mobControlActions = new ControlActions_MobileApp(appiumDriver);
		REG_SubmissionsPage SavedPageObj = new REG_SubmissionsPage(appiumDriver);
		LoginPage loginPage = new LoginPage(appiumDriver);
		HomePage homePage = new HomePage(appiumDriver);
		UploadImage oUploadImage = new UploadImage(appiumDriver);
		if (mobileApp_Tenant.equals("test1.stage")) {
			boolean SSOLogin = loginPage.SSO_Login(mobileAppUsername, mobileAppPassword);
			TestValidation.IsTrue(SSOLogin, "logged into the application with User " + mobileAppUsername,
					"Failed to log in");

		} else {
			boolean login = loginPage.Login(mobileAppUsername, mobileAppPassword);
			TestValidation.IsTrue(login, "logged into the application with User " + mobileAppUsername,
					"Failed to log in");

		}
		Boolean searchFrms = homePage.SearchForm(FormName);

		TestValidation.IsTrue(searchFrms, "Form Searched successfully", "Failed to search Form");

		Boolean clickFrms = homePage.ClickForm(FormName);
		TestValidation.IsTrue(clickFrms, "Form Clicked successfully", "Failed to click Form");
		Boolean searchLocation = SavedPageObj.SearchResource(locationInstanceValue1);
		TestValidation.IsTrue(searchLocation, "Location Searched successfully", "Failed to search Location");
		Boolean searchResource = SavedPageObj.SearchResource(resourceInstanceValue1);
		TestValidation.IsTrue(searchResource, "Resource Searched successfully", "Failed to Resource Form");

		Boolean fillFormDetails = SavedPageObj.QuestionareForm_fillFormDetailsWithAttachment();
		TestValidation.IsTrue(fillFormDetails, "Form Details filled successfully", "Failed to fill form details");

		Boolean submitForm = SavedPageObj.submitForm();
		TestValidation.IsTrue(submitForm, "Form Submitted Successfully", "Failed to Submit Form");
		Boolean clickSubmissions = homePage.ClickSubMenu(homePage.submissionsSubMenu);
		TestValidation.IsTrue(clickSubmissions, "Clicked on submissions subMenu Successfully",
				"Failed to click Submissions subMenu");
		ControlActions_MobileApp.click(SavedPageObj.sortBtn);
		Boolean searchForm = homePage.SearchForm(FormName);
		TestValidation.IsTrue(searchForm, "form Searched Successfully",
				"Failed to search form from submissions screen");
		Boolean chkStatus = SavedPageObj.checkFormStatusFromSubmissionsSubMenu(FormName, "RECEIVED");
		TestValidation.IsTrue(chkStatus, "Verified form Status Successfully", "Failed to verify Form status");

		SavedPageObj.clickFormFromSubmissionsScreen(FormName);

		Thread.sleep(2000);

		oUploadImage.verifySelectedImageThroughGallery();
		LinkedHashMap<String, String> FieldTypeValues = new LinkedHashMap<String, String>();
		FieldTypeValues.put("Numeric", "10");
		FieldTypeValues.put("Identifier1", "2");
		FieldTypeValues.put("SelectOne", "C");
		FieldTypeValues.put("AGG1-SUM", "30");
		FieldTypeValues.put("AGG1-SumRange", "90");
		FieldTypeValues.put("ParaGraph1",
				"This is Paragraph Text and has to be checked with Maxixmun Field Values!@#$%^&*()");
		FieldTypeValues.put("SingleLineText1", "Single Line Text Value Has Entered Successsfully");
		FieldTypeValues.put("Numeric2", "100");
		FieldTypeValues.put("Numeric3", "10");
		FieldTypeValues.put("Numeric4", "10");
		FieldTypeValues.put("Numeric5", "80");

		boolean verifyUpdatedValue = SavedPageObj.verifyFieldValues(FieldTypeValues);

		logInfo("Field Values Map" + FieldTypeValues);

		TestValidation.IsTrue(verifyUpdatedValue, "VERIFIED updated value for field type Numeric as " + FieldTypeValues,
				"Failed to verify updated value for field type Numeric as " + FieldTypeValues);

		driver = launchbrowser();
		fbp = new FSQABrowserPage(driver);
		controlActions = new ControlActions(driver);
		lp = new com.project.safetychain.webapp.pageobjects.LoginPage(driver);
		controlActions.getUrl(applicationUrl);
		hp = lp.performLogin(adminUsername, adminPassword);
		if (hp.error) {
			TCGFailureMsg = "Could NOT login to application";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		hp.clickFSQABrowserMenu();

		boolean navigate = fbp.selectResourceDropDownandNavigate("CUSTOMERS", "Documents");
		TestValidation.IsTrue(navigate, "Navigated to FSQABrowser>RecordsTab",
				"Failed to navigate to FSQABrowser>Records Tab");

		boolean openRecord = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.DOCUMENTNAME, FormName);
		TestValidation.IsTrue(openRecord, "OPENED record for form - " + FormName,
				"Failed to open record for form - " + FormName);

		FSQABrowserRecordsPage frp = new FSQABrowserRecordsPage(driver);

		HashMap<String, List<String>> WebFieldTypeValues = new HashMap<String, List<String>>();
		WebFieldTypeValues.put("Numeric", Arrays.asList("10"));
		WebFieldTypeValues.put("Identifier1", Arrays.asList("2"));
		WebFieldTypeValues.put("SelectOne", Arrays.asList("c"));
		WebFieldTypeValues.put("Numeric3", Arrays.asList("10"));
		WebFieldTypeValues.put("Numeric4", Arrays.asList("10"));
		WebFieldTypeValues.put("Numeric5", Arrays.asList("80"));
		WebFieldTypeValues.put("AGG1-SUM", Arrays.asList("30"));
		WebFieldTypeValues.put("AGG1-SumRange", Arrays.asList("90"));
		WebFieldTypeValues.put("Numeric2", Arrays.asList("100"));

		boolean verifyWebUpdatedValue = frp.verifyFieldValues(WebFieldTypeValues);
		TestValidation.IsTrue(verifyWebUpdatedValue, "VERIFIED updated value on Web as " + WebFieldTypeValues,
				"Failed to verify updated value on Web as " + WebFieldTypeValues);

	}

	@Test(description = "37397 || Verify User is able to submit the form in low network area")
	public void TestCase_37397() throws Exception {
		appiumDriver = launchMobileApp();
		mobControlActions = new ControlActions_MobileApp(appiumDriver);
		ControlActions_MobileApp.changeNetworkPreferenceUsingADBCommands("11");
		REG_SubmissionsPage SavedPageObj = new REG_SubmissionsPage(appiumDriver);
		LoginPage loginPage = new LoginPage(appiumDriver);
		HomePage homePage = new HomePage(appiumDriver);
		UploadImage oUploadImage = new UploadImage(appiumDriver);
		if (mobileApp_Tenant.equals("test1.stage")) {
			boolean SSOLogin = loginPage.SSO_Login(mobileAppUsername, mobileAppPassword);
			TestValidation.IsTrue(SSOLogin, "logged into the application with User " + mobileAppUsername,
					"Failed to log in");

		} else {
			boolean login = loginPage.Login(mobileAppUsername, mobileAppPassword);
			TestValidation.IsTrue(login, "logged into the application with User " + mobileAppUsername,
					"Failed to log in");

		}
		Boolean searchFrms = homePage.SearchForm(FormName);

		TestValidation.IsTrue(searchFrms, "Form Searched successfully", "Failed to search Form");

		Boolean clickFrms = homePage.ClickForm(FormName);
		TestValidation.IsTrue(clickFrms, "Form Clicked successfully", "Failed to click Form");
		Boolean searchLocation = SavedPageObj.SearchResource(locationInstanceValue1);
		TestValidation.IsTrue(searchLocation, "Location Searched successfully", "Failed to search Location");
		Boolean searchResource = SavedPageObj.SearchResource(resourceInstanceValue1);
		TestValidation.IsTrue(searchResource, "Resource Searched successfully", "Failed to Resource Form");

		Boolean fillFormDetails = SavedPageObj.QuestionareForm_fillFormDetailsWithAttachment();
		TestValidation.IsTrue(fillFormDetails, "Form Details filled successfully", "Failed to fill form details");

		Boolean submitForm = SavedPageObj.submitForm();
		TestValidation.IsTrue(submitForm, "Form Submitted Successfully", "Failed to Submit Form");
		Boolean clickSubmissions = homePage.ClickSubMenu(homePage.submissionsSubMenu);
		TestValidation.IsTrue(clickSubmissions, "Clicked on submissions subMenu Successfully",
				"Failed to click Submissions subMenu");
		ControlActions_MobileApp.click(SavedPageObj.sortBtn);
		Boolean searchForm = homePage.SearchForm(FormName);
		TestValidation.IsTrue(searchForm, "form Searched Successfully",
				"Failed to search form from submissions screen");
		Boolean chkStatus = SavedPageObj.checkFormStatusFromSubmissionsSubMenu(FormName, "RECEIVED");
		TestValidation.IsTrue(chkStatus, "Verified form Status Successfully", "Failed to verify Form status");

		SavedPageObj.clickFormFromSubmissionsScreen(FormName);

		Thread.sleep(2000);

		oUploadImage.verifySelectedImageThroughGallery();
		LinkedHashMap<String, String> FieldTypeValues = new LinkedHashMap<String, String>();
		FieldTypeValues.put("Numeric", "10");
		FieldTypeValues.put("Identifier1", "2");
		FieldTypeValues.put("SelectOne", "C");
		FieldTypeValues.put("AGG1-SUM", "30");
		FieldTypeValues.put("AGG1-SumRange", "90");
		FieldTypeValues.put("ParaGraph1",
				"This is Paragraph Text and has to be checked with Maxixmun Field Values!@#$%^&*()");
		FieldTypeValues.put("SingleLineText1", "Single Line Text Value Has Entered Successsfully");
		FieldTypeValues.put("Numeric2", "100");
		FieldTypeValues.put("Numeric3", "10");
		FieldTypeValues.put("Numeric4", "10");
		FieldTypeValues.put("Numeric5", "80");

		boolean verifyUpdatedValue = SavedPageObj.verifyFieldValues(FieldTypeValues);

		logInfo("Field Values Map" + FieldTypeValues);

		TestValidation.IsTrue(verifyUpdatedValue, "VERIFIED updated value for field type Numeric as " + FieldTypeValues,
				"Failed to verify updated value for field type Numeric as " + FieldTypeValues);

		driver = launchbrowser();
		fbp = new FSQABrowserPage(driver);
		controlActions = new ControlActions(driver);
		lp = new com.project.safetychain.webapp.pageobjects.LoginPage(driver);
		controlActions.getUrl(applicationUrl);
		hp = lp.performLogin(adminUsername, adminPassword);
		if (hp.error) {
			TCGFailureMsg = "Could NOT login to application";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		hp.clickFSQABrowserMenu();

		boolean navigate = fbp.selectResourceDropDownandNavigate("CUSTOMERS", "Documents");
		TestValidation.IsTrue(navigate, "Navigated to FSQABrowser>RecordsTab",
				"Failed to navigate to FSQABrowser>Records Tab");

		boolean openRecord = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.DOCUMENTNAME, FormName);
		TestValidation.IsTrue(openRecord, "OPENED record for form - " + FormName,
				"Failed to open record for form - " + FormName);

		FSQABrowserRecordsPage frp = new FSQABrowserRecordsPage(driver);

		HashMap<String, List<String>> WebFieldTypeValues = new HashMap<String, List<String>>();
		WebFieldTypeValues.put("Numeric", Arrays.asList("10"));
		WebFieldTypeValues.put("Identifier1", Arrays.asList("2"));
		WebFieldTypeValues.put("SelectOne", Arrays.asList("c"));
		WebFieldTypeValues.put("Numeric3", Arrays.asList("10"));
		WebFieldTypeValues.put("Numeric4", Arrays.asList("10"));
		WebFieldTypeValues.put("Numeric5", Arrays.asList("80"));
		WebFieldTypeValues.put("AGG1-SUM", Arrays.asList("30"));
		WebFieldTypeValues.put("AGG1-SumRange", Arrays.asList("90"));
		WebFieldTypeValues.put("Numeric2", Arrays.asList("100"));

		boolean verifyWebUpdatedValue = frp.verifyFieldValues(WebFieldTypeValues);
		TestValidation.IsTrue(verifyWebUpdatedValue, "VERIFIED updated value on Web as " + WebFieldTypeValues,
				"Failed to verify updated value on Web as " + WebFieldTypeValues);

	}

	@SuppressWarnings("unused")
	@Test(priority = 0, description = "Verify same saved form submission on various device ex. tab & phone in some time difference, should show received status & only one record should get created")
	public void TestCase_37429_37427() throws Exception {

		String dateTimeSelected = ControlActions_MobileApp.AddDaystoToddaysDateInGivenFormat("M/d/yyyy h:mm a", 0);

		String dateSelected = ControlActions_MobileApp.AddDaystoToddaysDateInGivenFormat("M/d/yyyy", 0);

		driver = launchbrowser();
		fbp = new FSQABrowserPage(driver);
		fbForms = new FSQABrowserFormsPage(driver);
		controlActions = new ControlActions(driver);
		lp = new com.project.safetychain.webapp.pageobjects.LoginPage(driver);
		fbDocuments = new FSQABrowserDocumentsPage(driver);
		controlActions.getUrl(applicationUrl);
		hp = lp.performLogin(adminUsername, adminPassword);
		if (hp.error) {
			TCGFailureMsg = "Could NOT login to application";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		hp.clickFSQABrowserMenu();
		boolean navigate = fbp.selectResourceDropDownandNavigate("CUSTOMERS", "Forms");
		TestValidation.IsTrue(navigate, "Navigated to FSQABrowser>FormsTab",
				"Failed to navigate to FSQABrowser>Forms Tab");

		boolean applyfilter = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, FormName);
		TestValidation.IsTrue(applyfilter, "Applied Filter on" + COLUMNHEADER.FORMNAME,
				"Failed to apply filter on" + COLUMNHEADER.FORMNAME);

		LinkedHashMap<String, List<String>> fillDetails = new LinkedHashMap<String, List<String>>();
		fillDetails.put("Numeric", Arrays.asList("10"));
		fillDetails.put("Date1", Arrays.asList(dateSelected));
		fillDetails.put("Date2", Arrays.asList(dateSelected));
		fillDetails.put("Identifier1", Arrays.asList("2"));
		fillDetails.put("SelectOne", Arrays.asList("c"));
		fillDetails.put("ParaGraph1",
				Arrays.asList("This is Paragraph Text and has to be checked with Maxixmun Field Values!@#$%^&*()"));
		fillDetails.put("SingleLineText1", Arrays.asList("Single Line Text Value Has Entered Successsfully"));

		fillDetails.put("Numeric2", Arrays.asList("100"));
		fillDetails.put("MultiSelect", Arrays.asList("a"));
		fillDetails.put("Time1", Arrays.asList("12:00 AM"));
		fillDetails.put("Time2", Arrays.asList("2:30 PM"));
		fillDetails.put("Numeric3", Arrays.asList("10"));
		fillDetails.put("Numeric4", Arrays.asList("10"));
		fillDetails.put("Numeric5", Arrays.asList("80"));
		fillDetails.put("DateTime1", Arrays.asList(dateTimeSelected));
		fillDetails.put("DateTime2", Arrays.asList(dateTimeSelected));

		FormDetails fd = new FormDetails();
		fd.fieldDetails = fillDetails;
		fd.locationName = locationInstanceValue1;
		fd.resourceName = resourceInstanceValue1;
		fd.isSubmit = false;
		FormOperations fo = new FormOperations(driver);
		boolean submit = fo.submitData(fd);
		TestValidation.IsTrue(submit, "Form submitted" + formName, "Failed to submit form" + formName);

		appiumDriver = launchMobileApp();
		mobControlActions = new ControlActions_MobileApp(appiumDriver);

		REG_SubmissionsPage SavedPageObj = new REG_SubmissionsPage(appiumDriver);
		LoginPage loginPage = new LoginPage(appiumDriver);
		HomePage homePage = new HomePage(appiumDriver);
		if (mobileApp_Tenant.equals("test1.stage")) {
			boolean SSOLogin = loginPage.SSO_Login(mobileAppUsername, mobileAppPassword);
			TestValidation.IsTrue(SSOLogin, "logged into the application with User " + mobileAppUsername,
					"Failed to log in");

		} else {
			boolean login = loginPage.Login(mobileAppUsername, mobileAppPassword);
			TestValidation.IsTrue(login, "logged into the application with User " + mobileAppUsername,
					"Failed to log in");

		}

		Boolean searchFrms = homePage.SearchForm(FormName);
		TestValidation.IsTrue(searchFrms, "Form Searched successfully", "Failed to search Form");

		List<String> dateInFormat = SavedPageObj.TodaysDateinFormat("MM/dd/yyyy HH:mm z");
		boolean clickSaveMenu = homePage.ClickSubMenu(homePage.saveSubMenu);
		TestValidation.IsTrue(clickSaveMenu, "Clicked on Save subMenu Successfully", "Failed to click Save subMenu");
		ControlActions_MobileApp.click(SavedPageObj.sortBtn);
		boolean searchForm = homePage.SearchForm(FormName);
		TestValidation.IsTrue(searchForm, "Form Searched Successfully", "Failed to Search Form from Forms subMenu");

		List<String> dateInFormatMob = SavedPageObj.TodaysDateinFormat("M/d/YY HH:mm z");
		String datespliter[] = dateInFormatMob.get(0).split(" ");
		String Date = datespliter[0];
		
//		String datespliter[] = dateInFormat.get(0).split(" ");
//		String Date = datespliter[0];

		boolean formStatus = SavedPageObj.checkFormStatusFromSavedSubMenu(FormName, Date);
		TestValidation.IsTrue(formStatus, "Verified Form Status Successfully",
				"Failed to verify Form Status on Submissions");

		String expDateTime = dateInFormat.get(0).replace("Saved on ", "");
		System.out.println("expDateTime"+expDateTime);
		expDateTime = expDateTime.replace("at ", "");
		boolean open1 = fbForms.openAndSubmitForm(FormName);
		TestValidation.IsTrue(open1, "Form has Opened and Submitted Successfully", "Failed to open and submit form ");

		fbp = hp.clickFSQABrowserMenu();
		boolean navigateToRecords2 = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.CUSTOMERS, FSQATAB.DOCUMENTS);
		TestValidation.IsTrue(navigateToRecords2, "For customer category, NAVIGATED to FSQABrowser > Records tab",
				"Failed to navigate to FSQABrowser > Records tab");

		boolean applyfilter3 = fbp.openAndApplySettingsForColumn(COLUMNHEADER.DOCUMENTNAME, COLUMNSETTING.FILTER,
				FormName);
		TestValidation.IsTrue(applyfilter3, "Applied Filter on" + COLUMNHEADER.DOCUMENTNAME,
				"Failed to apply filter on" + COLUMNHEADER.DOCUMENTNAME);

		int documentsSizeBefr = fbDocuments.SelectedDocsGridLst.size();
		TestValidation.IsTrue(documentsSizeBefr == 1 || documentsSizeBefr > 1,
				"Verified Documents Count for" + COLUMNHEADER.DOCUMENTNAME,
				"Failed to verify Documents count" + COLUMNHEADER.DOCUMENTNAME);

		boolean clickSavedForm = SavedPageObj.clickFormFromSubmissionsScreen(FormName);
		TestValidation.IsTrue(clickSavedForm, "Click Saved Form", "Failed to Open Saved form");

		String dateTimeSelected1 = dateTimeSelected + " IST";
		logInfo("Expected Time= " + dateTimeSelected);
		LinkedHashMap<String, String> FieldTypeValues = new LinkedHashMap<String, String>();
		FieldTypeValues.put("Numeric", "10");
		FieldTypeValues.put("Identifier1", "2");
		FieldTypeValues.put("SelectOne", "C");
		FieldTypeValues.put("AGG1-SUM", "30");
		FieldTypeValues.put("AGG1-SumRange", "90");
		FieldTypeValues.put("ParaGraph1",
				"This is Paragraph Text and has to be checked with Maxixmun Field Values!@#$%^&*()");
		FieldTypeValues.put("SingleLineText1", "Single Line Text Value Has Entered Successsfully");
		FieldTypeValues.put("Numeric2", "100");

		FieldTypeValues.put("MultiSelect", "A");

//		FieldTypeValues.put("Time1", "12:00 AM IST");
//		FieldTypeValues.put("Time2", "2:30 PM IST");

		FieldTypeValues.put("Numeric3", "10");
		FieldTypeValues.put("Numeric4", "10");
		FieldTypeValues.put("Numeric5", "80");

		boolean verifyUpdatedValue = SavedPageObj.verifyFieldValues(FieldTypeValues);

		logInfo("Field Values Map" + FieldTypeValues);

		TestValidation.IsTrue(verifyUpdatedValue, "VERIFIED updated value for field type Numeric as " + FieldTypeValues,
				"Failed to verify updated value for field type Numeric as " + FieldTypeValues);
		Boolean submitForm = SavedPageObj.submitForm();
		TestValidation.IsTrue(submitForm, "Form Submitted Successfully", "Failed to Submit Form");
		Boolean clickSubmissions = homePage.ClickSubMenu(homePage.submissionsSubMenu);
		TestValidation.IsTrue(clickSubmissions, "Clicked on submissions subMenu Successfully",
				"Failed to click Submissions subMenu");
		ControlActions_MobileApp.click(SavedPageObj.sortBtn);
		searchForm = homePage.SearchForm(FormName);
		TestValidation.IsTrue(searchForm, "form Searched Successfully",
				"Failed to search form from submissions screen");
		Boolean chkStatus = SavedPageObj.checkFormStatusFromSubmissionsSubMenu(FormName, "RECEIVED");
		TestValidation.IsTrue(chkStatus, "Verified form Status Successfully", "Failed to verify Form status");

		hp.clickFSQABrowserMenu();

		navigate = fbp.selectResourceDropDownandNavigate("CUSTOMERS", "Documents");
		TestValidation.IsTrue(navigate, "Navigated to FSQABrowser>RecordsTab",
				"Failed to navigate to FSQABrowser>Records Tab");

		boolean applyfilter4 = fbp.openAndApplySettingsForColumn(COLUMNHEADER.DOCUMENTNAME, COLUMNSETTING.FILTER,
				FormName);
		TestValidation.IsTrue(applyfilter4, "Applied Filter on" + COLUMNHEADER.FORMNAME,
				"Failed to apply filter on" + COLUMNHEADER.FORMNAME);

		int documentsSize = fbDocuments.SelectedDocsGridLst.size();
		TestValidation.IsTrue(documentsSize == 1 || documentsSize > 1,
				"Verified Records Count for" + COLUMNHEADER.DOCUMENTNAME,
				"Failed to verify Record count" + COLUMNHEADER.DOCUMENTNAME);

	}

	@Test(description = "Resubmit button Functionality")
	public void TestCase_36822_47714() throws Exception {
		appiumDriver = launchMobileApp();
		mobControlActions = new ControlActions_MobileApp(appiumDriver);
		REG_SubmissionsPage SavedPageObj = new REG_SubmissionsPage(appiumDriver);
		mobControlActions = new ControlActions_MobileApp(appiumDriver);
		LoginPage loginPage = new LoginPage(appiumDriver);
		HomePage homePage = new HomePage(appiumDriver);
		if (mobileApp_Tenant.equals("test1.stage")) {
			boolean SSOLogin = loginPage.SSO_Login(mobileAppUsername, mobileAppPassword);
			TestValidation.IsTrue(SSOLogin, "logged into the application with User " + mobileAppUsername,
					"Failed to log in");

		} else {
			boolean login = loginPage.Login(mobileAppUsername, mobileAppPassword);
			TestValidation.IsTrue(login, "logged into the application with User " + mobileAppUsername,
					"Failed to log in");

		}

		Boolean clickSettings = homePage.ClickSubMenu(homePage.settingsSubMenu);
		TestValidation.IsTrue(clickSettings, "Clicked on Settings subMenu Successfully",
				"Failed to click Submissions subMenu");
		boolean isOffline = SavedPageObj.offlineButtonEnabled(true);
		TestValidation.IsTrue(isOffline, "offline Mode Activated", "Failed to Activate offline Mode");
		ControlActions_MobileApp.click(SavedPageObj.settingsBckBtn);

		Boolean clickForms = homePage.ClickMenu(homePage.formsSubMenu);
		TestValidation.IsTrue(clickForms, "Clicked on Forms subMenu Successfully", "Failed to click Forms subMenu");
		homePage.SearchForm(FormName);
		Boolean clickForm = homePage.ClickForm(FormName);
		TestValidation.IsTrue(clickForm, "Selected Forms to be submitted from Forms Tab Successfully",
				"Failed to Select Form from Submissions subMenu");
		boolean SelLocation = SavedPageObj.SearchResource(locationInstanceValue1);
		TestValidation.IsTrue(SelLocation, "Location Selected Successfully", "Failed to select Location");
		boolean SelResource = SavedPageObj.SearchResource(resourceInstanceValue1);
		TestValidation.IsTrue(SelResource, "Resource Selected Successfully", "Failed to select Resource");

		Boolean fillFormDetails = SavedPageObj.QuestionareForm_fillFormDetailsWithoutAttachment();
		TestValidation.IsTrue(fillFormDetails, "Form Details filled successfully", "Failed to fill form details");

		Boolean isSubmit = SavedPageObj.submitForm();
		TestValidation.IsTrue(isSubmit, "Form Submitted successfully", "Failed to Submit Form");
		Boolean clickSubmissionSubMenu = homePage.ClickSubMenu(homePage.submissionsSubMenu);
		TestValidation.IsTrue(clickSubmissionSubMenu, "Submission sub menu clicked successfully",
				"Failed to click Submissions Sub Menu");
		ControlActions_MobileApp.click(SavedPageObj.sortBtn);
		Boolean searchForm = homePage.SearchForm(FormName);
		TestValidation.IsTrue(searchForm, "Form Searched successfully", "Failed to search Form");
		appiumDriver.hideKeyboard();
		Boolean formStatus = SavedPageObj.checkFormStatusFromSubmissionsSubMenu(FormName, "PENDING");
		TestValidation.IsTrue(formStatus, "Form status verified successfully from submissions subMenu",
				"Failed to verify form Status  from submissions subMenu");
		appiumDriver.hideKeyboard();
		boolean ResubmitVisibleOffline = false;
		if (SavedPageObj.resubmitBtn.isDisplayed()) {
			ResubmitVisibleOffline = true;
		}
		TestValidation.IsTrue(ResubmitVisibleOffline, "Resubmit All and Clear All button are present at bottom",
				"Resubmit All and clear all button are not present");

		Boolean clickSettingsSubMnu = homePage.ClickSubMenu(homePage.settingsSubMenu);
		TestValidation.IsTrue(clickSettingsSubMnu, "clicked on settings subMenu successfully",
				"Failed to click settings submenu");
		ControlActions_MobileApp.click(SavedPageObj.offlineToggle);
		TestValidation.IsTrue(true, "Offline toggle button has clicked", "Failed to click offline toggle button");
		ControlActions_MobileApp.click(SavedPageObj.settingsBckBtn);
		TestValidation.IsTrue(true, "Settings back button has clicked", "Failed to click Settings back button");
		homePage.ClickMenu(homePage.submissionsSubMenu);
		TestValidation.IsTrue(true, "Submission sub menu clicked successfully", "Failed to Click Submission sub Menu");

		if (ControlActions_MobileApp.isElementDisplayed(SavedPageObj.resubmitBtn)) {
			Boolean clickResubmitAll = SavedPageObj.clickResubmitAll();
			TestValidation.IsTrue(clickResubmitAll, "Resubmit All button clicked successfully",
					"Failed to click Resubmit all button");
		}

		ControlActions_MobileApp.waitForVisibility(SavedPageObj.clearAllBtn, 60);
		boolean ResubmitVisible = false;
		try {
			if (ControlActions_MobileApp.isElementDisplayed(SavedPageObj.clearAllBtn)) {

				if (ControlActions_MobileApp.isElementDisplayed(SavedPageObj.resubmitBtn)) {
					ResubmitVisible = false;
				}
			}
		} catch (Exception e) {

			ResubmitVisible = true;

		}
		TestValidation.IsFalse(ResubmitVisible, "Resubmit All button is not present",
				"Resubmit All button is still present");
		ControlActions_MobileApp.ClearText(SavedPageObj.searchField);
		ControlActions_MobileApp.actionEnterText(SavedPageObj.searchField, FormName);

		Boolean chkFormStatus = SavedPageObj.checkFormStatusFromSubmissionsSubMenu(FormName, "RECEIVED");
		TestValidation.IsTrue(chkFormStatus, "Form Status has verified successfully", "Failed to verify Form Status");
		SavedPageObj.clickFormFromSubmissionsScreen(FormName);

		driver = launchbrowser();
		fbp = new FSQABrowserPage(driver);
		controlActions = new ControlActions(driver);
		lp = new com.project.safetychain.webapp.pageobjects.LoginPage(driver);
		controlActions.getUrl(applicationUrl);
		hp = lp.performLogin(adminUsername, adminPassword);
		if (hp.error) {
			TCGFailureMsg = "Could NOT login to application";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		// Checking on Web
		FSQABrowserPage fbp = new FSQABrowserPage(driver);
		FSQABrowserRecordsPage rp = new FSQABrowserRecordsPage(driver);
		boolean navigate = fbp.navigateToFSQATab("Documents");
		TestValidation.IsTrue(navigate, "Navigated to FSQABrowser>RecordsTab",
				"Failed to navigate to FSQABrowser>Records Tab");

		boolean applyfilter = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.DOCUMENTNAME, FormName);

		TestValidation.IsTrue(applyfilter, "Applied Filter on" + COLUMNHEADER.DOCUMENTNAME,
				"Failed to apply filter on" + COLUMNHEADER.DOCUMENTNAME);

		String dateMinus1 = ControlActions_MobileApp.AddDaystoToddaysDateInGivenFormat("MM/dd/yyyy", -1);

		@SuppressWarnings("unused")
		String CurrentDate = ControlActions_MobileApp.AddDaystoToddaysDateInGivenFormat("MM/dd/yyyy", 0);

		System.out.println("New date is :" + dateMinus1);
		HashMap<String, List<String>> WebFieldTypeValues = new HashMap<String, List<String>>();
		WebFieldTypeValues.put("Numeric", Arrays.asList("10"));
		WebFieldTypeValues.put("Identifier1", Arrays.asList("2"));
		WebFieldTypeValues.put("SelectOne", Arrays.asList("c"));
		WebFieldTypeValues.put("Numeric3", Arrays.asList("10"));
		WebFieldTypeValues.put("Numeric4", Arrays.asList("10"));
		WebFieldTypeValues.put("Numeric5", Arrays.asList("80"));
		WebFieldTypeValues.put("AGG1-SUM", Arrays.asList("30"));
		WebFieldTypeValues.put("AGG1-SumRange", Arrays.asList("90"));
		WebFieldTypeValues.put("Numeric2", Arrays.asList("100"));

		boolean verifyUpdatedValueWeb = rp.verifyFieldValues(WebFieldTypeValues);

		TestValidation.IsTrue(verifyUpdatedValueWeb, "VERIFIED updated values for all fields",
				"Failed to verify updated values for fields");

	}

	@Test(description = "Verify Resubmit on long press")
	public void TestCase_37273() throws Exception {
		// FormName="API_Task_18.02_13.31.45";
		appiumDriver = launchMobileApp();
		mobControlActions = new ControlActions_MobileApp(appiumDriver);
		REG_SubmissionsPage SavedPageObj = new REG_SubmissionsPage(appiumDriver);
		mobControlActions = new ControlActions_MobileApp(appiumDriver);
		LoginPage loginPage = new LoginPage(appiumDriver);
		HomePage homePage = new HomePage(appiumDriver);
		if (mobileApp_Tenant.equals("test1.stage")) {
			boolean SSOLogin = loginPage.SSO_Login(mobileAppUsername, mobileAppPassword);
			TestValidation.IsTrue(SSOLogin, "logged into the application with User " + mobileAppUsername,
					"Failed to log in");

		} else {
			boolean login = loginPage.Login(mobileAppUsername, mobileAppPassword);
			TestValidation.IsTrue(login, "logged into the application with User " + mobileAppUsername,
					"Failed to log in");

		}

		Boolean clickSettings = homePage.ClickSubMenu(homePage.settingsSubMenu);
		TestValidation.IsTrue(clickSettings, "Clicked on Settings subMenu Successfully",
				"Failed to click Submissions subMenu");
		boolean isOffline = SavedPageObj.offlineButtonEnabled(true);
		TestValidation.IsTrue(isOffline, "offline Mode Activated", "Failed to Activate offline Mode");
		ControlActions_MobileApp.click(SavedPageObj.settingsBckBtn);

		Boolean clickForms = homePage.ClickMenu(homePage.formsSubMenu);
		TestValidation.IsTrue(clickForms, "Clicked on Forms subMenu Successfully", "Failed to click Forms subMenu");
		homePage.SearchForm(FormName);
		Boolean clickForm = homePage.ClickForm(FormName);
		TestValidation.IsTrue(clickForm, "Selected Forms to be submitted from Forms Tab Successfully",
				"Failed to Select Form from Submissions subMenu");
		boolean SelLocation = SavedPageObj.SearchResource(locationInstanceValue1);
		TestValidation.IsTrue(SelLocation, "Location Selected Successfully", "Failed to select Location");
		boolean SelResource = SavedPageObj.SearchResource(resourceInstanceValue1);
		TestValidation.IsTrue(SelResource, "Resource Selected Successfully", "Failed to select Resource");

		Boolean fillFormDetails = SavedPageObj.QuestionareForm_fillFormDetailsWithoutAttachment();
		TestValidation.IsTrue(fillFormDetails, "Form Details filled successfully", "Failed to fill form details");

		Boolean isSubmit = SavedPageObj.submitForm();
		TestValidation.IsTrue(isSubmit, "Form Submitted successfully", "Failed to Submit Form");
		Boolean clickSubmissionSubMenu = homePage.ClickSubMenu(homePage.submissionsSubMenu);
		TestValidation.IsTrue(clickSubmissionSubMenu, "Submission sub menu clicked successfully",
				"Failed to click Submissions Sub Menu");
		ControlActions_MobileApp.click(SavedPageObj.sortBtn);
		Boolean searchForm = homePage.SearchForm(FormName);
		TestValidation.IsTrue(searchForm, "Form Searched successfully", "Failed to search Form");
		appiumDriver.hideKeyboard();
		Boolean formStatus = SavedPageObj.checkFormStatusFromSubmissionsSubMenu(FormName, "PENDING");
		TestValidation.IsTrue(formStatus, "Form status verified successfully from submissions subMenu",
				"Failed to verify form Status  from submissions subMenu");
		appiumDriver.hideKeyboard();
		boolean ResubmitVisibleOffline = false;
		if (SavedPageObj.resubmitBtn.isDisplayed()) {
			ResubmitVisibleOffline = true;
		}
		TestValidation.IsTrue(ResubmitVisibleOffline, "Resubmit All and Clear All button are present at bottom",
				"Resubmit All and clear all button are not present");

		Boolean clickSettingsSubMnu = homePage.ClickSubMenu(homePage.settingsSubMenu);
		TestValidation.IsTrue(clickSettingsSubMnu, "clicked on settings subMenu successfully",
				"Failed to click settings submenu");
		ControlActions_MobileApp.click(SavedPageObj.offlineToggle);
		TestValidation.IsTrue(true, "Offline toggle button has clicked", "Failed to click offline toggle button");
		ControlActions_MobileApp.click(SavedPageObj.settingsBckBtn);
		TestValidation.IsTrue(true, "Settings back button has clicked", "Failed to click Settings back button");
		homePage.ClickMenu(homePage.submissionsSubMenu);
		TestValidation.IsTrue(true, "Submission sub menu clicked successfully", "Failed to Click Submission sub Menu");

		boolean clickResubmitAll = SavedPageObj.clickResubmitAllByLongPress(FormName);
		TestValidation.IsTrue(clickResubmitAll, "Clicked on Resubmit All Button on Submissions subMenu Successfully",
				"Failed to click Resubmit All Button on Submissions subMenu");
		ControlActions_MobileApp.waitForVisibility(SavedPageObj.clearAllBtn, 60);
		boolean ResubmitVisible = false;
		try {
			if (ControlActions_MobileApp.isElementDisplayed(SavedPageObj.clearAllBtn)) {
				ResubmitVisible = true;
				if (ControlActions_MobileApp.isElementDisplayed(SavedPageObj.resubmitBtn)) {
					ResubmitVisible = false;
				}
			}

		} catch (Exception e) {
			ResubmitVisible = true;
		}

		TestValidation.IsTrue(ResubmitVisible, "Resubmit All button is not present",
				"Resubmit All button is still present");
		ControlActions_MobileApp.ClearText(SavedPageObj.searchField);
		ControlActions_MobileApp.actionEnterText(SavedPageObj.searchField, FormName);

		Boolean chkFormStatus = SavedPageObj.checkFormStatusFromSubmissionsSubMenu(FormName, "RECEIVED");
		TestValidation.IsTrue(chkFormStatus, "Form Status has verified successfully", "Failed to verify Form Status");
		SavedPageObj.clickFormFromSubmissionsScreen(FormName);

		driver = launchbrowser();
		fbp = new FSQABrowserPage(driver);
		controlActions = new ControlActions(driver);
		lp = new com.project.safetychain.webapp.pageobjects.LoginPage(driver);
		controlActions.getUrl(applicationUrl);
		hp = lp.performLogin(adminUsername, adminPassword);
		if (hp.error) {
			TCGFailureMsg = "Could NOT login to application";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		// Checking on Web
		FSQABrowserPage fbp = new FSQABrowserPage(driver);
		FSQABrowserRecordsPage rp = new FSQABrowserRecordsPage(driver);
		boolean navigate = fbp.navigateToFSQATab("Documents");
		TestValidation.IsTrue(navigate, "Navigated to FSQABrowser>RecordsTab",
				"Failed to navigate to FSQABrowser>Records Tab");

		boolean applyfilter = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.DOCUMENTNAME, FormName);

		TestValidation.IsTrue(applyfilter, "Applied Filter on" + COLUMNHEADER.DOCUMENTNAME,
				"Failed to apply filter on" + COLUMNHEADER.DOCUMENTNAME);

		String dateMinus1 = ControlActions_MobileApp.AddDaystoToddaysDateInGivenFormat("MM/dd/yyyy", -1);

		@SuppressWarnings("unused")
		String CurrentDate = ControlActions_MobileApp.AddDaystoToddaysDateInGivenFormat("MM/dd/yyyy", 0);

		System.out.println("New date is :" + dateMinus1);
		HashMap<String, List<String>> WebFieldTypeValues = new HashMap<String, List<String>>();

		WebFieldTypeValues.put("Numeric", Arrays.asList("10"));
		WebFieldTypeValues.put("Identifier1", Arrays.asList("2"));
		WebFieldTypeValues.put("SelectOne", Arrays.asList("c"));
		WebFieldTypeValues.put("Numeric3", Arrays.asList("10"));
		WebFieldTypeValues.put("Numeric4", Arrays.asList("10"));
		WebFieldTypeValues.put("Numeric5", Arrays.asList("80"));
		WebFieldTypeValues.put("AGG1-SUM", Arrays.asList("30"));
		WebFieldTypeValues.put("AGG1-SumRange", Arrays.asList("90"));
		WebFieldTypeValues.put("Numeric2", Arrays.asList("100"));

		boolean verifyUpdatedValueWeb = rp.verifyFieldValues(WebFieldTypeValues);

		TestValidation.IsTrue(verifyUpdatedValueWeb, "VERIFIED updated values for all fields",
				"Failed to verify updated values for fields");

	}

	@AfterMethod(alwaysRun = true)
	public void closeAppium() throws InterruptedException {

		appiumDriver.closeApp();
	}

	@AfterClass(alwaysRun = true)
	public void closeBrowser() throws InterruptedException {
		driver.quit();

	}

}
