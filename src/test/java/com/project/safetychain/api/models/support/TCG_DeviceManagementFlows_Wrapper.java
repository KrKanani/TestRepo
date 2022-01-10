package com.project.safetychain.api.models.support;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.project.safetychain.api.models.Auth;
import com.project.safetychain.api.models.UsersManagement;
import com.project.safetychain.api.models.DeviceManagement;
import com.project.safetychain.api.models.UsersManagement.UM_SingleDataResponse;
import com.project.safetychain.api.models.support.Support.DeviceParams;
import com.project.testbase.TestBase;
import com.project.utilities.ApiUtils;
import com.project.utilities.ApiUtils.METHOD_TYPE;

import io.restassured.response.Response;

public class TCG_DeviceManagementFlows_Wrapper extends TestBase {

	Auth auth = null;
	ApiUtils apiUtils = null;
	UsersManagement usersManagement = null;
	DeviceManagement deviceManagement = null;

	public TCG_DeviceManagementFlows_Wrapper() {
		usersManagement = new UsersManagement();
		deviceManagement = new DeviceManagement();
		auth = new Auth();
		auth.setAccessToken();
	}

	/**
	 * This method is used to get user details
	 * @author pashine_a
	 * @param username
	 * @return String. It will return the user details - "<First Name> <SPACE> <Last Name>"
	 */
	public String getUserInfoByUsername(String username) {
		String detail = null;
		Gson gson = new GsonBuilder().create();
		apiUtils = new ApiUtils();

		try {

			Map<String, String> headers = new HashMap<String, String>();
			headers.put("Content-Type", "application/json");
			headers.put("Accept", "application/json, text/plain, /");
			headers.put("Authorization", "Bearer " + accessToken);

			String usersJson = "{\r\n" + "	\"filter\": {\r\n" + "		\"filters\": [{\r\n"
					+ "			\"field\": \"Username\",\r\n" + "			\"operator\": \"contains\",\r\n"
					+ "			\"value\": \"" + username + "\"\r\n" + "		}],\r\n"
					+ "		\"logic\": \"and\"\r\n" + "	},\r\n" + "	\"group\": [],\r\n" + "	\"skip\": 0,\r\n"
					+ "	\"sort\": [],\r\n" + "	\"take\": 100,\r\n" + "	\"Parameters\": {\r\n"
					+ "		\"LocationName\": \"\",\r\n" + "		\"SearchString\": null,\r\n"
					+ "		\"Columns\": [\"Status\",\r\n" + "		\"Username\",\r\n" + "		\"FirstName\",\r\n"
					+ "		\"LastName\",\r\n" + "		\"Email\",\r\n" + "		\"TimeZone\"]\r\n" + "	},\r\n"
					+ "	\"pageSize\": 100\r\n" + "}";

			Response response = apiUtils.submitRequest(METHOD_TYPE.POST, usersJson, headers, usersManagement.UsersUrl);
			UM_SingleDataResponse sdr = gson.fromJson(response.asString(), UM_SingleDataResponse.class);
			if (sdr.Data.Count == 1) {
				detail = sdr.Data.Rows.get(0).FirstName;
				detail = detail + " " + sdr.Data.Rows.get(0).LastName;
				return detail;
			} else {
				return detail;
			}
		} catch (Exception e) {
			logError("Failed to get details for username - '" + username +"' - "+ e.getMessage());
			return detail;
		}
	}

	/**
	 * This method is used to add new device with default details
	 * @author pashine_a
	 * @param deviceName
	 * @return boolean. It will return the boolean result true if new device is been added
	 */
	public boolean createDevice(String deviceName) throws InterruptedException{
		try {
			boolean saveDevice = deviceManagement.saveDevice(deviceName);
			threadsleep(3000);
			if(saveDevice) {
				logInfo("Added device - "+deviceName);
			}else {
				logError("Failed to add device - "+deviceName);
			}
			return true;
		}catch(Exception e){
			logError("Error while adding new device - "+deviceName);
			return false;
		}
	}
	
	/** INCOMPLETE  - PAYLOAD UPDATE IS REQUIRED
	 * This method is used to add/update device with provided details
	 * @author pashine_a
	 * @param deviceDetails (Device configuration details)
	 * @return boolean. It will return the boolean result true if new device is been added/updated
	 */
	public boolean addUpdateDevice(DeviceParams deviceDetails) throws InterruptedException{
		try {
			deviceDetails.UserDetails = getUserInfoByUsername(username);
			boolean saveDevice = deviceManagement.saveDevice(deviceDetails);
			threadsleep(3000);
			if(saveDevice) {
				logInfo("Added/Updated device - "+deviceDetails.DeviceName);
			}else {
				logError("Failed to add/update device - "+deviceDetails.DeviceName);
			}
			return true;
		}catch(Exception e){
			logError("Error while adding/updating device - "+deviceDetails.DeviceName);
			return false;
		}
	}

}
