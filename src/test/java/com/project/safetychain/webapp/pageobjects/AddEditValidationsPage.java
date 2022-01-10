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

import com.project.utilities.CommonMethods;
import com.project.utilities.ControlActions;

public class AddEditValidationsPage extends CommonPage{

	WebDriver driver;
	WebDriverWait wait;
	ControlActions controlActions;

	public AddEditValidationsPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 40);
		controlActions = new ControlActions(driver);	
	}	

	/**
	 * Page Objects
	 */


	@FindBy(id = AddEditValidationsPageLocators.CLOSE_VLDTN_BTN)
	public WebElement CloseVldtnBtn;

	@FindBy(id = AddEditValidationsPageLocators.REVALIDATE_VLDTN_BTN)
	public WebElement RevalidateVldtnBtn;

	@FindBy(id = AddEditValidationsPageLocators.COMPLETE_VLDTN_DETS)
	public WebElement CompleteVldtnDets;
	
	@FindBy(id = AddEditValidationsPageLocators.DELETE_VLDTN_BTN)
	public WebElement DeleteVldtnBtn;
	
	@FindBy(id = AddEditValidationsPageLocators.COPY_VLDTN_BTN)
	public WebElement CopyVldtnBtn;
	
	@FindBy(id = AddEditValidationsPageLocators.CLEAR_VLDTN_BTN)
	public WebElement ClearVldtnBtn;
	
	@FindBy(id = AddEditValidationsPageLocators.UPDATE_VLDTN_BTN)
	public WebElement UpdateVldtnBtn;
	
	@FindBy(id = AddEditValidationsPageLocators.VLDTN_TITLE)
	public WebElement VldtnTitle;
	
	@FindBy(id = AddEditValidationsPageLocators.VLDTN_TYPE_VAL)
	public WebElement VldtnTypeVal;
	
	@FindBy(id = AddEditValidationsPageLocators.VLDTN_LOCATION_VAL)
	public WebElement VldtnLocationVal;
	
	@FindBy(id = AddEditValidationsPageLocators.VLDTN_RESOURCE_VAL)
	public WebElement VldtnResourceVal;
	
	@FindBy(className = AddEditValidationsPageLocators.VLDTN_SUBDETS_TITLES)
	public List<WebElement> VldtnSubdetsTitles;
	
	@FindBy(xpath = AddEditValidationsPageLocators.NAME_TXT)
	public WebElement NameTxt;
	
	@FindBy(xpath = AddEditValidationsPageLocators.START_DATE_TXT)
	public WebElement StartDateTxt;
	
	@FindBy(xpath = AddEditValidationsPageLocators.END_DATE_TXT)
	public WebElement EndDateTxt;

	@FindBy(xpath = AddEditValidationsPageLocators.IDENTIFIER_NAME_TXT)
	public List<WebElement> IdentifierNameTxt;
	
	@FindBy(xpath = AddEditValidationsPageLocators.IDENTIFIER_VALUE_TXT)
	public List<WebElement> IdentifierValueTxt;
	
	@FindBy(xpath = AddEditValidationsPageLocators.COMPLETE_VLDTN_POPUP_TXA)
	public WebElement CompleteVldtnPopupTxa;
	
	@FindBy(xpath = AddEditValidationsPageLocators.COMPLETE_VLDTN_POPUP_BTN)
	public WebElement CompleteVldtnPopupBtn;
	
	@FindBy(xpath = AddEditValidationsPageLocators.REVALIDATE_VLDTN_POPUP_BTN)
	public WebElement RevalidateVldtnPopupBtn;
	
	@FindBy(xpath = AddEditValidationsPageLocators.CANCEL_VLDTN_POPUP_BTN)
	public WebElement CancelVldtnPopupBtn;
	
	@FindBy(xpath = AddEditValidationsPageLocators.RSLT_SUMM_POPUP_LBL)
	public WebElement RsltSummPopupLbl;
	
	@FindBy(xpath = AddEditValidationsPageLocators.VLDTN_RSLT_POPUP_LBL)
	public WebElement VldtnRsltPopupLbl;
	
	@FindBy(xpath = AddEditValidationsPageLocators.CMPLT_VLDTN_OPTNS)
	public List<WebElement> CmpltVldtnOptns;
	
	@FindBy(xpath = AddEditValidationsPageLocators.REQD_VLDTN_LBL)
	public List<WebElement> ReqdVldtnLbl;
	
	@FindBy(xpath = AddEditValidationsPageLocators.VLDTN_DESCPTN_LBL)
	public WebElement VldtnDescptnLbl;
	
	@FindBy(xpath = AddEditValidationsPageLocators.VLDTN_FILTER_LBL)
	public List<WebElement> VldtnFilterLbl;
	
	@FindBy(xpath = AddEditValidationsPageLocators.COMMON_POPUP_LST)
	public List<WebElement> CommonPopupLst;
	
	@FindBy(xpath = AddEditValidationsPageLocators.DISPLYD_FORMS_LBL)
	public List<WebElement> DisplydFormsLbl;
	
	@FindBy(xpath = AddEditValidationsPageLocators.COMPLETE_VLDTN_BTN)
	public WebElement CompleteVldtnBtn;
	
	@FindBy(xpath = AddEditValidationsPageLocators.ADD_VLDTN_BTN)
	public WebElement AddVldtnBtn;
	
	@FindBy(xpath = AddEditValidationsPageLocators.REVLDTN_POPUP_MSG)
	public WebElement RevldtnPopupMsg;	
	
	@FindBy(xpath = AddEditValidationsPageLocators.CANCEL_REVALIDATE_POPUP_BTN)
	public WebElement CancelRevalidatePopupMsg;		
	
	@FindBy(xpath = AddEditValidationsPageLocators.HISTORY_DETAILS)
	public List<WebElement> HistoryDetails;
	
	@FindBy(xpath = AddEditValidationsPageLocators.FORM_REQD_MSG)
	public WebElement FormReqdMsg;
	
	@FindBy(xpath = AddEditValidationsPageLocators.DELETE_VLDTN_POPUP_BTN)
	public WebElement DeleteVldtnPopupBtn;
	
	/**
	 * Functions
	 */

	/**
	 * This method is used to click on Close Validation button
	 * @author hingorani_a
	 * @param none 
	 * @return ValidationsPage This returns object with error variable as false
	 * if Close Validation button is clicked successfully.
	 */
	public ValidationsPage clickCloseValidation() {
		ValidationsPage vp = new ValidationsPage(driver);
		try {
			controlActions.click(CloseVldtnBtn);
			Sync();
			logInfo("Close Validation button is clicked");
			return vp;
		}
		catch(Exception e) {
			logError("Failed while clicking on Close Validation button - "
					+ e.getMessage());
			vp.error = true;
			return vp;
		}	
	}
	
	/**
	 * This method is used to click on Add Validation button
	 * @author hingorani_a
	 * @param none 
	 * @return boolean This returns as true if Add Validation button is clicked successfully.
	 */
	public boolean clickAddValidation() {
		try {
			controlActions.click(AddVldtnBtn);
			Sync();
			logInfo("Add Validation button is clicked");
			return true;
		}
		catch(Exception e) {
			logError("Failed while clicking on Add Validation button - "
					+ e.getMessage());
			return false;
		}	
	}
	
	/**
	 * This method is used to set Validation name
	 * @author hingorani_a
	 * @param name The name for Validation  
	 * @return boolean This returns as true when name is set successfully.
	 */
	public boolean setValidationName(String name) {
		try {
			controlActions.sendKeys(NameTxt, name);
			logInfo("Validation Name is set to - " + name);
			return true;
		}
		catch(Exception e) {
			logError("Failed to set Validation name - " + e.getMessage());
			return false;
		}	
	}
	
	/**
	 * This method is used to set Validation type
	 * @author hingorani_a
	 * @param type Use Class VALIDATION_TYPE to set Validation type  
	 * @return boolean This returns as true when type is set successfully.
	 */
	public boolean setValidationType(String type) {
		String selector, finalXpath = null;
		
		try {
			selector = "#scs-validation-type-input > span";
			finalXpath = controlActions.perform_GetDynamicXPath(AddEditValidationsPageLocators.COMMON_DDL_VALUE, 
					"DDLOPTIONVALUE", type);
			if(!controlActions.setKendoDropDownValue(driver, selector, finalXpath)) {
				return false;
			}
			Sync();
			logInfo("Validation Type is set to - " + type);
			return true;
		}
		catch(Exception e) {
			logError("Failed to set Validation type - " + e.getMessage());
			return false;
		}	
	}
	
	/**
	 * This method is used to set Validation location
	 * @author hingorani_a
	 * @param locationName The location for Validation  
	 * @return boolean This returns as true when location is set successfully.
	 */
	public boolean setValidationLocation(String locationName) {
		String selector, finalXpath = null;
		
		try {
			selector = "#scs-validation-location-input > span";
			finalXpath = controlActions.perform_GetDynamicXPath(AddEditValidationsPageLocators.COMMON_DDL_VALUE, 
					"DDLOPTIONVALUE", locationName);
			if(!controlActions.setKendoDropDownValue(driver, selector, finalXpath)) {
				return false;
			}
			Sync();
			logInfo("Validation Location is set to - " + locationName);
			return true;
		}
		catch(Exception e) {
			logError("Failed to set Validation location - " + e.getMessage());
			return false;
		}	
	}
	
	/**
	 * This method is used to set Validation resource name
	 * @author hingorani_a
	 * @param resourceName The resource name for Validation  
	 * @return boolean This returns as true when resource is set successfully.
	 */
	public boolean setValidationResource(String resourceName) {
		String selector = null;
		
		try {
			selector = "#scs-validation-resource-input > span";

			if(resourceName.equalsIgnoreCase("ANY")) {

				if(!controlActions.JSClick(driver, selector)) {
					return false;
				}

				String selectorForAny = "span[title='ANY']";
				if(!controlActions.JSClick(driver, selectorForAny)) {
					return false;
				}
				Sync();
				logInfo("Validation resource is set to 'ANY'");
				return true;
			}
			else {
				if(!controlActions.setKendoDropDownValue(driver, selector, resourceName, 
						AddEditValidationsPageLocators.ADD_RSRC_VLDTN_POPUP_TXT)) {
					return false;
				}
				Sync();
				logInfo("Validation resource is set to - " + resourceName);
				return true;
			}
		}
		catch(Exception e) {
			logError("Failed to set Validation resource - " + e.getMessage());
			return false;
		}	
	}
	
	/**
	 * This method is used to set Validation forms
	 * @author hingorani_a
	 * @param forms The list of forms for Validation  
	 * @return boolean This returns as true when forms are set successfully.
	 */
	public boolean setValidationForms(List<String> forms) {
		WebElement SelectFormChk = null;
		
		try {
			for(String form : forms) {
				SelectFormChk = controlActions.perform_GetElementByXPath(AddEditValidationsPageLocators.SELECT_FORM_CHK, 
						"FORMNAME", form);
				controlActions.WaitForAnElementToBeClickable(SelectFormChk);
				controlActions.perform_ClickWithJavaScriptExecutor(SelectFormChk);
			}
			logInfo("Validation Forms is set to - " + forms);
			return true;
		}
		catch(Exception e) {
			logError("Failed to set Validation forms - " + e.getMessage());
			return false;
		}	
	}
	
	/**
	 * This method is used to set Validation Start date
	 * @author hingorani_a
	 * @param identifiers The identifier key value pairs for Validation  
	 * @return boolean This returns as true when identifiers are set successfully.
	 */
	public boolean setValidationIdentifiers(HashMap<String,String> identifiers) {
		int count = 0;
		
		try {
			for(Map.Entry<String, String> entry : identifiers.entrySet()) {
				String identifierName = entry.getKey();
				String identifierValue = entry.getValue();
				
				controlActions.sendKeys(IdentifierNameTxt.get(count), identifierName);
				controlActions.sendKeys(IdentifierValueTxt.get(count), identifierValue);
				
				count++;
			}
			logInfo("Validation Identifiers is set to - " + identifiers);
			return true;
		}
		catch(Exception e) {
			logError("Failed to set Validation Identifiers - " + e.getMessage());
			return false;
		}	
	}
	
	/**
	 * This method is used to set details to Validation fields
	 * @author hingorani_a
	 * @param vdp An object of Class VldtnDetsParams, in order to set
	 * different fields like Name, Type, Location, Forms, etc
	 * @return boolean This returns true if Validations details are set
	 */
	public boolean setVldtnDetails(VldtnDetsParams vdp) {
		try {
			if(vdp.Name != null) {
				if(!setValidationName(vdp.Name))
					return false;
			}
			
			if(vdp.Type != null) {
				if(!setValidationType(vdp.Type))
					return false;
			}

			if(vdp.Location != null) {
				if(!setValidationLocation(vdp.Location))
					return false;
			}

			if(vdp.Resource != null) {
				if(!setValidationResource(vdp.Resource))
					return false;
			}
			
			if(vdp.Forms != null) {
				if(!setValidationForms(vdp.Forms))
					return false;
			}

			if(vdp.StartDate != null) {
				if(!setKendoDateTime(StartDateTxt, vdp.StartDate))
				logInfo("Validation Start Date Time is set to - " + vdp.StartDate);
			}

			if(vdp.EndDate != null) {
				setKendoDateTime(EndDateTxt, vdp.EndDate);
				logInfo("Validation End Date Time is set to - " + vdp.EndDate);
			}
			
			if(vdp.Identifiers != null) {
				if(!setValidationIdentifiers(vdp.Identifiers))
					return false;
			}
			
			logInfo("Details set for Validation");
			return true;
		}
		catch(Exception e) {
			logError("Failed to set details for Validation - "
					+ e.getMessage());
			return false;
		}	
	}
	
	/**
	 * This method is used to set details and create Validation 
	 * @author hingorani_a
	 * @param vdp An object of Class VldtnDetsParams, in order to set
	 * different fields like Name, Type, Location, Forms, etc to create Validation
	 * @return boolean This returns true if Validation is created
	 */
	public boolean createAndAddValidation(VldtnDetsParams vdp) {
		try {
			
			if(!setVldtnDetails(vdp)) {
				return false;
			}
			
			if(!clickAddValidation()) {
				return false;
			}
			
			logInfo("Validation name " + vdp.Name + " created/added");
			return true;
		}
		catch(Exception e) {
			logError("Failed to set details for Validation - "
					+ e.getMessage());
			return false;
		}	
	}
	
	/**
	 * This method is used to Complete a validation with Status
	 * @author hingorani_a
	 * @param result Use Class VALIDATION_RESULT to pass result type as either Pass or Fail.
	 * @param summary The note you would like to add while completing validation
	 * @return boolean This returns true if Validation is Completed
	 */
	public boolean completeValidationWithResult(String result, String summary) {
		String selector, finalXpath = null;
		try {
			Sync();
			if(!clickCompleteValidation())
				return false;
			
			selector = "#validationResultType > span";
			finalXpath = controlActions.perform_GetDynamicXPath(AddEditValidationsPageLocators.COMMON_DDL_VALUE, 
					"DDLOPTIONVALUE", result);
			if(!controlActions.setKendoDropDownValue(driver, selector, finalXpath)) {
				return false;
			}
			logInfo("Validation result is set to - " + result);
			
			if(!setComplteVldtnSummaryOnPopup(summary))
				return false;

			if(!clickComplteValidationOnPopup())
				return false;
			
			logInfo("Completed validation");
			return true;
		}
		catch(Exception e) {
			logError("Failed to complete Validation - "
					+ e.getMessage());
			return false;
		}	
	}
	
	/**
	 * This method is used to revalidate a Completed validation
	 * @author hingorani_a
	 * @param none 
	 * @return boolean This returns as true if Completed Validation is revalidated successfully.
	 */
	public boolean revalidateValidation() {
		try {
			controlActions.click(RevalidateVldtnBtn);
			controlActions.click(RevalidateVldtnPopupBtn);
			Sync();
			logInfo("Revalidated validation");
			return true;
		}
		catch(Exception e) {
			logError("Failed to revalidate Validation - "
					+ e.getMessage());
			return false;
		}	
	}
	
	/**
	 * This method is used to input date time to Validation's Kendo DateTime picker
	 * @author hingorani_a
	 * @param element The WebElement to be set with Date Time
	 * @param date The value of Date Time to be set
	 * @return boolean This returns as true if date time value is set successfully.
	 */
	public boolean setKendoDateTime(WebElement element, String date) {
		try {
			String DTParts[]=date.split(" ");
			String Date[]=DTParts[0].split("/");
			String Time[]=DTParts[1].split(":");
//			for(int i = 0; i < 3; i++) {
//	        	if(Date[i].startsWith("0")){
//	        		Date[i]=Date[i].replace("0", "");
//		        }
//		        else{
//		        	Date[i]=Date[i];
//		        }
//	        }
			
			element.click();
			element.sendKeys(Keys.chord(Keys.CONTROL, "a"));
			element.sendKeys(Date[0]);
			element.sendKeys(Date[1]);
			element.sendKeys(Date[2]);
			element.sendKeys(Time[0]);
			element.sendKeys(Time[1]);
			element.sendKeys(DTParts[2]);
			logInfo("Added date time - " + date);
			return true;
		}
		catch(Exception e) {
			logError("Failed to add date time - "
					+ e.getMessage());
			return false;
		}	
	}
	
	/**
	 * This method is used to click on Complete Validation button
	 * @author hingorani_a
	 * @param none 
	 * @return boolean This returns as true if Complete Validation button is clicked successfully.
	 */
	public boolean clickCompleteValidation() {
		try {
			controlActions.perform_waitUntilClickable(CompleteVldtnBtn);
			controlActions.click(CompleteVldtnBtn);
			Sync();
			logInfo("Complete Validation button is clicked");
			return true;
		}
		catch(Exception e) {
			logError("Failed while clicking on Complete Validation button - "
					+ e.getMessage());
			return false;
		}	
	}
	
	/**
	 * This method is used to click on Cancel Validation button
	 * @author hingorani_a
	 * @param none 
	 * @return boolean This returns as true if Cancel Validation button is clicked successfully.
	 */
	public boolean clickCancelCmplteValidation() {
		try {
			controlActions.perform_waitUntilClickable(CancelVldtnPopupBtn);
			controlActions.click(CancelVldtnPopupBtn);
			Sync();
			logInfo("Cancel Validation button is clicked");
			return true;
		}
		catch(Exception e) {
			logError("Failed while clicking on Cancel Validation button - "
					+ e.getMessage());
			return false;
		}	
	}
	
	/**
	 * This method is used to get count of options available to Complete Validation
	 * @author hingorani_a
	 * @param none 
	 * @return int This returns value of options found 
	 */
	public int verifyCmplteValidationOptions() {
		String selector = null;
		int columnIndex = 0;
		
		try {
			
			selector = "#validationResultType > span";
			controlActions.JSClick(driver, selector);
			Sync();
			for(WebElement option : CmpltVldtnOptns) {
				if(controlActions.perform_CheckIfElementTextContains(option, VALIDATION_RESULT.PASS)) 
				{
					columnIndex++;
				}
				else if(controlActions.perform_CheckIfElementTextContains(option, VALIDATION_RESULT.FAIL)) 
				{
					columnIndex++;
				}
			}	
			
			logInfo("Verified options count as " + columnIndex);
			return columnIndex;
		}
		catch(Exception e) {
			logError("Failed to verify options count "
					+ e.getMessage());
			return columnIndex;
		}	
	}
	
	/**
	 * This method is used to click on Complete button on Complete validation popup
	 * @author hingorani_a
	 * @param none 
	 * @return boolean This returns as true if Complete button is clicked successfully.
	 */
	public boolean clickComplteValidationOnPopup() {
		try {
			CompleteVldtnPopupBtn.click();
			Sync();
			logInfo("Clicked on complete validation on popup");
			return true;
		}
		catch(Exception e) {
			logError("Failed to click on complete validation on popup - "
					+ e.getMessage());
			return false;
		}	
	}
	
	/**
	 * This method is used to set Summary on Complete validation popup
	 * @author hingorani_a
	 * @param summaryText Text to be set as summary 
	 * @return boolean This returns as true if Summary is set successfully.
	 */
	public boolean setComplteVldtnSummaryOnPopup(String summaryText) {
		try {
			CompleteVldtnPopupTxa.clear();
			CompleteVldtnPopupTxa.sendKeys(summaryText);
			logInfo("Have set Summary on complete validation popup");
			return true;
		}
		catch(Exception e) {
			logError("Failed to set Summary on complete validation popup - "
					+ e.getMessage());
			return false;
		}	
	}
	
	/**
	 * This method is used to click Clear validations details to bring it back to it's old state
	 * @author hingorani_a
	 * @param none 
	 * @return boolean This returns as true if Clear Validation button is clicked successfully.
	 */
	public boolean clickClearValidation() {
		try {
			ClearVldtnBtn.click();
			Sync();
			logInfo("Clicked on Clear button");
			return true;
		}
		catch(Exception e) {
			logError("Failed to click on Clear button - "
					+ e.getMessage());
			return false;
		}	
	}
	
	/**
	 * This method is used to click on Cancel button on Revalidation popup
	 * @author hingorani_a
	 * @param none 
	 * @return boolean This returns as true if Cancel button is clicked successfully.
	 */
	public boolean clickCancelRevalidatnPopup() {
		try {
			controlActions.click(CancelRevalidatePopupMsg);
//			Sync();
			logInfo("Clicked on Cancel button on revalidation popup");
			return true;
		}
		catch(Exception e) {
			logError("Failed to click on Cancel button on revalidation popup - "
					+ e.getMessage());
			return false;
		}	
	}
	
	/**
	 * This method is used to click on Revalidate button for a Completed validation
	 * @author hingorani_a
	 * @param none 
	 * @return boolean This returns as true if Revalidate button is clicked successfully.
	 */
	public boolean clickRevalidateValidation() {
		try {
			controlActions.click(RevalidateVldtnBtn);
//			Sync();
			logInfo("Clicked on Revalidate button for complete validation");
			return true;
		}
		catch(Exception e) {
			logError("Failed to click on Revalidate button for complete validation - "
					+ e.getMessage());
			return false;
		}	
	}
	
	/**
	 * This method is used to click Update validations details to bring it back to it's old state
	 * @author hingorani_a
	 * @param none 
	 * @return boolean This returns as true if Update Validation button is clicked successfully.
	 */
	public boolean clickUpdateValidation() {
		try {
			UpdateVldtnBtn.click();
			Sync();
			logInfo("Clicked on Update button");
			return true;
		}
		catch(Exception e) {
			logError("Failed to click on Update button - "
					+ e.getMessage());
			return false;
		}	
	}
	
	/**
	 * This method is used to select a Validation tab like Details, History
	 * @author hingorani_a
	 * @param tabName Use class VALIDATION_TABS to set which tab to be selected
	 * @return boolean This returns as true after selecting mentioned tab successfully
	 */
	public boolean selectValidationTab(String tabName) {
		WebElement SetVldtnTab = null;
		try {
			SetVldtnTab = controlActions.perform_GetElementByXPath(AddEditValidationsPageLocators.SET_VLDTN_TAB, 
					"TABNAME", tabName);
			SetVldtnTab.click();
			Sync();
			logInfo("Clicked on " + tabName + " Validation tab");
			return true;
		}
		catch(Exception e) {
			logError("Failed to click on " + tabName + " Validation tab"
					+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to verify Validation History details
	 * @author hingorani_a
	 * @param detail History detail like date/first name/event/timezone etc to be verified
	 * @return boolean This returns true after verifying History details
	 */
	public boolean verifyHistoryDetails(List<String> details) {
		String[] values;
		int count = 0, valueCount = 0, recordFound = 0;
		try {
			for(String detail : details) {
				values = CommonMethods.splitAndGetString(detail);
				valueCount = values.length;
				for(WebElement histDetail : HistoryDetails) {
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
//					valueCount = 0;	
				}
			}
			
			if(recordFound>0 && recordFound==details.size()) {
				logInfo("Verified History details for " + recordFound + " - validation event");
				return true;
			}
			else {
				logError("Could not verify History details");
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
	 * This method is used to get count of options available for Validation Type
	 * @author hingorani_a
	 * @param none 
	 * @return int This returns value of options found 
	 */
	public int verifyValidationTypeOptions() {
		String selector = null;
		int columnIndex = 0;
		
		try {
			
			selector = "#scs-validation-type-input > span";
			controlActions.JSClick(driver, selector);
			Sync();
			for(WebElement option : CmpltVldtnOptns) {
				if(controlActions.perform_CheckIfElementTextContains(option, VALIDATION_TYPE.NEW_PROCEDURES)) 
				{
					columnIndex++;
				}
				else if(controlActions.perform_CheckIfElementTextContains(option, VALIDATION_TYPE.NEW_PRODUCT)) 
				{
					columnIndex++;
				}
				else if(controlActions.perform_CheckIfElementTextContains(option, VALIDATION_TYPE.PROCESS_VIOLATION)) 
				{
					columnIndex++;
				}
				else if(controlActions.perform_CheckIfElementTextContains(option, VALIDATION_TYPE.NEW_INGREDIENT)) 
				{
					columnIndex++;
				}
				else if(controlActions.perform_CheckIfElementTextContains(option, VALIDATION_TYPE.NEW_EQUIPMENT)) 
				{
					columnIndex++;
				}
				else if(controlActions.perform_CheckIfElementTextContains(option, VALIDATION_TYPE.NEW_PROCESS)) 
				{
					columnIndex++;
				}
				else if(controlActions.perform_CheckIfElementTextContains(option, VALIDATION_TYPE.PROGRAM_VIOLATION)) 
				{
					columnIndex++;
				}
			}	
			
			logInfo("Verified options count as " + columnIndex);
			return columnIndex;
		}
		catch(Exception e) {
			logError("Failed to verify options count "
					+ e.getMessage());
			return columnIndex;
		}	
	}
	
	/**
	 * This method is used to click Copy validations details to create a copy
	 * @author hingorani_a
	 * @param none 
	 * @return boolean This returns as true if Copy Validation button is clicked successfully.
	 */
	public boolean clickCopyValidation() {
		try {
			CopyVldtnBtn.click();
			Sync();
			logInfo("Clicked on Copy button");
			return true;
		}
		catch(Exception e) {
			logError("Failed to click on Copy button - "
					+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to click on Delete validations button
	 * @author hingorani_a
	 * @param none 
	 * @return boolean This returns as true if Delete Validation button is clicked successfully.
	 */
	public ValidationsPage clickDeleteValidation() {
		ValidationsPage vp = new ValidationsPage(driver);

		try {
			DeleteVldtnBtn.click();
			DeleteVldtnPopupBtn.click();
			Sync();
			logInfo("Clicked on Delete button");
			return vp;
		}
		catch(Exception e) {
			logError("Failed to click on Delete button - "
					+ e.getMessage());
			vp.error = true;
			return vp;
		}	
	}
	
	public static class VldtnDetsParams{
		public String Name;
		public String Type;
		public String Location;
		public String Resource;
		public List<String> Forms;
		public String StartDate;
		public String EndDate;
		public HashMap<String,String> Identifiers;
	}
	
	public static class VALIDATION_RESULT{
		public static final String PASS = "Pass";
		public static final String FAIL = "Fail";
	}
	
	public static class VALIDATION_TYPE{
		public static final String NEW_PROCEDURES = "New Procedures";
		public static final String NEW_PRODUCT = "New Product";
		public static final String PROCESS_VIOLATION = "Process Violation";
		public static final String NEW_INGREDIENT = "New Ingredient";	
		public static final String NEW_EQUIPMENT = "New Equipment";
		public static final String NEW_PROCESS = "New Process";
		public static final String PROGRAM_VIOLATION = "Program Violation";
	}
	
	public static class VALIDATION_TABS{
		public static final String DETAILS = "Details";
		public static final String HISTORY = "History";
	}
	
	public static class VALIDATION_EVENTS{
		public static final String VALIDATION_CREATED = "Validation Created";
		public static final String VALIDATION_COMPLETE = "Validation Complete";
		public static final String REVALIDATION_CREATED = "Revalidation Created";
	}
}
