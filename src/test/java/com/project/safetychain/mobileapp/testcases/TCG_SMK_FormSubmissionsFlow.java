package com.project.safetychain.mobileapp.testcases;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
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
import com.project.safetychain.api.models.support.Support.RESOURCE_TYPES;
import com.project.safetychain.api.models.support.TCG_FormCreation_Wrapper;
import com.project.safetychain.mobileapp.pageobjects.HomePage;
import com.project.safetychain.mobileapp.pageobjects.LoginPage;
import com.project.safetychain.mobileapp.pageobjects.SavedPage;
import com.project.safetychain.mobileapp.pageobjects.UpdatedFormVersionPage;
import com.project.safetychain.webapp.pageobjects.CommonPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserFormsPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.COLUMNHEADER;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.COLUMNSETTING;
import com.project.safetychain.webapp.pageobjects.FSQABrowserRecordsPage;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage;
import com.project.safetychain.webapp.pageobjects.LinkPage;
import com.project.safetychain.webapp.pageobjects.LinkRecordsPage;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FormDesignParams;
import com.project.safetychain.webapp.pageobjects.LinkRecordsPage.RecordFieldNames;
import com.project.safetychain.webapp.pageobjects.ManageLocationPage;
import com.project.safetychain.webapp.pageobjects.ManageResourcePage;
import com.project.safetychain.webapp.pageobjects.ResourceDesignerPage;
import com.project.safetychain.webapp.testcases.TCG_SMK_RecordVault_VerifySync;
import com.project.safetychain.webapp.testcases.support.FormOperations;
import com.project.safetychain.webapp.testcases.support.FormOperations.FormDetails;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.utilities.ApiUtils;
import com.project.utilities.CommonMethods;
import com.project.utilities.ControlActions;
import com.project.utilities.ControlActions_MobileApp;

import io.appium.java_client.pagefactory.AndroidFindBy;

public class TCG_SMK_FormSubmissionsFlow extends TestBase {
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
	public static String resourceCatTest;
	public static String resourceInstTest1;
	public static String resourceInstTest2;

	public static String formName = "Automation_CheckForm_15.10_17.19.19";
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
			FormName = CommonMethods.dynamicText("Automation_CheckForm");
			driver = launchbrowser();

			formDesignerPage = new FormDesignerPage(driver);
			locations = new ManageLocationPage(driver);
			manageResource = new ManageResourcePage(driver);
			fbp = new FSQABrowserPage(driver);
			fbForms = new FSQABrowserFormsPage(driver);
			resourceDesigner = new ResourceDesignerPage(driver);
			mainPage = new CommonPage(driver);
			fop = new FormOperations(driver);

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

			// Field Short Names
			chkFreeTxt = "FreeText";
			ParaTxt = "Paragraph";
			chkSelectOne = "SelectOne";
			Numeric = "Numeric";
			chkDate = "Date";
			chkTime = "Time";
			chkDateTime = "Date&Time";
			SelectMultiple = "MutiSelect";

			TCG_FormCreation_Wrapper formCreationWrapper = new TCG_FormCreation_Wrapper();

			apiUtils = new ApiUtils();

			locationCategoryValue = CommonMethods.dynamicText("LCat");
			locationInstanceValue1 = CommonMethods.dynamicText("LInst1");
			locationInstanceValue2 = CommonMethods.dynamicText("LInst2");
			resourceCategoryValue = CommonMethods.dynamicText("RCat");
			resourceInstanceValue1 = CommonMethods.dynamicText("RInst1");
			resourceInstanceValue2 = CommonMethods.dynamicText("RInst2");

			resourceCatTest = CommonMethods.dynamicText("R");
			resourceInstTest1 = CommonMethods.dynamicText("RIn1");
			resourceInstTest2 = CommonMethods.dynamicText("RIn2");

			// FieldGroup

			Element_Types Section1 = new Element_Types();
			Section1.ElementType = DPT_FIELD_TYPES.SECTION;
			Section1.Name = "Section1";

			Element_Types fieldGroup1 = new Element_Types();
			fieldGroup1.ElementType = DPT_FIELD_TYPES.FIELD_GROUP;
			fieldGroup1.Name = "FieldGroup1";

