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

public class verifyLinkOptionPage extends TestBase {

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

	public verifyLinkOptionPage(AppiumDriver<MobileElement> driver) {
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

	@AndroidFindBy(id = verifyLinkOptionPageLocators.EllipsisBtn)
	public WebElement ellipsisBtn;

	@AndroidFindBy(xpath = verifyLinkOptionPageLocators.Link)
	public WebElement Link;

	@AndroidFindBy(xpath = verifyLinkOptionPageLocators.Sidebar)
	public WebElement Sidebar;

	@AndroidFindBy(xpath = verifyLinkOptionPageLocators.DOCUMENTS)
	public WebElement documentOpt;

	@AndroidFindBy(xpath = verifyLinkOptionPageLocators.CARDS)
	public WebElement cards;
	@AndroidFindBy(xpath = verifyLinkOptionPageLocators.FILE)
	public WebElement file;
	@AndroidFindBy(xpath = verifyLinkOptionPageLocators.VIEWER)
	public WebElement viewer;
	@AndroidFindBy(xpath = verifyLinkOptionPageLocators.CLOSEVIEWER)
	public WebElement closeviewer;

	@AndroidFindBy(id = verifyLinkOptionPageLocators.DOCNAME)
	public WebElement docName;

	@AndroidFindBy(xpath = verifyLinkOptionPageLocators.FIRSTDOCSEARCH)
	public WebElement firstPDFDoc;

	@AndroidFindBy(id = verifyLinkOptionPageLocators.EXITLINK)
	public WebElement exitLink;

	@AndroidFindBy(xpath = verifyLinkOptionPageLocators.ALLFORMS)
	public WebElement formsTab;

	public void actionEnter() {
		// TODO Auto-generated method stub

	}
 
	public boolean Hamburger() {
		try {
			ControlActions_MobileApp.waitForVisibility(ellipsisBtn, 1000);
			ControlActions_MobileApp.click(ellipsisBtn);
			return true;
		} catch (Exception ex) {
			logInfo("Unable to click Hamburger");
			return false;
		}
	}

	public boolean Link() {
		try {

			ControlActions_MobileApp.waitForVisibility(Link, 80);
			ControlActions_MobileApp.click(Link);

			return true;
		} catch (Exception ex) {
			logInfo("Unable to click link");
			return false;
		}
	}

	public boolean Linkpage() throws InterruptedException {
		Thread.sleep(120000);
		try {
			if (ControlActions_MobileApp.isElementDisplayed(Sidebar)) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			logInfo("sidebar is not visible on Link Page");
			return false;
		}

	}

	public boolean clickDocuments() {
		try {
			ControlActions_MobileApp.WaitforelementToBeClickable(Sidebar);
			Sidebar.click();
			if (ControlActions_MobileApp.isElementDisplayed(documentOpt)) {

				ControlActions_MobileApp.WaitforelementToBeClickable(documentOpt);
				documentOpt.click();

				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			logInfo("sidebar is not visible on Link Page");
			return false;
		}
	}

	public boolean clickDocumentsCards() {
		try {
			ControlActions_MobileApp.waitForVisibility(cards, 60);
			ControlActions_MobileApp.WaitforelementToBeClickable(cards);
			cards.click();
			ControlActions_MobileApp.waitForVisibility(docName, 60);
			if (ControlActions_MobileApp.isElementDisplayed(docName)) {

				ControlActions_MobileApp.sendKeys(docName, ".pdf");
				appiumDriver.hideKeyboard();
				ControlActions_MobileApp.waitForVisibility(firstPDFDoc, 60);
				String pdf = firstPDFDoc.getText();
				ControlActions_MobileApp.ClearText(docName);
				ControlActions_MobileApp.sendKeys(docName, pdf);
				ControlActions_MobileApp.waitForVisibility(file, 60);
				if (file.isDisplayed() && viewer.isDisplayed()) {
					ControlActions_MobileApp.WaitforelementToBeClickable(file);
					file.click();
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		} catch (Exception e) {
			logInfo("Could not verify Documents Card");
			return false;
		}
	}

	public boolean searchDownloadedDoc() {
		ControlActions_MobileApp.WaitforelementToBeClickable(exitLink);
		exitLink.click();
		ControlActions_MobileApp.waitForVisibility(formsTab, 60);
		ControlActions_MobileApp.WaitforelementToBeClickable(formsTab);
		formsTab.click(); 
		 
		
		return false;
	}
}
