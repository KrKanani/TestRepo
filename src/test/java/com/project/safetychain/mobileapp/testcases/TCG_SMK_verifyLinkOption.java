package com.project.safetychain.mobileapp.testcases;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.project.safetychain.mobileapp.pageobjects.LoginPage;
import com.project.safetychain.mobileapp.pageobjects.SavedPage;
import com.project.safetychain.mobileapp.pageobjects.verifyLinkOptionPage;
import com.project.safetychain.webapp.pageobjects.CommonPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserFormsPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FormDesignParams;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FormFieldParams;
import com.project.safetychain.webapp.pageobjects.ManageLocationPage;
import com.project.safetychain.webapp.pageobjects.ManageResourcePage;
import com.project.safetychain.webapp.pageobjects.ResourceDesignerPage;
import com.project.safetychain.webapp.testcases.support.FormOperations;
import com.project.safetychain.webapp.testcases.support.FormOperations.FormDetails;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.utilities.ControlActions;
import com.project.utilities.ControlActions_MobileApp;

public class TCG_SMK_verifyLinkOption extends TestBase {

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

	String formtype = "Check";
	String modifiedby;
	public static String FormName;

	@BeforeClass(alwaysRun = true)
	public void groupInit() {
		try {

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test(description = "Verify Link option in Hamburger menu")
	public void TestCase_36823() throws Exception {
		appiumDriver = launchMobileApp();

		LoginPage loginPage = new LoginPage(appiumDriver);
		mobControlActions = new ControlActions_MobileApp(appiumDriver);
		if (!(loginPage.tenantTxt.getText().equalsIgnoreCase("waynefarms"))) {
			ControlActions_MobileApp.waitForVisibility(loginPage.profileSettingBtn, 100);
			ControlActions_MobileApp.click(loginPage.profileSettingBtn);
			loginPage.updateTenantName("waynefarms");
		}

		boolean login = loginPage.LoginForLinkFunctionality(waynefarms_UserName, waynefarms_Password);
		TestValidation.IsTrue(login, "logged into the application with User " + mobileAppUsername, "Failed to log in");

		// Click on Hamburger menu

		verifyLinkOptionPage verifyLinkOptionPageObj = new verifyLinkOptionPage(appiumDriver);
		Boolean Hamburger = verifyLinkOptionPageObj.Hamburger();
		TestValidation.IsTrue(Hamburger, "Hamburger clicked successfully", "Failed to click Hamburger");

		// Click on link
		Boolean ClickLink = verifyLinkOptionPageObj.Link();
		TestValidation.IsTrue(ClickLink, "Link clicked successfully", "Failed to click link");

		// check for page load

		Boolean sidebar = verifyLinkOptionPageObj.Linkpage();
		TestValidation.IsTrue(sidebar, "Sidebar is visible", "sidebar not visible");

	}

	@AfterMethod(alwaysRun = true)
	public void closeAppium() throws InterruptedException {
		appiumDriver.closeApp();
	}

	@AfterClass(alwaysRun = true)
	public void closeBrowser() throws InterruptedException {

	}

}