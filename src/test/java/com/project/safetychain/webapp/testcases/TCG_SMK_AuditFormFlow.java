package com.project.safetychain.webapp.testcases;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.project.safetychain.api.models.support.TCG_FormCreation_Wrapper;
import com.project.safetychain.api.models.support.Support.DPT_FORM_TYPES;
import com.project.safetychain.api.models.support.Support.FormParams;
import com.project.safetychain.api.models.support.Support.RESOURCE_TYPES;
import com.project.safetychain.apiproject.models.Documents;
import com.project.safetychain.apiproject.models.Documents.DocType;
import com.project.safetychain.webapp.pageobjects.CommonPage;
import com.project.safetychain.webapp.pageobjects.DocumentManagementPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage;
import com.project.safetychain.webapp.pageobjects.FormDesignerPageLocator;
import com.project.safetychain.webapp.pageobjects.HomePage;
import com.project.safetychain.webapp.pageobjects.LoginPage;
import com.project.safetychain.webapp.pageobjects.ManageLocationPage;
import com.project.safetychain.webapp.pageobjects.ManageResourcePage;
import com.project.safetychain.webapp.pageobjects.ResourceDesignerPage;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.ELEMENT_TYPE;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FIELD_TYPES;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.testbase.SupportingClasses.ExecutionMode;
import com.project.utilities.ApiUtils;
import com.project.utilities.CommonMethods;
import com.project.utilities.ControlActions;

public class TCG_SMK_AuditFormFlow extends TestBase{

	ControlActions controlActions;
	CommonPage mainPage;
	HomePage homePage;
	LoginPage lgnPge;
	ResourceDesignerPage resourceDesigner;
	ManageLocationPage locations;
	ManageResourcePage manageResource;
	FormDesignerPage fdp;
	DocumentManagementPage dmp;

	//Data
	public static String auditFormName;
	public static String auditFormName1;
	public static String auditFormName2;
	public static String resourceType = RESOURCE_TYPES.CUSTOMERS;
	public static String locationCategoryValue;
	public static String resourceCategoryValue;
	public static String resourceInstanceValue;
	public static String locationInstanceValue;

	public static String noteText = "Testing Note";
	public static String docTypeName;
	public static String documentName = "upload.png";
	public static String filePath = System.getProperty("user.dir") + "\\test-data-files\\UploadDocuments\\"+documentName;;

