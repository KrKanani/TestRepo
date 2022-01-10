package com.project.safetychain.apiproject.models;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.project.safetychain.apiproject.models.Auth;
import com.project.utilities.ApiUtils;
import com.project.utilities.ApiUtils.METHOD_TYPE;

import io.restassured.response.Response;

public class Users extends Auth{

	public static final String ALL_LOCATION_NAME = "ALL";
	public static final String ALL_LOCATION_ID = "00000000-0000-0000-0000-000000000000";

	public static final String SUPERADMIN_ROLE_NAME = "SuperAdmin";
	public static final String SUPERADMIN_ROLE_ID = "306d1ef8-298c-4fdc-95d1-6dc41ee612d6";

	public static final String ALL_SUPPLIER_NAME = "ALL";
	public static final String ALL_SUPPLIER_ID = "00000000-0000-0000-0000-000000000000";

	public String AddUserUrl = baseURI + UsersURLs.ADD_USER_URL;

	/**
	 * This method is used to create user
	 * @author pashine_a
	 * @param userDetails (User profile configuration details)
	 * @return boolean. It will return boolean result true if new user has been added
	 */
	public boolean createUser(UserDetails userDetails) {
		try {

			String createUserJSON = null;

			ApiUtils apiUtils = new ApiUtils();
			Gson gson = new GsonBuilder().create();

			Map<String, String> headers = new HashMap<String, String>();
			headers.put("Content-Type", "application/json");
			headers.put("Accept", "application/json, text/plain, /");
			headers.put("Authorization", "Bearer " + accessToken);

			if(userDetails.userType.equalsIgnoreCase(UserType.INTERNAL_USER)) {
				createUserJSON = getInternalUserJSON(userDetails);
			}else {
				createUserJSON = getSupplierUserJSON(userDetails);
			}

			Response createUserResponse = apiUtils.submitRequest(METHOD_TYPE.POST, createUserJSON, headers,
					AddUserUrl);

			SimpleDataResponse response = gson.fromJson(createUserResponse.asString(),
					SimpleDataResponse.class);

			if(!response.Status) {
				logError("Failed to create user - "+userDetails.username);
				return false;
			}

			logInfo("Created user - "+userDetails.username);
			return true;
		} catch (Exception e) {
			logError("Failed to create user - '"+userDetails.username+" ' - "+ e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to get internal user template(JSON payload)
	 * @author pashine_a
	 * @param userDetails (User profile configuration details)
	 * @return String. It will return create user payload
	 */
	public String getInternalUserJSON(UserDetails userDetails) {
		String userProfile =  "{"
				+ "  \"Username\": \""+userDetails.username+"\","
				+ "  \"ClearPassword\": \""+userDetails.password+"\","
				+ "  \"FirstName\": \""+userDetails.firstname+"\","
				+ "  \"LastName\": \""+userDetails.lastname+"\","
				+ "  \"Email\": \""+userDetails.email+"\","
				+ "  \"Locations\": ["
				+ getNameID(userDetails.locations)
				+ "  ],"
				+ "  \"Roles\": ["
				+ getNameID(userDetails.roles)
				+ "  ],"
				+ "  \"TimeZone\": {"
				+ "    \"Id\": \""+userDetails.timezone+"\""
				+ "  },"
				+ "  \"WorkGroups\": ["
				+ getNameID(userDetails.workgroup)
				+ "  ],"
				+ "  \"Pin\": \""+userDetails.pin+"\","
				+ "  \"EmployeeId\": \""+userDetails.employeeID+"\","
				+ "  \"Phone\": \""+userDetails.phone+"\","
				+ "  \"DefaultLocation\": \""+userDetails.defaultLocation+"\","
				+ "  \"isUserNameAvailable\": true,"
				+ "  \"AddedLocation\": ["
				+ getNameID(userDetails.locations)
				+ "  ],"
				+ "  \"RemovedLocation\": ["
				+ "    "
				+ "  ],"
				+ "  \"AddedRoles\": ["
				+ getNameID(userDetails.roles)
				+ "  ],"
				+ "  \"RemovedRoles\": ["
				+ "    "
				+ "  ],"
				+ "  \"AddedWorkGroups\": ["
				+ getNameID(userDetails.workgroup)
				+ "  ],"
				+ "  \"RemovedWorkGroups\": ["
				+ "    "
				+ "  ]"
				+ "}";	
		return userProfile;
	}


	/**
	 * This method is used to get supplier user template(JSON payload)
	 * @author pashine_a
	 * @param userDetails (User profile configuration details)
	 * @return String. It will return create user payload
	 */
	public String getSupplierUserJSON(UserDetails userDetails) {
		String userProfile = "{"
				+ "  \"Username\": \""+userDetails.username+"\","
				+ "  \"ClearPassword\": \""+userDetails.password+"\","
				+ "  \"FirstName\": \""+userDetails.firstname+"\","
				+ "  \"LastName\": \""+userDetails.lastname+"\","
				+ "  \"Email\": \""+userDetails.email+"\","
				+ "  \"Partner\": ["
				+ getNameID(userDetails.suppliers)
				+ "  ],"
				+ "  \"IsAdmin\": "+userDetails.isAdmin+","
				+ "  \"Title\": \""+userDetails.title+"\","
				+ "  \"TimeZone\": {"
				+ "    \"Id\": \""+userDetails.timezone+"\""
				+ "  },"
				+ "  \"EmployeeId\": \""+userDetails.employeeID+"\","
				+ "  \"Phone\": \""+userDetails.phone+"\","
				+ "  \"UserType\": \"PartnerPortalAdmin\","
				+ "  \"AddedPartnerRefs\": ["
				+ getNameID(userDetails.suppliers)
				+ "  ],"
				+ "  \"RemovedPartnerRefs\": ["
				+ "    "
				+ "  ],"
				+ "  \"HasAllSuppliers\": true"
				+ "}";
		return userProfile;
	}

	/**
	 * This method is used to get 'Name' & 'Id' JSON
	 * @author pashine_a
	 * @param instances (Name & Id pair)
	 * @return String. It will return Name & ID payload
	 */
	public String getNameID(String instances[][]) {
		String template = "";
		if(instances==null) {
			return template;
		}
		for(int i=0;i<instances.length;i++) {

			template = template + "{"
					+ "               \"Name\":\""+instances[i][0]+"\","
					+ "               \"Id\":\""+instances[i][1]+"\""
					+ "            }";

			if(i<instances.length-1) {
				template = template + ",";
			}
		}
		return template;
	}

	public static class UserDetails {

		public String userType = UserType.INTERNAL_USER;
		public String username;
		public String employeeID = "";
		public String password = "APT_TEST";
		public String firstname = "API Auto";
		public String lastname = "User";
		public String email = "autoapi@sctest.com";
		public String phone = "";
		public String timezone = "US/Pacific";
		public String locations[][];
		public String defaultLocation = "";
		public String roles[][];
		public String workgroup[][];
		public String pin = "";

		public String suppliers[][];
		public String title = "";
		public boolean isAdmin = true;

	}

	public static class UserType {
		public static final String INTERNAL_USER = "Internal";
		public static final String SUPPLIER_USER = "Supplier";
	}
	
	public class SimpleDataResponse {
		public Data Data;
		public boolean Status;
	}

}
