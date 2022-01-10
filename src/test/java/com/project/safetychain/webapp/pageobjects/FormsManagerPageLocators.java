package com.project.safetychain.webapp.pageobjects;


public class FormsManagerPageLocators {

	/** id */
	public static final String FTU_IMPORT_BTN = "scs-import-btn";
	public static final String FTU_VIEW_IMPORT_HIST_LNK = "scs-view-import-history";


	/** xpath */
	//Form Manager page TBD =SELECTVERIFICATION
	public static final String VERIFICATION_BTN = "//*[@id='k-tabstrip-tab-1']/span";
	public static final String SELECTVERIFICATION_BTN = "//div[@class='scs-list-view-name-container']/span[contains(text(),'VERIFICATION')]";
	public static final String LINKFORMVERIFICATION_CHK = "//*[@id='scs-forms-manager-verifications-grid']//table[@class='k-grid-table']//input";
	//MODIFIED --public static final String LINKFORMVERIFICATION = "//*[@id=\"scs-forms-manager-verifications-grid\"]/scs-kendo-grid/kendo-grid/div/kendo-grid-list//tr[1]//input";
	public static final String SAVE_BTN = "//button[contains(text(),'SAVE')]";
	public static final String SEARCHUSER_TXT = "//*[@id=\"scs-forms-manager-verifications-grid\"]/scs-kendo-grid/kendo-grid"
			+ "/div/div/div/table/thead/tr[2]/td[2]"
			+ "/div/kendo-grid-string-filter-cell/kendo-grid-filter-wrapper-cell/input";

	public static final String SEARCH_FORMNAME_INPUT = "//*[@id='scs-forms-manager-overview-grid']//td[2]//input";
	public static final String SELECT_FORM = "//span[@class='scs-overview-form-name ng-star-inserted' and contains(.,'FORMNAME')]";
	public static final String CHARTS_TAB = "//*[@class='scs-form-details-tabstrip']//li[contains(.,'Charts')]";
	public static final String FM_OVERVIEW_CHARTS_TAB = "//*[@class='scs-form-details-tabstrip']//li[contains(.,'Charts')]";


