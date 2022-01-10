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

import com.project.safetychain.api.models.support.Support.RESOURCE_TYPES;
import com.project.safetychain.api.models.support.TCG_FormCreation_FTUFlows;
import com.project.safetychain.api.models.support.TCG_OtherFormCreation_Wrapper.FormCreation_FTUFlows;
import com.project.safetychain.api.models.support.TCG_FormCreation_Wrapper;
import com.project.safetychain.webapp.pageobjects.*;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.COLUMNHEADER;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.COLUMNSETTING;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.FSQARESOURCE;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.FSQATAB;
import com.project.safetychain.webapp.pageobjects.FormsManagerPage.FTU_IMPORT_OPTNS;
import com.project.safetychain.webapp.pageobjects.ManageResourcePage.RESOURCETYPES;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.utilities.CommonMethods;
import com.project.utilities.Constants;
import com.project.utilities.ControlActions;
import com.project.utilities.DateTime;
import com.project.utilities.FileOperations;

public class TCG_SMK_FormManager_FTUFlows extends TestBase {
	
	DateTime dt = new DateTime();

	ControlActions controlActions;
	FSQABrowserPage fbp;
	ManageResourcePage mrp;
	FormDesignerPage fdp;
	HomePage hp;
	LoginPage lp;
	FormsManagerPage fmp;

	public static String locationCategoryValue;
	public static String locationInstanceValue1_53725;
	public static String locationInstanceValue2_53725;
	public static String customersCategoryValue;
	public static String customersInstanceValue1_53725;
	public static String customersInstanceValue2_53725;
	public static String itemsCategoryValue;
	public static String itemsInstanceValue_53727;
	public static String customersCategoryValue_53728;
	public static String customersInstanceValue_53728;
	public static String target_customersCategoryValue_53728;
	public static String target_locationCategoryValue;
	public static String target_locationInstanceValue;
	public static String displayName = null;
	public static String usrTimezone = null;
	public static String timezoneCode = null;
	public static String sourceEnv = null;
	public static String sourceEnvHist = null;
	public static List<String> largeFormNames = null;
	public static List<String> smallFormDetails = null;
	public static List<String> formName_53728 = null;
	public static String currentDate;
	public static String formName1 = "FTU Form having Section";
	public static String formName2 = "FTU Number Field Form - 1";
	public static String formName3 = "FTU Number Field Form - 2";
	public static String formName4 = "HUT - Prepared Foods Core Form - Metal Detection";
	public static String formName5 = "Poultry - Piece Raw Dimensions";
	public static String formName6 = "Prepared Foods Core Form - Piece Weight";
	public static String f1 = CommonMethods.dynamicText(formName1 + " ");
	public static String f2 = CommonMethods.dynamicText(formName2 + " ");
	public static String f3 = CommonMethods.dynamicText(formName3 + " ");
	public static String f4 = CommonMethods.dynamicText(formName4 + " ");
	public static String f5 = CommonMethods.dynamicText(formName5 + " ");
	public static String f6 = CommonMethods.dynamicText(formName6 + " ");


