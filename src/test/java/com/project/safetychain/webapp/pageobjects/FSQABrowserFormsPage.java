package com.project.safetychain.webapp.pageobjects;

import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FIELD_TYPES;
import com.project.utilities.ControlActions;
import com.project.utilities.enums.WaitType;

public class FSQABrowserFormsPage extends CommonPage{

	public static String fileName = "ABC1.csv";
	public static String filePath = System.getProperty("user.dir") + "\\test-data-files\\UploadDocuments\\"+fileName; 

	public FSQABrowserFormsPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 40);
		controlActions = new ControlActions(driver);	
	}


	@FindBy(className = FSQABrowserFormsPageLocators.SUBMIT_FRM_POPUP_MSG)
	public WebElement SubmitFrmPopupMsg;

	@FindBy(xpath = FSQABrowserFormsPageLocators.FORM_SELECTION)
	public WebElement SelectForm;

	@FindBy(xpath = FSQABrowserFormsPageLocators.FORM_LEVEL_ATTACHMENT)
	public WebElement FormLevelAttachment;

	@FindBy(xpath = FSQABrowserFormsPageLocators.FORM_LEVEL_ATTACHMENT_CLOSE_BTN)
	public WebElement FormLevelAttachmentCloseBtn;

	@FindBy(xpath = FSQABrowserFormsPageLocators.FORM_LEVEL_ATTACHMENT_SELECT_BTN)
	public WebElement FormLevelAttachmentSelectBtn;

	@FindBy(xpath = FSQABrowserFormsPageLocators.FORM_LEVEL_ATTACHMENT_INP)
	public WebElement FormLevelAttachmentInp;

	@FindBy(xpath = FSQABrowserFormsPageLocators.FORM_LEVEL_ATTACHMENT_STATUS)
	public WebElement FormLevelAttachmentStatus;

	@FindBy(xpath = FSQABrowserFormsPageLocators.FORM_LEVEL_ATTACHMENT_RETRY)
	public WebElement FormLevelAttachmentRetry;


	@FindBy(xpath = FSQABrowserFormsPageLocators.ALL_FIELDS)
	List<WebElement> AllFieldsLst;

	@FindBy(xpath = FSQABrowserFormsPageLocators.SUBMIT_FORM_BTN)
	public WebElement SubmitFormBtn;

	@FindBy(xpath = FSQABrowserFormsPageLocators.SUBMIT_REPEAT_BUTTON)
	public WebElement SubmitRepeatFormBtn;

	@FindBy(xpath = FSQABrowserFormsPageLocators.POPUP_OK_BTN)
	public WebElement PopUpOkBtn;

	@FindBy(xpath = FSQABrowserFormsPageLocators.SUBMISSION_POP_UP_OK_BTN)
	public WebElement SubmissionOKBtn;

	@FindBy(xpath = FSQABrowserFormsPageLocators.SAVE_BTN)
	public WebElement SaveBtn;

	@FindBy(xpath = FSQABrowserFormsPageLocators.NON_COMPLIANCE)
	public WebElement NonCompliance;

	@FindBy(xpath = FSQABrowserFormsPageLocators.COMPLIANCE)
	public WebElement Compliance;

	@FindBy(xpath = FSQABrowserFormsPageLocators.FORM_CANCEL_BTN)
	public WebElement FormCancelBtn;

	@FindBy(xpath = FSQABrowserFormsPageLocators.LOCATION_DDL)
	public WebElement LocationDdl;

	@FindBy(xpath = FSQABrowserFormsPageLocators.SELECT_RESOURCE_DDL)
	public WebElement SelectResourceDdl;

	@FindBy(xpath = FSQABrowserFormsPageLocators.SELCTED_LOCATION_LBL)
	public WebElement SelectedLocationLbl;

	@FindBy(xpath = FSQABrowserFormsPageLocators.SELCTED_RESOURCE_LBL)
	public WebElement SelectedResourceLbl;

	@FindBy(xpath = FSQABrowserFormsPageLocators.SUBMIT_FORM_POPUP_MSG)
	public WebElement SubmitFormPopupMsg;

	@FindBy(xpath = FSQABrowserFormsPageLocators.EXIST_CORRECTION_TXA)
	public WebElement ExistCorrectionTxa;

	@FindBy(xpath = FSQABrowserFormsPageLocators.INCOMPLETE_FORM_SUBMIT_ALERT)
	public WebElement IncompleteFormSubmitAlert;

	@FindBy(xpath = FSQABrowserFormsPageLocators.INCOMPLETE_FORM_SUBMIT_ALERT_OKBTN)
	public WebElement IncompleteFormSubmitAlertOkBtn;

	@FindBy(xpath = FSQABrowserFormsPageLocators.FORM_RESOURCE_LST)
	public List<WebElement> FormResourceLst;

	@FindBy(xpath = FSQABrowserFormsPageLocators.DELETE_SAVED_FORM_ICON)
	public WebElement DeleteSavedFormIcon;

	@FindBy(xpath = FSQABrowserFormsPageLocators.DELETE_SAVED_FORM_POP_UP_YES_BTN)
	public WebElement DeleteFormYesBtn;
	
	@FindBy(xpath = FSQABrowserFormsPageLocators.NO_DATA_FOUND)
	public WebElement NoDataFound;
	
	@FindBy(xpath = FSQABrowserFormsPageLocators.DROPDOWN_LIST_FILTER)
	public WebElement DropDownListFilter;

	/** 'setPredefinedCorrectionDropdown' method is used to set the value in 'Corrections' drop-down
	 * @author pashine_a
	 * @param WebElement - 'correctionsInput'
	 * @param int - 'optionNumber'
	 * @return boolean status
	 * IF value in set in 'Corrections' dropdown THEN true ELSE false
	 */	
	public boolean setPredefinedCorrectionDropdown(WebElement correctionsInput, int optionNumber) {
		try {
			for(int i=0;i<optionNumber;i++) {
				controlActions.sendKey(correctionsInput, Keys.ARROW_DOWN);
			}
			logInfo("Option number - "+optionNumber+" setted in 'Corrections(Predefined Correction)' dropdown");
			return true;
		}catch(Exception e) {
			logError("Failed to set value in Corrections - "+e.getMessage());
			return false;
		}
	}

	/** 'setCorrectionTextarea' method is used to set the value in 'Corrections' text area box
	 * @author pashine_a
	 * @param WebElement - 'correctionsBox'
	 * @return boolean status
	 * IF value in set in 'Corrections' text area box THEN true ELSE false
	 */	
	public boolean setCorrectionTextarea(WebElement correctionsBox) {
		try {
			controlActions.sendKeys(correctionsBox, "Test Corrections");
			logInfo("Value set in 'Corrections'");
			return true;
		}catch(Exception e) {
			logError("Failed to set value in Corrections - "+e.getMessage());
			return false;
		}
	}

	/** 'expandPlus' method is used to expand the field's property element(s)
	 * @author pashine_a
	 * @param WebElement - 'expandPlus'
	 * @return boolean status
	 * IF field property is expanded THEN true ELSE false
	 */	
	public boolean expandPlus(WebElement expandPlus) {
		try {
			controlActions.clickElement(expandPlus);
			return true;
		}catch(Exception e) {
			logError("Failed to open field's properties plus - "+e.getMessage());
			return false;
		}
	}

	/** 'setComments' method is used to set the value in 'Comments' text area box
	 * @author pashine_a
	 * @param WebElement - 'commentBox'
	 * @return boolean status
	 * IF value in set in 'Comments' text area box THEN true ELSE false
	 */	
	public boolean setComments(WebElement commentBox) {
		try {
			controlActions.sendKeys(commentBox, "Test Comments");
			logInfo("Value setted in 'Comments'");
			return true;
		}catch(Exception e) {
			logError("Failed to set value in Comments - "+e.getMessage());
			return false;
		}
	}

	/** 'addAttachment' method is used to upload the file in field level attachment
	 * @author pashine_a
	 * @param String - 'fieldID'
	 * @return boolean status
	 * IF file is added in attachments THEN true ELSE false
	 */	
	public boolean addAttachment(String fieldID) {
		WebElement element;
		String uploadedAttachmentPath;
		try {
			element = controlActions.perform_GetElementByXPath(FSQABrowserFormsPageLocators.FIELDS_ATTACHMENTS_INP, "FIELDID", fieldID);
			controlActions.sendKeys(element, filePath);
			logInfo("Uploading the attchment");
			threadsleep(8000);
			uploadedAttachmentPath = controlActions.perform_GetDynamicXPath(FSQABrowserFormsPageLocators.UPLOADED_ATTACHMENT_LNK, "FIELDID", fieldID);
			if(!controlActions.isElementDisplayed(uploadedAttachmentPath)) {
				logError("Verified - Attachment is not uploaded'");
				return false;
			}			
			logInfo("Verified - Attachment is uploaded'");
			return true;
		}catch(Exception e) {
			logError("Failed to add attachment - "+e.getMessage());
			return false;
		}
	}

	/** 'setFieldProperties' method is used to set values in the field's properties
	 * @author pashine_a
	 * @param String - 'fieldID'
	 * @param boolean - 'allProperties'
	 * @return boolean status
	 * IF all properties are setted successfully THEN true ELSE false
	 */	
	public boolean setFieldProperties(String fieldID, boolean allProperties, String complianceStatus) {
		String checkPredefinedCorrectionPath, checkCorrectionPath, expandPlusPath, attachmentPath, commentPath ;
		boolean correctionsPredefinedAvail,correctionsAvail,expandAvail, attachAvail, commentAvail;
		try {
			if(complianceStatus.equalsIgnoreCase("Non-Compliant")) {
				checkPredefinedCorrectionPath = controlActions.perform_GetDynamicXPath(FSQABrowserFormsPageLocators.CORRECTIONS_PREDEIFNED_DDL, "FIELDID", fieldID);
				correctionsPredefinedAvail= controlActions.isElementDisplayed(checkPredefinedCorrectionPath);
				if(correctionsPredefinedAvail) {
					if(!setPredefinedCorrectionDropdown(controlActions.perform_GetElementByXPath(checkPredefinedCorrectionPath),2)) {
						return false;
					}
				}else{
					checkCorrectionPath = controlActions.perform_GetDynamicXPath(FSQABrowserFormsPageLocators.CORRECTIONS_TXA, "FIELDID", fieldID);
					correctionsAvail= controlActions.isElementDisplayed(checkCorrectionPath);
					if(correctionsAvail) {
						if(!setCorrectionTextarea(controlActions.perform_GetElementByXPath(checkCorrectionPath))) {
							return false;
						}
					}
				}
			}

			if(allProperties==true) {
				expandPlusPath = controlActions.perform_GetDynamicXPath(FSQABrowserFormsPageLocators.FIELDS_SETTINGS_STATUS, "FIELDID", fieldID);
				expandAvail= controlActions.isElementDisplayed(expandPlusPath);
				if(expandAvail) {
					if(!expandPlus(controlActions.perform_GetElementByXPath(expandPlusPath))) {
						return false;
					}
				}
				attachmentPath = controlActions.perform_GetDynamicXPath(FSQABrowserFormsPageLocators.FIELDS_ATTACHMENTS, "FIELDID", fieldID);
				attachAvail= controlActions.isElementDisplayed(attachmentPath);
				if(attachAvail) {
					if(!addAttachment(fieldID)) {
						return false;
					}
				}
				commentPath = controlActions.perform_GetDynamicXPath(FSQABrowserFormsPageLocators.FIELDS_COMMENTS, "FIELDID", fieldID);
				commentAvail= controlActions.isElementDisplayed(commentPath);
				if(commentAvail) {
					if(!setComments(controlActions.perform_GetElementByXPath(commentPath))) {
						return false;
					}
				}
			}
			logInfo("The properties has been set for the field");
			return true;
		}catch(Exception e) {
			logError("Failed to set proprties for the field - "+e.getMessage());
			return false;
		}
	}

	/** 'submitData' method is used to submit the data in the form
	 * @author pashine_a
	 * @param boolean - 'checkProperties'
	 * @param boolean - 'checkCorrections'
	 * @return boolean status
	 * IF data in all fields setted & form is submitted successfully THEN true ELSE false
	 */	
	public boolean submitData(boolean checkProperties, boolean checkCorrections,boolean complianceCheck, boolean isSubmit, boolean isSave) {
		WebElement currentElement,CurrentElementField, CurrentElementFieldStatus, CurrentElementLabel,fieldDetails;
		String fieldType,fieldID,fieldStatusClassName,fieldName, fieldDisplayStatus, complianceStatus = null;

		try {
			int a=11;
			System.out.println("Total fields - "+AllFieldsLst.size());
			for(int i=0;i<AllFieldsLst.size();i++) {
				currentElement = AllFieldsLst.get(i);
				fieldType = currentElement.getAttribute("data-fieldtype");
				fieldID = currentElement.getAttribute("data-field");
				fieldDetails = controlActions.perform_GetElementByXPath(FSQABrowserFormsPageLocators.CHECK_FIELD_DISPLAYED, "FIELDID", fieldID);
				fieldDisplayStatus = fieldDetails.getAttribute("class");
				if(fieldType.equals("Aggregate")) {
					CurrentElementField = controlActions.perform_GetElementByXPath(FSQABrowserFormsPageLocators.FIELDS_TXT, "FIELDID", fieldID);
				}else {
					CurrentElementField = controlActions.perform_GetElementByXPath(FSQABrowserFormsPageLocators.ALL_FIELDS_ID, "FIELDID", fieldID);
				}

				if(fieldType.equals("Paragraph")) {
					CurrentElementFieldStatus = controlActions.perform_GetElementByXPath(FSQABrowserFormsPageLocators.PARAGRAPH_FIELD_STATUS, "FIELDID", fieldID);
					if((CurrentElementFieldStatus.getAttribute("class").toString()).contains("ng-empty")) {
						fieldStatusClassName = "fa fa-circle-o";
					}else {
						fieldStatusClassName = "No Status";
					}
				}else {
					CurrentElementFieldStatus = controlActions.perform_GetElementByXPath(FSQABrowserFormsPageLocators.FIELDS_FILL_STATUS, "FIELDID", fieldID);
					fieldStatusClassName = CurrentElementFieldStatus.getAttribute("class");
				}

				CurrentElementLabel = controlActions.perform_GetElementByXPath(FSQABrowserFormsPageLocators.FIELDS_LBL, "FIELDID", fieldID);
				fieldName = CurrentElementLabel.getText().toString();

				if(fieldDisplayStatus.equals("scs-field ng-hide")) {
					logInfo("Field - '"+fieldName+"' of type - '"+fieldType+"' is hidden");

				}else {
					if(fieldStatusClassName.equals("fa fa-circle-o")){
						if(fieldType.equals("SingleLineText") || fieldType.equals("FreeText")){
							controlActions.sendKeys(CurrentElementField, SubmissionData.textField);
						}
						if(fieldType.equals("Paragraph")){
							controlActions.sendKeys(CurrentElementField, SubmissionData.paragraphField);		
						}
						if(fieldType.equals("SelectOne")){
							CurrentElementField = controlActions.perform_GetElementByXPath(FSQABrowserFormsPageLocators.FIELDS_TXT, "FIELDID", fieldID);
							logInfo("Setting value in 'Select One' field");
							for(int k=0;k<SubmissionData.selectOneFieldOption;k++) {
								controlActions.sendKey(CurrentElementField, Keys.ARROW_DOWN);	
							}
						}
						if(fieldType.equals("SelectMultiple")) {
							CurrentElementField = controlActions.perform_GetElementByXPath(FSQABrowserFormsPageLocators.FIELDS_TXT, "FIELDID", fieldID);
							logInfo("Setting value in 'Select One' field");
							for(int k=0;k<SubmissionData.selectMultipleFieldOption;k++) {
								controlActions.sendKey(CurrentElementField, Keys.ARROW_DOWN);	
							}
							controlActions.sendKey(CurrentElementField, Keys.ENTER);	
						}
						if(fieldType.equals("Numeric")) {
							CurrentElementField = controlActions.perform_GetElementByXPath(FSQABrowserFormsPageLocators.FIELDS_TXT, "FIELDID", fieldID);
							controlActions.sendKeys(CurrentElementField,SubmissionData.numericField);		
						}
						if(fieldType.equals("Date")) {
							controlActions.setDate(CurrentElementField, "Day", 0);
							//controlActions.sendKeys(CurrentElementField, SubmissionData.dateField,"Date field");		
						}
						if(fieldType.equals("Time")) {
							controlActions.setTime(CurrentElementField, 0, 0, true, true);
							//controlActions.sendKeys(CurrentElementField, SubmissionData.timeField, "Time field");		
						}
						if(fieldType.equals("DateTime")) {
							controlActions.setDateTime(CurrentElementField, "Day", 0, 0, 0, true, true);
							//controlActions.sendKeys(CurrentElementField, SubmissionData.dateTimeField, "Date & Time field");		
						}
						if(fieldType.equals("Identifier")) {
							CurrentElementField = controlActions.perform_GetElementByXPath(FSQABrowserFormsPageLocators.FIELDS_TXT, "FIELDID", fieldID);
							//System.out.println(CurrentElementField.getAttribute("placeholder")+"-");
							if(CurrentElementField.getAttribute("placeholder").trim().equalsIgnoreCase("Select...")) {
								logInfo("Setting value in 'Identifier - Select One' field");
								controlActions.sendKey(CurrentElementField, Keys.ARROW_DOWN);	
							}else {
								controlActions.sendKeys(CurrentElementField, SubmissionData.identifierField);	
							}
						}
						logInfo("Value setted for field - '"+fieldName+"' of type - '"+fieldType+"'");
					}else {
						logInfo("Value was already setted for field - '"+fieldName+"' of type - '"+fieldType+"'");
					}
					controlActions.clickElement(CurrentElementLabel);
					Sync();
					threadsleep(2000);
					CurrentElementFieldStatus = controlActions.perform_GetElementByXPath(FSQABrowserFormsPageLocators.FIELDS_FILL_STATUS, "FIELDID", fieldID);
					fieldStatusClassName = CurrentElementFieldStatus.getAttribute("class");
					switch(fieldStatusClassName) {
					case "fa fa-check":
						complianceStatus = "Compliant";
						break;

					case "fa fa-close":
						complianceStatus = "Non-Compliant";
						break;

					default:
						complianceStatus = "Filled";

					}
					logInfo("Field status is - '"+complianceStatus+"'");
					if(complianceCheck==true){
						if(complianceStatus == "Filled") {
							logError("The form has no complaint fields");
							return false;
						}

					}		
					if(checkProperties==true && checkCorrections==true) {
						if(!setFieldProperties(fieldID,true, complianceStatus)) {
							return false;
						}
					}else{
						if(checkCorrections==true) {
							if(!setFieldProperties(fieldID,false, complianceStatus)) {
								return false;
							}
						}
					}
				}
			}

			if(isSubmit) {
				
				controlActions.clickElement(SubmitFormBtn);
				Sync();
				logInfo("Clicked on 'SUBMIT' form button");
				controlActions.clickElement(PopUpOkBtn);
				Sync();
				logInfo("The form is filled & submitted sucessfully");
			}else {
				if(isSave) {
					controlActions.clickElement(SaveBtn);
					Sync();
					logInfo("The form is filled & saved sucessfully");
				}else {
					logInfo("The form is filled sucessfully");
				}
			}
			return true;
		}catch(Exception e) {
			logError("Failed to fill & submit the form - "+e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to save form
	 * @author choubey_a
	 * @param formName is the name of the form to be saved
	 * @boolean will return true when the form will be saved
	 */

	public boolean saveForm(String formName) {
		try {
			WebElement SelectForm = controlActions.perform_GetElementByXPath(FSQABrowserFormsPageLocators.FORM_SELECTION, "FORM_NAME", formName);
			controlActions.clickElement(SelectForm);
			threadsleep(5000);
			controlActions.clickElement(SaveBtn);
			Thread.sleep(2000);
			logInfo("Form saved");
			return true;
		}catch(Exception e) {
			logError("Failed to save form" +e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to save and submit the form 
	 * @author choubey_a
	 * @param formName is the name of the form to be saved and submitted
	 * @boolean will return true when the form will be saved and submitted
	 */

	public boolean saveAndSubmitForm(String formName) {
		try {
			saveForm(formName);
			WebElement SelectSavedForm = controlActions.perform_GetElementByXPath(FSQABrowserFormsPageLocators.SAVED_FORM_DGD,
					"FORM_NAME", formName);
			controlActions.clickElement(SelectSavedForm);
			threadsleep(2000);
			submitData(false,false,false,true,false);
			logInfo("Saved the form and submitted");
			return true;
		}catch(Exception e) {
			logInfo("Failed to save and submit the form" +e.getMessage());
			return false;
		}		
	}

	/**
	 * This method is used to open saved form and submit the form 
	 * @author choubey_a
	 * @param formName is the name of the saved form that to be submitted
	 * @boolean will return true when the saved form will be submitted
	 */
	public boolean openAndSubmitForm(String formName) {
		try {
			WebElement SelectSavedForm = controlActions.perform_GetElementByXPath(FSQABrowserFormsPageLocators.SAVED_FORM_DGD,
					"FORM_NAME", formName);
			controlActions.clickElement(SelectSavedForm);
			threadsleep(2000);
			submitData(false,false,false,true,false);
			logInfo("Saved form is submitted");
			return true;
		}catch(Exception e) {
			logInfo("Failed to submit the saved form" +e.getMessage());
			return false;
		}		
	}

	public static class SubmissionData{
		public static String textField = "Text Field Data";
		public static String paragraphField = "Paragraph Field Data";
		public static int selectOneFieldOption = 1;
		public static int selectMultipleFieldOption = 1;
		public static int numericData = 2;
		public static String numericField = new Integer(numericData).toString();
		public static String dateField = "7/13/2020";
		public static String timeField = "2:30 AM";
		public static String dateTimeField = "7/13/2020 3:00 AM";
		public static String identifierField = "1234";
	}

	/**
	 * This 'selectForm' method is used select & open the form
	 * @author pashine_a
	 * @param formName is the name of the form that will opened
	 * @boolean will return true when the form gets opened
	 */

	public boolean selectOpenForm(String formName) {
		try {
			WebElement SelectForm = controlActions.perform_GetElementByXPath(FSQABrowserFormsPageLocators.FORM_SELECTION, "FORM_NAME", formName);
			controlActions.clickElement(SelectForm);
			threadsleep(4000);
			WebElement verifyOpenedForm = controlActions.perform_GetElementByXPath(FSQABrowserFormsPageLocators.FORM_OPENED, "FORM_NAME", formName);
			if(!controlActions.isElementDisplayedOnPage(verifyOpenedForm)) {
				logError("Failed to Verify opened form");
				return false;
			}
			logInfo("Form is opened");
			return true;
		}catch(Exception e) {
			logError("Failed to select & open the form - "+e.getMessage());
			return false;
		}	
	}

	/**
	 * This 'addFormLevelAttachment' method is add attachment at form level
	 * @author pashine_a
	 * @param null
	 * @boolean will return true when attachment is uploaded
	 */
	public boolean addFormLevelAttachment() {
		try {
			controlActions.clickElement(FormLevelAttachment);
			if(!controlActions.isElementDisplayedOnPage(FormLevelAttachmentSelectBtn)) {
				logError("Failed to find upload attachment option");
				return false;
			}
			controlActions.sendKeys(FormLevelAttachmentInp, filePath);
			logInfo("Uploading the attchment");
			threadsleep(4000);
			String uploadStatus = FormLevelAttachmentStatus.getAttribute("class");
			for(int i=0;i<2;i++) {
				if(uploadStatus.equals("k-file k-file-success")) {
					break;
				}
				if(uploadStatus.equals("k-file k-file-error")) {
					controlActions.clickElement(FormLevelAttachmentRetry);			
				}
				threadsleep(4000);
			}
			if(!uploadStatus.equals("k-file k-file-success")) {
				logError("Failed to upload attachment'");
				return false;
			}			
			logInfo("Verified - Attachment is uploaded'");
			controlActions.clickElement(FormLevelAttachmentCloseBtn);
			return true;
		}catch(Exception e) {
			logError("Failed to upload attachment - "+e.getMessage());
			return false;
		}	
	}

	/**
	 * This 'verifySubmitRepeatValue' method is used "SUBMIT & REPEAT" the form & verify the "Carryover Field" value
	 * @author pashine_a
	 * @param fieldName
	 * @param fieldValue
	 * @boolean will return true if submitted value is carryover in next opened form
	 */
	public boolean verifySubmitRepeatValue(String fieldName,String fieldValue) {
		String carryoverFieldValue;
		WebElement carryoverfield;
		try {
			controlActions.WaitforelementToBeClickable(SubmitRepeatFormBtn);
			SubmitRepeatFormBtn.click();
			Sync();
			SubmissionOKBtn.click();
			//			Sync();
			//			controlActions.clickElement(SubmitRepeatFormBtn);
			//			Sync();
			//			controlActions.clickElement(SubmissionOKBtn);
			//			Sync();
			carryoverfield = controlActions.perform_GetElementByXPath(FSQABrowserFormsPageLocators.SUBMIT_REPEAT_FIELD, "REPEAT_FIELD", fieldName);
			carryoverFieldValue = carryoverfield.getAttribute("aria-valuenow");
			if(fieldValue.equals(carryoverFieldValue)) {
				logInfo("Verified the carryover field value");
			}else {
				logError("Value is not carryovered in the next form");
				return false;
			}
			return true;
		}catch(Exception e) {
			logError("Failed to Verify carryover field value - "+e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to set the resource of record using resourceName passed
	 * @author hingorani_a
	 * @param resourceName The name of resource to be set
	 * @return boolean This returns boolean true after setting the resource for record
	 */
	public boolean setResourceForForm(String resourceName) {
		try {

			String selector = "li[class='k-item ng-scope'][role='option']";
			controlActions.JSSetValueFromList(driver, selector, resourceName);
			logInfo("Resource set to - " + resourceName);
			Sync();
			return true;
		}
		catch(Exception e) {
			logError("Failed to set resource " + resourceName + " for form - "
					+ e.getMessage());
			return false;
		}	
	}

	/** 
	 * @author hingorani_a
	 */
	public boolean submitData(boolean checkProperties, boolean checkCorrections,boolean complianceCheck, boolean isSubmit, String resourceName) {
		WebElement currentElement,CurrentElementField, CurrentElementFieldStatus, CurrentElementLabel,fieldDetails;
		String fieldType,fieldID,fieldStatusClassName,fieldName, fieldDisplayStatus, complianceStatus = null;

		try {
			if(!setResourceForForm(resourceName)) {
				return false;
			}

			System.out.println("Total fields - "+AllFieldsLst.size());
			for(int i=0;i<AllFieldsLst.size();i++) {
				currentElement = AllFieldsLst.get(i);
				fieldType = currentElement.getAttribute("data-fieldtype");
				fieldID = currentElement.getAttribute("data-field");
				fieldDetails = controlActions.perform_GetElementByXPath(FSQABrowserFormsPageLocators.CHECK_FIELD_DISPLAYED, "FIELDID", fieldID);
				fieldDisplayStatus = fieldDetails.getAttribute("class");
				if(fieldType.equals("Aggregate")) {
					CurrentElementField = controlActions.perform_GetElementByXPath(FSQABrowserFormsPageLocators.FIELDS_TXT, "FIELDID", fieldID);
				}else {
					CurrentElementField = controlActions.perform_GetElementByXPath(FSQABrowserFormsPageLocators.ALL_FIELDS_ID, "FIELDID", fieldID);
				}

				if(fieldType.equals("Paragraph")) {
					CurrentElementFieldStatus = controlActions.perform_GetElementByXPath(FSQABrowserFormsPageLocators.PARAGRAPH_FIELD_STATUS, "FIELDID", fieldID);
					if((CurrentElementFieldStatus.getAttribute("class").toString()).contains("ng-empty")) {
						fieldStatusClassName = "fa fa-circle-o";
					}else {
						fieldStatusClassName = "No Status";
					}
				}else {
					CurrentElementFieldStatus = controlActions.perform_GetElementByXPath(FSQABrowserFormsPageLocators.FIELDS_FILL_STATUS, "FIELDID", fieldID);
					fieldStatusClassName = CurrentElementFieldStatus.getAttribute("class");
				}

				CurrentElementLabel = controlActions.perform_GetElementByXPath(FSQABrowserFormsPageLocators.FIELDS_LBL, "FIELDID", fieldID);
				fieldName = CurrentElementLabel.getText().toString();

				if(fieldDisplayStatus.equals("scs-field ng-hide")) {
					logInfo("Field - '"+fieldName+"' of type - '"+fieldType+"' is hidden");

				}else {
					if(fieldStatusClassName.equals("fa fa-circle-o")){
						if(fieldType.equals("SingleLineText") || fieldType.equals("FreeText")){
							controlActions.sendKeys(CurrentElementField, SubmissionData.textField);
						}
						if(fieldType.equals("Paragraph")){
							controlActions.sendKeys(CurrentElementField, SubmissionData.paragraphField);		
						}
						if(fieldType.equals("SelectOne")){
							CurrentElementField = controlActions.perform_GetElementByXPath(FSQABrowserFormsPageLocators.FIELDS_TXT, "FIELDID", fieldID);
							logInfo("Setting value in 'Select One' field");
							for(int k=0;k<SubmissionData.selectOneFieldOption;k++) {
								controlActions.sendKey(CurrentElementField, Keys.ARROW_DOWN);	
							}
						}
						if(fieldType.equals("SelectMultiple")) {
							CurrentElementField = controlActions.perform_GetElementByXPath(FSQABrowserFormsPageLocators.FIELDS_TXT, "FIELDID", fieldID);
							logInfo("Setting value in 'Select One' field");
							for(int k=0;k<SubmissionData.selectMultipleFieldOption;k++) {
								controlActions.sendKey(CurrentElementField, Keys.ARROW_DOWN);	
							}
							controlActions.sendKey(CurrentElementField, Keys.ENTER);	
						}
						if(fieldType.equals("Numeric")) {
							CurrentElementField = controlActions.perform_GetElementByXPath(FSQABrowserFormsPageLocators.FIELDS_TXT, "FIELDID", fieldID);
							controlActions.sendKeys(CurrentElementField,SubmissionData.numericField);		
						}
						if(fieldType.equals("Date")) {
							controlActions.setDate(CurrentElementField, "Day", 0);
							//controlActions.sendKeys(CurrentElementField, SubmissionData.dateField,"Date field");		
						}
						if(fieldType.equals("Time")) {
							controlActions.setTime(CurrentElementField, 0, 0, true, true);
							//controlActions.sendKeys(CurrentElementField, SubmissionData.timeField, "Time field");		
						}
						if(fieldType.equals("DateTime")) {
							controlActions.setDateTime(CurrentElementField, "Day", 0, 0, 0, true, true);
							//controlActions.sendKeys(CurrentElementField, SubmissionData.dateTimeField, "Date & Time field");		
						}
						if(fieldType.equals("Identifier")) {
							CurrentElementField = controlActions.perform_GetElementByXPath(FSQABrowserFormsPageLocators.FIELDS_TXT, "FIELDID", fieldID);
							//System.out.println(CurrentElementField.getAttribute("placeholder")+"-");
							if(CurrentElementField.getAttribute("placeholder").trim().equalsIgnoreCase("Select...")) {
								logInfo("Setting value in 'Identifier - Select One' field");
								controlActions.sendKey(CurrentElementField, Keys.ARROW_DOWN);	
							}else {
								controlActions.sendKeys(CurrentElementField, SubmissionData.identifierField);	
							}
						}
						logInfo("Value setted for field - '"+fieldName+"' of type - '"+fieldType+"'");
					}else {
						logInfo("Value was already setted for field - '"+fieldName+"' of type - '"+fieldType+"'");
					}
					controlActions.clickElement(CurrentElementLabel);
					Sync();
					threadsleep(2000);
					CurrentElementFieldStatus = controlActions.perform_GetElementByXPath(FSQABrowserFormsPageLocators.FIELDS_FILL_STATUS, "FIELDID", fieldID);
					fieldStatusClassName = CurrentElementFieldStatus.getAttribute("class");
					switch(fieldStatusClassName) {
					case "fa fa-check":
						complianceStatus = "Compliant";
						break;

					case "fa fa-close":
						complianceStatus = "Non-Compliant";
						break;

					default:
						complianceStatus = "Filled";

					}
					logInfo("Field status is - '"+complianceStatus+"'");
					if(complianceCheck==true){
						if(complianceStatus == "Filled") {
							logError("The form has no complaint fields");
							return false;
						}

					}		
					if(checkProperties==true && checkCorrections==true) {
						if(!setFieldProperties(fieldID,true, complianceStatus)) {
							return false;
						}
					}else{
						if(checkCorrections==true) {
							if(!setFieldProperties(fieldID,false, complianceStatus)) {
								return false;
							}
						}
					}
				}
			}

			if(isSubmit) {
				controlActions.clickElement(SubmitFormBtn);
				Sync();
				controlActions.clickElement(PopUpOkBtn);
				Sync();
				logInfo("The form is filled & submitted sucessfully");
			}else {
				logInfo("The form is filled sucessfully");

			}
			return true;
		}catch(Exception e) {
			logError("Failed to fill & submit the form - "+e.getMessage());
			return false;
		}
	}

	/* This method is used to set Field Values 
	 * @author thakker_k
	 * @param String fieldName, String fieldType, String fieldValue, boolean repeatFields
	 * @return boolean true post setting value 
	 */	
	public boolean setFieldValues(String fieldName, String fieldType, String fieldValue, boolean repeatFields) {
		List<WebElement> UpdateFormField = null;
		String tempXpath, finalXpath = null;
		try {
			tempXpath = controlActions.perform_GetDynamicXPath(FSQABrowserFormsPageLocators.SET_FORM_FIELD,
					"FIELDLABEL", fieldName);
			finalXpath = controlActions.perform_GetDynamicXPath(tempXpath,
					"FIELDTYPE", fieldType);
			UpdateFormField = controlActions.perform_GetListOfElementsByXPath_WithWaitType(finalXpath, WaitType.WhenPresent);

			if(repeatFields) {

				for(WebElement field : UpdateFormField) {
					if(controlActions.isElementDisplay(field))  
						//if(controlActions.isElementDisplayedOnPage(field))
					{
						controlActions.perform_scrollToElement_ByElement(field);

						switch(fieldType) {
						case FIELD_TYPES.NUMERIC:

							controlActions.moveToElementAction(field);
							//field.clear();
							controlActions.actionClearTextBox(field);
							controlActions.clickOnElement(SCLogo);
							controlActions.sendKeys(field, fieldValue);
							break;


						default:
							logError("Input is an invalid fieldtype : " + fieldType);
							break;
						}
					}
				}

				return true;
			}
			else {		

				return true;			
			}
		}
		catch(Exception e) {
			logError("Failed to set field with name - " + fieldName 
					+ e.getMessage());
			return false;
		}	
	}

	/* This method is used to click Submit Form Btn
	 * @author thakker_k
	 * @return boolean true post clicking Submit Form Btn
	 */	
	public boolean clickSubmitFormBtn()
	{
		try {
			controlActions.click(SubmitFormBtn);
			Sync();
			logInfo("Clicked Submit Form ");
			return true;
		} 

		catch (Exception e) {
			logError("Failed to click on Submit Form Btn " + e.getMessage());
			return false;
		}

	}

	/* This method is used to validate is saved form displayed
	 * @author thakker_k
	 * @param formName is the name of saved form to be validated
	 * @param resourceType is the type of resource
	 * @param dateTimeStamp is the date time stamp of saved form to be validated
	 * @boolean will return true if form is displayed
	 */

	public boolean isSavedFormDisplayed(String resourceType, String formName, String dateTimeStamp) {
		try {
			FSQABrowserPage fbp = clickFSQABrowserMenu();
			if(fbp.selectResourceDropDownandNavigate(resourceType,"Forms"))
			{
				Sync();
				String SavedForm1 = FSQABrowserFormsPageLocators.SAVED_FORM.replace("FORM_NAME", formName);
				WebElement SavedForm2 = controlActions.perform_GetElementByXPath(SavedForm1,
						"DATE_TIME_STAMP", dateTimeStamp);
				if(SavedForm2.isDisplayed())
				{
					logInfo( formName + " is displayed in saved forms");
					return true;
				}
				logError("Form not found" + formName);
				return false;
			}
			logError("Failed to navigate to forms tab");
			return false;

		}catch(Exception e) {
			logError("Failed to open saved form" +e.getMessage());
			return false;
		}		
	}

	/** 'setPredefinedCorrectionDropdown' method is used to set the value in 'Corrections' drop-down
	 * @author pashine_a
	 * @param WebElement - 'correctionsInput'
	 * @param String - 'correctionValue'
	 * @return boolean status
	 * IF value in set in 'Corrections' dropdown THEN true ELSE false
	 */	
	public boolean setPredefinedCorrectionDropdown(WebElement correctionsInput, String correctionValue) {
		try {
			controlActions.sendKeys(correctionsInput, correctionValue);
			controlActions.sendKey(correctionsInput, Keys.ESCAPE);
			logInfo("Setted value - "+correctionValue+" in 'Corrections(Predefined Correction)' dropdown");
			return true;
		}catch(Exception e) {
			logError("Failed to set value in Corrections - "+e.getMessage());
			return false;
		}
	}
	/** 'setCorrectionTextarea' method is used to set the value in 'Corrections' text area box
	 * @author pashine_a
	 * @param WebElement - 'correctionsBox'
	 * @param String - 'correctionValue'
	 * @return boolean status
	 * IF value in set in 'Corrections' text area box THEN true ELSE false
	 */	
	public boolean setCorrectionTextarea(WebElement correctionsBox, String correctionValue) {
		try {
			controlActions.sendKeys(correctionsBox,correctionValue);
			logInfo("Value setted in 'Corrections'");
			return true;
		}catch(Exception e) {
			logError("Failed to set value in Corrections - "+e.getMessage());
			return false;
		}
	}


	/** 'setFieldProperties' method is used to set values in the field's properties
	 * @author pashine_a
	 * @param String - 'fieldID'
	 * @param boolean - 'perdefinedCorrection'
	 * @param String - 'correctionValue'
	 * @return boolean status
	 * IF all properties are setted successfully THEN true ELSE false
	 */	
	public boolean setCorections(String fieldID, boolean perdefinedCorrection, String correctionValue) {
		String checkPredefinedCorrectionPath, checkCorrectionPath;
		boolean correctionsPredefinedAvail,correctionsAvail;
		try {
			if(perdefinedCorrection) {
				checkPredefinedCorrectionPath = controlActions.perform_GetDynamicXPath(FSQABrowserFormsPageLocators.CORRECTIONS_PREDEIFNED_DDL, "FIELDID", fieldID);
				correctionsPredefinedAvail= controlActions.isElementDisplayed(checkPredefinedCorrectionPath);
				if(correctionsPredefinedAvail) {
					if(!setPredefinedCorrectionDropdown(controlActions.perform_GetElementByXPath(checkPredefinedCorrectionPath),correctionValue)) {
						logError("Failed to set proprties for the field"); 
						return false;
					}
					logInfo("The properties has been set for the field");
					return true;
				}else {
					logError("Pre-defined Correction dropdown is not visible"); 
					return false;
				}
			}else{
				checkCorrectionPath = controlActions.perform_GetDynamicXPath(FSQABrowserFormsPageLocators.CORRECTIONS_TXA, "FIELDID", fieldID);
				correctionsAvail= controlActions.isElementDisplayed(checkCorrectionPath);
				if(correctionsAvail) {
					if(!setCorrectionTextarea(controlActions.perform_GetElementByXPath(checkCorrectionPath),correctionValue)) {
						logError("Failed to set proprties for the field");
						return false;
					}
					logInfo("The properties has been set for the field");
					return true;

				}else {
					logError("Correction text box is not visible"); 
					return false;

				}
			}
		}catch(Exception e) {
			logError("Failed to set proprties for the field - "+e.getMessage());
			return false;
		}
	}


	/** 'setIsResolvedStatus' method is used to set the 'is Resolved' status
	 * @author pashine_a
	 * @param String - 'status'
	 * @return boolean status
	 * IF 'is Resolved' status setted THEN true ELSE false
	 */	
	public boolean setIsResolvedStatus(String fieldID, String status) {
		String isResolvedPath;
		WebElement selectIsResolvedStatus;
		try {
			isResolvedPath = controlActions.perform_GetDynamicXPath(FSQABrowserFormsPageLocators.IS_RESOLVED_BTN, "FIELDID", fieldID);
			isResolvedPath = controlActions.perform_GetDynamicXPath(isResolvedPath, "STATUS", status);
			selectIsResolvedStatus = controlActions.perform_GetElementByXPath(isResolvedPath);
			controlActions.clickElement(selectIsResolvedStatus);
			logInfo("Value setted - '"+status+"' in 'Is Resolved' field");
			return true;
		}catch(Exception e) {
			logError("Failed to set - '"+status+"' in 'Is Resolved' field - "+e.getMessage());
			return false;
		}
	}

	/** 'setComments' method is used to set the value in 'Comments' text area box
	 * @author pashine_a
	 * @param String fieldID
	 * @return boolean status
	 * IF value in set in 'Comments' text area box THEN true ELSE false
	 */	
	public boolean setComments(String fieldID, String comment) {
		String expandPlusPath, commentPath;
		boolean expandAvail, commentAvail;
		WebElement commentBox;
		try {
			expandPlusPath = controlActions.perform_GetDynamicXPath(FSQABrowserFormsPageLocators.FIELDS_SETTINGS_STATUS, "FIELDID", fieldID);
			expandAvail= controlActions.isElementDisplayed(expandPlusPath);
			if(expandAvail) {
				if(!expandPlus(controlActions.perform_GetElementByXPath(expandPlusPath))) {
					return false;
				}
			}
			commentPath = controlActions.perform_GetDynamicXPath(FSQABrowserFormsPageLocators.FIELDS_COMMENTS, "FIELDID", fieldID);
			commentAvail= controlActions.isElementDisplayed(commentPath);
			if(commentAvail) {
				commentBox = controlActions.perform_GetElementByXPath(commentPath);
				controlActions.sendKeys(commentBox, comment);
				logInfo("Value setted in 'Comments'");
			}else {
				logError("Comments section not found");
				return false;
			}
			return true;
		}catch(Exception e) {
			logError("Failed to set value in Comments - "+e.getMessage());
			return false;
		}
	}

	/**
	 * This 'addFormLevelAttachment' method is add attachment at form level
	 * @author pashine_a
	 * @param filePath1
	 * @boolean will return true when attachment is uploaded
	 */

	public boolean addFormLevelAttachment(String filePath1) {
		try {
			controlActions.clickElement(FormLevelAttachment);
			if(!controlActions.isElementDisplayedOnPage(FormLevelAttachmentSelectBtn)) {
				logError("Failed to find upload attachment option");
				return false;
			}
			controlActions.sendKeys(FormLevelAttachmentInp, filePath1);
			logInfo("Uploading the attchment");
			threadsleep(4000);
			String uploadStatus = FormLevelAttachmentStatus.getAttribute("class");
			for(int i=0;i<2;i++) {
				if(uploadStatus.equals("k-file k-file-success")) {
					break;
				}
				if(uploadStatus.equals("k-file k-file-error")) {
					controlActions.clickElement(FormLevelAttachmentRetry);			
				}
				threadsleep(4000);
			}
			if(!uploadStatus.equals("k-file k-file-success")) {
				logError("Failed to upload attachment'");
				return false;
			}			
			logInfo("Verified - Attachment is uploaded'");
			controlActions.clickElement(FormLevelAttachmentCloseBtn);
			return true;
		}catch(Exception e) {
			logError("Failed to upload attachment - "+e.getMessage());
			return false;
		}	
	}


	/** 'addAttachment' method is used to upload the file in field level attachment
	 * @author pashine_a
	 * @param String - 'fieldID'
	 * @param String filePath
	 * @return boolean status
	 * IF file is added in attachments THEN true ELSE false
	 */	
	public boolean addAttachment(String fieldName, String fieldID, String filePath1) {
		String expandPlusPath, attachmentPath;
		WebElement element;
		String uploadedAttachmentPath;
		boolean expandAvail, attachAvail;
		try {
			if(fieldName.contains("Paragraph") || fieldName.contains("paragraph") || fieldName.contains("PARAGRAPH"))
				expandPlusPath = controlActions.perform_GetDynamicXPath(FSQABrowserFormsPageLocators.OTHR_FIELDS_SETTINGS_STATUS, "FIELDID", fieldID);
			else
				expandPlusPath = controlActions.perform_GetDynamicXPath(FSQABrowserFormsPageLocators.FIELDS_SETTINGS_STATUS, "FIELDID", fieldID);

			expandAvail= controlActions.isElementDisplayed(expandPlusPath);
			if(expandAvail) {
				if(!expandPlus(controlActions.perform_GetElementByXPath(expandPlusPath))) {
					return false;
				}
			}
			attachmentPath = controlActions.perform_GetDynamicXPath(FSQABrowserFormsPageLocators.FIELDS_ATTACHMENTS, "FIELDID", fieldID);
			attachAvail= controlActions.isElementDisplayed(attachmentPath);
			if(attachAvail) {
				element = controlActions.perform_GetElementByXPath(FSQABrowserFormsPageLocators.FIELDS_ATTACHMENTS_INP, "FIELDID", fieldID);
				controlActions.sendKeys(element, filePath1);
				logInfo("Uploading the attchment");
				threadsleep(8000);
				uploadedAttachmentPath = controlActions.perform_GetDynamicXPath(FSQABrowserFormsPageLocators.UPLOADED_ATTACHMENT_LNK, "FIELDID", fieldID);
				if(!controlActions.isElementDisplayed(uploadedAttachmentPath)) {
					logError("Verified - Attachment is not uploaded'");
					return false;
				}			
				logInfo("Verified - Attachment is uploaded'");
				return true;
			}else {
				logError("Attachment section not found");
				return false;
			}
		}catch(Exception e) {
			logError("Failed to add attachment - "+e.getMessage());
			return false;
		}
	}

	/** This method is used to click on Ok button on Submit Form popup
	 * @author hingorani_a
	 * @param none
	 * @return boolean true when Ok button is clicked successfully
	 */	
	public boolean clickOkPopupSubmitFormBtn()
	{
		try {
			controlActions.click(PopUpOkBtn);
			Sync();
			logInfo("Clicked on popup's Ok button");
			return true;
		} 

		catch (Exception e) {
			logError("Failed to click on popup's Ok button " + e.getMessage());
			return false;
		}
	}

	/** This method is used to submit the form; clicks on Submit button and then on Popup's Ok button
	 * @author hingorani_a
	 * @param none
	 * @return boolean true after submitting the form
	 */	
	public boolean submitForm()
	{
		try {
			SubmitFormBtn.click();
			Sync();
			PopUpOkBtn.click();
			Sync();
			logInfo("The form submitted successfully");
			return true;
		} 
		catch (Exception e) {
			logError("Failed to submit form - " + e.getMessage());
			return false;
		}
	}

	/** This method is used to verify saved form details
	 * @author choubey_a
	 * @param formname,location,resource,numeric field and numeric field value
	 * @return boolean true when all the form details is verified
	 */	

	public boolean verifyISavedFormDetails(String formname,String location,String resource,String numericfieldname, String numvalue)
	{
		try {

			WebElement form = controlActions.perform_GetElementByXPath(FSQABrowserFormsPageLocators.FORM_HEADERLEVEL_DETAILS, "DETAILS",formname);
			WebElement loc = controlActions.perform_GetElementByXPath(FSQABrowserFormsPageLocators.FORM_HEADERLEVEL_DETAILS, "DETAILS",location);
			WebElement res = controlActions.perform_GetElementByXPath(FSQABrowserFormsPageLocators.FORM_HEADERLEVEL_DETAILS, "DETAILS",resource);
			WebElement num = controlActions.perform_GetElementByXPath(FSQABrowserFormsPageLocators.NUMERIC_FIELD_VALUE, "FIELD_NAME",numericfieldname);

			if(!controlActions.isElementDisplayed(form)) {
				logError("Could not verify formname - "+formname);
				return false;
			}
			if(!controlActions.isElementDisplayed(loc)) {
				logError("Could not verify location - "+location);
				return false;
			}
			if(!controlActions.isElementDisplayed(res)) {
				logError("Could not verify resource - "+resource);
				return false;
			}
			if(!(num.getAttribute("aria-valuenow").toString()).equals(numvalue)) {
				logError("Could not verify numeric field value for - "+numericfieldname);
				return false;
			}
			logInfo("Verified saved form details");
			return true;
		} 

		catch (Exception e) {
			logError("Failed to verify saved form details " + e.getMessage());
			return false;
		}

	}

	/** 
	 * This method is used to open a Saved Form having a Unique Name
	 * @author ahmed_tw
	 * @param formName - Name of the Saved Form
	 * @return True after clicking and opening the Saved form, else false
	 */
	public boolean openSavedForm_WithUniqueName(String formName) {
		WebElement savedForm = null;
		try {
			savedForm = controlActions.perform_GetElementByXPath(FSQABrowserFormsPageLocators.SAVED_FORM_NAME, "FORM_NAME", formName);
			savedForm.click();
			logInfo("Successfully clicked on the saved form " + formName);
			return true;
		} catch (Exception e) {
			logError("Could Not click on the saved form " + formName + e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to click on "Submit&Repeat" Button while Filling the Form
	 * @author ahmed_tw
	 * @return True after clicking on the "Submit&Repeat" button, else false
	 */
	public boolean clickSubmitAndReapeatButton()
	{
		try {
			controlActions.WaitforelementToBeClickable(SubmitRepeatFormBtn);
			SubmitRepeatFormBtn.click();
			Sync();
			SubmissionOKBtn.click();
			logInfo("Clicked Submit&Repeat button ");
			return true;
		} 
		catch (Exception e) {
			logError("Clicked Submit&Repeat button " + e.getMessage());
			return false;
		}
	}

	/** 'verifyRelatedDocuments' method is used to verfiy Related Documents
	 * @author thakker_k
	 * @param String - 'documentName'
	 * @return boolean status
	 * IF file present in Related Docs THEN true ELSE false
	 */	
	public boolean verifyRelatedDocuments(String documentName) {
		WebElement documentNameLink;
		try {
			documentNameLink = controlActions.perform_GetElementByXPath(FSQABrowserFormsPageLocators.DOCUMENT_NAME_LNK,
					"DOCUMENTNAME", documentName);
			boolean isdocumentNameLinkDisplayed = documentNameLink.isDisplayed();			
			if (isdocumentNameLinkDisplayed ==true) {				
				logInfo("Verified Related Documents");
				return true;
			}
			else {
				logInfo("Failed to Verify Related Documents");
				return false;
			}

		}catch(Exception e) {
			logError("Failed to Verify Related Documents - "+e.getMessage());
			return false;
		}
	}

	/**This method is used to click on Cancel Button
	 * @author ahmed_tw
	 * @return [boolean] : True post clicking on the cancel button. False if fails to do so
	 */
	public boolean clickCancelButton()
	{
		try {
			controlActions.click(FormCancelBtn);
			Sync();
			logInfo("Clicked Cancle Form Button");
			return true;
		} 

		catch (Exception e) {
			logError("Failed to click on Cancel Form Button " + e.getMessage());
			return false;
		}

	}

	/** 'deleteSavedForm' method is used to delete the opened saved form
	 * @author pashine_a
	 * @param null
	 * @return boolean status
	 * IF form delete action performed THEN true ELSE false
	 */	
	public boolean deleteSavedForm() {
		try {
			controlActions.clickElement(DeleteSavedFormIcon);
			controlActions.clickElement(DeleteFormYesBtn);
			Sync();
			logInfo("Deleted the task");
			return true;
		}catch(Exception e) {
			logError("Failed to delete the task - "+e.getMessage());
			return false;
		}
	}

}

