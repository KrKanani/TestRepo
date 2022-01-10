package com.project.safetychain.mobileapp.testcases;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
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
import com.project.safetychain.api.models.support.Support.TaskParams;
import com.project.safetychain.api.models.support.TCG_FormCreation_Wrapper;
import com.project.safetychain.api.models.support.TCG_TaskCreation_Wrapper;
import com.project.safetychain.mobileapp.pageobjects.HomePage;
import com.project.safetychain.mobileapp.pageobjects.LoginPage;
import com.project.safetychain.mobileapp.pageobjects.SavedPage;
import com.project.safetychain.mobileapp.pageobjects.SavedPageLocators;
import com.project.safetychain.mobileapp.pageobjects.TaskPage;
import com.project.safetychain.mobileapp.pageobjects.UploadImage;
import com.project.safetychain.webapp.pageobjects.CommonPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserFormsPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.COLUMNHEADER;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.TASKDETAILS;
import com.project.safetychain.webapp.pageobjects.FSQABrowserRecordsPage;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FormDesignParams;
import com.project.safetychain.webapp.pageobjects.ManageLocationPage;
import com.project.safetychain.webapp.pageobjects.ManageResourcePage;
import com.project.safetychain.webapp.pageobjects.ResourceDesignerPage;
import com.project.safetychain.webapp.pageobjects.WorkGroupsPage;
import com.project.safetychain.webapp.testcases.support.FormOperations;
import com.project.safetychain.webapp.testcases.support.FormOperations.FormDetails;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.utilities.ApiUtils;
import com.project.utilities.CommonMethods;
import com.project.utilities.ControlActions;
import com.project.utilities.ControlActions_MobileApp;

import io.appium.java_client.pagefactory.AndroidFindBy;

public class TCG_SMK_Inbox_Flow extends TestBase {
	ControlActions_MobileApp mobControlActions;
	ApiUtils apiUtils = null;
	FormDesignerPage formDesignerPage;
	ControlActions controlActions;
	FSQABrowserPage fbp;
	FSQABrowserRecordsPage fbrp;
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
	TASKDETAILS tsd;
	WorkGroupsPage wgp;

	public static String locationCategoryValue;
	public static String locationInstanceValue1;
	public static String locationInstanceValue2;
	public static String resourceCategoryValue;
	public static String resourceInstanceValue1;
	public static String resourceInstanceValue2;
	public static String chkFreeTxt, ParaTxt, chkSelectOne, SelectMultiple, Numeric, chkDate, chkTime, chkDateTime;
	public static String formtaskname = "Automation_CheckForm_17.02_09.27.05";
	public static String assignedTo = "ACV 1st Shift Techs";
	public static String Status = "Past Due";
	public static String workGroupName;
	public static String taskType = "DUELATER";

	public static String formName = "Automation_CheckForm_17.02_09.27.05";
	public static String ResourceName = "RInst1_17.02_09.28.21";
	public static String LocationName = "LInst1_17.02_09.28.21";
	String formtype = "Check";
	String modifiedby;
	public static String FormName;
	public static String TaskName;
	public static String WGName;

	public static final String fresult = "com.safetychain.SCM.M2:id/resultView_num";
	@AndroidFindBy(id = fresult)
	public WebElement NTxt;

