package com.project.safetychain.pcapp.testcases;

import java.util.Arrays;

import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.project.safetychain.api.models.support.TCG_UserFlows_Wrapper;
import com.project.safetychain.api.models.support.Support.UserParams;
import com.project.safetychain.pcapp.pageobjects.CommonScreen;
import com.project.safetychain.pcapp.pageobjects.CommonScreen.SSOViewType;
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

public class TCG_REG_Login_UserLoginFlow1 extends TestBase{

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

			newUserName =  CommonMethods.dynamicText("PC_LoginFlowUser1");
			newUserPassword = "Test";
			newWGName =  CommonMethods.dynamicText("PC_LoginFlowWG1");

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

	@Test(description="Tenant Validation || Verify the tenant name validation")
	public void TestCase_30255() {

		boolean reLogin = loginScreen.reLogin(incorrectTenantName, correctUsername, correctPassword);
		TestValidation.IsFalse(reLogin, 
				"SUCCESSFUL tenant name validation", 
				"FAILED to validate tenant name");

		CommonScreen login = loginScreen.Login(correctTenantName, correctUsername, correctPassword);
		TestValidation.IsFalse(login.error, 
				"SUCCESSFUL login", 
				"FAILED to login");

	}

	@Test(description="Login || Verify the user credentials")
	public void TestCase_30256() {

		boolean reLogin = loginScreen.reLogin(incorrectUsername, incorrectPassword);
		TestValidation.IsFalse(reLogin, 
				"SUCCESSFUL login validation with incorrect username & password", 
				"FAILED to validate login with incorrect credentails");

		CommonScreen login =  loginScreen.Login(correctUsername, incorrectPassword);
		TestValidation.IsTrue(login.error, 
				"SUCCESSFUL login validation with correct username & incorrect password", 
				"FAILED to validate login with incorrect credentails");

		login = loginScreen.Login(correctUsername, correctPassword);
		TestValidation.IsFalse(login.error, 
				"SUCCESSFUL login with correct credentails", 
				"FAILED to login with correct credentails");

	}

	@Test(description="Login || Verify the user status and successful login")
	public void TestCase_30257() {
		CommonScreen newLogin;

		boolean logout = loginScreen.logOut();
		TestValidation.IsTrue(logout, 
				"SUCCESSFUL logout from application", 
				"FAILED to logout from application");

		boolean lockUser = loginScreen.tryToLoginUntilLock(correctUsername,incorrectPassword);
		TestValidation.IsTrue(lockUser, 
				"SUCCESSFUL locked the user", 
				"FAILED to lock the user");

		try {
			driver = launchbrowser();
			driver.get(pcAppTenantURL);

			webLoginPage = new LoginPage(driver);
			controlActionsWeb = new ControlActions(driver);
			webHomePage = webLoginPage.performLogin(pcAppUsername, pcAppPassword);
			webManageUserPage = webHomePage.clickUsersMenu();

			boolean unlockUser = webManageUserPage.unlockUser(correctUsername);
			TestValidation.IsTrue(unlockUser, 
					"UNLOCKED user - "+correctUsername+" from web app", 
					"FAILED to Unlocked user - "+correctUsername+" from web app");

			newLogin = loginScreen.Login(correctUsername, correctPassword);
			TestValidation.IsFalse(newLogin.error, 
					"SUCCESSFUL login to current tenant - "+correctTenantName+" after user unlock", 
					"FAILED to login to current tenant - "+correctTenantName+" after user unlock");

			logout = loginScreen.logOut();
			TestValidation.IsTrue(logout, 
					"SUCCESSFUL logout from application", 
					"FAILED to logout from application");

			boolean deactivateUser = webManageUserPage.deactivateUser(correctUsername);
			TestValidation.IsTrue(deactivateUser, 
					"DEACTIVATED user - "+correctUsername+" from web app", 
					"FAILED to deactivate user - "+correctUsername+" from web app");

			boolean verifyDeactivatedUser = loginScreen.verifyDeavtivatedUserLogin(correctUsername,correctPassword);
			TestValidation.IsTrue(verifyDeactivatedUser, 
					"SUCCESSFUL verified the deactivted user", 
					"FAILED to verify deactivated user");

			boolean reactivateUser = webManageUserPage.reactivateUser(correctUsername);
			TestValidation.IsTrue(reactivateUser, 
					"REACTIVATED user - "+correctUsername+" from web app", 
					"FAILED to reactivate user - "+correctUsername+" from web app");

		} catch (Exception e) {
			logError("Failed to perform user changes on web app - "+e.getMessage());	
		}finally {
			driver.close();
		}

		newLogin = loginScreen.Login(correctUsername, correctPassword);
		TestValidation.IsFalse(newLogin.error, 
				"SUCCESSFUL login to current tenant with user - "+correctUsername+" after user reactivation", 
				"FAILED to login to current tenant with user - "+correctUsername+" after user reactivation");

		boolean reLogin = loginScreen.reLogin(correctUsername,correctPassword);
		TestValidation.IsTrue(reLogin, 
				"SUCCESSFUL login with '"+correctUsername+"' user", 
				"FAILED to login with '"+correctUsername+"' user");

	}


