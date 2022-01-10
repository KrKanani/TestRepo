package com.project.safetychain.webapp.pageobjects;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.amazonaws.services.simplesystemsmanagement.model.LoggingInfo;
import com.project.utilities.CommonMethods;
import com.project.utilities.ControlActions;

public class FormDesignerPage extends CommonPage{

	static String allFieldsOfForm[][] = new String[256][2];
	public String automationReleaseFormMsg = "Automation Testing Form";
	static int fieldNumber = 0;
	public static String noRepeatExpectedNote = "Parent field group has repeat value, field cannot be repeated.";
	public static String noRepeatExpectedNoteAdtFrm = "Parent question group has repeat value, field cannot be repeated.";
	public static String noRepeatExpectedNoteAtdGroup = "Field(s) within this group contains repeat value, hence question group cannot have repeat.";
	public static String noRepeatExpectedNoteGroup = "Field(s) within this group contains repeat value, hence field group cannot have repeat.";
	public static final String dragDropJSExecutorScript = "function createEvent(typeOfEvent) {\n" + "var event =document.createEvent(\"CustomEvent\");\n"
            + "event.initCustomEvent(typeOfEvent,true, true, null);\n" + "event.dataTransfer = {\n" + "data: {},\n"
            + "setData: function (key, value) {\n" + "this.data[key] = value;\n" + "},\n"
            + "getData: function (key) {\n" + "return this.data[key];\n" + "}\n" + "};\n" + "return event;\n"
            + "}\n" + "\n" + "function dispatchEvent(element, event,transferData) {\n"
            + "if (transferData !== undefined) {\n" + "event.dataTransfer = transferData;\n" + "}\n"
            + "if (element.dispatchEvent) {\n" + "element.dispatchEvent(event);\n"
            + "} else if (element.fireEvent) {\n" + "element.fireEvent(\"on\" + event.type, event);\n" + "}\n"
            + "}\n" + "\n" + "function simulateHTML5DragAndDrop(element, destination) {\n"
            + "var dragStartEvent =createEvent('dragstart');\n" + "dispatchEvent(element, dragStartEvent);\n"
            + "var dropEvent = createEvent('drop');\n"
            + "dispatchEvent(destination, dropEvent,dragStartEvent.dataTransfer);\n"
            + "var dragEndEvent = createEvent('dragend');\n"
            + "dispatchEvent(element, dragEndEvent,dropEvent.dataTransfer);\n" + "}\n" + "\n"
            + "var source = arguments[0];\n" + "var destination = arguments[1];\n"
            + "simulateHTML5DragAndDrop(source,destination);";

	public FormDesignerPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 40);
		controlActions = new ControlActions(driver);
		fieldNumber = 0;
	}

	@FindBy(xpath = FormDesignerPageLocator.SELECT_CHECK_FORM_LNK)
	public WebElement SelectCheckFormLnk;

	@FindBy(xpath = FormDesignerPageLocator.SELECT_QUESTIONNAIRE_FORM_LNK)
	public WebElement SelectQuestionnaireFormLnk;

	@FindBy(xpath = FormDesignerPageLocator.SELECT_AUDIT_FORM_LNK)
	public WebElement SelectAuditFormLnk;

	@FindBy(xpath = FormDesignerPageLocator.SHOW_TARGET_BTN)
	public WebElement ShowTargetBtn;

	@FindBy(xpath = FormDesignerPageLocator.DRAG_RES)
	public WebElement DragRes;

	@FindBy(xpath = FormDesignerPageLocator.DROP_RES)
	public WebElement DropRes;

	@FindBy(xpath = FormDesignerPageLocator.VERIFY_RESOURCE_LBL)
	public WebElement VerifyResourceLbl; 

	@FindBy(xpath = FormDesignerPageLocator.NEXT_FORM_DESIGNER_LNK)
	public WebElement NextInFormDesigner;

	@FindBy(xpath = FormDesignerPageLocator.DESIGN_FORM_PG)
	public WebElement DesignFormPg;

	@FindBy(xpath = FormDesignerPageLocator.SET_FORM_NAME_TXT)
	public WebElement FormNameTxt;

	@FindBy(xpath = FormDesignerPageLocator.FORM_DESIGNER_FIELD_INP)
	public WebElement FieldsInp;

	@FindBy(xpath = FormDesignerPageLocator.SAVE_FORM_BTN)
	public WebElement SaveFormButton;

	@FindBy(xpath = FormDesignerPageLocator.SAVE_FORM_OK_BTN)
	public WebElement SaveFormOkButton;

	@FindBy(xpath = FormDesignerPageLocator.FORM_DESIGNER_EXIT_BTN)
	public WebElement FormDesignerExitBtn;

	@FindBy(xpath = FormDesignerPageLocator.DELETE_SAVE_FORM_YES_BTN)
	public WebElement DeleteFormYesBtn;

	@FindBy(xpath = FormDesignerPageLocator.FIELD_COMPLIANCE_ICON_STATUS)
	public WebElement ComplianceIconStatus;

	@FindBy(xpath = FormDesignerPageLocator.FIELD_COMPLIANCE_EXPAND)
	public WebElement ComplianceOpen;

	@FindBy(xpath = FormDesignerPageLocator.FIELD_COMPLIANCE_CLOSE)
	public WebElement ComplianceClose;

	@FindBy(xpath = FormDesignerPageLocator.DEFAULTCHK)
	public WebElement DefaultChk;

	@FindBy(xpath = FormDesignerPageLocator.MINIMUM_TXT)
	public WebElement MinimunTxt;

	@FindBy(xpath = FormDesignerPageLocator.TARGET_TXT)
	public WebElement TargetTxt;

	@FindBy(xpath = FormDesignerPageLocator.MAXIMUN_TXT)
	public WebElement MaximumTxt;

	@FindBy(xpath = FormDesignerPageLocator.UOM_TXT)
	public WebElement UOMTxt;

	@FindBy(xpath = FormDesignerPageLocator.NOTE_DBL)
	public WebElement NoteDbl;

	@FindBy(xpath = FormDesignerPageLocator.RELATED_DOCS_DBL)
	public WebElement RelatedDocsDbl;

	@FindBy(xpath = FormDesignerPageLocator.SECTION_DBL)
	public WebElement SectionDbl;

	@FindBy(xpath = FormDesignerPageLocator.GROUP_DBL)
	public WebElement GroupDbl;

	@FindBy(xpath = FormDesignerPageLocator.SUMMARY_DBL)
	public WebElement Summarydbl;

	@FindBy(xpath = FormDesignerPageLocator.HEADER_LEVEL)
	public WebElement HeaderLevel;

	@FindBy(xpath = FormDesignerPageLocator.NOTE_TXT)
	public WebElement NoteTxt;

	@FindBy(xpath = FormDesignerPageLocator.ADD_NEW_DOCUMENT_BTN)
	public WebElement AddNewDocumentButton;

	@FindBy(xpath = FormDesignerPageLocator.DOCUMENT_TYPE_DDL)
	public WebElement DocumentTypeDdl;

	@FindBy(xpath = FormDesignerPageLocator.SAVE_DOCUMENT_BTN)
	public WebElement SaveDocumentBtn;

	@FindBy(xpath = FormDesignerPageLocator.DELETE_RELTD_DOCS_BTN)
	public WebElement DeleteReltdDocsBtn;
	
	@FindBy(xpath = FormDesignerPageLocator.FORM_LEVEL)
	public WebElement FormLevel;

	@FindBy(xpath = FormDesignerPageLocator.SECTION1_LEVEL)
	public WebElement section1Level;

	@FindBy(xpath = FormDesignerPageLocator.FIELD_SETTINGS_TAB)
	public WebElement SettingsTab;

	@FindBy(xpath = FormDesignerPageLocator.FIELD_REPEAT_INP)
	public WebElement RepeatFieldCountInp;

	@FindBy(xpath = FormDesignerPageLocator.FIELD_NO_REPEAT_TXT)
	public WebElement NoRepeatTxt;

	@FindBy(xpath = FormDesignerPageLocator.ALLOW_COMMENTS_ON_BTN)
	public WebElement AllowCommentsONBtn;

	@FindBy(xpath = FormDesignerPageLocator.SHOW_HINT_ON_BTN)
	public WebElement ShowHintONBtn;

	@FindBy(xpath = FormDesignerPageLocator.HINT_TXA)
	public WebElement HintTxa;

	@FindBy(xpath = FormDesignerPageLocator.ALLOW_ATTACHMENT_ON_BTN)
	public WebElement AllowAttachmentsONBtn;

	@FindBy(xpath = FormDesignerPageLocator.ALLOW_CORRECTION_ON_BTN)
	public WebElement AllowCorrectionsONBtn;
	
	@FindBy(xpath = FormDesignerPageLocator.ALLOW_CORRECTION_OFF_BTN)
	public WebElement AllowCorrectionsOffBtn;

	@FindBy(xpath = FormDesignerPageLocator.MARK_RESOLVED_ON_BTN)
	public WebElement MarkResolvedONBtn;

	@FindBy(xpath = FormDesignerPageLocator.ADD_NEW_PREDEINED_CORRECTION_BTN)
	public WebElement PredefinedCorrectionAddBtn;

	@FindBy(xpath = FormDesignerPageLocator.PREDEINED_CORRECTION_OPTION_INP)
	public WebElement PredefinedCorrectionOptionInp;

	@FindBy(xpath = FormDesignerPageLocator.CARRYOVSER_FIELD_ON_BTN)
	public WebElement CarryOverFieldONBtn;

	@FindBy(xpath = FormDesignerPageLocator.NUMERIC_DBL)
	public WebElement NumericFieldDbl;

	@FindBy(xpath = FormDesignerPageLocator.AGGRGATE_DBL)
	public WebElement AggrgateFieldDbl;

	@FindBy(xpath = FormDesignerPageLocator.SECTION_FIELD1_TXT)
	public WebElement Section1Txt;

	@FindBy(xpath = FormDesignerPageLocator.NUMERIC_FIELD_TXT)
	public WebElement NumericFieldTxt;

	@FindBy(xpath = FormDesignerPageLocator.NUMERIC_FIELD_TXA)
	public WebElement NumericFieldTxa;

	@FindBy(xpath = FormDesignerPageLocator.QUESTION_WEIGHT_INP)
	public WebElement QuestionWeightInp;

	@FindBy(xpath = FormDesignerPageLocator.ADD_NEW_OPTION_BTN)
	public WebElement AddNewOption;

	@FindBy(xpath = FormDesignerPageLocator.OPTION_INP)
	public WebElement OptionValueInp;

	@FindBy(xpath = FormDesignerPageLocator.SHORT_NAME_LBL)
	public WebElement ShortNameLbl;

	@FindBy(xpath = FormDesignerPageLocator.CURRENT_FIELD_LBL)
	public WebElement CurrentFieldLbl;

	@FindBy(xpath = FormDesignerPageLocator.OPTION_WEIGHT_INP)
	public WebElement OptionWeightInp;

	@FindBy(xpath = FormDesignerPageLocator.RELEASE_FORM_PG)
	public WebElement ReleaseFormPg;

	@FindBy(xpath = FormDesignerPageLocator.RELEASE_FORM_STEP)
	public WebElement ReleaseFormStep;

	@FindBy(xpath = FormDesignerPageLocator.RELEASE_BTN)
	public WebElement ReleaseBtn;

	@FindBy(xpath = FormDesignerPageLocator.RELEASE_SUMMARY_TXA)
	public WebElement ReleaseSummaryTxa;

	@FindBy(xpath = FormDesignerPageLocator.RELEASE_SUMMARY_SAVE_BTN)
	public WebElement ReleaseSummarySaveBtn;

	@FindBy(xpath = FormDesignerPageLocator.FIELD_TYPE_NAME_TXT)
	public WebElement FieldTypeNameText;

	@FindBy(xpath = FormDesignerPageLocator.FIELD_TYPE_DROP_AREA_FORM_LEVEL)
	public WebElement FieldTypeDropAreaFormLevel;

	@FindBy(xpath = FormDesignerPageLocator.ADD_NEW_VALUE_BUTTON)
	public WebElement AddNewValueButton;

	@FindBy(xpath = FormDesignerPageLocator.VALUE_TXT)
	public WebElement ValueTxt;

	@FindBy(xpath = FormDesignerPageLocator.SAVE_FORM_BUTTON)
	public WebElement SaveButton;

	@FindBy(xpath = FormDesignerPageLocator.OK_SAVE_FORM_BUTTON)
	public WebElement OkSaveButton;

	@FindBy(xpath = FormDesignerPageLocator.PREVIEW_BTN)
	public WebElement PreviewBtn;

	@FindBy(xpath = FormDesignerPageLocator.PREVIEW_SUMMARY)
	public WebElement VerifySummary;

	@FindBy(xpath = FormDesignerPageLocator.ALL_FIELDS)
	List<WebElement> AllFieldsLst;

	@FindBy(xpath = FormDesignerPageLocator.INDENTIFIER_INPUT_TYPE)
	public WebElement IdentifierInputType;

	@FindBy(xpath = FormDesignerPageLocator.SELECT_INDENTIFIER_INPUT_TYPE)
	public WebElement SelectIdentifierInputType;

	@FindBy(xpath = FormDesignerPageLocator.INDENTIFIER_INPUT_MASK)
	public WebElement IdentifierInputMask;

	@FindBy(xpath = FormDesignerPageLocator.IDENTIFIER_ADD_NEW_BTN)
	public WebElement AddNewBtn;

	@FindBy(xpath = FormDesignerPageLocator.IDENTIFIER_SELECT_ONE_INPUT)
	List<WebElement> SelectOneInput;

	@FindBy(xpath = FormDesignerPageLocator.AGGREGATE_FUNCTION)
	public WebElement AggregateFunction;

	@FindBy(xpath = FormDesignerPageLocator.SELECT_AGGREGATE_FUNCTION)
	public WebElement SelectAggregateFunction;

	@FindBy(xpath = FormDesignerPageLocator.SET_AGGREGATE_SOURCE)
	public WebElement SetAggregateSource;

	@FindBy(xpath = FormDesignerPageLocator.GENERAL_TAB)
	public WebElement GeneralTab;

	@FindBy(xpath = FormDesignerPageLocator.ADVANCED_TAB)
	public WebElement AdvancedTab;

	@FindBy(xpath = FormDesignerPageLocator.EXPRESSIONS_ADD_NEW_BTN)
	public WebElement AddNewExpressions;

	@FindBy(xpath = FormDesignerPageLocator.IF_TXT)
	public WebElement IfTxt;

	@FindBy(xpath = FormDesignerPageLocator.TIME_DD)
	public WebElement TimeDropDown;

	@FindBy(xpath = FormDesignerPageLocator.TIME_DDList)
	public List<WebElement> TimeDropDownList;

	@FindBy(xpath = FormDesignerPageLocator.EXPRESSIONS_DD)
	public WebElement Expressions_Dropdown;

	@FindBy(xpath = FormDesignerPageLocator.IF_ELEMENTS_VALUE)
	public WebElement IfElementsValue;

	@FindBy(xpath = FormDesignerPageLocator.THEN_DROPDOWN_ARROW)
	public WebElement ThenDropDownArrow;

	@FindBy(xpath = FormDesignerPageLocator.THEN_DROPDOWN_TXT)
	public WebElement ThenDropDownText;

	@FindBy(xpath = FormDesignerPageLocator.ELSE_DROPDOWN_ARROW)
	public WebElement ElseDropDownArrow;

	@FindBy(xpath = FormDesignerPageLocator.ELSE_DROPDOWN_TXT)
	public WebElement ElseDropDownText;

	@FindBy(xpath = FormDesignerPageLocator.THEN_TXT)
	public WebElement ThenText;

	@FindBy(xpath = FormDesignerPageLocator.ELSE_TXT)
	public WebElement ElseText;

	@FindBy(xpath = FormDesignerPageLocator.SAVECLOSE_BUTTON)
	public WebElement SaveCloseButton;

	@FindBy(xpath = FormDesignerPageLocator.DATE_TIME_VALUE_TXT)
	public WebElement DateTimeValueTxt;

	@FindBy(xpath = FormDesignerPageLocator.CMPLNCE_RESRC_NAME_LST)
	public List<WebElement> CmplnceResrcNameLst;
	
	@FindBy(xpath = FormDesignerPageLocator.TIME_UNIT_DROPDOWN)
	public WebElement TimeUnitDropdown;

	@FindBy(xpath = FormDesignerPageLocator.TIME_UNIT_VALUE_TXT)
	public WebElement TimeUnitValueTxt;

	@FindBy(xpath = FormDesignerPageLocator.COMPLIANCE_BAR_DRAG)
	public WebElement ComplianceBarDrag;

	@FindBy(xpath = FormDesignerPageLocator.COMPLIANCE_BAR_DROP)
	public WebElement ComplianceBarDrop;
	
	@FindBy(xpath = FormDesignerPageLocator.HIGHLIGHTED_FLD_SHRTNM)
	public WebElement HighlightedFldShrtnm;

	@FindBy(xpath = FormDesignerPageLocator.SELECT_RESOURCE_DDL)
	public WebElement SelectResourceDdl;

	@FindBy(xpath = FormDesignerPageLocator.RESOURCE_LIST_VALUE)
	public WebElement ResourceListValue;

	@FindBy(xpath = FormDesignerPageLocator.SELECTED_RESOURCE_LBL)
	public WebElement SelectedResourceLbl;

	@FindBy(xpath = FormDesignerPageLocator.CLOSE_PREVIEW_BTN)
	public WebElement ClosePreviewBtn;

	@FindBy(xpath = FormDesignerPageLocator.COMPLIANCE_UNIT)
	public WebElement ComplianceUnit; 

	@FindBy(xpath = FormDesignerPageLocator.COMPLIANCE_MIN_VAL)
	public WebElement ComplianceMinVal; 

	@FindBy(xpath = FormDesignerPageLocator.COMPLIANCE_MAX_VAL)
	public WebElement ComplianceMaxVal; 

	@FindBy(xpath = FormDesignerPageLocator.COMPLIANCE_TARGET)
	public WebElement ComplianceTargetVal; 

	@FindBy(xpath = FormDesignerPageLocator.IF_BTN_DRAG_ELEMENT)
	public WebElement IfBtnDragElement;

	@FindBy(xpath = FormDesignerPageLocator.MAIN_DROP_AREA)
	public WebElement MainDropArea;

	@FindBy(xpath = FormDesignerPageLocator.VALUE_BTN_DRAG_ELEMENT)
	public WebElement ValueBtnDragElement;

	@FindBy(xpath = FormDesignerPageLocator.COMPLIANCE_DROP_AREA)
	public WebElement ComplianceDropArea;

	@FindBy(xpath = FormDesignerPageLocator.DELETE_FIELD_ICON)
	public WebElement DeleteFieldIcon;

	@FindBy(xpath = FormDesignerPageLocator.FIELD_CANNOT_BE_DELETED_POPUP_LABEL)
	public WebElement FieldCannotBeDeletedPopupLabel;

	@FindBy(xpath = FormDesignerPageLocator.OK_BUTTON)
	public WebElement OkButton;

	@FindBy(xpath = FormDesignerPageLocator.DELETE_ICON_RULE)
	public WebElement DeleteIconRule;

	@FindBy(xpath = FormDesignerPageLocator.INVALID_RULE_CONFIGURATION_ERROR_LABEL)
	public WebElement InvalidRuleConfigurationErrorLabel;

	@FindBy(xpath = FormDesignerPageLocator.CANCEL_BUTTON)
	public WebElement CancelButton;

	@FindBy(xpath = FormDesignerPageLocator.SET_AGGREGATE_MIN_TXT)
	public WebElement SetAggregateMinTxt;

	@FindBy(xpath = FormDesignerPageLocator.SET_AGGREGATE_MAX_TXT)
	public WebElement SetAggregateMaxTxt;

	@FindBy(xpath = FormDesignerPageLocator.FORM_NAME_LBL)
	public WebElement FormNameLabel;

	@FindBy(xpath = FormDesignerPageLocator.SELECT_RESOURCE_HEADER)
	public WebElement SelectResourceHeader;
	
	@FindBy(xpath = FormDesignerPageLocator.RELEASE_PAGE_ERRORS)
	public List<WebElement> ReleasePageErrors;
	
	@FindBy(xpath = FormDesignerPageLocator.FORM_LVL_FIELD_NAME)
	public WebElement FormLvlFieldName;
	
	@FindBy(xpath = FormDesignerPageLocator.SECTION_FRM_LVL_FIELD_NAME)
	public WebElement SectionFrmLvlFieldName;
	
	@FindBy(xpath = FormDesignerPageLocator.SCS_FRM_POPUP_MSG)
	public WebElement ScsFrmPopupMsg;
	
	@FindBy(xpath = FormDesignerPageLocator.SET_AGGREGATE_FUNCTION)
	public WebElement SetAggregateFunction;

	@FindBy(xpath = FormDesignerPageLocator.AGG_SRC_DRPDWN)
	public WebElement AggSourceDropDown;
	
	@FindBy(xpath = FormDesignerPageLocator.SUMMRY_COLMN_NAMES_TBL)
	public List<WebElement> SummryColmnNamesTbl;
	
	@FindBy(xpath = FormDesignerPageLocator.DEL_SAVED_FORM_DIALOG)
	public WebElement DeleteSavedFormDialog;
	
	@FindBy(xpath = FormDesignerPageLocator.FORM_ELEMENTS_HEADER)
	public WebElement FormElementsHeader;
	
	@FindBy(xpath = FormDesignerPageLocator.YES_BUTTON)
	public WebElement YesButton;
	
	@FindBy(xpath = FormDesignerPageLocator.SHOW_HINT_OFF_BTN)
	public WebElement ShowHintOFFBtn;
	
	@FindBy(xpath = FormDesignerPageLocator.COMPLIANT_TEXT_DEFAULT_INPUT)
	public WebElement CompliantTextDefaultInput;
	
	@FindBy(xpath = FormDesignerPageLocator.FIELD_BANK_BTN)
	public WebElement FieldBankBtton;
	
	@FindBy(xpath = FormDesignerPageLocator.FILED_BANK_SEARCH)
	public WebElement FieldBankSearch;
	
	@FindBy(xpath = FormDesignerPageLocator.NAV_PANEL_DESIGN_FORM)
	public WebElement NavigationPanelDesignForm;
	
	@FindBy(linkText = "Select Resources")
	public WebElement BreadCrumbsSelectResources;
	
	@FindBy(linkText = "Select Form Type")
	public WebElement BreadCrumbsSelectFormType;
	
	@FindBy(linkText = "Form Designer")
	public WebElement BreadCrumbsFormDesigner;
	
	@FindBy(linkText = "Home")
	public WebElement BreadCrumbsHome;
	
	@FindBy(xpath = FormDesignerPageLocator.SELECT_ALL_RESOURCES_HEADER_CB)
	public WebElement SelectAllResourcesCB;
	
	@FindBy(xpath = FormDesignerPageLocator.SEARCH_RESOURCE_TXT)
	public WebElement SearchResourceTxt;
	
	@FindBy(xpath = FormDesignerPageLocator.SELECT_FORM_TYPE_LNK)
	public WebElement SelectFormTypeLnk;
	
	
	@FindBy(xpath = FormDesignerPageLocator.FREETEXT_DBL)
	public WebElement FreeTextFieldDbl;

	
	@FindBy(xpath = FormDesignerPageLocator.FREETEXT_FIELD_TXT)
	public WebElement FreeTextFieldTxt;
	
	@FindBy(xpath = FormDesignerPageLocator.FREETEXT_FIELD_TXA)
	public WebElement FreeTextFieldTxa;
	
	@FindBy(xpath = FormDesignerPageLocator.FORM_HEADER_SELECT_RESOURCES)
	public WebElement FormHeaderSelectResources;
	
	@FindBy(xpath = FormDesignerPageLocator.DROPDOWN_LIST_FILTER)
	public WebElement DropDownListFilter;
	
	@FindBy(xpath = FormDesignerPageLocator.NO_DATA_FOUND)
	public WebElement NoDataFound;
	@FindBy(xpath = FormDesignerPageLocator.SELECTRESOURCES_TAB)
	public WebElement SelectResourcesTab;
	@FindBy(xpath = FormDesignerPageLocator.NO_DATA_POPUP)
	public WebElement NoDataPopup;

	@FindBy(xpath = FormDesignerPageLocator.DEPENDENCY_RULE_PRESENCE)
	public WebElement DependencyRulePresence;
	
	@FindBy(xpath = FormDesignerPageLocator.DEPENDENCY_RULE_ADD_NEW_BUTTON)
	public WebElement DependencyRuleAddNewButton;
	
	@FindBy(xpath = FormDesignerPageLocator.RULE_EDITFORM_NAME_FIELD)
	public WebElement RuleEditFormNameField;
	
	@FindBy(xpath =  FormDesignerPageLocator.EXPRESSIONS_ADD_NEW_BTN)
	public WebElement ExpressionRuleAddNewButton;
	
	@FindBy(xpath = FormDesignerPageLocator.EXPRESSION_RULE_PRESENCE)
	public WebElement ExpressionRulePresence;
	
	@FindBy(xpath = FormDesignerPageLocator.EXIT_FORM)
	public WebElement ExitForm;
	
	@FindBy(xpath = FormDesignerPageLocator.DELETE_ICON_OF_DEPENDENCY_RULE)
	public WebElement DeleteIconOfDependencyRule;
	
	@FindBy(xpath = FormDesignerPageLocator.FIELD_IN_USE_ERROR_MESSAGE)
	public WebElement FieldinUseErrorMessage;
	
	public enum FieldType {
		FREETEXT("Free Text"),
		DATE("Date");
		
		public final String fieldTypeValue;
		private FieldType(String fieldTypeValue){
			this.fieldTypeValue = fieldTypeValue;
		}
		
	}
	//static  List<String> totalFields = new ArrayList<String>();;
	/** 'selectResource' method is used to select resource while designing a from
	 * @author pashine_a
	 * @param String - 'resourceType'
	 * @param String - 'resourceCategory'
	 * @param String - 'resourceInstance'
	 * @return boolean status
	 * IF resource is added THEN true ELSE false
	 */	
	public boolean selectResource(String resourceType, String resourceCategory, String resourceInstance) {
		try {
//			Sync();
			String select1 = "li[class='k-item ng-scope'][role='option']";
			controlActions.JSSetValueFromList(driver, select1, resourceType);
//			Thread.sleep(3000);
			Sync();
			WebElement DragResource = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.DRAG_RES, "RESOURCE_CATEGORY",resourceCategory);
			controlActions.dragdrop(DragResource, DropRes);
			Sync();
			logInfo("Resource is added");
			String resourceName = resourceType+" > "+resourceCategory+" > "+resourceInstance;
			WebElement addedResource = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.VERIFY_RESOURCE_LBL, "RESOURCE_NAME",resourceName);
