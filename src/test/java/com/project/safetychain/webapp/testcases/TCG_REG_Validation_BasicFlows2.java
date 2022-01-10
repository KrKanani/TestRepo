package com.project.safetychain.webapp.testcases;

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
import com.project.safetychain.webapp.pageobjects.AddEditValidationsPage.VALIDATION_TABS;
import com.project.safetychain.webapp.pageobjects.AddEditValidationsPage.VALIDATION_TYPE;
import com.project.safetychain.webapp.pageobjects.AddEditValidationsPage.VldtnDetsParams;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FIELD_TYPES;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FORMRESOURCETYPES;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FORMTYPES;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FormDesignParams;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FormFieldParams;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.IDENTIFIER_INPUT_TYPE;
import com.project.safetychain.webapp.pageobjects.FormsManagerPage.FM_STATUS;
import com.project.safetychain.webapp.pageobjects.ManageLocationPage.LocationInstanceParams;
import com.project.safetychain.webapp.pageobjects.ManageResourcePage.RESOURCETYPES;
import com.project.safetychain.webapp.pageobjects.ManageResourcePage.ResourceDetailParams;
import com.project.safetychain.webapp.pageobjects.ResourceDesignerPage.CATEGORYTYPES;
import com.project.safetychain.webapp.pageobjects.ValidationsPage.VLDTN_FIELDS;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.utilities.CommonMethods;
import com.project.utilities.ControlActions;
import com.project.utilities.DateTime;

public class TCG_REG_Validation_BasicFlows2 extends TestBase{

	ControlActions controlActions;
	HomePage hp;
	LoginPage lp;
	DateTime dt;
	public static String locationCategoryValue;
	public static String locationInstanceValue;
	public static String customerCategoryValue;
	public static String customerInstanceValue;
	public static String formName;
	public static String numericFN = "Number";
	public static String identifierFN = "ID";
	public static String section = "Section";
	public static String vldtnName;
	public static String vldtnSummary;
	public static String vldtnStartDate, vldtnEndDate;
	HashMap<String,String> identifierDets;
	public static String rolename = "SuperAdmin";
	public static String currentDate;

