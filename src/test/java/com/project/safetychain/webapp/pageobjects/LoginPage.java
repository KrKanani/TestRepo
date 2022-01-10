package com.project.safetychain.webapp.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.project.utilities.ControlActions;

public class LoginPage extends CommonPage{

	WebDriver driver;
	WebDriverWait wait;
	ControlActions controlActions;

	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 40);
		controlActions = new ControlActions(driver);	
	}	

	/**
	 * Page Objects
	 */

	@FindBy(id = LoginPageLocators.USERNAME_TXT)
	public WebElement UsernameTxt;

	@FindBy(id = LoginPageLocators.PASSWORD_TXT)
	public WebElement PasswordTxt;

	@FindBy(className = LoginPageLocators.LOGIN_BTN)
	public WebElement LoginBtn;

	@FindBy(id = LoginPageLocators.NEW_PASSWORD_TXT)
	public WebElement NewPasswordTxt;

	@FindBy(id = LoginPageLocators.CONFIRM_PASSWORD_TXT)
	public WebElement ConfirmPasswordTxt;

	@FindBy(xpath = LoginPageLocators.CREATE_PASSWORD_BTN)
	public WebElement CreatePasswordBtn;

	@FindBy(xpath = LoginPageLocators.SUPPLIER_LOGIN_BTN)
	public WebElement SupplierLoginBtn;



	/**
	 * Functions
	 */

	/**
	 * This method is used to click on Supplier Login button
	 * @author hingorani_a
	 * @param none 
	 * @return boolean Returns true if Supplier button is clicked 
	 */
	public boolean supplierLoginVerify() {
		try {
			//controlActions.waitforElementToBeDisplayed(SupplierLoginBtn);
			if(controlActions.WaitForAnElementToBeClickable(SupplierLoginBtn)) {
				logInfo("Clicked on Supplier Login button");
				SupplierLoginBtn.click();
			}
			return true;
		}
		catch(Exception e) {
			logError("Failed to click on Supplier Login button - "
					+ e.getMessage());
			return false;
		}	
	}


	/**
	 * This method is used to login to Safety Chain aplication.
	 * @author hingorani_a
	 * @param usrname The username from which you want to login to application
	 * @param passwrd Corresponding username's password
	 * @return HomePage This returns object with error variable as false
	 * if login is performed successfully.
	 */
	public HomePage performLogin(String usrname, String passwrd) {
		HomePage hp = new HomePage(driver);
		
		try {
			
			supplierLoginVerify();

			controlActions.WaitforelementToBeClickable(UsernameTxt);
			UsernameTxt.click();
			UsernameTxt.clear();
			UsernameTxt.sendKeys(usrname);	
			logInfo("UserName entered: "+ usrname);

			PasswordTxt.click();
			PasswordTxt.clear();
			PasswordTxt.sendKeys(passwrd);
			logInfo("Password entered: " + passwrd);				

			LoginBtn.click();
			logInfo("Login Button Clicked");
			Sync();

			if(!showLoggedInUserDetails()) {
				hp.error = true;
				return hp;
			}	
			logInfo("Logged in Successfully");
			return new HomePage(driver);
		}
		catch(Exception e) {
			logError("Failed while logging in to Safety Chain application - "
					+ e.getMessage());
			hp.error = true;
			return hp;
		}	
	}

	/**
	 * This method is used to login to SafetyChain application with new user
	 * @author pashine_a
	 * @param username 
	 * @param oldPassword
	 * @param newPassword
	 * @return boolean status 
	 * IF logged in with new user THEN true ELSE false
	 */
	public boolean performLogin(String username, String oldPassword, String newPassword) {
		try {

			supplierLoginVerify();

			controlActions.WaitforelementToBeClickable(UsernameTxt);
			controlActions.sendKeys(UsernameTxt, username);		
			controlActions.sendKeys(PasswordTxt, oldPassword);
			controlActions.clickElement(LoginBtn);
			logInfo("Login Button Clicked");
			controlActions.WaitforelementToBeClickable(NewPasswordTxt);
			controlActions.sendKeys(NewPasswordTxt, newPassword);		
			controlActions.sendKeys(ConfirmPasswordTxt, newPassword);
			controlActions.clickElement(CreatePasswordBtn);
			if(performLogin(username, newPassword).error) {
				return false;
			}	
			logInfo("Logged in Successfully with new user");
			return true;
		}catch(Exception e) {
			logError("Failed while login to SafetyChain application - "
					+ e.getMessage());
			return false;
		}	
	}
	
	public boolean loginAfterUserCreation(String username, String password, String newpassword) {
		try {			
			if(!userLogout()){
			}
			if(!performLogin(username, password, newpassword)) {

			}
			return true;
		}
		catch(Exception e) {
			logError("Failed to log in with new user - "+ e.getMessage());
			return false;
		}		
	}
}
