package com.project.safetychain.pcapp.pageobjects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.project.utilities.ControlActions_PCApp;

import io.appium.java_client.windows.WindowsDriver;

public class DeviceManagementScreen extends CommonScreen{

	WindowsDriver<WebElement> PCAppDriver;
	WebDriverWait wait;
	ControlActions_PCApp desktopControlActions;

	public DeviceManagementScreen(WindowsDriver<WebElement> driver) {
		super(driver);
		this.PCAppDriver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 30);
		desktopControlActions = new ControlActions_PCApp(this.PCAppDriver);
	}

	@FindBy(xpath = DeviceManagementScreenLocators.ADD_DEVICES_BTN) 
	public WebElement AddDeviceBtn;

	@FindBy(xpath = DeviceManagementScreenLocators.SEARCH_DEVICE_INP) 
	public WebElement SearchDeviceInp;

	@FindBy(xpath = DeviceManagementScreenLocators.SELECT_DEVICE_CHK) 
	public WebElement SelectDeviceChk;

	@FindBy(xpath = DeviceManagementScreenLocators.DOWNLOAD_DEVICES_BTN) 
	public WebElement DownloadBtn;

	@FindBy(xpath = DeviceManagementScreenLocators.DOWNLOADED_DEVICE) 
	public WebElement DownloadDeviceName;

	@FindBy(xpath = DeviceManagementScreenLocators.DELETE_DEVICE_BTN) 
	public WebElement DownloadDeviceDeleteBtn;

	@FindBy(xpath = DeviceManagementScreenLocators.NO_DEVICES_LBL) 
	public WebElement NoDevicesLbl;

	@FindBy(xpath = DeviceManagementScreenLocators.CANCEL_BTN) 
	public WebElement CancelBtn;

	/**
	 * This method is used to download the device
	 * @author pashine_a
	 * @param deviceName
	 * @return boolean
	 */
	public boolean downloadDevice(String deviceName) {
		List<WebElement> elements;
		try {
			desktopControlActions.click(AddDeviceBtn);
			threadsleep(2000);
			desktopControlActions.waitForElementToBeVisisble(DownloadBtn);
			threadsleep(2000);

			elements = desktopControlActions.getListOfElements(DeviceManagementScreenLocators.SEARCH_DEVICE_INP);
			desktopControlActions.sendKeys(elements.get(elements.size()-1), deviceName);
			try {
				desktopControlActions.click(SelectDeviceChk);
			}catch(Exception e){
				desktopControlActions.click(CancelBtn);
				threadsleep(2000);
				logError("Device not found - '"+deviceName+"' - "+ e.getMessage());
				return false;
			}
			desktopControlActions.click(DownloadBtn);
			threadsleep(2000);
			logInfo("Downloaded device - "+deviceName);
			return true;
		}catch(Exception e) {
			logError("Failed to download device - '"+deviceName+"' - "+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to verify downloaded device
	 * @author pashine_a
	 * @param deviceName
	 * @return boolean
	 */
	public boolean verifyDownloadedDeviceAvailability(String deviceName) {
		String downloadedDevice;
		try {
			desktopControlActions.sendKeys(SearchDeviceInp, deviceName);
			threadsleep(2000);
			downloadedDevice = desktopControlActions.getDynamicXPath(DeviceManagementScreenLocators.DOWNLOADED_DEVICE, "DEVICE_NAME", deviceName);
			if(!desktopControlActions.isElementDisplayed(downloadedDevice)) {
				logInfo("Downloaded device name is not shown");
				return false;
			}
			if(!desktopControlActions.isElementDisplayed(DeviceManagementScreenLocators.DELETE_DEVICE_BTN)) {
				logInfo("Downloaded device delete button is not shown");
				return false;
			}
			logInfo("Found device - "+deviceName);
			return true;
		}catch(Exception e) {
			logError("Failed to get device - '"+deviceName+"' - "+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to verify no devices
	 * @author choubey_a
	 * @param none
	 * @return boolean
	 */
	public boolean verifyNoDevices() {
		try {
			if(!desktopControlActions.isElementDisplayed(NoDevicesLbl)) {
				logError("Could not verify no devices");
				return false;
			}
			logInfo("Verified no devices");
			return true;
		}catch(Exception e) {
			logError("Could not verify no devices-"+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to perform double click on 'Device Management' Tab
	 * @author pashine_a
	 * @param null
	 * @return boolean. If nothing is impacting on double click then returns TRUE
	 */
	public boolean doubleClickDeviceManagement() {
		try {
			desktopControlActions.doubleClick(DeviceManagementMnu);
			if(!desktopControlActions.isElementDisplayed(AddDeviceBtn)) {
				logError("Failed to double click on Device Management");
				return false;
			}
			logInfo("Multiple clicks performed on 'Device Management' Tab");
			return true;
		}catch(Exception e) {
			logError("Failed to double click on Device Management - "+ e.getMessage());
			return false;
		}	
	}

}
