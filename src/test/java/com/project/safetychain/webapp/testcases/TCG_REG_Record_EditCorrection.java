package com.project.safetychain.webapp.testcases;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.project.safetychain.webapp.pageobjects.FSQABrowserPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserRecordsPage;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage;
import com.project.safetychain.webapp.pageobjects.HomePage;
import com.project.safetychain.webapp.pageobjects.LoginPage;
import com.project.safetychain.webapp.pageobjects.ManageLocationPage;
import com.project.safetychain.webapp.pageobjects.ManageResourcePage;
import com.project.safetychain.webapp.pageobjects.RecordSignoffPage;
import com.project.safetychain.webapp.pageobjects.ResourceDesignerPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.COLUMNHEADER;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.FSQARESOURCE;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.FSQATAB;
import com.project.safetychain.webapp.pageobjects.FSQABrowserRecordsPage.FIELD_OTHER_TYPES;
import com.project.safetychain.webapp.pageobjects.FSQABrowserRecordsPage.RecFieldProps;
import com.project.safetychain.webapp.pageobjects.FSQABrowserRecordsPage.UpdateFieldDets;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.ElementProperties;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FIELD_TYPES;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FORMRESOURCETYPES;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FORMTYPES;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FormDesignParams;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FormFieldParams;
import com.project.safetychain.webapp.pageobjects.ManageLocationPage.LocationInstanceParams;
import com.project.safetychain.webapp.pageobjects.ManageResourcePage.RESOURCETYPES;
import com.project.safetychain.webapp.pageobjects.ManageResourcePage.ResourceDetailParams;
import com.project.safetychain.webapp.pageobjects.RecordSignoffPage.FILTERTYPES;
import com.project.safetychain.webapp.pageobjects.RecordSignoffPage.RECORD_ANALYTIC_PANELS;
import com.project.safetychain.webapp.pageobjects.ResourceDesignerPage.CATEGORYTYPES;
import com.project.safetychain.webapp.testcases.support.FormOperations;
import com.project.safetychain.webapp.testcases.support.FormOperations.FieldProperties;
import com.project.safetychain.webapp.testcases.support.FormOperations.FormDetails;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.utilities.CommonMethods;
import com.project.utilities.ControlActions;


public class TCG_REG_Record_EditCorrection extends TestBase{
	
	ControlActions controlActions;
	public static String formName;
	public static String locationCategoryValue;	
	public static String locationInstanceValue;	
	public static String equipmentCategoryValue;
	public static String equipmentInstanceValue;
	public static String signOffCmmt;
	public static String numFieldName = "Numeric";
	public static String submitRecordCmmt = "update existing record";
	public static String fieldMin = "1", fieldTar = "2", fieldMax = "3", fieldUOM = "KG";
	public static String newNumFieldValue;

