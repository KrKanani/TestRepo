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
import com.project.safetychain.webapp.pageobjects.AddEditValidationsPage.VALIDATION_EVENTS;
import com.project.safetychain.webapp.pageobjects.AddEditValidationsPage.VALIDATION_RESULT;
import com.project.safetychain.webapp.pageobjects.AddEditValidationsPage.VALIDATION_TABS;
import com.project.safetychain.webapp.pageobjects.AddEditValidationsPage.VALIDATION_TYPE;
import com.project.safetychain.webapp.pageobjects.AddEditValidationsPage.VldtnDetsParams;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.COLUMNHEADER;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.FSQARESOURCE;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.FSQATAB;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FIELD_TYPES;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FORMRESOURCETYPES;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FORMTYPES;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FormDesignParams;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FormFieldParams;
import com.project.safetychain.webapp.pageobjects.ManageLocationPage.LocationInstanceParams;
import com.project.safetychain.webapp.pageobjects.ManageResourcePage.RESOURCETYPES;
import com.project.safetychain.webapp.pageobjects.ManageResourcePage.ResourceDetailParams;
import com.project.safetychain.webapp.pageobjects.ResourceDesignerPage.CATEGORYTYPES;
import com.project.safetychain.webapp.pageobjects.ValidationsPage.VLDTN_FIELDS;
import com.project.safetychain.webapp.testcases.support.FormOperations;
import com.project.safetychain.webapp.testcases.support.FormOperations.FormDetails;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.utilities.CommonMethods;
import com.project.utilities.ControlActions;
import com.project.utilities.DateTime;

public class TCG_REG_Validation_BasicFlows extends TestBase{

	ControlActions controlActions;
	HomePage hp;
	LoginPage lp;
	DateTime dt = new DateTime();
	public static String locationCategoryValue;
	public static String locationInstanceValue;
	public static String customerCategoryValue;
	public static String customerInstanceValue;
	public static String formName;
	public static String numericFN = "Number";
	public static String identifierFN = "ID";
	public static String section = "Section";
	public static String vldtnName;
	public static String cmpltVldtnName;
	public static String vldtnSummary;
	public static String vldtnStartDate, vldtnEndDate;
	public static final int CMPLT_VLDTN_OPTN_COUNT = 2;

