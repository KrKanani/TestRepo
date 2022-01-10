package com.project.safetychain.webapp.testcases;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.project.safetychain.webapp.pageobjects.HomePage;
import com.project.safetychain.webapp.pageobjects.LoginPage;
import com.project.safetychain.webapp.pageobjects.DataImportExportPage.COLUMN_SETTING;
import com.project.safetychain.webapp.pageobjects.DataImportExportPage.ColumnNames;
import com.project.safetychain.webapp.pageobjects.DataImportExportPage.ExtractType;
import com.project.safetychain.webapp.pageobjects.DataImportExportPage.IMPORT_STATUS;
import com.project.safetychain.webapp.pageobjects.DataImportExportPage.ImportType;
import com.project.safetychain.webapp.pageobjects.DataImportExportPage.UserInfoDetails;
import com.project.safetychain.webapp.pageobjects.RolesManagerPage.ROLES_TYPES;
import com.project.safetychain.webapp.pageobjects.UserManagerPage;
import com.project.safetychain.webapp.pageobjects.UserManagerPage.USERFIELDS;
import com.project.safetychain.webapp.pageobjects.UserManagerPage.UsrDetailParams;
import com.project.safetychain.api.models.support.Support.UserParams;
import com.project.safetychain.api.models.support.TCG_UserFlows_Wrapper;
import com.project.safetychain.webapp.pageobjects.CommonPage.TIMEZONE;
import com.project.safetychain.webapp.pageobjects.DataImportExportPage;
import com.project.safetychain.webapp.pageobjects.DataImportExportPageLocators;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.testbase.SupportingClasses.ExecutionMode;
import com.project.utilities.CommonMethods;
import com.project.utilities.ControlActions;
import com.project.utilities.DateTime;

public class TCG_REG_DPT_UserFlows extends TestBase{
	ControlActions controlActions;
	HomePage hp;
	DateTime dt = new DateTime();
	
	public static String userUN1;
	public static String userFN1;
	public static String userLN1;
	public static String userUN2;
	public static String userFN2;
	public static String userLN2;
	public static String emailUN1 = "test1@tst.com";
	public static String emailUN2 = "test2@tst.com";
	public static String timezoneUN1 = TIMEZONE.USEASTERN;
	public static String timezoneUN2 = TIMEZONE.USPACIFIC;
	public static String phoneNumber = "9999900000";
	public static List<String> userLocation1 = new ArrayList<String>();
	public static List<String> userRole1 = new ArrayList<String>();
	public static List<String> userLocation2 = new ArrayList<String>();
	public static List<String> userRole2 = new ArrayList<String>();
	
	
	
