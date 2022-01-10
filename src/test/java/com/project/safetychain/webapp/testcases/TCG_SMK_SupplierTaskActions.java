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
import com.project.safetychain.webapp.pageobjects.UserManagerPage.UsrDetailParams;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.utilities.CommonMethods;
import com.project.utilities.ControlActions;

public class TCG_SMK_SupplierTaskActions extends TestBase{

	ControlActions controlActions;
	CommonPage mainPage;	
	FSQABrowserFormsPage fbForms;
	ManageResourcePage manageResource;
	ManageLocationPage locations;
	FormDesignerPage formDesignerPage;
	ResourceDesignerPage resourceDesigner;
	UserManagerPage usermanager;
	UsrDetailParams userDetails;
	LoginPage lp;
	HomePage hp;
	SupplierPortalPage supplierPage;
	FSQABrowserPage fsqaBrowser;
	InboxPage inbox;
	//Supplier User// 
	public static String SupplierUserName;
	public static List<String> supplierinstance;
	public static String supplierPassword;
	public static String supplierNewPassword;
	//resource and form creation//	
	public static String locationCategoryValue;
	public static String itemcategory;
	public static String iteminstance;
	public static String suppliercategory;
	public static String suppinstance;
	public static String locationInstanceValue;
	public static String formType;
	public static String formName;
	//Work group//
	public static String workGroupName;
	//document upload// 
	public static String image;
	public static String document;
	public static String doctype;
	//requirement type creation//
	public static String suppdocreqname;
	public static String suppdocreqname1;
	public static String suppformreqname;
	public static String suppformreqname1;
	public static String itemdocreqname;
	public static String itemdocreqname1;
	public static String itemformreqname;
	public static String itemformreqname1;
	public static String supptempname;
	public static String itemtempname;
	public static String ackreqname;

