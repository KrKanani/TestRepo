package com.project.safetychain.pcapp.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.project.utilities.ControlActions_PCApp;

import io.appium.java_client.windows.WindowsDriver;

public class InboxScreen  extends CommonScreen{

	public InboxScreen(WindowsDriver<WebElement> driver) {
		super(driver);
		this.PCAppDriver = driver;
		PageFactory.initElements(this.PCAppDriver, this);
		wait = new WebDriverWait(driver, 30);
		desktopControlActions = new ControlActions_PCApp(this.PCAppDriver);
	}

	/**
	 * Page Objects
	 */

	@FindBy(xpath = InboxScreenLocators.INBOX_SEARCH_TXT) 
	public WebElement SearchTaskTxt;

	@FindBy(xpath = InboxScreenLocators.INBOX_TASK_COUNT) 
	public WebElement InboxTaskCount;

	@FindBy(xpath = InboxScreenLocators.NEXT_BTN) 
	public WebElement NextBtn;

	@FindBy(xpath = InboxScreenLocators.TASK_ASSIGNED_TO) 
	public WebElement TaskAssignedWG;

	@FindBy(xpath = InboxScreenLocators.TASK_RESOURCE) 
	public WebElement TaskAssignedResource;

	@FindBy(xpath = InboxScreenLocators.INBOX_REFRESH_BTN) 
	public WebElement InboxRefreshBtn;

	@FindBy(xpath = InboxScreenLocators.INBOX_REFRESH_BTN1) 
	public WebElement InboxRefreshBtn1;

	@FindBy(xpath = InboxScreenLocators.REFRESH_YES_BTN) 
	public WebElement InboxRefreshYesBtn;

	@FindBy(xpath = InboxScreenLocators.SAVE_BTN) 
	public WebElement SaveBtn;

	@FindBy(xpath = InboxScreenLocators.PAST_DUE_LBL) 
	public WebElement PastDueLbl;

	@FindBy(xpath = InboxScreenLocators.DUE_TODAY_LBL) 
	public WebElement DueTodayLbl;

	@FindBy(xpath = InboxScreenLocators.DUE_LATER_LBL) 
	public WebElement DueLaterLbl;

	@FindBy(xpath = InboxScreenLocators.PAST_DUE_SELECT) 
	public WebElement PastDueSlt;

	@FindBy(xpath = InboxScreenLocators.DUE_TODAY_SELECT) 
	public WebElement DueTodaySlt;

	@FindBy(xpath = InboxScreenLocators.DUE_LATER_SELECT) 
	public WebElement DueLaterSlt;

	@FindBy(xpath = InboxScreenLocators.WORKGROUP_BTN) 
	public WebElement WorkgroupBtn;

	@FindBy(xpath = InboxScreenLocators.STATUS_BTN) 
	public WebElement StatusBtn;

	@FindBy(xpath = InboxScreenLocators.DUEBY_BTN) 
	public WebElement DueByBtn;

	@FindBy(xpath = InboxScreenLocators.WORKGROUP_SORT_DRD) 
	public WebElement WorkgroupSortDrd;

	@FindBy(xpath = InboxScreenLocators.STATUS_SORT_DRD) 
	public WebElement StatusSortDrd;

	@FindBy(xpath = InboxScreenLocators.DUEBY_SELECT) 
	public WebElement DueBySelect;

	@FindBy(xpath = InboxScreenLocators.INBOX_LISTVIEW) 
	public WebElement InboxListView;

	@FindBy(xpath = InboxScreenLocators.INVALID_TASK_POPUP) 
	public WebElement InvalidTaskPopUp;

	@FindBy(xpath = InboxScreenLocators.INVALID_TASK) 
	public WebElement InvalidTask;

	@FindBy(xpath = InboxScreenLocators.DELETE_BTN) 
	public WebElement DeleteBtn;

	@FindBy(xpath = InboxScreenLocators.YES_BTN) 
	public WebElement YesBtn;

	@FindBy(xpath = InboxScreenLocators.INBOX_BACK_BTN) 
	public WebElement InboxBackBtn;

	@FindBy(xpath = InboxScreenLocators.RESOURCELOCATION_BACK_BTN) 
	public WebElement ResourceLocationBackBtn;

	@FindBy(xpath = InboxScreenLocators.FORMS_POPUP) 
	public WebElement FormPopUp;

	@FindBy(xpath = InboxScreenLocators.PASSWORD_RESET_INPUT) 
	public WebElement PasswordResetInput;

	@FindBy(xpath = InboxScreenLocators.LOGIN_BTN) 
	public WebElement LoginBtn;

