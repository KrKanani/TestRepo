package com.project.safetychain.webapp.testcases;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.project.safetychain.webapp.pageobjects.*;
import com.project.safetychain.webapp.pageobjects.AddEditValidationsPage.VALIDATION_RESULT;
import com.project.safetychain.webapp.pageobjects.AddEditValidationsPage.VALIDATION_TYPE;
import com.project.safetychain.webapp.pageobjects.AddEditValidationsPage.VldtnDetsParams;
import com.project.safetychain.webapp.pageobjects.CommonPage.TIMEZONE;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.COLUMNHEADER;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.FSQARESOURCE;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.FSQATAB;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FIELD_TYPES;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FORMRESOURCETYPES;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FORMTYPES;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FormDesignParams;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FormFieldParams;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.IDENTIFIER_INPUT_TYPE;
import com.project.safetychain.webapp.pageobjects.ManageLocationPage.LocationInstanceParams;
import com.project.safetychain.webapp.pageobjects.ManageResourcePage.RESOURCETYPES;
import com.project.safetychain.webapp.pageobjects.ManageResourcePage.ResourceDetailParams;
import com.project.safetychain.webapp.pageobjects.ResourceDesignerPage.CATEGORYTYPES;
import com.project.safetychain.webapp.pageobjects.RolesManagerPage.COLUMN_HEADER;
import com.project.safetychain.webapp.pageobjects.RolesManagerPage.ROLE_ACTION;
import com.project.safetychain.webapp.pageobjects.RolesManagerPage.ROLE_PERMISSION;
import com.project.safetychain.webapp.pageobjects.UserManagerPage.UsrDetailParams;
import com.project.safetychain.webapp.pageobjects.ValidationsPage.VLDTN_FIELDS;
import com.project.safetychain.webapp.testcases.support.FormOperations;
import com.project.safetychain.webapp.testcases.support.FormOperations.FormDetails;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.utilities.CommonMethods;
import com.project.utilities.ControlActions;
import com.project.utilities.DateTime;

public class TCG_REG_Validation_PermissionFlows extends TestBase{

	ControlActions controlActions;
	HomePage hp;
	LoginPage lp;
	DateTime dt = new DateTime();
	public static String workGroupName;
	public static String roleName;
	public static String userUN;
	public static String userFN;
	public static String userLN;
	public static List<String> userLocation;
	public static List<String> userRole;
	public static String username, firstName, lastName;
	public static String locationCategoryValue;
	public static String locationInstanceValue;
	public static String customerCategoryValue;
	public static String customerInstanceValue;
	public static String formName;
	public static String numericFN = "Number";
	public static String identifierFN = "ID";
	public static String vldtnName;
	public static String vldtnSummary;
	HashMap<String,String> identifierDets;

