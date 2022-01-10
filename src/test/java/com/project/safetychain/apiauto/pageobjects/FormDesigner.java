package com.project.safetychain.apiauto.pageobjects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang.StringEscapeUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.project.safetychain.apiauto.pageobjects.Auth;
import com.project.safetychain.apiauto.pageobjects.ResourceDesigner;
import com.project.safetychain.api.models.support.Support.RESOURCE_TYPES;
import com.project.utilities.ApiUtils;
import com.project.utilities.ApiUtils.METHOD_TYPE;

import io.restassured.response.Response;

public class FormDesigner extends Auth {

	public static String formName;
	public static String recordId;
	public static HashMap<String, String> fieldValues = new HashMap<String, String>();

	/** Web APIs */
	public String SaveFormDesignUrl = baseURI + FormDesignerUrls.SAVE_FORM_DESIGN_URL;
	public String AddFieldUrl = baseURI + FormDesignerUrls.ADD_FIELD_URL;
	public String SubmitFormUrl = baseURI + FormDesignerUrls.SUBMIT_FORM_URL;
	public String SubmitFormWebUrl = baseURI + FormDesignerUrls.SUBMIT_FORM_WEB_URL;
	public String getRecordUrl = baseURI + FormDesignerUrls.GET_RECORD_URL;

	public String ValidateReleaseUrl = baseURI + FormDesignerUrls.VALIDATE_RELEASE_URL;
	public String ReleaseFormUrl = baseURI + FormDesignerUrls.RELEASE_FORM_URL;
	public String GetFormsUrl = baseURI + FormDesignerUrls.GET_FORMS_URL;
	public String GetFormLocationUrl = baseURI + FormDesignerUrls.GET_FORM_LOCATION_URL;
	public String GetFormResourcesForLocUrl = baseURI + FormDesignerUrls.GET_FORM_RESOURCES_LOC_URL;
	public String GetFormDataUrl = baseURI + FormDesignerUrls.GET_FORM_DATA_URL;

	public String GetRecordDefinitionUrl = baseURI + FormDesignerUrls.GET_RECORD_DEFINITION_URL;
	public String VerificationUrl = baseURI + FormDesignerUrls.SAVE_FORMS_VERIFICATION_URL;

	public String GetLocationCategories = baseURI + FormDesignerUrls.GET_ROOT_LOCATIONS_CAT_URL;
	public String manageResourceCategories = baseURI + FormDesignerUrls.MANAGE_RESOURCE_CATEGORIES_URL;

	/** DPT APIs */
	public String DptSaveFormDesignUrl = baseURI + FormDesignerUrls.DPT_SAVE_FORM_DESIGN_URL;
	public String DptAddUpdateComplianceUrl = baseURI + FormDesignerUrls.DPT_ADD_UPDATE_FORM_COMPLIANCE_URL;
	public String DptGetFormComplianceUrl = baseURI + FormDesignerUrls.DPT_GET_FORM_COMPLIANCE_URL;
	public String DptGetFormDefinitionUrl = baseURI + FormDesignerUrls.DPT_GET_FORM_DEFINITION_URL;

	ApiUtils apiUtils = new ApiUtils();
	Gson gson = new GsonBuilder().create();
	Random rand = new Random();

	public static LinkedHashMap<String, String> shortNames = new LinkedHashMap<String, String>();
	public boolean isMatched = true;

	public String verifyIsFormCreated(String formName) {
		String getFormsRequest = null;
		String Data = "";

		apiUtils = new ApiUtils();
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("Accept", "application/json, text/plain, /");
		headers.put("Authorization", "Bearer " + accessToken);

		// level 2 try for Select One/Multiple and Aggregate
		Gson gson = new GsonBuilder().create();

		try {

			getFormsRequest = "  {\r\n" + "    \"Take\": 100,\r\n" + "    \"Skip\": \"\",\r\n" + "    \"Filter\": {\r\n"
					+ "      \"filters\": [\r\n" + "        {\r\n" + "         \"field\": \"FormName\",\r\n"
					+ "         \"operator\": \"equals\",\r\n" + "          \"value\": \"" + formName + "\"\r\n"
					+ "        }\r\n" + "       ]\r\n" + "    }\r\n" + "  }\r\n" + "";
			Response response = apiUtils.submitRequest(METHOD_TYPE.POST, getFormsRequest, headers, GetFormsUrl);
			MultipleDataResponse sdr = gson.fromJson(response.asString(), MultipleDataResponse.class);
			int count = sdr.Data.size();
			if (count > 0) {
				logInfo("A created form found for - " + formName);
				Data = sdr.Data.toString();

			} else {
				logInfo("Could not found form named - " + formName);
			}

			return Data;

		} catch (Exception e) {
			logError("Failed to get form Data - " + e.getMessage());
			return Data;
		}
	}

	public boolean verifyIsFormCreated(String resourceType, String formName, Map<String, String> headers) {
		String getFormsRequest = null;
		String resourceId = null;

		try {

			ResourceDesigner rd = new ResourceDesigner();
			if (!rd.setResourceCatIDs())
				return false;

			switch (resourceType) {
			case RESOURCE_TYPES.CUSTOMERS:
				resourceId = customersCatID;
				break;
			case RESOURCE_TYPES.EQUIPMENT:
				resourceId = equipmentCatID;
				break;
			case RESOURCE_TYPES.ITEMS:
				resourceId = itemsCatID;
				break;
			case RESOURCE_TYPES.SUPPLIERS:
				resourceId = suppliersCatID;
				break;
			default:
				logError("Failed to set for Resource Id for Resource Type : " + resourceType);
				return false;
			}

			getFormsRequest = "{\r\n" + "	\"take\": 100,\r\n" + "	\"skip\": 0,\r\n" + "	\"page\": 1,\r\n"
					+ "	\"pageSize\": 100,\r\n" + "	\"sort\": [{\r\n" + "		\"field\": \"ModifiedOn\",\r\n"
					+ "		\"dir\": \"desc\"\r\n" + "	}],\r\n" + "	\"filter\": {\r\n"
					+ "		\"logic\": \"and\",\r\n" + "		\"filters\": [{\r\n"
					+ "			\"field\": \"Name\",\r\n" + "			\"operator\": \"contains\",\r\n"
					+ "			\"value\": \"" + formName + "\"\r\n" + "		}]\r\n" + "	},\r\n"
					+ "	\"Parameters\": {\r\n" + "		\"Node\": {\r\n" + "			\"Type\": \"" + resourceId
					+ "\",\r\n" + "			\"Node\": \"" + resourceId + "\",\r\n" + "			\"IsParent\": true\r\n"
					+ "		},\r\n" + "		\"FromDate\": \"\",\r\n" + "		\"ToDate\": \"\",\r\n"
					+ "		\"Resource\": [],\r\n" + "		\"FormName\": [],\r\n" + "		\"FormType\": [],\r\n"
					+ "		\"Program\": [],\r\n" + "		\"ModifiedBy\": [],\r\n"
					+ "		\"FirstRowId\": null,\r\n" + "		\"LastRowId\": null\r\n" + "	},\r\n"
					+ "	\"FirstRowId\": null,\r\n" + "	\"LastRowId\": null\r\n" + "}";
			Response response = apiUtils.submitRequest(METHOD_TYPE.POST, getFormsRequest, headers, GetFormsUrl);
			SingleDataResponse sdr = gson.fromJson(response.asString(), SingleDataResponse.class);
			int count = sdr.Data.Count;
			if (count == 1) {
				logInfo("A created form found for - " + formName);
				return true;
			}
			if (count > 1) {
				logInfo("More than one created form found for - " + formName);
				return false;
			} else {
				logInfo("Could not verify form named - " + formName);
				return false;
			}

		} catch (Exception e) {
			logError("Failed to set resource IDs for Categories - " + e.getMessage());
			return false;
		}
	}

