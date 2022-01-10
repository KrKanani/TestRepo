package com.project.safetychain.webapp.pageobjects;

public class FormDesignerPageLocator {


	//Form types
	public static final String SELECT_AUDIT_FORM_LNK = "//*[@id='scs-audit-form-icon']";
	public static final String SELECT_CHECK_FORM_LNK = "//*[@id='scs-check-form-icon']";
	public static final String SELECT_QUESTIONNAIRE_FORM_LNK = "//*[@id='scs-questionnaire-form-icon']";

	public static final String DRAG_RES = "//div[@id='scs-left-panel-treeview']//span[text()='RESOURCE_CATEGORY']";
	public static final String EXPAND_RESOURCE_CAT = "//*[@id='scs-left-panel-treeview']//span[text()='INSTANCENAME']/preceding-sibling::span[contains(@class,'expand')]";
	public static final String DROP_RES = "//*[@id='scs-formdesigner-select-resource-grid']";

	public static final String VERIFY_RESOURCE_LBL = "//div[@id='scs-formdesigner-select-resource-grid']//div[text()='RESOURCE_NAME']";
	public static final String RESOURCE_CAT_LBL = "//div[@id='scs-formdesigner-select-resource-grid']//div[contains(text(),'RESOURCE_NAME')]";
	public static final String NEXT_FORM_DESIGNER_LNK = "//*[@id='scs-form-next-icon']";

	public static final String SAVE_FORM_BUTTON = "//button[@id=\"scs-form-design-save\"]";
	public static final String OK_SAVE_FORM_BUTTON = "//button[contains(text(),'OK')]";

	public static final String FORM_DESIGNER_EXIT_BTN = "//div[@class='scs-nav-exit']";
	public static final String DELETE_SAVE_FORM_YES_BTN = "//div[@class='ui-dialog-buttonset']//button[text()='YES']";

	//Design Form
	public static final String DESIGN_FORM_PG = "//*[@id='breadCrumb']//a[text()='Design Form']";
	public static final String SET_FORM_NAME_TXT =  "//*[@id='field-Name-0']//input";
	public static final String SAVE_FORM_BTN = "//button[@id='scs-form-design-save']";
	public static final String SAVE_FORM_OK_BTN = "//div[@class='ui-dialog-buttonset']/button";
	public static final String DEFAULTCHK = "//*[@class='scs-default-row']//input[@title='Apply Default to empty Compliances']";
	public static final String MINIMUM_TXT = "//*[@class='scs-default-row']//input[@ng-model='formData[currentFieldID].DefaultCompliance.Min']";
	public static final String TARGET_TXT = "//*[@class='scs-default-row']//input[@ng-model='formData[currentFieldID].DefaultCompliance.Target']";
	public static final String MAXIMUN_TXT = "//*[@class='scs-default-row']//input[@ng-model='formData[currentFieldID].DefaultCompliance.Max']";
	public static final String UOM_TXT = "//*[@class='scs-default-row']//input[@ng-model='formData[currentFieldID].DefaultCompliance.UOM']";

	//From Elements
	public static final String NOTE_DBL = "//li//span[text()='Note']";
	public static final String RELATED_DOCS_DBL = "//li//span[text()='Related Docs']";
	public static final String SECTION_DBL = "//*[@id='scs-designform-elem-option-contents']//span[text()='Section']";
	public static final String GROUP_DBL = "//*[@id='scs-designform-elem-option-contents']//span[text()='Question Group' or text()='Field Group']";
	public static final String SUMMARY_DBL = "//*[@id='scs-designform-elem-option-contents']//span[text()='Summary']";
	public static final String AGGRGATE_DBL = "//*[@id='scs-designform-elem-option-contents']//span[text()='Aggregate']";
	public static final String IDENTFIER_DBL = "//*[@id='scs-designform-elem-option-contents']//span[text()='Identifier']";	
	public static final String FORM_ELEMENT = "//ol[@id=\"scs-designform-elem-option-contents\"]//span[contains(text(),\"FORMELEMENT\")]/../..";

	//Header Level
	public static final String FORM_NAME_LBL = "//div[@id=\"scs-form-name\"]";
	public static final String HEADER_LEVEL = "//*[@id='scs-scrollable-activity-area']/div[1]";
	public static final String NOTE_TXT = "//*[@id='field-Note-0']//textarea";
	public static final String ADD_NEW_DOCUMENT_BTN = "//*[@id='RelatedDocuments-field-RelatedDocs-0']";
	public static final String DOCUMENT_TYPE_DDL = "//*[@id='popup-dropdown-container']/span/span/span[2]";
	public static final String SELECT_DOCUMENT_TYPE = "//div[@aria-describedby='scs-popup']//following::div//li[text()='DOCUMENT_TYPE']";
	public static final String SELECT_DOCUMENT = "//tr[td[span[contains(text(),'DOCUMENT_NAME')]]]/td[1]/input";
	public static final String SELECT_DOCUMENT_VERIFY = "//*[@id='scs-related-documents-list']/related-docs//span[text()='DOCUMENT_NAME']";
	public static final String SAVE_DOCUMENT_BTN = "//div[@class='ui-dialog-buttonset']/button[text()='SAVE']";
	public static final String DELETE_RELTD_DOCS_BTN = "//*[contains(@id,'RelatedDocs')]//i[@class='fa fa-trash-o']";


