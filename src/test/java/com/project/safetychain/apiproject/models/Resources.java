package com.project.safetychain.apiproject.models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.project.safetychain.apiproject.models.Auth;
import com.project.utilities.ApiUtils;
import com.project.utilities.ApiUtils.METHOD_TYPE;

import io.restassured.response.Response;

public class Resources  extends Auth{

	public String GetLocationResourceUrl = baseURI + ResourcesURLs.GET_LOCATION_RESOURCE;
	public String GetRootLocCatUrl = baseURI + ResourcesURLs.GET_ROOTLOC_CAT_URL;
	public String AddLocationUrl = baseURI + ResourcesURLs.ADD_LOCATION_URL;
	public String GetLocationUrl = baseURI + ResourcesURLs.GET_LOCATION_URL;
	public String AddNewResource = baseURI + ResourcesURLs.ADD_NEW_RESOURCE_URL;
	public String GetResourceCategory = baseURI + ResourcesURLs.MANAGE_RESOURCE_CATEGORY;
	public String GetHeaderDetailsUrl = baseURI + ResourcesURLs.GET_HEADER_DETAILS_URL;
	public String LocationResourceLinkUrl = baseURI + ResourcesURLs.LOCATION_RESOURCE_LINK_URL;
	public String RelatedLinks = baseURI + ResourcesURLs.RELATED_LINKS;
	public String SaveLinks = baseURI + ResourcesURLs.SAVE_LINKS;

	ApiUtils apiUtils = new ApiUtils();
	Gson gson = new GsonBuilder().create();
	String resourceTypeId = null;
	String EnabledFieldId = null , EnabledFieldName = null, DisabledFieldId = null;
	String customersCatID, equipmentCatID,itemsCatID, locationCatID,suppliersCatID ;
	String resourceCatId = null;
	String locationInstanceId = null;
	String LocationTypeId = null, ResourceInstanceId = null;;
	String resourceTypefields = "", appendResourceTypeFields = null;

	public static String nameId = null;
	public static String modifiedById = null;
	public static String lastModifiedId = null;
	public static String statusId = null;
	public static String statusValue = "true";
	public static String text = "abc", number = "3";

	ResourceDesigner designer = new ResourceDesigner();

	/**
	 * This method is used to get Location Cat ID
	 * @author choubey_a
	 * @param locationCatName - the name of the location category
	 * @return location category Id
	 */
	public String getLocCatId(String locationCatName) {

		String locationCatId = null;

		apiUtils = new ApiUtils();
		Gson gson = new GsonBuilder().create();
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("Accept", "application/json, text/plain, /");
		headers.put("Authorization", "Bearer " + accessToken);

		try {
			Response response = apiUtils.submitRequest(METHOD_TYPE.GET, null, headers,
					GetLocationResourceUrl);

			SimpleDataResponse sdr = gson.fromJson(response.asString(),
					SimpleDataResponse.class);

			LocationTypeId = sdr.Data;

			logInfo(LocationTypeId);

			String request = "{\"Type\":\""+LocationTypeId+"\"}";

			Response response1 = apiUtils.submitRequest(METHOD_TYPE.POST, request, headers,
					GetRootLocCatUrl);

			MultipleDataResponse mdr = gson.fromJson(response1.asString(),
					MultipleDataResponse.class);

			for(int i=0; i < mdr.Data.size(); i++) {

				if(mdr.Data.get(i).Name.equals(locationCatName)) {
					locationCatId = mdr.Data.get(i).Id;
				}				
			}
			logInfo("Location category ID of category - "+locationCatName+ "is - "+locationCatId);
		}catch(Exception e) {
			logError("Could not fetch details of location category - "+locationCatName+ " "+e.getMessage());
		}
		return locationCatId;
	}

	/**
	 * This method is used to get the details of the fields of the resource type
	 * @author choubey_a
	 * @param listOfEnabledFields - Id of the enabled field ID
	 * @param instanceName - name of the resource instance
	 * @return the field IDs of the resource type
	 */