	public HashMap<String, String> getResourceName(String formName) {
		String getFormsRequest = null;

		HashMap<String, String> resourceMap = new HashMap<String, String>();

		apiUtils = new ApiUtils();
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("Accept", "application/json, text/plain, /");
		headers.put("Authorization", "Bearer " + accessToken);

		// level 2 try for Select One/Multiple and Aggregate
		Gson gson = new GsonBuilder().create();

		try {

			getFormsRequest = "  {\r\n" + "    \"Take\": 100,\r\n" + "    \"Skip\": \"\",\r\n" + "    \"Filter\": {\r\n"
					+ "      \"filters\": [\r\n" + "        {\r\n" + "         \"field\": \"FormName\",\r\n"
					+ "         \"operator\": \"equals\",\r\n" + "          \"value\": \"" + formName + "\"\r\n"
					+ "        }\r\n" + "       ]\r\n" + "    }\r\n" + "  }\r\n" + "";
			Response response = apiUtils.submitRequest(METHOD_TYPE.POST, getFormsRequest, headers, GetFormsUrl);
			MultipleDataResponse sdr = gson.fromJson(response.asString(), MultipleDataResponse.class);
			int count = sdr.Data.get(0).Resources.size();
			if (count > 0) {
				logInfo("A created form found for - " + formName);

				for (int i = 0; i < count; i++) {
					resourceMap.put(sdr.Data.get(0).Resources.get(i).Id, sdr.Data.get(0).Resources.get(i).Name);
				}

			} else {
				logInfo("Could not found form named - " + formName);
			}

			return resourceMap;

		} catch (Exception e) {
			logError("Failed to get form Data - " + e.getMessage());
			return resourceMap;
		}
	}

	public String getFormId_AlreadyCreated(String formName) {
		String getFormsRequest = null;
		String formId = "";

		apiUtils = new ApiUtils();
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("Accept", "application/json, text/plain, /");
		headers.put("Authorization", "Bearer " + accessToken);

		// level 2 try for Select One/Multiple and Aggregate
		Gson gson = new GsonBuilder().create();

		try {

			getFormsRequest = "  {\r\n" + "    \"Take\": 100,\r\n" + "    \"Skip\": \"\",\r\n" + "    \"Filter\": {\r\n"
					+ "      \"filters\": [\r\n" + "        {\r\n" + "         \"field\": \"FormName\",\r\n"
					+ "         \"operator\": \"equals\",\r\n" + "          \"value\": \"" + formName + "\"\r\n"
					+ "        }\r\n" + "       ]\r\n" + "    }\r\n" + "  }\r\n" + "";
			Response response = apiUtils.submitRequest(METHOD_TYPE.POST, getFormsRequest, headers, GetFormsUrl);
			MultipleDataResponse sdr = gson.fromJson(response.asString(), MultipleDataResponse.class);
			int count = sdr.Data.size();
			if (count > 0) {
				logInfo("A created form found for - " + formName);
				formId = sdr.Data.get(0).FormId;

			} else {
				logInfo("Could not found form named - " + formName);
			}

			return formId;

		} catch (Exception e) {
			logError("Failed to get form Data - " + e.getMessage());
			return formId;
		}
	}

	public HashMap<String, String> getFormLocation(String formId) {
		String getFormLocRequest = null;
		// String formId = "";
		HashMap<String, String> locMap = new HashMap<String, String>();
		apiUtils = new ApiUtils();
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("Accept", "application/json, text/plain, /");
		headers.put("Authorization", "Bearer " + accessToken);

		// level 2 try for Select One/Multiple and Aggregate
		Gson gson = new GsonBuilder().create();

		try {

			getFormLocRequest = "{\"Id\":\"" + formId + "\"}";
			Response response = apiUtils.submitRequest(METHOD_TYPE.POST, getFormLocRequest, headers,
					GetFormLocationUrl);
			SingleDataResponse sdr = gson.fromJson(response.asString(), SingleDataResponse.class);
			int count = sdr.Data.Locations.size();
			if (count > 0) {

				for (int i = 0; i < sdr.Data.Locations.size(); i++) {
					locMap.put(sdr.Data.Locations.get(i).Id, sdr.Data.Locations.get(i).Name);
				}

			}

			return locMap;

		} catch (Exception e) {
			logError("Failed to get location for the form provided in the request Data - " + e.getMessage());
			return locMap;
		}
	}

	public HashMap<String, String> getFormResourceForLocationMapped(String formId, String locId) {
		String getFormResForLocRequest = null;
		// String formId = "";
		HashMap<String, String> ResMap = new HashMap<String, String>();
		apiUtils = new ApiUtils();
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("Accept", "application/json, text/plain, /");
		headers.put("Authorization", "Bearer " + accessToken);

		// level 2 try for Select One/Multiple and Aggregate
		Gson gson = new GsonBuilder().create();

		try {

			getFormResForLocRequest = "{\r\n" + "    \"FormId\": \"" + formId + "\",\r\n" + "    \"LocationId\": \""
					+ locId + "\"\r\n" + "}";
			Response response = apiUtils.submitRequest(METHOD_TYPE.POST, getFormResForLocRequest, headers,
					GetFormResourcesForLocUrl);
			MultipleDataResponse sdr = gson.fromJson(response.asString(), MultipleDataResponse.class);
			int count = sdr.Data.size();
			if (count > 0) {

				for (int i = 0; i < sdr.Data.size(); i++) {
					ResMap.put(sdr.Data.get(i).Id, sdr.Data.get(i).Name);
				}

			}

			return ResMap;

		} catch (Exception e) {
			logError("Failed to get form Data - " + e.getMessage());
			return ResMap;
		}
	}

