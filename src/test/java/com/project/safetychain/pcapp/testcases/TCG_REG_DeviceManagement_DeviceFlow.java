package com.project.safetychain.pcapp.testcases;

import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.project.safetychain.api.models.support.TCG_DeviceManagementFlows_Wrapper;
import com.project.safetychain.pcapp.pageobjects.CommonScreen;
import com.project.safetychain.pcapp.pageobjects.DeviceManagementScreen;
import com.project.safetychain.pcapp.pageobjects.LoginScreen;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.utilities.ApiUtils;
import com.project.utilities.CommonMethods;

public class TCG_REG_DeviceManagement_DeviceFlow extends TestBase{

	ApiUtils apiUtils = null;
	TCG_DeviceManagementFlows_Wrapper deviceCreationWrapper;

	String deviceName1 = null;

	CommonScreen commonScreen;
	LoginScreen loginScreen;
	DeviceManagementScreen deviceScreen;

	@BeforeClass(alwaysRun = true)
	public void groupInit() {
		try {
			apiUtils = new ApiUtils();

			deviceCreationWrapper = new TCG_DeviceManagementFlows_Wrapper();

			deviceName1 = CommonMethods.dynamicText("PCAppDevice1");

			boolean createDevice = deviceCreationWrapper.createDevice(deviceName1);
			if(!createDevice) {
				TCGFailureMsg = "COULD not create device";
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}

			PCAppDriver = launchPCApp();

			loginScreen = new LoginScreen(PCAppDriver);

			commonScreen = loginScreen.Login(pcAppTenantname, pcAppUsername,pcAppPassword);
			if(commonScreen.error) {
				TCGFailureMsg = "COULD not login to the application";
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}

		}catch(Exception e) {
			TCGFailureMsg = "Exception found while performing group init action";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
	}



	@Test (description="Device Management || Verify Add Devices functionality")
	public void TestCase_33599() {

		deviceScreen = commonScreen.selectDeviceManagement();
		TestValidation.IsFalse(deviceScreen.error, "SELECTED 'Device Management' tab", 
				"COULD NOT 'Device Management' tab");

	}
	
	@AfterClass(alwaysRun = true)
	public void closeBrowser() throws InterruptedException {
		PCAppDriver.close();
	}
}
