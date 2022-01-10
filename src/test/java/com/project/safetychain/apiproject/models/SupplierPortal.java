package com.project.safetychain.apiproject.models;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.project.safetychain.apiproject.models.FSQABrowserForms.FormData;
import com.project.safetychain.apiproject.models.Requirements.SingleDataResponse1;
import com.project.utilities.ApiUtils;
import com.project.utilities.ApiUtils.METHOD_TYPE;
import com.project.safetychain.apiproject.models.Auth;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class SupplierPortal extends Auth{

	public String getSupplierTask = baseURI + SupplierPortalURLs.GET_SUPPLIER_TASK;
	public String getTaskCount = baseURI + SupplierPortalURLs.GET_TASK_COUNT;
	public String ackTaskSign = baseURI + SupplierPortalURLs.ACK_TASK_SIGN;
	public String addDocument = baseURI+ SupplierPortalURLs.ADD_DOCUMENT;
	public String addQuestForm = baseURI+ SupplierPortalURLs.ADD_QUEST_FORM;
	public String ChangePassword = baseURI + SupplierPortalURLs.CHANGE_PASSWORD_URL;
	public String GetFormData = baseURI + SupplierPortalURLs.GETFORMDATA;
	public String GetForm = baseURI + SupplierPortalURLs.GETFORM;

	Requirements requirements = new Requirements();
	FSQABrowserForms forms = new FSQABrowserForms();
	ApiUtils apiUtils = new ApiUtils();
	Gson gson = new GsonBuilder().create();	
	
	String formTypeId=null;

	public void resetSupplierAccessToken(String username,String oldpassword, String newpassword) {

		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("Accept", "application/json, text/plain, /");
		headers.put("Authorization", "Bearer " + accessToken);

		try {
			String request = "{\"UserId\":\""+username+"\",\"OldPassword\":\""+oldpassword+"\","
					+ "\"NewPassword\":\""+newpassword+"\",\"ConfirmPassword\":\""+newpassword+"\"}";
			Response response = apiUtils.submitRequest(METHOD_TYPE.POST, request, headers, ChangePassword);		

			gson.fromJson(response.asString(), LoginResponse.class);
			logInfo("Supplier Login passowrd change done");
		} catch (Exception e) {
			logError("Failed to change password for supplier login" + e.getMessage());	
		}
	}
	
	/**
	 * This method is used to get TaskId and Master Id
	 * @author choubey_a
	 * @param resourceId -  Id of the resource
	 * @param resourceName - name of the resource
	 * @param taskname - name of the task
	 * @return Task and Master TaskId of the given task
	 */

	public String verifyTasks(String resourceId, String ResourceName,String taskName) {

		String masterTaskId = null;
		String TaskId = null;
		try {

			ApiUtils apiUtils = new ApiUtils();
			Gson gson = new GsonBuilder().create();

			Map<String, String> headers = new HashMap<String, String>();
			headers.put("Content-Type", "application/json");
			headers.put("Accept", "application/json, text/plain, /");
			headers.put("Authorization", "Bearer " + accessToken);

			String request = "{\"take\":100,\"skip\":0,\"page\":1,\"pageSize\":100,"
					+ "\"sort\":[{\"field\":\"DueBy\",\"dir\":\"desc\"}],\"Parameters\":{\"Id\":\""+resourceId+"\","
					+ "\"Name\":\""+ResourceName+"\",\"Search\":null,\"FirstRowId\":null,\"LastRowId\":null},\"FirstRowId\":null,"
					+ "\"LastRowId\":null}";

			Response response = apiUtils.submitRequest(METHOD_TYPE.POST, request, headers,
					getSupplierTask);

			SingleDataResponse sdr = gson.fromJson(response.asString(),
					SingleDataResponse.class);

			for (int i=0; i < sdr.Data.Rows.size(); i++){
				if(taskName.equals(sdr.Data.Rows.get(i).Name)) {
					masterTaskId = sdr.Data.Rows.get(i).MasterTaskId;
					TaskId = sdr.Data.Rows.get(i).Id;
					break;
				}				
			}

			logInfo("Verified Supplier Tasks");
			return masterTaskId+" "+TaskId;

		} catch (Exception e) {
			logError("Failed to verify Tasks - "+ e.getMessage());
			return null;
		}
	}
	
	/**
	 * This method is used to verify task count
	 * @author choubey_a
	 * @param count - count that need to be verified
	 * @return true if count is verified
	 */

	public boolean verifyTaskCount(String count) {

		try {
			ApiUtils apiUtils = new ApiUtils();
			Gson gson = new GsonBuilder().create();
			Map<String, String> headers = new HashMap<String, String>();
			headers.put("Content-Type", "application/json");
			headers.put("Accept", "application/json, text/plain, /");
			headers.put("Authorization", "Bearer " + accessToken);

			Response response = apiUtils.submitRequest(METHOD_TYPE.GET, "", headers,
					getTaskCount);

			SingleDataResponse sdr = gson.fromJson(response.asString(),
					SingleDataResponse.class);

			if(!(count.equals(sdr.Data))) {
				logInfo("Task count is incorrect");
				return false;
			}
			logInfo("Verified task count");
			return true;
		} catch (Exception e) {
			logError("Failed to verify Task Count- "+ e.getMessage());
			return false;
		}
	}
	
	/**
	 * This method is used to sign acknowledgement Task
	 * @author choubey_a
	 * @param req - details of the Requirement
	 * @return true if acknowledgment task is signed
	 */

	public boolean signAcknowledgementTask(RequirementTaskDetails req){

		try {
			ApiUtils apiUtils = new ApiUtils();
			Gson gson = new GsonBuilder().create();
			Map<String, String> headers = new HashMap<String, String>();
			headers.put("Content-Type", "application/json");
			headers.put("Accept", "application/json, text/plain, /");
			headers.put("Authorization", "Bearer " + accessToken);
			String URL = requirements.docUploadPath(environment);

			String request = "{\"TaskId\":\""+req.ATaskId+"\",\"MasterTaskId\":\""+req.AMasterId+"\","
					+ "\"DocumentDetail\":{\"URL\":\""+URL+"/"+req.UploadedDocumentId+"/upload.png\","
					+ "\"DocType\":\""+req.DocTypeName+"\",\"DocumentVersion\":null,\"Resource\":null,\"DocItem\":null,"
					+ "\"Id\":\""+req.UploadedDocumentId+"\",\"Name\":\"upload.png\"},\"Type\":\"Acknowledgement\","
					+ "\"TaskStatus\":\"Completed\"}";

			Response response = apiUtils.submitRequest(METHOD_TYPE.POST, request, headers,
					ackTaskSign);

			SimpleDataResponse sdr = gson.fromJson(response.asString(),
					SimpleDataResponse.class);

			if(!sdr.Status) {
				logError("Could not Sign Acknowledgement Task");
				return false;
			}
			logInfo("Acknowledgement Task Signed");
			return true;
		}catch(Exception e) {
			logError("Count not sign acknowledgement Task - "+e.getMessage());
			return false;
		}
	}
	
	/**
	 * This method is used to complete doc task
	 * @author choubey_a
	 * @param req - details of the Requirement
	 * @param supplier - if the req is for supplier or item
	 * @return true if doc task is completed
	 */

	public boolean completeDocRequirementTask(RequirementTaskDetails req, boolean supplier) {

		Gson gson = new GsonBuilder().create();
		Response response = null;
		try {	
			String uploadfile = System.getProperty("user.dir") + "\\test-data-files\\UploadDocuments\\upload.png";
			byte[] fileContent = FileUtils.readFileToByteArray(new File(uploadfile));
			Map<String, String> headers = new HashMap<String, String>();
			headers.put("Content-Type", "multipart/form-data");
			headers.put("Accept", "application/json, charset=utf-8, /");
			headers.put("Authorization", "Bearer " + accessToken);

			if(supplier) {

				response = RestAssured.given().headers(headers)
						.formParam("IsApproved","false")
						.formParam("DocumentRequest", "{\"Id\":\""+req.docTypeId+"\",\"Name\":\""+req.DocTypeName+"}\"}")
						.formParam("MasterTaskId", req.DMasterId)
						.formParam("RequirementId", req.docUploadReqId)
						.formParam("TaskId", req.DTaskId)
						.formParam("DocumentName", req.supplierInstanceName+"::"+req.DocTypeName)
						.formParam("RequestingUserPartner", "{\"Id\":\""+req.supplierInstanceId+"\",\"Name\":\""+req.supplierInstanceName+"\"}")
						.formParam("AppoveOrRejectDocument", req.rejectId)
						.formParam("Comment", "API Document Upload")
						.formParam("Url","")
						.multiPart("asset","upload.png",fileContent).when()
						.post(addDocument);
			}

			else {

				response = RestAssured.given().headers(headers)
						.formParam("IsApproved","false")
						.formParam("DocumentRequest", "{\"Id\":\""+req.docTypeId+"\",\"Name\":\""+req.DocTypeName+"}\"}")
						.formParam("MasterTaskId", req.DMasterId)
						.formParam("RequirementId", req.docUploadReqId)
						.formParam("TaskId", req.DTaskId)
						.formParam("DocumentName", req.supplierInstanceName+"::"+req.DocTypeName+"::"+req.itemResourceName)
						.formParam("RequestingUserPartner", "{\"Id\":\""+req.itemresourceId+"\",\"Name\":\""+req.itemResourceName+"\"}")
						.formParam("AppoveOrRejectDocument", req.rejectId)
						.formParam("Comment", "API Document Upload")
						.formParam("Url","")
						.multiPart("asset","upload.png",fileContent).when()
						.post(addDocument);

			}

			System.out.println(response.asString());

			SimpleDataResponse sdr = gson.fromJson(response.asString(),
					SimpleDataResponse.class);

			if(!sdr.Status) {
				logError("Could not complete Document Upload Task");
				return false;
			}
			logInfo("Doc Upload Task completed");
			return true;
		}catch(Exception e) {
			logError("Doc Upload Task not completed - "+e.getMessage());
			return false;
		}
	}
	
	/**
	 * This method is used to get formId
	 * @author choubey_a
	 * @param formId -  Id of the form
	 * @return FormId
	 */
	
	public String getForm(String formId) {
		
		String formrefId=null;

		apiUtils = new ApiUtils();

		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("Accept", "application/json, text/plain, /");
		headers.put("Authorization", "Bearer " + accessToken);
		Gson gson = new GsonBuilder().create();

		try {

			String request = "{\"Id\":\""+formId+"\"}";

			Response response = apiUtils.submitRequest(METHOD_TYPE.POST, request, headers, GetForm);
			com.project.safetychain.apiproject.models.FSQABrowserForms.SingleDataResponse sdr = gson.fromJson(response.asString(), com.project.safetychain.apiproject.models.FSQABrowserForms.SingleDataResponse.class);

			formrefId = sdr.Data.FormId;

			return formrefId;
		} catch (Exception e) {
			logInfo("Unable to get Form Id -"+e.getMessage());
			return null;
		}
	}
	
	/**
	 * This method is used to get FormType Id
	 * @author choubey_a
	 * @param formId,resourceId, resourceHierarchy
	 * @return formTypeId
	 */
	
	public String getFormTypeId(String formId, String resourceId, String resourceHierarchy) {

		apiUtils = new ApiUtils();

		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("Accept", "application/json, text/plain, /");
		headers.put("Authorization", "Bearer " + accessToken);
		Gson gson = new GsonBuilder().create();

		try {

			String request = "{\"FormId\":\""+formId+"\","
					+ "\"Resource\":{\"Id\":\""+resourceId+"\",\"Name\":\""+resourceHierarchy+"\"}}";

			Response response = apiUtils.submitRequest(METHOD_TYPE.POST, request, headers, GetFormData);
			com.project.safetychain.apiproject.models.FSQABrowserForms.SingleDataResponse sdr = gson.fromJson(response.asString(), com.project.safetychain.apiproject.models.FSQABrowserForms.SingleDataResponse.class);			
			formTypeId = sdr.Data.ID;			
			return formTypeId;
		} catch (Exception e) {
			logInfo("Unable to get Form Type Id -"+e.getMessage());
			return null;
		}
	}
	
	/**
	 * This method is used to get form data
	 * @author choubey_a
	 * @param formId,resourceId, resourceName
	 * @return list of formdata
	 */
	
	public List<FormData> getFormData(String formId, String resourceId, String resourceName) {

		apiUtils = new ApiUtils();
		List<FormData> data = new ArrayList<>();

		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("Accept", "application/json, text/plain, /");
		headers.put("Authorization", "Bearer " + accessToken);
		Gson gson = new GsonBuilder().create();

		try {

			String request = "{\"FormId\":\""+formId+"\","
					+ "\"Resource\":{\"Id\":\""+resourceId+"\",\"Name\":\""+resourceName+"\"}}";

			Response response = apiUtils.submitRequest(METHOD_TYPE.POST, request, headers, GetFormData);
			com.project.safetychain.apiproject.models.FSQABrowserForms.SingleDataResponse sdr = gson.fromJson(response.asString(), com.project.safetychain.apiproject.models.FSQABrowserForms.SingleDataResponse.class);			
			formTypeId = sdr.Data.ID;
			for(int i=0;i< sdr.Data.FormDetails.size();i++) {
				com.project.safetychain.apiproject.models.FSQABrowserForms.FormData fd1 = new FormData();
				fd1.fieldId = sdr.Data.FormDetails.get(i).Id;
				fd1.fieldname = sdr.Data.FormDetails.get(i).Name;				
				fd1.shortname = sdr.Data.FormDetails.get(i).ShortName;
				fd1.Type = sdr.Data.FormDetails.get(i).Type;
				try {
					fd1.Options = new ArrayList<com.project.safetychain.apiproject.models.FSQABrowserForms.Options>();					
					for(int j=0;j< sdr.Data.FormDetails.get(i).Options.size();j++) {
						com.project.safetychain.apiproject.models.FSQABrowserForms.Options fieldOptions = new com.project.safetychain.apiproject.models.FSQABrowserForms.Options();
						fieldOptions.Id = sdr.Data.FormDetails.get(i).Options.get(j).Id;
						fieldOptions.Name = sdr.Data.FormDetails.get(i).Options.get(j).Name;
						fd1.Options.add(fieldOptions);
					}
				}catch(Exception e) {
					logInfo("Options are not available -"+e.getMessage());
				}
				data.add(fd1);				
			}
			return data;
		} catch (Exception e) {
			logInfo("Unable to get Form Data -"+e.getMessage());
			return null;
		}
	}
	
	/**
	 * This method is used to get field details according to task rejection
	 * @author choubey_a
	 * @param formdata, data and boolean reject
	 * @return fieldDetails
	 */
	
	public String FieldDetails(List<FormData>FormData, HashMap<String,String> data, boolean reject) {
		String fields =null;
		try{
			if(reject) {
				fields = forms.getFieldDetails(FormData, data,true);
			}
			else {
				fields = forms.getFieldDetails(FormData, data,false);
			}
			return fields;
		}catch(Exception e) {
			logError("Could not get form fields"+e.getMessage());
			return null;
		}
	}
	
	/**
	 * This method is used to complete form task
	 * @author choubey_a
	 * @param formdata, data, details, boolean supplier and reject
	 * @return form Doc Id
	 */

	public String addQuestForm(List<FormData>FormData, HashMap<String,String> data,RequirementTaskDetails details, boolean supplier,boolean reject) {

		String request =null;
		String formDocId = null;

		ApiUtils apiUtils = new ApiUtils();
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("Accept", "application/json, text/plain, /");
		headers.put("Authorization", "Bearer " + accessToken);
		Gson gson = new GsonBuilder().create();
		try {
			if(supplier) {
				request = "{\r\n" + 
						"    \"Data\": {\r\n" + 
						"        \"Attachments\": [\r\n" + 
						"        ],\r\n" + 
						"        \"Comments\": \"\",\r\n" + 
						"        \"DocumentName\": \""+details.supplierInstanceName+" :: "+details.questformname+"\",\r\n" + 
						"        \"FormDetails\": ["+FieldDetails(FormData, data, reject)+"],\r\n" + 
						"        \"FormRef\": {\r\n" + 
						"            \"FormRefId\": \""+details.formrefId+"\",\r\n" + 
						"            \"Id\": \""+details.formId+"\",\r\n" + 
						"            \"Name\": \""+details.questformname+"\",\r\n" + 
						"            \"Type\": \"Questionnaire\"\r\n" + 
						"        },\r\n" + 
						"        \"Header\": [\r\n" + 
						"        ],\r\n" + 
						"        \"ID\": \""+details.formTypeId+"\",\r\n" + 
						"        \"JobDetails\": [\r\n" + 
						"        ],\r\n" + 
						"        \"Program\": {\r\n" + 
						"        },\r\n" + 
						"        \"RequestingUserPartner\": {\r\n" + 
						"            \"Id\": \""+details.supplierInstanceId+"\",\r\n" + 
						"            \"Name\": \""+details.supplierInstanceName+"\"\r\n" + 
						"        },\r\n" + 
						"        \"Resource\": {\r\n" + 
						"            \"Id\": \""+details.supplierInstanceId+"\",\r\n" + 
						"            \"Name\": \"Suppliers > "+details.supplierCatName+" > "+details.supplierInstanceName+"\"\r\n" + 
						"        },\r\n" + 
						"        \"TaskDetails\": [\r\n" + 
						"        ],\r\n" + 
						"        \"TaskInfo\": {\r\n" + 
						"            \"AppoveOrRejectDocument\": \""+details.rejectId+"\",\r\n" + 
						"            \"Id\": \""+details.FTaskId+"\"\r\n" + 
						"        }\r\n" + 
						"    },\r\n" + 
						"    \"DocumentTaskRef\": {\r\n" + 
						"        \"MasterTaskId\": \""+details.FMasterId+"\",\r\n" + 
						"        \"OptionalComment\": \"\",\r\n" + 
						"        \"RequirementId\": \""+details.formReqId+"\",\r\n" + 
						"        \"TaskId\": \""+details.FTaskId+"\"\r\n" + 
						"    }\r\n" + 
						"}";
			}
			else {

				request = "{\r\n" + 
						"    \"Data\": {\r\n" + 
						"        \"Attachments\": [\r\n" + 
						"        ],\r\n" + 
						"        \"Comments\": \"\",\r\n" + 
						"        \"DocumentName\": \""+details.supplierInstanceName+" :: "+details.questformname+" :: "+details.itemResourceName+"\",\r\n" + 
						"        \"FormDetails\": ["+FieldDetails(FormData, data, reject)+"],\r\n" + 
						"        \"FormRef\": {\r\n" + 
						"            \"FormRefId\": \""+details.formrefId+"\",\r\n" + 
						"            \"Id\": \""+details.formId+"\",\r\n" + 
						"            \"Name\": \""+details.questformname+"\",\r\n" + 
						"            \"Type\": \"Questionnaire\"\r\n" + 
						"        },\r\n" + 
						"        \"Header\": [\r\n" + 
						"        ],\r\n" + 
						"        \"ID\": \""+details.formTypeId+"\",\r\n" + 
						"        \"JobDetails\": [\r\n" + 
						"        ],\r\n" + 
						"        \"Program\": {\r\n" + 
						"        },\r\n" + 
						"        \"RequestingUserPartner\": {\r\n" + 
						"            \"Id\": \""+details.supplierInstanceId+"\",\r\n" + 
						"            \"Name\": \""+details.supplierInstanceName+"\"\r\n" + 
						"        },\r\n" + 
						"        \"Resource\": {\r\n" + 
						"            \"Id\": \""+details.itemresourceId+"\",\r\n" + 
						"            \"Name\": \"Items > "+details.itemCatName+" > "+details.itemResourceName+"\"\r\n" + 
						"        },\r\n" + 
						"        \"TaskDetails\": [\r\n" + 
						"        ],\r\n" + 
						"        \"TaskInfo\": {\r\n" + 
						"            \"AppoveOrRejectDocument\": \""+details.rejectId+"\",\r\n" + 
						"            \"Id\": \""+details.FTaskId+"\"\r\n" + 
						"        }\r\n" + 
						"    },\r\n" + 
						"    \"DocumentTaskRef\": {\r\n" + 
						"        \"MasterTaskId\": \""+details.FMasterId+"\",\r\n" + 
						"        \"OptionalComment\": \"\",\r\n" + 
						"        \"RequirementId\": \""+details.formReqId+"\",\r\n" + 
						"        \"TaskId\": \""+details.FTaskId+"\"\r\n" + 
						"    }\r\n" + 
						"}";
			}

			Response response = apiUtils.submitRequest(METHOD_TYPE.POST, request, headers, addQuestForm);
			SingleDataResponse1 sdr = gson.fromJson(response.asString(), SingleDataResponse1.class);			
			formDocId = sdr.Data.Id;
			logInfo("Form Task Completed");
			return formDocId;
		}catch(Exception e) {
			logError("Form is not completed -"+e.getMessage());
			return null;
		}
	}

	public class SingleDataResponse{
		public Data Data;
	}

	public class Data{
		public List<Rows> Rows;
	}

	public class Rows {
		public String Id;
		public String Name;
		public String MasterTaskId;
	}

	public class SimpleDataResponse{
		public boolean Status;
	}

	public static class RequirementTaskDetails {

		public String AMasterId;
		public String FMasterId;
		public String DMasterId;
		public String ATaskId;
		public String FTaskId;
		public String DTaskId;
		public String UploadedDocumentId;
		public String DocTypeName;
		public String docTypeId;
		public String docUploadReqId;
		public String formReqId;
		public String supplierInstanceName;
		public String supplierCatName;
		public String itemCatName;
		public String supplierInstanceId;
		public String questformname;
		public String formrefId;
		public String formId;
		public String formTypeId;
		public String itemresourceId;
		public String itemResourceName;
		public String rejectId = "00000000-0000-0000-0000-000000000000";

	}
}
