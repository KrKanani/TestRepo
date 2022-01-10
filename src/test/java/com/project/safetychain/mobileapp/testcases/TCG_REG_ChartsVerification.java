package com.project.safetychain.mobileapp.testcases;

import java.text.ParseException;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.project.safetychain.api.models.support.Support.Chart_Template_TYPES;
import com.project.safetychain.api.models.support.Support.FormFieldParams;
import com.project.safetychain.mobileapp.pageobjects.LoginPage;
import com.project.safetychain.mobileapp.pageobjects.REG_AllFormsSaved;
import com.project.safetychain.mobileapp.pageobjects.REG_ChartsPage;
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
import com.project.testbase.TestValidation;
import com.project.utilities.ApiUtils;
import com.project.utilities.ControlActions;
import com.project.utilities.ControlActions_MobileApp;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class TCG_REG_ChartsVerification extends TestBase {

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
	REG_ChartsPage RCP;
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

	@Test(description = "Verify XBar S chart is displayed once user Submit the XBar S chart configured Form")
	public void TestCase_37297() throws InterruptedException, ElementNotVisibleException, ParseException {

		appiumDriver = launchMobileApp();
		RCP = new REG_ChartsPage(appiumDriver);
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
		RCP.XBarSChartVerification();
	}

	@Test(description = "Verify P chart is displayed once user Submit the P chart configured Form")
	public void TestCase_37295() throws InterruptedException, ElementNotVisibleException, ParseException {

		appiumDriver = launchMobileApp();
		RCP = new REG_ChartsPage(appiumDriver);
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
		RCP.PChartVerification(); 
	}

	@Test(description = "Verify C chart is displayed once user Submit the C chart configured Form")
	public void TestCase_37294() throws InterruptedException, ElementNotVisibleException, ParseException {

		appiumDriver = launchMobileApp();
		RCP = new REG_ChartsPage(appiumDriver);
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
		RCP.CChartVerification(); 
	}
	
	@Test(description = "Verify XBar R chart is displayed once user Submit the XBar S chart configured Form")
	public void TestCase_37296() throws Exception {

//		appiumDriver = launchMobileApp();
//		RCP = new REG_ChartsPage(appiumDriver);
//		mobControlActions = new ControlActions_MobileApp(appiumDriver);
		TCG_SMK_ChartsBuilderFlow smkCB = new TCG_SMK_ChartsBuilderFlow();
//		LoginPage loginPage = new LoginPage(appiumDriver);
//		if (mobileApp_Tenant.equals("test1.stage")) {
//			boolean SSOLogin = loginPage.SSO_Login(mobileAppUsername, mobileAppPassword);
//			TestValidation.IsTrue(SSOLogin, "logged into the application with User " + mobileAppUsername,
//					"Failed to log in");
//
//		} else {
//			Thread.sleep(5000);
//			boolean login = loginPage.Login(mobileAppUsername, mobileAppPassword);
//			TestValidation.IsTrue(login, "logged into the application with User " + mobileAppUsername,
//					"Failed to log in");
//
//		}
		smkCB.groupInit();
		smkCB.TestCase_36825();
	}

	@Test(description = "Verify NP chart is displayed once user Submit the NP chart configured Form")
	public void TestCase_37298() throws Exception {

		String numVal = "20";
		String chartAxisLabel = "Defects";
		String expectedMean = "20.00";
		String chartType = Chart_Template_TYPES.NpChart;

		appiumDriver = launchMobileApp();
		RCP = new REG_ChartsPage(appiumDriver);
		mobControlActions = new ControlActions_MobileApp(appiumDriver);

		LoginPage loginPage = new LoginPage(appiumDriver);
		if (mobileApp_Tenant.equals("test1.stage")) {
			boolean SSOLogin = loginPage.SSO_Login(mobileAppUsername, mobileAppPassword);
			TestValidation.IsTrue(SSOLogin, "Logged into the application with User " + mobileAppUsername,
					"Failed to log in");
		} else {
			Thread.sleep(5000);
			boolean login = loginPage.Login(mobileAppUsername, mobileAppPassword);
			TestValidation.IsTrue(login, "logged into the application with User " + mobileAppUsername,
					"Failed to log in");
		}

		String formName = RCP.NPChartCreation();

		RCP.fillFormNVerifyChartDetails(formName, numVal, chartAxisLabel);

		boolean inSubmissionVerify = RCP.verifyChartDetailsInSubmission(formName, expectedMean, chartAxisLabel,
				chartType);
		TestValidation.IsTrue(inSubmissionVerify, "Verified chart details succesfully for " + chartType,
				"Failed to verify chart details for " + chartType);

	}
	
	@Test(description = "Verify Average chart is displayed once user Submit the Average chart configured Form")
	public void TestCase_37299() throws Exception {
		
		String numVal = "20";
		String chartAxisLabel = "Average";
		String expectedMean = "20.00";
		String chartType = Chart_Template_TYPES.AverageChart;

		appiumDriver = launchMobileApp();
		RCP = new REG_ChartsPage(appiumDriver);
		mobControlActions = new ControlActions_MobileApp(appiumDriver);
		
		LoginPage loginPage = new LoginPage(appiumDriver);
		if (mobileApp_Tenant.equals("test1.stage")) {
			boolean SSOLogin = loginPage.SSO_Login(mobileAppUsername, mobileAppPassword);
			TestValidation.IsTrue(SSOLogin, 
								 "Logged into the application with User " + mobileAppUsername,
								 "Failed to log in");
		} else {
			Thread.sleep(5000);
			boolean login = loginPage.Login(mobileAppUsername, mobileAppPassword);
			TestValidation.IsTrue(login, 
								  "logged into the application with User " + mobileAppUsername,
								  "Failed to log in");
		}
		
		String formName = RCP.createChart(chartType,null);
		
		RCP.fillFormNVerifyChartDetails(formName, numVal, chartAxisLabel);
		
		boolean inSubmissionVerify = RCP.verifyChartDetailsInSubmission(formName, expectedMean, chartAxisLabel, chartType);
		TestValidation.IsTrue(inSubmissionVerify, 
							 "Verified chart details succesfully for " + chartType,
							 "Failed to verify chart details for " + chartType);
		
		
	}
	
	@Test(description = "Verify Sum chart is displayed once user Submit the Sum chart configured Form")
	public void TestCase_37300() throws Exception {

		String numVal = "20";
		String chartAxisLabel = "Sum";
		String expectedMean = "40.00";
		String chartType = Chart_Template_TYPES.SumChart;

		appiumDriver = launchMobileApp();
		RCP = new REG_ChartsPage(appiumDriver);
		mobControlActions = new ControlActions_MobileApp(appiumDriver);
		
		LoginPage loginPage = new LoginPage(appiumDriver);
		if (mobileApp_Tenant.equals("test1.stage")) {
			boolean SSOLogin = loginPage.SSO_Login(mobileAppUsername, mobileAppPassword);
			TestValidation.IsTrue(SSOLogin, 
								 "Logged into the application with User " + mobileAppUsername,
								 "Failed to log in");
		} else {
			Thread.sleep(5000);
			boolean login = loginPage.Login(mobileAppUsername, mobileAppPassword);
			TestValidation.IsTrue(login, 
								  "logged into the application with User " + mobileAppUsername,
								  "Failed to log in");
		}
		
		String formName = RCP.createChart(chartType,null);
		
		RCP.fillFormNVerifyChartDetails(formName, numVal, chartAxisLabel);
		
		boolean inSubmissionVerify = RCP.verifyChartDetailsInSubmission(formName, expectedMean, chartAxisLabel, chartType);
		TestValidation.IsTrue(inSubmissionVerify, 
							 "Verified chart details succesfully for " + chartType,
							 "Failed to verify chart details for " + chartType);
	}
	
	@Test(description = "Verify XiMr chart is displayed once user Submit the XiMr chart configured Form")
	public void TestCase_37301() throws Exception {

		String numVal = "200";
		String chartAxisLabel = "Mean (oz)";
		String expectedMean = "200.00";
		String chartType = Chart_Template_TYPES.XiMr;

		appiumDriver = launchMobileApp();
		RCP = new REG_ChartsPage(appiumDriver);
		mobControlActions = new ControlActions_MobileApp(appiumDriver);
		
		LoginPage loginPage = new LoginPage(appiumDriver);
		if (mobileApp_Tenant.equals("test1.stage")) {
			boolean SSOLogin = loginPage.SSO_Login(mobileAppUsername, mobileAppPassword);
			TestValidation.IsTrue(SSOLogin, 
								 "Logged into the application with User " + mobileAppUsername,
								 "Failed to log in");
		} else {
			Thread.sleep(5000);
			boolean login = loginPage.Login(mobileAppUsername, mobileAppPassword);
			TestValidation.IsTrue(login, 
								  "logged into the application with User " + mobileAppUsername,
								  "Failed to log in");
		}
		
		String formName = RCP.createChart(chartType,null);
		
		RCP.fillFormNVerifyChartDetails(formName, numVal, chartAxisLabel);
		
		boolean inSubmissionVerify = RCP.verifyChartDetailsInSubmission(formName, expectedMean, chartAxisLabel, chartType);
		TestValidation.IsTrue(inSubmissionVerify, 
							 "Verified chart details succesfully for " + chartType,
							 "Failed to verify chart details for " + chartType);
	}
		
	@Test(description = "Verify U chart is displayed once user Submit the U chart configured Form")
	public void TestCase_37302() throws Exception {

		String numVal = "100";
		String chartAxisLabel = "Defects";
		String expectedMean = "0.00";
		String chartType = Chart_Template_TYPES.UChart;

		appiumDriver = launchMobileApp();
		RCP = new REG_ChartsPage(appiumDriver);
		mobControlActions = new ControlActions_MobileApp(appiumDriver);
		
		LoginPage loginPage = new LoginPage(appiumDriver);
		if (mobileApp_Tenant.equals("test1.stage")) {
			boolean SSOLogin = loginPage.SSO_Login(mobileAppUsername, mobileAppPassword);
			TestValidation.IsTrue(SSOLogin, 
								 "Logged into the application with User " + mobileAppUsername,
								 "Failed to log in");
		} else {
			Thread.sleep(5000);
			boolean login = loginPage.Login(mobileAppUsername, mobileAppPassword);
			TestValidation.IsTrue(login, 
								  "logged into the application with User " + mobileAppUsername,
								  "Failed to log in");
		}
		
		String formName = RCP.createChart(chartType,null);
		
		RCP.fillFormNVerifyChartDetails(formName, numVal, chartAxisLabel);
		
		boolean inSubmissionVerify = RCP.verifyChartDetailsInSubmission(formName, expectedMean, chartAxisLabel, chartType);
		TestValidation.IsTrue(inSubmissionVerify, 
							 "Verified chart details succesfully for " + chartType,
							 "Failed to verify chart details for " + chartType);
		
	}


	
	@Test(description = "Test Case 39732: SCM - iOS/Android - Charts || Average Chart || User should be able to see sum of Mean values of total visible points under 16hrs in \"Running Sum\" field\n"
			+ "Test Case 39733: SCM - iOS/Android -Charts || Average Chart || User should be able to see sum of Mean values of total visible points under 16hrs in \"Running Sum\" field for the particular submission")
	public void TestCase_39732_39733() throws Exception {
		
		String chartType = Chart_Template_TYPES.AverageChart;

		appiumDriver = launchMobileApp();
		RCP = new REG_ChartsPage(appiumDriver);
		mobControlActions = new ControlActions_MobileApp(appiumDriver);
		
		LoginPage loginPage = new LoginPage(appiumDriver);
		if (mobileApp_Tenant.equals("test1.stage")) {
			boolean SSOLogin = loginPage.SSO_Login(mobileAppUsername, mobileAppPassword);
			TestValidation.IsTrue(SSOLogin, 
								 "Logged into the application with User " + mobileAppUsername,
								 "Failed to log in");
		} else {
			Thread.sleep(5000);
			boolean login = loginPage.Login(mobileAppUsername, mobileAppPassword);
			TestValidation.IsTrue(login, 
								  "logged into the application with User " + mobileAppUsername,
								  "Failed to log in");
		}
		
		String formName = RCP.createChart(chartType,null);
		
		boolean verifyRunningSum = RCP.verifyAvgChartRunnigSum(formName);
		TestValidation.IsTrue(verifyRunningSum, 
				  "Verified Mean values in Running sum field and  Mean values in Running sum field for particular submission for Average chart", 
				  "Failed to Verify Mean Values in Running sum field for Average chart");
	}

	
	@Test(description = "SCM - iOS//Android - Charts || Average Chart || User should be able to see sum of Mean values of total visible points under 16 hrs in \"Running Sum\" field when having filter applied")
	public void TestCase_39734() throws Exception {
		
		String chartType = Chart_Template_TYPES.AverageChart;

		appiumDriver = launchMobileApp();
		RCP = new REG_ChartsPage(appiumDriver);
		mobControlActions = new ControlActions_MobileApp(appiumDriver);
		
		LoginPage loginPage = new LoginPage(appiumDriver);
		if (mobileApp_Tenant.equals("test1.stage")) {
			boolean SSOLogin = loginPage.SSO_Login(mobileAppUsername, mobileAppPassword);
			TestValidation.IsTrue(SSOLogin, 
								 "Logged into the application with User " + mobileAppUsername,
								 "Failed to log in");
		} else {
			Thread.sleep(5000);
			boolean login = loginPage.Login(mobileAppUsername, mobileAppPassword);
			TestValidation.IsTrue(login, 
								  "logged into the application with User " + mobileAppUsername,
								  "Failed to log in");
		}
		
		String formName = RCP.createChartWithFilter(chartType,null);
		
		boolean verifyRunningSum = RCP.verifyAvgChartRunnigSumWithFilter(formName);
		TestValidation.IsTrue(verifyRunningSum, 
				  "Verified Mean values in Running sum field and  Mean values in Running sum field for particular submission for Average chart", 
				  "Failed to Verify Mean Values in Running sum field for Average chart");
	}

	
	@AfterMethod(alwaysRun = true)
	public void closeAppium() throws InterruptedException {

		appiumDriver.closeApp();

	}

}
