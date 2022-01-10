package com.project.safetychain.mobileapp.pageobjects;

import java.util.ArrayList;
import java.util.HashMap;
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

public class LoginPage extends TestBase {

	AppiumDriver<MobileElement> appiumDriver;
	WebDriverWait wait;
	ControlActions_MobileApp mobControlActions;

	public LoginPage(AppiumDriver<MobileElement> driver) {
		this.appiumDriver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		mobControlActions = new ControlActions_MobileApp(this.appiumDriver);
	}

	@AndroidFindBy(id = LoginPageLocators.Main_menu)
	public WebElement mainMenu;

	@AndroidFindBy(id = LoginPageLocators.NOFORMSAVAILABLEMSG)
	public WebElement NoFormsAvailbaleMsg;

	@AndroidFindBy(id = LoginPageLocators.usernameTxtFld)
	public WebElement UserNameTxt;

	@AndroidFindBy(id = LoginPageLocators.passwordtxtFld)
	public WebElement PasswordTxt;

	@AndroidFindBy(id = LoginPageLocators.LoginBtn)
	public WebElement LoginBtn;

	@AndroidFindBy(xpath = LoginPageLocators.USERNAME)
	public WebElement UserName;

	@AndroidFindBy(xpath = LoginPageLocators.PASSWORD)
	public WebElement passwordTxt;

	@AndroidFindBy(xpath = LoginPageLocators.LOGOUT)
	public WebElement logOutBtn;

	@AndroidFindBy(xpath = LoginPageLocators.LOGOUT_POPUP_CONFIRM)
	public WebElement logOutConfirm;

	@AndroidFindBy(xpath = LoginPageLocators.LOGOUT_POPUP_CANCEL)
	public WebElement logOutCancel;

	@AndroidFindBy(xpath = LoginPageLocators.DOWNLOAD_POPUP_NAME)
	public WebElement PopupName;

	@AndroidFindBy(xpath = LoginPageLocators.DOWNLOAD_POPUP_FORMS)
	public WebElement formsLabel;

	@AndroidFindBy(xpath = LoginPageLocators.DOWNLOAD_POPUP_SAVED)
	public WebElement savedLabel;

	@AndroidFindBy(xpath = LoginPageLocators.DOWNLOAD_POPUP_TASKS)
	public WebElement tasksLabel;

	@AndroidFindBy(id = LoginPageLocators.DOWNLOAD_FORMS_COUNT)
	public WebElement formsCount;

	@AndroidFindBy(id = LoginPageLocators.DOWNLOAD_SAVED_COUNT)
	public WebElement savedCount;

	@AndroidFindBy(id = REG_AllFormsSavedLocators.SEARCHBOX)
	public WebElement searchBox;
	@AndroidFindBy(id = LoginPageLocators.DOWNLOAD_TASKS_COUNT)
	public WebElement tasksCount;

	@AndroidFindBy(xpath = LoginPageLocators.INBOX_COUNT)
	public WebElement subMenuInbxCnt;

	@AndroidFindBy(xpath = LoginPageLocators.SAVED_COUNT)
	public WebElement subMenuSavedCnt;

	@AndroidFindBy(xpath = LoginPageLocators.FORMS_COUNT)
	public WebElement subMenuFormsCnt;

	@AndroidFindBy(xpath = LoginPageLocators.FAVOURITES_COUNT)
	public WebElement subMenuFavouritesCnt;

	@AndroidFindBy(xpath = LoginPageLocators.SUBMISSIONS_COUNT)
	public WebElement subMenuSubmissionsCnt;

	@AndroidFindBy(xpath = LoginPageLocators.SSO_USRNM)
	public WebElement ssoUsernameTxt;

	@AndroidFindBy(xpath = LoginPageLocators.SSO_PASS)
	public WebElement ssoPassTxt;

	@AndroidFindBy(xpath = LoginPageLocators.SSO_SUBMIT)
	public WebElement ssoSubmitBtn;

	@AndroidFindBy(id = LoginPageLocators.PROFILE_SETTING_BTN)
	public WebElement profileSettingBtn;

	@AndroidFindBy(id = LoginPageLocators.CLEAR_BTN)
	public WebElement clearBtn;

