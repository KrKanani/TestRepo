package com.project.safetychain.mobileapp.testcases;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.project.safetychain.api.models.support.Support.DPT_FIELD_TYPES;
import com.project.safetychain.api.models.support.Support.DPT_FORM_TYPES;
import com.project.safetychain.api.models.support.Support.FormFieldParams;
import com.project.safetychain.api.models.support.Support.FormParams;
import com.project.safetychain.api.models.support.Support.RESOURCE_TYPES;
import com.project.safetychain.api.models.support.Support.TaskOccurence;
import com.project.safetychain.api.models.support.Support.TaskParams;
import com.project.safetychain.api.models.support.TCG_FormCreation_Wrapper;
import com.project.safetychain.api.models.support.TCG_TaskCreation_Wrapper;
import com.project.safetychain.mobileapp.pageobjects.HomePage;
import com.project.safetychain.mobileapp.pageobjects.LoginPage;
import com.project.safetychain.mobileapp.pageobjects.SavedPage;
import com.project.safetychain.mobileapp.pageobjects.TaskPage;
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

public class TCG_TaskScheduler_Flow extends TestBase {
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
			TaskName = CommonMethods.dynamicText("API_TaskScheduler");
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
			String TodaysDuedate = ControlActions_MobileApp.AddDaystoToddaysDateInGivenFormat("MM/dd/YYYY", 1);
			TodaysDuedate = TodaysDuedate + " 00:00";
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
			tp.ExpirationIntervalType = "Never";
			tp.FirstDueDate = TodaysDuedate;
			tp.AssignTimeBeforeTaskIsDueHours = "24";
			tp.Timezone = "Republic of India";
			tp.Occurrence = TaskOccurence.ONETIMEONLY;
			tp.Repetition = "2";

			taskCreationWrapper.create_Link_workGroup_Wrapper(tp);
			taskCreationWrapper.create_Task_TaskScheduler_Wrapper(tp);

			// Multiple task Creation

			// List<String> dueByLst = new ArrayList<String>();
			//
			// dueByLst.add(ControlActions_MobileApp.AddDaystoToddaysDateInGivenFormat("MM/dd/YYYY
			// HH:mm", -2));
			// dueByLst.add(ControlActions_MobileApp.AddDaystoToddaysDateInGivenFormat("MM/dd/YYYY
			// HH:mm", -4));
			// dueByLst.add(ControlActions_MobileApp.AddDaystoToddaysDateInGivenFormat("MM/dd/YYYY
			// HH:mm", 7));
			//
			// for (int i = 0; i < dueByLst.size(); i++) {
			// tp.DueBy = dueByLst.get(i);
			// taskCreationWrapper.create_Task_Wrapper(tp);
			// }
			//
			//
			//
			//
			//
			// driver = launchbrowser();
			//
			// formDesignerPage = new FormDesignerPage(driver);
			// locations = new ManageLocationPage(driver);
			// manageResource = new ManageResourcePage(driver);
			// fbp = new FSQABrowserPage(driver);
			// fbForms = new FSQABrowserFormsPage(driver);
			// resourceDesigner = new ResourceDesignerPage(driver);
			// mainPage = new CommonPage(driver);
			// fop = new FormOperations(driver);
			// fbrp = new FSQABrowserRecordsPage(driver);
			//
			// controlActions = new ControlActions(driver);
			// lp = new
			// com.project.safetychain.webapp.pageobjects.LoginPage(driver);
			// controlActions.getUrl(applicationUrl);
			// hp = lp.performLogin(adminUsername, adminPassword);
			// if (hp.error) {
			// TCGFailureMsg = "Could NOT login to application";
			// logFatal(TCGFailureMsg);
			// throw new SkipException(TCGFailureMsg);
			// }

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test(description = " Verify Latest Task is displayed after navigating to Inbox screen and task submit functionality", priority = 0)
	public void TestCase_36801_37044() throws Exception {
		// formName = "check_01";
		appiumDriver = launchMobileApp();

		SavedPage SavedPageObj = new SavedPage(appiumDriver);
		TaskPage InboxPageObj = new TaskPage(appiumDriver);
		// LoginPage loginPage =
		// registrationPage.RegisterTenant(mobileApp_Tenant);
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
		String date = ControlActions_MobileApp.AddDaystoToddaysDate(1);
		boolean Sedate = SavedPageObj.clickFieldType(homePage.datePicker, date);
		TestValidation.IsTrue(Sedate, "Date Selected successfully", "Failed to select Date value");
		boolean SedateTime = SavedPageObj.clickFieldType(homePage.dateTimePicker, date);
		TestValidation.IsTrue(SedateTime, "DateTime value selected successfully", "Failed to Select DateTime value");
		Boolean SeMultiple = SavedPageObj.selectOneOrMultipleFieldValues("MutiSelect", "10");
		TestValidation.IsTrue(SeMultiple, "Value selected for SelectMultiple Field Type",
				"Failed to select MultiSelect value");
		Boolean SeMultiple1 = SavedPageObj.selectOneOrMultipleFieldValues("MutiSelect", "30");
		TestValidation.IsTrue(SeMultiple1, "Value selected for SelectMultiple Field Type",
				"Failed to select MultiSelect value");

		boolean submitForm = InboxPageObj.submitTask();

		Thread.sleep(4000);

		TestValidation.IsTrue(submitForm, "Form submitted Successfully", "Failed to submit Form from Forms SubTab");
		homePage.ClickSubMenu(homePage.submissionsSubMenu);
		// mobControlActions.click(SavedPageObj.sortBtn);
		homePage.SearchForm(FormName);
		SavedPageObj.checkFormStatusFromSubmissionsSubMenu(FormName, "RECEIVED");

		hp.clickFSQABrowserMenu();

		boolean navigate = fbp.selectResourceDropDownandNavigate("CUSTOMERS", "RECORDS");
		TestValidation.IsTrue(navigate, "Navigated to FSQABrowser>RecordsTab",
				"Failed to navigate to FSQABrowser>Records Tab");

		boolean applyfilter = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, FormName);
		TestValidation.IsTrue(applyfilter, "Applied Filter on" + COLUMNHEADER.FORMNAME,
				"Failed to apply filter on" + COLUMNHEADER.FORMNAME);

		List<String> fieldVal = new ArrayList<String>();
		fieldVal.add("8");
		boolean verifyNumValue = fbrp.verifyFieldValues("Numeric", fieldVal);
		TestValidation.IsTrue(verifyNumValue, "VERIFIED updated value for field type Numeric as ",
				"Failed to verify value for All field type Numeric");

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
