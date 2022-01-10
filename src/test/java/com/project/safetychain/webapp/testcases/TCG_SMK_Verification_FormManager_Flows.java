
package com.project.safetychain.webapp.testcases;

import java.util.ArrayList; 
import org.testng.SkipException;
import org.testng.annotations.AfterClass; 
import org.testng.annotations.BeforeClass; 
import org.testng.annotations.Test;
import com.project.safetychain.webapp.pageobjects.HomePage;
import com.project.safetychain.webapp.pageobjects.FormsManagerPage; 
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage; 
import com.project.safetychain.webapp.pageobjects.LoginPage; 
import com.project.safetychain.webapp.pageobjects.VerificationsPage;
import com.project.safetychain.webapp.pageobjects.VerificationsPage.VerificationDetsParams;
import com.project.testbase.TestBase; 
import com.project.testbase.TestValidation;
import com.project.utilities.CommonMethods; 
import com.project.utilities.ControlActions;
import com.project.safetychain.webapp.pageobjects.FSQABrowserFormsPage;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.COLUMNHEADER;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.FSQARESOURCE;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.FSQATAB;
import com.project.safetychain.webapp.pageobjects.FSQABrowserRecordsPage;


import com.project.safetychain.webapp.pageobjects.ManageLocationPage;
import com.project.safetychain.webapp.pageobjects.ManageResourcePage;

public class TCG_SMK_Verification_FormManager_Flows extends TestBase {

	ControlActions controlActions; 
	CommonMethods common; 
	VerificationsPage verification; 
	FormsManagerPage formsManager; 
	LoginPage login; 
	HomePage home; 
	FormDesignerPage fdp;
	VerificationDetsParams vdp;
	FSQABrowserFormsPage fbForms;
	FSQABrowserPage fbp;
	FSQABrowserRecordsPage frp;
	public static String verificationName;
	public static String rolename;
	public static String formName1;
	public static String formName2;
	public static String verificationComment1;
	public static String verificationComment2;
	public static String SetRoleForVerification;
	public static String locationCategoryValue;
	public static String customersCategoryValue;
	public static String customersInstanceValue;
	public static String locationInstanceValue;


	@BeforeClass(alwaysRun = true) 
	public void groupInit() throws InterruptedException { 

		driver = launchbrowser(); 
		controlActions = new ControlActions(driver); 
		controlActions.getUrl(applicationUrl); 
		verification=new VerificationsPage(driver); 
		home=new HomePage(driver); 
		login = new LoginPage(driver); 
		common = new CommonMethods(driver); 
		fdp = new FormDesignerPage(driver);
		formsManager = new FormsManagerPage(driver);
		fbp = new FSQABrowserPage(driver);
		frp = new FSQABrowserRecordsPage(driver);
		rolename = "SuperAdmin";
		formName1 = CommonMethods.dynamicText("Automation_Chk1");
		formName2 = CommonMethods.dynamicText("Automation_Chk2");
		verificationComment1 = CommonMethods.dynamicText("AutoComment1");
		verificationComment2 = CommonMethods.dynamicText("AutoComment2");
		SetRoleForVerification = CommonMethods.dynamicText("SuperAdmin");
		locationCategoryValue	= CommonMethods.dynamicText("Loc_Cat");
		customersCategoryValue	= CommonMethods.dynamicText("Cust_Cat");
		customersInstanceValue = CommonMethods.dynamicText("Cust_Inst");
		locationInstanceValue	= CommonMethods.dynamicText("Loc_Inst");
		verificationName =CommonMethods.dynamicText("AutoVN");
		fbForms = new FSQABrowserFormsPage(driver);
		vdp = new VerificationDetsParams();

		LoginPage lp = new LoginPage(driver);
		home = lp.performLogin(adminUsername, adminPassword);
		if(home.error) {
			TCGFailureMsg = "Could not login to application with internal user";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}	
		threadsleep(2000);
		verification = home.clickVerificationsMenu();			  

		vdp.VerificationName = verificationName;//objName.Attribute Name = Value)
		vdp.EnableVerification = true;
		vdp.AddComments = new ArrayList<String>();
		vdp.AddComments.add(verificationComment1);
		vdp.AddComments.add(verificationComment2);
		threadsleep(2000);
		vdp.Rolename = rolename;
		threadsleep(5000);
		if (!verification.createVerification(vdp)){
			TCGFailureMsg = "Could NOT able to create verification"; 
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg); 

		}