	@BeforeClass(alwaysRun = true)
	public void groupInit() throws InterruptedException {

		formName = CommonMethods.dynamicText("RViewer");
		locationCategoryValue = CommonMethods.dynamicText("Loc_Cat");	
		locationInstanceValue = CommonMethods.dynamicText("Loc_Inst");	
		equipmentCategoryValue = CommonMethods.dynamicText("Eqp_Cat");
		equipmentInstanceValue = CommonMethods.dynamicText("Eqp_Inst");
		signOffCmmt = CommonMethods.dynamicText("Cmmt1");

		driver = launchbrowser();
		controlActions = new ControlActions(driver);
		controlActions.getUrl(applicationUrl);
		
		LoginPage lp = new LoginPage(driver);
		HomePage hp = lp.performLogin(adminUsername, adminPassword);
		if(hp.error) {
			TCGFailureMsg = "Could NOT login to application";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
		
		hp.clickFSQABrowserMenu();
		//Category creation
		HashMap<String,String> categories = new HashMap<String, String>();
		categories.put(CATEGORYTYPES.EQUIPMENT, equipmentCategoryValue);
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
		instances.put(equipmentInstanceValue, true);
		ResourceDetailParams rd = new ResourceDetailParams();
		rd.CategoryName = equipmentCategoryValue;
		rd.CategoryType = RESOURCETYPES.EQUIPMENT;
		rd.NumberFieldValue = 30;
		rd.TextFieldValue = "ABC";
		rd.InstanceNames = instances;
		rd.LocationInstanceValue = locationInstanceValue;	
		ManageResourcePage mrp = hp.clickResourcesMenu();
		if(!mrp.createResourceLinkLocation(rd)) {
			TCGFailureMsg = "Could NOT create Instances for resource - " + equipmentCategoryValue;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
		
		//FORM - Creation and Release
		HashMap<String, List<String>> fields = new HashMap<String, List<String>>();
		fields.put(FIELD_TYPES.NUMERIC, Arrays.asList(numFieldName));

		FormFieldParams ffp = new FormFieldParams();
		ffp.FieldDetails = fields;

		HashMap<String, List<String>> resource = new HashMap<String, List<String>>();
		resource.put(FORMRESOURCETYPES.EQUIPMENT, Arrays.asList(equipmentCategoryValue));

		FormDesignParams fdpDets = new FormDesignParams();
		fdpDets.FormType = FORMTYPES.CHECK;
		fdpDets.FormName = formName;
		fdpDets.SelectResources = resource;
		fdpDets.DesignFields = Arrays.asList(ffp);
		fdpDets.ReleaseForm = false;

		FormDesignerPage fdp = hp.clickFormDesignerMenu();
		if(!fdp.createAndReleaseForm(fdpDets)) {
			TCGFailureMsg = "Could NOT create unreleased form - " + formName;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
		
		if (!fdp.navigateToReleaseForm_EditForm(formName)) {
			TCGFailureMsg = "Could NOT open edit form - " + formName;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
		
		ElementProperties settings = new ElementProperties();
		settings.COMPLIANCE_CONFIG = true;
		settings.COMPLIANCE_VALUES = new String[] { fieldMin, fieldTar, fieldMax, null };
		settings.ALLOW_CORRECTION = true;
		settings.MARK_RESOLVED = true;
		
		HashMap<String, ElementProperties> fieldSettings = new HashMap<String, ElementProperties>();
		fieldSettings.put(numFieldName, settings);

		if (!fdp.setFieldProperties(fieldSettings)) {
			TCGFailureMsg = "Could NOT set compliance properties for field - " + numFieldName;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		if (!fdp.releaseForm(formName)) {
			TCGFailureMsg = "Could NOT release form - " + formName;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
		
		FSQABrowserPage fbp = hp.clickFSQABrowserMenu();
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
		
		HashMap<String, List<String>> fillData = new HashMap<String, List<String>>();
		fillData.put(numFieldName,Arrays.asList("5"));
		
		//Field Property objects
		FieldProperties fp = new FieldProperties();
		fp.fieldName = numFieldName;
		fp.allowCorrectionsCheck = true;
		fp.allowCorrectionsValue = "Test Value";
		fp.isResolvedCheck = true;
		fp.isResolvedStatus = "No";
		fp.complianceStatusCheck = true;
		fp.complianceStatus = "Non-Compliant";

		//Form object
		FormDetails fd = new FormDetails();
		//Form level attachment
		fd.formLevelAttachmenttCheck = false;
		fd.fieldDetails = fillData;
		//Field property assignment  
		fd.fieldProperties = Arrays.asList(fp);
		FormOperations fo = new FormOperations(driver);
		if(!fo.submitData(fd)) {
			TCGFailureMsg = "Could NOT enter data and submit form for second time " + formName;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);			
		}
		
	}
	
	@Test(description="Record Signoff || Show Corrections || Edit the submitted record to have no correction data"
			+ " and view data displayed in corrections panel")
	public void TestCase_37497() throws Exception{
		
		HomePage hp = new HomePage(driver);
		hp.clickFSQABrowserMenu();
		RecordSignoffPage rsp = hp.clickRecordSignoffMenu();
		TestValidation.IsFalse(rsp.error, 
							  "NAVIGATED to Record Signoff page", 
				  			  "Failed to navigate to Record Signoff page");
		
		boolean closePopup = rsp.closeMsgPopup();
		TestValidation.IsTrue(closePopup, 
				  			  "CLOSED the 24 hours message popup", 
			  				  "Failed to close the 24 hours message popup");
		
		boolean applyFilter1 = rsp.applyFilterBySearch(FILTERTYPES.FORMS, formName);
		TestValidation.IsTrue(applyFilter1, 
							  "APPLIED filter for forms for form - " + formName, 
				  			  "Failed to apply filter for forms for form - " + formName);
		
		boolean clickShowCorrBtn1 = rsp.clickShowCorrectionsBtn();
		TestValidation.IsTrue(clickShowCorrBtn1, 
							  "CLICKED on Show Corrections button", 
				  			  "Failed to click on Show Corrections button");
		
		boolean beforeUnresolveCount = rsp.UnresolvdRecsCount.getText().trim().equals("1");
		TestValidation.IsTrue(beforeUnresolveCount, 
				  			  "VERIFIED count of unresolved records as 1", 
			  				  "Failed since count of unresolved records is found as " + rsp.UnresolvdRecsCount.getText().trim());
		
		boolean beforeResolveCount = rsp.ResolvdRecsCount.getText().trim().equals("0");
		TestValidation.IsTrue(beforeResolveCount, 
				  			  "VERIFIED count of resolved records as 0", 
			  				  "Failed since count of resolved records is found as " + rsp.ResolvdRecsCount.getText().trim());
		
		FSQABrowserRecordsPage frp = rsp.drilldownAnalytics(RECORD_ANALYTIC_PANELS.UNRESOLVED);
		TestValidation.Equals(false,
							  frp.error, 
							  "As we have 1 Unresolved record, we CAN drill down for Unresolved panel", 
							  "Failed to verify whether we can drill down for Unresolved panel or not");
		
		boolean editRecord = frp.clickEditRecordBtn();
		TestValidation.IsTrue(editRecord, 
							  "CLICKED on edit record", 
							  "Failed to click on edit");
		
		RecFieldProps fp = new RecFieldProps();
		fp.fieldName = numFieldName;
		fp.yesResolve = true;
		
		UpdateFieldDets ufd = new UpdateFieldDets();
		ufd.recFieldProps = Arrays.asList(fp);
		boolean updateValue = frp.updateFieldValues(ufd);
		TestValidation.IsTrue(updateValue, 
			  	  			  "UPDATED values for field type Numeric as " + newNumFieldValue, 
				  			  "Failed to update values for field type Numeric as " + newNumFieldValue);
		
		boolean submitRecord = frp.submitRecord(submitRecordCmmt);
		TestValidation.IsTrue(submitRecord, 
	  			  			  "SUBMITTED record with comment " + submitRecordCmmt, 
	  			  			  "Failed to submit record with comment " + submitRecordCmmt);
		
		boolean navigateBack = rsp.clickReviewRecsLink();
		TestValidation.IsTrue(navigateBack, 
				  			  "NAVIGATED back to Record Signoff > Review Records", 
							  "Failed to navigate back to Record Signoff > Review Records");
		
		boolean applyFilter2 = rsp.applyFilterBySearch(FILTERTYPES.FORMS, formName);
		TestValidation.IsTrue(applyFilter2, 
							  "APPLIED filter for forms for form - " + formName, 
				  			  "Failed to apply filter for forms for form - " + formName);
		
		boolean clickShowCorrBtn2 = rsp.clickShowCorrectionsBtn();
		TestValidation.IsTrue(clickShowCorrBtn2, 
							  "CLICKED on Show Corrections button", 
				  			  "Failed to click on Show Corrections button");
		
		boolean afterUnresolveCount = rsp.UnresolvdRecsCount.getText().trim().equals("0");
		TestValidation.IsTrue(afterUnresolveCount, 
				  			  "VERIFIED count of unresolved records as 0", 
			  				  "Failed since count of unresolved records is found as " + rsp.UnresolvdRecsCount.getText().trim());
		
		boolean afterResolveCount = rsp.ResolvdRecsCount.getText().trim().equals("1");
		TestValidation.IsTrue(afterResolveCount, 
				  			  "VERIFIED count of resolved records as 1", 
			  				  "Failed since count of resolved records is found as " + rsp.ResolvdRecsCount.getText().trim());
		
		frp = rsp.drilldownAnalytics(RECORD_ANALYTIC_PANELS.RESOLVED);
		TestValidation.Equals(false,
							  frp.error, 
							  "As we have 1 Resolved record, we CAN drill down for Resolved panel", 
							  "Failed to verify whether we can drill down for Resolved panel or not");
		
		String newResolveValue = frp.getFieldSpecificValue(numFieldName,FIELD_OTHER_TYPES.RESOLVED);
		TestValidation.IsTrue((newResolveValue.trim().equalsIgnoreCase("Yes")), 
							  "Retrieved value for the field " + numFieldName + " for Resolved as " + newResolveValue, 
							  "Failed to retrieve value for the field " + numFieldName + " for Resolved");

	}
	
	@AfterClass(alwaysRun = true)
	public void closeBrowser() throws InterruptedException {
		driver.close();
	}
}