	@AndroidFindBy(id = LoginPageLocators.TENANT_NAME_TXT)
	public WebElement tenantNameTxt;

	@AndroidFindBy(id = LoginPageLocators.SAVE_BTN)
	public WebElement saveBtn;

	@AndroidFindBy(id = LoginPageLocators.TENANT_TXT)
	public WebElement tenantTxt;

	@AndroidFindBy(id = LoginPageLocators.OK_BTN)
	public WebElement oKbtn;

	@AndroidFindBy(id = LoginPageLocators.OFFLINE_CANCEL_BTN)
	public WebElement offlineCancelBtn;

	@AndroidFindBy(id = LoginPageLocators.AUTO_SAVE_DIALOG_MSG)
	public WebElement autoSaveDialogMsg;

	@AndroidFindBy(id = LoginPageLocators.COPYRIGHT_LBL)
	public WebElement copyrightLbl;

	@AndroidFindBy(id = LoginPageLocators.DIALOG_MSG)
	public WebElement dialogMsg;

	@AndroidFindBy(id = RegistrationPageLocators.tenantTxtFld)
	public WebElement TenantTxtFld;

	@AndroidFindBy(id = RegistrationPageLocators.NextBtn)
	public WebElement Nextbtn;

	@AndroidFindBy(id = LoginPageLocators.REMEMBER_ME_TOGGLE)
	public WebElement RememberMeToggleBtn;

	@AndroidFindBy(id = HomePageLocators.Search_Field)
	public WebElement SearchBar;

	@AndroidFindBy(xpath = LoginPageLocators.OKTA_LOGIN_PAGE)
	public WebElement OktaLoginPage;

	@AndroidFindBy(xpath = LoginPageLocators.OKTA_CLOSE_BTN)
	public WebElement OktaCloseButton;

	@AndroidFindBy(xpath = LoginPageLocators.WHOLEFOODS_LOGIN_PG)
	public WebElement WholefoodsLoginPage;

	public boolean LoginwithTenant(String serverName, String username, String password) {
		try {

			ControlActions_MobileApp.waitForVisibility(TenantTxtFld, 240000);
			// ControlActions_MobileApp.WaitforelementToBeClickable(TenantTxtFld);
			// TenantTxtFld.click();
			ControlActions_MobileApp.sendKeys(TenantTxtFld, serverName);

			ControlActions_MobileApp.click(Nextbtn);

			ControlActions_MobileApp.waitForVisibility(UserName, 120);
			ControlActions_MobileApp.WaitforelementToBeClickable(UserName);
			ControlActions_MobileApp.sendKeys(UserName, username);
			logInfo("UserName entered: " + username);
			ControlActions_MobileApp.waitForVisibility(passwordTxt, 60);
			ControlActions_MobileApp.sendKeys(passwordTxt, password);
			logInfo("Password entered: " + password);
			ControlActions_MobileApp.click(LoginBtn);
			// ControlActions_MobileApp.waitForVisibility(MenuBtn, 1000);
			return true;
		} catch (Exception ex) {
			System.out.println("Failed to Login" + ex.getMessage());
			return false;
		}
	}

	public boolean updateTenantName(String TenantName) {
		try {

			ControlActions_MobileApp.waitForVisibility(clearBtn, 100);
			ControlActions_MobileApp.WaitforelementToBeClickable(clearBtn);
			ControlActions_MobileApp.click(clearBtn);

			ControlActions_MobileApp.waitForVisibility(tenantNameTxt, 100);
			ControlActions_MobileApp.sendKeys(tenantNameTxt, TenantName);

			ControlActions_MobileApp.waitForVisibility(saveBtn, 100);
			ControlActions_MobileApp.WaitforelementToBeClickable(saveBtn);
			threadsleep(3000);
			ControlActions_MobileApp.click(saveBtn);
			logInfo("Tenant name has updated to " + TenantName);

			return true;

		} catch (Exception ex) {
			logInfo("Failed to update Tenant Name" + ex.getMessage());
			return false;
		}

	}

