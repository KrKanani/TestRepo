package com.project.safetychain.pcapp.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.project.utilities.ControlActions_PCApp;

import io.appium.java_client.windows.WindowsDriver;

public class LoginScreen extends CommonScreen {
	WindowsDriver<WebElement> PCAppDriver;
	WebDriverWait wait;
	ControlActions_PCApp desktopControlActions; 

	public LoginScreen(WindowsDriver<WebElement> driver) {
		super(driver);
		this.PCAppDriver = driver;
		PageFactory.initElements(this.PCAppDriver, this);
		wait = new WebDriverWait(driver, 30);
		desktopControlActions = new ControlActions_PCApp(this.PCAppDriver);
	}

	/**
	 * Page Objects
	 */

	@FindBy(xpath = LoginScreenLocators.TENANT_NAME_TXT) 
	public WebElement TenantNameTxt;

	@FindBy(xpath = LoginScreenLocators.TENANT_LOGIN_BTN) 
	public WebElement TenantLoginBtn;

	@FindBy(xpath = LoginScreenLocators.TENANT_NAME_LBL) 
	public WebElement TenantNameLbl;

	@FindBy(xpath = LoginScreenLocators.TENANT_CHANGE_LNK) 
	public WebElement TenantChangeLnk;

	@FindBy(xpath = LoginScreenLocators.USERNAME_TXT) 
	public WebElement UsernameTxt;

	@FindBy(xpath = LoginScreenLocators.PASSWORD_TXT)
	public WebElement PasswordTxt;

	@FindBy(xpath = LoginScreenLocators.LOGIN_BTN)
	public WebElement LoginBtn;

	@FindBy(xpath = LoginScreenLocators.LOGIN_FAILED_LBL)
	public WebElement LoginFailedLbl;

	@FindBy(xpath = LoginScreenLocators.LOGIN_ERROR_LBL)
	public WebElement LoginErrorLbl;

	@FindBy(xpath = LoginScreenLocators.OK_BTN)
	public WebElement OkBtn;

	@FindBy(xpath = LoginScreenLocators.TENANT_VALIDATION_ERROR_LBL)
	public WebElement TenantValidationErrorLbl;

	//test1.stage
	@FindBy(xpath = LoginScreenLocators.SSO_USERNAME_TXT) 
	public WebElement SSOUsernameTxt;

	@FindBy(xpath = LoginScreenLocators.SSO_PASSWORD_TXT)
	public WebElement SSOPasswordTxt;

	@FindBy(xpath = LoginScreenLocators.SSO_SIGNIN_BTN)
	public WebElement SSOLoginBtn;

	@FindBy(xpath = LoginScreenLocators.WEB_VIEW_LOGIN_BTN)
	public WebElement WebViewSSOLoginBtn;


	/**
	 * Methods
	 */

	/**
	 * This method is used to select the tenant for login
	 * @author pashine_a
	 * @param tenantName  - The tenant name to which user want to login in application
	 * @return LoginScreen object
	 * IF tenant is selected successfully then object ELSE object with error value as true
	 */
	public LoginScreen selectTenant(String tenantName) {
		LoginScreen loginScreen = null;
		String currentTenantName;
		try {
			loginScreen = new LoginScreen(PCAppDriver);
			if(desktopControlActions.isElementDisplayed(LoginScreenLocators.TENANT_CHANGE_LNK)){
				currentTenantName = TenantNameLbl.getAttribute("Name");
				if(currentTenantName.equalsIgnoreCase(tenantName)) {
					logInfo("Tenant name - '"+tenantName+"' already selected");
				}else {
					desktopControlActions.click(TenantChangeLnk);
					threadsleep(2000);
					desktopControlActions.sendKeys(TenantNameTxt, tenantName);
					desktopControlActions.click(TenantLoginBtn);
					if(!waitToChangeTenant()) {
						loginScreen.error = true;
						return loginScreen;		
					}
					logInfo("Selected tenant - "+tenantName);
				}
			}else {
				desktopControlActions.sendKeys(TenantNameTxt, tenantName);
				desktopControlActions.click(TenantLoginBtn);
				logInfo("Added tenant name - "+tenantName);
			}
			if(desktopControlActions.isElementDisplayed(CommonScreenLocators.SSO_LOGIN_POPUP_CLOSE_BTN)){
				desktopControlActions.click(SSOClosePopUpBtn);
				threadsleep(2000);
				logInfo("Closed SSO Login popup");
			}
			threadsleep(2000);
			if(!desktopControlActions.isElementDisplayed(LoginScreenLocators.TENANT_CHANGE_LNK)){
				logError("Failed to select tenant");
				loginScreen.error = true;
				return loginScreen;
			}
			logInfo("Tenant selection is done");
			return loginScreen;
		}catch(Exception e) {
			logError("Failed to select tenant name - "+ e.getMessage());
			loginScreen.error = true;
			return loginScreen;
		}	
	}

