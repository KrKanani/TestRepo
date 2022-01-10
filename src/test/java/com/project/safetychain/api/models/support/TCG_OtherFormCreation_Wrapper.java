package com.project.safetychain.api.models.support;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.project.safetychain.api.models.Auth;
import com.project.safetychain.api.models.Auth.MultipleDataResponse;
import com.project.safetychain.api.models.Auth.SingleDataResponse;
import com.project.safetychain.api.models.FormDesigner;
import com.project.safetychain.api.models.ManageLocation;
import com.project.safetychain.api.models.ManageResources;
import com.project.safetychain.api.models.ResourceDesigner;
import com.project.safetychain.api.models.ResourceDesigner.Data;
import com.project.safetychain.api.models.support.Support.RESOURCE_FIELDS;
import com.project.safetychain.api.models.support.Support.RESOURCE_TYPES;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.utilities.ApiUtils;
import com.project.utilities.ApiUtils.METHOD_TYPE;

import io.restassured.response.Response;

public class TCG_OtherFormCreation_Wrapper extends TestBase {

	Auth auth = null;
	ApiUtils apiUtils = null;
	ResourceDesigner resourceDesigner = null;
	ManageLocation manageLocation = null;
	ManageResources manageResources = null;
	FormDesigner formDesigner = null;
	public static String createdLocCatId = null;
	public static String createdCustCatId = null;
	public static String createdLocInstId = null;
	public static String createdInstId = null;
	public static String createdFormId = null;
	public static String nameId = null;
	public static String modifiedById = null;
	public static String lastModifiedId = null;
	public static String statusId = null;
	public static String statusValue = "true";
	public static String text = "abc", number = "3";
	public Map<String, String> headers = null;

	public TCG_OtherFormCreation_Wrapper(Map<String, String> headersSetup) {

		manageLocation = new ManageLocation();
		manageResources = new ManageResources();
		formDesigner = new FormDesigner();

		auth = new Auth();
		apiUtils = new ApiUtils();
		auth.setAccessToken(auth.otherLoginUrl);
		
		headers = headersSetup;

		resourceDesigner = new ResourceDesigner();
		resourceDesigner.setResourceCatIDs(resourceDesigner.otherResourceTypeUrl, headers);

	}

	// For other APIs
	