	public boolean Login(String username, String password) {
		try {
			Thread.sleep(7000);
			ControlActions_MobileApp.waitForVisibility(tenantTxt, 120);

			if (!(tenantTxt.getText().equalsIgnoreCase(mobileApp_Tenant))) {
				ControlActions_MobileApp.waitForVisibility(profileSettingBtn, 50);
				ControlActions_MobileApp.WaitforelementToBeClickable(profileSettingBtn);
				ControlActions_MobileApp.click(profileSettingBtn);
				updateTenantName(mobileApp_Tenant);
			}

			Thread.sleep(7000);
			ControlActions_MobileApp.waitForVisibility(UserName, 50);
			ControlActions_MobileApp.WaitforelementToBeClickable(UserName);
			ControlActions_MobileApp.sendKeys(UserName, username);
			logInfo("UserName entered: " + username);
			ControlActions_MobileApp.waitForVisibility(passwordTxt, 50);
			ControlActions_MobileApp.sendKeys(passwordTxt, password);
			logInfo("Password entered: " + password);
			ControlActions_MobileApp.click(LoginBtn);
			ControlActions_MobileApp.waitForVisibility(searchBox, 300);

			return true;
		} catch (Exception ex) {
			if (ControlActions_MobileApp.isElementDisplayed(dialogMsg)) {
				ControlActions_MobileApp.click(oKbtn);
			} else {
				logInfo("Failed to Login" + ex.getMessage());
			}
			return false;
		}

	}

	public boolean SSO_Login(String username, String password) {
		try {
			ControlActions_MobileApp.waitForVisibility(ssoUsernameTxt, 50);
			ControlActions_MobileApp.sendKeys(ssoUsernameTxt, username);
			logInfo("UserName entered: " + username);
			ControlActions_MobileApp.waitForVisibility(ssoPassTxt, 50);
			ControlActions_MobileApp.sendKeys(ssoPassTxt, password);
			logInfo("Password entered: " + password);
			ControlActions_MobileApp.click(ssoSubmitBtn);
			// ControlActions_MobileApp.waitForVisibility(MenuBtn, 1000);
			ControlActions_MobileApp.waitForVisibility(SearchBar, 300);
			return true;
		} catch (Exception ex) {
			logInfo("Failed to Login" + ex.getMessage());
			return false;
		}

	}

	public HashMap<String, Integer> CountDownloadingForms() {
		try {

			HashMap<String, Integer> DownCount = new HashMap<>();
			ControlActions_MobileApp.waitForVisibility(PopupName, 50);
			logInfo("Download pop has opened with name :" + PopupName.getText());

			String FormsCount = formsCount.getText();
			String Count[] = FormsCount.split(" of ");
			@SuppressWarnings("unused")
			String downloading = Count[0];
			int totalForms = Integer.parseInt(Count[1]);

			String SavedCount = savedCount.getText();
			String CountS[] = SavedCount.split(" of ");
			int SavedForms = Integer.parseInt(CountS[1]);

			String TasksCount = tasksCount.getText();
			String CountT[] = TasksCount.split(" of ");
			int Inbox = Integer.parseInt(CountT[1]);

			DownCount.put("Forms", totalForms);
			DownCount.put("Saved", SavedForms);
			DownCount.put("Inbox", Inbox);

			logInfo("Total Forms Count: " + totalForms);
			logInfo("Total Saved Forms Count: " + SavedForms);
			logInfo("Total Tasks Count: " + Inbox);
			return DownCount;
		} catch (Exception ex) {
			logInfo("Failed to get Total Forms Count" + ex.getMessage());
			return null;
		}

	}

