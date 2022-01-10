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
import com.project.safetychain.webapp.pageobjects.FSQABrowserTaskPage;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FORMTYPES;
import com.project.safetychain.webapp.pageobjects.SupplierPortalPage.SupplierPortalDetails;
import com.project.safetychain.webapp.pageobjects.UserManagerPage.UsrDetailParams;
import com.project.safetychain.webapp.pageobjects.TaskSchedulerPage;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.utilities.CommonMethods;
import com.project.utilities.ControlActions;
import com.project.utilities.DateTime;

public class TCG_REG_Inbox_Task_Flows extends TestBase{
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
	UserManagerPage ump;

	public static String locationCategoryValue;
	public static String locationInstanceValue;
	public static String locationInstanceValue2;
	public static String itemsCategoryValue;
	public static String itemsInstanceValue;
	public static String suppliersCategoryValue;
	public static String suppliersInstanceValue;
	public static String formname ;
	public static String wgName;
	public static String qformName;	
	public static String numericFN = "Numeric";	
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
	public static String schedulename;
	public static String formDueByDate;
	public static String currentDate;

	//requirement type creation//
	public static String suppdocreqname,suppdocreqname1,suppformreqname,suppformreqname1;
	public static String itemdocreqname,itemdocreqname1,itemformreqname,itemformreqname1;
	public static String supptempname,itemtempname,ackreqname;
	public static String submissionComment;

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

		locationCategoryValue = CommonMethods.dynamicText("LC"); 
		locationInstanceValue = CommonMethods.dynamicText("LI");
		locationInstanceValue2 =CommonMethods.dynamicText("LI2");
		itemsCategoryValue =  CommonMethods.dynamicText("IC");
		itemsInstanceValue =  CommonMethods.dynamicText("II");
		suppliersCategoryValue =CommonMethods.dynamicText("SC");
		suppliersInstanceValue = CommonMethods.dynamicText("SI");
		wgName = CommonMethods.dynamicText("WG");
		formname = CommonMethods.dynamicText("Quest");
		suppdocreqname =CommonMethods.dynamicText("SuppDoc");
		suppdocreqname1 = CommonMethods.dynamicText("SuppDoc1");
		suppformreqname = CommonMethods.dynamicText("SuppForm");
		suppformreqname1 =CommonMethods.dynamicText("SuppForm1"); 
		itemdocreqname =CommonMethods.dynamicText("ItemDoc");
		itemdocreqname1 = CommonMethods.dynamicText("ItemDoc1");
		itemformreqname = CommonMethods.dynamicText("ItemForm");
		itemformreqname1 =CommonMethods.dynamicText("ItemForm1"); 
		supptempname =CommonMethods.dynamicText("SupplierTemp");
		itemtempname =CommonMethods.dynamicText("ItemTemp");
		docTypeName =CommonMethods.dynamicText("Supplier_DocType"); 
		image = "upload.png";
		submissionComment = CommonMethods.dynamicText("Comment");	
		document = System.getProperty("user.dir") + "\\test-data-files\\UploadDocuments\\"+image;

		supplierinstance = new ArrayList<String>();
		supplierinstance.add(suppliersInstanceValue);
		iteminstance = new ArrayList<String>();
		iteminstance.add(itemsInstanceValue);



		// ------------------------------------------------------------------------------------------------
		// API Implementation
		TCG_FormCreation_Wrapper formCreationWrapper = new TCG_FormCreation_Wrapper();
		//ApiUtils apiUtils = new ApiUtils();

		// ------------------------------------------------------------------------------------------------
		// API - Location & Resource Creation and Linking
		List<String> userList = Arrays.asList(adminUsername);

		HashMap<String, List<String>> LocationMap = new HashMap<String, List<String>>();
		LocationMap.put(locationCategoryValue, Arrays.asList(locationInstanceValue));

		HashMap<String, List<String>> resourceCatMap = new HashMap<String, List<String>>();
		resourceCatMap.put(suppliersCategoryValue, Arrays.asList(suppliersInstanceValue));

