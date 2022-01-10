package com.project.safetychain.mobileapp.testcases;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.project.safetychain.api.models.support.Support.DPT_FIELD_TYPES;
import com.project.safetychain.api.models.support.Support.DPT_FORM_TYPES;
import com.project.safetychain.api.models.support.Support.FormFieldParams;
import com.project.safetychain.api.models.support.Support.FormParams;
import com.project.safetychain.api.models.support.Support.RESOURCE_TYPES;
import com.project.safetychain.api.models.support.Support.TaskParams;
import com.project.safetychain.api.models.support.TCG_FormCreation_Wrapper;
import com.project.safetychain.api.models.support.TCG_TaskCreation_Wrapper;
import com.project.safetychain.mobileapp.pageobjects.HomePage;
import com.project.safetychain.mobileapp.pageobjects.HomePageLocators;
import com.project.safetychain.mobileapp.pageobjects.LoginPage;
import com.project.safetychain.mobileapp.pageobjects.REG_AllFormsSaved;
import com.project.safetychain.mobileapp.pageobjects.REG_InboxTaskPage;
import com.project.safetychain.mobileapp.pageobjects.SavedPage;
import com.project.safetychain.mobileapp.pageobjects.UploadImage;
import com.project.safetychain.webapp.pageobjects.CommonPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserFormsPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserRecordsPage;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FormDesignParams;
import com.project.safetychain.webapp.pageobjects.ManageLocationPage;
import com.project.safetychain.webapp.pageobjects.ManageResourcePage;
import com.project.safetychain.webapp.pageobjects.ResourceDesignerPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.COLUMNHEADER;
import com.project.safetychain.webapp.testcases.support.FormOperations;
import com.project.safetychain.webapp.testcases.support.FormOperations.FormDetails;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.utilities.ApiUtils;
import com.project.utilities.CommonMethods;
import com.project.utilities.ControlActions;
import com.project.utilities.ControlActions_MobileApp;

import io.appium.java_client.pagefactory.AndroidFindBy;

public class TCG_REG_InboxTaskPage extends TestBase {

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
	REG_InboxTaskPage inbox;
	public WebDriver driver;
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

	@Test(description = "Verify Page Title,Footer,Latest Task is presnt in Inbox and Task Count is updated Accordingly")
	public void TestCase_37377_37380_37408_37416_37332_37334()
			throws InterruptedException, ElementNotVisibleException, ParseException {

		appiumDriver = launchMobileApp();
		inbox = new REG_InboxTaskPage(appiumDriver);
		mobControlActions = new ControlActions_MobileApp(appiumDriver);
		LoginPage loginPage = new LoginPage(appiumDriver);
		if (mobileApp_Tenant.equals("test1.stage")) {
			boolean SSOLogin = loginPage.SSO_Login(mobileAppUsername, mobileAppPassword);
			TestValidation.IsTrue(SSOLogin, "logged into the application with User " + mobileAppUsername,
					"Failed to log in");

		} else {
			Thread.sleep(20000);
			boolean login = loginPage.Login(mobileAppUsername, mobileAppPassword);
			TestValidation.IsTrue(login, "logged into the application with User " + mobileAppUsername,
					"Failed to log in");

		}

		inbox.InboxHeaderFooterTaskBadgeValidation();

	}

	@Test(description = "Verify Task Save & Verify Task already assigned with location & resource & Saved Task Submission")
	public void TestCase_37266_37333_37420_37407() throws InterruptedException, ElementNotVisibleException, ParseException {

		appiumDriver = launchMobileApp();
		inbox = new REG_InboxTaskPage(appiumDriver);
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

		inbox.TaskSaveFunctionality();

	}

	@Test(description = " Verify user is able to submit the task successfully with attachments in Online Mode.")
	public void TestCase_37387() throws InterruptedException, ElementNotVisibleException, ParseException {

		appiumDriver = launchMobileApp();
		inbox = new REG_InboxTaskPage(appiumDriver);
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

		inbox.TaskSubmissionFunctionality();

	}

