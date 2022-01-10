package com.project.safetychain.webapp.pageobjects;

public class DevicesPageLocators {
	
	public static final String ADD_NEW_DEVICE_BTN = "//*[@id='scs-admin-manage-device-list']/div[contains(text(),'Add New Device')]";
	public static final String DEVICE_NAME_TXT ="//input[@formcontrolname='DeviceName']";
	public static final String ENABLE_DEVICE_BTN = "//span[contains(text(),'Enable Device')]/..//span[@class='k-switch-handle']";
	//public static final String DEVICE_TYPE_DRD = "//*[@id='scs-admin-manage-device-settings']//div//span[@role = 'listbox']";
	public static final String DEVICE_TYPE_DRD = "//div[@class='k-list-scroller']//span[@title ='Scale']";

	
	public static final String BRAND_TXT = "//*[@id='scs-admin-manage-device-settings']//input[@class='valid ng-untouched ng-pristine ng-valid k-textbox']";
	
	public static final String CONFIG_SETTINGS_TXT = "//*[@id='scs-device-details-panel']//label[contains(text(),'CONFIGSETTING')]//following-sibling::div//input";// TBD
	public static final String BAUDRATE_DRD = "//*[@id='scs-device-details-panel']//label[contains(text(),'BAUDRATE')]//following-sibling::div//input";// TBD
	public static final String DATABITS_DRD = "//*[@id='scs-device-details-panel']//label[contains(text(),'DATABITS')]//following-sibling::div//input";//TBD
	public static final String PRINT_COMMAND_TXT = "//*[@id='scs-device-details-panel']//label[contains(text(),'PRINTCOMMAND')]//following-sibling::div//input";
	
//	public static final String CONFIG_SETTINGS_DRD = "//*[@class='scs-field-panel']//label[contains(text(),'CONFIGSETTINGDRD')]//following-sibling::div/kendo-dropdownlist";
	public static final String CONFIG_SETTINGS_DRD = "//kendo-list//span[contains(text(),'CONFIGSETTINGDRD')]";
	public static final String STOPBITS_DRD = "/*[@class='scs-field-panel']//label[contains(text(),'STOPBITS')]//following-sibling::div/kendo-dropdownlist";
    public static final String PARITY_DRD = "//*[@class='scs-field-panel']//label[contains(text(),'PARITY')]//following-sibling::div/kendo-dropdownlist";
    public static final String HANDSHAKE_DRD = "//*[@class='scs-field-panel']//label[contains(text(),'HANDSHAKE')]//following-sibling::div/kendo-dropdownlist";
	
    
    //public static final String PARITY_DRD = "//div[@class='scs-device-form-field ng-star-inserted']//span[@class='k-dropdown-wrap k-state-default']//span[@class = 'k-input']";
	//public static final String STOPBITS_DRD = "//div[@class='scs-device-form-field ng-star-inserted']//span[@class='k-dropdown-wrap k-state-default']//span[@class = 'k-input']";
	//public static final String HANDSHAKE_DRD = "//div[@class='scs-device-form-field ng-star-inserted']//span[@class='k-dropdown-wrap k-state-default']//span[@class = 'k-input']";
    //public static final String BAUDRATE_DRD = "//*[@id=\"scs-device-details-panel\"]/div/div[1]/app-common-field-template//input]";// TBD
    //public static final String DATABITS_DRD = "//*[@id='scs-device-details-panel']/div/div[2]/app-common-field-template//input]";//TBD
	//public static final String PRINT_COMMAND_TXT = "//*[@id=\"scs-device-details-panel\"]/div/div//input[@class='ng-pristine ng-valid k-textbox ng-touched']";

	public static final String ADD_BTN = "//*[@id='scs-admin-manage-device-configuration-area']/div//button[contains(text(),'ADD')]";
	public static final String COPY_BTN = "//*[@id='scs-admin-manage-device-configuration-area']/div//button[contains(text(),'COPY')]";
	public static final String UPDATE_BTN = "//*[@id='scs-admin-manage-device-configuration-area']/div//button[contains(text(),'UPDATE')]";
	public static final String CLEAR_BTN = "//*[@id='scs-admin-manage-device-configuration-area']/div//button[contains(text(),'CLEAR')]";

	//public static final String DEVICE_NAME= "//*[@class='scs-list-view-content-container']//span[text()='DEVICENAME']";
	public static final String DEVICE_NAME= "//*[@class='scs-manage-device-field-row-value']//input[@formcontrolname='DeviceName']";

	// SELECTOR
	
	public static final String DEVICE_TYPE_DDL = "kendo-dropdownlist[formcontrolname='DeviceType'] > span";
	
}