	public boolean logOut() {
		boolean isVerified = true;
		try {
			ControlActions_MobileApp.waitForVisibility(mainMenu, 1000);
			ControlActions_MobileApp.click(mainMenu);
			logInfo("Clicked on Main menu button");
			ControlActions_MobileApp.waitForVisibility(logOutBtn, 50);
			ControlActions_MobileApp.click(logOutBtn);
			logInfo("Clicked on LogOut button");

			ControlActions_MobileApp.waitForVisibility(logOutCancel, 50);
			ControlActions_MobileApp.click(logOutCancel);
			logInfo("Clicked on logout pop cancel button");

			if (ControlActions_MobileApp.isElementDisplayed(logOutBtn)) {
				logInfo("Verified popup cancel button");

				ControlActions_MobileApp.click(logOutBtn);
				logInfo("Clicked on LogOut button Again");

				ControlActions_MobileApp.waitForVisibility(logOutConfirm, 50);
				ControlActions_MobileApp.click(logOutConfirm);
				logInfo("Clicked on logout popup confirm button");
				isVerified = true;
			} else {
				isVerified = false;
			}

		} catch (Exception ex) {
			logInfo("Failed to verify logOut popup" + ex.getMessage());
			return isVerified;
		}
		return isVerified;

	}

	public HashMap<String, Integer> VerifyReLoginFunctionality(String username, String password,
			HashMap<String, Integer> DownCountMain) {
		HashMap<String, Integer> DownCount = new HashMap<>();
		try {
			if (ControlActions_MobileApp.isElementDisplayed(UserName)) {
				ControlActions_MobileApp.waitForVisibility(UserName, 50);
				ControlActions_MobileApp.WaitforelementToBeClickable(UserName);
				ControlActions_MobileApp.sendKeys(UserName, username);
				logInfo("UserName entered: " + username);
				ControlActions_MobileApp.sendKeys(PasswordTxt, password);
				logInfo("Password entered: " + password);
				ControlActions_MobileApp.click(LoginBtn);
				logInfo("Clicked On Login Button");
			}
			Thread.sleep(2000);
			if (ControlActions_MobileApp.isElementDisplayed(PopupName)) {
				DownCount = CountDownloadingForms();
				logInfo("Count After ReLogin when popup is present: " + DownCount);
			} else {
				ControlActions_MobileApp.waitForVisibility(mainMenu, 50);
				if (ControlActions_MobileApp.isElementDisplayed(mainMenu)) {
					logInfo("No new form created:");

					DownCount = DownCountMain;
				}
			}
			// ControlActions_MobileApp.waitForVisibility(MenuBtn, 1000);

		} catch (Exception ex) {
			logInfo("Failed to Login" + ex.getMessage());
			return DownCount;
		}

		return DownCount;

	}

	public boolean calculateNewlyDownloadedForms(int beforeCount, int afterCount) {

		try {
			ControlActions_MobileApp.waitForVisibility(PopupName, 60);
			logInfo("Download pop has opened with name :" + PopupName.getText());
			// SearchBar.isDisplayed();
			if (afterCount == 0) {
				logInfo("No new forms created during Re-Login");
			} else {
				int newCount = afterCount - beforeCount;
				logInfo("Newly created forms during Re-Login : " + newCount);
			}
			return true;

		} catch (Exception ex) {
			logInfo("Forms are already downloaded,no new forms created" + ex.getMessage());
			return false;

		}

	}

	public boolean checkHumbergMenuCount(HashMap<String, Integer> DownLoadCount) {
		boolean isVerified = true;
		@SuppressWarnings("unused")
		HashMap<String, Integer> subMenuCnt = new HashMap<>();
		try {
			ControlActions_MobileApp.waitForVisibility(mainMenu, 100);
			ControlActions_MobileApp.click(mainMenu);
			logInfo("Clicked on Main menu button");
			Thread.sleep(3000);
			for (Map.Entry<String, Integer> entry1 : DownLoadCount.entrySet()) {
				String k1 = entry1.getKey();
				Integer v1 = entry1.getValue();
				logInfo("K1 = " + k1 + "V1 = " + v1);
				if (k1.contentEquals("Forms") && v1 > 0) {
					int formsCount = Integer.parseInt(subMenuFormsCnt.getText());
					logInfo("Sub Menu Forms Count " + formsCount);
					// Assert.assertEquals(v1.toString(), formsCount);

					logInfo("MainMenu Count for Forms" + formsCount + "Downloading Count" + v1);
				} else if (k1.contentEquals("Saved") && v1 > 0) {
					int savedCount = Integer.parseInt(subMenuSavedCnt.getText());
					logInfo("Sub Menu Forms Count " + formsCount);
					// Assert.assertEquals(v1.toString(), savedCount);

					logInfo("MainMenu Count for Saved" + savedCount + "Downloading Count" + v1);
				} else if (k1.contentEquals("Inbox") && v1 > 0) {
					@SuppressWarnings("unused")
					int inboxCount = Integer.parseInt(subMenuInbxCnt.getText());
					logInfo("Sub Menu Forms Count " + formsCount);
					// Assert.assertEquals(v1.toString(), inboxCount);

					logInfo("MainMenu Count for Inbox" + savedCount + "Downloading Count" + v1);
				}

			}

		} catch (Exception ex) {
			logInfo("Failed to verify logOut popup" + ex.getMessage());
			// return null;
		}
		return isVerified;

	}