	//Field Types
	public static final String NUMERIC_DBL = "//*[@id='scs-designform-left-panel-fields-contents']//span[contains(text(),'Numeric')]";
	public static final String FIELD_TYPE_NAME_TXT = "//span[contains(text(),\"FIELDTYPENAME\")]/../..//span[contains(@class,\"ng-empty\")]/input";
	public static final String FIELD_TYPE_DROP_AREA_FORM_LEVEL = "//div[@id=\"scs-scrollable-activity-area\"]//div[contains(text(),\"Form Level\")]/../div/ol/li[contains(@class,\"dd-item\")]";	
	public static final String FIELD_TYPE = "//ol[@id=\"scs-designform-left-panel-fields-contents\"]//span[contains(text(),\"FIELDTYPE\")]/../..";
	public static final String FIELD_TYPE_DROP_AREA_SECTION_GROUP_LEVEL = "//div[contains(text(),\"SECTIONGROUPNAME\")]/../ol/li[contains(@class,\"dd-item\")]";
	public static final String FIELD_TYPE_NAME_TEXT_AREA = "//span[contains(text(),\"FIELDTYPENAME\")]/../..//textarea[contains(@class,\"ng-empty\")]";
	public static final String UPDATE_FIELD_TYPE_NAME_TXT = "//span[contains(text(),\"FIELDTYPENAME\")]/../..//span[contains(@class,\"ng-not-empty\")]/input";
	public static final String FREETEXT_DBL = "//*[@id='scs-designform-left-panel-fields-contents']//span[contains(text(),'Free Text')]";

	//Field Inputs
	public static final String FORM_LEVEL = "//*[@id='scs-scrollable-activity-area']/div[2]";
	public static final String SECTION1_LEVEL = "//*[@id='field-Section-0']/div[2]";
	public static final String FORM_DESIGNER_FIELD_INP = "//*[@ng-model='formInputMapping[elem.id]' and contains(@class,'ng-empty')]";
	public static final String SECTION_FIELD1_TXT = "//*[@id='field-Section-0']//input";
	public static final String NUMERIC_FIELD_TXT = "//*[@id='field-Numeric-0']//input";
	public static final String NUMERIC_FIELD_TXA = "//*[@id='field-Numeric-0']//textarea";
	public static final String FREETEXT_FIELD_TXT = "//*[@id='field-FreeText-0']//input";
	public static final String FREETEXT_FIELD_TXA = "//*[@id='field-FreeText-0']//textarea";


