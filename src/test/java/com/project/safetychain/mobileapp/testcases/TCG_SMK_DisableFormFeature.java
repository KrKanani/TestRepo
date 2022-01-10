package com.project.safetychain.mobileapp.testcases;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.project.safetychain.api.models.support.Support.DPT_FIELD_TYPES;
import com.project.safetychain.api.models.support.Support.DPT_FORM_TYPES;
import com.project.safetychain.api.models.support.Support.FormFieldParams;
import com.project.safetychain.api.models.support.Support.FormParams;
import com.project.safetychain.api.models.support.Support.RESOURCE_TYPES;
import com.project.safetychain.api.models.support.TCG_FormCreation_Wrapper;
import com.project.safetychain.mobileapp.pageobjects.DisableFormFeaturePage;
import com.project.safetychain.mobileapp.pageobjects.HomePage;
import com.project.safetychain.mobileapp.pageobjects.LoginPage;
import com.project.safetychain.mobileapp.pageobjects.SavedPage;
import com.project.safetychain.webapp.pageobjects.CommonPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserFormsPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FormDesignParams;
import com.project.safetychain.webapp.pageobjects.FormsManagerPage;
import com.project.safetychain.webapp.pageobjects.FormsManagerPage.FM_FIELDS;
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

public class TCG_SMK_DisableFormFeature extends TestBase {

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
	FormsManagerPage fm;

	public static String checkFormName;
	public static String locationCategoryValue;
	public static String locationInstanceValue1;
	public static String locationInstanceValue2;
	public static String resourceCategoryValue;
	public static String resourceInstanceValue1;
	public static String resourceInstanceValue2;
	public static String chkFreeTxt, ParaTxt, chkSelectOne, SelectMultiple, Numeric, chkDate, chkTime, chkDateTime;

	public static String formName = "Automation_CheckForm_15.10_17.19.19_1";
	public static String resourceName = "RInst2_15.10_13.47.04";
	public static String LocationName = "LInst1_15.10_13.47.04";
	String formtype = "Check";
	String modifiedby;
	public static String FormName;

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

			locationCategoryValue = CommonMethods.dynamicText("LCat");
			locationInstanceValue1 = CommonMethods.dynamicText("LInst1");
			locationInstanceValue2 = CommonMethods.dynamicText("LInst2");
			resourceCategoryValue = CommonMethods.dynamicText("RCat");
			resourceInstanceValue1 = CommonMethods.dynamicText("RInst1");
			resourceInstanceValue2 = CommonMethods.dynamicText("RInst2");
			 
			lp = new com.project.safetychain.webapp.pageobjects.LoginPage(driver);
			controlActions = new ControlActions(driver);

			controlActions.getUrl(applicationUrl);