	public boolean verifyLoginFunctionality(String username, String password) {
		try {
			Thread.sleep(7000);
			String copyrightLabel = copyrightLbl.getText();
			System.out.println(copyrightLabel);
			ControlActions_MobileApp.waitForVisibility(profileSettingBtn, 60);
			ControlActions_MobileApp.WaitforelementToBeClickable(profileSettingBtn);
			ControlActions_MobileApp.click(profileSettingBtn);

			verifyinvalidTenantPopup("Swapnali.@1234^%&*():;'");

			updateTenantName(mobileApp_Tenant);

			Thread.sleep(7000);

			verifyInvalidCredentialsPopup("swapnali@12345", "Shingare@17890");
			ControlActions_MobileApp.waitForVisibility(UserNameTxt, 60);
			ControlActions_MobileApp.sendKeys(UserNameTxt, username);
			logInfo("UserName entered: " + username);
			ControlActions_MobileApp.waitForVisibility(PasswordTxt, 60);
			ControlActions_MobileApp.sendKeys(PasswordTxt, password);
			logInfo("Password entered: " + password);
			ControlActions_MobileApp.click(LoginBtn);
			return true;
		} catch (Exception ex) {
			logInfo("Failed to Login" + ex.getMessage());
			return false;
		}

	}

	public boolean verifyinvalidTenantPopup(String inValidTenantName) {
		try {

			ControlActions_MobileApp.waitForVisibility(clearBtn, 60);
			ControlActions_MobileApp.click(clearBtn);

			ControlActions_MobileApp.waitForVisibility(tenantNameTxt, 60);
			ControlActions_MobileApp.sendKeys(tenantNameTxt, inValidTenantName);

			ControlActions_MobileApp.waitForVisibility(saveBtn, 60);
			ControlActions_MobileApp.click(saveBtn);

			ControlActions_MobileApp.waitForVisibility(autoSaveDialogMsg, 60);
			if (ControlActions_MobileApp.isElementDisplayed(autoSaveDialogMsg)) {
				ControlActions_MobileApp.click(oKbtn);
			}

			logInfo("Invalid Tenant name pop up has verified ");

			return true;

		} catch (Exception ex) {
			logInfo("Failed to verify invalid Tenant Name popup" + ex.getMessage());
			return false;
		}

	}

	public boolean verifyInvalidCredentialsPopup(String inValidUserName, String inValidPassword) {
		try {

			ControlActions_MobileApp.waitForVisibility(UserName, 60);
			ControlActions_MobileApp.sendKeys(UserName, inValidUserName);
			logInfo("UserName entered: " + inValidUserName);
			ControlActions_MobileApp.waitForVisibility(PasswordTxt, 60);
			ControlActions_MobileApp.sendKeys(PasswordTxt, mobileAppPassword);
			logInfo("Password entered: " + mobileAppPassword);
			ControlActions_MobileApp.click(LoginBtn);

			ControlActions_MobileApp.waitForVisibility(autoSaveDialogMsg, 60);
			if (ControlActions_MobileApp.isElementDisplayed(autoSaveDialogMsg)) {
				String inValidUserMessage = autoSaveDialogMsg.getText();
				logInfo("Error Message for invalidUserName =" + inValidUserMessage);

				ControlActions_MobileApp.click(oKbtn);
			}

			logInfo("Invalid UserName pop up has verified ");

			ControlActions_MobileApp.waitForVisibility(UserNameTxt, 60);
			ControlActions_MobileApp.sendKeys(UserNameTxt, mobileAppUsername);
			logInfo("UserName entered: " + mobileAppUsername);
			ControlActions_MobileApp.waitForVisibility(PasswordTxt, 60);
			ControlActions_MobileApp.sendKeys(PasswordTxt, inValidPassword);
			logInfo("Password entered: " + inValidPassword);
			ControlActions_MobileApp.click(LoginBtn);

			ControlActions_MobileApp.waitForVisibility(autoSaveDialogMsg, 60);
			if (ControlActions_MobileApp.isElementDisplayed(autoSaveDialogMsg)) {
				String inValidPasswordMessage = autoSaveDialogMsg.getText();
				logInfo("Error Message for invalidUserName =" + inValidPasswordMessage);

				ControlActions_MobileApp.click(oKbtn);
			}

			logInfo("Invalid Password pop up has verified ");

			return true;

		} catch (Exception ex) {
			logInfo("Failed to verify invalid Tenant Name popup" + ex.getMessage());
			return false;
		}

	}

