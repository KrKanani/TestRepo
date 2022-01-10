package com.project.safetychain.api.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.project.safetychain.api.models.support.Support;
import com.project.safetychain.api.models.support.Support.UserParams;
import com.project.safetychain.api.models.support.TCG_FormCreation_Wrapper;
import com.project.testbase.TestValidation;
import com.project.utilities.ApiUtils;
import com.project.utilities.ApiUtils.METHOD_TYPE;

import io.restassured.response.Response;

public class UsersManagement extends Auth {

	public String UsersUrl = baseURI + UsersManagementUrls.USERS_URL;
	public String AddUsersUrl = baseURI + UsersManagementUrls.ADD_USERS_URL;
	public String GetRoleUrl = baseURI + UsersManagementUrls.GET_ROLES_URL;
	public String GetAllLocationsUrl = baseURI + UsersManagementUrls.GET_LOCATIONS_URL;
	public String ResetPasswordUrl = baseURI + UsersManagementUrls.RESET_PASSWORD_URL;

	ApiUtils apiUtils = new ApiUtils();
	Gson gson = new GsonBuilder().create();
	TCG_FormCreation_Wrapper formCreationWrapper = new TCG_FormCreation_Wrapper();

	/*******************************
	 * Methods - Start
	 ****************************/

	/*******************************
	 * Methods - End
	 ****************************/

	/*******************************
	 * Json Path Builder - Start
	 ****************************/

	public class UM_SingleDataResponse {
		public Data Data;
		public boolean Status;
		public Errors Errors;
		public boolean RefreshToken;
		public String Message;
	}

	public class Data {
		public List<Rows> Rows;
		public int Count;
		public boolean Status;
		public Errors Errors;
		public boolean RefreshToken;
		public List<Roles> Roles;
		public List<Locations> Locations;
		public List<WorkGroups> WorkGroups;
		public String UserType;
		public String FullName;
		public String HasAllLocations;
		public String Pin;
		public String EmployeeId;
		public String Phone;
		public DefaultLocation DefaultLocation;
	}

	public class Rows {
		public String FirstName;
		public String LastName;
		public String Email;
		public boolean PasswordExpired;
		public boolean IsActive;
		public boolean IsLocked;
		public TimeZone TimeZone;
	}

	public class TimeZone {
		public String Id;
		public String DisplayName;
	}

	public class Roles {
		public String Id;
		public String Name;
	}

	public class Locations {
		public String Id;
		public String Name;
	}

	public class WorkGroups {
		public String Id;
		public String Name;
	}

	public class DefaultLocation {
		public boolean IsEnabled;
		public String Id;
		public String Name;
	}

	/*******************************
	 * Json Path Builder - End
	 ****************************/

