package com.project.safetychain.pcapp.pageobjects;

public class LoginScreenLocators {

	public static final String TENANT_NAME_TXT = "//*[@AutomationId='TenantName']";
	public static final String TENANT_LOGIN_BTN = "//*[@Name='LOGIN TO TENANT']";

	public static final String TENANT_CHANGE_LNK = "//*[@ClassName='Hyperlink' and @Name='Change']";
	public static final String TENANT_NAME_LBL = "//*[@Name='TENANT']//following-sibling::*[@ClassName='TextBlock']";
	public static final String LOADING_TENANT_LBL =  "//*[@Name='Loading Please Wait..']";
	public static final String SETTING_TENANT_LBL =  "//*[@Name='Setting up tenant']";
	public static final String USERNAME_TXT = "//*[@AutomationId='UsernameTextBox']";
	public static final String PASSWORD_TXT = "//*[@AutomationId='PasswordBox']";

	public static final String LOGIN_BTN = "//*[@Name='LOGIN']";
	public static final String LOGGING_IN_LBL =  "//*[@Name='Logging In...']";
	public static final String LOGIN_FAILED_LBL = "//*[@Name='Login Failed']";
	public static final String LOGIN_ERROR_LBL = "//*[@Name='Login error']";

	public static final String OK_BTN = "//*[@ClassName='Button' and @Name='Ok']";

	public static final String INCORRECT_PASSWORD_LBL = "//*[contains(@Name,'Incorrect Username / Password combination. Your account will be locked after') and @ClassName='TextBlock']";
	public static final String USER_LOCK_LBL = "//*[@Name='Your user account has been locked due to multiple failed login attempts. Please contact your account administrator.' and @ClassName='TextBlock']";
	public static final String USER_DEACTIVATE_LBL = "//*[@Name='Your user account has been deactivated. Please contact your account administrator.' and @ClassName='TextBlock']";

	public static final String TENANT_VALIDATION_ERROR_LBL = "//*[@ClassName='TextBlock' and @Name='Tenant validation failed. Please check your input.']";

	public static final String SSO_USERNAME_TXT = "//*[@AutomationId='okta-signin-username']";
	public static final String SSO_PASSWORD_TXT = "//*[@AutomationId='okta-signin-password']";
	public static final String SSO_SIGNIN_BTN = "//*[@AutomationId='okta-signin-submit']";
	
	public static final String WEB_VIEW_LOGIN_BTN = "//*[@Name='Login' and @FrameworkId='MicrosoftEdge']";


}