	@Test(description = "Verify Task Submission in offline mode with attachment")
	public void TestCase_37388_37389() throws InterruptedException, ElementNotVisibleException, ParseException {

		appiumDriver = launchMobileApp();
		inbox = new REG_InboxTaskPage(appiumDriver);
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

		inbox.OfflineTaskSubmissionFunctionality();

	}

	@Test(description = "Verify the task gets removed from the inbox screen if user unlinks the location which was assigned by creating task")
	public void TestCase_37404() throws InterruptedException, ElementNotVisibleException, ParseException {

		appiumDriver = launchMobileApp();
		inbox = new REG_InboxTaskPage(appiumDriver);
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

		inbox.UnlinkLocationFunctionalityTaskValidation();

	}

	@Test(priority=11,description = "Verify Task search and Data Retained - all 3 modules ( Status, Work group & Due by )")
	public void TestCase_37263_37433_47707() throws InterruptedException, ElementNotVisibleException, ParseException {

		appiumDriver = launchMobileApp();
		inbox = new REG_InboxTaskPage(appiumDriver);
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

		inbox.TaskSearchAndDataRetainValidation();

	}
	
	@Test(description = "Verify Task types - No due / Today / Later / Past sections")
	public void TestCase_37262_47706() throws InterruptedException, ElementNotVisibleException, ParseException {

		appiumDriver = launchMobileApp();
		inbox = new REG_InboxTaskPage(appiumDriver);
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

		inbox.StatusFilterValidate();

	}

	@Test(description = "Verify Work group & status & Due by respective task listing")
	public void TestCase_37335_WG() throws InterruptedException, ElementNotVisibleException, ParseException {

		appiumDriver = launchMobileApp();
		inbox = new REG_InboxTaskPage(appiumDriver);
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

		inbox.WorkgroupFilterValidate();

	}

	@Test(description = "Verify Inbox filter - Assigned to / status / Due by & Verify Clear filter")
	public void TestCase_37264_37259() throws InterruptedException, ElementNotVisibleException, ParseException {

		appiumDriver = launchMobileApp();
		inbox = new REG_InboxTaskPage(appiumDriver);
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

		inbox.scrollingBetweenFilterOptionsWithSortValidation();

	}

	@Test(description = "Verify Work group & status & Due by respective task listing")
	public void TestCase_37335_DB() throws InterruptedException, ElementNotVisibleException, ParseException {

		appiumDriver = launchMobileApp();
		inbox = new REG_InboxTaskPage(appiumDriver);
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
		inbox.DueByFilterValidate();

	}

	@Test(description = "Verify Task already assigned with location but not assigned resource")
	public void TestCase_37421() throws InterruptedException, ElementNotVisibleException, ParseException {

		appiumDriver = launchMobileApp();
		inbox = new REG_InboxTaskPage(appiumDriver);
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

		inbox.TaskWithoutResource();

	}

	@Test(description = " Verify Close filter using cross icon")
	public void TestCase_37260() throws InterruptedException, ElementNotVisibleException, ParseException {

		appiumDriver = launchMobileApp();
		inbox = new REG_InboxTaskPage(appiumDriver);
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

		inbox.ClosefilterCrossIcon();

	}

	@Test(description = "Verify Header - Task Badge count should get updated if new task assigned directly from forms or from task scheduler @ Task submission")
	public void TestCase_37378_37390() throws InterruptedException, ElementNotVisibleException, ParseException {

		appiumDriver = launchMobileApp();
		inbox = new REG_InboxTaskPage(appiumDriver);
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

		inbox.TaskViaTaskScheduler();

	}

	@Test(description = "Verify Header - Task Badge should get updated after every time window change")
	public void TestCase_37376() throws Exception {

		appiumDriver = launchMobileApp();
		inbox = new REG_InboxTaskPage(appiumDriver);
		mobControlActions = new ControlActions_MobileApp(appiumDriver);
		LoginPage loginPage = new LoginPage(appiumDriver);
		TCG_SMK_Inbox_Flow inbx = new TCG_SMK_Inbox_Flow();
		if (mobileApp_Tenant.equals("test1.stage")) {
			boolean SSOLogin = loginPage.SSO_Login(mobileAppUsername, mobileAppPassword);
			TestValidation.IsTrue(SSOLogin, "logged into the application with User " + mobileAppUsername,
					"Failed to log in");

		} else {
			boolean login = loginPage.Login(mobileAppUsername, mobileAppPassword);
			TestValidation.IsTrue(login, "logged into the application with User " + mobileAppUsername,
					"Failed to log in");

		}

		inbx.TestCase_36799();

	}

