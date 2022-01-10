package com.project.safetychain.webapp.testcases;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

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
import com.project.safetychain.webapp.pageobjects.FSQABrowserTaskPage.HISTORY_ACTIONS;
import com.project.safetychain.webapp.pageobjects.FSQABrowserTaskPage.HISTORY_DATA;
import com.project.safetychain.webapp.pageobjects.FSQABrowserTaskPage;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FORMTYPES;
import com.project.safetychain.webapp.pageobjects.ManageResourcePage.RESOURCETYPES;
import com.project.safetychain.webapp.pageobjects.ResourceDesignerPage.CATEGORYTYPES;
import com.project.safetychain.webapp.pageobjects.SupplierPortalPage.SupplierPortalDetails;
import com.project.safetychain.webapp.pageobjects.TaskSchedulerPage;
import com.project.safetychain.webapp.pageobjects.UserManagerPage.UsrDetailParams;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.utilities.CommonMethods;
import com.project.utilities.ControlActions;
import com.project.utilities.DateTime;

public class TCG_REG_FSQA_SupplierFlows extends TestBase{
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

	public static String locationCategoryValue;
	public static String locationInstanceValue;
	public static String locationInstanceValue2;
	public static String customersCategoryValue;
	public static String customersInstanceValue;
	public static String equipmentCategoryValue;
	public static String equipmentInstanceValue;
	public static String itemsCategoryValue;
	public static String itemsInstanceValue;
	public static String suppliersCategoryValue;
	public static String suppliersInstanceValue;
	public static String formname ;
	public static String wgName;
	public static String wgName1;
	public static String qformName;	
	public static String numericFN = "Numeric";	
	public static String cctask,catask,cqtask,ectask,eatask,eqtask,ictask,iatask,iqtask,sctask,satask,sqtask,duebyTask,locTask,locTask1 ;
	public static String displayName = null;
	public static String timezoneCode = null;
	public static String usrTimezone = null;

	public static String roleName;
	public static String userUN;
	public static String userFN;
	public static String userLN;
	public static List<String> supplierinstance;
	public static List<String> userLocation;
	public static List<String> userRole , workgroup;
	public static String supplierUserName, supplierPassword, supplierNewPassword;
	//document upload// 
	public static String image;
	public static String document;
	UsrDetailParams udp;
	public static String schedulename;
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

		locationCategoryValue = CommonMethods.dynamicText("LC"); //"LC_pr01";
		locationInstanceValue = CommonMethods.dynamicText("LI"); //"LI_11.11_10.09.28";
		locationInstanceValue2 =CommonMethods.dynamicText("LI2"); //"LI2_pr01";
		customersCategoryValue = CommonMethods.dynamicText("CC"); //"CC_pr01";
		customersInstanceValue = CommonMethods.dynamicText("CI"); //"CI_pr01";
		equipmentCategoryValue = CommonMethods.dynamicText("EC"); //"EC_pr01";
		equipmentInstanceValue = CommonMethods.dynamicText("EI"); //"EI_pr01";
		itemsCategoryValue =  CommonMethods.dynamicText("IC"); //"IC_pr01";
		itemsInstanceValue =  CommonMethods.dynamicText("II"); //"II_11.11_10.09.28";
		suppliersCategoryValue =CommonMethods.dynamicText("SC");//"SC_pr01"
		suppliersInstanceValue = CommonMethods.dynamicText("SI");//  "SI_11.11_10.09.28";
		wgName = CommonMethods.dynamicText("WG"); //"WG_pr01";
		wgName1 = CommonMethods.dynamicText("WG1");//"WG1_pr01";
		formname = CommonMethods.dynamicText("Quest"); // "Quest_11.11_10.09.28";
		cctask =  CommonMethods.dynamicText("CCTask"); //"CCTask_pr01";
		catask = CommonMethods.dynamicText("CATask"); // "CATask_pr01";
		cqtask = CommonMethods.dynamicText("CQTask"); //"CQTask_pr01"; 
		ectask = CommonMethods.dynamicText("ECTask"); // "ECTask_pr01";
		eatask = CommonMethods.dynamicText("EATask"); // "EATask_pr01";
		eqtask = CommonMethods.dynamicText("EQTask"); //"EQTask_pr01";
		ictask= CommonMethods.dynamicText("ICTask"); //"ICTask_pr01"; 
		iatask =CommonMethods.dynamicText("IATask"); //"IATask_pr01"; 
		iqtask =CommonMethods.dynamicText("IQTask"); // "IQTask_pr01";
		sctask = CommonMethods.dynamicText("SCTask"); //"SCTask_pr01";
		satask = CommonMethods.dynamicText("SATask"); //"SATask_pr01";
		sqtask = CommonMethods.dynamicText("SQTask"); //"SQTask_pr01";
		duebyTask = CommonMethods.dynamicText("TaskDueBy"); //"TaskDueBy_pr01";

