package com.project.safetychain.apiproject.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.project.safetychain.api.models.support.Support.RESOURCE_TYPES;
import com.project.safetychain.apiproject.models.Auth;
import com.project.utilities.ApiUtils;
import com.project.utilities.ApiUtils.METHOD_TYPE;

import io.restassured.response.Response;

public class ResourceDesigner extends Auth{

	public String GetResourceTypesUrl = baseURI + ResourceDesignerURLs.RESOURCE_TYPES_URL;
	public String ResourceDesignerFieldsUrl = baseURI + ResourceDesignerURLs.RESOURCE_DESIGNER_FIELDS_URL;
	public String AddNewCatUrl = baseURI + ResourceDesignerURLs.ADD_NEW_CAT_URL;
	public String GetFieldValue = baseURI + ResourceDesignerURLs.GET_FIELD_VALUE_URL;
	public String EnableDisableResourceType = baseURI + ResourceDesignerURLs.ENABLE_DISABLE_RESOURCE_TYPE_URL;
	public String EnableDisableField = baseURI + ResourceDesignerURLs.ENABLE_DISABLE_FIELD_URL;
	public String EditResourceCategory =  baseURI + ResourceDesignerURLs.EDIT_CATEGORY_DETAILS_URL;
	public String DeleteResourceCategory =  baseURI + ResourceDesignerURLs.DELETE_CATEGORY_URL;

	ApiUtils apiUtils = new ApiUtils();
	Gson gson = new GsonBuilder().create();
	String resourceTypeId = null;
	String EnabledFieldId = null , EnabledFieldName = null;
	String customersCatID, equipmentCatID,itemsCatID, locationCatID,suppliersCatID,platCatID ;
	String FieldId = null , FieldName = null;


	/**
	 * This method is used to get resource types ID
	 * @author choubey_a
	 * @param resourcetypes - the name of the resource get the ID
	 * @return resource type Id
	 */
	public String getResourceTypeId(String resourceType) {

		try {
			apiUtils = new ApiUtils();
			Gson gson = new GsonBuilder().create();
			Map<String, String> headers = new HashMap<String, String>();
			headers.put("Content-Type", "application/json");
			headers.put("Accept", "application/json, text/plain, /");
			headers.put("Authorization", "Bearer " + accessToken);

			Response response = apiUtils.submitRequest(METHOD_TYPE.GET, null, headers,
					GetResourceTypesUrl);

			MultipleDataResponse mdr = gson.fromJson(response.asString(),
					MultipleDataResponse.class);

			String dataId = null;

			for (Data data : mdr.Data) {								
				if(data.Name.equals (resourceType)) {
					dataId = data.Id;
					break;
				}
			}
			switch (resourceType) {
			case ResourceTypes.CUSTOMERS:
				customersCatID = dataId;
				resourceTypeId = customersCatID;
				break;
			case ResourceTypes.EQUIPMENT:
				equipmentCatID = dataId;
				resourceTypeId = equipmentCatID;
				break;
			case ResourceTypes.ITEMS:
				itemsCatID = dataId;
				resourceTypeId = itemsCatID;
				break;
			case ResourceTypes.LOCATIONS:
				locationCatID =dataId;
				resourceTypeId = locationCatID;
				break;
			case ResourceTypes.SUPPLIERS:
				suppliersCatID = dataId;
				resourceTypeId = suppliersCatID;
				break;
			case RESOURCE_TYPES.PLANT_RESOURCE_TYPE:
				plantCatID = dataId;
				resourceTypeId = plantCatID;
				break;
			default:
				logError("Resource Type : " + resourceType + " is incorrect");
			}			
			logInfo("Resource Type : " + resourceType + " ID : " + dataId);
		}catch(Exception e) {
			logError ("Failed to get for Resource Type : " + resourceType +e.getMessage());
		}
		return resourceTypeId;
	}


	/**
	 * This method is used to get resource types Field Ids
	 * @author choubey_a
	 * @param ID of the resource for which field ID should be extracted
	 * @return resource type field Ids
	 */

