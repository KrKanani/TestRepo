package com.project.safetychain.webapp.testcases;

import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.project.safetychain.webapp.pageobjects.CommonPage;
import com.project.safetychain.webapp.pageobjects.DocumentManagementPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserDocumentsPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage;
import com.project.safetychain.webapp.pageobjects.HomePage;
import com.project.safetychain.webapp.pageobjects.LoginPage;
import com.project.safetychain.webapp.pageobjects.ManageLocationPage;
import com.project.safetychain.webapp.pageobjects.ManageResourcePage;
import com.project.safetychain.webapp.pageobjects.ResourceDesignerPage;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.utilities.CommonMethods;
import com.project.utilities.ControlActions;

public class TCG_SMK_DocumentViewer extends TestBase{
	ControlActions controlActions;
	CommonPage mainPage;
	LoginPage lgnPge;
	ResourceDesignerPage resourceDesigner;
	ManageResourcePage manageResource;
	ManageLocationPage locations;
	HomePage hp;
	FSQABrowserDocumentsPage fbdp;
	DocumentManagementPage dmp;

	//Data
	public static String locationCategoryValue;
	public static String resourceType = "Customers";
	public static String resourceCategoryValue;
	public static String resourceInstanceValue1,resourceInstanceValue2;
	public static String locationInstanceValue;
	public static String uploadfile;
	public static String uploadfile1;
	public static String uploadfile2;
	public static String uploadfile3;
	public static String image;
	public static String image1;
	public static String image2;
	public static String image3;
	public static String downloadPath;
	public static String doctype;
	

