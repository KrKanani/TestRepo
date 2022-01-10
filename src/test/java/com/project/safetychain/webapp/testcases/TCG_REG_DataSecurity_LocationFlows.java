package com.project.safetychain.webapp.testcases;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;


import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.project.safetychain.webapp.pageobjects.HomePage;
import com.project.safetychain.webapp.pageobjects.LoginPage;
import com.project.safetychain.webapp.pageobjects.ManageLocationPage;
import com.project.safetychain.webapp.pageobjects.ManageLocationPage.LocationInstanceParams;
import com.project.safetychain.webapp.pageobjects.ManageLocationPage.SelectResource;
import com.project.safetychain.webapp.pageobjects.ManageLocationPage.SelectTAB;
import com.project.safetychain.webapp.pageobjects.ManageResourcePage;
import com.project.safetychain.webapp.pageobjects.ManageResourcePage.RESOURCETYPES;
import com.project.safetychain.webapp.pageobjects.ManageResourcePage.ResourceDetailParams;
import com.project.safetychain.webapp.pageobjects.ResourceDesignerPage;
import com.project.safetychain.webapp.pageobjects.RolesManagerPage;
import com.project.safetychain.webapp.pageobjects.UserManagerPage;
import com.project.safetychain.webapp.pageobjects.WorkGroupsPage;
import com.project.safetychain.webapp.pageobjects.CommonPage.TIMEZONE;
import com.project.safetychain.webapp.pageobjects.DocumentManagementPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.COLUMNHEADER;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.COLUMNSETTING;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.FSQARESOURCE;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.FSQATAB;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.TASKDETAILS;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FIELD_TYPES;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FORMRESOURCETYPES;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FORMTYPES;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FormDesignParams;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FormFieldParams;
import com.project.safetychain.webapp.pageobjects.ResourceDesignerPage.CATEGORYTYPES;
import com.project.safetychain.webapp.pageobjects.UserManagerPage.USERFIELDS;
import com.project.safetychain.webapp.pageobjects.UserManagerPage.UsrDetailParams;
import com.project.safetychain.webapp.testcases.support.FormOperations;
import com.project.safetychain.webapp.testcases.support.FormOperations.FormDetails;
import com.project.safetychain.webapp.pageobjects.FSQABrowserFormsPage;
//import com.project.safetychain.webapp.pageobjects.FSQABrowserFormsPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.utilities.CommonMethods;
import com.project.utilities.ControlActions;
import com.project.utilities.DateTime;

public class TCG_REG_DataSecurity_LocationFlows extends TestBase{
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
	DateTime dt = new DateTime();

	public static String workGroupName;
	public static String roleName;
	public static String userUN, userUN1, userUN2, userUN3, userUN31513;
	public static String userFN;
	public static String userLN;

	public static String task31513;

	public static List<String> userLocation;
	public static List<String> userRole;

	public static String locationCategoryValue, customerCategoryValue, 
	equipmentCategoryValue, itemCategoryValue, supplierCategoryValue;
	public static String locationInstanceValue, customerInstanceValue,locationInstanceValue1,
	equipmentInstanceValue, itemInstanceValue, supplierInstanceValue;
	public static String resourceCategory;
	public static String newLocInst, newlocationInstanceValue, newlocationInstanceValue1, newlocationInstanceValue2, newlocationInstanceValue3, locationInstValue;
	public static String newLocationCategoryValue, newCustomerCategoryValue, newEquipmentCategoryValue;
	public static String newCustomerInstanceValue, newEquipmentInstanceValue;
	public static String locationCategoryValue31513, customerCategoryValue31513, equipmentCategoryValue31513, locationInstanceValue31513, customerInstanceValue31513, equipmentInstanceValue31513;

	public static String checkFormName, questionnaireFormName, auditFormName, numFieldName, numericFieldName, singleLineTxtFN, section,checkFormName31513;

	public static List<String> userLocation2;
	public static List<String> userRole2,workgroup;
	public static List<String> defaultLocation;
	public static String pin;

	@BeforeClass(alwaysRun = true)
	public void groupInit() throws Exception {

		workGroupName = CommonMethods.dynamicText("WG");
		roleName = CommonMethods.dynamicText("RL");
		userUN = CommonMethods.dynamicText("UN");

		userUN2 = CommonMethods.dynamicText("U_N");
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

		newlocationInstanceValue = CommonMethods.dynamicText("New_Loc2_Inst");
		newlocationInstanceValue1 = CommonMethods.dynamicText("Loc2_Inst");
		newlocationInstanceValue2 = CommonMethods.dynamicText("New_Loc2_Inst_2");
		locationInstValue = CommonMethods.dynamicText("Location_Inst");
		customerInstanceValue = CommonMethods.dynamicText("Cust_Inst");
		equipmentInstanceValue = CommonMethods.dynamicText("Equip_Inst");
		itemInstanceValue = CommonMethods.dynamicText("Item_Inst");
		supplierInstanceValue = CommonMethods.dynamicText("Supp_Inst");
		task31513 = CommonMethods.dynamicText("Task_31513");

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

		RolesManagerPage rmp = hp.clickRolesMenu();
		if(!rmp.createRole(roleName)) {
			TCGFailureMsg = "Could not create role - " + roleName;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}


		//user Creation
		userLocation.add(locationInstanceValue);
		userRole.add(roleName);
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
		if(!userCreation) {
			TCGFailureMsg = "Could NOT create user - " + userUN;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}


	}

	@Test(description = "9117 - Data Security - Manage Locations - Resources can be\r\n"
			+ "“revealed” to Locations via Manage Locations\r\n"
			+ "")
	public void TestCase_10129() {

		mlp = hp.clickLocationsMenu();
		TestValidation.Equals(mlp.error, 
				false, 
				"Opened Manage Location", 
				"Could Not Open Manage Location"); 

		locationInstanceValue1 = CommonMethods.dynamicText("Loc1_Inst");
		boolean addNewInstance = mlp.createLocationInstance(locationCategoryValue, locationInstanceValue1);
		TestValidation.IsTrue(addNewInstance, 
				"Succesfully added new Location Instance " + locationInstanceValue1, 
				"FAIL to add new Location Instance " + locationInstanceValue1);

		boolean selectTab1 = mlp.selectTab(SelectTAB.CUSTOMERS);
		TestValidation.IsTrue(selectTab1, 
				"Succesfully selected " + selectTab1, 
				"FAIL to select " + selectTab1);	

		boolean selectCustTabNCheck = mlp.selectFirstCheckboxForResourceCategory(SelectTAB.CUSTOMERS);
		TestValidation.IsTrue(selectCustTabNCheck, 
				"Successfully clicked customer tab and Checked first customer", 
				"FAIL to click customer tab and Checked first customer");

		String firstCustomerResource = mlp.getFirstResourceName(SelectResource.CUSTOMERS);
		TestValidation.IsTrue(firstCustomerResource != null, 
				"Verified First resource selected is " + firstCustomerResource,
				"FAIL to verify that first resource selected is " + firstCustomerResource);

		boolean clickSave1 = mlp.clickOnSave();
		TestValidation.IsTrue(clickSave1, 
				"Successfully Saved First Customer", 
				"FAIL to save First Customer");

		boolean selectTab2 = mlp.selectTab(SelectTAB.EQUIPMENT);
		TestValidation.IsTrue(selectTab1, 
				"Succesfully selected " + selectTab2, 
				"FAIL to select " + selectTab2);

		boolean selectEquipTabNCheck = mlp.selectFirstCheckboxForResourceCategory(SelectTAB.EQUIPMENT);
		TestValidation.IsTrue(selectEquipTabNCheck, 
				"Successfully clicked Equipment tab and Checked first Equipment", 
				"FAIL to click Equipment tab and Checked first Equipment");

		String firstEquipmentResource =  mlp.getFirstResourceName(SelectResource.EQUIPMENT);

		boolean clickSave2 = mlp.clickOnSave();
		TestValidation.IsTrue(clickSave2, 
				"Successfully Saved First Equipment " + firstEquipmentResource, 
				"FAIL to save First Equipment " + firstEquipmentResource);


		boolean selectTab3 = mlp.selectTab(SelectTAB.ITEMS);
		TestValidation.IsTrue(selectTab3, 
				"Succesfully selected " + selectTab3, 
				"FAIL to select " + selectTab3);	

		boolean selectItemTabNCheck = mlp.selectFirstCheckboxForResourceCategory(SelectTAB.ITEMS);
		TestValidation.IsTrue(selectItemTabNCheck, 
				"Successfully clicked Items tab and Checked first Item", 
				"FAIL to click Items tab and Checked first Item");

		String firstItemResource = mlp.getFirstResourceName(SelectResource.ITEMS);

		boolean clickSave3 = mlp.clickOnSave();
		TestValidation.IsTrue(clickSave3, 
				"Successfully Saved First Item " + firstItemResource, 
				"FAIL to save First Item " + firstItemResource);

		boolean selectTab4 = mlp.selectTab(SelectTAB.SUPPLIERS);
		TestValidation.IsTrue(selectTab4, 
				"Succesfully selected " + selectTab4, 
				"FAIL to select " + selectTab4);	

		boolean selectSupTabNCheck = mlp.selectFirstCheckboxForResourceCategory(SelectTAB.SUPPLIERS);
		TestValidation.IsTrue(selectSupTabNCheck, 
				"Successfully clicked Suppliers tab and Checked first supplier", 
				"FAIL to click Suppliers tab and Checked first supplier");

		String firstSupplierResource =  mlp.getFirstResourceName(SelectResource.SUPPLIERS);
		boolean clickSave4 = mlp.clickOnSave();
		TestValidation.IsTrue(clickSave4, 
				"Successfully Saved First Supplier " + firstSupplierResource, 
				"FAIL to save First Supplier " + firstSupplierResource);

		boolean chkUserToAssociate = mlp.linkUser(userUN);
		TestValidation.IsTrue(chkUserToAssociate, 
				"Succesfully Associated user" + userUN + "with selected location", 
				"FAILED to Associate user" + userUN + " with selected location");

		boolean logoutUser = hp.userLogout();
		TestValidation.IsTrue(logoutUser, 
				"Logged out with user - " + adminUsername, 
				"Could NOT logout with user - " + adminUsername);

		boolean loginNewUser = lp.loginAfterUserCreation(userUN, GenericPassword, GenericNewPassword);
		TestValidation.IsTrue(loginNewUser, 
				"Logged In with new user - " + userUN, 
				"Could NOT login with user - " + userUN);

		String resourceName[] = CommonMethods.splitAndGetString(firstCustomerResource, " > ");
		String resourceInstanceName = resourceName[resourceName.length-1];

		fbp = hp.clickFSQABrowserMenu();
		TestValidation.Equals(fbp.error, 
				false, 
				"Opened FSQA Browser", 
				"Could Not Open FSQA Browser"); 

		boolean searchAndSelect = fbp.searchSelect(resourceInstanceName.trim());
		TestValidation.IsTrue(searchAndSelect, 
				"Succesfully searched and selected resource "+ resourceInstanceName, 
				"FAILED to search and select resource " + resourceInstanceName);

		boolean locIsRevealed = fbp.selectLocationAndCheckLocationIsDisplayed(locationInstanceValue1);
		TestValidation.IsTrue(locIsRevealed, 
				"Succesfully verified resources are revealed to location " + locationInstanceValue1, 
				"Failed to verify that resources are revealed to locations or not for "+ locationInstanceValue1);

		boolean logoutUser1 = hp.userLogout();
		TestValidation.IsTrue(logoutUser1, 
				"Logged out with user - " + adminUsername, 
				"Could NOT logout with user - " + adminUsername);

		hp = lp.performLogin(adminUsername,adminPassword);
		TestValidation.Equals(hp.error,
				false,
				"Logged in with user - " + adminUsername, 
				"Could NOT logout with user - " + adminUsername);

		mlp = hp.clickLocationsMenu();
		TestValidation.Equals(mlp.error, 
				false, 
				"Opened Manage Location", 
				"Could Not Open Manage Location"); 

		boolean findAndClickLoc = mlp.scrollAndFindLocationInstance(locationCategoryValue, locationInstanceValue1);
		TestValidation.IsTrue(findAndClickLoc, 
				"Succesfully clicked Location Category and Location Instance " + locationInstanceValue1, 
				"FAIL to click Location Category and Location Instance " + locationInstanceValue1);

		boolean selectTab5 = mlp.selectTab(SelectTAB.CUSTOMERS);
		TestValidation.IsTrue(selectTab5, 
				"Succesfully selected " + selectTab5,
				"FAIL to select " + selectTab5);	

		boolean selectCustTabNCheck2 = mlp.selectFirstCheckboxForResourceCategory(SelectTAB.CUSTOMERS);
		TestValidation.IsTrue(selectCustTabNCheck2, 
				"Succesfully clicked on Customer tab and Unchecked the selected Customer", 
				"FAIL to click on Customer tab and Unchecked the selected Customer");

		boolean clickSave5 = mlp.clickOnSave();
		TestValidation.IsTrue(clickSave5, 
				"Saved after unchecking selected Customer", 
				"FAIL to save after unchecking selected Customer");

		boolean logoutUser2 = hp.userLogout();
		TestValidation.IsTrue(logoutUser2, 
				"Logged out with user " + adminUsername, 
				"Could NOT logout with user " + adminUsername);

		hp = lp.performLogin(userUN, GenericNewPassword);
		TestValidation.IsTrue(logoutUser2, 
				"Logged in with user " + userUN, 
				"Could NOT login with user " + userUN);

		fbp = hp.clickFSQABrowserMenu();
		TestValidation.Equals(fbp.error, 
				false, 
				"Opened FSQA Browser", 
				"Could Not Open FSQA Browser"); 

		boolean searchNoData = fbp.searchNoData(resourceInstanceName.trim());
		TestValidation.IsTrue(searchNoData, 
				"Selected resource is not associated to user " + resourceInstanceName, 
				"Failed, resources are associated to user " + resourceInstanceName);

		boolean logoutUser3 = hp.userLogout();
		TestValidation.IsTrue(logoutUser3, 
				"Logged out with user - " + userUN, 
				"Could NOT logout with user - " + userUN);

	}

