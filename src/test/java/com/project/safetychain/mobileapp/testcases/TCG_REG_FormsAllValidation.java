package com.project.safetychain.mobileapp.testcases;

import java.awt.AWTException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.project.safetychain.api.models.support.Support.AGGREGATE_FUNC_TYPES;
import com.project.safetychain.api.models.support.Support.DPT_FIELD_TYPES;
import com.project.safetychain.api.models.support.Support.DPT_FORM_TYPES;
import com.project.safetychain.api.models.support.Support.Element_Types;
import com.project.safetychain.api.models.support.Support.FormFieldParams;
import com.project.safetychain.api.models.support.Support.FormParams;
import com.project.safetychain.api.models.support.Support.RESOURCE_TYPES;
import com.project.safetychain.api.models.support.TCG_FormCreation_Wrapper;
import com.project.safetychain.mobileapp.pageobjects.HomePage;
import com.project.safetychain.mobileapp.pageobjects.LoginPage;
import com.project.safetychain.mobileapp.pageobjects.REG_AllFormsSaved;
import com.project.safetychain.mobileapp.pageobjects.REG_DynamicFlow;
import com.project.safetychain.mobileapp.pageobjects.REG_FormsAllValidation;
import com.project.safetychain.mobileapp.pageobjects.REG_InboxTaskPage;
import com.project.safetychain.mobileapp.pageobjects.SavedPage;
import com.project.safetychain.webapp.pageobjects.CommonPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserFormsPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.COLUMNHEADER;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.FSQATAB;
import com.project.safetychain.webapp.pageobjects.FSQABrowserRecordsPage;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FIELD_SETTINGS;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FormDesignParams;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.PROPERTIES_TABS;
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

public class TCG_REG_FormsAllValidation extends TestBase {

	ControlActions_MobileApp mobControlActions;
	ApiUtils apiUtils = null;
	FormDesignerPage formDesignerPage;
	ControlActions controlActions;
	FSQABrowserPage fbp;
	FSQABrowserFormsPage fbForms;
	FSQABrowserRecordsPage fbRecords;
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
	REG_AllFormsSaved afSaved;
	REG_InboxTaskPage inbox;
	public WebDriver driver;
	REG_FormsAllValidation afv;
	public static String locationCategoryValue;
	public static String locationInstanceValue1;
	public static String locationInstanceValue2;
	public static String resourceCategoryValue;
	public static String resourceInstanceValue1;
	public static String resourceInstanceValue2;
	public static String chkFreeTxt, ParaTxt, chkSelectOne, SelectMultiple, Numeric, chkDate, chkTime, chkDateTime;

	String modifiedby;
	public static String FormNameCreated;
	public static final String fresult = "com.safetychain.SCM.M2:id/resultView_num";
	@AndroidFindBy(id = fresult)
	public WebElement NTxt;

	@Test(description = "Form and fields validation")
	public void TestCase_37226_29_31_33_39_43_48_45_47_53_54_55_37423_37381()
			throws InterruptedException, ElementNotVisibleException, ParseException, AWTException {

		appiumDriver = launchMobileApp();
		afv = new REG_FormsAllValidation(appiumDriver);
		mobControlActions = new ControlActions_MobileApp(appiumDriver);
		afv.formAllValidation("ABC1.csv");
	}

	@Test(description = "DateDifference ,Expression Rule")
	public void TestCase_37227_37238_37249()
			throws InterruptedException, ElementNotVisibleException, ParseException, AWTException {

		appiumDriver = launchMobileApp();
		afv = new REG_FormsAllValidation(appiumDriver);
		afv.ExpressionDateDiffIdentifiers();

	}

	@Test(description = "Verify Delete, Discard & Back form")
	public void TestCase_37241() throws InterruptedException, ElementNotVisibleException, ParseException, AWTException {

		appiumDriver = launchMobileApp();
		afv = new REG_FormsAllValidation(appiumDriver);
		mobControlActions = new ControlActions_MobileApp(appiumDriver);
		LoginPage loginPage = new LoginPage(appiumDriver);
		if (mobileApp_Tenant.equals("test1.stage")) {
			boolean SSOLogin = loginPage.SSO_Login(mobileAppUsername, mobileAppPassword);
			TestValidation.IsTrue(SSOLogin, "logged into the application with User " + mobileAppUsername,
					"Failed to log in");

		} else {
			boolean login = loginPage.Login(mobileAppUsername, mobileAppPassword);
			TestValidation.IsTrue(login, "logged into the application with User " + mobileAppUsername,
					"Failed to log in");

		}

		afv.deleteBackDiscard();

	}

	@Test(description = "Aggregate Advanced Function - Verify Aggregate field evaluation having Count Range function configured with negative/Positive Source field values")
	public void TestCase_39145_47_48_58_59()
			throws InterruptedException, ElementNotVisibleException, ParseException, AWTException {

		appiumDriver = launchMobileApp();
		afv = new REG_FormsAllValidation(appiumDriver);
		mobControlActions = new ControlActions_MobileApp(appiumDriver);
		LoginPage loginPage = new LoginPage(appiumDriver);
		if (mobileApp_Tenant.equals("test1.stage")) {
			boolean SSOLogin = loginPage.SSO_Login(mobileAppUsername, mobileAppPassword);
			TestValidation.IsTrue(SSOLogin, "logged into the application with User " + mobileAppUsername,
					"Failed to log in");

		} else {
			boolean login = loginPage.Login(mobileAppUsername, mobileAppPassword);
			TestValidation.IsTrue(login, "logged into the application with User " + mobileAppUsername,
					"Failed to log in");

		}

		afv.PositiveNegativeSourceCountRangeValidation();

	}

	@Test(description = "Aggregate Advanced Function - Verify Aggregate field evaluation having Sum Range function configured with negative/Positive Source field values")
	public void TestCase_41127_28_29_36_37()
			throws InterruptedException, ElementNotVisibleException, ParseException, AWTException {

		appiumDriver = launchMobileApp();
		afv = new REG_FormsAllValidation(appiumDriver);
		mobControlActions = new ControlActions_MobileApp(appiumDriver);
		LoginPage loginPage = new LoginPage(appiumDriver);
		if (mobileApp_Tenant.equals("test1.stage")) {
			boolean SSOLogin = loginPage.SSO_Login(mobileAppUsername, mobileAppPassword);
			TestValidation.IsTrue(SSOLogin, "logged into the application with User " + mobileAppUsername,
					"Failed to log in");

		} else {
			boolean login = loginPage.Login(mobileAppUsername, mobileAppPassword);
			TestValidation.IsTrue(login, "logged into the application with User " + mobileAppUsername,
					"Failed to log in");

		}

		afv.PositiveNegativeSourceSumRangeValidation();

	}

	@Test(description = "Verify Aggregate field")
	public void TestCase_37237_41139_41140()
			throws InterruptedException, ElementNotVisibleException, ParseException, AWTException {

		appiumDriver = launchMobileApp();
		afv = new REG_FormsAllValidation(appiumDriver);
		mobControlActions = new ControlActions_MobileApp(appiumDriver);
		LoginPage loginPage = new LoginPage(appiumDriver);
		if (mobileApp_Tenant.equals("test1.stage")) {
			boolean SSOLogin = loginPage.SSO_Login(mobileAppUsername, mobileAppPassword);
			TestValidation.IsTrue(SSOLogin, "logged into the application with User " + mobileAppUsername,
					"Failed to log in");

		} else {
			boolean login = loginPage.Login(mobileAppUsername, mobileAppPassword);
			TestValidation.IsTrue(login, "logged into the application with User " + mobileAppUsername,
					"Failed to log in");

		}

		afv.aggregateFieldsValidation();

	}

	@Test(description = "Verify Decimal value calculation for aggregate")
	public void TestCase_37251_41143()
			throws InterruptedException, ElementNotVisibleException, ParseException, AWTException {

		appiumDriver = launchMobileApp();
		afv = new REG_FormsAllValidation(appiumDriver);
		mobControlActions = new ControlActions_MobileApp(appiumDriver);
		LoginPage loginPage = new LoginPage(appiumDriver);
		if (mobileApp_Tenant.equals("test1.stage")) {
			boolean SSOLogin = loginPage.SSO_Login(mobileAppUsername, mobileAppPassword);
			TestValidation.IsTrue(SSOLogin, "logged into the application with User " + mobileAppUsername,
					"Failed to log in");

		} else {
			boolean login = loginPage.Login(mobileAppUsername, mobileAppPassword);
			TestValidation.IsTrue(login, "logged into the application with User " + mobileAppUsername,
					"Failed to log in");

		}

		afv.decimalAggregateFieldsValidation();

	}

	@Test(description = "Verify Image Delete functionality")
	public void TestCase_37236() throws InterruptedException, ElementNotVisibleException, ParseException, AWTException {

		appiumDriver = launchMobileApp();
		afv = new REG_FormsAllValidation(appiumDriver);
		mobControlActions = new ControlActions_MobileApp(appiumDriver);
		LoginPage loginPage = new LoginPage(appiumDriver);
		if (mobileApp_Tenant.equals("test1.stage")) {
			boolean SSOLogin = loginPage.SSO_Login(mobileAppUsername, mobileAppPassword);
			TestValidation.IsTrue(SSOLogin, "logged into the application with User " + mobileAppUsername,
					"Failed to log in");

		} else {
			boolean login = loginPage.Login(mobileAppUsername, mobileAppPassword);
			TestValidation.IsTrue(login, "logged into the application with User " + mobileAppUsername,
					"Failed to log in");

		}

		afv.imageDeleteFieldAndFormLevel();

	}

