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

public class Roles extends Auth {

	public String GetAllRolesUrl = baseURI + RolesURLs.GET_ALL_ROLES_URL;
	public String AddRolesUrl = baseURI + RolesURLs.ADD_ROLE_URL;
	public String GetPermissionForRole = baseURI + RolesURLs.GET_PERMISSION_ROLE_URL;
	public String EnableDisableRole = baseURI + RolesURLs.ENABLE_DISABLE_ROLE_URL;
	public String CopyRole = baseURI + RolesURLs.COPY_ROLE_URL;


	ApiUtils apiUtils = new ApiUtils();
	Gson gson = new GsonBuilder().create();
	String RoleId;

	/**
	 * This method is used to get all the roles details
	 * @author choubey_a
	 * @param role name 
	 * @return String. It will return the role Id of the role name provided in parameters
	 */

	public boolean getAllRoles(String rolename) {
		try {
			apiUtils = new ApiUtils();
			Gson gson = new GsonBuilder().create();
			Map<String, String> headers = new HashMap<String, String>();
			headers.put("Content-Type", "application/json");
			headers.put("Accept", "application/json, text/plain, /");
			headers.put("Authorization", "Bearer " + accessToken);

			String request = "{\"IsEnable\":\"false\"}";

			Response response = apiUtils.submitRequest(METHOD_TYPE.POST,request,headers, GetAllRolesUrl);

			MultipleDataResponse mdr = gson.fromJson(response.asString(), MultipleDataResponse.class);

			for (int i = 0; i < mdr.Data.size(); i++) {
				if (rolename.equalsIgnoreCase(mdr.Data.get(i).Name)) {
					RoleId = mdr.Data.get(i).Id;
					logInfo("Role ID= " + RoleId);
					if(mdr.Data.get(i).IsEnable = true) {
						logInfo("Role is enabled");
					}
					else {
						logInfo("Role is disabled");
					}

				}
			}

			return true;
		}catch (Exception e) {			
			logError("Failed to get role details of - '"+rolename+"' - "+ e.getMessage());
			return false;
		}
	}



	/**
	 * This method is used to add role
	 * @author choubey_a
	 * @param rolename 
	 * @return String. It will return the role Id of the role name provided in parameters
	 */

	public String addRole(String rolename) {
		try {
			apiUtils = new ApiUtils();
			Gson gson = new GsonBuilder().create();
			Map<String, String> headers = new HashMap<String, String>();
			headers.put("Content-Type", "application/json");
			headers.put("Accept", "application/json, text/plain, /");
			headers.put("Authorization", "Bearer " + accessToken);

			String request = "{\"Name\":\""+rolename+"\"}";

			Response response = apiUtils.submitRequest(METHOD_TYPE.POST,request,headers, AddRolesUrl);

			SingleDataResponse sdr = gson.fromJson(response.asString(), SingleDataResponse.class);

			RoleId = sdr.Data.Id;

			logInfo("Role created - "+ rolename);

		}catch (Exception e) {
			logError("Failed to get role Id- '"+rolename+"' - "+ e.getMessage());
		}
		return RoleId;
	}

	/**
	 * This method is used to get all the permissions for a role
	 * @author choubey_a
	 * @param rolename and roleID
	 * @return String. It will return true if 
	 */

