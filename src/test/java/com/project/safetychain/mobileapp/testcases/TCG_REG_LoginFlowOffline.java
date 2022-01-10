package com.project.safetychain.mobileapp.testcases;

import java.util.ArrayList;
import java.util.HashMap;

import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.project.safetychain.mobileapp.pageobjects.HomePage;
import com.project.safetychain.mobileapp.pageobjects.LoginPage;
import com.project.safetychain.mobileapp.pageobjects.REG_AllFormsSavedLocators;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.utilities.ApiUtils;
import com.project.utilities.ControlActions_MobileApp;

public class TCG_REG_LoginFlowOffline extends TestBase {
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

	public final String ssoUsername = "dinesh.kanojia";
	public final String ssoPassword = "Password123#";

	@Test(description = "Verify login if user is in offline mode", enabled = true)
	public void TestCase_37336() throws Exception {
		appiumDriver = launchMobileApp();
		mobControlActions = new ControlActions_MobileApp(appiumDriver);
		LoginPage loginPage = new LoginPage(appiumDriver);

		boolean dataOn = ControlActions_MobileApp.EnableData(true);
		TestValidation.IsTrue(dataOn, "Mobile data has enabled successfully", "Failed to enable mobile data");

		if (!(loginPage.tenantTxt.getText().equalsIgnoreCase(mobileApp_Tenant))) {
			ControlActions_MobileApp.waitForVisibility(loginPage.profileSettingBtn, 100);
			ControlActions_MobileApp.click(loginPage.profileSettingBtn);
			loginPage.updateTenantName(mobileApp_Tenant);
		}

		Thread.sleep(4000);
		try
		{
		boolean dataDisable = ControlActions_MobileApp.EnableData(false);
		TestValidation.IsTrue(dataDisable, "Mobile data has disabled successfully", "Failed to disable mobile data");

		boolean login = loginPage.LoginInOffline(mobileAppUsername, mobileAppPassword);
		TestValidation.IsTrue(login, "Offline login Functionality verified Successfully",
				"Failed to verify Offline login functionality");

		boolean dataEnable = ControlActions_MobileApp.EnableData(true);
		TestValidation.IsTrue(dataEnable, "Mobile data has enabled successfully", "Failed to enable mobile data");
		}
		finally
		{
			ControlActions_MobileApp.EnableData(true);
		}
	}

	@Test(description = "Verify SSO Login screen is not displayed when useropen the app in Offline mode", enabled = true)

	public void TestCase_37861() throws Exception {
		appiumDriver = launchMobileApp();
		mobControlActions = new ControlActions_MobileApp(appiumDriver);
		LoginPage loginPage = new LoginPage(appiumDriver);
		boolean dataOn = ControlActions_MobileApp.EnableData(true);
		TestValidation.IsTrue(dataOn, "Mobile data has enabled successfully", "Failed to enable mobile data");

		try {

			ControlActions_MobileApp.waitForVisibility(loginPage.profileSettingBtn, 50);
			ControlActions_MobileApp.WaitforelementToBeClickable(loginPage.profileSettingBtn);
			ControlActions_MobileApp.click(loginPage.profileSettingBtn);
			ControlActions_MobileApp.waitForVisibility(loginPage.clearBtn, 100);
			ControlActions_MobileApp.WaitforelementToBeClickable(loginPage.clearBtn);
			ControlActions_MobileApp.click(loginPage.clearBtn);

			ControlActions_MobileApp.waitForVisibility(loginPage.tenantNameTxt, 100);
			ControlActions_MobileApp.sendKeys(loginPage.tenantNameTxt, "Wholefoods");
			ControlActions_MobileApp.WaitforelementToBeClickable(loginPage.saveBtn);
			ControlActions_MobileApp.click(loginPage.saveBtn);
			logInfo("Tenant name has updated to " + "Wholefoods");
			Thread.sleep(20000);
			appiumDriver.navigate().back();
			boolean dataDisable = ControlActions_MobileApp.EnableData(false);
			TestValidation.IsTrue(dataDisable, "Mobile data has disabled successfully",
					"Failed to disable mobile data");
			boolean login = loginPage.LoginInOffline("Superadmin", "latewholefoods3772volt");
			TestValidation.IsTrue(login, "Offline login Functionality verified Successfully",
					"Failed to verify Offline login functionality");
		} finally {
			ControlActions_MobileApp.EnableData(true);
		}
	}

	@Test(description = "Verify while loading bar in progress & network goes offline", enabled = true)

	public void TestCase_37347() throws Exception {
		appiumDriver = launchMobileAppReinstall();
		mobControlActions = new ControlActions_MobileApp(appiumDriver);
		LoginPage loginPage = new LoginPage(appiumDriver);
		HomePage homePage = new HomePage(appiumDriver);
		HashMap<String, Integer> DownCount = new HashMap<>(); 
		try {

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
			logInfo("Total Downloading Count : " + DownCount);
			
			Thread.sleep(10000);
			
			boolean dataDisable = ControlActions_MobileApp.EnableData(false);
			TestValidation.IsTrue(dataDisable, "Mobile data has disabled successfully",
					"Failed to disable mobile data");

			ControlActions_MobileApp.waitForVisibility(homePage.searchField, 120000);
			ControlActions_MobileApp.WaitforelementToBeClickable(homePage.searchField);
			boolean list = false;
			ArrayList<WebElement> ls = new ArrayList<>(appiumDriver.findElementsById("formName"));
			logInfo("Number of elements in grid :  " + ls.size());
			if (ls.size() > 0) {
				list = true;
				for (int i = 0; i < ls.size(); i++) {
					System.out.println(ls.get(i));

				}
			}

			else {
				list = false;
				logInfo("No Forms were downloaded before switching to offline mode");

			}

			TestValidation.IsTrue(list, "Forms got downoloaded in online mode are present in listing",
					"Error or blank screen is appearing: No forms were downloaded");

		} finally {
			ControlActions_MobileApp.EnableData(true);
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
