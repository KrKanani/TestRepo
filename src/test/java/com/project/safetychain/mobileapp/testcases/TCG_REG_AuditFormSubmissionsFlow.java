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
import com.project.safetychain.webapp.pageobjects.FSQABrowserFormsPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserRecordsPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.COLUMNHEADER;
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

public class TCG_REG_AuditFormSubmissionsFlow extends TestBase {
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

	public static String locationCategoryValue;
	public static String locationInstanceValue1;
	public static String locationInstanceValue2;
	public static String resourceCategoryValue;
	public static String resourceInstanceValue1;
	public static String resourceInstanceValue2;
	public static String chkFreeTxt, ParaTxt, chkSelectOne, SelectMultiple, Numeric, chkDate, chkTime, chkDateTime;

	public static String formName = "Auto_Audit_15.10_17.19.19";
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
			FormName = CommonMethods.dynamicText("API_Audit");
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

			// Section2-

			Element_Types Section2 = new Element_Types();
			Section2.ElementType = DPT_FIELD_TYPES.SECTION;
			Section2.Name = "Section2";

			FormFieldParams selectMultipleField = new FormFieldParams();
			selectMultipleField.DPTFieldType = DPT_FIELD_TYPES.SELECT_MULTIPLE;
			selectMultipleField.Name = "MultiSelect";
			selectMultipleField.SelectOptions = Arrays.asList("70", "80", "90", "100");
			selectMultipleField.AllowAttachments = "true";

			FormFieldParams Identifier1 = new FormFieldParams();
			Identifier1.DPTFieldType = DPT_FIELD_TYPES.IDENTIFIER;
			Identifier1.Name = "IDNSection1";
			Identifier1.identifierId = IDENTIFIER_TYPES.SELECT_ONE;
			Identifier1.IdentifierOption = Arrays.asList("G3", "G4");

			Element_Types fieldGroup3 = new Element_Types();
			fieldGroup3.ElementType = DPT_FIELD_TYPES.FIELD_GROUP;
			fieldGroup3.Name = "FieldGroup3";
			fieldGroup3.DependencyRule = "if(IDNSection1=G3;Show)else(Hide)";

			FormFieldParams DateField1 = new FormFieldParams();
			DateField1.DPTFieldType = DPT_FIELD_TYPES.DATE;
			DateField1.Name = "Date1";

			FormFieldParams TimeField1 = new FormFieldParams();
			TimeField1.DPTFieldType = DPT_FIELD_TYPES.TIME;
			TimeField1.Name = "Time1";

			FormFieldParams DateTimeField1 = new FormFieldParams();
			DateTimeField1.DPTFieldType = DPT_FIELD_TYPES.DATE_TIME;
			DateTimeField1.Name = "DateTime1";

			fieldGroup3.formFieldParams = Arrays.asList(DateField1, TimeField1, DateTimeField1);

			Element_Types fieldGroup4 = new Element_Types();
			fieldGroup4.ElementType = DPT_FIELD_TYPES.FIELD_GROUP;
			fieldGroup4.Name = "FieldGroup4";
			fieldGroup4.DependencyRule = "if(IDNSection1=G4;Show)else(Hide)";

			FormFieldParams DateField2 = new FormFieldParams();
			DateField2.DPTFieldType = DPT_FIELD_TYPES.DATE;
			DateField2.Name = "Date2";

			FormFieldParams TimeField2 = new FormFieldParams();
			TimeField2.DPTFieldType = DPT_FIELD_TYPES.TIME;
			TimeField2.Name = "Time2";

			FormFieldParams DateTimeField2 = new FormFieldParams();
			DateTimeField2.DPTFieldType = DPT_FIELD_TYPES.DATE_TIME;
			DateTimeField2.Name = "DateTime2";

			fieldGroup4.formFieldParams = Arrays.asList(DateField2, TimeField2, DateTimeField2);

			Element_Types fieldGroup5 = new Element_Types();
			fieldGroup5.ElementType = DPT_FIELD_TYPES.FIELD_GROUP;
			fieldGroup5.Name = "FieldGroup5";