		schedulename =CommonMethods.dynamicText("Schedule"); // "Schedule_pr01";
		filtertask = CommonMethods.dynamicText("FilterTask"); //"FilterTask_pr01";
		suppdocreqname =CommonMethods.dynamicText("SuppDoc");//"SuppDoc_11.11_10.09.28";
		suppdocreqname1 = CommonMethods.dynamicText("SuppDoc1"); //"SuppDoc1_11.11_10.09.28";
		suppformreqname = CommonMethods.dynamicText("SuppForm");//"SuppForm_11.11_10.09.28";
		suppformreqname1 =CommonMethods.dynamicText("SuppForm1"); //"SuppForm1_11.11_10.09.28"; 
		itemdocreqname =CommonMethods.dynamicText("ItemDoc");//"ItemDoc_11.11_10.09.28";
		itemdocreqname1 = CommonMethods.dynamicText("ItemDoc1"); //"ItemDoc1_11.11_10.09.28";
		itemformreqname = CommonMethods.dynamicText("ItemForm");//"SuppForm_pr00102";"ItemForm_11.11_10.09.28";
		itemformreqname1 =CommonMethods.dynamicText("ItemForm1"); //"SuppForm1_pr00102"; "ItemForm1_11.11_10.09.28";
		supptempname =CommonMethods.dynamicText("SupplierTemp");//"SupplierTemp_pr00102"; "SupplierTemp_11.11_10.09.28";
		itemtempname =CommonMethods.dynamicText("ItemTemp");//"SupplierTemp_pr00102"; "ItemTemp_11.11_10.11.10";
		docTypeName =CommonMethods.dynamicText("Supplier_DocType");//"Supplier_DocType_11.11_10.11.10"; 
		image = "upload.png";
		document = System.getProperty("user.dir") + "\\test-data-files\\UploadDocuments\\"+image;

		supplierinstance = new ArrayList<String>();
		supplierinstance.add(suppliersInstanceValue);


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

		String supplierInstances[][] = {{suppliersInstanceValue,"true"}};
		String itemInstances[][] = {{itemsInstanceValue,"true"}};

		HashMap<String,String> resourceCategories = new HashMap<String,String>();
		resourceCategories.put(CATEGORYTYPES.LOCATION, locationCategoryValue);
		resourceCategories.put(CATEGORYTYPES.ITEMS, itemsCategoryValue);
		resourceCategories.put(CATEGORYTYPES.SUPPLIERS, suppliersCategoryValue);