	//Element Properties
	public static final String FIELD_SETTINGS_TAB = "//div[text()='Settings']";
	public static final String ALLOW_COMMENTS_ON_BTN = "//div[@id='AllowComments']/label[1]";
	public static final String SHOW_HINT_ON_BTN = "//div[@id='ShowHint']/label[1]";
	public static final String HINT_TXA = "//div[@id='Hint']/textarea";
	public static final String ALLOW_ATTACHMENT_ON_BTN = "//div[@id='AllowAttachments']/label[1]";
	public static final String ALLOW_CORRECTION_ON_BTN = "//div[@id='AllowCorrection']/label[1]";
	public static final String ALLOW_CORRECTION_OFF_BTN = "//div[@id='AllowCorrection']/label[contains(.,'Off')]";
	public static final String MARK_RESOLVED_ON_BTN = "//div[@id='ShowIsResolved']/label[1]";
	public static final String ADD_NEW_PREDEINED_CORRECTION_BTN = "//div[@id='PredefinedCorrections']/button";
	public static final String PREDEINED_CORRECTION_OPTION_INP = "//div[@class='predefined-field']/input[contains(@class,'ng-empty')]";
	public static final String CARRYOVSER_FIELD_ON_BTN = "//div[@id='RepeatField']/label[1]";
	public static final String ADD_NEW_VALUE_BUTTON = "//div[@id=\"scs-designform-form-content-prop-area\"]//button[contains(text(),\"Add New\")]";
	public static final String VALUE_TXT = "//div[@id=\"scs-designform-form-content-prop-area\"]//div[contains(@class,\"value-field\")]/input[contains(@class,\"ng-empty\")]";
	public static final String INDENTIFIER_INPUT_TYPE = "//*[@id='InputType']//span[@class='k-icon k-i-arrow-60-down']";
	public static final String SELECT_INDENTIFIER_INPUT_TYPE = "//*[@class='k-animation-container']//li[text()='INPUTTYPE']";
	public static final String INDENTIFIER_INPUT_MASK = "//div[@id=\"scs-input-mask\"]/input";
	public static final String IDENTIFIER_ADD_NEW_BTN = "//*[@id='scs-designform-form-content-prop-area']//button[contains(.,'Add New')]";
	public static final String IDENTIFIER_SELECT_ONE_INPUT = "//*[@class='value-field']/input";
	public static final String QUESTION_WEIGHT_INP = "//div[@id='QuestionWeight']/*[@placeholder='Weight']";
	public static final String ADD_NEW_OPTION_BTN =  "//div[@class='add-button-container']/button";
	public static final String OPTION_INP = "//div[@class='value-field']/input[contains(@class,'ng-empty')]";
	public static final String OPTION_WEIGHT_INP = "//div[contains(@class,'valueWeight')]/input[contains(@class,'ng-empty')]";
	public static final String SHORT_NAME_LBL = "//label[text()='Short Name']";
	public static final String CURRENT_FIELD_LBL = "//div[contains(@class,'activeDroppedElement')]//div[contains(@class,'scs-droped-leftbox')]";
	public static final String AGGREGATE_FUNCTION = "//div[@id=\"Function\"]//span[@class=\"k-icon k-i-arrow-60-down\"]";
	public static final String SELECT_AGGREGATE_FUNCTION = "//ul/li[text()=\"AGGREGATEFUNCTION\"]";
	public static final String SET_AGGREGATE_SOURCE = "//div[@id=\"Source\"]//span/span/input";
	public static final String GENERAL_TAB = "//*[@id='scs-designform-form-content-prop-conteiner']//div[./text()='General']";
	public static final String FIELD_REPEAT_INP = "//div[@id='Repeat']/input";
	public static final String FIELD_NO_REPEAT_TXT = "//div[div[@id='Repeat']]/div[@id='scs-no-repeat-note']/i";
	public static final String ADVANCED_TAB = "//*[@id='scs-designform-form-content-prop-conteiner']//div[./text()='Advanced']";
	public static final String EXPRESSIONS_ADD_NEW_BTN = "//button[contains(@class,'scs-add-new-rule')][@data-add-rule-for='ExpressionRule']";
	public static final String SHOW_TARGET_BTN = "//div[@id='ShowTarget']/label[1]";
	public static final String DELETE_FIELD_ICON = "//div[contains(text(),\"SHORT_NAME\")]/..//i[contains(@class,\"trash\")]";
	public static final String OK_BUTTON = "//button[contains(text(),\"OK\")]";
	public static final String FIELD_CANNOT_BE_DELETED_POPUP_LABEL = "//div[@id=\"scs-popup\"]//div[contains(text(),\"Field is used in rule. Cannot be deleted.\")]";
	public static final String SELECT_AGGREGATE_SOURCE_CB = "//div[@id=\"scs-agg-sourcelist-container\"]//label[contains(text(),\"SOURCENAME\")]/input";
	public static final String SET_AGGREGATE_MAX_TXT = "//*[@id=\"scs-agg-func-parameters\"]//label[contains(text(),\"Max\")]/..//input[contains(@class,\"k-formatted-value\")]";
	public static final String SET_AGGREGATE_MIN_TXT = "//*[@id=\"scs-agg-func-parameters\"]//label[contains(text(),\"Min\")]/..//input[contains(@class,\"k-formatted-value\")]";
	public static final String GET_STATE_OF_TOGGLE_BTN = "//label[text()='BUTTON_NAME']/../div//label[@class='scs-toggle-btn selected']";
	public static final String GENERAL_TAB_INPUT_FIELDS_TXT = "//div[@id=\"scs-designform-form-content-prop-area\"]//label[text()=\"FIELD_NAME\"]/..//input";
	public static final String FLD_SETTING_OFF_LBL = "//*[@id='scs-designform-form-content-prop-area']//label[contains(text(),'FIELDSETTING')]/../div//label[contains(.,'Off')]";
	public static final String FLD_SETTING_ON_LBL = "//*[@id='scs-designform-form-content-prop-area']//label[contains(text(),'FIELDSETTING')]/../div//label[contains(.,'On')]";


