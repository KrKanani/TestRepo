package com.project.safetychain.apiproject.models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.project.safetychain.webapp.pageobjects.CommonPage.TIMEZONE;
import com.project.testbase.TestValidation;
import com.project.utilities.ApiUtils;
import com.project.utilities.ApiUtils.METHOD_TYPE;
import io.restassured.response.Response;
import com.project.utilities.DateTime;

public class Requirements extends Auth{

	public String addsupptemp = baseURI + RequirementsURLs.ADD_SUPPTEMP;
	public String gettempreqdetails = baseURI + RequirementsURLs.GET_TEMPLATE_REQDETAIL;
	public String addsuppreq = baseURI + RequirementsURLs.ADD_SUPPLIER_REQ;
	public String addtempsupp = baseURI + RequirementsURLs.ADD_TEMP_SUPP;
	public String gettempsupp = baseURI + RequirementsURLs.GET_TEMP_SUPP;
	public String getsuppdetails = baseURI + RequirementsURLs.GET_SUPPLIER_DETAILS;
	public String additemtemplate = baseURI + RequirementsURLs.ADD_ITEM_TEMPLATE;
	public String additemreq = baseURI + RequirementsURLs.ADD_ITEM_REQ;
	public String addtempitem = baseURI + RequirementsURLs.ADD_TEMP_ITEM;
	public String gettempitem = baseURI + RequirementsURLs.GET_TEMP_ITEM;

	ApiUtils apiUtils = new ApiUtils();
	Gson gson = new GsonBuilder().create();
	DateTime datetime = new DateTime();

	String documentPath = null;
	String acknowledgementTaskId = null;
	String docUploadTaskId =null;
	String formtaskId = null;
	String  modifiedBy = null;			
	String lastmodifiedBy = null;
	String scName = null;
	String status = null;

	String dateFormat = "yyyy-MM-dd";
	String currentDate = datetime.getTodayDate(dateFormat, TIMEZONE.REPUBLICOFINDIA);		
	String time = datetime.getTime();		
	String dateandtime = currentDate+"T"+time+"Z";


	/**
	 * This method is used to create supplier/item requirement template
	 * @author choubey_a
	 * @param reqtempname - Name of the item template
	 * @return tempId - Created item template Id
	 */

	public String createTemp(String reqtempname, boolean supplier) {

		apiUtils = new ApiUtils();

		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("Accept", "application/json, text/plain, /");
		headers.put("Authorization", "Bearer " + accessToken);
		Gson gson = new GsonBuilder().create();

		String tempId = null;

		Response response = null;

		try {

			if(supplier) {
				String request = "{\"Name\":\""+reqtempname+"\"}";
				response = apiUtils.submitRequest(METHOD_TYPE.POST, request, headers, addsupptemp);
			}
			else {
				if(supplier==false){
					String request = "{\"Name\":\""+reqtempname+"\"}";

					response = apiUtils.submitRequest(METHOD_TYPE.POST, request, headers, additemtemplate);
				}
			}
			SingleDataResponse1 sdr = gson.fromJson(response.asString(), SingleDataResponse1.class);
			tempId = sdr.Data.Id;
			TestValidation.IsTrue((sdr.Status), "Template  is created", "Could NOT create template ");
			return tempId;
		} catch (Exception e) {
			logInfo("Unable to create Template -"+e.getMessage());
			return tempId;

		}
	}

	/**
	 * This method is used to create path
	 * @author choubey_a
	 * @param environement
	 * @return documentPath
	 */

	public String docUploadPath(String environment) {
		try {
			switch (environment.toUpperCase()) {

			case "STAGE":
				documentPath = ""+baseURI+"/DocumentsStorage/stage/"+tenantName+"/";
				break;		
			case "LIVE":
				documentPath = ""+baseURI+"/DocumentsStorage/"+tenantName+"/";
				break;
			case "QA":
				documentPath = ""+baseURI+"/DocumentsStorage/qa/"+tenantName+"/";
				break;
			case "TRAIN":
				documentPath = ""+baseURI+"/DocumentsStorage/"+tenantName+"/";
				break;
			default: 
				logError("Correct environment not selected");
			}
		}catch(Exception e) {
			logError("Cound not set path -"+e.getMessage());
		}
		return documentPath;		
	}

	/**
	 * This method is used to add Acknowledgement Requirement
	 * @author choubey_a
	 * @param reqDetails
	 * @return acknowledgmentTask Id
	 */

