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
import com.project.safetychain.webapp.pageobjects.HomePage;
import com.project.safetychain.webapp.pageobjects.LoginPage;
import com.project.safetychain.webapp.pageobjects.ProgramDesignerPage;
import com.project.safetychain.webapp.pageobjects.ProgramDesignerPage.PGDSGNTABS;
import com.project.safetychain.webapp.pageobjects.ProgramDesignerPage.PROGRAMFIELD;
import com.project.safetychain.webapp.pageobjects.ProgramDesignerPage.ProgramEleParams;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.utilities.CommonMethods;
import com.project.utilities.ControlActions;

public class TCG_SMK_ProgramDesigner_BasicFlows extends TestBase {

	ControlActions controlActions;
	static CommonMethods commonMethods;
	public static String formName;
	public static String locationCategoryValue;
	public static String locationInstanceValue;
	public static String customersCategoryValue;
	public static String customersInstanceValue;
	public static String programName; 
	public static String programElementName;
	public static String numFieldName;
	public static String docTypeName1;
	public static String docTypeName2;
	public static String documentName1 = "TestCase_17364.png";
	public static String documentName2 = "TestCase_17370.png";
	public static String filePath1 = System.getProperty("user.dir") + "\\test-data-files\\UploadDocuments\\"+documentName1;
	public static String filePath2 = System.getProperty("user.dir") + "\\test-data-files\\UploadDocuments\\"+documentName2;
	public static List<String> documentList1 = Arrays.asList(filePath1); 
	public static List<String> documentList2 = Arrays.asList(filePath2); 
	public static List<String> documentNMLst = new ArrayList<String>();
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
		docTypeName1 = CommonMethods.dynamicText("ProgDoc1");
		docTypeName2 = CommonMethods.dynamicText("ProgDoc2");

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
		
		DocumentManagementPage dmp = hp.clickdocumentsmenu();
		HashMap<String,List<String>> addFiles = new HashMap<String,List<String>>();
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

	}

	@Test(description = "Program Views - Admin Tool: Tenant admin can create a new program name ,"
			+ "program heading and program elements")
	public void TestCase_35351() throws Exception {
		String programName = CommonMethods.dynamicText("PD");
		String headerName = CommonMethods.dynamicText("HE");
		String elementName = CommonMethods.dynamicText("PE");

		HomePage hp = new HomePage(driver);
		ProgramDesignerPage pdp = hp.clickProgramDesignerMenu();
		TestValidation.Equals(pdp.error, 
							  false, 
							  "CLICKED on Program Designer menu",
							  "Could NOT click on Program Designer menu");

		boolean pgSetName = pdp.setProgramName(programName);
		TestValidation.IsTrue(pgSetName, 
							  "Name SET for Program as - " + programName,
							  "Could NOT set Program name as - " + programName);

		boolean pgeHeaderName = pdp.addProgramHeader(headerName);
		TestValidation.IsTrue(pgeHeaderName, 
						      "Name SET for Program Header as - " + headerName,
						      "Could NOT set Program Header name as - " + headerName);

		boolean pgeSetName = pdp.addProgramElement(elementName);
		TestValidation.IsTrue(pgeSetName, 
							  "Name SET for Program Element as - " + elementName,
							  "Could NOT set Program Element name as - " + elementName);

		hp.clickFSQABrowserMenu();
		pdp = hp.clickProgramDesignerMenu();
		ProgramEleParams pep = new ProgramEleParams();
		pep.Type = PROGRAMFIELD.ELEMENT;
		pep.ProgramName = programName;
		pep.HeaderName = headerName;
		pep.ElementName = elementName;
		boolean vrfyPgmNm = pdp.verifyProgramElementHeader(pep);
		TestValidation.IsTrue(vrfyPgmNm, 
							  "Verified element Name as - " + pep.ElementName,
							  "Could NOT verify element name as - " + pep.ElementName);

	}

	@Test(description = "Program Views - Admin Tool: Tenant admin can associate individual documents "
			+ "with a program element(12032)")
	public void TestCase_17370() throws Exception {

		HomePage hp = new HomePage(driver);
		ProgramDesignerPage pdp = hp.clickProgramDesignerMenu();
		boolean searchProgram = pdp.searchProgram(programName);
		TestValidation.IsTrue(searchProgram, 
						      "SEARCHED program named - " + programName,
							  "Could NOT search program named - " + programName);

		boolean selectElement = pdp.selectProgramElementUnderProgram(programElementName);
		TestValidation.IsTrue(selectElement, 
							  "Program element " + programElementName + " has SELECTED - ",
							  "Could NOT select Program element " + programElementName);

		boolean selectDocTab = pdp.clickProgramDesignerTab(PGDSGNTABS.DOCUMENTTYPE);
		TestValidation.IsTrue(selectDocTab, 
							  "SELECTED Document Tab for Program element - " + programElementName,
							  "Could NOT select Document Tab for Program element - " + programElementName);
		
		documentNMLst.add(documentName2);
		boolean pdAddDocument = pdp.selectAndSaveDocuments(documentNMLst, docTypeName2);
		TestValidation.IsTrue(pdAddDocument,
							  "ADDED Documents " + documentNMLst,
							  "Could NOT add Documents " + documentNMLst);

		boolean verifyDocs = pdp.verifyDocumentsFromProgramDesigner(documentNMLst);
		TestValidation.IsTrue(verifyDocs, 
							  "VERIFIED Documents " + documentNMLst, 
							  "Could NOT verify Documents " + documentNMLst);

	}

	@Test(description = "Program Views - Admin Tool: Tenant admin can associate\r\n" + 
			"document types with a program element(12023)")
	public void TestCase_17364() throws Exception {
		
		HomePage hp = new HomePage(driver);
		ProgramDesignerPage pdp = hp.clickProgramDesignerMenu();
		boolean searchProgram = pdp.searchProgram(programName);
		TestValidation.IsTrue(searchProgram, 
						      "SEARCHED program named - " + programName,
							  "Could NOT search program named - " + programName);

		boolean selectElement = pdp.selectProgramElementUnderProgram(programElementName);
		TestValidation.IsTrue(selectElement, 
							  "Program element " + programElementName + " has SELECTED - ",
							  "Could NOT select Program element " + programElementName);

		documentTypeNMLst.add(docTypeName1);
		boolean pdAddDocumentType = pdp.selectDocumentType(documentTypeNMLst);
		TestValidation.IsTrue(pdAddDocumentType,
							  "ADDED Document Type " + documentTypeNMLst,
							  "Could NOT add Document Type " + documentTypeNMLst);

		boolean verifyDocType = pdp.verifyDocTypeFromProgramDesigner(documentTypeNMLst);
		TestValidation.IsTrue(verifyDocType, 
							  "VERIFIED DocType - " + documentTypeNMLst,
							  "Could NOT verify DocType - " + documentTypeNMLst);

	}

	@AfterClass
	public void closeBrowser() throws InterruptedException {
		driver.close();
	}

}
