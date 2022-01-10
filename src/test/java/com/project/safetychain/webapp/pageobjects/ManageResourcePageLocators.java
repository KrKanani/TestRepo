package com.project.safetychain.webapp.pageobjects;

public class ManageResourcePageLocators{
	
	public static final String SEARCH_TXT = "filterText";
	public static final String SEARCH_FILTER_LST = "//*[@id='filterText_listbox']/li";

	//Resource Tabs
	public static final String CUSTOMER_CATEGORY_TAB = "//*[@id='scs-manage-resource-list-view-data']//div[@class='scs-list-view-content-container']/div[normalize-space(.)='Customers']";
	public static final String EQUIPMENT_CATEGORY_TAB = "//*[@id='scs-manage-resource-list-view-data']//div[@class='scs-list-view-content-container']/div[normalize-space(.)='Equipment']";
	public static final String ITEM_CATEGORY_TAB = "//*[@id='scs-manage-resource-list-view-data']//div[@class='scs-list-view-content-container']/div[normalize-space(.)='Items']";
	public static final String SUPPLIER_CATEGORY_TAB = "//*[@id='scs-manage-resource-list-view-data']//div[@class='scs-list-view-content-container']/div[normalize-space(.)='Suppliers']";

	//Resource Category (Dynamically used)
	public static final String CUSTOMER_CATEGORY_TRE = "//span[span[@class='scs-category-text ng-binding']]/span[text()='CUSTOMER_CATEGORY_NAME']"; 
	public static final String EQUIPMENT_CATEGORY_TRE = "//span[span[@class='scs-category-text ng-binding']]/span[text()='EQUIPMENT_CATEGORY_NAME']"; 
	public static final String ITEM_CATEGORY_TRE = "//span[span[@class='scs-category-text ng-binding']]/span[text()='ITEM_CATEGORY_NAME']";
	public static final String SUPPLIER_CATEGORY_TRE = "//span[span[@class='scs-category-text ng-binding']]/span[text()='SUPPLIER_CATEGORY_NAME']";
	public static final String RESOURCE_CATEGORY_TRE = "//span[@class='scs-category-text ng-binding' and text()='RESOURCE_CATEGORY']";
	
	public static final String TOTAL_RESOURCES_TRE = "//span[@class='scs-treenode-text ng-binding']";

	//Resource Category - Plus Icon (Dynamically used)
	public static final String ADD_NEW_CUSTOMER_TRE = "//span[span[@class='scs-category-text ng-binding']]/span[text()='CUSTOMER_CATEGORY_NAME']/following-sibling::i[@class='fa fa-plus-square ng-scope']";
	public static final String ADD_NEW_EQUIPMENT_TRE = "//span[span[@class='scs-category-text ng-binding']]/span[text()='EQUIPMENT_CATEGORY_NAME']/following-sibling::i[@class='fa fa-plus-square ng-scope']";
	public static final String ADD_NEW_ITEM_TRE = "//span[span[@class='scs-category-text ng-binding']]/span[text()='ITEM_CATEGORY_NAME']/following-sibling::i[@class='fa fa-plus-square ng-scope']";
	public static final String ADD_NEW_SUPPLIER_TRE = "//span[span[@class='scs-category-text ng-binding']]/span[text()='SUPPLIER_CATEGORY_NAME']/following-sibling::i[@class='fa fa-plus-square ng-scope']";
	public static final String ADD_NEW_RESOURCE_TRE = "//span[span[@class='scs-category-text ng-binding']]/span[text()='RESOURCE_CATEGORY_NAME']/following-sibling::i[@class='fa fa-plus-square ng-scope']";

	//Create Resource Instance Name
	public static final String RESOURCE_INSTANCE_TXT = "//*[@id='{{settings.treeId}}_tv_active']//input";
	public static final String RESOURCE_INSTANCE_NAME_TXT = "//div[div/label[text()='Name']]//input";
	public static final String TOTAL_FIELDS_TXT = "//*[@id='scsResourceDetails']//li//input[1]";
	public static final String FIELD_TXT_CNT = "//*[@id='scsResourceDetails']//li";
	public static final String FIELD_INPUTS_TXT = "//*[@id='scsResourceDetails']//li[COUNT]//input[1]";
	public static final String SAVE_RESOURCE_BTN = "//*[@id='scs-details-save-btn']";

	//Disable Resource
	public static final String DISABLE_RESOURCE_BTN = "//*[@id='StatusValue']/label[2]";
	public static final String YES_DLG_BTN = "//button[text()='YES']";
	public static final String DISABLED_RESOURCE_NAME = "//span[text()='RESOURCE']/following-sibling::i";
	
	//Locations Tab
	public static final String LOCATIONS_TAB = "//*[@id='tabstrip']//li[@data-res-name='Locations']/span[2]";
	public static final String LOCATION_CHK = "//td[span[contains(text(),'LOCATION_NAME')]]/input";
	public static final String SAVE_LOCATION_BTN = "//input[@type='button' and @value='SAVE']";

