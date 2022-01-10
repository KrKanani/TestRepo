package com.project.safetychain.pcapp.pageobjects;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.project.utilities.ControlActions_PCApp;

import io.appium.java_client.windows.WindowsDriver;

public class SubmissionsScreen extends CommonScreen{

	public SubmissionsScreen(WindowsDriver<WebElement> driver) {
		super(driver);
		this.PCAppDriver = driver;
		PageFactory.initElements(this.PCAppDriver, this);
		wait = new WebDriverWait(driver, 30);
		desktopControlActions = new ControlActions_PCApp(this.PCAppDriver);
	}

	@FindBy(xpath = SubmissionsScreenLocators.SUBMISSIONS_SEARCH) 
	public WebElement SeachSubmissionInp;

	@FindBy(xpath = SubmissionsScreenLocators.SUBMISSION_LOCATION) 
	public WebElement SubmissionLocation;

	@FindBy(xpath = SubmissionsScreenLocators.SUBMISSION_BACK_BTN) 
	public WebElement SubmissionBackBtn;

	@FindBy(xpath = SubmissionsScreenLocators.SUBMISSIONS_PENDING_COUNT) 
	public WebElement PendingSubmissionCount;

	@FindBy(xpath = SubmissionsScreenLocators.CLEAR_ALL_BTN) 
	public WebElement ClearAllBtn;

	@FindBy(xpath = SubmissionsScreenLocators.RESUBMIT_ALL_BTN) 
	public WebElement ResubmitAllBtn;

	@FindBy(xpath = SubmissionsScreenLocators.VIEW_CHART_BTN) 
	public WebElement ViewChartBtn;

	@FindBy(xpath = SubmissionsScreenLocators.FIELD_LEVEL_ATTACHMENT_CLOSE_BTN) 
	public WebElement FormAttachmentViewCloseBtn;

	@FindBy(xpath = SubmissionsScreenLocators.NO_SUBMISSIONS_LBL) 
	public WebElement NoSubmissionsLbl;

