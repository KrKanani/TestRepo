package com.project.safetychain.mobileapp.testcases;

import java.util.HashMap;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.project.safetychain.mobileapp.pageobjects.HomePage;
import com.project.safetychain.mobileapp.pageobjects.LoginPage;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.utilities.ApiUtils;
import com.project.utilities.ControlActions_MobileApp;

public class TCG_REG_LoginFlow extends TestBase {
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

	public final String ssoUsername = "abhishek.pashine";
	public final String ssoPassword = "SSOPassword123#";

	@Test(priority = 1, description = "Verify in hamburger menu Count is displayed beside Inbox Submission Saved Favorites Forms", enabled = true)

	public void TestCase_37336_38_39_43_44_86() throws Exception {
		appiumDriver = launchMobileApp();
		mobControlActions = new ControlActions_MobileApp(appiumDriver);
		HashMap<String, Integer> DownCount = new HashMap<>();
		LoginPage loginPage = new LoginPage(appiumDriver);
		boolean copyrightLbl = loginPage.verifyCopyrightLbl(copyrightLabel);
		TestValidation.IsTrue(copyrightLbl, "copyright Label has verified successfully",
				"Failed to verify copyright label");

		boolean loginFunct = loginPage.verifyLoginFunctionality(mobileAppUsername, mobileAppPassword);
		TestValidation.IsTrue(loginFunct, "Login Functionality verified Successfully",
				"Failed to verify login functionality");
		boolean logout = loginPage.logOut();
		TestValidation.IsTrue(logout, "Logout Functionality verified  Successfully",
				"Failed to verify logout functionality");
		loginPage.VerifyReLoginFunctionality(mobileAppUsername, mobileAppPassword, DownCount);
	}

	@Test(priority = 4, description = "SSO -Verify Forms screen is displayed once userenter  Valid SSO credentials", enabled = true)

	public void TestCase_37340() throws Exception {
		appiumDriver = launchMobileApp();
		mobControlActions = new ControlActions_MobileApp(appiumDriver);
		LoginPage loginPage = new LoginPage(appiumDriver);
		if (!(loginPage.tenantTxt.getText().equalsIgnoreCase("test1.stage"))) {
			ControlActions_MobileApp.waitForVisibility(loginPage.profileSettingBtn, 100);
			ControlActions_MobileApp.WaitforelementToBeClickable(loginPage.profileSettingBtn);
			ControlActions_MobileApp.click(loginPage.profileSettingBtn);
			loginPage.updateTenantName("test1.stage");
		}
		boolean ssoLogin1 = loginPage.SSO_Login(ssoUsername, ssoPassword);
		TestValidation.IsTrue(ssoLogin1, "logged into the application with User " + mobileAppUsername,
				"Failed to log in");

		boolean logout = loginPage.logOut();
		TestValidation.IsTrue(logout, "Logout Functionality verified Successfully",
				"Failed to verify logout functionality");
		boolean ssoLogin2 = loginPage.SSO_Login(ssoUsername, ssoPassword);
		TestValidation.IsTrue(ssoLogin2, "logged into the application with User " + mobileAppUsername,
				"Failed to log in");
		boolean logout1 = loginPage.logOut();
		TestValidation.IsTrue(logout1, "Logout Successfully", "Failed to  logout ");
		appiumDriver.navigate().back();
	}

	@Test(priority = 2, description = "Verify \" Remember Me\" toggle switch")
	public void TestCase_37337() throws Exception {

		appiumDriver = launchMobileApp();
		mobControlActions = new ControlActions_MobileApp(appiumDriver);

		LoginPage loginPage = new LoginPage(appiumDriver);
		if ((loginPage.tenantTxt.getText().equalsIgnoreCase("test1.stage"))) {
			Thread.sleep(10000);
			appiumDriver.navigate().back();
		}
		boolean RememberMeLogin = loginPage.verifyRememberMeFunctionality(mobileAppUsername, mobileAppPassword);
		TestValidation.IsTrue(RememberMeLogin, "logged into the application with User " + mobileAppUsername,
				"Failed to log in");

	}

	@Test(description = "Verify web view SSO login page", priority = 3)
	public void TestCase_37342() throws Exception {

		appiumDriver = launchMobileApp();
		mobControlActions = new ControlActions_MobileApp(appiumDriver);
		LoginPage loginPage = new LoginPage(appiumDriver);
		HomePage hp = new HomePage(appiumDriver);

		String ssoTenant = "test1.stage";
		try {
			boolean setssoTenant = loginPage.setNewTenant(ssoTenant);
			TestValidation.IsTrue(setssoTenant, "Successfully set the tenant as SSO tenant -" + ssoTenant,
					"Failed to se to set the tenant as SSo tenant ");

			boolean verifySsoLoginPage = loginPage.verifySSOLoginPage(ssoTenant);
			TestValidation.IsTrue(verifySsoLoginPage, "Verified the Login page for SSo Tenant -" + ssoTenant,
					"Failed to verify the login page for SSO tenat -" + ssoPassword);

			boolean doSSOLogin = loginPage.SSO_Login(ssoUsername, ssoPassword);
			TestValidation.IsTrue(doSSOLogin, "Logged In for SSO tenant -" + ssoTenant,
					"Failed to login for SSO tenant");

			boolean verifyHomePage = hp.verifyAppHomePage();
			TestValidation.IsTrue(verifyHomePage, "Verified Mobile App Homepage After Logging in",
					"Failed to verify Mobile App Homepage");

			boolean logout = loginPage.logOut();
			TestValidation.IsTrue(logout, "Logged Out of applictaion", "Failed to logout of application");

			boolean closeSSOTenant = loginPage.closeSSOLoginPage(ssoTenant);
			TestValidation.IsTrue(closeSSOTenant, "Closed SSO Tenant -" + ssoTenant + " Login Page",
					"Failed to close SSO Tenant Login Page");
		} finally {
			boolean setTenant = loginPage.setNewTenant(mobileApp_Tenant);
			TestValidation.IsTrue(setTenant, "Successfully set the tenant as -" + mobileApp_Tenant,
					"Failed to set the tenant as -" + mobileApp_Tenant);
		}
	}

	@AfterMethod(alwaysRun = true)
	public void closeAppium() throws InterruptedException {

		appiumDriver.closeApp();
	}

	@AfterClass(alwaysRun = true)
	public void closeBrowser() throws InterruptedException {

	}

}
