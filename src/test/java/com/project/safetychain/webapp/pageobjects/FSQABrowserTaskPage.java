package com.project.safetychain.webapp.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.project.utilities.ControlActions;

public class FSQABrowserTaskPage extends CommonPage{

	WebDriver driver;
	WebDriverWait wait;
	ControlActions controlActions;
	FSQABrowserPageLocators fbp;

	public FSQABrowserTaskPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 40);
		controlActions = new ControlActions(driver);	
	}	


	/**
	 * Page Objects
	 */

	@FindBy(xpath = FSQABrowserTaskPageLocators.TASKHISTORY_MNU)
	public WebElement TaskHistoryMnu;

	@FindBy(xpath = FSQABrowserTaskPageLocators.CLOSE_POPUP)
	public WebElement ClosePopup;

	@FindBy(xpath = FSQABrowserPageLocators.CALLOUT_MNU)
	public WebElement CalloutMenu;
	@FindBy(xpath = FSQABrowserTaskPageLocators.DUE_BY)
	public WebElement DueBy;
	@FindBy(xpath = FSQABrowserTaskPageLocators.SAVE_BTN)
	public WebElement SaveBtn;

	/**
	 * This method is used to click on View Task History
	 * @author choubey_a
	 * @param none
	 * @return boolean This returns true after clicking on View Task History
	 */
	public boolean clickViewTaskHistoryHistory(String taskName) {
		try {
			WebElement selectTask = controlActions.perform_GetElementByXPath(FSQABrowserPageLocators.TASK_ROW,
					"TASKNAME", taskName);
			controlActions.action.moveToElement(selectTask).build().perform();
			controlActions.clickOnElement(CalloutMenu);
			controlActions.clickOnElement(TaskHistoryMnu);
			Sync();
			logInfo("Clicked on View Task History");
			return true;
		}
		catch(Exception e) {
			logError("Failed to click on View Task History - "
					+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to close the task history popup
	 * @author choubey_a
	 * @param none
	 * @return boolean This returns true after closing task history popup
	 * 
	 */

	public boolean closePopup() {
		try {
			Sync();
			controlActions.clickOnElement(ClosePopup);
			logInfo("Clicked on Close Task History Popup");
			Sync();
			return true;
		}
		catch(Exception e) {
			logError("Failed to click on Close Task Hostory Popup - "
					+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to get the task history details
	 * @author choubey_a
	 * @param List of details and a boolean dueBy
	 * @return boolean This returns true after getting all the details on the task history popup
	 * 
	 */


	public String getTaskHistoryDetails(List <WebElement> details, Boolean dueBy) {
		String getActualDetails = "";
		int count = 2;

		try {	
			if(dueBy) {
				count = 1;
			}
			for(int i=0; i < count ; i++) {				
				String temp = details.get(i).getText().toString().trim();
				getActualDetails = getActualDetails + " " +temp;
			}
			return getActualDetails;			
		}catch(Exception e) {
			logError("Failed to get Task History Details - "
					+ e.getMessage());
			return null;

		}	
	}

	/**
	 * This method is used to verify task history details
	 * @author choubey_a
	 * @param actions and data which is object of class History_Data
	 * @return boolean This returns true after verifying the task history details
	 * 
	 */

	public boolean verifyTaskHistoryDetails(String actions,HISTORY_DATA data) {
		try {
			String HeaderDetails = null;
			String DueByDetails = null;
			String assignedDetails = " Assigned to "+data.Workgroup+" by "+data.Username;

			List <WebElement> header = controlActions.perform_GetListOfElementsByXPath(FSQABrowserTaskPageLocators.HISTORY_ACTIONS_HEADING, 
					"HEADER", actions);

			List <WebElement> dueby = controlActions.perform_GetListOfElementsByXPath(FSQABrowserTaskPageLocators.HISTORY_DUEBY, 
					"HEADER", actions);

			List <WebElement> assignedTo = controlActions.perform_GetListOfElementsByXPath(FSQABrowserTaskPageLocators.HISTORY_ACTIONS_HEADING, 
					"HEADER", HISTORY_ACTIONS.ASSIGNED);

			if(!(assignedDetails.equals(getTaskHistoryDetails(assignedTo,false)))) {
				logError("Assigned To Details are not appropriate");
			}


			switch(actions) {

			case HISTORY_ACTIONS.DUEBY:
				HeaderDetails = " Due By changed by "+data.Username;				
				DueByDetails = " Due By: "+data.DueBy;				
				break;

			case HISTORY_ACTIONS.REASSIGNED:
				HeaderDetails = " Reassigned to "+data.Workgroup+" by " +data.Username;				
				DueByDetails = " Due By: "+data.DueBy;				
				break;

			default:
				logError("Wrong action selected");
				return false;
			}

			if(!HeaderDetails.equals(getTaskHistoryDetails(header,false))) {
				logError("Task History Heading Details are not appropriate");
				return false;
			}

			if(!DueByDetails.equals(getTaskHistoryDetails(dueby,true))) {
				logError("Task History DueBy Details are not appropriate");
			}
			logInfo("Verified Task History Details");
			return true;
		}catch(Exception e) {
			logError("Failed to verify task history details - "
					+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is a wrapper for verifying the details on the Task History popup
	 * @author choubey_a
	 * @param taskname, actions and data object of class History_Data
	 * @return boolean This returns true after veryfing the task details on task history pop up
	 * 
	 */

	public boolean verifyTaskHistory(String taskName,String actions,HISTORY_DATA data) {
		try {
			if(!clickViewTaskHistoryHistory(taskName)) {
				logError("Could not click on View History callout menu");
			}

			if(!verifyTaskHistoryDetails(actions,data)) {
				logError("Could not verify Task History details");
			}

			if(!closePopup()) {
				logError("Could not close Task History popup");
			}
			logInfo("Verified Task History for "+actions);
			return true;

		}catch(Exception e) {
			logError("Failed to verify task history - "
					+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to view task on the FSQA Browser>Task grid
	 * @author choubey_a
	 * @param none
	 * @return boolean This returns true if task is not available
	 * 
	 */

	public boolean verifytaskonGrid(boolean view,String taskname) {
		try {
			WebElement task = controlActions.perform_GetElementByXPath(FSQABrowserPageLocators.TASK_ROW,
					"TASKNAME", taskname);
			if(view) {
				if((controlActions.isElementDisplayed(task))) {
					logInfo("Verified task is visible");
					return true;
				}
			}
			else {
				if(!(controlActions.isElementDisplayed(task))) {
					logInfo("Verified task is not visible");
					return true;
				}
			}
			logError("Task having no resource is visible in Task tab");
			return false;	
		}catch(Exception e) {
			logError("Failed to verify task - "
					+ e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to verify the started column of the task
	 * @author choubey_a
	 * @param taskname
	 * @return boolean This returns true if task is started
	 * 
	 */

	public boolean verifytaskStartedColumn(String taskname) {
		try {
			WebElement task = controlActions.perform_GetElementByXPath(FSQABrowserTaskPageLocators.TASK_STARTED,
					"TASKNAME", taskname);
			if(!(controlActions.isElementDisplayed(task))) {
				logError("Task has not started");
				return false;
			}
			logInfo("Task has started");
			return true;	
		}catch(Exception e) {
			logError("Failed to verify task - "
					+ e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to verify if all 4 Caret Dropdown submenus are present
	 * like View Task History, Change Due Date, Reassign Task, Review Task
	 * @author pednekar_pr
	 * @param taskname	The name of the task
	 * @return boolean This returns true if all 4 caret dropdown sub-menus are present
	 * 
	 */

	public boolean verifyCaretDropDownOptions(String taskname)
	{
		try {

			WebElement Caret = controlActions.perform_GetElementByXPath(FSQABrowserTaskPageLocators.CARET,
					"TASKNAME", taskname);
			if(!(controlActions.isElementDisplayed(Caret))) {
				logError("Task has not started");
				return false;
			}		
			controlActions.click(Caret);
			// check viewtaskhistory option present in caret dropdown
			String	viewTaskHist = controlActions.perform_GetDynamicXPath(FSQABrowserTaskPageLocators.CARET_DRPDWN, "CARET_ITEM", 
					CARET_ACTIONS.VIEWTASKHISTORY);
			boolean viewTaskHistoryDisplay= controlActions.isElementDisplayed(viewTaskHist);
			if(!viewTaskHistoryDisplay)
			{
				logError(CARET_ACTIONS.VIEWTASKHISTORY +" is missing");
				return false;
			}

			// check changeduedate option present in caret dropdown
			String	changeDueDate = controlActions.perform_GetDynamicXPath(FSQABrowserTaskPageLocators.CARET_DRPDWN, "CARET_ITEM", 
					CARET_ACTIONS.CHANGEDUEDATE);
			boolean changeDueDateDisplay= controlActions.isElementDisplayed(changeDueDate);
			if(!changeDueDateDisplay)
			{
				logError(CARET_ACTIONS.CHANGEDUEDATE+" is missing");
				return false;
			}

			// check reassign task option present in caret dropdown
			String	reassignTask = controlActions.perform_GetDynamicXPath(FSQABrowserTaskPageLocators.CARET_DRPDWN, "CARET_ITEM", 
					CARET_ACTIONS.REASSIGNTASK);
			boolean reassignTaskDisplay= controlActions.isElementDisplayed(reassignTask);
			if(!reassignTaskDisplay)
			{
				logError(CARET_ACTIONS.REASSIGNTASK+ " is missing");
				return false;
			}

			// check recall task option present in caret dropdown
			String	recallTask = controlActions.perform_GetDynamicXPath(FSQABrowserTaskPageLocators.CARET_DRPDWN, "CARET_ITEM", 
					CARET_ACTIONS.RECALLTASK);
			boolean recallTaskDisplay= controlActions.isElementDisplayed(recallTask);
			if(!recallTaskDisplay)
			{
				logError(CARET_ACTIONS.RECALLTASK+" is missing");
				return false;
			}

			else
			{
				logInfo(CARET_ACTIONS.VIEWTASKHISTORY+" "+ CARET_ACTIONS.CHANGEDUEDATE+" "+CARET_ACTIONS.REASSIGNTASK+" "
						+CARET_ACTIONS.RECALLTASK+ "are present in dropdown");
				return true;
			}

		}
		catch(Exception e) {
			logError("Failed to verify caret drop down options for tasks - "
					+ e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to verify if task with given taskname is visible for given date 
	 * @author pednekar_pr
	 * @param taskname	The name of the task
	 * @param date		The date of next day from scheduled date
	 * @return boolean This returns false when task with given taskname isn't visible for given date
	 * 
	 */
	public boolean viewTaskOnGrid(String taskname, String date) {
		try {
			String taskDate = controlActions.perform_GetDynamicXPath(FSQABrowserPageLocators.TASK_ROW_DATE,
					"TASKNAME", taskname);
			WebElement taskDueDate = controlActions.perform_GetElementByXPath(taskDate,
					"UPDATEDATE", date);
			if(controlActions.isElementDisplayed(taskDueDate))
			{
				logInfo("Scheduled due task "+ taskname +" is visible for date"+date);
				return true;
			}

			logInfo("Scheduled due task "+ taskname +" is not visible for date"+date);
			return false;
		}
		catch(Exception e) {
			logError("Failed to verify visibility of task - "
					+ e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to verify if Change Due Date is displayed or not in Caret option
	 * @author pednekar_pr
	 * @param taskname	The name of the task
	 * @return boolean This returns true if Change Due Date is displayed in Caret option
	 * 
	 */

	public boolean verifyChangeDueDateIsDisplayed(String taskname)
	{
		try {

			WebElement Caret = controlActions.perform_GetElementByXPath(FSQABrowserTaskPageLocators.CARET,
					"TASKNAME", taskname);
			if(!(controlActions.isElementDisplayed(Caret))) {
				logError("Task has not started");
				return false;
			}		
			controlActions.click(Caret);
			// check changeduedate option present in caret dropdown
			String	changeDueDate = controlActions.perform_GetDynamicXPath(FSQABrowserTaskPageLocators.CARET_DRPDWN, "CARET_ITEM", 
					CARET_ACTIONS.CHANGEDUEDATE);
			boolean changeDueDateDisplay= controlActions.isElementDisplayed(changeDueDate);
			if(!changeDueDateDisplay)
			{
				logError(CARET_ACTIONS.CHANGEDUEDATE+" is missing");
				return false;
			}

			else
			{
				logInfo(CARET_ACTIONS.CHANGEDUEDATE+"is displayed in dropdown");
				return true;
			}

		}
		catch(Exception e) {
			logError("Failed to verify caret drop down option 'Change Due Date' for tasks - "
					+ e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to verify if Recall Task is displayed or not in Caret option
	 * @author pednekar_pr
	 * @param taskname	The name of the task
	 * @return boolean This returns true if Recall Task is displayed in Caret option
	 * 
	 */

	public boolean verifyRecallTaskIsDisplayed(String taskname)
	{
		try {

			WebElement Caret = controlActions.perform_GetElementByXPath(FSQABrowserTaskPageLocators.CARET,
					"TASKNAME", taskname);
			if(!(controlActions.isElementDisplayed(Caret))) {
				logError("Task has not started");
				return false;
			}		
			// check Recall Task option present in caret dropdown
			controlActions.click(Caret);
			String	recallTask = controlActions.perform_GetDynamicXPath(FSQABrowserTaskPageLocators.CARET_DRPDWN, "CARET_ITEM", 
					CARET_ACTIONS.RECALLTASK);
			boolean recallTaskDisplay= controlActions.isElementDisplayed(recallTask);
			if(!recallTaskDisplay)
			{
				logError(CARET_ACTIONS.RECALLTASK+" is missing");
				return false;
			}
			else
			{
				logInfo(CARET_ACTIONS.RECALLTASK+"is displayed in dropdown");
				return true;
			}

		}
		catch(Exception e) {
			logError("Failed to verify caret drop down option 'Recall Task' for tasks - "
					+ e.getMessage());
			return false;
		}
	}
	/**
	 * This method is used to verify if View Task History is displayed or not in Caret option
	 * @author pednekar_pr
	 * @param taskname	The name of the task
	 * @return boolean This returns true if View Task History is displayed in Caret option
	 * 
	 */

	public boolean taskHistoryOptionIsVisible(String taskname)
	{
		try {

			WebElement Caret = controlActions.perform_GetElementByXPath(FSQABrowserTaskPageLocators.CARET,
					"TASKNAME", taskname);
			if(!(controlActions.isElementDisplayed(Caret))) {
				logError("Task has not started");
				return false;
			}		
			// check Recall Task option present in caret dropdown
			controlActions.click(Caret);

			// check viewtaskhistory option present in caret dropdown
			String	viewTaskHist = controlActions.perform_GetDynamicXPath(FSQABrowserTaskPageLocators.CARET_DRPDWN, "CARET_ITEM", 
					CARET_ACTIONS.VIEWTASKHISTORY);
			boolean viewTaskHistoryDisplay= controlActions.isElementDisplayed(viewTaskHist);
			if(!viewTaskHistoryDisplay)
			{
				logError(CARET_ACTIONS.VIEWTASKHISTORY +" is missing");
				return false;
			}

			else
			{
				logInfo(CARET_ACTIONS.VIEWTASKHISTORY+"is displayed in dropdown");
				return true;
			}

		}
		catch(Exception e) {
			logError("Failed to verify caret drop down option 'View Task History' for tasks - "
					+ e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to perform Change Due Date on given task
	 * @author pednekar_pr
	 * @param taskname	The name of the task
	 * @return boolean This returns true if Change Due Date is displayed in Caret option
	 * 
	 */

	public boolean changeDueDate(String taskname, String dueDate)
	{
		try {

			WebElement Caret = controlActions.perform_GetElementByXPath(FSQABrowserTaskPageLocators.CARET,
					"TASKNAME", taskname);

			if(!(controlActions.isElementDisplayed(Caret))) {
				logError("Task has not started");
				return false;
			}		
			controlActions.click(Caret);
			dueDate="11/01/2021 00:00";
			// check changeduedate option present in caret dropdown
			WebElement	changeDueDate = controlActions.perform_GetElementByXPath(FSQABrowserTaskPageLocators.CARET_DRPDWN, "CARET_ITEM", 
					CARET_ACTIONS.CHANGEDUEDATE);
			boolean changeDueDateDisplay= controlActions.isElementDisplayed(changeDueDate);
			if(!changeDueDateDisplay)
			{
				logError(CARET_ACTIONS.CHANGEDUEDATE+" is missing");
				return false;
			}

			controlActions.click(changeDueDate);
			controlActions.sendKeys(DueBy, dueDate);
			controlActions.click(SaveBtn);

			logInfo(CARET_ACTIONS.CHANGEDUEDATE+"action is performed");
			return true;


		}
		catch(Exception e) {
			logError("Failed to verify caret drop down option 'Change Due Date' for tasks - "
					+ e.getMessage());
			return false;
		}
	}


	public static class HISTORY_ACTIONS {
		public static final String ASSIGNED = "Assigned";
		public static final String REASSIGNED = "Reassigned";
		public static final String DUEBY = "Due By";
	}

	public static class HISTORY_DATA {
		public  String Workgroup;
		public  String Username;
		public  String AssignedDueBy;
		public  String DueBy;
	}

	public static class CARET_ACTIONS{
		public static final String VIEWTASKHISTORY="View Task History";
		public static final String CHANGEDUEDATE="Change Due Date";
		public static final String REASSIGNTASK="Reassign Task";
		public static final String RECALLTASK="Recall Task";
	}

}