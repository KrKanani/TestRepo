package com.project.safetychain.webapp.testcases;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.project.safetychain.api.models.support.TCG_UserFlows_Wrapper;
import com.project.safetychain.api.models.support.Support.UserParams;
import com.project.safetychain.webapp.pageobjects.*;
import com.project.safetychain.webapp.pageobjects.UserManagerPage.USERFIELDS;
import com.project.safetychain.webapp.pageobjects.UserManagerPage.UsrDetailParams;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.testbase.SupportingClasses.ExecutionMode;
import com.project.utilities.CommonMethods;
import com.project.utilities.ControlActions;

public class TCG_SMK_AdminToolUsrFlows extends TestBase{

	ControlActions controlActions;
	HomePage hp;
	UserManagerPage ump;
	WorkGroupsPage wgp;
	RolesManagerPage rmp;
	public static String workGroupName;
	public static String roleName;
	public static String userUN;
	public static String userFN;
	public static String userLN;
	public static List<String> userLocation1;
	public static List<String> userRole1;
	public static List<String> userLocation2;
	public static List<String> userRole2;
	public static String username, firstName, lastName;

	@BeforeClass(alwaysRun = true)
	public void groupInit() throws InterruptedException {

		// Prerequisite 
		workGroupName = CommonMethods.dynamicText("WG");
		roleName = CommonMethods.dynamicText("RL");
		userUN = CommonMethods.dynamicText("UN");
		userFN = CommonMethods.dynamicText("FN");
		userLN = CommonMethods.dynamicText("LN");
		userLocation1 = new ArrayList<String>();
		userRole1 = new ArrayList<String>();
		userLocation2 = new ArrayList<String>();
		userRole2 = new ArrayList<String>();

		userLocation1.add("ALL");
		userRole1.add("SuperAdmin");
		
		if(executionMode.equalsIgnoreCase(ExecutionMode.API)) {
			
			TCG_UserFlows_Wrapper userCreationWrapper = new TCG_UserFlows_Wrapper();
			UserParams userDetails = new UserParams();
			userDetails.Username = userUN;
			userDetails.ClearPassword = GenericPassword;
			userDetails.FirstName = userFN;
			userDetails.LastName = userLN;
			userDetails.Email = "test@testdsfsd.com";
			userDetails.TimeZone = "U.S. Eastern";
			userDetails.LocationNmIds = userLocation1;
			userDetails.Roles = userRole1;

			userCreationWrapper.createUser_Wrapper(userDetails);
			
			// ------------------------------------------------------------------------------------------------
			// WEB Application code starts here
	
			driver = launchbrowser();
			controlActions = new ControlActions(driver);
			controlActions.getUrl(applicationUrl);
			hp = new HomePage(driver);
			ump = new UserManagerPage(driver);
			wgp = new WorkGroupsPage(driver);
			rmp = new RolesManagerPage(driver);

			LoginPage lp = new LoginPage(driver);
			hp = lp.performLogin(adminUsername, adminPassword);
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
			controlActions.getUrl(applicationUrl);
			hp = new HomePage(driver);
			ump = new UserManagerPage(driver);
			wgp = new WorkGroupsPage(driver);
			rmp = new RolesManagerPage(driver);

			LoginPage lp = new LoginPage(driver);
			hp = lp.performLogin(adminUsername, adminPassword);
			if(hp.error) {
				TCGFailureMsg = "Could NOT login to application";
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}
			
			UserManagerPage ump = hp.clickUsersMenu();
			UsrDetailParams udp = new UsrDetailParams();
			udp.Username = userUN;
			udp.Password = GenericPassword;
			udp.FirstName = userFN;
			udp.LastName = userLN;
			udp.Email = "abc@test.com";
			udp.Timezone = "U.S. Eastern";
			udp.Locations = userLocation1;
			udp.Roles = userRole1;
			boolean userCreation = ump.createInternalUser(udp);
			if(!userCreation) {
				TCGFailureMsg = "Could NOT create user - " + userUN;
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

	@Test(description="Creation of Internal User")
	public void TestCase_32556() throws Exception{

		hp.clickFSQABrowserMenu();
		hp.clickUsersMenu();
		TestValidation.Equals(ump.error, 
				false, 
				"CLICKED on Users menu", 
				"Could NOT click on Users menu"); 

		boolean clickAddBtn = ump.clickAddNewUserBtn();
		TestValidation.IsTrue(clickAddBtn, 
				"CLICKED on Add User button", 
				"Could NOT click on Add User button");

		username = CommonMethods.dynamicText("UN");
		firstName = CommonMethods.dynamicText("FN");
		lastName = CommonMethods.dynamicText("LN");
		userLocation2.add("ALL");
		userRole2.add("SuperAdmin");
		UsrDetailParams udp = new UsrDetailParams();
		udp.Username = username;
		udp.Password = GenericPassword;
		udp.FirstName = firstName;
		udp.LastName = lastName;
		udp.Email = "xyz@teseeet.com";
		udp.Timezone = "U.S. Eastern";
		udp.Locations = userLocation2;
		udp.Roles = userRole2;
		boolean userCreation = ump.setUsrDetails(udp);
		TestValidation.IsTrue(userCreation, 
				"Details set for user - " + username, 
				"Could NOT set details for user - " + username);

		boolean saveUser = ump.clickSaveBtn();
		TestValidation.IsTrue(saveUser, 
				"User " + username + " is SAVED successfully", 
				"Could NOT create User " + username);

		boolean userExists = ump.searchAndVerifyWithUsrDetails(USERFIELDS.USERNAME, username);
		TestValidation.IsTrue(userExists, 
				"VERIFIED User " + username + " exists", 
				"Could NOT verify if User " + username + " exists");
	}

	@Test(description="Creation of Workgroup")
	public void TestCase_32565() throws Exception{

		hp.clickFSQABrowserMenu();
		hp.clickWorkGroupsMenu();
		TestValidation.Equals(wgp.error, 
				false, 
				"CLICKED on Work Groups menu", 
				"Could NOT click on Work Groups menu");   

		boolean wgSetName = wgp.setWorkGroupName(workGroupName);
		TestValidation.IsTrue(wgSetName, 
				"Name SET for Work Group as - " + workGroupName, 
				"Could NOT set Work Group name as - " + workGroupName);  

		boolean saveWg1 = wgp.clickSaveBtn();
		TestValidation.IsTrue(saveWg1, 
				"SAVED Work Group - " + workGroupName, 
				"Could NOT save Work Group - " + workGroupName);   

		boolean wgAddUser = wgp.searchAndSelectUser(userUN);
		TestValidation.IsTrue(wgAddUser, 
				"ADDED user " + userUN + " to Work Group - " + workGroupName, 
				"Could NOT add user " + userUN + " to Work Group - " + workGroupName);  

		boolean saveWg2 = wgp.clickSaveBtn();
		TestValidation.IsTrue(saveWg2, 
				"SAVED Work Group - " + workGroupName, 
				"Could NOT save Work Group - " + workGroupName);  

		hp.clickFSQABrowserMenu();
		hp.clickWorkGroupsMenu();
		WebElement wgElement = controlActions.perform_GetElementByXPath(WorkGroupsPageLocators.VIEW_WORKGROUP_LST,
				"WORKGROUPNAME", workGroupName);
		boolean wgExists = controlActions.isElementDisplay(wgElement);
		TestValidation.IsTrue(wgExists, 
				"VERIFIED Work Group - " + workGroupName + " exists", 
				"Work Group - " + workGroupName+ " DOES NOT exist");
	}

	@Test(description="Creation of Roles")
	public void TestCase_32568() throws Exception{

		hp.clickFSQABrowserMenu();
		hp.clickRolesMenu();
		TestValidation.Equals(rmp.error, 
				false, 
				"CLICKED on Roles menu", 
				"Could NOT click on Roles menu");   

		boolean roleSetName = rmp.setRoleName(roleName);
		TestValidation.IsTrue(roleSetName, 
				"Name SET for Role as - " + roleName, 
				"Could NOT set Role name as - " + roleName);  

		boolean saveRole = rmp.clickSaveBtn();
		TestValidation.IsTrue(saveRole, 
				"SAVED Role - " + roleName, 
				"Could NOT save Role - " + roleName);   

		hp.clickFSQABrowserMenu();
		hp.clickRolesMenu();
		WebElement roleElement = controlActions.perform_GetElementByXPath(RolesManagerPageLocators.VIEW_ROLES_LST,
				"ROLESNAME", roleName);
		boolean roleExists = controlActions.isElementDisplay(roleElement);
		TestValidation.IsTrue(roleExists, 
				"VERIFIED Role - " + roleName + " exists", 
				"Role - " + roleName+ " DOES NOT exist");
	}	

	@AfterClass(alwaysRun = true)
	public void closeBrowser() throws InterruptedException {
		driver.close();
	}

}
