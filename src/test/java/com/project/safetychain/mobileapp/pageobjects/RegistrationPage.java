package com.project.safetychain.mobileapp.pageobjects;


import org.openqa.selenium.WebElement; 
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.project.testbase.TestBase; 
import com.project.utilities.ControlActions_MobileApp;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class RegistrationPage extends TestBase {
	
	AppiumDriver<MobileElement> appiumDriver;
	WebDriverWait wait;
	ControlActions_MobileApp mobControlActions;

	public RegistrationPage(AppiumDriver<MobileElement> driver) {
		this.appiumDriver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		mobControlActions = new ControlActions_MobileApp(this.appiumDriver);
	}
	

	@AndroidFindBy(id = RegistrationPageLocators.tenantTxtFld)
	public WebElement TenantTxtFld;
	
	@AndroidFindBy(id = RegistrationPageLocators.NextBtn)
	public WebElement Nextbtn;
	
	public LoginPage RegisterTenant(String serverName)
	{
		try {
		ControlActions_MobileApp.sendKeys(TenantTxtFld, serverName);		
		
		ControlActions_MobileApp.click(Nextbtn);
		
		return new LoginPage(appiumDriver);
	}
		catch(Exception ex)
		{
			System.out.println("Failed to Login"+ ex.getMessage());
			return null;
		}
	}

} 

