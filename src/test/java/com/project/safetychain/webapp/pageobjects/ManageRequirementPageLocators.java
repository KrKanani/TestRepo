package com.project.safetychain.webapp.pageobjects;

public class ManageRequirementPageLocators {


	public static final String PLUS_BTN = "//list-view[@id='scs-pp-list-view-data']//i[@class='fa fa-plus-circle']";
	public static final String TEMP_INPUT = "//*[@id='scs-pp-list-view-data']//div/input";
	public static final String REQ_DRD = "//*[@class='k-icon  k-i-arrow-60-down']";
	public static final String ACK_SELECT = "//*[@id='scs-product-dropdown_mn_active']//span[contains(text(),'Acknowledgement')]";
	public static final String DOCTYPE_DRD = "//*[@class='scs-pp-acknowledgement-step-1 ng-scope']//span[@class='k-icon k-i-arrow-60-down']";
	public static final String DOC_SELECT = "//*[@id='scs-pp-acknowledgement-step-1-grid-container']//tr[1]/td[1]/label";
	public static final String NEXT_BTN = "//*[@id='scs-steps-buttons-holder']//span[contains(text(),'NEXT')]";
	public static final String TASKNAME_INPUT = "//*[@class='scs-add-requirement-field-container']/div[contains(text(),'Task Name')]//following-sibling::input";
	public static final String DUEIN_INPUT = "//*[@class='scs-add-requirement-stepper-container']//input";
	public static final String DUE_INPUT = "//*[@class='scs-add-requirement-stepper-container']//input[@id='scs-add-requirement-stepper']";
	public static final String SAVE_BTN = "//*[@id='scs-steps-buttons-holder']//span[contains(text(),'SAVE')]";	
	public static final String DOCUPLOAD_SELECT = "//*[@id='scs-product-dropdown_mn_active']//span[contains(text(),'Document Upload')]";
	public static final String SEARCH_INPUT = "//*[@class='scs-pp-acknowledgement-step-1-search-container']//input";
	public static final String COMPLETE_FRM_SELECT = "//*[@id='scs-product-dropdown_mn_active']//span[contains(text(),'Complete Form')]";

	public static final String SUPPLIER_TAB = "//*[@id='SupplierItemsGrid']//ancestor::span[contains(text(),'Suppliers')]";	
	public static final String ITEMS_TAB = "//*[@id='SupplierItemsGrid']//ancestor::span[contains(text(),'Items')]";
	public static final String REQUIREMENTS_TAB = "//*[@class='scs-admintools-right-panel scs-pp-right-panel-container']//ancestor::span[contains(text(),'Requirements')]";
	public static final String SELECT_NEW_BUTTON = "//*[@id='scs-partner-portal']//button[contains(text(),'SELECT NEW')]";	
	public static final String NAME_ARROW = "//*[@class='scs-pp-add-supplier-container']//i";
	public static final String FILTER ="//*[@class='k-column-menu k-popup k-group k-reset k-state-border-up']//span[contains(text(),'Filter')]";
	public static final String FILTER_INPUT= "//*[@class='k-animation-container']//input[@type='text']";
	public static final String FILTER_BTN = "//button[contains(text(),'Filter')]";
	public static final String INSTANCE_SELECT = "//input[@type = 'checkbox' and @class='scs-grid-row-checkbox']";
	public static final String SAVEINST_BTN = "//div[@class='ui-dialog-buttonset']/button[text()='SAVE']";
	public static final String RESOURCE_SELECT = "//*[@class='scs-pp-supplier-item-dropdown']//span[@class='k-icon k-i-arrow-60-down']";
	public static final String SELECT_ITEMS = "//*[@class='k-animation-container']//li[contains(text(),'Items')]";
	public static final String SELECT_SUPPLIER = "//*[@class='k-animation-container']//li[contains(text(),'Suppliers')]";

	public static final String INSTANCE_SELECTION_NEXT_ICON = "//a[@aria-label='Go to the next page']";
	public static final String INSTANCE_AVAILABLE = "//div[@id='scs-add-supplier-grid-container']/div//tr/td[text()='INSTANCE_NAME']";
	public static final String MINSTANCE_ELEMENT = "//div[@id='scs-add-supplier-grid-container']/div[2]//tr/td[2]";
	public static final String INSTANCE_ELEMENT_INP = "//div[@id='scs-add-supplier-grid-container']/div[2]//td/input";
	public static final String MFIRST_INSTANCE = "//*[@id='scs-add-supplier-grid-container']/div[2]//tr[1]/td[2]";

	public static final String SINSTANCE_ELEMENT = "//div[@id='scs-add-supplier-grid-container']/div[3]//tr/td[1]";
	public static final String SFIRST_INSTANCE = "//*[@id='scs-add-supplier-grid-container']/div[3]//tr[1]/td[1]";

	public static final String MULTIPLE_FIELDS_CHECK = "//*[@id='scs-add-supplier-grid-container']//div[@class='k-grid-header-locked']//th[last() and @data-field='SCSName']";

