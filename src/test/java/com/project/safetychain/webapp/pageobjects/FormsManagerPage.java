package com.project.safetychain.webapp.pageobjects;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.project.utilities.CommonMethods;
import com.project.utilities.ControlActions;

public class FormsManagerPage extends CommonPage{
	WebDriver driver;
	WebDriverWait wait;
	ControlActions controlActions;

	public FormsManagerPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 40);
		controlActions = new ControlActions(driver);
	}

	/**
	 * Page Objects
	 */
	@FindBy(id = FormsManagerPageLocators.FTU_IMPORT_BTN)
	public WebElement FTUImportBtn;
	
	@FindBy(id = FormsManagerPageLocators.FTU_VIEW_IMPORT_HIST_LNK)
	public WebElement FTUViewImportHistLnk;
	
	@FindBy(xpath = FormsManagerPageLocators.VERIFICATION_BTN)
	public WebElement VerificationBtn;

	@FindBy(xpath = FormsManagerPageLocators.SELECTVERIFICATION_BTN)
	public WebElement SelectVerificationBtn;

	@FindBy(xpath = FormsManagerPageLocators.LINKFORMVERIFICATION_CHK)
	public WebElement LinkFormVerificationChk;

	@FindBy(xpath = FormsManagerPageLocators.SAVE_BTN)
	public WebElement SaveBtn;

	@FindBy(xpath = FormsManagerPageLocators.SEARCHUSER_TXT)
	public WebElement SearchUserTxt;

	@FindBy(xpath = FormsManagerPageLocators.SEARCH_FORMNAME_INPUT)
	public WebElement SearchFormnameInput;

	@FindBy(xpath = FormsManagerPageLocators.SEARCH_LOCATIONNAME_INPUT)
	public WebElement SearchLocationNameInput;

	@FindBy(xpath = FormsManagerPageLocators.SEARCH_FORMNAMEFORVERIFICATION_TXT)
	public WebElement SearchFormnameVerificationTxt;

	@FindBy(xpath = FormsManagerPageLocators.CHARTS_TAB)
	public WebElement ChartsTab;

	@FindBy(xpath = FormsManagerPageLocators.DISABLE_BTN)
	public WebElement DisableBtn;

	@FindBy(xpath = FormsManagerPageLocators.ENABLE_BTN)
	public WebElement EnableBtn;

	@FindBy(xpath = FormsManagerPageLocators.DISABLE_POPUP_YES_BTN)
	public WebElement DisablePopupYesBtn;

	@FindBy(xpath = FormsManagerPageLocators.ENABLE_POPUP_YES_BTN)
	public WebElement EnablePopupYesBtn;


	@FindBy(xpath = FormsManagerPageLocators.GRID_COLUMNS)
	public List<WebElement> GridColumns;


	@FindBy(xpath = FormsManagerPageLocators.ENABLE_VERIFICATION_BTN)
	public WebElement EnableVerificationBtn;

	@FindBy(xpath = FormsManagerPageLocators.LOCATION_BTN)
	public WebElement LocationBtn;

	@FindBy(xpath = FormsManagerPageLocators.SELECTlOCATION_BTN)
	public WebElement SelectLocationBtn;

	@FindBy(xpath = FormsManagerPageLocators.TOGGLE_LOCATION_BTN)
	public WebElement ToggleLocationBtn;

	@FindBy(xpath = FormsManagerPageLocators.SELECT_LOCATION_CHK)
	public WebElement SelectLocationChk;

	@FindBy(xpath = FormsManagerPageLocators.SELECT_LOCATION_CHK_CHECK)
	public WebElement SelectLocationChkCheck;

	@FindBy(xpath = FormsManagerPageLocators.FORM_DESIGNER_BTN)
	public WebElement FormDesignerBtn;

	@FindBy(xpath = FormsManagerPageLocators.CREATE_TASK_SCHEDULER_BTN)
	public WebElement CreateTaskSchdulerBtn;

	@FindBy(xpath = FSQABrowserPageLocators.FILTER_TXT)
	public List<WebElement> FilterTxt;

	@FindBy(xpath = FSQABrowserPageLocators.FILTER_BTN)
	public List<WebElement> FilterBtn;	

	@FindBy(xpath = FormsManagerPageLocators.VERIFICATION_PAGINATION_DRD)
	public WebElement Verification_PaginationDrd;

	@FindBy(xpath = FormsManagerPageLocators.LOCATION_TAB)
	public WebElement LocationTab;

	@FindBy(xpath = FormsManagerPageLocators.SELECT_ALLFORMS_CHECK)
	public WebElement SelectAllFormsCheck;

	@FindBy(xpath = FormsManagerPageLocators.CLICK_NEXT_PAGE_ARROW)
	public WebElement ClickNextPageArrow;

	@FindBy(xpath = FormsManagerPageLocators.VERIFY_FORM_BTN)
	public WebElement VerifyFormBtn;

	@FindBy(xpath = FormsManagerPageLocators.DIRECTOBS_USERNAME_TXT)
	public WebElement DirectObsUserNameTxt;

	@FindBy(xpath = FormsManagerPageLocators.VERIFICATION_PIN_TXT)
	public WebElement VerificationPinTxt;

	@FindBy(xpath = FormsManagerPageLocators.ISCOMPLIANT_TOGGLE_YES)
	public WebElement IsCompliantToggleYes;

	@FindBy(xpath = FormsManagerPageLocators.CLOSE_DIROBS_POPUP_BTN)
	public WebElement CloseDirObsPopUpBtn;


	@FindBy(xpath = FormsManagerPageLocators.FORM_MANAGER_VERSION_LNK)
	public WebElement FormManagerVersionLnk;


	@FindBy(xpath = FormsManagerPageLocators.FORM_MANAGER_VIEWCOMMENT_LNK)
	public WebElement FormManagerViewCommentLnk;


	/////////////////////////// Below to add in FormsManagerPageLocators

	@FindBy(xpath = FormsManagerPageLocators.POPUP_COLUMN_APPLY_BTN)
	public WebElement PopupColumnApplyBtn;



	@FindBy(xpath = FormsManagerPageLocators.POPUP_OPTION_MNU)
	public WebElement PopupOptionMnu;

	@FindBy(xpath = FormsManagerPageLocators.COLUMN_SETTINGS_LNK)
	public WebElement ColumnSettingLnk;



	@FindBy(xpath = FormsManagerPageLocators.POPUP_COLUMN_SET_OPTN)
	public WebElement PopupColumnSetOptn;

	@FindBy(xpath = FormsManagerPageLocators.GRID_COLUMN_VALUE)
	public List<WebElement> GridColumnValue;

	@FindBy(xpath = FormsManagerPageLocators.LOCATIONSELECTION_TXT)
	public WebElement LocationSelectionTxt;

	@FindBy(xpath = FormsManagerPageLocators.FM_DROPDOWN_COLUMN_VAL)
	public WebElement FmDropDownColumnValue;

	@FindBy(xpath = FormsManagerPageLocators.CLEAR_GRID_FILTER_BTN)
	public List<WebElement> ClearGridFilterBtn;

	@FindBy(xpath = FormsManagerPageLocators.FM_OVERVIEW_SCROLL_HR)
	public WebElement FmOverviewScrollHR;

	@FindBy(xpath = FormsManagerPageLocators.CLEAR_COLUMN_FILTER_BTN)
	public WebElement ClearColumnFilterBtn;	


	@FindBy(xpath = FormsManagerPageLocators.COLUMN_FM_FORMNAME_LOCATION_TXT)
	public WebElement ColumnFmFormNameLocationTxt;

	@FindBy(xpath = FormsManagerPageLocators.CLEAR_COLUMN_FILTER_LOCATION_BTN)
	public WebElement ClearColumnFilterLocationBtn;	

	@FindBy(xpath = FormsManagerPageLocators.FM_OVERVIEW_FORM_LOCATION_TXT)
	public WebElement FmOverviewFormLocation;


	@FindBy(xpath = FormsManagerPageLocators.CLEAR_GRID_OVERVIEW_LOCATION_FILTER_BTN)
	public WebElement ClearGridOverviewFormLocationBtn;

	@FindBy(xpath = FormsManagerPageLocators.FM_OVERVIEW_FORM_CHECKBOX)
	public WebElement fmOverviewFormCheckbox;

	@FindBy(xpath = FormsManagerPageLocators.FM_OVERVIEW_SEARCH_FORM_TXT)
	public WebElement fmOverviewSearchFormTxt;

	@FindBy(xpath = FormsManagerPageLocators.FM_OVERVIEW_CLEAR_COLUMN_FILTER_BTN)
	public WebElement fmOverviewClearColumnFilterBtn;


	@FindBy(xpath = FormsManagerPageLocators.FM_OVERVIEW_STATE_CALLOUT_MNU)
	public WebElement fmOverviewStateCalloutMnu;

	@FindBy(xpath = FormsManagerPageLocators.FM_OVERVIEW_SELECTBULKFORM_CHECKBOX)
	public WebElement fmOverviewSelectBulkFormCheckbox;


	@FindBy(xpath = FormsManagerPageLocators.FM_OVERVIEW_FORM_STATE_ENABLED)
	public WebElement fmOverviewFormStateEnabled;

	@FindBy(xpath = FormsManagerPageLocators.FM_OVERVIEW_FORM_STATE_DISABLED)
	public WebElement fmOverviewFormStateDisabled;

	@FindBy(xpath = FormsManagerPageLocators.FM_OVERVIEW_FORM_POPUP_ENABLED)
	public WebElement fmOverviewFormPopupEnable;

	@FindBy(xpath = FormsManagerPageLocators.FM_OVERVIEW_FORM_POPUP_DISABLED)
	public WebElement fmOverviewFormPopupDisable;

	@FindBy(xpath = FormsManagerPageLocators.FM_OVERVIEW_SEARCHFORM_TOUCHED_TXT)
	public WebElement fmOverviewSearchFormTouchedTxt;

	@FindBy(xpath = FormsManagerPageLocators.FM_OVERVIEW_CHARTS_TAB)
	public WebElement fmOverviewChartsTab;

	@FindBy(xpath = FormsManagerPageLocators.FM_CHART_POPUP_OK_BUTTON)
	public WebElement fmChartPopUpOKButton;

	@FindBy(xpath = FormsManagerPageLocators.FM_CHART_POPUP_CLEAR_BUTTON)
	public WebElement fmChartPopUpClearButton;

	@FindBy(xpath = FormsManagerPageLocators.FM_CHART_CLOSE)
	public WebElement fmChartClose;

	@FindBy(xpath = FormsManagerPageLocators.FM_CHART_DELETE_CHART_BUTTON)
	public WebElement fmChartDeleteChart ;

	@FindBy(xpath = FormsManagerPageLocators.FM_OVERVIEW_FORM_CLOSE_BUTTON)
	public WebElement fmOverviewFormCloseButton;

	@FindBy(xpath = FormsManagerPageLocators.FM_CHART_RESOURCE_LOCATION_DDL)
	public WebElement fmChartResouceLocationDdl; 

	@FindBy(xpath = FormsManagerPageLocators.ASSOCIATE_BTN)
	public WebElement AssociatedBtn;

	@FindBy(xpath = FormsManagerPageLocators.CHARTNAME_INPUT)
	public WebElement ChartNameInput;

	@FindBy(xpath = FormsManagerPageLocators.ASSOCIATE_NEW_CHART_SAVE_BTN)
	public WebElement AssociateChartSaveBtn;

	@FindBy(xpath = FormsManagerPageLocators.FM_CHARTS_FIELD_DDL)
	public WebElement fmChartsFieldDdl;

	@FindBy(xpath = FormsManagerPageLocators.FM_CHARTS_FIELD_VALUE)
	public WebElement fmChartsFieldVal;


	@FindBy(xpath = FormsManagerPageLocators.FM_FIELD_VALUE_SAVE)
	public WebElement fmFieldValueSave;

	@FindBy(xpath = FormsManagerPageLocators.FM_CHART_FIELD_CHECKBOX)
	public WebElement fmChartFieldCheckbox; 

	@FindBy(xpath = FormsManagerPageLocators.FM_CHART_FIELD_CLICK_OK_POPUP)
	public WebElement fmChartFieldClickOkPopUp; 

	@FindBy(xpath = FormsManagerPageLocators.FM_CHART_FIELD_CLICK_CLEAR_BUTTON)
	public WebElement fmChartFieldClickClearButton;


	@FindBy(xpath = FormsManagerPageLocators.FM_FIELD_CLOSE_BUTTON)
	public WebElement fmFieldCloseButton; 

	@FindBy(xpath = FormsManagerPageLocators.FM_CHART_MEAN_UP_ARROW)
	public WebElement FMChartsMeanUpArrow;

	@FindBy(xpath = FormsManagerPageLocators.FM_CHART_VARIANCE_UP_ARROW)
	public WebElement FMChartsVarianceUpArrow;

	@FindBy(xpath = FormsManagerPageLocators.FM_CHART_SELECT_RESOURCE)
	public WebElement FMChartSelectResource;


	@FindBy(xpath = FormsManagerPageLocators.FM_RESOURCETAB_SAVE_BTN)
	public WebElement FMResourceTabSaveBtn;

	@FindBy(xpath = FormsManagerPageLocators.FM_RESOURCETAB_FORM_CLOSE_BUTTON)
	public WebElement fmResourceTabFormCloseButton;

	@FindBy(xpath = FormsManagerPageLocators.FM_CHART_RESOURCES_DEFAULT_MEAN_UP_ARROW)
	public WebElement fmChartResourcesDefaultMeanUpArrow;

	@FindBy(xpath = FormsManagerPageLocators.FM_CHART_RESOURCES_DEFAULT_VARIANCE_UP_ARROW)
	public WebElement fmChartResourcesDefaultVarianceUpArrow;

	@FindBy(xpath = FormsManagerPageLocators.FM_CHART_RESOURCES_DEFAULT_CHECKBOX)
	public WebElement fmChartResourcesDefaultCheckbox;

	@FindBy(xpath = FormsManagerPageLocators.FM_CHART_RESOURCES_DEFAULTCHECKPOPUP)
	public WebElement fmChartResourcesDefaultChecKPopUp;

	@FindBy(xpath = FormsManagerPageLocators.FM_OVERVIEW_VERIFICATION_TAB)
	public WebElement FmOverviewVerificationTab;
	
	@FindBy(xpath = FormsManagerPageLocators.FM_OVERVIEW_VERSION_LST)
	public List<WebElement> FmOverviewVersionLst;
	
	@FindBy(xpath = FormsManagerPageLocators.FM_OVERVIEW_VERSION_CMMNT_CONTAINER)
	public WebElement FmOverviewVersionCmmntContainer;

	@FindBy(xpath = FormsManagerPageLocators.LOCATION_TAB_LOC_NAME_LST)
	public List<WebElement> LocationTabLocNameLst;
	
	@FindBy(xpath = FormsManagerPageLocators.FM_IMPORT_TAB)
	public WebElement FmImportTab;
	
	@FindBy(xpath = FormsManagerPageLocators.FTU_COLUMN_NAMES_TBL)
	public List<WebElement> FTUColumnNamesTbl;
	
	@FindBy(xpath = FormsManagerPageLocators.FTU_FIRST_FORM_CHK)
	public WebElement FTUFirstFormChk;
	
	@FindBy(xpath = FormsManagerPageLocators.FTU_POPUP_IMPORT_BTN)
	public WebElement FTUPopupImportBtn;
	
	@FindBy(xpath = FormsManagerPageLocators.FTU_POPUP_CLOSE_BTN)
	public WebElement FTUPopupCloseBtn;
	
	@FindBy(xpath = FormsManagerPageLocators.FTU_VIEW_FIRST_HIST_DETS)
	public WebElement FTUViewFirstHistDets;
	
	@FindBy(xpath = FormsManagerPageLocators.FTU_HIST_ENV_DETS)
	public WebElement FTUHistEnvDets;
	
	@FindBy(xpath = FormsManagerPageLocators.FTU_HIST_FORM_DETS)
	public WebElement FTUHistFormDets;
	
	@FindBy(xpath = FormsManagerPageLocators.FTU_HIST_RESOURCE_DETS)
	public WebElement FTUHistResourceDets;
	
	
	/*
	 * @FindBy(xpath = FormsManagerPageLocators.FM_FIELD_VALUE_SAVE1) public
	 * WebElement fmFieldValueSave1;
	 */



	/**
	 * This method is used to click on VERIFICATION button.
	 * @author dahale_p
	 * @param none
	 * @return boolean This returns true if ADD NEW VERIFICATION button is clicked.
	 */
	public boolean clickVerificationBtn() { 
		try {
			controlActions.WaitforelementToBeClickable(VerificationBtn);
			VerificationBtn.click();
			Sync();
			logInfo("Clicked on VERIFICATION Menu");
			return true;
		}
		catch(Exception e) {
			logError("Failed to click on VERIFICATION Menu "
					+ e.getMessage());
			return false;
		}		
	}


	/**
	 * This method is used to click on LOCATION button.
	 * @author dahale_p
	 * @date 12 Mar 2021
	 * @param none
	 * @return boolean This returns true if Location tab is clicked.
	 */
	public boolean clickLoctionTab() { 
		try {
			controlActions.WaitforelementToBeClickable(LocationTab);
			Sync();
			controlActions.clickOnElement(LocationTab);
			logInfo("Clicked on LOCATION tab");
			Sync();
			return true;
		}
		catch(Exception e) {
			logError("Failed to click on LOCATION tab "
					+ e.getMessage());
			return false;
		}		
	}

	/**
	 * This method is used to click on PAGINATION icon.
	 * @author dahale_p 
	 * @Date  10 march
	 * @param none
	 * @return boolean This returns true if PAGINATION ICON is clicked.
	 */
	public boolean clickPagination() { 
		try {
			//	controlActions.WaitforelementToBeClickable(Verification_PaginationDrd);
			//	Sync();
			//	controlActions.clickOnElement(Verification_PaginationDrd);
			//	logInfo("Clicked on pagination selector");
			//	Sync();
			return true;


		}
		catch(Exception e) {
			logError("Failed to click on pagination selector "
					+ e.getMessage());
			return false;
		}		
	}


	/**
	 * This method is used to click on LOCATION tab.
	 * @author dahale_p
	 * @param none
	 * @return boolean This returns true if Check Box for Location Filtering is clicked.
	 */
	public boolean clickLocationMenu() {
		try {
			controlActions.WaitforelementToBeClickable(SelectLocationBtn);
			Sync();
			controlActions.clickOnElement(SelectLocationBtn);
			logInfo("Clicked on LOCATION button");
			Sync();
			return true;
		}
		catch(Exception e) {
			logError("Failed to click on LOCATION button "
					+ e.getMessage());
			return false;
		}		
	}



	/**
	 * This method is used to click on LOCATION button.
	 * @param none
	 * @return boolean This returns true if Check Box for Location Filtering is clicked.
	 */
	public boolean ClickLocationCheck() { // name Changed by PD
		try {
			controlActions.WaitforelementToBeClickable(SelectLocationChkCheck);
			Sync();
			controlActions.clickOnElement(SelectLocationChkCheck);
			logInfo("Clicked on LOCATION button");
			Sync();
			return true;
		}
		catch(Exception e) {
			logError("Failed to click on LOCATION button "
					+ e.getMessage());
			return false;
		}		
	}


	/**
	 * This method is used to check on ALL LOCATION check box.
	 * @author dahale_p
	 * @param none
	 * @return boolean This returns true if ALL LOCATION check box is clicked.
	 */

	public boolean clickLocationChk() { 
		try {
			if(SelectLocationChk.isSelected()) {
				controlActions.WaitforelementToBeClickable(SelectLocationChk);
				Sync();
				controlActions.clickOnElement(SelectLocationChk);
				logInfo("DeSelected ALL LOCATION check box");
				Sync();
			}
			else {
				controlActions.WaitforelementToBeClickable(SelectLocationChk);
				Sync();
				controlActions.clickOnElement(SelectLocationChk);
				logInfo("Selected ALL LOCATION check box");
				Sync();
			}
			return true;

		}

		catch(Exception e) {
			logError("Failed to click on ALL LOCATION Check Box button "
					+ e.getMessage());
			return false;
		}		
	}


	/**
	 * This method is used to select actual VERIFICATION .
	 * @author dahale_p
	 * @param verificationName
	 * @return boolean This returns true if ADD NEW VERIFICATION button is clicked.
	 */
	public boolean selectVerification(String verificationName) { 
		try {
			WebElement selectVerificationName = controlActions.perform_GetElementByXPath(FormsManagerPageLocators.SELECTVERIFICATION_BTN, 
					"VERIFICATION",verificationName);
			controlActions.WaitforelementToBeClickable(selectVerificationName);
			//			Sync();
			//			controlActions.clickOnElement(selectVerificationName);
			selectVerificationName.click();
			Sync();
			logInfo("Clicked on SelectVerification button");
			return true;
		}
		catch(Exception e) {
			logError("Failed to click on VERIFICATION button "
					+ e.getMessage());
			return false;
		}		
	}

	/**
	 * This method is used to link Form to Verifications
	 * @author dahale_p
	 * @param formname FormName of user to be added to Work Group
	 * @return boolean This returns true if user is set successfully
	 */
	public boolean searchAndSelectForm(String formname) {
		try {
			controlActions.WaitforelementToBeClickable(SearchUserTxt);
			controlActions.clickOnElement(SearchUserTxt);	
			controlActions.sendKeys(SearchUserTxt,formname);
			logInfo("Add form name to search textbox: " + formname);
			actionEnter();
			Sync();
			logInfo("Clicked on search button");
			//WebElement formnameChk = controlActions.perform_GetElementByXPath(FormsManagerPageLocators.LINKFORMVERIFICATIONCHK, "FORMNAME", formname);
			controlActions.clickOnElement(LinkFormVerificationChk);		
			logInfo("Selected form: " + formname);				
			return true;
		}
		catch(Exception e) {
			logError("Failed to select form: " + formname + " - "
					+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to link Form to Verifications
	 * @author dahale_p
	 * @param formname 
	 * @return boolean This returns true if user is set successfully
	 */
	public boolean searchformName(String formname) {
		try {
			controlActions.WaitforelementToBeClickable(SearchUserTxt);
			controlActions.clickOnElement(SearchUserTxt);	
			controlActions.sendKeys(SearchUserTxt,formname);
			logInfo("Add user's name to search textbox: " + formname);
			actionEnter();
			Sync();
			logInfo("Clicked on search button");
			//WebElement formnameChk = controlActions.perform_GetElementByXPath(FormsManagerPageLocators.LINKFORMVERIFICATIONCHK, "FORMNAME", formname);
			controlActions.clickOnElement(LinkFormVerificationChk);		
			logInfo("Selected user: " + formname);				
			return true;
		}
		catch(Exception e) {
			logError("Failed to select user: " + formname + " - "
					+ e.getMessage());
			return false;
		}	
	}


	/**
	 * This method is used to link Form to Verifications
	 * @author dahale_p
	 * date created 21 Jan 2021
	 * @param formname 
	 * @return boolean This returns true if user is set successfully
	 */
	public boolean searchformNameForVerification(String formname) {
		try {
			controlActions.WaitforelementToBeClickable(SearchFormnameVerificationTxt);
			controlActions.clickOnElement(SearchFormnameVerificationTxt);	
			controlActions.sendKeys(SearchFormnameVerificationTxt,formname);
			logInfo("Add forms name to search textbox: " + formname);
			actionEnter();
			Sync();
			logInfo("Clicked on search button");
			return true;
		}
		catch(Exception e) {
			logError("Failed to seaech and select form: " + formname + " - "
					+ e.getMessage());
			return false;
		}	
	}

	public void actionEnter() {
		// TODO Auto-generated method stub

	}

	/**
	 * This method is used to enable verification 
	 * @author dahale_p
	 * @param 0
	 * @return boolean This returns boolean true after Clicking ENABLE VRIFICATION button
	 */
	public boolean clickEnableVerification() {
		try {
			if(EnableVerificationBtn.isSelected()) {
				//	EnableVerificationBtn.is();
				logInfo("Toggle Button is ON");
			}
			else {
				EnableVerificationBtn.click();
				logInfo("Toggle Button is OFF");

			}
			//logInfo(EnableVerificationBtn +" is Clicked ");
			return true;
		}
		catch(Exception e) {
			logError("Failed to click element  " + EnableVerificationBtn + " :"
					+ e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to enable verification 
	 * @author dahale_p
	 * @param 0
	 * @return boolean This returns boolean true after Clicking ENABLE VRIFICATION button
	 */
	String a = "a";
	public boolean clickToggleLocation() {
		try {
			if(!ToggleLocationBtn.isEnabled()) {
				//ToggleLocationBtn.click();
				logInfo("Toggle Button is OFF");
				Sync();


			}
			else {
				ToggleLocationBtn.click();
				logInfo("Toggle Button is ON");
				Sync();

				Thread.sleep(5000);
			}
			//logInfo(ToggleLocationBtn +" is Clicked ");

			return true;
		}
		catch(Exception e) {
			logError("Failed to click element  " + ToggleLocationBtn + " :"
					+ e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to check Location is ON or OFF
	 * @author dahale_p
	 * @param 0
	 * @return boolean This returns boolean true after Clicking location tab
	 */
	public boolean clickLocationBtn() {
		try {
			if(LocationBtn.isSelected()) {
				LocationBtn.click();
				logInfo("Location tab is ON");
			}
			else {
				LocationBtn.click();
				logInfo("Location tab is OFF");

			}
			//logInfo(EnableVerificationBtn +" is Clicked ");
			return true;
		}
		catch(Exception e) {
			logError("Failed to click element  " + LocationBtn + " :"
					+ e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to set the Verification and Form by clicking on Save button
	 * @author dahale_p
	 * @param none 
	 * @return boolean This returns true if save button is clicked successfully
	 */
	public boolean clickSaveBtn() {
		try {		
			controlActions.WaitforelementToBeClickable(SaveBtn);
			controlActions.clickOnElement(SaveBtn);
			Sync();
			logInfo("Clicked on Save button");
			return true;
		}
		catch(Exception e) {
			logError("Failed to click on Save button - "
					+ e.getMessage());
			return false;
		}	
	}

	// To create wrapper function

	/**
	 * This method is used to link Verification with Form
	 * @author dahale_p
	 * @param none 
	 * @return boolean This returns true if save button is clicked successfully
	 */
	public boolean linkVerificationwithForm(String verificationName,String formName) {
		try {		
			if(!clickVerificationBtn()) {
				return false;
			}
			if(!selectVerification(verificationName)) {
				return false;
			}
			if(!searchAndSelectForm(formName)) {
				return false;
			}
			if(!clickSaveBtn()) {
				return false;
			}
			logInfo("Clicked on Save button");
			return true;
		}
		catch(Exception e) {
			logError("Failed to click on Save button - "
					+ e.getMessage());
			return false;
		}	
	}



	/**
	 * This method is used to link Verification with Form
	 * @author dahale_p
	 * @param none 
	 * @return boolean This returns true if save button is clicked successfully
	 */
	public boolean VerificationwithForm(String verificationName) {
		try {		
			if(!clickVerificationBtn()) {
				return false;
			}
			if(!selectVerification(verificationName)) {
				return false;
			}
			logInfo("Selected Form is linked with Verification");
			return true;
		}
		catch(Exception e) {
			logError("Selected Form is not linked with Verification - "
					+ e.getMessage());
			return false;
		}	
	}


	/**
	 * This method is used to search a form in Form Manager and navigate to Charts Tab
	 * @author choubey_a
	 * @param formname 
	 * @return boolean This returns true if form is selected and navigation to charts tab is done
	 */

	public boolean selectFormAndNavigaToCharts(String formname) {
		try {	
			Sync();
			controlActions.click(SearchFormnameInput);
			controlActions.sendKeys(SearchFormnameInput, formname);
			controlActions.actionEnter();
			Sync();
			WebElement formselect = controlActions.perform_GetElementByXPath(FormsManagerPageLocators.SELECT_FORM, "FORMNAME",formname);
			controlActions.doubleClick(formselect);
			Sync();
			controlActions.WaitforelementToBeClickable(ChartsTab);
			controlActions.clickOnElement(ChartsTab);
			Sync();
			logInfo("Selected a form and navigated to charts tab");
			return true;
		}
		catch(Exception e) {
			logError("Failed to select form and navigation to charts tab "
					+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to set the disable and Form by clicking on Disable button
	 * @author dahale_p
	 * @param none 
	 * @return boolean This returns true if disable button is clicked successfully
	 */
	public boolean clickDisableBtn() {
		try {		
			controlActions.clickOnElement(DisableBtn);
			logInfo("Clicked on Disable button");
			Sync();
			controlActions.clickOnElement(DisablePopupYesBtn);
			logInfo("Clicked on Popup Yes button");
			return true;
		}
		catch(Exception e) {
			logError("Failed to click on Disable button - "
					+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to set the Search the Form 
	 * @author dahale_p
	 * @param none 
	 * @return boolean This returns true if form is searched
	 * */
	public boolean searchForm(String formname) {
		try {	
			Sync();
			controlActions.click(SearchFormnameInput);
			controlActions.sendKeys(SearchFormnameInput, formname);
			controlActions.actionEnter();
			Sync();
			return true;
		}
		catch(Exception e) {
			logError("Failed to search form"
					+ e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to search the Form and Disable the Form
	 * @author dahale_p
	 * @param none 
	 * @return boolean This returns true if disable button on the Form
	 */


	public boolean selectForm(String formname) {
		WebElement SelectForm = null;
		try {	
			SelectForm = controlActions.perform_GetElementByXPath(FormsManagerPageLocators.SELECT_FORM, "FORMNAME",formname);
			controlActions.doubleClick(SelectForm);
			logInfo("Selected a form and navigated to Details tab");
			Sync();
			return true;
		}
		catch(Exception e) {
			logError("Failed to search form"
					+ e.getMessage());
			return false;
		}
	}





	/**
	 * This method is used to search the Form and Disable the Form
	 * @author dahale_p
	 * @param none 
	 * @return boolean This returns true if disable button on the Form
	 */
	public boolean selectFormAndDisable(String formName) {
		try {		
			if(!searchForm(formName)) {
				return false;
			}


			if(DisableBtn.isEnabled()){
				logInfo("Disable button will be clicked");
				clickDisableBtn();
				return true;	
			}
			else
				logInfo("State of form is disabled, so need to disable it");
			logInfo("Selected Form is disabled successfully");
			return true;
		}
		catch(Exception e) {
			logError("Failed to click on Disable button "
					+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to set the disable and Form by clicking on Disable button
	 * @author dahale_p
	 * @param none 
	 * @return boolean This returns true if disable button is clicked successfully
	 */
	public boolean clickEnableBtn() {
		try {		
			controlActions.clickOnElement(EnableBtn);
			logInfo("Clicked on Enable button");
			Sync();
			controlActions.clickOnElement(EnablePopupYesBtn);
			logInfo("Clicked on Popup Yes button");
			return true;
		}
		catch(Exception e) {
			logError("Failed to click on Enable button - "
					+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to search the Form and Disable the Form
	 * @author dahale_p
	 * @param none 
	 * @return boolean This returns true if disable button on the Form
	 */
	public boolean selectFormAndEnable(String formName) {
		try {		
			if(!searchForm(formName)) {
				return false;
			}
			if(EnableBtn.isEnabled()){
				logInfo("Enable button will be clicked");
				clickEnableBtn();
				return true;	
			}
			else
				logInfo("State of form is Enabled, so need to disable it");
			logInfo("Selected Form is Enabled successfully");
			return true;
		}
		catch(Exception e) {
			logError("Failed to click on Enabled button "
					+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to find Column Index for the column present in Users Grid
	 * @author hingorani_a
	 * @param columnName Column name whose Index you need. Use Class USERFIELDS to pass column name.
	 * This Index is in turn used in creating dynamic xpaths. 
	 * @return int This returns value of column index. 
	 * Returns 0, if column is not found
	 */
	public int getColumnIndex(String columnName) {
		int columnIndex = 0;  
		try {
			for(WebElement column : GridColumns) {
				if(column.getText().equalsIgnoreCase(columnName)) {
					columnIndex++;
					break;
				}
				else {
					columnIndex++;
				}
			}		
			logInfo("For column : " + columnName + " the index is : " + columnIndex);
			return columnIndex;
		}
		catch(Exception e) {
			logError("Failed to get index value for column : " + columnName + " - "
					+ e.getMessage());
			return columnIndex;
		}	
	}



	/**
	 * This method is used to searchAndDeSelectForm to Verifications
	 * @author dahale_p
	 * @param formname FormName of user to be added to Work Group
	 * @return boolean This returns true if user is set successfully
	 */
	public boolean searchAndDeSelectForm(String formName) {

		WebElement FormVerification = null;
		try {	
			if(!searchformNameForVerification(formName)) {//calling function searchAndSelectForm to prepare for verification
				return false;
			}

			FormVerification = controlActions.perform_GetElementByXPath(FormsManagerPageLocators.SELECT_FORM_VERIFICATION, 
					"FORMNAME",formName);

			if(FormVerification.isSelected()) {
				FormVerification.click();
				logInfo("FormVerification is Deselected");
			}
			else { 
				logError("The is form already deselected");
				return false;
			}

			return true;
		}
		catch(Exception e) {
			logError("Failed to deselect  " + FormVerification + " :"
					+ e.getMessage());
			return false;
		}
	}



	/**
	 * This method is used to toggleOFF the Location 
	 * @author dahale_p
	 * date 28 Jan 2021
	 * @param 0
	 * @return boolean This returns boolean true after deselecting the form with verification
	 */
	public boolean toggleOFFLocation(String formName) {
		try {		
			if(!searchForm(formName)) {
				return false;
			}
			if(!selectForm(formName)) {
				return false;
			}
			if(!clickLocationMenu()) {
				return false;
			}
			if(!clickToggleLocation()) {
				return false;
			}
			if(!clickLocationChk()) {
				return false;
			}
			if(!clickSaveBtn()) {
				return false;
			}
			logInfo("Clicked on Save button");
			return true;
		}
		catch(Exception e) {
			logError("Failed to click on Save button - "
					+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to toggleON the Location 
	 * @author dahale_p
	 * date 28 Jan 2021
	 * @param 0
	 * @return boolean This returns boolean true after deselecting the form with location
	 */
	public boolean toggleONLocation(String formName) {
		try {		
			if(!searchForm(formName)) {
				return false;
			}
			if(!selectForm(formName)) {
				return false;
			}
			if(!clickLocationMenu()) {
				return false;
			}

			if(!clickLocationChk()) {
				return false;
			}
			if(!clickSaveBtn()) {
				return false;
			}
			logInfo("Clicked on Save button");
			return true;
		}
		catch(Exception e) {
			logError("Failed to click on Save button - "
					+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to toggleON the Location 
	 * @author dahale_p
	 * date 11 feb 2021
	 * @param 0
	 * @return boolean This returns boolean true after deselecting the form with verification
	 */
	public boolean LocationFormtoggleON(String formName) {
		try {		
			if(!searchForm(formName)) {
				return false;
			}
			if(!selectForm(formName)) {
				return false;
			}
			if(!clickLocationMenu()) {
				return false;
			}
			if(!clickToggleLocation())  {
				return false;
			}
			//perform invisibility of Enable toggle text is off

			if(!ClickLocationCheck()) {
				return false;
			}
			if(!clickSaveBtn()) {
				return false;
			}
			logInfo("Clicked on Save button");
			return true;
		}
		catch(Exception e) {
			logError("Failed to click on Save button - "
					+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to toggleON the Location 
	 * @author dahale_p
	 * date 11 feb 2021 one more
	 * @param 0
	 * @return boolean This returns boolean true after FormtoggleONLocation
	 */
	public boolean FormtoggleONLocation(String formName) {//35052 2nd
		try {		
			if(!searchForm(formName)) {
				return false;
			}
			if(!selectForm(formName)) {
				return false;
			}
			if(!clickLocationMenu()) {
				return false;
			}

			if(!ClickLocationCheck()) {
				return false;
			}
			if(!clickSaveBtn()) {
				return false;
			}
			logInfo("Clicked on Save button");
			return true;
		}
		catch(Exception e) {
			logError("Failed to click on Save button - "
					+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to deselect verification 
	 * @author dahale_p
	 * @param 0
	 * @return boolean This returns boolean true after deselecting the form with verification
	 */
	public boolean delinkVerificationwithForm(String verificationName,String formName) {
		try {		
			if(!clickVerificationBtn()) {
				return false;
			}
			if(!selectVerification(verificationName)) {
				return false;
			}
			if(!searchAndDeSelectForm(formName)) {
				return false;
			}
			if(!clickSaveBtn()) {
				return false;
			}
			logInfo("Clicked on Save button");
			return true;
		}
		catch(Exception e) {
			logError("Failed to click on Save button - "
					+ e.getMessage());
			return false;
		}	
	}


	/**
	 * This method is used to toggleOFF the Location 
	 * @author dahale_p
	 * date 28 Jan 2021
	 * @param 0
	 * 
	 * fm.searchForm(checkFormNameFM2);
		fm.selectForm(checkFormNameFM2);
		fm.clickVerificationBtn();
		fm.VerificationwithForm(verificationName);
		fm.clickEnableVerification();
		fm.clickSaveBtn();
	 * @return boolean This returns boolean true after deselecting the form with verification
	 */
	public boolean verificationToggle(String formName, String verificationName ) {//verificationToggle -to be used as 38651 and 38652
		try {		
			if(!searchForm(formName)) {
				return false;
			}
			if(!selectForm(formName)) {
				return false;
			}
			if(!clickVerificationBtn()) {
				return false;
			}
			if(!VerificationwithForm(verificationName)) {
				return false;
			}
			if(!clickEnableVerification()) {
				return false;
			}
			if(!clickSaveBtn()) {
				return false;
			}
			logInfo("Clicked on Save button");
			return true;
		}
		catch(Exception e) {
			logError("Failed to click on Save button - "
					+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to enabled/disabled verification 
	 * @author dahale_p
	 * @param 0
	 * @return boolean This returns boolean true after deselecting the form with verification
	 */

	public boolean performEnableDisableAction(String status) {
		try {		
			switch(status) {
			case FM_STATUS.ENABLED:
				if(!clickDisableBtn()) {
					return false;
				}
				break;
			case FM_STATUS.DISABLED:
				if(!clickEnableBtn()) {
					return false;
				}
				break;
			}
			logInfo("Clicked on Popup Yes button");
			return true;
		}
		catch(Exception e) {
			logError("Failed to click on Disable button - "
					+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to click Form Designer button from the Form manager men
	 * @author dahale_p
	 * date 08 march 2021
	 * @param 0
	 * @return boolean This returns boolean true after deselecting the form with verification
	 */
	public boolean clickFormDesignerNew() {
		try {
			controlActions.WaitforelementToBeClickable(FormDesignerBtn);
			Sync();
			controlActions.clickOnElement(FormDesignerBtn);
			logInfo("Clicked on Form Designer Button");
			Sync();
			return true;
		}
		catch(Exception e) {
			logError("Failed to click on Form Designer button "
					+ e.getMessage());
			return false;
		}		
	}

	/**
	 * This method is used to click Form Designer button from the Form manager men
	 * @author dahale_p
	 * date 08 march 2021
	 * @param 0
	 * @return boolean This returns boolean true after deselecting the form with verification
	 */
	public boolean clickTaskSchdulerBtn() {
		try {
			controlActions.WaitforelementToBeClickable(CreateTaskSchdulerBtn);
			Sync();
			controlActions.clickOnElement(CreateTaskSchdulerBtn);
			logInfo("Clicked on Create task Scheduler Button");
			Sync();
			return true;
		}
		catch(Exception e) {
			logError("Failed to click on Create task Scheduler button "
					+ e.getMessage());
			return false;
		}		
	}

	/**
	 * This method is used to click Location from the Form manager menu
	 * @author dahale_p
	 * date 30 march 2021
	 * @param 0
	 * @return boolean This returns boolean true after 
	 */
	public boolean clickLocationTab() {
		try {
			controlActions.WaitforelementToBeClickable(LocationTab);
			Sync();
			controlActions.clickOnElement(LocationTab);
			logInfo("Clicked on Location Tab Button");
			Sync();
			return true;
		}
		catch(Exception e) {
			logError("Failed to click on Location Tab Button "
					+ e.getMessage());
			return false;
		}		
	}


	/**
	 * This method is used to set the Search the Location from Click form> Location Tab 
	 * @author dahale_p
	 * @param none 
	 * @date 31 March 2021
	 * @return boolean This returns true if location is get searched is done
	 * */
	public boolean searchLocationName(String locationname) {

		try {	
			Sync();
			controlActions.click(SearchLocationNameInput);
			controlActions.sendKeys(SearchLocationNameInput, locationname);
			controlActions.actionEnter();
			Sync();
			//		controlActions.perform_waitUntilClickable(SearchLocationNameInput);
			//		SearchLocationNameInput.click();
			//		SearchLocationNameInput.sendKeys(locationname);
			//		controlActions.actionEnter();
			//		Sync();
			return true;
		}
		catch(Exception e) {
			logError("Failed to search location"
					+ e.getMessage());

			return false;
		}
	}

	/**
	 * This method is used to link Location search
	 * @author dahale_p
	 * @param none 
	 * @return boolean This returns true if searched location is highlighted successfully
	 */
	public boolean selectAndSetLocation(String locationName) {
		try {		
			if(!clickLocationTab()) {
				return false;
			}
			if(!searchLocationName(locationName)) {
				return false;
			}

			logInfo("Able to search location");
			return true;
		}
		catch(Exception e) {
			logError("Failed to search location: - "
					+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to click on Select All Forms Checkbox
	 * @param none
	 * @return boolean This returns true if Check Box for Location Filtering is clicked.
	 */
	public boolean clickSelectAllFormsCheck() { 
		try {
			controlActions.WaitforelementToBeClickable(SelectAllFormsCheck);
			Sync();
			controlActions.clickOnElement(SelectAllFormsCheck);
			logInfo("Clicked on select checkbox");
			Sync();
			return true;
		}
		catch(Exception e) {
			logError("Failed to click on Select All checkbox "
					+ e.getMessage());
			return false;
		}		
	}

	/**
	 * This method is used to click on Next Arrow to turn next Page
	 * @param none
	 * @return boolean This returns true if click on Next Arrow to turn next Page
	 */
	public boolean clickOnNextPageArrow() { 
		try {
			controlActions.WaitforelementToBeClickable(ClickNextPageArrow);
			Sync();
			controlActions.clickOnElement(ClickNextPageArrow);
			logInfo("Clicked on next page arrow");
			Sync();
			return true;
		}
		catch(Exception e) {
			logError("Failed to click on next page arrow "
					+ e.getMessage());
			return false;
		}		
	}
	/**
	 * This method is used to Verification of Bulk association of Forms with Verification Type
	 * @author dahale_p
	 * @param none 
	 * @return boolean This returns true if save button is clicked successfully
	 */
	public boolean linkingVerificationWithAllForms(String verificationName) {
		try {		
			if(!clickVerificationBtn()) {
				return false;
			}
			if(!selectVerification(verificationName)) {
				return false;
			}
			if(!clickSelectAllFormsCheck()) {
				return false;
			}
			if(!clickSaveBtn()) {
				return false;
			}
			logInfo("Clicked on Save button");
			return true;
		}
		catch(Exception e) {
			logError("Failed to click on Save button - "
					+ e.getMessage());
			return false;
		}	
	}


	/**
	 * This method is used to Verification of Bulk association of Forms with Verification Type on next page
	 * @author dahale_p
	 * @param none 
	 * @return boolean This returns true if save button is clicked successfully
	 */
	public boolean linkingVerificationWithAllFormsNextPageArrow(String verificationName) {
		try {		
			if(!clickOnNextPageArrow()) {
				return false;
			}

			if(!clickSelectAllFormsCheck()) {
				return false;
			}
			if(!clickSaveBtn()) {
				return false;
			}
			logInfo("Clicked on Save button");
			return true;
		}
		catch(Exception e) {
			logError("Failed to click on Save button - "
					+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to set the Search the Location from Click form> Location Tab 
	 * @author dahale_p
	 * @param none 
	 * @date 07 April 2021
	 * @return boolean This returns true if location is get searched is done
	 * */
	public boolean clickDirectObservationVerification(String directobservation,String adminverificationPinTxt) {

		try {	
			Sync();
			controlActions.click(VerifyFormBtn);
			controlActions.click(DirectObsUserNameTxt);
			controlActions.sendKeys(DirectObsUserNameTxt,directobservation);
			controlActions.actionEnter();
			controlActions.click(VerificationPinTxt);
			controlActions.sendKeys(VerificationPinTxt,adminverificationPinTxt);
			controlActions.actionEnter();

			controlActions.click(IsCompliantToggleYes);
			Sync();
			controlActions.WaitforelementToBeClickable(SaveBtn);
			controlActions.clickOnElement(SaveBtn);
			Sync();
			controlActions.click(CloseDirObsPopUpBtn);
			Sync();
			logInfo("Clicked on Save button");
			return true;


		}
		catch(Exception e) {
			logError("Failed to click on Save button"
					+ e.getMessage());
			return false;
		}
	}


	/**
	 * This method is used to search Validation
	 * @author hingorani_a
	 * @param columnName Column name whose Index you need. Use Class VLDTN_FIELDS to pass column name.
	 * @param value The value you would like to use to search Validation 
	 * @return boolean This returns true if Validation search is performed successfully
	 */
	public boolean searchFormDetails(String columnName, String value) {
		String finalColumnNameXpath = null; 
		try {
			int columnIndex = getColumnIndex(columnName);
			if(columnIndex == 0) {
				logError("Failed to get correct column index : " + columnIndex + " for column " + columnName);
				return false;
			}
			else {
				finalColumnNameXpath = controlActions.perform_GetDynamicXPath(FormsManagerPageLocators.COLUMN_NAMES_TXT, 
						"COLUMNINDEXNO", 
						Integer.toString(columnIndex));

				if(controlActions.perform_PutText(finalColumnNameXpath,value, false)) {
					Sync();
					logInfo("Searched validation with value : " + value + " for column : " + columnName);
					return true;
				}
			}
			return false;
		}
		catch(Exception e) {
			logError("Failed to search validation with value : " + value + " for column : " + columnName + " - "
					+ e.getMessage());
			return false;
		}	
	}



	/**
	 * This method is used to enable or disable the mentioned form
	 * @author hingorani_a
	 * @param formName The name of the form 
	 * @param status Use Class FM_STATUS to set a status we want to set for 
	 * @return boolean This returns true if a form is enabled/disabled as per mentioned status
	 */
	public boolean searchAndEnableDisableForm(String formName, String status) {
		String currentStatus = null; 
		try {

			if(!searchForm(formName))
				return false;

			if(!selectForm(formName))
				return false;

			if(status.equals(FM_STATUS.DISABLED))
				currentStatus = FM_STATUS.ENABLED;
			else if(status.equals(FM_STATUS.ENABLED))
				currentStatus = FM_STATUS.DISABLED;

			if(!performEnableDisableAction(currentStatus))
				return false;

			logInfo("The form " + formName + " has been " + status);
			return true;

		}
		catch(Exception e) {
			logError("Failed to set form : " + formName + " to : " + status + " state - "
					+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to link Verification with Form from Overview
	 * @author dahale_p
	 * @param none 
	 * 27 April
	 * @return boolean This returns true if Form get search And Selected successfully.
	 */

	public boolean searchAndFormSelection(String formName) {
		try {		
			if(!searchForm(formName)) { 
				return false;
			}
			if(!selectForm(formName)) {
				return false;
			}

			logInfo("Able to Select Form");
			return true;
		}
		catch(Exception e) {
			logError("Failed to Select Form - "
					+ e.getMessage());
			return false;
		}	

	}

	/**
	 * This method is used to link Verification with Form
	 * @author dahale_p
	 * @param none 
	 * 27 April
	 * @return boolean This returns true if Form get search And Selected successfully.
	 */

	public boolean clickOnFormVersion(String formName) {
		try {		
			Sync();

			controlActions.WaitforelementToBeClickable(FormManagerVersionLnk);
			Sync();
			controlActions.clickOnElement(FormManagerVersionLnk);
			logInfo("Clicked on FormVersion Link");
			Sync();
			logInfo("Able to click Form version");
			return true;
		}
		catch(Exception e) {
			logError("Failed to click on Form Version - "
					+ e.getMessage());
			return false;
		}	

	}
	/**
	 * This method is used to link Verification with Form
	 * @author dahale_p
	 * @param none 
	 * 27 April
	 * @return boolean This returns true if Form get search And Selected successfully.
	 */

	public boolean clickOnFormViewComment(String formName) {
		try {		
			Sync();

			controlActions.WaitforelementToBeClickable(FormManagerViewCommentLnk);
			Sync();
			controlActions.clickOnElement(FormManagerViewCommentLnk);
			logInfo("Clicked on Form comment Link");
			Sync();
			logInfo("Able to click Form comment link");
			return true;
		}
		catch(Exception e) {
			logError("Failed to click on Form View Comment Link - "
					+ e.getMessage());
			return false;
		}	

	}

	/**
	 * This method is used to link Verification with Form
	 * @author dahale_p
	 * @param none 
	 * @return boolean This returns true if save button is clicked successfully
	 */
	public boolean linkVerificationwithFormNewDO(String directobservation,String formName) {
		try {		


			if(!selectVerification(directobservation)) {
				return false;
			}

			if(!clickSaveBtn()) {
				return false;
			}

			logInfo("Clicked on Save button");
			return true;
		}
		catch(Exception e) {
			logError("Failed to click on Save button - "
					+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to link Verification with Form
	 * @author dahale_p
	 * @param none 
	 * @return boolean This returns true if save button is clicked successfully
	 */
	public boolean verificationClick(String verificationName) {
		try {		
			if(!clickVerificationBtn()) {
				return false;
			}
			if(!selectVerification(verificationName)) {
				return false;
			}

			logInfo("Clicked on Verification menu ");
			return true;
		}
		catch(Exception e) {
			logError("Failed to click on Verification menu "
					+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to toggleON the Location 
	 * @author dahale_p
	 * date 11 feb 2021
	 * @param 0
	 * @return boolean This returns boolean true after deselecting the form with verification
	 */
	public boolean LocationFormtoggleONLinking(String formName) {
		try {		
			if(!searchForm(formName)) {
				return false;
			}
			if(!selectForm(formName)) {
				return false;
			}
			if(!clickLocationMenu()) {
				return false;
			}
			if(!clickToggleLocation())  {
				return false;
			}
			//perform invisibility of Enable toggle text is off

			//if(!ClickLocationCheck()) {
			//	return false;
			//}
			if(!clickSaveBtn()) {
				return false;
			}
			logInfo("Clicked on Save button");
			return true;
		}
		catch(Exception e) {
			logError("Failed to click on Save button - "
					+ e.getMessage());
			return false;
		}	
	}



	/**
	 * This method is used to link Verification with Form
	 * @author dahale_p
	 * 2 June 2021
	 * @param verificationName 
	 * @return boolean This returns true if Location menu is clicked successfully
	 */
	public boolean clickLocationVerification(String locationName) {
		try {		
			//	if(!clickLocationMenu()) {
			//		return false;
			//	}
			if(!selectAndSetLocation(locationName)) {
				return false;
			}

			logInfo("Clicked on Location menu ");
			return true;
		}
		catch(Exception e) {
			logError("Failed to click on Location menu "
					+ e.getMessage());
			return false;
		}	
	}


	/**
	 * This method is used to set the Search the Location from Click form> Location Tab 
	 * @author dahale_p
	 * @param none 
	 * @date 3 June 2021
	 * @return boolean This returns true if location is get searched is done
	 * */
	public boolean searchselectLocationName(String locationname) {

		try {	
			Sync();
			controlActions.click(SearchLocationNameInput);
			controlActions.sendKeys(SearchLocationNameInput, locationname); 
			controlActions.actionEnter();
			Sync();
			//controlActions.clickOnElement(LocationSelectionTxt);		
			//logInfo("Selected form: " + locationname);
			return true;

		}
		catch(Exception e) {
			logError("Failed to search and select location"
					+ e.getMessage());
			return false;
		}
	}


	/**
	 * This method is used to click on Settings dropdown link for a column
	 * @author hingorani_a
	 * @param columnName Use Class FORMMANAGER_FIELDS to set value for column name
	 * @return boolean This returns true once we click on Settings dropdown
	 */
	public boolean clickColumnDropDown(String columnName) {
		WebElement ColumnSettingsLnk = null;
		try {
			ColumnSettingsLnk = controlActions.perform_GetElementByXPath(FormsManagerPageLocators.COLUMN_SETTINGS_LNK, 
					"COLUMNNAME", columnName);
			//			Sync();
			controlActions.WaitforelementToBeClickable(ColumnSettingsLnk);
			ColumnSettingsLnk.click();
			Sync();
			logInfo("Clicked on column : " + columnName + " settings link");
			return true;
		}
		catch(Exception e) {
			logError("Failed to click on column : " + columnName + " settings link " + " - "
					+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to apply settings like Sorting on column
	 * @author dahale_p
	 * @param settingName Use Class COLUMN_SETTING to set value for column name
	 * @param settingValue If you want to check/uncheck any columns, provide the text
	 * to be used with separator '|' else for other settings keep it as null.
	 * Example: "Name-Check|Status-Uncheck"
	 * @return boolean This returns true after applying setting
	 */
	public boolean applySettingsForColumn(String settingName, String settingValue) {

		WebElement PopupOptionMnu = null;
		WebElement PopupColumnSetOptn = null;
		try {

			PopupOptionMnu = controlActions.perform_GetElementByXPath(FormsManagerPageLocators.POPUP_OPTION_MNU, 
					"POPUPOPTION", settingName);

			switch(settingName) {
			case COLUMN_SETTING.SORTASCENDING:
			case COLUMN_SETTING.SORTDESCENDING:

				controlActions.WaitforelementToBeClickable(PopupOptionMnu);
				PopupOptionMnu.click();
				Sync();
				logInfo("Applied " + settingName + " to column");
				return true;


			case COLUMN_SETTING.COLUMNS:

				String[] settings = CommonMethods.splitAndGetString(settingValue);
				String[] settingPairs = null;

				for(int i = 0; i < settings.length; i++) {
					settingPairs = CommonMethods.splitAndGetString(settings[i],"-");
					PopupColumnSetOptn = controlActions.perform_GetElementByXPath(FormsManagerPageLocators.POPUP_COLUMN_SET_OPTN, 
							"COLUMNNAME", settingPairs[0]);
					if(settingPairs[1].equalsIgnoreCase(COLUMN_SETTING.UNCHECK) && PopupColumnSetOptn.isSelected()) {
						PopupColumnSetOptn.click();
						logInfo("Deselected for column " + settingPairs[0]);
					}
					else if(settingPairs[1].equalsIgnoreCase(COLUMN_SETTING.CHECK) && !PopupColumnSetOptn.isSelected()) {
						PopupColumnSetOptn.click();
						logInfo("Selected for column " + settingPairs[0]);
					}
				}

				PopupColumnApplyBtn.click();
				logInfo("Applied settings for column by clicking on Apply button");
				return true;

			default:
				logInfo("Incorrect setting provided : " + settingValue);
				return false;
			}
		}
		catch(Exception e) {
			logError("Failed to apply : " + settingName + " on column " + " - "
					+ e.getMessage());
			return false;
		}	
	}
	/**
	 * This method is used to open settings popup and then apply settings
	 *  like Sorting, Filtering on column
	 * @author dahale_p
	 * @param columnName Use Class FM_FIELDS to set value for column name
	 * @param settingName Use Class COLUMN_SETTING to set value for column name
	 * @param settingValue If you want to check/uncheck any columns, provide the text
	 * to be used with separator '|' else for other settings keep it as null.
	 * Example: "Name-Check|Status-Uncheck"
	 * @return boolean This returns true after applying setting
	 */
	public boolean openAndApplySettingsForColumn(String columnName, String settingName, String settingValue) {
		try {
			if(!clickColumnDropDown(columnName)) {
				return false;
			}

			if(!applySettingsForColumn(settingName, settingValue)) {
				return false;
			}

			Sync();
			logInfo("Successfully applied setting " + settingName + " to column " + columnName);
			return true;
		}
		catch(Exception e) {
			logError("Failed to apply setting " + settingName + " to column " + columnName + " - "
					+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to link Verification with Form
	 * @author dahale_p
	 * @param none
	 * date 20 MAY 2021 
	 * @return boolean This returns true if save button is clicked successfully TC TestCase_38629
	 */

	public boolean verificationStatus( String verificationName,String formName) {
		try {		
			if(!searchForm(formName)) {
				return false;
			}
			if(!selectForm(formName)) {
				return false;
			}
			if(!clickVerificationBtn()) {
				return false;
			}

			if(!selectVerification(verificationName)) {
				return false;

			}
			logInfo("Successfully Checked the Verification status");
			return true;
		}

		catch(Exception e) {
			logError("Failed to check verification name - "
					+ e.getMessage());
			return false;
		}	
	}



	/**
	 * This method is used to Disable verification 
	 * @author dahale_p
	 * @param 0
	 * 25 th MAY 2021
	 * @return boolean This returns boolean true after Clicking ENABLE VRIFICATION button
	 */
	public boolean clickDisableVerification() {
		try {
			EnableVerificationBtn.click(); 
			logInfo("Toggle Button is OFF");


			return true;
		}
		catch(Exception e) {
			logError("Failed to click element  " + EnableVerificationBtn + " :"
					+ e.getMessage());
			return false;
		}
	}




	/**
	 * This method is used to search and get FORM MANAGER detail as per column
	 * @author hingorani_a
	 * 28 MAY 2021 
	 * @param columnName Column name whose Index you need. Use Class VLDTN_FIELDS to pass column name.
	 * @return String This returns string value with the validation detail if found; else null
	 */
	public String getFormManagerDetails(String columnName) {
		WebElement ColumnValue = null; 
		try {
			int columnIndex = getColumnIndex(columnName);
			if(columnIndex == 0) {
				logError("Failed to get correct column index : " + columnIndex + " for column " + columnName);
				return null;
			}
			else {
				ColumnValue = controlActions.perform_GetElementByXPath(FormsManagerPageLocators.FM_COLUMN_VALUE, 
						"COLUMNINDEXNO", 
						Integer.toString(columnIndex));

				String value = ColumnValue.getText();
				logInfo("For column : " + columnName + " value retrieved as - " + value);
				return value;
			}
		}
		catch(Exception e) {
			logError("Failed to get value for column : " + columnName + " - "
					+ e.getMessage());
			return null;
		}	
	}



	/**
	 * This method is used to set filters on columns of Form Manager Verification grid
	 * @author hingorani_a
	 * @param columnName Use class FM_FIELDS to set name of column
	 * @param filterValue The value to be set for filter
	 * @return boolean Return true when filter value is set successfully
	 */
	public boolean setFilterToColumn(String columnName, String filterValue) {
		WebElement DropdwnOptn = null;

		try {

			int columnIndex = getColumnIndex(columnName);
			if(columnIndex == 0) { 
				logError("Failed to get correct column index : " + columnIndex + " for column " + columnName);
				return false;
			}
			else {
				switch(columnName) {

				case FM_FIELDS.FORM:
					if(searchFormMgrWithDetails(columnName, filterValue)) 
						return true;
					else
						return false;



				case FM_FIELDS.TYPE:

					controlActions.perform_ClickWithJavaScriptExecutor(FmDropDownColumnValue);  
					DropdwnOptn = controlActions.perform_GetElementByXPath(FormsManagerPageLocators.DROPDWN_OPTN, 
							"DROPDOWNOPTION", filterValue);
					Sync();
					controlActions.perform_waitUntilClickable(DropdwnOptn);
					Sync();
					controlActions.perform_ClickWithJavaScriptExecutor(DropdwnOptn);
					Sync();
					logInfo("For column : " + columnName + " filter value set as - " + filterValue);
					return true;

				default:
					logError("Incorrect column value - " + columnName);
					return false;
				}
			}
		}
		catch(Exception e) {
			logError("Failed to set filter to column - " + columnName + " "
					+ e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to search Verifications
	 * @author hingorani_a
	 * @param columnName Column name whose Index you need. Use Class VLDTN_FIELDS to pass column name.
	 * @param value The value you would like to use to search Verifications 
	 * @return boolean This returns true if Validation search is performed successfully
	 */
	public boolean searchFormMgrWithDetails(String columnName, String value) {
		String finalColumnNameXpath = null; 
		try {
			int columnIndex = getColumnIndex(columnName);
			if(columnIndex == 0) {
				logError("Failed to get correct column index : " + columnIndex + " for column " + columnName);
				return false;
			}
			else {
				finalColumnNameXpath = controlActions.perform_GetDynamicXPath(FormsManagerPageLocators.COLUMN_FM_FORMNAME_TXT, 
						"COLUMNINDEXNO", 
						Integer.toString(columnIndex));

				if(controlActions.perform_PutText(finalColumnNameXpath,value, false)) {
					Sync();
					logInfo("Searched FORM  with value : " + value + " for column : " + columnName);
					return true;
				}
			}
			return false;
		}
		catch(Exception e) {
			logError("Failed to search validation with value : " + value + " for column : " + columnName + " - "
					+ e.getMessage());
			return false;
		}	
	}
	/**
	 * This method is used to verify Forms detail against a column
	 * @author  Dahale_p
	 * 	14 June 2021
	 * @param columnName Column name whose Index you need. Use Class FM_FIELDS to pass column name.
	 * @param value The value to be compared to the value displayed for Column
	 * @return String This returns true if the value is found for the given column
	 */
	public boolean verifyValidationDetail(String columnName, String value) {
		List<WebElement> ColumnValue = null; 
		try {
			int columnIndex = getColumnIndex(columnName);
			if(columnIndex == 0) {
				logError("Failed to get correct column index : " + columnIndex + " for column " + columnName);
				return false;
			}
			else {
				ColumnValue = controlActions.perform_GetListOfElementsByXPath(FormsManagerPageLocators.COLUMN_FM_FORMNAME_TXT,
						"COLUMNINDEXNO", Integer.toString(columnIndex));

				for(WebElement element : ColumnValue) {
					if(element.getText().contains(value)) {
						logInfo("For column : " + columnName + " value verified as - " + value);
						return true;
					}
				}
			}

			logError("Could not verify value for column : " + columnName);
			return false;
		}
		catch(Exception e) {
			logError("Failed to verify value for column : " + columnName + " - "
					+ e.getMessage());
			return false;
		}	
	}


	/**
	 * This method is used to scroll the element to horizontal right
	 * @author dahale_p
	 * @param columnName Use Class COLUMNHEADER to set value for column name
	 * @return 
	 */

	public boolean horizontalscroll() {
		try {	
			Sync();
			controlActions.click(FmOverviewScrollHR);
			//	sendKeys(Keys.ARROW_RIGHT);
			controlActions.action.sendKeys(Keys.ARROW_RIGHT);

			//actionEnter();
			//controlActions.actionEnter();
			Sync();
			return true;
		}
		catch(Exception e) {
			logError("Failed to search form"
					+ e.getMessage());
			return false;
		}
	}


	/** This method is used to clear applied filter on Form manager grid iff one filter is applied
	 * @author dahale_p
	 * @param none
	 * @return boolean return true when applied filter is cleared
	 */	
	public boolean clearAppliedFilter() {
		try {

			if(ClearGridFilterBtn.size() == 1) {
				ClearGridFilterBtn.get(0).click();
				Sync();
				logInfo("Cleared applied filter");
				return true;
			}
			else {
				logError("Could not clear applied filter as there are more than 1 applied filters");
				return false;
			}
		}
		catch(Exception e) {
			logError("Failed to clear applied filter " + e.getMessage());
			return false;
		}
	}


	/** This method is used to clear applied filter on Form manager grid iff one filter is applied
	 * @author dahale_p
	 * @param none
	 * @return boolean return true when applied filter is cleared
	 */	
	public boolean clickSelectitem() {
		WebElement DropdwnOptn = null;

		try {
			Sync();
			controlActions.perform_ClickWithJavaScriptExecutor(FmDropDownColumnValue);
			/*
			 * DropdwnOptn =
			 * controlActions.perform_GetElementByXPath(FormsManagerPageLocators.
			 * DROPDWN_OPTN, "DROPDOWNOPTION", filterValue);
			 */Sync();
			 controlActions.perform_waitUntilClickable(DropdwnOptn);
			 Sync();
			 controlActions.perform_ClickWithJavaScriptExecutor(DropdwnOptn);
			 Sync();
			 logInfo("Clicked on select item filter");
			 return true;
		}

		catch(Exception e) {
			logError("Failed to click select item filter " + e.getMessage());
			return false;
		}
	}

	/** This method is used to clear applied filter on Forms Manager grid for mentioned column
	 * @author hingorani_a
	 * @param columnName Use class FM_FIELDS to set name of column
	 * @return boolean return true when applied filter is cleared
	 */	
	public boolean clearAppliedFilter(String columnName) {
		WebElement ClearColumnFilterBtn = null; 

		try {
			int columnIndex = getColumnIndex(columnName);
			if(columnIndex == 0) {
				logError("Failed to get correct column index : " + columnIndex + " for column " + columnName);
				return false;
			}
			else {
				ClearColumnFilterBtn = controlActions.perform_GetElementByXPath(FormsManagerPageLocators.CLEAR_COLUMN_FILTER_BTN, 
						"COLUMNINDEXNO", Integer.toString(columnIndex));

				ClearColumnFilterBtn.click();
				Sync();
				logInfo("Cleared applied filter for column - " + columnName);
				return true;
			}
		}
		catch(Exception e) {
			logError("Failed to clear applied filter for column - " + columnName
					+ e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to get list of Elements for a column of Form Manager grid
	 * @author hingorani_a Dahale_p 
	 * 14 June 2021 to be checked
	 * @param columnName Use Class COLUMNHEADER to set value for column name
	 * @return List<WebElement> This returns list of WebElements for given column
	 */
	public List<WebElement> getListOfElementsForFormManager(String columnName) {
		List<WebElement> ColumnValue = null;
		try {
			int columnIndex = getColumnIndex(columnName);
			if(columnIndex == 0) {
				logError("Failed to get correct column index : " + columnIndex + " for column " + columnName);
				return ColumnValue;
			}
			else {
				ColumnValue = controlActions.perform_GetListOfElementsByXPath(FormsManagerPageLocators.FM_COLUMN_VALUE, 
						"COLUMNINDEXNO", Integer.toString(columnIndex));
				logInfo("Retrieved list of elements for column : " + columnName);
				return ColumnValue;
			}
		}
		catch(Exception e) {
			logError("Failed to get list of elements for column : " + columnName + " - "
					+ e.getMessage());
			return ColumnValue;
		}	
	}

	/**
	 * This method is used to search Form name in Location Tab
	 * @author dahale_p
	 * @param columnName Column name whose Index you need. Use Class FM_FIELDS to pass column name.
	 * @param value The value you would like to use to search Form name in Location Tab
	 * @return boolean This returns true if Form name in Location Tab search is performed successfully
	 */
	public boolean searchFormMgrWithDetailsForLocationForm(String columnName, String value) {
		String finalColumnNameXpath = null; 
		try {
			int columnIndex = getColumnIndex(columnName);
			if(columnIndex == 0) {
				logError("Failed to get correct column index : " + columnIndex + " for column " + columnName);
				return false;
			}
			else {
				finalColumnNameXpath = controlActions.perform_GetDynamicXPath(FormsManagerPageLocators.COLUMN_FM_FORMNAME_LOCATION_TXT, 
						"COLUMNINDEXNO", 
						Integer.toString(columnIndex));

				if(controlActions.perform_PutText(finalColumnNameXpath,value, false)) {
					Sync();
					logInfo("Searched FORM  with value : " + value + " for column : " + columnName);
					return true;
				}
			}
			return false;
		}
		catch(Exception e) {
			logError("Failed to search validation with value : " + value + " for column : " + columnName + " - "
					+ e.getMessage());
			return false;
		}	


	}

	/**
	 * This method is used to set filters on columns of Form Manager Location grid
	 * @author dahale_p
	 * @param columnName Use class FM_FIELDS to set name of column
	 * @param filterValue The value to be set for filter
	 * @return boolean Return true when filter value is set successfully
	 */
	public boolean setFilterToColumnLocation(String columnName, String filterValue) {
		WebElement DropdwnOptn = null;

		try {

			int columnIndex = getColumnIndex(columnName);
			if(columnIndex == 0) { 
				logError("Failed to get correct column index : " + columnIndex + " for column " + columnName);
				return false;
			}
			else {
				switch(columnName) {

				case FM_FIELDS.FORM:
					if(searchFormMgrWithDetailsForLocationForm(columnName, filterValue)) 
						return true;
					else
						return false;



				case FM_FIELDS.TYPE:

					controlActions.perform_ClickWithJavaScriptExecutor(FmDropDownColumnValue);  
					DropdwnOptn = controlActions.perform_GetElementByXPath(FormsManagerPageLocators.DROPDWN_OPTN, 
							"DROPDOWNOPTION", filterValue);
					Sync();
					controlActions.perform_waitUntilClickable(DropdwnOptn);
					Sync();
					controlActions.perform_ClickWithJavaScriptExecutor(DropdwnOptn);
					Sync();
					logInfo("For column : " + columnName + " filter value set as - " + filterValue);
					return true;

				default:
					logError("Incorrect column value - " + columnName);
					return false;
				}
			}
		}
		catch(Exception e) {
			logError("Failed to set filter to column - " + columnName + " "
					+ e.getMessage());
			return false;
		}
	}

	/** This method is used to clear applied filter on Forms Manager's LOCATION Tab grid for mentioned column
	 * @author Dahale_p
	 * @23 June 2021
	 * @param columnName Use class FM_FIELDS to set name of column
	 * @return boolean return true when applied filter is cleared
	 */	
	public boolean clearAppliedFilterLocationTab(String columnName) {
		WebElement ClearColumnFilterBtn = null; 

		try {
			int columnIndex = getColumnIndex(columnName);
			if(columnIndex == 0) {
				logError("Failed to get correct column index : " + columnIndex + " for column " + columnName);
				return false;
			}
			else {
				ClearColumnFilterBtn = controlActions.perform_GetElementByXPath(FormsManagerPageLocators.CLEAR_COLUMN_FILTER_LOCATION_BTN, 
						"COLUMNINDEXNO", Integer.toString(columnIndex));

				ClearColumnFilterBtn.click();
				Sync();
				logInfo("Cleared applied filter for column - " + columnName);
				return true;
			}
		}
		catch(Exception e) {
			logError("Failed to clear applied filter for column - " + columnName
					+ e.getMessage());
			return false;
		}
	}


	/**
	 * This method is used to move for the page view
	 * @author dahale_p
	 * @param none
	 * @return boolean This returns true after  move for the page view
	 */
	public boolean clickMovetoViewColumn(String columnName) {

		WebElement ColumnSettingsLnk = null;
		try {
			ColumnSettingsLnk = controlActions.perform_GetElementByXPath(FormsManagerPageLocators.COLUMN_SETTINGS_LNK, 
					"COLUMNNAME", columnName);
			controlActions.action.moveToElement(ColumnSettingsLnk).build().perform();

			Sync();
			logInfo("Able to move for the page view");
			return true;
		}
		catch(Exception e) {
			logError("Failed to move for the page view - "
					+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to set filters on columns of Form Manager Location grid
	 * @author dahale_p
	 * @param columnName Use class FM_FIELDS to set name of column
	 * 30June 2021
	 * @param filterValue The value to be set for filter
	 * @return boolean Return true when filter value is set successfully
	 */
	public boolean setFilterToColumnOverviewFormLocation(String columnName, String filterValue) {
		try {

			int columnIndex = getColumnIndex(columnName);
			if(columnIndex == 0) { 
				logError("Failed to get correct column index : " + columnIndex + " for column " + columnName);
				return false;

			}
			else {
				if(searchFormMgrWithDetailsForOverviewLocationForm(columnName, filterValue)) 
					return true;
				else
					return false;
			}
		}
		catch(Exception e) {
			logError("Failed to set filter to column - " + columnName + " "
					+ e.getMessage());
			return false;
		}
	}



	/**
	 * This method is used to search Form name in Location Tab
	 * @author dahale_p
	 * @param columnName Column name whose Index you need. Use Class FM_FIELDS to pass column name.
	 * @param value The value you would like to use to search Form name in Location Tab
	 * @return boolean This returns true if Form name in Location Tab search is performed successfully
	 * 30 June 2021
	 */
	public boolean searchFormMgrWithDetailsForOverviewLocationForm(String columnName, String value) {
		String finalColumnNameXpath = null; 
		try {
			int columnIndex = getColumnIndex(columnName);
			if(columnIndex == 0) {
				logError("Failed to get correct column index : " + columnIndex + " for column " + columnName);
				return false;
			}
			else {
				finalColumnNameXpath = controlActions.perform_GetDynamicXPath(FormsManagerPageLocators.FM_OVERVIEW_FORM_LOCATION_TXT, 
						"COLUMNINDEXNO", 
						Integer.toString(columnIndex));

				if(controlActions.perform_PutText(finalColumnNameXpath,value, false)) {
					Sync();
					logInfo("Searched Location  with value : " + value + " for column : " + columnName);
					return true;
				}
			}
			return false;
		}
		catch(Exception e) {
			logError("Failed to search validation with value : " + value + " for column : " + columnName + " - "
					+ e.getMessage());
			return false;
		}	


	}

	/** This method is used to clear applied filter on Forms Manager's LOCATION Tab grid for mentioned column
	 * @author Dahale_p
	 * @30 June 2021
	 * @param columnName Use class FM_FIELDS to set name of column
	 * @return boolean return true when applied filter is cleared
	 */	
	public boolean clearAppliedFilteroOverviewLocationTab(String columnName) {
		WebElement ClearColumnFilterBtn = null; 

		try {
			int columnIndex = getColumnIndex(columnName);
			if(columnIndex == 0) {
				logError("Failed to get correct column index : " + columnIndex + " for column " + columnName);
				return false;
			}
			else {
				ClearColumnFilterBtn = controlActions.perform_GetElementByXPath(FormsManagerPageLocators.CLEAR_GRID_OVERVIEW_LOCATION_FILTER_BTN, 
						"COLUMNINDEXNO", Integer.toString(columnIndex));

				ClearColumnFilterBtn.click();
				Sync();
				logInfo("Cleared applied filter for column - " + columnName);
				return true;
			}
		}
		catch(Exception e) {
			logError("Failed to clear applied filter for column - " + columnName
					+ e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to search form
	 * @author dahale_p
	 * 7July 2021
	 * @param formname FormName of user to be added to Work Group
	 * @return boolean This returns true if user is set successfully
	 */
	public boolean searchAndSelectOverviewForm(String formname, boolean untouched) {
		try {
			if (untouched) {
				controlActions.WaitforelementToBeClickable(fmOverviewSearchFormTxt);
				controlActions.clickOnElement(fmOverviewSearchFormTxt);	
				controlActions.sendKeys(fmOverviewSearchFormTxt,formname);	
			}
			else {
				controlActions.WaitforelementToBeClickable(fmOverviewSearchFormTouchedTxt);
				controlActions.clickOnElement(fmOverviewSearchFormTouchedTxt);	
				controlActions.sendKeys(fmOverviewSearchFormTouchedTxt,formname);
			}

			logInfo("Add form name to search textbox: " + formname);
			actionEnter();
			Sync();
			logInfo("Clicked on search button");
			//WebElement formnameChk = controlActions.perform_GetElementByXPath(FormsManagerPageLocators.LINKFORMVERIFICATIONCHK, "FORMNAME", formname);
			controlActions.clickOnElement(fmOverviewFormCheckbox);		
			logInfo("Selected form: " + formname);				
			return true;
		}
		catch(Exception e) {
			logError("Failed to select form: " + formname + " - "
					+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to enabled/disabled verification 
	 * @author dahale_p
	 * @param 0
	 * @return boolean This returns boolean true after deselecting the form with verification
	 */

	public boolean performEnableDisableActionOverview(String status,String status1) {
		try {	

			controlActions.WaitforelementToBeClickable(fmOverviewStateCalloutMnu);
			controlActions.click(fmOverviewStateCalloutMnu);
			Sync();
			switch(status) {
			case FM_STATUS.ENABLED:
				if(!clickDisableBtnOverview()) {
					return false;
				}
				break;
			case FM_STATUS.DISABLED:
				if(!clickEnableBtnOverview()) {
					return false;
				}
				break;
			}
			logInfo("Clicked on Popup Yes button");
			return true;
		}
		catch(Exception e) {
			logError("Failed to click on Disable button - "
					+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to set the disable and Form by clicking on Disable button
	 * @author dahale_p
	 * @param none 
	 * @return boolean This returns true if disable button is clicked successfully
	 */
	public boolean clickDisableBtnOverview() {
		try {	
			controlActions.WaitforelementToBeClickable(fmOverviewFormStateDisabled);
			controlActions.clickOnElement(fmOverviewFormStateDisabled);
			logInfo("Clicked on Disable button");
			Sync();
			controlActions.clickOnElement(fmOverviewFormPopupDisable);
			logInfo("Clicked on Popup Yes button");
			return true;
		}
		catch(Exception e) {
			logError("Failed to click on Disable button - "
					+ e.getMessage());
			return false;
		}	
	}


	/**
	 * This method is used to set the Enable and Form by clicking on Enable button
	 * @author dahale_p
	 * @param none 
	 * @return boolean This returns true if Enable button is clicked successfully
	 */
	public boolean clickEnableBtnOverview() {
		try {	
			controlActions.WaitforelementToBeClickable(fmOverviewFormStateEnabled);
			controlActions.clickOnElement(fmOverviewFormStateEnabled);
			logInfo("Clicked on Enable button");
			Sync();
			controlActions.clickOnElement(fmOverviewFormPopupEnable);
			logInfo("Clicked on Popup Enabled button");
			return true;
		}
		catch(Exception e) {
			logError("Failed to click on Enable button - "
					+ e.getMessage());
			return false;
		}	
	}

	/** This method is used to clear applied filter on Forms Manager's LOCATION Tab grid for mentioned column
	 * @author Dahale_p
	 * @30 June 2021
	 * @param columnName Use class FM_FIELDS to set name of column
	 * @return boolean return true when applied filter is cleared
	 */	
	public boolean clearAppliedFilterOverviewFormTab(String columnName) {
		WebElement ClearColumnFilterBtn = null; 

		try {
			int columnIndex = getColumnIndex(columnName);
			if(columnIndex == 0) {
				logError("Failed to get correct column index : " + columnIndex + " for column " + columnName);
				return false;
			}
			else {
				ClearColumnFilterBtn = controlActions.perform_GetElementByXPath(FormsManagerPageLocators.FM_OVERVIEW_CLEAR_COLUMN_FILTER_BTN, 
						"COLUMNINDEXNO", Integer.toString(columnIndex));

				ClearColumnFilterBtn.click();
				Sync();
				logInfo("Cleared applied filter for column - " + columnName);
				return true;
			}
		}
		catch(Exception e) {
			logError("Failed to clear applied filter for column - " + columnName
					+ e.getMessage());
			return false;
		}
	}


	/**
	 * This method is used to click on Chart button.
	 * @author dahale_p
	 * @param none
	 * @return boolean This returns true if CHART button is clicked.
	 */
	public boolean clickChartMenu() { 
		try {
			Sync();
			controlActions.WaitforelementToBeClickable(fmOverviewChartsTab);
			Sync();
			controlActions.clickOnElement(fmOverviewChartsTab); 
			logInfo("Clicked on CHART Menu");
			Sync();
			return true;
		}
		catch(Exception e) {
			logError("Failed to click on CHART Menu "
					+ e.getMessage());
			return false;
		}		
	}


	/**
	 * This method is used to click on CHART menu button.
	 * @author dahale_p
	 * @param none
	 * @return boolean This returns true if CHART button is clicked.
	 */
	public boolean clickChartClose() { 
		try {
			controlActions.WaitforelementToBeClickable(fmOverviewChartsTab);
			Sync();
			controlActions.clickOnElement(fmOverviewChartsTab);
			Sync();
			controlActions.clickOnElement(fmChartClose);
			logInfo("Clicked on close button to Close CHART Menu");
			Sync();
			return true;
		}
		catch(Exception e) {
			logError("Failed to click close button on CHART Menu "
					+ e.getMessage());
			return false;
		}		
	}


	/**
	 * This method is used to click OK and CLEAR button on Popup menu button.
	 * @author dahale_p
	 * @param none
	 * @return boolean This returns true if CHART button is clicked.
	 */
	public boolean clickOkPopup() { 
		try {
			controlActions.WaitforelementToBeClickable(fmChartPopUpOKButton);
			Sync();
			controlActions.clickOnElement(fmChartPopUpOKButton);
			Sync();
			controlActions.WaitforelementToBeClickable(fmChartPopUpClearButton);
			Sync();
			controlActions.clickOnElement(fmChartPopUpClearButton);
			logInfo("Clicked on Ok and Clear button of Pop up CHART Menu");
			Sync();
			return true;
		}
		catch(Exception e) {
			logError("Failed to click ok and Clear button on POPUP Menu "
					+ e.getMessage());
			return false;
		}		
	}


	/**
	 * This method is used to click OK button on Popup menu button.
	 * @author dahale_p
	 * @param none
	 * @return boolean This returns true if CHART button is clicked.
	 */
	public boolean deleteChart() { 
		try {
			controlActions.WaitforelementToBeClickable(fmChartDeleteChart);
			Sync();
			controlActions.clickOnElement(fmChartDeleteChart);
			logInfo("Clicked on Delete Chart button ");
			Sync();
			return true;
		}
		catch(Exception e) {
			logError("Failed to click on Delete Chart button "
					+ e.getMessage());
			return false;
		}		
	}


	/**
	 * This method is used to search a Questionnaire form in Form Manager and navigate to Charts Tab
	 * @author choubey_a
	 * @param formname 
	 * @return boolean This returns true if form is selected and navigation to charts tab is done
	 */

	public boolean selectFormAndNavigaToChartsQuestform(String formname) {
		try {	
			Sync();
			controlActions.click(SearchFormnameInput);
			controlActions.sendKeys(SearchFormnameInput, formname);
			controlActions.actionEnter();
			Sync();
			WebElement formselect = controlActions.perform_GetElementByXPath(FormsManagerPageLocators.SELECT_FORM, "FORMNAME",formname);
			controlActions.doubleClick(formselect);
			Sync();
			logInfo("Selected a Quesetionnire form and to view the charts tab");
			return true;
		}
		catch(Exception e) {
			logError("Failed to select form and navigation to charts tab "
					+ e.getMessage());
			return false;
		}	
	}


	/**
	 * This method is used to click on Close the form with Close button.
	 * @author dahale_p
	 * @param none
	 * @return boolean This returns true if CHART button is clicked.
	 */
	public boolean clickOverviewFormClose() { 
		try {
			controlActions.WaitforelementToBeClickable(fmOverviewFormCloseButton);
			Sync();
			controlActions.clickOnElement(fmOverviewFormCloseButton);
			Sync();

			logInfo("Clicked on close button to Close Form ");
			Sync();
			return true;
		}
		catch(Exception e) {
			logError("Failed to click close button to close form "
					+ e.getMessage());
			return false;
		}		
	}

	/**
	 * This method is used to select resource for the chart
	 * @author dahale_p
	 * @return boolean This returns true when the resource is selected for chart
	 */

	public boolean selectResourceTabforCharts() {
		try {
			WebElement SubTabOption = controlActions.perform_GetElementByXPath(FormsManagerPageLocators.VALUE_SELECT,"VALUE",SubTabs.RESOURCES);
			controlActions.clickOnElement(SubTabOption);
			Sync();	
			logInfo("Resource Tab selected for chart");
			return true;			
		}catch(Exception e) {
			logError("Resource Tab  not selected for charts");
			return false;
		}
	}


	/** NEW CREATION
	 * This method is used to select resource for the chart
	 * @author dahale_p
	 * @return boolean This returns true when the resource is selected for chart
	 */

	public boolean selectResourceTabforResourcesCharts() {
		try {
			WebElement SubTabOption = controlActions.perform_GetElementByXPath(FormsManagerPageLocators.VALUE_SELECT,"VALUE",SubTabs.RESOURCES);
			controlActions.clickOnElement(SubTabOption);
			Sync();	
			logInfo("Resource Tab selected for chart");
			return true;			
		}catch(Exception e) {
			logError("Resource Tab  not selected for charts");
			return false;
		}
	}

	/**
	 * This method is used to check all the Associated Location to the form.
	 * @author dahale_p
	 * @param none
	 * @return boolean This returns true if CHART button is clicked.
	 */
	public boolean clickResourceTabLocation() { 
		try {
			controlActions.WaitforelementToBeClickable(fmChartResouceLocationDdl);
			Sync();
			controlActions.clickOnElement(fmChartResouceLocationDdl);
			Sync();

			logInfo("Clicked on Location dropdown to check associated location ");
			Sync();
			return true;
		}
		catch(Exception e) {
			logError("Failed to click Location dropdown "
					+ e.getMessage());
			return false;
		}		
	}


	/**
	 * This method is used to create associated chart 
	 * @author choubey_a
	 * @param formname
	 * @return boolean This returns true when the associated chart will be created
	 */


	public boolean createAssociateChart(String chart) {
		try {

			controlActions.clickOnElement(AssociatedBtn);
			Thread.sleep(3000);
			String selector = "kendo-dropdownlist[formcontrolname='ChartTemplateType'] > span";
			Thread.sleep(5000);
			String chartselect = controlActions.perform_GetDynamicXPath(FormsManagerPageLocators.VALUE_SELECT, "VALUE", chart);
			controlActions.setKendoDropDownValue(driver,selector,chartselect);
			Thread.sleep(3000);
			controlActions.clickOnElement(ChartNameInput);
			controlActions.actionSendKeys(ChartNameInput, chart);
			Thread.sleep(3000);
			controlActions.clickOnElement(AssociateChartSaveBtn);
			Sync();	
			logInfo("Associated Chart created with chart type - " +chart );
			return true;
		}
		catch(Exception e) {
			logError("Failed to create associated chart with chart type - " +chart
					+ e.getMessage());
			return false;
		}	


	}

	/**
	 * This method is used to select Field Tab for the chart
	 * @author dahale_p
	 * @return boolean This returns true when the Field tab is selected for chart
	 */

	public boolean selectFieldTabforCharts() {
		try {
			WebElement SubTabOption = controlActions.perform_GetElementByXPath(FormsManagerPageLocators.VALUE_SELECT,"VALUE",SubTabs.FIELDS);
			controlActions.clickOnElement(SubTabOption);
			Sync();	
			logInfo("Field Tab selected for chart");
			return true;			
		}catch(Exception e) {
			logError("Field Tab  not selected for charts");
			return false;
		}
	}


	/**
	 * This method is used to Select and SAVE field value to the form.
	 * @author dahale_p
	 * @param none
	 * @return boolean This returns true if SAVE button is clicked.
	 */
	public boolean selectFieldValue(String option) { 
		try {
			 int a =1;
			WebElement chartselect1 = null; //To click any element
			controlActions.clickElement(fmChartsFieldDdl);  

			Sync();
			chartselect1 = controlActions.perform_GetElementByXPath(FormsManagerPageLocators.FM_CHARTS_FIELD_VALUE, "VALUE", option);
			Sync();
			controlActions.WaitforelementToBeClickable(chartselect1);
			Sync();
			controlActions.clickOnElement(chartselect1);

			Sync();

			logInfo("Clicked on field Value to apply single input field ");
			Sync();
			controlActions.WaitforelementToBeClickable(fmFieldValueSave);
			Sync();
			controlActions.clickOnElement(fmFieldValueSave);

			return true;
		}
		catch(Exception e) {
			logError("Failed to select field value from dropdown"
					+ e.getMessage());
			return false;
		}		
	}




	/**
	 * This method is used to check all the Associated Location to the form.
	 * @author dahale_p
	 * @param none
	 * @return boolean This returns true if CHART button is clicked.
	 */
	public boolean fieldClickCheckBoxdandSave() { 
		try {
			controlActions.WaitforelementToBeClickable(fmChartFieldCheckbox);
			Sync();
			controlActions.clickOnElement(fmChartFieldCheckbox);
			Sync();

			logInfo("Clicked on checkbox for field in : ");
			controlActions.WaitforelementToBeClickable(fmFieldValueSave);
			Sync();
			controlActions.clickOnElement(fmFieldValueSave);
			Sync();
			//				controlActions.WaitforelementToBeClickable(fmChartFieldClickOkPopUp);
			//				Sync();
			//				controlActions.clickOnElement(fmChartFieldClickOkPopUp);
			return true;
		}
		catch(Exception e) {
			logError("Failed to click Location dropdown "
					+ e.getMessage());
			return false;
		}		
	}

	/**
	 * This method is used to check all the Associated Location to the form.
	 * @author dahale_p
	 * @param none
	 * @return boolean This returns true if CHART button is clicked.
	 */
	public boolean fieldClickOkButtonCheckValidation() { 
		try {

			controlActions.WaitforelementToBeClickable(fmChartFieldClickOkPopUp);
			Sync();
			controlActions.clickOnElement(fmChartFieldClickOkPopUp);
			return true;
		}
		catch(Exception e) {
			logError("Failed to click Location dropdown "
					+ e.getMessage());
			return false;
		}		
	}

	/**
	 * This method is used to check all the Associated Location to the form.
	 * @author dahale_p
	 * @param none
	 * @return boolean This returns true if CHART button is clicked.
	 */
	public boolean fieldClickClearButton() { 
		try {

			controlActions.WaitforelementToBeClickable(fmChartFieldClickClearButton);
			Sync();
			controlActions.clickOnElement(fmChartFieldClickClearButton);
			return true;
		}
		catch(Exception e) {
			logError("Failed to click Location dropdown "
					+ e.getMessage());
			return false;
		}		
	}



	/**
	 * This method is used to check all the Associated Location to the form.
	 * @author dahale_p
	 * @param none
	 * @return boolean This returns true if CHART button is clicked.
	 */
	public boolean clickFieldPopupforMinimum1Field() { 
		try {
			controlActions.WaitforelementToBeClickable(fmChartFieldCheckbox); 
			Sync();
			controlActions.clickOnElement(fmChartFieldCheckbox);
			Sync();

			logInfo("Clicked on checkbox for field : ");
			controlActions.WaitforelementToBeClickable(fmFieldValueSave);
			Sync();
			controlActions.clickOnElement(fmFieldValueSave);
			Sync();
			controlActions.WaitforelementToBeClickable(fmChartFieldCheckbox);
			Sync();
			controlActions.clickOnElement(fmChartFieldCheckbox);
			Sync();
			controlActions.WaitforelementToBeClickable(fmFieldValueSave);
			Sync();
			controlActions.clickOnElement(fmFieldValueSave);
			Sync();
			controlActions.WaitforelementToBeClickable(fmChartFieldClickOkPopUp);
			Sync();
			controlActions.clickOnElement(fmChartFieldClickOkPopUp);
			return true;
		}
		catch(Exception e) {
			logError("Failed to click field dropdown "
					+ e.getMessage());
			return false;
		}	


	}

	/**
	 * This method is used to Select and SAVE field value to the form.
	 * @author dahale_p
	 * @param none
	 * @return boolean This returns true if SAVE button is clicked.
	 */
	public boolean fieldTabClose() { 
		try {
			Sync();
			controlActions.WaitforelementToBeClickable(fmFieldCloseButton);
			Sync();
			controlActions.clickOnElement(fmFieldCloseButton);
			Sync();
			return true;
		}
		catch(Exception e) {
			logError("Failed to click field dropdown "
					+ e.getMessage());
			return false;
		}					

	}

	public boolean increamentMean(int mean, String resourceName) {
		WebElement FMChartMeanUpArrow = null;


		try {
			Sync();

			FMChartMeanUpArrow = controlActions.perform_GetElementByXPath(FormsManagerPageLocators.FM_CHART_MEAN_UP_ARROW, 
					"RESOURCE_NAME", resourceName);

			Sync();	
			//set every day value by clicking on upp arrow
			logInfo("Clicking Up arrow to set Mean value equal to "+ mean );
			for(int i=1;i<mean;i++)
			{
				controlActions.click(FMChartMeanUpArrow);
			}

			controlActions.clickOnElement(FMResourceTabSaveBtn);
			Sync();
			logInfo("Mean Has been Incremented");
			return true;
		} catch (Exception e) {
			logError("Failed to set mean - " + e.getMessage());
			return false;
		}
	}

	public boolean increamentVariance(int variance,String resourceName) {
		WebElement FMChartVarianceUpArrow = null;
		try {
			FMChartVarianceUpArrow = controlActions.perform_GetElementByXPath(FormsManagerPageLocators.FM_CHART_VARIANCE_UP_ARROW, 
					"RESOURCE_NAME", resourceName);
			Sync();	

			logInfo("Clicking Up arrow to set Variance value equal to "+ variance );
			for(int i=1;i<variance;i++)
			{
				controlActions.click(FMChartVarianceUpArrow);
			}

			controlActions.clickOnElement(FMResourceTabSaveBtn);
			Sync();
			logInfo("Variance Has been Incremented");
			return true;
		} catch (Exception e) {
			logError("Failed to set mean - " + e.getMessage());
			return false;
		}

	}



	/**
	 * This method is used to set Mean and Variance increment value.
	 * @author dahale_p
	 * @param none
	 * @return boolean This returns true if set Mean and Variance increment value.
	 */

	public boolean increamentMeanvariance(int mean,int variance) {
		//WebElement mean= null;
		try {
			controlActions.WaitforelementToBeClickable(FMChartSelectResource);
			Sync();
			controlActions.clickOnElement(FMChartSelectResource);
			Sync();
			controlActions.WaitforelementToBeClickable(FMChartsMeanUpArrow);
			Sync();	

			logInfo("Clicking Up arrow to set Mean value equal to "+ mean );
			for(int i=1;i<mean;i++)
			{
				controlActions.click(FMChartsMeanUpArrow);
			}

			controlActions.WaitforelementToBeClickable(FMChartsVarianceUpArrow);
			Sync();	

			logInfo("Clicking Up arrow to set Variance value equal to "+ variance );
			for(int i=1;i<variance;i++)
			{
				controlActions.click(FMChartsVarianceUpArrow);
			}

			controlActions.clickOnElement(SaveBtn);
			Sync();
			logInfo("Mean Has been Incremented");
			return true;
		} catch (Exception e) {
			logError("Failed to set mean - " + e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to search a form in Form Manager and navigate to Charts Tab
	 * @author dahale_p
	 * @param formname 
	 * @return boolean This returns true if form is selected and navigation to charts tab is done
	 */

	public boolean selectFormAndToCheckCharts(String formname) {
		try {	
			Sync();
			controlActions.click(SearchFormnameInput);
			controlActions.sendKeys(SearchFormnameInput, formname);
			controlActions.actionEnter();
			Sync();
			WebElement formselect = controlActions.perform_GetElementByXPath(FormsManagerPageLocators.SELECT_FORM, "FORMNAME",formname);
			controlActions.doubleClick(formselect);
			Sync();
			logInfo("Selected a form  and check charts tab");
			return true;
		}
		catch(Exception e) {
			logError("Failed to select form and to check charts tab "
					+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to click on Close the form with Close button.
	 * @author dahale_p
	 * @param none
	 * @return boolean This returns true if CHART button is clicked.
	 */
	public boolean clickResourceTabFormClose() { 
		try {
			controlActions.WaitforelementToBeClickable(fmResourceTabFormCloseButton);
			Sync();
			controlActions.clickOnElement(fmResourceTabFormCloseButton);
			Sync();

			logInfo("Clicked on close button to Close Form ");
			Sync();
			return true;
		}
		catch(Exception e) {
			logError("Failed to click close button to close form "
					+ e.getMessage());
			return false;
		}		
	}

	/**
	 * This method is used to set Mean and Variance increment value.
	 * @author dahale_p
	 * @param none
	 * @return boolean This returns true if set Default Mean increment value.
	 */

	public boolean increamentDefaultValMean(int mean) {
		//WebElement FMChartMeanUpArrow = null;


		try {
			Sync();
			controlActions.WaitforelementToBeClickable(fmChartResourcesDefaultMeanUpArrow);
			Sync();
			controlActions.clickOnElement(fmChartResourcesDefaultMeanUpArrow);

			Sync();	
			//set every day value by clicking on up arrow
			logInfo("Clicking Default Up arrow to set Mean value equal to "+ mean );
			for(int i=1;i<mean;i++)
			{
				controlActions.click(fmChartResourcesDefaultMeanUpArrow);
			}

			controlActions.clickOnElement(FMResourceTabSaveBtn);
			Sync();
			logInfo("Default Mean has been Incremented");
			return true;
		} catch (Exception e) {
			logError("Failed to set default mean - " + e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to set Default Variance increment value.
	 * @author dahale_p
	 * @param none
	 * @return boolean This returns true if set Default Variance increment value.
	 */


	public boolean increamentDefaultValVariance(int variance) {
		//WebElement FMChartVarianceUpArrow = null;


		try {
			Sync();
			controlActions.WaitforelementToBeClickable(fmChartResourcesDefaultVarianceUpArrow);
			Sync();
			controlActions.clickOnElement(fmChartResourcesDefaultVarianceUpArrow);

			Sync();	
			//set every day value by clicking on up arrow
			logInfo("Clicking Default Up arrow to set Mean value equal to "+ variance );
			for(int i=1;i<variance;i++)
			{
				controlActions.click(fmChartResourcesDefaultVarianceUpArrow);
			}

			controlActions.clickOnElement(FMResourceTabSaveBtn);
			Sync();
			logInfo("Default Mean has been Incremented");
			return true;
		} catch (Exception e) {
			logError("Failed to set default mean - " + e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to set Default check box for mean and variance value for resources.
	 * @author dahale_p
	 * @param none
	 * @return boolean This returns true if set Default check box is checked for resources.
	 */

	public boolean clickDefaultCheckboxWithPopup() {

		try {
			Sync();
			controlActions.WaitforelementToBeClickable(fmChartResourcesDefaultCheckbox);
			Sync();
			controlActions.clickOnElement(fmChartResourcesDefaultCheckbox);
			Sync();	
			controlActions.WaitforelementToBeClickable(fmChartResourcesDefaultChecKPopUp);
			Sync();
			controlActions.clickOnElement(fmChartResourcesDefaultChecKPopUp);
			logInfo("Clicking on OK button of Default Pop up" );

			controlActions.clickOnElement(FMResourceTabSaveBtn);
			Sync();
			logInfo("Default Values of Mean and Variance has been set");
			return true;
		} catch (Exception e) {
			logError("Failed to Default Values of Mean and Variance - " + e.getMessage());
			return false;
		}
	}


	/**
	 * This method is used to enabled/disabled verification 
	 * @author dahale_p
	 * @param 0
	 * @return boolean This returns boolean true after deselecting the form with verification
	 */

	public boolean performDisableFormOverview() {
		try {	

			controlActions.WaitforelementToBeClickable(fmOverviewStateCalloutMnu);
			controlActions.click(fmOverviewStateCalloutMnu);
			Sync();
			if(clickDisableBtnOverview()) {

				logInfo("Form gets disabled successfully");
				return true;
			}
			logInfo("Form not get disabled");
			return false;

		}
		catch(Exception e) {
			logError("Failed to click on Disable option - "
					+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to enabled/disabled verification 
	 * @author dahale_p
	 * @param 0
	 * * date 21 Oct 2021
	 * @return boolean This returns boolean true after deselecting the form with verification
	 */

	public boolean performEnableFormOverview() {
		try {	

			controlActions.WaitforelementToBeClickable(fmOverviewStateCalloutMnu);
			controlActions.click(fmOverviewStateCalloutMnu);
			Sync();
			if(clickEnableBtnOverview()) {

				logInfo("Form gets enabled successfully");
				return true;
			}
			logInfo("Form not get enabled");
			return false;

		}
		catch(Exception e) {
			logError("Failed to click on Enable option - "
					+ e.getMessage());
			return false;
		}	
	}


	/**
	 * This method is used to check all the Associated Location to the form.
	 * @author dahale_p
	 * @param none
	 * @return boolean This returns true if CHART button is clicked.
	 */
	public boolean clickBulkFormCheckBox() { 
		try {
			controlActions.WaitforelementToBeClickable(fmOverviewSelectBulkFormCheckbox);
			Sync();
			controlActions.clickOnElement(fmOverviewSelectBulkFormCheckbox);
			Sync();

			logInfo("Clicked on checkbox for field in : ");
			Sync();
			//					controlActions.clickOnElement(fmChartFieldClickOkPopUp);
			return true;
		}
		catch(Exception e) {
			logError("Failed to click Location dropdown "
					+ e.getMessage());
			return false;
		}		
	}


	/**
	 * This method is used to search form
	 * @author dahale_p
	 * 7July 2021
	 * @param formname FormName of user to be added to Work Group
	 * @return boolean This returns true if user is set successfully
	 */
	public boolean searchOverviewForm(String formname, boolean untouched) {
		try {
			if (untouched) {
				controlActions.WaitforelementToBeClickable(fmOverviewSearchFormTxt);
				controlActions.clickOnElement(fmOverviewSearchFormTxt);	
				controlActions.sendKeys(fmOverviewSearchFormTxt,formname);	
			}
			else {
				controlActions.WaitforelementToBeClickable(fmOverviewSearchFormTouchedTxt);
				controlActions.clickOnElement(fmOverviewSearchFormTouchedTxt);	
				controlActions.sendKeys(fmOverviewSearchFormTouchedTxt,formname);
			}

			logInfo("Add form name to search textbox: " + formname);
			actionEnter();
			Sync();
			logInfo("Clicked on search button");
			//WebElement formnameChk = controlActions.perform_GetElementByXPath(FormsManagerPageLocators.LINKFORMVERIFICATIONCHK, "FORMNAME", formname);
			//controlActions.clickOnElement(fmOverviewFormCheckbox);		
			logInfo("Selected form: " + formname);				
			return true;
		}
		catch(Exception e) {
			logError("Failed to select form: " + formname + " - "
					+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to search form
	 * @author dahale_p
	 * 28 Oct 2021
	 * @param verificationNameUpdated for updated verification
	 * @return boolean This returns true if verification is updated successfully
	 */

	public boolean chkUpdatedVerificationwithForm(String verificationNameUpdated) {
		try {		
			if(!clickVerificationBtn()) {
				return false;
			}
			if(!selectVerification(verificationNameUpdated)) {
				return false;
			}

			logInfo("Clicked the updated Verification name");
			return true;
		}
		catch(Exception e) {
			logError("Failed to click on updated Verification name - "
					+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to click on Overview > Verifications Tab
	 * @author hingorani_a
	 * @return boolean This returns true if Overview > Verifications Tab is clicked.
	 */
	public boolean clickVerificationTabViaOverview() { 
		try {
			controlActions.WaitforelementToBeClickable(FmOverviewVerificationTab);
			controlActions.clickOnElement(FmOverviewVerificationTab); 
			Sync();
			logInfo("Clicked on Overview > Verifications Tab");
			return true;
		}
		catch(Exception e) {
			logError("Failed to click on Overview > Verifications Tab "
					+ e.getMessage());
			return false;
		}		
	}

	/** FTU Functions - Start */
	
	/**
	 * This method is used to click on Import tab
	 * @author hingorani_a
	 * @return boolean This returns true if Import tab is clicked.
	 */
	public boolean clickImportTab() { 
		try {
			controlActions.WaitforelementToBeClickable(FmImportTab);
			FmImportTab.click();
			Sync();
			logInfo("Clicked on Import tab");
			return true;
		}
		catch(Exception e) {
			logError("Failed to click on Import tab " + e.getMessage());
			return false;
		}		
	}
	
	/**
	 * This method is used to set source env for FTU [Form Transfer Utility]
	 * @author hingorani_a
	 * @param sourceEnvName Use Class FTU_SOURCE_ENVS to pass source env name to be set
	 * @return boolean This returns true if source env is set successfully
	 */
	public boolean setSourceEnvForFTU(String sourceEnvName) {
		String selector, finalXpath = null;
		try {
			
			selector = "kendo-dropdownlist[class='scs-source-dropdown k-widget k-dropdown k-header'] > span";
			finalXpath = controlActions.perform_GetDynamicXPath(FormsManagerPageLocators.IMPORT_SOURCE_ENV_OPTN, 
					"SOURCE_ENV", sourceEnvName);
			
			if(!controlActions.setKendoDropDownValue(driver, selector, finalXpath)) 
				return false;
			
			Sync();
			logInfo("Source environment for FTU set to - " + sourceEnvName);
			return true;
		}
		catch(Exception e) {
			logError("Failed to set Source environment for FTU as - " + sourceEnvName
					+ e.getMessage());
			return false;
		}	
	}
	
	/**
	 * This method is used to find Column Index for the column present in Validations Grid
	 * @author hingorani_a
	 * @param columnName Column name whose Index you need. Use Class VLDTN_FIELDS to pass column name.
	 * This Index is in turn used in creating dynamic xpaths. 
	 * @return int This returns value of column index. 
	 * Returns 0, if column is not found
	 */
	public int getColumnIndexForFTU(String columnName) {
		int columnIndex = 0; 
		try {
			for(WebElement column : FTUColumnNamesTbl) {
				if(controlActions.perform_CheckIfElementTextContains(column, columnName)) 
				{
					columnIndex++;
					break;
				}
				else if (controlActions.perform_CheckIfElementTextEquals(column, "")) {
					//Do nothing
				}
				else {
					columnIndex++;
				}
			}		
			logInfo("For column : " + columnName + " the index is : " + columnIndex);
			return columnIndex;
		}
		catch(Exception e) {
			logError("Failed to get index value for column : " + columnName + " - "
					+ e.getMessage());
			return columnIndex;
		}	
	}
	
	/**
	 * This method is used to search details for FTU having text input
	 * @author hingorani_a
	 * @param columnName Column name whose Index you need. Use Class FM_FTU_FIELDS to pass column name.
	 * @param value The value you would like to use to search Form 
	 * @return boolean This returns true if search is performed successfully
	 */
	public boolean searchFormWithDetails(String columnName, String value) {
		String finalColumnNameXpath = null; 
		try {
			int columnIndex = getColumnIndex(columnName);
			if(columnIndex == 0) {
				logError("Failed to get correct column index : " + columnIndex + " for column " + columnName);
				return false;
			}
			else {
				finalColumnNameXpath = controlActions.perform_GetDynamicXPath(FormsManagerPageLocators.FTU_COLUMN_NAMES_TXT, 
						"COLUMNINDEXNO", 
						Integer.toString(columnIndex));

				if(controlActions.perform_PutText(finalColumnNameXpath,value, true)) {
					Sync();
					logInfo("Searched with value : " + value + " for column : " + columnName);
					return true;
				}
			}
			return false;
		}
		catch(Exception e) {
			logError("Failed to Search with value : " + value + " for column : " + columnName + " - "
					+ e.getMessage());
			return false;
		}	
	}
	
	/**
	 * This method is used to search and select the form in grid
	 * @author hingorani_a
	 * @param formName The name of the form you need to select 
	 * @return boolean This returns true if form is selected successfully
	 */
	public boolean searchAndSelectFormInFTU(String formName) {
		try {
			if(!searchFormWithDetails(FM_FTU_FIELDS.FORM, formName))
				return false;
			
			controlActions.WaitforelementToBeClickable(FTUFirstFormChk);
			FTUFirstFormChk.click();
			logInfo("Selected the form - " + formName);
			return true;
		}
		catch(Exception e) {
			logError("Failed to Select form : " + formName + " - "
					+ e.getMessage());
			return false;
		}	
	}
	
	/**
	 * This method is used to search and select the forms in grid
	 * @author hingorani_a
	 * @param formNames The name of the forms you need to select 
	 * @return boolean This returns true if forms is selected successfully
	 */
	public boolean searchAndSelectFormInFTU(List<String> formNames) {
		try {
			for(String formName : formNames) {
				if(!searchFormWithDetails(FM_FTU_FIELDS.FORM, formName))
					return false;
				
				controlActions.WaitforelementToBeClickable(FTUFirstFormChk);
				FTUFirstFormChk.click();
				logInfo("Selected the form - " + formName);
			}
			return true;
		}
		catch(Exception e) {
			logError("Failed to Select forms : " + formNames + " - "
					+ e.getMessage());
			return false;
		}	
	}
	
	/**
	 * This method is used to click on 'Select for import' button
	 * @author hingorani_a
	 * @return boolean This returns true if 'Select for import' button is clicked.
	 */
	public boolean clickSelectForImportBtn() { 
		try {
			controlActions.WaitforelementToBeClickable(FTUImportBtn);
			FTUImportBtn.click();
			Sync();
			logInfo("Clicked on 'Select for import' button");
			return true;
		}
		catch(Exception e) {
			logError("Failed to click on 'Select for import' button " + e.getMessage());
			return false;
		}		
	}
	
	/**
	 * This method is used to select the Import option for FTU 
	 * @author hingorani_a
	 * @param option Use Class FTU_IMPORT_OPTNS to pass Import option to be selected
	 * @return boolean This returns true if Import option is selected successfully
	 */
	public boolean selectImportOptionForFTU(String option) {
		WebElement FTUImportOptn = null;
		try {
			
			FTUImportOptn = controlActions.perform_GetElementByXPath(FormsManagerPageLocators.FTU_IMPORT_OPTN, 
					"IMPORT_OPTION", option);
			controlActions.WaitforelementToBeClickable(FTUImportOptn);
			FTUImportOptn.click();
			logInfo("Selected the Import option as - " + option);
			return true;
		}
		catch(Exception e) {
			logError("Failed to Select Import option as : " + option + " - "
					+ e.getMessage());
			return false;
		}	
	}
	
	/**
	 * This method is used to select the Import options for FTU 
	 * @author hingorani_a
	 * @param options Use Class FTU_IMPORT_OPTNS to pass Import options to be selected
	 * @return boolean This returns true if Import options are selected successfully
	 */
	public boolean selectImportOptionForFTU(List<String> options) {
		try {
			for(String option : options) {
				if(!selectImportOptionForFTU(option))
					return false;
			}
			logInfo("Selected Import options as - " + options);
			return true;
		}
		catch(Exception e) {
			logError("Failed to Select Import options as : " + options + " - "
					+ e.getMessage());
			return false;
		}	
	}
	
	/**
	 * This method is used to click on Import button on FTU popup
	 * @author hingorani_a
	 * @return boolean This returns true if Import button on FTU popup is clicked.
	 */
	public boolean clickImportBtnOnPopup() { 
		try {
			controlActions.WaitforelementToBeClickable(FTUPopupImportBtn);
			FTUPopupImportBtn.click();
			Sync();
			logInfo("Clicked on Import button on FTU popup");
			return true;
		}
		catch(Exception e) {
			logError("Failed to click on Import button on FTU popup " + e.getMessage());
			return false;
		}		
	}
	
	/**
	 * This method is used to click on Close button on FTU popup
	 * @author hingorani_a
	 * @return boolean This returns true if Close button on FTU popup is clicked.
	 */
	public boolean clickCloseBtnOnPopup() { 
		try {
			controlActions.WaitforelementToBeClickable(FTUPopupCloseBtn);
			FTUPopupCloseBtn.click();
			Sync();
			logInfo("Clicked on Close button on FTU popup");
			return true;
		}
		catch(Exception e) {
			logError("Failed to click on Close button on FTU popup " + e.getMessage());
			return false;
		}		
	}
	
	/**
	 * This method is used to Import forms and Close FTU popup
	 * @author hingorani_a
	 * @return boolean This returns true if Import action is performed successfully
	 */
	public boolean importFormsAndCloseFTUPopup() { 
		try {
			if(!clickImportBtnOnPopup())
				return false;
			
			if(!clickCloseBtnOnPopup())
				return false;
			
			return true;
		}
		catch(Exception e) {
			logError("Failed to Perform Import of Forms for FTU " + e.getMessage());
			return false;
		}		
	}
	
	/**
	 * This method is used to click on View Import History link
	 * @author hingorani_a
	 * @return boolean This returns true if View Import History link is clicked.
	 */
	public boolean clickViewImportHistory() { 
		try {
			controlActions.WaitforelementToBeClickable(FTUViewImportHistLnk);
			FTUViewImportHistLnk.click();
			Sync();
			logInfo("Clicked on View Import History link");
			return true;
		}
		catch(Exception e) {
			logError("Failed to click on View Import History link " + e.getMessage());
			return false;
		}		
	}
	
	/**
	 * This method is used to set source environment for FTU as per the other API
	 * env set in config.properties file
	 * @author hingorani_a
	 * @return String This returns value of the Source env to be set
	 */
	public String decideSourceEnvToBeUsedForFTU() { 
		String sourceEnv = null;
		try {
			if(otherBaseURI.contains("test2.stage")) {
				sourceEnv = FTU_SOURCE_ENVS.TEST2_STAGE;
			}
			else if(otherBaseURI.contains("test2.safetychain")) {
				sourceEnv = FTU_SOURCE_ENVS.TEST2_LIVE;
			}
			else if(otherBaseURI.contains("test1.stage")) {
				sourceEnv = FTU_SOURCE_ENVS.TEST1_STAGE;
			}
			else if(otherBaseURI.contains("test1.safetychain")) {
				sourceEnv = FTU_SOURCE_ENVS.TEST1_LIVE;
			}

			logInfo("Source Enviromnent for FTU is set as - " +  sourceEnv);
		}
		catch(Exception e) {
			logError("Failed to Set Source Enviromnent for FTU " + e.getMessage());
		}		
		return sourceEnv;
	}
	
	/**
	 * This method is used to get source environment for FTU as per the other API 
	 * for History details verification as per it is set in config.properties file
	 * @author hingorani_a
	 * @return String This returns value of the Source env to be set
	 */
	public String getSourceEnvHistToBeUsedForFTU() { 
		String sourceEnvHist = null;
		try {
			if(otherBaseURI.contains("test2.stage")) {
				sourceEnvHist = FTU_SOURCE_ENVS.TEST2_STAGE_HIST;
			}
			else if(otherBaseURI.contains("test2.safetychain")) {
				sourceEnvHist = FTU_SOURCE_ENVS.TEST2_LIVE_HIST;
			}
			else if(otherBaseURI.contains("test1.stage")) {
				sourceEnvHist = FTU_SOURCE_ENVS.TEST1_STAGE_HIST;
			}
			else if(otherBaseURI.contains("test1.safetychain")) {
				sourceEnvHist = FTU_SOURCE_ENVS.TEST1_LIVE_HIST;
			}

			logInfo("Source Enviromnent for FTU is - " +  sourceEnvHist);
		}
		catch(Exception e) {
			logError("Failed to Get Source Enviromnent for FTU " + e.getMessage());
		}		
		return sourceEnvHist;
	}
	
	/** This method is used to update excel with the new file names of the forms to be imported
	 * @author hingorani_a
	 * @param excelPath Path to the excel to be updated
	 * @param sheetName Name of the sheet to be updated
	 * @param fileNames Key is the TC ID and Value is the new form name to be updated
	 * @return boolean This returns as true if the excel file is written properly
	 */	
	public boolean updateFormNameForSheet(String excelPath, String sheetName, HashMap<String,String> fileNames) {
		InputStream fis;
		Workbook wb = null;
		Sheet sheet = null;
		Row row = null;
		String testCaseID = null, newFileName = null;
		int expectedCount = 0;
		
		try {
			fis = new FileInputStream(excelPath);
			wb = new XSSFWorkbook(fis);  

			sheet = wb.getSheet(sheetName);	

			int size = fileNames.size()+1;
			
			for(Map.Entry<String, String> entry : fileNames.entrySet()) {
				testCaseID = entry.getKey();
				newFileName = entry.getValue();
				
				for(int i = 1; i < size; i++) {
					row  = sheet.getRow(i);
					int tID = (int) row.getCell(0).getNumericCellValue();
					if(tID == Integer.parseInt(testCaseID)) {
						expectedCount++;
						row.getCell(1).setCellValue(newFileName);
						logInfo("Updated excel with Filename : " + newFileName);
					}
				}
			}

			FileOutputStream fileOut;
			fileOut = new FileOutputStream(excelPath);
			wb.write(fileOut); 
			fileOut.close(); 
			
			if(expectedCount == fileNames.size())
				return true;
			else
				return false;
		}
		catch(Exception e) {
			logError("Failed to update excel with new form names - " + e.getMessage());
			return false;
		}
	}
	
	/** FTU Functions - END */
	
	public static class FTU_SOURCE_ENVS {
		public static final String TEST2_STAGE = "test2 - stage";
		public static final String TEST2_STAGE_HIST = "test2.stage";
		public static final String TEST2_LIVE = "test2 - live";
		public static final String TEST2_LIVE_HIST = "test2.live";
		public static final String TEST1_STAGE = "test1 - stage";
		public static final String TEST1_STAGE_HIST = "test1.stage";
		public static final String TEST1_LIVE = "test1 - live";
		public static final String TEST1_LIVE_HIST = "test1.live";

	}
	
	public static class FM_FTU_FIELDS {
		public static final String FORM = "Form";
		public static final String TYPE = "Type";	
		public static final String VERSION = "Version";
		public static final String LAST_MODIFIED = "Last Modified";
		public static final String MODIFIED_BY = "Modified By";

	}
	
	public static class FTU_IMPORT_OPTNS {
		public static final String IMPORT_EXISTING_FORMS = "Import Existing Forms";
		public static final String IMPORT_MISSING_RESOURCES = "Import Missing Resources";	
		public static final String SYNCHRONIZE_MISMATCHED_RESOURCES = "Synchronize Mis-Matched Resources";
		
	}

	public static class FM_FIELDS{
		public static final String STATUS = "Status";
		public static final String FORMNAME = "Form Name";
		public static final String FORMTYPE = "Form Type";	
		public static final String MODIFIEDBY = "Modified By";	
		public static final String MODIFIEDON = "Modified On";	
		public static final String VERSION = "Version";	
		public static final String FORM = "Form";	
		public static final String TYPE = "Type";	
		public static final String DRAFT = "Draft";
		public static final String LASTMODIFIED = "Last Modified";
		public static final String LOCATION = "Location";
		public static final String OVERVIEW = "Overview";

	}

	public static class FM_FORMTYPE{
		public static final String CHECK = "Check";
		public static final String AUDIT = "Audit";
		public static final String SELECTITEM = "Select item...";
	}

	public static class FM_STATUS{
		public static final String ENABLED = "Enabled";
		public static final String DISABLED = "Disabled";
	}

	public static class PGN_SIZE{
		public static final String SIZE_40 = "40";
		public static final String SIZE_50 = "50";
	}

	public static class COLUMN_SETTING{
		public static final String SORTASCENDING = "Sort Ascending";
		public static final String SORTDESCENDING = "Sort Descending";
		public static final String COLUMNS = "Columns";
		public static final String CHECK = "check";
		public static final String UNCHECK = "uncheck";
	}	

	public static class SubTabs {
		public static final String FIELDS = "Fields";
		public static final String FILTERS ="Filters";
		public static final String RESOURCES = "Resources";
	}

}
