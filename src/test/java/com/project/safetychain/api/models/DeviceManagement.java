package com.project.safetychain.api.models;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.project.safetychain.api.models.support.Support.DeviceParams;
import com.project.safetychain.api.models.support.TCG_FormCreation_Wrapper;
import com.project.utilities.ApiUtils;
import com.project.utilities.ApiUtils.METHOD_TYPE;

import io.restassured.response.Response;

public class DeviceManagement extends Auth {

	public String GetDevicesUrl = baseURI + DeviceManagementURLs.GET_ALL_DEVICES_URL;
	public String GetAllDeviceTypesUrl = baseURI + DeviceManagementURLs.GET_ALL_DEVICE_TYPES_URL;
	public String GetDeviceDetailsUrl = baseURI + DeviceManagementURLs.GET_DEVICE_DETAILS_URL;
	public String AddDeviceUrl = baseURI + DeviceManagementURLs.ADD_DEVICE_URL;

	ApiUtils apiUtils = new ApiUtils();
	Gson gson = new GsonBuilder().create();
	TCG_FormCreation_Wrapper formCreationWrapper = new TCG_FormCreation_Wrapper();

	public class UM_SingleDataResponse {
		public Data Data;
		public boolean Status;
		public Errors Errors;
		public boolean RefreshToken;
		public String Message;
	}

	public class Data {
		public String deviceID;
		public boolean Status;
		public Errors Errors;
		public boolean RefreshToken;
	}