	public List<String> getResourceDesignerFields(String resourcetypeId) {

		List<String> listOfEnabledFields = new ArrayList<String>();


		try {
			apiUtils = new ApiUtils();
			Gson gson = new GsonBuilder().create();
			Map<String, String> headers = new HashMap<String, String>();
			headers.put("Content-Type", "application/json");
			headers.put("Accept", "application/json, text/plain, /");
			headers.put("Authorization", "Bearer " + accessToken);

			String request = "{\"Id\":\""+resourceTypeId+"\"}";

			Response response = apiUtils.submitRequest(METHOD_TYPE.POST, request, headers,
					ResourceDesignerFieldsUrl);

			MultipleDataResponse mdr = gson.fromJson(response.asString(),
					MultipleDataResponse.class);

			int count = mdr.Data.size();
			System.out.println(count);
			for (int i=0; i < mdr.Data.size(); i++) {
				FieldId = mdr.Data.get(i).Field.Id;
				listOfEnabledFields.add(FieldId);
				FieldName =  mdr.Data.get(i).Field.Name;					
				logInfo("Field Name = "+FieldName+ " and Field Id = "+FieldId);						
			}

		}catch(Exception e) {
			logError("Failed to get Field Name of resourceTypeId "+resourceTypeId+ " - "+e.getMessage());
		}
		return listOfEnabledFields;
	}

	/**
	 * This method is used to get resource types Field Ids of the enabled resource types
	 * @author choubey_a
	 * @param ID of the resource for which field ID should be extracted
	 * @return enabled resource type field Ids
	 */

	public List<String> getEnabledResourceTypeFields(String resourcetypeId) {

		List<String> listOfEnabledFields = new ArrayList<String>();

		try {
			apiUtils = new ApiUtils();
			Gson gson = new GsonBuilder().create();
			Map<String, String> headers = new HashMap<String, String>();
			headers.put("Content-Type", "application/json");
			headers.put("Accept", "application/json, text/plain, /");
			headers.put("Authorization", "Bearer " + accessToken);

			String request = "{\"Id\":\""+resourceTypeId+"\"}";

			Response response = apiUtils.submitRequest(METHOD_TYPE.POST, request, headers,
					ResourceDesignerFieldsUrl);

			MultipleDataResponse mdr = gson.fromJson(response.asString(),
					MultipleDataResponse.class);

			for (Data data : mdr.Data) {
				if (data.IsEnable) {
					listOfEnabledFields.add(data.Field.Id);
				}
			}
		}catch(Exception e) {
			logError("Failed to get Field Name of resourceTypeId "+resourceTypeId+ " - "+e.getMessage());
		}
		return listOfEnabledFields;
	}

	/**
	 * This method is used to get resource types Field Values
	 * @author choubey_a
	 * @param ID of the resource types field and resourceType Name
	 * @return resource type field Values
	 */

	public List<String> getFieldValue(List<String> EnabledFieldId, String resourceType) {

		String request = null,fieldDetail = null;
		List<String> enabledFieldValues = new ArrayList<String>();
		try {

			apiUtils = new ApiUtils();
			Gson gson = new GsonBuilder().create();
			Map<String, String> headers = new HashMap<String, String>();
			headers.put("Content-Type", "application/json");
			headers.put("Accept", "application/json, text/plain, /");
			headers.put("Authorization", "Bearer " + accessToken);


			for(String fieldId : EnabledFieldId) {
				switch (resourceType) {
				case ResourceTypes.CUSTOMERS:
					customersCatID = getResourceTypeId(ResourceTypes.CUSTOMERS);
					request  = "{\r\n" + "    \"Id\": \"" + customersCatID + "\",\r\n" + "    \"FieldId\": \""
							+ fieldId + "\"\r\n" + "}";
					break;
				case ResourceTypes.EQUIPMENT:
					equipmentCatID = getResourceTypeId(ResourceTypes.EQUIPMENT);
					request = "{\r\n" + "    \"Id\": \"" + equipmentCatID + "\",\r\n" + "    \"FieldId\": \""
							+ fieldId + "\"\r\n" + "}";
					break;
				case ResourceTypes.ITEMS:
					itemsCatID = getResourceTypeId(ResourceTypes.ITEMS);
					request = "{\r\n" + "    \"Id\": \"" + itemsCatID + "\",\r\n" + "    \"FieldId\": \""
							+ fieldId + "\"\r\n" + "}";
					break;
				case ResourceTypes.LOCATIONS:
					locationCatID = getResourceTypeId(ResourceTypes.LOCATIONS);
					request = "{\r\n" + "    \"Id\": \"" + locationCatID + "\",\r\n" + "    \"FieldId\": \""
							+ fieldId + "\"\r\n" + "}";
					break;
				case ResourceTypes.SUPPLIERS:
					suppliersCatID = getResourceTypeId(ResourceTypes.SUPPLIERS);
					request = "{\r\n" + "    \"Id\": \"" + suppliersCatID + "\",\r\n" + "    \"FieldId\": \""
							+ fieldId + "\"\r\n" + "}";
					break;
				default:
					logError("Failed to set Json value  : " + request);
					break;
				}

				Response response = apiUtils.submitRequest(METHOD_TYPE.POST, request, headers, GetFieldValue);
				MultipleDataResponse mdr = gson.fromJson(response.asString(), MultipleDataResponse.class);
				if (mdr.Data.get(0).Type.equalsIgnoreCase("SingleSelect"))
					fieldDetail = fieldId + "|" + mdr.Data.get(0).FieldName + "|" + mdr.Data.get(0).Type + "|"
							+ mdr.Data.get(0).DataSource.get(0).Name;
				else
					fieldDetail = fieldId + "|" + mdr.Data.get(0).FieldName + "|" + mdr.Data.get(0).Type;
				enabledFieldValues.add(fieldDetail);
			}

			logInfo("Added details of enabled fields : " + enabledFieldValues);			

		}catch(Exception e) {
			logError("Not able to add the details of the fields-"+e.getMessage());
		}
		return enabledFieldValues;
	}

