package com.project.safetychain.webapp.pageobjects;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.project.safetychain.webapp.pageobjects.ManageLocationPage.SelectResource;
import com.project.utilities.ControlActions;

public class ManageResourcePage extends CommonPage{
	ManageResourcePageLocators resource_locators;
	public ManageResourcePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 40);
		controlActions = new ControlActions(driver);
		resource_locators = new ManageResourcePageLocators();
	}


	@FindBy(id = ManageResourcePageLocators.SEARCH_TXT)
	public WebElement SearchTxt;
	@FindBy(xpath = ManageResourcePageLocators.SEARCH_FILTER_LST)
	public List<WebElement> SearchFilterLst;
	@FindBy(xpath = ManageResourcePageLocators.CUSTOMER_CATEGORY_TAB)
	public WebElement CustomerTab;
	@FindBy(xpath = ManageResourcePageLocators.EQUIPMENT_CATEGORY_TAB)
	public WebElement EquipmentTab ;
	@FindBy(xpath = ManageResourcePageLocators.ITEM_CATEGORY_TAB)
	public WebElement ItemsTab;
	@FindBy(xpath = ManageResourcePageLocators.SUPPLIER_CATEGORY_TAB)
	public WebElement SuppliersTab;

	@FindBy(xpath = ManageResourcePageLocators.SUPPLIER_CATEGORY_TRE)
	public WebElement SelectSupplierTre;
	@FindBy(xpath = ManageResourcePageLocators.ADD_NEW_SUPPLIER_TRE)
	public WebElement AddSupplierTre;
	@FindBy(xpath = ManageResourcePageLocators.RESOURCE_INSTANCE_TXT)
	public WebElement SetResourceNameTxt;
	@FindBy(xpath = ManageResourcePageLocators.RESOURCE_INSTANCE_NAME_TXT)
	public WebElement SetResourceInstanceNameTxt;
	@FindBy(xpath = ManageResourcePageLocators.FIELD_INPUTS_TXT)
	public WebElement FieldsInputTxt;
	@FindBy(xpath = ManageResourcePageLocators.TOTAL_FIELDS_TXT)
	public List<WebElement> AllFieldsInputTxt;
	@FindBy(xpath = ManageResourcePageLocators.FIELD_TXT_CNT)
	public List<WebElement> AllFieldsTxt;
	@FindBy(xpath = ManageResourcePageLocators.SAVE_RESOURCE_BTN)
	public WebElement SaveResourceBtn;
	@FindBy(xpath = ManageResourcePageLocators.MODIFIED_BY_LBL)
	public WebElement ModifiedByLbl;   
	@FindBy(xpath = ManageResourcePageLocators.ITEMS_TAB)
	public WebElement ItemsTabInSupplier;   
	@FindBy(xpath = ManageResourcePageLocators.SELECT_NEW_BTN)
	public WebElement SelectNewBtn; 
	@FindBy(xpath = ManageResourcePageLocators.POPUP_SAVE_BTN)
	public WebElement PopUpSaveBtn; 

	@FindBy(xpath = ManageResourcePageLocators.LOCATIONS_TAB)
	public WebElement LocationsTab; 

	@FindBy(xpath = ManageResourcePageLocators.LOCATION_CHK)
	public WebElement LocationChk; 

	@FindBy(xpath = ManageResourcePageLocators.SAVE_LOCATION_BTN)
	public WebElement SaveLocationBtn; 

	@FindBy(xpath = ManageResourcePageLocators.DISABLE_RESOURCE_BTN)
	public WebElement DisableResourceBtn;

	@FindBy(xpath = ManageResourcePageLocators.YES_DLG_BTN)
	public WebElement DisablePopUpYesBtn;

	@FindBy(xpath = ManageResourcePageLocators.TOTAL_RESOURCES_TRE)
	public List<WebElement> TotalResourceTre;

	@FindBy(xpath = ManageResourcePageLocators.NAME_DRD)
	public WebElement NameDRD;

	@FindBy(xpath = ManageResourcePageLocators.FILTER_OPTION)
	public WebElement FilterOption;

	@FindBy(xpath = ManageResourcePageLocators.FILTER_INPUT)
	public WebElement FilterInput;

	@FindBy(xpath = ManageResourcePageLocators.FILTER_BTN)
	public WebElement FilterBtn;
	
	@FindBy(xpath = ManageResourcePageLocators.LINKS_TAB)
	public WebElement LinksTab;
	
	@FindBy(xpath = ManageResourcePageLocators.ADD_LINK_BTN)
	public WebElement AddLinkBtn;
	
	@FindBy(xpath = ManageResourcePageLocators.MANAGE_RESOURCE_LINKS_TEXT)
	public WebElement ManageResourceLinksText;
	@FindBy(xpath = ManageResourcePageLocators.MANAGE_LINKS_TEXT)
	public WebElement ManageLinksText;
	
	@FindBy(xpath = ManageResourcePageLocators.CUSTOMERS_TAB_IN_POPUP)
	public WebElement CustomersTabInPopUp;
	
	@FindBy(xpath = ManageResourcePageLocators.ITEMS_TAB_IN_POPUP)
	public WebElement ItemsTabInPopUp;
	
	@FindBy(xpath = ManageResourcePageLocators.SUPPLIERS_TAB_IN_POPUP)
	public WebElement SuppliersTabInPopUp;
	
	@FindBy(xpath = ManageResourcePageLocators.EQUIPMENT_TAB_IN_POPUP)
	public WebElement EquipmentTabInPopUp;
	
	@FindBy(xpath = ManageResourcePageLocators.CUSTOMERS_CHK)
	public WebElement CustomersChk;
	
	@FindBy(xpath = ManageResourcePageLocators.EQUIPMENT_CHK)
	public WebElement EquipmentChk;
	
	@FindBy(xpath = ManageResourcePageLocators.ITEMS_CHK)
	public WebElement ItemsChk;
	
	@FindBy(xpath = ManageResourcePageLocators.SUPPLIERS_CHK)
	public WebElement SuppliersChk;
	
	@FindBy(xpath = ManageResourcePageLocators.SAVE_BTN)
	public WebElement SaveBtn;
	
	@FindBy(xpath = ManageResourcePageLocators.CUSTOMERS_CHK_NAME)
	public WebElement CustomersChkName;
	
	@FindBy(xpath = ManageResourcePageLocators.ITEMS_CHK_NAME)
	public WebElement ItemsChkName;
	
	@FindBy(xpath = ManageResourcePageLocators.SUPPLIERS_CHK_NAME)
	public WebElement SuppliersChkName;
	
	@FindBy(xpath = ManageResourcePageLocators.EQUIPMENT_CHK_NAME)
	public WebElement EquipmentChkName;
	
	@FindBy(xpath = ManageResourcePageLocators.ACTIVE_RESOURCE_BTN)
	public WebElement ActiveResourceBtn;
	
	


	/** "addCustomerResourceInstance" method is used to add the customer instance in the Customers Category tree
	 * @author pashine_a
	 * @param String 'CategoryName'
	 * @return boolean status
	 * IF supplier resource instance is added THEN true ELSE false
	 */	
	public boolean addCustomerResourceInstance(String CategoryName) {
		try {
			if(!selectResourceCategory(CustomerTab,"Customers")) {
				return false;
			}
			WebElement NewCustomerCategory = controlActions.perform_GetElementByXPath(ManageResourcePageLocators.CUSTOMER_CATEGORY_TRE, "CUSTOMER_CATEGORY_NAME", CategoryName);
			controlActions.doubleClickElement(NewCustomerCategory);
			WebElement NewCustomerCategoryPlus = controlActions.perform_GetElementByXPath(ManageResourcePageLocators.ADD_NEW_CUSTOMER_TRE, "CUSTOMER_CATEGORY_NAME", CategoryName);
			//			controlActions.clickElement(NewCustomerCategoryPlus, "Add customer instance icon");
			controlActions.perform_ClickWithJavaScriptExecutor(NewCustomerCategoryPlus);
			logInfo("Extracted customer category tree");
			Sync();
			return true;
		}catch(Exception e) {
			logError("Error while adding new resource instance in customer category tree - "+e.getMessage());
			return false;
		}

	}
	/** "addEquipmentResourceInstance" method is used to add the equipment instance in the Equipment Category tree
	 * @author pashine_a
	 * @param String 'CategoryName'
	 * @return boolean status
	 * IF supplier resource instance is added THEN true ELSE false
	 */	
	public boolean addEquipmentResourceInstance(String CategoryName) {
		try {
			if(!selectResourceCategory(EquipmentTab,"Equipment")) {
				return false;
			}
			WebElement NewEquipmentCategory = controlActions.perform_GetElementByXPath(ManageResourcePageLocators.EQUIPMENT_CATEGORY_TRE, "EQUIPMENT_CATEGORY_NAME", CategoryName);
			controlActions.doubleClickElement(NewEquipmentCategory);
			WebElement NewEquipmetCategoryPlus = controlActions.perform_GetElementByXPath(ManageResourcePageLocators.ADD_NEW_EQUIPMENT_TRE, "EQUIPMENT_CATEGORY_NAME", CategoryName);
			//			controlActions.clickElement(NewEquipmetCategoryPlus, "Add equipment instance icon");
			controlActions.perform_ClickWithJavaScriptExecutor(NewEquipmetCategoryPlus);
			logInfo("Extracted equipment category tree");
			Sync();
			return true;
		}catch(Exception e) {
			logError("Error while adding new resource instance in Equipment category tree - "+e.getMessage());
			return false;
		}

	}

	/**
	 * This method is used to add item instance
	 * @author choubey_a
	 * @param mng2 the path of the item category and String 'CategoryName'
	 * @return boolean This returns true if the item instance added
	 */

	public boolean addItemResourceInstance( String CategoryName) {
		try {
			if(!selectResourceCategory(ItemsTab,"Items")) {
				return false;
			}
			WebElement NewItemCategory = controlActions.perform_GetElementByXPath(ManageResourcePageLocators.ITEM_CATEGORY_TRE, "ITEM_CATEGORY_NAME", CategoryName);
			controlActions.doubleClickElement(NewItemCategory);
			WebElement NewItemCategoryPlus = controlActions.perform_GetElementByXPath(ManageResourcePageLocators.ADD_NEW_ITEM_TRE, "ITEM_CATEGORY_NAME", CategoryName);
			//			controlActions.clickElement(NewItemCategoryPlus, "Add item instance icon");
			controlActions.perform_ClickWithJavaScriptExecutor(NewItemCategoryPlus);
			//controlActions.sendKeys(SetResourceNameTxt, ResourceDesignerPage.itemInstanceValue1, "Item instance name");
			logInfo("Extracted item category tree");
			Sync();
			return true;
		}catch(Exception e) {
			logError("Error while adding new resource instance in item category tree - "+e.getMessage());
			return false;
		}
	}


	/** "addSupplierResourceInstance" method is used to add the supplier instance in the Suppliers Category tree
	 * @author pashine_a
	 * @param String 'CategoryName'
	 * @return boolean status
	 * IF supplier resource instance is added THEN true ELSE false
	 */	
	public boolean addSupplierResourceInstance(String CategoryName) {
		try {
			if(!selectResourceCategory(SuppliersTab,"Suppliers")) {
				return false;
			}
			WebElement NewSupplierCategory = controlActions.perform_GetElementByXPath(ManageResourcePageLocators.SUPPLIER_CATEGORY_TRE, "SUPPLIER_CATEGORY_NAME", CategoryName);
			controlActions.doubleClickElement(NewSupplierCategory);
			WebElement NewSupplierCategoryPlus = controlActions.perform_GetElementByXPath(ManageResourcePageLocators.ADD_NEW_SUPPLIER_TRE, "SUPPLIER_CATEGORY_NAME", CategoryName);
			controlActions.perform_ClickWithJavaScriptExecutor(NewSupplierCategoryPlus);
			//			controlActions.clickElement(NewSupplierCategoryPlus, "Add supplier instance icon");
			//controlActions.sendKeys(SetResourceNameTxt, ResourceDesignerPage.supplierInstanceValue1, "Supplier instance name");
			logInfo("Extracted supplier category tree");
			Sync();
			return true;
		}catch(Exception e) {
			logError("Error while adding new resource instance in supplier category tree - "+e.getMessage());
			return false;
		}

	}


	/**
	 * This method is used to link supplier and item resource instance
	 * @author choubey_a
	 * @param mg3 the path of the item instance selection in items tab for a supplier resource
	 * @return boolean This returns true if the item instance gets linked to the supplier instance
	 */