	@Test(description = "9118-Data Security - Manage Locations - No resources are\r\n"
			+ "“revealed” to a new Location by default when it is added")
	public void TestCase_10159() {
		hp.userLogout();
		hp = lp.performLogin(adminUsername, adminPassword);
		TestValidation.Equals(hp.error, 
				false, 
				"Successfully Login with " + adminUsername, 
				"Could Not Login with " + adminUsername);


		//Resource creation - Customer
		HashMap<String,Boolean> custInstance = new HashMap<String, Boolean>();
		custInstance.put(customerInstanceValue, true);

		ResourceDetailParams rd1 = new ResourceDetailParams();
		rd1.CategoryName = customerCategoryValue;
		rd1.CategoryType = RESOURCETYPES.CUSTOMERS;
		rd1.NumberFieldValue = 15;
		rd1.TextFieldValue = "LMN";
		rd1.InstanceNames = custInstance;
		rd1.LocationInstanceValue = locationInstanceValue;

		mrp = hp.clickResourcesMenu();
		TestValidation.Equals(mrp.error, 
				false, 
				"Opened Manage Resource", 
				"Could Not Open Manage Resource"); 

		boolean createResource1 = mrp.createResourceLinkLocation(rd1);
		TestValidation.IsTrue(createResource1, 
				"Succesfully created Customer Instance for category - " + customerCategoryValue, 
				"Could NOT create Customer Instance for category - " + customerCategoryValue);

		//Resource creation - Equipment
		HashMap<String,Boolean> equipInstance = new HashMap<String, Boolean>();
		equipInstance.put(equipmentInstanceValue, true);

		ResourceDetailParams rd2 = new ResourceDetailParams();
		rd2.CategoryName = equipmentCategoryValue;
		rd2.CategoryType = RESOURCETYPES.EQUIPMENT;
		rd2.NumberFieldValue = 15;
		rd2.TextFieldValue = "LMN";
		rd2.InstanceNames = equipInstance;
		rd2.LocationInstanceValue = locationInstanceValue;	

		mrp = hp.clickResourcesMenu();
		TestValidation.Equals(mrp.error, 
				false, 
				"Opened Manage Resource", 
				"Could Not Open Manage Resource"); 

		boolean createResource2 = mrp.createResourceLinkLocation(rd2);
		TestValidation.IsTrue(createResource2, 
				"Succesfully created Equipment Instance for category - " + equipmentCategoryValue, 
				"Could NOT create Equipment Instance for category - " + equipmentCategoryValue);

		//Resource creation - Item
		HashMap<String,Boolean> itemInstance = new HashMap<String, Boolean>();
		itemInstance.put(itemInstanceValue, true);

		ResourceDetailParams rd3 = new ResourceDetailParams();
		rd3.CategoryName = itemCategoryValue;
		rd3.CategoryType = RESOURCETYPES.ITEMS;
		rd3.NumberFieldValue = 15;
		rd3.TextFieldValue = "LMN";
		rd3.InstanceNames = itemInstance;
		rd3.LocationInstanceValue = locationInstanceValue;	

		mrp = hp.clickResourcesMenu();
		TestValidation.Equals(mrp.error, 
				false, 
				"Opened Manage Resource", 
				"Could Not Open Manage Resource"); 

		boolean createResource3 = mrp.createResourceLinkLocation(rd3);
		TestValidation.IsTrue(createResource3, 
				"Succesfully created Item Instance for category - " + itemCategoryValue, 
				"Could NOT create Item Instance for category - " + itemCategoryValue);


		//Resource creation - Supplier
		HashMap<String,Boolean> suppInstance = new HashMap<String, Boolean>();
		suppInstance.put(supplierInstanceValue, true);

		ResourceDetailParams rd4 = new ResourceDetailParams();
		rd4.CategoryName = supplierCategoryValue;
		rd4.CategoryType = RESOURCETYPES.SUPPLIERS;
		rd4.NumberFieldValue = 15;
		rd4.TextFieldValue = "LMN";
		rd4.InstanceNames = suppInstance;
		rd4.LocationInstanceValue = locationInstanceValue;

		mrp = hp.clickResourcesMenu();
		TestValidation.Equals(mrp.error, 
				false, 
				"Opened Manage Resource", 
				"Could Not Open Manage Resource"); 

		boolean createResource4 = mrp.createResourceLinkLocation(rd4);
		TestValidation.IsTrue(createResource4, 
				"Succesfully created Supplier Instance for category - " + supplierCategoryValue, 
				"Could NOT create Supplier Instance for category - " + supplierCategoryValue);

		mlp = hp.clickLocationsMenu();
		TestValidation.Equals(mlp.error, 
				false, 
				"Opened Manage Location", 
				"Could Not Open Manage Location"); 

		boolean addNewInstance = mlp.createLocationInstance(locationCategoryValue, newlocationInstanceValue);
		TestValidation.IsTrue(addNewInstance, 
				"Succesfully added new Location Instance " + newlocationInstanceValue, 
				"FAIL to add new Location Instance " + newlocationInstanceValue);

		boolean selectTab1 = mlp.selectTab(SelectTAB.CUSTOMERS);
		TestValidation.IsTrue(selectTab1, 
				"Succesfully selected CUSTOMERS Tab", 
				"FAIL to select CUSTOMERS Tab ");

		boolean ResourcesAreUnselected1 = mlp.resourceCategoryAreNotSelected(SelectTAB.CUSTOMERS);
		TestValidation.IsTrue(ResourcesAreUnselected1, 
				"Verified Resources are not Selected for CUSTOMERS" , 
				"FAIL to verify that resources are selected or not for CUSTOMERS");

		boolean selectTab2 = mlp.selectTab(SelectTAB.EQUIPMENT);
		TestValidation.IsTrue(selectTab2, 
				"Succesfully selected EQUIPMENT Tab", 
				"FAIL to select Customers Tab ");

		boolean ResourcesAreUnselected2 = mlp.resourceCategoryAreNotSelected(SelectTAB.EQUIPMENT);
		TestValidation.IsTrue(ResourcesAreUnselected2, 
				"Verified Resources are not Selected for EQUIPMENT", 
				"FAIL to verify that resources are selected or not for EQUIPMENT");

		boolean selectTab3 = mlp.selectTab(SelectTAB.ITEMS);
		TestValidation.IsTrue(selectTab3, 
				"Succesfully selected ITEMS Tab", 
				"FAIL to select ITEMS Tab ");

		boolean ResourcesAreUnselected3 = mlp.resourceCategoryAreNotSelected(SelectTAB.ITEMS);
		TestValidation.IsTrue(ResourcesAreUnselected3, 
				"Verified Resources are not Selected for ITEMS", 
				"FAIL to verify that resources are selected or not for ITEMS");

		boolean selectTab4 = mlp.selectTab(SelectTAB.SUPPLIERS);
		TestValidation.IsTrue(selectTab4, 
				"Succesfully selected SUPPLIERS Tab", 
				"FAIL to select SUPPLIERS Tab ");

		boolean ResourcesAreUnselected4 = mlp.resourceCategoryAreNotSelected(SelectTAB.SUPPLIERS);
		TestValidation.IsTrue(ResourcesAreUnselected4, 
				"Verified Resources are not Selected for SUPPLIERS", 
				"FAIL to verify that resources are selected or not for SUPPLIERS");


		mrp = hp.clickResourcesMenu();
		TestValidation.Equals(mrp.error, 
				false, 
				"Opened Manage Resource", 
				"Could Not Open Manage Resource"); 

		boolean ClkEquipTabAndDisableResource = mrp.searchAndDisableResource(RESOURCETYPES.EQUIPMENT, equipmentInstanceValue);
		TestValidation.IsTrue(ClkEquipTabAndDisableResource, 
				"Succesfully Disabled " + equipmentInstanceValue, 
				"FAIL to Disable " + equipmentInstanceValue);

		mlp = hp.clickLocationsMenu();
		TestValidation.Equals(mlp.error, 
				false, 
				"Opened Manage Location", 
				"Could Not Open Manage Location"); 

		boolean findAndClickLoc = mlp.scrollAndFindLocationInstance(locationCategoryValue, newlocationInstanceValue);
		TestValidation.IsTrue(findAndClickLoc, 
				"Succesfully clicked Location Category "+locationCategoryValue+" and Location Instance " + newlocationInstanceValue, 
				"FAIL to click Location Category "+locationCategoryValue+" and Location Instance " + newlocationInstanceValue);

		boolean selectTab6 = mlp.selectTab(SelectTAB.EQUIPMENT);
		TestValidation.IsTrue(selectTab6, 
				"Succesfully selected " + SelectTAB.EQUIPMENT, 
				"FAIL to select " + SelectTAB.EQUIPMENT);

		boolean searchResource = mlp.searchResource(equipmentInstanceValue);
		TestValidation.IsTrue(searchResource, 
				"Succesfully selected " + equipmentInstanceValue, 
				"FAIL to select " + equipmentInstanceValue);

		boolean ResourceNotDisplay = mlp.chkEquipmentResourceIsNotDisplayed();
		TestValidation.IsTrue(ResourceNotDisplay, 
				"Succesfully verified Resource is not Displayed", 
				"FAILED to verify Resource is displaying");

		boolean selectTab7 = mlp.selectTab(SelectTAB.ITEMS);
		TestValidation.IsTrue(selectTab7, 
				"Succesfully selected " + SelectTAB.ITEMS, 
				"FAIL to select " + SelectTAB.ITEMS);

		boolean searchResource1 = mlp.searchResource(itemInstanceValue);
		TestValidation.IsTrue(searchResource1, 
				"Succesfully searched resource " + itemInstanceValue, 
				"FAIL to search resource " + itemInstanceValue);

		boolean selectItemTabNCheck = mlp.selectFirstCheckboxForResourceCategory(SelectTAB.ITEMS);
		TestValidation.IsTrue(selectItemTabNCheck, 
				"Successfully clicked Items tab and selected Item "+ selectItemTabNCheck, 
				"FAIL to click Items tab and select  Item "+ selectItemTabNCheck);

		String itemResource = mlp.getFirstResourceName(SelectResource.ITEMS);
		TestValidation.IsTrue(itemResource != null, 
				"Verified resource is selected  " + itemResource,
				"FAIL to verify that resource is selected for " + itemResource);

		boolean getHeadingText = controlActions.isElementDisplay(mlp.AllItemsResourceHeader);
		TestValidation.IsTrue(getHeadingText, 
				"Verified that page is not changed ", 
				"FAIL to verify if page is change  ");

		boolean selectTab8 = mlp.selectTab(SelectTAB.SUPPLIERS);
		TestValidation.IsTrue(selectTab8, 
				"Succesfully selected " + SelectTAB.SUPPLIERS, 
				"FAIL to select " + SelectTAB.SUPPLIERS);

		boolean getHeadingText1 = controlActions.isElementDisplay(mlp.AllItemsResourceHeader);
		TestValidation.IsTrue(getHeadingText1, 
				"Verified that page is not changed ", 
				"FAIL to verify if page is change ");

		boolean selectTab9 = mlp.selectTab(SelectTAB.CUSTOMERS);
		TestValidation.IsTrue(selectTab9, 
				"Succesfully selected " + SelectTAB.CUSTOMERS, 
				"FAIL to select " + SelectTAB.CUSTOMERS);

		boolean getHeadingText2 = controlActions.isElementDisplay(mlp.AllItemsResourceHeader);
		TestValidation.IsTrue(getHeadingText2, 
				"Verified that page is not changed ", 
				"FAIL to verify if page is change  ");

		boolean selectTab10 = mlp.selectTab(SelectTAB.EQUIPMENT);
		TestValidation.IsTrue(selectTab10, 
				"Succesfully selected " + SelectTAB.EQUIPMENT, 
				"FAIL to select " + SelectTAB.EQUIPMENT);

		boolean getHeadingText3 = controlActions.isElementDisplay(mlp.AllItemsResourceHeader);
		TestValidation.IsTrue(getHeadingText3, 
				"Verified that page is not changed ", 
				"FAIL to verify if page is change ");

		boolean selectTab11 = mlp.selectTab(SelectTAB.ITEMS);
		TestValidation.IsTrue(selectTab8, 
				"Succesfully selected " + selectTab11, 
				"FAIL to select " + selectTab11);

		boolean getHeadingText4 = controlActions.isElementDisplay(mlp.AllItemsResourceHeader);
		TestValidation.IsTrue(getHeadingText4, 
				"Verified that page is not changed ", 
				"FAIL to verify if page is change ");

		boolean clickSave1 = mlp.clickOnSave();
		TestValidation.IsTrue(clickSave1, 
				"Successfully Saved Resource", 
				"FAIL to save Resource");

		String resourceName1[] = CommonMethods.splitAndGetString(itemResource, ">");
		String resourceInstanceName1 = resourceName1[resourceName1.length-1];

		fbp = hp.clickFSQABrowserMenu();
		TestValidation.Equals(fbp.error, 
				false, 
				"Opened FSQA Browser", 
				"Could Not Open FSQA Browser"); 

		boolean select = fbp.selectDropDownValue(FORMRESOURCETYPES.ITEMS);
		TestValidation.IsTrue(select, 
				"Selected Items tab from drop down", 
				"Failed to select Items tab from drop down");

		boolean searchAndSelect = fbp.searchSelect(resourceInstanceName1.trim());
		TestValidation.IsTrue(searchAndSelect, 
				"Succesfully searched and selected resource "+ resourceInstanceName1, 
				"FAILED to search and select resource " + resourceInstanceName1);

		boolean itemIsRevealed = fbp.selectLocationAndCheckLocationIsDisplayed(locationInstanceValue);
		TestValidation.IsTrue(itemIsRevealed, 
				"Succesfully verified Items resources are revealed to location " + locationInstanceValue, 
				"Failed to verify that Items resources are revealed to locations or not for "+ locationInstanceValue);

	}