	/**
	 * This method is used to login to SafetyChain PC App
	 * @author pashine_a
	 * @param username  - The user name from which user want to login in application
	 * @param password -  The username's password
	 * @return CommonScreen object
	 * IF login is performed successfully then object ELSE object with error value as true
	 */
	public CommonScreen Login(String tenantName, String username, String password) {
		CommonScreen commonscreen = null;
		try {
			commonscreen = new CommonScreen(PCAppDriver);
			threadsleep(6000);
			if(desktopControlActions.isElementDisplayed(CommonScreenLocators.LOADING_PLEASE_WAIT_LBL)){
				logInfo("Loading is in progress");
				desktopControlActions.waitForElementToGetInVisisble(CommonScreenLocators.LOADING_PLEASE_WAIT_LBL);
				threadsleep(4000);
			}
			if(desktopControlActions.isElementDisplayed(CommonScreenLocators.SSO_LOGIN_POPUP_CLOSE_BTN)){
				desktopControlActions.click(SSOClosePopUpBtn);
				logInfo("Closed SSO Login popup");
			}
			threadsleep(2000);
			if(selectTenant(tenantName).error) {
				logError("Failed to select tenant name");
				commonscreen.error = true;
				if(desktopControlActions.isElementDisplayed(LoginScreenLocators.OK_BTN)){
					desktopControlActions.click(OkBtn);
				}
				return commonscreen;
			}
			threadsleep(4000);
			if(desktopControlActions.isElementDisplayed(CommonScreenLocators.SSO_LOGIN_POPUP_CLOSE_BTN)){
				desktopControlActions.click(SSOClosePopUpBtn);
				logInfo("Closed SSO Login popup");
			}
			threadsleep(2000);
			desktopControlActions.sendKeys(UsernameTxt, username);		
			logInfo("UserName entered: "+ username);
			desktopControlActions.sendKeys(PasswordTxt, password);
			logInfo("Password entered: " + password);				
			desktopControlActions.click(LoginBtn);
			logInfo("Login Button Clicked");
			if(!waitToLogin()) {
				if(desktopControlActions.isElementDisplayed(LoginScreenLocators.OK_BTN)){
					desktopControlActions.click(OkBtn);
				}
				commonscreen.error = true;
				return commonscreen;	
			}
			if(desktopControlActions.isElementDisplayed(CommonScreenLocators.DOWNLOAD_DATA_POPUP_LBL)){
				logInfo("Downloading Data");
				waitToLoadData();
			}else {
				if(desktopControlActions.isElementAvailable(CommonScreenLocators.FORM_MNU)){
					logInfo("Data is downloaded");
				}else {
					if(desktopControlActions.isElementDisplayed(LoginScreenLocators.LOGIN_FAILED_LBL)){
						logError("Login Failed");
					}else{
						if(desktopControlActions.isElementDisplayed(LoginScreenLocators.LOGIN_ERROR_LBL)){
							logError("Login Error");
						}
					}

					if(desktopControlActions.isElementDisplayed(LoginScreenLocators.OK_BTN)){
						desktopControlActions.click(OkBtn);
					}
					logError("Not logged in");
					commonscreen.error = true;
					return commonscreen;
				}
			}
			logInfo("Sucessfully logged into application with user - "+loggedUserDetails());
			return commonscreen;
		}catch(Exception e) {
			if(desktopControlActions.isElementDisplayed(LoginScreenLocators.OK_BTN)){
				desktopControlActions.click(OkBtn);
			}
			logError("Failed to login SafetyChain PC application - "+ e.getMessage());
			commonscreen.error = true;
			return commonscreen;
		}	
	}


	/**
	 * This method is used to wait till "Logging In..." is in progress
	 * @author pashine_a
	 * @param null
	 * @return boolean
	 */
	public boolean waitToLogin() {
		try {
			if(desktopControlActions.isElementAvailable(LoginScreenLocators.LOGGING_IN_LBL)) {
				logInfo("Logging in progress");
				desktopControlActions.waitForElementToGetInVisisble(LoginScreenLocators.LOGGING_IN_LBL);
			}
			return true;
		}catch(Exception e) {
			return false;
		}
	}