	public String getResourceTypeFields(List<String> listOfEnabledFields, String instanceName) {


		try {
			for (int i = 0; i < listOfEnabledFields.size(); i++) {

				String[] details = listOfEnabledFields.get(i).split("\\|");
				if (i != (listOfEnabledFields.size() - 1)) {
					if (details[2].equalsIgnoreCase("SingleSelect")) {
						appendResourceTypeFields = "        {\r\n" + "            \"FieldId\": \"" + details[0] + "\",\r\n"
								+ "            \"Value\": {\r\n" + "                \"Name\": \"" + details[3]
										+ "\"\r\n" + "            },\r\n" + "            \"Type\": \"" + details[2] + "\",\r\n"
										+ "            \"FieldName\": \"" + details[1] + "\"\r\n" + "        },\r\n";
					} else if (details[2].equalsIgnoreCase("Textbox")) {
						if (details[1].equalsIgnoreCase("SCSName")) {
							appendResourceTypeFields = "        {\r\n" + "            \"FieldId\": \"" + details[0] + "\",\r\n"
									+ "            \"Value\": \"" + instanceName + "\",\r\n"
									+ "            \"Type\": \"" + details[2] + "\",\r\n"
									+ "            \"FieldName\": \"" + details[1] + "\"\r\n" + "        },\r\n";
						} else {
							appendResourceTypeFields = "        {\r\n" + "            \"FieldId\": \"" + details[0] + "\",\r\n"
									+ "            \"Value\": \"ResourceText\",\r\n" + "            \"Type\": \""
									+ details[2] + "\",\r\n" + "            \"FieldName\": \"" + details[1] + "\"\r\n"
									+ "        },\r\n";
						}
					} else if (details[2].equalsIgnoreCase("Numeric")) {
						appendResourceTypeFields = "        {\r\n" + "            \"FieldId\": \"" + details[0] + "\",\r\n"
								+ "            \"Value\": \"12\",\r\n" + "            \"Type\": \""
								+ details[2] + "\",\r\n" + "            \"FieldName\": \"" + details[1] + "\"\r\n"
								+ "        },\r\n";
					}
				} else {
					if (details[2].equalsIgnoreCase("SingleSelect")) {
						appendResourceTypeFields = "        {\r\n" + "            \"FieldId\": \"" + details[0] + "\",\r\n"
								+ "            \"Value\": {\r\n" + "                \"Name\": \"" + details[3]
										+ "\"\r\n" + "            },\r\n" + "            \"Type\": \"" + details[2] + "\",\r\n"
										+ "            \"FieldName\": \"" + details[1] + "\"\r\n" + "        }\r\n";
					} else if (details[2].equalsIgnoreCase("Textbox")) {
						if (details[1].equalsIgnoreCase("SCSName")) {
							appendResourceTypeFields = "        {\r\n" + "            \"FieldId\": \"" + details[0] + "\",\r\n"
									+ "            \"Value\": \"" + instanceName + "\",\r\n"
									+ "            \"Type\": \"" + details[2] + "\",\r\n"
									+ "            \"FieldName\": \"" + details[1] + "\"\r\n" + "        }\r\n";
						} else {
							appendResourceTypeFields = "        {\r\n" + "            \"FieldId\": \"" + details[0] + "\",\r\n"
									+ "            \"Value\": \"ResourceText\",\r\n" + "            \"Type\": \""
									+ details[2] + "\",\r\n" + "            \"FieldName\": \"" + details[1] + "\"\r\n"
									+ "        }\r\n";
						}
					} else if (details[2].equalsIgnoreCase("Numeric")) {
						appendResourceTypeFields = "        {\r\n" + "            \"FieldId\": \"" + details[0] + "\",\r\n"
								+ "            \"Value\": \"12\",\r\n" + "            \"Type\": \""
								+ details[2] + "\",\r\n" + "            \"FieldName\": \"" + details[1] + "\"\r\n"
								+ "        }\r\n";
					}
				}

				resourceTypefields = resourceTypefields + appendResourceTypeFields;
			}
			return resourceTypefields;
		} catch (Exception e) {
			logError("Failed to get details of resource Types fields - " + e.getMessage());
			return resourceTypefields;
		}
	}

	/**
	 * This method is used to create Location Instance
	 * @author choubey_a
	 * @param locationCatName - name of the location category
	 * @param locationInstanceName - name of the location instance
	 * @return the new location instance ID
	 */