		formCreationWrapper.Resource_Location_CreationWrapper(resourceCatMap, LocationMap, true, userList,
				RESOURCE_TYPES.SUPPLIERS);
		// ------------------------------------------------------------------------------------------------
		// WEB Application code starts here

		// ------------------------------------------------------------------------------------------------

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
	
				//FORM - Creation and Release
		
				if(!fdp.createAndReleaseForm(FORMTYPES.QUESTIONNAIRE, formname,"Suppliers", suppliersCategoryValue,
						suppliersInstanceValue)) {
					TCGFailureMsg = "Could not create and release form - " + formname;
					logFatal(TCGFailureMsg);
					throw new SkipException(TCGFailureMsg);
				}
		
				if(!wgp.createWorkGroup(wgName, adminUsername)) {
					TCGFailureMsg = "Could not create workgroup - " + wgName;
					logFatal(TCGFailureMsg);
					throw new SkipException(TCGFailureMsg);
				}
		
				DocumentManagementPage dmp = home.clickdocumentsmenu();
				if(!dmp.docUploadinDocType (docTypeName,document)) {
					TCGFailureMsg = "Could not upload document in doctype- " + docTypeName;
					logFatal(TCGFailureMsg);
					throw new SkipException(TCGFailureMsg);
				}
		
				// Create requirement for Supplier instance
				ManageRequirementPage mrp = home.clickRequirementsMenu();		
				if(!mrp.createSuppReq(supptempname, docTypeName, wgName, suppdocreqname, suppdocreqname1, formname, 
						suppformreqname, suppformreqname1, suppliersInstanceValue)) {
					TCGFailureMsg = "Could not create requirement for supplier " + suppliersInstanceValue;
					logFatal(TCGFailureMsg);
					throw new SkipException(TCGFailureMsg);
		
				}
				
				if(!mrp.createitemreq(itemtempname, docTypeName, wgName, itemdocreqname, itemdocreqname1, formname, 
						itemformreqname, itemformreqname1, itemsInstanceValue)) {
					TCGFailureMsg = "Could not create requirement for item " + itemsInstanceValue;
					logFatal(TCGFailureMsg);
					throw new SkipException(TCGFailureMsg);
		
				}
				//supptempname = "SupplierTemp_pr0123";
		
				roleName = "superadmin";
				userFN = CommonMethods.dynamicText("FN");//"FN_11.11_11.34.56";
				userLN = CommonMethods.dynamicText("LN");//"LN_11.11_11.34.56";
				userLocation = new ArrayList<String>();
				supplierUserName = CommonMethods.dynamicText("SupplierUser");//"Suppl_pr12";"SupplierUser_11.11_11.34.58";
				supplierPassword = GenericPassword;
				supplierNewPassword = GenericNewPassword;
				userLocation.add(locationInstanceValue2);
		
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
		
				if(!manageUser.supplierUserCreation(udp)) {
					TCGFailureMsg = "Could NOT create supplier user";
					logFatal(TCGFailureMsg);
					throw new SkipException(TCGFailureMsg);
				}
		
				if(!home.userLogout()){
					TCGFailureMsg = "Could NOT logout from application";
					logFatal(TCGFailureMsg);
					throw new SkipException(TCGFailureMsg);
				}
		
						if(!(login.performLogin(supplierUserName, supplierPassword, supplierNewPassword))) {
							TCGFailureMsg = "Could NOT login to application";
							logFatal(TCGFailureMsg);
							throw new SkipException(TCGFailureMsg);
						}
		
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
//						
						if(!home.userLogout()){
							TCGFailureMsg = "Could NOT logout from application";
							logFatal(TCGFailureMsg);
							throw new SkipException(TCGFailureMsg);
						}
						