	@Test(description = "Verify Submit & Repeat")
	public void TestCase_37249_37369() throws Exception {

		String resourceType = "Customers";
		String fsqaTab = "Records";

		// RESOURCES AND LOCATIONS
		String locationCategoryValue = CommonMethods.dynamicText("LCat");
		String locationInstanceValue1 = CommonMethods.dynamicText("LInst1");
		String locationInstanceValue2 = CommonMethods.dynamicText("LInst2");
		String resourceCategoryValue = CommonMethods.dynamicText("RCat");
		String resourceInstanceValue1 = CommonMethods.dynamicText("RInst1");
		String resourceInstanceValue2 = CommonMethods.dynamicText("RInst2");

		// TO BE Created Form Details
		String formName = CommonMethods.dynamicText("API_ChkForm");
		String freeTxt1 = "FreeText1", freeTxt1Val = "Free1";
		String freeTxt2 = "FreeText2", freeTxt2Val = "Free2";
		String paraTxt1 = "Paragraph1", paraTxt1Val = "Para1";
		String paraTxt2 = "Paragraph2", paraTxt2Val = "Para2";
		String numeric1 = "Numeric1", numeric1Val = "8";
		String numeric2 = "Numeric2", numeric2Val = "80";

		TCG_FormCreation_Wrapper formCreationWrapper = new TCG_FormCreation_Wrapper();

		apiUtils = new ApiUtils();

		// DEFIING RESOURCES AND LOCATIONS
		HashMap<String, List<String>> resourceCatMap = new HashMap<String, List<String>>();
		resourceCatMap.put(resourceCategoryValue, Arrays.asList(resourceInstanceValue1, resourceInstanceValue2));

		HashMap<String, List<String>> LocationMap = new HashMap<String, List<String>>();
		LocationMap.put(locationCategoryValue, Arrays.asList(locationInstanceValue1, locationInstanceValue2));

		// CREATING and LINKING RESOURECES LOCATIONS and USERS
		List<String> userList = Arrays.asList(mobileAppUsername02, mobileAppUsername);
		formCreationWrapper.Resource_Location_CreationWrapper(resourceCatMap, LocationMap, true, userList,
				RESOURCE_TYPES.CUSTOMERS);

		// DEFINEING FIELDS
		FormFieldParams numericField1 = new FormFieldParams();
		numericField1.DPTFieldType = DPT_FIELD_TYPES.NUMERIC;
		numericField1.Name = numeric1;
		numericField1.RepeatField = "true";

		FormFieldParams numericField2 = new FormFieldParams();
		numericField2.DPTFieldType = DPT_FIELD_TYPES.NUMERIC;
		numericField2.Name = numeric2;

		FormFieldParams paraTextField1 = new FormFieldParams();
		paraTextField1.DPTFieldType = DPT_FIELD_TYPES.PARAGRAPH_TEXT;
		paraTextField1.Name = paraTxt1;
		paraTextField1.RepeatField = "true";

		FormFieldParams paraTextField2 = new FormFieldParams();
		paraTextField2.DPTFieldType = DPT_FIELD_TYPES.PARAGRAPH_TEXT;
		paraTextField2.Name = paraTxt2;

		FormFieldParams freeTextField1 = new FormFieldParams();
		freeTextField1.DPTFieldType = DPT_FIELD_TYPES.FREE_TEXT;
		freeTextField1.Name = freeTxt1;
		freeTextField1.RepeatField = "true";

		FormFieldParams freeTextField2 = new FormFieldParams();
		freeTextField2.DPTFieldType = DPT_FIELD_TYPES.FREE_TEXT;
		freeTextField2.Name = freeTxt2;

		// CREATING FORM
		String formId = apiUtils.getUUID();
		// Form details
		logInfo("FormId = " + formId);
		FormParams fp = new FormParams();
		fp.FormId = formId;
		fp.FormName = formName;
		logInfo("Form Name = " + formName);
		fp.type = DPT_FORM_TYPES.CHECK;
		fp.ResourceType = RESOURCE_TYPES.CUSTOMERS;
		fp.ResourceCategoryNm = resourceCategoryValue;
		fp.ResourceInstanceNm = resourceInstanceValue1;
		fp.formElements = Arrays.asList(numericField1, numericField2, freeTextField1, freeTextField2, paraTextField1,
				paraTextField2);
		fp.CustomerResources = resourceCatMap;
		logInfo("fp.formElements = " + fp.formElements);

		formCreationWrapper.API_Wrapper_Forms(fp);

		appiumDriver = launchMobileApp();
		afv = new REG_FormsAllValidation(appiumDriver);
		SavedPageObj = new SavedPage(appiumDriver);
		mobControlActions = new ControlActions_MobileApp(appiumDriver);
		LoginPage loginPage = new LoginPage(appiumDriver);
		HomePage homePage = new HomePage(appiumDriver);
		boolean login = loginPage.Login(mobileAppUsername, mobileAppPassword);
		TestValidation.IsTrue(login, "logged into the application with User " + mobileAppUsername, "Failed to log in");

		boolean searchForm = homePage.SearchForm(formName);
		TestValidation.IsTrue(searchForm, "Form Searched successfully", "Failed to search Form");

		boolean clickForm = homePage.ClickForm(formName);
		TestValidation.IsTrue(clickForm, "Clicked on form Successfully", "Failed to click Form");

		boolean searchLocation = SavedPageObj.SearchResource(locationInstanceValue1);
		TestValidation.IsTrue(searchLocation, "Location Searched Successfully",
				"Failed to Search Location from Forms subMenu");

		boolean searchResource = SavedPageObj.SearchResource(resourceInstanceValue1);
		TestValidation.IsTrue(searchResource, "Resource Searched Successfully",
				"Failed to Search Resource from Forms subMenu");

		boolean txtNumeric1 = afv.enterFieldValue(numeric1 + " *", numeric1Val);
		TestValidation.IsTrue(txtNumeric1, "Numeric 1 Field value entered successfully", "Failed to enter field value");

		boolean txtNumeric2 = afv.enterFieldValue(numeric2 + " *", numeric2Val);
		TestValidation.IsTrue(txtNumeric2, "Numeric 2 Field value entered successfully", "Failed to enter field value");

		boolean txtPara1 = afv.enterFieldValue(paraTxt1 + " *", paraTxt1Val);
		TestValidation.IsTrue(txtPara1, "Para 1 Field value entered successfully", "Failed to enter field value");

		boolean txtPara2 = afv.enterFieldValue(paraTxt2 + " *", paraTxt2Val);
		TestValidation.IsTrue(txtPara2, "Para 2 Field value entered successfully", "Failed to enter field value");

		boolean txtFree1 = afv.enterFieldValue(freeTxt1 + " *", freeTxt1Val);
		TestValidation.IsTrue(txtFree1, "Free Text 1 Field value entered successfully", "Failed to enter field value");

		boolean txtFree2 = afv.enterFieldValue(freeTxt2 + " *", freeTxt2Val);
		TestValidation.IsTrue(txtFree2, "Free Text 2 Field value entered successfully", "Failed to enter field value");

		// click on submit and repeat
		boolean clickSubmitAndRepeat = afv.clickSubmitAndRepeat();
		TestValidation.IsTrue(clickSubmitAndRepeat, "Successfully Performed Submit and Repeat",
				"Failed to Perform Submit and Repeat");

		// verify values of carry over field
		boolean verifyNum1 = afv.verifyValuesOfField(numeric1, numeric1Val);
		boolean verifyPara1 = afv.verifyValuesOfField(paraTxt1, paraTxt1Val);
		boolean verifyFree1 = afv.verifyValuesOfField(freeTxt1, freeTxt1Val);
		TestValidation.IsTrue(verifyNum1 && verifyPara1 && verifyFree1,
				"Successfully verified the fields with 'carry over' setting have their values repeated after 'Submit And Repat'",
				"Failed to verify that the fields with 'carry over' setting have their values repeated after 'Submit And Repat'");

		// verify values of NOT carry over field
		boolean verifyNum2 = afv.verifyValuesOfField(numeric2, "");
		boolean verifyPara2 = afv.verifyValuesOfField(paraTxt2, "");
		boolean verifyFree2 = afv.verifyValuesOfField(freeTxt2, "");
		TestValidation.IsTrue(verifyNum2 && verifyPara2 && verifyFree2,
				"Successfully verified that the fields NOT with 'carry over' setting does NOT have their values repeated after 'Submit And Repat'",
				"Failed to verify that the fields NOT with 'carry over' setting does NOT have their values repeated after 'Submit And Repat'");

		boolean deleteForm = afv.clickDeleteThenOk();
		TestValidation.IsTrue(deleteForm, "Deleted Current Form Filling", "Failed to Delete Current Form Filling");

		boolean clickSubmissionSub = homePage.ClickSubMenu(homePage.submissionsSubMenu);
		TestValidation.IsTrue(clickSubmissionSub, "Clicked on Save subMenu Successfully",
				"Failed to click Save subMenu");

		boolean searchForm2 = homePage.SearchForm(formName);
		TestValidation.IsTrue(searchForm2, "Latest Created form is present in All forms",
				"Latest Created Form is not present in All forms");

		boolean formStatus = SavedPageObj.checkFormStatusFromSubmissionsSubMenu(formName, "RECEIVED");
		TestValidation.IsTrue(formStatus, "Verified Form Status as 'RECEIVED' Successfully",
				"Failed to Verify Form Status on Submissions");

		appiumDriver.closeApp();

		WebDriver driver1 = launchbrowser();
		controlActions = new ControlActions(driver1);
		controlActions.getUrl(applicationUrl);
		fbp = new FSQABrowserPage(driver1);
		fbRecords = new FSQABrowserRecordsPage(driver1);
		lp = new com.project.safetychain.webapp.pageobjects.LoginPage(driver1);
		hp = lp.performLogin(adminUsername, adminPassword);
		if (hp.error) {
			TCGFailureMsg = "Could NOT login to application";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		} else {
			TestValidation.IsTrue(true, "========== Launched Browser and Logged Into SC WEB Application =========",
					"Failed to login to SC WEB Application");
		}

		boolean selectResourceDropDownandNavigate = fbp.selectResourceDropDownandNavigate(resourceType, fsqaTab);
		TestValidation.IsTrue(selectResourceDropDownandNavigate, "Navigated to FSQA ->  " + fsqaTab,
				"Could NOT Navigate to FSQA ->  " + fsqaTab);

		boolean searchSelectRes = fbp.searchSelect(resourceInstanceValue1);
		TestValidation.IsTrue(searchSelectRes, "Searched and selected instance -> " + resourceInstanceValue1,
				"Could NOT search and select instance ->  " + resourceInstanceValue1);

		boolean isRecordPresent = fbRecords.isRecordPresentInGrid(formName, resourceInstanceValue1);
		TestValidation.IsTrue(isRecordPresent,
				"Verified that the Record is present and Form was submitted Successfully",
				"Failed to verify that Form was submitted Successfully, Record is not present");
		driver1.close();
	}

