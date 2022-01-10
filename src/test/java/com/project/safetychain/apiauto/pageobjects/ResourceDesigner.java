package com.project.safetychain.apiauto.pageobjects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.project.safetychain.api.models.support.Support.RESOURCE_TYPES;
import com.project.utilities.ApiUtils;
import com.project.utilities.ApiUtils.METHOD_TYPE;

import io.restassured.response.Response;

public class ResourceDesigner extends Auth {

	public String ResourceTypeUrl = baseURI + ResourceDesignerUrls.RESOURCE_TYPE_URL;
	public String AddResourceCatUrl = baseURI + ResourceDesignerUrls.ADD_RESOURCE_CAT_URL;
	public String ResourceCatFieldsUrl = baseURI + ResourceDesignerUrls.RESOURCE_CAT_FIELDS_URL;
	public String ResourceCatFieldsValUrl = baseURI + ResourceDesignerUrls.RESOURCE_CAT_FIELDS_VAL_URL;
	public String ResourceFieldDetailsUrl = baseURI + ResourceDesignerUrls.RESOURCE_FIELD_DETAILS_URL;
	public String UserViewUrlUrl = baseURI + ResourceDesignerUrls.USER_VIEW_URL;

	ApiUtils au = new ApiUtils();
	Gson gson = new GsonBuilder().create();

