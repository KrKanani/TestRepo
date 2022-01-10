package com.project.safetychain.pcapp.testcases;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.project.safetychain.pcapp.pageobjects.CommonScreen;
import com.project.safetychain.pcapp.pageobjects.FormViewScreen;
import com.project.safetychain.pcapp.pageobjects.FormViewScreen.FormDetailsPC;
import com.project.safetychain.webapp.pageobjects.CommonPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage;
import com.project.safetychain.webapp.pageobjects.HomePage;
import com.project.safetychain.webapp.pageobjects.LoginPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.COLUMNHEADER;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.FSQARESOURCE;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.FSQATAB;
import com.project.safetychain.pcapp.pageobjects.FormsScreen;
import com.project.safetychain.pcapp.pageobjects.LoginScreen;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.utilities.ControlActions;
import com.project.utilities.ControlActions_PCApp;

public class TCG_PCWebDemo extends TestBase{
	ControlActions_PCApp controlActions;
	FormsScreen forms;
	FormViewScreen formview; 

	String formName = "Regular Inspection Form";
	String location = "Amravati", resource = "Cust1";

	ControlActions controlActionsWeb;
	LoginPage login;
	HomePage homePage;
	CommonPage mainPage;

	@BeforeClass(alwaysRun = true)
	public void groupInit() {
		try {
			PCAppDriver = launchPCApp();
			controlActions = new ControlActions_PCApp(PCAppDriver);
			forms = new FormsScreen(PCAppDriver);
			formview = new FormViewScreen(PCAppDriver);
		}catch(Exception e){
			e.printStackTrace();
		}
	}


	@Test(description="Verify PC App Submisison on web")
	public void verifyPCAppSubmission() throws Exception{
		LoginScreen loginScreen = new LoginScreen(PCAppDriver);
		//CommonScreen commonScreen = loginScreen.Login(pcAppUsername, pcAppPassword);
		//threadsleep(10000);
		CommonScreen commonScreen = loginScreen.Login("test1.qa", "tester","Test@1");

		TestValidation.IsFalse(commonScreen.error,
				"App is launched", 
				"App could not be launched");

		forms = forms.selectForms();
		forms.selectOpenForm(formName);

		FormDetailsPC fd = new FormDetailsPC();
		HashMap<String, List<String>> map1 = new LinkedHashMap<String, List<String>>();
		map1.put("Inspection Needed ?",Arrays.asList("Yes"));
		map1.put("Weight",Arrays.asList("25"));
		map1.put("Comment",Arrays.asList("Test"));

		fd.locationName = location;
		fd.resourceName = resource;
		fd.fieldDetails = map1;
		formview.fillDataInForm1(fd);
		PCAppDriver.close();

		driver = launchbrowser();
		controlActionsWeb = new ControlActions(driver);
		mainPage = new CommonPage(driver);
		login = new LoginPage(driver);
		controlActionsWeb.getUrl("http://test1.qa.safetychain.com/");
		homePage = login.performLogin("tester","Test@1");
		TestValidation.IsFalse(homePage.error, 
				"LOGGED to application", "FAILED to login to application");

		FSQABrowserPage fbp = homePage.clickFSQABrowserMenu();
		boolean navigateToRecords = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.CUSTOMERS,FSQATAB.RECORDS);
		TestValidation.IsTrue(navigateToRecords, 
				"For customer category, NAVIGATED to FSQABrowser > Records tab", 
				"FAILED to navigate to FSQABrowser > Records tab");

		boolean openRecord = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, formName);
		TestValidation.IsTrue(openRecord, 
				"OPENED record for form - " + formName, 
				"FAILED to open record for form - " + formName);

	}


	@AfterClass(alwaysRun = true)
	public void closeBrowser() throws InterruptedException {
		if(PCAppDriver!=null) {
			PCAppDriver.close();
		}
	}

}
