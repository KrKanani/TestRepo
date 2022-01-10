package com.project.safetychain.mobileapp.pageobjects;

import java.text.ParseException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.project.testbase.TestBase;
import com.project.utilities.CommonMethods;
import com.project.utilities.ControlActions_MobileApp;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class HomePage extends TestBase {

	public class HomepageLocators {

		public final String formName = null;
		public final String FORM_NAME = null;

		public void main(String[] args) {
			// TODO Auto-generated method stub

		}

	}

	@SuppressWarnings("unused")
	private static final int classname = 0;

	AppiumDriver<MobileElement> appiumDriver;
	WebDriverWait wait;
	ControlActions_MobileApp mobControlActions;
	CommonMethods commonmethods;

	public HomePage(AppiumDriver<MobileElement> driver) {
		this.appiumDriver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		mobControlActions = new ControlActions_MobileApp(this.appiumDriver);
		commonmethods = new CommonMethods(this.appiumDriver);
	}

	@AndroidFindBy(id = HomePageLocators.menuBtn)
	public WebElement MenuBtn;

	@AndroidFindBy(id = HomePageLocators.inboxBtn)
	public WebElement InboxBtn;

	@AndroidFindBy(xpath = HomePageLocators.Form_Name)
	public WebElement formNameSearched;

	@AndroidFindBy(id = HomePageLocators.formName)
	public WebElement formNameSe;

	@AndroidFindBy(id = HomePageLocators.Search_Field)
	public WebElement searchField;

	@AndroidFindBy(id = HomePageLocators.NORESULTSFOUND)
	public WebElement noResultsFound;

	@AndroidFindBy(id = HomePageLocators.Search_formName_Keyboard)
	public WebElement softkeyboardlayout;

	@AndroidFindBy(xpath = HomePageLocators.Resource_Search_Field)
	public WebElement resourceSearchField;

	@AndroidFindBy(xpath = HomePageLocators.Resource_Name)
	public WebElement resourceName;

	@AndroidFindBy(id = HomePageLocators.NUMERIC_FIELD)
	public WebElement NumericTxt;

	@AndroidFindBy(id = HomePageLocators.PARAGRAPH_FIELD)
	public WebElement paragraphTxt;
	@AndroidFindBy(id = HomePageLocators.FREETEXT_FIELD)
	public WebElement freeText;
	@AndroidFindBy(id = HomePageLocators.DATE_FIELD)
	public WebElement datePicker;
	@AndroidFindBy(id = HomePageLocators.DATETIME_FIELD)
	public WebElement dateTimePicker;

	@AndroidFindBy(id = HomePageLocators.Form_Submit)
	public WebElement formSubmit;

	@AndroidFindBy(id = HomePageLocators.Form_Save)
	public WebElement formSave;

	@AndroidFindBy(id = HomePageLocators.Main_menu)
	public WebElement mainMenu;

	@AndroidFindBy(xpath = HomePageLocators.Submissions_Submenu)
	public WebElement submissionsSubMenu;

	@AndroidFindBy(xpath = HomePageLocators.Inbox_Submenu)
	public WebElement inboxSubMenu;

	@AndroidFindBy(xpath = HomePageLocators.SAVE_SUBMENU)
	public WebElement saveSubMenu;

	@AndroidFindBy(xpath = HomePageLocators.Favourites_Submenu)
	public WebElement favouritesSubMenu;

	@AndroidFindBy(xpath = HomePageLocators.Forms_Submenu)
	public WebElement formsSubMenu;

	@AndroidFindBy(xpath = HomePageLocators.Settings_Submenu)
	public WebElement settingsSubMenu;

	@AndroidFindBy(xpath = HomePageLocators.Submissions_Form_Status)
	public WebElement submissionsFormStatus;

	@AndroidFindBy(id = HomePageLocators.HEADERDATE)
	public WebElement headerDate;

	@AndroidFindBy(id = HomePageLocators.DONE_BTN)
	public WebElement doneBtn;

	public void actionEnter() {
		// TODO Auto-generated method stub

	}

	public boolean SearchForm(String Formname) {
		boolean isPresent = false;
		try {
			WebElement resource = null;
			ControlActions_MobileApp.waitForVisibility(searchField, 120000);
			ControlActions_MobileApp.ClearText(searchField);
			ControlActions_MobileApp.actionEnterText(searchField, Formname);
			logInfo("Name has Entered");
			Thread.sleep(6000);

			resource = ControlActions_MobileApp.perform_GetElementByXPath(SavedPageLocators.Form_Name,
					"FormNameLocator", Formname);
			isPresent = ControlActions_MobileApp.isElementDisplayed(resource);

			return isPresent;
		} catch (Exception ex) {
			logInfo("Failed to Search" + ex.getMessage());
			return isPresent;
		}
	}

	public boolean NoResultsFoundCheck(String Formname) {
		boolean isFormPresent = false;
		try {

			ControlActions_MobileApp.waitForVisibility(searchField, 60);
			ControlActions_MobileApp.ClearText(searchField);
			ControlActions_MobileApp.actionEnterText(searchField, Formname);
			System.out.println("Text has Entered");
			Thread.sleep(6000);
			ControlActions_MobileApp.waitForVisibility(noResultsFound, 120);
			isFormPresent = (noResultsFound.isDisplayed()
					&& (noResultsFound.getText().trim().equalsIgnoreCase("No Results Found")));
			isFormPresent = true;
		} catch (Exception ex) {
			logInfo("Failed to Search" + ex.getMessage());

		}
		return isFormPresent;
	}

	public boolean SearchTask(String Formname) {
		boolean isPresent = false;
		try {
			WebElement resource = null;
			ControlActions_MobileApp.waitForVisibility(searchField, 60);
			ControlActions_MobileApp.ClearText(searchField);
			searchField.sendKeys(Formname);

			logInfo("Text has Entered" + Formname);
			Thread.sleep(2000);

			resource = ControlActions_MobileApp.perform_GetElementByXPath(SavedPageLocators.Form_Name,
					"FormNameLocator", Formname);
			isPresent = ControlActions_MobileApp.isElementDisplayed(resource);

			return isPresent;
		} catch (Exception ex) {
			logInfo("Failed to Search" + ex.getMessage());
			return isPresent;
		}
	}

	@SuppressWarnings("rawtypes")
	public void topBottomswipe(int timeduration) {
		try {

			Dimension size = appiumDriver.manage().window().getSize();
			// logInfo(size);
			int starty = (int) (size.height * 0.80);
			int endy = (int) (size.height * 0.20);
			int startx = size.width / 2;

			new TouchAction(appiumDriver).press(PointOption.point(startx, starty))
					.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(0, endy))
					.release().perform();// (startx, endy, startx, starty,
											// timeduration);
		} catch (Exception ex) {
			logInfo("Failed to Search resource" + ex.getMessage());
			// return false;
		}

	}

	public boolean ClickForm(String formName) {
		try {
			Thread.sleep(5000);
			WebElement formCheck = null;
			formCheck = ControlActions_MobileApp.perform_GetElementByXPath(HomePageLocators.Form_Name,
					"FormNameLocator", formName);
			ControlActions_MobileApp.waitForVisibility(formCheck, 80);

			ControlActions_MobileApp.click(formCheck);
			logInfo("Clicked on 'formName -> formName");

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

			resource = ControlActions_MobileApp.perform_GetElementByXPath(HomePageLocators.Resource_Name,
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

	public boolean selectOneOrMultipleFieldValues(String FieldType, String FieldValue) {
		try {

			WebElement fieldValue = null;
			Thread.sleep(5000);
			fieldValue = ControlActions_MobileApp.perform_GetElementByXPath(HomePageLocators.SELECTONE_FIELD, "VALUE",
					FieldValue);
			ControlActions_MobileApp.waitForVisibility(fieldValue, 80);
			while (!ControlActions_MobileApp.isElementDisplayed(fieldValue)) {
				logInfo("Entered into While loop");
				ControlActions_MobileApp.swipe(400, 700, 400, 300);
			}
			ControlActions_MobileApp.click(fieldValue);

			logInfo("Field Value has Selected");

			return true;
		} catch (Exception ex) {
			logInfo("Failed to Search resource" + ex.getMessage());
			return false;
		}
	}

	public void selectOneOrMultipleFieldValues2(String FieldType, String FieldValue)
			throws InterruptedException, ElementNotVisibleException {

		String fieldValue = null;
		WebElement Field = null;
		Thread.sleep(5000);
		fieldValue = ControlActions_MobileApp.perform_GetElementByXPath2(HomePageLocators.SELECTONE_FIELD, "VALUE",
				FieldValue);
		do {
			try {
				Field = appiumDriver.findElement(By.xpath(fieldValue));
				Thread.sleep(5000);
				ControlActions_MobileApp.click(Field);
				logInfo("Field Value has Selected");

			} catch (Exception ex) {

				ControlActions_MobileApp.swipe(400, 700, 400, 300);

				Thread.sleep(3000);
				Field = appiumDriver.findElement(By.xpath(fieldValue));

				ControlActions_MobileApp.click(Field);
				// return false;
			}
		} while (!ControlActions_MobileApp.isElementDisplayed(Field));
	}

	public boolean submitForm() {
		try {
			ControlActions_MobileApp.waitForVisibility(formSubmit, 80);
			ControlActions_MobileApp.click(formSubmit);

			return true;
		} catch (Exception ex) {
			logInfo("Failed to Search resource" + ex.getMessage());
			return false;
		}
	}

	public boolean saveForm() {
		try {

			ControlActions_MobileApp.waitForVisibility(formSave, 80);
			Thread.sleep(5000);
			ControlActions_MobileApp.click(formSave);
			Thread.sleep(5000);

			return true;
		} catch (Exception ex) {
			logInfo("Failed to Search resource" + ex.getMessage());
			return false;
		}
	}

	public boolean clickFieldType(WebElement DatePicker) throws ParseException {
		try {
			ControlActions_MobileApp.waitForVisibility(DatePicker, 1000);
			ControlActions_MobileApp.click(DatePicker);
			@SuppressWarnings("unused")
			String date = ControlActions_MobileApp.AddDaystoToddaysDate(1);
			return true;
		} catch (Exception ex) {
			logInfo("Failed to Search resource" + ex.getMessage());
			return false;
		}
	}

	public boolean ClickSubMenu(WebElement subMenu) {
		try {
			ControlActions_MobileApp.waitForVisibility(mainMenu, 180);
			ControlActions_MobileApp.WaitforelementToBeClickable(mainMenu);
			ControlActions_MobileApp.click(mainMenu);
			ControlActions_MobileApp.waitForVisibility(subMenu, 160);
			ControlActions_MobileApp.WaitforelementToBeClickable(subMenu);
			ControlActions_MobileApp.click(subMenu);
			logInfo("Clicked on Submenu ");
			Thread.sleep(6000);
			return true;
		} catch (Exception ex) {
			logInfo("Failed To click On SubMenu " + ex.getMessage());
			return false;
		}
	}

	public boolean ClickMenu(WebElement subMenu) {
		try {

			ControlActions_MobileApp.waitForVisibility(subMenu, 60);
			ControlActions_MobileApp.click(subMenu);
			return true;
		} catch (Exception ex) {
			logInfo("Failed to click Menu" + ex.getMessage());
			return false;
		}
	}

	public boolean checkFormStatusFromSubmissionsSubMenu(String FormName) {
		try {

			WebElement resource = null;

			Thread.sleep(9000);

			resource = ControlActions_MobileApp.perform_GetElementByXPath(HomePageLocators.Form_Name, "FormNameLocator",
					FormName);

			ControlActions_MobileApp.waitForVisibility(resource, 80);
			logInfo("Form is Present");

			ControlActions_MobileApp.waitForVisibility(submissionsFormStatus, 80);
			String form_Status = submissionsFormStatus.getText();
			logInfo("Form status = " + form_Status);
			if (form_Status.equals("RECEIVED")) {
				// ControlActions_MobileApp.click(resource);
				logInfo("Resource has Selected");
			} else {
				Assert.fail("Form status is not received");
			}

			return true;
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

		WebElement firstDay = ControlActions_MobileApp.perform_GetElementByXPath(HomePageLocators.DATE_SELECTED, "DATE",
				"1");

		String Date = firstDay.getAttribute("content-desc");

		String datespliter1[] = Date.split(" ");
		calandermonth = datespliter1[1] + " " + datespliter1[2];

		try {
			do {
				threadsleep(4000);

				if (calandermonth.equals(month_year)) {

					WebElement selectday = ControlActions_MobileApp
							.perform_GetElementByXPath(HomePageLocators.DATE_SELECTED, "DATE", day);

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

	public boolean SearchFormAfterUpdate(String Formname) {
		try {
			WebElement resource = null;
			ControlActions_MobileApp.waitForVisibility(searchField, 80);

			ControlActions_MobileApp.ClearText(searchField);
			ControlActions_MobileApp.actionEnterText(searchField, Formname);

			Thread.sleep(4000);

			resource = ControlActions_MobileApp.perform_GetElementByXPath(SavedPageLocators.Form_Name,
					"FormNameLocator", Formname);

			ControlActions_MobileApp.waitForVisibility(resource, 80);
			logInfo("Form is Present");

			return true;
		} catch (Exception ex) {
			logInfo("Failed to Search" + ex.getMessage());
			return false;
		}
	}

	public boolean verifyAppHomePage() {
		MobileElement allFormsIcon, allFormsTxt;

		try {
			allFormsIcon = appiumDriver.findElement(By.id(HomePageLocators.ALL_FORMS_ICON));
			allFormsTxt = appiumDriver.findElement(By.id(HomePageLocators.ALL_FORMS_TEXT));

			if (allFormsIcon.isDisplayed() && allFormsTxt.isDisplayed()) {
				return true;
			}
			return false;

		} catch (Exception e) {
			logError("" + e.getMessage());
			return false;
		}
	}

}