	public static final String TEMPLATE_SELECT = "//div[@class='scs-list-view-name-container ng-binding'][contains(.,'TEMPLATE_NAME')]";
	public static final String DISABLE_TEMP_SELECT = "//div[@class='scs-list-view-name-container ng-binding scs-list-view-title-disabled'][contains(.,'TEMPLATE_NAME')]";
	public static final String REQ_GRD = "//*[@class='k-grid-content k-auto-scrollable']//td[2]";
	public static final String ENABLE_DISABLE_BTN = "//*[@class='scs-list-view-ban-image enable-disable-icon-wrapper']/i";
	public static final String VERIFY_DISABLE_TEMPLATE = "//div[contains(.,'TEMPLATE_NAME')]/i[@class='fa fa-ban scs-list-view-ban-image-disabled ng-scope']";
	public static final String YES_BTN = "//button[contains(.,'YES')]";	 
	public static final String COPY_BTN = "//*[@class='fa fa-files-o scs-list-view-copy-image']";
	public static final String SVE_BTN = "//button[contains(.,'SAVE')]";

	public static final String TEMP_SEARCH_INPUT = "//*[@id='scs-list-view-search-input']";
	public static final String TEMPLATE_LST = "//*[@id='scs-list-view-search-input_listbox']/li[contains(.,'TEMPLATE')]";
	public static final String REQ_SELECT = "//*[@id='scs-manage-requirements-grid-container']//tr[td[contains(.,'REQ_NAME')]]";
	public static final String DELETE_BTN = "//*[@id='scs-manage-requirements-grid-container']//tr[td[contains(.,'ACK_TASK')]]/td//span[@class='fa fa-trash-o scs-pp-grid-font']";
	public static final String EDIT_BTN = "//*[@id='scs-manage-requirements-grid-container']//tr[td[contains(.,'FORM_TASK')]]/td//span[@class='k-grid-edit custom-pp-grid-option']";
	public static final String VERIFY_EDIT = "//*[@id='scs-manage-requirements-grid-container']//tr[td[contains(.,'FORM_TASK')]]/td/span[contains(.,'2')]";
	public static final String INCREASE_DUEIN = "//*[@class='k-widget k-numerictextbox']//span[@title='Increase value']/span";
	public static final String CLOSE_COMPLETEFORM_POPUP= "/html/body/div[8]/div[1]/i";

	public static final String COMPLETEFORM  ="//*[@id='scs-pp-acknowledgement-step-1-grid-container']//tr[1]/td[text()='FORMNAME']";

	public static final String GRID_HEADERS = "//div[contains(@class,'k-state-active')]//table//a[contains(@class,'k-link')]";
	public static final String COLUMN_LST = "//div[contains(@class,'k-state-active')]//td[COLUMN_INDEX]";
	public static final String COLUMN_LST1 = "//div[contains(@class,'k-state-active')]//td[COLUMN_INDEX]/span";

	public static final String RESOURCE_SELECTION_INPUT = "//*[@id='scs-add-supplier-grid-container']//tr[1]/td[1]/input";
	public static final String DATE_PICKER = "//*[@id='scs-supplier-date-picker']";
	public static final String RESOURCE_NAME = "//*[@id='scs-add-supplier-grid-container']/div[2]/table/tbody/tr/td[2]";
	public static final String RESOURCE_NAME1 = "//*[@id=\"scs-add-supplier-grid-container\"]/div[3]/table/tbody/tr[1]/td";
	public static final String RESOURCE_ADDED = "//*[@class='k-grid-content k-auto-scrollable']//tr/td[contains(.,'RESOURCE')]";
	public static final String EFFECTIVE_ON_COL = "//*[@class='k-grid-content k-auto-scrollable']//tr[td[contains(.,'RESOURCE')]]/td[2]";
	public static final String ADDED_ON_COL = "//*[@class='k-grid-content k-auto-scrollable']//tr[td[contains(.,'RESOURCE')]]/td[3]";
	public static final String SELECT_ALL_INPUT = "//*[@id='scs-grid-select-all']";
	public static final String RESOURCE_NAME_HEADING = "//*[@id='scs-add-supplier-grid-container']//th[@data-field='SCSName']";
	public static final String RESOURCE_LST = "//*[@class='k-grid-content-locked']//td[2]";
	public static final String CLOSE_BTN = "//i[@class='fa fa-times-circle scs-popup-close']";
	
	public static final String SUPPLIER_BTN = "//*[@id='SupplierItemsGrid']/span[contains(.,'Suppliers')]";
	public static final String DELETE_INSTANCE_BTN = "//*[@id='scs-manage-supplier-grid-container']//tr[contains(.,'INSTANCE_NAME')]//a[contains(@class,'delete')]/span";
	//public static final String DELETE_INSTANCE_BTN = "//*[@id='scs-manage-supplier-grid-container']/div[2]/div[1]/table/tbody/tr/td[5]/a/span";
	public static final String INSTANCE_NAME = "//*[@id='scs-manage-supplier-grid-container']//tr[contains(.,'INSTANCE_NAME')]";
	public static final String YES_POPUP_BTN = "//div[@class='ui-dialog-buttonset']/button[contains(.,'YES')]";
	
}

