package com.project.safetychain.apiproject.models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.project.testbase.TestValidation;
import com.project.utilities.ApiUtils;
import com.project.utilities.ApiUtils.METHOD_TYPE;

import io.restassured.response.Response;

public class Program extends Auth {

	public String programTmp_URL = baseURI + ProgramURLs.programTmp_HEADERS_URL;
	public String programElm_URL = baseURI + ProgramURLs.programElm_HEADERS_URL;
	public String getProgramIntId_URL = baseURI + ProgramURLs.getProgramIntId_URL;
	public String programDocTypeAdd_URL = baseURI + ProgramURLs.programDocTypeAdd_HEADERS_URL;
	public String programDocUpload_URL = baseURI + ProgramURLs.docElementUpload_URL;
	public String programAddDocUpload_URL = baseURI + ProgramURLs.addDocs_URL;
	
	public String programDetails_URL = baseURI + ProgramURLs.progElementDetails_URL;
	public String programFormLink_URL = baseURI + ProgramURLs.progFormLink_URL;
	
	public String programViewGetDocs_URL = baseURI + ProgramViewerURLs.programViewGetDocs_URL;

	public String createdProgramTempId = null;
	public String createdProgramTempIntId = null;
	public String createdProgramElemId = null;
	
	public String programDetails = null;
	public String verifyDocsForProgView = null;

	ApiUtils apiUtils = new ApiUtils();
	Gson gson = new GsonBuilder().create();
	
	/**
	 * This method is used to create Program Template 
	 * @author nimmani_j
	 * @param programTempName - Creating Program Template Name 
	 * @return createdProgramTempId
	 */


	public String createProgramTemp(String programTempName) {

		apiUtils = new ApiUtils();

		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("Accept", "application/json, text/plain, /");
		headers.put("Authorization", "Bearer " + accessToken);
		Gson gson = new GsonBuilder().create();

		try {

			String request = "{\"Name\":\"" + programTempName + "\"}";
			Response response = apiUtils.submitRequest(METHOD_TYPE.POST, request, headers, programTmp_URL);
			SingleDataResponse1 sdr = gson.fromJson(response.asString(), SingleDataResponse1.class);
			createdProgramTempId = sdr.Data.Id;
			TestValidation.IsTrue((sdr.Status), "Program Template is created", "Could NOT create Program Template");
			return createdProgramTempId;

		} catch (Exception e) {
			logInfo("Unable to create Program Template Id -" + e.getMessage());
			return createdProgramTempId;

		}
	}
	
	/**
	 * This method is used to get Program Template Integer Id's
	 * @author nimmani_j
	 * @param programTempName - Program Template Name
	 * @param createdProgramTempIntId - program Template Integer Id
	 * @return createdProgramTempIntId
	 */


	public String getProgramTempIntId(String programTempName, String createdProgramTempIntId) {

		apiUtils = new ApiUtils();

		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("Accept", "application/json, text/plain, /");
		headers.put("Authorization", "Bearer " + accessToken);
		Gson gson = new GsonBuilder().create();

		try {

			String requestGetProgTempIntId = "{\"Id\":\"" + createdProgramTempId + "\",\"Name\":\"" + programTempName
					+ "\"}";

			Response responseGetProgTempIntId = apiUtils.submitRequest(METHOD_TYPE.POST, requestGetProgTempIntId,
					headers, getProgramIntId_URL);
			SimpleDataResponse sdr1 = gson.fromJson(responseGetProgTempIntId.asString(), SimpleDataResponse.class);
			createdProgramTempIntId = sdr1.Data;
			TestValidation.IsTrue((sdr1.Status), "Program Template Internal Id is created",
					"Could NOT create Program Template Internal Id");
			return createdProgramTempIntId;

		} catch (Exception e) {
			logInfo("Unable to create Program Template Id -" + e.getMessage());
			return createdProgramTempIntId;

		}
	}
	
	
	/**
	 * This method is used to create Program Element 
	 * @author nimmani_j
	 * @param programTempName -  Program Template Name folder
	 * @param createdProgramTempId -  Program Template Id
	 * @param createdProgramTempIntId - program Template Integer Id
	 * @param programElementName - Program Element Name
	 * @return createdProgramElemId
	 */