	@BeforeClass(alwaysRun = true)
	public void groupInit() {
		try {

			FormName = CommonMethods.dynamicText("API_Form");
			TaskName = CommonMethods.dynamicText("API_Task");
			WGName = CommonMethods.dynamicText("API_WG");

			TCG_FormCreation_Wrapper formCreationWrapper = new TCG_FormCreation_Wrapper();
			TCG_TaskCreation_Wrapper taskCreationWrapper = new TCG_TaskCreation_Wrapper();

			apiUtils = new ApiUtils();

			locationCategoryValue = CommonMethods.dynamicText("LCat");
			locationInstanceValue1 = CommonMethods.dynamicText("LInst1");
			locationInstanceValue2 = CommonMethods.dynamicText("LInst2");
			resourceCategoryValue = CommonMethods.dynamicText("RCat");
			resourceInstanceValue1 = CommonMethods.dynamicText("RInst1");
			resourceInstanceValue2 = CommonMethods.dynamicText("RInst2");

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

			// Location, Resource Creation and linking
			List<String> userList = Arrays.asList(mobileAppUsername02, mobileAppUsername);

			HashMap<String, String> locResMap = formCreationWrapper.Resource_Location_CreationWrapper(resourceCatMap,
					LocationMap, true, userList, fp.ResourceType);

			String locInstId = locResMap.get(locationInstanceValue1);
			String ResInstId = locResMap.get(resourceInstanceValue1);

			// Form Creation and Validation call
			formCreationWrapper.API_Wrapper_Forms(fp);

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

			taskCreationWrapper.create_Link_workGroup_Wrapper(tp);
			taskCreationWrapper.create_Task_Wrapper(tp);

			// Multiple task Creation

			List<String> dueByLst = new ArrayList<String>();

			dueByLst.add(ControlActions_MobileApp.AddDaystoToddaysDateInGivenFormat("MM/dd/YYYY HH:mm", -2));
			dueByLst.add(ControlActions_MobileApp.AddDaystoToddaysDateInGivenFormat("MM/dd/YYYY HH:mm", -4));
			dueByLst.add(ControlActions_MobileApp.AddDaystoToddaysDateInGivenFormat("MM/dd/YYYY HH:mm", 7));

			for (int i = 0; i < dueByLst.size(); i++) {
				tp.DueBy = dueByLst.get(i);
				taskCreationWrapper.create_Task_Wrapper(tp);
			}

			driver = launchbrowser();

			formDesignerPage = new FormDesignerPage(driver);
			locations = new ManageLocationPage(driver);
			manageResource = new ManageResourcePage(driver);
			fbp = new FSQABrowserPage(driver);
			fbForms = new FSQABrowserFormsPage(driver);
			resourceDesigner = new ResourceDesignerPage(driver);
			mainPage = new CommonPage(driver);
			fop = new FormOperations(driver);
			fbrp = new FSQABrowserRecordsPage(driver);

			controlActions = new ControlActions(driver);
			lp = new com.project.safetychain.webapp.pageobjects.LoginPage(driver);
			controlActions.getUrl(applicationUrl);
			hp = lp.performLogin(adminUsername, adminPassword);
			if (hp.error) {
				TCGFailureMsg = "Could NOT login to application";
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test(description = "Verify Latest Task is displayed after navigating to Inbox screen and task submit functionality", priority = 0)

	public void TestCase_36801_37044() throws Exception {
		appiumDriver = launchMobileApp();
		SavedPage SavedPageObj = new SavedPage(appiumDriver);
		UploadImage oUploadImage = new UploadImage(appiumDriver);
		TaskPage InboxPageObj = new TaskPage(appiumDriver);
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

		Thread.sleep(10000);
		homePage.ClickSubMenu(homePage.inboxSubMenu);

		boolean searchForm = homePage.SearchTask(TaskName);
		TestValidation.IsTrue(searchForm, "Task Searched successfully", "Failed to search Task");
		boolean clickTask = InboxPageObj.ClickTask(TaskName);
		TestValidation.IsTrue(clickTask, "Task Searched successfully", "Failed to search Task");
		boolean txtNumeric = SavedPageObj.enterFieldValue(SavedPageObj.NumericTxt, "8");
		TestValidation.IsTrue(txtNumeric, "Numeric Field value entered successfully", "Failed to enter field value");
		boolean txtPara = SavedPageObj.enterFieldValue(SavedPageObj.paragraphTxt, "Para");
		TestValidation.IsTrue(txtPara, "Numeric Field value entered successfully", "Failed to enter field value");
		boolean txtFT = SavedPageObj.enterFieldValue(SavedPageObj.freeText, "FreeText");
		TestValidation.IsTrue(txtFT, "Numeric Field value entered successfully", "Failed to enter field value");
		boolean selectOne = SavedPageObj.selectOneOrMultipleFieldValues("SelectOne", "GOOD");
		TestValidation.IsTrue(selectOne, "Numeric Field value entered successfully", "Failed to enter field value");
		String date = ControlActions_MobileApp.AddDaystoToddaysDate(0);
		boolean Sedate = SavedPageObj.clickFieldType(homePage.datePicker, date);
		TestValidation.IsTrue(Sedate, "Date Selected successfully", "Failed to select Date value");
		String dateSelected = ControlActions_MobileApp.AddDaystoToddaysDateInGivenFormat("M/d/YYYY", 0);
		logInfo("Date Field Value = " + dateSelected);
		boolean SedateTime = SavedPageObj.clickFieldType(homePage.dateTimePicker, date);
		TestValidation.IsTrue(SedateTime, "DateTime value selected successfully", "Failed to Select DateTime value");

		String dateTimeSel = homePage.dateTimePicker.getText();
		logInfo("Date&Time Field Value = " + dateTimeSel);
		Boolean SeMultiple = SavedPageObj.selectOneOrMultipleFieldValues("MutiSelect", "10");
		TestValidation.IsTrue(SeMultiple, "Value selected for SelectMultiple Field Type",
				"Failed to select MultiSelect value");
		Boolean SeMultiple1 = SavedPageObj.selectOneOrMultipleFieldValues("MutiSelect", "30");
		TestValidation.IsTrue(SeMultiple1, "Value selected for SelectMultiple Field Type",
				"Failed to select MultiSelect value");
		oUploadImage.CameraClick();
		Thread.sleep(2000);
		oUploadImage.OpenGallery();
		oUploadImage.SelectImage();
		oUploadImage.CloseGallery();
		boolean submitForm = InboxPageObj.submitTask();

		Thread.sleep(4000);

		TestValidation.IsTrue(submitForm, "Form submitted Successfully", "Failed to submit Form from Forms SubTab");
		homePage.ClickSubMenu(homePage.submissionsSubMenu);
		homePage.SearchForm(FormName);
		SavedPageObj.checkFormStatusFromSubmissionsSubMenu(FormName, "RECEIVED");

		hp.clickFSQABrowserMenu();

		boolean navigate = fbp.selectResourceDropDownandNavigate("CUSTOMERS", "RECORDS");
		TestValidation.IsTrue(navigate, "Navigated to FSQABrowser>RecordsTab",
				"Failed to navigate to FSQABrowser>Records Tab");

		boolean applyfilter = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, FormName);
		TestValidation.IsTrue(applyfilter, "Applied Filter on" + COLUMNHEADER.FORMNAME,
				"Failed to apply filter on" + COLUMNHEADER.FORMNAME);
		String dateMinus1 = ControlActions_MobileApp.AddDaystoToddaysDateInGivenFormat("MM/dd/yyyy", -1);
		System.out.println("dateMinus1" + dateMinus1);

		String CurrentDate = ControlActions_MobileApp.AddDaystoToddaysDateInGivenFormat("MM/dd/yyyy", 0);
		System.out.println("CurrentDate" + CurrentDate);
		String[] time = dateTimeSel.split("\\s");

		String dateTimeNew = CurrentDate + " " + time[1];

		System.out.println("New date is :" + dateMinus1);

		System.out.println("New datetime is :" + dateTimeNew);
		FSQABrowserRecordsPage rp = new FSQABrowserRecordsPage(driver);
		HashMap<String, List<String>> map1 = new HashMap<String, List<String>>();
		map1.put(Numeric, Arrays.asList("8"));
		map1.put(chkFreeTxt, Arrays.asList("FreeText"));
		map1.put(ParaTxt, Arrays.asList("Para"));
		map1.put(chkDate, Arrays.asList(dateMinus1));
		map1.put(chkDateTime, Arrays.asList(dateTimeNew));

		map1.put(SelectMultiple, Arrays.asList("10 , 30"));
		map1.put(chkSelectOne, Arrays.asList("GOOD"));

		boolean verifyUpdatedValueWeb = rp.verifyFieldValues(map1);

		TestValidation.IsTrue(verifyUpdatedValueWeb,
				"Submitted form is present on web.VERIFIED updated values for all fields",
				"Failed to verify updated values for fields");
		WebElement image = driver.findElement(By.xpath("//a[@ng-bind='attachment.name']"));
		String FileActual = image.getText();

		boolean AttachmentMob = (FileActual.equals("0.png") || FileActual.equals("1.png"));
		TestValidation.IsTrue(AttachmentMob, "Attachment from mobile is present at bottom on Web",
				"Attachment from mobile is not present at bottom on Web");

	}

	@Test(description = "Verify user should be able to save the task", priority = 1)
	public void TestCase_37043() throws Exception {
		appiumDriver = launchMobileApp();
		SavedPage SavedPageObj = new SavedPage(appiumDriver);
		TaskPage InboxPageObj = new TaskPage(appiumDriver);
		LoginPage loginPage = new LoginPage(appiumDriver);
		HomePage homePage = new HomePage(appiumDriver);
		mobControlActions = new ControlActions_MobileApp(appiumDriver);
		if (mobileApp_Tenant.equals("test1.stage")) {
			boolean SSOLogin = loginPage.SSO_Login(mobileAppUsername, mobileAppPassword);
			TestValidation.IsTrue(SSOLogin, "logged into the application with User " + mobileAppUsername,
					"Failed to log in");

		} else {
			boolean login = loginPage.Login(mobileAppUsername, mobileAppPassword);
			TestValidation.IsTrue(login, "logged into the application with User " + mobileAppUsername,
					"Failed to log in");

		}

		boolean clickInbox = homePage.ClickSubMenu(homePage.inboxSubMenu);
		TestValidation.IsTrue(clickInbox, "Clicked on Inbox Sub Menu", "Failed to click Inbox Submenu");
		boolean searchForm = homePage.SearchTask(TaskName);
		TestValidation.IsTrue(searchForm, "Task Searched successfully", "Failed to search Task");
		boolean clickTask = InboxPageObj.ClickTask(TaskName);
		TestValidation.IsTrue(clickTask, "Task Searched successfully", "Failed to search Task");

		boolean txtNumeric = SavedPageObj.enterFieldValue(SavedPageObj.NumericTxt, "8");
		TestValidation.IsTrue(txtNumeric, "Numeric Field value entered successfully", "Failed to enter field value");
		boolean txtPara = SavedPageObj.enterFieldValue(SavedPageObj.paragraphTxt, "Para");
		TestValidation.IsTrue(txtPara, "Numeric Field value entered successfully", "Failed to enter field value");
		boolean txtFT = SavedPageObj.enterFieldValue(SavedPageObj.freeText, "FreeText");
		TestValidation.IsTrue(txtFT, "Numeric Field value entered successfully", "Failed to enter field value");
		boolean selectOne = SavedPageObj.selectOneOrMultipleFieldValues("SelectOne", "GOOD");
		TestValidation.IsTrue(selectOne, "Numeric Field value entered successfully", "Failed to enter field value");
		String date = ControlActions_MobileApp.AddDaystoToddaysDate(0);
		boolean Sedate = SavedPageObj.clickFieldType(homePage.datePicker, date);
		TestValidation.IsTrue(Sedate, "Date Selected successfully", "Failed to select Date value");
		boolean SedateTime = SavedPageObj.clickFieldType(homePage.dateTimePicker, date);
		TestValidation.IsTrue(SedateTime, "DateTime value selected successfully", "Failed to Select DateTime value");
		String dateTimeSel = homePage.dateTimePicker.getText();
		logInfo("Date&Time Field Value = " + dateTimeSel);
		Boolean SeMultiple = SavedPageObj.selectOneOrMultipleFieldValues("MutiSelect", "10");
		TestValidation.IsTrue(SeMultiple, "Value selected for SelectMultiple Field Type",
				"Failed to select MultiSelect value");
		Boolean SeMultiple1 = SavedPageObj.selectOneOrMultipleFieldValues("MutiSelect", "30");
		TestValidation.IsTrue(SeMultiple1, "Value selected for SelectMultiple Field Type",
				"Failed to select MultiSelect value");

		boolean saveForm = SavedPageObj.saveForm();
		TestValidation.IsTrue(saveForm, "Form Saved Successfully", "Failed to Save Form from Forms SubTab");
		List<String> dateInFormatMob = SavedPageObj.TodaysDateinFormat("M/d/yy HH:mm z");
		boolean clickSaveMenu = homePage.ClickSubMenu(homePage.saveSubMenu);
		TestValidation.IsTrue(clickSaveMenu, "Clicked on Save subMenu Successfully", "Failed to click Save subMenu");
		ControlActions_MobileApp.click(SavedPageObj.sortBtn);
		searchForm = homePage.SearchForm(FormName);
		TestValidation.IsTrue(searchForm, "Form Searched Successfully", "Failed to Search Form from Forms subMenu");
		String datespliter[] = dateInFormatMob.get(0).split(" ");
		String Date = datespliter[0];

		boolean formStatus = SavedPageObj.checkFormStatusFromSavedSubMenu(FormName, Date);
		TestValidation.IsTrue(formStatus, "Verified Form Status Successfully",
				"Failed to verify Form Status on Submissions");
		String isPresent = ControlActions_MobileApp.perform_GetElementByXPath2(SavedPageLocators.SAVED_TIMESTAMP,
				"TIMESTAMP", Date);

		WebElement Field = appiumDriver.findElement(By.xpath(isPresent));
		String actualTimestamp = Field.getText();
		logInfo("Actual Timestamp = " + actualTimestamp);

		hp.clickFSQABrowserMenu();
		boolean navigate = fbp.selectResourceDropDownandNavigate("CUSTOMERS", "Forms");
		TestValidation.IsTrue(navigate, "Navigated to FSQABrowser>RecordsTab",
				"Failed to navigate to FSQABrowser>Records Tab");

		String[] ab = actualTimestamp.split(" ");

		Date dt1 = new Date();
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		System.out.println("New Date--->" + format.format(dt1));
		String dt = format.format(dt1) + " " + ab[4] + " " + ab[5];

		// System.out.println(format.format(dt1) + " " + ab[4]+ " " + ab[5] );
		SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy HH:mm z");
		Date d2 = format1.parse(dt);
		System.out.println("TimeStamp--->" + format1.format(d2));

		FSQABrowserFormsPage fbfp = new FSQABrowserFormsPage(driver);
		boolean open1 = fbfp.isSavedFormDisplayed("CUSTOMERS", FormName, format1.format(d2));
		// SavedPageObj.isSavedFormDisplayedOnWeb(FormName, format1.format(d2));
		TestValidation.IsTrue(open1, "Saved form on mobile is displayed on web",
				"Saved form on mobile is not present on web");

	}

	@Test(description = "Verify Inbox--Task Filter", priority = 2)
	public void TestCase_37045() throws Exception {

		appiumDriver = launchMobileApp();
		TaskPage InboxPageObj = new TaskPage(appiumDriver);
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
		boolean clickInboxMenu = homePage.ClickSubMenu(homePage.inboxSubMenu);
		TestValidation.IsTrue(clickInboxMenu, "Clicked on Inbox subMenu Successfully", "Failed to click Inbox subMenu");
		boolean SelAssignToFilter = InboxPageObj.selectAssignToFiler(WGName);
		TestValidation.IsTrue(SelAssignToFilter, "Selected Assigned to Filter", "Failed to select Assigned To Filter");
		boolean verTaskFilterResult = InboxPageObj.verifyTaskFilterResultGrid("WorkGroup", WGName, "Past Due", "Due");
		TestValidation.IsTrue(verTaskFilterResult, "Verified Filter Result Grid",
				"Failed to verify Filter Result Grid");
		boolean SelClearFilter = InboxPageObj.selectClearFiler();
		TestValidation.IsTrue(SelClearFilter, "Cleared All Filter", "Failed to clear Filter");

		SelAssignToFilter = InboxPageObj.selectStatusFiler(Status);
		TestValidation.IsTrue(SelAssignToFilter, "Selected Status Filter", "Failed to select Status Filter");
		verTaskFilterResult = InboxPageObj.verifyTaskFilterResultGrid("Status", assignedTo, "Past Due", "due");
		TestValidation.IsTrue(verTaskFilterResult, "Verified Filter Result Grid",
				"Failed to verify Filter Result Grid");

		String dueDate = ControlActions_MobileApp.AddDaystoToddaysDateInGivenFormat("MMMM d", 0);
		SelClearFilter = InboxPageObj.selectClearFiler();
		TestValidation.IsTrue(SelClearFilter, "Cleared All Filter", "Failed to clear Filter");

		boolean selDueByFilter = InboxPageObj.selectDueByFiler(dueDate);
		TestValidation.IsTrue(selDueByFilter, "Selected Due By Filter", "Failed to select Due By Filter");
		boolean verTaskFilterResult1 = InboxPageObj.verifyTaskFilterResultGrid("dueBy", assignedTo, "Past Due",
				dueDate);
		TestValidation.IsTrue(verTaskFilterResult1, "Verified Filter Result Grid",
				"Failed to verify Filter Result Grid");

	}

	@Test(description = "Verify Task as per the Task Window", priority = 3)
	public void TestCase_36799() throws Exception {
		appiumDriver = launchMobileApp();
		SavedPage SavedPageObj = new SavedPage(appiumDriver);
		TaskPage InboxPageObj = new TaskPage(appiumDriver);
		LoginPage loginPage = new LoginPage(appiumDriver);
		HomePage homePage = new HomePage(appiumDriver);
		mobControlActions = new ControlActions_MobileApp(appiumDriver);
		if (mobileApp_Tenant.equals("test1.stage")) {
			boolean SSOLogin = loginPage.SSO_Login(mobileAppUsername, mobileAppPassword);
			TestValidation.IsTrue(SSOLogin, "logged into the application with User " + mobileAppUsername,
					"Failed to log in");

		} else {
			boolean login = loginPage.Login(mobileAppUsername, mobileAppPassword);
			TestValidation.IsTrue(login, "logged into the application with User " + mobileAppUsername,
					"Failed to log in");

		}

		boolean clickSettingsMenu = homePage.ClickSubMenu(homePage.settingsSubMenu);
		TestValidation.IsTrue(clickSettingsMenu, "Clicked on settings subMenu  Successfully",
				"Failed to click Settings subMenu");
		boolean select7DaysdataLimit = InboxPageObj.selectDataLimit("7 DAYS");
		TestValidation.IsTrue(select7DaysdataLimit, "Selected 7 Days data limit from Settings",
				"Failed to select 7 Days data limit from Settings");
		ControlActions_MobileApp.click(SavedPageObj.settingsBckBtn);
		TestValidation.IsTrue(true, "Clicked on Settings Back Button  Successfully",
				"Failed to click Settings Back Button");
		boolean clickInboxMenu = homePage.ClickMenu(homePage.inboxSubMenu);
		TestValidation.IsTrue(clickInboxMenu, "Clicked on Inbox subMenu Successfully", "Failed to click Inbox subMenu");
		String dueDate = ControlActions_MobileApp.AddDaystoToddaysDateInGivenFormat("MMMM d", -6);
		logInfo("Due Date for 7 Days= " + dueDate);
		boolean vrfyDataLimit1 = InboxPageObj.verifyDataLimitFilterResult(dueDate);
		TestValidation.IsTrue(vrfyDataLimit1, "Verified Data Limit Filter Successfully",
				"Failed to cVerified Data Limit Filter");

		boolean clickSettingsMenu1 = homePage.ClickSubMenu(homePage.settingsSubMenu);
		TestValidation.IsTrue(clickSettingsMenu1, "Clicked on settings subMenu Successfully",
				"Failed to click Settings subMenu");
		select7DaysdataLimit = InboxPageObj.selectDataLimit("3 DAYS");
		TestValidation.IsTrue(select7DaysdataLimit, "Selected 7 Days data limit from Settings",
				"Failed to select 7 Days data limit from Settings");
		ControlActions_MobileApp.click(SavedPageObj.settingsBckBtn);
		TestValidation.IsTrue(true, "Clicked on Settings Back Button Successfully",
				"Failed to click Settings Back Button");
		clickInboxMenu = homePage.ClickMenu(homePage.inboxSubMenu);
		TestValidation.IsTrue(clickInboxMenu, "Clicked on Inbox subMenu Successfully", "Failed to click Inbox subMenu");
		String dueDate3 = ControlActions_MobileApp.AddDaystoToddaysDateInGivenFormat("MMMM d", -3);
		logInfo("Due Date for 3 Days= " + dueDate3);
		boolean vrfyDataLimit = InboxPageObj.verifyDataLimitFilterResult(dueDate3);
		TestValidation.IsTrue(vrfyDataLimit, "Verified Data Limit Filter Successfully",
				"Failed to cVerified Data Limit Filter");

		clickSettingsMenu = homePage.ClickSubMenu(homePage.settingsSubMenu);
		TestValidation.IsTrue(clickSettingsMenu, "Clicked on settings subMenu Successfully",
				"Failed to click Settings subMenu");
		boolean select14DaysdataLimit = InboxPageObj.selectDataLimit("14 DAYS");
		TestValidation.IsTrue(select14DaysdataLimit, "Selected 14 Days data limit from Settings",
				"Failed to select 14 Days data limit from Settings");
		ControlActions_MobileApp.click(SavedPageObj.settingsBckBtn);
		TestValidation.IsTrue(clickInboxMenu, "Clicked on Settings Back Button  Successfully",
				"Failed to click Settings Back Button");
		clickInboxMenu = homePage.ClickMenu(homePage.inboxSubMenu);
		TestValidation.IsTrue(clickInboxMenu, "Clicked on Inbox subMenu  Successfully",
				"Failed to click Inbox subMenu");
		String dueDate14 = ControlActions_MobileApp.AddDaystoToddaysDateInGivenFormat("MMMM d", -14);
		logInfo("Due Date for 3 Days= " + dueDate14);
		vrfyDataLimit = InboxPageObj.verifyDataLimitFilterResult(dueDate14);
		TestValidation.IsTrue(vrfyDataLimit, "Verified Data Limit Filter for 14 Days Successfully",
				"Failed to verify Data Limit Filter for 14 days");
		Dimension size = appiumDriver.manage().window().getSize();

		int starty = (int) (size.height * 0.80);
		int endy = (int) (size.height * 0.20);
		int startx = size.width / 2;
		System.out.println("starty = " + starty + " ,endy = " + endy + " , startx = " + startx);

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
