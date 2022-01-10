package com.project.safetychain.webapp.testcases;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


import org.testng.SkipException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.project.safetychain.webapp.pageobjects.*;
import com.project.safetychain.webapp.pageobjects.CommonPage.TIMEZONE;
import com.project.safetychain.webapp.pageobjects.DocumentManagementPage.DOC_VIEWER_TOOLBAR;
import com.project.safetychain.webapp.pageobjects.DocumentManagementPage.TABS_IN_DOC_DETAILS_SCECTION;
import com.project.safetychain.webapp.pageobjects.DocumentManagementPage.TOOLTIPOPTIONS;
import com.project.safetychain.webapp.pageobjects.RolesManagerPage.COLUMN_HEADER;
import com.project.safetychain.webapp.pageobjects.RolesManagerPage.ROLE_PERMISSION;
import com.project.safetychain.webapp.pageobjects.UserManagerPage.UsrDetailParams;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.utilities.CommonMethods;
import com.project.utilities.ControlActions;

public class TCG_REG_DMS_ToolbarFlows extends TestBase {

	ControlActions controlActions;
	HomePage hp;
	LoginPage lp;
	ResourceDesignerPage resourceDesigner;
	ManageResourcePage manageResource;
	FSQABrowserPage browserPage;
	CommonPage mainPage;
	FSQABrowserDocumentsPage fbdp;
	ManageLocationPage locations;
	DocumentManagementPage documentManagement;
	WorkGroupsPage workGroupPage;
	InboxPage indboxPage;
	RolesManagerPage rmp;
	
	public static String workGroupName;
	
	public static String locationCategoryValue ;
	public static String locationInstanceValue ;
	
	public static String mainDropDownValue ;
	
	public static String resourceCategoryValue;
	public static String resourceCategoryInstanceValue;
	
	public static String[][] resourceCatInstances;
	
	public static String documentType1;
	public static String documentType2;

	public static String delRestoreFile = "delete.png";
	public static String delRestorePath = System.getProperty("user.dir") + "\\test-data-files\\UploadDocuments\\"+delRestoreFile;
	
	public static String attribute;
	
	  //File for Deleting
	  public static String deleteFile = "Delete.png";
	  public static String deleteFilePath = System.getProperty("user.dir") + "\\test-data-files\\UploadDocuments\\FSQA-Documents\\"+deleteFile;
	
	  //File for EditDetails DMS
	  public static String editDetailsFile1 = "EditDetails1.xlsx";
	  public static String editDetailsFilePath1 = System.getProperty("user.dir") + "\\test-data-files\\UploadDocuments\\FSQA-Documents\\"+editDetailsFile1;
	  
	  //File for EditDetails DMS - Documents Viewer
	  public static String editDetailsFile2 = "EditDetails2.xlsx";
	  public static String  editDetailsFilePath2 = System.getProperty("user.dir") + "\\test-data-files\\UploadDocuments\\FSQA-Documents\\"+editDetailsFile2;
	
	  //File for other usages DMS (upload new, download, manage links, assigntask)
	  public static String otherUsagesFile1 = "OtherUsages1.xlsx";
	  public static String otherUsagesFilePath1 = System.getProperty("user.dir") + "\\test-data-files\\UploadDocuments\\FSQA-Documents\\"+otherUsagesFile1;
	  
	  //File for other usages DMS - Documents Viewer (upload new, download, manage links)
	  public static String otherUsagesFile2 = "OtherUsages2.xlsx";
	  public static String otherUsagesFilePath2 = System.getProperty("user.dir") + "\\test-data-files\\UploadDocuments\\FSQA-Documents\\"+otherUsagesFile2;
	  
	  //File for New Version Upload
	  public static String  newVersionUploadFile = "NewVersionUpload.png";
	  public static String  newVersionUploadFilePath = System.getProperty("user.dir") + "\\test-data-files\\UploadDocuments\\FSQA-Documents\\"+newVersionUploadFile;
	
	  //No Document Type File
	  public static String  noDocTypeFile = "ABC1.csv";
	  public static String 	noDocTypeFilePath = System.getProperty("user.dir") + "\\test-data-files\\UploadDocuments\\"+noDocTypeFile;
	
	  //General purpose file - Nothing is done to this except for uploading it.
	  public static String  generalFile = "generalFile.png";
	  public static String 	generalFilePath = System.getProperty("user.dir") + "\\test-data-files\\UploadDocuments\\"+generalFile;
	
	  
	  String files1 = otherUsagesFilePath1 + "\n " + editDetailsFilePath1;
	  String files2 = deleteFilePath + "\n " + otherUsagesFilePath2 + "\n " + editDetailsFilePath2;
	  
	  public static String resourceType= "Customers" ;
	
	  public static String downloadPath = System.getProperty("user.dir") + "\\test-data-files\\DownloadedDocuments";