	/**
	 * This method is used to wait till Tenant Login is in progress
	 * @author pashine_a
	 * @param null
	 * @return boolean
	 */
	public boolean waitToChangeTenant() {
		try {
			if(desktopControlActions.isElementAvailable(LoginScreenLocators.LOADING_TENANT_LBL)) {
				logInfo("Loading tenant in progress");
				desktopControlActions.waitForElementToGetInVisisble(LoginScreenLocators.LOADING_TENANT_LBL);
			}
			if(desktopControlActions.isElementAvailable(LoginScreenLocators.SETTING_TENANT_LBL)) {
				logInfo("Setting tenant in progress");
				desktopControlActions.waitForElementToGetInVisisble(LoginScreenLocators.SETTING_TENANT_LBL);
			}
			logInfo("Tenant is loaded");
			return true;
		}catch(Exception e) {
			return false;
		}
	}


	/**
	 * This method is used to relogin to the app(Logout & Login) including tenant name
	 * @author pashine_a
	 * @param tenantName
	 * @param username
	 * @param password
	 * @return boolean
	 */
	public boolean reLogin(String tenantName, String username, String password) {
		try {
			if(!logOut()) {
				logError("Fail to Logout from application");
				return false;
			}
			if(Login(tenantName, username, password).error) {
				logError("Fail to login in application");
				return false;
			}
			threadsleep(5000);
			logInfo("Tenant is loaded");
			return true;
		}catch(Exception e) {
			return false;
		}
	}


	/**
	 * This method is used to relogin to the app(Logout & Login)
	 * @author pashine_a
	 * @param username
	 * @param password	
	 * @return boolean
	 */
	public boolean reLogin(String username, String password) {
		try {
			if(!logOut()) {
				logError("Fail to Logout from application");
				return false;
			}
			if(Login(username, password).error) {
				logError("Fail to login in application");
				return false;
			}
			logInfo("Tenant is loaded");
			return true;
		}catch(Exception e) {
			return false;
		}
	}


	/**
	 * This method is used to login to the application
	 * @author pashine_a
	 * @param username
	 * @param password	
	 * @return CommonScreen object
	 */
	public CommonScreen Login(String username, String password) {
		CommonScreen commonscreen = null;
		try {
			commonscreen = new CommonScreen(PCAppDriver);
			threadsleep(6000);
			if(desktopControlActions.isElementDisplayed(CommonScreenLocators.SSO_LOGIN_POPUP_CLOSE_BTN)){
				desktopControlActions.click(SSOClosePopUpBtn);
				logInfo("Closed SSO Login popup");
			}
			threadsleep(2000);
			desktopControlActions.sendKeys(UsernameTxt, username);		
			logInfo("UserName entered: "+ username);
			desktopControlActions.sendKeys(PasswordTxt, password);
			logInfo("Password entered: " + password);				
			desktopControlActions.click(LoginBtn);
			logInfo("Login Button Clicked");
			if(!waitToLogin()) {
				commonscreen.error = true;
				return commonscreen;	
			}
			if(desktopControlActions.isElementDisplayed(CommonScreenLocators.DOWNLOAD_DATA_POPUP_LBL)){
				logInfo("Downloading Data");
				waitToLoadData();
			}else {
				if(desktopControlActions.isElementAvailable(CommonScreenLocators.FORM_MNU)){
					logInfo("Data is downloaded");
				}else {
					if(desktopControlActions.isElementDisplayed(LoginScreenLocators.LOGIN_FAILED_LBL)){
						logError("Login Failed");
					}else{
						if(desktopControlActions.isElementDisplayed(LoginScreenLocators.LOGIN_ERROR_LBL)){
							logError("Login Error");
						}
					}
					if(desktopControlActions.isElementDisplayed(LoginScreenLocators.OK_BTN)){
						desktopControlActions.click(OkBtn);
					}
					logError("Not logged in");
					commonscreen.error = true;
					return commonscreen;
				}
			}
			logInfo("Sucessfully logged into application with user - "+loggedUserDetails());
			return commonscreen;
		}catch(Exception e) {
			logError("Failed to login SafetyChain PC application - "+ e.getMessage());
			commonscreen.error = true;
			return commonscreen;
		}	
	}

