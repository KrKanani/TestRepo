package com.project.safetychain.webapp.testcases;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.project.safetychain.webapp.pageobjects.DocumentManagementPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserDocumentsPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserDocumentsPage.FLD_OTHER_TYPES;
import com.project.safetychain.webapp.pageobjects.FSQABrowserFormsPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage;
import com.project.safetychain.webapp.pageobjects.FormDesignerPageLocator;
import com.project.safetychain.webapp.pageobjects.HomePage;
import com.project.safetychain.webapp.pageobjects.LoginPage;
import com.project.safetychain.webapp.pageobjects.ManageLocationPage;
import com.project.safetychain.webapp.pageobjects.ManageLocationPage.LocationInstanceParams;
import com.project.safetychain.webapp.pageobjects.ManageResourcePage;
import com.project.safetychain.webapp.pageobjects.ManageResourcePage.RESOURCETYPES;
import com.project.safetychain.webapp.pageobjects.ManageResourcePage.ResourceDetailParams;
import com.project.safetychain.webapp.pageobjects.ResourceDesignerPage;
import com.project.safetychain.webapp.pageobjects.ResourceDesignerPage.CATEGORYTYPES;
import com.project.safetychain.webapp.pageobjects.UserManagerPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.COLUMNHEADER;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.COLUMNSETTING;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.FORM_TYPES;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.FSQARESOURCE;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.FSQATAB;
import com.project.safetychain.webapp.pageobjects.FSQABrowserRecordsPage;
import com.project.safetychain.webapp.testcases.support.FormOperations;
import com.project.safetychain.webapp.testcases.support.FormOperations.FormDetails;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.ELEMENT_INPUT_TYPES;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.ELEMENT_TYPE;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FIELD_TYPES;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FORMRESOURCETYPES;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FORMTYPES;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FORM_NEXT_PAGE;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FormDesignParams;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FormFieldParams;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.PreviewFormDetails;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.SUMMARY_TABLE_HEADER;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.utilities.CommonMethods;
import com.project.utilities.ControlActions;
import com.project.utilities.DateTime;

public class TCG_REG_FormDesigner_MultiResource extends TestBase{
	ControlActions controlActions;
	LoginPage lgnPge;
	ResourceDesignerPage resourceDesigner;
	ManageResourcePage manageResource;
	ManageLocationPage locations;
	FormDesignerPage fdp;
	HomePage hp;

	public static String locationCategoryValue;
	public static String fieldGroupName = "Test Group";
	public static String fieldSectionName = "Test Section";
	public static String displayName = null;
	public static String currentDate;
	public static String timezoneCode = null;
	public static String fieldParagraphName = "Test Paragraph";
	public static String fieldSingleLineTxtName = "Test Single Line Text";
	public static String fieldSelectOneName = "Test Select One";
	public static String fieldSelectMultName = "Test Select Multiple";
	public static String fieldNumericName = "Test Numeric";
	public static String fieldTimeName = "Test Time";
	public static String fieldDateTimeName = "Test Date Time";
	public static String resourceType = "Customers";
	public static String resourceCategoryValue;
	public static String resourceInstance1Value;
	public static String resourceInstance2Value;
	public static String locationInstanceValue;

	@BeforeClass(alwaysRun = true)
	public void groupInit() throws Exception {

		locationCategoryValue = CommonMethods.dynamicText("Loc_Cat");
		resourceCategoryValue = CommonMethods.dynamicText("Cust_Cat");
		resourceInstance1Value = CommonMethods.dynamicText("Cust_Inst1");
		resourceInstance2Value = CommonMethods.dynamicText("Cust_Inst2");
		locationInstanceValue = CommonMethods.dynamicText("Loc_Inst");
		
		driver = launchbrowser();
		controlActions = new ControlActions(driver);
		controlActions.getUrl(applicationUrl);
		lgnPge = new LoginPage(driver);
		resourceDesigner = new ResourceDesignerPage(driver);
		manageResource = new ManageResourcePage(driver);
		locations = new ManageLocationPage(driver);
		fdp = new FormDesignerPage(driver);

		//login
		hp = lgnPge.performLogin(adminUsername, adminPassword);
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
		
		//Category creation
		HashMap<String,String> categories = new HashMap<String, String>();
		categories.put(CATEGORYTYPES.CUSTOMERS, resourceCategoryValue);
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
		instances.put(resourceInstance1Value, true);
		instances.put(resourceInstance2Value, true);
		ResourceDetailParams rd = new ResourceDetailParams();
		rd.CategoryName = resourceCategoryValue;
		rd.CategoryType = RESOURCETYPES.CUSTOMERS;
		rd.NumberFieldValue = 30;
		rd.TextFieldValue = "ABC";
		rd.InstanceNames = instances;
		rd.LocationInstanceValue = locationInstanceValue;
		ManageResourcePage mrp = hp.clickResourcesMenu();
		if(!mrp.createResourceLinkLocation(rd)) {
			TCGFailureMsg = "Could NOT create Instances for resource - " + resourceCategoryValue;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
		
	}

	@Test(description="Form Designer can select Resources form can be used with. (feature 491)")
	public void TestCase_5229() {

		try {
			
			String auditFormName = CommonMethods.dynamicText("AdtFrm_Rsrc");
			
			//Open 'Form Designer'
			FormDesignerPage fdp = hp.clickFormDesignerMenu();
			boolean selectAdt = fdp.selectFormType(FORMTYPES.AUDIT);
			TestValidation.IsTrue(selectAdt, 
								  "SELECTED Audit form type successfully", 
								  "Failed to Select Audit form type");

			//Selection of resource
			boolean selectResource = fdp.selectResource(resourceType,resourceCategoryValue,resourceInstance1Value);
			TestValidation.IsTrue(selectResource, 
								  "SELECTED Resources under '" + resourceCategoryValue  +"' successfully", 
								  "Failed to Select resources under '" + resourceCategoryValue + "'");
	
			//Go to 'Form Design'
			boolean clickOnNextButton = fdp.clickOnNextButton(fdp.DesignFormPg, FORM_NEXT_PAGE.DESIGN_FORM);
			TestValidation.IsTrue(clickOnNextButton, 
								  "CLICKED On Next Button successfully", 
								  "Failed to Click On Next Button");
	
			boolean setName = fdp.setFormName(auditFormName);
			TestValidation.IsTrue(setName, 
					  			  "Form name SET as " + auditFormName, 
					  			  "Failed to Set form name as " + auditFormName);
			
			//Add Section to Form
			WebElement SectionElement = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FORM_ELEMENT,
					"FORMELEMENT",ELEMENT_INPUT_TYPES.SECTION);
			controlActions.dragdrop(SectionElement, fdp.FieldTypeDropAreaFormLevel);
			boolean setSectionFieldValue = fdp.setFieldName(ELEMENT_INPUT_TYPES.SECTION, fieldSectionName);
			TestValidation.IsTrue(setSectionFieldValue, 
								  "ADDED Section " + fieldSectionName + " successfully", 
								  "Failed to Add Section " + fieldSectionName + " successfully");
			
			// Add Numeric field to Section
			boolean addNumericFieldInSection = fdp.dragDropElementInSectionGroup(ELEMENT_TYPE.FIELD_TYPES,
					FIELD_TYPES.NUMERIC, fieldSectionName);
			TestValidation.IsTrue(addNumericFieldInSection, 
								  "ADDED 'Numeric' field to 'Section' " + fieldSectionName, 
								  "Failed to Add 'Numeric' field to 'Section' " + fieldSectionName);

			boolean setNumericFldName = fdp.setTextBoxValue(FIELD_TYPES.NUMERIC, fieldNumericName);	
			TestValidation.IsTrue(setNumericFldName, 
								  "ADDED name '" + fieldNumericName + "' to Numeric field successfully", 
								  "Failed to Add name '" + fieldNumericName + "' Numeric field");
			
			boolean openCmpliance = fdp.openComplianceForField(fieldNumericName);
			TestValidation.IsTrue(openCmpliance, 
								  "OPENED Compliance For Numeric Field successfully", 
								  "Failed to Open Compliance For Numeric Field");
			
			boolean verifyRsrc1 = fdp.CmplnceResrcNameLst.get(0).getAttribute("innerText").contains(resourceInstance1Value);
			boolean verifyRsrc2 = fdp.CmplnceResrcNameLst.get(1).getAttribute("innerText").contains(resourceInstance2Value);
			TestValidation.IsTrue((verifyRsrc1 && verifyRsrc2), 
								  "VERIFIED resource names under Compliance For Numeric Field successfully", 
								  "Failed to Verify resource names under Compliance For Numeric Field");
			
			boolean clickPreviewButton = fdp.clickPreviewButton();
			TestValidation.IsTrue(clickPreviewButton, 
								  "CLICKED Preview button Successfully", 
								  "Failed to Click Preview button");
			
			boolean selectRsrc1 = fdp.selectResourceInPreviewForm(resourceInstance1Value);
			TestValidation.IsTrue(selectRsrc1, 
								  "HAS SET resource to - " + resourceInstance1Value, 
								  "Failed to Set resource to - " + resourceInstance1Value);
			
			boolean verifySelectRsrc1 = fdp.verifySelectedResourceInPreviewForm(resourceInstance1Value);
			TestValidation.IsTrue(verifySelectRsrc1, 
								  "VERIFIED selected resource is - " + resourceInstance1Value, 
								  "Failed to verify selected resource is - " + resourceInstance1Value);
			
			boolean selectRsrc2 = fdp.selectResourceInPreviewForm(resourceInstance2Value);
			TestValidation.IsTrue(selectRsrc2, 
								  "HAS SET resource to - " + resourceInstance2Value, 
								  "Failed to Set resource to - " + resourceInstance2Value);
			
			boolean verifySelectRsrc2 = fdp.verifySelectedResourceInPreviewForm(resourceInstance2Value);
			TestValidation.IsTrue(verifySelectRsrc2, 
								  "VERIFIED selected resource is - " + resourceInstance2Value, 
								  "Failed to verify selected resource is - " + resourceInstance2Value);
			
		}
		finally {
			if(hp.clickHomepageLinkAndBypassPopup().error){
				TCGFailureMsg = "Could NOT land on FSQA Browser page after clicking on Home link";
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}
		}
	}	
	