	/**
	 * This method is used to create resource category
	 * @author choubey_a
	 * @param baseTyId - ID of the first category ( for the first one it will be same as resource Type Id)
	 * @param resourceTypeId - Id of the resource Type (Supplier/Customer/Location/Equipments/Items)
	 * @return the new created category ID
	 */


	public String createResourceCategory (String baseTypeId, String resourceTypeId, String resourceCatName) {
		String categoryId = null;
		try {
			apiUtils = new ApiUtils();
			Gson gson = new GsonBuilder().create();
			Map<String, String> headers = new HashMap<String, String>();
			headers.put("Content-Type", "application/json");
			headers.put("Accept", "application/json, text/plain, /");
			headers.put("Authorization", "Bearer " + accessToken);

			String request = "{\"BaseTypeID\":\""+baseTypeId+"\",\"Name\":\""+resourceCatName+"\","
					+ "\"ID\":null,\"ResourceTypeID\":\""+resourceTypeId+"\"}";

			Response response = apiUtils.submitRequest(METHOD_TYPE.POST, request, headers, AddNewCatUrl);

			SingleDataResponse sdr = gson.fromJson(response.asString(), SingleDataResponse.class);

			categoryId = sdr.Data.Id;

			logInfo("Category created");
		}catch(Exception e) {
			logError("Not able to create category -"+e.getMessage());
		}
		return categoryId;
	}

	/**
	 * This method is used to enable/disable resource Type (Supplier/Customer/Location/Equipments/Items)
	 * @author choubey_a
	 * @param enable (true if want to enable and vice versa)
	 * @param resourceTypeId - Id of the resource Type (Supplier/Customer/Location/Equipments/Items)
	 * @return true if resourceType is enabled or disbaled
	 */


	public boolean enableDisableResourceType(String resourceTypeId, boolean enable) {

		try {
			apiUtils = new ApiUtils();
			Gson gson = new GsonBuilder().create();
			Map<String, String> headers = new HashMap<String, String>();
			headers.put("Content-Type", "application/json");
			headers.put("Accept", "application/json, text/plain, /");
			headers.put("Authorization", "Bearer " + accessToken);

			String request = "{\"Id\":\""+resourceTypeId+"\",\"IsEnable\":"+enable+"}";

			Response response = apiUtils.submitRequest(METHOD_TYPE.POST, request, headers, EnableDisableResourceType);

			SimpleDataResponse sdr = gson.fromJson(response.asString(), SimpleDataResponse.class);

			if(sdr.Status == true) {
				if(enable) {
					logInfo("Resource Type enabled");
				}
				else {
					logInfo("Resource Type disabled");
				}
			}
			return true;
		}catch(Exception e) {
			logInfo("Could not enable/disable Resource Type- "+e.getMessage());
			return false;
		}

	}

	/**
	 * This method is used to enable/disable Field of Resource Type
	 * @author choubey_a
	 * @param enable (true if want to enable and vice versa)
	 * @param resourceTypeId - Id of the resource Type (Supplier/Customer/Location/Equipments/Items)
	 * @param fieldId - Id of the field that has to be disabled/enabled
	 * @return true if resource Type Field is enabled or disbaled
	 */