	@Test(description = "47705||Andriod- Inbox- Verify that task is getting Sync when user Saves the task on one device and Opens the task on Other device from inbox screen")
	public void TestCase_47705() throws Exception {
		String formName = CommonMethods.dynamicText("API_Form");
		String taskName = CommonMethods.dynamicText("API_Task");
		String wrkgpName = CommonMethods.dynamicText("API_WG");

		TCG_FormCreation_Wrapper formCreationWrapper = new TCG_FormCreation_Wrapper();
		TCG_TaskCreation_Wrapper taskCreationWrapper = new TCG_TaskCreation_Wrapper();

		apiUtils = new ApiUtils();

		resourceCategoryValue = CommonMethods.dynamicText("RCat");
		resourceInstanceValue1 = CommonMethods.dynamicText("RInst1");

		// Field Names
		String chkFreeTxt = "FreeText";
		String ParaTxt = "Paragraph";
		String chkSelectOne = "SelectOne";
		String Numeric = "Numeric";
		String chkDate = "Date";
		String chkDateTime = "Date&Time";
		String SelectMultiple = "MutiSelect";

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

		FormFieldParams dateField = new FormFieldParams();
		dateField.DPTFieldType = DPT_FIELD_TYPES.DATE;
		dateField.Name = chkDate;
		
		FormFieldParams dateTimeField = new FormFieldParams();
		dateTimeField.DPTFieldType = DPT_FIELD_TYPES.DATE_TIME;
		dateTimeField.Name = chkDateTime;
		
		FormFieldParams selectOneField = new FormFieldParams();
		selectOneField.DPTFieldType = DPT_FIELD_TYPES.SELECT_ONE;
		selectOneField.Name = chkSelectOne;
		selectOneField.SelectOptions = Arrays.asList("GOOD", "BAD");

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
		fp.FormName = formName;
		logInfo("Form Name  = " + formName);
		fp.type = DPT_FORM_TYPES.CHECK;
		fp.ResourceType = RESOURCE_TYPES.CUSTOMERS;
		fp.ResourceCategoryNm = resourceCategoryValue;
		fp.ResourceInstanceNm = resourceInstanceValue1;
		fp.formElements = Arrays.asList(numericField, paraTextField, freeTextField, dateField, dateTimeField, selectOneField, selectMultipleField);
		logInfo("fp.formElements = " + fp.formElements);

		HashMap<String, List<String>> resourceCatMap = new HashMap<String, List<String>>();
		resourceCatMap.put(resourceCategoryValue, Arrays.asList(resourceInstanceValue1));
		fp.CustomerResources = resourceCatMap;

		HashMap<String, List<String>> LocationMap = new HashMap<String, List<String>>();
		LocationMap.put(locationName, Arrays.asList(locationName));

		// Location, Resource Creation and linking
		List<String> userList = Arrays.asList(mobileAppUsername);

		HashMap<String, String> locResMap = formCreationWrapper.Resource_Location_CreationWrapper(resourceCatMap,
				LocationMap, true, userList, fp.ResourceType);

		String locInstId = locResMap.get(locationName);
		String ResInstId = locResMap.get(resourceInstanceValue1);

		// Form Creation and Validation call
		formCreationWrapper.API_Wrapper_Forms(fp);

		// Task Creation
		String dueDate = ControlActions_MobileApp.AddDaystoToddaysDateInGivenFormat("MM/dd/YYYY HH:mm", 2);
		TaskParams tp = new TaskParams();
		tp.FormId = formId;
		tp.DueBy = dueDate;
		tp.FormName = formName;
		tp.LocationInstanceNm = locationName;
		tp.ResourceInstanceNm = resourceInstanceValue1;
		tp.TaskName = taskName;
		tp.WorkGroupName = wrkgpName;
		tp.LocationInstanceId = locInstId;
		tp.ResourceInstanceId = ResInstId;
		tp.UserName = mobileAppUsername;

		taskCreationWrapper.create_Link_workGroup_Wrapper(tp);
		taskCreationWrapper.create_Task_Wrapper(tp);
		
		appiumDriver = launchMobileApp();
		inbox = new REG_InboxTaskPage(appiumDriver);
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

		HomePage homePage = new HomePage(appiumDriver);
		SavedPage SavedPageObj = new SavedPage(appiumDriver);
		
		boolean clickInboxMenu = homePage.ClickSubMenu(homePage.inboxSubMenu);
		TestValidation.IsTrue(clickInboxMenu, "Clicked on Inbox Sub Menu", "Failed to click Inbox Submenu");

		boolean searchForm = homePage.SearchTask(taskName);
		TestValidation.IsTrue(searchForm, 
							  "Latest Created Task is present in Inbox",
							  "Latest Created Task is not present in Inbox");
		
		WebElement task = ControlActions_MobileApp.perform_GetElementByXPath(HomePageLocators.Form_Name,"FormNameLocator", taskName);
		ControlActions_MobileApp.waitForVisibility(task, 60);
		ControlActions_MobileApp.click(task);
		logInfo("Clicked on Task = " + taskName);
		Thread.sleep(10000);

		Boolean txtNumeric = SavedPageObj.enterFieldValue(SavedPageObj.NumericTxt, "8");
		TestValidation.IsTrue(txtNumeric, "Numeric Field value entered successfully", "Failed to enter field value");

		Boolean txtPara = SavedPageObj.enterFieldValue(SavedPageObj.paragraphTxt, "Para");
		TestValidation.IsTrue(txtPara, "Numeric Field value entered successfully", "Failed to enter field value");

		Boolean txtFT = SavedPageObj.enterFieldValue(SavedPageObj.freeText, "FreeText");
		TestValidation.IsTrue(txtFT, "Numeric Field value entered successfully", "Failed to enter field value");

		String date = ControlActions_MobileApp.AddDaystoToddaysDate(0);
		Boolean Sedate = SavedPageObj.clickFieldType(homePage.datePicker, date);
		TestValidation.IsTrue(Sedate, "Date Selected successfully", "Failed to select Date value");
		String dateSelected = ControlActions_MobileApp.AddDaystoToddaysDateInGivenFormat("M/d/YY", 0);
		logInfo("Date Field Value = " + dateSelected);

		Boolean SedateTime = SavedPageObj.clickFieldType(homePage.dateTimePicker, date);
		TestValidation.IsTrue(SedateTime, "DateTime value selected successfully", "Failed to Select DateTime value");
		String dateTimeSel = homePage.dateTimePicker.getText();
		logInfo("Date&Time Field Value = " + dateTimeSel);

		Boolean selectOne = SavedPageObj.selectOneOrMultipleFieldValues("SelectOne", "GOOD");
		TestValidation.IsTrue(selectOne, "Numeric Field value entered successfully", "Failed to enter field value");
		
		Boolean SeMultiple = SavedPageObj.selectOneOrMultipleFieldValues("MutiSelect", "10");
		TestValidation.IsTrue(SeMultiple, "Value selected for SelectMultiple Field Type",
				"Failed to select MultiSelect value");
		Boolean SeMultiple1 = SavedPageObj.selectOneOrMultipleFieldValues("MutiSelect", "30");
		TestValidation.IsTrue(SeMultiple1, "Value selected for SelectMultiple Field Type",
				"Failed to select MultiSelect value");

		ControlActions_MobileApp.WaitforelementToBeClickable(inbox.formSave);
		inbox.formSave.click();

		// Checking in Saved Forms
		homePage.ClickSubMenu(homePage.saveSubMenu);
		ControlActions_MobileApp.swipeScreen("DOWN");

		// Checking details of saved form
		boolean searchSavedTask = homePage.SearchForm(formName);
		TestValidation.IsTrue(searchSavedTask, 
							 "Saved task is present in Saved Forms",
							 "Saved Task is not present in Saved Forms");
		
		boolean clickSavedTask = homePage.ClickForm(formName);
		logInfo("Clicked on Task = " + formName);
		TestValidation.IsTrue(clickSavedTask, "Clicked on Saved Task", "Failed to click on saved task");

		LinkedHashMap<String, String> FieldTypeValues = new LinkedHashMap<String, String>();
		FieldTypeValues.put(Numeric, "8");
		FieldTypeValues.put(ParaTxt, "Para");
		FieldTypeValues.put(chkFreeTxt, "FreeText");
		FieldTypeValues.put(chkDate, dateSelected);
		FieldTypeValues.put(chkDateTime, dateTimeSel);
		FieldTypeValues.put(chkSelectOne, "GOOD");
		FieldTypeValues.put(SelectMultiple, "10");
		FieldTypeValues.put(SelectMultiple, "30");

		boolean verifyUpdatedValue = SavedPageObj.verifyFieldValues2(FieldTypeValues);
		TestValidation.IsTrue(verifyUpdatedValue,
							  "Form is saved and value is verified for fields",
							  "Failed to verify updated values for fields");
		
		//DEVICE 2 - WEB BROWSER =========================================================================
		WebDriver driver = launchbrowser();
		controlActions = new ControlActions(driver);
		controlActions.getUrl(applicationUrl);
		try {
		lp = new com.project.safetychain.webapp.pageobjects.LoginPage(driver);
		hp = lp.performLogin(adminUsername, adminPassword);
		if (hp.error) {
			TCGFailureMsg = "Could NOT login to application";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
		fbp = new FSQABrowserPage(driver); 
		FormOperations formoperations = new FormOperations(driver);
		boolean navigate = fbp.selectResourceDropDownandNavigate("CUSTOMERS", "Forms");
		TestValidation.IsTrue(navigate, "Navigated to FSQABrowser>FormsTab",
				"Failed to navigate to FSQABrowser>Forms Tab");
		threadsleep(5000);
		
		boolean openForm = fbp.openFormInSavedForms(formName);
		TestValidation.IsTrue(openForm, 
							 "Selected & opened Saved form - "+formName, 
							 "Could NOT open saved form - "+formName);
		
		LocalDate currentdate = LocalDate.now();
		int currentDay = currentdate.getDayOfMonth();
		String dateMinus1;
		if(currentDay<=9)
			 dateMinus1 = ControlActions_MobileApp.AddDaystoToddaysDateInGivenFormat("MM/d/yyyy", -1);
		else
			 dateMinus1 = ControlActions_MobileApp.AddDaystoToddaysDateInGivenFormat("MM/dd/yyyy", -1);
		
		String CurrentDate = ControlActions_MobileApp.AddDaystoToddaysDateInGivenFormat("MM/dd/yyyy", 0);

		String[] time = dateTimeSel.split("\\s");
		String dateTimeNew = CurrentDate + " " + time[1];

		
		boolean verifySavedNumField = formoperations.verifyFieldValue(Numeric, "8");
		boolean verifySavedFreeTxtField = formoperations.verifyFieldValue(chkFreeTxt, "FreeText");
		boolean verifySavedParaField = formoperations.verifyParagraphTextFieldValue(ParaTxt, "Para");
		boolean verifySavedDateField = formoperations.verifyFieldValue(chkDate, dateMinus1);
		boolean verifySavedDateNTimeField = formoperations.verifyFieldValue(chkDateTime,dateTimeNew);
		boolean verifySavedSelOField = formoperations.verifyFieldValue(chkSelectOne, "GOOD");
		boolean verifySavedSelMField = formoperations.verifySelectMultipleFieldValues(SelectMultiple,Arrays.asList("10","30"));
		TestValidation.IsTrue(verifySavedNumField && verifySavedFreeTxtField && verifySavedParaField && verifySavedDateField
								&& verifySavedDateNTimeField && verifySavedSelOField && verifySavedSelMField, 
			  				  "Verified the saved data for all the fields on web" ,
			  			  	  "Failed to Verify the saved data");
		}finally {
			driver.close();
		}
	}
	
	@AfterMethod(alwaysRun = true)
	public void closeAppium() throws InterruptedException {

		appiumDriver.closeApp();
	}

}