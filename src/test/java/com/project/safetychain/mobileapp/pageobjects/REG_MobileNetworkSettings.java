package com.project.safetychain.mobileapp.pageobjects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
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
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.connection.ConnectionState;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class REG_MobileNetworkSettings extends TestBase {

	AndroidDriver<MobileElement> androidDriver;
	WebDriverWait wait;
	static ControlActions_MobileApp mobControlActions;

	public REG_MobileNetworkSettings(AndroidDriver<MobileElement> driver) {
		this.androidDriver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		mobControlActions = new ControlActions_MobileApp(this.androidDriver);
	}

	@AndroidFindBy(id = HomePageLocators.menuBtn)
	public WebElement MenuBtn;

	
	/**This method is used to turn off Mobile Data
	 * @author ahmed_tw
	 * @param androidDriver : AndroidDriver<MobileElement> 
	 * @return [boolean] : True after turning off Mobile Data, false if failed to do so. 
	 */
	public boolean turnOffMobileData(AndroidDriver<MobileElement> androidDriver) {
		ConnectionState state = null ;
		try {
			state = androidDriver.getConnection();
		    if(state.isDataEnabled()) {
		    	androidDriver.toggleData();
		    	Thread.sleep(3000);
		    	state = androidDriver.getConnection();
		    	if(!state.isDataEnabled()) {
		    		logInfo("Disabled/Turned OFF Mobile Data successfully");
		    		return true;
		    	}
		    }else if(!state.isDataEnabled()) {
		    	logInfo("Mobile Data is already Disabled/OFF");
		    	return true;
		    }
		    logInfo("Failed to turn OFF Mobile Data ");	
			return false;
		} catch (Exception e) {
			logError("Could not turn OFF the Mobile Data "+e.getMessage());
			return false;
		}
	}
	
	/**This method is used to turn on Mobile Data
	 * @author ahmed_tw
	 * @param androidDriver : AndroidDriver<MobileElement> 
	 * @return [boolean] : True after turning on Mobile Data, false if failed to do so. 
	 */
	public boolean turnOnMobileData(AndroidDriver<MobileElement> androidDriver) {
		ConnectionState state = null ;
		try {
			state = androidDriver.getConnection();
		    if(!state.isDataEnabled()) {
		    	androidDriver.toggleData();
		    	Thread.sleep(3000);
		    	state = androidDriver.getConnection();
		    	if(state.isDataEnabled()) {
		    		logInfo("Enabled/Turned ON Mobile Data successfully");
		    		return true;
		    	}
		    }else if(state.isDataEnabled()) {
		    	logInfo("Mobile Data is already Eabled/ON");
		    	return true;
		    }
		    logInfo("Failed to turn ON Mobile Data");	
			return false;
		} catch (Exception e) {
			logError("Could not turn ON the Mobile Data "+e.getMessage());
			return false;
		}
	}
	
	/**This method is used to turn on Mobile WiFi
	 * @author ahmed_tw
	 * @param androidDriver : AndroidDriver<MobileElement> 
	 * @return [boolean] : True after turning off Mobile WiFi, false if failed to do so. 
	 */
	public boolean turnOffMobileWifi(AndroidDriver<MobileElement> androidDriver) {
		ConnectionState state = null ;
		try {
			state = androidDriver.getConnection();
		    if(state.isWiFiEnabled()) {
		    	androidDriver.toggleWifi();
		    	Thread.sleep(3000);
		    	state = androidDriver.getConnection();
		    	if(!state.isWiFiEnabled()) {
		    		logInfo("Disabled/Turned OFF WiFi successfully");
		    		return true;
		    	}
		    }else if(!state.isWiFiEnabled()) {
		    	logInfo("WiFi is already Disabled/OFF");
		    	return true;
		    }
		    logInfo("Failed to turn OFF WiFi ");	
			return false;
		} catch (Exception e) {
			logError("Could not turn OFF  WiFi "+e.getMessage());
			return false;
		}
	}
	
	/**This method is used to turn on Mobile WiFi
	 * @author ahmed_tw
	 * @param androidDriver : AndroidDriver<MobileElement> 
	 * @return [boolean] : True after turning on Mobile WiFi, false if failed to do so. 
	 */
	public boolean turnOnMobileWifi(AndroidDriver<MobileElement> androidDriver) {
		ConnectionState state = null ;
		try {
			state = androidDriver.getConnection();
		    if(!state.isWiFiEnabled()) {
		    	androidDriver.toggleWifi();
		    	Thread.sleep(3000);
		    	state = androidDriver.getConnection();
		    	if(state.isWiFiEnabled()) {
		    		logInfo("Enabled/Turned ON WiFi successfully");
		    		return true;
		    	}
		    }else if(state.isWiFiEnabled()) {
		    	logInfo("WiFi is already Eabled/ON");
		    	return true;
		    }
		    logInfo("Failed to turn ON WiFi ");	
			return false;
		} catch (Exception e) {
			logError("Could not turn ON WiFi "+e.getMessage());
			return false;
		}
	}
	
	
	/**This method is used to turn on Mobile Data and Wifi both
	 * @author ahmed_tw
	 * @param androidDriver : AndroidDriver<MobileElement> 
	 * @return [boolean] : True after turning on Mobile Data and Wifi both, false if failed to do so. 
	 */
	public boolean turnOnMobileDataAndWifi(AndroidDriver<MobileElement> androidDriver) {
		try {
			boolean dataOn = turnOnMobileData(androidDriver);
			boolean wifiOn = turnOnMobileWifi(androidDriver);
			
			if(dataOn && wifiOn) {
				logInfo("Turned ON Mobile Data and WiFi Successfully");
				return true;
			}else {
				logInfo("Failed to turn ON Mobile Data and WiFi");
				return false;
			}
		} catch (Exception e) {
			logError("Could not turn ON Mobile Data and WiFi "+e.getMessage());
			return false;
		}
	}

	/**This method is used to turn off Mobile Data and WiFi both
	 * @author ahmed_tw
	 * @param androidDriver : AndroidDriver<MobileElement> 
	 * @return [boolean] : True after turning off Mobile Data and WiFi both, false if failed to do so. 
	 */
	public boolean turnOffMobileDataAndWifi(AndroidDriver<MobileElement> androidDriver) {
		try {
			boolean dataOff = turnOffMobileData(androidDriver);
			boolean wifiOff = turnOffMobileWifi(androidDriver);
			
			if(dataOff && wifiOff) {
				logInfo("Turned OFF Mobile Data and Wifi Successfully");
				return true;
			}else {
				logInfo("Failed to turn OFF Mobile Data and WiFi");
				return false;
			}
		} catch (Exception e) {
			logError("Could not turn OFF Mobile Data and WiFi "+e.getMessage());
			return false;
		}
	}
	
	
	
}