	@Test(description = " 9123-Data Security - Manage Users - A special “ALL” option will be\r\n"
			+ "available in the Location assignment field to aid in configuration of administrative\r\n"
			+ "users who need access to all Location data")
	public void TestCase_10248() {

		//user Creation
		String username = CommonMethods.dynamicText("UN");
		String firstName = CommonMethods.dynamicText("FN");
		String lastName = CommonMethods.dynamicText("LN");
		String email = CommonMethods.dynamicText("EMAIL");
		UsrDetailParams udp = new UsrDetailParams();
		udp.Username = username;
		udp.Password = GenericPassword;
		udp.FirstName = firstName;
		udp.LastName = lastName;
		udp.Email = email +"@test.com";
		udp.Timezone = "Republic of India";
		udp.Phone = "8976543211";
		udp.Locations = Arrays.asList("ALL");

		udp.DefaultLocation = defaultLocation;
		udp.WorkGroups1 = workGroupName;
		udp.Pin = pin;
		udp.Roles = Arrays.asList("SuperAdmin");	

		ump = hp.clickUsersMenu();
		TestValidation.Equals(ump.error, 
				false, 
				"CLICKED on Users menu", 
				"Could NOT click on Users menu"); 

		boolean clickAddBtn = ump.clickAddNewUserBtn();
		TestValidation.IsTrue(clickAddBtn, 
				"CLICKED on Add User button", 
				"Could NOT click on Add User button");


		boolean userCreation = ump.setUsrDetails(udp);
		TestValidation.IsTrue(userCreation, 
				"Details set for user - " + username, 
				"Could NOT set details for user - " + username);

		boolean saveUser = ump.clickSaveBtn();
		TestValidation.IsTrue(saveUser, 
				"User " + username + " is SAVED successfully", 
				"Could NOT create User " + username);

		boolean searchUmpUser = ump.searchAndOpenUsrDetails(USERFIELDS.USERNAME, username);
		TestValidation.IsTrue(searchUmpUser, 
				"User " + username + " is Searched and opened Succesfully",
				"Could NOT Search and open " + username );

		boolean allOptionDisplayed = ump.AllOptionIsDisplayed();
		TestValidation.IsTrue(allOptionDisplayed, 
				"All option is Displayed",            
				"All option is not displayed");

		boolean allOptionIsCapital = ump.AllOptionIsCapitalized();
		TestValidation.IsTrue(allOptionIsCapital, 
				"All option is displayed in UpperCase",            
				"All option is not displayed in UpperCase");

		boolean closePopup = ump.closeWindow();
		TestValidation.IsTrue(closePopup, 
				"CLOSED Popup window",            
				"Failed to close Popup window");

		mlp = hp.clickLocationsMenu();
		TestValidation.Equals(mlp.error, 
				false, 
				"Opened Manage Location", 
				"Could Not Open Manage Location"); 

		boolean findAndClickLoc = mlp.scrollAndFindLocationInstance(locationCategoryValue, locationInstanceValue);
		TestValidation.IsTrue(findAndClickLoc, 
				"Succesfully clicked Location Category and Location Instance " + locationInstanceValue, 
				"FAIL to click Location Category and Location Instance " + locationInstanceValue);

		boolean selectTab  = mlp.selectTab(SelectTAB.USERS);
		TestValidation.IsTrue(selectTab, 
				"Succesfully selected user tab", 
				"FAIL to select user tab");	

		boolean searchUser1= mlp.searchUser(username);
		TestValidation.IsTrue(searchUser1, 
				"Succesfully searched user "+ username,
				"FAIL to search user "+ username);

		boolean chkUserIsDisableOrNot = mlp.usercheckboxIsDisableOrEnable(username);
		TestValidation.IsTrue(chkUserIsDisableOrNot, 
				"Succesfully Verified that user " +username+ " has access to every location in the system",
				"FAIL to verify that user" +username+ " is having access to every location in system or not");

		boolean addNewInstance = mlp.createLocationInstance(locationCategoryValue, newlocationInstanceValue1);
		TestValidation.IsTrue(addNewInstance, 
				"Succesfully added new Location Instance " + newlocationInstanceValue1, 
				"FAIL to add new Location Instance " + newlocationInstanceValue1);

		boolean selectTab1  = mlp.selectTab(SelectTAB.USERS);
		TestValidation.IsTrue(selectTab1, 
				"Succesfully selected user tab", 
				"FAIL to select user tab");	

		boolean searchUser2= mlp.searchUser(username);
		TestValidation.IsTrue(searchUser2, 
				"Succesfully searched user "+ username,
				"FAIL to search user "+ username);

		boolean chkUserIsDisableOrNot1 = mlp.usercheckboxIsDisableOrEnable(username);
		TestValidation.IsTrue(chkUserIsDisableOrNot1, 
				"Succesfully Checked user "+username+ " is Disabled, Since the user "+username+" is Disabled we cannot add or remove",
				"FAIL " +username+ " is Enable , We can Add or remove user");

		ump = hp.clickUsersMenu();
		TestValidation.Equals(ump.error, 
				false, 
				"CLICKED on Users menu", 
				"Could NOT click on Users menu"); 

		boolean searchUmpUser1 = ump.searchAndOpenUsrDetails(USERFIELDS.USERNAME, username);
		TestValidation.IsTrue(searchUmpUser1, 
				"User " + username + " is Searched and opened Succesfully",
				"Could NOT Search and open " + username );

		boolean removeAll = ump.removeLocationAll();
		TestValidation.IsTrue(removeAll, 
				"Succesfully removed ALL location from locations field", 
				"FAIL to remove All location from locations field");

		boolean alert = ump.alertMsgDisplay();
		TestValidation.IsTrue(alert, 
				"Verified that after removing All location Alert message is displayed", 
				"FAIL to verify that after removing All location no alert message is display");

		boolean setNewLocation = ump.setMultiSelectValue(USERFIELDS.LOCATIONS,Arrays.asList(locationInstanceValue));
		TestValidation.IsTrue(setNewLocation, 
				"Succesfully set new Location "+locationInstanceValue+" in locations field", 
				"FAIL to set new location " +locationInstanceValue+ " in locations field");

		boolean saveUser1 = ump.clickSaveBtn();
		TestValidation.IsTrue(saveUser1, 
				"User " + username + " is SAVED successfully", 
				"Could NOT create User " + username);

		mlp = hp.clickLocationsMenu();
		TestValidation.Equals(mlp.error, 
				false, 
				"Opened Manage Location", 
				"Could Not Open Manage Location"); 

		boolean findAndClickLoc1 = mlp.scrollAndFindLocationInstance(locationCategoryValue, locationInstanceValue);
		TestValidation.IsTrue(findAndClickLoc1, 
				"Succesfully clicked Location Category and Location Instance " + locationInstanceValue, 
				"FAIL to click Location Category and Location Instance " + locationInstanceValue);

		boolean selectTab2  = mlp.selectTab(SelectTAB.USERS);
		TestValidation.IsTrue(selectTab2, 
				"Succesfully selected user tab", 
				"FAIL to select user tab");	

		boolean searchUser = mlp.searchUser(username);
		TestValidation.IsTrue(searchUser, 
				"Succesfully searched user "+ username,
				"FAIL to search user "+ username);

		boolean chkUserIsDisableOrNot3 = mlp.usercheckboxIsDisableOrEnable(username);
		TestValidation.IsTrue(chkUserIsDisableOrNot3, 
				"Succesfully Verified that user " +username+ " dont have access to every location in the system",
				"FAIL to verify that user" +username+ " is having access to every location in system or not");


	}

	@Test(description = "9124-Data Security - Manage Locations - Only users with the “ALL”\r\n"
			+ "option in the Location control of their User Profile should be automatically\r\n"
			+ "associated with a new Location when it is added\r\n")
	public void TestCase_10256() {


		ump = hp.clickUsersMenu();
		TestValidation.Equals(ump.error, 
				false, 
				"SUccesfully Opened Manage User", 
				"Could NOT Open Manage User"); 

		boolean clickAddBtn = ump.clickAddNewUserBtn();
		TestValidation.IsTrue(clickAddBtn, 
				"CLICKED on Add User button", 
				"Could NOT click on Add User button");

		//user Creation
		String username = CommonMethods.dynamicText("UN");
		String firstName = CommonMethods.dynamicText("FN");
		String lastName = CommonMethods.dynamicText("LN");
		String email = CommonMethods.dynamicText("EMAIL");
		UsrDetailParams udp = new UsrDetailParams();
		udp.Username = username;
		udp.Password = GenericPassword;
		udp.FirstName = firstName;
		udp.LastName = lastName;
		udp.Email = email +"@test.com";
		udp.Timezone = "Republic of India";
		udp.Phone = "8976543211";
		udp.Locations = Arrays.asList("ALL");
		udp.DefaultLocation = defaultLocation;
		udp.WorkGroups1 = workGroupName;
		udp.Pin = pin;
		udp.Roles = Arrays.asList("SuperAdmin");

		boolean userCreation = ump.setUsrDetails(udp);
		TestValidation.IsTrue(userCreation, 
				"Details set for user - " + username, 
				"Could NOT set details for user - " + username);

		boolean saveUser = ump.clickSaveBtn();
		TestValidation.IsTrue(saveUser, 
				"User " + username + " is SAVED successfully", 
				"Could NOT create User " + username);

		mlp = hp.clickLocationsMenu();
		TestValidation.Equals(mlp.error, 
				false, 
				"Opened Manage Location", 
				"Could Not Open Manage Location");

		boolean addNewInstance = mlp.createLocationInstance(locationCategoryValue, locationInstValue);
		TestValidation.IsTrue(addNewInstance, 
				"Succesfully added new Location Instance " + locationInstValue, 
				"FAIL to add new Location Instance " + locationInstValue);

		boolean selectTab1  = mlp.selectTab(SelectTAB.USERS);
		TestValidation.IsTrue(selectTab1, 
				"Succesfully selected user tab", 
				"FAIL to select user tab");	

		boolean searchUser2= mlp.searchUser(username);
		TestValidation.IsTrue(searchUser2, 
				"Succesfully searched user "+ username,
				"FAIL to search user "+ username);


		boolean chkUserIsDisableOrNot1 = mlp.usercheckboxIsDisableOrEnable(username);
		TestValidation.IsTrue(chkUserIsDisableOrNot1, 
				"Succesfully Checked user "+username+ " is Disabled i.e, Since the user "+username+" is Disabled Location is automatically added with user having permission for ALL location and Cannot be removed ",
				"FAIL " +username+ " is Enable , Since the user "+username+" is enabled Location is not automatically added with user having permission for ALL location and can be removed");

		ump = hp.clickUsersMenu();
		TestValidation.Equals(ump.error, 
				false, 
				"CLICKED on Users menu", 
				"Could NOT click on Users menu");

		boolean searchUmpUser1 = ump.searchAndOpenUsrDetails(USERFIELDS.USERNAME, username);
		TestValidation.IsTrue(searchUmpUser1, 
				"User " + username + " is Searched and opened Succesfully",
				"Could NOT Search and open " + username );

		boolean removeAll = ump.removeLocationAll();
		TestValidation.IsTrue(removeAll, 
				"Succesfully removed ALL location from locations field", 
				"FAIL to remove All location from locations field");

		boolean setNewLocation = ump.setMultiSelectValue(USERFIELDS.LOCATIONS,Arrays.asList(locationInstanceValue));
		TestValidation.IsTrue(setNewLocation, 
				"Succesfully associated new Location "+locationInstanceValue+" in locations field for user" + username, 
				"FAIL to associate new location " +locationInstanceValue+ " in locations field for user" + username);

		boolean saveUser1 = ump.clickSaveBtn();
		TestValidation.IsTrue(saveUser1, 
				"User " + username + " is SAVED successfully", 
				"Could NOT create User " + username);

		mlp = hp.clickLocationsMenu();
		TestValidation.Equals(mlp.error, 
				false, 
				"Opened Manage Location", 
				"Could Not Open Manage Location"); 

		boolean findAndClickLoc1 = mlp.scrollAndFindLocationInstance(locationCategoryValue, locationInstanceValue);
		TestValidation.IsTrue(findAndClickLoc1, 
				"Succesfully clicked Location Category and Location Instance " + locationInstanceValue, 
				"FAIL to click Location Category and Location Instance " + locationInstanceValue);

		boolean selectTab2  = mlp.selectTab(SelectTAB.USERS);
		TestValidation.IsTrue(selectTab2, 
				"Succesfully selected user tab", 
				"FAIL to select user tab");	

		boolean searchUser = mlp.searchUser(username);
		TestValidation.IsTrue(searchUser, 
				"Succesfully searched user "+ username,
				"FAIL to search user "+ username);

		boolean chkUserIsDisableOrNot3 = mlp.usercheckboxIsDisableOrEnable(username);
		TestValidation.IsTrue(chkUserIsDisableOrNot3, 
				"Succesfully Verified that user " +username+ " is enabled and dont have access to every location in the system",
				"FAIL to verify that user" +username+ " is disable and having access to every location in system or not");


	}

