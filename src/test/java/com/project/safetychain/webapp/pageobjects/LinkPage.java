package com.project.safetychain.webapp.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.project.utilities.ControlActions;

public class LinkPage extends CommonPage{

	WebDriver driver;
	WebDriverWait wait;
	ControlActions controlActions;

	public LinkPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 40);
		controlActions = new ControlActions(driver);	
	}	

	/**
	 * Page Objects
	 */

	@FindBy(id = LinkPageLocators.LINK_HAMBURGER_MNU)
	public WebElement LinkHamburgerMnu;
	
	@FindBy(xpath = LinkPageLocators.LINK_RECORDS_MNU)
	public WebElement LinkRecordsMnu;
	
	
	/**
	 * Functions
	 */
	
	/** Start - General */

	/**
	 * This method is used to click on Hamburger Menu.
	 * @author hingorani_a
	 * @return boolean This returns true if the Hamburger Menu is clicked.
	 */

	public boolean clickHamburgerMenu() {
		try {			
			LinkSync();	
			controlActions.WaitforelementToBeClickable(LinkHamburgerMnu);
			controlActions.clickElement(LinkHamburgerMnu);
			logInfo("Clicked on Hamburger Menu");
			return true;
		}
		catch(Exception e) {
			logError("Failed to click on Hamburger Menu - "
					+ e.getMessage());
			return false;
		}		
	}
	
	/** End - General */

	
	/** Start - Hamburger Menu */
	
	
	/**
	 * This method is used to click on Link - Hamburger Menu > Records Menu.
	 * @author hingorani_a
	 * @return LinkRecordsPage This returns object with error variable as false
	 * if Records menu is clicked.
	 */
	public LinkRecordsPage clickLinkRecords() {
		
		LinkRecordsPage lrp = new LinkRecordsPage(driver);
		try {
			if(!clickHamburgerMenu()) {
				logError("Failed to click on Link Hamburgur Menu");
				lrp.error = true;
				return lrp;
			}

			controlActions.perform_scrollToElement_ByElement(LinkRecordsMnu);
			controlActions.clickOnElement(LinkRecordsMnu);
			LinkSync();
			logInfo("Clicked on Link Records Menu");
			return new LinkRecordsPage(driver);			
		}
		catch(Exception e) {
			logError("Failed to click on Link Records Menu - "
					+ e.getMessage());
			lrp.error = true;
			return lrp;
		}	
	}
	
	/** End - Hamburger Menu */


}
