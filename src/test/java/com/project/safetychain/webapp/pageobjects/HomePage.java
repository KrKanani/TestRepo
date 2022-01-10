package com.project.safetychain.webapp.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.project.utilities.ControlActions;

public class HomePage extends CommonPage {
	
	WebDriver driver;
	WebDriverWait wait;
	ControlActions controlActions;
	
	public HomePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 40);
		controlActions = new ControlActions(driver);
	}	
	
	/**
	* Page Objects
	*/
	
	@FindBy(xpath = HomePageLocators.HOME_PAGE_LNK)
	public WebElement HomePageLnk;
	
	
	/**
	* Functions
	*/
	
	/**
	* This method is used to click on Hamburger Menu > Browser Menu.
	* @author hingorani_a
	* @param none
	* @return FSQABrowserPage This returns object with error variable as false
	* if Browser menu is clicked.
	*/
	public FSQABrowserPage clickHomepageLink() {
		try {
			controlActions.WaitforelementToBeClickable(HomePageLnk);
			controlActions.clickOnElement(HomePageLnk);
			Sync();
			logInfo("Clicked on Homepage link");
			return new FSQABrowserPage(driver);			
		}
		catch(Exception e) {
			logError("Failed to click on Homepage link - "
					+ e.getMessage());
			FSQABrowserPage fbp = new FSQABrowserPage(driver);
			fbp.error = true;
			return fbp;
		}	
	}
	
	/**
	* This method is used to click on Home link on breadcrumb and handle unnecessary popups if any
	* @author hingorani_a
	* @param none
	* @return FSQABrowserPage This returns object with error variable as false
	* if successfully land on Home (FSQA Browser)
	*/
	public FSQABrowserPage clickHomepageLinkAndBypassPopup() {
		WebElement SCSOkPopupBtn = null;
		try {
			controlActions.WaitforelementToBeClickable(HomePageLnk);
			HomePageLnk.click();
			Sync();
			logInfo("Clicked on Homepage link");

			
			SCSOkPopupBtn = controlActions.performGetElementByXPath(HomePageLocators.SCS_OK_POPUP_BTN);
			if(SCSOkPopupBtn!=null) {
				SCSOkPopupBtn.click();
				Sync();
			}
			else {
				logInfo("No popup is displayed to bypass");
			}
			
			logInfo("Landed on Homepage link");
			return new FSQABrowserPage(driver);			
		}
		catch(Exception e) {
			logError("Failed to click on Homepage link - "
					+ e.getMessage());
			FSQABrowserPage fbp = new FSQABrowserPage(driver);
			fbp.error = true;
			return fbp;
		}	
	}
}
