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
import com.project.safetychain.webapp.pageobjects.ResourceDesignerPage.CATEGORYTYPES;
import com.project.safetychain.webapp.pageobjects.SupplierPortalPage.SupplierPortalDetails;
import com.project.safetychain.webapp.pageobjects.UserManagerPage.UsrDetailParams;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.utilities.CommonMethods;
import com.project.utilities.ControlActions;

public class TCG_SMK_SupplierPortalFlow extends TestBase {

	ControlActions controlActions;

	//Pages
	LoginPage login;
	HomePage homePage;
	CommonPage mainPage;
	ResourceDesignerPage resourceDesigner;
	ManageResourcePage manageResource;
	ManageLocationPage locations;
	FormDesignerPage formDesignerPage;
	DocumentManagementPage documentPage;
	FSQABrowserPage fsqaBrowser;
	FSQABrowserFormsPage fsqaForms;
	UserManagerPage manageUser;
	UsrDetailParams userDetails;
	WorkGroupsPage workgroup;
	SupplierPortalPage supplierPage;
	SupplierPortalDetails supplierDetails;
	ManageRequirementPage requirements;
	InboxPage inbox;

	//Data
	public static List<String> supplierinstance;

	public static String formType;
	public static String formName;

	public static String locationCategoryValue;
	public static String locationInstanceValue;
	public static String itemCategoryValue,supplierCategoryValue;
	public static String itemResourceInstanceValue,supplierResourceInstanceValue;
	public static String supplierUserName, supplierPassword, supplierNewPassword;
	public static String workgroupName;
	public static String supplierTemplateName;
	public static String supplierFormReqName,supplierDocumentReqName,supplierACKReqName;
	public static String itemTemplateName;
	public static String itemFormReqName,itemDocumentReqName,itemACKReqName;
	public static String itemRejectFormReqName,supplierRejectDocumentReqName;
	public static String supplierSearchACK, supplierNoSearchACK;

	//Internal user portal
	public static String documentTypeName;
	public static String documentName = "upload.png";
	public static String documentPath = System.getProperty("user.dir") + "\\test-data-files\\UploadDocuments\\"+documentName;

	//SUpplier Portal
	public static String uploadDocumentName = "upload.png";
	public static String uploadDocumentPath = System.getProperty("user.dir") + "\\test-data-files\\UploadDocuments\\"+uploadDocumentName;