//	public boolean linkItemSupplier(String iteminstance) {
//		try {
//			controlActions.WaitforelementToBeClickable(ItemsTabInSupplier);
//			controlActions.clickElement(ItemsTabInSupplier);
//			controlActions.WaitforelementToBeClickable(SelectNewBtn);
//			controlActions.clickElement(SelectNewBtn);
//			Sync();
//			controlActions.WaitforelementToBeClickable(NameDRD);
//			controlActions.clickElement(NameDRD);
//			controlActions.WaitforelementToBeClickable(FilterOption);
//			controlActions.clickElement(FilterOption);
//			FilterInput.sendKeys(iteminstance);
//			controlActions.WaitforelementToBeClickable(FilterBtn);
//			controlActions.clickElement(FilterBtn);
//			Sync();
//			WebElement ItemInstance = controlActions.perform_GetElementByXPath(ManageResourcePageLocators.SELECT_ITEM_CBO, "ITEM_NAME",iteminstance);
//			controlActions.clickElement(ItemInstance);
//			controlActions.WaitforelementToBeClickable(PopUpSaveBtn);
//			controlActions.clickElement(PopUpSaveBtn);
//			Sync();
//			logInfo("Linked Items resource with Suppliers resource");
//			return true;
//		}catch(Exception e) {
//			logError("Failed to Link Items resource with Suppliers resource " + e.getMessage());
//			return false;
//		}
//	}
	public boolean linkItemSupplier(String iteminstance) {
		try {
			controlActions.WaitforelementToBeClickable(LinksTab);
			controlActions.clickElement(LinksTab);
			controlActions.WaitforelementToBeClickable(AddLinkBtn);
			controlActions.clickElement(AddLinkBtn);
			Sync();
			controlActions.clickElement(ItemsTabInPopUp);
			Sync();
			controlActions.WaitforelementToBeClickable(NameDRD);
			controlActions.clickElement(NameDRD);
			controlActions.WaitforelementToBeClickable(FilterOption);
			controlActions.clickElement(FilterOption);
			FilterInput.sendKeys(iteminstance);
			controlActions.WaitforelementToBeClickable(FilterBtn);
			controlActions.clickElement(FilterBtn);
			Sync();
			WebElement ItemInstance = controlActions.perform_GetElementByXPath(ManageResourcePageLocators.SELECT_ITEM_CBO, "ITEM_NAME",iteminstance);
			controlActions.clickElement(ItemInstance);
			controlActions.WaitforelementToBeClickable(PopUpSaveBtn);
			controlActions.clickElement(PopUpSaveBtn);
			Sync();
			logInfo("Linked Items resource with Suppliers resource");
			return true;
		}catch(Exception e) {
			logError("Failed to Link Items resource with Suppliers resource " + e.getMessage());
			return false;
		}
	}
	/** "setFields" method is used to set the value in fields of the resource instance. For 'Select One' field, chooses first option
	 * @author pashine_a
	 * @param It takes 3 parameters. 'number' for Numeric value , String 'text' for Single Line Text value and String 'resourceInstanceName'
	 * @return boolean status
	 * IF all the values have been set THEN true ELSE false
	 */	
	public boolean setFields(int number,String text, String resourceInstanceName) {
		WebElement input;
		String inputPath;
		int count=1, visibleFieldCount = 1;
		try {
			controlActions.sendKeys(SetResourceInstanceNameTxt, resourceInstanceName);
			for(int i=1;i<AllFieldsTxt.size();i++) {
				count++;
				if(AllFieldsTxt.get(i).isDisplayed()) {
					inputPath = controlActions.perform_GetDynamicXPath(ManageResourcePageLocators.FIELD_INPUTS_TXT, "COUNT", Integer.toString(count));
					if(controlActions.isElementDisplayed(inputPath)) {
						visibleFieldCount++;
						input = controlActions.perform_GetElementByXPath(ManageResourcePageLocators.FIELD_INPUTS_TXT, "COUNT", Integer.toString(count));
						if(input.getAttribute("role")==null) {
							controlActions.sendKeys(input, text);
						}else {
							if(input.getAttribute("role").equals("spinbutton")) {
								controlActions.sendKeys(input, Integer.toString(number));
								i++;
							}
							if(input.getAttribute("role").equals("combobox")) {
								controlActions.clickElement(input);
								controlActions.sendKey(input,Keys.DOWN);
							}
						}
						if(AllFieldsInputTxt.size()==visibleFieldCount) {
							break;
						}
					}
				}
			}
			logInfo("VALUES in all the field for resource instance has been SET");
			return true;
		}catch(Exception e) {
			logError("ERROR while setting field values - "+e.getMessage());
			return false;
		}
	}

	/** "createResource" method is used create a resource of any type (Resource Category & Resource Instance)
	 * @author pashine_a
	 * @param It takes 4 parameters. WebDriver instance 'driver' , String 'categoryType', String categoryName, String 'instanceName'
	 * @return boolean status
	 * IF the resource is created THEN true ELSE false
	 */	
	public boolean createResource(String categoryType, String categoryName, String instanceName) {
		ResourceDesignerPage rdp;
		try {
			rdp = clickResourceDesignerMenu();
			if(rdp.error) {
				return false;
			}
			categoryType = categoryType.toUpperCase();

			switch(categoryType) {
			case "CUSTOMERS":
				if(!rdp.createCustomersCategory(categoryName)) {
					return false;	
				}
				if(clickResourcesMenu().error) {
					return false;	
				}
				if(!addCustomerResourceInstance(categoryName)) {
					return false;	
				}
				if(!setFields(12345,"Automation Test",instanceName)) {
					return false;	
				}
				controlActions.clickElement(SaveResourceBtn);
				Sync();
				if(!verifyCreatedResource()){
					return false;	
				}				
				break;

			case "EQUIPMENT":
				if(!rdp.createEquipmentCategory(categoryName)) {
					return false;	
				}
				if(clickResourcesMenu().error) {
					return false;	
				}
				if(!addEquipmentResourceInstance(categoryName)) {
					return false;	
				}
				if(!setFields(12345,"Automation Test",instanceName)) {
					return false;	
				}
				controlActions.clickElement(SaveResourceBtn);
				Sync();
				if(!verifyCreatedResource()){
					return false;	
				}
				break;

			case "ITEMS":
				if(!rdp.createItemsCategory(categoryName)) {
					return false;	
				}
				if(clickResourcesMenu().error) {
					return false;	
				}
				if(!addItemResourceInstance(categoryName)) {
					return false;	
				}
				if(!setFields(12345,"Automation Test",instanceName)) {
					return false;	
				}
				controlActions.clickElement(SaveResourceBtn);
				Sync();
				if(!verifyCreatedResource()){
					return false;			
				}
				break;

			case "SUPPLIERS":
				if(!rdp.createSuppliersCategory(categoryName)) {
					return false;			
				}
				if(clickResourcesMenu().error) {
					return false;			
				}
				if(!addSupplierResourceInstance(categoryName)) {
					return false;			
				}
				if(!setFields(12345,"Automation Test",instanceName)) {
					return false;			
				}
				controlActions.clickElement(SaveResourceBtn);
				Sync();
				if(!verifyCreatedResource()){
					return false;			
				}
				break;

			default:
				logInfo("INVALID resource category name");
				return false;
			}
			logInfo("Resource is created successfully");
			return true;
		}catch(Exception e) {
			logError("FAILED to create resource - "+e.getMessage());
			return false;
		}
	}

	/** "verifyCreatedResource" method is used to verify that the resource instance is created
	 * @author pashine_a
	 * @param null
	 * @return boolean status
	 * IF Added resource is verified THEN true ELSE false
	 */	
	public boolean verifyCreatedResource() {
		try {
			if(!controlActions.isElementDisplayedOnPage(ModifiedByLbl)) {
				logError("NOT VERIFIED created resource");
				return false;
			}
			logInfo("VERIFIED created resource");
			return true;
		}catch(Exception e) {
			logError("FAILED to VERIFY created resource - "+e.getMessage());
			return false;
		}
	}

	/** "linkToLocation" method is used to link the the resource instance with the location
	 * @author pashine_a
	 * @param String - 'locationInstance'
	 * @return boolean status
	 * IF resource in linked with the location THEN true ELSE false
	 */	
	public boolean linkToLocation(String locationInstance) {
		try {
			controlActions.doubleClickElement(LocationsTab);
			WebElement locationNameChk = controlActions.perform_GetElementByXPath(ManageResourcePageLocators.LOCATION_CHK, "LOCATION_NAME",locationInstance);
			controlActions.clickElement(locationNameChk);
			controlActions.clickElement(SaveLocationBtn);
			Sync();
			logInfo("Location is selected"); 
			return true;
		}catch(Exception e) {
			logError("FAILED to select the location - "+e.getMessage());
			return false;
		}
	}

	/** "createResourceLinkLocation" method is used to create the resource & link the the resource instance with the location
	 * @author pashine_a
	 * @param String 'categoryType'
	 * @param String 'categoryName' 
	 * @param String 'instanceName'
	 * @param String 'locationInstanceValue'
	 * @return boolean status
	 * IF created resource in linked with the location THEN true ELSE false
	 */	
	public boolean createResourceLinkLocation(String categoryType, String categoryName, String instanceName,String locationInstanceValue) {
		try {
			if(!createResource(categoryType, categoryName, instanceName)) {
				return false;
			}

			if(!linkToLocation(locationInstanceValue)) {
				return false;
			}
			logInfo("Flow -> 'Resource Creation - Location Linking' COMPLETED");
			return true;
		}catch(Exception e) {
			logError("Error in flow -> 'Resource Creation - Location Linking' - "+e.getMessage());
			return false;
		}
	}

	/** "addResourceInstances" method is used to add the resource instances
	 * @author pashine_a
	 * @param categoryType
	 * @param categoryName
	 * @param instances[][]
	 * @param linkLocation
	 * @param locationName
	 * @return boolean status
	 * IF instance(s) are added THEN true ELSE false
	 */	
	public boolean addResourceInstances(String categoryType, String categoryName, String instances[][], boolean linkLocation, String locationName) {
		WebElement newResourceCategory, NewResourceCategoryPlus;
		try {
			if(clickResourcesMenu().error) {
				return false;	
			}
			controlActions.refreshPage();
			categoryType = categoryType.toUpperCase();
			switch(categoryType) {
			case "CUSTOMERS":
				if(!selectResourceCategory(CustomerTab, "Customers")) {
					return false;
				}
				break;

			case "EQUIPMENT":
				if(!selectResourceCategory(EquipmentTab, "Equipment")) {
					return false;
				}
				break;

			case "ITEMS":
				if(!selectResourceCategory(ItemsTab, "Items")) {
					return false;
				}
				break;

			case "SUPPLIERS":
				if(!selectResourceCategory(SuppliersTab, "Suppliers")) {
					return false;
				}
				break;

			default:
				logInfo("INVALID resource category name");
				return false;
			}
			Sync();
			threadsleep(2000);
			newResourceCategory = controlActions.perform_GetElementByXPath(ManageResourcePageLocators.RESOURCE_CATEGORY_TRE, "RESOURCE_CATEGORY", categoryName);
			controlActions.doubleClickElement(newResourceCategory);
			for(int i=0;i<instances.length;i++) {
				newResourceCategory = controlActions.perform_GetElementByXPath(ManageResourcePageLocators.RESOURCE_CATEGORY_TRE, "RESOURCE_CATEGORY", categoryName);
				controlActions.clickElement(newResourceCategory);
				NewResourceCategoryPlus = controlActions.perform_GetElementByXPath(ManageResourcePageLocators.ADD_NEW_RESOURCE_TRE, "RESOURCE_CATEGORY_NAME", categoryName);
				controlActions.clickElement(NewResourceCategoryPlus);
				if(!setFields(12345,"Automation Test",instances[i][0])) {
					return false;	
				}
				controlActions.clickElement(SaveResourceBtn);
				Sync();
				if(!verifyCreatedResource()){
					return false;	
				}
				if(instances[i][1].equalsIgnoreCase("false")) {
					if(!disableResource()) {
						return false;
					}
				}
				if(linkLocation) {
					if(!linkToLocation(locationName)) {
						return false;
					}
				}
			}
			logInfo("Created resource instance(s) in resource category tree");
			return true;
		}catch(Exception e) {
			logError("Error while creating resource instance(s) in resource category tree - "+e.getMessage());
			return false;
		}

	}

	/** "createResources" method is used create a resource of any type (Resource Category & Resource Instance)
	 * @author pashine_a
	 * @param categoryType
	 * @param categoryName
	 * @param instanceNames[][]
	 * @param linkLocation
	 * @param locationName
	 * @return boolean status
	 * IF the resource is created THEN true ELSE false
	 */	
	public boolean createResources(String categoryType, String categoryName, String instanceNames[][], boolean linkLocation, String locationInstanceValue) {
		ResourceDesignerPage rdp;
		try {
			rdp = clickResourceDesignerMenu();
			if(rdp.error) {
				return false;
			}
			categoryType = categoryType.toUpperCase();
			switch(categoryType) {
			case "CUSTOMERS":
				if(!rdp.createCustomersCategory(categoryName)) {
					return false;	
				}
				break;

			case "EQUIPMENT":
				if(!rdp.createEquipmentCategory(categoryName)) {
					return false;	
				}
				break;

			case "ITEMS":
				if(!rdp.createItemsCategory(categoryName)) {
					return false;	
				}
				break;

			case "SUPPLIERS":
				if(!rdp.createSuppliersCategory(categoryName)) {
					return false;			
				}
				break;

			default:
				logInfo("INVALID resource category name");
				return false;
			}
			if(!addResourceInstances(categoryType,categoryName, instanceNames, linkLocation, locationInstanceValue)) {
				return false;	
			}
			logInfo("All resources is created successfully");
			return true;
		}catch(Exception e) {
			logError("FAILED to create resources - "+e.getMessage());
			return false;
		}
	}

	/** "disableResource" method is used to disable the resource
	 * @author pashine_a
	 * @param null
	 * @return boolean status
	 * IF resource is disabled THEN true ELSE false
	 */	
	public boolean disableResource() {
		try {
			threadsleep(2000);
			controlActions.clickElement(DisableResourceBtn);
			controlActions.clickElement(DisablePopUpYesBtn);
			controlActions.clickElement(SaveResourceBtn);
			Sync();
			logInfo("Resource is disabled"); 
			return true;
		}catch(Exception e) {
			logError("FAILED to disable the resource - "+e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to create resource as per the Category type and name.
	 * @author hingorani_a
	 * @param rdp An object of Class ResourceDetailParams, in order to set
	 * different details and to enable/disable a resource instance.
	 * @return boolean This returns boolean true after creating instances for a Category
	 */
	public boolean createResource(ResourceDetailParams rdp) {
		try {
			
			switch(rdp.CategoryType.toUpperCase()) {
			case RESOURCETYPES.CUSTOMERS:
				if(!addCustomerResourceInstance(rdp.CategoryName))
					return false;	
				break;
			case RESOURCETYPES.EQUIPMENT:
				if(!addEquipmentResourceInstance(rdp.CategoryName))
					return false;	
				break;
			case RESOURCETYPES.ITEMS:
				if(!addItemResourceInstance(rdp.CategoryName))
					return false;	
				break;

			case RESOURCETYPES.SUPPLIERS:
				if(!addSupplierResourceInstance(rdp.CategoryName))
					return false;			
			break;

			default:
				logInfo("Invalid resource category name - " + rdp.CategoryName);
				return false;
			}

			if(!setFields(rdp.InstanceName,rdp.NumberFieldValue,rdp.TextFieldValue)) {
				return false;	
			}
			controlActions.clickElement(SaveResourceBtn);
			Sync();
			logInfo("Saving resource instance named - " + rdp.InstanceName + " for category - " + rdp.CategoryName);

			if(!verifyCreatedResource()){
				return false;	
			}

			if(!rdp.InstanceStatus) {
				if(!disableResource()) {
					return false;
				}
			}

			logInfo("Resource instance - " + rdp.InstanceName + " created successfully");
			return true;
		}
		catch(Exception e) {
			logError("Failed to create resource instance - " + rdp.InstanceName
					+ e.getMessage());
			return false;
		}
	}	

	/**
	 * This method is used to set fields when creating a resource instance
	 * @author hingorani_a
	 * @param resourceInstanceName Name of Resource instance
	 * @param number The value you would like to set for Number fields
	 * @param text The value you would like to set for Text fields 
	 * @return boolean This returns true if values are set for resource instance
	 */
	public boolean setFields(String resourceInstanceName, int number, String text) {
		try {
			SetResourceInstanceNameTxt.clear();
			controlActions.sendKeys(SetResourceInstanceNameTxt, resourceInstanceName);
			logInfo("Resource instance name added as - " + resourceInstanceName );

			for(int i = 1; i < AllFieldsInputTxt.size(); i++) {
				if(AllFieldsInputTxt.get(i).isDisplayed()) {

					if(AllFieldsInputTxt.get(i).getAttribute("role")==null) {
						controlActions.sendKeys(AllFieldsInputTxt.get(i), text);
						logInfo("Resource text field set as - " + text);
					}
					else if(AllFieldsInputTxt.get(i).getAttribute("role").equals("spinbutton")) {
						controlActions.sendKeys(AllFieldsInputTxt.get(i), Integer.toString(number));
						logInfo("Resource numeric field set as - " + number);
						//i++;
					}
					else if(AllFieldsInputTxt.get(i).getAttribute("role").equals("combobox")) {
						controlActions.clickElement(AllFieldsInputTxt.get(i));
						controlActions.actionDown(); 
						logInfo("Resource combobox value is set");		
					}
					else {
						logInfo("The value of role tag for input element is - " + AllFieldsInputTxt.get(i).getAttribute("role"));
					}

					//Sync();
				}
			}
			logInfo("Values in all the field for resource instance has been SET");
			return true;
		}
		catch(Exception e) {
			logError("Failed while setting field values - "+ e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to create and link Resource to location.
	 * @author hingorani_a
	 * @param rdp An object of Class ResourceDetailParams, in order to set
	 * different details and to enable/disable a resource instance.
	 * @return boolean This returns boolean true after creating and linking Resource to location
	 */
	public boolean createResourceLinkLocation(ResourceDetailParams rdp) {
		try {
			for (Map.Entry<String, Boolean> entry : rdp.InstanceNames.entrySet()) {
				String instanceName = entry.getKey();
				boolean instanceStatus = entry.getValue();

				rdp.InstanceName = instanceName;
				rdp.InstanceStatus = instanceStatus;
				Sync();

				if(!createResource(rdp)) {
					return false;
				}

				if (rdp.LinkLocation) {
					if (rdp.LocationInstanceValue != null) {
						if (!linkToLocation(rdp.LocationInstanceValue)) {
							return false;
						}
					}
					if (rdp.LocationInstanceValues != null) {
						controlActions.doubleClickElement(LocationsTab);
						for(String loc : rdp.LocationInstanceValues) {
							WebElement locationNameChk = controlActions.perform_GetElementByXPath(
									ManageResourcePageLocators.LOCATION_CHK, "LOCATION_NAME", loc);
							controlActions.clickElement(locationNameChk);
						}
						controlActions.clickElement(SaveLocationBtn);
						Sync();
						logInfo("Could set location(s)-" + rdp.LocationInstanceValues + " for resource - " + rdp.InstanceName);
					}
					logInfo("Flow -> 'Resource Creation - Location Linking' COMPLETED");
				}
			}
			return true;
		}
		catch(Exception e) {
			logError("Error in flow -> 'Resource Creation - Location Linking' - "+e.getMessage());
			return false;
		}
	}	

	/** "selectResourceCategory" method is used to select the resource type in Resource Designer & Resource Manager
	 * @author pashine_a
	 * @param WebElement 'category'
	 * @return boolean status
	 * IF category is selected THEN true ELSE false
	 */	
	public boolean selectResourceCategory(WebElement category, String categoryName) {
		String categorySelectedStatus;
		try {
			controlActions.refreshPage();
			Sync();
			while(true) {
				controlActions.clickElement(category);
				Sync();
				threadsleep(2000);
				categorySelectedStatus = category.getAttribute("class");
				if(categorySelectedStatus.contains("title-highlight")) {
					break;
				}
			}
			logInfo(categoryName+" category Tab is selected");
			return true;
		}catch(Exception e) {
			logError("Fail to select the "+categoryName+" category tab");
			return false;
		}
	}

	/** "linkToLocation" method is used to link the the resource instance with the location
	 * @author pashine_a
	 * @param String - 'locationInstance'
	 * @return boolean status
	 * IF resource in linked with the location THEN true ELSE false
	 */	
	public boolean linkToLocations(String locationInstances[]) {
		WebElement locationNameChk;
		try {
			controlActions.doubleClickElement(LocationsTab);
			for(int i=0;i<locationInstances.length;i++) {
				locationNameChk = controlActions.perform_GetElementByXPath(ManageResourcePageLocators.LOCATION_CHK, "LOCATION_NAME",locationInstances[i]);
				controlActions.clickElement(locationNameChk);
			}
			controlActions.clickElement(SaveLocationBtn);
			Sync();
			logInfo("Location(s) are selected"); 
			return true;
		}catch(Exception e) {
			logError("FAILED to select the location(s) - "+e.getMessage());
			return false;
		}
	}

	/** "verifyResourceAvailability" method is used verify that resource(s) are avaiable
	 * @author pashine_a
	 * @param String - 'resourceType'
	 * @param String - 'categoryName'
	 * @param String - 'resources'

	 * @return boolean status
	 * IF resource in linked with the location THEN true ELSE false
	 */	
	public boolean verifyResourceAvailability(String resourceType, String categoryName, List<String> resources) {
		int resourceFoundFlag=0;
		try {
			resourceType = resourceType.toUpperCase();
			switch(resourceType) {
			case RESOURCETYPES.CUSTOMERS:
				if(!selectResourceCategory(CustomerTab,"Customers")) {
					return false;
				}
				break;
			case RESOURCETYPES.EQUIPMENT:
				if(!selectResourceCategory(EquipmentTab,"Equipment")) {
					return false;
				}
				break;
			case RESOURCETYPES.ITEMS:
				if(!selectResourceCategory(ItemsTab,"Items")) {
					return false;
				}
				break;

			case RESOURCETYPES.SUPPLIERS:
				if(!selectResourceCategory(SuppliersTab,"Suppliers")) {
					return false;
				}
				break;

			default:
				logInfo("Invalid resource type - " + resourceType);
				return false;
			}

			WebElement NewCustomerCategory = controlActions.perform_GetElementByXPath(ManageResourcePageLocators.RESOURCE_CATEGORY_TRE, "RESOURCE_CATEGORY", categoryName);
			controlActions.doubleClickElement(NewCustomerCategory);
			Sync();

			for(int i=0;i<resources.size();i++) {
				resourceFoundFlag = 0;
				for(int j=0;j<TotalResourceTre.size();j++) {
					if(resources.get(i).equals(TotalResourceTre.get(j).getText().toString())) {
						resourceFoundFlag = 1;
						break;
					}
				}	
				if(resourceFoundFlag==0) {
					logError("Resource is not avaiable");
					return false;
				}
			}
			logInfo("Resources(s) are present"); 
			return true;
		}catch(Exception e) {
			logError("Resources are not present - "+e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to search for a resource instance
	 * @author hingorani_a
	 * @param resourceName Name of Resource instance
	 * @return boolean This returns true if resource instance is searched successfully
	 */
	public boolean searchResource(String resourceName) {
		try {
			controlActions.sendKeys(SearchTxt, resourceName);
			if(SearchFilterLst.size() == 1) {
				SearchFilterLst.get(0).click();
				Sync();
				logInfo("Search for resource - " + resourceName);
				return true;
			}
			else {
				logInfo("More than one entry found for - " + resourceName);
				return false;
			}
		}
		catch(Exception e) {
			logError("FAILED to search for resource - " +e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to set locations to a resource instance
	 * @author hingorani_a
	 * @param resourceType Use Class RESOURCETYPES to set type of Resource instance
	 * @param resourceName Name of Resource instance
	 * @param locationInst List of location values to be set
	 * @return boolean This returns true if locations are set for resource instance
	 */
	public boolean searchAndAddLinkToResource(String resourceType, String resourceName, List<String> locationInst) {
		try {
			switch(resourceType.toUpperCase()) {
			case RESOURCETYPES.CUSTOMERS:
				if(!selectResourceCategory(CustomerTab,"Customers")) {
					return false;
				}
				break;
			case RESOURCETYPES.EQUIPMENT:
				if(!selectResourceCategory(EquipmentTab,"Equipment")) {
					return false;
				}
				break;
			case RESOURCETYPES.ITEMS:
				if(!selectResourceCategory(ItemsTab,"Items")) {
					return false;
				}
				break;

			case RESOURCETYPES.SUPPLIERS:
				if(!selectResourceCategory(SuppliersTab,"Suppliers")) {
					return false;
				}

			default:
				logError("Invalid resource type option - " + resourceType);
			}

			if(!searchResource(resourceName)) {
				return false;
			}

			controlActions.WaitforelementToBeClickable(LocationsTab);
			controlActions.doubleClickElement(LocationsTab);

			for(String loc : locationInst) {
				WebElement locationNameChk = controlActions.perform_GetElementByXPath(ManageResourcePageLocators.LOCATION_CHK, 
						"LOCATION_NAME", loc);
				if(!locationNameChk.isSelected())
					controlActions.clickElement(locationNameChk);
			}
			controlActions.clickElement(SaveLocationBtn);
			Sync();

			logInfo("Could set location(s) for resource - " + resourceName);
			return true;
		}
		catch(Exception e) {
			logError("FAILED to set location(s) for resource - " + resourceName + e.getMessage());
			return false;
		}
	}
	
	/**
	 * This method is used to add instances for sub category of a category for any Resource
	 * 
	 * @author ahmed_tw
	 * @param categoryType   : selects the resource type for category
	 * @param categoryName   : is Category name
	 * @param instances[][]	 : provides instances names to be added with their enable/disable status
	 * @param linkLocation   : decides whether this instance is required to linked to a location or not
	 * @param locationName   : the location name which is to be linked with the created instance
	 * @return boolean : IF instance(s) are added THEN true ELSE false
	 */
	public boolean addResourceInstances(String categoryType, String categoryName, String subcategoryName,
			String instances[][], boolean linkLocation, String locationName) {
		WebElement newResourceCategory, NewResourceCategoryPlus;
		try {
			if (clickResourcesMenu().error) {
				return false;
			}
			controlActions.refreshPage();
			categoryType = categoryType.toUpperCase();
			switch (categoryType) {
			case "CUSTOMERS":
				if (!selectResourceCategory(CustomerTab, "Customers")) {
					return false;
				}
				break;

			case "EQUIPMENT":
				if (!selectResourceCategory(EquipmentTab, "Equipment")) {
					return false;
				}
				break;

			case "ITEMS":
				if (!selectResourceCategory(ItemsTab, "Items")) {
					return false;
				}
				break;

			case "SUPPLIERS":
				if (!selectResourceCategory(SuppliersTab, "Suppliers")) {
					return false;
				}
				break;

			default:
				logInfo("INVALID resource category name");
				return false;
			}
			Sync();
			threadsleep(2000);
			newResourceCategory = controlActions.perform_GetElementByXPath(
					ManageResourcePageLocators.RESOURCE_CATEGORY_TRE, "RESOURCE_CATEGORY", categoryName);
			controlActions.doubleClickElement(newResourceCategory);
			for (int i = 0; i < instances.length; i++) {
				newResourceCategory = controlActions.perform_GetElementByXPath(
						ManageResourcePageLocators.RESOURCE_CATEGORY_TRE, "RESOURCE_CATEGORY", subcategoryName);
				controlActions.clickElement(newResourceCategory);
				NewResourceCategoryPlus = controlActions.perform_GetElementByXPath(
						ManageResourcePageLocators.ADD_NEW_RESOURCE_TRE, "RESOURCE_CATEGORY_NAME", subcategoryName);
				controlActions.clickElement(NewResourceCategoryPlus);
				if (!setFields(12345, "Automation Test", instances[i][0])) {
					return false;
				}
				controlActions.clickElement(SaveResourceBtn);
				Sync();
				if (!verifyCreatedResource()) {
					return false;
				}
				if (instances[i][1].equalsIgnoreCase("false")) {
					if (!disableResource()) {
						return false;
					}
				}
				if (linkLocation) {
					if (!linkToLocation(locationName)) {
						return false;
					}
				}
			}
			logInfo("Created resource instance(s) in resource category tree");
			return true;
		} catch (Exception e) {
			logError("Error while creating resource instance(s) in resource category tree - " + e.getMessage());
			return false;
		}

	}
	
	/**
	 * This method is used to Search and Disable resource as per type mentioned
	 * @author hingorani_a
	 * @param resourceType Use Class RESOURCETYPES to set Type of Resource like Customers, Equipment, etc 
	 * @param resourceName Name of Resource instance
	 * @return boolean This returns true if resource instance is disabled successfully
	 */
	public boolean searchAndDisableResource(String resourceType, String resourceName) {
		try {
			if(!searchResourceAsPerType(resourceType, resourceName))
				return false;
			
			if(!disableResource())
				return false;
			
			logInfo("Disabled the resource " + resourceName);
			return true;
		}
		catch(Exception e) {
			logError("Failed to disable resource - " + resourceName 
					+ e.getMessage());
			return false;
		}
	}
	
	/**
	 * This method is used to Search resource as per type mentioned
	 * @author hingorani_a
	 * @param resourceType Use Class RESOURCETYPES to set Type of Resource like Customers, Equipment, etc 
	 * @param resourceName Name of Resource instance
	 * @return boolean This returns true if resource instance is searched successfully
	 */
	public boolean searchResourceAsPerType(String resourceType, String resourceName) {
		try {
			switch(resourceType.toUpperCase()) {
			case RESOURCETYPES.CUSTOMERS:
				if(!selectResourceCategory(CustomerTab,"Customers")) {
					return false;
				}
				break;
			case RESOURCETYPES.EQUIPMENT:
				if(!selectResourceCategory(EquipmentTab,"Equipment")) {
					return false;
				}
				break;
			case RESOURCETYPES.ITEMS:
				if(!selectResourceCategory(ItemsTab,"Items")) {
					return false;
				}
				break;

			case RESOURCETYPES.SUPPLIERS:
				if(!selectResourceCategory(SuppliersTab,"Suppliers")) {
					return false;
				}

			default:
				logError("Invalid resource type option - " + resourceType);
			}

			if(!searchResource(resourceName)) {
				return false;
			}

			logInfo("Could search resource - " + resourceName);
			return true;
		}
		catch(Exception e) {
			logError("Failed to search for resource - " + resourceName + e.getMessage());
			return false;
		}
	}
	
	/**
	 * This method is used to click on Links Tab
	 * @author jadhav_akan
	 * @return boolean This returns true if Clicked on Links Tab
	 */
	public boolean clkLinksTab() {
		try {
			Sync();
			controlActions.clickElement(LinksTab);
			logInfo("CLICKED on Links Tab"); 
			return true;
		}catch(Exception e) {
			logError("FAILED to click on Links Tab- "+e.getMessage());
			return false;
		}
	}
	
	/**
	 * This method is used to click on ADD LINK Button
	 * @author jadhav_akan
	 * @return boolean This returns true if Clicked on ADD LINK Button
	 */
	public boolean clkAddLinkBtn() {
		try {
			
			controlActions.clickElement(AddLinkBtn);
			Sync();
			logInfo("CLICKED on Add Link Button"); 
			return true;
		}catch(Exception e) {
			logError("FAILED to click on Add Link Button- "+e.getMessage());
			return false;
		}
	}
	
//	/**
//	 * This method is used to check header Text on popUp is Displayed to verify popUp is Opened
//	 * @author jadhav_akan
//	 * @return boolean This returns true if header Text on popUp is Displayed
//	 */
//	public boolean headerTextOfPopupIsDisplayed() {
//		try {
//			controlActions.isElementDisplay(ManageResourceLinksText);
//			logInfo("Verifeid Manage Resource Links Pop up Header");
//			return true;
//		}catch(Exception e) {
//			logError("FAILED to verify Manage Resource Links Pop up Header "+e.getMessage());
//			return false;
//		}
//	}
	/**
	 * This method is used to check header Text on popUp is Displayed to verify popUp is Opened
	 * @author jadhav_akan
	 * @return boolean This returns true if header Text on popUp is Displayed
	 */
	public boolean headerTextOfPopupIsDisplayed() {
		try {
			controlActions.isElementDisplay(ManageLinksText);
			logInfo("Verifeid Manage Resource Links Pop up Header");
			return true;
		}catch(Exception e) {
			logError("FAILED to verify Manage Resource Links Pop up Header "+e.getMessage());
			return false;
		}
	}
	
	/**
	 * This method is used to select resources tab in ADD LINK PopUp
	 * @author jadhav_akan
	 * @param tabName : Name of the tab to be select
	 * @return boolean This returns true if mentioned resource category tab is selected
	 */
	public boolean selectTabInAddLinkPopUp(String tabName) {
		WebElement tab = null;
		try {
			switch (tabName) {
			case SelectTAB.CUSTOMERS : tab = CustomersTabInPopUp;break;
			case SelectTAB.EQUIPMENT : tab = EquipmentTabInPopUp;break;
			case SelectTAB.ITEMS 	 : tab = ItemsTabInPopUp;break;
			case SelectTAB.SUPPLIERS : tab = SuppliersTabInPopUp;break;
			}
			controlActions.click(tab);
			logInfo("Selected resource category tab -" + tabName);
			return true;
		}
		catch(Exception e) {
			logError("Failed to switch to category tab: " + tabName
					+ e.getMessage());
			return false;
		}
	}
	/**
	 * This method is used to check first checkBox of Customer tab/Equipment tab/Items tab/Suppliers tab in ADD LINK PopUp
	 * @author jadhav_akan
	 * @param resourceCategory : Name of resource category that needs to be selected
	 * @return boolean This returns true if Selects resource category tab and checked first resource
	 */
	public boolean selectFirstCheckboxForResourceCategoryInAddLinkPopUp(String resourceCategory) {
		WebElement firstChkbox = null;
		
		try {
			switch(resourceCategory) {
			case SelectTAB.CUSTOMERS : firstChkbox = CustomersChk;
									   break;
									   
			case SelectTAB.EQUIPMENT : firstChkbox = EquipmentChk;
									   break;
			
			case SelectTAB.ITEMS     : firstChkbox = ItemsChk;
			   						   break;
			   
			case SelectTAB.SUPPLIERS : firstChkbox = SuppliersChk;
							           firstChkbox.getText();
							           break;
			}
			controlActions.click(firstChkbox);
			firstChkbox.getText();
			logInfo("Selected resource category tab - " + resourceCategory + " and checked first resource");
			return true;
		
		}catch(Exception e) {
		logError("Failed to click on First Category " + e.getMessage());
		return false;
	}
}
	/**
	 * This method is used to get name of the first resource selected
	 * @author jadhav_akan
	 * @param resourceTab : Name of resource category
	 * @return String This returns value if gets first resource name
	 */
	public String getFirstResourceNameInAddLinkPopUp(String resourceTab) {
		WebElement firstResourceElement = null;
		try {
			switch(resourceTab) {
			case SelectResource.CUSTOMERS : firstResourceElement = CustomersChkName;
									        break;
									   
			case SelectResource.EQUIPMENT : firstResourceElement = EquipmentChkName;
									        break;
									   
			
			case SelectResource.ITEMS     : firstResourceElement = ItemsChkName;
			                                break;
			   
			case SelectResource.SUPPLIERS : firstResourceElement = SuppliersChkName;
			                                break;
			}
			logInfo("Succesfully get Resource Name "+firstResourceElement.getText());
			System.out.println(firstResourceElement.getText());
			return firstResourceElement.getText();
		}
		catch(Exception e) {
			logError("Failed to get Resource Name "+ e.getMessage());
		return null;
		}
	}
	/**
	 * This method is used to click on Save button
	 * @author jadhav_akan
	 * @return boolean This returns true if Clicked on save location button
	 */
	public boolean clkSaveBtn() {
		try {
			
			controlActions.clickElement(SaveBtn);
			logInfo("CLICKED on Save Button"); 
			return true;
		}catch(Exception e) {
			logError("FAILED to click on Save Button- "+e.getMessage());
			return false;
		}
	}
	
	/**
	 * This method is used to check Selected Resources Links is Displayed in Links Tab
	 * @author jadhav_akan
	 * @param resourceName : Name of resource
	 * @return String This returns value if Selected Resources Links is Displayed in Links Tab
	 */
	public boolean verifySelectedResourceLinkIsDisplayed(String resourceName) {
		try {
			WebElement resourceSelect = controlActions.perform_GetElementByXPath(ManageResourcePageLocators.RESOURCE_LINK_NAME,"RESOURCE",resourceName);
			if(controlActions.isElementDisplayedOnPage(resourceSelect)){
				logInfo("Selected Resource Link - "+resourceName+" is present");
				return true;
			}
			else {
				logError("Selected Resource Link - "+resourceName+" is not present");
				return false;
			}
		}catch(Exception e) {
			logError("Failed verify Document -  "+resourceName+"" + e.getMessage());
			return false;
		}
	}
	/**
	 * This method is used to check Resource is Disabled
	 * @author jadhav_akan
	 * @param resourceName : Name of resource
	 * @return This returns True if Resource is Disable else False 
	 */
	public boolean verifyResourceIsDisabled(String resourceName) {
		try {
			WebElement recordSelect = controlActions.perform_GetElementByXPath(ManageResourcePageLocators.DISABLED_RESOURCE_NAME,"RESOURCE",resourceName);
			if(!controlActions.isElementDisplayedOnPage(recordSelect)){
				logError("Resource  "+resourceName+" is not Disabled");
				return false;
			}
			logInfo("Verified Resource  "+resourceName+" is Disabled");
			return true;
		}catch(Exception e) {
			logError("Failed to verify Resource  - "+resourceName+" is Disabled " + e.getMessage());
			return false;
		}
	}
	/** "activeResource" method is used to active the resource
	 * @author jadhav_akan
	 * @param null
	 * @return boolean status
	 * IF resource is activate THEN true ELSE false
	 */	
	public boolean activeResource() {
		try {
			threadsleep(2000);
			controlActions.clickElement(ActiveResourceBtn);
			controlActions.clickElement(DisablePopUpYesBtn);
			controlActions.clickElement(SaveResourceBtn);
			Sync();
			logInfo("Resource is Activated"); 
			return true;
		}catch(Exception e) {
			logError("FAILED to active the resource - "+e.getMessage());
			return false;
		}
	}
	/**
	 * This method is used to Search and Active resource as per type mentioned
	 * @author hingorani_a
	 * @param resourceType Use Class RESOURCETYPES to set Type of Resource like Customers, Equipment, etc 
	 * @param resourceName Name of Resource instance
	 * @return boolean This returns true if resource instance is activated successfully
	 */
	public boolean searchAndActiveResource(String resourceType, String resourceName) {
		try {
			if(!searchResourceAsPerType(resourceType, resourceName))
				return false;
			
			if(!activeResource())
				return false;
			
			logInfo("Activated the resource " + resourceName);
			return true;
		}
		catch(Exception e) {
			logError("Failed to active resource - " + resourceName 
					+ e.getMessage());
			return false;
		}
	}
	/**
	 * This method is used to check Resource is Activated
	 * @author jadhav_akan
	 * @param resourceName : Name of resource
	 * @return This returns True if Resource is Active else False 
	 */
	public boolean verifyResourceIsActivated(String resourceName) {
		try {
			WebElement recordSelect = controlActions.perform_GetElementByXPath(ManageResourcePageLocators.DISABLED_RESOURCE_NAME,"RESOURCE",resourceName);
			if(controlActions.isElementDisplayedOnPage(recordSelect)){
				logError("Resource  "+resourceName+" is not Activated");
				return false;
			}
			logInfo("Verified Resource  "+resourceName+" is Activated");
			return true;
		}catch(Exception e) {
			logError("Failed to verify Resource  - "+resourceName+" is Disabled " + e.getMessage());
			return false;
		}
	}
	/**
	 * This method is used to unlink/unCheck locations from a resource instance
	 * @author jadhav_akan
	 * @param resourceType Use Class RESOURCETYPES to set type of Resource instance
	 * @param resourceName Name of Resource instance
	 * @param locationInst List of location values to be unlink/unCheck
	 * @return boolean This returns true if locations are unlink/unCheck from resource instance
	 */
	public boolean searchAndUnlinkLocationFromResource(String resourceType, String resourceName, List<String> locationInst) {
		try {
			switch(resourceType.toUpperCase()) {
			case RESOURCETYPES.CUSTOMERS:
				if(!selectResourceCategory(CustomerTab,"Customers")) {
					return false;
				}
				break;
			case RESOURCETYPES.EQUIPMENT:
				if(!selectResourceCategory(EquipmentTab,"Equipment")) {
					return false;
				}
				break;
			case RESOURCETYPES.ITEMS:
				if(!selectResourceCategory(ItemsTab,"Items")) {
					return false;
				}
				break;

			case RESOURCETYPES.SUPPLIERS:
				if(!selectResourceCategory(SuppliersTab,"Suppliers")) {
					return false;
				}

			default:
				logError("Invalid resource type option - " + resourceType);
			}

			if(!searchResource(resourceName)) {
				return false;
			}

			controlActions.WaitforelementToBeClickable(LocationsTab);
			controlActions.doubleClickElement(LocationsTab);

			for(String loc : locationInst) {
				WebElement locationNameChk = controlActions.perform_GetElementByXPath(ManageResourcePageLocators.LOCATION_CHK, 
						"LOCATION_NAME", loc);
				if(locationNameChk.isSelected())
					controlActions.clickElement(locationNameChk);
			}
			controlActions.clickElement(SaveLocationBtn);
			Sync();

			logInfo("Succesfully Unlinked location(s) - "+locationInst+" from resource - " + resourceName);
			return true;
		}
		catch(Exception e) {
			logError("FAILED to Unlinked location(s) from resource - " + resourceName + e.getMessage());
			return false;
		}
	}
	public static class ResourceDetailParams{
		public String CategoryType;
		public String CategoryName;
		public HashMap<String,Boolean> InstanceNames;
		public String TextFieldValue;
		public int NumberFieldValue;
		public String LocationInstanceValue;
		public List<String> LocationInstanceValues;
		public String InstanceName;
		public boolean InstanceStatus;
		public boolean LinkLocation = true;
	}

	public static class RESOURCETYPES{
		public static final String CUSTOMERS = "CUSTOMERS";
		public static final String EQUIPMENT = "EQUIPMENT";
		public static final String ITEMS = "ITEMS";
		public static final String SUPPLIERS = "SUPPLIERS";	
	}
	
	public static class SelectTAB{
		public static final String CUSTOMERS = "Customers";
		public static final String EQUIPMENT = "Equipment";
		public static final String ITEMS = "Items";
		public static final String SUPPLIERS = "Suppliers";
			
	}
}