	@BeforeClass(alwaysRun = true)
	public void groupInit() throws InterruptedException {

		locationCategoryValue = CommonMethods.dynamicText("Loc_Cat");
		locationInstanceValue = CommonMethods.dynamicText("Loc_Inst");
		itemcategory = CommonMethods.dynamicText("ItemCat") ;
		iteminstance = CommonMethods.dynamicText("0000ItemInst");
		suppliercategory = CommonMethods.dynamicText("SuppCat");
		suppinstance = CommonMethods.dynamicText("0000SuppInst");
		formType = "Questionnaire";
		formName = CommonMethods.dynamicText("Automation_QuestForm");	
		
		workGroupName = CommonMethods.dynamicText("WG");
		
		doctype = CommonMethods.dynamicText("Doc");
		image = "upload.png";
		document = System.getProperty("user.dir") + "\\test-data-files\\UploadDocuments\\"+image;
		
		supplierPassword = GenericPassword;
		supplierNewPassword = GenericNewPassword;
		supplierinstance = new ArrayList<String>();
		supplierinstance.add(suppinstance);
		
		supptempname = CommonMethods.dynamicText("Supp Temp"); 
		itemtempname = CommonMethods.dynamicText("Item Temp"); 
		suppdocreqname = CommonMethods.dynamicText("Supp Doc"); 
		suppdocreqname1 = CommonMethods.dynamicText("Supp Doc1"); 
		suppformreqname = CommonMethods.dynamicText("Supp Form"); 
		suppformreqname1 = CommonMethods.dynamicText("Supp Form1"); 
		itemdocreqname = CommonMethods.dynamicText("Item Doc"); 
		itemdocreqname1 = CommonMethods.dynamicText("Item Doc1"); 
		itemformreqname = CommonMethods.dynamicText("Item Form");
		itemformreqname1 = CommonMethods.dynamicText("Item Form1");				
		SupplierUserName= CommonMethods.dynamicText("SuppUser");

		
		String supplierInstances[][] = {{suppinstance,"true"}};
		String itemInstances[][] = {{iteminstance,"true"}};
		
		driver = launchbrowser();
		controlActions = new ControlActions(driver);
		controlActions.getUrl(applicationUrl);
		fbForms = new FSQABrowserFormsPage(driver);
		resourceDesigner = new ResourceDesignerPage(driver);
		locations = new ManageLocationPage(driver);
		manageResource = new ManageResourcePage(driver);
		formDesignerPage = new FormDesignerPage(driver);
		usermanager = new UserManagerPage(driver);
		userDetails = new UsrDetailParams();
		lp = new LoginPage(driver);
		supplierPage = new SupplierPortalPage(driver);
		inbox = new InboxPage(driver);
		hp = new HomePage(driver);

		
		LoginPage lp = new LoginPage(driver);
		hp = lp.performLogin(adminUsername, adminPassword);
		if(hp.error) {
			TCGFailureMsg = "Could not login to application with internal user";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}	
		
		HashMap<String,String> resourceCategories = new HashMap<String,String>();
		resourceCategories.put(CATEGORYTYPES.LOCATION, locationCategoryValue);
		resourceCategories.put(CATEGORYTYPES.ITEMS, itemcategory);
		resourceCategories.put(CATEGORYTYPES.SUPPLIERS, suppliercategory);

		if(!resourceDesigner.createCategory(resourceCategories)) {
			TCGFailureMsg = "Could not create Locations/Items/Suppliers category - ";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		if(!locations.createLocationInstanceLinkUser(locationCategoryValue,locationInstanceValue,adminUsername)) {
			TCGFailureMsg = "Could not create location instance - " + locationCategoryValue;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		if(!manageResource.addResourceInstances("Items", itemcategory, itemInstances, true, locationInstanceValue)) {
			TCGFailureMsg = "Could not create resource " + iteminstance + " & link Location - "+ locationInstanceValue;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		if(!manageResource.addResourceInstances("Suppliers", suppliercategory, supplierInstances,true, locationInstanceValue)) {
			TCGFailureMsg = "Could not create resource " + suppinstance + " & link Location - "+ locationInstanceValue;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		if(!manageResource.linkItemSupplier(iteminstance)) {
			TCGFailureMsg = "Could not link supplier resource " + suppinstance + " with item resource - "+ iteminstance;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		if(!formDesignerPage.createAndReleaseForm(formType, formName,"Suppliers", suppliercategory,
				suppinstance)) {
			TCGFailureMsg = "Could not create and release form - " + formName;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		WorkGroupsPage wgp = hp.clickWorkGroupsMenu();
		if(!wgp.createWorkGroup(workGroupName, adminUsername)) {
			TCGFailureMsg = "Could not create workgroup - " + workGroupName;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		DocumentManagementPage dmp = hp.clickdocumentsmenu();
		if(!dmp.docUploadinDocType (doctype,document)) {
			TCGFailureMsg = "Could not upload document in doctype- " + doctype;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		ManageRequirementPage mrp = hp.clickRequirementsMenu();	
		if(!mrp.createSuppReq(supptempname, doctype, workGroupName, suppdocreqname, suppdocreqname1, formName, 
				suppformreqname, suppformreqname1, suppinstance)) {
			TCGFailureMsg = "Could not create requirement for supplier " + suppinstance;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		if(!mrp.createitemreq(itemtempname, doctype, workGroupName, itemdocreqname, itemdocreqname1, formName, 
				itemformreqname, itemformreqname1, iteminstance)) {
			TCGFailureMsg = "Could not create requirement for item " + iteminstance;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
		
		userDetails.Suppliers = supplierinstance;
		userDetails.Username = SupplierUserName;
		userDetails.EmployeeId = "X12345";
		userDetails.FirstName = "Supplier";
		userDetails.LastName = "User";
		userDetails.Email = "test@test.com";
		userDetails.Password = supplierPassword;
		userDetails.Title = "Supplier Title";
		userDetails.Timezone = "U.S. Pacific";

		if(!usermanager.supplierUserCreation(userDetails)) {
			TCGFailureMsg = "Could not create supplier user";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		if(!hp.userLogout()) {
			TCGFailureMsg = "Could not log out from internal user";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		if(!lp.performLogin(SupplierUserName,supplierPassword,supplierNewPassword)) {
			TCGFailureMsg = "Could not log in from supplier user   "+ SupplierUserName;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		String [] formtaskname = {suppformreqname,suppformreqname1,itemformreqname,itemformreqname1};
		String [] doctaskname = {suppdocreqname,suppdocreqname1,itemdocreqname,itemdocreqname1};
		
		if(!supplierPage.submitformTask(formtaskname)) {
			TCGFailureMsg = "Could not submit form task  ";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}		

		if(!supplierPage.uploadDocTask(doctaskname,document)) {
			TCGFailureMsg = "Could not upload document task  ";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
		
		if(!hp.userLogout()) {
			TCGFailureMsg = "Could not log out from supplier user";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
		
		lp.performLogin(adminUsername, adminPassword);
		if(hp.error) {
			TCGFailureMsg = "Could NOT login to application";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

	}

	@Test(description="ACCEPT/REJECT form and document task",priority=1)
	public void TestCase_35346() throws Exception{

		hp.clickInboxMenu();

		String[] accepttaskname = {suppformreqname,suppdocreqname,itemformreqname,itemdocreqname};
		boolean accept = inbox.actionOnTask(accepttaskname, true, false);
		TestValidation.IsTrue(accept, 
				"Task accepted", 
				"Failed to accept task");

		String[] rejecttaskname = {suppformreqname1,suppdocreqname1,itemformreqname1,itemdocreqname1};
		boolean reject =  inbox.actionOnTask(rejecttaskname, false, true);
		TestValidation.IsTrue(reject, 
				"Task rejected", 
				"Failed to reject task");

	}

	@AfterClass(alwaysRun = true)
	public void closeBrowser() throws InterruptedException {
		driver.close();
	}

}
