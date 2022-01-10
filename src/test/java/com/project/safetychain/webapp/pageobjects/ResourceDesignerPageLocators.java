package com.project.safetychain.webapp.pageobjects;

public class ResourceDesignerPageLocators {

	//Resource Category
	public static final String LOCATION_CATEGORY_TAB = "//*[@id='scs-manage-resource-list-view-data']/div/div/div/div[normalize-space(.)='Locations']";
	public static final String CUSTOMER_CATEGORY_TAB  = "//*[@id='scs-manage-resource-list-view-data']/div/div/div/div[normalize-space(.)='Customers']";
	public static final String EQUIPMENT_CATEGORY_TAB  = "//*[@id='scs-manage-resource-list-view-data']/div/div/div/div[normalize-space(.)='Equipment']";
	public static final String ITEM_CATEGORY_TAB  = "//*[@id='scs-manage-resource-list-view-data']/div/div/div/div[normalize-space(.)='Items']";
	public static final String SUPPLIER_CATEGORY_TAB  = "//*[@id='scs-manage-resource-list-view-data']/div/div/div/div[normalize-space(.)='Suppliers']";
	
	//Resource Property/Creation
	public static final String CATEGORIES_TAB = "//span[contains(text(),'Categories')]";
	public static final String CATEGORIES_ELEMENT_TAB = "//li[@data-hash='categories']";
	public static final String CATEGORY_HEADING_TRE = "//span[@class='scs-category-text ng-binding']";
	public static final String ADD_CATEGORY_TRE = "//span[span[@class='scs-category-text ng-binding']]/i[@class='fa fa-plus-square ng-scope']";
	public static final String SET_CATEGORY_NAME_TXT = "//*[@id='{{settings.treeId}}_tv_active']//input";
	public static final String SAVE_CATEGORY_BTN = "//*[@id='scs-details-save-btn']";

	public static final String VERIFY_CREATED_CATEGORY= "//div[@class='scs-treeview-item ng-scope']//span[text()='CATEGORY_NAME']"; 
	
	//select category from category tree (dynamic)
	public static final String CATEGORY_FRM_TREE = "//*[text()='CATEGORY_NAME']";
	public static final String ADD_EDIT_DEL_MENU = "//span[contains(.,'CATEGORY_NAME')]//i";
	
	public static final String ADD_CATEGORY_BTN = "//span[text()='Add Category']";
	
}