	public String getRecordDefiition(String formId, String resourceName) {
		String getRecordDefinitionReq = "";
		String recordDefinitionData = null;
        new HashMap<String, String>();

		apiUtils = new ApiUtils();
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("Accept", "application/json, text/plain, /");
		headers.put("Authorization", "Bearer " + accessToken);

		// level 2 try for Select One/Multiple and Aggregate
		new GsonBuilder().create();
		String name = "";
		try {

			getRecordDefinitionReq = "{\r\n" + "    \"FormId\": \"" + formId + "\",\r\n"
					+ "    \"Resource\": {     \r\n" + "        \"Name\": \"" + resourceName + "\"\r\n" + "    }\r\n"
					+ "}";
			Response response = apiUtils.submitRequest(METHOD_TYPE.POST, getRecordDefinitionReq, headers,
					GetFormDataUrl);

			recordDefinitionData = response.jsonPath().getJsonObject("Data.FormDetails").toString();

			// System.out.println(" UPdated 789 " + recordDefinitionData);

			JSONObject jsnobject = new JSONObject(response.asString());

			JSONObject jsnobject2 = jsnobject.getJSONObject("Data");

			// System.out.println(" jsnobject " +jsnobject);
			// System.out.println("jsnobject2 " +jsnobject2);
			JSONArray jsonArray = jsnobject2.getJSONArray("FormDetails");
			System.out.println("jsonArray " + jsonArray);

			String formDetails = "";
			for (int i = 0; i < jsonArray.length(); i++) {

				JSONObject object = jsonArray.getJSONObject(i);

				if (i < jsonArray.length()) {
	
					JSONObject data = provideInputValues(object);
					String ElementType = data.get("ElementType").toString();

					if (ElementType.equals("FieldGroup")) {
						JSONArray jsonarray = data.getJSONArray("Fields");
						String nameFG = data.get("Name").toString();
						nameFG = nameFG.replace("\"", "\\\"");
						String formDetails2 = "";

						for (int j = 0; j < jsonarray.length(); j++) {
							String formDetails1 = updatefieldValues(jsonarray.getJSONObject(j));

							if (j == 0) {

								formDetails2 = formDetails1;
							} else {
								formDetails2 = formDetails2 + "," + formDetails1;
							}

							// formDetails = formDetails + "," + formDetails1;
						}
						if (i == 0) {
							formDetails = " { \"ElementType\": \"FieldGroup\",\r\n" + "                \"Name\": \""
									+ nameFG + "\",\r\n" + "   \"Fields\": [" + formDetails2 + "] }";
						} else {

							formDetails = formDetails + "," + " { \"ElementType\": \"FieldGroup\",\r\n"
									+ "                \"Name\": \"" + nameFG + "\",\r\n" + "   \"Fields\": ["
									+ formDetails2 + "] }";
						}

					} else if (ElementType.equals("Section")) {
						JSONArray jsonarray = data.getJSONArray("SectionElements");
						String Name = data.get("Name").toString();
						name = name.replace("\"", "\\\"");
						String formDetailsFG = "";
						String formDetailsSec = "";
						Integer counter = 0;
						jsonarray.length();
						for (int j = 0; j < jsonarray.length(); j++) {

							ElementType = jsonarray.getJSONObject(j).get("ElementType").toString();
							if (ElementType.equals("FieldGroup")) {
								JSONArray jsonArrayFG = jsonarray.getJSONObject(j).getJSONArray("Fields");
								String nameFG = jsonarray.getJSONObject(j).getString("Name");
								nameFG = nameFG.replace("\"", "\\\"");
								String formDetails2 = "";
								for (int k = 0; k < jsonArrayFG.length(); k++) {
									String formDetails1 = updatefieldValues(jsonArrayFG.getJSONObject(k));
									if (k == 0) {

										formDetails2 = formDetails1;
									} else {
										formDetails2 = formDetails2 + "," + formDetails1;
									}
									// formDetails = formDetails + "," + formDetails1;
								}

								formDetailsFG = " { \"ElementType\": \"FieldGroup\",\r\n"
										+ "                \"Name\": \"" + nameFG + "\",\r\n" + "   \"Fields\": ["
										+ formDetails2 + "] },";
							} else {
//								String formDetails1 = updatefieldValues(jsonarray.getJSONObject(j));
//								formDetails = formDetails + "," + formDetails1;

								String formDetails1 = updatefieldValues(jsonarray.getJSONObject(j));
								if (counter == 0) {
									counter++;
									formDetailsSec = formDetails1;

								} else {
									counter++;
									formDetailsSec = formDetailsSec + "," + formDetails1;

								}
							}

						}
						String formDetailsSection = formDetailsFG + formDetailsSec;

						if (i == 0) {
							formDetails = " { \"ElementType\": \"Section\",\r\n" + "                \"Name\": \"" + Name
									+ "\",\r\n" + "   \"SectionElements\": [" + formDetailsSection + "] }";
						} else {
							formDetails = formDetails + "," + " { \"ElementType\": \"Section\",\r\n"
									+ "                \"Name\": \"" + Name + "\",\r\n" + "   \"SectionElements\": ["
									+ formDetailsSection + "] }";
						}
					} else {
						String formDetails1 = updatefieldValues(data);
						if (i == 0) {
							formDetails = formDetails1;
						} else {
							formDetails = formDetails + "," + formDetails1;
						}
					}

				}

//				else {
//					String Value = "";
//					String Id = "";
//					String Type = "";
//					String ShortName = "";
//					// formDetails += provideInputValues(object);
//					JSONObject data = provideInputValues(object);
//					String ElementType = data.get("ElementType").toString();
//					JSONArray jsonarray = null;
//					if (ElementType.equals("FieldGroup")) {
//						String Name = data.get("Name").toString();
//						jsonarray = data.getJSONArray("Fields");
//						String formDetails2 = "";
//
//						for (int j = 0; j < jsonarray.length(); j++)
//
//						{
//
//							String formDetails1 = updatefieldValues(jsonarray.getJSONObject(j));
//							if (j == 0) {
//
//								formDetails2 = formDetails1;
//							} else {
//								formDetails2 = formDetails2 + "," + formDetails1;
//							}
//
//							// formDetails += formDetails1;
//						}
//						formDetails += " { \"ElementType\": \"FieldGroup\",\r\n" + "                \"Name\": \"" + Name
//								+ "\",\r\n" + "   \"Fields\": [" + formDetails2 + "] },";
//					} else if (ElementType.equals("Section")) {
//						jsonarray = data.getJSONArray("SectionElements");
//						String Name = data.getString("Name");
//						String formDetailsFG = "";
//						String formDetailsSec = "";
//						int counter = 0;
//						for (int j = 0; j < jsonarray.length(); j++) {
//
//							ElementType = jsonarray.getJSONObject(j).get("ElementType").toString();
//							if (ElementType.equals("FieldGroup")) {
//								JSONArray jsonArrayFG = jsonarray.getJSONObject(j).getJSONArray("Fields");
//								String Name1 = jsonarray.getJSONObject(j).get("Name").toString();
//								Name1 = Name1.replace("\"", "\\\"");
//								String formDetails2 = "";
//								for (int k = 0; k < jsonArrayFG.length(); k++)
//
//								{
//
//									String formDetails1 = updatefieldValues(jsonArrayFG.getJSONObject(k));
//									if (k == 0) {
//
//										formDetails2 = formDetails1;
//									} else {
//										formDetails2 = formDetails2 + "," + formDetails1;
//									}
//
//								}
//
//								formDetailsFG = " { \"ElementType\": \"FieldGroup\",\r\n"
//										+ "                \"Name\": \"" + Name1 + "\",\r\n" + "   \"Fields\": ["
//										+ formDetails2 + "] }";
//							} else {
//								// String formDetails2 = "";
//
//								String formDetails1 = updatefieldValues(jsonarray.getJSONObject(j));
//
//								if (counter == 0) {
//									counter++;
//									formDetailsSec = formDetails1;
//
//								} else {
//									counter++;
//									formDetailsSec = formDetailsSec + "," + formDetails1;
//
//								}
//
//							}
//						}
//						String formDetailsSection = formDetailsFG + "," + formDetailsSec;
//						formDetails += " { \"ElementType\": \"Section\",\r\n" + "                \"Name\": \"" + Name
//								+ "\",\r\n" + "   \"SectionElements\": [" + formDetailsSection + "] }";
//
//					} else {
//						String formDetails1 = updatefieldValues(data);
//						formDetails += formDetails1;
//
//					}
//				}

			}

			// recordDefinitionData=jsonArray.toString();
			recordDefinitionData = formDetails.toString();

			System.out.println("Form Data  " + recordDefinitionData);
			return recordDefinitionData;

		} catch (Exception e) {
			logError("Failed to get form Data and fill input details - " + e.getMessage());
			return recordDefinitionData;
		}
	}

	public Boolean submitForm(String recordData, String LocId, String LocName, String ResId, String ResName,
			String FormId, String FormName) {
		String submitFormReq = "";
		apiUtils = new ApiUtils();
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("Accept", "application/json, text/plain, /");
		headers.put("Authorization", "Bearer " + accessToken);

		// level 2 try for Select One/Multiple and Aggregate
		Gson gson = new GsonBuilder().create();

		try {

			submitFormReq = "{\r\n" + "    \"Data\": {\r\n" + "        \"Header\": [],\r\n"
					+ "        \"FormDetails\": [\r\n" + recordData + "        ],\r\n"
					+ "        \"JobDetails\": [],\r\n" + "        \"TaskDetails\": [],\r\n"
					+ "        \"FormRef\": {\r\n" + "            \"Id\": \"" + FormId + "\",\r\n"
					// + " \"FormRefId\": \"432804b2-aca7-445e-8bf9-4b6a3d12321e\",\r\n"
					+ "            \"Name\": \"" + FormName + "\",\r\n" + "            \"Type\": \"Check\"\r\n"
					+ "        },\r\n" + "        \"FormType\": null,\r\n" + "        \"Notes\": null,\r\n"
					+ "        \"Program\": {},\r\n" + "        \"Resource\": {\r\n" + "            \"Id\": \"" + ResId
					+ "\",\r\n" + "            \"Name\": \"" + ResName + "\"\r\n" + "        },\r\n"
					+ "        \"Location\": {\r\n" + "            \"Id\": \"" + LocId + "\",\r\n"
					+ "            \"Name\": \"" + LocName + "\"\r\n" + "        },\r\n"
					+ "        \"Comments\": null,\r\n" + "        \"Attachments\": [],\r\n"
					+ "        \"IsSavedFormWip\": false,\r\n" + "        \"VerifiedBy\": {},\r\n"
					+ "        \"VerificationUpdated\": false\r\n" + "    }\r\n" + "}";

			Response response = apiUtils.submitRequest(METHOD_TYPE.POST, submitFormReq, headers, SubmitFormWebUrl);
			SingleDataResponse sdr = gson.fromJson(response.asString(), SingleDataResponse.class);
			recordId = sdr.Data.ID.toString();

			return sdr.Status;

		} catch (Exception e) {
			logError("Failed to get form Data - " + e.getMessage());
			return false;
		}
	}