	public boolean enableDisableField(String resourceTypeId, String fieldId, boolean enable) {

		try {
			apiUtils = new ApiUtils();
			Gson gson = new GsonBuilder().create();
			Map<String, String> headers = new HashMap<String, String>();
			headers.put("Content-Type", "application/json");
			headers.put("Accept", "application/json, text/plain, /");
			headers.put("Authorization", "Bearer " + accessToken);

			String request = "{\"Id\":\""+resourceTypeId+"\",\"FieldId\":\""+fieldId+"\",\"IsEnable\":\""+enable+"\"}";

			Response response = apiUtils.submitRequest(METHOD_TYPE.POST, request, headers, EnableDisableField);

			SimpleDataResponse sdr = gson.fromJson(response.asString(), SimpleDataResponse.class);

			if(sdr.Status == true) {
				if(enable) {
					logInfo("Field Type enabled");
				}
				else {
					logInfo("Field Type disabled");
				}
			}else {
				return false;
			}
			return true;
		}catch(Exception e) {
			logInfo("Could not enable/disable Resource field -"+e.getMessage());
			return false;
		}

	}

	/**
	 * This method is used to edit resource Category by changing the name
	 * @author choubey_a
	 * @param baseTyId - ID of the first category ( for the first one it will be same as resource Type Id)
	 * @param categoryId - Id of the category to be edited
	 * @param resourceCatName - the new name that will be provided to the category
	 * @param resourceTypeId - Id of the resource Type (Supplier/Customer/Location/Equipments/Items)
	 * @return true if category name is edited
	 */

	public boolean editResourceCategory(String baseTypeId, String resourceTypeId, String categoryId,String resourceCatName) {

		try {
			apiUtils = new ApiUtils();
			Gson gson = new GsonBuilder().create();
			Map<String, String> headers = new HashMap<String, String>();
			headers.put("Content-Type", "application/json");
			headers.put("Accept", "application/json, text/plain, /");
			headers.put("Authorization", "Bearer " + accessToken);

			String request = "{\"BaseTypeID\":\""+baseTypeId+"\",\"Name\":\""+resourceCatName+"\","
					+ "\"ID\":\""+categoryId+"\",\"ResourceTypeID\":\""+resourceTypeId+"\"}";

			Response response = apiUtils.submitRequest(METHOD_TYPE.POST, request, headers, EditResourceCategory);

			SimpleDataResponse sdr = gson.fromJson(response.asString(), SimpleDataResponse.class);

			if(sdr.Data.Id == categoryId && sdr.Status == true) {
				logInfo("Category Edited");							
			}
			return true;
		}catch(Exception e) {
			logError("Could not edit the category -"+e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to delete resource Category
	 * @author choubey_a
	 * @param categoryId - Id of the category to be edited
	 * @param resourceTypeId - Id of the resource Type (Supplier/Customer/Location/Equipments/Items)
	 * @return true if category name is deleted
	 */

	public boolean deleteResourceCategory(String resourceTypeId, String categoryId) {

		try {
			apiUtils = new ApiUtils();
			Gson gson = new GsonBuilder().create();
			Map<String, String> headers = new HashMap<String, String>();
			headers.put("Content-Type", "application/json");
			headers.put("Accept", "application/json, text/plain, /");
			headers.put("Authorization", "Bearer " + accessToken);

			String request = "{\"data\":{\"Type\":\""+resourceTypeId+"\",\"Node\":\""+categoryId+"\"}}";

			Response response = apiUtils.submitRequest(METHOD_TYPE.POST, request, headers, DeleteResourceCategory);

			SimpleDataResponse sdr = gson.fromJson(response.asString(), SimpleDataResponse.class);

			if(sdr.Status == true) {
				logInfo("Category deleted");							
			}
			return true;
		}catch(Exception e) {
			logError("Could not delete the category - "+e.getMessage());
			return false;
		}
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

	}

	public class SimpleDataResponse{
		public String Id;
		public Data Data;
		public boolean Status;
	}

	public class Data {

		public boolean IsEnable;
		public String Id;
		public String Name;
		public List<String> ID;
		public String Type;
		public String FieldName;
		public List<DataSource> DataSource;
		public Links Links;
		public Tabs Tabs;
		public Field Field;


	}

	public class ResourceTypes {
		public static final String LOCATIONS = "Locations";
		public static final String CUSTOMERS = "Customers";
		public static final String EQUIPMENT = "Equipment";
		public static final String SUPPLIERS = "Suppliers";
		public static final String ITEMS = "Items";
		public static final String PLANT_RESOURCE_TYPE = "PlantResourceType";
	}


	public class DataSource {
		public String Name;
	}

	public class Links {
		public List<String> Id;
	}

	public class Tabs {
		public String Id;
	}


	public class Field {
		public String Id;
		public String Name;
	}
}