	@BeforeClass(alwaysRun = true)
	public void groupInit() throws Exception {

		userUN1 = CommonMethods.dynamicText("UN1");
		userFN1 = CommonMethods.dynamicText("FN1");
		userLN1 = CommonMethods.dynamicText("LN1");
		userUN2 = CommonMethods.dynamicText("UN2");
		userFN2 = CommonMethods.dynamicText("FN2");
		userLN2 = CommonMethods.dynamicText("LN2");

		userLocation1.add("ALL");
		userRole1.add(ROLES_TYPES.SUPERADMIN);
		userLocation2.add(locationName);
		userRole2.add(ROLES_TYPES.ADMIN_TOOL_USER);
		
		
		if(executionMode.equalsIgnoreCase(ExecutionMode.API)) {
			
			// ------------------------------------------------------------------------------------------------
			// API Implementation
			TCG_UserFlows_Wrapper userCreationWrapper = new TCG_UserFlows_Wrapper();
			
			// User 1 Creation
			UserParams userDetails1 = new UserParams();
			userDetails1.Username = userUN1;
			userDetails1.ClearPassword = GenericPassword;
			userDetails1.FirstName = userFN1;
			userDetails1.LastName = userLN1;
			userDetails1.Email = emailUN1;
			userDetails1.TimeZone = timezoneUN1;
			userDetails1.LocationNmIds = userLocation1;
			userDetails1.Roles = userRole1;

			userCreationWrapper.createUser_Wrapper(userDetails1);
			
			// User 2 Creation
			UserParams userDetails2 = new UserParams();
			userDetails2.Username = userUN2;
			userDetails2.ClearPassword = GenericPassword;
			userDetails2.FirstName = userFN2;
			userDetails2.LastName = userLN2;
			userDetails2.Email = emailUN2;
			userDetails2.TimeZone = timezoneUN2;
			userDetails2.LocationNmIds = userLocation2;
			userDetails2.Roles = userRole2;

			userCreationWrapper.createUser_Wrapper(userDetails2);
			
			// ------------------------------------------------------------------------------------------------
			// WEB Application code starts here
	
			driver = launchbrowser();
			controlActions = new ControlActions(driver);
			controlActions.getUrl(applicationUrl);
			hp = new HomePage(driver);

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

			LoginPage lp = new LoginPage(driver);
			hp = lp.performLogin(adminUsername, adminPassword);
			if(hp.error) {
				TCGFailureMsg = "Could NOT login to application";
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}
			
			UserManagerPage ump = hp.clickUsersMenu();
			UsrDetailParams udp1 = new UsrDetailParams();
			udp1.Username = userUN1;
			udp1.Password = GenericPassword;
			udp1.FirstName = userFN1;
			udp1.LastName = userLN1;
			udp1.Email = emailUN1;
			udp1.Timezone = timezoneUN1;
			udp1.Locations = userLocation1;
			udp1.Roles = userRole1;
			boolean userCreation1 = ump.createInternalUser(udp1);
			if(!userCreation1) {
				TCGFailureMsg = "Could NOT create user - " + userUN1;
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}
			
			UsrDetailParams udp2 = new UsrDetailParams();
			udp2.Username = userUN2;
			udp2.Password = GenericPassword;
			udp2.FirstName = userFN2;
			udp2.LastName = userLN2;
			udp2.Email = emailUN2;
			udp2.Timezone = timezoneUN2;
			udp2.Locations = userLocation2;
			udp2.Roles = userRole2;
			boolean userCreation2 = ump.createInternalUser(udp2);
			if(!userCreation2) {
				TCGFailureMsg = "Could NOT create user - " + userUN2;
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

	@Test(description = "DPT || Verify single and multiple data extract for \"Users\".")
	public void TestCase_24283() {
		
		try {
			HomePage hp = new HomePage(driver);			
			DataImportExportPage diep = hp.clickDataImportExportMenu();
			
			boolean exportBtn1 = diep.selectExportTab();
			TestValidation.IsTrue(exportBtn1, 
								  "CLICKED on Export tab", 
								  "Failed to Click on Export tab");
			
			boolean openPopup1 = diep.openExtractDataPopUp();
			TestValidation.IsTrue(openPopup1, 
								  "OPENED Export Data popup", 
								  "Failed to Open Export Data popup");
			
			boolean setType1 = diep.selectExtractType(ExtractType.USERS);
			TestValidation.IsTrue(setType1, 
								  "Extract type SET to " + ExtractType.USERS, 
								  "Failed to Set Extract type to " + ExtractType.USERS);

			List<String> items = Arrays.asList(userUN1);
			WebElement element = controlActions.perform_GetElementByXPath(DataImportExportPageLocators.COLUMN_SETTINGS, 
					"COLUMN_NAME", ColumnNames.USER_NAME);
			boolean selectUser1 = diep.selectItemsInGrid(element,items);
			TestValidation.IsTrue(selectUser1, 
								  "SELECTED user " + userUN1, 
								  "Failed to Select user " + userUN1);
			
			boolean clickExtract1 = diep.clickDataExtract();
			TestValidation.IsTrue(clickExtract1, 
								  "CLICKED on Data Extract button on popup", 
								  "Failed to Click on Data Extract button on popup");
			
			boolean verifyExtractSts1 = diep.verifyFileExportStatus();
			TestValidation.IsTrue(verifyExtractSts1, 
								  "VERIFIED data extraction went successful", 
								  "Failed to Verify whether data extraction went successful or not");
			
			boolean exportBtn2 = diep.selectExportTab();
			TestValidation.IsTrue(exportBtn2, 
								  "CLICKED on Export tab", 
								  "Failed to Click on Export tab");
			
			boolean openPopup2 = diep.openExtractDataPopUp();
			TestValidation.IsTrue(openPopup2, 
								  "OPENED Export Data popup", 
								  "Failed to Open Export Data popup");
			
			boolean setType2 = diep.selectExtractType(ExtractType.USERS);
			TestValidation.IsTrue(setType2, 
								  "Extract type SET to " + ExtractType.USERS, 
								  "Failed to Set Extract type to " + ExtractType.USERS);
			
			boolean selectAllCustResource = diep.selectAllOnDataExtractPopup();
			TestValidation.IsTrue(selectAllCustResource, 
								  "SELECTED All resources under Extract type " + ExtractType.USERS, 
								  "Failed to Select all resources under Extract type " + ExtractType.USERS);
			
			boolean clickExtract2 = diep.clickDataExtract();
			TestValidation.IsTrue(clickExtract2, 
								  "CLICKED on Data Extract button on popup", 
								  "Failed to Click on Data Extract button on popup");
			
			boolean verifyExtractSts2 = diep.verifyFileExportStatus();
			TestValidation.IsTrue(verifyExtractSts2, 
								  "VERIFIED data extraction went successful", 
								  "Failed to Verify whether data extraction went successful or not");
			
		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				TestValidation.Fail(TCGFailureMsg);
			}
		}
	}
	
	@Test(description = "DPT || Verify the import functionality for \"Users\" having only "
			+ "existing data in the file and select \"Update only\".")
	public void TestCase_24289() {
		String updatedFirstName = "Updated " + userFN1;
		try {
			HomePage hp = new HomePage(driver);			
			DataImportExportPage diep = hp.clickDataImportExportMenu();
			
			boolean exportBtn = diep.selectExportTab();
			TestValidation.IsTrue(exportBtn, 
								  "CLICKED on Export tab", 
								  "Failed to Click on Export tab");
			
			boolean openPopup = diep.openExtractDataPopUp();
			TestValidation.IsTrue(openPopup, 
								  "OPENED Export Data popup", 
								  "Failed to Open Export Data popup");
			
			boolean setType = diep.selectExtractType(ExtractType.USERS);
			TestValidation.IsTrue(setType, 
								  "Extract type SET to " + ExtractType.USERS, 
								  "Failed to Set Extract type to " + ExtractType.USERS);
			
			List<String> items = Arrays.asList(userUN1);
			WebElement element = controlActions.perform_GetElementByXPath(DataImportExportPageLocators.COLUMN_SETTINGS, 
									"COLUMN_NAME", ColumnNames.USER_NAME);
			boolean selectUser1 = diep.selectItemsInGrid(element,items);
			TestValidation.IsTrue(selectUser1, 
								  "SELECTED user " + userUN1, 
								  "Failed to Select user " + userUN1);
			
			boolean clickExtract = diep.clickDataExtract();
			TestValidation.IsTrue(clickExtract, 
								  "CLICKED on Data Extract button on popup", 
								  "Failed to Click on Data Extract button on popup");
			
			boolean verifyExtractSts = diep.verifyFileExportStatus();
			TestValidation.IsTrue(verifyExtractSts, 
								  "VERIFIED data extraction went successful", 
								  "Failed to Verify whether data extraction went successful or not");
			
			String filePath = diep.downloadExportFileAndGetFilePath();
			TestValidation.IsTrue(filePath!=null, 
								  "The exported file HAS BEEN downloaded successfully", 
								  "Failed to Download the exported file");
			
			boolean importTab = diep.selectImportTab();
			TestValidation.IsTrue(importTab, 
								  "SWITCHED to DPT Import tab", 
								  "Failed to Switch to DPT Import Tab");
			
			boolean openImportPopup = diep.openNewImportDataPopUp();
			TestValidation.IsTrue(openImportPopup, 
								  "OPENED Import data popup", 
								  "Failed to Open Import data popup");
			
			boolean setImportType = diep.selectImportType(ImportType.INTERNAL_USER);
			TestValidation.IsTrue(setImportType, 
								  "Import type SET to " + ImportType.INTERNAL_USER, 
								  "Failed to Set Import type to " + ImportType.INTERNAL_USER);
			
			boolean setUpdateOnly = diep.selectUpdateOnlyOnImportPopup();
			TestValidation.IsTrue(setUpdateOnly, 
								  "SELECTED 'Update only' checkbox successfully", 
								  "Failed to Select 'Update only' checkbox");
			
			UserInfoDetails uid = new UserInfoDetails();
			uid.userName = userUN1;
			uid.timeZone = timezoneUN1;
			uid.phone = phoneNumber;
			uid.firstName = updatedFirstName;
			
			boolean updateExcel = diep.updateUsersExcelWithDetails(filePath, 0, Arrays.asList(uid));
			TestValidation.IsTrue(updateExcel, 
								  "UPDATED excel with required information of existing data", 
								  "Failed to Update excel sheet");
			
			boolean uploadExcel = diep.UploadFileImport(filePath);
			boolean clickUploadBtn = diep.clickUpload();
			TestValidation.IsTrue(uploadExcel && clickUploadBtn, 
								  "IMPORTED the updated excel with required information of existing data", 
								  "Failed to Import excel sheet");
			
			String fileName[] = CommonMethods.splitAndGetString(filePath, "\\\\");
			String excelDocName = fileName[fileName.length-1];
			boolean applyFilter = diep.openAndApplySettingsForColumn(ColumnNames.FILENAME, COLUMN_SETTING.FILTER, excelDocName);
			TestValidation.IsTrue(applyFilter, 
								  "For Filename column, APPLIED filter using text " + excelDocName, 
								  "Failed to apply filter on Filename with text " + excelDocName);
			
			boolean verifyImport = diep.verifyFileImportStatus();
			TestValidation.IsTrue(verifyImport, 
								  "VERIFIED Import of resource is done sucessfully", 
								  "Failed to Verify Import of resource is done or not");
			
			UserManagerPage ump = hp.clickUsersMenu();
			boolean searchUser = ump.searchUsrWithDetails(USERFIELDS.USERNAME, userUN1);
			TestValidation.IsTrue(searchUser, 
								  "SEARCHED user " + userUN1, 
								  "Failed to Search user " + userUN1);
			
			String usrFirstname = ump.getUsrDetails(USERFIELDS.FIRSTNAME);
			boolean compareFirstname = usrFirstname.contains(updatedFirstName);
			TestValidation.IsTrue(compareFirstname, 
								  "VERIFIED First name as " + updatedFirstName + " for user", 
								  "Failed to Verify First name as " + updatedFirstName + " for user");
			
			String usrTimezone = ump.getUsrDetails(USERFIELDS.TIMEZONE);
			boolean compareTimezone = usrTimezone.contains(TIMEZONE.USEASTERN);
			TestValidation.IsTrue(compareTimezone, 
								  "VERIFIED Timezone as " + TIMEZONE.USEASTERN + " for user", 
								  "Failed to Verify Timezone as " + TIMEZONE.USEASTERN + " for user");
			
		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				TestValidation.Fail(TCGFailureMsg);
			}
		}
	}
	
	@Test(description = "DPT || Verify the import functionality for \"Users\" having all new data in file.")
	public void TestCase_24292() {
		
		String userName = CommonMethods.dynamicText("NewDPT_UN");
		String firstName = CommonMethods.dynamicText("NewDPT_FN");
		String lastName = CommonMethods.dynamicText("NewDPT_LN");
		
		String filePath = System.getProperty("user.dir") + "\\test-data-files\\UploadDocuments\\DPT_REG_NewUsersImport.xlsx";

		try {
			HomePage hp = new HomePage(driver);			
			DataImportExportPage diep = hp.clickDataImportExportMenu();
			
			boolean importTab = diep.selectImportTab();
			TestValidation.IsTrue(importTab, 
								  "SWITCHED to DPT Import tab", 
								  "Failed to Switch to DPT Import Tab");
			
			boolean openImportPopup = diep.openNewImportDataPopUp();
			TestValidation.IsTrue(openImportPopup, 
								  "OPENED Import data popup", 
								  "Failed to Open Import data popup");
			
			boolean setImportType = diep.selectImportType(ImportType.INTERNAL_USER);
			TestValidation.IsTrue(setImportType, 
								  "Import type SET to " + ImportType.INTERNAL_USER, 
								  "Failed to Set Import type to " + ImportType.INTERNAL_USER);
			
			UserInfoDetails uid = new UserInfoDetails();
			uid.userName = userName;
			uid.timeZone = adminTimezone;
			uid.phone = phoneNumber;
			uid.firstName = firstName;
			uid.lastName = lastName;
			uid.location = "ALL";
			uid.role = ROLES_TYPES.SUPERADMIN;
			uid.email = emailUN1;
			uid.employeeId = "8990";
			uid.status = "Y";
			uid.newEntriesOnly = true;
			
			boolean updateExcel = diep.updateUsersExcelWithDetails(filePath, 0, Arrays.asList(uid));
			TestValidation.IsTrue(updateExcel, 
								  "UPDATED excel with required information of new data", 
								  "Failed to Update excel sheet");
			
			boolean uploadExcel = diep.UploadFileImport(filePath);
			boolean clickUploadBtn = diep.clickUpload();
			TestValidation.IsTrue(uploadExcel && clickUploadBtn, 
								  "IMPORTED the updated excel with required information of new data", 
								  "Failed to Import excel sheet");
			
			String fileName[] = CommonMethods.splitAndGetString(filePath, "\\\\");
			String excelDocName = fileName[fileName.length-1];
			boolean applyFilter = diep.openAndApplySettingsForColumn(ColumnNames.FILENAME, COLUMN_SETTING.FILTER, excelDocName);
			TestValidation.IsTrue(applyFilter, 
								  "For Filename column, APPLIED filter using text " + excelDocName, 
								  "Failed to apply filter on Filename with text " + excelDocName);
			
			boolean verifyImport = diep.verifyFileImportStatus();
			TestValidation.IsTrue(verifyImport, 
								  "VERIFIED Import of resource is done sucessfully", 
								  "Failed to Verify Import of resource is done or not");
			
			UserManagerPage ump = hp.clickUsersMenu();
			boolean searchUser = ump.searchUsrWithDetails(USERFIELDS.USERNAME, userName);
			TestValidation.IsTrue(searchUser, 
								  "SEARCHED user " + userName, 
								  "Failed to Search user " + userName);
			
			String usrTimezone = ump.getUsrDetails(USERFIELDS.TIMEZONE);
			boolean compareTimezone = usrTimezone.contains(TIMEZONE.REPUBLICOFINDIA);
			TestValidation.IsTrue(compareTimezone, 
								  "VERIFIED Timezone as " + TIMEZONE.USEASTERN + " for user", 
								  "Failed to Verify Timezone as " + TIMEZONE.USEASTERN + " for user");
			
			String usrFirstname = ump.getUsrDetails(USERFIELDS.FIRSTNAME);
			boolean compareFirstname = usrFirstname.contains(firstName);
			TestValidation.IsTrue(compareFirstname, 
								  "VERIFIED First name as " + firstName + " for user", 
								  "Failed to Verify First name as " + firstName + " for user");
			
			String usrLastname = ump.getUsrDetails(USERFIELDS.LASTNAME);
			boolean compareLastname = usrLastname.contains(lastName);
			TestValidation.IsTrue(compareLastname, 
								  "VERIFIED Last name as " + lastName + " for user", 
								  "Failed to Verify Last name as " + lastName + " for user");
			
			String usrEmail = ump.getUsrDetails(USERFIELDS.EMAIL);
			boolean compareEmail = usrEmail.contains(emailUN1);
			TestValidation.IsTrue(compareEmail, 
								  "VERIFIED Email as " + emailUN1 + " for user", 
								  "Failed to Verify Email as " + emailUN1 + " for user");
			
		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				TestValidation.Fail(TCGFailureMsg);
			}
		}
	}
	