	public String createProgramElem(String programTempName, String createdProgramTempId, String createdProgramTempIntId,
			String programElementName) {

		apiUtils = new ApiUtils();

		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("Accept", "application/json, text/plain, /");
		headers.put("Authorization", "Bearer " + accessToken);
		Gson gson = new GsonBuilder().create();

		try {

			String requestElement = "{\"Program\":{\"Name\":\"" + programTempName + "\",\"Id\":\""
					+ createdProgramTempId + "\",\"IsEnable\":false,\"IsEnabled\":true,\"IntegerId\":"
					+ createdProgramTempIntId + "},\"ParentId\":\"" + createdProgramTempId + "\",\"Name\":\""
					+ programElementName + "\",\"childType\":\"element\"}";

			Response responseGetProgElemId = apiUtils.submitRequest(METHOD_TYPE.POST, requestElement, headers,
					programElm_URL);
			SingleDataResponse1 sdr2 = gson.fromJson(responseGetProgElemId.asString(), SingleDataResponse1.class);
			createdProgramElemId = sdr2.Data.Id;
			TestValidation.IsTrue((sdr2.Status), "Program Template Internal Id is created",
					"Could NOT create Program Template Internal Id");
			return createdProgramElemId;

		} catch (Exception e) {
			logInfo("Unable to create Program Template Id -" + e.getMessage());
			return createdProgramElemId;

		}
	}
	
	/**
	 * This method is used to Add Document Typee to Program Element 
	 * @author nimmani_j
	 * @param createdProgramTempId - program Template Id
	 * @param createdProgramElemId - program Element Id
	 * @param docElementUploadId - upload document to Program Element
	 * @return true if document type is added in program Element
	 */

	public boolean AddDocTypeToProgramElem(String createdProgramTempId, String createdProgramElemId,
			String docElementUploadId) {

		apiUtils = new ApiUtils();

		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("Accept", "application/json, text/plain, /");
		headers.put("Authorization", "Bearer " + accessToken);
		Gson gson = new GsonBuilder().create();

		try {

			String requestElement = "{\"AddedDocumentTypes\":[\"" + docElementUploadId + "\"],\"ElementId\":\""
					+ createdProgramElemId + "\",\"ProgramId\":\"" + createdProgramTempId
					+ "\",\"RemovedDocumentTypes\":[]}";

			Response responseGetProgElemId = apiUtils.submitRequest(METHOD_TYPE.POST, requestElement, headers,
					programDocUpload_URL);
			SingleDataResponse sdr2 = gson.fromJson(responseGetProgElemId.asString(), SingleDataResponse.class);

			TestValidation.IsTrue((sdr2.Status), "Program Template Internal Id is created",
					"Could NOT create Program Template Internal Id");
			return true;

		} catch (Exception e) {
			logInfo("Unable to create Program Template Id -" + e.getMessage());
			return false;
		}

	}
	
	/**
	 * This method is used to Add Documents to Program Element 
	 * @author nimmani_j
	 * @param createdProgramTempId -  program Template Id
	 * @param createdProgramElemId -  program Element Id
	 * @param docElementUploadId - Document Id uploaded to program Element
	 * @return true if document is added
	 */