	@BeforeClass(alwaysRun = true)
	public void groupInit() throws InterruptedException {

		locationCategoryValue = CommonMethods.dynamicText("Loc_Cat");
		locationInstanceValue = CommonMethods.dynamicText("Loc_Inst");
		customerCategoryValue = CommonMethods.dynamicText("Cust_Cat");
		customerInstanceValue = CommonMethods.dynamicText("Cust_Inst");
		formName = CommonMethods.dynamicText("VldtnBsc");
		vldtnName = CommonMethods.dynamicText("Vldtn");
		cmpltVldtnName = CommonMethods.dynamicText("VldtnCmplt");
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

		//FORM - Creation and Release
		HashMap<String, List<String>> fields = new HashMap<String, List<String>>();
		fields.put(FIELD_TYPES.NUMERIC, Arrays.asList(numericFN));

		FormFieldParams ffp = new FormFieldParams();
		ffp.FieldDetails = fields;

		HashMap<String, List<String>> resource = new HashMap<String, List<String>>();
		resource.put(FORMRESOURCETYPES.CUSTOMERS, Arrays.asList(customerCategoryValue));

		FormDesignParams fdpDets = new FormDesignParams();
		fdpDets.FormType = FORMTYPES.CHECK;
		fdpDets.FormName = formName;
		fdpDets.SelectResources = resource;
		fdpDets.DesignFields = Arrays.asList(ffp);

		FormDesignerPage fdp = hp.clickFormDesignerMenu();
		if(!fdp.createAndReleaseForm(fdpDets)) {
			TCGFailureMsg = "Could NOT create unreleased form - " + formName;
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

		vldtnStartDate = dt.AddDaystoToddaysDate(-3, "MM/dd/yyyy hh:mm aa");
		vldtnEndDate = dt.AddDaystoToddaysDate(0, "MM/dd/yyyy hh:mm aa");
		
		VldtnDetsParams vdp = new VldtnDetsParams();
		vdp.Name = vldtnName;
		vdp.Type = VALIDATION_TYPE.NEW_PROCEDURES;
		vdp.Location = locationInstanceValue;
		vdp.Resource = customerInstanceValue;
		vdp.Forms = Arrays.asList(formName);
		vdp.StartDate = vldtnStartDate;
		vdp.EndDate = vldtnEndDate;
		if(!aevp.createAndAddValidation(vdp)) {
			TCGFailureMsg = "Could NOT add validation - " + vldtnName;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
		
	}

	@Test(description="Validations || Verify that after clicking on validation name, \"Edit Validation\""
			+ " console should get open")
	public void TestCase_34239() {
		
		try {
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
			
			boolean verifyUpdateBtn1 = controlActions.isElementDisplayed(aevp.UpdateVldtnBtn);
			TestValidation.IsTrue(verifyUpdateBtn1, 
								  "VERIFIED Update validation button is displayed", 
								  "Failed to verify Update validation button is displayed or not");
			
			boolean verifyClearBtn1 = controlActions.isElementDisplayed(aevp.ClearVldtnBtn);
			TestValidation.IsTrue(verifyClearBtn1, 
								  "VERIFIED Clear validation button is displayed", 
								  "Failed to verify Clear validation button is displayed or not");
			
			boolean verifyDeleteBtn1 = controlActions.isElementDisplayed(aevp.DeleteVldtnBtn);
			TestValidation.IsTrue(verifyDeleteBtn1, 
								  "VERIFIED Delete validation button is displayed", 
								  "Failed to verify Delete validation button is displayed or not");
			
			boolean verifyCopyBtn1 = controlActions.isElementDisplayed(aevp.CopyVldtnBtn);
			TestValidation.IsTrue(verifyCopyBtn1, 
								  "VERIFIED Copy validation button is displayed", 
								  "Failed to verify Copy validation button is displayed or not");
			
			vp = aevp.clickCloseValidation();
			TestValidation.Equals(vp.error, 
					  			  false, 
					  			  "CLOSED validtion " + vldtnName, 
					  			  "Could NOT close validation " + vldtnName);
			
			boolean setLocation = vp.selectLocationForGrid(locationInstanceValue);
			TestValidation.IsTrue(setLocation, 
								  "Location SET to value " + locationInstanceValue, 
								  "Failed to set location value to " + locationInstanceValue);
			
			aevp = vp.searchAndOpenVldtn(VLDTN_FIELDS.NAME, vldtnName);
			TestValidation.Equals(aevp.error, 
								  false, 
								  "OPENED validtion " + vldtnName, 
								  "Could NOT open validation " + vldtnName);
			
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
		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}
		}
	}
	
	@Test(description="Validations || Verify when user clicks on Cancel button it will clear the pop up"
			+ " and the user will return to the “Edit Validation” view.")
	public void TestCase_33891() {
		try {
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

			boolean openCompletePopup = aevp.clickCompleteValidation();
			TestValidation.IsTrue(openCompletePopup, 
								  "OPENED Complete Validation popup", 
								  "Failed to open Complete Validation popup");

			String vldtnResultClass = aevp.VldtnRsltPopupLbl.getAttribute("class");
			boolean verifyMandatory1 = vldtnResultClass.contains("reqdfieldLabel");
			TestValidation.IsTrue(verifyMandatory1, 
								  "VERIFIED 'Validation Result' is a mandatory field", 
								  "Could NOT verify 'Validation Result' is a mandatory field");

			String vldtnResultSummryClass = aevp.RsltSummPopupLbl.getAttribute("class");
			boolean verifyMandatory2 = vldtnResultSummryClass.contains("reqdfieldLabel");
			TestValidation.IsTrue(verifyMandatory2, 
								  "VERIFIED 'Validation Result Summary' is a mandatory field", 
								  "Could NOT verify 'Validation Result Summary' is a mandatory field");

			boolean cancelPopup = aevp.clickCancelCmplteValidation();
			TestValidation.IsTrue(cancelPopup, 
								  "CLICKED on Cancel button on Validation popup", 
								  "Failed to click on Cancel button on Validation popup");
		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}
		}
	}
	
