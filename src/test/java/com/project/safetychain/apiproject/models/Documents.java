package com.project.safetychain.apiproject.models;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.project.safetychain.apiproject.models.Auth;
import com.project.testbase.TestValidation;
import com.project.utilities.ApiUtils;
import com.project.utilities.ApiUtils.METHOD_TYPE;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Documents extends Auth {

	public String dmsHeaderUrl = baseURI + DocumentURLs.DMS_HEADERS_URL;
	public String doctypeUrl = baseURI + DocumentURLs.ADD_DOCTYPE_URL;
	public String docuploadUrl = baseURI + DocumentURLs.ADD_DOCUMENT_URL;
	public String docTypeDetails = baseURI + DocumentURLs.DOC_TYPE_DETAILS;
	public String searchTree = baseURI + DocumentURLs.SEARCH_TREE;

	ApiUtils apiUtils = new ApiUtils();
	Gson gson = new GsonBuilder().create();

	String docTypeId = null;
	String createdDocId = null;
	String uploadedDocId = null;

	/**
	 * This method is used to get document Type ID's
	 * @author choubey_a
	 * @param doctype - The types of doc folder
	 * @return doctype type Id
	 */

	public String getDocumentTypeId(String docType) {
		String docTypeId = null;
		apiUtils = new ApiUtils();

		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("Accept", "application/json, text/plain, /");
		headers.put("Authorization", "Bearer " + accessToken);
		Gson gson = new GsonBuilder().create();
		try {

			Response response = apiUtils.submitRequest(METHOD_TYPE.GET, null, headers, dmsHeaderUrl);
			MultipleDataResponse mdr = gson.fromJson(response.asString(), MultipleDataResponse.class);

			String dataId = null;
			for (Data data : mdr.Data) {								
				if(data.Name.equals (docType)) {
					dataId = data.Id;
					break;
				}
			}			
			switch(docType) {
			case DocType.DOCUMENTTYPES :
				docTypeId = dataId;
				break;
			case DocType.EQUIPMENT:
				docTypeId = dataId;
				break;
			case DocType.ITEMS:
				docTypeId = dataId;
				break;
			case DocType.CUSTOMERS:
				docTypeId =dataId;
				break;
			case DocType.SUPPLIERS:
				docTypeId = dataId;
				break;
			default:
				logError("Docuemnt Type : " + docType + " is incorrect");
			}			
			logInfo("Document Type : " + docType + " ID : " + dataId);				
			return docTypeId;
		} catch (Exception e) {
			logInfo("Unable to fetch docTypeId -"+e.getMessage());
			return docTypeId;
		}
	}

	/**
	 * This method is used to create document Type
	 * @author choubey_a
	 * @param doctypename - The name of document folder
	 * @param - docTypeId - The Id of the type of the document
	 * @return created DocType Id 
	 */

	public String createDocType(String docTypeName, String docTypeId) {

		apiUtils = new ApiUtils();

		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("Accept", "application/json, text/plain, /");
		headers.put("Authorization", "Bearer " + accessToken);
		Gson gson = new GsonBuilder().create();

		try {

			String request = "{\"DocItem\":{\"Id\":\""+docTypeId+"\",\"Name\":\"Document Types\"},"
					+ "\"Resource\":{\"Id\":null,\"Name\":\""+docTypeName+"\"}}";
			Response response = apiUtils.submitRequest(METHOD_TYPE.POST, request, headers, doctypeUrl);
			SingleDataResponse sdr = gson.fromJson(response.asString(), SingleDataResponse.class);
			createdDocId = sdr.Data.Id;
			TestValidation.IsTrue((sdr.Status), "Doc Type  is created", "Could NOT create Doc Type ");
			return createdDocId;
		} catch (Exception e) {
			logInfo("Unable to create Doc Type Id -"+e.getMessage());
			return createdDocId;

		}
	}

	/**
	 * This method is used to upload document in doc type
	 * @author choubey_a
	 * @param createdDocTypeId - The Id in which document has to be uploaded
	 * @param createdDocTypeName - The name of the document type created 
	 * @param - docTypeId - The Id of the type of the document
	 * @return upload document Id
	 */

	public String uploadDoc(String doctypeId, String createdDocTypeId, String createddocTypeName) {
		String uploadedDocId = null;
		apiUtils = new ApiUtils();

		try {
			String uploadfile = System.getProperty("user.dir") + "\\test-data-files\\UploadDocuments\\upload.png";
			byte[] fileContent = FileUtils.readFileToByteArray(new File(uploadfile));
			Map<String, String> headers = new HashMap<String, String>();
			headers.put("Content-Type", "multipart/form-data");
			headers.put("Accept", "application/json, charset=utf-8, /");
			headers.put("Authorization", "Bearer " + accessToken);

			Response response = RestAssured.given().headers(headers)
					.formParam("IsApproved","true")
					.formParam("DocumentRequest", "{\"DocItem\":{\"Id\":\""+doctypeId+"\",\"Name\":\"Document Types\",\"Type\":\"Others\"},\"Resource\":{\"Id\":\""+createdDocTypeId+"\",\"Name\":\""+createddocTypeName+"\"}}")					
					.multiPart("asset","upload.png",fileContent).when()
					.post(docuploadUrl);

			SingleDataResponse sdr = gson.fromJson(response.asString(), SingleDataResponse.class);
			uploadedDocId = sdr.Data.Id;
			TestValidation.IsTrue((sdr.Status), "Doc  is uploaded", "Could NOT upload document ");
			return uploadedDocId;

		}catch (Exception e) {
			logInfo("Unable to upload document -"+e.getMessage());
			return uploadedDocId;

		}
	}

	/**
	 * This method is used to upload document against a resource
	 * @author choubey_a
	 * @param resourceInstanceId - The Id in of the resource instance
	 * @param resourceInstanceName - The name of the instance against which the document has to be uploaded
	 * @param - docTypeId - The Id of the type of the document
	 * @param - resource - the resource selection from the DocType class
	 * @return uploadedDocId - Id of the uploaded Document
	 */

	public String uploadDoc(String doctypeId,String resourceInstanceId, String resourceInstanceName,String resource ) {
		apiUtils = new ApiUtils();
		try {
			String uploadfile = System.getProperty("user.dir") + "\\test-data-files\\UploadDocuments\\upload.png";
			byte[] fileContent = FileUtils.readFileToByteArray(new File(uploadfile));
			Map<String, String> headers = new HashMap<String, String>();
			headers.put("Content-Type", "multipart/form-data");
			headers.put("Accept", "application/json, charset=utf-8, /");
			headers.put("Authorization", "Bearer " + accessToken);

			Response response = RestAssured.given().headers(headers)
					.formParam("IsApproved","true")
					.formParam("DocumentRequest", "{\"DocItem\":{\"Id\":\""+doctypeId+"\",\"Name\":\""+resource+"\",\"Type\":\"Resource\"},\"Resource\":{\"Id\":\""+resourceInstanceId+"\",\"Name\":\""+resourceInstanceName+"\"}}")					
					.multiPart("asset","upload.png",fileContent).when()
					.post(docuploadUrl);

			SingleDataResponse sdr = gson.fromJson(response.asString(), SingleDataResponse.class);
			uploadedDocId = sdr.Data.Id;
			TestValidation.IsTrue((sdr.Status), "Doc  is uploaded", "Could NOT upload document ");
			return uploadedDocId;

		}catch (Exception e) {
			logInfo("Unable to upload document -"+e.getMessage());
			return uploadedDocId;
		}
	}

	/**
	 * This method is used to upload document for a document type
	 * @author choubey_a
	 * @param addFiles where HashMap's key is name of the Document Type and 
	 * value is List of files to be added 
	 * @return true if files are added in document type
	 */

	public boolean uploadDocInDocType(HashMap<String,List<String>> addFiles) {
		try {
			for (Map.Entry<String, List<String>> entry : addFiles.entrySet()) {
				String docTypeName = entry.getKey();
				List<String> uploadfile = entry.getValue();

				docTypeId = getDocumentTypeId("Document Types");

				createdDocId = createDocType(docTypeName,docTypeId);

				threadsleep(3000);

				for(String file : uploadfile) {
					uploadDoc(docTypeId,createdDocId,file);
					logInfo("Document " + file + " uploaded in document type " + docTypeName);
				}
				logInfo("Added files " + uploadfile + " to Document type - " + docTypeName);
			}
			return true;
		}
		catch(Exception e) {
			logError("Failed to add files " + addFiles + " to Document type - "
					+ e.getMessage());
			return false;
		}

	}

	/**
	 * This method is used to get created Doc Type Id
	 * @author choubey_a
	 * @param createddoctypeName - The name of created Document Type
	 * @param docTypeId - The Id of the type of the document
	 * @return created DocType Id 
	 */


	public String searchDocType(String createddocTypeName, String docTypeId) {

		String createdDocId= null;
		apiUtils = new ApiUtils();

		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("Accept", "application/json, text/plain, /");
		headers.put("Authorization", "Bearer " + accessToken);
		Gson gson = new GsonBuilder().create();

		try {

			String request = "{\"Type\":\""+docTypeId+"\","
					+ "\"ItemType\":\"Document\",\"SearchCriteria\":\""+createddocTypeName+"\",\"IsHideDisabled\":true}";		
			Response response = apiUtils.submitRequest(METHOD_TYPE.POST, request, headers, searchTree);
			MultipleDataResponse mdr = gson.fromJson(response.asString(), MultipleDataResponse.class);
			for(int i =0;i < mdr.Data.size() ; i++) {
				createdDocId = mdr.Data.get(i).Id;
			}
			logInfo("Created Doc Type Id "+createdDocId);
			return createdDocId;
		} catch (Exception e) {
			logInfo("Unable to get create Doc Type Id -"+e.getMessage());
			return null;
		}
	}



	/**
	 * This method is used to create verify document
	 * @author choubey_a
	 * @param docname - The name of created doc Type
	 * @param docTypeId - The Id of the type of the document
	 * @param docId - The Id of the created docType
	 * @param filename - The name of the file to be verified
	 * @param fileId - The Id of the filed that to be verified
	 * @return true if filename and filedId is verified
	 */

	public boolean verifyDocument(String docName,String docId, String docTypeId,String fileId) {

		apiUtils = new ApiUtils();

		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("Accept", "application/json, text/plain, /");
		headers.put("Authorization", "Bearer " + accessToken);
		Gson gson = new GsonBuilder().create();

		try {

			String request = "{\"take\":100,\"skip\":0,\"page\":1,\"pageSize\":100,"
					+ "\"Parameters\":{\"Item\":{\"Id\":\""+docTypeId+"\","
					+ "\"Name\":\"Document Types\",\"Type\":\"Others\"}"
					+ ",\"Data\":{\"Id\":\""+docId+"\","
					+ "\"Name\":\""+docName+"\"},\"SearchCriteria\":\"\","
					+ "\"FirstRowId\":null,\"LastRowId\":null},\"FirstRowId\":null,\"LastRowId\":null}";

			Response response = apiUtils.submitRequest(METHOD_TYPE.POST, request, headers, docTypeDetails);
			SingleDataResponse sdr = gson.fromJson(response.asString(), SingleDataResponse.class);
			for (Rows element : sdr.Data.Rows) {
				if(fileId.equals(element.Id)) {
					break;
				}
			}
			return true;
		} catch (Exception e) {
			logError("Unable to verify file-"+e.getMessage());
			return false;
		}
	}


	public class DocType{
		public static final String DOCUMENTTYPES = "Document Types";
		public static final String CUSTOMERS = "Customers";
		public static final String EQUIPMENT = "Equipment";
		public static final String ITEMS = "Items";
		public static final String SUPPLIERS = "Suppliers";
		public static final String DELETEDDOCUMENTS = "Deleted Documents";
	}

	public class MultipleDataResponse {		
		public List<Data> Data;
	}

	public class SingleDataResponse {
		public Data Data;
		public boolean Status;
	}

	public class Data {
		public String Id;
		public String Name;
		public List<Rows> Rows;

	}

	public class Rows{
		public String FileName;
		public String Id;
	}


}