	public JSONObject provideInputValues(JSONObject fd) {


		String ElementType = fd.get("ElementType").toString();
		JSONArray jsonArray = null;
		try {

			if (ElementType.equalsIgnoreCase("FieldGroup")) {


				jsonArray = fd.getJSONArray("Fields");

				updateJsonObject(jsonArray);

			}

			else if (ElementType.equalsIgnoreCase("Section")) {

				String Type = "";
				JSONArray jsonArraySec = null;
				// JSONArray jsonArrayFG = null;

				jsonArraySec = fd.getJSONArray("SectionElements");

				for (int i = 0; i < jsonArraySec.length(); i++) {

					JSONObject field = jsonArraySec.getJSONObject(i);
					Type = field.get("ElementType").toString();

					if (Type.equalsIgnoreCase("FieldGroup")) {
						jsonArray = field.getJSONArray("Fields");

						updateJsonObject(jsonArray);
					} else {
						// jsonArray = fd.getJSONArray("Fields");

						updateJsonObject(jsonArraySec);
					}

				}

			} else {

				String Type = fd.get("Type").toString();
				String shortName = fd.get("ShortName").toString();
				// fieldValues.put(shortName, "Identifier having Input Type FreeText Filled by
				// Automation");
				switch (Type) {
				case "Numeric":

					fd.put("Value", "100");
					fieldValues.put(shortName, "100.0");
					break;
				case "FreeText":
					fd.put("Value", "Free Text value entered through Automation");
					fieldValues.put(shortName, "Free Text value entered through Automation");
					break;
				case "Paragraph":
					fd.put("Value", "Paragraph Text value entered through Automation");
					fieldValues.put(shortName, "Paragraph Text value entered through Automation");
					break;
				case "SelectMultiple":

					fd.put("Value", StringEscapeUtils.unescapeJava(setValueForSelectMultipleField(fd)));

					String s = fd.get("Value").toString().replace("\\", "");

					fd.put("Value", new JSONArray(s));
					fieldValues.put(shortName, setValueForSelectMultipleField(fd));

					break;
				case "Date":
					fd.put("Value", "2021-07-19T18:30:00.000Z");
					fieldValues.put(shortName, "2021-07-19T18:30:00.000Z");
					break;
				case "Time":
					fd.put("Value", "2021-07-19T18:30:00.000Z");
					fieldValues.put(shortName, "2021-07-19T18:30:00.000Z");
					break;
				case "DateTime":
					fd.put("Value", "2021-07-19T18:30:00.000Z");
					fieldValues.put(shortName, "2021-07-19T18:30:00.000Z");
					break;
				case "SelectOne":

					fd.put("Value", setValueForSelectOneField(fd));
					String s1 = fd.get("Value").toString().replace("\\", "");

					fd.put("Value", new JSONObject(s1));
					fieldValues.put(shortName, setValueForSelectOneField(fd));

					break;

				case "Aggregate":
					fd.put("Value", "500");
					break;

				case "Identifier":

					String inputType = fd.getJSONObject("InputType").get("Id").toString();
					if (inputType.equals("SelectOne")) {

						String value = setValueForIdentifierSelectOneField(fd);
						fd.put("Value", value);
						fieldValues.put(shortName, value);
						break;

					} else {
						fd.put("Value", "Identifier having Input Type FreeText Filled by Automation");
						fieldValues.put(shortName, "Identifier having Input Type FreeText Filled by Automation");
					}

					break;

				}

			}

			return fd;

		}

		catch (Exception e) {
			logError("Failed to enter field value " + e.getMessage());
			return fd;
		}

	}

	public String setValueForSelectMultipleField(JSONObject fd) {
		String value = "";
		String Name = fd.getJSONArray("Options").getJSONObject(0).get("Name").toString();
		String Id = fd.getJSONArray("Options").getJSONObject(0).get("Id").toString();
		try {

			value = "[{\"Id\": \"" + Id + "\",\"Name\": \"" + Name + "\" }]";

			return value;
		} catch (Exception e) {
			logError("Failed to enter field value for Select Multiple " + e.getMessage());
			return value;
		}
	}

	public String setValueForSelectOneField(JSONObject fd) {
		String value = "";
		try {

			String Name = fd.getJSONArray("Options").getJSONObject(0).get("Name").toString();
			String Id = fd.getJSONArray("Options").getJSONObject(0).get("Id").toString();

			value = "{\"Id\": \"" + Id + "\",\"Name\": \"" + Name + "\"}";
			return value;
		} catch (Exception e) {
			logError("Failed to enter field value for Select Multiple " + e.getMessage());
			return value;
		}
	}

	public String setValueForIdentifierSelectOneField(JSONObject fd) {
		String value = "";
		try {

			value = fd.getJSONArray("IdentifierOption").get(0).toString();
			// String Id = fd.getJSONArray("Options").getJSONObject(0).get("Id").toString();

			// value = "{\"Id\": \"" + Id + "\",\"Name\": \"" + Name + "\"}";

			fd.put("Value", value);
			return value;
		} catch (Exception e) {
			logError("Failed to enter field value for Select Multiple " + e.getMessage());
			return value;
		}
	}

	public void updateJsonObject(JSONArray jsonArray) {
		String Type = "";
		String shortName = "";

		try {

			for (int j = 0; j < jsonArray.length(); j++) {
				JSONObject fieldGroupElement = jsonArray.getJSONObject(j);
				Type = fieldGroupElement.get("Type").toString();
				shortName = fieldGroupElement.get("ShortName").toString();

				switch (Type) {
				case "Numeric":
					jsonArray.getJSONObject(j).put("Value", "100");
					fieldValues.put(shortName, "100.0");

					break;
				case "FreeText":
					jsonArray.getJSONObject(j).put("Value", "Free Text value entered through Automation");
					fieldValues.put(shortName, "Free Text value entered through Automation");
					break;
				case "Paragraph":
					jsonArray.getJSONObject(j).put("Value", "Paragraph Text value entered through Automation");
					fieldValues.put(shortName, "Paragraph Text value entered through Automation");
					break;
				case "SelectMultiple":

					jsonArray.getJSONObject(j).put("Value",
							new JSONArray(setValueForSelectMultipleField(jsonArray.getJSONObject(j))));
					fieldValues.put(shortName, setValueForSelectMultipleField(jsonArray.getJSONObject(j)));

					break;
				case "Date":

					jsonArray.getJSONObject(j).put("Value", "2021-07-19T18:30:00.000Z");
					fieldValues.put(shortName, "2021-07-19T18:30:00.000Z");
					break;
				case "Time":
					jsonArray.getJSONObject(j).put("Value", "2021-07-19T18:30:00.000Z");
					fieldValues.put(shortName, "2021-07-19T18:30:00.000Z");
					break;
				case "DateTime":
					jsonArray.getJSONObject(j).put("Value", "2021-07-19T18:30:00.000Z");
					fieldValues.put(shortName, "2021-07-19T18:30:00.000Z");
					break;
				case "SelectOne":

					jsonArray.getJSONObject(j).put("Value",
							new JSONObject(setValueForSelectOneField(jsonArray.getJSONObject(j))));
					fieldValues.put(shortName, setValueForSelectOneField(jsonArray.getJSONObject(j)));
					break;

				case "Aggregate":

					jsonArray.getJSONObject(j).put("Value", "500");
					break;

				case "Identifier":

					String inputType = jsonArray.getJSONObject(j).getJSONObject("InputType").get("Id").toString();
					if (inputType.equals("SelectOne")) {

						String value = setValueForIdentifierSelectOneField(jsonArray.getJSONObject(j));
						jsonArray.getJSONObject(j).put("Value", value);
						fieldValues.put(shortName, value);
						break;

					} else {
						jsonArray.getJSONObject(j).put("Value",
								"Identifier having Input Type FreeText Filled by Automation");
						fieldValues.put(shortName, "Identifier having Input Type FreeText Filled by Automation");
					}

					break;

				}
			}
		} catch (Exception e) {

		}
	}