	public boolean AddDocsToProgramElem(String createdProgramTempId, String createdProgramElemId,
			String docElementUploadId) {

		apiUtils = new ApiUtils();

		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("Accept", "application/json, text/plain, /");
		headers.put("Authorization", "Bearer " + accessToken);
		Gson gson = new GsonBuilder().create();

		try {

			String requestElement = "{\"AddedDocuments\":[\"" + docElementUploadId + "\"],\"ElementId\":\""
					+ createdProgramElemId + "\",\"ProgramId\":\"" + createdProgramTempId
					+ "\",\"RemovedDocumentTypes\":[]}";

			Response responseGetProgElemId = apiUtils.submitRequest(METHOD_TYPE.POST, requestElement, headers,
					programAddDocUpload_URL);
			SingleDataResponse sdr2 = gson.fromJson(responseGetProgElemId.asString(), SingleDataResponse.class);

			TestValidation.IsTrue((sdr2.Status), "Program Template Internal Id is created",
					"Could NOT create Program Template Internal Id");
			return true;

		} catch (Exception e) {
			logInfo("Unable to create Program Template Id -" + e.getMessage());
			return false;

		}
	}
	
	/**
	 * This method is used to Add Documents to Program Element 
	 * @author nimmani_j
	 * @param progDetails -  Added Text 
	 * @param ElemId -  program Element Id
	 * @param ProgTempId - ProgTempId
	 * return true if details are added to Program Element
	 */
	
	public boolean programDetails(String progDetails, String ElemId, String ProgTempId ) {

		apiUtils = new ApiUtils();

		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("Accept", "application/json, text/plain, /");
		headers.put("Authorization", "Bearer " + accessToken);
		Gson gson = new GsonBuilder().create();

		try {

			String request = "{\"ElementId\":\""+ElemId+"\",\"ProgramId\":\""+ProgTempId+"\",\"Detail\":\""+progDetails+"\"}";
			Response response = apiUtils.submitRequest(METHOD_TYPE.POST, request, headers, programDetails_URL);
			SingleDataResponse sdr = gson.fromJson(response.asString(), SingleDataResponse.class);
			
			TestValidation.IsTrue((sdr.Status), "Program Template Details Added", "Could NOT add Program Template Details");
			return true;
			
		} catch (Exception e) {
			logInfo("Unable to create Program Template Id -" + e.getMessage());
			return false;
 
		}
	}
	
	/**
	 * This method is used to Add Forms Program Element 
	 * @author nimmani_j
	 * @param createdProgramTempId -  program Template ID
	 * @param createdProgramElemId -  program Element Id
	 * @param formId - Form Id
	 * @return true if form is added to Program Element
	 */
	
	
	public boolean AddFormsToProgramElem(String createdProgramTempId, String createdProgramElemId,
			String formId) {

		apiUtils = new ApiUtils();

		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("Accept", "application/json, text/plain, /");
		headers.put("Authorization", "Bearer " + accessToken);
		Gson gson = new GsonBuilder().create();

		try {

			String requestElement = "{\"ElementId\":\""+createdProgramElemId+"\",\"ProgramId\":\""+createdProgramTempId+"\",\"AddedForms\":[\""+formId+"\"],\"RemovedForms\":[]}";

			Response responseGetProgElemId = apiUtils.submitRequest(METHOD_TYPE.POST, requestElement, headers,
					programFormLink_URL);
			SingleDataResponse sdr2 = gson.fromJson(responseGetProgElemId.asString(), SingleDataResponse.class);

			TestValidation.IsTrue((sdr2.Status), "Form added to program Template",
					"unable to link Form");
			return true;

		} catch (Exception e) {
			logInfo("Unable to link Form Id -" + e.getMessage());
			return false;
		}

	}
	
	
	public class SingleDataResponse1 {
		public Data Data;
		public boolean Status;
		public String Errors;
		public boolean RefreshToken;
	}

	public class Data {
		public String Id;
		public String Name;
		public List<Rows> Rows;
	}

	public class SimpleDataResponse {
		public String Data;
		public boolean Status;
		public String Errors;
		public boolean RefreshToken;
	}
	

	public class Rows{
		public String Name;
		public Type Type;
		public String FileName;
		
	}

	
	public class Type{
		public String Name;
		public String Id;
	}

}
