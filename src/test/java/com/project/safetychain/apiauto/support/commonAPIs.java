package com.project.safetychain.apiauto.support;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.project.safetychain.api.models.FormDesigner;
import com.project.safetychain.api.models.FormDesignerUrls;
import com.project.safetychain.api.models.ManageLocation;
import com.project.safetychain.api.models.ManageResources;
import com.project.safetychain.api.models.ResourceDesigner;
import com.project.safetychain.api.models.ResourceDesignerUrls;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.project.safetychain.api.models.Auth;
import com.project.safetychain.api.models.ResourceDesigner.Data;
import com.project.safetychain.api.models.support.Support;
import com.project.safetychain.api.models.support.Support.RESOURCE_FIELDS;
import com.project.safetychain.api.models.support.Support.RESOURCE_TYPES;
import com.project.testbase.TestValidation;
import com.project.utilities.ApiUtils;
import com.project.utilities.ApiUtils.METHOD_TYPE;

import io.restassured.response.Response;

public class commonAPIs extends Auth {
	ResourceDesigner resourceDesigner = null;
	ManageLocation manageLocation = null;
	ManageResources manageResources = null;
	FormDesigner formDesigner = null;
	public static String nameId = null;
	public static String modifiedById = null;
	public static String lastModifiedId = null;
	public static String statusId = null;
	public static String createdLocCatId = null;
	public static String createdCustCatId = null;
	public static String createdLocInstId = null;
	public static String createdInstId = null;
	public static String createdFormId = null;
	public static String text = "abc", number = "3";
	public String ResourceTypeUrl = baseURI + ResourceDesignerUrls.RESOURCE_TYPE_URL;
	public String GetLocationCategories = baseURI + FormDesignerUrls.GET_ROOT_LOCATIONS_CAT_URL;
	public String manageResourceCategories = baseURI + FormDesignerUrls.MANAGE_RESOURCE_CATEGORIES_URL;

	ApiUtils au = new ApiUtils();
	Gson gson = new GsonBuilder().create();

	public commonAPIs() {

		manageLocation = new ManageLocation();
		manageResources = new ManageResources();
		formDesigner = new FormDesigner();
		resourceDesigner = new ResourceDesigner();
	}

	/**
	 * This method is get and set the IDs of Categories .
	 * 
	 * @author Shingare_s
	 */
	public boolean setResourceCatIDs() {
		try {

			Map<String, String> headers = new HashMap<String, String>();
			headers.put("Content-Type", "application/json");
			headers.put("Accept", "application/json, text/plain, /");
			headers.put("Authorization", "Bearer " + accessToken);
			Response response = au.submitRequest(METHOD_TYPE.GET, null, headers, ResourceTypeUrl);
			MultipleDataResponse mdr = gson.fromJson(response.asString(), MultipleDataResponse.class);
			for (Data data : mdr.Data) {
				switch (data.Name) {
				case RESOURCE_TYPES.CUSTOMERS:
					customersCatID = data.Id;
					logInfo("Resource Type : " + RESOURCE_TYPES.CUSTOMERS + " ID : " + data.Id);
					break;
				case RESOURCE_TYPES.EQUIPMENT:
					equipmentCatID = data.Id;
					logInfo("Resource Type : " + RESOURCE_TYPES.EQUIPMENT + " ID : " + data.Id);
					break;
				case RESOURCE_TYPES.ITEMS:
					itemsCatID = data.Id;
					logInfo("Resource Type : " + RESOURCE_TYPES.ITEMS + " ID : " + data.Id);
					break;
				case RESOURCE_TYPES.LOCATION:
					locationCatID = data.Id;
					logInfo("Resource Type : " + RESOURCE_TYPES.LOCATION + " ID : " + data.Id);
					break;
				case RESOURCE_TYPES.SUPPLIERS:
					suppliersCatID = data.Id;
					logInfo("Resource Type : " + RESOURCE_TYPES.SUPPLIERS + " ID : " + data.Id);
					break;
				default:
					logError("Failed to set for Resource Type : " + data.Name + " ID : " + data.Id);
					return false;
				}
			}
			return true;
		} catch (Exception e) {
			logError("Failed to set resource IDs for Categories - " + e.getMessage());
			return false;
		}
	}