	public String updatefieldValues(JSONObject jsonObj) {
		String Type = "";
		String ShortName = "";
		String ElementType = "";
		String Id = "";
		String Value = "";
		String formDetails1 = "";
		String Name = "";

		try {
			Type = jsonObj.get("Type").toString();
			if (Type.equals("SelectOne")) {
				Value = jsonObj.getJSONObject("Value").toString();
			} else if (Type.equals("SelectMultiple")) {
				Value = jsonObj.getJSONArray("Value").toString();
			} else {
				Value = jsonObj.get("Value").toString();
			}
			ShortName = jsonObj.get("ShortName").toString();
			ElementType = jsonObj.get("ElementType").toString();
			Id = jsonObj.get("Id").toString();
			Name = jsonObj.get("Name").toString();

			// if(Name.contains("\""))
			Name = Name.replace("\"", "\\\"");
			formDetails1 = "    {\r\n" + "                \"Default\": null,\r\n" + "                \"Id\": \"" + Id
					+ "\",\r\n" + "                \"ElementType\": \"" + ElementType + "\",\r\n"
					+ "                \"ShortName\": \"" + ShortName + "\",\r\n" + "                \"Type\": \""
					+ Type + "\",\r\n" + "                \"Name\": \"" + Name + "\",\r\n"
					+ "                \"Configuration\": null,\r\n" + "                \"Value\": \"" + Value
					+ "\"\r\n" + "            }";

			if (Type.equals("Aggregate")) {

				String selectedFunction = jsonObj.getJSONObject("SelectedFunction").toString();
				String selectedSource = jsonObj.getJSONObject("SelectedSource").toString();
				formDetails1 = "{\r\n" + "                        \"Type\": \"" + Type + "\",\r\n"
						+ "                        \"SelectedFunction\": " + selectedFunction + ",\r\n"
						+ "                        \"Name\": \"" + Name + "\",\r\n"
						+ "                        \"SelectedSource\": " + selectedSource + ",\r\n"
						+ "                        \"Configuration\": null,\r\n"
						+ "                        \"ElementType\": \"" + ElementType + "\",\r\n"
						+ "                        \"Value\": " + Value + ",\r\n"
						+ "                        \"Default\": null,\r\n" + "                        \"Id\": \"" + Id
						+ "\",\r\n" + "                        \"ShortName\": \"" + ShortName + "\"\r\n"
						+ "                    }";
			}
			if (Type.equals("SelectOne") || Type.equals("SelectMultiple")) {
				formDetails1 = "    {\r\n" + "                \"Default\": null,\r\n" + "                \"Id\": \""
						+ Id + "\",\r\n" + "                \"ElementType\": \"" + ElementType + "\",\r\n"
						+ "                \"ShortName\": \"" + ShortName + "\",\r\n" + "                \"Type\": \""
						+ Type + "\",\r\n" + "                \"Name\": \"" + Name + "\",\r\n"
						+ "                \"Configuration\": null,\r\n" + "                \"Value\": " + Value
						+ "\r\n" + "            }";
			}
			if (Type.equals("Identifier")) {
				String InputType = jsonObj.getJSONObject("InputType").toString();
				formDetails1 = "    {\r\n" + "                \"Default\": null,\r\n" + "                \"Id\": \""
						+ Id + "\",\r\n" + "                \"InputType\": " + InputType + ",\r\n"
						+ "                \"ElementType\": \"" + ElementType + "\",\r\n"
						+ "                \"ShortName\": \"" + ShortName + "\",\r\n" + "                \"Type\": \""
						+ Type + "\",\r\n" + "                \"Name\": \"" + Name + "\",\r\n"
						+ "                \"Configuration\": null,\r\n" + "                \"Value\": \"" + Value
						+ "\"\r\n" + "            }";

			}
			return formDetails1;
		} catch (Exception e) {
			return formDetails1;
		}
	}

	public String verifyRecord() {
		String getRecordReq = "";
		String formDetails = "";
		apiUtils = new ApiUtils();
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("Accept", "application/json, text/plain, /");
		headers.put("Authorization", "Bearer " + accessToken);

		// level 2 try for Select One/Multiple and Aggregate
		new GsonBuilder().create();

		try {

			getRecordReq = "{\"data\":\"" + recordId + "\"}";

			Response response = apiUtils.submitRequest(METHOD_TYPE.POST, getRecordReq, headers, getRecordUrl);
			// SingleDataResponse sdr = gson.fromJson(response.asString(),
			// SingleDataResponse.class);
			// formDetails = sdr.Data.RecordData.FormDetails.toString();

			JSONObject jsnobject = new JSONObject(response.asString());

			JSONObject jsnobjectData = jsnobject.getJSONObject("Data");
			JSONObject jsnobjectRecordData = jsnobjectData.getJSONObject("RecordData");

			JSONArray jsonArray = jsnobjectRecordData.getJSONArray("FormDetails");
			System.out.println("jsonArray " + jsonArray);
			isMatched = true;
			for (int i = 0; i < jsonArray.length(); i++) {

				JSONObject data = jsonArray.getJSONObject(i);

				if (i < jsonArray.length()) {

					String ElementType = data.get("ElementType").toString();

					if (ElementType.equals("FieldGroup")) {
						JSONArray jsonarray = data.getJSONArray("Fields");
						String nameFG = data.get("Name").toString();
						nameFG = nameFG.replace("\"", "\\\"");
						// String formDetails2 = "";

						for (int j = 0; j < jsonarray.length(); j++) {
							
							String Type = jsonarray.getJSONObject(j).get("Type").toString();

							if (Type.equals("Aggregate")) {
								Double aggVal = calculateValuesForSumRangeCountRangeFunctions(jsonArray,
										jsonarray.getJSONObject(j), "FieldGroup", nameFG);
								isMatched = veriyfRecordDetails(jsonarray.getJSONObject(j), aggVal);
							} else {
								isMatched = veriyfRecordDetails(jsonarray.getJSONObject(j), 0.0);
							}

						}

					} else if (ElementType.equals("Section")) {
						JSONArray jsonarray = data.getJSONArray("SectionElements");
						String nameSection = data.get("Name").toString();
						for (int j = 0; j < jsonarray.length(); j++) {

							ElementType = jsonarray.getJSONObject(j).get("ElementType").toString();
							if (ElementType.equals("FieldGroup")) {
								JSONArray jsonArrayFG = jsonarray.getJSONObject(j).getJSONArray("Fields");

								// String formDetails2 = "";
								for (int k = 0; k < jsonArrayFG.length(); k++) {
									String Type = jsonArrayFG.getJSONObject(k).get("Type").toString();
									if (Type.equals("Aggregate")) {
										Double aggVal = calculateValuesForSumRangeCountRangeFunctions(jsonArray,
												jsonArrayFG.getJSONObject(k), "Section-FieldGroup", nameSection);
										isMatched = veriyfRecordDetails(jsonArrayFG.getJSONObject(k), aggVal);
									} else {

										isMatched = veriyfRecordDetails(jsonArrayFG.getJSONObject(k), 0.0);
									}

								}
							} else {
								String Type = jsonarray.getJSONObject(j).get("Type").toString();
								if (Type.equals("Aggregate")) {
									Double aggVal = calculateValuesForSumRangeCountRangeFunctions(jsonArray,
											jsonarray.getJSONObject(j), "Section", nameSection);
									isMatched = veriyfRecordDetails(jsonarray.getJSONObject(j), aggVal);
								} else {

									isMatched = veriyfRecordDetails(jsonarray.getJSONObject(j), 0.0);

								}
							}
						}
					} else {
						String Type = data.get("Type").toString();
						String Name = data.get("Name").toString();
						if (Type.equals("Aggregate")) {
							Double aggVal = calculateValuesForSumRangeCountRangeFunctions(jsonArray, data, "fields",
									Name);
							isMatched = veriyfRecordDetails(data, aggVal);
						} else {
							isMatched = veriyfRecordDetails(data, 0.0);
						}
					}

				}
			}

			return formDetails;

		} catch (Exception e) {
			logError("Failed to get record data - " + e.getMessage());
			return formDetails;
		}
	}

