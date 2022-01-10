package com.project.safetychain.mobileapp.testcases;

import java.util.Arrays;
import java.util.HashMap;
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
import com.project.safetychain.api.models.support.TCG_TaskCreation_Wrapper;
import com.project.safetychain.mobileapp.pageobjects.HomePage;
import com.project.safetychain.mobileapp.pageobjects.LoginPage;
import com.project.safetychain.mobileapp.pageobjects.REG_FormsAllValidation;
import com.project.safetychain.mobileapp.pageobjects.REG_SubmissionsPage;
import com.project.safetychain.mobileapp.pageobjects.SavedPage;
import com.project.safetychain.mobileapp.pageobjects.SavedPageLocators;
import com.project.safetychain.webapp.pageobjects.CommonPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserFormsPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.COLUMNHEADER;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage;
import com.project.safetychain.webapp.pageobjects.FormsManagerPage;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FormDesignParams;
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

public class TCG_REG_PinVerificationFlow extends TestBase {
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
	FormsManagerPage fm;
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

	public static String formName = "Automation_CheckForm_15.10_17.19.19";
	public static String resourceName = "RInst1_15.10_13.47.04";
	public static String LocationName = "LInst1_15.10_13.47.04";
	String formtype = "Check";
	String modifiedby;
	public static String FormName;

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
			dateSelected = ControlActions_MobileApp.AddDaystoToddaysDateInGivenFormat("M/dd/yyyy h:mm a", 0);
			dateTime = ControlActions_MobileApp.formatdate2(dateSelected, "M/d/YYYY h:mm a");
			FormName = CommonMethods.dynamicText("Auto_CheckForm");//"Auto_CheckForm_12.10_16.05.16";//

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
			locationInstanceValue1 = CommonMethods.dynamicText("LInst1");//"LInst1_12.10_16.05.24";//
			locationInstanceValue2 = CommonMethods.dynamicText("LInst2");
			resourceCategoryValue = CommonMethods.dynamicText("RCat");
			resourceInstanceValue1 = CommonMethods.dynamicText("RInst1");//"RInst1_12.10_16.05.24" ; //
			resourceInstanceValue2 = CommonMethods.dynamicText("RInst2");

			// API Implementation

			FormFieldParams numericField = new FormFieldParams();
			numericField.DPTFieldType = DPT_FIELD_TYPES.NUMERIC;
			numericField.Name = Numeric;
			numericField.ShowHint = "true";
			numericField.Hint = "Numeric";
			numericField.RepeatField = "true";

			// numericField.RepeatField = "true";

			FormFieldParams dateField1 = new FormFieldParams();
			dateField1.DPTFieldType = DPT_FIELD_TYPES.DATE;
			dateField1.Name = "Date1";
			// dateField1.DependencyRule = "if(Numeric=10;Show)else(Hide)";

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

			FormFieldParams AGG3 = new FormFieldParams();
			AGG3.DPTFieldType = DPT_FIELD_TYPES.AGGREGATE;
			AGG3.Name = "AGG1-AVERAGE";
			AGG3.SelectedFunction = AGGREGATE_FUNC_TYPES.AVERAGE;
			AGG3.SelectedSource = "Numeric4";

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
			
			REG_FormsAllValidation afv = new REG_FormsAllValidation(appiumDriver);
			afv.waitForVisibilityOfSubmitNRepeat();
			
