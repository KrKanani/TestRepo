package com.project.safetychain.api.models;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.project.utilities.ApiUtils;

public class ManageLocation extends Auth {

	public String AddLocationUrl = baseURI + ManageLocationUrls.ADD_LOCATION_URL;
	
	public String otherAddLocationUrl = otherBaseURI + ManageLocationUrls.ADD_LOCATION_URL;
	
	ApiUtils au = new ApiUtils();
	Gson gson = new GsonBuilder().create();

	public class Data {
		public boolean IsEnable;
		public String Id;
		public String Name;
	}

}