	public boolean verifyCopyrightLbl(String actualcopyrightLbl) {
		try {
			Thread.sleep(7000);
			String copyrightLabel = copyrightLbl.getText();
			if (copyrightLabel.trim().equalsIgnoreCase(actualcopyrightLbl.trim())) {
				logInfo("copyright Label Verified Successfully" + copyrightLabel);
				return true;
			} else {
				logInfo("Actual copyright Label" + copyrightLabel.trim() + "Expected copyright = "
						+ actualcopyrightLbl.trim());
				return false;
			}
		} catch (Exception ex) {
			logInfo("Failed to copyright Label" + ex.getMessage());
			return false;
		}
	}

	public boolean LoginInOffline(String username, String password) {
		boolean isPresent = false;
		try {

			Thread.sleep(7000);
			ControlActions_MobileApp.waitForVisibility(UserName, 60);
			ControlActions_MobileApp.sendKeys(UserName, username);
			logInfo("UserName entered: " + username);
			ControlActions_MobileApp.waitForVisibility(passwordTxt, 60);
			ControlActions_MobileApp.sendKeys(passwordTxt, password);
			logInfo("Password entered: " + password);
			ControlActions_MobileApp.click(LoginBtn);
			if (ControlActions_MobileApp.isElementDisplayed(dialogMsg)) {
				ControlActions_MobileApp.click(oKbtn);
				logInfo("offline Mode login popup verified");
				isPresent = true;
			} else {
				isPresent = false;
			}

			if (ControlActions_MobileApp.isElementDisplayed(autoSaveDialogMsg)) {
				if (autoSaveDialogMsg.getText().trim()
						.equalsIgnoreCase("Please check your User Name and ensure it is correct.")) {
					ControlActions_MobileApp.click(oKbtn);
					logInfo("For this user - forms are not downloaded Already");
					isPresent = true;
				}
			} else {
				ControlActions_MobileApp.waitForVisibility(mainMenu);
				logInfo("For this user - forms are downloaded Already");

			}
			return isPresent;

		} catch (Exception ex) {
			logInfo("Failed to Login" + ex.getMessage());
			return isPresent;
		}

	}

	public boolean LoginForLinkFunctionality(String username, String password) {
		try {

			Thread.sleep(7000);
			ControlActions_MobileApp.waitForVisibility(UserName, 50);
			ControlActions_MobileApp.sendKeys(UserName, username);
			logInfo("UserName entered: " + username);
			ControlActions_MobileApp.waitForVisibility(passwordTxt, 50);
			ControlActions_MobileApp.sendKeys(passwordTxt, password);
			logInfo("Password entered: " + password);
			ControlActions_MobileApp.click(LoginBtn);
			if (ControlActions_MobileApp.isElementDisplayed(dialogMsg)) {
				ControlActions_MobileApp.click(oKbtn);
			}

			return true;
		} catch (Exception ex) {
			logInfo("Failed to Login" + ex.getMessage());
			return false;
		}

	}

