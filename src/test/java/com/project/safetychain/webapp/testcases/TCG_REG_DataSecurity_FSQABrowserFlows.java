package com.project.safetychain.webapp.testcases;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.project.safetychain.webapp.pageobjects.HomePage;
import com.project.safetychain.webapp.pageobjects.InboxPage;
import com.project.safetychain.webapp.pageobjects.LoginPage;
import com.project.safetychain.webapp.pageobjects.ManageLocationPage;
import com.project.safetychain.webapp.pageobjects.ManageLocationPage.LocationInstanceParams;
import com.project.safetychain.webapp.pageobjects.ManageLocationPage.SelectTAB;
import com.project.safetychain.webapp.pageobjects.ManageResourcePage;
import com.project.safetychain.webapp.pageobjects.ManageResourcePage.RESOURCETYPES;
import com.project.safetychain.webapp.pageobjects.ManageResourcePage.ResourceDetailParams;
import com.project.safetychain.webapp.pageobjects.ResourceDesignerPage;
import com.project.safetychain.webapp.pageobjects.ResourceDesignerPage.CATEGORYTYPES;
import com.project.safetychain.webapp.pageobjects.UserManagerPage;
import com.project.safetychain.webapp.pageobjects.WorkGroupsPage;
import com.project.safetychain.webapp.pageobjects.CommonPage.TIMEZONE;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.COLUMNHEADER;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.COLUMNSETTING;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.FSQARESOURCE;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.FSQATAB;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.TASKDETAILS;
import com.project.safetychain.webapp.pageobjects.DocumentManagementPage;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FIELD_TYPES;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FORMRESOURCETYPES;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FORMTYPES;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FormDesignParams;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FormFieldParams;
import com.project.safetychain.webapp.pageobjects.UserManagerPage.UsrDetailParams;
import com.project.safetychain.webapp.testcases.support.FormOperations;
import com.project.safetychain.webapp.testcases.support.FormOperations.FormDetails;
import com.project.safetychain.webapp.pageobjects.FSQABrowserFormsPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage;
import com.project.safetychain.webapp.pageobjects.FormDesignerPageLocator;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.utilities.CommonMethods;
import com.project.utilities.ControlActions;
import com.project.utilities.DateTime;

public class TCG_REG_DataSecurity_FSQABrowserFlows extends TestBase{
	ControlActions controlActions;
	HomePage hp;
	LoginPage lp;
	FSQABrowserPage fbp;
	ManageLocationPage mlp;
	ManageResourcePage mrp;
	UserManagerPage ump;
	FormDesignerPage fdp;
	FSQABrowserFormsPage fbForms;
	DocumentManagementPage dmp;
	ResourceDesignerPage rdp;
	WorkGroupsPage wgp;
	InboxPage ip;
	DateTime dt = new DateTime();

	public static String workGroupName;
	public static String newWorkGroupName;
	public static String wgName;
	public static String roleName;
	public static String userUN, newUserUN, newUserUN1, userUN1, userUN2,userUN3,userUN17658, userUN18209,newUserUN18209,userUN18504, newUserUN18504;
	public static String userFN;
	public static String userLN;

	public static List<String> userLocation;
	public static List<String> userRole,workgroup;


	public static String locationCategoryValue, customerCategoryValue, 
	equipmentCategoryValue, itemCategoryValue, supplierCategoryValue;
	public static String locationInstanceValue, customerInstanceValue,locationInstanceValue1,
	equipmentInstanceValue, itemInstanceValue, supplierInstanceValue;
	public static String resourceCategory;
	public static String locInstValue,newlocationInstanceValue, newlocationInstanceValue1, newlocationInstanceValue2, newlocationInstanceValue3, newLocationInstanceValue17658, locationCategoryValue18209,locationInstanceValue18504, customerInstanceValue18504,equipmentInstanceValue18504;
	public static String newLocationCategoryValue, newCustomerCategoryValue, newEquipmentCategoryValue, locationInstanceValue18209, newLocationInstanceValue18209, customerInstanceValue18209, equipmentInstanceValue18209, itemInstanceValue18209,customerCategoryValue18504, equipmentCategoryValue18504;
	public static String newCustomerInstanceValue, newEquipmentInstanceValue, newCustomerInstanceValue1, newEquipmentInstanceValue1, newSupplierInstanceValue, supplierInstanceValue18209, locationCategoryValue18504;
	public static String locationCategoryValue17658, customerCategoryValue17658, equipmentCategoryValue17658, locationInstanceValue17658, customerInstanceValue17658, equipmentInstanceValue17658,customerCategoryValue18209, equipmentCategoryValue18209, itemCategoryValue18209, supplierCategoryValue18209; 
	public static String checkFormName,newcheckFormName,newcheckFormName1,checkFormName17321, checkFormName17322, checkFormName18209,
	questionnaireFormName, questionnaireFormName17322,auditFormName,auditFormName17322, numFieldName, numericFieldName, singleLineTxtFN, section,questionnaireFormName17556, checkFormName17658,checkFormName18504;
	public static String task17553, newTask17553, task17658, newTask17658;
	public static String locationCategoryValue10339, customerCategoryValue10339, equipmentCategoryValue10339, locationInstanceValue10339, customerInstanceValue10339, equipmentInstanceValue10339, checkFormName10339;
	public static List<String> userLocation2;
	public static List<String> userRole2;
	public static List<String> defaultLocation;
	public static String pin;