	@BeforeClass(alwaysRun = true)
	public void groupInit() throws InterruptedException {
		
		
		  documentType1 = CommonMethods.dynamicText("DocType_N1");//"DocType_N2_21.04_17.54.33";
		
		  workGroupName = CommonMethods.dynamicText("WG");//"WG_15.04_18.18.03" ; //
		 	
		  locationCategoryValue = CommonMethods.dynamicText("LCat");
		  locationInstanceValue = CommonMethods.dynamicText("LInst");
		  
		  mainDropDownValue = "Document Types"; 
		  
		  resourceCategoryValue = CommonMethods.dynamicText("Cat_");
		  resourceCategoryInstanceValue = CommonMethods.dynamicText("CatInst_");
		  
		  resourceCatInstances = new String[][] {{ resourceCategoryInstanceValue, "true"}};
		  
		driver = launchbrowser();
		controlActions = new ControlActions(driver);
		controlActions.getUrl(applicationUrl);

		hp = new HomePage(driver);
		locations = new ManageLocationPage(driver);
		resourceDesigner = new ResourceDesignerPage(driver);
		manageResource = new ManageResourcePage(driver);
		browserPage = new FSQABrowserPage(driver);
		fbdp = new FSQABrowserDocumentsPage(driver);
		lp = new LoginPage(driver);
		rmp = new RolesManagerPage(driver);
		//workGroupPage = new WorkGroupsPage(driver);

		// login
		hp = lp.performLogin(adminUsername, adminPassword);
		if (hp.error) {
			TCGFailureMsg = "Could NOT login to application";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
	
		//creating wrkgroup
		WorkGroupsPage wgp = hp.clickWorkGroupsMenu();
		boolean wgcreate = wgp.createWorkGroup(workGroupName, adminUsername);
		TestValidation.IsTrue(wgcreate, 
				"Created workgroup - " + workGroupName, 
				"Workgroup creation failed");
		
	    //creating a document type1 - for DMS Toolbar Options
		documentManagement = hp.clickdocumentsmenu();
		if(!documentManagement.createDocType(documentType1)) {
			TCGFailureMsg = "Could NOT Create Document Type";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
		
		// Uploading Prerequisit Document to documentType1 for DMS-Toolbar Actions
		if (!documentManagement.uploadDocument(files1,documentType1)) {
				TCGFailureMsg = "Could NOT Upload prerequisit files to document type - "	+ documentType1;
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
		}
		
		//creating a document type 2- for DMS-Document Viewer Toolbar Options
		documentType2 = CommonMethods.dynamicText("DocType_N2");//"DocType_N_15.04_18.30.06";//
		if(!documentManagement.createDocType(documentType2)) {
				TCGFailureMsg = "Could NOT Create Document Type";
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
		}
		
		// Uploading Prerequisit Document to documentType2 for DMS-DOCUMENT VIWERE Toolbar Actions
		if (!documentManagement.uploadDocument(files2, documentType2)) {
				TCGFailureMsg = "Could NOT Upload prerequisit files to document type - "+ documentType2;
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
		}
		
	}
	
	
	@Test(description = " DMS || Verify the New version of the document when user performs action from toolbar " )
	public void TestCase_38659() throws Exception {
		
		double oldVersion ;
		double newVersion ;
		String comment = "This is a comment!!";
		
		documentManagement = hp.clickdocumentsmenu();

		boolean selectDocumentType = documentManagement.selectDocumentType(documentType1);
		TestValidation.IsTrue(selectDocumentType, 
							 "Selected Document Type -> " + documentType1,
						      "Could NOT Select Document Type -> " + documentType1);
		
		oldVersion = documentManagement.getDocumentVersion(otherUsagesFile1);
		boolean versionValidation = (oldVersion!=0.0);
		TestValidation.IsTrue(versionValidation,
							  "GOT Current Document version as - >" + oldVersion,
							  "Could NOT GET Document Version");
		
		boolean clickEditDetails = documentManagement.selectDmsToolbarOptionForDocument(otherUsagesFile1, TOOLTIPOPTIONS.EDITDETAILS);
		TestValidation.IsTrue(clickEditDetails, 
							 "OPEN Edit Details Pop Up",
							 "Could NOT Open Edit Details Pop Up");
		
		boolean editDescription = documentManagement.editDocDescription(comment);
		TestValidation.IsTrue(editDescription, 
							  "EDITED Document Description as -> " + comment,
							  "Could NOT EDIT Document Description as -> " + comment);
		
		boolean clickSaveButton = documentManagement.clickSaveButton();
		TestValidation.IsTrue(clickSaveButton, 
							  "CLICKED Save Button",
							  "Could Not CLICK Save Button");
		
		newVersion = documentManagement.getDocumentVersion(otherUsagesFile1);
		TestValidation.IsTrue((newVersion > oldVersion), 
							  "VERIFIED version update to version - > " + newVersion ,
							  "COULD NOT VERIFY version update");
	}

	@Test(description = " DMS || Verify when the document type of any document is changed" )
	public void TestCase_38661() throws Exception {
		
		String docTypeName = CommonMethods.dynamicText("DocType");
		String doctypeNewName = CommonMethods.dynamicText("NewDocType");
		
		documentManagement = hp.clickdocumentsmenu();
		
		boolean createDocType = documentManagement.createDocType(docTypeName);
		TestValidation.IsTrue(createDocType, 
							  "CREATED Document Type with Name - > " + docTypeName  ,
							  "Could NOT Create Document Type with Name - > " + docTypeName );
		
		boolean renameDocType = documentManagement.renameDocumentTypeName(docTypeName,doctypeNewName);
		TestValidation.IsTrue(renameDocType, 
				  			  "RENAMED Document Type -> " + docTypeName + " as -> " +  doctypeNewName ,
				  			  "Could NOT Rename Document Type - > " + docTypeName + " as -> " +  doctypeNewName );
		
		
		TestValidation.IsTrue((doctypeNewName.equals(DocumentManagementPage.DocumentTypeName.getText())),
							  "VERIFIED Document Type Name Change to - > " + doctypeNewName, 
							  "Could NOT Verify Document Type Name Change to - > " + doctypeNewName);
		
	}

	@Test(description = " DMS|| Verify the behavior when user performs the 'Edit Details' from toolbar " )
	public void TestCase_38662() throws Exception {
		
		
		String editDocName = "NewName.xlsx";
		String editDescription = "Edit description form method call";
		String editExpiryDate = "12/31/2025";
		
		documentManagement = hp.clickdocumentsmenu();
		
		boolean selectDocumentType = documentManagement.selectDocumentType(documentType1);
		TestValidation.IsTrue(selectDocumentType, 
							 "Selected Document Type -> " + documentType1,
						      "Could NOT Select Document Type -> " + documentType1);

		boolean clickEditDetails = documentManagement.selectDmsToolbarOptionForDocument(editDetailsFile1, TOOLTIPOPTIONS.EDITDETAILS);
		TestValidation.IsTrue(clickEditDetails, 
							  "OPEN Edit Details Pop Up",
							  "Could NOT Open Edit Details Pop Up");

		boolean performEdit = documentManagement.editAllDetails(editDocName, documentType2, editDescription, editExpiryDate);
		TestValidation.IsTrue(performEdit, 
							  "EDITED document details ->  " + editDetailsFile1,
							  "Could NOT EDIT document details ->  " + editDetailsFile1);		

		boolean openDocument = documentManagement.viewDocument(editDocName);
		TestValidation.IsTrue(openDocument, 
						      "OPEN document ->   " + editDocName,
						      "Could NOT OPEN document ->  " + editDocName);

		boolean verifyDetails = documentManagement.verifyAllChanges(editDocName, documentType2, editDescription, editExpiryDate);
		TestValidation.IsTrue(verifyDetails, 
							  "VERIFIED all Edited details",
							  "VERIFICATION FAILED for performed Edit Details");		
		
		boolean closeDocumentDetails = documentManagement.closeDocumentDetails();
		TestValidation.IsTrue(closeDocumentDetails, 
							  "CLOSED Document Details Section ",
							  "COULD NOT CLOSE Document Derails Section");

		
	}
	
	@Test(description = " DMS|| Verify the behavior when user performs the 'Manage Links' from toolbar " )
	public void TestCase_38663() throws Exception {
		
		String fsqaTab = "Documents";
		
		documentManagement = hp.clickdocumentsmenu();
		
		boolean selectDocumentType = documentManagement.selectDocumentType(documentType1);
		TestValidation.IsTrue(selectDocumentType, 
							 "Selected Document Type -> " + documentType1,
						      "Could NOT Select Document Type -> " + documentType1);
		
		boolean selectDocument = documentManagement.selectDocument(otherUsagesFile1);
		TestValidation.IsTrue(selectDocument, 
				 			  "Selected Document  -> " + otherUsagesFile1,
				 			  "Could NOT Select Document -> " + otherUsagesFile1);
		
		boolean clickManageLinks = documentManagement.selectDmsToolbarOptions(TOOLTIPOPTIONS.MANAGELINKS);
		TestValidation.IsTrue(clickManageLinks, 
							  "OPEN Manage Links Pop Up",
							  "Could NOT Open Manage Links Pop Up");
		
		boolean manageLink =  documentManagement.manageLink();
		TestValidation.IsTrue(manageLink, 
							  "ADDED LINK to Instance ->   " + DocumentManagementPage.linkedResource + "   for Document ->   " + otherUsagesFile2,
							  "Could NOT ADD LINK to instance ->   " + DocumentManagementPage.linkedResource + "   for Document ->   " + otherUsagesFile2);
		
		browserPage = hp.clickFSQABrowserMenu();
		
		boolean selectResourceDropDownandNavigate = browserPage.selectResourceDropDownandNavigate(DocumentManagementPage.linkedResourceType,fsqaTab);
		TestValidation.IsTrue(selectResourceDropDownandNavigate, 
							  "Selected Resource type -> " +DocumentManagementPage.linkedResource +" and Navigated to FSQA ->  " + fsqaTab ,
							  "Could NOT Select Resource type -> " +DocumentManagementPage.linkedResource +" Navigated to FSQA ->  " + fsqaTab);
		
		boolean selectResourceInstance = browserPage.searchSelect( DocumentManagementPage.linkedResource);
		TestValidation.IsTrue(selectResourceInstance, 
							  "Searched and selected instance ->  " +  DocumentManagementPage.linkedResource,
							  "Could NOT search and select instance ->  " +  DocumentManagementPage.linkedResource);
		
		boolean verifyDocument = fbdp.verifyDocUpload(otherUsagesFile1);
		TestValidation.IsTrue(verifyDocument, 
							  "VERIFIED Linked Document successfully -  " + otherUsagesFile1,
							  "Could NOT Verify Linked Document -  " + otherUsagesFile1);
		
	}
	
	@Test(description = " DMS|| Verify the behavior when user performs the 'Upload New' from toolbar " )
	public void TestCase_38664() throws Exception {
		
		documentManagement = hp.clickdocumentsmenu();
		
		boolean uploadnNewVersion = documentManagement.uploadNewVersion(newVersionUploadFilePath,documentType1,newVersionUploadFile);
		TestValidation.IsTrue(uploadnNewVersion, 
							  "Upload new version done of file -> " + newVersionUploadFile,
							  "upload new version failed of file -> " + newVersionUploadFile );

		boolean versionverify = documentManagement.verifyDocumentVersion(newVersionUploadFile);
		TestValidation.IsTrue(versionverify, 
							  "Verified version for new upload file -> " + newVersionUploadFile  ,
							  "Version verification failed for new upload file -> " + newVersionUploadFile);
		
	}
	
	@Test(description = " DMS|| Verify the behavior when user performs the 'Assign task' from toolbar " )
	public void TestCase_38665() throws Exception {
		
		String taskName = CommonMethods.dynamicText("TaskNew_");
		
		documentManagement = hp.clickdocumentsmenu();
		
		boolean selectDocumentType = documentManagement.selectDocumentType(documentType1);
		TestValidation.IsTrue(selectDocumentType, 
							 "Selected Document Type -> " + documentType1,
						      "Could NOT Select Document Type -> " + documentType1);
		
		boolean selectDocument = documentManagement.selectDocument(otherUsagesFile1);
		TestValidation.IsTrue(selectDocument, 
				 			  "Selected Document  -> " + otherUsagesFile1,
				 			  "Could NOT Select Document -> " + otherUsagesFile1);
		
		boolean clickAssignTask = documentManagement.selectDmsToolbarOptions(TOOLTIPOPTIONS.ASSIGNTASK);
		TestValidation.IsTrue(clickAssignTask, 
							  "OPEN Assign Task Pop Up",
							  "Could NOT Open Assign Task Pop Up");
		
		boolean assign = documentManagement.assignTask(otherUsagesFile1,taskName,workGroupName);
		TestValidation.IsTrue(assign, 
							  "Task -> " + taskName+ " ASSIGNED for document - > " + otherUsagesFile1 ,
							  "Could NOT Assign -> " + taskName + " for document -> " + otherUsagesFile1);

		indboxPage = hp.clickInboxMenu();

		boolean enterTask = indboxPage.enterTaskName(taskName);
		TestValidation.IsTrue(enterTask,
							  "ENTERED Task -> " + taskName ,
							  "COULD NOT ENTER Task -> " + taskName );
		
		boolean searchTask = indboxPage.clickSearchButton();
		TestValidation.IsTrue(searchTask,
							  "SEARCHED Task -> " + taskName ,
							  "COULD NOT SEARCH Task -> " + taskName );
		
		boolean verifytask = indboxPage.verifySingleTask(taskName);
		TestValidation.IsTrue(verifytask,
							  "VERIFIED Assigned Task -> " + taskName  + " for Document - " + otherUsagesFile1,
							  "COULD NOT VERIFY Assigned Task -> " + taskName + "for Document - " + otherUsagesFile1 );
		
		
	}
	
	@Test(description = "DMS|| Verify the behavior when user performs the 'Download' from toolbar ")
	public void TestCase_38666() throws Exception {
		
		documentManagement = hp.clickdocumentsmenu();
		
		boolean selectDocumentType = documentManagement.selectDocumentType(documentType1);
		TestValidation.IsTrue(selectDocumentType, 
							 "Selected Document Type -> " + documentType1,
						      "Could NOT Select Document Type -> " + documentType1);
		
		boolean selectDocument = documentManagement.selectDocument(otherUsagesFile1);
		TestValidation.IsTrue(selectDocument, 
				 			  "Selected Document  -> " + otherUsagesFile1,
				 			  "Could NOT Select Document -> " + otherUsagesFile1);
		
		boolean clickDownload = documentManagement.selectDmsToolbarOptions(TOOLTIPOPTIONS.DOWNLOAD);
		TestValidation.IsTrue(clickDownload, 
							  "Clicked Download for document - >" + otherUsagesFile1,
							  "Could NOT Click Download for document - >" + otherUsagesFile1);
		

		boolean verifydownload = documentManagement.verifyFileDownloaded(downloadPath,otherUsagesFile1);
		TestValidation.IsTrue(verifydownload, 
							  "Document Download " +otherUsagesFile1+ " is Verified",
							  "Document Download "+otherUsagesFile1+ " is not verified");
		
		
	}
	
	@Test(description = " DMS|| Verify the behavior when user performs the 'Delete' the documents from toolbar " )
	public void TestCase_38667() throws Exception {
		
		documentManagement = hp.clickdocumentsmenu();
		
		boolean delete = documentManagement.deleteDocument(deleteFilePath,documentType1,deleteFile);
		TestValidation.IsTrue(delete, 
							  "Document deleted - > " + deleteFile ,
							  "Document deletion FAILED " + deleteFile);
		
		boolean verifyDelete = documentManagement.verifyDocAbsence(deleteFile);
		TestValidation.IsTrue(verifyDelete, 
							  "Verified Deletion of Document successfully - " + deleteFile,
							  "Could NOT Verify deletion of Document - " + deleteFile);
	}
	
	@Test(description = " DMS || Verify the permission for various action from Roles ", priority = -1 )
	public void TestCase_38669() throws Exception {
		
		List<String> userLocation = new ArrayList<String>();;
		List<String> userRole = new ArrayList<String>();;
		String userUN = CommonMethods.dynamicText("UN");
		String userFN = CommonMethods.dynamicText("FN");
		String userLN = CommonMethods.dynamicText("LN");
		
		String searchText = "Document Management System";
		String filterText = "Manage Links";
		String role = CommonMethods.dynamicText("DMS");
		
		
		//try {
//		hp.clickHomepageLink();
		rmp = hp.clickRolesMenu();
		
		boolean createRole = rmp.createRole(role);
		TestValidation.IsTrue(createRole, 
							 "Created role - " + role, 
							 "Could not create role - " + role);

		userLocation.add("ALL");
		userRole.add(role);
		UserManagerPage ump = hp.clickUsersMenu();
		UsrDetailParams udp = new UsrDetailParams();
		udp.Username = userUN;
		udp.Password = GenericPassword;
		udp.FirstName = userFN;
		udp.LastName = userLN;
		udp.Email = "test@test.com";
		udp.Timezone = TIMEZONE.REPUBLICOFINDIA;
		udp.Locations = userLocation;
		udp.Roles = userRole;
		
		boolean userCreation = ump.createInternalUser(udp);
		TestValidation.IsTrue(userCreation, 
							 "Successfully Created user - " + userUN, 
				             "Could NOT create user - " + userUN);
		
		
		boolean loginNewUser = lp.loginAfterUserCreation(userUN, GenericPassword, GenericNewPassword);
		TestValidation.IsTrue(loginNewUser, 
							  "Logged In with new user - " + userUN, 
							  "Could NOT login with user - " + userUN);
		try {
		// Before Changing Permission
		documentManagement = hp.clickdocumentsmenu();
		
		boolean selectDocumentType = documentManagement.selectDocumentType(documentType1);
		TestValidation.IsTrue(selectDocumentType, 
							 "Selected Document Type -> " + documentType1,
						      "Could NOT Select Document Type -> " + documentType1);
		
		boolean optionPresent = documentManagement.isToolbarOptionPresent(otherUsagesFile1, TOOLTIPOPTIONS.MANAGELINKS);
		TestValidation.IsTrue(optionPresent, 
							 "Tools Option ->   " + TOOLTIPOPTIONS.MANAGELINKS + "   is Present",
							 "Tools Option ->   " + TOOLTIPOPTIONS.MANAGELINKS + "   is Not Present");
		
		rmp = hp.clickRolesMenu();
		
		boolean changePermission = rmp.searchAndSetRoleForModule(role,searchText,COLUMN_HEADER.ACTION,filterText,ROLE_PERMISSION.NO);
		TestValidation.IsTrue(changePermission,
							 "CHANGED permission of Role : \"" + role + "\", Module : \"" + searchText + "\", Action : \"" + filterText + "\", To - \"NO\"" ,
							 "COULD NOT CHANGE permission of Role : \"" + role + "\", Module : \"" + searchText + "\", Action : \"" + filterText + "\", To - \"NO\"" );
		
		hp.userLogout();
		//===========================================================
		
		//After Changing Permission
		hp = lp.performLogin(userUN, GenericNewPassword);
		
		documentManagement = hp.clickdocumentsmenu();
		
		boolean selectDocumentType2 = documentManagement.selectDocumentType(documentType1);
		TestValidation.IsTrue(selectDocumentType2, 
							 "Selected Document Type -> " + documentType1,
						      "Could NOT Select Document Type -> " + documentType1);
		
		
		boolean optionPresent1 = documentManagement.isToolbarOptionPresent(otherUsagesFile1, TOOLTIPOPTIONS.MANAGELINKS);
		TestValidation.IsFalse(optionPresent1, 
							 "Tools Option ->   " + TOOLTIPOPTIONS.MANAGELINKS + "   is NOT Present",
							 "Tools Option ->   " + TOOLTIPOPTIONS.MANAGELINKS + "   is STILL Present");
		
		TestValidation.IsFalse(optionPresent1, 
							  "VERIFIED permission Change for Action -   " + TOOLTIPOPTIONS.MANAGELINKS + "   Successfully" , 
							  "Could NOT VERIFY permission Change for Action -   " + TOOLTIPOPTIONS.MANAGELINKS);
		
//		}finally{
//			
//		//========== Changing the permission back to Yes ===================//
//		rmp = hp.clickRolesMenu();
//		boolean changePermission1 = rmp.searchAndSetRoleForModule(role,searchText,COLUMN_HEADER.ACTION,filterText,ROLE_PERMISSION.YES);
//		TestValidation.IsTrue(changePermission1, 
//							  "CHANGED permission of Role : \"" + role + "\", Module : \"" + searchText + "\", Action : \"" + filterText + "\", To - \"YES\"" ,
//							  "COULD NOT CHANGE permission of Role : \"" + role + "\", Module : \"" + searchText + "\", Action : \"" + filterText + "\", To - \"YES\"" );
//		
//		hp.userLogout();
//		
//		hp = lp.performLogin(adminUsername, adminPassword);
//		}
		}finally{
			hp.userLogout();
			hp = lp.performLogin(adminUsername, adminPassword);
			
		}
	}
	
	@Test(description = " Verify Upload document through DMS  " )
	public void TestCase_30741() throws Exception {
		
		String docType = CommonMethods.dynamicText("DocTypeM_");
		
		documentManagement = hp.clickdocumentsmenu();
		
		boolean createDocType = documentManagement.createDocType(docType);
		TestValidation.IsTrue(createDocType, 
							  "CREATED Document Type with Name - > " + docType  ,
							  "Could NOT Create Document Type with Name - > " + docType );
		
		boolean uploadDoc = documentManagement.uploadDocument(otherUsagesFilePath1,docType);
		TestValidation.IsTrue(uploadDoc, 
							  "UPLOADED Document -> " + otherUsagesFile1,
							  "Could NOT UPLOAD Document -> " + otherUsagesFile1);
		
		boolean verify = documentManagement.verifyDocUpload(otherUsagesFile1);
		TestValidation.IsTrue(verify, 
							  "Document Uploaded " +otherUsagesFile1+ "Verified",
							  "Document uploaded "+otherUsagesFile1+ "not verified");
	}
	
	@Test(description = "  Verify functionality of Delete and restore document " )
	public void TestCase_31236() throws Exception {
		
		documentManagement = hp.clickdocumentsmenu();
		
		boolean delete = documentManagement.deleteDocument(delRestorePath,documentType1,delRestoreFile);
		TestValidation.IsTrue(delete, 
				"Document deleted ->   " + delRestoreFile,
				"Document deletion failed for ->   "+ delRestoreFile);

		boolean restore = documentManagement.restoreDocument(documentType1, delRestoreFile);
		TestValidation.IsTrue(restore, 
				"Document restored   " + delRestoreFile,
				"Document restoration failed for ->   "+ delRestoreFile);

		boolean verify = documentManagement.verifyRestoration(documentType1, delRestoreFile);
		TestValidation.IsTrue(verify, 
				"Document restoration verified for ->   " + delRestoreFile,
				"Document restoration verification failed for ->   "+delRestoreFile);
	}
	

	//======================== DMS - DOC VIEWER =====================================//
	
	@Test(description = "  DMS -Document Viewer || Verify the behavior when user performs the 'Edit Details' from toolbar  " )
	public void TestCase_38736() throws Exception {

		String editDocName = "NewName_DMS_DOCViewer.xlsx";
		String editDescription = "Edit description form method call";
		String editExpiryDate = "12/31/2025";
		
		documentManagement = hp.clickdocumentsmenu();
		
		boolean selectDocumentType = documentManagement.selectDocumentType(documentType2);
		TestValidation.IsTrue(selectDocumentType, 
							 "Selected Document Type -> " + documentType2,
						      "Could NOT Select Document Type -> " + documentType2);
		
		boolean viewDocument = documentManagement.viewDocument(editDetailsFile2);
		TestValidation.IsTrue(viewDocument, 
						      "OPEN document ->   " + editDetailsFile2,
						      "Could NOT OPEN document ->  " + editDetailsFile2);
		
		boolean openEditDetails = documentManagement.selectDocViewerToolbarOptions(DOC_VIEWER_TOOLBAR.EDIT_DETAILS);
		TestValidation.IsTrue(openEditDetails, 
							  "OPEN Edit Details Pop Up",
							  "Could NOT Open Edit Details Pop Up");

		boolean performEdit = documentManagement.editAllDetails(editDocName, documentType1, editDescription, editExpiryDate);
		TestValidation.IsTrue(performEdit, 
							  "EDITED document details ->  " + editDetailsFile2,
							  "Could NOT EDIT document details ->  " + editDetailsFile2);		

		boolean openDocumentDetails = documentManagement.openDocumentDetailsSection();
		TestValidation.IsTrue(openDocumentDetails, 
							  "CLOSED Document Details Section ",
							  "COULD NOT CLOSE Document Derails Section");
		
		boolean verifyDetails = documentManagement.verifyAllChanges(editDocName, documentType1, editDescription, editExpiryDate);
		TestValidation.IsTrue(verifyDetails, 
							  "VERIFIED all Edited details",
							  "VERIFICATION FAILED for performed Edit Details");		
		
		boolean closeDocumentDetails = documentManagement.closeDocumentDetails();
		TestValidation.IsTrue(closeDocumentDetails, 
							  "CLOSED Document Details Section ",
							  "COULD NOT CLOSE Document Derails Section");
	}
	
	@Test(description = "  DMS -Document Viewer || Verify the behavior when user performs the 'Manage Links' from toolbar  " )
	public void TestCase_38737() throws Exception {

		documentManagement = hp.clickdocumentsmenu();
		
		boolean selectDocumentType = documentManagement.selectDocumentType(documentType2);
		TestValidation.IsTrue(selectDocumentType, 
							 "Selected Document Type -> " + documentType2,
						      "Could NOT Select Document Type -> " + documentType2);
		
		boolean viewDocument = documentManagement.viewDocument(otherUsagesFile2);
		TestValidation.IsTrue(viewDocument, 
						      "OPEN document ->   " + otherUsagesFile2,
						      "Could NOT OPEN document ->  " + otherUsagesFile2);

		boolean openedDetailsSection = documentManagement.openDocumentDetailsSection();
		TestValidation.IsTrue(openedDetailsSection, 
							  "OPEN Document Details Section ->   " + otherUsagesFile2,
							  "Could NOT OPEN Document Details Section for Document ->   " + otherUsagesFile2);

		boolean linksTab = documentManagement.clickTabInDocumentDetailsSection(TABS_IN_DOC_DETAILS_SCECTION.LINKS);
		TestValidation.IsTrue(linksTab, 
							  "OPEN Links Tab for Document ->   " + otherUsagesFile2,
							  "Could NOT OPEN Links Tab for Document ->   " + otherUsagesFile2);
		
		boolean manageDocumentsLinksClicked = documentManagement.clickManageDocumentsLinks();
		TestValidation.IsTrue(manageDocumentsLinksClicked,
							  "OPEN Manage Links popup",	
							  "Could NOT OPEN Manage Links popup");

		boolean manageLink =  documentManagement.manageLink();
		TestValidation.IsTrue(manageLink, 
							  "ADDED LINK to Instance ->   " + DocumentManagementPage.linkedResource + "   for Document ->   " + otherUsagesFile2,
							  "Could NOT ADD LINK to instance ->   " + DocumentManagementPage.linkedResource + "   for Document ->   " + otherUsagesFile2);
		
		boolean verifyLink = documentManagement.verifyLink(DocumentManagementPage.linkedResource);
		TestValidation.IsTrue(verifyLink, 
							  "VERIFIED linking of document ->   " + otherUsagesFile2 + "   with resource ->   " + DocumentManagementPage.linkedResource,
							  "Could NOT VERIFY linking of document ->   " + otherUsagesFile2 + "   with resource ->   " + DocumentManagementPage.linkedResource);
		
		boolean closeDocumentDetails = documentManagement.closeDocumentDetails();
		TestValidation.IsTrue(closeDocumentDetails, 
							  "CLOSED Document Details Section ",
							  "COULD NOT CLOSE Document Details Section");
	
	}
	
	@Test(description = "  DMS - Document Type view - A 'No Document Type' item appears in the Document Type list for files not assigned a document type   " )
	public void TestCase_6362() throws Exception {
		
		String noDocumentType = "No Document Type";
		
	     // Locations Creation 
		 boolean createLocation= locations.createLocation(locationCategoryValue, locationInstanceValue);
		 TestValidation.IsTrue(createLocation, 
			  				   "Created LocationCategory" + locationCategoryValue + "and Location " + locationCategoryValue,
			  				   "Could NOT create LocationCategory" + locationCategoryValue + "and Location " + locationCategoryValue);
	  
		 resourceDesigner = hp.clickResourceDesignerMenu(); 
		 
		 // Create category 
		 boolean categoryCreation = resourceDesigner.createACategory(resourceType,resourceCategoryValue);
		 TestValidation.IsTrue(categoryCreation, 
				 			   "CREATED  Category - " + resourceCategoryValue,
				 			   "Could NOT create Category - " + resourceCategoryValue);
	  
	  
		 //Create Category Instance 
		 boolean categoryInstanceCreation = manageResource.addResourceInstances(resourceType, resourceCategoryValue, resourceCatInstances, true, locationInstanceValue);
		 TestValidation.IsTrue(categoryInstanceCreation, 
				 			  "CREATED Category Instance - " + resourceCatInstances,
					  		  "Could NOT create Category Instance - " + resourceCatInstances);
	  
		 documentManagement = hp.clickdocumentsmenu();
		 
		 
		 boolean selectResource = documentManagement.selectResourceFrmDrpDwn(resourceType);
		 TestValidation.IsTrue(selectResource,
							  "SELECTED Dropdown -> " + resourceType ,
							  "Could NOT SELECT Dropdown -> " + resourceType);
	  

		 boolean selectResourceInstance = documentManagement.searchSelect(resourceCategoryInstanceValue);
		 TestValidation.IsTrue(selectResourceInstance, 
							  "Searched and selected instance -> " + resourceCategoryInstanceValue,
							  "Could NOT search and select instance ->  " + resourceCategoryInstanceValue);
		
		
		 boolean uploadDoc = documentManagement.uploadDocument(noDocTypeFilePath);
		 TestValidation.IsTrue(uploadDoc, 
							 "UPLOADED Document -> " + noDocTypeFile,
							 "Could NOT UPLOAD Document -> " + noDocTypeFile);
		
		 
		 boolean selecDrpDwnAndCategory = documentManagement.selectDrpDwnAndDocType(noDocumentType);
		 TestValidation.IsTrue(selecDrpDwnAndCategory,
							  "SELECTED Dropdown -> " + mainDropDownValue + "and category -> " + noDocumentType ,
							  "Could NOT SELECT Dropdown -> " + mainDropDownValue + "and category -> " + noDocumentType );
		
		 
		 boolean isDocPresent =  documentManagement.isDocumentPresent(noDocTypeFile);
		 TestValidation.IsTrue(isDocPresent, 
							  "Document Presence VERIFIED for document -> " + noDocTypeFile + " Under 'No Document Type' ",
							  "Could NOT VERIFY presence of document -> " + noDocTypeFile + " Under 'No Document Type' ");
		
	}	
	
	@Test
	(description = " DMS -Document Viewer || Verify the behavior when user performs the 'Assign Tasks' from toolbar " )
	public void TestCase_38738() throws Exception {

		String taskName = CommonMethods.dynamicText("Task_new");
		
		documentManagement = hp.clickdocumentsmenu();
		
		boolean selectDocumentType = documentManagement.selectDocumentType(documentType2);
		TestValidation.IsTrue(selectDocumentType, 
							 "Selected Document Type -> " + documentType2,
						      "Could NOT Select Document Type -> " + documentType2);
		
		boolean viewDocument = documentManagement.viewDocument(otherUsagesFile2);
		TestValidation.IsTrue(viewDocument, 
						      "OPEN document ->   " + otherUsagesFile2,
						      "Could NOT OPEN document ->  " + otherUsagesFile2);
		
		boolean openEditDetails = documentManagement.selectDocViewerToolbarOptions(DOC_VIEWER_TOOLBAR.ASSIGN_TASK);
		TestValidation.IsTrue(openEditDetails, 
							  "OPEN Assign Task Pop Up",
							  "Could NOT Open Assign Task Pop Up");
		
		boolean assign = documentManagement.assignTask(otherUsagesFile2,taskName,workGroupName);
		TestValidation.IsTrue(assign, 
							  "Task -> " + taskName+ " ASSIGNED for document - > " + otherUsagesFile2 ,
							  "Could NOT Assign -> " + taskName + " for document -> " + otherUsagesFile2);

		hp.clickHomepageLink();
	
		indboxPage = hp.clickInboxMenu();

		boolean enterTask = indboxPage.enterTaskName(taskName);
		TestValidation.IsTrue(enterTask,
							  "ENTERED Task -> " + taskName ,
							  "COULD NOT ENTER Task -> " + taskName );
		
		boolean searchTask = indboxPage.clickSearchButton();
		TestValidation.IsTrue(searchTask,
							  "SEARCHED Task -> " + taskName ,
							  "COULD NOT SEARCH Task -> " + taskName );
		
		boolean verifytask = indboxPage.verifySingleTask(taskName);
		TestValidation.IsTrue(verifytask,
							  "VERIFIED Assigned Task -> " + taskName  + " for Document - " + otherUsagesFile2,
							  "COULD NOT VERIFY Assigned Task -> " + taskName + "for Document - " + otherUsagesFile2 );
	}
	
	@Test(description = "DMS -Document Viewer || Verify the behavior when user performs the 'Download' from toolbar " )
	public void TestCase_38739() throws Exception {

	documentManagement = hp.clickdocumentsmenu();
	
	boolean selectDocumentType = documentManagement.selectDocumentType(documentType2);
	TestValidation.IsTrue(selectDocumentType, 
						 "Selected Document Type -> " + documentType2,
					      "Could NOT Select Document Type -> " + documentType2);
	
	boolean viewDocument = documentManagement.viewDocument(otherUsagesFile2);
	TestValidation.IsTrue(viewDocument, 
					      "OPEN document ->   " + otherUsagesFile2,
					      "Could NOT OPEN document ->  " + otherUsagesFile2);
	
	boolean openEditDetails = documentManagement.selectDocViewerToolbarOptions(DOC_VIEWER_TOOLBAR.DOWNLOAD);
	TestValidation.IsTrue(openEditDetails, 
						  "Download file - > " + otherUsagesFile2,
						  "Could NOT Download file -> " + otherUsagesFile2);
	
	boolean verifydownload = documentManagement.verifyFileDownloaded(downloadPath,otherUsagesFile2);
	TestValidation.IsTrue(verifydownload, 
						  "Document Download " +otherUsagesFile2+ " is Verified",
						  "Document Download "+otherUsagesFile2+ " is not verified");

	}
	
	@Test(description = " DMS|| Verify the behavior when Disable the document type  " )
	public void TestCase_38536() throws Exception {
		
	String documentType = CommonMethods.dynamicText("DDoc_");

	documentManagement = hp.clickdocumentsmenu();
	
	boolean createDocType = documentManagement.createDocType(documentType);
	TestValidation.IsTrue(createDocType, 
						  "CREATED Document Type with Name - > " + documentType  ,
						  "Could NOT Create Document Type with Name - > " + documentType );
	
	boolean renameDocType = documentManagement.disableDocumentType(documentType);
	TestValidation.IsTrue(renameDocType, 
			  			  "DISABLED Document Type -> " + documentType,
			  			  "Could NOT DISABLE Document Type - > " + documentType);
	
	boolean verifyDisable = documentManagement.verifyDisabledDocType(documentType);
	TestValidation.IsTrue(verifyDisable, 
						 "Verified DISABLE Status for Document Type -> " + documentType,
						 "Could NOT Verify DISABLE Status for Document Type -> " + documentType);
	
	}
	
	@Test(description = " DMS-Document Viewer|| Verify the Details tab which opens on " )
	public void TestCase_38743() throws Exception {

	String expDate = LocalDate.now().plusDays(2).format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
	
	List<String> gridDetails = null;
		
	String documentType, 
	documentName = generalFile, fileName = generalFile,
	size, description ="This is Description", 
	status = "Expiring Soon";
	
	documentManagement = hp.clickdocumentsmenu();
	
	boolean selectDocumentType = documentManagement.selectDocumentType(documentType2);
	TestValidation.IsTrue(selectDocumentType, 
						 "Selected Document Type -> " + documentType2,
					      "Could NOT Select Document Type -> " + documentType2);
	
	 boolean uploadDoc = documentManagement.uploadDocument(generalFilePath);
	 TestValidation.IsTrue(uploadDoc, 
						 "UPLOADED Document -> " + generalFile,
						 "Could NOT UPLOAD Document -> " + generalFile);
	
	boolean clickEditDetails = documentManagement.selectDmsToolbarOptionForDocument(generalFile, TOOLTIPOPTIONS.EDITDETAILS);
	TestValidation.IsTrue(clickEditDetails, 
						 "OPEN Edit Details Pop Up",
						 "Could NOT Open Edit Details Pop Up"); 
	 
	boolean editDescription = documentManagement.editDocDescription(description);
	TestValidation.IsTrue(editDescription, 
						  "EDITED Document Description as -> " + description,
						  "Could NOT EDIT Document Description as -> " + description);
		
	boolean editExpDate = documentManagement.editDocExpiryDate(expDate);
	TestValidation.IsTrue(editExpDate, 
						  "EDITED Document Expiry Date to -> " + expDate,
						  "Could NOT EDIT Document Expiry Date to -> " + expDate);
	
	boolean clickSaveButton = documentManagement.clickSaveButton();
	TestValidation.IsTrue(clickSaveButton, 
						  "CLICKED Save Button",
						  "Could Not CLICK Save Button");
	 
	 gridDetails = documentManagement.getAllDetailsOfDocFromGrid(generalFile);
	 TestValidation.IsTrue((gridDetails!=null), 
			 				"Fetched Document Details for  -> " + generalFile,
			 				"Could NOT Fetch Document Details for -> " + generalFile);
	 
	 documentType = gridDetails.get(4);
	 size = gridDetails.get(2);

	boolean openDocument = documentManagement.viewDocument(generalFile);
	TestValidation.IsTrue(openDocument, 
					      "OPEN document ->   " + generalFile,
					      "Could NOT OPEN document ->  " +generalFile);
	
	boolean openedDetailsSection = documentManagement.openDocumentDetailsSection();
	TestValidation.IsTrue(openedDetailsSection, 
						  "OPEN Document Details Section ->   " + generalFile,
						  "Could NOT OPEN Document Details Section for Document ->   " + generalFile);

	boolean verifyDeailsTabDetails = documentManagement.verifyDetailsTab(documentName, documentType, fileName, size, description, expDate, status);
	TestValidation.IsTrue(verifyDeailsTabDetails, 
					      "VERIFED all the deatils in Details Tab for document ->   " + generalFile,
					      "Could NOT VERIFY deatils in Details Tab for document ->  " + generalFile);
	
	boolean closeDocumentDetails = documentManagement.closeDocumentDetails();
	TestValidation.IsTrue(closeDocumentDetails, 
						  "CLOSED Document Details Section ",
						  "COULD NOT CLOSE Document Derails Section");
	}
	
	@Test(description = " DMS-Document Viewer|| Verify the Details tab which opens on " )
	public void TestCase_38742() throws Exception {

	String expDate = LocalDate.now().plusDays(2).format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
	
	List<String> gridDetails = null;
		
	String documentType, 
	documentName = generalFile,
	size, comment ="This is Description", modifiedBy;
	
	documentManagement = hp.clickdocumentsmenu();
	
	boolean selectDocumentType = documentManagement.selectDocumentType(documentType1);
	TestValidation.IsTrue(selectDocumentType, 
						 "Selected Document Type -> " + documentType1,
					      "Could NOT Select Document Type -> " + documentType1);
	
	 boolean uploadDoc = documentManagement.uploadDocument(generalFilePath);
	 TestValidation.IsTrue(uploadDoc, 
						 "UPLOADED Document -> " + generalFile,
						 "Could NOT UPLOAD Document -> " + generalFile);
	 
	 boolean uploadNew = documentManagement.uploadNewVersion(generalFile, newVersionUploadFilePath,comment,expDate);
	TestValidation.IsTrue(uploadNew,
							  "UPLOADED New Version file-  " + newVersionUploadFile+" for Document ->  " + generalFile,
							  "Could NOT UPLOAD New Version file-  " +newVersionUploadFile+" for Document ->  " + generalFile);
	 
	 gridDetails = documentManagement.getAllDetailsOfDocFromGrid(generalFile);
	 TestValidation.IsTrue((gridDetails!=null), 
			 				"Fetched Document Details for  -> " + generalFile,
			 				"Could NOT Fetch Document Details for -> " + generalFile);
	 
	 modifiedBy = gridDetails.get(0);
	 System.out.println(modifiedBy);
	 documentType = gridDetails.get(4);
	 size = gridDetails.get(2);

	boolean openDocument = documentManagement.viewDocument(generalFile);
	TestValidation.IsTrue(openDocument, 
					      "OPEN document ->   " + generalFile,
					      "Could NOT OPEN document ->  " +generalFile);
	
	boolean openedDetailsSection = documentManagement.openDocumentDetailsSection();
	TestValidation.IsTrue(openedDetailsSection, 
						  "OPEN Document Details Section ->   " + generalFile,
						  "Could NOT OPEN Document Details Section for Document ->   " + generalFile);

	boolean linksTab = documentManagement.clickTabInDocumentDetailsSection(TABS_IN_DOC_DETAILS_SCECTION.HISTORY);
	TestValidation.IsTrue(linksTab, 
						  "OPEN History Tab for Document ->   " + generalFile,
						  "Could NOT OPEN History Tab for Document ->   " + generalFile);
	
	boolean verifyDeailsTabDetails = documentManagement.verifyHistoryTab(documentName, modifiedBy, DocumentManagementPage.uploadNewFileName, size, comment, expDate, documentType);
	TestValidation.IsTrue(verifyDeailsTabDetails, 
						"VERIFED all the deatils in History Tab for document ->   " + generalFile,
						"Could NOT VERIFY deatils in History Tab for document ->  " +generalFile);
	
	boolean closeDocumentDetails = documentManagement.closeDocumentDetails();
	TestValidation.IsTrue(closeDocumentDetails, 
						  "CLOSED Document Details Section ",
						  "COULD NOT CLOSE Document Derails Section");

	}
	
	@Test(description = "  DMS -Document Viewer || Verify the behavior when user performs the 'Print' from toolbar  " )
	public void TestCase_38741() throws Exception {

	documentManagement = hp.clickdocumentsmenu();
	
	boolean selectDocumentType = documentManagement.selectDocumentType(documentType2);
	TestValidation.IsTrue(selectDocumentType, 
						 "Selected Document Type -> " + documentType2,
					      "Could NOT Select Document Type -> " + documentType2);

	boolean openDocument = documentManagement.viewDocument(otherUsagesFile2);
	TestValidation.IsTrue(openDocument, 
					      "OPEN document ->   " + otherUsagesFile2,
					      "Could NOT OPEN document ->  " +otherUsagesFile2);
	
	boolean print = documentManagement.handlePrint();
	TestValidation.IsTrue(print, 
						  "VERIFIED print functionality successfully for document  ->   " + otherUsagesFile2,
						  "Could NOT VERIFY print functionality successfully for documentt ->   " + otherUsagesFile2);
	}
	
	@Test(description = " DMS -Document Viewer || Verify the behavior when user performs the 'Delete' from toolbar  " )
	public void TestCase_38740() throws Exception {

	documentManagement = hp.clickdocumentsmenu();
	
	boolean selectDocumentType = documentManagement.selectDocumentType(documentType2);
	TestValidation.IsTrue(selectDocumentType, 
						 "Selected Document Type -> " + documentType2,
					      "Could NOT Select Document Type -> " + documentType2);

	boolean openDocument = documentManagement.viewDocument(deleteFile);
	TestValidation.IsTrue(openDocument, 
					      "OPEN document ->   " + deleteFile,
					      "Could NOT OPEN document ->  " +deleteFile);
	
	boolean openEditDetails = documentManagement.dmsDocViewerDelete(DOC_VIEWER_TOOLBAR.DELETE);
	TestValidation.IsTrue(openEditDetails, 
						  "Performed Delete for file - > " + deleteFile,
						  "Could NOT Perform Delete for file -> " + deleteFile);
	
	boolean verifyDelete = documentManagement.verifyDocAbsence(deleteFile);
	TestValidation.IsTrue(verifyDelete, 
						  "Verified Deletion of Document successfully - " + deleteFile,
						  "Could NOT Verify deletion of Document - " + deleteFile);

	}
		
	@AfterClass(alwaysRun = true)
	public void closeBrowser() throws InterruptedException {
		driver.close();
	}

}
