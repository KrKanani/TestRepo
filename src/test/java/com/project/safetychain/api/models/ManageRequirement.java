package com.project.safetychain.api.models;


import java.util.HashMap;
import java.util.Map;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.project.testbase.TestValidation;
import com.project.utilities.ApiUtils;
import com.project.utilities.ApiUtils.METHOD_TYPE;

import io.restassured.response.Response;

public class ManageRequirement extends Auth{

	ApiUtils apiUtils = new ApiUtils();
	Gson gson = new GsonBuilder().create();

	public String SuppTempName;
	public String Id;
	public String AddSuppTempUrl =baseURI + ManageRequirementUrls.ADD_SUPPTEMP_URL;

	public String createSuppTemp(String SuppTempName) {		

		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("Accept", "application/json, text/plain, /");
		headers.put("Authorization", "Bearer " + accessToken);
		Gson gson = new GsonBuilder().create();


		try {

			String addSuppTempJson = "{\"Name\": \"" + SuppTempName + "\"}";
			Response response = apiUtils.submitRequest(METHOD_TYPE.POST, addSuppTempJson, headers, AddSuppTempUrl);

			SimpleDataResponse sdr = gson.fromJson(response.asString(), SimpleDataResponse.class);
			Id = sdr.Data;
			TestValidation.IsTrue((sdr.Status), "Supplier Template created", "Could NOT create Supplier Template");

		} catch (Exception e) {
			logInfo("Unable to create suplier template");
		}
		return AddSuppTempUrl;
	}
}