			FormFieldParams paraTextField1 = new FormFieldParams();
			paraTextField1.DPTFieldType = DPT_FIELD_TYPES.PARAGRAPH_TEXT;
			paraTextField1.Name = "FGParaTxt";

			fieldGroup1.formFieldParams = Arrays.asList(paraTextField1);

			Section1.FieldGroupParams = Arrays.asList(fieldGroup1);

			// API Implementation

			FormFieldParams numericField = new FormFieldParams();
			numericField.DPTFieldType = DPT_FIELD_TYPES.NUMERIC;
			numericField.Name = Numeric;

			FormFieldParams paraTextField = new FormFieldParams();
			paraTextField.DPTFieldType = DPT_FIELD_TYPES.PARAGRAPH_TEXT;
			paraTextField.Name = ParaTxt;

			FormFieldParams freeTextField = new FormFieldParams();
			freeTextField.DPTFieldType = DPT_FIELD_TYPES.FREE_TEXT;
			freeTextField.Name = chkFreeTxt;

			FormFieldParams selectOneField = new FormFieldParams();
			selectOneField.DPTFieldType = DPT_FIELD_TYPES.SELECT_ONE;
			selectOneField.Name = chkSelectOne;
			selectOneField.SelectOptions = Arrays.asList("GOOD", "BAD");

			FormFieldParams dateField = new FormFieldParams();
			dateField.DPTFieldType = DPT_FIELD_TYPES.DATE;
			dateField.Name = chkDate;

			FormFieldParams dateTimeField = new FormFieldParams();
			dateTimeField.DPTFieldType = DPT_FIELD_TYPES.DATE_TIME;
			dateTimeField.Name = chkDateTime;

			FormFieldParams selectMultipleField = new FormFieldParams();
			selectMultipleField.DPTFieldType = DPT_FIELD_TYPES.SELECT_MULTIPLE;
			selectMultipleField.Name = SelectMultiple;
			selectMultipleField.SelectOptions = Arrays.asList("10", "20", "30");

			String formId = apiUtils.getUUID();
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
			fp.formElements = Arrays.asList(numericField, paraTextField, freeTextField, selectOneField, dateField,
					dateTimeField, selectMultipleField);
			logInfo("fp.formElements = " + fp.formElements);

			HashMap<String, List<String>> resourceCatMap = new HashMap<String, List<String>>();
			resourceCatMap.put(resourceCategoryValue, Arrays.asList(resourceInstanceValue1, resourceInstanceValue2));
			fp.CustomerResources = resourceCatMap;

			HashMap<String, List<String>> resourceCatMap1 = new HashMap<String, List<String>>();
			resourceCatMap1.put(resourceCatTest, Arrays.asList(resourceInstTest1, resourceInstTest2));
			HashMap<String, List<String>> LocationMap = new HashMap<String, List<String>>();
			LocationMap.put(locationCategoryValue, Arrays.asList(locationInstanceValue1, locationInstanceValue2));
			List<String> userList = Arrays.asList(mobileAppUsername02, mobileAppUsername);

			formCreationWrapper.Resource_Location_CreationWrapper(resourceCatMap, LocationMap, true, userList,
					fp.ResourceType);

			formCreationWrapper.Resource_Location_CreationWrapper(resourceCatMap1, LocationMap, true, userList,
					fp.ResourceType);