	public static final String DISABLE_BTN = "//button[contains(text(),'DISABLE')]";
	public static final String ENABLE_BTN = "//button[contains(text(),'ENABLE')]";
	public static final String DISABLE_POPUP_YES_BTN = "//button[contains(text(),'YES')]";
	public static final String ENABLE_POPUP_YES_BTN = "//button[contains(text(),'YES')]";
	public static final String COLUMN_NAMES_TBL = "//*[@id='scs-validations-grid-container']//table//th";
	public static final String COLUMN_NAMES_TXT = "//div[contains(@class, 'k-grid-content')]//table//td[COLUMNINDEXNO]";
	public static final String VLDTN_COLUMN_VALUE = "//div[contains(@class,'k-grid-content')]//td[COLUMNINDEXNO]";
	public static final String GRID_COLUMN_VALUE = "//div[contains(@class, 'k-grid-content')]//table//td[COLUMNINDEXNO]";
	public static final String GRID_COLUMNS = "//table//th[contains(@class,'k-header')]";
	public static final String ENABLE_VERIFICATION_BTN="//div[@id='k-tabstrip-tabpanel-1']/div//div[@class='scs-verification-details']/div[1]//div[@class='scs-verification-each-config']/kendo-switch/span[@role='switch']";
	public static final String LOCATION_BTN="//body/scs-main-app/scs-landing//scs-form-details[@class='ng-star-inserted']//kendo-switch/span[@role='switch']";
	public static final String SELECTlOCATION_BTN ="//span[contains(text(),'Locations')]";
	public static final String TOGGLE_LOCATION_BTN="//span[contains(text(),'Location Filtering')]/..//span[@class='k-switch-handle']";
	public static final String SELECT_LOCATION_CHK="//*[@id='k-tabstrip-tabpanel-1']//*/*[@role='presentation']/thead/tr[1]/th[1]//input[@type='checkbox']";
	public static final String SELECT_LOCATION_CHK_CHECK="//*[@id='k-tabstrip-tabpanel-3']//th//input[@type='checkbox']";
	public static final String SELECT_ALL_LOCATION_CHECK="//*[@role='presentation']/thead/tr[1]/th[1]//input[@type='checkbox']";
	public static final String LOCATIONSELECTION_TXT = "//div[@class='scs-list-view-name-container']/span[contains(text(),'LOCATION')]";
	public static final String SEARCH_FORMNAMEFORVERIFICATION_TXT = "//*[@id='scs-forms-manager-verifications-grid']//td[2]//input";
	public static final String SELECT_FORM_VERIFICATION = "//*[@id='scs-forms-manager-verifications-grid']//td[text()='FORMNAME']/..//input";
	public static final String FORM_DESIGNER_BTN = "//*[@id='scs-btn-navigate-to-form-designer']";
	public static final String CREATE_TASK_SCHEDULER_BTN = "//*[@id='scs-btn-create-task-schedule']";
	public static final String COLUMN_FILTER_TXT = "//*[@id='scs-forms-manager-overview-grid']/scs-kendo-grid/kendo-grid/div/div/div/table/thead/tr[2]/td[2]//input";
	public static final String VERIFICATION_PAGINATION_DRD = "//*[@id='scs-forms-manager-verifications-grid']/scs-kendo-grid/kendo-grid/kendo-pager/kendo-pager-page-sizes/select";
	public static final String LOCATION_TAB="//*[@id='k-tabstrip-tab-2']/span";
	public static final String LOCATION_SELECTION="//*[@id='k-tabstrip-tabpanel-3']/div/div/scs-kendo-grid/kendo-grid/div/kendo-grid-list//tr[1]/td[1]/input";
	//public static final String SEARCH_LOCATIONNAME_INPUT = "//*[@class='scs-list-view-search-container ng-star-inserted']";
	public static final String SEARCH_LOCATIONNAME_INPUT = "//div[contains(@class,'scs-list-view-search-container')]//input";
	//public static final String SEARCH_FORMNAMEFORVERIFICATION_INPUT = "//*[@id=\"scs-forms-manager-verifications-grid\"]/scs-kendo-grid/kendo-grid/div/div/div/table/thead/tr[2]/td[2]/div/kendo-grid-string-filter-cell/kendo-grid-filter-wrapper-cell/input";
	//*[@id="scs-forms-manager-verifications-grid"]/scs-kendo-grid/kendo-grid/div/div/div/table/thead/tr[2]/td[2]/div/kendo-grid-string-filter-cell/kendo-grid-filter-wrapper-cell/input

	public static final String SELECT_ALLFORMS_CHECK = "//*[@id='scs-forms-manager-verifications-grid']/scs-kendo-grid/kendo-grid/div/div/div/table/thead/tr[1]/th[1]/input";

	public static final String CLICK_NEXT_PAGE_ARROW = "//*[@id='scs-forms-manager-verifications-grid']/scs-kendo-grid/kendo-grid/kendo-pager/kendo-pager-next-buttons/a[1]/span";

	public static final String VERIFY_FORM_BTN = "//*[@id='scs-verify-form']/i";
	//*[@id="scs-verificaton-toggler"]/label[1]

	public static final String ISCOMPLIANT_TOGGLE_YES = "//*[@class='scs-toggle-btn scs-toggle-btn-compliant']";
	public static final String DIRECTOBS_USERNAME_TXT = "//*[@id='scs-verification-username']";
	public static final String VERIFICATION_PIN_TXT = "//*[@id='scs-verification-validate-pin']";

	public static final String FORM_MANAGER_VERSION_LNK="//div[@class='scs-versions-grid']/table//tr[2]/td[@class='scs-version']";
	public static final String FORM_MANAGER_VIEWCOMMENT_LNK="//div[@class='scs-versions-grid']/table//tr[2]/td[@class='scs-frm-detail-version-comment-btn']";
	////div[@class="scs-versions-grid"]/table//tr[2]/td[@class="scs-version"]

