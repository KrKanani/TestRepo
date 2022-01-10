package com.project.safetychain.mobileapp.pageobjects;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.utilities.CommonMethods;
import com.project.utilities.ControlActions_MobileApp;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class REG_SavedPage extends TestBase {

	public class HomePageLocators {

		public final String formName = null;
		public final String FORM_NAME = null;

		public void main(String[] args) {
			// TODO Auto-generated method stub

		}

	}

	WebDriver driver;
	AppiumDriver<MobileElement> appiumDriver;
	WebDriverWait wait;
	ControlActions_MobileApp mobControlActions;
	CommonMethods commonmethods;
	HomePage homePage;

	public REG_SavedPage(AppiumDriver<MobileElement> driver) {
		this.appiumDriver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		mobControlActions = new ControlActions_MobileApp(this.appiumDriver);
		commonmethods = new CommonMethods(this.appiumDriver);
		homePage = new HomePage(appiumDriver);

	}

	@AndroidFindBy(id = SavedPageLocators.menuBtn)
	public WebElement MenuBtn;

	@AndroidFindBy(id = SavedPageLocators.inboxBtn)
	public WebElement InboxBtn;

	@AndroidFindBy(xpath = SavedPageLocators.Form_Name)
	public WebElement formNameSearched;

	@AndroidFindBy(id = SavedPageLocators.formName)
	public WebElement formNameSe;

	@AndroidFindBy(id = SavedPageLocators.Search_Field)
	public WebElement searchField;

	@AndroidFindBy(id = SavedPageLocators.Search_formName_Keyboard)
	public WebElement softkeyboardlayout;

	@AndroidFindBy(xpath = SavedPageLocators.Resource_Search_Field)
	public WebElement resourceSearchField;

	@AndroidFindBy(xpath = SavedPageLocators.Resource_Name)
	public WebElement resourceName;

	@AndroidFindBy(id = SavedPageLocators.NUMERIC_FIELD)
	public WebElement NumericTxt;

	@AndroidFindBy(id = SavedPageLocators.PARAGRAPH_FIELD)
	public WebElement paragraphTxt;
	@AndroidFindBy(id = SavedPageLocators.FREETEXT_FIELD)
	public WebElement freeText;
	@AndroidFindBy(id = SavedPageLocators.DATE_FIELD)
	public WebElement datePicker;
	@AndroidFindBy(id = SavedPageLocators.DATETIME_FIELD)
	public WebElement dateTimePicker;

	@AndroidFindBy(id = SavedPageLocators.Form_Submit)
	public WebElement formSubmit;

	@AndroidFindBy(id = SavedPageLocators.SUBMIT_REPEAT_BTN)
	public WebElement SubmitRepeatBtn;

	@AndroidFindBy(id = SavedPageLocators.Form_Save)
	public WebElement formSave;

	@AndroidFindBy(id = SavedPageLocators.Main_menu)
	public WebElement mainMenu;

	@AndroidFindBy(xpath = SavedPageLocators.Submissions_Submenu)
	public WebElement submissionsSubMenu;

	@AndroidFindBy(xpath = SavedPageLocators.Inbox_Submenu)
	public WebElement inboxSubMenu;

	@AndroidFindBy(xpath = SavedPageLocators.SAVE_SUBMENU)
	public WebElement saveSubMenu;

	@AndroidFindBy(xpath = SavedPageLocators.Favourites_Submenu)
	public WebElement favouritesSubMenu;

	@AndroidFindBy(xpath = SavedPageLocators.Forms_Submenu)
	public WebElement formsSubMenu;

	@AndroidFindBy(xpath = SavedPageLocators.Settings_Submenu)
	public WebElement settingsSubMenu;

	@AndroidFindBy(xpath = SavedPageLocators.Submissions_Form_Status)
	public WebElement submissionsFormStatus;

	@AndroidFindBy(xpath = SavedPageLocators.Submissions_Form_Status_Offline)
	public WebElement submissionsFormStatusOffline;

	@AndroidFindBy(id = SavedPageLocators.HEADERDATE)
	public WebElement headerDate;

	@AndroidFindBy(id = SavedPageLocators.DONE_BTN)
	public WebElement doneBtn;

	@AndroidFindBy(id = SavedPageLocators.SAVED_TIMESTAMP1)
	public WebElement savedTimestamp;

	@AndroidFindBy(id = SavedPageLocators.OFFLINETOGGLE)
	public WebElement offlineToggle;

	@AndroidFindBy(id = SavedPageLocators.SETTINGS_BCKBTN)
	public WebElement settingsBckBtn;

	@AndroidFindBy(id = SavedPageLocators.RESUBMIT_BTN)
	public WebElement resubmitBtn;

	@AndroidFindBy(id = SavedPageLocators.RESUBMIT_POPUP_BTN)
	public WebElement resubmitPopupBtn;

	@AndroidFindBy(id = SavedPageLocators.CLEARALL_BTN)
	public WebElement clearAllBtn;

	@AndroidFindBy(id = SavedPageLocators.FORM_STATUS)
	public WebElement formStatusBtn;

	@AndroidFindBy(id = SavedPageLocators.FORM_VISIBLE)
	public WebElement formVisibilityTxt;

	@AndroidFindBy(id = SavedPageLocators.DETAIL_LAYOUT)
	public WebElement detailLayout;

	@AndroidFindBy(id = SavedPageLocators.RESUBMIT_LONGPRESS_BTN)
	public WebElement ResubmitLongPressBtn;

	@AndroidFindBy(id = SavedPageLocators.PIN_BTN)
	public WebElement pinBtn;

	@AndroidFindBy(id = SavedPageLocators.PIN_BTN_Save)
	public WebElement pinBtnSave;

	@AndroidFindBy(id = SavedPageLocators.PIN_BTN_Complaint)
	public WebElement pinCompliantBtn;

	@AndroidFindBy(id = SavedPageLocators.PIN_BTN_NonComplaint)
	public WebElement pinNonCompliantBtn;

	@AndroidFindBy(id = SavedPageLocators.PIN_USER)
	public WebElement pinUser;

	@AndroidFindBy(id = SavedPageLocators.PIN)
	public WebElement pin;

	@AndroidFindBy(id = SavedPageLocators.PIN_COMMENTS)
	public WebElement pinComments;

	@AndroidFindBy(id = SavedPageLocators.PIN_OBSERVERDBY)
	public WebElement verifyPinObservedBy;

	@AndroidFindBy(id = SavedPageLocators.PIN_COMPLIANT)
	public WebElement verifyPinCompliant;

	@AndroidFindBy(id = SavedPageLocators.PIN_COMMENT)
	public WebElement verifyPinComment;

	@AndroidFindBy(id = SavedPageLocators.FL_COMMENTS)
	public WebElement flComments;

	@AndroidFindBy(id = SavedPageLocators.FL_CORRECTION_TXT)
	public WebElement flCorrectiontxt;

	@AndroidFindBy(id = SavedPageLocators.FL_RESOLVED_YES)
	public WebElement flResolvedYes;

	@AndroidFindBy(id = SavedPageLocators.FL_RESOLVED_NO)
	public WebElement flResolvedNo;

	@AndroidFindBy(xpath = SavedPageLocators.CLOSE_ALL_APPS)
	public WebElement closeAllApps;

	@AndroidFindBy(id = SavedPageLocators.NUMERIC_FIELD)
	public List<WebElement> ListNumericTxt;

	@AndroidFindBy(id = SavedPageLocators.SORT_BTN)
	public WebElement sortBtn;

	@AndroidFindBy(id = SavedPageLocators.NETWORK_MODE_ICON)
	public WebElement networkModeIcn;

	@AndroidFindBy(id = SavedPageLocators.CLOSE_SEARCH)
	public WebElement closeSearch;

	@FindBy(id = TaskPageLocators.DUEDATE_GRID)
	public List<WebElement> dueDateGrid;

	public void actionEnter() {
		// TODO Auto-generated method stub

	}

	public boolean SearchForm(String Formname) {
		try {
			ControlActions_MobileApp.waitForVisibility(searchField, 40);
			// ControlActions_MobileApp.sendKeys(searchField,Formname);
			ControlActions_MobileApp.ClearText(searchField);
			ControlActions_MobileApp.actionEnterText(searchField, Formname);
			// ControlActions_MobileApp.SetValue_By_JavaScriptexecutor(searchField,
			// Formname);

			return true;
		} catch (Exception ex) {
			logInfo("Failed to Search" + ex.getMessage());
			return false;
		}
	}

	public boolean offlineButtonEnabled(boolean isOffline) {
		try {
			if (isOffline) {
				// networkModeIcn
				if (!(ControlActions_MobileApp.isElementDisplayed(networkModeIcn))) {
					ControlActions_MobileApp.waitForVisibility(offlineToggle, 80);
					ControlActions_MobileApp.click(offlineToggle);
					logInfo("Offline mode has Activated");
				}
			}

			return true;
		} catch (Exception ex) {
			logInfo("Failed To activate Offline Mode" + ex.getMessage());
			return false;
		}
	}

	public boolean ClickForm(String formName) {
		try {
			Thread.sleep(5000);
			WebElement formCheck = null;

			ControlActions_MobileApp.waitForVisibility(formNameSe, 50);
			ControlActions_MobileApp.click(formNameSe);
			formCheck = ControlActions_MobileApp.perform_GetElementByXPath(SavedPageLocators.Form_Name,
					"FormNameLocator", formName);
			ControlActions_MobileApp.waitForVisibility(formCheck, 50);
			ControlActions_MobileApp.click(formCheck);
			logInfo("Clicked on 'formName -> Location Menu'");

			return true;
		} catch (Exception e) {
			logError("Failed to click - 'formName -> Forms list' - " + e.getMessage());
			return false;
		}
	}

	public boolean SearchResource(String resourceNm) {
		try {
			WebElement resource = null;
			ControlActions_MobileApp.waitForVisibility(resourceSearchField, 80);

			ControlActions_MobileApp.ClearText(resourceSearchField);
			ControlActions_MobileApp.actionEnterText(resourceSearchField, resourceNm);
			logInfo("Resource Name has Entered");
			Thread.sleep(9000);

			resource = ControlActions_MobileApp.perform_GetElementByXPath(SavedPageLocators.Resource_Name,
					"ResourceName", resourceNm);

			ControlActions_MobileApp.waitForVisibility(resource, 80);
			ControlActions_MobileApp.click(resource);
			logInfo("Resource has Selected");

			return true;
		} catch (Exception ex) {
			logInfo("Failed to Search resource" + ex.getMessage());
			return false;
		}
	}

	public boolean enterFieldValue(WebElement FieldType, String FieldValue) {
		try {

			ControlActions_MobileApp.waitForVisibility(FieldType, 80);
			ControlActions_MobileApp.ClearText(FieldType);
			ControlActions_MobileApp.actionEnterText(FieldType, FieldValue);
			ControlActions_MobileApp.performTabAction();
			logInfo("Field Value has Entered");

			return true;
		} catch (Exception ex) {
			logInfo("Failed to Search resource" + ex.getMessage());
			return false;
		}
	}

	public boolean enterFieldValueforAttachment(WebElement FieldType, String FieldValue) {
		try {

			ControlActions_MobileApp.waitForVisibility(FieldType, 80);
			ControlActions_MobileApp.ClearText(FieldType);
			ControlActions_MobileApp.actionEnterText(FieldType, FieldValue);

			logInfo("Field Value has Entered");

			return true;
		} catch (Exception ex) {
			logInfo("Failed to Search resource" + ex.getMessage());
			return false;
		}
	}

	public void selectPreDefinedCorrectionValues(String FieldType, String FieldValue)
			throws InterruptedException, ElementNotVisibleException {

		String fieldValue = null;
		WebElement Field = null;
		Thread.sleep(3000);
		Boolean isPresent = false;
		fieldValue = ControlActions_MobileApp.perform_GetElementByXPath2(SavedPageLocators.FL_CORRECTION_INBUILT,
				"CORRECTIONVALUE", FieldValue);
		do {
			try {
				Field = appiumDriver.findElement(By.xpath(fieldValue));
				Thread.sleep(3000);

				ControlActions_MobileApp.click(Field);
				isPresent = true;

				logInfo("Field Value has Selected");

			} catch (Exception ex) {

				ControlActions_MobileApp.swipe(400, 700, 400, 100);
				Thread.sleep(1000);

			}
		} while (!isPresent);
	}

	public boolean selectOneOrMultipleFieldValues2(String FieldType, String FieldValue)
			throws InterruptedException, ElementNotVisibleException {

		String fieldValue = null;
		WebElement Field = null;
		Thread.sleep(3000);
		Boolean isPresent = false;

		ControlActions_MobileApp.ScrollIntoView(FieldValue);
		fieldValue = ControlActions_MobileApp.perform_GetElementByXPath2(SavedPageLocators.SELECTONE_FIELD, "VALUE",
				FieldValue);

		try {
			Field = appiumDriver.findElement(By.xpath(fieldValue));
			// Thread.sleep(3000);

			ControlActions_MobileApp.click(Field);
			isPresent = true;
			logInfo("Field Value has Selected");

			return isPresent;

		} catch (Exception ex) {
			logInfo("Failed to Select Date" + ex.getMessage());
			return isPresent;
		}
	}

	public boolean selectOneOrMultipleFieldValues(String FieldType, String FieldValue)
			throws InterruptedException, ElementNotVisibleException {
		try {

			String fieldValue = null;
			WebElement Field = null;
			Thread.sleep(3000);
			Boolean isPresent = false;
			fieldValue = ControlActions_MobileApp.perform_GetElementByXPath2(SavedPageLocators.SELECTONE_FIELD, "VALUE",
					FieldValue);
			do {
				try {
					Field = appiumDriver.findElement(By.xpath(fieldValue));
					Thread.sleep(3000);

					ControlActions_MobileApp.click(Field);
					isPresent = true;
					logInfo("Field Value has Selected");

				} catch (Exception ex) {

					ControlActions_MobileApp.swipe(400, 700, 400, 100);
					Thread.sleep(1000);

				}
			} while (!isPresent);
			return true;
		} catch (Exception ex) {
			logInfo("Failed to Select Date" + ex.getMessage());
			return false;
		}
	}

	public boolean submitForm() {
		try {
			Thread.sleep(6000);
			ControlActions_MobileApp.waitForVisibility(formSubmit, 80);
			formSubmit.click();
			logInfo("Submit button has clicked");
			Thread.sleep(5000);

			return true;
		} catch (Exception ex) {
			logInfo("Failed to submit form" + ex.getMessage());
			return false;
		}
	}

	public boolean clickSubmitRepeatForm() {
		try {
			Thread.sleep(6000);
			ControlActions_MobileApp.waitForVisibility(SubmitRepeatBtn, 10);
			SubmitRepeatBtn.click();
			logInfo("Submit and Repeat button has clicked");

			return true;
		} catch (Exception ex) {
			logInfo("Failed to submit form" + ex.getMessage());
			return false;
		}
	}

	public boolean selectResolvedField(WebElement Resolved) {
		try {
			ControlActions_MobileApp.waitForVisibility(Resolved, 80);
			ControlActions_MobileApp.click(Resolved);
			return true;
		} catch (Exception ex) {
			logInfo("Failed to Select Resolved Field" + ex.getMessage());
			return false;
		}

	}

	public boolean saveForm() {
		try {
			ControlActions_MobileApp.waitForVisibility(formSave, 80);
			ControlActions_MobileApp.click(formSave);

			return true;
		} catch (Exception ex) {
			logInfo("Failed to Save Form" + ex.getMessage());
			return false;
		}

	}

	public boolean clickFieldType(WebElement DatePicker, String date) throws ParseException {
		try {
			ControlActions_MobileApp.click(DatePicker);

			select_Date_from_Calendar(date);
			return true;
		} catch (Exception ex) {
			logInfo("Failed to Select Field Type" + ex.getMessage());
			return false;
		}
	}

	public boolean ClickSubMenu(WebElement subMenu) {
		try {
			ControlActions_MobileApp.waitForVisibility(mainMenu, 80);
			ControlActions_MobileApp.click(mainMenu);
			ControlActions_MobileApp.waitForVisibility(subMenu, 80);
			ControlActions_MobileApp.click(subMenu);

			return true;
		} catch (Exception ex) {
			logInfo("Failed to Select subMenu" + ex.getMessage());
			return false;
		}
	}

	public boolean checkFormStatusFromSubmissionsSubMenu(String FormName, String FormStatus) {
		try {

			WebElement resource = null;
			Thread.sleep(8000);

			resource = ControlActions_MobileApp.perform_GetElementByXPath(SavedPageLocators.Form_Name,
					"FormNameLocator", FormName);
			ControlActions_MobileApp.waitForVisibility(resource, 80);
			logInfo("Form is Present");

			WebElement ActualformStatus = ControlActions_MobileApp
					.perform_GetElementByXPath(SavedPageLocators.Submissions_Form_Status, "STATUS", FormStatus);
			ControlActions_MobileApp.waitForVisibility(ActualformStatus, 80);
			logInfo("Form is Present");
			if (ControlActions_MobileApp.isElementDisplayed(ActualformStatus)) {
				if (ActualformStatus.getText().equals(FormStatus)) {
					logInfo("Form status has verified");
					return true;
				} else {
					Assert.fail("Form status verification failed");
					return false;
				}

			} else {
				return false;
			}

		} catch (Exception ex) {
			logInfo("Failed to Search resource" + ex.getMessage());
			return false;
		}
	}

	public boolean ClickInbox() {
		ControlActions_MobileApp.waitForVisibility(MenuBtn, 80);
		ControlActions_MobileApp.click(MenuBtn);
		ControlActions_MobileApp.click(InboxBtn);

		return true;
	}

	public boolean select_Date_from_Calendar(String taskdate) throws ParseException {
		String pickdate = ControlActions_MobileApp.formatdate(taskdate, "d-MMMM yyyy");
		String datespliter[] = pickdate.split("-");
		String day = datespliter[0];
		String month_year = datespliter[1];

		ControlActions_MobileApp.waitForVisibility(headerDate, 80);
		String calandermonth = null;

		WebElement firstDay = ControlActions_MobileApp.perform_GetElementByXPath(SavedPageLocators.DATE_SELECTED,
				"DATE", "1");

		String Date = firstDay.getAttribute("content-desc");

		String datespliter1[] = Date.split(" ");
		calandermonth = datespliter1[1] + " " + datespliter1[2];

		try {
			do {
				threadsleep(4000);

				if (calandermonth.equals(month_year)) {

					WebElement selectday = ControlActions_MobileApp
							.perform_GetElementByXPath(SavedPageLocators.DATE_SELECTED, "DATE", day);

					ControlActions_MobileApp.click(selectday);
					ControlActions_MobileApp.click(doneBtn);
					break;
				} else {

					ControlActions_MobileApp.getTapAction(551, 494);
				}

			} while (calandermonth.equals(month_year) == false);

			logInfo("Selected Date from Calender -- " + taskdate);
			return true;

		} catch (Exception e) {
			logError("Unable to select date" + e.getMessage());
			return false;
		}

	}

	public List<String> TodaysDateinFormat(String timeFormat) {
		String ExpectedTimestamp1 = null;
		String ExpectedTimestamp2 = null;
		List<String> ExpectedTimestamp = new ArrayList<String>();

		try {
			ExpectedTimestamp1 = ControlActions_MobileApp.formatdateAndTime(timeFormat);

			logInfo("Expected Timestamp in 24 Hr Format = " + ExpectedTimestamp1);
			ExpectedTimestamp.add(ExpectedTimestamp1);

			String datespliter[] = ExpectedTimestamp1.split(" ");
			String Date = datespliter[0];
			String Time = datespliter[1];
			String TimeZone = datespliter[3];

			String TimeIn12Hr = ControlActions_MobileApp.convert24HrFormatInto12HrTime(Time);

			ExpectedTimestamp2 = "Saved on " + Date + " at " + TimeIn12Hr + " " + TimeZone;
			logInfo(" ExpectedTimestamp in 12 Hr Format=" + ExpectedTimestamp2);
			ExpectedTimestamp.add(ExpectedTimestamp2);

			return ExpectedTimestamp;
		} catch (Exception ex) {
			logInfo("Failed to Search Form on Saved Form Tab" + ex.getMessage());
			return ExpectedTimestamp;
		}
	}

	public boolean checkFormStatusFromSavedSubMenu(String FormName, String ExpectedDate) throws InterruptedException {
		WebElement resource = null;
		WebElement Field = null;
		String isPresent = null;
		Boolean FormPresent = false;
		Thread.sleep(3000);

		resource = ControlActions_MobileApp.perform_GetElementByXPath(SavedPageLocators.Form_Name, "FormNameLocator",
				FormName);
		ControlActions_MobileApp.waitForVisibility(resource, 80);
		logInfo("Form is Present");
		// do {
		try {

			isPresent = ControlActions_MobileApp.perform_GetElementByXPath2(SavedPageLocators.SAVED_TIMESTAMP,
					"TIMESTAMP", ExpectedDate);

			Field = appiumDriver.findElement(By.xpath(isPresent));
			String actualTimestamp = Field.getText();
			logInfo("Actual Timestamp = " + actualTimestamp);

			if (ControlActions_MobileApp.isElementDisplayed(Field)) {
				logInfo("Timestamp has verified successfully");
				FormPresent = true;
			}

			return FormPresent;
		} catch (Exception ex) {

			return FormPresent;

		}
	}

	public boolean checkFormStatusFromSavedSubMenu2(String dueBy) {
		Boolean isPresent = false;
		String dueDate = null;
		// String PreviousDate = null;
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
					List<String> dateParts = Arrays.asList(dueDate.split(","));
					SimpleDateFormat format = new SimpleDateFormat("MMM dd");
					Date date = format.parse(dateParts.get(1).trim());

					logInfo("Date to be compared = " + date);

					SimpleDateFormat sdf = new SimpleDateFormat("MMM dd");

					Date dueByDate = sdf.parse(dueBy);
					if (date.equals(dueByDate)) {
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

	public boolean clickClearAll() {
		try {
			ControlActions_MobileApp.waitForVisibility(clearAllBtn, 60);
			ControlActions_MobileApp.click(clearAllBtn);
			logInfo("Clicked on clear All button");
			ControlActions_MobileApp.waitForVisibility(resubmitPopupBtn, 60);
			ControlActions_MobileApp.click(resubmitPopupBtn);
			logInfo("Clicked on popup OK button");

			return true;
		} catch (Exception e) {
			logInfo("Could not click on Clear All button" + e.getStackTrace());
			return false;
		}

	}

	public boolean clickPopupOkBtn() {
		try {

			ControlActions_MobileApp.waitForVisibility(resubmitPopupBtn, 60);
			ControlActions_MobileApp.click(resubmitPopupBtn);
			logInfo("Clicked on popup OK button");

			return true;
		} catch (Exception e) {
			logInfo("Could not click on popup OK button" + e.getStackTrace());
			return false;
		}

	}

	public boolean clickResubmitAll() {
		try {
			if (ControlActions_MobileApp.isElementDisplayed(resubmitBtn)) {
				ControlActions_MobileApp.click(resubmitBtn);
				logInfo("Clicked on resubmitBtn");
				ControlActions_MobileApp.waitForVisibility(resubmitPopupBtn, 80);
				ControlActions_MobileApp.click(resubmitPopupBtn);
				ControlActions_MobileApp.waitForVisibility(resubmitPopupBtn, 60);
				if (ControlActions_MobileApp.isElementDisplayed(resubmitPopupBtn)) {
					ControlActions_MobileApp.click(resubmitPopupBtn);
				}
				Thread.sleep(2000);
				if (ControlActions_MobileApp.isElementDisplayed(resubmitPopupBtn)) {
					ControlActions_MobileApp.click(resubmitPopupBtn);
				}
			} else {
				return true;
			}

			return true;
		} catch (Exception e) {
			logInfo("Could not click on Resubmit button" + e.getStackTrace());
			return false;
		}
	}

	public boolean checkFormVisibility(String Formname) {
		try {

			Boolean IsPresent = ControlActions_MobileApp.isElementDisplayed(formVisibilityTxt);
			if (IsPresent) {

				String FormText = formVisibilityTxt.getText();
				logInfo("Text on submission screen " + FormText);

				if (FormText.equals("No Submissions")) {
					logInfo("Form is not present on Submissions Screen");
					return true;
				} else {
					logInfo("Still Forms are present");
					return false;
				}
			}

			else {
				return false;
			}

		} catch (Exception ex) {
			logInfo("Failed to Search" + ex.getMessage());
			return false;
		}
	}

	public boolean checkFormVisibilityOnSubmissionScreen(String Formname) {
		try {

			Boolean IsPresent = ControlActions_MobileApp.isElementDisplayed(formVisibilityTxt);
			if (IsPresent) {

				String FormText = formVisibilityTxt.getText();
				logInfo("Text on submission screen " + FormText);

				if (FormText.equals("No Results Found")) {
					logInfo("Form is not present on Submissions Screen");
					return true;
				} else {
					logInfo("Still Forms are present");
					return false;
				}
			}

			else {
				// Assert.fail("Clear all functionality is not working");
				return false;
			}

		} catch (Exception ex) {
			logInfo("Failed to Search" + ex.getMessage());
			return false;
		}
	}

	public boolean clickResubmitAllByLongPress(String FormName) {
		try {

			WebElement resource;

			resource = ControlActions_MobileApp.perform_GetElementByXPath(SavedPageLocators.Form_Name,
					"FormNameLocator", FormName);

			ControlActions_MobileApp.waitForVisibility(resource, 80);// detailLayout
			logInfo("Form is Present");
			ControlActions_MobileApp.getLongPressAction(resource);
			logInfo("Clicked on detail Layout Page");
			if (formStatusBtn.isDisplayed()) {
				ControlActions_MobileApp.waitForVisibility(formStatusBtn, 80);
				ControlActions_MobileApp.click(formStatusBtn);
				logInfo("Clicked on resubmit button");
			}

			return true;
		} catch (Exception e) {
			logInfo("Could not click on Resubmit button" + e.getStackTrace());
			return false;
		}
	}

	public boolean clickFormFromSubmissionsScreen(String FormName) {
		Boolean isPresent = false;
		try {

			WebElement resource = null;
			Thread.sleep(8000);
			resource = ControlActions_MobileApp.perform_GetElementByXPath(SavedPageLocators.Form_Name,
					"FormNameLocator", FormName);
			ControlActions_MobileApp.waitForVisibility(resource, 60);
			if (ControlActions_MobileApp.isElementDisplayed(resource)) {
				logInfo("Form is Present");
				ControlActions_MobileApp.click(resource);
				isPresent = true;
			} else {
				isPresent = false;
			}
		} catch (Exception ex) {
			logInfo("Failed to Search" + ex.getMessage());
			return false;
		}
		return isPresent;
	}

	public boolean verifyPinDetailsFromSubmissionScreen(String Formname, String username, String tenant,
			boolean Compliant, String Comment) {
		Boolean isPresent = true;
		String isCompliant = "Non Compliant";
		try {
			ControlActions_MobileApp.waitForVisibility(pinBtn, 60);
			ControlActions_MobileApp.click(pinBtn);
			Boolean IsPresent = ControlActions_MobileApp.isElementDisplayed(verifyPinObservedBy);
			Boolean IsPresentCompliant = ControlActions_MobileApp.isElementDisplayed(verifyPinCompliant);
			Boolean IsPresentComment = ControlActions_MobileApp.isElementDisplayed(verifyPinComment);
			if (IsPresent && IsPresentCompliant && IsPresentComment) {

				String observerdBy = verifyPinObservedBy.getText();
				String compliant = verifyPinCompliant.getText();
				String comment = verifyPinComment.getText();
				logInfo("Pin Details Observed By = " + observerdBy + "  compliant = " + compliant + "  comment = "
						+ comment);
				if (Compliant) {
					isCompliant = "Compliant";
				}

				String expected_observerdBy = username;
				String expected_compliant = isCompliant;
				String expected_comment = Comment;

				logInfo("Expected Pin Details Observed By = " + expected_observerdBy + "  compliant = "
						+ expected_compliant + "  comment = " + expected_comment);

				if (observerdBy.contains(expected_observerdBy) && compliant.contains(expected_compliant)
						&& comment.contains(expected_comment)) {
					logInfo("Pin Details has been verified");
					isPresent = true;

				} else {
					logInfo("Couldnt verified Pin DEtails");
					isPresent = false;
				}
			} else {
				Assert.fail("Pin Popup doesn't contain Pin Details");
				isPresent = false;
			}

		} catch (Exception ex) {
			logInfo("Failed to Search" + ex.getMessage());
			return false;
		}
		return isPresent;
	}

	public boolean ModifyPin(AppiumDriver<MobileElement> appiumDriver) {
		try {
			ControlActions_MobileApp.waitForVisibility(pinBtn, 60);
			Thread.sleep(4000);
			Actions action = new Actions(appiumDriver);
			action.moveToElement(pinBtn).click().build().perform();

			return true;
		} catch (Exception ex) {
			logInfo("Failed to click on pin button" + ex.getMessage());
			return false;
		}

	}

	public boolean ModifyPinDetails(String User, String Pin, boolean iscompliant, String comment) {
		try {
			ControlActions_MobileApp.waitForVisibility(pinUser, 60);
			pinUser.sendKeys(User);
			ControlActions_MobileApp.waitForVisibility(pin, 60);
			pin.sendKeys(Pin);
			if (iscompliant) {
				pinCompliantBtn.click();
			} else {
				pinNonCompliantBtn.click();
			}
			pinComments.sendKeys(comment);

			return true;
		} catch (Exception ex) {
			logInfo("Failed to modify on pin" + ex.getMessage());
			return false;
		}

	}

	public boolean SavePin() {
		try {
			ControlActions_MobileApp.waitForVisibility(pinBtnSave, 1000);
			ControlActions_MobileApp.click(pinBtnSave);
			return true;
		} catch (Exception ex) {
			logInfo("Failed to save on pin" + ex.getMessage());
			return false;
		}
	}

	public boolean verifyCorrectionDetailsFromSubmissionScreen(String Comments, String Correction) {
		Boolean isPresent = true;
		try {

			Boolean IsPresentComments = ControlActions_MobileApp.isElementDisplayed(flComments);
			Boolean IsPresentCorrection = ControlActions_MobileApp.isElementDisplayed(flCorrectiontxt);

			if (IsPresentComments && IsPresentCorrection) {

				String comments = flComments.getText();
				String correction = flCorrectiontxt.getText();

				logInfo("Correction Details Comments = " + comments + " Correction = " + correction);

				if (comments.equals(Comments) && correction.equalsIgnoreCase(Correction)) {
					logInfo("Correction DEtails has been verified");
					isPresent = true;

				} else {
					logInfo("Couldnt verified Correction DEtails");
					isPresent = false;
				}
			} else {
				Assert.fail("Correction Details are not present");
				isPresent = false;
			}

		} catch (Exception ex) {
			logInfo("Failed to Search" + ex.getMessage());
			return false;
		}
		return isPresent;
	}

	/**
	 * This method is used to verify value for list of fields
	 * 
	 * @author hingorani_a
	 * @param fields
	 *            A list of Field name and their values
	 * @return boolean This returns true after verifying values for given fields
	 */
	public boolean verifyFieldValues(LinkedHashMap<String, String> fields) {
		try {
			for (Map.Entry<String, String> entry : fields.entrySet()) {
				String fieldName = entry.getKey();
				String fieldValues = entry.getValue();
				// fields.get("");

				if (!verifyFieldValues(fieldName, fieldValues)) {
					return false;
				}
				logInfo("Verified Field - " + fieldName + " and it's values - " + fieldValues);
			}
			return true;
		} catch (Exception e) {
			logError("Failed to verify values for Record - " + e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to verify value for a particular field
	 * 
	 * @author hingorani_a
	 * @param fieldName
	 *            Name of the field to be set
	 * @param fieldValue
	 *            List of values verified for a field
	 * @return boolean This returns true after verifying value for a field
	 */
	public boolean verifyFieldValues(String fieldName, String fieldValue) {
		// List<String> getFieldVals = new ArrayList<String>();
		// String fieldVal = null;
		Boolean isverified = false;
		String fieldTxt = null;
		try {

			switch (fieldName) {
			case "Numeric":

				fieldTxt = ControlActions_MobileApp.perform_GetElementByXPath2(REG_SubmissionPageLocators.INPUT_FIELD,
						"FIELDNAME", fieldName);
				isverified = VerifyFieldValuesUsingScroll(fieldValue, fieldTxt);
				break;

			case "Numeric2":

				fieldTxt = ControlActions_MobileApp.perform_GetElementByXPath2(REG_SubmissionPageLocators.INPUT_FIELD,
						"FIELDNAME", fieldName);
				isverified = VerifyFieldValuesUsingScroll(fieldValue, fieldTxt);
				break;

			case "Numeric3":

				fieldTxt = ControlActions_MobileApp.perform_GetElementByXPath2(REG_SubmissionPageLocators.INPUT_FIELD,
						"FIELDNAME", fieldName);
				isverified = VerifyFieldValuesUsingScroll(fieldValue, fieldTxt);
				break;
			case "Numeric4":

				fieldTxt = ControlActions_MobileApp.perform_GetElementByXPath2(REG_SubmissionPageLocators.INPUT_FIELD,
						"FIELDNAME", fieldName);
				isverified = VerifyFieldValuesUsingScroll(fieldValue, fieldTxt);
				break;

			case "Numeric5":

				fieldTxt = ControlActions_MobileApp.perform_GetElementByXPath2(REG_SubmissionPageLocators.INPUT_FIELD,
						"FIELDNAME", fieldName);
				isverified = VerifyFieldValuesUsingScroll(fieldValue, fieldTxt);
				break;

			case "Date1":
				fieldTxt = ControlActions_MobileApp.perform_GetElementByXPath2(REG_SubmissionPageLocators.INPUT_FIELD,
						"FIELDNAME", fieldName);
				isverified = VerifyFieldValuesUsingScroll(fieldValue, fieldTxt);
				break;
			case "Date2":
				fieldTxt = ControlActions_MobileApp.perform_GetElementByXPath2(REG_SubmissionPageLocators.INPUT_FIELD,
						"FIELDNAME", fieldName);
				isverified = VerifyFieldValuesUsingScroll(fieldValue, fieldTxt);
				break;

			case "DateTime1":
				fieldTxt = ControlActions_MobileApp.perform_GetElementByXPath2(REG_SubmissionPageLocators.INPUT_FIELD,
						"FIELDNAME", fieldName);
				isverified = VerifyFieldValuesUsingScroll(fieldValue, fieldTxt);
				break;

			case "DateTime2":
				fieldTxt = ControlActions_MobileApp.perform_GetElementByXPath2(REG_SubmissionPageLocators.INPUT_FIELD,
						"FIELDNAME", fieldName);
				isverified = VerifyFieldValuesUsingScroll(fieldValue, fieldTxt);
				break;

			case "ParaGraph1":

				fieldTxt = ControlActions_MobileApp.perform_GetElementByXPath2(REG_SubmissionPageLocators.INPUT_FIELD,
						"FIELDNAME", fieldName);
				logInfo("Paragraph element =" + fieldTxt);
				isverified = VerifyFieldValuesUsingScroll(fieldValue, fieldTxt);
				break;

			case "SingleLineText1":

				fieldTxt = ControlActions_MobileApp.perform_GetElementByXPath2(REG_SubmissionPageLocators.INPUT_FIELD,
						"FIELDNAME", fieldName);
				logInfo("SingleLineText1 element =" + fieldTxt);
				isverified = VerifyFieldValuesUsingScroll(fieldValue, fieldTxt);
				break;

			case "AGG1-SUM":

				fieldTxt = ControlActions_MobileApp.perform_GetElementByXPath2(REG_SubmissionPageLocators.AGG_FIELD,
						"FIELDNAME", fieldName);
				isverified = VerifyFieldValuesUsingScroll(fieldValue, fieldTxt);
				break;

			case "AGG1-CountRange":

				fieldTxt = ControlActions_MobileApp.perform_GetElementByXPath2(REG_SubmissionPageLocators.AGG_FIELD,
						"FIELDNAME", fieldName);
				isverified = VerifyFieldValuesUsingScroll(fieldValue, fieldTxt);
				break;

			case "SelectOne":

				fieldTxt = ControlActions_MobileApp.perform_GetElementByXPath2(REG_SubmissionPageLocators.SELECT_FIELD,
						"FIELDNAME", fieldName);

				fieldTxt = ControlActions_MobileApp.perform_GetElementByXPath2(fieldTxt, "FIELDVALUE", fieldValue);

				isverified = VerifyFieldValuesUsingScroll(fieldValue, fieldTxt);
				break;

			case "Identifier1":

				fieldTxt = ControlActions_MobileApp.perform_GetElementByXPath2(REG_SubmissionPageLocators.SELECT_FIELD,
						"FIELDNAME", fieldName);

				fieldTxt = ControlActions_MobileApp.perform_GetElementByXPath2(fieldTxt, "FIELDVALUE", fieldValue);

				isverified = VerifyFieldValuesUsingScroll(fieldValue, fieldTxt);
				break;

			}
			return isverified;
		} catch (Exception e) {
			logError("Failed to verify values for Field - " + fieldName + e.getMessage());
			return isverified;
		}
	}

	public boolean verifyFieldTypeValues(String Actual, String expected) {
		try {
			if (Actual.equals(expected)) {
				logInfo("Verified values for Field " + expected);
				return true;
			}
			logInfo("Could not verify values for Field " + expected);
			return false;

		} catch (Exception e) {
			logError("Failed to verify values for Record - " + e.getMessage());
			return false;
		}
	}

	public boolean VerifyFieldValuesUsingScroll(String expectedVal, String FieldValue)
			throws InterruptedException, ElementNotVisibleException {
		try {

			WebElement Field = null;
			Boolean isPresent = false;
			do {
				try {
					Field = appiumDriver.findElement(By.xpath(FieldValue));

					String ActualFieldValue = Field.getText();
					if (ActualFieldValue.equals(expectedVal)) {
						isPresent = true;
						logInfo("Field Value has Verified" + ActualFieldValue);
					} else {
						isPresent = false;
						return isPresent;
					}

				} catch (Exception ex) {

					ControlActions_MobileApp.swipe(400, 700, 400, 100);
					Thread.sleep(1000);

				}
			} while (!isPresent);
			return isPresent;
		} catch (Exception ex) {
			logInfo("Failed to verify Field" + ex.getMessage());
			return false;
		}
	}

	public boolean selectDateTimeFieldValues(WebElement DatePicker, String date)
			throws InterruptedException, ElementNotVisibleException {
		try {

			Thread.sleep(3000);
			Boolean isPresent = false;
			do {
				try {

					if (DatePicker.isDisplayed()) {

						ControlActions_MobileApp.click(DatePicker);
						select_Date_from_Calendar(date);

						isPresent = true;
						logInfo("Field Value has Selected");
					}

				} catch (Exception ex) {

					ControlActions_MobileApp.swipe(400, 700, 400, 100);
					Thread.sleep(1000);

				}
			} while (!isPresent);
			return true;
		} catch (Exception ex) {
			logInfo("Failed to Select Date" + ex.getMessage());
			return false;
		}
	}

	public boolean EnterFieldValuesUsingScrolling(String FieldValue, String FieldName)
			throws InterruptedException, ElementNotVisibleException {
		try {

			String fieldValue = null;
			WebElement Field = null;
			Thread.sleep(1000);
			Boolean isPresent = false;
			fieldValue = ControlActions_MobileApp.perform_GetElementByXPath2(REG_SubmissionPageLocators.INPUT_FIELD,
					"FIELDNAME", FieldName);

			do {
				try {
					Field = appiumDriver.findElement(By.xpath(fieldValue));
					// Thread.sleep(3000);

					ControlActions_MobileApp.waitForVisibility(Field, 8);
					ControlActions_MobileApp.ClearText(Field);
					ControlActions_MobileApp.actionEnterText(Field, FieldValue);
					ControlActions_MobileApp.performTabAction();
					logInfo("Field Value has Entered");

					isPresent = true;
					logInfo("Field Value has Entered");

				} catch (Exception ex) {

					ControlActions_MobileApp.swipe(400, 700, 400, 100);
					Thread.sleep(1000);

				}
			} while (!isPresent);
			return true;
		} catch (Exception ex) {
			logInfo("Failed to Select Date" + ex.getMessage());
			return false;
		}
	}

	public boolean EnterFieldValuesForRepeatUsingScrolling(String FieldValue, String FieldName)
			throws InterruptedException, ElementNotVisibleException {
		try {

			String fieldValue = null;
			List<MobileElement> Field = null;
			// Thread.sleep(3000);
			Boolean isPresent = false;
			fieldValue = ControlActions_MobileApp.perform_GetElementByXPath2(REG_SubmissionPageLocators.INPUT_FIELD,
					"FIELDNAME", FieldName);

			do {
				try {
					Field = appiumDriver.findElements(By.xpath(fieldValue));

					int size = Field.size();
					logInfo("Repeat field size " + size);
					Thread.sleep(3000);
					for (WebElement Ele : Field) {
						ControlActions_MobileApp.waitForVisibility(Ele, 30);
						ControlActions_MobileApp.ClearText(Ele);
						ControlActions_MobileApp.actionEnterText(Ele, FieldValue);
						ControlActions_MobileApp.performTabAction();
						logInfo("Field Value has Entered");
					}

					isPresent = true;
					logInfo("Field Value has Entered");

				} catch (Exception ex) {

					ControlActions_MobileApp.swipe(400, 700, 400, 100);
					Thread.sleep(1000);

				}
			} while (!isPresent);
			return true;
		} catch (Exception ex) {
			logInfo("Failed to Select Date" + ex.getMessage());
			return false;
		}
	}

	public boolean SelectFieldValuesUsingScrolling(String FieldValue, String FieldName)
			throws InterruptedException, ElementNotVisibleException {
		try {

			String fieldValue = null;
			WebElement Field = null;
			// Thread.sleep(3000);
			Boolean isPresent = false;
			fieldValue = ControlActions_MobileApp.perform_GetElementByXPath2(REG_SubmissionPageLocators.SELECT_FIELD,
					"FIELDNAME", FieldName);

			fieldValue = ControlActions_MobileApp.perform_GetElementByXPath2(fieldValue, "FIELDVALUE", FieldValue);

			logInfo("xpath =" + fieldValue);
			do {
				try {
					Field = appiumDriver.findElement(By.xpath(fieldValue));
					ControlActions_MobileApp.click(Field);
					isPresent = true;
					logInfo("Field Value has Selected");

				} catch (Exception ex) {

					ControlActions_MobileApp.swipe(400, 700, 400, 100);
					Thread.sleep(1000);

				}
			} while (!isPresent);
			return true;
		} catch (Exception ex) {
			logInfo("Failed to Select Date" + ex.getMessage());
			return false;
		}
	}

	public boolean SelectDateFieldValuesUsingScrolling(String FieldName, String DateToBeSelected)
			throws InterruptedException, ElementNotVisibleException {
		try {

			String fieldValue = null;
			WebElement Field = null;
			// Thread.sleep(3000);
			Boolean isPresent = false;
			fieldValue = ControlActions_MobileApp.perform_GetElementByXPath2(REG_SubmissionPageLocators.INPUT_FIELD,
					"FIELDNAME", FieldName);
			do {
				try {
					Field = appiumDriver.findElement(By.xpath(fieldValue));

					clickFieldType(Field, DateToBeSelected);
					isPresent = true;
					logInfo("Date Value has Selected");

				} catch (Exception ex) {

					ControlActions_MobileApp.swipe(400, 700, 400, 100);
					Thread.sleep(1000);

				}
			} while (!isPresent);
			return true;
		} catch (Exception ex) {
			logInfo("Failed to Select Date" + ex.getMessage());
			return false;
		}
	}

	public boolean SelectTimeFieldValuesUsingScrolling(String FieldName)
			throws InterruptedException, ElementNotVisibleException {
		try {

			String fieldValue = null;
			WebElement Field = null;
			// Thread.sleep(3000);
			Boolean isPresent = false;
			fieldValue = ControlActions_MobileApp.perform_GetElementByXPath2(REG_SubmissionPageLocators.INPUT_FIELD,
					"FIELDNAME", FieldName);
			do {
				try {
					Field = appiumDriver.findElement(By.xpath(fieldValue));

					ControlActions_MobileApp.click(Field);
					ControlActions_MobileApp.click(doneBtn);

					isPresent = true;
					logInfo("Date Value has Selected");

				} catch (Exception ex) {

					ControlActions_MobileApp.swipe(400, 700, 400, 100);
					Thread.sleep(1000);

				}
			} while (!isPresent);
			return true;
		} catch (Exception ex) {
			logInfo("Failed to Select Date" + ex.getMessage());
			return false;
		}
	}

	public boolean fillFormDetails() throws ElementNotVisibleException, InterruptedException, ParseException {
		try {
			UploadImage oUploadImage = new UploadImage(appiumDriver);

			Boolean txtNumeric = EnterFieldValuesUsingScrolling("8", "Numeric");
			TestValidation.IsTrue(txtNumeric, "Numeric Field value entered successfully",
					"Failed to enter field value");

			oUploadImage.CameraClick();
			oUploadImage.OpenCamera();
			oUploadImage.CameraShutter();
			oUploadImage.CloseCamera();
			oUploadImage.verifySelectedImageThroughGallery();

			String date = ControlActions_MobileApp.AddDaystoToddaysDate(0);

			Boolean txtDate1 = SelectDateFieldValuesUsingScrolling("Date1", date);
			TestValidation.IsTrue(txtDate1, "Date Field value entered successfully", "Failed to enter field value");
			String dateSelected = ControlActions_MobileApp.AddDaystoToddaysDateInGivenFormat("M/d/YYYY", 0);
			logInfo("Date Field Value = " + dateSelected);

			String date1 = ControlActions_MobileApp.AddDaystoToddaysDate(1);
			String dateTimeSelected = ControlActions_MobileApp.AddDaystoToddaysDateInGivenFormat("M/d/YYYY h:mm a z",
					1);
			logInfo("Date Field Value = " + dateTimeSelected);

			Boolean txtDate2 = SelectDateFieldValuesUsingScrolling("Date2", date1);
			TestValidation.IsTrue(txtDate2, "Date Field value entered successfully", "Failed to enter field value");
			String dateSelected1 = ControlActions_MobileApp.AddDaystoToddaysDateInGivenFormat("M/d/YYYY", 1);
			logInfo("Date Field Value = " + dateSelected1);

			Boolean Identifier1 = SelectFieldValuesUsingScrolling("2", "Identifier1");
			TestValidation.IsTrue(Identifier1, "SelectOne Field value Selected successfully",
					"Failed to enter field value");

			Boolean txtSelectOne1 = SelectFieldValuesUsingScrolling("C", "SelectOne");
			TestValidation.IsTrue(txtSelectOne1, "SelectOne Field value Selected successfully",
					"Failed to enter field value");

			Boolean txtParagraph = EnterFieldValuesUsingScrolling(
					"This is Paragraph Text and has to be checked with Maxixmun Field Values!@#$%^&*()", "ParaGraph1");
			TestValidation.IsTrue(txtParagraph, "Paragraph Field value entered successfully",
					"Failed to enter field value");

			Boolean txtSingleLineText = EnterFieldValuesUsingScrolling(
					"Single Line Text Value Has Entered Successsfully", "SingleLineText1");
			TestValidation.IsTrue(txtSingleLineText, "SingleLineText Field value entered successfully",
					"Failed to enter field value");

			Boolean txtIdnNumeric = EnterFieldValuesUsingScrolling("100", "Numeric2");
			TestValidation.IsTrue(txtIdnNumeric, "Identifier Numeric Field value entered successfully",
					"Failed to enter field value");

			Boolean txtMultiSelectOne1 = SelectFieldValuesUsingScrolling("A", "MultiSelect");
			TestValidation.IsTrue(txtMultiSelectOne1, "MultiSelect Field value Selected successfully",
					"Failed to enter field value");

			Boolean txtDateTime1 = SelectDateFieldValuesUsingScrolling("DateTime1", date1);
			TestValidation.IsTrue(txtDateTime1, "Date Time Field value entered successfully",
					"Failed to enter field value");

			WebElement dateTime1 = ControlActions_MobileApp
					.perform_GetElementByXPath(REG_SubmissionPageLocators.INPUT_FIELD, "FIELDNAME", "DateTime1");
			String dateTime = dateTime1.getText();
			logInfo("date time selected =" + dateTime);

			Boolean txtDateTime2 = SelectDateFieldValuesUsingScrolling("DateTime2", date1);
			TestValidation.IsTrue(txtDateTime2, "Date Time Field value entered successfully",
					"Failed to enter field value");

			WebElement dateTime2 = ControlActions_MobileApp
					.perform_GetElementByXPath(REG_SubmissionPageLocators.INPUT_FIELD, "FIELDNAME", "DateTime2");
			String dateTime3 = dateTime2.getText();
			logInfo("date time selected 2=" + dateTime3);

			Boolean txtTime1 = SelectTimeFieldValuesUsingScrolling("Time1");
			TestValidation.IsTrue(txtTime1, "Time Field value entered successfully", "Failed to enter field value");

			Boolean txtTime2 = SelectTimeFieldValuesUsingScrolling("Time2");
			TestValidation.IsTrue(txtTime2, "Time Field2 value entered successfully", "Failed to enter field value");

			Boolean txtNumeric3 = EnterFieldValuesUsingScrolling("10", "Numeric3");
			TestValidation.IsTrue(txtNumeric3, "Numeric Field value entered successfully",
					"Failed to enter field value");
			ControlActions_MobileApp.swipe(400, 700, 400, 100);
			ControlActions_MobileApp.swipe(400, 700, 400, 100);

			Boolean txtNumeric4 = EnterFieldValuesForRepeatUsingScrolling("10", "Numeric4");
			TestValidation.IsTrue(txtNumeric4, "Numeric Field value entered successfully",
					"Failed to enter field value");

			Boolean txtNumeric5 = EnterFieldValuesForRepeatUsingScrolling("80", "Numeric5");
			TestValidation.IsTrue(txtNumeric5, "Numeric Field value entered successfully",
					"Failed to enter field value");

			return true;
		} catch (Exception ex) {
			logInfo("Failed to fill field Details" + ex.getMessage());
			return false;

		}
	}

}