	public boolean createUser(UserParams up, HashMap<String, String> workGroupIds) {
		FormDesigner formDesigner = new FormDesigner();

		try {

			apiUtils = new ApiUtils();
			Gson gson = new GsonBuilder().create();
			Map<String, String> headers = new HashMap<String, String>();
			headers.put("Content-Type", "application/json");
			headers.put("Accept", "application/json, text/plain, /");
			headers.put("Authorization", "Bearer " + accessToken);
			String locationIdsLst = "";

			HashMap<String, String> LocHashMap = new HashMap<String, String>();

			if(up.LocationCat!=null) {
				String locId = formDesigner.verifylocationCategory(locationCatID, up.LocationCat);
				if (locId.equalsIgnoreCase("")) {
					formCreationWrapper.createResourceCategory_Location(up.LocationCat);
				}
			}

			if (up.LocationNmIds.get(0).equalsIgnoreCase("ALL")) {

				locationIdsLst = "00000000-0000-0000-0000-000000000000";
				LocHashMap.put("ALL", locationIdsLst);

			}else{

				List<String> LocDetailsExsting = verifyLocationCreation(up.LocationNmIds);

				if (LocDetailsExsting != null) {
					for (int i = 0; i < LocDetailsExsting.size(); i++) {
						formCreationWrapper.createResourceInstance_Location(LocDetailsExsting.get(i));
					}
				}

				LocHashMap = getAllLocationsDetails(up.LocationNmIds);
			}

			locationIdsLst = Support.updateUserFieldValues(LocHashMap);

			HashMap<String, String> DefLocHashMap = getAllLocationsDetails(Arrays.asList(up.DefaultLocation));
			HashMap<String, String> RoleHashMap = getRoleDetails(up.Roles);

			String DeflocationIdsLst = Support.updateUserFieldValues(DefLocHashMap);
			String WGIdsLst = Support.updateUserFieldValues(workGroupIds);
			String roleLst = Support.updateUserFieldValues(RoleHashMap);

			// 4. Create Task
			String createUserJson = "{\r\n" + "    \"Username\": \"" + up.Username + "\",\r\n"
					+ "    \"ClearPassword\": \"" + up.ClearPassword + "\",\r\n" + "    \"FirstName\": \""
					+ up.FirstName + "\",\r\n" + "    \"LastName\": \"" + up.LastName + "\",\r\n" + "    \"Email\": \""
					+ up.Email + "\",\r\n" + "    \"Locations\": [\r\n" + locationIdsLst + "    ],\r\n"
					+ "    \"Roles\": [\r\n" + roleLst + "    ],\r\n" + "    \"TimeZone\": {\r\n"
					+ "        \"Id\": \"Asia/Kolkata\",\r\n" + "        \"DisplayName\": \"" + up.TimeZone + "\"\r\n"
					+ "    },\r\n" + "    \"WorkGroups\": [\r\n" + WGIdsLst + "    ],\r\n" + "    \"Pin\": \"" + up.Pin
					+ "\",\r\n" + "    \"EmployeeId\": \"" + up.EmployeeId + "\",\r\n" + "    \"Phone\": \"" + up.Phone
					+ "\",\r\n" + "    \"DefaultLocation\": " + DeflocationIdsLst + ",\r\n"
					+ "    \"isUserNameAvailable\": true,\r\n" + "    \"AddedLocation\": [\r\n" + locationIdsLst
					+ "    ],\r\n" + "    \"RemovedLocation\": [],\r\n" + "    \"AddedRoles\": [\r\n" + roleLst
					+ "    ],\r\n" + "    \"RemovedRoles\": [],\r\n" + "    \"AddedWorkGroups\": [\r\n" + WGIdsLst
					+ "    ],\r\n" + "    \"RemovedWorkGroups\": []\r\n" + "}";

			Response createUserResponse = apiUtils.submitRequest(METHOD_TYPE.POST, createUserJson, headers,
					AddUsersUrl);

			SimpleDataResponse validateResponse1 = gson.fromJson(createUserResponse.asString(),
					SimpleDataResponse.class);

			TestValidation.IsTrue((validateResponse1.Status), "User named " + up.Username + " is Created",
					"Could NOT Create User " + up.Username);

			return true;

		} catch (Exception e) {
			logError("Failed to create user - " + e.getMessage());
			return false;
		}

	}

	public HashMap<String, String> getRoleDetails(List<String> RoleLst) {
		HashMap<String, String> RoleDetails = new HashMap<String, String>();

		apiUtils = new ApiUtils();
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("Accept", "application/json, text/plain, /");
		headers.put("Authorization", "Bearer " + accessToken);
		Gson gson = new GsonBuilder().create();

		try {
			String getRoleValuesJson = "{\"IsEnable\":\"true\"}";

			Response getRoleResponse = apiUtils.submitRequest(METHOD_TYPE.POST, getRoleValuesJson, headers, GetRoleUrl);

			MultipleDataResponse mdr = gson.fromJson(getRoleResponse.asString(), MultipleDataResponse.class);

			for (int j = 0; j < RoleLst.size(); j++) {

				for (int i = 0; i < mdr.Data.size(); i++) {

					if (RoleLst.get(j).equalsIgnoreCase(mdr.Data.get(i).Name)) {

						RoleDetails.put(RoleLst.get(j), mdr.Data.get(i).Id);
						break;
					}
				}

			}

			logInfo("Role Details = " + RoleDetails);
			return RoleDetails;

		} catch (Exception e) {
			logInfo("Unable to fetch Role");
			return RoleDetails;
		}
	}

