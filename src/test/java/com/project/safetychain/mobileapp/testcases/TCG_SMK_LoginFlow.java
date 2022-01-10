package com.project.safetychain.mobileapp.testcases;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.project.safetychain.mobileapp.pageobjects.LoginPage;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.utilities.ApiUtils;
import com.project.utilities.ControlActions_MobileApp;

public class TCG_SMK_LoginFlow extends TestBase {
	ControlActions_MobileApp mobControlActions;
	ApiUtils apiUtils = null;
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
	public static String copyrightLabel = "© SafetyChain Software, Inc 2021";

	@Test(description = "Verify Relogin Functionality", priority = 1)
	public void TestCase_37046() throws Exception {
		appiumDriver = launchMobileAppReinstall();
		mobControlActions = new ControlActions_MobileApp(appiumDriver);
		HashMap<String, Integer> DownCount = new HashMap<String, Integer>();
		HashMap<String, Integer> DownCountAfter = new HashMap<String, Integer>();

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
		DownCount = loginPage.CountDownloadingForms();
		logInfo("Total Forms Count After 1st Login: " + DownCount.get("Forms"));
		ControlActions_MobileApp.waitForVisibility(loginPage.mainMenu, 60000);
		boolean formsGrid = loginPage.FormsGridAfterLogin();
		TestValidation.IsTrue(formsGrid, "Forms are present after login on AllForms Tab",
				"Forms are not present after login on AllForms Tab");

		loginPage.logOut();
		DownCountAfter = loginPage.VerifyReLoginFunctionality(mobileAppUsername, mobileAppPassword, DownCount);

		loginPage.calculateNewlyDownloadedForms(DownCount.get("Forms"), DownCountAfter.get("Forms"));
		ControlActions_MobileApp.waitForVisibility(loginPage.mainMenu, 60000);
		boolean formsGridRelogin = loginPage.FormsGridAfterLogin();
		TestValidation.IsTrue(formsGridRelogin, "Forms are present after login on AllForms Tab",
				"Forms are not present after login on AllForms Tab");
	}

	@Test(description = "Verify in hamburger menu Count is displayed beside Inbox Submission Saved Favorites Forms", priority = 2)
	public void TestCase_36804() throws Exception {

		appiumDriver = launchMobileApp();
		mobControlActions = new ControlActions_MobileApp(appiumDriver);
		HashMap<String, Integer> DownCount = new HashMap<>();

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
		DownCount = loginPage.CountDownloadingForms();
		logInfo("Total Downloading Count : " + DownCount);

		boolean isVerified = loginPage.checkHumbergMenuCount(DownCount);
		logInfo("Both counts are same " + isVerified);
		TestValidation.IsTrue(isVerified, "Hamburger Menu Count has been Verified",
				"Failed to verify Count from Hamburger Menu");
	}

	@AfterMethod(alwaysRun = true)
	public void closeAppium() throws InterruptedException {

		appiumDriver.closeApp();
	}

	@AfterClass(alwaysRun = true)
	public void closeBrowser() throws InterruptedException {

	}

}
