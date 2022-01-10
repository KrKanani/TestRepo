package com.project.safetychain.mobileapp.testcases;

import java.awt.AWTException;
import java.text.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.project.safetychain.api.models.support.Support.FormFieldParams;
import com.project.safetychain.mobileapp.pageobjects.LoginPage;
import com.project.safetychain.mobileapp.pageobjects.REG_AllFormsSaved;
import com.project.safetychain.mobileapp.pageobjects.REG_DynamicFlow;
import com.project.safetychain.mobileapp.pageobjects.REG_InboxTaskPage;
import com.project.safetychain.mobileapp.pageobjects.SavedPage;
import com.project.safetychain.webapp.pageobjects.CommonPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserFormsPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FormDesignParams;
import com.project.safetychain.webapp.pageobjects.ManageLocationPage;
import com.project.safetychain.webapp.pageobjects.ManageResourcePage;
import com.project.safetychain.webapp.pageobjects.ResourceDesignerPage;
import com.project.safetychain.webapp.testcases.support.FormOperations;
import com.project.safetychain.webapp.testcases.support.FormOperations.FormDetails;
import com.project.testbase.TestBase;
import com.project.utilities.ApiUtils;
import com.project.utilities.ControlActions;
import com.project.utilities.ControlActions_MobileApp;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class TCG_REG_DynamicFlow extends TestBase {

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
	REG_AllFormsSaved afSaved;
	REG_InboxTaskPage inbox;
	public WebDriver driver;
	REG_DynamicFlow dynamic;
	public static String locationCategoryValue;
	public static String locationInstanceValue1;
	public static String locationInstanceValue2;
	public static String resourceCategoryValue;
	public static String resourceInstanceValue1;
	public static String resourceInstanceValue2;
	public static String chkFreeTxt, ParaTxt, chkSelectOne, SelectMultiple, Numeric, chkDate, chkTime, chkDateTime;

	String modifiedby;
	public static String FormNameCreated;
	public static final String fresult = "com.safetychain.SCM.M2:id/resultView_num";
	@AndroidFindBy(id = fresult)
	public WebElement NTxt;

	@Test(description = "Verify user is able to validate Dynamic Flow")
	public void TestCase_37289_291_292_293_835_836_837_838_839()
			throws InterruptedException, ElementNotVisibleException, ParseException, AWTException {

		appiumDriver = launchMobileApp();
		dynamic = new REG_DynamicFlow(appiumDriver);
		LoginPage loginPage = new LoginPage(appiumDriver);

		Thread.sleep(5000);
		if (!appiumDriver.findElement(By.id("tanentname")).getText().equalsIgnoreCase("test1.stage")) {
			ControlActions_MobileApp.waitForVisibility(loginPage.profileSettingBtn, 50);
			ControlActions_MobileApp.WaitforelementToBeClickable(loginPage.profileSettingBtn);
			ControlActions_MobileApp.click(loginPage.profileSettingBtn);
			ControlActions_MobileApp.waitForVisibility(loginPage.clearBtn, 100);
			ControlActions_MobileApp.WaitforelementToBeClickable(loginPage.clearBtn);
			ControlActions_MobileApp.click(loginPage.clearBtn);

			ControlActions_MobileApp.waitForVisibility(loginPage.tenantNameTxt, 100);
			ControlActions_MobileApp.sendKeys(loginPage.tenantNameTxt, "test1.stage");
			ControlActions_MobileApp.WaitforelementToBeClickable(loginPage.saveBtn);
			ControlActions_MobileApp.click(loginPage.saveBtn);
			logInfo("Tenant name has updated to " + "test1.stage");
			Thread.sleep(20000);
			appiumDriver.navigate().back();
		} else {
			Thread.sleep(20000);
			appiumDriver.navigate().back();
		}
		Thread.sleep(5000);
		ControlActions_MobileApp.waitForVisibility(loginPage.UserName, 50);
		ControlActions_MobileApp.WaitforelementToBeClickable(loginPage.UserName);
		ControlActions_MobileApp.sendKeys(loginPage.UserName, "superadmin");
		logInfo("UserName entered: " + "superadmin");
		ControlActions_MobileApp.waitForVisibility(loginPage.passwordTxt, 50);
		ControlActions_MobileApp.sendKeys(loginPage.passwordTxt, "sliptest13772epic");
		logInfo("Password entered: " + "sliptest13772epic");
		ControlActions_MobileApp.click(loginPage.LoginBtn);

		dynamic.DyamicFlowValidation();

	}

	@AfterMethod(alwaysRun = true)
	public void closeAppium() throws InterruptedException {

		appiumDriver.closeApp();

	}

}
