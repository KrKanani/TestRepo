package com.project.safetychain.apiproject.models;

import static org.testng.Assert.assertTrue;

import com.project.safetychain.apiproject.models.Program.SingleDataResponse1;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.project.utilities.ApiUtils;
import com.project.utilities.ApiUtils.METHOD_TYPE;

import io.restassured.response.Response;

public class ProgramViewer extends Auth {
	
	public String programViewGetDocs_URL = baseURI + ProgramViewerURLs.programViewGetDocs_URL;
	public String programViewGetForm_URL = baseURI + ProgramViewerURLs.programViewGetForm_URL;
	public String programViewGetRecords_URL = baseURI + ProgramViewerURLs.programViewGetRecords_URL;

	public String createdProgramTempIntId = null;
	public String createdProgramElemId = null;
	

	ApiUtils apiUtils = new ApiUtils();
	Gson gson = new GsonBuilder().create();
	
	/**
	 * This method is used to verify Documents
	 * @author nimmani_j
	 * @param programTempId,progElemId,fileName
	 * @param programTempName - Creating Program Template Name 
	 * @return true if Document is Verified
	 */


	public boolean verifyDocsForProgViewer(String programTempId, String progTempName, String progElemId,String docName, String fileName) {

		apiUtils = new ApiUtils();

		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("Accept", "application/json, text/plain, /");
		headers.put("Authorization", "Bearer " + accessToken);
		Gson gson = new GsonBuilder().create();
		
		boolean flag = false;

		try {

			String request = "{\"take\":100,\"skip\":0,\"page\":1,\"pageSize\":100,\"Parameters\":{\"Item\":{\"Id\":\""+programTempId+"\",\"Name\":\""+progTempName+"\",\"Type\":\"Resource\"},\"Data\":{\"Id\":\""+progElemId+"\",\"Name\":\""+progTempName+"\"},\"ResourceType\":\"Program\",\"SearchCriteria\":\"\",\"ElementId\":\""+progElemId+"\",\"FirstRowId\":null,\"LastRowId\":null},\"FirstRowId\":null,\"LastRowId\":null}";
			Response response = apiUtils.submitRequest(METHOD_TYPE.POST, request, headers, programViewGetDocs_URL);
			SingleDataResponse1 sdr = gson.fromJson(response.asString(), SingleDataResponse1.class);
			System.out.println(sdr.Data.Rows.get(0).Type.Name);
			
			for(int i =0;i<sdr.Data.Rows.size();i++) {
				if(sdr.Data.Rows.get(i).Type.Name.equalsIgnoreCase(docName) && sdr.Data.Rows.get(i).FileName.equalsIgnoreCase(fileName)) {
					flag = true;
					break;
				}
			}
			assertTrue(flag);
			return flag;

		} catch (Exception e) {
			logInfo("Unable to create Program Template Id -" + e.getMessage());
			return flag;

		}
	}
	
	/**
	 * This method is used to verify Forms
	 * @author nimmani_j
	 * @param progElemId,fileName 
	 * @return true if Form is Verified
	 */
	
	public boolean verifyFormForProgViewer( String progElemId, String formName) {

		apiUtils = new ApiUtils();
		boolean flag = false;

		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("Accept", "application/json, text/plain, /");
		headers.put("Authorization", "Bearer " + accessToken);
		Gson gson = new GsonBuilder().create();
	

		try {

			String request = "{\"take\":100,\"skip\":0,\"page\":1,\"pageSize\":100,\"Parameters\":{\"ElementId\":\""+progElemId+"\",\"FromDate\":\"\",\"ToDate\":\"\",\"FormName\":[],\"FormType\":[],\"ModifiedBy\":[],\"FirstRowId\":null,\"LastRowId\":null},\"FirstRowId\":null,\"LastRowId\":null}";
			Response response = apiUtils.submitRequest(METHOD_TYPE.POST, request, headers, programViewGetForm_URL);
			SingleDataResponse sdr = gson.fromJson(response.asString(), SingleDataResponse.class);
			for(int i =0;i<sdr.Data.Rows.size();i++) {
				if(formName.equalsIgnoreCase(sdr.Data.Rows.get(i).Name)) {
					flag = true;
					break;
				}
			}
			assertTrue(flag);
			return flag;

		} catch (Exception e) {
			logInfo("Unable to validate form in program Viewer -" + e.getMessage());
			return flag;

		}
	}
	
	/**
	 * This method is used to verify Records
	 * @author nimmani_j
	 * @param progElemId,fileName 
	 * @return true if Form is Verified
	 */
	
	public boolean verifyRecordsForProgViewer( String progElemId, String formName) {

		apiUtils = new ApiUtils();
		boolean flag = false;

		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("Accept", "application/json, text/plain, /");
		headers.put("Authorization", "Bearer " + accessToken);
		Gson gson = new GsonBuilder().create();

		try {

			String request = "{\"take\":100,\"skip\":0,\"page\":1,\"pageSize\":100,\"Parameters\":{\"ElementId\":\""+progElemId+"\",\"FromDate\":\"\",\"ToDate\":\"\",\"IsCompliance\":\"\",\"Resource\":[],\"FormName\":[],\"FormType\":[],\"Program\":[],\"CompletedBy\":[],\"FirstRowId\":null,\"LastRowId\":null},\"FirstRowId\":null,\"LastRowId\":null}";
			Response response = apiUtils.submitRequest(METHOD_TYPE.POST, request, headers, programViewGetRecords_URL);
			SingleDataResponse sdr = gson.fromJson(response.asString(), SingleDataResponse.class);
			
			for(int i =0;i<sdr.Data.Rows.size();i++) {
				if(sdr.Data.Rows.get(i).Name.equalsIgnoreCase(formName)) {
					flag = true;
					break;
				}
			}
			assertTrue(flag);
			return flag;

		} catch (Exception e) {
			logInfo("Unable to verfiy record -" + e.getMessage());
			return flag;

		}
	}


	public class SingleDataResponse {
		public Data Data;
		public boolean Status;
		public String Errors;
		public boolean RefreshToken;
	
	}

	public class Rows{
		public String Name;
		public String FileName;
		
	}

	public class Data {
		public List<Rows> Rows;
	}

	public class Type{
		public String Name;
		public String Id;
	}
	public static class FormSubDetails {
		public String formrefId;
		public String formTypeId;
		public String formId;
		public String formName;
		public String locationName;
		public String locationId;
		public String ResourceName;
		public String ResourceId;
		public String comment;


	}


}