	public void GetResourceCreationInputFieldIds() {

		nameId = resourceDesigner.getResourceFieldId(RESOURCE_FIELDS.NAME, catID, createdCustCatId);
		modifiedById = resourceDesigner.getResourceFieldId(RESOURCE_FIELDS.MODIFIED_BY, catID, createdCustCatId);
		lastModifiedId = resourceDesigner.getResourceFieldId(RESOURCE_FIELDS.LAST_MODIFIED, catID, createdCustCatId);
		statusId = resourceDesigner.getResourceFieldId(RESOURCE_FIELDS.STATUS, catID, createdCustCatId);
		System.out.print("SCS Id= " + nameId + "MOdified By Id = " + modifiedById + "last MOdified Id = "
				+ lastModifiedId + "Status Id = " + statusId);

	}

	public String createResourceCategory(String resourceCategory, String resourceType) {

		au = new ApiUtils();
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("Accept", "application/json, text/plain, /");
		headers.put("Authorization", "Bearer " + accessToken);
		// String catId = null;
		switch (resourceType) {
		case RESOURCE_TYPES.CUSTOMERS:
			catID = customersCatID;
			break;
		case RESOURCE_TYPES.ITEMS:
			catID = itemsCatID;
			break;
		case RESOURCE_TYPES.EQUIPMENT:
			catID = equipmentCatID;
			break;
		case RESOURCE_TYPES.SUPPLIERS:
			catID = suppliersCatID;
			break;

		}

		GetResourceCreationInputFieldIds();

		String addCatJson = "{\r\n" + "    \"BaseTypeID\": \"" + catID + "\",\r\n" + "    \"Name\": \""
				+ resourceCategory + "\",\r\n" + "    \"ID\": null,\r\n" + "    \"ResourceTypeID\": \"" + catID
				+ "\"\r\n" + "}";

		Response response = au.submitRequest(METHOD_TYPE.POST, addCatJson, headers, resourceDesigner.AddResourceCatUrl);
		Gson gson = new GsonBuilder().create();
		SingleDataResponse sdr = gson.fromJson(response.asString(), SingleDataResponse.class);
		createdCustCatId = sdr.Data.Id;
		TestValidation.IsTrue((sdr.Status), "Customers Category " + resourceCategory + " is created",
				"Could NOT create customers category " + resourceCategory);
		return createdCustCatId;

	}