	@Test(description="Login || Verify the user should able to login with different user and on different tenants (not in offline mode)")
	public void TestCase_30258() {

		boolean reLogin = loginScreen.reLogin(STAGE_Test1_TenantName,STAGE_Test1_CorrectUsername, STAGE_Test1_CorrectPassword);
		TestValidation.IsTrue(reLogin, 
				"SUCCESSFUL login on 'test1.stage' tenant", 
				"FAILED to login on 'test1.stage'");

		SettingsScreen selectSettingsTab  = commonScreen.selectSettings();
		TestValidation.IsFalse(selectSettingsTab.error,
				"OPENED 'Settings' Tab", 
				"COULD not open 'Settings' Tab");

		boolean changeOfflineModeToggle = selectSettingsTab.changeOfflineModeToggle(true);
		TestValidation.IsTrue(changeOfflineModeToggle, 
				"CHANGED 'Offline Mode' toggle - ON", 
				"FAILED to change''Offline Mode' toggle - ON");

		boolean logout = loginScreen.logOut();
		TestValidation.IsTrue(logout, 
				"SUCCESSFUL logout from application", 
				"FAILED to logout from application");

		try {
			LoginScreen selectTenant = loginScreen.selectTenant(otherTenantName);
			TestValidation.IsTrue(selectTenant.error, 
					"SUCCESSFUL validated tenant change in offline mode", 
					"FAILED to validate tenant change in offline mode");

			boolean verifyTenantErrorMessage = loginScreen.verifyTenantError();
			TestValidation.IsTrue(verifyTenantErrorMessage, 
					"SUCCESSFUL verified tenant change error message", 
					"FAILED to verify tenant change error message");
		}finally{
			PCAppDriver.close();
			PCAppDriver = launchPCApp();
			controlActionsPC = new ControlActions_PCApp(PCAppDriver);
			loginScreen = new LoginScreen(PCAppDriver);
			commonScreen = loginScreen.Login(STAGE_Test1_CorrectUsername, STAGE_Test1_CorrectPassword);

			TestValidation.IsTrue(reLogin, 
					"SUCCESSFUL login to the app in offline mode after app restarts", 
					"FAILED to login to the app in offline mode after app restarts");

			selectSettingsTab  = commonScreen.selectSettings();
			TestValidation.IsFalse(selectSettingsTab.error,
					"OPENED 'Settings' Tab", 
					"COULD not open 'Settings' Tab");

			changeOfflineModeToggle = selectSettingsTab.changeOfflineModeToggle(false);
			TestValidation.IsTrue(changeOfflineModeToggle, 
					"CHANGED 'Offline Mode' toggle - OFF", 
					"FAILED to change''Offline Mode' toggle - OFF");

			reLogin = loginScreen.reLogin(correctTenantName, correctUsername, correctPassword);
			TestValidation.IsTrue(reLogin, 
					"SUCCESSFUL re-login", 
					"FAILED to login");
		}

	}


	@Test(description="Login || Verify user should be able to login on test1.stage")
	public void TestCase_37949() {

		boolean reLogin = loginScreen.reLogin(STAGE_Test1_TenantName,STAGE_Test1_CorrectUsername, STAGE_Test1_CorrectPassword);
		TestValidation.IsTrue(reLogin, 
				"SUCCESSFUL login on 'test1.stage' tenant", 
				"FAILED to login on 'test1.stage'");

		reLogin = loginScreen.reLogin(correctTenantName,correctUsername, correctPassword);
		TestValidation.IsTrue(reLogin, 
				"SUCCESSFUL login to current tenant - "+correctTenantName, 
				"FAILED to login to current tenant - "+correctTenantName);

	}