	public String addSuppAckRequirement(RequirementDetails reqDetails) {

		apiUtils = new ApiUtils();

		String path = docUploadPath(reqDetails.environment);

		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("Accept", "application/json, text/plain, /");
		headers.put("Authorization", "Bearer " + accessToken);
		Gson gson = new GsonBuilder().create();

		Response response = null;

		try {
			String request = "{\r\n" + 
					"   \"IsEnable\":true,\r\n" + 
					"   \"Id\":\""+reqDetails.supptempId+"\",\r\n" + 
					"   \"Name\":\""+reqDetails.supptempname+"\",\r\n" + 
					"   \"Requirements\":{\r\n" + 
					"      \"TaskType\":\"Acknowledgement\",\r\n" + 
					"      \"RequirementType\":\"Acknowledgement\",\r\n" + 
					"      \"Message\":\"\",\r\n" + 
					"      \"TaskName\":\""+reqDetails.acknowledgmentTaskName+"\",\r\n" + 
					"      \"Note\":\"\",\r\n" + 
					"      \"CreatedBy\":{\r\n" + 
					"         \"Date\":\""+dateandtime+"\",\r\n" + 
					"         \"User\":{\r\n" + 
					"            \"Upn\":null,\r\n" + 
					"            \"FirstName\":\"SuperAdmin\",\r\n" + 
					"            \"LastName\":\"User\",\r\n" + 
					"            \"IANA\":null,\r\n" + 
					"            \"IsAdmin\":false,\r\n" + 
					"            \"Partner\":{\r\n" + 
					"               \"Id\":\"00000000-0000-0000-0000-000000000000\",\r\n" + 
					"               \"Name\":\"\",\r\n" + 
					"               \"Type\":\"Customer\",\r\n" + 
					"               \"Alias\":null\r\n" + 
					"            },\r\n" + 
					"            \"UserType\":\"TenantUser\",\r\n" + 
					"            \"HasAllLocations\":false,\r\n" + 
					"            \"HasAllSuppliers\":false,\r\n" + 
					"            \"Title\":\"\",\r\n" + 
					"            \"UserAgent\":null,\r\n" + 
					"            \"IsMobile\":false,\r\n" + 
					"            \"RequestingIp\":null,\r\n" + 
					"            \"EmployeeId\":null,\r\n" + 
					"            \"Phone\":null,\r\n" + 
					"            \"DefaultLocation\":\"00000000-0000-0000-0000-000000000000\",\r\n" + 
					"            \"SecurityLevel\":0,\r\n" + 
					"            \"Id\":\"acabe4ef-9e50-4a6f-81b8-7e6ff789eb6c\",\r\n" + 
					"            \"Name\":\"SuperAdmin User\"\r\n" + 
					"         },\r\n" + 
					"         \"Iana\":\"Asia/Kolkata\",\r\n" + 
					"         \"Latitude\":11.11,\r\n" + 
					"         \"Longitude\":12.23\r\n" + 
					"      },\r\n" + 
					"      \"ModifiedBy\":{\r\n" + 
					"         \"Date\":\""+dateandtime+"\",\r\n" + 
					"         \"User\":{\r\n" + 
					"            \"Upn\":null,\r\n" + 
					"            \"FirstName\":\"SuperAdmin\",\r\n" + 
					"            \"LastName\":\"User\",\r\n" + 
					"            \"IANA\":null,\r\n" + 
					"            \"IsAdmin\":false,\r\n" + 
					"            \"Partner\":null,\r\n" + 
					"            \"UserType\":\"TenantUser\",\r\n" + 
					"            \"HasAllLocations\":false,\r\n" + 
					"            \"HasAllSuppliers\":false,\r\n" + 
					"            \"Title\":null,\r\n" + 
					"            \"UserAgent\":null,\r\n" + 
					"            \"IsMobile\":false,\r\n" + 
					"            \"RequestingIp\":null,\r\n" + 
					"            \"EmployeeId\":null,\r\n" + 
					"            \"Phone\":null,\r\n" + 
					"            \"DefaultLocation\":\"00000000-0000-0000-0000-000000000000\",\r\n" + 
					"            \"SecurityLevel\":0,\r\n" + 
					"            \"Id\":\"acabe4ef-9e50-4a6f-81b8-7e6ff789eb6c\",\r\n" + 
					"            \"Name\":\"SuperAdmin User\"\r\n" + 
					"         },\r\n" + 
					"         \"Iana\":\"Asia/Kolkata\",\r\n" + 
					"         \"Latitude\":11.11,\r\n" + 
					"         \"Longitude\":12.23\r\n" + 
					"      },\r\n" + 
					"      \"Id\":\""+reqDetails.supptempId+"\",\r\n" + 
					"      \"Name\":\""+reqDetails.supptempname+"\",\r\n" + 
					"      \"Span\":\"1\",\r\n" + 
					"      \"Type\":\"days\",\r\n" + 
					"      \"ApprovedBy\":{\r\n" + 
					"         \"Id\":\""+reqDetails.wgId+"\",\r\n" + 
					"         \"Name\":\""+reqDetails.wgName+"\"\r\n" + 
					"      },\r\n" + 
					"      \"Document\":{\r\n" + 
					"         \"URL\":\""+path+""+reqDetails.docuemntuploadId+"/"+reqDetails.docname+"\",\r\n" + 
					"         \"DocType\":\""+reqDetails.docTypeName+"\",\r\n" + 
					"         \"Id\":\""+reqDetails.documentTypeId+"\",\r\n" + 
					"         \"Name\":\""+reqDetails.docname+"\"\r\n" + 
					"      }\r\n" + 
					"   },\r\n" + 
					"   \"Suppliers\":null\r\n" + 
					"}";

			response = apiUtils.submitRequest(METHOD_TYPE.POST, request, headers, addsuppreq);

			SingleDataResponse1 sdr = gson.fromJson(response.asString(), SingleDataResponse1.class);
			acknowledgementTaskId = sdr.Data.Id;
			TestValidation.IsTrue((sdr.Status), "Acknowledgement Task Created", "Could NOT create acknowledgement task");
			return acknowledgementTaskId;
		} catch (Exception e) {
			logInfo("Unable to create acknowledgement Task -"+e.getMessage());
			return acknowledgementTaskId;
		}
	}