	public boolean getAllPermissionsForRole(String rolename,String roleid) {
		try {
			apiUtils = new ApiUtils();
			Gson gson = new GsonBuilder().create();
			Map<String, String> headers = new HashMap<String, String>();
			headers.put("Content-Type", "application/json");
			headers.put("Accept", "application/json, text/plain, /");
			headers.put("Authorization", "Bearer " + accessToken);

			String request = "{\"take\":100,\"skip\":0,\"page\":1,\"pageSize\":100,"
					+ "\"Parameters\":{\"Resource\":{\"Id\":\""+roleid+"\","
					+ "\"Name\":\""+rolename+"\"},\"SearchString\":\"\","
					+ "\"Columns\":[],\"FirstRowId\":null,\"LastRowId\":null},"
					+ "\"FirstRowId\":null,\"LastRowId\":null}";

			Response response = apiUtils.submitRequest(METHOD_TYPE.POST,request,headers, GetPermissionForRole);

			SingleDataResponse sdr = gson.fromJson(response.asString(), SingleDataResponse.class);

			for (int i = 0; i < sdr.Data.Rows.size(); i++) {
				if (!(sdr.Data.Rows.get(i).HasPermission == true)) {					
					logError("Permission is not true for -"+ sdr.Data.Rows.get(i));
					return false;
				}
			}
			logInfo("All the permissions has been linked to the role - "+ rolename);
			return true;
		}catch (Exception e) {			
			logError("Failed to get all the permissions of the role- '"+rolename+"' - "+ e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to enable or disable role
	 * @author choubey_a
	 * @param role id and flag 
	 * @return void
	 */

	public void enableDisableRole(String roleid, boolean flag) {
		try {
			apiUtils = new ApiUtils();
			Gson gson = new GsonBuilder().create();
			Map<String, String> headers = new HashMap<String, String>();
			headers.put("Content-Type", "application/json");
			headers.put("Accept", "application/json, text/plain, /");
			headers.put("Authorization", "Bearer " + accessToken);

			String request = "{\"Id\":\""+roleid+"\",\"IsEnable\":"+flag+"}";

			Response response = apiUtils.submitRequest(METHOD_TYPE.POST,request,headers, EnableDisableRole);

			SimpleDataResponse sdr = gson.fromJson(response.asString(), SimpleDataResponse.class);

			if (sdr.Status == true) {

				if(flag == true) {

					logInfo("Role has been enabled");
				}

				else {
					logInfo("Role has been disabled");
				}

			}
		}catch (Exception e) {			
			logError("Failed to enable or diable role- "+ e.getMessage());
		}
	}

	/**
	 * This method is used to copy role
	 * @author choubey_a
	 * @param roleid and rolename of the role from which it has to copied 
	 * @return true if role will be copied
	 */

	public String copyRole(String roleid, String rolename) {		
		String copyrolename = null;
		try {
			apiUtils = new ApiUtils();
			Gson gson = new GsonBuilder().create();
			Map<String, String> headers = new HashMap<String, String>();
			headers.put("Content-Type", "application/json");
			headers.put("Accept", "application/json, text/plain, /");
			headers.put("Authorization", "Bearer " + accessToken);

			String request = "{\r\n" + 
					"   \"UserRefs\":[\r\n" + 
					"      \r\n" + 
					"   ],\r\n" + 
					"   \"IsEnable\":true,\r\n" + 
					"   \"Id\":\""+roleid+"\",\r\n" + 
					"   \"Name\":\""+rolename+"\"\r\n" + 
					"}";
			Response response = apiUtils.submitRequest(METHOD_TYPE.POST,request,headers, CopyRole);

			SingleDataResponse sdr = gson.fromJson(response.asString(), SingleDataResponse.class);

			String roleId = sdr.Data.Id;
			copyrolename = sdr.Data.Name;

			if(sdr.Status = true) {
				logInfo("Role copied with name - "+copyrolename+ "and Id - "+roleId);
			}

		}catch(Exception e) {
			logError("Failed to copy role- "+e);
		}
		return copyrolename;
	}


	public class Rows{
		public boolean HasPermission;
	}

	public class Data {
		public boolean Status;
		public String Name;
		public String Id;
		public boolean IsEnable;
		public List<Rows> Rows;
	}

	public class SingleDataResponse {
		public Data Data;
		public boolean Status;
	}

	public class MultipleDataResponse {		
		public List<Data> Data;
	}

	public class SimpleDataResponse {
		public Data Data;
		public boolean Status;
	}
}

