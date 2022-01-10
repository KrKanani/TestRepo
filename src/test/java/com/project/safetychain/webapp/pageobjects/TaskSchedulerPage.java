package com.project.safetychain.webapp.pageobjects;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.project.utilities.CommonMethods;
import com.project.utilities.ControlActions;
import com.project.utilities.DateTime;
import com.project.utilities.DateTime.ComparisonValue;

public class TaskSchedulerPage extends CommonPage {
	WebDriver driver;
	WebDriverWait wait;
	ControlActions controlActions;
	DateTime dateTime;
	FSQABrowserTaskPage fsqaTasksPage;
	CommonMethods commonmethods;
	CommonPage cp;
	ValidationsPage vp;
	public static HashMap<String, String> TaskDetails = new HashMap<String, String>();

	public TaskSchedulerPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 40);
		controlActions = new ControlActions(driver);
		dateTime = new DateTime(driver);
	}

	@FindBy(id = TaskSchedulerPageLocators.RECALL_BTN)
	public WebElement RecallBtn;

	@FindBy(xpath = TaskSchedulerPageLocators.ALL_BTN)
	public WebElement AllBtn;

	@FindBy(xpath = TaskSchedulerPageLocators.BYDATE_BTN)
	public WebElement ByDateBtn;

	@FindBy(xpath = TaskSchedulerPageLocators.PREVIOUSDAY_BTN)
	public WebElement PreviousDayBtn;

	@FindBy(xpath = TaskSchedulerPageLocators.NEXTDAY_BTN)
	public WebElement NextDayBtn;

	@FindBy(xpath = TaskSchedulerPageLocators.DATEPICKER_BTN)
	public WebElement DatePickerBtn;

	@FindBy(xpath = TaskSchedulerPageLocators.SCHEDULETASK_BTN)
	public WebElement ScheduleTaskBtn;

	@FindBy(xpath = TaskSchedulerPageLocators.NEXT_BTN)
	public WebElement NextBtn;

	@FindBy(xpath = TaskSchedulerPageLocators.SAVE_BTN)
	public WebElement SaveBtn;

	@FindBy(xpath = TaskSchedulerPageLocators.BACK_BTN)
	public WebElement BackBtn;

	@FindBy(xpath = TaskSchedulerPageLocators.CANCEL_BTN)
	public WebElement CancelBtn;

	@FindBy(xpath = TaskSchedulerPageLocators.VIEWBYTASK_LINK)
	public WebElement ViewByTaskLink;

	@FindBy(xpath = TaskSchedulerPageLocators.VIEWBYSCHEDULE_LINK)
	public WebElement ViewByScheduleLink;

	@FindBy(xpath = TaskSchedulerPageLocators.OPTION_DDL)
	public WebElement OptionDd;

	@FindBy(xpath = TaskSchedulerPageLocators.lOCATION_DDL)
	public WebElement LocationDd;

	@FindBy(xpath = TaskSchedulerPageLocators.SELECTFORM_DDL)
	public WebElement SelectformDd;

	@FindBy(xpath = TaskSchedulerPageLocators.SELECTDOCUMENT_DDL)
	public WebElement SelectFromDdl;

	@FindBy(xpath = TaskSchedulerPageLocators.SELECTRESOURCE_DDL)
	public WebElement SelectResourceDdl;

	@FindBy(xpath = TaskSchedulerPageLocators.SELECTWORKGROUP_DDL)
	public WebElement SelectWorkGroupDdl;

	@FindBy(xpath = TaskSchedulerPageLocators.TASKOCCURS_DDL)
	public WebElement TaskOccurDdl;

	@FindBy(xpath = TaskSchedulerPageLocators.TIMEZONE_DDL)
	public WebElement TimeZoneDd;

	@FindBy(xpath = TaskSchedulerPageLocators.PROCESSFORM_RB)
	public WebElement ProcessFormRb;

	@FindBy(xpath = TaskSchedulerPageLocators.PROCESDOCUMENT_RB)
	public WebElement ProcessDocumentRb;

	@FindBy(xpath = TaskSchedulerPageLocators.TASKNAME_TXT)
	public WebElement TaskNameTxt;

	@FindBy(xpath = TaskSchedulerPageLocators.TASKDESCRIPTION_TXT)
	public WebElement TaskDescriptionTxt;

	@FindBy(xpath = TaskSchedulerPageLocators.ASSIGNHOUR_TXT)
	public WebElement AssignHoutTxt;

	@FindBy(xpath = TaskSchedulerPageLocators.ASSIGNMIN_TXT)
	public WebElement AssignMinTxt;

	@FindBy(xpath = TaskSchedulerPageLocators.LOCAIONSEARCH_TXT)
	public WebElement LocationSearchTxt;

	@FindBy(xpath = TaskSchedulerPageLocators.FORMSEARCH_TXT)
	public WebElement FormSearcTtxt;

	@FindBy(xpath = TaskSchedulerPageLocators.RESOURCESEARCH_TXT)
	public WebElement ResourceSearchTxt;

	@FindBy(xpath = TaskSchedulerPageLocators.WORKGROUPSEARCH_TXT)
	public WebElement WorkGroupSearchTxt;

	@FindBy(xpath = TaskSchedulerPageLocators.STEP3DUEDATETIME_TXT)
	public WebElement Step3DuedatetimeTxt;

	@FindBy(xpath = TaskSchedulerPageLocators.TIMEZONESEARCH_TXT)
	public WebElement TimeZoneSearchTxt;

	@FindBy(xpath = TaskSchedulerPageLocators.STEP3CALENDAR_ICN)
	public WebElement Step3Calendaricn;

	@FindBy(xpath = TaskSchedulerPageLocators.STEP3TIME_ICN)
	public WebElement Step3Dateicn;

	@FindBy(xpath = TaskSchedulerPageLocators.CALENDARMAINPAGE)
	public WebElement CalenderMainpage;

	@FindBy(xpath = TaskSchedulerPageLocators.DATEPICCERMAINPAGE)
	public WebElement DatePiccerMainpage;

	@FindBy(xpath = TaskSchedulerPageLocators.DATE)
	public WebElement TaskDate;

	@FindBy(xpath = TaskSchedulerPageLocators.STEP3FIRSTDUEDATE_TXT)
	public WebElement Step3FirstDueDatetxt;

	@FindBy(xpath = TaskSchedulerPageLocators.STEP3_YEARLY_ENDDATE_INPUT)
	public WebElement Step3YearlyEndDateInput;

	@FindBy(xpath = TaskSchedulerPageLocators.DUE_TIME_INPUT)
	public WebElement DueTimeInput;	

	@FindBy(xpath = TaskSchedulerPageLocators.MAINPAGECALENDEA_ICN)
	public WebElement mainpagecalendaricn;

	@FindBy(xpath = TaskSchedulerPageLocators.STEP3DAILYDUETIME)
	public WebElement Step3DailyDueTime;

	@FindBy(xpath = TaskSchedulerPageLocators.TASK_DUE_TIME)
	public WebElement TaskDueTime;

	@FindBy(xpath = TaskSchedulerPageLocators.HOMECALENDARMONTH)
	public WebElement calendarmonth;

	@FindBy(xpath = TaskSchedulerPageLocators.CALENDARNEXT_BTN)
	public WebElement calendarnextbutton;

	@FindBy(xpath = TaskSchedulerPageLocators.STEP3EVERY_TXT)
	public WebElement everytxt;

	@FindBy(xpath = TaskSchedulerPageLocators.STEP3FIRSTDUEDATE_ICN)
	public WebElement step3FirstDueDateicn;

	@FindBy(xpath = TaskSchedulerPageLocators.STEP3ENDDATE_ICN)
	public WebElement step3EndDateicn;

	@FindBy(xpath = TaskSchedulerPageLocators.ENDDATE_FLAG)
	public WebElement enddateflag;

	@FindBy(xpath = TaskSchedulerPageLocators.INCREASEEVERY_BTN)
	public WebElement increaseeverybtn;

	@FindBy(xpath = TaskSchedulerPageLocators.ENDDATE_TXT)
	public WebElement enddatetxt;

	@FindBy(xpath = TaskSchedulerPageLocators.DUEDATE_ICN)
	public WebElement step3DueDateIcon;

	@FindBy(xpath = TaskSchedulerPageLocators.DAY_CHCKBX)
	public WebElement DayChkbx;

	@FindBy(xpath = TaskSchedulerPageLocators.WEEKLY_TAB)
	public WebElement WeeklyTab;

	@FindBy(xpath = TaskSchedulerPageLocators.DAILY_TAB)
	public WebElement DailyTab;

	@FindBy(xpath = TaskSchedulerPageLocators.DATELBL)
	public WebElement DateLbl;

	@FindBy(xpath = TaskSchedulerPageLocators.SCHEDULEGRIDHEADERTR)
	public List<WebElement> ScheduleGrdHeader;

	@FindBy(xpath = TaskSchedulerPageLocators.TASKSGRIDHEADERTR)
	public List<WebElement> TasksGrdHeader; 

	@FindBy(xpath = TaskSchedulerPageLocators.TASKSCHEDULE_DTR)
	public WebElement TaskScheduleDtr;

	@FindBy(xpath = TaskSchedulerPageLocators.OCCURRED_VALUE)
	public WebElement OccrurredValue;

	@FindBy(xpath = TaskSchedulerPageLocators.VIEW_BY_TASK_BTN)
	public WebElement ViewByTaskBtn;

	@FindBy(xpath = TaskSchedulerPageLocators.TASK_SELECT)
	public WebElement TaskSelect;

	@FindBy(xpath = TaskSchedulerPageLocators.RECALLED_STATUS)
	public WebElement RecalledStatus;

	@FindBy(xpath = TaskSchedulerPageLocators.DUE_DATE)
	public WebElement DueDate;

	@FindBy(xpath = TaskSchedulerPageLocators.DUE_TIME)
	public WebElement DueTime;

	@FindBy(xpath = TaskSchedulerPageLocators.GRID_LOCATION_DDL)
	public WebElement GridLocationDDL;

	@FindBy(xpath = TaskSchedulerPageLocators.LOCATION_INPUT)
	public WebElement LocationInput;

	@FindBy(xpath = TaskSchedulerPageLocators.INTERVAL_STARTDATE_INPUT)
	public WebElement IntervalStartDateInput;

	@FindBy(xpath = TaskSchedulerPageLocators.INTERVAL_ENDDATE_INPUT)
	public WebElement IntervalEndDateInput;

	@FindBy(xpath = TaskSchedulerPageLocators.INTERVAL_STARTTIME_INPUT)
	public WebElement IntervalStartTimeInput;

	@FindBy(xpath = TaskSchedulerPageLocators.INTERVAL_ENDDADTE_INPUT)
	public WebElement IntervalEndTimeInput;

	@FindBy(xpath = TaskSchedulerPageLocators.INTERVAL_DDL)
	public WebElement IntervalDDL;

	@FindBy(xpath = TaskSchedulerPageLocators.INTERVAL_INPUT)
	public WebElement IntervalInput;

	@FindBy(xpath = TaskSchedulerPageLocators.RANDOMIZE_INPUT)
	public WebElement RandomizeInput;

	@FindBy(xpath = TaskSchedulerPageLocators.UPDATE_BTN)
	public WebElement UpdateBtn;

	@FindBy(xpath = TaskSchedulerPageLocators.CALENDER_NEXT_BTN)
	public WebElement CalenderNextBtn;

	@FindBy(xpath = TaskSchedulerPageLocators.RECALL_POPUP_OK_BTN)
	public WebElement RecallPopupOkBtn;


	@FindBy(xpath = TaskSchedulerPageLocators.DELETE_BTN)
	public WebElement DeleteBtn;

	@FindBy(xpath = TaskSchedulerPageLocators.EDIT_CANCEL_BTN)
	public WebElement EditCancelBtn;

	@FindBy(xpath = TaskSchedulerPageLocators.EVERY_DAY_UP_ARROW)
	public WebElement EveryDayUpArrow;

	@FindBy(xpath = TaskSchedulerPageLocators.SELECT_DOCUMENT_DRPDWN)
	public WebElement SelectDocumentDrpdwn;

	@FindBy(xpath = TaskSchedulerPageLocators.SELECT_DOCUMENT_WORKGROUP_SEARCHBOX)
	public WebElement SelectDocWorkgroupSearchBox;	

	@FindBy(xpath = TaskSchedulerPageLocators.CALENDER_PREV_BTN)
	public WebElement CalenderPrevBtn;

	@FindBy(xpath = TaskSchedulerPageLocators.SKIP_BTN)
	public WebElement SkipBtn;

	@FindBy(xpath = TaskSchedulerPageLocators.COMMENT_TXT_BOX)
	public WebElement CommentTxtBox;

	@FindBy(xpath = TaskSchedulerPageLocators.SKIP_POPUP_SKIP_BTN)
	public WebElement SkipPopupSkipBtn;

	@FindBy(xpath = TaskSchedulerPageLocators.CLOSE_BTN)
	public WebElement CloseBtn;
	
	@FindBy(xpath = TaskSchedulerPageLocators.WORK_GROUP_EDIT_SEARCH_TEXT)
	public WebElement WorkGroupEditSearchTxt;
	
	/**
	 * This method is used to click on Schedule task button.
	 * @author dahale_p
	 * @param none
	 * @return boolean This returns true if SCHEDULE TASK button is clicked or not
	 */
	public boolean clickAddNewTaskBtn() {
		try {
			controlActions.WaitforelementToBeClickable(ScheduleTaskBtn);
			controlActions.clickOnElement(ScheduleTaskBtn);
			Sync();
			logInfo("Clicked on Schedule Task button");

			return true;
		} catch (Exception e) {
			logError("Failed to click on Schedule Task button " + e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to fill Schedule Task Step 1 Details.
	 * @author dahale_p
	 * @param 1. Location , 2. process Form / Document Form , 3.form or document
	 *           value
	 * @return boolean This returns true if user is able to fill data and click on
	 *         next button.
	 */
	public boolean fillstepOneData(TaskScheduleDetails TSD) {
		try {
			clickAddNewTaskBtn();
			controlActions.perform_ClickWithJavaScriptExecutor(LocationDd);
			LocationSearchTxt.click();
			controlActions.sendKeys(LocationSearchTxt, TSD.locationvalue);
			Sync();
			controlActions.actionEnter();
			if (TSD.formORdocument.equalsIgnoreCase("Form")) {
				controlActions.clickOnElement(ProcessFormRb);
				Sync();

				String selector = "div[title='"+ TSD.formORdocumentvalue +"']";
				controlActions.JSClick(driver, selector);
			} else {
				controlActions.clickOnElement(ProcessDocumentRb);
				/**
				 * ---------------------------------------------- Need to add code for document
				 * selection-------------------------------------------------------------------------------------
				 */
				/*
				 * controlActions.WaitforelementToBeClickable(select);
				 * controlActions.clickOnElement(FormSearcTtxt);
				 * controlActions.sendKeys(FormSearcTtxt, formORdocumentvalue);
				 */
			}
			controlActions.clickOnElement(NextBtn);
			Sync();
			logInfo("Step one data filed successfully");
			return true;
		} catch (Exception e) {
			logError("Failed to fill data on step one" + e.getMessage());
			return false;
		}

	}

	/**
	 * This method is used to fill Schedule Task Step two Details.
	 * @author dahale_p
	 * @param 1. Task Name , 2.Resource , 3.Work Group, 4.Task Description
	 * @return boolean This returns true if user is able to fill data and click on
	 *         next button.
	 */
	public boolean fillstepTwoData(TaskScheduleDetails TSD) {
		try {
			controlActions.sendKeys(TaskNameTxt, TSD.taskname);
			threadsleep(2000);
			controlActions.perform_ClickWithJavaScriptExecutor(SelectResourceDdl);
			threadsleep(2000);
			controlActions.sendKeys(ResourceSearchTxt, TSD.resource);
			threadsleep(2000);
			controlActions.actionEnter();
			controlActions.perform_ClickWithJavaScriptExecutor(SelectWorkGroupDdl);
			threadsleep(2000);
			controlActions.sendKeys(WorkGroupSearchTxt, TSD.workGroup);
			threadsleep(2000);
			controlActions.actionEnter();
			controlActions.sendKeys(TaskDescriptionTxt, TSD.taskDescription);
			controlActions.click(NextBtn);
			logInfo("Step two data filed successfully");
			return true;
		} catch (Exception e) {
			logError("Failed to fill data on step one" + e.getMessage());
			return false;
		}

	}

	/**
	 * This method is used to fill Schedule Task Step Three Details.
	 * @author Choubey_A
	 * @param TaskScheduleDetails
	 * @return boolean This returns true if schedule is created.
	 */
	public boolean fillstepThreeData(TaskScheduleDetails TSD) {
		WebElement OccurredValue = null;
		try {
			controlActions.click(TaskOccurDdl);
			OccurredValue = controlActions.perform_GetElementByXPath(TaskSchedulerPageLocators.OCCURRED_VALUE,
					"occurrence", TSD.taskoccure);
			controlActions.perform_ClickWithJavaScriptExecutor(OccurredValue);

			switch (TSD.taskoccure) {
			case "One Time Only":
				Sync();
				dateTime.setDateTime(Step3DuedatetimeTxt, "Day", 0 , 0, 0, false, false);
				controlActions.clickOnElement(SaveBtn);
				Sync();

				logInfo("One Time Schedule Created");
				break;

			case "Daily":
				dateTime.setDate(Step3FirstDueDatetxt, "Day", 0);
				dateTime.setTime(TaskDueTime, 0, 3, false, false);
				if (TSD.haveEndDate == true) {
					controlActions.click(enddateflag);
					dateTime.setDate(enddatetxt, "Day", 10);
				}
				controlActions.clickOnElement(SaveBtn);
				Sync();
				logInfo("Daily Schedule Created");
				break;

			case "Weekly":
				String dayOfWeek = get_Current_Day_Of_Week();
				logInfo("Today's Day" + dayOfWeek);
				WebElement DayOfWeek = controlActions.perform_GetElementByXPath(TaskSchedulerPageLocators.DAY_CHCKBX, "DAY",
						dayOfWeek);
				controlActions.perform_ClickWithJavaScriptExecutor(DayOfWeek);
				dateTime.setDate(Step3FirstDueDatetxt, "Day", 0);
				dateTime.setTime(TaskDueTime, 0, 1, false, false);
				if (TSD.haveEndDate == true) {
					controlActions.click(enddateflag);
					dateTime.setDate(enddatetxt, "Day", 30);

				}
				controlActions.clickOnElement(SaveBtn);
				Sync();
				logInfo("Weekly Schedule Created");
				break;
			case "Monthly":
				dateTime.setDate(Step3FirstDueDatetxt, "Day", 0);
				dateTime.setTime(DueTimeInput, 0, 1, false, false);
				if (TSD.haveEndDate == true) {
					controlActions.click(enddateflag);
					dateTime.setDate(enddatetxt, "Day", 90);
				}
				controlActions.clickOnElement(SaveBtn);
				Sync();
				logInfo("Monthly Schedule Created");
				break;
			case "Yearly":
				dateTime.setDate(Step3FirstDueDatetxt, "Day", 0);
				dateTime.setTime(DueTimeInput, 0, 1, false, false);
				if (TSD.haveEndDate == true) {
					controlActions.click(enddateflag);
					dateTime.setYear(Step3YearlyEndDateInput,2);
				}
				controlActions.clickOnElement(SaveBtn);
				Sync();
				logInfo("Yearly Schedule Created");
				break;
			case "Interval":
				dateTime.setDate(IntervalStartDateInput, "Day", 0);
				dateTime.setDate(IntervalEndDateInput, "Day", 1);
				String dayOfWeek1 = get_Current_Day_Of_Week();
				WebElement DayOfWeek1 = controlActions.perform_GetElementByXPath(TaskSchedulerPageLocators.DAY_CHCKBX, "DAY",
						dayOfWeek1);
				controlActions.perform_ClickWithJavaScriptExecutor(DayOfWeek1);
				dateTime.setTime(IntervalStartTimeInput, 0, 0, false, false);
				dateTime.setTime(IntervalEndTimeInput, 1, 30, false, false);
				controlActions.perform_ClickWithJavaScriptExecutor(IntervalDDL);
				IntervalInput.click();
				controlActions.sendKeys(IntervalInput, TSD.interval);
				threadsleep(2000);
				controlActions.actionEnter();	
				if(TSD.ramdomize == true) {
					controlActions.perform_ClickWithJavaScriptExecutor(RandomizeInput);
					logInfo("Randomize Start Time Selected");
				}
				controlActions.clickOnElement(SaveBtn);
				Sync();
				logInfo("Interval Schedule Created");
				break;
			}
			logInfo(TSD.taskoccure + "-" + "Schedule Created");
			return true;
		} catch (Exception e) {
			logError("Failed to create schedule of type  - " + TSD.taskoccure + e.getMessage());
			return false;
		}

	}

	public boolean createNewTask(TaskScheduleDetails TSD) {
		try {
			if (!(fillstepOneData(TSD))) {
				return false;
			}

			if (!(fillstepTwoData(TSD))) {
				return false;
			}

			if (!(fillstepThreeData(TSD))) {
				return false;
			}

			logInfo("Task Created Successfully");
			Sync();
			return true;
		} catch (Exception e) {
			logError("Error occured during Task Creation" + e.getMessage());
			return false;
		}

	}

	/**
	 * This method is used to Verify Created task on scheduled date.
	 * @author Shingare_S
	 * @param 1. String taskname, 2. String duedate
	 * @return boolean This returns true if user is able to find the new task
	 *         created on respective date.
	 * @throws ParseException
	 */
	public boolean verifyCreatedTaskFromSchedulerView(String taskname, String duedate) throws ParseException {

		//		String newTask = null;
		String Taskdate = null;
		boolean isVerified = true;
		HashMap<String, String> MatchedDetails = new HashMap<String, String>();

		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		DateFormat outputFormat = new SimpleDateFormat("MMM dd yyyy");

		Date date = sdf.parse(duedate);
		duedate = outputFormat.format(date);

		try {

			do {
				Taskdate = TaskDate.getText();
				System.out.println("TaskDate=" + Taskdate);
				System.out.println("Due Date" + duedate);
				if ((Taskdate.equals(duedate))) {

					Thread.sleep(2000);

					//					newTask = controlActions.perform_GetDynamicXPath(TaskSchedulerPageLocators.TASKSCHEDULE_DTR, "TASKNAME",
					//							taskname);

					List<WebElement> TaskRow = driver.findElements(By.xpath(controlActions
							.perform_GetDynamicXPath(TaskSchedulerPageLocators.TASKSCHEDULE_DTR, "TASKNAME", taskname)));
					Thread.sleep(2000);

					for (int i = 1; i < TaskRow.size(); i++) {
						WebElement Element = TaskRow.get(i);
						WebElement Header = ScheduleGrdHeader.get(i);
						MatchedDetails.put(Header.getText(), Element.getText());
					}

					logInfo("Created Map " + MatchedDetails);
					isVerified = Compare_Map_By_Key_Value(MatchedDetails);

					break;
				} else {
					Sync();
					controlActions.click(NextDayBtn);
				}
			} while (Taskdate.equals(duedate) == false);

			logInfo("Task " + taskname + " Found on " + duedate);
			return isVerified;
		} catch (Exception e) {
			logError("Newly Created Task is not displayed on due date" + e.getMessage());
			return false;
		}

	}

	/**
	 * This method is used to Verify Created task on scheduled date.
	 * @author dahale_p
	 * @param 1. String taskname, 2. String duedate
	 * @return boolean This returns true if user is able to find the new task
	 *         created on respective date.
	 */
	public boolean verifyCreatedTaskFromTaskView(String taskname, String duedate, String taskType)
			throws ParseException, InterruptedException {
		boolean isVerify = true;
		controlActions.clickOnElement(ViewByTaskLink);
		Thread.sleep(2000);
		switch (taskType) {
		case "Weekly":
			controlActions.clickOnElement(WeeklyTab);
			Thread.sleep(2000);
			logInfo("Clicked on Weekly tab from Task view");
			break;
		case "Daily":
			controlActions.clickOnElement(DailyTab);
			Thread.sleep(2000);
			logInfo("Clicked on Daily tab from Task view");
			break;
		default:
			break;
		}

		String Taskdate = null;
		HashMap<String, String> MatchedDetails = new HashMap<String, String>();

		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		DateFormat outputFormat = new SimpleDateFormat("MMM dd yyyy");

		Date date = sdf.parse(duedate);
		duedate = outputFormat.format(date);

		try {

			do {
				Taskdate = DateLbl.getText();
				String DateRange = getCurrentWeekRange();

				if ((Taskdate.equals(DateRange))) {

					Thread.sleep(2000);

					WebElement TaskRowMatched = controlActions.perform_GetElementByXPath(
							TaskSchedulerPageLocators.TASKSCHEDULEDONTASKTR, "TASKNAME", taskname);

					List<WebElement> TaskRow = driver.findElements(By.xpath(controlActions.perform_GetDynamicXPath(
							TaskSchedulerPageLocators.TASKSCHEDULEDONTASKTR, "TASKNAME", taskname)));

					controlActions.perform_scrollToElement_ByElement(TaskRowMatched);

					Thread.sleep(2000);

					for (int i = 1; i < TaskRow.size() - 1; i++) {
						WebElement Element = TaskRow.get(i + 1);
						String Details = Element.getText();
						WebElement Header = TasksGrdHeader.get(i);
						MatchedDetails.put(Header.getText(), Details);
					}

					logInfo("Created Map " + MatchedDetails);
					if (MatchedDetails.get("Task").contains(taskname)) {
						Assert.assertEquals(MatchedDetails.get("Status"), "Scheduled Due Today");
					} else {
						isVerify = false;
					}

					break;
				} else {
					Sync();
					controlActions.click(NextDayBtn);
				}
			} while (Taskdate.equals(duedate) == false);

			logInfo("Task " + taskname + " Found on " + duedate);
			return isVerify;
		} catch (Exception e) {
			logError("Newly Created Task is not displayed on due date" + e.getMessage());
			return false;
		}

	}

	public boolean select_Date_from_Calendar(String taskdate) throws ParseException {

		String pickdate = DateTime.formatDate(taskdate, "d-MMMM yyyy");
		String datespliter[] = pickdate.split("-");
		String day = datespliter[0];
		String month_year = datespliter[1];
		System.out.println("Month year =" + month_year);

		String calandermonth = null;
		System.out.println("calandermonth" + calandermonth);
		try {

			do {
				threadsleep(4000);
				calandermonth = calendarmonth.getText();
				System.out.println(calandermonth);
				if (calandermonth.equals(month_year)) {

					WebElement selectday = controlActions.perform_GetElementByXPath(TaskSchedulerPageLocators.CALENDAR_DATE,
							"DATE", day);
					controlActions.isElementDisplay(selectday);
					controlActions.click(selectday);
					break;
				} else {

					controlActions.isElementDisplay(calendarnextbutton);
					controlActions.click(calendarnextbutton);

				}

			} while (calandermonth.equals(month_year) == false);

			logInfo("Selected Date -- " + taskdate);
			return true;

		} catch (Exception e) {
			logError("Unable to select date" + e.getMessage());
			return false;
		}

	}

	public String get_Current_Day_Of_Week() {
		String Day_Week = null;
		try {
			Calendar calendar = Calendar.getInstance();
			int day = calendar.get(Calendar.DAY_OF_WEEK);

			switch (day) {
			case Calendar.SUNDAY:
				Day_Week = "Sun";
				break;
			case Calendar.MONDAY:
				Day_Week = "Mon";
				break;
			case Calendar.TUESDAY:
				Day_Week = "Tue";
				break;
			case Calendar.WEDNESDAY:
				Day_Week = "Wed";
				break;
			case Calendar.THURSDAY:
				Day_Week = "Thu";
				break;
			case Calendar.FRIDAY:
				Day_Week = "Fri";
				break;
			case Calendar.SATURDAY:
				Day_Week = "Sat";
				break;
			}
		} catch (Exception e) {
			logError("Unable to get day of week" + e.getMessage());
		}
		return Day_Week;
	}

	public Boolean Compare_Map_By_Key_Value(HashMap<String, String> ExpectedMap) {
		boolean isMatched = true;
		System.out.println("Actual Map: " + TaskDetails);
		System.out.println("Expeccted Map: " + ExpectedMap);

		for (Map.Entry<String, String> entry : TaskDetails.entrySet()) {
			String k = entry.getKey();
			String v = entry.getValue();
			// System.out.println("Key: " + k + ", Value: " + v);
			for (Map.Entry<String, String> entry1 : ExpectedMap.entrySet()) {
				String k1 = entry1.getKey();
				String v1 = entry1.getValue();
				// System.out.println("Key: " + k + ", Value: " + v);
				if (k.contentEquals(k1)) {
					if (v.contentEquals(v1)) {
						System.out.println("Matched Key: " + k + ", Value: " + v);
						// isMatched = true;
					} else {
						isMatched = false;
					}

				}

			}
		}
		return isMatched;

	}

	/**
	 * This method is used to convert 24hr Format time into 12 hr formatted time.
	 * @author dahale_p
	 * @param String time-24 hr time format
	 * @return returns 12hr time format
	 */
	public String convert24HrFormatInto12HrTime(String time) throws ParseException {
		String Time12HrFormat = null;

		try {
			final SimpleDateFormat sdf = new SimpleDateFormat("H:mm");
			final Date dateObj = sdf.parse(time);
			System.out.println(dateObj);
			System.out.println(new SimpleDateFormat("hh:mm").format(dateObj));
			Time12HrFormat = new SimpleDateFormat("hh:mm").format(dateObj);
			return Time12HrFormat;
		} catch (final ParseException e) {
			e.printStackTrace();
			return Time12HrFormat;
		}

	}

	/**
	 * This method is used to get current week range
	 * @author Shingare_S
	 * @param String time time format
	 * @return returns 12hr time format
	 * @throws ParseException
	 * 
	 */
	public String getCurrentWeekRange() throws ParseException {
		String CurrentWeekRange = null;

		// Get calendar set to current date and time
		Calendar c = Calendar.getInstance();

		// Set the calendar to monday of the current week
		c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);

		// System.out.println();
		// Print dates of the current week starting on Monday
		DateFormat df = new SimpleDateFormat("MMM dd");
		DateFormat df1 = new SimpleDateFormat("MMM dd yyyy");
		System.out.println(df.format(c.getTime()));
		String Start_Date = df.format(c.getTime());
		for (int i = 0; i < 6; i++) {
			c.add(Calendar.DATE, 1);
		}
		System.out.println(df.format(c.getTime()));
		System.out.println();
		CurrentWeekRange = Start_Date + " - " + df1.format(c.getTime());
		return CurrentWeekRange;

	}

	/**
	 * This method is used to recall task of a schedule
	 * @author Choubey_a
	 * @param String schedulename
	 * @return true when the task will be recalled
	 */

	public boolean recallTask(String schedulename) {
		try {
			if(controlActions.isElementDisplay(ViewByTaskBtn)) {
				controlActions.clickOnElement(ViewByTaskBtn);
			}
			//			Sync();
			WebElement schedule = controlActions.perform_GetElementByXPath(TaskSchedulerPageLocators.SCHEDULE_SELECT,
					"SCHEDULENAME", schedulename);
			//			controlActions.doubleClick(schedule);
			schedule.click();
			Sync();
			controlActions.WaitforelementToBeClickable(TaskSelect);
			TaskSelect.click();
			//			Thread.sleep(1000);
			controlActions.WaitforelementToBeClickable(RecallBtn);
			RecallBtn.click();
			//			Sync();
			controlActions.WaitforelementToBeClickable(RecallPopupOkBtn);
			RecallPopupOkBtn.click();
			Sync();
			if(!(controlActions.isElementDisplayed(RecalledStatus))) {
				logError("The status of the task is not recalled");
			}
			//			Sync();
			logInfo("The task is recalled");
			return true;
		}catch(Exception e) {
			logError("Failed to recall task - " +e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to click Task View
	 * @author Choubey_a
	 * @param none
	 * @return true when the Task View will be clicked
	 */

	public boolean taskView() {
		try {
			controlActions.clickOnElement(ViewByTaskLink);
			Sync();
			logInfo("Click Task View");
			return true;	
		}catch(Exception e) {
			logError("Clicked Task View" +e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to verify schedules except Interval
	 * @author Choubey_a
	 * @param taskname 
	 * @return true when schedule is verified
	 */

	public boolean verifyScheduleInTaskView(String taskname) {
		WebElement TaskView = null;
		try {
			if(!taskView()) {
				return false;
			}
			TaskView = controlActions.perform_GetElementByXPath(TaskSchedulerPageLocators.TASK_VIEW,"TASKNAME", taskname);
			if(controlActions.isElementDisplayed(TaskView)) {								
				logInfo("Schedule Verified - " + taskname);
				return true;
			}
			logInfo("Unable to verify Schedule - " + taskname);
			return false;
		}
		catch(Exception e) {
			logError("Failed to verify schedule" +  taskname + e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to verify only interval schedule
	 * @author Choubey_a
	 * @param taskname
	 * @return true when interval schedule is verified
	 */

	public boolean verifyIntervalSchedule(String taskname) {
		try {
			controlActions.clickOnElement(ViewByTaskLink);
			Sync();
			WebElement taskviewinterval = controlActions.perform_GetElementByXPath(TaskSchedulerPageLocators.SCHEDULE_VIEW,"SCHEDULE",taskname);
			if(controlActions.isElementDisplayed(taskviewinterval)) {				
				logError("Interval Schedule Verified");
			}
			Sync();
			return true;
		}catch(Exception e) {
			logError("Not able to verify schedule" + e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to verify schedule edit
	 * @author Choubey_a
	 * @param taskName Name of the task
	 * @return true when schedule is edited
	 */
	public boolean editSchedule(String taskName) {
		try {
			controlActions.clickOnElement(CalenderNextBtn);
			Sync();
			WebElement schedule = controlActions.perform_GetElementByXPath(TaskSchedulerPageLocators.SCHEDULE_VIEW,
					"SCHEDULE",taskName);	
			controlActions.click(schedule);
			Sync();
			enddatetxt.clear();
			dateTime.setDate(enddatetxt, "Day", 32);
			TaskScheduleDetails.taskendDate = enddatetxt.getText();			
			threadsleep(3000);
			controlActions.click(UpdateBtn);
			Sync();
			return true;
		}catch(Exception e) {
			return false;
		}
	}

	/**
	 * This method is used to verify schedule edit
	 * @author Choubey_a
	 * @param taskName Name of the task
	 * @return true when schedule edit is verified
	 */
	public boolean verifyEditedSchedule(String taskName) {
		try {
			String scheduleverify = controlActions.perform_GetDynamicXPath(TaskSchedulerPageLocators.VERIFY_EDITED_SCHEDULE,
					"SCHEDULE",taskName);	
			WebElement scheduledate = controlActions.perform_GetElementByXPath(scheduleverify,
					"DATE",TaskScheduleDetails.taskendDate);
			if(controlActions.isElementDisplay(scheduledate)) {
				logInfo("Edited Schedule Verified");
			}
			return true;
		}catch(Exception e) {
			logError("Edit Schedule verification failed");
			return false;
		}
	}

	/**
	 * This method is used to set a location on Task Scheduler grid
	 * @author hingorani_a
	 * @param location 
	 * @return boolean 
	 */
	public boolean selectLocationForGrid(String location)
	{
		WebElement LocationGrd = null;
		try {
			controlActions.perform_ClickWithJavaScriptExecutor(GridLocationDDL);
			controlActions.click(LocationInput);
			controlActions.sendKeys(LocationInput, location);
			Sync();
			LocationGrd = controlActions.perform_GetElementByXPath(TaskSchedulerPageLocators.LOCATION_GRD, 
					"LOCATION", location);
			controlActions.click(LocationGrd);
			logInfo("Location " + location + " is selected");
			Sync();
			return true;
		}
		catch(Exception e) {
			logError("Failed to select Location - " + location + " "
					+ e.getMessage());
			return false;
		}
	}

	public boolean clickAllTasks()
	{
		try {
			AllBtn.click();
			Sync();
			logInfo("Clicked on All button");
			return true;
		}
		catch(Exception e) {
			logError("Failed to click on All Button " + e.getMessage());
			return false;
		}
	}

	public boolean verifyInScheduleView(String taskname) {
		WebElement TaskScheduleView = null;
		try {
			TaskScheduleView = controlActions.perform_GetElementByXPath(TaskSchedulerPageLocators.TASK_SCHEDULE_VIEW,
					"SCHEDULENAME", taskname);
			if(controlActions.isElementDisplayed(TaskScheduleView)) {								
				logInfo("Schedule Verified - " + taskname);
				return true;
			}
			logInfo("Unable to verify Schedule - " + taskname);
			return false;
		}
		catch(Exception e) {
			logError("Failed to verify schedule" +  taskname + e.getMessage());
			return false;
		}
	}

	/**
	 * This method is a wrapper used create a schedule
	 * @author choubey_a
	 * @param tsd object of TaskScheduleDetails class 
	 * @param schedulename
	 * @param location
	 * @return boolean return true if schedule is created
	 */

	public boolean createTaskSchedule(TaskScheduleDetails tsd, String schedulename, String location) {
		try {
			if(!fillstepOneData(tsd)){
				return false;
			}
			if(!fillstepTwoData(tsd)){
				return false;
			}
			if(!fillstepThreeData(tsd)){
				return false;
			}
			if(!selectLocationForGrid(location)){
				return false;
			}
			if(!clickAllTasks()){
				return false;
			}
			if(!verifyInScheduleView(schedulename)){
				return false;
			}
			logInfo("Created Schedule - " + schedulename);
			return true;
		}catch(Exception e) {
			logError("Failed to create schedule" +  schedulename + e.getMessage());
			return false;
		}

	}

	/**
	 * This method first searches required task using location and taskName than to check whether the task scheduler link is Editable or not
	 * @author pednekar_pr
	 * @param location This value stores the value of location
	 * @param taskName This value stores the value of taskName
	 * @return boolean return true if task created is clickable hence scheduled task can be edited
	 */

	public boolean isScheduleEditable(String location, String taskName)
	{
		try {

			if(!selectLocationForGrid(location)){
				return false;
			}
			if(!clickAllTasks()){
				return false;
			}
			if(!verifyInScheduleView(taskName)){
				return false;
			}
			WebElement schedule = controlActions.perform_GetElementByXPath(TaskSchedulerPageLocators.SCHEDULE_VIEW,
					"SCHEDULE",taskName);
			if (schedule!=null)
			{	
				controlActions.click(schedule);
				logInfo("Link is clickable"+taskName);
				return true;
			}
			else
			{
				logError("Failed to click on Scheduled Task ");
				return false;
			}

		}
		catch (Exception e) {
			logError("Failed to click on Scheduled Task "+taskName + e.getMessage());
			return false;
		}
	}

	public boolean isTaskEditable(String location, String taskName)
	{
		try {

			if(!selectLocationForGrid(location)){
				return false;
			}
			
			WebElement TaskScheduleView = controlActions.perform_GetElementByXPath(TaskSchedulerPageLocators.TASK_VIEW,
					"TASKNAME", taskName);
			if(!controlActions.isElementDisplayed(TaskScheduleView))
				return false;
			
				logInfo("Schedule Verified - " + taskName);
				controlActions.click(TaskScheduleView);
				logInfo("Link is clickable"+taskName);
				return true;

		}
		catch (Exception e) {
			logError("Failed to click on Scheduled Task "+taskName + e.getMessage());
			return false;
		}
	}

	
	
	
	
	
	/**
	 * This method is used to check whether Delete Button is displayed or not
	 * @author pednekar_pr
	 * @param location This value stores the value of location
	 * @param taskName This value stores the value of taskName
	 * @return boolean return true if Delete Button is displayed
	 */

	public boolean isScheduleDeletable(String location, String taskName)
	{
		boolean result=false;

		try {
			cp= new CommonPage(driver);


			result =controlActions.isElementDisplay(DeleteBtn);
			if(result)
			{
				logInfo("Delete btn is visible");
			}
			else
				logError("Failed to display Delete btn");
			controlActions.click(EditCancelBtn);

		}
		catch (Exception e) {
			logError("Failed to display delete buttons "+taskName + e.getMessage());
			result= false;
		}

		return result;
	}

	/**
	 * This method is used to check whether status of given taskname on next day matches value paramter or not
	 * @author pednekar_pr
	 * @param taskname   This value stores the value of taskname
	 * @param columnName This value stores the value of columnName
	 * @param value 	 This stores the taskstatus value
	 * value can be Active / Finished / Scheduled Due Today / Past Due
	 * @return boolean return true when status column value for given taskname matches value parameter
	 */

	public boolean verifyTaskStatusColumn(String taskname, String columnName, String value) {

		String finalColumnNameXpath = null; 
		try {

			controlActions.clickOnElement(ByDateBtn);
			controlActions.clickOnElement(CalenderNextBtn);
			logInfo("Checking task visibility on next day after Schedule ");
			Sync();

			//if on view by task view then change to schedule task view
			if(value==Task_Status.ACTIVE || value==Task_Status.FINISHED)
			{
				if (controlActions.isElementDisplay(ViewByScheduleLink))
				{
					controlActions.clickOnElement(ViewByScheduleLink);
				}
				//  string finalcolumnxpath contains xpath as per viewbyschedule page
				String taskXpath= controlActions.perform_GetDynamicXPath(TaskSchedulerPageLocators.TASK_STATUS, "TASKNAME", taskname);
				finalColumnNameXpath = controlActions.perform_GetDynamicXPath(taskXpath,"COLUMNINDEXNO", value);
			}
			//if on scheduletask link than switch to view by task view
			else	
			{
				if (controlActions.isElementDisplay(ViewByTaskLink))
					controlActions.clickOnElement(ViewByTaskLink);
				//  string finalColumnNameXpath contains xpath as per viewbytask page
				String taskXpath= controlActions.perform_GetDynamicXPath(TaskSchedulerPageLocators.TASK_STATUS1, 
						"TASKNAME", taskname);
				finalColumnNameXpath = controlActions.perform_GetDynamicXPath(taskXpath,"COLUMNINDEXNO", value);

			}

			// checking xpath exists or not of finalColumnNameXpath
			if (controlActions.isElementDisplayed(finalColumnNameXpath)==true)
			{
				logInfo("TaskStatus is "+ value +"  on next day");				
				return true;
			}
			else
			{
				logInfo("TaskStatus is not "+ value +" on next day");
				return false;
			}
		}
		catch(Exception e) {
			logError("Failed, to verify status of task - "
					+ e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to fillstep3 data of form
	 * @author pednekar_pr
	 * @param TSD   This value stores the Task Scheduler Details
	 * @param days This stores count of end date from current date
	 * @param interval 	 This stores the EveryDay value of form
	 * EveryDate value stores value after how many days daily task should trigger
	 * @return boolean return true when step 3 data is filled
	 */
	public boolean fillStepThreeDataForDaily(TaskScheduleDetails TSD, int days, int interval) {
		WebElement OccurredValue = null;
		try {
			controlActions.click(TaskOccurDdl);
			OccurredValue = controlActions.perform_GetElementByXPath(TaskSchedulerPageLocators.OCCURRED_VALUE,
					"occurrence", TSD.taskoccure);
			controlActions.perform_ClickWithJavaScriptExecutor(OccurredValue);
			//set every day value by clicing on upp arrow
			logInfo("Clicking Up arrow to set EveryDay value equal to "+ interval );
			for(int i=1;i<interval;i++)
			{
				controlActions.click(EveryDayUpArrow);
			}

			dateTime.setDate(Step3FirstDueDatetxt, "Day", 0);
			dateTime.setTime(TaskDueTime, 0, 3, false, false);
			if (TSD.haveEndDate == true) {
				controlActions.click(enddateflag);
				dateTime.setDate(enddatetxt, "Day", days);
			}
			controlActions.clickOnElement(SaveBtn);
			Sync();
			logInfo("Daily Schedule Created");
			logInfo(TSD.taskoccure + "-" + "Schedule Created");
			return true;
		} catch (Exception e) {
			logError("Failed to create schedule of type  - " + TSD.taskoccure + e.getMessage());
			return false;
		}

	}

	/**
	 * This method is used to check whether due date is correct for given range of current date to end date with interval value
	 * @author pednekar_pr
	 * @param TSD   This value stores the Task Scheduler Details
	 * @param days This stores count of end date from current date
	 * @param interval 	 This stores the EveryDay value of form
	 * @param currentDate This stores the current Date value i.e when task was created
	 * EveryDate value stores value after how many days daily task should trigger
	 * @return boolean return true when due date is correctly displayed for given range of dates with given interval
	 */

	public boolean verifyTaskDueDate(TaskScheduleDetails TSD, int days, int interval, String currentDate)
	{
		try {
			String dateFormat = "MM/dd/yyyy";
			//String currentDate = dateTime.getTodayDate(dateFormat, TIMEZONE.REPUBLICOFINDIA);
			String taskDate = controlActions.perform_GetDynamicXPath(TaskSchedulerPageLocators.TASK_DUE_DATE,
					"TASKNAME", TSD.taskname);
			String updatedDate= currentDate ;
			String endDate= dateTime.addSubtractDaysFromDate(dateTime.getDate(currentDate, dateFormat),+(days), dateFormat);
			String taskDueDate ;
			//flag is used to store flag value as 1 if element is present else stores 0
			int flag=0;
			//compare stores comparision values of two dates
			ComparisonValue compare = null;
			compare = dateTime.compareDates(updatedDate, endDate, null, null);
			// if number of days from current date to end date is greater than or equal to interval of scheduling task
			if(days>=interval)
			{

				//check duedate till updatedDate value equals or less than endDate value
				while((compare.equals(ComparisonValue.LESSER))||(compare.equals(ComparisonValue.EQUALS)))
				{
					flag=0;
					updatedDate= dateTime.addSubtractDaysFromDate(dateTime.getDate(updatedDate, dateFormat),+(interval), dateFormat);
					taskDueDate = controlActions.perform_GetDynamicXPath(taskDate,
							"DATEDUEBY", updatedDate);
					
					logInfo("updated Date: "+updatedDate );
					logInfo(" taskDueDate: "+ taskDueDate);
					//click next button of calender till reaches required date					
					for (int i=1;i<=interval;i++)
					{
						controlActions.clickOnElement(CalenderNextBtn);
						Sync();
					}

						Sync();
					if((driver.findElements(By.xpath(taskDueDate)).size())==1)
					{
						logInfo("Daily task "+TSD.taskname +" is visible for date"+updatedDate);
						flag=1;
					}

					compare = dateTime.compareDates(updatedDate, endDate, null, null);

					if(flag==0)
						break;

				}
			}

			// if number of days from current date to end date is less than interval of scheduling task
			else
			{

				taskDueDate = controlActions.perform_GetDynamicXPath(taskDate,
						"DATEDUEBY", currentDate);
				flag=0;

				if((driver.findElements(By.xpath(taskDueDate)).size())==1)
				{
					logInfo("Daily task "+TSD.taskname +" is visible for date"+updatedDate);
					flag=1;
				}
			}


			if((flag==0)&&(compare.equals(ComparisonValue.GREATER))||(flag==1))
			{
				logInfo("DueDate of Daily task "+TSD.taskname +" is proper from date "+currentDate+" to "+updatedDate +" with interval of "+ interval+" days");
				return true;
			}

			else
			{
				logError("DueDate of Daily task "+TSD.taskname +" is incorrect");
				return false;
			}

		}
		catch (Exception e) {
			logError("Failed to check dislay of due date for given task " + TSD.taskname + e.getMessage());
			return false;
		}

	}


	/**
	 * This method is used to check whether form/document can be search properly
	 * @author pednekar_pr
	 * @param TSD   This value stores the Task Scheduler Details
	 * @param DataName This stores name of document/form
	 * @return boolean return true when form/document is successfully searched
	 */
	public boolean searchData(TaskScheduleDetails TSD) {
		try {
			if(!clickAddNewTaskBtn())
			return false;
			
			controlActions.perform_ClickWithJavaScriptExecutor(LocationDd);
			LocationSearchTxt.click();
			controlActions.sendKeys(LocationSearchTxt, TSD.locationvalue);
			Sync();
			controlActions.actionEnter();
			if (TSD.formORdocument.equalsIgnoreCase("Form")) {
				controlActions.clickOnElement(ProcessFormRb);
				Sync();
				String selector = "div[title='"+ TSD.formORdocumentvalue +"']";
				controlActions.JSClick(driver, selector);
				logInfo("Succesfully searched form" );
				controlActions.clickOnElement(CancelBtn);
				Sync();
			} 
			else {
				controlActions.perform_clickElement_ByElement(ProcessDocumentRb);
				Sync();
				//String selector = "div[title='"+ TSD.formORdocumentvalue +"']";
				//controlActions.JSClick(driver, selector);
				if(controlActions.JSSetValueFromList(driver, "div[class='scs-single-select-item-text']" ,TSD.formORdocumentvalue,1))
					logInfo("Succesfully searched document" );	
				else
				{
					logError("Failed to search document");
					return false;
				}

			controlActions.clickOnElement(NextBtn);
			Sync();
			}
			logInfo("Step one data filed successfully");
			Sync();
			return true;
		} 
		catch (Exception e) {
			logError("Failed to search data on step one" + e.getMessage());
			return false;
		}

	}

	/**
	 * This method is used to fillstep2 data after selecting Process Document in Schedule Task form
	 * @author pednekar_pr
	 * @param TSD   This value stores the Task Scheduler Details
	 * @return boolean return true when step 2 data is successfully filled
	 */
	public boolean fillstepTwoDocData(TaskScheduleDetails TSD) {
		try {
			controlActions.sendKeys(TaskNameTxt, TSD.taskname);
			threadsleep(2000);
			controlActions.perform_ClickWithJavaScriptExecutor(SelectWorkGroupDdl);
			threadsleep(2000);
			controlActions.sendKeys(SelectDocWorkgroupSearchBox, TSD.workGroup);
			threadsleep(2000);
			controlActions.actionEnter();
			controlActions.sendKeys(TaskDescriptionTxt, TSD.taskDescription);
			controlActions.click(NextBtn);
			logInfo("Step two data filed successfully");
			return true;
		} catch (Exception e) {
			logError("Failed to fill data on step one" + e.getMessage());
			return false;
		}

	}

	/**
	 * This method is used to fillstep3 data of Monthly type form
	 * @author pednekar_pr
	 * @param TSD   This value stores the Task Scheduler Details
	 * @param days This stores count of end date from current date
	 * @param interval 	 This stores the EveryDay value of form
	 * EveryDate value stores value after how many days daily task should trigger
	 * @return boolean return true when step 3 data is filled
	 */
	public boolean fillStepThreeMonthlyData(TaskScheduleDetails TSD, int days, int interval) {
		WebElement OccurredValue = null;
		try {
			controlActions.click(TaskOccurDdl);
			OccurredValue = controlActions.perform_GetElementByXPath(TaskSchedulerPageLocators.OCCURRED_VALUE,
					"occurrence", TSD.taskoccure);
			controlActions.perform_ClickWithJavaScriptExecutor(OccurredValue);
			//set every day value by clicing on upp arrow
			logInfo("Clicking Up arrow to set EveryDay value equal to "+ interval );
			for(int i=1;i<interval;i++)
			{
				controlActions.click(EveryDayUpArrow);
			}

			dateTime.setDate(Step3FirstDueDatetxt, "Day", 0);
			dateTime.setTime(DueTimeInput, 0, 1, false, false);
			if (TSD.haveEndDate == true) {
				controlActions.click(enddateflag);
				dateTime.setDate(enddatetxt, "Day", days);
			}
			controlActions.clickOnElement(SaveBtn);
			Sync();
			logInfo("Monthly Schedule Created");
			logInfo(TSD.taskoccure + "-" + "Schedule Created");
			return true;
		} catch (Exception e) {
			logError("Failed to create schedule of type  - " + TSD.taskoccure + e.getMessage());
			return false;
		}

	}

	/**
	 * This method is used to fillstep3 data of Yearly type form
	 * @author pednekar_pr
	 * @param TSD   This value stores the Task Scheduler Details
	 * @param days This stores count of end date from current date
	 * @param interval 	 This stores the EveryDay value of form
	 * EveryDate value stores value after how many days daily task should trigger
	 * @return boolean return true when step 3 data is filled
	 */
	public boolean fillStepThreeYearlyData(TaskScheduleDetails TSD, int year, int interval) {
		WebElement OccurredValue = null;
		try {
			controlActions.click(TaskOccurDdl);
			OccurredValue = controlActions.perform_GetElementByXPath(TaskSchedulerPageLocators.OCCURRED_VALUE,
					"occurrence", TSD.taskoccure);
			controlActions.perform_ClickWithJavaScriptExecutor(OccurredValue);
			//set every day value by clicing on upp arrow
			logInfo("Clicking Up arrow to set EveryDay value equal to "+ interval );
			for(int i=1;i<interval;i++)
			{
				controlActions.click(EveryDayUpArrow);
			}

			dateTime.setDate(Step3FirstDueDatetxt, "Day", 0);
			dateTime.setTime(DueTimeInput, 0, 1, false, false);
			if (TSD.haveEndDate == true) {
				controlActions.click(enddateflag);
				dateTime.setYear(Step3YearlyEndDateInput,year);
			}
			controlActions.clickOnElement(SaveBtn);
			Sync();
			logInfo("Yearly Schedule Created");
			logInfo(TSD.taskoccure + "-" + "Schedule Created");
			return true;
		} catch (Exception e) {
			logError("Failed to create schedule of type  - " + TSD.taskoccure + e.getMessage());
			return false;
		}

	}

	/**
	 * This method is used to check Index View after selecting Weekly Tab & Daily Tab
	 * @author pednekar_pr
	 * @param TSD   This value stores the Task Scheduler Details
	 * @return boolean return true when Index View after selecting Weekly Tab & Daily Tab
	 */
	public boolean Indexview(TaskScheduleDetails TSD)
	{
		try {
			//view by task
			if(!controlActions.isElementDisplay(ViewByTaskBtn)) {
				logError("Failed to click View By task button");
				return false;
			}

			controlActions.clickOnElement(ViewByTaskBtn);
			logInfo("Clicked View By Task Button succesfully!!");

			// weekly tab
			if(!controlActions.isElementDisplay(WeeklyTab)) {
				logError("Failed to click WeeklyTab button");
				return false;
			}
			controlActions.clickOnElement(WeeklyTab);
			logInfo("Clicked WeeklyTab succesfully!!");

			logInfo("Checked weekly schedule of current week");
			controlActions.clickOnElement(CalenderPrevBtn);
			logInfo("Checked weekly schedule of previous week");
			// daily tab
			if(!controlActions.isElementDisplay(DailyTab)) {

				logError("Failed to click Daily Tab button");
				return false;
			}
			controlActions.clickOnElement(DailyTab);
			logInfo("Clicked DailyTab succesfully!!");

			return true;	
		}
		catch (Exception e) {
			logError("Failed to create schedule of type  - " + TSD.taskoccure + e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to check if location value matches form location grid value
	 * @author pednekar_pr
	 * @param location   This value stores location grid value on the Task Scheduler Page
	 * @return boolean return true when location value matches form location grid value
	 */

	public boolean verifyLocationOfForm(String location)
	{
		try {
			if(!clickAddNewTaskBtn())
				return false;
			Sync();
			String locationValue=LocationDd.getText();
			System.out.print(locationValue);
			System.out.print( location);
			if(locationValue.equals(location))
			{
				logInfo("Grid location matches form location ");
				return true;	
			}

			else
			{
				logError("Grid location doesn't matches form location ");
				return false;
			}


		}
		catch (Exception e) {
			logError("Failed to verify Location of form - " + location + e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to fill step 3 data of TaskScheduler Form
	 * @author pednekar_pr
	 * @param TSD   This value stores the Task Scheduler Details
	 * @param dueTime This stores minutes value
	 * @return boolean return true when step 3 data is filled.
	 */

	public boolean fillDuetime(TaskScheduleDetails TSD, int dueTime) {
		WebElement OccurredValue = null;
		try {
			controlActions.click(TaskOccurDdl);
			OccurredValue = controlActions.perform_GetElementByXPath(TaskSchedulerPageLocators.OCCURRED_VALUE,
					"occurrence", TSD.taskoccure);
			controlActions.perform_ClickWithJavaScriptExecutor(OccurredValue);

			switch (TSD.taskoccure) {
			case "One Time Only":
				Sync();
				dateTime.setDateTime(Step3DuedatetimeTxt, "Day", 0 , 0, 0, false, false);
				controlActions.clickOnElement(SaveBtn);
				Sync();

				logInfo("One Time Schedule Created");
				break;

			case "Daily":
				dateTime.setDate(Step3FirstDueDatetxt, "Day", 0);
				dateTime.setTime(TaskDueTime, 0, dueTime, false, false);
				if (TSD.haveEndDate == true) {
					controlActions.click(enddateflag);
					dateTime.setDate(enddatetxt, "Day", 10);
				}
				controlActions.clickOnElement(SaveBtn);
				Sync();
				logInfo("Daily Schedule Created");
				break;

			case "Weekly":
				String dayOfWeek = get_Current_Day_Of_Week();
				logInfo("Today's Day" + dayOfWeek);
				WebElement DayOfWeek = controlActions.perform_GetElementByXPath(TaskSchedulerPageLocators.DAY_CHCKBX, "DAY",
						dayOfWeek);
				controlActions.perform_ClickWithJavaScriptExecutor(DayOfWeek);
				dateTime.setDate(Step3FirstDueDatetxt, "Day", 0);
				dateTime.setTime(TaskDueTime, 0, dueTime, false, false);
				if (TSD.haveEndDate == true) {
					controlActions.click(enddateflag);
					dateTime.setDate(enddatetxt, "Day", 30);

				}
				controlActions.clickOnElement(SaveBtn);
				Sync();
				logInfo("Weekly Schedule Created");
				break;
			case "Monthly":
				dateTime.setDate(Step3FirstDueDatetxt, "Day", 0);
				dateTime.setTime(DueTimeInput, 0, dueTime, false, false);
				if (TSD.haveEndDate == true) {
					controlActions.click(enddateflag);
					dateTime.setDate(enddatetxt, "Day", 90);
				}
				controlActions.clickOnElement(SaveBtn);
				Sync();
				logInfo("Monthly Schedule Created");
				break;
			case "Yearly":
				dateTime.setDate(Step3FirstDueDatetxt, "Day", 0);
				dateTime.setTime(DueTimeInput, 0, dueTime, false, false);
				if (TSD.haveEndDate == true) {
					controlActions.click(enddateflag);
					dateTime.setYear(Step3YearlyEndDateInput,2);
				}
				controlActions.clickOnElement(SaveBtn);
				Sync();
				logInfo("Yearly Schedule Created");
				break;
			case "Interval":
				dateTime.setDate(IntervalStartDateInput, "Day", 0);
				dateTime.setDate(IntervalEndDateInput, "Day", 1);
				String dayOfWeek1 = get_Current_Day_Of_Week();
				WebElement DayOfWeek1 = controlActions.perform_GetElementByXPath(TaskSchedulerPageLocators.DAY_CHCKBX, "DAY",
						dayOfWeek1);
				controlActions.perform_ClickWithJavaScriptExecutor(DayOfWeek1);
				dateTime.setTime(IntervalStartTimeInput, 0, 0, false, false);
				dateTime.setTime(IntervalEndTimeInput, 1, 30, false, false);
				controlActions.perform_ClickWithJavaScriptExecutor(IntervalDDL);
				IntervalInput.click();
				controlActions.sendKeys(IntervalInput, TSD.interval);
				threadsleep(2000);
				controlActions.actionEnter();	
				if(TSD.ramdomize == true) {
					controlActions.perform_ClickWithJavaScriptExecutor(RandomizeInput);
					logInfo("Randomize Start Time Selected");
				}
				controlActions.clickOnElement(SaveBtn);
				Sync();
				logInfo("Interval Schedule Created");
				break;
			}
			logInfo(TSD.taskoccure + "-" + "Schedule Created");
			return true;
		} catch (Exception e) {
			logError("Failed to create schedule of type  - " + TSD.taskoccure + e.getMessage());
			return false;
		}

	}

	/**
	 * This method is used to skip task 
	 * @author pednekar_pr
	 * @param schedulename   This value stores the Schedule name
	 * @return boolean return true when task is skipped.
	 */	
	public boolean skipTask(String schedulename) {
		try {
			if(controlActions.isElementDisplay(ViewByTaskBtn)) {
				controlActions.clickOnElement(ViewByTaskBtn);
			}
			//			Sync();
			WebElement schedule = controlActions.perform_GetElementByXPath(TaskSchedulerPageLocators.SCHEDULE_SELECT,
					"SCHEDULENAME", schedulename);
			schedule.click();
			Sync();
			controlActions.WaitforelementToBeClickable(TaskSelect);
			TaskSelect.click();
			//			Thread.sleep(1000);
			controlActions.WaitforelementToBeClickable(SkipBtn);
			SkipBtn.click();
			Sync();
			controlActions.sendKeys(CommentTxtBox, "Skip this task");
			controlActions.WaitforelementToBeClickable(SkipPopupSkipBtn);
			SkipPopupSkipBtn.click();
			Sync();
			//controlActions.click(CloseBtn);
			logInfo("The skip button is clicked");
			Sync();
			return true;
		}
		catch(Exception e) {
			logError("Failed to click skip button - " +e.getMessage());
			return false;
		}
	}	


	/**
	 * This method is used to verify task status on same day in Detailed View
	 * @author pednekar_pr
	 * @param schedulename  This value stores the Schedule name
	 * @param status  This value stores the status of task
	 * Status can be Skipped / To Be Skipped / Active / Finished / Scheduled Due Today / Past Due
	 * @return boolean return true when task with given status is present.
	 */

	public boolean verifyTaskStatusInDetailView(String schedulename, String status) {
		try {
			String taskXpath= controlActions.perform_GetDynamicXPath(TaskSchedulerPageLocators.TASK_STATUS1, 
					"TASKNAME", schedulename);
			String	SkippedStatus = controlActions.perform_GetDynamicXPath(taskXpath,"COLUMNINDEXNO", status);
			if(!(controlActions.isElementDisplayed(SkippedStatus))) {
				logError("The status of the task is not "+ status);
			}
			//			Sync();
			logInfo("The status of task is "+ status);
			return true;
		}catch(Exception e) {
			logError("Failed to check status of task - " +e.getMessage());
			return false;
		}
	}	

	/**
	 * This method is used to check whether link is clickable or not
	 * @author pednekar_pr
	 * @param schedulename  This value stores the Schedule name
	 * @return boolean return true when link is clickable
	 */
	public boolean checkLinkIsActive(String schedulename) {
		try {
			WebElement schedule = controlActions.perform_GetElementByXPath(TaskSchedulerPageLocators.SCHEDULE_SELECT,
					"SCHEDULENAME", schedulename);
			if(controlActions.isElementDisplayed(schedule))
			{
				schedule.click();
				Sync();
				logInfo(schedulename +" link is clickable ");
				return true;
			}
			else
			{
				logInfo(schedulename +" link cannot be click ");
				return false;				
			}

		}catch(Exception e) {
			logError("Failed to check " + schedulename +" link is active/inactive - " +e.getMessage());
			return false;
		}
	}	

	/**
	 * This method is used to check whether selected task has given status or not in Grid View
	 * @author pednekar_pr
	 * @param status  This value stores the status value to be matched
	 * @return boolean return true when grid status value of recently selected task matches given status
	 */
	public boolean verifyTaskStatusInGridView(String status) {
		try {
			WebElement TaskGridStatus = controlActions.perform_GetElementByXPath(TaskSchedulerPageLocators.TASK_GRID_STATUS,
					"STATUS", status);
			if(controlActions.isElementDisplayed(TaskGridStatus))
			{	
				controlActions.click(CloseBtn);
				Sync();
				logInfo(status+"status is not displayed for given task in Grid view");
				return true;
			}
			else
			{
				logInfo("Failed to display "+ status+ " status for given task in Grid view");
				return false;
			}
		}catch(Exception e) {
			logError("Failed to check status for given task in Grid view - " +e.getMessage());
			return false;
		}
	}	

	/**
	 * This method is used to click Update Button on Edit Schedule Page
	 * @author pednekar_pr
	 * @return boolean return true when update Button is clicked
	 */
	public boolean clickUpdateBtn() {
		try {
			controlActions.WaitforelementToBeClickable(UpdateBtn);
			controlActions.clickOnElement(UpdateBtn);
			Sync();
			logInfo("Edit Task Schedule title is dispalyed");
			return true;
			
		}catch(Exception e) {
			logError("Failed to click Update Button - " +e.getMessage());
			return false;
		}
	}	
	
	/**
	 * This method is used to update Workgroup on Edit Schedule Page
	 * @author pednekar_pr
	 * @param workGroup  This value stores the old WorkGroup name
	 * @param workGroup1  This value stores the new WorkGroup name
	 * @return boolean return true on updating work Group
	 */
	public boolean updateWorkGroup(String workGroup, String workGroup1) {
		try {
			
			WebElement workGroupDd= controlActions.perform_GetElementByXPath(TaskSchedulerPageLocators.SELECTWORKGROUP_DDL, "Select Workgroup",workGroup);			
			controlActions.perform_ClickWithJavaScriptExecutor(workGroupDd);
			WorkGroupEditSearchTxt.click();
			controlActions.sendKeys(WorkGroupEditSearchTxt,workGroup1);
			WebElement workGroupOption= controlActions.perform_GetElementByXPath(TaskSchedulerPageLocators.WORK_GROUP_SEARCH_TEXT_SELECT, "WORKGROUP",workGroup1);		
			controlActions.click(workGroupOption);
			Sync();
			logInfo("Edit Task Schedule title is dispalyed");
			return true;
			
		}catch(Exception e) {
			logError("Failed to click Update Button - " +e.getMessage());
			return false;
		}
	}	
	
	/**
	 * This method is used to verify update Workgroup on View by schedule
	 * @author pednekar_pr
	 * @param workGroup  This value stores the old WorkGroup name
	 * @param workGroup1  This value stores the new WorkGroup name
	 * @return boolean return true on verifying updation of  work Group
	 */
	public boolean verifyUpdatedWorkGroupValue(String workGroup1, String taskName) {
		try {
			

			taskView();
			logInfo("Clicked ViewbyTask Btn succesfully");
			
			WebElement schedule = controlActions.perform_GetElementByXPath(TaskSchedulerPageLocators.SCHEDULE_VIEW,
					"SCHEDULE",taskName);
			if (schedule==null)
			return false;
			
			String taskXpath= controlActions.perform_GetDynamicXPath(TaskSchedulerPageLocators.SCHEDULE_VIEW_WORKGROUP, "TASKNAME", taskName);
			WebElement finalColumnNameXpath = controlActions.perform_GetElementByXPath(taskXpath,"WORKGROUPNAME", workGroup1);
			
			if(!controlActions.isElementDisplay(finalColumnNameXpath))
				return false;
			
			logInfo("Verified Workgroup has been updated for given task");
			return true;
			
		}catch(Exception e) {
			logError("Failed to verify Updated workgroup - " +e.getMessage());
			return false;
		}
	}		
	
	
	public static class	ViewBySchedule_Menu {
		public static final String TASK = "Task";
		public static final String STATUS = "Status";
		public static final String TASKSTART = "Task Start";
		public static final String TASKDUE = "Task Due";
		public static final String ASSIGNEDTO = "Assigned To";
		public static final String RESOURCE = "Resource";

	}

	public static class Task_Status{
		public static final String ACTIVE = "Active";		
		public static final String FINISHED = "Finished";
		public static final String PASTDUE = "Past Due";
		public static final String SCHEDULED = "Scheduled Due Today";
		public static final String TOBESKIPPED = "To Be Skipped";
		public static final String SKIPPED = "Skipped";
		public static final String RECALLED = "Recalled";

	}

	public static class TaskScheduleDetails {
		public String locationvalue;
		public String formORdocument;
		public String formORdocumentvalue;
		public String taskname;
		public String resource;
		public String workGroup;
		public String taskDescription;
		public String taskoccure;
		public String DueDate;
		public String DueTime;
		public String TimeZone;
		public String AssignbeforeDays;
		public String AssignbeforeHours;
		public String Assignbeforemin;
		public String duetimein24format;
		public String Daily;
		public String every;
		public String firstduedate;
		public static String taskendDate;
		public boolean haveEndDate;
		public boolean ramdomize;
		public String interval;

	}
}
