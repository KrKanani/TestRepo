package com.project.safetychain.webapp.testcases;


import java.util.HashMap;

import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.project.safetychain.webapp.pageobjects.*;
import com.project.safetychain.webapp.pageobjects.CommonPage.HAMBURGER_MENU_TITLE;
import com.project.safetychain.webapp.pageobjects.ResourceDesignerPage.CATEGORYTYPES;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.utilities.CommonMethods;
import com.project.utilities.ControlActions;

public class TCG_REG_TaskScheduler_GenericFlows extends TestBase{

	ControlActions controlActions;
	HomePage hp;
	LoginPage lp;
	TaskSchedulerPage tsp;
	FSQABrowserTaskPage fsqatp;
	ManageLocationPage locations;
	ResourceDesignerPage rdp;

	public static String locationCategoryValue;
	public static String locationInstanceValue;
	public static String customersCategoryValue;
	public static String customersInstanceValue;
	public static String equipmentCategoryValue;
	public static String equipmentInstanceValue;
	public static String itemsCategoryValue;
	public static String itemsInstanceValue;
	public static String suppliersCategoryValue;
	public static String suppliersInstanceValue;


	@BeforeClass(alwaysRun = true)
	public void groupInit() throws InterruptedException {

		driver = launchbrowser();
		controlActions = new ControlActions(driver);
		controlActions.getUrl(applicationUrl);
		hp = new HomePage(driver);
		lp = new LoginPage(driver);
		tsp= new TaskSchedulerPage(driver);
		hp = lp.performLogin(adminUsername, adminPassword);
		rdp = new ResourceDesignerPage(driver);

		locationCategoryValue = CommonMethods.dynamicText("LC");
		locationInstanceValue = CommonMethods.dynamicText("LI");
		customersInstanceValue = CommonMethods.dynamicText("CI");
		equipmentCategoryValue = CommonMethods.dynamicText("EC");
		equipmentInstanceValue = CommonMethods.dynamicText("EI");
		itemsCategoryValue = CommonMethods.dynamicText("IC");
		itemsInstanceValue = CommonMethods.dynamicText("II");
		suppliersCategoryValue = CommonMethods.dynamicText("SC");
		suppliersInstanceValue = CommonMethods.dynamicText("SI");
		customersCategoryValue = CommonMethods.dynamicText("CC");


		HashMap<String,String> resourceCategories = new HashMap<String,String>();
		resourceCategories.put(CATEGORYTYPES.LOCATION, locationCategoryValue);
		resourceCategories.put(CATEGORYTYPES.ITEMS, itemsCategoryValue);
		resourceCategories.put(CATEGORYTYPES.SUPPLIERS, suppliersCategoryValue);
		resourceCategories.put(CATEGORYTYPES.EQUIPMENT, equipmentCategoryValue);
		resourceCategories.put(CATEGORYTYPES.CUSTOMERS, customersCategoryValue);

		if(hp.error) {
			TCGFailureMsg = "Could NOT login to application";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		if(!rdp.createCategory(resourceCategories)) {
			TCGFailureMsg = "Could NOT create Locations/Items/Suppliers/Customers/Equipments category - ";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}


	}

	@Test(description="Task Scheduler - Task Scheduler Module - As a user, I want to be able to quickly navigate"
			+ " to the Task Scheduler module via the main navigation menu from anywhere in the application.(15790)")
	public void TestCase_22201() {

		boolean verifyClickHamburger= hp.clickHamburgerMenu();
		TestValidation.IsTrue(verifyClickHamburger, 
				"Clicked Hamburger succesfully ", 
				"Failed to click Hamburger");

		boolean verifyLabelTaskScheduler=hp.verifyLabelTaskSchedularMenu();
		TestValidation.IsTrue(verifyLabelTaskScheduler, 
				"Verified label TaskScheduler succesfully ", 
				"Failed to verify label TaskScheduler");

		boolean verifyLocTaskScheduler = hp.verifyLocationOfLabelInMnu( 3, HAMBURGER_MENU_TITLE.DOCUMENTMANAGEMENT ,
				HAMBURGER_MENU_TITLE.ADMINTOOLSMNU ,HAMBURGER_MENU_TITLE.TASKSCHEDULERMNU);
		TestValidation.IsTrue(verifyLocTaskScheduler, 
				"Verified location of  TaskScheduler succesfully ", 
				"Failed to verify location of  TaskScheduler");

		boolean verifyIcon= hp.verifyIconTaskSchedularMenu();
		TestValidation.IsTrue(verifyIcon, 
				"Verified icon of TaskScheduler succesfully ", 
				"Failed to verify icon of TaskScheduler");

		TaskSchedulerPage tsp =hp.clickTaskSchedulerTab();
		TestValidation.Equals(tsp.error, 
				false,
				"TaskScheduler is clicked succesfully ", 
				"Failed to click TaskScheduler");

		boolean viewByTaskBtnDisplayed= controlActions.isElementDisplayed(tsp.ViewByTaskBtn);
		TestValidation.IsTrue(viewByTaskBtnDisplayed, 
				"VERIFIED 'View By Task' button is displayed", 
				"Failed to verify 'View By Task' button is displayed");

	}

	@Test(description="Test case 23246: Task Scheduler || Location in the Add New Scheduler modal should "
			+ "be fetched based on location selected on the Task Scheduler index grid (23083)")
	public void TestCase_23246() {

		locations = new ManageLocationPage(driver);

		boolean createLocationInstance=locations.createLocationInstanceLinkUser(locationCategoryValue,locationInstanceValue,adminUsername);
		TestValidation.IsTrue(createLocationInstance, 
				"Succesfully created Location Instance", 
				"Failed to create Location Instance");

		TaskSchedulerPage tsp =hp.clickTaskSchedulerMenu();

		boolean viewByTaskBtnDisplayed= controlActions.isElementDisplayed(tsp.ViewByTaskBtn);
		TestValidation.IsTrue(viewByTaskBtnDisplayed, 
				"Navigated succesfully to Task Scheduler Page", 
				"Failed to verify 'View By Task' button is displayed");

		boolean selectLocation =tsp.selectLocationForGrid(locationInstanceValue);
		TestValidation.IsTrue(selectLocation, 
				"Succesfully selected Location "+locationInstanceValue+" in Grid", 
				"Failed to select Location "+locationInstanceValue+" in grid");
		boolean checkLocationForm = tsp.verifyLocationOfForm(locationInstanceValue);
		TestValidation.IsTrue(checkLocationForm, 
				"Grid location and Form location "+locationInstanceValue+" matches ", 
				"Failed, Grid location and Form location  "+locationInstanceValue+" are different");

	}




	@AfterClass(alwaysRun = true)
	public void closeBrowser() throws InterruptedException {
		driver.close();
	}

}