	@Test(description="Validation || Verify on clicking complete button on pop-up the screen refreshes"
			+ " and displays the “Complete Validation” view.")
	public void TestCase_33892() {
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

			VldtnDetsParams vdp = new VldtnDetsParams();
			vdp.Name = cmpltVldtnName;
			vdp.Type = VALIDATION_TYPE.NEW_INGREDIENT;
			vdp.Location = locationInstanceValue;
			vdp.Resource = "ANY";
			vdp.Forms = Arrays.asList(formName);
			vdp.StartDate = vldtnStartDate;
			vdp.EndDate = vldtnEndDate;
			boolean addedVldtn = aevp.createAndAddValidation(vdp);
			TestValidation.IsTrue(addedVldtn, 
								  "ADDED Validation named " + cmpltVldtnName, 
								  "Failed to add Validation named " + cmpltVldtnName);

			boolean openCompletePopup = aevp.clickCompleteValidation();
			TestValidation.IsTrue(openCompletePopup, 
								  "OPENED Complete Validation popup", 
								  "Failed to open Complete Validation popup");

			int optionCount = aevp.verifyCmplteValidationOptions();
			TestValidation.IsTrue(optionCount==CMPLT_VLDTN_OPTN_COUNT, 
					  			  "VERIFIED 'Pass' and 'Fail' options are displayed", 
					         	  "Failed to verify whether 'Pass' and 'Fail' options are displayed or not");
			
			boolean setSummary = aevp.setComplteVldtnSummaryOnPopup(vldtnSummary);
			TestValidation.IsTrue(setSummary, 
								  "COULD set Validation summary as " + vldtnSummary, 
								  "Failed to set Validation summary as " + vldtnSummary);
			
			boolean completeValidation = aevp.clickComplteValidationOnPopup();
			TestValidation.IsTrue(completeValidation, 
								  "CLICKED on Complete Validation button on popup", 
								  "Failed to click on Complete Validation button on popup");
			
		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}
		}
	}
	
	@Test(description = "Validation || Verify on clicking Complete button the Validation status and "
			+ "Result Summary are listed at top.", dependsOnMethods = { "TestCase_33892" })
	public void TestCase_33893() {
		
		ValidationsPage vp = hp.clickValidationsMenu();
		AddEditValidationsPage aevp = vp.searchAndOpenVldtn(VLDTN_FIELDS.NAME, cmpltVldtnName);
		TestValidation.Equals(aevp.error, 
							  false, 
							  "OPENED validtion " + cmpltVldtnName, 
							  "Could NOT open validation " + cmpltVldtnName);
		
		boolean verifySummary = controlActions.perform_CheckIfElementTextContains(aevp.CompleteVldtnDets, vldtnSummary);
		boolean verifyResult = controlActions.perform_CheckIfElementTextContains(aevp.CompleteVldtnDets, VALIDATION_RESULT.PASS);
		TestValidation.IsTrue((verifySummary && verifyResult), 
				  			  "VERIFIED summary details as " + vldtnSummary + " for validation " + vldtnName, 
				  			  "Could NOT verify summary details as " + vldtnSummary + " for validation " + vldtnName);
		
	}
	
	@Test(description="Validations || Verify \"CLEAR\" functionality")
	public void TestCase_34243() {
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
			
			boolean clearBtn = aevp.ClearVldtnBtn.getAttribute("className").contains("disabled");
			TestValidation.IsTrue(clearBtn, 
								  "VERIFIED Clear button is disabled", 
								  "Failed to verify whether Clear button is disabled or not");
			
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
			
			boolean verifyTitle = aevp.VldtnTitle.getText().contains("Add New Validation");
			TestValidation.IsTrue(verifyTitle, 
								  "VERIFIED the title has value as 'Add New Validation'", 
								  "Failed to verify that the title has value as 'Add New Validation'");
			
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
			
			boolean clickClearBtn = aevp.clickClearValidation();
			TestValidation.IsTrue(clickClearBtn, 
				  			      "CLICKED on Clear button", 
				  			      "Failed to click on Clear button");
			
			boolean verifyType2 = aevp.VldtnTypeVal.getText().contains("Select");
			TestValidation.IsTrue(verifyType2, 
				  			      "VERIFIED Validation type field's value is 'Select'; the default value", 
				  			      "Failed to verify Validation type field's value");
			
			boolean verifyLocn2 = aevp.VldtnLocationVal.getText().contains("Select");
			TestValidation.IsTrue(verifyLocn2, 
				  			      "VERIFIED Validation location field's value is 'Select'; the default value", 
				  			      "Failed to verify Validation location field's value");
			
			boolean verifyRes2 = aevp.VldtnResourceVal.getText().contains("Select");
			TestValidation.IsTrue(verifyRes2, 
				  			      "VERIFIED Validation resource field's value  is 'Select'; the default value", 
				  			      "Failed to verify Validation resource field's value");
			
			SelectFormChk = controlActions.perform_GetElementByXPath(AddEditValidationsPageLocators.SELECT_FORM_CHK, 
							"FORMNAME", formName);
			TestValidation.IsTrue(SelectFormChk==null, 
				  			      "VERIFIED Validation form section's has forms displayed", 
				  			      "Failed to verify Validation form section");
		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}
		}
	}
	
	@Test(description="Validations || Verify tabs in for validation")
	public void TestCase_34275() {
		
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
		
		boolean clickHistory = aevp.selectValidationTab(VALIDATION_TABS.HISTORY);
		TestValidation.IsTrue(clickHistory, 
							  "OPENED History tab for validation", 
							  "Failed to open History tab for validation");
		
		List<String> details = new ArrayList<String>();
		details.add(VALIDATION_EVENTS.VALIDATION_CREATED);
		boolean verifyHistory = aevp.verifyHistoryDetails(details);
		TestValidation.IsTrue(verifyHistory, 
							  "VERIFIED History details displayed for validation as Created", 
							  "Failed to verify History details displayed for validation");
		
	}
	
	@Test(description="Validations || Verify the buttons")
	public void TestCase_34276() {
		
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
		
		boolean verifyUpdateBtn = controlActions.isElementDisplayed(aevp.UpdateVldtnBtn);
		TestValidation.IsTrue(verifyUpdateBtn, 
							  "VERIFIED Update validation button is displayed", 
							  "Failed to verify Update validation button is displayed or not");
		
		boolean verifyCompleteBtn = controlActions.isElementDisplayed(aevp.CompleteVldtnBtn);
		TestValidation.IsTrue(verifyCompleteBtn, 
							  "VERIFIED Complete validation button is displayed", 
							  "Failed to verify Complete validation button is displayed or not");
		
		boolean verifyDeleteBtn = controlActions.isElementDisplayed(aevp.DeleteVldtnBtn);
		TestValidation.IsTrue(verifyDeleteBtn, 
							  "VERIFIED Delete validation button is displayed", 
							  "Failed to verify Delete validation button is displayed or not");
		
		boolean verifyCopyBtn = controlActions.isElementDisplayed(aevp.CopyVldtnBtn);
		TestValidation.IsTrue(verifyCopyBtn, 
							  "VERIFIED Copy validation button is displayed", 
							  "Failed to verify Copy validation button is displayed or not");
		
		boolean verifyClearBtn = controlActions.isElementDisplayed(aevp.ClearVldtnBtn);
		TestValidation.IsTrue(verifyClearBtn, 
							  "VERIFIED Clear validation button is displayed", 
							  "Failed to verify Clear validation button is displayed or not");
		
	}
	
	@Test(description="Validations || Verify \"CLOSE\" button functionality.")
	public void TestCase_34282() {
		
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
		
		boolean verifyTitle = aevp.VldtnTitle.getText().contains(vldtnName);
		TestValidation.IsTrue(verifyTitle, 
							  "VERIFIED the title has value as " + vldtnName, 
							  "Failed to verify that the title has value as " + vldtnName);
		
		vp = aevp.clickCloseValidation();
		TestValidation.Equals(vp.error, 
				  			  false, 
				  			  "CLOSED validtion " + vldtnName, 
				  			  "Could NOT close validation " + vldtnName);
		
		boolean verifyNewBtn = controlActions.isElementDisplayed(vp.NewVldtnBtn);
		TestValidation.IsTrue(verifyNewBtn, 
							  "VERIFIED New validation button is displayed", 
							  "Failed to verify New validation button is displayed or not");
		
	}

	@AfterClass(alwaysRun = true)
	public void closeBrowser() throws InterruptedException {
		driver.close();
	}

}
