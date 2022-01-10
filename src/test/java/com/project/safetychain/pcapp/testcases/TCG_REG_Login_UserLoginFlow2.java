package com.project.safetychain.pcapp.testcases;

import java.util.Arrays;

import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.project.safetychain.api.models.support.TCG_UserFlows_Wrapper;
import com.project.safetychain.api.models.support.Support.UserParams;
import com.project.safetychain.pcapp.pageobjects.CommonScreen;
import com.project.safetychain.pcapp.pageobjects.LoginScreen;
import com.project.safetychain.pcapp.pageobjects.SettingsScreen;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage;
import com.project.safetychain.webapp.pageobjects.HomePage;
import com.project.safetychain.webapp.pageobjects.LoginPage;
import com.project.safetychain.webapp.pageobjects.UserManagerPage;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.utilities.ApiUtils;
import com.project.utilities.CommonMethods;
import com.project.utilities.ControlActions;
import com.project.utilities.ControlActions_PCApp;

public class TCG_REG_Login_UserLoginFlow2 extends TestBase{

	ApiUtils apiUtils = null;

	ControlActions_PCApp controlActionsPC;
	CommonScreen commonScreen;
	LoginScreen loginScreen;

	ControlActions controlActionsWeb;
	LoginPage webLoginPage;
	HomePage webHomePage;
	FSQABrowserPage webfsqaFormsPage;
	UserManagerPage webManageUserPage;

	public static String newUserName, newUserPassword, newWGName;

	public static String correctTenantName;
	public static String correctUsername, correctPassword;

	public static String incorrectTenantName;
	public static String incorrectUsername, incorrectPassword;

	public static String desktopViewSSOTenant, webViewSSOTenant;

	public static String STAGE_Test1_TenantName;
	public static String STAGE_Test1_CorrectUsername, STAGE_Test1_CorrectPassword;

	public static String STAGE_Test1_SSOUsername, STAGE_Test1_SSOPassword;

	public static String otherTenantName;

