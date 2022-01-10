package com.project.safetychain.apiproject.models;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.project.safetychain.apiproject.models.Auth;
import com.project.utilities.ApiUtils;
import com.project.utilities.ApiUtils.METHOD_TYPE;
import io.restassured.response.Response;

public class Devices extends Auth{

	public String GetDevicesUrl = baseURI + DevicesURLs.GET_ALL_DEVICES_URL;
	public String GetAllDeviceTypesUrl = baseURI + DevicesURLs.GET_ALL_DEVICE_TYPES_URL;
	public String GetDeviceDetailsUrl = baseURI + DevicesURLs.GET_DEVICE_DETAILS_URL;
	public String AddDeviceUrl = baseURI + DevicesURLs.ADD_DEVICE_URL;
	public String CopyDeviceUrl =  baseURI + DevicesURLs.COPY_DEVICE_URL;

	public String DeviceId = null;
	ApiUtils apiUtils = new ApiUtils();
	Gson gson = new GsonBuilder().create();
	String createdDeviceId,savedDeviceId;	

	/**
	 * This method is used to create device with provided details
	 * @author choubey_a
	 * @param deviceDetails (Device configuration details)
	 * @return boolean. It will return the boolean result true if new device is been added
	 */
	public boolean createDevice(DeviceParams deviceDetails) {
		try {

			apiUtils = new ApiUtils();
			Gson gson = new GsonBuilder().create();
			Map<String, String> headers = new HashMap<String, String>();
			headers.put("Content-Type", "application/json");
			headers.put("Accept", "application/json, text/plain, /");
			headers.put("Authorization", "Bearer " + accessToken);

			String createDeviceJson = 			
					"{\r\n" + 
							"   \"DeviceType\":{\r\n" + 
							"      \"DefaultConfiguration\":[\r\n" + 
							"         {\r\n" + 
							"            \"Name\":\"BaudRate\",\r\n" + 
							"            \"DisplayName\":\"Baud Rate\",\r\n" + 
							"            \"ControlType\":\"Numeric\",\r\n" + 
							"            \"DataType\":\"Int\",\r\n" + 
							"            \"Value\":\"\",\r\n" + 
							"            \"Source\":null,\r\n" + 
							"            \"IsRequired\":true\r\n" + 
							"         },\r\n" + 
							"         {\r\n" + 
							"            \"Name\":\"DataBits\",\r\n" + 
							"            \"DisplayName\":\"Data Bits\",\r\n" + 
							"            \"ControlType\":\"Numeric\",\r\n" + 
							"            \"DataType\":\"Int\",\r\n" + 
							"            \"Value\":\"\",\r\n" + 
							"            \"Source\":null,\r\n" + 
							"            \"IsRequired\":true\r\n" + 
							"         },\r\n" + 
							"         {\r\n" + 
							"            \"Name\":\"StopBits\",\r\n" + 
							"            \"DisplayName\":\"Stop Bits\",\r\n" + 
							"            \"ControlType\":\"Select\",\r\n" + 
							"            \"DataType\":\"Text\",\r\n" + 
							"            \"Value\":\"\",\r\n" + 
							"            \"Source\":[\r\n" + 
							"               \"None\",\r\n" + 
							"               \"One\",\r\n" + 
							"               \"OnePointFive\",\r\n" + 
							"               \"Two\"\r\n" + 
							"            ],\r\n" + 
							"            \"IsRequired\":true\r\n" + 
							"         },\r\n" + 
							"         {\r\n" + 
							"            \"Name\":\"Parity\",\r\n" + 
							"            \"DisplayName\":\"Parity\",\r\n" + 
							"            \"ControlType\":\"Select\",\r\n" + 
							"            \"DataType\":\"Text\",\r\n" + 
							"            \"Value\":\"\",\r\n" + 
							"            \"Source\":[\r\n" + 
							"               \"None\",\r\n" + 
							"               \"Even\",\r\n" + 
							"               \"Mark\",\r\n" + 
							"               \"Odd\",\r\n" + 
							"               \"Space\"\r\n" + 
							"            ],\r\n" + 
							"            \"IsRequired\":true\r\n" + 
							"         },\r\n" + 
							"         {\r\n" + 
							"            \"Name\":\"PrintCommand\",\r\n" + 
							"            \"DisplayName\":\"Print Command\",\r\n" + 
							"            \"ControlType\":\"Text\",\r\n" + 
							"            \"DataType\":\"String\",\r\n" + 
							"            \"Value\":\"\",\r\n" + 
							"            \"Source\":null,\r\n" + 
							"            \"IsRequired\":true\r\n" + 
							"         },\r\n" + 
							"         {\r\n" + 
							"            \"Name\":\"Handshake\",\r\n" + 
							"            \"DisplayName\":\"Handshake\",\r\n" + 
							"            \"ControlType\":\"Select\",\r\n" + 
							"            \"DataType\":\"Text\",\r\n" + 
							"            \"Value\":\"\",\r\n" + 
							"            \"Source\":[\r\n" + 
							"               \"None\",\r\n" + 
							"               \"RequestToSend\",\r\n" + 
							"               \"XOnXOff\",\r\n" + 
							"               \"RequestToSendXOnXOff\"\r\n" + 
							"            ],\r\n" + 
							"            \"IsRequired\":true\r\n" + 
							"         }\r\n" + 
							"      ],\r\n" + 
							"      \"Id\":\"fc79c854-34de-4a96-abd3-03fe5b0f5cd7\",\r\n" + 
							"      \"Name\":\"Scale\"\r\n" + 
							"   },\r\n" + 
							"   \"Model\":\""+deviceDetails.BRAND+"\",\r\n" + 
							"   \"SerialNumber\":null,\r\n" + 
							"   \"Created\":null,\r\n" + 
							"   \"Modified\":null,\r\n" + 
							"   \"Configuration\":[\r\n" + 
							"      {\r\n" + 
							"         \"Name\":\"BaudRate\",\r\n" + 
							"         \"DisplayName\":\"Baud Rate\",\r\n" + 
							"         \"ControlType\":\"Numeric\",\r\n" + 
							"         \"DataType\":\"Int\",\r\n" + 
							"         \"Value\":\""+deviceDetails.BAUDRATE+"\",\r\n" + 
							"         \"Source\":null,\r\n" + 
							"         \"IsRequired\":true\r\n" + 
							"      },\r\n" + 
							"      {\r\n" + 
							"         \"Name\":\"DataBits\",\r\n" + 
							"         \"DisplayName\":\"Data Bits\",\r\n" + 
							"         \"ControlType\":\"Numeric\",\r\n" + 
							"         \"DataType\":\"Int\",\r\n" + 
							"         \"Value\":\""+deviceDetails.DATABITS+"\",\r\n" + 
							"         \"Source\":null,\r\n" + 
							"         \"IsRequired\":true\r\n" + 
							"      },\r\n" + 
							"      {\r\n" + 
							"         \"Name\":\"StopBits\",\r\n" + 
							"         \"DisplayName\":\"Stop Bits\",\r\n" + 
							"         \"ControlType\":\"Select\",\r\n" + 
							"         \"DataType\":\"Text\",\r\n" + 
							"         \"Value\":\""+deviceDetails.STOPBITS+"\",\r\n" + 
							"         \"Source\":[\r\n" + 
							"            \"None\",\r\n" + 
							"            \"One\",\r\n" + 
							"            \"OnePointFive\",\r\n" + 
							"            \"Two\"\r\n" + 
							"         ],\r\n" + 
							"         \"IsRequired\":true\r\n" + 
							"      },\r\n" + 
							"      {\r\n" + 
							"         \"Name\":\"Parity\",\r\n" + 
							"         \"DisplayName\":\"Parity\",\r\n" + 
							"         \"ControlType\":\"Select\",\r\n" + 
							"         \"DataType\":\"Text\",\r\n" + 
							"         \"Value\":\""+deviceDetails.PARITY+"\",\r\n" + 
							"         \"Source\":[\r\n" + 
							"            \"None\",\r\n" + 
							"            \"Even\",\r\n" + 
							"            \"Mark\",\r\n" + 
							"            \"Odd\",\r\n" + 
							"            \"Space\"\r\n" + 
							"         ],\r\n" + 
							"         \"IsRequired\":true\r\n" + 
							"      },\r\n" + 
							"      {\r\n" + 
							"         \"Name\":\"PrintCommand\",\r\n" + 
							"         \"DisplayName\":\"Print Command\",\r\n" + 
							"         \"ControlType\":\"Text\",\r\n" + 
							"         \"DataType\":\"String\",\r\n" + 
							"         \"Value\":\""+deviceDetails.PRINTCOMMAND+"\",\r\n" + 
							"         \"Source\":null,\r\n" + 
							"         \"IsRequired\":true\r\n" + 
							"      },\r\n" + 
							"      {\r\n" + 
							"         \"Name\":\"Handshake\",\r\n" + 
							"         \"DisplayName\":\"Handshake\",\r\n" + 
							"         \"ControlType\":\"Select\",\r\n" + 
							"         \"DataType\":\"Text\",\r\n" + 
							"         \"Value\":\"None\",\r\n" + 
							"         \"Source\":[\r\n" + 
							"            \"None\",\r\n" + 
							"            \"RequestToSend\",\r\n" + 
							"            \"XOnXOff\",\r\n" + 
							"            \"RequestToSendXOnXOff\"\r\n" + 
							"         ],\r\n" + 
							"         \"IsRequired\":true\r\n" + 
							"      }\r\n" + 
							"   ],\r\n" + 
							"   \"IsEnable\":true,\r\n" + 
							"   \"Id\":null,\r\n" + 
							"   \"Name\":\""+deviceDetails.DEVICENAME+"\"\r\n" + 
							"}";

			Response createDevicResponse = apiUtils.submitRequest(METHOD_TYPE.POST, createDeviceJson, headers,
					AddDeviceUrl);

			SimpleDataResponse response = gson.fromJson(createDevicResponse.asString(),
					SimpleDataResponse.class);

			createdDeviceId = response.Data;

			if(response.Status) {
				logInfo("Added device - "+deviceDetails.DEVICENAME);
				return true;
			}else {
				logError("Failed to add device - "+deviceDetails.DEVICENAME);
				return true;
			}
		} catch (Exception e) {
			logError("Failed to create device - '"+deviceDetails.DEVICENAME+"' - "+ e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to get all the device details
	 * @author choubey_a
	 * @param devicename 
	 * @return String. It will return the device Id of the device name provided
	 */

	public String getDevices(String devicename) {
		try {
			apiUtils = new ApiUtils();
			Gson gson = new GsonBuilder().create();
			Map<String, String> headers = new HashMap<String, String>();
			headers.put("Content-Type", "application/json");
			headers.put("Accept", "application/json, text/plain, /");
			headers.put("Authorization", "Bearer " + accessToken);

			String request = "";

			Response response = apiUtils.submitRequest(METHOD_TYPE.GET,request,headers, GetDevicesUrl);

			MultipleDataResponse mdr = gson.fromJson(response.asString(), MultipleDataResponse.class);

			for (int i = 0; i < mdr.Data.size(); i++) {
				System.out.println(" sdr.Data.size()" + mdr.Data.size());
				if (mdr.Data.get(i).Name.equalsIgnoreCase(devicename)) {
					DeviceId = mdr.Data.get(i).Id;
					logInfo("Device ID= " + DeviceId);
				}
			}
		}catch (Exception e) {
			logError("Failed to get device Id- '"+devicename+"' - "+ e.getMessage());
		}
		return DeviceId;
	}

	/**
	 * This method is used to get the device details
	 * @author choubey_a
	 * @param devicename and device Id
	 * @return boolean. It will return true if the device name details are provided in request
	 */

	public boolean getDevice(String deviceID,String devicename) {
		try {

			apiUtils = new ApiUtils();
			Gson gson = new GsonBuilder().create();
			Map<String, String> headers = new HashMap<String, String>();
			headers.put("Content-Type", "application/json");
			headers.put("Accept", "application/json, text/plain, /");
			headers.put("Authorization", "Bearer " + accessToken);

			String request = "{\"Id\":\""+deviceID+"\"}";

			Response response = apiUtils.submitRequest(METHOD_TYPE.POST,request,headers, GetDeviceDetailsUrl);

			SingleDataResponse sdr = gson.fromJson(response.asString(), SingleDataResponse.class);

			if(sdr.Data.Name.equalsIgnoreCase(devicename)){
				logInfo ("Device creation validated");
			}
			return true;
		}catch (Exception e) {
			logError("Failed to validate device Id- '"+deviceID+"' - "+ e.getMessage());
			return false;
		}

	}

	/**
	 * This method is used to save device with provided details
	 * @author choubey_a
	 * @param deviceDetails (Device configuration details)
	 * @return boolean. It will return the boolean result true if device is been saved
	 */

	public boolean saveDevice(DeviceParams deviceDetails) {
		try {

			apiUtils = new ApiUtils();
			Gson gson = new GsonBuilder().create();
			Map<String, String> headers = new HashMap<String, String>();
			headers.put("Content-Type", "application/json");
			headers.put("Accept", "application/json, text/plain, /");
			headers.put("Authorization", "Bearer " + accessToken);

			String save = "{\r\n" + 
					"   \"DeviceType\":{\r\n" + 
					"      \"Id\":\"fc79c854-34de-4a96-abd3-03fe5b0f5cd7\",\r\n" + 
					"      \"Name\":\"Scale\"\r\n" + 
					"   },\r\n" + 
					"   \"Model\":\""+deviceDetails.BRAND+"\",\r\n" + 
					"   \"SerialNumber\":null,\r\n" + 
					"   \"Created\":null,\r\n" + 
					"   \"Modified\":null,\r\n" + 
					"   \"Configuration\":[\r\n" + 
					"      {\r\n" + 
					"         \"Name\":\"BaudRate\",\r\n" + 
					"         \"DisplayName\":\"Baud Rate\",\r\n" + 
					"         \"ControlType\":\"Numeric\",\r\n" + 
					"         \"DataType\":\"Int\",\r\n" + 
					"         \"Value\":\""+deviceDetails.BAUDRATE+"\",\r\n" + 
					"         \"Source\":null,\r\n" + 
					"         \"IsRequired\":true\r\n" + 
					"      },\r\n" + 
					"      {\r\n" + 
					"         \"Name\":\"DataBits\",\r\n" + 
					"         \"DisplayName\":\"Data Bits\",\r\n" + 
					"         \"ControlType\":\"Numeric\",\r\n" + 
					"         \"DataType\":\"Int\",\r\n" + 
					"         \"Value\":\""+deviceDetails.DATABITS+"\",\r\n" + 
					"         \"Source\":null,\r\n" + 
					"         \"IsRequired\":true\r\n" + 
					"      },\r\n" + 
					"      {\r\n" + 
					"         \"Name\":\"StopBits\",\r\n" + 
					"         \"DisplayName\":\"Stop Bits\",\r\n" + 
					"         \"ControlType\":\"Select\",\r\n" + 
					"         \"DataType\":\"Text\",\r\n" + 
					"         \"Value\":\""+deviceDetails.STOPBITS+"\",\r\n" + 
					"         \"Source\":[\r\n" + 
					"            \"None\",\r\n" + 
					"            \"One\",\r\n" + 
					"            \"OnePointFive\",\r\n" + 
					"            \"Two\"\r\n" + 
					"         ],\r\n" + 
					"         \"IsRequired\":true\r\n" + 
					"      },\r\n" + 
					"      {\r\n" + 
					"         \"Name\":\"Parity\",\r\n" + 
					"         \"DisplayName\":\"Parity\",\r\n" + 
					"         \"ControlType\":\"Select\",\r\n" + 
					"         \"DataType\":\"Text\",\r\n" + 
					"         \"Value\":\""+deviceDetails.PARITY+"\",\r\n" + 
					"         \"Source\":[\r\n" + 
					"            \"None\",\r\n" + 
					"            \"Even\",\r\n" + 
					"            \"Mark\",\r\n" + 
					"            \"Odd\",\r\n" + 
					"            \"Space\"\r\n" + 
					"         ],\r\n" + 
					"         \"IsRequired\":true\r\n" + 
					"      },\r\n" + 
					"      {\r\n" + 
					"         \"Name\":\"PrintCommand\",\r\n" + 
					"         \"DisplayName\":\"Print Command\",\r\n" + 
					"         \"ControlType\":\"Text\",\r\n" + 
					"         \"DataType\":\"String\",\r\n" + 
					"         \"Value\":\""+deviceDetails.PRINTCOMMAND+"\",\r\n" + 
					"         \"Source\":null,\r\n" + 
					"         \"IsRequired\":true\r\n" + 
					"      },\r\n" + 
					"      {\r\n" + 
					"         \"Name\":\"Handshake\",\r\n" + 
					"         \"DisplayName\":\"Handshake\",\r\n" + 
					"         \"ControlType\":\"Select\",\r\n" + 
					"         \"DataType\":\"Text\",\r\n" + 
					"         \"Value\":\""+deviceDetails.HANDSHAKE+"\",\r\n" + 
					"         \"Source\":[\r\n" + 
					"            \"None\",\r\n" + 
					"            \"RequestToSend\",\r\n" + 
					"            \"XOnXOff\",\r\n" + 
					"            \"RequestToSendXOnXOff\"\r\n" + 
					"         ],\r\n" + 
					"         \"IsRequired\":true\r\n" + 
					"      }\r\n" + 
					"   ],\r\n" + 
					"   \"IsEnable\":"+deviceDetails.ENABLE+",\r\n" + 
					"   \"Id\":null,\r\n" + 
					"   \"Name\":\""+deviceDetails.DEVICENAME+"\"\r\n" + 
					"}";

			Response saveDevicResponse = apiUtils.submitRequest(METHOD_TYPE.POST, save, headers,
					AddDeviceUrl);

			SimpleDataResponse response = gson.fromJson(saveDevicResponse.asString(),
					SimpleDataResponse.class);

			savedDeviceId = response.Data;

			if(response.Status) {
				logInfo("Saved device - "+deviceDetails.DEVICENAME);
				return true;
			}else {
				logError("Failed to save device - "+deviceDetails.DEVICENAME);
				return true;
			}

		}catch(Exception e) {
			logError("Failed to save device - '"+deviceDetails.DEVICENAME+"' - "+ e.getMessage());
			return false;
		}

	}

	/**
	 * This method is used to copy device
	 * @author choubey_a
	 * @param deviceId -  Id to be provided of the device from which copy has to be created
	 * @return String. It will return the ID of source device
	 */
	public String copyDevice(String deviceId,String copydevicename) {
		SingleDataResponse response;
		try {

			apiUtils = new ApiUtils();
			Gson gson = new GsonBuilder().create();
			Map<String, String> headers = new HashMap<String, String>();
			headers.put("Content-Type", "application/json");
			headers.put("Accept", "application/json, text/plain, /");
			headers.put("Authorization", "Bearer " + accessToken);

			String copyJson = "{\"Id\":\""+deviceId+"\"}";

			Response copyDevicResponse = apiUtils.submitRequest(METHOD_TYPE.POST, copyJson, headers,
					CopyDeviceUrl);

			response = gson.fromJson(copyDevicResponse.asString(),
					SingleDataResponse.class);

			if(response.Data.Name.equalsIgnoreCase(copydevicename)){
				logInfo ("Copied device - "+copydevicename);
			}else {
				logError ("Error while getting source device details - "+copydevicename);
				return null;
			}

		}catch(Exception e) {
			logError("Failed to copy device - '"+deviceId+"' - "+ e.getMessage());
			return null;
		}
		return response.Data.Id;
	}

	public boolean savedisableDevice(DeviceParams deviceDetails) {
		try {

			apiUtils = new ApiUtils();
			Gson gson = new GsonBuilder().create();
			Map<String, String> headers = new HashMap<String, String>();
			headers.put("Content-Type", "application/json");
			headers.put("Accept", "application/json, text/plain, /");
			headers.put("Authorization", "Bearer " + accessToken);

			String save = "{\r\n" + 
					"   \"DeviceType\":{\r\n" + 
					"      \"Id\":\"fc79c854-34de-4a96-abd3-03fe5b0f5cd7\",\r\n" + 
					"      \"Name\":\"Scale\"\r\n" + 
					"   },\r\n" + 
					"   \"Model\":\"\",\r\n" + 
					"   \"SerialNumber\":null,\r\n" + 
					"   \"Created\":{\r\n" + 
					"      \"Date\":\"2021-08-19T09:28:56.6770000Z\",\r\n" + 
					"      \"User\":{\r\n" + 
					"         \"Upn\":null,\r\n" + 
					"         \"FirstName\":null,\r\n" + 
					"         \"LastName\":null,\r\n" + 
					"         \"IANA\":null,\r\n" + 
					"         \"IsAdmin\":false,\r\n" + 
					"         \"Partner\":null,\r\n" + 
					"         \"UserType\":\"TenantUser\",\r\n" + 
					"         \"HasAllLocations\":false,\r\n" + 
					"         \"HasAllSuppliers\":false,\r\n" + 
					"         \"Title\":null,\r\n" + 
					"         \"UserAgent\":null,\r\n" + 
					"         \"IsMobile\":false,\r\n" + 
					"         \"RequestingIp\":null,\r\n" + 
					"         \"EmployeeId\":null,\r\n" + 
					"         \"Phone\":null,\r\n" + 
					"         \"DefaultLocation\":\"00000000-0000-0000-0000-000000000000\",\r\n" + 
					"         \"SecurityLevel\":0,\r\n" + 
					"         \"Id\":\"fb0fc26f-aaac-4000-b9f7-b89908643f69\",\r\n" + 
					"         \"Name\":\"Auto User01\"\r\n" + 
					"      },\r\n" + 
					"      \"Iana\":null,\r\n" + 
					"      \"Latitude\":null,\r\n" + 
					"      \"Longitude\":null\r\n" + 
					"   },\r\n" + 
					"   \"Modified\":{\r\n" + 
					"      \"Date\":\"2021-08-19T09:29:40.6570000Z\",\r\n" + 
					"      \"User\":{\r\n" + 
					"         \"Upn\":null,\r\n" + 
					"         \"FirstName\":null,\r\n" + 
					"         \"LastName\":null,\r\n" + 
					"         \"IANA\":null,\r\n" + 
					"         \"IsAdmin\":false,\r\n" + 
					"         \"Partner\":null,\r\n" + 
					"         \"UserType\":\"TenantUser\",\r\n" + 
					"         \"HasAllLocations\":false,\r\n" + 
					"         \"HasAllSuppliers\":false,\r\n" + 
					"         \"Title\":null,\r\n" + 
					"         \"UserAgent\":null,\r\n" + 
					"         \"IsMobile\":false,\r\n" + 
					"         \"RequestingIp\":null,\r\n" + 
					"         \"EmployeeId\":null,\r\n" + 
					"         \"Phone\":null,\r\n" + 
					"         \"DefaultLocation\":\"00000000-0000-0000-0000-000000000000\",\r\n" + 
					"         \"SecurityLevel\":0,\r\n" + 
					"         \"Id\":\"fb0fc26f-aaac-4000-b9f7-b89908643f69\",\r\n" + 
					"         \"Name\":\"Auto User01\"\r\n" + 
					"      },\r\n" + 
					"      \"Iana\":null,\r\n" + 
					"      \"Latitude\":null,\r\n" + 
					"      \"Longitude\":null\r\n" + 
					"   },\r\n" + 
					"   \"Configuration\":[\r\n" + 
					"      {\r\n" + 
					"         \"Name\":\"BaudRate\",\r\n" + 
					"         \"DisplayName\":\"Baud Rate\",\r\n" + 
					"         \"ControlType\":\"Numeric\",\r\n" + 
					"         \"DataType\":\"Int\",\r\n" + 
					"         \"Value\":\""+deviceDetails.BAUDRATE+"\",\r\n" + 
					"         \"Source\":null,\r\n" + 
					"         \"IsRequired\":true\r\n" + 
					"      },\r\n" + 
					"      {\r\n" + 
					"         \"Name\":\"DataBits\",\r\n" + 
					"         \"DisplayName\":\"Data Bits\",\r\n" + 
					"         \"ControlType\":\"Numeric\",\r\n" + 
					"         \"DataType\":\"Int\",\r\n" + 
					"         \"Value\":\""+deviceDetails.DATABITS+"\",\r\n" + 
					"         \"Source\":null,\r\n" + 
					"         \"IsRequired\":true\r\n" + 
					"      },\r\n" + 
					"      {\r\n" + 
					"         \"Name\":\"StopBits\",\r\n" + 
					"         \"DisplayName\":\"Stop Bits\",\r\n" + 
					"         \"ControlType\":\"Select\",\r\n" + 
					"         \"DataType\":\"Text\",\r\n" + 
					"         \"Value\":\""+deviceDetails.STOPBITS+"\",\r\n" + 
					"         \"Source\":[\r\n" + 
					"            \"None\",\r\n" + 
					"            \"One\",\r\n" + 
					"            \"OnePointFive\",\r\n" + 
					"            \"Two\"\r\n" + 
					"         ],\r\n" + 
					"         \"IsRequired\":true\r\n" + 
					"      },\r\n" + 
					"      {\r\n" + 
					"         \"Name\":\"Parity\",\r\n" + 
					"         \"DisplayName\":\"Parity\",\r\n" + 
					"         \"ControlType\":\"Select\",\r\n" + 
					"         \"DataType\":\"Text\",\r\n" + 
					"         \"Value\":\""+deviceDetails.PARITY+"\",\r\n" + 
					"         \"Source\":[\r\n" + 
					"            \"None\",\r\n" + 
					"            \"Even\",\r\n" + 
					"            \"Mark\",\r\n" + 
					"            \"Odd\",\r\n" + 
					"            \"Space\"\r\n" + 
					"         ],\r\n" + 
					"         \"IsRequired\":true\r\n" + 
					"      },\r\n" + 
					"      {\r\n" + 
					"         \"Name\":\"PrintCommand\",\r\n" + 
					"         \"DisplayName\":\"Print Command\",\r\n" + 
					"         \"ControlType\":\"Text\",\r\n" + 
					"         \"DataType\":\"String\",\r\n" + 
					"         \"Value\":\""+deviceDetails.PRINTCOMMAND+"\",\r\n" + 
					"         \"Source\":null,\r\n" + 
					"         \"IsRequired\":true\r\n" + 
					"      },\r\n" + 
					"      {\r\n" + 
					"         \"Name\":\"Handshake\",\r\n" + 
					"         \"DisplayName\":\"Handshake\",\r\n" + 
					"         \"ControlType\":\"Select\",\r\n" + 
					"         \"DataType\":\"Text\",\r\n" + 
					"         \"Value\":\""+deviceDetails.HANDSHAKE+"\",\r\n" + 
					"         \"Source\":[\r\n" + 
					"            \"None\",\r\n" + 
					"            \"RequestToSend\",\r\n" + 
					"            \"XOnXOff\",\r\n" + 
					"            \"RequestToSendXOnXOff\"\r\n" + 
					"         ],\r\n" + 
					"         \"IsRequired\":true\r\n" + 
					"      }\r\n" + 
					"   ],\r\n" + 
					"   \"IsEnable\":"+deviceDetails.ENABLE+",\r\n" + 
					"   \"Id\":\""+deviceDetails.DEVICEID+"\",\r\n" + 
					"   \"Name\":\""+deviceDetails.DEVICENAME+"\"\r\n" + 
					"}";

			Response saveDevicResponse = apiUtils.submitRequest(METHOD_TYPE.POST, save, headers,
					AddDeviceUrl);

			SimpleDataResponse response = gson.fromJson(saveDevicResponse.asString(),
					SimpleDataResponse.class);

			savedDeviceId = response.Data;

			if(response.Status) {
				logInfo("Saved device - "+deviceDetails.DEVICENAME);
				return true;
			}else {
				logError("Failed to save device - "+deviceDetails.DEVICENAME);
				return true;
			}

		}catch(Exception e) {
			logError("Failed to save device - '"+deviceDetails.DEVICENAME+"' - "+ e.getMessage());
			return false;
		}

	}

	public static class DeviceParams {
		public String BAUDRATE;
		public String STOPBITS;
		public String PRINTCOMMAND;
		public String DATABITS;
		public String PARITY;
		public String HANDSHAKE;
		public String DEVICENAME;
		public String DEVICETYPE;
		public String BRAND;
		public String ENABLE = "true";
		public String DEVICEID;

	}

	public class Data {
		public boolean Status;
		public String Name;
		public String Id;
	}

	public class SingleDataResponse {
		public Data Data;
		public boolean Status;
	}

	public class MultipleDataResponse{
		public List<Data> Data;
	}
	
	public class SimpleDataResponse {
		public String Data;
		public boolean Status;
	}
}

