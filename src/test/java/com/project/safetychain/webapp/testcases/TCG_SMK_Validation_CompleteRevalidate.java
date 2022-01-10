package com.project.safetychain.webapp.testcases;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.project.safetychain.api.models.support.TCG_FormCreation_Wrapper;
import com.project.safetychain.api.models.support.TCG_FormSubmission_Wrapper;
import com.project.safetychain.api.models.support.Support.DPT_FIELD_TYPES;
import com.project.safetychain.api.models.support.Support.DPT_FORM_TYPES;
import com.project.safetychain.api.models.support.Support.FormFieldParams;
import com.project.safetychain.api.models.support.Support.FormParams;
import com.project.safetychain.api.models.support.Support.IDENTIFIER_TYPES;
import com.project.safetychain.api.models.support.Support.RESOURCE_TYPES;
import com.project.safetychain.webapp.pageobjects.*;
import com.project.safetychain.webapp.pageobjects.AddEditValidationsPage.VALIDATION_RESULT;
import com.project.safetychain.webapp.pageobjects.AddEditValidationsPage.VALIDATION_TYPE;
import com.project.safetychain.webapp.pageobjects.AddEditValidationsPage.VldtnDetsParams;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.COLUMNHEADER;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.FSQARESOURCE;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.FSQATAB;
import com.project.safetychain.webapp.pageobjects.FSQABrowserRecordsPage.FORMFIELDS;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FIELD_TYPES;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FORMRESOURCETYPES;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FORMTYPES;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FormDesignParams;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.IDENTIFIER_INPUT_TYPE;
import com.project.safetychain.webapp.pageobjects.ManageLocationPage.LocationInstanceParams;
import com.project.safetychain.webapp.pageobjects.ManageResourcePage.RESOURCETYPES;
import com.project.safetychain.webapp.pageobjects.ManageResourcePage.ResourceDetailParams;
import com.project.safetychain.webapp.pageobjects.ResourceDesignerPage.CATEGORYTYPES;
import com.project.safetychain.webapp.pageobjects.ValidationsPage.VLDTN_FIELDS;
import com.project.safetychain.webapp.testcases.support.FormOperations;
import com.project.safetychain.webapp.testcases.support.FormOperations.FormDetails;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.testbase.SupportingClasses.ExecutionMode;
import com.project.utilities.ApiUtils;
import com.project.utilities.CommonMethods;
import com.project.utilities.ControlActions;
import com.project.utilities.DateTime;

public class TCG_SMK_Validation_CompleteRevalidate extends TestBase{

	ControlActions controlActions;
	HomePage hp;
	UserManagerPage ump;
	WorkGroupsPage wgp;
	RolesManagerPage rmp;
	FormDesignerPage fdp;
	FSQABrowserPage fbp;
	FormOperations fop;
	ValidationsPage vp;
	AddEditValidationsPage aevp;
	FSQABrowserRecordsPage frp;
	DateTime dateTime;
	public static String locationCategoryValue;
	public static String locationInstanceValue;
	public static String equipmentsCategoryValue;
	public static String equipmentsInstanceValue;
	public static String formName;
	public static String numericFN = "Number";
	public static String identifierFN = "ID";
	public static String vldtnName;
	public static String vldtnSummary;