	/**
	 * This method is used to add Acknowledgement Requirement
	 * @author choubey_a
	 * @param reqDetails
	 * @return acknowledgmentTask Id
	 */


	public String addItemAckRequirement(RequirementDetails reqDetails) {

		apiUtils = new ApiUtils();
		String path = docUploadPath(reqDetails.environment);
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("Accept", "application/json, text/plain, /");
		headers.put("Authorization", "Bearer " + accessToken);
		Gson gson = new GsonBuilder().create();

		Response response = null;

		try {
			String request =			
					"{\r\n" + 
							"   \"IsEnable\":true,\r\n" + 
							"   \"Id\":\""+reqDetails.itemtempId+"\",\r\n" + 
							"   \"Name\":\""+reqDetails.itemtempName+"\",\r\n" + 
							"   \"Requirements\":{\r\n" + 
							"      \"TaskType\":\"Acknowledgement\",\r\n" + 
							"      \"RequirementType\":\"Acknowledgement\",\r\n" + 
							"      \"Message\":\"\",\r\n" + 
							"      \"TaskName\":\""+reqDetails.itemacknowledgmentTaskName+"\",\r\n" + 
							"      \"Note\":\"\",\r\n" + 
							"      \"CreatedBy\":{\r\n" + 
							"         \"Date\":\""+dateandtime+"\",\r\n" + 
							"         \"User\":{\r\n" + 
							"            \"Upn\":null,\r\n" + 
							"            \"FirstName\":\"SuperAdmin\",\r\n" + 
							"            \"LastName\":\"User\",\r\n" + 
							"            \"IANA\":null,\r\n" + 
							"            \"IsAdmin\":false,\r\n" + 
							"            \"Partner\":{\r\n" + 
							"               \"Id\":\"00000000-0000-0000-0000-000000000000\",\r\n" + 
							"               \"Name\":\"\",\r\n" + 
							"               \"Type\":\"Customer\",\r\n" + 
							"               \"Alias\":null\r\n" + 
							"            },\r\n" + 
							"            \"UserType\":\"TenantUser\",\r\n" + 
							"            \"HasAllLocations\":false,\r\n" + 
							"            \"HasAllSuppliers\":false,\r\n" + 
							"            \"Title\":\"\",\r\n" + 
							"            \"UserAgent\":null,\r\n" + 
							"            \"IsMobile\":false,\r\n" + 
							"            \"RequestingIp\":null,\r\n" + 
							"            \"EmployeeId\":null,\r\n" + 
							"            \"Phone\":null,\r\n" + 
							"            \"DefaultLocation\":\"00000000-0000-0000-0000-000000000000\",\r\n" + 
							"            \"SecurityLevel\":0,\r\n" + 
							"            \"Id\":\"acabe4ef-9e50-4a6f-81b8-7e6ff789eb6c\",\r\n" + 
							"            \"Name\":\"SuperAdmin User\"\r\n" + 
							"         },\r\n" + 
							"         \"Iana\":\"Asia/Kolkata\",\r\n" + 
							"         \"Latitude\":11.11,\r\n" + 
							"         \"Longitude\":12.23\r\n" + 
							"      },\r\n" + 
							"      \"ModifiedBy\":{\r\n" + 
							"         \"Date\":\""+dateandtime+"\",\r\n" + 
							"         \"User\":{\r\n" + 
							"            \"Upn\":null,\r\n" + 
							"            \"FirstName\":\"SuperAdmin\",\r\n" + 
							"            \"LastName\":\"User\",\r\n" + 
							"            \"IANA\":null,\r\n" + 
							"            \"IsAdmin\":false,\r\n" + 
							"            \"Partner\":null,\r\n" + 
							"            \"UserType\":\"TenantUser\",\r\n" + 
							"            \"HasAllLocations\":false,\r\n" + 
							"            \"HasAllSuppliers\":false,\r\n" + 
							"            \"Title\":null,\r\n" + 
							"            \"UserAgent\":null,\r\n" + 
							"            \"IsMobile\":false,\r\n" + 
							"            \"RequestingIp\":null,\r\n" + 
							"            \"EmployeeId\":null,\r\n" + 
							"            \"Phone\":null,\r\n" + 
							"            \"DefaultLocation\":\"00000000-0000-0000-0000-000000000000\",\r\n" + 
							"            \"SecurityLevel\":0,\r\n" + 
							"            \"Id\":\"acabe4ef-9e50-4a6f-81b8-7e6ff789eb6c\",\r\n" + 
							"            \"Name\":\"SuperAdmin User\"\r\n" + 
							"         },\r\n" + 
							"         \"Iana\":\"Asia/Kolkata\",\r\n" + 
							"         \"Latitude\":11.11,\r\n" + 
							"         \"Longitude\":12.23\r\n" + 
							"      },\r\n" + 
							"      \"Id\":\""+reqDetails.itemtempId+"\",\r\n" + 
							"      \"Name\":\""+reqDetails.itemtempName+"\",\r\n" + 
							"      \"Span\":\"1\",\r\n" + 
							"      \"Type\":\"days\",\r\n" + 
							"      \"ApprovedBy\":{\r\n" + 
							"         \"Id\":\""+reqDetails.wgId+"\",\r\n" + 
							"         \"Name\":\""+reqDetails.wgName+"\"\r\n" + 
							"      },\r\n" + 
							"      \"Document\":{\r\n" + 
							"         \"URL\":\""+path+"/"+reqDetails.docuemntuploadId+"/"+reqDetails.docname+"\",\r\n" + 
							"         \"DocType\":\""+reqDetails.docTypeName+"\",\r\n" + 
							"         \"Id\":\""+reqDetails.documentTypeId+"\",\r\n" + 
							"         \"Name\":\""+reqDetails.docname+"\"\r\n" + 
							"      }\r\n" + 
							"   },\r\n" + 
							"   \"Items\":null\r\n" + 
							"}";

			response = apiUtils.submitRequest(METHOD_TYPE.POST, request, headers, additemreq);
			SingleDataResponse1 sdr = gson.fromJson(response.asString(), SingleDataResponse1.class);
			acknowledgementTaskId = sdr.Data.Id;
			TestValidation.IsTrue((sdr.Status), "Acknowledgement Task Created", "Could NOT create acknowledgement task");
			return acknowledgementTaskId;
		} catch (Exception e) {
			logInfo("Unable to create acknowledgement Task -"+e.getMessage());
			return acknowledgementTaskId;
		}
	}
	/**
	 * This method is used to add Doc Upload Requirement
	 * @author choubey_a
	 * @param reqDetails
	 * @return doc Upload Id
	 */