	//Items Tab - For Supplier Category
	public static final String ITEMS_TAB = "//*[@id='tabstrip']//li[@data-res-name='Items']/span[2]";
	public static final String SELECT_NEW_BTN = "//input[@type='button' and @value='SELECT NEW']";
	public static final String SELECT_ITEM_CBO = "//*[@id='scs-supp-item-grid-parent']//tr[td[text()='ITEM_NAME']]/td[1]/input";	
	public static final String MODIFIED_BY_LBL = "//*[@id='scsResourceDetails']//li[last()]//label[text()='Modified By']";
	public static final String POPUP_SAVE_BTN = "//*[@class='ui-dialog-buttonset']//button[contains(text(),'SAVE')]";
	public static final String NAME_DRD = "//*[@id='scs-supp-item-grid-parent']//a[contains(.,'Name')]/preceding-sibling::a/i[@class='fa fa-chevron-down']";
	public static final String FILTER_OPTION = "//*[@class='k-animation-container']//span[@class='k-icon k-i-filter']";
	public static final String FILTER_INPUT = "//*[@class='k-animation-container']//form[@class='k-filter-menu']//input";
	public static final String FILTER_BTN = "//*[@class='k-animation-container']//form[@class='k-filter-menu']//button[contains(.,'Filter')]";
	
	//Links Tab
	public static final String LINKS_TAB = "//*[@id='tabstrip']/ul/li[2]/span[2]";
	public static final String ADD_LINK_BTN = "//*[@id='scs-manage-resource-link-btn'][@class='btn ng-scope']";
	public static final String MANAGE_RESOURCE_LINKS_TEXT = "//*[@id='ui-id-3'][text()='Manage Resource Links']";
//	public static final String CUSTOMERS_TAB_IN_POPUP = "//*[@id='scs-add-link-tabstrip-popup']//span[text()='Customers']";
//	public static final String ITEMS_TAB_IN_POPUP = "//*[@id='scs-add-link-tabstrip-popup']//span[text()='Items']";
//	public static final String SUPPLIERS_TAB_IN_POPUP = "//*[@id='scs-add-link-tabstrip-popup']//span[text()='Suppliers']";
//	public static final String EQUIPMENT_TAB_IN_POPUP = "//*[@id='scs-add-link-tabstrip-popup']//span[text()='Equipment']";
//	public static final String CUSTOMERS_CHK = "(//*[@id='grid-Customers']/div[2]//input)[1]";
//	public static final String ITEMS_CHK = "(//*[@id='grid-Items']/div[2]//input)[1]";
//	public static final String SUPPLIERS_CHK = "(//*[@id='grid-Suppliers']/div[2]//input)[1]";
//	public static final String EQUIPMENT_CHK = "(//*[@id='grid-Equipment']/div[2]//input)[1]";
	public static final String SAVE_BTN = "*//button[2][text()='SAVE']";
	public static final String RESOURCE_LINK_NAME = "//*[@id='tabstrip-2']/div[1]/div/div/ul/li//span[text()='RESOURCE']";
//	public static final String CUSTOMERS_CHK_NAME = "//div[@id='grid-Customers']//tr[@class='k-state-selected']/td[2]";
//	public static final String ITEMS_CHK_NAME = "//div[@id='grid-Items']//tr[@class='k-state-selected']/td[2]";
//	public static final String SUPPLIERS_CHK_NAME = "//div[@id='grid-Suppliers']//tr[@class='k-state-selected']/td[2]";
//	public static final String EQUIPMENT_CHK_NAME = "//div[@id='grid-Equipment']//tr[@class='k-state-selected']/td[2]";

	//New Links Tab
	public static final String MANAGE_LINKS_TEXT = "//span[text()='Manage Links']";
	public static final String CUSTOMERS_TAB_IN_POPUP = "(//div[@class='scs-managelink-popup-resourcelist']//following-sibling::div[@class='scs-list-view-name-container ng-binding' ])[1]";
	public static final String ITEMS_TAB_IN_POPUP = "(//div[@class='scs-managelink-popup-resourcelist']//following-sibling::div[@class='scs-list-view-name-container ng-binding' ])[3]";
	public static final String SUPPLIERS_TAB_IN_POPUP = "(//div[@class='scs-managelink-popup-resourcelist']//following-sibling::div[@class='scs-list-view-name-container ng-binding' ])[4]";
	public static final String EQUIPMENT_TAB_IN_POPUP = "(//div[@class='scs-managelink-popup-resourcelist']//following-sibling::div[@class='scs-list-view-name-container ng-binding' ])[2]";
	public static final String CUSTOMERS_CHK = "(//div[@id='scs-Customers-link-grid']/div[@class='k-grid-content k-auto-scrollable']//input)[1]";
	public static final String ITEMS_CHK = "(//div[@id='scs-Items-link-grid']/div[@class='k-grid-content k-auto-scrollable']//input)[1]";
	public static final String SUPPLIERS_CHK = "(//div[@id='scs-Suppliers-link-grid']/div[@class='k-grid-content k-auto-scrollable']//input)[1]";
	public static final String EQUIPMENT_CHK = "(//div[@id='scs-Equipment-link-grid']/div[@class='k-grid-content k-auto-scrollable']//input)[1]";
	public static final String CUSTOMERS_CHK_NAME = "//*[@id='scs-Customers-link-grid']//tr[@class='k-state-selected']/td[3]";
	public static final String ITEMS_CHK_NAME = "//*[@id='scs-Items-link-grid']//tr[@class='k-state-selected']/td[3]";
	public static final String SUPPLIERS_CHK_NAME = "//*[@id='scs-Suppliers-link-grid']//tr[@class='k-state-selected']/td[3]";
	public static final String EQUIPMENT_CHK_NAME = "//*[@id='scs-Equipment-link-grid']//tr[@class='k-state-selected']/td[3]";
	//Active Resource
	public static final String ACTIVE_RESOURCE_BTN = "//*[@id='StatusValue']/label[1]";
	

}
