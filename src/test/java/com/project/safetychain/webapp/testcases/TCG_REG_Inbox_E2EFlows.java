package com.project.safetychain.webapp.testcases;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.project.safetychain.api.models.support.TCG_FormCreation_Wrapper;
import com.project.safetychain.api.models.support.Support.RESOURCE_TYPES;
import com.project.safetychain.webapp.pageobjects.CommonPage;
import com.project.safetychain.webapp.pageobjects.DocumentManagementPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserFormsPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage;
import com.project.safetychain.webapp.pageobjects.HomePage;
import com.project.safetychain.webapp.pageobjects.InboxPage;
import com.project.safetychain.webapp.pageobjects.LoginPage;
import com.project.safetychain.webapp.pageobjects.ManageLocationPage;
import com.project.safetychain.webapp.pageobjects.ManageRequirementPage;
import com.project.safetychain.webapp.pageobjects.ManageResourcePage;
import com.project.safetychain.webapp.pageobjects.ResourceDesignerPage;
import com.project.safetychain.webapp.pageobjects.SupplierPortalPage;
import com.project.safetychain.webapp.pageobjects.UserManagerPage;
import com.project.safetychain.webapp.pageobjects.WorkGroupsPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.COLUMNHEADER;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.COLUMNSETTING;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.TASKDETAILS;
import com.project.safetychain.webapp.pageobjects.FSQABrowserTaskPage;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FORMTYPES;
import com.project.safetychain.webapp.pageobjects.ManageResourcePage.RESOURCETYPES;
import com.project.safetychain.webapp.pageobjects.SupplierPortalPage.SupplierPortalDetails;
import com.project.safetychain.webapp.pageobjects.UserManagerPage.UsrDetailParams;
import com.project.safetychain.webapp.pageobjects.TaskSchedulerPage;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.utilities.CommonMethods;
import com.project.utilities.ControlActions;
import com.project.utilities.DateTime;

public class TCG_REG_Inbox_E2EFlows extends TestBase{
	ControlActions controlActions;
	CommonPage common;	
	LoginPage login;
	HomePage home;
	DateTime dateTime;	
	FormDesignerPage fdp;
	FSQABrowserPage fbp;
	WorkGroupsPage wgp;
	ResourceDesignerPage rdp;
	ManageLocationPage locations;
	ManageResourcePage manageResource;
	FSQABrowserFormsPage fsqaFormsPage;
	FSQABrowserTaskPage fsqaTasksPage;
	DateTime datetime;
	UserManagerPage manageUser;
	TaskSchedulerPage tsp;
	ManageRequirementPage mrp;
	SupplierPortalPage supplierPage;
	SupplierPortalDetails supplierDetails;
	InboxPage ip;

	public static String locationCategoryValue;
	public static String locationInstanceValue;
	public static String suppliersCategoryValue;
	public static String suppliersInstanceValue;
	public static String itemsCategoryValue;
	public static String itemsInstanceValue;
	public static String formname ;
	public static String wgName;
	public static String wgName1;
	public static String qformName;	
	public static String numericFN = "Numeric";	
	public static String cctask,locTask,locTask1 ;
	public static String displayName = null;
	public static String timezoneCode = null;
	public static String usrTimezone = null;

	public static String roleName;
	public static String userUN;
	public static String userFN;
	public static String userLN;
	public static List<String> supplierinstance;
	public static List<String> iteminstance;
	public static List<String> userLocation;
	public static List<String> userRole , workgroup;
	public static String supplierUserName, supplierPassword, supplierNewPassword;
	//document upload// 
	public static String image;
	public static String document;
	UsrDetailParams udp;
	public static String filtertask;

	//requirement type creation//
	public static String suppdocreqname,suppdocreqname1,suppformreqname,suppformreqname1;
	public static String itemdocreqname,itemdocreqname1,itemformreqname,itemformreqname1;
	public static String supptempname,itemtempname,ackreqname;

	//Internal user portal
	public static String docTypeName, qstFormName;
	public static String docReqName1;
	public static String formReqName1;
	public static String documentName = "upload.png";
	public static String documentPath = System.getProperty("user.dir") + "\\test-data-files\\UploadDocuments\\"+documentName;

	//SUpplier Portal
	public static String uploadDocumentName = "upload.png";
	public static String uploadDocumentPath = System.getProperty("user.dir") + "\\test-data-files\\UploadDocuments\\"+uploadDocumentName;


