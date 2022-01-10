package com.project.safetychain.mobileapp.testcases;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

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
import com.project.safetychain.api.models.support.Support.TaskParams;
import com.project.safetychain.api.models.support.TCG_FormCreation_Wrapper;
import com.project.safetychain.api.models.support.TCG_TaskCreation_Wrapper;
import com.project.safetychain.mobileapp.pageobjects.HomePage;
import com.project.safetychain.mobileapp.pageobjects.LoginPage;
import com.project.safetychain.mobileapp.pageobjects.REG_FormsAllValidation;
import com.project.safetychain.mobileapp.pageobjects.REG_SubmissionsPage;
import com.project.safetychain.mobileapp.pageobjects.SavedPage;
import com.project.safetychain.mobileapp.pageobjects.SavedPageLocators;
import com.project.safetychain.mobileapp.pageobjects.TaskPage;
import com.project.safetychain.mobileapp.pageobjects.UploadImage;
import com.project.safetychain.webapp.pageobjects.CommonPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserFormsPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserRecordsPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.COLUMNHEADER;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.COLUMNSETTING;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.FSQARESOURCE;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.FSQATAB;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FormDesignParams;
import com.project.safetychain.webapp.pageobjects.InboxPage;
import com.project.safetychain.webapp.pageobjects.ManageLocationPage;
import com.project.safetychain.webapp.pageobjects.ManageResourcePage;
import com.project.safetychain.webapp.pageobjects.ResourceDesignerPage;
import com.project.safetychain.webapp.pageobjects.VerificationsPage;
import com.project.safetychain.webapp.testcases.support.FormOperations;
import com.project.safetychain.webapp.testcases.support.FormOperations.FormDetails;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.utilities.ApiUtils;
import com.project.utilities.CommonMethods;
import com.project.utilities.ControlActions;
import com.project.utilities.ControlActions_MobileApp;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class TCG_REG_CheckFormSubmissionsFlow extends TestBase {
	ControlActions_MobileApp mobControlActions;
	ApiUtils apiUtils = null;
	FormDesignerPage formDesignerPage;
	ControlActions controlActions;
	FSQABrowserPage fbp;
	FSQABrowserFormsPage fbForms;
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
	REG_FormsAllValidation fav;
	public static Properties prop = new Properties();
	public static String locationCategoryValue;
	public static String locationInstanceValue1;
	public static String locationInstanceValue2;
	public static String resourceCategoryValue;
	public static String resourceInstanceValue1;
	public static String resourceInstanceValue2;
	public static String chkFreeTxt, ParaTxt, chkSelectOne, SelectMultiple, Numeric, chkDate, chkTime, chkDateTime;

	public static String formName = "Automation_CheckForm_15.10_17.19.19";
	public static String resourceName = "RInst1_15.10_13.47.04";
	public static String LocationName = "LInst1_15.10_13.47.04";
	String formtype = "Check";
	String modifiedby;
	public static String FormName;
	public static String TaskName;
	public static String WGName;

	public static final String fresult = "com.safetychain.SCM.M2:id/resultView_num";
	@AndroidFindBy(id = fresult)
	public WebElement NTxt;
	TCG_TaskCreation_Wrapper taskCreationWrapper = null;
	HashMap<String, String> locResMap = null;
	String formId = null;
	String dateTime = null;
	String dateSelected = null;

	@BeforeClass(alwaysRun = true)
	public void groupInit() {
		try {
			dateSelected = ControlActions_MobileApp.AddDaystoToddaysDateInGivenFormat("M/dd/yyyy HH:mm", 0);
			dateTime = ControlActions_MobileApp.formatdate2(dateSelected, "M/d/YYYY HH:mm");
			FormName = CommonMethods.dynamicText("Auto_CheckForm");
			TaskName = CommonMethods.dynamicText("API_Task");
			WGName = CommonMethods.dynamicText("API_WG");

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
			taskCreationWrapper = new TCG_TaskCreation_Wrapper();

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

			// numericField.RepeatField = "true";

			FormFieldParams dateField1 = new FormFieldParams();
			dateField1.DPTFieldType = DPT_FIELD_TYPES.DATE;
			dateField1.Name = "Date1";
			dateField1.DependencyRule = "if(Numeric=10;Show)else(Hide)";

			FormFieldParams dateField2 = new FormFieldParams();
			dateField2.DPTFieldType = DPT_FIELD_TYPES.DATE;
			dateField2.Name = "Date2";

			FormFieldParams Identifier = new FormFieldParams();
			Identifier.DPTFieldType = DPT_FIELD_TYPES.IDENTIFIER;
			Identifier.Name = "Identifier1";
			Identifier.identifierId = IDENTIFIER_TYPES.SELECT_ONE;
			Identifier.IdentifierOption = Arrays.asList("1", "2", "3", "4");

			FormFieldParams selectOneField = new FormFieldParams();
			selectOneField.DPTFieldType = DPT_FIELD_TYPES.SELECT_ONE;
			selectOneField.Name = chkSelectOne;
			selectOneField.SelectOptions = Arrays.asList("a", "b", "c", "d");
			selectOneField.AllowAttachments = "true";

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

			fieldGroup2.formFieldParams = Arrays.asList(Identifier3);

			// Section ---> FieldGroup 1--------->Numeric
			// ---> FieldGroup 2--------->Numeric,Select Multiple

			Element_Types Section1 = new Element_Types();
			Section1.ElementType = DPT_FIELD_TYPES.SECTION;
			Section1.Name = "Section1";

			Element_Types fieldGroup3 = new Element_Types();
			fieldGroup3.ElementType = DPT_FIELD_TYPES.FIELD_GROUP;
			fieldGroup3.Name = "FieldGroup3";
			fieldGroup3.DependencyRule = "if(Identifier1=1;Show)elseif(Identifier1=3;Show)else(Hide)";

			FormFieldParams Numeric1 = new FormFieldParams();
			Numeric1.DPTFieldType = DPT_FIELD_TYPES.NUMERIC;
			Numeric1.Name = "Numeric1";

			fieldGroup3.formFieldParams = Arrays.asList(Numeric1);

			Element_Types fieldGroup4 = new Element_Types();
			fieldGroup4.ElementType = DPT_FIELD_TYPES.FIELD_GROUP;
			fieldGroup4.Name = "FieldGroup4";
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

			// Section---> T1,T2 FG5--(DT1,DT2) ,N3,N4,n5

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
			fieldGroup5.Name = "FieldGroup5";
			// fieldGroup5.DependencyRule =
			// "if(Identifier1=2;Show)elseif(Identifier1=4;Show)else(Hide)";

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
			AGG2.AGG_MIN = "-1000";
			AGG2.AGG_MAX = "10000";
			AGG2.SelectedSourceCollection = Arrays.asList("Numeric5", "Numeric3");

			Element_Types fieldGroup6 = new Element_Types();
			fieldGroup6.ElementType = DPT_FIELD_TYPES.FIELD_GROUP;
			fieldGroup6.Name = "FieldGroup6";
			fieldGroup6.DependencyRule = "if(Numeric1>50;Show)else(Hide)";

			FormFieldParams Paragraph = new FormFieldParams();
			Paragraph.DPTFieldType = DPT_FIELD_TYPES.PARAGRAPH_TEXT;
			Paragraph.Name = "ParaGraph1";

			FormFieldParams FreeText = new FormFieldParams();
			FreeText.DPTFieldType = DPT_FIELD_TYPES.FREE_TEXT;
			FreeText.Name = "FreeText1";

			fieldGroup6.formFieldParams = Arrays.asList(Paragraph, FreeText);

			formId = apiUtils.getUUID();
			// Form details
			logInfo("FormId = " + formId);
			FormParams fp = new FormParams();
			logInfo("Form Name = " + formId);
			fp.FormId = formId;
			fp.FormName = FormName;
			logInfo("Form Name 2 = " + FormName);
			fp.type = DPT_FORM_TYPES.CHECK;
			fp.ResourceType = RESOURCE_TYPES.CUSTOMERS;

			fp.ResourceCategoryNm = resourceCategoryValue;
			fp.ResourceInstanceNm = resourceInstanceValue1;
			fp.formElements = Arrays.asList(numericField, dateField1, dateField2, Identifier, selectOneField, AGG1,
					AGG2);

			fp.SectionElements = Arrays.asList(fieldGroup, fieldGroup2, Section1, Section2, fieldGroup6);

			logInfo("fp.formElements = " + fp.formElements);

			HashMap<String, List<String>> resourceCatMap = new HashMap<String, List<String>>();
			resourceCatMap.put(resourceCategoryValue, Arrays.asList(resourceInstanceValue1, resourceInstanceValue2));
			fp.CustomerResources = resourceCatMap;

			HashMap<String, List<String>> LocationMap = new HashMap<String, List<String>>();
			LocationMap.put(locationCategoryValue, Arrays.asList(locationInstanceValue1, locationInstanceValue2));
			List<String> userList = Arrays.asList(mobileAppUsername02, mobileAppUsername);

			locResMap = formCreationWrapper.Resource_Location_CreationWrapper(resourceCatMap, LocationMap, true,
					userList, fp.ResourceType);
			formCreationWrapper.API_Wrapper_Forms(fp);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test(description = "Verify Form (check , audit & Questionnaire )submissions on Online without attachment")
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

		Boolean fillFormDetails = SavedPageObj.checkForm_fillFormDetailsWithoutAttachment();
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
		FieldTypeValues.put("Identifier1", "1");
		FieldTypeValues.put("SelectOne", "B");
		FieldTypeValues.put("AGG1-SUM", "15000");
		FieldTypeValues.put("AGG1-SumRange", "7900");
		FieldTypeValues.put("IDNFieldGroup2", "TEST 1");
		FieldTypeValues.put("Numeric1", "100");
		FieldTypeValues.put("MultiSelect", "A");
		FieldTypeValues.put("Numeric3", "-100");
		FieldTypeValues.put("Numeric4", "5000");
		FieldTypeValues.put("Numeric5", "8000");

		FieldTypeValues.put("ParaGraph1",
				"This is Paragraph Text and has to be checked with Maxixmun Field Values!@#$%^&*()");
		FieldTypeValues.put("FreeText1", "Single Line Text Value Has Entered Successsfully");

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

		boolean navigate = fbp.selectResourceDropDownandNavigate("CUSTOMERS", "Records");
		TestValidation.IsTrue(navigate, "Navigated to FSQABrowser>RecordsTab",
				"Failed to navigate to FSQABrowser>Records Tab");

		boolean openRecord = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, FormName);
		TestValidation.IsTrue(openRecord, "OPENED record for form - " + FormName,
				"Failed to open record for form - " + FormName);

		FSQABrowserRecordsPage frp = new FSQABrowserRecordsPage(driver);

		HashMap<String, List<String>> WebFieldTypeValues = new HashMap<String, List<String>>();
		WebFieldTypeValues.put("Numeric", Arrays.asList("10"));
		WebFieldTypeValues.put("Identifier1", Arrays.asList("1"));
		WebFieldTypeValues.put("IDNFieldGroup2", Arrays.asList("Test 1"));
		WebFieldTypeValues.put("SelectOne", Arrays.asList("b"));
		WebFieldTypeValues.put("Numeric3", Arrays.asList("-100"));
		WebFieldTypeValues.put("Numeric4", Arrays.asList("5000"));
		WebFieldTypeValues.put("Numeric5", Arrays.asList("8000"));
		WebFieldTypeValues.put("AGG1-SUM", Arrays.asList("15000"));
		WebFieldTypeValues.put("AGG1-SumRange", Arrays.asList("7900"));
		WebFieldTypeValues.put("Numeric1", Arrays.asList("100"));
		WebFieldTypeValues.put("MultiSelect", Arrays.asList("a"));

		WebFieldTypeValues.put("ParaGraph1",
				Arrays.asList("This is Paragraph Text and has to be checked with Maxixmun Field Values!@#$%^&*()"));
		WebFieldTypeValues.put("FreeText1", Arrays.asList("Single Line Text Value Has Entered Successsfully"));

		boolean verifyWebUpdatedValue = frp.verifyFieldValues(WebFieldTypeValues);
		TestValidation.IsTrue(verifyWebUpdatedValue, "VERIFIED updated value on Web as " + WebFieldTypeValues,
				"Failed to verify updated value on Web as " + WebFieldTypeValues);

	}

	@Test(description = "Verify Form (check , audit & Questionnaire )submissions on Offline without attachment")
	public void TestCase_37362_37392() throws Exception {
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

		Boolean fillFormDetails = SavedPageObj.checkForm_fillFormDetailsWithoutAttachment();
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

		boolean isOffline2 = SavedPageObj.clickofflineToggleButton();
		TestValidation.IsTrue(isOffline2, "Verified offline Toggle button click Successfully",
				"Failed to click offline toggle button from Settings subMenu");

		boolean verifyStatus = SavedPageObj.verifyStatusAfterclickingResubmitAllButton(FormName, "RECEIVED");
		TestValidation.IsTrue(verifyStatus, "Verified form Status as Received Successfully",
				"Failed to verify Form status");
		SavedPageObj.clickFormFromSubmissionsScreen(FormName);
		LinkedHashMap<String, String> FieldTypeValues = new LinkedHashMap<String, String>();
		FieldTypeValues.put("Numeric", "10");
		FieldTypeValues.put("Identifier1", "1");
		FieldTypeValues.put("SelectOne", "B");
		FieldTypeValues.put("AGG1-SUM", "15000");
		FieldTypeValues.put("AGG1-SumRange", "7900");
		FieldTypeValues.put("IDNFieldGroup2", "TEST 1");
		FieldTypeValues.put("Numeric1", "100");
		FieldTypeValues.put("MultiSelect", "A");
		FieldTypeValues.put("Numeric3", "-100");
		FieldTypeValues.put("Numeric4", "5000");
		FieldTypeValues.put("Numeric5", "8000");
		FieldTypeValues.put("ParaGraph1",
				"This is Paragraph Text and has to be checked with Maxixmun Field Values!@#$%^&*()");
		FieldTypeValues.put("FreeText1", "Single Line Text Value Has Entered Successsfully");
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

		boolean navigate = fbp.selectResourceDropDownandNavigate("CUSTOMERS", "Records");
		TestValidation.IsTrue(navigate, "Navigated to FSQABrowser>RecordsTab",
				"Failed to navigate to FSQABrowser>Records Tab");

		boolean openRecord = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, FormName);
		TestValidation.IsTrue(openRecord, "OPENED record for form - " + FormName,
				"Failed to open record for form - " + FormName);

		FSQABrowserRecordsPage frp = new FSQABrowserRecordsPage(driver);

		HashMap<String, List<String>> WebFieldTypeValues = new HashMap<String, List<String>>();
		WebFieldTypeValues.put("Numeric", Arrays.asList("10"));
		WebFieldTypeValues.put("Identifier1", Arrays.asList("1"));
		WebFieldTypeValues.put("IDNFieldGroup2", Arrays.asList("Test 1"));
		WebFieldTypeValues.put("SelectOne", Arrays.asList("b"));
		WebFieldTypeValues.put("Numeric1", Arrays.asList("100"));
		WebFieldTypeValues.put("Numeric3", Arrays.asList("-100"));
		WebFieldTypeValues.put("Numeric4", Arrays.asList("5000"));
		WebFieldTypeValues.put("Numeric5", Arrays.asList("8000"));
		WebFieldTypeValues.put("AGG1-SUM", Arrays.asList("15000"));
		WebFieldTypeValues.put("AGG1-SumRange", Arrays.asList("7900"));
		WebFieldTypeValues.put("MultiSelect", Arrays.asList("a"));
		WebFieldTypeValues.put("ParaGraph1",
				Arrays.asList("This is Paragraph Text and has to be checked with Maxixmun Field Values!@#$%^&*()"));
		WebFieldTypeValues.put("FreeText1", Arrays.asList("Single Line Text Value Has Entered Successsfully"));

		boolean verifyWebUpdatedValue = frp.verifyFieldValues(WebFieldTypeValues);
		TestValidation.IsTrue(verifyWebUpdatedValue, "VERIFIED updated value on Web as " + WebFieldTypeValues,
				"Failed to verify updated value on Web as " + WebFieldTypeValues);

	}

	@Test(description = "Verify user is able to submit check form with all field types and attachments in Online mode")
	public void TestCase_37363() throws Exception {
		appiumDriver = launchMobileApp();
		mobControlActions = new ControlActions_MobileApp(appiumDriver);
		REG_SubmissionsPage SavedPageObj = new REG_SubmissionsPage(appiumDriver);
		LoginPage loginPage = new LoginPage(appiumDriver);
		UploadImage oUploadImage = new UploadImage(appiumDriver);
		fav = new REG_FormsAllValidation(appiumDriver);
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

		Boolean fillFormDetails = SavedPageObj.checkForm_fillFormDetailsWithAttachment();
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
		oUploadImage.verifySelectedImageThroughGallery();

		LinkedHashMap<String, String> FieldTypeValues = new LinkedHashMap<String, String>();
		FieldTypeValues.put("Numeric", "10");
		FieldTypeValues.put("Identifier1", "1");

		FieldTypeValues.put("SelectOne", "B");

		FieldTypeValues.put("AGG1-SUM", "15000");
		FieldTypeValues.put("AGG1-SumRange", "7900");
		FieldTypeValues.put("IDNFieldGroup2", "TEST 1");
		FieldTypeValues.put("MultiSelect", "A");
		FieldTypeValues.put("Numeric1", "100");
		FieldTypeValues.put("Numeric3", "-100");
		FieldTypeValues.put("Numeric4", "5000");
		FieldTypeValues.put("Numeric5", "8000");
		FieldTypeValues.put("ParaGraph1",
				"This is Paragraph Text and has to be checked with Maxixmun Field Values!@#$%^&*()");
		FieldTypeValues.put("FreeText1", "Single Line Text Value Has Entered Successsfully");
		boolean verifyUpdatedValue = SavedPageObj.verifyFieldValues(FieldTypeValues);

		logInfo("Field Values Map" + FieldTypeValues);

		TestValidation.IsTrue(verifyUpdatedValue, "VERIFIED updated value for field type Numeric as " + FieldTypeValues,
				"Failed to verify updated value for field type Numeric as " + FieldTypeValues);

		ControlActions_MobileApp.swipe(400, 700, 400, 100);
		oUploadImage.verifySelectedImageThroughCamera("B");

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

		boolean navigate = fbp.selectResourceDropDownandNavigate("CUSTOMERS", "Records");
		TestValidation.IsTrue(navigate, "Navigated to FSQABrowser>RecordsTab",
				"Failed to navigate to FSQABrowser>Records Tab");

		boolean openRecord = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, FormName);
		TestValidation.IsTrue(openRecord, "OPENED record for form - " + FormName,
				"Failed to open record for form - " + FormName);

		FSQABrowserRecordsPage frp = new FSQABrowserRecordsPage(driver);

		HashMap<String, List<String>> WebFieldTypeValues = new HashMap<String, List<String>>();
		WebFieldTypeValues.put("Numeric", Arrays.asList("10"));
		WebFieldTypeValues.put("Identifier1", Arrays.asList("1"));
		WebFieldTypeValues.put("IDNFieldGroup2", Arrays.asList("Test 1"));

		WebFieldTypeValues.put("SelectOne", Arrays.asList("b"));
		WebFieldTypeValues.put("Numeric3", Arrays.asList("-100"));
		WebFieldTypeValues.put("Numeric4", Arrays.asList("5000"));
		WebFieldTypeValues.put("Numeric5", Arrays.asList("8000"));
		WebFieldTypeValues.put("AGG1-SUM", Arrays.asList("15000"));
		WebFieldTypeValues.put("AGG1-SumRange", Arrays.asList("7900"));
		WebFieldTypeValues.put("Numeric1", Arrays.asList("100"));
		WebFieldTypeValues.put("MultiSelect", Arrays.asList("a"));

		WebFieldTypeValues.put("ParaGraph1",
				Arrays.asList("This is Paragraph Text and has to be checked with Maxixmun Field Values!@#$%^&*()"));
		WebFieldTypeValues.put("FreeText1", Arrays.asList("Single Line Text Value Has Entered Successsfully"));

		boolean verifyWebUpdatedValue = frp.verifyFieldValues(WebFieldTypeValues);
		TestValidation.IsTrue(verifyWebUpdatedValue, "VERIFIED updated value on Web as " + WebFieldTypeValues,
				"Failed to verify updated value on Web as " + WebFieldTypeValues);

	}

	@Test(description = " Verify user is able to submit check form with all field types and attachments in Offline mode")
	public void TestCase_37366_37403() throws Exception {
		appiumDriver = launchMobileApp();
		mobControlActions = new ControlActions_MobileApp(appiumDriver);

		REG_SubmissionsPage SavedPageObj = new REG_SubmissionsPage(appiumDriver);
		UploadImage oUploadImage = new UploadImage(appiumDriver);
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

		Boolean fillFormDetails = SavedPageObj.checkForm_fillFormDetailsWithAttachment();
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

		boolean isOffline2 = SavedPageObj.clickofflineToggleButton();
		TestValidation.IsTrue(isOffline2, "Verified offline Toggle button click Successfully",
				"Failed to click offline toggle button from Settings subMenu");

		boolean verifyStatus = SavedPageObj.verifyStatusAfterclickingResubmitAllButton(FormName, "RECEIVED");
		TestValidation.IsTrue(verifyStatus, "Verified form Status as Received Successfully",
				"Failed to verify Form status");

		SavedPageObj.clickFormFromSubmissionsScreen(FormName);
		oUploadImage.verifySelectedImageThroughGallery();

		LinkedHashMap<String, String> FieldTypeValues = new LinkedHashMap<String, String>();
		FieldTypeValues.put("Numeric", "10");
		FieldTypeValues.put("Identifier1", "1");
		FieldTypeValues.put("SelectOne", "B");
		FieldTypeValues.put("AGG1-SUM", "15000");
		FieldTypeValues.put("AGG1-SumRange", "7900");
		FieldTypeValues.put("IDNFieldGroup2", "TEST 1");
		FieldTypeValues.put("MultiSelect", "A");
		FieldTypeValues.put("Numeric1", "100");
		FieldTypeValues.put("Numeric3", "-100");
		FieldTypeValues.put("Numeric4", "5000");
		FieldTypeValues.put("Numeric5", "8000");
		FieldTypeValues.put("ParaGraph1",
				"This is Paragraph Text and has to be checked with Maxixmun Field Values!@#$%^&*()");
		FieldTypeValues.put("FreeText1", "Single Line Text Value Has Entered Successsfully");
		boolean verifyUpdatedValue = SavedPageObj.verifyFieldValues(FieldTypeValues);
		ControlActions_MobileApp.swipe(400, 700, 400, 100);
		// oUploadImage.verifySelectedImageThroughCamera("B");
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

		boolean navigate = fbp.selectResourceDropDownandNavigate("CUSTOMERS", "Records");
		TestValidation.IsTrue(navigate, "Navigated to FSQABrowser>RecordsTab",
				"Failed to navigate to FSQABrowser>Records Tab");

		boolean openRecord = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, FormName);
		TestValidation.IsTrue(openRecord, "OPENED record for form - " + FormName,
				"Failed to open record for form - " + FormName);

		FSQABrowserRecordsPage frp = new FSQABrowserRecordsPage(driver);

		HashMap<String, List<String>> WebFieldTypeValues = new HashMap<String, List<String>>();
		WebFieldTypeValues.put("Numeric", Arrays.asList("10"));
		WebFieldTypeValues.put("Identifier1", Arrays.asList("1"));
		WebFieldTypeValues.put("IDNFieldGroup2", Arrays.asList("Test 1"));
		WebFieldTypeValues.put("SelectOne", Arrays.asList("b"));
		WebFieldTypeValues.put("Numeric1", Arrays.asList("100"));
		WebFieldTypeValues.put("Numeric3", Arrays.asList("-100"));
		WebFieldTypeValues.put("Numeric4", Arrays.asList("5000"));
		WebFieldTypeValues.put("Numeric5", Arrays.asList("8000"));
		WebFieldTypeValues.put("AGG1-SUM", Arrays.asList("15000"));
		WebFieldTypeValues.put("AGG1-SumRange", Arrays.asList("7900"));
		WebFieldTypeValues.put("MultiSelect", Arrays.asList("a"));
		WebFieldTypeValues.put("ParaGraph1",
				Arrays.asList("This is Paragraph Text and has to be checked with Maxixmun Field Values!@#$%^&*()"));
		WebFieldTypeValues.put("FreeText1", Arrays.asList("Single Line Text Value Has Entered Successsfully"));

		boolean verifyWebUpdatedValue = frp.verifyFieldValues(WebFieldTypeValues);
		TestValidation.IsTrue(verifyWebUpdatedValue, "VERIFIED updated value on Web as " + WebFieldTypeValues,
				"Failed to verify updated value on Web as " + WebFieldTypeValues);

	}

	@Test(priority = 1, description = "Verify if same task is submitted by 2 different user then record status for both of the submissions to be displayed is received")
	public void TestCase_37398() throws Exception {

		String locInstId = locResMap.get(locationInstanceValue1);
		String ResInstId = locResMap.get(resourceInstanceValue1);

		// Task Creation

		String date = ControlActions_MobileApp.AddDaystoToddaysDateInGivenFormat("MM/dd/YYYY HH:mm", 2);
		TaskParams tp = new TaskParams();
		tp.FormId = formId;
		tp.DueBy = date;
		tp.FormName = FormName;
		tp.LocationInstanceNm = locationInstanceValue1;
		tp.ResourceInstanceNm = resourceInstanceValue1;
		tp.TaskName = TaskName;
		tp.WorkGroupName = WGName;
		tp.LocationInstanceId = locInstId;
		tp.ResourceInstanceId = ResInstId;
		tp.UserName = mobileAppUsername;

		taskCreationWrapper = new TCG_TaskCreation_Wrapper();
		taskCreationWrapper.create_Link_workGroup_Wrapper(tp);
		taskCreationWrapper.create_Task_Wrapper(tp);

		appiumDriver = launchMobileApp();
		TaskPage InboxPageObj = new TaskPage(appiumDriver);
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

		homePage.ClickSubMenu(homePage.inboxSubMenu);

		boolean searchForm = homePage.SearchTask(TaskName);
		TestValidation.IsTrue(searchForm, "Task Searched successfully", "Failed to search Task");

		driver=launchbrowser();
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
		InboxPage ip = hp.clickInboxMenu();
		boolean completetask = ip.selectFormTask(TaskName);
		TestValidation.IsTrue(completetask, "Form task selected" + TaskName, "Failed to select form task" + TaskName);

		LinkedHashMap<String, List<String>> fillDetails = new LinkedHashMap<String, List<String>>();
		fillDetails.put("Numeric", Arrays.asList("10"));
		fillDetails.put("Date1", Arrays.asList(dateSelected));
		fillDetails.put("Date2", Arrays.asList(dateSelected));
		fillDetails.put("Identifier1", Arrays.asList("1"));
		fillDetails.put("SelectOne", Arrays.asList("b"));
		fillDetails.put("IDNFieldGroup2", Arrays.asList("Test 1"));
		fillDetails.put("Numeric1", Arrays.asList("100"));
		fillDetails.put("Time1", Arrays.asList("12:00 AM"));
		fillDetails.put("Time2", Arrays.asList("2:30 PM"));
		fillDetails.put("Numeric4", Arrays.asList("5000"));
		fillDetails.put("Numeric3", Arrays.asList("-100"));
		fillDetails.put("Numeric4", Arrays.asList("5000"));
		fillDetails.put("Numeric5", Arrays.asList("8000"));
		fillDetails.put("MultiSelect", Arrays.asList("a"));
		fillDetails.put("DateTime1", Arrays.asList(dateTime));
		fillDetails.put("DateTime2", Arrays.asList(dateTime));
		fillDetails.put("ParaGraph1",
				Arrays.asList("This is Paragraph Text and has to be checked with Maxixmun Field Values!@#$%^&*()"));
		fillDetails.put("FreeText1", Arrays.asList("Single Line Text Value Has Entered Successsfully"));

		FormDetails fd = new FormDetails();
		fd.fieldDetails = fillDetails;
		FormOperations fo = new FormOperations(driver);
		boolean submit = fo.submitData(fd);
		TestValidation.IsTrue(submit, "Form submitted" + formName, "Failed to submit form" + formName);

		fbp = hp.clickFSQABrowserMenu();
		boolean navigateToRecords2 = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.CUSTOMERS, FSQATAB.RECORDS);
		TestValidation.IsTrue(navigateToRecords2, "For customer category, NAVIGATED to FSQABrowser > Records tab",
				"Failed to navigate to FSQABrowser > Records tab");

		boolean applyfilter3 = fbp.openAndApplySettingsForColumn(COLUMNHEADER.FORMNAME, COLUMNSETTING.FILTER, FormName);
		TestValidation.IsTrue(applyfilter3, "Applied Filter on" + COLUMNHEADER.FORMNAME,
				"Failed to apply filter on" + COLUMNHEADER.FORMNAME);

		int recordSize = fbp.TotalRecordsLst.size();
		TestValidation.IsTrue(recordSize == 1, "Verified Records Count for" + COLUMNHEADER.FORMNAME,
				"Failed to verify Record count" + COLUMNHEADER.FORMNAME);

		boolean clickTask = InboxPageObj.ClickTask(TaskName);
		TestValidation.IsTrue(clickTask, "Task Searched successfully", "Failed to search Task");

		Boolean fillFormDetails = SavedPageObj.checkForm_fillFormDetailsWithoutAttachment();
		TestValidation.IsTrue(fillFormDetails, "Form Details filled successfully", "Failed to fill form details");

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

		boolean navigate = fbp.selectResourceDropDownandNavigate("CUSTOMERS", "Records");
		TestValidation.IsTrue(navigate, "Navigated to FSQABrowser>RecordsTab",
				"Failed to navigate to FSQABrowser>Records Tab");

		boolean applyfilter4 = fbp.openAndApplySettingsForColumn(COLUMNHEADER.FORMNAME, COLUMNSETTING.FILTER, FormName);
		TestValidation.IsTrue(applyfilter4, "Applied Filter on" + COLUMNHEADER.FORMNAME,
				"Failed to apply filter on" + COLUMNHEADER.FORMNAME);

		int recordSize1 = fbp.TotalRecordsLst.size();
		TestValidation.IsTrue(recordSize1 == 1, "Verified Records Count for" + COLUMNHEADER.FORMNAME,
				"Failed to verify Record count" + COLUMNHEADER.FORMNAME);

	}

	@Test(priority = 7, description = "Verify after Updating the app the submitted form on old should be displayed in New updated application")
	public void TestCase_37391() throws Exception {
		appiumDriver = //launchMobileApp();
		 launchMobileAppReinstallFunctionality(prop.getProperty("oldApp"));
		mobControlActions = new ControlActions_MobileApp(appiumDriver);
		REG_SubmissionsPage SavedPageObj = new REG_SubmissionsPage(appiumDriver);
		LoginPage loginPage = new LoginPage(appiumDriver);
		HomePage homePage = new HomePage(appiumDriver);
//
//		if (mobileApp_Tenant.equals("test1.stage")) {
//			boolean SSOLogin = loginPage.SSO_Login(mobileAppUsername, mobileAppPassword);
//			TestValidation.IsTrue(SSOLogin, "logged into the application with User " + mobileAppUsername,
//					"Failed to log in");
//
//		} else {
//			boolean login1 = loginPage.Login(mobileAppUsername, mobileAppPassword);
//			TestValidation.IsTrue(login1, "logged into the application with User " + mobileAppUsername,
//					"Failed to log in");
//
//		}
		boolean login = loginPage.LoginwithTenant(mobileApp_Tenant, mobileAppUsername, mobileAppPassword);
		TestValidation.IsTrue(login, "logged into the application with User " + mobileAppUsername, "Failed to log in");

		Boolean searchFrms = homePage.SearchForm(FormName);
		TestValidation.IsTrue(searchFrms, "Form Searched successfully", "Failed to search Form");

		Boolean clickFrms = homePage.ClickForm(FormName);
		TestValidation.IsTrue(clickFrms, "Form Clicked successfully", "Failed to click Form");
		Boolean searchLocation = SavedPageObj.SearchResource(locationInstanceValue1);
		TestValidation.IsTrue(searchLocation, "Location Searched successfully", "Failed to search Location");
		Boolean searchResource = SavedPageObj.SearchResource(resourceInstanceValue1);
		TestValidation.IsTrue(searchResource, "Resource Searched successfully", "Failed to Resource Form");
		Thread.sleep(5000);
		Boolean fillFormDetails = SavedPageObj.checkForm_fillFormDetailsWithoutAttachment();
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
		Boolean chkStatus = SavedPageObj.checkFormStatusFromSubmissionsSubMenu(FormName, "RECEIVED");
		TestValidation.IsTrue(chkStatus, "Verified form Status Successfully", "Failed to verify Form status");

		homePage.ClickSubMenu(homePage.settingsSubMenu);
		ControlActions_MobileApp.click(SavedPageObj.offlineToggle);
		ControlActions_MobileApp.click(SavedPageObj.settingsBckBtn);

		Boolean clickForms = homePage.ClickMenu(homePage.formsSubMenu);
		TestValidation.IsTrue(clickForms, "Clicked on Forms subMenu Successfully", "Failed to click Forms subMenu");

		searchFrms = homePage.SearchForm(FormName);
		TestValidation.IsTrue(searchFrms, "Form Searched successfully", "Failed to search Form");

		clickFrms = homePage.ClickForm(FormName);
		TestValidation.IsTrue(clickFrms, "Form Clicked successfully", "Failed to click Form");
		searchLocation = SavedPageObj.SearchResource(locationInstanceValue1);
		TestValidation.IsTrue(searchLocation, "Location Searched successfully", "Failed to search Location");
		searchResource = SavedPageObj.SearchResource(resourceInstanceValue1);
		TestValidation.IsTrue(searchResource, "Resource Searched successfully", "Failed to Resource Form");
		Thread.sleep(5000);
		fillFormDetails = SavedPageObj.checkForm_fillFormDetailsWithoutAttachment();
		TestValidation.IsTrue(fillFormDetails, "Form Details filled successfully", "Failed to fill form details");

		submitForm = SavedPageObj.submitForm();
		TestValidation.IsTrue(submitForm, "Form Submitted Successfully", "Failed to Submit Form");
		clickSubmissions = homePage.ClickSubMenu(homePage.submissionsSubMenu);
		TestValidation.IsTrue(clickSubmissions, "Clicked on submissions subMenu Successfully",
				"Failed to click Submissions subMenu");
		ControlActions_MobileApp.click(SavedPageObj.sortBtn);
		searchForm = homePage.SearchForm(FormName);
		TestValidation.IsTrue(searchForm, "form Searched Successfully",
				"Failed to search form from submissions screen");

		appiumDriver.hideKeyboard();
		chkStatus = SavedPageObj.checkFormStatusFromSubmissionsSubMenu(FormName, "PENDING");
		TestValidation.IsTrue(chkStatus, "Verified form Status as Pending Successfully",
				"Failed to verify Form status");
		launchMobileAppReinstallFunctionality(prop.getProperty("androidAppLocation"));
//		appiumDriver = launchMobileAppReinstall();
		Thread.sleep(40000);
		boolean login1 = loginPage.LoginwithTenant(mobileApp_Tenant, mobileAppUsername, mobileAppPassword);
		TestValidation.IsTrue(login1, "logged into the application with User " + mobileAppUsername, "Failed to log in");

		clickSubmissions = homePage.ClickSubMenu(homePage.submissionsSubMenu);
		TestValidation.IsTrue(clickSubmissions, "Clicked on submissions subMenu Successfully",
				"Failed to click Submissions subMenu");
		ControlActions_MobileApp.click(SavedPageObj.sortBtn);
		searchForm = homePage.SearchForm(FormName);
		TestValidation.IsTrue(searchForm, "form Searched Successfully",
				"Failed to search form from submissions screen");
		chkStatus = SavedPageObj.checkFormListStatusFromSubmissionsMenu(FormName);
		TestValidation.IsTrue(chkStatus, "Verified form Status Successfully", "Failed to verify Form status");

		SavedPageObj.clickFormFromSubmissionsScreen(FormName);
		LinkedHashMap<String, String> FieldTypeValues = new LinkedHashMap<String, String>();
		FieldTypeValues.put("Numeric", "10");
		FieldTypeValues.put("Identifier1", "1");
		FieldTypeValues.put("SelectOne", "B");
		FieldTypeValues.put("AGG1-SUM", "15000");
		FieldTypeValues.put("AGG1-SumRange", "7900");
		FieldTypeValues.put("IDNFieldGroup2", "TEST 1");
		FieldTypeValues.put("Numeric1", "100");
		FieldTypeValues.put("MultiSelect", "A");
		FieldTypeValues.put("Numeric3", "-100");
		FieldTypeValues.put("Numeric4", "5000");
		FieldTypeValues.put("Numeric5", "8000");
		FieldTypeValues.put("ParaGraph1",
				"This is Paragraph Text and has to be checked with Maxixmun Field Values!@#$%^&*()");
		FieldTypeValues.put("FreeText1", "Single Line Text Value Has Entered Successsfully");
		boolean verifyUpdatedValue = SavedPageObj.verifyFieldValues(FieldTypeValues);

		logInfo("Field Values Map" + FieldTypeValues);

		TestValidation.IsTrue(verifyUpdatedValue, "VERIFIED updated value for field type Numeric as " + FieldTypeValues,
				"Failed to verify updated value for field type Numeric as " + FieldTypeValues);

	}

	@Test(description = "Verify background submission")
	public void TestCase_37272() throws Exception {
		appiumDriver = launchMobileApp();
		mobControlActions = new ControlActions_MobileApp(appiumDriver);
		REG_SubmissionsPage SavedPageObj = new REG_SubmissionsPage(appiumDriver);
		LoginPage loginPage = new LoginPage(appiumDriver);
		UploadImage oUploadImage = new UploadImage(appiumDriver);
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

		Boolean fillFormDetails = SavedPageObj.checkForm_fillFormDetailsWithAttachment();
		TestValidation.IsTrue(fillFormDetails, "Form Details filled successfully", "Failed to fill form details");

		Boolean submitForm = SavedPageObj.submitForm();
		TestValidation.IsTrue(submitForm, "Form Submitted Successfully", "Failed to Submit Form");
		List<String> clickHomeButtonArgs = Arrays.asList("keyevent", "KEYCODE_HOME");
		Map<String, Object> clickHomeCmd = new HashMap<String, Object>();
		clickHomeCmd.put("command", "input");
		clickHomeCmd.put("args", clickHomeButtonArgs);

		appiumDriver.executeScript("mobile: shell", clickHomeCmd);
		logInfo("Clicked on Home Button");

		Thread.sleep(5000);

		appiumDriver = launchMobileApp();
		Thread.sleep(10000);
		ControlActions_MobileApp.waitForVisibility(loginPage.UserName, 50);
		ControlActions_MobileApp.WaitforelementToBeClickable(loginPage.UserName);
		loginPage.UserName.click();
		boolean login2 = loginPage.Login(mobileAppUsername, mobileAppPassword);
		TestValidation.IsTrue(login2, "logged into the application with User " + mobileAppUsername, "Failed to log in");
		Thread.sleep(20000);
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
		oUploadImage.verifySelectedImageThroughGallery();
		LinkedHashMap<String, String> FieldTypeValues = new LinkedHashMap<String, String>();
		FieldTypeValues.put("Numeric", "10");
		FieldTypeValues.put("Identifier1", "1");
		FieldTypeValues.put("SelectOne", "B");
		FieldTypeValues.put("AGG1-SUM", "15000");
		FieldTypeValues.put("AGG1-SumRange", "7900");
		FieldTypeValues.put("IDNFieldGroup2", "TEST 1");

		FieldTypeValues.put("MultiSelect", "A");
		FieldTypeValues.put("Numeric1", "100");
		FieldTypeValues.put("Numeric3", "-100");
		FieldTypeValues.put("Numeric4", "5000");
		FieldTypeValues.put("Numeric5", "8000");
		FieldTypeValues.put("ParaGraph1",
				"This is Paragraph Text and has to be checked with Maxixmun Field Values!@#$%^&*()");
		FieldTypeValues.put("FreeText1", "Single Line Text Value Has Entered Successsfully");

		boolean verifyUpdatedValue = SavedPageObj.verifyFieldValues(FieldTypeValues);

		logInfo("Field Values Map" + FieldTypeValues);

		TestValidation.IsTrue(verifyUpdatedValue, "VERIFIED updated value for field type Numeric as " + FieldTypeValues,
				"Failed to verify updated value for field type Numeric as " + FieldTypeValues);

		ControlActions_MobileApp.swipe(400, 700, 400, 100);
		oUploadImage.verifySelectedImageThroughCamera("B");

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

		boolean navigate = fbp.selectResourceDropDownandNavigate("CUSTOMERS", "Records");
		TestValidation.IsTrue(navigate, "Navigated to FSQABrowser>RecordsTab",
				"Failed to navigate to FSQABrowser>Records Tab");

		boolean openRecord = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, FormName);
		TestValidation.IsTrue(openRecord, "OPENED record for form - " + FormName,
				"Failed to open record for form - " + FormName);

		FSQABrowserRecordsPage frp = new FSQABrowserRecordsPage(driver);

		HashMap<String, List<String>> WebFieldTypeValues = new HashMap<String, List<String>>();
		WebFieldTypeValues.put("Numeric", Arrays.asList("10"));
		WebFieldTypeValues.put("Identifier1", Arrays.asList("1"));
		WebFieldTypeValues.put("IDNFieldGroup2", Arrays.asList("Test 1"));

		WebFieldTypeValues.put("SelectOne", Arrays.asList("b"));
		WebFieldTypeValues.put("Numeric3", Arrays.asList("-100"));
		WebFieldTypeValues.put("Numeric4", Arrays.asList("5000"));
		WebFieldTypeValues.put("Numeric5", Arrays.asList("8000"));
		WebFieldTypeValues.put("AGG1-SUM", Arrays.asList("15000"));
		WebFieldTypeValues.put("AGG1-SumRange", Arrays.asList("7900"));
		WebFieldTypeValues.put("Numeric1", Arrays.asList("100"));
		WebFieldTypeValues.put("MultiSelect", Arrays.asList("a"));
		WebFieldTypeValues.put("ParaGraph1",
				Arrays.asList("This is Paragraph Text and has to be checked with Maxixmun Field Values!@#$%^&*()"));
		WebFieldTypeValues.put("FreeText1", Arrays.asList("Single Line Text Value Has Entered Successsfully"));

		boolean verifyWebUpdatedValue = frp.verifyFieldValues(WebFieldTypeValues);
		TestValidation.IsTrue(verifyWebUpdatedValue, "VERIFIED updated value on Web as " + WebFieldTypeValues,
				"Failed to verify updated value on Web as " + WebFieldTypeValues);

	}

	@Test(description = "Verify Pin Verification icon is not displayed on Form / Task, if user disable \"Direct Observation\" from \"Verification\" screen")
	public void TestCase_37313() throws Exception {

		String verificationName = "Direct Observation";

		try {

			driver = launchbrowser();
			fbp = new FSQABrowserPage(driver);
			controlActions = new ControlActions(driver);
			lp = new com.project.safetychain.webapp.pageobjects.LoginPage(driver);
			controlActions.getUrl(applicationUrl);

			hp = lp.performLogin(adminUsername, adminPassword);
			TestValidation.IsTrue(!hp.error, "LOGGED IN with Admin user " + adminUsername,
					"Failed to login with Admin user " + adminUsername);

			VerificationsPage vp = hp.clickVerificationsMenu();
			boolean disableVerification = vp.searchAndEnableDisableVerification(verificationName, true);
			TestValidation.IsTrue(disableVerification, "DISABLED the verification " + verificationName,
					"Failed to Disable the verification " + verificationName);

			String verificationStatus = vp.VerifyEnableDisableVerftnBtn.getAttribute("aria-checked");
			TestValidation.IsTrue(verificationStatus.equals("false"),
					"VERIFIED that the verification " + verificationName + " is disabled",
					"Failed to Verify that the verification " + verificationName + " is disabled or not");

			boolean logOutFromSC = hp.userLogout();
			TestValidation.IsTrue(logOutFromSC, "User successfully LOGGED OUT from Safety Chain application",
					"Failed to Logout user from Safety Chain application");

			appiumDriver = launchMobileApp();
			mobControlActions = new ControlActions_MobileApp(appiumDriver);

			LoginPage loginPage = new LoginPage(appiumDriver);
			if (mobileApp_Tenant.equals("test1.stage")) {
				boolean SSOLogin = loginPage.SSO_Login(mobileAppUsername, mobileAppPassword);
				TestValidation.IsTrue(SSOLogin, "LOGGED into the application with User " + mobileAppUsername,
						"Failed to Log in to mobile application");

			} else {
				boolean login = loginPage.Login(mobileAppUsername, mobileAppPassword);
				TestValidation.IsTrue(login, "LOGGED into the application with User " + mobileAppUsername,
						"Failed to Log in to mobile application");

			}

			HomePage homePage = new HomePage(appiumDriver);
			boolean searchFrm = homePage.SearchForm(FormName);
			TestValidation.IsTrue(searchFrm, "Form " + FormName + " SEARCHED successfully",
					"Failed to Search Form " + FormName);

			Boolean clickFrm = homePage.ClickForm(FormName);
			TestValidation.IsTrue(clickFrm, "OPENED Form successfully", "Failed to Open Form");

			REG_SubmissionsPage SavedPageObj = new REG_SubmissionsPage(appiumDriver);
			boolean searchLocation = SavedPageObj.SearchResource(locationInstanceValue1);
			TestValidation.IsTrue(searchLocation, "SEARCHED Location " + locationInstanceValue1 + " successfully",
					"Failed to search Location " + locationInstanceValue1);

			boolean searchResource = SavedPageObj.SearchResource(resourceInstanceValue1);
			TestValidation.IsTrue(searchResource, "SEARCHED Resource " + resourceInstanceValue1 + " successfully",
					"Failed to search Resource " + resourceInstanceValue1);

			WebElement PinBtn = ControlActions_MobileApp.perform_GetElementById(SavedPageLocators.PIN_BTN);
			System.out.println(PinBtn);
			TestValidation.IsFalse(PinBtn == null, "VERIFIED Pin Verification icon is not displayed for form",
					"Failed to Verify if Pin Verification icon is or is not displayed for form");

		} finally {
			hp = lp.performLogin(adminUsername, adminPassword);
			if (hp.error)
				TestValidation.Fail("Failed to login with Admin user " + adminUsername);

			VerificationsPage vp = hp.clickVerificationsMenu();
			boolean enableVerification = vp.searchAndEnableDisableVerification(verificationName, false);
			if (!enableVerification)
				TestValidation.Fail("Failed to Enable the verification " + verificationName);
		}
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