	public HashMap<String, String> getAllLocationsDetails(List<String> LocationsLst) {
		HashMap<String, String> LocationDetails = new HashMap<String, String>();

		apiUtils = new ApiUtils();
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("Accept", "application/json, text/plain, /");
		headers.put("Authorization", "Bearer " + accessToken);
		Gson gson = new GsonBuilder().create();

		try {
			String getRoleValuesJson = "{\"IsEnable\":\"true\"}";

			Response getLocationsResponse = apiUtils.submitRequest(METHOD_TYPE.POST, getRoleValuesJson, headers,
					GetAllLocationsUrl);

			MultipleDataResponse mdr = gson.fromJson(getLocationsResponse.asString(), MultipleDataResponse.class);

			for (int j = 0; j < LocationsLst.size(); j++) {

				for (int i = 0; i < mdr.Data.size(); i++) {

					if (LocationsLst.get(j).equalsIgnoreCase(mdr.Data.get(i).Name)) {

						LocationDetails.put(LocationsLst.get(j), mdr.Data.get(i).Id);

						break;
					}
				}

			}

			logInfo("Location Details = " + LocationDetails);
			return LocationDetails;

		} catch (Exception e) {
			logInfo("Unable to fetch Location Details");
			return LocationDetails;
		}
	}

	public List<String> verifyLocationCreation(List<String> LocationsLst) {
		// HashMap<String, String> LocationDetails = new HashMap<String, String>();

		apiUtils = new ApiUtils();
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("Accept", "application/json, text/plain, /");
		headers.put("Authorization", "Bearer " + accessToken);
		Gson gson = new GsonBuilder().create();

		List<String> LocationList = new ArrayList<String>();

		try {
			String getRoleValuesJson = "{\"IsEnable\":\"true\"}";

			Response getLocationsResponse = apiUtils.submitRequest(METHOD_TYPE.POST, getRoleValuesJson, headers,
					GetAllLocationsUrl);

			MultipleDataResponse mdr = gson.fromJson(getLocationsResponse.asString(), MultipleDataResponse.class);

			int existFlag = 0;
			for (int j = 0; j < LocationsLst.size(); j++) {
				existFlag = 0;
				for (int i = 0; i < mdr.Data.size(); i++) {
					if ((LocationsLst.get(j).equalsIgnoreCase(mdr.Data.get(i).Name))) {
						existFlag = 0;
						break;
					}
					existFlag = 1;
				}
				if(existFlag==1) {
					LocationList.add(LocationsLst.get(j));
				}
			}

			logInfo("Added Location Details for new locations - " + LocationList);
			return LocationList;

		} catch (Exception e) {
			logInfo("Unable to fetch Location Details");
			return LocationList;
		}
	}