	@Test(description="Form Designer can manage document links on Related Documents element via the Properties Panel.(feature 326)")
	public void TestCase_5208() {

		try {
			
			String docTypeName = CommonMethods.dynamicText("Doc");
			String documentName = "upload.png";
			String filePath = System.getProperty("user.dir") + "\\test-data-files\\UploadDocuments\\"+documentName;
			String auditFormName = CommonMethods.dynamicText("AdtFrm_RltdDocs");
			
			// Create Document in Doc Type
			DocumentManagementPage dmp = hp.clickdocumentsmenu();
			boolean createDocType = dmp.docUploadinDocType(docTypeName,filePath);
			TestValidation.IsTrue(createDocType, 
						          "CREATED Document type - " + docTypeName, 
							      "Failed to create Document type " + docTypeName);
			
			//Open 'Form Designer'
			FormDesignerPage fdp = hp.clickFormDesignerMenu();
			boolean selectAdt = fdp.selectFormType(FORMTYPES.AUDIT);
			TestValidation.IsTrue(selectAdt, 
								  "SELECTED Audit form type successfully", 
								  "Failed to Select Audit form type");

			//Selection of resource
			boolean selectResource = fdp.selectResource(resourceType,resourceCategoryValue,resourceInstance1Value);
			TestValidation.IsTrue(selectResource, 
								  "SELECTED Resources under '" + resourceCategoryValue  +"' successfully", 
								  "Failed to Select resources under '" + resourceCategoryValue + "'");
	
			//Go to 'Form Design'
			boolean clickOnNextButton = fdp.clickOnNextButton(fdp.DesignFormPg, FORM_NEXT_PAGE.DESIGN_FORM);
			TestValidation.IsTrue(clickOnNextButton, 
								  "CLICKED On Next Button successfully", 
								  "Failed to Click On Next Button");
	
			boolean setName = fdp.setFormName(auditFormName);
			TestValidation.IsTrue(setName, 
					  			  "Form name SET as " + auditFormName, 
					  			  "Failed to Set form name as " + auditFormName);
			
			controlActions.dragdrop(fdp.RelatedDocsDbl, fdp.HeaderLevel);
			boolean addDocBtn = fdp.clickAddNewDocBtn();
			TestValidation.IsTrue(addDocBtn, 
					  			  "CLICKED Add New document button successfully", 
					  			  "Failed to Click Add New document button");
			
			boolean selectRelatdDocs = fdp.addRelatedDocumentFromPopup(docTypeName, documentName);
			TestValidation.IsTrue(selectRelatdDocs, 
					  			  "SELECTED document '" + documentName + "' from Document Type - " + docTypeName, 
					  			  "Failed to Select document'" + documentName + "' from Document Type - " + docTypeName);
			
			boolean saveDocBtn = fdp.clickSaveDocBtn();
			TestValidation.IsTrue(saveDocBtn, 
					  			  "CLICKED Save document button successfully", 
					  			  "Failed to Click Save document button");
			
			boolean verifyDocAdded = fdp.verifyAddedRelatedDocument(documentName);
			TestValidation.IsTrue(verifyDocAdded, 
					  			  "VERIFIED document '" + documentName + "' is added to Related Docs", 
					  			  "Failed to Verify document'" + documentName + "' is added to Related Docs");
			
			boolean delteReltdDocs = fdp.clickDeleteReltdDocsBtn();
			TestValidation.IsTrue(delteReltdDocs, 
					  			  "CLICKED on Delete Related document button successfully", 
					  			  "Failed to Click Delete Related document button");
			
			boolean verifyDocDeleted = fdp.verifyAddedRelatedDocument(documentName);
			TestValidation.IsFalse(verifyDocDeleted, 
					  			  "VERIFIED document '" + documentName + "' has been deleted/removed", 
					  			  "Failed to Verify document'" + documentName + "' has been deleted/removed");
			
		}
		finally {
			if(hp.clickHomepageLinkAndBypassPopup().error){
				TCGFailureMsg = "Could NOT land on FSQA Browser page after clicking on Home link";
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}
		}
	}	
	