	@Test(description="Login || Verify user should be able to see SSO login page for desktop view & web view")
	public void TestCase_39146() {

		boolean logout = loginScreen.logOut();
		TestValidation.IsTrue(logout, 
				"SUCCESSFUL logout from application", 
				"FAILED to logout from application");

		LoginScreen selectTenant = loginScreen.selectTenant(webViewSSOTenant);
		TestValidation.IsFalse(selectTenant.error, 
				"SUCCESSFUL selected tenant - "+webViewSSOTenant, 
				"FAILED to select tenant - "+webViewSSOTenant);

		boolean verifySSOPopUp = commonScreen.verifySSOLoginPopUp(SSOViewType.WEB_VIEW);
		TestValidation.IsTrue(verifySSOPopUp, 
				"VERIFIED Web View SSO Login Pop up", 
				"FAILED to verify Web View SSO Login Pop up");

		selectTenant = loginScreen.selectTenant(desktopViewSSOTenant);
		TestValidation.IsFalse(selectTenant.error, 
				"SUCCESSFUL selected tenant - "+desktopViewSSOTenant, 
				"FAILED to select tenant - "+desktopViewSSOTenant);

		verifySSOPopUp = commonScreen.verifySSOLoginPopUp(SSOViewType.DESKTOP_VIEW);
		TestValidation.IsTrue(verifySSOPopUp, 
				"VERIFIED Desktop View SSO Login Pop up", 
				"FAILED to verify Desktop View SSO Login Pop up");

		CommonScreen login = loginScreen.Login(correctTenantName, correctUsername, correctPassword);
		TestValidation.IsFalse(login.error, 
				"SUCCESSFUL logged on - "+correctTenantName, 
				"FAILED to login to application on - "+correctTenantName);

	}


	@Test(description="Login || Verify user should be able login in offline mode")
	public void TestCase_39503() {

		SettingsScreen selectSettingsTab  = commonScreen.selectSettings();
		TestValidation.IsFalse(selectSettingsTab.error,
				"OPENED 'Settings' Tab", 
				"COULD not open 'Settings' Tab");

		boolean changeOfflineModeToggle = selectSettingsTab.changeOfflineModeToggle(true);
		TestValidation.IsTrue(changeOfflineModeToggle, 
				"CHANGED 'Offline Mode' toggle - ON", 
				"FAILED to change''Offline Mode' toggle - ON");

		boolean reLogin = loginScreen.reLogin(correctUsername, correctPassword);
		TestValidation.IsTrue(reLogin, 
				"SUCCESSFUL login in''Offline Mode'", 
				"FAILED to login in''Offline Mode'");

		selectSettingsTab  = commonScreen.selectSettings();
		TestValidation.IsFalse(selectSettingsTab.error,
				"OPENED 'Settings' Tab", 
				"COULD not open 'Settings' Tab");

		changeOfflineModeToggle = selectSettingsTab.changeOfflineModeToggle(false);
		TestValidation.IsTrue(changeOfflineModeToggle, 
				"CHANGED 'Offline Mode' toggle - OFF", 
				"FAILED to change''Offline Mode' toggle - OFF");
	}