	@Test(description = "9119 - Data Security - Manage Locations - Users can select which\r\n"
			+ "Resource Types to carry over when copying an existing Location")
	public void TestCase_10182() {


		mlp = hp.clickLocationsMenu();
		TestValidation.Equals(mlp.error, 
				false, 
				"Opened Manage Location", 
				"Could Not Open Manage Location"); 

		boolean copyISDisplay = mlp.copyIsDisplayed();
		TestValidation.IsTrue(copyISDisplay, 
				"Succesfully verified Copy Icon is not Displayed before extracting Location Instance ", 
				"FAILED, Copy Icon is displaying before extracting Location Instance");

		boolean createLocationInstance= mlp.createLocationInstanceLinkUser(locationCategoryValue,newlocationInstanceValue2,userUN);
		TestValidation.IsTrue(createLocationInstance, 
				"Succesfully created Location Instance "+newlocationInstanceValue2+ " and link with user "+userUN, 
				"Failed to create Location Instance "+newlocationInstanceValue2 + " and link with user "+userUN);

		boolean selectTab1  = mlp.selectTab(SelectTAB.CUSTOMERS);
		TestValidation.IsTrue(selectTab1, 
				"Succesfully selected CUSTOMERS tab", 
				"FAIL to select CUSTOMERS tab");

		boolean CheckResource1 = mlp.selectFirstCheckboxForResourceCategory(SelectTAB.CUSTOMERS);
		TestValidation.IsTrue(CheckResource1, 
				"Successfully Checked first customer", 
				"FAIL to Checked first customer");

		String firstCustomerResource = mlp.getFirstResourceName(SelectResource.CUSTOMERS);
		TestValidation.IsTrue(firstCustomerResource != null, 
				"Verified First resource selected " + firstCustomerResource,
				"FAIL to verify that first resource selected "+firstCustomerResource);

		boolean clickSave1 = mlp.clickOnSave();
		TestValidation.IsTrue(clickSave1, 
				"Successfully Saved First Customer", 
				"FAIL to save First Customer");

		boolean selectTab2  = mlp.selectTab(SelectTAB.EQUIPMENT);
		TestValidation.IsTrue(selectTab2, 
				"Succesfully selected EQUIPMENT tab", 
				"FAIL to select EQUIPMENT tab");

		boolean CheckResource2 = mlp.selectFirstCheckboxForResourceCategory(SelectTAB.EQUIPMENT);
		TestValidation.IsTrue(CheckResource2, 
				"Successfully Checked first Equipment", 
				"FAIL to Checked first Equipment");

		String firstEquipmentResource =  mlp.getFirstResourceName(SelectResource.EQUIPMENT);
		TestValidation.IsTrue(firstEquipmentResource != null, 
				"Verified First resource selected "+firstEquipmentResource,
				"FAIL to verify that first resource selected "+firstEquipmentResource);

		boolean clickSave2 = mlp.clickOnSave();
		TestValidation.IsTrue(clickSave2, 
				"Successfully Saved First Equipment ", 
				"FAIL to save First Equipment ");

		boolean selectTab3  = mlp.selectTab(SelectTAB.ITEMS);
		TestValidation.IsTrue(selectTab3, 
				"Succesfully selected ITEMS tab", 
				"FAIL to select ITEMS tab");

		String firstItemResource = mlp.getFirstResourceName(SelectResource.ITEMS);
		TestValidation.IsTrue(firstItemResource != null, 
				"Successfully get Text of First resource "+firstItemResource+ " which is not selected",
				"FAIL to get Text of First resource "+firstItemResource);

		boolean selectTab4  = mlp.selectTab(SelectTAB.SUPPLIERS);
		TestValidation.IsTrue(selectTab4, 
				"Succesfully selected SUPPLIERS tab", 
				"FAIL to select SUPPLIERS tab");

		String firstSupplierResource =  mlp.getFirstResourceName(SelectResource.SUPPLIERS);
		TestValidation.IsTrue(firstSupplierResource != null, 
				"Successfully get Text of First resource "+firstSupplierResource+ " which is not selected",
				"FAIL to get Text of First resource "+firstSupplierResource);

		boolean copyISDisplay1 = mlp.copyIsDisplayed();
		TestValidation.IsTrue(copyISDisplay1, 
				"Succesfully verified Copy Button is Displayed ", 
				"FAILED, Copy button is not displaying ");

		boolean clickCopy = mlp.clickOnCopyIcon();
		TestValidation.IsTrue(clickCopy, 
				"Succesfully Clicked on Copy Icon and Copy Dialogue opened", 
				"FAILED to click on copy Icon and to Open Copy Location Dialogue ");

		boolean getHeadingText = controlActions.isElementDisplay(mlp.CopyLocationTxt);
		TestValidation.IsTrue(getHeadingText, 
				"Verified that Copy Location dialogue is Displayed.", 
				"FAIL to verify that Copy Location dialogue is Display or not. ");

		boolean copyButton = mlp.copyButtonIsEnabledOrDisabled();
		TestValidation.IsTrue(copyButton, 
				"Verified that Copy Button is Disabled.As copy button is Disabled Resource Types are Unchecked", 
				"FAIL to verify that Copy Button is Disabled and Resource types are Unchecked. ");

		boolean selectAll = mlp.clickOnSelectAll();
		TestValidation.IsTrue(selectAll, 
				"Succesfully Clicked Select ALL and ALL resource types from list are selected", 
				"FAIL to Click Select All. ");

		boolean allResourcesSelected = mlp.checkListOfAllResourceTypesSelected();
		TestValidation.IsTrue(allResourcesSelected, 
				"Verified that All resource types are Selected.", 
				"FAIL to verify All resource types are selected or not. ");

		boolean copyButton1 = mlp.copyButtonIsEnabledOrDisabled();
		TestValidation.IsTrue(copyButton1, 
				"Verified that Copy Button is Enabled.As copy button is Enabled Resource Types are Seleted", 
				"FAIL to verify that Copy Button is Enabled or Disabled. ");

		boolean clkCopyBtn = mlp.clkCopyButton();
		TestValidation.IsTrue(clkCopyBtn, 
				"Successfully Copied Resources by clicking copy button", 
				"FAIL to Copy Resources ");

		String copyNewLocationInstanceValue = newlocationInstanceValue2+" 1";

		TestValidation.IsTrue(copyNewLocationInstanceValue.equals(mlp.getSelectedLocInstName()), 
				"Succesfully Location Instance " + newlocationInstanceValue2+ " is copied and Displayed as " +copyNewLocationInstanceValue , 
				"FAIL to copy Location Instance " + newlocationInstanceValue2+ " and display "+copyNewLocationInstanceValue);


		boolean selectTab5  = mlp.selectTab(SelectTAB.USERS);
		TestValidation.IsTrue(selectTab5, 
				"Succesfully selected user tab", 
				"FAIL to select user tab");	

		boolean searchUser = mlp.searchUser(userUN);
		TestValidation.IsTrue(searchUser, 
				"Succesfully searched user "+ userUN,
				"FAIL to search user "+ userUN);

		boolean chkUserIsSelected = mlp.userIsCheckedOrUnchecked();
		TestValidation.IsTrue(chkUserIsSelected, 
				"Succesfully Verified that user " +userUN+ " is not copied when location is copied",
				"FAIL to verify that user" +userUN+ " is not copied when location is copied");

		boolean selectTab6  = mlp.selectTab(SelectTAB.CUSTOMERS);
		TestValidation.IsTrue(selectTab6, 
				"Succesfully selected CUSTOMERS tab", 
				"FAIL to select CUSTOMERS tab");


		String resourceName1[] = CommonMethods.splitAndGetString(firstCustomerResource, ">");
		String custResourceInstName = resourceName1[resourceName1.length-1];

		boolean searchResource1 = mlp.searchResource(custResourceInstName.trim());
		TestValidation.IsTrue(searchResource1, 
				"Succesfully searched Resource: "+ firstCustomerResource,
				"FAIL to search Resource: "+ firstCustomerResource);

		boolean chkResourceIsSelected = mlp.checkListOfCheckboxAreSelectedOrNot();
		TestValidation.IsTrue(chkResourceIsSelected, 
				"Succesfully Verified that Resource--> "+ firstCustomerResource+ " selected by user is Matched with original location copied from ",
				"FAIL to verify Resource--> "+ firstCustomerResource+ " selected by user is matching with original location copied from");

		boolean selectTab7  = mlp.selectTab(SelectTAB.EQUIPMENT);
		TestValidation.IsTrue(selectTab7, 
				"Succesfully selected EQUIPMENT tab", 
				"FAIL to select EQUIPMENT tab");

		String resourceName2[] = CommonMethods.splitAndGetString(firstEquipmentResource, ">");
		String equipResourceInstName = resourceName1[resourceName2.length-1];

		boolean searchResource2 = mlp.searchResource(equipResourceInstName.trim());
		TestValidation.IsTrue(searchResource2, 
				"Succesfully searched Resource: "+ firstEquipmentResource,
				"FAIL to search Resource: "+ firstEquipmentResource);

		boolean chkResourceIsSelected1 = mlp.checkListOfCheckboxAreSelectedOrNot();
		TestValidation.IsTrue(chkResourceIsSelected1, 
				"Succesfully Verified that Resource--> "+ firstEquipmentResource+ " selected is Matched with original location copied from",
				"FAIL to verify Resource--> "+ firstEquipmentResource+ " selected is matching with original location copied from");


		boolean selectTab8  = mlp.selectTab(SelectTAB.ITEMS);
		TestValidation.IsTrue(selectTab8, 
				"Succesfully selected ITEMS tab", 
				"FAIL to select ITEMS tab");

		String resourceName3[] = CommonMethods.splitAndGetString(firstItemResource, ">");
		String itemResourceInstName = resourceName3[resourceName3.length-1];

		boolean searchResource3 = mlp.searchResource(itemResourceInstName.trim());
		TestValidation.IsTrue(searchResource3, 
				"Succesfully searched Resource: "+ firstItemResource,
				"FAIL to search Resource: "+ firstItemResource);

		boolean chkResourceIsSelected2 = mlp.resourceCategoryAreNotSelected(SelectTAB.ITEMS);
		TestValidation.IsTrue(chkResourceIsSelected2, 
				"Succesfully Verified that Resource--> "+ firstItemResource+ " was not selected is Matched with original location ",
				"FAIL to verify Resource--> "+ firstItemResource+ " was not selected is matching with original location or not");

		boolean selectTab9  = mlp.selectTab(SelectTAB.SUPPLIERS);
		TestValidation.IsTrue(selectTab9, 
				"Succesfully selected SUPPLIERS tab", 
				"FAIL to select SUPPLIERS tab");

		String resourceName4[] = CommonMethods.splitAndGetString(firstSupplierResource, ">");
		String supplResourceInstName = resourceName4[resourceName4.length-1];

		boolean searchResource4 = mlp.searchResource(supplResourceInstName.trim());
		TestValidation.IsTrue(searchResource4, 
				"Succesfully searched Resource: "+ firstSupplierResource,
				"FAIL to search Resource: "+ firstSupplierResource);

		boolean chkResourceIsSelected3 = mlp.resourceCategoryAreNotSelected(SelectTAB.SUPPLIERS);
		TestValidation.IsTrue(chkResourceIsSelected3, 
				"Succesfully Verified that Resource "+ firstSupplierResource+ " was not selected is Matched with original location ",
				"FAIL to verify Resource "+ firstSupplierResource+ " was not selected is matching with original location or not");

	}