	public String getFormDefiition(String formName, JSONArray recordDetails) {
		String getFormefinitionReq = "";
		String recordDefinitionData = null;
		

		// HashMap<String, String> resourceMap = new HashMap<String, String>();

		apiUtils = new ApiUtils();
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("Accept", "application/json, text/plain, /");
		headers.put("Authorization", "Bearer " + accessToken);

		// level 2 try for Select One/Multiple and Aggregate
		new GsonBuilder().create();
		String name = "";
		try {

			getFormefinitionReq = "  {\r\n" + "    \"Take\": 100,\r\n" + "    \"Skip\": \"\",\r\n"
					+ "    \"Filter\": {\r\n" + "      \"filters\": [\r\n" + "        {\r\n"
					+ "         \"field\": \"FormName\",\r\n" + "         \"operator\": \"equals\",\r\n"
					+ "          \"value\": \"" + formName + "\"\r\n" + "        }\r\n" + "       ]\r\n" + "    }\r\n"
					+ "  }\r\n" + "";
			Response response = apiUtils.submitRequest(METHOD_TYPE.POST, getFormefinitionReq, headers,
					DptGetFormDefinitionUrl);

			recordDefinitionData = response.jsonPath().getJsonObject("Data.FormDetails").toString();

			// System.out.println(" UPdated 789 " + recordDefinitionData);

			JSONObject jsnobject = new JSONObject(response.asString());

			JSONObject jsnobject2 = jsnobject.getJSONObject("Data");

			// System.out.println(" jsnobject " +jsnobject);
			// System.out.println("jsnobject2 " +jsnobject2);
			JSONArray jsonArray = jsnobject2.getJSONArray("Fields");
			System.out.println("jsonArray " + jsonArray);
			HashMap<String, String> extractDepRule = new HashMap<String, String>();
			
			for (int i = 0; i < jsonArray.length(); i++) {

				JSONObject object = jsonArray.getJSONObject(i);

				if (i < jsonArray.length()) {

					JSONObject data = provideInputValues(object);
					String ElementType = data.get("ElementType").toString();
					List<Boolean> verifiedList = new ArrayList<Boolean>();
					if (ElementType.equals("FieldGroup")) {
						JSONArray jsonarray = data.getJSONArray("Fields");
						String nameFG = data.get("Name").toString();
						nameFG = nameFG.replace("\"", "\\\"");
						

						for (int j = 0; j < jsonarray.length(); j++) {
							// String formDetails1 = updatefieldValues(jsonarray.getJSONObject(j));
							if (jsonarray.getJSONObject(j).getJSONObject("DependencyRule").toString() != null) {
								String DepRule = jsonarray.getJSONObject(j).getJSONObject("DependencyRule").get("Rule")
										.toString();
								extractDepRule = extractStringUsingRegEx(DepRule);

								for (Map.Entry<String, String> entry : extractDepRule.entrySet()) {
									String fieldName = entry.getKey();
									String expectedvalue = entry.getValue();
									HashMap<String, List<Double>> value = checkVisibilityOfField(jsonArray, fieldName,
											"fields", "Dep");
									List<Double> actualVal = value.get("Values");

									String operator = extractArithmaticOrNumberUsingRegEx("[=><]*", expectedvalue);
									int expectedval = Integer
											.parseInt(extractArithmaticOrNumberUsingRegEx("[0-9]*", expectedvalue));
									double actualValue = actualVal.get(0);

									boolean isverified = compareData(operator, actualValue, expectedval);
									verifiedList.add(isverified);

								}
							}

						}

					} else if (ElementType.equals("Section")) {
						JSONArray jsonarray = data.getJSONArray("SectionElements");
						
						name = name.replace("\"", "\\\"");
						
						
						for (int j = 0; j < jsonarray.length(); j++) {

							ElementType = jsonarray.getJSONObject(j).get("ElementType").toString();
							if (ElementType.equals("FieldGroup")) {
								JSONArray jsonArrayFG = jsonarray.getJSONObject(j).getJSONArray("Fields");
//								String nameFG = jsonarray.getJSONObject(j).getString("Name");
//								nameFG = nameFG.replace("\"", "\\\"");
//								String formDetails2 = "";
								for (int k = 0; k < jsonArrayFG.length(); k++) {
									// String formDetails1 = updatefieldValues(jsonArrayFG.getJSONObject(k));
									if (jsonArrayFG.getJSONObject(k).getJSONObject("DependencyRule")
											.toString() != null) {
										String DepRule = jsonArrayFG.getJSONObject(k).getJSONObject("DependencyRule")
												.get("Rule").toString();
										extractDepRule = extractStringUsingRegEx(DepRule);

										for (Map.Entry<String, String> entry : extractDepRule.entrySet()) {
											String fieldName = entry.getKey();
											String expectedvalue = entry.getValue();
											HashMap<String, List<Double>> value = checkVisibilityOfField(jsonArray,
													fieldName, "fields", "Dep");
											List<Double> actualVal = value.get("Values");

											String operator = extractArithmaticOrNumberUsingRegEx("[=><]*",
													expectedvalue);
											int expectedval = Integer.parseInt(
													extractArithmaticOrNumberUsingRegEx("[0-9]*", expectedvalue));
											Double actualValue = actualVal.get(0);

											boolean isverified = compareData(operator, actualValue, expectedval);
											verifiedList.add(isverified);

										}
									}
								}

							} else {
//								
								if (jsonarray.getJSONObject(j).getJSONObject("DependencyRule").toString() != null) {
									String DepRule = jsonarray.getJSONObject(j).getJSONObject("DependencyRule")
											.get("Rule").toString();
									extractDepRule = extractStringUsingRegEx(DepRule);

									for (Map.Entry<String, String> entry : extractDepRule.entrySet()) {
										String fieldName = entry.getKey();
										String expectedvalue = entry.getValue();
										HashMap<String, List<Double>> value = checkVisibilityOfField(jsonArray,
												fieldName, "fields", "DEp");
										List<Double> actualVal = value.get("Values");

										String operator = extractArithmaticOrNumberUsingRegEx("[=><]*", expectedvalue);
										int expectedval = Integer
												.parseInt(extractArithmaticOrNumberUsingRegEx("[0-9]*", expectedvalue));
										Double actualValue = actualVal.get(0);

										boolean isverified = compareData(operator, actualValue, expectedval);
										verifiedList.add(isverified);

									}
								}

							}

						}
					} else {
						updatefieldValues(data);

						if (data.getJSONObject("DependencyRule").toString() != null) {
							String DepRule = data.getJSONObject("DependencyRule").get("Rule").toString();
							extractDepRule = extractStringUsingRegEx(DepRule);

							for (Map.Entry<String, String> entry : extractDepRule.entrySet()) {
								String fieldName = entry.getKey();
								String expectedvalue = entry.getValue();
								HashMap<String, List<Double>> value = checkVisibilityOfField(jsonArray, fieldName,
										"fields", "DEp");
								List<Double> actualVal = value.get("Values");

								String operator = extractArithmaticOrNumberUsingRegEx("[=><]*", expectedvalue);
								int expectedval = Integer
										.parseInt(extractArithmaticOrNumberUsingRegEx("[0-9]*", expectedvalue));
								Double actualValue = actualVal.get(0);

								boolean isverified = compareData(operator, actualValue, expectedval);
								verifiedList.add(isverified);

							}
						}

					}

				}

//				else {
////					String Value = "";
////					String Id = "";
////					String Type = "";
////					String ShortName = "";
//					// formDetails += provideInputValues(object);
//					JSONObject data = provideInputValues(object);
//					String ElementType = data.get("ElementType").toString();
//					JSONArray jsonarray = null;
//					if (ElementType.equals("FieldGroup")) {
//						String Name = data.get("Name").toString();
//						jsonarray = data.getJSONArray("Fields");
//						String formDetails2 = "";
//
//						for (int j = 0; j < jsonarray.length(); j++)
//
//						{
//
//							String formDetails1 = updatefieldValues(jsonarray.getJSONObject(j));
//							if (j == 0) {
//
//								formDetails2 = formDetails1;
//							} else {
//								formDetails2 = formDetails2 + "," + formDetails1;
//							}
//
//							// formDetails += formDetails1;
//						}
//						formDetails += " { \"ElementType\": \"FieldGroup\",\r\n" + "                \"Name\": \"" + Name
//								+ "\",\r\n" + "   \"Fields\": [" + formDetails2 + "] },";
//					} else if (ElementType.equals("Section")) {
//						jsonarray = data.getJSONArray("SectionElements");
//						String Name = data.getString("Name");
//						String formDetailsFG = "";
//						String formDetailsSec = "";
//						int counter = 0;
//						for (int j = 0; j < jsonarray.length(); j++) {
//
//							ElementType = jsonarray.getJSONObject(j).get("ElementType").toString();
//							if (ElementType.equals("FieldGroup")) {
//								JSONArray jsonArrayFG = jsonarray.getJSONObject(j).getJSONArray("Fields");
//								String Name1 = jsonarray.getJSONObject(j).get("Name").toString();
//								Name1 = Name1.replace("\"", "\\\"");
//								String formDetails2 = "";
//								for (int k = 0; k < jsonArrayFG.length(); k++)
//
//								{
//
//									String formDetails1 = updatefieldValues(jsonArrayFG.getJSONObject(k));
//									if (k == 0) {
//
//										formDetails2 = formDetails1;
//									} else {
//										formDetails2 = formDetails2 + "," + formDetails1;
//									}
//
//								}
//
//								formDetailsFG = " { \"ElementType\": \"FieldGroup\",\r\n"
//										+ "                \"Name\": \"" + Name1 + "\",\r\n" + "   \"Fields\": ["
//										+ formDetails2 + "] }";
//							} else {
//								// String formDetails2 = "";
//
//								String formDetails1 = updatefieldValues(jsonarray.getJSONObject(j));
//
//								if (counter == 0) {
//									counter++;
//									formDetailsSec = formDetails1;
//
//								} else {
//									counter++;
//									formDetailsSec = formDetailsSec + "," + formDetails1;
//
//								}
//
//							}
//						}
//						String formDetailsSection = formDetailsFG + "," + formDetailsSec;
//						formDetails += " { \"ElementType\": \"Section\",\r\n" + "                \"Name\": \"" + Name
//								+ "\",\r\n" + "   \"SectionElements\": [" + formDetailsSection + "] }";
//
//					} else {
//						String formDetails1 = updatefieldValues(data);
//						formDetails += formDetails1;
//
//					}
//				}

			}

			// recordDefinitionData=jsonArray.toString();
//			recordDefinitionData = formDetails.toString();
//
//			System.out.println("Form Data  " + recordDefinitionData);
			return recordDefinitionData;

		} catch (Exception e) {
			logError("Failed to get form Data and fill input details - " + e.getMessage());
			return recordDefinitionData;
		}
	}