	public String createResourceInstance(String resourceInstance, String resourceType) {

		List<String> listOfEnabledFields = new ArrayList<String>();
		switch (resourceType) {
		case RESOURCE_TYPES.CUSTOMERS:
			listOfEnabledFields = resourceDesigner.createResFieldsJson(RESOURCE_TYPES.CUSTOMERS);
			break;
		case RESOURCE_TYPES.ITEMS:
			listOfEnabledFields = resourceDesigner.createResFieldsJson(RESOURCE_TYPES.ITEMS);
			break;
		case RESOURCE_TYPES.EQUIPMENT:
			listOfEnabledFields = resourceDesigner.createResFieldsJson(RESOURCE_TYPES.EQUIPMENT);
			break;
		case RESOURCE_TYPES.SUPPLIERS:
			listOfEnabledFields = resourceDesigner.createResFieldsJson(RESOURCE_TYPES.SUPPLIERS);
			break;

		}

		String fieldDets = resourceDesigner.updateResFieldsJson(listOfEnabledFields, resourceInstance, number, text);
		// String fieldDets = resourceDesigner.updateResFieldsJson(listOfEnabledFields,
		// custInstName, number, text);

		au = new ApiUtils();
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("Accept", "application/json, text/plain, /");
		headers.put("Authorization", "Bearer " + accessToken);

		String addInstJson = "{\r\n" + "    \"BaseTypeID\": \"" + createdCustCatId + "\",\r\n" + "    \"Name\": \""
				+ resourceInstance + "\",\r\n" + "    \"ResourceTypeID\": \"" + catID + "\",\r\n"
				+ "    \"ResourceValue\": [\r\n" + "        {\r\n" + "            \"FieldName\": \"SCSModifiedBy\",\r\n"
				+ "            \"FieldId\": \"" + modifiedById + "\",\r\n" + "            \"Value\": null,\r\n"
				+ "            \"Type\": \"Label\"\r\n" + "        },\r\n" + "        {\r\n"
				+ "            \"FieldName\": \"SCSLastModified\",\r\n" + "            \"FieldId\": \"" + lastModifiedId
				+ "\",\r\n" + "            \"Value\": null,\r\n" + "            \"Type\": \"Label\"\r\n"
				+ "        },\r\n" + "		 {\r\n" + "			 \"FieldName\":\"SCSStatus\",\r\n"
				+ "			 \"FieldId\":\"" + statusId + "\",\r\n" + "			 \"Value\":null,\r\n"
				+ "			 \"Type\":\"Toggle\"\r\n" + "        },\r\n" + fieldDets + "    ]\r\n" + "}";
		Response response = au.submitRequest(METHOD_TYPE.POST, addInstJson, headers, manageResources.AddNewResourceUrl);
		Gson gson = new GsonBuilder().create();
		SingleDataResponse sdr = gson.fromJson(response.asString(), SingleDataResponse.class);
		createdInstId = sdr.Data.Id;
		TestValidation.IsTrue((sdr.Status), "Resource Instance " + resourceInstance + " is created",
				"Could NOT create Resource Instance " + resourceInstance);
		 return createdInstId;

	}

	// @Test(description = "Create Location category")
	public String createResourceCategory_Location(String locCatName) {

		au = new ApiUtils();
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("Accept", "application/json, text/plain, /");
		headers.put("Authorization", "Bearer " + accessToken);

		String addCatJson = "{\r\n" + "    \"BaseTypeID\": \"" + locationCatID + "\",\r\n" + "    \"Name\": \""
				+ locCatName + "\",\r\n" + "    \"ID\": null,\r\n" + "    \"ResourceTypeID\": \"" + locationCatID
				+ "\"\r\n" + "}";
		Response response = au.submitRequest(METHOD_TYPE.POST, addCatJson, headers, resourceDesigner.AddResourceCatUrl);
		Gson gson = new GsonBuilder().create();
		SingleDataResponse sdr = gson.fromJson(response.asString(), SingleDataResponse.class);
		createdLocCatId = sdr.Data.Id;
		logInfo("Location Cat Id =   " + createdLocCatId);
		TestValidation.IsTrue((sdr.Status), "Location Category " + locCatName + " is created",
				"Could NOT create location category " + locCatName);
		return createdLocCatId;

	}

	// @Test(description = "Create Location instance")
	public String createResourceInstance_Location(String locInstName) {

		List<String> listOfEnabledFields = new ArrayList<String>();
		listOfEnabledFields = resourceDesigner.createResFieldsJson(RESOURCE_TYPES.LOCATION);
		String fieldDets = resourceDesigner.updateResFieldsJson(listOfEnabledFields, locInstName, number, text);

		au = new ApiUtils();
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("Accept", "application/json, text/plain, /");
		headers.put("Authorization", "Bearer " + accessToken);

		String addInstJson = "{\r\n" + "    \"ParentBusinessObjectId\": \"" + createdLocCatId + "\",\r\n"
				+ "    \"Name\": \"" + locInstName + "\",\r\n" + "    \"ResourceTypeId\": \"" + locationCatID
				+ "\",\r\n" + "    \"FieldValues\": [\r\n" + "{\r\n"
				+ "         \"FieldId\":\"422052e7-1b84-4d1c-81f1-950149d90512\",\r\n" + "         \"Value\":null,\r\n"
				+ "         \"Type\":\"Label\",\r\n" + "         \"FieldName\":\"SCSLastModified\"\r\n" + "      },"
				+ "{\r\n" + "         \"FieldId\":\"d7ebfab1-eb2f-48d6-8514-8da3ec2d8a28\",\r\n"
				+ "         \"Value\":null,\r\n" + "         \"Type\":\"Label\",\r\n"
				+ "         \"FieldName\":\"SCSModifiedBy\"\r\n" + "      }," + fieldDets + "    ],\r\n"
				+ "    \"Id\": null,\r\n" + "    \"IsEnabled\": true,\r\n"
				+ "    \"BusinessObjectRefsWithResourceType\": [],\r\n"
				+ "    \"RemovedBusinessObjectRefsWithResourceType\": [],\r\n" + "    \"AddedUsers\": [],\r\n"
				+ "    \"RemovedUsers\": []\r\n" + "}";
		Response response = au.submitRequest(METHOD_TYPE.POST, addInstJson, headers, manageLocation.AddLocationUrl);
		Gson gson = new GsonBuilder().create();
		SingleDataResponse sdr = gson.fromJson(response.asString(), SingleDataResponse.class);
		createdLocInstId = sdr.Data.Id;
		TestValidation.IsTrue((sdr.Status), "Location Instance " + locInstName + " is created",
				"Could NOT create location Instance " + locInstName);
		return createdLocInstId;

	}