	@BeforeClass(alwaysRun = true)
	public void groupInit() throws Exception {

		workGroupName = CommonMethods.dynamicText("Work_Group");
		newWorkGroupName = CommonMethods.dynamicText("New_Work_Group");
		roleName = CommonMethods.dynamicText("RL");
		userUN = CommonMethods.dynamicText("UN");
		newUserUN = CommonMethods.dynamicText("User_N");
		newUserUN1 = CommonMethods.dynamicText("New_User_N");
		userUN1 = CommonMethods.dynamicText("UserN");
		userUN2 = CommonMethods.dynamicText("UN17322");
		userUN3 = CommonMethods.dynamicText("UN3");
		userFN = CommonMethods.dynamicText("FN");
		userLN = CommonMethods.dynamicText("LN");
		userLocation = new ArrayList<String>();
		userRole = new ArrayList<String>();

		userLocation2 = new ArrayList<String>();
		userRole2 = new ArrayList<String>();

		locationCategoryValue = CommonMethods.dynamicText("Loc_Cat");
		customerCategoryValue = CommonMethods.dynamicText("Cust_Cat");
		equipmentCategoryValue = CommonMethods.dynamicText("Equip_Cat");
		itemCategoryValue = CommonMethods.dynamicText("Item_Cat");
		supplierCategoryValue = CommonMethods.dynamicText("Supp_Cat");
		locationInstanceValue = CommonMethods.dynamicText("Loc_Inst");
		locInstValue = CommonMethods.dynamicText("LocInst1");
		newlocationInstanceValue = CommonMethods.dynamicText("Loc_I");
		newlocationInstanceValue1 = CommonMethods.dynamicText("New_Loc_I");
		newlocationInstanceValue2 = CommonMethods.dynamicText("Loc_Inst_17322");
		customerInstanceValue = CommonMethods.dynamicText("Cust_Inst");
		newCustomerInstanceValue = CommonMethods.dynamicText("Customers_I");
		newCustomerInstanceValue1 = CommonMethods.dynamicText("Cust_In");
		equipmentInstanceValue = CommonMethods.dynamicText("Equip_Inst");
		newEquipmentInstanceValue = CommonMethods.dynamicText("Equipment_I");
		newEquipmentInstanceValue1 = CommonMethods.dynamicText("Equip_In");
		itemInstanceValue = CommonMethods.dynamicText("Item_Inst");
		supplierInstanceValue = CommonMethods.dynamicText("Supp_Inst");
		newSupplierInstanceValue = CommonMethods.dynamicText("Supplier_I");
		task17553 =CommonMethods.dynamicText("Task_17553");
		newTask17553 = CommonMethods.dynamicText("New_Task_17553");

		driver = launchbrowser();
		controlActions = new ControlActions(driver);
		controlActions.getUrl(applicationUrl);
		hp = new HomePage(driver);
		mlp = new ManageLocationPage(driver);
		ump = new UserManagerPage(driver);
		mrp = new ManageResourcePage(driver);
		fbForms = new FSQABrowserFormsPage(driver);
		dmp = new DocumentManagementPage(driver);
		fdp = new FormDesignerPage(driver);
		rdp = new ResourceDesignerPage(driver);
		wgp = new WorkGroupsPage(driver);
		fbp = new FSQABrowserPage(driver);
		ip = new InboxPage(driver);

		lp = new LoginPage(driver);
		hp = lp.performLogin(adminUsername, adminPassword);
		if(hp.error) {
			TCGFailureMsg = "Could NOT login to application";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}


		//Category creation
		HashMap<String,String> categories = new HashMap<String, String>();
		categories.put(CATEGORYTYPES.LOCATION, locationCategoryValue);
		categories.put(CATEGORYTYPES.CUSTOMERS, customerCategoryValue);
		categories.put(CATEGORYTYPES.EQUIPMENT, equipmentCategoryValue);
		categories.put(CATEGORYTYPES.ITEMS, itemCategoryValue);
		categories.put(CATEGORYTYPES.SUPPLIERS, supplierCategoryValue);
		ResourceDesignerPage rdp = hp.clickResourceDesignerMenu();
		if(!rdp.createCategory(categories)) {
			TCGFailureMsg = "Could NOT create Resource categories for - " + locationCategoryValue + " "
					+ customerCategoryValue + " " + equipmentCategoryValue + " " + itemCategoryValue
					+ " " + supplierCategoryValue;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		//Location instance creation
		HashMap<String,Boolean> locInstance = new HashMap<String, Boolean>();
		locInstance.put(locationInstanceValue, true);
		locInstance.put(locInstValue, true);
		locInstance.put(newlocationInstanceValue, true);
		locInstance.put(newlocationInstanceValue1, true);
		locInstance.put(newlocationInstanceValue2, true);

		LocationInstanceParams lip = new LocationInstanceParams();
		lip.CategoryName = locationCategoryValue;
		lip.NumberFieldValue = 10;
		lip.TextFieldValue = "XYZ";
		lip.InstanceName = locInstance;
		ManageLocationPage mlp = hp.clickLocationsMenu();
		if(!mlp.createLocation(lip)) {
			TCGFailureMsg = "Could NOT create Location instance";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		//Resource creation - Customer
		ResourceDetailParams rd1 = new ResourceDetailParams();
		rd1.CategoryName = customerCategoryValue;
		rd1.CategoryType = RESOURCETYPES.CUSTOMERS;
		rd1.NumberFieldValue = 15;
		rd1.TextFieldValue = "LMN";
		rd1.InstanceName = customerInstanceValue;
		rd1.InstanceStatus = true;

		ManageResourcePage mrp = hp.clickResourcesMenu();
		if(!mrp.createResource(rd1)) {
			TCGFailureMsg = "Could NOT create Customer Instance for category - " + customerCategoryValue;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		//Resource creation - Customer
		ResourceDetailParams rd5 = new ResourceDetailParams();
		rd5.CategoryName = customerCategoryValue;
		rd5.CategoryType = RESOURCETYPES.CUSTOMERS;
		rd5.NumberFieldValue = 15;
		rd5.TextFieldValue = "LMN";
		rd5.InstanceName = newCustomerInstanceValue;
		rd5.InstanceStatus = true;

		hp.clickResourcesMenu();
		if(!mrp.createResource(rd5)) {
			TCGFailureMsg = "Could NOT create Customer Instance for category - " + customerCategoryValue;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		//Resource creation - Equipment

		ResourceDetailParams rd2 = new ResourceDetailParams();
		rd2.CategoryName = equipmentCategoryValue;
		rd2.CategoryType = RESOURCETYPES.EQUIPMENT;
		rd2.NumberFieldValue = 15;
		rd2.TextFieldValue = "LMN";
		rd2.InstanceName = equipmentInstanceValue;
		rd2.InstanceStatus = true;

		mrp = hp.clickResourcesMenu();
		if(!mrp.createResource(rd2)) {
			TCGFailureMsg = "Could NOT create Equipment Instance for category - " + equipmentCategoryValue;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		//Resource creation - Equipment

		ResourceDetailParams rd6 = new ResourceDetailParams();
		rd6.CategoryName = equipmentCategoryValue;
		rd6.CategoryType = RESOURCETYPES.EQUIPMENT;
		rd6.NumberFieldValue = 15;
		rd6.TextFieldValue = "LMN";
		rd6.InstanceName = newEquipmentInstanceValue;
		rd6.InstanceStatus = true;

		mrp = hp.clickResourcesMenu();
		if(!mrp.createResource(rd6)) {
			TCGFailureMsg = "Could NOT create Equipment Instance for category - " + equipmentCategoryValue;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
		//Resource creation - Items

		ResourceDetailParams rd3 = new ResourceDetailParams();
		rd3.CategoryName = itemCategoryValue;
		rd3.CategoryType = RESOURCETYPES.ITEMS;
		rd3.NumberFieldValue = 15;
		rd3.TextFieldValue = "LMN";
		rd3.InstanceName = itemInstanceValue;
		rd3.InstanceStatus = true;

		mrp = hp.clickResourcesMenu();
		if(!mrp.createResource(rd3)) {
			TCGFailureMsg = "Could NOT create Equipment Instance for category - " + itemCategoryValue;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
		//Resource creation - Supplier
		ResourceDetailParams rd7 = new ResourceDetailParams();
		rd7.CategoryName = supplierCategoryValue;
		rd7.CategoryType = RESOURCETYPES.SUPPLIERS;
		rd7.NumberFieldValue = 15;
		rd7.TextFieldValue = "LMN";
		rd7.InstanceName = supplierInstanceValue;
		rd7.InstanceStatus = true;

		hp.clickResourcesMenu();
		if(!mrp.createResource(rd7)) {
			TCGFailureMsg = "Could NOT create Customer Instance for category - " + supplierCategoryValue;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		//Resource creation - Supplier
		ResourceDetailParams rd8 = new ResourceDetailParams();
		rd8.CategoryName = supplierCategoryValue;
		rd8.CategoryType = RESOURCETYPES.SUPPLIERS;
		rd8.NumberFieldValue = 15;
		rd8.TextFieldValue = "LMN";
		rd8.InstanceName = newSupplierInstanceValue;
		rd8.InstanceStatus = true;

		hp.clickResourcesMenu();
		if(!mrp.createResource(rd8)) {
			TCGFailureMsg = "Could NOT create Customer Instance for category - " + supplierCategoryValue;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		hp.clickLocationsMenu();
		if(!mlp.scrollAndFindLocationInstance(locationCategoryValue, locationInstanceValue)) {
			TCGFailureMsg = "Could NOT find Location instance"+locationInstanceValue;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		HashMap<String, List<String>> resourceInstances = new LinkedHashMap<String, List<String>>();
		resourceInstances.put(SelectTAB.CUSTOMERS, Arrays.asList(customerInstanceValue, newCustomerInstanceValue));
		resourceInstances.put(SelectTAB.EQUIPMENT, Arrays.asList(equipmentInstanceValue, newEquipmentInstanceValue));
		resourceInstances.put(SelectTAB.ITEMS, Arrays.asList(itemInstanceValue));
		resourceInstances.put(SelectTAB.SUPPLIERS, Arrays.asList(newSupplierInstanceValue));

		if(!mlp.linkResourceToLocation(resourceInstances)) {
			TCGFailureMsg = "Could NOT LINKED resource instances";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		} 
		if(!wgp.createWorkGroup(newWorkGroupName, adminUsername)) {
			TCGFailureMsg = "Could not create workgroup - " + newWorkGroupName;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		hp.clickLocationsMenu();
		if(!mlp.scrollAndFindLocationInstance(locationCategoryValue, locInstValue)) {
			TCGFailureMsg = "Could NOT find Location instance"+locInstValue;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		HashMap<String, List<String>> resourceInstances1 = new LinkedHashMap<String, List<String>>();
		resourceInstances1.put(SelectTAB.SUPPLIERS, Arrays.asList(supplierInstanceValue));

		if(!mlp.linkResourceToLocation(resourceInstances1)) {
			TCGFailureMsg = "Could NOT LINKED resource instances";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		} 

		//FORM - Creation and Release

		checkFormName	= CommonMethods.dynamicText("Automation_CheckForm");
		numFieldName    = "Numeric Field";

		HashMap<String, List<String>> fields = new HashMap<String, List<String>>();
		fields.put(FIELD_TYPES.NUMERIC, Arrays.asList(numFieldName));

		FormFieldParams ffp = new FormFieldParams();
		ffp.FieldDetails = fields;

		HashMap<String, List<String>> resource = new HashMap<String, List<String>>();
		resource.put(FORMRESOURCETYPES.CUSTOMERS, Arrays.asList(customerCategoryValue));
		resource.put(FORMRESOURCETYPES.EQUIPMENT, Arrays.asList(equipmentCategoryValue));
		resource.put(FORMRESOURCETYPES.ITEMS, Arrays.asList(itemCategoryValue));
		resource.put(FORMRESOURCETYPES.SUPPLIERS, Arrays.asList(supplierCategoryValue));


		FormDesignParams fdpDets = new FormDesignParams();
		fdpDets.FormType = FORMTYPES.CHECK;
		fdpDets.FormName = checkFormName;
		fdpDets.SelectResources = resource;
		fdpDets.DesignFields = Arrays.asList(ffp);
		fdpDets.ReleaseForm = true;

		hp.clickFormDesignerMenu();
		boolean createAndReleaseForm = fdp.createAndReleaseForm(fdpDets);
		if(!createAndReleaseForm) {
			TCGFailureMsg = "Could NOT create and Release form - " + checkFormName;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
		//Create Questionnaire Form and Release
		questionnaireFormName17556 = CommonMethods.dynamicText("Auto_Quest_Form_17556");
		numericFieldName = "Numeric Field";

		HashMap<String, List<String>> qstSecFields = new HashMap<String, List<String>>();
		qstSecFields.put(FIELD_TYPES.NUMERIC, Arrays.asList(numericFieldName));

		FormFieldParams ffpSecQst = new FormFieldParams();
		ffpSecQst.FieldDetails = qstSecFields;

		HashMap<String, List<String>> resources = new HashMap<String, List<String>>();
		resources.put(FORMRESOURCETYPES.SUPPLIERS, Arrays.asList(supplierCategoryValue));

		FormDesignParams fdpQst = new FormDesignParams();
		fdpQst.FormType = FORMTYPES.QUESTIONNAIRE;
		fdpQst.FormName = questionnaireFormName17556;
		fdpQst.SelectResources = resources;
		fdpQst.DesignFields = Arrays.asList(ffpSecQst);
		fdpQst.ReleaseForm = true;

		hp.clickFormDesignerMenu();
		boolean createAndReleaseForm1 = fdp.createAndReleaseForm(fdpQst);
		if(!createAndReleaseForm1) {
			TCGFailureMsg = "Could NOT create and Release form - " + questionnaireFormName17556;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		hp.clickFSQABrowserMenu();
		if(!fbp.selectResourceDropDownandNavigate(FSQARESOURCE.SUPPLIERS,FSQATAB.FORMS)) {
			TCGFailureMsg = "For SUPPLIERS category, could NOT navigate to FSQABrowser > Forms tab";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		if(!fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, questionnaireFormName17556)) {
			TCGFailureMsg = "Could not filter form - " + questionnaireFormName17556;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		//Submit form
		LinkedHashMap<String, List<String>> fillDetails = new LinkedHashMap<String, List<String>>();
		fillDetails.put("Numeric Field", Arrays.asList("10"));

		FormDetails fd = new FormDetails();
		fd.fieldDetails = fillDetails;
		fd.locationName = locInstValue;
		fd.resourceName = supplierInstanceValue;
		fd.isSubmit = true;

		FormOperations form = new FormOperations(driver);
		if(!form.fillAndSubmitData(fd)) {
			TCGFailureMsg = "Could NOT submit form - " + questionnaireFormName17556;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
		if(!wgp.createWorkGroup(workGroupName, adminUsername)) {
			TCGFailureMsg = "Could not create workgroup - " + workGroupName;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		//user Creation
		workgroup = new ArrayList<String>();
		userRole.add("SuperAdmin");
		workgroup.add(workGroupName);
		UserManagerPage ump = hp.clickUsersMenu();
		UsrDetailParams udp = new UsrDetailParams();
		udp.Username = userUN;
		udp.Password = GenericPassword;
		udp.FirstName = userFN;
		udp.LastName = userLN;
		udp.Email = "test@test.com";
		udp.Timezone = TIMEZONE.REPUBLICOFINDIA;
		udp.Locations = Arrays.asList(locationInstanceValue);
		udp.Roles = userRole;
		udp.WorkGroups = workgroup;
		boolean userCreation = ump.createInternalUser(udp);
		if(!userCreation) {
			TCGFailureMsg = "Could NOT create user - " + userUN;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
		//user Creation
		UsrDetailParams udp1 = new UsrDetailParams();
		udp1.Username = userUN3;
		udp1.Password = GenericPassword;
		udp1.FirstName = userFN;
		udp1.LastName = userLN;
		udp1.Email = "test@test.com";
		udp1.Timezone = TIMEZONE.REPUBLICOFINDIA;
		udp1.Locations = Arrays.asList(locInstValue);
		udp1.Roles = Arrays.asList("SuperAdmin");
		boolean userCreation1 = ump.createInternalUser(udp1);
		if(!userCreation1) {
			TCGFailureMsg = "Could NOT create user - " + userUN3;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		if(!hp.userLogout()){
			TCGFailureMsg = "Could NOT logout from application";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
		if(!lp.loginAfterUserCreation(userUN, GenericPassword, GenericNewPassword)) {
			TCGFailureMsg = "Could NOT login with user - " + userUN;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}



	}

	@Test(description = "9130-Data Security - Admin Users can disable resource(s) to\r\n"
			+ "remove them from all Locations via Manage Resources")
	public void TestCase_12055() {
		hp.userLogout();
		hp = lp.performLogin(userUN, GenericNewPassword);
		TestValidation.Equals(hp.error, 
				false, 
				"Login with user - "+userUN, 
				"Could Not Login with user - "+userUN);

		fbp = hp.clickFSQABrowserMenu();
		TestValidation.Equals(fbp.error, 
				false, 
				"Opened FSQA Browser", 
				"Could Not Open FSQA Browser"); 

		boolean selectCust = fbp.selectDropDownValue(FORMRESOURCETYPES.CUSTOMERS);
		TestValidation.IsTrue(selectCust, 
				"Selected CUSTOMERS Category from drop down", 
				"Failed to select CUSTOMERS Category from drop down");

		boolean searchAndSelectCust = fbp.searchSelect(customerInstanceValue);
		TestValidation.IsTrue(searchAndSelectCust, 
				"Succesfully searched and selected resource "+ customerInstanceValue, 
				"FAILED to search and select resource " + customerInstanceValue);

		boolean custIsRevealed = fbp.selectLocationAndCheckLocationIsDisplayed(locationInstanceValue);
		TestValidation.IsTrue(custIsRevealed, 
				"Succesfully verified CUSTOMERS resource - "+customerInstanceValue+" is revealed to location " + locationInstanceValue, 
				"Failed to verify that CUSTOMERS resource - "+customerInstanceValue+" is revealed to location "+ locationInstanceValue);

		boolean searchAndSelectCust1 = fbp.searchSelect(newCustomerInstanceValue);
		TestValidation.IsTrue(searchAndSelectCust1, 
				"Succesfully searched and selected resource "+ newCustomerInstanceValue, 
				"FAILED to search and select resource " + newCustomerInstanceValue);

		boolean custIsRevealed1 = fbp.selectLocationAndCheckLocationIsDisplayed(locationInstanceValue);
		TestValidation.IsTrue(custIsRevealed1, 
				"Succesfully verified CUSTOMERS resource - "+newCustomerInstanceValue+" is revealed to location " + locationInstanceValue, 
				"Failed to verify that CUSTOMERS resource - "+newCustomerInstanceValue+" is revealed to location "+ locationInstanceValue);

		hp.clickResourcesMenu();
		boolean searchAndDisableResource = mrp.searchAndDisableResource(RESOURCETYPES.CUSTOMERS, customerInstanceValue);
		TestValidation.IsTrue(searchAndDisableResource, 
				"Succesfully Disabled Resource - "+customerInstanceValue, 
				"Failed to Disable Resource - "+customerInstanceValue);

		boolean verifyResourceIsDisable = mrp.verifyResourceIsDisabled(customerInstanceValue);
		TestValidation.IsTrue(verifyResourceIsDisable, 
				"Verified Resource - "+customerInstanceValue+ "is Disabled in Manage Resource Tools", 
				"Failed to verify Resource - "+customerInstanceValue+" is disable in Manage Resource Tools ");


		fbp = hp.clickFSQABrowserMenu();
		TestValidation.Equals(fbp.error, 
				false, 
				"Opened FSQA Browser", 
				"Could Not Open FSQA Browser"); 

		boolean disabledResourceIsNotHidden = fbp.searchSelect(customerInstanceValue);
		TestValidation.IsTrue(disabledResourceIsNotHidden, 
				"Verified disabled Resource - "+customerInstanceValue+ " is not hidden from resource hierarchy ", 
				"Failed to verify disable Resource - "+customerInstanceValue+" is not hidden from resource hierarchy");

		boolean navigateToforms = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.CUSTOMERS,FSQATAB.FORMS);
		TestValidation.IsTrue(navigateToforms, 
				"For customer category, NAVIGATED to FSQABrowser > Forms tab", 
				"Failed to navigate to FSQABrowser > Forms tab");

		boolean applyFilterAndOpenForm = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, checkFormName);
		TestValidation.IsTrue(applyFilterAndOpenForm, 
				"Selected & opened the form - "+checkFormName, 
				"Could NOT open the form - "+checkFormName);

		FormOperations fo3 = new FormOperations(driver);
		boolean searchNodata = fo3.searchSelectResourceNoData(customerInstanceValue);
		TestValidation.IsTrue(searchNodata, 
				"Verified disabled Resource - "+customerInstanceValue+ " is hidden in SELECT RESOURCE DropDown on a form.", 
				"Failed to verify disable Resource - "+customerInstanceValue+" is hidden in SELECT RESOURCE DropDown on a form.");
		hp.userLogout();
		lp.performLogin(adminUsername, adminPassword);
	}
	@Test(description = " Data Security - Admin Users can reactivate disabled resource(s) to\r\n"
			+ "re-“reveal” them to all Locations via Manage Resources")
	public void TestCase_12118() {
		hp.userLogout();
		hp = lp.performLogin(userUN, GenericNewPassword);
		TestValidation.Equals(hp.error, 
				false, 
				"Login with user - "+userUN, 
				"Could Not Login with user - "+userUN);

		fbp = hp.clickFSQABrowserMenu();
		TestValidation.Equals(fbp.error, 
				false, 
				"Opened FSQA Browser", 
				"Could Not Open FSQA Browser"); 

		boolean selectEquip = fbp.selectDropDownValue(FORMRESOURCETYPES.EQUIPMENT);
		TestValidation.IsTrue(selectEquip, 
				"Selected EQUIPMENT Category from drop down", 
				"Failed to select EQUIPMENT Category from drop down");

		boolean searchAndSelectEquiInst = fbp.searchSelect(equipmentInstanceValue);
		TestValidation.IsTrue(searchAndSelectEquiInst, 
				"Succesfully searched and selected resource "+ equipmentInstanceValue, 
				"FAILED to search and select resource " + equipmentInstanceValue);

		boolean equipResourcesIsRevealed = fbp.selectLocationAndCheckLocationIsDisplayed(locationInstanceValue);
		TestValidation.IsTrue(equipResourcesIsRevealed, 
				"Succesfully verified EQUIPMENT resource - "+equipmentInstanceValue+" is revealed to location - " + locationInstanceValue, 
				"Failed to verify that EQUIPMENT resource - "+equipmentInstanceValue+" is revealed to location - "+ locationInstanceValue);


		hp.clickResourcesMenu();
		boolean searchAndDisableResource = mrp.searchAndDisableResource(RESOURCETYPES.EQUIPMENT, equipmentInstanceValue);
		TestValidation.IsTrue(searchAndDisableResource, 
				"Succesfully Disabled Resource - "+equipmentInstanceValue, 
				"Failed to Disable Resource - "+equipmentInstanceValue);

		boolean verifyResourceIsDisable = mrp.verifyResourceIsDisabled(equipmentInstanceValue);
		TestValidation.IsTrue(verifyResourceIsDisable, 
				"Verified Resource - "+equipmentInstanceValue+ "is Disabled in Manage Resource Tools", 
				"Failed to verify Resource - "+equipmentInstanceValue+" is disable in Manage Resource Tools ");

		fbp = hp.clickFSQABrowserMenu();
		TestValidation.Equals(fbp.error, 
				false, 
				"Opened FSQA Browser", 
				"Could Not Open FSQA Browser"); 

		boolean selectEquip1 = fbp.selectDropDownValue(FORMRESOURCETYPES.EQUIPMENT);
		TestValidation.IsTrue(selectEquip1, 
				"Selected EQUIPMENT Category from drop down", 
				"Failed to select EQUIPMENT Category from drop down");

		boolean disabledResourceIsNotHidden = fbp.searchSelect(equipmentInstanceValue);
		TestValidation.IsTrue(disabledResourceIsNotHidden, 
				"Verified disabled Resource - "+equipmentInstanceValue+ " is not hidden from resource hierarchy ", 
				"Failed to verify disable Resource - "+equipmentInstanceValue+" is not hidden from resource hierarchy");

		boolean navigateToforms = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.EQUIPMENT,FSQATAB.FORMS);
		TestValidation.IsTrue(navigateToforms, 
				"For EQUIPMENT category, NAVIGATED to FSQABrowser > Forms tab", 
				"Failed to navigate to FSQABrowser > Forms tab");

		boolean applyFilterAndOpenForm = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, checkFormName);
		TestValidation.IsTrue(applyFilterAndOpenForm, 
				"Selected & opened the form - "+checkFormName, 
				"Could NOT open the form - "+checkFormName);

		FormOperations fo3 = new FormOperations(driver);
		boolean searchNodata = fo3.searchSelectResourceNoData(equipmentInstanceValue);
		TestValidation.IsTrue(searchNodata, 
				"Verified disabled Resource - "+equipmentInstanceValue+ " is hidden in SELECT RESOURCE DropDown on a form.", 
				"Failed to verify disable Resource - "+equipmentInstanceValue+" is hidden in SELECT RESOURCE DropDown on a form.");

		hp.clickResourcesMenu();
		boolean searchAndActiveResource = mrp.searchAndActiveResource(RESOURCETYPES.EQUIPMENT, equipmentInstanceValue);
		TestValidation.IsTrue(searchAndActiveResource, 
				"Succesfully Activated Resource - "+equipmentInstanceValue, 
				"Failed to Activate Resource - "+equipmentInstanceValue);

		boolean verifyResourceIsActive = mrp.verifyResourceIsActivated(equipmentInstanceValue);
		TestValidation.IsTrue(verifyResourceIsActive, 
				"Verified Resource - "+equipmentInstanceValue+ "is Reactivated in Manage Resource Tools", 
				"Failed to verify Resource - "+equipmentInstanceValue+" is Reactivated in Manage Resource Tools ");

		fbp = hp.clickFSQABrowserMenu();
		TestValidation.Equals(fbp.error, 
				false, 
				"Opened FSQA Browser", 
				"Could Not Open FSQA Browser"); 

		boolean selectEquip2 = fbp.selectDropDownValue(FORMRESOURCETYPES.EQUIPMENT);
		TestValidation.IsTrue(selectEquip2, 
				"Selected EQUIPMENT Category from drop down", 
				"Failed to select EQUIPMENT Category from drop down");

		boolean activatedResourceIsNotHidden = fbp.searchSelect(equipmentInstanceValue);
		TestValidation.IsTrue(activatedResourceIsNotHidden, 
				"Verified Reactivated Resource - "+equipmentInstanceValue+ " is not hidden from resource hierarchy ", 
				"Failed to verify Reactivated Resource - "+equipmentInstanceValue+" is not hidden from resource hierarchy");

		boolean navigateToforms1 = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.EQUIPMENT,FSQATAB.FORMS);
		TestValidation.IsTrue(navigateToforms1, 
				"For EQUIPMENT category, NAVIGATED to FSQABrowser > Forms tab", 
				"Failed to navigate to FSQABrowser > Forms tab");

		boolean applyFilterAndOpenForm1 = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, checkFormName);
		TestValidation.IsTrue(applyFilterAndOpenForm1, 
				"Selected & opened the form - "+checkFormName, 
				"Could NOT open the form - "+checkFormName);

		FormOperations fo4 = new FormOperations(driver);
		boolean selectResource = fo4.selectResource(equipmentInstanceValue);
		TestValidation.IsTrue(selectResource, 
				"Verified Reactivated Resource - "+equipmentInstanceValue+ " is displayed in SELECT RESOURCE DropDown on a form.", 
				"Failed to verify Reactivated Resource - "+equipmentInstanceValue+" is display in SELECT RESOURCE DropDown on a form.");

		hp.userLogout();
		lp.performLogin(adminUsername, adminPassword);
	}

	@Test(description = "Data Security - Data Security should apply to WIP forms on Web")
	public void TestCase_16790() {
		hp.userLogout();
		lp.performLogin(adminUsername, adminPassword);

		//user Creation
		userLocation.add(newlocationInstanceValue);
		UserManagerPage ump = hp.clickUsersMenu();
		UsrDetailParams udp = new UsrDetailParams();
		udp.Username = newUserUN;
		udp.Password = GenericPassword;
		udp.FirstName = userFN;
		udp.LastName = userLN;
		udp.Email = "test@test.com";
		udp.Timezone = TIMEZONE.REPUBLICOFINDIA;
		udp.Locations = userLocation;
		udp.Roles = Arrays.asList("SuperAdmin");
		boolean userCreation = ump.createInternalUser(udp);
		TestValidation.IsTrue(userCreation, 
				"Succesfully created User  - " +newUserUN, 
				"Could NOT create user - " + newUserUN );

		//Resource creation - Customer
		HashMap<String,Boolean> custInstance = new HashMap<String, Boolean>();
		custInstance.put(newCustomerInstanceValue1, true);

		ResourceDetailParams rd1 = new ResourceDetailParams();
		rd1.CategoryName = customerCategoryValue;
		rd1.CategoryType = RESOURCETYPES.CUSTOMERS;
		rd1.NumberFieldValue = 15;
		rd1.TextFieldValue = "LMN";
		rd1.InstanceNames = custInstance;
		rd1.LocationInstanceValue = newlocationInstanceValue;

		mrp = hp.clickResourcesMenu();
		TestValidation.Equals(mrp.error, 
				false, 
				"Opened Manage Resource", 
				"Could Not Open Manage Resource"); 

		boolean createResource1 = mrp.createResourceLinkLocation(rd1);
		TestValidation.IsTrue(createResource1, 
				"Succesfully created Customer Instance - "+newCustomerInstanceValue1+" for category - " + customerCategoryValue+ " and linked with Location -" +newlocationInstanceValue, 
				"Could NOT create Customer Instance - "+newCustomerInstanceValue1+" for category - " + customerCategoryValue);

		//FORM - Creation and Release

		newcheckFormName	= CommonMethods.dynamicText("16790_Automation_CheckForm");
		numFieldName    = "Numeric Field";

		HashMap<String, List<String>> fields = new HashMap<String, List<String>>();
		fields.put(FIELD_TYPES.NUMERIC, Arrays.asList(numFieldName));

		FormFieldParams ffp = new FormFieldParams();
		ffp.FieldDetails = fields;

		HashMap<String, List<String>> resource = new HashMap<String, List<String>>();
		resource.put(FORMRESOURCETYPES.CUSTOMERS, Arrays.asList(customerCategoryValue));

		FormDesignParams fdpDets = new FormDesignParams();
		fdpDets.FormType = FORMTYPES.CHECK;
		fdpDets.FormName = newcheckFormName;
		fdpDets.SelectResources = resource;
		fdpDets.DesignFields = Arrays.asList(ffp);
		fdpDets.ReleaseForm = true;

		hp.clickFormDesignerMenu();
		boolean createAndReleaseForm = fdp.createAndReleaseForm(fdpDets);
		TestValidation.IsTrue(createAndReleaseForm,
				"Able to create and release form:" + newcheckFormName,
				"Could NOT able to create and release form:" + newcheckFormName);

		fbp = hp.clickFSQABrowserMenu();
		boolean navigateToforms = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.CUSTOMERS,FSQATAB.FORMS);
		TestValidation.IsTrue(navigateToforms, 
				"For customer category, NAVIGATED to FSQABrowser > Forms tab", 
				"Failed to navigate to FSQABrowser > Forms tab");

		boolean openAndApplySettings = fbp.openAndApplySettingsForColumn(COLUMNHEADER.FORMNAME, COLUMNSETTING.FILTER, newcheckFormName);
		TestValidation.IsTrue(openAndApplySettings, 
				"Searched form - "+newcheckFormName, 
				"Could NOT search the form - "+newcheckFormName);

		boolean verifyForm = fbp.verifyFormIsDisplayed(newcheckFormName);
		TestValidation.IsTrue(verifyForm, 
				"VERIFIED form "+newcheckFormName+" is Displayed in FSQA browser", 
				"FAILED to verify form in FSQA browser");

		boolean logoutUser = hp.userLogout();
		TestValidation.IsTrue(logoutUser, 
				"Logged out with user " + adminUsername, 
				"Could NOT logout with user " + adminUsername);

		boolean loginNewUser = lp.loginAfterUserCreation(newUserUN, GenericPassword, GenericNewPassword);
		TestValidation.IsTrue(loginNewUser, 
				"Logged In with created user - " + newUserUN, 
				"Could NOT login with user - " + newUserUN);

		fbp = hp.clickFSQABrowserMenu();
		boolean navigateToforms2 = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.CUSTOMERS,FSQATAB.FORMS);
		TestValidation.IsTrue(navigateToforms2, 
				"For customer category, NAVIGATED to FSQABrowser > Forms tab", 
				"Failed to navigate to FSQABrowser > Forms tab");

		boolean applyFilterAndOpenForm = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, newcheckFormName);
		TestValidation.IsTrue(applyFilterAndOpenForm, 
				"Selected & opened the form - "+newcheckFormName, 
				"Could NOT open the form - "+newcheckFormName);

		//Save form
		LinkedHashMap<String, List<String>> fillDetails = new LinkedHashMap<String, List<String>>();
		fillDetails.put("Numeric Field", Arrays.asList("10"));

		FormDetails fd = new FormDetails();
		fd.fieldDetails = fillDetails;
		fd.locationName = newlocationInstanceValue;
		fd.resourceName = newCustomerInstanceValue1;
		fd.isSubmit = false;
		FormOperations fo = new FormOperations(driver);
		boolean save = fo.submitData(fd);
		TestValidation.IsTrue(save, "Successfully saved Form -  " + newcheckFormName, "Failed to save form " + newcheckFormName);

		boolean verifySavedForm = fbp.verifyFormIsDisplayedInSavedForms(newcheckFormName);
		TestValidation.IsTrue(verifySavedForm, 
				"Successfully verified Form -  " + newcheckFormName+ " in Saved Forms", 
				"Failed to verify form " + newcheckFormName+" in Saved Forms");
		hp.userLogout();
		lp.performLogin(adminUsername, adminPassword);
	}
	@Test(description = "Data Security - Verify that Save & Submit Forms with Data security\r\n"
			+ "on Web")
	public void TestCase_16792() {
		hp.userLogout();
		lp.performLogin(adminUsername, adminPassword);

		//user Creation
		userLocation.add(newlocationInstanceValue1);
		userRole.add("SuperAdmin");
		UserManagerPage ump = hp.clickUsersMenu();
		UsrDetailParams udp = new UsrDetailParams();
		udp.Username = newUserUN1;
		udp.Password = GenericPassword;
		udp.FirstName = userFN;
		udp.LastName = userLN;
		udp.Email = "test@test.com";
		udp.Timezone = TIMEZONE.REPUBLICOFINDIA;
		udp.Locations = userLocation;
		udp.Roles = userRole;
		boolean userCreation = ump.createInternalUser(udp);
		TestValidation.IsTrue(userCreation, 
				"Succesfully created User  - " +newUserUN1, 
				"Could NOT create user - " + newUserUN1 );

		//Resource creation - Equipment
		HashMap<String,Boolean> equipInstance = new HashMap<String, Boolean>();
		equipInstance.put(newEquipmentInstanceValue1, true);

		ResourceDetailParams rd1 = new ResourceDetailParams();
		rd1.CategoryName = equipmentCategoryValue;
		rd1.CategoryType = RESOURCETYPES.EQUIPMENT;
		rd1.NumberFieldValue = 15;
		rd1.TextFieldValue = "LMN";
		rd1.InstanceNames = equipInstance;
		rd1.LocationInstanceValue = newlocationInstanceValue1;

		mrp = hp.clickResourcesMenu();
		TestValidation.Equals(mrp.error, 
				false, 
				"Opened Manage Resource", 
				"Could Not Open Manage Resource"); 

		boolean createResource1 = mrp.createResourceLinkLocation(rd1);
		TestValidation.IsTrue(createResource1, 
				"Succesfully created Equipment Instance - "+newEquipmentInstanceValue1+" for category - " + equipmentCategoryValue+ " and linked with Location -" +newlocationInstanceValue1, 
				"Could NOT create Equipment Instance - "+newEquipmentInstanceValue1+" for category - " + equipmentCategoryValue);

		//FORM - Creation and Release

		newcheckFormName1	= CommonMethods.dynamicText("16792_Automation_CheckForm");
		numFieldName    = "Numeric Field";

		HashMap<String, List<String>> fields = new HashMap<String, List<String>>();
		fields.put(FIELD_TYPES.NUMERIC, Arrays.asList(numFieldName));

		FormFieldParams ffp = new FormFieldParams();
		ffp.FieldDetails = fields;

		HashMap<String, List<String>> resource = new HashMap<String, List<String>>();
		resource.put(FORMRESOURCETYPES.EQUIPMENT, Arrays.asList(equipmentCategoryValue));

		FormDesignParams fdpDets = new FormDesignParams();
		fdpDets.FormType = FORMTYPES.CHECK;
		fdpDets.FormName = newcheckFormName1;
		fdpDets.SelectResources = resource;
		fdpDets.DesignFields = Arrays.asList(ffp);
		fdpDets.ReleaseForm = true;

		hp.clickFormDesignerMenu();
		boolean createAndReleaseForm = fdp.createAndReleaseForm(fdpDets);
		TestValidation.IsTrue(createAndReleaseForm,
				"Able to create and release form:" + newcheckFormName1,
				"Could NOT able to create and release form:" + newcheckFormName1);

		fbp = hp.clickFSQABrowserMenu();
		boolean navigateToforms = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.EQUIPMENT,FSQATAB.FORMS);
		TestValidation.IsTrue(navigateToforms, 
				"For Equipment category, NAVIGATED to FSQABrowser > Forms tab", 
				"Failed to navigate to FSQABrowser > Forms tab");

		boolean openAndApplySettings = fbp.openAndApplySettingsForColumn(COLUMNHEADER.FORMNAME, COLUMNSETTING.FILTER, newcheckFormName1);
		TestValidation.IsTrue(openAndApplySettings, 
				"Searched form - "+newcheckFormName1, 
				"Could NOT search the form - "+newcheckFormName1);

		boolean verifyForm = fbp.verifyFormIsDisplayed(newcheckFormName1);
		TestValidation.IsTrue(verifyForm, 
				"VERIFIED form "+newcheckFormName1+" is Displayed in FSQA browser", 
				"FAILED to verify form in FSQA browser");

		boolean logoutUser = hp.userLogout();
		TestValidation.IsTrue(logoutUser, 
				"Logged out with user " + adminUsername, 
				"Could NOT logout with user " + adminUsername);

		boolean loginNewUser = lp.loginAfterUserCreation(newUserUN1, GenericPassword, GenericNewPassword);
		TestValidation.IsTrue(loginNewUser, 
				"Logged In with created user - " + newUserUN1, 
				"Could NOT login with user - " + newUserUN1);

		fbp = hp.clickFSQABrowserMenu();
		boolean navigateToforms2 = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.EQUIPMENT,FSQATAB.FORMS);
		TestValidation.IsTrue(navigateToforms2, 
				"For Equipment category, NAVIGATED to FSQABrowser > Forms tab", 
				"Failed to navigate to FSQABrowser > Forms tab");

		boolean applyFilterAndOpenForm = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, newcheckFormName1);
		TestValidation.IsTrue(applyFilterAndOpenForm, 
				"Selected & opened the form - "+newcheckFormName1, 
				"Could NOT open the form - "+newcheckFormName1);

		//Save form
		LinkedHashMap<String, List<String>> fillDetails = new LinkedHashMap<String, List<String>>();
		fillDetails.put("Numeric Field", Arrays.asList("10"));

		FormDetails fd = new FormDetails();
		fd.fieldDetails = fillDetails;
		fd.locationName = newlocationInstanceValue1;
		fd.resourceName = newEquipmentInstanceValue1;
		fd.isSubmit = false;
		FormOperations fo = new FormOperations(driver);
		boolean save = fo.submitData(fd);
		TestValidation.IsTrue(save, "Successfully saved Form -  " + newcheckFormName1, "Failed to save form " + newcheckFormName1);

		boolean verifySavedForm = fbp.verifyFormIsDisplayedInSavedForms(newcheckFormName1);
		TestValidation.IsTrue(verifySavedForm, 
				"Successfully verified Form -  " + newcheckFormName1+ " in Saved Forms", 
				"Failed to verify form " + newcheckFormName1+" in Saved Forms");

		boolean logout = hp.userLogout();
		TestValidation.IsTrue(logout, 
				"Logged out with user " + newUserUN1, 
				"Could NOT logout with user " + newUserUN1);

		hp = lp.performLogin(adminUsername, adminPassword);
		TestValidation.Equals(hp.error, 
				false, 
				"Login with superadmin user - "+adminUsername, 
				"Could Not Login with superadmin user - "+adminUsername); 

		mlp = hp.clickLocationsMenu();
		TestValidation.Equals(mlp.error, 
				false, 
				"Opened Manage Location", 
				"Could Not Open Manage Location");

		boolean findAndClickLoc1 = mlp.scrollAndFindLocationInstance(locationCategoryValue, newlocationInstanceValue1);
		TestValidation.IsTrue(findAndClickLoc1, 
				"Succesfully clicked Location Category and Location Instance " + newlocationInstanceValue1, 
				"FAIL to click Location Category and Location Instance " + newlocationInstanceValue1);

		boolean selectTab  = mlp.selectTab(SelectTAB.EQUIPMENT);
		TestValidation.IsTrue(selectTab, 
				"Succesfully selected EQUIPMENT tab", 
				"FAIL to select EQUIPMENT tab");

		boolean searchResource = mlp.searchResource(newEquipmentInstanceValue1);
		TestValidation.IsTrue(searchResource, 
				"Succesfully searched Resource: "+ newEquipmentInstanceValue1,
				"FAIL to search Resource: "+ newEquipmentInstanceValue1);

		boolean unlinkResource = mlp.selectFirstCheckboxForResourceCategory(SelectTAB.EQUIPMENT);
		TestValidation.IsTrue(unlinkResource, 
				"Succesfully Unlinked Resource: "+ newEquipmentInstanceValue1+" associated with saved form",
				"FAIL to Unlink Resource: "+ newEquipmentInstanceValue1+" associated with saved form");

		boolean logout1 = hp.userLogout();
		TestValidation.IsTrue(logout1, 
				"Logged out with superadmin ", 
				"Could NOT logout with superadmin ");

		hp = lp.performLogin(newUserUN1, GenericNewPassword);
		TestValidation.Equals(hp.error, 
				false, 
				"Login with user - "+newUserUN1, 
				"Could Not Login with user - "+newUserUN1);

		fbp = hp.clickFSQABrowserMenu();
		boolean navigateToforms1 = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.EQUIPMENT,FSQATAB.FORMS);
		TestValidation.IsTrue(navigateToforms1, 
				"For Equipment category, NAVIGATED to FSQABrowser > Forms tab", 
				"Failed to navigate to FSQABrowser > Forms tab");

		boolean openForm = fbp.openFormInSavedForms(newcheckFormName1);
		TestValidation.IsTrue(openForm, 
				"Selected & opened Saved form - "+newcheckFormName1, 
				"Could NOT open saved form - "+newcheckFormName1);

		//	FormOperations fo1 = new FormOperations(driver);
		//	boolean submitForm = fo1.clickSubmitThenAlertOkBtn();

		hp.userLogout();
		lp.performLogin(adminUsername, adminPassword);

	}
	@Test(description = "15319-Data Security Gaps - Records Tab- Users can see a a\r\n"
			+ "submitted form if they have access to any of the resources associated with the form")
	public void TestCase_17321() {
		hp.userLogout();
		lp.performLogin(adminUsername, adminPassword);

		userLocation.add(newlocationInstanceValue1);
		userRole.add("SuperAdmin");
		UserManagerPage ump = hp.clickUsersMenu();
		UsrDetailParams udp = new UsrDetailParams();
		udp.Username = userUN1;
		udp.Password = GenericPassword;
		udp.FirstName = userFN;
		udp.LastName = userLN;
		udp.Email = "test@test.com";
		udp.Timezone = TIMEZONE.REPUBLICOFINDIA;
		udp.Locations = userLocation;
		udp.Roles = userRole;
		boolean userCreation = ump.createInternalUser(udp);
		TestValidation.IsTrue(userCreation, 
				"Succesfully created User  - " +userUN1, 
				"Could NOT create user - " + userUN1 );

		//Resource creation - ITEM
		HashMap<String,Boolean> itemInstance = new HashMap<String, Boolean>();
		itemInstance.put(itemInstanceValue, true);

		ResourceDetailParams rd1 = new ResourceDetailParams();
		rd1.CategoryName = itemCategoryValue;
		rd1.CategoryType = RESOURCETYPES.ITEMS;
		rd1.NumberFieldValue = 15;
		rd1.TextFieldValue = "LMN";
		rd1.InstanceNames = itemInstance;
		rd1.LocationInstanceValue = newlocationInstanceValue1;

		mrp = hp.clickResourcesMenu();
		TestValidation.Equals(mrp.error, 
				false, 
				"Opened Manage Resource", 
				"Could Not Open Manage Resource"); 

		boolean createResource1 = mrp.createResourceLinkLocation(rd1);
		TestValidation.IsTrue(createResource1, 
				"Succesfully created Item Instance - "+itemInstanceValue+" for category - " + itemCategoryValue+ " and linked with Location -" +newlocationInstanceValue1, 
				"Could NOT create Item Instance - "+itemInstanceValue+" for category - " + itemCategoryValue);

		//FORM - Creation and Release

		checkFormName17321	= CommonMethods.dynamicText("17321_Automation_CheckForm");
		numFieldName    = "Numeric Field";

		HashMap<String, List<String>> fields = new HashMap<String, List<String>>();
		fields.put(FIELD_TYPES.NUMERIC, Arrays.asList(numFieldName));

		FormFieldParams ffp = new FormFieldParams();
		ffp.FieldDetails = fields;

		HashMap<String, List<String>> resource = new HashMap<String, List<String>>();
		resource.put(FORMRESOURCETYPES.ITEMS, Arrays.asList(itemCategoryValue));

		FormDesignParams fdpDets = new FormDesignParams();
		fdpDets.FormType = FORMTYPES.CHECK;
		fdpDets.FormName = checkFormName17321;
		fdpDets.SelectResources = resource;
		fdpDets.DesignFields = Arrays.asList(ffp);
		fdpDets.ReleaseForm = true;

		hp.clickFormDesignerMenu();
		boolean createAndReleaseForm = fdp.createAndReleaseForm(fdpDets);
		TestValidation.IsTrue(createAndReleaseForm,
				"Able to create and release form: " + checkFormName17321,
				"Could NOT able to create and release forms:" + checkFormName17321);

		//Create Questionnaire Form and Release
		questionnaireFormName = CommonMethods.dynamicText("Auto_Quest_Form");
		numericFieldName = "Numeric Field";

		HashMap<String, List<String>> qstSecFields = new HashMap<String, List<String>>();
		qstSecFields.put(FIELD_TYPES.NUMERIC, Arrays.asList(numericFieldName));

		FormFieldParams ffpSecQst = new FormFieldParams();
		ffpSecQst.FieldDetails = qstSecFields;

		HashMap<String, List<String>> resources = new HashMap<String, List<String>>();
		resources.put(FORMRESOURCETYPES.ITEMS, Arrays.asList(itemCategoryValue));

		FormDesignParams fdpQst = new FormDesignParams();
		fdpQst.FormType = FORMTYPES.QUESTIONNAIRE;
		fdpQst.FormName = questionnaireFormName;
		fdpQst.SelectResources = resources;
		fdpQst.DesignFields = Arrays.asList(ffpSecQst);
		fdpQst.ReleaseForm = true;

		hp.clickFormDesignerMenu();
		boolean createAndReleaseQuestForm = fdp.createAndReleaseForm(fdpQst);
		TestValidation.IsTrue(createAndReleaseQuestForm,
				"Able to create and release form:" + questionnaireFormName,
				"Could NOT able to create and release forms:" + questionnaireFormName);

		//Create Audit Form and Release
		auditFormName = CommonMethods.dynamicText("Automation_AuditForm");
		singleLineTxtFN = "Text";
		section = "Section";

		HashMap<String, List<String>> selectResources = new HashMap<String, List<String>>();
		selectResources.put(FORMRESOURCETYPES.ITEMS,Arrays.asList(itemCategoryValue));

		HashMap<String,List<String>> fieldDetails = new HashMap<String,List<String>>();
		fieldDetails.put(FIELD_TYPES.SINGLE_LINE_TEXT, Arrays.asList(singleLineTxtFN));

		FormFieldParams ffparams = new FormFieldParams();
		ffparams.FieldDetails = fieldDetails;		
		ffparams.SectionName = section;

		FormDesignParams fdparams = new FormDesignParams();
		fdparams.FormType = FORMTYPES.AUDIT;
		fdparams.FormName = auditFormName;
		fdparams.SelectResources = selectResources;
		fdparams.DesignFields = Arrays.asList(ffparams);
		fdparams.ReleaseForm = true;

		hp.clickFormDesignerMenu();
		boolean createAndReleaseAuditForm = fdp.createAndReleaseForm(fdparams);
		TestValidation.IsTrue(createAndReleaseAuditForm,
				"Able to create and release form:" + auditFormName,
				"Could NOT able to create and release form:" + auditFormName);

		fbp = hp.clickFSQABrowserMenu();
		boolean navigateToforms = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.ITEMS,FSQATAB.FORMS);
		TestValidation.IsTrue(navigateToforms, 
				"For Items category, NAVIGATED to FSQABrowser > Forms tab", 
				"Failed to navigate to FSQABrowser > Forms tab");

		boolean openAndApplySettings = fbp.openAndApplySettingsForColumn(COLUMNHEADER.FORMNAME, COLUMNSETTING.FILTER, checkFormName17321);
		TestValidation.IsTrue(openAndApplySettings, 
				"Searched form - "+checkFormName17321, 
				"Could NOT search the form - "+checkFormName17321);

		boolean verifyForm = fbp.verifyFormIsDisplayed(checkFormName17321);
		TestValidation.IsTrue(verifyForm, 
				"VERIFIED form "+checkFormName17321+" is Displayed in FSQA browser", 
				"FAILED to verify form in FSQA browser");

		boolean openAndApplySettings1 = fbp.openAndApplySettingsForColumn(COLUMNHEADER.FORMNAME, COLUMNSETTING.FILTER, questionnaireFormName);
		TestValidation.IsTrue(openAndApplySettings1, 
				"Searched form - "+questionnaireFormName, 
				"Could NOT search the form - "+questionnaireFormName);

		boolean verifyForm1 = fbp.verifyFormIsDisplayed(questionnaireFormName);
		TestValidation.IsTrue(verifyForm1, 
				"VERIFIED form "+questionnaireFormName+" is Displayed in FSQA browser", 
				"FAILED to verify form in FSQA browser");

		boolean openAndApplySettings2 = fbp.openAndApplySettingsForColumn(COLUMNHEADER.FORMNAME, COLUMNSETTING.FILTER, auditFormName);
		TestValidation.IsTrue(openAndApplySettings2, 
				"Searched form - "+auditFormName, 
				"Could NOT search the form - "+auditFormName);

		boolean verifyForm2 = fbp.verifyFormIsDisplayed(auditFormName);
		TestValidation.IsTrue(verifyForm2, 
				"VERIFIED form "+auditFormName+" is Displayed in FSQA browser", 
				"FAILED to verify form in FSQA browser");

		boolean logoutUser = hp.userLogout();
		TestValidation.IsTrue(logoutUser, 
				"Logged out with user " + adminUsername, 
				"Could NOT logout with user " + adminUsername);

		boolean loginNewUser = lp.loginAfterUserCreation(userUN1, GenericPassword, GenericNewPassword);
		TestValidation.IsTrue(loginNewUser, 
				"Logged In with user - " + userUN1, 
				"Could NOT login user - " + userUN1);

		fbp = hp.clickFSQABrowserMenu();
		boolean navigateToforms2 = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.ITEMS,FSQATAB.FORMS);
		TestValidation.IsTrue(navigateToforms2, 
				"For Items category, NAVIGATED to FSQABrowser > Forms tab", 
				"Failed to navigate to FSQABrowser > Forms tab");

		boolean applyFilterAndOpenForm = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, checkFormName17321);
		TestValidation.IsTrue(applyFilterAndOpenForm, 
				"Selected & opened the form - "+checkFormName17321, 
				"Could NOT open the form - "+checkFormName17321);

		//Submit form
		LinkedHashMap<String, List<String>> fillDetails = new LinkedHashMap<String, List<String>>();
		fillDetails.put("Numeric Field", Arrays.asList("10"));

		FormDetails fd = new FormDetails();
		fd.fieldDetails = fillDetails;
		fd.locationName = newlocationInstanceValue1;
		fd.resourceName = itemInstanceValue;
		fd.isSubmit = true;
		FormOperations fo = new FormOperations(driver);
		boolean submit = fo.fillAndSubmitData(fd);
		TestValidation.IsTrue(submit, "Form submitted " + checkFormName17321, "Failed to submit form " + checkFormName17321);

		boolean applyFilterAndOpenForm1 = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, questionnaireFormName);
		TestValidation.IsTrue(applyFilterAndOpenForm1, 
				"Selected & opened the form - "+questionnaireFormName, 
				"Could NOT open the form - "+questionnaireFormName);

		//Submit form
		LinkedHashMap<String, List<String>> fillDetails1 = new LinkedHashMap<String, List<String>>();
		fillDetails1.put("Numeric Field", Arrays.asList("10"));

		FormDetails fd1 = new FormDetails();
		fd1.fieldDetails = fillDetails1;
		fd1.locationName = newlocationInstanceValue1;
		fd1.resourceName = itemInstanceValue;
		fd1.isSubmit = true;
		FormOperations fo1 = new FormOperations(driver);
		boolean submit1 = fo1.fillAndSubmitData(fd1);
		TestValidation.IsTrue(submit1, "Form submitted " + questionnaireFormName, "Failed to submit form " + questionnaireFormName);

		boolean applyFilterAndOpenForm3 = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, auditFormName);
		TestValidation.IsTrue(applyFilterAndOpenForm3, 
				"Selected & opened the form - "+auditFormName, 
				"Could NOT open the form - "+auditFormName);

		//Submit form
		LinkedHashMap<String, List<String>> fillDetails2 = new LinkedHashMap<String, List<String>>();
		fillDetails2.put("Text", Arrays.asList("abc"));

		FormDetails fd2 = new FormDetails();
		fd2.fieldDetails = fillDetails2;
		fd2.locationName = newlocationInstanceValue1;
		fd2.resourceName = itemInstanceValue;
		fd2.isSubmit = true;
		FormOperations fo2 = new FormOperations(driver);
		boolean submit2 = fo2.fillAndSubmitData(fd2);
		TestValidation.IsTrue(submit2, "Form submitted " + auditFormName, "Failed to submit form " + auditFormName);

		boolean navigateToRecordsTab = fbp.navigateToFSQATab(FSQATAB.RECORDS);
		TestValidation.IsTrue(navigateToRecordsTab, 
				"NAVIGATED to FSQABrowser > Records tab", 
				"Failed to navigate to FSQABrowser > Records tab");

		boolean openAndApplySettings3 = fbp.openAndApplySettingsForColumn(COLUMNHEADER.FORMNAME, COLUMNSETTING.FILTER, checkFormName17321);
		TestValidation.IsTrue(openAndApplySettings3, 
				"Searched form - "+checkFormName17321, 
				"Could NOT search form - "+checkFormName17321);

		boolean verifyForm3 = fbp.verifyRecordIsDisplayed(checkFormName17321);
		TestValidation.IsTrue(verifyForm3, 
				"VERIFIED submitted form "+checkFormName17321+" is Displayed in Records tab", 
				"FAILED to verify submitted form in Records tab");

		boolean openAndApplySettings4 = fbp.openAndApplySettingsForColumn(COLUMNHEADER.FORMNAME, COLUMNSETTING.FILTER, auditFormName);
		TestValidation.IsTrue(openAndApplySettings4, 
				"Searched form - "+auditFormName, 
				"Could NOT search form - "+auditFormName);

		boolean verifyForm4 = fbp.verifyRecordIsDisplayed(auditFormName);
		TestValidation.IsTrue(verifyForm4, 
				"VERIFIED submitted form "+auditFormName+" is Displayed in Records tab", 
				"FAILED to verify submitted form in Records tab");


		boolean navigateToDocumentsTab = fbp.navigateToFSQATab(FSQATAB.DOCUMENTS);
		TestValidation.IsTrue(navigateToDocumentsTab, 
				"NAVIGATED to FSQABrowser > DOCUMENTS tab", 
				"Failed to navigate to FSQABrowser > DOCUMENTS tab");


		boolean verifyForm5 = fbp.verifyDocumentIsDisplayed(questionnaireFormName);
		TestValidation.IsTrue(verifyForm5, 
				"VERIFIED Submitted form "+questionnaireFormName+" is Displayed in FSQA browser - Documents Tab", 
				"FAILED to verify form in FSQA browser - Documents Tab");

		hp.userLogout();
		lp.performLogin(adminUsername, adminPassword);
	}
	@Test(description = "15319-Data Security Gaps - Records Tab- Users cannot see a a\r\n"
			+ "submitted form if they do not have access to any of the resources associated with\r\n"
			+ "the form")
	public void TestCase_17322() {
		hp.userLogout();
		lp.performLogin(adminUsername, adminPassword);

		userLocation.add(newlocationInstanceValue2);
		userRole.add("SuperAdmin");
		UserManagerPage ump = hp.clickUsersMenu();
		UsrDetailParams udp = new UsrDetailParams();
		udp.Username = userUN2;
		udp.Password = GenericPassword;
		udp.FirstName = userFN;
		udp.LastName = userLN;
		udp.Email = "test@test.com";
		udp.Timezone = TIMEZONE.REPUBLICOFINDIA;
		udp.Locations = userLocation;
		udp.Roles = userRole;
		boolean userCreation = ump.createInternalUser(udp);
		TestValidation.IsTrue(userCreation, 
				"Succesfully created User  - " +userUN2, 
				"Could NOT create user - " + userUN2 );

		//Resource creation - Suppliers
		HashMap<String,Boolean> itemInstance = new HashMap<String, Boolean>();
		itemInstance.put(supplierInstanceValue, true);

		ResourceDetailParams rd1 = new ResourceDetailParams();
		rd1.CategoryName = supplierCategoryValue;
		rd1.CategoryType = RESOURCETYPES.SUPPLIERS;
		rd1.NumberFieldValue = 15;
		rd1.TextFieldValue = "LMN";
		rd1.InstanceNames = itemInstance;
		rd1.LocationInstanceValue = newlocationInstanceValue2;

		mrp = hp.clickResourcesMenu();
		TestValidation.Equals(mrp.error, 
				false, 
				"Opened Manage Resource", 
				"Could Not Open Manage Resource"); 

		boolean createResource1 = mrp.createResourceLinkLocation(rd1);
		TestValidation.IsTrue(createResource1, 
				"Succesfully created Suppliers Instance - "+supplierInstanceValue+" for category - " + supplierCategoryValue+ " and linked with Location -" +newlocationInstanceValue2, 
				"Could NOT create Suppliers Instance - "+supplierCategoryValue+" for category - " + supplierCategoryValue);

		//FORM - Creation and Release

		checkFormName17322	= CommonMethods.dynamicText("17322_Automation_CheckForm");
		numFieldName    = "Numeric Field";

		HashMap<String, List<String>> fields = new HashMap<String, List<String>>();
		fields.put(FIELD_TYPES.NUMERIC, Arrays.asList(numFieldName));

		FormFieldParams ffp = new FormFieldParams();
		ffp.FieldDetails = fields;

		HashMap<String, List<String>> resource = new HashMap<String, List<String>>();
		resource.put(FORMRESOURCETYPES.SUPPLIERS, Arrays.asList(supplierCategoryValue));

		FormDesignParams fdpDets = new FormDesignParams();
		fdpDets.FormType = FORMTYPES.CHECK;
		fdpDets.FormName = checkFormName17322;
		fdpDets.SelectResources = resource;
		fdpDets.DesignFields = Arrays.asList(ffp);
		fdpDets.ReleaseForm = true;

		hp.clickFormDesignerMenu();
		boolean createAndReleaseForm = fdp.createAndReleaseForm(fdpDets);
		TestValidation.IsTrue(createAndReleaseForm,
				"Able to create and release form: " + checkFormName17322,
				"Could NOT able to create and release forms:" + checkFormName17322);

		//Create Questionnaire Form and Release
		questionnaireFormName17322 = CommonMethods.dynamicText("Auto_Quest_Form_17322");
		numericFieldName = "Numeric Field";

		HashMap<String, List<String>> qstSecFields = new HashMap<String, List<String>>();
		qstSecFields.put(FIELD_TYPES.NUMERIC, Arrays.asList(numericFieldName));

		FormFieldParams ffpSecQst = new FormFieldParams();
		ffpSecQst.FieldDetails = qstSecFields;

		HashMap<String, List<String>> resources = new HashMap<String, List<String>>();
		resources.put(FORMRESOURCETYPES.SUPPLIERS, Arrays.asList(supplierCategoryValue));

		FormDesignParams fdpQst = new FormDesignParams();
		fdpQst.FormType = FORMTYPES.QUESTIONNAIRE;
		fdpQst.FormName = questionnaireFormName17322;
		fdpQst.SelectResources = resources;
		fdpQst.DesignFields = Arrays.asList(ffpSecQst);
		fdpQst.ReleaseForm = true;

		hp.clickFormDesignerMenu();
		boolean createAndReleaseQuestForm = fdp.createAndReleaseForm(fdpQst);
		TestValidation.IsTrue(createAndReleaseQuestForm,
				"Able to create and release form:" + questionnaireFormName17322,
				"Could NOT able to create and release forms:" + questionnaireFormName17322);

		//Create Audit Form and Release
		auditFormName17322 = CommonMethods.dynamicText("Automation_AuditForm_17322");
		singleLineTxtFN = "Text";
		section = "Section";

		HashMap<String, List<String>> selectResources = new HashMap<String, List<String>>();
		selectResources.put(FORMRESOURCETYPES.SUPPLIERS,Arrays.asList(supplierCategoryValue));

		HashMap<String,List<String>> fieldDetails = new HashMap<String,List<String>>();
		fieldDetails.put(FIELD_TYPES.SINGLE_LINE_TEXT, Arrays.asList(singleLineTxtFN));

		FormFieldParams ffparams = new FormFieldParams();
		ffparams.FieldDetails = fieldDetails;		
		ffparams.SectionName = section;

		FormDesignParams fdparams = new FormDesignParams();
		fdparams.FormType = FORMTYPES.AUDIT;
		fdparams.FormName = auditFormName17322;
		fdparams.SelectResources = selectResources;
		fdparams.DesignFields = Arrays.asList(ffparams);
		fdparams.ReleaseForm = true;

		hp.clickFormDesignerMenu();
		boolean createAndReleaseAuditForm = fdp.createAndReleaseForm(fdparams);
		TestValidation.IsTrue(createAndReleaseAuditForm,
				"Able to create and release form:" + auditFormName17322,
				"Could NOT able to create and release form:" + auditFormName17322);

		fbp = hp.clickFSQABrowserMenu();
		boolean navigateToforms = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.SUPPLIERS,FSQATAB.FORMS);
		TestValidation.IsTrue(navigateToforms, 
				"For SUPPLIERS category, NAVIGATED to FSQABrowser > Forms tab", 
				"Failed to navigate to FSQABrowser > Forms tab");

		boolean openAndApplySettings = fbp.openAndApplySettingsForColumn(COLUMNHEADER.FORMNAME, COLUMNSETTING.FILTER, checkFormName17322);
		TestValidation.IsTrue(openAndApplySettings, 
				"Searched form - "+checkFormName17322, 
				"Could NOT search the form - "+checkFormName17322);

		boolean verifyForm = fbp.verifyFormIsDisplayed(checkFormName17322);
		TestValidation.IsTrue(verifyForm, 
				"VERIFIED form "+checkFormName17322+" is Displayed in FSQA browser", 
				"FAILED to verify form in FSQA browser");

		boolean openAndApplySettings1 = fbp.openAndApplySettingsForColumn(COLUMNHEADER.FORMNAME, COLUMNSETTING.FILTER, questionnaireFormName17322);
		TestValidation.IsTrue(openAndApplySettings1, 
				"Searched form - "+questionnaireFormName17322, 
				"Could NOT search the form - "+questionnaireFormName17322);

		boolean verifyForm1 = fbp.verifyFormIsDisplayed(questionnaireFormName17322);
		TestValidation.IsTrue(verifyForm1, 
				"VERIFIED form "+questionnaireFormName17322+" is Displayed in FSQA browser", 
				"FAILED to verify form in FSQA browser");

		boolean openAndApplySettings2 = fbp.openAndApplySettingsForColumn(COLUMNHEADER.FORMNAME, COLUMNSETTING.FILTER, auditFormName17322);
		TestValidation.IsTrue(openAndApplySettings2, 
				"Searched form - "+auditFormName17322, 
				"Could NOT search the form - "+auditFormName17322);

		boolean verifyForm2 = fbp.verifyFormIsDisplayed(auditFormName17322);
		TestValidation.IsTrue(verifyForm2, 
				"VERIFIED form "+auditFormName17322+" is Displayed in FSQA browser", 
				"FAILED to verify form in FSQA browser");

		boolean logoutUser = hp.userLogout();
		TestValidation.IsTrue(logoutUser, 
				"Logged out with user " + adminUsername, 
				"Could NOT logout with user " + adminUsername);

		boolean loginNewUser = lp.loginAfterUserCreation(userUN2, GenericPassword, GenericNewPassword);
		TestValidation.IsTrue(loginNewUser, 
				"Logged In with user - " + userUN2, 
				"Could NOT login user - " + userUN2);

		fbp = hp.clickFSQABrowserMenu();
		boolean navigateToforms2 = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.SUPPLIERS,FSQATAB.FORMS);
		TestValidation.IsTrue(navigateToforms2, 
				"For SUPPLIERS category, NAVIGATED to FSQABrowser > Forms tab", 
				"Failed to navigate to FSQABrowser > Forms tab");

		boolean applyFilterAndOpenForm = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, checkFormName17322);
		TestValidation.IsTrue(applyFilterAndOpenForm, 
				"Selected & opened the form - "+checkFormName17322, 
				"Could NOT open the form - "+checkFormName17322);

