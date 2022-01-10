package com.project.safetychain.webapp.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.project.utilities.CommonMethods;
import com.project.utilities.ControlActions;

public class InboxPage extends CommonPage {

	WebDriver driver;
	WebDriverWait wait;
	ControlActions controlActions;


	public InboxPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 40);
		controlActions = new ControlActions(driver);	
	}	

	/**
	 * Page Objects
	 */

	@FindBy(id = InboxPageLocators.HIST_WINDOW_TITLE)
	public WebElement HistWindowTitle;

	@FindBy(className = InboxPageLocators.TASK_HIST_DETAILS)
	public List<WebElement> TaskHistDetails;

	@FindBy(xpath = InboxPageLocators.SEARCH_TXTBOX)
	public WebElement searchTxtBox;

	@FindBy(xpath = InboxPageLocators.CLICK_SEARCH_BTN)
	public WebElement clickSearchBtn;

	@FindBy(xpath = InboxPageLocators.BROWSE_INPUT)
	public WebElement BrowseInput;

	@FindBy(xpath = InboxPageLocators.UPLOAD_BTN)
	public WebElement UploadBtn;

	@FindBy(xpath = InboxPageLocators.CONTENT)
	public WebElement Content;

	@FindBy(xpath = InboxPageLocators.APPROVE_BTN)
	public WebElement ApproveBtn;

	@FindBy(xpath = InboxPageLocators.YES_BTN)
	public WebElement YesBtn;

	@FindBy(xpath = InboxPageLocators.REJECT_BTN)
	public WebElement RejectBtn;

	@FindBy(xpath = InboxPageLocators.NOTE_INPUT)
	public WebElement NoteInput;

	@FindBy(xpath = InboxPageLocators.NOTE_BTN)
	public WebElement NoteBtn;

	@FindBy(xpath = InboxPageLocators.TOTAL_TASK_LST)
	public List<WebElement> TotalTasksLst;

	@FindBy(xpath = InboxPageLocators.HIST_ICON)
	public WebElement HistIcon;

	@FindBy(xpath = InboxPageLocators.HIST_CLOSE_BTN)
	public WebElement HistCloseBtn;

	@FindBy(xpath = InboxPageLocators.DELETE_SAVED_TASK_ICON)
	public WebElement DeleteSavedTaskIcon;

	@FindBy(xpath = InboxPageLocators.DELETE_SAVED_FORM_POP_UP_YES_BTN)
	public WebElement DeleteFormYesBtn;

	@FindBy(xpath = InboxPageLocators.CANCEL_BTN)
	public WebElement CancelBtn;

	@FindBy(xpath = InboxPageLocators.FILTER_BTN)
	public WebElement FilterBtn;

	@FindBy(xpath = InboxPageLocators.CLEAR_BTN)
	public WebElement ClearBtn;

	@FindBy(xpath = InboxPageLocators.REFRESH_BTN)
	public WebElement RefreshBtn;
	
	@FindBy(xpath = InboxPageLocators.PREV_BTN)
	public WebElement PrevBtn;
	
	@FindBy(xpath = InboxPageLocators.NEXT_BTN)
	public WebElement NextBtn;
	
	@FindBy(xpath = InboxPageLocators.FIRST_BTN)
	public WebElement FirstBtn;
	
	@FindBy(xpath = InboxPageLocators.LAST_BTN)
	public WebElement LastBtn;
	
	@FindBy(xpath = InboxPageLocators.APPROVAL_FILTER_NAME)
	public WebElement ApprovalFilterName;
	
	@FindBy(xpath = InboxPageLocators.COMPLETE_FORM_FILTER_NAME)
	public WebElement CompleteFormFilterName;
	
	@FindBy(xpath = InboxPageLocators.DOCUMENT_UPLOAD_FILTER_NAME)
	public WebElement DocumentUploadFilterName;
	
	@FindBy(xpath = InboxPageLocators.APPROVAL_TYPE_FILTER_CHECKBOX)
	public WebElement ApprovalTypeFilterCheckbox;
	
	@FindBy(xpath = InboxPageLocators.COMPLETE_FORM_TYPE_FILTER_CHECKBOX)
	public WebElement CompleteFormTypeFilterCheckbox;
	
	@FindBy(xpath = InboxPageLocators.DOCUMENT_UPLOAD_TYPE_FILTER_CHECKBOX)
	public WebElement DocumentUploadTypeFilterCheckbox;
	
	@FindBy(xpath = InboxPageLocators.DUE_BY_FROM_TXTBOX)
	public WebElement FromTxtBox;
	
	@FindBy(xpath = InboxPageLocators.DUE_BY_TO_TXTBOX)
	public WebElement ToTxtBox;
	
	@FindBy(xpath = InboxPageLocators.PAGE_DRPDWN)
	public WebElement PageDropDown;
	
	@FindBy(xpath = InboxPageLocators.TASK_TOTAL)
	public WebElement TaskTotal;
	
	
	/**
	 * Functions
	 */

	/**
	 * This method is used to search task
	 * @author choubey_a
	 * @param taskName The Task name
	 * @return boolean This returns true if Task name is entered successfully.
	 */
	public boolean enterTaskName(String taskName) {
		try {
			controlActions.WaitforelementToBeClickable(searchTxtBox);
			searchTxtBox.clear();
			controlActions.sendKeys(searchTxtBox,taskName);
			Sync();
			logInfo("Task named entered: " + taskName);
			return true;
		}
		catch(Exception e) {
			logError("Failed to enter Task name - " + taskName + " - "
					+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to click search button
	 * @author choubey_a
	 * @return boolean This returns true if click on search button successfully.
	 */
	public boolean clickSearchButton() {
		try {
			controlActions.WaitforelementToBeClickable(clickSearchBtn);
			controlActions.clickbutton(clickSearchBtn);
			Sync();
			logInfo("Clicked on Search button");
			return true;
		}
		catch(Exception e) {
			logError("Failed to click on Search button - "
					+ e.getMessage());
			return false;
		}	
	}


	/**
	 * This method is used to complete document task
	 * @author choubey_a
	 * @param taskname and addfile
	 * @return boolean This returns true if document task will be completed
	 */


	public boolean documentUploadTask(String taskname,String addfile) {
		try {
			enterTaskName(taskname);
			clickSearchButton();
			WebElement taskselect = controlActions.perform_GetElementByXPath(InboxPageLocators.TASKNAME_COL_DATA,"TASK",taskname);
			controlActions.doubleClick(taskselect);
			Sync();
			controlActions.sendKeys(BrowseInput,addfile);
			Sync();
			controlActions.clickOnElement(UploadBtn);
			Sync();
			logInfo("Document Upload Task completed");		
			return true;
		}
		catch(Exception e) {
			logError("Document Upload Task failed"+ e.getMessage());
			return false;

		}
	}

	/**
	 * This method is used to select form task
	 * @author choubey_a
	 * @param formtaskname
	 * @return boolean This returns true if form task is selected
	 */

	public boolean selectFormTask(String formtaskname) {
		try {
			enterTaskName(formtaskname);
			clickSearchButton();
			Sync();
			WebElement taskselect = controlActions.perform_GetElementByXPath(InboxPageLocators.TASKNAME_COL_DATA,"TASK",formtaskname);
			controlActions.doubleClick(taskselect);
			Sync();	
			logInfo("Form Task selected");
			return true;
		}
		catch(Exception e) {
			logError("Faied to select Form Task" +e.getMessage());
			return false;

		}
	}

	/**
	 * This method is used to approve task
	 * @author choubey_a
	 * @param taskname
	 * @return boolean This returns true if task is approved
	 */

	public boolean approveTask(String taskname) {
		try {
			enterTaskName(taskname);
			clickSearchButton();
			Sync();
			WebElement taskselect = controlActions.perform_GetElementByXPath(InboxPageLocators.TASKNAME_COL_DATA,"TASK",taskname);
			controlActions.doubleClick(taskselect);
			Sync();
			threadsleep(5000);
			controlActions.WaitforelementToBeClickable(ApproveBtn);
			controlActions.clickOnElement(ApproveBtn);
			threadsleep(3000);
			controlActions.WaitforelementToBeClickable(YesBtn);
			controlActions.clickOnElement(YesBtn);
			Sync();
			logInfo("Task approved");		
			return true;
		}
		catch(Exception e) {
			logError("Task not approved - " + e.getMessage());
			return false;

		}		
	}

	/**
	 * This method is used to reject task
	 * @author choubey_a
	 * @param taskname
	 * @return boolean This returns true if task is rejected
	 */

	public boolean rejectTask(String taskname) {
		try {
			enterTaskName(taskname);
			clickSearchButton();
			Sync();
			WebElement taskselect = controlActions.perform_GetElementByXPath(InboxPageLocators.TASKNAME_COL_DATA,"TASK",taskname);
			controlActions.doubleClick(taskselect);
			Sync();
			threadsleep(5000);
			controlActions.WaitforelementToBeClickable(RejectBtn);
			controlActions.clickOnElement(RejectBtn);
			Thread.sleep(2000);
			controlActions.clickOnElement(NoteInput);
			controlActions.sendKeys(NoteInput,"Rejecting the task");
			Thread.sleep(2000);
			controlActions.WaitforelementToBeClickable(NoteBtn);
			controlActions.clickOnElement(NoteBtn);
			Sync();
			logInfo("Task rejected");		
			return true;
		}
		catch(Exception e) {
			logError("Task not rejected - " + e.getMessage());
			return false;

		}		
	}

	/**
	 * This method is used to accept or reject approval tasks
	 * @author choubey_a
	 * @param taskname[] - Array of task name to perform actions
	 * @param boolean approve - if set true, approve functionality will be performed
	 * @param boolean reject - if set true, reject functionality will be performed
	 * @return boolean This returns true if action on the task is performed
	 */

	public boolean actionOnTask(String taskname[],boolean approve,boolean reject) {
		try {
			for(int i = 0;i<taskname.length;i++) {
				enterTaskName(taskname[i]);
				clickSearchButton();
				Sync();
				WebElement taskselect = controlActions.perform_GetElementByXPath(InboxPageLocators.TASKNAME_COL_DATA,"TASK",taskname[i]);
				controlActions.doubleClick(taskselect);
				Sync();
				if(approve) {
					Sync();	
					controlActions.WaitforelementToBeClickable(ApproveBtn);
					controlActions.clickOnElement(ApproveBtn);
					threadsleep(3000);
					controlActions.WaitforelementToBeClickable(YesBtn);
					controlActions.clickOnElement(YesBtn);
					Sync();
					logInfo("Task approved");
				}
				else if(reject) {
					Sync();
					controlActions.WaitforelementToBeClickable(RejectBtn);
					controlActions.clickOnElement(RejectBtn);
					threadsleep(3000);
					controlActions.clickOnElement(NoteInput);
					controlActions.sendKeys(NoteInput,"Rejecting the task");
					Thread.sleep(2000);
					controlActions.WaitforelementToBeClickable(NoteBtn);
					controlActions.clickOnElement(NoteBtn);
					Sync();
					logInfo("Task rejected");
				}
			}
			logInfo("Action performed on the task");
			return true;
		}
		catch(Exception e) {
			logError("Failed to perform action in the task- " + e.getMessage());
			return false;

		}		

	}

	/**
	 * This method is used to verify that only single task should be shown to the user
	 * @author pashine_a
	 * @param String taskName
	 * @return boolean This returns true if single given task is found
	 */
	public boolean verifySingleTask(String taskName) {
		try {
			WebElement taskSelect = controlActions.perform_GetElementByXPath(InboxPageLocators.FIRST_TASK_DATA,"TASK",taskName);
			if(!controlActions.isElementDisplayedOnPage(taskSelect)){
				logError("Task is not present");
				return false;
			}
			if(TotalTasksLst.size()!=1) {
				logError("Getting more than 1 task");
				return false;
			}
			logInfo("Verified the task in Inbox");
			return true;
		}catch(Exception e) {
			logError("Failed verify task - " + e.getMessage());
			return false;
		}
	}


	/**
	 * This method is used to search task in the inbox
	 * @author ahmed_tw
	 * @param taskName The Task name to be searched
	 * @return boolean This returns true post searching task Name
	 */
	public boolean searchTaskName(String taskName) {
		try {

			enterTaskName(taskName);
			clickSearchButton();
			logInfo("Task -> " + taskName + " searched successfully");
			return true;
		}
		catch(Exception e) {
			logError("Failed to search the task - > " + taskName + " - "
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
	 * This method is used to verify Task History details
	 * @author hingorani_a
	 * @param detail Task History detail like date/timezone/messages/due by etc to be verified
	 * separated by '|'
	 * @return boolean This returns true after verifying Task History details
	 */
	public boolean verifyTaskHistoryPopupDetails(String detail) {
		String[] values;
		int count = 0, valueCount = 0, recordFound = 0;
		try {
			for(WebElement histDetail : TaskHistDetails) {

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
	 * This method is used to click on Close button on History popup
	 * @author hingorani_a
	 * @param none 
	 * @return boolean This returns true when Close button is clicked successfully
	 */
	public boolean closeHistoryPopup() {
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
			if(controlActions.isElementDisplayed(HistCloseBtn)) {
				closeHistoryPopup();
			}
		}
	}

	/**
	 * This method is used to verify that task status is Saved or not
	 * @author pashine_a
	 * @param String taskName
	 * @return boolean This returns true if task status is Saved
	 */
	public boolean verifySavedTaskStatus(String taskName) {
		try {
			WebElement savedTask = controlActions.perform_GetElementByXPath(InboxPageLocators.TASK_SAVE_ICON,"TASK_NAME",taskName);
			if(!controlActions.isElementDisplayedOnPage(savedTask)){
				logError("Saved Task is not present");
				return false;
			}
			logInfo("Verified the saved task status in Inbox");
			return true;
		}catch(Exception e) {
			logError("Failed verify saved task - " + e.getMessage());
			return false;
		}
	}

	/** 'deleteSavedTask' method is used to delete the opened saved task
	 * @author pashine_a
	 * @param null
	 * @return boolean status
	 * IF form delete action performed THEN true ELSE false
	 */	
	public boolean deleteSavedTask() {
		try {
			controlActions.clickElement(DeleteSavedTaskIcon);
			controlActions.clickElement(DeleteFormYesBtn);
			Sync();
			logInfo("Deleted the task");
			return true;
		}catch(Exception e) {
			logError("Failed to delete the task - "+e.getMessage());
			return false;
		}
	}

	/** 'openCloseTask' method is used to open TaskName and close it
	 * @author pednekar_pr
	 * @param Taskname
	 * @return true if open and close task successfully
	 */	
	public boolean openCloseTask(String Taskname) {
		try {
			Sync();
			WebElement EditTaskName = controlActions.perform_GetElementByXPath(InboxPageLocators.EDIT_ICON, "TASK_NAME", Taskname);
			logInfo("Task succesfully opened ");
			controlActions.doubleClick(EditTaskName);
			Sync();
			threadsleep(5000);
			controlActions.WaitforelementToBeClickable(ApproveBtn);
			controlActions.clickOnElement(ApproveBtn);
			threadsleep(3000);
			controlActions.WaitforelementToBeClickable(YesBtn);
			controlActions.clickOnElement(YesBtn);
			Sync();
			logInfo("Task approved");	
			logInfo("Task succesfully closed ");
			Sync();
			return true;

		}catch(Exception e) {
			logError("Failed to open and close the task - "+e.getMessage());
			return false;
		}
	}

	/**  compareSearchBoxText method is used to compare before and after text in Searchbox
	 * @author pednekar_pr
	 * @param Taskname
	 * @return true if open and close task successfully
	 */	
	public boolean compareSearchBoxText(String Taskname) {
		try {
			Sync();
			//get text of searchbox
			controlActions.WaitforelementToBeClickable(searchTxtBox);
			String searchText=searchTxtBox.getCssValue("value");
			if (searchText.compareTo(Taskname)==1)
			{
				logInfo("Before After text of searchbox match succesfully");
				return true;
			}
			else
			{
				logError("Before after text of searchbox didn't match");
				return false;
			}

		}catch(Exception e) {
			logError("Failed to compare before & aftertext of searchbox - "+e.getMessage());
			return false;
		}
	}

	/**  verifyTaskTypeFilters method is used to verify whetehr all 3 task type filter
	 * Approval, Complete Form and Document Upload are displayed
	 * @author pednekar_pr
	 * @return true if all 3 task type filters are displayed
	 */	
	public boolean verifyTaskTypeFilters() {
		try {
			Sync();
			//get text of searchbox
			if (controlActions.isElementDisplay(ApprovalFilterName) && controlActions.isElementDisplay(CompleteFormFilterName) && controlActions.isElementDisplay(DocumentUploadFilterName) )
			{
				logInfo("Before After text of searchbox match succesfully");
				return true;
			}
			else
			{
				logError("Not all 3 Task Type filters are present");
				return false;
			}

		}catch(Exception e) {
			logError("Failed to verify whether all 3 task type filters are present or not "+e.getMessage());
			return false;
		}
	}

	/**  verifyTaskTypeFilters method is used to check given checkbox for any task from available 3 task type filter
	 * Approval, Complete Form and Document Upload 
	 * @param filterTxt is the string containing any one of the above mentioned Task Type filter
	 * @author pednekar_pr
	 * @return true if succesfully checked checkbox
	 */	
	public boolean checkTaskTypeFiltercheckbox(String filterTxt) {
		try {
			Sync();
			//get text of searchbox

			
			if (filterTxt=="Approval")
			{
				controlActions.click(ApprovalTypeFilterCheckbox);
				if(ApprovalTypeFilterCheckbox.isSelected())
				{
					controlActions.click(FilterBtn);
					logInfo("Succesfully checked checkbox for "+ filterTxt);
					return true;
				}
				else
				{
					logError("Document Upload is unchecked");
					return false;
				}
			}
			else if(filterTxt=="Complete Form")
			{
				controlActions.click(CompleteFormTypeFilterCheckbox);
				if(CompleteFormTypeFilterCheckbox.isSelected())
				{
					controlActions.click(FilterBtn);
					logInfo("Succesfully checked checkbox for "+ filterTxt);
					return true;
				}
				else
				{
					logError("Document Upload is unchecked");
					return false;
				}
			}
			else if(filterTxt=="Document Upload")
			{
				controlActions.click(DocumentUploadTypeFilterCheckbox);
				if(DocumentUploadTypeFilterCheckbox.isSelected())
				{
					controlActions.click(FilterBtn);
					logInfo("Succesfully checked checkbox for "+ filterTxt);
					return true;
				}
				else
				{
					logError("Document Upload is unchecked");
					return false;
				}
			}
			else
			{
				logError("Not all 3 Task Type filters are present");
				return false;
			}

		}catch(Exception e) {
			logError("Failed to verify whether all 3 task type filters are present or not "+e.getMessage());
			return false;
		}
	}

	/**  clickClearBtn method is used to click Clear Button
	 * @author pednekar_pr
	 * @return true if clear btn is clicked
	 */	
	public boolean clickClearBtn() {
		try {
			if (controlActions.isElementDisplay(ClearBtn) && controlActions.isElementDisplay(FilterBtn))
			{
				controlActions.click(ClearBtn);
				logInfo("Succesfully clicked Clear btn");
				if(uncheckTaskFilters())
					logInfo("Verified that clear filter is applied");	
				return true;
			}
			else
			{
				logError("Failed to click Clear btn");
				return false;
			}

		}catch(Exception e) {
			logError("Failed to click Clear btn "+e.getMessage());
			return false;
		}
	}

	/**  clickClearBtn method is used to check all 3 Task Filter checkbox are unchecked
	 * Approval, Complete Form, Document Upload
	 * @author pednekar_pr
	 * @return true if all 3 task filter checkbox are unchecked
	 */	
	public boolean uncheckTaskFilters() {
		try {
			Sync();
			//get text of searchbox
			int f=0;
			if( controlActions.isElementDisplay(ApprovalTypeFilterCheckbox)  && (!ApprovalTypeFilterCheckbox.isSelected()))
			{
				logInfo("Succesfully checked checkbox for ApprovalCheckbox");
				f++;
			}

			if( controlActions.isElementDisplay(CompleteFormTypeFilterCheckbox)  && (!CompleteFormTypeFilterCheckbox.isSelected()))
			{
				logInfo("Succesfully checked checkbox for ");
				f++;
			}
			if( controlActions.isElementDisplay(DocumentUploadTypeFilterCheckbox)  && (!DocumentUploadTypeFilterCheckbox.isSelected()))
			{
				logInfo("Succesfully unchecked checkbox for ");
				f++;
			}
			if(f!=3)
			{
				logError("Failed to uncheck all 3 task filters");
				return false;
			}
			else
			{
				logInfo("All3 Task Filter checkboxes are unchecked");
				return true;
			}

		}catch(Exception e) {
			logError("Failed to verify whether all 3 task type filters are unchecked or not "+e.getMessage());
			return false;
		}
	}

	/**  refreshInboxPage method is used to refresh Inbox Page
	 * Approval, Complete Form, Document Upload
	 * @author pednekar_pr
	 * @return true if Inbox Page is succesfully refreshed
	 */	
	public boolean refreshInboxPage() {
		try {
			if(controlActions.isElementDisplay(RefreshBtn))
			{
				controlActions.click(RefreshBtn);
				logInfo("Succesfully checked checkbox for ApprovalCheckbox");
				return true;
			}
			else
			{
				logInfo("All3 Task Filter checkboxes are unchecked");
				return false;
			}

		}catch(Exception e) {
			logError("Failed to verify whether all 3 task type filters are unchecked or not "+e.getMessage());
			return false;
		}
	}

	/**  workGroupFilterCheckbox method is used to check given work group check-box 
	 * @param workGroupText string contains work group name
	 * @author pednekar_pr
	 * @return true if successfully checked check-box
	 */	
	public boolean workGroupFilterCheckbox(String workGroupText) {
		try {
			Sync();
			//get text of searchbox

			WebElement workGroupName=controlActions.perform_GetElementByXPath(InboxPageLocators.WORK_GROUP_FILTER_CHECKBOX, "WG_NAME", workGroupText);
			if(controlActions.isElementDisplay(workGroupName))
			{
				controlActions.click(workGroupName);
				if (workGroupName.isSelected())
				{
					controlActions.click(FilterBtn);
					//logInfo("Succesfully checked checkbox for "+ filterTxt);
					return true;
				}
				else
					return false;
			}
			else
			{
				logError(workGroupText +" is not displayed under Filter ");
				return false;
			}

		}catch(Exception e) {
			logError("Failed to verify whether all 3 task type filters are present or not "+e.getMessage());
			return false;
		}
	}

	/**  verifyApproveTask method is used to verify Approve task
	 * @param taskName is string containing name of task
	 * @author pednekar_pr
	 * @return true if approved task is visible
	 */	
	public boolean verifyTaskTotal(boolean searchCompleteForm, String count) {
		try {
			
			String count1=TaskTotal.getText();
			
			if(Integer.parseInt(count1)==Integer.parseInt(count)-1)
			{
				logInfo("count was "+ count + "new count is"+ count1);
				logInfo("Verified Task Total");
			return true;
			}
			else
			{
				return false;
			}
			
			
		}catch(Exception e) {
			logError("Failed to verify Task Total Clear btn "+e.getMessage());
			return false;
		}
	}
	
	/**  paginationDropdown method is used to select given pageNumber count
	 * @param pageNumber is string containing pageNumber
	 * @author pednekar_pr
	 * @return true if approved task is visible
	 */	
	public boolean paginationDropdown(String pageNumber) {
		try {
			
			//controlActions.click(PageDropDown);
//select kendo pending
			String selector = "span[class='k-widget k-dropdown k-header'] > span ";
			String value = controlActions.perform_GetDynamicXPath(InboxPageLocators.PAGE_DRPDWN_OPTIONS,"VALUE",pageNumber);
			//value="//div[@class='k-animation-container']//li[text()='200']";
			controlActions.setKendoDropDownValue(driver,selector,value);
			Sync();

			logInfo("Succesfully selected given "+ pageNumber + "value from dropdown");
			return true;

		}
		catch(Exception e) 
			{
			logError("Failed to click dropdown btn "+e.getMessage());
			return false;
		}
	}
	
	/**  paginationBtn method is used to click given type of buttonType
	 * @param buttonType is string containing button type i.e Prev, Next or FirstPrev
	 * @author pednekar_pr
	 * @return true if button is clicked
	 */	
	public boolean paginationBtn(String buttonType) {
		try {
			
			switch(buttonType)
			{
			
			case "Prev":
			{
			
				controlActions.click(PrevBtn);
				logInfo("succesfully clicked prev btn");
				return true;
			}
			
			case "Next":
			{
				controlActions.click(NextBtn);
				logInfo("succesfully clicked Next btn");
				return true;
			}
			
			case "First":
			{
				if(controlActions.isElementDisplay(FirstBtn))
				{
					controlActions.click(FirstBtn);
				
				logInfo("succesfully clicked First btn");
				return true;
				}
				else
				{
					logError("Failed to display First Btn");
					return false;
				}
			}
			
			case "Last":
			{
				if(controlActions.isElementDisplay(LastBtn))
				{
				controlActions.click(LastBtn);
				logInfo("succesfully clicked Last btn");
				return true;
				}
				else
				{
					logError("Failed to display First Btn");
					return false;
				}
			}
			
			 default:
			 {
				 logInfo("Invalid option");
				 return false;
				 
			 }
			 
				
			}
			

		}catch(Exception e) {
			logError("Failed to click given btn "+e.getMessage());
			return false;
		}
	}
	
	/**  paginationDropdown method is used to check whether task with given taskName is visible in Inbox post search
	 * @param taskName is string containing name of task
	 * @author pednekar_pr
	 * @return true if task is visible in Inbox
	 */	
	public boolean taskVisible(String taskName) {
		try {
			
			Sync();
			WebElement value = controlActions.perform_GetElementByXPath(InboxPageLocators.TASK_NAME,"TASK_NAME",taskName);
			if(controlActions.isElementDisplay(value))
			{
			
				logInfo("Succesfully displayed given task "+taskName);
				return true;
			}
			else
			{
				logInfo("Failed to display given task "+taskName);
				return false;
			}
			

		}
		catch(Exception e) 
			{
			logError("Failed to click dropdown btn "+e.getMessage());
			return false;
		}
	}
	
	
	/**  workGroupFilterCheckbox method is used to check given work group check-box 
	 * @param workGroupText string contains work group name
	 * @author pednekar_pr
	 * @return true if successfully checked check-box
	 */	
	public boolean statusFilterCheckbox(String statusText) {
		try {
			Sync();
			//get text of searchbox

			WebElement statusType=controlActions.perform_GetElementByXPath(InboxPageLocators.STATUS_FILTER_CHECKBOX, "STATUS_TYPE", statusText);
			if(controlActions.isElementDisplay(statusType))
			{
				controlActions.click(statusType);
				if (statusType.isSelected())
				{
					controlActions.click(FilterBtn);
					//logInfo("Succesfully checked checkbox for "+ filterTxt);
					return true;
				}
				else
					return false;
			}
			else
			{
				logError(statusType +" is not displayed under Filter ");
				return false;
			}

		}catch(Exception e) {
			logError("Failed to verify whether all 3 task type filters are present or not "+e.getMessage());
			return false;
		}
	}
	
	/**
	 * searchResult method is used to search task in the inbox
	 * @author pednekar_pr
	 * @param taskName The Task name to be searched
	 * @return boolean This returns true when taskName is found
	 */
	public boolean searchResult(String taskName) {
		try {

			WebElement task= controlActions.perform_GetElementByXPath(InboxPageLocators.TASK_NAME,"TASK_NAME", taskName);
			if(controlActions.isElementDisplay(task))
			logInfo("Searched task -> " + taskName + " found successfully");
			return true;
		}
		catch(Exception e) {
			logError("Failed to find the given searched task - > " + taskName + " - "
					+ e.getMessage());
			return false;
		}	
	}
	/**  This method is used to check whether task with associated ResourceName is present in Inbox post search
	 * @param resourceName is string containing name of resource
	 * @author jadhav_akan
	 * @return true if resource is present in Inbox
	 */	
	public boolean associatedResourceIsPresent(String resourceName) {
		try {
			
			Sync();
			WebElement value = controlActions.perform_GetElementByXPath(InboxPageLocators.RESOURCE_NAME,"RESOURCE_NAME",resourceName);
			if(controlActions.isElementDisplay(value))
			
			logInfo("Resource "+resourceName+ " is present");
			return true;
		}
		catch(Exception e) 
			{
			logError("Resource " +resourceName+" is not present "+e.getMessage());
			return false;
		}
	}
	
	/**  This method is used to check  ResourceName is not present in Inbox with Associated WG
	 * @param resourceName is string containing name of resource
	 * @author jadhav_akan
	 * @return true if resource is not present in Inbox
	 */	
	public boolean resourceIsNotPresent(String resourceName) {
		try {
			
			Sync();
			WebElement value = controlActions.perform_GetElementByXPath(InboxPageLocators.RESOURCE_NAME,"RESOURCE_NAME",resourceName);
			if(!controlActions.isElementDisplay(value))
			
			logInfo("Resource "+resourceName+ " is not present");
			return true;
		}
		catch(Exception e) 
			{
			logError("Resource " +resourceName+" is present "+e.getMessage());
			return false;
		}
	}
	
	/**  ApplyDueByFilter method is used to apply due by filter
	 * @param from is the string containing from date
	 * @param to is the string containing to date
	 * @author pednekar_pr
	 * @return true if succesfully checked checkbox
	 */	
	public boolean ApplyDueByFilter(String from, String to) {
		try {
			Sync();
			//get text of searchbox
			if ((from!=null) &&(to!=null))
			{
				controlActions.sendKeys(FromTxtBox, from);
				controlActions.sendKeys(ToTxtBox, to);
				controlActions.click(FilterBtn);
				return true;
			}
			else if ((from!=null) &&(to==null))
			{
				controlActions.sendKeys(FromTxtBox, from);
				controlActions.click(FilterBtn);
				return true;
			}
			else if ((from==null) &&(to!=null))
			{
				controlActions.sendKeys(ToTxtBox, to);
				controlActions.click(FilterBtn);
				return true;
			}
			else
			{
				logError("Not all 3 Task Type filters are present");
				return false;
			}

		}catch(Exception e) {
			logError("Failed to verify whether all 3 task type filters are present or not "+e.getMessage());
			return false;
		}
	}

	
}