	@BeforeClass(alwaysRun = true)
	public void groupInit() throws InterruptedException {

		workGroupName = CommonMethods.dynamicText("WG");
		roleName = CommonMethods.dynamicText("RL");
		userUN = CommonMethods.dynamicText("UN");
		userFN = CommonMethods.dynamicText("FN");
		userLN = CommonMethods.dynamicText("LN");
		userLocation = new ArrayList<String>();
		userRole = new ArrayList<String>();
		locationCategoryValue = CommonMethods.dynamicText("Loc_Cat");
		locationInstanceValue = CommonMethods.dynamicText("Loc_Inst");
		customerCategoryValue = CommonMethods.dynamicText("Cust_Cat");
		customerInstanceValue = CommonMethods.dynamicText("Cust_Inst");
		formName = CommonMethods.dynamicText("VldtnPermssn");
		vldtnName = CommonMethods.dynamicText("Vldtn");
		vldtnSummary = CommonMethods.dynamicText("Summary");
		
		driver = launchbrowser();
		controlActions = new ControlActions(driver);
		controlActions.getUrl(applicationUrl);
		hp = new HomePage(driver);
		lp = new LoginPage(driver);
		
		hp = lp.performLogin(adminUsername, adminPassword);
		if(hp.error) {
			TCGFailureMsg = "Could NOT login to application";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
		
		WorkGroupsPage wgp = new WorkGroupsPage(driver);
		if(!wgp.createWorkGroup(workGroupName)) {
			TCGFailureMsg = "Could not create workgroup - " + workGroupName;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
		
		RolesManagerPage rmp = hp.clickRolesMenu();
		if(!rmp.createRole(roleName)) {
			TCGFailureMsg = "Could not create role - " + roleName;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		userLocation.add("ALL");
		userRole.add(roleName);
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

		hp.clickFSQABrowserMenu();
		//Category creation
		HashMap<String,String> categories = new HashMap<String, String>();
		categories.put(CATEGORYTYPES.CUSTOMERS, customerCategoryValue);
		categories.put(CATEGORYTYPES.LOCATION, locationCategoryValue);
		ResourceDesignerPage rdp = hp.clickResourceDesignerMenu();
		if(!rdp.createCategory(categories)) {
			TCGFailureMsg = "Could NOT create Resource categories";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		//Location instance
		HashMap<String,Boolean> locInstance = new HashMap<String, Boolean>();
		locInstance.put(locationInstanceValue, true);
		LocationInstanceParams lip = new LocationInstanceParams();
		lip.CategoryName = locationCategoryValue;
		lip.NumberFieldValue = 10;
		lip.TextFieldValue = "XYZ";
		lip.InstanceName = locInstance;
		ManageLocationPage mlp = hp.clickLocationsMenu();
		if(!mlp.createLocation(lip)) {
			TCGFailureMsg = "Could NOT create Location instance";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		//Resource creation
		HashMap<String,Boolean> instances = new HashMap<String, Boolean>();
		instances.put(customerInstanceValue, true);
		ResourceDetailParams rd = new ResourceDetailParams();
		rd.CategoryName = customerCategoryValue;
		rd.CategoryType = RESOURCETYPES.CUSTOMERS;
		rd.NumberFieldValue = 30;
		rd.TextFieldValue = "ABC";
		rd.InstanceNames = instances;
		rd.LocationInstanceValue = locationInstanceValue;	
		ManageResourcePage mrp = hp.clickResourcesMenu();
		if(!mrp.createResourceLinkLocation(rd)) {
			TCGFailureMsg = "Could NOT create Instances for resource - " + customerInstanceValue;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		HashMap<String, List<String>> selectResources = new HashMap<String, List<String>>();
		selectResources.put(FORMRESOURCETYPES.CUSTOMERS,Arrays.asList(customerCategoryValue));

		HashMap<String,List<String>> fieldDetails = new HashMap<String,List<String>>();
		fieldDetails.put(FIELD_TYPES.NUMERIC, Arrays.asList(numericFN));
		fieldDetails.put(FIELD_TYPES.IDENTIFIER, Arrays.asList(identifierFN));

		FormFieldParams ffparams = new FormFieldParams();
		ffparams.FieldDetails = fieldDetails;		
		ffparams.IdentiferType = IDENTIFIER_INPUT_TYPE.SELECT_ONE;
		ffparams.IdentifierValue = Arrays.asList("LINE1","LINE2");

		FormDesignParams fdparams = new FormDesignParams();
		fdparams.FormType = FORMTYPES.CHECK;
		fdparams.FormName = formName;
		fdparams.SelectResources = selectResources;
		fdparams.DesignFields = Arrays.asList(ffparams);

		FormDesignerPage fdp = hp.clickFormDesignerMenu();
		if(!fdp.createAndReleaseForm(fdparams)) {
			TCGFailureMsg = "Could NOT create and release form - " + formName;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		FSQABrowserPage fbp = hp.clickFSQABrowserMenu();
		if(!fbp.selectResourceDropDownandNavigate(FSQARESOURCE.CUSTOMERS,FSQATAB.FORMS)) {
			TCGFailureMsg = "For customer category, could NOT navigate to FSQABrowser > Forms tab";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		if(!fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, formName)) {
			TCGFailureMsg = "Could not filter form - " + formName;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		HashMap<String, List<String>> fillDetails = new HashMap<String, List<String>>();
		fillDetails.put(numericFN,Arrays.asList("4"));
		fillDetails.put(identifierFN,Arrays.asList("LINE1"));

		FormDetails fd = new FormDetails();
		fd.fieldDetails = fillDetails;

		FormOperations fo = new FormOperations(driver);
		if(!fo.submitData(fd)) {
			TCGFailureMsg = "Failed to submit form";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
		
		ValidationsPage vp = hp.clickValidationsMenu();
		AddEditValidationsPage aevp = vp.createNewValidation();
		if(aevp.error) {
			TCGFailureMsg = "Could NOT navigate to create new Validations page";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
		
		identifierDets = new HashMap<String, String>();
		identifierDets.put(identifierFN, "LINE1");
		
		VldtnDetsParams vdp = new VldtnDetsParams();
		vdp.Name = vldtnName;
		vdp.Type = VALIDATION_TYPE.NEW_PROCEDURES;
		vdp.Location = locationInstanceValue;
		vdp.Resource = customerInstanceValue;
		vdp.Forms = Arrays.asList(formName);
		vdp.StartDate = dt.AddDaystoToddaysDate(-1, "MM/dd/yyyy hh:mm aa");
		vdp.EndDate = dt.AddDaystoToddaysDate(0, "MM/dd/yyyy hh:mm aa");
		vdp.Identifiers = identifierDets;
		if(!aevp.createAndAddValidation(vdp)) {
			TCGFailureMsg = "Could NOT add validation - " + vldtnName;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
		
		if(!lp.loginAfterUserCreation(userUN, GenericPassword, GenericNewPassword)) {
			TCGFailureMsg = "Could NOT login with user - " + userUN;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
		
		if(!hp.userLogout()){
			TCGFailureMsg = "Could NOT logout from application";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
		
	}

	@Test(description="Validations || Verify when the 'Access Subsystem' permission is 'No' "
			+ "then user should not be able to view Validations in Admin Tools.")
	public void TestCase_34001() {
		try {
			
			hp = lp.performLogin(adminUsername, adminPassword);
			TestValidation.IsTrue(!hp.error, 
								  "LOGGED IN with Admin user " + adminUsername, 
								  "Failed to login with Admin user " + adminUsername);
			
			RolesManagerPage rmp = hp.clickRolesMenu();
			boolean updateRole = rmp.searchAndSetRoleForModule(roleName, "Validation", COLUMN_HEADER.ACTION, 
					ROLE_ACTION.ACCESS_SUBSYSTEM, ROLE_PERMISSION.NO);
			TestValidation.IsTrue(updateRole, 
								  "UPDATED the role " + roleName + " with validation " + ROLE_ACTION.ACCESS_SUBSYSTEM + 
								  " as " + ROLE_PERMISSION.NO, 
								  "Failed to update role " + roleName);
			
			boolean logout = hp.userLogout();
			TestValidation.IsTrue(logout, 
								  "Log out from application", 
								  "Failed to Log out from application");
			
			hp = lp.performLogin(userUN, GenericNewPassword);
			TestValidation.IsTrue(!hp.error, 
								  "LOGGED IN with Admin user " + userUN, 
								  "Failed to login with Admin user " + userUN);
			
			boolean openAdminTools = hp.clickAdminToolsMenu();
			TestValidation.IsTrue(openAdminTools, 
								  "OPENED Admin Tools menu", 
								  "Failed to open Admin Tools menu");
			
			WebElement ValidationMnu = controlActions.performGetElementByXPath(CommonPageLocators.VALIDATIONS_MNU);
			TestValidation.IsTrue(ValidationMnu==null, 
								  "VERIFIED Validations menu is not displayed", 
								  "Failed to verify Validations menu is displayed or not");
			
		}
 
		finally {
			if(!hp.userLogout()){
				TCGFailureMsg = "Could NOT logout from application";
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}
		}
	}
	
	@Test(description="Validations || Verify when the 'Add/Edit Validations' permission is 'No' "
			+ "then the user should be able view details of validation on edit screen in read only mode.")
	public void TestCase_34002() {
		try {
			
			hp = lp.performLogin(adminUsername, adminPassword);
			TestValidation.IsTrue(!hp.error, 
								  "LOGGED IN with Admin user " + adminUsername, 
								  "Failed to login with Admin user " + adminUsername);
			
			RolesManagerPage rmp = hp.clickRolesMenu();
			boolean updateAccessSubsystemRole = rmp.searchAndSetRoleForModule(roleName, "Validation", COLUMN_HEADER.ACTION, 
					ROLE_ACTION.ACCESS_SUBSYSTEM, ROLE_PERMISSION.YES);
			TestValidation.IsTrue(updateAccessSubsystemRole, 
								  "UPDATED the role " + roleName + " with validation " + ROLE_ACTION.ACCESS_SUBSYSTEM + 
								  " as " + ROLE_PERMISSION.YES, 
								  "Failed to update role " + roleName);
			
			boolean updateAddEditRole = rmp.searchAndSetRoleForModule(null, null, COLUMN_HEADER.ACTION, 
					ROLE_ACTION.ADDEDIT_VALIDATION, ROLE_PERMISSION.NO);
			TestValidation.IsTrue(updateAddEditRole, 
					 			  "UPDATED the role " + roleName + " with validation " + ROLE_ACTION.ADDEDIT_VALIDATION + 
					 			  " as " + ROLE_PERMISSION.NO, 
					 			  "Failed to update role " + roleName);
			
			boolean logout = hp.userLogout();
			TestValidation.IsTrue(logout, 
								  "Log out from application", 
								  "Failed to Log out from application");
			
			hp = lp.performLogin(userUN, GenericNewPassword);
			TestValidation.IsTrue(!hp.error, 
								  "LOGGED IN with Admin user " + userUN, 
								  "Failed to login with Admin user " + userUN);
			
			ValidationsPage vp = hp.clickValidationsMenu();
			TestValidation.Equals(vp.error, 
								  false, 
								  "CLICKED on Validations menu", 
								  "Could NOT click on Validations menu"); 

			boolean openValidation = vp.openValidationByIndex(1);
			TestValidation.IsTrue(openValidation, 
								  "OPENED Validation at index 1", 
								  "Failed to open Validation at index 1");
			
			WebElement VldtnUpdateBtn = controlActions.performGetElementById(AddEditValidationsPageLocators.UPDATE_VLDTN_BTN);
			TestValidation.IsTrue(VldtnUpdateBtn==null, 
								  "VERIFIED Update validation button is not displayed", 
								  "Failed to verify Update validation button is displayed or not");
			
			WebElement VldtnClearBtn = controlActions.performGetElementById(AddEditValidationsPageLocators.CLEAR_VLDTN_BTN);
			TestValidation.IsTrue(VldtnClearBtn==null, 
								  "VERIFIED Clear validation button is not displayed", 
								  "Failed to verify Clear validation button is displayed or not");
			
			WebElement VldtnDeleteBtn = controlActions.performGetElementById(AddEditValidationsPageLocators.DELETE_VLDTN_BTN);
			TestValidation.IsTrue(VldtnDeleteBtn==null, 
								  "VERIFIED Delete validation button is not displayed", 
								  "Failed to verify Delete validation button is displayed or not");
			
			WebElement VldtnCopyBtn = controlActions.performGetElementById(AddEditValidationsPageLocators.COPY_VLDTN_BTN);
			TestValidation.IsTrue(VldtnCopyBtn==null, 
								  "VERIFIED Copy validation button is not displayed", 
								  "Failed to verify Copy validation button is displayed or not");
			
		}
 
		finally {
			if(!hp.userLogout()){
				TCGFailureMsg = "Could NOT logout from application";
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}
		}
	}
	
	@Test(description="Verification || Verify when 'Complete Validation' permission is 'Off' then the Complete Button "
			+ "should be disabled. ")
	public void TestCase_34003() {
		try {
			
			hp = lp.performLogin(adminUsername, adminPassword);
			TestValidation.IsTrue(!hp.error, 
								  "LOGGED IN with Admin user " + adminUsername, 
								  "Failed to login with Admin user " + adminUsername);
			
			RolesManagerPage rmp = hp.clickRolesMenu();
			boolean updateAccessSubsystemRole = rmp.searchAndSetRoleForModule(roleName, "Validation", COLUMN_HEADER.ACTION, 
					ROLE_ACTION.ACCESS_SUBSYSTEM, ROLE_PERMISSION.YES);
			TestValidation.IsTrue(updateAccessSubsystemRole, 
								  "UPDATED the role " + roleName + " with validation " + ROLE_ACTION.ACCESS_SUBSYSTEM + 
								  " as " + ROLE_PERMISSION.YES, 
								  "Failed to update role " + roleName);
			
			boolean updateAddEditRole = rmp.searchAndSetRoleForModule(null, null, COLUMN_HEADER.ACTION, 
					ROLE_ACTION.ADDEDIT_VALIDATION, ROLE_PERMISSION.YES);
			TestValidation.IsTrue(updateAddEditRole, 
					 			  "UPDATED the role " + roleName + " with validation " + ROLE_ACTION.ADDEDIT_VALIDATION + 
					 			  " as " + ROLE_PERMISSION.YES, 
					 			  "Failed to update role " + roleName);
			
			boolean updateCompleteRole = rmp.searchAndSetRoleForModule(null, null, COLUMN_HEADER.ACTION, 
					ROLE_ACTION.COMPLETE_VALIDATION, ROLE_PERMISSION.NO);
			TestValidation.IsTrue(updateCompleteRole, 
					 			  "UPDATED the role " + roleName + " with validation " + ROLE_ACTION.COMPLETE_VALIDATION + 
					 			  " as " + ROLE_PERMISSION.NO, 
					 			  "Failed to update role " + roleName);
			
			boolean logout = hp.userLogout();
			TestValidation.IsTrue(logout, 
								  "Log out from application", 
								  "Failed to Log out from application");
			
			hp = lp.performLogin(userUN, GenericNewPassword);
			TestValidation.IsTrue(!hp.error, 
								  "LOGGED IN with Admin user " + userUN, 
								  "Failed to login with Admin user " + userUN);
			
			ValidationsPage vp = hp.clickValidationsMenu();
			TestValidation.Equals(vp.error, 
								  false, 
								  "CLICKED on Validations menu", 
								  "Could NOT click on Validations menu"); 

			AddEditValidationsPage aevp = vp.searchAndOpenVldtn(VLDTN_FIELDS.NAME, vldtnName);
			TestValidation.Equals(aevp.error, 
								  false, 
								  "OPENED validtion " + vldtnName, 
								  "Could NOT open validation " + vldtnName);
			
			WebElement CompleteVldtnBtn = controlActions.performGetElementById(AddEditValidationsPageLocators.COMPLETE_VLDTN_BTN);
			TestValidation.IsTrue(CompleteVldtnBtn==null, 
								  "VERIFIED Complete validation button is not displayed", 
								  "Failed to verify Complete validation button is displayed or not");
			
		}
 
		finally {
			if(!hp.userLogout()){
				TCGFailureMsg = "Could NOT logout from application";
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}
		}
	}
	
	@Test(description="Validations || Verify when the user do not have ‘Add/Edit permission’ but has ‘Complete Validation’ "
			+ "permission, then the user should be able to view the ‘edit validation’ screen in read only mode "
			+ "and ‘Complete validation’ button should be enabled.")
	public void TestCase_34004() {
		try {
			
			hp = lp.performLogin(adminUsername, adminPassword);
			TestValidation.IsTrue(!hp.error, 
								  "LOGGED IN with Admin user " + adminUsername, 
								  "Failed to login with Admin user " + adminUsername);
			
			RolesManagerPage rmp = hp.clickRolesMenu();
			boolean updateAccessSubsystemRole = rmp.searchAndSetRoleForModule(roleName, "Validation", COLUMN_HEADER.ACTION, 
					ROLE_ACTION.ACCESS_SUBSYSTEM, ROLE_PERMISSION.YES);
			TestValidation.IsTrue(updateAccessSubsystemRole, 
								  "UPDATED the role " + roleName + " with validation " + ROLE_ACTION.ACCESS_SUBSYSTEM + 
								  " as " + ROLE_PERMISSION.YES, 
								  "Failed to update role " + roleName);
			
			boolean updateAddEditRole = rmp.searchAndSetRoleForModule(null, null, COLUMN_HEADER.ACTION, 
					ROLE_ACTION.ADDEDIT_VALIDATION, ROLE_PERMISSION.NO);
			TestValidation.IsTrue(updateAddEditRole, 
					 			  "UPDATED the role " + roleName + " with validation " + ROLE_ACTION.ADDEDIT_VALIDATION + 
					 			  " as " + ROLE_PERMISSION.NO, 
					 			  "Failed to update role " + roleName);
			
			boolean updateCompleteRole = rmp.searchAndSetRoleForModule(null, null, COLUMN_HEADER.ACTION, 
					ROLE_ACTION.COMPLETE_VALIDATION, ROLE_PERMISSION.YES);
			TestValidation.IsTrue(updateCompleteRole, 
					 			  "UPDATED the role " + roleName + " with validation " + ROLE_ACTION.COMPLETE_VALIDATION + 
					 			  " as " + ROLE_PERMISSION.YES, 
					 			  "Failed to update role " + roleName);
			
			boolean logout = hp.userLogout();
			TestValidation.IsTrue(logout, 
								  "Log out from application", 
								  "Failed to Log out from application");
			
			hp = lp.performLogin(userUN, GenericNewPassword);
			TestValidation.IsTrue(!hp.error, 
								  "LOGGED IN with Admin user " + userUN, 
								  "Failed to login with Admin user " + userUN);
			
			ValidationsPage vp = hp.clickValidationsMenu();
			TestValidation.Equals(vp.error, 
								  false, 
								  "CLICKED on Validations menu", 
								  "Could NOT click on Validations menu"); 

			AddEditValidationsPage aevp = vp.searchAndOpenVldtn(VLDTN_FIELDS.NAME, vldtnName);
			TestValidation.Equals(aevp.error, 
								  false, 
								  "OPENED validtion " + vldtnName, 
								  "Could NOT open validation " + vldtnName);
			
			boolean setName = aevp.setValidationName("Updated "+ vldtnName);
			TestValidation.IsFalse(setName, 
					  			   "The Validation name is in READ ONLY mode", 
					  			   "Failed to verify whether Validation name is or is not in Read Only mode");
			
			boolean setType = aevp.setValidationType(VALIDATION_TYPE.NEW_EQUIPMENT);
			TestValidation.IsFalse(setType, 
					  			   "The Validation type is in READ ONLY mode", 
					  			   "Failed to verify whether Validation type is or is not in Read Only mode");
			
			boolean setLocation = aevp.setValidationLocation(locationInstanceValue);
			TestValidation.IsFalse(setLocation, 
					  			   "The Validation location is in READ ONLY mode", 
					  			   "Failed to verify whether Validation location is or is not in Read Only mode");
			
			boolean setIdentifiers = aevp.setValidationIdentifiers(identifierDets);
			TestValidation.IsFalse(setIdentifiers, 
		  			   			   "The Validation identifiers is in READ ONLY mode", 
		  			   			   "Failed to verify whether Validation identifiers is or is not in Read Only mode");
			
			boolean completeVldtn = aevp.completeValidationWithResult(VALIDATION_RESULT.PASS, vldtnSummary);
			TestValidation.IsTrue(completeVldtn, 
								  "COMPLETED Validation " + vldtnName + " with status " + VALIDATION_RESULT.PASS, 
								  "Could NOT complete Validation " + vldtnName + " with status " + VALIDATION_RESULT.PASS);
			
			boolean verifySummary = controlActions.perform_CheckIfElementTextContains(aevp.CompleteVldtnDets, vldtnSummary);
			boolean verifyResult = controlActions.perform_CheckIfElementTextContains(aevp.CompleteVldtnDets, VALIDATION_RESULT.PASS);
			TestValidation.IsTrue((verifySummary && verifyResult), 
					  			  "VERIFIED summary details as " + vldtnSummary + " for validation " + vldtnName, 
					  			  "Could NOT verify summary details as " + vldtnSummary + " for validation " + vldtnName);
			
		}
 
		finally {
			if(!hp.userLogout()){
				TCGFailureMsg = "Could NOT logout from application";
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}
		}
	}
	

	@AfterClass(alwaysRun = true)
	public void closeBrowser() throws InterruptedException {
		driver.close();
	}

}
