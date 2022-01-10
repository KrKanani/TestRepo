package com.project.safetychain.webapp.testcases;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.project.safetychain.webapp.pageobjects.DocumentManagementPage;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage;
import com.project.safetychain.webapp.pageobjects.HomePage;
import com.project.safetychain.webapp.pageobjects.LoginPage;
import com.project.safetychain.webapp.pageobjects.ManageLocationPage;
import com.project.safetychain.webapp.pageobjects.ManageResourcePage;
import com.project.safetychain.webapp.pageobjects.ProgramDesignerPage;
import com.project.safetychain.webapp.pageobjects.ProgramViewerPage;
import com.project.safetychain.webapp.pageobjects.ResourceDesignerPage;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FIELD_TYPES;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FORMRESOURCETYPES;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FORMTYPES;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FormDesignParams;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FormFieldParams;
import com.project.safetychain.webapp.pageobjects.ManageLocationPage.LocationInstanceParams;
import com.project.safetychain.webapp.pageobjects.ManageResourcePage.RESOURCETYPES;
import com.project.safetychain.webapp.pageobjects.ManageResourcePage.ResourceDetailParams;
import com.project.safetychain.webapp.pageobjects.ResourceDesignerPage.CATEGORYTYPES;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.utilities.CommonMethods;
import com.project.utilities.ControlActions;

public class TCG_SMK_Programs_BasicFlow extends TestBase {

	ControlActions controlActions;
	static CommonMethods commonMethods;
	public static String formName;
	public static String locationCategoryValue;
	public static String locationInstanceValue;
	public static String customersCategoryValue;
	public static String customersInstanceValue;
	public static String programName; 
	public static String programElementName;
	public static String programDesc;
	public static String numFieldName;
	public static String docTypeName1;
	public static String docTypeName2;
	public static String documentName1 = "TestCase_17364.png";
	public static String documentName2 = "TestCase_17370.png";
	public static String filePath1 = System.getProperty("user.dir") + "\\test-data-files\\UploadDocuments\\"+documentName1;
	public static String filePath2 = System.getProperty("user.dir") + "\\test-data-files\\UploadDocuments\\"+documentName2;
	public static List<String> documentList1 = Arrays.asList(filePath1); 
	public static List<String> documentList2 = Arrays.asList(filePath2); 
	public static HashMap<String,List<String>> addFiles = new HashMap<String,List<String>>();
	public static List<String> documentNMLst = new ArrayList<String>();
	public static List<String> formsNMLst = new ArrayList<String>();
	public static List<String> documentTypeNMLst = new ArrayList<String>();