	public String addSuppDocUploadRequirement(RequirementDetails reqDetails) {

		apiUtils = new ApiUtils();

		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("Accept", "application/json, text/plain, /");
		headers.put("Authorization", "Bearer " + accessToken);
		Gson gson = new GsonBuilder().create();

		try {

			String request = "{\r\n" + 
					"    \"Id\": \""+reqDetails.supptempId+"\",\r\n" + 
					"    \"IsEnable\": true,\r\n" + 
					"    \"Name\": \""+reqDetails.supptempname+"\",\r\n" + 
					"    \"Requirements\": {\r\n" + 
					"        \"ApprovedBy\": {\r\n" + 
					"            \"Id\": \""+reqDetails.wgId+"\",\r\n" + 
					"            \"Name\": \""+reqDetails.wgName+"\"\r\n" + 
					"        },\r\n" + 
					"        \"Document\": {\r\n" + 
					"            \"DocType\": \""+reqDetails.docTypeName+"\",\r\n" + 
					"            \"DocumentVersion\": \"1.0\",\r\n" + 
					"            \"Id\": \""+reqDetails.documentTypeId+"\",\r\n" + 
					"            \"Name\": \""+reqDetails.docTypeName+"\",\r\n" + 
					"            \"URL\": \"\"\r\n" + 
					"        },\r\n" + 
					"        \"Id\": \""+reqDetails.supptempId+"\",\r\n" + 
					"        \"Message\": \"\",\r\n" + 
					"        \"Name\": \""+reqDetails.supptempname+"\",\r\n" + 
					"        \"Note\": \"\",\r\n" + 
					"        \"RequirementType\": \"Document\",\r\n" + 
					"        \"Span\": \"1\",\r\n" + 
					"        \"TaskName\": \""+reqDetails.docUploadTaskName+"\",\r\n" + 
					"        \"TaskType\": \"Document\",\r\n" + 
					"        \"Type\": \"days\"\r\n" + 
					"    },\r\n" + 
					"    \"Suppliers\": null\r\n" + 
					"}\r\n" + 
					"";

			Response response = apiUtils.submitRequest(METHOD_TYPE.POST, request, headers, addsuppreq);

			SingleDataResponse1 sdr = gson.fromJson(response.asString(), SingleDataResponse1.class);
			docUploadTaskId = sdr.Data.Id;
			TestValidation.IsTrue((sdr.Status), "Document Upload Task Created", "Could NOT create document Upload task");
			return docUploadTaskId;
		} catch (Exception e) {
			logInfo("Unable to create Document Upload Task -"+e.getMessage());
			return docUploadTaskId;
		}
	}

