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
import com.project.safetychain.webapp.pageobjects.LoginPage;
import com.project.safetychain.webapp.pageobjects.ManageLocationPage;
import com.project.safetychain.webapp.pageobjects.ManageRequirementPage;
import com.project.safetychain.webapp.pageobjects.ManageResourcePage;
import com.project.safetychain.webapp.pageobjects.ResourceDesignerPage;
import com.project.safetychain.webapp.pageobjects.UserManagerPage;
import com.project.safetychain.webapp.pageobjects.WorkGroupsPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.COLUMNHEADER;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.COLUMNSETTING;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.FSQATAB;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.TASKDETAILS;
import com.project.safetychain.webapp.pageobjects.ResourceDesignerPage.CATEGORYTYPES;
import com.project.safetychain.webapp.pageobjects.UserManagerPage.USERFIELDS;
import com.project.safetychain.webapp.pageobjects.UserManagerPage.UsrDetailParams;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.utilities.CommonMethods;
import com.project.utilities.ControlActions;

public class TCG_SMK_SupplierUserandRequirementCreation extends TestBase {

	ControlActions controlActions;
	FSQABrowserPage fbp;
	CommonPage mainPage;
	FSQABrowserFormsPage fbForms;
	ManageResourcePage manageResource;
	ManageLocationPage locations;
	FormDesignerPage formDesignerPage;
	ResourceDesignerPage resourceDesigner;
	ManageRequirementPage mrp;
	WorkGroupsPage wgp;
	UserManagerPage ump;
	HomePage hp;
	TASKDETAILS tsd;
	//Supplier User// 
	public static String SupplierUserName;
	public static List<String> supplierinstance;
	public static List<String> suppadmin;
	//resource and form creation//	
	public static String locationCategoryValue;
	public static String itemcategory;
	public static String iteminstance;
	public static String suppliercategory;
	public static String suppinstance;
	public static String locationInstanceValue;
	public static String formType;
	public static String formName;
	public static String formtaskname;
	//Work group//
	public static String workGroupName;
	//document upload// 
	public static String image;
	public static String document;
	public static String doctype;
	//requirement type creation//
	public static String ackreqname;
	public static String docreqname;
	public static String formsupp;
	public static String formitem;
	public static String formreqname;

	
	@BeforeClass(alwaysRun = true)
	public void groupInit() throws InterruptedException {

		SupplierUserName= CommonMethods.dynamicText("SuppUser");
		suppadmin = new ArrayList<String>();
		supplierinstance = new ArrayList<String>();
		locationCategoryValue = CommonMethods.dynamicText("Loc_Cat");
		locationInstanceValue = CommonMethods.dynamicText("Loc_Inst");
		itemcategory = CommonMethods.dynamicText("ItemCat") ;
		iteminstance = CommonMethods.dynamicText("000ItemInst");
		suppliercategory = CommonMethods.dynamicText("SuppCat");
		suppinstance = CommonMethods.dynamicText("000SuppInst");
		formType = "Questionnaire";
		formName = CommonMethods.dynamicText("Automation_QuestForm");	
		workGroupName = CommonMethods.dynamicText("WG");
		tsd = new TASKDETAILS();

		doctype = CommonMethods.dynamicText("Doc");
		image = "upload.png";
		document = System.getProperty("user.dir") + "\\test-data-files\\UploadDocuments\\"+image;

		ackreqname = CommonMethods.dynamicText("Ack");		
		docreqname = CommonMethods.dynamicText("DocUpload");
		formreqname = CommonMethods.dynamicText("Form");
		formsupp = CommonMethods.dynamicText("FormSupp");
		formitem = CommonMethods.dynamicText("FormItem");
		formtaskname = CommonMethods.dynamicText("FormTask");

		driver = launchbrowser();
		controlActions = new ControlActions(driver);
		controlActions.getUrl(applicationUrl);
		fbp = new FSQABrowserPage(driver);
		fbForms = new FSQABrowserFormsPage(driver);
		resourceDesigner = new ResourceDesignerPage(driver);
		locations = new ManageLocationPage(driver);
		manageResource = new ManageResourcePage(driver);
		formDesignerPage = new FormDesignerPage(driver);
		mrp = new ManageRequirementPage(driver);
		wgp = new WorkGroupsPage(driver);
		ump = new UserManagerPage(driver);
		hp = new HomePage(driver);
		LoginPage lp = new LoginPage(driver);

		String supplierInstances[][] = {{suppinstance,"true"}};
		String itemInstances[][] = {{iteminstance,"true"}};

		HashMap<String,String> resourceCategories = new HashMap<String,String>();
		resourceCategories.put(CATEGORYTYPES.LOCATION, locationCategoryValue);
		resourceCategories.put(CATEGORYTYPES.ITEMS, itemcategory);
		resourceCategories.put(CATEGORYTYPES.SUPPLIERS, suppliercategory);


		hp = lp.performLogin(adminUsername, adminPassword);
		if(hp.error) {
			TCGFailureMsg = "Could NOT login to application";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}	

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

		if(!manageResource.addResourceInstances("Items", itemcategory, itemInstances, true, locationInstanceValue)) {
			TCGFailureMsg = "Could NOT create resource " + iteminstance + " & link Location - "+ locationInstanceValue;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		if(!manageResource.addResourceInstances("Suppliers", suppliercategory, supplierInstances,true, locationInstanceValue)) {
			TCGFailureMsg = "Could NOT create resource " + suppinstance + " & link Location - "+ locationInstanceValue;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		if(!manageResource.linkItemSupplier(iteminstance)) {
			TCGFailureMsg = "Could NOT link supplier resource " + suppinstance + " with item resource - "+ iteminstance;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		if(!formDesignerPage.createAndReleaseForm(formType, formName,"Suppliers", suppliercategory,
				suppinstance)) {
			TCGFailureMsg = "Could NOT create and release form - " + formName;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

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
	}

	@Test(description="Creation of Supplier User")	
	public void TestCase_32557() throws Exception{

		hp.clickFSQABrowserMenu();
		hp.clickUsersMenu();
		TestValidation.Equals(ump.error, 
				false, 
				"CLICKED on Users menu", 
				"Could NOT click on Users menu"); 

		boolean clicksuppliertab = ump.clickSupplierTab();
		TestValidation.IsTrue(clicksuppliertab, 
				"CLICKED on Supplier Tab", 
				"Could NOT click on Supplier Tab");

		UsrDetailParams udp = new UsrDetailParams();
		supplierinstance.add(suppinstance);
		udp.Suppliers = supplierinstance;
		udp.Username = SupplierUserName;
		udp.EmployeeId = "X12345";
		udp.FirstName = "Supplier";
		udp.LastName = "User";
		udp.Email = "abc@test.com";
		udp.Password = GenericPassword;
		udp.Title = "Supplier Title";
		udp.Timezone = "U.S. Eastern";
		boolean createsuppuser = ump.createsupplieruser(udp);
		TestValidation.IsTrue(createsuppuser,
				"CREATED supplier user", 
				"Could NOT create supplier user");

		boolean userExists = ump.searchAndVerifyWithSuppUsrDetails(USERFIELDS.USERNAME, SupplierUserName);
		TestValidation.IsTrue(userExists, 
				"VERIFIED User " + SupplierUserName + " exists", 
				"Could NOT verify if User " + SupplierUserName + " exists");
	}

	@Test(description="Creation of Requirements for Supplier")	
	public void TestCase_35283() throws Exception{

		String supptempname = CommonMethods.dynamicText("Supp"); 
		hp.clickFSQABrowserMenu();
		hp.clickRequirementsMenu();
		boolean addtemp = mrp.addSupplierTemplate(supptempname);
		TestValidation.IsTrue(addtemp, 
				"Supplier Template Created", 
				"Failed to create supplier template");

		boolean ackreq = mrp.addAcknowledgmentRequirement(doctype,ackreqname,workGroupName);
		TestValidation.IsTrue(ackreq, 
				"Acknowledgement Requirement Created", 
				"Failed to create Acknowledgement Requirement");

		boolean docreq = mrp.addDocUploadRequirement(doctype,docreqname,workGroupName);
		TestValidation.IsTrue(docreq, 
				"Document Upload Requirement Created", 
				"Failed to create Document Upload Requirement");

		boolean formreq = mrp.addCompleteFormRequirement(formName,formreqname,workGroupName);
		TestValidation.IsTrue(formreq, 
				"Complete Form Requirement Created", 
				"Failed to create Complete Form Requirement");

		boolean addsupp = mrp.addInstancetoRequirement("Suppliers",suppinstance);
		TestValidation.IsTrue(addsupp, 
				"Supplier Instance Added", 
				"Failed to add Supplier Instance");

	}

	@Test(description="Creation of Requirements for Item")	
	public void TestCase_35284() throws Exception{

		String itemtempname = CommonMethods.dynamicText("Item"); 
		hp.clickFSQABrowserMenu();
		hp.clickRequirementsMenu();
		boolean addtemp = mrp.addItemTemplate(itemtempname);
		TestValidation.IsTrue(addtemp, 
				"Item Template Created", 
				"Failed to create Item template");

		boolean ackreq = mrp.addAcknowledgmentRequirement(doctype,ackreqname,workGroupName);
		TestValidation.IsTrue(ackreq, 
				"Acknowledgement Requirement Created", 
				"Failed to create Acknowledgement Requirement");

		boolean docreq = mrp.addDocUploadRequirement(doctype,docreqname,workGroupName);
		TestValidation.IsTrue(docreq, 
				"Document Upload Requirement Created", 
				"Failed to create Document Upload Requirement");

		boolean formreq = mrp.addCompleteFormRequirement(formName,formreqname,workGroupName);
		TestValidation.IsTrue(formreq, 
				"Complete Form Requirement Created", 
				"Failed to create Complete Form Requirement");

		boolean addsupp = mrp.addInstancetoRequirement("Items",iteminstance);
		TestValidation.IsTrue(addsupp, 
				"Item Instance Added", 
				"Failed to add Item Instance");

	}

	@Test(description="Recall Task of Supplier and Internal User")	
	public void TestCase_16288() throws Exception{

		String suppliertemplate = CommonMethods.dynamicText("Suppliers"); 
		String itemtemplate = CommonMethods.dynamicText("Items"); 
		hp.clickFSQABrowserMenu();
		hp.clickRequirementsMenu();
		boolean addtemp = mrp.addItemTemplate(itemtemplate);
		TestValidation.IsTrue(addtemp, 
				"Item Template Created", 
				"Failed to create Item template");

		boolean formreq = mrp.addCompleteFormRequirement(formName,formitem,workGroupName);
		TestValidation.IsTrue(formreq, 
				"Complete Form Requirement Created", 
				"Failed to create Complete Form Requirement");

		boolean additem = mrp.addInstancetoRequirement("Items",iteminstance);
		TestValidation.IsTrue(additem, 
				"Item Instance Added", 
				"Failed to add Item Instance");

		boolean addsupptemp = mrp.addSupplierTemplate(suppliertemplate);
		TestValidation.IsTrue(addsupptemp, 
				"Supplier Template Created", 
				"Failed to create supplier template");

		boolean formsup = mrp.addCompleteFormRequirement(formName,formsupp,workGroupName);
		TestValidation.IsTrue(formsup, 
				"Complete Form Requirement Created", 
				"Failed to create Complete Form Requirement");

		boolean addsupp = mrp.addInstancetoRequirement("Suppliers",suppinstance);
		TestValidation.IsTrue(addsupp, 
				"Supplier Instance Added", 
				"Failed to add Supplier Instance");

		hp.clickFSQABrowserMenu();

		boolean navigate = fbp.selectResourceDropDownandNavigate("Suppliers","Forms");
		TestValidation.IsTrue(navigate, 
				"Navigated to FSQABrowser>FormsTab", 
				"Failed to navigate to FSQABrowser>Forms Tab");

		boolean applyfilter = fbp.openAndApplySettingsForColumn(COLUMNHEADER.FORMNAME, COLUMNSETTING.FILTER,formName);
		TestValidation.IsTrue(applyfilter, 
				"Applied Filter on" + COLUMNHEADER.FORMNAME, 
				"Failed to apply filter on" + COLUMNHEADER.FORMNAME);

		tsd.Location = locationInstanceValue;
		tsd.Resource = suppinstance;
		tsd.TaskName = formtaskname;
		tsd.Workgroup = workGroupName;
		tsd.IndexNo = 1;

		boolean assigntask = fbp.assignFormTask(tsd);
		TestValidation.IsTrue(assigntask, 
				"Form task assigned" + formtaskname, 
				"Failed to assign form task" + formtaskname);

		boolean navigate1 = fbp.navigateToFSQATab(FSQATAB.TASKS);
		TestValidation.IsTrue(navigate1, 
				"Navigated to FSQABrowser>Tasks Tab", 
				"Failed to navigate to FSQABrowser>Tasks Tab");			

		boolean applyfilter1 = fbp.openAndApplySettingsForColumn(COLUMNHEADER.TASKNAME, COLUMNSETTING.FILTER,formitem);
		TestValidation.IsTrue(applyfilter1, 
				"Applied Filter on" + COLUMNHEADER.TASKNAME, 
				"Failed to apply filter on" + COLUMNHEADER.TASKNAME);

		boolean recallitemtask = fbp.recallTask(formitem);
		TestValidation.IsTrue(recallitemtask, 
				"Recalled Task - " + formitem, 
				"Failed to recall task - " + formitem);

		boolean applyfilter2 = fbp.openAndApplySettingsForColumn(COLUMNHEADER.TASKNAME, COLUMNSETTING.FILTER,formsupp);
		TestValidation.IsTrue(applyfilter2, 
				"Applied Filter on" + COLUMNHEADER.TASKNAME, 
				"Failed to apply filter on" + COLUMNHEADER.TASKNAME);

		boolean recallsuppliertask = fbp.recallTask(formsupp);
		TestValidation.IsTrue(recallsuppliertask, 
				"Recalled Task - " + formsupp, 
				"Failed to recall task - " + formsupp);

		boolean applyfilter3= fbp.openAndApplySettingsForColumn(COLUMNHEADER.TASKNAME, COLUMNSETTING.FILTER,formtaskname);
		TestValidation.IsTrue(applyfilter3, 
				"Applied Filter on" + COLUMNHEADER.TASKNAME, 
				"Failed to apply filter on" + COLUMNHEADER.TASKNAME);

		boolean recallformtask = fbp.recallTask(formtaskname);
		TestValidation.IsTrue(recallformtask, 
				"Recalled Task - " + formtaskname, 
				"Failed to recall task - " + formtaskname);	

	}



	@AfterClass(alwaysRun = true)
	public void closeBrowser() throws InterruptedException {
		driver.close();
	}
}