	//Expression Builder
	public static final String IF_TXT = "//*[@id='scs-rule-builder-container']//div[contains(@class,'ifElse ng-scope')]//input";//div[@droppable]//input[contains(@class,'scs-typeahead-box') and @title]
	public static final String EXPRESSIONS_DD = "//*[@id='scs-rule-builder-container']//select[contains(@class,'scs-rb-dropdown-date-time')]";
	public static final String TIME_DD = "//*[@id='scs-rule-builder-container']//span[contains(@class,'timepicker-value')]//span[contains(@aria-label,'select')]/span";
	public static final String TIME_DDList = "//div[contains(@class,'k-list-container k-list-scroller')]/ul/li";
	public static final String IF_ELEMENTS_VALUE = "//*[@id='scs-rule-builder-container']//div[contains(@class,'expressionsResultInput ng-scope')]//input[@ng-model='expression.value']";
	public static final String THEN_DROPDOWN_ARROW = "//*[@id='scs-rule-builder-container']//div[text()='THEN']/ancestor::div[1]//span[@aria-label='select']/span";
	public static final String THEN_DROPDOWN_TXT = "//*[@id='scs-rule-builder-container']//div[text()='THEN']/ancestor::div[1]//div[contains(@class,'expressionsResultInput')]//span[contains(@class,\"dropdown\")]/input";
	public static final String ELSE_DROPDOWN_ARROW = "//*[@id='scs-rule-builder-container']//div[text()='ELSE']/ancestor::div[1]//span[@aria-label='select']/span";
	public static final String ELSE_DROPDOWN_TXT = "//*[@id='scs-rule-builder-container']//div[text()='ELSE']/ancestor::div[1]//div[contains(@class,'expressionsResultInput')]//span[contains(@class,\"dropdown\")]/input";
	public static final String SAVECLOSE_BUTTON = "//div[contains(@class,\"buttonset\")]//button[text()=\"SAVE & CLOSE\"]";
	public static final String TIME_UNIT_DROPDOWN = "//*[@id='scs-rule-builder-container']//select[contains(@class,'scs-rb-dropdown-date-time')]";
	public static final String TIME_UNIT_VALUE_TXT = "//*[@id='scs-rule-builder-container']//div[contains(@class,'expressionsResultInput ng-scope')]//input[@ng-model='expression.value']";
	public static final String THEN_TXT = "//*[@id='scs-rule-builder-container']//div[text()='THEN']/ancestor::div[1]//div[contains(@class,'expressionsResultInput')]//input[@type!='checkbox']";
	public static final String ELSE_TXT = "//*[@id='scs-rule-builder-container']//div[text()='ELSE']/ancestor::div[1]//div[contains(@class,'expressionsResultInput')]//input[@type!='checkbox']";
	public static final String IF_BTN_DRAG_ELEMENT = "//div[@id=\"scs-rule-builder-right-header-container\"]/div[text()= \"IF\"]";
	public static final String MAIN_DROP_AREA = "//div[@id=\"scs-rule-builder-container\"]//div[contains(@class,\"mainDropArea\")]";
	public static final String VALUE_BTN_DRAG_ELEMENT = "//div[@id=\"scs-rule-builder-right-header-container\"]/div[text()=\"Value\"]";
	public static final String COMPLIANCE_DROP_AREA = "//div[@id=\"scs-rule-builder-container\"]//div[contains(@class,\"ifElse\")]/div[contains(@config,\"ifConfig\")]";
	public static final String DELETE_ICON_RULE = "//*[@id=\"scs-designform-form-content-prop-area\"]//div[text()=\"RULE_NAME\"]/../..//i[contains(@class,\"trash\")]";
	public static final String INVALID_RULE_CONFIGURATION_ERROR_LABEL = "//div[contains(text(),\"Rule Configuration is invalid. Please update the rule.\")]";
	public static final String CANCEL_BUTTON = "//button[text()='Cancel']";
	public static final String EXPRESSION_RULE_PRESENCE = "//div[@class='scs-existing-rules-list'][contains(@ng-show,'ExpressionRule')]";
	 
	//Field
	public static final String FIELD_SHORTNAME = "//*[@ng-model='formElems']/li//div[text()='(SHORT_NAME)']";
	public static final String FIELD_COMPLIANCE_ICON_STATUS = "//*[@id='scs-designform-center-panel']/div[2]/div[2]";
	public static final String FIELD_COMPLIANCE_EXPAND = "//*[@id='scs-designform-center-panel']/div[2]/div[@class='k-icon k-expand-next k-i-arrow-60-up']";
	public static final String FIELD_COMPLIANCE_CLOSE = "//*[@id='scs-designform-center-panel']/div[2]/div[@class='k-icon k-collapse-next k-i-arrow-60-down']";
	public static final String EDIT_COMPLIANCE_RULE = "//div[@id='scs-resources-grid']//div[@class='tableBody']//span[1]/i";
	public static final String COMPLIANCE_BAR_DRAG = "//*[@id='scs-designform-center-panel']/div[2]";
	public static final String COMPLIANCE_BAR_DROP = "//*[@id='scs-designform-activity-area']/div[1]";
	public static final String HIGHLIGHTED_FLD_SHRTNM = "//*[@id='ShortName']//input";
    public static final String DELETE_FIELD_TYPE = "//span[contains(text(),'%')]//parent::div//following-sibling::div[@class='scs-droped-rightbox']//i[contains(@ng-click,'deleteFormElement')]";
    public static final String FIELD_IN_USE_ERROR_MESSAGE = "//div[contains(text(),'Field is used in rule. Cannot be deleted.')]";
	//Compliance Rule Builder
	public static final String DATE_TIME_VALUE_TXT = "//div[@id=\"scs-rule-builder-container\"]//span[contains(@class,\"picker\")]//input";
	public static final String CMPLNCE_RESRC_NAME_LST = "//*[@id='scs-resources-grid']//span[contains(@ng-bind, 'Name')]";