	@BeforeClass(alwaysRun = true)
	public void groupInit() throws InterruptedException {

		locationCategoryValue = CommonMethods.dynamicText("Loc_Cat");
		locationInstanceValue = CommonMethods.dynamicText("Loc_Inst");
		customerCategoryValue = CommonMethods.dynamicText("Cust_Cat");
		customerInstanceValue = CommonMethods.dynamicText("Cust_Inst");
		formName = CommonMethods.dynamicText("VldtnBsc2");
		vldtnName = CommonMethods.dynamicText("VldtnBsc2");
		vldtnSummary = CommonMethods.dynamicText("Summary");
		
		dt = new DateTime();
		vldtnStartDate = dt.AddDaystoToddaysDate(-3, "MM/dd/yyyy hh:mm aa");
		vldtnEndDate = dt.AddDaystoToddaysDate(0, "MM/dd/yyyy hh:mm aa");

		
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
		
		// Category creation
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

		//Create Audit Form
		HashMap<String, List<String>> selectResources = new HashMap<String, List<String>>();
		selectResources.put(FORMRESOURCETYPES.CUSTOMERS,Arrays.asList(customerCategoryValue));

		HashMap<String,List<String>> fieldDetails = new HashMap<String,List<String>>();
		fieldDetails.put(FIELD_TYPES.NUMERIC, Arrays.asList(numericFN));
		fieldDetails.put(FIELD_TYPES.IDENTIFIER, Arrays.asList(identifierFN));

		FormFieldParams ffparams = new FormFieldParams();
		ffparams.FieldDetails = fieldDetails;		
		ffparams.IdentiferType = IDENTIFIER_INPUT_TYPE.SELECT_ONE;
		ffparams.IdentifierValue = Arrays.asList("LINE1","LINE2");
		ffparams.SectionName = section;

		FormDesignParams fdparams = new FormDesignParams();
		fdparams.FormType = FORMTYPES.AUDIT;
		fdparams.FormName = formName;
		fdparams.SelectResources = selectResources;
		fdparams.DesignFields = Arrays.asList(ffparams);


		FormDesignerPage fdp = hp.clickFormDesignerMenu();
		if(!fdp.createAndReleaseForm(fdparams)) {
			TCGFailureMsg = "Could NOT create and release form - " + formName;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

	}

	@Test(description="Validations || Verify the elements in edit validation screen.")
	public void TestCase_34272() {
		try {
			
			WebElement SelectFormChk = null;
			ValidationsPage vp = hp.clickValidationsMenu();
			TestValidation.Equals(vp.error, 
								  false, 
								  "CLICKED on Validations menu", 
								  "Could NOT click on Validations menu"); 
			
			AddEditValidationsPage aevp = vp.createNewValidation();
			TestValidation.Equals(aevp.error, 
								  false, 
								  "CLICKED on New Validation button", 
								  "Could NOT click on New Validation button");
			
			boolean setName = aevp.setValidationName(vldtnName);
			TestValidation.IsTrue(setName, 
					  			   "The Validation name is SET as " + vldtnName, 
					  			   "Failed to set Validation name as " + vldtnName);
			
			boolean setType = aevp.setValidationType(VALIDATION_TYPE.PROCESS_VIOLATION);
			TestValidation.IsTrue(setType, 
					  			   "The Validation type is SET as " + VALIDATION_TYPE.PROCESS_VIOLATION, 
					  			   "Failed to set Validation type as " + VALIDATION_TYPE.PROCESS_VIOLATION);
			
			boolean setLocation = aevp.setValidationLocation(locationInstanceValue);
			TestValidation.IsTrue(setLocation, 
					  			   "The Validation location is SET as " + locationInstanceValue, 
					  			   "Failed to set Validation location as " + locationInstanceValue);
			
			boolean setResource = aevp.setValidationResource(customerInstanceValue);
			TestValidation.IsTrue(setResource, 
					  			   "The Validation resource is SET as " + customerInstanceValue, 
					  			   "Failed to set Validation resource as " + customerInstanceValue);
			
			boolean setForm = aevp.setValidationForms(Arrays.asList(formName));
			TestValidation.IsTrue(setForm, 
								  "The Validation form is SET as " + formName, 
								  "Failed to set Validation form as " + formName);
			
			boolean verifyTitleBefore = aevp.VldtnTitle.getText().contains("Add New Validation");
			TestValidation.IsTrue(verifyTitleBefore, 
								  "VERIFIED the title has value as 'Add New Validation'", 
								  "Failed to verify that the title has value as 'Add New Validation'");
			
			boolean verifyUpdateBtn1 = controlActions.isElementDisplayed(aevp.UpdateVldtnBtn);
			TestValidation.IsFalse(verifyUpdateBtn1, 
								  "VERIFIED Update validation button is not displayed", 
								  "Failed to verify Update validation button is displayed or not");
			
			boolean verifyCompleteBtn1 = controlActions.isElementDisplayed(aevp.CompleteVldtnBtn);
			TestValidation.IsFalse(verifyCompleteBtn1, 
								  "VERIFIED Complete validation button is not displayed", 
								  "Failed to verify Complete validation button is displayed or not");
			
			boolean verifyDeleteBtn1 = controlActions.isElementDisplayed(aevp.DeleteVldtnBtn);
			TestValidation.IsFalse(verifyDeleteBtn1, 
								  "VERIFIED Delete validation button is not displayed", 
								  "Failed to verify Delete validation button is displayed or not");
			
			boolean verifyCopyBtn1 = controlActions.isElementDisplayed(aevp.CopyVldtnBtn);
			TestValidation.IsFalse(verifyCopyBtn1, 
								  "VERIFIED Copy validation button is not displayed", 
								  "Failed to verify Copy validation button is displayed or not");
			
			boolean addVldtn = aevp.clickAddValidation();
			TestValidation.IsTrue(addVldtn, 
								  "Validation " + vldtnName + " is ADDED successfully", 
								  "Could NOT create Validation " + vldtnName);
			
			//additionally added
			vp = aevp.clickCloseValidation();
			TestValidation.Equals(vp.error, 
					  			  false, 
					  			  "CLOSED validtion " + vldtnName, 
					  			  "Could NOT close validation " + vldtnName);
			
			aevp = vp.searchAndOpenVldtn(VLDTN_FIELDS.NAME, vldtnName);
			TestValidation.Equals(aevp.error, 
								  false, 
								  "OPENED validtion " + vldtnName, 
								  "Could NOT open validation " + vldtnName);
			
			boolean verifyTitleAfter = aevp.VldtnTitle.getText().contains(vldtnName);
			TestValidation.IsTrue(verifyTitleAfter, 
								  "VERIFIED the title has value as " + vldtnName, 
								  "Failed to verify that the title has value as " + vldtnName);
			
			boolean verifyType1 = aevp.VldtnTypeVal.getText().contains(VALIDATION_TYPE.PROCESS_VIOLATION);
			TestValidation.IsTrue(verifyType1, 
				  			      "VERIFIED Validation type field's value is " + VALIDATION_TYPE.PROCESS_VIOLATION, 
				  			      "Failed to verify Validation type field's value");
			
			boolean verifyLocn1 = aevp.VldtnLocationVal.getText().contains(locationInstanceValue);
			TestValidation.IsTrue(verifyLocn1, 
				  			      "VERIFIED Validation location field's value is " + locationInstanceValue, 
				  			      "Failed to verify Validation location field's value");
			
			boolean verifyRes1 = aevp.VldtnResourceVal.getText().contains(customerInstanceValue);
			TestValidation.IsTrue(verifyRes1, 
				  			      "VERIFIED Validation resource field's value is " + customerInstanceValue, 
				  			      "Failed to verify Validation resource field's value");
			
			SelectFormChk = controlActions.perform_GetElementByXPath(AddEditValidationsPageLocators.SELECT_FORM_CHK, 
							"FORMNAME", formName);
			boolean verifyForm1 = SelectFormChk.isSelected();
			TestValidation.IsTrue(verifyForm1, 
				  			      "VERIFIED Validation form section's has selected form - " + formName, 
				  			      "Failed to verify Validation form section's selected form");
			
			boolean verifyUpdateBtn2 = controlActions.isElementDisplayed(aevp.UpdateVldtnBtn);
			TestValidation.IsTrue(verifyUpdateBtn2, 
								  "VERIFIED Update validation button is displayed", 
								  "Failed to verify Update validation button is displayed or not");
			
			boolean verifyClearBtn2 = controlActions.isElementDisplayed(aevp.ClearVldtnBtn);
			TestValidation.IsTrue(verifyClearBtn2, 
								  "VERIFIED Clear validation button is displayed", 
								  "Failed to verify Clear validation button is displayed or not");
			
			boolean verifyDeleteBtn2 = controlActions.isElementDisplayed(aevp.DeleteVldtnBtn);
			TestValidation.IsTrue(verifyDeleteBtn2, 
								  "VERIFIED Delete validation button is displayed", 
								  "Failed to verify Delete validation button is displayed or not");
			
			boolean verifyCopyBtn2 = controlActions.isElementDisplayed(aevp.CopyVldtnBtn);
			TestValidation.IsTrue(verifyCopyBtn2, 
								  "VERIFIED Copy validation button is displayed", 
								  "Failed to verify Copy validation button is displayed or not");
			
			boolean verifyCompleteBtn2 = controlActions.isElementDisplayed(aevp.CompleteVldtnBtn);
			TestValidation.IsTrue(verifyCompleteBtn2, 
								  "VERIFIED Complete validation button is displayed", 
								  "Failed to verify Complete validation button is displayed or not");
		}
		finally {
			vldtnName = "Retry" + vldtnName;

			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}
		}
	}	
	