	@FindBy(xpath = InboxScreenLocators.NO_TASK_LBL) 
	public WebElement NoTaskLbl;

	@FindBy(xpath = InboxScreenLocators.RECALLED_TASK) 
	public WebElement RecalledTaskLbl;

	@FindBy(xpath = InboxScreenLocators.OK_BTN) 
	public WebElement OkBtn;

	@FindBy(xpath = InboxScreenLocators.REASSIGNED_TASK) 
	public WebElement ReassignedTaskLbl;

	@FindBy(xpath = InboxScreenLocators.TASK_DETAILS_NO_DUE_DATE) 
	public WebElement NoDueDateTaskDetails;

	/**
	 * This method is used to search the task
	 * @author pashine_a
	 * @param taskName
	 * @return boolean
	 */
	public boolean searchTask(String taskName) {
		WebElement SelectTask = null;
		try {
			desktopControlActions.sendKeys(SearchTaskTxt, taskName);
			threadsleep(2000);
			SelectTask = desktopControlActions.getDynamicElement(InboxScreenLocators.SELECT_TASK, "TASK_NAME", taskName);
			desktopControlActions.waitForElementToBeVisisble(SelectTask);
			logInfo("Found the task - "+taskName);
			return true;
		}catch(Exception e) {
			logError("Failed to search task - '"+taskName+"' - "+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to open the task
	 * @author pashine_a
	 * @param taskName
	 * @param refresh
	 * @return boolean
	 */
	public boolean selectOpenTask(String taskName, boolean refresh) {
		WebElement SelectTask = null;
		try {
			if(refresh) {
				desktopControlActions.click(InboxRefreshBtn);
				desktopControlActions.click(InboxRefreshYesBtn);
				waitToLoadData();
			}
			desktopControlActions.sendKeys(SearchTaskTxt, taskName);
			threadsleep(2000);
			SelectTask = desktopControlActions.getDynamicElement(InboxScreenLocators.SELECT_TASK, "TASK_NAME", taskName);
			desktopControlActions.click(SelectTask);
			desktopControlActions.waitForElementToBeVisisble(NextBtn);
			logInfo("Opened the task - "+taskName);
			return true;
		}catch(Exception e) {
			logError("Failed to open task - '"+taskName+"' - "+ e.getMessage());
			return false;
		}	
	}


	public boolean openTask(String taskName, boolean refresh) {
		WebElement SelectTask = null;
		try {
			if(refresh) {
				desktopControlActions.click(InboxRefreshBtn);
				desktopControlActions.click(InboxRefreshYesBtn);
				waitToLoadData();
			}
			desktopControlActions.sendKeys(SearchTaskTxt, taskName);
			threadsleep(2000);
			SelectTask = desktopControlActions.getDynamicElement(InboxScreenLocators.SELECT_TASK, "TASK_NAME", taskName);
			desktopControlActions.click(SelectTask);
			threadsleep(2000);
			logInfo("Clicked on the task - "+taskName);
			return true;
		}catch(Exception e) {
			logError("Failed to click on task - '"+taskName+"' - "+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to verify the task count of 'Inbox'
	 * @author pashine_a
	 * @param count
	 * @return boolean
	 */
	public boolean verifyTaskCount(int count) {
		String taskCount = new Integer(count).toString();
		try {
			if(!InboxTaskCount.getAttribute("Name").equals(taskCount)){
				logError("Task count is not correct");
				return false;
			}
			logInfo("Verified task count - "+taskCount);
			return true;
		}catch (Exception e) {
			logError("Failed to verify task count - "+e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to verify task details
	 * @author pashine_a
	 * @param taskName
	 * @param workgroupName
	 * @param resourceName
	 * @param taskStatus
	 * @return boolean
	 */
	public boolean verifyTaskDetails(String taskName, String workgroupName, String resourceName, String taskStatus) {
		WebElement SelectTask = null;
		String taskDueType;
		try {
			desktopControlActions.sendKeys(SearchTaskTxt, taskName);
			threadsleep(2000);
			SelectTask = desktopControlActions.getDynamicElement(InboxScreenLocators.SELECT_TASK, "TASK_NAME", taskName);
			desktopControlActions.waitForElementToBeVisisble(SelectTask);
			taskDueType = desktopControlActions.getDynamicXPath(InboxScreenLocators.PAST_DUE_LBL, "TASK_DUE", taskStatus);
			if(!desktopControlActions.isElementDisplayed(taskDueType)) {
				logInfo(taskDueType+" label is not shown");
				return false;
			}
			if(!TaskAssignedWG.getAttribute("Name").equals(workgroupName)) {
				logError("Workgroup name is not correct");
			}
			if(!TaskAssignedResource.getAttribute("Name").equals(resourceName)) {
				logError("Resource name is not correct");
			}
			logInfo("Verified task details - "+taskName);
			return true;
		}catch(Exception e) {
			logError("Failed to verify task details - '"+taskName+"' - "+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to save the task
	 * @author pashine_a
	 * @param null
	 * @return boolean
	 */
	public boolean saveTask() {
		try {
			desktopControlActions.click(SaveBtn);
			threadsleep(2000);
			if(!desktopControlActions.isElementAvailable(CommonScreenLocators.INBOX_MNU)){
				logError("Failed to navigate to main screen");
				return false;
			}
			logInfo("Saved task & navigated to main screen");
			return true;
		}catch(Exception e) {
			logError("Failed to save task & navigate to main screen "+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used refresh the 'Inbox'
	 * @author pashine_a
	 * @param null
	 * @return boolean
	 */
	public boolean refreshInbox() {
		try {
			desktopControlActions.click(InboxRefreshBtn);
			threadsleep(2000);
			desktopControlActions.click(InboxRefreshYesBtn);
			if(!waitToLoadData()) {
				logError("Failed to load data");
			}
			logInfo("Refreshed Inbox");
			return true;
		}catch(Exception e) {
			logError("Failed to refresh inbox - "+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used refresh the 'Inbox' if no task is available
	 * @author choubey_a
	 * @param null
	 * @return boolean
	 */
	public boolean refreshInbox1() {
		try {
			desktopControlActions.click(InboxRefreshBtn1);
			desktopControlActions.click(InboxRefreshYesBtn);
			if(!waitToLoadData()) {
				logError("Failed to load data");
			}
			logInfo("Refreshed Inbox");
			return true;
		}catch(Exception e) {
			logError("Failed to refresh inbox - "+ e.getMessage());
			return false;
		}	
	}
	/**
	 * This method is used to get the task count of 'Inbox'
	 * @author pashine_a
	 * @param null
	 * @return int
	 */
	public int getTaskCount() {
		String taskCount = null;
		try {
			taskCount = InboxTaskCount.getText().toString();
			logInfo("Inbox task count is - "+taskCount);
			return new Integer(taskCount);
		}catch (Exception e) {
			logError("Failed to get task count - "+e.getMessage());
			return 0;
		}
	}

	/**
	 * This method is used to click workgroup button
	 * @author choubey_a
	 * @param null
	 * @return true if workgroup is selected
	 */

	public boolean clickWorkGroup() {
		try {
			desktopControlActions.click(WorkgroupBtn);
			logInfo("Clicked on workgroup button");
			return true;			
		}catch (Exception e) {
			logError("Failed to click on workgroup button - "+e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to click status button
	 * @author choubey_a
	 * @param null
	 * @return true if status is selected
	 */

	public boolean clickStatus() {
		try {
			desktopControlActions.click(StatusBtn);
			logInfo("Clicked on status botton");
			return true;			
		}catch (Exception e) {
			logError("Failed to click on status button - "+e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to click due By button
	 * @author choubey_a
	 * @param null
	 * @return true if due by is selected
	 */


	public boolean clickDueBy() {
		try {
			desktopControlActions.click(DueByBtn);
			logInfo("Clicked on due by botton");
			return true;			
		}catch (Exception e) {
			logError("Failed to click on due by button - "+e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to select workgroup
	 * @author choubey_a
	 * @param wgName
	 * @return true if workgroup is selected
	 */


	public boolean selectWorkgroup(String wgName) {
		try {
			if(!clickWorkGroup()) {
				logError("Not able to click on workgroup button");
				return false;
			}
			desktopControlActions.click(WorkgroupSortDrd);
			WebElement wg = desktopControlActions.getDynamicElement(InboxScreenLocators.WORKGROUP_DRD_SELECT, "WGNAME", wgName);
			desktopControlActions.click(wg);
			WebElement wglbl = desktopControlActions.getDynamicElement(InboxScreenLocators.WORKGROUP_LBL, "WGNAME", wgName);
			if(!desktopControlActions.isElementDisplayed(wglbl)) {
				return false;
			}
			logInfo(wgName +"- Workgroup Selected");
			return true;
		}catch(Exception e) {
			logError("Not able to select workgroup -"+wgName+" - "+e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to select status
	 * @author choubey_a
	 * @param null
	 * @return true if status is selected
	 */

	public boolean selectStatus(String status) {
		try {
			if(!clickStatus()) {
				logError("Not able to click on Status button");
				return false;
			}
			desktopControlActions.click(StatusSortDrd);
			switch(status) {
			case Status.DUELATER:
				desktopControlActions.click(DueLaterSlt);
				if(!desktopControlActions.isElementDisplayed(DueLaterLbl)) {
					return false;
				}
				break;

			case Status.DUETODAY:
				desktopControlActions.click(DueTodaySlt);
				if(!desktopControlActions.isElementDisplayed(DueTodayLbl)) {
					return false;
				}
				break;

			case Status.PASTDUE:
				desktopControlActions.click(PastDueSlt);
				if(!desktopControlActions.isElementDisplayed(PastDueLbl)) {
					return false;
				}
				break;

			default:
				logError("Right input not selected");
			}
			logInfo("Status selected");
			return true;
		}catch(Exception e) {
			logError("Status not selected - "+e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to select date
	 * @author choubey_a
	 * @param null
	 * @return true if date is selected
	 */


	public boolean dueBySelect(String date) {
		try {
			if(!clickDueBy()) {
				logError("Due By button not clicked");
				return false;
			}
			desktopControlActions.click(DueBySelect);
			WebElement dateselect = desktopControlActions.getDynamicElement(InboxScreenLocators.DATE_SELECT, "DATE", date);
			desktopControlActions.click(dateselect);
			logInfo("Due by provided");
			return true;
		}catch(Exception e) {
			logError("Due By input not provided - "+e.getMessage());
			return false;
		}

	}

	/**
	 * This method is used to view list view on inbox screen
	 * @author choubey_a
	 * @param null
	 * @return true if list view is shown
	 */

	public boolean listView() {
		try {
			if(desktopControlActions.isElementDisplayed(InboxListView)) {
				logInfo("Inbox has no tasks");				
			}
			return true;
		}catch(Exception e) {
			logError("Inbox has tasks - "+e.getMessage());
			return false;
		}

	}

	/**
	 * This method is used to click on invalid task pop up
	 * @author choubey_a
	 * @param null
	 * @return true if ok button is clicked
	 */

	public boolean clickInvalidTaskPopUp() {
		try {
			if(!desktopControlActions.isElementDisplayed(InvalidTask)) {				
				logError("Invalid Task Pop Up not displayed");	
				return false;
			}
			desktopControlActions.click(InvalidTaskPopUp);			
			logInfo("Clicked on the Invalid Task Pop Up");		
			return true;
		}catch(Exception e) {
			logError("Failed to click on the invalid task pop up - "+e.getMessage());
			return false;
		}

	}

	/**
	 * This method is used to delete saved task
	 * @author choubey_a
	 * @param null
	 * @return returns true if delete button is clicked
	 */
	public boolean deleteSavedTask() {
		try {
			desktopControlActions.click(DeleteBtn);
			threadsleep(2000);
			desktopControlActions.click(YesBtn);
			logInfo("Clicked on delete button");
			return true;
		}catch(Exception e) {
			logError("Failed to click on delete button "+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to discard open form
	 * @author choubey_a
	 * @param null
	 * @return returns true if form is discarded
	 */
	public boolean discardOpenForm() {
		try {
			desktopControlActions.click(InboxBackBtn);
			threadsleep(2000);
			desktopControlActions.click(FormPopUp);
			logInfo("Discarded the opened form");
			return true;
		}catch(Exception e) {
			logError("Failed to discard form "+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to discard open form without pre selected resource
	 * @author choubey_a
	 * @param null
	 * @return returns true if form is discarded
	 */

	public boolean discardFormWithoutResource() {
		try {
			desktopControlActions.click(ResourceLocationBackBtn);
			threadsleep(2000);
			desktopControlActions.click(FormPopUp);
			logInfo("Discarded the opened form");
			return true;
		}catch(Exception e) {
			logError("Failed to discard form "+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to verify unlinking of workgroup
	 * @author choubey_a
	 * @param null
	 * @return returns true if workgroup is unlinked
	 */

	public boolean verifyWorkgroup(String wgName) {
		try {
			if(!clickWorkGroup()) {
				logError("Not able to click on workgroup button");
				return false;
			}
			desktopControlActions.click(WorkgroupSortDrd);
			String wg = desktopControlActions.getDynamicXPath(InboxScreenLocators.WORKGROUP_DRD_SELECT, "WGNAME", wgName);
			if(!desktopControlActions.isElementAvailable(wg)) {
				logInfo(wgName +"- Workgroup is not attached to the user");	
				return false;
			}
			return true;
		}catch(Exception e) {
			logError("Workgroup "+wgName+" is linked to the logged in user " +e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to verify login of logged in user with updated password
	 * @author choubey_a
	 * @param null
	 * @return returns true if login is performed succesfully
	 */

	public boolean newPasswordChange(String password) {
		try {			
			threadsleep(3000);
			if(!desktopControlActions.isElementDisplayed(InboxScreenLocators.PASSWORD_INPUT_LBL)) {
				logError("Password input label is not shown");
				return false;
			}	
			threadsleep(3000);
			PasswordResetInput.sendKeys(password);
			desktopControlActions.click(LoginBtn);
			if(!waitToLoadData()) {
				logError("Failed to load data");
			}
			logInfo("Logged in with updated password");	
			return true;
		}catch(Exception e) {
			logError("Could not login with updated password" +e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to click Refresh Button
	 * @author choubey_a
	 * @param null
	 * @return boolean
	 */
	public boolean clickrefresh() {
		try {
			desktopControlActions.click(InboxRefreshBtn);
			desktopControlActions.click(InboxRefreshYesBtn);
			logInfo("Clicked on refresh button");
			return true;
		}catch(Exception e) {
			logError("Failed to click on refresh button - "+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to perform double click on 'Inbox' Tab
	 * @author pashine_a
	 * @param null
	 * @return boolean. If nothing is impacting on double click then returns TRUE
	 */
	public boolean doubleClickInbox() {
		try {
			desktopControlActions.doubleClick(InboxMnu);
			if(!desktopControlActions.isElementDisplayed(NoTaskLbl)) {
				logError("Failed to double click on Inbox");
				return false;
			}
			logInfo("Multiple clicks performed on 'Inbox' Tab");
			return true;
		}catch(Exception e) {
			logError("Failed to double click on Inbox - "+ e.getMessage());
			return false;
		}	
	}


	/**
	 * This method is used to get task's due date
	 * @author pashine_a
	 * @param taskName
	 * @return String
	 */
	public String getDueDate(String taskName) {
		try {
			WebElement dueDetails = desktopControlActions.getDynamicElement(InboxScreenLocators.INBOX_TASK_DUE_LBL, "FORM_NAME", taskName); 
			String dueDateTime = dueDetails.getText().toString();
			dueDateTime = dueDateTime.replace("Due : ", "");
			logInfo("Got task due date - "+dueDateTime);
			return dueDateTime;
		}catch(Exception e) {
			logError("Failed to get task due date - "+ e.getMessage());
			return null;
		}	
	}

	/**
	 * This method is used to clear search text
	 * @author choubey_a
	 * @param taskname
	 * @return true is search text is cleared
	 */

	public boolean clearSearchTask() {
		try {			
			desktopControlActions.clear(SearchTaskTxt);		
			logInfo("Cleared the task");
			return true;
		}catch(Exception e) {
			logError("Failed to clear task - "+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to click on task pop up
	 * @author choubey_a
	 * @param null
	 * @return true if ok button is clicked
	 */

	public boolean actionOnTaskPopUp(String action) {

		try {

			switch(action){

			case Action.RECALL:
				if(!desktopControlActions.isElementDisplayed(RecalledTaskLbl)) {				
					logError("Recalled Task Pop Up not displayed");	
					break;
				}
			case Action.REASSIGN:
				if(!desktopControlActions.isElementDisplayed(ReassignedTaskLbl)) {				
					logError("Reassigned Task Pop Up not displayed");	
					break;
				}

			default:
				logError("Invalid action type");
			}
			desktopControlActions.click(OkBtn);			
			logInfo("Clicked on Ok Button");		
			return true;
		}catch(Exception e) {
			logError("Failed to take action on task pop up - "+e.getMessage());
			return false;
		}

	}

	/**
	 * This method is used to verify 'No Due Date' label in task due date details
	 * @author pashine_a
	 * @param null
	 * @return boolean
	 */
	public boolean verifyNoDueDateTaskDetails() {
		try {
			if(!NoDueDateTaskDetails.getText().toString().equals("No Due Date")) {
				logError("Failed to verify 'No Due Date' in task details");
			}
			logInfo("Task Due Date - "+NoDueDateTaskDetails.getText());
			logInfo("'No Due Date' is shown in task details");
			return true;
		}catch(Exception e) {
			logError("Failed to verify 'No Due Date' in task details - "+ e.getMessage());
			return false;
		}	
	}

	public class Status{
		public static final String DUELATER = "Due Later";
		public static final String DUETODAY = "Due Today";
		public static final String PASTDUE = "Past Due";
	}

	public class Action{
		public static final String RECALL = "Recall";
		public static final String REASSIGN = "Reassign";
	}
}