	/**
	 * This method is used to add Doc Upload Requirement
	 * @author choubey_a
	 * @param reqDetails
	 * @return doc Upload Id
	 */

	public String addItemDocUploadRequirement(RequirementDetails reqDetails) {

		apiUtils = new ApiUtils();

		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("Accept", "application/json, text/plain, /");
		headers.put("Authorization", "Bearer " + accessToken);
		Gson gson = new GsonBuilder().create();	

		try {
			String request =				
					"{\r\n" + 
							"   \"IsEnable\":true,\r\n" + 
							"   \"Id\":\""+reqDetails.itemtempId+"\",\r\n" + 
							"   \"Name\":\""+reqDetails.itemtempName+"\",\r\n" + 
							"   \"Requirements\":{\r\n" + 
							"      \"TaskType\":\"Document\",\r\n" + 
							"      \"RequirementType\":\"Document\",\r\n" + 
							"      \"Message\":\"\",\r\n" + 
							"      \"TaskName\":\""+reqDetails.itemdocUploadTaskName+"\",\r\n" + 
							"      \"Note\":\"\",\r\n" + 
							"      \"Id\":\""+reqDetails.itemtempId+"\",\r\n" + 
							"      \"Name\":\""+reqDetails.itemtempName+"\",\r\n" + 
							"      \"Span\":\"1\",\r\n" + 
							"      \"Type\":\"days\",\r\n" + 
							"      \"ApprovedBy\":{\r\n" + 
							"         \"Id\":\""+reqDetails.wgId+"\",\r\n" + 
							"         \"Name\":\""+reqDetails.wgName+"\"\r\n" + 
							"      },\r\n" + 
							"      \"Document\":{\r\n" + 
							"         \"URL\":\"\",\r\n" + 
							"         \"DocType\":\""+reqDetails.docTypeName+"\",\r\n" + 
							"         \"Id\":\""+reqDetails.documentTypeId+"\",\r\n" + 
							"         \"Name\":\""+reqDetails.docTypeName+"\",\r\n" + 
							"         \"DocumentVersion\":\"1.0\"\r\n" + 
							"      }\r\n" + 
							"   },\r\n" + 
							"   \"Items\":null\r\n" + 
							"}";

			Response response = apiUtils.submitRequest(METHOD_TYPE.POST, request, headers, additemreq);
			SingleDataResponse1 sdr = gson.fromJson(response.asString(), SingleDataResponse1.class);
			docUploadTaskId = sdr.Data.Id;
			TestValidation.IsTrue((sdr.Status), "Document Upload Task Created", "Could NOT create document Upload task");
			return docUploadTaskId;
		} catch (Exception e) {
			logInfo("Unable to create Document Upload Task -"+e.getMessage());
			return docUploadTaskId;
		}
	}

	/**
	 * This method is used to add form Requirement
	 * @author choubey_a
	 * @param reqDetails
	 * @return form task Id
	 */

