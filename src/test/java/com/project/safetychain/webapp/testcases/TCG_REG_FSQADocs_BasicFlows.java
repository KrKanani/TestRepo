package com.project.safetychain.webapp.testcases;

import java.util.ArrayList;
import java.util.List;

import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.project.safetychain.webapp.pageobjects.*;
import com.project.safetychain.webapp.pageobjects.CommonPage.TIMEZONE;
import com.project.safetychain.webapp.pageobjects.FSQABrowserDocumentsPage.DOC_VIEWER_DETAILS_TAB;
import com.project.safetychain.webapp.pageobjects.FSQABrowserDocumentsPage.TABS_IN_DOC_DETAILS_SCECTION;
import com.project.safetychain.webapp.pageobjects.FSQABrowserDocumentsPage.TOOLBAR_OPTIONS;
import com.project.safetychain.webapp.pageobjects.RolesManagerPage.COLUMN_HEADER;
import com.project.safetychain.webapp.pageobjects.RolesManagerPage.ROLE_PERMISSION;
import com.project.safetychain.webapp.pageobjects.UserManagerPage.UsrDetailParams;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.utilities.CommonMethods;
import com.project.utilities.ControlActions;

public class TCG_REG_FSQADocs_BasicFlows extends TestBase {

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
	
	public static String locationCategoryValue ;
	public static String locationInstanceValue ;
	
	public static String resourceType= "Customers" ;
	public static String fsqaTab = "Documents" ;
	
	public static String resourceCategoryValue;
	public static String resourceSubCategoryValue;
	
	public static String resourceCategoryInstanceValue;
	public static String resourceSubCategoryInstanceValue;

	public static String attribute = "aria-activedescendant";
	
	
	  //File for Deleting
	  public static String deleteFile = "Delete.png";
	  public static String deleteFilePath = System.getProperty("user.dir") + "\\test-data-files\\UploadDocuments\\FSQA-Documents\\"+deleteFile;
	
	  //File for EditDetails FSQA->Documents
	  public static String editDetailsFile1 = "EditDetails1.xlsx";
	  public static String editDetailsFilePath1 = System.getProperty("user.dir") + "\\test-data-files\\UploadDocuments\\FSQA-Documents\\"+editDetailsFile1;
	  
	  //File for EditDetails FSQA->Documents - Documents Viewer
	  public static String editDetailsFile2 = "EditDetails2.xlsx";
	  public static String  editDetailsFilePath2 = System.getProperty("user.dir") + "\\test-data-files\\UploadDocuments\\FSQA-Documents\\"+editDetailsFile2;
	
	  //File for other usages FSQA->Documents (upload new, download, manage links)
	  public static String otherUsagesFile1 = "OtherUsages1.xlsx";
	  public static String otherUsagesFilePath1 = System.getProperty("user.dir") + "\\test-data-files\\UploadDocuments\\FSQA-Documents\\"+otherUsagesFile1;
	  
	  //File for other usages FSQA->Documents - Documents Viewer (upload new, download, manage links)
	  public static String otherUsagesFile2 = "OtherUsages2.xlsx";
	  public static String otherUsagesFilePath2 = System.getProperty("user.dir") + "\\test-data-files\\UploadDocuments\\FSQA-Documents\\"+otherUsagesFile2;
	  
	  //File to SubCategory
	  public static String subCategoryUploadFile = "SubCategoryUpload.xlsx";
	  public static String subCategoryUploadFilePath = System.getProperty("user.dir") + "\\test-data-files\\UploadDocuments\\FSQA-Documents\\"+subCategoryUploadFile;
	
	  //File for New Version Upload
	  public static String newVersionUploadFile = "NewVersionUpload.png";
	  public static String  newVersionUploadFilePath = System.getProperty("user.dir") + "\\test-data-files\\UploadDocuments\\FSQA-Documents\\"+newVersionUploadFile;
	
	
	  String files1 = deleteFilePath + "\n " + otherUsagesFilePath1 + "\n " + editDetailsFilePath1;
	  String files2 = deleteFilePath + "\n " + otherUsagesFilePath2 + "\n " + editDetailsFilePath2;
	
	  public static String downloadPath = System.getProperty("user.dir") + "\\test-data-files\\DownloadedDocuments";