	//Release Form
	public static final String RELEASE_FORM_STEP = "//span[@ng-bind='step.title' and text()='Release Form']";
	public static final String RELEASE_FORM_PG = "//*[@id='breadCrumb']//a[text()='Release Form']";
	public static final String SELECT_FORM_LBL = "//div[text()='FORM_NAME']";
	public static final String EDIT_FORM_BTN = "//div[div/div[text()='FORM_NAME']]//span[@class='fa fa-edit ng-scope']";
	public static final String RELEASE_BTN = "//*[@id='scs-release-form-right-panel']/div[2]/button";
	public static final String RELEASE_SUMMARY_TXA = "//*[@id='scs-release-form-comment']";
	public static final String RELEASE_SUMMARY_SAVE_BTN = "//button[text()='SAVE']";
	public static final String FORM_DETAILS = "//div[div/div[text()='FORM_NAME']]//div[contains(@class,\"form-container-info\")]";
	public static final String FIELD_TYPE_ERR_MSG = "//*[contains(@id,'field-TYPEOFFIELD')]/div[@class='scs-designform-alert-msg']";
	public static final String FORM_VERSION_LBL = "//div[text()='FORMNAME']/..//span[@id='scs-form-version']";

	//Preview Forms
	public static final String PREVIEW_BTN = "//button[@id='scs-form-design-preview']";
	public static final String PREVIEW_FORM_OPENED = "//*[@id='scs-form-preview-form']//div[@class='scs-form-title ng-binding' and text()='FORM_NAME']";
	public static final String PREVIEW_FORM_NOTE = "//div[div[text()='Note']]/div[text()='NOTE_COMMENT']";
	public static final String PREVIEW_FORM_RELATED_DOCS = "//div[div[text()='Related Documents']]//span[text()='DOCUMENT_NAME']";
	public static final String PREVIEW_SUMMARY = "//*[@class='scs-field-detail-left' and text()='Summary:']";
	public static final String ALL_FIELDS = "//field-template/div";
	public static final String ALL_FIELDS_NAME = "//field-template/div//label[@for='field-FIELD_ID']";
	public static final String ALL_FIELDS_REQUIRED = "//field-template/div//label[@for='field-FIELD_ID']/span";
	public static final String SELECT_RESOURCE_DDL = "//div[div[text()='Select Resource:']]//span[@class='k-icon k-i-arrow-60-down']"; 
	public static final String RESOURCE_LIST_VALUE = "//li[contains(text(),'NAME')]";
	public static final String SELECTED_RESOURCE_LBL = "//div[div[text()='Select Resource:']]//span[contains(@class,'input')]";
	public static final String CLOSE_PREVIEW_BTN = "//button[@id='scs-close-preview-button']";
	public static final String SUMMRY_TABLE_SEC_VAL = "//*[@id='scs-audit-form-summary']/tbody//td[contains(.,'SECTIONTITLE')]/../td[COLUMNINDEXNO]";
	public static final String SUMMRY_COLMN_NAMES_TBL = "//*[@id='scs-audit-form-summary']/thead//td";


	public static final String ADD_FIELD_TYPE = "//*[@id='scs-designform-left-panel-fields-contents']//span[text()='FIELDTYPE']";
	public static final String ADD_FIELD_ELEMENTS = "//*[@id='scs-designform-elem-option-contents']//span[text()='FIELDELEMENT']";
	public static final String ADD_CHK_AND_AUDIT_FORM_FIELD = "//*[contains(@id,'field-TYPEOFFIELD')]//input[contains(@class,'ng-empty')]";
	public static final String ADD_QUEST_FORM_FIELD = "//*[contains(@id,'field-TYPEOFFIELD')]//textarea[contains(@class,'ng-empty')]";
	//	public static final String ADD_GRP_AND_SEC_FORM_ELE = "//li[contains(@id,'field-ELEMENTTYPE')][contains(@class,'active')]";
	public static final String ADD_GRP_AND_SEC_FORM_ELE = "//li[contains(@id,'field-ELEMENTTYPE')][COUNT]";
	public static final String GRP_AND_SEC_CHK_ELE_TXT = "//li[contains(@id,'field-ELEMENTTYPE')][contains(@class,'active')]//input";
	public static final String GRP_AND_SEC_QUEST_ELE_TXA = "//li[contains(@id,'field-ELEMENTTYPE')][contains(@class,'active')]//textarea";
	public static final String IDENTIFIER_SELECT = "//*[@id='field-Identifier-0']//input";
	public static final String IDENTIFIER_SELECT1 = "//*[@id='field-Identifier-1']//input";
	public static final String COMPLIANCE_UNIT = "//*[@class='scs-field-detail-right']//span[contains(@class,'compliance-unit')]";
	public static final String COMPLIANCE_MIN_VAL = "//*[@class='scs-field-detail-right']//span[contains(@class,'scs-compliance-value')][1]/span[1]";
	public static final String COMPLIANCE_MAX_VAL = "//*[@class='scs-field-detail-right']//span[contains(@class,'scs-compliance-value')][1]/span[3]";
	public static final String COMPLIANCE_TARGET = "//*[@class='scs-field-detail-right']//span[contains(@class,'scs-compliance-value')][2]/span[1]";
	public static final String RELEASE_PAGE_PREVIEW_BTN = "//div[contains(text(),'FORM')]/preceding-sibling::div[@id='scs-Release-Form-preview']";
	public static final String SELECT_RESOURCE_HEADER = "//*[@class='scs-nav-options ng-scope scs-nav-completed']//span[contains(.,'Select Resources')]";
	public static final String RESOURCE_GRD = "//*[@id='scs-formdesigner-select-resource-grid']//div[contains(text(),'RESOURCENAME')]";
	public static final String RELEASE_PAGE_ERRORS = "//*[@id='scs-released-form-error-errors']/div[contains(@ng-repeat, 'fieldInfo')]";
	public static final String SET_EXISTING_SEC = "//div[contains(text(),'SECTIONNAME')]";