		home.clickFSQABrowserMenu();
		ManageLocationPage mlp = new ManageLocationPage(driver);
		if(!mlp.createLocation(locationCategoryValue,locationInstanceValue)) {
			TCGFailureMsg = "Could NOT create location category - " + locationCategoryValue;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		ManageResourcePage mrp = new ManageResourcePage(driver);
		if(!mrp.createResourceLinkLocation("Customers", customersCategoryValue, 
				customersInstanceValue,locationInstanceValue)) {
			TCGFailureMsg = "Could NOT create resource " + customersInstanceValue + " & link Location - "+ locationInstanceValue;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}



	}

	@Test(description="Linking of Verification with Form", priority = 1) 
	public void TestCase_35198() throws Exception{ 

		//Creation of Form
		home.clickFSQABrowserMenu();
		
	/*	if(!fdp.createAndReleaseForm("Check", formName1,"Customers", customersCategoryValue,
				customersInstanceValue)) {
			TCGFailureMsg = "Could NOT create and release form - " + formName1;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);

		}
		*/
		boolean createAndReleaseForm = fdp.createAndReleaseForm("Check", formName1,"Customers", customersCategoryValue,
				customersInstanceValue); 
					
				TestValidation.IsTrue(createAndReleaseForm, 
							"Able to create and release form:" + formName1,
							"Could NOT able to create and release forms:" +formName1);
					
		home.clickFormsManagerMenu();
		boolean formLinkedwithVerifications = formsManager.linkVerificationwithForm(verificationName,formName1);
		if(!formLinkedwithVerifications) {
			TCGFailureMsg = "Could NOT Linked verification" + formsManager.linkVerificationwithForm(verificationName,formName1);
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

	}


	@Test(description="Verify the added Verification in FSQA>Records while sign off functionality", priority = 2)

	public void TestCase_35044() throws Exception{ 

		home.clickFSQABrowserMenu();


		//Creation of Form 2
/*
		if(!fdp.createAndReleaseForm("Check", formName2,"Customers", customersCategoryValue,
				customersInstanceValue)) {
			TCGFailureMsg = "Could NOT create and release form - " + formName2;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);

		}
		*/
		
		boolean createAndReleaseForm = fdp.createAndReleaseForm("Check", formName2,"Customers", customersCategoryValue,
				customersInstanceValue); 
					
				TestValidation.IsTrue(createAndReleaseForm, 
							"Able to create and release form:" + formName2,
							"Could NOT able to create and release forms:" +formName2);
					
					
				
		


		home.clickFormsManagerMenu();
		boolean formLinkedwithVerifications = formsManager.linkVerificationwithForm(verificationName,formName2);
		if(!formLinkedwithVerifications) {
			TCGFailureMsg = "Could NOT Linked verification" + formsManager.linkVerificationwithForm(verificationName,formName2);
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}




		home.clickFSQABrowserMenu();
		boolean navigate = fbp.selectResourceDropDownandNavigate("Customers","Forms");
		TestValidation.IsTrue(navigate, 
				"Navigated to FSQABrowser>FormsTab", 
				"Failed to navigate to FSQABrowser>Forms Tab");

		boolean applyfilter = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME,formName2);
		TestValidation.IsTrue(applyfilter, 
				"Applied Filter on" + COLUMNHEADER.FORMNAME, 
				"Failed to apply filter on" + COLUMNHEADER.FORMNAME);


		boolean submitData = fbForms.submitData(false,false,false,true,false);
		TestValidation.IsTrue(submitData, 
				"Able to submit the form with data:" +formName2,
				"Could NOT able to submit the form with data:" +formName2);

		home.clickFSQABrowserMenu();
		boolean navigateToRecords = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.CUSTOMERS,FSQATAB.RECORDS);
		TestValidation.IsTrue(navigateToRecords, 
				"For customer category, NAVIGATED to FSQABrowser > Records tab", 
				"Failed to navigate to FSQABrowser > Records tab");


		boolean openRecord = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, formName2);
		TestValidation.IsTrue(openRecord, 
				"OPENED record for form - " + formName2, 
				"Failed to open record for form - " + formName2);

		boolean signOffRecord = frp.signoffRecordWithVerfication(verificationName,verificationComment1);
		TestValidation.IsTrue(signOffRecord, 
				"SIGNED record for form - " + formName2, 
				"Failed to sign record for form " + formName2);

	}


	@AfterClass(alwaysRun = true) 
	public void closeBrowser() throws InterruptedException { 
		driver.close(); 
	} 
}