	public void Link_Resource_LocationInstance(String createdCustInstId, String locCatName, String createdLocInstId,
			String locInstName) {

		au = new ApiUtils();
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("Accept", "application/json, text/plain, /");
		headers.put("Authorization", "Bearer " + accessToken);

		String linkResourceJson = "{\r\n" + "\"ResourceTypeID\":\"" + catID + "\",\r\n" + "\"BusinessObjectID\":\""
				+ createdCustInstId + "\",\r\n" + "\"LinksWithResourceType\":[\r\n" + "{\r\n" + "\"Id\":\""
				+ locationCatID + "\",\r\n" + "\"Name\":\"Locations\",\r\n" + "\"Collection\":[\r\n" + "{\r\n"
				+ "\"Id\":\"" + createdLocInstId + "\",\r\n" + "\"Name\":\"Locations > " + locCatName + " > "
				+ locInstName + "\"\r\n" + "}\r\n" + "]\r\n" + "}\r\n" + "],\r\n"
				+ "\"RemovedLinksWithResoueceType\":[\r\n" + "{\r\n" + "\"Id\":\"" + locationCatID + "\",\r\n"
				+ "\"Name\":\"Locations\",\r\n" + "\"Collection\":[\r\n" + "]\r\n" + "}\r\n" + "]\r\n" + "}";
		Response response = au.submitRequest(METHOD_TYPE.POST, linkResourceJson, headers,
				manageResources.LinkResourceUrl);
		Gson gson = new GsonBuilder().create();
		SingleDataResponse sdr = gson.fromJson(response.asString(), SingleDataResponse.class);
		// createdCustInstId = sdr.Data.;
		TestValidation.IsTrue((sdr.Status), "resource linked with Location Instance " + locCatName + " is created",
				"Could NOT link location Instance " + locCatName);

	}

	public void Link_LocationInstance_User(String searchString, String locCatId, String locInstId) {

		String userId = resourceDesigner.getUserFieldId(searchString);

		// List<String> listOfEnabledFields = new ArrayList<String>();
		// listOfEnabledFields =
		// resourceDesigner.createResFieldsJson(CATEGORY_TYPES.CUSTOMERS);
		// String fieldDets = resourceDesigner.updateResFieldsJson(listOfEnabledFields,
		// custInstName, number, text);

		au = new ApiUtils();
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("Accept", "application/json, text/plain, /");
		headers.put("Authorization", "Bearer " + accessToken);

		// Link User with Location

		String linkResourceJson = "{\r\n" + "   \"ParentBusinessObjectId\":\"" + locCatId + "\",\r\n"
				+ "   \"Name\":null,\r\n" + "   \"ResourceTypeId\":\"" + locationCatID + "\",\r\n"
				+ "   \"FieldValues\":[\r\n" + "      \r\n" + "   ],\r\n" + "   \"Id\":\"" + locInstId + "\",\r\n"
				+ "   \"IsEnabled\":true,\r\n" + "   \"BusinessObjectRefsWithResourceType\":[\r\n" + "      \r\n"
				+ "   ],\r\n" + "   \"RemovedBusinessObjectRefsWithResourceType\":[\r\n" + "      \r\n" + "   ],\r\n"
				+ "   \"AddedUsers\":[\r\n" + "      {\r\n" + "         \"Id\":\"" + userId + "\"\r\n" + "      }\r\n"
				+ "   ],\r\n" + "   \"RemovedUsers\":[\r\n" + "      \r\n" + "   ]\r\n" + "}\r\n" + "	";
		Response response = au.submitRequest(METHOD_TYPE.POST, linkResourceJson, headers,
				manageResources.EditLocationForUsersUrl);

		Gson gson = new GsonBuilder().create();
		SingleDataResponse sdr = gson.fromJson(response.asString(), SingleDataResponse.class);
		// createdCustInstId = sdr.Data.;
		TestValidation.IsTrue((sdr.Status), "Link Location Instance with User " + searchString + " is created",
				"Could NOT link location Instance with user " + searchString);

	}