	// Form Navigator
	public static final String FORM_LVL_FIELD_NAME = "//*[@class='scs-designform-level-title'][contains(.,'Form Level')]/..//div[contains(@class,'scs-designform-content')]//span";
	public static final String SECTION_FRM_LVL_FIELD_NAME = "//*[@class='scs-designform-level-title'][contains(.,'Form Level')]/..//div[contains(@class,'scs-designform-content')]//li//span";

	// Popup
	public static final String SCS_FRM_POPUP_MSG = "//*[@id='scs-popup'][contains(@class,'-visible')]";

	//tan 
	public static final String AGG_SRC_DRPDWN = "//div[@id=\"Source\"]//span[@class=\"k-icon k-i-arrow-60-down\"]";
	public static final String AGG_SRC_OPTION = "//ul[@aria-hidden='false']/li[text()='SOURCE_OPTION']";
	public static final String AGG_SRC_DRP_DWN_OPTIONS = "//div[@class='k-animation-container']//ul/li";
	public static final String COPY_ICON = "//i[@class='fa fa-copy']";
	public static final String SET_AGGREGATE_FUNCTION = "//*[@id=\"Function\"]/span/span/input";

	//RELEASE FORM PAGE --
	public static final String ERROR_SYMBOL = "//i[@class='fa fa-exclamation-triangle popout-icon-color']";
	public static final String  ERRORS_FOR_FIELD = "//*[@id='scs-released-form-error-errors']//span[contains(text(),'FIELD_SHORT_NAME')]/../div//span";
	//--/

	public static final String PROPERTIES_TABS = "//*[@id='scs-designform-form-content-prop-conteiner']//div[text()='TAB_NAME']";
	public static final String DEL_SAVED_FORM_DIALOG = "//span[@class='ui-dialog-title' and  text()='Delete Saved Form']";

	//UI Display of elements
	public static final String FORM_ELEMENTS_HEADER = "//*[@class='scs-designform-panel-header' and contains(text(),'Form Elements')]";
	public static final String FORM_ELEMENTS_LIST = "//ol[@id='scs-designform-elem-option-contents']/li/div/span";
	public static final String FIELD_TYPES_LIST = "//ol[@id='scs-designform-left-panel-fields-contents']/li/div/span";
	public static final String FORM_LEVEL_FIELD = "//*[@ng-model='formElems']/li//div[text()='(SHORT_NAME)']/..";
	public static final String GEN_TAB_DEFAULT_LBL = "//label[text()='Default']";
	public static final String GEN_TAB_DEFAULT_INP = "//label[text()='Default']/../div/input";
	public static final String GEN_TAB_REPEAT_LBL = "//label[text()='Repeat']";
	public static final String NO_FLIELD_SELECTED_TXT = "//*[@id='scs-designform-form-content-prop-area']//*[text()='Select an element on the form to modify its properties']";
	//Properties Panel Text
	public static final String NO_FIELD_SELECTED_TXT = "//*[@id='scs-designform-form-content-prop-area']//*[text()='Select an element on the form to modify its properties']";
	public static final String FORM_NAME_ELMNT_SELECTED_TXT = "//*[@id='scs-designform-form-content-prop-area']//*[text()='Select an element on the form to modify its properties']";
	public static final String NOTE_SELECTED_PROP_PANEL_TXT ="//*[@id='scs-designform-form-content-prop-area']//*[text()='Use the text field in the form element to configure the Note']";



