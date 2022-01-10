package com.project.safetychain.mobileapp.testcases;
 
import java.text.ParseException;  
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement; 
import org.testng.annotations.AfterMethod; 
import org.testng.annotations.Test; 
import com.project.safetychain.api.models.support.Support.FormFieldParams; 
import com.project.safetychain.mobileapp.pageobjects.LoginPage;
import com.project.safetychain.mobileapp.pageobjects.REG_AllFormsSaved;
import com.project.safetychain.mobileapp.pageobjects.REG_InboxTaskPage;
import com.project.safetychain.mobileapp.pageobjects.REG_Notifications; 
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
import com.project.testbase.TestValidation;
import com.project.utilities.ApiUtils; 
import com.project.utilities.ControlActions;
import com.project.utilities.ControlActions_MobileApp; 
import io.appium.java_client.pagefactory.AndroidFindBy;

public class TCG_REG_Notifications extends TestBase {

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
	REG_Notifications notific;
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

	@Test(description = "Verify user is able to get notification on task submission and Verify user is able to get notification on Compliant task submission")
	public void TestCase_37279_37280() throws InterruptedException, ElementNotVisibleException, ParseException {

		appiumDriver = launchMobileApp();
		notific = new REG_Notifications(appiumDriver);
		mobControlActions = new ControlActions_MobileApp(appiumDriver);
		LoginPage loginPage = new LoginPage(appiumDriver);
		if (mobileApp_Tenant.equals("test1.stage")) {
			boolean SSOLogin = loginPage.SSO_Login(mobileAppUsername, mobileAppPassword);
			TestValidation.IsTrue(SSOLogin, "logged into the application with User " + mobileAppUsername,
					"Failed to log in");

		} else {
			Thread.sleep(5000);
			boolean login = loginPage.Login(mobileAppUsername, mobileAppPassword);
			TestValidation.IsTrue(login, "logged into the application with User " + mobileAppUsername,
					"Failed to log in");

		}

		String CompTask = notific.TaskSubmissionFunctionality("10");
		System.out.println("Compliant task name is : " + CompTask);
	}

	@Test(description = "Verify user is able to get notification on Non-Compliant task submission")
	public void TestCase_37281() throws InterruptedException, ElementNotVisibleException, ParseException {

		appiumDriver = launchMobileApp();
		notific = new REG_Notifications(appiumDriver);
		mobControlActions = new ControlActions_MobileApp(appiumDriver);
		LoginPage loginPage = new LoginPage(appiumDriver);
		if (mobileApp_Tenant.equals("test1.stage")) {
			boolean SSOLogin = loginPage.SSO_Login(mobileAppUsername, mobileAppPassword);
			TestValidation.IsTrue(SSOLogin, "logged into the application with User " + mobileAppUsername,
					"Failed to log in");

		} else {
			Thread.sleep(5000);
			boolean login = loginPage.Login(mobileAppUsername, mobileAppPassword);
			TestValidation.IsTrue(login, "logged into the application with User " + mobileAppUsername,
					"Failed to log in");

		}

		String nonCompTask = notific.TaskSubmissionFunctionality("8");
		System.out.println("Non Compliant task name is : " + nonCompTask);
	}

	@Test(description = "Verify user is able to get notification on offline task submission after user gets in online mode.",priority=1)
	public void TestCase_37282() throws InterruptedException, ElementNotVisibleException, ParseException {

		appiumDriver = launchMobileApp();
		notific = new REG_Notifications(appiumDriver);
		mobControlActions = new ControlActions_MobileApp(appiumDriver);
		LoginPage loginPage = new LoginPage(appiumDriver);
		if (mobileApp_Tenant.equals("test1.stage")) {
			boolean SSOLogin = loginPage.SSO_Login(mobileAppUsername, mobileAppPassword);
			TestValidation.IsTrue(SSOLogin, "logged into the application with User " + mobileAppUsername,
					"Failed to log in");

		} else {
			Thread.sleep(5000);
			boolean login = loginPage.Login(mobileAppUsername, mobileAppPassword);
			TestValidation.IsTrue(login, "logged into the application with User " + mobileAppUsername,
					"Failed to log in");

		}

		String offlineTask = notific.OfflineTaskSubmissionFunctionality();
		System.out.println("Offline mode task name is : " + offlineTask);
	}

	@AfterMethod(alwaysRun = true)
	public void closeAppium() throws InterruptedException {

		appiumDriver.closeApp();

	}

}