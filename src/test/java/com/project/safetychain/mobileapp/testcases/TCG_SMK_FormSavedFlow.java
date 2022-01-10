package com.project.safetychain.mobileapp.testcases;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
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
import com.project.safetychain.api.models.support.TCG_FormCreation_Wrapper;
import com.project.safetychain.mobileapp.pageobjects.HomePage;
import com.project.safetychain.mobileapp.pageobjects.LoginPage;
import com.project.safetychain.mobileapp.pageobjects.SavedPage;
import com.project.safetychain.mobileapp.pageobjects.SavedPageLocators;
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

public class TCG_SMK_FormSavedFlow extends TestBase {
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

	public static String formName = "Automation_CheckForm_15.10_17.19.19";
	public static String ResourceName = "RInst2_15.10_13.47.04";
	public static String LocationName = "LInst1_15.10_13.47.04";// LInst2_15.10_13.47.04
	String formtype = "Check";
	String modifiedby;
	public static String FormName;

	@BeforeClass(alwaysRun = true)
	public void groupInit() {
		try {
			FormName = CommonMethods.dynamicText("Auto_ChkFrm");
			driver = launchbrowser();

			formDesignerPage = new FormDesignerPage(driver);
			locations = new ManageLocationPage(driver);
			manageResource = new ManageResourcePage(driver);
			fbp = new FSQABrowserPage(driver);
			fbForms = new FSQABrowserFormsPage(driver);
			resourceDesigner = new ResourceDesignerPage(driver);
			mainPage = new CommonPage(driver);
			fop = new FormOperations(driver);

			// resourceCategoryValue = CommonMethods.dynamicText("Equip_Cat");

			locationCategoryValue = CommonMethods.dynamicText("LCat");
			locationInstanceValue1 = CommonMethods.dynamicText("LInst1");
			locationInstanceValue2 = CommonMethods.dynamicText("LInst2");
			resourceCategoryValue = CommonMethods.dynamicText("RCat");
			resourceInstanceValue1 = CommonMethods.dynamicText("RInst1");
			resourceInstanceValue2 = CommonMethods.dynamicText("RInst2");

			// Field Short Names
			chkFreeTxt = "FreeText";
			ParaTxt = "Paragraph";
			chkSelectOne = "SelectOne";
			Numeric = "Numeric";
			chkDate = "Date";
			chkTime = "Time";
			chkDateTime = "Date&Time";
			SelectMultiple = "MutiSelect";

			lp = new com.project.safetychain.webapp.pageobjects.LoginPage(driver);
			controlActions = new ControlActions(driver);

			controlActions.getUrl(applicationUrl);

			hp = lp.performLogin(adminUsername, adminPassword);
			if (hp.error) {
				TCGFailureMsg = "Could NOT login to application";
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}

			modifiedby = mainPage.getLoggedInUserDetails();

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

			List<String> userList = Arrays.asList(mobileAppUsername02, mobileAppUsername);
			formCreationWrapper.Resource_Location_CreationWrapper(resourceCatMap, LocationMap, true, userList,
					fp.ResourceType);

			formCreationWrapper.API_Wrapper_Forms(fp);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test(description = " Verify Forms are saved successfully and displayed on web and Mobile")
	public void TestCase_36793() throws Exception {
		appiumDriver = launchMobileApp();

		SavedPage SavedPageObj = new SavedPage(appiumDriver);

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
		boolean searchForm = homePage.SearchForm(FormName);
		TestValidation.IsTrue(searchForm, "Form Searched successfully", "Failed to search Form");
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
		String dateSelected = ControlActions_MobileApp.AddDaystoToddaysDateInGivenFormat("M/d/YY", 0);
		logInfo("Date Field Value = " + dateSelected);

		Boolean SedateTime = SavedPageObj.clickFieldType(homePage.dateTimePicker, date);
		TestValidation.IsTrue(SedateTime, "DateTime value selected successfully", "Failed to Select DateTime value");
		String dateTimeSel = homePage.dateTimePicker.getText();
		logInfo("Date&Time Field Value = " + dateTimeSel);
		List<String> dateInFormat = SavedPageObj.TodaysDateinFormat("MM/dd/yyyy HH:mm z");
		Boolean SeMultiple = SavedPageObj.selectOneOrMultipleFieldValues("MutiSelect", "10");
		TestValidation.IsTrue(SeMultiple, "Value selected for SelectMultiple Field Type",
				"Failed to select MultiSelect value");
		Boolean SeMultiple1 = SavedPageObj.selectOneOrMultipleFieldValues("MutiSelect", "30");
		TestValidation.IsTrue(SeMultiple1, "Value selected for SelectMultiple Field Type",
				"Failed to select MultiSelect value");

		boolean saveForm = SavedPageObj.saveForm();
		TestValidation.IsTrue(saveForm, "Form Saved Successfully", "Failed to Save Form from Forms SubTab");

		boolean clickSaveMenu = homePage.ClickSubMenu(homePage.saveSubMenu);
		TestValidation.IsTrue(clickSaveMenu, "Clicked on Save subMenu Successfully", "Failed to click Save subMenu");
		ControlActions_MobileApp.click(SavedPageObj.sortBtn);
		searchForm = homePage.SearchForm(FormName);
		TestValidation.IsTrue(searchForm, "Form Searched Successfully", "Failed to Search Form from Forms subMenu");
		String datespliter[] = dateInFormat.get(0).split(" ");
		String Date = datespliter[0];
		logInfo("Datesplitter[0] : " + Date);
		boolean formStatus = SavedPageObj.checkFormStatusFromSavedSubMenu(FormName, dateSelected);
		TestValidation.IsTrue(formStatus, "Verified Form Status Successfully",
				"Failed to verify Form Status on Submissions");

		String isPresent = ControlActions_MobileApp.perform_GetElementByXPath2(SavedPageLocators.SAVED_TIMESTAMP,
				"TIMESTAMP", dateSelected);

		WebElement Field = appiumDriver.findElement(By.xpath(isPresent));
		String actualTimestamp = Field.getText();
		logInfo("Actual Timestamp = " + actualTimestamp);

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

		TestValidation.IsTrue(verifyUpdatedValue, "VERIFIED updated value for field type Numeric as " + FieldTypeValues,
				"Failed to verify updated value for field type Numeric as " + FieldTypeValues);

		// hp.clickFSQABrowserMenu();
		boolean navigate = fbp.selectResourceDropDownandNavigate("CUSTOMERS", "Forms");
		TestValidation.IsTrue(navigate, "Navigated to FSQABrowser>FormsTab",
				"Failed to navigate to FSQABrowser>Forms Tab");
		threadsleep(5000);
		String[] ab = actualTimestamp.split(" ");
		for (int i = 0; i < ab.length; i++) {
			System.out.println(ab[i]);
		}
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
		TestValidation.IsTrue(open1, "Saved form on mobile is displayed on web",
				"Saved form on mobile is not present on web");
		
//		controlActions.refreshPage();
//		threadsleep(5000);
		boolean openForm = fbp.openFormInSavedForms(FormName);
		TestValidation.IsTrue(openForm, 
							 "Selected & opened Saved form - "+FormName, 
							 "Could NOT open saved form - "+FormName);
		
		LocalDate currentdate = LocalDate.now();
		int currentDay = currentdate.getDayOfMonth();
		String dateMinus1;
		if(currentDay<=10)
			 dateMinus1 = ControlActions_MobileApp.AddDaystoToddaysDateInGivenFormat("MM/d/yyyy", -1);
		else
			 dateMinus1 = ControlActions_MobileApp.AddDaystoToddaysDateInGivenFormat("MM/dd/yyyy", -1);

		String CurrentDate = ControlActions_MobileApp.AddDaystoToddaysDateInGivenFormat("MM/dd/yyyy", 0);

		String[] time = dateTimeSel.split("\\s");
		String dateTimeNew = CurrentDate + " " + time[1];
		logInfo("dateTimeNew"+ dateTimeNew);
		logInfo("dateMinus1"+ dateMinus1);
		boolean verifySavedNumField = fop.verifyFieldValue(Numeric, "8");
		boolean verifySavedFreeTxtField = fop.verifyFieldValue(chkFreeTxt, "FreeText");
		boolean verifySavedParaField = fop.verifyParagraphTextFieldValue(ParaTxt, "Para");
		boolean verifySavedDateField = fop.verifyFieldValue(chkDate, dateMinus1);
		boolean verifySavedDateNTimeField = fop.verifyFieldValue(chkDateTime,dateTimeNew);
		boolean verifySavedSelOField = fop.verifyFieldValue(chkSelectOne, "GOOD");
		boolean verifySavedSelMField = fop.verifySelectMultipleFieldValues(SelectMultiple,Arrays.asList("10","30"));
		TestValidation.IsTrue(verifySavedNumField && verifySavedFreeTxtField && verifySavedParaField && verifySavedDateField
								&& verifySavedDateNTimeField && verifySavedSelOField && verifySavedSelMField, 
			  				  "Verified the saved data for all the fields on web" ,
			  			  	  "Failed to Verify the saved data");
		
		SavedPageObj.clickDeleteThenOk();
		Thread.sleep(2000);
	}

	@Test(description = "Kill app and check saved functionality",priority=3)
	public void TestCase_36828() throws Exception {
		
		hp.clickHomepageLink();
		
		appiumDriver = launchMobileApp();
		mobControlActions = new ControlActions_MobileApp(appiumDriver);

		SavedPage SavedPageObj = new SavedPage(appiumDriver);
		LoginPage loginPage = new LoginPage(appiumDriver);
		HomePage homePage = new HomePage(appiumDriver);
		boolean login = loginPage.Login(mobileAppUsername, mobileAppPassword);
		TestValidation.IsTrue(login, "logged into the application with User " + mobileAppUsername, "Failed to log in");

		boolean searchForm = homePage.SearchForm(FormName);
		TestValidation.IsTrue(searchForm, "Form Searched successfully", "Failed to search Form");
		boolean clickForm = homePage.ClickForm(FormName);
		TestValidation.IsTrue(clickForm, "Clicked on form Successfully", "Failed to click Form");
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

		Thread.sleep(60000);

		appiumDriver.closeApp();
		List<String> dateInFormat = SavedPageObj.TodaysDateinFormat("MM/dd/yyyy HH:mm z");
		Thread.sleep(5000);

		List<String> removePicsArgs = Arrays.asList("keyevent", "KEYCODE_APP_SWITCH");
		Map<String, Object> removePicsCmd = new HashMap<String, Object>();
		removePicsCmd.put("command", "input");
		removePicsCmd.put("args", removePicsArgs);

		appiumDriver.executeScript("mobile: shell", removePicsCmd);
		logInfo("Clicked on Home Button");

		Thread.sleep(1000);
		List<String> removePicsArgs1 = Arrays.asList("swipe", "10 600 1600 600");
		Map<String, Object> removePicsCmd1 = new HashMap<String, Object>();
		removePicsCmd1.put("command", "input");
		removePicsCmd1.put("args", removePicsArgs1);

		appiumDriver.executeScript("mobile: shell", removePicsCmd1);
		logInfo("Swipe done");
		Thread.sleep(2000);

		ControlActions_MobileApp.click(SavedPageObj.closeAllApps);
		logInfo("Clicked on Clear All button");

		appiumDriver = launchMobileApp();
		mobControlActions = new ControlActions_MobileApp(appiumDriver);
		SavedPageObj = new SavedPage(appiumDriver);
		LoginPage loginPage1 = new LoginPage(appiumDriver);
		HomePage homePage1 = new HomePage(appiumDriver);
		boolean login1 = loginPage1.Login(mobileAppUsername, mobileAppPassword);
		TestValidation.IsTrue(login1, "logged into the application with User " + mobileAppUsername, "Failed to log in");
		Thread.sleep(20000);
		ControlActions_MobileApp.WaitforelementToBeClickable(homePage1.mainMenu);
		boolean clickFormsSub = homePage1.ClickSubMenu(homePage1.saveSubMenu);
		TestValidation.IsTrue(clickFormsSub, "Clicked on Saved subMenu Successfully", "Failed to click Saved subMenu");
		ControlActions_MobileApp.click(SavedPageObj.sortBtn);
		Thread.sleep(10000);
		List<String> dateInFormatMob = SavedPageObj.TodaysDateinFormat("M/d/YY HH:mm z");
		String datespliter[] = dateInFormatMob.get(0).split(" ");
		String Date = datespliter[0];

		boolean formStatus = SavedPageObj.checkFormStatusFromSavedSubMenu(FormName, Date);
		TestValidation.IsTrue(formStatus, "Verified Form Status Successfully",
				"Failed to verify Form Status on Submissions");
		
//		boolean searchSavedForm = homePage.SearchForm(FormName);
//		TestValidation.IsTrue(searchSavedForm, 
//							 "Saved form is present in Saved Forms",
//							 "Saved form is not present in Saved Forms");
		
		boolean clickSavedForm = homePage.ClickForm(FormName);
		logInfo("Clicked on Form = " + FormName);
		TestValidation.IsTrue(clickSavedForm, "Clicked on Saved form", "Failed to click on saved form");

		LinkedHashMap<String, String> FieldTypeValues = new LinkedHashMap<String, String>();
		FieldTypeValues.put(Numeric, "8");
		FieldTypeValues.put(ParaTxt, "Para");
		FieldTypeValues.put(chkFreeTxt, "FreeText");
		FieldTypeValues.put(chkSelectOne, "GOOD");

		boolean verifyUpdatedValue = SavedPageObj.verifyFieldValues(FieldTypeValues);
		TestValidation.IsTrue(verifyUpdatedValue,
							  "Form is saved and value is verified for fields",
							  "Failed to verify updated values for fields");
		SavedPageObj.clickDeleteThenOk();
	}

	@Test(description = "Verify Forms saved On web is displayed on Tab / Phone", priority=2)
	public void TestCase_36803() throws Exception {

		hp.clickHomepageLink();

		boolean navigate = fbp.selectResourceDropDownandNavigate("CUSTOMERS", "Forms");
		TestValidation.IsTrue(navigate, "Navigated to FSQABrowser>FormsTab",
				"Failed to navigate to FSQABrowser>Forms Tab");

		boolean applyfilter = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, FormName);
		TestValidation.IsTrue(applyfilter, "Applied Filter on" + COLUMNHEADER.FORMNAME,
				"Failed to apply filter on" + COLUMNHEADER.FORMNAME);
		threadsleep(5000);

		FormDetails fd = new FormDetails();
		HashMap<String, List<String>> map1 = new HashMap<String, List<String>>();
		map1.put(Numeric, Arrays.asList("3"));
		map1.put(chkFreeTxt, Arrays.asList("FreeText"));
		map1.put(ParaTxt, Arrays.asList("Paragraph"));
		map1.put(chkDate, Arrays.asList("10/19/2025"));
		map1.put(chkDateTime, Arrays.asList("10/14/2025 01:59"));

		map1.put(SelectMultiple, Arrays.asList("20"));
		map1.put(chkSelectOne, Arrays.asList("Good"));

		fd.fieldDetails = map1;
		fd.resourceName = resourceInstanceValue1;
		fd.locationName = locationInstanceValue1;
		fd.isSubmit = false;

		boolean submit1 = fop.submitData(fd);
		TestValidation.IsTrue(submit1, "Entered data and Saved form successfully",
				"Could NOT Enter data and Save form");
		List<String> dateInFormat = TodaysDateinFormat("MM/dd/yyyy HH:mm z");
		appiumDriver = launchMobileApp();
		SavedPageObj = new SavedPage(appiumDriver);

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
		boolean clickSubmissionSub = homePage.ClickSubMenu(homePage.saveSubMenu);
		TestValidation.IsTrue(clickSubmissionSub, "Clicked on Save subMenu Successfully",
				"Failed to click Save subMenu");
		Thread.sleep(10000);
		boolean searchForm = homePage.SearchForm(FormName);
		TestValidation.IsTrue(searchForm, "Form Searched successfully", "Failed to search Form");
		appiumDriver.hideKeyboard();

		List<String> dateInFormatMob = SavedPageObj.TodaysDateinFormat("M/d/YY HH:mm z");

		System.out.println(dateInFormatMob.toString());
		System.out.println(dateInFormatMob.get(0));
		// System.out.println(dateInFormat.get(1));
		String datespliter[] = dateInFormatMob.get(0).split(" ");
		String Date = datespliter[0];

		boolean formStatus = SavedPageObj.checkFormStatusFromSavedSubMenu(FormName, Date);
		TestValidation.IsTrue(formStatus, "Verified Form Status Successfully",
				"Failed to verify Form Status on Save Sub Tab");

		boolean clickFormsSub = homePage.ClickForm(FormName);
		TestValidation.IsTrue(clickFormsSub, "Clicked on form Successfully", "Failed to click Forms");

		Thread.sleep(30000);
		LinkedHashMap<String, String> FieldTypeValues = new LinkedHashMap<String, String>();
		FieldTypeValues.put(Numeric, "3");
		FieldTypeValues.put(ParaTxt, "Paragraph");
		FieldTypeValues.put(chkFreeTxt, "FreeText");
		FieldTypeValues.put(chkSelectOne, "GOOD");
		FieldTypeValues.put(chkDate, "10/19/25");
		FieldTypeValues.put(chkDateTime, "10/14/25 01:59 IST");
		FieldTypeValues.put(SelectMultiple, "20");

		boolean verifyUpdatedValue = SavedPageObj.verifyFieldValues(FieldTypeValues);
		TestValidation.IsTrue(verifyUpdatedValue, "VERIFIED updated value for  all fields",
				"Failed to verify updated values for all fields");
 
		ControlActions_MobileApp.ScrollIntoView("Numeric *");
		Boolean txtNumeric = SavedPageObj.enterFieldValue(SavedPageObj.NumericTxt, "8");
		TestValidation.IsTrue(txtNumeric, "Numeric Field value entered successfully", "Failed to enter field value");

		Boolean txtPara = SavedPageObj.enterFieldValue(SavedPageObj.paragraphTxt, "Para");
		TestValidation.IsTrue(txtPara, "Numeric Field value entered successfully", "Failed to enter field value");

		Boolean txtFT = SavedPageObj.enterFieldValue(SavedPageObj.freeText, "Free");
		TestValidation.IsTrue(txtFT, "Numeric Field value entered successfully", "Failed to enter field value");

		boolean saveForm = SavedPageObj.saveForm();
		TestValidation.IsTrue(saveForm, "Form Saved Successfully", "Failed to Save Form from Forms SubTab");

		// Web validation : Add Code for opening saved form

		FSQABrowserPage fbp = new FSQABrowserPage(driver);
		FSQABrowserRecordsPage rp = new FSQABrowserRecordsPage(driver);

		boolean openForm = fbp.openFormInSavedForms(FormName);
		TestValidation.IsTrue(openForm, 
							 "Selected & opened Saved form - "+FormName, 
							 "Could NOT open saved form - "+FormName);
		
//		LocalDate currentdate = LocalDate.now();
//		int currentDay = currentdate.getDayOfMonth();
//		String dateMinus1;
//		if(currentDay<=9)
//			 dateMinus1 = ControlActions_MobileApp.AddDaystoToddaysDateInGivenFormat("MM/d/yyyy", -1);
//		else
//			 dateMinus1 = ControlActions_MobileApp.AddDaystoToddaysDateInGivenFormat("MM/dd/yyyy", -1);
//
//		String CurrentDate = ControlActions_MobileApp.AddDaystoToddaysDateInGivenFormat("MM/dd/yyyy", 0);
//
//		String[] time = dateTimeSel.split("\\s");
//
//		System.out.println(time[0]);
//		System.out.println(time[1]);
//		System.out.println(time[2]);
//
//		String dateTimeNew = CurrentDate + " " + time[1];// + " " + time[2];
//
//		System.out.println("New date is :" + dateMinus1);

//		System.out.println("New datetime is :" + dateTimeNew);
//		HashMap<String, List<String>> map2 = new HashMap<String, List<String>>();
//		map2.put(Numeric, Arrays.asList("3"));
//		map2.put(chkFreeTxt, Arrays.asList("FreeText"));
//		map2.put(ParaTxt, Arrays.asList("Paragraph"));
//		map2.put(chkDate, Arrays.asList(dateMinus1));
//		map2.put(chkDateTime, Arrays.asList(dateTimeNew));
//
//		map2.put(SelectMultiple, Arrays.asList("20"));
//		map2.put(chkSelectOne, Arrays.asList("GOOD"));
//
//		boolean verifyUpdatedValueWeb = rp.verifyFieldValues(map1);
//		TestValidation.IsTrue(verifyUpdatedValueWeb, "VERIFIED updated values for all fields",
//				"Failed to verify updated values for fields");
		
		boolean verifySavedNumField = fop.verifyFieldValue(Numeric, "8");
		boolean verifySavedFreeTxtField = fop.verifyFieldValue(chkFreeTxt, "Free");
		boolean verifySavedParaField = fop.verifyParagraphTextFieldValue(ParaTxt, "Para");
		boolean verifySavedDateField = fop.verifyFieldValue(chkDate, "10/18/2025");
		boolean verifySavedDateNTimeField = fop.verifyFieldValue(chkDateTime,"10/14/2025 01:59");
		boolean verifySavedSelOField = fop.verifyFieldValue(chkSelectOne, "GOOD");
		boolean verifySavedSelMField = fop.verifySelectMultipleFieldValues(SelectMultiple,Arrays.asList("20"));
		TestValidation.IsTrue(verifySavedNumField && verifySavedFreeTxtField && verifySavedParaField && verifySavedDateField
								&& verifySavedDateNTimeField && verifySavedSelOField && verifySavedSelMField, 
			  				  "Verified the saved data for all the fields on web" ,
			  			  	  "Failed to Verify the saved data");
		
		fop.clickSubmitThenAlertOkBtn();
	}

	public List<String> TodaysDateinFormat(String timeFormat) {
		String ExpectedTimestamp1 = null;
		String ExpectedTimestamp2 = null;
		List<String> ExpectedTimestamp = new ArrayList<String>();

		try {
			ExpectedTimestamp1 = ControlActions_MobileApp.formatdateAndTime(timeFormat);

			System.out.println("Expected Timestamp = " + ExpectedTimestamp1);
			ExpectedTimestamp.add(ExpectedTimestamp1);

			String datespliter[] = ExpectedTimestamp1.split(" ");
			String Date = datespliter[0];
			String Time = datespliter[1];
			String TimeZone = datespliter[3];

			String TimeIn12Hr = ControlActions_MobileApp.convert24HrFormatInto12HrTime(Time);
			System.out.println(" Time in 12hr format =" + TimeIn12Hr);
			ExpectedTimestamp2 = "Saved on " + Date + " at " + TimeIn12Hr + " " + TimeZone;
			System.out.println(" ExpectedTimestamp =" + ExpectedTimestamp2);
			ExpectedTimestamp.add(ExpectedTimestamp2);

			return ExpectedTimestamp;
		} catch (Exception ex) {
			System.out.println("Failed to Search Form on Saved Form Tab" + ex.getMessage());
			return ExpectedTimestamp;
		}
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
