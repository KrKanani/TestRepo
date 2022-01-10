package com.project.safetychain.mobileapp.pageobjects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.utilities.ControlActions_MobileApp;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class SettingsPage extends TestBase {

	public class HomepageLocators {

		public final String formName = null;
		public final String FORM_NAME = null;

		public void main(String[] args) {
			// TODO Auto-generated method stub

		}

	}
 
	AppiumDriver<MobileElement> appiumDriver;
	WebDriverWait wait;
	static ControlActions_MobileApp mobControlActions;

	public SettingsPage(AppiumDriver<MobileElement> driver) {
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

	@AndroidFindBy(xpath = HomePageLocators.Settings_Submenu) // Setting tab
	public WebElement settingsSubMenu;

	@AndroidFindBy(xpath = HomePageLocators.Submissions_Form_Status)
	public WebElement submissionsFormStatus;

	@AndroidFindBy(id = SettingsPageLocators.Settings_ClearAllForms)
	public WebElement clearsubmissionbtn;

	@AndroidFindBy(id = SavedPageLocators.FORM_VISIBLE)
	public WebElement formVisibilityTxt;

	@AndroidFindBy(id = SettingsPageLocators.ENABLE_LOG_TOGGLE)
	public WebElement logToggle;

	@AndroidFindBy(id = SettingsPageLocators.BackBtn)
	public WebElement BackBtn;

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

	@AndroidFindBy(id = REG_AllFormsSavedLocators.CLEARSUBMSG)
	public WebElement clearSubMsg;

	@AndroidFindBy(id = REG_AllFormsSavedLocators.CLEARLOGMSG)
	public WebElement clearLogMsg;

	@AndroidFindBy(id = REG_AllFormsSavedLocators.CANCELSUBMSSN)
	public WebElement cancelSub;

	@AndroidFindBy(id = REG_AllFormsSavedLocators.CLEARLOGS)
	public WebElement clearLogs;

	@AndroidFindBy(xpath = REG_AllFormsSavedLocators.ACCESSTODOWNLOAD)
	public WebElement accessToDownloads;

	@AndroidFindBy(xpath = REG_AllFormsSavedLocators.ALLOW)
	public WebElement allow;

	@AndroidFindBy(xpath = SettingsPageLocators.ALLOW_ACCESS_TO_DOWNLOAD_BTN)
	public WebElement AllowAccessToDownloadButton;
	
	@AndroidFindBy(xpath = SettingsPageLocators.ALLOW_ACCESS_POPUP_ALLOW_BTN)
	public WebElement AllowAccessPopupAllowButton;
	
	@AndroidFindBy(xpath = SettingsPageLocators.CLEAR_LOGS_BTN)
	public WebElement ClearLogsButton;
	
	public void actionEnter() {
		// TODO Auto-generated method stub

	}

	public boolean clickClearAllSubmissions() {
		try {
			ControlActions_MobileApp.waitForVisibility(clearsubmissionbtn, 60);
			ControlActions_MobileApp.click(clearsubmissionbtn);
			ControlActions_MobileApp.waitForVisibility(clearsubmissionbtn, 60);
			ControlActions_MobileApp.click(clearsubmissionbtn);

			logInfo("Clicked on clear All Form Submissions");
			return true;
		} catch (Exception e) {
			logInfo("Could not click on Clear All Form Submissions");
			return false;
		}

	}

	public boolean ClickBackBtn() {
		try {

			ControlActions_MobileApp.waitForVisibility(BackBtn, 60);
			ControlActions_MobileApp.click(BackBtn);
			return true;

		} catch (Exception e) {
			logInfo("Could not click on back button");
			return false;

		}
	}

	public boolean EnableLogButton() {
		try {

			ControlActions_MobileApp.waitForVisibility(logToggle, 60);
			ControlActions_MobileApp.click(logToggle);
			return true;

		} catch (Exception e) {
			logInfo("Could not click on back button");
			return false;

		}
	}

	public boolean clickofflineToggleButton() {
		try {

			ControlActions_MobileApp.waitForVisibility(offlineToggleBtn);
			ControlActions_MobileApp.click(offlineToggleBtn);

			return true;
		} catch (Exception ex) {
			logInfo("Failed to fill field Details" + ex.getMessage());
			return false;

		}

	}

	public boolean verifySettingsMenu() {
		try {

			boolean isOffline1 = clickofflineToggleButton();
			TestValidation.IsTrue(isOffline1, "Verified offline Toggle button click Successfully",
					"Failed to click offline toggle button from Settings subMenu");

			ControlActions_MobileApp.waitForVisibility(logToggle, 20);
			ControlActions_MobileApp.click(logToggle);

			try {
				ControlActions_MobileApp.waitForVisibility(accessToDownloads, 60);
				ControlActions_MobileApp.WaitforelementToBeClickable(accessToDownloads);
				accessToDownloads.click();

				appiumDriver.switchTo().alert();
				ControlActions_MobileApp.waitForVisibility(allow, 20);
				ControlActions_MobileApp.WaitforelementToBeClickable(allow);
				allow.click();
			} catch (Exception ex) {
				logInfo("Access permission was not asked");
			}

			ControlActions_MobileApp.waitForVisibility(clearLogs, 20);
			ControlActions_MobileApp.WaitforelementToBeClickable(clearLogs);
			clearLogs.click();

			if (clearLogMsg.isDisplayed() && clearLogMsg.getText()
					.equalsIgnoreCase("This action will clear all logs for all users on this device.")) {
				ControlActions_MobileApp.WaitforelementToBeClickable(clearLogs);
				clearLogs.click();
			}
			if (clearSubBtn.isEnabled() && clearSubBtn.getText().equalsIgnoreCase("CLEAR SUBMISSIONS FOR ALL USERS")) {
				ControlActions_MobileApp.click(clearSubBtn);
				ControlActions_MobileApp.waitForVisibility(clearSubMsg);
				if (clearSubMsg.getText().equalsIgnoreCase(
						"This action will clear all successfully submitted records for all users on this device.")) {
					ControlActions_MobileApp.waitForVisibility(cancelSub);
					cancelSub.click();

				}
			}

			// clearSubMsg

			boolean setVal = false;
			if (SettingTitle.getText().equalsIgnoreCase("Settings") && offlineModeText.isDisplayed()
					&& limiDataText.isDisplayed() && limitDurText.isDisplayed() && selectDurationText.isDisplayed()
					&& enableLogsText.isDisplayed() && clearSubText.isDisplayed() && offlineToggleBtn.isEnabled()
					&& limitDataToggleBtn.isEnabled() && enableLogToggleBtn.isEnabled() && clearSubBtn.isEnabled()) {

				ControlActions_MobileApp.WaitforelementToBeClickable(selectDurationAngle);
				selectDurationAngle.click();

				ArrayList<MobileElement> items = new ArrayList<>(appiumDriver.findElements(By.id("limitdataitem")));
				System.out.println("ArraySize is :" + items.size());
				ArrayList<String> Actual = new ArrayList<>();
				ArrayList<String> Expected = new ArrayList<>();
				Expected.add(0, "3 DAYS");
				Expected.add(1, "7 DAYS");
				Expected.add(2, "14 DAYS");
				Expected.add(3, "21 DAYS");

				System.out.println("Expected list is:" + Expected);

				for (int i = 0; i < items.size(); i++) {
					Actual.add(items.get(i).getText());
				}
				System.out.println("Actual list is:" + Actual);

				if (Actual.equals(Expected)) {
					setVal = true;
				}

			}

			TestValidation.IsTrue(setVal, "Successfully Validated Setting tab", "Failed to validate Setting Tab");

			return true;

		} catch (Exception e) {
			logInfo("Could not verify Settings tab");
			return false;

		}
	}
	
	public boolean enableLogs() {
		try {
			ControlActions_MobileApp.waitForVisibility(logToggle, 60);
			ControlActions_MobileApp.click(logToggle);
			try {
				ControlActions_MobileApp.waitForVisibility(AllowAccessToDownloadButton, 60);
				ControlActions_MobileApp.WaitforelementToBeClickable(AllowAccessToDownloadButton);
				AllowAccessToDownloadButton.click();

				appiumDriver.switchTo().alert();
				ControlActions_MobileApp.waitForVisibility(AllowAccessPopupAllowButton, 20);
				ControlActions_MobileApp.WaitforelementToBeClickable(AllowAccessPopupAllowButton);
				AllowAccessPopupAllowButton.click();
			} catch (Exception ex) {
				logInfo("Access permission was not asked");
			}
			logInfo("Enabled Logs");
			return true;

		} catch (Exception e) {
			logInfo("Could enable Logs");
			return false;

		}
	}
	
	public boolean clearLogs() {
		try {
			ControlActions_MobileApp.waitForVisibility(clearLogs, 20);
			ControlActions_MobileApp.WaitforelementToBeClickable(clearLogs);
			clearLogs.click();

			if (clearLogMsg.isDisplayed() && clearLogMsg.getText()
					.equalsIgnoreCase("This action will clear all logs for all users on this device.")) {
				ControlActions_MobileApp.WaitforelementToBeClickable(clearLogs);
				clearLogs.click();
			}
			logInfo("Cleared Log Successfully");
			return true;
		} catch (Exception e) {
			logError("Could Not Clear Logs"+e.getMessage());
			return false;
		}
	}
	
	public boolean isLogFilesPresentInSCM_DataFolder(AppiumDriver<MobileElement>  appiumDriver) {
		try {
			List<String> lsArgs = Arrays.asList("/storage/emulated/0/Download/SCM_Data/.");
			Map<String, Object> lsCmd = new HashMap<String, Object>();
			lsCmd.put("command", "ls");
			lsCmd.put("args", lsArgs);
			String lsOutput = (String) appiumDriver.executeScript("mobile: shell", lsCmd);

			if(lsOutput.equals("")) {
				logInfo("No Log Files Present in SCM_Data Folder");
				return false;
			}
			logInfo("Log Files aren present in SCM_Data Folder");
			return true;
		} catch (Exception ex) {
			logError("Could Not check files in SCM_Data folder " + ex.getMessage());
			return false;
		}
	}

}
