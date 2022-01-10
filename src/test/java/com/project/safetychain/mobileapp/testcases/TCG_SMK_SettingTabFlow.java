package com.project.safetychain.mobileapp.testcases;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.project.safetychain.api.models.FormDesigner;
import com.project.safetychain.api.models.ManageLocation;
import com.project.safetychain.api.models.ManageResources;
import com.project.safetychain.api.models.support.Support.DPT_FIELD_TYPES;
import com.project.safetychain.api.models.support.Support.DPT_FORM_TYPES;
import com.project.safetychain.api.models.support.Support.FormFieldParams;
import com.project.safetychain.api.models.support.Support.FormParams;
import com.project.safetychain.api.models.support.Support.RESOURCE_TYPES;
import com.project.safetychain.api.models.support.TCG_FormCreation_Wrapper;
import com.project.safetychain.mobileapp.pageobjects.HomePage;
import com.project.safetychain.mobileapp.pageobjects.LoginPage;
import com.project.safetychain.mobileapp.pageobjects.REG_SubmissionsPage;
import com.project.safetychain.mobileapp.pageobjects.SavedPage;
import com.project.safetychain.mobileapp.pageobjects.SettingsPage;
import com.project.safetychain.webapp.pageobjects.CommonPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserFormsPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FormDesignParams;
//import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FormDesignParams;
//import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FormFieldParams;
import com.project.safetychain.webapp.pageobjects.ManageLocationPage;
import com.project.safetychain.webapp.pageobjects.ManageResourcePage;
import com.project.safetychain.webapp.pageobjects.ResourceDesignerPage;
import com.project.safetychain.webapp.testcases.support.FormOperations;
import com.project.safetychain.webapp.testcases.support.FormOperations.FormDetails;
import com.project.testbase.TestBase;
//import com.project.testbase.TestBaseApi;
import com.project.testbase.TestValidation;
import com.project.utilities.ApiUtils;
import com.project.utilities.CommonMethods;
import com.project.utilities.ControlActions;
import com.project.utilities.ControlActions_MobileApp;

public class TCG_SMK_SettingTabFlow extends TestBase {

	ControlActions_MobileApp mobControlActions;

	ApiUtils apiUtils = null;
	ManageLocation manageLocation = null;
	ManageResources manageResources = null;
	FormDesigner formDesigner = null;

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

	String formtype = "Check";
	String modifiedby;
	public static String FormName;
	public static String locInstId, resInstId;

