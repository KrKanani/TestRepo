package com.project.safetychain.pcapp.pageobjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.project.testbase.TestBase;
import com.project.utilities.ControlActions_PCApp;

import io.appium.java_client.windows.WindowsDriver;

public class CommonScreen extends TestBase{

	WindowsDriver<WebElement> PCAppDriver;
	WebDriverWait wait;
	ControlActions_PCApp desktopControlActions;

	public CommonScreen(WindowsDriver<WebElement> driver) {
		this.PCAppDriver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 30);
		desktopControlActions = new ControlActions_PCApp(this.PCAppDriver);
	}

	public CommonScreen() {
	}


	@FindBy(xpath = CommonScreenLocators.DOWNLOAD_DATA_POPUP_LBL)
	public WebElement DownloadDataLbl;

	@FindBy(xpath = CommonScreenLocators.FORM_MNU)
	public WebElement FormsMnu;

	@FindBy(xpath = CommonScreenLocators.INBOX_MNU)
	public WebElement InboxMnu;

	@FindBy(xpath = CommonScreenLocators.SAVED_MNU)
	public WebElement SavedMnu;

	@FindBy(xpath = CommonScreenLocators.LINK_MNU)
	public WebElement LINKMnu;

	@FindBy(xpath = CommonScreenLocators.SUBMISSIONS_MNU)
	public WebElement SubmissionsMnu;

	@FindBy(xpath = CommonScreenLocators.FAVORITES_MNU)
	public WebElement FavoritesMnu;

	@FindBy(xpath = CommonScreenLocators.DEVICE_MANAGEMENT_MNU)
	public WebElement DeviceManagementMnu;

	@FindBy(xpath = CommonScreenLocators.SETTINGS_MNU)
	public WebElement SettingsMnu;

	@FindBy(xpath = CommonScreenLocators.LOGOUT_BTN)
	public WebElement LogoutBtn;

	@FindBy(xpath = CommonScreenLocators.CONFIRM_LOGOUT_BTN)
	public WebElement ConfirmLogoutBtn;

	@FindBy(xpath = CommonScreenLocators.USER_DETAILS_LBL)
	public WebElement UserDeatilsLbl;

	@FindBy(xpath = CommonScreenLocators.SSO_LOGIN_POPUP_CLOSE_BTN)
	public WebElement SSOClosePopUpBtn;

	@FindBy(xpath = CommonScreenLocators.OPEN_POPUP_ALL_LOCATIONS_BTN)
	public WebElement OpenPopUpPreviousBtn;

	@FindBy(xpath = CommonScreenLocators.OPEN_POPUP_ADDRESS_LINK)
	public WebElement OpenPopUpAddressLnk;

	@FindBy(xpath = CommonScreenLocators.OPEN_POPUP_FILE_BTN)
	public WebElement OpenPopUpFileBtn;

	@FindBy(xpath = CommonScreenLocators.OPEN_POPUP_OPEN_BTN)
	public WebElement OpenPopUpOpenBtn;

	@FindBy(xpath = CommonScreenLocators.SPC_NEXT_BTN) 
	public WebElement ChartNextButton;

	@FindBy(xpath = CommonScreenLocators.SPC_CLOSE_BTN) 
	public WebElement ChartCloseBtn;

	@FindBy(xpath = CommonScreenLocators.SSO_WEB_VIEW_LOGIN_POPUP_CLOSE_BTN)
	public WebElement SSOWebViewClosePopUpBtn;

	@FindBy(xpath = CommonScreenLocators.SSO_LOGIN_LNK)
	public WebElement SSOLoginLnk;

	@FindBy(xpath = CommonScreenLocators.HAMBURGER_MENU)
	public WebElement HamburgerMenu;

	@FindBy(xpath = CommonScreenLocators.MINIMIZE)
	public WebElement Minimize;


	/**
	 * This method is used to wait to get 'Download Data' pop up disappear
	 * @author pashine_a
	 * @param null
	 * @return boolean
	 */
	public boolean waitToLoadData() {
		try {
			desktopControlActions.waitForElementToGetInVisisble(CommonScreenLocators.DOWNLOAD_DATA_POPUP_LBL);
			threadsleep(5000);
			logInfo("Data is loaded");
			return true;
		}catch(Exception e) {
			logError("Failed to load data - "+e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to get name of logged in user
	 * @author pashine_a
	 * @param null
	 * @return String
	 */
	public String loggedUserDetails() {
		try {
			desktopControlActions.waitForElementToBeVisisble(UserDeatilsLbl);
			return UserDeatilsLbl.getText();
		}catch(Exception e) {
			logError("Failed to get user details - "+e.getMessage());
			return null;
		}
	}

	/**
	 * This method is used to select 'Inbox' tab
	 * @author pashine_a
	 * @param null
	 * @return boolean
	 */
	public InboxScreen selectInbox() {
		InboxScreen inbox = null;
		try {
			inbox = new InboxScreen(PCAppDriver);
			desktopControlActions.click(InboxMnu);
			if(desktopControlActions.isElementAvailable(CommonScreenLocators.AUTOMATIC_REFRESH_LBL)) {
				threadsleep(8000);
			}
			threadsleep(2000);
			logInfo("Opened 'Inbox' tab");
			return inbox;
		}catch(Exception e) {
			inbox.error = true;
			logError("Fail to open 'Inbox' tab - "+e.getMessage());
			return inbox;
		}
	}

	/**
	 * This method is used to select 'Submissions' tab
	 * @author pashine_a
	 * @param null
	 * @return boolean
	 */
	public SubmissionsScreen selectSubmissions() {
		SubmissionsScreen submissions = null;
		try {
			submissions = new SubmissionsScreen(PCAppDriver);
			desktopControlActions.click(SubmissionsMnu);
			logInfo("Opened 'Submissions' tab");
			return submissions;
		}catch(Exception e) {
			submissions.error = true;
			logError("Fail to open 'Submissions' tab - "+e.getMessage());
			return submissions;
		}
	}

	/**
	 * This method is used to select 'Saved' tab
	 * @author pashine_a
	 * @param null
	 * @return boolean
	 */
	public SavedScreen selectSaved() {
		SavedScreen saved = null;
		try {
			saved = new SavedScreen(PCAppDriver);
			desktopControlActions.click(SavedMnu);
			logInfo("Opened 'Saved' tab");
			return saved;
		}catch(Exception e) {
			saved.error = true;
			logError("Fail to open 'Saved' tab - "+e.getMessage());
			return saved;
		}
	}

	/**
	 * This method is used to select 'Favorites' tab
	 * @author pashine_a
	 * @param null
	 * @return boolean
	 */
	public FavoritesScreen selectFavorites() {
		FavoritesScreen favorites = null;
		try {
			favorites = new FavoritesScreen(PCAppDriver);
			desktopControlActions.click(FavoritesMnu);
			logInfo("Opened 'Favorites' tab");
			return favorites;
		}catch(Exception e) {
			favorites.error = true;
			logError("Fail to open 'Favorites' tab - "+e.getMessage());
			return favorites;
		}
	}

	/**
	 * This method is used to select 'Forms' tab
	 * @author pashine_a
	 * @param null
	 * @return boolean
	 */
	public FormsScreen selectForms() {
		FormsScreen forms = null;
		try {
			forms = new FormsScreen(PCAppDriver);
			desktopControlActions.click(FormsMnu);
			threadsleep(2000);
			logInfo("Opened 'Forms' tab");
			return forms;
		}catch(Exception e) {
			forms.error = true;
			logError("Fail to open 'Forms' tab - "+e.getMessage());
			return forms;
		}
	}

	/**
	 * This method is used to select 'Device Management' tab
	 * @author pashine_a
	 * @param null
	 * @return boolean
	 */
	public DeviceManagementScreen selectDeviceManagement() {
		DeviceManagementScreen deviceManagement = null;
		try {
			deviceManagement = new DeviceManagementScreen(PCAppDriver);
			desktopControlActions.click(DeviceManagementMnu);
			logInfo("Opened 'Device Management' tab");
			return deviceManagement;
		}catch(Exception e) {
			deviceManagement.error = true;
			logError("Fail to open 'Device Management' tab - "+e.getMessage());
			return deviceManagement;
		}
	}


	/**
	 * This method is used to select 'Link' tab
	 * @author pashine_a
	 * @param null
	 * @return boolean
	 */
	public LINKScreen selectLink() {
		LINKScreen links = null;
		try {
			links = new LINKScreen(PCAppDriver);
			desktopControlActions.click(LINKMnu);
			threadsleep(60000);
			desktopControlActions.waitForElementToBePresent(CommonScreenLocators.TASK_CARD);
			logInfo("Opened 'Link' tab");
			return links;
		}catch(Exception e) {
			links.error = true;
			logError("Fail to open 'Link' tab - "+e.getMessage());
			return links;
		}
	}

	/**
	 * This method is used to select 'Settings' tab
	 * @author pashine_a
	 * @param null
	 * @return boolean
	 */
	public SettingsScreen selectSettings() {
		SettingsScreen settings = null;
		try {
			settings = new SettingsScreen(PCAppDriver);
			desktopControlActions.click(SettingsMnu);
			logInfo("Opened 'Settings' tab");
			return settings;
		}catch(Exception e) {
			settings.error = true;
			logError("Fail to open 'Settings' tab - "+e.getMessage());
			return settings;
		}
	}


	/**
	 * This method is used to select file from file explorer (upload.png in 'test-data-files\UploadDocuments')
	 * @author pashine_a
	 * @param null
	 * @return boolean
	 */
	public boolean selectfile() {
		try {
			desktopControlActions.click(OpenPopUpPreviousBtn);
			if(!OpenPopUpAddressLnk.getAttribute("Value.Value").equals(uploadPath)) {
				desktopControlActions.sendKeys(OpenPopUpAddressLnk, uploadPath);
				desktopControlActions.sendKeys(OpenPopUpAddressLnk, Keys.ENTER);
			}
			desktopControlActions.click(OpenPopUpFileBtn);
			desktopControlActions.click(OpenPopUpOpenBtn);
			logInfo("Selected file");
			return true;
		}catch(Exception e) {
			logError("Failed to select file - "+e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to get logout from application
	 * @author pashine_a
	 * @param null
	 * @return boolean
	 */
	public boolean logOut() {
		try {
			desktopControlActions.click(LogoutBtn);
			if(desktopControlActions.isElementDisplayed(CommonScreenLocators.CONFIRM_LOGOUT_LBL)){
				logInfo("Verified 'Confirm Logout' label");
			}else {
				logError("Failed to verify 'Confirm Logout' label");
				return false;
			}
			if(desktopControlActions.isElementDisplayed(CommonScreenLocators.LOGOUT_CONFIRMATION_LBL)){
				logInfo("Verified 'Are you sure you want to logout?' label");
			}else {
				logError("Failed to verify 'Are you sure you want to logout?' label");
				return false;
			}
			desktopControlActions.click(ConfirmLogoutBtn);
			logInfo("Logut from application");
			return true;
		}catch(Exception e) {
			logError("Fail to Logout from application - "+e.getMessage());
			return false;
		}
	}


	/**
	 * This method is used to navigate to next chart
	 * @author pashine_a
	 * @param null
	 * @return boolean
	 */
	public boolean moveToNextChart() {
		try {
			desktopControlActions.click(ChartNextButton);
			threadsleep(4000);
			logInfo("Moved to next chart");
			return true;
		}catch(Exception e) {
			logError("Failed to move to next chart - "+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to close the chart screen
	 * @author pashine_a
	 * @param null
	 * @return boolean
	 */
	public boolean closeChartScreen() {
		try {
			desktopControlActions.click(ChartCloseBtn);
			threadsleep(4000);
			//			if(!desktopControlActions.isElementAvailable(CommonScreenLocators.FORM_MNU)){
			//				logError("Failed to navigate to main screen");
			//				return false;
			//			}
			logInfo("Closed chart screen");
			return true;
		}catch(Exception e) {
			logError("Failed to close chart screen - "+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to navigate to view & close the chart screen
	 * @author pashine_a
	 * @param null
	 * @return boolean
	 */
	public boolean moveAndCloseChartScreen() {
		try {
			while(true) {
				if(desktopControlActions.isElementAvailable(CommonScreenLocators.SPC_CLOSE_BTN)){
					if(!closeChartScreen()) {
						logError("Fail to move to close chart screen");
						return false;
					}
					break;
				}
				if(!moveToNextChart()) {
					logError("Fail to move to next chart");
					return false;
				}
			}

			logInfo("Closed chart screen");
			return true;
		}catch(Exception e) {
			logError("Failed to close chart screen - "+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to verify SSO login pop view(Desktop View & Web View)
	 * @author pashine_a
	 * @param viewType
	 * @return boolean
	 */
	public boolean verifySSOLoginPopUp(String viewType) {
		try {
			switch(viewType) {
			case SSOViewType.DESKTOP_VIEW:
				if(desktopControlActions.isElementDisplayed(CommonScreenLocators.SSO_LOGIN_POPUP_CLOSE_BTN)){
					desktopControlActions.click(SSOClosePopUpBtn);
					logInfo("Closed SSO Desktop View Login popup");
				}
				desktopControlActions.click(SSOLoginLnk);
				threadsleep(5000);
				if(desktopControlActions.isElementDisplayed(CommonScreenLocators.SSO_LOGIN_POPUP_CLOSE_BTN)){
					desktopControlActions.click(SSOClosePopUpBtn);
					logInfo("Closed SSO Desktop View Login popup");
				}else {
					logError("Issue on verifing SSO Desktop View Login popup");
					return false;
				}
				break;

			case SSOViewType.WEB_VIEW:
				if(desktopControlActions.isElementDisplayed(CommonScreenLocators.SSO_WEB_VIEW_LOGIN_POPUP_CLOSE_BTN)){
					desktopControlActions.click(SSOWebViewClosePopUpBtn);
					logInfo("Closed SSO Web View Login popup");
				}
				desktopControlActions.click(SSOLoginLnk);
				threadsleep(5000);
				if(desktopControlActions.isElementDisplayed(CommonScreenLocators.SSO_WEB_VIEW_LOGIN_POPUP_CLOSE_BTN)){
					desktopControlActions.click(SSOWebViewClosePopUpBtn);
					logInfo("Closed SSO Web View Login popup");
				}else {
					logError("Issue on verifing SSO Web View Login popup");
					return false;
				}
				break;

			default:
				logError("Incorrect view name");
				return false;
			}
			logInfo("Verified SSO login pop up for "+viewType);
			return true;
		}catch(Exception e) {
			logError("Failed to verify SSO login pop up for "+viewType+" - "+e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to click the hamburger menu
	 * @author choubey_a
	 * @param null
	 * @return boolean
	 */
	public boolean clickHamburgerMenu() {
		try {
			desktopControlActions.click(HamburgerMenu);
			threadsleep(4000);
			logInfo("Clicked on hamburger menu");
			return true;
		}catch(Exception e) {
			logError("Failed to click on Hamburger Menu - "+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to click the hamburger menu
	 * @author choubey_a
	 * @param null
	 * @return boolean
	 */
	public boolean verifyMenuItems() {
		try {
			if(!desktopControlActions.isElementAvailable(CommonScreenLocators.INBOX_MNU)) {
				return false;
			}
			if(!desktopControlActions.isElementAvailable(CommonScreenLocators.SUBMISSIONS_MNU)) {
				return false;
			}
			if(!desktopControlActions.isElementAvailable(CommonScreenLocators.SAVED_MNU)) {
				return false;
			}
			if(!desktopControlActions.isElementAvailable(CommonScreenLocators.FORM_MNU)) {
				return false;
			}
			return true;
		}catch(Exception e) {
			logError("Could not verify menu items - "+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to verify version and username
	 * @author choubey_a
	 * @param version and username
	 * @return boolean
	 */

	public boolean verifyloggedInDetails(String version,String username) {
		String Version;
		String Username;
		try {

			Version = desktopControlActions.getDynamicXPath(CommonScreenLocators.SC_VERSION, "VERSION", version);
			if(!desktopControlActions.isElementAvailable(Version)) {
				return false;
			}
			Username = desktopControlActions.getDynamicXPath(CommonScreenLocators.USERNAME, "USERNAME", username);
			if(!desktopControlActions.isElementAvailable(Username)) {
				return false;
			}			
			logInfo("Verified the version and username of the logged in user");
			return true;	
		}catch(Exception e) {
			logError("Could not verify the version and the username "+e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to minimize application
	 * @author choubey_a
	 * @param none
	 * @return boolean
	 */

	public boolean minimizeApp() {
		try {
			desktopControlActions.click(Minimize);
			threadsleep(2000);
			logInfo("Minimized App");
			return true;	
		}catch(Exception e) {
			logError("Could not minimize App "+e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to maximize application
	 * @author choubey_a
	 * @param none
	 * @return boolean
	 */

	public boolean maximizeApp() {
		try {
			PCAppDriver.manage().window().maximize();
			threadsleep(2000);
			logInfo("Maximized App");
			return true;	
		}catch(Exception e) {
			logError("Could not maximized App "+e.getMessage());
			return false;
		}
	}


	/**
	 * This method is used to select file from file explorer (Provided file located in 'test-data-files\UploadDocuments')
	 * @author pashine_a
	 * @param fileName
	 * @return boolean
	 */
	public boolean selectfile(String fileName) {
		WebElement selectFile = null;
		try {
			desktopControlActions.click(OpenPopUpPreviousBtn);
			if(!OpenPopUpAddressLnk.getAttribute("Value.Value").equals(uploadPath)) {
				desktopControlActions.sendKeys(OpenPopUpAddressLnk, uploadPath);
				desktopControlActions.sendKeys(OpenPopUpAddressLnk, Keys.ENTER);
			}
			selectFile = desktopControlActions.getDynamicElement(CommonScreenLocators.OPEN_POPUP_SELECT_FILE_BTN, "FILE_NAME", fileName);
			desktopControlActions.click(selectFile);
			desktopControlActions.click(OpenPopUpOpenBtn);
			logInfo("Selected file");
			return true;
		}catch(Exception e) {
			logError("Failed to select file - "+e.getMessage());
			return false;
		}	
	}

	public class SSOViewType{
		public static final String DESKTOP_VIEW = "Desktop View";
		public static final String WEB_VIEW = "Web View";
	}

}