			WebElement PinBtn = ControlActions_MobileApp.perform_GetElementById(SavedPageLocators.PIN_BTN);
			TestValidation.IsTrue(PinBtn==null, "VERIFIED Pin Verification icon is not displayed for form",
					"Failed to Verify if Pin Verification icon is or is not displayed for form");

		} finally {
			hp = lp.performLogin(adminUsername, adminPassword);
			if (hp.error)
				TestValidation.Fail("Failed to login with Admin user " + adminUsername);

			VerificationsPage vp = hp.clickVerificationsMenu();
			boolean enableVerification = vp.searchAndEnableDisableVerification(verificationName, false);
			if (!enableVerification)
				TestValidation.Fail("Failed to Enable the verification " + verificationName);
			
			driver.quit();
		}
	}

	@Test(description = "Verify Pin Verification icon is not displayed on Form / Task, if user disable \"Direct Observation\" from \"Verification\" screen",priority=10)
	public void TestCase_37314() throws Exception {

		String verificationName = "Direct Observation";

		driver = launchbrowser();
		try {
			fbp = new FSQABrowserPage(driver);
			controlActions = new ControlActions(driver);
			lp = new com.project.safetychain.webapp.pageobjects.LoginPage(driver);
			controlActions.getUrl(applicationUrl);
			fm = new FormsManagerPage(driver);
			hp = lp.performLogin(adminUsername, adminPassword);
			TestValidation.IsTrue(!hp.error, "LOGGED IN with Admin user " + adminUsername,
					"Failed to login with Admin user " + adminUsername);

			VerificationsPage vp = hp.clickVerificationsMenu();
			boolean disableVerification = vp.searchAndEnableDisableVerification(verificationName, false);
			TestValidation.IsTrue(disableVerification, "Enabled the verification " + verificationName,
					"Failed to Enabled the verification " + verificationName);

			String verificationStatus = vp.VerifyEnableDisableVerftnBtn.getAttribute("aria-checked");
			TestValidation.IsTrue(verificationStatus.equals("true"),
					"VERIFIED that the verification " + verificationName + " is enabled",
					"Failed to Verify that the verification " + verificationName + " is enabled or not");

			hp.clickFormsManagerMenu();
			boolean delinkDirectObservationtoForm = fm.delinkVerificationwithForm(verificationName, FormName);
			TestValidation.IsTrue(delinkDirectObservationtoForm,
					"Verification Direct observation is delinked with Form",
					"Failed to delink the Verification Direct observation with Form");

			boolean logOutFromSC = hp.userLogout();
			TestValidation.IsTrue(logOutFromSC, "User successfully LOGGED OUT from Safety Chain application",
					"Failed to Logout user from Safety Chain application");
			}finally {
				driver.quit();
			}
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
			
			REG_FormsAllValidation afv = new REG_FormsAllValidation(appiumDriver);
			afv.waitForVisibilityOfSubmitNRepeat();
			
			WebElement PinBtn = ControlActions_MobileApp.perform_GetElementById(SavedPageLocators.PIN_BTN);
			TestValidation.IsTrue(PinBtn==null, "VERIFIED Pin Verification icon is not displayed for form",
					"Failed to Verify if Pin Verification icon is or is not displayed for form");

	}

	@Test(description = "Verify Pin Verification icon is displayed on Form / Task, if user enable \"Direct Observation\" from \"Verification\" screen and \"Direct Observation\" is Enable on form Level")
	public void TestCase_37315() throws Exception {

		String verificationName = "Direct Observation";
		try {
			driver = launchbrowser();
			fbp = new FSQABrowserPage(driver);
			controlActions = new ControlActions(driver);
			lp = new com.project.safetychain.webapp.pageobjects.LoginPage(driver);
			controlActions.getUrl(applicationUrl);
			fm = new FormsManagerPage(driver);
			hp = lp.performLogin(adminUsername, adminPassword);
			TestValidation.IsTrue(!hp.error, "LOGGED IN with Admin user " + adminUsername,
					"Failed to login with Admin user " + adminUsername);

			VerificationsPage vp = hp.clickVerificationsMenu();
			boolean disableVerification = vp.searchAndEnableDisableVerification(verificationName, false);
			TestValidation.IsTrue(disableVerification, "DISABLED the verification " + verificationName,
					"Failed to Disable the verification " + verificationName);

			String verificationStatus = vp.VerifyEnableDisableVerftnBtn.getAttribute("aria-checked");
			TestValidation.IsTrue(verificationStatus.equals("true"),
					"VERIFIED that the verification " + verificationName + " is enabled",
					"Failed to Verify that the verification " + verificationName + " is enabled or not");

			boolean logOutFromSC = hp.userLogout();
			TestValidation.IsTrue(logOutFromSC, "User successfully LOGGED OUT from Safety Chain application",
					"Failed to Logout user from Safety Chain application");
			
		}finally {
			driver.quit();
		}
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

			REG_FormsAllValidation afv = new REG_FormsAllValidation(appiumDriver);
			afv.waitForVisibilityOfSubmitNRepeat();
			
			WebElement PinBtn = ControlActions_MobileApp.perform_GetElementById(SavedPageLocators.PIN_BTN);
			TestValidation.IsTrue(PinBtn != null, "VERIFIED Pin Verification icon is not displayed for form",
					"Failed to Verify if Pin Verification icon is or is not displayed for form");

//		} finally {
////			hp = lp.performLogin(adminUsername, adminPassword);
////			if (hp.error)
////				TestValidation.Fail("Failed to login with Admin user " + adminUsername);
////
////			VerificationsPage vp = hp.clickVerificationsMenu();
////			boolean enableVerification = vp.searchAndEnableDisableVerification(verificationName, false);
////			if (!enableVerification)
////				TestValidation.Fail("Failed to Enable the verification " + verificationName);
//		}
	}

	@Test(description = "Verify pin verification should work on saved forms, after enabling the \"Direct Observation\" on verification screen and on form Level.||Verify Pin verification icon gets disabled after saving with verification credentials")
	public void TestCase_37316_37317() throws Exception {
		String verificationName = "Direct Observation";
		String User = mobileAppUsername;
		String Pin = "1234";
		boolean iscompliant = true;
		String comment = "Pin Verification";

		driver = launchbrowser();
		try {
		fbp = new FSQABrowserPage(driver);
		fop = new FormOperations(driver);
		controlActions = new ControlActions(driver);
		fbForms = new FSQABrowserFormsPage(driver);
		lp = new com.project.safetychain.webapp.pageobjects.LoginPage(driver);
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
		threadsleep(5000);

		fop.selectLocationResource(locationInstanceValue1, resourceInstanceValue1);

		controlActions.clickElement(fbForms.SaveBtn);
		fbForms.Sync();
		logInfo("The form is filled & saved sucessfully");

		VerificationsPage vp = hp.clickVerificationsMenu();
		boolean disableVerification = vp.searchAndEnableDisableVerification(verificationName, false);
		TestValidation.IsTrue(disableVerification, "DISABLED the verification " + verificationName,
				"Failed to Disable the verification " + verificationName);

		String verificationStatus = vp.VerifyEnableDisableVerftnBtn.getAttribute("aria-checked");
		TestValidation.IsTrue(verificationStatus.equals("true"),
				"VERIFIED that the verification " + verificationName + " is enabled",
				"Failed to Verify that the verification " + verificationName + " is enabled or not");

		boolean logOutFromSC = hp.userLogout();
		TestValidation.IsTrue(logOutFromSC, "User successfully LOGGED OUT from Safety Chain application",
				"Failed to Logout user from Safety Chain application");

		}finally {
		driver.quit();
		}
		appiumDriver = launchMobileApp();

		SavedPage SavedPageObj = new SavedPage(appiumDriver);
		LoginPage loginPage = new LoginPage(appiumDriver);
		mobControlActions = new ControlActions_MobileApp(appiumDriver);
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

		boolean searchForm = homePage.SearchForm(FormName);
		TestValidation.IsTrue(searchForm, "Form Searched successfully", "Failed to search Form");
		Boolean clickForm = homePage.ClickForm(FormName);
		TestValidation.IsTrue(clickForm, "Clicked on Searched form Successfully",
				"Failed to click on Form on Submissions");
		Boolean searchLocation = SavedPageObj.SearchResource(locationInstanceValue1);
		TestValidation.IsTrue(searchLocation, "Searched Location Successfully", "Failed to search Form on Submissions");
		Boolean searchResource = SavedPageObj.SearchResource(resourceInstanceValue1);
		TestValidation.IsTrue(searchResource, "Searched Location Successfully", "Failed to search Form on Submissions");

		boolean clickPinBtn = SavedPageObj.ModifyPin(appiumDriver);
		TestValidation.IsTrue(clickPinBtn, "Clicked on Pin Button Successfully", "Failed to click on Pin Button");
		Boolean enterPinDetails = SavedPageObj.ModifyPinDetails(User, Pin, iscompliant, comment);
		TestValidation.IsTrue(enterPinDetails, "Pin Details entered Successfully",
				"Failed to enter pin Details on Verification Popup");
		boolean savePin = SavedPageObj.SavePin();
		TestValidation.IsTrue(savePin, "Pin Details Saved Successfully",
				"Failed to save pin Details on Verification Popup");
		boolean verifyPinetails = SavedPageObj.verifyPinDetailsFromSubmissionScreen(FormName, mobileApp_FNameLName,
				mobileApp_Tenant, iscompliant, comment);
		TestValidation.IsTrue(verifyPinetails, "Pin Details Verified Successfully from Submissions screen",
				"Failed to verify Pin Details from Submissions screen");

	}

	@Test(description = "Verify pin verification icon gets enabled after submit & repeat")
	public void TestCase_37319() throws Exception {

		String User = mobileAppUsername;
		String Pin = "1234";
		boolean iscompliant = true;
		String comment = "Pin Verification";
		appiumDriver = launchMobileApp();
		mobControlActions = new ControlActions_MobileApp(appiumDriver);

		REG_SubmissionsPage SavedPageObj = new REG_SubmissionsPage(appiumDriver);
		REG_FormsAllValidation afv = new REG_FormsAllValidation(appiumDriver);
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

		boolean clickPinBtn = SavedPageObj.ModifyPin(appiumDriver);
		TestValidation.IsTrue(clickPinBtn, "Clicked on Pin Button Successfully", "Failed to click on Pin Button");
		Boolean enterPinDetails = SavedPageObj.ModifyPinDetails(User, Pin, iscompliant, comment);
		TestValidation.IsTrue(enterPinDetails, "Pin Details entered Successfully",
				"Failed to enter pin Details on Verification Popup");
		boolean savePin = SavedPageObj.SavePin();
		TestValidation.IsTrue(savePin, "Pin Details Saved Successfully",
				"Failed to save pin Details on Verification Popup");

		Boolean fillFormDetails = SavedPageObj.checkForm_fillFormDetailsWithoutAttachment();
		TestValidation.IsTrue(fillFormDetails, "Form Details filled successfully", "Failed to fill form details");

		Boolean submitForm = afv.clickSubmitAndRepeat();
		TestValidation.IsTrue(submitForm, "Form Submitted Successfully", "Failed to Submit Form");

		Thread.sleep(10000);

		boolean clickPinBtn1 = SavedPageObj.ModifyPin(appiumDriver);
		TestValidation.IsTrue(clickPinBtn1, "Clicked on Pin Button Successfully", "Failed to click on Pin Button");
		Boolean enterPinDetails1 = SavedPageObj.ModifyPinDetails(User, Pin, iscompliant, comment);
		TestValidation.IsTrue(enterPinDetails1, "Pin Details entered Successfully",
				"Failed to enter pin Details on Verification Popup");
		boolean savePin1 = SavedPageObj.SavePin();
		TestValidation.IsTrue(savePin1, "Pin Details Saved Successfully",
				"Failed to save pin Details on Verification Popup");

		Boolean fillFormDetails1 = SavedPageObj.checkForm_fillFormDetailsWithoutAttachment();
		TestValidation.IsTrue(fillFormDetails1, "Form Details filled successfully", "Failed to fill form details");

		Boolean submitForm1 = SavedPageObj.submitForm();
		TestValidation.IsTrue(submitForm1, "Form Submitted Successfully", "Failed to Submit Form");

	}

	@AfterMethod(alwaysRun = true)
	public void closeAppium() throws InterruptedException {
		appiumDriver.closeApp();
	}

	@AfterClass(alwaysRun = true)
	public void closeBrowser() throws InterruptedException {
		//driver.quit();

	}

}
