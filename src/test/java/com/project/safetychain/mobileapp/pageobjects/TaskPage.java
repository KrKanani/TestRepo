package com.project.safetychain.mobileapp.pageobjects;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.project.testbase.TestBase;
import com.project.utilities.CommonMethods;
import com.project.utilities.ControlActions_MobileApp;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class TaskPage extends TestBase {

	public class HomepageLocators {

		public final String formName = null;
		public final String FORM_NAME = null;

		public void main(String[] args) {
			// TODO Auto-generated method stub

		}

	}

	AppiumDriver<MobileElement> appiumDriver;
	WebDriverWait wait;
	ControlActions_MobileApp mobControlActions;
	CommonMethods commonmethods;

	public TaskPage(AppiumDriver<MobileElement> driver) {
		this.appiumDriver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		mobControlActions = new ControlActions_MobileApp(this.appiumDriver);
		commonmethods = new CommonMethods(this.appiumDriver);
	}

	@FindBy(id = TaskPageLocators.FILTER_BTN)
	public WebElement filterBtn;

	@FindBy(id = TaskPageLocators.ASSIGNEDTO_BTN)
	public WebElement assignedToBtn;

	@FindBy(id = TaskPageLocators.STATUS_BTN)
	public WebElement statusBtn;

	@FindBy(xpath = TaskPageLocators.DUEBY_BTN)
	public WebElement dueByBtn;

	@FindBy(id = TaskPageLocators.CLEAR_BTN)
	public WebElement clearBtn;

	@FindBy(id = TaskPageLocators.CAL_FILTER_BTN)
	public WebElement CalFilterBtn;

	@FindBy(id = TaskPageLocators.CURRENTMONTH_TXT)
	public WebElement currentMonthTxt;

	@FindBy(id = TaskPageLocators.NXT_MONTH_BTN)
	public WebElement nxtMonthBtn;

	@FindBy(xpath = TaskPageLocators.DAY_TOBE_BTN)
	public WebElement dayToBeSelected;

	@FindBy(xpath = TaskPageLocators.STATUS_FILTER)
	public WebElement statusFilter;

	@FindBy(xpath = TaskPageLocators.ASSIGNTO_FILTER)
	public WebElement workGroupFilter;

	@FindBy(xpath = TaskPageLocators.STATUS_BTN1)
	public WebElement statusBtn1;

	@FindBy(id = TaskPageLocators.DUEDATE_GRID)
	public List<WebElement> dueDateGrid;

	@FindBy(id = TaskPageLocators.GROUPNAME_GRID)
	public List<WebElement> groupNameGrid;

	@FindBy(id = TaskPageLocators.LIMIT_DATA)
	public WebElement limiDataToggle;

	@FindBy(id = TaskPageLocators.LIMIT_DATA_DEFAULT)
	public WebElement limitDataDeafault;

	@FindBy(xpath = TaskPageLocators.DATA_OPTION_TOBE_SELECTED)
	public WebElement OptionToBeSelected;

	@FindBy(xpath = TaskPageLocators.TASK_SUBMIT)
	public WebElement taskSubmit;

	@AndroidFindBy(id = REG_AllFormsSavedLocators.SETTINGTITLE)
	public WebElement SettingTitle;

	@AndroidFindBy(id = REG_AllFormsSavedLocators.OFFLINETOGGLE)
	public WebElement offlineToggleBtn;
	@AndroidFindBy(id = REG_AllFormsSavedLocators.LIMITDATATOGGLE)
	public WebElement limitDataToggleBtn;
	@AndroidFindBy(id = REG_AllFormsSavedLocators.ENABLELOGTOGGLE)
	public WebElement enableLogToggleBtn;
	@AndroidFindBy(id = REG_AllFormsSavedLocators.CLEARSUBBTN)
	public WebElement clearSubBtn;
	@AndroidFindBy(id = REG_AllFormsSavedLocators.LIMITDATAITEMS)
	public WebElement limitDataItems;

	@AndroidFindBy(xpath = REG_AllFormsSavedLocators.OFFLINEMODE)
	public WebElement offlineModeText;
	@AndroidFindBy(xpath = REG_AllFormsSavedLocators.LIMITDATA)
	public WebElement limiDataText;
	@AndroidFindBy(xpath = REG_AllFormsSavedLocators.LIMITDATADURATIOM)
	public WebElement limitDurText;
	@AndroidFindBy(xpath = REG_AllFormsSavedLocators.SELECTDURATION)
	public WebElement selectDurationText;
	@AndroidFindBy(id = REG_AllFormsSavedLocators.SELECTDURATIONANGLEBUTTON)
	public WebElement selectDurationAngle;
	@AndroidFindBy(xpath = REG_AllFormsSavedLocators.ENABLELOGS)
	public WebElement enableLogsText;
	@AndroidFindBy(xpath = REG_AllFormsSavedLocators.CLEARSUBMISSIONS)
	public WebElement clearSubText;

	public void actionEnter() {
		// TODO Auto-generated method stub

	}

	public boolean selectDataLimit(String days) {
		try {
			ControlActions_MobileApp.waitForVisibility(limitDataDeafault, 60);
			ControlActions_MobileApp.click(limitDataDeafault);
			WebElement formCheck = null;
			formCheck = ControlActions_MobileApp.perform_GetElementByXPath(TaskPageLocators.DATA_OPTION_TOBE_SELECTED,
					"DAYS", days);
			ControlActions_MobileApp.waitForVisibility(formCheck, 60);
			ControlActions_MobileApp.click(formCheck);

			logInfo("Selected Data Limit = " + days);
			return true;
		} catch (Exception e) {
			logError("Failed to click -Task - " + e.getMessage());
			return false;
		}
	}

	public boolean ClickTask(String taskName) {
		try {
			Thread.sleep(5000);
			WebElement formCheck = null;
			formCheck = ControlActions_MobileApp.perform_GetElementByXPath(HomePageLocators.Form_Name,
					"FormNameLocator", taskName);
			ControlActions_MobileApp.waitForVisibility(formCheck, 60);
			ControlActions_MobileApp.click(formCheck);
			logInfo("Clicked on Task = " + taskName);
			return true;
		} catch (Exception e) {
			logError("Failed to click -Task - " + e.getMessage());
			return false;
		}
	}

	public boolean submitTask() {
		try {

			Thread.sleep(1000);
			List<String> removePicsArgs1 = Arrays.asList("tap", "967 1708");
			Map<String, Object> removePicsCmd1 = new HashMap<String, Object>();
			removePicsCmd1.put("command", "input");
			removePicsCmd1.put("args", removePicsArgs1);

			appiumDriver.executeScript("mobile: shell", removePicsCmd1);
			logInfo("Swipe done");
			Thread.sleep(2000);
			logInfo("Submit button has clicked");

			return true;
		} catch (Exception ex) {
			logInfo("Failed to submit Task" + ex.getMessage());
			return false;
		}
	}

	public boolean saveTask() {
		try {
			Thread.sleep(4000);
			ControlActions_MobileApp.getTapAction(377, 1705);
			logInfo("save button has clicked");
			Thread.sleep(5000);
			return true;
		} catch (Exception ex) {
			logInfo("Failed to save Task" + ex.getMessage());
			return false;
		}
	}

	public boolean selectClearFiler() {
		try {

			ControlActions_MobileApp.waitForVisibility(filterBtn, 60);
			ControlActions_MobileApp.click(filterBtn);

			ControlActions_MobileApp.waitForVisibility(clearBtn, 60);
			ControlActions_MobileApp.click(clearBtn);

			return true;
		} catch (Exception e) {
			logError("Failed to Select AssignTo Filter - " + e.getMessage());
			return false;
		}
	}

	public boolean selectAssignToFiler(String assignTo) {
		try {

			ControlActions_MobileApp.waitForVisibility(filterBtn, 60);
			ControlActions_MobileApp.click(filterBtn);

			ControlActions_MobileApp.waitForVisibility(assignedToBtn, 60);
			ControlActions_MobileApp.click(assignedToBtn);

			WebElement formCheck = null;
			formCheck = ControlActions_MobileApp.perform_GetElementByXPath(TaskPageLocators.ASSIGNTO_FILTER, "WG",
					assignTo);
			ControlActions_MobileApp.waitForVisibility(formCheck, 60);
			ControlActions_MobileApp.click(formCheck);
			logInfo("Clicked on WorkGroup to be selected = " + assignTo);

			ControlActions_MobileApp.waitForVisibility(assignedToBtn, 60);
			ControlActions_MobileApp.click(assignedToBtn);

			ControlActions_MobileApp.waitForVisibility(CalFilterBtn, 60);
			ControlActions_MobileApp.click(CalFilterBtn);
			return true;
		} catch (Exception e) {
			logError("Failed to Select AssignTo Filter - " + e.getMessage());
			return false;
		}
	}

	public boolean selectStatusFiler(String status) {

		try {

			ControlActions_MobileApp.waitForVisibility(filterBtn, 50);
			ControlActions_MobileApp.click(filterBtn);

			ControlActions_MobileApp.waitForVisibility(statusBtn1, 50);
			ControlActions_MobileApp.click(statusBtn1);

			WebElement formCheck = null;
			formCheck = ControlActions_MobileApp.perform_GetElementByXPath(TaskPageLocators.STATUS_FILTER, "STATUS",
					status);
			ControlActions_MobileApp.waitForVisibility(formCheck, 50);
			ControlActions_MobileApp.click(formCheck);
			logInfo("Clicked on status to be selected = " + status);

			ControlActions_MobileApp.waitForVisibility(CalFilterBtn, 60);
			ControlActions_MobileApp.click(CalFilterBtn);
			return true;
		} catch (Exception e) {
			logError("Failed to Select AssignTo Filter - " + e.getMessage());
			return false;
		}
	}

	public boolean selectDueByFiler(String dueDate) {

		try {

			ControlActions_MobileApp.waitForVisibility(filterBtn, 60);
			ControlActions_MobileApp.click(filterBtn);

			ControlActions_MobileApp.waitForVisibility(dueByBtn, 50);
			ControlActions_MobileApp.click(dueByBtn);

			WebElement formCheck = null;
			String currentMonth = currentMonthTxt.getText();
			logInfo("Current Mnth Selected = " + currentMonth);

			List<String> currentMonthPart = Arrays.asList(currentMonth.split(" "));
			logInfo("Current Month = " + currentMonthPart.get(0) + "Year = " + currentMonthPart.get(1));
			List<String> dateParts = Arrays.asList(dueDate.split(" "));
			do {

				logInfo("Due date to be selected = " + dateParts.get(1) + "MOnth = " + dateParts.get(0));
				if (dateParts.get(0).equals(currentMonthPart.get(0))) {
					formCheck = ControlActions_MobileApp.perform_GetElementByXPath(TaskPageLocators.DAY_TOBE_BTN, "DAY",
							dateParts.get(1));
					ControlActions_MobileApp.waitForVisibility(formCheck, 50);
					ControlActions_MobileApp.click(formCheck);

				} else {
					ControlActions_MobileApp.waitForVisibility(nxtMonthBtn, 60);
					ControlActions_MobileApp.click(nxtMonthBtn);
				}
			} while (!(dateParts.get(0).equals(currentMonthPart.get(0))));

			ControlActions_MobileApp.waitForVisibility(CalFilterBtn, 50);
			ControlActions_MobileApp.click(CalFilterBtn);
			return true;
		} catch (Exception e) {
			logError("Failed to Select AssignTo Filter - " + e.getMessage());
			return false;
		}
	}

	public boolean verifyTaskFilterResultGrid(String verificationType, String WGname, String Status, String dueBy) {
		boolean isVerified = true;
		try {
			switch (verificationType) {
			case "WorkGroup":
				isVerified = verifyAssignToFilterResult(WGname);
				break;
			case "Status":
				isVerified = verifyStatusFilterResult(Status);
				break;
			case "dueBy":
				isVerified = verifydueByFilterResult(dueBy);
				break;
			}
			return isVerified;
		} catch (Exception e) {
			logError("Failed to Select AssignTo Filter - " + e.getMessage());
			return isVerified;
		}
	}

	public boolean verifyAssignToFilterResult(String WGName) {
		Boolean isPresent = false;
		try {

			int count = groupNameGrid.size();
			for (int i = 0; i < count; i++) {
				String wgName = groupNameGrid.get(i).getText();
				logInfo("Work Group Name = " + wgName);
				logInfo("Expected Work Group Name = " + WGName);
				if (wgName.trim().equals(WGName.trim())) {
					logInfo("Work Group Name Matched = " + WGName);
					isPresent = true;

				} else {
					isPresent = false;
				}
			}

			return isPresent;
		} catch (Exception e) {
			logError("Failed to Verify AssignTo Filter - " + e.getMessage());
			return isPresent;
		}
	}

	public boolean verifyStatusFilterResult(String status) {
		Boolean isPresent = false;
		try {

			int count = dueDateGrid.size();
			logInfo("Grid Count = " + count);
			for (int i = 0; i < count; i++) {
				String dueDate = dueDateGrid.get(i).getText();

				List<String> dateParts = Arrays.asList(dueDate.split("Due:"));
				logInfo("Due Date = " + dateParts.get(1));
				DateFormat format = new SimpleDateFormat("MMM dd, yyyy");

				Date date = format.parse(dateParts.get(1).trim());

				logInfo("Date to be compared = " + date);

				if (status.equalsIgnoreCase("Past Due")) {

					if (ControlActions_MobileApp.compareTwoDate("MMM dd, yyyy hh:mm a", date)) {
						isPresent = true;
						logInfo("Filtered date is less than Current Date");
					} else {
						isPresent = false;
						logInfo("Filtered date is Greater than Current Date");
					}
				} else if (status.equalsIgnoreCase("Due Later")) {
					if (ControlActions_MobileApp.compareTwoDate("MMM dd, yyyy hh:mm a", date)) {
						isPresent = false;
						logInfo("Filtered date is less than Current Date");
					} else {
						isPresent = true;
						logInfo("Filtered date is Greater than Current Date");

					}
				} else if (status.equalsIgnoreCase("Due Today")) {
					if (ControlActions_MobileApp.checkDateEquality("MMM dd, yyyy hh:mm a", date)) {
						isPresent = false;
						logInfo("Filtered date is equal to Current Date");
					} else {
						isPresent = true;
						logInfo("Filtered date and Current Date are not equal");

					}
				}

			}

			return isPresent;
		} catch (Exception e) {
			logError("Failed to Verify AssignTo Filter - " + e.getMessage());
			return isPresent;
		}
	}

	public boolean verifydueByFilterResult(String dueBy) {
		Boolean isPresent = false;
		try {

			int count = dueDateGrid.size();
			for (int i = 0; i < count; i++) {
				String dueDate = dueDateGrid.get(i).getText();
				logInfo("Due By = " + dueDate);
				List<String> dateParts = Arrays.asList(dueDate.split("Due:"));
				logInfo("Due Date = " + dateParts.get(1));
				SimpleDateFormat format = new SimpleDateFormat("MMM dd, yyyy");
				Date date = format.parse(dateParts.get(1).trim());

				logInfo("Date to be compared = " + date);

				if (ControlActions_MobileApp.compareTwoDate("MMM dd, yyyy ", date)
						|| ControlActions_MobileApp.compareTwoDate("MMM dd. yyyy", date)) {
					isPresent = true;
					logInfo("Filtered date is less than or Equal to Current Date");
				} else {
					isPresent = false;
					logInfo("Filtered date is greater than Current Date");
				}
			}

			return isPresent;
		} catch (Exception e) {
			logError("Failed to Verify Duee By Filter - " + e.getMessage());
			return isPresent;
		}
	}

	public boolean verifyDataLimitFilterResult(String dueBy) {
		Boolean isPresent = false;
		String dueDate = null;
		List<String> PreviousDateSet = new ArrayList<String>();
		List<String> CurrentDateSet = new ArrayList<String>();
		try {
			do {
				PreviousDateSet.clear();
				for (String data : CurrentDateSet) {
					PreviousDateSet.add(data);
				}
				logInfo("Previous Date set= " + PreviousDateSet + "Due Date Set" + CurrentDateSet);
				ControlActions_MobileApp.swipe(400, 700, 400, 100);
				Thread.sleep(5000);
				int count = dueDateGrid.size();
				logInfo("Data Grid Count = " + count);
				CurrentDateSet.clear();
				for (int i = 0; i < count; i++) {

					dueDate = dueDateGrid.get(i).getText();

					CurrentDateSet.add(dueDate);
					List<String> dateParts = Arrays.asList(dueDate.split("Due:"));
					SimpleDateFormat format = new SimpleDateFormat("MMM dd");
					Date date = format.parse(dateParts.get(1).trim());

					logInfo("Date to be compared = " + date);

					SimpleDateFormat sdf = new SimpleDateFormat("MMM dd");

					Date dueByDate = sdf.parse(dueBy);
					if (date.after(dueByDate)) {
						isPresent = true;
						logInfo("Filtered task due date is greater than Due By Date");
					} else {
						isPresent = false;
						logInfo("Filtered task due date is less than Due By Date");
					}
				}

				logInfo("Previous Date set= " + PreviousDateSet + "Due Date Set" + CurrentDateSet);
			} while (!(PreviousDateSet.equals(CurrentDateSet)));
			return isPresent;
		} catch (Exception e) {
			logError("Failed to Verify Data Limit Functionality - " + e.getMessage());
			return isPresent;
		}
	}

	public boolean verifyDurationDropdown() {
		boolean setVal = false;
		try {

			if (SettingTitle.getText().equalsIgnoreCase("Settings") && offlineModeText.isDisplayed()
					&& limiDataText.isDisplayed() && limitDurText.isDisplayed() && selectDurationText.isDisplayed()
					&& enableLogsText.isDisplayed() && clearSubText.isDisplayed() && offlineToggleBtn.isEnabled()
					&& limitDataToggleBtn.isEnabled() && enableLogToggleBtn.isEnabled() && clearSubBtn.isEnabled()) {

				ControlActions_MobileApp.WaitforelementToBeClickable(selectDurationAngle);
				selectDurationAngle.click();

				ArrayList<String> Actual = new ArrayList<>();
				ArrayList<String> Expected = new ArrayList<>();
				Expected.add(0, "3 DAYS");
				Expected.add(1, "7 DAYS");
				Expected.add(2, "14 DAYS");
				Expected.add(3, "21 DAYS");

				ArrayList<MobileElement> items = new ArrayList<>(appiumDriver.findElements(By.id("limitdataitem")));
				for (int i = 0; i < items.size(); i++) {
					Actual.add(items.get(i).getText());
					if (items.get(i).getText().equalsIgnoreCase("3 DAYS")
							|| items.get(i).getText().equalsIgnoreCase("14 DAYS")) {
						items.get(i).click();
						ControlActions_MobileApp.WaitforelementToBeClickable(selectDurationAngle);
						selectDurationAngle.click();

					}

				}

				if (Actual.equals(Expected)) {
					setVal = true;
					logInfo("Duration dropdown values has been verified");
				}

			}

			return setVal;
		}

		catch (Exception e) {

			return setVal;
		}
	}

}
