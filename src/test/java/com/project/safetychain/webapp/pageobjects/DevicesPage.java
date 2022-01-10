package com.project.safetychain.webapp.pageobjects;

import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.project.utilities.ControlActions;

public class DevicesPage extends CommonPage{
	WebDriver driver;
	WebDriverWait wait;
	ControlActions controlActions;

	public DevicesPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 40);
		controlActions = new ControlActions(driver);

	}

	/**
	 * Page Objects
	 */
	@FindBy(xpath = DevicesPageLocators.ADD_NEW_DEVICE_BTN)
	public WebElement AddNewDeviceBtn;

	@FindBy(xpath = DevicesPageLocators.DEVICE_NAME_TXT)
	public WebElement DeviceNameTxt ;

	@FindBy(xpath = DevicesPageLocators.ENABLE_DEVICE_BTN)
	public WebElement EnableDeviceBtn;

	@FindBy(css = DevicesPageLocators.DEVICE_TYPE_DDL)
	public WebElement DeviceTypeDdl;

	@FindBy(xpath = DevicesPageLocators.DEVICE_TYPE_DRD)
	public WebElement DeviceTypeDrd;

	@FindBy(xpath = DevicesPageLocators.BRAND_TXT)
	public WebElement BrandTxt;

	@FindBy(xpath = DevicesPageLocators.BAUDRATE_DRD)
	public WebElement BaudRateDrd;

	@FindBy(xpath = DevicesPageLocators.DATABITS_DRD)
	public WebElement DataBitsDrd;

	@FindBy(xpath = DevicesPageLocators.STOPBITS_DRD)
	public WebElement StopBitsDrd;

	@FindBy(xpath = DevicesPageLocators.PARITY_DRD)
	public WebElement ParityDrd;

	@FindBy(xpath = DevicesPageLocators.PRINT_COMMAND_TXT)
	public WebElement PrintCommandTxt;

	@FindBy(xpath = DevicesPageLocators.HANDSHAKE_DRD)
	public WebElement HandShakeDrd;

	@FindBy(xpath = DevicesPageLocators.ADD_BTN)
	public WebElement AddBtn;

	@FindBy(xpath = DevicesPageLocators.COPY_BTN)
	public WebElement CopyBtn;

	@FindBy(xpath = DevicesPageLocators.UPDATE_BTN)
	public WebElement UpdateBtn;

	@FindBy(xpath = DevicesPageLocators.CLEAR_BTN)
	public WebElement ClearBtn;


	/**
	 * This method is used to click on ADD NEW DEVICE.
	 * @author dahale_p
	 * @param none
	 * @return boolean This returns true if ADD NEW DEVICE button is clicked.
	 */
	public boolean clickAddNewDeviceBtn() { 
		try {
			controlActions.WaitforelementToBeClickable(AddNewDeviceBtn);
			controlActions.clickOnElement(AddNewDeviceBtn);
			logInfo("Clicked on ADD NEW DEVICE button");
			Sync();
			return true;
		}
		catch(Exception e) {
			logError("Failed to click on ADD NEW DEVICE button "
					+ e.getMessage());
			return false;
		}	

	}

	/**
	 * This method is used to set New Verification Name
	 * @author dahale_p
	 * @param deviceName
	 * @return boolean This returns boolean true after setting value to text box
	 */
	public boolean setDeviceName(String deviceName) {
		try {
			controlActions.actionClearTextBox(DeviceNameTxt);
			controlActions.WaitforelementToBeClickable(DeviceNameTxt);		
			controlActions.clickOnElement(DeviceNameTxt);
			controlActions.sendKeys(DeviceNameTxt, deviceName);
			threadsleep(5000);
			Sync();
			logInfo("The Device name : " + deviceName );
			return true;
		}
		catch(Exception e) {
			logError("Failed to set value for field " + deviceName + " :"
					+ e.getMessage());
			return false;
		}	
	}


	/**
	 * This method is used to enable device 
	 * @author dahale_p
	 * @param 0
	 * @return boolean This returns boolean true after Clicking ENABLE DEVICE button
	 */
	public boolean clickEnableDevice() {
		try {
			controlActions.WaitforelementToBeClickable(EnableDeviceBtn);		
			controlActions.clickOnElement(EnableDeviceBtn);
			threadsleep(10000);
			Sync();
			logInfo("Device deviceName  is enabled successfully ");
			return true;
		}
		catch(Exception e) {
			logError("Failed to click element  EnableDevice " 
					+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to select device type
	 * @author dahale_p
	 * @param 0
	 * @return boolean This returns boolean true after Clicking Select DEVICE Type
	 */
	public boolean selectDeviceType() {
		String selector;
		String deviceType =null;
		try {
			selector = DevicesPageLocators.DEVICE_TYPE_DDL;
			deviceType  = DevicesPageLocators.DEVICE_TYPE_DRD;
			Sync();
			if(!controlActions.setKendoDropDownValue(driver, selector, deviceType)){
				return false;
			}

			Sync();
			return true;

		}
		catch(Exception e) {
			logError("Failed to click element DeviceType :"
					+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to Save and verify added Device
	 * @author dahale_p
	 * @param 0
	 * @return boolean This returns boolean true after Clicking ADD button
	 */
	public boolean addDevice() {

		try {
			controlActions.WaitforelementToBeClickable(AddBtn);	
			Sync();
			controlActions.clickOnElement(AddBtn);
			Sync();
			logInfo(AddBtn +" is Clicked and Device is added successfully ");


			return true;
		}
		catch(Exception e) {
			logError(" Failed to add Device "+ " :"
					+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to Copy added Device
	 * @author dahale_p
	 * @param 0
	 * @return boolean This returns boolean true after Clicking COPY button
	 */
	public boolean copyDevice(String deviceName) {

		try {
			controlActions.WaitforelementToBeClickable(CopyBtn);		
			controlActions.clickOnElement(CopyBtn);
			if(!addDevice()) {
				return false;
			}
			Sync();
			logInfo(CopyBtn +" is Clicked and Device is copied successfully ");

			return true;
		}
		catch(Exception e) {
			logError(" Failed to add Device "+ " :"
					+ e.getMessage());
			return false;
		}	
	}


	/**
	 * This method is used to Update added Device
	 * @author dahale_p
	 * @param 0
	 * @return boolean This returns boolean true after Clicking Update button
	 */
	public boolean updateDevice() {

		try {

			controlActions.WaitforelementToBeClickable(UpdateBtn);		
			controlActions.clickOnElement(UpdateBtn);

			logInfo(UpdateBtn +" is Clicked and Device is updated successfully ");
			Sync();

			String stateOfUpdate= UpdateBtn.getAttribute("class");
			if(stateOfUpdate.contains("disable")) {
				logInfo("Updated Device is visible successfully ");

				return true;
			}
			return false;
		}
		catch(Exception e) {
			logError(" Failed to add Device " + " :"
					+ e.getMessage());
			return false;
		}	
	}

	public boolean setDeviceFields(HashMap<String, String> addSettings){
		String selector = null;
		String configSettingsDrd;
		WebElement ConfigSettingsTxt ;
		try {	
			for (Map.Entry<String, String> entry : addSettings.entrySet()) {
				String labelName = entry.getKey();
				String settingValue = entry.getValue();

				switch(labelName) {
				case DEVICEFIELDS.BAUDRATE:
					ConfigSettingsTxt = controlActions.perform_GetElementByXPath(DevicesPageLocators.CONFIG_SETTINGS_TXT,
							"CONFIGSETTING",labelName);
					controlActions.WaitforelementToBeClickable(ConfigSettingsTxt);

					controlActions.sendKeys(ConfigSettingsTxt, settingValue);
					break;
				case DEVICEFIELDS.DATABITS:
					ConfigSettingsTxt = controlActions.perform_GetElementByXPath(DevicesPageLocators.CONFIG_SETTINGS_TXT,
							"CONFIGSETTING",labelName);

					controlActions.WaitforelementToBeClickable(ConfigSettingsTxt);
					controlActions.sendKeys(ConfigSettingsTxt, settingValue);
					break;
				case DEVICEFIELDS.PRINTCOMMAND:
					ConfigSettingsTxt = controlActions.perform_GetElementByXPath(DevicesPageLocators.CONFIG_SETTINGS_TXT,
							"CONFIGSETTING",labelName);
					controlActions.WaitforelementToBeClickable(ConfigSettingsTxt);
					controlActions.sendKeys(ConfigSettingsTxt, settingValue);
					break;

				case DEVICEFIELDS.PARITY:
					selector = "div:nth-child(4) > app-common-field-template > div > div > div > kendo-dropdownlist > span";
					configSettingsDrd = controlActions.perform_GetDynamicXPath(DevicesPageLocators.CONFIG_SETTINGS_DRD,
							"CONFIGSETTINGDRD",settingValue);

					if(!controlActions.setKendoDropDownValue(driver, selector, configSettingsDrd)) {
						return false;
					}
					break;
				case DEVICEFIELDS.STOPBITS:
					selector = "div:nth-child(3) > app-common-field-template > div > div > div > kendo-dropdownlist > span";
					configSettingsDrd = controlActions.perform_GetDynamicXPath(DevicesPageLocators.CONFIG_SETTINGS_DRD,
							"CONFIGSETTINGDRD",settingValue);

					if(!controlActions.setKendoDropDownValue(driver, selector, configSettingsDrd)) {
						return false;
					}
					break;
				case DEVICEFIELDS.HANDSHAKE:
					selector = "div:nth-child(6) > app-common-field-template > div > div > div > kendo-dropdownlist > span";
					configSettingsDrd = controlActions.perform_GetDynamicXPath(DevicesPageLocators.CONFIG_SETTINGS_DRD,
							"CONFIGSETTINGDRD",settingValue);
					if(!controlActions.setKendoDropDownValue(driver, selector, configSettingsDrd)) {
						return false;
					}
					break;
				default:
					logError("Invalid Option - " + labelName);
					return false;
				}
				Sync();

			}
			return true;

		}
		catch(Exception e) {
			logError("Failed to click on Save button - "
					+ e.getMessage());
			return false;
		}

	}


	/**
	 * This method is used to create Device name
	 * @author dahale_p
	 * @param device name and printComment 
	 * @return boolean This returns true if save button is clicked successfully
	 */

	public boolean setConnectionSettings(String deviceName,HashMap<String, String> addSettings) {
		try {	

			if(!clickAddNewDeviceBtn()) {
				return false;
			}
			if(!setDeviceName(deviceName)) {
				return false;
			}
			if(!clickEnableDevice()) {
				return false;
			}

			if(!setDeviceFields(addSettings)) {
				return false;
			}
			if(!addDevice()) {
				return false;
			}


			logInfo("Clicked on Save button");
			return true;
		}
		catch(Exception e) {
			logError("Failed to click on Save button - "
					+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to create a new device
	 * @author pashine_a
	 * @param deviceName
	 * @return boolean. This returns true if new device added successfully
	 */
	public boolean addNewDevice(String deviceName){
		try {
			
			HashMap<String,String> configSetting = new HashMap<String, String>();
			configSetting.put(DEVICEFIELDS.BAUDRATE, "5");  
			configSetting.put(DEVICEFIELDS.DATABITS, "5");
			configSetting.put(DEVICEFIELDS.PRINTCOMMAND, "Print");
			configSetting.put(DEVICEFIELDS.HANDSHAKE, HANDSHAKEDDLVAL.REQUESTTOSEND);
			configSetting.put(DEVICEFIELDS.PARITY, PARITYDDLVAL.EVEN);
			configSetting.put(DEVICEFIELDS.STOPBITS, STOPBITSDDLVAL.ONEPONTFIVE);

			if(clickDevicesMenu().error) {
				logError("Fail to navigate to 'Devices'");
				return false;
			}
			if(!clickAddNewDeviceBtn()) {
				logError("Fail to click on 'Add New Device' button");
				return false;
			}
			if(!setDeviceName(deviceName)) {
				logError("Fail to set device name");
				return false;
			}
			if(!clickEnableDevice()) {
				logError("Fail to enable device");
				return false;
			}
			if(!selectDeviceType()) {
				logError("Fail to select device type");
				return false;
			}
			if(!setDeviceFields(configSetting)) { 
				logError("Fail to set device field values");
				return false;
			}
			if(!addDevice()) {
				logError("Fail to add device");
				return false;
			}
			
			logInfo("Added new device - "+deviceName);
			return true;
		}catch(Exception e) {
			logError("Failed to add new device - "+ e.getMessage());
			return false;
		}
	}
	
	public static class DEVICEFIELDS{
		public static final String BAUDRATE = "BAUDRATE";
		public static final String DATABITS = "DATABITS";
		public static final String PRINTCOMMAND = "PRINTCOMMAND";
		public static final String PARITY   =   "PARITY";
		public static final String STOPBITS = "STOPBITS";
		public static final String HANDSHAKE = "HANDSHAKE";
	}

	public static class PARITYDDLVAL{
		public static final String NONE = "None";
		public static final String EVEN = "Even";
		public static final String MARK = "Mark";
		public static final String ODD = "Odd";
		public static final String SPACE = "Space";

	}

	public static class STOPBITSDDLVAL{
		public static final String NONE = "None";
		public static final String ONE = "One";
		public static final String ONEPONTFIVE = "OnePointFive";
		public static final String TWO = "Two";
	}
	public static class HANDSHAKEDDLVAL{
		public static final String NONE = "None";
		public static final String REQUESTTOSEND = "RequestToSend";
		public static final String XONXOFF = "XOnXOff";
		public static final String REQUESTTOSENDXONXOFF = "RequestToSendXOnXOff";

	}
}