	/**
	 * This method is get and set the IDs of Categories .
	 * 
	 * @author hingorani_a
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

	public List<String> createResFieldsJson(String resourceType) {
		String fieldDetail = null, resDesignJson = null, resFieldDetsJson = null;
		List<String> listOfEnabledFields = new ArrayList<String>();
		List<String> enabledFieldDets = new ArrayList<String>();
		Response resFields = null;

		try {
			Map<String, String> headers = new HashMap<String, String>();
			headers.put("Content-Type", "application/json");
			headers.put("Accept", "application/json, text/plain, /");
			headers.put("Authorization", "Bearer " + accessToken);

			switch (resourceType) {
			case RESOURCE_TYPES.CUSTOMERS:
				resDesignJson = "{\r\n" + "    \"Id\": \"" + customersCatID + "\"\r\n" + "}";
				break;
			case RESOURCE_TYPES.EQUIPMENT:
				resDesignJson = "{\r\n" + "    \"Id\": \"" + equipmentCatID + "\"\r\n" + "}";
				break;
			case RESOURCE_TYPES.ITEMS:
				resDesignJson = "{\r\n" + "    \"Id\": \"" + itemsCatID + "\"\r\n" + "}";
				break;
			case RESOURCE_TYPES.LOCATION:
				resDesignJson = "{\r\n" + "    \"Id\": \"" + locationCatID + "\"\r\n" + "}";
				break;
			case RESOURCE_TYPES.SUPPLIERS:
				resDesignJson = "{\r\n" + "    \"Id\": \"" + suppliersCatID + "\"\r\n" + "}";
				break;
			default:
				logError("Failed to set Json value  : " + resDesignJson);
				break;
			}

			// Get Resource Designer fields
			Response resDesign = au.submitRequest(METHOD_TYPE.POST, resDesignJson, headers, ResourceCatFieldsUrl);
			MultipleDataResponse mdr = gson.fromJson(resDesign.asString(), MultipleDataResponse.class);
			for (Data data : mdr.Data) {
				if (data.IsEnable) {
					listOfEnabledFields.add(data.Field.Id);
				}
			}

			for (String fieldId : listOfEnabledFields) {
				switch (resourceType) {
				case RESOURCE_TYPES.CUSTOMERS:
					resFieldDetsJson = "{\r\n" + "    \"Id\": \"" + customersCatID + "\",\r\n" + "    \"FieldId\": \""
							+ fieldId + "\"\r\n" + "}";
					break;
				case RESOURCE_TYPES.EQUIPMENT:
					resFieldDetsJson = "{\r\n" + "    \"Id\": \"" + equipmentCatID + "\",\r\n" + "    \"FieldId\": \""
							+ fieldId + "\"\r\n" + "}";
					break;
				case RESOURCE_TYPES.ITEMS:
					resFieldDetsJson = "{\r\n" + "    \"Id\": \"" + itemsCatID + "\",\r\n" + "    \"FieldId\": \""
							+ fieldId + "\"\r\n" + "}";
					break;
				case RESOURCE_TYPES.LOCATION:
					resFieldDetsJson = "{\r\n" + "    \"Id\": \"" + locationCatID + "\",\r\n" + "    \"FieldId\": \""
							+ fieldId + "\"\r\n" + "}";
					break;
				case RESOURCE_TYPES.SUPPLIERS:
					resFieldDetsJson = "{\r\n" + "    \"Id\": \"" + suppliersCatID + "\",\r\n" + "    \"FieldId\": \""
							+ fieldId + "\"\r\n" + "}";
					break;
				default:
					logError("Failed to set Json value  : " + resFieldDetsJson);
					break;
				}

				resFields = au.submitRequest(METHOD_TYPE.POST, resFieldDetsJson, headers, ResourceCatFieldsValUrl);
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

	public String updateResFieldsJson(List<String> listOfEnabledFields, String instanceName, String number,
			String text) {
		String fieldDets = "", appendFieldDets = null;

		try {
			for (int i = 0; i < listOfEnabledFields.size(); i++) {

				String[] details = listOfEnabledFields.get(i).split("\\|");
				if (i != (listOfEnabledFields.size() - 1)) {
					if (details[2].equalsIgnoreCase("SingleSelect")) {
						appendFieldDets = "        {\r\n" + "            \"FieldId\": \"" + details[0] + "\",\r\n"
								+ "            \"Value\": {\r\n" + "                \"Name\": \"" + details[3]
								+ "\"\r\n" + "            },\r\n" + "            \"Type\": \"" + details[2] + "\",\r\n"
								+ "            \"FieldName\": \"" + details[1] + "\"\r\n" + "        },\r\n";
					} else if (details[2].equalsIgnoreCase("Textbox")) {
						if (details[1].equalsIgnoreCase("SCSName")) {
							appendFieldDets = "        {\r\n" + "            \"FieldId\": \"" + details[0] + "\",\r\n"
									+ "            \"Value\": \"" + instanceName + "\",\r\n"
									+ "            \"Type\": \"" + details[2] + "\",\r\n"
									+ "            \"FieldName\": \"" + details[1] + "\"\r\n" + "        },\r\n";
						} else {
							appendFieldDets = "        {\r\n" + "            \"FieldId\": \"" + details[0] + "\",\r\n"
									+ "            \"Value\": \"" + text + "\",\r\n" + "            \"Type\": \""
									+ details[2] + "\",\r\n" + "            \"FieldName\": \"" + details[1] + "\"\r\n"
									+ "        },\r\n";
						}
					} else if (details[2].equalsIgnoreCase("Numeric")) {
						appendFieldDets = "        {\r\n" + "            \"FieldId\": \"" + details[0] + "\",\r\n"
								+ "            \"Value\": \"" + number + "\",\r\n" + "            \"Type\": \""
								+ details[2] + "\",\r\n" + "            \"FieldName\": \"" + details[1] + "\"\r\n"
								+ "        },\r\n";
					}
				} else {
					if (details[2].equalsIgnoreCase("SingleSelect")) {
						appendFieldDets = "        {\r\n" + "            \"FieldId\": \"" + details[0] + "\",\r\n"
								+ "            \"Value\": {\r\n" + "                \"Name\": \"" + details[3]
								+ "\"\r\n" + "            },\r\n" + "            \"Type\": \"" + details[2] + "\",\r\n"
								+ "            \"FieldName\": \"" + details[1] + "\"\r\n" + "        }\r\n";
					} else if (details[2].equalsIgnoreCase("Textbox")) {
						if (details[1].equalsIgnoreCase("SCSName")) {
							appendFieldDets = "        {\r\n" + "            \"FieldId\": \"" + details[0] + "\",\r\n"
									+ "            \"Value\": \"" + instanceName + "\",\r\n"
									+ "            \"Type\": \"" + details[2] + "\",\r\n"
									+ "            \"FieldName\": \"" + details[1] + "\"\r\n" + "        }\r\n";
						} else {
							appendFieldDets = "        {\r\n" + "            \"FieldId\": \"" + details[0] + "\",\r\n"
									+ "            \"Value\": \"" + text + "\",\r\n" + "            \"Type\": \""
									+ details[2] + "\",\r\n" + "            \"FieldName\": \"" + details[1] + "\"\r\n"
									+ "        }\r\n";
						}
					} else if (details[2].equalsIgnoreCase("Numeric")) {
						appendFieldDets = "        {\r\n" + "            \"FieldId\": \"" + details[0] + "\",\r\n"
								+ "            \"Value\": \"" + number + "\",\r\n" + "            \"Type\": \""
								+ details[2] + "\",\r\n" + "            \"FieldName\": \"" + details[1] + "\"\r\n"
								+ "        }\r\n";
					}
				}

				fieldDets = fieldDets + appendFieldDets;
			}
			return fieldDets;
		} catch (Exception e) {
			logError("Failed to set resource IDs for Categories - " + e.getMessage());
			return fieldDets;
		}
	}

	public String getResourceFieldId1(String fieldName, String resourceCatID) {
		// apiUtils = new ApiUtils();
		String FieldId = null;
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("Accept", "application/json, text/plain, /");
		headers.put("Authorization", "Bearer " + accessToken);

		String linkResourceJson = "{\"Id\":\"" + resourceCatID + "\"}";
		Response response = au.submitRequest(METHOD_TYPE.POST, linkResourceJson, headers, ResourceFieldDetailsUrl);
		// Gson gson = new GsonBuilder().create();
		logInfo("mdr.data");
		MultipleDataResponse mdr = gson.fromJson(response.asString(), MultipleDataResponse.class);

		System.out.println(" mdr.Data.size()" + mdr.Data.size());
		System.out.println(" mdr.Data.size()" + mdr.Data.size());
		for (int i = 0; i < mdr.Data.size(); i++) {
			// System.out.println(" mdr.Data.size()" + mdr.Data.size());
			if (mdr.Data.get(i).FieldName.equalsIgnoreCase(fieldName)) {
				FieldId = mdr.Data.get(i).Field.Id.toString();
				return FieldId;
			}

		}
		return FieldId;

	}

	public String getResourceFieldId(String fieldName, String resourceCatID,String baseTypeId) {
		ApiUtils apiUtils = new ApiUtils();
		String FieldId = null;
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("Accept", "application/json, text/plain, /");
		headers.put("Authorization", "Bearer " + accessToken);
		Gson gson = new GsonBuilder().create();

		String linkResourceJson = "{\"ID\":\"bdb4ec1d-e028-4e7f-86d5-34424abdef63\",\"BasetypeId\":\""+baseTypeId+"\",\"ResourceTypeID\":\""+resourceCatID+"\"}";
		Response response = apiUtils.submitRequest(METHOD_TYPE.POST, linkResourceJson, headers, ResourceFieldDetailsUrl);
		
		logInfo("mdr.data");
		MultipleDataResponse multipleDataResponse = gson.fromJson(response.asString(), MultipleDataResponse.class);

		System.out.println(" mdr.Data.size()" + multipleDataResponse.Data.size());	
		for (int i = 0; i < multipleDataResponse.Data.size(); i++) {		
			if (multipleDataResponse.Data.get(i).Definition.FieldName.equalsIgnoreCase(fieldName)) {
				FieldId = multipleDataResponse.Data.get(i).Definition.Id.toString();
				return FieldId;
			}

		}
		return FieldId;

	}

	public String getUserFieldId(String searchString) {
		// apiUtils = new ApiUtils();
		String FieldId = null;
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("Accept", "application/json, text/plain, /");
		headers.put("Authorization", "Bearer " + accessToken);

		String linkResourceJson = "{\r\n" + "   \"take\":100,\r\n" + "   \"skip\":0,\r\n" + "   \"page\":1,\r\n"
				+ "   \"pageSize\":100,\r\n" + "   \"Parameters\":{\r\n" + "      \"SearchString\":\"" + searchString
				+ "\",\r\n" + "      \"Columns\":[\r\n" + "         \"check_row\",\r\n" + "         \"username\",\r\n"
				+ "         \"firstname\",\r\n" + "         \"lastname\"\r\n" + "      ],\r\n"
				+ "      \"IsHideDeactiveUser\":true,\r\n" + "      \"FirstRowId\":null,\r\n"
				+ "      \"LastRowId\":null\r\n" + "   },\r\n" + "   \"FirstRowId\":null,\r\n"
				+ "   \"LastRowId\":null\r\n" + "}";
		Response response = au.submitRequest(METHOD_TYPE.POST, linkResourceJson, headers, UserViewUrlUrl);
		Gson gson = new GsonBuilder().create();

		SingleDataResponse sdr = gson.fromJson(response.asString(), SingleDataResponse.class);
		System.out.println(" mdr.Data.size()" + sdr.Data.Rows.size());
		for (int i = 0; i < sdr.Data.Rows.size(); i++) {
			System.out.println("sdr.Data.Row.size()" + sdr.Data.Rows.size());
			if (sdr.Data.Rows.get(i).Username.equalsIgnoreCase(searchString)) {
				FieldId = sdr.Data.Rows.get(i).Id;
				return FieldId;
			}
		}
		return FieldId;

	}

	public class Data {
		public boolean IsEnable;
		public String Id;
		public String ID;
		public String Name;
		public Field Field;
		public List<Fields> Fields;
		public Definition Definition;
		public List<Rows> Rows;
		public String FieldName;
		public String Type;
		public List<DataSource> DataSource;
		public int Count;

		public List<Forms> Forms;
		// ChartBuilderFlow
		public ChartType ChartType;
		// Task Scheduler
		public Object Value;
		public String IntervalFormat;
		public String ExpirationIntervalId;
		//getFormDefinition
		
		public List<Resources> Resources;
		public List<FormDetails> FormDetails;
		public String FormId;
		public List<Locations> Locations;
		
		
		public RecordData RecordData;
		

	}
	
	public class RecordData {
		public List<FormDetails> FormDetails;
		//public String Name;
	}
	
	
	public class Field {
		public String Id;
		public String Name;
	}
	
	public class Locations {
		public String Id;
		public String Name;
	}

//	public class Fields {
//		public String ShortName;
//		public String Name;
//	}

	public class Definition {
		public String FieldName;
		public String Id;
	}
	
	public class Resources {
		public String Id;
		public String Name;
	}


	public class Rows {
		public String Username;
		public String LastName;
		public String FirstName;
		public String Id;
	}

	public class DataSource {
		public String Name;
	}

	public class Forms {

		public FormData FormData;

	}

	public  class FormData {

		public FormDetails FormDetails;

	}

	public static class FormDetails {

		public List<Fields> Fields;
		public String Type;
		public String Value;
		public String ControlType;
		public String DependencyRule;
		public String ExpressionRule;
		public String FailsForm;
		public List<Options> Options;
		public String AllowCorrection;
		public String PredefinedCorrections;
		public String ShowIsResolved;
		public String Configuration;
		public String IsCompliant;
		public String DefaultCompliance;
		public String IncludeInExport;
		public String DataSourceId;
		
		public String DataSourceName;
		public String DataSourceType;
		public String RepeatField;
		
		public String ElementType;
		public String IsRequired;
		public String AllowComments;
		public String Comment;
		public String AllowAttachments;
		public String Attachments;
		public String ShowOnCOA;		
		public String ShowHint;
		public String Hint;
		public String ShowPointsPossible;		
		public String ShowPointsEarned;
		public String QuestionWeight;
		public String Order;		
		public String Repeat;
		public String RepeatIds;
		public String GroupID;	
		
		public String ShowField;
		public String FieldBankId;
		public String ParentId;		
		public String Default;
		public String Id;
		public String Name;
		public String ShortName;


	}
	
	public static class Options {

		public String Id;
		public String Name;
		public String Value;
		public String Code;
		
	}

	public static class Fields {

		public String Type;
		public String ElementType;
		public String ShortName;
		public String Id;
		public String Name;
		public List<Fields> Fields;

	}

	public class ChartType {
		public String Name;
		public String Id;

	}

}
