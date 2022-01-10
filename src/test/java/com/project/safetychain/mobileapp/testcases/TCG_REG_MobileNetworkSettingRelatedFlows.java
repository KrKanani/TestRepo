package com.project.safetychain.mobileapp.testcases;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.project.safetychain.api.models.Auth;
import com.project.safetychain.api.models.FormDesigner;
import com.project.safetychain.api.models.ManageLocation;
import com.project.safetychain.api.models.ManageResources;
import com.project.safetychain.api.models.support.Support.DPT_FIELD_TYPES;
import com.project.safetychain.api.models.support.Support.DPT_FORM_TYPES;
import com.project.safetychain.api.models.support.Support.FormFieldParams;
import com.project.safetychain.api.models.support.Support.FormParams;
import com.project.safetychain.api.models.support.Support.RESOURCE_TYPES;
import com.project.safetychain.api.models.support.Support.UserParams;
import com.project.safetychain.apiproject.models.Users;
import com.project.safetychain.apiproject.models.Users.UserDetails;
import com.project.safetychain.apiproject.models.Users.UserType;
import com.project.safetychain.api.models.support.TCG_FormCreation_Wrapper;
import com.project.safetychain.api.models.support.TCG_UserFlows_Wrapper;
import com.project.safetychain.mobileapp.pageobjects.HomePage;
import com.project.safetychain.mobileapp.pageobjects.LoginPage;
import com.project.safetychain.mobileapp.pageobjects.SavedPage;
import com.project.safetychain.mobileapp.pageobjects.SettingsPage;
import com.project.safetychain.webapp.pageobjects.CommonPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserFormsPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FormDesignParams;
//import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FormDesignParams;
//import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FormFieldParams;
import com.project.safetychain.webapp.pageobjects.ManageLocationPage;
import com.project.safetychain.webapp.pageobjects.ManageResourcePage;
import com.project.safetychain.webapp.pageobjects.ResourceDesignerPage;
import com.project.safetychain.webapp.testcases.support.FormOperations;
import com.project.safetychain.webapp.testcases.support.FormOperations.FormDetails;
import com.project.testbase.TestBase;
//import com.project.testbase.TestBaseApi;
import com.project.testbase.TestValidation;
import com.project.utilities.ApiUtils;
import com.project.utilities.CommonMethods;
import com.project.utilities.ControlActions;
import com.project.utilities.ControlActions_MobileApp;
import com.project.utilities.JSONUtils;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.connection.ConnectionState;
import io.appium.java_client.android.connection.ConnectionStateBuilder;
import io.appium.java_client.android.connection.HasNetworkConnection;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.android.connection.*;

public class TCG_REG_MobileNetworkSettingRelatedFlows extends TestBase {

	ControlActions_MobileApp mobControlActions;

	Auth auth = null;
	ApiUtils apiUtils = null;
	JSONUtils jsonUtils = null;

	Users internalUser = null, supplierUser = null;

	UserDetails userDetails = null;

	ControlActions controlActions;
	com.project.safetychain.webapp.pageobjects.HomePage hp;
	com.project.safetychain.webapp.pageobjects.LoginPage lp;

	@BeforeClass(alwaysRun = true)
	public void groupInit() throws InterruptedException {
			//driver = launchbrowser();
			
			
			

	}

	@Test(description = "SCM-IOS/Andriod- Verify that Error message should get displayed after entering invalid password")
	public void TestCase_44676() throws Exception {
//		
//		// Form submission
//
//		HomePage homePage = new HomePage(appiumDriver);
//		boolean login = loginPage.Login(mobileAppUsername, mobileAppPassword);
//		TestValidation.IsTrue(login, "logged into the application with User " + mobileAppUsername, "Failed to log in");

		
		//create a user - API
//		String userUN = CommonMethods.dynamicText("UN");
//		String userFN = CommonMethods.dynamicText("FN");
//		String userLN = CommonMethods.dynamicText("LN");
//		
//		String userPassword = GenericPassword;
//
//		TCG_UserFlows_Wrapper userCreationWrapper = new TCG_UserFlows_Wrapper();
//
//		UserParams userDetails = new UserParams();
//		userDetails.Username = userUN;
//		userDetails.ClearPassword = userPassword;
//		userDetails.FirstName = userFN;
//		userDetails.LastName = userLN;
//		userDetails.Email = "sc@pcappm.com";
//		userDetails.TimeZone = "U.S. Pacific";
//		userDetails.LocationCat =  "ALL";
//		userDetails.LocationNmIds = Arrays.asList("ALL");
//		userDetails.Roles = Arrays.asList("Superadmin");
//
//		boolean userCreation = false;
//
//		try {
//			userCreationWrapper.createUser_Wrapper(userDetails);
//			userCreation = true;
//			logInfo("'"+userUN+"' User is created");
//		} catch (InterruptedException e) {
//			logError("Error while '"+userUN+"' user creation");
//			userCreation = false;
//		}
//
//		TestValidation.IsTrue(userCreation, 
//				"CREATED User - " + userUN, 
//				"Could NOT create user - " + userUN);
//
//		//Launch Browser
//		driver = launchbrowser();
//		controlActions = new ControlActions(driver);
//		controlActions.getUrl(applicationUrl);
//		lp = new com.project.safetychain.webapp.pageobjects.LoginPage(driver);
//		
//		//do login first time
//		boolean loginNewUser = lp.loginAfterUserCreation(userUN, GenericPassword, GenericNewPassword);
//		TestValidation.IsTrue(loginNewUser, 
//							  "Logged In with new user - " + userUN, 
//							  "Could NOT login with user - " + userUN);
		
		//launch app
		AndroidDriver<MobileElement> androidDriver = new AndroidDriver<MobileElement>(new URL(prop.getProperty("appiumURL")), getDesiredCapabilitiesForAndroid());
//		LoginPage loginPage = new LoginPage(androidDriver);
//		mobControlActions = new ControlActions_MobileApp(androidDriver);
//		boolean login = loginPage.Login(mobileAppUsername, mobileAppPassword);
		
		//androidDriver.toggleData();
		
       

//        assertTrue(state.isWiFiEnabled());
//        assertFalse(state.isDataEnabled());
//        assertFalse(state.isAirplaneModeEnabled());
		
		//turn off data/wifi
		
		//login - handle No internet connectivity 
		
		//Change password in web for the user
		
		//turn on data/wifi
		
		//Verify Password Change Pop up
		
		// Enter wrong password
		
		// verify error message for invalid password
		
		
		
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