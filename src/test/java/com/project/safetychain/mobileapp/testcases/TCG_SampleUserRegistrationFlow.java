package com.project.safetychain.mobileapp.testcases;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.project.safetychain.mobileapp.pageobjects.HomePage;
import com.project.safetychain.mobileapp.pageobjects.LoginPage;
import com.project.safetychain.mobileapp.pageobjects.RegistrationPage;
import com.project.safetychain.webapp.pageobjects.CommonPage;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FIELD_TYPES;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FORMRESOURCETYPES;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FORMTYPES;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FormDesignParams;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FormFieldParams;
import com.project.safetychain.webapp.pageobjects.ManageLocationPage;
import com.project.safetychain.webapp.pageobjects.ManageResourcePage;
import com.project.safetychain.webapp.pageobjects.ResourceDesignerPage;
import com.project.safetychain.webapp.testcases.support.FormOperations.FormDetails;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.utilities.CommonMethods;
import com.project.utilities.ControlActions;
import com.project.utilities.ControlActions_MobileApp;

public class TCG_SampleUserRegistrationFlow extends TestBase {
	ControlActions_MobileApp mobControlActions;

	FormDesignerPage formDesignerPage;
	ControlActions controlActions;
	CommonPage mainPage;
	FormFieldParams ffp;
	FormDesignParams fp;
	FormDetails formDetails;
	com.project.safetychain.webapp.pageobjects.HomePage hp;
	com.project.safetychain.webapp.pageobjects.LoginPage lp;
	ManageResourcePage manageResource;
	ManageLocationPage locations;
	ResourceDesignerPage resourceDesigner;

	public static String locationCategoryValue;
	public static String locationInstanceValue1;
	public static String locationInstanceValue2;
	public static String resourceCategoryValue;
	public static String resourceInstanceValue1;
	public static String resourceInstanceValue2;
	public static String chkFreeTxt, ParaTxt, chkSelectOne, SelectMultiple, Numeric, chkDate, chkTime, chkDateTime;

	public static String formName = "Automation_CheckForm_15.10_17.19.19";
	public static String ResourceName = "RInst2_15.10_13.47.04";
	public static String LocationName = "LInst1_15.10_13.47.04";
	String formtype = "Check";
	String modifiedby;
	public static String FormName = CommonMethods.dynamicText("Automation_CheckForm");

	@BeforeClass(alwaysRun = true)
	public void groupInit() {
		try {
			driver = launchbrowser();
			formDesignerPage = new FormDesignerPage(driver);
			locations = new ManageLocationPage(driver);
			manageResource = new ManageResourcePage(driver);

			resourceDesigner = new ResourceDesignerPage(driver);
			mainPage = new CommonPage(driver);
			 
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

			HashMap<String, List<String>> fields = new HashMap<String, List<String>>();
			fields.put(FIELD_TYPES.NUMERIC, Arrays.asList(Numeric));
			fields.put(FIELD_TYPES.FREE_TEXT, Arrays.asList(chkFreeTxt));
			fields.put(FIELD_TYPES.PARAGRAPH_TEXT, Arrays.asList(ParaTxt)); //
			fields.put(FIELD_TYPES.SELECT_ONE, Arrays.asList(chkSelectOne));

			fields.put(FIELD_TYPES.DATE, Arrays.asList(chkDate));
			fields.put(FIELD_TYPES.DATE_TIME, Arrays.asList(chkDateTime));

			HashMap<String, List<String>> fields1 = new HashMap<String, List<String>>();
			fields1.put(FIELD_TYPES.SELECT_MULTIPLE, Arrays.asList(SelectMultiple));

			FormFieldParams ffpChk = new FormFieldParams();
			ffpChk.FieldDetails = fields;
			ffpChk.SelectOneMultipleOptions = Arrays.asList("GOOD", "BAD");

			FormFieldParams ffpChk1 = new FormFieldParams();
			ffpChk1.FieldDetails = fields1;
			ffpChk1.SelectOneMultipleOptions = Arrays.asList("10", "20", "30");

			HashMap<String, List<String>> resource = new HashMap<String, List<String>>();
			resource.put(FORMRESOURCETYPES.CUSTOMERS, Arrays.asList(resourceCategoryValue));

			fp = new FormDesignParams();
			fp.FormType = FORMTYPES.CHECK;
			fp.FormName = FormName;
			fp.SelectResources = resource;
			fp.DesignFields = Arrays.asList(ffpChk, ffpChk1);
			fp.ReleaseForm = true;

			lp = new com.project.safetychain.webapp.pageobjects.LoginPage(driver);
			controlActions = new ControlActions(driver);

			 

			driver.close();
			appiumDriver = launchMobileApp();
		} catch (Exception e) {
			e.printStackTrace();
		}
		mobControlActions = new ControlActions_MobileApp(appiumDriver);

	}