	//public static final String POPUP_OPTION_MNU = "//div[contains(@class, 'columnmenu-popup')]//div[contains(text(),'POPUPOPTION')]";

	public static final String POPUP_COLUMN_APPLY_BTN = "//div[contains(@class, 'columnmenu-popup')]//button[text()='Apply']";
	public static final String CLOSE_DIROBS_POPUP_BTN = "//span[@class='k-icon k-i-close']";
	public static final String COLUMN_SETTINGS_LNK = "//span[contains(@class, 'k-link')][text()='COLUMNNAME']/preceding-sibling::kendo-grid-column-menu/a";
	public static final String POPUP_OPTION_MNU = "//div[contains(@class, 'columnmenu-popup')]//div[contains(text(),'POPUPOPTION')]";

	public static final String POPUP_COLUMN_SET_OPTN = "//div[contains(@class, 'k-columnmenu-item-content')]//span[text()='COLUMNNAME']/preceding-sibling::input";

	//public static final String POPUP_COLUMN_APPLY_BTN = "//div[contains(@class, 'columnmenu-popup')]//button[text()='Apply']";
	public static final String DROPDWN_OPTN = "//li[contains(.,'DROPDOWNOPTION')]";
	public static final String FM_DROPDOWN_COLUMN_VAL = "//div[contains(@class, 'scs-kendo-dropdownlist-filter')]//span[span[text()='Select item...']]/span[1]";
	public static final String FM_COLUMN_VALUE = "//div[contains(@class, 'k-grid-content')]//table//td[COLUMNINDEXNO]";
	public static final String FM_OVERVIEW_SCROLL_HR = "//*[@id='scs-forms-manager-overview-grid']/scs-kendo-grid/kendo-grid/div/kendo-grid-list/div";

	public static final String CLEAR_GRID_FILTER_BTN = "//button[contains(@class,'k-clear-button-visible')]";

	public static final String CLEAR_GRID_OVERVIEW_LOCATION_FILTER_BTN  = "//button[contains(@class,'k-clear-button-visible')]";
	public static final String COLUMN_FM_FORMNAME_TXT = "//*[@id='scs-forms-manager-verifications-grid']//tr[contains(@class,'k-filter-row')]//td[2]//input";
	public static final String CLEAR_COLUMN_FILTER_BTN = "//*[@id='scs-forms-manager-verifications-grid']//td[2]//button[contains(@class,'k-clear-button-visible')]";
	public static final String COLUMN_FM_FORMNAME_LOCATION_TXT = "//*[@id='scs-forms-manager-locations-grid']//tr[contains(@class,'k-filter-row')]//td[2]//input";

	public static final String CLEAR_COLUMN_FILTER_LOCATION_BTN = "//*[@id='scs-forms-manager-locations-grid']//td[2]//button[contains(@class,'k-clear-button-visible')]";
	public static final String FM_OVERVIEW_FORM_LOCATION_TXT = "//*[@class ='k-filtercell-wrapper']/input";