	@Test(description="Audit Form - Verify User is able to Add/ Configure  form elements and release  form . ")
	public void TestCase_37697() {

		try {
			
			String auditFormName = CommonMethods.dynamicText("AdtFrm_Summary");
			String overallScoreTxt = "Overall Score";
			String secAOptionswithWeight[][] = {{"10","10"},{"20","20"}};
			String secBOptionswithWeight[][] = {{"30","30"},{"40","40"}};
			
			//Open 'Form Designer'
			FormDesignerPage fdp = hp.clickFormDesignerMenu();
			boolean selectAdt = fdp.selectFormType(FORMTYPES.AUDIT);
			TestValidation.IsTrue(selectAdt, 
								  "SELECTED Audit form type successfully", 
								  "Failed to Select Audit form type");

			//Selection of resource
			boolean selectResource = fdp.selectResource(resourceType,resourceCategoryValue,resourceInstance1Value);
			TestValidation.IsTrue(selectResource, 
								  "SELECTED Resources under '" + resourceCategoryValue  +"' successfully", 
								  "Failed to Select resources under '" + resourceCategoryValue + "'");
	
			//Go to 'Form Design'
			boolean clickOnNextButton = fdp.clickOnNextButton(fdp.DesignFormPg, FORM_NEXT_PAGE.DESIGN_FORM);
			TestValidation.IsTrue(clickOnNextButton, 
								  "CLICKED On Next Button successfully", 
								  "Failed to Click On Next Button");
	
			boolean setName = fdp.setFormName(auditFormName);
			TestValidation.IsTrue(setName, 
					  			  "Form name SET as " + auditFormName, 
					  			  "Failed to Set form name as " + auditFormName);
			
			controlActions.dragdrop(fdp.Summarydbl, fdp.HeaderLevel);
			//Add Section A to Form
			WebElement SectionAElement = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FORM_ELEMENT,
					"FORMELEMENT",ELEMENT_INPUT_TYPES.SECTION);
			controlActions.dragdrop(SectionAElement, fdp.FieldTypeDropAreaFormLevel);
			boolean setSectionAFieldValue = fdp.setFieldName(ELEMENT_INPUT_TYPES.SECTION, fieldSectionName);
			TestValidation.IsTrue(setSectionAFieldValue, 
								  "ADDED Section " + fieldSectionName + " successfully", 
								  "Failed to Add Section " + fieldSectionName + " successfully");
			
			//Add Select Multiple Field 
			boolean addSelectMultFieldInSection = fdp.dragDropElementInSectionGroup(ELEMENT_TYPE.FIELD_TYPES,
					FIELD_TYPES.SELECT_MULTIPLE, fieldSectionName);
			boolean setSelectMultipleFldName = fdp.setTextBoxValue(FIELD_TYPES.SELECT_MULTIPLE, fieldSelectMultName);	
			TestValidation.IsTrue(addSelectMultFieldInSection && setSelectMultipleFldName, 
								  "ADDED Select Multiple field successfully with name - " + fieldSelectMultName, 
								  "Failed to Add Select Multiple field");
			
			boolean optnsWithWeightSecA = fdp.setFieldptions("100", null, secAOptionswithWeight);
			TestValidation.IsTrue(optnsWithWeightSecA, 
								  "Options with Weights SET for '" + fieldSelectMultName + "' field successfully", 
								  "Failed to Add Options with Weights SET for '" + fieldSelectMultName + "' field");
			
			//Add Select One Field 
			boolean addSelectOneFieldInSection = fdp.dragDropElementInSectionGroup(ELEMENT_TYPE.FIELD_TYPES,
					FIELD_TYPES.SELECT_ONE, fieldSectionName);
			boolean setSelectOneFldName = fdp.setTextBoxValue(FIELD_TYPES.SELECT_ONE, fieldSelectOneName);	
			TestValidation.IsTrue(addSelectOneFieldInSection && setSelectOneFldName, 
								  "ADDED Select One field successfully with name - " + fieldSelectOneName, 
								  "Failed to Add One Multiple field");
			
			boolean optnsWithWeightSecB = fdp.setFieldptions("100", null, secBOptionswithWeight);
			TestValidation.IsTrue(optnsWithWeightSecB, 
								  "Options with Weights SET for '" + fieldSelectOneName + "' field successfully", 
								  "Failed to Add Options with Weights SET for '" + fieldSelectOneName + "' field");
			
			boolean clickPreviewButton = fdp.clickPreviewButton();
			TestValidation.IsTrue(clickPreviewButton, 
								  "CLICKED Preview button Successfully", 
								  "Failed to Click Preview button");
			
			LinkedHashMap<String, List<String>> fieldValues = new LinkedHashMap<String, List<String>>();
			fieldValues.put(fieldSelectMultName,Arrays.asList("10"));
			fieldValues.put(fieldSelectOneName,Arrays.asList("30"));
			
			PreviewFormDetails pfd = new PreviewFormDetails();
			pfd.fieldDetails = fieldValues;
			pfd.resourceName = resourceInstance1Value;
			pfd.closePreview = false;
			boolean enterDataForPreviewForm = fdp.enterDataForPreviewForm(pfd);
			TestValidation.IsTrue(enterDataForPreviewForm, 
								  "Data SET for Preview form successfully", 
								  "Failed to Set data for Preview form");
			
			FormOperations fo = new FormOperations(driver);
			String PrePPvalSec = fo.getSummaryForSecDetails(SUMMARY_TABLE_HEADER.POINTS_POSSIBLE, fieldSectionName);
			String PrePEvalSec = fo.getSummaryForSecDetails(SUMMARY_TABLE_HEADER.POINTS_EARNED, fieldSectionName);
			String PreScorevalSec = fo.getSummaryForSecDetails(SUMMARY_TABLE_HEADER.SCORE, fieldSectionName);
			TestValidation.IsTrue(PrePPvalSec.contains("200") && PrePEvalSec.contains("40") && PreScorevalSec.contains("20.00"), 
								  "For Preview, VERIFIED Summary values for section - " + fieldSectionName, 
								  "For Preview, Failed to verify Summary values for section - " + fieldSectionName);
			
			String PrePPvalOverall = fo.getSummaryForSecDetails(SUMMARY_TABLE_HEADER.POINTS_POSSIBLE, overallScoreTxt);
			String PrePEvalOverall = fo.getSummaryForSecDetails(SUMMARY_TABLE_HEADER.POINTS_EARNED, overallScoreTxt);
			String PreScorevalOverall = fo.getSummaryForSecDetails(SUMMARY_TABLE_HEADER.SCORE, overallScoreTxt);
			TestValidation.IsTrue(PrePPvalOverall.contains("200") && PrePEvalOverall.contains("40") && PreScorevalOverall.contains("20.00 %"), 
								  "For Preview, VERIFIED Summary values for Overall Score", 
								  "For Preview, Failed to verify Summary values for Overall Score");
			
			boolean clickClosePreview = fdp.clickClosePreviewBtn();
			TestValidation.IsTrue(clickClosePreview, 
								  "CLICKED on Close Preview button Successfully", 
								  "Failed to Click on Close Preview button");
			
			boolean nextToReleaseForm = fdp.clickOnNextButton(fdp.ReleaseFormPg, FORM_NEXT_PAGE.RELEASE_FORM);
			TestValidation.IsTrue(nextToReleaseForm, 
								  "NAVIGATED to 'Release Form'", 
								  "Failed to Navigate to 'Release Form'");
			
			boolean releaseForm = fdp.releaseForm(auditFormName);
			TestValidation.IsTrue(releaseForm, 
							       "SHOULD release form - " + auditFormName, 
								   "Failed since uncertain that the form " + auditFormName + " did/did not release'");
			
			FSQABrowserPage fbp = hp.clickFSQABrowserMenu();
			boolean navigateToforms = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.CUSTOMERS, FSQATAB.FORMS);
			TestValidation.IsTrue(navigateToforms, 
								  "For customer category, NAVIGATED to FSQABrowser > Forms tab", 
								  "Failed to navigate to FSQABrowser > Forms tab");
					