	/**
	 * This method is used to add new device with default details
	 * @author pashine_a
	 * @param deviceName
	 * @return boolean. It will return the boolean result true if new device is been added
	 */
	public boolean saveDevice(String deviceName) {
		try {

			apiUtils = new ApiUtils();
			Gson gson = new GsonBuilder().create();
			Map<String, String> headers = new HashMap<String, String>();
			headers.put("Content-Type", "application/json");
			headers.put("Accept", "application/json, text/plain, /");
			headers.put("Authorization", "Bearer " + accessToken);

			String createDeviceJson = "{\r\n"
					+ "  \"DeviceType\": {\r\n"
					+ "    \"DefaultConfiguration\": [\r\n"
					+ "      {\r\n"
					+ "        \"Name\": \"BaudRate\",\r\n"
					+ "        \"DisplayName\": \"Baud Rate\",\r\n"
					+ "        \"ControlType\": \"Numeric\",\r\n"
					+ "        \"DataType\": \"Int\",\r\n"
					+ "        \"Value\": \"\",\r\n"
					+ "        \"Source\": null,\r\n"
					+ "        \"IsRequired\": true\r\n"
					+ "      },\r\n"
					+ "      {\r\n"
					+ "        \"Name\": \"DataBits\",\r\n"
					+ "        \"DisplayName\": \"Data Bits\",\r\n"
					+ "        \"ControlType\": \"Numeric\",\r\n"
					+ "        \"DataType\": \"Int\",\r\n"
					+ "        \"Value\": \"\",\r\n"
					+ "        \"Source\": null,\r\n"
					+ "        \"IsRequired\": true\r\n"
					+ "      },\r\n"
					+ "      {\r\n"
					+ "        \"Name\": \"StopBits\",\r\n"
					+ "        \"DisplayName\": \"Stop Bits\",\r\n"
					+ "        \"ControlType\": \"Select\",\r\n"
					+ "        \"DataType\": \"Text\",\r\n"
					+ "        \"Value\": \"\",\r\n"
					+ "        \"Source\": [\r\n"
					+ "          \"None\",\r\n"
					+ "          \"One\",\r\n"
					+ "          \"OnePointFive\",\r\n"
					+ "          \"Two\"\r\n"
					+ "        ],\r\n"
					+ "        \"IsRequired\": true\r\n"
					+ "      },\r\n"
					+ "      {\r\n"
					+ "        \"Name\": \"Parity\",\r\n"
					+ "        \"DisplayName\": \"Parity\",\r\n"
					+ "        \"ControlType\": \"Select\",\r\n"
					+ "        \"DataType\": \"Text\",\r\n"
					+ "        \"Value\": \"\",\r\n"
					+ "        \"Source\": [\r\n"
					+ "          \"None\",\r\n"
					+ "          \"Even\",\r\n"
					+ "          \"Mark\",\r\n"
					+ "          \"Odd\",\r\n"
					+ "          \"Space\"\r\n"
					+ "        ],\r\n"
					+ "        \"IsRequired\": true\r\n"
					+ "      },\r\n"
					+ "      {\r\n"
					+ "        \"Name\": \"PrintCommand\",\r\n"
					+ "        \"DisplayName\": \"Print Command\",\r\n"
					+ "        \"ControlType\": \"Text\",\r\n"
					+ "        \"DataType\": \"String\",\r\n"
					+ "        \"Value\": \"\",\r\n"
					+ "        \"Source\": null,\r\n"
					+ "        \"IsRequired\": true\r\n"
					+ "      },\r\n"
					+ "      {\r\n"
					+ "        \"Name\": \"Handshake\",\r\n"
					+ "        \"DisplayName\": \"Handshake\",\r\n"
					+ "        \"ControlType\": \"Select\",\r\n"
					+ "        \"DataType\": \"Text\",\r\n"
					+ "        \"Value\": \"\",\r\n"
					+ "        \"Source\": [\r\n"
					+ "          \"None\",\r\n"
					+ "          \"RequestToSend\",\r\n"
					+ "          \"XOnXOff\",\r\n"
					+ "          \"RequestToSendXOnXOff\"\r\n"
					+ "        ],\r\n"
					+ "        \"IsRequired\": true\r\n"
					+ "      }\r\n"
					+ "    ],\r\n"
					+ "    \"Id\": \"fc79c854-34de-4a96-abd3-03fe5b0f5cd7\",\r\n"
					+ "    \"Name\": \"Scale\"\r\n"
					+ "  },\r\n"
					+ "  \"Model\": \"Ohaus/Ranger 3000\",\r\n"
					+ "  \"SerialNumber\": null,\r\n"
					+ "  \"Created\": null,\r\n"
					+ "  \"Modified\": null,\r\n"
					+ "  \"Configuration\": [\r\n"
					+ "    {\r\n"
					+ "      \"Name\": \"BaudRate\",\r\n"
					+ "      \"DisplayName\": \"Baud Rate\",\r\n"
					+ "      \"ControlType\": \"Numeric\",\r\n"
					+ "      \"DataType\": \"Int\",\r\n"
					+ "      \"Value\": \"9600\",\r\n"
					+ "      \"Source\": null,\r\n"
					+ "      \"IsRequired\": true\r\n"
					+ "    },\r\n"
					+ "    {\r\n"
					+ "      \"Name\": \"DataBits\",\r\n"
					+ "      \"DisplayName\": \"Data Bits\",\r\n"
					+ "      \"ControlType\": \"Numeric\",\r\n"
					+ "      \"DataType\": \"Int\",\r\n"
					+ "      \"Value\": \"8\",\r\n"
					+ "      \"Source\": null,\r\n"
					+ "      \"IsRequired\": true\r\n"
					+ "    },\r\n"
					+ "    {\r\n"
					+ "      \"Name\": \"StopBits\",\r\n"
					+ "      \"DisplayName\": \"Stop Bits\",\r\n"
					+ "      \"ControlType\": \"Select\",\r\n"
					+ "      \"DataType\": \"Text\",\r\n"
					+ "      \"Value\": \"One\",\r\n"
					+ "      \"Source\": [\r\n"
					+ "        \"None\",\r\n"
					+ "        \"One\",\r\n"
					+ "        \"OnePointFive\",\r\n"
					+ "        \"Two\"\r\n"
					+ "      ],\r\n"
					+ "      \"IsRequired\": true\r\n"
					+ "    },\r\n"
					+ "    {\r\n"
					+ "      \"Name\": \"Parity\",\r\n"
					+ "      \"DisplayName\": \"Parity\",\r\n"
					+ "      \"ControlType\": \"Select\",\r\n"
					+ "      \"DataType\": \"Text\",\r\n"
					+ "      \"Value\": \"None\",\r\n"
					+ "      \"Source\": [\r\n"
					+ "        \"None\",\r\n"
					+ "        \"Even\",\r\n"
					+ "        \"Mark\",\r\n"
					+ "        \"Odd\",\r\n"
					+ "        \"Space\"\r\n"
					+ "      ],\r\n"
					+ "      \"IsRequired\": true\r\n"
					+ "    },\r\n"
					+ "    {\r\n"
					+ "      \"Name\": \"PrintCommand\",\r\n"
					+ "      \"DisplayName\": \"Print Command\",\r\n"
					+ "      \"ControlType\": \"Text\",\r\n"
					+ "      \"DataType\": \"String\",\r\n"
					+ "      \"Value\": \"P\",\r\n"
					+ "      \"Source\": null,\r\n"
					+ "      \"IsRequired\": true\r\n"
					+ "    },\r\n"
					+ "    {\r\n"
					+ "      \"Name\": \"Handshake\",\r\n"
					+ "      \"DisplayName\": \"Handshake\",\r\n"
					+ "      \"ControlType\": \"Select\",\r\n"
					+ "      \"DataType\": \"Text\",\r\n"
					+ "      \"Value\": \"None\",\r\n"
					+ "      \"Source\": [\r\n"
					+ "        \"None\",\r\n"
					+ "        \"RequestToSend\",\r\n"
					+ "        \"XOnXOff\",\r\n"
					+ "        \"RequestToSendXOnXOff\"\r\n"
					+ "      ],\r\n"
					+ "      \"IsRequired\": true\r\n"
					+ "    }\r\n"
					+ "  ],\r\n"
					+ "  \"IsEnable\": true,\r\n"
					+ "  \"Id\": null,\r\n"
					+ "  \"Name\": \""+deviceName+"\"\r\n"
					+ "}";

			Response createDevicResponse = apiUtils.submitRequest(METHOD_TYPE.POST, createDeviceJson, headers,
					AddDeviceUrl);

			SimpleDataResponse validateResponse = gson.fromJson(createDevicResponse.asString(),
					SimpleDataResponse.class);

			if(validateResponse.Status) {
				logInfo("Added device - "+deviceName);
				return true;
			}else {
				logError("Failed to add device - "+deviceName);
				return false;
			}
		} catch (Exception e) {
			logError("Failed to create device - " + e.getMessage());
			return false;
		}
	}