	// @Test(description = "Link Location Instances with User ")
		public void Link_LocationInstance_User(List<String> searchString, String locCatId, String locInstId) {
			List<String> userIds = new ArrayList<String>();

			for (String userName : searchString) {
				String userId = resourceDesigner.getUserFieldId(userName);
				userIds.add(userId);
			}

			//		List<String> listOfEnabledFields = new ArrayList<String>();
			//		listOfEnabledFields = resourceDesigner.createResFieldsJson(CATEGORY_TYPES.CUSTOMERS);
			//		String fieldDets = resourceDesigner.updateResFieldsJson(listOfEnabledFields, custInstName, number, text);

			au = new ApiUtils();
			Map<String, String> headers = new HashMap<String, String>();
			headers.put("Content-Type", "application/json");
			headers.put("Accept", "application/json, text/plain, /");
			headers.put("Authorization", "Bearer " + accessToken);

			// Link User with Location

			String linkResourceJson = "{\r\n" + "   \"ParentBusinessObjectId\":\"" + locCatId + "\",\r\n"
					+ "   \"Name\":null,\r\n" + "   \"ResourceTypeId\":\"" + locationCatID + "\",\r\n"
					+ "   \"FieldValues\":[\r\n" + "      \r\n" + "   ],\r\n" + "   \"Id\":\"" + locInstId + "\",\r\n"
					+ "   \"IsEnabled\":true,\r\n" + "   \"BusinessObjectRefsWithResourceType\":[\r\n" + "      \r\n"
					+ "   ],\r\n" + "   \"RemovedBusinessObjectRefsWithResourceType\":[\r\n" + "      \r\n" + "   ],\r\n"
					+ "   \"AddedUsers\":[" + Support.updateUserList(userIds) + " ],\r\n" + "   \"RemovedUsers\":[\r\n"
					+ "      \r\n" + "   ]\r\n" + "}\r\n" + "	";

			Response response = au.submitRequest(METHOD_TYPE.POST, linkResourceJson, headers,
					manageResources.EditLocationForUsersUrl);

			Gson gson = new GsonBuilder().create();
			SingleDataResponse sdr = gson.fromJson(response.asString(), SingleDataResponse.class);
			// createdCustInstId = sdr.Data.;
			TestValidation.IsTrue((sdr.Status), "Location Instance linked with user " + searchString + " is created",
					"Could NOT link location Instance with user " + searchString);

		}

		
	