			formCreationWrapper.API_Wrapper_Forms(fp);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test(description = "Form Submission")
	public void TestCase_36790() throws Exception {
		appiumDriver = launchMobileApp();
		mobControlActions = new ControlActions_MobileApp(appiumDriver);
		SavedPage SavedPageObj = new SavedPage(appiumDriver);
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

		Boolean txtNumeric = SavedPageObj.enterFieldValue(SavedPageObj.NumericTxt, "8");
		TestValidation.IsTrue(txtNumeric, "Numeric Field value entered successfully", "Failed to enter field value");

		Boolean txtPara = SavedPageObj.enterFieldValue(SavedPageObj.paragraphTxt, "Para");
		TestValidation.IsTrue(txtPara, "Numeric Field value entered successfully", "Failed to enter field value");

		Boolean txtFT = SavedPageObj.enterFieldValue(SavedPageObj.freeText, "FreeText");
		TestValidation.IsTrue(txtFT, "Numeric Field value entered successfully", "Failed to enter field value");

		Boolean selectOne = SavedPageObj.selectOneOrMultipleFieldValues("SelectOne", "GOOD");
		TestValidation.IsTrue(selectOne, "Numeric Field value entered successfully", "Failed to enter field value");

		String date = ControlActions_MobileApp.AddDaystoToddaysDate(0);
		Boolean Sedate = SavedPageObj.clickFieldType(homePage.datePicker, date);
		TestValidation.IsTrue(Sedate, "Date Selected successfully", "Failed to select Date value");
		String dateSelected = ControlActions_MobileApp.AddDaystoToddaysDateInGivenFormat("M/d/YY", 0);
		logInfo("Date Field Value = " + dateSelected);

		Boolean SedateTime = SavedPageObj.clickFieldType(homePage.dateTimePicker, date);
		String dateTimeSel = homePage.dateTimePicker.getText();
		logInfo("Date&Time Field Value = " + dateTimeSel);

		TestValidation.IsTrue(SedateTime, "DateTime value selected successfully", "Failed to Select DateTime value");

		Boolean SeMultiple = SavedPageObj.selectOneOrMultipleFieldValues("MutiSelect", "10");
		TestValidation.IsTrue(SeMultiple, "Numeric Field value entered successfully", "Failed to enter field value");
		Boolean SeMultiple1 = SavedPageObj.selectOneOrMultipleFieldValues("MutiSelect", "30");
		TestValidation.IsTrue(SeMultiple1, "Numeric Field value entered successfully", "Failed to enter field value");

		Boolean submitForm = SavedPageObj.submitForm();
		TestValidation.IsTrue(submitForm, "Form Submitted Successfully", "Failed  to Submit Form");
		Boolean clickSubmissions = homePage.ClickSubMenu(homePage.submissionsSubMenu);
		TestValidation.IsTrue(clickSubmissions, "Clicked on submissions subMenu Successfully",
				"Failed to click Submissions subMenu");
		ControlActions_MobileApp.click(SavedPageObj.sortBtn);
		Boolean searchForm = homePage.SearchForm(FormName);
		TestValidation.IsTrue(searchForm, "form Searched Successfully",
				"Failed to search form from submissions screen");
		Boolean chkStatus = SavedPageObj.checkFormStatusFromSubmissionsSubMenu(FormName, "RECEIVED");
		TestValidation.IsTrue(chkStatus, "Verified form Status Successfully", "Failed to verify Form status");
		appiumDriver.hideKeyboard();
		boolean ResubmitVisible = true;
		try {
			if (SavedPageObj.clearAllBtn.isDisplayed() && SavedPageObj.clearAllBtn.isEnabled()) {
				if (SavedPageObj.resubmitBtn.isDisplayed())
					ResubmitVisible = false;
			}
		} catch (NoSuchElementException e) {
			logInfo("Resubmit All button is not present as expected");
		}
		TestValidation.IsTrue(ResubmitVisible, "Resubmit All button is not present as expected in online mode",
				"Resubmit All button is still present in online mode");
		SavedPageObj.clickFormFromSubmissionsScreen(FormName);
		LinkedHashMap<String, String> FieldTypeValues = new LinkedHashMap<String, String>();
		FieldTypeValues.put(Numeric, "8");
		FieldTypeValues.put(ParaTxt, "Para");
		FieldTypeValues.put(chkFreeTxt, "FreeText");
		FieldTypeValues.put(chkSelectOne, "GOOD");
		FieldTypeValues.put(chkDate, dateSelected);
		FieldTypeValues.put(chkDateTime, dateTimeSel);
		FieldTypeValues.put(SelectMultiple, "10");
		FieldTypeValues.put(SelectMultiple, "30");

		boolean verifyUpdatedValue = SavedPageObj.verifyFieldValues(FieldTypeValues);

		TestValidation.IsTrue(verifyUpdatedValue, "VERIFIED updated value for all fields",
				"Failed to verify updated values for all fields");

		FSQABrowserPage fbp = new FSQABrowserPage(driver);
		FSQABrowserRecordsPage rp = new FSQABrowserRecordsPage(driver);

		boolean navigate = fbp.navigateToFSQATab("Records");
		TestValidation.IsTrue(navigate, "Navigated to FSQABrowser>RecordsTab",
				"Failed to navigate to FSQABrowser>Records Tab");

		boolean applyfilter = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, FormName);