	@Test(description = "Verify Negative Audit value")
	public void TestCase_37244() throws Exception {

		// RESOURCES AND LOCATIONS
		String locationCategoryValue = CommonMethods.dynamicText("LCat");
		String locationInstanceValue1 = CommonMethods.dynamicText("LInst1");
		String locationInstanceValue2 = CommonMethods.dynamicText("LInst2");
		String resourceCategoryValue = CommonMethods.dynamicText("RCat");
		String resourceInstanceValue1 = CommonMethods.dynamicText("RInst1");
		String resourceInstanceValue2 = CommonMethods.dynamicText("RInst2");

		// TO BE Created Form Details
		String formName = CommonMethods.dynamicText("API_AtdForm");
		String SO = "SelectOne", SOQuesWeight = "-400";
		String SOVal1 = "APPLE", SOVal1Weight = "-200.0";
		String SOVal2 = "MANGO", SOVal2Weight = "-100.0";

		String SM = "SelectMultiple", SMQuesWeight = "-600";
		String SMVal1 = "CASHEW", SMVal1Weight = "-200.0";
		String SMVal2 = "ALMONDS", SMVal2Weight = "-300.0";

		String Section = "Section";

		String scoreAfterSelectingSOOption = "20 %";
		String scoretAfterSelectingSMOption = "30 %";

		String percentBothFieldOptionSelected = "50 %";

		TCG_FormCreation_Wrapper formCreationWrapper = new TCG_FormCreation_Wrapper();

		apiUtils = new ApiUtils();

		// DEFIING RESOURCES AND LOCATIONS
		HashMap<String, List<String>> resourceCatMap = new HashMap<String, List<String>>();
		resourceCatMap.put(resourceCategoryValue, Arrays.asList(resourceInstanceValue1, resourceInstanceValue2));

		HashMap<String, List<String>> LocationMap = new HashMap<String, List<String>>();
		LocationMap.put(locationCategoryValue, Arrays.asList(locationInstanceValue1, locationInstanceValue2));

		// CREATING and LINKING RESOURECES LOCATIONS and USERS
		List<String> userList = Arrays.asList(mobileAppUsername02, mobileAppUsername);
		formCreationWrapper.Resource_Location_CreationWrapper(resourceCatMap, LocationMap, true, userList,
				RESOURCE_TYPES.CUSTOMERS);

		// DEFINEING FIELDS
		FormFieldParams SelectOneField = new FormFieldParams();
		SelectOneField.DPTFieldType = DPT_FIELD_TYPES.SELECT_ONE;
		SelectOneField.Name = SO;
		SelectOneField.QuestionWeight = SOQuesWeight;
		SelectOneField.SelectOptions = Arrays.asList(SOVal1 + "," + SOVal1Weight, SOVal2 + "," + SOVal2Weight);

		FormFieldParams SelectMultipleField = new FormFieldParams();
		SelectMultipleField.DPTFieldType = DPT_FIELD_TYPES.SELECT_MULTIPLE;
		SelectMultipleField.Name = SM;
		SelectMultipleField.QuestionWeight = SMQuesWeight;
		SelectMultipleField.SelectOptions = Arrays.asList(SMVal1 + "," + SMVal1Weight, SMVal2 + "," + SMVal2Weight);

		Element_Types Section1 = new Element_Types();
		Section1.ElementType = DPT_FIELD_TYPES.SECTION;
		Section1.Name = Section;
		Section1.formFieldParams = Arrays.asList(SelectOneField, SelectMultipleField);

		// CREATING FORM
		String formId = apiUtils.getUUID();
		// Form details
		logInfo("FormId = " + formId);
		FormParams fp = new FormParams();
		fp.FormId = formId;
		fp.FormName = formName;
		logInfo("Form Name = " + formName);
		fp.type = DPT_FORM_TYPES.AUDIT;
		fp.ResourceType = RESOURCE_TYPES.CUSTOMERS;
		fp.ResourceCategoryNm = resourceCategoryValue;
		fp.ResourceInstanceNm = resourceInstanceValue1;
		fp.SectionElements = Arrays.asList(Section1);
		fp.CustomerResources = resourceCatMap;
		logInfo("fp.formElements = " + fp.formElements);

		LinkedHashMap<String, String> shortNames = formCreationWrapper.API_Wrapper_CreateUnreleasedForms(fp);

		WebDriver driver1 = launchbrowser();
		try {
			controlActions = new ControlActions(driver1);
			controlActions.getUrl(applicationUrl);
			FormDesignerPage fdp = new FormDesignerPage(driver1);
			lp = new com.project.safetychain.webapp.pageobjects.LoginPage(driver1);
			hp = lp.performLogin(adminUsername, adminPassword);
			if (hp.error) {
				TCGFailureMsg = "Could NOT login to application";
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			} else {
				TestValidation.IsTrue(true, "========== Launched Browser and Logged Into SC WEB Application =========",
						"Failed to login to SC WEB Application");
			}

			boolean editFormDesign = fdp.navigateToReleaseForm_EditForm(formName);
			TestValidation.IsTrue(editFormDesign, "OPENED form - '" + formName + "' in edit mode",
					"Could NOT open form - '" + formName + "' in edit mode");

			boolean noteElement = fdp.addHeaderLevelFields(null, null, null, true);
			TestValidation.IsTrue(noteElement, "Successfully added Summary Table at header level ",
					"Could Not add Summary Table at header level ");

			// SO
			boolean selectFieldSO = fdp.selectPropertiesTabForField(shortNames.get(SO), PROPERTIES_TABS.SETTINGS_TAB);
			TestValidation.IsTrue(selectFieldSO, "Selected SelectOne Field - " + SO,
					"Could Not Select, SelectOne Field - " + SO);

			boolean trunOnPtsPsblSo = fdp.turnSettingOn(FIELD_SETTINGS.SHOW_POINTS_POSSIBLE);
			TestValidation.IsTrue(trunOnPtsPsblSo, "Turned On setting - " + FIELD_SETTINGS.SHOW_POINTS_POSSIBLE,
					"Could NOT Turn on setting - " + FIELD_SETTINGS.SHOW_POINTS_POSSIBLE);

			boolean trunOnPtsEarnedSo = fdp.turnSettingOn(FIELD_SETTINGS.SHOW_POINTS_EARNED);
			TestValidation.IsTrue(trunOnPtsEarnedSo, "Turned On setting - " + FIELD_SETTINGS.SHOW_POINTS_EARNED,
					"Could NOT Turn on setting - " + FIELD_SETTINGS.SHOW_POINTS_EARNED);

			// SM
			boolean selectFieldSM = fdp.selectPropertiesTabForField(shortNames.get(SM), PROPERTIES_TABS.SETTINGS_TAB);
			TestValidation.IsTrue(selectFieldSM, "Selected SelectOne Field - " + SM,
					"Could Not Select, SelectOne Field - " + SM);

			boolean trunOnPtsPsblSM = fdp.turnSettingOn(FIELD_SETTINGS.SHOW_POINTS_POSSIBLE);
			TestValidation.IsTrue(trunOnPtsPsblSM, "Turned On setting - " + FIELD_SETTINGS.SHOW_POINTS_POSSIBLE,
					"Could NOT Turn on setting - " + FIELD_SETTINGS.SHOW_POINTS_POSSIBLE);

			boolean trunOnPtsEarnedSM = fdp.turnSettingOn(FIELD_SETTINGS.SHOW_POINTS_EARNED);
			TestValidation.IsTrue(trunOnPtsEarnedSM, "Turned On setting - " + FIELD_SETTINGS.SHOW_POINTS_EARNED,
					"Could NOT Turn on setting - " + FIELD_SETTINGS.SHOW_POINTS_EARNED);

			boolean saveForm = fdp.clickSaveButton();
			TestValidation.IsTrue(saveForm, "Form Saved Successfully", "Could NOT Save Form");

			boolean clivkNextBtn = fdp.clickOnNextButton(fdp.ReleaseFormPg, "Release Form");
			TestValidation.IsTrue(clivkNextBtn, "Clicked on next button and Navigated to Release Page",
					"Could not click on next button and not Navigate to Release Page");

			boolean verifyFormOnReleasePage = fdp.isformPresentOnReleasePage(formName);
			TestValidation.IsTrue(verifyFormOnReleasePage, "Verified Saved Form " + formName + " on release Page",
					"Could NOT Verify Saved Form - " + formName + " on release Page");

			boolean releaseForm = fdp.releaseForm(formName);
			TestValidation.IsTrue(releaseForm, "Successfully released form -" + formName,
					"Could Not release form -" + formName);

			boolean logOutFromSC = hp.userLogout();
			TestValidation.IsTrue(logOutFromSC, "User successfully LOGGED OUT from Safety Chain application",
					"Failed to Logout user from Safety Chain application");

			TestValidation.IsTrue(true, "========== Logged Out off SC WEB Application =========",
					"Failed to logout off SC WEB Application");

		} finally {
			driver1.close();
		}
		appiumDriver = launchMobileApp();
		afv = new REG_FormsAllValidation(appiumDriver);
		SavedPageObj = new SavedPage(appiumDriver);
		mobControlActions = new ControlActions_MobileApp(appiumDriver);
		LoginPage loginPage = new LoginPage(appiumDriver);
		HomePage homePage = new HomePage(appiumDriver);
		REG_DynamicFlow rdf = new REG_DynamicFlow(appiumDriver);

		boolean login = loginPage.Login(mobileAppUsername, mobileAppPassword);
		TestValidation.IsTrue(login, "logged into the application with User " + mobileAppUsername, "Failed to log in");

		boolean searchForm = homePage.SearchForm(formName);
		TestValidation.IsTrue(searchForm, "Form Searched successfully", "Failed to search Form");

		boolean clickForm = homePage.ClickForm(formName);
		TestValidation.IsTrue(clickForm, "Clicked on form Successfully", "Failed to click Form");

		boolean searchLocation = SavedPageObj.SearchResource(locationInstanceValue1);
		TestValidation.IsTrue(searchLocation, "Location Searched Successfully",
				"Failed to Search Location from Forms subMenu");

		boolean searchResource = SavedPageObj.SearchResource(resourceInstanceValue1);
		TestValidation.IsTrue(searchResource, "Resource Searched Successfully",
				"Failed to Search Resource from Forms subMenu");

		// Select One
		boolean selectOne = rdf.selectOption(SO, SOVal1);
		TestValidation.IsTrue(selectOne, "Value selected for SelectOne Field  - " + SO + " as = " + SOVal1
				+ " with value weight - " + SOVal1Weight, "Failed to select value for field - " + SO);

		String gotEarndPtsSo = afv.getPointsEarnedBelowField(SO);
		TestValidation.IsTrue(gotEarndPtsSo.equals(SOVal1Weight),
				"Verified Negative Points Earned displayed as - " + SOVal1Weight + ",  below the field -" + SO,
				"Failed to verify Negative Points Earned displayed below field  Field - " + SO);

		String gotPsblPtsSo = afv.getPointsPossibleBelowField(SO);
		TestValidation.IsTrue(gotPsblPtsSo.equals(SOQuesWeight),
				"Verified Points Negative Possible displayed as - " + SOQuesWeight + ",  below the field -" + SO,
				"Failed to verify Negative Points Possible displayed below field  Field - " + SO);

		String overAllScore1 = afv.getOverallScoreSummaryTable();
		TestValidation.IsTrue(overAllScore1.equals(scoreAfterSelectingSOOption),
				"Verified Score Percentage Update for 'SelectOne' in Summary Table as - " + scoreAfterSelectingSOOption,
				"Failed to verify Score Percentage for 'SelectOne' in Summary Table");

		boolean unSelectOne = rdf.selectOption(SO, SOVal1);
		TestValidation.IsTrue(unSelectOne, "Unselected Option from Field - " + SO,
				"Failed to Unselect Option From field - " + SO);

		// Select Multiple
		ControlActions_MobileApp.swipe(400, 700, 400, 100);

		boolean selectMul = rdf.selectOption(SM, SMVal2);
		TestValidation.IsTrue(selectMul, "Value selected for 'SelectMultiple' Field  - " + SM + " as = " + SMVal2
				+ " with value weight - " + SMVal2Weight, "Failed to select value for field - " + SM);

		String gotEarndPtsSm = afv.getPointsEarnedBelowField(SM);
		TestValidation.IsTrue(gotEarndPtsSm.equals(SMVal2Weight),
				"Verified Negative Points Earned displayed as - " + SMVal2Weight + ",  below the field -" + SM,
				"Failed to verify Negative Points Earned displayed below field  Field - " + SM);

		String gotPsblPtsSm = afv.getPointsPossibleBelowField(SM);
		TestValidation.IsTrue(gotPsblPtsSm.equals(SMQuesWeight),
				"Verified Negative Points Possible displayed as - " + SOQuesWeight + ",  below the field -" + SM,
				"Failed to verify Negative Points Possible displayed below field  Field - " + SM);

		String overAllScore2 = afv.getOverallScoreSummaryTable();
		TestValidation.IsTrue(overAllScore2.equals(scoretAfterSelectingSMOption),
				"Verified Score Percentage Update for 'SeleceMultiple' field in Summary Table as - "
						+ scoretAfterSelectingSMOption,
				"Failed to verify Score Percentage Update for 'SeleceMultiple' field in Summary Table");

		boolean selectOne2 = rdf.selectOption(SO, SOVal1);
		TestValidation.IsTrue(selectOne2, "Unselected Option from Field - " + SO,
				"Failed to Unselect Option From field - " + SO);

		String overAllScore3 = afv.getOverallScoreSummaryTable();
		TestValidation.IsTrue(overAllScore3.equals(percentBothFieldOptionSelected),
				"Verified Score Percentage Update for in Summary Table when both field options selected as - "
						+ percentBothFieldOptionSelected,
				"Failed to verify Score Percentage in Summary Table both field options selected");

	}