	public String addSuppCompleteFormRequirement(RequirementDetails reqDetails) {
		apiUtils = new ApiUtils();

		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("Accept", "application/json, text/plain, /");
		headers.put("Authorization", "Bearer " + accessToken);
		Gson gson = new GsonBuilder().create();		

		try {
			String request = "{\"IsEnable\":true,\"Id\":\""+reqDetails.supptempId+"\","
					+ "\"Name\":\""+reqDetails.supptempname+"\",\"Requirements\":{\"TaskType\":\"Form\",\"RequirementType\":\"Form\","
					+ "\"Message\":\"\",\"TaskName\":\""+reqDetails.formTaskName+"\",\"Note\":\"\","
					+ "\"ModifiedBy\":{\"Date\":\""+dateandtime+"\",\"User\":{\"Upn\":null,"
					+ "\"FirstName\":\"SuperAdmin\",\"LastName\":\"User\",\"IANA\":null,\"IsAdmin\":false,\"Partner\":null,"
					+ "\"UserType\":\"TenantUser\",\"HasAllLocations\":false,\"HasAllSuppliers\":false,\"Title\":null,\"UserAgent\":null,"
					+ "\"IsMobile\":false,\"RequestingIp\":null,\"EmployeeId\":null,\"Phone\":null,"
					+ "\"DefaultLocation\":\"00000000-0000-0000-0000-000000000000\",\"SecurityLevel\":0,"
					+ "\"Id\":\"acabe4ef-9e50-4a6f-81b8-7e6ff789eb6c\",\"Name\":\"SuperAdmin User\"},\"Iana\":\"Asia/Kolkata\","
					+ "\"Latitude\":11.11,\"Longitude\":12.23},\"Id\":\""+reqDetails.supptempId+"\",\"Name\":\""+reqDetails.supptempname+"\","
					+ "\"Span\":\"1\",\"Type\":\"days\",\"ApprovedBy\":{\"Id\":\""+reqDetails.wgId+"\","
					+ "\"Name\":\""+reqDetails.wgName+"\"},\"Form\":{\"Type\":\"Check\",\"Id\":\""+reqDetails.formId+"\","
					+ "\"Name\":\""+reqDetails.formName+"\",\"Version\":{\"major\":1}}},\"Suppliers\":null}";

			Response response = apiUtils.submitRequest(METHOD_TYPE.POST, request, headers, addsuppreq);

			SingleDataResponse1 sdr = gson.fromJson(response.asString(), SingleDataResponse1.class);
			formtaskId = sdr.Data.Id;
			TestValidation.IsTrue((sdr.Status), "Complete Form Task Created", "Could NOT create Complete Form task");
			return formtaskId;

		}catch(Exception e) {
			logInfo("Not able to add complete form requirement -"+e.getMessage());
		}
		return formtaskId;
	}

	/**
	 * This method is used to add form Requirement
	 * @author choubey_a
	 * @param reqDetails
	 * @return form task Id
	 */

	public String addItemCompleteFormRequirement(RequirementDetails reqDetails) {
		apiUtils = new ApiUtils();

		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("Accept", "application/json, text/plain, /");
		headers.put("Authorization", "Bearer " + accessToken);
		Gson gson = new GsonBuilder().create();		

		try {
			String request = 
					"{\r\n" + 
							"   \"IsEnable\":true,\r\n" + 
							"   \"Id\":\""+reqDetails.itemtempId+"\",\r\n" + 
							"   \"Name\":\""+reqDetails.itemtempName+"\",\r\n" + 
							"   \"Requirements\":{\r\n" + 
							"      \"TaskType\":\"Form\",\r\n" + 
							"      \"RequirementType\":\"Form\",\r\n" + 
							"      \"Message\":\"\",\r\n" + 
							"      \"TaskName\":\""+reqDetails.itemformTaskName+"\",\r\n" + 
							"      \"Note\":\"\",\r\n" + 
							"      \"ModifiedBy\":{\r\n" + 
							"         \"Date\":\""+dateandtime+"\",\r\n" + 
							"         \"User\":{\r\n" + 
							"            \"Upn\":null,\r\n" + 
							"            \"FirstName\":\"SuperAdmin\",\r\n" + 
							"            \"LastName\":\"User\",\r\n" + 
							"            \"IANA\":null,\r\n" + 
							"            \"IsAdmin\":false,\r\n" + 
							"            \"Partner\":null,\r\n" + 
							"            \"UserType\":\"TenantUser\",\r\n" + 
							"            \"HasAllLocations\":false,\r\n" + 
							"            \"HasAllSuppliers\":false,\r\n" + 
							"            \"Title\":null,\r\n" + 
							"            \"UserAgent\":null,\r\n" + 
							"            \"IsMobile\":false,\r\n" + 
							"            \"RequestingIp\":null,\r\n" + 
							"            \"EmployeeId\":null,\r\n" + 
							"            \"Phone\":null,\r\n" + 
							"            \"DefaultLocation\":\"00000000-0000-0000-0000-000000000000\",\r\n" + 
							"            \"SecurityLevel\":0,\r\n" + 
							"            \"Id\":\"acabe4ef-9e50-4a6f-81b8-7e6ff789eb6c\",\r\n" + 
							"            \"Name\":\"SuperAdmin User\"\r\n" + 
							"         },\r\n" + 
							"         \"Iana\":\"Asia/Kolkata\",\r\n" + 
							"         \"Latitude\":11.11,\r\n" + 
							"         \"Longitude\":12.23\r\n" + 
							"      },\r\n" + 
							"      \"Id\":\""+reqDetails.itemtempId+"\",\r\n" + 
							"      \"Name\":\""+reqDetails.itemtempName+"\",\r\n" + 
							"      \"Span\":\"1\",\r\n" + 
							"      \"Type\":\"days\",\r\n" + 
							"      \"ApprovedBy\":{\r\n" + 
							"         \"Id\":\""+reqDetails.wgId+"\",\r\n" + 
							"         \"Name\":\""+reqDetails.wgName+"\"\r\n" + 
							"      },\r\n" + 
							"      \"Form\":{\r\n" + 
							"         \"Type\":\"Check\",\r\n" + 
							"         \"Id\":\""+reqDetails.formId+"\",\r\n" + 
							"         \"Name\":\""+reqDetails.formName+"\",\r\n" + 
							"         \"Version\":{\r\n" + 
							"            \"major\":2\r\n" + 
							"         }\r\n" + 
							"      }\r\n" + 
							"   },\r\n" + 
							"   \"Items\":null\r\n" + 
							"}";

			Response response = apiUtils.submitRequest(METHOD_TYPE.POST, request, headers, additemreq);

			SingleDataResponse1 sdr = gson.fromJson(response.asString(), SingleDataResponse1.class);
			formtaskId = sdr.Data.Id;
			TestValidation.IsTrue((sdr.Status), "Complete Form Task Created", "Could NOT create Complete Form task");
			return formtaskId;

		}catch(Exception e) {
			logInfo("Not able to add complete form requirement -"+e.getMessage());
		}
		return formtaskId;
	}

