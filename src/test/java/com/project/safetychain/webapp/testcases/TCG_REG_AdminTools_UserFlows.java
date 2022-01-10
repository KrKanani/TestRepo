package com.project.safetychain.webapp.testcases;

import java.util.ArrayList;
import java.util.List;

import org.testng.SkipException;
import org.testng.annotations.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import com.project.safetychain.webapp.pageobjects.FSQABrowserPage;
import com.project.safetychain.webapp.pageobjects.HomePage;
import com.project.safetychain.webapp.pageobjects.LoginPage;
import com.project.safetychain.webapp.pageobjects.RolesManagerPage;
import com.project.safetychain.webapp.pageobjects.UserManagerPage;
import com.project.safetychain.webapp.pageobjects.CommonPage.TIMEZONE;
import com.project.safetychain.webapp.pageobjects.UserManagerPage.UsrDetailParams;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.utilities.CommonMethods;
import com.project.utilities.ControlActions;


public class TCG_REG_AdminTools_UserFlows extends TestBase{

	ControlActions controlActions;
	LoginPage lp;
	HomePage hp;
	FSQABrowserPage fbp;
	UserManagerPage ump;
	RolesManagerPage rmp;

	public static String workGroupName;
	public static String roleName;
	public static String userUN;
	public static String userFN;
	public static String userLN;
	public static String phone;
	public static List<String> userLocation;
	public static List<String> defaultLocation;
	public static List<String> userRole;
	public static List<String> userLocation2;
	public static List<String> userRole2;
	public static String pin;
	public static String username, firstName, lastName;