	@Test(description = "Verify Audit summary table")
	public void TestCase_37230() throws Exception {

		// RESOURCES AND LOCATIONS
		String locationCategoryValue = CommonMethods.dynamicText("LCat");
		String locationInstanceValue1 = CommonMethods.dynamicText("LInst1");
		String locationInstanceValue2 = CommonMethods.dynamicText("LInst2");
		String resourceCategoryValue = CommonMethods.dynamicText("RCat");
		String resourceInstanceValue1 = CommonMethods.dynamicText("RInst1");
		String resourceInstanceValue2 = CommonMethods.dynamicText("RInst2");

		// TO BE Created Form Details
		String formName = CommonMethods.dynamicText("API_AtdForm");
		String SO = "SelectOne", SOQuesWeight = "400";
		String SOVal1 = "APPLE", SOVal1Weight = "200.0";
		String SOVal2 = "MANGO", SOVal2Weight = "100.0";

		String SM = "SelectMultiple", SMQuesWeight = "600";
		String SMVal1 = "CASHEW", SMVal1Weight = "200.0";
		String SMVal2 = "ALMONDS", SMVal2Weight = "300.0";

		String Section = "Section";

		String scoreAfterSelectingSOOption = "20 %";
		String scoretAfterSelectingSMOption = "30 %";

		String percentBothFieldOptionSelected = "50 %";

		TCG_FormCreation_Wrapper formCreationWrapper = new TCG_FormCreation_Wrapper();

		apiUtils = new ApiUtils();

		// DEFIING RESOURCES AND LOCATIONS
		HashMap<String, List<String>> resourceCatMap = new HashMap<String, List<String>>();
		resourceCatMap.put(resourceCategoryValue, Arrays.asList(resourceInstanceValue1, resourceInstanceValue2));

		HashMap<String, List<String>> LocationMap = new HashMap<String, List<String>>();
		LocationMap.put(locationCategoryValue, Arrays.asList(locationInstanceValue1, locationInstanceValue2));

		// CREATING and LINKING RESOURECES LOCATIONS and USERS
		List<String> userList = Arrays.asList(mobileAppUsername02, mobileAppUsername);
		formCreationWrapper.Resource_Location_CreationWrapper(resourceCatMap, LocationMap, true, userList,
				RESOURCE_TYPES.CUSTOMERS);

		// DEFINEING FIELDS
		FormFieldParams SelectOneField = new FormFieldParams();
		SelectOneField.DPTFieldType = DPT_FIELD_TYPES.SELECT_ONE;
		SelectOneField.Name = SO;
		SelectOneField.QuestionWeight = SOQuesWeight;
		SelectOneField.SelectOptions = Arrays.asList(SOVal1 + "," + SOVal1Weight, SOVal2 + "," + SOVal2Weight);

		FormFieldParams SelectMultipleField = new FormFieldParams();
		SelectMultipleField.DPTFieldType = DPT_FIELD_TYPES.SELECT_MULTIPLE;
		SelectMultipleField.Name = SM;
		SelectMultipleField.QuestionWeight = SMQuesWeight;
		SelectMultipleField.SelectOptions = Arrays.asList(SMVal1 + "," + SMVal1Weight, SMVal2 + "," + SMVal2Weight);

		Element_Types Section1 = new Element_Types();
		Section1.ElementType = DPT_FIELD_TYPES.SECTION;
		Section1.Name = Section;
		Section1.formFieldParams = Arrays.asList(SelectOneField, SelectMultipleField);

		// CREATING FORM
		String formId = apiUtils.getUUID();
		// Form details
		logInfo("FormId = " + formId);
		FormParams fp = new FormParams();
		fp.FormId = formId;
		fp.FormName = formName;
		logInfo("Form Name = " + formName);
		fp.type = DPT_FORM_TYPES.AUDIT;
		fp.ResourceType = RESOURCE_TYPES.CUSTOMERS;
		fp.ResourceCategoryNm = resourceCategoryValue;
		fp.ResourceInstanceNm = resourceInstanceValue1;
		fp.SectionElements = Arrays.asList(Section1);
		fp.CustomerResources = resourceCatMap;
		logInfo("fp.formElements = " + fp.formElements);

		LinkedHashMap<String, String> shortNames = formCreationWrapper.API_Wrapper_CreateUnreleasedForms(fp);

		WebDriver driver1 = launchbrowser();
		try {
			controlActions = new ControlActions(driver1);
			controlActions.getUrl(applicationUrl);
			FormDesignerPage fdp = new FormDesignerPage(driver1);
			lp = new com.project.safetychain.webapp.pageobjects.LoginPage(driver1);
			hp = lp.performLogin(adminUsername, adminPassword);
			if (hp.error) {
				TCGFailureMsg = "Could NOT login to application";
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			} else {
				TestValidation.IsTrue(true, "========== Launched Browser and Logged Into SC WEB Application =========",
						"Failed to login to SC WEB Application");
			}

			boolean editFormDesign = fdp.navigateToReleaseForm_EditForm(formName);
			TestValidation.IsTrue(editFormDesign, "OPENED form - '" + formName + "' in edit mode",
					"Could NOT open form - '" + formName + "' in edit mode");

			boolean noteElement = fdp.addHeaderLevelFields(null, null, null, true);
			TestValidation.IsTrue(noteElement, "Successfully added Summary Table at header level ",
					"Could Not add Summary Table at header level ");

			// SO
			boolean selectFieldSO = fdp.selectPropertiesTabForField(shortNames.get(SO), PROPERTIES_TABS.SETTINGS_TAB);
			TestValidation.IsTrue(selectFieldSO, "Selected SelectOne Field - " + SO,
					"Could Not Select, SelectOne Field - " + SO);

			boolean trunOnPtsPsblSo = fdp.turnSettingOn(FIELD_SETTINGS.SHOW_POINTS_POSSIBLE);
			TestValidation.IsTrue(trunOnPtsPsblSo, "Turned On setting - " + FIELD_SETTINGS.SHOW_POINTS_POSSIBLE,
					"Could NOT Turn on setting - " + FIELD_SETTINGS.SHOW_POINTS_POSSIBLE);

			boolean trunOnPtsEarnedSo = fdp.turnSettingOn(FIELD_SETTINGS.SHOW_POINTS_EARNED);
			TestValidation.IsTrue(trunOnPtsEarnedSo, "Turned On setting - " + FIELD_SETTINGS.SHOW_POINTS_EARNED,
					"Could NOT Turn on setting - " + FIELD_SETTINGS.SHOW_POINTS_EARNED);

			// SM
			boolean selectFieldSM = fdp.selectPropertiesTabForField(shortNames.get(SM), PROPERTIES_TABS.SETTINGS_TAB);
			TestValidation.IsTrue(selectFieldSM, "Selected SelectOne Field - " + SM,
					"Could Not Select, SelectOne Field - " + SM);

			boolean trunOnPtsPsblSM = fdp.turnSettingOn(FIELD_SETTINGS.SHOW_POINTS_POSSIBLE);
			TestValidation.IsTrue(trunOnPtsPsblSM, "Turned On setting - " + FIELD_SETTINGS.SHOW_POINTS_POSSIBLE,
					"Could NOT Turn on setting - " + FIELD_SETTINGS.SHOW_POINTS_POSSIBLE);

			boolean trunOnPtsEarnedSM = fdp.turnSettingOn(FIELD_SETTINGS.SHOW_POINTS_EARNED);
			TestValidation.IsTrue(trunOnPtsEarnedSM, "Turned On setting - " + FIELD_SETTINGS.SHOW_POINTS_EARNED,
					"Could NOT Turn on setting - " + FIELD_SETTINGS.SHOW_POINTS_EARNED);

			boolean saveForm = fdp.clickSaveButton();
			TestValidation.IsTrue(saveForm, "Form Saved Successfully", "Could NOT Save Form");

			boolean clivkNextBtn = fdp.clickOnNextButton(fdp.ReleaseFormPg, "Release Form");
			TestValidation.IsTrue(clivkNextBtn, "Clicked on next button and Navigated to Release Page",
					"Could not click on next button and not Navigate to Release Page");

			boolean verifyFormOnReleasePage = fdp.isformPresentOnReleasePage(formName);
			TestValidation.IsTrue(verifyFormOnReleasePage, "Verified Saved Form " + formName + " on release Page",
					"Could NOT Verify Saved Form - " + formName + " on release Page");

			boolean releaseForm = fdp.releaseForm(formName);
			TestValidation.IsTrue(releaseForm, "Successfully released form -" + formName,
					"Could Not release form -" + formName);

			boolean logOutFromSC = hp.userLogout();
			TestValidation.IsTrue(logOutFromSC, "User successfully LOGGED OUT from Safety Chain application",
					"Failed to Logout user from Safety Chain application");

			TestValidation.IsTrue(true, "========== Logged Out off SC WEB Application =========",
					"Failed to logout off SC WEB Application");

		} finally {
			driver1.close();
		}
		appiumDriver = launchMobileApp();
		afv = new REG_FormsAllValidation(appiumDriver);
		SavedPageObj = new SavedPage(appiumDriver);
		mobControlActions = new ControlActions_MobileApp(appiumDriver);
		LoginPage loginPage = new LoginPage(appiumDriver);
		HomePage homePage = new HomePage(appiumDriver);
		REG_DynamicFlow rdf = new REG_DynamicFlow(appiumDriver);

		boolean login = loginPage.Login(mobileAppUsername, mobileAppPassword);
		TestValidation.IsTrue(login, "logged into the application with User " + mobileAppUsername, "Failed to log in");

		boolean searchForm = homePage.SearchForm(formName);
		TestValidation.IsTrue(searchForm, "Form Searched successfully", "Failed to search Form");

		boolean clickForm = homePage.ClickForm(formName);
		TestValidation.IsTrue(clickForm, "Clicked on form Successfully", "Failed to click Form");

		boolean searchLocation = SavedPageObj.SearchResource(locationInstanceValue1);
		TestValidation.IsTrue(searchLocation, "Location Searched Successfully",
				"Failed to Search Location from Forms subMenu");

		boolean searchResource = SavedPageObj.SearchResource(resourceInstanceValue1);
		TestValidation.IsTrue(searchResource, "Resource Searched Successfully",
				"Failed to Search Resource from Forms subMenu");

		// Select One
		boolean selectOne = rdf.selectOption(SO, SOVal1);
		TestValidation.IsTrue(selectOne, "Value selected for SelectOne Field  - " + SO + " as = " + SOVal1
				+ " with value weight - " + SOVal1Weight, "Failed to select value for field - " + SO);

		String gotEarndPtsSo = afv.getPointsEarnedBelowField(SO);
		TestValidation.IsTrue(gotEarndPtsSo.equals(SOVal1Weight),
				"Verified Negative Points Earned displayed as - " + SOVal1Weight + ",  below the field -" + SO,
				"Failed to verify Negative Points Earned displayed below field  Field - " + SO);

		String gotPsblPtsSo = afv.getPointsPossibleBelowField(SO);
		TestValidation.IsTrue(gotPsblPtsSo.equals(SOQuesWeight),
				"Verified Points Negative Possible displayed as - " + SOQuesWeight + ",  below the field -" + SO,
				"Failed to verify Negative Points Possible displayed below field  Field - " + SO);

		String overAllScore1 = afv.getOverallScoreSummaryTable();
		TestValidation.IsTrue(overAllScore1.equals(scoreAfterSelectingSOOption),
				"Verified Score Percentage Update for 'SelectOne' in Summary Table as - " + scoreAfterSelectingSOOption,
				"Failed to verify Score Percentage for 'SelectOne' in Summary Table");

		boolean unSelectOne = rdf.selectOption(SO, SOVal1);
		TestValidation.IsTrue(unSelectOne, "Unselected Option from Field - " + SO,
				"Failed to Unselect Option From field - " + SO);

		// Select Multiple
		ControlActions_MobileApp.swipe(400, 700, 400, 100);

		boolean selectMul = rdf.selectOption(SM, SMVal2);
		TestValidation.IsTrue(selectMul, "Value selected for 'SelectMultiple' Field  - " + SM + " as = " + SMVal2
				+ " with value weight - " + SMVal2Weight, "Failed to select value for field - " + SM);

		String gotEarndPtsSm = afv.getPointsEarnedBelowField(SM);
		TestValidation.IsTrue(gotEarndPtsSm.equals(SMVal2Weight),
				"Verified Negative Points Earned displayed as - " + SMVal2Weight + ",  below the field -" + SM,
				"Failed to verify Negative Points Earned displayed below field  Field - " + SM);

		String gotPsblPtsSm = afv.getPointsPossibleBelowField(SM);
		TestValidation.IsTrue(gotPsblPtsSm.equals(SMQuesWeight),
				"Verified Negative Points Possible displayed as - " + SOQuesWeight + ",  below the field -" + SM,
				"Failed to verify Negative Points Possible displayed below field  Field - " + SM);

		String overAllScore2 = afv.getOverallScoreSummaryTable();
		TestValidation.IsTrue(overAllScore2.equals(scoretAfterSelectingSMOption),
				"Verified Score Percentage Update for 'SeleceMultiple' field in Summary Table as - "
						+ scoretAfterSelectingSMOption,
				"Failed to verify Score Percentage Update for 'SeleceMultiple' field in Summary Table");

		boolean selectOne2 = rdf.selectOption(SO, SOVal1);
		TestValidation.IsTrue(selectOne2, "Unselected Option from Field - " + SO,
				"Failed to Unselect Option From field - " + SO);

		String overAllScore3 = afv.getOverallScoreSummaryTable();
		TestValidation.IsTrue(overAllScore3.equals(percentBothFieldOptionSelected),
				"Verified Score Percentage Update for in Summary Table when both field options selected as - "
						+ percentBothFieldOptionSelected,
				"Failed to verify Score Percentage in Summary Table both field options selected");

	}
	