	@BeforeClass(alwaysRun = true)
	public void groupInit() {
		try {

			driver = launchbrowser();
			formDesignerPage = new FormDesignerPage(driver);
			locations = new ManageLocationPage(driver);
			manageResource = new ManageResourcePage(driver);
			fbp = new FSQABrowserPage(driver);
			fbForms = new FSQABrowserFormsPage(driver);
			resourceDesigner = new ResourceDesignerPage(driver);
			mainPage = new CommonPage(driver);
			fop = new FormOperations(driver);

			apiUtils = new ApiUtils();
			TCG_FormCreation_Wrapper formCreationWrapper = new TCG_FormCreation_Wrapper();

			FormName = CommonMethods.dynamicText("API_Form");
			locationCategoryValue = CommonMethods.dynamicText("LCat");
			locationInstanceValue1 = CommonMethods.dynamicText("LInst1");
			locationInstanceValue2 = CommonMethods.dynamicText("LInst2");
			resourceCategoryValue = CommonMethods.dynamicText("RCat");
			resourceInstanceValue1 = CommonMethods.dynamicText("RInst1");
			resourceInstanceValue2 = CommonMethods.dynamicText("RInst2");

			// API Implementation

			// Field Names
			chkFreeTxt = "FreeText";
			ParaTxt = "Paragraph";
			chkSelectOne = "SelectOne";
			Numeric = "Numeric";
			chkDate = "Date";
			chkTime = "Time";
			chkDateTime = "Date&Time";
			SelectMultiple = "MutiSelect";

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

			HashMap<String, List<String>> LocationMap = new HashMap<String, List<String>>();
			LocationMap.put(locationCategoryValue, Arrays.asList(locationInstanceValue1, locationInstanceValue2));

			List<String> userList = Arrays.asList(mobileAppUsername, mobileAppUsername02);

			HashMap<String, String> locResMap = formCreationWrapper.Resource_Location_CreationWrapper(resourceCatMap,
					LocationMap, true, userList, fp.ResourceType);

			locInstId = locResMap.get(locationInstanceValue1);
			resInstId = locResMap.get(resourceInstanceValue1);

			formCreationWrapper.API_Wrapper_Forms(fp);

		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	@Test(description = "Clear Submission For All Users")
	public void TestCase_36829() throws Exception {
		appiumDriver = launchMobileApp();

		SavedPage SavedPageObj = new SavedPage(appiumDriver);
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
		String dateSelected = ControlActions_MobileApp.AddDaystoToddaysDateInGivenFormat("M/d/YYYY", 0);
		logInfo("Date Field Value = " + dateSelected);

		Boolean SedateTime = SavedPageObj.clickFieldType(homePage.dateTimePicker, date);
		String dateTimeSel = homePage.dateTimePicker.getText();
		logInfo("Date&Time Field Value = " + dateTimeSel);
		TestValidation.IsTrue(SedateTime, "DateTime value selected successfully", "Failed to Select DateTime value");

		Boolean SeMultiple = SavedPageObj.selectOneOrMultipleFieldValues("MutiSelect", "10");
		TestValidation.IsTrue(SeMultiple, "Numeric Field value entered successfully", "Failed to enter field value");
		Boolean SeMultiple1 = SavedPageObj.selectOneOrMultipleFieldValues("MutiSelect", "30");
		TestValidation.IsTrue(SeMultiple1, "Numeric Field value entered successfully", "Failed to enter field value");

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
		
		//======== Form Submission - Online Mode, Received
		ControlActions_MobileApp.waitForVisibility(homePage.searchField, 300);
		Boolean searchFrms1 = homePage.SearchForm(FormName);
		TestValidation.IsTrue(searchFrms1, "Form Searched successfully", "Failed to search Form");
		Boolean clickFrms1 = homePage.ClickForm(FormName);
		TestValidation.IsTrue(clickFrms1, "Form Clicked successfully", "Failed to click Form");
		Boolean searchLocation1 = SavedPageObj.SearchResource(locationInstanceValue1);
		TestValidation.IsTrue(searchLocation1, "Location Searched successfully", "Failed to search Location");
		Boolean searchResource1 = SavedPageObj.SearchResource(resourceInstanceValue1);
		TestValidation.IsTrue(searchResource1, "Resource Searched successfully", "Failed to Resource Form");

		Boolean txtNumeric1 = SavedPageObj.enterFieldValue(SavedPageObj.NumericTxt, "8");
		TestValidation.IsTrue(txtNumeric1, "Numeric Field value entered successfully", "Failed to enter field value");

		Boolean txtPara1 = SavedPageObj.enterFieldValue(SavedPageObj.paragraphTxt, "Para");
		TestValidation.IsTrue(txtPara1, "Numeric Field value entered successfully", "Failed to enter field value");

		Boolean txtFT1 = SavedPageObj.enterFieldValue(SavedPageObj.freeText, "FreeText");
		TestValidation.IsTrue(txtFT1, "Numeric Field value entered successfully", "Failed to enter field value");

		Boolean selectOne1 = SavedPageObj.selectOneOrMultipleFieldValues("SelectOne", "GOOD");
		TestValidation.IsTrue(selectOne1, "Numeric Field value entered successfully", "Failed to enter field value");

		// String date = ControlActions_MobileApp.AddDaystoToddaysDate(0);
		Boolean Sedate1 = SavedPageObj.clickFieldType(homePage.datePicker, date);
		TestValidation.IsTrue(Sedate1, "Date Selected successfully", "Failed to select Date value");
		String dateSelected1 = ControlActions_MobileApp.AddDaystoToddaysDateInGivenFormat("M/d/YYYY", 0);
		logInfo("Date Field Value = " + dateSelected1);

		Boolean SedateTime1 = SavedPageObj.clickFieldType(homePage.dateTimePicker, date);
		String dateTimeSel1 = homePage.dateTimePicker.getText();
		logInfo("Date&Time Field Value = " + dateTimeSel1);
		TestValidation.IsTrue(SedateTime1, "DateTime value selected successfully", "Failed to Select DateTime value");

		Boolean SeMultiple3 = SavedPageObj.selectOneOrMultipleFieldValues("MutiSelect", "10");
		TestValidation.IsTrue(SeMultiple3, "Numeric Field value entered successfully", "Failed to enter field value");
		Boolean SeMultiple2 = SavedPageObj.selectOneOrMultipleFieldValues("MutiSelect", "30");
		TestValidation.IsTrue(SeMultiple2, "Numeric Field value entered successfully", "Failed to enter field value");

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

		
		//================= Form Submission - Offline Mode, Pending
		REG_SubmissionsPage SubPage = new REG_SubmissionsPage(appiumDriver);
		boolean goOffline = SubPage.clickofflineToggleButton();
		TestValidation.IsTrue(goOffline, "Verified offline Toggle button click Successfully",
				"Failed to click offline toggle button from Settings subMenu");
		
		Boolean clickForms = homePage.ClickMenu(homePage.formsSubMenu);
		TestValidation.IsTrue(clickForms, "Clicked on Forms subMenu Successfully", "Failed to click Forms subMenu");
		
		Boolean searchFrmsO = homePage.SearchForm(FormName);
		TestValidation.IsTrue(searchFrmsO, "Form Searched successfully", "Failed to search Form");
		Boolean clickFrmsO = homePage.ClickForm(FormName);
		TestValidation.IsTrue(clickFrmsO, "Form Clicked successfully", "Failed to click Form");
		Boolean searchLocationO = SavedPageObj.SearchResource(locationInstanceValue1);
		TestValidation.IsTrue(searchLocationO, "Location Searched successfully", "Failed to search Location");
		Boolean searchResourceO = SavedPageObj.SearchResource(resourceInstanceValue1);
		TestValidation.IsTrue(searchResourceO, "Resource Searched successfully", "Failed to Resource Form");

		Boolean txtNumericO = SavedPageObj.enterFieldValue(SavedPageObj.NumericTxt, "8");
		TestValidation.IsTrue(txtNumericO, "Numeric Field value entered successfully", "Failed to enter field value");

		Boolean txtParaO = SavedPageObj.enterFieldValue(SavedPageObj.paragraphTxt, "Para");
		TestValidation.IsTrue(txtParaO, "Numeric Field value entered successfully", "Failed to enter field value");

		Boolean txtFTO = SavedPageObj.enterFieldValue(SavedPageObj.freeText, "FreeText");
		TestValidation.IsTrue(txtFTO, "Numeric Field value entered successfully", "Failed to enter field value");

		Boolean selectOneO = SavedPageObj.selectOneOrMultipleFieldValues("SelectOne", "GOOD");
		TestValidation.IsTrue(selectOneO, "Numeric Field value entered successfully", "Failed to enter field value");

		// String date = ControlActions_MobileApp.AddDaystoToddaysDate(0);
		Boolean SedateO = SavedPageObj.clickFieldType(homePage.datePicker, date);
		TestValidation.IsTrue(SedateO, "Date Selected successfully", "Failed to select Date value");
		String dateSelectedO = ControlActions_MobileApp.AddDaystoToddaysDateInGivenFormat("M/d/YYYY", 0);
		logInfo("Date Field Value = " + dateSelectedO);

		Boolean SedateTimeO = SavedPageObj.clickFieldType(homePage.dateTimePicker, date);
		String dateTimeSelO = homePage.dateTimePicker.getText();
		logInfo("Date&Time Field Value = " + dateTimeSelO);
		TestValidation.IsTrue(SedateTimeO, "DateTime value selected successfully", "Failed to Select DateTime value");

		Boolean SeMultipleO = SavedPageObj.selectOneOrMultipleFieldValues("MutiSelect", "10");
		TestValidation.IsTrue(SeMultipleO, "Numeric Field value entered successfully", "Failed to enter field value");
		Boolean SeMultiple2O = SavedPageObj.selectOneOrMultipleFieldValues("MutiSelect", "30");
		TestValidation.IsTrue(SeMultiple2O, "Numeric Field value entered successfully", "Failed to enter field value");

		Boolean submitFormO = SavedPageObj.submitForm();
		TestValidation.IsTrue(submitFormO, "Form Submitted Successfully", "Failed to Submit Form");
		Boolean clickSubmissionsO = homePage.ClickSubMenu(homePage.submissionsSubMenu);
		TestValidation.IsTrue(clickSubmissionsO, "Clicked on submissions subMenu Successfully",
				"Failed to click Submissions subMenu");
		ControlActions_MobileApp.click(SavedPageObj.sortBtn);
		Boolean searchFormO = homePage.SearchForm(FormName);
		TestValidation.IsTrue(searchFormO, "form Searched Successfully",
				"Failed to search form from submissions screen");
		Boolean chkStatusO = SavedPageObj.checkFormStatusFromSubmissionsSubMenu(FormName, "PENDING");
		TestValidation.IsTrue(chkStatusO, "Verified form Status Successfully", "Failed to verify Form status");
		ControlActions_MobileApp.click(SavedPageObj.closeSearch);
		appiumDriver.hideKeyboard();
		
		//========= Clear submission for all users - Offline, Pending Form Remains
		homePage.ClickSubMenu(homePage.settingsSubMenu);
		SettingsPage SettingsPageObj = new SettingsPage(appiumDriver);
		Boolean clearAll = SettingsPageObj.clickClearAllSubmissions();
		TestValidation.IsTrue(clearAll, "Clicked on ClearAll Submissions", "Unable to Click on ClearAllSubmissions");

		Boolean BackButton = SettingsPageObj.ClickBackBtn();
		TestValidation.IsTrue(BackButton, "BackButton clicked", "Unable to click on BackButton");

		Boolean submissionClick = homePage.ClickMenu(homePage.submissionsSubMenu);
		TestValidation.IsTrue(submissionClick, "submissionSubmenu clicked", "Unable to click on submissionsubmenu");

		ControlActions_MobileApp.click(SavedPageObj.sortBtn);
		Boolean searchForm2O = homePage.SearchForm(FormName);
		TestValidation.IsTrue(searchForm2O, "form Searched Successfully",
				"Failed to search form from submissions screen");
		Boolean chkStatus2O = SavedPageObj.checkFormStatusFromSubmissionsSubMenu(FormName, "PENDING");
		TestValidation.IsTrue(chkStatus2O, "Verified form Status Successfully", "Failed to verify Form status");
		ControlActions_MobileApp.click(SavedPageObj.closeSearch);
		appiumDriver.hideKeyboard();
		
		//=========== Go Online, Verify Pending Form -> Received in Submissions
		homePage.ClickSubMenu(homePage.settingsSubMenu);
		boolean goOnline = SettingsPageObj.clickofflineToggleButton();
		
		Boolean BackButton2 = SettingsPageObj.ClickBackBtn();
		TestValidation.IsTrue(BackButton2, "BackButton clicked", "Unable to click on BackButton");
		
		Boolean submissionClick2 = homePage.ClickMenu(homePage.submissionsSubMenu);
		TestValidation.IsTrue(submissionClick2, "submissionSubmenu clicked", "Unable to click on submissionsubmenu");
		ControlActions_MobileApp.swipeScreen("DOWN");
		Thread.sleep(8000);
		ControlActions_MobileApp.click(SavedPageObj.sortBtn);
		Boolean searchForm3O = homePage.SearchForm(FormName);
		TestValidation.IsTrue(searchForm3O, "form Searched Successfully",
				"Failed to search form from submissions screen");
		Boolean chkStatus3O = SavedPageObj.checkFormStatusFromSubmissionsSubMenu(FormName, "RECEIVED");
		TestValidation.IsTrue(chkStatus3O, "Verified form Status Successfully", "Failed to verify Form status");
		ControlActions_MobileApp.click(SavedPageObj.closeSearch);
		appiumDriver.hideKeyboard();
		
		//========= Clear submission for all users - Online, Pending Form is cleared -> All form is cleared
		homePage.ClickSubMenu(homePage.settingsSubMenu);
		Boolean clearAll2 = SettingsPageObj.clickClearAllSubmissions();
		TestValidation.IsTrue(clearAll2, "Clicked on ClearAll Submissions", "Unable to Click on ClearAllSubmissions");

		Boolean BackButton3 = SettingsPageObj.ClickBackBtn();
		TestValidation.IsTrue(BackButton3, "BackButton clicked", "Unable to click on BackButton");

		Boolean submissionClick3 = homePage.ClickMenu(homePage.submissionsSubMenu);
		TestValidation.IsTrue(submissionClick3, "submissionSubmenu clicked", "Unable to click on submissionsubmenu");
		
		ControlActions_MobileApp.swipeScreen("DOWN");
		Thread.sleep(8000);
		
		Boolean SubmittedForms = SavedPageObj.checkFormVisibility(FormName);
		TestValidation.IsTrue(SubmittedForms, "No forms on submissions screen", "Forms are still available");

		// 1st User Login Again to check clear submission Functionality
		loginPage.logOut();
		boolean login3 = loginPage.Login(mobileAppUsername, mobileAppPassword);
		TestValidation.IsTrue(login3, "logged into the application with User " + mobileAppUsername, "Failed to log in");
		Boolean submissionClick1 = homePage.ClickMenu(homePage.submissionsSubMenu);

		TestValidation.IsTrue(submissionClick1, "submissionSubmenu clicked", "Unable to click on submissionsubmenu");
		Boolean SubmittedForms1 = SavedPageObj.checkFormVisibility(FormName);
		TestValidation.IsTrue(SubmittedForms1, "No forms on submissions screen", "Forms are still available");

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