	@BeforeClass(alwaysRun = true)
	public void groupInit() {
		try {

			apiUtils = new ApiUtils();

			newUserName =  CommonMethods.dynamicText("PC_LoginFlowUser");
			newUserPassword = "Test";
			newWGName =  CommonMethods.dynamicText("PC_LoginFlowWG");

			correctTenantName = pcAppTenantname;
			correctUsername = newUserName;
			correctPassword = newUserPassword;

			incorrectTenantName = "tst1.stg";
			incorrectUsername = "TESTB8";
			incorrectPassword = "0aA";

			desktopViewSSOTenant = pcAppSSOTenantname_DesktopView;
			webViewSSOTenant = pcAppSSOTenantname_WebView;

			STAGE_Test1_TenantName = "test1.stage";
			STAGE_Test1_CorrectUsername = test1STAGEUsername;
			STAGE_Test1_CorrectPassword = test1STAGEPassword;

			STAGE_Test1_SSOUsername = pcAppSSOUsername;
			STAGE_Test1_SSOPassword = pcAppSSOPassword;

			otherTenantName = "mobile.stage";

			TCG_UserFlows_Wrapper userCreationWrapper = new TCG_UserFlows_Wrapper();
			UserParams userDetails = new UserParams();
			userDetails.Username = newUserName;
			userDetails.ClearPassword = newUserPassword;
			userDetails.FirstName = "UWP LOGIN";
			userDetails.LastName = "User";
			userDetails.Email = "loginuser@pcapp.com";
			userDetails.TimeZone = "U.S. Pacific";
			userDetails.LocationNmIds = Arrays.asList("ALL");
			userDetails.Roles = Arrays.asList("SuperAdmin");
			userDetails.WorkGroupNames = Arrays.asList(newWGName);

			boolean userCreation = false;

			try {
				userCreationWrapper.create_User_Wrapper(userDetails);
				userCreation = true;
				logInfo("'"+newUserName+"' User is created");
			} catch (InterruptedException e) {
				logError("Error while '"+newUserName+"' user creation");
				userCreation = false;
			}
			TestValidation.IsTrue(userCreation, 
					"CREATED User - " + newUserName, 
					"Could NOT create user - " + newUserName);

			PCAppDriver = launchPCApp();
			controlActionsPC = new ControlActions_PCApp(PCAppDriver);

			loginScreen = new LoginScreen(PCAppDriver);
			commonScreen = loginScreen.Login(pcAppTenantname, correctUsername, correctPassword);

			if(commonScreen.error) {
				TCGFailureMsg = "COULD not login to the application";
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}

		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Test(description="SSO Login - Desktop View || Verify user should get log out after app restarts after SSO user login")
	public void TestCase_48661() {

		boolean reLogin = loginScreen.reLoginSSO(pcAppSSOTenantname, pcAppSSOUsername, pcAppSSOPassword);
		TestValidation.IsTrue(reLogin, 
				"SUCCESSFUL logged in with SSO User", 
				"FAILED to logged in with SSO User");

		PCAppDriver.close();

		PCAppDriver = launchPCApp();
		controlActionsPC = new ControlActions_PCApp(PCAppDriver);
		loginScreen = new LoginScreen(PCAppDriver);

		commonScreen = loginScreen.loginSSO(pcAppSSOTenantname, pcAppSSOUsername, pcAppSSOPassword);
		TestValidation.IsFalse(commonScreen.error, 
				"SUCCESSFUL logged in with SSO User(User gets log out after app restart)", 
				"FAILED to logged in with SSO User in correct way(After app restart)");

		reLogin = loginScreen.reLogin(correctTenantName, correctUsername, correctPassword);
		TestValidation.IsTrue(reLogin, 
				"SUCCESSFUL login", 
				"FAILED to login");
	}

	@Test(description="SSO Login - Desktop View || Verify user should get log out after app restarts after Internal user login")
	public void TestCase_48662() {

		PCAppDriver.close();

		PCAppDriver = launchPCApp();
		controlActionsPC = new ControlActions_PCApp(PCAppDriver);
		loginScreen = new LoginScreen(PCAppDriver);

		commonScreen = loginScreen.Login(correctTenantName, correctUsername, correctPassword);
		TestValidation.IsFalse(commonScreen.error, 
				"SUCCESSFUL logged in with Internal User(User gets log out after app restart)", 
				"FAILED to logged in with Internal User in correct way(After app restart)");

	}


	@Test(description="SSO Login - Web View || Verify user should get log out after app restarts after internal user login(SSO user logout) in offline mode")
	public void TestCase_48663() {

		boolean reLogin = loginScreen.reLoginSSO(pcAppSSOTenantname, pcAppSSOUsername, pcAppSSOPassword);
		TestValidation.IsTrue(reLogin, 
				"SUCCESSFUL logged in with SSO User", 
				"FAILED to logged in with SSO User");

		SettingsScreen selectSettingsTab  = commonScreen.selectSettings();
		TestValidation.IsFalse(selectSettingsTab.error,
				"OPENED 'Settings' Tab", 
				"COULD not open 'Settings' Tab");

		boolean changeOfflineModeToggle = selectSettingsTab.changeOfflineModeToggle(true);
		TestValidation.IsTrue(changeOfflineModeToggle, 
				"CHANGED 'Offline Mode' toggle - ON", 
				"FAILED to change''Offline Mode' toggle - ON");

		reLogin = loginScreen.reLogin(correctTenantName, correctUsername, correctPassword);
		TestValidation.IsTrue(reLogin, 
				"SUCCESSFUL login", 
				"FAILED to login");

		changeOfflineModeToggle = selectSettingsTab.changeOfflineModeToggle(false);
		TestValidation.IsTrue(changeOfflineModeToggle, 
				"CHANGED 'Offline Mode' toggle - OFF", 
				"FAILED to change''Offline Mode' toggle - OFF");

		PCAppDriver.close();

		PCAppDriver = launchPCApp();
		controlActionsPC = new ControlActions_PCApp(PCAppDriver);
		loginScreen = new LoginScreen(PCAppDriver);

		commonScreen = loginScreen.loginSSO(pcAppSSOTenantname, pcAppSSOUsername, pcAppSSOPassword);
		TestValidation.IsFalse(commonScreen.error, 
				"SUCCESSFUL logged in with SSO User(User gets log out after app restart)", 
				"FAILED to logged in with SSO User in correct way(After app restart)");

		reLogin = loginScreen.reLogin(correctTenantName, correctUsername, correctPassword);
		TestValidation.IsTrue(reLogin, 
				"SUCCESSFUL login", 
				"FAILED to login");
	}

	@Test(description="SSO Login || WebView for SSO || Verify login screen when user clicks on 'Use Single Sign On' multiple times")
	public void TestCase_48820() {

		boolean logout = loginScreen.logOut();
		TestValidation.IsTrue(logout, 
				"SUCCESSFUL logged out", 
				"FAILED to log out");

		loginScreen = loginScreen.selectTenant(webViewSSOTenant);
		TestValidation.IsFalse(loginScreen.error, 
				"SUCCESSFULLY opened Web View Tenant - "+webViewSSOTenant, 
				"FAILED to open Web View Tenant");

		boolean multipleSSOClick = loginScreen.performMultipleClickSSO();
		TestValidation.IsTrue(multipleSSOClick, 
				"SUCCESSFULLY performed multiple clicks on SSO link", 
				"FAILED to perform multiple clicks on SSO link");

		boolean verifyCloseSSOLogin =	loginScreen.verifyCloseWebSSOLogin();
		TestValidation.IsTrue(verifyCloseSSOLogin, 
				"VERIFIED Web View SSO Login pop up", 
				"FAILED to verify Web View SSO Login pop up");

		boolean reLogin = loginScreen.reLogin(correctTenantName, correctUsername, correctPassword);
		TestValidation.IsTrue(reLogin, 
				"SUCCESSFUL login", 
				"FAILED to login");
	}

	@AfterClass(alwaysRun = true)
	public void closeBrowser() throws InterruptedException {
		PCAppDriver.close();
	}

}