	@BeforeClass(alwaysRun = true)
	public void groupInit() throws Exception {
		auditFormName = CommonMethods.dynamicText("Automation_AuditForm");
		auditFormName1 = CommonMethods.dynamicText("Automation_AuditForm1");
		auditFormName2 = CommonMethods.dynamicText("Automation_AuditForm2");
		locationCategoryValue = CommonMethods.dynamicText("Loc_Cat");
		resourceCategoryValue = CommonMethods.dynamicText("customers_Cat");
		resourceInstanceValue = CommonMethods.dynamicText("Cust_Inst1");
		locationInstanceValue = CommonMethods.dynamicText("Loc_Inst");
		docTypeName = CommonMethods.dynamicText("Doc");

		if(executionMode.equalsIgnoreCase(ExecutionMode.API)) {
			// ------------------------------------------------------------------------------------------------
			// API Implementation
			TCG_FormCreation_Wrapper formCreationWrapper = new TCG_FormCreation_Wrapper();
			Documents docsAPI = new Documents();
			ApiUtils apiUtils = new ApiUtils();

			// ------------------------------------------------------------------------------------------------
			// API - Location & Resource Creation and Linking
			List<String> userList = Arrays.asList(adminUsername);

			HashMap<String, List<String>> LocationMap = new HashMap<String, List<String>>();
			LocationMap.put(locationCategoryValue, Arrays.asList(locationInstanceValue));

			HashMap<String, List<String>> resourceCatMap = new HashMap<String, List<String>>();
			resourceCatMap.put(resourceCategoryValue, Arrays.asList(resourceInstanceValue));

			formCreationWrapper.Resource_Location_CreationWrapper(resourceCatMap, LocationMap, true, userList,
					RESOURCE_TYPES.CUSTOMERS);

			// ------------------------------------------------------------------------------------------------
			// API - Document Type Creation and Upload
			
			String doctypeId = docsAPI.getDocumentTypeId(DocType.DOCUMENTTYPES);

			String createdDocTypeId = docsAPI.createDocType(docTypeName, doctypeId);

			// Internally in code upload 'upload.png' in created Doc Type
			String uploadedDocId = docsAPI.uploadDoc(doctypeId, createdDocTypeId, docTypeName);
			if(uploadedDocId == "" || uploadedDocId == null) {
				TCGFailureMsg = "Could NOT create Document type named " + docTypeName;
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}
			
			// ------------------------------------------------------------------------------------------------
			// API - Form Creation - Audit

			// API - Form details
			// API - Generate unique ID for form to be created
			String formId = apiUtils.getUUID();

			// API - Form layout details
			FormParams fp = new FormParams();
			fp.FormId = formId;
			fp.FormName = auditFormName;
			fp.type = DPT_FORM_TYPES.AUDIT;
			fp.ResourceType = RESOURCE_TYPES.CUSTOMERS;
			fp.ResourceCategoryNm = resourceCategoryValue;
			fp.ResourceInstanceNm = resourceInstanceValue;
			fp.CustomerResources = resourceCatMap;

			formCreationWrapper.API_Wrapper_CreateUnreleasedForms(fp);

			// ------------------------------------------------------------------------------------------------
			// WEB Application code starts here

			driver = launchbrowser();
			controlActions = new ControlActions(driver);
			mainPage = new CommonPage(driver);
			lgnPge = new LoginPage(driver);
			resourceDesigner = new ResourceDesignerPage(driver);
			manageResource = new ManageResourcePage(driver);
			locations = new ManageLocationPage(driver);
			fdp = new FormDesignerPage(driver);
			dmp = new DocumentManagementPage(driver);
			homePage = new HomePage(driver);

			controlActions.getUrl(applicationUrl);

			//login
			HomePage hp = lgnPge.performLogin(adminUsername, adminPassword);
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
			mainPage = new CommonPage(driver);
			lgnPge = new LoginPage(driver);
			resourceDesigner = new ResourceDesignerPage(driver);
			manageResource = new ManageResourcePage(driver);
			locations = new ManageLocationPage(driver);
			homePage = new HomePage(driver);
			
			controlActions.getUrl(applicationUrl);

			//login
			HomePage hp = lgnPge.performLogin(adminUsername, adminPassword);
			if(hp.error) {
				TCGFailureMsg = "Could NOT login to application";
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}

			//Location creation - Link User
			if(!locations.createLocationLinkUser(locationCategoryValue,locationInstanceValue,adminUsername)) {
				TCGFailureMsg ="Could NOT Create Location '"+locationInstanceValue+"' & Link User'"+adminUsername+"'";
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}
			
			//Resource creation - Link Location
			if(!manageResource.createResourceLinkLocation("Customers", resourceCategoryValue, resourceInstanceValue,locationInstanceValue)) {
				TCGFailureMsg = "Could NOT Create Resource '"+resourceInstanceValue+"' & Link Location'"+locationInstanceValue+"'";
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}

			dmp = mainPage.clickdocumentsmenu();
			if(dmp.error) {
				TCGFailureMsg = "Could NOT open 'Documents'";
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}
			if(!dmp.docUploadinDocType(docTypeName,filePath)) {
				TCGFailureMsg = "Could NOT add document type";
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}

			//Open 'Form Designer'
			fdp = mainPage.clickFormDesignerMenu();
			if(fdp.error) {
				TCGFailureMsg = "Could NOT Open Form Designer";
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}

			controlActions.clickElement(fdp.SelectAuditFormLnk);

			if(!fdp.selectResource("Customers",resourceCategoryValue,resourceInstanceValue)) {
				TCGFailureMsg = "Could NOT select resource";
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}

			//Go to 'Form Design'
			if(!fdp.clickOnNextButton(fdp.DesignFormPg,"Design Form")) {
				TCGFailureMsg = "Could NOT Navigate to 'Design Form'";
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}

			controlActions.sendKeys(fdp.FormNameTxt, auditFormName);

			//Save the form
			if(!fdp.clickSaveButton()) {
				TCGFailureMsg = "Could NOT Save Form";
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

	@Test(description="Adding header level fields")
	public void TestCase_31125() {

		boolean editFormDesign = fdp.navigateToReleaseForm_EditForm(auditFormName);
		TestValidation.IsTrue(editFormDesign, 
				"OPENED form - '"+auditFormName+"' in edit mode", 
				"Could NOT open form - '"+auditFormName+"' in edit mode"); 

		controlActions.dragdrop(fdp.NoteDbl, fdp.HeaderLevel);
		controlActions.sendKeys(fdp.NoteTxt,noteText);

		boolean addRelatedDocs = fdp.addRelatedDocument(docTypeName, documentName);
		TestValidation.IsTrue(addRelatedDocs, 
				"Document is added in 'Related Docs'", 
				"Could NOT add document 'Related Docs'"); 

		controlActions.dragdrop(fdp.Summarydbl, fdp.HeaderLevel);

		boolean saveForm = fdp.clickSaveButton();
		TestValidation.IsTrue(saveForm, 
				"Saved form Successfully", 
				"Could NOT Save Form");

		boolean verifyHeaderFields = fdp.verifyInPreviewFormHeader(auditFormName,noteText, documentName);
		TestValidation.IsTrue(verifyHeaderFields, 
				"VERIFIED hearder level fields", 
				"Could NOT verify hrader level fields");
	}

	@Test(description="Adding 'Select One' & 'Select Multiple' fields with weight")
	public void TestCase_31146() {
		String section1Name = "Section 1";

		boolean editFormDesign = fdp.navigateToReleaseForm_EditForm(auditFormName);
		TestValidation.IsTrue(editFormDesign, 
				"OPENED form - '"+auditFormName+"' in edit mode", 
				"Could NOT open form - '"+auditFormName+"' in edit mode"); 

		WebElement SectionElement1 = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FORM_ELEMENT,"FORMELEMENT","Section");
		controlActions.dragdrop(SectionElement1, fdp.FieldTypeDropAreaFormLevel);

		boolean setSectionName = fdp.setFieldName("Section",section1Name);
		TestValidation.IsTrue(setSectionName, 
				"Setted 'Section' name", 
				"Could NOT set 'Section' name");

		boolean addSelectOneFieldInSection = fdp.dragDropElementInSectionGroup(ELEMENT_TYPE.FIELD_TYPES,FIELD_TYPES.SELECT_ONE, section1Name);
		TestValidation.IsTrue(addSelectOneFieldInSection, 
				"Added 'Select One' field in 'Section'", 
				"Could NOT add 'Select One' field in 'Section'");

		boolean setSelectOneFieldName = fdp.setFieldName("Select One","Select One field 1");
		TestValidation.IsTrue(setSelectOneFieldName, 
				"Setted 'Select One' field name", 
				"Could NOT set 'Select One' field name");

		controlActions.sendKeys(fdp.QuestionWeightInp, "200");

		boolean setValues1 = fdp.addOption("1", "150");
		boolean setValues2 = fdp.addOption("2", "200");
		TestValidation.IsTrue(setValues1&&setValues2, 
				"Successfully added values in Select One field", 
				"Failed to add value in Select One field");


		boolean addSelectMultipleFieldInSection = fdp.dragDropElementInSectionGroup(ELEMENT_TYPE.FIELD_TYPES,FIELD_TYPES.SELECT_MULTIPLE, section1Name);
		TestValidation.IsTrue(addSelectMultipleFieldInSection, 
				"Added 'Select Multiple' field in 'Section'", 
				"Could NOT add 'Select Multiple' field in 'Section'");

		boolean setSelectMultipleFieldName = fdp.setFieldName("Select Multiple","Select Multiple field 1");
		TestValidation.IsTrue(setSelectMultipleFieldName, 
				"Setted 'Select Multiple' field name", 
				"Could NOT set 'Select Multiple' field name");


		controlActions.sendKeys(fdp.QuestionWeightInp, "200");

		setValues1 = fdp.addOption("1", "80");
		setValues2 = fdp.addOption("2", "120");
		TestValidation.IsTrue(setValues1&&setValues2, 
				"Successfully added values in Select Multiple field", 
				"Failed to add value in Select Multiple field");

		boolean saveForm = fdp.clickSaveButton();
		TestValidation.IsTrue(saveForm, 
				"Saved form Successfully", 
				"Could NOT Save Form");


		boolean verifyInPreviewForm = fdp.verifyInPreviewForm(auditFormName);
		TestValidation.IsTrue(verifyInPreviewForm, 
				"Verified fields in 'Preview Form'", 
				"Could NOT verify fields in the 'Preview Form'");
	}

	@Test(description="The form details should be correctly shown for the draft version")
	public void TestCase_31149() {


		//Open 'Form Designer'
		fdp = homePage.clickFormDesignerMenu();

		controlActions.clickElement(fdp.SelectAuditFormLnk);

		//Selection of resource
		boolean selectResource = fdp.selectResource("Customers",resourceCategoryValue,resourceInstanceValue);
		TestValidation.IsTrue(selectResource, 
				"Selected Resource successfully", 
				"Failed to Select Resource");

		//Go to 'Form Design'
		boolean clickOnNextButton = fdp.clickOnNextButton(fdp.DesignFormPg,"Design Form");
		TestValidation.IsTrue(clickOnNextButton, 
				"Clicked On Next Button successfully", 
				"Failed to Click On Next Button");

		controlActions.sendKeys(fdp.FormNameTxt, auditFormName1);

		boolean clickSaveButton = fdp.clickSaveButton();
		TestValidation.IsTrue(clickSaveButton, 
				"Clicked On Save Button successfully", 
				"Failed to Click On Save Button");		

		boolean navigateToReleaseForm = fdp.navigateToReleaseForm();
		TestValidation.IsTrue(navigateToReleaseForm, 
				"Navigated to Release form successfully", 
				"Could NOT Navigate to Release form"); 

		String formDetails = fdp.getFormDetails(auditFormName1);
		String version = "Version 0.1";
		String createdBy = mainPage.getLoggedInUserDetails();

		boolean expected1 = formDetails.contains(version);
		boolean expected2 = formDetails.contains(createdBy);
		TestValidation.IsTrue(expected1 && expected2 , 
				"Validated Version and Created by values in form details successfully", 
				"Failed to Validated Version and Created by values in form details");		
	}	

	@Test(description="Form Designer || Check user should be able to create and release \"Audit\" type of form.")
	public void TestCase_31177() {

		FormDesignerPage fdp = new FormDesignerPage(driver);

		//Open 'Form Designer'
		fdp = homePage.clickFormDesignerMenu();

		controlActions.clickElement(fdp.SelectAuditFormLnk);

		//Selection of resource
		boolean selectResource = fdp.selectResource("Customers",resourceCategoryValue,resourceInstanceValue);
		TestValidation.IsTrue(selectResource, 
				"Selected Resource successfully", 
				"Failed to Select Resource");

		//Go to 'Form Design'
		boolean clickOnNextButton = fdp.clickOnNextButton(fdp.DesignFormPg,"Design Form");
		TestValidation.IsTrue(clickOnNextButton, 
				"Clicked On Next Button successfully", 
				"Failed to Click On Next Button");

		controlActions.sendKeys(fdp.FormNameTxt, auditFormName2);

		WebElement SectionElement1 = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FORM_ELEMENT,"FORMELEMENT","Section");
		controlActions.dragdrop(SectionElement1, fdp.FieldTypeDropAreaFormLevel);

		String section1Name = "Section1";

		boolean setSectionName = fdp.setFieldName("Section",section1Name);
		TestValidation.IsTrue(setSectionName, 
				"Setted 'Section' name", 
				"Could NOT set 'Section' name");

		boolean addNumericFieldInSection = fdp.dragDropElementInSectionGroup(ELEMENT_TYPE.FIELD_TYPES,FIELD_TYPES.NUMERIC, section1Name);
		TestValidation.IsTrue(addNumericFieldInSection, 
				"Added 'Numeric' field in 'Section'", 
				"Could NOT add 'Numeric' field in 'Section'");

		boolean setNumericFieldName = fdp.setFieldName("Numeric","Numeric1");
		TestValidation.IsTrue(setNumericFieldName, 
				"Setted 'Numeric' field name", 
				"Could NOT set 'Numeric' field name");

		boolean clickSaveButton = fdp.clickSaveButton();
		TestValidation.IsTrue(clickSaveButton, 
				"Clicked On Save Button successfully", 
				"Failed to Click On Save Button");

		boolean nextToReleaseForm = fdp.clickOnNextButton(fdp.ReleaseFormPg,"Release Form");
		TestValidation.IsTrue(nextToReleaseForm, 
				"NAVIGATED to 'Release Form'", 
				"Could NOT Navigate to 'Release Form'");

		boolean releaseForm = fdp.releaseForm(auditFormName2);
		TestValidation.IsTrue(releaseForm, 
				"RELEASED form - "+auditFormName2, 
				"Could NOT release form'");

		FSQABrowserPage fbp = mainPage.clickFSQABrowserMenu();

		boolean selectResourceDropDownandNavigate =  fbp.selectResourceDropDownandNavigate("Customers","Forms");	
		TestValidation.IsTrue(selectResourceDropDownandNavigate, 
				"NAVIGATED to FSQA -> Forms Tab", 
				"Could NOT Navigate to FSQA -> Forms Tab");

		boolean applyFilterAndOpenForDetails = fbp.applyFilterAndOpenForDetails("Form Name", auditFormName2);
		TestValidation.IsTrue(applyFilterAndOpenForDetails, 
				"Verify form is displayed in Forms Tab", 
				"Form is NOT displayed in Forms Tab");

	}	


	@AfterClass(alwaysRun = true)
	public void closeBrowser() throws InterruptedException {
		driver.close();
	}
}