	public boolean createUser(UserParams up) {
		FormDesigner formDesigner = new FormDesigner();

		try {

			apiUtils = new ApiUtils();
			Gson gson = new GsonBuilder().create();
			Map<String, String> headers = new HashMap<String, String>();
			headers.put("Content-Type", "application/json");
			headers.put("Accept", "application/json, text/plain, /");
			headers.put("Authorization", "Bearer " + accessToken);
			String locationIdsLst = "";

			HashMap<String, String> LocHashMap = new HashMap<String, String>();

			if(up.LocationCat!=null) {
				String locId = formDesigner.verifylocationCategory(locationCatID, up.LocationCat);
				if (locId.equalsIgnoreCase("")) {
					formCreationWrapper.createResourceCategory_Location(up.LocationCat);
				}
			}

			if (up.LocationNmIds.get(0).equalsIgnoreCase("ALL")) {

				locationIdsLst = "00000000-0000-0000-0000-000000000000";
				LocHashMap.put("ALL", locationIdsLst);

			}else{

				List<String> LocDetailsExsting = verifyLocationCreation(up.LocationNmIds);

				if (LocDetailsExsting != null) {
					for (int i = 0; i < LocDetailsExsting.size(); i++) {
						formCreationWrapper.createResourceInstance_Location(LocDetailsExsting.get(i));
					}
				}

				LocHashMap = getAllLocationsDetails(up.LocationNmIds);
			}

			locationIdsLst = Support.updateUserFieldValues(LocHashMap);

			HashMap<String, String> DefLocHashMap = getAllLocationsDetails(Arrays.asList(up.DefaultLocation));
			HashMap<String, String> RoleHashMap = getRoleDetails(up.Roles);

			String DeflocationIdsLst = Support.updateUserFieldValues(DefLocHashMap);
			String roleLst = Support.updateUserFieldValues(RoleHashMap);

			// 4. Create Task
			String createUserJson = "{\r\n" + "    \"Username\": \"" + up.Username + "\",\r\n"
					+ "    \"ClearPassword\": \"" + up.ClearPassword + "\",\r\n" + "    \"FirstName\": \""
					+ up.FirstName + "\",\r\n" + "    \"LastName\": \"" + up.LastName + "\",\r\n" + "    \"Email\": \""
					+ up.Email + "\",\r\n" + "    \"Locations\": [\r\n" + locationIdsLst + "    ],\r\n"
					+ "    \"Roles\": [\r\n" + roleLst + "    ],\r\n" + "    \"TimeZone\": {\r\n"
					+ "        \"Id\": \"Asia/Kolkata\",\r\n" + "        \"DisplayName\": \"" + up.TimeZone + "\"\r\n"
					+ "    },\r\n" + "    \"WorkGroups\": [],\r\n" + "    \"Pin\": \"" + up.Pin
					+ "\",\r\n" + "    \"EmployeeId\": \"" + up.EmployeeId + "\",\r\n" + "    \"Phone\": \"" + up.Phone
					+ "\",\r\n" + "    \"DefaultLocation\": " + DeflocationIdsLst + ",\r\n"
					+ "    \"isUserNameAvailable\": true,\r\n" + "    \"AddedLocation\": [\r\n" + locationIdsLst
					+ "    ],\r\n" + "    \"RemovedLocation\": [],\r\n" + "    \"AddedRoles\": [\r\n" + roleLst
					+ "    ],\r\n" + "    \"RemovedRoles\": [],\r\n" + "    \"AddedWorkGroups\": []"
					+ "    ,\r\n" + "    \"RemovedWorkGroups\": []\r\n" + "}";

			Response createUserResponse = apiUtils.submitRequest(METHOD_TYPE.POST, createUserJson, headers,
					AddUsersUrl);

			SimpleDataResponse validateResponse1 = gson.fromJson(createUserResponse.asString(),
					SimpleDataResponse.class);

			TestValidation.IsTrue((validateResponse1.Status), "User named " + up.Username + " is Created",
					"Could NOT Create User " + up.Username);

			return true;

		} catch (Exception e) {
			logError("Failed to create user - " + e.getMessage());
			return false;
		}

	}
	
	public boolean resetPassword(String username,String password) {

		apiUtils = new ApiUtils();
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("Accept", "application/json, text/plain, /");
		headers.put("Authorization", "Bearer " + accessToken);
		Gson gson = new GsonBuilder().create();
		String resetPassword = null;

		try {
			ResourceDesigner resourceDesigner = new ResourceDesigner();
			String UserId = resourceDesigner.getUserFieldId(username);

			resetPassword = "{\"UserId\":\""+UserId+"\",\"NewPassword\":\""+password+"\"}";

			Response resetPassswordResponse = apiUtils.submitRequest(METHOD_TYPE.POST, resetPassword, headers,
					ResetPasswordUrl);
			SingleDataResponse validateRes = gson.fromJson(resetPassswordResponse.asString(), SingleDataResponse.class);
			TestValidation.IsTrue((validateRes.Status),
					"Password is reset for user " + username ,
					"Could NOT reset password for " + username);

			logInfo("Password reset succesful");
			return true;

		}catch (Exception e) {
			logInfo("Could not reset password");
			return false;
		}
	}
}
