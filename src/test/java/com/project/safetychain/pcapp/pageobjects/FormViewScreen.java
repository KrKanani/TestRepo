package com.project.safetychain.pcapp.pageobjects;

import java.text.DateFormatSymbols;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.project.utilities.ControlActions_PCApp;
import com.project.utilities.DateTime;

import io.appium.java_client.windows.WindowsDriver;

public class FormViewScreen extends CommonScreen{
	WindowsDriver<WebElement> PCAppDriver;
	WebDriverWait wait;
	ControlActions_PCApp desktopControlActions;

	public FormViewScreen(WindowsDriver<WebElement> driver) {
		super(driver);
		this.PCAppDriver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 30);
		desktopControlActions = new ControlActions_PCApp(this.PCAppDriver);
	}

	@FindBy(xpath = FormViewScreenLocators.LOCATION_TAB) 
	public WebElement LocationTab;

	@FindBy(xpath = FormViewScreenLocators.LOCATION_SEARCH_INP) 
	public WebElement LocationSearchInp;

	@FindBy(xpath = FormViewScreenLocators.RESOURCE_TAB) 
	public WebElement ResourceTab;

	@FindBy(xpath = FormViewScreenLocators.RESOURCE_SEARCH_INP) 
	public WebElement ResourceSearchInp;

	@FindBy(xpath = FormViewScreenLocators.NEXT_BTN) 
	public WebElement NextBtn;

	@FindBy(xpath = FormViewScreenLocators.EDIT_FIELD_INP) 
	public WebElement EditFieldInp;

	@FindBy(xpath = FormViewScreenLocators.TIME_FIELD_INP) 
	public WebElement DateTimeFieldFlyoutBtn;

	@FindBy(xpath = FormViewScreenLocators.TIME_FIELD_INP) 
	public List<WebElement> DateTimeFieldFlyoutBtnLst;

	@FindBy(xpath = FormViewScreenLocators.TIME_FLYOUT_ACCEPT_BTN) 
	public WebElement TimeFlyoutAcceptBtn;

	@FindBy(xpath = FormViewScreenLocators.SUBMIT_BTN) 
	public WebElement SubmitBtn;

	@FindBy(xpath = FormViewScreenLocators.SAVE_BTN) 
	public WebElement SaveBtn;

	@FindBy(xpath = FormViewScreenLocators.SUBMIT_REPEAT_BTN) 
	public WebElement SubmitRepeatBtn;

	@FindBy(xpath = FormViewScreenLocators.SUMMARY_RESULT) 
	public WebElement SummaryResult;

	@FindBy(xpath = FormViewScreenLocators.FORM_LEVEL_VERIFICATION_BTN) 
	public WebElement FormVerificationBtn;

	@FindBy(xpath = FormViewScreenLocators.VERIFICATION_USERNAME_INP) 
	public WebElement VerificationUserNameInp;

	@FindBy(xpath = FormViewScreenLocators.VERIFICATION_PIN_INP) 
	public WebElement VerificationPinInp;

	@FindBy(xpath = FormViewScreenLocators.VERIFICATION_COMPLIANT_BTN) 
	public WebElement VerificationCompliantBtn;

	@FindBy(xpath = FormViewScreenLocators.VERIFICATION_VERIFY_BTN) 
	public WebElement VerificationVerifyBtn;

	@FindBy(xpath = FormViewScreenLocators.FORM_LEVEL_ATTACHMENT_BTN) 
	public WebElement FormAttachmentBtn;

	@FindBy(xpath = FormViewScreenLocators.FORM_LEVEL_ADDED_ATTACHMENT_TXT) 
	public WebElement FormAddedAttachmentTxt;

	@FindBy(xpath = FormViewScreenLocators.FORM_LEVEL_COMMENT_BTN) 
	public WebElement FormCommentBtn;

	@FindBy(xpath = FormViewScreenLocators.FORM_LEVEL_COMMENT_INP) 
	public WebElement FormCommentInp;

	@FindBy(xpath = FormViewScreenLocators.FORM_LEVEL_COMMENT_SAVE_BTN) 
	public WebElement FormCommentSaveBtn;

	@FindBy(xpath = FormViewScreenLocators.FIELD_LEVEL_COMMENTS_INP) 
	public WebElement FieldCommentInp;
	@FindBy(xpath = FormViewScreenLocators.FIELD_LEVEL_CORRECTION_INP) 
	public WebElement FieldCorrectionInp;

	@FindBy(xpath = FormViewScreenLocators.FIELD_LEVEL_ATTACHMENT_BTN) 
	public WebElement FieldAttachmentBtn;

	@FindBy(xpath = FormViewScreenLocators.FIELD_LEVEL_HINT_TXT) 
	public WebElement FieldHintBtn;

	@FindBy(xpath = FormViewScreenLocators.SINGLE_DIGIT_CLEAR_BTN) 
	public WebElement SingleDigitClearBtn;

	@FindBy(xpath = FormViewScreenLocators.FORM_BACK_BTN) 
	public WebElement FormBackBtn;

	@FindBy(xpath = FormViewScreenLocators.DIRECT_FORM_BACK_BTN) 
	public WebElement DirectFormBackBtn;

	@FindBy(xpath = FormViewScreenLocators.DISCARD_BTN) 
	public WebElement FormDiscardBtn;

	@FindBy(xpath = FormsScreenLocators.MODIFIED_BY_LBL)
	public WebElement ModifiedByLbl;

	@FindBy(xpath = FormViewScreenLocators.FORM_LEVEL_ATTACHMENT_CLOSE_BTN) 
	public WebElement FormAttachmentViewCloseBtn;

	@FindBy(xpath = FormViewScreenLocators.OK_BTN)
	public WebElement OkBtn;

	@FindBy(xpath = FormViewScreenLocators.FORM_VALIDATION_FAILED_LBL)
	public WebElement FormValidationFailedLbl;

	@FindBy(xpath = FormViewScreenLocators.FORM_VALIDATION_MESSAGE_LBL)
	public WebElement FormValidationMessage;

	@FindBy(xpath = FormViewScreenLocators.ERRORS_LBL)
	public WebElement ErrorsLbl;

	@FindBy(xpath = FormViewScreenLocators.ENTER_VALUE_WARNING_LBL)
	public WebElement EmptyValueLbl;

	@FindBy(xpath = FormViewScreenLocators.FORM_LEVEL_CAMERA_ICON)
	public WebElement FormLevelCameraIcon;

	@FindBy(xpath = FormViewScreenLocators.FIELD_LEVEL_CAMERA_ICON)
	public WebElement FieldLevelCameraIcon;

	@FindBy(xpath = FormViewScreenLocators.CLOSE_CAMERA_BTN)
	public WebElement CloseCameraBtn;

	@FindBy(xpath = FormViewScreenLocators.SCROLL_UP)
	public WebElement FormScrollUp;

	@FindBy(xpath = FormViewScreenLocators.SCROLL_DOWN)
	public WebElement FormScrollDown;

	@FindBy(xpath = FormViewScreenLocators.PAGE_SCROLL_UP)
	public WebElement FormPageScrollUp;

	@FindBy(xpath = FormViewScreenLocators.PAGE_SCROLL_DOWN)
	public WebElement FormPageScrollDown;

	@FindBy(xpath = FormViewScreenLocators.RECORD_SUBMISSION_DIALOG)
	public WebElement RecordSubmissionDialog;


	public boolean openformByModifieduser() {
		try {
			desktopControlActions.click(ModifiedByLbl);
			return true;
		}catch(Exception e) {
			return false;
		}
	}

	/**
	 * This method is used to select Location & Resource for form template
	 * @author pashine_a
	 * @param locationName
	 * 	@param resourceName
	 * @return boolean
	 */
	public boolean selectLocationResource(String locationName, String resourceName) {
		WebElement selectLocation = null, selectResource = null;
		String locationResourceInfoPath1 = null, locationResourceInfoPath2 = null;
		try {
			if(desktopControlActions.isElementAvailable(FormViewScreenLocators.LOCATION_TAB)) {
				desktopControlActions.click(LocationTab);
				desktopControlActions.sendKeys(LocationSearchInp, locationName);
				selectLocation = desktopControlActions.getDynamicElement(FormViewScreenLocators.LOCATION_VALUE, "LOCATION_NAME", locationName);
				desktopControlActions.click(selectLocation);

				desktopControlActions.click(ResourceTab);
				desktopControlActions.sendKeys(ResourceSearchInp, resourceName);
				selectResource = desktopControlActions.getDynamicElement(FormViewScreenLocators.RESOURCE_VALUE, "RESOURCE_NAME", resourceName);
				desktopControlActions.click(selectResource);

				desktopControlActions.click(NextBtn);

				logInfo("Selected Location & Resource");

			}else {
				logInfo("Not selected location/resource. May have only one location & resource");
			}

			threadsleep(2000);
			locationResourceInfoPath1 = desktopControlActions.getDynamicXPath(FormViewScreenLocators.LOCATION_RESOURCE_SELECTION, "LOCATION_NAME", locationName);
			locationResourceInfoPath2 = desktopControlActions.getDynamicXPath(locationResourceInfoPath1, "RESOURCE_NAME", resourceName);

			desktopControlActions.waitForElementToBePresent(locationResourceInfoPath2);

			if(!desktopControlActions.isElementAvailable(locationResourceInfoPath2)) {
				logError("Selected Location/Resource not verified");
			}

			logInfo("Verfied selected Location & Resource");
			return true;
		}catch(Exception e) {
			logError("Failed to select location & resource - "+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to fill data in the form
	 * @author pashine_a
	 * @param formDetails
	 * @return boolean
	 */
	public boolean fillDataInForm(FormDetailsPC formDetails) {
		HashMap<String, List<String>> details;
		String fieldName, fieldType;;
		List<String> fieldValue;
		WebElement currentfield;
		List<WebElement> fields;
		WebElement fieldInput = null, dateValue=null, optionValue=null;
		try {
			details = formDetails.fieldDetails;
			if(!selectLocationResource(formDetails.locationName, formDetails.resourceName)) {
				logError("Failed to select location/resource");
				return false;
			}

			for (Map.Entry<String, List<String>> entry : details.entrySet()) {
				fieldName = entry.getKey();
				fieldValue = entry.getValue();
				fields = desktopControlActions.getDynamicElements(FormViewScreenLocators.SELECT_FIELD_LBL, "FIELD_NAME", fieldName);
				fieldType = getFieldType(fieldName);
				switch(fieldType) {
				case FieldInputs.EDIT_FIELD:
					System.out.println(fields.size());
					for(int i=0;i<fields.size();i++) {
						currentfield = fields.get(i);
						fieldInput = EditFieldInp;
						desktopControlActions.clickElement(currentfield);
						if(fieldValue.size()==fields.size()) {
							desktopControlActions.sendKeys(fieldInput,fieldValue.get(i));
						}else {
							desktopControlActions.sendKeys(fieldInput,fieldValue.get(0));
						}
					}

					break;

				case FieldInputs.SELECT_OPTION_FIELD:
					for(int i=0;i<fields.size();i++) {
						currentfield = fields.get(i);
						desktopControlActions.clickElement(currentfield);
						if(fieldValue.size()==fields.size()) {
							String[] options = fieldValue.get(i).split(",");
							for(int j=0;j<options.length;j++) {
								optionValue = desktopControlActions.getDynamicElement(FormViewScreenLocators.SELECT_OPTION_FIELD_VALUE, "OPTION_VALUE", options[j]);
								desktopControlActions.click(optionValue);
							}
						}else{
							String[] options = fieldValue.get(0).split(",");
							for(int j=0;j<options.length;j++) {
								optionValue = desktopControlActions.getDynamicElement(FormViewScreenLocators.SELECT_OPTION_FIELD_VALUE, "OPTION_VALUE", options[j]);
								desktopControlActions.click(optionValue);
							}
						}		
					}
					break;

				case FieldInputs.DATE_FIELD:
					for(int i=0;i<fields.size();i++) {
						currentfield = fields.get(i);
						desktopControlActions.clickElement(currentfield);
						if(fieldValue.size()==fields.size()) {
							dateValue = desktopControlActions.getDynamicElement(FormViewScreenLocators.DATE_FIELD_VALUE, "DAY", fieldValue.get(i));
						}else{
							dateValue = desktopControlActions.getDynamicElement(FormViewScreenLocators.DATE_FIELD_VALUE, "DAY", fieldValue.get(0));
						}		
						desktopControlActions.click(dateValue);
					}
					break;

				case FieldInputs.TIME_FIELD:
				case FieldInputs.DATE_TIME_FIELD:
					for(int i=0;i<fields.size();i++) {
						currentfield = fields.get(i);
						desktopControlActions.clickElement(currentfield);
						desktopControlActions.clickElement(DateTimeFieldFlyoutBtnLst.get(0));
						desktopControlActions.clickElement(TimeFlyoutAcceptBtn);
					}
					break;
				default:
					return false;
				}

			}

			logInfo("Filled form details");
			if(formDetails.isSubmit) {
				desktopControlActions.click(SubmitBtn);
				threadsleep(4000);
				if(!desktopControlActions.isElementAvailable(CommonScreenLocators.FORM_MNU)) {
					logError("Fail to submit form");
					return false;
				}
				logInfo("Form is submitted");
			}
			return true;
		}catch (Exception e) {
			logError("Failed to fill data in form - ");
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * This method is used to fill data in the form using 'NEXT' button
	 * @author pashine_a
	 * @param formDetails
	 * @return boolean
	 */
	public boolean fillDataInForm1(FormDetailsPC formDetails) {
		HashMap<String, List<String>> details;
		String fieldName,fieldType;;
		List<String> fieldValue;
		String dateXPath = null;
		WebElement fieldInput = null, dateValue=null, optionValue=null;
		try {
			details = formDetails.fieldDetails;
			if(formDetails.selectLocationResource) {
				if(!selectLocationResource(formDetails.locationName, formDetails.resourceName)) {
					logError("Failed to select location/resource");
					return false;
				}
			}

			if(formDetails.navigateToTop) {
				if(!navigateToFormTop((byte)formDetails.navigateToTopCount)) {
					logError("Failed to scroll form page");
				}
				WebElement firstField = desktopControlActions.getDynamicElement(FormViewScreenLocators.FIELD_LBL, "FIELD_NAME", details.entrySet().iterator().next().getKey());
				desktopControlActions.clickElement(firstField);
			}

			for (Map.Entry<String, List<String>> entry : details.entrySet()) {
				fieldName = entry.getKey();
				fieldValue = entry.getValue();
				for(int i=0;i<fieldValue.size();i++) {
					fieldType = getFieldType();
					switch(fieldType) {
					case FieldInputs.EDIT_FIELD:
						fieldInput = EditFieldInp;
						desktopControlActions.sendKeys(fieldInput,fieldValue.get(i));
						break;

					case FieldInputs.SELECT_OPTION_FIELD:
						String[] options = fieldValue.get(i).split(",");
						for(int j=0;j<options.length;j++) {
							optionValue = desktopControlActions.getDynamicElement(FormViewScreenLocators.SELECT_OPTION_FIELD_VALUE, "OPTION_VALUE", options[j]);
							desktopControlActions.clickElement(optionValue);
						}
						break;

					case FieldInputs.DATE_FIELD:
						dateXPath = desktopControlActions.getDynamicXPath(FormViewScreenLocators.DATE_FIELD_VALUE, "INDEX", getDateIndex(fieldValue.get(i)));
						dateValue = desktopControlActions.getDynamicElement(dateXPath, "DAY", fieldValue.get(i));

						desktopControlActions.click(dateValue);	
						//dateXPath = desktopControlActions.getDynamicXPath(FormViewScreenLocators.DATE_FIELD_VALUE, "MONTH_NAME", getMonth(Integer.parseInt(fieldValue.get(i))));
						//dateValue = desktopControlActions.getDynamicElement(dateXPath, "DAY",desktopControlActions.getDayDate((Integer.parseInt(fieldValue.get(i)))));
						//desktopControlActions.click(dateValue);
						break;

					case FieldInputs.TIME_FIELD:
					case FieldInputs.DATE_TIME_FIELD:
						desktopControlActions.clickElement(DateTimeFieldFlyoutBtnLst.get(0));
						desktopControlActions.clickElement(TimeFlyoutAcceptBtn);
						break;
					default:
						return false;
					}
					if(formDetails.propertyfieldName.equals(fieldName)) {
						if(!checkFieldProperties(formDetails)) {
							logError("Fail to set field properties");
						}
					}
					desktopControlActions.clickElement(NextBtn);
					desktopControlActions.waitForElementToBeVisisble(NextBtn);
				}
			}

			logInfo("Filled form details");
			if(formDetails.submitRepeatCheck) {
				boolean discardCheckTemp = formDetails.discardCheck;
				for(byte c=0;c<formDetails.submitRepeatCount;c++) {
					if(c==formDetails.submitRepeatCount-1) {
						formDetails.discardCheck = discardCheckTemp;
					}else {
						formDetails.discardCheck = false;
					}
					if(!submitRepeatForm(formDetails.chartClose, formDetails.discardCheck)) {
						logError("Fail to submit repeat form");
						return false;
					}
					threadsleep(2000);
				}
			}
			if(formDetails.isSubmit) {
				if(!submitForm(formDetails.chartClose)) {
					logError("Fail to submit form");
					return false;
				}
			}
			if(formDetails.isSave) {
				if(!saveForm()) {
					logError("Fail to save the form");
					return false;
				}
			}
			return true;
		}catch (Exception e) {
			logError("Failed to fill data in form - "+e.getMessage());
			return false;
		}
	}


	/**
	 * This method is used to select the field of the form
	 * @author pashine_a
	 * @param fieldName
	 * @return boolean
	 */
	public boolean selectField(String fieldName) {
		List<WebElement> totalFields = null;
		WebElement selectField = null;
		try {
			totalFields = desktopControlActions.getDynamicElements(FormViewScreenLocators.SELECT_FIELD_LBL, "FIELD_NAME", fieldName);
			selectField = totalFields.get(0);
			desktopControlActions.clickElement(selectField);
			logInfo("Selected field - "+fieldName);
			return true;
		}catch(Exception e) {
			logError("Failed to select field- '"+fieldName+"' - "+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to get type type of field input
	 * @author pashine_a
	 * @param fieldName
	 * @return boolean
	 */
	public String getFieldType(String fieldName) {
		String fieldType = null;
		try {
			if(!selectField(fieldName)) {
				logError("Failed to select field- '"+fieldName+"'");
				return null;
			}
			if(desktopControlActions.isElementAvailable(FormViewScreenLocators.SELECT_OPTION_FIELD_INP)) {
				return FieldInputs.SELECT_OPTION_FIELD;
			}
			if(desktopControlActions.isElementAvailable(FormViewScreenLocators.EDIT_FIELD_INP)) {
				return FieldInputs.EDIT_FIELD;
			}
			if(desktopControlActions.isElementAvailable(FormViewScreenLocators.DATE_FIELD_INP)) {
				return FieldInputs.DATE_FIELD;
			}
			if(desktopControlActions.isElementAvailable(FormViewScreenLocators.TIME_FIELD_INP)) {
				if(desktopControlActions.isElementAvailable(FormViewScreenLocators.DATE_TIME_FIELD_INP)) {
					return FieldInputs.DATE_TIME_FIELD;
				}
				return FieldInputs.TIME_FIELD;
			}
			logInfo("Got field type for field - "+fieldName);
			return fieldType;
		}catch(Exception e) {
			logError("Failed to get field type for field - '"+fieldName+"' - "+ e.getMessage());
			return null;
		}	
	}

	/**
	 * This method is used to get type type of field input
	 * @author pashine_a
	 * @param null
	 * @return boolean
	 */
	public String getFieldType() {
		String fieldType = null;
		try {

			if(desktopControlActions.isElementAvailable(FormViewScreenLocators.SELECT_OPTION_FIELD_INP)) {
				return FieldInputs.SELECT_OPTION_FIELD;
			}
			if(desktopControlActions.isElementAvailable(FormViewScreenLocators.EDIT_FIELD_INP)) {
				return FieldInputs.EDIT_FIELD;
			}
			if(desktopControlActions.isElementAvailable(FormViewScreenLocators.DATE_FIELD_INP)) {
				return FieldInputs.DATE_FIELD;
			}
			if(desktopControlActions.isElementAvailable(FormViewScreenLocators.TIME_FIELD_INP)) {
				if(desktopControlActions.isElementAvailable(FormViewScreenLocators.DATE_TIME_FIELD_INP)) {
					return FieldInputs.DATE_TIME_FIELD;
				}
				return FieldInputs.TIME_FIELD;
			}
			logInfo("Got field type for field");
			return fieldType;
		}catch(Exception e) {
			logError("Failed to get field type"+ e.getMessage());
			return null;
		}	
	}

	/**
	 * This method is used to save the form
	 * @author pashine_a
	 * @param null
	 * @return boolean
	 */
	public boolean saveForm() {
		try {
			threadsleep(2000);
			desktopControlActions.click(SaveBtn);
			threadsleep(4000);
			if(!desktopControlActions.isElementAvailable(CommonScreenLocators.FORM_MNU)){
				logError("Failed to navigate to main screen");
				return false;
			}
			logInfo("Form is saved & navigated to main screen");
			return true;
		}catch(Exception e) {
			logError("Failed to save form & navigate to main screen "+ e.getMessage());
			return false;
		}	
	}


	/**
	 * This method is used to verify 'Note' field text
	 * @author pashine_a
	 * @param data
	 * @return boolean
	 */
	public boolean verifyNoteField(String data) {
		try {
			String noteFieldDataPath = desktopControlActions.getDynamicXPath(FormViewScreenLocators.NOTE, "NOTE", data);
			if(!desktopControlActions.isElementAvailable(noteFieldDataPath)){
				logError("Failed to view note field data");
				return false;
			}
			logInfo("Note field data is visible");
			return true;
		}catch(Exception e) {
			logError("Failed to verify note field "+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to verify 'Related Docs' field document
	 * @author pashine_a
	 * @param data
	 * @return boolean
	 */
	public boolean verifyRelatedRocs(String data) {
		try {
			String RelatedDocsDataPath = desktopControlActions.getDynamicXPath(FormViewScreenLocators.RELATED_DOC_DOCS, "DOCUMENT_NAME", data);
			if(!desktopControlActions.isElementAvailable(RelatedDocsDataPath)){
				logError("Failed to view document in 'Releated Docs' field");
				return false;
			}
			logInfo("Releated Docs field data is visible");
			return true;
		}catch(Exception e) {
			logError("Failed to verify Related Docs field "+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to verify 'Summary' field final result
	 * @author pashine_a
	 * @param fieldName
	 * @return boolean
	 */
	public boolean verifySummaryResult() {
		try {
			if(!desktopControlActions.isElementAvailable(FormViewScreenLocators.SUMMARY_RESULT)){
				logError("Failed to view 'Summary' results");
				return false;
			}
			logInfo("Summary field data is visible");
			return true;
		}catch(Exception e) {
			logError("Failed to verify Summary field "+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to verify all form elements
	 * @author pashine_a
	 * @param note
	 * @param document
	 * @return boolean
	 */
	public boolean verifyFormElements(String note,String document) {
		try {
			if(!verifyNoteField(note)){
				logError("Failed 1");

				return false;
			}
			if(!verifyRelatedRocs(document)){
				logError("Failed 2");

				return false;
			}
			if(!verifySummaryResult()){
				logError("Failed 3");

				return false;
			}
			logInfo("Form elements are visible");
			return true;
		}catch(Exception e) {
			logError("Failed to verify Form elements "+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to submit the form
	 * @author pashine_a
	 * @param null
	 * @return boolean
	 */
	public boolean submitForm() {
		try {
			desktopControlActions.clickElement(SubmitBtn);
			logInfo("Clicked on 'SUBMIT'");
			return true;
		}catch(Exception e) {
			logError("Failed to submit form"+ e.getMessage());
			return false;
		}	
	}


	/**
	 * This method is used to submit the form
	 * @author pashine_a
	 * @param null
	 * @return boolean
	 */
	public boolean submitForm(boolean chartClose) {
		try {
			desktopControlActions.clickElement(SubmitBtn);
			threadsleep(6000);
			if(chartClose) {
				if(!moveAndCloseChartScreen()) {
					logError("Failed to close chart screen");
					return false;
				}
			}

			if(!desktopControlActions.isElementAvailable(CommonScreenLocators.FORM_MNU)) {
				logError("Fail to submit form");
				return false;
			}
			logInfo("Form is submitted");
			return true;
		}catch(Exception e) {
			logError("Failed to submit form"+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to perform 'Direct Observation'(Verification)
	 * @author pashine_a
	 * @param username
	 * @param pin
	 * @return boolean
	 */
	public boolean performDirectObservation(String username, String pin) {
		try {
			desktopControlActions.clickElement(FormVerificationBtn);
			desktopControlActions.sendKeys(VerificationUserNameInp, username);
			desktopControlActions.sendKeys(VerificationPinInp, pin);
			desktopControlActions.clickElement(VerificationCompliantBtn);
			desktopControlActions.clickElement(VerificationVerifyBtn);
			logInfo("Added form level verification(Direct Observation)");
			return true;
		}catch(Exception e) {
			logError("Failed to add form level verification(Direct Observation) - "+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to add comment at form level
	 * @author pashine_a
	 * @param comment
	 * @return boolean
	 */
	public boolean addFormComment(String comment) {
		try {
			desktopControlActions.clickElement(FormCommentBtn);
			desktopControlActions.sendKeys(FormCommentInp, comment);
			desktopControlActions.clickElement(FormCommentSaveBtn);
			logInfo("Added form level comment");
			return true;
		}catch(Exception e) {
			logError("Failed to add form level comment - "+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to add comment at field level
	 * @author pashine_a
	 * @param comment
	 * @return boolean
	 */
	public boolean addFieldComment(String comment) {
		try {
			desktopControlActions.sendKeys(FieldCommentInp, comment);
			logInfo("Added field level comment");
			return true;
		}catch(Exception e) {
			logError("Failed to add field level comment - "+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to add correction text
	 * @author pashine_a
	 * @param correctionText
	 * @return boolean
	 */
	public boolean addCorrection(String correctionText) {
		try {
			desktopControlActions.sendKeys(FieldCorrectionInp, correctionText);
			logInfo("Added field level correction");		
			return true;
		}catch(Exception e) {
			logError("Failed to add field level correction - "+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to verify the 'Hint' text
	 * @author pashine_a
	 * @param null
	 * @return boolean
	 */
	public boolean verifyHintText() {
		try {
			desktopControlActions.clickElement(FieldHintBtn);
			logInfo("Verified field level hint");		
			return true;
		}catch(Exception e) {
			logError("Failed to verify field level hint - "+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to add attachment at form level
	 * @author pashine_a
	 * @param null
	 * @return boolean
	 */
	public boolean addFormLevelAttachment() {
		try {
			desktopControlActions.clickElement(FormAttachmentBtn);
			if(!selectfile()) {
				logError("File Not selected");
				return false;
			}
			desktopControlActions.clickElement(FormAddedAttachmentTxt);
			threadsleep(4000);
			desktopControlActions.clickElement(FormAttachmentViewCloseBtn);
			logInfo("Form level attachment is added");
			return true;
		}catch(Exception e) {
			logError("Failed to add attachment at form level - "+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to add attachment at field level
	 * @author pashine_a
	 * @param null
	 * @return boolean
	 */
	public boolean addFieldLevelAttachment() {
		try {
			desktopControlActions.clickElement(FieldAttachmentBtn);
			if(!selectfile()) {
				logError("File Not selected");
				return false;
			}
			logInfo("Field level attachment is added");
			return true;
		}catch(Exception e) {
			logError("Failed to add attachment at field level - "+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to verify field properties
	 * @author pashine_a
	 * @param formDetails
	 * @return boolean
	 */
	public boolean checkFieldProperties(FormDetailsPC formDetails) {
		try {
			if(formDetails.showHintCheck) {
				if(!verifyHintText()) {
					logError("Not able to verify hint");
					return false;
				}
			}
			if(formDetails.allowCommentCheck) {
				if(!addFieldComment(formDetails.comment)) {
					logError("Not able to set comment");
					return false;
				}
			}
			if(formDetails.allowCorrectionsCheck) {
				if(!addCorrection(formDetails.correctionText)) {
					logError("Not able to set correction text");
					return false;
				}
			}
			if(formDetails.allowAttachmentCheck) {
				if(formDetails.fileName!=null) {
					if(!addFieldLevelAttachment(formDetails.fileName)) {
						logError("Not able select attachment - "+formDetails.fileName);
						return false;
					}
				}else {
					if(!addFieldLevelAttachment()) {
						logError("Not able select attachment");
						return false;
					}
				}
			}

			logInfo("Able to set/verify field properties");		
			return true;
		}catch(Exception e) {
			logError("Failed to set/verify field properties - "+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to submit 'OEE Event' form (Dynamic flow) 
	 * @author pashine_a
	 * @param eventType
	 * @param batchID
	 * @param formDetailsPC
	 * @return boolean
	 */
	public boolean submitOEEEventsForm(String eventType, String batchID, FormDetailsPC formDetailsPC) {
		try {
			HashMap<String, List<String>> allFields = new LinkedHashMap<String, List<String>>();
			allFields.put("BatchID",Arrays.asList(batchID));
			switch(eventType) {
			case OEEeventType.ACTUAL_PRODUCTION_START:
				allFields.put("Shift ",Arrays.asList("Shift 1"));
				allFields.put("Event Type",Arrays.asList(OEEeventType.ACTUAL_PRODUCTION_START));
				allFields.put("Actual Production Start Time",Arrays.asList("NA"));
				break;

			case OEEeventType.THROUGHPUT_QUANTITY:
				allFields.put("Shift ",Arrays.asList("Shift 2"));
				allFields.put("Event Type",Arrays.asList(OEEeventType.THROUGHPUT_QUANTITY));
				allFields.put("Quantity Produced",Arrays.asList("4"));
				allFields.put("Quantity Date & Time",Arrays.asList("NA"));
				break;

			case OEEeventType.UNPLANNED_DOWNTIME:
				allFields.put("Shift ",Arrays.asList("Shift 3"));
				allFields.put("Event Type",Arrays.asList(OEEeventType.UNPLANNED_DOWNTIME));
				allFields.put("Downtime Event Type",Arrays.asList("Start"));
				allFields.put("Event Start Time",Arrays.asList("NA"));
				break;

			case OEEeventType.QUALITY:
				allFields.put("Shift ",Arrays.asList("Shift 1"));
				allFields.put("Event Type",Arrays.asList(OEEeventType.QUALITY));
				allFields.put("Quality Event Type",Arrays.asList("Production Rejects"));
				allFields.put("Production Rejects Reason ",Arrays.asList("Production Rejects Reason 2"));
				allFields.put("Event Quantity Rejected",Arrays.asList("2"));
				allFields.put("Quantity Rejected Time",Arrays.asList("NA"));
				allFields.put("Other Reason",Arrays.asList("PC App Test Form"));
				break;

			case OEEeventType.ACTUAL_PRODUCTION_END:
				allFields.put("Shift ",Arrays.asList("Shift 2"));
				allFields.put("Event Type",Arrays.asList(OEEeventType.ACTUAL_PRODUCTION_END));
				allFields.put("Actual Production End Time",Arrays.asList("NA"));
				allFields.put("Actual Quantity Produced",Arrays.asList("4"));
				allFields.put("Actual Quantity Rejected",Arrays.asList("2"));
				break;

			default:
				logError("Invalid event type");
				return false;

			}
			formDetailsPC.fieldDetails = allFields;
			if(!fillDataInForm1(formDetailsPC)) {
				logError("Fail to fill & submit OEE Event form/task");
				return false;
			}
			logInfo("Filled & submitted OEE Event(Dynamic Flow) form/task");
			return true;
		}catch(Exception e) {
			logError("Failed to fill & submit OEE Event(Dynamic flow) form/task - "+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to verify Numeric keypad
	 * @author pashine_a
	 * @param null
	 * @return boolean
	 */
	public boolean verifyNumericKeyPad() {
		try {
			if(!desktopControlActions.isElementAvailable(FormViewScreenLocators.SINGLE_DIGIT_CLEAR_BTN)) {
				logError("Numeric keypad is not shown");
				return false;
			}
			logInfo("Numeric keypad is visible");
			return true;
		}catch(Exception e) {
			logError("Failed to verify Numeric keypad - "+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to verify given field in form
	 * @author pashine_a
	 * @param name
	 * @return boolean
	 */
	public boolean verifyFieldVisibility(String name) {
		try {
			String formElement = desktopControlActions.getDynamicXPath(FormViewScreenLocators.FORM_ELEMENT, "FIELD", name);
			if(!desktopControlActions.isElementAvailable(formElement)){
				logError(name+" is not visible");
				return false;
			}
			logInfo(name+" is visible");
			return true;
		}catch(Exception e) {
			logError("Failed to verify Form element - "+name+" - "+e.getMessage());
			return false;
		}	
	}


	/**
	 * This method is used to submit the form using "SUBMIT REPEAT"
	 * @author pashine_a
	 * @param null
	 * @return boolean
	 */
	public boolean submitRepeatForm(boolean chartClose, boolean discard) {
		try {
			desktopControlActions.clickElement(SubmitRepeatBtn);
			threadsleep(6000);
			if(chartClose) {
				if(!moveAndCloseChartScreen()) {
					logError("Failed to close chart screen");
					return false;
				}
			}
			logInfo("Form is submitted");
			if(discard) {
				if(!discardForm(false)) {
					logError("Fail to discard form");
					return false;
				}
				if(!desktopControlActions.isElementAvailable(CommonScreenLocators.FORM_MNU)) {
					logError("Fail to discard form");
					return false;
				}
			}

			logInfo("Performed 'SUBMIT REPEAT'/Discard for form");
			return true;
		}catch(Exception e) {
			logError("Failed to perform 'SUBMIT REPEAT'/Discard form"+ e.getMessage());
			return false;
		}	
	}


	/**
	 * This method is used to move back from form view screen & discard the form
	 * @author pashine_a
	 * @param null
	 * @return boolean
	 */
	public boolean discardForm(boolean directMainScreen) {
		try {
			if(directMainScreen) {
				desktopControlActions.clickElement(DirectFormBackBtn);
			}else {
				desktopControlActions.clickElement(FormBackBtn);
			}
			threadsleep(2000);
			desktopControlActions.clickElement(FormDiscardBtn);
			threadsleep(4000);
			desktopControlActions.waitForElementToBePresent(CommonScreenLocators.FORM_MNU);
			if(!desktopControlActions.isElementAvailable(CommonScreenLocators.FORM_MNU)) {
				logError("Fail to discard form");
				return false;
			}
			logInfo("Form is discarded");
			return true;
		}catch(Exception e) {
			logError("Failed to discard the form - "+e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to verify delete button after submit repeat
	 * @author choubey_a
	 * @param null
	 * @return boolean
	 */

	public boolean verifyDeleteBtn() {
		try {
			desktopControlActions.clickElement(SubmitRepeatBtn);
			threadsleep(6000);
			if(!desktopControlActions.isElementAvailable(FormViewScreenLocators.DELETE_BTN)) {
				logInfo("Delete Button is not availble");
			}
			logInfo("Delete Button is avaialable");
			return true;
		}catch(Exception e) {
			logError("Failed to view the button - "+e.getMessage());
			return false;
		}	
	}


	String getMonth(int count) {
		int targetMonth = 0;
		String month = null;
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, count);
		targetMonth =  calendar.get(Calendar.MONTH);
		DateFormatSymbols dfs = new DateFormatSymbols();
		String[] months = dfs.getMonths();
		month = months[targetMonth];
		return month;
	}


	String getDateIndex(String dayDateValue) {
		int dayDate = 0;
		dayDate = Integer.parseInt(dayDateValue);
		if(dayDate<20) {
			return "1";
		}else {
			return "last()";
		}
	}


	/**
	 * This method is used to perform double click on 'SAVE' button
	 * @author pashine_a
	 * @param null
	 * @return boolean. It will return TRUE if form gets saved
	 */
	public boolean doubleCickOnSave() {
		try {
			threadsleep(2000);
			desktopControlActions.doubleClick(SaveBtn);
			threadsleep(4000);
			if(!desktopControlActions.isElementAvailable(CommonScreenLocators.FORM_MNU)){
				logError("Failed to navigate to main screen");
				return false;
			}
			logInfo("Form is saved & navigated to main screen");
			return true;
		}catch(Exception e) {
			logError("Failed to save form & navigate to main screen "+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to perform double click on 'SUBMIT' button
	 * @author pashine_a
	 * @param null
	 * @return boolean. It will return TRUE if form gets submitted
	 */
	public boolean doubleCickOnSubmit() {
		try {
			threadsleep(2000);
			desktopControlActions.doubleClick(SubmitBtn);
			threadsleep(4000);
			if(!desktopControlActions.isElementAvailable(CommonScreenLocators.FORM_MNU)){
				logError("Failed to navigate to main screen");
				return false;
			}
			logInfo("Form is submitted & navigated to main screen");
			return true;
		}catch(Exception e) {
			logError("Failed to submitted form & navigate to main screen "+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to perform double click on 'SUBMIT REPEAT' button
	 * @author pashine_a
	 * @param null
	 * @return boolean. It will return TRUE if form gets Submitted & new form gets loaded
	 */
	public boolean doubleCickOnSubmitRepeat() {
		try {
			threadsleep(2000);
			desktopControlActions.doubleClick(SubmitRepeatBtn);
			threadsleep(4000);
			if(!desktopControlActions.isElementAvailable(FormViewScreenLocators.SUBMIT_BTN)){
				logError("Failed to view new form after 'SUBMIT REPEAT'");
				return false;
			}
			logInfo("Form is submitted & new form is visible");
			return true;
		}catch(Exception e) {
			logError("Failed to submit(& repeat) form & view the new form "+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to add attachment(file name specified by user) at field level
	 * @author pashine_a
	 * @param fileName
	 * @return boolean
	 */
	public boolean addFieldLevelAttachment(String fileName) {
		try {
			desktopControlActions.clickElement(FieldAttachmentBtn);
			if(!selectfile(fileName)) {
				logError("File Not selected");
				return false;
			}
			logInfo("Field level attachment is added");
			return true;
		}catch(Exception e) {
			logError("Failed to add attachment at field level - "+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to verify 'RecordId Already Deleted' error message
	 * @author pashine_a
	 * @param null
	 * @return boolean
	 */
	public boolean verifyRecordID_DeletedMsg() {
		try {
			threadsleep(2000);
			desktopControlActions.click(SaveBtn);
			threadsleep(4000);
			if(!desktopControlActions.isElementDisplayed(FormViewScreenLocators.ERROR_POP_UP_LBL)) {
				logError("Error pop is not shown");
				return false;
			}
			if(!desktopControlActions.isElementDisplayed(FormViewScreenLocators.RECORD_ID_DELETED_ERROR_LBL)) {
				logError("'RecordId Already Deleted' error message not shown");
				return false;
			}
			desktopControlActions.click(OkBtn);
			threadsleep(4000);
			logInfo("''RecordId Already Deleted' error message is shown");
			return true;
		}catch(Exception e) {
			logError("Failed to verify 'RecordId Already Deleted' error message - "+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to close error 'Form Validation Failed' pop up
	 * @author pashine_a
	 * @param null
	 * @return boolean
	 */
	public boolean closeFormValidationFailedPopup() {
		try {
			threadsleep(2000);
			desktopControlActions.click(FormValidationFailedLbl);
			desktopControlActions.click(FormValidationMessage);
			desktopControlActions.click(OkBtn);
			threadsleep(2000);
			logInfo("Closed error message pop up");
			return true;
		}catch(Exception e) {
			logError("Failed to close error message pop up - "+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to verify 'Please enter a value.' label
	 * @author pashine_a
	 * @param null
	 * @return boolean
	 */
	public boolean verifyPleaseEnterAValueLabel() {
		try {
			threadsleep(2000);
			if(!ErrorsLbl.isDisplayed()) {
				logError("Failed to view 'Errors' label");
			}
			if(!EmptyValueLbl.isDisplayed()) {
				logError("Failed to view 'Please enter a value.' label");
			}
			logInfo("Verified 'Please enter a value.' label");
			return true;
		}catch(Exception e) {
			logError("Failed to verify 'Please enter a value.' label - "+ e.getMessage());
			return false;
		}	
	}



	/**
	 * This method is used to perform double click on 'Camera' button(Form Level)
	 * @author pashine_a
	 * @param close
	 * @return boolean. It will return TRUE if Camera gets opened
	 */
	public boolean doubleCickOnFormLevelCamera(boolean close) {
		try {
			threadsleep(2000);
			desktopControlActions.doubleClick(FormLevelCameraIcon);
			threadsleep(6000);
			logInfo("Opened camera");
			if(close) {
				desktopControlActions.click(CloseCameraBtn);
				logInfo("Closed camera");
			}
			return true;
		}catch(Exception e) {
			logError("Failed to open camera "+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to perform double click on 'Camera' button(Field Level)
	 * @author pashine_a
	 * @param close
	 * @return boolean. It will return TRUE if Camera gets opened
	 */
	public boolean doubleCickOnFieldLevelCamera(boolean close) {
		try {
			threadsleep(2000);
			if(!desktopControlActions.isElementAvailable(FormViewScreenLocators.FIELD_LEVEL_CAMERA_ICON)) {
				logError("Field level Camera is missing");
				discardForm(true);
				return false;
			}
			desktopControlActions.doubleClick(FieldLevelCameraIcon);
			threadsleep(6000);
			logInfo("Opened camera");
			if(close) {
				desktopControlActions.click(CloseCameraBtn);
				logInfo("Closed camera");
			}
			return true;
		}catch(Exception e) {
			logError("Failed to open camera "+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to scroll page(form field) for given number of times
	 * @author pashine_a
	 * @param formName
	 * @return boolean. If scroll performed then returns TRUE
	 */
	public boolean navigateToFormTop(byte count) {
		try {
			for(byte i=0;i<count;i++) {
				desktopControlActions.click(FormPageScrollUp);
			}
			logInfo("Scrolled form page "+count+" times");
			return true;
		}catch(Exception e) {
			logError("Failed to scroll form page "+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to verify data
	 * @author pashine_a
	 * @param formName
	 * @return boolean. If data is correct then returns TRUE
	 */
	public boolean verifyFormData(HashMap <String, String> fieldDetails, int pageScroll) {
		String fieldName = null, fieldValue = null;
		String fieldNamePath = null;
		WebElement fieldElement = null;
		try {
			if(!navigateToFormTop((byte)pageScroll)) {
				logError("Failed to scroll form page");
			}
			HashMap<String, String> savedFormdetails = fieldDetails;
			for (Map.Entry<String, String> entry : savedFormdetails.entrySet()) {
				fieldName = entry.getKey();
				fieldValue = entry.getValue();

				fieldNamePath = desktopControlActions.getDynamicXPath(FormViewScreenLocators.FIELD_DATA_LBL, "FIELD_NAME", fieldName);

				fieldElement = desktopControlActions.getDynamicElement(fieldNamePath, "FIELD_VALUE", fieldValue);

				if(desktopControlActions.isElementDisplayed(fieldElement)) {
					desktopControlActions.clickElement(fieldElement);
					desktopControlActions.clickElement(NextBtn);
					logInfo("Verified field details for field - "+fieldName+" having field value - "+fieldValue);
				}else {
					logError("Failed to verify field details for field - "+fieldName+" having field value - "+fieldValue);
					return false;
				}
			}
			logInfo("Verified the field details");
			return true;
		}catch(Exception e) {
			logError("Failed to verify field details "+ e.getMessage());
			return false;
		}	
	}


	/**
	 * This method is used to verify timestamp format in Record Submission Dialog
	 * @author pashine_a
	 * @param format
	 * @return boolean. If Date-Time format matched with provided("MM/dd/yyyy HH:mm) format then returns TRUE
	 */
	public boolean verifyRecordSubmissionDialog(String format) {
		try {
			String label = RecordSubmissionDialog.getText();
			String[] details = label.split("on");
			String dateTimeStamp = details[1].toString().trim();

			DateTime dateTime = new DateTime();
			if(!dateTime.verifyDateTimeFormat(format,dateTimeStamp)) {
				logError("Record Submission Dialog details are not correct");
				return false;
			}
			logInfo("Record Submission Dialog details are correct");
			return true;
		}catch(Exception e) {
			logError("Failed to Record Submission Dialog details - "+ e.getMessage());
			return false;
		}	
	}


	public static class FormDetailsPC{
		public HashMap<String, List<String>>fieldDetails;
		List<String> fieldData;
		public String locationName;
		public String resourceName;
		public boolean isSubmit = true;
		public String propertyfieldName = "NA";
		public boolean complianceStatusCheck = false;
		public boolean allowAttachmentCheck = false;
		public boolean allowCommentCheck = false;
		public boolean showHintCheck = false;
		public boolean allowCorrectionsCheck = false;
		public String correctionText = "Correction Test";
		public String comment = "Comment Test";
		public String username;
		public String pin;
		public boolean selectLocationResource = true;
		public boolean chartClose = false;
		public boolean submitRepeatCheck = false;
		public boolean saveCheck = false;
		public boolean discardCheck = false;
		public boolean currentMonthCheck = false;
		public byte submitRepeatCount = 1;
		public String fileName = null;
		public boolean isSave = false;
		public boolean navigateToTop = false;
		public int navigateToTopCount = 2;

	}

	public static class FieldInputs{
		public final static String EDIT_FIELD = "EDIT";
		public final static String SELECT_OPTION_FIELD = "SELECT_OPTION";
		public final static String DATE_FIELD = "DATE_PICKER";
		public final static String TIME_FIELD = "TIME_PICKER";
		public final static String DATE_TIME_FIELD = "DATE_TIME_PICKER";
	}

	public static class OEEeventType{
		public final static String ACTUAL_PRODUCTION_START = "Actual Production Start";
		public final static String THROUGHPUT_QUANTITY = "Throughput Quantity";
		public final static String UNPLANNED_DOWNTIME = "Unplanned Downtime";
		public final static String QUALITY = "Quality";
		public final static String ACTUAL_PRODUCTION_END = "Actual Production End";
	}
}