	public static final String FM_OVERVIEW_FORM_CHECKBOX = "//*[@id='scs-forms-manager-overview-grid']//table[@class='k-grid-table']//input";
	public static final String FM_OVERVIEW_SEARCH_FORM_TXT = "(//*[@class ='k-filtercell-wrapper']/input[contains(@class, 'k-textbox ng-untouched ng-pristine ng-valid')])[1]";
	public static final String FM_OVERVIEW_CLEAR_COLUMN_FILTER_BTN = "//*[@id='scs-forms-manager-overview-grid']//td[2]//button[contains(@class,'k-clear-button-visible')]";
	public static final String FM_OVERVIEW_STATE_CALLOUT_MNU = "//*[@id='scs-btn-options-callout']";
	public static final String FM_OVERVIEW_FORM_STATE_DISABLED= "//li[contains(@class, 'scs-callout-menu-item ng-star-inserted')]//span[contains(text(),'Disable')]";
	public static final String FM_OVERVIEW_FORM_STATE_ENABLED ="//li[contains(@class, 'scs-callout-menu-item ng-star-inserted')]//span[contains(text(),'Enable')]";
	public static final String FM_OVERVIEW_FORM_POPUP_ENABLED = "//button[contains(text(),'ENABLE')]";
	public static final String FM_OVERVIEW_FORM_POPUP_DISABLED = "//button[contains(text(),'DISABLE')]";
	public static final String FM_OVERVIEW_SEARCHFORM_TOUCHED_TXT = "//*[@class ='k-filtercell-wrapper']/input[contains(@class, 'k-textbox ng-pristine ng-valid ng-touched')]";
	public static final String FM_OVERVIEW_SELECTBULKFORM_CHECKBOX = "//*[@id='scs-forms-manager-overview-grid']/scs-kendo-grid/kendo-grid//thead/tr[1]/th[1]/input";
	//charts

	public static final String ASSOCIATE_BTN= "//*[@id='scs-associate-chart-btn']";
	public static final String CHARTNAME_INPUT = "//*[@class='scs-popup-field-element']//input";

	public static final String ASSOCIATE_NEW_CHART_SAVE_BTN = "//button[contains(.,'SAVE')]";
	public static final String FM_CHART_CLOSE = "//button[contains(text(),'Close')]";
	public static final String FM_CHART_POPUP_OK_BUTTON ="//button[contains(text(),'Ok')]";
	public static final String FM_CHART_POPUP_CLEAR_BUTTON ="//button[contains(text(),'CLEAR')]";
	public static final String FM_CHART_DELETE_CHART_BUTTON = "//button[contains(text(),'DELETE CHART')]";

	public static final String FM_CHART_RESOURCE_LOCATION = "//span[@role='listbox']";

	public static final String FM_OVERVIEW_FORM_CLOSE_BUTTON = "//*[@class='scs-form-details-header-buttons-container']/button[contains(text(),'Close')]";
	public static final String VALUE_SELECT = "//li[contains(.,'VALUE')]";
	public static final String FM_CHART_RESOURCE_LOCATION_DDL = "//*[@class='k-input']";
	public static final String FM_CHARTS_FIELD_DDL = "//span[@class='k-icon k-i-arrow-s']";
	public static final String FM_CHARTS_FIELD_VALUE = "//div[@class='k-list-scroller']//span[text()='VALUE']";
	public static final String FM_FIELD_VALUE_SAVE = "//*[@id='scs-update-chart']";

	public static final String FM_CHART_FIELD_CHECKBOX = "//*[@class='scs-charts-search-wrapper']/..//input[@type='checkbox']";
	//*[@id="scs-frm-mgr-chart-delete"]
	public static final String FM_CHART_FIELD_CLICK_OK_POPUP = "//button[contains(text(),'Ok')]";

	public static final String FM_CHART_FIELD_CLICK_CLEAR_BUTTON ="//*[@id='scs-clear-chart']";