			FormFieldParams Identifier2 = new FormFieldParams();
			Identifier2.DPTFieldType = DPT_FIELD_TYPES.IDENTIFIER;
			Identifier2.Name = "IDNText2";
			Identifier2.identifierId = IDENTIFIER_TYPES.FREE_TEXT;
			Identifier2.InputMask = "0000";

			FormFieldParams Identifier3 = new FormFieldParams();
			Identifier3.DPTFieldType = DPT_FIELD_TYPES.IDENTIFIER;
			Identifier3.Name = "IDNText3";
			Identifier3.identifierId = IDENTIFIER_TYPES.FREE_TEXT;

			fieldGroup5.formFieldParams = Arrays.asList(Identifier2, Identifier3);

			Section2.formFieldParams = Arrays.asList(selectMultipleField, Identifier1);
			Section2.FieldGroupParams = Arrays.asList(fieldGroup3, fieldGroup4, fieldGroup5);

			String formId = apiUtils.getUUID();
			// Form details
			logInfo("FormId = " + formId);
			FormParams fp = new FormParams();
			logInfo("Form Name = " + formId);
			fp.FormId = formId;
			fp.FormName = FormName;
			logInfo("Form Name 2 = " + FormName);
			fp.type = DPT_FORM_TYPES.AUDIT;
			fp.ResourceType = RESOURCE_TYPES.CUSTOMERS;

			fp.ResourceCategoryNm = resourceCategoryValue;
			fp.ResourceInstanceNm = resourceInstanceValue1;
			fp.SectionElements = Arrays.asList(Section1, Section2);

			logInfo("fp.SectionElements = " + fp.SectionElements);

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

		Boolean fillFormDetails = SavedPageObj.auditForm_fillFormDetailsWithoutAttachment();
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
		FieldTypeValues.put("ParaGraph1",
				"This is Paragraph Text and has to be checked with Maxixmun Field Values!@#$%^&*()");
		FieldTypeValues.put("SingleLineText1", "Single Line Text Value Has Entered Successsfully");
		FieldTypeValues.put("SelectOne1", "2");
		FieldTypeValues.put("Numeric1", "-10000");
		FieldTypeValues.put("MultiSelect", "80");
		FieldTypeValues.put("IDNSection1", "G3");
		FieldTypeValues.put("IDNText2", "7777");
		FieldTypeValues.put("IDNText3", "Identifiert@1234");

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
		WebFieldTypeValues.put("ParaGraph1",
				Arrays.asList("This is Paragraph Text and has to be checked with Maxixmun Field Values!@#$%^&*()"));
		WebFieldTypeValues.put("SingleLineText1", Arrays.asList("Single Line Text Value Has Entered Successsfully"));
		WebFieldTypeValues.put("SelectOne1", Arrays.asList("2"));
		WebFieldTypeValues.put("Numeric1", Arrays.asList("-10000"));
		WebFieldTypeValues.put("MultiSelect", Arrays.asList("80"));
		WebFieldTypeValues.put("IDNSection1", Arrays.asList("G3"));
		WebFieldTypeValues.put("IDNText2", Arrays.asList("7777"));
		WebFieldTypeValues.put("IDNText3", Arrays.asList("Identifiert@1234"));

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

		Boolean fillFormDetails = SavedPageObj.auditForm_fillFormDetailsWithoutAttachment();
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
		FieldTypeValues.put("ParaGraph1",
				"This is Paragraph Text and has to be checked with Maxixmun Field Values!@#$%^&*()");
		FieldTypeValues.put("SingleLineText1", "Single Line Text Value Has Entered Successsfully");
		FieldTypeValues.put("SelectOne1", "2");
		FieldTypeValues.put("Numeric1", "-10000");
		FieldTypeValues.put("MultiSelect", "80");
		FieldTypeValues.put("IDNSection1", "G3");
		FieldTypeValues.put("IDNText2", "7777");
		FieldTypeValues.put("IDNText3", "Identifiert@1234");

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
		WebFieldTypeValues.put("ParaGraph1",
				Arrays.asList("This is Paragraph Text and has to be checked with Maxixmun Field Values!@#$%^&*()"));
		WebFieldTypeValues.put("SingleLineText1", Arrays.asList("Single Line Text Value Has Entered Successsfully"));
		WebFieldTypeValues.put("SelectOne1", Arrays.asList("2"));
		WebFieldTypeValues.put("Numeric1", Arrays.asList("-10000"));
		WebFieldTypeValues.put("MultiSelect", Arrays.asList("80"));
		WebFieldTypeValues.put("IDNSection1", Arrays.asList("G3"));
		WebFieldTypeValues.put("IDNText2", Arrays.asList("7777"));
		WebFieldTypeValues.put("IDNText3", Arrays.asList("Identifiert@1234"));