	@Test(description = "9223- Data Security - Forms - Users cannot see or select resources\r\n"
			+ "within a form that they do not have access to")
	public void TestCase_11343() {
		newLocationCategoryValue = CommonMethods.dynamicText("New_Loc_Cat");
		newCustomerCategoryValue = CommonMethods.dynamicText("New_Cust_Cat");
		newEquipmentCategoryValue = CommonMethods.dynamicText("New_Equip_Cat");
		newlocationInstanceValue3 = CommonMethods.dynamicText("New_Loc_Inst");
		newCustomerInstanceValue = CommonMethods.dynamicText("New_Cust_Inst");
		newEquipmentInstanceValue = CommonMethods.dynamicText("New_Equip_Inst");
		userUN1 = CommonMethods.dynamicText("UN_11343");
		//user Creation
		userLocation.add(newlocationInstanceValue3);
		userRole.add("SuperAdmin");
		UsrDetailParams udp = new UsrDetailParams();
		udp.Username = userUN1;
		udp.Password = GenericPassword;
		udp.FirstName = userFN;
		udp.LastName = userLN;
		udp.Email = "test@test.com";
		udp.Timezone = TIMEZONE.REPUBLICOFINDIA;
		udp.Locations = userLocation;
		udp.Roles = userRole;

		//Category creation
		HashMap<String,String> categories = new HashMap<String, String>();
		categories.put(CATEGORYTYPES.LOCATION, newLocationCategoryValue);
		categories.put(CATEGORYTYPES.CUSTOMERS, newCustomerCategoryValue);
		categories.put(CATEGORYTYPES.EQUIPMENT, newEquipmentCategoryValue);

		ResourceDesignerPage rdp = hp.clickResourceDesignerMenu();

		boolean createCategory1 = rdp.createCategory(categories);
		TestValidation.IsTrue(createCategory1, 
				"Succesfully created Resource categories for Location - " +newLocationCategoryValue+ " , Customer - "+ newCustomerCategoryValue+  " , Equipment - " +newEquipmentCategoryValue, 
				"Could NOT create Resource categories Location - " +newLocationCategoryValue+ " , Customer - "+ newCustomerCategoryValue+  " , Equipment - " +newEquipmentCategoryValue);

		//Location instance creation
		HashMap<String,Boolean> locInstance = new HashMap<String, Boolean>();
		locInstance.put(newlocationInstanceValue3, true);

		LocationInstanceParams lip = new LocationInstanceParams();
		lip.CategoryName = newLocationCategoryValue;
		lip.NumberFieldValue = 10;
		lip.TextFieldValue = "XYZ";
		lip.InstanceName = locInstance;
		ManageLocationPage mlp = hp.clickLocationsMenu();

		boolean createLocInst = mlp.createLocation(lip);
		TestValidation.IsTrue(createLocInst, 
				"Succesfully created Location Instance  - " +newlocationInstanceValue3, 
				"Could NOT create Location instance - "+newlocationInstanceValue3 );

		UserManagerPage ump = hp.clickUsersMenu();
		boolean userCreation = ump.createInternalUser(udp);
		TestValidation.IsTrue(userCreation, 
				"Succesfully created User  - " +userUN1, 
				"Could NOT create user - " + userUN1 );


		//Resource creation - Customer
		HashMap<String,Boolean> custInstance = new HashMap<String, Boolean>();
		custInstance.put(newCustomerInstanceValue, true);

		ResourceDetailParams rd1 = new ResourceDetailParams();
		rd1.CategoryName = newCustomerCategoryValue;
		rd1.CategoryType = RESOURCETYPES.CUSTOMERS;
		rd1.NumberFieldValue = 15;
		rd1.TextFieldValue = "LMN";
		rd1.InstanceNames = custInstance;
		rd1.LocationInstanceValue = newlocationInstanceValue3;

		mrp = hp.clickResourcesMenu();
		TestValidation.Equals(mrp.error, 
				false, 
				"Opened Manage Resource", 
				"Could Not Open Manage Resource"); 

		boolean createResource1 = mrp.createResourceLinkLocation(rd1);
		TestValidation.IsTrue(createResource1, 
				"Succesfully created Customer Instance - "+newCustomerInstanceValue+" for category - " + newCustomerCategoryValue, 
				"Could NOT create Customer Instance - "+newCustomerInstanceValue+" for category - " + newCustomerCategoryValue);

		//Resource creation - Equipment
		HashMap<String,Boolean> equipInstance = new HashMap<String, Boolean>();
		equipInstance.put(newEquipmentInstanceValue, true);

		ResourceDetailParams rd2 = new ResourceDetailParams();
		rd2.CategoryName = newEquipmentCategoryValue;
		rd2.CategoryType = RESOURCETYPES.EQUIPMENT;
		rd2.NumberFieldValue = 15;
		rd2.TextFieldValue = "LMN";
		rd2.InstanceNames = equipInstance;
		rd2.LocationInstanceValue = newlocationInstanceValue3;	

		mrp = hp.clickResourcesMenu();
		TestValidation.Equals(mrp.error, 
				false, 
				"Opened Manage Resource", 
				"Could Not Open Manage Resource"); 

		boolean createResource2 = mrp.createResourceLinkLocation(rd2);
		TestValidation.IsTrue(createResource2, 
				"Succesfully created Equipment Instance - "+newEquipmentInstanceValue+" for category - " + newEquipmentCategoryValue, 
				"Could NOT create Equipment Instance - "+newEquipmentInstanceValue+" for category - " + newEquipmentCategoryValue);

		fdp = hp.clickFormDesignerMenu();
		TestValidation.Equals(fdp.error, 
				false, 
				"Opened Form Designer", 
				"Could Not Open Form Designer");

		checkFormName	= CommonMethods.dynamicText("Automation_CheckForm");

		boolean createAndReleaseForm = fdp.createAndReleaseForm("Check", checkFormName, "Customers",
				newCustomerCategoryValue, newCustomerInstanceValue);
		TestValidation.IsTrue(createAndReleaseForm, "Able to create and release form: " + checkFormName,
				"Could NOT able to create and release forms:" + checkFormName);

		boolean logoutUser = hp.userLogout();
		TestValidation.IsTrue(logoutUser, 
				"Logged out with user " + adminUsername, 
				"Could NOT logout with user " + adminUsername);

		boolean loginNewUser = lp.loginAfterUserCreation(userUN1, GenericPassword, GenericNewPassword);
		TestValidation.IsTrue(loginNewUser, 
				"Logged In with new user - " + userUN1, 
				"Could NOT login with user - " + userUN1);

		fbp = hp.clickFSQABrowserMenu();
		boolean navigateToforms = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.CUSTOMERS,FSQATAB.FORMS);
		TestValidation.IsTrue(navigateToforms, 
				"For customer category, NAVIGATED to FSQABrowser > Forms tab", 
				"Failed to navigate to FSQABrowser > Forms tab");

