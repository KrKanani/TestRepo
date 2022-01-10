package com.project.safetychain.webapp.testcases;

import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.project.safetychain.api.models.support.TCG_FormCreation_Wrapper;
import com.project.safetychain.api.models.support.Support.RESOURCE_TYPES;
import com.project.safetychain.webapp.pageobjects.CommonPage;
import com.project.safetychain.webapp.pageobjects.HomePage;
import com.project.safetychain.webapp.pageobjects.LoginPage;
import com.project.safetychain.webapp.pageobjects.ManageResourcePage;
import com.project.safetychain.webapp.pageobjects.ResourceDesignerPage;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.testbase.SupportingClasses.ExecutionMode;
import com.project.utilities.CommonMethods;
import com.project.utilities.ControlActions;

public class TCG_SMK_ResourceManager extends TestBase{

	ControlActions controlActions;
	public static CommonMethods methods;
	CommonPage mainPage;
	LoginPage lgnPge;
	ResourceDesignerPage resourceDesigner;
	ManageResourcePage resources;

	//Data
	public static String supplierCategoryValue;
	public static String supplierInstanceValue;

	@BeforeClass(alwaysRun = true)
	public void groupInit() throws Exception {
		supplierCategoryValue =  CommonMethods.dynamicText("Supplier_Cat");
		supplierInstanceValue = CommonMethods.dynamicText("Supp_Inst");
		
		if(executionMode.equalsIgnoreCase(ExecutionMode.API)) {

			// ------------------------------------------------------------------------------------------------
			// API Implementation
			TCG_FormCreation_Wrapper formCreationWrapper = new TCG_FormCreation_Wrapper();

			// ------------------------------------------------------------------------------------------------
			// API - User , Location & Resource Creation and Linking
			formCreationWrapper.createResourceCategory(supplierCategoryValue, RESOURCE_TYPES.SUPPLIERS);

			// ------------------------------------------------------------------------------------------------
			// WEB Application code starts here

			driver = launchbrowser();
			controlActions = new ControlActions(driver);
			mainPage = new CommonPage(driver);
			controlActions.getUrl(applicationUrl);
			lgnPge = new LoginPage(driver);
			resourceDesigner = new ResourceDesignerPage(driver);
			resources = new ManageResourcePage(driver);

			HomePage hp = lgnPge.performLogin(adminUsername,adminPassword);
			if(hp.error) {
				TCGFailureMsg = "Could NOT login to application";
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}	
		}
		else if(executionMode.equalsIgnoreCase(ExecutionMode.UI)) {

			// ------------------------------------------------------------------------------------------------
			// Only WEB Application code starts here

			driver = launchbrowser();
			controlActions = new ControlActions(driver);
			mainPage = new CommonPage(driver);
			controlActions.getUrl(applicationUrl);
			lgnPge = new LoginPage(driver);
			resourceDesigner = new ResourceDesignerPage(driver);
			resources = new ManageResourcePage(driver);

			HomePage hp = lgnPge.performLogin(adminUsername,adminPassword);
			if(hp.error) {
				TCGFailureMsg = "Could NOT login to application";
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}	

			ResourceDesignerPage rdp = mainPage.clickResourceDesignerMenu();
			if(rdp.error) {
				TCGFailureMsg ="Could NOT Open Resource Designer";
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}
			
			boolean creationOfSupplierCategory = resourceDesigner.createSuppliersCategory(supplierCategoryValue);
			if(!creationOfSupplierCategory) {
				TCGFailureMsg = "Fail to create supplier category";
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}
		}
		else {
			TCGFailureMsg = "Improper Execution Mode is set - " + executionMode;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
	}

	@Test(description="Creation of resource(Supplier)")
	public void TestCase_32567() {
		ManageResourcePage mrp = mainPage.clickResourcesMenu();
		TestValidation.Equals(mrp.error, 
				false, 
				"OPENED Manage Resource", 
				"Could NOT Open Manage Resource");

		boolean addNewSupplier = resources.addSupplierResourceInstance(supplierCategoryValue);	
		TestValidation.IsTrue(addNewSupplier,
				"ADDED supplier instance in Supplier category tree", 
				"FAILED to ADD supplier instance in Supplier category tree");

		boolean settingFieldValues = mrp.setFields(1234,"Automation Test", supplierInstanceValue);
		TestValidation.IsTrue(settingFieldValues,
				"VALUE have been set in fields", 
				"FAILED to set value in fields");

		controlActions.clickElement(resources.SaveResourceBtn);
		logInfo("Click on 'SAVE' resource instance");

		boolean modifiedByDisplayed = resources.verifyCreatedResource();
		TestValidation.IsTrue(modifiedByDisplayed,
				"VERIFIED saved supplier instance in supplier category tree", 
				"FAILED to SAVE created supplier instance in supplier category tree");
	}

	@AfterClass(alwaysRun = true)
	public void closeBrowser() throws InterruptedException {
		driver.close();
	}
}
