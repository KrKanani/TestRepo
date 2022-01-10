package com.project.safetychain.mobileapp.pageobjects;

public class LoginPageLocators {

	public static final String usernameTxtFld = "username";
	public static final String passwordtxtFld = "password";
	public static final String LoginBtn = "loginBtn";

	public static final String USERNAME = "//android.widget.EditText[@text='Username']";
	public static final String PASSWORD = "//android.widget.EditText[@text='Password']";

	public static final String Main_menu = "ellipsisBtn";


	// SSO Login

	public static final String SSO_USRNM = "//android.widget.EditText[@resource-id='okta-signin-username']";
	public static final String SSO_PASS = "//android.widget.EditText[@resource-id='okta-signin-password']";
	public static final String SSO_SUBMIT = "//android.widget.Button[@resource-id='okta-signin-submit']";

	// LogOut Confirmation
	public static final String LOGOUT = "//android.widget.TextView[@text='Logout']";
	public static final String LOGOUT_POPUP_CONFIRM = "//android.widget.Button[@text='YES']";
	public static final String LOGOUT_POPUP_CANCEL = "//android.widget.Button[@text='CANCEL']";

	// Downloading Popup

	public static final String DOWNLOAD_POPUP_NAME = "//android.widget.TextView[@text='Downloading Data']";
	public static final String DOWNLOAD_POPUP_FORMS = "//android.widget.TextView[@text='Forms']";
	public static final String DOWNLOAD_POPUP_SAVED = "//android.widget.TextView[@text='Saved']";
	public static final String DOWNLOAD_POPUP_TASKS = "//android.widget.TextView[@text='Tasks']";
	public static final String DOWNLOAD_FORMS_COUNT = "formscountlayout";
	public static final String DOWNLOAD_SAVED_COUNT = "savedcountlayout";
	public static final String DOWNLOAD_TASKS_COUNT = "taskscountlayout";

	// SubMenuCount
	public static final String INBOX_COUNT = "//android.widget.TextView[@text='Inbox']/following::android.widget.TextView[1]";
	public static final String SAVED_COUNT = "//android.widget.TextView[@text='Saved']/following::android.widget.TextView[1]";
	public static final String FORMS_COUNT = "//android.widget.TextView[@text='Forms']/following::android.widget.TextView[1]";

	public static final String FAVOURITES_COUNT = "//android.widget.TextView[@text='Favorites']/following::android.widget.TextView[1]";
	public static final String SUBMISSIONS_COUNT = "//android.widget.TextView[@text='Submissions']/following::android.widget.TextView[1]";

	// Tenant Edit

	public static final String PROFILE_SETTING_BTN = "profileSettingBtn";
	public static final String CLEAR_BTN = "clearBtn";
	public static final String TENANT_NAME_TXT = "tanentnameField";
	public static final String SAVE_BTN = "saveBtn";

	public static final String TENANT_TXT = "tanentname";
	public static final String OK_BTN = "btnOk";
	public static final String OFFLINE_CANCEL_BTN = "btnCancel";

	public static final String AUTO_SAVE_DIALOG_MSG = "autoSaveDialogMessage";// Invalid server address, please check
																				// hostname.
	public static final String DIALOG_MSG = "dialogMessage";

	// Copyright

	public static final String COPYRIGHT_LBL = "copyright";
 
	public static final String REMEMBER_ME_TOGGLE = "remembermebtn";
	
	public static final String OKTA_LOGIN_PAGE = "//android.view.View[@resource-id='okta-sign-in']/android.view.View[2]";
	public static final String OKTA_CLOSE_BTN = "//android.widget.ImageButton[@content-desc='Close tab']";
	public static final String WHOLEFOODS_LOGIN_PG = "//android.view.View[@resource-id=\"lightbox\"]/android.view.View[2]";
	
	public static final String NOFORMSAVAILABLEMSG="headerLabel";
	
}