	/**
	 * This method is used to verify availability of submission
	 * @author pashine_a
	 * @param formName
	 * @return boolean
	 */
	public boolean verifySubmissionAvailability(String formName) {
		String submission;
		try {
			desktopControlActions.sendKeys(SeachSubmissionInp, formName);
			threadsleep(2000);
			submission = desktopControlActions.getDynamicXPath(SubmissionsScreenLocators.SUBMISSIONS_FORM, "FORM_NAME", formName);
			if(!desktopControlActions.isElementAvailable(submission)) {
				logInfo("Submission is not shown");
				return false;
			}
			logInfo("Found submission - "+formName);
			return true;
		}catch(Exception e) {
			logError("Failed to get form - '"+formName+"' - "+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to open & view the submission
	 * @author pashine_a
	 * @param formName
	 * @return boolean
	 */
	public boolean selectOpenSubmission(String formName) {
		WebElement SelectSubmission = null;
		try {
			desktopControlActions.sendKeys(SeachSubmissionInp, formName);
			threadsleep(2000);
			SelectSubmission = desktopControlActions.getDynamicElement(SubmissionsScreenLocators.SELECT_SUBMISSION, "FORM_NAME", formName);
			desktopControlActions.click(SelectSubmission);
			desktopControlActions.waitForElementToBeVisisble(SubmissionLocation);
			desktopControlActions.click(SubmissionBackBtn);
			logInfo("Verified the submission - "+formName);
			return true;
		}catch(Exception e) {
			logError("Failed to open submission - '"+formName+"' - "+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to verify availability NO submissions in SUbmission list
	 * @author pashine_a
	 * @param null
	 * @return boolean
	 */
	public boolean verifyNOSubmissions() {
		try {
			if(desktopControlActions.isElementAvailable(SubmissionsScreenLocators.SUBMISSIONS_SEARCH)) {
				logError("Submissions are shown");
				return false;
			}
			logInfo("Found no submissions");
			return true;
		}catch(Exception e) {
			logError("Failed to verify submissions - "+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to verify the pending submission count of 'Submissions'
	 * @author pashine_a
	 * @param count
	 * @return boolean
	 */
	public boolean verifySubmissionCount(int count) {
		String taskCount = new Integer(count).toString();
		try {
			threadsleep(7000);
			if(!PendingSubmissionCount.getAttribute("Name").equals(taskCount)){
				logError("Submission count is not correct");
				return false;
			}
			logInfo("Verified submission count - "+taskCount);
			return true;
		}catch (Exception e) {
			logError("Failed to verify submission count - "+e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to clear the submission & verify the cleared submissions'
	 * @author pashine_a
	 * @param formName
	 * @param verifySubmission
	 * @return boolean
	 */
	public boolean clearVerifySubmission(boolean verifySubmission) {
		try {
			if(desktopControlActions.isElementAvailable(SubmissionsScreenLocators.SUBMISSIONS_SEARCH)) {
				desktopControlActions.click(ClearAllBtn);
			}else {
				logInfo("Not performed 'CLEAR ALL' action as NO submissions are visible");
				return true;
			}
			threadsleep(2000);
			if(verifySubmission) {
				threadsleep(2000);
				if(desktopControlActions.isElementAvailable(SubmissionsScreenLocators.SUBMISSIONS_SEARCH)) {
					logError("Submissions are shown");
					return false;
				}
			}
			logInfo("Cleared all submissions");
			return true;
		}catch(Exception e) {
			logError("Failed to clear submissions "+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to open the submission
	 * @author pashine_a
	 * @param formName
	 * @return boolean
	 */
	public boolean openSubmission(String formName) {
		WebElement SelectSubmission = null;
		try {
			desktopControlActions.sendKeys(SeachSubmissionInp, formName);
			threadsleep(2000);
			SelectSubmission = desktopControlActions.getDynamicElement(SubmissionsScreenLocators.SELECT_SUBMISSION, "FORM_NAME", formName);
			desktopControlActions.click(SelectSubmission);
			desktopControlActions.waitForElementToBeVisisble(SubmissionLocation);
			logInfo("Opened the submission - "+formName);
			return true;
		}catch(Exception e) {
			logError("Failed to open submission - '"+formName+"' - "+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to verify field details in the submission
	 * @author pashine_a
	 * @param fieldDetails
	 * @return boolean
	 */
	public boolean verifySubmissionDetails(HashMap <String, String> fieldDetails) {
		String fieldName = null, fieldValue = null;
		//		String fieldDetailsXPath = null;
		WebElement fieldElement = null;
		try {
			HashMap<String, String> formsubmissiondetails = fieldDetails;
			for (Map.Entry<String, String> entry : formsubmissiondetails.entrySet()) {
				fieldName = entry.getKey();
				fieldValue = entry.getValue();

				//				fieldDetails = desktopControlActions.getDynamicXPath(SubmissionsScreenLocators.FIELD_VALUE_TXT, "FIELD_NAME", fieldName);
				//				fieldElement = desktopControlActions.getDynamicElement(fieldDetails, "FIELD_VALUE", fieldValue);

				fieldElement = desktopControlActions.getDynamicElement(SubmissionsScreenLocators.FIELD_VALUE_TXT, "FIELD_VALUE", fieldValue);

				if(desktopControlActions.isElementDisplayed(fieldElement)) {
					desktopControlActions.click(fieldElement);
				}else {
					logError("Failed to verify field details for field - "+fieldName);
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
	 * This method is used to open, view & verify field details in the submission
	 * @author pashine_a
	 * @param formName
	 * @param fieldDetails
	 * @return boolean
	 */
	public boolean selectOpenSubmissionVerifyDetails(String formName, HashMap <String, String> fieldDetails) {
		try {
			if(!openSubmission(formName)) {
				logError("Fail to open submission for form - "+formName);
			}
			threadsleep(4000);
			if(!verifySubmissionDetails(fieldDetails)) {
				logError("Failed to verify field details  - "+formName);	
			}
			logInfo("Verified the form submission details for form - "+formName);
			return true;
		}catch(Exception e) {
			logError("Fail to verify submission for form "+ e.getMessage());
			return false;
		}finally {
			desktopControlActions.click(SubmissionBackBtn);
		}
	}

	/**
	 * This method is used to render fields in the submission
	 * @author pashine_a
	 * @param fieldDetails
	 * @return boolean
	 */
	public boolean verifySubmissionFieldDetails(HashMap <String, String> fieldDetails) {
		String fieldName = null, fieldValue = null;
		//		String fieldDetailsXPath = null;
		WebElement fieldElement = null;
		try {
			HashMap<String, String> formsubmissiondetails = fieldDetails;
			for (Map.Entry<String, String> entry : formsubmissiondetails.entrySet()) {
				fieldName = entry.getKey();
				fieldValue = entry.getValue();

				//				fieldDetails = desktopControlActions.getDynamicXPath(SubmissionsScreenLocators.FIELD_VALUE_TXT, "FIELD_NAME", fieldName);
				//				fieldElement = desktopControlActions.getDynamicElement(fieldDetails, "FIELD_VALUE", fieldValue);

				fieldElement = desktopControlActions.getDynamicElement(SubmissionsScreenLocators.FIELD_NAME_TXT, "FIELD_NAME", fieldName);

				if(desktopControlActions.isElementDisplayed(fieldElement)) {
					desktopControlActions.click(fieldElement);
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
	 * This method is used to open, view & render field details in the submission
	 * @author pashine_a
	 * @param formName
	 * @param fieldDetails
	 * @return boolean
	 */
	public boolean selectOpenSubmissionVerifyFieldDetails(String formName, HashMap <String, String> fieldDetails) {
		try {
			if(!openSubmission(formName)) {
				logError("Fail to open submission for form - "+formName);
			}
			threadsleep(4000);
			if(!verifySubmissionFieldDetails(fieldDetails)) {
				logError("Failed to verify field details  - "+formName);	
			}
			logInfo("Verified the form field details in submission for form - "+formName);
			return true;
		}catch(Exception e) {
			logError("Fail to verify field details in submission for form "+ e.getMessage());
			return false;
		}finally {
			desktopControlActions.click(SubmissionBackBtn);
		}
	}

	/**
	 * This method is used to navigate to view & close the chart screen
	 * @author pashine_a
	 * @param null
	 * @return boolean
	 */
	public boolean moveAndCloseChartScreen() {
		try {
			threadsleep(2000);
			while(true) {
				if(desktopControlActions.isElementAvailable(CommonScreenLocators.SPC_CLOSE_BTN)){
					if(!closeChartScreen()) {
						logError("Fail to move to close chart screen");
						return false;
					}
					break;
				}
				if(!moveToNextChart()) {
					logError("Fail to move to next chart");
					return false;
				}
				threadsleep(2000);
			}

			logInfo("Closed chart screen");
			return true;
		}catch(Exception e) {
			logError("Failed to close chart screen - "+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to open & view the chart in the submission
	 * @author pashine_a
	 * @param formName
	 * @return boolean
	 */
	public boolean viewChart(boolean multipleClicks) {
		try {
			desktopControlActions.waitForElementToBeVisisble(SubmissionLocation);

			if(multipleClicks) {
				desktopControlActions.doubleClick(ViewChartBtn);
			}else {
				desktopControlActions.click(ViewChartBtn);
			}
			if(!moveAndCloseChartScreen()) {
				return false;
			}
			desktopControlActions.waitForElementToBeVisisble(SubmissionLocation);
			logInfo("Verified the chart gets opened in submission");
			return true;
		}catch(Exception e) {
			logError("Fail to verify chart in submission for form "+ e.getMessage());
			return false;
		}finally {
			desktopControlActions.click(SubmissionBackBtn);
		}
	}


	/**
	 * This method is used to perform 'RESUBMIT ALL' action on the Submissions screen
	 * @author pashine_a
	 * @param null
	 * @return boolean
	 */
	public boolean performResubmitAll() {
		try {
			desktopControlActions.click(ResubmitAllBtn);
			logInfo("Perfomed 'RESUBMIT ALL' action");
			return true;
		}catch(Exception e) {
			logError("Fail to perform 'RESUBMIT ALL' action - "+ e.getMessage());
			return false;
		}
	}


	/**
	 * This method is used to verify field's attachment in the submission
	 * @author pashine_a
	 * @param fieldName
	 * @param fileName
	 * @return boolean
	 */
	public boolean verifySubmissionAttachment(String fieldName, String fileName) {
		WebElement fieldElement = null, fieldAttachment = null;
		try {
			fieldElement = desktopControlActions.getDynamicElement(SubmissionsScreenLocators.FIELD_NAME_TXT, "FIELD_NAME", fieldName);
			desktopControlActions.click(fieldElement);

			fieldAttachment = desktopControlActions.getDynamicElement(SubmissionsScreenLocators.FIELD_FILE_LNK, "FILE_NAME", fileName);
			desktopControlActions.click(fieldAttachment);
			threadsleep(4000);

			desktopControlActions.clickElement(FormAttachmentViewCloseBtn);
			logInfo("Verified the field details");
			return true;
		}catch(Exception e) {
			logError("Failed to verify field details "+ e.getMessage());
			return false;
		}finally {
			desktopControlActions.click(SubmissionBackBtn);
		}
	}

	/**
	 * This method is used to verify no submissions
	 * @author choubey_a
	 * @param none
	 * @return boolean
	 */
	public boolean verifyNoSubissions() {
		try {
			desktopControlActions.isElementDisplayed(NoSubmissionsLbl);		
			logInfo("Verified no submmions");
			return true;
		}catch(Exception e) {
			logError("Fail to verify no submissions - "+ e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to perform double click on 'Submissions' Tab
	 * @author pashine_a
	 * @param null
	 * @return boolean. If nothing is impacting on double click then returns TRUE
	 */
	public boolean doubleClickSubmissionsTab() {
		try {
			desktopControlActions.doubleClick(SubmissionsMnu);
			if(!desktopControlActions.isElementDisplayed(NoSubmissionsLbl)) {
				logError("Failed to double click on Submissions");
				return false;
			}
			logInfo("Multiple clicks performed on 'Submissions' Tab");
			return true;
		}catch(Exception e) {
			logError("Failed to double click on Submissions - "+ e.getMessage());
			return false;
		}	
	}

}