		TestValidation.IsTrue(applyfilter, "Applied Filter on" + COLUMNHEADER.FORMNAME,
				"Failed to apply filter on" + COLUMNHEADER.FORMNAME);

		String dateMinus1 = ControlActions_MobileApp.AddDaystoToddaysDateInGivenFormat("MM/dd/yyyy", -1);

		String CurrentDate = ControlActions_MobileApp.AddDaystoToddaysDateInGivenFormat("MM/dd/yyyy", 0);

		String[] time = dateTimeSel.split("\\s");

		System.out.println(time[0]);
		System.out.println(time[1]);
		System.out.println(time[2]);

		String dateTimeNew = CurrentDate + " " + time[1];// + " " + time[2];

		System.out.println("New date is :" + dateMinus1);

		System.out.println("New datetime is :" + dateTimeNew);
		HashMap<String, List<String>> map1 = new HashMap<String, List<String>>();
		map1.put(Numeric, Arrays.asList("8"));
		map1.put(chkFreeTxt, Arrays.asList("FreeText"));
		map1.put(ParaTxt, Arrays.asList("Para"));
		map1.put(chkDate, Arrays.asList(dateMinus1));
		map1.put(chkDateTime, Arrays.asList(dateTimeNew));

		map1.put(SelectMultiple, Arrays.asList("10 , 30"));
		map1.put(chkSelectOne, Arrays.asList("GOOD"));

		boolean verifyUpdatedValueWeb = rp.verifyFieldValues(map1);

		TestValidation.IsTrue(verifyUpdatedValueWeb, "VERIFIED updated values for all fields",
				"Failed to verify updated values for fields");