		boolean verifyWebUpdatedValue = frp.verifyFieldValues(WebFieldTypeValues);
		TestValidation.IsTrue(verifyWebUpdatedValue, "VERIFIED updated value on Web as " + WebFieldTypeValues,
				"Failed to verify updated value on Web as " + WebFieldTypeValues);

	}

	@Test(description = "Verify user is able to submit Audit form with all field types and attachments in Online mode")
	public void TestCase_37364() throws Exception {
		appiumDriver = launchMobileApp();
		UploadImage oUploadImage = new UploadImage(appiumDriver);
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

		Boolean fillFormDetails = SavedPageObj.auditForm_fillFormDetailsWithAttachment();
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
		FieldTypeValues.put("ParaGraph1",
				"This is Paragraph Text and has to be checked with Maxixmun Field Values!@#$%^&*()");
		FieldTypeValues.put("SingleLineText1", "Single Line Text Value Has Entered Successsfully");
		FieldTypeValues.put("SelectOne1", "2");
		FieldTypeValues.put("Numeric1", "-10000");
		FieldTypeValues.put("MultiSelect", "80");
		FieldTypeValues.put("IDNSection1", "G3");
		FieldTypeValues.put("IDNText2", "7777");
		FieldTypeValues.put("IDNText3", "Identifiert@1234");

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
		WebFieldTypeValues.put("ParaGraph1",
				Arrays.asList("This is Paragraph Text and has to be checked with Maxixmun Field Values!@#$%^&*()"));
		WebFieldTypeValues.put("SingleLineText1", Arrays.asList("Single Line Text Value Has Entered Successsfully"));
		WebFieldTypeValues.put("SelectOne1", Arrays.asList("2"));
		WebFieldTypeValues.put("Numeric1", Arrays.asList("-10000"));
		WebFieldTypeValues.put("MultiSelect", Arrays.asList("80"));
		WebFieldTypeValues.put("IDNSection1", Arrays.asList("G3"));
		WebFieldTypeValues.put("IDNText2", Arrays.asList("7777"));
		WebFieldTypeValues.put("IDNText3", Arrays.asList("Identifiert@1234"));

		boolean verifyWebUpdatedValue = frp.verifyFieldValues(WebFieldTypeValues);
		TestValidation.IsTrue(verifyWebUpdatedValue, "VERIFIED updated value on Web as " + WebFieldTypeValues,
				"Failed to verify updated value on Web as " + WebFieldTypeValues);

	}

	@Test(description = "Verify user is able to submit Audit form with all field types and attachments in Offline mode")
	public void TestCase_37367() throws Exception {
		appiumDriver = launchMobileApp();
		UploadImage oUploadImage = new UploadImage(appiumDriver);
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

		Boolean fillFormDetails = SavedPageObj.auditForm_fillFormDetailsWithAttachment();
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
		FieldTypeValues.put("ParaGraph1",
				"This is Paragraph Text and has to be checked with Maxixmun Field Values!@#$%^&*()");
		FieldTypeValues.put("SingleLineText1", "Single Line Text Value Has Entered Successsfully");
		FieldTypeValues.put("SelectOne1", "2");
		FieldTypeValues.put("Numeric1", "-10000");
		FieldTypeValues.put("MultiSelect", "80");
		FieldTypeValues.put("IDNSection1", "G3");
		FieldTypeValues.put("IDNText2", "7777");
		FieldTypeValues.put("IDNText3", "Identifiert@1234");

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
		WebFieldTypeValues.put("ParaGraph1",
				Arrays.asList("This is Paragraph Text and has to be checked with Maxixmun Field Values!@#$%^&*()"));
		WebFieldTypeValues.put("SingleLineText1", Arrays.asList("Single Line Text Value Has Entered Successsfully"));
		WebFieldTypeValues.put("SelectOne1", Arrays.asList("2"));
		WebFieldTypeValues.put("Numeric1", Arrays.asList("-10000"));
		WebFieldTypeValues.put("MultiSelect", Arrays.asList("80"));
		WebFieldTypeValues.put("IDNSection1", Arrays.asList("G3"));
		WebFieldTypeValues.put("IDNText2", Arrays.asList("7777"));
		WebFieldTypeValues.put("IDNText3", Arrays.asList("Identifiert@1234"));

		boolean verifyWebUpdatedValue = frp.verifyFieldValues(WebFieldTypeValues);
		TestValidation.IsTrue(verifyWebUpdatedValue, "VERIFIED updated value on Web as " + WebFieldTypeValues,
				"Failed to verify updated value on Web as " + WebFieldTypeValues);

	}

	@Test(description = " Verify User is able to Save and Submit the form")
	public void TestCase_37370() throws Exception {
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

		Boolean fillFormDetails = SavedPageObj.auditForm_fillFormDetailsWithoutAttachment();
		TestValidation.IsTrue(fillFormDetails, "Form Details filled successfully", "Failed to fill form details");

		boolean saveForm = SavedPageObj.saveForm();
		TestValidation.IsTrue(saveForm, "Form Saved Successfully", "Failed to Save Form from Forms SubTab");
		List<String> dateInFormat = SavedPageObj.TodaysDateinFormat("MM/dd/yyyy HH:mm z");
		boolean clickSaveMenu = homePage.ClickSubMenu(homePage.saveSubMenu);
		TestValidation.IsTrue(clickSaveMenu, "Clicked on Save subMenu Successfully", "Failed to click Save subMenu");
		ControlActions_MobileApp.click(SavedPageObj.sortBtn);
		Boolean searchFrms1 = homePage.SearchForm(FormName);
		TestValidation.IsTrue(searchFrms1, "Form Searched Successfully", "Failed to Search Form from Forms subMenu");
		List<String> dateInFormatMob = SavedPageObj.TodaysDateinFormat("M/d/YY HH:mm z");
		String datespliter[] = dateInFormatMob.get(0).split(" ");
		String Date = datespliter[0];
		boolean formStatus = SavedPageObj.checkFormStatusFromSavedSubMenu(FormName, Date);
		TestValidation.IsTrue(formStatus, "Verified Form Status Successfully",
				"Failed to verify Form Status on Submissions");

		SavedPageObj.clickFormFromSubmissionsScreen(FormName);

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
		FieldTypeValues.put("ParaGraph1",
				"This is Paragraph Text and has to be checked with Maxixmun Field Values!@#$%^&*()");
		FieldTypeValues.put("SingleLineText1", "Single Line Text Value Has Entered Successsfully");
		FieldTypeValues.put("SelectOne1", "2");
		FieldTypeValues.put("Numeric1", "-10000");
		FieldTypeValues.put("MultiSelect", "80");
		FieldTypeValues.put("IDNSection1", "G3");
		FieldTypeValues.put("IDNText2", "7777");
		FieldTypeValues.put("IDNText3", "Identifiert@1234");

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
		WebFieldTypeValues.put("ParaGraph1",
				Arrays.asList("This is Paragraph Text and has to be checked with Maxixmun Field Values!@#$%^&*()"));
		WebFieldTypeValues.put("SingleLineText1", Arrays.asList("Single Line Text Value Has Entered Successsfully"));
		WebFieldTypeValues.put("SelectOne1", Arrays.asList("2"));
		WebFieldTypeValues.put("Numeric1", Arrays.asList("-10000"));
		WebFieldTypeValues.put("MultiSelect", Arrays.asList("80"));
		WebFieldTypeValues.put("IDNSection1", Arrays.asList("G3"));
		WebFieldTypeValues.put("IDNText2", Arrays.asList("7777"));
		WebFieldTypeValues.put("IDNText3", Arrays.asList("Identifiert@1234"));

		boolean verifyWebUpdatedValue = frp.verifyFieldValues(WebFieldTypeValues);
		TestValidation.IsTrue(verifyWebUpdatedValue, "VERIFIED updated value on Web as " + WebFieldTypeValues,
				"Failed to verify updated value on Web as " + WebFieldTypeValues);

	}

	@Test(description = "Verify Clear all button features")
	public void TestCase_37274_1() throws Exception {
		appiumDriver = launchMobileApp();
		REG_SubmissionsPage SavedPageObj = new REG_SubmissionsPage(appiumDriver);
		LoginPage loginPage = new LoginPage(appiumDriver);
		mobControlActions = new ControlActions_MobileApp(appiumDriver);
		// Form submission

		HomePage homePage = new HomePage(appiumDriver);
		boolean login = loginPage.Login(mobileAppUsername, mobileAppPassword);
		TestValidation.IsTrue(login, "logged into the application with User " + mobileAppUsername, "Failed to log in");

		Boolean searchFrms = homePage.SearchForm(FormName);
		TestValidation.IsTrue(searchFrms, "Form Searched successfully", "Failed to search Form");
		Boolean clickFrms = homePage.ClickForm(FormName);
		TestValidation.IsTrue(clickFrms, "Form Clicked successfully", "Failed to click Form");
		Boolean searchLocation = SavedPageObj.SearchResource(locationInstanceValue1);
		TestValidation.IsTrue(searchLocation, "Location Searched successfully", "Failed to search Location");
		Boolean searchResource = SavedPageObj.SearchResource(resourceInstanceValue1);
		TestValidation.IsTrue(searchResource, "Resource Searched successfully", "Failed to Resource Form");

		Boolean fillFormDetails = SavedPageObj.auditForm_fillFormDetailsWithoutAttachment();
		TestValidation.IsTrue(fillFormDetails, "Form Details filled successfully", "Failed to fill form details");

		SavedPageObj.submitForm();
		homePage.ClickSubMenu(homePage.submissionsSubMenu);

		boolean searchFrms2 = homePage.SearchForm(FormName);
		TestValidation.IsTrue(searchFrms2, "Form Searched successfully", "Failed to search Form");
		boolean chkStatus1 = SavedPageObj.checkFormStatusFromSubmissionsSubMenu(FormName, "RECEIVED");
		TestValidation.IsTrue(chkStatus1, "Verified form Status Successfully", "Failed to verify Form status");
		appiumDriver.hideKeyboard();

		// Login with second user

		boolean logOut = loginPage.logOut();
		TestValidation.IsTrue(logOut, "logged out of the application " + mobileAppUsername, "Failed to log out");
		boolean login2 = loginPage.Login(mobileAppUsername02, mobileAppPassword02);
		TestValidation.IsTrue(login2, "logged into the application with User " + mobileAppUsername, "Failed to log in");
		// Form submission

		Boolean searchFrms1 = homePage.SearchForm(FormName);
		TestValidation.IsTrue(searchFrms1, "Form Searched successfully", "Failed to search Form");
		Boolean clickFrms1 = homePage.ClickForm(FormName);
		TestValidation.IsTrue(clickFrms1, "Form Clicked successfully", "Failed to click Form");
		Boolean searchLocation1 = SavedPageObj.SearchResource(locationInstanceValue1);
		TestValidation.IsTrue(searchLocation1, "Location Searched successfully", "Failed to search Location");
		Boolean searchResource1 = SavedPageObj.SearchResource(resourceInstanceValue1);
		TestValidation.IsTrue(searchResource1, "Resource Searched successfully", "Failed to Resource Form");

		Boolean fillFormDetails1 = SavedPageObj.auditForm_fillFormDetailsWithoutAttachment();
		TestValidation.IsTrue(fillFormDetails1, "Form Details filled successfully", "Failed to fill form details");

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
		ControlActions_MobileApp.click(SavedPageObj.closeSearch);
		appiumDriver.hideKeyboard();

		boolean clickClearAll = SavedPageObj.clickClearAll();
		TestValidation.IsTrue(clickClearAll, "Clicked on Clear All Button Successfully",
				"Failed to click Clear All Button on Submissions");
		boolean checkVisibity = SavedPageObj.checkFormVisibility(FormName);

		TestValidation.IsTrue((checkVisibity), "Verified Form Visibility on Submissions Successfully",
				"Failed to Verify Form Visibility on Submissions");

		// 1st User Login Again to check clear submission Functionality
		loginPage.logOut();
		boolean login3 = loginPage.Login(mobileAppUsername, mobileAppPassword);
		TestValidation.IsTrue(login3, "logged into the application with User " + mobileAppUsername, "Failed to log in");
		// Thread.sleep(60000);
		Boolean submissionClick1 = homePage.ClickMenu(homePage.submissionsSubMenu);

		TestValidation.IsTrue(submissionClick1, "submissionSubmenu clicked", "Unable to click on submissionsubmenu");
		boolean searchFrms3 = homePage.SearchForm(FormName);
		TestValidation.IsTrue(searchFrms3, "Form Searched successfully", "Failed to search Form");

	}

	@Test(description = "Resubmit All Features", priority = 0)
	public void TestCase_37274_2() throws Exception {
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
		for (int i = 0; i < 2; i++) {
			homePage.SearchForm(FormName);
			Boolean clickForm = homePage.ClickForm(FormName);
			TestValidation.IsTrue(clickForm, "Selected Forms to be submitted from Forms Tab Successfully",
					"Failed to Select Form from Submissions subMenu");
			boolean SelLocation = SavedPageObj.SearchResource(locationInstanceValue1);
			TestValidation.IsTrue(SelLocation, "Location Selected Successfully", "Failed to select Location");
			boolean SelResource = SavedPageObj.SearchResource(resourceInstanceValue1);
			TestValidation.IsTrue(SelResource, "Resource Selected Successfully", "Failed to select Resource");

			Boolean fillFormDetails1 = SavedPageObj.auditForm_fillFormDetailsWithoutAttachment();
			TestValidation.IsTrue(fillFormDetails1, "Form Details filled successfully", "Failed to fill form details");

			Boolean isSubmit = SavedPageObj.submitForm();
			TestValidation.IsTrue(isSubmit, "Form Submitted successfully", "Failed to Submit Form");
		}
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
		boolean ResubmitVisible = false;
		if (!(ControlActions_MobileApp.isElementDisplayed(SavedPageObj.resubmitBtn))
				&& (ControlActions_MobileApp.isElementDisplayed(SavedPageObj.clearAllBtn))) {
			ResubmitVisible = true;
		}

		TestValidation.IsTrue(ResubmitVisible, "Resubmit All button is not present",
				"Resubmit All button is still present");
		ControlActions_MobileApp.ClearText(SavedPageObj.searchField);
		ControlActions_MobileApp.actionEnterText(SavedPageObj.searchField, FormName);

		Boolean chkFormStatus = SavedPageObj.checkFormListStatusFromSubmissionsMenu(FormName);
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
		boolean navigate = fbp.navigateToFSQATab("Records");
		TestValidation.IsTrue(navigate, "Navigated to FSQABrowser>RecordsTab",
				"Failed to navigate to FSQABrowser>Records Tab");

		boolean applyfilter = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, FormName);

		TestValidation.IsTrue(applyfilter, "Applied Filter on" + COLUMNHEADER.FORMNAME,
				"Failed to apply filter on" + COLUMNHEADER.FORMNAME);

		String dateMinus1 = ControlActions_MobileApp.AddDaystoToddaysDateInGivenFormat("MM/dd/yyyy", -1);

		@SuppressWarnings("unused")
		String CurrentDate = ControlActions_MobileApp.AddDaystoToddaysDateInGivenFormat("MM/dd/yyyy", 0);

		System.out.println("New date is :" + dateMinus1);
		HashMap<String, List<String>> WebFieldTypeValues = new HashMap<String, List<String>>();
		WebFieldTypeValues.put("ParaGraph1",
				Arrays.asList("This is Paragraph Text and has to be checked with Maxixmun Field Values!@#$%^&*()"));
		WebFieldTypeValues.put("SingleLineText1", Arrays.asList("Single Line Text Value Has Entered Successsfully"));
		WebFieldTypeValues.put("SelectOne1", Arrays.asList("2"));
		WebFieldTypeValues.put("Numeric1", Arrays.asList("-10000"));
		WebFieldTypeValues.put("MultiSelect", Arrays.asList("80"));
		WebFieldTypeValues.put("IDNSection1", Arrays.asList("G3"));
		WebFieldTypeValues.put("IDNText2", Arrays.asList("7777"));
		WebFieldTypeValues.put("IDNText3", Arrays.asList("Identifiert@1234"));

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