	@BeforeClass
	public void groupInit() throws InterruptedException {

		formName = CommonMethods.dynamicText("Chk");
		locationCategoryValue = CommonMethods.dynamicText("Loc_Cat");
		locationInstanceValue = CommonMethods.dynamicText("Loc_Inst");
		customersCategoryValue = CommonMethods.dynamicText("Cust_Cat");
		customersInstanceValue = CommonMethods.dynamicText("Cust_Inst");
		numFieldName = CommonMethods.dynamicText("Num");
		programName = CommonMethods.dynamicText("PD");
		programElementName = CommonMethods.dynamicText("PE");
		programDesc = CommonMethods.dynamicText("Prog_Desc");
		docTypeName1 = CommonMethods.dynamicText("ProgDoc1");
		docTypeName2 = CommonMethods.dynamicText("ProgDoc2");
		documentNMLst.add(documentName2);
		documentTypeNMLst.add(docTypeName1);
		formsNMLst.add(formName);
		
		driver = launchbrowser();
		controlActions = new ControlActions(driver);
		controlActions.getUrl(applicationUrl);

		LoginPage lp = new LoginPage(driver);
		HomePage hp = lp.performLogin(adminUsername, adminPassword);
		if (hp.error) {
			TCGFailureMsg = "Could NOT login to application";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
		
		//Category creation
		HashMap<String,String> categories = new HashMap<String, String>();
		categories.put(CATEGORYTYPES.CUSTOMERS, customersCategoryValue);
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

		//Resource creation and linking
		HashMap<String,Boolean> instances = new HashMap<String, Boolean>();
		instances.put(customersInstanceValue, true);
		ResourceDetailParams rd = new ResourceDetailParams();
		rd.CategoryName = customersCategoryValue;
		rd.CategoryType = RESOURCETYPES.CUSTOMERS;
		rd.NumberFieldValue = 30;
		rd.TextFieldValue = "ABC";
		rd.InstanceNames = instances;
		rd.LocationInstanceValue = locationInstanceValue;	
		ManageResourcePage mrp = hp.clickResourcesMenu();
		if(!mrp.createResourceLinkLocation(rd)) {
			TCGFailureMsg = "Could NOT create Instances for resource - " + customersCategoryValue;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
		
		//FORM - Creation and Release
		HashMap<String, List<String>> fields = new HashMap<String, List<String>>();
		fields.put(FIELD_TYPES.NUMERIC, Arrays.asList(numFieldName));

		FormFieldParams ffp = new FormFieldParams();
		ffp.FieldDetails = fields;

		HashMap<String, List<String>> resource = new HashMap<String, List<String>>();
		resource.put(FORMRESOURCETYPES.CUSTOMERS, Arrays.asList(customersCategoryValue));

		FormDesignParams fp = new FormDesignParams();
		fp.FormType = FORMTYPES.CHECK;
		fp.FormName = formName;
		fp.SelectResources = resource;
		fp.DesignFields = Arrays.asList(ffp);

		FormDesignerPage fdp = hp.clickFormDesignerMenu();
		if(!fdp.createAndReleaseForm(fp)) {
			TCGFailureMsg = "Could NOT create and release form - " + formName;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
		
		DocumentManagementPage dmp = hp.clickdocumentsmenu();
		addFiles.put(docTypeName1, documentList1);
		addFiles.put(docTypeName2, documentList2);
		if(!dmp.uploadDocInDocType(addFiles)) {
			TCGFailureMsg = "Could NOT add files to document type - " + addFiles;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}	

		ProgramDesignerPage pdp = hp.clickProgramDesignerMenu();
		if(!pdp.setProgramName(programName)) {
			TCGFailureMsg = "Could NOT set Program name as - " + programName;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		if(!pdp.addProgramElement(programElementName)){
			TCGFailureMsg = "Could NOT set Program element as - " + programElementName;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		if(!pdp.setDescription(programDesc)){
			TCGFailureMsg = "Could NOT add description to the Details Tab - " + programDesc ;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		if(!pdp.selectDocumentType(documentTypeNMLst)){
			TCGFailureMsg = "Could NOT add Document Type " + documentTypeNMLst + " to Program Designer";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		if(!pdp.selectAndSaveDocuments(documentNMLst, docTypeName2)){
			TCGFailureMsg = "Could NOT add Documents " + documentNMLst + " to Program Designer";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		if(!pdp.selectForms(formsNMLst)){
			TCGFailureMsg = "Could NOT add Forms " + formsNMLst + " to Program Designer";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
	}

	@Test(description = "Program Views - Admin Tool || Tenant admin can associate forms with a program element.(12041)")
	public void TestCase_17437() throws Exception {
		
		String frmName = CommonMethods.dynamicText("ChkFrm");
		HomePage hp = new HomePage(driver);
		
		HashMap<String, List<String>> fields = new HashMap<String, List<String>>();
		fields.put(FIELD_TYPES.NUMERIC, Arrays.asList(numFieldName));

		FormFieldParams ffp = new FormFieldParams();
		ffp.FieldDetails = fields;

		HashMap<String, List<String>> resource = new HashMap<String, List<String>>();
		resource.put(FORMRESOURCETYPES.CUSTOMERS, Arrays.asList(customersCategoryValue));

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

		hp.clickFSQABrowserMenu();
		ProgramDesignerPage pdp = hp.clickProgramDesignerMenu();
		boolean searchProgram = pdp.searchProgram(programName);
		TestValidation.IsTrue(searchProgram, 
						      "SEARCHED program named - " + programName,
							  "Could NOT search program named - " + programName);

		boolean selectElement = pdp.selectProgramElementUnderProgram(programElementName);
		TestValidation.IsTrue(selectElement, 
							  "Program element " + programElementName + " has SELECTED - ",
							  "Could NOT select Program element " + programElementName);
		
		List<String> formsLst = new ArrayList<String>();
		formsLst.add(frmName);
		boolean pdAddForms = pdp.selectForms(formsLst);
		TestValidation.IsTrue(pdAddForms, 
							  "ADDED Forms " + formsLst + " to Program Element",
							  "Could NOT add Forms " + formsLst + " to Program Element");

		boolean vrfyFrmNm = pdp.verifyFormsFromProgramDesigner(formsLst);
		TestValidation.IsTrue(vrfyFrmNm, 
					     	  "Verified Form - " + formsLst,
					     	  "Could NOT verify Form as - " + formsLst);

	}

	@Test(description = "Program Management - Program Viewer: Tenant user can view a document in the Document Viewer"
			+ " from the Program Viewer Documents tab. (12113)")
	public void TestCase_17243() throws Exception {

		HomePage hp = new HomePage(driver);
		hp.clickFSQABrowserMenu();
		ProgramViewerPage pvp = hp.clickProgramsMenu();
		TestValidation.Equals(pvp.error, 
						      false, 
						      "CLICKED on Programs  menu", 
						      "Could NOT click on Programs menu");

		boolean pdSelectPrgram = pvp.selectPrograms(programName);
		TestValidation.IsTrue(pdSelectPrgram,
							  "SELECTED program " + programName + " from the Program list",
							  "Could NOT select program " + programName + " from the Program list");

		HashMap<String,List<String>> verifyFiles = new HashMap<String,List<String>>();
		verifyFiles.put(docTypeName1, Arrays.asList(documentName1));
		verifyFiles.put(docTypeName2, Arrays.asList(documentName2));
		boolean pdVerifyDoc = pvp.verifyDocumentsFromPrograms(verifyFiles);
		TestValidation.IsTrue(pdVerifyDoc, 
							  "VERIFIED Documents " + documentNMLst  + " " + documentTypeNMLst + " in Program  - " + programName,
							  "Could NOT verify Documents " + documentNMLst  + " " + documentTypeNMLst + " in Program  - " + programName);

	}

	@Test(description = "Program Management - Program Viewer: Tenant user can view a list of forms associated "
			+ "with a program element in the Forms Tab(12122)")
	public void TestCase_17246() throws Exception {

		HomePage hp = new HomePage(driver);
		hp.clickFSQABrowserMenu();
		ProgramViewerPage pvp = hp.clickProgramsMenu();
		TestValidation.Equals(pvp.error, 
			      			  false, 
			      			  "CLICKED on Programs  menu", 
			      			  "Could NOT click on Programs menu");
		
		boolean pdSelectPrgram = pvp.selectPrograms(programName);
		TestValidation.IsTrue(pdSelectPrgram,
							  "SELECTED program " + programName + " from the Program list",
							  "Could NOT select program " + programName + " from the Program list");

		boolean pdVerifyForms = pvp.verifyFormsFromPrograms(formsNMLst);
		TestValidation.IsTrue(pdVerifyForms, 
							  "VERIFIED Forms " + formsNMLst + " in Program  - " + programName,
							  "Could NOT verify Forms " + formsNMLst + " in Program  - " + programName);

	}

	@Test(description = "Program Management - Program Viewer: Tenant user can view details for a program element(12108)")
	public void TestCase_17229() throws Exception {
		
		HomePage hp = new HomePage(driver);
		hp.clickFSQABrowserMenu();
		ProgramViewerPage pvp = hp.clickProgramsMenu();
		TestValidation.Equals(pvp.error, 
			      			  false, 
			      			  "CLICKED on Programs  menu", 
			      			  "Could NOT click on Programs menu");
		
		boolean pdSelectPrgram = pvp.selectPrograms(programName);
		TestValidation.IsTrue(pdSelectPrgram,
							  "SELECTED program " + programName + " from the Program list",
							  "Could NOT select program " + programName + " from the Program list");

		boolean pdVerifyDesc = pvp.verifyProgramDetailsTabFromPrograms(programDesc);
		TestValidation.IsTrue(pdVerifyDesc, 
							  "Verified Desc " + programDesc + " in Program  - " + programName,
							  "Could NOT verify Desc " + programDesc + " in Program  - " + programName);

	}

	@Test(description = "Program Views - Admin Tool :Tenant admin can remove individual document,document types and "
			+ "Forms associations from a program element(12035)", priority=100)
	public void TestCase_17409() throws Exception {
		
		HomePage hp = new HomePage(driver);
		hp.clickFSQABrowserMenu();
		ProgramDesignerPage pdp = hp.clickProgramDesignerMenu();
		boolean searchProgram = pdp.searchProgram(programName);
		TestValidation.IsTrue(searchProgram, 
						      "SEARCHED program named - " + programName,
							  "Could NOT search program named - " + programName);

		boolean selectElement = pdp.selectProgramElementUnderProgram(programElementName);
		TestValidation.IsTrue(selectElement, 
							  "Program element " + programElementName + " has SELECTED - ",
							  "Could NOT select Program element " + programElementName);

		boolean vrfyDocType = pdp.verifyDocTypeFromProgramDesigner(documentTypeNMLst);
		TestValidation.IsTrue(vrfyDocType, 
							  "VERIFIED Documents Type(s) " + documentTypeNMLst + " added", 
							  "Could NOT verify added Document Type(s) - " + documentTypeNMLst);

		boolean delDocType = pdp.deselectDocTypeFromProgramDesigner(documentTypeNMLst);
		TestValidation.IsTrue(delDocType, 
							  "DESELECTED Document Type(s) - " + documentTypeNMLst,
							  "Could NOT deselect Document Type(s) - " + documentTypeNMLst);

		boolean vrfyDoc = pdp.verifyDocumentsFromProgramDesigner(documentNMLst);
		TestValidation.IsTrue(vrfyDoc, 
							  "VERIFIED Document(s) " + documentNMLst + " added",
							  "Could NOT verify Document(s) - " + documentNMLst);

		boolean delDoc = pdp.deselectDocument(documentNMLst);
		TestValidation.IsTrue(delDoc, 
							  "DESELECTED Document(s) - " + documentNMLst,
							  "Could NOT deselect Document(s) - " + documentNMLst);

		boolean vrfyForm = pdp.verifyFormsFromProgramDesigner(formsNMLst);
		TestValidation.IsTrue(vrfyForm, 
							  "VERIFIED Form(s) - " + formsNMLst + " added",
							  "Could NOT verify Form(s) - " + formsNMLst);

		boolean delForm = pdp.deselectFormsFromProgramDesigner(formsNMLst);
		TestValidation.IsTrue(delForm, 
							  "DESELECTED Form(s) - " + formsNMLst,
							  "Could NOT deselect Form(s) - " + formsNMLst);
	}

	@AfterClass
	public void closeBrowser() throws InterruptedException {
		driver.close();
	}

}