		if(!rdp.createCategory(resourceCategories)) {
			TCGFailureMsg = "Could NOT create Locations/Items/Suppliers category - ";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		if(!locations.createLocationInstanceLinkUser(locationCategoryValue,locationInstanceValue,adminUsername)) {
			TCGFailureMsg = "Could NOT create location instance - " + locationCategoryValue;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		if(!manageResource.addResourceInstances("Items", itemsCategoryValue, itemInstances, true, locationInstanceValue)) {
			TCGFailureMsg = "Could NOT create resource " + itemsCategoryValue + " & link Location - "+ locationInstanceValue;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		if(!manageResource.addResourceInstances("Suppliers", suppliersCategoryValue, supplierInstances,true, locationInstanceValue)) {
			TCGFailureMsg = "Could NOT create resource " + suppliersCategoryValue + " & link Location - "+ locationInstanceValue;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		if(!manageResource.linkItemSupplier(itemsInstanceValue)) {
			TCGFailureMsg = "Could NOT link supplier resource " + suppliersInstanceValue + " with item resource - "+ itemsInstanceValue;
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

		if(!mrp.createItemRequirement(itemtempname,docTypeName,itemdocreqname,wgName,itemdocreqname1,formname,
				itemformreqname,itemsInstanceValue)) {
			TCGFailureMsg = "Could not create requirement for item " + suppliersInstanceValue;
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

		if(!home.userLogout()){
			TCGFailureMsg = "Could NOT logout from application";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

	}

	@Test(description = "Task Monitoring || FSQA Browser || Users can view time remaining"
			+ "on a task that is Due Later/Today")
	public void TestCase_14534() throws InterruptedException {	

		String task=suppdocreqname;//"SuppDoc_11.11_21.20.37";

		home = login.performLogin(adminUsername, adminPassword);
		TestValidation.IsTrue(!home.error, 
				"LOGGED IN with Admin user " + adminUsername, 
				"Failed to login with Admin user " + adminUsername);

		home.clickFSQABrowserMenu();

		boolean navigate = fbp.selectResourceDropDownandNavigate("Suppliers","Tasks");
		TestValidation.IsTrue(navigate, 
				"Navigated to FSQABrowser>FormsTab", 
				"Failed to navigate to FSQABrowser>Forms Tab");

		boolean applyfilter = fbp.openAndApplySettingsForColumn(COLUMNHEADER.TASKNAME, COLUMNSETTING.FILTER,task);
		TestValidation.IsTrue(applyfilter, 
				"Applied Filter on" + task, 
				"Failed to apply filter on" + task);

		boolean changeCursor = fbp.changeCursor(task);
		TestValidation.IsTrue(changeCursor, 
				"Cursor changed from cursor to pointer" , 
				"Failed to notice cursor change" );

		boolean hoverOutStandingTask = fbp.hoverOutStandingTask(task);
		TestValidation.IsTrue(hoverOutStandingTask, 
				"Successfully hovered over Outstanding task" + task +" tooltip text matched the given format", 
				"Failed to hover over outstanding task" + task );

	}


	@Test(description = "Task Monitoring || Verify Due By Date for Approval Task")
	public void TestCase_16618() throws InterruptedException {	

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

			// xcomplete task

			boolean openSuppliersCompleteFormTask = supplierPage.openCompleteForm(formname , supplierDetails.formTask_Suppliers, supplierDetails.suppliersResource);
			TestValidation.IsTrue(openSuppliersCompleteFormTask, 
					"OPENED 'Supplier' complete form task", 
					"Could NOT open 'Suppliers' complete form task");

			boolean submitSuppliersTask = fsqaFormsPage.submitData(false, false, false, true,false);
			TestValidation.IsTrue(submitSuppliersTask, 
					"Submitted task", 
					"Could NOT submit task");

			//uploaddoc

			verifySearchedTask = supplierPage.verifySearchedTask(supplierDetails.documentTask_Suppliers);
			TestValidation.IsTrue(verifySearchedTask, 
					"Task is displayed in Suppleir login ->Inbox", 
					"Failed, Task not displayed in Inbox");

			boolean openSuppliersDocumentUploadTask = supplierPage.openDocumentUpload(docTypeName, supplierDetails.documentTask_Suppliers, supplierDetails.suppliersResource);
			TestValidation.IsTrue(openSuppliersDocumentUploadTask, 
					"OPENED 'Supplier' document upload task", 
					"Could NOT open 'Suppliers' document upload task");

			submitSuppliersTask = supplierPage.uploadDocument(uploadDocumentPath, true);
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

			boolean action =  ip.actionOnTask(formTaskName, false, false);
			TestValidation.IsTrue(action, 
					"Task " + formTaskName + " is not  APPROVED", 
					"Failed to Approve task " + formTaskName);

			//reject complete task

			ip = home.clickInboxMenu();
			boolean reject =  ip.actionOnTask(docTaskName, false,  true);
			TestValidation.IsTrue(reject, 
					"Task " + formTaskName + " has been REJECTED", 
					"Failed to Reject task " + formTaskName);

			//		 
			// nsvigate fsqa->task -> check change duedate
			home.clickFSQABrowserMenu();

			boolean navigate = fbp.selectResourceDropDownandNavigate("Suppliers","Tasks");
			TestValidation.IsTrue(navigate, 
					"Navigated to FSQABrowser>FormsTab", 
					"Failed to navigate to FSQABrowser>Forms Tab");

			boolean applyfilter = fbp.openAndApplySettingsForColumn(COLUMNHEADER.TASKNAME, COLUMNSETTING.FILTER,suppdocreqname);
			TestValidation.IsTrue(applyfilter, 
					"Applied Filter on" + suppdocreqname, 
					"Failed to apply filter on" + suppdocreqname);


			boolean changeInDueVisible = fsqaTasksPage.verifyChangeDueDateIsDisplayed(suppdocreqname);
			TestValidation.IsTrue(changeInDueVisible, 
					"Change In Due is display " + suppdocreqname, 
					"Failed, Change In Due isn't displayed " +suppdocreqname);

			applyfilter = fbp.openAndApplySettingsForColumn(COLUMNHEADER.TASKNAME, COLUMNSETTING.FILTER,suppformreqname);
			TestValidation.IsTrue(applyfilter, 
					"Applied Filter on" + suppformreqname, 
					"Failed to apply filter on" + suppformreqname);

			changeInDueVisible = fsqaTasksPage.verifyChangeDueDateIsDisplayed(suppformreqname);
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

	@Test(description = "Task Monitoring || FSQA Browser || An event is shown in the Task\r\n"
			+ "History modal whenever a task is resubmitted")
	public void TestCase_16226() throws InterruptedException {	

		try {

			supplierDetails = new SupplierPortalDetails();
			supplierDetails.userName =supplierUserName;// "Suppl_pr12";//;
			supplierDetails.password = GenericNewPassword;
			supplierDetails.formTask_Suppliers = suppformreqname;
			supplierDetails.suppliersResource = suppliersInstanceValue;

			supplierDetails.documentTask_Suppliers = suppdocreqname1;


			String [] docTaskName = {suppdocreqname1};


			home = login.performLogin(supplierDetails.userName, GenericNewPassword);
			TestValidation.IsTrue(!home.error, 
					"LOGGED IN with Supplier user " + supplierDetails.userName, 
					"Failed to login with Supplier user " + supplierDetails.userName);
			//		
			//		
			//uploaddoc

			boolean verifySearchedTask = supplierPage.verifySearchedTask(supplierDetails.documentTask_Suppliers);
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
			//			
			home = login.performLogin(adminUsername, adminPassword);
			TestValidation.IsTrue(!home.error, 
					"LOGGED IN with Admin user " + adminUsername, 
					"Failed to login with Admin user " + adminUsername);
			////		 
			//		//approve one say doc upload task
			InboxPage ip = home.clickInboxMenu();

			//reject complete task

			boolean reject =  ip.actionOnTask(docTaskName, false,  true);
			TestValidation.IsTrue(reject, 
					"Task " + docTaskName + " has been REJECTED", 
					"Failed to Reject task " + docTaskName);		



			//login with supplr cred and resubmit task
			logout = home.userLogout();
			TestValidation.IsTrue(logout, 
					"Log out from application", 
					"Failed to Log out from application");

			home= login.performLogin(supplierDetails.userName, supplierDetails.password);
			TestValidation.IsTrue(!home.error, 
					"logged in with supplier user", 
					"Could not login with supplier user");

			boolean verifyOpenedRejectedTask = supplierPage.verifyRejectedTask(supplierDetails.documentTask_Suppliers,false);
			TestValidation.IsTrue(verifyOpenedRejectedTask, 
					"Verified opened'Rejected Task'", 
					"Could NOT verify opened 'Rejected Task'");

			openSuppliersDocumentUploadTask = supplierPage.openDocumentUpload(docTypeName, supplierDetails.documentTask_Suppliers, supplierDetails.suppliersResource);
			TestValidation.IsTrue(openSuppliersDocumentUploadTask, 
					"OPENED 'Supplier' document upload task", 
					"Could NOT open 'Suppliers' document upload task");

			submitSuppliersTask = supplierPage.uploadDocument(uploadDocumentPath, false);
			TestValidation.IsTrue(submitSuppliersTask, 
					"Submitted task", 
					"Could NOT submit task");

			// login with superadmin cred
			logout = home.userLogout();
			TestValidation.IsTrue(logout, 
					"Log out from application", 
					"Failed to Log out from application");

			home = login.performLogin(adminUsername, adminPassword);
			TestValidation.IsTrue(!home.error, 
					"LOGGED IN with Admin user " + adminUsername, 
					"Failed to login with Admin user " + adminUsername);

			// check inbox  -> display of resubmitted task
			ip = home.clickInboxMenu();
			boolean displayResubmittedTask =  ip.searchTaskName(suppdocreqname1);
			TestValidation.IsTrue(displayResubmittedTask, 
					"Task " + suppdocreqname1 + " is displayed in Inbox after Resubmittig", 
					"Failed to display task " + suppdocreqname1 +"after Resubmitting");					
			////		 
			//		 // navigate fsqa->task -> check change duedate
			home.clickFSQABrowserMenu();

			boolean navigate = fbp.selectResourceDropDownandNavigate("Suppliers","Tasks");
			TestValidation.IsTrue(navigate, 
					"Navigated to FSQABrowser>FormsTab", 
					"Failed to navigate to FSQABrowser>Forms Tab");

			boolean applyFilter = fbp.openAndApplySettingsForColumn(COLUMNHEADER.TASKNAME, COLUMNSETTING.FILTER,suppdocreqname1);
			TestValidation.IsTrue(applyFilter, 
					"Applied Filter on" + suppdocreqname1, 
					"Failed to apply filter on" + suppdocreqname1);

//			//verify Task History
//			//		
//
//			String Date = dt.getDateTime("Day", 1, 2, 10, false, false);
//			boolean changeDueDate = fbp.changeDueByOfTask(suppdocreqname1, Date, null);
//			TestValidation.IsTrue(changeDueDate, 
//					"CHANGED due date for Task to - " + Date, 
//					"Failed to change due date for Task to - " + Date);
			DateTime dt = new DateTime(driver);
			String date = dt.getDate("Day", 1);
			String time = dt.getTime(2, 10, true, true);
			HISTORY_DATA data = new HISTORY_DATA();
			data.Workgroup = wgName;
			data.Username = displayName;
			data.AssignedDueBy = " Due By:   No Due Date" ;

			data.DueBy = time+" "+"IST"+" on "+date;

			boolean verifyTaskHistory = fsqaTasksPage.verifyTaskHistory(suppdocreqname1,HISTORY_ACTIONS.DUEBY, data);
			TestValidation.IsTrue(verifyTaskHistory, 
					"Verified Task History for resubmitted task", 
					"Failed to verify Task History for resubmitted task");
		}
		finally {

			// login with superadmin cred
			boolean logout = home.userLogout();
			TestValidation.IsTrue(logout, 
					"Log out from application", 
					"Failed to Log out from application");

		}

	}
	//
	//	
	@Test(description = "Task Monitoring || FSQA Browser || An event is shown in the Task "
			+ "History modal whenever a task is rejected ")
	public void TestCase_16244() throws InterruptedException {	

		try{	
			supplierDetails = new SupplierPortalDetails();
			supplierDetails.userName = supplierUserName;//"Suppl_pr12";
			supplierDetails.password = GenericNewPassword;
			supplierDetails.formTask_Suppliers = suppformreqname1;
			supplierDetails.suppliersResource = suppliersInstanceValue;

			displayName = home.getLoggedInUserDetails();

			String [] formTaskName = {suppformreqname1};

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
			//			
			home = login.performLogin(adminUsername, adminPassword);
			TestValidation.IsTrue(!home.error, 
					"LOGGED IN with Admin user " + adminUsername, 
					"Failed to login with Admin user " + adminUsername);
			//		 
			//approve one say doc upload task
			InboxPage ip = home.clickInboxMenu();

			//reject complete task

			boolean reject =  ip.actionOnTask(formTaskName, false,  true);
			TestValidation.IsTrue(reject, 
					"Task " + formTaskName + " has been REJECTED", 
					"Failed to Reject task " + formTaskName);		

			// nsvigate fsqa->task -> check change duedate
			home.clickFSQABrowserMenu();

			boolean navigate = fbp.selectResourceDropDownandNavigate("Suppliers","Tasks");
			TestValidation.IsTrue(navigate, 
					"Navigated to FSQABrowser>Task Tab", 
					"Failed to navigate to FSQABrowser>Task Tab");

			boolean applyFilter = fbp.openAndApplySettingsForColumn(COLUMNHEADER.TASKNAME, COLUMNSETTING.FILTER,suppformreqname1);
			TestValidation.IsTrue(applyFilter, 
					"Applied Filter on" + suppformreqname1, 
					"Failed to apply filter on" + suppformreqname1);

			//		//verify Task History

			DateTime dt = new DateTime(driver);
			String Date = dt.getDateTime("Day", 1, 2, 10, false, false);
			boolean changeDueDate = fbp.changeDueByOfTask(suppformreqname1, Date, null);
			TestValidation.IsTrue(changeDueDate, 
					"CHANGED due date for "+suppformreqname1+" Task to - " + Date, 
					"Failed to change due date for "+suppformreqname1+" Task to - " + Date);

			String date = dt.getDate("Day", 1);
			String time = dt.getTime(2, 10, true, true);
			HISTORY_DATA data = new HISTORY_DATA();
			data.Workgroup = wgName;
			data.Username = displayName;
			data.AssignedDueBy = " Due By:   No Due Date" ;

			data.DueBy = time+" "+"IST"+" on "+date;

			boolean verifyTaskHistory = fsqaTasksPage.verifyTaskHistory(suppformreqname1,HISTORY_ACTIONS.DUEBY, data);
			TestValidation.IsTrue(verifyTaskHistory, 
					"Verified Task History for resubmitted task " +suppformreqname1, 
					"Failed to verify Task History for resubmitted task "+suppformreqname1);
		}

		finally {

			// login with superadmin cred
			boolean logout = home.userLogout();
			TestValidation.IsTrue(logout, 
					"Log out from application", 
					"Failed to Log out from application");

		}
	}
	//	
	//	
	@Test(description = "Task Monitoring || Users should not be able to recall an approval task", dependsOnMethods = { "TestCase_16618" })
	public void TestCase_16798() throws InterruptedException {	

		try{
			supplierDetails = new SupplierPortalDetails();
			supplierDetails.userName = supplierUserName;//"Suppl_pr12";
			supplierDetails.password = GenericNewPassword;
			//suppformreqname1="SuppForm1_12.10_09.58.14";
			supplierDetails.formTask_Suppliers = suppformreqname;
			supplierDetails.suppliersResource = suppliersInstanceValue;


			home = login.performLogin(adminUsername, adminPassword);
			TestValidation.IsTrue(!home.error, 
					"LOGGED IN with Admin user " + adminUsername, 
					"Failed to login with Admin user " + adminUsername);

			// navigate fsqa->task -> check change duedate
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
	//
	@Test(description = "Task Monitoring || Verify only Task History option is available for approval tasks",	dependsOnMethods = { "TestCase_16618" })
	public void TestCase_38334() throws InterruptedException {		
		try{
			supplierDetails = new SupplierPortalDetails();
			supplierDetails.userName =supplierUserName;// "Suppl_pr12";
			supplierDetails.password = GenericNewPassword;
			//suppformreqname1="SuppForm1_12.10_09.58.14";
			supplierDetails.formTask_Suppliers = suppformreqname;
			supplierDetails.suppliersResource = suppliersInstanceValue;

			home = login.performLogin(adminUsername, adminPassword);
			TestValidation.IsTrue(!home.error, 
					"LOGGED IN with Admin user " + adminUsername, 
					"Failed to login with Admin user " + adminUsername);

			// navigate fsqa->task -> check change duedate
			home.clickFSQABrowserMenu();

			boolean navigate = fbp.selectResourceDropDownandNavigate("Suppliers","Tasks");
			TestValidation.IsTrue(navigate, 
					"Navigated to FSQABrowser>Task Tab", 
					"Failed to navigate to FSQABrowser>Task Tab");

			boolean applyFilter = fbp.openAndApplySettingsForColumn(COLUMNHEADER.TASKNAME, COLUMNSETTING.FILTER,suppformreqname);
			TestValidation.IsTrue(applyFilter, 
					"Applied Filter on" + suppformreqname, 
					"Failed to apply filter on" + suppformreqname);

			boolean TaskHistoryOptionIsVisible = fsqaTasksPage.taskHistoryOptionIsVisible(suppformreqname);
			TestValidation.IsTrue(TaskHistoryOptionIsVisible, 
					"Task History option is display " + suppformreqname, 
					"Failed, Task History option isn't displayed " +suppformreqname);
		}
		finally {

			// login with superadmin cred
			boolean logout = home.userLogout();
			TestValidation.IsTrue(logout, 
					"Log out from application", 
					"Failed to Log out from application");

		}

	}


	@Test(description = "Task Monitoring || Verify the tasks that should be recalled after disabling the item/supplier template.")
	public void TestCase_30360() throws InterruptedException {		
		try{

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
			home.clickRequirementsMenu();
			boolean enabletemp = mrp.enabledisabletemplate(supptempname,"SUPPLIERS",true);
			TestValidation.IsTrue(enabletemp, 
					"Supplier Template Enabled", 
					"Failed to enable supplier template");
			// login with superadmin cred
			boolean logout = home.userLogout();
			TestValidation.IsTrue(logout, 
					"Log out from application", 
					"Failed to Log out from application");

		}
		//		
	}

	@Test(description = "Task Monitoring || Verify the tasks that should be recalled after removing the item/supplier requirement.")
	public void TestCase_30362() throws InterruptedException {		
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

			// login with superadmin cred
			boolean logout = home.userLogout();
			TestValidation.IsTrue(logout, 
					"Log out from application", 
					"Failed to Log out from application");

		}

	}

	@Test(description = "Task Monitoring || Verify the tasks that should be recalled after "
			+ "removing the item/supplier instance from the requirement.")
	public void TestCase_30364() throws InterruptedException {		
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


	@Test(description = "Task Monitoring || Verify the tasks that should be recalled after disabling the item/supplier instance.")
	public void TestCase_30366() throws InterruptedException {		
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

	@Test(description = "Task Monitoring || Verify change due date fucntionality for supplier tasks and internal task.")
	public void TestCase_38333() throws InterruptedException {		
		try{

			supplierDetails = new SupplierPortalDetails();
			supplierDetails.userName = supplierUserName;//"Suppl_pr12";
			supplierDetails.password = GenericNewPassword;
			//suppformreqname1="SuppForm1_12.10_09.58.14";
			supplierDetails.formTask_Suppliers = itemformreqname1;
			supplierDetails.suppliersResource = itemsInstanceValue;
			//supptempname ="SupplierTemp_pr00102";
			String dueDate ="12/31/2021 00:00";

			home = login.performLogin(adminUsername, adminPassword);
			TestValidation.IsTrue(!home.error, 
					"LOGGED IN with Admin user " + adminUsername, 
					"Failed to login with Admin user " + adminUsername);

			// verify changeinDue
			home.clickFSQABrowserMenu();

			boolean navigate = fbp.selectResourceDropDownandNavigate("Items","Tasks");
			TestValidation.IsTrue(navigate, 
					"Navigated to FSQABrowser>Task Tab", 
					"Failed to navigate to FSQABrowser>Task Tab");

			boolean applyFilter = fbp.openAndApplySettingsForColumn(COLUMNHEADER.TASKNAME, COLUMNSETTING.FILTER,itemformreqname1);
			TestValidation.IsTrue(applyFilter, 
					"Applied Filter on" + itemformreqname1, 
					"Failed to apply filter on" + itemformreqname1);

			boolean changeDueDate = fsqaTasksPage.changeDueDate(itemformreqname1, dueDate);
			TestValidation.IsFalse(changeDueDate, 
					"Recall Task is not displayed "+ itemformreqname1, 
					"Failed, Recall Task is display "+ itemformreqname1);

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