	public boolean verifyTextEquality(String actual, String expected) {
		boolean isEqual = false;
		try {
			if (actual.equals(expected)) {
				isEqual = true;
			} else {
				isEqual = false;
			}
			return isEqual;
		} catch (Exception e) {

			return isEqual;
		}

	}

	public boolean veriyfRecordDetails(JSONObject object, Double expected) {
		boolean isMatched = false;
		String Type = object.get("Type").toString();
		String shortName = object.get("ShortName").toString();
		String Value = "";
		try {
			switch (Type) {
			case "Numeric":

				Value = object.get("Value").toString();
				isMatched = verifyTextEquality(Value, "100.0");

				break;
			case "FreeText":
				Value = object.get("Value").toString();
				isMatched = verifyTextEquality(Value, "Free Text value entered through Automation");
				// fd.put("Value", "Free Text value entered through Automation");

				break;
			case "Paragraph":
				Value = object.get("Value").toString();
				isMatched = verifyTextEquality(Value, "Paragraph Text value entered through Automation");
				// fd.put("Value", "Paragraph Text value entered through Automation");

				break;
			case "SelectMultiple":
				JSONArray value = object.getJSONArray("Value");
				for (int j = 0; j < value.length(); j++) {
					String Name = value.getJSONObject(j).get("Name").toString();
					isMatched = verifyTextEquality(Name, fieldValues.get(shortName));
				}

				break;
			case "Date":
				Value = object.get("Value").toString();
				isMatched = verifyTextEquality(Value, "2021-07-19T18:30:00.0000000Z");

				break;
			case "Time":
				Value = object.get("Value").toString();
				isMatched = verifyTextEquality(Value, "2021-07-19T18:30:00.0000000Z");

				break;
			case "DateTime":
				Value = object.get("Value").toString();
				isMatched = verifyTextEquality(Value, "2021-07-19T18:30:00.0000000Z");
				break;
			case "SelectOne":

				Value = object.getJSONObject("Value").get("Name").toString();
				JSONObject obj = new JSONObject(fieldValues.get(shortName).toString());

				isMatched = verifyTextEquality(Value, obj.get("Name").toString());

				break;

			case "Aggregate":
				Value = object.get("Value").toString();
				isMatched = verifyTextEquality(Value, expected.toString());
				break;

			case "Identifier":

				String inputType = object.getJSONObject("InputType").get("Id").toString();
				if (inputType.equals("SelectOne")) {
					Value = object.get("Value").toString();
					isMatched = verifyTextEquality(Value, fieldValues.get(shortName));

					break;

				} else {

					Value = object.get("Value").toString();
					isMatched = verifyTextEquality(Value, fieldValues.get(shortName));

				}

				break;

			}
			return isMatched;
		} catch (Exception e) {
			logInfo("Record details are not matched");
			return false;
		}

	}

	public HashMap<String, List<Double>> checkVisibilityOfField(JSONArray jsonArray, String Name, String placeOfFields,
			String SecFGName) {
		double value = 0.0;
		double counter = 0;
		List<Double> values = new ArrayList<Double>();
		// List<String> sourceList = new ArrayList<String>();
	
		HashMap<String, List<Double>> fieldValues = new HashMap<String, List<Double>>();
		try {
			for (int i = 0; i < jsonArray.length(); i++) {

				JSONObject data = jsonArray.getJSONObject(i);

				if (i < jsonArray.length()) {
					String ElementType = data.get("ElementType").toString();
					if (placeOfFields.equals("fields")) {
					

						if (ElementType.equals("FieldGroup")) {
							JSONArray jsonarray = data.getJSONArray("Fields");
							for (int j = 0; j < jsonarray.length(); j++) {

								String NameActual = jsonarray.getJSONObject(j).get("Name").toString();

								if (NameActual.equals(Name)) {
									value = Double.parseDouble(jsonarray.getJSONObject(j).get("Value").toString());

									counter++;
									values.add(value);

								}
							}

						} else if (ElementType.equals("Section")) {
							JSONArray jsonarray = data.getJSONArray("SectionElements");

							for (int j = 0; j < jsonarray.length(); j++) {

								ElementType = jsonarray.getJSONObject(j).get("ElementType").toString();
								if (ElementType.equals("FieldGroup")) {
									JSONArray jsonArrayFG = jsonarray.getJSONObject(j).getJSONArray("Fields");

									for (int k = 0; k < jsonArrayFG.length(); k++) {

										String NameActual = jsonArrayFG.getJSONObject(k).get("Name").toString();

										if (NameActual.equals(Name)) {
											value = Double
													.parseDouble(jsonArrayFG.getJSONObject(j).get("Value").toString());
											counter++;
											values.add(value);

										}

									}
								} else {

									String NameActual = jsonarray.getJSONObject(j).get("Name").toString();

									if (NameActual.equals(Name)) {
										value = Double.parseDouble(jsonarray.getJSONObject(j).get("Value").toString());
										counter++;
										values.add(value);
									}

								}
							}
						} else {

							String NameActual = data.get("Name").toString();

							if (NameActual.equals(Name)) {
								value = Double.parseDouble(data.get("Value").toString());
								counter++;
								values.add(value);
							}

						}

						List<Double> counterList = new ArrayList<Double>();
						counterList.add(counter);
						fieldValues.put("Values", values);
						fieldValues.put("Count", counterList);

					}
				

					else if (placeOfFields.equals("Section-FieldGroup") && ElementType.equals("Section")) {

						JSONArray jsonarray = data.getJSONArray("SectionElements");

						for (int j = 0; j < jsonarray.length(); j++) {

							String ElementType1 = jsonarray.getJSONObject(j).get("ElementType").toString();
							String FGName = jsonarray.getJSONObject(j).get("Name").toString();
							if (ElementType1.equals("FieldGroup") && FGName.equals(SecFGName)) {
								JSONArray jsonArrayFG = jsonarray.getJSONObject(j).getJSONArray("Fields");

								for (int k = 0; k < jsonArrayFG.length(); k++) {

									String NameActual = jsonArrayFG.getJSONObject(k).get("Name").toString();

									if (NameActual.equals(Name)) {
										value = Double
												.parseDouble(jsonArrayFG.getJSONObject(j).get("Value").toString());
										counter++;
										values.add(value);

									}

								}

							}
						}
						List<Double> counterList = new ArrayList<Double>();
						counterList.add(counter);
						fieldValues.put("Values", values);
						fieldValues.put("Count", counterList);
					}

					else if (placeOfFields.equals("Section")&& ElementType.equals("Section")) {

						JSONArray jsonarray = data.getJSONArray("SectionElements");
						String SecName = data.get("Name").toString();
						if (SecName.equals(SecFGName)) {

							for (int j = 0; j < jsonarray.length(); j++) {

								String ElementType3 = jsonarray.getJSONObject(j).get("ElementType").toString();
								if (ElementType3.equals("FieldGroup")) {
									JSONArray jsonArrayFG = jsonarray.getJSONObject(j).getJSONArray("Fields");

									for (int k = 0; k < jsonArrayFG.length(); k++) {

										String NameActual = jsonArrayFG.getJSONObject(k).get("Name").toString();

										if (NameActual.equals(Name)) {
											value = Double
													.parseDouble(jsonArrayFG.getJSONObject(j).get("Value").toString());
											counter++;
											values.add(value);

										}

									}
								} else {

									String NameActual = jsonarray.getJSONObject(j).get("Name").toString();

									if (NameActual.equals(Name)) {
										value = Double.parseDouble(jsonarray.getJSONObject(j).get("Value").toString());
										counter++;
										values.add(value);
									}

								}
							}
						}
						List<Double> counterList = new ArrayList<Double>();
						counterList.add(counter);
						fieldValues.put("Values", values);
						fieldValues.put("Count", counterList);

					} else {

						String ElementType1 = data.get("ElementType").toString();
						String FGName = data.get("Name").toString();
						if (ElementType1.equals("FieldGroup") && FGName.equals(SecFGName)) {
							JSONArray jsonarray1 = data.getJSONArray("Fields");
							for (int l = 0; l < jsonarray1.length(); l++) {

								String NameActual = jsonarray1.getJSONObject(l).get("Name").toString();

								if (NameActual.equals(Name)) {
									value = Double.parseDouble(jsonarray1.getJSONObject(l).get("Value").toString());

									counter++;
									values.add(value);

								}
							}

						}
						List<Double> counterList = new ArrayList<Double>();
						counterList.add(counter);
						fieldValues.put("Values", values);
						fieldValues.put("Count", counterList);
					}
				}

			}

			return fieldValues;

		} catch (Exception e) {
			logInfo("could not fetch details for the field " + e.getMessage());
			return fieldValues;
		}
	}