	/**
	 * This method is used to get item/Supplier Details
	 * @author choubey_a
	 * @param details
	 * @return null
	 */

	public void getResourceDetails(RequirementDetails details, boolean supplier) {

		apiUtils = new ApiUtils();
		Response response = null;

		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("Accept", "application/json, text/plain, /");
		headers.put("Authorization", "Bearer " + accessToken);
		Gson gson = new GsonBuilder().create();		

		try {

			if(supplier) {

				String request = "	{\"take\":100,\"skip\":0,\"page\":1,\"pageSize\":100,\"sort\":[{\"field\":\"Name\",\"dir\":\"asc\"}],\r\n" + 
						"\"filter\":{\"filters\":[{\"field\":\"SCSName\",\"operator\":\"contains\",\"value\":\""+details.suppResourceName+"\"}],\r\n" + 
						"\"logic\":\"and\"},\"Parameters\":{\"Id\":\""+details.suppResourceId+"\",\r\n" + 
						"\"Name\":\""+details.suppResourceName+"\",\"FirstRowId\":null,\"LastRowId\":null},\"FirstRowId\":null,\"LastRowId\":null}";

				response = apiUtils.submitRequest(METHOD_TYPE.POST, request, headers, getsuppdetails);

			}
			else {
				if(supplier == false) {
					String request = "	{\"take\":100,\"skip\":0,\"page\":1,\"pageSize\":100,\"sort\":[{\"field\":\"Name\",\"dir\":\"asc\"}],\r\n" + 
							"\"filter\":{\"filters\":[{\"field\":\"SCSName\",\"operator\":\"contains\",\"value\":\""+details.itemResourceName+"\"}],\r\n" + 
							"\"logic\":\"and\"},\"Parameters\":{\"Id\":\""+details.itemResourceId+"\",\r\n" + 
							"\"Name\":\""+details.itemResourceName+"\",\"FirstRowId\":null,\"LastRowId\":null},\"FirstRowId\":null,\"LastRowId\":null}";

					response = apiUtils.submitRequest(METHOD_TYPE.POST, request, headers, gettempitem);
				}
			}

			SingleDataResponse1 sdr = gson.fromJson(response.asString(), SingleDataResponse1.class);

			modifiedBy = sdr.Data.Rows.get(0).SCSModifiedBy.Name;
			lastmodifiedBy = sdr.Data.Rows.get(0).SCSLastModified.Name;
			scName = sdr.Data.Rows.get(0).SCSName.Name;
			status = sdr.Data.Rows.get(0).SCSStatus.Name;

			TestValidation.IsTrue((sdr.Status), "Resource Details is extracted", "Could NOT get resource details");
		} catch (Exception e) {
			logInfo("Unable to get Resource Details -"+e.getMessage());
		}
	}