	@BeforeClass(alwaysRun = true)
	public void groupInit() throws InterruptedException {

		locationCategoryValue ="LC_31.12_16.08.01";//CommonMethods.dynamicText("LC"); 
		locationInstanceValue = "LI_31.12_16.08.01";//CommonMethods.dynamicText("LI"); 
		suppliersCategoryValue ="SC_31.12_16.08.01";//CommonMethods.dynamicText("SC");
		suppliersInstanceValue ="SI_31.12_16.08.01";// CommonMethods.dynamicText("SI");
		itemsCategoryValue ="IC_31.12_16.08.01";//CommonMethods.dynamicText("IC");
		itemsInstanceValue = "II_31.12_16.08.01";// CommonMethods.dynamicText("II");
		wgName = "WG_31.12_16.08.01";//CommonMethods.dynamicText("WG"); 
		wgName1 ="WG1_31.12_16.08.01";// CommonMethods.dynamicText("WG1");
		formname ="Quest_31.12_16.08.01";//CommonMethods.dynamicText("Quest");
		cctask =  CommonMethods.dynamicText("CCTask");

		filtertask = CommonMethods.dynamicText("FilterTask");
		suppdocreqname ="SuppDoc_31.12_16.08.01";//CommonMethods.dynamicText("SuppDoc");
		suppdocreqname1 ="SuppDoc1_31.12_16.08.01";//CommonMethods.dynamicText("SuppDoc1");
		suppformreqname ="SuppForm_31.12_16.08.01";//CommonMethods.dynamicText("SuppForm");
		suppformreqname1 ="SuppForm1_31.12_16.08.01";//CommonMethods.dynamicText("SuppForm1");
		itemdocreqname ="ItemDoc_31.12_16.08.01";//CommonMethods.dynamicText("ItemDoc");
		itemdocreqname1 = "ItemDoc1_31.12_16.08.01";//CommonMethods.dynamicText("ItemDoc1");
		itemformreqname = "ItemForm_31.12_16.08.01";//CommonMethods.dynamicText("ItemForm");
		itemformreqname1 ="ItemForm1_31.12_16.08.01";//CommonMethods.dynamicText("ItemForm1"); 
		supptempname ="SupplierTemp_31.12_16.08.01";//CommonMethods.dynamicText("SupplierTemp");
		itemtempname ="ItemTemp_31.12_16.08.01";//CommonMethods.dynamicText("ItemTemp");
		docTypeName ="Supplier_DocType_31.12_16.08.01";//CommonMethods.dynamicText("Supplier_DocType");
		image = "upload.png";
		document = System.getProperty("user.dir") + "\\test-data-files\\UploadDocuments\\"+image;

		supplierinstance = new ArrayList<String>();
		supplierinstance.add(suppliersInstanceValue);
		
		iteminstance = new ArrayList<String>();
		iteminstance.add(itemsInstanceValue);

//		// ------------------------------------------------------------------------------------------------
//		// API Implementation
//		TCG_FormCreation_Wrapper formCreationWrapper = new TCG_FormCreation_Wrapper();
//		//ApiUtils apiUtils = new ApiUtils();
//
//		// ------------------------------------------------------------------------------------------------
//		// API - Location & Resource Creation and Linking
//		List<String> userList = Arrays.asList(adminUsername);
//
//		HashMap<String, List<String>> LocationMap = new HashMap<String, List<String>>();
//		LocationMap.put(locationCategoryValue, Arrays.asList(locationInstanceValue));
//
//		HashMap<String, List<String>> resourceCatMap = new HashMap<String, List<String>>();
//		resourceCatMap.put(suppliersCategoryValue, Arrays.asList(suppliersInstanceValue));
//
//		HashMap<String, List<String>> resourceCatMap1 = new HashMap<String, List<String>>();
//		resourceCatMap1.put(itemsCategoryValue, Arrays.asList(itemsInstanceValue));
//
//		formCreationWrapper.Resource_Location_CreationWrapper(resourceCatMap, LocationMap, true, userList,
//				RESOURCE_TYPES.SUPPLIERS);
//		
//		formCreationWrapper.Resource_Location_CreationWrapper(resourceCatMap1, LocationMap, true, userList,
//				RESOURCE_TYPES.ITEMS);
//		// ------------------------------------------------------------------------------------------------
//		// WEB Application code starts here
//
//		// ------------------------------------------------------------------------------------------------
//
//

		driver = launchbrowser();
		controlActions = new ControlActions(driver);
		controlActions.getUrl(applicationUrl);
		home = new HomePage(driver);
		login = new LoginPage(driver);
		dateTime = new DateTime(driver);
		fdp = new FormDesignerPage(driver);
		wgp = new WorkGroupsPage(driver);
		fbp = new FSQABrowserPage(driver);
		rdp = new ResourceDesignerPage(driver);
		locations = new ManageLocationPage(driver);
		manageResource = new ManageResourcePage(driver);		
		fsqaFormsPage = new FSQABrowserFormsPage(driver);
		fsqaTasksPage = new FSQABrowserTaskPage(driver);
		tsp = new TaskSchedulerPage(driver);
		supplierPage = new SupplierPortalPage(driver);
		mrp = new ManageRequirementPage(driver);
		manageUser = new UserManagerPage(driver);
		fdp = new FormDesignerPage(driver);

		login.performLogin(adminUsername, adminPassword);
		if(home.error) {
			TCGFailureMsg = "Could NOT login to application";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		displayName = home.getLoggedInUserDetails();
		if(displayName == ""){
			TCGFailureMsg = "Could NOT get display name for user - " + adminUsername;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		
//				//FORM - Creation and Release
//		
//				if(!fdp.createAndReleaseForm(FORMTYPES.QUESTIONNAIRE, formname,"Suppliers", suppliersCategoryValue,
//						suppliersInstanceValue)) {
//					TCGFailureMsg = "Could not create and release form - " + formname;
//					logFatal(TCGFailureMsg);
//					throw new SkipException(TCGFailureMsg);
//				}
//		
//				if(!wgp.createWorkGroup(wgName, adminUsername)) {
//					TCGFailureMsg = "Could not create workgroup - " + wgName;
//					logFatal(TCGFailureMsg);
//					throw new SkipException(TCGFailureMsg);
//				}
//				
//				if(!wgp.createWorkGroup(wgName1, adminUsername)) {
//					TCGFailureMsg = "Could not create workgroup - " + wgName1;
//					logFatal(TCGFailureMsg);
//					throw new SkipException(TCGFailureMsg);
//				}
//		
//				DocumentManagementPage dmp = home.clickdocumentsmenu();
//				if(!dmp.docUploadinDocType (docTypeName,document)) {
//					TCGFailureMsg = "Could not upload document in doctype- " + docTypeName;
//					logFatal(TCGFailureMsg);
//					throw new SkipException(TCGFailureMsg);
//				}
//		
//				// Create requirement for Supplier instance
//				ManageRequirementPage mrp = home.clickRequirementsMenu();		
//				if(!mrp.createSuppReq(supptempname, docTypeName, wgName, suppdocreqname, suppdocreqname1, formname, 
//						suppformreqname, suppformreqname1, suppliersInstanceValue)) {
//					TCGFailureMsg = "Could not create requirement for supplier " + suppliersInstanceValue;
//					logFatal(TCGFailureMsg);
//					throw new SkipException(TCGFailureMsg);
//		
//				}
//				
//				if(!mrp.createitemreq(itemtempname, docTypeName, wgName1, itemdocreqname, itemdocreqname1, formname, 
//						itemformreqname, itemformreqname1, itemsInstanceValue)) {
//					TCGFailureMsg = "Could not create requirement for item " + itemsInstanceValue;
//					logFatal(TCGFailureMsg);
//					throw new SkipException(TCGFailureMsg);
//		
//				}
				//supptempname = "SupplierTemp_pr0123";
		
				roleName = "superadmin";
				userFN = "FN_31.12_16.33.59";//CommonMethods.dynamicText("FN");
				userLN = "LN_31.12_16.33.59";//CommonMethods.dynamicText("LN");
				userLocation = new ArrayList<String>();
				supplierUserName ="SupplierUser_31.12_16.33.59";// CommonMethods.dynamicText("SupplierUser");
				supplierPassword = GenericPassword;
				supplierNewPassword = GenericNewPassword;
				userLocation.add(locationInstanceValue);
		
				udp = new UsrDetailParams();
		
				udp.Suppliers=supplierinstance;
				udp.Username = supplierUserName;
				udp.Password = supplierPassword;
				udp.FirstName = userFN;
				udp.LastName = userLN;
				udp.Email = "task@test.com";
				udp.Timezone = "Republic of India";
				udp.EmployeeId = "X12345";
				udp.Title = "Supplier Title";
		
//				if(!manageUser.supplierUserCreation(udp)) {
//					TCGFailureMsg = "Could NOT create supplier user";
//					logFatal(TCGFailureMsg);
//					throw new SkipException(TCGFailureMsg);
//				}
		
//				if(!home.userLogout()){
//					TCGFailureMsg = "Could NOT logout from application";
//					logFatal(TCGFailureMsg);
//					throw new SkipException(TCGFailureMsg);
//				}
//		
//						if(!(login.performLogin(supplierUserName, supplierPassword, supplierNewPassword))) {
//							TCGFailureMsg = "Could NOT login to application";
//							logFatal(TCGFailureMsg);
//							throw new SkipException(TCGFailureMsg);
//						}
		
//						if(!(supplierPage.verifySearchedTask(suppformreqname))){
//							TCGFailureMsg = "Failed, Task not displayed in Inbox";
//							logFatal(TCGFailureMsg);
//							throw new SkipException(TCGFailureMsg);
//						}
//						
//						if(!(supplierPage.openCompleteForm(formname , suppformreqname, suppliersInstanceValue)))
//						{
//							TCGFailureMsg = "Could NOT open 'Suppliers' complete form task";
//							logFatal(TCGFailureMsg);
//							throw new SkipException(TCGFailureMsg);
//						}
//							
//						if(!(fsqaFormsPage.submitData(false, false, false, true,false)))
//						{
//							TCGFailureMsg = "Could NOT submit task";
//							logFatal(TCGFailureMsg);
//							throw new SkipException(TCGFailureMsg);
//						}
//		
//						if(!(supplierPage.verifySearchedTask(suppdocreqname)))
//						{
//							TCGFailureMsg = "Failed, Task not displayed in Inbox";
//							logFatal(TCGFailureMsg);
//							throw new SkipException(TCGFailureMsg);
//						}
//		
//						if(!(supplierPage.openDocumentUpload(docTypeName,suppdocreqname, suppliersInstanceValue)))
//						{
//							TCGFailureMsg = "Could NOT open 'Suppliers' document upload task";
//							logFatal(TCGFailureMsg);
//							throw new SkipException(TCGFailureMsg);
//						}
//		
//						if(!(supplierPage.uploadDocument(uploadDocumentPath, true)))
//						{
//							TCGFailureMsg = "Could NOT submit task";
//							logFatal(TCGFailureMsg);
//							throw new SkipException(TCGFailureMsg);
//						}
//						
//						if(!(supplierPage.verifySearchedTask(suppdocreqname1)))
//						{
//							TCGFailureMsg = "Failed, Task not displayed in Inbox";
//							logFatal(TCGFailureMsg);
//							throw new SkipException(TCGFailureMsg);
//						}
//						if(!(supplierPage.openDocumentUpload(docTypeName,suppdocreqname1, suppliersInstanceValue)))
//						{
//							TCGFailureMsg = "Could NOT open 'Suppliers' document upload task";
//							logFatal(TCGFailureMsg);
//							throw new SkipException(TCGFailureMsg);
//						}
//		
//						if(!(supplierPage.uploadDocument(uploadDocumentPath, true)))
//						{
//							TCGFailureMsg = "Could NOT submit task";
//							logFatal(TCGFailureMsg);
//							throw new SkipException(TCGFailureMsg);
//						}
						
//						if(!home.userLogout()){
//							TCGFailureMsg = "Could NOT logout from application";
//							logFatal(TCGFailureMsg);
//							throw new SkipException(TCGFailureMsg);
//						}
//						
//						login.performLogin(adminUsername, adminPassword);
//						if(home.error) {
//							TCGFailureMsg = "Could NOT login to application";
//							logFatal(TCGFailureMsg);
//							throw new SkipException(TCGFailureMsg);
//						}

	}

	@Test(description = "Inbox || Verify that after searching & opening the task, search term persist")
	public void TestCase_26764() throws InterruptedException {

		try {
			//navigate to inbox
			//itemdocreqname1="itemDoc1_10.11_18.25.04";
			ip=home.clickInboxMenu();
			boolean verifyInboxPage = home.verifyInboxPage();
			TestValidation.IsTrue(verifyInboxPage, 
					"Succesfully navigated to Inbox Page", 
					"Failed , navigate to Inbox Page  ");

			// search the taskname in inbox
			boolean searchTask =  ip.searchTaskName(suppdocreqname);
			TestValidation.IsTrue(searchTask, 
					"Task " + suppdocreqname + " is found", 
					"Failed to find task " + suppdocreqname);
			
			
			//open task and close task
			boolean openAndCloseTask =  ip.openCloseTask(suppdocreqname);
			TestValidation.IsTrue(openAndCloseTask, 
					"Task " + suppdocreqname + " is succesfully open and close", 
					"Failed to open and close task  " + suppdocreqname);

		}
		finally {
			controlActions.refreshPage();
			logInfo("Task performed");
		}

	}

	@Test(description = "Inbox || Verify the 'Task Type' filter")
	public void TestCase_34096() throws InterruptedException {

		try{
			//navigate to inbox
			ip=home.clickInboxMenu();
			String taskName1=suppdocreqname1;
			String taskName2=suppformreqname;
			String taskName3=suppdocreqname;

			boolean verifyInboxPage = home.verifyInboxPage();
			TestValidation.IsTrue(verifyInboxPage, 
					"Succesfully navigated to Inbox Page", 
					"Failed , navigate to Inbox Page  ");

			//verify all 3 task type filters are available
			boolean verifyTaskTypeFiltersDisplayed = ip.verifyTaskTypeFilters();
			TestValidation.IsTrue(verifyTaskTypeFiltersDisplayed, 
					"All 3 task typefilters are displayed", 
					"Failed , not all 3 task type filters are displayed ");

			// click on checkbox one by one
			boolean verifyApprovalCheckbox  = ip.checkTaskTypeFiltercheckbox("Approval");
			TestValidation.IsTrue(verifyApprovalCheckbox, 
					"Approval checkbox is checked", 
					"Failed , to check checkbox of Approval ");

			boolean searchApproval  = ip.searchTaskName(taskName1);
			TestValidation.IsTrue(searchApproval, 
					taskName1+ " is typed in search box", 
					"Failed , to type "+ taskName1 +"in search box ");
			
			boolean taskVisible  = ip.taskVisible(taskName1);
			TestValidation.IsTrue(taskVisible, 
					taskName1+" is  visible in Inbox Page", 
					"Failed ,"+ taskName1+ " is not visible in Inbox Page ");

			//verify approval task visible

			boolean verifyCompleteFormCheckbox = ip.checkTaskTypeFiltercheckbox("Complete Form");
			TestValidation.IsTrue(verifyCompleteFormCheckbox, 
					"Complete Form checkbox is checked", 
					"Failed , to check checkbox of Complete Form ");

			boolean searchCompleteForm  = ip.searchTaskName(taskName2);
			TestValidation.IsTrue(searchCompleteForm, 
					taskName2+ " is typed in search box", 
					"Failed , to type "+ taskName2 +"in search box ");
			
			 taskVisible  = ip.taskVisible(taskName2);
			TestValidation.IsTrue(taskVisible, 
					taskName2+" is  visible in Inbox Page", 
					"Failed ,"+ taskName2 + " is not visible in Inbox Page ");

			// verify completeform visible
			boolean verifyDocumentUploadCheckbox = ip.checkTaskTypeFiltercheckbox("Document Upload");
			TestValidation.IsTrue(verifyDocumentUploadCheckbox, 
					"Document Upload checkbox is checked", 
					"Failed , to check checkbox of Document Upload ");

			//verify document upload is visible
			boolean searchDocumentUpload  = ip.searchTaskName(taskName3);
			TestValidation.IsTrue(searchDocumentUpload, 
					taskName3+ "is typed in search box", 
					"Failed , to type "+taskName3 +" in search box ");
			

			 taskVisible  = ip.taskVisible(taskName3);
			TestValidation.IsTrue(taskVisible, 
					taskName3+" is  visible in Inbox Page", 
					"Failed ,"+ taskName3 + " is not visible in Inbox Page ");


		}
		finally {

			boolean clickClearbtn = ip.clickClearBtn();
			TestValidation.IsTrue(clickClearbtn, 
					"Succesfully clicked  clear btn and verified that all 3 task filters are unchecked", 
					"Failed , click clear btn and verify whether all 3 task filter checkboxes are unchecked or not ");			
		}

	}

	@Test(description = "Inbox || Verify refresh button is working properly.")
	public void TestCase_34094() throws InterruptedException {

		//navigate to inbox
		ip=home.clickInboxMenu();

		boolean verifyInboxPage = home.verifyInboxPage();
		TestValidation.IsTrue(verifyInboxPage, 
				"Succesfully navigated to Inbox Page", 
				"Failed , navigate to Inbox Page  ");

		boolean verifyRefreshBtnAction = ip.refreshInboxPage();
		TestValidation.IsTrue(verifyRefreshBtnAction, 
				"Succesfully Refreshed Inbox Page", 
				"Failed , to refresh to Inbox Page");


	}

	@Test(description = "Inbox || Verify if logged in user work-group has no tasks, then no work-group will be visible in Inbox.")
	public void TestCase_38259() throws InterruptedException {

		try{
			//wgName1="WG1_16.11_10.03.24";
			//create user -> assign new wg -> verify wgfilter
			//navigate to inbox
			ip=home.clickInboxMenu();

			boolean verifyInboxPage = home.verifyInboxPage();
			TestValidation.IsTrue(verifyInboxPage, 
					"Succesfully navigated to Inbox Page", 
					"Failed , navigate to Inbox Page  ");

			//verify all 3 task type filters are available
			boolean verifyTaskTypeFiltersDisplayed = ip.workGroupFilterCheckbox(wgName1);
			TestValidation.IsFalse(verifyTaskTypeFiltersDisplayed, 
					"No Task displayed for given workgroup", 
					"Failed , task displayed for given workgroup ");

		}
		finally {

			boolean clickClearbtn = ip.clickClearBtn();
			TestValidation.IsTrue(clickClearbtn, 
					"Succesfully clicked  clear btn and verified that all 3 task filters are unchecked", 
					"Failed , click clear btn and verify whether all 3 task filter checkboxes are unchecked or not ");			
		}

	}

	//34095
	@Test(description = "Inbox || Verify the 'Assigned To' filter.")
	public void TestCase_34097() throws InterruptedException {

		try{
//			wgName="Auto_WG_17.11_18.05.13";
	//		suppdocreqname="Yearly__17.11_18.28.36";
			//create user -> assign new wg -> verify wgfilter
			//navigate to inbox
			ip=home.clickInboxMenu();

			boolean verifyInboxPage = home.verifyInboxPage();
			TestValidation.IsTrue(verifyInboxPage, 
					"Succesfully navigated to Inbox Page", 
					"Failed , navigate to Inbox Page  ");

			//verify all 3 task type filters are available
			boolean verifyTaskTypeFiltersDisplayed = ip.workGroupFilterCheckbox(wgName);
			TestValidation.IsTrue(verifyTaskTypeFiltersDisplayed, 
					"Selected workgroup for given Assigned to filter", 
					"Failed, to select workgroup for given Assigned to filter ");

			boolean searchCompleteForm  = ip.searchTaskName(suppdocreqname);
			TestValidation.IsTrue(searchCompleteForm, 
					"Succesfully typed in search box", 
					"Failed , to type in searchbox ");
			

			boolean taskVisible  = ip.taskVisible(suppdocreqname);
			TestValidation.IsTrue(taskVisible, 
					suppdocreqname+" is  visible in Inbox Page", 
					"Failed ,"+ suppdocreqname + " is not visible in Inbox Page ");

		}
		finally {

			boolean clickClearbtn = ip.clickClearBtn();
			TestValidation.IsTrue(clickClearbtn, 
					"Succesfully clicked  clear btn and verified that AssignedTo filter checkbox are unchecked", 
					"Failed , click clear btn and verify whether all Assigned To filter are unchecked or not ");			
		}

	}

	@Test(description = "Inbox || Verify approval of form task.")
	public void TestCase_34929() throws InterruptedException {

		try {
			//navigate to inbox
			//suppformreqname="SuppForm_18.11_21.27.15";
			ip=home.clickInboxMenu();
			//String count="588";
			boolean verifyInboxPage = home.verifyInboxPage();
			TestValidation.IsTrue(verifyInboxPage, 
					"Succesfully navigated to Inbox Page", 
					"Failed , navigate to Inbox Page  ");
			
			String count=(ip.TaskTotal).getText();
			
			//open task and close task
			boolean approveTask1 =  ip.approveTask(suppformreqname);
			TestValidation.IsTrue(approveTask1, 
					"Task " + suppformreqname + " is succesfully Approved", 
					"Failed to open and close task  " + suppformreqname);

			//observe the search taskname
			boolean searchCompleteForm  = ip.searchTaskName(suppformreqname);
			TestValidation.IsTrue(searchCompleteForm, 
					suppdocreqname+" is typesd in search box", 
					"Failed ,"+ suppdocreqname + " can't type in search box ");

			boolean taskVisible  = ip.taskVisible(suppformreqname);
			TestValidation.IsFalse(taskVisible, 
					suppdocreqname+" is not visible in Inbox Page", 
					"Failed ,"+ suppdocreqname + " is visible in Inbox Page ");
			//check count 
			//
			boolean verifyTaskTotal = ip.verifyTaskTotal(searchCompleteForm, count );
			TestValidation.IsTrue(verifyTaskTotal, 
					"Task total count is reduced by 1, hence verified", 
					"Failed, to verify task count");

		}
		finally {
			controlActions.refreshPage();
			logInfo("Task performed");
		}

	}

	@Test(description = "Inbox || Verify the pagination of the Inbox. ")
	public void TestCase_34095() throws InterruptedException {

		try {
			String pagenumber="200";
			//navigate to inbox
			ip=home.clickInboxMenu();
			boolean verifyInboxPage = home.verifyInboxPage();
			TestValidation.IsTrue(verifyInboxPage, 
					"Succesfully navigated to Inbox Page", 
					"Failed , navigate to Inbox Page  ");

			// pagination drpdown
			boolean paginationDrpdwn =  ip.paginationDropdown(pagenumber);
			TestValidation.IsTrue(paginationDrpdwn, 
					"Selected given pagination ",
					"Failed to select given pagination " + suppformreqname);
			
			// check diable
			boolean clickPrevBtn =  ip.paginationBtn("Prev");
			TestValidation.IsFalse(clickPrevBtn, 
					"Disable clicked Prev Btn ",
					"Failed, Prev Btn is enabled ");
			

			boolean clickFirstPrevBtn =  ip.paginationBtn("First");
			TestValidation.IsTrue(clickFirstPrevBtn, 
					"Disable  First Prev Btn ",
					"Failed, enabled First Prev Btn ");	
			
			//next btn
			boolean clickNextBtn =  ip.paginationBtn("Next");
			TestValidation.IsTrue(clickNextBtn, 
					"Succesfully clicked Next Btn ",
					"Failed to clickNext Btn ");

			//prev btn
			 clickPrevBtn =  ip.paginationBtn("Prev");
			TestValidation.IsTrue(clickPrevBtn, 
					"Succesfully clicked Prev Btn ",
					"Failed to click Prev Btn ");

			// pagination drpdown
			pagenumber="500";
			
			paginationDrpdwn =  ip.paginationDropdown(pagenumber);
			TestValidation.IsTrue(paginationDrpdwn, 
					"Selected given pagination ",
					"Failed to select given pagination " + suppformreqname);
			
//			boolean clickLastBtn =  ip.paginationBtn("Last");
//			TestValidation.IsTrue(clickLastBtn, 
//					"Succesfully clicked Last Btn ",
//					"Failed to click Last Btn ");
//			 clickLastBtn =  ip.paginationBtn("Last");
//				TestValidation.IsTrue(clickLastBtn, 
//						"Succesfully clicked Last Btn ",
//						"Failed to click Last Btn ");	
			

			//next btn
			 clickNextBtn =  ip.paginationBtn("Next");
			TestValidation.IsTrue(clickNextBtn, 
					"Succesfully clicked Next Btn ",
					"Failed to clickNext Btn ");
			//first page
			 clickFirstPrevBtn =  ip.paginationBtn("First");
			TestValidation.IsTrue(clickFirstPrevBtn, 
					"Succesfully clicked First Prev Btn ",
					"Failed to click First Prev Btn ");			

			
		}
		finally {
			controlActions.refreshPage();
			logInfo("Task performed");
		}

	}


	@Test(description = "Inbox || Verify the 'Status' filter.")
	public void TestCase_34098() throws InterruptedException {
		try{
			suppdocreqname="PD_SIR";
			//navigate to inbox
			ip=home.clickInboxMenu();

			boolean verifyInboxPage = home.verifyInboxPage();
			TestValidation.IsTrue(verifyInboxPage, 
					"Succesfully navigated to Inbox Page", 
					"Failed , navigate to Inbox Page  ");

			//verify PAST DUE FILTER
			boolean verifyStatusFiltersDisplayed = ip.statusFilterCheckbox("Past Due");
			TestValidation.IsTrue(verifyStatusFiltersDisplayed, 
					"Selected given status for given Status filter", 
					"Failed, to select status for given Status filter ");

			boolean searchTask  = ip.searchTaskName(suppdocreqname);
			TestValidation.IsTrue(searchTask, 
					"Succesfully typed in search box", 
					"Failed , to type in searchbox ");
			

			boolean taskVisible  = ip.taskVisible(suppdocreqname);
			TestValidation.IsTrue(taskVisible, 
					suppdocreqname+" is  visible in Inbox Page", 
					"Failed ,"+ suppdocreqname + " is not visible in Inbox Page ");


			//verify DUE TODAY FILTER
			 verifyStatusFiltersDisplayed = ip.statusFilterCheckbox("Due Today");
			TestValidation.IsTrue(verifyStatusFiltersDisplayed, 
					"Selected given status for given Status filter", 
					"Failed, to select status for given Status filter ");

			 searchTask  = ip.searchTaskName(suppdocreqname);
			TestValidation.IsTrue(searchTask, 
					"Succesfully typed in search box", 
					"Failed , to type in searchbox ");
			

			 taskVisible  = ip.taskVisible(suppdocreqname);
			TestValidation.IsTrue(taskVisible, 
					suppdocreqname+" is  visible in Inbox Page", 
					"Failed ,"+ suppdocreqname + " is not visible in Inbox Page ");


			//verify DUE LATER FILTER
			 verifyStatusFiltersDisplayed = ip.statusFilterCheckbox("Due Later");
			TestValidation.IsTrue(verifyStatusFiltersDisplayed, 
					"Selected given status for given Status filter", 
					"Failed, to select status for given Status filter ");

			searchTask  = ip.searchTaskName(suppdocreqname);
			TestValidation.IsTrue(searchTask, 
					"Succesfully typed in search box", 
					"Failed , to type in searchbox ");
			

			 taskVisible  = ip.taskVisible(suppdocreqname);
			TestValidation.IsTrue(taskVisible, 
					suppdocreqname+" is  visible in Inbox Page", 
					"Failed ,"+ suppdocreqname + " is not visible in Inbox Page ");

			//verify NO DUE DATE FILTER
			 verifyStatusFiltersDisplayed = ip.statusFilterCheckbox("No Due Date");
			TestValidation.IsTrue(verifyStatusFiltersDisplayed, 
					"Selected given status for given Status filter", 
					"Failed, to select status for given Status filter ");

			searchTask  = ip.searchTaskName(suppdocreqname);
			TestValidation.IsTrue(searchTask, 
					"Succesfully typed in search box", 
					"Failed , to type in searchbox ");
			

			 taskVisible  = ip.taskVisible(suppdocreqname);
			TestValidation.IsTrue(taskVisible, 
					suppdocreqname+" is  visible in Inbox Page", 
					"Failed ,"+ suppdocreqname + " is not visible in Inbox Page ");
		}
		finally {

			boolean clickClearbtn = ip.clickClearBtn();
			TestValidation.IsTrue(clickClearbtn, 
					"Succesfully clicked  clear btn and verified that AssignedTo filter checkbox are unchecked", 
					"Failed , click clear btn and verify whether all Assigned To filter are unchecked or not ");			
		}
	}
	
	@Test(description = "Inbox || Verify the 'Due By' filter.")
	public void TestCase_34099() throws InterruptedException {
		try{
			suppdocreqname="PD_SIR";
			String from="11/02/2021";
			String to="12/05/2021";
			//navigate to inbox
			ip=home.clickInboxMenu();

			boolean verifyInboxPage = home.verifyInboxPage();
			TestValidation.IsTrue(verifyInboxPage, 
					"Succesfully navigated to Inbox Page", 
					"Failed , navigate to Inbox Page  ");

			//verify PAST DUE FILTER
			boolean verifyStatusFiltersDisplayed = ip.ApplyDueByFilter(from,to);
			TestValidation.IsTrue(verifyStatusFiltersDisplayed, 
					"Selected given status for given Status filter", 
					"Failed, to select status for given Status filter ");

			boolean searchTask  = ip.searchTaskName(suppdocreqname);
			TestValidation.IsTrue(searchTask, 
					"Succesfully typed in search box", 
					"Failed , to type in searchbox ");
			

			boolean taskVisible  = ip.taskVisible(suppdocreqname);
			TestValidation.IsTrue(taskVisible, 
					suppdocreqname+" is  visible in Inbox Page", 
					"Failed ,"+ suppdocreqname + " is not visible in Inbox Page ");
		}
		finally {

			boolean clickClearbtn = ip.clickClearBtn();
			TestValidation.IsTrue(clickClearbtn, 
					"Succesfully clicked  clear btn and verified that AssignedTo filter checkbox are unchecked", 
					"Failed , click clear btn and verify whether all Assigned To filter are unchecked or not ");			
		}
	}
	
	@Test(description = "Inbox || Verify sorting of the columns Status,Task Type,Task Name,Resource,From and Due By.")
	public void TestCase_34100() throws InterruptedException {
		try{
			suppdocreqname="PD_SIR";
			String from="11/02/2021";
			String to="12/05/2021";
			String taskType="Approval";
			String  status="Past Due";
			//navigate to inbox
			ip=home.clickInboxMenu();

			boolean verifyInboxPage = home.verifyInboxPage();
			TestValidation.IsTrue(verifyInboxPage, 
					"Succesfully navigated to Inbox Page", 
					"Failed , navigate to Inbox Page  ");
			
			//verify TASK TYPE FILTER
	
		//verify all 3 task type filters are available
		boolean verifyTaskTypeFiltersDisplayed = ip.verifyTaskTypeFilters();
		TestValidation.IsTrue(verifyTaskTypeFiltersDisplayed, 
				"All 3 task typefilters are displayed", 
				"Failed , not all 3 task type filters are displayed ");

		// click on checkbox one by one
		boolean verifyApprovalCheckbox  = ip.checkTaskTypeFiltercheckbox(taskType);
		TestValidation.IsTrue(verifyApprovalCheckbox, 
				"Approval checkbox is checked", 
				"Failed , to check checkbox of Approval ");

			//verify ASSIGNED TO  FILTER
		
		//verify all 3 task type filters are available
		boolean verifyAssignedToFiltersDisplayed = ip.workGroupFilterCheckbox(wgName);
		TestValidation.IsTrue(verifyAssignedToFiltersDisplayed, 
				"Selected workgroup for given Assigned to filter", 
				"Failed, to select workgroup for given Assigned to filter ");

			
			//verify STATUS FILTER
		boolean verifyStatusFiltersDisplayed = ip.statusFilterCheckbox(status);
		TestValidation.IsTrue(verifyStatusFiltersDisplayed, 
				"Selected given status for given Status filter", 
				"Failed, to select status for given Status filter ");
			

			//verify  DUE BY FILTER
			boolean ApplyDueByFilter = ip.ApplyDueByFilter(from,to);
			TestValidation.IsTrue(ApplyDueByFilter, 
					"Selected given status for given Status filter", 
					"Failed, to select status for given Status filter ");

			boolean searchTask  = ip.searchTaskName(suppdocreqname);
			TestValidation.IsTrue(searchTask, 
					"Succesfully typed in search box", 
					"Failed , to type in searchbox ");
			

			boolean taskVisible  = ip.taskVisible(suppdocreqname);
			TestValidation.IsTrue(taskVisible, 
					suppdocreqname+" is  visible in Inbox Page", 
					"Failed ,"+ suppdocreqname + " is not visible in Inbox Page ");
		}
		finally {

			boolean clickClearbtn = ip.clickClearBtn();
			TestValidation.IsTrue(clickClearbtn, 
					"Succesfully clicked  clear btn and verified that AssignedTo filter checkbox are unchecked", 
					"Failed , click clear btn and verify whether all Assigned To filter are unchecked or not ");			
		}
	}
	
	@Test(description = "Inbox || Verify that the Universal search is working.")
	public void TestCase_26763() throws InterruptedException {

		try {
			//navigate to inbox
			//suppdocreqname="Testing One";
			ip=home.clickInboxMenu();
			boolean verifyInboxPage = home.verifyInboxPage();
			TestValidation.IsTrue(verifyInboxPage, 
					"Succesfully navigated to Inbox Page", 
					"Failed , navigate to Inbox Page  ");

			// search the taskname in inbox
			boolean searchTask =  ip.searchTaskName(suppdocreqname);
			TestValidation.IsTrue(searchTask, 
					"Task " + suppdocreqname + " is found", 
					"Failed to find task " + suppdocreqname);
			
			
			//search result
			boolean searchResult =  ip.searchResult(suppdocreqname);
			TestValidation.IsTrue(searchResult, 
					"Task " + suppdocreqname + " is succesfully searched", 
					"Failed to search task  " + suppdocreqname);

		}
		finally {
			controlActions.refreshPage();
			logInfo("Task performed");
		}

	}
	
	@Test(description = "Inbox || Verify approval of document task")
	public void TestCase_34930() throws InterruptedException {

		try {
			//navigate to inbox
			suppdocreqname1="SuppDoc1_23.11_19.01.18";
			ip=home.clickInboxMenu();
			//String count="588";
			boolean verifyInboxPage = home.verifyInboxPage();
			TestValidation.IsTrue(verifyInboxPage, 
					"Succesfully navigated to Inbox Page", 
					"Failed , navigate to Inbox Page  ");
			
			String count=(ip.TaskTotal).getText();
			
			//open task and close task
			boolean approveTask1 =  ip.approveTask(suppdocreqname1);
			TestValidation.IsTrue(approveTask1, 
					"Task " + suppdocreqname1 + " is succesfully Approved", 
					"Failed to open and close task  " + suppdocreqname1);

			//observe the search taskname
			boolean searchCompleteForm  = ip.searchTaskName(suppdocreqname1);
			TestValidation.IsTrue(searchCompleteForm, 
					suppdocreqname1+" is typesd in search box", 
					"Failed ,"+ suppdocreqname1 + " can't type in search box ");

			boolean taskVisible  = ip.taskVisible(suppdocreqname1);
			TestValidation.IsFalse(taskVisible, 
					suppdocreqname1+" is not visible in Inbox Page", 
					"Failed ,"+ suppdocreqname1 + " is visible in Inbox Page ");
			//check count 
			//
			boolean verifyTaskTotal = ip.verifyTaskTotal(searchCompleteForm, count );
			TestValidation.IsTrue(verifyTaskTotal, 
					"Task total count is reduced by 1, hence verified", 
					"Failed, to verify task count");

		}
		finally {
			controlActions.refreshPage();
			logInfo("Task performed");
		}

	}

	@Test(description = " Inbox || Verify approval tasks should not be recalled after deletion of requirement.")
	public void TestCase_30389() throws InterruptedException {

		try {
			//navigate to inbox
			//suppdocreqname1="SuppDoc1_23.11_19.01.18";
			ip=home.clickInboxMenu();
			//String count="588";
			boolean verifyInboxPage = home.verifyInboxPage();
			TestValidation.IsTrue(verifyInboxPage, 
					"Succesfully navigated to Inbox Page", 
					"Failed , navigate to Inbox Page  ");
			
			String count=(ip.TaskTotal).getText();
			
			//open task and close task
			boolean approveTask1 =  ip.approveTask(suppdocreqname1);
			TestValidation.IsTrue(approveTask1, 
					"Task " + suppdocreqname1 + " is succesfully Approved", 
					"Failed to open and close task  " + suppdocreqname1);

			//observe the search taskname
			boolean searchCompleteForm  = ip.searchTaskName(suppdocreqname1);
			TestValidation.IsTrue(searchCompleteForm, 
					suppdocreqname1+" is typesd in search box", 
					"Failed ,"+ suppdocreqname1 + " can't type in search box ");

			boolean taskVisible  = ip.taskVisible(suppdocreqname1);
			TestValidation.IsFalse(taskVisible, 
					suppdocreqname1+" is not visible in Inbox Page", 
					"Failed ,"+ suppdocreqname1 + " is visible in Inbox Page ");
			//check count 
			//
			boolean verifyTaskTotal = ip.verifyTaskTotal(searchCompleteForm, count );
			TestValidation.IsTrue(verifyTaskTotal, 
					"Task total count is reduced by 1, hence verified", 
					"Failed, to verify task count");
			
			
			
			// remove requirement 

			home.clickRequirementsMenu();

			boolean deleteSuppTemp = mrp.deleterequirement(supptempname, "SUPPLIERS", suppdocreqname);
			TestValidation.IsTrue(deleteSuppTemp, 
					"Supplier Requiement deleted", 
					"Failed to delete Supplier requirement");

			// check recall

			home.clickFSQABrowserMenu();

			boolean navigate = fbp.selectResourceDropDownandNavigate("Suppliers","Tasks");
			TestValidation.IsTrue(navigate, 
					"Navigated to FSQABrowser>Task Tab", 
					"Failed to navigate to FSQABrowser>Task Tab");

			boolean applyFilter = fbp.openAndApplySettingsForColumn(COLUMNHEADER.TASKNAME, COLUMNSETTING.FILTER,suppformreqname);
			TestValidation.IsTrue(applyFilter, 
					"Applied Filter on" + suppformreqname, 
					"Failed to apply filter on" + suppformreqname);

			boolean recallTaskVisible = fsqaTasksPage.verifyRecallTaskIsDisplayed(suppformreqname);
			TestValidation.IsFalse(recallTaskVisible, 
					"Recall Task is not displayed "+ suppformreqname, 
					"Failed, Recall Task is display "+ suppformreqname);

		}
		finally {
			controlActions.refreshPage();
			logInfo("Task performed");
		}

	}
	
	@Test(description = "Inbox || Verify approval tasks should not be recalled after disabling of template")
	public void TestCase_38418() throws InterruptedException {

		try {
			supplierDetails = new SupplierPortalDetails();
			supplierDetails.userName =supplierUserName;//"Suppl_pr12";
			supplierDetails.password = GenericNewPassword;
			//suppformreqname1="SuppForm1_12.10_09.58.14";
			supplierDetails.formTask_Suppliers = suppformreqname;
			supplierDetails.suppliersResource = suppliersInstanceValue;
			//supptempname ="SupplierTemp_pr00102";
			//		
			home = login.performLogin(adminUsername, adminPassword);
			TestValidation.IsTrue(!home.error, 
					"LOGGED IN with Admin user " + adminUsername, 
					"Failed to login with Admin user " + adminUsername);

			home.clickFSQABrowserMenu();

			home.clickRequirementsMenu();

			boolean disableTemp = mrp.enabledisabletemplate(supptempname,"SUPPLIERS",false);
			TestValidation.IsTrue(disableTemp, 
					"Supplier Template Disabled", 
					"Failed to disable supplier template");

			boolean verifyDisableTemp = mrp.verifydisabledtemplate(supptempname);
			TestValidation.IsTrue(verifyDisableTemp, 
					"Supplier Template Disabled verified", 
					"Failed to verify disable supplier template");

			home.clickFSQABrowserMenu();

			boolean navigate = fbp.selectResourceDropDownandNavigate("Suppliers","Tasks");
			TestValidation.IsTrue(navigate, 
					"Navigated to FSQABrowser>Task Tab", 
					"Failed to navigate to FSQABrowser>Task Tab");

			boolean applyFilter = fbp.openAndApplySettingsForColumn(COLUMNHEADER.TASKNAME, COLUMNSETTING.FILTER,suppformreqname);
			TestValidation.IsTrue(applyFilter, 
					"Applied Filter on" + suppformreqname, 
					"Failed to apply filter on" + suppformreqname);

			boolean recallTaskVisible = fsqaTasksPage.verifyRecallTaskIsDisplayed(suppformreqname);
			TestValidation.IsFalse(recallTaskVisible, 
					"Recall Task is not displayed "+ suppformreqname, 
					"Failed, Recall Task is display "+ suppformreqname);

		}
		finally {
			controlActions.refreshPage();
			logInfo("Task performed");
		}

	}
	
	@Test(description = "Inbox || Verify approval tasks should not be recalled after disabling supplier/item instance. ")
	public void TestCase_38420() throws InterruptedException {
		try{
			supplierDetails = new SupplierPortalDetails();
			supplierDetails.userName = supplierUserName;//"Suppl_pr12";
			supplierDetails.password = GenericNewPassword;
			//suppformreqname1="SuppForm1_12.10_09.58.14";
			supplierDetails.formTask_Suppliers = itemformreqname;
			supplierDetails.suppliersResource = itemsInstanceValue;
			//supptempname ="SupplierTemp_pr00102";

			home = login.performLogin(adminUsername, adminPassword);
			TestValidation.IsTrue(!home.error, 
					"LOGGED IN with Admin user " + adminUsername, 
					"Failed to login with Admin user " + adminUsername);

			// remove instances 

			manageResource=home.clickResourcesMenu();

			boolean disableInstance = manageResource.searchAndDisableResource(RESOURCETYPES.ITEMS, itemsInstanceValue);
			TestValidation.IsTrue(disableInstance, 
					"Supplier Instance" + itemsInstanceValue+" Disabled", 
					"Failed to disable supplier instance"+ itemsInstanceValue);


			// verify recall
			home.clickFSQABrowserMenu();

			boolean navigate = fbp.selectResourceDropDownandNavigate("Items","Tasks");
			TestValidation.IsTrue(navigate, 
					"Navigated to FSQABrowser>Task Tab", 
					"Failed to navigate to FSQABrowser>Task Tab");

			boolean applyFilter = fbp.openAndApplySettingsForColumn(COLUMNHEADER.TASKNAME, COLUMNSETTING.FILTER,itemformreqname);
			TestValidation.IsTrue(applyFilter, 
					"Applied Filter on" + itemformreqname, 
					"Failed to apply filter on" + itemformreqname);

			boolean recallTaskVisible = fsqaTasksPage.verifyRecallTaskIsDisplayed(itemformreqname);
			TestValidation.IsTrue(recallTaskVisible, 
					"Recall Task is not displayed "+ itemformreqname, 
					"Failed, Recall Task is display "+ itemformreqname);

		}
		finally {

			manageResource=home.clickResourcesMenu();

			boolean enableInstance = manageResource.searchAndActiveResource(RESOURCETYPES.ITEMS, itemsInstanceValue);
			TestValidation.IsTrue(enableInstance, 
					"Supplier Instance" + itemsInstanceValue+" Enabled", 
					"Failed to disable supplier instance"+ itemsInstanceValue);

			// login with superadmin cred
			boolean logout = home.userLogout();
			TestValidation.IsTrue(logout, 
					"Log out from application", 
					"Failed to Log out from application");

		}

	}


	@Test(description = "Inbox || Verify approval tasks should not be recalled after removal of supplier/item instance from requirement")
	public void TestCase_38419() throws InterruptedException {		
		try{

			supplierDetails = new SupplierPortalDetails();
			supplierDetails.userName = supplierUserName;//"Suppl_pr12";
			supplierDetails.password = GenericNewPassword;
			//suppformreqname1="SuppForm1_12.10_09.58.14";
			supplierDetails.formTask_Suppliers = suppformreqname;
			supplierDetails.suppliersResource = suppliersInstanceValue;
			//supptempname ="SupplierTemp_pr00102";

			home = login.performLogin(adminUsername, adminPassword);
			TestValidation.IsTrue(!home.error, 
					"LOGGED IN with Admin user " + adminUsername, 
					"Failed to login with Admin user " + adminUsername);

			home.clickFSQABrowserMenu();

			// remove instances 

			home.clickRequirementsMenu();

			boolean removeInstance = mrp.removeInstance(supptempname, "SUPPLIERS", suppliersInstanceValue);
			TestValidation.IsTrue(removeInstance, 
					"Supplier Instance is removed", 
					"Failed to remove supplier Instance");


			// verify recall
			home.clickFSQABrowserMenu();

			boolean navigate = fbp.selectResourceDropDownandNavigate("Suppliers","Tasks");
			TestValidation.IsTrue(navigate, 
					"Navigated to FSQABrowser>Task Tab", 
					"Failed to navigate to FSQABrowser>Task Tab");

			boolean applyFilter = fbp.openAndApplySettingsForColumn(COLUMNHEADER.TASKNAME, COLUMNSETTING.FILTER,suppformreqname);
			TestValidation.IsTrue(applyFilter, 
					"Applied Filter on" + suppformreqname, 
					"Failed to apply filter on" + suppformreqname);

			boolean recallTaskVisible = fsqaTasksPage.verifyRecallTaskIsDisplayed(suppformreqname);
			TestValidation.IsFalse(recallTaskVisible, 
					"Recall Task is not displayed "+ suppformreqname, 
					"Failed, Recall Task is display "+ suppformreqname);

		}
		finally {

			// login with superadmin cred
			boolean logout = home.userLogout();
			TestValidation.IsTrue(logout, 
					"Log out from application", 
					"Failed to Log out from application");

		}

	}
	
	@Test(description = "Inbox || Verify the task count")
	public void TestCase_30129() throws InterruptedException {

		try {
			//navigate to inbox
			//suppformreqname="SuppForm_18.11_21.27.15";
			ip=home.clickInboxMenu();
			//String count="588";
			boolean verifyInboxPage = home.verifyInboxPage();
			TestValidation.IsTrue(verifyInboxPage, 
					"Succesfully navigated to Inbox Page", 
					"Failed , navigate to Inbox Page  ");
			
			String count=(ip.TaskTotal).getText();
			
			//count increase by one
			home.clickFSQABrowserMenu();

			boolean navigate = fbp.selectResourceDropDownandNavigate("Suppliers","Forms");
			TestValidation.IsTrue(navigate, 
					"Navigated to FSQABrowser>FormsTab", 
					"Failed to navigate to FSQABrowser>Forms Tab");

			boolean applyfilter = fbp.openAndApplySettingsForColumn(COLUMNHEADER.FORMNAME, COLUMNSETTING.FILTER,formname);
			TestValidation.IsTrue(applyfilter, 
					"Applied Filter on" + COLUMNHEADER.FORMNAME, 
					"Failed to apply filter on" + COLUMNHEADER.FORMNAME);

			TASKDETAILS tsd = new TASKDETAILS();
			tsd.Location = locationInstanceValue;
			tsd.Resource = suppliersInstanceValue;
			tsd.TaskName = cctask;
			tsd.Workgroup = wgName;

			boolean assigntask = fbp.assignFormTask(tsd);
			TestValidation.IsTrue(assigntask, 
					"Form task assigned" + cctask, 
					"Failed to assign form task" + cctask);
			
			ip=home.clickInboxMenu();
			//String count="588";
			 verifyInboxPage = home.verifyInboxPage();
			TestValidation.IsTrue(verifyInboxPage, 
					"Succesfully navigated to Inbox Page", 
					"Failed , navigate to Inbox Page  ");
			//observe the search taskname
			boolean searchCompleteForm  = ip.searchTaskName(cctask);
			TestValidation.IsTrue(searchCompleteForm, 
					suppdocreqname+" is typesd in search box", 
					"Failed ,"+ suppdocreqname + " can't type in search box ");

			boolean taskVisible  = ip.taskVisible(cctask);
			TestValidation.IsTrue(taskVisible, 
					suppdocreqname+" is not visible in Inbox Page", 
					"Failed ,"+ suppdocreqname + " is visible in Inbox Page ");
			//check count 
			//
			boolean verifyTaskTotal = ip.verifyTaskTotal(searchCompleteForm, count );
			TestValidation.IsTrue(verifyTaskTotal, 
					"Task total count is reduced by 1, hence verified", 
					"Failed, to verify task count");

			
			
			// count reduce by one
			//open task and close task
			boolean approveTask1 =  ip.approveTask(suppformreqname);
			TestValidation.IsTrue(approveTask1, 
					"Task " + suppformreqname + " is succesfully Approved", 
					"Failed to open and close task  " + suppformreqname);

			//observe the search taskname
			 searchCompleteForm  = ip.searchTaskName(suppformreqname);
			TestValidation.IsTrue(searchCompleteForm, 
					suppdocreqname+" is typesd in search box", 
					"Failed ,"+ suppdocreqname + " can't type in search box ");

			 taskVisible  = ip.taskVisible(suppformreqname);
			TestValidation.IsFalse(taskVisible, 
					suppdocreqname+" is not visible in Inbox Page", 
					"Failed ,"+ suppdocreqname + " is visible in Inbox Page ");
			//check count 
			//
			 verifyTaskTotal = ip.verifyTaskTotal(searchCompleteForm, count );
			TestValidation.IsTrue(verifyTaskTotal, 
					"Task total count is reduced by 1, hence verified", 
					"Failed, to verify task count");

		}
		finally {
			controlActions.refreshPage();
			logInfo("Task performed");
		}

	}

	
	

	@AfterClass(alwaysRun = true)
	public void closeBrowser() throws InterruptedException {
		driver.close();
	}
}