	public static final String FM_FIELD_CLOSE_BUTTON = "//button[@class='scs-form-details-btn']";
	public static final String FM_CHART_MEAN_UP_ARROW = "//*[@id='scs-forms-manager-resources-grid']//div[contains(@class,'k-grid-content')]//td[contains(text(), 'RESOURCE_NAME')]/..//td[5]//span[@class=\"k-link k-link-increase\"]";
	public static final String FM_CHART_VARIANCE_UP_ARROW = "//*[@id='scs-forms-manager-resources-grid']//div[contains(@class,'k-grid-content')]//td[contains(text(), 'RESOURCE_NAME')]/..//td[6]//span[@class=\"k-link k-link-increase\"]";
	public static final String FM_CHART_SELECT_RESOURCE = "//*[@id='scs-forms-manager-resources-grid']//div[contains(@class,'k-grid-content')]//td[contains(text(), 'RESOURCE_NAME')]/..//td[1]//input[@type=\"checkbox\"]";
	public static final String FM_RESOURCETAB_SAVE_BTN = "//*[@id='scs-charts-tab-buttons-panel']/div/button[1]";
	public static final String FM_RESOURCETAB_FORM_CLOSE_BUTTON = "//button[@class='scs-form-details-btn']";
	public static final String FM_CHART_RESOURCES_DEFAULT_MEAN_UP_ARROW = "//*[@id='scs-forms-manager-resources-grid']//th[@aria-colindex ='5']/.//span[@class='k-link k-link-increase']";
	public static final String FM_CHART_RESOURCES_DEFAULT_VARIANCE_UP_ARROW = "//*[@id='scs-forms-manager-resources-grid']//th[@aria-colindex ='6']/.//span[@class='k-link k-link-increase']";
	public static final String FM_CHART_RESOURCES_DEFAULT_CHECKBOX = "//*[@id='scs-forms-manager-resources-grid']//div/..//thead/tr[1]/th[contains(text(), 'Default')]/.//input[@type='checkbox']";
	public static final String FM_CHART_RESOURCES_DEFAULTCHECKPOPUP = "//div[@class='k-content k-window-content k-dialog-content']/..//button[contains(text(),'OK')]";
	public static final String FM_OVERVIEW_VERIFICATION_TAB = "//*[@class='scs-form-details-tabstrip']//li[contains(.,'Verifications')]";
	public static final String FM_OVERVIEW_VERIFICATION_NAME = "//div[contains(@class,'scs-list-view-title-container')]//span[text()='VERIFICATION_NAME']";
	public static final String FM_OVERVIEW_VERSION_LST = "//div[@class='scs-versions-grid']/table//tr/td[@class='scs-version']";
	public static final String FM_OVERVIEW_VERSION_CMMNT_CONTAINER = "//div[contains(@class, 'scs-version-comment-container')]";
	public static final String LOCATION_TAB_LOC_NAME_LST = "//div[@class='scs-list-view-name-container']//span";

	// FTU
	public static final String FM_IMPORT_TAB = "//*[contains(@id,'k-tabstrip-tab')]//span[contains(.,'Import')]";
	public static final String IMPORT_SOURCE_ENV_OPTN = "//div[@class='k-list-scroller']//span[text()='SOURCE_ENV']";
	public static final String FTU_COLUMN_NAMES_TBL = "//*[@id='scs-import-grid-container']//table//th";
	public static final String FTU_COLUMN_NAMES_TXT = "//*[@id='scs-import-grid-container']//tr[contains(@class,'k-filter-row')]//td[COLUMNINDEXNO]//input";
	public static final String FTU_FIRST_FORM_CHK = "//*[@id='scs-import-grid-container']//div[contains(@class,'k-grid-content')]//td[1]//input";
	public static final String FTU_IMPORT_OPTN = "//*[@id='scs-import-config-container']//label[text()='IMPORT_OPTION']/preceding-sibling::input";
	public static final String FTU_POPUP_IMPORT_BTN = "//kendo-dialog-actions//button[text()='IMPORT']";
	public static final String FTU_POPUP_CLOSE_BTN = "//kendo-dialog-actions//button[text()='CLOSE']";
	public static final String FTU_VIEW_FIRST_HIST_DETS = "//div[contains(@class,'scs-list-view-title-container')][1]";
	public static final String FTU_HIST_ENV_DETS = "//div[contains(@class,'scs-import-history-details')]/div[contains(@class, 'history-environment-details')]";
	public static final String FTU_HIST_FORM_DETS = "//div[contains(@class,'scs-import-history-details')]//div[contains(@class, 'history-form-container')]";
	public static final String FTU_HIST_RESOURCE_DETS = "//div[contains(@class,'scs-import-history-details')]//div[contains(@class, 'history-resource-container')]";


}