	public static final String FORM_NAV_HEADER_LVL_ELM = "//div[@id='scs-designform-form-navigator-content-list']/div[@class='scs-designform-level-container'][1]//span[text()='ELEMENT_NAME']";
	public static final String FORM_NAV_HEADER_LVL_ELM_V2 = "//div[@class='scs-designform-level-container'][1]//span[text()='ELEMENT_NAME']";
	public static final String FORM_NAV_FORM_LVL_FIELD = "//div[@id='scs-designform-form-navigator-content-list']/div[@class='scs-designform-level-container'][2]//span[text()='FIELD_NAME']";
	public static final String FORM_NAV_FORM_LVL_FIELDS_LIST = "//div[@id='scs-designform-form-navigator-content-list']/div[@class='scs-designform-level-container'][2]//span";
	public static final String FORM_NAV_FORM_LVL_FIELD_V2 = "//div[@class='scs-designform-level-container'][2]//span[text()='FIELD_NAME']";
	public static final String SELECTED_FIELD_NAME_INPUT = "//li[contains(@class,'scs-active-dd-item')]//input";
	public static final String SELECTED_FIELD_DELETE_ICON = "//li[contains(@class,'scs-active-dd-item')]//i[@class='fa fa-trash-o']";
	public static final String SELECTED_FIELD_COPY_ICON = "//li[contains(@class,'scs-active-dd-item')]//i[@class='fa fa-copy']";
	public static final String SELECTED_FIELD_MOVE_ICON = "//li[contains(@class,'scs-active-dd-item')]//i[@class='fa fa-arrows-v dd-handle']";
	public static final String COPY_ICON_FOR_FORM = "//div[text()='FORM_NAME']//following::div[@class='scs-release-form-icons'][1]/span[@class='fa fa-copy ng-scope']";
	public static final String FORM_ON_RELEASE_PG = "//div[text()='FORM_NAME']/../..";
	public static final String YES_BUTTON = "//button[text()='YES']";
	public static final String COPY_FORM_POPUP = "//div[@class='scs-popup-main-body-form']";

	// Breadcrumbs - > Form Designer
	public static final String BREADCRUMBS = "//*[@id='breadCrumb']//a";
	public static final String BREADCRUMBS_SELECTRESOURCES = "";
	public static final String BREADCRUMBS_SELECTFORMTYPE = "";
	public static final String BREADCRUMBS_FORMDESIGNER = "";
	public static final String BREADCRUMBS_HOME = "";

	// Navigation Panel
	public static final String NAV_PANEL_DESIGN_FORM = "//div[@class='scs-nav']//span[text()='Design Form']";

	public static final String SHOW_HINT_OFF_BTN = "//div[@id='ShowHint']/label[2]";

	public static final String COMPLIANT_TEXT_DEFAULT_INPUT = "//div[@id='scs-resources-grid']//input[@ng-model='formData[currentFieldID].DefaultCompliance.ComplianceValue']";

	//FIELD BANK
	public static final String FIELD_BANK_BTN = "//div[text()='FIELD BANK']";
	public static final String FILED_BANK_SEARCH = "//input[@id='filterText']";
	public static final String FIELD_BANK_FIELD = "//td[text()='FIELD_NAME']";

	public static final String PRVW_BTN_FOR_FORM_RLS_PG = "//div[text()='FORM_NAME']//preceding-sibling::div[@id='scs-Release-Form-preview']";
	
	public static final String COMPLIANT_NONCOMPLINAT_VALUE_SYMBOL = "//span[@class='compliancemode']/i";

	public static final String DOCUMENT_NAME_LBL = "//div[@id=\"scs-related-documents-list\"]//span[contains(text(),\"DOCUMENTNAME\")]";
	public static final String PREVIEW_ON_RELEASE_PAGE ="//div[text()=\"FORMNAME\"]/..//div[text()=\"Preview\"]";
	public static final String EXPAND_COLLAPSE_BTN = "//*[@id=\"scs-form-level\"]//label[contains(text(),\"FIELDNAME\")]/.././..//i[contains(@class,\"square\")]";
	public static final String FIELD_SETTING_LABEL = "//*[@id=\"scs-form-level\"]//label[contains(text(),\"FIELDNAME\")]/.././..//div[contains(text(),\"FIELDSETTING\")]";
	public static final String SELECT_FORM_TYPE_LNK = "//*[@id='breadCrumb']//a[text()='Select Form Type']";

	//Resource Selection for Related Docs
	public static final String SELECT_ALL_RESOURCES_HEADER_CB = "//*[@id=\"scs-resources-grid\"]/div[@class='tableHeading']//input[@type='checkbox']";
	public static final String RESOURCE_INSTANCE_CB = "//*[@id=\"scs-resources-grid\"]//span[contains(text(),'RESOURCEINSTANCENAME')]/../input[@type='checkbox']";

	//Select Resource
	public static final String SEARCH_RESOURCE_TXT = "//input[@id='filterText']";
	public static final String RESOURCE_NAME_LBL = "//*[@id=\"filterText_listbox\"]//span[contains(text(),'RESOURCEINSTANCENAME')]";
	public static final String SEARCH_RESOURCE_BTN = "//*[@id=\"filterDataSource\"]";
	
