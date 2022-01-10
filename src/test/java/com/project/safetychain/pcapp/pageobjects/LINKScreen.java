package com.project.safetychain.pcapp.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.project.utilities.ControlActions_PCApp;

import io.appium.java_client.windows.WindowsDriver;

public class LINKScreen extends CommonScreen{
	WindowsDriver<WebElement> PCAppDriver;
	WebDriverWait wait;
	ControlActions_PCApp desktopControlActions;

	public LINKScreen(WindowsDriver<WebElement> driver) {
		super(driver);
		this.PCAppDriver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 30);
		desktopControlActions = new ControlActions_PCApp(this.PCAppDriver);
	}


	@FindBy(xpath = LINKScreenLocators.DOCUMENTS_TXT)
	WebElement DocumentsTxt;

	@FindBy(xpath = LINKScreenLocators.RECORDS_TXT)
	WebElement RecordsTxt;

	/**
	 * This method is used to verify LINK page elements
	 * @author pashine_a
	 * @param null
	 * @return boolean
	 */
	public boolean verifyLINKElements() {
		try {
			if(!desktopControlActions.isElementAvailable(LINKScreenLocators.DOCUMENTS_TXT)){
				logError("Documents card is not displayed");
				return false;
			}
			if(!desktopControlActions.isElementAvailable(LINKScreenLocators.RECORDS_TXT)){
				logError("Records card not displayed");
				return false;
			}
			logInfo("LINK page elements are visible");
			return true;
		}catch(Exception e) {
			logError("Failed to track LINK elements"+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to perform double click on 'LINK' Tab
	 * @author pashine_a
	 * @param null
	 * @return boolean. If nothing is impacting on double click then returns TRUE
	 */
	public boolean doubleClickLINK() {
		try {
			desktopControlActions.doubleClick(LINKMnu);
			threadsleep(15000);
			desktopControlActions.waitForElementToBePresent(LINKScreenLocators.TASK_CARD);
			threadsleep(5000);
			if(!desktopControlActions.isElementAvailable(LINKScreenLocators.RECORDS_TXT)){
				logError("Records card not displayed");
				return false;
			}
			logInfo("Multiple clicks performed on LINK' Tab");
			return true;
		}catch(Exception e) {
			logError("Failed to double click on LINK - "+ e.getMessage());
			return false;
		}	
	}

}