	@Test(description = "Sample mobile app automation TC")
	public void TestCase_36790() throws Exception {

		RegistrationPage registrationPage = new RegistrationPage(appiumDriver);
		LoginPage loginPage = registrationPage.RegisterTenant("test1");
		HomePage homePage = new HomePage(appiumDriver);
		boolean login = loginPage.Login(mobileAppUsername, mobileAppPassword);
		TestValidation.IsTrue(login, "logged into the application with User " + mobileAppUsername, "Failed to log in");

		homePage.SearchForm(formName);
		homePage.ClickForm(formName);
		homePage.SearchResource(LocationName);
		homePage.SearchResource(ResourceName);

		homePage.enterFieldValue(homePage.NumericTxt, "8");
		homePage.enterFieldValue(homePage.paragraphTxt, "Para");

		homePage.enterFieldValue(homePage.freeText, "FreeText");
		homePage.selectOneOrMultipleFieldValues("SelectOne", "GOOD");
		homePage.clickFieldType(homePage.datePicker);
		homePage.clickFieldType(homePage.dateTimePicker); 
		ControlActions_MobileApp.swipe(400, 700, 400, 300);

		homePage.selectOneOrMultipleFieldValues("MutiSelect", "10");
		homePage.selectOneOrMultipleFieldValues2("MutiSelect", "30");

		homePage.submitForm();
		homePage.ClickSubMenu(homePage.submissionsSubMenu);
		homePage.SearchForm(formName);
		homePage.checkFormStatusFromSubmissionsSubMenu(formName);

	}

	@Test(description = "Sample mobile app automation TC")
	public void TestCase_36793() throws Exception {

		RegistrationPage registrationPage = new RegistrationPage(appiumDriver);
		LoginPage loginPage = registrationPage.RegisterTenant("test1");
		HomePage homePage = new HomePage(appiumDriver);
		boolean login = loginPage.Login(mobileAppUsername, mobileAppPassword);
		TestValidation.IsTrue(login, "logged into the application with User " + mobileAppUsername, "Failed to log in");

		homePage.SearchForm(formName);
		homePage.ClickForm(formName);
		homePage.SearchResource(LocationName);
		homePage.SearchResource(ResourceName);
		homePage.enterFieldValue(homePage.NumericTxt, "8");
		homePage.saveForm();
		homePage.ClickSubMenu(homePage.saveSubMenu);
		homePage.SearchForm(formName);
		 
	}

	@Test(description = "Sample mobile app automation TC")
	public void TestCase_02() throws Exception {

		RegistrationPage registrationPage = new RegistrationPage(appiumDriver);

		LoginPage loginPage = registrationPage.RegisterTenant("test1");

		HomePage homePage = new HomePage(appiumDriver);
		boolean login = loginPage.Login(mobileAppUsername, mobileAppPassword);
		TestValidation.IsTrue(login, "logged into the application with User " + mobileAppUsername, "Failed to log in");

		homePage.SearchForm(FormName);
		homePage.ClickForm(FormName);
		homePage.SearchResource(locationInstanceValue1);
		homePage.SearchResource(resourceInstanceValue1);
		homePage.enterFieldValue(homePage.NumericTxt, "8");
		homePage.enterFieldValue(homePage.paragraphTxt, "Para");
		homePage.enterFieldValue(homePage.freeText, "FreeText");

		homePage.clickFieldType(homePage.datePicker);
		homePage.clickFieldType(homePage.dateTimePicker);
		homePage.saveForm();
		homePage.ClickSubMenu(homePage.saveSubMenu);
		homePage.SearchForm(FormName);
		homePage.checkFormStatusFromSubmissionsSubMenu(FormName);

	}

	@AfterClass(alwaysRun = true)
	public void closeBrowser() throws InterruptedException {
		 appiumDriver.close();
	}

}