	@Test(description = "39160||SCM - iOS\\Android - Aggregate Advanced Functionality - Saved from Web - Verify Aggregate field evaluation having \"Count Range\" function configured from/on web app")
	public void TestCase_39160() throws Exception {

		// RESOURCES AND LOCATIONS
		String resourceCategoryValue = CommonMethods.dynamicText("RCat");
		String resourceInstanceValue1 = CommonMethods.dynamicText("RInst1");

		// TO BE Created Form Details
		String formName = CommonMethods.dynamicText("API_ChkForm");
		String numeric1 = "Numeric1";
		String numeric2 = "Numeric2";
		String aggCountRange = "CountRange" , cMin = "10", cMax = "15";
		String valInRange = "12", valNotInRangeGreater = "100" , valNotInRangeLesser = "9";
		
		TCG_FormCreation_Wrapper formCreationWrapper = new TCG_FormCreation_Wrapper();

		apiUtils = new ApiUtils();

		// DEFIING RESOURCES AND LOCATIONS
		HashMap<String, List<String>> resourceCatMap = new HashMap<String, List<String>>();
		resourceCatMap.put(resourceCategoryValue, Arrays.asList(resourceInstanceValue1));

		HashMap<String, List<String>> LocationMap = new HashMap<String, List<String>>();
		LocationMap.put(locationName, Arrays.asList(locationName));

		// CREATING and LINKING RESOURECES LOCATIONS and USERS
		List<String> userList = Arrays.asList(mobileAppUsername);
		formCreationWrapper.Resource_Location_CreationWrapper(resourceCatMap, LocationMap, true, userList,RESOURCE_TYPES.CUSTOMERS);

		// DEFINEING FIELDS
		FormFieldParams numericField1 = new FormFieldParams();
		numericField1.DPTFieldType = DPT_FIELD_TYPES.NUMERIC;
		numericField1.Name = numeric1;

		/*
		FormFieldParams numericField2 = new FormFieldParams();
		numericField2.DPTFieldType = DPT_FIELD_TYPES.NUMERIC;
		numericField2.Name = numeric2; */

		FormFieldParams countRange = new FormFieldParams();
		countRange.DPTFieldType = DPT_FIELD_TYPES.AGGREGATE;
		countRange.Name = aggCountRange;
		countRange.SelectedFunction = AGGREGATE_FUNC_TYPES.COUNT_RANGE;
		countRange.AGG_MIN = cMin;
		countRange.AGG_MAX = cMax;
		countRange.SelectedSourceCollection = Arrays.asList(numeric1);
		
		// CREATING FORM
		String formId = apiUtils.getUUID();
		// Form details
		logInfo("FormId = " + formId);
		FormParams fp = new FormParams();
		fp.FormId = formId;
		fp.FormName = formName;
		logInfo("Form Name = " + formName);
		fp.type = DPT_FORM_TYPES.CHECK;
		fp.ResourceType = RESOURCE_TYPES.CUSTOMERS;
		fp.ResourceCategoryNm = resourceCategoryValue;
		fp.ResourceInstanceNm = resourceInstanceValue1;
		fp.formElements = Arrays.asList(numericField1,countRange);
		fp.CustomerResources = resourceCatMap;
		logInfo("fp.formElements = " + fp.formElements);

		formCreationWrapper.API_Wrapper_Forms(fp);
		
		WebDriver driver1 = launchbrowser();
		try {
			controlActions = new ControlActions(driver1);
			controlActions.getUrl(applicationUrl);
			lp = new com.project.safetychain.webapp.pageobjects.LoginPage(driver1);
			hp = lp.performLogin(adminUsername, adminPassword);
			fbp = new FSQABrowserPage(driver1);
			fop = new FormOperations(driver1);
			if (hp.error) {
				TCGFailureMsg = "Could NOT login to application";
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			} else {
				TestValidation.IsTrue(true, "========== Launched Browser and Logged Into SC WEB Application =========",
						"Failed to login to SC WEB Application");
			}

			boolean navigate = fbp.navigateToFSQATab(FSQATAB.FORMS);
			TestValidation.IsTrue(navigate, "Navigated to FSQABrowser>FormsTab",
					"Failed to navigate to FSQABrowser>Forms Tab");

			boolean applyfilter = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, formName);
			TestValidation.IsTrue(applyfilter, "Applied Filter on" + COLUMNHEADER.FORMNAME,
					"Failed to apply filter on" + COLUMNHEADER.FORMNAME);
			threadsleep(2000);
			
			
			//Case 1 - Only one numeric field is filled, a. in range, b. not in range
			
			//fop.clickNumericField1(numeric1);
			fop.enterNumericFieldValue(numeric1, valNotInRangeLesser);
			fop.clickSafetyChainLogo();
			boolean n1verify1 = fop.verifyAggregateFieldValue(aggCountRange, "0");
			TestValidation.IsTrue(n1verify1 ,
								 "Verified : Form Numeric Field 1, value LESS than specified range - Count Range Value = 0",
								 "Failed to verify Count Range Value as 0 for Numeric Field " + numeric1 + " value LESS than specified range");
			
			fop.reEnterNumericFieldValue(numeric1, valNotInRangeGreater);
			fop.clickSafetyChainLogo();
			boolean n1verify2 = fop.verifyAggregateFieldValue(aggCountRange, "0");
			TestValidation.IsTrue(n1verify2 ,
								 "Verified : Form Numeric Field 1, value GREATER than specified range - Count Range Value = 0",
								 "Failed to verify Count Range Value as 0 for Numeric Field " + numeric1 + " value GREATER than specified range");

			
			fop.reEnterNumericFieldValue(numeric1, valInRange);
			fop.clickSafetyChainLogo();
			boolean n1verify3 = fop.verifyAggregateFieldValue(aggCountRange, "1");
			TestValidation.IsTrue(n1verify3 ,
					 			  "Verified : Form Numeric Field 1, value IN THE specified range - Count Range Value = 1",
					 			  "Failed to verify Count Range Value as 1 for Numeric Field " + numeric1 + " value IN THE specified range");
			
			boolean clickSaveBtn = fop.clickSaveButton();
			TestValidation.IsTrue(clickSaveBtn, 
								  "Saved the Form Successfully", 
								  "Failed to Save the form");
			/*
			================================================= TO IMPLEMENT LATER =====================================
			fop.clearNumericInput(numeric1);
			boolean n2fill1 = fop.enterNumericFieldValue(numeric2, valNotInRangeLesser);
			fop.clickSafetyChainLogo();
			boolean n2verify1 = fop.verifyAggregateFieldValue(aggCountRange, "0");
			System.out.println(n2verify1);
			
			boolean n2fill2 = fop.reEnterNumericFieldValue(numeric2, valNotInRangeGreater);
			fop.clickSafetyChainLogo();
			boolean n2verify2 = fop.verifyAggregateFieldValue(aggCountRange, "0");
			System.out.println(n2verify2);
			
			boolean n2fill3 = fop.reEnterNumericFieldValue(numeric2, valInRange);
			fop.clickSafetyChainLogo();
			boolean n3verify3 = fop.verifyAggregateFieldValue(aggCountRange, "1");
			System.out.println(n3verify3);
			
			fop.clearNumericInput(numeric2);
			
			
			//Case 2 - Both Numeric Field filled with in range and out range scenario.
			fop.enterNumericFieldValue(numeric1, valNotInRangeLesser);
			fop.clickSafetyChainLogo();
			fop.enterNumericFieldValue(numeric2, valNotInRangeGreater);
			fop.clickSafetyChainLogo();
			boolean n2verifysda2 = fop.verifyAggregateFieldValue(aggCountRange, "0");
			System.out.println(n2verifysda2);
			
			
			fop.reEnterNumericFieldValue(numeric1, valNotInRangeGreater);
			fop.reEnterNumericFieldValue(numeric2, valNotInRangeLesser);
			fop.clickSafetyChainLogo();
			boolean n2verifysdasda2 = fop.verifyAggregateFieldValue(aggCountRange, "0");
			System.out.println(n2verifysdasda2);
			
			fop.reEnterNumericFieldValue(numeric1, valNotInRangeGreater);
			fop.reEnterNumericFieldValue(numeric2, valNotInRangeGreater);
			fop.clickSafetyChainLogo();
			boolean n2verifysddsaasda2 = fop.verifyAggregateFieldValue(aggCountRange, "0");
			System.out.println(n2verifysddsaasda2);

			fop.reEnterNumericFieldValue(numeric1, valNotInRangeLesser);
			fop.reEnterNumericFieldValue(numeric2, valNotInRangeLesser);
			fop.clickSafetyChainLogo();
			boolean n2verifysddsaasdasa2 = fop.verifyAggregateFieldValue(aggCountRange, "0");
			System.out.println(n2verifysddsaasdasa2);
			
			
			fop.reEnterNumericFieldValue(numeric1, valInRange);
			fop.reEnterNumericFieldValue(numeric2, valInRange);
			fop.clickSafetyChainLogo();
			boolean n2vaerifysddsaasdasa2 = fop.verifyAggregateFieldValue(aggCountRange, "2");
			System.out.println(n2vaerifysddsaasdasa2);
			================================================= TO IMPLEMENT LATER =====================================
			*/
			boolean logOutFromSC = hp.userLogout();
			TestValidation.IsTrue(logOutFromSC, "User successfully LOGGED OUT from Safety Chain application",
					"Failed to Logout user from Safety Chain application");

			TestValidation.IsTrue(true, "========== Logged Out off SC WEB Application =========",
					"Failed to logout off SC WEB Application");

		} finally {
			driver1.close();
		}