						login.performLogin(adminUsername, adminPassword);
						if(home.error) {
							TCGFailureMsg = "Could NOT login to application";
							logFatal(TCGFailureMsg);
							throw new SkipException(TCGFailureMsg);
						}

	}

	@Test(description = "Inbox || Verify document upload task. ")
	public void TestCase_34934() throws InterruptedException {
		try{
			supplierDetails = new SupplierPortalDetails();
			supplierDetails.userName = supplierUserName;//"Suppl_pr12";
			supplierDetails.password = GenericNewPassword;
			supplierDetails.formTask_Suppliers = suppformreqname;
			supplierDetails.suppliersResource = suppliersInstanceValue;

			supplierDetails.documentTask_Suppliers = suppdocreqname;


			String [] formTaskName = {suppformreqname};
			String [] docTaskName = {suppdocreqname};


			home = login.performLogin(supplierDetails.userName, GenericNewPassword);
			TestValidation.IsTrue(!home.error, 
					"LOGGED IN with Supplier user " + supplierDetails.userName, 
					"Failed to login with Supplier user " + supplierDetails.userName);

			boolean verifySearchedTask = supplierPage.verifySearchedTask(supplierDetails.formTask_Suppliers);
			TestValidation.IsTrue(verifySearchedTask, 
					"Task is displayed in Suppleir login ->Inbox", 
					"Failed, Task not displayed in Inbox");

			//uploaddoc

			verifySearchedTask = supplierPage.verifySearchedTask(supplierDetails.documentTask_Suppliers);
			TestValidation.IsTrue(verifySearchedTask, 
					"Task is displayed in Suppleir login ->Inbox", 
					"Failed, Task not displayed in Inbox");

			boolean openSuppliersDocumentUploadTask = supplierPage.openDocumentUpload(docTypeName, supplierDetails.documentTask_Suppliers, supplierDetails.suppliersResource);
			TestValidation.IsTrue(openSuppliersDocumentUploadTask, 
					"OPENED 'Supplier' document upload task", 
					"Could NOT open 'Suppliers' document upload task");

			boolean submitSuppliersTask = supplierPage.uploadDocument(uploadDocumentPath, true);
			TestValidation.IsTrue(submitSuppliersTask, 
					"Submitted task", 
					"Could NOT submit task");


			// login with superadmin cred
			boolean logout = home.userLogout();
			TestValidation.IsTrue(logout, 
					"Log out from application", 
					"Failed to Log out from application");

			home = login.performLogin(adminUsername, adminPassword);
			TestValidation.IsTrue(!home.error, 
					"LOGGED IN with Admin user " + adminUsername, 
					"Failed to login with Admin user " + adminUsername);
			//		 
			//approve one say doc upload task
			InboxPage ip = home.clickInboxMenu();
			//approve complete task

			ip = home.clickInboxMenu();
			boolean approve =  ip.actionOnTask(docTaskName, true,  false);
			TestValidation.IsTrue(approve, 
					"Task " + formTaskName + " has been Approved", 
					"Failed to Approve task " + formTaskName);

		}
		finally {

			// login with superadmin cred
			boolean logout = home.userLogout();
			TestValidation.IsTrue(logout, 
					"Log out from application", 
					"Failed to Log out from application");

		}

	}
	
	@Test(description = " Inbox || Task History - An event is logged in the Task History whenever a task is submitted ")
	public void TestCase_12154() throws InterruptedException {
		try{


			DateTime dt = new DateTime();
			formDueByDate = dt.AddDaystoToddaysDate(3, "MM/dd/YYYY");
			timezoneCode = ump.getTimezoneCode(adminTimezone);
			currentDate = dt.getTodayDate("MM/dd/YYYY", dt.getTimezone(adminTimezone));
			
			supplierDetails = new SupplierPortalDetails();
			supplierDetails.userName = supplierUserName;//"Suppl_pr12";
			supplierDetails.password = GenericNewPassword;
			supplierDetails.formTask_Suppliers = suppformreqname;
			supplierDetails.suppliersResource = suppliersInstanceValue;
			supplierDetails.documentTask_Suppliers = suppdocreqname;


			String [] formTaskName = {suppformreqname};
			String [] docTaskName = {suppdocreqname};


			home = login.performLogin(supplierDetails.userName, GenericNewPassword);
			TestValidation.IsTrue(!home.error, 
					"LOGGED IN with Supplier user " + supplierDetails.userName, 
					"Failed to login with Supplier user " + supplierDetails.userName);

			boolean verifySearchedTask = supplierPage.verifySearchedTask(supplierDetails.formTask_Suppliers);
			TestValidation.IsTrue(verifySearchedTask, 
					"Task is displayed in Suppleir login ->Inbox", 
					"Failed, Task not displayed in Inbox");

			//uploaddoc

			verifySearchedTask = supplierPage.verifySearchedTask(supplierDetails.documentTask_Suppliers);
			TestValidation.IsTrue(verifySearchedTask, 
					"Task is displayed in Suppleir login ->Inbox", 
					"Failed, Task not displayed in Inbox");

			boolean openSuppliersDocumentUploadTask = supplierPage.openDocumentUpload(docTypeName, supplierDetails.documentTask_Suppliers, supplierDetails.suppliersResource);
			TestValidation.IsTrue(openSuppliersDocumentUploadTask, 
					"OPENED 'Supplier' document upload task", 
					"Could NOT open 'Suppliers' document upload task");

			boolean submitSuppliersTask = supplierPage.uploadDocument(uploadDocumentPath, true);
			TestValidation.IsTrue(submitSuppliersTask, 
					"Submitted task", 
					"Could NOT submit task");


			// login with superadmin cred
			boolean logout = home.userLogout();
			TestValidation.IsTrue(logout, 
					"Log out from application", 
					"Failed to Log out from application");

			home = login.performLogin(adminUsername, adminPassword);
			TestValidation.IsTrue(!home.error, 
					"LOGGED IN with Admin user " + adminUsername, 
					"Failed to login with Admin user " + adminUsername);
			//		 
			//approve one say doc upload task
			InboxPage ip = home.clickInboxMenu();
			//approve complete task

			ip = home.clickInboxMenu();
			boolean approve =  ip.actionOnTask(docTaskName, true,  false);
			TestValidation.IsTrue(approve, 
					"Task " + formTaskName + " has been Approved", 
					"Failed to Approve task " + formTaskName);
			boolean openInboxHistory = ip.clickHistoryIcon();
			TestValidation.IsTrue(openInboxHistory, 
								  "OPENED History for Form task " + suppformreqname, 
								  "Failed to Open history for Form task " + suppformreqname);
			
			boolean verifyInboxWindowTitle = ip.HistWindowTitle.getText().contains("Task History");
			TestValidation.IsTrue(verifyInboxWindowTitle, 
								  "VERIFIED History window's title is 'Task History'", 
								  "Failed to Verify History window's title is 'Task History'");
			
			String expectedMsg1 = "Assigned to " + suppliersInstanceValue + " by " + displayName + "|" +
					 				timezoneCode  + "|" + currentDate  + "|" + formDueByDate;
			boolean verifyActualMsg1 = ip.verifyTaskHistoryPopupDetails(expectedMsg1);
			TestValidation.IsTrue(verifyActualMsg1, 
					  			  "VERIFIED Task History details whenever a task is Assigned", 
					  			  "Failed to verify the Task History details whenever a task is Assigned");
			
			String expectedMsg2 = "Submitted by " + udp.FirstName + " " + udp.LastName + " , " + udp.Title 
					+ " at " + suppliersInstanceValue + "|" + timezoneCode  + "|" + currentDate  + "|" + 
					suppformreqname + "|" + submissionComment;
			boolean verifyActualMsg2 = ip.verifyTaskHistoryPopupDetails(expectedMsg2);
			TestValidation.IsTrue(verifyActualMsg2, 
					  			  "VERIFIED the Task History details whenever a task is Submitted by Supplier user", 
					  			  "Failed to verify the Task History details whenever a task is Submitted by Supplier user");
			
			boolean closeInboxHistWindow = ip.closeHistoryPopup();
			TestValidation.IsTrue(closeInboxHistWindow, 
								  "CLOSED History window opened for Form task " + suppformreqname, 
								  "Failed to Close history window");
			
			
			

		}
		finally {

			// login with superadmin cred
			boolean logout = home.userLogout();
			TestValidation.IsTrue(logout, 
					"Log out from application", 
					"Failed to Log out from application");
		}
	}

	@Test(description = "Inbox || Verify rejection of form approval task.")
	public void TestCase_34931() throws InterruptedException {
		try{
			supplierDetails = new SupplierPortalDetails();
			supplierDetails.userName = supplierUserName;//"Suppl_pr12";
			supplierDetails.password = GenericNewPassword;
			supplierDetails.formTask_Suppliers = suppformreqname;
			supplierDetails.suppliersResource = suppliersInstanceValue;

			String [] formTaskName = {suppformreqname};

			home = login.performLogin(supplierDetails.userName, GenericNewPassword);
			TestValidation.IsTrue(!home.error, 
					"LOGGED IN with Supplier user " + supplierDetails.userName, 
					"Failed to login with Supplier user " + supplierDetails.userName);

			boolean verifySearchedTask = supplierPage.verifySearchedTask(supplierDetails.formTask_Suppliers);
			TestValidation.IsTrue(verifySearchedTask, 
					"Task is displayed in Suppleir login ->Inbox", 
					"Failed, Task not displayed in Inbox");

			// xcomplete task

			boolean openSuppliersCompleteFormTask = supplierPage.openCompleteForm(formname , supplierDetails.formTask_Suppliers, supplierDetails.suppliersResource);
			TestValidation.IsTrue(openSuppliersCompleteFormTask, 
					"OPENED 'Supplier' complete form task", 
					"Could NOT open 'Suppliers' complete form task");

			boolean submitSuppliersTask = fsqaFormsPage.submitData(false, false, false, true,false);
			TestValidation.IsTrue(submitSuppliersTask, 
					"Submitted task", 
					"Could NOT submit task");

			// login with superadmin cred
			boolean logout = home.userLogout();
			TestValidation.IsTrue(logout, 
					"Log out from application", 
					"Failed to Log out from application");

			home = login.performLogin(adminUsername, adminPassword);
			TestValidation.IsTrue(!home.error, 
					"LOGGED IN with Admin user " + adminUsername, 
					"Failed to login with Admin user " + adminUsername);
			//		 
			//approve one say doc upload task
			InboxPage ip = home.clickInboxMenu();

			boolean actionReject =  ip.actionOnTask(formTaskName, false, true);
			TestValidation.IsTrue(actionReject, 
					"Task " + formTaskName + " is REJECTED", 
					"Failed to Reject task " + formTaskName);
	 
			// navigate fsqa->task -> check change duedate
			home.clickFSQABrowserMenu();

			boolean navigate = fbp.selectResourceDropDownandNavigate("Suppliers","Tasks");
			TestValidation.IsTrue(navigate, 
					"Navigated to FSQABrowser>FormsTab", 
					"Failed to navigate to FSQABrowser>Forms Tab");

			boolean applyfilter = fbp.openAndApplySettingsForColumn(COLUMNHEADER.TASKNAME, COLUMNSETTING.FILTER,suppformreqname);
			TestValidation.IsTrue(applyfilter, 
					"Applied Filter on" + suppformreqname, 
					"Failed to apply filter on" + suppformreqname);

			boolean changeInDueVisible = fsqaTasksPage.verifyChangeDueDateIsDisplayed(suppformreqname);
			TestValidation.IsFalse(changeInDueVisible, 
					"Change In Due is displayed "+ suppformreqname, 
					"Failed, Change In Due isn't display "+ suppformreqname);

		}
		finally {

			// login with superadmin cred
			boolean logout = home.userLogout();
			TestValidation.IsTrue(logout, 
					"Log out from application", 
					"Failed to Log out from application");

		}
	}

	
	@AfterClass(alwaysRun = true)
	public void closeBrowser() throws InterruptedException {
		driver.close();
	}
}