	public String createLocInstance(String locationCatId,String locationCatName, String locationInstanceName, String resourceTypefields) {

		try {
			apiUtils = new ApiUtils();
			Gson gson = new GsonBuilder().create();
			Map<String, String> headers = new HashMap<String, String>();
			headers.put("Content-Type", "application/json");
			headers.put("Accept", "application/json, text/plain, /");
			headers.put("Authorization", "Bearer " + accessToken);


			String request = "{\r\n" + 
					"   \"ParentBusinessObjectId\":\""+locationCatId+"\",\r\n" + 
					"   \"Name\":\""+locationInstanceName+"\",\r\n" + 
					"   \"ResourceTypeId\":\"ab23a562-40df-41ea-a2c4-f946b443a32f\",\r\n" + 
					"   \"FieldValues\":[\r\n" + 
					"      {\r\n" + 
					"         \"FieldId\":\"87f4b0fd-5f60-4959-ae79-852e7c1fade1\",\r\n" + 
					"         \"Value\":null,\r\n" + 
					"         \"Type\":\"Label\",\r\n" + 
					"         \"FieldName\":\"SCSModifiedBy\"\r\n" + 
					"      },\r\n" + 
					"      \r\n" + resourceTypefields + 
					"      ,{\r\n" + 
					"         \"FieldId\":\"c8a150e1-3c01-4b2f-81fc-ac6cc19b138e\",\r\n" + 
					"         \"Value\":null,\r\n" + 
					"         \"Type\":\"Label\",\r\n" + 
					"         \"FieldName\":\"SCSLastModified\"\r\n" + 
					"      }\r\n" + 
					"   ],\r\n" + 
					"   \"Id\":null,\r\n" + 
					"   \"IsEnabled\":true,\r\n" + 
					"   \"BusinessObjectRefsWithResourceType\":[\r\n" + 
					"      \r\n" + 
					"   ],\r\n" + 
					"   \"RemovedBusinessObjectRefsWithResourceType\":[\r\n" + 
					"      \r\n" + 
					"   ],\r\n" + 
					"   \"AddedUsers\":[\r\n" + 
					"      \r\n" + 
					"   ],\r\n" + 
					"   \"RemovedUsers\":[\r\n" + 
					"      \r\n" + 
					"   ]\r\n" + 
					"}";

			Response response = apiUtils.submitRequest(METHOD_TYPE.POST, request, headers,
					AddLocationUrl);

			SingleDataResponse sdr = gson.fromJson(response.asString(), SingleDataResponse.class);
			locationInstanceId = sdr.Data.Id;
			logInfo("Location Instance "+locationInstanceName+" is created");
		}catch(Exception e) {
			logError("Could not create location Instance "+locationInstanceName+ " "+e.getMessage());
		}
		return locationInstanceId;
	}

	/**
	 * This method is used to verify created location
	 * @author choubey_a
	 * @param categoryId - ID of the location category 
	 * @param instanceid - ID of the location instance created
	 * @param resourceTypeId - ID of the location resource Type
	 * @return true if created location instance id found
	 */