	/**
	 * This method is used to lock the user by trying incorrect password attempt for valid user
	 * @author pashine_a
	 * @param username
	 * @param password	
	 * @return boolean
	 */
	public boolean tryToLoginUntilLock(String username, String password) {
		boolean lockStatus = false;
		try {
			threadsleep(2000);
			if(desktopControlActions.isElementDisplayed(CommonScreenLocators.SSO_LOGIN_POPUP_CLOSE_BTN)){
				desktopControlActions.click(SSOClosePopUpBtn);
				logInfo("Closed SSO Login popup");
			}
			threadsleep(2000);
			while(!lockStatus) {
				desktopControlActions.sendKeys(UsernameTxt, username);		
				logInfo("UserName entered: "+ username);

				desktopControlActions.sendKeys(PasswordTxt, password);
				logInfo("Password entered: " + password);	

				desktopControlActions.click(LoginBtn);
				logInfo("Login Button Clicked");

				threadsleep(2000);

				if(!desktopControlActions.isElementDisplayed(LoginScreenLocators.INCORRECT_PASSWORD_LBL)){
					if(desktopControlActions.isElementDisplayed(LoginScreenLocators.USER_LOCK_LBL)){
						logInfo("User is locked");
						desktopControlActions.click(OkBtn);
						lockStatus = true;
						break;
					}else {
						logError("Issue on performing login with incorrect credentails");
						return false;
					}
				}
				desktopControlActions.click(OkBtn);
				logInfo("Sucessfully locked the user");
			}
			return true;
		}catch(Exception e) {
			logError("Failed to lock the user using incorrect password attempts while login - "+ e.getMessage());
			return false;
		}
	}