		// REcord Vault Code
		try {
			lp = new com.project.safetychain.webapp.pageobjects.LoginPage(driver);
			controlActions.getUrl(applicationUrl);
			hp = lp.performLogin(adminUsername, adminPassword);

			com.project.safetychain.webapp.pageobjects.HomePage hp1 = new com.project.safetychain.webapp.pageobjects.HomePage(
					driver);
			com.project.safetychain.webapp.pageobjects.LinkPage lp = hp1.clickLinkMenu();
			TestValidation.Equals(lp.error, false, "CLICKED on Link menu", "Could NOT click on Link menu");

			LinkRecordsPage lrp = lp.clickLinkRecords();
			TestValidation.Equals(lrp.error, false, "CLICKED on Link - Records menu",
					"Could NOT click on Link - Records menu");

			boolean selectForm = lrp.findAndClickFormToSelect(FormName);
			TestValidation.IsTrue(selectForm, "SELECTED the Form " + FormName, "Failed to Select the Form " + FormName);

			boolean verifyLocation = lrp.verifyRecordsDetail(RecordFieldNames.LOCATION, locationInstanceValue1);
			TestValidation.IsTrue(verifyLocation, "VERIFIED Location of the form as " + locationInstanceValue1,
					"Failed to Verify Location of the form as " + locationInstanceValue1);

			boolean verifyResource = lrp.verifyRecordsDetail(RecordFieldNames.RESOURCE, resourceInstanceValue1);
			TestValidation.IsTrue(verifyResource, "VERIFIED Resource of the form as " + resourceInstanceValue1,
					"Failed to Verify Resource of the form as " + resourceInstanceValue1);

			boolean verifyFormName = lrp.verifyRecordsDetail(RecordFieldNames.FORM, FormName);
			TestValidation.IsTrue(verifyFormName, "VERIFIED the Form name as " + FormName,
					"Failed to Verify the Form name as " + FormName);

			boolean verifyDisplayName = lrp.verifyRecordsDetail(RecordFieldNames.USER, "Auto User");
			TestValidation.IsTrue(verifyDisplayName, "VERIFIED Display name of the form as " + "Auto User",
					"Failed to Verify Display name of the form as " + "Auto User");
		} finally {
			driver.close();
		}
	}

	@Test(description = "Verify clear button functionality from submission Screen")
	public void TestCase_36798() throws Exception {
		appiumDriver = launchMobileApp();
		mobControlActions = new ControlActions_MobileApp(appiumDriver);
		SavedPage SavedPageObj = new SavedPage(appiumDriver);
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
		boolean searchForm = homePage.SearchForm(FormName);
		TestValidation.IsTrue(searchForm, "Form Searched Successfully", "Failed to Search Form from Forms subMenu");
		boolean clickFormsSub = homePage.ClickForm(FormName);
		TestValidation.IsTrue(clickFormsSub, "Clicked on Forms subMenu Successfully", "Failed to click Forms subMenu");
		boolean searchLocation = SavedPageObj.SearchResource(locationInstanceValue1);
		TestValidation.IsTrue(searchLocation, "Location Searched Successfully",
				"Failed to Search Location from Forms subMenu");
		boolean searchResource = SavedPageObj.SearchResource(resourceInstanceValue1);
		TestValidation.IsTrue(searchResource, "Resource Searched Successfully",
				"Failed to Search Resource from Forms subMenu");

		Boolean txtNumeric = SavedPageObj.enterFieldValue(SavedPageObj.NumericTxt, "8");
		TestValidation.IsTrue(txtNumeric, "Numeric Field value entered successfully", "Failed to enter field value");

		Boolean txtPara = SavedPageObj.enterFieldValue(SavedPageObj.paragraphTxt, "Para");
		TestValidation.IsTrue(txtPara, "Numeric Field value entered successfully", "Failed to enter field value");

		Boolean txtFT = SavedPageObj.enterFieldValue(SavedPageObj.freeText, "FreeText");
		TestValidation.IsTrue(txtFT, "Numeric Field value entered successfully", "Failed to enter field value");

		Boolean selectOne = SavedPageObj.selectOneOrMultipleFieldValues("SelectOne", "GOOD");
		TestValidation.IsTrue(selectOne, "Numeric Field value entered successfully", "Failed to enter field value");

		String date = ControlActions_MobileApp.AddDaystoToddaysDate(0);
		Boolean Sedate = SavedPageObj.clickFieldType(homePage.datePicker, date);
		TestValidation.IsTrue(Sedate, "Date Selected successfully", "Failed to select Date value");
		Boolean SedateTime = SavedPageObj.clickFieldType(homePage.dateTimePicker, date);
		TestValidation.IsTrue(SedateTime, "DateTime value selected successfully", "Failed to Select DateTime value");

		Boolean SeMultiple = SavedPageObj.selectOneOrMultipleFieldValues("MutiSelect", "10");
		TestValidation.IsTrue(SeMultiple, "Value selected for SelectMultiple Field Type",
				"Failed to select MultiSelect value");
		Boolean SeMultiple1 = SavedPageObj.selectOneOrMultipleFieldValues("MutiSelect", "30");
		TestValidation.IsTrue(SeMultiple1, "Value selected for SelectMultiple Field Type",
				"Failed to select MultiSelect value");

		boolean submitForm = SavedPageObj.submitForm();
		TestValidation.IsTrue(submitForm, "Form Submitted Successfully", "Failed to Submit Form from Forms SubTab");
		boolean clickSubmissionsSub = homePage.ClickSubMenu(homePage.submissionsSubMenu);
		TestValidation.IsTrue(clickSubmissionsSub, "Clicked on Submissions subMenu Successfully",
				"Failed to click Submissions subMenu");
		ControlActions_MobileApp.click(SavedPageObj.sortBtn);
		searchForm = homePage.SearchForm(FormName);
		TestValidation.IsTrue(searchForm, "Form Searched Successfully", "Failed to Search Form from Forms subMenu");
		appiumDriver.hideKeyboard();
		boolean formStatus = SavedPageObj.checkFormStatusFromSubmissionsSubMenu(FormName, "RECEIVED");
		TestValidation.IsTrue(formStatus, "Form status verified Succesfully", "Failed to verify form status");
		boolean ResubmitVisible = true;
		try {
			if (SavedPageObj.clearAllBtn.isDisplayed() && SavedPageObj.clearAllBtn.isEnabled()) {
				if (SavedPageObj.resubmitBtn.isDisplayed())
					ResubmitVisible = false;
			}
		} catch (NoSuchElementException e) {
			logInfo("Resubmit All button is not present as expected");
		}

		TestValidation.IsTrue(ResubmitVisible, "Resubmit All button is not present as expected in online mode",
				"Resubmit All button is still present in online mode");
		boolean clickClearAll = SavedPageObj.clickClearAll();
		TestValidation.IsTrue(clickClearAll, "Clicked on Clear All Button Successfully",
				"Failed to click Clear All Button on Submissions");
		boolean checkVisibity = SavedPageObj.checkFormVisibilityOnSubmissionScreen(FormName);
		TestValidation.IsTrue(checkVisibity, "Verified Form Visibility on Submissions Successfully",
				"Failed to Verify Form Visibility on Submissions");
		mobControlActions.WaitforelementToBeClickable(SavedPageObj.closeSearch);
		SavedPageObj.closeSearch.click();

		String FormText = SavedPageObj.formVisibilityTxt.getText();
		logInfo("Text on submission screen " + FormText);
		boolean clear = false;
		if (FormText.equals("No Submissions")) {
			logInfo("All Submissions are cleared on Submissions Screen");
			clear = true;
		} else {
			logInfo("Still Forms are present");
			clear = false;
		}
		TestValidation.IsTrue(clear, "Verified clear All functionality on Submissions Successfully",
				"Failed to Verify clear All functionality on Submissions Screen");
	}

	@Test(description = "Verify Pin")
	public void TestCase_36827() throws Exception {
		String User = mobileAppUsername;
		String Pin = "1234";
		boolean iscompliant = true;
		String comment = "Pin Verification";
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

		boolean verifySavedPinetails = SavedPageObj.verifyPinDetailsFromSubmissionScreen(FormName, mobileApp_FNameLName,
				mobileApp_Tenant, iscompliant, comment);
		TestValidation.IsTrue(verifySavedPinetails, "Pin Details Verified Successfully After Saving pin details",
				"Failed to verify Pin Details After Saving pin details");

		appiumDriver.findElement(By.id("btnClose")).click();

		Boolean txtNumeric = SavedPageObj.enterFieldValue(SavedPageObj.NumericTxt, "8");
		TestValidation.IsTrue(txtNumeric, "Numeric Field value entered successfully", "Failed to enter field value");

		Boolean txtPara = SavedPageObj.enterFieldValue(SavedPageObj.paragraphTxt, "Para");
		TestValidation.IsTrue(txtPara, "Numeric Field value entered successfully", "Failed to enter field value");

		Boolean txtFT = SavedPageObj.enterFieldValue(SavedPageObj.freeText, "FreeText");
		TestValidation.IsTrue(txtFT, "Numeric Field value entered successfully", "Failed to enter field value");

		Boolean selectOne = SavedPageObj.selectOneOrMultipleFieldValues("SelectOne", "GOOD");
		TestValidation.IsTrue(selectOne, "Numeric Field value entered successfully", "Failed to enter field value");

		String date = ControlActions_MobileApp.AddDaystoToddaysDate(0);
		Boolean Sedate = SavedPageObj.clickFieldType(homePage.datePicker, date);
		TestValidation.IsTrue(Sedate, "Date Selected successfully", "Failed to select Date value");
		Boolean SedateTime = SavedPageObj.clickFieldType(homePage.dateTimePicker, date);
		TestValidation.IsTrue(SedateTime, "DateTime value selected successfully", "Failed to Select DateTime value");

		Boolean SeMultiple = SavedPageObj.selectOneOrMultipleFieldValues("MutiSelect", "10");
		TestValidation.IsTrue(SeMultiple, "Numeric Field value entered successfully", "Failed to enter field value");
		Boolean SeMultiple1 = SavedPageObj.selectOneOrMultipleFieldValues("MutiSelect", "30");
		TestValidation.IsTrue(SeMultiple1, "Numeric Field value entered successfully", "Failed to enter field value");

		Boolean submitForm = SavedPageObj.submitForm();
		TestValidation.IsTrue(submitForm, "Form submitted Successfully", "Failed to submit Form");
		homePage.ClickSubMenu(homePage.submissionsSubMenu);
		TestValidation.IsTrue(submitForm, "Clicked on Submissions Sub Menu Successfully",
				"Failed to click Submissions subMenu ");
		ControlActions_MobileApp.click(SavedPageObj.sortBtn);
		searchForm = homePage.SearchForm(FormName);
		TestValidation.IsTrue(searchForm, "Form Searched Successfully", "Failed to search Form on Submissions");
		appiumDriver.hideKeyboard();
		clickForm = SavedPageObj.clickFormFromSubmissionsScreen(FormName);
		TestValidation.IsTrue(clickForm, "Form clicked Successfully", "Failed to click Form");
		boolean verifyPinetails = SavedPageObj.verifyPinDetailsFromSubmissionScreen(FormName, mobileApp_FNameLName,
				mobileApp_Tenant, iscompliant, comment);
		TestValidation.IsTrue(verifyPinetails, "Pin Details Verified Successfully from Submissions screen",
				"Failed to verify Pin Details from Submissions screen");

	}

	@Test(description = "UpdatedFormVersion")
	public void TestCase_36802() throws Exception {
		appiumDriver = launchMobileApp();
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
		// Form details

		homePage.SearchForm(FormName);

		// Version Check

		UpdatedFormVersionPage UpdatedFormVersionPageObj = new UpdatedFormVersionPage(appiumDriver);
		UpdatedFormVersionPageObj.FirstVersion();

		// Browser code

		hp.clickFSQABrowserMenu();
		boolean navigate = fbp.navigateToFSQATab("Forms");
		TestValidation.IsTrue(navigate, "Navigated to FSQABrowser>FormsTab",
				"Failed to navigate to FSQABrowser>Forms Tab");

		boolean applyfilter = fbp.openAndApplySettingsForColumn(COLUMNHEADER.FORMNAME, COLUMNSETTING.FILTER, FormName);
		TestValidation.IsTrue(applyfilter, "Applied Filter on" + COLUMNHEADER.FORMNAME,
				"Failed to apply filter on" + COLUMNHEADER.FORMNAME);

		String version = fbp.getGridValueForColumn(COLUMNHEADER.VERSION);

		float expected_version = (Float.parseFloat(version));

		Float expected_version1 = expected_version + 1;

		logInfo("Expected Version =" + expected_version1);

		boolean editform = fbp.editForm(FormName, formDesignerPage);
		TestValidation.IsTrue(editform, "Form Edited", "Failed to edit form");

		hp.clickFSQABrowserMenu();

		boolean navigate1 = fbp.selectResourceDropDownandNavigate("CUSTOMERS", "Forms");
		TestValidation.IsTrue(navigate1, "Navigated to FSQABrowser>FormsTab",
				"Failed to navigate to FSQABrowser>Forms Tab");

		boolean applyfilter1 = fbp.openAndApplySettingsForColumn(COLUMNHEADER.FORMNAME, COLUMNSETTING.FILTER, FormName);
		TestValidation.IsTrue(applyfilter1, "Applied Filter on" + COLUMNHEADER.FORMNAME,
				"Failed to apply filter on" + COLUMNHEADER.FORMNAME);

		boolean verifyversion = fbp.verifyGridValuesForColumn(COLUMNHEADER.VERSION, expected_version1.toString());
		TestValidation.IsTrue(verifyversion, "Verified version" + expected_version1.toString(),
				"Failed to verifiy version" + expected_version1.toString());
		mainPage.Sync();

		// Version Check

		homePage.SearchForm(FormName);
		ControlActions_MobileApp.swipeScreen("DOWN");
		Thread.sleep(20000);
		String mobVersionAfterUpdate = UpdatedFormVersionPageObj.FirstVersion();
		Boolean CompareVersion = UpdatedFormVersionPageObj.CompareVersion(mobVersionAfterUpdate,
				expected_version1.toString());
		TestValidation.IsTrue(CompareVersion, "Version is updated", "version not updated");

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