	@Test(description="Validations || Verify if \"ANY\" is selected in the resource dropdown "
			+ "then all the forms linked to the selected location should be listed.")
	public void TestCase_35253() {
		try {
			
			ValidationsPage vp = hp.clickValidationsMenu();
			TestValidation.Equals(vp.error, 
								  false, 
								  "CLICKED on Validations menu", 
								  "Could NOT click on Validations menu"); 
			
			AddEditValidationsPage aevp = vp.createNewValidation();
			TestValidation.Equals(aevp.error, 
								  false, 
								  "CLICKED on New Validation button", 
								  "Could NOT click on New Validation button");
			
			boolean setName = aevp.setValidationName(vldtnName);
			TestValidation.IsTrue(setName, 
					  			   "The Validation name is SET as " + vldtnName, 
					  			   "Failed to set Validation name as " + vldtnName);
			
			boolean setType = aevp.setValidationType(VALIDATION_TYPE.NEW_PROCEDURES);
			TestValidation.IsTrue(setType, 
					  			   "The Validation type is SET as " + VALIDATION_TYPE.NEW_PROCEDURES, 
					  			   "Failed to set Validation type as " + VALIDATION_TYPE.NEW_PROCEDURES);
			
			boolean setLocation = aevp.setValidationLocation(locationInstanceValue);
			TestValidation.IsTrue(setLocation, 
					  			   "The Validation location is SET as " + locationInstanceValue, 
					  			   "Failed to set Validation location as " + locationInstanceValue);
			
			boolean setResource = aevp.setValidationResource("ANY");
			TestValidation.IsTrue(setResource, 
					  			   "The Validation resource is SET as " + customerInstanceValue, 
					  			   "Failed to set Validation resource as " + customerInstanceValue);
			
			int formSize = aevp.DisplydFormsLbl.size();
			TestValidation.IsTrue(formSize==1, 
				  			      "VERIFIED 1 form is displayed in Validation's form section", 
				  			      "Failed to verify displayed forms in Validation form section");
					
			String verifyFormName = aevp.DisplydFormsLbl.get(0).getText();
			TestValidation.IsTrue(verifyFormName.contains(formName), 
				  			      "VERIFIED Validation form section's is displaying form - " + formName, 
				  			      "Failed to verify displayed forms in Validation form section");
		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}
		}
	}	
	
	@Test(description="Validations || Verify History tab and Copy & Complete button should not be present in "
			+ "edit validation screen.")
	public void TestCase_34271() {
		try {
			
			ValidationsPage vp = hp.clickValidationsMenu();
			TestValidation.Equals(vp.error, 
								  false, 
								  "CLICKED on Validations menu", 
								  "Could NOT click on Validations menu"); 
			
			AddEditValidationsPage aevp = vp.createNewValidation();
			TestValidation.Equals(aevp.error, 
								  false, 
								  "CLICKED on New Validation button", 
								  "Could NOT click on New Validation button");
			
			boolean setName = aevp.setValidationName(vldtnName);
			TestValidation.IsTrue(setName, 
					  			   "The Validation name is SET as " + vldtnName, 
					  			   "Failed to set Validation name as " + vldtnName);
			
			boolean setType = aevp.setValidationType(VALIDATION_TYPE.NEW_PROCEDURES);
			TestValidation.IsTrue(setType, 
					  			   "The Validation type is SET as " + VALIDATION_TYPE.NEW_PROCEDURES, 
					  			   "Failed to set Validation type as " + VALIDATION_TYPE.NEW_PROCEDURES);
			
			boolean setLocation = aevp.setValidationLocation(locationInstanceValue);
			TestValidation.IsTrue(setLocation, 
					  			   "The Validation location is SET as " + locationInstanceValue, 
					  			   "Failed to set Validation location as " + locationInstanceValue);
			
			boolean verifyCompleteBtn = controlActions.isElementDisplayed(aevp.CompleteVldtnBtn);
			TestValidation.IsFalse(verifyCompleteBtn, 
								  "VERIFIED Complete validation button is not displayed", 
								  "Failed to verify Complete validation button is displayed or not");
			
			boolean verifyCopyBtn = controlActions.isElementDisplayed(aevp.CopyVldtnBtn);
			TestValidation.IsFalse(verifyCopyBtn, 
								  "VERIFIED Copy validation button is not displayed", 
								  "Failed to verify Copy validation button is displayed or not");
			
			WebElement SetVldtnTab = controlActions.perform_GetElementByXPath(AddEditValidationsPageLocators.SET_VLDTN_TAB, 
					"TABNAME", VALIDATION_TABS.HISTORY);
			TestValidation.IsTrue(SetVldtnTab==null, 
								  "VERIFIED History tab is not displayed", 
								  "Failed to verify History tab is displayed or not");
			
		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}
		}
	}	
	
	@Test(description="Validation || When form selected for a Validation is disabled, an error message "
			+ "should be displayed on Complete")
	public void TestCase_38187() {
		
		// Prerequisite creation - Form and Validation
		String frmName = CommonMethods.dynamicText("DisableFrm");
		String validatnName = CommonMethods.dynamicText("Vldtn_Disable");
		
		HashMap<String, List<String>> fields = new HashMap<String, List<String>>();
		fields.put(FIELD_TYPES.NUMERIC, Arrays.asList(numericFN));

		FormFieldParams ffp = new FormFieldParams();
		ffp.FieldDetails = fields;

		HashMap<String, List<String>> resource = new HashMap<String, List<String>>();
		resource.put(FORMRESOURCETYPES.CUSTOMERS, Arrays.asList(customerCategoryValue));

		FormDesignParams fp = new FormDesignParams();
		fp.FormType = FORMTYPES.CHECK;
		fp.FormName = frmName;
		fp.SelectResources = resource;
		fp.DesignFields = Arrays.asList(ffp);

		FormDesignerPage fdp = hp.clickFormDesignerMenu();
		boolean releaseForm = fdp.createAndReleaseForm(fp);
		TestValidation.IsTrue(releaseForm,
							  "RELEASED form - " + frmName,
							  "Could NOT release form - " + frmName); 
		
		ValidationsPage vp = hp.clickValidationsMenu();
		TestValidation.Equals(vp.error, 
							  false, 
							  "CLICKED on Validations menu", 
							  "Could NOT click on Validations menu"); 

		AddEditValidationsPage aevp = vp.createNewValidation();
		TestValidation.Equals(aevp.error, 
							  false, 
							  "CLICKED on New Validation button", 
							  "Could NOT click on New Validation button");

		VldtnDetsParams vdp = new VldtnDetsParams();
		vdp.Name = validatnName;
		vdp.Type = VALIDATION_TYPE.PROGRAM_VIOLATION;
		vdp.Location = locationInstanceValue;
		vdp.Resource = "ANY";
		vdp.Forms = Arrays.asList(frmName);
		vdp.StartDate = vldtnStartDate;
		vdp.EndDate = vldtnEndDate;
		boolean addedVldtn = aevp.createAndAddValidation(vdp);
		TestValidation.IsTrue(addedVldtn, 
							  "ADDED Validation named " + validatnName, 
							  "Failed to add Validation named " + validatnName);
		
		// TC Flow
		FormsManagerPage fmp = hp.clickFormsManagerMenu();
		boolean disableForm = fmp.searchAndEnableDisableForm(frmName, FM_STATUS.DISABLED);
		TestValidation.IsTrue(disableForm, 
						      "DISABLED the form - " + frmName,
						      "Failed to disable form - " + frmName);
		
		vp = hp.clickValidationsMenu();
		TestValidation.Equals(vp.error, 
							  false, 
							  "CLICKED on Validations menu", 
							  "Could NOT click on Validations menu");
		
		aevp = vp.searchAndOpenVldtn(VLDTN_FIELDS.NAME, validatnName);
		TestValidation.Equals(aevp.error, 
							  false, 
							  "OPENED validtion " + vldtnName, 
							  "Could NOT open validation " + vldtnName);
		
		boolean clickedComplete = aevp.clickCompleteValidation();
		TestValidation.IsTrue(clickedComplete, 
							  "CLICKED on Complete validation button", 
							  "Failed to click on Complete validation button");
		
		String errorMsg = "Required - Select at least 1 Valid Form";
		boolean verifyMsg = aevp.FormReqdMsg.getText().contains(errorMsg);
		TestValidation.IsTrue(verifyMsg, 
							  "VERIFIED the error message as : " + errorMsg, 
							  "Failed to verify error message as : " + errorMsg);
		
	}
	
	@Test(description="Verify the new validation is created when the Copy Validation button is clicked")
	public void TestCase_34065() {
		
		String validatnName = CommonMethods.dynamicText("Vldtn_Copy");
		String updatedValidatnName = null;
		
		ValidationsPage vp = hp.clickValidationsMenu();
		TestValidation.Equals(vp.error, 
							  false, 
							  "CLICKED on Validations menu", 
							  "Could NOT click on Validations menu"); 

		AddEditValidationsPage aevp = vp.createNewValidation();
		TestValidation.Equals(aevp.error, 
							  false, 
							  "CLICKED on New Validation button", 
							  "Could NOT click on New Validation button");

		VldtnDetsParams vdp = new VldtnDetsParams();
		vdp.Name = validatnName;
		vdp.Type = VALIDATION_TYPE.NEW_PRODUCT;
		vdp.Location = locationInstanceValue;
		vdp.Resource = customerInstanceValue;
		vdp.Forms = Arrays.asList(formName);
		vdp.StartDate = vldtnStartDate;
		vdp.EndDate = vldtnEndDate;
		boolean addedVldtn = aevp.createAndAddValidation(vdp);
		TestValidation.IsTrue(addedVldtn, 
							  "ADDED Validation named " + validatnName, 
							  "Failed to add Validation named " + validatnName);
		
		boolean completeVldtn = aevp.completeValidationWithResult(VALIDATION_RESULT.PASS, vldtnSummary);
		TestValidation.IsTrue(completeVldtn, 
							  "COMPLETED Validation " + validatnName + " with status " + VALIDATION_RESULT.PASS, 
							  "Could NOT complete Validation " + validatnName + " with status " + VALIDATION_RESULT.PASS);
		
		boolean clickCopyBtn = aevp.clickCopyValidation();
		TestValidation.IsTrue(clickCopyBtn, 
			  			      "CLICKED on Copy button", 
			  			      "Failed to click on Copy button");
		
		updatedValidatnName = validatnName + "_1";
		vp = aevp.clickCloseValidation();
		TestValidation.Equals(vp.error, 
				  			  false, 
				  			  "CLOSED validtion " + updatedValidatnName, 
				  			  "Could NOT close validation " + updatedValidatnName);
		
		aevp = vp.searchAndOpenVldtn(VLDTN_FIELDS.NAME, updatedValidatnName);
		TestValidation.Equals(aevp.error, 
							  false, 
							  "OPENED validtion " + updatedValidatnName, 
							  "Could NOT open validation " + updatedValidatnName);
		
		boolean verifyCompleteBtn = controlActions.isElementDisplayed(aevp.CompleteVldtnBtn);
		TestValidation.IsTrue(verifyCompleteBtn, 
							  "VERIFIED Complete validation button is displayed", 
							  "Failed to verify Complete validation button is displayed or not");
		
	}
	
	@Test(description="Verify the validation is deleted once the button is clicked on the page")
	public void TestCase_34071() {
		
		String validatnName = CommonMethods.dynamicText("Vldtn_Del");
		
		ValidationsPage vp = hp.clickValidationsMenu();
		TestValidation.Equals(vp.error, 
							  false, 
							  "CLICKED on Validations menu", 
							  "Could NOT click on Validations menu"); 

		AddEditValidationsPage aevp = vp.createNewValidation();
		TestValidation.Equals(aevp.error, 
							  false, 
							  "CLICKED on New Validation button", 
							  "Could NOT click on New Validation button");

		VldtnDetsParams vdp = new VldtnDetsParams();
		vdp.Name = validatnName;
		vdp.Type = VALIDATION_TYPE.NEW_PRODUCT;
		vdp.Location = locationInstanceValue;
		vdp.Resource = customerInstanceValue;
		vdp.Forms = Arrays.asList(formName);
		vdp.StartDate = vldtnStartDate;
		vdp.EndDate = vldtnEndDate;
		boolean addedVldtn = aevp.createAndAddValidation(vdp);
		TestValidation.IsTrue(addedVldtn, 
							  "ADDED Validation named " + validatnName, 
							  "Failed to add Validation named " + validatnName);
		
		vp = aevp.clickDeleteValidation();
		TestValidation.Equals(vp.error, 
	  			              false,  
			  			      "DELETED validation named " + validatnName, 
			  			      "Failed to delete validation named " + validatnName);
		
		boolean applyNameFilter = vp.setFilterToColumn(VLDTN_FIELDS.NAME, validatnName);
		TestValidation.IsTrue(applyNameFilter, 
							  "APPLIED grid filter to Name column with value " + validatnName, 
							  "Failed to set grid filter to Name column");
		
		int cntAfterFilter = vp.VldtnGridNameLst.size();
		TestValidation.IsTrue(cntAfterFilter==0, 
							  "As " + validatnName + " validation is deleted, it is NOT DISPLAYED", 
							  "Failed to verify whether validations are displayed or not");
		
	}
	
	@AfterClass(alwaysRun = true)
	public void closeBrowser() throws InterruptedException {
		driver.close();
	}

}
