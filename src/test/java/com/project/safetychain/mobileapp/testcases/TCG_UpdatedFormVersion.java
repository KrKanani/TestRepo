package com.project.safetychain.mobileapp.testcases;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.project.safetychain.mobileapp.pageobjects.HomePage;
import com.project.safetychain.mobileapp.pageobjects.LoginPage;
import com.project.safetychain.mobileapp.pageobjects.SavedPage;
import com.project.safetychain.mobileapp.pageobjects.UpdatedFormVersionPage;
import com.project.safetychain.webapp.pageobjects.CommonPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserFormsPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.COLUMNHEADER;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.COLUMNSETTING;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FIELD_TYPES;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FORMRESOURCETYPES;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FORMTYPES;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FormDesignParams;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FormFieldParams;
import com.project.safetychain.webapp.pageobjects.ManageLocationPage;
import com.project.safetychain.webapp.pageobjects.ManageResourcePage;
import com.project.safetychain.webapp.pageobjects.ResourceDesignerPage;
import com.project.safetychain.webapp.testcases.support.FormOperations;
import com.project.safetychain.webapp.testcases.support.FormOperations.FormDetails;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.utilities.CommonMethods;
import com.project.utilities.ControlActions;
import com.project.utilities.ControlActions_MobileApp;

public class TCG_UpdatedFormVersion extends TestBase {

	ControlActions_MobileApp mobControlActions;

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

	public static String formName = "Automation_CheckForm_15.10_17.19.19_1";
	public static String ResourceName = "RInst2_15.10_13.47.04";
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

	@Test(description = "UpdatedFormVersion")
	public void TestCase_36802() throws Exception {
		appiumDriver = launchMobileApp();
		LoginPage loginPage = new LoginPage(appiumDriver);
		HomePage homePage = new HomePage(appiumDriver);
		mobControlActions = new ControlActions_MobileApp(appiumDriver);
		boolean login = loginPage.Login(mobileAppUsername, mobileAppPassword);
		TestValidation.IsTrue(login, "logged into the application with User " + mobileAppUsername, "Failed to log in");

		// Form details

		homePage.SearchForm(formName);

		// Version Check

		UpdatedFormVersionPage UpdatedFormVersionPageObj = new UpdatedFormVersionPage(appiumDriver);

		UpdatedFormVersionPageObj.FirstVersion();

		// Browser code

		hp.clickFSQABrowserMenu();

		boolean navigate = fbp.selectResourceDropDownandNavigate("CUSTOMERS", "Forms");
		TestValidation.IsTrue(navigate, "Navigated to FSQABrowser>FormsTab",
				"Failed to navigate to FSQABrowser>Forms Tab");

		boolean applyfilter = fbp.openAndApplySettingsForColumn(COLUMNHEADER.FORMNAME, COLUMNSETTING.FILTER, formName);
		TestValidation.IsTrue(applyfilter, "Applied Filter on" + COLUMNHEADER.FORMNAME,
				"Failed to apply filter on" + COLUMNHEADER.FORMNAME);

		String version = fbp.getGridValueForColumn(COLUMNHEADER.VERSION);

		float expected_version = (Float.parseFloat(version));

		Float expected_version1 = expected_version + 1;

		logInfo("Expected Version =" + expected_version1);

		boolean editform = fbp.editForm(formName, formDesignerPage);
		TestValidation.IsTrue(editform, "Form Edited", "Failed to edit form");

		hp.clickFSQABrowserMenu();

		boolean navigate1 = fbp.selectResourceDropDownandNavigate("CUSTOMERS", "Forms");
		TestValidation.IsTrue(navigate1, "Navigated to FSQABrowser>FormsTab",
				"Failed to navigate to FSQABrowser>Forms Tab");

		boolean applyfilter1 = fbp.openAndApplySettingsForColumn(COLUMNHEADER.FORMNAME, COLUMNSETTING.FILTER, formName);
		TestValidation.IsTrue(applyfilter1, "Applied Filter on" + COLUMNHEADER.FORMNAME,
				"Failed to apply filter on" + COLUMNHEADER.FORMNAME);

		boolean verifyversion = fbp.verifyGridValuesForColumn(COLUMNHEADER.VERSION, expected_version1.toString());
		TestValidation.IsTrue(verifyversion, "Verified version" + expected_version1.toString(),
				"Failed to verifiy version" + expected_version1.toString());
		mainPage.Sync();

		// Version Check
		homePage.SearchForm(formName);
		ControlActions_MobileApp.swipeScreen("DOWN");
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
		driver.close();

	}
}