	public static final String DATE_COMPLIANCE_INPUT = "//div[@class='scs-rb-select-container']//following::input[@type='text']";

	// Date Diff Expression Rule
	public static final String DRAG_FIELD = "//div[@id='scs-rule-builder-right-header-container']//div[text()='Field']";
	public static final String DRAG_MINUS_OPERATOR = "//div[@id='scs-rule-builder-right-header-container']//div[text()='-']";
	public static final String DROP_AREA_1st = "//div[@droppable]";
	public static final String INPUT_1st = "//div[@droppable]//input[@type]";
	public static final String DROP_AREA_OPERATOR = "//div[@droppable]//div[contains(@class,'expressionDiv')]";
	public static final String DROP_AREA_2ndFIELD = "//div[@class='divWithOperator ng-scope']/div[@droppable]";
	public static final String INPUT_2nd = "//div[@class='divWithOperator ng-scope']/div[@droppable]//input[@type]";
	
	public static final String COMPLIANCE_MIN_FOR_RESOURCE = "//span[contains(text(),'RES_INSTANCE')]/../..//input[@ng-model='config.Compliance.Min']";
	public static final String COMPLIANCE_MAX_FOR_RESOURCE = "//span[contains(text(),'RES_INSTANCE')]/../..//input[@ng-model='config.Compliance.Max']";
	public static final String COMPLIANCE_TARGET_FOR_RESOURCE = "//span[contains(text(),'RES_INSTANCE')]/../..//input[@ng-model='config.Compliance.Target']";
	public static final String COMPLIANCE_UOM_FOR_RESOURCE = "//span[contains(text(),'RES_INSTANCE')]/../..//input[@ng-model='config.Compliance.UOM']";
	
	public static final String DRAG_VALUE = "//div[@id='scs-rule-builder-right-header-container']//div[text()='Value']";
	public static final String IF_DROPPABLE_AREA = "//div[@config='ifConfig' and @droppable]"; 
	public static final String IF_EXPRESSION_VALE_IN = "//input[@ng-model='expression.value']";
	
	public static final String IF_EXPRESSION_THEN = "(//div[text()='THEN']/following-sibling::div//input)[1]";
	public static final String IF_EXPRESSION_ELSE = "(//div[text()='ELSE']/following-sibling::div//input)[1]";
	
	public static final String SEL_MUL_SEL_ONE_COMPLIANCE_INPUT = "//div[@id='scs-resources-grid']//select[@k-ng-model='formData[currentFieldID].DefaultCompliance.ComplianceValue']/..//input";
	
	public static final String PROPERTIES_SINGLE_PANEL = "//div[@id='scs-designform-form-content-prop-conteiner']/div/div[contains(text(),'Properties for ELEMENT')]";
	public static final String PROP_SNGL_PNL_FRM_NM = "//div[@id='scs-designform-form-content-prop-conteiner']/div/div[contains(text(),'Element Properties')]";
	public static final String PROPERTIES_PANEL_NO_TABS = "//div[@id='scs-designform-form-content-prop-conteiner']//div[@class='scs-properties-panel-tabs ng-hide']";
	public static final String HEADER_ELEMENTS_IN_DESIGNER = "//span[text()='ELEMENTNAME:']";
	

	
	public static final String FORM_HEADER_SELECTION = "//div[@class='scs-nav-options ng-scope scs-nav-completed']/span[2][contains(text(),'Select Resources')]";
	//public static final String RESOURCE_DELETION = "//div[contains(text(),'Apples' )]";
	public static final String RESOURCE_DELETION ="//div[contains(text(),'INSTANCE_NAME' )]/following-sibling::i";
	public static final String FORM_HEADER_SELECT_RESOURCES ="//div[@class='scs-nav-options ng-scope scs-nav-completed']/span[contains(text(),'Select Resources')]";

	public static final String NO_DATA_FOUND = "//*[@id='resrcDrpdown-list']//div[text()='No data found.']";
	public static final String DROPDOWN_LIST_FILTER = "//*[@id='resrcDrpdown-list']/span/input";
	public static final String SELECTRESOURCES_TAB = "//span[text()='Select Resources']";
	public static final String NO_DATA_POPUP = "//*[@id='filterText-list']//div[text()='No data found.']";

	// Dependency Rule builder
	public static final String DEPENDENCY_RULE_PRESENCE = "//div[@class='scs-existing-rules-list'][contains(@ng-show,'DependencyRule')]";
	public static final String RULE_EDITFORM_NAME_FIELD = "//input[@id='scs-rule-builder-name']";
	public static final String DEPENDENCY_RULE_ADD_NEW_BUTTON = "//button[contains(@class,'scs-add-new-rule')][@data-add-rule-for='DependencyRule']";
	public static final String EXIT_FORM = "//div[@class='scs-nav-exit']";
    public static final String DELETE_ICON_OF_DEPENDENCY_RULE = "//div[@class='scs-delete-rule'][@data-delete-for='DependencyRule']";


}