	/**INCOMPLETE  - PAYLOAD UPDATE IS REQUIRED
	 * This method is used to add/update device with provided details
	 * @author pashine_a
	 * @param deviceDetails (Device configuration details)
	 * @return boolean. It will return the boolean result true if new device is been added/updated
	 */
	public boolean saveDevice(DeviceParams deviceDetails) {
		try {

			apiUtils = new ApiUtils();
			Gson gson = new GsonBuilder().create();
			Map<String, String> headers = new HashMap<String, String>();
			headers.put("Content-Type", "application/json");
			headers.put("Accept", "application/json, text/plain, /");
			headers.put("Authorization", "Bearer " + accessToken);

			String createDeviceJson = "{\r\n"
					+ "  \"DeviceType\": {\r\n"
					+ "    \"DefaultConfiguration\": [\r\n"
					+ "      {\r\n"
					+ "        \"Name\": \"BaudRate\",\r\n"
					+ "        \"DisplayName\": \"Baud Rate\",\r\n"
					+ "        \"ControlType\": \"Numeric\",\r\n"
					+ "        \"DataType\": \"Int\",\r\n"
					+ "        \"Value\": \"\",\r\n"
					+ "        \"Source\": null,\r\n"
					+ "        \"IsRequired\": true\r\n"
					+ "      },\r\n"
					+ "      {\r\n"
					+ "        \"Name\": \"DataBits\",\r\n"
					+ "        \"DisplayName\": \"Data Bits\",\r\n"
					+ "        \"ControlType\": \"Numeric\",\r\n"
					+ "        \"DataType\": \"Int\",\r\n"
					+ "        \"Value\": \"\",\r\n"
					+ "        \"Source\": null,\r\n"
					+ "        \"IsRequired\": true\r\n"
					+ "      },\r\n"
					+ "      {\r\n"
					+ "        \"Name\": \"StopBits\",\r\n"
					+ "        \"DisplayName\": \"Stop Bits\",\r\n"
					+ "        \"ControlType\": \"Select\",\r\n"
					+ "        \"DataType\": \"Text\",\r\n"
					+ "        \"Value\": \"\",\r\n"
					+ "        \"Source\": [\r\n"
					+ "          \"None\",\r\n"
					+ "          \"One\",\r\n"
					+ "          \"OnePointFive\",\r\n"
					+ "          \"Two\"\r\n"
					+ "        ],\r\n"
					+ "        \"IsRequired\": true\r\n"
					+ "      },\r\n"
					+ "      {\r\n"
					+ "        \"Name\": \"Parity\",\r\n"
					+ "        \"DisplayName\": \"Parity\",\r\n"
					+ "        \"ControlType\": \"Select\",\r\n"
					+ "        \"DataType\": \"Text\",\r\n"
					+ "        \"Value\": \"\",\r\n"
					+ "        \"Source\": [\r\n"
					+ "          \"None\",\r\n"
					+ "          \"Even\",\r\n"
					+ "          \"Mark\",\r\n"
					+ "          \"Odd\",\r\n"
					+ "          \"Space\"\r\n"
					+ "        ],\r\n"
					+ "        \"IsRequired\": true\r\n"
					+ "      },\r\n"
					+ "      {\r\n"
					+ "        \"Name\": \"PrintCommand\",\r\n"
					+ "        \"DisplayName\": \"Print Command\",\r\n"
					+ "        \"ControlType\": \"Text\",\r\n"
					+ "        \"DataType\": \"String\",\r\n"
					+ "        \"Value\": \"\",\r\n"
					+ "        \"Source\": null,\r\n"
					+ "        \"IsRequired\": true\r\n"
					+ "      },\r\n"
					+ "      {\r\n"
					+ "        \"Name\": \"Handshake\",\r\n"
					+ "        \"DisplayName\": \"Handshake\",\r\n"
					+ "        \"ControlType\": \"Select\",\r\n"
					+ "        \"DataType\": \"Text\",\r\n"
					+ "        \"Value\": \"\",\r\n"
					+ "        \"Source\": [\r\n"
					+ "          \"None\",\r\n"
					+ "          \"RequestToSend\",\r\n"
					+ "          \"XOnXOff\",\r\n"
					+ "          \"RequestToSendXOnXOff\"\r\n"
					+ "        ],\r\n"
					+ "        \"IsRequired\": true\r\n"
					+ "      }\r\n"
					+ "    ],\r\n"
					+ "    \"Id\": \"fc79c854-34de-4a96-abd3-03fe5b0f5cd7\",\r\n"
					+ "    \"Name\": \""+deviceDetails.DeviceType+"\"\r\n"
					+ "  },\r\n"
					+ "  \"Model\": \""+deviceDetails.BrandModel+"\",\r\n"
					+ "  \"SerialNumber\": null,\r\n"
					+ "  \"Created\": null,\r\n"
					+ "  \"Modified\": null,\r\n"
					+ "  \"Configuration\": [\r\n"
					+ "    {\r\n"
					+ "      \"Name\": \"BaudRate\",\r\n"
					+ "      \"DisplayName\": \"Baud Rate\",\r\n"
					+ "      \"ControlType\": \"Numeric\",\r\n"
					+ "      \"DataType\": \"Int\",\r\n"
					+ "      \"Value\": \""+deviceDetails.BaudRate+"\",\r\n"
					+ "      \"Source\": null,\r\n"
					+ "      \"IsRequired\": true\r\n"
					+ "    },\r\n"
					+ "    {\r\n"
					+ "      \"Name\": \"DataBits\",\r\n"
					+ "      \"DisplayName\": \"Data Bits\",\r\n"
					+ "      \"ControlType\": \"Numeric\",\r\n"
					+ "      \"DataType\": \"Int\",\r\n"
					+ "      \"Value\": \""+deviceDetails.DataBits+"\",\r\n"
					+ "      \"Source\": null,\r\n"
					+ "      \"IsRequired\": true\r\n"
					+ "    },\r\n"
					+ "    {\r\n"
					+ "      \"Name\": \"StopBits\",\r\n"
					+ "      \"DisplayName\": \"Stop Bits\",\r\n"
					+ "      \"ControlType\": \"Select\",\r\n"
					+ "      \"DataType\": \"Text\",\r\n"
					+ "      \"Value\": \""+deviceDetails.StopBits+"\",\r\n"
					+ "      \"Source\": [\r\n"
					+ "        \"None\",\r\n"
					+ "        \"One\",\r\n"
					+ "        \"OnePointFive\",\r\n"
					+ "        \"Two\"\r\n"
					+ "      ],\r\n"
					+ "      \"IsRequired\": true\r\n"
					+ "    },\r\n"
					+ "    {\r\n"
					+ "      \"Name\": \"Parity\",\r\n"
					+ "      \"DisplayName\": \"Parity\",\r\n"
					+ "      \"ControlType\": \"Select\",\r\n"
					+ "      \"DataType\": \"Text\",\r\n"
					+ "      \"Value\": \""+deviceDetails.Parity+"\",\r\n"
					+ "      \"Source\": [\r\n"
					+ "        \"None\",\r\n"
					+ "        \"Even\",\r\n"
					+ "        \"Mark\",\r\n"
					+ "        \"Odd\",\r\n"
					+ "        \"Space\"\r\n"
					+ "      ],\r\n"
					+ "      \"IsRequired\": true\r\n"
					+ "    },\r\n"
					+ "    {\r\n"
					+ "      \"Name\": \"PrintCommand\",\r\n"
					+ "      \"DisplayName\": \"Print Command\",\r\n"
					+ "      \"ControlType\": \"Text\",\r\n"
					+ "      \"DataType\": \"String\",\r\n"
					+ "      \"Value\": \""+deviceDetails.PrintCommand+"\",\r\n"
					+ "      \"Source\": null,\r\n"
					+ "      \"IsRequired\": true\r\n"
					+ "    },\r\n"
					+ "    {\r\n"
					+ "      \"Name\": \"Handshake\",\r\n"
					+ "      \"DisplayName\": \"Handshake\",\r\n"
					+ "      \"ControlType\": \"Select\",\r\n"
					+ "      \"DataType\": \"Text\",\r\n"
					+ "      \"Value\": \""+deviceDetails.HandShake+"\",\r\n"
					+ "      \"Source\": [\r\n"
					+ "        \"None\",\r\n"
					+ "        \"RequestToSend\",\r\n"
					+ "        \"XOnXOff\",\r\n"
					+ "        \"RequestToSendXOnXOff\"\r\n"
					+ "      ],\r\n"
					+ "      \"IsRequired\": true\r\n"
					+ "    }\r\n"
					+ "  ],\r\n"
					+ "  \"IsEnable\": "+deviceDetails.IsEnable+",\r\n"
					+ "  \"Id\": null,\r\n"
					+ "  \"Name\": \""+deviceDetails.DeviceName+"\"\r\n"
					+ "}";

			Response createDevicResponse = apiUtils.submitRequest(METHOD_TYPE.POST, createDeviceJson, headers,
					AddDeviceUrl);

			SimpleDataResponse validateResponse = gson.fromJson(createDevicResponse.asString(),
					SimpleDataResponse.class);

			if(validateResponse.Status) {
				logInfo("Added device - "+deviceDetails.DeviceName);
				return true;
			}else {
				logError("Failed to add device - "+deviceDetails.DeviceName);
				return true;
			}
		} catch (Exception e) {
			logError("Failed to create add/update device - '"+deviceDetails.DeviceName+"' - "+ e.getMessage());
			return false;
		}
	}

}
