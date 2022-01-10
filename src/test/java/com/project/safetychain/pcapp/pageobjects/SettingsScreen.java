package com.project.safetychain.pcapp.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.project.utilities.ControlActions_PCApp;

import io.appium.java_client.windows.WindowsDriver;

public class SettingsScreen extends CommonScreen{
	WindowsDriver<WebElement> PCAppDriver;
	WebDriverWait wait;
	ControlActions_PCApp desktopControlActions;

	public SettingsScreen(WindowsDriver<WebElement> driver) {
		super(driver);
		this.PCAppDriver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 30);
		desktopControlActions = new ControlActions_PCApp(this.PCAppDriver);
	}

	@FindBy(xpath = SettingsScreenLocators.SHOW_NUMERIC_KEYPAD_TGL)
	public WebElement NumericKeyPadTgle;

	@FindBy(xpath = SettingsScreenLocators.OFFLINE_MODE_TGL)
	public WebElement OfflineModeTgle;

	@FindBy(xpath = SettingsScreenLocators.LIMIT_DATA_KEYPAD_TGL)
	public WebElement LimitDataTgle;

	@FindBy(xpath = SettingsScreenLocators.CLEAR_SUBMISSION_BTN)
	public WebElement ClearSubmissionsBtn;

	@FindBy(xpath = SettingsScreenLocators.YES_POPUP_BTN)
	public WebElement YesButton;

	/**
	 * This method is used to change 'Show Numeric Keypad' toggle status
	 * @author pashine_a
	 * @param null
	 * @return boolean
	 */
	public boolean changeShowNumericKeyadToggle() {
		try {
			desktopControlActions.click(NumericKeyPadTgle);
			logInfo("Changed 'Show Numeric Keypad' toggle status");
			return true;
		}catch(Exception e) {
			logError("Fail to change 'Show Numeric Keypad' toggle status - "+e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to change 'Offline Mode' toggle status
	 * @author pashine_a
	 * @param null
	 * @return boolean
	 */
	public boolean changeOfflineModeToggle(boolean status) {
		try {
			desktopControlActions.click(OfflineModeTgle);
			if(status) {
				desktopControlActions.click(YesButton);
			}
			logInfo("Changed 'Offline Mode' toggle status");
			return true;
		}catch(Exception e) {
			logError("Fail to change 'Offline Mode' toggle status - "+e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to change 'Limit Data' toggle status
	 * @author pashine_a
	 * @param null
	 * @return boolean
	 */
	public boolean changeLimitDataToggle() {
		try {
			desktopControlActions.click(LimitDataTgle);
			logInfo("Changed 'Limit Data' toggle status");
			return true;
		}catch(Exception e) {
			logError("Fail to change 'Limit Data' toggle status - "+e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to click on 'Clear' button
	 * @author pashine_a
	 * @param null
	 * @return boolean
	 */
	public boolean performClearSubmission() {
		try {
			desktopControlActions.click(ClearSubmissionsBtn);
			desktopControlActions.click(YesButton);
			logInfo("Clicked on 'Clear' button");
			return true;
		}catch(Exception e) {
			logError("Fail to click on 'Clear' button - "+e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to verify the labels on the screen
	 * @author choubey_a
	 * @param null
	 * @return boolean
	 */

	public boolean verifyLabels() {
		try {
			if(!desktopControlActions.isElementAvailable(SettingsScreenLocators.ACCESSIBILITY_LBL)) {
				return false;
			}
			if(!desktopControlActions.isElementAvailable(SettingsScreenLocators.CONNECTIVITY_LBL)) {
				return false;
			}
			if(!desktopControlActions.isElementAvailable(SettingsScreenLocators.LOGS_MANAGEMENT_LBL)) {
				return false;
			}
			if(!desktopControlActions.isElementAvailable(SettingsScreenLocators.SUBMISSIONS_LBL)) {
				return false;
			}
			logInfo("Verified labels");
			return true;
		}catch(Exception e) {
			logError("Failed to verify labels- "+e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to perform double click on 'Settings' Tab
	 * @author pashine_a
	 * @param null
	 * @return boolean. If nothing is impacting on double click then returns TRUE
	 */
	public boolean doubleClickSettings() {
		try {
			desktopControlActions.doubleClick(SettingsMnu);
			if(!desktopControlActions.isElementDisplayed(OfflineModeTgle)) {
				logError("Failed to double click on Settings");
				return false;
			}
			logInfo("Multiple clicks performed on 'Settings' Tab");
			return true;
		}catch(Exception e) {
			logError("Failed to double click on Settings - "+ e.getMessage());
			return false;
		}	
	}


}