	public boolean getLocation (String categoryId, String instanceId, String resourecTypeId) {
		try {
			apiUtils = new ApiUtils();
			Gson gson = new GsonBuilder().create();
			Map<String, String> headers = new HashMap<String, String>();
			headers.put("Content-Type", "application/json");
			headers.put("Accept", "application/json, text/plain, /");
			headers.put("Authorization", "Bearer " + accessToken);

			String request = "{\"Id\":\""+instanceId+"\","
					+ "\"ParentBusinessObjectId\":\""+categoryId+"\","
					+ "\"ResourceTypeId\":\""+resourecTypeId+"\"}";

			Response response = apiUtils.submitRequest(METHOD_TYPE.POST, request, headers,
					GetLocationUrl);

			SingleDataResponse sdr = gson.fromJson(response.asString(), SingleDataResponse.class);
			if(!(sdr.Data.Id.contentEquals(instanceId) && sdr.Status == true)) {				
				logError("Location not created");
				return false;
			}
			return true;
		}catch(Exception e) {
			logError("Could not find location Instance -"+e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to get Resource Category (Customers/Equipment/Items/Suppliers)
	 * @author choubey_a
	 * @param resourceCategoryName - ID of the resource category 
	 * @param resourceTypeId - ID of the location resource Type
	 * @return true if created location instance id found
	 */

	public String getResourceCategoryId(String resourceTypeId, String resourceCategoryName) {
		try {
			apiUtils = new ApiUtils();
			Map<String, String> headers = new HashMap<String, String>();
			headers.put("Content-Type", "application/json");
			headers.put("Accept", "application/json, text/plain, /");
			headers.put("Authorization", "Bearer " + accessToken);

			String request = "{\"Data\":{\"IsParent\":false,\"Type\":\""+resourceTypeId+"\","
					+ "\"Node\":\""+resourceTypeId+"\"}}";

			Response response = apiUtils.submitRequest(METHOD_TYPE.POST, request, headers,
					GetResourceCategory);

			MultipleDataResponse mdr = gson.fromJson(response.asString(),
					MultipleDataResponse.class);

			for(int i=0; i < mdr.Data.size(); i++) {

				if(mdr.Data.get(i).Name.equals(resourceCategoryName)) {
					resourceCatId = mdr.Data.get(i).Id;
				}				
			}
			logInfo("Resource category ID of category - "+resourceCategoryName+ "is - "+resourceCatId);
		}catch(Exception e) {
			logError("Could not fetch details of resource category - "+resourceCategoryName+ " "+e.getMessage());
		}
		return resourceCatId;
	}

	/**
	 * This method is used to get Resource Type Field ID's
	 * @author choubey_a
	 * @param fieldName - name of the Field to get the ID
	 * @param resourceTypeId - ID of the resource Type
	 * @return The ID of the field
	 */

	public String getResourceFieldId(String fieldName, String ResourceTypeId) {
		ApiUtils apiUtils = new ApiUtils();
		String FieldId = null;
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("Accept", "application/json, text/plain, /");
		headers.put("Authorization", "Bearer " + accessToken);
		Gson gson = new GsonBuilder().create();

		String linkResourceJson = "{\"Data\":\""+ResourceTypeId+"\"}";
		Response response = apiUtils.submitRequest(METHOD_TYPE.POST, linkResourceJson, headers, GetHeaderDetailsUrl);

		MultipleDataResponse multipleDataResponse = gson.fromJson(response.asString(), MultipleDataResponse.class);

		for (int i = 0; i < multipleDataResponse.Data.size(); i++) {		
			if (multipleDataResponse.Data.get(i).FieldName.equalsIgnoreCase(fieldName)) {
				FieldId = multipleDataResponse.Data.get(i).FieldId.toString();
				return FieldId;
			}

		}
		return FieldId;
	}

	/**
	 * This method is used to get resource type field ID's
	 * @author choubey_a
	 * @param resourceTypeId - ID of the resource Type
	 * @return null
	 */


	public void GetResourceCreationInputFieldIds(String ResourceTypeId) {

		nameId = getResourceFieldId(FIELD_DETAILS.NAME,ResourceTypeId);
		modifiedById = getResourceFieldId(FIELD_DETAILS.MODIFIED_BY,ResourceTypeId);
		lastModifiedId = getResourceFieldId(FIELD_DETAILS.LAST_MODIFIED,ResourceTypeId);
		statusId = getResourceFieldId(FIELD_DETAILS.STATUS,ResourceTypeId);

	}

	/**
	 * This method is used to create Resource Instance
	 * @author choubey_a
	 * @param fieldDetails - The string of all the fields in the resource type
	 * @param resourceInstanceName - name of the resource instance to be created
	 * @param ResourcecategoryId -  ID of the resource category
	 * @param resourceTypeId - ID of the resource Type
	 * @param Status - true/false/null - according to the status of the fields of a resource type
	 * @return ID of the created resource instance
	 */


	public String createResourceInstance(String fieldDetails,String recourceTypedId,String resourceInstanceName, String ResourcecategoryId, String status) {
		try {			
			if(status!=null)
				statusValue = status;

			apiUtils = new ApiUtils();
			Map<String, String> headers = new HashMap<String, String>();
			headers.put("Content-Type", "application/json");
			headers.put("Accept", "application/json, text/plain, /");
			headers.put("Authorization", "Bearer " + accessToken);


			GetResourceCreationInputFieldIds(recourceTypedId);

			String request = "{\r\n" + "    \"BaseTypeID\": \"" + ResourcecategoryId + "\",\r\n" + "    \"Name\": \""
					+ resourceInstanceName + "\",\r\n" + "    \"ResourceTypeID\": \"" + recourceTypedId + "\",\r\n"
					+ "    \"ResourceValue\": [\r\n" + "        {\r\n" + "            \"FieldName\": \"SCSModifiedBy\",\r\n"
					+ "            \"FieldId\": \"" + modifiedById + "\",\r\n" + "            \"Value\": null,\r\n"
					+ "            \"Type\": \"Label\"\r\n" + "        },\r\n" + "        {\r\n"
					+ "            \"FieldName\": \"SCSLastModified\",\r\n" + "            \"FieldId\": \"" + lastModifiedId
					+ "\",\r\n" + "            \"Value\": null,\r\n" + "            \"Type\": \"Label\"\r\n"
					+ "        },\r\n" + "		 {\r\n" + "			 \"FieldName\":\"SCSStatus\",\r\n"
					+ "			 \"FieldId\":\"" + statusId + "\",\r\n" + "			 \"Value\":"+ statusValue +",\r\n"
					+ "			 \"Type\":\"Toggle\"\r\n" + "        },\r\n" + fieldDetails + "    ]\r\n" + "}";

			Response response = apiUtils.submitRequest(METHOD_TYPE.POST, request, headers,
					AddNewResource);
			Gson gson = new GsonBuilder().create();
			SingleDataResponse sdr = gson.fromJson(response.asString(), SingleDataResponse.class);
			ResourceInstanceId = sdr.Data.Id;
			logInfo("Resource Instance created - "+resourceInstanceName);
		}catch(Exception e) {
			logError("Could not create resource instance "+resourceInstanceName+ " "+e.getMessage());
		}
		return ResourceInstanceId;
	}

	/**
	 * This method is used to link resource with location
	 * @author choubey_a
	 * @param data - All the required details in the request
	 * @return true if resource location linking is done
	 */

	public boolean linkResourceLocation(ResourcesData data) {
		try {
			apiUtils = new ApiUtils();
			Map<String, String> headers = new HashMap<String, String>();
			headers.put("Content-Type", "application/json");
			headers.put("Accept", "application/json, text/plain, /");
			headers.put("Authorization", "Bearer " + accessToken);

			String request = "{\r\n" + 
					"   \"ResourceTypeID\":\""+data.resourceTypeId+"\",\r\n" + 
					"   \"BusinessObjectID\":\""+data.resourceInstanceId+"\",\r\n" + 
					"   \"LinksWithResourceType\":[\r\n" + 
					"      {\r\n" + 
					"         \"Id\":\""+data.locationTypeId+"\",\r\n" + 
					"         \"Name\":\"Locations\",\r\n" + 
					"         \"Collection\":[\r\n" + 
					"            {\r\n" + 
					"               \"Id\":\""+data.locationInstanceId+"\",\r\n" + 
					"               \"Name\":\"Locations > "+data.locationCatName+" > "+data.locationInstname+"\"\r\n" + 
					"            }\r\n" + 
					"         ]\r\n" + 
					"      }\r\n" + 
					"   ],\r\n" + 
					"   \"RemovedLinksWithResoueceType\":[\r\n" + 
					"      {\r\n" + 
					"         \"Id\":\""+data.locationTypeId+"\",\r\n" + 
					"         \"Name\":\"Locations\",\r\n" + 
					"         \"Collection\":[\r\n" + 
					"            \r\n" + 
					"         ]\r\n" + 
					"      }\r\n" + 
					"   ]\r\n" + 
					"}";

			Response response = apiUtils.submitRequest(METHOD_TYPE.POST, request, headers,
					LocationResourceLinkUrl);
			Gson gson = new GsonBuilder().create();
			SimpleDataResponse sdr = gson.fromJson(response.asString(), SimpleDataResponse.class);
			if(sdr.Status) {
				logInfo("Location and resource Linked");
				return true;
			}
		}catch(Exception e) {
			logError("Could  not link location with resource - "+e.getMessage());			
		}
		return false;
	}

	/**
	 * This method is used to verify resource location linking
	 * @author choubey_a
	 * @param data - All the required details in the request
	 * @return true if resource location linking is verified
	 */

	public boolean verifyLinking(ResourcesData data) {
		try {
			apiUtils = new ApiUtils();
			Map<String, String> headers = new HashMap<String, String>();
			headers.put("Content-Type", "application/json");
			headers.put("Accept", "application/json, text/plain, /");
			headers.put("Authorization", "Bearer " + accessToken);

			String request = "{\"Type\":\""+data.resourceTypeId+"\",\"Node\":\""+data.resourceInstanceId+"\"}";

			Response response = apiUtils.submitRequest(METHOD_TYPE.POST, request, headers,
					RelatedLinks);
			Gson gson = new GsonBuilder().create();
			MultipleDataResponse sdr = gson.fromJson(response.asString(), MultipleDataResponse.class);

			for(int i=0; i < sdr.Data.size();i++) {				
				if(sdr.Data.get(0).Collection.get(i).ResourceType.Id.equals(data.resourceTypeId)) {
					if(sdr.Data.get(0).Collection.get(i).Resource.Id.equals(data.locationInstanceId)) {
						if(!(sdr.Data.get(0).Collection.get(i).Resource.Name.equals(data.locationInstname))) {
							logError("Location is not linked with the resource");
							return false;
						}
					}
				}
			}

			logInfo("Location is linked with the resource");
			return true;
		}catch(Exception e) {
			logError("Could not verify linking - "+e.getMessage());
		}
		return false;
	}

	/**
	 * This method is used to link item and supplier resource
	 * @author choubey_a
	 * @param data - All the required details in the request
	 * @return true if supplier item resource is linked
	 */

	public boolean ItemSupplierlink(ResourcesData data) {
		try {
			apiUtils = new ApiUtils();
			Map<String, String> headers = new HashMap<String, String>();
			headers.put("Content-Type", "application/json");
			headers.put("Accept", "application/json, text/plain, /");
			headers.put("Authorization", "Bearer " + accessToken);

			String request = "{\"ResourceTypeID\":\""+data.itemTypeId+"\","
					+ "\"BusinessObjectID\":\""+data.itemInstanceId+"\","
					+ "\"LinksWithResourceType\":[{\"Id\":\""+data.suppTypeId+"\","
					+ "\"Name\":\"Suppliers\",\"Collection\":[{\"Id\":\""+data.supplierInstanceId+"\","
					+ "\"Name\":\""+data.supplierInstanceName+"\"}]}],"
					+ "\"RemovedLinksWithResoueceType\":[{\"Id\":\""+data.suppTypeId+"\","
					+ "\"Name\":\"Suppliers\",\"Collection\":[]}]}";

			Response response = apiUtils.submitRequest(METHOD_TYPE.POST, request, headers,
					SaveLinks);
			Gson gson = new GsonBuilder().create();
			SimpleDataResponse1 sdr = gson.fromJson(response.asString(), SimpleDataResponse1.class);

			if(!sdr.Status) {
				return false;
			}
			logInfo("Location is linked with the resource");
			return true;
		}catch(Exception e) {
			logError("Could not verify linking - "+e.getMessage());
		}
		return false;
	}

	public class MultipleDataResponse {		
		public List<Data> Data;
		public boolean Status;
		public Data Id;
		public Data DisplayName;
		public Data data;
	}

	public class SingleDataResponse{
		public String Id;
		public Data Data;
		public boolean Status;

	}

	public class SimpleDataResponse{
		public String Id;
		public String Data;
		public boolean Status;
	}

	public class SimpleDataResponse1{
		public boolean Status;
	}


	public class Data {

		public boolean IsEnable;
		public String Id;
		public String Name;
		public String Type;
		public String FieldName;
		public String FieldId;
		public List<Collection> Collection;
	}

	public class Collection {

		public ResourceType ResourceType;
		public Resource Resource;

	}

	public class ResourceType{
		public String Id;
	}

	public class Resource{
		public String Id;
		public String Name;
	}

	public class FIELD_DETAILS {
		public static final String NAME = "SCSName";
		public static final String MODIFIED_BY = "SCSModifiedBy";
		public static final String LAST_MODIFIED = "SCSLastModified";
		public static final String STATUS = "SCSStatus";
	}

	public static class ResourcesData {

		public String resourceTypeId;
		public String locationTypeId;
		public String locationInstanceId;
		public String resourceInstanceId;
		public String locationCatName;
		public String locationInstname;
		public String supplierInstanceId;
		public String supplierInstanceName;
		public String suppTypeId;
		public String itemTypeId;
		public String itemInstanceId;
	}

}