		appiumDriver = launchMobileApp();
		afv = new REG_FormsAllValidation(appiumDriver);
		SavedPageObj = new SavedPage(appiumDriver);
		mobControlActions = new ControlActions_MobileApp(appiumDriver);
		LoginPage loginPage = new LoginPage(appiumDriver);
		HomePage homePage = new HomePage(appiumDriver);
		boolean login = loginPage.Login(mobileAppUsername, mobileAppPassword);
		TestValidation.IsTrue(login, "logged into the application with User " + mobileAppUsername, "Failed to log in");
		
		homePage.ClickSubMenu(homePage.saveSubMenu);
		ControlActions_MobileApp.swipeScreen("DOWN");

		// Checking details of saved form
		boolean searchSavedTask = homePage.SearchForm(formName);
		TestValidation.IsTrue(searchSavedTask, 
							 "Saved task is present in Saved Forms",
							 "Saved Task is not present in Saved Forms");
		
		boolean clickSavedForm = homePage.ClickForm(formName);
		logInfo("Clicked on Saved Form = " + formName);
		TestValidation.IsTrue(clickSavedForm, "Clicked on Saved Form", "Failed to click on saved Form");
		

		boolean verifySavedN1 = SavedPageObj.verifyMandatoryInputFieldValue(numeric1, valInRange);
		TestValidation.IsTrue(verifySavedN1,
							  "Form is saved and value is verified for field - " + numeric1,
							  "Failed to verify updated values for field - " + numeric1);
		
		/*boolean verifySavedN2 = SavedPageObj.verifyMandatoryInputFieldValue(numeric2, valInRange);
		TestValidation.IsTrue(verifySavedN2,
							  "Form is saved and value is verified for field - " + numeric2,
							  "Failed to verify updated values for field - " + numeric2);*/
		