		boolean applyFilterAndOpenForm = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, checkFormName);
		TestValidation.IsTrue(applyFilterAndOpenForm, 
				"Selected & opened the form - "+checkFormName, 
				"Could NOT open the form - "+checkFormName);

		FormOperations fo = new FormOperations(driver);
		boolean setResrc = fo.selectLocationResource(newlocationInstanceValue3, newCustomerInstanceValue);
		TestValidation.IsTrue(setResrc, 
				"Verified that Associated resource - "+newCustomerInstanceValue+" is displaying as per the assigned location - "+newlocationInstanceValue3+"  in drop down list" , 
				"Could not able to verify Associated resource - "+newCustomerInstanceValue+" is displaying as per the assigned location - "+newlocationInstanceValue3+" on drop down list or not " );

		boolean searchNodata1 = fo.searchSelectResourceNoData(newEquipmentInstanceValue);
		TestValidation.IsTrue(searchNodata1, 
				"Verified that resource - "+newEquipmentInstanceValue+" for which the access was not provided is not displaying in drop down list " , 
				"Could not able to verify resource - "+newEquipmentInstanceValue+" for which the access was not provided is not displaying in drop down list" );

		boolean logoutUser1 = hp.userLogout();
		TestValidation.IsTrue(logoutUser1, 
				"Logged out with user " + userUN1, 
				"Could NOT logout with user " + userUN1);
		hp = lp.performLogin(adminUsername, adminPassword);

	}

	@Test(description = "9210-Data Security - FSQA Browser - The Resource Hierarchy for\r\n"
			+ "each Resource Type only shows resources the user has access to based on data security")
	public void TestCase_11532() {
		newLocationCategoryValue = CommonMethods.dynamicText("New_Loc_Cat");
		newCustomerCategoryValue = CommonMethods.dynamicText("New_Cust_Cat");
		newEquipmentCategoryValue = CommonMethods.dynamicText("New_Equip_Cat");
		newlocationInstanceValue3 = CommonMethods.dynamicText("New_Loc_Inst");
		newCustomerInstanceValue = CommonMethods.dynamicText("New_Cust_Inst");
		newEquipmentInstanceValue = CommonMethods.dynamicText("New_Equip_Inst");
		userUN3 = CommonMethods.dynamicText("UN_11532");

		//user Creation
		userLocation.add(newlocationInstanceValue3);
		userRole.add("SuperAdmin");
		UsrDetailParams udp = new UsrDetailParams();
		udp.Username = userUN3;
		udp.Password = GenericPassword;
		udp.FirstName = userFN;
		udp.LastName = userLN;
		udp.Email = "test@test.com";
		udp.Timezone = TIMEZONE.REPUBLICOFINDIA;
		udp.Locations = userLocation;
		udp.Roles = userRole;

		//Category creation
		HashMap<String,String> categories = new HashMap<String, String>();
		categories.put(CATEGORYTYPES.LOCATION, newLocationCategoryValue);
		categories.put(CATEGORYTYPES.CUSTOMERS, newCustomerCategoryValue);
		categories.put(CATEGORYTYPES.EQUIPMENT, newEquipmentCategoryValue);

		ResourceDesignerPage rdp = hp.clickResourceDesignerMenu();

		boolean createCategory1 = rdp.createCategory(categories);
		TestValidation.IsTrue(createCategory1, 
				"Succesfully created Resource categories for Location - " +newLocationCategoryValue+ " , Customer - "+ newCustomerCategoryValue+  " , Equipment - " +newEquipmentCategoryValue, 
				"Could NOT create Resource categories Location - " +newLocationCategoryValue+ " , Customer - "+ newCustomerCategoryValue+  " , Equipment - " +newEquipmentCategoryValue);

		//Location instance creation
		HashMap<String,Boolean> locInstance = new HashMap<String, Boolean>();
		locInstance.put(newlocationInstanceValue3, true);

		LocationInstanceParams lip = new LocationInstanceParams();
		lip.CategoryName = newLocationCategoryValue;
		lip.NumberFieldValue = 10;
		lip.TextFieldValue = "XYZ";
		lip.InstanceName = locInstance;
		ManageLocationPage mlp = hp.clickLocationsMenu();

		boolean createLocInst = mlp.createLocation(lip);
		TestValidation.IsTrue(createLocInst, 
				"Succesfully created Location Instance  - " +newlocationInstanceValue3, 
				"Could NOT create Location instance - "+newlocationInstanceValue3 );

		UserManagerPage ump = hp.clickUsersMenu();
		boolean userCreation = ump.createInternalUser(udp);
		TestValidation.IsTrue(userCreation, 
				"Succesfully created User  - " +userUN3, 
				"Could NOT create user - " + userUN3 );

		mrp = hp.clickResourcesMenu();
		TestValidation.Equals(mrp.error, 
				false, 
				"Opened Manage Resource", 
				"Could Not Open Manage Resource"); 

		//Resource creation - Customer
		HashMap<String,Boolean> custInstance = new HashMap<String, Boolean>();
		custInstance.put(newCustomerInstanceValue, true);

		ResourceDetailParams rd1 = new ResourceDetailParams();
		rd1.CategoryName = newCustomerCategoryValue;
		rd1.CategoryType = RESOURCETYPES.CUSTOMERS;
		rd1.NumberFieldValue = 15;
		rd1.TextFieldValue = "LMN";
		rd1.InstanceNames = custInstance;
		rd1.LocationInstanceValue = newlocationInstanceValue3;

		boolean createResource1 = mrp.createResourceLinkLocation(rd1);
		TestValidation.IsTrue(createResource1, 
				"Succesfully created Customer Instance - "+newCustomerInstanceValue+" for category - " + newCustomerCategoryValue+ " and linked with "+newlocationInstanceValue3, 
				"Could NOT create Customer Instance - "+newCustomerInstanceValue+" for category - " + newCustomerCategoryValue);

		//Resource creation - Equipment

		ResourceDetailParams rd2 = new ResourceDetailParams();
		rd2.CategoryName = newEquipmentCategoryValue;
		rd2.CategoryType = RESOURCETYPES.EQUIPMENT;
		rd2.NumberFieldValue = 15;
		rd2.TextFieldValue = "LMN";
		rd2.InstanceName = newEquipmentInstanceValue;
		rd2.LocationInstanceValue = newlocationInstanceValue3;

		boolean createResource2 = mrp.createResource(rd2);
		TestValidation.IsTrue(createResource2, 
				"Succesfully created Equipment Instance - "+newEquipmentInstanceValue+" for category - " + newEquipmentCategoryValue+ " and not linked with location "+newlocationInstanceValue3, 
				"Could NOT create Equipment Instance - "+newEquipmentInstanceValue+" for category - " + newEquipmentCategoryValue);

		fdp = hp.clickFormDesignerMenu();
		TestValidation.Equals(fdp.error, 
				false, 
				"Opened Form Designer", 
				"Could Not Open Form Designer");

		checkFormName	= CommonMethods.dynamicText("Automation_CheckForm");

		boolean createAndReleaseForm = fdp.createAndReleaseForm("Check", checkFormName, "Customers",
				newCustomerCategoryValue, newCustomerInstanceValue);
		TestValidation.IsTrue(createAndReleaseForm, "Able to create and release form: " + checkFormName,
				"Could NOT able to create and release forms:" + checkFormName);

		boolean logoutUser = hp.userLogout();
		TestValidation.IsTrue(logoutUser, 
				"Logged out with user " + adminUsername, 
				"Could NOT logout with user " + adminUsername);

		boolean loginNewUser = lp.loginAfterUserCreation(userUN3, GenericPassword, GenericNewPassword);
		TestValidation.IsTrue(loginNewUser, 
				"Logged In with new user - " + userUN3, 
				"Could NOT login with user - " + userUN3);

		fbp = hp.clickFSQABrowserMenu();
		TestValidation.Equals(fdp.error, 
				false, 
				"Opened FSQA Browser", 
				"Could Not Open FSQA Browser");

		boolean searchAndSelect = fbp.searchSelect(newCustomerInstanceValue);
		TestValidation.IsTrue(searchAndSelect, 
				"Succesfully searched and selected resource "+ newCustomerInstanceValue+" .Since Displaying in DropDown List", 
				"FAILED to search and select resource " + newCustomerInstanceValue);

		boolean customerIsRevealed = fbp.selectLocationAndCheckLocationIsDisplayed(newlocationInstanceValue3);
		TestValidation.IsTrue(customerIsRevealed, 
				"Succesfully verified CUSTOMERS resources are revealed to location - " + newlocationInstanceValue3+ " they were Assigned to.", 
				"Failed to verify that CUSTOMERS resources are revealed to locations or not for "+ newlocationInstanceValue3);

		boolean navigateToforms = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.CUSTOMERS,FSQATAB.FORMS);
		TestValidation.IsTrue(navigateToforms, 
				"For CUSTOMERS category, NAVIGATED to FSQABrowser > Forms tab", 
				"Failed to navigate to FSQABrowser > Forms tab");

		boolean searchSelect = fbp.searchSelect(newCustomerInstanceValue);
		TestValidation.IsTrue(searchSelect, 
				"Succesfully searched and selected Resource - " + newCustomerInstanceValue, 
				"Could NOT search and select Resource - " + newCustomerInstanceValue);


		boolean verifyForm = fbp.verifyFormIsDisplayed(checkFormName);
		TestValidation.IsTrue(verifyForm, 
				"VERIFIED form "+checkFormName+" is Displayed in FSQA browser", 
				"FAILED to verify form in FSQA browser");

		boolean navigateToforms1 = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.EQUIPMENT,FSQATAB.FORMS);
		TestValidation.IsTrue(navigateToforms1, 
				"For EQUIPMENT category, NAVIGATED to FSQABrowser > Forms tab", 
				"Failed to navigate to FSQABrowser > Forms tab");

		boolean searchNodata1 = fbp.searchNoData(newEquipmentInstanceValue);
		TestValidation.IsTrue(searchNodata1, 
				"Verified that resource - "+newEquipmentInstanceValue+" for which the access was not provided is not displaying in drop down list " , 
				"Could not able to verify resource - "+newEquipmentInstanceValue+" for which the access was not provided is not displaying in drop down list" );

		boolean logoutUser1 = hp.userLogout();
		TestValidation.IsTrue(logoutUser1, 
				"Logged out with user " + userUN3, 
				"Could NOT logout with user " + userUN3);

		hp = lp.performLogin(adminUsername, adminPassword);

	}

	@Test(description = "9224-Data Security - Forms - Users cannot see a form if they do\r\n"
			+ "not have access to any of the resources associated with the form")
	public void TestCase_11382() {

		newLocationCategoryValue = CommonMethods.dynamicText("New_Loc_Cat");
		newCustomerCategoryValue = CommonMethods.dynamicText("New_Cust_Cat");
		newEquipmentCategoryValue = CommonMethods.dynamicText("New_Equip_Cat");
		newlocationInstanceValue3 = CommonMethods.dynamicText("New_Loc_Inst");
		newCustomerInstanceValue = CommonMethods.dynamicText("New_Cust_Inst");
		newEquipmentInstanceValue = CommonMethods.dynamicText("New_Equip_Inst");
		newLocInst = CommonMethods.dynamicText("Loc_Inst_11382");
		userUN2 = CommonMethods.dynamicText("UN_11382");

		//user Creation
		//userLocation.add(newlocationInstanceValue3);
		userRole.add("SuperAdmin");
		UsrDetailParams udp = new UsrDetailParams();
		udp.Username = userUN2;
		udp.Password = GenericPassword;
		udp.FirstName = userFN;
		udp.LastName = userLN;
		udp.Email = "test@test.com";
		udp.Timezone = TIMEZONE.REPUBLICOFINDIA;
		udp.Locations = Arrays.asList(newlocationInstanceValue3);
		udp.Roles = userRole;

		//Category creation
		HashMap<String,String> categories = new HashMap<String, String>();
		categories.put(CATEGORYTYPES.LOCATION, newLocationCategoryValue);
		categories.put(CATEGORYTYPES.CUSTOMERS, newCustomerCategoryValue);
		categories.put(CATEGORYTYPES.EQUIPMENT, newEquipmentCategoryValue);

		ResourceDesignerPage rdp = hp.clickResourceDesignerMenu();

		boolean createCategory1 = rdp.createCategory(categories);
		TestValidation.IsTrue(createCategory1, 
				"Succesfully created Resource categories for Location - " +newLocationCategoryValue+ " , Customer - "+ newCustomerCategoryValue+  " , Equipment - " +newEquipmentCategoryValue, 
				"Could NOT create Resource categories Location - " +newLocationCategoryValue+ " , Customer - "+ newCustomerCategoryValue+  " , Equipment - " +newEquipmentCategoryValue);

		//Location instance creation
		HashMap<String,Boolean> locInstance = new HashMap<String, Boolean>();
		locInstance.put(newlocationInstanceValue3, true);

		LocationInstanceParams lip = new LocationInstanceParams();
		lip.CategoryName = newLocationCategoryValue;
		lip.NumberFieldValue = 10;
		lip.TextFieldValue = "XYZ";
		lip.InstanceName = locInstance;
		ManageLocationPage mlp = hp.clickLocationsMenu();

		boolean createLocInst = mlp.createLocation(lip);
		TestValidation.IsTrue(createLocInst, 
				"Succesfully created Location Instance  - " +newlocationInstanceValue3, 
				"Could NOT create Location instance - "+newlocationInstanceValue3 );

		mlp = hp.clickLocationsMenu();
		boolean addNewInstance = mlp.createLocationInstance(newLocationCategoryValue, newLocInst);
		TestValidation.IsTrue(addNewInstance, 
				"Succesfully added new Location Instance " + newLocInst, 
				"FAIL to add new Location Instance " + newLocInst);

		UserManagerPage ump = hp.clickUsersMenu();
		boolean userCreation = ump.createInternalUser(udp);
		TestValidation.IsTrue(userCreation, 
				"Succesfully created User  - " +userUN, 
				"Could NOT create user - " + userUN );


		//Resource creation - Customer
		HashMap<String,Boolean> custInstance = new HashMap<String, Boolean>();
		custInstance.put(newCustomerInstanceValue, true);

		ResourceDetailParams rd1 = new ResourceDetailParams();
		rd1.CategoryName = newCustomerCategoryValue;
		rd1.CategoryType = RESOURCETYPES.CUSTOMERS;
		rd1.NumberFieldValue = 15;
		rd1.TextFieldValue = "LMN";
		rd1.InstanceNames = custInstance;
		rd1.LocationInstanceValue = newlocationInstanceValue3;

		mrp = hp.clickResourcesMenu();
		TestValidation.Equals(mrp.error, 
				false, 
				"Opened Manage Resource", 
				"Could Not Open Manage Resource"); 

		boolean createResource1 = mrp.createResourceLinkLocation(rd1);
		TestValidation.IsTrue(createResource1, 
				"Succesfully created Customer Instance - "+newCustomerInstanceValue+" for category - " + newCustomerCategoryValue+ " and linked with Location -" +newlocationInstanceValue3, 
				"Could NOT create Customer Instance - "+newCustomerInstanceValue+" for category - " + newCustomerCategoryValue);

		//Resource creation - Equipment
		HashMap<String,Boolean> equipInstance = new HashMap<String, Boolean>();
		equipInstance.put(newEquipmentInstanceValue, true);

		ResourceDetailParams rd2 = new ResourceDetailParams();
		rd2.CategoryName = newEquipmentCategoryValue;
		rd2.CategoryType = RESOURCETYPES.EQUIPMENT;
		rd2.NumberFieldValue = 15;
		rd2.TextFieldValue = "LMN";
		rd2.InstanceNames = equipInstance;
		rd2.LocationInstanceValue = newLocInst;	

		mrp = hp.clickResourcesMenu();
		TestValidation.Equals(mrp.error, 
				false, 
				"Opened Manage Resource", 
				"Could Not Open Manage Resource"); 

		boolean createResource2 = mrp.createResourceLinkLocation(rd2);
		TestValidation.IsTrue(createResource2, 
				"Succesfully created Equipment Instance - "+newEquipmentInstanceValue+" for category - " + newEquipmentCategoryValue+ " and linked with Location - "+newLocInst, 
				"Could NOT create Equipment Instance - "+newEquipmentInstanceValue+" for category - " + newEquipmentCategoryValue);

		//FORM - Creation and Release

		checkFormName	= CommonMethods.dynamicText("Automation_CheckForm");
		numFieldName    = "Numeric Field";

		HashMap<String, List<String>> fields = new HashMap<String, List<String>>();
		fields.put(FIELD_TYPES.NUMERIC, Arrays.asList(numFieldName));

		FormFieldParams ffp = new FormFieldParams();
		ffp.FieldDetails = fields;

		HashMap<String, List<String>> resource = new HashMap<String, List<String>>();
		resource.put(FORMRESOURCETYPES.CUSTOMERS, Arrays.asList(newCustomerCategoryValue));

		FormDesignParams fdpDets = new FormDesignParams();
		fdpDets.FormType = FORMTYPES.CHECK;
		fdpDets.FormName = checkFormName;
		fdpDets.SelectResources = resource;
		fdpDets.DesignFields = Arrays.asList(ffp);
		fdpDets.ReleaseForm = true;

		hp.clickFormDesignerMenu();
		boolean createAndReleaseForm = fdp.createAndReleaseForm(fdpDets);
		TestValidation.IsTrue(createAndReleaseForm,
				"Able to create and release form: " + checkFormName,
				"Could NOT able to create and release forms:" + checkFormName);

		//Create Questionnaire Form and Release
		questionnaireFormName = CommonMethods.dynamicText("Auto_Quest_Form");
		numericFieldName = "Numeric Field";

		HashMap<String, List<String>> qstSecFields = new HashMap<String, List<String>>();
		qstSecFields.put(FIELD_TYPES.NUMERIC, Arrays.asList(numericFieldName));

		FormFieldParams ffpSecQst = new FormFieldParams();
		ffpSecQst.FieldDetails = qstSecFields;

		HashMap<String, List<String>> resources = new HashMap<String, List<String>>();
		resources.put(FORMRESOURCETYPES.CUSTOMERS, Arrays.asList(newCustomerCategoryValue));

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
		selectResources.put(FORMRESOURCETYPES.EQUIPMENT,Arrays.asList(newEquipmentCategoryValue));

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
		boolean navigateToforms = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.CUSTOMERS,FSQATAB.FORMS);
		TestValidation.IsTrue(navigateToforms, 
				"For customer category, NAVIGATED to FSQABrowser > Forms tab", 
				"Failed to navigate to FSQABrowser > Forms tab");

		boolean openAndApplySettings = fbp.openAndApplySettingsForColumn(COLUMNHEADER.FORMNAME, COLUMNSETTING.FILTER, checkFormName);
		TestValidation.IsTrue(openAndApplySettings, 
				"Searched form - "+checkFormName, 
				"Could NOT search the form - "+checkFormName);

		boolean verifyForm = fbp.verifyFormIsDisplayed(checkFormName);
		TestValidation.IsTrue(verifyForm, 
				"VERIFIED form "+checkFormName+" is Displayed in FSQA browser", 
				"FAILED to verify form in FSQA browser");

		boolean openAndApplySettings1 = fbp.openAndApplySettingsForColumn(COLUMNHEADER.FORMNAME, COLUMNSETTING.FILTER, questionnaireFormName);
		TestValidation.IsTrue(openAndApplySettings1, 
				"Searched form - "+questionnaireFormName, 
				"Could NOT search the form - "+questionnaireFormName);

		boolean verifyForm1 = fbp.verifyFormIsDisplayed(questionnaireFormName);
		TestValidation.IsTrue(verifyForm1, 
				"VERIFIED form "+questionnaireFormName+" is Displayed in FSQA browser", 
				"FAILED to verify form in FSQA browser");

		boolean navigateToforms3 = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.EQUIPMENT,FSQATAB.FORMS);
		TestValidation.IsTrue(navigateToforms3, 
				"For EQUIPMENT category, NAVIGATED to FSQABrowser > Forms tab", 
				"Failed to navigate to FSQABrowser > Forms tab");

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

		boolean loginNewUser = lp.loginAfterUserCreation(userUN2, GenericPassword, GenericNewPassword);
		TestValidation.IsTrue(loginNewUser, 
				"Logged In with new user - " + userUN2, 
				"Could NOT login with user - " + userUN2);

		fbp = hp.clickFSQABrowserMenu();
		boolean navigateToforms2 = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.CUSTOMERS,FSQATAB.FORMS);
		TestValidation.IsTrue(navigateToforms2, 
				"For customer category, NAVIGATED to FSQABrowser > Forms tab", 
				"Failed to navigate to FSQABrowser > Forms tab");

		boolean applyFilterAndOpenForm = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, checkFormName);
		TestValidation.IsTrue(applyFilterAndOpenForm, 
				"Selected & opened the form - "+checkFormName+" .Since created form is displayed", 
				"Could NOT open the form - "+checkFormName);

		//Submit form
		LinkedHashMap<String, List<String>> fillDetails = new LinkedHashMap<String, List<String>>();
		fillDetails.put("Numeric Field", Arrays.asList("10"));

		FormDetails fd = new FormDetails();
		fd.fieldDetails = fillDetails;
		fd.locationName = newlocationInstanceValue3;//newlocationInstanceValue3
		fd.resourceName = newCustomerInstanceValue;//newCustomerInstanceValue
		fd.isSubmit = true;
		FormOperations fo = new FormOperations(driver);
		boolean submit = fo.fillAndSubmitData(fd);
		TestValidation.IsTrue(submit, "Form submitted " + checkFormName, "Failed to submit form " + checkFormName);

		boolean applyFilterAndOpenForm1 = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, questionnaireFormName);
		TestValidation.IsTrue(applyFilterAndOpenForm1, 
				"Selected & opened the form - "+questionnaireFormName+" .Since created form is displayed", 
				"Could NOT open the form - "+questionnaireFormName);

		//Submit form
		LinkedHashMap<String, List<String>> fillDetails1 = new LinkedHashMap<String, List<String>>();
		fillDetails1.put("Numeric Field", Arrays.asList("10"));

		FormDetails fd1 = new FormDetails();
		fd1.fieldDetails = fillDetails1;
		fd1.locationName = newlocationInstanceValue3;
		fd1.resourceName = newCustomerInstanceValue;
		fd1.isSubmit = true;
		FormOperations fo1 = new FormOperations(driver);
		boolean submit1 = fo1.fillAndSubmitData(fd1);
		TestValidation.IsTrue(submit1, "Form submitted " + questionnaireFormName, "Failed to submit form " + questionnaireFormName);

		boolean navigateToRecordsTab = fbp.navigateToFSQATab(FSQATAB.RECORDS);
		TestValidation.IsTrue(navigateToRecordsTab, 
				"NAVIGATED to FSQABrowser > Records tab", 
				"Failed to navigate to FSQABrowser > Records tab");

		boolean openAndApplySettings3 = fbp.openAndApplySettingsForColumn(COLUMNHEADER.FORMNAME, COLUMNSETTING.FILTER, checkFormName);
		TestValidation.IsTrue(openAndApplySettings3, 
				"Searched form - "+checkFormName, 
				"Could NOT search form - "+checkFormName);

		boolean verifyForm3 = fbp.verifyRecordIsDisplayed(checkFormName);
		TestValidation.IsTrue(verifyForm3, 
				"VERIFIED submitted form "+checkFormName+" is Displayed in Records tab", 
				"FAILED to verify submitted form in Records tab");

		boolean navigateToDocumentsTab = fbp.navigateToFSQATab(FSQATAB.DOCUMENTS);
		TestValidation.IsTrue(navigateToDocumentsTab, 
				"NAVIGATED to FSQABrowser > DOCUMENTS tab", 
				"Failed to navigate to FSQABrowser > DOCUMENTS tab");


		boolean verifyForm4 = fbp.verifyDocumentIsDisplayed(questionnaireFormName);
		TestValidation.IsTrue(verifyForm4, 
				"VERIFIED  "+questionnaireFormName+" is Displayed in FSQA browser - Documents Tab", 
				"FAILED to verify form in FSQA browser");

		boolean navigateToforms1 = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.EQUIPMENT,FSQATAB.FORMS);
		TestValidation.IsTrue(navigateToforms1, 
				"For EQUIPMENT category, NAVIGATED to FSQABrowser > Forms tab", 
				"Failed to navigate to FSQABrowser > Forms tab");

		boolean verifyForm5 = fbp.verifyFormIsNotDisplayed(auditFormName);
		TestValidation.IsTrue(verifyForm5, 
				"VERIFIED form "+auditFormName+" is not Displayed in FSQA browser. As Access was  not provided", 
				"FAILED to verify form in FSQA browser");

		boolean navigateToforms4 = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.CUSTOMERS,FSQATAB.FORMS);
		TestValidation.IsTrue(navigateToforms4, 
				"For EQUIPMENT category, NAVIGATED to FSQABrowser > Forms tab", 
				"Failed to navigate to FSQABrowser > Forms tab");

		boolean applyFilterAndOpenForm3 = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, checkFormName);
		TestValidation.IsTrue(applyFilterAndOpenForm3, 
				"Selected & opened the form - "+checkFormName, 
				"Could NOT open the form - "+checkFormName);

		FormOperations fo3 = new FormOperations(driver);
		boolean searchNodata1 = fo3.searchSelectResourceNoData(newEquipmentInstanceValue);
		TestValidation.IsTrue(searchNodata1, 
				"Verified that resource - "+newEquipmentInstanceValue+" for which the access was not provided is not displaying " , 
				"Could not able to verify resource - "+newEquipmentInstanceValue+" for which the access was not provided is not displaying" );

		hp.userLogout();
		lp.performLogin(adminUsername, adminPassword);
	}

	@Test(description = "Data Security - Manage Resource Links - Manage Resources -\r\n"
			+ "Resource links can be established between Customers and any resource type (9648)")
	public void TestCase_12049() {
		newCustomerCategoryValue = CommonMethods.dynamicText("New_Cust_Cat");
		newCustomerInstanceValue = CommonMethods.dynamicText("New_Cust_Inst");

		boolean createResource = mrp.createResource(RESOURCETYPES.CUSTOMERS, newCustomerCategoryValue, newCustomerInstanceValue);
		TestValidation.IsTrue(createResource, 
				"Succesfully created Customer Instance - "+newCustomerInstanceValue+" for category - " + newCustomerCategoryValue, 
				"Could NOT create Customer Instance - "+newCustomerInstanceValue+" for category - " + newCustomerCategoryValue);

		boolean clkLinksTab = mrp.clkLinksTab();
		TestValidation.IsTrue(clkLinksTab, 
				"CLICKED on Links Tab",
				"FAIL to click on Links Tab");

		boolean clkAddLinkBtn = mrp.clkAddLinkBtn();
		TestValidation.IsTrue(clkAddLinkBtn, 
				"CLICKED on ADD LINK Button",
				"FAIL to click on ADD LINK Button");

		boolean popUpHeader = mrp.headerTextOfPopupIsDisplayed();
		TestValidation.IsTrue(popUpHeader, 
				"Verifeid Manage Resource Links Pop-Up is opened",
				"FAIL to verify Manage Resource Links Pop-Up");

		boolean selectTab1 = mrp.selectTabInAddLinkPopUp(SelectTAB.CUSTOMERS);
		TestValidation.IsTrue(selectTab1, 
				"Succesfully selected " + SelectTAB.CUSTOMERS, 
				"FAIL to select " + SelectTAB.CUSTOMERS);

		boolean checkResource1 = mrp.selectFirstCheckboxForResourceCategoryInAddLinkPopUp(SelectTAB.CUSTOMERS);
		TestValidation.IsTrue(checkResource1, 
				"Successfully Checked first CUSTOMERS", 
				"FAIL to check first CUSTOMERS");

		String custResourceName = mrp.getFirstResourceNameInAddLinkPopUp(SelectResource.CUSTOMERS);
		TestValidation.IsTrue(custResourceName != null, 
				"Verified First resource selected is " + custResourceName,
				"FAIL to verify that first resource selected is " + custResourceName);

		boolean selectTab2 = mrp.selectTabInAddLinkPopUp(SelectTAB.ITEMS);
		TestValidation.IsTrue(selectTab2, 
				"Succesfully selected " + SelectTAB.ITEMS, 
				"FAIL to select " + SelectTAB.ITEMS);

		boolean checkResource2 = mrp.selectFirstCheckboxForResourceCategoryInAddLinkPopUp(SelectTAB.ITEMS);
		TestValidation.IsTrue(checkResource2, 
				"Successfully Checked first ITEMS", 
				"FAIL to check first ITEMS");

		String itemResourceName = mrp.getFirstResourceNameInAddLinkPopUp(SelectResource.ITEMS);
		TestValidation.IsTrue(itemResourceName != null, 
				"Verified First resource selected is " + itemResourceName,
				"FAIL to verify that first resource selected is " + itemResourceName);

		boolean selectTab3 = mrp.selectTabInAddLinkPopUp(SelectTAB.SUPPLIERS);
		TestValidation.IsTrue(selectTab3, 
				"Succesfully selected " + SelectTAB.SUPPLIERS, 
				"FAIL to select " + SelectTAB.SUPPLIERS);

		boolean checkResource3 = mrp.selectFirstCheckboxForResourceCategoryInAddLinkPopUp(SelectTAB.SUPPLIERS);
		TestValidation.IsTrue(checkResource3, 
				"Successfully Checked first SUPPLIERS", 
				"FAIL to check first SUPPLIERS");

		String suppResourceName = mrp.getFirstResourceNameInAddLinkPopUp(SelectResource.SUPPLIERS);
		TestValidation.IsTrue(suppResourceName != null, 
				"Verified First resource selected is " + suppResourceName,
				"FAIL to verify that first resource selected is " + suppResourceName);

		boolean selectTab4 = mrp.selectTabInAddLinkPopUp(SelectTAB.EQUIPMENT);
		TestValidation.IsTrue(selectTab4, 
				"Succesfully selected " + SelectTAB.EQUIPMENT, 
				"FAIL to select " + SelectTAB.EQUIPMENT);

		boolean checkResource4 = mrp.selectFirstCheckboxForResourceCategoryInAddLinkPopUp(SelectTAB.EQUIPMENT);
		TestValidation.IsTrue(checkResource4, 
				"Successfully Checked first EQUIPMENT", 
				"FAIL to check first EQUIPMENT");

		String equipResourceName = mrp.getFirstResourceNameInAddLinkPopUp(SelectResource.EQUIPMENT);
		TestValidation.IsTrue(equipResourceName != null, 
				"Verified First resource selected is " + equipResourceName,
				"FAIL to verify that first resource selected is " + equipResourceName);

		boolean clickOnSave = mrp.clkSaveBtn();
		TestValidation.IsTrue(clickOnSave, 
				"Successfully Saved selected Links ", 
				"FAIL to Save selected Links ");

		boolean SelectedLinkForCustIsDisplayed = mrp.verifySelectedResourceLinkIsDisplayed(custResourceName);
		TestValidation.IsTrue(SelectedLinkForCustIsDisplayed, 
				"Verified Selected resource link - " + custResourceName + " is appear in Links Tab", 
				"Failed to Verify Selected resource link - " + custResourceName + " is appear in Links Tab");

		boolean SelectedLinkForItemIsDisplayed = mrp.verifySelectedResourceLinkIsDisplayed(itemResourceName);
		TestValidation.IsTrue(SelectedLinkForItemIsDisplayed, 
				"Verified Selected resource link - " + itemResourceName + " is appear in Links Tab", 
				"Failed to Verify Selected resource link - " + itemResourceName + " is appear in Links Tab");

		boolean SelectedLinkForSuppIsDisplayed = mrp.verifySelectedResourceLinkIsDisplayed(suppResourceName);
		TestValidation.IsTrue(SelectedLinkForSuppIsDisplayed, 
				"Verified Selected resource link - " + suppResourceName + " is appear in Links Tab", 
				"Failed to Verify Selected resource link - " + suppResourceName + " is appear in Links Tab");

		boolean SelectedLinkForEquipIsDisplayed = mrp.verifySelectedResourceLinkIsDisplayed(equipResourceName);
		TestValidation.IsTrue(SelectedLinkForEquipIsDisplayed, 
				"Verified Selected resource link - " + equipResourceName + " is appear in Links Tab", 
				"Failed to Verify Selected resource link - " + equipResourceName + " is appear in Links Tab");


	}
	
	@Test(description = "Data Security - Location")
	public void TestCase_31513() {

		hp.userLogout();
		lp.performLogin(adminUsername, adminPassword);

		locationCategoryValue31513 = CommonMethods.dynamicText("Loc_Cat_31513");
		customerCategoryValue31513 = CommonMethods.dynamicText("Cust_Cat_31513");
		equipmentCategoryValue31513 = CommonMethods.dynamicText("Equip_Cat_31513");
		locationInstanceValue31513 = CommonMethods.dynamicText("Loc_Inst_31513");
		customerInstanceValue31513 = CommonMethods.dynamicText("Cust_Inst_31513");
		equipmentInstanceValue31513 = CommonMethods.dynamicText("Equip_Inst_31513");
		userUN31513 = CommonMethods.dynamicText("UN_31513");
		//Category creation
		HashMap<String,String> categories = new HashMap<String, String>();
		categories.put(CATEGORYTYPES.LOCATION, locationCategoryValue31513);
		categories.put(CATEGORYTYPES.CUSTOMERS, customerCategoryValue31513);
		categories.put(CATEGORYTYPES.EQUIPMENT, equipmentCategoryValue31513);


		hp.clickResourceDesignerMenu();
		boolean createCategory = rdp.createCategory(categories);			
		TestValidation.IsTrue(createCategory, 
				"CREATED Resource categories for - " + locationCategoryValue31513 + ", "+ customerCategoryValue31513 +", " + equipmentCategoryValue31513, 
				"Failed to Create Resource categories for " + locationCategoryValue31513 +" ,"+customerCategoryValue31513+ ", "+equipmentCategoryValue31513);
		mrp = hp.clickResourcesMenu();
		TestValidation.Equals(mrp.error, 
				false, 
				"Opened Manage Resource", 
				"Could Not Open Manage Resource"); 
		//Resource creation - Customer
		ResourceDetailParams rd1 = new ResourceDetailParams();
		rd1.CategoryName = customerCategoryValue31513;
		rd1.CategoryType = RESOURCETYPES.CUSTOMERS;
		rd1.NumberFieldValue = 15;
		rd1.TextFieldValue = "LMN";
		rd1.InstanceName = customerInstanceValue31513;
		rd1.InstanceStatus = true;

		boolean createResource1 = mrp.createResource(rd1);
		TestValidation.IsTrue(createResource1, 
				"Succesfully created Customer Instance - "+customerInstanceValue31513+" for category - " + customerCategoryValue31513, 
				"Could NOT create Customer Instance - "+customerInstanceValue31513+" for category - " + customerCategoryValue31513);

		//Resource creation - Equipment
		ResourceDetailParams rd2 = new ResourceDetailParams();
		rd2.CategoryName = equipmentCategoryValue31513;
		rd2.CategoryType = RESOURCETYPES.EQUIPMENT;
		rd2.NumberFieldValue = 15;
		rd2.TextFieldValue = "LMN";
		rd2.InstanceName = equipmentInstanceValue31513;
		rd2.InstanceStatus = true;

		boolean createResource2 = mrp.createResource(rd2);
		TestValidation.IsTrue(createResource2, 
				"Succesfully created Equipment Instance - "+equipmentInstanceValue31513+" for category - " + equipmentCategoryValue31513, 
				"Could NOT create Equipment Instance - "+equipmentInstanceValue31513+" for category - " + equipmentCategoryValue31513);

		//Location instance creation
		HashMap<String,Boolean> locInstance = new HashMap<String, Boolean>();
		locInstance.put(locationInstanceValue31513, true);

		LocationInstanceParams lip = new LocationInstanceParams();
		lip.CategoryName = locationCategoryValue31513;
		lip.NumberFieldValue = 10;
		lip.TextFieldValue = "XYZ";
		lip.InstanceName = locInstance;
		hp.clickLocationsMenu();
		TestValidation.Equals(mlp.error, 
				false, 
				"Opened Manage Location", 
				"Could Not Open Manage Location");

		boolean createLoc = mlp.createLocation(lip);
		TestValidation.IsTrue(createLoc, 
				"CREATED Location Instance - " + locationInstanceValue31513 , 
				"Failed to Create Location Instance " + locationInstanceValue31513 );

		HashMap<String, List<String>> resourceInstances = new LinkedHashMap<String, List<String>>();
		resourceInstances.put(SelectTAB.CUSTOMERS, Arrays.asList(customerInstanceValue31513));
		resourceInstances.put(SelectTAB.EQUIPMENT, Arrays.asList(equipmentInstanceValue31513));

		boolean linkResToLoc1 = mlp.linkResourceToLocation(resourceInstances);
		TestValidation.IsTrue(linkResToLoc1, 
				"Succesfully linked Resource - "+customerInstanceValue31513+" and "+equipmentInstanceValue31513+" with Location Instance  - "+locationInstanceValue31513, 
				"Could NOT link Resource - "+customerInstanceValue31513+" and "+equipmentInstanceValue31513+" with Location instance - "+locationInstanceValue31513 );

		//FORM - Creation and release

		checkFormName31513	= CommonMethods.dynamicText("31513_Automation_CheckForm");
		numFieldName    = "Numeric Field";
		HashMap<String, List<String>> fields = new HashMap<String, List<String>>();
		fields.put(FIELD_TYPES.NUMERIC, Arrays.asList(numFieldName));
		FormFieldParams ffp = new FormFieldParams();
		ffp.FieldDetails = fields;
		HashMap<String, List<String>> resource = new HashMap<String, List<String>>();
		resource.put(FORMRESOURCETYPES.CUSTOMERS, Arrays.asList(customerCategoryValue31513));
		resource.put(FORMRESOURCETYPES.EQUIPMENT, Arrays.asList(equipmentCategoryValue31513));

		FormDesignParams fdpDets = new FormDesignParams();
		fdpDets.FormType = FORMTYPES.CHECK;
		fdpDets.FormName = checkFormName31513;
		fdpDets.SelectResources = resource;
		fdpDets.DesignFields = Arrays.asList(ffp);
		fdpDets.ReleaseForm = true;

		hp.clickFormDesignerMenu();
		boolean createAndReleaseForm = fdp.createAndReleaseForm(fdpDets);
		TestValidation.IsTrue(createAndReleaseForm,
				"Able to create and release the form:" + checkFormName31513,
				"Could NOT able to create and release the form:" + checkFormName31513);

		boolean createWg = wgp.createWorkGroup(workGroupName, adminUsername);
		TestValidation.IsTrue(createWg,
				"Succesfully created workgroup - " + workGroupName,
				"Could NOT able to create workgroup" + workGroupName);

		//user Creation
		workgroup = new ArrayList<String>();
		workgroup.add(workGroupName);
		UsrDetailParams udp = new UsrDetailParams();
		udp.Username = userUN31513;
		udp.Password = GenericPassword;
		udp.FirstName = userFN;
		udp.LastName = userLN;
		udp.Email = "test@test.com";
		udp.Timezone = TIMEZONE.REPUBLICOFINDIA;
		udp.Locations = Arrays.asList(locationInstanceValue31513);
		udp.Roles = Arrays.asList("SuperAdmin");
		udp.WorkGroups = workgroup;
		UserManagerPage ump = hp.clickUsersMenu();
		boolean userCreation = ump.createInternalUser(udp);
		TestValidation.IsTrue(userCreation, 
				"Succesfully created User  - " +userUN31513, 
				"Could NOT create user - " + userUN31513 );

		hp.clickFSQABrowserMenu();
		
		boolean navigateToforms = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.CUSTOMERS,FSQATAB.FORMS);
		TestValidation.IsTrue(navigateToforms, 
				"For CUSTOMERS category, NAVIGATED to FSQABrowser > Forms tab", 
				"Failed to navigate to FSQABrowser > Forms tab");
		
		boolean applyFilterAndOpenForm = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, checkFormName31513);
		TestValidation.IsTrue(applyFilterAndOpenForm, 
				"Selected & opened the form - "+checkFormName31513, 
				"Could NOT open the form - "+checkFormName31513);
		//Submit form
		LinkedHashMap<String, List<String>> fillDetails = new LinkedHashMap<String, List<String>>();
		fillDetails.put("Numeric Field", Arrays.asList("10"));

		FormDetails fd = new FormDetails();
		fd.fieldDetails = fillDetails;
		fd.locationName = locationInstanceValue31513;
		fd.resourceName = customerInstanceValue31513;
		fd.isSubmit = true;
		FormOperations fo = new FormOperations(driver);
		boolean submit = fo.submitData(fd);
		TestValidation.IsTrue(submit, "Successfully submitted Form -  " + checkFormName31513, "Failed to submitted form " + checkFormName31513);

		boolean applyfilter1 = fbp.openAndApplySettingsForColumn(COLUMNHEADER.FORMNAME, COLUMNSETTING.FILTER,checkFormName31513);
		TestValidation.IsTrue(applyfilter1, 
				"Applied Filter on" + COLUMNHEADER.FORMNAME, 
				"Failed to apply filter on" + COLUMNHEADER.FORMNAME);

		TASKDETAILS tsd = new TASKDETAILS();
		tsd.Location = locationInstanceValue31513;
		tsd.Resource = customerInstanceValue31513;
		tsd.TaskName = task31513;
		tsd.Workgroup = workGroupName;

		boolean assigntask = fbp.assignFormTask(tsd);
		TestValidation.IsTrue(assigntask, 
				"Form task assigned" + task31513, 
				"Failed to assign form task" + task31513);

		boolean logoutUser = hp.userLogout();
		TestValidation.IsTrue(logoutUser, 
				"Logged out with user - " + adminUsername, 
				"Could NOT logout with user - " + adminUsername);

		boolean loginNewUser = lp.loginAfterUserCreation(userUN31513, GenericPassword, GenericNewPassword);
		TestValidation.IsTrue(loginNewUser, 
				"Logged In with user - " + userUN31513, 
				"Could NOT login with user - " + userUN31513);

		hp.clickFSQABrowserMenu();
		boolean navigateToforms1 = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.CUSTOMERS,FSQATAB.FORMS);
		TestValidation.IsTrue(navigateToforms1, 
				"For CUSTOMERS category, NAVIGATED to FSQABrowser > Forms tab", 
				"Failed to navigate to FSQABrowser > Forms tab");

		boolean applyfilter2 = fbp.openAndApplySettingsForColumn(COLUMNHEADER.FORMNAME, COLUMNSETTING.FILTER,checkFormName31513);
		TestValidation.IsTrue(applyfilter2, 
				"Searched form - "+checkFormName31513, 
				"Could NOT search the form - "+checkFormName31513);

		boolean verifyForm = fbp.verifyFormIsDisplayed(checkFormName31513);
		TestValidation.IsTrue(verifyForm, 
				"VERIFIED Location Specific form - "+checkFormName31513+" is visible in FSQA browser", 
				"FAILED to verify form "+checkFormName31513+"in FSQA browser");

		boolean navigateToRecordsTab = fbp.navigateToFSQATab(FSQATAB.RECORDS);
		TestValidation.IsTrue(navigateToRecordsTab, 
				"NAVIGATED to FSQABrowser > Records tab", 
				"Failed to navigate to FSQABrowser > Records tab");

		boolean openAndApplySettings3 = fbp.openAndApplySettingsForColumn(COLUMNHEADER.FORMNAME, COLUMNSETTING.FILTER, checkFormName31513);
		TestValidation.IsTrue(openAndApplySettings3, 
				"Searched form - "+checkFormName31513, 
				"Could NOT search form - "+checkFormName31513);

		boolean verifyForm3 = fbp.verifyRecordIsDisplayed(checkFormName31513);
		TestValidation.IsTrue(verifyForm3, 
				"VERIFIED Location specific Record - "+checkFormName31513+" is visible in Records tab", 
				"FAILED to verify Record - "+checkFormName31513+" in Records tab");


		boolean navigate = fbp.navigateToFSQATab(FSQATAB.TASKS);
		TestValidation.IsTrue(navigate, 
				"Navigated to FSQABrowser>Tasks Tab", 
				"Failed to navigate to FSQABrowser>Tasks Tab");

		String taskname[] = {task31513};

		boolean viewtask = fbp.openAndApplySettingsForTaskColumn(COLUMNHEADER.TASKNAME, COLUMNSETTING.FILTER,taskname);
		TestValidation.IsTrue(viewtask, 
				"VERIFIED Location specific Task - "+task31513+" is visible", 
				"Failed to verify tasks"+task31513);

	}
	@AfterClass(alwaysRun = true)
	public void closeBrowser() throws InterruptedException {
		driver.close();
	}
}
