package com.project.safetychain.webapp.pageobjects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.project.utilities.CommonMethods;
import com.project.utilities.ControlActions;
import com.project.utilities.DateTime;
import com.project.utilities.DateTime.ComparisonValue;
import com.project.utilities.enums.WaitType;

public class FSQABrowserRecordsPage extends CommonPage{
	
	WebDriver driver;
	WebDriverWait wait;
	ControlActions controlActions;
	 
	public FSQABrowserRecordsPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 40);
		controlActions = new ControlActions(driver);	
	}	
	
	/**
	* Page Objects
	*/
	
	@FindBy(id = FSQABrowserRecordsPageLocators.SIGNOFF_LNK)
	public WebElement SignoffLnk;
	
	@FindBy(id = FSQABrowserRecordsPageLocators.SIGNOFF_BTN)
	public WebElement SignoffBtn;
	
	@FindBy(id = FSQABrowserRecordsPageLocators.SEARCH_TXT)
	public WebElement SearchTxt;
	
	@FindBy(id = FSQABrowserRecordsPageLocators.SEARCH_BTN)
	public WebElement SearchBtn;
	
	@FindBy(id = FSQABrowserRecordsPageLocators.VOID_CMMT_TXA)
	public WebElement VoidCmmtTxa;
	
	@FindBy(id = FSQABrowserRecordsPageLocators.SUBMIT_BTN)
	public WebElement SubmitBtn;
	
	@FindBy(id = FSQABrowserRecordsPageLocators.SUBMIT_CMMT_TXA)
	public WebElement SubmitCmmtTxa;
	
	@FindBy(id = FSQABrowserRecordsPageLocators.HISTORY_ICON)
	public WebElement HistoryIcon;
	
	@FindBy(id = FSQABrowserRecordsPageLocators.CLOSE_HISTORY_POPUP)
	public WebElement CloseHistoryPopup;
	
	@FindBy(id = FSQABrowserRecordsPageLocators.CHANGE_RESOURCE_WARNING_BTN)
	public WebElement ChangeResourceWarningBtn;
	
	@FindBy(id = FSQABrowserRecordsPageLocators.CHANGE_RESOURCE_CMMT_TXA)
	public WebElement ChangeResourceCmmtTxa;
	
	@FindBy(id = FSQABrowserRecordsPageLocators.CHANGE_RESOURCE_POPUP_OK_BTN)
	public WebElement ChangeResourcePopupOkBtn;
	
	@FindBy(id = FSQABrowserRecordsPageLocators.RVIEWER_RECORDS_TITLE)
	public WebElement RViewerRecordsTitle;
	
	@FindBy(id = FSQABrowserRecordsPageLocators.RVIEWER_GROUPBY_BTN)
	public WebElement RViewerGroupbyBtn;
	
	@FindBy(className = FSQABrowserRecordsPageLocators.RVIEWER_GROUPBY_MNU_TITLE)
	public WebElement RViewerGroupbyMnuTitle;
	
	@FindBy(xpath = FSQABrowserRecordsPageLocators.SIGNOFF_CMMT_TXA)
	public WebElement SignoffCmmtTxa;
	
	@FindBy(xpath = FSQABrowserRecordsPageLocators.SIGNED_BY_HEADER_VALUE)
	public List<WebElement> SignedByHeaderValue;
	
	@FindBy(xpath = FSQABrowserRecordsPageLocators.RECORDS_LST)
	public List<WebElement> RecordsLst;
	
	@FindBy(xpath = FSQABrowserRecordsPageLocators.FORM_NAME)
	public WebElement FormName;
	
	@FindBy(xpath = FSQABrowserRecordsPageLocators.EDIT_RECORD_BTN)
	public WebElement EditRecordBtn;
	
	@FindBy(xpath = FSQABrowserRecordsPageLocators.EDIT_RECORD_BTN_DETS)
	public WebElement EditRecordBtnDets;
	
	@FindBy(xpath = FSQABrowserRecordsPageLocators.DOWNLOAD_RECORD_BTN)
	public WebElement DownloadRecordBtn;
	
	@FindBy(xpath = FSQABrowserRecordsPageLocators.VOID_RECORD_BTN)
	public WebElement VoidRecordBtn;
	
	@FindBy(xpath = FSQABrowserRecordsPageLocators.CANCEL_RECORD_BTN)
	public WebElement CancelRecordBtn;
	
	@FindBy(xpath = FSQABrowserRecordsPageLocators.CONFIRM_VOID_RECORD_BTN)
	public WebElement ConfirmVoidRecordBtn;
	
	@FindBy(xpath = FSQABrowserRecordsPageLocators.ACCEPT_SUBMIT_BTN)
	public WebElement AcceptSubmitBtn;
	
	@FindBy(xpath = FSQABrowserRecordsPageLocators.SUBMIT_SUCCESS_BTN)
	public WebElement SubmitSuccessBtn;
	
	@FindBy(xpath = FSQABrowserRecordsPageLocators.HISTORY_DETAILS)
	public List<WebElement> HistoryDetails;
	
	@FindBy(xpath = FSQABrowserRecordsPageLocators.EDIT_RESOURCE_LNK)
	public WebElement EditResourceLnk;
	
	//Verification Xpath
	@FindBy(xpath = FSQABrowserRecordsPageLocators.SIGNOFF_WITH_VERIFICATION_DDL)
	public WebElement SignOffWithVerificationDdl;
	
	@FindBy(xpath = FSQABrowserRecordsPageLocators.VERIFICATION_SELECT)
	public WebElement VerificationSelect;
	
	@FindBy(xpath = FSQABrowserRecordsPageLocators.VERIFICATION_COMMENT_DDL)
	public WebElement VerificationCommentDll;
	
	@FindBy(xpath = FSQABrowserRecordsPageLocators.VERIFICATION_COMMENT_SELECT)
	public WebElement VerificationCommentSelect;
	
	@FindBy(xpath = FSQABrowserRecordsPageLocators.RVIEWER_CARD_DATE_HEADER)
	public List<WebElement> RViewerCardDateHeader;
	
	@FindBy(xpath = FSQABrowserRecordsPageLocators.RVIEWER_CARD_CMPLTEDON_DATE)
	public List<WebElement> RViewerCardCmpltedOnDate;
	
	@FindBy(xpath = FSQABrowserRecordsPageLocators.RVIEWER_RECORD_CARDS)
	public List<WebElement> RViewerRecordCards;

	@FindBy(xpath = FSQABrowserRecordsPageLocators.RVIEWER_CARD_USRNM_DETS)
	public WebElement RViewerCardUsrnmDets;
	
	@FindBy(xpath = FSQABrowserRecordsPageLocators.RVIEWER_CARD_FORM_DETS)
	public WebElement RViewerCardFormDets;
	
	@FindBy(xpath = FSQABrowserRecordsPageLocators.RVIEWER_CARD_RESRCE_DETS)
	public WebElement RViewerCardResrceDets;
	
	@FindBy(xpath = FSQABrowserRecordsPageLocators.RVIEWER_GROUPBY_SLCTD_OPTN)
	public WebElement RViewerGroupbySlctdOptn;
	
	@FindBy(xpath = FSQABrowserRecordsPageLocators.RVIEWER_GROUPBY_DIVIDER)
	public WebElement RViewerGroupbyDivider;
	
	@FindBy(xpath = FSQABrowserRecordsPageLocators.RVIEWER_GROUPBY_LST)
	public List<WebElement> RViewerGroupbyLst;
	
	@FindBy(xpath = FSQABrowserRecordsPageLocators.RVIEWER_SIGN_ICON)
	public WebElement RViewerSignIcon;
	
	@FindBy(xpath = FSQABrowserRecordsPageLocators.RVIEWER_ATTCHMNT_ICON)
	public WebElement RViewerAttchmntIcon;
	
	@FindBy(xpath = FSQABrowserRecordsPageLocators.RVIEWER_COMPLIANT_ICON)
	public WebElement RViewerCompliantIcon;
	
	@FindBy(xpath = FSQABrowserRecordsPageLocators.NO_CHNGE_REC_POPUP)
	public WebElement NoChngeRecPopup;	
	
	@FindBy(xpath = FSQABrowserRecordsPageLocators.CONFIRM_OK_RECORD_BTN)
	public WebElement ConfirmOkRecordBtn;
	
	@FindBy(xpath = FSQABrowserRecordsPageLocators.RESRC_DROPDWN_LST)
	public List<WebElement> ResrcDropdwnLst;
	
	@FindBy(xpath = FSQABrowserRecordsPageLocators.SHOW_MORE_HIST_BTN)
	public List<WebElement> ShowMoreHistBtn;
	
	@FindBy(xpath = FSQABrowserRecordsPageLocators.SHOW_MORE_HIST_HEADER)
	public List<WebElement> ShowMoreHistHeader;
	
	@FindBy(xpath = FSQABrowserRecordsPageLocators.HEADER_DOC_LNK)
	public List<WebElement> HeaderDocLnk;
	
	@FindBy(xpath = FSQABrowserRecordsPageLocators.LOAD_DOC)
	public WebElement LoadDoc;
	
	@FindBy(xpath = FSQABrowserRecordsPageLocators.FORM_LEVEL_ATTCHMNT_LNK)
	public List<WebElement> FormLevelAttchmntLnk;
	
	@FindBy(xpath = FSQABrowserRecordsPageLocators.TASK_HISTORY_DETAILS)
	public List<WebElement> TaskHistoryDetails;
	
	@FindBy(xpath = FSQABrowserRecordsPageLocators.SELECT_SIGNOFF_VERIFICATION_DDL)
	public WebElement selectSignOffVerificationDdl;
	
	
	
	/**
	* Functions
	*/
	
	/**
	 * This method is used to click on Sign Off link
	 * @author hingorani_a
	 * @param none 
	 * @return boolean This returns true after clicking on Sign Off link
	 */
	public boolean clickSignRecordLnk() {
		try {
			controlActions.WaitforelementToBeClickable(SignoffLnk);
			controlActions.clickOnElement(SignoffLnk);
			logInfo("Clicked on Sign off link");
			Sync();
			return true;
		}
		catch(Exception e) {
			logError("Failed to click on Sign Record link - "
					+ e.getMessage());
			return false;
		}	
	}
	
	/**
	 * This method is used to click on Sign Off a record
	 * @author hingorani_a
	 * @param comment The comment you want to add while Signing Off 
	 * @return boolean This returns true after Signing off a record
	 */
	public boolean signoffRecord(String comment) {
		try {
			if(!clickSignRecordLnk()) {
				return false;
			}
			
			controlActions.WaitforelementToBeClickable(SignoffCmmtTxa);
			SignoffCmmtTxa.clear();
			controlActions.sendKeys(SignoffCmmtTxa, comment);
			logInfo("Added comment to Sign off as - " + comment);
			
			controlActions.WaitforelementToBeClickable(SignoffBtn);
			controlActions.clickOnElement(SignoffBtn);
			logInfo("Clicked on Sign off button");
			Sync();
			return true;
		}
		catch(Exception e) {
			logError("Failed to Sign off a record - "
					+ e.getMessage());
			return false;
		}	
	}
	
	/**
	 * This method is used to verify data of fields present in Form Header
	 * @author hingorani_a
	 * @param fieldName Use Class FORMFIELDS to set header field name
	 * @param value The value you want to verify for a header field
	 * @return boolean This returns true after verifying data for a Form Header field
	 */
	public boolean verifyRecordsDetails(String fieldName, String value) {
		WebElement FieldValue = null;
		String[] values = null;
		int count = 0;
		int valueCount = 0;
		int signedByEntries = 0;
		try {
			switch(fieldName) {
				case FORMFIELDS.FORMNAME:
					if(controlActions.perform_CheckIfElementTextContains(FormName, value)) {
						logInfo("Verified value " + value + " for field " + fieldName);
						return true;
					}
					return false;
					
				case FORMFIELDS.RESOURCE:
				case FORMFIELDS.LOCATION:
				case FORMFIELDS.VALIDATIONLOCK:
					FieldValue = controlActions.perform_GetElementByXPath(FSQABrowserRecordsPageLocators.HEADER_FIELD_VALUE, 
							"HEADERFIELDNAME", 
							fieldName);
					if(controlActions.perform_CheckIfElementTextContains(FieldValue, value)) {
						logInfo("Verified value " + value + " for field " + fieldName);
						return true;
					}
					return false;
					
				case FORMFIELDS.COMPLETEDBY:
					FieldValue = controlActions.perform_GetElementByXPath(FSQABrowserRecordsPageLocators.HEADER_FIELD_VALUE, 
							"HEADERFIELDNAME", 
							fieldName);
					values = CommonMethods.splitAndGetString(value);
					for(int i = 0; i < values.length; i++) {
						if(controlActions.perform_CheckIfElementTextContains(FieldValue, values[i])) {
							logInfo("Verified value " + values[i] + " is present ");
							count++;
						}
					}
					if(count > 0 && values.length == count) {
						logInfo("Verified Completed By value");
						return true;
					}
					logInfo("Could not verify Completed By value");
					return false;
					
				case FORMFIELDS.SIGNEDBY:
					values = CommonMethods.splitAndGetString(value);
					signedByEntries = SignedByHeaderValue.size();
					valueCount = values.length * signedByEntries;
					for(WebElement signedBy : SignedByHeaderValue) {
						for(int i = 0; i < values.length; i++) {
							if(controlActions.perform_CheckIfElementTextContains(signedBy, values[i])) {
								logInfo("Verified value " + values[i] + " is present ");
								count++;
							}
						}
					}
					if(count > 0 && valueCount == count) {
						logInfo("Verified Signed By values. Count of entries - " + signedByEntries);
						return true;
					}
					logInfo("Could not verify Signed By value");
					return false;

				default:
					logError("Incorrect Form Header field " + fieldName);
					return false;
			}
		}
		catch(Exception e) {
			logError("Failed to verify value " + value + " for Form field " + fieldName + " - "
					+ e.getMessage());
			return false;
		}	
	} 
	
	/**
	 * This method is used to click on Search button in View mode
	 * @author hingorani_a
	 * @param none
	 * @return boolean This returns true after clicking on Search button successfully
	 */
	public boolean clickSearchBtn() {
		try {
			controlActions.clickOnElement(SearchBtn);
			Sync();
			logInfo("Search Button Clicked");
			return true;
		}
		catch(Exception e) {
			logError("Failed to click on search button - "
					+ e.getMessage());
			return false;
		}	
	}
	
	/**
	 * This method is used to search a record in View mode
	 * @author hingorani_a
	 * @param recordDetail Put resource/form name/username to search
	 * @return boolean This returns true after searching the record
	 */
	public boolean searchRecordInViewSignMode(String recordDetail) {
		try {
			controlActions.WaitforelementToBeClickable(SearchTxt);
			SearchTxt.clear();
			controlActions.sendKeys(SearchTxt, recordDetail);
			logInfo("Search record using text : " + recordDetail);				

			if(!clickSearchBtn())
				return false;
			
			return true;
		}
		catch(Exception e) {
			logError("Failed to click search for text : " + recordDetail + " - "
					+ e.getMessage());
			return false;
		}	
	}
	
	/**
	 * This method is used to search and select a record in View mode
	 * @author hingorani_a
	 * @param recordDetail Put resource/form name/username to search
	 * @return boolean This returns true after selecting the record
	 */
	public boolean searchAndSelectRecordInViewSignMode(String recordDetail) {
		try {
			if(!searchRecordInViewSignMode(recordDetail)) {
				return false;
			}
			
			if(RecordsLst.size() == 1) {
				controlActions.WaitforelementToBeClickable(RecordsLst.get(0));
				controlActions.clickOnElement(RecordsLst.get(0));
				Sync();
				logInfo("Record with detail : " + recordDetail + " has been selected");
			}
			else {
				logError("More than 1 record found. Hence unable to select record in View/Sign Mode");
				return false;
			}
			return true;
		}
		catch(Exception e) {
			logError("Failed to search and select record for text : " + recordDetail + " - "
					+ e.getMessage());
			return false;
		}	
	}
	
	/**
	 * This method is used to search and verify the details of record in View Mode
	 * @author hingorani_a
	 * @param RecordFormDetails An object of Class RecordFormDetails, in order to verify
	 * values for different fields like Form name, Resource, Signed By for a record
	 * @return boolean This returns true after verifying record details in View Mode
	 */
	public boolean searchAndVerifyRecordsDetailsInView(RecordFormDetails rfd) {
		try {
			if(!searchAndSelectRecordInViewSignMode(rfd.FormName)) {
				return false;
			}
			
			if(rfd.FormName != null ) {
				if(!verifyRecordsDetails(FORMFIELDS.FORMNAME, rfd.FormName)) {
					return false;
				}
			}
			
			if(rfd.Resource != null ) {
				if(!verifyRecordsDetails(FORMFIELDS.RESOURCE, rfd.Resource)) {
					return false;
				}
			}

			if(rfd.Location != null ) {
				if(!verifyRecordsDetails(FORMFIELDS.LOCATION, rfd.Location)) {
					return false;
				}
			}
			
			if(rfd.CompletedBy != null ) {
				if(!verifyRecordsDetails(FORMFIELDS.COMPLETEDBY, rfd.CompletedBy)) {
					return false;
				}
			}
			
			if(rfd.SignedBy != null ) {
				if(!verifyRecordsDetails(FORMFIELDS.SIGNEDBY, rfd.SignedBy)) {
					return false;
				}
			}
			
			if(rfd.HistoryDetail != null) {
				if(!clickHistoryIcon()) {
					return false;
				}
				if(!verifyHistoryPopupDetails(rfd.HistoryDetail)) {
					return false;
				}
				if(!closeHistoryPopup()) {
					return false;
				}
			}	
			
			logInfo("Verified details of record in View/Sign Mode");
			return true;
		}
		catch(Exception e) {
			logError("Failed to to verify details of record in View/Sign Mode : "
					+ e.getMessage());
			return false;
		}		
	}
	
	/**
	 * This method is used to click on Edit button
	 * @author hingorani_a
	 * @param none 
	 * @return boolean This returns true after clicking on Edit button
	 */
	public boolean clickEditRecordBtn() {
		try {
			controlActions.WaitforelementToBeClickable(EditRecordBtn);
			controlActions.clickOnElement(EditRecordBtn);
			logInfo("Clicked on Edit Record button");
			Sync();
			return true;
		}
		catch(Exception e) {
			logError("Failed to click on Edit Record button - "
					+ e.getMessage());
			return false;
		}	
	}
	
	/**
	 * This method is used to click on Void button
	 * @author hingorani_a
	 * @param cmmt The comment to be added while voiding record
	 * @return FSQABrowserPage This returns object with error variable as false
	 * if Void button is clicked.
	 */
	public FSQABrowserPage clickAndPerformVoidRecord(String cmmt) {
		try {
			controlActions.WaitforelementToBeClickable(VoidRecordBtn);
			controlActions.clickOnElement(VoidRecordBtn);
			logInfo("Clicked on Void Record button");
			Sync();
			
			controlActions.WaitforelementToBeClickable(VoidCmmtTxa);
			VoidCmmtTxa.clear();
			controlActions.sendKeys(VoidCmmtTxa, cmmt);
			logInfo("Add comment : " + cmmt);
			
			controlActions.WaitforelementToBeClickable(ConfirmVoidRecordBtn);
			controlActions.clickOnElement(ConfirmVoidRecordBtn);
			logInfo("Clicked on Void button hence confirming void off record");
			Sync();
			return new FSQABrowserPage(driver);
		}
		catch(Exception e) {
			logError("Failed to Void Record - "
					+ e.getMessage());
			FSQABrowserPage fbp = new FSQABrowserPage(driver);
			fbp.error = true;
			return fbp;
		}	
	}
	
	/**
	 * This method is used to update value for a particular field
	 * @author hingorani_a
	 * @param fieldName Name of the field to be set
	 * @param fieldType Use class FIELDTYPE to set type of field
	 * @param fieldValue Value to be set to the field
	 * @param repeatFields Set true, if there are more than one same named field of same type
	 * @return boolean This returns true after updating field with value
	 */
	public boolean updateFieldValues(String fieldName, String fieldType, String fieldValue, boolean repeatFields) {
		List<WebElement> UpdateFormField = null;
		String tempXpath, finalXpath = null;
		try {
			tempXpath = controlActions.perform_GetDynamicXPath(FSQABrowserRecordsPageLocators.UPDATE_FORM_FIELD,
															   "FIELDLABEL", fieldName);
			finalXpath = controlActions.perform_GetDynamicXPath(tempXpath,
					   										   "FIELDTYPE", fieldType);
			UpdateFormField = controlActions.perform_GetListOfElementsByXPath_WithWaitType(finalXpath, WaitType.WhenPresent);
						
			if(repeatFields) {
				//yet to code
				logInfo("YET TO CODE! This code area is to tackle for Repeat fields");
				return true;
			}
			else {
				for(WebElement field : UpdateFormField) {
					if(controlActions.isElementDisplay(field)) {
						controlActions.perform_scrollToElement_ByElement(field);
						
						switch(fieldType) {
							case FIELDTYPE.NUMERIC:
								controlActions.WaitforelementToBeClickable(field);
								controlActions.actionClearTextBox(field);
								controlActions.clickOnElement(SCLogo);
								controlActions.sendKeys(field, fieldValue);
								SCLogo.click();
								logInfo("Clicked on Edit Record button");
								Sync();
								return true;
								
							default:
								logError("Input is an invalid fieldtype : " + fieldType);
								return false;
						}
					}
				}
				logError("Field with name : " + fieldName + " not found");
				return false;
			}
		}
		catch(Exception e) {
			logError("Failed to set field with name - " + fieldName 
					+ e.getMessage());
			return false;
		}	
	}
	
	/**
	 * This method is used to click on Submit Record button
	 * @author hingorani_a
	 * @param none
	 * @return boolean This returns true after clicking on Submit Record button
	 */
	public boolean clickSubmitRecordBtn() {
		try {
			controlActions.WaitforelementToBeClickable(SubmitBtn);
			controlActions.clickOnElement(SubmitBtn);
			logInfo("Clicked on Submit button");
			Sync();
			return true;
		}
		catch(Exception e) {
			logError("Failed to click on Submit button - "
					+ e.getMessage());
			return false;
		}	
	}
	
	/**
	 * This method is used to Submit Record with comment
	 * @author hingorani_a
	 * @param comment The comment to be added while submitting record
	 * @return boolean This returns true after Submitting Record
	 */
	public boolean submitRecord(String comment) {
		try {
			
			if(!clickSubmitRecordBtn()) {
				return false;
			}
			
			controlActions.WaitforelementToBeClickable(SubmitCmmtTxa);
			SignoffCmmtTxa.clear();
			controlActions.sendKeys(SubmitCmmtTxa, comment);
			logInfo("Added comment to Submit popup as - " + comment);
			
			controlActions.WaitforelementToBeClickable(AcceptSubmitBtn);
			controlActions.clickOnElement(AcceptSubmitBtn);
			logInfo("Clicked on Accept Submit button");
			Sync();
			
			controlActions.WaitforelementToBeClickable(SubmitSuccessBtn);
			controlActions.clickOnElement(SubmitSuccessBtn);
			logInfo("Clicked on Submit successfully popup's OK button");
			Sync();
			return true;
		}
		catch(Exception e) {
			logError("Failed to Submit a record - "
					+ e.getMessage());
			return false;
		}	
	}
	
	/**
	 * This method is used to verify value for a particular field
	 * @author hingorani_a
	 * @param fieldName Name of the field to be set
	 * @param fieldValue List of values verified for a field
	 * @return boolean This returns true after verifying value for a field
	 */
	public boolean verifyFieldValues(String fieldName, List<String> fieldValue) {
		List<WebElement> ReadFormField = null;
		List<String> getFieldVals = new ArrayList<String>();
		String fieldVal = null;
		try {
			ReadFormField = controlActions.perform_GetListOfElementsByXPath(FSQABrowserRecordsPageLocators.READ_FORM_FIELD,
															   "FIELDLABEL", fieldName);
			
			for(WebElement readField : ReadFormField) {
				fieldVal = readField.getText(); 
				getFieldVals.add(fieldVal);
				logInfo("For field " + fieldName + " value found is : " + fieldVal);
			}
			
			if(getFieldVals.containsAll(fieldValue)) {
				logInfo("Verified values for Field " + fieldName);
				return true;
			}
			
			logInfo("Could not verify values for Field " + fieldName);
			return false;
		}
		catch(Exception e) {
			logError("Failed to verify values for Field - " + fieldName 
					+ e.getMessage());
			return false;
		}	
	}
	
	/**
	 * This method is used to verify value for list of fields
	 * @author hingorani_a
	 * @param fields A list of Field name and their values
	 * @return boolean This returns true after verifying values for given fields
	 */
	public boolean verifyFieldValues(HashMap<String, List<String>> fields) {
		try {
			for (Map.Entry<String, List<String>> entry : fields.entrySet()) {
				String fieldName = entry.getKey();
				List<String> fieldValues = entry.getValue();
				
				if(!verifyFieldValues(fieldName, fieldValues)) {
					return false;
				}
				logInfo("Verified Field - " + fieldName + " and it's values - " + fieldValues);
			}
			return true;
		}
		catch(Exception e) {
			logError("Failed to verify values for Record - " 
					+ e.getMessage());
			return false;
		}	
	}
	
	/**
	 * This method is used to click on Download Record button
	 * @author hingorani_a
	 * @param none
	 * @return boolean This returns true after clicking on Download Record button
	 */
	public boolean clickDownloadRecordLnk() {
		try {
			controlActions.WaitforelementToBeClickable(DownloadRecordBtn);
			controlActions.clickOnElement(DownloadRecordBtn);
			logInfo("Clicked on Download record link");
			Sync();
			return true;
		}
		catch(Exception e) {
			logError("Failed to click on Download Record link - "
					+ e.getMessage());
			return false;
		}	
	}
	
	/**
	 * This method is used to close History popup 
	 * @author hingorani_a
	 * @param none
	 * @return boolean This returns true after closing History popup
	 */
	public boolean closeHistoryPopup() {
		try {
			controlActions.WaitforelementToBeClickable(CloseHistoryPopup);
			controlActions.clickOnElement(CloseHistoryPopup);
			logInfo("Closed History popup");
			Sync();
			return true;
		}
		catch(Exception e) {
			logError("Failed to close History popup - "
					+ e.getMessage());
			return false;
		}	
	}
	
	/**
	 * This method is used to click on History icon 
	 * @author hingorani_a
	 * @param none
	 * @return boolean This returns true after clicking on History icon 
	 */
	public boolean clickHistoryIcon() {
		try {
			controlActions.WaitforelementToBeClickable(HistoryIcon);
			controlActions.clickOnElement(HistoryIcon);
			logInfo("Clicked on History icon");
			Sync();
			return true;
		}
		catch(Exception e) {
			logError("Failed to click on History icon - "
					+ e.getMessage());
			return false;
		}	
	}
	
	/**
	 * This method is used to verify History record details
	 * @author hingorani_a
	 * @param detail History detail like date/username/comment/timezone etc to be verified
	 * @return boolean This returns true after verifying History record details
	 */
	public boolean verifyHistoryPopupDetails(String detail) {
		String[] values;
		int count = 0, valueCount = 0, recordFound = 0;
		try {
			for(WebElement histDetail : HistoryDetails) {
				values = CommonMethods.splitAndGetString(detail);
				valueCount = values.length;
				for(int i = 0; i < valueCount; i++) {
					if(controlActions.perform_CheckIfElementTextContains(histDetail, values[i])) {
						logInfo("Verified value " + values[i] + " is present ");
						count++;
					}
					else {
						break;
					}
				}
				
				if(count > 0 && count == valueCount) {
					recordFound++;
				}
				count = 0;
				valueCount = 0;	
			}
			if(recordFound>0 ) {
				logInfo("Verified History details on popup for " + recordFound + " history record");
				return true;
			}
			else {
				logError("Could not verify History Popup details");
				return false;
			}
		}
		catch(Exception e) {
			logError("Failed to verify History Details - "
					+ e.getMessage());
			return false;
		}	
	}
	
	/**
	 * This method is used to click on Edit resource link
	 * @author hingorani_a
	 * @param none
	 * @return boolean This returns boolean true after clicking on Edit resource link
	 */
	public boolean enableEditResource() {
		try {
			controlActions.WaitforelementToBeClickable(EditResourceLnk);
			controlActions.clickOnElement(EditResourceLnk);
			logInfo("Clicked on Edit resource link");
			
			controlActions.WaitforelementToBeClickable(ChangeResourceWarningBtn);
			controlActions.clickOnElement(ChangeResourceWarningBtn);
			logInfo("Clicked on Change Resource warning button");
			Sync();
			return true;
		}
		catch(Exception e) {
			logError("Failed to enable Edit resource state for a record - "
					+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to edit and set the resource of record using resourceName passed
	 * @author hingorani_a
	 * @param resourceName The name of resource to be set
	 * @return boolean This returns boolean true after setting the resource for record
	 */
	public boolean editResourceForRecord(String resourceName) {
		try {
			
			if(!enableEditResource()) {
				return false;
			}
			
			String selector = "li[class='k-item ng-scope'][role='option']";
			controlActions.JSSetValueFromList(driver, selector, resourceName);
			Sync();
			logInfo("Resource changed to - " + resourceName);
			return true;
		}
		catch(Exception e) {
			logError("Failed to enable Edit resource state for a record - "
					+ e.getMessage());
			return false;
		}	
	}
	
	/**
	 * This method is used to submit record for record whose resource has changed
	 * @author hingorani_a
	 * @param comment The comment to be added while submitting record
	 * @return FSQABrowserPage This returns object with error variable as false
	 * if Record is submitted successfully.
	 */
	public FSQABrowserPage submitEditResourceRecord(String comment) {
		try {
			FSQABrowserPage fbp = new FSQABrowserPage(driver);
			if(!clickSubmitRecordBtn()) {
				fbp.error = true;
				return fbp;
			}
			
			Sync();
			//controlActions.WaitforelementToBeClickable(ChangeResourceCmmtTxa);
			ChangeResourceCmmtTxa.clear();
			controlActions.sendKeys(ChangeResourceCmmtTxa, comment);
			logInfo("Added comment to Submit popup as - " + comment);
			
			controlActions.WaitforelementToBeClickable(ChangeResourcePopupOkBtn);
			controlActions.clickOnElement(ChangeResourcePopupOkBtn);
			logInfo("Clicked on Change resource Ok button");
			Sync();
			return fbp;
		}
		catch(Exception e) {
			logError("Failed to submit resource change for record - "
					+ e.getMessage());
			FSQABrowserPage fbp = new FSQABrowserPage(driver);
			fbp.error = true;
			return fbp;
		}	
	}
	
	/**
	 * This method is used to click on Sign Off a record with Verification
	 * @author dahale_p
	 * @param comment The comment you want to add while Signing Off 
	 * @return boolean This returns true after Signing off a record
	 */
	public boolean signoffRecordWithVerfication(String verificationName, String verificationComment) {
		try {
			if(!clickSignRecordLnk()) {
				return false;
			}
			Sync();
			controlActions.clickOnElement(SignOffWithVerificationDdl);
			Sync();
			WebElement SelectVerification = controlActions.perform_GetElementByXPath(FSQABrowserRecordsPageLocators.VERIFICATION_SELECT,"VERIFICATION_NAME",verificationName);
			Sync();
			controlActions.clickOnElement(SelectVerification);
			Sync();
			
			String selector = "li[class='k-item ng-scope'][role='option']";
			controlActions.JSSetValueFromList(driver, selector, verificationComment);
			Sync();
			controlActions.WaitforelementToBeClickable(SignoffBtn);
			Sync();
			controlActions.clickOnElement(SignoffBtn);
			logInfo("Clicked on Sign off button");
			Sync();
			return true;
		}
		catch(Exception e) {
			logError("Failed to Sign off a record - "
					+ e.getMessage());
			return false;
		}	
	}
	
	/**
	 * This method is used to click on Cancel button
	 * @author hingorani_a
	 * @param none 
	 * @return boolean This returns true after clicking on Cancel button
	 */
	public boolean clickCancelRecordBtn() {
		try {
			controlActions.WaitforelementToBeClickable(CancelRecordBtn);
			controlActions.clickOnElement(CancelRecordBtn);
			logInfo("Clicked on Cancel Record button");
			Sync();
			return true;
		}
		catch(Exception e) {
			logError("Failed to click on Cancel Record button - "
					+ e.getMessage());
			return false;
		}	
	}
	
	/**
	 * This method is used to get Card Layout Date Header details
	 * @author hingorani_a
	 * @param none 
	 * @return List<String> This returns list of Dates present as per Record Viewer Card layout 
	 * header dates
	 */
	public List<String> getCardLayoutHeaderDates() {
		String currentDate = null;
		String DTParts[] = null;
		String Date[] = null;
		List<String> dateCompare = new ArrayList<String>();
		
		try {
			if(RViewerCardDateHeader.isEmpty()) {
				logError("No Data found for Card Layout header");
				return null;
			}
//			else if(RViewerCardDateHeader.size()==1) {
//				//handle
//			}
			else {
				for(WebElement date : RViewerCardDateHeader) {
					currentDate = date.getText();
					DTParts=currentDate.split(", ");
					Date=DTParts[1].split(" ");
					
					switch(Date[0].toUpperCase()) {
					case MONTH.JAN:
						Date[0]=MONTH_NUM.JAN;
						break;
					case MONTH.FEB:
						Date[0]=MONTH_NUM.FEB;
						break;
					case MONTH.MAR:
						Date[0]=MONTH_NUM.MAR;
						break;
					case MONTH.APR:
						Date[0]=MONTH_NUM.APR;
						break;
					case MONTH.MAY:
						Date[0]=MONTH_NUM.MAY;
						break;
					case MONTH.JUN:
						Date[0]=MONTH_NUM.JUN;
						break;
					case MONTH.JUL:
						Date[0]=MONTH_NUM.JUL;
						break;
					case MONTH.AUG:
						Date[0]=MONTH_NUM.AUG;
						break;
					case MONTH.SEP:
						Date[0]=MONTH_NUM.SEP;
						break;
					case MONTH.OCT:
						Date[0]=MONTH_NUM.OCT;
						break;
					case MONTH.NOV:
						Date[0]=MONTH_NUM.NOV;
						break;
					case MONTH.DEC:
						Date[0]=MONTH_NUM.DEC;
						break;
					default:
						logError("Incorrect Month - " + Date[0]);
						break;
					}
					
					String dateInFormat = Date[0] + "/" + Date[1] + "/" + Date[2];
					dateCompare.add(dateInFormat);
				}
			}
			return dateCompare;
		}
		catch(Exception e) {
			logError("Failed to get Card Layout header dates - "
					+ e.getMessage());
			return dateCompare;
		}	
	}
	
	/**
	 * This method is used to compare Card Layout Header Dates
	 * @author hingorani_a
	 * @param none 
	 * @return int This returns 0 if no values for Header Dates/in case of Exception
	 * returns 1 if only one value is retrieved for Header Dates
	 * returns 2 iff the Header Dates are in ascending order in Record Viewer Card layout 
	 */
	public int compareCardLayoutHeaderDates() {
		DateTime dt = new DateTime();
		int dateVerifiedCount = 0;
		ComparisonValue comparisonVal = null;

		try {
			
			List<String> dateValues = getCardLayoutHeaderDates();
			if(dateValues==null)
				return 0;
			
			if(dateValues.size()==1) {
				logInfo("Found just one record for Card Layout Header, could not compare");
				return 1;
			}
			else {
				for(int i=0; i<dateValues.size()-1; i=i+1) {
					comparisonVal = dt.compareDates(dateValues.get(i), dateValues.get(i+1), null, null);
					if(comparisonVal.equals(ComparisonValue.LESSER))
						dateVerifiedCount++;
				}
			}
			
			if((dateValues.size()-1)==dateVerifiedCount) {
				logInfo("The dates are in ascending order or oldest to newest");
				return 2;
			}
			
			return 0;
		}
		catch(Exception e) {
			logError("Failed to compare Card Layout header dates - "
					+ e.getMessage());
			return 0;
		}	
	}
	
	/**
	 * This method is used to compare Card Layout Completed On Dates
	 * @author hingorani_a
	 * @param verifyCount Date range to be compared. Example: If you pass 5, the 5 Dates will be compared 
	 * @return boolean This returns true iff the Completed On Dates are in ascending order in 
	 * Record Viewer Card layout 
	 */
	public boolean compareCardLayoutCompletedOnDates(int verifyCount) {
		DateTime dt = new DateTime();
		int dateVerifiedCount = 0;
		String firstDate, secondDate = null;
		ComparisonValue comparisonVal = null;
		String DTParts[] = null;
		
		try {
			
			if(RViewerCardCmpltedOnDate==null)
				return false;
			
			if(RViewerCardCmpltedOnDate.size()==1) {
				//nothing to do
			}
			else {
				for(int i=0; i<verifyCount-1; i=i+1) {
					DTParts=RViewerCardCmpltedOnDate.get(i).getText().split(" ");
					firstDate = DTParts[0] + " " + DTParts[1];
					DTParts=RViewerCardCmpltedOnDate.get(i+1).getText().split(" ");
					secondDate = DTParts[0] + " " + DTParts[1];

					comparisonVal = dt.compareDates(firstDate, secondDate, false, false);
					if(comparisonVal.equals(ComparisonValue.LESSER))
						dateVerifiedCount++;
				}
			}
			
			if((verifyCount-1)==dateVerifiedCount) {
				logInfo("The dates are in ascending order or oldest to newest");
				return true;
			}
			
			return false;
		}
		catch(Exception e) {
			logError("Failed to compare Card Layout Completed On dates - "
					+ e.getMessage());
			return false;
		}	
	}
	
	/**
	 * This method is used to compare Card Layout Header Dates
	 * @author hingorani_a
	 * @param recordNo Number of record to be clicked 
	 * @return boolean This returns true iff the Header Dates are in ascending order in Record Viewer Card layout 
	 */
	public boolean selectRecordInRecordViewer(int recordNo) {
		try {
			
			if(RViewerRecordCards==null)
				return false;
			
			if(RViewerRecordCards.size()==1) {
				logInfo("Found just one record");
				return false;
			}
			else {
				RViewerRecordCards.get(recordNo-1).click();
				logInfo("Selected " + (recordNo-1) + " record in Record Viewer");
				return true;
			}
		}
		catch(Exception e) {
			logError("Failed to compare Card Layout header dates - "
					+ e.getMessage());
			return false;
		}	
	}
	
	/**
	 * This method is used to select a record in Record Viewer
	 * @author hingorani_a
	 * @param formName Form name to identify the record  
	 * @return boolean This returns true if the expected record in Record Viewer is selected
	 */
	public boolean selectRecordInRecordViewer(String formName) {
		WebElement RViewerCardFormnm = null;
		
		try {
			RViewerCardFormnm = controlActions.perform_GetElementByXPath(FSQABrowserRecordsPageLocators.RVIEWER_CARD_FORMNM,
					   "FORMNAME", formName);
			controlActions.perform_ClickWithJavaScriptExecutor(RViewerCardFormnm);
			Sync();
			logInfo("Selected record - " + formName + " in Record Viewer");
			return true;
		}
		catch(Exception e) {
			logError("Failed to select a record in Record Viewer - "
					+ e.getMessage());
			return false;
		}	
	}
	
	/**
	 * This method is used to get generic details for a record like form name, resource name, location name and
	 * completed by fields
	 * @author hingorani_a
	 * @param none 
	 * @return List<String> This returns generic details for a record 
	 */
	public List<String> getSelectedRecordDetsInRecordViewer() {
		WebElement ResourceName = null;
		WebElement LocationName = null;
		WebElement CompletedBy = null;
		List<String> details = new ArrayList<String>();
		
		try {
			
			details.add(FormName.getText());
			
			ResourceName = controlActions.perform_GetElementByXPath(FSQABrowserRecordsPageLocators.HEADER_FIELD_VALUE, 
					"HEADERFIELDNAME", 
					FORMFIELDS.RESOURCE);
			details.add(ResourceName.getText());
			
			LocationName = controlActions.perform_GetElementByXPath(FSQABrowserRecordsPageLocators.HEADER_FIELD_VALUE, 
					"HEADERFIELDNAME", 
					FORMFIELDS.LOCATION);
			details.add(LocationName.getText());
			
			CompletedBy = controlActions.perform_GetElementByXPath(FSQABrowserRecordsPageLocators.HEADER_FIELD_VALUE, 
					"HEADERFIELDNAME", 
					FORMFIELDS.COMPLETEDBY);
			details.add(CompletedBy.getText());
		
			logError("Added generic record details to list - " + details);
			return details;
		}
		catch(Exception e) {
			logError("Failed to get generic record details - "
					+ e.getMessage());
			return null;
		}	
	}
	
	/**
	 * This method is used to verify the details of record in View Mode
	 * @author hingorani_a
	 * @param RecordFormDetails An object of Class RecordFormDetails, in order to verify
	 * values for different fields like Form name, Resource, Signed By for a record
	 * @return boolean This returns true after verifying record details in View Mode
	 */
	public boolean verifyRecordsDetailsInView(RecordFormDetails rfd) {
		try {

			if(rfd.FormName != null ) {
				if(!verifyRecordsDetails(FORMFIELDS.FORMNAME, rfd.FormName)) {
					return false;
				}
			}
			
			if(rfd.Resource != null ) {
				if(!verifyRecordsDetails(FORMFIELDS.RESOURCE, rfd.Resource)) {
					return false;
				}
			}

			if(rfd.Location != null ) {
				if(!verifyRecordsDetails(FORMFIELDS.LOCATION, rfd.Location)) {
					return false;
				}
			}
			
			if(rfd.CompletedBy != null ) {
				if(!verifyRecordsDetails(FORMFIELDS.COMPLETEDBY, rfd.CompletedBy)) {
					return false;
				}
			}
			
			if(rfd.SignedBy != null ) {
				if(!verifyRecordsDetails(FORMFIELDS.SIGNEDBY, rfd.SignedBy)) {
					return false;
				}
			}
			
			if(rfd.HistoryDetail != null) {
				if(!clickHistoryIcon()) {
					return false;
				}
				if(!verifyHistoryPopupDetails(rfd.HistoryDetail)) {
					return false;
				}
				if(!closeHistoryPopup()) {
					return false;
				}
			}	
			
			logInfo("Verified details of record in View/Sign Mode");
			return true;
		}
		catch(Exception e) {
			logError("Failed to to verify details of record in View/Sign Mode : "
					+ e.getMessage());
			return false;
		}		
	}
	
	/**
	 * This method is used to click on Record Viewer's Group by button
	 * @author hingorani_a
	 * @param none 
	 * @return boolean This returns true after clicking on Group by button
	 */
	public boolean clickGroupByInRecViewer() {
		try {
			controlActions.WaitforelementToBeClickable(RViewerGroupbyBtn);
			RViewerGroupbyBtn.click();
			logInfo("Clicked on Record Viewer's Group by button");
			return true;
		}
		catch(Exception e) {
			logError("Failed to click on Record Viewer's Group by button - "
					+ e.getMessage());
			return false;
		}	
	}
	
	/**
	 * This method is used to set the viewing type of Record Viewer by Group By 
	 * @author hingorani_a
	 * @param optionName Use class GROUPBY_OPTION to set name of option as group by Resource, Form name, etc
	 * @return boolean This returns true after setting a viewing type using Group by
	 */
	public boolean selectAndSetGroupByOption(String optionName) {
		WebElement RViewerGroupbyOptn = null;
		
		try {
			
			if(!clickGroupByInRecViewer())
				return false;
			
			RViewerGroupbyOptn = controlActions.perform_GetElementByXPath(FSQABrowserRecordsPageLocators.RVIEWER_GROUPBY_OPTN, 
					"OPTIONNAME", optionName);
			controlActions.WaitforelementToBeClickable(RViewerGroupbyOptn);
			RViewerGroupbyOptn.click();
			logInfo("Record Viewer's Group by option set to - " + optionName);
			return true;
		}
		catch(Exception e) {
			logError("Failed to set Record Viewer's Group by option - "
					+ e.getMessage());
			return false;
		}	
	}
	
	/**
	 * This method is used to verify the Record Viewer's header 
	 * @author hingorani_a
	 * @param headerDetail Detail to check if it is present in Header
	 * @return boolean This returns true after verifying Record Viewer's header
	 */
	public boolean verifyHeaderDetail(String headerDetail) {
		try {
			
			for(WebElement header : RViewerCardDateHeader) {
				if(header.getText().contains(headerDetail))
					return true;
			}
			
			logInfo("Could not find detail " + headerDetail + "in header");
			return false;
		}
		catch(Exception e) {
			logError("Failed to verify detail - " + headerDetail + "in header"
					+ e.getMessage());
			return false;
		}	
	}
	
	/**
	 * This method is used to search and select a record in View mode
	 * @author hingorani_a
	 * @param recordDetail Put resource/form name/username to search
	 * @param numberOfRec The number of record to be clicked
	 * @return boolean This returns true after selecting the record
	 */
	public boolean searchAndSelectRecordInViewSignMode(String recordDetail, int numberOfRec) {
		try {
			if(!searchRecordInViewSignMode(recordDetail)) {
				return false;
			}
			
			controlActions.WaitforelementToBeClickable(RecordsLst.get(numberOfRec-1));
			controlActions.clickOnElement(RecordsLst.get(numberOfRec-1));
			Sync();
			logInfo("Record with detail : " + recordDetail + " has been selected");
			return true;
		}
		catch(Exception e) {
			logError("Failed to search and select record for text : " + recordDetail + " - "
					+ e.getMessage());
			return false;
		}	
	}
	
	/**
	 * This method is used to click on Ok button on Information popup
	 * @author hingorani_a
	 * @param none 
	 * @return boolean This returns true after clicking on Ok button
	 */
	public boolean clickOkOnInfoPopup() {
		try {
			controlActions.WaitforelementToBeClickable(ConfirmOkRecordBtn);
			ConfirmOkRecordBtn.click();
			logInfo("Clicked on Ok button on Information popup");
			return true;
		}
		catch(Exception e) {
			logError("Failed to click on Ok button on Information popup - "
					+ e.getMessage());
			return false;
		}	
	}
	
	
	/**
	 * This method is used to open and verify mentioned History details 
	 * @author hingorani_a
	 * @param hd Use Class HistoryDetails to set details like type of edit, name of field changed
	 * old and new value, etc
	 * @return boolean This returns true after verifying history details and closes the popup as well
	 */
	public boolean verifyHistoryPopupDetails(HistoryDetails hd) {
		List<WebElement> ShowMoreHistLst = null;
		WebElement ShowMoreHistLabel = null;
		WebElement ShowMoreHistNewVal = null;
		WebElement ShowMoreHistOthrNewVal = null;
		WebElement ShowMoreHistOldVal = null;
		String intermediateXpath = null;
		int countBefore = 0;
		int countAfter = 0;
		int cntTotalAfter = 0;
		int totalIterations = 0;
		int fieldIndexNo = 1;
		
		try {
			if(hd.openHistoryPopup) {
				if(!clickHistoryIcon()) {
					return false;
				}
			}

			for (Map.Entry<String, String> entry : hd.HeaderDetail.entrySet()) {
				String histType = entry.getKey();
				String histDetail = entry.getValue();

				if(histDetail!=null) {
					if(!verifyHistoryPopupDetails(histDetail)) 
						return false;
					else 
						cntTotalAfter++; totalIterations++;
				}
				
				if(histType!=null) {
					switch(histType) {
					case HISTORY_EDIT_TYPES.RECORD_EDITED:
						if(hd.showMoreLink) {
							ShowMoreHistBtn.get(0).click();
							Sync();
						}
						
						if(hd.detailedInfoList != null) {
							for(HistDetailedInfo details : hd.detailedInfoList) {
								
								// Verify Field name and get index
								if(details.fieldName!=null) {
									if(ShowMoreHistHeader!=null) {
										for(WebElement element: ShowMoreHistHeader) {
											if(element.getText().equalsIgnoreCase(details.fieldName)) {
												logInfo("Verified header as " + details.fieldName + " with index number " + fieldIndexNo);
											}
											else {
												fieldIndexNo++;
											}
										}
									}
								}
								
								ShowMoreHistLst = controlActions.perform_GetListOfElementsByXPath(
										FSQABrowserRecordsPageLocators.SHOW_MORE_HIST_LST, 
										"I_INDEX_NO", Integer.toString(fieldIndexNo));
								
								for(int i = 1; i <= ShowMoreHistLst.size() + 1; i++) {

									// Verify labels like - Correction, ATtachment, Resolved are present/displayed etc
									if(details.editType!=null && details.editType!=FIELD_OTHER_TYPES.FIELD) {
										countBefore++;

										intermediateXpath = controlActions.perform_GetDynamicXPath(
												FSQABrowserRecordsPageLocators.SHOW_MORE_HIST_LABEL, 
												"I_INDEX_NO", Integer.toString(fieldIndexNo));
										ShowMoreHistLabel = controlActions.perform_GetElementByXPath(
												intermediateXpath, "J_INDEX_NO", Integer.toString(i));
										if(ShowMoreHistLabel!=null) {
											if(ShowMoreHistLabel.getText().contains(details.editType)) {
												countAfter++;
												logInfo("Verified label name as " + details.editType);
											}
										}
									}

									// for attachment type
									if(details.newFieldValue!=null && details.editType==FIELD_OTHER_TYPES.ATTACHMENT) {
										countBefore++;

										ShowMoreHistNewVal = controlActions.perform_GetElementByXPath(
												FSQABrowserRecordsPageLocators.SHOW_MORE_HIST_ATTCH_NEW_VAL, 
												"I_INDEX_NO", Integer.toString(fieldIndexNo));
										if(ShowMoreHistNewVal!=null) {
											if(ShowMoreHistNewVal.getText().equalsIgnoreCase(details.newFieldValue)) {
												countAfter++;
												logInfo("Verified Attachment added named as " + details.newFieldValue);
											}
										}
									}
									
									// for anything except Attachment type that has new value
									else if(details.newFieldValue!=null) {
										countBefore++;

										intermediateXpath = controlActions.perform_GetDynamicXPath(
												FSQABrowserRecordsPageLocators.SHOW_MORE_HIST_OTHR_NEW_VAL, 
												"I_INDEX_NO", Integer.toString(fieldIndexNo));
										ShowMoreHistOthrNewVal = controlActions.perform_GetElementByXPath(
												intermediateXpath, "J_INDEX_NO", Integer.toString(i));
										
										if(ShowMoreHistOthrNewVal!=null) {
											if(ShowMoreHistOthrNewVal.getText().equalsIgnoreCase(details.newFieldValue)){
												countAfter++;
												logInfo("Verified for " + details.editType + " the new value is " 
												+ details.newFieldValue);
											}
										}
									}
									
									// for anything that has old value
									if(details.oldFieldValue!=null) {
										countBefore++;

										intermediateXpath = controlActions.perform_GetDynamicXPath(
												FSQABrowserRecordsPageLocators.SHOW_MORE_HIST_OLD_VAL, 
												"I_INDEX_NO", Integer.toString(fieldIndexNo));
										ShowMoreHistOldVal = controlActions.perform_GetElementByXPath(
												intermediateXpath, "J_INDEX_NO", Integer.toString(i));

										if(ShowMoreHistOldVal!=null) {	
											if(ShowMoreHistOldVal.getText().equalsIgnoreCase(details.oldFieldValue)){
													countAfter++;
													logInfo("Verified for " + details.editType + " the old value is " 
													+ details.oldFieldValue);
												}
										}
									}
									
									if(countBefore==countAfter) {
										cntTotalAfter++;
										logInfo("Verfied value for " + details.editType);
										break;
									}
									else {
										countBefore = 0;
										countAfter = 0;
									}
								}
								totalIterations++;
							}
						}
					}
				}
			}
			
			if(hd.closeHistoryPopup) {
				if(!closeHistoryPopup()) {
					return false;
				}
			}

			if(cntTotalAfter==totalIterations) {
				logInfo("Verified history values");
				return true;
			}
			else {
				logInfo("Could not verify history values");
				return false;
			}
		}
		catch(Exception e) {
			logError("Failed to verify history details - "
					+ e.getMessage());
			return false;
		}
	}
	
	/**
	 * This method is used to get data of field present in Form section
	 * @author hingorani_a
	 * @param fieldName The name of field whose value you want
	 * @param otherType Use Class FIELD_OTHER_TYPES to set a type of field it is; whether a field or
	 * comment or Correction etc
	 * @return String This returns value of mentioned fieldName
	 */
	public String getFieldSpecificValue(String fieldName, String otherType) {
		List<WebElement> FieldValues = null;

		try {
			switch(otherType) {
				case FIELD_OTHER_TYPES.FIELD:
					FieldValues = controlActions.perform_GetListOfElementsByXPath(FSQABrowserRecordsPageLocators.FIELD_VAL, 
							"FIELD_NAME", fieldName);
					break;
					
				case FIELD_OTHER_TYPES.COMPLIANCE:
					FieldValues = controlActions.perform_GetListOfElementsByXPath(FSQABrowserRecordsPageLocators.FIELD_CMPLIANCE_VAL, 
							"FIELD_NAME", fieldName);
					break;
					
				case FIELD_OTHER_TYPES.COMMENT:
				case FIELD_OTHER_TYPES.CORRECTION:
				case FIELD_OTHER_TYPES.RESOLVED:
					String finalXpath = controlActions.perform_GetDynamicXPath(FSQABrowserRecordsPageLocators.OTHR_FIELD_VAL, 
							"FIELD_NAME", fieldName);
					FieldValues = controlActions.perform_GetListOfElementsByXPath(finalXpath, 
							"FIELD_TYPE", otherType);
					break;
					
				case FIELD_OTHER_TYPES.ATTACHMENT:
					FieldValues = controlActions.perform_GetListOfElementsByXPath(FSQABrowserRecordsPageLocators.ATTCHMNT_FIELD_VAL, 
							"FIELD_NAME", fieldName);
					break;
					
				default:
					logError("Fetching incorrect Record field " + fieldName);
					return null;
			}
			
			if(FieldValues.isEmpty())
				return null;
			else if(FieldValues.size()==1)
				return FieldValues.get(0).getText();
			else {
				StringBuffer sb = new StringBuffer();
			      
			    for (WebElement element : FieldValues) {
			    	sb.append(element.getText());
			        sb.append("|");
			    }
			    String values = sb.toString();
			    return values;
			}
		}
		catch(Exception e) {
			logError("Failed to verify value for Form field " + fieldName + " - "
					+ e.getMessage());
			return null;
		}	
	} 
	
	/**
	 * This method is used to set Text type fields when Record is in Edit mode
	 * @author hingorani_a
	 * @param fieldId The unique id for a field
	 * @param fieldValue The value to be set to text-box field
	 * @return boolean This returns true after we set Text type field
	 */
	public boolean setTextField(String fieldId, String fieldValue) {
		WebElement UpdateFieldTxt = null;
		
		try {
			UpdateFieldTxt = controlActions.perform_GetElementByXPath(FSQABrowserRecordsPageLocators.UPDATE_FIELD_TXT,
					"DATA_FIELD", fieldId);

			controlActions.perform_scrollToElement_ByElement(UpdateFieldTxt);
			controlActions.WaitforelementToBeClickable(UpdateFieldTxt);
			controlActions.actionClearTextBox(UpdateFieldTxt);
			SCLogo.click();
			UpdateFieldTxt.sendKeys(fieldValue);
			SCLogo.click();
			Sync();
			logInfo("Updated field with value " + fieldValue);
			return true;
		}
		catch(Exception e) {
			logError("Failed to update field with value - " + fieldValue
					+ e.getMessage());
			return false;
		}	
	}
	
	/**
	 * This method is used to set Comments type fields when Record is in Edit mode
	 * @author hingorani_a
	 * @param fieldId The unique id for a field
	 * @param fieldValue The value to be set to Comments field
	 * @return boolean This returns true after we set Comments type field
	 */
	public boolean setCommentsField(String fieldId, String fieldValue) {
		WebElement UpdateCmmntTxa = null;
		
		try {
			UpdateCmmntTxa = controlActions.perform_GetElementById(FSQABrowserRecordsPageLocators.UPDATE_CMMNT_TXA,
					"DATA_FIELD", fieldId);

			controlActions.perform_scrollToElement_ByElement(UpdateCmmntTxa);
			controlActions.WaitforelementToBeClickable(UpdateCmmntTxa);
			controlActions.actionClearTextBox(UpdateCmmntTxa);
			SCLogo.click();
			UpdateCmmntTxa.sendKeys(fieldValue);
			Sync();
			logInfo("Updated field with value " + fieldValue);
			return true;
		}
		catch(Exception e) {
			logError("Failed to update field with value - " + fieldValue
					+ e.getMessage());
			return false;
		}	
	}
	
	/**
	 * This method is used to set Correction text type fields when Record is in Edit mode
	 * @author hingorani_a
	 * @param fieldId The unique id for a field
	 * @param fieldValue The value to be set to Corrections field
	 * @return boolean This returns true after we set Correction text type field
	 */
	public boolean setCorrectionField(String fieldId, String fieldValue) {
		WebElement UpdateCrrctnTxa = null;
		
		try {
			UpdateCrrctnTxa = controlActions.perform_GetElementById(FSQABrowserRecordsPageLocators.UPDATE_CRRCTN_TXA,
					"DATA_FIELD", fieldId);

			controlActions.perform_scrollToElement_ByElement(UpdateCrrctnTxa);
			controlActions.WaitforelementToBeClickable(UpdateCrrctnTxa);
			controlActions.actionClearTextBox(UpdateCrrctnTxa);
			SCLogo.click();
			UpdateCrrctnTxa.sendKeys(fieldValue);
			Sync();
			logInfo("Updated field with value " + fieldValue);
			return true;
		}
		catch(Exception e) {
			logError("Failed to update field with value - " + fieldValue
					+ e.getMessage());
			return false;
		}	
	}
	
	/**
	 * This method is used to set Attachment upload type fields when Record is in Edit mode
	 * @author hingorani_a
	 * @param fieldId The unique id for a field
	 * @param fieldValue The value to be set to Attachment field
	 * @return boolean This returns true after we set Attachment upload type field
	 */
	public boolean setAttachmentField(String fieldId, String fieldValue) {
		WebElement UpdateAttchmnt = null;
		
		try {
			UpdateAttchmnt = driver.findElement(By.id(fieldId));

			controlActions.perform_scrollToElement_ByElement(UpdateAttchmnt);
			UpdateAttchmnt.sendKeys(fieldValue);
			Sync();
			logInfo("Updated field with value " + fieldValue);
			return true;
		}
		catch(Exception e) {
			logError("Failed to update field with value - " + fieldValue
					+ e.getMessage());
			return false;
		}	
	}
	
	/**
	 * This method is used to set Yes type of Resolve field when Record is in Edit mode
	 * @author hingorani_a
	 * @param fieldId The unique id for a field
	 * @return boolean This returns true after we set Yes type of Resolve field
	 */
	public boolean setYesResolveField(String fieldId) {
		WebElement UpdateToYesRslv = null;
		
		try {
			UpdateToYesRslv = controlActions.perform_GetElementByXPath(FSQABrowserRecordsPageLocators.UPDATE_TO_YES_RSLV,
					"DATA_FIELD", fieldId);

			controlActions.perform_scrollToElement_ByElement(UpdateToYesRslv);
			controlActions.WaitforelementToBeClickable(UpdateToYesRslv);
			UpdateToYesRslv.click();
			Sync();
			logInfo("Updated field with Yes type of Resolve value");
			return true;
		}
		catch(Exception e) {
			logError("Failed to update field with Yes type of Resolve value - "
					+ e.getMessage());
			return false;
		}	
	}
	
	/**
	 * This method is used to set No type of Resolve field when Record is in Edit mode
	 * @author hingorani_a
	 * @param fieldId The unique id for a field
	 * @return boolean This returns true after we set No type of Resolve field
	 */
	public boolean setNoResolveField(String fieldId) {
		WebElement UpdateToNoRslv = null;
		
		try {
			UpdateToNoRslv = controlActions.perform_GetElementByXPath(FSQABrowserRecordsPageLocators.UPDATE_TO_NO_RSLV,
					"DATA_FIELD", fieldId);

			controlActions.perform_scrollToElement_ByElement(UpdateToNoRslv);
			controlActions.WaitforelementToBeClickable(UpdateToNoRslv);
			UpdateToNoRslv.click();
			Sync();
			logInfo("Updated field with No type of Resolve value");
			return true;
		}
		catch(Exception e) {
			logError("Failed to update field with No type of Resolve value - "
					+ e.getMessage());
			return false;
		}	
	}
	
	
	/**
	 * This method is used to update fields of a record
	 * @author hingorani_a
	 * @param ufd Use Class UpdateFieldDets to set field details like value of field, corrections,
	 * comments, attachment, etc
	 * @return boolean This returns true after updating the record
	 */
	public boolean updateFieldValues(UpdateFieldDets ufd) {
		WebElement FieldInRecord = null;
		String fieldType = null;
		String fieldId = null;

		try {

			if(ufd.recFieldProps!=null) {
				for(RecFieldProps fieldProps : ufd.recFieldProps) {
					FieldInRecord = controlActions.perform_GetElementByXPath(FSQABrowserRecordsPageLocators.FIELD_IN_RECORD,
							"FIELD_NAME", fieldProps.fieldName);

					fieldType =  FieldInRecord.getAttribute("data-fieldtype");
					fieldId =  FieldInRecord.getAttribute("data-field");

					if(fieldProps.fieldValue!=null) {
						switch(fieldType) {
						case FIELDTYPE.NUMERIC:
						case FIELDTYPE.FREETEXT:
							
							if(!setTextField(fieldId, fieldProps.fieldValue))
								return false;
							logInfo("Updated field " + fieldProps.fieldName + " value");
							break;
	
						default:
							logError("Input is an invalid fieldtype for field : " + fieldProps.fieldName);
							return false;
						}
					}
					
					if(fieldProps.commentsText!=null) {
						
						if(!setCommentsField(fieldId, fieldProps.commentsText))
							return false;
						logInfo("Updated comment for field " + fieldProps.fieldName);
					}

					if(fieldProps.correctionText!=null) {
						
						if(!setCorrectionField(fieldId, fieldProps.correctionText))
							return false;
						logInfo("Updated correction for field " + fieldProps.fieldName);
					}

					if(fieldProps.uploadAttachment!=null) {
						
						if(!setAttachmentField(fieldId, fieldProps.uploadAttachment))
							return false;
						logInfo("Updated attachment for field " + fieldProps.fieldName);
					}

					if(fieldProps.yesResolve || fieldProps.noResolve) {
						if(fieldProps.yesResolve) {
							
							if(!setYesResolveField(fieldId))
								return false;
							logInfo("Updated Resolve as Yes for field " + fieldProps.fieldName);
						}

						if(fieldProps.noResolve) {
							
							if(!setNoResolveField(fieldId))
								return false;
							logInfo("Updated Resolve as No for field " + fieldProps.fieldName);
						}
					}
				}
			}

			logInfo("Updated the record details");
			return true;
		}
		catch(Exception e) {
			logError("Failed to update the record details - " 
					+ e.getMessage());
			return false;
		}	
	}
	
	/**
	 * This method is used to click and open Document from form header
	 * @author hingorani_a
	 * @param fileName The file to be clicked/opened
	 * @return boolean This returns true after file is clicked
	 */
	public boolean clickAndOpenHeaderDoc(String fileName) {
		
		try {
			
			for(WebElement doc : HeaderDocLnk) {
				controlActions.WaitforelementToBeClickable(doc);
				if(doc.getText().contains(fileName)) {
					controlActions.perform_scrollToElement_ByElement(doc);
					doc.click();
					Sync();
					logInfo("Clicked on document named " + fileName);
					return true;
				}
			}
			
			logError("Could not click on document named " + fileName);
			return false;
		}
		catch(Exception e) {
			logError("Failed to click on document named - " + fileName
					+ e.getMessage());
			return false;
		}	
	}
	
	/**
	 * This method is used to click and download Document for a field in form
	 * @author hingorani_a
	 * @param fieldName The name of field to which Attachment belongs
	 * @param fileName The file to be downloaded
	 * @return boolean This returns true after file is downloaded
	 */
	public boolean downloadAttachmentForField(String fieldName, String fileName) {
		List<WebElement> AttchmntFieldVal = null;
		try {
			
			AttchmntFieldVal = controlActions.perform_GetListOfElementsByXPath(FSQABrowserRecordsPageLocators.ATTCHMNT_FIELD_VAL, 
					"FIELD_NAME", fieldName);
			
			for(WebElement doc : AttchmntFieldVal) {
				controlActions.WaitforelementToBeClickable(doc);
				if(doc.getText().contains(fileName)) {
					controlActions.perform_scrollToElement_ByElement(doc);
					doc.click();
					Sync();
					
					logInfo("Downloaded document named " + fileName);
					return true;
				}
			}
			
			logError("Could not download document named " + fileName);
			return false;
		}
		catch(Exception e) {
			logError("Failed to download document named - " + fileName
					+ e.getMessage());
			return false;
		}	
	}
	
	/**
	 * This method is used to click and download Document for a field in form
	 * @author hingorani_a
	 * @param fileName The file to be downloaded
	 * @return boolean This returns true after file is downloaded
	 */
	public boolean downloadAttachmentFormLevel(String fileName) {
		try {
			
			for(WebElement doc : FormLevelAttchmntLnk) {
				controlActions.WaitforelementToBeClickable(doc);
				if(doc.getText().contains(fileName)) {
					controlActions.perform_scrollToElement_ByElement(doc);
					doc.click();
					Sync();
					
					logInfo("Downloaded document named " + fileName);
					return true;
				}
			}
			
			logError("Could not download document named " + fileName);
			return false;
		}
		catch(Exception e) {
			logError("Failed to download document named - " + fileName
					+ e.getMessage());
			return false;
		}	
	}
	
	/**
	 * This method is used to verify Task History record details
	 * @author hingorani_a
	 * @param detail Task History detail like date/username/comment/timezone etc to be verified
	 * separated by '|'
	 * @return boolean This returns true after verifying History record details
	 */
	public boolean verifyTaskHistoryPopupDetails(String detail) {
		String[] values;
		int count = 0, valueCount = 0, recordFound = 0;
		try {
			for(WebElement histDetail : TaskHistoryDetails) {
				
				values = CommonMethods.splitAndGetString(detail);
				valueCount = values.length;
				for(int i = 0; i < valueCount; i++) {
					if(controlActions.perform_CheckIfElementTextContains(histDetail, values[i])) {
						logInfo("Verified value " + values[i] + " is present ");
						count++;
					}
					else {
						break;
					}
				}
				
				if(count > 0 && count == valueCount) {
					recordFound++;
				}
				count = 0;
				valueCount = 0;	
			}
			if(recordFound>0 ) {
				logInfo("Verified Task History details on popup for " + recordFound + " history record");
				return true;
			}
			else {
				logError("Could not verify Task History Popup details");
				return false;
			}
		}
		catch(Exception e) {
			logError("Failed to verify Task History Details - "
					+ e.getMessage());
			return false;
		}	
	}
	
	/**
	 * This method is used to open and verify mentioned Task History details 
	 * @author hingorani_a
	 * @param histDetail A List of details to be verified with respect to Task History
	 * @return boolean This returns true after verifying history details and closes the popup as well
	 */
	public boolean verifyTaskHistoryPopupDetails(List<String> histDetail) {
		int count = 0;
		
		try {
			
			if(!clickHistoryIcon()) {
				return false;
			}

			for (String detail : histDetail) {

				if(!verifyTaskHistoryPopupDetails(detail)) 
					return false;
				else 
					count++;
			}
			
			if(!closeHistoryPopup()) {
				return false;
			}

			if(count==histDetail.size()) {
				logInfo("Verified history values");
				return true;
			}
			else {
				logInfo("Could not verify history values");
				return false;
			}
		}
		catch(Exception e) {
			logError("Failed to verify history details - "
					+ e.getMessage());
			return false;
		}
		finally {
			if(controlActions.isElementDisplayed(CloseHistoryPopup)) {
				closeHistoryPopup();
			}
		}
	}
	
	/**
	 * This method is used to set the Verification and Form by clicking on Save button
	 * @author dahale_p
	 * @date 8 April 2021
	 * @param none 
	 * @return boolean This returns true if save button is clicked successfully
	 */
	public boolean clickRecordHistoryBtn() {
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
	
	/**This method is used to verify whether a record is compliant or not (Fails check setting On for this form).
	 * @author ahmed_tw
	 * @param formName [string] : formName
	 * @param check [String] : 'FAILS' or 'NOT_FAILS' form class FAILS_CHECK
	 * @return [boolean] : True if the passed argument (Fails or Not Fails) to the function is verified, else false;
	 */
	public boolean verifyFailsCheckForRecord(String formName, String check) {
		WebElement failsCheckIcon = null;
		try {
			failsCheckIcon = controlActions.perform_GetElementByXPath(FSQABrowserRecordsPageLocators.RECORD_COMPLIANT_ICON, "FORM_NAME", formName);
			if(check.equals(FAILS_CHECK.FAILS)) {
				if(failsCheckIcon.getAttribute("class").equals(FAILS_CHECK_UI_ICON_CLASS.FAILS_UI_ICON_CLASS)) {
					logInfo("Successfully verified the fails check status for the record as-" + check);
					return true;
				}else {
					logInfo("Fails Check Status not verified as -" + check);
					return false;
				}
			}
			if(check.equals(FAILS_CHECK.NOT_FAILS)) {
				if(failsCheckIcon.getAttribute("class").equals(FAILS_CHECK_UI_ICON_CLASS.NOT_FAILS_UI_ICON_CLASS)) {
					logInfo("Successfully verified the fails check status for the record as-" + check);
					return true;
				}else {
					logInfo("Fails Check Status not verified as -" + check);
					return false;
				}
			}
		} catch (Exception e) {
			logError("Could Not verify Fails Check Status for record with form name -" + formName + e.getMessage());
			return false;
		}
		logInfo("Could Not verify Fails Check Status for record with form name -" + formName);
		return false;
	}
	
	/**
	 * This method is used to verify if the field is Marked as "Required" i,e have * symbol or not while viewing this record.
	 * @author ahmed_tw
	 * @param fieldName - name of the field
	 * @return True is the field is marked as "Required" else false
	 */
	public boolean verifyIsFieldMarkedAsRequired(String fieldName) {
		WebElement reqSymbol = null;
		try {
			reqSymbol = controlActions.perform_GetElementByXPath(FSQABrowserRecordsPageLocators.FIELD_REQUIRED_ASTERISK, "FIELD_NAME", fieldName);
			if(reqSymbol.isDisplayed()) {
				logInfo("Verified that the field -" + fieldName + " is marked as 'Required");
				return true;
			}
			logInfo("The field -" + fieldName + " is OT marked as 'Required'");
			return false;
		} catch (Exception e) {
			logError("Could not verify whether the field -" + fieldName+ " is Marked as 'Required' or Not" + e.getMessage());
			return false;
		}
	}
	
	/**
	 * This method is used to verify if the proper hint is displayed for the field while Viewing the Record
	 * @author ahmed_tw
	 * @param fieldName - Name of the field
	 * @param hint - The hint to be verified
	 * @return True if the hint for the field is verified else false
	 */
	public boolean verifyHintForField(String fieldName, String hint) {
		WebElement displayedHint = null ;
		try {
			displayedHint = controlActions.perform_GetElementByXPath(FSQABrowserRecordsPageLocators.FIELD_HINT, "FIELD_NAME", fieldName);
			
			if(displayedHint.isDisplayed() ) {
				logInfo("Verified that hint is displayed for field -" + fieldName);
			}else {
				logInfo("Hint is NOT displayed for field -" + fieldName);
				return false;
			}
			
			if( displayedHint.getText().equals(hint)) {
				logInfo("Verified that correct hint is displayed for field -" + fieldName);
				return true;
			}else {
				logInfo("Correct hint is NOT displayed for field -" + fieldName);
				return false;
			}
		} catch (Exception e) {
			logError("Could Not verify the display of proper hint for the field -" + fieldName + e.getMessage());
			return false;
		}
		
	}
	
	/**
	 * This Method is used to verify the comment for the field.
	 * @author ahmed_tw
	 * @param fieldName - Name of the field
	 * @param comment - Expected comment for the field
	 * @return True if the comment is verified, else false
	 */
	public boolean verifyCommentForField(String fieldName, String comment) {
		WebElement displayedComment = null ;
		try {
			displayedComment = controlActions.perform_GetElementByXPath(FSQABrowserRecordsPageLocators.FIELD_COMMENT, "FIELD_NAME", fieldName);
			if(displayedComment.getText().equals(comment)) {
				logInfo("Comment -" + comment + " for field -" + fieldName + " is Verified");
				return true;
			}
			
			logInfo("Comment -" + comment + " for field -" + fieldName + " is Not Verified");
			return false;
		} catch (Exception e) {
			logError("Could not verify the comment -" +  comment + " for field -" + fieldName + e.getMessage());
			return false;
		}
	}
	
	/**
	 * This method is used to check if there is a particular attachment file with the field or not.
	 * @author ahmed_tw
	 * @param fieldName - Name of the Field 
	 * @param fileName - Name of the Attachment File
	 * @return True if the attachment is there, else false
	 */
	public boolean verifyAttachmentFileNameForField(String fieldName, String fileName) {
		List<WebElement> displayedListOfAttachments = null ;
		try {
			displayedListOfAttachments = controlActions.perform_GetListOfElementsByXPath(FSQABrowserRecordsPageLocators.FIELD_ATTACHMENTS_NAME, "FIELD_NAME", fieldName);

			for(WebElement element : displayedListOfAttachments) {
				if(element.getText().equals(fileName)) {
					logInfo("Verified that the attachment -" + fileName + " is attached with field -" + fieldName );
					return true;
				}
			}
			logInfo("The attachment -" + fileName + " is NOT attached with field -" + fieldName );
			return false;
		} catch (Exception e) {
			logError("Could NOT Verify that the attachment -" + fileName + " is attached with field -" + fieldName );
			return false;
		}
		
	}
	
	/**
	 * This method is used to verify the correction for the field. 
	 * @author ahmed_tw
	 * @param fieldName - Field Name
	 * @param correction - The Expected correction for that field
	 * @return True if the correction is verified, else false
	 */
	public boolean verifyCorrectionForField(String fieldName, String correction) {
		WebElement displayedCorretion = null ;
		try {
			displayedCorretion = controlActions.perform_GetElementByXPath(FSQABrowserRecordsPageLocators.FIELD_CORRECTION, "FIELD_NAME", fieldName);

			if(displayedCorretion.getText().equals(correction)) {
				logInfo("Correction -" + correction + " for field -" + fieldName + " is Verified");
				return true;
			}
			
			logInfo("Correction -" + correction + " for field -" + fieldName + " is Not Verified");
			return false;
		} catch (Exception e) {
			logError("Could not verify the Correction -" +  correction + " for field -" + fieldName + e.getMessage());
			return false;
		}
	}
	
	/**
	 * This method is used to verify Is Resolved (Mark As Resolvd) Yes or No
	 * @author ahmed_tw
	 * @param fieldName - Name of Field
	 * @param yesOrNo - Set Yes or No value
	 * @return True after verifying the Yes/No value, else false
	 */
	public boolean verifyIsResolvedForField(String fieldName, String yesOrNo) {
		WebElement displayedResolvedValue = null ;
		try {
			displayedResolvedValue = controlActions.perform_GetElementByXPath(FSQABrowserRecordsPageLocators.FIELD_RESOLVED, "FIELD_NAME", fieldName);
			if(displayedResolvedValue.getText().equals(yesOrNo)) {
				logInfo("Is Resolved value -" + yesOrNo + " for field -" + fieldName + " is Verified");
				return true;
			}
			
			logInfo("Is Resolved value -" + yesOrNo + " for field -" + fieldName + " is Not Verified");
			return false;
		} catch (Exception e) {
			logError("Could not verify the Is Resolved -" +  yesOrNo + " for field -" + fieldName + e.getMessage());
			return false;
		}
	}
	
	
	/**This method is used to verify if Min/Max values are correctly displayed along the Numeric Field if 'Show Min/Max' setting is ON for that Numeric Field 
	 * @author ahmed_tw
	 * @param fieldName [String ] : Field Name
	 * @param minValue [String] : Min value
	 * @param maxValue [String] : Max value
	 * @return [boolean] : True if the values are displayed else false.
	 */
	public boolean verifyShowMinMaxForNumField(String fieldName, String minValue, String maxValue) {
		try {
			if(verifyShowMinForNumField(fieldName, minValue)&&verifyShowMaxForNumField(fieldName, maxValue)) {
				logInfo("Verified Min/Max Labels with proper values");
				return true;
			}
		} catch (Exception e) {
			logError("Could NOT verify Min/Max  Labels with proper values" + e.getMessage());
			return false;
		}
		logInfo("Min/Max labels with proper values was NOT verified");
		return false;
	}
	
	/**This method is used to verify if Min value is correctly displayed along the Numeric Field if 'Show Min/Max' setting is ON for that Numeric Field 
	 * @author ahmed_tw
	 * @param fieldNam [String] : Field Name
	 * @param minValue [String] : Min value
	 * @return [boolean] : True if the value is correctly displayed else false.
	 */
	public boolean verifyShowMinForNumField(String fieldName, String minValue) {
		WebElement minLabel = null;
		try {
			minLabel = controlActions.perform_GetElementByXPath(FSQABrowserRecordsPageLocators.MIN_MAX_TARGET_LBL, "MIN_MAX_TARGET", "Min");
			if((minLabel.getText().substring(minLabel.getText().length()-minValue.length())).equals(minValue)) {
				logInfo("Verified Min: label for value -" + minValue);
				return true;
			}
		} catch (Exception e) {
			logError("Could not verify Show Min for Numeric field -" + e.getMessage());
			return false;
		}
		logInfo("Failed verification for Min: label with value -" + minValue);
		return false;
	}
	
	/**This method is used to verify if Max value is correctly displayed along the Numeric Field if 'Show Min/Max' setting is ON for that Numeric Field 
	 * @author ahmed_tw
	 * @param fieldNam [String] : Field Name
	 * @param maxValue [String] : Max value
	 * @return [boolean] : True if the value is correctly displayed else false.
	 */
	public boolean verifyShowMaxForNumField(String fieldName, String maxValue) {
		WebElement maxLabel = null;
		try {
			maxLabel = controlActions.perform_GetElementByXPath(FSQABrowserRecordsPageLocators.MIN_MAX_TARGET_LBL, "MIN_MAX_TARGET", "Max");
			if((maxLabel.getText().substring(maxLabel.getText().length()-maxValue.length())).equals(maxValue)) {
				logInfo("Verified Max: label for value -" + maxValue);
				return true;
			}
		} catch (Exception e) {
			logError("Could not verify Show Max for Numeric field -" + e.getMessage());
			return false;
		}
		logInfo("Failed verification for Max: label with value -" + maxValue);
		return false;
	}
	
	/**This method is used to verify if Target value is correctly displayed along the Numeric Field if 'Show Target' setting is ON for that Numeric Field 
	 * @author ahmed_tw
	 * @param fieldNam [String] : Field Name
	 * @param targetValue [String] : Target value
	 * @return [boolean] : True if the value is correctly displayed else false.
	 */
	public boolean verifyShowTargetForNumField(String fieldName, String targetValue) {
		WebElement targetLabel = null;
		try {
			targetLabel = controlActions.perform_GetElementByXPath(FSQABrowserRecordsPageLocators.MIN_MAX_TARGET_LBL, "MIN_MAX_TARGET", "Target");
			if((targetLabel.getText().substring(targetLabel.getText().length()-targetValue.length())).equals(targetValue)) {
				logInfo("Verified Target: label for value -" + targetValue);
				return true;
			}
		} catch (Exception e) {
			logError("Could not verify Show Target for Numeric field -" + e.getMessage());
			return false;
		}
		logInfo("Failed verification for Target: label with value -" + targetValue);
		return false;
	}
	
	/**This method is used to verify "Show Points Earned" setting i.e Points Earned is visible. 
	 * @author ahmed_tw
	 * @param fieldName [String] : Name of field
	 * @param pointsEarned [String] : Points Earned
	 * @return True after verifying points earned for the field else false.
	 */
	public boolean verifyPointsEarnedForField(String fieldName, String pointsEarned) {
		try {
			WebElement ptsErn = controlActions.perform_GetElementByXPath(FSQABrowserRecordsPageLocators.POINTS_EARNED, "FIELD_NAME", fieldName);
			if(ptsErn.getAttribute("innerText").equals(pointsEarned)) {
				logInfo("Verified Points Earned as -" + pointsEarned + " for field -" + fieldName);
				return true;
			}
			logInfo("Failed verification for Points Earned as -" + pointsEarned + " for field -" + fieldName);
			return false;
			
		} catch (Exception e) {
			logError("Could not verify Points Earned for field -" + fieldName);
			return false;
		}
	}
	
	/**This method is used to verify "Show Points Possible" setting i.e Points Possible is visible. 
	 * @author ahmed_tw
	 * @param fieldName [String] : Name of field
	 * @param pointsPossible [String] : Points Earned
	 * @return True after verifying points possible for the field else false.
	 */
	public boolean verifyPointsPossibleForField(String fieldName, String pointsPossible) {
		try {
			WebElement ptsPsbl = controlActions.perform_GetElementByXPath(FSQABrowserRecordsPageLocators.POINTS_POSSIBLE, "FIELD_NAME", fieldName);
			if(ptsPsbl.getAttribute("innerText").equals(pointsPossible)) {
				logInfo("Verified Points Possible as -" + pointsPossible + " for field -" + fieldName);
				return true;
			}
			logInfo("Failed verification for Points Possible as -" + pointsPossible + " for field -" + fieldName);
			return false;
			
		} catch (Exception e) {
			logError("Could not verify Points Possible for field -" + fieldName);
			return false;
		}
	}
	
	/**This method is used to check is a record is present or not 
	 * @author ahmed_tw
	 * @param formName  [String] : Name of Form
	 * @param resourceInstance  [String] : Name of Resource Instance
	 * @return [boolean] : True if the Record corresponding to that Form Name is present
	 */
	public boolean isRecordPresentInGrid(String formName,String resourceInstance) {
		try {
			String resXpath = FSQABrowserRecordsPageLocators.RECORD_IN_RECORDS_TAB.replaceAll("RES_INST",resourceInstance);
			WebElement formNameOfRecord = controlActions.perform_GetElementByXPath(resXpath, "FORM_NAME", formName);
			
			if(formNameOfRecord.isDisplayed()) {
				logInfo("Verified presence of Record with resource -" + resourceInstance + " and Form -" + formName);
				return true;
			}
			logInfo("Failed to verify presence of Record with resource -" + resourceInstance + " and Form -" + formName);
			return false;
		} catch (Exception e) {
			logError("Could NOT to verify presence of Record with resource -" + resourceInstance + " and Form -" + formName + e.getMessage());
			return false;
		}
	}
	
	/**
	 * This method is used to set Verification on record Sign Off popup
	 * @author hingorani_a
	 * @param verificationName The name of verification 
	 * @return boolean This returns true after setting verification for a record signoff
	 */
	public boolean setVerficationForSignOff(String verificationName) {
		try {
			if(!clickSignRecordLnk()) {
				return false;
			}
			Sync();
			controlActions.clickOnElement(SignOffWithVerificationDdl);
			Sync();
			WebElement SelectVerification = controlActions.perform_GetElementByXPath(FSQABrowserRecordsPageLocators.VERIFICATION_SELECT,"VERIFICATION_NAME",verificationName);
			Sync();
			controlActions.clickOnElement(SelectVerification);
			Sync();
			logInfo("Verification for sign off set to - " + verificationName);
			return true;
		}
		catch(Exception e) {
			logError("Failed to set Verification for sign off - "
					+ e.getMessage());
			return false;
		}	
	}
	
	
	/**
	 * Supporting Classes 
	 */
	
	public static class HistoryDetails{
		public HashMap<String, String> HeaderDetail;
		public List<HistDetailedInfo> detailedInfoList;
		public boolean openHistoryPopup = true;
		public boolean closeHistoryPopup = true;
		public boolean showMoreLink = true;
	}
	
	public static class HistDetailedInfo{
		public String editType;
		public String fieldName;
		// For old new value change
		public String newFieldValue;
		public String oldFieldValue;
	}
	
	public static class HISTORY_EDIT_TYPES{
		// TYPES OF RECORD EDIT
		public static final String CHANGES_ACCEPTED = "Changes Accepted";
		public static final String RECORD_EDITED = "Record Edited";
		public static final String RECORD_SIGNED = "Record Signed";
	}
	
	public static class FIELD_OTHER_TYPES{
		// FIELDS IMPACTED BY RECORD EDIT
		public static final String ATTACHMENT = "Attachment";
		public static final String ADD_REMOVE_ATTCHMNT = "Attachment";
		public static final String RESOLVED = "Resolved";
		public static final String FIELD = "Field";
		public static final String CORRECTION = "Correction";
		public static final String COMMENT = "Comment";
		public static final String COMPLIANCE = "Compliance";
		
	}
	
	public static class UpdateFieldDets{
		public List<RecFieldProps> recFieldProps;
		
		// for future use - need to implement
		public HashMap<String, List<String>>fieldDetails;
		public List<String> fieldData;
		public String locationName;
		public String resourceName;
		public boolean isSubmit = true;
		public boolean formLevelAttachmenttCheck = false;
		public String uploadFormLevelFilePath = filePath;
	}

	public static class RecFieldProps{
		public String fieldName;
		public String fieldValue;
		public String commentsText;
		public String correctionText;
		public String uploadAttachment;
		public boolean yesResolve;
		public boolean noResolve;
		
		// for future use - need to implement
		public String complianceStatus;
		public boolean allowCorrectionsCheck = false;
		public boolean allowPreCorrectionsCheck = false;
		public String allowCorrectionsValue = "1";
		public boolean isResolvedCheck = false;
		public String isResolvedStatus = "Yes";
		public boolean allowCommentsCheck = false;
		public boolean allowAttachmenstCheck = false;
	}
	
	public static class RecordFormDetails{
		public String FormName;
		public String Resource;
		public String Location;
		public String CompletedBy;
		public String SignedBy;
		public String HistoryDetail;
		public String TaskHistoryDetail;
	}
	
	public static class FORMFIELDS{
		public static final String FORMNAME = "Form Name";
		public static final String RESOURCE = "Resource";
		public static final String LOCATION = "Location";
		public static final String COMPLETEDBY = "Completed By";
		public static final String SIGNEDBY = "Signed By";	
		public static final String VALIDATIONLOCK = "Validation Lock";	
	}
	
	public static class FIELDTYPE{
		public static final String FREETEXT = "FreeText";
		public static final String PARAGRAPH = "Paragraph";
		public static final String SELECTONE = "SelectOne";
		public static final String SELECTMULTIPLE = "SelectMultiple";
		public static final String NUMERIC = "Numeric";
		public static final String DATE = "Date";
		public static final String TIME = "Time";
		public static final String DATETIME = "DateTime";
	}
	
	public static class MONTH{
		public static final String JAN = "JAN";
		public static final String FEB = "FEB";
		public static final String MAR = "MAR";
		public static final String APR = "APR";
		public static final String MAY = "MAY";
		public static final String JUN = "JUN";
		public static final String JUL = "JUL";
		public static final String AUG = "AUG";
		public static final String SEP = "SEP";
		public static final String OCT = "OCT";
		public static final String NOV = "NOV";
		public static final String DEC = "DEC";
	}
	
	public static class MONTH_NUM{
		public static final String JAN = "01";
		public static final String FEB = "02";
		public static final String MAR = "03";
		public static final String APR = "04";
		public static final String MAY = "05";
		public static final String JUN = "06";
		public static final String JUL = "07";
		public static final String AUG = "08";
		public static final String SEP = "09";
		public static final String OCT = "10";
		public static final String NOV = "11";
		public static final String DEC = "12";
	}
	
	public static class GROUPBY_OPTION{
		public static final String RESOURCE = "Resource";
		public static final String FORMNAME = "Form Name";
		public static final String COMPLETED_BY = "Completed By";
		public static final String COMPLETED_ON = "Completed On";
	}
	
	public static class FAILS_CHECK{
		public static final String FAILS = "Fails";
		public static final String NOT_FAILS = "Not Fails";
	}
	
	public static class FAILS_CHECK_UI_ICON_CLASS{
		public static final String FAILS_UI_ICON_CLASS =  "fa fa-times" ;
		public static final String NOT_FAILS_UI_ICON_CLASS = "fa fa-check";
	}
}