		boolean verifySavedCR = SavedPageObj.verifyNotMandatoryInputFieldValue(aggCountRange, "1");
		TestValidation.IsTrue(verifySavedCR,
							  "Form is saved and value is verified for field - " + aggCountRange,
							  "Failed to verify updated values for field - " + aggCountRange);
	}
	
	@Test(description = "SCM - iOS\\Android - Aggregate Advanced Function - Verify Aggregate field evaluation having \"Count Range\" function configured with positive, negative & decimal \"Source\" field values in one form")
	public void TestCase_39841() throws Exception {

		// RESOURCES AND LOCATIONS
		String resourceCategoryValue = CommonMethods.dynamicText("RCat");
		String resourceInstanceValue1 = CommonMethods.dynamicText("RInst1");

		// TO BE Created Form Details
		String formName = CommonMethods.dynamicText("F39841_");
		String numeric1 = "Num";
//		String numeric2 = "NumNeg";
//		String numeric3 = "NumDec";
		String aggCountRange1 = "PositiveCR" , PosMin = "10", PosMax = "15";
		String aggCountRange2 = "NegativeCR" , NegMin = "-15", NegMax = "-10";
		String aggCountRange3 = "DecimalCR" , DecMin = "0.10", DecMax = "0.15";
		
		String valInRangeP = "12", valNotInRangeGreaterP = "100" , valNotInRangeLesserP = "9";
		String valInRangeN = "-12", valNotInRangeGreaterN = "-100" , valNotInRangeLesserN = "-9";
		String valInRangeD = "0.12", valNotInRangeGreaterD = "0.21" , valNotInRangeLesserD = "0.9";
		
		TCG_FormCreation_Wrapper formCreationWrapper = new TCG_FormCreation_Wrapper();

		apiUtils = new ApiUtils();

		// DEFIING RESOURCES AND LOCATIONS
		HashMap<String, List<String>> resourceCatMap = new HashMap<String, List<String>>();
		resourceCatMap.put(resourceCategoryValue, Arrays.asList(resourceInstanceValue1));

		HashMap<String, List<String>> LocationMap = new HashMap<String, List<String>>();
		LocationMap.put(locationName, Arrays.asList(locationName));

		// CREATING and LINKING RESOURECES LOCATIONS and USERS
		List<String> userList = Arrays.asList(mobileAppUsername);
		formCreationWrapper.Resource_Location_CreationWrapper(resourceCatMap, LocationMap, true, userList,RESOURCE_TYPES.CUSTOMERS);

		// DEFINEING FIELDS
		FormFieldParams numericField1 = new FormFieldParams();
		numericField1.DPTFieldType = DPT_FIELD_TYPES.NUMERIC;
		numericField1.Name = numeric1;

		/*
		FormFieldParams numericField2 = new FormFieldParams();
		numericField2.DPTFieldType = DPT_FIELD_TYPES.NUMERIC;
		numericField2.Name = numeric2; */

		// POSITIVE
		FormFieldParams countRange1 = new FormFieldParams();
		countRange1.DPTFieldType = DPT_FIELD_TYPES.AGGREGATE;
		countRange1.Name = aggCountRange1;
		countRange1.SelectedFunction = AGGREGATE_FUNC_TYPES.COUNT_RANGE;
		countRange1.AGG_MIN = PosMin;
		countRange1.AGG_MAX = PosMax;
		countRange1.SelectedSourceCollection = Arrays.asList(numeric1);
		
		// NEGATIVE
		FormFieldParams countRange2 = new FormFieldParams();
		countRange2.DPTFieldType = DPT_FIELD_TYPES.AGGREGATE;
		countRange2.Name = aggCountRange2;
		countRange2.SelectedFunction = AGGREGATE_FUNC_TYPES.COUNT_RANGE;
		countRange2.AGG_MIN = NegMin;
		countRange2.AGG_MAX = NegMax;
		countRange2.SelectedSourceCollection = Arrays.asList(numeric1);
		
		// DECIMAL
		FormFieldParams countRange3 = new FormFieldParams();
		countRange3.DPTFieldType = DPT_FIELD_TYPES.AGGREGATE;
		countRange3.Name = aggCountRange3;
		countRange3.SelectedFunction = AGGREGATE_FUNC_TYPES.COUNT_RANGE;
		countRange3.AGG_MIN = DecMin;
		countRange3.AGG_MAX = DecMax;
		countRange3.SelectedSourceCollection = Arrays.asList(numeric1);
		
		// CREATING FORM
		String formId = apiUtils.getUUID();
		// Form details
		logInfo("FormId = " + formId);
		FormParams fp = new FormParams();
		fp.FormId = formId;
		fp.FormName = formName;
		logInfo("Form Name = " + formName);
		fp.type = DPT_FORM_TYPES.CHECK;
		fp.ResourceType = RESOURCE_TYPES.CUSTOMERS;
		fp.ResourceCategoryNm = resourceCategoryValue;
		fp.ResourceInstanceNm = resourceInstanceValue1;
		fp.formElements = Arrays.asList(numericField1,countRange1, countRange2, countRange3);
		fp.CustomerResources = resourceCatMap;
		logInfo("fp.formElements = " + fp.formElements);

		formCreationWrapper.API_Wrapper_Forms(fp);
		
		appiumDriver = launchMobileApp();
		afv = new REG_FormsAllValidation(appiumDriver);
		SavedPageObj = new SavedPage(appiumDriver);
		mobControlActions = new ControlActions_MobileApp(appiumDriver);
		LoginPage loginPage = new LoginPage(appiumDriver);
		HomePage homePage = new HomePage(appiumDriver);
		boolean login = loginPage.Login(mobileAppUsername, mobileAppPassword);
		TestValidation.IsTrue(login, "logged into the application with User " + mobileAppUsername, "Failed to log in");
		
		// Checking details of saved form
		boolean searchForm = homePage.SearchForm(formName);
		TestValidation.IsTrue(searchForm, 
							 "Form is present - " + formName,
							 "Form is NOT present - " + formName);
		
		boolean clickForm = homePage.ClickForm(formName);
		logInfo("Clicked on  Form = " + formName);
		TestValidation.IsTrue(clickForm, "Clicked on Form", "Failed to click on Form");
		

		// FOR POSITIVE COUNT RANGE
		boolean txtNumericP = afv.enterFieldValue(numeric1 + " *", valInRangeP);
		TestValidation.IsTrue(txtNumericP, "Num Field value entered successfully - " + valInRangeP, "Failed to enter field value");
		
		boolean verifyCRP1 = SavedPageObj.verifyNotMandatoryInputFieldValue(aggCountRange1, "1");
		TestValidation.IsTrue(verifyCRP1,
							  "Value is verified for positive count range agg field - " + aggCountRange1 + " as 1,  when value enterd is in defined positive range",
							  "Failed to verify value for positive count range - " + aggCountRange1);
		
		boolean verifyCRN1 = SavedPageObj.verifyNotMandatoryInputFieldValue(aggCountRange2, "0");
		boolean verifyCRD1 = SavedPageObj.verifyNotMandatoryInputFieldValue(aggCountRange3, "0");
		TestValidation.IsTrue(verifyCRD1 && verifyCRN1,
							 "Value is verified for negative/decimal count range agg field - as 0,  when value enterd is in defined positive range",
						     "Failed to verify value for negative/decimal count range fields - as 0,  when value enterd is in defined positive range");

		// FOR NNEGATIVE COUNT RANGE
		boolean txtNumeriN = afv.enterFieldValue(numeric1 + " *", valInRangeN);
		TestValidation.IsTrue(txtNumeriN, "Num Field value entered successfully - " + valInRangeN, "Failed to enter field value");

		boolean verifyCRN2 = SavedPageObj.verifyNotMandatoryInputFieldValue(aggCountRange2, "1");
		TestValidation.IsTrue(verifyCRN2,
				"Value is verified for Negative count range agg field - " + aggCountRange2 + " as 1,  when value enterd is in defined negative range",
				"Failed to verify value for negative count range - " + aggCountRange2);

		boolean verifyCRP2 = SavedPageObj.verifyNotMandatoryInputFieldValue(aggCountRange1, "0");
		boolean verifyCRD2 = SavedPageObj.verifyNotMandatoryInputFieldValue(aggCountRange3, "0");
		TestValidation.IsTrue(verifyCRP2 && verifyCRD2,
				"Value is verified for positive/decimal count range agg field - as 0,  when value enterd is in defined negative range",
				"Failed to verify value for positive/decimal count range fields - as 0,  when value enterd is in defined negative range");
		
		// FOR NNEGATIVE COUNT RANGE
		boolean txtNumeriD = afv.enterFieldValue(numeric1 + " *", valInRangeD);
		TestValidation.IsTrue(txtNumeriD, "Num Field value entered successfully - " + valInRangeD, "Failed to enter field value");

		boolean verifyCRD3 = SavedPageObj.verifyNotMandatoryInputFieldValue(aggCountRange3, "1");
		TestValidation.IsTrue(verifyCRD3,
				"Value is verified for Decimal count range agg field - " + aggCountRange3 + " as 1,  when value enterd is in defined decimal range",
				"Failed to verify value for Decimal count range - " + aggCountRange3);

		boolean verifyCRP3 = SavedPageObj.verifyNotMandatoryInputFieldValue(aggCountRange1, "0");
		boolean verifyCRN3 = SavedPageObj.verifyNotMandatoryInputFieldValue(aggCountRange2, "0");
		TestValidation.IsTrue(verifyCRP3 && verifyCRN3,
				"Value is verified for positive/negative count range agg field - as 0,  when value enterd is in defined negative range",
				"Failed to verify value for positive/negative count range fields - as 0,  when value enterd is in defined negative range");	
	}
	
	@Test(description = "SCM -iOS\\ Android - Advanced Aggregate Functionality - Verify aggregate count range and other Aggregate functions in same form")
	public void TestCase_39200() throws Exception {

		// RESOURCES AND LOCATIONS
		String resourceCategoryValue = CommonMethods.dynamicText("RCat");
		String resourceInstanceValue1 = CommonMethods.dynamicText("RInst1");

		// TO BE Created Form Details
		String formName = CommonMethods.dynamicText("F39200_");
		String numeric1 = "Num1";
		String numeric2 = "Num2";
		String numeric3 = "Num3";
		String aggCountRangeFieldName = "PositiveCR" , PosMin = "10", PosMax = "15";
		String aggMinFieldName = "MinValue";
		String aggMaxFieldName = "MaxValue";
		String aggRangeFieldName  = "Range";

		String valInRangeP = "12", valMin = "1" , valMax = "23";
		String CRValue = "2", RangeValue = "22";

		TCG_FormCreation_Wrapper formCreationWrapper = new TCG_FormCreation_Wrapper();

		apiUtils = new ApiUtils();

		// DEFIING RESOURCES AND LOCATIONS
		HashMap<String, List<String>> resourceCatMap = new HashMap<String, List<String>>();
		resourceCatMap.put(resourceCategoryValue, Arrays.asList(resourceInstanceValue1));

		HashMap<String, List<String>> LocationMap = new HashMap<String, List<String>>();
		LocationMap.put(locationName, Arrays.asList(locationName));

		// CREATING and LINKING RESOURECES LOCATIONS and USERS
		List<String> userList = Arrays.asList(mobileAppUsername);
		formCreationWrapper.Resource_Location_CreationWrapper(resourceCatMap, LocationMap, true, userList,RESOURCE_TYPES.CUSTOMERS);

		// DEFINEING FIELDS
		FormFieldParams numericField1 = new FormFieldParams();
		numericField1.DPTFieldType = DPT_FIELD_TYPES.NUMERIC;
		numericField1.Name = numeric1;

		FormFieldParams numericField2 = new FormFieldParams();
		numericField2.DPTFieldType = DPT_FIELD_TYPES.NUMERIC;
		numericField2.Name = numeric2; 

		FormFieldParams numericField3 = new FormFieldParams();
		numericField3.DPTFieldType = DPT_FIELD_TYPES.NUMERIC;
		numericField3.Name = numeric3;
		numericField3.Repeat = "2";


		// POSITIVE
		FormFieldParams countRange1 = new FormFieldParams();
		countRange1.DPTFieldType = DPT_FIELD_TYPES.AGGREGATE;
		countRange1.Name = aggCountRangeFieldName;
		countRange1.SelectedFunction = AGGREGATE_FUNC_TYPES.COUNT_RANGE;
		countRange1.AGG_MIN = PosMin;
		countRange1.AGG_MAX = PosMax;
		countRange1.SelectedSourceCollection = Arrays.asList(numeric1,numeric2);

		FormFieldParams minVal = new FormFieldParams();
		minVal.DPTFieldType = DPT_FIELD_TYPES.AGGREGATE;
		minVal.Name = aggMinFieldName;
		minVal.SelectedFunction = AGGREGATE_FUNC_TYPES.MIN_VAL;
		minVal.SelectedSource = numeric3;

		FormFieldParams maxVal = new FormFieldParams();
		maxVal.DPTFieldType = DPT_FIELD_TYPES.AGGREGATE;
		maxVal.Name = aggMaxFieldName;
		maxVal.SelectedFunction = AGGREGATE_FUNC_TYPES.MAX_VAL;
		maxVal.SelectedSource = numeric3;

		FormFieldParams range = new FormFieldParams();
		range.DPTFieldType = DPT_FIELD_TYPES.AGGREGATE;
		range.Name = aggRangeFieldName;
		range.SelectedFunction = AGGREGATE_FUNC_TYPES.RANGE;
		range.SelectedSource = numeric3;

		// CREATING FORM
		String formId = apiUtils.getUUID();
		// Form details
		logInfo("FormId = " + formId);
		FormParams fp = new FormParams();
		fp.FormId = formId;
		fp.FormName = formName;
		logInfo("Form Name = " + formName);
		fp.type = DPT_FORM_TYPES.CHECK;
		fp.ResourceType = RESOURCE_TYPES.CUSTOMERS;
		fp.ResourceCategoryNm = resourceCategoryValue;
		fp.ResourceInstanceNm = resourceInstanceValue1;
		fp.formElements = Arrays.asList(numericField1,numericField2,numericField3,countRange1,minVal,maxVal,range);
		fp.CustomerResources = resourceCatMap;
		logInfo("fp.formElements = " + fp.formElements);

		formCreationWrapper.API_Wrapper_Forms(fp);

		appiumDriver = launchMobileApp();
		afv = new REG_FormsAllValidation(appiumDriver);
		SavedPageObj = new SavedPage(appiumDriver);
		mobControlActions = new ControlActions_MobileApp(appiumDriver);
		LoginPage loginPage = new LoginPage(appiumDriver);
		HomePage homePage = new HomePage(appiumDriver);
		boolean login = loginPage.Login(mobileAppUsername, mobileAppPassword);
		TestValidation.IsTrue(login, "logged into the application with User " + mobileAppUsername, "Failed to log in");

		// Checking details of saved form
		boolean searchForm = homePage.SearchForm(formName);
		TestValidation.IsTrue(searchForm, 
				"Form is present - " + formName,
				"Form is NOT present - " + formName);

		boolean clickForm = homePage.ClickForm(formName);
		logInfo("Clicked on  Form = " + formName);
		TestValidation.IsTrue(clickForm, "Clicked on Form", "Failed to click on Form");

		boolean enterNum1Value = afv.enterFieldValue(numeric1 + " *", valInRangeP);
		TestValidation.IsTrue(enterNum1Value, "Num1 Field value entered successfully - " + valInRangeP, "Failed to enter field value");

		boolean enterNum2Value = afv.enterFieldValue(numeric2 + " *", valInRangeP);
		TestValidation.IsTrue(enterNum2Value, "Num2 Field value entered successfully - " + valInRangeP, "Failed to enter field value");

		boolean enterNum3Value1 = afv.enterValue(numeric3 + " *", valMin, afv.Field1);
		TestValidation.IsTrue(enterNum3Value1, "1st Num3 Field value entered successfully - " + valMin, "Failed to enter field value");
		
		boolean enterNum3Value2 = afv.enterValue(numeric3 + " *", valMax, afv.Field2);
		TestValidation.IsTrue(enterNum3Value2, "2nd Num3 Field value entered successfully - " + valMax, "Failed to enter field value");
		
		ControlActions_MobileApp.ScrollToEnd("");
		//ControlActions_MobileApp.ScrollIntoView(aggRangeFieldName);
	
		boolean verifyCR = SavedPageObj.verifyNotMandatoryInputFieldValue(aggCountRangeFieldName,CRValue);
		TestValidation.IsTrue(verifyCR,
							 "Value is verified for positive count range agg field - " + aggCountRangeFieldName + " as 2,  when value enterd is in defined positive range",
							 "Failed to verify value for positive count range - " + aggCountRangeFieldName);
		
		boolean verifyMin = SavedPageObj.verifyNotMandatoryInputFieldValue(aggMinFieldName,valMin);
		TestValidation.IsTrue(verifyMin,
							 "Value is verified for Min Aggregate field - " + aggMinFieldName + " as - " + valMin,
							 "Failed to verify value for Min Aggregate field - " + aggMinFieldName);
		
		boolean verifyMax = SavedPageObj.verifyNotMandatoryInputFieldValue(aggMaxFieldName,valMax);
		TestValidation.IsTrue(verifyMax,
							 "Value is verified for Max Aggregate field - " + aggMaxFieldName + " as - " + valMax,
							 "Failed to verify value for Min Aggregate field - " + aggMaxFieldName);
		
		boolean verifyRange = SavedPageObj.verifyNotMandatoryInputFieldValue(aggRangeFieldName,RangeValue);
		TestValidation.IsTrue(verifyRange,
							 "Value is verified for Range Aggregate field - " + aggRangeFieldName + " as - " + RangeValue,
							 "Failed to verify value for Min Aggregate field - " + aggRangeFieldName);
	}

	@Test(description = "SCM -iOS\\ Android - Advanced Aggregate Functionality - Verify aggregate count range and other Aggregate functions in same form")
	public void TestCase_39200_2() throws Exception {

		// RESOURCES AND LOCATIONS
		String resourceCategoryValue = CommonMethods.dynamicText("RCat");
		String resourceInstanceValue1 = CommonMethods.dynamicText("RInst1");

		// TO BE Created Form Details
		String formName = CommonMethods.dynamicText("F39200_2_");
		String numeric1 = "Num1";
		String numeric2 = "Num2";
		String numeric3 = "Num3";
		String aggCountRangeFieldName = "PositiveCR" , PosMin = "10", PosMax = "15";
		String aggSumFieldName = "Sum";
		String aggAverageFieldName = "Average";
		String aggMedianFieldName  = "Median";

		String valInRangeP = "12", val1 = "10" , val2 = "20";
		String CRValue = "2", averageValue = "15", medianValue = "15", sumValue = "30";

		TCG_FormCreation_Wrapper formCreationWrapper = new TCG_FormCreation_Wrapper();

		apiUtils = new ApiUtils();

		// DEFIING RESOURCES AND LOCATIONS
		HashMap<String, List<String>> resourceCatMap = new HashMap<String, List<String>>();
		resourceCatMap.put(resourceCategoryValue, Arrays.asList(resourceInstanceValue1));

		HashMap<String, List<String>> LocationMap = new HashMap<String, List<String>>();
		LocationMap.put(locationName, Arrays.asList(locationName));

		// CREATING and LINKING RESOURECES LOCATIONS and USERS
		List<String> userList = Arrays.asList(mobileAppUsername);
		formCreationWrapper.Resource_Location_CreationWrapper(resourceCatMap, LocationMap, true, userList,RESOURCE_TYPES.CUSTOMERS);

		// DEFINEING FIELDS
		FormFieldParams numericField1 = new FormFieldParams();
		numericField1.DPTFieldType = DPT_FIELD_TYPES.NUMERIC;
		numericField1.Name = numeric1;

		FormFieldParams numericField2 = new FormFieldParams();
		numericField2.DPTFieldType = DPT_FIELD_TYPES.NUMERIC;
		numericField2.Name = numeric2; 

		FormFieldParams numericField3 = new FormFieldParams();
		numericField3.DPTFieldType = DPT_FIELD_TYPES.NUMERIC;
		numericField3.Name = numeric3;
		numericField3.Repeat = "2";


		// POSITIVE
		FormFieldParams countRange1 = new FormFieldParams();
		countRange1.DPTFieldType = DPT_FIELD_TYPES.AGGREGATE;
		countRange1.Name = aggCountRangeFieldName;
		countRange1.SelectedFunction = AGGREGATE_FUNC_TYPES.COUNT_RANGE;
		countRange1.AGG_MIN = PosMin;
		countRange1.AGG_MAX = PosMax;
		countRange1.SelectedSourceCollection = Arrays.asList(numeric1,numeric2);

		FormFieldParams aggSumFiled = new FormFieldParams();
		aggSumFiled.DPTFieldType = DPT_FIELD_TYPES.AGGREGATE;
		aggSumFiled.Name = aggSumFieldName;
		aggSumFiled.SelectedFunction = AGGREGATE_FUNC_TYPES.MIN_VAL;
		aggSumFiled.SelectedSource = numeric3;

		FormFieldParams aggAverageField = new FormFieldParams();
		aggAverageField.DPTFieldType = DPT_FIELD_TYPES.AGGREGATE;
		aggAverageField.Name = aggAverageFieldName;
		aggAverageField.SelectedFunction = AGGREGATE_FUNC_TYPES.MAX_VAL;
		aggAverageField.SelectedSource = numeric3;

		FormFieldParams aggMedianField = new FormFieldParams();
		aggMedianField.DPTFieldType = DPT_FIELD_TYPES.AGGREGATE;
		aggMedianField.Name = aggMedianFieldName;
		aggMedianField.SelectedFunction = AGGREGATE_FUNC_TYPES.RANGE;
		aggMedianField.SelectedSource = numeric3;

		// CREATING FORM
		String formId = apiUtils.getUUID();
		// Form details
		logInfo("FormId = " + formId);
		FormParams fp = new FormParams();
		fp.FormId = formId;
		fp.FormName = formName;
		logInfo("Form Name = " + formName);
		fp.type = DPT_FORM_TYPES.CHECK;
		fp.ResourceType = RESOURCE_TYPES.CUSTOMERS;
		fp.ResourceCategoryNm = resourceCategoryValue;
		fp.ResourceInstanceNm = resourceInstanceValue1;
		fp.formElements = Arrays.asList(numericField1,numericField2,numericField3,countRange1,aggSumFiled,aggAverageField,aggMedianField);
		fp.CustomerResources = resourceCatMap;
		logInfo("fp.formElements = " + fp.formElements);

		formCreationWrapper.API_Wrapper_Forms(fp);

		appiumDriver = launchMobileApp();
		afv = new REG_FormsAllValidation(appiumDriver);
		SavedPageObj = new SavedPage(appiumDriver);
		mobControlActions = new ControlActions_MobileApp(appiumDriver);
		LoginPage loginPage = new LoginPage(appiumDriver);
		HomePage homePage = new HomePage(appiumDriver);
		boolean login = loginPage.Login(mobileAppUsername, mobileAppPassword);
		TestValidation.IsTrue(login, "logged into the application with User " + mobileAppUsername, "Failed to log in");

		// Checking details of saved form
		boolean searchForm = homePage.SearchForm(formName);
		TestValidation.IsTrue(searchForm, 
				"Form is present - " + formName,
				"Form is NOT present - " + formName);

		boolean clickForm = homePage.ClickForm(formName);
		logInfo("Clicked on  Form = " + formName);
		TestValidation.IsTrue(clickForm, "Clicked on Form", "Failed to click on Form");

		boolean enterNum1Value = afv.enterFieldValue(numeric1 + " *", valInRangeP);
		TestValidation.IsTrue(enterNum1Value, "Num1 Field value entered successfully - " + valInRangeP, "Failed to enter field value");

		boolean enterNum2Value = afv.enterFieldValue(numeric2 + " *", valInRangeP);
		TestValidation.IsTrue(enterNum2Value, "Num2 Field value entered successfully - " + valInRangeP, "Failed to enter field value");

		boolean enterNum3Value1 = afv.enterValue(numeric3 + " *", val1, afv.Field1);
		TestValidation.IsTrue(enterNum3Value1, "1st Num3 Field value entered successfully - " + val1, "Failed to enter field value");
		
		boolean enterNum3Value2 = afv.enterValue(numeric3 + " *", val2, afv.Field2);
		TestValidation.IsTrue(enterNum3Value2, "2nd Num3 Field value entered successfully - " + val2, "Failed to enter field value");
		
		ControlActions_MobileApp.ScrollToEnd("");
		//ControlActions_MobileApp.ScrollIntoView(aggRangeFieldName);
	
		boolean verifyCR = SavedPageObj.verifyNotMandatoryInputFieldValue(aggCountRangeFieldName,CRValue);
		TestValidation.IsTrue(verifyCR,
							 "Value is verified for positive count range agg field - " + aggCountRangeFieldName + " as 2,  when value enterd is in defined positive range",
							 "Failed to verify value for positive count range - " + aggCountRangeFieldName);
		
		boolean verifysum = SavedPageObj.verifyNotMandatoryInputFieldValue(aggSumFieldName,sumValue);
		TestValidation.IsTrue(verifysum,
							 "Value is verified for Sum Aggregate field - " + aggSumFieldName + " as - " + sumValue,
							 "Failed to verify value for Min Aggregate field - " + aggSumFieldName);
		
		boolean verifyAverage = SavedPageObj.verifyNotMandatoryInputFieldValue(aggAverageFieldName,averageValue);
		TestValidation.IsTrue(verifyAverage,
							 "Value is verified for Average Aggregate field - " + aggAverageFieldName + " as - " + averageValue,
							 "Failed to verify value for Average Aggregate field - " + aggAverageFieldName);
		
		boolean verifyMedian = SavedPageObj.verifyNotMandatoryInputFieldValue(aggMedianFieldName,medianValue);
		TestValidation.IsTrue(verifyMedian,
							 "Value is verified for Median Aggregate field - " + aggMedianFieldName + " as - " + medianValue,
							 "Failed to verify value for Median Aggregate field - " + aggMedianFieldName);
	}

	
	@AfterMethod(alwaysRun = true)
	public void closeAppium() throws InterruptedException {

		appiumDriver.closeApp();

	}

}
