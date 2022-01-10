package com.project.safetychain.mobileapp.testcases;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.project.safetychain.api.models.support.TCG_FormCreation_Wrapper;
import com.project.safetychain.api.models.support.Support.DPT_FIELD_TYPES;
import com.project.safetychain.api.models.support.Support.DPT_FORM_TYPES;
import com.project.safetychain.api.models.support.Support.FormFieldParams;
import com.project.safetychain.api.models.support.Support.FormParams;
import com.project.safetychain.api.models.support.Support.RESOURCE_TYPES;
import com.project.safetychain.mobileapp.pageobjects.HomePage;
import com.project.safetychain.mobileapp.pageobjects.LoginPage;
import com.project.safetychain.mobileapp.pageobjects.REG_AllFormsSaved;
import com.project.safetychain.mobileapp.pageobjects.SavedPage;
import com.project.safetychain.mobileapp.pageobjects.UpdatedFormVersionPage;
import com.project.safetychain.webapp.pageobjects.CommonPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserFormsPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FormDesignParams;
import com.project.safetychain.webapp.pageobjects.ManageLocationPage;
import com.project.safetychain.webapp.pageobjects.ManageResourcePage;
import com.project.safetychain.webapp.pageobjects.ResourceDesignerPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.COLUMNHEADER;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.COLUMNSETTING;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.FSQATAB;
import com.project.safetychain.webapp.testcases.support.FormOperations;
import com.project.safetychain.webapp.testcases.support.FormOperations.FieldProperties;
import com.project.safetychain.webapp.testcases.support.FormOperations.FormDetails;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.utilities.ApiUtils;
import com.project.utilities.CommonMethods;
import com.project.utilities.ControlActions;
import com.project.utilities.ControlActions_MobileApp;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class TCG_REG_AllFormsSavedFavourites extends TestBase {

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
	REG_AllFormsSaved afSaved;
	public WebDriver driver;
	public static String locationCategoryValue;
	public static String locationInstanceValue1;
	public static String locationInstanceValue2;
	public static String resourceCategoryValue;
	public static String resourceInstanceValue1;
	public static String resourceInstanceValue2;
	public static String chkFreeTxt, ParaTxt, chkSelectOne, SelectMultiple, Numeric, chkDate, chkTime, chkDateTime;

	public String FormName = null;
	String modifiedby;
	public static String FormNameCreated;
	public static final String fresult = "com.safetychain.SCM.M2:id/resultView_num";
	@AndroidFindBy(id = fresult)
	public WebElement NTxt;

	@Test(description = "Refresh pull down")
	public void TestCase_37349() throws InterruptedException, ElementNotVisibleException, ParseException {

		appiumDriver = launchMobileAppReinstall();
		afSaved = new REG_AllFormsSaved(appiumDriver);
		mobControlActions = new ControlActions_MobileApp(appiumDriver);
		LoginPage loginPage = new LoginPage(appiumDriver);

		if (mobileApp_Tenant.equals("test1.stage")) {
			boolean SSOLogin = loginPage.SSO_Login(mobileAppUsername, mobileAppPassword);
			TestValidation.IsTrue(SSOLogin, "logged into the application with User " + mobileAppUsername,
					"Failed to log in");

		} else {
			boolean login = loginPage.LoginwithTenant(mobileApp_Tenant, mobileAppUsername, mobileAppPassword);
			TestValidation.IsTrue(login, "logged into the application with User " + mobileAppUsername,
					"Failed to log in");

		}

		afSaved.updatedListCount();

	}

	@Test(description = "Navigation Between Tabs and verify Footer in Forms Screen")
	public void TestCase_37350_37412() throws InterruptedException {
		appiumDriver = launchMobileApp();
		mobControlActions = new ControlActions_MobileApp(appiumDriver);

		LoginPage loginPage = new LoginPage(appiumDriver);
		afSaved = new REG_AllFormsSaved(appiumDriver);

		if (mobileApp_Tenant.equals("test1.stage")) {
			boolean SSOLogin = loginPage.SSO_Login(mobileAppUsername, mobileAppPassword);
			TestValidation.IsTrue(SSOLogin, "logged into the application with User " + mobileAppUsername,
					"Failed to log in");

		} else {
			boolean login = loginPage.Login(mobileAppUsername, mobileAppPassword);
			TestValidation.IsTrue(login, "logged into the application with User " + mobileAppUsername,
					"Failed to log in");

		}

		afSaved.NavigatingTabs();

	}

	@Test(description = "Saved Functionality")
	public void TestCase_37353_37409() throws InterruptedException, ElementNotVisibleException, ParseException {
		appiumDriver = launchMobileApp();
		mobControlActions = new ControlActions_MobileApp(appiumDriver);

		LoginPage loginPage = new LoginPage(appiumDriver);
		afSaved = new REG_AllFormsSaved(appiumDriver);

		if (mobileApp_Tenant.equals("test1.stage")) {
			boolean SSOLogin = loginPage.SSO_Login(mobileAppUsername, mobileAppPassword);
			TestValidation.IsTrue(SSOLogin, "logged into the application with User " + mobileAppUsername,
					"Failed to log in");

		} else {
			boolean login = loginPage.Login(mobileAppUsername, mobileAppPassword);
			TestValidation.IsTrue(login, "logged into the application with User " + mobileAppUsername,
					"Failed to log in");

		}

		boolean flag =

				afSaved.SavedFunctionality();
		TestValidation.IsTrue(flag, "Saved functionality is working as expected",
				"Saved functionality is not working as expected");
	}

	@Test(description = "Left to Right Navigation")
	public void TestCase_37359_37360() throws InterruptedException, ElementNotVisibleException, ParseException {
		appiumDriver = launchMobileApp();
		mobControlActions = new ControlActions_MobileApp(appiumDriver);

		LoginPage loginPage = new LoginPage(appiumDriver);
		afSaved = new REG_AllFormsSaved(appiumDriver);

		if (mobileApp_Tenant.equals("test1.stage")) {
			boolean SSOLogin = loginPage.SSO_Login(mobileAppUsername, mobileAppPassword);
			TestValidation.IsTrue(SSOLogin, "logged into the application with User " + mobileAppUsername,
					"Failed to log in");

		} else {
			boolean login = loginPage.Login(mobileAppUsername, mobileAppPassword);
			TestValidation.IsTrue(login, "logged into the application with User " + mobileAppUsername,
					"Failed to log in");

		}

		afSaved.leftToRightNavigation();

	}

	@Test(description = "Verify Page Title in Search Box")
	public void TestCase_37406() throws InterruptedException, ElementNotVisibleException, ParseException, IOException {
		appiumDriver = launchMobileApp();
		mobControlActions = new ControlActions_MobileApp(appiumDriver);

		LoginPage loginPage = new LoginPage(appiumDriver);
		afSaved = new REG_AllFormsSaved(appiumDriver);

		if (mobileApp_Tenant.equals("test1.stage")) {
			boolean SSOLogin = loginPage.SSO_Login(mobileAppUsername, mobileAppPassword);
			TestValidation.IsTrue(SSOLogin, "logged into the application with User " + mobileAppUsername,
					"Failed to log in");

		} else {
			boolean login = loginPage.Login(mobileAppUsername, mobileAppPassword);
			TestValidation.IsTrue(login, "logged into the application with User " + mobileAppUsername,
					"Failed to log in");

		}

		afSaved.VerifyPageTitleFromSearchbox();

	}

	@Test(description = "Verify Favourite/Unfavorite functionality")
	public void TestCase_37352() throws InterruptedException, ElementNotVisibleException, ParseException, IOException {
		appiumDriver = launchMobileApp();
		mobControlActions = new ControlActions_MobileApp(appiumDriver);

		LoginPage loginPage = new LoginPage(appiumDriver);
		afSaved = new REG_AllFormsSaved(appiumDriver);

		if (mobileApp_Tenant.equals("test1.stage")) {
			boolean SSOLogin = loginPage.SSO_Login(mobileAppUsername, mobileAppPassword);
			TestValidation.IsTrue(SSOLogin, "logged into the application with User " + mobileAppUsername,
					"Failed to log in");

		} else {
			boolean login = loginPage.Login(mobileAppUsername, mobileAppPassword);
			TestValidation.IsTrue(login, "logged into the application with User " + mobileAppUsername,
					"Failed to log in");

		}

		afSaved.favorite_Unfavorite();

	}

	

	@Test(description = "Verify Hamburger menu count displayed beside menu")
	public void TestCase_37383_37348() throws InterruptedException, ElementNotVisibleException, ParseException, IOException {
		appiumDriver = launchMobileApp();
		mobControlActions = new ControlActions_MobileApp(appiumDriver);

		LoginPage loginPage = new LoginPage(appiumDriver);
		afSaved = new REG_AllFormsSaved(appiumDriver);

		if (mobileApp_Tenant.equals("test1.stage")) {
			boolean SSOLogin = loginPage.SSO_Login(mobileAppUsername, mobileAppPassword);
			TestValidation.IsTrue(SSOLogin, "logged into the application with User " + mobileAppUsername,
					"Failed to log in");

		} else {
			boolean login = loginPage.Login(mobileAppUsername, mobileAppPassword);
			TestValidation.IsTrue(login, "logged into the application with User " + mobileAppUsername,
					"Failed to log in");

		}

		afSaved.countingFormsByScrolling();

	}

	@Test(description = "Verify search feature on all forms /saved / favorite page (by resource & by name ")
	public void TestCase_37351_37394_37396()
			throws InterruptedException, ElementNotVisibleException, ParseException, IOException {
		appiumDriver = launchMobileApp();
		mobControlActions = new ControlActions_MobileApp(appiumDriver);

		LoginPage loginPage = new LoginPage(appiumDriver);
		afSaved = new REG_AllFormsSaved(appiumDriver);

		if (mobileApp_Tenant.equals("test1.stage")) {
			boolean SSOLogin = loginPage.SSO_Login(mobileAppUsername, mobileAppPassword);
			TestValidation.IsTrue(SSOLogin, "logged into the application with User " + mobileAppUsername,
					"Failed to log in");

		} else {
			boolean login = loginPage.Login(mobileAppUsername, mobileAppPassword);
			TestValidation.IsTrue(login, "logged into the application with User " + mobileAppUsername,
					"Failed to log in");

		}

		afSaved.searchByNameAndResource();

	}

	@Test(description = "Task badge count validation")
	public void TestCase_37373_37374()
			throws InterruptedException, ElementNotVisibleException, ParseException, IOException {
		appiumDriver = launchMobileApp();
		mobControlActions = new ControlActions_MobileApp(appiumDriver);

		LoginPage loginPage = new LoginPage(appiumDriver);
		afSaved = new REG_AllFormsSaved(appiumDriver);

		if (mobileApp_Tenant.equals("test1.stage")) {
			boolean SSOLogin = loginPage.SSO_Login(mobileAppUsername, mobileAppPassword);
			TestValidation.IsTrue(SSOLogin, "logged into the application with User " + mobileAppUsername,
					"Failed to log in");

		} else {
			boolean login = loginPage.Login(mobileAppUsername, mobileAppPassword);
			TestValidation.IsTrue(login, "logged into the application with User " + mobileAppUsername,
					"Failed to log in");

		}

		afSaved.taskBadgeValidation();

	}

	@Test(description = "Unlink locations from Web")
	public void TestCase_37401() throws InterruptedException, ElementNotVisibleException, ParseException, IOException {
		appiumDriver = launchMobileApp();
		mobControlActions = new ControlActions_MobileApp(appiumDriver);

		LoginPage loginPage = new LoginPage(appiumDriver);
		afSaved = new REG_AllFormsSaved(appiumDriver);

		if (mobileApp_Tenant.equals("test1.stage")) {
			boolean SSOLogin = loginPage.SSO_Login(mobileAppUsername, mobileAppPassword);
			TestValidation.IsTrue(SSOLogin, "logged into the application with User " + mobileAppUsername,
					"Failed to log in");

		} else {
			boolean login = loginPage.Login(mobileAppUsername, mobileAppPassword);
			TestValidation.IsTrue(login, "logged into the application with User " + mobileAppUsername,
					"Failed to log in");

		}

		afSaved.UnlinkLocationFunctionality();

	}

	@Test(description = "Unlink specified locations from Web")
	public void TestCase_37402() throws InterruptedException, ElementNotVisibleException, ParseException, IOException {
		appiumDriver = launchMobileApp();
		mobControlActions = new ControlActions_MobileApp(appiumDriver);

		LoginPage loginPage = new LoginPage(appiumDriver);
		afSaved = new REG_AllFormsSaved(appiumDriver);

		if (mobileApp_Tenant.equals("test1.stage")) {
			boolean SSOLogin = loginPage.SSO_Login(mobileAppUsername, mobileAppPassword);
			TestValidation.IsTrue(SSOLogin, "logged into the application with User " + mobileAppUsername,
					"Failed to log in");

		} else {
			boolean login = loginPage.Login(mobileAppUsername, mobileAppPassword);
			TestValidation.IsTrue(login, "logged into the application with User " + mobileAppUsername,
					"Failed to log in");

		}

		afSaved.UnlinkSpecifiedLocationFunctionality();

	}

	@Test(description = "Verify searched data retain on respective page")
	public void TestCase_37355() throws InterruptedException, ElementNotVisibleException, ParseException, IOException {
		appiumDriver = launchMobileApp();
		mobControlActions = new ControlActions_MobileApp(appiumDriver);

		LoginPage loginPage = new LoginPage(appiumDriver);
		afSaved = new REG_AllFormsSaved(appiumDriver);

		if (mobileApp_Tenant.equals("test1.stage")) {
			boolean SSOLogin = loginPage.SSO_Login(mobileAppUsername, mobileAppPassword);
			TestValidation.IsTrue(SSOLogin, "logged into the application with User " + mobileAppUsername,
					"Failed to log in");

		} else {
			boolean login = loginPage.Login(mobileAppUsername, mobileAppPassword);
			TestValidation.IsTrue(login, "logged into the application with User " + mobileAppUsername,
					"Failed to log in");

		}

		afSaved.SearchDataRetainingValidation();

	}

	@Test(description = "Verify sort by functionality for all modules - Forms/ saved / favorite")
	public void TestCase_37356_37357_37358() throws Exception {
		appiumDriver = launchMobileApp();
		mobControlActions = new ControlActions_MobileApp(appiumDriver);

		LoginPage loginPage = new LoginPage(appiumDriver);
		afSaved = new REG_AllFormsSaved(appiumDriver);

		if (mobileApp_Tenant.equals("test1.stage")) {
			boolean SSOLogin = loginPage.SSO_Login(mobileAppUsername, mobileAppPassword);
			TestValidation.IsTrue(SSOLogin, "logged into the application with User " + mobileAppUsername,
					"Failed to log in");

		} else {
			boolean login = loginPage.Login(mobileAppUsername, mobileAppPassword);
			TestValidation.IsTrue(login, "logged into the application with User " + mobileAppUsername,
					"Failed to log in");

		} 

		afSaved.SortingValidation();

	}

	@Test(description = "Verify Forms saved On web is displayed on Tab / Phone")
	public void TestCase_37382() throws Exception {
		appiumDriver = launchMobileApp();
		mobControlActions = new ControlActions_MobileApp(appiumDriver);

		afSaved = new REG_AllFormsSaved(appiumDriver);

		afSaved.WebSavedFormsValidation();

	}

	@Test(description = "Verify Auto save functionality with offline condition")
	public void TestCase_37385() throws InterruptedException, ElementNotVisibleException, ParseException, IOException {
		appiumDriver = launchMobileApp();
		mobControlActions = new ControlActions_MobileApp(appiumDriver);

		afSaved = new REG_AllFormsSaved(appiumDriver);

		afSaved.offlineAutoSaveFunctionality();

	}

	@Test(description = "Verify by switching the tabs after the search by resource and again switching back to the same screen")
	public void TestCase_37395() throws InterruptedException, ElementNotVisibleException, ParseException, IOException {
		appiumDriver = launchMobileApp();
		mobControlActions = new ControlActions_MobileApp(appiumDriver);

		LoginPage loginPage = new LoginPage(appiumDriver);
		afSaved = new REG_AllFormsSaved(appiumDriver);

		if (mobileApp_Tenant.equals("test1.stage")) {
			boolean SSOLogin = loginPage.SSO_Login(mobileAppUsername, mobileAppPassword);
			TestValidation.IsTrue(SSOLogin, "logged into the application with User " + mobileAppUsername,
					"Failed to log in");

		} else {
			boolean login = loginPage.Login(mobileAppUsername, mobileAppPassword);
			TestValidation.IsTrue(login, "logged into the application with User " + mobileAppUsername,
					"Failed to log in");

		}

		afSaved.searchByResourceNavigateBack();

	}

	@Test(description = "Task Badge should get updated after every time window change")
	public void TestCase_37372() throws InterruptedException, ElementNotVisibleException, ParseException, IOException {
		appiumDriver = launchMobileApp();
		mobControlActions = new ControlActions_MobileApp(appiumDriver);

		LoginPage loginPage = new LoginPage(appiumDriver);
		afSaved = new REG_AllFormsSaved(appiumDriver);

		if (mobileApp_Tenant.equals("test1.stage")) {
			boolean SSOLogin = loginPage.SSO_Login(mobileAppUsername, mobileAppPassword);
			TestValidation.IsTrue(SSOLogin, "logged into the application with User " + mobileAppUsername,
					"Failed to log in");

		} else {
			boolean login = loginPage.Login(mobileAppUsername, mobileAppPassword);
			TestValidation.IsTrue(login, "logged into the application with User " + mobileAppUsername,
					"Failed to log in");

		}

		afSaved.TaskBadgeWithWindowChange();

	}
	
	@Test(description = "Verify Search By Resource Functionality on Favorites tab when navigated From \"All Forms -> Favorite\" on Footer")
	public void TestCase_47649() throws InterruptedException, ElementNotVisibleException, ParseException, IOException {
		appiumDriver = launchMobileApp();
		mobControlActions = new ControlActions_MobileApp(appiumDriver);

		LoginPage loginPage = new LoginPage(appiumDriver);
		afSaved = new REG_AllFormsSaved(appiumDriver);

		boolean login = loginPage.Login(mobileAppUsername, mobileAppPassword);
		TestValidation.IsTrue(login, 
							 "Logged into the application with User " + mobileAppUsername,
							 "Failed to log in");

		// RESOURCES AND LOCATIONS
		String resourceCategoryValue = CommonMethods.dynamicText("RCat");
		String resourceInstanceValue1 = CommonMethods.dynamicText("RInst1");

		// TO BE Created Form Details
		String formName = CommonMethods.dynamicText("API_ChkForm");
		String freeTxt1 = "FreeText1";

		TCG_FormCreation_Wrapper formCreationWrapper = new TCG_FormCreation_Wrapper();

		apiUtils = new ApiUtils();

		// DEFIING RESOURCES AND LOCATIONS
		HashMap<String, List<String>> resourceCatMap = new HashMap<String, List<String>>();
		resourceCatMap.put(resourceCategoryValue, Arrays.asList(resourceInstanceValue1));

		HashMap<String, List<String>> LocationMap = new HashMap<String, List<String>>();
		LocationMap.put(locationName, Arrays.asList(locationName));

		// USER LIST
		List<String> userList = Arrays.asList(mobileAppUsername);
		
		// CREATING and LINKING RESOURECES LOCATIONS and USERS
		formCreationWrapper.Resource_Location_CreationWrapper(resourceCatMap, LocationMap, true, userList,
				RESOURCE_TYPES.CUSTOMERS);

		// DEFINEING FIELDS
		FormFieldParams freeTextField1 = new FormFieldParams();
		freeTextField1.DPTFieldType = DPT_FIELD_TYPES.FREE_TEXT;
		freeTextField1.Name = freeTxt1;
//		freeTextField1.RepeatField = "true";

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
		fp.formElements = Arrays.asList(freeTextField1);
		fp.CustomerResources = resourceCatMap;
		logInfo("fp.formElements = " + fp.formElements);

		formCreationWrapper.API_Wrapper_Forms(fp);
		
		boolean searchByRecVerified = afSaved.searchByResourceInFavouritiesTab(formName, resourceInstanceValue1);
		TestValidation.IsTrue(searchByRecVerified, 
				"Verified Search by Resource functionality on favourities tab when navigated from All Forms to Favourities", 
				"Failed to verify Search by Resource functionality on favourities tab when navigated from All Forms to Favourities");

	}
	
	@Test(description = "Verify if Form version is edited from Web and after Refreshing on Mobile Form version should be latest")
	public void TestCase_37393() throws Exception {
			
		// RESOURCES AND LOCATIONS
		String resourceCategoryValue = CommonMethods.dynamicText("RCat");
		String resourceInstanceValue1 = CommonMethods.dynamicText("RInst1");

		// TO BE Created Form Details
		String formName = CommonMethods.dynamicText("API_ChkForm");
		String freeTxt1Name = "FreeText1";
		String freeText1NewName = "FreeText2";

		TCG_FormCreation_Wrapper formCreationWrapper = new TCG_FormCreation_Wrapper();

		apiUtils = new ApiUtils();

		// DEFIING RESOURCES AND LOCATIONS
		HashMap<String, List<String>> resourceCatMap = new HashMap<String, List<String>>();
		resourceCatMap.put(resourceCategoryValue, Arrays.asList(resourceInstanceValue1));

		HashMap<String, List<String>> LocationMap = new HashMap<String, List<String>>();
		LocationMap.put(locationName, Arrays.asList(locationName));

		// USER LIST
		List<String> userList = Arrays.asList(mobileAppUsername);
		
		// CREATING and LINKING RESOURECES LOCATIONS and USERS
		formCreationWrapper.Resource_Location_CreationWrapper(resourceCatMap, LocationMap, true, userList,
				RESOURCE_TYPES.CUSTOMERS);

		// DEFINEING FIELDS
		FormFieldParams freeTextField1 = new FormFieldParams();
		freeTextField1.DPTFieldType = DPT_FIELD_TYPES.FREE_TEXT;
		freeTextField1.Name = freeTxt1Name;

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
		fp.formElements = Arrays.asList(freeTextField1);
		fp.CustomerResources = resourceCatMap;
		logInfo("fp.formElements = " + fp.formElements);

		LinkedHashMap<String, String> shortNames = formCreationWrapper.API_Wrapper_Forms(fp);
		
		// Form details
		appiumDriver = launchMobileApp();
		mobControlActions = new ControlActions_MobileApp(appiumDriver);
		LoginPage loginPage = new LoginPage(appiumDriver);
		HomePage homePage = new HomePage(appiumDriver);
		
		boolean login = loginPage.Login(mobileAppUsername, mobileAppPassword);
		TestValidation.IsTrue(login, "logged into the application with User " + mobileAppUsername,
					"Failed to log in");
		
		homePage.SearchForm(formName);

		// Version Check
		UpdatedFormVersionPage UpdatedFormVersionPageObj = new UpdatedFormVersionPage(appiumDriver);
		TestValidation.IsTrue(UpdatedFormVersionPageObj.FirstVersion().equals("1.0"), 
				  "Verified Form Version as 1.0 on Mobile app", 
				  "Failed to verify Form Version as 1.0 on Mobile app");
		// Browser code
		WebDriver driver1 = launchbrowser();
		try {
			controlActions = new ControlActions(driver1);
			controlActions.getUrl(applicationUrl);
			FormDesignerPage fdp = new FormDesignerPage(driver1);
			FSQABrowserPage fbp = new FSQABrowserPage (driver1);
			lp = new com.project.safetychain.webapp.pageobjects.LoginPage(driver1);
			hp = lp.performLogin(mobileAppUsername, mobileAppPassword);
			if (hp.error) {
				TCGFailureMsg = "Could NOT login to application";
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			} else {
				TestValidation.IsTrue(true, "========== Launched Browser and Logged Into SC WEB Application =========",
						"Failed to login to SC WEB Application");
			}

			boolean navigate = fbp.navigateToFSQATab(FSQATAB.FORMS);
			TestValidation.IsTrue(navigate, 
					"Navigated to FSQABrowser>FormsTab", 
					"Failed to navigate to FSQABrowser>Forms Tab");

			boolean applyfilter = fbp.openAndApplySettingsForColumn(COLUMNHEADER.FORMNAME, COLUMNSETTING.FILTER,formName);
			TestValidation.IsTrue(applyfilter, 
					"Applied Filter on" + COLUMNHEADER.FORMNAME, 
					"Failed to apply filter on" + COLUMNHEADER.FORMNAME);
			
			String version = fbp.getGridValueForColumn(COLUMNHEADER.VERSION);
			
			TestValidation.IsTrue(version.equals("1.0"), 
					  			 "Verified form version to be 1.0 on Web app", 
					  			 "Failed to verify form version as 1.0 on Web app");
			
			boolean editform = fbp.editSelectForm(formName);
			TestValidation.IsTrue(editform, 
							     "Form Edited", 
								 "Failed to edit form");

			boolean selectField = fdp.selectField(shortNames.get(freeTxt1Name));
			TestValidation.IsTrue(selectField, 
								  "Selected form field " + shortNames.get(freeTxt1Name), 
								  "Failed to select form field " + shortNames.get(freeTxt1Name));
			
			boolean changeFieldName = fdp.setShortNameInputFieldValue(freeText1NewName);
			TestValidation.IsTrue(changeFieldName, 
								  "Changed Free Text short Name to - " + freeText1NewName , 
								  "Failed t change Free Text short Name - " + freeText1NewName);
			
			boolean clivkNextBtn = fdp.clickOnNextButton(fdp.ReleaseFormPg, "Release Form");
			TestValidation.IsTrue(clivkNextBtn, "Clicked on next button and Navigated to Release Page",
					"Could not click on next button and not Navigate to Release Page");

			boolean releaseForm = fdp.releaseForm(formName);
			TestValidation.IsTrue(releaseForm, "Successfully released form -" + formName,
					"Could Not release form -" + formName);
			
			hp.clickHomepageLink();
			
			boolean navigate1 = fbp.navigateToFSQATab(FSQATAB.FORMS);
			TestValidation.IsTrue(navigate1, "Navigated to FSQABrowser>FormsTab",
					"Failed to navigate to FSQABrowser>Forms Tab");

			String version2 = fbp.getGridValueForColumn(COLUMNHEADER.VERSION);
			TestValidation.IsTrue(version2.equals("2.0"), 
					"Verified version 2.0 on Web app, after field short name change",
					"Failed to verifiy version 2.0 on Web app after field short name change");
			
			boolean logOutFromSC = hp.userLogout();
			TestValidation.IsTrue(logOutFromSC, "User successfully LOGGED OUT from Safety Chain application",
					"Failed to Logout user from Safety Chain application");
			
			TestValidation.IsTrue(true, "========== Logged Out off SC WEB Application =========",
					"Failed to logout off SC WEB Application");

		} finally {
			driver1.close();
		}

		// Version Check
		homePage.SearchForm(formName);
		ControlActions_MobileApp.swipeScreen("DOWN");
		String mobVersionAfterUpdate = UpdatedFormVersionPageObj.FirstVersion();
		Boolean CompareVersion = UpdatedFormVersionPageObj.CompareVersion(mobVersionAfterUpdate,"2.0");
		TestValidation.IsTrue(CompareVersion, 
							 "Verified version is updated to 2.0 on mobile app", 
							 "Version NOT updated to 2.0 on mobile app");

	}

	@Test(description = "Verify Saved sync between web & other device with correct data")
	public void TestCase_37432() throws Exception {

		int savedCountBefore, savedCountAfter;
		
		appiumDriver = launchMobileApp();
		SavedPageObj = new SavedPage(appiumDriver);
		afSaved = new REG_AllFormsSaved(appiumDriver);
		String FormName = afSaved.APIInitiation();
		
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
		
		//BROWSER
		WebDriver driver1 = launchbrowser();
		try {
		fbp = new FSQABrowserPage(driver1);
		fop = new FormOperations(driver1);
		
		lp = new com.project.safetychain.webapp.pageobjects.LoginPage(driver1);
		controlActions = new ControlActions(driver1);
		controlActions.getUrl(applicationUrl);

		hp = lp.performLogin(adminUsername, adminPassword);
		if (hp.error) {
			TCGFailureMsg = "Could NOT login to application";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
		
		//hp.clickFSQABrowserMenu();
		boolean navigate = fbp.selectResourceDropDownandNavigate("CUSTOMERS", "Forms");
		TestValidation.IsTrue(navigate, "Navigated to FSQABrowser>FormsTab",
				"Failed to navigate to FSQABrowser>Forms Tab");

		boolean applyfilter = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, FormName);
		TestValidation.IsTrue(applyfilter, "Applied Filter on" + COLUMNHEADER.FORMNAME,
				"Failed to apply filter on" + COLUMNHEADER.FORMNAME);
		threadsleep(5000);

		FormDetails fd = new FormDetails();
		HashMap<String, List<String>> map1 = new HashMap<String, List<String>>();
		map1.put(REG_AllFormsSaved.Numeric, Arrays.asList("3"));
		map1.put(REG_AllFormsSaved.chkFreeTxt, Arrays.asList("FreeText"));
		map1.put(REG_AllFormsSaved.ParaTxt, Arrays.asList("Paragraph"));
		map1.put(REG_AllFormsSaved.chkSelectOne, Arrays.asList("Good"));
//		map1.put(REG_AllFormsSaved.chkDate, Arrays.asList("10/19/2025"));
//		map1.put(REG_AllFormsSaved.chkDateTime, Arrays.asList("10/14/2025 1:59"));
//		map1.put(REG_AllFormsSaved.SelectMultiple, Arrays.asList("20"));
		
		fd.fieldDetails = map1;
		fd.resourceName = REG_AllFormsSaved.resourceInstanceValue1;
		fd.locationName = REG_AllFormsSaved.locationInstanceValue1;
		fd.isSubmit = false;

		savedCountBefore = Integer.parseInt(afSaved.SavedBadgeCount.getText().trim());

		boolean submit1 = fop.submitData(fd);
		TestValidation.IsTrue(submit1, "Entered data and Saved form successfully",
				"Could NOT Enter data and Save form");

		}finally {
			driver1.close();
		}
		//=========BROWSER END
		
		
		//=========== MOBILE START
		boolean clickSubmissionSub = homePage.ClickSubMenu(homePage.saveSubMenu);
		TestValidation.IsTrue(clickSubmissionSub, 
				"Clicked on Save subMenu Successfully",
				"Failed to click Save subMenu");
		
		ControlActions_MobileApp.swipeScreen("DOWN");
		Thread.sleep(10000);
		
		savedCountAfter = Integer.parseInt(afSaved.SavedBadgeCount.getText().trim());
		
		TestValidation.IsTrue(savedCountAfter == savedCountBefore+1, 
							  "Successfully Verified Save Count Updated", 
							  "Failed to verify saved count update");
		
		boolean searchForm = homePage.SearchForm(FormName);
		TestValidation.IsTrue(searchForm, "Form Searched successfully", "Failed to search Form");
		boolean clickForm = homePage.ClickForm(FormName);
		TestValidation.IsTrue(clickForm, "Clicked on form Successfully", "Failed to click Form");
		appiumDriver.hideKeyboard();

		LinkedHashMap<String, String> FieldTypeValues = new LinkedHashMap<String, String>();
		FieldTypeValues.put(REG_AllFormsSaved.Numeric, "3");
		FieldTypeValues.put(REG_AllFormsSaved.ParaTxt, "Paragraph");
		FieldTypeValues.put(REG_AllFormsSaved.chkFreeTxt, "FreeText");
		FieldTypeValues.put(REG_AllFormsSaved.chkSelectOne, "GOOD");
//		FieldTypeValues.put(REG_AllFormsSaved.chkDate, "10/19/2025");
//		FieldTypeValues.put(REG_AllFormsSaved.chkDateTime, "10/14/2025 1:59 IST");
//		FieldTypeValues.put(REG_AllFormsSaved.SelectMultiple, "20");
			
		boolean verifyUpdatedValue = SavedPageObj.verifyFieldValues(FieldTypeValues);
		TestValidation.IsTrue(verifyUpdatedValue, "VERIFIED updated value for  all fields",
				"Failed to verify updated values for all fields");

	}

	@AfterMethod(alwaysRun = true)
	public void closeAppium() throws InterruptedException {

//		appiumDriver.closeApp();
	}

}