	// Done
	public String verifylocationCategory(FormParamsSetUrls urls, Map<String, String> headers, 
			String locationId, String locationCategory) {

		String locationCatId = "";
		Gson gson = new GsonBuilder().create();
		try {
			String getLocationCategoriesJson = "{\"Type\":\""+locationId+"\"}";

			Response getComplianceResponse = apiUtils.submitRequest(METHOD_TYPE.POST, getLocationCategoriesJson,
					headers, urls.GetLocationCategories);
			MultipleDataResponse sdr = gson.fromJson(getComplianceResponse.asString(), MultipleDataResponse.class);
			System.out.println(" sdr.Data.size()" + sdr.Data.size());
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
	
	public String createResourceCategory_Location(FormParamsSetUrls urls, Map<String, String> headers,
			String locCatName) {

		String addCatJson = "{\r\n" + "    \"BaseTypeID\": \"" + otherLocationCatID + "\",\r\n" + "    \"Name\": \""
				+ locCatName + "\",\r\n" + "    \"ID\": null,\r\n" + "    \"ResourceTypeID\": \"" + otherLocationCatID
				+ "\"\r\n" + "}";
		Response response = apiUtils.submitRequest(METHOD_TYPE.POST, addCatJson, headers, urls.AddResourceCatUrl);
		Gson gson = new GsonBuilder().create();
		SingleDataResponse sdr = gson.fromJson(response.asString(), SingleDataResponse.class);
		createdLocCatId = sdr.Data.Id;
		logInfo("Location Cat Id =   " + createdLocCatId);
		TestValidation.IsTrue((sdr.Status), "Location Category " + locCatName + " is created",
				"Could NOT create location category " + locCatName);
		return createdLocCatId;

	}
	
	public String verifylocationInstance(FormParamsSetUrls urls, Map<String, String> headers, 
			String locationId, String locationCategory, String locationInstance) {
		String locationInstanceId = "";

		Gson gson = new GsonBuilder().create();
		try {
			String getLocInstanceValuesJson = "{\r\n" + "    \"Data\": {\r\n" + "        \"IsParent\": true,\r\n"
					+ "        \"Type\": \"" + locationId + "\",\r\n" + "        \"Node\": \"" + locationCategory
					+ "\"\r\n" + "    }\r\n" + "}";

			Response getComplianceResponse = apiUtils.submitRequest(METHOD_TYPE.POST, getLocInstanceValuesJson, headers,
					urls.ManageResourceCategories);
			MultipleDataResponse sdr = gson.fromJson(getComplianceResponse.asString(), MultipleDataResponse.class);
			for (int i = 0; i < sdr.Data.size(); i++) {

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
	
	// Done
	public List<String> createResFieldsJson(FormParamsSetUrls urls, Map<String, String> headers, String resourceType) {
		String fieldDetail = null, resDesignJson = null, resFieldDetsJson = null;
		List<String> listOfEnabledFields = new ArrayList<String>();
		List<String> enabledFieldDets = new ArrayList<String>();
		Response resFields = null;
		Gson gson = new GsonBuilder().create();

		try {

			switch (resourceType) {
			case RESOURCE_TYPES.CUSTOMERS:
				resDesignJson = "{\r\n" + "    \"Id\": \"" + otherCustomersCatID + "\"\r\n" + "}";
				break;
			case RESOURCE_TYPES.EQUIPMENT:
				resDesignJson = "{\r\n" + "    \"Id\": \"" + otherEquipmentCatID + "\"\r\n" + "}";
				break;
			case RESOURCE_TYPES.ITEMS:
				resDesignJson = "{\r\n" + "    \"Id\": \"" + otherItemsCatID + "\"\r\n" + "}";
				break;
			case RESOURCE_TYPES.LOCATION:
				resDesignJson = "{\r\n" + "    \"Id\": \"" + otherLocationCatID + "\"\r\n" + "}";
				break;
			case RESOURCE_TYPES.SUPPLIERS:
				resDesignJson = "{\r\n" + "    \"Id\": \"" + otherSuppliersCatID + "\"\r\n" + "}";
				break;
			default:
				logError("Failed to set Json value  : " + resDesignJson);
				break;
			}

			// Get Resource Designer fields
			Response resDesign = apiUtils.submitRequest(METHOD_TYPE.POST, resDesignJson, headers, urls.ResourceCatFieldsUrl);
			MultipleDataResponse mdr = gson.fromJson(resDesign.asString(), MultipleDataResponse.class);
			for (Data data : mdr.Data) {
				if (data.IsEnable) {
					listOfEnabledFields.add(data.Field.Id);
				}
			}

			for (String fieldId : listOfEnabledFields) {
				switch (resourceType) {
				case RESOURCE_TYPES.CUSTOMERS:
					resFieldDetsJson = "{\r\n" + "    \"Id\": \"" + otherCustomersCatID + "\",\r\n" + "    \"FieldId\": \""
							+ fieldId + "\"\r\n" + "}";
					break;
				case RESOURCE_TYPES.EQUIPMENT:
					resFieldDetsJson = "{\r\n" + "    \"Id\": \"" + otherEquipmentCatID + "\",\r\n" + "    \"FieldId\": \""
							+ fieldId + "\"\r\n" + "}";
					break;
				case RESOURCE_TYPES.ITEMS:
					resFieldDetsJson = "{\r\n" + "    \"Id\": \"" + otherItemsCatID + "\",\r\n" + "    \"FieldId\": \""
							+ fieldId + "\"\r\n" + "}";
					break;
				case RESOURCE_TYPES.LOCATION:
					resFieldDetsJson = "{\r\n" + "    \"Id\": \"" + otherLocationCatID + "\",\r\n" + "    \"FieldId\": \""
							+ fieldId + "\"\r\n" + "}";
					break;
				case RESOURCE_TYPES.SUPPLIERS:
					resFieldDetsJson = "{\r\n" + "    \"Id\": \"" + otherSuppliersCatID + "\",\r\n" + "    \"FieldId\": \""
							+ fieldId + "\"\r\n" + "}";
					break;
				default:
					logError("Failed to set Json value  : " + resFieldDetsJson);
					break;
				}

				resFields = apiUtils.submitRequest(METHOD_TYPE.POST, resFieldDetsJson, headers, urls.ResourceCatFieldsValUrl);
				mdr = gson.fromJson(resFields.asString(), MultipleDataResponse.class);
				if (mdr.Data.get(0).Type.equalsIgnoreCase("SingleSelect"))
					fieldDetail = fieldId + "|" + mdr.Data.get(0).FieldName + "|" + mdr.Data.get(0).Type + "|"
							+ mdr.Data.get(0).DataSource.get(0).Name;
				else
					fieldDetail = fieldId + "|" + mdr.Data.get(0).FieldName + "|" + mdr.Data.get(0).Type;
				enabledFieldDets.add(fieldDetail);
			}
			logInfo("Add details of enabled fields : " + enabledFieldDets);
			return enabledFieldDets;

		} catch (Exception e) {
			logError("Failed to add details of enabled fields - " + e.getMessage());
			return enabledFieldDets;
		}
	}
	
	// Done
	public void getResourceCreationInputFieldIds(FormParamsSetUrls urls) {
		nameId = resourceDesigner.getResourceFieldId(urls.ResourceFieldDetailsUrl, headers, 
				RESOURCE_FIELDS.NAME, catID,createdCustCatId);
		modifiedById = resourceDesigner.getResourceFieldId(urls.ResourceFieldDetailsUrl, headers,
				RESOURCE_FIELDS.MODIFIED_BY, catID,createdCustCatId);
		lastModifiedId = resourceDesigner.getResourceFieldId(urls.ResourceFieldDetailsUrl, headers,
				RESOURCE_FIELDS.LAST_MODIFIED, catID,createdCustCatId);
		statusId = resourceDesigner.getResourceFieldId(urls.ResourceFieldDetailsUrl, headers,
				RESOURCE_FIELDS.STATUS, catID,createdCustCatId);
	}
	
	// Done
	public void createResourceCategory(FormParamsSetUrls urls, Map<String, String> headers, 
			String resourceCategory, String resourceType) {

		switch (resourceType) {
		case RESOURCE_TYPES.CUSTOMERS:
			catID = otherCustomersCatID;
			break;
		case RESOURCE_TYPES.ITEMS:
			catID = otherItemsCatID;
			break;
		case RESOURCE_TYPES.EQUIPMENT:
			catID = otherEquipmentCatID;
			break;
		case RESOURCE_TYPES.SUPPLIERS:
			catID = otherSuppliersCatID;
			break;

		}

		getResourceCreationInputFieldIds(urls);

		String addCatJson = "{\r\n" + "    \"BaseTypeID\": \"" + catID + "\",\r\n" + "    \"Name\": \""
				+ resourceCategory + "\",\r\n" + "    \"ID\": null,\r\n" + "    \"ResourceTypeID\": \"" + catID
				+ "\"\r\n" + "}";

		Response response = apiUtils.submitRequest(METHOD_TYPE.POST, addCatJson, headers,
				urls.AddResourceCatUrl);
		Gson gson = new GsonBuilder().create();
		SingleDataResponse sdr = gson.fromJson(response.asString(), SingleDataResponse.class);
		createdCustCatId = sdr.Data.Id;
		TestValidation.IsTrue((sdr.Status), "Resource Category " + resourceCategory + " is created",
				"Could NOT create resource category " + resourceCategory);

	}

	// Done
	public void createResourceInstance(FormParamsSetUrls urls, Map<String, String> headers, 
			String resourceInstance, String resourceType) {

		List<String> listOfEnabledFields = new ArrayList<String>();
		switch (resourceType) {
		case RESOURCE_TYPES.CUSTOMERS:
			listOfEnabledFields = createResFieldsJson(urls, headers, RESOURCE_TYPES.CUSTOMERS);
			break;
		case RESOURCE_TYPES.ITEMS:
			listOfEnabledFields = createResFieldsJson(urls, headers, RESOURCE_TYPES.ITEMS);
			break;
		case RESOURCE_TYPES.EQUIPMENT:
			listOfEnabledFields = createResFieldsJson(urls, headers, RESOURCE_TYPES.EQUIPMENT);
			break;
		case RESOURCE_TYPES.SUPPLIERS:
			listOfEnabledFields = createResFieldsJson(urls, headers, RESOURCE_TYPES.SUPPLIERS);
			break;

		}

		String fieldDets = resourceDesigner.updateResFieldsJson(listOfEnabledFields, resourceInstance, number, text);

		String addInstJson = "{\r\n" + "    \"BaseTypeID\": \"" + createdCustCatId + "\",\r\n" + "    \"Name\": \""
				+ resourceInstance + "\",\r\n" + "    \"ResourceTypeID\": \"" + catID + "\",\r\n"
				+ "    \"ResourceValue\": [\r\n" + "        {\r\n" + "            \"FieldName\": \"SCSModifiedBy\",\r\n"
				+ "            \"FieldId\": \"" + modifiedById + "\",\r\n" + "            \"Value\": null,\r\n"
				+ "            \"Type\": \"Label\"\r\n" + "        },\r\n" + "        {\r\n"
				+ "            \"FieldName\": \"SCSLastModified\",\r\n" + "            \"FieldId\": \"" + lastModifiedId
				+ "\",\r\n" + "            \"Value\": null,\r\n" + "            \"Type\": \"Label\"\r\n"
				+ "        },\r\n" + "		 {\r\n" + "			 \"FieldName\":\"SCSStatus\",\r\n"
				+ "			 \"FieldId\":\"" + statusId + "\",\r\n" + "			 \"Value\":"+ statusValue +",\r\n"
				+ "			 \"Type\":\"Toggle\"\r\n" + "        },\r\n" + fieldDets + "    ]\r\n" + "}";
		
		Response response = apiUtils.submitRequest(METHOD_TYPE.POST, addInstJson, headers, 
				urls.AddNewResourceUrl);
		Gson gson = new GsonBuilder().create();
		SingleDataResponse sdr = gson.fromJson(response.asString(), SingleDataResponse.class);
		createdInstId = sdr.Data.Id;
		TestValidation.IsTrue((sdr.Status), "Resource Instance " + resourceInstance + " is created",
				"Could NOT create resource Instance " + resourceInstance);
		// return createdInstId;
	}

	// Done
	public String createResourceInstance_Location(FormParamsSetUrls urls, Map<String, String> headers, String locInstName) {
		List<String> listOfEnabledFields = new ArrayList<String>();
		listOfEnabledFields = createResFieldsJson(urls, headers, RESOURCE_TYPES.LOCATION);
		String fieldDets = resourceDesigner.updateResFieldsJson(listOfEnabledFields, locInstName, number, text);

		String addInstJson = "{\r\n" + "    \"ParentBusinessObjectId\": \"" + createdLocCatId + "\",\r\n"
				+ "    \"Name\": \"" + locInstName + "\",\r\n" + "    \"ResourceTypeId\": \"" + otherLocationCatID
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
		
		Response response = apiUtils.submitRequest(METHOD_TYPE.POST, addInstJson, headers,
				urls.AddLocationUrl);
		Gson gson = new GsonBuilder().create();
		SingleDataResponse sdr = gson.fromJson(response.asString(), SingleDataResponse.class);
		createdLocInstId = sdr.Data.Id;
		TestValidation.IsTrue((sdr.Status), "Location Instance " + createdLocInstId + " is created",
				"Could NOT create location Instance " + createdLocInstId);
		return createdLocInstId;

	}
	
	// Done
	public void link_Resource_LocationInstance(FormParamsSetUrls urls, Map<String, String> headers, String createdCustInstId, 
			String locCatName, String createdLocInstId, String locInstName) {

		String linkResourceJson = "{\r\n" + "\"ResourceTypeID\":\"" + catID + "\",\r\n" + "\"BusinessObjectID\":\""
				+ createdCustInstId + "\",\r\n" + "\"LinksWithResourceType\":[\r\n" + "{\r\n" + "\"Id\":\""
				+ otherLocationCatID + "\",\r\n" + "\"Name\":\"Locations\",\r\n" + "\"Collection\":[\r\n" + "{\r\n"
				+ "\"Id\":\"" + createdLocInstId + "\",\r\n" + "\"Name\":\"Locations > " + locCatName + " > "
				+ locInstName + "\"\r\n" + "}\r\n" + "]\r\n" + "}\r\n" + "],\r\n"
				+ "\"RemovedLinksWithResoueceType\":[\r\n" + "{\r\n" + "\"Id\":\"" + otherLocationCatID + "\",\r\n"
				+ "\"Name\":\"Locations\",\r\n" + "\"Collection\":[\r\n" + "]\r\n" + "}\r\n" + "]\r\n" + "}";
		
		Response response = apiUtils.submitRequest(METHOD_TYPE.POST, linkResourceJson, headers, 
				urls.LinkResourceUrl);
		Gson gson = new GsonBuilder().create();
		SingleDataResponse sdr = gson.fromJson(response.asString(), SingleDataResponse.class);
		TestValidation.IsTrue((sdr.Status), "Location Instance " + locCatName + " is created",
				"Could NOT create location Instance " + locCatName);

	}

	// Done
	public String getUserFieldId(FormParamsSetUrls urls, Map<String, String> headers, String searchString) {
		String FieldId = null;

		String linkResourceJson = "{\r\n" + "   \"take\":100,\r\n" + "   \"skip\":0,\r\n" + "   \"page\":1,\r\n"
				+ "   \"pageSize\":100,\r\n" + "   \"Parameters\":{\r\n" + "      \"SearchString\":\"" + searchString
				+ "\",\r\n" + "      \"Columns\":[\r\n" + "         \"check_row\",\r\n" + "         \"username\",\r\n"
				+ "         \"firstname\",\r\n" + "         \"lastname\"\r\n" + "      ],\r\n"
				+ "      \"IsHideDeactiveUser\":true,\r\n" + "      \"FirstRowId\":null,\r\n"
				+ "      \"LastRowId\":null\r\n" + "   },\r\n" + "   \"FirstRowId\":null,\r\n"
				+ "   \"LastRowId\":null\r\n" + "}";
		
		Response response = apiUtils.submitRequest(METHOD_TYPE.POST, linkResourceJson, headers, 
				urls.UserViewUrl);
		Gson gson = new GsonBuilder().create();

		SingleDataResponse sdr = gson.fromJson(response.asString(), SingleDataResponse.class);
		for (int i = 0; i < sdr.Data.Rows.size(); i++) {
			if (sdr.Data.Rows.get(i).Username.equalsIgnoreCase(searchString)) {
				FieldId = sdr.Data.Rows.get(i).Id;
				return FieldId;
			}
		}
		return FieldId;
	}
	
	// Done
	public void link_LocationInstance_User(FormParamsSetUrls urls, Map<String, String> headers,
			List<String> searchString, String locCatId, String locInstId) {
		List<String> userIds = new ArrayList<String>();

		for (String userName : searchString) {
			String userId = getUserFieldId(urls, headers, userName);
			userIds.add(userId);
		}

		String linkResourceJson = "{\r\n" + "   \"ParentBusinessObjectId\":\"" + locCatId + "\",\r\n"
				+ "   \"Name\":null,\r\n" + "   \"ResourceTypeId\":\"" + otherLocationCatID + "\",\r\n"
				+ "   \"FieldValues\":[\r\n" + "      \r\n" + "   ],\r\n" + "   \"Id\":\"" + locInstId + "\",\r\n"
				+ "   \"IsEnabled\":true,\r\n" + "   \"BusinessObjectRefsWithResourceType\":[\r\n" + "      \r\n"
				+ "   ],\r\n" + "   \"RemovedBusinessObjectRefsWithResourceType\":[\r\n" + "      \r\n" + "   ],\r\n"
				+ "   \"AddedUsers\":[" + Support.updateUserList(userIds) + " ],\r\n" + "   \"RemovedUsers\":[\r\n"
				+ "      \r\n" + "   ]\r\n" + "}\r\n" + "	";

		Response response = apiUtils.submitRequest(METHOD_TYPE.POST, linkResourceJson, headers,
				urls.EditLocationForUsersUrl);

		Gson gson = new GsonBuilder().create();
		SingleDataResponse sdr = gson.fromJson(response.asString(), SingleDataResponse.class);
		TestValidation.IsTrue((sdr.Status), "Linked Location Instance with users " + searchString,
				"Could NOT Link Location Instance with users " + searchString);

	}
	
	public HashMap<String, String> resource_Location_CreationWrapper(FormParamsSetUrls urls, 
			HashMap<String, List<String>> custCatName, HashMap<String, List<String>> locationCat, 
			boolean linkResource, List<String> userList, String resourceType) {

		List<String> locCat = new ArrayList<String>();
		List<String> locInst = new ArrayList<String>();
		List<String> locInstId = new ArrayList<String>();
		HashMap<String, String> locResMap = new HashMap<String, String>();
		for (Map.Entry<String, List<String>> entry : locationCat.entrySet()) {
			String locCatName = entry.getKey();

			List<String> locInstances = entry.getValue();
			String locId = verifylocationCategory(urls, headers, otherLocationCatID, locCatName);

			if (locId.equalsIgnoreCase("")) 
				createResourceCategory_Location(urls, headers, locCatName);
			else
				createdLocCatId = locId;

			for (String locInstance : locInstances) {
				String locInstanceId = "";
				locInstanceId = verifylocationInstance(urls, headers,
						otherLocationCatID, locId, locInstance);

				if (locInstanceId.equalsIgnoreCase("")) {
					locInstanceId = createResourceInstance_Location(urls, headers, 
							locInstance);
				}
				else
					createdLocInstId = locInstanceId;
				
				locCat.add(locCatName);
				locInst.add(locInstance);
				locInstId.add(locInstanceId);
				locResMap.put(locInstance, locInstanceId);

			}
		}

		for (Map.Entry<String, List<String>> entry : custCatName.entrySet()) {
			String fieldName = entry.getKey();
			List<String> fieldValues = entry.getValue();
			createResourceCategory(urls, headers, fieldName, resourceType);
			for (String resInstance : fieldValues) {
				createResourceInstance(urls, headers, resInstance, resourceType);
				locResMap.put(resInstance, createdInstId);
				logInfo("locInst Size ======== " + locInst.size());
				for (int i = 0; i < locInst.size(); i++) {
					link_Resource_LocationInstance(urls, headers, 
							createdInstId, locCat.get(i), locInstId.get(i), locInst.get(i));
				}
			}
		}

		for (int i = 0; i < locInstId.size(); i++) {
			logInfo("locInst value ======== " + locInst.get(i));
			link_LocationInstance_User(urls, headers, userList, createdLocCatId, locInstId.get(i));
		}
		return locResMap;
	}
	
	public static class FormParamsSetUrls {
		public String ResourceCatFieldsUrl = null; //resourceDesigner.otherResourceCatFieldsUrl;
		public String ResourceCatFieldsValUrl = null; //resourceDesigner.otherResourceCatFieldsValUrl;
		public String AddNewResourceUrl = null; //manageResources.otherAddNewResourceUrl;
		public String AddLocationUrl = null; //manageLocation.otherAddLocationUrl;
		public String LinkResourceUrl = null; //manageResources.otherLinkResourceUrl
		public String UserViewUrl = null; //resourceDesigner.otherUserViewUrl
		public String EditLocationForUsersUrl = null; //manageResources.otherEditLocationForUsersUrl
		public String AddResourceCatUrl = null; //resourceDesigner.otherAddResourceCatUrl
		public String ResourceFieldDetailsUrl = null; //resourceDesigner.otherResourceFieldDetailsUrl
		public String ManageResourceCategories = null; //formDesigner.otherManageResourceCategories
		public String GetLocationCategories = null; //formDesigner.otherGetLocationCategories
		
	}
	
	public static class FormCreation_FTUFlows {
		public String excelSheetName = null;
		public String resourceType = null;
		public String locCatName = null;
		public String resCatName = null;
		public String locInstName1 = null;
		public String locInstName2 = null;
		public String resInstName1 = null;
		public String resInstName2 = null;
		
	}
}