		//Submit form
		LinkedHashMap<String, List<String>> fillDetails = new LinkedHashMap<String, List<String>>();
		fillDetails.put("Numeric Field", Arrays.asList("10"));

		FormDetails fd = new FormDetails();
		fd.fieldDetails = fillDetails;
		fd.locationName = newlocationInstanceValue2;
		fd.resourceName = supplierInstanceValue;
		fd.isSubmit = true;
		FormOperations fo = new FormOperations(driver);
		boolean submit = fo.fillAndSubmitData(fd);
		TestValidation.IsTrue(submit, "Form submitted " + checkFormName17322, "Failed to submit form " + checkFormName17322);

		boolean applyFilterAndOpenForm1 = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, questionnaireFormName17322);
		TestValidation.IsTrue(applyFilterAndOpenForm1, 
				"Selected & opened the form - "+questionnaireFormName17322, 
				"Could NOT open the form - "+questionnaireFormName17322);

		//Submit form
		LinkedHashMap<String, List<String>> fillDetails1 = new LinkedHashMap<String, List<String>>();
		fillDetails1.put("Numeric Field", Arrays.asList("10"));

		FormDetails fd1 = new FormDetails();
		fd1.fieldDetails = fillDetails1;
		fd1.locationName = newlocationInstanceValue2;
		fd1.resourceName = supplierInstanceValue;
		fd1.isSubmit = true;
		FormOperations fo1 = new FormOperations(driver);
		boolean submit1 = fo1.fillAndSubmitData(fd1);
		TestValidation.IsTrue(submit1, "Form submitted " + questionnaireFormName17322, "Failed to submit form " + questionnaireFormName17322);

		boolean applyFilterAndOpenForm3 = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, auditFormName17322);
		TestValidation.IsTrue(applyFilterAndOpenForm3, 
				"Selected & opened the form - "+auditFormName17322, 
				"Could NOT open the form - "+auditFormName17322);

		//Submit form
		LinkedHashMap<String, List<String>> fillDetails2 = new LinkedHashMap<String, List<String>>();
		fillDetails2.put("Text", Arrays.asList("abc"));

		FormDetails fd2 = new FormDetails();
		fd2.fieldDetails = fillDetails2;
		fd2.locationName = newlocationInstanceValue2;
		fd2.resourceName = supplierInstanceValue;
		fd2.isSubmit = true;
		FormOperations fo2 = new FormOperations(driver);
		boolean submit2 = fo2.fillAndSubmitData(fd2);
		TestValidation.IsTrue(submit2, "Form submitted " + auditFormName17322, "Failed to submit form " + auditFormName17322);

		boolean navigateToRecordsTab = fbp.navigateToFSQATab(FSQATAB.RECORDS);
		TestValidation.IsTrue(navigateToRecordsTab, 
				"NAVIGATED to FSQABrowser > Records tab", 
				"Failed to navigate to FSQABrowser > Records tab");

		boolean openAndApplySettings3 = fbp.openAndApplySettingsForColumn(COLUMNHEADER.FORMNAME, COLUMNSETTING.FILTER, checkFormName17322);
		TestValidation.IsTrue(openAndApplySettings3, 
				"Searched form - "+checkFormName17322, 
				"Could NOT search form - "+checkFormName17322);

		boolean verifyForm3 = fbp.verifyRecordIsDisplayed(checkFormName17322);
		TestValidation.IsTrue(verifyForm3, 
				"VERIFIED submitted form "+checkFormName17322+" is Displayed in Records tab", 
				"FAILED to verify submitted form in Records tab");

		boolean openAndApplySettings4 = fbp.openAndApplySettingsForColumn(COLUMNHEADER.FORMNAME, COLUMNSETTING.FILTER, auditFormName17322);
		TestValidation.IsTrue(openAndApplySettings4, 
				"Searched form - "+auditFormName17322, 
				"Could NOT search form - "+auditFormName17322);

		boolean verifyForm4 = fbp.verifyRecordIsDisplayed(auditFormName17322);
		TestValidation.IsTrue(verifyForm4, 
				"VERIFIED submitted form "+auditFormName17322+" is Displayed in Records tab", 
				"FAILED to verify submitted form in Records tab");


		boolean navigateToDocumentsTab = fbp.navigateToFSQATab(FSQATAB.DOCUMENTS);
		TestValidation.IsTrue(navigateToDocumentsTab, 
				"NAVIGATED to FSQABrowser > DOCUMENTS tab", 
				"Failed to navigate to FSQABrowser > DOCUMENTS tab");


		boolean verifyForm5 = fbp.verifyDocumentIsDisplayed(questionnaireFormName17322);
		TestValidation.IsTrue(verifyForm5, 
				"VERIFIED Submitted form "+questionnaireFormName17322+" is Displayed in FSQA browser - Documents Tab", 
				"FAILED to verify form in FSQA browser - Documents Tab");

		mrp = hp.clickResourcesMenu();
		TestValidation.Equals(mrp.error, 
				false, 
				"Opened Manage Resource", 
				"Could Not Open Manage Resource"); 

		boolean searchAndDisableResource = mrp.searchAndDisableResource(RESOURCETYPES.SUPPLIERS, supplierInstanceValue);
		TestValidation.IsTrue(searchAndDisableResource, 
				"Succesfully Disabled Resource - "+supplierInstanceValue, 
				"Failed to Disable Resource - "+supplierInstanceValue);

		fbp = hp.clickFSQABrowserMenu();

		boolean navigateToRecordsTab1 = fbp.navigateToFSQATab(FSQATAB.RECORDS);
		TestValidation.IsTrue(navigateToRecordsTab1, 
				"NAVIGATED to FSQABrowser > Records tab", 
				"Failed to navigate to FSQABrowser > Records tab");

		boolean openAndApplySettings5 = fbp.openAndApplySettingsForColumn(COLUMNHEADER.FORMNAME, COLUMNSETTING.FILTER, checkFormName17322);
		TestValidation.IsTrue(openAndApplySettings5, 
				"Searched form - "+checkFormName17322, 
				"Could NOT search form - "+checkFormName17322);

		boolean verifyForm6 = fbp.verifyRecordIsDisplayed(checkFormName17322);
		TestValidation.IsTrue(verifyForm6, 
				"VERIFIED submitted form "+checkFormName17322+" is Displayed in Records tab", 
				"FAILED to verify submitted form is display in Records tab");

		boolean openAndApplySettings6 = fbp.openAndApplySettingsForColumn(COLUMNHEADER.FORMNAME, COLUMNSETTING.FILTER, auditFormName17322);
		TestValidation.IsTrue(openAndApplySettings6, 
				"Searched form - "+auditFormName17322, 
				"Could NOT search form - "+auditFormName17322);

		boolean verifyForm7 = fbp.verifyRecordIsDisplayed(auditFormName17322);
		TestValidation.IsTrue(verifyForm7, 
				"VERIFIED submitted form "+auditFormName17322+" is Displayed in Records tab", 
				"FAILED to verify submitted form in Records tab");


		boolean navigateToDocumentsTab1 = fbp.navigateToFSQATab(FSQATAB.DOCUMENTS);
		TestValidation.IsTrue(navigateToDocumentsTab1, 
				"NAVIGATED to FSQABrowser > DOCUMENTS tab", 
				"Failed to navigate to FSQABrowser > DOCUMENTS tab");


		boolean verifyForm8 = fbp.verifyDocumentIsDisplayed(questionnaireFormName17322);
		TestValidation.IsTrue(verifyForm8, 
				"VERIFIED Submitted form "+questionnaireFormName17322+" is Displayed in FSQA browser - Documents Tab", 
				"FAILED to verify form is display in FSQA browser - Documents Tab");
		hp.userLogout();
		lp.performLogin(adminUsername, adminPassword);
	}
	@Test(description = "17505-Data Security Gaps - FSQA Browser Tasks Tab")
	public void TestCase_17553() {
		hp.userLogout();
		hp = lp.performLogin(userUN, GenericNewPassword);
		TestValidation.Equals(hp.error, 
				false, 
				"Login with user - "+userUN, 
				"Could Not Login with user - "+userUN);

		fbp = hp.clickFSQABrowserMenu();
		TestValidation.Equals(fbp.error, 
				false, 
				"Opened FSQA Browser", 
				"Could Not Open FSQA Browser"); 

		boolean selectInst = fbp.selectDropDownValue(FORMRESOURCETYPES.ITEMS);
		TestValidation.IsTrue(selectInst, 
				"Selected ITEMS Category from drop down", 
				"Failed to select ITEMS Category from drop down");

		boolean searchAndSelectInst = fbp.searchSelect(itemInstanceValue);
		TestValidation.IsTrue(searchAndSelectInst, 
				"Succesfully searched and selected resource "+ itemInstanceValue, 
				"FAILED to search and select resource " + itemInstanceValue);

		boolean itemIsRevealed = fbp.selectLocationAndCheckLocationIsDisplayed(locationInstanceValue);
		TestValidation.IsTrue(itemIsRevealed, 
				"Succesfully verified ITEMS resource - "+itemInstanceValue+" is revealed to location " + locationInstanceValue, 
				"Failed to verify that ITEMS resource - "+itemInstanceValue+" is revealed to location "+ locationInstanceValue);

		boolean navigateToforms = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.ITEMS,FSQATAB.FORMS);
		TestValidation.IsTrue(navigateToforms, 
				"For SUPPLIERS category, NAVIGATED to FSQABrowser > Forms tab", 
				"Failed to navigate to FSQABrowser > Forms tab");

		boolean applyfilter = fbp.openAndApplySettingsForColumn(COLUMNHEADER.FORMNAME, COLUMNSETTING.FILTER,checkFormName);
		TestValidation.IsTrue(applyfilter, 
				"Applied Filter on" + COLUMNHEADER.FORMNAME, 
				"Failed to apply filter on" + COLUMNHEADER.FORMNAME);

		TASKDETAILS tsd = new TASKDETAILS();
		tsd.Location = locationInstanceValue;
		tsd.Resource = itemInstanceValue;
		tsd.TaskName = task17553;
		tsd.Workgroup = workGroupName;

		boolean assigntask = fbp.assignFormTask(tsd);
		TestValidation.IsTrue(assigntask, 
				"Form task assigned" + task17553, 
				"Failed to assign form task" + task17553);

		boolean navigate = fbp.navigateToFSQATab(FSQATAB.TASKS);
		TestValidation.IsTrue(navigate, 
				"Navigated to FSQABrowser>Tasks Tab", 
				"Failed to navigate to FSQABrowser>Tasks Tab");

		String taskname[] = {task17553};

		boolean viewtask = fbp.openAndApplySettingsForTaskColumn(COLUMNHEADER.TASKNAME, COLUMNSETTING.FILTER,taskname);
		TestValidation.IsTrue(viewtask, 
				"Task "+task17553+" is visible", 
				"Failed to verify tasks"+task17553);
		TASKDETAILS reassignTsd = new TASKDETAILS();
		reassignTsd.TaskName = task17553;
		reassignTsd.UpdatedTaskName = newTask17553;
		reassignTsd.Workgroup = newWorkGroupName;
		//reassignTsd.DueByDate = updatedDate;
		boolean reassignTask = fbp.reassignFormTask(reassignTsd);
		TestValidation.IsTrue(reassignTask, 
				"REASSIGNED form task - " + newTask17553, 
				"Failed to reassign form task - " + newTask17553);

		boolean viewtask1 = fbp.verifyTaskIsNotVisible(COLUMNHEADER.TASKNAME, COLUMNSETTING.FILTER,newTask17553);
		TestValidation.IsTrue(viewtask1, 
				"Verified Reassigned Task - "+newTask17553+" is Not visible as it does not have access to Resource this task is assigned to.", 
				"Failed to verify Reassigned task "+newTask17553+" is not visible");
		hp.userLogout();
		lp.performLogin(adminUsername, adminPassword);
	}

	@Test(description = "Data Security- Submit save form for disabled resource")
	public void TestCase_16794() {
		hp.userLogout();
		lp.performLogin(adminUsername, adminPassword);

		fbp = hp.clickFSQABrowserMenu();
		boolean navigateToforms = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.SUPPLIERS,FSQATAB.FORMS);
		TestValidation.IsTrue(navigateToforms, 
				"For SUPPLIERS category, NAVIGATED to FSQABrowser > Forms tab", 
				"Failed to navigate to FSQABrowser > Forms tab");

		boolean openAndApplySettings = fbp.openAndApplySettingsForColumn(COLUMNHEADER.FORMNAME, COLUMNSETTING.FILTER, checkFormName);
		TestValidation.IsTrue(openAndApplySettings, 
				"Searched form - "+checkFormName, 
				"Could NOT search the form - "+checkFormName);

		boolean verifyForm = fbp.verifyFormIsDisplayed(checkFormName);
		TestValidation.IsTrue(verifyForm, 
				"VERIFIED form "+checkFormName+" is Displayed in FSQA browser", 
				"FAILED to verify form in FSQA browser");

		boolean logoutUser = hp.userLogout();
		TestValidation.IsTrue(logoutUser, 
				"Logged out with user " + adminUsername, 
				"Could NOT logout with user " + adminUsername);

		boolean loginNewUser = lp.loginAfterUserCreation(userUN3, GenericPassword, GenericNewPassword);
		TestValidation.IsTrue(loginNewUser, 
				"Logged In with new user - " + userUN3, 
				"Could NOT login with user - " + userUN3);

		fbp = hp.clickFSQABrowserMenu();
		boolean navigateToforms2 = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.SUPPLIERS,FSQATAB.FORMS);
		TestValidation.IsTrue(navigateToforms2, 
				"For SUPPLIERS category, NAVIGATED to FSQABrowser > Forms tab", 
				"Failed to navigate to FSQABrowser > Forms tab");

		boolean applyFilterAndOpenForm = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, checkFormName);
		TestValidation.IsTrue(applyFilterAndOpenForm, 
				"Selected & opened the form - "+checkFormName, 
				"Could NOT open the form - "+checkFormName);

		//Save form
		LinkedHashMap<String, List<String>> fillDetails = new LinkedHashMap<String, List<String>>();
		fillDetails.put("Numeric Field", Arrays.asList("10"));

		FormDetails fd = new FormDetails();
		fd.fieldDetails = fillDetails;
		fd.locationName = locInstValue;
		fd.resourceName = supplierInstanceValue;
		fd.isSubmit = false;
		FormOperations fo = new FormOperations(driver);
		boolean save = fo.submitData(fd);
		TestValidation.IsTrue(save, "Successfully saved Form -  " + checkFormName, "Failed to save form " + checkFormName);

		boolean verifySavedForm = fbp.verifyFormIsDisplayedInSavedForms(checkFormName);
		TestValidation.IsTrue(verifySavedForm, 
				"Successfully verified Form -  " + checkFormName+ " in Saved Forms", 
				"Failed to verify form " + checkFormName+" in Saved Forms");

		boolean logoutUser1 = hp.userLogout();
		TestValidation.IsTrue(logoutUser1, 
				"Logged out with user " + userUN, 
				"Could NOT logout with user " + userUN);

		hp = lp.performLogin(adminUsername, adminPassword);
		TestValidation.Equals(hp.error, 
				false, 
				"Login with superadmin - "+adminUsername, 
				"Could Not Login with superadmin - "+adminUsername);

		hp.clickResourcesMenu();
		boolean searchAndDisableResource = mrp.searchAndDisableResource(RESOURCETYPES.SUPPLIERS, supplierInstanceValue);
		TestValidation.IsTrue(searchAndDisableResource, 
				"Succesfully Disabled Resource - "+supplierInstanceValue, 
				"Failed to Disable Resource - "+supplierInstanceValue);

		boolean verifyResourceIsDisable = mrp.verifyResourceIsDisabled(supplierInstanceValue);
		TestValidation.IsTrue(verifyResourceIsDisable, 
				"Verified Resource - "+supplierInstanceValue+ "is Disabled in Manage Resource Tools", 
				"Failed to verify Resource - "+supplierInstanceValue+" is disable in Manage Resource Tools ");

		boolean logoutUser2 = hp.userLogout();
		TestValidation.IsTrue(logoutUser2, 
				"Logged out with user " + adminUsername, 
				"Could NOT logout with user " + adminUsername);

		hp = lp.performLogin(userUN3, GenericNewPassword);
		TestValidation.Equals(hp.error, 
				false, 
				"Login with user - "+userUN3, 
				"Could Not Login with user - "+userUN3);

		boolean navigateToforms3 = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.SUPPLIERS,FSQATAB.FORMS);
		TestValidation.IsTrue(navigateToforms3, 
				"For SUPPLIERS category, NAVIGATED to FSQABrowser > Forms tab", 
				"Failed to navigate to FSQABrowser > Forms tab");

		boolean verifySavedForm1 = fbp.verifyFormIsDisplayedInSavedForms(checkFormName);
		TestValidation.IsTrue(verifySavedForm1, 
				"Successfully verified Form -  " + checkFormName+ " which has disabled resource - "+supplierInstanceValue+" in Saved Forms is Displaying to user", 
				"Failed to verify form " + checkFormName+" in Saved Forms");
		hp.userLogout();
		lp.performLogin(adminUsername, adminPassword);
	}

	@Test(description = "17503-Data Security Gaps - FSQA Browser Documents Tab - Both\r\n"
			+ "the Manage Links Popup and the Links Tab of the document viewer are affected")
	public void TestCase_17556() {
		hp.userLogout();
		hp = lp.performLogin(userUN, GenericNewPassword);
		TestValidation.Equals(hp.error, 
				false, 
				"Login with user - "+userUN, 
				"Could Not Login with user - "+userUN);

		fbp = hp.clickFSQABrowserMenu();
		TestValidation.Equals(fbp.error, 
				false, 
				"Opened FSQA Browser", 
				"Could Not Open FSQA Browser"); 

		boolean selectInst = fbp.selectDropDownValue(FORMRESOURCETYPES.SUPPLIERS);
		TestValidation.IsTrue(selectInst, 
				"Selected SUPPLIERS Category from drop down", 
				"Failed to select ITEMS Category from drop down");

		boolean searchAndSelectInst = fbp.searchSelect(newSupplierInstanceValue);
		TestValidation.IsTrue(searchAndSelectInst, 
				"Succesfully searched and selected resource "+ newSupplierInstanceValue, 
				"FAILED to search and select resource " + newSupplierInstanceValue);

		boolean itemIsRevealed = fbp.selectLocationAndCheckLocationIsDisplayed(locationInstanceValue);
		TestValidation.IsTrue(itemIsRevealed, 
				"Succesfully verified SUPPLIERS resource - "+newSupplierInstanceValue+" is revealed to location " + locationInstanceValue, 
				"Failed to verify that SUPPLIERS resource - "+newSupplierInstanceValue+" is revealed to location "+ locationInstanceValue);

		dmp = hp.clickdocumentsmenu();
		TestValidation.Equals(dmp.error, 
				false, 
				"Opened Documents Management", 
				"Could Not Open Documents Management");

		boolean selectDocType = dmp.searchSelect(questionnaireFormName17556);
		TestValidation.IsTrue(selectDocType, 
				"Searched and selected Document Type -> " + questionnaireFormName17556,
				"Could NOT search and select Document Type ->  " + questionnaireFormName17556);

		boolean isDocPresent =  dmp.isDocumentPresent(questionnaireFormName17556);
		TestValidation.IsTrue(isDocPresent, 
				"Document Presence VERIFIED for document -> " + questionnaireFormName17556,
				"Could NOT VERIFY presence of document -> " + questionnaireFormName17556);

		boolean selectDocOpenPopUp =  dmp.selectDocumentAndOpenManageLinks(questionnaireFormName17556);
		TestValidation.IsTrue(selectDocOpenPopUp, 
				"Succesfully selected document -> " + questionnaireFormName17556 + " and Opened Manage Links PopUp ",
				"Fail to select document -> " + questionnaireFormName17556 + " and Open Manage Links PopUp ");

		boolean linkResource =  dmp.selectCategorylinkResource(SelectTAB.SUPPLIERS,newSupplierInstanceValue);
		TestValidation.IsTrue(linkResource, 
				"Succesfully linked document -> " + questionnaireFormName17556 + " with Resource - "+newSupplierInstanceValue,
				"Fail to link document -> " + questionnaireFormName17556 + " with Resource "+newSupplierInstanceValue);

		hp.clickFSQABrowserMenu();
		hp.clickdocumentsmenu();

		boolean selectResource = dmp.selectResourceFrmDrpDwn("Suppliers");
		TestValidation.IsTrue(selectResource,
				"SELECTED Dropdown Suppliers ",
				"Could NOT SELECT Dropdown Suppliers");


		boolean selectResourceInstance = dmp.searchSelect(newSupplierInstanceValue);
		TestValidation.IsTrue(selectResourceInstance, 
				"Searched and selected instance -> " + newSupplierInstanceValue,
				"Could NOT search and select instance ->  " + newSupplierInstanceValue);

		boolean isSingleDocPresent =  dmp.verifySingleDocIsPresent(questionnaireFormName17556);
		TestValidation.IsTrue(isSingleDocPresent, 
				"VERIFIED document -> " + questionnaireFormName17556+ " is linked with associated Resource - "+newSupplierInstanceValue+" and multiple Document is not get generated",
				"Fail to verify document -> " + questionnaireFormName17556+ " is link with associated Resource - "+newSupplierInstanceValue);

		hp.userLogout();
		lp.performLogin(adminUsername, adminPassword);
	}
	@Test(description = "17506-Data Security Gaps - My Inbox")
	public void TestCase_17658() {
		hp.userLogout();
		lp.performLogin(adminUsername, adminPassword);

		locationCategoryValue17658 = CommonMethods.dynamicText("Loc_Cat_17658");
		customerCategoryValue17658 = CommonMethods.dynamicText("Cust_Cat_17658");
		equipmentCategoryValue17658 = CommonMethods.dynamicText("Equip_Cat_17658");
		locationInstanceValue17658 = CommonMethods.dynamicText("Loc_Inst_17658");
		newLocationInstanceValue17658 = CommonMethods.dynamicText("New_Loc_Inst_17658");
		customerInstanceValue17658 = CommonMethods.dynamicText("Cust_Inst_17658");
		equipmentInstanceValue17658 = CommonMethods.dynamicText("Equip_Inst_17658");
		userUN17658 = CommonMethods.dynamicText("UN_17658");
		wgName = CommonMethods.dynamicText("WG_17658");
		task17658 = CommonMethods.dynamicText("Task_17658");
		newTask17658 = CommonMethods.dynamicText("New_Task_17658");


		//Category creation
		HashMap<String,String> categories = new HashMap<String, String>();
		categories.put(CATEGORYTYPES.LOCATION, locationCategoryValue17658);
		categories.put(CATEGORYTYPES.CUSTOMERS, customerCategoryValue17658);
		categories.put(CATEGORYTYPES.EQUIPMENT, equipmentCategoryValue17658);

		ResourceDesignerPage rdp = hp.clickResourceDesignerMenu();

		boolean createCategory1 = rdp.createCategory(categories);
		TestValidation.IsTrue(createCategory1, 
				"Succesfully created Resource categories for Location - " +locationCategoryValue17658+ " , Customer - "+ customerCategoryValue17658+  " , Equipment - " +equipmentCategoryValue17658, 
				"Could NOT create Resource categories Location - " +locationCategoryValue17658+ " , Customer - "+ customerCategoryValue17658+  " , Equipment - " +equipmentCategoryValue17658);



		mrp = hp.clickResourcesMenu();
		TestValidation.Equals(mrp.error, 
				false, 
				"Opened Manage Resource", 
				"Could Not Open Manage Resource"); 

		//Resource creation - Customer
		ResourceDetailParams rd1 = new ResourceDetailParams();
		rd1.CategoryName = customerCategoryValue17658;
		rd1.CategoryType = RESOURCETYPES.CUSTOMERS;
		rd1.NumberFieldValue = 15;
		rd1.TextFieldValue = "LMN";
		rd1.InstanceName = customerInstanceValue17658;
		rd1.InstanceStatus = true;

		boolean createResource1 = mrp.createResource(rd1);
		TestValidation.IsTrue(createResource1, 
				"Succesfully created Customer Instance - "+customerInstanceValue17658+" for category - " + customerCategoryValue17658, 
				"Could NOT create Customer Instance - "+customerInstanceValue17658+" for category - " + customerCategoryValue17658);


		//Location instance creation
		HashMap<String,Boolean> locInstance = new HashMap<String, Boolean>();
		locInstance.put(locationInstanceValue17658, true);

		LocationInstanceParams lip = new LocationInstanceParams();
		lip.CategoryName = locationCategoryValue17658;
		lip.NumberFieldValue = 10;
		lip.TextFieldValue = "XYZ";
		lip.InstanceName = locInstance;
		ManageLocationPage mlp = hp.clickLocationsMenu();

		boolean createLocInst = mlp.createLocation(lip);
		TestValidation.IsTrue(createLocInst, 
				"Succesfully created Location Instance  - " +locationInstanceValue17658, 
				"Could NOT create Location instance - "+locationInstanceValue17658);

		HashMap<String, List<String>> resourceInstances = new LinkedHashMap<String, List<String>>();
		resourceInstances.put(SelectTAB.CUSTOMERS, Arrays.asList(customerInstanceValue17658));

		boolean linkResToLoc = mlp.linkResourceToLocation(resourceInstances);
		TestValidation.IsTrue(linkResToLoc, 
				"Succesfully linked Resource - "+customerInstanceValue17658+" with Location Instance  - "+locationInstanceValue17658, 
				"Could NOT link Resource - "+customerInstanceValue17658+" with Location instance - "+locationInstanceValue17658 );

		mrp = hp.clickResourcesMenu();
		TestValidation.Equals(mrp.error, 
				false, 
				"Opened Manage Resource", 
				"Could Not Open Manage Resource"); 
		//Resource creation - Equipment

		ResourceDetailParams rd2 = new ResourceDetailParams();
		rd2.CategoryName = equipmentCategoryValue17658;
		rd2.CategoryType = RESOURCETYPES.EQUIPMENT;
		rd2.NumberFieldValue = 15;
		rd2.TextFieldValue = "LMN";
		rd2.InstanceName = equipmentInstanceValue17658;
		rd2.InstanceStatus = true;

		boolean createResource2 = mrp.createResource(rd2);
		TestValidation.IsTrue(createResource2, 
				"Succesfully created Equipment Instance - "+equipmentInstanceValue17658+" for category - " + equipmentCategoryValue17658, 
				"Could NOT create Equipment Instance - "+equipmentInstanceValue17658+" for category - " + equipmentCategoryValue17658);

		//Location instance creation
		HashMap<String,Boolean> locInstance1 = new HashMap<String, Boolean>();
		locInstance1.put(newLocationInstanceValue17658, true);

		LocationInstanceParams lip1 = new LocationInstanceParams();
		lip1.CategoryName = locationCategoryValue17658;
		lip1.NumberFieldValue = 10;
		lip1.TextFieldValue = "XYZ";
		lip1.InstanceName = locInstance1;
		hp.clickLocationsMenu();

		boolean createLocInst1 = mlp.createLocation(lip1);
		TestValidation.IsTrue(createLocInst1, 
				"Succesfully created Location Instance  - "+newLocationInstanceValue17658, 
				"Could NOT create Location instance - "+newLocationInstanceValue17658 );

		HashMap<String, List<String>> resourceInstances1 = new LinkedHashMap<String, List<String>>();
		resourceInstances1.put(SelectTAB.EQUIPMENT, Arrays.asList(equipmentInstanceValue17658));

		boolean linkResToLoc1 = mlp.linkResourceToLocation(resourceInstances1);
		TestValidation.IsTrue(linkResToLoc1, 
				"Succesfully linked Resource - "+equipmentInstanceValue17658+" with Location Instance  - "+newLocationInstanceValue17658, 
				"Could NOT link Resource - "+equipmentInstanceValue17658+" with Location instance - "+newLocationInstanceValue17658 );

		//FORM - Creation and Release

		checkFormName17658	= CommonMethods.dynamicText("17658_Automation_CheckForm");
		numFieldName    = "Numeric Field";
		HashMap<String, List<String>> fields = new HashMap<String, List<String>>();
		fields.put(FIELD_TYPES.NUMERIC, Arrays.asList(numFieldName));
		FormFieldParams ffp = new FormFieldParams();
		ffp.FieldDetails = fields;
		HashMap<String, List<String>> resource = new HashMap<String, List<String>>();
		resource.put(FORMRESOURCETYPES.CUSTOMERS, Arrays.asList(customerCategoryValue17658));
		resource.put(FORMRESOURCETYPES.EQUIPMENT, Arrays.asList(equipmentCategoryValue17658));

		FormDesignParams fdpDets = new FormDesignParams();
		fdpDets.FormType = FORMTYPES.CHECK;
		fdpDets.FormName = checkFormName17658;
		fdpDets.SelectResources = resource;
		fdpDets.DesignFields = Arrays.asList(ffp);
		fdpDets.ReleaseForm = true;

		hp.clickFormDesignerMenu();
		boolean createAndReleaseForm = fdp.createAndReleaseForm(fdpDets);
		TestValidation.IsTrue(createAndReleaseForm,
				"Able to create and release form:" + checkFormName17658,
				"Could NOT able to create and release form:" + checkFormName17658);

		//user Creation
		UsrDetailParams udp = new UsrDetailParams();
		udp.Username = userUN17658;
		udp.Password = GenericPassword;
		udp.FirstName = userFN;
		udp.LastName = userLN;
		udp.Email = "test@test.com";
		udp.Timezone = TIMEZONE.REPUBLICOFINDIA;
		udp.Locations = Arrays.asList(locationInstanceValue17658);
		udp.Roles = Arrays.asList("SuperAdmin");
		UserManagerPage ump = hp.clickUsersMenu();
		boolean userCreation = ump.createInternalUser(udp);
		TestValidation.IsTrue(userCreation, 
				"Succesfully created User  - " +userUN17658, 
				"Could NOT create user - " + userUN17658 );
		hp.userLogout();
		boolean loginUser = lp.loginAfterUserCreation(userUN17658, GenericPassword, GenericNewPassword);
		TestValidation.IsTrue(loginUser, 
				"Logged In with created user - " + userUN17658, 
				"Could NOT login with user - " + userUN17658);

		boolean searchAndSelectCust = fbp.searchSelect(customerInstanceValue17658);
		TestValidation.IsTrue(searchAndSelectCust, 
				"Succesfully searched and selected resource "+ customerInstanceValue17658, 
				"FAILED to search and select resource " + customerInstanceValue17658);

		boolean custIsRevealed = fbp.selectLocationAndCheckLocationIsDisplayed(locationInstanceValue17658);
		TestValidation.IsTrue(custIsRevealed, 
				"Succesfully verified CUSTOMERS resource - "+customerInstanceValue17658+" is revealed to location " + locationInstanceValue17658, 
				"Failed to verify that CUSTOMERS resource - "+customerInstanceValue17658+" is revealed to location "+ locationInstanceValue17658);

		boolean assignWG = wgp.createWorkGroup(wgName, userUN17658);
		TestValidation.IsTrue(assignWG, 
				"Assigned Workgroup - "+wgName+" to user - " + userUN17658, 
				"Could NOT assign Workgroup - " +wgName+ " to user - " + userUN17658);

		boolean navigateToforms = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.CUSTOMERS,FSQATAB.FORMS);
		TestValidation.IsTrue(navigateToforms, 
				"For CUSTOMERS category, NAVIGATED to FSQABrowser > Forms tab", 
				"Failed to navigate to FSQABrowser > Forms tab");

		boolean applyfilter = fbp.openAndApplySettingsForColumn(COLUMNHEADER.FORMNAME, COLUMNSETTING.FILTER,checkFormName17658);
		TestValidation.IsTrue(applyfilter, 
				"Applied Filter on" + COLUMNHEADER.FORMNAME, 
				"Failed to apply filter on" + COLUMNHEADER.FORMNAME);

		TASKDETAILS tsd = new TASKDETAILS();
		tsd.Location = locationInstanceValue17658;
		tsd.Resource = customerInstanceValue17658;
		tsd.TaskName = task17658;
		tsd.Workgroup = wgName;

		boolean assigntask = fbp.assignFormTask(tsd);
		TestValidation.IsTrue(assigntask, 
				"Form task assigned" + task17553, 
				"Failed to assign form task" + task17553);

		ip=hp.clickInboxMenu();
		TestValidation.Equals(ip.error, 
				false, 
				"Opened Inbox", 
				"Could Not Open Inbox"); 

		boolean verifyAssignWGDisplayed = ip.statusFilterCheckbox(wgName);
		TestValidation.IsTrue(verifyAssignWGDisplayed, 
				"Verified Assigned Workgroup - "+wgName+" is Displayed", 
				"Failed, to select status for given Status filter ");

		boolean verifyResIsSameAsAssociated = ip.associatedResourceIsPresent(customerInstanceValue17658);
		TestValidation.IsTrue(verifyResIsSameAsAssociated, 
				"Verified Resource - "+customerInstanceValue17658+" is Displayed as it is assigned to user", 
				"Failed, to verify Resource - " +customerInstanceValue17658+ " is display");

		hp.userLogout();
		lp.performLogin(adminUsername, adminPassword);

		boolean navigateToforms1 = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.EQUIPMENT,FSQATAB.FORMS);
		TestValidation.IsTrue(navigateToforms1, 
				"For EQUIPMENT category, NAVIGATED to FSQABrowser > Forms tab", 
				"Failed to navigate to FSQABrowser > Forms tab");

		boolean applyfilter1 = fbp.openAndApplySettingsForColumn(COLUMNHEADER.FORMNAME, COLUMNSETTING.FILTER,checkFormName17658);
		TestValidation.IsTrue(applyfilter1, 
				"Applied Filter on" + COLUMNHEADER.FORMNAME, 
				"Failed to apply filter on" + COLUMNHEADER.FORMNAME);

		TASKDETAILS tsd1 = new TASKDETAILS();
		tsd1.Location = newLocationInstanceValue17658;
		tsd1.Resource = equipmentInstanceValue17658;
		tsd1.TaskName = newTask17658;
		tsd1.Workgroup = wgName;

		boolean assigntask1 = fbp.assignFormTask(tsd1);
		TestValidation.IsTrue(assigntask1, 
				"Form task assigned" + newTask17658, 
				"Failed to assign form task" + newTask17658);
		hp.userLogout();
		hp = lp.performLogin(userUN17658, GenericNewPassword);

		ip=hp.clickInboxMenu();
		TestValidation.Equals(ip.error, 
				false, 
				"Opened Inbox", 
				"Could Not Open Inbox"); 

		boolean verifyAssignWGDisplayed1 = ip.statusFilterCheckbox(wgName);
		TestValidation.IsTrue(verifyAssignWGDisplayed1, 
				"Verified Assigned Workgroup - "+wgName+" is Displayed", 
				"Failed, to select status for given Status filter ");

		boolean verifyResource = ip.resourceIsNotPresent(equipmentInstanceValue17658);
		TestValidation.IsTrue(verifyResource, 
				"Verified Resource - "+equipmentInstanceValue17658+" is Not Displayed as it is not associated", 
				"Failed, to verify Resource - " +equipmentInstanceValue17658+ " is not display");

		hp.userLogout();
		lp.performLogin(adminUsername, adminPassword);
	}

	@Test(description = "17507 - Data Security Gaps - Form Designer")
	public void TestCase_18209() {
		hp.userLogout();
		lp.performLogin(adminUsername, adminPassword);

		locationCategoryValue18209 = CommonMethods.dynamicText("Loc_Cat_18209");
		customerCategoryValue18209 = CommonMethods.dynamicText("Cust_Cat_18209");
		equipmentCategoryValue18209 = CommonMethods.dynamicText("Equip_Cat_18209");
		itemCategoryValue18209 = CommonMethods.dynamicText("Item_Cat_18209");
		supplierCategoryValue18209 = CommonMethods.dynamicText("Supp_Cat_18209");
		locationInstanceValue18209 = CommonMethods.dynamicText("Loc_Inst_18209");
		newLocationInstanceValue18209 = CommonMethods.dynamicText("New_Loc_Inst_18209");
		customerInstanceValue18209 = CommonMethods.dynamicText("Cust_Inst_18209");
		equipmentInstanceValue18209 = CommonMethods.dynamicText("Equip_Inst_18209");
		itemInstanceValue18209 = CommonMethods.dynamicText("Item_Inst_18209");
		supplierInstanceValue18209 = CommonMethods.dynamicText("Supp_Inst_18209");
		userUN18209 = CommonMethods.dynamicText("UN_18209");
		newUserUN18209 = CommonMethods.dynamicText("New_UN_18209");

		//Category creation
		HashMap<String,String> categories = new HashMap<String, String>();
		categories.put(CATEGORYTYPES.LOCATION, locationCategoryValue18209);
		categories.put(CATEGORYTYPES.CUSTOMERS, customerCategoryValue18209);
		categories.put(CATEGORYTYPES.EQUIPMENT, equipmentCategoryValue18209);
		categories.put(CATEGORYTYPES.ITEMS, itemCategoryValue18209);
		categories.put(CATEGORYTYPES.SUPPLIERS, supplierCategoryValue18209);

		ResourceDesignerPage rdp = hp.clickResourceDesignerMenu();

		boolean createCategory1 = rdp.createCategory(categories);
		TestValidation.IsTrue(createCategory1, 
				"Succesfully created Resource categories for Location - " +locationCategoryValue18209+ " , Customer - "+ customerCategoryValue18209+  " , Equipment - " +equipmentCategoryValue18209+ " , Item - "+itemCategoryValue18209+ " , Supplier -"+ supplierCategoryValue18209, 
				"Could NOT create Resource categories Location - " +locationCategoryValue18209+ " , Customer - "+ customerCategoryValue18209+  " , Equipment - " +equipmentCategoryValue18209+" , Item - "+itemCategoryValue18209+ " , Supplier -"+ supplierCategoryValue18209);

		mrp = hp.clickResourcesMenu();
		TestValidation.Equals(mrp.error, 
				false, 
				"Opened Manage Resource", 
				"Could Not Open Manage Resource"); 

		//Resource creation - Customer
		ResourceDetailParams rd1 = new ResourceDetailParams();
		rd1.CategoryName = customerCategoryValue18209;
		rd1.CategoryType = RESOURCETYPES.CUSTOMERS;
		rd1.NumberFieldValue = 15;
		rd1.TextFieldValue = "LMN";
		rd1.InstanceName = customerInstanceValue18209;
		rd1.InstanceStatus = true;

		boolean createResource1 = mrp.createResource(rd1);
		TestValidation.IsTrue(createResource1, 
				"Succesfully created Customer Instance - "+customerInstanceValue18209+" for category - " + customerCategoryValue18209, 
				"Could NOT create Customer Instance - "+customerInstanceValue18209+" for category - " + customerCategoryValue18209);

		//Resource creation - Equipment
		ResourceDetailParams rd2 = new ResourceDetailParams();
		rd2.CategoryName = equipmentCategoryValue18209;
		rd2.CategoryType = RESOURCETYPES.EQUIPMENT;
		rd2.NumberFieldValue = 15;
		rd2.TextFieldValue = "LMN";
		rd2.InstanceName = equipmentInstanceValue18209;
		rd2.InstanceStatus = true;

		boolean createResource2 = mrp.createResource(rd2);
		TestValidation.IsTrue(createResource2, 
				"Succesfully created Equipment Instance - "+equipmentInstanceValue18209+" for category - " + equipmentCategoryValue18209, 
				"Could NOT create Equipment Instance - "+equipmentInstanceValue18209+" for category - " + equipmentCategoryValue18209);

		//Resource creation - Item
		ResourceDetailParams rd3 = new ResourceDetailParams();
		rd3.CategoryName = itemCategoryValue18209;
		rd3.CategoryType = RESOURCETYPES.ITEMS;
		rd3.NumberFieldValue = 15;
		rd3.TextFieldValue = "LMN";
		rd3.InstanceName = itemInstanceValue18209;
		rd3.InstanceStatus = true;

		boolean createResource3 = mrp.createResource(rd3);
		TestValidation.IsTrue(createResource3, 
				"Succesfully created Item Instance - "+itemInstanceValue18209+" for category - " + itemCategoryValue18209, 
				"Could NOT create Item Instance - "+itemInstanceValue18209+" for category - " + itemCategoryValue18209);

		//Resource creation - Supplier
		ResourceDetailParams rd4 = new ResourceDetailParams();
		rd4.CategoryName = supplierCategoryValue18209;
		rd4.CategoryType = RESOURCETYPES.SUPPLIERS;
		rd4.NumberFieldValue = 15;
		rd4.TextFieldValue = "LMN";
		rd4.InstanceName = supplierInstanceValue18209;
		rd4.InstanceStatus = true;

		boolean createResource4 = mrp.createResource(rd4);
		TestValidation.IsTrue(createResource4, 
				"Succesfully created Supplier Instance - "+supplierInstanceValue18209+" for category - " + supplierCategoryValue18209, 
				"Could NOT create Supplier Instance - "+supplierInstanceValue18209+" for category - " + supplierCategoryValue18209);


		//Location instance creation
		HashMap<String,Boolean> locInstance1 = new HashMap<String, Boolean>();
		locInstance1.put(locationInstanceValue18209, true);

		LocationInstanceParams lip1 = new LocationInstanceParams();
		lip1.CategoryName = locationCategoryValue18209;
		lip1.NumberFieldValue = 10;
		lip1.TextFieldValue = "XYZ";
		lip1.InstanceName = locInstance1;
		hp.clickLocationsMenu();

		boolean createLocInst1 = mlp.createLocation(lip1);
		TestValidation.IsTrue(createLocInst1, 
				"Succesfully created Location Instance  - "+locationInstanceValue18209, 
				"Could NOT create Location instance - "+locationInstanceValue18209 );

		HashMap<String, List<String>> resourceInstances = new LinkedHashMap<String, List<String>>();
		resourceInstances.put(SelectTAB.CUSTOMERS, Arrays.asList(customerInstanceValue18209));
		resourceInstances.put(SelectTAB.EQUIPMENT, Arrays.asList(equipmentInstanceValue18209));
		resourceInstances.put(SelectTAB.ITEMS, Arrays.asList(itemInstanceValue18209));

		boolean linkResToLoc1 = mlp.linkResourceToLocation(resourceInstances);
		TestValidation.IsTrue(linkResToLoc1, 
				"Succesfully linked Resource - "+customerInstanceValue18209+" ,"+equipmentInstanceValue18209+" ,"+itemInstanceValue18209+" with Location Instance  - "+locationInstanceValue18209, 
				"Could NOT link Resource - "+customerInstanceValue18209+" ,"+equipmentInstanceValue18209+" ,"+itemInstanceValue18209+" with Location instance - "+locationInstanceValue18209 );

		//Location instance creation
		HashMap<String,Boolean> locInstance2 = new HashMap<String, Boolean>();
		locInstance2.put(newLocationInstanceValue18209, true);

		LocationInstanceParams lip2 = new LocationInstanceParams();
		lip2.CategoryName = locationCategoryValue18209;
		lip2.NumberFieldValue = 10;
		lip2.TextFieldValue = "XYZ";
		lip2.InstanceName = locInstance2;
		hp.clickLocationsMenu();

		boolean createLocInst2 = mlp.createLocation(lip2);
		TestValidation.IsTrue(createLocInst2, 
				"Succesfully created Location Instance  - "+newLocationInstanceValue18209, 
				"Could NOT create Location instance - "+newLocationInstanceValue18209 );

		HashMap<String, List<String>> resourceInstances2 = new LinkedHashMap<String, List<String>>();
		resourceInstances2.put(SelectTAB.CUSTOMERS, Arrays.asList(customerInstanceValue18209));
		resourceInstances2.put(SelectTAB.SUPPLIERS, Arrays.asList(supplierInstanceValue18209));

		boolean linkResToLoc2 = mlp.linkResourceToLocation(resourceInstances2);
		TestValidation.IsTrue(linkResToLoc2, 
				"Succesfully linked Resources - "+customerInstanceValue18209+" and " +supplierInstanceValue18209+" with Location Instance  - "+newLocationInstanceValue18209, 
				"Could NOT link Resources - "+customerInstanceValue18209+" and "+supplierInstanceValue18209+" with Location instance - "+newLocationInstanceValue18209 );


		//user Creation
		UsrDetailParams udp = new UsrDetailParams();
		udp.Username = userUN18209;
		udp.Password = GenericPassword;
		udp.FirstName = userFN;
		udp.LastName = userLN;
		udp.Email = "test@test.com";
		udp.Timezone = TIMEZONE.REPUBLICOFINDIA;
		udp.Locations = Arrays.asList(locationInstanceValue18209);
		udp.Roles = Arrays.asList("SuperAdmin");
		UserManagerPage ump = hp.clickUsersMenu();
		boolean userCreation = ump.createInternalUser(udp);
		TestValidation.IsTrue(userCreation, 
				"Succesfully created User  - " +userUN18209, 
				"Could NOT create user - " + userUN18209 );

		//user Creation
		UsrDetailParams udp1 = new UsrDetailParams();
		udp1.Username = newUserUN18209;
		udp1.Password = GenericPassword;
		udp1.FirstName = userFN;
		udp1.LastName = userLN;
		udp1.Email = "test@test.com";
		udp1.Timezone = TIMEZONE.REPUBLICOFINDIA;
		udp1.Locations = Arrays.asList(newLocationInstanceValue18209);
		udp1.Roles = Arrays.asList("SuperAdmin");
		boolean userCreation1 = ump.createInternalUser(udp1);
		TestValidation.IsTrue(userCreation1, 
				"Succesfully created User  - " +newUserUN18209, 
				"Could NOT create user - " + newUserUN18209 );

		hp.userLogout();
		boolean loginUser = lp.loginAfterUserCreation(userUN18209, GenericPassword, GenericNewPassword);
		TestValidation.IsTrue(loginUser, 
				"Logged In with created user - " + userUN18209, 
				"Could NOT login with user - " + userUN18209);

		boolean searchAndSelectCust = fbp.searchSelect(customerInstanceValue18209);
		TestValidation.IsTrue(searchAndSelectCust, 
				"Succesfully searched and selected resource "+ customerInstanceValue18209, 
				"FAILED to search and select resource " + customerInstanceValue18209);

		boolean custIsRevealed = fbp.selectLocationAndCheckLocationIsDisplayed(locationInstanceValue18209);
		TestValidation.IsTrue(custIsRevealed, 
				"Succesfully verified CUSTOMERS resource - "+customerInstanceValue18209+" is revealed to location " + locationInstanceValue18209, 
				"Failed to verify that CUSTOMERS resource - "+customerInstanceValue18209+" is revealed to location "+ locationInstanceValue18209);

		//FORM - Creation and Save

		checkFormName18209	= CommonMethods.dynamicText("18209_Automation_CheckForm");
		numFieldName    = "Numeric Field";
		HashMap<String, List<String>> fields = new HashMap<String, List<String>>();
		fields.put(FIELD_TYPES.NUMERIC, Arrays.asList(numFieldName));
		FormFieldParams ffp = new FormFieldParams();
		ffp.FieldDetails = fields;
		HashMap<String, List<String>> resource = new HashMap<String, List<String>>();
		resource.put(FORMRESOURCETYPES.CUSTOMERS, Arrays.asList(customerCategoryValue18209));
		resource.put(FORMRESOURCETYPES.EQUIPMENT, Arrays.asList(equipmentCategoryValue18209));
		resource.put(FORMRESOURCETYPES.ITEMS, Arrays.asList(itemCategoryValue18209));

		FormDesignParams fdpDets = new FormDesignParams();
		fdpDets.FormType = FORMTYPES.CHECK;
		fdpDets.FormName = checkFormName18209;
		fdpDets.SelectResources = resource;
		fdpDets.DesignFields = Arrays.asList(ffp);
		fdpDets.ReleaseForm = false;

		hp.clickFormDesignerMenu();
		boolean createAndReleaseForm = fdp.createAndReleaseForm(fdpDets);
		TestValidation.IsTrue(createAndReleaseForm,
				"Able to create and save the form:" + checkFormName18209,
				"Could NOT able to create and save the form:" + checkFormName18209);

		hp.userLogout();
		boolean loginUser1 = lp.loginAfterUserCreation(newUserUN18209, GenericPassword, GenericNewPassword);
		TestValidation.IsTrue(loginUser1, 
				"Logged In with created user - " + newUserUN18209, 
				"Could NOT login with user - " + newUserUN18209);

		boolean editForm = fdp.navigateToReleaseForm_EditForm(checkFormName18209);
		TestValidation.IsTrue(editForm, 
				"Navigated to release form and OPENED form "+checkFormName18209+ "in edit mode", 
				"Failed to Open form"+checkFormName18209+" in edit mode");

		boolean selectResource = fdp.selectResourcesFromEditForm("Suppliers", supplierCategoryValue18209, supplierInstanceValue18209);
		TestValidation.IsTrue(selectResource, 
				"Successfully user is able to add new resource - "+supplierInstanceValue18209+ " from edit form", 
				"Fail to add new resource - "+supplierInstanceValue18209+ " from edit form");

		String freeTextFieldName = "FreeText1";
		WebElement FreeTextFieldType = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_TYPE,"FIELDTYPE",FIELD_TYPES.FREE_TEXT);
		controlActions.dragdrop(FreeTextFieldType, fdp.FormLevel);
		boolean setFreeText = fdp.setTextBoxValue(FIELD_TYPES.FREE_TEXT, freeTextFieldName);		
		TestValidation.IsTrue(setFreeText, 
				"'Free Text field' added successfully. Since user is able to edit form -"+checkFormName18209, 
				"Failed to Add Free Text Field.User is not able to edit form - "+checkFormName18209);

		boolean saveForm = fdp.clickSaveButton();
		TestValidation.IsTrue(saveForm, 
				"Saved edited form - "+checkFormName18209+" Successfully", 
				"Could NOT Save edited form - "+checkFormName18209+" Form");

		boolean nextToReleaseForm = fdp.clickOnNextButton(fdp.ReleaseFormPg,"Release Form");
		TestValidation.IsTrue(nextToReleaseForm, 
				"NAVIGATED to 'Release Form'", 
				"Could NOT Navigate to 'Release Form'");

		boolean clickPreviewOnReleasePage = fdp.clickPreviewOnReleasePage(checkFormName18209);
		TestValidation.IsTrue(clickPreviewOnReleasePage, 
				"Clicked on Preview for form - "+checkFormName18209+" on ReleasePage Successfully", 
				"Could NOT Click Preview for form - "+checkFormName18209+"  On ReleasePage");

		boolean searchRes = fdp.searchSelectResourceNoDataInPreviewForm(equipmentInstanceValue18209);
		TestValidation.IsTrue(searchRes, 
				"Verified Resource - "+equipmentInstanceValue18209+" is not Present in Resource Drop Down Form Preview as user not having access ", 
				"Fail to verify Resource - "+equipmentInstanceValue18209+" is not present in Resource Drop Down Form Preview");

		hp.userLogout();
		lp.performLogin(adminUsername, adminPassword);
	}

	@Test(description = "17823-Data Security Gaps - FSQA Browser Saved Forms Tab")
	public void TestCase_18504() {
		hp.userLogout();
		lp.performLogin(adminUsername, adminPassword);
		locationCategoryValue18504 = CommonMethods.dynamicText("Loc_Cat_185045");
		customerCategoryValue18504 = CommonMethods.dynamicText("Cust_Cat_185045");
		equipmentCategoryValue18504 = CommonMethods.dynamicText("Equip_Cat_18504");
		locationInstanceValue18504 = CommonMethods.dynamicText("Loc_Inst_18504");
		customerInstanceValue18504 = CommonMethods.dynamicText("Cust_Inst_18504");
		equipmentInstanceValue18504 = CommonMethods.dynamicText("Equip_Inst_18504");
		userUN18504 = CommonMethods.dynamicText("UN_18504");
		newUserUN18504 = CommonMethods.dynamicText("New_UN_18504");

		//Category creation
		HashMap<String,String> categories = new HashMap<String, String>();
		categories.put(CATEGORYTYPES.LOCATION, locationCategoryValue18504);
		categories.put(CATEGORYTYPES.CUSTOMERS, customerCategoryValue18504);
		categories.put(CATEGORYTYPES.EQUIPMENT, equipmentCategoryValue18504);

		ResourceDesignerPage rdp = hp.clickResourceDesignerMenu();

		boolean createCategory1 = rdp.createCategory(categories);
		TestValidation.IsTrue(createCategory1, 
				"Succesfully created Resource categories for Location - " +locationCategoryValue18504+ " , Customer - "+ customerCategoryValue18504+  " , Equipment - " +equipmentCategoryValue18504 , 
				"Could NOT create Resource categories Location - " +locationCategoryValue18504+ " , Customer - "+ customerCategoryValue18504+  " , Equipment - " +equipmentCategoryValue18504);

		mrp = hp.clickResourcesMenu();
		TestValidation.Equals(mrp.error, 
				false, 
				"Opened Manage Resource", 
				"Could Not Open Manage Resource"); 

		//Resource creation - Customer
		ResourceDetailParams rd1 = new ResourceDetailParams();
		rd1.CategoryName = customerCategoryValue18504;
		rd1.CategoryType = RESOURCETYPES.CUSTOMERS;
		rd1.NumberFieldValue = 15;
		rd1.TextFieldValue = "LMN";
		rd1.InstanceName = customerInstanceValue18504;
		rd1.InstanceStatus = true;

		boolean createResource1 = mrp.createResource(rd1);
		TestValidation.IsTrue(createResource1, 
				"Succesfully created Customer Instance - "+customerInstanceValue18504+" for category - " + customerCategoryValue18504, 
				"Could NOT create Customer Instance - "+customerInstanceValue18504+" for category - " + customerCategoryValue18504);

		//Resource creation - Equipment
		ResourceDetailParams rd2 = new ResourceDetailParams();
		rd2.CategoryName = equipmentCategoryValue18504;
		rd2.CategoryType = RESOURCETYPES.EQUIPMENT;
		rd2.NumberFieldValue = 15;
		rd2.TextFieldValue = "LMN";
		rd2.InstanceName = equipmentInstanceValue18504;
		rd2.InstanceStatus = true;

		boolean createResource2 = mrp.createResource(rd2);
		TestValidation.IsTrue(createResource2, 
				"Succesfully created Equipment Instance - "+equipmentInstanceValue18504+" for category - " + equipmentCategoryValue18504, 
				"Could NOT create Equipment Instance - "+equipmentInstanceValue18504+" for category - " + equipmentCategoryValue18504);

		//Location instance creation
		HashMap<String,Boolean> locInstance1 = new HashMap<String, Boolean>();
		locInstance1.put(locationInstanceValue18504, true);

		LocationInstanceParams lip1 = new LocationInstanceParams();
		lip1.CategoryName = locationCategoryValue18504;
		lip1.NumberFieldValue = 10;
		lip1.TextFieldValue = "XYZ";
		lip1.InstanceName = locInstance1;
		hp.clickLocationsMenu();

		boolean createLocInst1 = mlp.createLocation(lip1);
		TestValidation.IsTrue(createLocInst1, 
				"Succesfully created Location Instance  - "+locationInstanceValue18504, 
				"Could NOT create Location instance - "+locationInstanceValue18504 );

		HashMap<String, List<String>> resourceInstances = new LinkedHashMap<String, List<String>>();
		resourceInstances.put(SelectTAB.CUSTOMERS, Arrays.asList(customerInstanceValue18504));
		resourceInstances.put(SelectTAB.EQUIPMENT, Arrays.asList(equipmentInstanceValue18504));

		boolean linkResToLoc1 = mlp.linkResourceToLocation(resourceInstances);
		TestValidation.IsTrue(linkResToLoc1, 
				"Succesfully linked Resource - "+customerInstanceValue18504+" and "+equipmentInstanceValue18504+" with Location Instance  - "+locationInstanceValue18504, 
				"Could NOT link Resource - "+customerInstanceValue18504+" and "+equipmentInstanceValue18504+" with Location instance - "+locationInstanceValue18504 );


		//FORM - Creation and release

		checkFormName18504	= CommonMethods.dynamicText("18504_Automation_CheckForm");
		numFieldName    = "Numeric Field";
		HashMap<String, List<String>> fields = new HashMap<String, List<String>>();
		fields.put(FIELD_TYPES.NUMERIC, Arrays.asList(numFieldName));
		FormFieldParams ffp = new FormFieldParams();
		ffp.FieldDetails = fields;
		HashMap<String, List<String>> resource = new HashMap<String, List<String>>();
		resource.put(FORMRESOURCETYPES.CUSTOMERS, Arrays.asList(customerCategoryValue18504));
		resource.put(FORMRESOURCETYPES.EQUIPMENT, Arrays.asList(equipmentCategoryValue18504));

		FormDesignParams fdpDets = new FormDesignParams();
		fdpDets.FormType = FORMTYPES.CHECK;
		fdpDets.FormName = checkFormName18504;
		fdpDets.SelectResources = resource;
		fdpDets.DesignFields = Arrays.asList(ffp);
		fdpDets.ReleaseForm = true;

		hp.clickFormDesignerMenu();
		boolean createAndReleaseForm = fdp.createAndReleaseForm(fdpDets);
		TestValidation.IsTrue(createAndReleaseForm,
				"Able to create and release the form:" + checkFormName18504,
				"Could NOT able to create and release the form:" + checkFormName18504);

		//user Creation
		UsrDetailParams udp = new UsrDetailParams();
		udp.Username = userUN18504;
		udp.Password = GenericPassword;
		udp.FirstName = userFN;
		udp.LastName = userLN;
		udp.Email = "test@test.com";
		udp.Timezone = TIMEZONE.REPUBLICOFINDIA;
		udp.Locations = Arrays.asList(locationInstanceValue18504);
		udp.Roles = Arrays.asList("SuperAdmin");
		UserManagerPage ump = hp.clickUsersMenu();
		boolean userCreation = ump.createInternalUser(udp);
		TestValidation.IsTrue(userCreation, 
				"Succesfully created User  - " +userUN18504, 
				"Could NOT create user - " + userUN18504 );

		//user Creation
		UsrDetailParams udp1 = new UsrDetailParams();
		udp1.Username = newUserUN18504;
		udp1.Password = GenericPassword;
		udp1.FirstName = userFN;
		udp1.LastName = userLN;
		udp1.Email = "test@test.com";
		udp1.Timezone = TIMEZONE.REPUBLICOFINDIA;
		udp1.Locations = Arrays.asList(locationInstanceValue18504);
		udp1.Roles = Arrays.asList("SuperAdmin");
		boolean userCreation1 = ump.createInternalUser(udp1);
		TestValidation.IsTrue(userCreation1, 
				"Succesfully created User  - " +newUserUN18504, 
				"Could NOT create user - " + newUserUN18504 );

		hp.userLogout();
		boolean loginUser = lp.loginAfterUserCreation(userUN18504, GenericPassword, GenericNewPassword);
		TestValidation.IsTrue(loginUser, 
				"Logged In with created user - " + userUN18504, 
				"Could NOT login with user - " + userUN18504);

		boolean searchAndSelectCust = fbp.searchSelect(customerInstanceValue18504);
		TestValidation.IsTrue(searchAndSelectCust, 
				"Succesfully searched and selected resource "+ customerInstanceValue18504, 
				"FAILED to search and select resource " + customerInstanceValue18504);

		boolean custIsRevealed = fbp.selectLocationAndCheckLocationIsDisplayed(locationInstanceValue18504);
		TestValidation.IsTrue(custIsRevealed, 
				"Succesfully verified CUSTOMERS resource - "+customerInstanceValue18504+" is revealed to location " + locationInstanceValue18504, 
				"Failed to verify that CUSTOMERS resource - "+customerInstanceValue18504+" is revealed to location "+ locationInstanceValue18504);

		boolean navigateToforms2 = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.CUSTOMERS,FSQATAB.FORMS);
		TestValidation.IsTrue(navigateToforms2, 
				"For customer category, NAVIGATED to FSQABrowser > Forms tab", 
				"Failed to navigate to FSQABrowser > Forms tab");

		boolean applyFilterAndOpenForm = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, checkFormName18504);
		TestValidation.IsTrue(applyFilterAndOpenForm, 
				"Selected & opened the form - "+checkFormName18504, 
				"Could NOT open the form - "+checkFormName18504);

		//Save form
		LinkedHashMap<String, List<String>> fillDetails = new LinkedHashMap<String, List<String>>();
		fillDetails.put("Numeric Field", Arrays.asList("10"));

		FormDetails fd = new FormDetails();
		fd.fieldDetails = fillDetails;
		fd.locationName = locationInstanceValue18504;
		fd.resourceName = customerInstanceValue18504;
		fd.isSubmit = false;
		FormOperations fo = new FormOperations(driver);
		boolean save = fo.submitData(fd);
		TestValidation.IsTrue(save, "Successfully saved Form -  " + checkFormName18504, "Failed to save form " + checkFormName18504);

		controlActions.refreshPage();

		boolean verifySavedForm = fbp.verifyFormIsDisplayedInSavedForms(checkFormName18504);
		TestValidation.IsTrue(verifySavedForm, 
				"Successfully verified Form -  " + checkFormName18504+ " in Saved Forms even after performing refresh action", 
				"Failed to verify form " + checkFormName18504+" in Saved Forms");

		hp.clickLocationsMenu();
		boolean unlinkCustInst = mlp.unlinkResourceFromLocation(locationCategoryValue18504, locationInstanceValue18504, SelectTAB.CUSTOMERS, customerInstanceValue18504, SelectTAB.CUSTOMERS);
		TestValidation.IsTrue(unlinkCustInst, 
				"Successfully Unchecked/Unlink resource - "+customerInstanceValue18504+" from location - "+locationInstanceValue18504, 
				"FAil to Uncheck/Unlink resource - "+customerInstanceValue18504+" from location - "+locationInstanceValue18504);

		fbp = hp.clickFSQABrowserMenu();
		boolean navigateToforms = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.CUSTOMERS,FSQATAB.FORMS);
		TestValidation.IsTrue(navigateToforms, 
				"For CUSTOMERS category, NAVIGATED to FSQABrowser > Forms tab", 
				"Failed to navigate to FSQABrowser > Forms tab");

		boolean openForm = fbp.openFormInSavedForms(checkFormName18504);
		TestValidation.IsTrue(openForm, 
				"Opened Saved form - "+checkFormName18504+" even after resource is unlink", 
				"Could NOT open saved form - "+checkFormName18504+" after unlinking the resource");

		FormOperations fo1 = new FormOperations(driver);
		boolean clickSaveBtn = fo1.clickSaveButton();
		TestValidation.IsTrue(clickSaveBtn, 
				"Succesfully saved the Form -"+checkFormName18504+" from saved forms", 
				"Failed to Save the form - "+checkFormName18504+" from saved forms");

		boolean openForm1 = fbp.openFormInSavedForms(checkFormName18504);
		TestValidation.IsTrue(openForm1, 
				"Opened Saved form - "+checkFormName18504+" even after saving the form from saved forms", 
				"Could NOT open saved form - "+checkFormName18504+" after saving the form from saved forms");

		boolean submitForm = fo1.clickSubmitThenAlertOkBtn();
		TestValidation.IsTrue(submitForm, 
				"Succesfully submitted Form -"+checkFormName18504+" from saved forms", 
				"Failed to submit form - "+checkFormName18504+" from saved forms");

		hp.userLogout();
		boolean loginUser1 = lp.loginAfterUserCreation(newUserUN18504, GenericPassword, GenericNewPassword);
		TestValidation.IsTrue(loginUser1, 
				"Logged In with created user - " + newUserUN18504, 
				"Could NOT login with user - " + newUserUN18504);

		boolean selectCust = fbp.selectDropDownValue(FORMRESOURCETYPES.EQUIPMENT);
		TestValidation.IsTrue(selectCust, 
				"Selected EQUIPMENT Category from drop down", 
				"Failed to select EQUIPMENT Category from drop down");

		boolean searchAndSelectEquip = fbp.searchSelect(equipmentInstanceValue18504);
		TestValidation.IsTrue(searchAndSelectEquip, 
				"Succesfully searched and selected resource "+ equipmentInstanceValue18504, 
				"FAILED to search and select resource " + equipmentInstanceValue18504);

		boolean equipIsRevealed = fbp.selectLocationAndCheckLocationIsDisplayed(locationInstanceValue18504);
		TestValidation.IsTrue(equipIsRevealed, 
				"Succesfully verified EQUIPMENT resource - "+equipmentInstanceValue18504+" is revealed to location " + locationInstanceValue18504, 
				"Failed to verify that EQUIPMENT resource - "+equipmentInstanceValue18504+" is revealed to location "+ locationInstanceValue18504);

		boolean navigateToforms3 = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.EQUIPMENT,FSQATAB.FORMS);
		TestValidation.IsTrue(navigateToforms3, 
				"For EQUIPMENT category, NAVIGATED to FSQABrowser > Forms tab", 
				"Failed to navigate to FSQABrowser > Forms tab");

		boolean applyFilterAndOpenForm1 = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, checkFormName18504);
		TestValidation.IsTrue(applyFilterAndOpenForm1, 
				"Selected & opened the form - "+checkFormName18504, 
				"Could NOT open the form - "+checkFormName18504);

		//Save form
		LinkedHashMap<String, List<String>> fillDetails1 = new LinkedHashMap<String, List<String>>();
		fillDetails1.put("Numeric Field", Arrays.asList("10"));

		FormDetails fd1 = new FormDetails();
		fd1.fieldDetails = fillDetails1;
		fd1.locationName = locationInstanceValue18504;
		fd1.resourceName = equipmentInstanceValue18504;
		fd1.isSubmit = false;
		FormOperations fo2 = new FormOperations(driver);
		boolean save1 = fo2.submitData(fd1);
		TestValidation.IsTrue(save1, "Successfully saved Form -  " + checkFormName18504, "Failed to save form " + checkFormName18504);

		boolean openForm2 = fbp.openFormInSavedForms(checkFormName18504);
		TestValidation.IsTrue(openForm2, 
				"Opened Saved form - "+checkFormName18504+"  from saved forms", 
				"Could NOT open saved form - "+checkFormName18504+" from saved forms");

		boolean submitForm1 = fo2.clickSubmitThenAlertOkBtn();
		TestValidation.IsTrue(submitForm1, 
				"Succesfully submitted Form -"+checkFormName18504+" from saved forms", 
				"Failed to submit form - "+checkFormName18504+" from saved forms");

		hp.userLogout();
		lp.performLogin(adminUsername, adminPassword);

	}
	@Test(description = "9127-Data Security - Manage Resources – “Revealed” resources can\r\n"
			+ "be removed from Locations via Manage Resources")
	public void TestCase_10335() {
		hp.userLogout();
		lp.performLogin(adminUsername, adminPassword);

		fbp = hp.clickFSQABrowserMenu();
		TestValidation.Equals(fbp.error, 
				false, 
				"Opened FSQA Browser", 
				"Could Not Open FSQA Browser"); 

		boolean navigateToforms = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.CUSTOMERS,FSQATAB.FORMS);
		TestValidation.IsTrue(navigateToforms, 
				"For customer category, NAVIGATED to FSQABrowser > Forms tab", 
				"Failed to navigate to FSQABrowser > Forms tab");

		boolean applyFilterAndOpenForm = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, checkFormName);
		TestValidation.IsTrue(applyFilterAndOpenForm, 
				"Selected & opened the form - "+checkFormName, 
				"Could NOT open the form - "+checkFormName);

		//Submit form
		LinkedHashMap<String, List<String>> fillDetails = new LinkedHashMap<String, List<String>>();
		fillDetails.put("Numeric Field", Arrays.asList("10"));

		FormDetails fd = new FormDetails();
		fd.fieldDetails = fillDetails;
		fd.locationName = locationInstanceValue;
		fd.resourceName = newCustomerInstanceValue;
		fd.isSubmit = true;
		FormOperations fo = new FormOperations(driver);
		boolean submit = fo.fillAndSubmitData(fd);
		TestValidation.IsTrue(submit, "Form submitted " + checkFormName, "Failed to submit form " + checkFormName);

		mrp = hp.clickResourcesMenu();
		TestValidation.Equals(mrp.error, 
				false, 
				"Opened Manage Resource", 
				"Could Not Open Manage Resource"); 

		boolean unlinkLocationFromResource = mrp.searchAndUnlinkLocationFromResource(RESOURCETYPES.CUSTOMERS, newCustomerInstanceValue, Arrays.asList(locationInstanceValue));
		TestValidation.IsTrue(unlinkLocationFromResource, 
				"UNLINKED resource " + newCustomerInstanceValue + " from location " + locationInstanceValue, 
				"Failed to UnLink resource " + newCustomerInstanceValue + " from location " + locationInstanceValue);	

		fbp = hp.clickFSQABrowserMenu();

		boolean selectCust = fbp.selectDropDownValue(FORMRESOURCETYPES.CUSTOMERS);
		TestValidation.IsTrue(selectCust, 
				"Selected CUSTOMERS Category from drop down", 
				"Failed to select CUSTOMERS Category from drop down");

		boolean searchNoData = fbp.searchNoData(newCustomerInstanceValue);
		TestValidation.IsTrue(searchNoData, 
				"Verified resource -  " + newCustomerInstanceValue+" is not associated to location", 
				"Failed to verify resource -  " + newCustomerInstanceValue+" is not associated to location");

		boolean navigateToRecords = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.CUSTOMERS,FSQATAB.RECORDS);
		TestValidation.IsTrue(navigateToRecords, 
				"For CUSTOMERS category, NAVIGATED to FSQABrowser > Records tab", 
				"Failed to navigate to FSQABrowser > Records tab");

		boolean openAndApplySettings3 = fbp.openAndApplySettingsForColumn(COLUMNHEADER.FORMNAME, COLUMNSETTING.FILTER, checkFormName);
		TestValidation.IsTrue(openAndApplySettings3, 
				"Searched Record - "+checkFormName, 
				"Could NOT search Record - "+checkFormName);

		boolean verifyForm3 = fbp.verifyRecordIsDisplayed(checkFormName);
		TestValidation.IsTrue(verifyForm3, 
				"VERIFIED submitted form "+checkFormName+" is Displayed in Records tab after unlinking the location associated to it", 
				"FAILED to verify submitted form is display in Records tab");

		dmp = hp.clickdocumentsmenu();
		TestValidation.Equals(dmp.error, 
				false, 
				"Opened Documents Management", 
				"Could Not Open Documents Management");

		boolean selectDocType = dmp.searchSelect(questionnaireFormName17556);
		TestValidation.IsTrue(selectDocType, 
				"Searched and selected Document Type -> " + questionnaireFormName17556,
				"Could NOT search and select Document Type ->  " + questionnaireFormName17556);

		boolean isDocPresent =  dmp.isDocumentPresent(questionnaireFormName17556);
		TestValidation.IsTrue(isDocPresent, 
				"Document Presence VERIFIED for document -> " + questionnaireFormName17556,
				"Could NOT VERIFY presence of document -> " + questionnaireFormName17556);

		fbp = hp.clickFSQABrowserMenu();
		TestValidation.Equals(fbp.error, 
				false, 
				"Opened FSQA Browser", 
				"Could Not Open FSQA Browser"); 

		boolean navigateToforms1 = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.CUSTOMERS,FSQATAB.FORMS);
		TestValidation.IsTrue(navigateToforms1, 
				"For customer category, NAVIGATED to FSQABrowser > Forms tab", 
				"Failed to navigate to FSQABrowser > Forms tab");

		boolean applyFilterAndOpenForm1 = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, checkFormName);
		TestValidation.IsTrue(applyFilterAndOpenForm1, 
				"Selected & opened the form - "+checkFormName, 
				"Could NOT open the form - "+checkFormName);

		boolean searchNodata = fo.searchSelectResourceNoData(newCustomerInstanceValue);
		TestValidation.IsTrue(searchNodata, 
				"Verified uncheck Resource - "+newCustomerInstanceValue+ " is hidden in SELECT RESOURCE DropDown on a form.", 
				"Failed to verify uncheck Resource - "+newCustomerInstanceValue+" is hidden in SELECT RESOURCE DropDown on a form.");

		hp.userLogout();
		lp.performLogin(adminUsername, adminPassword);
	}
	@Test(description = "9129 - Data Security - Users can re-add previously removed\r\n"
			+ "resource(s) to Location(s) via Manage Locations or Manage Resources")
	public void TestCase_10339() {
		hp.userLogout();
		lp.performLogin(adminUsername, adminPassword);

		locationCategoryValue10339 = CommonMethods.dynamicText("Loc_Cat_10339");
		customerCategoryValue10339 = CommonMethods.dynamicText("Cust_Cat_10339");
		equipmentCategoryValue10339 = CommonMethods.dynamicText("Equip_Cat_10339");
		locationInstanceValue10339 = CommonMethods.dynamicText("Loc_Inst_10339");
		customerInstanceValue10339 = CommonMethods.dynamicText("Cust_Inst_10339");
		equipmentInstanceValue10339 = CommonMethods.dynamicText("Equip_Inst_10339");

		//Category creation
		HashMap<String,String> categories = new HashMap<String, String>();
		categories.put(CATEGORYTYPES.LOCATION, locationCategoryValue10339);
		categories.put(CATEGORYTYPES.CUSTOMERS, customerCategoryValue10339);
		categories.put(CATEGORYTYPES.EQUIPMENT, equipmentCategoryValue10339);

		ResourceDesignerPage rdp = hp.clickResourceDesignerMenu();

		boolean createCategory1 = rdp.createCategory(categories);
		TestValidation.IsTrue(createCategory1, 
				"Succesfully created Resource categories --> Location - " +locationCategoryValue10339+ " , Customer - "+ customerCategoryValue10339+  " , Equipment - " +equipmentCategoryValue10339, 
				"Could NOT create Resource categories --> Location - " +locationCategoryValue10339+ " , Customer - "+ customerCategoryValue10339+  " , Equipment - " +equipmentCategoryValue10339);

		mrp = hp.clickResourcesMenu();
		TestValidation.Equals(mrp.error, 
				false, 
				"Opened Manage Resource", 
				"Could Not Open Manage Resource"); 

		//Resource creation - Customer
		ResourceDetailParams rd1 = new ResourceDetailParams();
		rd1.CategoryName = customerCategoryValue10339;
		rd1.CategoryType = RESOURCETYPES.CUSTOMERS;
		rd1.NumberFieldValue = 15;
		rd1.TextFieldValue = "LMN";
		rd1.InstanceName = customerInstanceValue10339;
		rd1.InstanceStatus = true;

		boolean createResource1 = mrp.createResource(rd1);
		TestValidation.IsTrue(createResource1, 
				"Succesfully created Customer Instance - "+customerInstanceValue10339+" for category - " + customerCategoryValue10339, 
				"Could NOT create Customer Instance - "+customerInstanceValue10339+" for category - " + customerCategoryValue10339);

		//Resource creation - Equipment
		ResourceDetailParams rd2 = new ResourceDetailParams();
		rd2.CategoryName = equipmentCategoryValue10339;
		rd2.CategoryType = RESOURCETYPES.EQUIPMENT;
		rd2.NumberFieldValue = 15;
		rd2.TextFieldValue = "LMN";
		rd2.InstanceName = equipmentInstanceValue10339;
		rd2.InstanceStatus = true;

		boolean createResource2 = mrp.createResource(rd2);
		TestValidation.IsTrue(createResource2, 
				"Succesfully created Equipment Instance - "+equipmentInstanceValue10339+" for category - " + equipmentCategoryValue10339, 
				"Could NOT create Equipment Instance - "+equipmentInstanceValue10339+" for category - " + equipmentCategoryValue10339);

		//Location instance creation
		HashMap<String,Boolean> locInstance1 = new HashMap<String, Boolean>();
		locInstance1.put(locationInstanceValue10339, true);

		LocationInstanceParams lip1 = new LocationInstanceParams();
		lip1.CategoryName = locationCategoryValue10339;
		lip1.NumberFieldValue = 10;
		lip1.TextFieldValue = "XYZ";
		lip1.InstanceName = locInstance1;
		hp.clickLocationsMenu();

		boolean createLocInst1 = mlp.createLocation(lip1);
		TestValidation.IsTrue(createLocInst1, 
				"Succesfully created Location Instance  - "+locationInstanceValue10339, 
				"Could NOT create Location instance - "+locationInstanceValue10339 );

		HashMap<String, List<String>> resourceInstances = new LinkedHashMap<String, List<String>>();
		resourceInstances.put(SelectTAB.CUSTOMERS, Arrays.asList(customerInstanceValue10339));
		resourceInstances.put(SelectTAB.EQUIPMENT, Arrays.asList(equipmentInstanceValue10339));

		boolean linkResToLoc1 = mlp.linkResourceToLocation(resourceInstances);
		TestValidation.IsTrue(linkResToLoc1, 
				"Succesfully linked Resource - "+customerInstanceValue10339+" ,"+equipmentInstanceValue10339+" with Location Instance  - "+locationInstanceValue10339, 
				"Could NOT link Resource - "+customerInstanceValue10339+" ,"+equipmentInstanceValue10339+"  with Location instance - "+locationInstanceValue10339 );


		//FORM - Creation and Release

		checkFormName10339	= CommonMethods.dynamicText("10339_Automation_CheckForm");
		numFieldName    = "Numeric Field";
		HashMap<String, List<String>> fields = new HashMap<String, List<String>>();
		fields.put(FIELD_TYPES.NUMERIC, Arrays.asList(numFieldName));
		FormFieldParams ffp = new FormFieldParams();
		ffp.FieldDetails = fields;
		HashMap<String, List<String>> resource = new HashMap<String, List<String>>();
		resource.put(FORMRESOURCETYPES.CUSTOMERS, Arrays.asList(customerCategoryValue10339));
		resource.put(FORMRESOURCETYPES.EQUIPMENT, Arrays.asList(equipmentCategoryValue10339));

		FormDesignParams fdpDets = new FormDesignParams();
		fdpDets.FormType = FORMTYPES.CHECK;
		fdpDets.FormName = checkFormName10339;
		fdpDets.SelectResources = resource;
		fdpDets.DesignFields = Arrays.asList(ffp);
		fdpDets.ReleaseForm = true;

		hp.clickFormDesignerMenu();
		boolean createAndReleaseForm = fdp.createAndReleaseForm(fdpDets);
		TestValidation.IsTrue(createAndReleaseForm,
				"Able to create and release form:" + checkFormName10339,
				"Could NOT able to create and release form:" + checkFormName10339);

		fbp = hp.clickFSQABrowserMenu();
		boolean navigateToforms = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.CUSTOMERS,FSQATAB.FORMS);
		TestValidation.IsTrue(navigateToforms, 
				"For CUSTOMERS category, NAVIGATED to FSQABrowser > Forms tab", 
				"Failed to navigate to FSQABrowser > Forms tab");

		boolean applyFilterAndOpenForm = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, checkFormName10339);
		TestValidation.IsTrue(applyFilterAndOpenForm, 
				"Selected & opened the form - "+checkFormName10339, 
				"Could NOT open the form - "+checkFormName10339);

		//Submit form
		LinkedHashMap<String, List<String>> fillDetails = new LinkedHashMap<String, List<String>>();
		fillDetails.put("Numeric Field", Arrays.asList("10"));

		FormDetails fd = new FormDetails();
		fd.fieldDetails = fillDetails;
		fd.locationName = locationInstanceValue10339;
		fd.resourceName = customerInstanceValue10339;
		fd.isSubmit = true;
		FormOperations fo = new FormOperations(driver);
		boolean submit = fo.fillAndSubmitData(fd);
		TestValidation.IsTrue(submit, "Form submitted " + checkFormName10339, "Failed to submit form " + checkFormName10339);

		mrp = hp.clickResourcesMenu();
		TestValidation.Equals(mrp.error, 
				false, 
				"Opened Manage Resource", 
				"Could Not Open Manage Resource"); 

		boolean unlinkLocationFromResource = mrp.searchAndUnlinkLocationFromResource(RESOURCETYPES.CUSTOMERS, customerInstanceValue10339, Arrays.asList("AutoFTULocationInst1 1"));
		TestValidation.IsTrue(unlinkLocationFromResource, 
				"UNLINKED resource " + customerInstanceValue10339 + " from location " + locationInstanceValue10339, 
				"Failed to UnLink resource " + customerInstanceValue10339 + " from location " + locationInstanceValue10339);	


		fbp = hp.clickFSQABrowserMenu();
		boolean navigateToRecords = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.CUSTOMERS,FSQATAB.RECORDS);
		TestValidation.IsTrue(navigateToRecords, 
				"For CUSTOMERS category, NAVIGATED to FSQABrowser > Records tab", 
				"Failed to navigate to FSQABrowser > Records tab");

		boolean openAndApplySettings3 = fbp.openAndApplySettingsForColumn(COLUMNHEADER.FORMNAME, COLUMNSETTING.FILTER, customerInstanceValue10339);
		TestValidation.IsTrue(openAndApplySettings3, 
				"Searched form - "+customerInstanceValue10339, 
				"Could NOT search form - "+customerInstanceValue10339);

		boolean verifyForm3 = fbp.verifyRecordIsDisplayed(customerInstanceValue10339);
		TestValidation.IsTrue(verifyForm3, 
				"VERIFIED submitted form "+customerInstanceValue10339+" is Displayed in Records tab", 
				"FAILED to verify submitted form in Records tab");
		
		boolean searchNoData = fbp.searchNoData(customerInstanceValue10339);
		TestValidation.IsTrue(searchNoData, 
				"Verified resource -  " + customerInstanceValue10339+" is not associated to location", 
				"Failed to verify resource -  " + customerInstanceValue10339+" is not associated to location");

		fdp.clickFormDesignerMenu();
		TestValidation.IsTrue(!(fdp.error), 
				"OPENED 'Form Designer'", 
				"Could NOT open the 'Form Designer'");
		
		boolean searchNoRes = fdp.searchResourceInstanceNoDataFound(FORMRESOURCETYPES.CUSTOMERS, customerInstanceValue10339 );
		TestValidation.IsTrue(searchNoRes, 
				"Verified resource -  " + customerInstanceValue10339+" is not associated to location in Form Designer", 
				"Failed to verify resource -  " + customerInstanceValue10339+" is not associated to location in Form Designer");

		hp.clickLocationsMenu();
		boolean unlinkCustInst = mlp.unlinkResourceFromLocation(locationCategoryValue10339, locationInstanceValue10339, SelectTAB.EQUIPMENT, equipmentInstanceValue10339, SelectTAB.EQUIPMENT);
		TestValidation.IsTrue(unlinkCustInst, 
				"Successfully Unchecked/Unlink resource - "+equipmentInstanceValue10339+" from location - "+locationInstanceValue10339, 
				"FAil to Uncheck/Unlink resource - "+equipmentInstanceValue10339+" from location - "+locationInstanceValue10339);

		fbp = hp.clickFSQABrowserMenu();
		boolean navigateToforms1 = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.EQUIPMENT,FSQATAB.FORMS);
		TestValidation.IsTrue(navigateToforms1, 
				"For EQUIPMENT category, NAVIGATED to FSQABrowser > Forms tab", 
				"Failed to navigate to FSQABrowser > Forms tab");
		
		boolean searchNoData1 = fbp.searchNoData(equipmentInstanceValue10339);
		TestValidation.IsTrue(searchNoData1, 
				"Verified resource -  " + equipmentInstanceValue10339+" is not associated to location", 
				"Failed to verify resource -  " + equipmentInstanceValue10339+" is not associated to location");

		fdp.clickFormDesignerMenu();
		TestValidation.IsTrue(!(fdp.error), 
				"OPENED 'Form Designer'", 
				"Could NOT open the 'Form Designer'");
		
		boolean searchNoRes1 = fdp.searchResourceInstanceNoDataFound(FORMRESOURCETYPES.EQUIPMENT, equipmentInstanceValue10339 );
		TestValidation.IsTrue(searchNoRes1, 
				"Verified resource -  " + equipmentInstanceValue10339+" is not associated to location in Form Designer", 
				"Failed to verify resource -  " + equipmentInstanceValue10339+" is not associated to location in Form Designer");

	}
	@AfterClass(alwaysRun = true)
	public void closeBrowser() throws InterruptedException {
		driver.close();
	}
}
