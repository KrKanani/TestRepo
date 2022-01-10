package com.project.safetychain.mobileapp.pageobjects;

public class REG_MobileNetworkSettingsLocators {
	 
	// No Internet Connectivity Dialogue 
	public static final String NO_CONNECTIVITY_DIALOG_TITLE = "//android.widget.TextView[@text='No internet connectivity']";
	public static final String NO_CONNECTIVITY_DIALOG_MSG = "//android.widget.TextView[@text='Do you want to access the application in offline mode?']";
	
	public static final String CANCLE_BTN = "btnCancel";
	public static final String YES_BTN = "btnOk";
	
	//User Name Invalid Dialog
	public static final String USR_NM_INVALID_DIALOG_TITLE = "//android.widget.TextView[@text='User Name is invalid']";
	public static final String USR_NM_INVALID_DIALOG_MSG = "//android.widget.TextView[@text='Please check your User Name and ensure it is correct.']";
	
	
	//Change Password Dialog
	public static final String CHNG_PSSWRD_DIALOG = "relogin_modal_title";
	public static final String CHGN_PSSWRD_DLOG_TITLE = "//android.widget.TextView[@text='Password']";
	public static final String CHNG_PSSWRD_DIALOG_SUBTITLE = "relogin_modal_subtitle";
	public static final String CHNG_PSSWRD_INPUT = "relogin_modal_pwdInput";
	public static final String CHNG_PSSWRD_LOGIN_BTN = "relogin_modal_pwdInput";
	public static final String CHNG_PSSWRD_SIGNOUT_BTN = "relogin_modal_signout_btn";
	public static final String CHNG_PSSWRD_ERROR = "relogin_modal_error";
	String IncorrectPasswordError = "Incorrect User Name / Password combination. Your account will be locked after 7 more invalid login attempt(s).";
	String USerBlockedError = "Your user account has been locked due to multiple failed login attempts. Please contact your account administrator.";
	
	
}