	public Double calculateExpectedValueforAggregareFunction(String funcName, HashMap<String, List<Double>> value) {
		List<Double> values = new ArrayList<Double>();
		Double counter = 0.0;
		Double min = 0.0;
		Double max = 0.0;
		double funcValue = 0.0;

		for (Map.Entry<String, List<Double>> entry : value.entrySet()) {
			String fieldName = entry.getKey();
			if (fieldName.equals("Values")) {
				values.addAll(entry.getValue());

			} else if (fieldName.equals("Min")) {
				min = Double.parseDouble(entry.getValue().get(0).toString());
			} else if (fieldName.equals("Max")) {
				max = Double.parseDouble(entry.getValue().get(0).toString());
			} else {
				counter = Double.parseDouble(entry.getValue().get(0).toString());
			}

		}
		try {

			switch (funcName) {
			case "Average":

				funcValue = values.stream().mapToDouble(d -> d).average().orElse(0.0);
				break;
			case "Sum":
				Double sum = 0.0;
				for (int i = 0; i < values.size(); i++) {
					sum = sum + values.get(i).doubleValue();

				}
				funcValue = sum.doubleValue();
				break;
			case "Min Value":

				funcValue = values.stream() // Stream<Integer>
						.min(Double::compare) // Optional<Integer>
						.get().doubleValue();
				break;

			case "Max Value":

				funcValue = values.stream() // Stream<Integer>
						.max(Double::compare) // Optional<Integer>
						.get().doubleValue();
				break;
			// break;

			case "Count Range":
				Integer Funccounter = 0;
				for (Double val : values) {
					if (val > min && val < max) {
						Funccounter++;
					}
				}
				funcValue = Funccounter.doubleValue();

				break;
			case "Sum Range":
				Double sumRange = 0.0;
				for (Double val : values) {

					if (val > min && val < max) {
						sumRange += val;
						counter++;
					}
				}
				funcValue = sumRange.doubleValue();

				break;

			}
			return funcValue;
		} catch (Exception e) {
			logInfo("Coudnt calculate value for Agg function" + e.getMessage());
			return funcValue;
		}

	}

	public Double calculateValuesForSumRangeCountRangeFunctions(JSONArray jsonArray, JSONObject object,
			String placeOfField, String SecFGName) {
		Double expected = 0.0;
		Double minValue = 0.0;
		Double maxValue = 0.0;
		String funName = object.getJSONObject("SelectedFunction").get("Name").toString();
		try {
			if (funName.equals("Sum Range") || funName.equals("Count Range")) {

				if (object.getJSONObject("SelectedFunction").get("Name").toString() == "Count Range"
						|| object.getJSONObject("SelectedFunction").get("Name").toString() == "Sum Range") {
					minValue = Double.parseDouble(
							object.getJSONArray("SelectedFunctionParameters").getJSONObject(0).get("Value").toString());
					maxValue = Double.parseDouble(
							object.getJSONArray("SelectedFunctionParameters").getJSONObject(1).get("Value").toString());
				}

				JSONArray sourcecollection = object.getJSONArray("SelectedSourceCollection");
				for (int k = 0; k < sourcecollection.length(); k++) {

					String SourceName = object.getJSONArray("SelectedSourceCollection").getJSONObject(k).get("Name")
							.toString();
					HashMap<String, List<Double>> value = checkVisibilityOfField(jsonArray, SourceName, placeOfField,
							SecFGName);
					value.put("Min", Arrays.asList(minValue));
					value.put("Max", Arrays.asList(maxValue));
					expected += calculateExpectedValueforAggregareFunction(funName, value);

				}

			} else {
				String SourceName = object.getJSONObject("SelectedSource").get("Name").toString();

				HashMap<String, List<Double>> value = checkVisibilityOfField(jsonArray, SourceName, placeOfField,
						SecFGName);
				expected = calculateExpectedValueforAggregareFunction(funName, value);
			}
			return expected;
		}

		catch (Exception e) {
			logInfo("Coudnt calculate value for Agg function" + e.getMessage());
			return expected;
		}
	}

	public HashMap<String, String> extractStringUsingRegEx(String DependencyRule) {
		// String fieldName = "";
		// String pattern="if(.*?)=<>";
		String pattern = "[=><]*[a-zA-Z\\s]*[0-9]*";

		Pattern regPattern = Pattern.compile(pattern);
		Matcher matcher = regPattern.matcher(DependencyRule);
		
		List<String> lst = new ArrayList<String>();
		HashMap<String, String> map = new HashMap<String, String>();
		while (matcher.find()) {

			for (int groupIdx = 0; groupIdx < matcher.groupCount() + 1; groupIdx++) {
				if (!(matcher.group(groupIdx).equals("if") || matcher.group(groupIdx).equals("Show")
						|| matcher.group(groupIdx).equals("else") || matcher.group(groupIdx).equals("Hide")
						|| matcher.group(groupIdx).equals(""))) {
					lst.add(matcher.group(groupIdx));
					// System.out.println("[" + mIdx + "][" + groupIdx + "] = " +
					// matcher.group(groupIdx));

				}

				// mIdx++;
			}

		}
		System.out.println("List= " + lst);
		for (int i = 0; i < lst.size(); i = i + 2) {

			map.put(lst.get(i), lst.get(i + 1));

		}
		System.out.println("Map= " + map);
		return map;
	}

	public String extractArithmaticOrNumberUsingRegEx(String pattern, String Actual) {
		String operator = "";
		// String pattern="if(.*?)=<>";
		// String pattern = "[=><]*";

		Pattern regPattern = Pattern.compile(pattern);
		Matcher matcher = regPattern.matcher(Actual);
		List<String> lst = new ArrayList<String>();
		// HashMap<String, String> map = new HashMap<String, String>();
		while (matcher.find()) {

			for (int groupIdx = 0; groupIdx < matcher.groupCount() + 1; groupIdx++) {

				operator = matcher.group(groupIdx);

			}

		}
		System.out.println("List= " + lst);

		return operator;
	}

	public boolean compareData(String operator, Double actual, Integer expected) {
		boolean isMatched = false;
//		String Type = object.get("Type").toString();
//		String Value = "";
		try {
			switch (operator) {
			case "=":
				if (actual.intValue() == expected) {
					isMatched = true;
				}

				break;
			case "<":
				if (actual.intValue() < expected) {
					isMatched = true;
				}

				break;
			case ">":
				if (actual.intValue() > expected) {
					isMatched = true;
				}

				break;
			case "<=":
				if (actual.intValue() <= expected) {
					isMatched = true;
				}

				break;
			case ">=":
				if (actual.intValue() >= expected) {
					isMatched = true;
				}
				break;

			}
			return isMatched;
		} catch (Exception e) {
			logInfo("data  not matched");
			return false;
		}

	}

}