	@BeforeClass(alwaysRun = true)
	public void groupInit() throws InterruptedException {
		
		locationCategoryValue = "AutoFTULocation";
		locationInstanceValue1_53725 = "AutoFTULocationInst1";
		locationInstanceValue2_53725 = "AutoFTULocationInst2";

		customersCategoryValue = CommonMethods.dynamicText("API FTU CCat");
		customersInstanceValue1_53725 = CommonMethods.dynamicText("API FTU CInst1");
		customersInstanceValue2_53725 = CommonMethods.dynamicText("API FTU CInst2");

		itemsCategoryValue = CommonMethods.dynamicText("API FTU ICat");
		itemsInstanceValue_53727 = CommonMethods.dynamicText("API FTU IInst");
		
		customersCategoryValue_53728 = CommonMethods.dynamicText("API FTU S_CCat");
		customersInstanceValue_53728 = CommonMethods.dynamicText("API FTU S_CInst");
		
		target_customersCategoryValue_53728 = CommonMethods.dynamicText("API FTU T_CCat");
		target_locationCategoryValue = locationName;
		target_locationInstanceValue = locationName;
		
		// Update the text file names that contains JSON requests
		String excelPath1 = Constants.large_form_api_file_path + "CreateForm\\";
		HashMap<String, String> names = new HashMap<String, String>();
		names.put(formName1, f1);
		names.put(formName2, f2);
		names.put(formName3, f3);
		names.put(formName4, f4);
		names.put(formName5, f5);
		names.put(formName6, f6);

		FileOperations fo = new FileOperations();
		fo.renameFilesInTheFolder(excelPath1, names, ".txt");
		
		driver = launchbrowser();
		fmp = new FormsManagerPage(driver);
		
		// Update the form names in the excel file as per the renamed file names (that contains JSON requests) 
		String excelPath2 = Constants.large_form_api_file_path + "FormData.xlsx";

		HashMap<String, String> updateExcel2 = new HashMap<String, String>();
		updateExcel2.put("537251", f4);
		updateExcel2.put("537252", f5);
		updateExcel2.put("537253", f6);
		boolean updateLargeFormDetails = fmp.updateFormNameForSheet(excelPath2, "LargeFormDetails", updateExcel2);
		if(!updateLargeFormDetails) {
			TCGFailureMsg = "Could NOT update form names for Sheet - LargeFormDetails";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
		
		HashMap<String, String> updateExcel1 = new HashMap<String, String>();
		updateExcel1.put("53727", f2);
		updateExcel1.put("53729", f3);
		boolean updateSmallFormDetails = fmp.updateFormNameForSheet(excelPath2, "SmallFormDetails", updateExcel1);
		if(!updateSmallFormDetails) {
			TCGFailureMsg = "Could NOT update form names for Sheet - SmallFormDetails";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
		
		HashMap<String, String> updateExcel3 = new HashMap<String, String>();
		updateExcel3.put("53728", f1);
		boolean updateFormDetails_53728 = fmp.updateFormNameForSheet(excelPath2, "FormDetails_53728", updateExcel3);
		if(!updateFormDetails_53728) {
			TCGFailureMsg = "Could NOT update form names for Sheet - FormDetails_53728";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
		
		usrTimezone = adminTimezone;
		timezoneCode = dt.getTimezone(usrTimezone);
		
		// ------------------------------------------------------------------------------------------------
		// API Implementation FOR SOURCE
		// Import Large forms
		
		FormCreation_FTUFlows fcff1 = new FormCreation_FTUFlows();
		fcff1.excelSheetName = "LargeFormDetails";
		fcff1.resourceType = RESOURCE_TYPES.CUSTOMERS;
		fcff1.locCatName = locationCategoryValue;
		fcff1.locInstName1 = locationInstanceValue1_53725;
		fcff1.locInstName2 = locationInstanceValue2_53725;
	    fcff1.resCatName = customersCategoryValue;
		fcff1.resInstName1 = customersInstanceValue1_53725;
		fcff1.resInstName2 = customersInstanceValue2_53725;
		
		TCG_FormCreation_FTUFlows formCreationFTUFlows1 = new TCG_FormCreation_FTUFlows(fcff1);
		try {
			largeFormNames = formCreationFTUFlows1.getFormNamesFromExcel();

			formCreationFTUFlows1.SMK_FTUFlows_Create3Forms();
			
		} catch (Exception e) {
			TCGFailureMsg = "Unable to create " + largeFormNames + " forms on source env";
			TestValidation.Fail(TCGFailureMsg + " : " + e.getMessage());
		}
		
		// ------------------------------------------------------------------------------------------------
		// Import forms for 53727 and 53729
		
		FormCreation_FTUFlows fcff2 = new FormCreation_FTUFlows();
		fcff2.excelSheetName = "SmallFormDetails";
		fcff2.resourceType = RESOURCE_TYPES.ITEMS;
		fcff2.locCatName = locationCategoryValue;
		fcff2.locInstName1 = locationInstanceValue1_53725;
		fcff2.locInstName2 = locationInstanceValue2_53725;
	    fcff2.resCatName = itemsCategoryValue;
		fcff2.resInstName1 = itemsInstanceValue_53727;
		
		TCG_FormCreation_FTUFlows formCreationFTUFlows2 = new TCG_FormCreation_FTUFlows(fcff2);
		try {
			smallFormDetails = formCreationFTUFlows2.getFormNamesFromExcel();

			formCreationFTUFlows2.SMK_FTUFlows_Create3Forms();
			
		} catch (Exception e) {
			TCGFailureMsg = "Unable to create " + smallFormDetails + " forms on source env";
			TestValidation.Fail(TCGFailureMsg + " : " + e.getMessage());
		}
		
		// ------------------------------------------------------------------------------------------------
		// Import forms for 53728
		
		FormCreation_FTUFlows fcff3 = new FormCreation_FTUFlows();
		fcff3.excelSheetName = "FormDetails_53728";
		fcff3.resourceType = RESOURCE_TYPES.CUSTOMERS;
		fcff3.locCatName = locationCategoryValue;
		fcff3.locInstName1 = locationInstanceValue1_53725;
		fcff3.locInstName2 = locationInstanceValue2_53725;
		// API FTU S_CCat > API FTU S_CInst (Same name as Target resource)
	    fcff3.resCatName = customersCategoryValue_53728;
		fcff3.resInstName1 = customersInstanceValue_53728;
		
		TCG_FormCreation_FTUFlows formCreationFTUFlows3 = new TCG_FormCreation_FTUFlows(fcff3);
		try {
			formName_53728 = formCreationFTUFlows3.getFormNamesFromExcel();

			formCreationFTUFlows3.SMK_FTUFlows_Create3Forms();
			
		} catch (Exception e) {
			TCGFailureMsg = "Unable to create " + formName_53728 + " forms on source env";
			TestValidation.Fail(TCGFailureMsg + " : " + e.getMessage());
		}
		
		// ------------------------------------------------------------------------------------------------
		// API Implementation FOR TARGET for TC 53728
		TCG_FormCreation_Wrapper formCreationWrapper = new TCG_FormCreation_Wrapper();

		// ------------------------------------------------------------------------------------------------
		// API - Location & Resource Creation and Linking
		List<String> userList = Arrays.asList(adminUsername);

		HashMap<String, List<String>> LocationMap = new HashMap<String, List<String>>();
		LocationMap.put(target_locationCategoryValue, Arrays.asList(target_locationInstanceValue));

		HashMap<String, List<String>> resourceCatMap = new HashMap<String, List<String>>();
		// API FTU T_CCat > API FTU S_CInst (Same name as Source)
		resourceCatMap.put(target_customersCategoryValue_53728, Arrays.asList(customersInstanceValue_53728));

		HashMap<String, String> resourceMap = formCreationWrapper.Resource_Location_CreationWrapper(resourceCatMap, 
				LocationMap, true, userList, RESOURCE_TYPES.CUSTOMERS);
		
		if(resourceMap.isEmpty()) {
			TCGFailureMsg = "Unable to create " + customersInstanceValue_53728 + " resource on target env";
			TestValidation.Fail(TCGFailureMsg);
		}
		
		// ------------------------------------------------------------------------------------------------
		// WEB Application code starts here

		controlActions = new ControlActions(driver);
		controlActions.getUrl(applicationUrl);
		
		hp = new HomePage(driver);
		lp = new LoginPage(driver);
		
		sourceEnv = fmp.decideSourceEnvToBeUsedForFTU();
		if(sourceEnv == null) {
			TCGFailureMsg = "Could NOT set Source environment for FTU";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
		
		sourceEnvHist = fmp.getSourceEnvHistToBeUsedForFTU();
		if(sourceEnvHist == null) {
			TCGFailureMsg = "Could NOT get Source environment for FTU History Details";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
		
		hp = lp.performLogin(adminUsername, adminPassword);
		if(hp.error) {
			TCGFailureMsg = "Could NOT login to application";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
		
		displayName = hp.getLoggedInUserDetails();
		if(displayName == ""){
			TCGFailureMsg = "Could NOT get display name for user - " + adminUsername;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
	}


	@Test(description="Verify that form import should take place if user select  all 3 options "
			+ "displayed on import forms popup while importing")
	public void TestCase_53725() throws Exception{
		try {

			fmp = hp.clickFormsManagerMenu();
			TestValidation.Equals(fmp.error, 
								  false, 
								  "CLICKED on Forms menu", 
								  "Could NOT click on Forms menu");
			
			boolean importTabClicked = fmp.clickImportTab();
			TestValidation.IsTrue(importTabClicked, 
								  "NAVIGATED to Forms Manager > Import tab", 
								  "Failed to Navigate to Forms Manager > Import tab");
			
			boolean sourceEnvSet = fmp.setSourceEnvForFTU(sourceEnv);
			TestValidation.IsTrue(sourceEnvSet, 
								  "Source Environment for FTU is set to " + sourceEnv, 
								  "Failed to Set Source Environment for FTU to " + sourceEnv);
			
			boolean selectedForms = fmp.searchAndSelectFormInFTU(largeFormNames);
			TestValidation.IsTrue(selectedForms, 
								  "SELECTED forms for import - " + largeFormNames, 
								  "Failed to Select forms for import " + largeFormNames);
			
			boolean importBtnClicked = fmp.clickSelectForImportBtn();
			TestValidation.IsTrue(importBtnClicked, 
								  "CLICKED on 'Select for import' button", 
								  "Failed to Click on 'Select for import' button");
			
			List<String> options = new ArrayList<String>();
			options.add(FTU_IMPORT_OPTNS.IMPORT_EXISTING_FORMS);
			options.add(FTU_IMPORT_OPTNS.IMPORT_MISSING_RESOURCES);
			options.add(FTU_IMPORT_OPTNS.SYNCHRONIZE_MISMATCHED_RESOURCES);
			boolean importOptnsSelected = fmp.selectImportOptionForFTU(options);
			TestValidation.IsTrue(importOptnsSelected, 
								  "SELECTED Import options - " + options, 
								  "Failed to Select Import options - " + options);
			
			boolean importForms = fmp.importFormsAndCloseFTUPopup();
			TestValidation.IsTrue(importForms, 
								  "CLICKED on Import button and Closed popup", 
								  "Failed to Click on Import button or Close popup");

			boolean viewImportHistClicked = fmp.clickViewImportHistory();
			TestValidation.IsTrue(viewImportHistClicked, 
								  "CLICKED on View Import History link", 
								  "Failed to Click on View Import History link");
			
			currentDate = dt.getTodayDate("MM/dd/YYYY", timezoneCode);
			String firstHistEntryDets = fmp.FTUViewFirstHistDets.getText();
			boolean verifyDate = firstHistEntryDets.contains(currentDate);
			TestValidation.IsTrue(verifyDate, 
								  "VERIFIED Import History date as " + currentDate, 
								  "Failed to Verify Import History date");
			
			boolean verifyTimezone = firstHistEntryDets.contains(timezoneCode);
			TestValidation.IsTrue(verifyTimezone, 
								  "VERIFIED Import History timezone as " + timezoneCode, 
								  "Failed to Verify Import History timezone");
			
			boolean verifyUserame = firstHistEntryDets.contains(adminUsername);
			TestValidation.IsTrue(verifyUserame, 
								  "VERIFIED Import History username as " + adminUsername, 
								  "Failed to Verify Import History username");
			
			boolean verifyImportStatus = firstHistEntryDets.contains("Successful");
			TestValidation.IsTrue(verifyImportStatus, 
								  "VERIFIED Import History status as Successful", 
								  "Failed to Verify Import History status as Successful");
			
			String histEnvDets = fmp.FTUHistEnvDets.getText();
			boolean verifySourceEnv = histEnvDets.contains(sourceEnvHist);
			TestValidation.IsTrue(verifySourceEnv, 
								  "VERIFIED Import History source as " + sourceEnvHist, 
								  "Failed to Verify Import History source");	
	
			String histFormDets = fmp.FTUHistFormDets.getText();
			boolean verifyForm1 = histFormDets.contains(largeFormNames.get(0));
			boolean verifyForm2 = histFormDets.contains(largeFormNames.get(1));
			boolean verifyForm3 = histFormDets.contains(largeFormNames.get(2));
			TestValidation.IsTrue(verifyForm1 && verifyForm2 && verifyForm3, 
								  "VERIFIED Import History forms has " + largeFormNames, 
								  "Failed to Verify Import History forms");	
			
			String histResourceDets = fmp.FTUHistResourceDets.getText();
			boolean verifyResource1 = histResourceDets.contains(customersInstanceValue1_53725);
			boolean verifyResource2 = histResourceDets.contains(customersInstanceValue2_53725);
			TestValidation.IsTrue(verifyResource1 && verifyResource2, 
								  "VERIFIED Import History resources as " + customersInstanceValue1_53725 +
								  " and " + customersInstanceValue2_53725, 
								  "Failed to Verify Import History resources");	
			
			mrp = hp.clickResourcesMenu();
			boolean setLocationForResource = mrp.searchAndAddLinkToResource(RESOURCETYPES.CUSTOMERS, 
					customersInstanceValue1_53725, Arrays.asList(locationName));
			TestValidation.IsTrue(setLocationForResource, 
							      "LINKED resource " + customersInstanceValue1_53725 + " with location " + locationName, 
							  	  "Failed to Link resource " + customersInstanceValue1_53725 + " with location " + locationName);	
			
			fdp = new FormDesignerPage(driver);
			boolean formReleasePage = fdp.navigateToReleaseForm();
			TestValidation.IsTrue(formReleasePage, 
								  "Navigated to Release form successfully", 
								  "Could NOT Navigate to Release form"); 
			
			boolean releaseForm1 = fdp.releaseForm(largeFormNames.get(0));
			TestValidation.IsTrue(releaseForm1, 
							       "SHOULD release form - " + largeFormNames.get(0), 
								   "Failed since uncertain that the form " + largeFormNames.get(0) + " did/did not release'");

			boolean releaseForm2 = fdp.releaseForm(largeFormNames.get(1));
			TestValidation.IsTrue(releaseForm2, 
							       "SHOULD release form - " + largeFormNames.get(1), 
								   "Failed since uncertain that the form " + largeFormNames.get(1) + " did/did not release'");

			boolean releaseForm3 = fdp.releaseForm(largeFormNames.get(2));
			TestValidation.IsTrue(releaseForm3, 
							       "SHOULD release form - " + largeFormNames.get(2), 
								   "Failed since uncertain that the form " + largeFormNames.get(2) + " did/did not release'");

			fbp = hp.clickFSQABrowserMenu();
			boolean navigateToforms = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.CUSTOMERS,FSQATAB.FORMS);
			TestValidation.IsTrue(navigateToforms, 
								  "For customer category, NAVIGATED to FSQABrowser > Forms tab", 
								  "Failed to navigate to FSQABrowser > Forms tab for customer category");

			boolean applyfilter1 = fbp.openAndApplySettingsForColumn(COLUMNHEADER.FORMNAME, 
					COLUMNSETTING.FILTER, largeFormNames.get(0));
			TestValidation.IsTrue(applyfilter1, 
								  "Applied Filter for " + largeFormNames.get(0), 
								  "Failed to apply filter for " + largeFormNames.get(0));
			
			boolean verifyFrm1 = fbp.verifyGridValuesForColumn(COLUMNHEADER.FORMNAME, largeFormNames.get(0));
			TestValidation.IsTrue(verifyFrm1, 
								  "VERIFIED Form Name value as - " + largeFormNames.get(0), 
								  "Failed to verify Form Name value as " + largeFormNames.get(0));
			
			boolean applyfilter2 = fbp.openAndApplySettingsForColumn(COLUMNHEADER.FORMNAME, 
					COLUMNSETTING.FILTER, largeFormNames.get(1));
			TestValidation.IsTrue(applyfilter2, 
								  "Applied Filter for " + largeFormNames.get(1), 
								  "Failed to apply filter for " + largeFormNames.get(1));
			
			boolean verifyFrm2 = fbp.verifyGridValuesForColumn(COLUMNHEADER.FORMNAME, largeFormNames.get(1));
			TestValidation.IsTrue(verifyFrm2, 
								  "VERIFIED Form Name value as - " + largeFormNames.get(1), 
								  "Failed to verify Form Name value as " + largeFormNames.get(1));
			
			boolean applyfilter3 = fbp.openAndApplySettingsForColumn(COLUMNHEADER.FORMNAME, 
					COLUMNSETTING.FILTER, largeFormNames.get(2));
			TestValidation.IsTrue(applyfilter3, 
								  "Applied Filter for " + largeFormNames.get(2), 
								  "Failed to apply filter for " + largeFormNames.get(2));
			
			boolean verifyFrm3 = fbp.verifyGridValuesForColumn(COLUMNHEADER.FORMNAME, largeFormNames.get(2));
			TestValidation.IsTrue(verifyFrm3, 
								  "VERIFIED Form Name value as - " + largeFormNames.get(2), 
								  "Failed to verify Form Name value as " + largeFormNames.get(2));
			
		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				TestValidation.Fail(TCGFailureMsg);
			}
		}
	} 
	
	@Test(description="Verify that form import should take place if user select \"Import Existing Forms\" "
			+ "& \"Import Missing Resources\" option and the associated resource is not present in "
			+ "the target tenant while importing form")
	public void TestCase_53727() throws Exception{
		String formName = smallFormDetails.get(0); // ITEMS
		try {

			fmp = hp.clickFormsManagerMenu();
			TestValidation.Equals(fmp.error, 
								  false, 
								  "CLICKED on Forms menu", 
								  "Could NOT click on Forms menu");
			
			boolean importTabClicked = fmp.clickImportTab();
			TestValidation.IsTrue(importTabClicked, 
								  "NAVIGATED to Forms Manager > Import tab", 
								  "Failed to Navigate to Forms Manager > Import tab");
			
			boolean sourceEnvSet = fmp.setSourceEnvForFTU(sourceEnv);
			TestValidation.IsTrue(importTabClicked, 
								  "Source Environment for FTU is set to " + sourceEnvSet, 
								  "Failed to Set Source Environment for FTU to " + sourceEnvSet);
			
			boolean selectedForms = fmp.searchAndSelectFormInFTU(formName);
			TestValidation.IsTrue(selectedForms, 
								  "SELECTED forms for import - " + formName, 
								  "Failed to Select forms for import " + formName);
			
			boolean importBtnClicked = fmp.clickSelectForImportBtn();
			TestValidation.IsTrue(importBtnClicked, 
								  "CLICKED on 'Select for import' button", 
								  "Failed to Click on 'Select for import' button");
			
			List<String> options = new ArrayList<String>();
			options.add(FTU_IMPORT_OPTNS.IMPORT_EXISTING_FORMS);
			options.add(FTU_IMPORT_OPTNS.IMPORT_MISSING_RESOURCES);
			boolean importOptnsSelected = fmp.selectImportOptionForFTU(options);
			TestValidation.IsTrue(importOptnsSelected, 
								  "SELECTED Import options - " + options, 
								  "Failed to Select Import options - " + options);
			
			boolean importForms = fmp.importFormsAndCloseFTUPopup();
			TestValidation.IsTrue(importForms, 
								  "CLICKED on Import button and Closed popup", 
								  "Failed to Click on Import button or Close popup");
			
			boolean viewImportHistClicked = fmp.clickViewImportHistory();
			TestValidation.IsTrue(viewImportHistClicked, 
								  "CLICKED on View Import History link", 
								  "Failed to Click on View Import History link");
			
			currentDate = dt.getTodayDate("MM/dd/YYYY", timezoneCode);
			String firstHistEntryDets = fmp.FTUViewFirstHistDets.getText();
			boolean verifyDate = firstHistEntryDets.contains(currentDate);
			TestValidation.IsTrue(verifyDate, 
								  "VERIFIED Import History date as " + currentDate, 
								  "Failed to Verify Import History date");
			
			boolean verifyTimezone = firstHistEntryDets.contains(timezoneCode);
			TestValidation.IsTrue(verifyTimezone, 
								  "VERIFIED Import History timezone as " + timezoneCode, 
								  "Failed to Verify Import History timezone");
			
			boolean verifyUsername = firstHistEntryDets.contains(adminUsername);
			TestValidation.IsTrue(verifyUsername, 
								  "VERIFIED Import History username as " + adminUsername, 
								  "Failed to Verify Import History username");
			
			boolean verifyImportStatus = firstHistEntryDets.contains("Successful");
			TestValidation.IsTrue(verifyImportStatus, 
								  "VERIFIED Import History status as Successful", 
								  "Failed to Verify Import History status as Successful");
			
			String histEnvDets = fmp.FTUHistEnvDets.getText();
			boolean verifySourceEnv = histEnvDets.contains(sourceEnvHist);
			TestValidation.IsTrue(verifySourceEnv, 
								  "VERIFIED Import History source as " + sourceEnvHist, 
								  "Failed to Verify Import History source");	
			
			String histFormDets = fmp.FTUHistFormDets.getText();
			boolean verifyForm = histFormDets.contains(formName);
			TestValidation.IsTrue(verifyForm, 
								  "VERIFIED Import History form has " + formName, 
								  "Failed to Verify Import History form");	
			
			String histResourceDets = fmp.FTUHistResourceDets.getText();
			boolean verifyResource = histResourceDets.contains(itemsInstanceValue_53727);
			TestValidation.IsTrue(verifyResource, 
								  "VERIFIED Import History resources has " + itemsInstanceValue_53727, 
								  "Failed to Verify Import History resources");	
			
			mrp = hp.clickResourcesMenu();
			boolean setLocationForResource = mrp.searchAndAddLinkToResource(RESOURCETYPES.ITEMS, 
					itemsInstanceValue_53727, Arrays.asList(locationName));
			TestValidation.IsTrue(setLocationForResource, 
							      "LINKED resource " + itemsInstanceValue_53727 + " with location " + locationName, 
							  	  "Failed to Link resource " + itemsInstanceValue_53727 + " with location " + locationName);	

			fdp = new FormDesignerPage(driver);
			boolean formReleasePage = fdp.navigateToReleaseForm();
			TestValidation.IsTrue(formReleasePage, 
								  "Navigated to Release form successfully", 
								  "Could NOT Navigate to Release form"); 
			
			boolean releaseForm = fdp.releaseForm(formName);
			TestValidation.IsTrue(releaseForm, 
							       "SHOULD release form - " + formName, 
								   "Failed since uncertain that the form " + formName + " did/did not release'");
			
			fbp = hp.clickFSQABrowserMenu();
			boolean navigateToforms = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.ITEMS,FSQATAB.FORMS);
			TestValidation.IsTrue(navigateToforms, 
								  "For item category, NAVIGATED to FSQABrowser > Forms tab", 
								  "Failed to navigate to FSQABrowser > Forms tab for item category");
			
			boolean applyfilter = fbp.openAndApplySettingsForColumn(COLUMNHEADER.FORMNAME, 
					COLUMNSETTING.FILTER, formName);
			TestValidation.IsTrue(applyfilter, 
								  "Applied Filter for " + formName, 
								  "Failed to apply filter for " + formName);
			
			boolean verifyFrm = fbp.verifyGridValuesForColumn(COLUMNHEADER.FORMNAME, formName);
			TestValidation.IsTrue(verifyFrm, 
								  "VERIFIED Form Name value as - " + formName, 
								  "Failed to verify Form Name value as " + formName);
			
		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				TestValidation.Fail(TCGFailureMsg);
			}
		}
	} 
	
	@Test(description="Verify that form import should take place if user select \"Import Existing Forms\" "
			+ "& \"Synchronize Mis-Matched Resources\" option and associated resource is present "
			+ "but having a different category hierarchy in target tenant while importing form")
	public void TestCase_53728() throws Exception{
		// Same name of the resource but different categories on Source and Target
		// API FTU S_CCat > API FTU S_CInst (On Source - Same resource name and Different Category name)
		// API FTU T_CCat > API FTU S_CInst (On Target - Same resource name and Different Category name)

		String formName = formName_53728.get(0);
		try {
			
			fmp = hp.clickFormsManagerMenu();
			TestValidation.Equals(fmp.error, 
								  false, 
								  "CLICKED on Forms menu", 
								  "Could NOT click on Forms menu");
			
			boolean importTabClicked = fmp.clickImportTab();
			TestValidation.IsTrue(importTabClicked, 
								  "NAVIGATED to Forms Manager > Import tab", 
								  "Failed to Navigate to Forms Manager > Import tab");
			
			boolean sourceEnvSet = fmp.setSourceEnvForFTU(sourceEnv);
			TestValidation.IsTrue(importTabClicked, 
								  "Source Environment for FTU is set to " + sourceEnvSet, 
								  "Failed to Set Source Environment for FTU to " + sourceEnvSet);
			
			boolean selectedForms = fmp.searchAndSelectFormInFTU(formName);
			TestValidation.IsTrue(selectedForms, 
								  "SELECTED forms for import - " + formName, 
								  "Failed to Select forms for import " + formName);
			
			boolean importBtnClicked = fmp.clickSelectForImportBtn();
			TestValidation.IsTrue(importBtnClicked, 
								  "CLICKED on 'Select for import' button", 
								  "Failed to Click on 'Select for import' button");
			
			List<String> options = new ArrayList<String>();
			options.add(FTU_IMPORT_OPTNS.IMPORT_EXISTING_FORMS);
			options.add(FTU_IMPORT_OPTNS.SYNCHRONIZE_MISMATCHED_RESOURCES);
			boolean importOptnsSelected = fmp.selectImportOptionForFTU(options);
			TestValidation.IsTrue(importOptnsSelected, 
								  "SELECTED Import options - " + options, 
								  "Failed to Select Import options - " + options);
			
			boolean importForms = fmp.importFormsAndCloseFTUPopup();
			TestValidation.IsTrue(importForms, 
								  "CLICKED on Import button and Closed popup", 
								  "Failed to Click on Import button or Close popup");
			
			boolean viewImportHistClicked = fmp.clickViewImportHistory();
			TestValidation.IsTrue(viewImportHistClicked, 
								  "CLICKED on View Import History link", 
								  "Failed to Click on View Import History link");
			
			currentDate = dt.getTodayDate("MM/dd/YYYY", timezoneCode);
			String firstHistEntryDets = fmp.FTUViewFirstHistDets.getText();
			boolean verifyDate = firstHistEntryDets.contains(currentDate);
			TestValidation.IsTrue(verifyDate, 
								  "VERIFIED Import History date as " + currentDate, 
								  "Failed to Verify Import History date");
			
			boolean verifyTimezone = firstHistEntryDets.contains(timezoneCode);
			TestValidation.IsTrue(verifyTimezone, 
								  "VERIFIED Import History timezone as " + timezoneCode, 
								  "Failed to Verify Import History timezone");
			
			boolean verifyUsername = firstHistEntryDets.contains(username);
			TestValidation.IsTrue(verifyUsername, 
								  "VERIFIED Import History username as " + username, 
								  "Failed to Verify Import History username");
			
			boolean verifyImportStatus = firstHistEntryDets.contains("Successful");
			TestValidation.IsTrue(verifyImportStatus, 
								  "VERIFIED Import History status as Successful", 
								  "Failed to Verify Import History status as Successful");
			
			String histEnvDets = fmp.FTUHistEnvDets.getText();
			boolean verifySourceEnv = histEnvDets.contains(sourceEnvHist);
			TestValidation.IsTrue(verifySourceEnv, 
								  "VERIFIED Import History source as " + sourceEnvHist, 
								  "Failed to Verify Import History source");	
			
			String histFormDets = fmp.FTUHistFormDets.getText();
			boolean verifyForm = histFormDets.contains(formName);
			TestValidation.IsTrue(verifyForm, 
								  "VERIFIED Import History form has " + formName, 
								  "Failed to Verify Import History form");	
			
			fdp = new FormDesignerPage(driver);
			boolean formReleasePage = fdp.navigateToReleaseForm();
			TestValidation.IsTrue(formReleasePage, 
								  "Navigated to Release form successfully", 
								  "Could NOT Navigate to Release form"); 
			
			boolean releaseForm = fdp.releaseForm(formName);
			TestValidation.IsTrue(releaseForm, 
							       "SHOULD release form - " + formName, 
								   "Failed since uncertain that the form " + formName + " did/did not release'");
			
			fbp = hp.clickFSQABrowserMenu();
			boolean navigateToforms = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.CUSTOMERS,FSQATAB.FORMS);
			TestValidation.IsTrue(navigateToforms, 
								  "For customer category, NAVIGATED to FSQABrowser > Forms tab", 
								  "Failed to navigate to FSQABrowser > Forms tab for customer category");
			
			boolean applyfilter = fbp.openAndApplySettingsForColumn(COLUMNHEADER.FORMNAME, 
					COLUMNSETTING.FILTER, formName);
			TestValidation.IsTrue(applyfilter, 
								  "Applied Filter for " + formName, 
								  "Failed to apply filter for " + formName);
			
			boolean verifyFrm = fbp.verifyGridValuesForColumn(COLUMNHEADER.FORMNAME, formName);
			TestValidation.IsTrue(verifyFrm, 
								  "VERIFIED Form Name value as - " + formName, 
								  "Failed to verify Form Name value as " + formName);
			
		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				TestValidation.Fail(TCGFailureMsg);
			}
		}
	} 
	
	@Test(description="Verify that form import should not take place twice if user does not "
			+ "select import existing form option while importing the form second time")
	public void TestCase_53729() throws Exception{
		String formName = smallFormDetails.get(1); // ITEMS
		try {
			
			// Pre-requisite - START
			fmp = hp.clickFormsManagerMenu();
			TestValidation.Equals(fmp.error, 
								  false, 
								  "CLICKED on Forms menu", 
								  "Could NOT click on Forms menu");
			
			boolean importTabClicked1 = fmp.clickImportTab();
			TestValidation.IsTrue(importTabClicked1, 
								  "NAVIGATED to Forms Manager > Import tab", 
								  "Failed to Navigate to Forms Manager > Import tab");
			
			boolean sourceEnvSet1 = fmp.setSourceEnvForFTU(sourceEnv);
			TestValidation.IsTrue(sourceEnvSet1, 
								  "Source Environment for FTU is set to " + sourceEnv, 
								  "Failed to Set Source Environment for FTU to " + sourceEnv);
			
			boolean selectedForms1 = fmp.searchAndSelectFormInFTU(formName);
			TestValidation.IsTrue(selectedForms1, 
								  "SELECTED forms for import - " + formName, 
								  "Failed to Select forms for import " + formName);
			
			boolean importBtnClicked1 = fmp.clickSelectForImportBtn();
			TestValidation.IsTrue(importBtnClicked1, 
								  "CLICKED on 'Select for import' button", 
								  "Failed to Click on 'Select for import' button");
			
			List<String> options1 = new ArrayList<String>();
			options1.add(FTU_IMPORT_OPTNS.IMPORT_EXISTING_FORMS);
			options1.add(FTU_IMPORT_OPTNS.IMPORT_MISSING_RESOURCES);
			options1.add(FTU_IMPORT_OPTNS.SYNCHRONIZE_MISMATCHED_RESOURCES);
			boolean importOptnsSelected1 = fmp.selectImportOptionForFTU(options1);
			TestValidation.IsTrue(importOptnsSelected1, 
								  "SELECTED Import options - " + options1, 
								  "Failed to Select Import options - " + options1);
			
			boolean importForms1 = fmp.importFormsAndCloseFTUPopup();
			TestValidation.IsTrue(importForms1, 
								  "CLICKED on Import button and Closed popup", 
								  "Failed to Click on Import button or Close popup");
			
			// Pre-requisite - END

			boolean selectedForms2 = fmp.searchAndSelectFormInFTU(formName);
			TestValidation.IsTrue(selectedForms2, 
								  "SELECTED forms for import - " + formName, 
								  "Failed to Select forms for import " + formName);
			
			boolean importBtnClicked2 = fmp.clickSelectForImportBtn();
			TestValidation.IsTrue(importBtnClicked2, 
								  "CLICKED on 'Select for import' button", 
								  "Failed to Click on 'Select for import' button");
			
			List<String> options2 = new ArrayList<String>();
			options2.add(FTU_IMPORT_OPTNS.IMPORT_MISSING_RESOURCES);
			options2.add(FTU_IMPORT_OPTNS.SYNCHRONIZE_MISMATCHED_RESOURCES);
			boolean importOptnsSelected2 = fmp.selectImportOptionForFTU(options2);
			TestValidation.IsTrue(importOptnsSelected2, 
								  "SELECTED Import options - " + options2, 
								  "Failed to Select Import options - " + options2);
			
			boolean importForms2 = fmp.importFormsAndCloseFTUPopup();
			TestValidation.IsTrue(importForms2, 
								  "CLICKED on Import button and Closed popup", 
								  "Failed to Click on Import button or Close popup");
			
			boolean viewImportHistClicked = fmp.clickViewImportHistory();
			TestValidation.IsTrue(viewImportHistClicked, 
								  "CLICKED on View Import History link", 
								  "Failed to Click on View Import History link");
			
			currentDate = dt.getTodayDate("MM/dd/YYYY", timezoneCode);
			String firstHistEntryDets = fmp.FTUViewFirstHistDets.getText();
			boolean verifyDate = firstHistEntryDets.contains(currentDate);
			TestValidation.IsTrue(verifyDate, 
								  "VERIFIED Import History date as " + currentDate, 
								  "Failed to Verify Import History date");
			
			boolean verifyTimezone = firstHistEntryDets.contains(timezoneCode);
			TestValidation.IsTrue(verifyTimezone, 
								  "VERIFIED Import History timezone as " + timezoneCode, 
								  "Failed to Verify Import History timezone");
			
			boolean verifyUserame = firstHistEntryDets.contains(adminUsername);
			TestValidation.IsTrue(verifyUserame, 
								  "VERIFIED Import History username as " + adminUsername, 
								  "Failed to Verify Import History username");
			
			boolean verifyImportStatus = firstHistEntryDets.contains("Failed");
			TestValidation.IsTrue(verifyImportStatus, 
								  "VERIFIED Import History status as Failed", 
								  "Failed to Verify Import History status as Failed");
			
			String histEnvDets = fmp.FTUHistEnvDets.getText();
			boolean verifySourceEnv = histEnvDets.contains(sourceEnvHist);
			TestValidation.IsTrue(verifySourceEnv, 
								  "VERIFIED Import History source as " + sourceEnvHist, 
								  "Failed to Verify Import History source");	
			
			String histFormDets = fmp.FTUHistFormDets.getText();
			boolean verifyForm = histFormDets.contains(formName);
			boolean verifyImportMsg = histFormDets.contains("Not Imported");
			TestValidation.IsTrue(verifyForm && verifyImportMsg, 
								  "VERIFIED Import History form has " + formName + " - " + "Not Imported", 
								  "Failed to Verify Import History form");	
			
			fdp = new FormDesignerPage(driver);
			boolean formReleasePage = fdp.navigateToReleaseForm();
			TestValidation.IsTrue(formReleasePage, 
								  "Navigated to Release form successfully", 
								  "Could NOT Navigate to Release form"); 
			
			WebElement FormVersionLbl = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FORM_VERSION_LBL,
										"FORMNAME",formName);
			boolean compareVersionBfrRelse = FormVersionLbl.getText().contains("0.1");
			TestValidation.IsTrue(compareVersionBfrRelse, 
								  "VERIFIED " + formName + " Form's version as 0.1 as Second time import Failed", 
								  "Failed to Verify " + formName + " Form's version as 0.1");
			
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