	/**
	 * This method is used to add supplier to template
	 * @author choubey_a
	 * @param supptempname,supptempId,suppResourceName, SuppResourceId
	 * @return true if supplier is added
	 */

	public boolean addSupplierToTemplate(RequirementDetails details) {

		apiUtils = new ApiUtils();
		String dateFormat = "MM/dd/yyyy";
		String currentDate = datetime.getTodayDate(dateFormat, TIMEZONE.REPUBLICOFINDIA);

		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("Accept", "application/json, text/plain, /");
		headers.put("Authorization", "Bearer " + accessToken);
		Gson gson = new GsonBuilder().create();		
		try {

			String request = "{\"Id\":\""+details.supptempId+"\",\"Name\":\""+details.supptempname+"\","
					+ "\"Effective\":\""+currentDate+"\",\"Supplier\":[{\"Id\":\""+details.suppResourceId+"\","
					+ "\"SCSName\":\""+scName+"\",\"SCSModifiedBy\":\""+modifiedBy+"\",\"SCSStatus\":\""+status+"\","
					+ "\"SCSLastModified\":\""+lastmodifiedBy+"\",\"Name\":\""+details.suppResourceName+"\"}]}";

			Response response = apiUtils.submitRequest(METHOD_TYPE.POST, request, headers, addtempsupp);

			SingleDataResponse sdr = gson.fromJson(response.asString(), SingleDataResponse.class);

			if(!sdr.Status== true) {
				logError("Count not add supplier to template");
			}
			logInfo("Supplier Added to Template");
			return true;
		}catch(Exception e) {
			logError("Could not add supplire to template -"+e.getMessage());
			return false;
		}			
	}

	/**
	 * This method is used to add item to template
	 * @author choubey_a
	 * @param details
	 * @return true if item is added
	 */

	public boolean addItemToTemplate(RequirementDetails details) {

		apiUtils = new ApiUtils();
		String dateFormat = "MM/dd/yyyy";
		String currentDate = datetime.getTodayDate(dateFormat, TIMEZONE.REPUBLICOFINDIA);

		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("Accept", "application/json, text/plain, /");
		headers.put("Authorization", "Bearer " + accessToken);
		Gson gson = new GsonBuilder().create();		
		try {
			String request =					
					"{\"Id\":\""+details.itemtempId+"\",\"Name\":\""+details.itemtempName+"\","
							+ "\"Effective\":\""+currentDate+"\",\"Item\":[{\"Id\":\""+details.itemResourceId+"\","
							+ "\"SCSLastModified\":\""+lastmodifiedBy+"\",\"SCSStatus\":\""+status+"\",\"SCSName\":\""+scName+"\","
							+ "\"SCSModifiedBy\":\""+modifiedBy+"\",\"Name\":\""+details.itemResourceName+"\"}]}";

			Response response = apiUtils.submitRequest(METHOD_TYPE.POST, request, headers, addtempitem);

			SingleDataResponse sdr = gson.fromJson(response.asString(), SingleDataResponse.class);

			if(!sdr.Status== true) {
				logError("Count not add item to template");				
			}
			logInfo("Item Added to Template");
			return true;
		}catch(Exception e) {
			logError("Could not add item to template -"+e.getMessage());
			return false;
		}			
	}

	public class MultipleDataResponse {		
		public Data Id;
		public List<Data> Data;
	}

	public class SingleDataResponse1 {
		public Data Data;
		public boolean Status;
	}

	public class Data {
		public String Id;
		public String Name;
		public List<Rows> Rows;
	}

	public class Rows {
		public SCSName SCSName;
		public SCSModifiedBy SCSModifiedBy;
		public SCSStatus SCSStatus;
		public SCSLastModified SCSLastModified;
	}

	public class SCSName{
		public String Name;
	}

	public class SCSModifiedBy{
		public String Name;
	}

	public class SCSStatus{
		public String Name;
	}

	public class SCSLastModified{
		public String Name;
	}

	public static class RequirementDetails{
		public String wgName;
		public String wgId;
		public String docuemntuploadId;
		public String documentTypeId;
		public String docname;
		public String docTypeName;
		public String supptempId;
		public String supptempname;
		public String acknowledgmentTaskName;
		public String environment;
		public String docUploadTaskName;
		public String formId;
		public String formName;
		public String formTaskName;
		public String itemtempId;
		public String itemtempName;
		public String suppResourceId;
		public String suppResourceName;
		public String itemResourceId;
		public String itemResourceName;
		public String itemacknowledgmentTaskName;
		public String itemdocUploadTaskName;		
		public String itemformTaskName;
	}

}