	@BeforeClass(alwaysRun = true)
	public void groupInit() throws InterruptedException {

		locationCategoryValue = CommonMethods.dynamicText("Loc_Cat");
		locationInstanceValue = CommonMethods.dynamicText("Loc_Inst");
		equipmentsCategoryValue = CommonMethods.dynamicText("Equip_Cat");
		equipmentsInstanceValue = CommonMethods.dynamicText("Equip_Inst");
		formName = CommonMethods.dynamicText("VldtnChk");
		vldtnName = CommonMethods.dynamicText("Vldtn");
		vldtnSummary = CommonMethods.dynamicText("Summary");

		if(executionMode.equalsIgnoreCase(ExecutionMode.API)) {
			// ------------------------------------------------------------------------------------------------
			// API Implementation
			TCG_FormCreation_Wrapper formCreationWrapper = new TCG_FormCreation_Wrapper();
			TCG_FormSubmission_Wrapper formSubmissionWrapper = new TCG_FormSubmission_Wrapper();
			ApiUtils apiUtils = new ApiUtils();
	
			// ------------------------------------------------------------------------------------------------
			// API - Location & Resource Creation and Linking
			List<String> userList = Arrays.asList(adminUsername);
	
			HashMap<String, List<String>> LocationMap = new HashMap<String, List<String>>();
			LocationMap.put(locationCategoryValue, Arrays.asList(locationInstanceValue));
	
			HashMap<String, List<String>> resourceCatMap = new HashMap<String, List<String>>();
			resourceCatMap.put(equipmentsCategoryValue, Arrays.asList(equipmentsInstanceValue));
	
			formCreationWrapper.Resource_Location_CreationWrapper(resourceCatMap, LocationMap, true, userList,
					RESOURCE_TYPES.EQUIPMENT);
	
			// ------------------------------------------------------------------------------------------------
			// API - Form Creation - Check
			// API - Add fields to form
			FormFieldParams numericField = new FormFieldParams();
			numericField.DPTFieldType = DPT_FIELD_TYPES.NUMERIC;
			numericField.Name = numericFN;
			
			FormFieldParams identifierField = new FormFieldParams();
			identifierField.DPTFieldType = DPT_FIELD_TYPES.IDENTIFIER;
			identifierField.Name = identifierFN;
			identifierField.identifierId = IDENTIFIER_TYPES.SELECT_ONE;
			identifierField.IdentifierOption = Arrays.asList("LINE1","LINE2");
	
			// API - Form details
			// API - Generate unique ID for form to be created
			String formId = apiUtils.getUUID();
	
			// API - Form layout details
			FormParams fp = new FormParams();
			fp.FormId = formId;
			fp.FormName = formName;
			fp.type = DPT_FORM_TYPES.CHECK;
			fp.ResourceType = RESOURCE_TYPES.EQUIPMENT;
			fp.ResourceCategoryNm = equipmentsCategoryValue;
			fp.ResourceInstanceNm = equipmentsInstanceValue;
			fp.formElements = Arrays.asList(numericField, identifierField);
			fp.EquipmentResources = resourceCatMap;
	
			formCreationWrapper.API_Wrapper_Forms(fp);
	
			// ------------------------------------------------------------------------------------------------
			// API - Submit form
	
			// API - Add values to fields in form
			FormFieldParams submitNumericField = new FormFieldParams();
			submitNumericField.Name = numericFN;
			submitNumericField.Value = "4";
			
			FormFieldParams submitIdentifierField = new FormFieldParams();
			submitIdentifierField.Name = identifierFN;
			submitIdentifierField.Value = "LINE1";
	
			// API - Form layout details
			FormParams fpSubmit = new FormParams();
			fpSubmit.FormName = formName;
			fpSubmit.ResourceInstanceNm = equipmentsInstanceValue;
			fpSubmit.LocationInstanceNm = locationInstanceValue;
			fpSubmit.formElements = Arrays.asList(submitNumericField, submitIdentifierField);
	
			formSubmissionWrapper.API_Form_Submit_Wrapper(fpSubmit);
	
			// ------------------------------------------------------------------------------------------------
			// WEB Application code starts here
			driver = launchbrowser();
			controlActions = new ControlActions(driver);
			controlActions.getUrl(applicationUrl);
	
			hp = new HomePage(driver);
			ump = new UserManagerPage(driver);
			wgp = new WorkGroupsPage(driver);
			rmp = new RolesManagerPage(driver);
			fdp = new FormDesignerPage(driver);
			fbp = new FSQABrowserPage(driver);
			fop = new FormOperations(driver);
			vp = new ValidationsPage(driver);
			aevp = new AddEditValidationsPage(driver);
			frp = new FSQABrowserRecordsPage(driver);
			dateTime = new DateTime(driver);
			
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
			// WEB Application code starts here
			driver = launchbrowser();
			controlActions = new ControlActions(driver);
			controlActions.getUrl(applicationUrl);

			hp = new HomePage(driver);
			ump = new UserManagerPage(driver);
			wgp = new WorkGroupsPage(driver);
			rmp = new RolesManagerPage(driver);
			fdp = new FormDesignerPage(driver);
			fbp = new FSQABrowserPage(driver);
			fop = new FormOperations(driver);
			vp = new ValidationsPage(driver);
			aevp = new AddEditValidationsPage(driver);
			frp = new FSQABrowserRecordsPage(driver);
			dateTime = new DateTime(driver);

			LoginPage lp = new LoginPage(driver);
			hp = lp.performLogin(adminUsername, adminPassword);
			if(hp.error) {
				TCGFailureMsg = "Could NOT login to application";
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}

			//Category creation
			HashMap<String,String> categories = new HashMap<String, String>();
			categories.put(CATEGORYTYPES.EQUIPMENT, equipmentsCategoryValue);
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
			instances.put(equipmentsInstanceValue, true);
			ResourceDetailParams rd = new ResourceDetailParams();
			rd.CategoryName = equipmentsCategoryValue;
			rd.CategoryType = RESOURCETYPES.EQUIPMENT;
			rd.NumberFieldValue = 30;
			rd.TextFieldValue = "ABC";
			rd.InstanceNames = instances;
			rd.LocationInstanceValue = locationInstanceValue;	
			ManageResourcePage mrp = hp.clickResourcesMenu();
			if(!mrp.createResourceLinkLocation(rd)) {
				TCGFailureMsg = "Could NOT create Instances for resource - " + equipmentsInstanceValue;
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}

			HashMap<String, List<String>> selectResources = new HashMap<String, List<String>>();
			selectResources.put(FORMRESOURCETYPES.EQUIPMENT,Arrays.asList(equipmentsCategoryValue));

			HashMap<String,List<String>> fieldDetails = new HashMap<String,List<String>>();
			fieldDetails.put(FIELD_TYPES.NUMERIC, Arrays.asList(numericFN));
			fieldDetails.put(FIELD_TYPES.IDENTIFIER, Arrays.asList(identifierFN));

			com.project.safetychain.webapp.pageobjects.FormDesignerPage.FormFieldParams ffparams = 
					new com.project.safetychain.webapp.pageobjects.FormDesignerPage.FormFieldParams();
			ffparams.FieldDetails = fieldDetails;		
			ffparams.IdentiferType = IDENTIFIER_INPUT_TYPE.SELECT_ONE;
			ffparams.IdentifierValue = Arrays.asList("LINE1","LINE2");

			FormDesignParams fdparams = new FormDesignParams();
			fdparams.FormType = FORMTYPES.CHECK;
			fdparams.FormName = formName;
			fdparams.SelectResources = selectResources;
			fdparams.DesignFields = Arrays.asList(ffparams);
			fdparams.ReleaseForm = true;

			hp.clickFormDesignerMenu();
			if(!fdp.createAndReleaseForm(fdparams)) {
				TCGFailureMsg = "Could NOT create and release form - " + formName;
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}

			hp.clickFSQABrowserMenu();
			if(!fbp.selectResourceDropDownandNavigate(FSQARESOURCE.EQUIPMENT,FSQATAB.FORMS)) {
				TCGFailureMsg = "For equipment category, could NOT navigate to FSQABrowser > Forms tab";
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

			if(!fop.submitData(fd)) {
				TCGFailureMsg = "Failed to submit form";
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

	@Test(description="Validations || Verify the records cannot be edited or purged as long as "
			+ "they are associated with a Validation.")
	public void TestCase_33897() throws Exception{
		
		vp = hp.clickValidationsMenu();
		TestValidation.Equals(vp.error, 
				false, 
				"CLICKED on Validations menu", 
				"Could NOT click on Validations menu"); 

		aevp = vp.createNewValidation();
		TestValidation.Equals(aevp.error, 
				false, 
				"CLICKED on New Validation button", 
				"Could NOT click on New Validation button");

		HashMap<String,String> identifierDets = new HashMap<String, String>();
		identifierDets.put(identifierFN, "LINE1");
		
		VldtnDetsParams vdp = new VldtnDetsParams();
		vdp.Name = vldtnName;
		vdp.Type = VALIDATION_TYPE.NEW_PROCEDURES;
		vdp.Location = locationInstanceValue;
		vdp.Resource = equipmentsInstanceValue;
		vdp.Forms = Arrays.asList(formName);
		vdp.StartDate = dateTime.AddDaystoToddaysDate(-1, "MM/dd/yyyy HH:mm");
		vdp.EndDate = dateTime.AddDaystoToddaysDate(0, "MM/dd/yyyy HH:mm");
		vdp.Identifiers = identifierDets;
		boolean setDetails = aevp.setVldtnDetails(vdp);
		TestValidation.IsTrue(setDetails, 
							  "Details set for Validation - " + vdp.Name, 
							  "Could NOT set details for Validation - " + vdp.Name);

		boolean addVldtn = aevp.clickAddValidation();
		TestValidation.IsTrue(addVldtn, 
							  "Validation " + vdp.Name + " is SAVED successfully", 
							  "Could NOT create Validation " + vdp.Name);

		boolean completeVldtn = aevp.completeValidationWithResult(VALIDATION_RESULT.PASS, vldtnSummary);
		TestValidation.IsTrue(completeVldtn, 
							  "COMPLETED Validation " + vdp.Name + " with status " + VALIDATION_RESULT.PASS, 
							  "Could NOT complete Validation " + vdp.Name + " with status " + VALIDATION_RESULT.PASS);
		
		boolean verifySummary = controlActions.perform_CheckIfElementTextContains(aevp.CompleteVldtnDets, vldtnSummary);
		boolean verifyResult = controlActions.perform_CheckIfElementTextContains(aevp.CompleteVldtnDets, VALIDATION_RESULT.PASS);
		TestValidation.IsTrue((verifySummary && verifyResult), 
				  			  "VERIFIED summary details as " + vldtnSummary + " for validation " + vdp.Name, 
				  			  "Could NOT verify summary details as " + vldtnSummary + " for validation " + vdp.Name);
		
		fbp = hp.clickFSQABrowserMenu();
		boolean navigateRecs1 = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.EQUIPMENT,FSQATAB.RECORDS);
		TestValidation.IsTrue(navigateRecs1, 
	  			  			  "NAVIGATED to FSQA Browser > Equipment > Records tab", 
	  			  			  "Could NOT navigate to FSQA Browser > Equipment > Records tab");
		
		
		boolean openForm1 = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, formName);
		TestValidation.IsTrue(openForm1, 
	  			  			  "OPENED record for form " + formName, 
	  			  		  	  "Could NOT open record for form " + formName);
		
		String vldtnMsg = "Record cannot be edited";
		boolean verifyLock1 = frp.verifyRecordsDetails(FORMFIELDS.VALIDATIONLOCK, vldtnMsg);
		TestValidation.IsTrue(verifyLock1, 
	  			  			  "VERIFIED record is locked", 
	  			  			  "Could NOT verify record is locked");
		
		String classDetail = frp.EditRecordBtnDets.getAttribute("class");
		boolean editRecord1 = classDetail.contains("disabled");
		TestValidation.IsTrue(editRecord1, 
	  			  			  "VERIFIED editing of record is not possible", 
	  			  			  "Could NOT verify editing of record");
		
		vp = hp.clickValidationsMenu();
		TestValidation.Equals(vp.error, 
							  false, 
							  "CLICKED on Validations menu", 
							  "Could NOT click on Validations menu");
		
		aevp = vp.searchAndOpenVldtn(VLDTN_FIELDS.NAME, vldtnName);
		TestValidation.Equals(aevp.error, 
							  false, 
							  "OPENED validtion " + vldtnName, 
							  "Could NOT open validation " + vldtnName);
		
		boolean revalidate = aevp.revalidateValidation();
		TestValidation.IsTrue(revalidate, 
	  			  			  "REVALIDATED the validation and released locked records", 
	  			  			  "Could NOT verify whether validation is revalidated");
		
		fbp = hp.clickFSQABrowserMenu();
		boolean navigateRecs2 = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.EQUIPMENT,FSQATAB.RECORDS);
		TestValidation.IsTrue(navigateRecs2, 
	  			  			  "NAVIGATED to FSQA Browser > Equipment > Records tab", 
	  			  			  "Could NOT navigate to FSQA Browser > Equipment > Records tab");
		
		boolean openForm2 = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, formName);
		TestValidation.IsTrue(openForm2, 
	  			  			  "OPENED record for form " + formName, 
	  			  			  "Could NOT open record for form " + formName);
		
		boolean verifyLock2 = frp.verifyRecordsDetails(FORMFIELDS.VALIDATIONLOCK, vldtnMsg); //return false
		TestValidation.IsFalse(verifyLock2, 
	  			  		  	   "VERIFIED record is locked", 
	  			  			   "Could NOT verify record is locked");

		boolean editRecord2 = frp.clickEditRecordBtn();
		TestValidation.IsTrue(editRecord2, 
							   "VERIFIED editing of record is possible now", 
							   "Could NOT verify editing of record");
		
		boolean cancelEditMode = frp.clickCancelRecordBtn();
		TestValidation.IsTrue(cancelEditMode, 
							   "CLICKED on Cancel record button to close Edit mode", 
							   "Could NOT click on Cancel record button");
	}

	@AfterClass(alwaysRun = true)
	public void closeBrowser() throws InterruptedException {
		driver.close();
	}

}