	/**
	 * This method is used to verify Deactivated user status
	 * @author pashine_a
	 * @param username
	 * @param password	
	 * @return boolean
	 */
	public boolean verifyDeavtivatedUserLogin(String username, String password) {
		try {
			threadsleep(2000);
			if(desktopControlActions.isElementDisplayed(CommonScreenLocators.SSO_LOGIN_POPUP_CLOSE_BTN)){
				desktopControlActions.click(SSOClosePopUpBtn);
				logInfo("Closed SSO Login popup");
			}
			threadsleep(2000);
			desktopControlActions.sendKeys(UsernameTxt, username);		
			logInfo("UserName entered: "+ username);
			desktopControlActions.sendKeys(PasswordTxt, password);
			logInfo("Password entered: " + password);	
			desktopControlActions.click(LoginBtn);
			logInfo("Login Button Clicked");

			if(!desktopControlActions.isElementDisplayed(LoginScreenLocators.USER_DEACTIVATE_LBL)){
				logError("Issue on performing login for deavtivatd user");
				return false;
			}
			desktopControlActions.click(OkBtn);
			logInfo("Verified the user deactivation status");
			return true;
		}catch(Exception e) {
			logError("Failed to user deactivation status - "+ e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to verify tenant error message
	 * @author pashine_a
	 * @param null	
	 * @return boolean
	 */
	public boolean verifyTenantError() {
		try {
			if(desktopControlActions.isElementAvailable(LoginScreenLocators.TENANT_VALIDATION_ERROR_LBL)) {
				desktopControlActions.click(OkBtn);
			}
			logInfo("Verified the tennat error message");
			return true;
		}catch(Exception e) {
			logError("Failed to verify tenant error message - "+ e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to login with SSO user to SafetyChain PC App
	 * @author pashine_a
	 * @param username  - The user name from which user want to login in application
	 * @param password -  The username's password
	 * @return CommonScreen object
	 * IF login is performed successfully then object ELSE object with error value as true
	 */
	public CommonScreen loginSSO(String tenantName, String username, String password) {
		CommonScreen commonscreen = null;
		try {
			commonscreen = new CommonScreen(PCAppDriver);
			threadsleep(6000);
			if(desktopControlActions.isElementDisplayed(CommonScreenLocators.SSO_LOGIN_POPUP_CLOSE_BTN)){
				desktopControlActions.click(SSOClosePopUpBtn);
				logInfo("Closed SSO Login popup");
			}
			threadsleep(2000);
			if(selectTenant(tenantName).error) {
				logError("Failed to select tenant name");
				commonscreen.error = true;
				if(desktopControlActions.isElementDisplayed(LoginScreenLocators.OK_BTN)){
					desktopControlActions.click(OkBtn);
				}
				return commonscreen;
			}
			threadsleep(4000);
			if(desktopControlActions.isElementDisplayed(CommonScreenLocators.SSO_LOGIN_POPUP_CLOSE_BTN)){
				desktopControlActions.click(SSOClosePopUpBtn);
				logInfo("Closed SSO Login popup");
			}
			threadsleep(2000);
			desktopControlActions.click(SSOLoginLnk);
			threadsleep(8000);
			desktopControlActions.sendKeys(SSOUsernameTxt, username);		
			logInfo("UserName entered: "+ username);
			desktopControlActions.sendKeys(SSOPasswordTxt, password);
			logInfo("Password entered: " + password);				
			desktopControlActions.click(SSOLoginBtn);
			logInfo("Login Button Clicked");
			threadsleep(8000);
			if(!waitToLogin()) {
				commonscreen.error = true;
				return commonscreen;	
			}
			if(desktopControlActions.isElementDisplayed(CommonScreenLocators.SSO_INCORRECT_USER_LBL)){
				logError("Invalid Credentials");
				commonscreen.error = true;
				return commonscreen;	
			}
			if(desktopControlActions.isElementDisplayed(CommonScreenLocators.DOWNLOAD_DATA_POPUP_LBL)){
				logInfo("Downloading Data");
				waitToLoadData();
			}else {
				if(desktopControlActions.isElementAvailable(CommonScreenLocators.FORM_MNU)){
					logInfo("Data is downloaded");
				}else {
					if(desktopControlActions.isElementDisplayed(LoginScreenLocators.LOGIN_FAILED_LBL)){
						logError("Login Failed");
					}else{
						if(desktopControlActions.isElementDisplayed(LoginScreenLocators.LOGIN_ERROR_LBL)){
							logError("Login Error");
						}
					}
					if(desktopControlActions.isElementDisplayed(LoginScreenLocators.OK_BTN)){
						desktopControlActions.click(OkBtn);
					}
					logError("Not logged in");
					commonscreen.error = true;
					return commonscreen;
				}
			}
			logInfo("Sucessfully logged into application with user(SSO) - "+loggedUserDetails());
			return commonscreen;
		}catch(Exception e) {
			logError("Failed to login SafetyChain PC application(SSO) - "+ e.getMessage());
			commonscreen.error = true;
			return commonscreen;
		}	
	}


	/**
	 * This method is used to relogin to the app using SSO user
	 * @author pashine_a
	 * @param viewType
	 * @return boolean
	 */
	public boolean reLoginSSO(String tenantName, String username, String password) {
		try {
			if(!logOut()) {
				logError("Fail to Logout from application");
				return false;
			}
			threadsleep(10000);
			if(loginSSO(tenantName, username, password).error) {
				if(desktopControlActions.isElementDisplayed(CommonScreenLocators.SSO_LOGIN_POPUP_CLOSE_BTN)){
					desktopControlActions.click(SSOClosePopUpBtn);
					logInfo("Closed SSO Login popup");
				}
				logError("Fail to login in application using SSO user");
				return false;
			}
			logInfo("Login sucessfully with SSO user");
			return true;
		}catch(Exception e) {
			logError("Failed to login with SSO user - "+ e.getMessage());
			return false;
		}
	}


	/**
	 * This method is used to perform double click on "Use Single Sign On" link
	 * @author pashine_a
	 * @param null	
	 * @return boolean
	 */
	public boolean performMultipleClickSSO() {
		try {
			desktopControlActions.doubleClick(SSOLoginLnk);
			logInfo("Clicked twice on 'Use Single Sign On' link");
			return true;
		}catch(Exception e) {
			logError("Failed to perform double click on 'Use Single Sign On' link - "+ e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to verify & close Web View SSO Login pop up
	 * @author pashine_a
	 * @param null	
	 * @return boolean
	 */
	public boolean verifyCloseWebSSOLogin() {
		try {
			if(desktopControlActions.isElementDisplayed(CommonScreenLocators.SSO_WEB_VIEW_LOGIN_POPUP_CLOSE_BTN)){
				threadsleep(18000);
				if(desktopControlActions.isElementDisplayed(LoginScreenLocators.WEB_VIEW_LOGIN_BTN)){
					desktopControlActions.click(SSOClosePopUpBtn);
					threadsleep(2000);
				}else {
					logError("Failed to verify Web View SSO Login pop up");
					return false;
				}
			}else {
				logError("Failed to verify Web View SSO Login pop up");
				return false;
			}
			logInfo("Verified & close Web View SSO Login pop up");
			return true;
		}catch(Exception e) {
			logError("Failed to verify Web View SSO Login pop up - "+ e.getMessage());
			return false;
		}
	}



}