	public String verifylocationCategory(String locationId, String locationCategory) {
		String locationCatId = "";
		
		au = new ApiUtils();
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("Accept", "application/json, text/plain, /");
		headers.put("Authorization", "Bearer " + accessToken);
		Gson gson = new GsonBuilder().create();
		try {
			String getLocationCategoriesJson = "{\"Type\":\"" + locationId + "\"}";

			Response getComplianceResponse = au.submitRequest(METHOD_TYPE.POST, getLocationCategoriesJson, headers,
					GetLocationCategories);
			MultipleDataResponse sdr = gson.fromJson(getComplianceResponse.asString(), MultipleDataResponse.class);
			// System.out.println(" sdr.Data.size()" + sdr.Data.size());
			for (int i = 0; i < sdr.Data.size(); i++) {

				if (sdr.Data.get(i).Name.equalsIgnoreCase(locationCategory)) {

					logInfo("location Category is already present= " + sdr.Data.get(i).Name);
					locationCatId = sdr.Data.get(i).Id;
					break;
				}

			}
			return locationCatId;

		} catch (Exception e) {
			logInfo("Couldnt verify location Categoty");
			return locationCatId;
		}
	}

	public String verifylocationInstance(String locationId, String locationCategory, String locationInstance) {
		String locationInstanceId = "";
		au = new ApiUtils();
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("Accept", "application/json, text/plain, /");
		headers.put("Authorization", "Bearer " + accessToken);
		Gson gson = new GsonBuilder().create();
		try {
			String getLocInstanceValuesJson = "{\r\n" + "    \"Data\": {\r\n" + "        \"IsParent\": true,\r\n"
					+ "        \"Type\": \"" + locationId + "\",\r\n" + "        \"Node\": \"" + locationCategory
					+ "\"\r\n" + "    }\r\n" + "}";

			Response getComplianceResponse = au.submitRequest(METHOD_TYPE.POST, getLocInstanceValuesJson, headers,
					manageResourceCategories);
			MultipleDataResponse sdr = gson.fromJson(getComplianceResponse.asString(), MultipleDataResponse.class);
			// System.out.println(" sdr.Data.size()" + sdr.Data.size());
			for (int i = 0; i < sdr.Data.size(); i++) {
				// logInfo("location instance= " + sdr.Data.get(i).Name);

				if (sdr.Data.get(i).Name.equalsIgnoreCase(locationInstance)) {

					logInfo("location instance is already present= " + sdr.Data.get(i).Name);
					locationInstanceId = sdr.Data.get(i).Id;
					break;
				}

			}
			return locationInstanceId;

		} catch (Exception e) {
			logInfo("Couldnt verify location Instance");
			return locationInstanceId;
		}
	}

	public boolean enableDirectObservation(String formId) {
		
		au = new ApiUtils();
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("Accept", "application/json, text/plain, /");
		headers.put("Authorization", "Bearer " + accessToken);
		new GsonBuilder().create();
		try {

			String addVerificatonRequest = "{\r\n"
					+ "    \"VeriicationId\": \"5cf98ec4-ab9b-4ba2-ae6c-4e002aa6a4cd\",\r\n"
					+ "    \"IsCommentEnabled\": true,\r\n" + "    \"RemovedFormIds\": [],\r\n"
					+ "    \"AddedFormIds\": [\r\n" + "        \"" + formId + "\"\r\n" + "    ]\r\n" + "}";

			au.submitRequest(METHOD_TYPE.POST, addVerificatonRequest, headers,
					formDesigner.VerificationUrl);

			return true;

		} catch (Exception e) {
			logInfo("Couldnt enable Direct Observation ");
			return false;
		}
	}
	
	public boolean DisableDirectObservation(String formId) {
		
		au = new ApiUtils();
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("Accept", "application/json, text/plain, /");
		headers.put("Authorization", "Bearer " + accessToken);
		new GsonBuilder().create();
		try {

			String addVerificatonRequest = "{\r\n"
					+ "    \"VeriicationId\": \"5cf98ec4-ab9b-4ba2-ae6c-4e002aa6a4cd\",\r\n"
					+ "    \"IsCommentEnabled\": true,\r\n" + "    \"RemovedFormIds\": [\r\n" + "  \"" + formId + "\"\r\n" + "],\r\n"
					+ "    \"AddedFormIds\": [ ]\r\n" + "}";

		au.submitRequest(METHOD_TYPE.POST, addVerificatonRequest, headers,
					formDesigner.VerificationUrl);

			return true;

		} catch (Exception e) {
			logInfo("Couldnt enable Direct Observation ");
			return false;
		}
	}

}