	@BeforeClass( alwaysRun = true)
	public void groupInit() throws InterruptedException
	{

		workGroupName = CommonMethods.dynamicText("WG");
		roleName = CommonMethods.dynamicText("RL");
		userUN = CommonMethods.dynamicText("UN");
		//	userUN = "SuperAdmin";
		userFN = CommonMethods.dynamicText("FN");
		userLN = CommonMethods.dynamicText("LN");
		phone = CommonMethods.dynamicText("phone");
		defaultLocation = new ArrayList<String>();
		userLocation = new ArrayList<String>();
		userLocation2 = new ArrayList<String>();
		userRole = new ArrayList<String>();
		userRole2 = new ArrayList<String>();
		pin = "12345";

		driver= launchbrowser();
		controlActions = new ControlActions(driver);
		controlActions.getUrl(applicationUrl);
		hp = new HomePage(driver);
		ump = new UserManagerPage(driver);
		lp = new LoginPage(driver);
		rmp= new RolesManagerPage(driver);
		hp = lp.performLogin(adminUsername, adminPassword);
		if(hp.error)
		{
			TCGFailureMsg = "Could NOT login to application";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
		//UsrDetailParams udp = new UsrDetailParams();
		rmp = hp.clickRolesMenu();

		boolean createRole = rmp.createRole(roleName);
		TestValidation.IsTrue(createRole, 
				"Created role - " + roleName, 
				"Could not create role - " + roleName);

		userLocation.add("ALL");
		userRole.add(roleName);

		//userLocation.add("ALL");
		//userRole.add(roleName);
		UserManagerPage ump = hp.clickUsersMenu();
		UsrDetailParams udp = new UsrDetailParams();
		udp.Username = userUN;
		udp.Password = GenericPassword;
		udp.FirstName = userFN;
		udp.LastName = userLN;
		udp.Email = "test@test.com";
		udp.Timezone = TIMEZONE.REPUBLICOFINDIA;
		udp.Locations = userLocation;
		udp.Roles = userRole;
		boolean userCreation = ump.createInternalUser(udp);
		if(!userCreation) {
			TCGFailureMsg = "Could NOT create user - " + userUN;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}


		if(!lp.loginAfterUserCreation(userUN, GenericPassword, GenericNewPassword)) {
			TCGFailureMsg = "Could NOT login with user - " + userUN;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
		/*
		if(!hp.userLogout()){
			TCGFailureMsg = "Could NOT logout from application";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
		//logout

		//login
		*/ 
	}



	@Test(description="Verification of Internal & Supplier Tab on Manage Users Page")

	public void TestCase_38227() throws Exception
	{
		//Click on Humburger menu

		//hp.clickFSQABrowserMenu();

		//Click on Admin tools > Users

		hp.clickUsersMenu();
		TestValidation.Equals(ump.error, 
				false, 
				"CLICKED on Users menu", 
				"Could NOT click on Users menu"); 

		// Manage user page should get opened

		// Check Internal tab is visible or not
		boolean internalButtonVisible= ump.visibleInternalTab();

		//Add validation code here
		TestValidation.IsTrue(internalButtonVisible, "Internal button is visible", "Internal button is not visible");

		//Check Supplier tan is visible or not
		boolean supplierButtonVisible=ump.visibleSupplierTab();

		//Add validation code here
		TestValidation.IsTrue(supplierButtonVisible, "Supplier button is visible", "Supplier button is not visible");




	}




	@Test(description="Verification of Location Filter on Manage Users Page")
	public void TestCase_38228() throws Exception
	{


		//hp.clickHamburgerMenu();
		//hp.clickAdminToolsMenu();

		//Manage user page should get opened
		hp.clickUsersMenu();
		TestValidation.Equals(ump.error, false, "Clicked on User menu", "Could not click on User menu");

		//Check Location filter is visible or not

		boolean locationFilterVisible= ump.visibleLocationFilter();

		//Add validation code 

		TestValidation.IsTrue(locationFilterVisible, "Location filter is available on manage user page", "Location filter is not available on manage user page");


	}



	@Test(description="Verification of all the grid filter on Manage Users Page")
	public void TestCase_38229() throws Exception	
	{


		//hp.clickHamburgerMenu();
		//hp.clickAdminToolsMenu();

		//Manage user page should get opened
		hp.clickUsersMenu();
		TestValidation.Equals(ump.error, false, "Clicked on User menu", "Could not click on User menu");

		//Check Location filter is visible or not

		boolean gridFilterVisible= ump.isFiltersVisible();

		//Add validation code 

		TestValidation.IsTrue(gridFilterVisible, "Grid filter is available on manage user page", "Grid filter is not available on manage user page");

	}




	@Test(description="Verification of duplicate username validation on \"Add New User\" Pop Up")
	public void TestCase_38231() throws Exception
	{

		hp.clickUsersMenu();
		TestValidation.Equals(ump.error, 
				false,
				"Clicked on User menu",
				"Could not click on User menu");

		boolean clickAddNewBtn=ump.clickAddNewUserBtn();
		boolean duplicateUserNameFiled=ump.vlidateDuplicateUsername();
		TestValidation.IsTrue(clickAddNewBtn && duplicateUserNameFiled, 
				"Verified - Username must be unique error is displayed",
				"Could Not verify unique username error, Another error message is displayed");

	}





	@Test(description="Verification of valid Email validation on \"Add New User\" Pop Up")
	public void TestCase_38232() throws Exception
	{
		//hp.clickAdminToolsMenu();
		hp.clickUsersMenu();
		TestValidation.Equals(ump.error,
				false,
				"Click on User menu",
				"Could not click on User menu");

		boolean clickAddNewBtn=ump.clickAddNewUserBtn();
		boolean validEmailAddressField=ump.validateEmailAddress();
		TestValidation.IsTrue(clickAddNewBtn && validEmailAddressField,
				"Verified - Please input a valid email address" ,
				"Could Not verify vaild email address. Another error message is displayed");
	}




	@Test(description="Verification of validation on all mandatory fields in \"Add New User\" Pop Up")
	public void TestCase_38230() throws Exception
	{

		hp.clickUsersMenu();
		TestValidation.Equals(ump.error,
				false,
				"Click on User menu", 
				"Could not click on User menu");

		boolean clickAddNewBtn=ump.clickAddNewUserBtn();	
		boolean validateRequiredFieldElement=ump.validateReuiredField();
		TestValidation.IsTrue(clickAddNewBtn && validateRequiredFieldElement,
				"Verified- Required field validation on Add New User Pop Up",
				"Could not verify Required field validation on Add New User Pop up");
	}




	@Test(description="Verification of User Creation for Internal User")
	public void TestCase_38233() throws Exception{

		try {

			//hp.clickFSQABrowserMenu();
			hp.clickUsersMenu();
			TestValidation.Equals(ump.error, 
					false, 
					"CLICKED on Users menu", 
					"Could NOT click on Users menu"); 

			boolean clickAddNewBtn = ump.clickAddNewUserBtn();
			TestValidation.IsTrue(clickAddNewBtn, 
					"CLICKED on Add User button", 
					"Could NOT click on Add User button");

			String username = CommonMethods.dynamicText("UN");
			String firstName = CommonMethods.dynamicText("FN");
			String lastName = CommonMethods.dynamicText("LN");
			String email = CommonMethods.dynamicText("EMAIL");
			userLocation2.add("ALL");
			userRole2.add("SuperAdmin");
			UsrDetailParams udp = new UsrDetailParams();
			udp.Username = username;
			udp.Password = GenericPassword;
			udp.FirstName = firstName;
			udp.LastName = lastName;
			udp.Email = email +"@test.com";
			udp.Timezone = "Republic of India";
			udp.Phone = "8976543211";
			udp.Locations = userLocation2;
			udp.DefaultLocation = defaultLocation;
			udp.WorkGroups1 = workGroupName;
			udp.Pin = pin;
			udp.Roles = userRole2;
			boolean userCreation = ump.setUsrDetails(udp);
			TestValidation.IsTrue(userCreation, 
					"Details set for user - " + username, 
					"Could NOT set details for user - " + username);

			boolean saveUser = ump.clickSaveBtn();
			TestValidation.IsTrue(saveUser, 
					"User " + username + " is SAVED successfully", 
					"Could NOT create User " + username);

			//ump.Logout.click();

		}
		finally {
			TestValidation.IsTrue(hp.userLogout(), 
					"User successfully LOGGED OUT from application", 
					"Failed to Logout user from application");
		}
	}



/*
	
		@Test(description="Verification of First & Last Name displayed correctly when user login after password reset ")
	public void TestCase_38236() throws Exception
	{ try {
		  
		

		hp = lp.performLogin(userUN, GenericNewPassword);
		TestValidation.IsTrue(!hp.error, 
							  "LOGGED IN with New user " + userUN, 
							  "Failed to login with New user " + userUN);

	//	ump.Logout.click();


	} catch (Exception e) {
		TestValidation.IsTrue(hp.userLogout(), 
				"User successfully LOGGED OUT from application", 
				"Failed to Logout user from application");
	}

	}
	/*
			home.userLogout();
			TV


			if(!(login.performLogin(userUN, userPassword, userNewPassword))) {
				TCGFailureMsg = "Could NOT login to application";
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}
			TV

			String actualTxtOnScreen = getLoggedInUserDetails();
			boolean = actualTxtOnScreen.equals(firstName + " " + lastName)
					TV
		}
		finally {
			if(!logout) {
				TestValidation.Fail("Could not Logout from user " + aa);
			}			login(groupinit userUN, "slip")
		}
	 */
	
}















/*

	@AfterClass(alwaysRun = true)
	public void closeBrowser() throws InterruptedException {
		driver.close();
	}
 */
//