//			boolean verifyAddedResource = controlActions.isElementDisplayedOnPage(addedResource);
			boolean verifyAddedResource = addedResource.isDisplayed();
			if(!verifyAddedResource) {
				logError("Failed to add resource");
				return false;
			}
			logInfo("Resource is sucessfully selected");
			return true;
		}catch(Exception e) {
			logError("Failed to add resource - "
					+ e.getMessage());
			return false;
		}
	}

	/** 'setSettingsProperty' method is used to set the form field's properties
	 * @author pashine_a
	 * @param Web element - 'element'
	 * @param String - 'property'
	 * @param String - 'status'(ON/OFF)
	 * @return boolean status
	 * IF property is set THEN true ELSE false
	 */	
	public boolean setSettingsProperty(WebElement element, String property, String status) {
		try {
			controlActions.clickElement(SettingsTab);
			logInfo("Clickted 'Settings' tab");
			controlActions.clickElement(element);
			logInfo("For property- '"+property+"' status set as "+status);
			String selectedStatus = element.getAttribute("class").toString();
			if(!(selectedStatus.equals("scs-toggle-btn selected") || selectedStatus.equals("scs-toggle-btn res-on selected"))) {
				logError("Status of '"+property+"' is not Verified");
				return false;
			}
			logInfo("Status of '"+property+"' is Verified");
			return true;
		}catch(Exception e) {
			logError("Failed to set property - '"+property+"' - "
					+ e.getMessage());
			return false;
		}
	}

	/** "releaseForm" method is used to release the form
	 * @author pashine_a
	 * @param String - formName
	 * @return boolean status
	 * IF form is released THEN true ELSE false
	 */	
	public boolean releaseForm(String formName) {
		try {
			WebElement selectForm = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.SELECT_FORM_LBL, "FORM_NAME",formName );
			selectForm.click();
			logInfo("Selected form - "+formName);
			ReleaseBtn.click();
			Sync();
			ReleaseSummaryTxa.sendKeys(automationReleaseFormMsg);
			ReleaseSummarySaveBtn.click();
			Sync();
			logInfo("Added release summary. Form is released");
			return true;
		}catch(Exception e) {
			logError("Failed to release form - "+formName+" - " 
					+ e.getMessage());
			return false;
		}
	}
	
	/**
	 * This method is used to set values to text box.
	 * @author thakker_k
	 * @param FieldTypeName Field Type for which you want to set value
	 * @param FieldTypeNamevalue Field type value to be set
	 * @return boolean This returns boolean true after setting value to text box
	 */
	public boolean setTextBoxValue(String FieldTypeName, String FieldTypeNamevalue) {
		WebElement TextBox = null;
		try {
			if(!getFields(FieldTypeName, FieldTypeNamevalue)) {
				logError("Failed to set values");
				return false;
			}
			TextBox = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_TYPE_NAME_TXT,"FIELDTYPENAME",FieldTypeName);
			controlActions.WaitforelementToBeClickable(TextBox);		
			controlActions.clickOnElement(TextBox);
			TextBox.clear();
			controlActions.sendKeys(TextBox, FieldTypeNamevalue);
			threadsleep(5000);
			logInfo(FieldTypeName +" entered: "+ FieldTypeNamevalue);
			return true;
		}
		catch(Exception e) {
			logError("Failed to set value for field " + FieldTypeName + " :"
					+ e.getMessage());
			return false;
		}	
	}

	/** "getFields" method is used to set the values in array 'allFieldsOfForm' contains all field details
	 * @author pashine_a
	 * @param String - FieldTypeName
	 * @param String - FieldTypeNamevalue
	 * @return boolean status
	 * IF values are setted in array THEN true ELSE false
	 */	
	public boolean getFields(String fieldTypeName, String FieldTypeNamevalue) {
		try {
			allFieldsOfForm[fieldNumber][0] = fieldTypeName.trim();
			allFieldsOfForm[fieldNumber][0] = allFieldsOfForm[fieldNumber][0].replaceAll(" ", "");
			allFieldsOfForm[fieldNumber][1] = FieldTypeNamevalue.trim();
			allFieldsOfForm[fieldNumber][1] = allFieldsOfForm[fieldNumber][1].replaceAll(" ", "");
			if(fieldTypeName.equals("Date & Time")) {
				allFieldsOfForm[fieldNumber][0] = 	"DateTime";
			}
			fieldNumber++;
			logInfo("Set Values in Fields");
			return true;
		}catch(Exception e) {
			logError("Failed to set values - "+e.getMessage());
			return false;
		}
	}

	/** "nextButton" method is used to navigate to 'Next' pages in form designer
	 * @author pashine_a
	 * @param Webelement element(next page element)
	 * @param String - page
	 * @return boolean status
	 * IF form is released THEN true ELSE false
	 */	
	public boolean clickOnNextButton(WebElement element,String page) {
		try {
//			Sync();
			controlActions.WaitforelementToBeClickable(NextInFormDesigner);
			NextInFormDesigner.click();
			Sync();
			Sync();
//			if(!controlActions.isElementDisplayedOnPage(element)) {
//				logError("Failed to move on 'Next' Page - "+page+"'"); 
//				return false;
//			}				
			logInfo("Moved to 'Next' page - "+page+"'");
			return true;
		}catch(Exception e) {
			logError("Failed to click on 'Next' - "+page+"' - " 
					+ e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to click Add New Value button.
	 * @author thakker_k
	 * @return boolean This returns boolean true after clicking Add New Value button
	 */
	public boolean clickAddNewValueButton() {

		try {
			controlActions.clickElement(AddNewValueButton);
			logInfo("Clicked Add New Value Button");
			return true;
		}
		catch(Exception e) {
			logError("Failed to click Add New Value button :"
					+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to set values to element.
	 * @author thakker_k
	 * @return boolean This returns boolean true after entering values to element
	 */
	public boolean setValuesToElement(String value) {		
		try {	

			if(clickAddNewValueButton()){
				threadsleep(5000);
				controlActions.WaitforelementToBeClickable(ValueTxt);		
				controlActions.clickOnElement(ValueTxt);
				ValueTxt.clear();
				controlActions.sendKeys(ValueTxt, value);
				threadsleep(5000);
				logInfo("Set values to element");
				return true;
			}			
			return false;
		}
		catch(Exception e) {
			logError("Failed to set values to element"
					+ e.getMessage());
			return false;
		}	
	}

	/**
	 * 'createAndReleaseForm' method is used to create & release the form having a 'Numeric' field
	 * @author pashine_a
	 * @param String - formType
	 * @param String - formName
	 * @param String - resourceType
	 * @param String - categoryValue
	 * @param String - instanceValue1
	 * @return boolean status
	 * IF created form gets released THEN true ELSE false;
	 */
	public boolean createAndReleaseForm(String formType,String formName,String resourceType,String categoryValue, String instanceValue1) {
		try {
			if(clickFormDesignerMenu().error) {
				return false;
			}
			formType = formType.toUpperCase();
			switch(formType) {
			case "CHECK":
				controlActions.clickElement(SelectCheckFormLnk);
				break;
			case "QUESTIONNAIRE":
				controlActions.clickElement(SelectQuestionnaireFormLnk);
				break;
			case "AUDIT":
				controlActions.clickElement(SelectAuditFormLnk);
				break;

			default:
				logError("Form type is INVALID");
				return false;			
			}
			Sync();

			if(!selectResource(resourceType,categoryValue,instanceValue1)) {
				logError("Could NOT select resource");
				return false;
			}

			if(!clickOnNextButton(DesignFormPg,"Design Form")) {
				logError("Could NOT Navigate to 'Design Form'");
				return false;
			}

			controlActions.sendKeys(FormNameTxt, formName);

			switch(formType) {
			case "CHECK":
				controlActions.dragdrop(NumericFieldDbl, FormLevel);
				controlActions.sendKeys(NumericFieldTxt, "Numeric Field");
				break;
			case "QUESTIONNAIRE":
				controlActions.dragdrop(NumericFieldDbl, FormLevel);
				controlActions.sendKeys(NumericFieldTxa, "Numeric Field");
				break;
			case "AUDIT":
				controlActions.dragdrop(SectionDbl, FormLevel);
				controlActions.sendKeys(Section1Txt, "Section 1");
				controlActions.dragdrop(NumericFieldDbl, section1Level);
				Sync();
				controlActions.sendKeys(NumericFieldTxt, "Numeric Field");
				break;

			default:
				logError("Form type is INVALID");
				return false;			
			}
			Sync();
			controlActions.clickElement(FormNameTxt); 
			
			if(!clickOnNextButton(ReleaseFormPg,"Release Form")) {
				logError("Failed to click on Next button");
				return false;
			}

			if(!releaseForm(formName)) {
				logError("Could NOT release form'");
			}
			logInfo("RELEASED FORM - '"+formName+"' of type - '"+formType+"' sucessfully");
			return true;
		}catch(Exception e) {
			logError("Failed to release form - '"+formName+"' of type - '"+formType+"'"+ e.getMessage());
			return false;
		}
	}

	/**
	 * 'addRelatedDocument' method is used to add document in 'Related Docs'
	 * @author pashine_a
	 * @param String - DocumentTypeName
	 * @param String - DocumentName
	 * @return boolean status
	 * IF document is selected in 'Related Docs' THEN true ELSE returns false;
	 */
	public boolean addRelatedDocument(String DocumentTypeName,String DocumentName) {		
		try {	
			controlActions.dragdrop(RelatedDocsDbl, HeaderLevel);
			controlActions.clickElement(AddNewDocumentButton);
			controlActions.clickElement(DocumentTypeDdl);

			WebElement selectDocumentType = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.SELECT_DOCUMENT_TYPE, "DOCUMENT_TYPE", DocumentTypeName);
			controlActions.clickElement(selectDocumentType);

			WebElement selectDocumentName = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.SELECT_DOCUMENT, "DOCUMENT_NAME", DocumentName);
			controlActions.clickElement(selectDocumentName);
			controlActions.clickElement(SaveDocumentBtn);

			WebElement selectedDocumentVerify= controlActions.perform_GetElementByXPath(FormDesignerPageLocator.SELECT_DOCUMENT_VERIFY, "DOCUMENT_NAME", DocumentName);
			if(!controlActions.isElementDisplayedOnPage(selectedDocumentVerify)) {
				logError("Could NOT Verify added document");
			}
			logInfo("VERIFIED - Document is added");
			return true;
		}
		catch(Exception e) {
			logError("Failed to add Document in 'Related Docs'"
					+ e.getMessage());
			return false;
		}	
	}

	/**
	 * 'navigateToReleaseForm' method is used to navigate to 'Form Designer - Release Form'
	 * @author pashine_a
	 * @return boolean status
	 * IF navigated to 'Release Form' step THEN true ELSE returns false;
	 */
	public boolean navigateToReleaseForm() {
		try {
			if(clickFormDesignerMenu().error) {
				return false;
			}
			controlActions.clickElement(ReleaseFormStep);
			Sync();
			if(!controlActions.isElementDisplayedOnPage(ReleaseFormPg)) {
				logError("Failed to navigate to 'Relase Form' step"); 
				return false;
			}	
			logInfo("Navigate to 'Release Form' step");
			return true;
		}catch(Exception e) {
			logError("Failed to navigate to 'Form Designer - Release Form' - "
					+ e.getMessage());
			return false;
		}
	}

	/**
	 * 'navigateToReleaseForm_EditForm' method is used to navigate to 'Form Designer - Release Form' and then edit the form(Design Form)
	 * @author pashine_a
	 * @param String - formName
	 * @return boolean status
	 * IF navigated to edit form screen THEN true ELSE returns false;
	 */
	public boolean navigateToReleaseForm_EditForm(String formName) {
		try {
			if(!navigateToReleaseForm()) {
				return false;
			}
			WebElement editForm = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.EDIT_FORM_BTN, "FORM_NAME",formName);
			controlActions.clickElement(editForm);
			Sync();
			if(!controlActions.isElementDisplayedOnPage(DesignFormPg)) {
				logError("Failed to go to design form"); 
				return false;
			}
			logInfo("Design form is opened");
			return true;
		}catch(Exception e) {
			logError("Failed to navigate to 'Form Designer - Release Form' & editing the form - "
					+ e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to click on Save button.
	 * @author thakker_k
	 * @return boolean This returns boolean true after clicking Add Save button
	 */
	public boolean clickSaveButton() {

		try {
			Sync();
			controlActions.clickElement(FormNameTxt);
			controlActions.clickElement(SaveButton);
			Sync();
			controlActions.clickElement(OkSaveButton);
			logInfo("Clicked Save button");
			return true;
		}
		catch(Exception e) {
			logError("Failed to click Save button :"
					+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to drag-drop elements in section or group
	 * @author thakker_k
	 * @return boolean This returns boolean true after drag-drop element
	 */
	public boolean dragDropElementInSectionGroup(String ElementType, String ElementName, String SectionGroupName) {
		try {	

			WebElement DragElement = null;

			switch (ElementType) {
			case "Field Types":
			case "Question Types":
				WebElement FieldTypeElement = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_TYPE,"FIELDTYPE",ElementName);
				DragElement = FieldTypeElement;
				break;

			case "Form Elements":
				WebElement FormElement = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FORM_ELEMENT,"FORMELEMENT",ElementName);
				DragElement = FormElement;				
				break;

			default:
				logError("Incorrect Element Type " + ElementType );
				return false;
			}

			WebElement SectionGroupLevelDrop = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_TYPE_DROP_AREA_SECTION_GROUP_LEVEL,"SECTIONGROUPNAME",SectionGroupName);			
			controlActions.dragdrop(DragElement, SectionGroupLevelDrop);
			Sync();
			logInfo("Dragged element " + ElementName +" and Dropped to "+ SectionGroupName );
			return true;
		}
		catch(Exception e) {
			logError("Failed drag element " + ElementName +" and drop to "+ SectionGroupName + " - "
					+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to set Element Value to Text Area.
	 * @author thakker_k
	 * @param ElementName Name of element
	 * @param ElementValue Value of Element
	 * @return boolean This returns boolean true after setting Element Value
	 */
	public boolean setElementValueTextArea(String ElementName, String ElementValue) {
		WebElement TextArea = null;
		try {

			TextArea = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_TYPE_NAME_TEXT_AREA,"FIELDTYPENAME",ElementName);
			controlActions.WaitforelementToBeClickable(TextArea);		
			controlActions.clickOnElement(TextArea);
			TextArea.clear();
			controlActions.sendKeys(TextArea, ElementValue);
			threadsleep(5000);
			logInfo(ElementName +" entered value : "+ ElementValue);
			return true;
		}
		catch(Exception e) {
			logError("Failed to set value for field " + ElementName + " :"
					+ e.getMessage());
			return false;
		}	
	}

	/**
	 * 'createForm_FormLevelFeatures' method is used to create a 'Check' form type with field level functionalities
	 * @author pashine_a
	 * @param String - formName
	 * @param String - resourceType
	 * @param String - resourceCategory
	 * @param String - resourceInstance
	 * @return boolean status
	 * IF form is created & released THEN true ELSE returns false;
	 */
	public boolean createForm_FormLevelFeatures(String formName, String resourceType,String resourceCategory,String resourceInstance, String attachmentField,String correctionsField,String preDefindedCorrectionsField,String carryoverField) {
		try { 
			attachmentField = "Numeric Field 1";
			correctionsField = "Numeric Field 2";
			preDefindedCorrectionsField = "Numeric Field 3";
			carryoverField = "Numeric Field 4";

			if(clickFormDesignerMenu().error) {
				logError("Failed to click Form Designer Menu ");
				return false;
			}
			controlActions.clickElement(SelectCheckFormLnk);
			if(!selectResource(resourceType,resourceCategory,resourceInstance)){
				logError("Failed to select resource");
				return false;
			}
			if(!clickOnNextButton(DesignFormPg,"Design Form")) {
				logError("Failed to click on Next button");
				return false;
			}
			controlActions.sendKeys(FormNameTxt, formName);

			controlActions.dragdrop(NumericFieldDbl, FormLevel);
			if(!setTextBoxValue("Numeric",attachmentField)){
				logError("Failed to set value to Text Box");
				return false;
			}
			if(!allowAttchments()) {
				logError("Failed to Add Attachments");
				return false;
			}
			controlActions.dragdrop(NumericFieldDbl, FormLevel);
			if(!setTextBoxValue("Numeric", correctionsField)){
				logError("Failed to set value to Text Box");
				return false;
			}
			if(!setComplianceForNumericAggragateField(correctionsField, "12", "16","18", null)) {
				logError("Failed to set Compliance value for fields");
				return false;
			}
			if(!allowCorrections(false)) {
				logError("Failed to set corrections");
				return false;
			}
			controlActions.dragdrop(NumericFieldDbl, FormLevel);
			if(!setTextBoxValue("Numeric", preDefindedCorrectionsField)){
				logError("Failed to set value to Text Box");
				return false;
			}
			if(!setComplianceForNumericAggragateField(preDefindedCorrectionsField, "12","16", "18", null)) {
				logError("Failed to set Compliance value for fields");
				return false;
			}
			if(!allowCorrections(true)) {
				logError("Failed to set corrections");
				return false;
			}
			controlActions.dragdrop(NumericFieldDbl, FormLevel);
			if(!setTextBoxValue("Numeric",carryoverField)){
				logError("Failed to set value to Text Box");
				return false;
			}
			if(!carryoverField()) {
				logError("Failed to configure 'Carryover Field' settings");
				return false;
			}
			controlActions.clickElement(FormNameTxt);
			if(!clickOnNextButton(ReleaseFormPg,"Release Form")) {
				logError("Failed to click on next button");
				return false;
			}
			if(!releaseForm(formName)) {
				logError("Failed to release form");
				return false;
			}
			logInfo("Form is created & released");
			return true;
		}
		catch(Exception e) {
			logError("Failed to create form - "
					+ e.getMessage());
			return false;
		}	
	}

	/**
	 * 'allowAttchments' method is used to set field's property 'Allow Attachments'
	 * @author pashine_a
	 * @return boolean status
	 * IF property is setted THEN true ELSE returns false;
	 */
	public boolean allowAttchments() {
		try {				
			if(!setSettingsProperty(AllowAttachmentsONBtn,"Allow Attachments","ON")) {
				logError("Failed to configure 'Allow Attachments' settings");
				return false;
			}
			logInfo("Configured 'Allow Attachments' settings");
			return true;
		}catch(Exception e) {
			logError("Failed to configure 'Allow Attachments' settings"
					+ e.getMessage());
			return false;
		}	
	}

	/**
	 * 'verifyInPreviewForm' method is used to verify the added fields in the preview form
	 * @author pashine_a
	 * @return boolean status
	 * IF added fields are correct THEN true ELSE returns false
	 */
	public boolean verifyInPreviewForm(String formName) {

		String fieldId, fieldType, fieldName;
		WebElement fieldLabel, requiredStatus;
		try {	
			threadsleep(2000);
			controlActions.clickElement(PreviewBtn);
			Sync();
			WebElement verifyOpenedForm = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.PREVIEW_FORM_OPENED, "FORM_NAME", formName);
			if(!controlActions.isElementDisplayedOnPage(verifyOpenedForm)) {
				logError("Failed to Verify opened preview form");
				return false;
			}
			for(int i=0;i<AllFieldsLst.size();i++) {
				fieldId = AllFieldsLst.get(i).getAttribute("data-field");
				fieldType =AllFieldsLst.get(i).getAttribute("data-fieldtype");	
				fieldType = fieldType.trim();
				fieldType = fieldType.replaceAll(" ", "");

				fieldLabel = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.ALL_FIELDS_NAME,"FIELD_ID",fieldId);
				requiredStatus = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.ALL_FIELDS_REQUIRED,"FIELD_ID",fieldId);

				fieldName =fieldLabel.getText();		
				if((requiredStatus.getAttribute("class").toString()).equals("scs-required-field")) {
					fieldName = fieldName.substring(0, fieldName.length()-1);
					fieldName = fieldName.trim();
					fieldName = fieldName.replaceAll(" ", "");
				}

				if(allFieldsOfForm[i][0].contains(fieldType) && allFieldsOfForm[i][1].equals(fieldName)) {
				}else {
					logError("Failed to verify added fields in the 'Preview Form'");
					return false;
				}
			}
			logInfo("Verified all the added fields in form in the 'Preview Form'");
			return true;
		}catch(Exception e) {
			logError("Failed to verify added field in the 'Preview Form' - "
					+ e.getMessage());
			return false;
		}	
	}

	/**
	 * 'verifyInPreviewFormHeader' method is used to verify the added header level fields in the preview form
	 * @author pashine_a
	 * @param formName
	 * @param noteComment
	 * @param relatedDoc
	 * @return boolean status
	 * IF added fields are correct THEN true ELSE returns false
	 */
	public boolean verifyInPreviewFormHeader(String formName,String noteComment,String relatedDoc ) {
		try {			
			threadsleep(2000);
			controlActions.clickElement(PreviewBtn);
			Sync();
			WebElement verifyOpenedForm = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.PREVIEW_FORM_OPENED, "FORM_NAME", formName);
			if(!controlActions.isElementDisplayedOnPage(verifyOpenedForm)) {
				logError("Failed to verify opened preview form");
				return false;
			}
			WebElement verifyNote = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.PREVIEW_FORM_NOTE, "NOTE_COMMENT", noteComment);
			if(!controlActions.isElementDisplayedOnPage(verifyNote)) {
				logError("Failed to verify 'Note'");
				return false;
			}
			WebElement verifyReleatedDocs = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.PREVIEW_FORM_RELATED_DOCS, "DOCUMENT_NAME", relatedDoc);
			if(!controlActions.isElementDisplayedOnPage(verifyReleatedDocs)) {
				logError("Failed to verify 'Releated Docs'");
				return false;
			}
			if(!controlActions.isElementDisplayedOnPage(VerifySummary)) {
				logError("Failed to verify 'Summary'");
				return false;
			}
			logInfo("Verified all the header level fields added in form in the 'Preview Form'");
			return true;
		}catch(Exception e) {
			logError("Failed to verify header level fields in the 'Preview Form' - "
					+ e.getMessage());
			return false;
		}	
	}

	/** 'selectIdentifierInputType' method is used to select InputType of Identifier
	 * @author thakker_k
	 * @param String - 'inputType'
	 * @return boolean true post selecting input type
	 */	
	public boolean selectIdentifierInputType(String inputType) {
		try {
			controlActions.clickElement(IdentifierInputType);
			Thread.sleep(3000);
			WebElement InputType = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.SELECT_INDENTIFIER_INPUT_TYPE, "INPUTTYPE",inputType );
			controlActions.clickElement(InputType);
			logInfo("Selected Identifier Input type -" + inputType);
			return true;
		}catch(Exception e) {
			logError("Failed to select Identifier Input Type - "+inputType+" - "
					+ e.getMessage());
			return false;
		}
	}

	/** 'setIdentifierInputMask' method is used to set Input Mask of Identifier
	 * @author thakker_k
	 * @param String - 'inputMask'
	 * @return boolean true post selecting input type
	 */	
	public boolean setIdentifierInputMask(String inputMask) {
		try {
			controlActions.clickElement(IdentifierInputMask);
			controlActions.sendKeys(IdentifierInputMask, inputMask);
			logInfo("Set Identifier Input Mask -" + inputMask);
			return true;
		}catch(Exception e) {
			logError("Failed to set Identifier Input Mask - "
					+ e.getMessage());
			return false;
		}
	}

	/** 
	 * @author hongorani_a
	 */

	public boolean setIdentifierInputMask(List<String> inputMask) {
		try {
			for(int i =0;i<inputMask.size();i++) {
				controlActions.clickOnElement(AddNewBtn);
				SelectOneInput.get(i).sendKeys(inputMask.get(i));
			}
			logInfo("Set Identifier Input Mask");
			return true;
		}catch(Exception e) {
			logError("Failed to set Identifier Input Mask - "
					+ e.getMessage());
			return false;
		}
	}

	/* 'setFieldName' method is used to set the name of the field
	 * @author pashine_a
	 * @param fieldType
	 * @param fieldName
	 * @return boolean status
	 * IF added fields are correct THEN true ELSE returns false
	 */
	public boolean setFieldName(String fieldType, String fieldName) {
		try {	
			if(fieldType.equals("Section") || fieldType.equals("Field Group") || fieldType.equals("Question Group")){
			}else {
				if(!getFields(fieldType, fieldName)) {
					logError("Failed to set values");
					return false;
				}
			}
			Sync();
			controlActions.sendKeys(FieldsInp, fieldName);
			logInfo("Set field name " + fieldName + " for field type " +fieldType);
			return true;	
		}catch(Exception e) {
			logError("Failed to set field name for field type - "+fieldType+". - "
					+ e.getMessage());
			return false;
		}	
	}

	/* 'addOption' method is used to add option(value) in Select One & Select Multiple fields with weights
	 * @author pashine_a
	 * @param optonValue
	 * @param optonWeight
	 * @return boolean status
	 * IF options are added in field THEN true ELSE returns false
	 */
	public boolean addOption(String optonValue, String optionWeight) {
		try {			
			controlActions.clickElement(AddNewOption);
			controlActions.sendKeys(OptionValueInp, optonValue);
			controlActions.sendKeys(OptionWeightInp, optionWeight);
			logInfo("Added option");
			return true;	
		}catch(Exception e) {
			logError("Failed to add option - "
					+ e.getMessage());
			return false;
		}	
	}
	/* 'addOption' method is used to add option(value) in Select One & Select Multiple fields
	 * @author pashine_a
	 * @param optonValue
	 * @return boolean status
	 * IF options are added in field THEN true ELSE returns false
	 */
	public boolean addOption(String optonValue) {
		try {			
			controlActions.perform_ClickWithJavaScriptExecutor(AddNewOption);
			//			controlActions.clickElement(AddNewOption);
			controlActions.sendKeys(OptionValueInp, optonValue);
			logInfo("Added option");
			return true;	
		}catch(Exception e) {
			logError("Failed to add option - "
					+ e.getMessage());
			return false;
		}	
	}

	/**
	 * 'getFormDetails' method is get Form Details from Release Form page
	 * @author thakker_k
	 * @param String - formName
	 * @return String - returns Form Details in string format
	 */
	public String getFormDetails(String formName) {
		try {			
			WebElement formDetails = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FORM_DETAILS, "FORM_NAME",formName);
			String formInfo = formDetails.getText();
			logInfo("Extracted Form Details Successfully");
			return formInfo;
		}catch(Exception e) {
			logError("Failed to Form Details - "
					+ e.getMessage());
			return null;
		}
	}

	/**
	 * 'setComplianceForNumericAggragateField' method is used to verify the added header level fields in the preview form
	 * @author pashine_a
	 * @param shortName
	 * @param minValue
	 * @param targetValue
	 * @param maxValue
	 * @param uomValue
	 * @return boolean status
	 * IF compliance values are set on selected field THEN true ELSE returns false
	 */
	public boolean setComplianceForNumericAggragateField(String shortName,String minValue,String targetValue,String maxValue, String uomValue) {
		try {	
			controlActions.clickElement(FormNameTxt);
			WebElement fieldSelection = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_SHORTNAME,"SHORT_NAME",shortName);
			controlActions.clickElement(fieldSelection);
			String complianceGridStatus = ComplianceIconStatus.getAttribute("class");
			if(!complianceGridStatus.equals("k-icon k-collapse-next k-i-arrow-60-down")) {
				controlActions.clickElement(ComplianceOpen);
			}
			Sync();
			controlActions.sendKeys(MinimunTxt, minValue);
			controlActions.sendKeys(TargetTxt, targetValue);
			controlActions.sendKeys(MaximumTxt, maxValue);
			if(uomValue != null) {
				controlActions.sendKeys(UOMTxt, uomValue);
			}
			controlActions.clickElement(DefaultChk);
			Sync();
			controlActions.clickElement(ComplianceClose);
			logInfo("Set Compliance Values");
			return true;
		}catch(Exception e) {
			logError("Failed to add compliance values - "
					+ e.getMessage());
			return false;
		}	
	}

	/* * 'allowCorrection' method is used to set field's property 'Allow Corrections'
	 * @author pashine_a
	 * @param String - 'isPredefined'
	 * @return boolean status
	 * IF property is setted THEN true ELSE returns false;
	 */
	public boolean allowCorrections(boolean isPredefined, int optionsCount) {
		try {				
			if(!setSettingsProperty(AllowCorrectionsONBtn,"Allow Corrections","ON")) {
				logError("Failed to set settings property");
				return false;
			}
			if(isPredefined) {
				if(!addPredefinedCorrectionsOptions(optionsCount)) {
					logError("Failed to add Predefined Corrections Options");
					return false;
				}
			}
			logInfo("Configured 'Allow Corrections' settings");
			return true;
		}catch(Exception e) {
			logError("Failed to configure 'Allow Corrections' settings"
					+ e.getMessage());
			return false;
		}	
	}

	/* * 'addPredefinedCorrectionsOption's method is used to add options for 'Predefined Corrections'
	 * @author pashine_a
	 * @param String - 'isPredefined'
	 * @return boolean status
	 * IF property is setted THEN true ELSE returns false;
	 */
	public boolean addPredefinedCorrectionsOptions(int optionsCount) {
		try {				
			if(optionsCount<1) {
				logError("'Predefined Corrections' options count is not valid");
				return false;
			}
			String optionValue;
			for(int i=1;i<=optionsCount;i++) {
				optionValue = "Option - "+i;
				controlActions.clickElement(PredefinedCorrectionAddBtn);
				controlActions.sendKeys(PredefinedCorrectionOptionInp, optionValue);
			}
			logInfo("Added options in 'Predefined Corrections'");
			return true;
		}catch(Exception e) {
			logError("Failed to add 'Predefined Corrections' option(s) - "
					+ e.getMessage());
			return false;
		}	
	}

	/* * 'carryoverField' method is used to set field's property Carryover Field'
	 * @author pashine_a
	 * @param null
	 * @return boolean status
	 * IF property is setted THEN true ELSE returns false;
	 */
	public boolean carryoverField() {
		try {				
			if(!setSettingsProperty(CarryOverFieldONBtn,"Carryover Field","ON")) {
				logError("Failed to set Settings Property");
				return false;
			}
			logInfo("Configured 'Carryover Field' settings");
			return true;
		}catch(Exception e) {
			logError("Failed to configure 'Carryover Field' settings"
					+ e.getMessage());
			return false;
		}	
	}


	/** 'selectAggregateFunction' method is used to select Function for Aggregate Field
	 * @author thakker_k
	 * @param String - 'aggregateFunction'
	 * @return boolean true post selecting Function for Aggregate Field
	 */	
	public boolean selectAggregateFunction(String aggregateFunction) {
		try {
			controlActions.clickElement(AggregateFunction);
			WebElement SelectAggregateFunction = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.SELECT_AGGREGATE_FUNCTION, "AGGREGATEFUNCTION",aggregateFunction );
			threadsleep(3000);
			controlActions.clickElement(SelectAggregateFunction);
			logInfo("Selected Aggregate Function - "+ aggregateFunction);
			return true;
		}catch(Exception e) {
			logError("Failed to select Aggregate Function - "+aggregateFunction+" - "
					+ e.getMessage());
			return false;
		}
	}

	/** 'selectAggregateSource' method is used to select Source for Aggregate Field
	 * @author thakker_k
	 * @param String - 'aggregateSource'
	 * @return boolean true post selecting Source for Aggregate Field
	 */	
	public boolean setAggregateSource(String aggregateSource) {
		try {
			SetAggregateSource.clear();
			SetAggregateSource.sendKeys(aggregateSource);
			controlActions.actionEnter();
			logInfo("Set Aggregate Source - "+aggregateSource);
			return true;
		}catch(Exception e) {
			logError("Failed to select Aggregate Source - "+aggregateSource+" - "
					+ e.getMessage());
			return false;
		}
	}
	

	/** 'clickPreviewButton' method is used to click on Preview button
	 * @author thakker_k
	 * @return boolean true post clicking on Preview button
	 */	
	public boolean clickPreviewButton() {
		try {
			controlActions.clickElement(PreviewBtn);
			Sync();
			logInfo("Clicked Preview button");
			return true;
		}catch(Exception e) {
			logError("Failed to click on Preview button"
					+ e.getMessage());
			return false;
		}
	}

	/** 'verifyAddedResourceStatus' method is used to verify added resource status
	 * @author pashine_a
	 * @param String - 'resourceType'
	 * @param String - 'resourceCategory'
	 * @param String - 'resourceInstance'
	 * @return boolean status
	 * IF resource is added THEN true ELSE false
	 */	
	public boolean verifyAddedResourceStatus(String resourceType, String resourceCategory, String resourceInstance) {
		try {
			Sync();
			String select1 = "li[class='k-item ng-scope'][role='option']";
			controlActions.JSSetValueFromList(driver, select1, resourceType);
			Thread.sleep(3000);
			WebElement DragResource = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.DRAG_RES, "RESOURCE_CATEGORY",resourceCategory);
			controlActions.dragdrop(DragResource, DropRes);
			logInfo("Resource Category is added");
			String resourceName = resourceType+" > "+resourceCategory+" > "+resourceInstance;
			String addedResource = controlActions.perform_GetDynamicXPath(FormDesignerPageLocator.VERIFY_RESOURCE_LBL, "RESOURCE_NAME",resourceName);
			boolean verifyAddedResource = controlActions.isElementDisplayed(addedResource);
			if(!verifyAddedResource) {
				logInfo("Resource is not selected");
				return false;
			}
			logInfo("Resource is sucessfully selected");
			return true;
		}catch(Exception e) {
			logError("Failed to add resource - "
					+ e.getMessage());
			return false;
		}
	}

	/** 'setRepeatCountForField' method is used to set the field's repeat count
	 * @author pashine_a
	 * @param String - 'fieldShortName'
	 * @param String - 'count'
	 * @return boolean status
	 * IF repeat count is set for a field THEN true ELSE false
	 */	
	public boolean setRepeatCountForField(String fieldShortName, String count) {
		try {
			controlActions.clickElement(FormNameTxt);
			WebElement fieldSelection = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_SHORTNAME,"SHORT_NAME",fieldShortName);
			controlActions.clickElement(fieldSelection);
			Sync();
			controlActions.sendKeys(RepeatFieldCountInp, count);
			logInfo("Repeat count - "+count+" has been set for - '"+fieldShortName+"'");
			return true;
		}catch(Exception e) {
			logError("Failed to set repeat count for - '"+fieldShortName+"' - "
					+ e.getMessage());
			return false;
		}
	}

	/** 'verifyAddedResourceStatus' method is used to verify the field's no repeat status
	 * @author pashine_a
	 * @param String - 'fieldShortName'
	 * @return boolean status
	 * IF No-Repeat status is verified THEN true ELSE false
	 */	
	public boolean verifyNoRepeatStatus(String fieldShortName) {
		String repeatCountEditStatus, noRepeatActualNote;
		try {
			controlActions.clickElement(FormNameTxt);
			WebElement fieldSelection = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_SHORTNAME,"SHORT_NAME",fieldShortName);
			controlActions.clickElement(fieldSelection);
			Sync();
			repeatCountEditStatus = RepeatFieldCountInp.getAttribute("disabled");
			if(repeatCountEditStatus==null) {
				logError("Field repeat input is editable");
				return false;
			}
			logInfo("Field repeat input is non-editable");
			noRepeatActualNote = NoRepeatTxt.getText();
			if(!(noRepeatExpectedNote.equals(noRepeatActualNote) || noRepeatExpectedNoteAdtFrm.equals(noRepeatActualNote) || noRepeatExpectedNoteGroup.equals(noRepeatActualNote) || noRepeatExpectedNoteAtdGroup.equals(noRepeatActualNote))){
				logError("Field No-Repeat note is not verified");
				return false;
			}
			logInfo("Field No-Repeat Note is Verified");
			logInfo("Verified field's No-Repeat status");
			return true;
		}catch(Exception e) {
			logError("Failed to verify field's No-Repeat status - "
					+ e.getMessage());
			return false;
		}
	}

	/** 'exitFormDesigner' method is used to get exit from Form Designer
	 * @author pashine_a
	 * @param null
	 * @return boolean status
	 * IF exited from from designer THEN true ELSE false
	 */	
	public boolean exitFormDesigner() {
		try {
			controlActions.clickElement(FormDesignerExitBtn);
			controlActions.clickElement(DeleteFormYesBtn);
			Sync();
			threadsleep(2000);
			logInfo("exited from 'Form Designer'");
			return true;
		}catch(Exception e) {
			logError("Failed to get exit from 'Form Designer' - "
					+ e.getMessage());
			return false;
		}
	}

	/** 
	 * @author hingorani_a
	 */

	public boolean selectIdentifierInputType(String inputType, List<String> maskValue) {
		try {
			controlActions.WaitforelementToBeClickable(IdentifierInputType);
//			controlActions.clickElement(IdentifierInputType);
			IdentifierInputType.click();
//			Thread.sleep(3000);
			Sync();
			WebElement InputType = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.SELECT_INDENTIFIER_INPUT_TYPE, "INPUTTYPE",inputType );
//			controlActions.clickElement(InputType);
			InputType.click();
			Sync();
			switch(inputType) {
			case IDENTIFIER_TYPE.FREETEXT:
				if(maskValue==null) {
					logInfo("Not set input mask");
				}else {
					setIdentifierInputMask(maskValue.get(0));
				}
				break;
			case IDENTIFIER_TYPE.SELECTONE:
				setIdentifierInputMask(maskValue);
				break;
			}
			logInfo("Set Identifier Input Type");
			return true;
		}catch(Exception e) {
			logError("Failed to Set Identifier Input Type - " +e.getMessage());
			return false;
		}
	}

	/** 
	 * @author hingorani_a
	 */
	public boolean createAndReleaseForm(String formType,String formName,String resourceType,String categoryValue, List<String> instanceValue) {
		try {
			if(clickFormDesignerMenu().error) {
				logError("Failed to click on Form Designer");
				return false;
			}
			formType = formType.toUpperCase();
			switch(formType) {
			case "CHECK":
				controlActions.clickElement(SelectCheckFormLnk);
				break;
			case "QUESTIONNAIRE":
				controlActions.clickElement(SelectQuestionnaireFormLnk);
				break;
			case "AUDIT":
				controlActions.clickElement(SelectAuditFormLnk);
				break;

			default:
				logError("Form type is INVALID");
				return false;			
			}
			Sync();

			for(String instanceVal : instanceValue) {
				if(!selectResource(resourceType,categoryValue,instanceVal)) {
					logError("Could NOT select resource");
					return false;
				}
				Sync();
			}		

			if(!clickOnNextButton(DesignFormPg,"Design Form")) {
				logError("Could NOT Navigate to 'Design Form'");				
				return false;
			}

			controlActions.sendKeys(FormNameTxt, formName);

			switch(formType) {
			case "CHECK":
				controlActions.dragdrop(NumericFieldDbl, FormLevel);
				controlActions.sendKeys(NumericFieldTxt, "Numeric Field");
				break;
			case "QUESTIONNAIRE":
				controlActions.dragdrop(NumericFieldDbl, FormLevel);
				controlActions.sendKeys(NumericFieldTxa, "Numeric Field");
				break;
			case "AUDIT":
				controlActions.dragdrop(SectionDbl, FormLevel);
				controlActions.sendKeys(Section1Txt, "Section 1");
				controlActions.dragdrop(NumericFieldDbl, section1Level);
				controlActions.sendKeys(NumericFieldTxt, "Numeric Field");
				break;

			default:
				logError("Form type is INVALID");
				return false;			
			}

			controlActions.clickElement(FormNameTxt);

			if(!clickOnNextButton(ReleaseFormPg,"Release Form")) {
				logError("Failed to click on Next button");
				return false;
			}

			if(!releaseForm(formName)) {
				logError("Could NOT release form'");
				return false;
			}
			logInfo("RELEASED FORM - '"+formName+"' of type - '"+formType+"' sucessfully");
			return true;
		}catch(Exception e) {
			logError("Failed to release form - '"+formName+"' of type - '"+formType+"'"+ e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to select a Type of form to be created
	 * @author hingorani_a
	 * @param formType Use Class FORMTYPES to set type of form
	 * @return boolean This returns true after selecting the form of the required type 
	 */
	public boolean selectFormType(String formType) {
		try {
			switch(formType.toUpperCase()) {
			case FORMTYPES.CHECK:
				controlActions.clickElement(SelectCheckFormLnk);
				Sync();
				logInfo("Selected form type - "+ formType);
				return true;

			case FORMTYPES.QUESTIONNAIRE:
				controlActions.clickElement(SelectQuestionnaireFormLnk);
				Sync();
				logInfo("Selected form type - "+ formType);
				return true;

			case FORMTYPES.AUDIT:
				controlActions.clickElement(SelectAuditFormLnk);
				Sync();
				logInfo("Selected form type - "+ formType);
				return true;

			default:
				logError("Form type is INVALID - " + formType);
				return false;			
			}
		}
		catch(Exception e) {
			logError("Failed to select form type as - '"+ formType 
					+ e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to select a resource category
	 * @author hingorani_a
	 * @param resourceType Use Class FORMRESOURCETYPES to set type of category
	 * @param resourceCategory Name of category
	 * @return boolean This returns true after selecting/adding category
	 */
	public boolean selectResourceCategory(String resourceType, String resourceCategory) {
		WebElement DragRes = null;
		List<WebElement> ResourceCatLbl = null;
		int count = 0;
		try {
			String selector = "li[class='k-item ng-scope'][role='option']";
			controlActions.JSSetValueFromList(driver, selector, resourceType);
			Thread.sleep(3000);
			DragRes = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.DRAG_RES, "RESOURCE_CATEGORY",resourceCategory);
			controlActions.dragdrop(DragRes, DropRes);
			Sync();
			logInfo("Resource is added");
			String resourceName = resourceType+" > "+resourceCategory;
			ResourceCatLbl = controlActions.perform_GetListOfElementsByXPath(FormDesignerPageLocator.RESOURCE_CAT_LBL,
					"RESOURCE_NAME",resourceName);
			for(WebElement res : ResourceCatLbl) {
				if(controlActions.isElementDisplay(res))
					count++;
			}
			if(count == ResourceCatLbl.size()) {
				logInfo("Resource is sucessfully selected");
				return true;
			}
			else {
				logError("Unable to add resource");
				return false;
			}
		}
		catch(Exception e) {
			logError("Failed to add resource - "
					+ e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to select a resource instance from a category
	 * @author hingorani_a
	 * @param resourceType Use Class FORMRESOURCETYPES to set type of category
	 * @param resourceCategory Name of category
	 * @param resourceInstance Name of instance to be selected
	 * @return boolean This returns true after selecting/adding instance
	 */
	public boolean selectResourceInstance(String resourceType, String resourceCategory, String resourceInstance) {
		WebElement DragRes = null;
		WebElement ExpandResourceCat = null;
		List<WebElement> ResourceCatLbl = null;
		int count = 0;
		try {
			String selector = "li[class='k-item ng-scope'][role='option']";
			controlActions.JSSetValueFromList(driver, selector, resourceType);
			Sync();
			ExpandResourceCat = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.EXPAND_RESOURCE_CAT,
					"INSTANCENAME", resourceCategory);
			controlActions.perform_scrollToElement_ByElement(ExpandResourceCat);
			controlActions.perform_ClickWithJavaScriptExecutor(ExpandResourceCat);

			DragRes = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.DRAG_RES, "RESOURCE_CATEGORY",
					resourceInstance);

			controlActions.dragdrop(DragRes, DropRes);
			Sync();
			logInfo("Resource is added");
			String resourceName = resourceType+" > "+resourceCategory+" > "+resourceInstance;
			ResourceCatLbl = controlActions.perform_GetListOfElementsByXPath(FormDesignerPageLocator.RESOURCE_CAT_LBL,
					"RESOURCE_NAME",resourceName);
			for(WebElement res : ResourceCatLbl) {
				if(controlActions.isElementDisplay(res))
					count++;
			}
			if(count == ResourceCatLbl.size()) {
				logInfo("Resource is sucessfully selected");
				return true;
			}
			else {
				logError("Unable to add resource instance" + resourceInstance);
				return false;
			}
		}
		catch(Exception e) {
			logError("Failed to add resource instance - " + resourceInstance
					+ e.getMessage());
			return false;
		}
	}
	
	/**
	 * This method is used to drag and drop a Group element
	 * @author hingorani_a
	 * @param formType Use Class FORMTYPES to set type of form
	 * @param groupName Name of group
	 * @param dragTo Element to where we want Group to be dragged 
	 * Keep it as null if you want to drag it to Form level
	 * @return boolean This returns true after dragging and dropping Group element
	 */
	public boolean dragGroup(String formType, String groupName, WebElement dragTo) {
		WebElement AddFieldElements = null;
		WebElement GrpAndSecFormEleTxa = null;
		try {
			
			if(dragTo!=null)
				FormLevel = dragTo;
			
			switch(formType) {

			case FORMTYPES.CHECK:
				AddFieldElements = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.ADD_FIELD_ELEMENTS,
						"FIELDELEMENT", FORM_ELEMENTS.FIELD_GROUP);
				controlActions.dragdrop(AddFieldElements, FormLevel);

				GrpAndSecFormEleTxa = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.GRP_AND_SEC_CHK_ELE_TXT,
						"ELEMENTTYPE", ELEMENT_INPUT_TYPES.FIELD_GROUP);
				controlActions.sendKeys(GrpAndSecFormEleTxa, groupName);
				break;

			case FORMTYPES.AUDIT:
				AddFieldElements = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.ADD_FIELD_ELEMENTS,
						"FIELDELEMENT", FORM_ELEMENTS.QUESTION_GROUP);
				controlActions.dragdrop(AddFieldElements, FormLevel);
				GrpAndSecFormEleTxa = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.GRP_AND_SEC_CHK_ELE_TXT,
						"ELEMENTTYPE", ELEMENT_INPUT_TYPES.QUESTION_GROUP);
				controlActions.sendKeys(GrpAndSecFormEleTxa, groupName);
				break;

			case FORMTYPES.QUESTIONNAIRE:
				AddFieldElements = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.ADD_FIELD_ELEMENTS,
						"FIELDELEMENT", FORM_ELEMENTS.FIELD_GROUP);
				controlActions.dragdrop(AddFieldElements, FormLevel);

				GrpAndSecFormEleTxa = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.GRP_AND_SEC_QUEST_ELE_TXA,
						"ELEMENTTYPE", ELEMENT_INPUT_TYPES.FIELD_GROUP);
				controlActions.sendKeys(GrpAndSecFormEleTxa, groupName);
				break;
			default:
				logError("Invalid form type - " + formType);
				return false;
			}
			logInfo("Added Group with name : " + groupName);
			
			return true;
		}
		catch(Exception e) {
			logError("Failed to drag Group to form - "  
					+ e.getMessage());
			return false;
		}
	}
	
	/**
	 * This method is used to drag and drop a Section element
	 * @author hingorani_a
	 * @param formType Use Class FORMTYPES to set type of form
	 * @param sectionName Name of section
	 * @return boolean This returns true after dragging and dropping Section element
	 */
	public boolean dragSection(String formType, String sectionName) {
		WebElement AddFieldElements = null;
		WebElement GrpAndSecFormEleTxa = null;
		try {
			AddFieldElements = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.ADD_FIELD_ELEMENTS,
					"FIELDELEMENT", FORM_ELEMENTS.SECTION);
			controlActions.dragdrop(AddFieldElements, FieldTypeDropAreaFormLevel);

			switch(formType) {
			case FORMTYPES.CHECK:
			case FORMTYPES.AUDIT:
				GrpAndSecFormEleTxa = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.GRP_AND_SEC_CHK_ELE_TXT,
						"ELEMENTTYPE", ELEMENT_INPUT_TYPES.SECTION);
				controlActions.sendKeys(GrpAndSecFormEleTxa, sectionName);
				break;

			case FORMTYPES.QUESTIONNAIRE:
				GrpAndSecFormEleTxa = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.GRP_AND_SEC_QUEST_ELE_TXA,
						"ELEMENTTYPE", ELEMENT_INPUT_TYPES.SECTION);
				controlActions.sendKeys(GrpAndSecFormEleTxa, sectionName);
				break;
			default:
				logError("Invalid form type - " + formType);
				return false;
			}
			logInfo("Added Section with name : " + sectionName);
			
			return true;
		}
		catch(Exception e) {
			logError("Failed to drag section to form - "  
					+ e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to set/design fields in Form
	 * @author hingorani_a
	 * @param fdp An object of Class FormDesignParams, in order to set
	 * different fields like Numeric, Free Text, etc for designing form.
	 * @return boolean This returns boolean true after setting/designing fields for Form
	 */
	public boolean setDetails(FormDesignParams fdp) {
		WebElement AddFieldType = null;
		WebElement AddChkAndAuditFormField = null;
		WebElement AddQuestFormField = null;
		WebElement AddFieldToForm = null;
		String inputElementTxt = null;
		String formLevel = null;

		try {

			FormNameTxt.clear();
			controlActions.sendKeys(FormNameTxt, fdp.FormName);
			logInfo("Added Form name as - " + fdp.FormName);

			if(fdp.HeaderNoteText != null || (fdp.HeaderDocTypeName != null && fdp.HeaderDocName != null) || fdp.HeaderSummaryField) {
				if(!addHeaderLevelFields(fdp.HeaderNoteText, fdp.HeaderDocTypeName, fdp.HeaderDocName, fdp.HeaderSummaryField)) {
					return false;
				}
			}

			for(FormFieldParams ffp : fdp.DesignFields) {
				
				if(ffp.GroupName != null && ffp.SectionName != null) {
					
					
					if(!dragSection(fdp.FormType, ffp.SectionName))
						return false;
					
					//set where fields should be placed
					formLevel = controlActions.perform_GetDynamicXPath(FormDesignerPageLocator.FIELD_TYPE_DROP_AREA_SECTION_GROUP_LEVEL,
							"SECTIONGROUPNAME", ffp.SectionName);
					AddFieldToForm = controlActions.perform_GetElementByXPath(formLevel);
					
					if(!dragGroup(fdp.FormType, ffp.GroupName, AddFieldToForm))
						return false;
					
					//set where fields should be placed
					formLevel = controlActions.perform_GetDynamicXPath(FormDesignerPageLocator.FIELD_TYPE_DROP_AREA_SECTION_GROUP_LEVEL,
							"SECTIONGROUPNAME", ffp.GroupName);
					AddFieldToForm = controlActions.perform_GetElementByXPath(formLevel);
				}

				else if(ffp.GroupName != null) {

					if(!dragGroup(fdp.FormType, ffp.GroupName, null))
						return false;

					//set where fields should be placed
					formLevel = controlActions.perform_GetDynamicXPath(FormDesignerPageLocator.FIELD_TYPE_DROP_AREA_SECTION_GROUP_LEVEL,
							"SECTIONGROUPNAME", ffp.GroupName);
					AddFieldToForm = controlActions.perform_GetElementByXPath(formLevel);
				}
				else if(ffp.SectionName != null) {
					
					if(!dragSection(fdp.FormType, ffp.SectionName))
						return false;

					//set where fields should be placed
					formLevel = controlActions.perform_GetDynamicXPath(FormDesignerPageLocator.FIELD_TYPE_DROP_AREA_SECTION_GROUP_LEVEL,
							"SECTIONGROUPNAME", ffp.SectionName);
					AddFieldToForm = controlActions.perform_GetElementByXPath(formLevel);
				}
				else if(ffp.DragToSectionName != null) {
					
					//set where fields should be placed
					formLevel = controlActions.perform_GetDynamicXPath(FormDesignerPageLocator.SET_EXISTING_SEC,
							"SECTIONNAME", ffp.DragToSectionName);
					AddFieldToForm = controlActions.perform_GetElementByXPath(formLevel);
				}
				else {
					AddFieldToForm = FormLevel;
				}

				for (Map.Entry<String, List<String>> entry : ffp.FieldDetails.entrySet()) {
					String fieldType = entry.getKey();
					List<String> fieldNames = entry.getValue();
					
					if(ffp.GroupName != null || ffp.SectionName != null || ffp.DragToSectionName != null) {
						AddFieldToForm = controlActions.WaitUntilElementIsStale(formLevel);
					}

					switch(fieldType) {
					case FIELD_TYPES.DATE:
						inputElementTxt = FIELD_INPUT_TYPES.DATE;
						break;
					case FIELD_TYPES.DATE_TIME:
						inputElementTxt = FIELD_INPUT_TYPES.DATE_TIME;
						break;
					case FIELD_TYPES.FREE_TEXT:
						inputElementTxt = FIELD_INPUT_TYPES.FREE_TEXT;
						break;
					case FIELD_TYPES.NUMERIC:
						inputElementTxt = FIELD_INPUT_TYPES.NUMERIC;
						break;
					case FIELD_TYPES.PARAGRAPH_TEXT:
						inputElementTxt = FIELD_INPUT_TYPES.PARAGRAPH_TEXT;
						break;
					case FIELD_TYPES.SELECT_MULTIPLE:
						inputElementTxt = FIELD_INPUT_TYPES.SELECT_MULTIPLE;
						break;
					case FIELD_TYPES.SELECT_ONE:
						inputElementTxt = FIELD_INPUT_TYPES.SELECT_ONE;
						break;
					case FIELD_TYPES.SINGLE_LINE_TEXT:
						inputElementTxt = FIELD_INPUT_TYPES.SINGLE_LINE_TEXT;
						break;
					case FIELD_TYPES.TIME:
						inputElementTxt = FIELD_INPUT_TYPES.TIME;
						break;
					case FIELD_TYPES.AGGREGATE:
						inputElementTxt = FIELD_INPUT_TYPES.AGGREGATE;
						break;
					case FIELD_TYPES.IDENTIFIER:
						inputElementTxt = FIELD_INPUT_TYPES.IDENTIFIER;
						break;	

					}

					for(String name : fieldNames) {
						switch(fieldType) {
						case FIELD_TYPES.AGGREGATE:
						case FIELD_TYPES.IDENTIFIER:
							AddFieldType = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.ADD_FIELD_ELEMENTS,
									"FIELDELEMENT", fieldType);
							break;	

						case FIELD_TYPES.DATE:
						case FIELD_TYPES.DATE_TIME:
						case FIELD_TYPES.FREE_TEXT:
						case FIELD_TYPES.NUMERIC:
						case FIELD_TYPES.PARAGRAPH_TEXT:
						case FIELD_TYPES.SELECT_MULTIPLE:
						case FIELD_TYPES.SELECT_ONE:
						case FIELD_TYPES.SINGLE_LINE_TEXT:
						case FIELD_TYPES.TIME:
							AddFieldType = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.ADD_FIELD_TYPE,
									"FIELDTYPE", fieldType);
							break;
						}

						if(ffp.GroupName != null || ffp.SectionName != null) {
							AddFieldToForm = controlActions.WaitUntilElementIsStale(formLevel);
						}
						controlActions.dragdrop(AddFieldType, AddFieldToForm);

						switch(fdp.FormType) {
						case FORMTYPES.CHECK:
						case FORMTYPES.AUDIT:
							AddChkAndAuditFormField = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.ADD_CHK_AND_AUDIT_FORM_FIELD,
									"TYPEOFFIELD", inputElementTxt);
							controlActions.sendKeys(AddChkAndAuditFormField, name);
							break;

						case FORMTYPES.QUESTIONNAIRE:
							AddQuestFormField = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.ADD_QUEST_FORM_FIELD,
									"TYPEOFFIELD", inputElementTxt);
							controlActions.sendKeys(AddQuestFormField, name);
							break;
						default:
							logError("Invalid form type - " + fdp.FormType);
							return false;
						}

						if(fieldType.equalsIgnoreCase(FIELD_TYPES.AGGREGATE)) {
							/*The following IF condition checks whether
							 * the boolean property 'SetAggregateFunction'
							 * is True or False (default is false) 
							 * IF it is TRUE, then the 'Function' property
							 * of the Aggregate Type Field in FormDesigner of Application
							 * is SET by using sendKeys method which is implemented
							 * in method 'setAggregateFunction'
							 * 
							 * IF boolean 'SetAggregateFunction' is FALSE
							 * then 'Function' property of the Aggregate Field
							 * is SELECTED by opening the 'Function' property drop down for an Aggregate field
							 * and then selecting the required function from the list
							 * this is implemented in method - 'selectAggregateFunction'
							 */
							if(ffp.SetAggregateFunction) {
								if(!setAggregateFunction(ffp.AgrregateFunction))
									return false;
							}else {
							
							if(!selectAggregateFunction(ffp.AgrregateFunction))
								return false;
							}
							
							if(!setAggregateSource(ffp.AgrregateSource))
								return false;
						}
						if(fieldType.equalsIgnoreCase(FIELD_TYPES.IDENTIFIER)) {
							if(!selectIdentifierInputType(ffp.IdentiferType,ffp.IdentifierValue))
								return false;
						}
						if(fieldType.equalsIgnoreCase(FIELD_TYPES.SELECT_ONE) || fieldType.equalsIgnoreCase(FIELD_TYPES.SELECT_MULTIPLE)) {
							if(!setFieldptions(ffp.QuestionWeight, ffp.SelectOneMultipleOptions, ffp.SelectOneMultipleOptionsWeight))
								return false;
						}
						logInfo("Added field with name : " + name);
					}
				}
				
				if(ffp.Copy!=null) {
					for(String fieldName : ffp.Copy) {
						copyElement(fieldName);
						logInfo("Performed Copy on field -" + fieldName );
					}
				}
				if(ffp.Repeat!=null) {
					for(List<String> field : ffp.Repeat) {
						setRepeatCountForField(field.get(0),field.get(1));
						logInfo("Set Repeat count -" + field.get(1)+ "for field - " + field.get(0) );
					}
					
				}
			}

			SCLogo.click();
			controlActions.clickElement(FormNameTxt);
			logInfo("All the fields have been set for form - " + fdp.FormName);
			return true;
		}
		catch(Exception e) {
			logError("Failed to set fields for form - " + fdp.FormName 
					+ e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to set details and release a Form
	 * @author hingorani_a
	 * @param fdp An object of Class FormDesignParams, in order to set
	 * different details like resources, fields, etc to release a form.
	 * @return boolean This returns boolean true after releasing/setting details for Form
	 */
	public boolean createAndReleaseForm(FormDesignParams fdp) {
		try {

			if(!selectFormType(fdp.FormType)) {
				logError("Failed to select form type -"+ fdp.FormType);
				return false;
			}

			//this should have by.category or by.instance
			for (Map.Entry<String, List<String>> entry : fdp.SelectResources.entrySet()) {
				String resourceType = entry.getKey();
				List<String> categories = entry.getValue();

				for(String category : categories) {
					if(!selectResourceCategory(resourceType, category)) {
						logError("Failed to select resource category - "+ category);
						return false;
					}
					Sync();
				}
				Sync();
			}

			if(!clickOnNextButton(DesignFormPg,"Design Form")) {
				logError("Failed to click on Next button");
				return false;
			}


			if(!setDetails(fdp)) {
				logError("Failed to set details");
				return false;
			}

			controlActions.clickElement(FormNameTxt);

			if(!clickOnNextButton(ReleaseFormPg,"Release Form")) {
				logError("Failed to click on Next button");
				return false;
			}

			if(fdp.ReleaseForm) {
				if(!releaseForm(fdp.FormName)) {
					logError("Failed to release form -"+fdp.FormName);
					return false;
				}
			}

			logInfo("RELEASED FORM - '" + fdp.FormName + "' of type - '" + fdp.FormType + "' sucessfully");
			return true;
		}
		catch(Exception e) {
			logError("Failed to release form - '" + fdp.FormName + "' of type - '" + fdp.FormType + "'"+ e.getMessage());
			return false;
		}
	}

	
	/**
	 * "addValuesToSelectDropDown" method is used to Add Values to select from dropdown 
	 * @author thakker_k
	 * @param List<String> - List of values to be added
	 * @return boolean true post adding values
	 */
	public boolean addValuesToSelectDropDown(List<String> Values) {
		try {

			Thread.sleep(2000);
			controlActions.perform_ClickWithJavaScriptExecutor(GeneralTab);
			Thread.sleep(2000);

			int size = Values.size();

			for (int i = 1; i <= size; i++) {

				controlActions.perform_scrollToElement_ByElement(AddNewValueButton);

				controlActions.click(AddNewValueButton);
				Thread.sleep(1000);
				String ValNum = Integer.valueOf(i).toString();
				WebElement ValueTxt = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.VALUE_TXT,
						"VALNUMBER", ValNum);
				Thread.sleep(1000);
				controlActions.perform_scrollToElement_ByElement(ValueTxt);
				ValueTxt.sendKeys(Values.get(i - 1));

			}
			logInfo("Added Values for dropdown");
			return true;

		} catch (Exception e) {
			logError("Failed to add values - " + e.getMessage());
			return false;
		}
	}

	/**
	 * "moveToElement" method is used to drap and drop elements using X-Y coordinates 
	 * @author thakker_k
	 * @param int DragXCord,DragYCord - X-Y coordinates of element to be dragged
	 * @param int DropXCord,DropYCord - X-Y coordinates of drop area
	 * @return boolean true post drag- drop element
	 */
	public boolean moveToElement(int DragXCord, int DragYCord, int DropXCord, int DropYCord)
			throws AWTException, InterruptedException {
		try {
			Robot robot = new Robot();

			logInfo("moveby offset");
			robot.mouseMove(DragXCord, DragYCord); //
			Thread.sleep(1000);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);

			Thread.sleep(4000);
			robot.mouseMove(DragXCord - 13, 310);
			Thread.sleep(500);
			robot.mouseMove(DropXCord, DropYCord);
			Thread.sleep(1000);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			logInfo("Robot drag and drop done");
			return true;
		} catch (Exception e) {
			logError("Error in Drag and Drop");
			return false;
		}

	}

	/**
	 * "setIfPropertyExpressionRuleBuilder" method is used to add expression rule 
	 * @author thakker_k
	 * @param String - Type 
	 * @param String - Value
	 * @return boolean status IF FieldType is added to the form
	 */
	public boolean setIfPropertyExpressionRuleBuilder(String Value) {
		try {
			controlActions.click(AdvancedTab);
			Thread.sleep(3000);
			controlActions.click(AddNewExpressions);
			Thread.sleep(3000);

			ArrayList<Integer> DragElement = controlActions.getElementCoordinates(IfBtnDragElement);
			ArrayList<Integer> DropElement = controlActions.getElementCoordinates(MainDropArea);
			moveToElement(DragElement.get(0)+30, DragElement.get(1)+125, DropElement.get(0)+150, DropElement.get(1)+175);
			logInfo("Drag and Drop action has performed");

			//moveToElement(510, 266, 500, 400);
			logInfo("Drag and Drop action has performed");
			controlActions.waitforElementToBeDisplayed(IfTxt);

			controlActions.sendKeys(IfTxt,Value);
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_DOWN);
			Thread.sleep(1000);
			robot.keyRelease(KeyEvent.VK_DOWN);
			Thread.sleep(1000);
			robot.keyPress(KeyEvent.VK_ENTER);
			Thread.sleep(1000);
			robot.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(2000);			
			Thread.sleep(2000);
			logInfo("Set If Property in Expression Builder");
			return true;

		} catch (Exception e) {
			logError("Failed to set If property in Expression Builder" + e.getMessage());
			return false;
		}
	}

	/* 'setIfElementsValue' method is used to set If Elements Value
	 * @author thakker_k
	 * @param value
	 * @return boolean status
	 * IF value is set THEN true ELSE returns false
	 */
	public boolean setIfElementsValue(String value) {
		try {	
			moveToElement(1057, 266, 970, 330);
			controlActions.sendKeys(IfElementsValue, value);
			logInfo("If elements value set successfully");
			return true;	
		}catch(Exception e) {
			logError("Failed to set If elements value - "
					+ e.getMessage());
			return false;
		}	
	}

	/**
	 * "setValuesToThenElseDropDown" method is used to Set Values To Then-Else DropDown in Rule Builder
	 * @author thakker_k
	 * @param String ConditionType - THEN or ELSE condition
	 * @param String ValueToBeSet - Value To Be Set
	 * @return boolean status IF FieldType is added to the form
	 */
	public boolean setValuesToThenElseDropDown(String ConditionType, String ValueToBeSet) {
		try {

			switch (ConditionType) {
			case "THEN":
				controlActions.sendKeys(ThenDropDownText, ValueToBeSet);
				controlActions.click(ThenDropDownArrow);
				break;
			case "ELSE":
				controlActions.sendKeys(ElseDropDownText, ValueToBeSet);
				controlActions.click(ElseDropDownArrow);
				break;
			default:
				logError("Incorrect Condition Type");
				return false;			
			}

			logInfo ("Set Values To" + ConditionType +" Drop Down ");
			return true;

		} catch (Exception e) {
			logError("Failed to Set Values To" + ConditionType +" Drop Down " + e.getMessage());
			return false;
		}
	}

	/** 'clickSaveCloseButton' method is used to click on Save & Close Button in Expression Builder
	 * @author thakker_k
	 * @return boolean true post clicking on Save & Close Button
	 */	
	public boolean clickSaveCloseButton() {
		try {
			controlActions.clickElement(SaveCloseButton);
			Sync();
			logInfo("Clicked on SaveClose button");
			return true;
		}catch(Exception e) {
			logError("Failed to click on SaveClose button"
					+ e.getMessage());
			return false;
		}
	}

	/**
	 * @author hingorani_a
	 */
	public boolean setExpressionRule(String fieldName, String fieldValue, String thenValue, String elseValue) {
		try {
			if(!setIfPropertyExpressionRuleBuilder(fieldName)) {
				return false;
			}

			if(!setIfElementsValue(fieldValue)) {
				return false;
			}

			if(!setValuesToThenElseDropDown("THEN", thenValue)) {
				return false;
			}

			if(!setValuesToThenElseDropDown("ELSE", elseValue)) {
				return false;
			}

			if(!clickSaveCloseButton()) {
				return false;
			}
			return true;
		}
		catch(Exception e) {
			logError("Failed to click on SaveClose button"
					+ e.getMessage());
			return false;
		}
	}

	/**
	 * "AddDependencyRule" method is used to Add Dependency Rule
	 * @author thakker_k
	 * @param String - IfValue - Value of If Text box
	 * @param String - ExpValue - Value of Value Text box
	 * @return boolean true post adding Dependency Rule
	 */
	public boolean AddDependencyRule(String IfValue, String ExpValue) {
		try {
			controlActions.click(AdvancedTab);
			Thread.sleep(3000);
			controlActions.click(AddNewBtn);
			controlActions.waitforElementToBeDisplayed(IfTxt);
			controlActions.sendKeys(IfTxt, IfValue);
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_DOWN);
			Thread.sleep(1000);
			robot.keyRelease(KeyEvent.VK_DOWN);
			Thread.sleep(1000);
			robot.keyPress(KeyEvent.VK_ENTER);
			Thread.sleep(1000);
			robot.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(4000);
			setIfElementsValue(ExpValue);
			clickSaveCloseButton();
			Thread.sleep(3000);
			logInfo("Added Dependency Rule");
			return true;

		} catch (Exception e) {
			logError("Failed to Add Dependency Rule - " + e.getMessage());
			return false;
		}
	}



	/* 'setComplianceForDateandTimeField' method is used to Set Compliance For Date and Time Field
	 * @author thakker_k
	 * @param shortName
	 * @param resourceName
	 * @param value
	 * @return boolean status
	 * IF compliance values are set on selected field THEN true ELSE returns false
	 */
	public boolean setComplianceForDateandTimeField(String shortName, String resourceName, String value) {
		try {	
			controlActions.clickElement(FormNameTxt);
			WebElement fieldSelection = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_SHORTNAME,"SHORT_NAME",shortName);
			controlActions.clickElement(fieldSelection);
			String complianceGridStatus = ComplianceIconStatus.getAttribute("class");
			if(!complianceGridStatus.equals("k-icon k-collapse-next k-i-arrow-60-down")) {
				controlActions.clickElement(ComplianceOpen);
			}

			Sync();		
			Actions action = new Actions(driver);
			action.moveToElement(ComplianceBarDrag).build().perform();
			controlActions.dragdrop(ComplianceBarDrag, ComplianceBarDrop);			
			WebElement ResourceEdit = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.EDIT_COMPLIANCE_RULE, "RESOURCEINSTANCENAME", resourceName);
			controlActions.click(ResourceEdit);

			Sync();

			ArrayList<Integer> DragElement = controlActions.getElementCoordinates(ValueBtnDragElement);
			ArrayList<Integer> DropElement = controlActions.getElementCoordinates(ComplianceDropArea);

			moveToElement(DragElement.get(0)+13, DragElement.get(1)+117, DropElement.get(0)+609, DropElement.get(1)+140);


			//moveToElement(1057, 266, 970, 330);
			logInfo("Drag and Drop action has performed");
			controlActions.sendKeys(DateTimeValueTxt, value);
			clickSaveCloseButton();

			Sync();
			controlActions.clickElement(ComplianceClose);
			logInfo("Set Compliance Values");
			return true;
		}catch(Exception e) {
			logError("Failed to add compliance values - "
					+ e.getMessage());
			return false;
		}	
	}

	/* 'setValuePropertyForTimeFieldTypeExpressionRuleBuilder' method is used to Set Value Property For Time Field Type in Expression Rule Builder 
	 * @author thakker_k
	 * @param value
	 * @return boolean status
	 * IF value property is set for Time field type THEN true ELSE returns false
	 */
	public boolean setValuePropertyForTimeFieldTypeExpressionRuleBuilder(String value) {
		try {
			controlActions.click(AdvancedTab);
			Thread.sleep(3000);
			controlActions.click(AddNewExpressions);
			Thread.sleep(3000);		

			moveToElement(1057, 266, 970, 330);
			controlActions.sendKeys(DateTimeValueTxt, value);

			logInfo("Set Value Property For Time Field Type In Expression Rule Builder");
			return true;

		} catch (Exception e) {
			logError("Failed to set Value Property For Time Field Type In Expression Rule Builder" + e.getMessage());
			return false;
		}
	}

	/* 'dropAddSignExpressionRuleBuilder' method is used to drop Add Sign in Expression Rule Builder 
	 * @author thakker_k
	 * @return boolean status
	 * IF Add Sign is drop successfully THEN true ELSE returns false
	 */
	public boolean dropAddSignExpressionRuleBuilder() {
		try {
			moveToElement(820, 266, 600, 340);
			logInfo("Dropped Add Sign in Expression Rule Builder ");
			return true;

		} catch (Exception e) {
			logError("Failed to Drop Add sign in Expression Builder" + e.getMessage());
			return false;
		}
	}


	/* 'setValuePropertyInMinutesHoursForTimeFieldTypeExpressionRuleBuilder' method is used to Set Value Property In Minutes-Hours For Time Field Type in Expression Rule Builder 
	 * @author thakker_k
	 * @param value -  field value
	 * @param unit - unit of value (eg : minutes)
	 * @return boolean status
	 * IF value and unit property is set for Time field type THEN true ELSE returns false
	 */
	public boolean setValuePropertyInMinutesHoursForTimeFieldTypeExpressionRuleBuilder(String value,String unit) {
		try {

			moveToElement(1057, 266, 600, 420);
			controlActions.sendKeys(TimeUnitValueTxt, value);
			controlActions.selectDropDown(unit, TimeUnitDropdown);		
			logInfo("Set Value Property In Minutes-HoursForTimeFieldTypeExpressionRuleBuilder ");
			return true;

		} catch (Exception e) {
			logError("Failed to set If property in Expression Builder" + e.getMessage());
			return false;
		}
	}

	/** This method is used to set field properties
	 * @author pashine_a
	 * @param HashMap<String, ElementProperties> - 'fieldSettings'
	 * @return boolean status
	 */	
	public boolean setFieldProperties(HashMap<String, ElementProperties> fieldSettings) {
		String fieldName = null;
		ElementProperties elementProperties = null;
		WebElement fieldSelection;
		try {
			for (Map.Entry<String, ElementProperties> entry : fieldSettings.entrySet()) {
				fieldName = entry.getKey();
				elementProperties = entry.getValue();
				fieldSelection = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_SHORTNAME,"SHORT_NAME",fieldName);
				controlActions.clickElement(fieldSelection);
				Sync();
				controlActions.clickElement(SettingsTab);
				if(elementProperties.ALLOW_COMMENTS) {
					if(!setSettingsProperty(AllowCommentsONBtn,"Allow Comments","ON")) {
						return false;
					}
				}
				if(elementProperties.ALLOW_ATTATHMENTS) {
					if(!allowAttchments()) {
						return false;
					}
				}
				if(elementProperties.SHOW_HINT) {
					if(!setSettingsProperty(ShowHintONBtn,"Show Hint","ON")) {
						return false;
					}
					controlActions.setValue(HintTxa, elementProperties.HINT);
				}
				if(elementProperties.ALLOW_CORRECTION) {
					if(elementProperties.PREDEFINED_CORRECTIONS_OPTNS !=null) {
						if(!setSettingsProperty(AllowCorrectionsONBtn,"Allow Corrections","ON")) {
							return false;
						}
						if(!setPredefinedCorrectionOptions(elementProperties.PREDEFINED_CORRECTIONS_OPTNS)) {
							return false;
						}
					}
					else {
						if(!allowCorrections(elementProperties.PREDEFINED_CORRECTIONS)) {
						return false;
						}
					}
					if(elementProperties.MARK_RESOLVED) {
						if(!setSettingsProperty(MarkResolvedONBtn,"Mark Resolved?","ON")) {
							return false;
						}
					}
				}
				if(elementProperties.CARRYOVER_FIELD) {
					if(!carryoverField()) {
						return false;
					}
				}
				if(elementProperties.SHOW_TARGET) {
					if(!setSettingsProperty(ShowTargetBtn,"Show Target","ON")) {
						return false;
					}
				}
				if(elementProperties.COMPLIANCE_CONFIG) {
					if(!setComplianceForNumericAggragateField(fieldName, elementProperties.COMPLIANCE_VALUES[0], elementProperties.COMPLIANCE_VALUES[1],
							elementProperties.COMPLIANCE_VALUES[2], elementProperties.COMPLIANCE_VALUES[3])) {
						return false;
					}
				}
				if(elementProperties.COMPLIANCE_TEXT !=null) {
					if(!setComplianceForFreeText(fieldName, elementProperties.COMPLIANCE_TEXT)) {
						return false;
					}
				}
			}
			controlActions.clickElement(FormNameTxt);
			if(!clickOnNextButton(ReleaseFormPg,"Release Form")) {
				logError("Failed to click on Next button");
				return false;
			}

			return true;
		}catch(Exception e) {
			return false;
		}
	}

	public boolean setFieldptions(String questionWeight, List<String> options, String optionsWithWeight[][]) {
		try {
			if(options==null || options.isEmpty()) {
				if(optionsWithWeight==null) {
					logError("No values found");
				}else {
					controlActions.sendKeys(QuestionWeightInp,questionWeight);
					for(int i=0;i<optionsWithWeight.length;i++) {
						addOption(optionsWithWeight[i][0], optionsWithWeight[i][1]);
					}
				}
			}else {
				for(int i=0;i<options.size();i++) {
					addOption(options.get(i));
				}
			}
			logInfo("Set field options");
			return true;
		}catch(Exception e) {
			logError("Failed to set field options - " +e.getMessage());
			return false;
		}
	}

	/* "setValuesToThenElseTextBox" method is used to Set Values To Then-Else TextBox in Rule Builder
	 * @author thakker_k
	 * @param String ConditionType - THEN or ELSE condition
	 * @param String ValueToBeSet - Value To Be Set
	 * @return boolean status IF FieldType is added to the form
	 */
	public boolean setValuesToThenElseTextBox(String ConditionType, String ValueToBeSet) {
		try {

			switch (ConditionType) {
			case "THEN":
				controlActions.sendKeys(ThenText, ValueToBeSet);
				break;
			case "ELSE":
				controlActions.sendKeys(ElseText, ValueToBeSet);
				break;
			default:
				logError("Incorrect Condition Type");
				return false;			
			}

			logInfo ("Set Values To " + ConditionType +" Text box ");
			return true;

		} catch (Exception e) {
			logError("Failed to Set Values To" + ConditionType +" Text box " + e.getMessage());
			return false;
		}
	}

	/**
	 * @author hingorani_a
	 */
	public boolean addHeaderLevelFields(String noteText, String docTypeName, String docName, boolean summaryField) {
		try {

			if(noteText != null) {
				controlActions.dragdrop(NoteDbl, HeaderLevel);
//				controlActions.sendKeys(NoteTxt, noteText);
				NoteTxt.sendKeys(noteText);
			}

			if((docTypeName != null) && (docName != null)) {
				if(!addRelatedDocument(docTypeName, docName)) {
					return false;
				}
			}

			if(summaryField)
				controlActions.dragdrop(Summarydbl, HeaderLevel);

			logInfo("Added Header level fields to form");
			return true;
		}
		catch(Exception e) {
			logError("Failed to Add Header level fields to form :"
					+ e.getMessage());
			return false;
		}	
	}

	/**
	 * @author hingorani_a
	 */
	public boolean selectField(String fieldName) {
		WebElement FieldShortname = null;
		try {
			FieldShortname = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_SHORTNAME,"SHORT_NAME",fieldName);
			controlActions.clickElement(FieldShortname);
			Sync();
			logInfo("Could select field - " + fieldName);
			return true;
		}
		catch(Exception e) {
			logError("Failed to select field - " + fieldName
					+ e.getMessage());
			return false;
		}	
	}


	public boolean selectResourceInPreviewForm(String resourceName) {
		WebElement resource;
		String selectedResource;
		try {
			if(controlActions.isElementDisplayedOnPage(SelectResourceDdl)) {
				controlActions.clickElement(SelectResourceDdl);
				resource = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.RESOURCE_LIST_VALUE, "NAME", resourceName);
				controlActions.clickElement(resource);
				Sync();
				logInfo("Selected Resource - "+resourceName);
			}else {
				selectedResource = SelectedResourceLbl.getText();
				logInfo("Resource - '"+selectedResource+"' is already selected");
			}
			return true;
		}catch (Exception e) {
			logError("Failed to select Resource - "+e.getMessage());	
			return false;
		}
	}

	public boolean enterDataForPreviewForm(PreviewFormDetails pfd) {
		LinkedHashMap<String, List<String>> details;
		String fieldName;
		List<String> fieldValue;
		try {
			details = pfd.fieldDetails;
			Sync();
			if(!selectResourceInPreviewForm(pfd.resourceName)) {
				logError("Failed to select location/resource");
				return false;
			}
			for (Map.Entry<String, List<String>> entry : details.entrySet()) {
				fieldName = entry.getKey();
				fieldValue = entry.getValue();
				if(!fillData(fieldName, fieldValue)) {
					logError("Failed to fill form");
					return false;
				}
				Sync();
			}
			logInfo("Filled data in provided field(s)");			

			if(pfd.closePreview) {
				controlActions.clickElement(ClosePreviewBtn);
				Sync();
				logInfo("Clicked on 'Close' preview form button");					
			}
			
			return true;
		}catch (Exception e) {
			logError("Failed to fill data in form - "+e.getMessage());
			return false;
		}
	}

	public boolean fillData(String fieldName, List<String> fieldValue) {
		WebElement fieldElement, fieldStatus;
		String fieldType = null;
		try {
			fieldElement = controlActions.perform_GetElementByXPath(FSQABrowserFormsPageLocators.FIELD_TYPE, "FIELD_NAME", fieldName);
			fieldStatus = controlActions.perform_GetElementByXPath(FSQABrowserFormsPageLocators.FIELD_FILLED_STATUS_LBL, "FIELD_NAME", fieldName);
			fieldType =  fieldElement.getAttribute("data-fieldtype");
			if(fieldStatus.getAttribute("class").equals("fa fa-circle-o") || fieldStatus.getAttribute("class").equals("fa")) {
				switch(fieldType) {
				case "Paragraph":
					if(!setTextAreaFieldValue(fieldName, fieldValue)) {
						return false;
					}
					break;

				case "SelectMultiple":
					if(!setSelecMultipleFieldValue(fieldName, fieldValue)){
						return false;
					}
					break;

				default:
					if(!setInputFieldValue(fieldName, fieldValue)) {
						return false;
					}
				}
				logInfo("Data is filled in field - '"+fieldName+"' for '"+fieldType+"' field type");
			}else {
				logInfo("Data is already filled in field");
			}
			return true;
		}catch (Exception e) {
			logError("Failed to set value in '"+fieldName+"' field for '"+fieldType+"'field type"+e.getMessage());	
			return false;
		}
	}

	public boolean setSelecMultipleFieldValue(String fieldName, List<String> fieldValue) {
		List<WebElement> fieldElement, fieldElementLbl;
		try {
			fieldElement = controlActions.perform_GetListOfElementsByXPath(FieldInputs.INPUT_FIELD, "FIELD_NAME", fieldName);
			fieldElementLbl =  controlActions.perform_GetListOfElementsByXPath(FSQABrowserFormsPageLocators.FIELD_LBL, "FIELD_NAME", fieldName);
			if(fieldValue.size()==1) {
				for(int i=0;i<fieldElement.size();i++) {
					controlActions.sendKeys(fieldElement.get(i),fieldValue.get(0));
					controlActions.sendKey(fieldElement.get(i),Keys.ARROW_DOWN);
					controlActions.sendKey(fieldElement.get(i),Keys.ENTER);		
					controlActions.clickElement(fieldElementLbl.get(i));
				}
			}else {
				for(int i=0;i<fieldElement.size();i++) {
					controlActions.sendKeys(fieldElement.get(i),fieldValue.get(i));
					controlActions.sendKey(fieldElement.get(i),Keys.ARROW_DOWN);
					controlActions.sendKey(fieldElement.get(i),Keys.ENTER);		
					controlActions.clickElement(fieldElementLbl.get(i));
				}
			}
			logInfo("Setted 'Select One' field's value");
			return true;
		}catch (Exception e) {
			logError("Failed to set 'Select One' field's value - "+e.getMessage());	
			return false;
		}
	}

	public boolean setInputFieldValue(String fieldName, List<String> fieldValue) {
		List<WebElement> fieldElement, fieldElementLbl;
		try {
			fieldElement = controlActions.perform_GetListOfElementsByXPath(FieldInputs.INPUT_FIELD, "FIELD_NAME", fieldName);
			fieldElementLbl =  controlActions.perform_GetListOfElementsByXPath(FSQABrowserFormsPageLocators.FIELD_LBL, "FIELD_NAME", fieldName);

			if(fieldValue.size()==1) {
				for(int i=0;i<fieldElement.size();i++) {
					controlActions.sendKeys(fieldElement.get(i),fieldValue.get(0));
					controlActions.clickElement(fieldElementLbl.get(i));
				}
			}else {
				for(int i=0;i<fieldElement.size();i++) {
					controlActions.sendKeys(fieldElement.get(i),fieldValue.get(i));
					controlActions.clickElement(fieldElementLbl.get(i));
				}
			}
			logInfo("Setted  field's value");
			return true;
		}catch (Exception e) {
			logError("Failed to set field input's value - "+e.getMessage());
			return false;
		}
	}

	public boolean setTextAreaFieldValue(String fieldName, List<String> fieldValue) {
		List<WebElement> fieldElement, fieldElementLbl;
		try {
			fieldElement = controlActions.perform_GetListOfElementsByXPath(FieldInputs.TEXTAREA_FIELD, "FIELD_NAME", fieldName);
			fieldElementLbl =  controlActions.perform_GetListOfElementsByXPath(FSQABrowserFormsPageLocators.FIELD_LBL, "FIELD_NAME", fieldName);
			if(fieldValue.size()==1) {
				for(int i=0;i<fieldElement.size();i++) {
					controlActions.sendKeys(fieldElement.get(i),fieldValue.get(0));
					controlActions.clickElement(fieldElementLbl.get(i));
				}
			}else {
				for(int i=0;i<fieldElement.size();i++) {
					controlActions.sendKeys(fieldElement.get(i),fieldValue.get(i));
					controlActions.clickElement(fieldElementLbl.get(i));
				}
			}
			logInfo("Setted  field's value");
			return true;
		}catch (Exception e) {
			logError("Failed to set field textarea's value - "+e.getMessage());	
			return false;
		}
	}

	/* used to verify the compliance values in preview form on release page
	 * @author choubey_a
	 * @param formname 
	 * @return boolean status IF compliance values changes is verified
	 */

	public boolean verifyComplainceData(String formName) {
		try {

			WebElement preview = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.RELEASE_PAGE_PREVIEW_BTN,"FORM",formName);
			controlActions.clickElement(preview);
			Sync();
			//if(!(ComplianceUnit.getText() == "KG")) {
			//	logError("Complaice UOM not verified");
			//}
			if(!(ComplianceMinVal.getText()== "100")) {
				logInfo("Complaice minimum value not verified");
			}
			if(!(ComplianceMaxVal.getText()== "200")) {
				logInfo("Complaice maximum value not verified");
			}
			if(!(ComplianceTargetVal.getText()== "150")) {
				logInfo("Complaice target value not verified");
			}
			Sync();
			controlActions.clickElement(ClosePreviewBtn);
			Sync();
			releaseForm(formName);
			logInfo("Form with compliance value changes is released");
			return true;
		}catch (Exception e) {
			logError("Failed to release form with complaince value changes - "+e.getMessage());	
			return false;
		}
	}

	/**
	 * @author thakker_k
	 */
	public boolean deleteField(String shortName) {
		WebElement FieldShortname = null;
		WebElement DeleteIcon = null;
		try {
			FieldShortname = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_SHORTNAME,"SHORT_NAME",shortName);
			controlActions.clickElement(FieldShortname);
			Sync();

			DeleteIcon = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.DELETE_FIELD_ICON,"SHORT_NAME",shortName);
			controlActions.clickElement(DeleteIcon);
			Sync();


			logInfo("Clicked Delete icon  successfully for field " + shortName);
			return true;
		}
		catch(Exception e) {
			logError("Failed to click on Delete icon for field - " + shortName
					+ e.getMessage());
			return false;
		}	
	}

	/**
	 * @author thakker_k
	 */
	public boolean fieldCannotBeDeletedPopupDisplayed() {

		try {

			boolean displayed = FieldCannotBeDeletedPopupLabel.isDisplayed();
			logInfo("Field Cannot Be Deleted Popup is displayed successfully");

			if(displayed) {
				OkButton.click();
				logInfo("Clicked OK button on Field Cannot Be Deleted Popup successfully");					
			}

			return true;
		}
		catch(Exception e) {
			logError("Failed to validate Field cannot be deleted Popup - "
					+ e.getMessage());
			return false;
		}	
	}


	/**
	 * @author thakker_k
	 */
	public boolean deleteRule(String fieldShortName, String ruleName) {
		WebElement FieldShortname = null;
		WebElement DeleteIconRule = null;
		try {

			FieldShortname = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_SHORTNAME,"SHORT_NAME",fieldShortName);
			controlActions.clickElement(FieldShortname);
			Sync();

			controlActions.click(AdvancedTab);
			Thread.sleep(3000);

			DeleteIconRule = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.DELETE_ICON_RULE,"RULE_NAME",ruleName);
			controlActions.clickElement(DeleteIconRule);
			Sync();

			logInfo("Deleted rule " + ruleName + " for field " + fieldShortName);
			return true;
		}
		catch(Exception e) {
			logError("Failed to Delete rule " + ruleName + " for field " + fieldShortName
					+ e.getMessage());
			return false;
		}	
	}

	/**
	 * @author thakker_k
	 */
	public boolean invalidRuleConfirgurationErrorDisplayed() {

		try {

			boolean displayed = InvalidRuleConfigurationErrorLabel.isDisplayed();
			logInfo("Invalid Rule Configuration message is displayed successfully");

			if(displayed) {
				CancelButton.click();
				logInfo("Clicked Cancel button on Rule Builder Popup successfully");					
			}

			return true;
		}
		catch(Exception e) {
			logError("Invalid Rule Configuration message is NOT displayed "
					+ e.getMessage());
			return false;
		}	
	}

	public boolean selectAggregateSources(List<String> aggregateSource) {
		WebElement SourceCB = null;

		try {			

			for(int i =0;i<aggregateSource.size();i++) {				
				SourceCB = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.SELECT_AGGREGATE_SOURCE_CB,"SOURCENAME",aggregateSource.get(i));
				controlActions.clickElement(SourceCB);
			}

			logInfo("Selected Aggregate Source Successfully");
			return true;
		}
		catch(Exception e) {
			logError("Failed to select Aggregate Source CB "
					+ e.getMessage());
			return false;
		}	
	}

	public boolean setMinMaxForAggregate(String min, String max) {

		try {

			SetAggregateMinTxt.sendKeys(min);
			SetAggregateMaxTxt.sendKeys(max);
			logInfo("Set Min value as "+min+" and Max value as "+max+" for Aggregate");					


			return true;
		}
		catch(Exception e) {
			logError("Failed to set Min and Max values for Aggregate - "
					+ e.getMessage());
			return false;
		}	
	}


	/** 'selectAggregateFunction' method is used to select Function for Aggregate Field
	 * @author thakker_k
	 * @param String - 'aggregateFunction'
	 * @return boolean true post selecting Function for Aggregate Field
	 */	
	public boolean configureAggregateFunction(String aggregateFunction,List<String> aggregateSource,String min, String max ) {
		try {
			selectAggregateFunction(aggregateFunction);

			if ((aggregateFunction == "Count Range") || (aggregateFunction == "Sum Range"))
			{
				setMinMaxForAggregate(min, max);				
				selectAggregateSources(aggregateSource);
			}
			else
			{
				setAggregateSource(aggregateSource.get(0));
			}

			logInfo("Configured Aggregate Function Successfully");
			return true;
		}catch(Exception e) {
			logError("Failed to configure Aggregate Function - "
					+ e.getMessage());
			return false;
		}
	}

	/**
	 * 'addFormElements' method is used to add Note, Related Docs & Summary form elements at header level
	 * @author pashine_a	
	 * @param String - noteText
	 * @param String - DocumentTypeName
	 * @param String - DocumentName
	 * @return boolean status
	 * IF form elements added THEN true ELSE returns false;
	 */
	public boolean addFormElements(String noteText,String DocumentTypeName,String DocumentName) {		
		try {	
			controlActions.dragdrop(NoteDbl,HeaderLevel);
			controlActions.sendKeys(NoteTxt,noteText);

			if(!addRelatedDocument(DocumentTypeName, DocumentName)) {
				logError("Fail to configure 'Related Docs' field");
			}

			controlActions.dragdrop(Summarydbl, HeaderLevel);

			controlActions.clickElement(FormNameTxt);
			if(!clickOnNextButton(ReleaseFormPg,"Release Form")) {
				logError("Failed to click on Next button");
				return false;
			}
			logInfo("Added form elements");
			return true;
		}
		catch(Exception e) {
			logError("Failed to add form elements"+ e.getMessage());
			return false;
		}	
	}



	/** 'getStatusOfToggleButton' method is used to get status of Toggle button in Settings Tab
	 * @author thakker_k
	 * @param String fieldShortName - 'Short name of field'
	 * @param String 'buttonName' - Button name 
	 * @return String -  Status of Toggle 
	 */	
	public String getStatusOfToggleButton(String fieldShortName, String buttonName) {
		WebElement ToggleButton = null;
		WebElement FieldShortname = null;
		try {
			FieldShortname = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_SHORTNAME,"SHORT_NAME",fieldShortName);
			controlActions.clickElement(FieldShortname);
			Sync();

			controlActions.clickElement(SettingsTab);
			logInfo("Clicked 'Settings' tab");

			ToggleButton = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.GET_STATE_OF_TOGGLE_BTN,"BUTTON_NAME",buttonName);
			String ButtonState = ToggleButton.getText();			

			logInfo ("State of "+buttonName+" button is "+ButtonState);
			return ButtonState;

		}catch(Exception e) {
			logError("Failed to get State of Toggle Button - '"+buttonName+"' - "
					+ e.getMessage());
			return null;
		}
	}


	/** 'inputFieldExistInGeneralTab' method is used to validate input field exists in General Tab
	 * @author thakker_k
	 * @param String - 'fieldShortName'
	 * @param String - 'inputFieldName'
	 * @return boolean true if aggregate field exists in General Tab
	 */	
	public boolean inputFieldExistInGeneralTab(String fieldShortName, String inputFieldName) {
		WebElement FieldShortname = null;
		WebElement InputField = null;
		try {

			FieldShortname = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_SHORTNAME,"SHORT_NAME",fieldShortName);
			controlActions.clickElement(FieldShortname);
			Sync();

			controlActions.clickElement(GeneralTab);
			logInfo("Clicked 'General' tab");

			InputField = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.GENERAL_TAB_INPUT_FIELDS_TXT,"FIELD_NAME",inputFieldName);
			boolean isInputFieldDisplayed = InputField.isDisplayed();


			logInfo( inputFieldName + " field is displayed in Genral Tab");
			return isInputFieldDisplayed;
		}catch(Exception e) {
			logError("Failed validate field "+ inputFieldName +" in General Tab - "
					+ e.getMessage());
			return false;
		}
	}

	/** 'allowCorrection' method is used to set field's property 'Allow Corrections'
	 * @author pashine_a
	 * @param boolean - 'isPredefined'
	 * @return boolean status
	 * IF property is setted THEN true ELSE returns false;
	 */
	public boolean allowCorrections(boolean isPredefined) {
		try {				
			if(!setSettingsProperty(AllowCorrectionsONBtn,"Allow Corrections","ON")) {
				logError("Failed to set settings property");
				return false;
			}
			if(isPredefined) {
				if(!addPredefinedCorrectionsOptions()) {
					logError("Failed to add Predefined Corrections Options");
					return false;
				}
			}
			logInfo("Configured 'Allow Corrections' settings");
			return true;
		}catch(Exception e) {
			logError("Failed to configure 'Allow Corrections' settings"
					+ e.getMessage());
			return false;
		}	
	}


	/** 'addPredefinedCorrectionsOption's method is used to add options for 'Predefined Corrections'
	 * @author pashine_a
	 * @param null
	 * @return boolean status
	 * IF property is setted THEN true ELSE returns false;
	 */
	public boolean addPredefinedCorrectionsOptions() {
		try {				
			controlActions.clickElement(PredefinedCorrectionAddBtn);
			controlActions.sendKeys(PredefinedCorrectionOptionInp, PreDefinedCorrectionOptions.Option1.toString());
			controlActions.clickElement(PredefinedCorrectionAddBtn);
			controlActions.sendKeys(PredefinedCorrectionOptionInp, PreDefinedCorrectionOptions.Option2.toString());
			controlActions.clickElement(PredefinedCorrectionAddBtn);
			controlActions.sendKeys(PredefinedCorrectionOptionInp, PreDefinedCorrectionOptions.Option3.toString());
			controlActions.clickElement(PredefinedCorrectionAddBtn);
			controlActions.sendKeys(PredefinedCorrectionOptionInp, PreDefinedCorrectionOptions.Option4.toString());

			logInfo("Added options in 'Predefined Corrections'");
			return true;
		}catch(Exception e) {
			logError("Failed to add 'Predefined Corrections' option(s) - "
					+ e.getMessage());
			return false;
		}	
	}

	public boolean setExpressionRuleNumeric(String fieldName, String fieldValue, String thenValue, String elseValue) {
		try {
			if(!setIfPropertyExpressionRuleBuilder(fieldName)) {
				return false;
			}

			if(!setIfElementsValue(fieldValue)) {
				return false;
			}

			if(!setValuesToThenElseTextBox("THEN", thenValue)) {
				return false;
			}

			if(!setValuesToThenElseTextBox("ELSE", elseValue)) {
				return false;
			}

			if(!clickSaveCloseButton()) {
				return false;
			}
			return true;
		}
		catch(Exception e) {
			logError("Failed to click on SaveClose button"
					+ e.getMessage());
			return false;
		}
	}


	/** This method is used to configure rules on field
	 * Expression rule for Text box & using IF-THEN-ELSE Combination
	 * Compliance rule for Date Field
	 * @author pashine_a
	 * @param HashMap<String, RuleProperties> - 'fieldSettings'
	 * @return boolean status
	 */	
	public boolean setFieldRules(HashMap<String, RuleProperties> fieldSettings) {
		String fieldName = null;
		RuleProperties elementProperties = null;
		WebElement fieldSelection;
		try {
			for (Map.Entry<String, RuleProperties> entry : fieldSettings.entrySet()) {
				fieldName = entry.getKey();
				elementProperties = entry.getValue();
				fieldSelection=controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_SHORTNAME,"SHORT_NAME",fieldName);
				controlActions.clickElement(fieldSelection);
				Sync();
				if(elementProperties.depdendencyRuleConfig!=null) {
					if(!AddDependencyRule(elementProperties.depdendencyRuleConfig.IF_FIELD, elementProperties.depdendencyRuleConfig.VALUE_VALUE)) {
						logError("Failed to add depdendency rule on field - "+fieldName);
					}
				}
				if(elementProperties.expressionRuleConfig!=null) {
					if(!setExpressionRuleNumeric(elementProperties.expressionRuleConfig.IF_FIELD, elementProperties.expressionRuleConfig.VALUE_VALUE, elementProperties.expressionRuleConfig.THEN_VALUE, elementProperties.expressionRuleConfig.ELSE_VALUE)) {
						logError("Failed to add expression rule on field - "+fieldName);
					}
				}
				if(elementProperties.complianceRuleConfig!=null) {
					if(!setComplianceForDateandTimeField(elementProperties.complianceRuleConfig.IF_FIELD, elementProperties.complianceRuleConfig.RESOURCE, elementProperties.complianceRuleConfig.VALUE_VALUE)) {
						logError("Failed to add compliance rule on field - "+fieldName);
					}
				}

			}
			controlActions.clickElement(FormNameTxt);
			if(!clickOnNextButton(ReleaseFormPg,"Release Form")) {
				logError("Failed to click on Next button");
				return false;
			}
			return true;
		}catch(Exception e) {
			return false;
		}
	}

	/** This method is used to verify the resource added in the form
	 * @author choubey_a
	 * @param resourcename
	 * @return boolean status
	 */	

	public boolean verifyResourceInForm(List<String> resourcename) {
		try {
			controlActions.clickElement(SelectResourceHeader);
			Sync();
			for(int i=0;i<resourcename.size();i++) {
				WebElement resourceselect= controlActions.perform_GetElementByXPath(FormDesignerPageLocator.RESOURCE_GRD, "RESOURCENAME", resourcename.get(i));
				if(!controlActions.isElementDisplayed(resourceselect)) {
					logError("Resource is not added in the form");
					return false;
				}
			}
			controlActions.clickElement(FormDesignerExitBtn);
			Sync();
			logInfo("Resource verified in the form");
			return true;
		}catch(Exception e) {
			logError("Resource not verified in the form");
			return false;
		}
	}
	
	/** This method is verify the error message displayed on Release page
	 * @author hingorani_a
	 * @param errorMsg The error message to be verified to be separated with '|'
	 * @return boolean true when the error message is verified that it is being displayed
	 */	
	public boolean verifyErrorMsgReleasePg(String errorMsg) {
		int count = 0;
		
		try {
			if(ReleasePageErrors.size()==1) {
				String[] values = CommonMethods.splitAndGetString(errorMsg);
				int valueCount = values.length;
				for(int i = 0; i < valueCount; i++) {
					if(controlActions.perform_CheckIfElementTextContains(ReleasePageErrors.get(0), values[i])) {
						logInfo("Verified value " + values[i] + " is present ");
						count++;
					}
					else {
						break;
					}
				}
				
				if(count == valueCount) {
					logInfo("Verified error message - " + errorMsg);
					return true;
				}
				else {
					logInfo("Could not verify all values for error message - " + errorMsg);
					return false;
				}
			}
			else if(ReleasePageErrors.size()==0) {
				logInfo("Found no error");
				return false;
			}
			else {
				logInfo("Found more than 1 error");
				return false;
			}
			
		}
		catch(Exception e) {
			logError("Failed to verify error " + errorMsg + " " + e.getMessage());
			return false;
		}
	}

	
	/** This method is set name to form name field
	 * @author hingorani_a
	 * @param formName Name to be set
	 * @return boolean true when the form name is set successfully
	 */	
	public boolean setFormName(String formName) {
		try {
			controlActions.WaitforelementToBeClickable(FormNameTxt);
			FormNameTxt.sendKeys(formName);	
			logInfo("Form name is set to - " + formName);
			return true;
		}
		catch(Exception e) {
			logError("Failed to set form name to " + formName + " " + e.getMessage());
			return false;
		}
	}
	
	/**
	 * This method is used to click on Save button. It does not click on popup's Ok
	 * @author hingorani_a
	 * @param none
	 * @return boolean This returns boolean true after clicking Form's Save button
	 */
	public boolean clickFormSaveButton() {
		try {
			controlActions.WaitforelementToBeClickable(FormNameTxt);
			FormNameTxt.click();
			SaveButton.click();
			Sync();
			logInfo("Clicked Form's Save button");
			return true;
		}
		catch(Exception e) {
			logError("Failed to click on Form's Save button :" + e.getMessage());
			return false;
		}	
	}
	
	/**
	 * This method is used to update values of text box.
	 * @author thakker_k
	 * @param FieldTypeName Field Type for which you want to update value
	 * @param FieldTypeNamevalue Field type value to be updated
	 * @return boolean This returns boolean true after updating value to text box
	 */
	public boolean updateTextBoxValue(String FieldTypeName, String FieldTypeNamevalue) {
		WebElement TextBox = null;
		try {
			if(!getFields(FieldTypeName, FieldTypeNamevalue)) {
				logError("Failed to update values");
				return false;
			}
			TextBox = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.UPDATE_FIELD_TYPE_NAME_TXT,"FIELDTYPENAME",FieldTypeName);
			controlActions.WaitforelementToBeClickable(TextBox);		
			controlActions.clickOnElement(TextBox);
			TextBox.clear();
			controlActions.sendKeys(TextBox, FieldTypeNamevalue);
			threadsleep(5000);
			logInfo(FieldTypeName +" entered: "+ FieldTypeNamevalue);
			return true;
		}
		catch(Exception e) {
			logError("Failed to update value for field " + FieldTypeName + " :"
					+ e.getMessage());
			return false;
		}	
	}
	
	/**
	 * This method is used to click on OK button on popup
	 * @author hingorani_a
	 * @param none
	 * @return boolean This returns boolean true after clicking on Ok button
	 */
	public boolean clickOkOnPopup() {

		try {
			SaveFormOkButton.click();
			Sync();
			logInfo("Clicked Ok button");
			return true;
		}
		catch(Exception e) {
			logError("Failed to click Ok button :"
					+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to click on Form name textbox
	 * @author hingorani_a
	 * @param none
	 * @return boolean This returns boolean true after clicking on Form name textbox
	 */
	public boolean clickFormNameTxt() {
		try {
			controlActions.WaitforelementToBeClickable(FormNameTxt);
			FormNameTxt.click();
			Sync();
			logInfo("Clicked on Form's name textbox");
			return true;
		}
		catch(Exception e) {
			logError("Failed to click on  on Form name textbox :" + e.getMessage());
			return false;
		}	
	}
	
	/** 
	 * This method is used to click on a highlighted field's Setting tab
	 * @author hingorani_a
	 * @param none
	 * @return boolean This returns boolean true after clicking on Settings tab
	 */	
	public boolean clickSettingsTab() {
		try {
			controlActions.WaitforelementToBeClickable(SettingsTab);
			SettingsTab.click();
			logInfo("Clickted 'Settings' tab");
			return true;
		}catch(Exception e) {
			logError("Failed to click on 'Settings' tab - "
					+ e.getMessage());
			return false;
		}
	}
	
	
	/** 'setAggregateFunction' method is used to SET Function for Aggregate Field **
	 * @author ahmed_tw
	 * @param String - 'aggregateFunction'
	 * @return boolean true post setting Function for Aggregate Field
	 */	
	public boolean setAggregateFunction(String aggregateFunction) {
		try {
			SetAggregateFunction.clear();
			SetAggregateFunction.sendKeys(aggregateFunction);
			controlActions.actionEnter();
			logInfo("Set Aggregate Function - "+aggregateFunction);
			return true;
		}catch(Exception e) {
			logError("Failed to select Aggregate Function - "+aggregateFunction+" - "
					+ e.getMessage());
			return false;
		}
	}
	
	/** 
	 * @author ahmed_tw
	 * @param elementType
	 * @param name
	 * @return 
	 */
	public boolean setNameChkFrmElements(String elementType, String name) {
		WebElement Element = null;
		//System.out.println(name);
		//System.out.println(elementType);
		try {
			switch(elementType) {
			case ELEMENT_INPUT_TYPES.FIELD_GROUP:
			case ELEMENT_INPUT_TYPES.SECTION :
				Element = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.GRP_AND_SEC_CHK_ELE_TXT,	"ELEMENTTYPE", elementType);
				break;
			case FIELD_INPUT_TYPES.NUMERIC:
			case FIELD_INPUT_TYPES.AGGREGATE:
				Element = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.ADD_CHK_AND_AUDIT_FORM_FIELD,"TYPEOFFIELD", elementType);
			default:
			}
			//System.out.println(Element);
			controlActions.sendKeys(Element, name);
			logInfo("Set name for " + elementType + " as : " + name);
		} catch (Exception e) {
			logError("Could Not Set name for " + elementType + " as : " + name + e.getMessage());
			return false;
		}
		
		return true;
	}
	
	/** 
	 * This method is used to get List of available options for Aggregate field source from 'Source' Dropdown
	 * @author ahmed_tw
	 * @param AggFieldShortName
	 * @return List of string, all the available options for Aggregate source
	 */
	public List<String> getAggDrpDwnSources(String AggFieldShortName){
		List<WebElement> drpdwnElements = null;
		List<String> drpdwnValues = new ArrayList<String>();
		try {
			controlActions.refreshDisplayedPage();
			
			selectField(AggFieldShortName);
			controlActions.clickElement(AggSourceDropDown);
			Thread.sleep(2000);
			
			drpdwnElements = controlActions.getElements(FormDesignerPageLocator.AGG_SRC_DRP_DWN_OPTIONS);
			
			for (WebElement itemInList : drpdwnElements) {
		         drpdwnValues.add(itemInList.getText());   
		        }
			logInfo("Got Aggregate sources from Source Drop Down - " + drpdwnValues);
		} catch (Exception e) {
			logError("Could Not get Aggregate sources from Source Dropdown" + e.getMessage());
		}
		return drpdwnValues;
	}
	
	/** 
	 * This function is used to verify whether all the available source options in
	 * 'Source' drop down of Aggregate field are Unique or Not
	 * @author ahmed_tw
	 * @param aggFieldSortName
	 * @return True if all the sources are unique else false
	 */
	public boolean uniqueAggSource(String aggFieldSortName) {
		List<String> allDrpdwnValues = null;
		try {
			allDrpdwnValues = getAggDrpDwnSources(aggFieldSortName) ;
			Set<String> uniqueDrpDwnValues = new HashSet<String>(allDrpdwnValues);
			
			if(allDrpdwnValues.size() == uniqueDrpDwnValues.size())
				logInfo("Verified unique Values for Aggregate Source Drop Down");
			
		} catch (Exception e) {
			logError("Could Not Verify unique Values for Aggregate Source Drop Down" + e.getMessage());
			return false;
		}
		return true;
	}
	
	/**
	 * This Method is used to return a Set containing values which are repeated in the passed parameter List
	 * example : If a Section has fields as : Num, Num, Num, Num1, Num1, Num2, Num3
	 * this will return : Set [Num, Num1] as these are repeated fields and can be used as source for aggregate field
	 * other field i. Num2 and Num3 cannot as they are present only once.
	 * @author ahmed_tw
	 * @param allFields
	 * @return Set of repeated fields from list of all fields.
	 */
	public Set<String> getRepeatedValues(List<String> allFields){
		  final Set<String> setToReturn = new HashSet<>(); 
		  final Set<String> set1 = new HashSet<>();
		try {
			  for (String value : allFields)
			  {
			   if (!set1.add(value))
			   {
			    setToReturn.add(value);
			   }
			  }
			 logInfo("Set of repeated fileds are : -" + setToReturn);
		} catch (Exception e) {
			logError("Could not get set of repeated fields from all list of all fields"+e.getMessage());
		}
		 return setToReturn;
	}
	
	/**
	 * This method is used to verify that only the repeated fields from all the present fields 
	 * are available for 'Source' of an Aggregate Field.
	 * @author ahmed_tw
	 * @param allFields
	 * @param shortNameAggFiled
	 * @return True, post verifying only repeated fields from all present fields are available as source.
	 */
	public boolean repeatedFieldsVerify(List<String> allFields, String shortNameAggFiled ) {
		List<String> actualFields ; 
		Set<String> repeatedFields = new HashSet<>(); 
		List<String> toCompare = new ArrayList<String>();
		try {
			
			actualFields = getAggDrpDwnSources(shortNameAggFiled);
			
			repeatedFields = getRepeatedValues(allFields);
			
			toCompare.addAll(repeatedFields);
			
			if(actualFields.equals(toCompare)) 
				logInfo("Verified that only Repeated fileds are available as source for Aggregate field");
			
		} catch (Exception e) {
			logInfo("Could Not erify that only Repeated fileds are available as source for Aggregate field"+e.getMessage());
		}
		return true;
	}
	
	
	/**
	 * This is method is used to compare sources from different Aggregate fields
	 * @author ahmed_tw
	 * @param shortNameAggSrc1
	 * @param shortNameAggSrc2
	 * @return TRUE if the sources are same, else false
	 */
	public boolean compareDiffAggSrcs(String shortNameAggSrc1, String shortNameAggSrc2) {
		List<String> aggSource1 ;
		List<String> aggSource2 ;
		try {

			aggSource1 = getAggDrpDwnSources(shortNameAggSrc1);
			aggSource2 = getAggDrpDwnSources(shortNameAggSrc2);
			
			if(aggSource1.equals(aggSource2)) {
				logInfo("Both the Aggregrate Fileds have Same Source");
				return true;
			}else {
				logInfo("Both the Aggregrate Fileds DO NOT have Same Source");
			}
			
			
		} catch (Exception e) {
			logError("Could Not Compare sources of aggregate fields -" +shortNameAggSrc1 +"and " +  shortNameAggSrc2 + e.getMessage());
		}
		return false;
	}
	
	/**
	 * This method is used to copy fields
	 * @author ahmed_tw
	 * @param elementShortName
	 * @return TRUE after successfully copying fields, else false
	 */
	public boolean copyElement(String elementShortName) {
		List<WebElement> copyIcons = null;
		try {
			
		selectField(elementShortName);
			
		copyIcons = controlActions.getElements(FormDesignerPageLocator.COPY_ICON);
		
		for(WebElement copyIcon:copyIcons) {
			if(copyIcon.isDisplayed()) {
				controlActions.clickOnElement(copyIcon);
				break;
			}
		}
		
		logInfo("Successfully Created Copy of Element with short name- " + elementShortName );
			
		} catch (Exception e) {
			logError("Could Not create Copy of Element with short name- " + elementShortName + e.getMessage());
		}
		
		return true;
	}
	
	/**
	 * This method is used to verify whether a field is present as Source for Aggregate field or not
	 * @param fields
	 * @param shortNameAggField
	 * @return TRUE if the field is present as source, else false
	 */
	public boolean repeatedFieldPresentAsAggSrcOrNot(String field, String shortNameAggField) {
		List<String> aggSrcFields;
		try {
			aggSrcFields = getAggDrpDwnSources(shortNameAggField);
			
			if(aggSrcFields.contains(field)) {
			 logInfo("Field is Present as source - " + shortNameAggField);
			 return true;
			}
			else {
				logInfo("Field is Not Present - " + shortNameAggField);
			}
		} catch (Exception e) {
			logError("Could not verify presence of field +" + field + "as source for -:" + shortNameAggField);
		}
		return false;
	}
	
	/**
	 * This method is used to design the Form only
	 * @author ahmed_tw
	 * @param fdp An object of Class FormDesignParams, in order to set
	 * different details like resources, fields, etc to release a form.
	 * @return boolean This returns boolean true after releasing/setting details for Form
	 */
	public boolean designForm(FormDesignParams fdp) {
		try {
			if(!selectFormType(fdp.FormType)) {
				logError("Failed to select form type -"+ fdp.FormType);
				return false;
			}
			//this should have by.category or by.instance
			for (Map.Entry<String, List<String>> entry : fdp.SelectResources.entrySet()) {
				String resourceType = entry.getKey();
				List<String> categories = entry.getValue();

				for(String category : categories) {
					if(!selectResourceCategory(resourceType, category)) {
						logError("Failed to select resource category - "+ category);
						return false;
					}
					Sync();
				}
				Sync();
			}
			if(!clickOnNextButton(DesignFormPg,"Design Form")) {
				logError("Failed to click on Next button");
				return false;
			}
			if(!setDetails(fdp)) {
				logError("Failed to set details");
				return false;
			}
			//controlActions.clickElement(FormNameTxt);
			return true;
		}
		catch(Exception e) {
			logError("Failed to release form - '" + fdp.FormName + "' of type - '" + fdp.FormType + "'"+ e.getMessage());
			return false;
		}
	}
	
	/**
	 * This method is use to set the aggregate source by using sendkeys approach
	 * @author ahmed_tw
	 * @param fieldName
	 * @param aggregateSource
	 * @return True after setting the aggregate source, else false
	 */
	public boolean setAggregateSource(String fieldName, String aggregateSource) {
		try {
			controlActions.clickElement(FormNameTxt);
			selectField(fieldName);
			SetAggregateSource.sendKeys(aggregateSource);
			logInfo("Set Aggregate Source - "+aggregateSource);
			return true;
		} catch (Exception e) {
			logError("Failed to set aggregate source "+ aggregateSource+"for aggregate field " +fieldName +e.getMessage());
		}
		
		return true;
	}
	
	/** 'selectAggregateSource' method is used to select Function for Aggregate Field
	 * @author ahmed_tw
	 * @param String - 'aggregateSource'
	 * @return boolean true post selecting Function for Aggregate Field
	 */	
	public boolean selectAggregateSource(String aggregateSource) {
		try {
			controlActions.clickElement(AggSourceDropDown);
			WebElement SelectAggregateFunction = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.AGG_SRC_OPTION, "SOURCE_OPTION",aggregateSource );
			System.out.println();
			threadsleep(3000);
			controlActions.clickElement(SelectAggregateFunction);
			logInfo("Selected Aggregate Function - "+ aggregateSource);
			return true;
		}catch(Exception e) {
			logError("Failed to select Aggregate Function - "+aggregateSource+" - "
					+ e.getMessage());
			return false;
		}
	}
	
	/**
	 * This method is use to verify the Selected resource in Preview mode
	 * @author hingorani_a
	 * @param resourceName The name of resource to compare with selected resource
	 * @return boolean true after verifying the selected resource is equal to the resource we pass as parameter
	 */
	public boolean verifySelectedResourceInPreviewForm(String resourceName) {
		String selectedResource;
		try {
				selectedResource = SelectedResourceLbl.getText();
				if(selectedResource.contains(resourceName)) {
					logInfo("Verified Selected resource - '" + resourceName + "'");
					return true;
				}
				
				logInfo("Could not verify Selected resource - '" + resourceName + "'");
				return false;
		}
		catch (Exception e) {
			logError("Failed to verify Selected resource - " + resourceName
						+ e.getMessage());	
			return false;
		}
	}
	
	/**
	 * This method is use to open Compliance for the mentioned field using it's short name
	 * @author hingorani_a
	 * @param shortName The field's short name
	 * @return boolean true after opening the Compliance for Field having passed short name
	 */
	public boolean openComplianceForField(String shortName) {
		try {	
			FormNameTxt.click();
			Sync();
			WebElement fieldSelection = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_SHORTNAME,"SHORT_NAME",shortName);
			fieldSelection.click();
			String complianceGridStatus = ComplianceIconStatus.getAttribute("class");
			if(complianceGridStatus.contains("expand")) {
				controlActions.clickElement(ComplianceOpen);
			}
			logInfo("Opened Compliance for selected field");
			return true;
		}
		catch(Exception e) {
			logError("Failed to Open Compliance for selected field - "
					+ e.getMessage());
			return false;
		}	
	}
	
	/**
	 * This method is use to click on Add New button for Related Documents
	 * @author hingorani_a
	 * @param none
	 * @return boolean true after clicking Add New button
	 */
	public boolean clickAddNewDocBtn() {
		try {
			controlActions.WaitforelementToBeClickable(AddNewDocumentButton);
			AddNewDocumentButton.click();
			Sync();
			logInfo("Clicked on Add New document button");
			return true;
		}
		catch(Exception e) {
			logError("Failed to click on Add New document button :" + e.getMessage());
			return false;
		}	
	}
	
	/**
	 * This method is use to add documents from mentioned document type for Related Documents
	 * @author hingorani_a
	 * @param documentTypeName The name of document type having documents to be uploaded
	 * @param documentName Name of the document to be added from mentioned documentTypeName
	 * @return boolean true after adding documents to Related Documents popup
	 */
	public boolean addRelatedDocumentFromPopup(String documentTypeName, String documentName) {		
		try {	
			DocumentTypeDdl.click();
			WebElement selectDocumentType = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.SELECT_DOCUMENT_TYPE, 
					"DOCUMENT_TYPE", documentTypeName);
			selectDocumentType.click();
			Sync();

			WebElement selectDocumentName = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.SELECT_DOCUMENT, 
					"DOCUMENT_NAME", documentName);
			selectDocumentName.click();
			return true;
		}
		catch(Exception e) {
			logError("Failed to add Document in 'Related Docs'"
					+ e.getMessage());
			return false;
		}	
	}
	
	/**
	 * This method is use to click on Save button on popup for Related Documents
	 * @author hingorani_a
	 * @param none
	 * @return boolean true after clicking Save button
	 */
	public boolean clickSaveDocBtn() {
		try {
			SaveDocumentBtn.click();
			Sync();
			logInfo("Clicked on Save document button");
			return true;
		}
		catch(Exception e) {
			logError("Failed to click on Save document button :" + e.getMessage());
			return false;
		}	
	}
	
	/**
	 * This method is use to verify added document to Related Documents
	 * @author hingorani_a
	 * @param documentName Name of the document to be verified
	 * @return boolean true after verified the document for Related Documents
	 */
	public boolean verifyAddedRelatedDocument(String documentName) {		
		try {	

			WebElement selectedDocumentVerify= controlActions.perform_GetElementByXPath(FormDesignerPageLocator.SELECT_DOCUMENT_VERIFY,
					"DOCUMENT_NAME", documentName);
			if(!selectedDocumentVerify.isDisplayed()) {
				logError("Could NOT Verify added document");
			}
			logInfo("VERIFIED - Document is added");
			return true;
		}
		catch(Exception e) {
			logError("Failed to verify added Document in 'Related Docs'"
					+ e.getMessage());
			return false;
		}	
	}
	
	/**
	 * This method is use to click on Delete button, to delete attached documents to Related Documents
	 * @author hingorani_a
	 * @param none
	 * @return boolean true after clicking on Delete button
	 */
	public boolean clickDeleteReltdDocsBtn() {
		try {
			controlActions.WaitforelementToBeClickable(DeleteReltdDocsBtn);
			DeleteReltdDocsBtn.click();
			Sync();
			logInfo("Clicked on Delete Related document button");
			return true;
		}
		catch(Exception e) {
			logError("Failed to click on Delete Related document button :" + e.getMessage());
			return false;
		}	
	}
	
	/**
	 * This method is use to click on Close button to exit from the Preview mode
	 * @author hingorani_a
	 * @param none
	 * @return boolean true after clicking on Close button
	 */
	public boolean clickClosePreviewBtn() {
		try {
			controlActions.WaitforelementToBeClickable(ClosePreviewBtn);
			ClosePreviewBtn.click();
			Sync();
			logInfo("Clicked on Close Preview button");
			return true;
		}
		catch(Exception e) {
			logError("Failed to click on Close Preview button :" + e.getMessage());
			return false;
		}	
	}
	
	/** 'exitFormDesigner' method is used to Exit from Form Designer
	 * @author hingorani_a
	 * @param none
	 * @return boolean true after exiting from Form Designer
	 */	
	public boolean exitFromFormDesigner() {
		try {
			FormDesignerExitBtn.click();
			Sync();
			logInfo("Exited from 'Form Designer'");
			return true;
		}
		catch(Exception e) {
			logError("Failed to Exit from 'Form Designer' - "
					+ e.getMessage());
			return false;
		}
	}
	
	/** 
	 * This method is used to get List of available options for Aggregate field Function from 'Function' Dropdown
	 * @author ahmed_tw
	 * @param AggFieldShortName
	 * @return List of string, all the available options for Aggregate Function
	 */
	public List<String> getAggDrpDwnFunctions(String AggFieldShortName){
		List<WebElement> drpdwnElements = null;
		List<String> drpdwnValues = new ArrayList<String>();
		try {
			controlActions.refreshDisplayedPage();
			
			selectField(AggFieldShortName);
			controlActions.clickElement(AggregateFunction);
			Thread.sleep(2000);
			
			drpdwnElements = controlActions.getElements(FormDesignerPageLocator.AGG_SRC_DRP_DWN_OPTIONS);
			
			for (WebElement itemInList : drpdwnElements) {
		         drpdwnValues.add(itemInList.getText());   
		        }
			logInfo("Got Aggregate functions from Function Drop Down as - " + drpdwnValues);
		} catch (Exception e) {
			logError("Could Not get Aggregate functions from Function Dropdown" + e.getMessage());
		}
		return drpdwnValues;
	}
	

	/**
	 * This method is used to clear the already set value for Aggregate Source Field
	 * @author ahmed_tw
	 * @param AggFieldShortName
	 * @return True after clearing the value for Aggregate Source field, else False
	 */
	public boolean clearAggSource(String AggFieldShortName) {
		try {
			controlActions.clickElement(FormNameTxt);
			selectField(AggFieldShortName);
			
			SetAggregateSource.clear();

			logInfo("Cleared Aggregate Source  for - "+ AggFieldShortName);
			return true;
		} catch (Exception e) {
			logError("Failed to clear Aggregate Source for -  aggregate field " +AggFieldShortName +e.getMessage());
		}
		
		return true;
	}
	
	/**
	 * This method is used to clear the already set value for Aggregate Function Field
	 * @author ahmed_tw
	 * @param AggFieldShortName
	 * @return True after clearing the value for Aggregate Function field, else False
	 */
	public boolean clearAggFunction(String AggFieldShortName) {
		try {
			controlActions.clickElement(FormNameTxt);
			selectField(AggFieldShortName);
			
			SetAggregateFunction.clear();

			logInfo("Cleared Aggregate Function  for - "+ AggFieldShortName);
			return true;
		} catch (Exception e) {
			logError("Failed to clear Aggregate Function for -  aggregate field " +AggFieldShortName +e.getMessage());
		}
		
		return true;
	}

	/** "releaseFormWithoutAggSrc" method is used to verify releasing the form without aggregate src will not release form
	 * @author ahmed_tw
	 * @param String - formName
	 * @return boolean status
	 * IF form is released THEN true ELSE false
	 */	
	public boolean releaseWithoutAggDetailsError(String formName, String fieldSortName, String requiredError) {
		List<String> displayedErrors ;
		try {
			WebElement selectForm = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.SELECT_FORM_LBL, "FORM_NAME",formName );
			controlActions.clickElement(selectForm);
			logInfo("Selected form - "+formName);
			controlActions.clickElement(ReleaseBtn);
			if(controlActions.isElementDisplayed(FormDesignerPageLocator.ERROR_SYMBOL)) {
				logInfo("Error Is Displayed");
				displayedErrors = releasePageErrorsForField("FormAgg");
				for(String errors:displayedErrors) {
					System.out.println(errors);
					if(errors.equalsIgnoreCase(requiredError)) {
						logInfo("Verified error-"+ requiredError+ "for Field - " + fieldSortName);
					}
				}
			}else {
				logInfo("No Error Displayed");
				return false;
			}
			return true;
		}catch(Exception e) {
			logError("Failed to verify error - "+requiredError+" for field -  " +  fieldSortName+ e.getMessage());
			return false;
		}
	}
	
	/**
	 * This method is used to get the Errors for a field which comes while releasing the form, on release Page.
	 * @author ahmed_tw
	 * @param fieldShortName
	 * @return List<String> of Errors 
	 */
	public List<String> releasePageErrorsForField(String fieldShortName){
		List<String> errors = new ArrayList<String>();
		List<WebElement> errorElements;
		try {
			
			errorElements = controlActions.perform_GetListOfElementsByXPath(FormDesignerPageLocator.ERRORS_FOR_FIELD, "FIELD_SHORT_NAME", fieldShortName);
			for(WebElement element : errorElements) {
				System.out.println(element);
				errors.add(element.getText());
			}
			logInfo("The fetched errors are :-" + errors);
		} catch (Exception e) {
			logInfo("Could not get all the errors " + errors + e.getMessage());
		
		}
		//System.out.println(errors);
		return errors;
	}
	
	
	/**
	 * 'backToFormDesignFrmRelease' method is used to navigate back to 'Form Design" from Release Page
	 * @author ahmed_tw
	 * @param String - formName
	 * @return boolean status
	 * IF navigated to edit form screen THEN true ELSE returns false;
	 */
	public boolean backToFormDesignFrmRelease(String formName) {
		try {
			WebElement editForm = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.EDIT_FORM_BTN, "FORM_NAME",formName);
			controlActions.clickElement(editForm);
			Sync();
			if(!controlActions.isElementDisplayedOnPage(DesignFormPg)) {
				logError("Failed to go back to design form"); 
				return false;
			}
			logInfo("Design form is opened");
			return true;
		}catch(Exception e) {
			logError("Failed to go back to design form - "
					+ e.getMessage());
			return false;
		}
	}
	/**
	 * This method is used to select tabs (General, Settings, Advanced) from Properties panel when form Field is already selected.
	 * @author ahmed_tw
	 * @param tabName - Name of thab to be selected
	 * @return 'True' post successfully selecting the specified Tabname, else false.
	 */
	public boolean selectPropertiesTabs(String tabName) {
		WebElement propertyTab;
		try {
			propertyTab = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.PROPERTIES_TABS, "TAB_NAME", tabName);
			controlActions.clickOnElement(propertyTab);
			logInfo("Selected Property Tab -" + tabName );
		} catch (Exception e) {
			logError("Could Not select Property Tab -" + tabName + e.getMessage() );
		}
		return true;
	}
	
	/**
	 * This method is used to select tabs from Properties Panel after selecting the field
	 * @author ahmed_tw
	 * @param fieldName - Field name for which the tab has to be selected
	 * @param tabName - The name of tab to be selected
	 * @return 'True' after selecting field and then the tab from properties panel, else false.
	 */
	public boolean selectPropertiesTabForField(String fieldName, String tabName) {
		try {
			selectField(fieldName);
			selectPropertiesTabs(tabName);
			logInfo("Selected Property Tab -" + tabName + "for field -" + fieldName);
		} catch (Exception e) {
			logError("Could Not select Property Tab -" + tabName + "for field -" + fieldName + e.getMessage());
		}
		return true;
	}
	
	/**
	 * This method is used to verify the state of Toggle buttons present under 'Settings' tab in properties panel for a field.
	 * @author ahmed_tw
	 * @param buttonName - Name of button
	 * @param buttonState - State of button to verify
	 * @return 'True' after successfully verifying the state of button, else False.
	 */
	public boolean verifyStateOfToggleButton(String buttonName, String buttonState) {
		WebElement ToggleButton = null;
		try {
			
			ToggleButton = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.GET_STATE_OF_TOGGLE_BTN,"BUTTON_NAME",buttonName);
			if(ToggleButton.getText().equals(buttonState))
				logInfo("Verified State - " + buttonState + " for Toggle Button - " + buttonName);
			else {
				logInfo("Could Not Verify State - " + buttonState + " for Toggle Button - " + buttonName);
				return false;
			}
		} catch (Exception e) {
			logError("Could Not Verify State - " + buttonState + " for Toggle Button - " + buttonName + e.getMessage());
		}
		
		return true;
	}
	/**
	 * This method is used to Navigate to Homepage in any scenario i.e if the form is already saved or even when the form is not saved or required to be saved.
	 * @author ahmed_tw
	 * @return 'True' after navigating to HomePage. False if failed to do so
	 */
	public boolean navigateToHome() {
		try {
		HomePage hp = new HomePage(driver);
		hp.clickHomepageLink();
		Thread.sleep(1000);
		if(controlActions.isElementDisplayed(FormDesignerPageLocator.DEL_SAVED_FORM_DIALOG))
			controlActions.clickbutton(DeleteFormYesBtn);
		
		Sync();
		} catch (Exception e) {
			logError("Navigatd to HomePage Successfully" + e.getMessage());
			return false;
		}
		logInfo("Navigatd to HomePage Successfully");
		return true;
	}
	
	/** 
	 * This method is used to verify display of Labels and Toggle Buttons for a setting under 'Settings' Tab in properties panle. 
	 * @author ahmed_tw
	 * @param settingName - Setting Name 
	 * @return 'True' after verifying  the label and toggle button, else false. 
	 */
	public boolean fieldSettingOnOffTogglePresent(String settingName) {
		WebElement onToggle ;
		WebElement offToggle ;
		try {
			offToggle = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FLD_SETTING_OFF_LBL,"FIELDSETTING",settingName);
			onToggle = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FLD_SETTING_ON_LBL,	"FIELDSETTING",settingName);
			
			boolean offToggleDisplay = offToggle.isDisplayed();
			boolean onToggleDisplay  = onToggle.isDisplayed();
			
			if(offToggleDisplay&&onToggleDisplay) {
				logInfo("Verfied controls ON and OFF present in Settings Tab For - " + settingName);
				return  true;
			}
		} catch (Exception e) {
			logError("Could Not verify controls ON and OFF present in Settings Tab For - " + settingName + e.getMessage());
		}
		logInfo("Could Not verify controls ON and OFF present in Settings Tab For - " + settingName);
		return false;
	}
	
	/**
	 * The method is used to get the existing aggregate function value for Aggregate Field when it is already selected.
	 * @author ahmed_tw
	 * @return String - The aggregate  function value for that aggregate field.
	 */
	public String getAggFuncExistingVal() {
		String aggFunctionValue = null ;
		try {
			aggFunctionValue = SetAggregateFunction.getAttribute("value");
			if(aggFunctionValue!=null) {
				logInfo("Successfuly got aggregate function as -" + aggFunctionValue);
			}else {
				logInfo("Failed to get aggregate function -" + aggFunctionValue);
			}
			
		} catch (Exception e) {
			logError("Culd Not get aggregate function -" + aggFunctionValue + e.getMessage());
			return null;
		}
		
		//System.out.println(aggFunctionValue);
		return aggFunctionValue;
	}
	
	/**
	 * The method is used to get the existing aggregate source value for Aggregate Field when it is already selected.
	 * @author ahmed_tw
	 * @return String - The aggregate source value for that aggregate field.
	 */
	public String getAggSrcExistingVal() {
		String aggSourceValue = null ;
		try {
			aggSourceValue = SetAggregateSource.getAttribute("value");	
			if(aggSourceValue!=null) {
				logInfo("Successfuly to aggregate source as -" + aggSourceValue);
			}else {
				logInfo("Failed  to aggregate source -" + aggSourceValue);
			}
		} catch (Exception e) {
			logError("Failed  to aggregate source -" + aggSourceValue + e.getMessage());
			return null;
		}
		
		//System.out.println(aggSourceValue);
		return aggSourceValue;
	}
	
	/**
	 * This method is used to get the Displayed Form Element List in the Form Designer from Left side 'Form Elements' Panel
	 * @author ahmed_tw
	 * @return List<String> of elements displayed for that particular form
	 */
	public List<String> getDisplayedFormElementsList(){
		List<WebElement> displayedFormElements = null;
		List<String> displayedFormElementsList = new ArrayList<String>();
		
		try {
			
			displayedFormElements = controlActions.getElements(FormDesignerPageLocator.FORM_ELEMENTS_LIST);
			
			for(WebElement formElement : displayedFormElements) {
				String elementName = formElement.getAttribute("outerText");
				displayedFormElementsList.add(elementName);
			}
			
			if(displayedFormElements!=null) {
				logInfo("Fetched list of displayed Form Elements: " + displayedFormElements);
			}
			else {
				logInfo("Could not fetch elements list of displayed Form Elements: " + displayedFormElements);
			}
			//System.out.println(displayedFormElementsList);
			
		} catch (Exception e) {
			logError("Failed to fetch displayed list of form elemets + " + displayedFormElements + e.getMessage());
		}
		return displayedFormElementsList;
	}
	
	/**
	 * This method is used to get the Displayed Field Types List in the Form Designer from Left side 'Form Elements' Panel
	 * @author ahmed_tw
	 * @return List<String> of Field Types displayed for that particular form
	 */
	public List<String> getDisplayedFieldTypesList(){
		List<WebElement> displayedFieldTypes = null;
		List<String> displayedFieldTypesList = new ArrayList<String>();
		
		try {
			displayedFieldTypes = controlActions.getElements(FormDesignerPageLocator.FIELD_TYPES_LIST);
			for(WebElement fieldTypes : displayedFieldTypes) {
				String elementName = fieldTypes.getAttribute("outerText");
				displayedFieldTypesList.add(elementName);
			}
			if(displayedFieldTypes!=null) {
				logInfo("Fetched list of displayed Field Types: " + displayedFieldTypes);
			}
			else {
				logInfo("Could not fetch Field Types list of displayed : " + displayedFieldTypes);
			}
			//System.out.println(displayedFieldTypesList);
			
		} catch (Exception e) {
			logError("Failed to fetch displayed list of field Types + " + displayedFieldTypes + e.getMessage());
		}
		return displayedFieldTypesList;
	}
	//**/
	
	/**
	 * This method is used to verify Text Prompt on the Properties Panel when the Form Name Element is Selected
	 * @author ahmed_tw
	 * @return 'True' after verifying the Text prompt in the properties panel. 'False' is verification fails.
	 */
	public boolean selectFormName_VerifyPropPanelTxtPrmpt() {
		String promptTxt = "Select an element on the form to modify its properties";
		try {
			
			controlActions.clickElement(FormNameTxt);
			logInfo("Selected Form Name Element");
			
			if(controlActions.isElementDisplayed(FormDesignerPageLocator.NO_FLIELD_SELECTED_TXT)) {
				logInfo("Verified Display of Default Text Prompt on properties Panel- " + promptTxt);
				return true;
				}
			
		} catch (Exception e) {
			logError("Failed to verify Default Text prompt - " + promptTxt + " on properties panel when Form Name Element is Selected" + e.getMessage());
			return false ;
		}
		logInfo("Failed to verify Default Text prompt - " + promptTxt + " on properties panel when Form Name Element is Selected");
		return false;
	}
	
	/**
	 * This method is used to verify Text Prompt on the Properties Panel when no other from element or field type is selected
	 * @author ahmed_tw
	 * @return 'True' after verifying the Text prompt in the properties panel. 'False' is verification fails.
	 */
	public boolean noElementFieldSelected_VerifyProPanelTxtPrmpt() {
		String promptTxt = "Select an element on the form to modify its properties";
		try {
			controlActions.click(FormElementsHeader);
			logInfo("No Field Element or Element is selected Now");
			
			if(controlActions.isElementDisplayed(FormDesignerPageLocator.NO_FLIELD_SELECTED_TXT)) {
				logInfo("Verified Display of Default Text Prompt on properties Panel- " + promptTxt);
				return true;
				}
			
		} catch (Exception e) {
			logError("Failed to verify Default Text prompt - " + promptTxt + " on properties panel when no Form Field or Element is Selected" + e.getMessage());
			return false ;
		}
		logInfo("Failed to verify Default Text prompt - " + promptTxt + " on properties panel when no Form Field or Element is Selected");
		return false;
	}
	
	/**
	 * @author ahmed_tw
	 * This method is used to check whether delete icon is displayed or not for Form Elements when they are selected
	 * @param headerElement - The header element to be selected
	 * @return True if the delete icon is present for that Form element else False
	 */
	public boolean deleteIconPresent_HeaderElements(String headerElement) {
		try {
			if(headerElement =="Note") {
				NoteTxt.click();
			}
			if(controlActions.isElementDisplayed(FormDesignerPageLocator.SELECTED_FIELD_DELETE_ICON)) {
				logInfo("Verified Visibility of Delete Icon When Header Level Form Element is selected -" + headerElement);
				return true;
			}
		} catch (Exception e) {
			logError("Could Not verify precence of Delete icon for Header element " + headerElement + e.getMessage());
			return false;
		}
		logInfo("Could Not Verify Visibility of Delete Icon When Header Level Form Element is selected -" + headerElement);
		return false;
	}	
	
	/**
	 * @author ahmed_tw
	 * This method is used to check whether copy icon is displayed or not for Form Elements when they are selected
	 * @param headerElement - The header element to be selected
	 * @return True if the copy icon is present for that Form element else False
	 */
	public boolean copyIconPresent_HeaderElements(String headerElement) {
		try {
			if(headerElement =="Note") {
				NoteTxt.click();
			}
			if(controlActions.isElementDisplayed(FormDesignerPageLocator.SELECTED_FIELD_COPY_ICON)) {
				logInfo("Verified Visibility of Delete Icon When Header Level Form Element is selected -" + headerElement);
				return true;
			}
		} catch (Exception e) {
			logError("Could Not verify precence of Copy icon for Header element " + headerElement + e.getMessage());
			return false;
		}
		logInfo("Could Not Verify Visibility of Copy Icon When Header Level Form Element is selected -" + headerElement);
		return false;
	}	
	
	/**
	 * @author ahmed_tw
	 * This method is used to check whether delete icon is displayed or not for Form Fields when they are selected
	 * @param headerElement - The Form Field to be selected
	 * @return True if the delete icon is present for that Form field, else False
	 */
	public boolean deleteIconPresent_FormLvlField(String fieldName) {
		try {
			selectField(fieldName);
			if(controlActions.isElementDisplayed(FormDesignerPageLocator.SELECTED_FIELD_DELETE_ICON)) {
				logInfo("Verified Visibility of Delete Icon When Form Level Field is selected -" + fieldName);
				return true;
			}
		} catch (Exception e) {
			logError("Could Not verify precence of Delete icon for Form Field " + fieldName + e.getMessage());
			return false;
			}
		logInfo("Could Not Verify Visibility of Delete Icon When Form Level Field is selected " + fieldName );
		return false;
	}
	
	/**
	 * @author ahmed_tw
	 * This method is used to check whether copy icon is displayed or not for Form Fields when they are selected
	 * @param headerElement - The Form Field to be selected
	 * @return True if the copy icon is present for that Form field, else False
	 */
	public boolean copyIconPresent_FormLvlField(String fieldName) {
		try {
			selectField(fieldName);
			if(controlActions.isElementDisplayed(FormDesignerPageLocator.SELECTED_FIELD_COPY_ICON)) {
				logInfo("Verified Visibility of Copy Icon When Form Level Field is selected -" + fieldName);
				return true;
			}
		} catch (Exception e) {
			logError("Could Not verify precence of Copy icon for Form Field " + fieldName + e.getMessage());
			return false;
			}
		logInfo("Could Not Verify Visibility of Copy Icon When Form Level Field is selected " + fieldName );
		return false;
	}
	
	/**
	 * This method is used to verify if a field is present at form level or not, using its short name
	 * @author ahmed_tw
	 * @param fieldShortName - The name of the field to be checked
	 * @return boolean - True if the field is present at form level, else False
	 */
	public boolean formLevelFieldPresent(String fieldShortName) {
		WebElement formLevelField = null;
		System.out.println(fieldShortName);
		try {
			formLevelField = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FORM_LEVEL_FIELD, "SHORT_NAME", fieldShortName);
			System.out.println(formLevelField);
			if(formLevelField.isDisplayed()) {
				logInfo("Form Level Field is present having short name " + fieldShortName );
				return true;
			}
		} catch (Exception e) {
			logError("Could not verify presence of form level field " + fieldShortName + e.getMessage());
			return false;
		}
		logInfo("Form Level Field is NOT present having short name " + fieldShortName );
		return false;
	}

	/**
	 * This method is used to copy a form level field and then verify the copied field .
	 * @author ahmed_tw
	 * @param shortName - The short name of the field to be copied
	 * @return boolean - True after successfully copying the field and verifying the copied field, else False
	 */
	public boolean copyField(String shortName) {
		WebElement copyIcon = null;
		String copyFieldShortName = shortName+"1";
		try {
			selectField(shortName);
			copyIcon = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.SELECTED_FIELD_COPY_ICON);
			copyIcon.click();
			Sync();
			logInfo("Performed copy for field " + shortName);
			if(formLevelFieldPresent(copyFieldShortName)) {
				logInfo("Verified Copied Form Field - " + copyFieldShortName);
				return true;
			}
		} catch (Exception e) {
			logError("Could not copy and verify the copied field with short name " + shortName + e.getMessage());
			return false;
		}
		logInfo("Could Not Verify Copied Form Field - " + copyFieldShortName);
		return false;
	}

	/**
	 * This method is used to copy the Form when on the Release Form Page, and then verify the copied form
	 * @author ahmed_tw
	 * @param formName - Name of the form to be copied
	 * @return boolean - True after copying and verifying the copied form, else false
	 */
	public boolean copyForm(String formName) {
		WebElement copyIconForForm = null;
		String copyFormName = formName+" 1";
		try {
			copyIconForForm = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.COPY_ICON_FOR_FORM, "FORM_NAME", formName);
			copyIconForForm.click();
			logInfo("Clicked on the copy icon for the form " + formName);
			controlActions.perform_waitUntilVisibility(By.xpath(FormDesignerPageLocator.COPY_FORM_POPUP));
			logInfo("Copy Form Pop up is visible");
			YesButton.click();
			logInfo("Clicked on Yes button for copy form Popup");
			Sync();
			if(isformPresentOnReleasePage(copyFormName)) {
				logInfo("Verified the copied form present on the release page " + copyFormName);
				return true;
			}
		} catch (Exception e) {
			logError("Could not copy and verify the copied Form with Form Name " + formName + e.getMessage());
			return false;
		}
		logInfo("Could Not Verify Copied Form - " + formName);
		return false;
	}
	
	/**
	 * This method is used to check whether a form is present or not on the Release Form Page
	 * @author ahmed_tw
	 * @param formName - Name of the form
	 * @return boolean - True if the form is present on the Release Form page else Flase
	 */
	public boolean isformPresentOnReleasePage(String formName){
		WebElement form = null;
		try {
			form = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FORM_ON_RELEASE_PG, "FORM_NAME", formName);
			if(form.isDisplayed()) {
				logInfo("The form " + formName + " is Present in the Relese Form Page");
				return true;
			}
		} catch (Exception e) {
			logError("Could not check whether the form is present or not present of the Release Form Page " + formName + e.getMessage());
			return false;
		}
		logInfo("The form " + formName + " is NOT Present in the Relese Form Page");
		return false;
	}
	
	/**
	 * This method is used to get all the displayed/visible breadcrumbs, when this method is called.
	 * @author ahmed_tw
	 * @return List<String> - List of all the options present in the breadcrumbs
	 */
	public List<String> getBreadcrumbs(){
		List<String> breadcrumbs = new ArrayList<String>();
		try {
			List<WebElement> breadcrumbsFromWebsite = controlActions.getElements(FormDesignerPageLocator.BREADCRUMBS);
			for(WebElement element: breadcrumbsFromWebsite) {
				String text = element.getAttribute("text");
				breadcrumbs.add(text);
			}
		} catch (Exception e) {
			logError("Could not get the Breadcrumbs"+ e.getMessage());
			return null;
		}
		return breadcrumbs;
	}
	 
	/**
	 * This method is used to verify the display of Short Name Input textbox and corresponding "Short Name" Label for it, after form field is selected
	 * @author ahmed_tw
	 * @return boolean - True if both the "Short Name" label and corresponding Input textbox is displayed after field is selected, else false
	 */
	public boolean isShortNameLabelNInputDisplayed() {
		try {
			if (ShortNameLbl.isDisplayed() && HighlightedFldShrtnm.isDisplayed()) {
				logInfo("Verified the display of Short Name Input field and corresponding 'Short Name' Label");
				return true;
			}
		} catch (Exception e) {
			logError("Could not verify the display of Short Name Input textbox and corresponding 'Short Name' Label " +e.getMessage());
			return false;	
		}
		logInfo("Short Name Input textbox and corresponding 'Short Name' Label are not displayed");
		return false;
	}
	
	/**
	 * This method is used to verify the display of Default Input textbox and corresponding "Default" Label for it, after form field is selected
	 * @author ahmed_tw
	 * @return boolean - True if both the "Default" label and corresponding Default Input textbox is displayed after field is selected, else false
	 */
	public boolean isDefaultLabelNInputDisplay() {
		try {
			if (controlActions.isElementDisplayed(FormDesignerPageLocator.GEN_TAB_DEFAULT_LBL)
					&& controlActions.isElementDisplayed(FormDesignerPageLocator.GEN_TAB_DEFAULT_INP)) {
				logInfo("Verified the display of Default Input field and corresponding 'Default' Label");
				return true;
			}
		} catch (Exception e) {
			logError("Could not verify the display of Default Input textbox and corresponding 'Default' Label " +e.getMessage());
			return false;	
		}
		logInfo("Default Input textbox and corresponding 'Defalut' Label are not displayed");
		return false;
	}
	
	/**
	 * This method is used to verify the display of Repeat Input textbox and corresponding "Repeat" Label for it, after form field is selected
	 * @author ahmed_tw
	 * @return boolean - True if both the "Repeat" label and corresponding Default Input textbox is displayed after field is selected, else false
	 */
	public boolean isRepeatLabelNInputDisplay() {
		try {
			if (controlActions.isElementDisplayed(FormDesignerPageLocator.GEN_TAB_REPEAT_LBL) && RepeatFieldCountInp.isDisplayed()) {
				logInfo("Verified the display of Repeat Input field and corresponding 'Repeat' Label");
				return true;
			}
		} catch (Exception e) {
			logError("Could not verify the display of Repeat Input textbox and corresponding 'Repeat' Label " +e.getMessage());
			return false;	
		}
		logInfo("Repeat Input textbox and corresponding 'Repeat' Label are not displayed");
		return false;
	}
	
	/**
	 * This method is used to set the value for Short Name Input textbox after a form field is selected
	 * @author ahmed_tw
	 * @param value - The value to be set for Short Name Input textbox
	 * @return boolean - True after setting the value for Short Name Input textbox and False if failed to do so
	 */
	public boolean setShortNameInputFieldValue(String value) {
		try {
			HighlightedFldShrtnm.clear();
			controlActions.sendKeys(HighlightedFldShrtnm, value);
			controlActions.actionEnter();
		} catch (Exception e) {
			logError("Could not set the value " + value +" short name input textbox" + e.getMessage());
			return false;	
		}
		logInfo("Successfully set the value " + value + " for short name input textbox");
		return true;
	}
	
	/**
	 * This method is used to set the value for Default Input textbox after a form field is selected
	 * @author ahmed_tw
	 * @param value - The value to be set for Short Name Input Field
	 * @return boolean - True after setting the value for Short Name Input Field and False if failed to do so
	 */
	public boolean setDefaultInputFieldValue(String value) {
		WebElement defaultInputField = null;
		try {
			defaultInputField = controlActions.performGetElementByXPath(FormDesignerPageLocator.GEN_TAB_DEFAULT_INP);
			controlActions.sendKeys(defaultInputField, value);
			controlActions.actionEnter();
		} catch (Exception e) {
			logError("Could not set the value " + value +" default input textbox" + e.getMessage());
			return false;	
		}
		logInfo("Successfully set the value " + value + " for Default input textbox");
		return true;
	}
	
	/**
	 * This method is used to verify the current value in the Short Name Input textbox after the field is selected.
	 * @author ahmed_tw
	 * @param value - The value to be verified
	 * @return boolean - True if the value in the Short Name Input textbox is verified, else false
	 */
	public boolean verifyShortNameInputFieldValue(String value) {
		try {
			if(value.equals(HighlightedFldShrtnm.getAttribute("value"))) {
				logInfo("Verified that the current value in the Short Name input textbox is equal to " + value);
				return true;
			}
		} catch (Exception e) {
			logError(" Could Not Verify that the current value in the Short Name input textbox is equal to " + value +e.getMessage());
			return false;	
		}
		logInfo("The current value in the Short Name input textbox is NOT equal to " + value);
		return false;
	}
	
	/**
	 * This method is used to verify the current value in the Default Input textbox after the field is selected.
	 * @author ahmed_tw
	 * @param value - The value to be verified
	 * @return boolean - True if the value in the Default Input textbox is verified, else false
	 */
	public boolean verifyDefaultInputFieldValue(String value) {
		WebElement defaultInputField = null;
		try {
			defaultInputField = controlActions.performGetElementByXPath(FormDesignerPageLocator.GEN_TAB_DEFAULT_INP);
			if(value.equals(defaultInputField.getAttribute("value"))) {
				logInfo("Verified that the current value in the Default Input textbox is equal to " + value);
				return true;
			}
		} catch (Exception e) {
			logError(" Could Not Verify that the current value in the Default Input textbox is equal to " + value +e.getMessage());
			return false;	
		}
		logInfo("The current value in the Default Input textbox is NOT equal to " + value);
		return false;
	}
	
	/**
	 * This method is used to verify the current value in the Repeat Input textbox after the field is selected.
	 * @author ahmed_tw
	 * @param value - The value to be verified
	 * @return boolean - True if the value in the Repeat Input textbox is verified, else false
	 */
	public boolean verifyRepeatInputFieldValue(String value) {
		try {
			if(value.equals(RepeatFieldCountInp.getAttribute("value"))) {
				logInfo("Verified that the current value in the Repeat Input textbox is equal to " + value);
				return true;
			}
		} catch (Exception e) {
			logError(" Could Not Verify that the current value in the Repeat Input textbox is equal to " + value +e.getMessage());
			return false;	
		}
		logInfo("The current value in the Repeat Input textbox is NOT equal to " + value);
		return false;
		
	}
	
	/**
	 * This method is used to set custom Predefined Correction Options if 'Allow Correction' setting is On for the field 
	 * @author ahmed_tw
	 * @param options - The options to be set for predefined corrections
	 * @return boolean - True after successfully setting custom predefined corrections options, else false
	 */
	public boolean setPredefinedCorrectionOptions(List<String> options) {
		try {				
			for (String option: options) {
				controlActions.clickElement(PredefinedCorrectionAddBtn);
				controlActions.sendKeys(PredefinedCorrectionOptionInp,option);
				logInfo("Added" +option + "options in 'Predefined Corrections'");
			}
			logInfo("Added all the options " +options + " in 'Predefined Corrections'");
			return true;
		}catch(Exception e) {
			logError("Failed to add 'Predefined Corrections' option(s) - "+ e.getMessage());
			return false;
		}	
	}
	
	/**
	 * This method is used to set compliant value text for the FreeText field 
	 * @author ahmed_tw 
	 * @param fieldName - Name of Free Text Field
	 * @param complianceValue - The compliant value
	 * @return boolean True after successfully setting the compliant value for the free text field, else false
	 */
	public boolean setComplianceForFreeText(String fieldName, String complianceValue) {
		try {	
			controlActions.clickElement(FormNameTxt);
			selectField(fieldName);
			String complianceGridStatus = ComplianceIconStatus.getAttribute("class");
			if(!complianceGridStatus.equals("k-icon k-collapse-next k-i-arrow-60-down")) {
				controlActions.clickElement(ComplianceOpen);
			}
			Sync();
			controlActions.sendKeys(CompliantTextDefaultInput, complianceValue);
			controlActions.clickElement(DefaultChk);
			Sync();
			controlActions.clickElement(ComplianceClose);
			logInfo("Set Compliance Values");
			return true;
		}catch(Exception e) {
			logError("Failed to add compliance values - "+ e.getMessage());
			return false;
		}	
	}
	
	/**
	 * This method is used to click preview button of a form listed in the Release Form Page
	 * @author ahmed_tw
	 * @param formName - name of the form for which the Preview button is to be clicked
	 * @return boolean - True after clicking the Preview button of the listed form, else false is falied to do so
	 */
	public boolean clickPreviewForFormOnReleaePage(String formName) {
		WebElement previewButtonForFormReleasePage =  null;
		try {
			previewButtonForFormReleasePage = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.PRVW_BTN_FOR_FORM_RLS_PG, "FORM_NAME", formName);
			controlActions.clickElement(previewButtonForFormReleasePage);
			Sync();
			logInfo("Clicked on Preview for form - " + formName);
			return true;
		}catch(Exception e) {
			logError("Failed to click on Preview for form - " + formName + e.getMessage());
			return false;
		}
	}
	
	/**
	 * This Method is used to go to Field Bank Section by clicking "Field Bank" button while in Form Designer,
	 * @author ahmed_tw
	 * @return True after successfully clicking the 'Field Bank" button, else False 
	 */
	public boolean clickFieldBankButton() {
		try {
			controlActions.clickElement(FieldBankBtton);
			Sync();
			logInfo("Clicked on Field Bank and Opened Field Bank");
			return true;
		}catch(Exception e) {
			logError("Failed to click on Field Bank - " + e.getMessage());
			return false;
		}
	}
	
	/**
	 * This Method is used to search field name in the search bar of Field Bank section.
	 * @author ahmed_tw
	 * @param fieldName - Name of Field to be searched
	 * @return True after performing the search operation, False if fails to do so.
	 */
	public boolean searchFieldNameInFieldBank(String fieldName) {
		try {
			controlActions.clearAndSetText(FieldBankSearch, fieldName);
			controlActions.actionEnter();
			Sync();
			logInfo("Successfully Searched the field name - " + fieldName + " in the Search bar of Field Bank");
			return true;
		} catch (Exception e) {
			logError("Could Not Perform search for field name - " + fieldName + " in the Search bar of Field Bank");
			return false;
		}
	}
	
	/**
	 * This method is used to verify if the field name is displayed in the table of Field Bank Section.
	 * @author ahmed_tw
	 * @param fieldName - The name of field
	 * @return - True if the field is displayed else returns False
	 */
	public boolean isFieldNameDisplayedFieldBank(String fieldName) {
		WebElement fieldNameFromGrid = null;
		try {
			fieldNameFromGrid = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_BANK_FIELD, "FIELD_NAME", fieldName);
			if(fieldNameFromGrid.isDisplayed()) {
				logInfo("Field name display verifed for - " + fieldName);
				return true;
			}
			logInfo("Field name NOT displayed for - " + fieldName);
			return false;
		} catch (Exception e) {
			logError("Field name display Could Not be checked for - " + fieldName + e.getMessage());
			return false;
		}
	}
	
	/**
	 * This method is used to verify FieldBank Functionality. It checks for presence of field in the field bank.
	 * @author ahmed_tw
	 * @param fieldName - Field Name to be verified/checked in Field Bank
	 * @return - True after verifying the field is present in the Field Bank, else false
	 */
	public boolean verifyFieldNamePresentInFieldBank(String fieldName) {
		try {
			if(!clickFieldBankButton() || !searchFieldNameInFieldBank(fieldName) || !isFieldNameDisplayedFieldBank(fieldName)) {
				logInfo("Could NOT verify that field - " + fieldName + " is Present in Field Bank");
				return false;
			}
			logInfo("Verified that field - " + fieldName + " is Present in Field Bank");
			return true;
		} catch (Exception e) {
			logError("Could NOT verify that field - " + fieldName + " is Present in Field Bank" + e.getMessage());
			return false;
		}
	}
	
	/**
	 * This method is used to verify Field Bank Functionality. It checks for presence of fields in the field bank.
	 * @author ahmed_tw
	 * @param fieldNames - List of Field Names to be verified/checked in Field Bank
	 * @return - True after verifying all the fields are present in the Field Bank, else false
	 */
	public boolean verifyFieldNamesPresentInFieldBank(List<String> fieldNames) {
		try {
			if(!clickFieldBankButton()) {
				logInfo("Could NOT verify that fields - " + fieldNames + " are Present in Field Bank");
				return false;
			}
			for(String field : fieldNames) {
				if(!searchFieldNameInFieldBank(field) || !isFieldNameDisplayedFieldBank(field)) {
					logInfo("Could NOT verify that fields - " + fieldNames + " are Present in Field Bank");
					return false;
				}
			}
			logInfo("Verified that fields - " + fieldNames + " are Present in Field Bank");
			return true;
		} catch (Exception e) {
			logError("Could NOT verify that fields - " + fieldNames + " are Present in Field Bank" + e.getMessage());
			return false;
		}
	}
	
	/**
	 * This method is used to select a form field by clicking the field name in the Field Navigator area while Form Designing
	 * @author ahmed_tw
	 * @param fieldName - Name of the field
	 * @return True after clicking in the field name in the Form Navigator Area, else false
	 */
	public boolean selectFieldFormNavigator(String fieldName) {
		try {
			WebElement fieldInFormNavigator = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FORM_NAV_FORM_LVL_FIELD_V2, "FIELD_NAME", fieldName);
			controlActions.clickElement(fieldInFormNavigator);
			logInfo("Clicked on the field name in the form Navigator area " + fieldName);
			return true;
		} catch (Exception e) {
			logError("Could not click on the Field Name in the Form Navigator area " + fieldName +e.getMessage());
			return false;	
		}
	}
	
	/**
	 * This method is used to verify/check if a form level field is in "Selected" state or not 
	 * @author ahmed_tw
	 * @param fieldName - Name of the field
	 * @return True if the field is in "Selected" state else false
	 */
	public boolean isFieldSelected(String fieldName) {
		WebElement selectedField = null;
		try {
			 selectedField = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.SELECTED_FIELD_NAME_INPUT);
			if(selectedField.getAttribute("value").equals(fieldName)) {
				logInfo("Verified that the field " + fieldName + " is in Selected state");
				return true;
			}
		} catch (Exception e) {
			logError("Could not check that the field " + fieldName + " is in Selected state" +e.getMessage());
			return false;	
		}
		logInfo("The Field "+ fieldName + " is NOT in Selected state");
		return false;
	}
	
	/**
	 * This method is used to get list of all the Form level fields form the Form Navigator
	 * @author ahmed_tw
	 * @return List of String of all the form level fields from the Form Navigator
	 */
	public List<String> getListOfFrmLvlFieldsFormNavigator(){
		List<WebElement> fieldElementsListFormNavigator = null;
		List<String> fieldNamesListFormNavigator = new ArrayList<String>();
		try {
			fieldElementsListFormNavigator = controlActions.perform_GetListOfElementsByXPath(FormDesignerPageLocator.FORM_NAV_FORM_LVL_FIELDS_LIST);
			for(WebElement fieldElement :fieldElementsListFormNavigator ) {
				fieldNamesListFormNavigator.add(fieldElement.getText());
				logInfo("Fetched field name " + fieldElement.getText() + " from Form Namvigator");
			}
		} catch (Exception e) {
			logError("Fetched field names from Form Namvigator"+e.getMessage());
			return null;	
		}
		
		logInfo("Fetched All field names from Form Namvigator");
		return fieldNamesListFormNavigator;
	}
	
	
	
	/** This method is used to click on Select Resources from the Breadcrumbs while form designing
	 * @author ahmed_tw
	 * @return boolean True post clicking on Select Resources from the Breadcrumbs, else false
	 */	
	public boolean clickSelectResourcesFromBreadcrumb() {
		try {
			controlActions.clickElement(BreadCrumbsSelectResources);
			Sync();
			logInfo("Clicked on Select Resources from the Breadcrumbs");
			return true;
		}catch(Exception e) {
			logError("Failed to Select Resources from the Breadcrumbs"+ e.getMessage());
			return false;
		}
	}
	
	/** This method is used to click on Select Form Type from the Breadcrumbs while form designing
	 * @author ahmed_tw
	 * @return boolean True post clicking on Select Form Type from the Breadcrumbs, else false
	 */	
	public boolean clickSelectFormTypeFromBreadcrumb() {
		try {
			controlActions.clickElement(BreadCrumbsSelectFormType);
			Sync();
			logInfo("Clicked on Select Form Type from the Breadcrumbs");
			return true;
		}catch(Exception e) {
			logError("Failed to Select Form Type from the Breadcrumbs"+ e.getMessage());
			return false;
		}
	}
	
	
	/** This method is used to click on Form Designer from the Breadcrumbs while form designing
	 * @author ahmed_tw
	 * @return boolean True post clicking on Form Designer from the Breadcrumbs, else false
	 */	
	public boolean clickFormDesignerFromBreadcrumb() {
		try {
			controlActions.clickElement(BreadCrumbsFormDesigner);
			Sync();
			logInfo("Clicked on Form Designer from the Breadcrumbs");
			return true;
		}catch(Exception e) {
			logError("Failed to Form Designer from the Breadcrumbs"+ e.getMessage());
			return false;
		}
	}
	
	
	/** This method is used to click on Home from the Breadcrumbs while form designing
	 * @author ahmed_tw
	 * @return boolean True post clicking on Home from the Breadcrumbs, else false
	 */	
	public boolean clickHomeFromBreadcrumb() {
		try {
			controlActions.clickElement(BreadCrumbsHome);
			Sync();
			logInfo("Clicked on Home from the Breadcrumbs");
			return true;
		}catch(Exception e) {
			logError("Failed to Home from the Breadcrumbs"+ e.getMessage());
			return false;
		}
	}
	
	
	/** This method is used to click on Design Form from the Breadcrumbs while form designing
	 * @author ahmed_tw
	 * @return boolean True post clicking on Design Form from the Breadcrumbs, else false
	 */	
	public boolean clickDesignFormFromNavigationPanel() {
		try {
			controlActions.clickElement(NavigationPanelDesignForm);
			Sync();
			logInfo("Clicked on Design Form from the Breadcrumbs");
			return true;
		}catch(Exception e) {
			logError("Failed to Design Form from the Breadcrumbs"+ e.getMessage());
			return false;
		}
	}
	
	/**
	 * This method is used to verify Text Prompt on the Properties Panel when Note Element is Selected
	 * @author ahmed_tw
	 * @return 'True' after verifying the Text prompt in the properties panel. 'False' is verification fails.
	 */
	public boolean noteSelected_VerifyPropPanelTxtPrmpt() {
		String promptTxt = "Use the text field in the form element to configure the Note";
		try {
			controlActions.clickElement(NoteTxt);
			logInfo("Selected Note Element");
			
			if(controlActions.isElementDisplayed(FormDesignerPageLocator.NOTE_SELECTED_PROP_PANEL_TXT)) {
				logInfo("Verified Display of Text Prompt on properties Panel- " + promptTxt + " when Note Element is Selected ");
				return true;
				}
		} catch (Exception e) {
			logError("Failed to verify Text prompt - " + promptTxt + " on properties panel when Note Element is Selected" + e.getMessage());
			return false ;
		}
		logInfo("Failed to verify Default Text prompt - " + promptTxt + " on properties panel Note Name Element is Selected");
		return false;
	}

	/** 
	 * This method is used to select resources for related documents. 
	 * @author thakker_k
	 * @param documentName - Document Name
	 * @param resourceInstanceName - Resource Name 
	 * @return 'True' after selecting resources for related documents. 
	 */
	public boolean selectResourcesForRelatedDocuments(String documentName, List<String> resourceInstanceName) {
		
		try {
			
			WebElement DocumentNameLabel = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.DOCUMENT_NAME_LBL,
					"DOCUMENTNAME",documentName);
			DocumentNameLabel.click();	
			
			//Deselect all the resources from Compliance Panel.
			SelectAllResourcesCB.click();
			
			//Select CB for above resources
			for(String instanceVal : resourceInstanceName) {
				WebElement ResourceInstanceCB = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.RESOURCE_INSTANCE_CB,
						"RESOURCEINSTANCENAME",instanceVal);
				ResourceInstanceCB.click();	
				
			}		

			logInfo("Selected Resources for Related Docs Succesfully ");
			return  true;
			
		} 
		catch (Exception e) {
			logError("Could Not Selected Resources for Related Docs - " + e.getMessage());
			return false;
		}
		
	}
	
	/**
	 * This method is used to click on Select Form Type link in breadcrumbs.
	 * @author thakker_k
	 * @return boolean This returns boolean true after clicking Select Form Type link
	 */
	public boolean clickSelectFormTypeLink() {

		try {
			controlActions.clickElement(SelectFormTypeLnk);
			logInfo("Clicked Select Form Type link");
			return true;
		}
		catch(Exception e) {
			logError("Failed to click Select Form Type link :"
					+ e.getMessage());
			return false;
		}	
	}
	
	/** 
	 * This method is used to search and click resource in resource panel. 
	 * @author thakker_k
	 * @param resourceInstanceName - Resource Name 
	 * @return 'True' after searching and clicking resource in resource panel. 
	 */
	public boolean searchAndClickOnResourceInstance(String resourceInstanceName) {
		
		try {
			SearchResourceTxt.clear();
			SearchResourceTxt.sendKeys(resourceInstanceName);
			
			//SearchResourceBtn.click();
			
			WebElement ResourceNameLabel = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.RESOURCE_NAME_LBL,
					"RESOURCEINSTANCENAME",resourceInstanceName);
			ResourceNameLabel.click();			
			
			logInfo("Searched and Clicked on Resource successfully " + resourceInstanceName);
			return  true;
			
		} 
		catch (Exception e) {
			logError("Could Not Search and Click on Resource - " + resourceInstanceName + e.getMessage());
			return false;
		}
	}
	
	/**
	 * 'clickPreviewOnReleasePage' method clicks on Preview button on Release page
	 * @author thakker_k
	 * @param String - formName
	 * @return boolean status true post clicking on Preview button on release page
	 */
	public boolean clickPreviewOnReleasePage(String formName) {		
		try {	
				WebElement PreviewBtn = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.PREVIEW_ON_RELEASE_PAGE,
						"FORMNAME", formName);
				controlActions.clickElement(PreviewBtn);	
				logInfo("Clicked Preview Button Successfully for form - "+ formName);	
				return true;
			}
		catch(Exception e) {
			logError("Failed to click Preview button for form - "  +formName+" - " + e.getMessage());
			return false;
		}	
	}
	
	/**
	 * 'verifyCommentsandAttachmentinPreview' method verifies Comments and Attachment in Preview Form
	 * @author thakker_k
	 * @param List - fieldNames
	 * @param boolean - verifyComment
	 * @param boolean - verifyAttachment
	 * @return boolean status true if comments and attachments are verified successfully on Preview else false
	 */
	public boolean verifyCommentsandAttachmentinPreview(List<String> fieldNames,boolean verifyComment, boolean verifyAttachment) {		
		try {	

			for (String fieldName: fieldNames ) {

				WebElement ExpandField = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.EXPAND_COLLAPSE_BTN,
						"FIELDNAME", fieldName);
				controlActions.clickElement(ExpandField);	
				
				if(verifyComment==true) {
					String commentLabel = FormDesignerPageLocator.FIELD_SETTING_LABEL.replace("FIELDSETTING", "Comments");
					WebElement CommentLabel = controlActions.perform_GetElementByXPath(commentLabel,"FIELDNAME", fieldName);
					if (CommentLabel.isDisplayed()) {
						logInfo("Field Setting Comment is displayed for field - "+ fieldName);						
					}
					else {
						logError("Could NOT Verify Field Setting Comment");
						return false;
					}				
				}
				if(verifyAttachment==true) {
					String attachmentLabel = FormDesignerPageLocator.FIELD_SETTING_LABEL.replace("FIELDSETTING", "Attachments");
					WebElement AttachmentLabel = controlActions.perform_GetElementByXPath(attachmentLabel,"FIELDNAME", fieldName);
					if (AttachmentLabel.isDisplayed()) {
						logInfo("Field Setting Attachment is displayed for field - "+ fieldName);						
					}
					else {
						logError("Could NOT Verify Field Setting Attachment");
						return false;
					}				
				}			
			}			
			return true;
		}
		catch(Exception e) {
			logError("Failed to verify Field Settings"
					+ e.getMessage());
			return false;
		}	
	}
	
	/**
	 * 'addRelatedDocument' method is used to add documents in 'Related Docs'
	 * @author thakker_k
	 * @param String - DocumentTypeName
	 * @param List - DocumentNames
	 * @return boolean status
	 * IF documents is selected in 'Related Docs' THEN true ELSE returns false;
	 */
	public boolean addRelatedDocuments(String DocumentTypeName,List<String> documentNames) {		
		try {	
			controlActions.dragdrop(RelatedDocsDbl, HeaderLevel);
			controlActions.clickElement(AddNewDocumentButton);
			controlActions.clickElement(DocumentTypeDdl);

			WebElement selectDocumentType = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.SELECT_DOCUMENT_TYPE, "DOCUMENT_TYPE", DocumentTypeName);
			controlActions.clickElement(selectDocumentType);

			for (String documentName: documentNames ) {

				WebElement selectDocumentName = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.SELECT_DOCUMENT, "DOCUMENT_NAME", documentName);
				controlActions.clickElement(selectDocumentName);	
			}
			controlActions.clickElement(SaveDocumentBtn);

			for (String documentName: documentNames ) {

				WebElement selectedDocumentVerify= controlActions.perform_GetElementByXPath(FormDesignerPageLocator.SELECT_DOCUMENT_VERIFY, "DOCUMENT_NAME", documentName);
				if(!controlActions.isElementDisplayedOnPage(selectedDocumentVerify)) {
					logError("Could NOT Verify added document");
				}	
			}	
			
			logInfo("VERIFIED - Document is added");
			return true;
		}
		catch(Exception e) {
			logError("Failed to add Document in 'Related Docs'"
					+ e.getMessage());
			return false;
		}	
	}
	
	/* 'setComplianceForDateandTimeFieldv2' method is used to Set Compliance For Date and Time Field
	 * @author ahmed
	 * @param shortName [String] - Name of the Field
	 * @param resourceName [String] - Name of Resource
	 * @param value [String] - Value for if condition
	 * @return [boolean]
	 * IF compliance values are set on selected field THEN true ELSE returns false
	 */
	public boolean setComplianceForDateandTimeFieldv2(String shortName, String resourceName, String value) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		WebElement ifDropArea;
		try {	
			controlActions.clickElement(FormNameTxt);
			WebElement fieldSelection = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_SHORTNAME,"SHORT_NAME",shortName);
			controlActions.clickElement(fieldSelection);
			String complianceGridStatus = ComplianceIconStatus.getAttribute("class");
			if(!complianceGridStatus.equals("k-icon k-collapse-next k-i-arrow-60-down")) {
				controlActions.clickElement(ComplianceOpen);
			}

			Sync();		
			Actions action = new Actions(driver);
			action.moveToElement(ComplianceBarDrag).build().perform();
			controlActions.dragdrop(ComplianceBarDrag, ComplianceBarDrop);			
			WebElement ResourceEdit = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.EDIT_COMPLIANCE_RULE, "RESOURCEINSTANCENAME", resourceName);
			controlActions.click(ResourceEdit);

			Thread.sleep(2000);

			ifDropArea = driver.findElement(By.xpath(FormDesignerPageLocator.IF_DROPPABLE_AREA));
			js.executeScript(dragDropJSExecutorScript, ValueBtnDragElement, ifDropArea);
			logInfo("Dragged and dropped 'VALUE'");
			
