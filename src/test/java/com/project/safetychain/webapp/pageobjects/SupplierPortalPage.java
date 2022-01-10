package com.project.safetychain.webapp.pageobjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.project.safetychain.webapp.pageobjects.FSQABrowserRecordsPage.UpdateFieldDets;
import com.project.safetychain.webapp.testcases.support.FormOperations;
import com.project.safetychain.webapp.testcases.support.FormOperations.FormDetails;
import com.project.utilities.ControlActions;

public class SupplierPortalPage extends CommonPage{
	FSQABrowserFormsPage fsqaFormsPage;
	public SupplierPortalPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 40);
		controlActions = new ControlActions(driver);
		fsqaFormsPage = new FSQABrowserFormsPage(driver);
	}

	public static String completeFormTaskType = "fa fa-pencil questionnaire-cell";
	public static String documentUploadTaskType = "fa fa-upload";
	public static String acknowledgementTaskType = "fa fa-flag-o";

	@FindBy(id = SupplierPortalPageLocators.SUBMIT_FORM_BTN)
	public WebElement SubmitFormBtn;
	
	@FindBy(id = SupplierPortalPageLocators.SUBMIT_POPUP_CMMT_TXA)
	public WebElement SubmitPopupCmmtTxa;
	
	@FindBy(id = SupplierPortalPageLocators.HIST_WINDOW_TITLE)
	public WebElement HistWindowTitle;
	
	@FindBy(id = SupplierPortalPageLocators.SAVE_FORM_BTN)
	public WebElement SaveFormBtn;
	
	@FindBy(css = SupplierPortalPageLocators.FORM_REQ_ICON)
	public WebElement FormReqIcon;
	
	@FindBy(css = SupplierPortalPageLocators.DOC_UPLOAD_REQ_ICON)
	public WebElement DocUploadReqIcon;
	
	@FindBy(css = SupplierPortalPageLocators.ACK_REQ_ICON)
	public WebElement AckReqIcon;
	
	@FindBy(className = SupplierPortalPageLocators.RSRC_INST_OPTNS)
	public List<WebElement> RsrcInstOptns;
	
	@FindBy(className = SupplierPortalPageLocators.SUPP_PRTL_TOTAL_CNT_HEADING)
	public WebElement SuppPrtlTotalCntHeading;
	
	@FindBy(className = SupplierPortalPageLocators.SUBMIT_POPUP_TITLE)
	public WebElement SubmitPopupTitle;
	
	@FindBy(className = SupplierPortalPageLocators.SUBMIT_POPUP_MSG)
	public WebElement SubmitPopupMsg;
	
	@FindBy(className = SupplierPortalPageLocators.SUBMIT_POPUP_NOTE)
	public WebElement SubmitPopupNote;
	
	@FindBy(className = SupplierPortalPageLocators.HIST_DATE_INFO)
	public WebElement HistDateInfo;
	
	@FindBy(className = SupplierPortalPageLocators.HIST_TIME_INFO)
	public WebElement HistTimeInfo;
	
	@FindBy(xpath = SupplierPortalPageLocators.SUPPLIER_PORTAL_LBL)
	public WebElement SupplierPortalLbl;

	@FindBy(xpath = SupplierPortalPageLocators.FORM_NAME)
	public WebElement CompleteFormName;

	@FindBy(xpath = SupplierPortalPageLocators.DOCUMENT_NAME)
	public WebElement DocumentUploadName;

	@FindBy(xpath = SupplierPortalPageLocators.SELECT_FILE_BTN)
	public WebElement SelectFileBtn;

	@FindBy(xpath = SupplierPortalPageLocators.DOCUMENT_UPLOAD_INP)
	public WebElement DocumentUploadInp;

	@FindBy(xpath = SupplierPortalPageLocators.DOCUMENT_UPLOAD_BTN)
	public WebElement DocumentUploadBtn;

	@FindBy(xpath = SupplierPortalPageLocators.DOCUMENT_SUBMIT_BTN)
	public WebElement DocumentSubmitBtn;

	@FindBy(xpath = SupplierPortalPageLocators.ACK_SIGN_BTN)
	public WebElement SignAcknowledgementBtn;

	@FindBy(xpath = SupplierPortalPageLocators.CANCEL_FORM_BTN)
	public WebElement FormCancelBtn;

	@FindBy(xpath = SupplierPortalPageLocators.CANCEL_DOCUMENT_BTN)
	public WebElement DocumentCancelBtn;

	@FindBy(xpath = SupplierPortalPageLocators.NO_FILE_POP_UP_OK_BTN)
	public WebElement NoFilePopUpOkBtn;

	@FindBy(xpath = SupplierPortalPageLocators.TASK_SEARCH_INP)
	public WebElement TaskNameSearchInp;

	@FindBy(xpath = SupplierPortalPageLocators.TASK_SEARCH_BTN)
	public WebElement TaskSearchBtn;

	@FindBy(xpath = SupplierPortalPageLocators.TASK_NAME_CLM)
	public WebElement TaskNameClm;
	
	@FindBy(xpath = SupplierPortalPageLocators.COLUMN_NAMES_TBL)
	public List<WebElement> ColumnNamesTbl;
	
	@FindBy(xpath = SupplierPortalPageLocators.SUPP_PRTL_TASK_COUNT)
	public WebElement SuppPrtlTaskCount;
	
	@FindBy(xpath = SupplierPortalPageLocators.SUPP_PRTL_TOTAL_TASK_COUNT)
	public WebElement SuppPrtlTotalTaskCount;
	
	@FindBy(xpath = SupplierPortalPageLocators.DUE_BY_COLMN_NAME)
	public WebElement DueByColmnName;
	
	@FindBy(xpath = SupplierPortalPageLocators.SUPP_STATUS_COLMN_SYMBL)
	public List<WebElement> SuppStatusColmnSymbl;
	
	@FindBy(xpath = SupplierPortalPageLocators.SUPP_GRID_ROW_COUNT)
	public List<WebElement> SuppGridRowCount;
	
	@FindBy(xpath = SupplierPortalPageLocators.SINGLE_SUPP_USR_HEADER)
	public WebElement SingleSuppUsrHeader;
	
	@FindBy(xpath = SupplierPortalPageLocators.SUPP_RSRC_DDL)
	public WebElement SuppRsrcDdl;
	
	@FindBy(xpath = SupplierPortalPageLocators.RSRC_SEARCH_TXT)
	public WebElement RsrcSearchTxt;
	
	@FindBy(xpath = SupplierPortalPageLocators.SUPP_PRTL_REFRESH_BTN)
	public WebElement SuppPrtlRefreshBtn;
	
	@FindBy(xpath = SupplierPortalPageLocators.SUPP_PRTL_REFRESH_TOOLTIP)
	public WebElement SuppPrtlRefreshTooltip;
	
	@FindBy(xpath = SupplierPortalPageLocators.SUBMIT_POPUP_CLOSE_BTN)
	public WebElement SubmitPopupCloseBtn;
	
	@FindBy(xpath = SupplierPortalPageLocators.SUBMIT_POPUP_OK_BTN)
	public WebElement SubmitPopupOkBtn;
	
	@FindBy(xpath = SupplierPortalPageLocators.HIST_ICON)
	public WebElement HistIcon;
	
	@FindBy(xpath = SupplierPortalPageLocators.HIST_ASSGNMNT_INFO)
	public WebElement HistAssgnmntInfo;
	
	@FindBy(xpath = SupplierPortalPageLocators.HIST_DUE_BY_INFO)
	public WebElement HistDueByInfo;
	
	@FindBy(xpath = SupplierPortalPageLocators.HIST_CLOSE_BTN)
	public WebElement HistCloseBtn;

	/** "openCompleteForm" method is used to verify the task type icon & resource name and use to open the task
	 * @author pashine_a
	 * @param String - 'formName'
	 * @param String -'taskName'
	 * @param String - 'resourceName'
	 * @return boolean status
	 * IF it is verified that the task is of 'Complete Form' type & is opened THEN true ELSE false
	 */
	public boolean openCompleteForm(String formName, String taskName, String resourceName) {
		WebElement taskNameTxt, resourceNameTxt, taskTypeIcon;
		String tempXpath =null;
		try {
			taskNameTxt = controlActions.perform_GetElementByXPath(SupplierPortalPageLocators.TASK_NAME_TXT, "TASK_NAME", taskName);
			//resourceNameTxt = controlActions.perform_GetElementByXPath(SupplierPortalPageLocators.TASK_RESOURCE_NAME_TXT, "TASK_NAME", taskName);
			tempXpath = controlActions.perform_GetDynamicXPath(SupplierPortalPageLocators.TASK_RESOURCE_NAME_TXT, "TASK_NAME", taskName);
			resourceNameTxt = controlActions.perform_GetElementByXPath(tempXpath, "RESOURCE", resourceName);
			taskTypeIcon = controlActions.perform_GetElementByXPath(SupplierPortalPageLocators.TASK_TYPE_ICON, "TASK_NAME", taskName);
			if(!(controlActions.isElementDisplay(resourceNameTxt))) {
				logError("Resource is not correct");
				return false;
			}

			/*if(!resourceName.equals((resourceNameTxt.getText().toString()))){
				logError("Resource is not correct");
				return false;
			}*/

			if(!completeFormTaskType.equals((taskTypeIcon.getAttribute("class")))){
				logError("Not having 'Complete Form' task type icon");
				return false;
			}
			controlActions.doubleClickElement(taskNameTxt);
			Sync();
			if(!formName.equals((CompleteFormName.getText().toString()))){
				logError("Form name is not correct");
				return false;
			}		
			logInfo("Verified & opened 'Complete Form' type task");
			return true;
		}catch(Exception e) {
			logError("Failed to verify/open 'Complete Form' type task - "+e.getMessage());
			return false;
		}
	}


	/** "openDocumentUpload" method is used to verify the task type icon & resource name and use to open the task
	 * @author pashine_a
	 * @param String - 'formName'
	 * @param String -'taskName'
	 * @param String - 'resourceName'
	 * @return boolean status
	 * IF it is verified that the task is of 'Document Upload' type & is opened THEN true ELSE false
	 */
	public boolean openDocumentUpload(String documentName, String taskName, String resourceName) {
		WebElement taskNameTxt, resourceNameTxt, taskTypeIcon;
		String tempXpath;
		try {
			taskNameTxt = controlActions.perform_GetElementByXPath(SupplierPortalPageLocators.TASK_NAME_TXT, "TASK_NAME", taskName);
			tempXpath = controlActions.perform_GetDynamicXPath(SupplierPortalPageLocators.TASK_RESOURCE_NAME_TXT, "TASK_NAME", taskName);
			resourceNameTxt = controlActions.perform_GetElementByXPath(tempXpath, "RESOURCE", resourceName);

			taskTypeIcon = controlActions.perform_GetElementByXPath(SupplierPortalPageLocators.TASK_TYPE_ICON, "TASK_NAME", taskName);
			if(!resourceName.equals((resourceNameTxt.getText().toString()))){
				logError("Resource is not correct");
			}
			if(!documentUploadTaskType.equals((taskTypeIcon.getAttribute("class")))){
				logError("Not having 'Document Upload' task type icon");
			}
			controlActions.doubleClickElement(taskNameTxt);
			Sync();
			if(!documentName.equals((DocumentUploadName.getText().toString()))){
				logError("Document name is not correct");
			}		
			logInfo("Verified & opened 'Document Upload' type task");
			return true;
		}catch(Exception e) {
			logError("Failed to verify/open 'Document Upload' type task - "+e.getMessage());
			return false;
		}
	}

	/** "uploadDocument" method is used to upload the document
	 * @author pashine_a
	 * @param String - 'uploadFile'
	 * @return boolean status
	 * IF document is uploaded THEN true ELSE false
	 */
	public boolean uploadDocument(String uploadFile, boolean isUpload) {
		try {
			controlActions.sendKeys(DocumentUploadInp, uploadFile);
			threadsleep(2000);
			if(isUpload) {
				controlActions.clickElement(DocumentUploadBtn);
			}else {
				controlActions.clickElement(DocumentSubmitBtn);
			}
			Sync();
			if(!controlActions.isElementDisplayedOnPage(SupplierPortalLbl)) {
				logError("Task not completed");
				return false;
			}
			logInfo("Document is uploaded");
			return true;
		}catch(Exception e) {
			logError("Failed to upload document - "+e.getMessage());
			return false;
		}
	}

	/** "openAcknowledgement" method is used to verify the task type icon & resource name and use to open the task
	 * @author pashine_a
	 * @param String - 'formName'
	 * @param String -'taskName'
	 * @param String - 'resourceName'
	 * @return boolean status
	 * IF it is verified that the task is of 'Acknowledgement' type & is opened THEN true ELSE false
	 */
	public boolean openAcknowledgement(String documentName, String taskName, String resourceName) {
		WebElement taskNameTxt, resourceNameTxt, taskTypeIcon;
		String tempXpath;
		try {
			taskNameTxt = controlActions.perform_GetElementByXPath(SupplierPortalPageLocators.TASK_NAME_TXT, "TASK_NAME", taskName);
			tempXpath = controlActions.perform_GetDynamicXPath(SupplierPortalPageLocators.TASK_RESOURCE_NAME_TXT, "TASK_NAME", taskName);
			resourceNameTxt = controlActions.perform_GetElementByXPath(tempXpath, "RESOURCE", resourceName);
			taskTypeIcon = controlActions.perform_GetElementByXPath(SupplierPortalPageLocators.TASK_TYPE_ICON, "TASK_NAME", taskName);
			if(!resourceName.equals((resourceNameTxt.getText().toString()))){
				logError("Resource is not correct");
			}
			if(!acknowledgementTaskType.equals((taskTypeIcon.getAttribute("class")))){
				logError("Not having 'Document Upload' task type icon");
			}
			controlActions.doubleClickElement(taskNameTxt);
			controlActions.perform_waitUntilPresent(SupplierPortalPageLocators.DOCUMENT_LOAD_STATUS);
			threadsleep(2000);
			logInfo("Verified & opened 'Acknowdegement' type task");
			return true;
		}catch(Exception e) {
			logError("Failed to verify/open 'Acknowdegement' type task - "+e.getMessage());
			return false;
		}
	}

	/** "signAcknowledgement" method is used to upload the document
	 * @author pashine_a
	 * @param null
	 * @return boolean status
	 * IF task is SIGNED THEN true ELSE false
	 */
	public boolean signAcknowledgement() {
		try {
			controlActions.clickElement(SignAcknowledgementBtn);
			Sync();
			threadsleep(2000);
			if(!controlActions.isElementDisplayedOnPage(SupplierPortalLbl)) {
				logError("Task not completed");
				return false;
			}
			threadsleep(2000);
			logInfo("Document is acknowledged");
			return true;
		}catch(Exception e) {
			logError("Failed to acknowledge document - "+e.getMessage());
			return false;
		}
	}

	/** "submitDocumentUploadTask" method is used to submit the 'Document Upload' task
	 * @author pashine_a
	 * @param String - 'formName'
	 * @param String -'taskName'
	 * @param String - 'resourceName'
	 * @param String - 'uploadFile'
	 * @return boolean status
	 * IF 'Document Upload' type task submitted THEN true ELSE false
	 */
	public boolean submitDocumentUploadTask(String documentName, String taskName, String resourceName, String uploadFile, boolean isUpload) {
		try {
			if(!openDocumentUpload(documentName, taskName,resourceName)) {
				logError("Fail to open/verify the document upload task");
				return false;
			}
			if(!uploadDocument(uploadFile, isUpload)) {
				logError("Fail to upload file in the document upload task");
				return false;
			}
			logInfo("Submitted 'Document Upload' type task");
			return true;
		}catch(Exception e) {
			logError("Failed to submit 'Document Upload' type task - "+e.getMessage());
			return false;
		}
	}


	/** "submitAcknowledgementTask" method is used to submit the 'Acknowledgement' task
	 * @author pashine_a
	 * @param String - 'formName'
	 * @param String -'taskName'
	 * @param String - 'resourceName'
	 * @return boolean status
	 * IF 'Acknowledgement' type task submitted THEN true ELSE false
	 */
	public boolean submitAcknowledgementTask(String documentName, String taskName, String resourceName) {
		try {
			if(!openAcknowledgement(documentName, taskName, resourceName)) {
				logError("Fail to open/verify the acknowledgement task");
				return false;
			}
			if(!signAcknowledgement()) {
				logError("Fail to sign the acknowledgement task");
				return false;
			}
			logInfo("Submitted 'Acknowledgement' type task");
			return true;
		}catch(Exception e) {
			logError("Failed to submit 'Acknowledgement' type task - "+e.getMessage());
			return false;
		}
	}

	/** This method is used to open a task in supplier portal
	 * @author choubey_a
	 * @param String -'taskName'
	 * IF task is opened then returns true
	 */	

	public boolean openTask(String taskname, String taskType) {
		try {
			WebElement taskNameTxt = controlActions.perform_GetElementByXPath(SupplierPortalPageLocators.TASK_NAME_TXT, "TASK_NAME", taskname);
			controlActions.doubleClickElement(taskNameTxt);
			Sync();
			logInfo("Opened task");		
			return true;
		}catch(Exception e) {
			logError("failed to open task- "+e.getMessage());
			return false;
		}
	}

	/** This method is used to submit multiple form tasks on supplier portal
	 * @author choubey_a
	 * @param String[] -'taskName'
	 * IF task is submitted then returns true
	 */	

	public boolean submitformTask(String[] taskname) {
		try {
			for (int i = 0; i < taskname.length; i++) {			
				openTask(taskname[i],"Complete Form");
				fsqaFormsPage.submitData(false, false, false, true,false);
			}
			logInfo("Form Task submitted");
			return true;
		}catch(Exception e) {
			logError("Failed to submit form task- "+e.getMessage());
			return false;
		}	
	}

	/** This method is used to upload multiple upload document tasks on supplier portal
	 * @author choubey_a
	 * @param String[] -'taskName'
	 * IF document is uploaded then returns true
	 */	

	public boolean uploadDocTask(String[] taskname, String documentpath) {
		try {
			for (int i = 0; i < taskname.length; i++) {			
				openTask(taskname[i], "Document Upload");
				uploadDocument(documentpath, true);
			}
			logInfo("Document Upload Task completed");
			return true;
		}catch(Exception e) {
			logError("Failed to completed upload document task- "+e.getMessage());
			return false;
		}	
	}


	/** "verifyRejectedTask" method is used to verify the is rejected
	 * @author pashine_a
	 * @param String -'taskName'
	 * @param boolean -'taskOpened'
	 * @return boolean status
	 * IF it is verified that the task is 'Rejected' THEN true ELSE false
	 */
	public boolean verifyRejectedTask(String taskName, boolean taskOpened) {
		WebElement rejectedTaskNameTxt;
		try {
			if(taskOpened) {
				rejectedTaskNameTxt = controlActions.perform_GetElementByXPath(SupplierPortalPageLocators.OPENED_REJECTED_TASK_NAME_TXT, "TASK_NAME", taskName);
			}else {
				rejectedTaskNameTxt = controlActions.perform_GetElementByXPath(SupplierPortalPageLocators.REJECTED_TASK_NAME_TXT, "TASK_NAME", taskName);
			}
			if(!controlActions.isElementDisplayedOnPage(rejectedTaskNameTxt)){
				return false;		
			}		
			logInfo("Verified 'Rejected' task");
			return true;
		}catch(Exception e) {
			logError("Failed to verify 'Rejected' task - "+e.getMessage());
			return false;
		}
	}

	/** "cancelTask" method is used to Cancel the task
	 * @author pashine_a
	 * @param WebElement - 'cancel'
	 * @return boolean status
	 * IF task is cancelled THEN true ELSE false
	 */
	public boolean cancelTask(WebElement cancel ) {
		try {
			controlActions.clickElement(cancel);
			Sync();
			if(!controlActions.isElementDisplayedOnPage(SupplierPortalLbl)) {
				logError("Supplier Portal page is not visisble");
				return false;
			}
			logInfo("Task is cancelled");
			return true;
		}catch(Exception e) {
			logError("Failed to cancel the task - "+e.getMessage());
			return false;
		}
	}

	/** "verifyNoFileSelected" method is used to verify that the document task should not complete if no file is selected
	 * @author pashine_a
	 * @return boolean status
	 * IF 'No File Selected' pop up is shown THEN true ELSE false
	 */
	public boolean verifyNoFileSelected() {
		try {
			Sync();
			controlActions.clickElement(NoFilePopUpOkBtn);
			if(!controlActions.isElementDisplayedOnPage(SupplierPortalLbl)) {
				logError("Supplier Portal page is not visisble");
				return false;
			}
			logInfo("'No File Selected' pop up is verified");
			return true;
		}catch(Exception e) {
			logError("Failed to verify 'No File Selected' pop up - "+e.getMessage());
			return false;
		}
	}

	/** "verifySearchedTask" method is used to verify searched task
	 * @author pashine_a
	 * @param taskName
	 * @return boolean status
	 * IF searched task is shown THEN true ELSE false
	 */
	public boolean verifySearchedTask(String taskName) {
		try {
			Sync();
			controlActions.sendKeys(TaskNameSearchInp,taskName);
			controlActions.clickElement(TaskSearchBtn);
			Sync();
			threadsleep(2000);
			controlActions.clickElement(TaskNameClm);
			threadsleep(2000);
			Sync();

			WebElement taskNameTxt = controlActions.perform_GetElementByXPath(SupplierPortalPageLocators.TASK_NAME_LST, "TASK_NAME", taskName);
			if(!controlActions.isElementDisplayedOnPage(taskNameTxt)) {
				logError("Task search is not working");
				return false;
			}
			logInfo("Searched task name is verified");
			return true;
		}catch(Exception e) {
			logError("Failed to search the task - "+e.getMessage());
			return false;
		}
	}

	/** "verifyClearedTask" method is used to verify cleared task
	 * @author pashine_a
	 * @param taskName
	 * @return boolean status
	 * IF searched task is not shown in first row THEN true ELSE false
	 */
	public boolean verifyClearedTask(String taskName) {
		String taskNameTxt;
		try {
			Sync();
			controlActions.actionClearTextBox(TaskNameSearchInp);
			Sync();
			threadsleep(2000);
			taskNameTxt = controlActions.perform_GetDynamicXPath(SupplierPortalPageLocators.TASK_NAME_LST, "TASK_NAME", taskName);
			if(controlActions.isElementDisplayed(taskNameTxt)) {
				logError("Task search is not cleared");
				return false;
			}
			logInfo("Cleared task name is verified");
			return true;
		}catch(Exception e) {
			logError("Failed to clear the task - "+e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to find Column Index for the column present in Supplier Portal Grid
	 * @author hingorani_a
	 * @param columnName Column name whose Index you need. Use Class SUPP_PRTL_FIELDS to pass column name.
	 * This Index is in turn used in creating dynamic xpaths. 
	 * @return int This returns value of column index. 
	 * Returns 0, if column is not found
	 */
	public int getColumnIndex(String columnName) {
		int columnIndex = 0; 
		try {
			for(WebElement column : ColumnNamesTbl) {
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
	 * This method is used to search and get Supplier Task detail as per column
	 * @author hingorani_a
	 * @param columnName Column name whose Index you need. Use Class SUPP_PRTL_FIELDS to pass column name.
	 * @return String This returns string value with the validation detail if found; else null
	 */
	public String getSuppTaskDetails(String columnName) {
		WebElement ColumnValue = null; 
		try {
			int columnIndex = getColumnIndex(columnName);
			if(columnIndex == 0) {
				logError("Failed to get correct column index : " + columnIndex + " for column " + columnName);
				return null;
			}
			else {
				ColumnValue = controlActions.perform_GetElementByXPath(SupplierPortalPageLocators.SUPP_PRTL_COLUMN_VALUE, 
						"COLUMNINDEXNO", Integer.toString(columnIndex));

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
	 * This method is used set task detail to be searched to the Search textbox
	 * @author hingorani_a
	 * @param searchTxt Text to be set for search 
	 * @return boolean This returns true when the text is set to the Search textbox
	 */
	public boolean setSearchTxt(String searchTxt) {
		try {
			controlActions.perform_waitUntilClickable(TaskNameSearchInp);
			TaskNameSearchInp.clear();
			TaskNameSearchInp.sendKeys(searchTxt);
			logInfo("Search text set as " + searchTxt);
			return true;
		}
		catch(Exception e) {
			logError("Failed while to set Search text as - " + searchTxt
					+ e.getMessage());
			return false;
		}	
	}
	
	/**
	 * This method is used click on Search button
	 * @author hingorani_a
	 * @param none 
	 * @return boolean This returns true when Search button is clicked successfully
	 */
	public boolean clickSearchBtn() {
		try {
			TaskSearchBtn.click();
			Sync();
			logInfo("Search button is clicked");
			return true;
		}
		catch(Exception e) {
			logError("Failed while to click on Search button "
					+ e.getMessage());
			return false;
		}	
	}
	
	/**
	 * This method is used search by any task detail
	 * @author hingorani_a
	 * @param searchTxt Text to be set for search  
	 * @return boolean This returns true when search operation is done successfully
	 */
	public boolean searchTaskInSuppPortal(String searchTxt) {
		try {
			if(!setSearchTxt(searchTxt))
				return false;
			
			if(!clickSearchBtn())
				return false;
			
			logInfo("Successfully Searched for Task with detail " + searchTxt);
			return true;
		}
		catch(Exception e) {
			logError("Failed to Search task with detail " + searchTxt
					+ e.getMessage());
			return false;
		}	
	}
	
	/**
	 * This method is used to get list of Elements for a column of Supplier Portal grid
	 * @author hingorani_a
	 * @param columnName Use Class SUPP_PRTL_FIELDS to set value for column name
	 * @return List<WebElement> This returns list of WebElements for given column
	 */
	public List<WebElement> getListOfElementsForSupp(String columnName) {
		List<WebElement> ColumnValue = null;
		try {
			int columnIndex = getColumnIndex(columnName);
			if(columnIndex == 0) {
				logError("Failed to get correct column index : " + columnIndex + " for column " + columnName);
				return ColumnValue;
			}
			else {
				ColumnValue = controlActions.perform_GetListOfElementsByXPath(SupplierPortalPageLocators.SUPP_PRTL_COLUMN_VALUE, 
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
	 * This method is used to click on mentioned column of Supplier Portal grid
	 * @author hingorani_a
	 * @param columnName Use Class SUPP_PRTL_FIELDS to set value for column name
	 * @return boolean This returns true when the mentioned column is clicked successfully
	 */
	public boolean clickOnSuppColumn(String columnName) {
		WebElement SuppColmnNames = null;
		
		try {
			SuppColmnNames = controlActions.perform_GetElementByXPath(SupplierPortalPageLocators.SUPP_COLMN_NAMES, 
					"COLUMNNAME", columnName);
			
			SuppColmnNames.click();
			Sync();
			logInfo("Clicked on Supplier column " + columnName);
			return true;
		}
		catch(Exception e) {
			logError("Failed to Click on Supplier column " + columnName
					+ e.getMessage());
			return false;
		}	
	}
	
	/**
	 * This method is used clear the search textbox
	 * @author hingorani_a
	 * @param none  
	 * @return boolean This returns true when search textbox is cleared successfully
	 */
	public boolean clearSearchText() {
		try {
			controlActions.actionClearTextBox(TaskNameSearchInp);
			threadsleep(5000);
			Sync();
			logInfo("Cleared text from Universal Search");
			return true;
		}
		catch(Exception e) {
			logError("Failed to Clear text from Universal Search "
					+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used set the instance from the dropdown list
	 * @author hingorani_a
	 * @param instanceName The name of the instance to be set  
	 * @return boolean This returns true when instance is set successfully
	 */
	public boolean selectResourceInstance(String instanceName) {
		WebElement DropdwnOptn = null;
		
		try {
			controlActions.WaitforelementToBeClickable(SuppRsrcDdl);
			controlActions.perform_ClickWithJavaScriptExecutor(SuppRsrcDdl);
			controlActions.WaitforelementToBeClickable(RsrcSearchTxt);
			controlActions.click(RsrcSearchTxt);
			RsrcSearchTxt.clear();
			RsrcSearchTxt.sendKeys(instanceName);
			Sync();
			DropdwnOptn = controlActions.perform_GetElementByXPath(SupplierPortalPageLocators.DROPDWN_OPTN, 
					"DROPDOWNOPTION", instanceName);
			DropdwnOptn.click();
			Sync();
			logInfo("Resource " + instanceName + " is selected");
			return true;
		}
		catch(Exception e) {
			logError("Failed to select Resource - " + instanceName + " "
					+ e.getMessage());
			return false;
		}
	}
	
	/**
	 * This method is used to get list of Elements for a column of Supplier Portal grid
	 * @author hingorani_a
	 * @param columnName Use Class SUPP_PRTL_FIELDS to set value for column name
	 * @return List<WebElement> This returns list of WebElements for given column
	 */
	public List<String> getListOfValuesForSupp(String columnName) {
		List<WebElement> Columns = null;
		List<String> ColumnValue = new ArrayList<String>();;
		try {
			int columnIndex = getColumnIndex(columnName);
			if(columnIndex == 0) {
				logError("Failed to get correct column index : " + columnIndex + " for column " + columnName);
				return ColumnValue;
			}
			else {
				Columns = controlActions.perform_GetListOfElementsByXPath(SupplierPortalPageLocators.SUPP_PRTL_COLUMN_VALUE, 
						"COLUMNINDEXNO", Integer.toString(columnIndex));
				for(WebElement column : Columns) {
					ColumnValue.add(column.getText().trim());
				}
				logInfo("Retrieved and added list of values for column : " + columnName);
				return ColumnValue;
			}
		}
		catch(Exception e) {
			logError("Failed to get list of values for column : " + columnName + " - "
					+ e.getMessage());
			return ColumnValue;
		}	
	}
	
	/**
	 * This method is used select the mentioned task
	 * @author hingorani_a
	 * @param taskName The name of the task to be selected  
	 * @return boolean This returns true when task is selected successfully
	 */
	public boolean selectTask(String taskName) {
		WebElement TaskNameTxt = null;
		
		try {
			TaskNameTxt = controlActions.perform_GetElementByXPath(SupplierPortalPageLocators.TASK_NAME_TXT, 
					"TASK_NAME", taskName);
			controlActions.click(TaskNameTxt);
			logInfo("Selected task named " + taskName);
			return true;
		}
		catch(Exception e) {
			logError("Failed to select task - " + taskName + " "
					+ e.getMessage());
			return false;
		}
	}
	
	/**
	 * This method is used to click on Cancel button for Form tasks
	 * @author hingorani_a
	 * @param none 
	 * @return boolean This returns true when Cancel button is clicked successfully
	 */
	public boolean clickFormCancelButton() {
		try {
			controlActions.WaitForAnElementToBeClickable(FormCancelBtn);
			FormCancelBtn.click();
			Sync();
			logInfo("Form's cancel button is clicked");
			return true;
		}
		catch(Exception e) {
			logError("Failed while clicking on Form's cancel button - "
					+ e.getMessage());
			return false;
		}	
	}
	
	/**
	 * This method is used to click on Supplier Portal grid's Refresh button
	 * @author hingorani_a
	 * @param none 
	 * @return boolean This returns true when Refresh button is clicked successfully
	 */
	public boolean clickSuppPortalRefreshButton() {
		try {
			controlActions.WaitForAnElementToBeClickable(SuppPrtlRefreshBtn);
			SuppPrtlRefreshBtn.click();
			Sync();
			logInfo("Refresh button is clicked");
			return true;
		}
		catch(Exception e) {
			logError("Failed while clicking on Refresh button - "
					+ e.getMessage());
			return false;
		}	
	}
	
	/**
	 * This method is used to verify the existence of the tooltip for Grid Refresh button 
	 * @author hingorani_a
	 * @param none 
	 * @return boolean This returns true after verifying the tooltip for Refresh button
	 */
	public boolean verifySuppPortalRefreshTooltip() {
		try {
			controlActions.WaitForAnElementToBeClickable(SuppPrtlRefreshBtn);
			controlActions.hoverElement(SuppPrtlRefreshBtn);
			Sync();
			if(SuppPrtlRefreshTooltip.isDisplayed()) {
				logInfo("Refresh button's tooltip is displayed");
				return true;
			}
			logError("Could not verify existence of Refresh button's tooltip");
			return false;
		}
		catch(Exception e) {
			logError("Failed to verify existence of Refresh button's tooltip - "
					+ e.getMessage());
			return false;
		}	
	}
	
	/** This method is used to submit multiple Form tasks on supplier portal
	 * @author hingorani_a
	 * @param taskname An array of form names to be submitted
	 * @param fd Use FormDetails to set values to the form.
	 * Additionally, we can set values for Correction/Attachment/Comments, etc
	 * @param comment Set comment while submitting form if you want, else pass null
	 * @return boolean This returns true if the mentioned Form tasks are submitted
	 */
	public boolean submitformTask(String[] taskname, FormDetails fd, String comment) {
		try {
			for (int i = 0; i < taskname.length; i++) {			
				openTask(taskname[i],"Complete Form");
				FormOperations fo = new FormOperations(driver);
				if(!fo.submitData(fd)) 
					return false;
				
				if(!submitFormTask(comment))
					return false;
				
			}
			logInfo("Form Task submitted");
			return true;
		}catch(Exception e) {
			logError("Failed to submit form task- "+e.getMessage());
			return false;
		}	
	}
	
	/**
	 * This method is used to click on Submit button of Form tasks
	 * @author hingorani_a
	 * @param none 
	 * @return boolean This returns true when Submit button is clicked successfully
	 */
	public boolean clickSubmitFormButton() {
		try {
			controlActions.WaitForAnElementToBeClickable(SubmitFormBtn);
			SubmitFormBtn.click();
			Sync();
			logInfo("Submit Form button is clicked");
			return true;
		}
		catch(Exception e) {
			logError("Failed while clicking on Submit Form button - "
					+ e.getMessage());
			return false;
		}	
	}
	
	/**
	 * This method is used to click Close Submit form task popup
	 * @author hingorani_a
	 * @param none 
	 * @return boolean This returns true when Close button is clicked successfully
	 */
	public boolean closeSubmitPopup() {
		try {
			controlActions.WaitForAnElementToBeClickable(SubmitPopupCloseBtn);
			SubmitPopupCloseBtn.click();
			logInfo("Submit popup is closed");
			return true;
		}
		catch(Exception e) {
			logError("Failed while closing on Submit popup - "
					+ e.getMessage());
			return false;
		}	
	}
	
	/**
	 * This method is used to click on Ok button on Submit form task popup
	 * @author hingorani_a
	 * @param none 
	 * @return boolean This returns true when Ok button is clicked successfully
	 */
	public boolean clickSubmitPopupOkButton() {
		try {
			SubmitPopupOkBtn.click();
			Sync();
			logInfo("Submit popup's Ok button is clicked");
			return true;
		}
		catch(Exception e) {
			logError("Failed while clicking on Submit popup's Ok button - "
					+ e.getMessage());
			return false;
		}	
	}
	
	/**
	 * This method is used to click History Icon for the 1 requirement task displayed
	 * @author hingorani_a
	 * @param none 
	 * @return boolean This returns true when History Icon button is clicked successfully
	 */
	public boolean clickHistoryIcon() {
		try {
			controlActions.WaitForAnElementToBeClickable(HistIcon);
			HistIcon.click();
			Sync();
			logInfo("Clicked on History Icon");
			return true;
		}
		catch(Exception e) {
			logError("Failed while clicking on History Icon - "
					+ e.getMessage());
			return false;
		}	
	}
	
	/**
	 * This method is used to click on Close button on History popup
	 * @author hingorani_a
	 * @param none 
	 * @return boolean This returns true when Close button is clicked successfully
	 */
	public boolean clickCloseHistButton() {
		try {
			controlActions.WaitForAnElementToBeClickable(HistCloseBtn);
			HistCloseBtn.click();
			Sync();
			logInfo("Closed History window");
			return true;
		}
		catch(Exception e) {
			logError("Failed while closing on History window - "
					+ e.getMessage());
			return false;
		}	
	}
	
	/**
	 * This method is used to set comment while submitting Form task 
	 * @author hingorani_a
	 * @param comment The comment to be set. If not, set as null
	 * @return boolean This returns true when comment is set for form task submission
	 */
	public boolean setSubmitPopupComment(String comment) {
		try {
			controlActions.WaitForAnElementToBeClickable(SubmitPopupCmmtTxa);
			SubmitPopupCmmtTxa.sendKeys(comment);
			logInfo("Comment set as " + comment);
			return true;
		}
		catch(Exception e) {
			logError("Failed to set comment - " + comment 
					+ e.getMessage());
			return false;
		}	
	}
	
	/**
	 * This method is used to submit Form task with comment
	 * @author hingorani_a
	 * @param comment The comment to be set. If not, set as null
	 * @return boolean This returns true when form task submission is done successfully
	 */
	public boolean submitFormTask(String comment) {
		try {
			if(!clickSubmitFormButton())
				return false;
			
			if(comment!=null) {
				if(!setSubmitPopupComment(comment))
					return false;
			}
			
			if(!clickSubmitPopupOkButton())
				return false;
			
			logInfo("Submitted Form task");
			return true;
		}
		catch(Exception e) {
			logError("Failed while submitting Form task - "
					+ e.getMessage());
			return false;
		}	
	}
	
	/**
	 * This method is used to update fields of an already filled Form
	 * @author hingorani_a
	 * @param ufd Use Class UpdateFieldDets to set field details like value of field, corrections,
	 * comments, attachment, etc
	 * @return boolean This returns true after updating the Form
	 */
	public boolean refillFormTask(UpdateFieldDets ufd) {
		try {
			FSQABrowserRecordsPage frp = new FSQABrowserRecordsPage(driver);
			if(!frp.updateFieldValues(ufd)) 
				return false;
				
			logInfo("Form Task is filled with new values");
			return true;
		}
		catch(Exception e) {
			logError("Failed to fill Form Task with new values - "+e.getMessage());
			return false;
		}	
	}
	
	/**
	 * This method is used to click Save button of Form task
	 * @author hingorani_a
	 * @param none 
	 * @return boolean This returns true when Save button is clicked successfully
	 */
	public boolean clickSaveFormButton() {
		try {
			controlActions.WaitForAnElementToBeClickable(SaveFormBtn);
			SaveFormBtn.click();
			Sync();
			logInfo("Saved the Form task");
			return true;
		}
		catch(Exception e) {
			logError("Failed while saving Form task - "
					+ e.getMessage());
			return false;
		}	
	}

	public static class SupplierPortalDetails{
		public String userName;
		public String password;
		public String itemsResource;
		public String formTask_Items;
		public String documentTask_Items;
		public String acknowledegmentTask_Items;
		public String suppliersResource;
		public String formTask_Suppliers;
		public String documentTask_Suppliers;
		public String acknowledegmentTask_Suppliers;
	}

	public static class SUPP_PRTL_FIELDS{
		public static final String TASK_NAME = "Task Name";
		public static final String RESOURCE = "Resource";
		public static final String RECEIVED_ON = "Received On";
		public static final String DUE_BY = "Due By";	
	}
	
	public static class SUPP_TASK_TYPE{
		public static final String COMPLETE_FORM = "Complete Form";
		public static final String DOCUMENT_UPLOAD = "Document Upload";
	}

}