	@BeforeClass(alwaysRun = true)
	public void groupInit() throws InterruptedException {
		//Data initialization
		formType = "Questionnaire";
		formName = CommonMethods.dynamicText("Automation_SupplierForm");
		locationCategoryValue = CommonMethods.dynamicText("Loc_Cat");
		locationInstanceValue = CommonMethods.dynamicText("Loc_Inst");
		supplierCategoryValue = CommonMethods.dynamicText("Supplier_Cat");
		supplierResourceInstanceValue = CommonMethods.dynamicText("00Supplier_Inst1");
		itemCategoryValue = CommonMethods.dynamicText("Item_Cat");		
		itemResourceInstanceValue = CommonMethods.dynamicText("00Item_Inst1");
		documentTypeName =  CommonMethods.dynamicText("Supplier_DocType");
		workgroupName =  CommonMethods.dynamicText("Workgroup");
		supplierUserName =  CommonMethods.dynamicText("SupplierUser");
		supplierPassword = GenericPassword;
		supplierNewPassword = GenericNewPassword;

		supplierinstance = new ArrayList<String>();
		supplierinstance.add(supplierResourceInstanceValue);
		supplierTemplateName = CommonMethods.dynamicText("SuppliersTemplate");
		supplierFormReqName = CommonMethods.dynamicText("SuppliersFormTask");
		supplierDocumentReqName = CommonMethods.dynamicText("SuppliesrDocTask");
		supplierACKReqName = CommonMethods.dynamicText("SuppliersACKTask");

		itemTemplateName = CommonMethods.dynamicText("ItemsTemplate");
		itemFormReqName = CommonMethods.dynamicText("ItemsFormTask");
		itemDocumentReqName = CommonMethods.dynamicText("ItemsDocTask");
		itemACKReqName = CommonMethods.dynamicText("ItemsACKTask");

		itemRejectFormReqName =  CommonMethods.dynamicText("Items_FormRejectTask");;
		supplierRejectDocumentReqName =  CommonMethods.dynamicText("Suppliers_DocumentUploadRejectTask");
		supplierSearchACK =  CommonMethods.dynamicText("SearchACKTask");
		supplierNoSearchACK =  CommonMethods.dynamicText("ACKTask");

		userDetails = new UsrDetailParams();

		userDetails.Suppliers = supplierinstance;
		userDetails.Username = supplierUserName;
		userDetails.EmployeeId = "X12345";
		userDetails.FirstName = "Supplier";
		userDetails.LastName = "User";
		userDetails.Email = "test@test.com";
		userDetails.Password = supplierPassword;
		userDetails.Title = "Supplier Title";
		userDetails.Timezone = "U.S. Pacific";

		String supplierResourceInstances[][] = {{supplierResourceInstanceValue,"true"}};
		String itemResourceInstances[][] = {{itemResourceInstanceValue,"true"}};

		driver = launchbrowser();
		controlActions = new ControlActions(driver);
		mainPage = new CommonPage(driver);
		fsqaBrowser = new FSQABrowserPage(driver);
		fsqaForms = new FSQABrowserFormsPage(driver);
		login = new LoginPage(driver);
		resourceDesigner = new ResourceDesignerPage(driver);
		locations = new ManageLocationPage(driver);
		manageResource = new ManageResourcePage(driver);
		formDesignerPage = new FormDesignerPage(driver);
		documentPage = new DocumentManagementPage(driver);
		manageUser = new UserManagerPage(driver);
		workgroup = new WorkGroupsPage(driver);
		supplierPage = new SupplierPortalPage(driver);
		inbox = new InboxPage(driver);

		controlActions.getUrl(applicationUrl);

		//homePage = new HomePage(driver);
		homePage = login.performLogin(adminUsername, adminPassword);

		if(homePage.error) {
			TCGFailureMsg = "Could NOT login to application";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		HashMap<String,String> resourceCategories = new HashMap<String,String>();
		resourceCategories.put(CATEGORYTYPES.LOCATION, locationCategoryValue);
		resourceCategories.put(CATEGORYTYPES.ITEMS, itemCategoryValue);
		resourceCategories.put(CATEGORYTYPES.SUPPLIERS, supplierCategoryValue);

		if(!resourceDesigner.createCategory(resourceCategories)) {
			TCGFailureMsg = "Could NOT create Locations/Items/Suppliers category - ";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		if(!locations.createLocationInstanceLinkUser(locationCategoryValue,locationInstanceValue,adminUsername)) {
			TCGFailureMsg = "Could NOT create location instance - " + locationCategoryValue;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		if(!manageResource.addResourceInstances("Items", itemCategoryValue, itemResourceInstances, true, locationInstanceValue)) {
			TCGFailureMsg = "Could NOT create resource " + itemResourceInstanceValue + " & link Location - "+ locationInstanceValue;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		if(!manageResource.addResourceInstances("Suppliers", supplierCategoryValue, supplierResourceInstances,true, locationInstanceValue)) {
			TCGFailureMsg = "Could NOT create resource " + supplierResourceInstanceValue + " & link Location - "+ locationInstanceValue;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		if(!manageResource.linkItemSupplier(itemResourceInstanceValue)) {
			TCGFailureMsg = "Could NOT link supplier resource " + supplierResourceInstanceValue + " with item resource - "+ itemResourceInstanceValue;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		if(!formDesignerPage.createAndReleaseForm(formType, formName,"Suppliers", supplierCategoryValue,
				supplierResourceInstanceValue)) {
			TCGFailureMsg = "Could NOT create and release form - " + formName;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		documentPage = mainPage.clickdocumentsmenu();
		if(documentPage.error) {
			TCGFailureMsg = "Could NOT open 'Documents'";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		if(!documentPage.docUploadinDocType(documentTypeName,documentPath)) {
			TCGFailureMsg = "Could NOT add document type";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		if(!workgroup.createWorkGroup(workgroupName, adminUsername)) {
			TCGFailureMsg = "Could NOT create workgroup";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		requirements = homePage.clickRequirementsMenu();
		if(requirements.error) {
			TCGFailureMsg = "Could NOT navigate to 'Requirements'";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		boolean createSupplierRequirement = requirements.createSupplierRequirement(supplierTemplateName, documentTypeName, supplierACKReqName, workgroupName, supplierDocumentReqName,formName, supplierFormReqName, supplierResourceInstanceValue);
		if(!createSupplierRequirement) {
			TCGFailureMsg = "Could NOT create Suppliers requirement";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		boolean addDocumentUploadRequirement = requirements.addDocUploadRequirement(documentTypeName, supplierRejectDocumentReqName, workgroupName);
		if(!addDocumentUploadRequirement) {
			TCGFailureMsg = "Could NOT add 'Document Upload' requirement";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		boolean addACKNoSearchRequirement = requirements.addAcknowledgmentRequirement(documentTypeName, supplierNoSearchACK, workgroupName);
		if(!addACKNoSearchRequirement) {
			TCGFailureMsg = "Could NOT add 'Acknowledgement' requirement";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		boolean addACKSearchRequirement = requirements.addAcknowledgmentRequirement(documentTypeName, supplierSearchACK, workgroupName);
		if(!addACKSearchRequirement) {
			TCGFailureMsg = "Could NOT add 'Acknowledgement' requirement";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}


		boolean createItemRequirement = requirements.createItemRequirement(itemTemplateName, documentTypeName, itemACKReqName, workgroupName, itemDocumentReqName, formName, itemFormReqName, itemResourceInstanceValue);
		if(!createItemRequirement) {
			TCGFailureMsg = "Could NOT create Items requirement";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		boolean addCompleteFormRequirement = requirements.addCompleteFormRequirement(formName, itemRejectFormReqName, workgroupName);
		if(!addCompleteFormRequirement) {
			TCGFailureMsg = "Could NOT add 'Complete Form' requirement";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		if(!manageUser.supplierUserCreation(userDetails)) {
			TCGFailureMsg = "Could NOT create supplier user";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		if(!homePage.userLogout()){
			TCGFailureMsg = "Could NOT logout from application";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		if(!(login.performLogin(supplierUserName, supplierPassword, supplierNewPassword))) {
			TCGFailureMsg = "Could NOT login to application";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		if(!homePage.userLogout()){
			TCGFailureMsg = "Could NOT logout from application";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

	}

	@Test(description="'Complete Form' task for 'Items' & 'Supplier'")	
	public void TestCase_8340(){
		try {

			supplierDetails = new SupplierPortalDetails();
			supplierDetails.userName = supplierUserName;
			supplierDetails.password = supplierNewPassword;
			supplierDetails.itemsResource = itemResourceInstanceValue;
			supplierDetails.formTask_Items = itemFormReqName;
			supplierDetails.suppliersResource = supplierResourceInstanceValue;
			supplierDetails.formTask_Suppliers = supplierFormReqName;

			homePage = login.performLogin(supplierDetails.userName, supplierDetails.password);
			TestValidation.IsTrue(!homePage.error, 
					"logged in with supplier user", 
					"Could not login with supplier user");

			boolean openSuppliersCompleteFormTask = supplierPage.openCompleteForm(formName, supplierDetails.formTask_Suppliers, supplierDetails.suppliersResource);
			TestValidation.IsTrue(openSuppliersCompleteFormTask, 
					"OPENED 'Supplier' complete form task", 
					"Could NOT open 'Suppliers' complete form task");

			boolean submitSuppliersTask = fsqaForms.submitData(false, false, false, true,false);
			TestValidation.IsTrue(submitSuppliersTask, 
					"Submitted task", 
					"Could NOT submit task");

			boolean openItemsCompleteFormTask = supplierPage.openCompleteForm(formName, supplierDetails.formTask_Items, supplierDetails.itemsResource);
			TestValidation.IsTrue(openItemsCompleteFormTask, 
					"OPENED 'Items' complete form task", 
					"Could NOT open 'Items' complete form task");

			boolean submitItemsTask = fsqaForms.submitData(false, false, false, true,false);
			TestValidation.IsTrue(submitItemsTask, 
					"Submitted task", 
					"Could NOT submit task");

		}
		finally {
			if(!homePage.userLogout()){
				TCGFailureMsg = "Could NOT logout from application";
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}
		}

	}

	@Test(description="'Document Upload' task for 'Items' & 'Supplier'")	
	public void TestCase_8595(){
		try {

			supplierDetails = new SupplierPortalDetails();
			supplierDetails.userName = supplierUserName;
			supplierDetails.password = supplierNewPassword;
			supplierDetails.itemsResource = itemResourceInstanceValue;
			supplierDetails.documentTask_Items = itemDocumentReqName;
			supplierDetails.suppliersResource = supplierResourceInstanceValue;
			supplierDetails.documentTask_Suppliers = supplierDocumentReqName;

			homePage = login.performLogin(supplierDetails.userName, supplierDetails.password);
			TestValidation.IsTrue(!homePage.error, 
					"logged in with supplier user", 
					"Could not login with supplier user");

			boolean openSuppliersDocumentUploadTask = supplierPage.openDocumentUpload(documentTypeName, supplierDetails.documentTask_Suppliers, supplierDetails.suppliersResource);
			TestValidation.IsTrue(openSuppliersDocumentUploadTask, 
					"OPENED 'Supplier' document upload task", 
					"Could NOT open 'Suppliers' document upload task");

			boolean submitSuppliersTask = supplierPage.uploadDocument(uploadDocumentPath, true);
			TestValidation.IsTrue(submitSuppliersTask, 
					"Submitted task", 
					"Could NOT submit task");

			boolean openItemsdocumentUploadTask = supplierPage.openDocumentUpload(documentTypeName, supplierDetails.documentTask_Items, supplierDetails.itemsResource);
			TestValidation.IsTrue(openItemsdocumentUploadTask, 
					"OPENED 'Items' document upload ask", 
					"Could NOT open 'Items' document upload task");

			boolean submitItemsTask = supplierPage.uploadDocument(uploadDocumentPath, true);
			TestValidation.IsTrue(submitItemsTask, 
					"Submitted task", 
					"Could NOT submit task");

		}
		finally {
			if(!homePage.userLogout()){
				TCGFailureMsg = "Could NOT logout from application";
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}
		}
	}

	@Test(description="'Acknowlegement' task for 'Items' & 'Supplier'")	
	public void TestCase_35268(){

		try {
			supplierDetails = new SupplierPortalDetails();
			supplierDetails.userName = supplierUserName;
			supplierDetails.password = supplierNewPassword;
			supplierDetails.itemsResource = itemResourceInstanceValue;
			supplierDetails.acknowledegmentTask_Items = itemACKReqName;
			supplierDetails.suppliersResource = supplierResourceInstanceValue;
			supplierDetails.acknowledegmentTask_Suppliers = supplierACKReqName;

			homePage = login.performLogin(supplierDetails.userName, supplierDetails.password);
			TestValidation.IsTrue(!homePage.error, 
					"logged in with supplier user", 
					"Could not login with supplier user");

			boolean openSuppliersAcknowlegementTask = supplierPage.openAcknowledgement(documentTypeName, supplierDetails.acknowledegmentTask_Suppliers, supplierDetails.suppliersResource);
			TestValidation.IsTrue(openSuppliersAcknowlegementTask, 
					"OPENED 'Supplier' Acknowlegement task", 
					"Could NOT open 'Suppliers' Acknowlegement task");

			boolean submitSuppliersTask = supplierPage.signAcknowledgement();
			TestValidation.IsTrue(submitSuppliersTask, 
					"Submitted task", 
					"Could NOT submit task");

			boolean openItemsAcknowlegementTask = supplierPage.openAcknowledgement(documentTypeName, supplierDetails.acknowledegmentTask_Items, supplierDetails.itemsResource);
			TestValidation.IsTrue(openItemsAcknowlegementTask, 
					"OPENED 'Items' Acknowlegement Task", 
					"Could NOT open 'Items' Acknowlegement task");

			boolean submitItemsTask = supplierPage.signAcknowledgement();
			TestValidation.IsTrue(submitItemsTask, 
					"Submitted task", 
					"Could NOT submit task");
		}
		finally {
			if(!homePage.userLogout()){
				TCGFailureMsg = "Could NOT logout from application";
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}
		}
	}

	@Test(description="Verify rejected task")	
	public void TestCase_10088(){
		try {

			supplierDetails = new SupplierPortalDetails();
			supplierDetails.userName = supplierUserName;
			supplierDetails.password = supplierNewPassword;
			supplierDetails.formTask_Items = itemRejectFormReqName;
			supplierDetails.itemsResource  = itemResourceInstanceValue;

			homePage = login.performLogin(supplierDetails.userName, supplierDetails.password);
			TestValidation.IsTrue(!homePage.error, 
					"logged in with supplier user", 
					"Could not login with supplier user");

			boolean openItemsCompleteFormTask = supplierPage.openCompleteForm(formName, supplierDetails.formTask_Items, supplierDetails.itemsResource);
			TestValidation.IsTrue(openItemsCompleteFormTask, 
					"OPENED 'Items' complete form task", 
					"Could NOT open 'Items' complete form task");

			boolean submitItemsTask = fsqaForms.submitData(false, false, false, true,false);
			TestValidation.IsTrue(submitItemsTask, 
					"Submitted task", 
					"Could NOT submit task");

			boolean logout = homePage.userLogout();
			TestValidation.IsTrue(logout, 
					"Logout from application", 
					"Could NOT logout from application");

			homePage = login.performLogin(adminUsername, adminPassword);
			TestValidation.IsTrue(!homePage.error, 
					"logged in with internal user", 
					"Could not login with internal user");

			inbox = mainPage.clickInboxMenu();
			TestValidation.IsTrue(!inbox.error, 
					"Navigated to Inbox", 
					"Could not navigate to Inbox");

			boolean rejectTask = inbox.rejectTask(supplierDetails.formTask_Items);
			TestValidation.IsTrue(rejectTask, 
					"Task is rejected", 
					"Could NOT reject the task");

			logout = homePage.userLogout();
			TestValidation.IsTrue(logout, 
					"Logout from application", 
					"Could NOT logout from application");

			homePage = login.performLogin(supplierDetails.userName, supplierDetails.password);
			TestValidation.IsTrue(!homePage.error, 
					"logged in with supplier user", 
					"Could not login with supplier user");

			boolean verifyRejectedTask = supplierPage.verifyRejectedTask(supplierDetails.formTask_Items,false);
			TestValidation.IsTrue(verifyRejectedTask, 
					"Verified 'Rejected Task'", 
					"Could NOT verify 'Rejected Task'");

			boolean openTask = supplierPage.openTask(supplierDetails.formTask_Items, "Complete Form");
			TestValidation.IsTrue(openTask, 
					"Task is opened", 
					"Could NOT open the task");

			boolean cancelTask = supplierPage.cancelTask(supplierPage.FormCancelBtn);
			TestValidation.IsTrue(cancelTask, 
					"Task is cancelled", 
					"Could NOT cancel the task");

			boolean verifyOpenedRejectedTask = supplierPage.verifyRejectedTask(supplierDetails.formTask_Items,true);
			TestValidation.IsTrue(verifyOpenedRejectedTask, 
					"Verified opened'Rejected Task'", 
					"Could NOT verify opened 'Rejected Task'");

			openItemsCompleteFormTask = supplierPage.openCompleteForm(formName, supplierDetails.formTask_Items, supplierDetails.itemsResource);
			TestValidation.IsTrue(openItemsCompleteFormTask, 
					"OPENED 'Items' complete form task", 
					"Could NOT open 'Items' complete form task");

			submitItemsTask = fsqaForms.submitData(false, false, false, true,false);
			TestValidation.IsTrue(submitItemsTask, 
					"Submitted task", 
					"Could NOT submit task");

		}
		finally {
			if(!homePage.userLogout()){
				TCGFailureMsg = "Could NOT logout from application";
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}
		}
	}


	@Test(description="Verify 'Upload Document' rejected task")	
	public void TestCase_10253(){

		try {

			supplierDetails = new SupplierPortalDetails();
			supplierDetails.userName = supplierUserName;
			supplierDetails.password = supplierNewPassword;
			supplierDetails.suppliersResource = supplierResourceInstanceValue;
			supplierDetails.documentTask_Suppliers = supplierRejectDocumentReqName;

			homePage = login.performLogin(supplierDetails.userName, supplierDetails.password);
			TestValidation.IsTrue(!homePage.error, 
					"logged in with supplier user", 
					"Could not login with supplier user");

			boolean submitDocumentUploadTask = supplierPage.submitDocumentUploadTask(documentTypeName, supplierDetails.documentTask_Suppliers, supplierDetails.suppliersResource,uploadDocumentPath, true);
			TestValidation.IsTrue(submitDocumentUploadTask, 
					"Submitted Task'", 
					"Could NOT submit task");

			boolean logout = homePage.userLogout();
			TestValidation.IsTrue(logout, 
					"Logout from application", 
					"Could NOT logout from application");

			homePage = login.performLogin(adminUsername, adminPassword);
			TestValidation.IsTrue(!homePage.error, 
					"logged in with internal user", 
					"Could not login with internal user");

			inbox = mainPage.clickInboxMenu();
			TestValidation.IsTrue(!inbox.error, 
					"Navigated to Inbox", 
					"Could not navigate to Inbox");

			boolean rejectTask = inbox.rejectTask(supplierDetails.documentTask_Suppliers);
			TestValidation.IsTrue(rejectTask, 
					"Task is rejected", 
					"Could NOT reject the task");

			logout = homePage.userLogout();
			TestValidation.IsTrue(logout, 
					"Logout from application", 
					"Could NOT logout from application");

			homePage = login.performLogin(supplierDetails.userName, supplierDetails.password);
			TestValidation.IsTrue(!homePage.error, 
					"logged in with supplier user", 
					"Could not login with supplier user");

			boolean verifyRejectedTask = supplierPage.verifyRejectedTask(supplierDetails.documentTask_Suppliers,false);
			TestValidation.IsTrue(verifyRejectedTask, 
					"Verified 'Rejected Task'", 
					"Could NOT verify 'Rejected Task'");

			boolean openTask = supplierPage.openTask(supplierDetails.documentTask_Suppliers, "Document Upload");
			TestValidation.IsTrue(openTask, 
					"Task is opened", 
					"Could NOT open the task");

			controlActions.clickElement(supplierPage.DocumentSubmitBtn);
			logInfo("Clicked on 'SUBMIT' without selecting a file");

			boolean verifyOpenedRejectedTask = supplierPage.verifyNoFileSelected();
			TestValidation.IsTrue(verifyOpenedRejectedTask, 
					"Verified opened'Rejected Task'", 
					"Could NOT verify opened 'Rejected Task'");

			boolean submitRejectedDocumentUploadTask = supplierPage.submitDocumentUploadTask(documentTypeName, supplierDetails.documentTask_Suppliers, supplierDetails.suppliersResource,uploadDocumentPath, false);
			TestValidation.IsTrue(submitRejectedDocumentUploadTask, 
					"Submitted Rejected Task'", 
					"Could NOT submit rejected task");

		}

		finally {
			if(!homePage.userLogout()){
				TCGFailureMsg = "Could NOT logout from application";
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}
		}
	}

	@Test(description="Verify search task feature")	
	public void TestCase_8257(){
		try {

			supplierDetails = new SupplierPortalDetails();
			supplierDetails.userName = supplierUserName;
			supplierDetails.password = supplierNewPassword;
			supplierDetails.acknowledegmentTask_Suppliers = supplierSearchACK;

			homePage = login.performLogin(supplierDetails.userName, supplierDetails.password);
			TestValidation.IsTrue(!homePage.error, 
					"logged in with supplier user", 
					"Could not login with supplier user");

			boolean verifySearchedTask = supplierPage.verifySearchedTask(supplierDetails.acknowledegmentTask_Suppliers);
			TestValidation.IsTrue(verifySearchedTask, 
					"Verified searched task", 
					"Could NOT verify search task");

			boolean verifyClearedSearchedTask = supplierPage.verifyClearedTask(supplierDetails.acknowledegmentTask_Suppliers);
			TestValidation.IsTrue(verifyClearedSearchedTask, 
					"Verified cleared searched task", 
					"Could NOT verify cleared search task");
		}

		finally {
			if(!homePage.userLogout()){
				TCGFailureMsg = "Could NOT logout from application";
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}
		}

	}

	@Test(description="Verify supplier user login")	
	public void TestCase_24597() {
		try {
			supplierDetails = new SupplierPortalDetails();
			supplierDetails.userName = supplierUserName;
			supplierDetails.password = supplierNewPassword;

			homePage = login.performLogin(supplierDetails.userName, supplierDetails.password);
			TestValidation.IsTrue(!homePage.error, 
					"logged in with supplier user", 
					"Could not login with supplier user");

			boolean verifySupplierPortalLbl = controlActions.isElementDisplayedOnPage(supplierPage.SupplierPortalLbl);
			TestValidation.IsTrue(verifySupplierPortalLbl, 
					"Verified Suupplier portal is visible", 
					"Could not verify supplier portal label");

		}

		finally {
			if(!homePage.userLogout()){
				TCGFailureMsg = "Could NOT logout from application";
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
