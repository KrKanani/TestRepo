package com.project.safetychain.webapp.testcases;

import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.project.safetychain.webapp.pageobjects.CommonPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserFormsPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage;
import com.project.safetychain.webapp.pageobjects.HomePage;
import com.project.safetychain.webapp.pageobjects.LoginPage;
import com.project.safetychain.webapp.pageobjects.ManageLocationPage;
import com.project.safetychain.webapp.pageobjects.ManageResourcePage;
import com.project.safetychain.webapp.pageobjects.ResourceDesignerPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.COLUMNHEADER;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.COLUMNSETTING;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.utilities.CommonMethods;
import com.project.utilities.ControlActions;

public class TCG_SMK_FormFunctionalties extends TestBase{
	ControlActions controlActions;
	CommonPage mainPage;
	LoginPage lgnPge;
	FSQABrowserPage fsqaBrowser;
	FSQABrowserFormsPage fsqaBrowserForms;
	ResourceDesignerPage resourceDesigner;
	ManageResourcePage manageResource;
	ManageLocationPage locations;
	FormDesignerPage fdp;

	//Data
	public static String checkFormName;
	public static String locationCategoryValue;
	public static String resourceCategoryValue;
	public static String resourceInstanceValue;
	public static String locationInstanceValue;

	String attachmentField, correctionsField, preDefindedCorrectionsField, carryoverField;

	@BeforeClass(alwaysRun = true)
	public void groupInit() throws Exception {

		checkFormName	= CommonMethods.dynamicText("Automation_CheckForm");
		locationCategoryValue	= CommonMethods.dynamicText("Loc_Cat");
		resourceCategoryValue	= CommonMethods.dynamicText("customers_Cat");
		resourceInstanceValue = CommonMethods.dynamicText("Cust_Inst");
		locationInstanceValue	= CommonMethods.dynamicText("Loc_Inst");

		attachmentField = "Numeric Field 1";
		correctionsField = "Numeric Field 2";
		preDefindedCorrectionsField = "Numeric Field 3";
		carryoverField = "Numeric Field 4";


		driver = launchbrowser();
		controlActions = new ControlActions(driver);
		controlActions.getUrl(applicationUrl);
		mainPage = new CommonPage(driver);
		lgnPge = new LoginPage(driver);
		resourceDesigner = new ResourceDesignerPage(driver);
		manageResource = new ManageResourcePage(driver);
		locations = new ManageLocationPage(driver);
		fdp = new FormDesignerPage(driver);
		fsqaBrowserForms = new FSQABrowserFormsPage(driver);

		//login
		HomePage hp = lgnPge.performLogin(adminUsername, adminPassword);
		if(hp.error) {
			TCGFailureMsg = "Could NOT login to application";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		//Location creation - Link User
		if(!locations.createLocationLinkUser(locationCategoryValue,locationInstanceValue,adminUsername)) {
			TCGFailureMsg ="Could NOT Create Location '"+locationInstanceValue+"' & Link User'"+adminUsername+"'";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
		//Resource creation - Link Location
		if(!manageResource.createResourceLinkLocation("Customers", resourceCategoryValue, resourceInstanceValue,locationInstanceValue)) {
			TCGFailureMsg = "Could NOT Create Resource '"+resourceInstanceValue+"' & Link Location'"+locationInstanceValue+"'";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		//Create Form with field level functionalities
		if(!fdp.createForm_FormLevelFeatures(checkFormName,"Customers",resourceCategoryValue,resourceInstanceValue,
				attachmentField, correctionsField, preDefindedCorrectionsField, carryoverField)) {
			TCGFailureMsg = "Could NOT create form";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		fsqaBrowser = hp.clickHomepageLink();
		if(fsqaBrowser.error) {
			TCGFailureMsg = "Failed to navigate to the 'FSQA Browser' page";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
		if(!fsqaBrowser.selectResourceDropDownandNavigate("Customers","Forms")){
			TCGFailureMsg = "Failed to navigate to FSQABrowser > Forms Tab";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		if(!fsqaBrowser.openAndApplySettingsForColumn(COLUMNHEADER.FORMNAME, COLUMNSETTING.FILTER,checkFormName)){
			TCGFailureMsg = "Failed to apply filter on" + COLUMNHEADER.FORMNAME;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

	}

	@Test(description="Verify attachments for the form & 'Allow attachment' functionaity for a field")
	public void TestCase_34337() {

		boolean selectOpenForm = fsqaBrowserForms.selectOpenForm(checkFormName);
		TestValidation.IsTrue(selectOpenForm, 
				"Select & opened the form", 
				"Could NOT open the form");

		boolean uploadFormLevelAttachment = fsqaBrowserForms.addFormLevelAttachment();
		TestValidation.IsTrue(uploadFormLevelAttachment, 
				"Uploaded form level attachment", 
				"Could NOT upload form level attachment");

		boolean uploadFieldLevelAttachment = fsqaBrowserForms.submitData(true, true, false, false,false);
		TestValidation.IsTrue(uploadFieldLevelAttachment, 
				"Uploaded field level attachment", 
				"Could NOT upload field level attachment");
		controlActions.clickElement(fsqaBrowserForms.FormCancelBtn);
		logInfo("Clicked on 'Cancel' Button");
	}

	@Test(description="Verify 'Allow Correction' funcationality for the field")
	public void TestCase_34336() {

		boolean selectOpenForm = fsqaBrowserForms.selectOpenForm(checkFormName);
		TestValidation.IsTrue(selectOpenForm, 
				"Select & opened the form", 
				"Could NOT open the form");

		boolean addedCorrections = fsqaBrowserForms.submitData(false, true, false, false,false);
		TestValidation.IsTrue(addedCorrections, 
				"Setted value in corrections", 
				"Could NOT set value in corrections");

		controlActions.clickElement(fsqaBrowserForms.FormCancelBtn);
		logInfo("Clicked on 'Cancel' Button");


	}

	@Test(description="Verify 'Carryover Field' functionality for the field")
	public void TestCase_31244() {

		boolean selectOpenForm = fsqaBrowserForms.selectOpenForm(checkFormName);
		TestValidation.IsTrue(selectOpenForm, 
				"Select & opened the form", 
				"Could NOT open the form");

		boolean fillData = fsqaBrowserForms.submitData(false, true, false, false,false);
		TestValidation.IsTrue(fillData, 
				"Setted value in all fields", 
				"Could NOT set value in all fields");

		boolean verifySubmitRepeatData = fsqaBrowserForms.verifySubmitRepeatValue(carryoverField, "2");
		TestValidation.IsTrue(verifySubmitRepeatData, 
				"VERIFIED carryover field value", 
				"Could NOT verify carryover field value");

		controlActions.clickElement(fsqaBrowserForms.FormCancelBtn);
		logInfo("Clicked on 'Cancel' Button");

	}

	@AfterClass(alwaysRun = true)
	public void closeBrowser() throws InterruptedException {
		driver.close();
	}
}