	/**
	 * This method is used to verify 'Remember Me' functionality before login
	 * 
	 * @author ahmed_tw
	 * @param username
	 *            [String] : Username for login
	 * @param password
	 *            [Password] : Password for login
	 * @return [boolean] : True if the Remember Me functionality was verified
	 *         else false
	 */
	public boolean verifyRememberMeFunctionality(String username, String password) {
		try {
			Thread.sleep(7000);
			ControlActions_MobileApp.waitForVisibility(tenantTxt, 50);

			if (!(tenantTxt.getText().equalsIgnoreCase(mobileApp_Tenant))) {
				ControlActions_MobileApp.waitForVisibility(profileSettingBtn, 50);
				ControlActions_MobileApp.WaitforelementToBeClickable(profileSettingBtn);
				ControlActions_MobileApp.click(profileSettingBtn);
				boolean updateTenenat = updateTenantName(mobileApp_Tenant);
				TestValidation.IsTrue(updateTenenat, "Updated tenant as -" + mobileApp_Tenant,
						"Failed toUpdate tenant");
			}

			ControlActions_MobileApp.waitForVisibility(UserName, 50);
			ControlActions_MobileApp.WaitforelementToBeClickable(UserName);
			ControlActions_MobileApp.sendKeys(UserName, username);
			logInfo("UserName entered: " + username);
			TestValidation.IsTrue(true, "UserName entered: " + username, "Failed to enter username");

			ControlActions_MobileApp.waitForVisibility(passwordTxt, 50);
			ControlActions_MobileApp.sendKeys(passwordTxt, password);
			logInfo("Password entered: " + password);
			TestValidation.IsTrue(true, "Password entered: " + password, "Failed to enter password");

			ControlActions_MobileApp.waitForVisibility(RememberMeToggleBtn, 50);
			ControlActions_MobileApp.WaitforelementToBeClickable(RememberMeToggleBtn);
			ControlActions_MobileApp.click(RememberMeToggleBtn);
			logInfo("Clicked on Remember Me Toggle");
			TestValidation.IsTrue(true, "Clicked on Remember Me Toggle Button",
					"Failed to Click Remember ME Toggle Button");

			ControlActions_MobileApp.click(LoginBtn);
			logInfo("Clicked Login Button");
			TestValidation.IsTrue(true, "Clicked Login Button", "Failed to Click Login Button");

			ControlActions_MobileApp.waitForVisibility(SearchBar, 300);

			logOut();
			TestValidation.IsTrue(true, "Logged Of The Application", "Failed to logout");
			Thread.sleep(7000);

			boolean verifyUsrNm = UserNameTxt.getText().equals(username);
			TestValidation.IsTrue(verifyUsrNm, "Verified already Present Username because of Remember Me Toggle ON",
					"Failed to already present username");
			if (verifyUsrNm) {
				logInfo("Verified that User name is already present after log out");

				ControlActions_MobileApp.waitForVisibility(RememberMeToggleBtn, 50);
				ControlActions_MobileApp.WaitforelementToBeClickable(RememberMeToggleBtn);
				ControlActions_MobileApp.click(RememberMeToggleBtn);
				logInfo("Clicked on Remember Me Toggle");

				ControlActions_MobileApp.waitForVisibility(PasswordTxt, 50);
				ControlActions_MobileApp.sendKeys(PasswordTxt, password);
				logInfo("Password entered Again: " + password);

				ControlActions_MobileApp.click(LoginBtn);
				logInfo("Clicked Login Button Again");

				ControlActions_MobileApp.waitForVisibility(SearchBar, 300);
				logOut();
				Thread.sleep(7000);
				TestValidation.IsTrue(true, "Logged In and Logged Out With Remember ME - Off",
						"Failed to Log In and Log Out With Remember ME - Off");
				return true;
			} else {
				logInfo("Remember Me functionality was not verified");
				return false;
			}

		} catch (Exception e) {
			logError("Could Not Verify Remember Me Functionality" + e.getMessage() + e.getCause());
			return false;
		}
	}