	@BeforeClass(alwaysRun = true)
	public void groupInit() throws Exception {

		locationCategoryValue = CommonMethods.dynamicText("Location_Cat");
		resourceCategoryValue = CommonMethods.dynamicText("Resource_Cat");
		resourceInstanceValue1 = CommonMethods.dynamicText("Resource_Inst1");
		resourceInstanceValue2 = CommonMethods.dynamicText("Resource_Inst2");
		locationInstanceValue = CommonMethods.dynamicText("Location_Inst");
		image = "upload.png";
		image1 = "uploadnew.jpg";
		image2 = "assigntask.png";
		image3 = "delete.png";
		uploadfile = System.getProperty("user.dir") + "\\test-data-files\\UploadDocuments\\"+image;
		uploadfile1 = System.getProperty("user.dir") + "\\test-data-files\\UploadDocuments\\"+image1;
		uploadfile2 = System.getProperty("user.dir") + "\\test-data-files\\UploadDocuments\\"+image2;
		uploadfile3 = System.getProperty("user.dir") + "\\test-data-files\\UploadDocuments\\"+image3;
		downloadPath = System.getProperty("user.dir") + "\\test-data-files\\DownloadedDocuments\\";
		doctype = CommonMethods.dynamicText("Doc");
		String resourceInstances[][] = {{resourceInstanceValue1,"true"},{resourceInstanceValue2,"false"}};


		driver = launchbrowser();
		controlActions = new ControlActions(driver);
		controlActions.getUrl(applicationUrl);
		mainPage = new CommonPage(driver);
		lgnPge = new LoginPage(driver);
		resourceDesigner = new ResourceDesignerPage(driver);
		manageResource = new ManageResourcePage(driver);
		locations = new ManageLocationPage(driver);
		fbdp = new FSQABrowserDocumentsPage(driver);

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

		//Resource creation - Link Location -- NEW
		if(!manageResource.createResources(resourceType, resourceCategoryValue, resourceInstances, true, locationInstanceValue)) {
			TCGFailureMsg = "Could NOT Create Resources '"+resourceInstanceValue1+"' & '"+resourceInstanceValue1+"' & Link Location'"+locationInstanceValue+"'";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
		
	}

	@Test(description="Verify upload of documents from resources")
	public void TestCase_34218() {

		dmp = new DocumentManagementPage(driver);
		mainPage.Sync();
		dmp = mainPage.clickdocumentsmenu();
		
		FSQABrowserPage fbp = mainPage.clickFSQABrowserMenu();

		boolean selectResourceDropDownandNavigate =  fbp.selectResourceDropDownandNavigate(resourceType,"Documents");	
		TestValidation.IsTrue(selectResourceDropDownandNavigate, 
				"NAVIGATED to FSQA -> Documents Tab", 
				"Could NOT Navigate to FSQA -> Documents Tab");
		
		boolean selectResource = fbdp.selectResource(resourceType, resourceCategoryValue, resourceInstanceValue1);
		TestValidation.IsTrue(selectResource, 
				"Selected Resource successfully - "+resourceInstanceValue1, 
				"Could NOT Select Resource - "+resourceInstanceValue1); 
	
		boolean uploadDocument = fbdp.uploadDocument(uploadfile);
		TestValidation.IsTrue(uploadDocument, 
				"Uploaded Document successfully - "+image, 
				"Could NOT Upload Document - "+image); 
		
		boolean verifyDocument = fbdp.verifyDocUpload(image);
		TestValidation.IsTrue(verifyDocument, 
				"Verified Uploaded Document successfully - "+image, 
				"Could NOT Verify Uploaded Document - "+image); 
		

	}

	@Test(description="Verify the functionality to perform actions like 'Download' from tool bar")
	public void TestCase_34212() {

		dmp = new DocumentManagementPage(driver);
		mainPage.Sync();
		dmp = mainPage.clickdocumentsmenu();
		
		FSQABrowserPage fbp = mainPage.clickFSQABrowserMenu();

		boolean selectResourceDropDownandNavigate =  fbp.selectResourceDropDownandNavigate(resourceType,"Documents");	
		TestValidation.IsTrue(selectResourceDropDownandNavigate, 
				"NAVIGATED to FSQA -> Documents Tab", 
				"Could NOT Navigate to FSQA -> Documents Tab");
	
		boolean selectResource = fbdp.selectResource(resourceType, resourceCategoryValue, resourceInstanceValue1);
		TestValidation.IsTrue(selectResource, 
				"Selected Resource successfully - "+resourceInstanceValue1, 
				"Could NOT Select Resource - "+resourceInstanceValue1); 
	
		boolean uploadDocument = fbdp.uploadDocument(uploadfile1);
		TestValidation.IsTrue(uploadDocument, 
				"Uploaded Document successfully - "+image1, 
				"Could NOT Upload Document - "+image1); 
		
		boolean verifyDocument = fbdp.verifyDocUpload(image1);
		TestValidation.IsTrue(verifyDocument, 
				"Verified Uploaded Document successfully - "+image1, 
				"Could NOT Verify Uploaded Document - "+image1); 
		
		boolean selectOptionFromTools = fbdp.selectOptionFromTools(image1, "Download");
		TestValidation.IsTrue(selectOptionFromTools, 
				"Selected Download  option from Tools successfully ", 
				"Could NOT select Download from Tools "); 
		
		boolean verifyFileDownloaded = fbdp.verifyFileDownloaded(downloadPath, image1);
		TestValidation.IsTrue(verifyFileDownloaded, 
				"Verified file is downloaded successfully ", 
				"Could NOT verfiy downloaded file "); 
	}

	@Test(description="Verify the functionality to perform actions like Edit details from Document Viewer")
	public void TestCase_34213() {

		dmp = new DocumentManagementPage(driver);
		mainPage.Sync();
		dmp = mainPage.clickdocumentsmenu();
		
		FSQABrowserPage fbp = mainPage.clickFSQABrowserMenu();

		boolean selectResourceDropDownandNavigate =  fbp.selectResourceDropDownandNavigate(resourceType,"Documents");	
		TestValidation.IsTrue(selectResourceDropDownandNavigate, 
				"NAVIGATED to FSQA -> Documents Tab", 
				"Could NOT Navigate to FSQA -> Documents Tab");

		boolean selectResource = fbdp.selectResource(resourceType, resourceCategoryValue, resourceInstanceValue1);
		TestValidation.IsTrue(selectResource, 
				"Selected Resource successfully - "+resourceInstanceValue1, 
				"Could NOT Select Resource - "+resourceInstanceValue1); 
	
		boolean uploadDocument = fbdp.uploadDocument(uploadfile2);
		TestValidation.IsTrue(uploadDocument, 
				"Uploaded Document successfully - "+image2, 
				"Could NOT Upload Document - "+image2); 
		
		boolean verifyDocument = fbdp.verifyDocUpload(image2);
		TestValidation.IsTrue(verifyDocument, 
				"Verified Uploaded Document successfully - "+image2, 
				"Could NOT Verify Uploaded Document - "+image2); 

		dmp = new DocumentManagementPage(driver);
		dmp = mainPage.clickdocumentsmenu();
		boolean doctypecreation = dmp.createDocType(doctype);
		TestValidation.IsTrue(doctypecreation, 
				"Created document type - " + doctype, 
				"Document Type creation failed");
		
		fbp = mainPage.clickFSQABrowserMenu();

		boolean selectResourceDropDownandNavigate1 =  fbp.selectResourceDropDownandNavigate(resourceType,"Documents");	
		TestValidation.IsTrue(selectResourceDropDownandNavigate1, 
				"NAVIGATED to FSQA -> Documents Tab", 
				"Could NOT Navigate to FSQA -> Documents Tab");
		
		fbdp = new FSQABrowserDocumentsPage(driver);
		boolean selectResource1 = fbdp.selectResource(resourceType, resourceCategoryValue, resourceInstanceValue1);
		TestValidation.IsTrue(selectResource1, 
				"Selected Resource successfully - "+resourceInstanceValue1, 
				"Could NOT Select Resource - "+resourceInstanceValue1); 

		boolean selectOptionFromTools = fbdp.selectOptionFromTools(image2, "Edit Details");
		TestValidation.IsTrue(selectOptionFromTools, 
				"Selected Download  option from Tools successfully ", 
				"Could NOT select Download from Tools ");
		
		boolean selectDocumentType = fbdp.selectDocumentType(doctype);
		TestValidation.IsTrue(selectDocumentType, 
				"Selected select Document Type successfully ", 
				"Could NOT select Document Type ");
		
		boolean clickSaveButton = fbdp.clickSaveButton();
		TestValidation.IsTrue(clickSaveButton, 
				"Clicked Save button successfully ", 
				"Could NOT click Save button");
		
		String documentType = fbdp.getDocumentTypeValueFromColumn(image2);
		boolean compare = documentType.contains(doctype);
		TestValidation.IsTrue(compare, 
				"Verified document type successfully ", 
				"Could NOT verify document type");
	}

	@Test(description="Verify the functionality to perform actions like Upload New functionality from Document Viewer")
	public void TestCase_34215() {

		dmp = new DocumentManagementPage(driver);
		mainPage.Sync();
		dmp = mainPage.clickdocumentsmenu();
		
		FSQABrowserPage fbp = mainPage.clickFSQABrowserMenu();

		boolean selectResourceDropDownandNavigate =  fbp.selectResourceDropDownandNavigate(resourceType,"Documents");	
		TestValidation.IsTrue(selectResourceDropDownandNavigate, 
				"NAVIGATED to FSQA -> Documents Tab", 
				"Could NOT Navigate to FSQA -> Documents Tab");
	
		boolean selectResource = fbdp.selectResource(resourceType, resourceCategoryValue, resourceInstanceValue1);
		TestValidation.IsTrue(selectResource, 
				"Selected Resource successfully - "+resourceInstanceValue1, 
				"Could NOT Select Resource - "+resourceInstanceValue1); 
	
		boolean uploadDocument = fbdp.uploadDocument(uploadfile3);
		TestValidation.IsTrue(uploadDocument, 
				"Uploaded Document successfully - "+image3, 
				"Could NOT Upload Document - "+image3); 
		
		boolean verifyDocument = fbdp.verifyDocUpload(image3);
		TestValidation.IsTrue(verifyDocument, 
				"Verified Uploaded Document successfully - "+image3, 
				"Could NOT Verify Uploaded Document - "+image3); 
		
		boolean selectOptionFromTools = fbdp.selectOptionFromTools(image3, "View Document");
		TestValidation.IsTrue(selectOptionFromTools, 
				"Selected View Document  option from Tools successfully ", 
				"Could NOT select View Document option from Tools "); 
		
		controlActions.perform_waitUntilVisibility(fbdp.ViewDocumentSectionTitle);
		
//		boolean clickInfoIcon = fbdp.clickInfoIcon();
//		TestValidation.IsTrue(clickInfoIcon, 
//				"Clicked on Info Icon successfully ", 
//				"Could NOT click on Info Icon ");
		
		boolean openedDetailsSection = fbdp.openDocumentDetails();
		TestValidation.IsTrue(openedDetailsSection, "OPENED Document Details Section ",
				"Could NOT OPEN Document Details Section for Document");
		
		boolean clickTabInDocumentDetailsSection = fbdp.clickTabInDocumentDetailsSection("Details");
		TestValidation.IsTrue(clickTabInDocumentDetailsSection, 
				"Clicked Details Tab In Document Details Section successfully ", 
				"Could NOT Click on Details Tab In Document Details Section "); 
		
		String fileName = fbdp.getFileNameDocDetailsSection();
		boolean compare1 = fileName.contains(image3);
		TestValidation.IsTrue(compare1, 
				"Verified field name successfully ", 
				"Could NOT verfiy field name "); 
	}

	@AfterClass(alwaysRun = true)
	public void closeBrowser() throws InterruptedException {
		driver.close();
	}
}