			boolean applyFilterAndOpenForm = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, auditFormName);
			TestValidation.IsTrue(applyFilterAndOpenForm, 
								  "OPENED form - " + auditFormName, 
								  "Failed to open form - " + auditFormName);
			
			boolean setResrc = fo.selectLocationResource(null, resourceInstance1Value);
			TestValidation.IsTrue(setResrc, 
								  "Resource SET to - " + resourceInstance1Value, 
								  "Failed to set Resource to - " + resourceInstance1Value);
			
			boolean fillSMData = fo.fillData(fieldSelectMultName, Arrays.asList("20"), null, false);
			TestValidation.IsTrue(fillSMData, 
								  "APPLIED value 20 to field - " + fieldSelectMultName, 
								  "Failed to apply value to field - " + fieldSelectMultName);
			
			boolean fillSOData = fo.fillData(fieldSelectOneName, Arrays.asList("40"), null, false);
			TestValidation.IsTrue(fillSOData, 
								  "APPLIED value 40 to field - " + fieldSelectOneName, 
								  "Failed to apply value to field - " + fieldSelectOneName);
			
			String FrmPPvalSec = fo.getSummaryForSecDetails(SUMMARY_TABLE_HEADER.POINTS_POSSIBLE, fieldSectionName);
			String FrmPEvalSec = fo.getSummaryForSecDetails(SUMMARY_TABLE_HEADER.POINTS_EARNED, fieldSectionName);
			String FrmScorevalSec = fo.getSummaryForSecDetails(SUMMARY_TABLE_HEADER.SCORE, fieldSectionName);
			TestValidation.IsTrue(FrmPPvalSec.contains("200") && FrmPEvalSec.contains("60") && FrmScorevalSec.contains("30.00"), 
								  "For Form submission, VERIFIED Summary values for section - " + fieldSectionName, 
								  "For Form submission, Failed to verify Summary values for section - " + fieldSectionName);
			
			String FrmPPvalOverall = fo.getSummaryForSecDetails(SUMMARY_TABLE_HEADER.POINTS_POSSIBLE, overallScoreTxt);
			String FrmPEvalOverall = fo.getSummaryForSecDetails(SUMMARY_TABLE_HEADER.POINTS_EARNED, overallScoreTxt);
			String FrmScorevalOverall = fo.getSummaryForSecDetails(SUMMARY_TABLE_HEADER.SCORE, overallScoreTxt);
			TestValidation.IsTrue(FrmPPvalOverall.contains("200") && FrmPEvalOverall.contains("60") && FrmScorevalOverall.contains("30.00 %"), 
								  "For Form submission, VERIFIED Summary values for Overall Score", 
								  "For Form submission	, Failed to verify Summary values for Overall Score");	
			
			FSQABrowserFormsPage ffp = new FSQABrowserFormsPage(driver);
			boolean submitForm = ffp.submitForm();
			TestValidation.IsTrue(submitForm, 
								  "SUBMITTED form - " + auditFormName, 
								  "Failed to submit form - " + auditFormName);
			
			boolean navigateToRecs = fbp.navigateToFSQATab(FSQATAB.RECORDS);
			TestValidation.IsTrue(navigateToRecs, 
								  "For customer category, NAVIGATED to FSQABrowser > Records tab", 
								  "Failed to navigate to FSQABrowser > Records tab");
					
			boolean applyFilterAndOpenRec = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, auditFormName);
			TestValidation.IsTrue(applyFilterAndOpenRec, 
								  "OPENED record - " + auditFormName, 
								  "Failed to open record - " + auditFormName);
			
			String RecPPvalSec = fo.getSummaryForSecDetails(SUMMARY_TABLE_HEADER.POINTS_POSSIBLE, fieldSectionName);
			String RecPEvalSec = fo.getSummaryForSecDetails(SUMMARY_TABLE_HEADER.POINTS_EARNED, fieldSectionName);
			String RecScorevalSec = fo.getSummaryForSecDetails(SUMMARY_TABLE_HEADER.SCORE, fieldSectionName);
			TestValidation.IsTrue(RecPPvalSec.contains("200") && RecPEvalSec.contains("60") && RecScorevalSec.contains("30.00"), 
								  "For Form submission, VERIFIED Summary values for section - " + fieldSectionName, 
								  "For Form submission, Failed to verify Summary values for section - " + fieldSectionName);
			
			String RecPPvalOverall = fo.getSummaryForSecDetails(SUMMARY_TABLE_HEADER.POINTS_POSSIBLE, overallScoreTxt);
			String RecPEvalOverall = fo.getSummaryForSecDetails(SUMMARY_TABLE_HEADER.POINTS_EARNED, overallScoreTxt);
			String RecScorevalOverall = fo.getSummaryForSecDetails(SUMMARY_TABLE_HEADER.SCORE, overallScoreTxt);
			TestValidation.IsTrue(RecPPvalOverall.contains("200") && RecPEvalOverall.contains("60") && RecScorevalOverall.contains("30.00 %"), 
								  "For Form submission, VERIFIED Summary values for Overall Score", 
								  "For Form submission	, Failed to verify Summary values for Overall Score");	
			

		}
		finally {
			if(hp.clickHomepageLinkAndBypassPopup().error){
				TCGFailureMsg = "Could NOT land on FSQA Browser page after clicking on Home link";
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}
		}
	}	
	
	@Test(description="Verify data for forms displayed in FSQA forms ( Version number, Modified by, Modified on , form type )")
	public void TestCase_37723() {
		
		WebElement FormVersionLbl = null;

		try {
			
			String auditFormName = CommonMethods.dynamicText("AdtFrm_Details");
			
			//Open 'Form Designer'
			FormDesignerPage fdp = hp.clickFormDesignerMenu();
			boolean selectAdt = fdp.selectFormType(FORMTYPES.AUDIT);
			TestValidation.IsTrue(selectAdt, 
								  "SELECTED Audit form type successfully", 
								  "Failed to Select Audit form type");

			//Selection of resource
			boolean selectResource = fdp.selectResource(resourceType,resourceCategoryValue,resourceInstance1Value);
			TestValidation.IsTrue(selectResource, 
								  "SELECTED Resources under '" + resourceCategoryValue  +"' successfully", 
								  "Failed to Select resources under '" + resourceCategoryValue + "'");
	
			//Go to 'Form Design'
			boolean clickOnNextButton = fdp.clickOnNextButton(fdp.DesignFormPg, FORM_NEXT_PAGE.DESIGN_FORM);
			TestValidation.IsTrue(clickOnNextButton, 
								  "CLICKED On Next Button successfully", 
								  "Failed to Click On Next Button");
	
			boolean setName = fdp.setFormName(auditFormName);
			TestValidation.IsTrue(setName, 
					  			  "Form name SET as " + auditFormName, 
					  			  "Failed to Set form name as " + auditFormName);
			
			//Add Section A to Form
			WebElement SectionAElement = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FORM_ELEMENT,
					"FORMELEMENT",ELEMENT_INPUT_TYPES.SECTION);
			controlActions.dragdrop(SectionAElement, fdp.FieldTypeDropAreaFormLevel);
			boolean setSectionAFieldValue = fdp.setFieldName(ELEMENT_INPUT_TYPES.SECTION, fieldSectionName);
			TestValidation.IsTrue(setSectionAFieldValue, 
								  "ADDED Section " + fieldSectionName + " successfully", 
								  "Failed to Add Section " + fieldSectionName + " successfully");
			
			//Add Time Field 
			boolean addTimeFieldInSection = fdp.dragDropElementInSectionGroup(ELEMENT_TYPE.FIELD_TYPES,
					FIELD_TYPES.TIME, fieldSectionName);
			boolean setTimeFldName = fdp.setTextBoxValue(FIELD_TYPES.TIME, fieldTimeName);	
			TestValidation.IsTrue(addTimeFieldInSection && setTimeFldName, 
								  "ADDED Time field successfully with name - " + fieldTimeName, 
								  "Failed to Add Time field");
			
			boolean nextToReleaseForm1 = fdp.clickOnNextButton(fdp.ReleaseFormPg, FORM_NEXT_PAGE.RELEASE_FORM);
			TestValidation.IsTrue(nextToReleaseForm1, 
								  "NAVIGATED to 'Release Form'", 
								  "Failed to Navigate to 'Release Form'");
			
			FormVersionLbl = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FORM_VERSION_LBL,
					"FORMNAME",auditFormName);
			boolean compareVersionBfrRelse1 = FormVersionLbl.getText().contains("0.1");
			TestValidation.IsTrue(compareVersionBfrRelse1, 
								  "VERIFIED Form version as 0.1", 
								  "Failed to Verify Form version as 0.1");
			
			boolean editForm = fdp.navigateToReleaseForm_EditForm(auditFormName);
			TestValidation.IsTrue(editForm, 
								  "OPENED form in edit mode", 
								  "Failed to Open form in edit mode");
			
			boolean nextToReleaseForm2 = fdp.clickOnNextButton(fdp.ReleaseFormPg, FORM_NEXT_PAGE.RELEASE_FORM);
			TestValidation.IsTrue(nextToReleaseForm2, 
								  "NAVIGATED to 'Release Form'", 
								  "Failed to Navigate to 'Release Form'");
			
			FormVersionLbl = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FORM_VERSION_LBL,
					"FORMNAME",auditFormName);
			boolean compareVersionBfrRelse2 = FormVersionLbl.getText().contains("0.2");
			TestValidation.IsTrue(compareVersionBfrRelse2, 
								  "VERIFIED Form version as 0.2", 
								  "Failed to Verify Form version as 0.2");
			
			boolean releaseForm = fdp.releaseForm(auditFormName);
			TestValidation.IsTrue(releaseForm, 
							       "SHOULD release form - " + auditFormName, 
								   "Failed since uncertain that the form " + auditFormName + " did/did not release'");
			
			FSQABrowserPage fbp = hp.clickFSQABrowserMenu();
			boolean navigateToforms = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.CUSTOMERS, FSQATAB.FORMS);
			TestValidation.IsTrue(navigateToforms, 
								  "For customer category, NAVIGATED to FSQABrowser > Forms tab", 
								  "Failed to navigate to FSQABrowser > Forms tab");
					
			boolean applyfilter = fbp.openAndApplySettingsForColumn(COLUMNHEADER.FORMNAME, COLUMNSETTING.FILTER, auditFormName);
			TestValidation.IsTrue(applyfilter, 
								  "Applied Filter on" + COLUMNHEADER.FORMNAME, 
								  "Failed to apply filter on" + COLUMNHEADER.FORMNAME);
			
			boolean verifyFrmType1 = fbp.verifyGridValuesForColumn(COLUMNHEADER.FORMTYPE, FORM_TYPES.AUDIT);
			TestValidation.IsTrue(verifyFrmType1, 
								  "VERIFIED Form Type value as - " + FORM_TYPES.AUDIT, 
								  "Failed to verify Form Type value as " + FORM_TYPES.AUDIT);
			
			boolean verifyModBy1 = fbp.verifyGridValuesForColumn(COLUMNHEADER.MODIFIEDBY, displayName);
			TestValidation.IsTrue(verifyModBy1, 
								  "VERIFIED Modified By value as - " + displayName, 
								  "Failed to verify Modified By value as " + displayName);
			
			DateTime dt = new DateTime();
			currentDate = dt.getTodayDate("MM/dd/YYYY", dt.getTimezone(adminTimezone));
			UserManagerPage ump = new UserManagerPage(driver);
			timezoneCode = ump.getTimezoneCode(adminTimezone);
			
			String modOnVal1 = fbp.getGridValueForColumn(COLUMNHEADER.MODIFIEDON);
			boolean verifyModOn1 = modOnVal1.contains(currentDate) && modOnVal1.contains(timezoneCode);
			TestValidation.IsTrue(verifyModOn1, 
								  "VERIFIED Modified On value as - " + modOnVal1, 
								  "Failed to verify Modified On value as " + modOnVal1);
			
			boolean verifyVersionVal1 = fbp.verifyGridValuesForColumn(COLUMNHEADER.VERSION, "1.0");
			TestValidation.IsTrue(verifyVersionVal1, 
								  "VERIFIED Version value as - 1.0", 
								  "Failed to verify Version value as 1.0");
			
			boolean editform = fbp.editForm(auditFormName,fdp);
			TestValidation.IsTrue(editform, 
								  "Form EDITED and RELEASED", 
								  "Failed to Edit form and Release");
			
			boolean exitFromFormDesigner = fdp.exitFromFormDesigner();
			TestValidation.IsTrue(exitFromFormDesigner, 
								  "EXITED from 'Form Designer'", 
								  "Failed to Exit from 'Form Designer'");
			
			boolean verifyFrmType2 = fbp.verifyGridValuesForColumn(COLUMNHEADER.FORMTYPE, FORM_TYPES.AUDIT);
			TestValidation.IsTrue(verifyFrmType2, 
								  "VERIFIED Form Type value as - " + FORM_TYPES.AUDIT, 
								  "Failed to verify Form Type value as " + FORM_TYPES.AUDIT);
			
			boolean verifyModBy2 = fbp.verifyGridValuesForColumn(COLUMNHEADER.MODIFIEDBY, displayName);
			TestValidation.IsTrue(verifyModBy2, 
								  "VERIFIED Modified By value as - " + displayName, 
								  "Failed to verify Modified By value as " + displayName);
			
			String modOnVal2 = fbp.getGridValueForColumn(COLUMNHEADER.MODIFIEDON);
			boolean verifyModOn2 = modOnVal2.contains(currentDate) && modOnVal2.contains(timezoneCode);
			TestValidation.IsTrue(verifyModOn2, 
								  "VERIFIED Modified On value as - " + modOnVal2, 
								  "Failed to verify Modified On value as " + modOnVal2);
			
			boolean verifyVersionVal2 = fbp.verifyGridValuesForColumn(COLUMNHEADER.VERSION, "2.0");
			TestValidation.IsTrue(verifyVersionVal2, 
								  "VERIFIED Version value as - 2.0", 
								  "Failed to verify Version value as 2.0");
			
		}
		finally {
			if(hp.clickHomepageLinkAndBypassPopup().error){
				TCGFailureMsg = "Could NOT land on FSQA Browser page after clicking on Home link";
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}
		}
	}	
	
	@Test(description = "Check-Verify Forms Submitted entry in records tab (Resource , Form Name , Completed by, Completed on , Location , Sign off)")
	public void TestCase_37724() throws Exception {
		
		String frmName = CommonMethods.dynamicText("ChkFrm_FSQARecDets");
		
		HashMap<String, List<String>> fields = new HashMap<String, List<String>>();
		fields.put(FIELD_TYPES.PARAGRAPH_TEXT, Arrays.asList(fieldParagraphName));

		FormFieldParams ffp = new FormFieldParams();
		ffp.FieldDetails = fields;

		HashMap<String, List<String>> resource = new HashMap<String, List<String>>();
		resource.put(FORMRESOURCETYPES.CUSTOMERS, Arrays.asList(resourceCategoryValue));

		FormDesignParams fp = new FormDesignParams();
		fp.FormType = FORMTYPES.CHECK;
		fp.FormName = frmName;
		fp.SelectResources = resource;
		fp.DesignFields = Arrays.asList(ffp);

		FormDesignerPage fdp = hp.clickFormDesignerMenu();
		boolean releaseForm = fdp.createAndReleaseForm(fp);
		TestValidation.IsTrue(releaseForm,
							  "RELEASED Check form - " + frmName,
							  "Failed to Release Check form - " + frmName); 
		
		FSQABrowserPage fbp = hp.clickFSQABrowserMenu();
		boolean navigateToforms = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.CUSTOMERS, FSQATAB.FORMS);
		TestValidation.IsTrue(navigateToforms, 
							  "For customer category, NAVIGATED to FSQABrowser > Forms tab", 
							  "Failed to navigate to FSQABrowser > Forms tab");
				
		boolean applyFilterAndOpenForm = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, frmName);
		TestValidation.IsTrue(applyFilterAndOpenForm, 
							  "OPENED form - " + frmName, 
							  "Failed to open form - " + frmName);
		
		HashMap<String, List<String>> fillDetails = new HashMap<String, List<String>>();
		fillDetails.put(fieldParagraphName, Arrays.asList("Test Values for Automation"));

		FormDetails fd = new FormDetails();
		fd.fieldDetails = fillDetails;
		fd.resourceName = resourceInstance2Value;
		FormOperations fo = new FormOperations(driver);
		boolean submit = fo.submitData(fd);
		TestValidation.IsTrue(submit, 
							  "Form submitted" + frmName, 
							  "Failed to submit form" + frmName);
		
		boolean navigateToRecs = fbp.navigateToFSQATab(FSQATAB.RECORDS);
		TestValidation.IsTrue(navigateToRecs, 
							  "For customer category, NAVIGATED to FSQABrowser > Records tab", 
							  "Failed to navigate to FSQABrowser > Records tab");
				
		boolean applyfilter = fbp.openAndApplySettingsForColumn(COLUMNHEADER.FORMNAME, COLUMNSETTING.FILTER, frmName);
		TestValidation.IsTrue(applyfilter, 
							  "Applied Filter on" + COLUMNHEADER.FORMNAME, 
							  "Failed to apply filter on" + COLUMNHEADER.FORMNAME);
		
		boolean verifyRecRsrcVal = fbp.verifyGridValuesForColumn(COLUMNHEADER.RESOURCE, resourceInstance2Value);
		TestValidation.IsTrue(verifyRecRsrcVal, 
							  "VERIFIED Resource value as - " + resourceInstance2Value, 
							  "Failed to verify Resource value as " + resourceInstance2Value);
		
		boolean verifyCmpltdByVal = fbp.verifyGridValuesForColumn(COLUMNHEADER.COMPLETEDBY, displayName);
		TestValidation.IsTrue(verifyCmpltdByVal, 
							  "VERIFIED Completed By value as - " + displayName, 
							  "Failed to verify Completed By value as " + displayName);
		
		DateTime dt = new DateTime();
		currentDate = dt.getTodayDate("MM/dd/YYYY", dt.getTimezone(adminTimezone));
		UserManagerPage ump = new UserManagerPage(driver);
		timezoneCode = ump.getTimezoneCode(adminTimezone);
		
		String cmpltdOnVal = fbp.getGridValueForColumn(COLUMNHEADER.COMPLETEDON);
		boolean verifyCmpltdOnVal = cmpltdOnVal.contains(currentDate) && cmpltdOnVal.contains(timezoneCode);
		TestValidation.IsTrue(verifyCmpltdOnVal, 
							  "VERIFIED Completed On value as - " + cmpltdOnVal, 
							  "Failed to verify Completed On value as " + cmpltdOnVal);
		
		boolean verifyLocationVal = fbp.verifyGridValuesForColumn(COLUMNHEADER.LOCATION, locationInstanceValue);
		TestValidation.IsTrue(verifyLocationVal, 
							  "VERIFIED Location value as - " + locationInstanceValue, 
							  "Failed to verify Location value as " + locationInstanceValue);
		
		// Verify if Sign off is Blank
		boolean verifySignOffVal = fbp.verifyGridValuesForColumn(COLUMNHEADER.SIGNOFF, "");
		TestValidation.IsTrue(verifySignOffVal, 
							  "VERIFIED Sign Off value as blank since the record is not Signed off", 
							  "Failed to verify Sign Off value as blank/none");

	}
	
	@Test(description = "Audit -  Verify Forms Submitted entry in records tab (Resource , Form Name , Completed by, Completed on , Location , Sign off)")
	public void TestCase_38499() throws Exception {
		
		String frmName = CommonMethods.dynamicText("AdtFrm_FSQARecDets");
		
		HashMap<String, List<String>> fields = new HashMap<String, List<String>>();
		fields.put(FIELD_TYPES.DATE_TIME, Arrays.asList(fieldDateTimeName));

		FormFieldParams ffp = new FormFieldParams();
		ffp.FieldDetails = fields;
		ffp.SectionName = fieldSectionName;

		HashMap<String, List<String>> resource = new HashMap<String, List<String>>();
		resource.put(FORMRESOURCETYPES.CUSTOMERS, Arrays.asList(resourceCategoryValue));

		FormDesignParams fp = new FormDesignParams();
		fp.FormType = FORMTYPES.AUDIT;
		fp.FormName = frmName;
		fp.SelectResources = resource;
		fp.DesignFields = Arrays.asList(ffp);

		FormDesignerPage fdp = hp.clickFormDesignerMenu();
		boolean releaseForm = fdp.createAndReleaseForm(fp);
		TestValidation.IsTrue(releaseForm,
							  "RELEASED Audit form - " + frmName,
							  "Failed to Release Audit form - " + frmName); 
		
		FSQABrowserPage fbp = hp.clickFSQABrowserMenu();
		boolean navigateToforms = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.CUSTOMERS, FSQATAB.FORMS);
		TestValidation.IsTrue(navigateToforms, 
							  "For customer category, NAVIGATED to FSQABrowser > Forms tab", 
							  "Failed to navigate to FSQABrowser > Forms tab");
				
		boolean applyFilterAndOpenForm = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, frmName);
		TestValidation.IsTrue(applyFilterAndOpenForm, 
							  "OPENED form - " + frmName, 
							  "Failed to open form - " + frmName);
		
		HashMap<String, List<String>> fillDetails = new HashMap<String, List<String>>();
		fillDetails.put(fieldDateTimeName, Arrays.asList("10/17/2025 1:59 PM"));

		FormDetails fd = new FormDetails();
		fd.fieldDetails = fillDetails;
		fd.resourceName = resourceInstance1Value;
		FormOperations fo = new FormOperations(driver);
		boolean submit = fo.submitData(fd);
		TestValidation.IsTrue(submit, 
							  "Form submitted" + frmName, 
							  "Failed to submit form" + frmName);
		
		boolean navigateToRecs = fbp.navigateToFSQATab(FSQATAB.RECORDS);
		TestValidation.IsTrue(navigateToRecs, 
							  "For customer category, NAVIGATED to FSQABrowser > Records tab", 
							  "Failed to navigate to FSQABrowser > Records tab");
				
		boolean applyfilter = fbp.openAndApplySettingsForColumn(COLUMNHEADER.FORMNAME, COLUMNSETTING.FILTER, frmName);
		TestValidation.IsTrue(applyfilter, 
							  "Applied Filter on" + COLUMNHEADER.FORMNAME, 
							  "Failed to apply filter on" + COLUMNHEADER.FORMNAME);
		
		boolean verifyRecRsrcVal = fbp.verifyGridValuesForColumn(COLUMNHEADER.RESOURCE, resourceInstance1Value);
		TestValidation.IsTrue(verifyRecRsrcVal, 
							  "VERIFIED Resource value as - " + resourceInstance1Value, 
							  "Failed to verify Resource value as " + resourceInstance1Value);
		
		boolean verifyCmpltdByVal = fbp.verifyGridValuesForColumn(COLUMNHEADER.COMPLETEDBY, displayName);
		TestValidation.IsTrue(verifyCmpltdByVal, 
							  "VERIFIED Completed By value as - " + displayName, 
							  "Failed to verify Completed By value as " + displayName);
		
		DateTime dt = new DateTime();
		currentDate = dt.getTodayDate("MM/dd/YYYY", dt.getTimezone(adminTimezone));
		UserManagerPage ump = new UserManagerPage(driver);
		timezoneCode = ump.getTimezoneCode(adminTimezone);
		
		String cmpltdOnVal = fbp.getGridValueForColumn(COLUMNHEADER.COMPLETEDON);
		boolean verifyCmpltdOnVal = cmpltdOnVal.contains(currentDate) && cmpltdOnVal.contains(timezoneCode);
		TestValidation.IsTrue(verifyCmpltdOnVal, 
							  "VERIFIED Completed On value as - " + cmpltdOnVal, 
							  "Failed to verify Completed On value as " + cmpltdOnVal);
		
		boolean verifyLocationVal = fbp.verifyGridValuesForColumn(COLUMNHEADER.LOCATION, locationInstanceValue);
		TestValidation.IsTrue(verifyLocationVal, 
							  "VERIFIED Location value as - " + locationInstanceValue, 
							  "Failed to verify Location value as " + locationInstanceValue);
		
		// Verify if Sign off is Blank
		boolean verifySignOffVal = fbp.verifyGridValuesForColumn(COLUMNHEADER.SIGNOFF, "");
		TestValidation.IsTrue(verifySignOffVal, 
							  "VERIFIED Sign Off value as blank since the record is not Signed off", 
							  "Failed to verify Sign Off value as blank/none");

	}
	
	@Test(description = "Questionnaire - Verify entry for submitted form in Documents tab (Document Name, Document Type , Modified by , Modified On, Version)")
	public void TestCase_37725() throws Exception {
		
		String frmName = CommonMethods.dynamicText("QstFrm_FSQADocDets");
		
		HashMap<String, List<String>> fields = new HashMap<String, List<String>>();
		fields.put(FIELD_TYPES.SINGLE_LINE_TEXT, Arrays.asList(fieldSingleLineTxtName));

		FormFieldParams ffp = new FormFieldParams();
		ffp.FieldDetails = fields;

		HashMap<String, List<String>> resource = new HashMap<String, List<String>>();
		resource.put(FORMRESOURCETYPES.CUSTOMERS, Arrays.asList(resourceCategoryValue));

		FormDesignParams fp = new FormDesignParams();
		fp.FormType = FORMTYPES.QUESTIONNAIRE;
		fp.FormName = frmName;
		fp.SelectResources = resource;
		fp.DesignFields = Arrays.asList(ffp);

		FormDesignerPage fdp = hp.clickFormDesignerMenu();
		boolean releaseForm = fdp.createAndReleaseForm(fp);
		TestValidation.IsTrue(releaseForm,
							  "RELEASED Questionnaire form - " + frmName,
							  "Failed to Release Questionnaire form - " + frmName); 
		
		FSQABrowserPage fbp = hp.clickFSQABrowserMenu();
		boolean navigateToforms = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.CUSTOMERS, FSQATAB.FORMS);
		TestValidation.IsTrue(navigateToforms, 
							  "For customer category, NAVIGATED to FSQABrowser > Forms tab", 
							  "Failed to navigate to FSQABrowser > Forms tab");
				
		boolean applyFilterAndOpenForm = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, frmName);
		TestValidation.IsTrue(applyFilterAndOpenForm, 
							  "OPENED form - " + frmName, 
							  "Failed to open form - " + frmName);
		
		HashMap<String, List<String>> fillDetails = new HashMap<String, List<String>>();
		fillDetails.put(fieldSingleLineTxtName, Arrays.asList("Test Values for Automation"));

		FormDetails fd = new FormDetails();
		fd.fieldDetails = fillDetails;
		fd.resourceName = resourceInstance1Value;
		FormOperations fo = new FormOperations(driver);
		boolean submit = fo.submitData(fd);
		TestValidation.IsTrue(submit, 
							  "Form submitted" + frmName, 
							  "Failed to submit form" + frmName);
		
		boolean navigateToRecs = fbp.navigateToFSQATab(FSQATAB.DOCUMENTS);
		TestValidation.IsTrue(navigateToRecs, 
							  "For customer category, NAVIGATED to FSQABrowser > Documents tab", 
							  "Failed to navigate to FSQABrowser > Documents tab");
				
		boolean openDocument = fbp.openAndApplySettingsForColumn(COLUMNHEADER.DOCUMENTNAME, COLUMNSETTING.FILTER, frmName);
		TestValidation.IsTrue(openDocument, 
				 			  "OPENED document with form name - " + frmName, 
				 			  "Failed to open document with form name - " + frmName);
		
		boolean verifyDocTypeVal = fbp.verifyGridValuesForColumn(COLUMNHEADER.DOCUMENTTYPE, frmName);
		TestValidation.IsTrue(verifyDocTypeVal, 
							  "VERIFIED Document Type value as - " + frmName, 
							  "Failed to verify Document Type value as " + frmName);
		
		boolean verifyModByVal = fbp.verifyGridValuesForColumn(COLUMNHEADER.MODIFIEDBY, displayName);
		TestValidation.IsTrue(verifyModByVal, 
							  "VERIFIED Modified By value as - " + displayName, 
							  "Failed to verify Modified By value as " + displayName);
		
		DateTime dt = new DateTime();
		currentDate = dt.getTodayDate("MM/dd/YYYY", dt.getTimezone(adminTimezone));
		
		boolean verifyModOnVal = fbp.verifyGridValuesForColumn(COLUMNHEADER.MODIFIEDON, currentDate);
		TestValidation.IsTrue(verifyModOnVal, 
							  "VERIFIED Modified On value as - " + currentDate, 
							  "Failed to verify Modified On value as " + currentDate);
		
		boolean verifyVersionVal = fbp.verifyGridValuesForColumn(COLUMNHEADER.VERSION, "1.0");
		TestValidation.IsTrue(verifyVersionVal, 
							  "VERIFIED Version value as - 1.0", 
							  "Failed to verify Version value as 1.0");

	}
	
	@Test(description="Questionnaire form - Select one and Select Multiple field functionality for questionnaire / audit form types")
	public void TestCase_38319() {

		try {
			
			String questFormName = CommonMethods.dynamicText("QstFrm_SelectFlds");
			String secAOptionswithWeight[][] = {{"10","10"},{"20","20"}};
			String secBOptionswithWeight[][] = {{"30","30"},{"40","40"}};
			
			//Open 'Form Designer'
			FormDesignerPage fdp = hp.clickFormDesignerMenu();
			boolean selectQst = fdp.selectFormType(FORMTYPES.QUESTIONNAIRE);
			TestValidation.IsTrue(selectQst, 
								  "SELECTED Questionnaire form type successfully", 
								  "Failed to Select Questionnaire form type");

			//Selection of resource
			boolean selectResource = fdp.selectResource(resourceType,resourceCategoryValue,resourceInstance1Value);
			TestValidation.IsTrue(selectResource, 
								  "SELECTED Resources under '" + resourceCategoryValue  +"' successfully", 
								  "Failed to Select resources under '" + resourceCategoryValue + "'");
	
			//Go to 'Form Design'
			boolean clickOnNextButton = fdp.clickOnNextButton(fdp.DesignFormPg, FORM_NEXT_PAGE.DESIGN_FORM);
			TestValidation.IsTrue(clickOnNextButton, 
								  "CLICKED On Next Button successfully", 
								  "Failed to Click On Next Button");
	
			boolean setName = fdp.setFormName(questFormName);
			TestValidation.IsTrue(setName, 
					  			  "Form name SET as " + questFormName, 
					  			  "Failed to Set form name as " + questFormName);
			
			//Add Select Multiple Field 
			WebElement SMFieldType = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_TYPE,
					"FIELDTYPE",FIELD_TYPES.SELECT_MULTIPLE);
			controlActions.dragdrop(SMFieldType, fdp.FieldTypeDropAreaFormLevel);
			boolean setSMFldName = fdp.setElementValueTextArea(FIELD_TYPES.SELECT_MULTIPLE, fieldSelectMultName);	
			TestValidation.IsTrue(setSMFldName, 
								  "ADDED name '" + fieldSelectMultName + "' to Select Multiple field successfully", 
								  "Failed to Add name '" + fieldSelectMultName + "' Select Multiple field");
			
			boolean optnsWithWeightSecA = fdp.setFieldptions("100", null, secAOptionswithWeight);
			TestValidation.IsTrue(optnsWithWeightSecA, 
								  "Options with Weights SET for '" + fieldSelectMultName + "' field successfully", 
								  "Failed to Add Options with Weights SET for '" + fieldSelectMultName + "' field");
			
			//Add Select One Field 
			WebElement SOFieldType = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_TYPE,
					"FIELDTYPE",FIELD_TYPES.SELECT_ONE);
			controlActions.dragdrop(SOFieldType, fdp.FieldTypeDropAreaFormLevel);
			boolean setSOFldName = fdp.setElementValueTextArea(FIELD_TYPES.SELECT_ONE, fieldSelectOneName);	
			TestValidation.IsTrue(setSOFldName, 
								  "ADDED name '" + fieldSelectOneName + "' to Select One field successfully", 
								  "Failed to Add name '" + fieldSelectOneName + "' Select One field");
			
			boolean optnsWithWeightSecB = fdp.setFieldptions("100", null, secBOptionswithWeight);
			TestValidation.IsTrue(optnsWithWeightSecB, 
								  "Options with Weights SET for '" + fieldSelectOneName + "' field successfully", 
								  "Failed to Add Options with Weights SET for '" + fieldSelectOneName + "' field");
			
			boolean nextToReleaseForm = fdp.clickOnNextButton(fdp.ReleaseFormPg, FORM_NEXT_PAGE.RELEASE_FORM);
			TestValidation.IsTrue(nextToReleaseForm, 
								  "NAVIGATED to 'Release Form'", 
								  "Failed to Navigate to 'Release Form'");
			
			boolean releaseForm = fdp.releaseForm(questFormName);
			TestValidation.IsTrue(releaseForm, 
							       "SHOULD release form - " + questFormName, 
								   "Failed since uncertain that the form " + questFormName + " did/did not release'");
			
			FSQABrowserPage fbp = hp.clickFSQABrowserMenu();
			boolean navigateToforms = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.CUSTOMERS, FSQATAB.FORMS);
			TestValidation.IsTrue(navigateToforms, 
								  "For customer category, NAVIGATED to FSQABrowser > Forms tab", 
								  "Failed to navigate to FSQABrowser > Forms tab");
					
			boolean applyFilterAndOpenForm = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, questFormName);
			TestValidation.IsTrue(applyFilterAndOpenForm, 
								  "OPENED form - " + questFormName, 
								  "Failed to open form - " + questFormName);
			
			HashMap<String, List<String>> fillDetails = new HashMap<String, List<String>>();
			fillDetails.put(fieldSelectMultName, Arrays.asList("10;20"));
			fillDetails.put(fieldSelectOneName, Arrays.asList("40"));

			FormDetails fd = new FormDetails();
			fd.fieldDetails = fillDetails;
			fd.resourceName = resourceInstance2Value;
			FormOperations fo = new FormOperations(driver);
			boolean submit = fo.submitData(fd);
			TestValidation.IsTrue(submit, 
								  "Form submitted" + questFormName, 
								  "Failed to submit form" + questFormName);
			
			boolean navigateToRecs = fbp.navigateToFSQATab(FSQATAB.DOCUMENTS);
			TestValidation.IsTrue(navigateToRecs, 
								  "For customer category, NAVIGATED to FSQABrowser > Documents tab", 
								  "Failed to navigate to FSQABrowser > Documents tab");
			
			boolean openDocument = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.DOCUMENTNAME, questFormName);
			TestValidation.IsTrue(openDocument, 
					 			  "OPENED document with form name - " + questFormName, 
					 			  "Failed to open document with form name - " + questFormName);
			
			FSQABrowserDocumentsPage fbdp = new FSQABrowserDocumentsPage(driver);
			// Select Multiple
			String SMFieldValue = fbdp.getFieldSpecificValue(fieldSelectMultName,FLD_OTHER_TYPES.FIELD);
			boolean verifySMFieldValue = SMFieldValue.contains("10") && SMFieldValue.contains("20");
			TestValidation.IsTrue(verifySMFieldValue, 
								  "RETRIEVED value for the field " + fieldSelectMultName + " as " + SMFieldValue, 
								  "Failed to retrieve value for the field " + fieldSelectMultName );
			
			// Select One
			String SOFieldValue = fbdp.getFieldSpecificValue(fieldSelectOneName,FLD_OTHER_TYPES.FIELD);
			boolean verifySOFieldValue = SOFieldValue.contains("40");
			TestValidation.IsTrue(verifySOFieldValue, 
								  "RETRIEVED value for the field " + fieldSelectOneName + " as " + SOFieldValue, 
								  "Failed to retrieve value for the field " + fieldSelectOneName );
			
		}
		finally {
			if(hp.clickHomepageLinkAndBypassPopup().error){
				TCGFailureMsg = "Could NOT land on FSQA Browser page after clicking on Home link";
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}
		}
	}	
	
	@Test(description="Audit form - Select one and Select Multiple field functionality for questionnaire / audit form types")
	public void TestCase_38511() {

		try {
			
			String auditFormName = CommonMethods.dynamicText("AdtFrm_SelectFlds");
			String secAOptionswithWeight[][] = {{"10","10"},{"20","20"}};
			String secBOptionswithWeight[][] = {{"30","30"},{"40","40"}};
			
			//Open 'Form Designer'
			FormDesignerPage fdp = hp.clickFormDesignerMenu();
			boolean selectAdt = fdp.selectFormType(FORMTYPES.AUDIT);
			TestValidation.IsTrue(selectAdt, 
								  "SELECTED Audit form type successfully", 
								  "Failed to Select Audit form type");

			//Selection of resource
			boolean selectResource = fdp.selectResource(resourceType,resourceCategoryValue,resourceInstance1Value);
			TestValidation.IsTrue(selectResource, 
								  "SELECTED Resources under '" + resourceCategoryValue  +"' successfully", 
								  "Failed to Select resources under '" + resourceCategoryValue + "'");
	
			//Go to 'Form Design'
			boolean clickOnNextButton = fdp.clickOnNextButton(fdp.DesignFormPg, FORM_NEXT_PAGE.DESIGN_FORM);
			TestValidation.IsTrue(clickOnNextButton, 
								  "CLICKED On Next Button successfully", 
								  "Failed to Click On Next Button");
	
			boolean setName = fdp.setFormName(auditFormName);
			TestValidation.IsTrue(setName, 
					  			  "Form name SET as " + auditFormName, 
					  			  "Failed to Set form name as " + auditFormName);
			
			//Add Section A to Form
			WebElement SectionAElement = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FORM_ELEMENT,
					"FORMELEMENT",ELEMENT_INPUT_TYPES.SECTION);
			controlActions.dragdrop(SectionAElement, fdp.FieldTypeDropAreaFormLevel);
			boolean setSectionAFieldValue = fdp.setFieldName(ELEMENT_INPUT_TYPES.SECTION, fieldSectionName);
			TestValidation.IsTrue(setSectionAFieldValue, 
								  "ADDED Section " + fieldSectionName + " successfully", 
								  "Failed to Add Section " + fieldSectionName + " successfully");
			
			//Add Select Multiple Field 
			boolean addSelectMultFieldInSection = fdp.dragDropElementInSectionGroup(ELEMENT_TYPE.FIELD_TYPES,
					FIELD_TYPES.SELECT_MULTIPLE, fieldSectionName);
			boolean setSelectMultipleFldName = fdp.setTextBoxValue(FIELD_TYPES.SELECT_MULTIPLE, fieldSelectMultName);	
			TestValidation.IsTrue(addSelectMultFieldInSection && setSelectMultipleFldName, 
								  "ADDED Select Multiple field successfully with name - " + fieldSelectMultName, 
								  "Failed to Add Select Multiple field");
			
			boolean optnsWithWeightSecA = fdp.setFieldptions("100", null, secAOptionswithWeight);
			TestValidation.IsTrue(optnsWithWeightSecA, 
								  "Options with Weights SET for '" + fieldSelectMultName + "' field successfully", 
								  "Failed to Add Options with Weights SET for '" + fieldSelectMultName + "' field");
			
			//Add Select One Field 
			boolean addSelectOneFieldInSection = fdp.dragDropElementInSectionGroup(ELEMENT_TYPE.FIELD_TYPES,
					FIELD_TYPES.SELECT_ONE, fieldSectionName);
			boolean setSelectOneFldName = fdp.setTextBoxValue(FIELD_TYPES.SELECT_ONE, fieldSelectOneName);	
			TestValidation.IsTrue(addSelectOneFieldInSection && setSelectOneFldName, 
								  "ADDED Select One field successfully with name - " + fieldSelectOneName, 
								  "Failed to Add One Multiple field");
			
			boolean optnsWithWeightSecB = fdp.setFieldptions("100", null, secBOptionswithWeight);
			TestValidation.IsTrue(optnsWithWeightSecB, 
								  "Options with Weights SET for '" + fieldSelectOneName + "' field successfully", 
								  "Failed to Add Options with Weights SET for '" + fieldSelectOneName + "' field");
			
			boolean nextToReleaseForm = fdp.clickOnNextButton(fdp.ReleaseFormPg, FORM_NEXT_PAGE.RELEASE_FORM);
			TestValidation.IsTrue(nextToReleaseForm, 
								  "NAVIGATED to 'Release Form'", 
								  "Failed to Navigate to 'Release Form'");
			
			boolean releaseForm = fdp.releaseForm(auditFormName);
			TestValidation.IsTrue(releaseForm, 
							       "SHOULD release form - " + auditFormName, 
								   "Failed since uncertain that the form " + auditFormName + " did/did not release'");
			
			FSQABrowserPage fbp = hp.clickFSQABrowserMenu();
			boolean navigateToforms = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.CUSTOMERS, FSQATAB.FORMS);
			TestValidation.IsTrue(navigateToforms, 
								  "For customer category, NAVIGATED to FSQABrowser > Forms tab", 
								  "Failed to navigate to FSQABrowser > Forms tab");
					
			boolean applyFilterAndOpenForm = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, auditFormName);
			TestValidation.IsTrue(applyFilterAndOpenForm, 
								  "OPENED form - " + auditFormName, 
								  "Failed to open form - " + auditFormName);
			
			HashMap<String, List<String>> fillDetails = new HashMap<String, List<String>>();
			fillDetails.put(fieldSelectMultName, Arrays.asList("20"));
			fillDetails.put(fieldSelectOneName, Arrays.asList("30"));

			FormDetails fd = new FormDetails();
			fd.fieldDetails = fillDetails;
			fd.resourceName = resourceInstance2Value;
			FormOperations fo = new FormOperations(driver);
			boolean submit = fo.submitData(fd);
			TestValidation.IsTrue(submit, 
								  "Form submitted" + auditFormName, 
								  "Failed to submit form" + auditFormName);
			
			boolean navigateToRecs = fbp.navigateToFSQATab(FSQATAB.RECORDS);
			TestValidation.IsTrue(navigateToRecs, 
								  "For customer category, NAVIGATED to FSQABrowser > Records tab", 
								  "Failed to navigate to FSQABrowser > Records tab");
					
			boolean applyFilterAndOpenRec = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, auditFormName);
			TestValidation.IsTrue(applyFilterAndOpenRec, 
								  "OPENED record - " + auditFormName, 
								  "Failed to open record - " + auditFormName);
			
			FSQABrowserRecordsPage frp = new FSQABrowserRecordsPage(driver);
			// Select Multiple
			String SMFieldValue = frp.getFieldSpecificValue(fieldSelectMultName,FLD_OTHER_TYPES.FIELD);
			boolean verifySMFieldValue = SMFieldValue.contains("20");
			TestValidation.IsTrue(verifySMFieldValue, 
								  "RETRIEVED value for the field " + fieldSelectMultName + " as " + SMFieldValue, 
								  "Failed to retrieve value for the field " + fieldSelectMultName );
			
			// Select One
			String SOFieldValue = frp.getFieldSpecificValue(fieldSelectOneName,FLD_OTHER_TYPES.FIELD);
			boolean verifySOFieldValue = SOFieldValue.contains("30");
			TestValidation.IsTrue(verifySOFieldValue, 
								  "RETRIEVED value for the field " + fieldSelectOneName + " as " + SOFieldValue, 
								  "Failed to retrieve value for the field " + fieldSelectOneName );
			
		}
		finally {
			if(hp.clickHomepageLinkAndBypassPopup().error){
				TCGFailureMsg = "Could NOT land on FSQA Browser page after clicking on Home link";
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