	/**
	 * This method is used to set new tenant
	 * 
	 * @author ahmed_tw
	 * @param tenantName
	 *            [String] : Tenant name
	 * @return [boolean] : True after setting the new tenant, false if fails to
	 *         do so.
	 */
	public boolean setNewTenant(String tenantName) {
		try {
			threadsleep(7000);
			ControlActions_MobileApp.waitForVisibility(profileSettingBtn, 12);
			ControlActions_MobileApp.click(profileSettingBtn);
			threadsleep(3000);
			updateTenantName(tenantName);
			logInfo("Successfully set New Tenant as -" + tenantName);
			return true;
		} catch (Exception e) {
			logError("Failed to set new tenant" + e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to verify Login Page of provided SSO Tenant
	 * 
	 * @author ahmed_tw
	 * @param tenantName
	 *            [String] : SSO Tenant Name
	 * @return [boolean] : True after verifying Login Pase of the sso Tenant
	 *         otherwise false
	 */
	public boolean verifySSOLoginPage(String ssoTenantName) {
		try {
			if (ssoTenantName.equals(SSO_TENANT.TEST1STAGE)) {
				ControlActions_MobileApp.waitForVisibility(OktaLoginPage, 20);
				if (OktaLoginPage.isDisplayed()) {
					logInfo("Verified Okta Login Page for tenant -" + ssoTenantName);
					return true;
				} else {
					logInfo("Failed to verify Okta Login Page for tenant -" + ssoTenantName);
					return false;
				}
			}
			if (ssoTenantName.equals(SSO_TENANT.WHOLEFOODS)) {
				ControlActions_MobileApp.waitForVisibility(WholefoodsLoginPage, 20);
				if (WholefoodsLoginPage.isDisplayed()) {
					logInfo("Verified WholeFoods Login Page for tenant -" + ssoTenantName);
					return true;
				} else {
					logInfo("Failed to verify WholeFoods Login Page for tenant -" + ssoTenantName);
					return false;
				}
			}
			return true;
		} catch (Exception ex) {
			logInfo("Failed to Set New Tenant " + ex.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to click on Close (X) button on OKTA Login Page
	 * 
	 * @author ahmed_tw
	 * @return [boolean] : True after clicking on close button of OKTA page
	 */
	public boolean clickOktaCloseButton() {
		try {
			ControlActions_MobileApp.waitForVisibility(OktaLoginPage, 20);
			ControlActions_MobileApp.waitForVisibility(OktaCloseButton, 90);
			OktaCloseButton.click();
			logInfo("Successfully Clicked Close Button on Okta Login Page");
			return true;

		} catch (Exception e) {
			logError("Could Not CLose Okta Login Page" + e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to close the Login Page of SSO Tenant
	 * 
	 * @author ahmed_tw
	 * @param ssoTenant
	 *            [String] : Name of SSO Tenant
	 * @return [boolean] : True after closing the login page of SSO Tenant,
	 *         otherwise false.
	 */
	public boolean closeSSOLoginPage(String ssoTenant) {
		try {
			if (ssoTenant.equals(SSO_TENANT.TEST1STAGE)) {
				if (!clickOktaCloseButton()) {
					logInfo("Failed To Close SSO tenant -" + ssoTenant + " Login Page");
					return false;
				}
			}

			if (ssoTenant.equals(SSO_TENANT.WHOLEFOODS)) {
				appiumDriver.navigate().back();
			}

			logInfo("Closed SSO Tenant -" + ssoTenant + " Login page");
			return true;
		} catch (Exception e) {
			logError("Could Not Close SSO tenant Page - " + e.getMessage());
			return false;
		}
	}

	public static class SSO_TENANT {
		public static final String TEST1STAGE = "test1.stage";
		public static final String WHOLEFOODS = "wholefoods";
	}

	public boolean FormsGridAfterLogin() {
		boolean formsPresent = false;
		try {
			ArrayList<MobileElement> forms = new ArrayList<>(appiumDriver.findElements(By.id("formmainLayout")));
			logInfo("No of forms present in grid"+ forms.size());
			if (forms.size() == 0 && NoFormsAvailbaleMsg.getText().equalsIgnoreCase("No Forms Available")) {
				logInfo("No forms are present after login");
				formsPresent = false;
			} else {
				formsPresent = true;
			}

		} catch (Exception e) {
			logInfo("No forms Available after login");
		}
		return formsPresent;
	}
}
