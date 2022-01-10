package com.project.safetychain.apiproject.testcases;

import org.json.simple.parser.ParseException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.project.safetychain.apiproject.models.Auth;
import com.project.safetychain.apiproject.models.Devices;
import com.project.safetychain.apiproject.models.Devices.DeviceParams;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.utilities.ApiUtils;
import com.project.utilities.CommonMethods;
import com.project.utilities.JSONUtils;

public class TCG_DevicesFlow extends TestBase{

	Auth auth = null;
	ApiUtils apiUtils = null;
	Devices device = null;
	JSONUtils jsnUtils = null;

	String deviceId;
	DeviceParams params,params1;

	String deviceName1 = CommonMethods.dynamicText("NewDevice1");
	String deviceName2 = CommonMethods.dynamicText("DisableDevice1");
	String copyDeviceName = deviceName1+" 1";

	@BeforeClass(alwaysRun = true)
	public void groupInit() {

		auth = new Auth();
		auth.setAccessToken();
		device = new Devices();
		jsnUtils = new JSONUtils();
		apiUtils = new ApiUtils();

	}


	@Test(description = "Create Device")
	public void Create_Device() throws InterruptedException, ParseException {

		params = new DeviceParams();
		params.BAUDRATE = "1200";
		params.BRAND = "WeighTronix QC3265";
		params.DATABITS = "8";
		params.DEVICENAME = deviceName1;
		params.DEVICETYPE = "Scale";
		params.HANDSHAKE = "None";
		params.PARITY = "None";
		params.PRINTCOMMAND = "Print";
		params.STOPBITS = "One";

		boolean create = device.createDevice(params);
		TestValidation.IsTrue(create, 
				"Device CREATED - "+deviceName1, 
				"FAILED to create device - "+deviceName1);

		deviceId = device.getDevices(deviceName1);

		boolean validate = device.getDevice(deviceId, deviceName1);
		TestValidation.IsTrue(validate, 
				"Device VALIDATED - "+deviceName1, 
				"FAILED to validate device - "+deviceName1);
	}

	@Test(description = "Copy Device",  dependsOnMethods = { "Create_Device" })
	public void Copy_Device() throws InterruptedException, ParseException {

		params1 = new DeviceParams();
		params1.BAUDRATE = "1200";
		params1.BRAND = "WeighTronix QC3265";
		params1.DATABITS = "8";
		params1.DEVICENAME = copyDeviceName;
		params1.DEVICETYPE = "Scale";
		params1.HANDSHAKE = "None";
		params1.PARITY = "None";
		params1.PRINTCOMMAND = "Print";
		params1.STOPBITS = "One";

		String copy = device.copyDevice(deviceId,copyDeviceName);
		TestValidation.IsTrue(copy.equals(deviceId), 
				"Device COPY is working with Device - "+deviceName1, 
				"FAILED to copy device - "+deviceName1);

		boolean save = device.saveDevice(params1);
		TestValidation.IsTrue(save, 
				"COPIED Device SAVED - "+copyDeviceName, 
				"FAILED to saved copied device - "+copyDeviceName);

		String DeviceId1 = device.getDevices(copyDeviceName);

		boolean validate = device.getDevice(DeviceId1, copyDeviceName);
		TestValidation.IsTrue(validate, 
				"Copied Device VALIDATED - "+copyDeviceName, 
				"FAILED to validate copied device - "+copyDeviceName);

	}

	@Test(description = "Disable Device")
	public void Disable_Device() throws InterruptedException, ParseException {

		params = new DeviceParams();
		params.BAUDRATE = "1200";
		params.BRAND = "WeighTronix QC3265";
		params.DATABITS = "8";
		params.DEVICENAME = deviceName2;
		params.DEVICETYPE = "Scale";
		params.HANDSHAKE = "None";
		params.PARITY = "None";
		params.PRINTCOMMAND = "Print";
		params.STOPBITS = "One";	

		boolean create = device.createDevice(params);
		TestValidation.IsTrue(create, 
				"Device CREATED - "+deviceName2, 
				"FAILED to create device - "+deviceName2);

		String DeviceId = device.getDevices(deviceName2);

		params1 = new DeviceParams();
		params1.BAUDRATE = "1200";
		params1.BRAND = "WeighTronix QC3265";
		params1.DATABITS = "8";
		params1.DEVICENAME = deviceName2;
		params1.DEVICETYPE = "Scale";
		params1.HANDSHAKE = "None";
		params1.PARITY = "None";
		params1.PRINTCOMMAND = "Print";
		params1.STOPBITS = "One";
		params1.ENABLE = "false";
		params1.DEVICEID = DeviceId;

		boolean save = device.savedisableDevice(params1);
		TestValidation.IsTrue(save, 
				"DISABLED Device - "+deviceName2, 
				"FAILED to disbale device - "+deviceName2);

		String DeviceId1 = device.getDevices(deviceName2);

		boolean validate = device.getDevice(DeviceId1, deviceName2);
		TestValidation.IsTrue(validate, 
				"VALIDATED disabled device- "+deviceName2, 
				"FAILED to validate disabled device - "+deviceName2);	

	}

}