	@BeforeClass(alwaysRun = true)
	public void groupInit() throws InterruptedException {

		
		  locationCategoryValue = CommonMethods.dynamicText("LCat");//"LCat_06.03_11.10.11"; 
		  locationInstanceValue = CommonMethods.dynamicText("LInst");//"LInst1_06.03_02.09.03";
		  resourceCategoryValue = CommonMethods.dynamicText("Cat_");//"Category__06.03_11.10.11";//
		  resourceSubCategoryValue =CommonMethods.dynamicText("SubCat_");// "SubCategory__06.03_11.10.11";//
		  resourceCategoryInstanceValue =CommonMethods.dynamicText("CatInst_");// "Cust_Inst_04.02_11.12.02";//CommonMethods.dynamicText("CatInst_");//"Cust_Inst_04.02_11.12.02";//
		  resourceSubCategoryInstanceValue =CommonMethods.dynamicText("SubCatInst_");//"Cust_Inst_04.02_12.24.01";//
		  
		  String resourceCatInstances[][] = {{ resourceCategoryInstanceValue, "true"}}; 
		  String resourceSubCatInstances[][] = {{ resourceSubCategoryInstanceValue,"true" }};
		 
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

		// login
		hp = lp.performLogin(adminUsername, adminPassword);
		if (hp.error) {
			TCGFailureMsg = "Could NOT login to application";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		  // Locations Creation 
		  if (!locations.createLocation(locationCategoryValue, locationInstanceValue)) { 
			  TCGFailureMsg = "Could NOT create LocationCategory" + locationCategoryValue + "and Location " + locationInstanceValue;
			  logFatal(TCGFailureMsg); throw new SkipException(TCGFailureMsg); 
		  }
		  
		  // navigate to Resource Designer page 
		  resourceDesigner =hp.clickResourceDesignerMenu(); 
		  if (resourceDesigner.error) { 
			  TCGFailureMsg = "Could NOT navigate to ResourceDesigner"; 
			  logFatal(TCGFailureMsg); 
			  throw new SkipException(TCGFailureMsg); 
		  }
		  
		  // Create sub category 
		  if (!resourceDesigner.createSubCategory(resourceType,resourceCategoryValue, resourceSubCategoryValue)) { 
			  TCGFailureMsg = "Could NOT create SUB category - " + resourceSubCategoryValue;
			  logFatal(TCGFailureMsg); 
		  	  throw new SkipException(TCGFailureMsg); 
		  }
		  
		  // Navigate to Resource Designer 
		  manageResource = hp.clickResourcesMenu(); 
		  if(manageResource.error) { 
			  TCGFailureMsg = "Could NOT navigate to ResourceDesigner "; 
			  logFatal(TCGFailureMsg); 
			  throw new SkipException(TCGFailureMsg); 
		  }
		  
		  //Create Category Instance 
		  if(!manageResource.addResourceInstances(resourceType, resourceCategoryValue, resourceCatInstances, true, locationInstanceValue)) { 
			  TCGFailureMsg = "Could NOT create Category Instance - " + resourceCatInstances;
		  logFatal(TCGFailureMsg); throw new SkipException(TCGFailureMsg); }
		  
		  // Create subcategory Instance  
		  if(!manageResource.addResourceInstances(resourceType, resourceCategoryValue, resourceSubCategoryValue, resourceSubCatInstances, true,locationInstanceValue)) { 
			  TCGFailureMsg = "Could NOT create SUB category Instance - " + resourceSubCatInstances;
			  logFatal(TCGFailureMsg); 
			  throw new SkipException(TCGFailureMsg); 
		  }
		 
		
		browserPage = hp.clickFSQABrowserMenu();
		
		if (!browserPage.selectResourceDropDownandNavigate(resourceType, fsqaTab)) {
			TCGFailureMsg = "Could NOT select resource type - " + resourceType + " and FSQA tab - " + fsqaTab;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
		
		// Selecting SubCategory Resource
		if (!browserPage.searchSelect(resourceSubCategoryInstanceValue)) {
			TCGFailureMsg = "Could NOT search and select instance ->" + resourceSubCategoryInstanceValue;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		// Uploading Prerequisit Document to subcategory for FSQA->Documents
		if (!fbdp.uploadDocument(files1)) {
				TCGFailureMsg = "Could NOT Upload prerequisit files to subcategory - "	+ resourceSubCategoryInstanceValue;
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
		}
	
		// Selecting SubCategory Resource 
		if (!browserPage.searchSelect(resourceCategoryInstanceValue)) {
			TCGFailureMsg = "Could NOT search and select instance ->" + resourceCategoryInstanceValue;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
		
		// Uploading Prerequisit Document to Category for FSQA->Documents || Documents Viewer
		if (!fbdp.uploadDocument(files2)){
				TCGFailureMsg = "Could NOT Upload prerequisit files to category - " + resourceCategoryInstanceValue;
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}
		
		
	}

	@Test(description = " FSQA-Documents || Verify the behavior when user performs the 'Edit Details' from toolbar")
	private void TestCase_38717() throws Exception{

		String editDocName = "NewName.xlsx";
		String editDescription = "Edit description form method call";
		String editExpiryDate = "12/31/2021";
		String documentType = CommonMethods.dynamicText("DocType_new");
		
		documentManagement = hp.clickdocumentsmenu();
		
		boolean createDocType = documentManagement.createDocType(documentType);
		TestValidation.IsTrue(createDocType,
							  "Created Document Type - >" + documentType,
							  "Could NOT Create Document Type - >" + documentType);
		
		browserPage = hp.clickFSQABrowserMenu();

		boolean selectResourceDropDownandNavigate = browserPage.selectResourceDropDownandNavigate(resourceType,fsqaTab);
		TestValidation.IsTrue(selectResourceDropDownandNavigate, 
							  "Navigated to FSQA ->  " + fsqaTab ,
							  "Could NOT Navigate to FSQA ->  " + fsqaTab);

		boolean selectResourceInstance = browserPage.searchSelect(resourceSubCategoryInstanceValue);
		TestValidation.IsTrue(selectResourceInstance, 
							  "Searched and selected instance -> " + resourceSubCategoryInstanceValue,
							  "Could NOT search and select instance ->  " + resourceSubCategoryInstanceValue);

		boolean openEditDetails = fbdp.selectOptionFromTools(editDetailsFile1, TOOLBAR_OPTIONS.EDIT_DETAILS);
		TestValidation.IsTrue(openEditDetails, 
							  "OPENED Edit Details for Document ->  " + editDetailsFile1,
							  "Could NOT OPEN Edit Details for Document ->  " + editDetailsFile1);

		boolean performEdit = fbdp.editAllDetails(editDocName, documentType, editDescription, editExpiryDate);
		TestValidation.IsTrue(performEdit, 
							  "EDITED document details ->  " + editDetailsFile1,
							  "Could NOT EDIT document details ->  " + editDetailsFile1);		

		boolean openDocument = fbdp.viewDocument(editDocName);
		TestValidation.IsTrue(openDocument, 
						      "OPENED document ->   " + editDocName,
						      "Could NOT OPEN document ->  " + editDocName);

		boolean verifyDetails = fbdp.verifyAllChanges(editDocName, documentType, editDescription, editExpiryDate);
		TestValidation.IsTrue(verifyDetails, 
							  "VERIFIED all Edited details",
							  "VERIFICATION FAILED for performed Edit Details");		
		
		boolean closeDocumentDetails = fbdp.closeDocumentDetails();
		TestValidation.IsTrue(closeDocumentDetails, 
							  "CLOSED Document Details Section ",
							  "COULD NOT CLOSE Document Derails Section");

}
	
	@Test(description = "Test case 38718: FSQA Documents || Verify the behavior when user performs the 'Manage Links' from toolbar")
	public void TestCase_38718() throws Exception{
		
		browserPage = hp.clickFSQABrowserMenu();
		
		boolean selectResourceDropDownandNavigate = browserPage.selectResourceDropDownandNavigate(resourceType,fsqaTab);
		TestValidation.IsTrue(selectResourceDropDownandNavigate, 
							  "Navigated to FSQA ->  " + fsqaTab ,
							  "Could NOT Navigate to FSQA -  " + fsqaTab);

		boolean selectResourceInstance = browserPage.searchSelect(resourceSubCategoryInstanceValue);
		TestValidation.IsTrue(selectResourceInstance, 
							  "Searched and selected instance ->  " + resourceSubCategoryInstanceValue,
							  "Could NOT search and select instance ->  " + resourceSubCategoryInstanceValue);
		
		boolean openDocument = fbdp.selectOptionFromTools(otherUsagesFile1, TOOLBAR_OPTIONS.MANAGE_LINKS);
		TestValidation.IsTrue(openDocument, 
							  "OPENED Manage Links popup for document->  " + otherUsagesFile1,
							  "Could NOT OPEN Manage Links popup for document ->  " + otherUsagesFile1);
	
		boolean manageLink =  fbdp.manageLinks(resourceType, resourceCategoryInstanceValue);
		TestValidation.IsTrue(manageLink,
							  "ADDED LINK to Instance ->  " + resourceCategoryInstanceValue + "  for Document ->  " + otherUsagesFile1,
							  "Could NOT ADD LINK to INSTANCE ->  " + resourceCategoryInstanceValue + "  for Document ->  " + otherUsagesFile1);
		
		boolean selectResourceInstance_2 = browserPage.searchSelect(resourceCategoryInstanceValue);
		TestValidation.IsTrue(selectResourceInstance_2, 
							  "Searched and selected instance ->   " + resourceCategoryInstanceValue,
							  "Could NOT search and select instance ->   " + resourceCategoryInstanceValue);
		
		boolean verifyDocument = fbdp.verifyDocUpload(otherUsagesFile1);
		TestValidation.IsTrue(verifyDocument, 
							  "VERIFIED Linked Document successfully -  " + otherUsagesFile1,
							  "Could NOT Verify Linked Document -  " + otherUsagesFile1);
	}
	
	@Test(description = "FSQA-Documents|| Verify the behavior when user performs the 'Upload New' from toolbar")
	public void TestCase_38719() throws Exception{
		
		double currentVersion ;
		double newVersion ;
		String comment = "This is comment for new version file upload";
		String expDate = "12/31/2021";
		
		
		browserPage = hp.clickFSQABrowserMenu();
		
		boolean selectResourceDropDownandNavigate = browserPage.selectResourceDropDownandNavigate(resourceType,fsqaTab);
		TestValidation.IsTrue(selectResourceDropDownandNavigate, 
							  "Navigated to FSQA -> " + fsqaTab ,
							  "Could NOT Navigate to FSQA -> " + fsqaTab);

		boolean selectResourceInstance = browserPage.searchSelect(resourceSubCategoryInstanceValue);
		TestValidation.IsTrue(selectResourceInstance, 
							  "Searched and Selected instance ->  " + resourceSubCategoryInstanceValue,
							  "Could NOT search and select instance ->  " + resourceSubCategoryInstanceValue);
		
		currentVersion = fbdp.getDocumentVersion(otherUsagesFile1);

		boolean uploadNew = fbdp.uploadNewVersion(otherUsagesFile1, newVersionUploadFilePath,comment,expDate);
		TestValidation.IsTrue(uploadNew,
							  "UPLOADED New Version file-  " + newVersionUploadFile+" for Document ->  " + otherUsagesFile1,
							  "Could NOT UPLOAD New Version file-  " +newVersionUploadFile+" for Document ->  " + otherUsagesFile1);

		newVersion = fbdp.getDocumentVersion(otherUsagesFile1);
		
		TestValidation.IsTrue(newVersion == (currentVersion+1),
							  "VERIFIED version update " + newVersion ,
							  "Could NOT VERIFY version update");
		
		boolean openDocument = fbdp.viewDocument(otherUsagesFile1);
		TestValidation.IsTrue(openDocument, 
							  "OPENED document ->  " + otherUsagesFile1,
							  "Could NOT OPEN document ->   " + otherUsagesFile1);
		
		boolean verifyNewFile = fbdp.verifyDocumentsDetails(DOC_VIEWER_DETAILS_TAB.FILE_NAME, newVersionUploadFile);
		TestValidation.IsTrue(verifyNewFile,
							  "VERIFIED new file -   "+ newVersionUploadFile ,
							  "Could NOT VERIFY new file -   " + newVersionUploadFile);	
		
		boolean closeDocumentDetails = fbdp.closeDocumentDetails();
		TestValidation.IsTrue(closeDocumentDetails, 
							 "CLOSED Document Details Section ",
							 "Could NOT CLOSE Document Details Section");

		TestValidation.IsTrue(((newVersion == currentVersion+1) && verifyNewFile),
						  	  "Successfully verified new version file upload -   " + newVersionUploadFile,
						  	  "FAILED to verified new version file upload -    " + newVersionUploadFile);
	}	
	
	@Test(description = "Test case 38720: FSQA-Documents|| Verify the behavior when user performs the 'Assign task' from toolbar")
	public void TestCase_38720() throws Exception{
		
		String taskName = CommonMethods.dynamicText("Task_new");
		String note = "This is a Note";
		String workGroup = CommonMethods.dynamicText("WG_new");
		
		workGroupPage = hp.clickWorkGroupsMenu();
		
		boolean createWorkgroup = workGroupPage.createWorkGroup(workGroup,adminUsername);
		TestValidation.IsTrue(createWorkgroup, 
							  "CREATED new Work Group ->  " + workGroup,
							  "Could NOT CREATE new Work Group ->  " + workGroup);
		
		browserPage = hp.clickFSQABrowserMenu();
		
		boolean selectResourceDropDownandNavigate = browserPage.selectResourceDropDownandNavigate(resourceType,fsqaTab);
		TestValidation.IsTrue(selectResourceDropDownandNavigate,
							  "Navigated to FSQA ->   " + fsqaTab ,
							  "Could NOT Navigate to FSQA ->   " + fsqaTab);

		boolean selectResourceInstance = browserPage.searchSelect(resourceSubCategoryInstanceValue);
		TestValidation.IsTrue(selectResourceInstance, 
							  "Searched and selected instance ->   " + resourceSubCategoryInstanceValue,
							  "Could NOT search and select instance ->   " + resourceSubCategoryInstanceValue);
		
		boolean openAssigntask = fbdp.selectOptionFromTools(otherUsagesFile1, TOOLBAR_OPTIONS.ASSIGN_TASK );
		TestValidation.IsTrue(openAssigntask, 
						      "OPENED document ->   " + otherUsagesFile1,
						      "Could NOT OPEN document ->   " + otherUsagesFile1);
		
		boolean assignTask = fbdp.assignTask(otherUsagesFile1,taskName, locationInstanceValue, workGroup, note);
		TestValidation.IsTrue(assignTask,
							  "ASSIGNED Task ->   " + taskName + "   for Document -   " + otherUsagesFile1,
							  "Could NOT ASSIGN Task ->   " + taskName + "   for Document -   " + otherUsagesFile1);

		indboxPage = hp.clickInboxMenu();
		
		boolean enterTask = indboxPage.enterTaskName(taskName);
		TestValidation.IsTrue(enterTask, 
						     "ENTERED Task ->  " + taskName ,
						     "Could NOT ENTER Task ->  " + taskName );
		
		boolean searchTask = indboxPage.clickSearchButton();
		TestValidation.IsTrue(searchTask, 
							  "SEARCHED Task ->  " + taskName ,
							  "Coud NOT SEARCH Task ->  " + taskName );
		
		boolean verifytask = indboxPage.verifySingleTask(taskName);
		TestValidation.IsTrue(verifytask, 
							  "VERIFIED Assigned Task ->   " + taskName  + "   for Document -   " + otherUsagesFile1,
							  "Could NOT VERIFY Assigned Task ->   " + taskName + "  for Document -   " + otherUsagesFile1 );
	}
	
	@Test(description = "FSQA-Documents|| Verify the behavior when user performs the 'Download' from toolbar")
	private void TestCase_38721() throws Exception {
		
		browserPage = hp.clickFSQABrowserMenu();
		
		boolean selectResourceDropDownandNavigate = browserPage.selectResourceDropDownandNavigate(resourceType,fsqaTab);
		TestValidation.IsTrue(selectResourceDropDownandNavigate, 
							 "Navigated to FSQA ->  " + fsqaTab ,
							 "Could NOT Navigate to FSQA ->  " + fsqaTab);

		boolean selectResourceInstance = browserPage.searchSelect(resourceSubCategoryInstanceValue);
		TestValidation.IsTrue(selectResourceInstance, 
							 "Searched and selected instance ->   " + resourceSubCategoryInstanceValue,
							 "Could NOT search and select instance ->  " + resourceSubCategoryInstanceValue);
		
		boolean downloadFile = fbdp.selectOptionFromTools(otherUsagesFile1, TOOLBAR_OPTIONS.DOWNLOAD);
		TestValidation.IsTrue(downloadFile,
							 "DOWNLOADED document ->   " + otherUsagesFile1,
							 "Could NOT DOWNLOAD document ->   " + otherUsagesFile1);
		
		boolean verifyDownload = fbdp.verifyFileDownloaded(downloadPath, otherUsagesFile1);
		TestValidation.IsTrue(verifyDownload,
							  "VERIFIED downloaded document ->   " + otherUsagesFile1,
							  "Could NOT VERIFY doeloaded document ->   " + otherUsagesFile1);
	}
	
	@Test(description = "FSQA-Documents|| Verify the behavior when user performs the 'Delete' the documents from toolbar")
	public void TestCase_38722() throws Exception {
		browserPage = hp.clickFSQABrowserMenu();

		boolean selectResourceDropDownandNavigate = browserPage.selectResourceDropDownandNavigate(resourceType,fsqaTab);
		TestValidation.IsTrue(selectResourceDropDownandNavigate,
							 "Navigated to FSQA ->   " + fsqaTab ,
							 "Could NOT Navigate to FSQA ->   " + fsqaTab);

		boolean selectResourceInstance = browserPage.searchSelect(resourceSubCategoryInstanceValue);
		TestValidation.IsTrue(selectResourceInstance, 
							  "Searched and selected instance ->   " + resourceSubCategoryInstanceValue,
							  "Could NOT search and select instance ->   " + resourceSubCategoryInstanceValue);
		
		boolean deleteDocument = fbdp.deleteFromToolbar(deleteFile);
		TestValidation.IsTrue(deleteDocument, 
							  "DELETED document ->   " + deleteFile,
							  "Could NOT DELETE document ->   " + deleteFile);
		
		boolean verifyDelete = fbdp.verifyDocAbsence(deleteFile);
		TestValidation.IsTrue(verifyDelete, 
							  "VERIFIED Deletion of Document successfully -   " + deleteFile,
							  "Could NOT VERIFY deletion of Document -   " + deleteFile);
	}
	
	@Test(description = "FSQA-Documents || Verify the permission for various action from Roles", priority = -1)
	public void TestCase_38724() throws Exception{
		
		List<String> userLocation = new ArrayList<String>();;
		List<String> userRole = new ArrayList<String>();;
		String userUN = CommonMethods.dynamicText("UN");
		String userFN = CommonMethods.dynamicText("FN");
		String userLN = CommonMethods.dynamicText("LN");
		
		String searchText = "FSQA Browser > Documents";
		String filterText = "Manage Links";
		String role = CommonMethods.dynamicText("FSQADocs");

//		try{
//			hp.clickHomepageLink();
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
		browserPage = hp.clickFSQABrowserMenu();
		
		boolean selectResourceDropDownandNavigate = browserPage.selectResourceDropDownandNavigate(resourceType,fsqaTab);
		TestValidation.IsTrue(selectResourceDropDownandNavigate, 
							  "Navigated to FSQA ->   " + fsqaTab ,
							  "Could NOT Navigate to FSQA ->   " + fsqaTab);

		boolean selectResourceInstance = browserPage.searchSelect(resourceSubCategoryInstanceValue);
		TestValidation.IsTrue(selectResourceInstance, 
							  "Searched and selected instance ->   " + resourceSubCategoryInstanceValue,
							  "Could NOT search and select instance ->   " + resourceSubCategoryInstanceValue);
		
		boolean optionPresent = fbdp.isToolsOptionPresent(otherUsagesFile1, TOOLBAR_OPTIONS.MANAGE_LINKS);
		TestValidation.IsTrue(optionPresent, 
							 "Tools Option ->   " + TOOLBAR_OPTIONS.MANAGE_LINKS + "   is Present",
							 "Tools Option ->   " + TOOLBAR_OPTIONS.MANAGE_LINKS + "   is Not Present");
		
		rmp = hp.clickRolesMenu();
		
		boolean changePermission = rmp.searchAndSetRoleForModule(role,searchText,COLUMN_HEADER.ACTION,filterText,ROLE_PERMISSION.NO);
		TestValidation.IsTrue(changePermission,
							 "CHANGED permission of Role : \"" + role + "\", Module : \"" + searchText + "\", Action : \"" + filterText + "\", To - \"NO\"" ,
							 "COULD NOT CHANGE permission of Role : \"" + role + "\", Module : \"" + searchText + "\", Action : \"" + filterText + "\", To - \"NO\"" );
		
		hp.userLogout();
		//===========================================================
		
		//After Changing Permission
		hp = lp.performLogin(userUN, GenericNewPassword);
		
		browserPage = hp.clickFSQABrowserMenu();
		
		boolean selectResourceDropDownandNavigate1 = browserPage.selectResourceDropDownandNavigate(resourceType,fsqaTab);
		TestValidation.IsTrue(selectResourceDropDownandNavigate1, 
							  "Navigated to FSQA ->   " + fsqaTab ,
							  "Could NOT Navigate to FSQA ->   " + fsqaTab);

		boolean selectResourceInstance1 = browserPage.searchSelect(resourceSubCategoryInstanceValue);
		TestValidation.IsTrue(selectResourceInstance1, 
							  "Searched and selected instance ->   " + resourceSubCategoryInstanceValue,
							  "Could NOT search and select instance ->   " + resourceSubCategoryInstanceValue);
		
		boolean optionPresent1 = fbdp.isToolsOptionPresent(otherUsagesFile1, TOOLBAR_OPTIONS.MANAGE_LINKS);
		TestValidation.IsFalse(optionPresent1,
				 			  "Tools Option ->   " + TOOLBAR_OPTIONS.MANAGE_LINKS + "   is Not Present",
				 			  "Tools Option -> 	 " + TOOLBAR_OPTIONS.MANAGE_LINKS + "   is STILL Present");
		
		TestValidation.IsFalse(optionPresent1, 
							  "VERIFIED permission Change for Action -   " + TOOLBAR_OPTIONS.MANAGE_LINKS + "   Successfully" , 
							  "Could NOT VERIFY permission Change for Action -   " + TOOLBAR_OPTIONS.MANAGE_LINKS);
		
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
	
	@Test(description = "FSQA-Documents - Document Viewer || Verify the behavior when user performs the 'Edit Details' from toolbar")
	public void TestCase_38725() throws Exception{
		
		String editDocName = CommonMethods.dynamicText("NewName_");//"NewNameInViewer.xlsx";
		String editDescription = "Edit description from FSQA->Documents || Documents-Viewer";
		String editExpiryDate = "12/31/2025";
		String documentType = CommonMethods.dynamicText("DocType_new"); //"DocType_new_09.03_23.18.16";//
		
		documentManagement = hp.clickdocumentsmenu();
		
		boolean createDocType = documentManagement.createDocType(documentType);
		TestValidation.IsTrue(createDocType,
							  "CREATED Document Type ->   " + documentType,	
							  "Could NOT CREATE Document Type ->   " + documentType);
		
		browserPage = hp.clickFSQABrowserMenu();
		
		boolean selectResourceDropDownandNavigate = browserPage.selectResourceDropDownandNavigate(resourceType,fsqaTab);
		TestValidation.IsTrue(selectResourceDropDownandNavigate, 
							  "Navigated to FSQA ->   " + fsqaTab ,
							  "Could NOT Navigate to FSQA ->    " + fsqaTab);

		boolean selectResourceInstance = browserPage.searchSelect(resourceCategoryInstanceValue);
		TestValidation.IsTrue(selectResourceInstance, 
							  "Searched and selected instance ->   " + resourceCategoryInstanceValue,
							  "Could NOT search and select instance ->   " + resourceCategoryInstanceValue);
		
		boolean openDocument = fbdp.viewDocument(editDetailsFile2);
		TestValidation.IsTrue(openDocument,
							  "OPENED document ->   " + editDetailsFile2,
							  "Could NOT OPEN document ->   " + editDetailsFile2);
		
		boolean openEditDetails = fbdp.selectVerticleToolbarOptions(TOOLBAR_OPTIONS.EDIT_DETAILS);
		TestValidation.IsTrue(openEditDetails, 
							  "OPENED Edit Details dialogue for Document ->   " + editDetailsFile2,
							  "Could NOT OPEN Edit Details dialogue for Document ->   " + editDetailsFile2);

		boolean performEdit = fbdp.editAllDetails(editDocName, documentType, editDescription, editExpiryDate);
		TestValidation.IsTrue(performEdit, 
							  "EDITED document details ->   " + editDetailsFile2,
							  "Could NOT EDIT document details ->   " + editDetailsFile2);		

		boolean verifyDetails = fbdp.verifyAllChanges(editDocName, documentType, editDescription, editExpiryDate);
		TestValidation.IsTrue(verifyDetails, 
							  "VERIFIED ALL Edited details",
							  "VERIFICATION FAILED for Edited details");
		
		boolean closeDocumentDetails = fbdp.closeDocumentDetails();
		TestValidation.IsTrue(closeDocumentDetails, 
							  "CLOSED Document Details Section ",
							  "COULD NOT CLOSE Document Details Section");
	}
	
	@Test(description = "FSQA Documents-Document Viewer || Verify the behavior when user performs the 'Manage Links' from toolbar")
	public void TestCase_38726() throws Exception{
		
		browserPage = hp.clickFSQABrowserMenu();
		
		boolean selectResourceDropDownandNavigate = browserPage.selectResourceDropDownandNavigate(resourceType,fsqaTab);
		TestValidation.IsTrue(selectResourceDropDownandNavigate, 
							  "Navigated to FSQA ->   " + fsqaTab ,
							  "Could NOT Navigate to FSQA ->   " + fsqaTab);

		boolean selectResourceInstance = browserPage.searchSelect(resourceCategoryInstanceValue);
		TestValidation.IsTrue(selectResourceInstance, 
							 "Searched and selected instance ->   " + resourceCategoryInstanceValue,
							 "Could NOT search and select instance ->   " + resourceCategoryInstanceValue);
		
		boolean openDocument = fbdp.viewDocument(otherUsagesFile2);
		TestValidation.IsTrue(openDocument, 
							  "OPENED document viewer for ->   " + otherUsagesFile2,
							  "Could NOT OPEN document viewer for ->" + otherUsagesFile2);
		
		boolean openedDetailsSection = fbdp.openDocumentDetails();
		TestValidation.IsTrue(openedDetailsSection, 
							  "OPENED Document Details Section ->   " + otherUsagesFile2,
							  "Could NOT OPEN Document Details Section for Document ->   " + otherUsagesFile2);

		boolean linksTab = fbdp.clickTabInDocumentDetailsSection(TABS_IN_DOC_DETAILS_SCECTION.LINKS);
		TestValidation.IsTrue(linksTab, 
							  "OPENED Links Tab for Document ->   " + otherUsagesFile2,
							  "Could NOT OPEN Links Tab for Document ->   " + otherUsagesFile2);
		
		boolean manageDocumentsLinksClicked = fbdp.clickManageDocumentsLinks();
		TestValidation.IsTrue(manageDocumentsLinksClicked,
							  "OPENED Manage Links popup",	
							  "Could NOT OPEN Manage Links popup");

		boolean manageLink =  fbdp.manageLinks(resourceType, resourceSubCategoryInstanceValue);
		TestValidation.IsTrue(manageLink, 
							  "ADDED LINK to Instance ->   " + "resourceSubCategoryInstanceValue" + "   for Document ->   " + otherUsagesFile2,
							  "Could NOT ADD LINK to instance ->   " + "resourceSubCategoryInstanceValue" + "   for Document ->   " + otherUsagesFile2);
		
		boolean verifyLink = fbdp.verifyLink(resourceSubCategoryInstanceValue);
		TestValidation.IsTrue(verifyLink, 
							  "VERIFIED linking of document ->   " + otherUsagesFile2 + "   with resource ->   " + resourceSubCategoryInstanceValue,
							  "Could NOT VERIFY linking of document ->   " + otherUsagesFile2 + "   with resource ->   " + resourceSubCategoryInstanceValue);
		
		boolean closeDocumentDetails = fbdp.closeDocumentDetails();
		TestValidation.IsTrue(closeDocumentDetails, 
							  "CLOSED Document Details Section ",
							  "COULD NOT CLOSE Document Details Section");
		
	}
	
	@Test(description = "FSQA-Documents-Document Viewer|| Verify the behavior when user performs the 'Assign task' from toolbar" )
	public void TestCase_38728() throws Exception{
		
		String taskName = CommonMethods.dynamicText("Task_new");
		String note = "This is a Note";
		String workGroup = CommonMethods.dynamicText("WG_new"); //"WG_new_16.03_15.53.56";
		
		workGroupPage = hp.clickWorkGroupsMenu();
		
		boolean createWorkgroup = workGroupPage.createWorkGroup(workGroup,"autouser01");
		TestValidation.IsTrue(createWorkgroup, 
							 "CREATED new Work Group ->   " + workGroup,
							 "COULD NOT CREATE new Work Group ->   " + workGroup);
		
		browserPage = hp.clickFSQABrowserMenu();
		
		boolean selectResourceDropDownandNavigate = browserPage.selectResourceDropDownandNavigate(resourceType,fsqaTab);
		TestValidation.IsTrue(selectResourceDropDownandNavigate, 
							  "Navigated to FSQA ->   " + fsqaTab ,
							  "Could NOT Navigate to FSQA ->   " + fsqaTab);

		boolean selectResourceInstance = browserPage.searchSelect(resourceCategoryInstanceValue);
		TestValidation.IsTrue(selectResourceInstance, 
							  "Searched and selected instance ->   " + resourceCategoryInstanceValue,
							  "Could NOT search and select instance ->   " + resourceCategoryInstanceValue);
		
		boolean openDocument = fbdp.viewDocument(otherUsagesFile2);
		TestValidation.IsTrue(openDocument, 
						      "OPENED document viewer ->   " + otherUsagesFile2,
						      "Could NOT OPEN document viewer ->   " + otherUsagesFile2);
		
		boolean openAssignTask = fbdp.selectVerticleToolbarOptions(TOOLBAR_OPTIONS.ASSIGN_TASK);
		TestValidation.IsTrue(openAssignTask,
							  "OPENED Assign Task dialogue for ->   " + otherUsagesFile2,
							  "Could NOT OPEN Assign Task dialogue for ->   " + otherUsagesFile2);
		
		boolean assignTask = fbdp.assignTask(otherUsagesFile2,taskName, locationInstanceValue, workGroup, note);
		TestValidation.IsTrue(assignTask, 
							  "ASSIGNED Task ->    " + taskName + "   for Document -   " + otherUsagesFile2,
							  "COULD NOT ASSIGN Task ->   " + taskName + "   for Document -   " + otherUsagesFile2);
		
		hp.clickHomepageLink();
		
		indboxPage = hp.clickInboxMenu();
		
		boolean enterTask = indboxPage.enterTaskName(taskName);
		TestValidation.IsTrue(enterTask, 
						      "ENTERED Task in search box ->   " + taskName ,
						      "COULD NOT ENTER Task in search box ->   " + taskName );
		
		boolean searchTask = indboxPage.clickSearchButton();
		TestValidation.IsTrue(searchTask, 
							  "CLICKED on Search button ->   " + taskName ,
							  "Could NOT CLICK on Search button ->   " + taskName );
			
		boolean verifytask = indboxPage.verifySingleTask(taskName);
		TestValidation.IsTrue(verifytask,
							  "VERIFIED Assigned Task ->   " + taskName  + "   for Document -   " + otherUsagesFile2,
							  "COULD NOT VERIFY Assigned Task ->   " + taskName + "   for Document -   " + otherUsagesFile2 );
	}
	
	@Test(description = "FSQA-Documents-Document Viewer|| Verify the behavior when user performs the 'Download' from toolbar")
	public void TestCase_38729() throws Exception{

		browserPage = hp.clickFSQABrowserMenu();
			
		boolean selectResourceDropDownandNavigate = browserPage.selectResourceDropDownandNavigate(resourceType,fsqaTab);
		TestValidation.IsTrue(selectResourceDropDownandNavigate, 
							 "Navigated to FSQA ->  " + fsqaTab ,
							 "Could NOT Navigate to FSQA ->  " + fsqaTab);

		boolean selectResourceInstance = browserPage.searchSelect(resourceCategoryInstanceValue);
		TestValidation.IsTrue(selectResourceInstance, 
							  "Searched and selected instance ->   " + resourceCategoryInstanceValue,
							  "Could NOT search and select instance ->   " + resourceCategoryInstanceValue);
		
			boolean openDocument = fbdp.viewDocument(otherUsagesFile2);
			TestValidation.IsTrue(openDocument, 
								  "OPENED document ->   " + otherUsagesFile2,
								  "Could NOT OPEN document ->   " + otherUsagesFile2);
			
			boolean downloadFile = fbdp.selectVerticleToolbarOptions(TOOLBAR_OPTIONS.DOWNLOAD);
			TestValidation.IsTrue(downloadFile, 
								  "DOWNLOADED document ->   " + otherUsagesFile2,
								  "Could NOT DOWNLOAD document ->   " + otherUsagesFile2);
	
			boolean verifyDownload = fbdp.verifyFileDownloaded(downloadPath, otherUsagesFile2);
			TestValidation.IsTrue(verifyDownload,
								  "VERIFIED Downloaded document ->   " + otherUsagesFile2,
								  "Could NOT VERIFY Downloaded document ->   " + otherUsagesFile2);

	}
	
	@Test(description = "FSQA-Documents-Document Viewer|| Verify the behavior when user performs the 'Print' the document from toolbar" )
	public void TestCase_38731() throws Exception{
		
		browserPage = hp.clickFSQABrowserMenu();

		boolean selectResourceDropDownandNavigate = browserPage.selectResourceDropDownandNavigate(resourceType,fsqaTab);
		TestValidation.IsTrue(selectResourceDropDownandNavigate, 
							  "Navigated to FSQA ->   " + fsqaTab ,
							  "Could NOT Navigate to FSQA ->   " + fsqaTab);

		boolean selectResourceInstance = browserPage.searchSelect(resourceCategoryInstanceValue);//CatInst__19.03_19.30.12
		TestValidation.IsTrue(selectResourceInstance, 
							  "Searched and selected instance -> " + resourceCategoryInstanceValue,
							  "Could NOT search and select instance ->   " + resourceCategoryInstanceValue);
		
		boolean openDocument = fbdp.viewDocument(otherUsagesFile2);
		TestValidation.IsTrue(openDocument, 
							  "OPENED document ->   " + otherUsagesFile2,
							  "Could NOT OPEN document ->   " + otherUsagesFile2);

		boolean print = fbdp.handlePrint();
		TestValidation.IsTrue(print, 
							  "VERIFIED print functionality successfully for document  ->   " + otherUsagesFile2,
							  "Could NOT VERIFY print functionality successfully for documentt ->   " + otherUsagesFile2);
	}
	
	@Test(description = "FSQA-Documents-Document Viewer|| Verify the History tab which opens on clicking i icon" )
	public void TestCase_38732() throws Exception{
	
		String attributeValue = "doc-history-tab";
		
		browserPage = hp.clickFSQABrowserMenu();
		
		boolean selectResourceDropDownandNavigate = browserPage.selectResourceDropDownandNavigate(resourceType,fsqaTab);
		TestValidation.IsTrue(selectResourceDropDownandNavigate, 
							  "Navigated to FSQA ->   " + fsqaTab ,
							  "Could NOT Navigate to FSQA ->   " + fsqaTab);

		boolean selectResourceInstance = browserPage.searchSelect(resourceCategoryInstanceValue);
		TestValidation.IsTrue(selectResourceInstance, 
							  "Searched and selected instance ->   " + resourceCategoryInstanceValue,
							  "Could NOT search and select instance ->   " + resourceCategoryInstanceValue);
	
		boolean openDocument = fbdp.viewDocument(otherUsagesFile2);
		TestValidation.IsTrue(openDocument, 
							  "OPENED document viewer for ->   " + otherUsagesFile2,
							  "Could NOT OPEN document viewer for->   " + otherUsagesFile2);

		boolean openedDetailsSection = fbdp.openDocumentDetails();
		TestValidation.IsTrue(openedDetailsSection,
							  "OPENED Document Details Section for document ->   " + otherUsagesFile2,
							  "Could NOT OPEN Document Details Section for Document ->   " + otherUsagesFile2);

		boolean historyTab = fbdp.clickTabInDocumentDetailsSection(TABS_IN_DOC_DETAILS_SCECTION.HISTORY);
		TestValidation.IsTrue(historyTab,
							 "OPENED History Tab for Document ->   " + otherUsagesFile2,
							 "Could NOT OPEN History Tab for Document ->   " + otherUsagesFile2);

		boolean historyTabVerify = fbdp.verifyTab(attribute,attributeValue);
		TestValidation.IsTrue(historyTabVerify, 
							 "VERIFIED History Tab for Document ->   " + otherUsagesFile2,
							 "Could NOT VERIFY History Tab for Document ->   " + otherUsagesFile2);

		boolean closeDocumentDetails = fbdp.closeDocumentDetails();
		TestValidation.IsTrue(closeDocumentDetails, 
							  "CLOSED Document Details Section ",
							  "COULD NOT CLOSE Document Details Section");
		
	}
	
	@Test(description = "FSQA-Documents-Document Viewer|| Verify the Details tab which opens on clicking i icon" )
	public void TestCase_38733() throws Exception{
		
		String attributeValue = "doc-detail-tab";
		
		browserPage = hp.clickFSQABrowserMenu();
		
		boolean selectResourceDropDownandNavigate = browserPage.selectResourceDropDownandNavigate(resourceType,fsqaTab);
		TestValidation.IsTrue(selectResourceDropDownandNavigate, 
							  "Navigated to FSQA ->   " + fsqaTab ,
							  "Could NOT Navigate to FSQA ->   " + fsqaTab);

		boolean selectResourceInstance = browserPage.searchSelect(resourceCategoryInstanceValue);
		TestValidation.IsTrue(selectResourceInstance,
							  "Searched and selected instance ->   " + resourceCategoryInstanceValue,
							  "Could NOT search and select instance ->   " + resourceCategoryInstanceValue);
		
		boolean openDocument = fbdp.viewDocument(otherUsagesFile2);
		TestValidation.IsTrue(openDocument,
							  "OPENED document viewer ->   " + otherUsagesFile2,
							  "Could NOT OPEN document viewer ->   " + otherUsagesFile2);
		
		boolean openedDetailsSection = fbdp.openDocumentDetails();
		TestValidation.IsTrue(openedDetailsSection, 
							  "OPENED Document Details Section ->   " + otherUsagesFile2,
							  "Could NOT OPEN Document Details Section for Document ->   " + otherUsagesFile2);
		
		boolean detailsTab = fbdp.clickTabInDocumentDetailsSection(TABS_IN_DOC_DETAILS_SCECTION.DETAILS);
		TestValidation.IsTrue(detailsTab, 
							  "OPENED Details Tab for Document ->   " + otherUsagesFile2,
							  "Could NOT OPEN  Details Tab for Document ->   "  + otherUsagesFile2 );
		
		boolean detailsTabVerify = fbdp.verifyTab(attribute,attributeValue);
		TestValidation.IsTrue(detailsTabVerify, 
							  "VERIFIED Details Tab for Document ->   " + otherUsagesFile2,
							  "Could NOT VERIFY Details Tab for Document ->   " + otherUsagesFile2);
		
		boolean closeDocumentDetails = fbdp.closeDocumentDetails();
		TestValidation.IsTrue(closeDocumentDetails, 
							  "CLOSED Document Details Section ",
							  "COULD NOT CLOSE Document Details Section");
		
	}	

	@Test(description = " FSQA-Documents|| Verify the user can upload the the documents with category and subcategory of resources")
	public void TestCase_38734() throws Exception {

		browserPage = hp.clickFSQABrowserMenu();
		
		boolean selectResourceDropDownandNavigate = browserPage.selectResourceDropDownandNavigate(resourceType,fsqaTab);
		TestValidation.IsTrue(selectResourceDropDownandNavigate, 
							  "Navigated to FSQA ->   " + fsqaTab ,
							  "Could NOT Navigate to FSQA ->   " + fsqaTab);

		boolean selectResourceInstance = browserPage.searchSelect(resourceSubCategoryInstanceValue);
		TestValidation.IsTrue(selectResourceInstance,
							  "Searched and selected instance ->   " + resourceSubCategoryInstanceValue,
							  "Could NOT search and select instance ->   " + resourceSubCategoryInstanceValue);

		boolean uploadDocument = fbdp.uploadDocument(subCategoryUploadFilePath);
		TestValidation.IsTrue(uploadDocument, 
							  "Uploaded Document to subcategory successfully -   " + subCategoryUploadFile,
							  "Could NOT Upload Document -   " + subCategoryUploadFile);

		boolean verifyDocument = fbdp.verifyDocUpload(subCategoryUploadFile);
		TestValidation.IsTrue(verifyDocument, 
							  "Verified Uploaded Document successfully -   " + subCategoryUploadFile,
							  "Could NOT Verify Uploaded Document -   " + subCategoryUploadFile);

	}
	
	
	@AfterClass(alwaysRun = true)
	public void closeBrowser() throws InterruptedException {
		driver.close();
	}

}
