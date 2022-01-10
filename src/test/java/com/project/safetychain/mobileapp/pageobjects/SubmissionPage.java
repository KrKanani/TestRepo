package com.project.safetychain.mobileapp.pageobjects;
 
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.project.testbase.TestBase;
import com.project.utilities.ControlActions_MobileApp;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement; 
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator; 

public class SubmissionPage extends TestBase {

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

	public SubmissionPage(AppiumDriver<MobileElement> driver) {
		this.appiumDriver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		mobControlActions = new ControlActions_MobileApp(this.appiumDriver);
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

	@AndroidFindBy(id = HomePageLocators.Search_formName_Keyboard)
	public WebElement softkeyboardlayout;

	@AndroidFindBy(xpath = HomePageLocators.Resource_Search_Field)
	public WebElement resourceSearchField;

	@AndroidFindBy(xpath = HomePageLocators.Resource_Name)
	public WebElement resourceName;

	@AndroidFindBy(id = HomePageLocators.NUMERIC_FIELD)
	public WebElement fieldValue;

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

	public void actionEnter() {
		// TODO Auto-generated method stub

	}

	public boolean SearchForm(String Formname) {
		try {
			ControlActions_MobileApp.waitForVisibility(searchField, 60);

			ControlActions_MobileApp.ClearText(searchField);
			ControlActions_MobileApp.actionEnterText(searchField, Formname);

			return true;
		} catch (Exception ex) {
			logInfo("Failed to Search" + ex.getMessage());
			return false;
		}
	}
 
	public boolean ClickForm(String formName) {
		try {
			Thread.sleep(5000);
			WebElement formCheck = null;

			ControlActions_MobileApp.waitForVisibility(formNameSe, 60);
			ControlActions_MobileApp.click(formNameSe);
			formCheck = ControlActions_MobileApp.perform_GetElementByXPath(HomePageLocators.Form_Name, "FormNameLocator",
					formName);
			ControlActions_MobileApp.waitForVisibility(formCheck, 60);

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
			ControlActions_MobileApp.waitForVisibility(resourceSearchField, 60);

			ControlActions_MobileApp.ClearText(resourceSearchField);
			ControlActions_MobileApp.actionEnterText(resourceSearchField, resourceNm);
			System.out.println("Resource Name has Entered");
			Thread.sleep(9000);

			resource = ControlActions_MobileApp.perform_GetElementByXPath(HomePageLocators.Resource_Name, "ResourceName",
					resourceNm);

			ControlActions_MobileApp.waitForVisibility(resource, 60);
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
			ControlActions_MobileApp.waitForVisibility(FieldType, 60);

			ControlActions_MobileApp.ClearText(FieldType);
			ControlActions_MobileApp.actionEnterText(FieldType, FieldValue);

			logInfo("Field Value has Entered");

			return true;
		} catch (Exception ex) {
			logInfo("Failed to Search resource" + ex.getMessage());
			return false;
		}
	}

	public boolean submitForm() {
		ControlActions_MobileApp.waitForVisibility(formSubmit, 60);
		ControlActions_MobileApp.click(formSubmit);

		return true;
	}

	public boolean saveForm() {
		ControlActions_MobileApp.waitForVisibility(formSave, 60);
		ControlActions_MobileApp.click(formSave);

		return true;
	}

	public boolean ClickSubMenu(WebElement subMenu) {
		ControlActions_MobileApp.waitForVisibility(mainMenu, 60);
		ControlActions_MobileApp.click(mainMenu);
		ControlActions_MobileApp.waitForVisibility(subMenu, 60);
		ControlActions_MobileApp.click(subMenu);

		return true;
	}

	public boolean checkFormStatusFromSubmissionsSubMenu(String FormName) {
		try {

			WebElement resource = null;

			Thread.sleep(9000);

			resource = ControlActions_MobileApp.perform_GetElementByXPath(HomePageLocators.Form_Name, "FormNameLocator",
					FormName);

			ControlActions_MobileApp.waitForVisibility(resource, 60);
			logInfo("Form is Present");

			ControlActions_MobileApp.waitForVisibility(submissionsFormStatus, 60);
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
		ControlActions_MobileApp.waitForVisibility(MenuBtn, 60);
		ControlActions_MobileApp.click(MenuBtn);
		ControlActions_MobileApp.click(InboxBtn);
		return true;
	}

}
