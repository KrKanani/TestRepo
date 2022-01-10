package com.project.safetychain.webapp.testcases;


import java.util.HashMap;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.project.safetychain.webapp.pageobjects.DevicesPage;
import com.project.safetychain.webapp.pageobjects.DevicesPage.DEVICEFIELDS;
import com.project.safetychain.webapp.pageobjects.DevicesPage.HANDSHAKEDDLVAL;
import com.project.safetychain.webapp.pageobjects.DevicesPage.PARITYDDLVAL;
import com.project.safetychain.webapp.pageobjects.DevicesPage.STOPBITSDDLVAL;
import com.project.safetychain.webapp.pageobjects.DevicesPageLocators;
import com.project.safetychain.webapp.pageobjects.HomePage;
import com.project.safetychain.webapp.pageobjects.LoginPage;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.utilities.CommonMethods;
import com.project.utilities.ControlActions;

public class TCG_SMK_Devices extends TestBase {

	ControlActions controlActions;
	static CommonMethods common;
	static DevicesPage devices;
	static LoginPage lp;
	static HomePage hp;

	public static String deviceName;
	public static HashMap<String, String> addSettings;
	//public static String deviceName = CommonMethods.dynamicText("AutoDevices");
	public static String printComment;
	public static String printComment1;

	@BeforeClass(alwaysRun = true)                       
	public void groupInit() throws InterruptedException {
		driver = launchbrowser();
		controlActions = new ControlActions(driver);
		controlActions.getUrl(applicationUrl);
		hp=new HomePage(driver);
		lp = new LoginPage(driver);
		common = new CommonMethods(driver);

		deviceName = CommonMethods.dynamicText("Auto_DVN");

		printComment1 =CommonMethods.dynamicText("Auto_PrintComment_updated");

		hp = lp.performLogin(adminUsername, adminPassword);
		if(hp.error) {
			TCGFailureMsg = "Could NOT login to application";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}


		DevicesPage dvp = hp.clickDevicesMenu();


		if(!dvp.clickAddNewDeviceBtn()) {
			TCGFailureMsg = "Could NOT click on add Device button";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		if(!dvp.setDeviceName(deviceName)) {
			TCGFailureMsg = "Could NOT set the DeviceName" ;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		if(!dvp.clickEnableDevice()) {
			TCGFailureMsg = "Could NOT set toggle button for enable or disable" ;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		if(!dvp.selectDeviceType()) {
			TCGFailureMsg = "Could NOT Select Device Type";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		HashMap<String,String> configSetting = new HashMap<String, String>();
		configSetting.put(DEVICEFIELDS.BAUDRATE, "5");  
		configSetting.put(DEVICEFIELDS.DATABITS, "5");
		configSetting.put(DEVICEFIELDS.PRINTCOMMAND, "Print");
		configSetting.put(DEVICEFIELDS.HANDSHAKE, HANDSHAKEDDLVAL.REQUESTTOSEND);
		configSetting.put(DEVICEFIELDS.PARITY, PARITYDDLVAL.EVEN);
		configSetting.put(DEVICEFIELDS.STOPBITS, STOPBITSDDLVAL.ONEPONTFIVE);

		if(!dvp.setDeviceFields(configSetting)) { 
			TCGFailureMsg = "Could NOT set the configuration Device" + configSetting;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		if(!dvp.addDevice()) {
			TCGFailureMsg = "Could NOT add the device" ;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

	}	


	@Test(description="Verify Copy device functionality") 
	public void TestCase_34007() throws Exception{ 

		DevicesPage dvp = new DevicesPage(driver);

		boolean copyDevice = dvp.copyDevice(deviceName);
		TestValidation.IsTrue(copyDevice, 
				"Able to Copy device " + copyDevice, 
				"Failed to Copy device:" +copyDevice); 

		String copyDeviceName = deviceName +  " 1" ;
		WebElement DeviceName = controlActions.perform_GetElementByXPath(DevicesPageLocators.DEVICE_NAME, 
				"DEVICENAME", copyDeviceName);
		boolean isDeviceCopied = controlActions.isElementDisplay(DeviceName);
		TestValidation.IsTrue(isDeviceCopied, 
				"Able to view Copy device in Device list " + isDeviceCopied, 
				"Failed to view Copy device in Device list" +isDeviceCopied);
	}


	@Test(description="Verify Update device functionality") 
	public void TestCase_34008() throws Exception{ 

		DevicesPage dvp = new DevicesPage(driver);

		HashMap<String,String> updateSetting = new HashMap<String, String>();
		updateSetting.put(DEVICEFIELDS.PRINTCOMMAND, printComment1);
		boolean updateSettng = dvp.setDeviceFields(updateSetting);
		TestValidation.IsTrue(updateSettng, 
				"Field " + DEVICEFIELDS.PRINTCOMMAND + " has been UPDATED to" + printComment1 + " for device " + deviceName, 
				"Failed to UPDATE device:" +updateSettng); 

		boolean updateDevice = dvp.updateDevice();
		TestValidation.IsTrue(updateDevice, 
				"Able to UPDATE device " + updateDevice, 
				"Failed to UPDATE device:" +updateDevice); 

	}


	@AfterClass(alwaysRun = true)
	public void closeBrowser() throws InterruptedException {
		driver.close();
	}

}
