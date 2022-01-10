package com.project.safetychain.webapp.testcases;

import java.util.Arrays;
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
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.COLUMNHEADER;
import com.project.safetychain.webapp.pageobjects.ManageRequirementPage.COLUMNHEADER1;
import com.project.safetychain.webapp.pageobjects.ManageResourcePage;
import com.project.safetychain.webapp.pageobjects.ResourceDesignerPage;
import com.project.safetychain.webapp.pageobjects.UserManagerPage;
import com.project.safetychain.webapp.pageobjects.WorkGroupsPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.COLUMNSETTING;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.TASKDETAILS;
import com.project.safetychain.webapp.pageobjects.ResourceDesignerPage.CATEGORYTYPES;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.utilities.CommonMethods;
import com.project.utilities.ControlActions;

public class TCG_REG_SupplierFlow_ManageRequirement extends TestBase {
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
	public static String supptempname;
	public static String itemtempname;
	public static String copyitemtemplate;
	public static String copysupptemplate;

	@BeforeClass(alwaysRun = true)
	public void groupInit() throws InterruptedException {

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

		supptempname = CommonMethods.dynamicText("SuppTemp"); 
		itemtempname = CommonMethods.dynamicText("ItemTemp"); 
		copyitemtemplate = itemtempname+" 1";
		copysupptemplate = supptempname+" 1";
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

		ManageRequirementPage mrp = hp.clickRequirementsMenu();	
		if(!mrp.createSupplierRequirement(supptempname,doctype,ackreqname,workGroupName,docreqname,formName,formreqname,suppinstance)) {
			TCGFailureMsg = "Could not create requirement for supplier " + suppinstance;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		if(!mrp.createItemRequirement(itemtempname,doctype,ackreqname,workGroupName,docreqname,formName,formreqname,iteminstance)) {
			TCGFailureMsg = "Could not create requirement for item " + iteminstance;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
	}

	@Test(description="Verifying supplier/item addition in the questionnaire form", priority = -3)	
	public void TestCase_33069() throws Exception{

		hp.clickFSQABrowserMenu();
		hp.clickRequirementsMenu();	

		String supplierresourcename = mrp.addResourceToRequirement("SUPPLIERS",supptempname);

		hp.clickFSQABrowserMenu();

		boolean navigate = fbp.selectResourceDropDownandNavigate("SUPPLIERS", "FORMS");
		TestValidation.IsTrue(navigate, 
				"Navigated to forms tab", 
				"Failed to navigate to forms tab");

		boolean applyfilter = fbp.openAndApplySettingsForColumn(COLUMNHEADER.FORMNAME, COLUMNSETTING.FILTER,formName);
		TestValidation.IsTrue(applyfilter, 
				"Applied Filter on" + COLUMNHEADER.FORMNAME, 
				"Failed to apply filter on" + COLUMNHEADER.FORMNAME);

		boolean edit =fbp.editSelectForm(formName);
		TestValidation.IsTrue(edit, 
				"Openend form in edit mode", 
				"Failed to open form in edit mode");

		List <String> resource = Arrays.asList(iteminstance,supplierresourcename);
		boolean verify = formDesignerPage.verifyResourceInForm(resource);
		TestValidation.IsTrue(verify, 
				"Verified resource in form", 
				"Failed to verify resource in form");


	}	

	@Test(description="Effective Date of supplier requirement",priority = -2)	
	public void TestCase_20467() throws Exception{

		hp.clickFSQABrowserMenu();
		hp.clickRequirementsMenu();

		boolean effectivesupplier = mrp.effectiveDateForRequirement(supptempname, "SUPPLIERS");
		TestValidation.IsTrue(effectivesupplier, 
				"Effective date set and verified for supplier requirement", 
				"Failed to set and verify effective date for supplier requirement");

	}

	@Test(description="Effective Date of Item requirement",priority = -1)	
	public void TestCase_20470() throws Exception{

		hp.clickFSQABrowserMenu();
		hp.clickRequirementsMenu();

		boolean effectiveitem = mrp.effectiveDateForRequirement(itemtempname, "ITEMS");
		TestValidation.IsTrue(effectiveitem, 
				"Effective date set and verified for item requirement", 
				"Failed to set and verify effective date for item requirement");
	}


	@Test(description="Sorting of supplier/item resource name columns", priority = -2)	
	public void TestCase_38265() throws Exception{

		hp.clickFSQABrowserMenu();
		hp.clickRequirementsMenu();

		boolean navigate = mrp.navigateToSelectNewAndSort(supptempname, "SUPPLIERS");
		TestValidation.IsTrue(navigate, 
				"Navigated to select new tab", 
				"Failed to navigate to select new tab");


		boolean navigate1 = mrp.navigateToSelectNewAndSort(itemtempname, "ITEMS");
		TestValidation.IsTrue(navigate1, 
				"Navigated to select new tab", 
				"Failed to navigate to select new tab");

	}

	@Test(description="Search item/suplier template" , priority = 0)	
	public void TestCase_38262() throws Exception{

		hp.clickFSQABrowserMenu();
		hp.clickRequirementsMenu();

		boolean searchsupptemp = mrp.searchtemplate(supptempname, "SUPPLIERS");
		TestValidation.IsTrue(searchsupptemp, 
				"Supplier Template searched", 
				"Failed to search Supplier Template");

		boolean searchitemtemp = mrp.searchtemplate(itemtempname, "ITEMS");
		TestValidation.IsTrue(searchitemtemp, 
				"Item Template searched", 
				"Failed to search Item Template");

	}

	@Test(description="Copy item/suplier template" , priority = 1)	
	public void TestCase_38266() throws Exception{

		hp.clickFSQABrowserMenu();
		hp.clickRequirementsMenu();

		boolean copytemp = mrp.copytemplate(supptempname,"SUPPLIERS");
		TestValidation.IsTrue(copytemp, 
				"Supplier Template copied", 
				"Failed to copy Supplier Template");

		String requirement[] = {ackreqname,docreqname,formreqname};

		boolean verifytemp = mrp.verifycopytemplate(copysupptemplate, requirement);
		TestValidation.IsTrue(verifytemp, 
				"Supplier Template copy verified", 
				"Failed to verify copy Supplier template");


		boolean copytemp1 = mrp.copytemplate(itemtempname,"ITEMS");
		TestValidation.IsTrue(copytemp1, 
				"Item Template copied", 
				"Failed to copy Item Template");

		String irequirement[] = {ackreqname,docreqname,formreqname};

		boolean verifytemp1 = mrp.verifycopytemplate(copyitemtemplate, irequirement);
		TestValidation.IsTrue(verifytemp1, 
				"Item Template copy verified", 
				"Failed to verify copy Item template");

	}

	@Test(description="Enable/Disable supplier template",priority = 2)	
	public void TestCase_38392() throws Exception{
		hp.clickFSQABrowserMenu();
		hp.clickRequirementsMenu();

		boolean disabletemp = mrp.enabledisabletemplate(supptempname,"SUPPLIERS",false);
		TestValidation.IsTrue(disabletemp, 
				"Supplier Template Disabled", 
				"Failed to disable supplier template");

		boolean verifydisabletemp = mrp.verifydisabledtemplate(supptempname);
		TestValidation.IsTrue(verifydisabletemp, 
				"Supplier Template Disabled verified", 
				"Failed to verify disable supplier template");

		boolean enabletemp = mrp.enabledisabletemplate(supptempname,"SUPPLIERS",true);
		TestValidation.IsTrue(enabletemp, 
				"Supplier Template Enabled", 
				"Failed to enable supplier template");

	}

	@Test(description="Enable/Disable item template",priority = 3)	
	public void TestCase_38261() throws Exception{
		hp.clickFSQABrowserMenu();
		hp.clickRequirementsMenu();

		boolean disabletemp = mrp.enabledisabletemplate(itemtempname,"ITEMS",false);
		TestValidation.IsTrue(disabletemp, 
				"Supplier Template Disabled", 
				"Failed to disable item template");

		boolean verifydisabletemp = mrp.verifydisabledtemplate(itemtempname);
		TestValidation.IsTrue(verifydisabletemp, 
				"Supplier Template Disabled verified", 
				"Failed to verify disable item template");

		boolean enabletemp = mrp.enabledisabletemplate(itemtempname,"ITEMS",true);
		TestValidation.IsTrue(enabletemp, 
				"Supplier Template Enabled", 
				"Failed to enable item template");

	}


	@Test(description="Delete item/suplier template",priority = 4)	
	public void TestCase_6991() throws Exception{

		hp.clickFSQABrowserMenu();
		hp.clickRequirementsMenu();

		boolean deletesupptemp = mrp.deleterequirement(supptempname, "SUPPLIERS", ackreqname);
		TestValidation.IsTrue(deletesupptemp, 
				"Supplier Requiement deleted", 
				"Failed to delete Supplier requirement");

		boolean deleteitemtemp = mrp.deleterequirement(itemtempname, "ITEMS", ackreqname);
		TestValidation.IsTrue(deleteitemtemp, 
				"Item Requirement deleted", 
				"Failed to delete Item requirement");

	}

	@Test(description="Edit item/suplier template",priority = 5)	
	public void TestCase_6897() throws Exception{

		hp.clickFSQABrowserMenu();
		hp.clickRequirementsMenu();

		boolean editsupptemp = mrp.editrequirement(supptempname, "SUPPLIERS", formreqname);
		TestValidation.IsTrue(editsupptemp, 
				"Supplier Requiement deleted", 
				"Failed to delete Supplier requirement");

		boolean edititemtemp = mrp.editrequirement(itemtempname, "ITEMS", formreqname);
		TestValidation.IsTrue(edititemtemp, 
				"Item Requirement deleted", 
				"Failed to delete Item requirement");

	}

	@Test(description="Sort of requirement tab columns",priority = 6)	
	public void TestCase_38263() throws Exception{

		hp.clickFSQABrowserMenu();
		hp.clickRequirementsMenu();

		boolean sortsupplier = mrp.navigateToRequirement(supptempname, "SUPPLIERS");
		TestValidation.IsTrue(sortsupplier, 
				"Navigated to requirement Tab", 
				"Failed to navigate to suppliers tab");

		String taskname = mrp.sortColumn(COLUMNHEADER1.TASK_NAME);
		TestValidation.IsTrue(taskname==null, "Sorting is completed", "Could not sort");
		
		String duein = mrp.sortColumn(COLUMNHEADER1.DUE_IN);
		TestValidation.IsTrue(duein==null, "Sorting is completed", "Could not sort");
		
		String approver = mrp.sortColumn(COLUMNHEADER1.APPROVER);		
		TestValidation.IsTrue(approver==null, "Sorting is completed", "Could not sort");

		boolean sortitem = mrp.navigateToRequirement(itemtempname, "ITEMS");
		TestValidation.IsTrue(sortitem, 
				"Navigated to requirement Tab", 
				"Failed to navigate to suppliers tab");

		String taskname1 = mrp.sortColumn(COLUMNHEADER1.TASK_NAME);
		TestValidation.IsTrue(taskname1==null, "Sorting is completed", "Could not sort");
		
		String duein1 = mrp.sortColumn(COLUMNHEADER1.DUE_IN);
		TestValidation.IsTrue(duein1==null, "Sorting is completed", "Could not sort");
		
		String approver1 = mrp.sortColumn(COLUMNHEADER1.APPROVER);
		TestValidation.IsTrue(approver1==null, "Sorting is completed", "Could not sort");

	}

	@Test(description="Sorting of supplier/item tab columns",priority = 7)	
	public void TestCase_38264() throws Exception{

		hp.clickFSQABrowserMenu();
		hp.clickRequirementsMenu();

		boolean navigate = mrp.navigateToResourceTab(supptempname, "SUPPLIERS");
		TestValidation.IsTrue(navigate, 
				"Navigated to resource tab and selected all resources", 
				"Failed to navigated to resource tab and selected all resources");

		String name =mrp.sortColumn(COLUMNHEADER1.NAME);
		TestValidation.IsTrue(name==null, "Sorting is completed", "Could not sort");
		String effectiveon = mrp.sortColumn(COLUMNHEADER1.EFFECTIVE_ON);
		TestValidation.IsTrue(effectiveon==null, "Sorting is completed", "Could not sort");
		String approver = mrp.sortColumn(COLUMNHEADER1.ADDED_ON);
		TestValidation.IsTrue(approver==null, "Sorting is completed", "Could not sort");
		String addedby = mrp.sortColumn(COLUMNHEADER1.ADDED_BY);
		TestValidation.IsTrue(addedby==null, "Sorting is completed", "Could not sort");

		boolean navigate1 = mrp.navigateToResourceTab(itemtempname, "ITEMS");
		TestValidation.IsTrue(navigate1, 
				"Navigated to resource tab and selected all resources", 
				"Failed to navigated to resource tab and selected all resources");

		String name1 = mrp.sortColumn(COLUMNHEADER1.NAME);
		TestValidation.IsTrue(name1==null, "Sorting is completed", "Could not sort");
		String effectiveon1 = mrp.sortColumn(COLUMNHEADER1.EFFECTIVE_ON);
		TestValidation.IsTrue(effectiveon1==null, "Sorting is completed", "Could not sort");
		String addedon1 = mrp.sortColumn(COLUMNHEADER1.ADDED_ON);
		TestValidation.IsTrue(addedon1==null, "Sorting is completed", "Could not sort");
		String approver1 = mrp.sortColumn(COLUMNHEADER1.ADDED_BY);
		TestValidation.IsTrue(approver1==null, "Sorting is completed", "Could not sort");

	}


	@AfterClass(alwaysRun = true)
	public void closeBrowser() throws InterruptedException {
		driver.close();
	}


}