	@Test(description="Login || Verify SSO user login")
	public void TestCase_39504() {

		boolean reLogin = loginScreen.reLoginSSO(STAGE_Test1_TenantName,STAGE_Test1_SSOUsername, STAGE_Test1_SSOPassword);
		TestValidation.IsTrue(reLogin, 
				"SUCCESSFUL login on 'test1.stage' tenant with SSO user", 
				"FAILED to login on 'test1.stage' with SSO user");

		SettingsScreen selectSettingsTab  = commonScreen.selectSettings();
		TestValidation.IsFalse(selectSettingsTab.error,
				"OPENED 'Settings' Tab", 
				"COULD not open 'Settings' Tab");

		boolean changeOfflineModeToggle = selectSettingsTab.changeOfflineModeToggle(true);
		TestValidation.IsTrue(changeOfflineModeToggle, 
				"CHANGED 'Offline Mode' toggle - ON", 
				"FAILED to change''Offline Mode' toggle - ON");

		reLogin = loginScreen.reLogin(STAGE_Test1_TenantName, STAGE_Test1_CorrectUsername, STAGE_Test1_CorrectPassword);
		TestValidation.IsTrue(reLogin, 
				"SUCCESSFUL re-login with internal user on test1.stage", 
				"FAILED to login with internal user on test1.stage");

		selectSettingsTab  = commonScreen.selectSettings();
		TestValidation.IsFalse(selectSettingsTab.error,
				"OPENED 'Settings' Tab", 
				"COULD not open 'Settings' Tab");

		changeOfflineModeToggle = selectSettingsTab.changeOfflineModeToggle(false);
		TestValidation.IsTrue(changeOfflineModeToggle, 
				"CHANGED 'Offline Mode' toggle - OFF", 
				"FAILED to change''Offline Mode' toggle - OFF");

		reLogin = loginScreen.reLogin(correctTenantName, correctUsername, correctPassword);
		TestValidation.IsTrue(reLogin, 
				"SUCCESSFUL re-login with internal user on "+correctTenantName, 
				"FAILED to login with internal user on "+correctTenantName);

	}


	@Test(description="Login || Verify user should be able login in offline mode")
	public void TestCase_39896() {

		SettingsScreen selectSettingsTab  = commonScreen.selectSettings();
		TestValidation.IsFalse(selectSettingsTab.error,
				"OPENED 'Settings' Tab", 
				"COULD not open 'Settings' Tab");

		boolean changeOfflineModeToggle = selectSettingsTab.changeOfflineModeToggle(true);
		TestValidation.IsTrue(changeOfflineModeToggle, 
				"CHANGED 'Offline Mode' toggle - ON", 
				"FAILED to change''Offline Mode' toggle - ON");

		boolean reLogin = loginScreen.reLogin(correctUsername, correctPassword);
		TestValidation.IsTrue(reLogin, 
				"SUCCESSFUL login in''Offline Mode'", 
				"FAILED to login in''Offline Mode'");

		selectSettingsTab  = commonScreen.selectSettings();
		TestValidation.IsFalse(selectSettingsTab.error,
				"OPENED 'Settings' Tab", 
				"COULD not open 'Settings' Tab");

		changeOfflineModeToggle = selectSettingsTab.changeOfflineModeToggle(false);
		TestValidation.IsTrue(changeOfflineModeToggle, 
				"CHANGED 'Offline Mode' toggle - OFF", 
				"FAILED to change''Offline Mode' toggle - OFF");

	}

	@Test(description="Login || Offline Mode || Verify user should not able to login with incorrect password")
	public void TestCase_41912() {

		SettingsScreen selectSettingsTab  = commonScreen.selectSettings();
		TestValidation.IsFalse(selectSettingsTab.error,
				"OPENED 'Settings' Tab", 
				"COULD not open 'Settings' Tab");

		boolean changeOfflineModeToggle = selectSettingsTab.changeOfflineModeToggle(true);
		TestValidation.IsTrue(changeOfflineModeToggle, 
				"CHANGED 'Offline Mode' toggle - ON", 
				"FAILED to change''Offline Mode' toggle - ON");


		boolean reLogin = loginScreen.reLogin(correctUsername, incorrectPassword);
		TestValidation.IsFalse(reLogin, 
				"SUCCESSFUL login validation 'Offline Mode'", 
				"FAILED to validate login in ''Offline Mode'");

		CommonScreen login = loginScreen.Login(correctUsername, correctPassword);
		TestValidation.IsFalse(login.error, 
				"SUCCESSFUL login in''Offline Mode'", 
				"FAILED to login in''Offline Mode'");

		selectSettingsTab  = commonScreen.selectSettings();
		TestValidation.IsFalse(selectSettingsTab.error,
				"OPENED 'Settings' Tab", 
				"COULD not open 'Settings' Tab");

		changeOfflineModeToggle = selectSettingsTab.changeOfflineModeToggle(false);
		TestValidation.IsTrue(changeOfflineModeToggle, 
				"CHANGED 'Offline Mode' toggle - OFF", 
				"FAILED to change''Offline Mode' toggle - OFF");

	}

	@AfterClass(alwaysRun = true)
	public void closeBrowser() throws InterruptedException {
		PCAppDriver.close();
	}

}