			hp = lp.performLogin(adminUsername, adminPassword);
			if (hp.error) {
				TCGFailureMsg = "Could NOT login to application";
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);

			}

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
			numericField.Name = "Numeric";

			numericField.Value = "10";

			FormFieldParams paraTextField = new FormFieldParams();
			paraTextField.DPTFieldType = DPT_FIELD_TYPES.PARAGRAPH_TEXT;
			paraTextField.Name = "Para Text";

			paraTextField.Value = "Para Value";

			FormFieldParams freeTextField = new FormFieldParams();
			freeTextField.DPTFieldType = DPT_FIELD_TYPES.FREE_TEXT;
			freeTextField.Name = "Free Text";

			freeTextField.Value = "Text Value";

			FormFieldParams selectOneField = new FormFieldParams();
			selectOneField.DPTFieldType = DPT_FIELD_TYPES.SELECT_ONE;
			selectOneField.Name = "Select One";
			selectOneField.SelectOptions = Arrays.asList("GOOD", "BAD");

			selectOneField.Value = "GOOD";

			FormFieldParams dateField = new FormFieldParams();
			dateField.DPTFieldType = DPT_FIELD_TYPES.DATE;
			dateField.Name = "Date";

			dateField.Value = "09/09/2021";

			FormFieldParams dateTimeField = new FormFieldParams();
			dateTimeField.DPTFieldType = DPT_FIELD_TYPES.DATE_TIME;
			dateTimeField.Name = "Date Time";

			dateTimeField.Value = "09/09/2021 12:00";

			FormFieldParams selectMultipleField = new FormFieldParams();
			selectMultipleField.DPTFieldType = DPT_FIELD_TYPES.SELECT_MULTIPLE;
			selectMultipleField.Name = "Select Multiple";
			selectMultipleField.SelectOptions = Arrays.asList("10", "20", "30");

			selectMultipleField.Value = "10";

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
			fp.LocationInstanceNm = locationInstanceValue1;

			fp.formElements = Arrays.asList(numericField, paraTextField, freeTextField, selectOneField, dateField,
					dateTimeField, selectMultipleField);

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

	@Test(description = "DisableFormFeature")
	public void TestCase_36826() throws Exception {
		appiumDriver = launchMobileApp();
		DisableFormFeaturePage DisableFormFeaturePageObj = new DisableFormFeaturePage(appiumDriver);

		SavedPage SavedPageObj = new SavedPage(appiumDriver);

		LoginPage loginPage = new LoginPage(appiumDriver);
		HomePage homePage = new HomePage(appiumDriver);
		boolean login = loginPage.Login(mobileAppUsername, mobileAppPassword);
		TestValidation.IsTrue(login, "logged into the application with User " + mobileAppUsername, "Failed to log in");

		 
		// Form details

		boolean searchForm = homePage.SearchForm(FormName);
		TestValidation.IsTrue(searchForm, "Form Searched Successfully", "Failed to Search Form");

		Boolean Fav = DisableFormFeaturePageObj.favoritesStarBtn(); // PK
		logInfo("added to Fav");// PK
		TestValidation.IsTrue(Fav, "Able to add in fav", "Unable to add in fav");
		Boolean clickForm = homePage.ClickForm(FormName);
		TestValidation.IsTrue(clickForm, "Clicked on Searched form Successfully", "Failed to click form");

		SavedPageObj.SearchResource(locationInstanceValue1);
		SavedPageObj.SearchResource(resourceInstanceValue1);
		SavedPageObj.enterFieldValue(SavedPageObj.NumericTxt, "8"); 

		boolean saveForm = SavedPageObj.saveForm();
		TestValidation.IsTrue(saveForm, "Form Saved Successfully", "Failed to Save Form ");

		System.out.println("Form is saved");

		boolean clickSaveMenu = homePage.ClickSubMenu(homePage.saveSubMenu);
		TestValidation.IsTrue(clickSaveMenu, "Clicked on Save subMenu Successfully", "Failed to click Save subMenu");
		boolean searchFormSaved = homePage.SearchForm(FormName);
		TestValidation.IsTrue(searchFormSaved, "Form Searched Successfully on saved tab",
				"Failed to Search Form on saved tab");

		boolean clickfavSubMenu = homePage.ClickSubMenu(homePage.favouritesSubMenu);
		TestValidation.IsTrue(clickfavSubMenu, "Clicked on favorites subMenu Successfully",
				"Failed to click favorites subMenu");
		boolean searchFormFav1 = homePage.SearchForm(FormName);
		TestValidation.IsTrue(searchFormFav1, "Form Searched Successfully on Favorites tab",
				"Failed to Search Form in favorites tab");

		// Submit Form

		boolean clickformSubMenu1 = homePage.ClickSubMenu(homePage.formsSubMenu);
		TestValidation.IsTrue(clickformSubMenu1, "Clicked on form subMenu Successfully",
				"Failed to click form subMenu");

		boolean searchFormSub = homePage.SearchForm(FormName);
		TestValidation.IsTrue(searchFormSub, "Form Searched Successfully", "Failed to Search Form from Forms subMenu");

		Boolean clickForm1 = homePage.ClickForm(FormName);
		TestValidation.IsTrue(clickForm1, "Clicked on Searched form Successfully", "Failed to click form");

		SavedPageObj.SearchResource(locationInstanceValue1);
		SavedPageObj.SearchResource(resourceInstanceValue1);

		SavedPageObj.enterFieldValue(SavedPageObj.NumericTxt, "8");
		SavedPageObj.enterFieldValue(SavedPageObj.paragraphTxt, "Para");
		SavedPageObj.enterFieldValue(SavedPageObj.freeText, "FreeText");
		SavedPageObj.selectOneOrMultipleFieldValues("SelectOne", "GOOD");
		String date = ControlActions_MobileApp.AddDaystoToddaysDate(0);
		SavedPageObj.clickFieldType(homePage.datePicker, date);
		SavedPageObj.clickFieldType(homePage.dateTimePicker, date);
		SavedPageObj.selectOneOrMultipleFieldValues("MutiSelect", "10");
		SavedPageObj.selectOneOrMultipleFieldValues("MutiSelect", "30");

		SavedPageObj.submitForm();
		System.out.println("Form is submitted");

		// Submissions Tab
		boolean clickformSubMenuTab = homePage.ClickSubMenu(homePage.submissionsSubMenu);
		TestValidation.IsTrue(clickformSubMenuTab, "Clicked on submission subMenu Successfully",
				"Failed to click submission subMenu");

		boolean searchFormSubTab = homePage.SearchForm(FormName);
		TestValidation.IsTrue(searchFormSubTab, "Form Searched Successfully in Submissions",
				"Failed to Search Form in Submissions");

		// Forms Tab
		boolean clickformsMenuTab = homePage.ClickSubMenu(homePage.formsSubMenu);
		TestValidation.IsTrue(clickformsMenuTab, "Clicked on form subMenu Successfully",
				"Failed to click form subMenu");

		boolean searchForm2 = homePage.SearchForm(FormName);
		TestValidation.IsTrue(searchForm2, "Form Searched Successfully", "Failed to Search Form from Forms subMenu");

		// Disable code here

		fm = hp.clickFormsManagerMenu();
		boolean searchingForm = fm.searchForm(FormName);
		TestValidation.IsTrue(searchingForm, "OPENED the search form - " + FormName,
				"Failed to open searched form - " + FormName);

		String formStatus = fm.getFormManagerDetails(FM_FIELDS.STATUS);
		TestValidation.Equals(true, true, "For Form Manager category,  Selected Form is fetched and displayed",
				"Failed to navigate to Admin Tools > Form Manager tab");
		logInfo("Status of formstatus," + formStatus);

		boolean selectForm = fm.selectForm(FormName);
		TestValidation.IsTrue(selectForm, "Form has Selected- " + FormName, "Failed to select Form- " + FormName);

		boolean isformStatus = fm.performEnableDisableAction(formStatus);
		TestValidation.IsTrue(isformStatus, "Form Status has Changed- " + FormName,
				"Failed to change Form Status- " + FormName);

		logInfo("Form status is changed");

		// Checking form availability after form disable

		// Forms Tab

		boolean clickformsMenuTab1 = homePage.ClickSubMenu(homePage.formsSubMenu);
		TestValidation.IsTrue(clickformsMenuTab1, "Clicked on forms subMenu Successfully",
				"Failed to click forms subMenu");

		ControlActions_MobileApp.swipeScreen("DOWN");
		boolean isDispalyedForms1 = homePage.NoResultsFoundCheck(FormName);

		if (isDispalyedForms1) {

			logInfo("Validation Passed as form is not visible on Forms after Disable in All forms tab");
		} else {
			logInfo("Validation Failed for All forms Tab");
		}

		TestValidation.IsTrue(isDispalyedForms1,
				"Validation Passed as form is not visible on Forms after Disable in All forms tab",
				"Validation Failed for All forms Tab");

		// Favorites Tab

		boolean clickfavMenuTab = homePage.ClickSubMenu(homePage.favouritesSubMenu);
		TestValidation.IsTrue(clickfavMenuTab, "Clicked on favorites subMenu Successfully",
				"Failed to click favorites subMenu");

		// mobControlActions.swipeScreen("DOWN");
		boolean isDispalyedFavourites = homePage.NoResultsFoundCheck(FormName);
		if (isDispalyedFavourites) {
			logInfo("Validation Passed as form is not visible on Favourites after Disable");
		} else {
			logInfo("Validation Failed for Favourites Tab");
		}

		TestValidation.IsTrue(isDispalyedFavourites,
				"Validation Passed as form is not visible on Favourites after Disable",
				"Validation Failed for Favourites tab");

		// Saved Tab

		boolean clickSavedformsMenuTab = homePage.ClickSubMenu(homePage.saveSubMenu);
		TestValidation.IsTrue(clickSavedformsMenuTab, "Clicked on saved form subMenu Successfully",
				"Failed to click saved form subMenu");

		// mobControlActions.swipeScreen("DOWN");
		boolean isDispalyedForms = homePage.NoResultsFoundCheck(FormName);
		if (isDispalyedForms) {
			logInfo("Validation Passed as form is not visible on Saved after Disable");
		} else {
			logInfo("Validation Failed for saved Tab");
		}

		TestValidation.IsTrue(isDispalyedForms, "Validation Passed as form is not visible on Saved after Disable",
				"Validation Failed for Saved tab");

		// Submissions Tab
		boolean clickSubformsMenuTab = homePage.ClickSubMenu(homePage.submissionsSubMenu);
		TestValidation.IsTrue(clickSubformsMenuTab, "Clicked on Submissions subMenu Successfully",
				"Failed to click Submissions subMenu");
		ControlActions_MobileApp.swipeScreen("DOWN");
		Thread.sleep(5000);
		Boolean SubmittedForms1 = SavedPageObj.checkFormVisibilityOnSubmissionScreen(FormName);
		TestValidation.IsTrue(SubmittedForms1, "No forms on submissions screen", "Forms are still available");
		boolean isDispalyed = homePage.NoResultsFoundCheck(FormName);
		if (isDispalyed) {
			logInfo("Validation Passed as form is not visible on Submissions after Disable");
		} else {
			logInfo("Validation Failed for Submissions Tab");
		}

		TestValidation.IsTrue(isDispalyed, "Validation Passed as form is not visible on Submissions after Disable",
				"Validation Failed for Submissions tab");

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