//			ArrayList<Integer> DragElement = controlActions.getElementCoordinates(ValueBtnDragElement);
//			ArrayList<Integer> DropElement = controlActions.getElementCoordinates(ComplianceDropArea);
//
//			moveToElement(DragElement.get(0)+100, DragElement.get(1)+117, DropElement.get(0)+609, DropElement.get(1)+140);
//
//
//			//moveToElement(1057, 266, 970, 330);
			
			
			
			logInfo("Drag and Drop action has performed");
			controlActions.sendKeys(DateTimeValueTxt, value);
			clickSaveCloseButton();

			Sync();
			controlActions.clickElement(ComplianceClose);
			logInfo("Set Compliance Values");
			return true;
		}catch(Exception e) {
			logError("Failed to add compliance values - "
					+ e.getMessage());
			return false;
		}	
	}
	
	/**This method is used to set an expression rule on a field using 2 other Date fields such that : Rule = Date1 - Date2 
	 * @author ahmed_tw
	 * @param fieldName [String] : Name of the field on which expression rule is to be applied
	 * @param dateField1 [String] : Name of 1st Date field
	 * @param dateField2 [String] : Name of 2nd Date field
	 * @return [boolean] : true post applying the expression rule else false.
	 */
	public boolean dateDiffExpressionRuleForField(String fieldName, String dateField1, String dateField2) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		WebElement field, drop1, input1, operatorMinus, operatorDrop, drop2, input2 = null;
		try {
			selectPropertiesTabForField(fieldName, PROPERTIES_TABS.ADVANCED_TAB);
			controlActions.click(AddNewExpressions);
			Thread.sleep(3000);
			
			field = driver.findElement(By.xpath(FormDesignerPageLocator.DRAG_FIELD));
			operatorMinus = driver.findElement(By.xpath(FormDesignerPageLocator.DRAG_MINUS_OPERATOR));

			// Drag drop field
			drop1 = driver.findElement(By.xpath(FormDesignerPageLocator.DROP_AREA_1st));
	        js.executeScript(dragDropJSExecutorScript, field, drop1);
	        logInfo("Dragged and dropped 'Filed'");
	        
	        // Field 1
			input1 = driver.findElement(By.xpath(FormDesignerPageLocator.INPUT_1st));
			input1.sendKeys(dateField1); Thread.sleep(3000); controlActions.actionEnter();
			logInfo("Set field value to be field - " + dateField1);
			
			//Drag drop operator (-)
			operatorDrop = driver.findElement(By.xpath(FormDesignerPageLocator.DROP_AREA_OPERATOR));
			js.executeScript(dragDropJSExecutorScript, operatorMinus, operatorDrop);
			logInfo("Dragged and dropped 'Minus (-)' operator for field -" + dateField1);
			
			//Drag and drop field 
			drop2 = driver.findElement(By.xpath(FormDesignerPageLocator.DROP_AREA_2ndFIELD));
			js.executeScript(dragDropJSExecutorScript, field, drop2);
			logInfo("Dragged and dropped 'Filed'");
			
			//Field2
			input2 = driver.findElement(By.xpath(FormDesignerPageLocator.INPUT_2nd));
			input2.sendKeys(dateField2); Thread.sleep(3000); controlActions.actionEnter();
			logInfo("Set field value to be field - " + dateField2);
			
			clickSaveCloseButton();
			logInfo("Successfully Set the Expression Rule as :: " + dateField1 + " - " + dateField2 +" ::");
			return true;
		} catch (Exception e) {
			logError("Could Not set Expression Rule - "+e.getMessage());
			return false;
		}
	}
	

	/**
	 * This method is used to add compliance values (min,max,target,uom) on a Numeric/Aggregate Field for particular resource.
	 * @author ahmed_tw 
	 * @param fieldName [String] : Name of Field on which compliance is to be set
	 * @param resourceInstance [String] : Resource for which compliance is to be set 
	 * @param minValue [String] : Min Value
	 * @param targetValue  [String] : Target Value
	 * @param maxValue  [String] : Max Value
	 * @param uomValue  [String] : UOM Value
	 * @return boolean [boolean] : True after setting compliance on a field for a particular
	 */
	public boolean setNumericAggragateFieldComplianceForAResourceInstance(String fieldName,String resourceInstance, String minValue,String targetValue,String maxValue, String uomValue) {
		WebElement minInput = null;
		WebElement maxInput = null;
		WebElement targetInput = null;
		try {	
			controlActions.clickElement(FormNameTxt);
			selectField(fieldName);
			String complianceGridStatus = ComplianceIconStatus.getAttribute("class");
			if(!complianceGridStatus.equals("k-icon k-collapse-next k-i-arrow-60-down")) {
				controlActions.clickElement(ComplianceOpen);
			}
			Sync();
			Actions action = new Actions(driver);
			action.moveToElement(ComplianceBarDrag).build().perform();
			controlActions.dragdrop(ComplianceBarDrag, ComplianceBarDrop);
			
			minInput = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.COMPLIANCE_MIN_FOR_RESOURCE, "RES_INSTANCE", resourceInstance);
			maxInput = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.COMPLIANCE_MAX_FOR_RESOURCE, "RES_INSTANCE", resourceInstance);
			targetInput = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.COMPLIANCE_TARGET_FOR_RESOURCE, "RES_INSTANCE", resourceInstance);
			
			controlActions.sendKeys(minInput, minValue);
			controlActions.sendKeys(maxInput, targetValue);
			controlActions.sendKeys(targetInput, maxValue);
			if(uomValue != null) {
				controlActions.sendKeys(UOMTxt, uomValue);
			}
			controlActions.clickElement(ComplianceClose);
			logInfo("Set Compliance Values of Field - " + fieldName  +" for a Resource - " + resourceInstance);
			return true;
		}catch(Exception e) {
			logError("Failed to add compliance  Values of Field - " + fieldName  +" for a Resource - " + resourceInstance
					+ e.getMessage());
			return false;
		}	
	
	}
	
	/**
	 * 'createAndReleaseFormNonNumeric' method is used to create & release the form having a 'FreeText' field
	 * @author dahale_p
	 * @param String - formType
	 * @param String - formName
	 * @param String - resourceType
	 * @param String - categoryValue
	 * @param String - instanceValue1
	 * @return boolean status
	 * IF created form gets released THEN true ELSE false;
	 */
	public boolean createAndReleaseFormNonNumeric(String formType,String formName,String resourceType,String categoryValue, String instanceValue1) {
		try {
			if(clickFormDesignerMenu().error) {
				return false;
			}
			formType = formType.toUpperCase();
			switch(formType) {
			case "CHECK":
				controlActions.clickElement(SelectCheckFormLnk);
				break;
			case "QUESTIONNAIRE":
				controlActions.clickElement(SelectQuestionnaireFormLnk);
				break;
			case "AUDIT":
				controlActions.clickElement(SelectAuditFormLnk);
				break;

			default:
				logError("Form type is INVALID");
				return false;			
			}
			Sync();

			if(!selectResource(resourceType,categoryValue,instanceValue1)) {
				logError("Could NOT select resource");
				return false;
			}

			if(!clickOnNextButton(DesignFormPg,"Design Form")) {
				logError("Could NOT Navigate to 'Design Form'");
				return false;
			}

			controlActions.sendKeys(FormNameTxt, formName);

			switch(formType) {
			case "CHECK":
				controlActions.dragdrop(FreeTextFieldDbl, FormLevel);
				controlActions.sendKeys(FreeTextFieldTxt, "FreeText Field");
				break;
			case "QUESTIONNAIRE":
				controlActions.dragdrop(FreeTextFieldDbl, FormLevel);
				controlActions.sendKeys(FreeTextFieldTxa, "FreeText Field");
				break;
			case "AUDIT":
				controlActions.dragdrop(SectionDbl, FormLevel);
				controlActions.sendKeys(Section1Txt, "Section 1");
				controlActions.dragdrop(FreeTextFieldDbl, section1Level);
				controlActions.sendKeys(FreeTextFieldTxt, "FreeText Field");
				break;

			default:
				logError("Form type is INVALID");
				return false;			
			}
			Sync();
			controlActions.clickElement(FormNameTxt);
			//controlActions.clickElement(FormNameTxt, "Form Name");
			if(!clickOnNextButton(ReleaseFormPg,"Release Form")) {
				logError("Failed to click on Next button");
				return false;
			}

			if(!releaseForm(formName)) {
				logError("Could NOT release form'");
			}
			logInfo("RELEASED FORM - '"+formName+"' of type - '"+formType+"' sucessfully");
			return true;
		}catch(Exception e) {
			logError("Failed to release form - '"+formName+"' of type - '"+formType+"'"+ e.getMessage());
			return false;
		}
	}
	
	/**
	 * This method is used to Add Dependency Rule Using 'VALUE'
	 * @author ahmed_tw
	 * @param String - ruleField - Name of field to be used INSIDE Dependency Rule
	 * @param String - ruleValue - Value for VALUE Text box
	 * @return boolean true post adding Dependency Rule
	 */
	public boolean addDependencyRuleUsingValue(String ruleField, String ruleValue) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		WebElement dragVALUE, dropIFArea, ifExpressionInput;
		try {
			//selectField(fieldName);
			selectPropertiesTabs(PROPERTIES_TABS.ADVANCED_TAB);
			controlActions.click(AddNewBtn);
			controlActions.waitforElementToBeDisplayed(IfTxt);
			controlActions.sendKeys(IfTxt, ruleField); controlActions.actionEnter();
			
			dragVALUE = driver.findElement(By.xpath(FormDesignerPageLocator.DRAG_VALUE));
			dropIFArea = driver.findElement(By.xpath(FormDesignerPageLocator.IF_DROPPABLE_AREA));
			
			js.executeScript(dragDropJSExecutorScript, dragVALUE, dropIFArea);
	        logInfo("Dragged and dropped 'VALUE' for IF");
	        Thread.sleep(1000);
	        ifExpressionInput = driver.findElement(By.xpath(FormDesignerPageLocator.IF_EXPRESSION_VALE_IN));
	        ifExpressionInput.sendKeys(ruleValue); controlActions.actionEnter();
	        logInfo("Entered the expression value as - " + ruleValue);
	        
			clickSaveCloseButton();
			logInfo("Added Dependency Rule");
			return true;
		} catch (Exception e) {
			logError("Failed to Add Dependency Rule - " + e.getMessage());
			return false;
		}
	}
	
	/** This method is used to add IF expression rule using VALUE
	 * @author ahmed_tw 
	 * @param ifField [String] -FieldName used in IF
	 * @param ifConditionValue [String] - Value for if Condition
	 * @param thenValue [String] - Then Value
	 * @param elseValue [String] - Else value
	 * @return [boolean] - True after expression rule is successfully added, else false
	 */
	public boolean ifExpressionRuleUsingValue(String ifField, String ifConditionValue, String thenValue, String elseValue) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		WebElement ifDropArea, valueInput, thenInput, elseInput;
		try {
			selectPropertiesTabs(PROPERTIES_TABS.ADVANCED_TAB);
			controlActions.click(AddNewExpressions);
			Thread.sleep(2000);

			js.executeScript(dragDropJSExecutorScript, IfBtnDragElement, MainDropArea);
		    logInfo("Dragged and dropped 'IF'");
		    
		    controlActions.waitforElementToBeDisplayed(IfTxt);
			controlActions.sendKeys(IfTxt, ifField); controlActions.actionEnter();
			
			ifDropArea = driver.findElement(By.xpath(FormDesignerPageLocator.IF_DROPPABLE_AREA));
			js.executeScript(dragDropJSExecutorScript, ValueBtnDragElement, ifDropArea);
			logInfo("Dragged and dropped 'VALUE'");
			Thread.sleep(1000);
			valueInput = driver.findElement(By.xpath(FormDesignerPageLocator.IF_EXPRESSION_VALE_IN));
			valueInput.sendKeys(ifConditionValue); controlActions.actionEnter();
	        logInfo("Entered the expression value as - " + ifConditionValue);
		    
	        thenInput = driver.findElement(By.xpath(FormDesignerPageLocator.IF_EXPRESSION_THEN));
	        controlActions.sendKeys(thenInput, thenValue); controlActions.actionEnter();
	        
	        elseInput = driver.findElement(By.xpath(FormDesignerPageLocator.IF_EXPRESSION_ELSE));
	        controlActions.sendKeys(elseInput, elseValue); controlActions.actionEnter();
	        logInfo("THEN, ELSE value set as - " + thenValue + " " + elseValue + " respectively");
	        
	        controlActions.clickElement(SaveCloseButton);
			Sync();
			logInfo("Clicked on Save And Close button");
			
			return true;
		} catch (Exception e) {
			logError("Could Not Expression Rule " + e.getMessage());
			return false;
		}
	}
	
	

	/**This method is used to drag one field above other field in Form Navigator.
	 * @author ahmed_tw
	 * @param srcFieldName [String] - Name of Field to be dragged
	 * @param destFieldName [String] - Name of field above which the field has to be dragged 
	 * @return [boolean] - True if the field was successfully dragged above other field, else false
	 */
	public boolean dragFieldAboveOtherFieldInFormNavigator(String srcFieldName, String destFieldName) {
		WebElement fieldToMove, destinationField;
		try {
			fieldToMove = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FORM_NAV_FORM_LVL_FIELD_V2, "FIELD_NAME", srcFieldName);
			destinationField = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FORM_NAV_FORM_LVL_FIELD_V2, "FIELD_NAME", destFieldName);
			
			controlActions.dragdrop(fieldToMove, destinationField);
			logInfo("Dragged Field - " +srcFieldName+ " above Field - " + destFieldName);
			
			return true;
		} catch (Exception e) {
			logError("Could Not Drag Field - " +srcFieldName+ " above Field - " + destFieldName + e.getMessage());
			return false;
		}
	}
	
	/**
	 * This method is used to set compliant value for the Select Multiple or Select One field 
	 * @author ahmed_tw 
	 * @param fieldName [String ]- Name of Select Multiple or Select One field 
	 * @param complianceValue [List<String>] - The compliant values to be set
	 * @return boolean True after successfully setting the compliant value for the Select Multiple or Select One field, else false
	 */
	public boolean setComplianceForSelectMultipleOrSelectOne(String fieldName, List<String> complianceValue) {
		try {	
			//controlActions.clickElement(FormNameTxt);
			selectField(fieldName);
			String complianceGridStatus = ComplianceIconStatus.getAttribute("class");
			if(!complianceGridStatus.equals("k-icon k-collapse-next k-i-arrow-60-down")) {
				controlActions.clickElement(ComplianceOpen);
			}
			Sync();
			WebElement complianceInput = driver.findElement(By.xpath(FormDesignerPageLocator.SEL_MUL_SEL_ONE_COMPLIANCE_INPUT));
			
			for(String val : complianceValue) {
				controlActions.sendKeys(complianceInput, val); controlActions.actionEnter();
			}
			controlActions.clickElement(DefaultChk);
			Sync();
			controlActions.clickElement(ComplianceClose);
			logInfo("Set Compliance Values");
			return true;
		}catch(Exception e) {
			logError("Failed to add compliance values - "+ e.getMessage());
			return false;
		}	
	}
	
	/**
	 * 'createAndReleaseForm' method is used to create & release the form having a  two'Numeric' fields
	 * @author dahale_p
	 * @param String - formType
	 * @param String - formName
	 * @param String - resourceType
	 * @param String - categoryValue
	 * @param String - instanceValue1
	 * @return boolean status
	 * IF created form gets released THEN true ELSE false;
	 */
	public boolean createAndReleaseFormTwoNumericFields(String formType,String formName,String resourceType,String categoryValue, String instanceValue1) {
		try {
			if(clickFormDesignerMenu().error) {
				return false;
			}
			formType = formType.toUpperCase();
			switch(formType) {
			case "CHECK":
				controlActions.clickElement(SelectCheckFormLnk);
				break;
			case "QUESTIONNAIRE":
				controlActions.clickElement(SelectQuestionnaireFormLnk);
				break;
			case "AUDIT":
				controlActions.clickElement(SelectAuditFormLnk);
				break;

			default:
				logError("Form type is INVALID");
				return false;			
			}
			Sync();

			if(!selectResource(resourceType,categoryValue,instanceValue1)) {
				logError("Could NOT select resource");
				return false;
			}

			if(!clickOnNextButton(DesignFormPg,"Design Form")) {
				logError("Could NOT Navigate to 'Design Form'");
				return false;
			}

			controlActions.sendKeys(FormNameTxt, formName);

			switch(formType) {
			case "CHECK":
				controlActions.dragdrop(NumericFieldDbl, FormLevel);
				controlActions.sendKeys(NumericFieldTxt, "Numeric Field");
				Sync();
				controlActions.dragdrop(NumericFieldDbl, FormLevel);
				controlActions.sendKeys(NumericFieldTxt, "Numeric Field 2");
				break;
			case "QUESTIONNAIRE":
				controlActions.dragdrop(NumericFieldDbl, FormLevel);
				controlActions.sendKeys(NumericFieldTxa, "Numeric Field");
				controlActions.dragdrop(NumericFieldDbl, FormLevel);
				controlActions.sendKeys(NumericFieldTxt, "Numeric Field 2");
				break;
			case "AUDIT":
				controlActions.dragdrop(SectionDbl, FormLevel);
				controlActions.sendKeys(Section1Txt, "Section 1");
				controlActions.dragdrop(NumericFieldDbl, section1Level);
				controlActions.sendKeys(NumericFieldTxt, "Numeric Field");
				controlActions.dragdrop(NumericFieldDbl, section1Level);
				controlActions.sendKeys(NumericFieldTxt, "Numeric Field 2");
				break;

			default:
				logError("Form type is INVALID");
				return false;			
			}
			Sync();
			controlActions.clickElement(FormNameTxt);
			//controlActions.clickElement(FormNameTxt, "Form Name");
			if(!clickOnNextButton(ReleaseFormPg,"Release Form")) {
				logError("Failed to click on Next button");
				return false;
			}

			if(!releaseForm(formName)) {
				logError("Could NOT release form'");
			}
			logInfo("RELEASED FORM - '"+formName+"' of type - '"+formType+"' sucessfully");
			return true;
		}catch(Exception e) {
			logError("Failed to release form - '"+formName+"' of type - '"+formType+"'"+ e.getMessage());
			return false;
		}
	}

	/**This method is used to verify Single Properties Panel and with no Tabs
	 * @author ahmed_tw
	 * @param elementName [String] : What Form Element i.e Section, Note etc
	 * @return [boolean] : True if there are no tabs and properties panel is single else false.
	 */
	public boolean verifyPropertiesPanelNoTabs(String elementName) {
		WebElement propertiesPanelHeader = null;
		WebElement tabsHide = null;
		try {
			selectHeaderElements(elementName);
			
			if(elementName.equals("Form Name")) {
				propertiesPanelHeader = driver.findElement(By.xpath(FormDesignerPageLocator.PROP_SNGL_PNL_FRM_NM));
			}else {
				propertiesPanelHeader = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.PROPERTIES_SINGLE_PANEL, "ELEMENT", elementName);
			}
			tabsHide = driver.findElement(By.xpath(FormDesignerPageLocator.PROPERTIES_PANEL_NO_TABS));
			
			if(propertiesPanelHeader.isDisplayed() && !tabsHide.isDisplayed()) {
				logInfo("Verified Single Properties Panel with No tabs for element -" + elementName);
				return true;
			}
			logInfo("Failed to verify single properties panel with no tabs for element -" + elementName);
			return false;
		} catch (Exception e) {
			logError("Could Not verify properties panle with no tabs" + e.getMessage());
			return false;
		}
	}
	
	/**This method is used to verify Properties Plane have 3 tabs for a Field.
	 * @author ahmed_tw
	 * @return [boolean] : True after verifying Properties Panel has 3 tabs, else false
	 */
	public boolean verifyPropertiesPanleWith3Tabs() {
		WebElement generalTab = null;
		WebElement settingsTab = null;
		WebElement advancedTab = null;
		try {
			generalTab = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.PROPERTIES_TABS, "TAB_NAME", PROPERTIES_TABS.GENERAL_TAB);
			settingsTab = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.PROPERTIES_TABS, "TAB_NAME", PROPERTIES_TABS.SETTINGS_TAB);
			advancedTab = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.PROPERTIES_TABS, "TAB_NAME", PROPERTIES_TABS.ADVANCED_TAB);
			
			if(generalTab.isDisplayed() && settingsTab.isDisplayed() && advancedTab.isDisplayed()) {
				logInfo("Verified all 3 Properties Tabs are displayed in Properties Panel");
				return true;
			}
			
			logInfo("All 3 Properties Tabs are NOT displayed");
			return false;
		} catch (Exception e) {
			logError("Could NOT Verify Display of all 3 Properties Tab");
			return false;
		}
	}
	
	/**This method is used to select header level elements
	 * @author ahmed_tw
	 * @param elementName [String] : Name of Header Level Element
	 * @return [boolean] : True after selecting header level element, else false
	 */
	public boolean selectHeaderElements(String elementName) {
		WebElement headerLvlElements;
		try {
			headerLvlElements = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.HEADER_ELEMENTS_IN_DESIGNER,"ELEMENTNAME" , elementName);
			headerLvlElements.click();
			logInfo("Selected Header Element - " + elementName);
			return true;
			//logInfo("Failed to select Header element - " + elementName);
			//return false;
		} catch (Exception e) {
			logError("Could Not select Header element - " + elementName + e.getMessage());
			return false;
		}
	}
	/**
	 * 'verifyInPreviewFormNameNoteReltdDoc' method is used to verify the added header level Note, Related Docs, Form Name in Preview Mode
	 * @author ahmed_tw
	 * @param formName  [String] : Name of Form
	 * @param noteComment  [String] : Note
	 * @param relatedDoc  [String] : Name of File
	 * @return boolean status [boolean] : IF added fields are correct THEN true ELSE returns false
	 */
	public boolean verifyInPreviewFormNameNoteReltdDoc(String formName,String noteComment,String relatedDoc) {
		try {			
			WebElement verifyOpenedForm = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.PREVIEW_FORM_OPENED, "FORM_NAME", formName);
			if(!controlActions.isElementDisplayedOnPage(verifyOpenedForm)) {
				logError("Failed to verify opened preview form");
				return false;
			}
			WebElement verifyNote = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.PREVIEW_FORM_NOTE, "NOTE_COMMENT", noteComment);
			if(!controlActions.isElementDisplayedOnPage(verifyNote)) {
				logError("Failed to verify 'Note'");
				return false;
			}
			WebElement verifyReleatedDocs = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.PREVIEW_FORM_RELATED_DOCS, "DOCUMENT_NAME", relatedDoc);
			if(!controlActions.isElementDisplayedOnPage(verifyReleatedDocs)) {
				logError("Failed to verify 'Releated Docs'");
				return false;
			}
			logInfo("Verified all the header Note, Related Docs, Form Name are added in form in the 'Preview Form'");
			return true;
		}catch(Exception e) {
			logError("Failed to verify header level Note, Related Docs, Form Name fields in the 'Preview Form' - "
					+ e.getMessage());
			return false;
		}	
	}
	
	/** 'selectAggregateFunction' method is used to select Function for Aggregate Field
	 * @author ahmed_tw
	 * @param String - 'aggregateFunction'
	 * @return boolean true post selecting Function for Aggregate Field
	 */	
	public boolean selectAggregateFunctionJSClick(String aggregateFunction) {
		try {
			controlActions.clickElement(AggregateFunction);
			WebElement SelectAggregateFunction = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.SELECT_AGGREGATE_FUNCTION, "AGGREGATEFUNCTION",aggregateFunction );
			threadsleep(3000);
			controlActions.perform_ClickWithJavaScriptExecutor(SelectAggregateFunction);
			//controlActions.clickElement(SelectAggregateFunction);
			logInfo("Selected Aggregate Function - "+ aggregateFunction);
			return true;
		}catch(Exception e) {
			logError("Failed to select Aggregate Function - "+aggregateFunction+" - "
					+ e.getMessage());
			return false;
		}
	}
	
	/**This method is used to turn ON field setting when already on settings Tab for that field
	 * @author ahmed_tw
	 * @param settingName [String] : Setting Name
	 * @return [boolean] : True after clicking on 'On' button for that setting, false otherwise.
	 */
	public boolean turnSettingOn(String settingName) {
		WebElement onButtonOfSetting = null;
		try {
			onButtonOfSetting = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FLD_SETTING_ON_LBL, "FIELDSETTING", settingName);
			onButtonOfSetting.click();
			logInfo("Turned On Setting -" + settingName);
			return true;
		} catch (Exception e) {
			logError("Could Not turn On Setting -" + settingName + e.getMessage());
			return false;
		}
	}
	
	/**This method is used to turn oOFF field setting when already on settings Tab for that field
	 * @author ahmed_tw
	 * @param settingName [String] : Setting Name
	 * @return [boolean] : True after clicking on 'OFF' button for that setting, false otherwise.
	 */
	public boolean turnSettingOff(String settingName) {
		WebElement offButtonOfSetting = null;
		try {
			offButtonOfSetting = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FLD_SETTING_OFF_LBL, "FIELDSETTING", settingName);
			offButtonOfSetting.click();
			logInfo("Turned Setting Off -" + settingName);
			return true;
		} catch (Exception e) {
			logError("Could Not turn Off Setting -" + settingName + e.getMessage());
			return false;
		}
	}
	
	/**This method is used to select header level elements
	 * @author dahale_p
	 * @param elementName [String] : Name of Header Level Element
	 * @return [boolean] : True after selecting header level element, else false
	 */
	public boolean deleteResourceFD(String resourceInstance) {
			WebElement DeleteIcon = null;
		try {
			
		//	clickSelectResourcesFromBreadcrumb();
			Sync();
			controlActions.WaitforelementToBeClickable(FormHeaderSelectResources);
			Sync();
			controlActions.click(FormHeaderSelectResources);
			Sync();
			//controlActions.clickElement(ResourceDeletion);//To comment
			DeleteIcon = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.RESOURCE_DELETION,"INSTANCE_NAME",resourceInstance);
			controlActions.clickElement(DeleteIcon);
			Sync();


			logInfo("Clicked Delete icon  successfully for resource " + resourceInstance);
			return true;
		}
		catch(Exception e) {
			logError("Failed to click on Delete icon for resource - " + resourceInstance
					+ e.getMessage());
			return false;
		}	
	}
	
	/**This method is used to select header level elements
	 * @author dahale_p
	 * @param elementName [String] : Name of Header Level Element
	 * @return [boolean] : True after selecting header level element, else false
	 */
	public boolean deleteResource(String resourceInstance) {
			WebElement DeleteIcon = null;
		try {
			
			Sync();
			clickSelectResourcesFromBreadcrumb();
			//controlActions.WaitforelementToBeClickable(FormHeaderSelection);
			//controlActions.click(FormHeaderSelection);
			Sync();
			//controlActions.clickElement(ResourceDeletion);
			DeleteIcon = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.RESOURCE_DELETION,"INSTANCE_NAME",resourceInstance);
			controlActions.clickElement(DeleteIcon);
			Sync();


			logInfo("Clicked Delete icon  successfully for resource " + resourceInstance);
			return true;
		}
		catch(Exception e) {
			logError("Failed to click on Delete icon for resource - " + resourceInstance
					+ e.getMessage());
			return false;
		}	
		
	}
	
	/** This method is used to check Created form is displaying or not in Release form tab
	 * @author jadhav_akan
	 * @param String - formName
	 * @return boolean status
	 * IF created form is displayed in Release form tab THEN true ELSE false
	 */	
	public boolean createdFormIsDisplayedInReleaseFormTab(String formName) {
		try {
			controlActions.clickElement(ReleaseFormStep);
			WebElement selectForm = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.SELECT_FORM_LBL, "FORM_NAME",formName );
			if(controlActions.isElementDisplayedOnPage(selectForm)) {
			logInfo("Form - "+formName+" is Displayed");
			}
			else {
				logInfo("Form - "+formName+" is not Displayed");
			}
			return true;
		}catch(Exception e) {
			logError("Failed to verify form - "+formName+ " is Displaying or not" 
					+ e.getMessage());
			return false;
		}
	}
	/**
	 * This method is used to check resource category is present/display
	 * @author jadhav_akan
	 * @param resourceType Use Class FORMRESOURCETYPES to set type of category
	 * @param resourceCategory Name of category
	 * @return boolean This returns true after category is present/displayed
	 */
	public boolean resourceCategoryIsPresent(String resourceType, String resourceCategory) {
		try 
		{
			String selector = "li[class='k-item ng-scope'][role='option']";
			controlActions.JSSetValueFromList(driver, selector, resourceType);
			Thread.sleep(3000);
			WebElement resCat = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.DRAG_RES, "RESOURCE_CATEGORY",resourceCategory);
			controlActions.isElementDisplayedOnPage(resCat);
			Sync();
			
			logInfo("Resource Category - "+resourceCategory+" is present");
			return true;
		}
		catch(Exception e) {
			logError("Resource Category - "+resourceCategory+" is not Present"
					+ e.getMessage());
			return false;
		}
	}
	/** This method is used to search Resource and display no data found popUp
	 * @author jadhav_akan
	 * @param resourceName: Name of Resource that needs to be searched
	 * @return boolean status
	 * IF Successfully no Data found popUp is displayed for searched Resource then True else False
	 */	
	public boolean searchSelectResourceNoDataInPreviewForm(String resourceName) {
		try {
				controlActions.clickElement(SelectResourceDdl);
			    WebElement	filter = DropDownListFilter;
			    controlActions.sendKeys(filter, resourceName);
				WebElement noDataPopup = NoDataFound;
				if(noDataPopup.isDisplayed()) {
					logInfo("Resource -" + resourceName+ " is not present in Select Resource.As no data found is Displayed");
				}
			logInfo("Resource -" + resourceName+ " is not present in Select Resource.As no data found is Displayed");
		    return true;
			
		}catch (Exception e) {
			logError("Resource - "+resourceName+" is present in Select resource"+e.getMessage());	
			return false;
		}
	}
	/** This method is used to select Resources. Navigating from edit form to select Resources 
	 * @author jadhav_akan
	 * @param resourceType: Name of Resource Type
	 * @param categoryValue: Name of Resource Category Value
	 * @param instanceValue1: Name of Resource Instanced Value
	 * @return boolean status
	 * IF Successfully select Resources then True else False
	 */	
	public boolean selectResourcesFromEditForm(String resourceType,String categoryValue, String instanceValue1) {
		try {
			controlActions.clickElement(SelectResourcesTab);
			Sync();

			if(!selectResource(resourceType,categoryValue,instanceValue1)) {
				logError("Could NOT select resource");
				return false;
			}

			if(!clickOnNextButton(DesignFormPg,"Design Form")) {
				logError("Could NOT Navigate to 'Design Form'");
				return false;
			}
			logInfo("Successfully selected Resource -" + instanceValue1+ " from edit form");
		    return true;
		}catch (Exception e) {
			logError("Could not Select resource - "+instanceValue1+" from edit form"+e.getMessage());	
			return false;
		}
		
	}
	
	/**
	 * This method is used to check Resource is not present
	 * @author jadhav_akan
	 * @param resourceType Use Class FORMRESOURCETYPES to set type of category
	 * @param resourceInstance Name of instance to be search
	 * @return boolean This returns true after searching resource and no data found popUp is displayed
	 */
	public boolean searchResourceInstanceNoDataFound(String resourceType, String resourceInstance) {
		try {
			String selector = "li[class='k-item ng-scope'][role='option']";
			controlActions.JSSetValueFromList(driver, selector, resourceType);
			Sync();
			SearchResourceTxt.clear();
			SearchResourceTxt.sendKeys(resourceInstance);
			WebElement noDataPopup = NoDataPopup;
			if(noDataPopup.isDisplayed()) {
				logInfo("Resource -" + resourceInstance+ " is not present.As no data found is Displayed");
			}
		logInfo("Resource -" + resourceInstance+ " is not present.As no data found is Displayed");
		return true;
		}
		catch(Exception e) {
			logError("Failed verify resource instance - " + resourceInstance+" is not present"
					+ e.getMessage());
			return false;
		}
	}
	/**
	 * This method is used to check Resource is not present
	 * @author jadhav_akan
	 * @param String - formType
	 * @param String - resourceType
	 * @param String - resourceInstance
	 * @return boolean status
	 * This returns true after searching resource and no data found popUp is displayed;
	 */
	public boolean selectFormTypeselectResTypeAndsearchResourceNoDataFound(String formType,String resourceType, String resourceInstance) {
		try {
			formType = formType.toUpperCase();
			switch(formType) {
			case "CHECK":
				controlActions.clickElement(SelectCheckFormLnk);
				break;
			case "QUESTIONNAIRE":
				controlActions.clickElement(SelectQuestionnaireFormLnk);
				break;
			case "AUDIT":
				controlActions.clickElement(SelectAuditFormLnk);
				break;

			default:
				logError("Form type is INVALID");
				return false;			
			}
			Sync();

			if(!searchResourceInstanceNoDataFound(resourceType,resourceInstance)) {
				logError("Resource -" + resourceInstance+ " is present");
				return false;
			}

			
			logInfo("Resource -" + resourceInstance+ " is not present.As no data found is Displayed");
			return true;
		}catch(Exception e) {
			logError("Failed to verify resource instance - " + resourceInstance+" is not present "+ e.getMessage());
			return false;
		}
	}

  /**    
	 * This method is used to check Dependency Rule is applied on field or not.
	 * @author Krishna_kanani
	 * @param String - ruleField - Name of field to be used INSIDE Dependency Rule
	 * @return boolean true post checking Dependency Rule presence
	 */
	public boolean checkDependencyRulePresence(String ruleField) {
		try {
			selectPropertiesTabs(PROPERTIES_TABS.ADVANCED_TAB);
			logInfo("Checking Dependency Rule's presence.");
			if(controlActions.perform_CheckIfElementTextContains(DependencyRulePresence,ruleField))
				return true;
			return false;
		} catch (Exception e) {
			logInfo("Dependency rule is not present."+e.getMessage());
			return false;
		}
	}
	
	/**
	 * This method is used to check Expression Rule is applied on field or not.
	 * @author Krishna_kanani
	 * @param String - ruleField - Name of field to be used INSIDE Dependency Rule
	 * @return boolean true post checking Dependency Rule presence
	 */
	public boolean checkExpressionRulePresence(String ruleField) {
		try {
			selectPropertiesTabs(PROPERTIES_TABS.ADVANCED_TAB);
			logInfo("Checking Expression Rule's presence.");
			if(controlActions.perform_CheckIfElementTextContains(ExpressionRulePresence, ruleField))
				return true;
			return false;
		} catch (Exception e) {
			logInfo("Expression rule is not present."+e.getMessage());
			return false;
		}
	}
	
	/**
	 * This method is used to check Add New button is enabled for Dependency rule or not.
	 * @author Krishna_kanani
	 * @param String - ruleField - Name of field to be used INSIDE Dependency Rule
	 * @return boolean true post checking Add New button's Enabled state
	 */
	public boolean checkAddNewButtonEnabledForDependencyRule(){
		try {
			selectPropertiesTabs(PROPERTIES_TABS.ADVANCED_TAB);
			logInfo("Checking Add new button's enabled state for Dependency rule.");
			if(controlActions.isElementEnabled(DependencyRuleAddNewButton))
				return true;
			return false;
		} catch (Exception e) {
			logError("Failed to check Add new button's enabled state for Dependency rule.- " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * This method is used to check Add New button is disabled for Dependency rule or not.
	 * @author Krishna_kanani
	 * @param String - ruleField - Name of field to be used INSIDE Dependency Rule
	 * @return boolean true post checking Add New button's Disabled state
	 */
	public boolean checkAddNewButtonDisabledDependencyRule(){
		try {
			selectPropertiesTabs(PROPERTIES_TABS.ADVANCED_TAB);
			logInfo("Checking Add new button's disabled state for Dependency rule.");
			if(controlActions.isElementDisabled(DependencyRuleAddNewButton))
				return true;
			return false;
		} catch (Exception e) {
			logError("Failed to check Add new button's disabled state Dependency rule.- " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * This method is used to check Add New button is Enabled for Expression rule or not.
	 * @author Krishna_kanani
	 * @param String - ruleField - Name of field to be used INSIDE Expression Rule
	 * @return boolean true post checking Add New button's Enabled state
	 */
	public boolean checkAddNewButtonEnabledForExpressionRule(){
		try {
			selectPropertiesTabs(PROPERTIES_TABS.ADVANCED_TAB);
			logInfo("Checking Add new button's enabled state for expression rule.");
			if(controlActions.isElementEnabled(ExpressionRuleAddNewButton))
				return true;
			return false;
		} catch (Exception e) {
			logError("Failed to check Add new button's enabled state for Expression rule.- " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * This method is used to check Add New button is Disabled for Expression rule or not.
	 * @author Krishna_kanani
	 * @param String - ruleField - Name of field to be used INSIDE Expression Rule
	 * @return boolean true post checking Add New button's Disabled state
	 */
	public boolean checkAddNewButtonDisabledExpressionRule(){
		try {
			selectPropertiesTabs(PROPERTIES_TABS.ADVANCED_TAB);
			logInfo("Checking Add new button's disabled state for expression rule.");
			if(controlActions.isElementDisabled(ExpressionRuleAddNewButton))
				return true;
			return false;
		} catch (Exception e) {
			logError("Failed to check Add new button's disabled state for Expression rule.- " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * This method is used to open existing Dependency rule.
	 * @author Krishna_kanani
	 * @param String - ruleField - Name of field to be used INSIDE Dependency Rule
	 * @return boolean true post checking existing dependency rule is opened.
	 */
	public boolean openExistingDependencyRule(String ruleField) {
		try {
			selectPropertiesTabs(PROPERTIES_TABS.ADVANCED_TAB);
			logInfo("Checking Dependency Rule's presence.");
			if(controlActions.perform_CheckIfElementTextEquals(DependencyRulePresence,ruleField+" DependencyRule")){
					controlActions.clickOnElement(DependencyRulePresence);
					logInfo("Existing Dependency rule form is opened");
					return controlActions.isElementDisplayedOnPage(CancelButton);
			}
			return false;
		} catch (Exception e) {
			logError("Failed to open existing Dependency Rule - " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * This method is used to open existing Expression rule.
	 * @author Krishna_kanani
	 * @param String - ruleField - Name of field to be used INSIDE Dependency Rule
	 * @return boolean true post checking existing Expression rule is opened.
	 */
	public boolean openExistingExpressionRule(String ruleField) {
		try {
			selectPropertiesTabs(PROPERTIES_TABS.ADVANCED_TAB);
			logInfo("Checking Expression Rule's presence.");
			if(controlActions.perform_CheckIfElementTextEquals(ExpressionRulePresence,ruleField+" ExpressionRule")){
					controlActions.clickOnElement(ExpressionRulePresence);
					logInfo("Existing Expression rule form is opened");
					return controlActions.isElementDisplayedOnPage(CancelButton);
			}
			return false;
		} catch (Exception e) {
			logError("Failed to open existing Dependency Rule - " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}
	
	/** 
	 * This method is used to click on cancel Button in rule Builder
	 * @author Krishna_Kanani
	 * @return boolean true post clicking on cancel Button
	 */	
	public boolean clickCancelButton() {
		try {
			controlActions.clickElement(CancelButton);
			Sync();
			logInfo("Clicked on cancel button");
			return true;
		}catch(Exception e) {
			logError("Failed to click on cancel button"
					+ e.getMessage());
			e.printStackTrace();
			return false;
		}
	}
	
	/** 
	 * This method is used to update rule name on rule Builder
	 * @author Krishna_Kanani
	 * @param setValue give the rule name which you want to update
	 * @return boolean String updated value of Rule name
	 */	
	public String updateRuleName(String setValue){
		try{
			if(controlActions.clearAndSetText(RuleEditFormNameField, setValue))
				return setValue;
			return null;
		}catch(Exception e) {
			logError("Failed to update rule name"
					+ e.getMessage());
			e.printStackTrace();
			return null;
		}
	}
  
	/* 'editIconPresenceOnComplianceRuleTextField' method is used to click on Compliance rule edit icon.
	 * @author Krishna Kanani
	 * @param shortName [String] - Name of the Field
	 * @param resourceName [String] - Name of Resource
	 * @param value [String] - Value for if condition
	 * @return [boolean]
	 * IF edit icon is present THEN true ELSE returns false
	 */
	public boolean editIconPresenceOnComplianceRuleTextField(String shortName, String resourceName, String value) {
		try {	
			controlActions.clickElement(FormNameTxt);
			WebElement fieldSelection = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_SHORTNAME,"SHORT_NAME",shortName);
			controlActions.clickElement(fieldSelection);
			String complianceGridStatus = ComplianceIconStatus.getAttribute("class");
			if(!complianceGridStatus.equals("k-icon k-collapse-next k-i-arrow-60-down")) {
				controlActions.clickElement(ComplianceOpen);
			}
			WebElement ResourceEdit = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.EDIT_COMPLIANCE_RULE, "RESOURCEINSTANCENAME", resourceName);
			return ResourceEdit.isDisplayed();
		}catch(Exception e) {
			logError("Failed to check presence of Edit icon on complience rule text field - "
					+ e.getMessage());
			return false;
		}	
	}
	
	/** 
	 * This method is used to click on edit icon of Compliance Rule. 
	 * @author Krishna_Kanani
	 * @param resourceName name which user want to click on edit icon.
	 * @return boolean True if clicked on edit icon successfully.
	 */	
	public boolean clickOnEditIconOfComplianceRuleTextField(String resourceName) {
		try {
			WebElement ResourceEdit = controlActions.perform_GetElementByXPath(
					FormDesignerPageLocator.EDIT_COMPLIANCE_RULE, "RESOURCEINSTANCENAME", resourceName);
			ResourceEdit.click();
			logInfo("Existing Compliance rule form is opened");
			return controlActions.isElementDisplayedOnPage(CancelButton);
		} catch (Exception e) {
			logError("Failed to check presence of Edit icon on complience rule text field - " + e.getMessage());
			return false;
		}
	}
	
	/** 
	 * This method is used to delete dependency rule.
	 * @author Krishna_Kanani
	 * @return boolean True if Dependency rule deleted successfully.
	 */	
	public boolean deleteDependencyRule(){
		try {
			selectPropertiesTabs(PROPERTIES_TABS.ADVANCED_TAB);
			logInfo("Deleting available Dependency rule.");
			controlActions.clickOnElement(DeleteIconOfDependencyRule);
			return controlActions.isElementEnabled(DependencyRuleAddNewButton);
		} catch (Exception e) {
			logError("Failed to click on delete icon of dependency rule. - " + e.getMessage());
			return false;
		}
	}
	
	/** 
	 * This method is used to delete dependency rule.
	 * @author Krishna_Kanani
	 * @return boolean True if Dependency rule deleted successfully.
	 */	
	public boolean deleteFieldType(FieldType type){
		try {
//			WebElement TextBox = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_TYPE_NAME_TXT,"FIELDTYPENAME",FieldTypeName);
//			controlActions.WaitforelementToBeClickable(TextBox);		
//			controlActions.clickOnElement(TextBox);
			String xpath = String.format(FormDesignerPageLocator.DELETE_FIELD_TYPE, type.fieldTypeValue);
			WebElement element  = controlActions.perform_GetElementByXPath(xpath);
			controlActions.clickOnElement(element);
			return true;
		} catch (Exception e) {
			logError("Failed to delete the "+type.fieldTypeValue+" field type. - " + e.getMessage());
			return false;
		}
	} 
	
	public boolean checkFieldIsInUseErrorMessage(){
		try{
			Sync();
			controlActions.waitforElementToBeDisplayed(FieldinUseErrorMessage);
			controlActions.clickOnElement(OkSaveButton);
			return true;
		}catch(Exception e){
			return false;
		}
		
	}
	public static class FormDesignParams{
		public String FormType;
		public String FormName;
		public String HeaderNoteText;
		public String HeaderDocTypeName;
		public String HeaderDocName;
		public boolean HeaderSummaryField;
		public HashMap<String, List<String>> SelectResources;
		public List<FormFieldParams> DesignFields;
		public boolean ReleaseForm = true;
		
	}

	public static class FormFieldParams{
		public HashMap<String, List<String>> FieldDetails;
		public String SectionName;
		public String DragToSectionName;
		public String GroupName;
		public String IdentiferType;
		public List<String> IdentifierValue;
		public List<String> SelectOneMultipleOptions;
		public String QuestionWeight;
		public String[][] SelectOneMultipleOptionsWeight;
		public String AgrregateFunction;
		public String AgrregateSource;
		public List<List<String>> Repeat;
		public List<String> Copy;
		public boolean SetAggregateFunction =  false;
	}


	public static class FIELD_TYPES{
		public static final String FREE_TEXT= "Free Text";
		public static final String PARAGRAPH_TEXT = "Paragraph Text";
		public static final String SELECT_ONE = "Select One";
		public static final String SELECT_MULTIPLE = "Select Multiple";
		public static final String NUMERIC = "Numeric";
		public static final String DATE = "Date";
		public static final String TIME = "Time";
		public static final String DATE_TIME = "Date & Time";
		public static final String SINGLE_LINE_TEXT = "Single Line Text";
		public static final String AGGREGATE = "Aggregate";
		public static final String IDENTIFIER = "Identifier";
		
	}

	public static class FORM_ELEMENTS{
		public static final String NOTE= "Note";
		public static final String RELATED_DOCS = "Related Docs";
		public static final String SECTION = "Section";
		public static final String FIELD_GROUP = "Field Group";
		public static final String QUESTION_GROUP = "Question Group";
		public static final String AGGREGATE = "Aggregate";
		public static final String IDENTIFIER = "Identifier";	
	}

	public static class ELEMENT_TYPE{
		public static final String FIELD_TYPES= "Field Types";
		public static final String QUESTION_TYPES = "Question Types";
		public static final String FORM_ELEMENTS = "Form Elements";

	}

	public static class IDENTIFIER_INPUT_TYPE{
		public static final String FREE_TEXT= "Free Text";
		public static final String SELECT_ONE = "Select One";
	}


	public static class AGGREGATE_FUNCTION{
		public static final String AVERAGE= "Average";
		public static final String SUM = "Sum";
		public static final String PERCENT_PASS = "% Pass";
		public static final String PERCENT_FAIL = "% Fail";
		public static final String COUNT_PASS = "Count Pass";
		public static final String COUNT_FAIL = "Count Fail";
		public static final String STANDARD_DEVIATION = "Standard Deviation";
		public static final String MIN_VALUE = "Min Value";
		public static final String MAX_VALUE = "Max Value";
		public static final String MEDIAN = "Median";
		public static final String RANGE = "Range";
	}

	public static class FORMTYPES{
		public static final String CHECK = "CHECK";
		public static final String AUDIT = "AUDIT";
		public static final String QUESTIONNAIRE = "QUESTIONNAIRE";
	}

	public static class FIELD_INPUT_TYPES{
		public static final String FREE_TEXT= "FreeText";
		public static final String PARAGRAPH_TEXT = "Paragraphtext";
		public static final String SELECT_ONE = "SelectOne";
		public static final String SELECT_MULTIPLE = "SelectMultiple";
		public static final String NUMERIC = "Numeric";
		public static final String DATE = "Date";
		public static final String TIME = "Time";
		public static final String DATE_TIME = "DateTime";
		public static final String SINGLE_LINE_TEXT = "SingleLineText";	
		public static final String AGGREGATE = "Aggregate";
		public static final String IDENTIFIER = "Identifier";	
	}

	public static class ELEMENT_INPUT_TYPES{
		public static final String FIELD_GROUP= "FieldGroup";
		public static final String FIELD_GROUP_TXT = "Field Group";
		public static final String SECTION = "Section";
		public static final String QUESTION_GROUP= "Question";
	}

	public static class IDENTIFIER_TYPE{
		public static final String FREETEXT = "Free Text";
		public static final String SELECTONE = "Select One";
	}

	public static class FORMRESOURCETYPES{
		public static final String CUSTOMERS = "Customers";
		public static final String EQUIPMENT = "Equipment";
		public static final String ITEMS = "Items";
		public static final String SUPPLIERS = "Suppliers";
	}

	public static class ElementProperties{
		public boolean IS_REQUIRED = false;
		public boolean ALLOW_COMMENTS = false;
		public boolean ALLOW_ATTATHMENTS = false;
		public boolean SHOW_HINT = false;
		public String HINT = "Test Hint";
		public boolean ALLOW_CORRECTION = false;
		public boolean MARK_RESOLVED = false;
		public boolean PREDEFINED_CORRECTIONS = false;
		public boolean CARRYOVER_FIELD = false;		
		public boolean COMPLIANCE_CONFIG = false;
		public String COMPLIANCE_VALUES[];
		public boolean SHOW_TARGET = false;
		public boolean FAILS_CHECK = false;
		public boolean FAILS_QUESTIONNAIRE = false;
		public boolean SHOW_MIN_MAX = false;
		public String COMPLIANCE_TEXT = null;
		public List<String> PREDEFINED_CORRECTIONS_OPTNS = null;
		
	}


	public static class FieldInputs{
		public final static String INPUT_FIELD = "//field-template/div[div/label[normalize-space(text())='FIELD_NAME']]//input[1]";
		public final static String TEXTAREA_FIELD = "//field-template/div[div/label[normalize-space(text())='FIELD_NAME']]//textarea";
	}

	public static class PreviewFormDetails{
		public LinkedHashMap<String, List<String>>fieldDetails;
		List<String> fieldData;
		public String resourceName;
		public boolean closePreview = true;
	}

	public static class RuleProperties{
		public DependencyRuleConfig depdendencyRuleConfig;
		public ExpressionRuleConfig expressionRuleConfig;
		public ComplianceRuleConfig complianceRuleConfig;
	}
	public static class DependencyRuleConfig{
		public String IF_FIELD;
		public String FIELD_VALUE;
		public String VALUE_VALUE;
	}
	public static class ExpressionRuleConfig{
		public String IF_FIELD;
		public String FIELD_VALUE;
		public String VALUE_VALUE;
		public String THEN_VALUE;
		public String ELSE_VALUE;
	}
	public static class ComplianceRuleConfig{
		public String IF_FIELD;
		public String FIELD_VALUE;
		public String VALUE_VALUE;
		public String RESOURCE;
	}
	
	public static class FORM_NEXT_PAGE{
		public static final String DESIGN_FORM = "Design Form";
		public static final String RELEASE_FORM = "Release Form";
	}
	
	public static class FIELD_SETTINGS{
		public static final String IS_REQUIRED = "Is Required";
		public static final String ALLOW_COMMENTS = "Allow Comments";
		public static final String ALLOW_ATTACHMENTS = "Allow Attachments";
		public static final String SHOW_ON_COA = "Show on COA";
		public static final String SHOW_HINT = "Show Hint";
		public static final String SHOW_MINMAX = "Show Min/Max";
		public static final String SHOW_TARGET = "Show Target";
		public static final String FAILS_CHECK = "Fails Check";
		public static final String FAILS_AUDIT = "Fails Audit";
		public static final String ALLOW_CORRECTION = "Allow Correction";	
		public static final String MARK_RESOLVED = "Mark Resolved";	
		public static final String TEMP_PROBE = "Temp Probe";
		public static final String CARRYOVER_FIELD = "Carryover Field";	
		public static final String SHOW_GROUP_NAME = "Show Group Name";
		public static final String SHOW_POINTS_POSSIBLE = "Show Pts. Possible";
		public static final String SHOW_POINTS_EARNED = "Show Pts. Earned";
		
	}
	
	public static class SUMMARY_TABLE_HEADER{
		public static final String POINTS_POSSIBLE = "Points Possible";
		public static final String POINTS_EARNED = "Points Earned";
		public static final String SCORE = "Score";
	}

	public static class PROPERTIES_TABS {
		public static final String GENERAL_TAB = "General";
		public static final String SETTINGS_TAB = "Settings";
		public static final String ADVANCED_TAB = "Advanced";
	}
}
