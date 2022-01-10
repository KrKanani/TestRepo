package com.project.safetychain.mobileapp.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.project.testbase.TestBase;
import com.project.utilities.ControlActions_MobileApp;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class UpdatedFormVersionPage extends TestBase {

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

	public UpdatedFormVersionPage(AppiumDriver<MobileElement> driver) {
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

	@AndroidFindBy(id = DisableFormFeaturePageLocators.FormFavBtn)
	public WebElement formfavBtn;

	@AndroidFindBy(id = UpdatedFormVersionPageLocators.FormVersion)
	public WebElement formVersion;

	public void actionEnter() {
		// TODO Auto-generated method stub

	}
	// Author Anima Pandey

	String version1;

	public String FirstVersion() {

		ControlActions_MobileApp.waitForVisibility(formVersion, 50);

		// MobileElement element = appiumDriver.findElement(By.id("formVersion"));
		version1 = formVersion.getText();
		logInfo(version1);
		String Version = version1.replace("Version ", "");
		logInfo("Updated Version =" + Version);
		return Version;

	}

	Integer expVersion;

	public boolean CompareVersion(String Actual, String expected) {
		try {
			if (Actual.equals(expected)) {
				logInfo("Mobile Form Version has verifed  " + Actual);
				return true;
			} else {
				return false;
			}

		} catch (Exception ex) {
			version1.equalsIgnoreCase(expVersion.toString());
			logInfo("version has not updated --- " + version1);
			return false;
		}

	}

}
