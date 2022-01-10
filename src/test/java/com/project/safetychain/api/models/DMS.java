package com.project.safetychain.api.models;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.project.testbase.TestValidation;
import com.project.utilities.ApiUtils;
import com.project.utilities.ApiUtils.METHOD_TYPE;

import io.restassured.response.Response;

public class DMS extends Auth{
	String docTypeId = null;
	String createdDocId = null;

	public String dmsHeaderUrl = baseURI + DMSUrls.DMS_HEADERS_URL;
	public String doctypeUrl = baseURI + DMSUrls.ADD_DOCTYPE_URL;
	public String docuploadUrl = baseURI + DMSUrls.ADD_DOCUMENT_URL;

	ApiUtils apiUtils = new ApiUtils();
	Gson gson = new GsonBuilder().create();

	public String getDocumentTypeId() {
		String docTypeId = null;
		apiUtils = new ApiUtils();

		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("Accept", "application/json, text/plain, /");
		headers.put("Authorization", "Bearer " + accessToken);
		Gson gson = new GsonBuilder().create();
		try {

			Response getChartListResponse = apiUtils.submitRequest(METHOD_TYPE.GET, null, headers, dmsHeaderUrl);
			MultipleDataResponse sdr = gson.fromJson(getChartListResponse.asString(), MultipleDataResponse.class);

			for (int i = 0; i < sdr.Data.size(); i++) {
				System.out.println(" sdr.Data.size()" + sdr.Data.size());
				if (sdr.Data.get(i).Name.equalsIgnoreCase("Document Types")) {
					docTypeId = sdr.Data.get(i).Id;
					logInfo("DocTypeId= " + docTypeId);
					return docTypeId;
				}

			}
			return docTypeId;

		} catch (Exception e) {
			logInfo("Unable to fetch docTypeId");
			return docTypeId;
		}
	}

	public String createDocType(String docTypeName) {
		
		apiUtils = new ApiUtils();

		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("Accept", "application/json, text/plain, /");
		headers.put("Authorization", "Bearer " + accessToken);
		Gson gson = new GsonBuilder().create();
		docTypeId = getDocumentTypeId();
		try {

			String addDocTypeJson = "{\"DocItem\":{\"Id\":\""+docTypeId+"\",\"Name\":\"Document Types\"},"
					+ "\"Resource\":{\"Id\":null,\"Name\":\""+docTypeName+"\"}}";
			Response response = apiUtils.submitRequest(METHOD_TYPE.POST, addDocTypeJson, headers, doctypeUrl);

			SingleDataResponse sdr = gson.fromJson(response.asString(), SingleDataResponse.class);
			createdDocId = sdr.Data.Id;
			TestValidation.IsTrue((sdr.Status), "Doc Type  is created", "Could NOT create Doc Type ");

			return createdDocId;

		} catch (Exception e) {
			logInfo("Unable to create Doc Type Id");
			return createdDocId;

		}
	}

	public String uploadDoc(String docTypeName) {
		String createdDocId = null;
		apiUtils = new ApiUtils();

		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "multipart/form-data");
		headers.put("Accept", "application/json, charset=utf-8, /");
		headers.put("Authorization", "Bearer " + accessToken);
		//headers.put("Content-Disposition", "form-data";"name=\"aFile\"";"filename=\"avatar.png\"");
		
	//	Content-Disposition: form-data; name="files"; filename="test.jpg"
		Gson gson = new GsonBuilder().create();
	//	docTypeId = getDocumentTypeId();

		try {
		//	String docRequest="{\"DocItem\":{\"Id\":"+docTypeId+",\"Name\":\"Document Types\",\"Type\":\"Others\"},\"Resource\":{\"Id\":"+createdDocId+",\"Name\":"+docTypeName+"}}";
			
		//	String docRequest="{\"DocItem\":{\"Id\":\"f89aebf4-0215-4cd9-8b36-3cd3a5c67861\",\"Name\":\"Document Types\",\"Type\":\"Others\"},\"Resource\":{\"Id\":\"2a1fe82b-a1eb-4062-9710-050fc8ffc0d8\",\"Name\":\"Unique\"}}";
			String docRequest="{\"DocItem\":{\"Id\":\"f89aebf4-0215-4cd9-8b36-3cd3a5c67861\",\"Name\":\"Document Types\",\"Type\":\"Others\"},\"Resource\":{\"Id\":\"7bda9c7d-27b8-4ead-9ee9-459669f9bf8e\",\"Name\":\"unique\"}}";
			Map<String, String> formData = new HashMap<String, String>();
			formData.put("IsApproved", "true");
			formData.put("DocumentRequest", docRequest);
			formData.put("Content-Disposition", " form-data; name=\"files\"; filename=Screenshot.jpg");
			formData.put("name", "files");
			formData.put("filename", "Screenshot.jpg");
			//formData.put("files", "Screenshot.jpg");
			
//			String addDocTypeJson = "{\"DocItem\":{\"Id\":\""+ docTypeId +"\",\"Name\":\"Document Types\"},"
//					+ "\"Resource\":{\"Id\":null,"
//					+ "\"Name\":\""+docTypeName+"}";
			Response response = apiUtils.submitRequestForImages(METHOD_TYPE.POST, formData, headers, docuploadUrl);

			SingleDataResponse sdr = gson.fromJson(response.asString(), SingleDataResponse.class);
			createdDocId = sdr.Data.Id;
			TestValidation.IsTrue((sdr.Status), "Doc Type  is created", "Could NOT create Doc Type ");

			return createdDocId;


			
		}catch (Exception e) {
			logInfo("Unable to create Doc Type Id");
			return createdDocId;

		}
	}
}