	@Test(description = "DPT || Verify the import functionality for \"Users\" having new data, "
			+ "existing data in the file and select \"Update only\".")
	public void TestCase_24271() {
		
		// For existing users
		String updatedLastName = "Updated " + userLN2;
		String updatedEmailName = "AH" + emailUN2;
		String updatedTimezone =  TIMEZONE.GREENWICHMEANTIME;
		
		// For new user
		String userName = CommonMethods.dynamicText("NewDPT_UN");
		String firstName = CommonMethods.dynamicText("NewDPT_FN");
		String lastName = CommonMethods.dynamicText("NewDPT_LN");
		
		try {
			HomePage hp = new HomePage(driver);			
			DataImportExportPage diep = hp.clickDataImportExportMenu();
			
			boolean exportBtn = diep.selectExportTab();
			TestValidation.IsTrue(exportBtn, 
								  "CLICKED on Export tab", 
								  "Failed to Click on Export tab");
			
			boolean openPopup = diep.openExtractDataPopUp();
			TestValidation.IsTrue(openPopup, 
								  "OPENED Export Data popup", 
								  "Failed to Open Export Data popup");
			
			boolean setType = diep.selectExtractType(ExtractType.USERS);
			TestValidation.IsTrue(setType, 
								  "Extract type SET to " + ExtractType.USERS, 
								  "Failed to Set Extract type to " + ExtractType.USERS);
			
			List<String> items = Arrays.asList(userUN2);
			WebElement element = controlActions.perform_GetElementByXPath(DataImportExportPageLocators.COLUMN_SETTINGS, 
									"COLUMN_NAME", ColumnNames.USER_NAME);
			boolean selectUser1 = diep.selectItemsInGrid(element,items);
			TestValidation.IsTrue(selectUser1, 
								  "SELECTED user " + userUN2, 
								  "Failed to Select user " + userUN2);
			
			boolean clickExtract = diep.clickDataExtract();
			TestValidation.IsTrue(clickExtract, 
								  "CLICKED on Data Extract button on popup", 
								  "Failed to Click on Data Extract button on popup");
			
			boolean verifyExtractSts = diep.verifyFileExportStatus();
			TestValidation.IsTrue(verifyExtractSts, 
								  "VERIFIED data extraction went successful", 
								  "Failed to Verify whether data extraction went successful or not");
			
			String filePath = diep.downloadExportFileAndGetFilePath();
			TestValidation.IsTrue(filePath!=null, 
								  "The exported file HAS BEEN downloaded successfully", 
								  "Failed to Download the exported file");
			
			boolean importTab = diep.selectImportTab();
			TestValidation.IsTrue(importTab, 
								  "SWITCHED to DPT Import tab", 
								  "Failed to Switch to DPT Import Tab");
			
			boolean openImportPopup = diep.openNewImportDataPopUp();
			TestValidation.IsTrue(openImportPopup, 
								  "OPENED Import data popup", 
								  "Failed to Open Import data popup");
			
			boolean setImportType = diep.selectImportType(ImportType.INTERNAL_USER);
			TestValidation.IsTrue(setImportType, 
								  "Import type SET to " + ImportType.INTERNAL_USER, 
								  "Failed to Set Import type to " + ImportType.INTERNAL_USER);
			
			boolean setUpdateOnly = diep.selectUpdateOnlyOnImportPopup();
			TestValidation.IsTrue(setUpdateOnly, 
								  "SELECTED 'Update only' checkbox successfully", 
								  "Failed to Select 'Update only' checkbox");
			
			UserInfoDetails uid1 = new UserInfoDetails();
			uid1.userName = userUN2;
			uid1.email = updatedEmailName;
			uid1.phone = phoneNumber;
			uid1.lastName = updatedLastName;
			uid1.timeZone = updatedTimezone;
			
			UserInfoDetails uid2 = new UserInfoDetails();
			uid2.userName = userName;
			uid2.timeZone = adminTimezone;
			uid2.phone = phoneNumber;
			uid2.firstName = firstName;
			uid2.lastName = lastName;
			uid2.location = "ALL";
			uid2.role = ROLES_TYPES.SUPERADMIN;
			uid2.email = emailUN1;
			uid2.employeeId = "8990";
			uid2.status = "Y";
			uid2.newEntriesOnly = true;
			
			boolean updateExcel = diep.updateUsersExcelWithDetails(filePath, 1, Arrays.asList(uid1,uid2));
			TestValidation.IsTrue(updateExcel, 
								  "UPDATED excel with required information of existing and new data", 
								  "Failed to Update excel sheet");
			
			boolean uploadExcel = diep.UploadFileImport(filePath);
			boolean clickUploadBtn = diep.clickUpload();
			TestValidation.IsTrue(uploadExcel && clickUploadBtn, 
								  "IMPORTED the updated excel with required information of existing and new data", 
								  "Failed to Import excel sheet");
			
			String fileName[] = CommonMethods.splitAndGetString(filePath, "\\\\");
			String excelDocName = fileName[fileName.length-1];
			boolean applyFilter = diep.openAndApplySettingsForColumn(ColumnNames.FILENAME, COLUMN_SETTING.FILTER, excelDocName);
			TestValidation.IsTrue(applyFilter, 
								  "For Filename column, APPLIED filter using text " + excelDocName, 
								  "Failed to apply filter on Filename with text " + excelDocName);
			
			boolean verifyImport = diep.verifyFileImportStatus(IMPORT_STATUS.FAILURE);
			TestValidation.IsTrue(verifyImport, 
								  "VERIFIED Import of users has FAILED", 
								  "Failed to Verify Import of users is done or not");
			
			boolean openInspErrPopup = diep.openInspectErrorsPopupForLatestImport();
			TestValidation.IsTrue(openInspErrPopup, 
								  "OPENED Inspect Errors popup for the Import done", 
								  "Failed to Open Inspect Errors popup for the Import done");
			
			String expectedErrMsg = "User with UserName " + userName + " is Not available in the system to update";
			String actualErrMsg = diep.getInspctErrsDetails(ColumnNames.ERROR_MESSAGE);
			boolean compareErrMsg = actualErrMsg.contains(expectedErrMsg);
			TestValidation.IsTrue(compareErrMsg, 
								  "VERIFIED the error message for Import failure as " + expectedErrMsg, 
								  "Failed to Verify the error message for Import failure as " + expectedErrMsg);
			
			boolean closePopup1 = diep.closeImportInspectErrsPopup();
			TestValidation.IsTrue(closePopup1, 
								  "CLOSED Import Tab > Inspect Error popup", 
								  "Failed to Close Import Tab > Inspect Error popup");
			
			UserManagerPage ump = hp.clickUsersMenu();
			boolean searchUser1 = ump.searchUsrWithDetails(USERFIELDS.USERNAME, userUN2);
			TestValidation.IsTrue(searchUser1, 
								  "SEARCHED user " + userUN2, 
								  "Failed to Search user " + userUN2);
			
			String usrFirstname = ump.getUsrDetails(USERFIELDS.FIRSTNAME);
			boolean compareFirstname = usrFirstname.contains(userFN2);
			TestValidation.IsTrue(compareFirstname, 
								  "VERIFIED First name as " + userFN2 + " for user", 
								  "Failed to Verify First name as " + userFN2 + " for user");
			
			String usrTimezone = ump.getUsrDetails(USERFIELDS.TIMEZONE);
			boolean compareTimezone = usrTimezone.contains(timezoneUN2);
			TestValidation.IsTrue(compareTimezone, 
								  "VERIFIED Timezone as " + timezoneUN2 + " for user", 
								  "Failed to Verify Timezone as " + timezoneUN2 + " for user");
			
			String usrEmail = ump.getUsrDetails(USERFIELDS.EMAIL);
			boolean compareEmail = usrEmail.contains(emailUN2);
			TestValidation.IsTrue(compareEmail, 
								  "VERIFIED Email as " + emailUN2 + " for user", 
								  "Failed to Verify Email as " + emailUN2 + " for user");
			
			boolean searchUser2 = ump.searchUsrWithDetails(USERFIELDS.USERNAME, userName);
			TestValidation.IsTrue(searchUser2, 
								  "SEARCHED user " + userName, 
								  "Failed to Search user " + userName);
			
			boolean noRecordFound = ump.NoRecordsAvailableForUser.isDisplayed();
			TestValidation.IsTrue(noRecordFound, 
								  "VERIFIED No such user " + userName + " found since import failed", 
								  "Failed to Verify whether user " + userName + " is found or not");
			
		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				TestValidation.Fail(TCGFailureMsg);
			}
		}
	}
	
	@Test(description = "DPT || Verify import functionality for \"Users\" with new data "
			+ "and existing data without selecting \"Update Only\".")
	public void TestCase_24275() {
		
		// For existing users
		String updatedLastName = "Updated " + userLN1;
		String updatedEmailName = "AH" + emailUN1;
		String updatedTimezone =  TIMEZONE.GREENWICHMEANTIME;
		
		// For new user
		String userName = CommonMethods.dynamicText("NewDPT_UN");
		String firstName = CommonMethods.dynamicText("NewDPT_FN");
		String lastName = CommonMethods.dynamicText("NewDPT_LN");
		
		try {
			HomePage hp = new HomePage(driver);			
			DataImportExportPage diep = hp.clickDataImportExportMenu();
			
			boolean exportBtn = diep.selectExportTab();
			TestValidation.IsTrue(exportBtn, 
								  "CLICKED on Export tab", 
								  "Failed to Click on Export tab");
			
			boolean openPopup = diep.openExtractDataPopUp();
			TestValidation.IsTrue(openPopup, 
								  "OPENED Export Data popup", 
								  "Failed to Open Export Data popup");
			
			boolean setType = diep.selectExtractType(ExtractType.USERS);
			TestValidation.IsTrue(setType, 
								  "Extract type SET to " + ExtractType.USERS, 
								  "Failed to Set Extract type to " + ExtractType.USERS);
			
			List<String> items = Arrays.asList(userUN1);
			WebElement element = controlActions.perform_GetElementByXPath(DataImportExportPageLocators.COLUMN_SETTINGS, 
									"COLUMN_NAME", ColumnNames.USER_NAME);
			boolean selectUser1 = diep.selectItemsInGrid(element,items);
			TestValidation.IsTrue(selectUser1, 
								  "SELECTED user " + userUN1, 
								  "Failed to Select user " + userUN1);
			
			boolean clickExtract = diep.clickDataExtract();
			TestValidation.IsTrue(clickExtract, 
								  "CLICKED on Data Extract button on popup", 
								  "Failed to Click on Data Extract button on popup");
			
			boolean verifyExtractSts = diep.verifyFileExportStatus();
			TestValidation.IsTrue(verifyExtractSts, 
								  "VERIFIED data extraction went successful", 
								  "Failed to Verify whether data extraction went successful or not");
			
			String filePath = diep.downloadExportFileAndGetFilePath();
			TestValidation.IsTrue(filePath!=null, 
								  "The exported file HAS BEEN downloaded successfully", 
								  "Failed to Download the exported file");
			
			boolean importTab = diep.selectImportTab();
			TestValidation.IsTrue(importTab, 
								  "SWITCHED to DPT Import tab", 
								  "Failed to Switch to DPT Import Tab");
			
			boolean openImportPopup = diep.openNewImportDataPopUp();
			TestValidation.IsTrue(openImportPopup, 
								  "OPENED Import data popup", 
								  "Failed to Open Import data popup");
			
			boolean setImportType = diep.selectImportType(ImportType.INTERNAL_USER);
			TestValidation.IsTrue(setImportType, 
								  "Import type SET to " + ImportType.INTERNAL_USER, 
								  "Failed to Set Import type to " + ImportType.INTERNAL_USER);
			
			UserInfoDetails uid1 = new UserInfoDetails();
			uid1.userName = userUN1;
			uid1.email = updatedEmailName;
			uid1.phone = phoneNumber;
			uid1.lastName = updatedLastName;
			uid1.timeZone = updatedTimezone;
			
			UserInfoDetails uid2 = new UserInfoDetails();
			uid2.userName = userName;
			uid2.timeZone = adminTimezone;
			uid2.phone = phoneNumber;
			uid2.firstName = firstName;
			uid2.lastName = lastName;
			uid2.location = "ALL";
			uid2.role = ROLES_TYPES.SUPERADMIN;
			uid2.email = emailUN1;
			uid2.employeeId = "8990";
			uid2.status = "Y";
			uid2.newEntriesOnly = true;
			
			boolean updateExcel = diep.updateUsersExcelWithDetails(filePath, 1, Arrays.asList(uid1, uid2));
			TestValidation.IsTrue(updateExcel, 
								  "UPDATED excel with required information of existing and new data", 
								  "Failed to Update excel sheet");
			
			boolean uploadExcel = diep.UploadFileImport(filePath);
			boolean clickUploadBtn = diep.clickUpload();
			TestValidation.IsTrue(uploadExcel && clickUploadBtn, 
								  "IMPORTED the updated excel with required information of existing and new data", 
								  "Failed to Import excel sheet");
			
			String fileName[] = CommonMethods.splitAndGetString(filePath, "\\\\");
			String excelDocName = fileName[fileName.length-1];
			boolean applyFilter = diep.openAndApplySettingsForColumn(ColumnNames.FILENAME, COLUMN_SETTING.FILTER, excelDocName);
			TestValidation.IsTrue(applyFilter, 
								  "For Filename column, APPLIED filter using text " + excelDocName, 
								  "Failed to apply filter on Filename with text " + excelDocName);
			
			boolean verifyImport = diep.verifyFileImportStatus(IMPORT_STATUS.FAILURE);
			TestValidation.IsTrue(verifyImport, 
								  "VERIFIED Import of users has FAILED", 
								  "Failed to Verify Import of users is done or not");
			
			boolean openInspErrPopup = diep.openInspectErrorsPopupForLatestImport();
			TestValidation.IsTrue(openInspErrPopup, 
								  "OPENED Inspect Errors popup for the Import done", 
								  "Failed to Open Inspect Errors popup for the Import done");
			
			String expectedErrMsg = "User with UserName " + userUN1 + " already exists in the system";
			String actualErrMsg = diep.getInspctErrsDetails(ColumnNames.ERROR_MESSAGE);
			boolean compareErrMsg = actualErrMsg.contains(expectedErrMsg);
			TestValidation.IsTrue(compareErrMsg, 
								  "VERIFIED the error message for Import failure as " + expectedErrMsg, 
								  "Failed to Verify the error message for Import failure as " + expectedErrMsg);
			
			boolean closePopup1 = diep.closeImportInspectErrsPopup();
			TestValidation.IsTrue(closePopup1, 
								  "CLOSED Import Tab > Inspect Error popup", 
								  "Failed to Close Import Tab > Inspect Error popup");
			
			UserManagerPage ump = hp.clickUsersMenu();
			boolean searchUser1 = ump.searchUsrWithDetails(USERFIELDS.USERNAME, userUN1);
			TestValidation.IsTrue(searchUser1, 
								  "SEARCHED user " + userUN1, 
								  "Failed to Search user " + userUN1);
			
			String usrTimezone = ump.getUsrDetails(USERFIELDS.TIMEZONE);
			boolean compareTimezone = usrTimezone.contains(timezoneUN1);
			TestValidation.IsTrue(compareTimezone, 
								  "VERIFIED Timezone as " + timezoneUN1 + " for user", 
								  "Failed to Verify Timezone as " + timezoneUN1 + " for user");
			
			String usrEmail = ump.getUsrDetails(USERFIELDS.EMAIL);
			boolean compareEmail = usrEmail.contains(emailUN1);
			TestValidation.IsTrue(compareEmail, 
								  "VERIFIED Email as " + emailUN1 + " for user", 
								  "Failed to Verify Email as " + emailUN1 + " for user");
			
			boolean searchUser2 = ump.searchUsrWithDetails(USERFIELDS.USERNAME, userName);
			TestValidation.IsTrue(searchUser2, 
								  "SEARCHED user " + userName, 
								  "Failed to Search user " + userName);
			
			boolean noRecordFound = ump.NoRecordsAvailableForUser.isDisplayed();
			TestValidation.IsTrue(noRecordFound, 
								  "VERIFIED No such user " + userName + " found since import failed", 
								  "Failed to Verify whether user " + userName + " is found or not");
			
		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				TestValidation.Fail(TCGFailureMsg);
			}
		}
	}
	
	@Test(description = "DPT || Verify download,inspect error and reprocess of the failed files for \"Users\".")
	public void TestCase_24284() {
		try {
			HomePage hp = new HomePage(driver);			
			DataImportExportPage diep = hp.clickDataImportExportMenu();
			
			boolean exportBtn = diep.selectExportTab();
			TestValidation.IsTrue(exportBtn, 
								  "CLICKED on Export tab", 
								  "Failed to Click on Export tab");
			
			boolean openPopup = diep.openExtractDataPopUp();
			TestValidation.IsTrue(openPopup, 
								  "OPENED Export Data popup", 
								  "Failed to Open Export Data popup");
			
			boolean setType = diep.selectExtractType(ExtractType.USERS);
			TestValidation.IsTrue(setType, 
								  "Extract type SET to " + ExtractType.USERS, 
								  "Failed to Set Extract type to " + ExtractType.USERS);
			
			List<String> items = Arrays.asList(userUN2);
			WebElement element = controlActions.perform_GetElementByXPath(DataImportExportPageLocators.COLUMN_SETTINGS, 
									"COLUMN_NAME", ColumnNames.USER_NAME);
			boolean selectUser1 = diep.selectItemsInGrid(element,items);
			TestValidation.IsTrue(selectUser1, 
								  "SELECTED user " + userUN2, 
								  "Failed to Select user " + userUN2);
			
			boolean clickExtract = diep.clickDataExtract();
			TestValidation.IsTrue(clickExtract, 
								  "CLICKED on Data Extract button on popup", 
								  "Failed to Click on Data Extract button on popup");
			
			boolean verifyExtractSts = diep.verifyFileExportStatus();
			TestValidation.IsTrue(verifyExtractSts, 
								  "VERIFIED data extraction went successful", 
								  "Failed to Verify whether data extraction went successful or not");
			
			String filePath = diep.downloadExportFileAndGetFilePath();
			TestValidation.IsTrue(filePath!=null, 
								  "The exported file HAS BEEN downloaded successfully", 
								  "Failed to Download the exported file");
			
			boolean importTab = diep.selectImportTab();
			TestValidation.IsTrue(importTab, 
								  "SWITCHED to DPT Import tab", 
								  "Failed to Switch to DPT Import Tab");
			
			boolean openImportPopup = diep.openNewImportDataPopUp();
			TestValidation.IsTrue(openImportPopup, 
								  "OPENED Import data popup", 
								  "Failed to Open Import data popup");
			
			boolean setImportType = diep.selectImportType(ImportType.INTERNAL_USER);
			TestValidation.IsTrue(setImportType, 
								  "Import type SET to " + ImportType.INTERNAL_USER, 
								  "Failed to Set Import type to " + ImportType.INTERNAL_USER);
			
			boolean setUpdateOnly = diep.selectUpdateOnlyOnImportPopup();
			TestValidation.IsTrue(setUpdateOnly, 
								  "SELECTED 'Update only' checkbox successfully", 
								  "Failed to Select 'Update only' checkbox");
			
			String invalidPhoneNumber = "abc";
			UserInfoDetails uid = new UserInfoDetails();
			uid.userName = userUN2;
			uid.phone = invalidPhoneNumber;
			
			boolean updateExcel = diep.updateUsersExcelWithDetails(filePath, 0, Arrays.asList(uid));
			TestValidation.IsTrue(updateExcel, 
								  "UPDATED excel with required information of existing data", 
								  "Failed to Update excel sheet");	
			
			boolean uploadExcel = diep.UploadFileImport(filePath);
			boolean clickUploadBtn = diep.clickUpload();
			TestValidation.IsTrue(uploadExcel && clickUploadBtn, 
								  "IMPORTED the updated excel with required information of new and existing data", 
								  "Failed to Import excel sheet");
			
			String fileName[] = CommonMethods.splitAndGetString(filePath, "\\\\");
			String excelDocName = fileName[fileName.length-1];
			boolean applyFilter = diep.openAndApplySettingsForColumn(ColumnNames.FILENAME, COLUMN_SETTING.FILTER, excelDocName);
			TestValidation.IsTrue(applyFilter, 
								  "For Filename column, APPLIED filter using text " + excelDocName, 
								  "Failed to apply filter on Filename with text " + excelDocName);
			
			boolean verifyImport = diep.verifyFileImportStatus(IMPORT_STATUS.FAILURE);
			TestValidation.IsTrue(verifyImport, 
								  "VERIFIED Import of resource has FAILED", 
								  "Failed to Verify Import of resource is done or not");
			
			boolean openInspErrPopup1 = diep.openInspectErrorsPopupForLatestImport();
			TestValidation.IsTrue(openInspErrPopup1, 
								  "OPENED Inspect Errors popup for the Import done", 
								  "Failed to Open Inspect Errors popup for the Import done");
			
			String expectedErrMsg = "Invalid Phone";
			String actualErrMsg1 = diep.getInspctErrsDetails(ColumnNames.ERROR_MESSAGE);
			boolean compareErrMsg1 = expectedErrMsg.equalsIgnoreCase(actualErrMsg1);
			TestValidation.IsTrue(compareErrMsg1, 
								  "VERIFIED the error message for Import failure as " + expectedErrMsg, 
								  "Failed to Verify the error message for Import failure as " + expectedErrMsg);
			
			boolean closePopup1 = diep.closeImportInspectErrsPopup();
			TestValidation.IsTrue(closePopup1, 
								  "CLOSED Import Tab > Inspect Error popup", 
								  "Failed to Close Import Tab > Inspect Error popup");
			
			boolean reprocessFile = diep.reprocessFailedImportOnce();
			TestValidation.IsFalse(reprocessFile, 
								  "As expected, the reprocess of file FAILED", 
								  "Failed to verify if reprocess of file failed or not");
			
			boolean openInspErrPopup2 = diep.openInspectErrorsPopupForLatestImport();
			TestValidation.IsTrue(openInspErrPopup2, 
								  "OPENED Inspect Errors popup for the Import done", 
								  "Failed to Open Inspect Errors popup for the Import done");
			
			String actualErrMsg2 = diep.getInspctErrsDetails(ColumnNames.ERROR_MESSAGE);
			boolean compareErrMsg2 = expectedErrMsg.equalsIgnoreCase(actualErrMsg2);
			TestValidation.IsTrue(compareErrMsg2, 
								  "VERIFIED the error message for Import failure as " + expectedErrMsg, 
								  "Failed to Verify the error message for Import failure as " + expectedErrMsg);
			
			boolean closePopup2 = diep.closeImportInspectErrsPopup();
			TestValidation.IsTrue(closePopup2, 
								  "CLOSED Import Tab > Inspect Error popup", 
								  "Failed to Close Import Tab > Inspect Error popup");
			
			boolean downloadFile = diep.downloadDataFromLatestImport();
			TestValidation.IsTrue(downloadFile, 
								  "DOWNLOADED excel sheet used for Import", 
								  "Failed to Download excel sheet used for Import");
			
			String filename[] = CommonMethods.splitAndGetString(excelDocName, ".xlsx");
			String verifyExcelName = filename[0] + " (1).xlsx";
			boolean verifyFileDownloaded = controlActions.verifyFileDownloaded(downloadPath, verifyExcelName);
			TestValidation.IsTrue(verifyFileDownloaded, 
								  "Verified " + verifyExcelName + " file is downloaded successfully ", 
								  "Failed to verify that document " + verifyExcelName + "  is downloadeded"); 

		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				TestValidation.Fail(TCGFailureMsg);
			}
		}
	}
	
	@Test(description = "DPT || Verify download of Users data.")
	public void TestCase_38423() {
		try {
			HomePage hp = new HomePage(driver);			
			DataImportExportPage diep = hp.clickDataImportExportMenu();
			
			boolean exportBtn = diep.selectExportTab();
			TestValidation.IsTrue(exportBtn, 
								  "CLICKED on Export tab", 
								  "Failed to Click on Export tab");
			
			boolean openPopup = diep.openExtractDataPopUp();
			TestValidation.IsTrue(openPopup, 
								  "OPENED Export Data popup", 
								  "Failed to Open Export Data popup");
			
			boolean setType = diep.selectExtractType(ExtractType.USERS);
			TestValidation.IsTrue(setType, 
								  "Extract type SET to " + ExtractType.USERS, 
								  "Failed to Set Extract type to " + ExtractType.USERS);
			
			List<String> items = Arrays.asList(adminUsername);
			WebElement element = controlActions.perform_GetElementByXPath(DataImportExportPageLocators.COLUMN_SETTINGS, 
					"COLUMN_NAME", ColumnNames.USER_NAME);
			boolean selectEquipResource = diep.selectItemsInGrid(element,items);
			TestValidation.IsTrue(selectEquipResource, 
								  "SELECTED user " + adminUsername, 
								  "Failed to Select user " + adminUsername);
			
			boolean clickExtract = diep.clickDataExtract();
			TestValidation.IsTrue(clickExtract, 
								  "CLICKED on Data Extract button on popup", 
								  "Failed to Click on Data Extract button on popup");
			
			boolean verifyExtractSts = diep.verifyFileExportStatus();
			TestValidation.IsTrue(verifyExtractSts, 
								  "VERIFIED data extraction went successful", 
								  "Failed to Verify whether data extraction went successful or not");
			
			String filePath = diep.downloadExportFileAndGetFilePath();
			TestValidation.IsTrue(filePath!=null, 
								  "The exported file HAS BEEN downloaded successfully", 
								  "Failed to Download the exported file");
			
			String adminFirstName = "Auto";
			String adminLastName = "User";
			
			UserInfoDetails uid = new UserInfoDetails();
			uid.userName = adminUsername;
			uid.timeZone = adminTimezone;
			uid.firstName = adminFirstName;
			uid.lastName = adminLastName;
			uid.location = "ALL";
			uid.role = ROLES_TYPES.SUPERADMIN;
			uid.status = "Y";
			
			boolean verifyDataInExportedExcel = diep.verifyUsersExcelDetails(filePath, Arrays.asList(uid));
			TestValidation.IsTrue(verifyDataInExportedExcel, 
								  "VERIFIED User " + adminUsername + " details from downloaded sheet", 
								  "Failed to Verify User " + adminUsername + " details from downloaded sheet");
			
		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				TestValidation.Fail(TCGFailureMsg);
			}
		}
	}
	
	@Test(description = "DPT || Export - Verify functionality of Universal search for Users.")
	public void TestCase_38534() {
		try {
			HomePage hp = new HomePage(driver);			
			DataImportExportPage diep = hp.clickDataImportExportMenu();
			
			boolean exportBtn = diep.selectExportTab();
			TestValidation.IsTrue(exportBtn, 
								  "CLICKED on Export tab", 
								  "Failed to Click on Export tab");
			
			boolean openPopup = diep.openExtractDataPopUp();
			TestValidation.IsTrue(openPopup, 
								  "OPENED Export Data popup", 
								  "Failed to Open Export Data popup");
			
			boolean setType = diep.selectExtractType(ExtractType.USERS);
			TestValidation.IsTrue(setType, 
								  "Extract type SET to " + ExtractType.USERS, 
								  "Failed to Set Extract type to " + ExtractType.USERS);
			
			boolean searchTxt = diep.searchExtractItem(userUN1);
			TestValidation.IsTrue(searchTxt, 
								  "SEARCHED for text " + userUN1, 
								  "Failed to Search for text " + userUN1);
			
			int rowCount = diep.DataExtractPopupRowsTbl.size();
			TestValidation.IsTrue(rowCount==1, 
								  "VERIFIED that a Row in Data Extract popup table IS DISPLAYED", 
								  "Failed since " + rowCount + " rows were found in Data Extract popup table");
			
			WebElement ExtractItemCheckbox = controlActions.perform_GetElementByXPath(DataImportExportPageLocators.EXPORT_ITEM_CHECKBOX, 
												"ITEMNAME", userUN1);
			TestValidation.IsTrue(ExtractItemCheckbox!=null, 
								  "VERIFIED element is present for " + userUN1, 
								  "Failed to Verify whether element " + userUN1 + " is present or not");
			
		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				TestValidation.Fail(TCGFailureMsg);
			}
		}
	}
	
	@Test(description = "DPT || Export>Data Extract- Verify the Filter functionality for user.")
	public void TestCase_38564() {
		try {
			HomePage hp = new HomePage(driver);			
			DataImportExportPage diep = hp.clickDataImportExportMenu();
			
			boolean exportBtn = diep.selectExportTab();
			TestValidation.IsTrue(exportBtn, 
								  "CLICKED on Export tab", 
								  "Failed to Click on Export tab");
			
			boolean openPopup = diep.openExtractDataPopUp();
			TestValidation.IsTrue(openPopup, 
								  "OPENED Export Data popup", 
								  "Failed to Open Export Data popup");
			
			boolean setType = diep.selectExtractType(ExtractType.USERS);
			TestValidation.IsTrue(setType, 
								  "Extract type SET to " + ExtractType.USERS, 
								  "Failed to Set Extract type to " + ExtractType.USERS);
			
			boolean applyFilter = diep.openAndApplySettingsForColumn(ColumnNames.USER_NAME, COLUMN_SETTING.FILTER, userUN2);
			TestValidation.IsTrue(applyFilter, 
								  "For User Name column, APPLIED filter using text " + userUN2, 
								  "Failed to apply filter on User Name with text " + userUN2);
			
			int rowCount = diep.DataExtractPopupRowsTbl.size();
			TestValidation.IsTrue(rowCount==1, 
								  "VERIFIED that a Row in Data Extract popup table IS DISPLAYED", 
								  "Failed since " + rowCount + " rows were found in Data Extract popup table");
			
			WebElement ExtractItemCheckbox = controlActions.perform_GetElementByXPath(DataImportExportPageLocators.EXPORT_ITEM_CHECKBOX, 
												"ITEMNAME", userUN2);
			TestValidation.IsTrue(ExtractItemCheckbox!=null, 
								  "VERIFIED element is present for " + userUN2, 
								  "Failed to Verify whether element " + userUN2 + " is present or not");
			
		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				TestValidation.Fail(TCGFailureMsg);
			}
		}
	}
	
	@AfterClass(alwaysRun = true)
	public void closeBrowser() throws InterruptedException {
		driver.close();
	}
}
