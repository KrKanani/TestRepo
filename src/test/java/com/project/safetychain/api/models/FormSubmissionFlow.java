package com.project.safetychain.api.models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.project.safetychain.api.models.support.Support;
import com.project.safetychain.api.models.support.Support.DPT_FORM_TYPES;
import com.project.safetychain.api.models.support.Support.FormFieldParams;
import com.project.safetychain.api.models.support.Support.FormParams;
import com.project.safetychain.api.models.support.Support.RESOURCE_TYPES;
import com.project.utilities.ApiUtils;
import com.project.utilities.ApiUtils.METHOD_TYPE;

import io.restassured.response.Response;

public class FormSubmissionFlow extends Auth {

	/** DPT APIs */
	public String DptAddSubmitFormUrl = baseURI + FormSubmissionUrls.DPT_ADD_SUBMIT_FORM_URL;

	ApiUtils apiUtils = new ApiUtils();
	Gson gson = new GsonBuilder().create();
	Random rand = new Random();

	public boolean createRecord(FormParams fp) {
		String saveFormDesignRequest = null;
		String fields = "";
		int typeCount = 0;
		String ResourceName = "";

		try {
			Map<String, String> headers = new HashMap<String, String>();
			headers.put("Content-Type", "application/json");
			headers.put("Accept", "application/json, text/plain, /");
			headers.put("Authorization", "Bearer " + accessToken);
			fields = appendFormElements(fp.formElements, fp.FormName, fp.type);
			if (fp.CustomerResources != null) {
				if (typeCount > 0) {
					ResourceName += Support.updateResourceList(fp.CustomerResources, RESOURCE_TYPES.CUSTOMERS);
					ResourceName = "," + ResourceName;
				} else {
					ResourceName += Support.updateResourceList(fp.CustomerResources, RESOURCE_TYPES.CUSTOMERS);
				}
				typeCount++;
			}

			if (fp.EquipmentResources != null) {
				if (typeCount > 0) {
					ResourceName += Support.updateResourceList(fp.EquipmentResources, RESOURCE_TYPES.EQUIPMENT);
					ResourceName = "," + ResourceName;
				} else {
					ResourceName += Support.updateResourceList(fp.EquipmentResources, RESOURCE_TYPES.EQUIPMENT);
				}
				typeCount++;
			}

			if (fp.ItemsResources != null) {
				if (typeCount > 0) {
					ResourceName += Support.updateResourceList(fp.ItemsResources, RESOURCE_TYPES.ITEMS);
					ResourceName = "," + ResourceName;
				} else {
					ResourceName += Support.updateResourceList(fp.ItemsResources, RESOURCE_TYPES.ITEMS);
				}
				typeCount++;
			}

			if (fp.SuppliersResources != null) {
				if (typeCount > 0) {
					ResourceName += Support.updateResourceList(fp.SuppliersResources, RESOURCE_TYPES.SUPPLIERS);
					ResourceName = "," + ResourceName;
				} else {
					ResourceName += Support.updateResourceList(fp.SuppliersResources, RESOURCE_TYPES.SUPPLIERS);
				}
				typeCount++;
			}

			saveFormDesignRequest = "{\r\n" + "\"Data\": \r\n" + "        {\r\n"
					+ "        \"RecordSummaryDetail\": \r\n" + "		    [{\r\n"
					+ "            \"FormRef\":{\"Name\":\"" + fp.FormName + "\"},\r\n"
					+ "			\"Resource\":{\"Name\":\"" + fp.ResourceInstanceNm + "\"},\r\n"
					+ "			\"Location\":{\"Name\":\"" + fp.LocationInstanceNm + "\"},\r\n"
					+ "            \"RecordData\": \r\n" + "			        {\r\n"
					+ "                    \"FormDetails\": \r\n" + "					    [\r\n"
					+ "                        " + fields + "]\r\n" + "                    }\r\n" + "            }\r\n"
					+ "			]\r\n" + "        },\r\n" + "\"Operation\": \"Insert\",\r\n" + "\"Status\": true,\r\n"
					+ "\"Errors\": null,\r\n" + "\"RefreshToken\": false,\r\n" + "\"PartialSuccess\": true\r\n" + "}";

			Response submitResponse = apiUtils.submitRequest(METHOD_TYPE.POST, saveFormDesignRequest, headers,
					DptAddSubmitFormUrl);
			SimpleDataResponse submitRes = gson.fromJson(submitResponse.asString(), SimpleDataResponse.class);

			if (submitRes.RequestStatus.equalsIgnoreCase("pending")) {
				logInfo("Created Record For form - " + fp.FormName);
				return true;
			} else {
				logInfo("Could not create Record - " + fp.FormName);
				return false;
			}

		} catch (Exception e) {
			logError("Failed to create Record- " + e.getMessage());
			return false;
		}
	}

	public String appendFormElements(List<FormFieldParams> listOfElements, String FormName, String formType) {
		String addField = "";

		try {
			for (int i = 0; i < listOfElements.size(); i++) {
				if (i != (listOfElements.size() - 1)) {
					addField += updateFormElements(listOfElements.get(i), FormName, false, formType);
				} else {
					addField += updateFormElements(listOfElements.get(i), FormName, true, formType);
				}
			}
			return addField;
		} catch (Exception e) {
			logError("Failed to set form element for - " + e.getMessage());
			return addField;
		}
	}

	public String updateFormElements(FormFieldParams ffp, String FormName, boolean lastField, String formType) {
		String addField = null;
		String shortName = "";
		shortName = getFieldShortName(FormName, ffp.Name, formType);
		try {

			addField = "{\r\n" + "							\"Value\":\"" + ffp.Value + "\",\r\n"
					+ "							\"ShortName\":\"" + shortName + "\"\r\n"
					+ "							}";

			if (!lastField) {

				addField += "                ,\r\n";
			} else {
				addField += "                \r\n";
			}

			return addField;
		} catch (Exception e) {
			logError("Failed to set form element for - " + ffp.Name + e.getMessage());
			return addField;
		}
	}

	public String getFieldShortNameUsingFormCompliance(String formName, String fieldName) {
		String shortName = null;
		apiUtils = new ApiUtils();
		FormDesigner formDesigner = new FormDesigner();
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("Accept", "application/json, text/plain, /");
		headers.put("Authorization", "Bearer " + accessToken);
		Gson gson = new GsonBuilder().create();
		try {
			String getComplianceValuesJson = "{\"Data\": \r\n" + "  {  \"Take\": 100,\r\n" + "    \"Skip\": \"\",\r\n"
					+ "    \"FormNames\": [\"" + formName + "\"]\r\n" + "    }\r\n" + "}";

			Response getComplianceResponse = apiUtils.submitRequest(METHOD_TYPE.POST, getComplianceValuesJson, headers,
					formDesigner.DptGetFormComplianceUrl);
			SingleDataResponse sdr = gson.fromJson(getComplianceResponse.asString(), SingleDataResponse.class);

			for (int i = 0; i < sdr.Data.Forms.get(0).FormData.FormDetails.Fields.size(); i++) {
				System.out.println(" sdr.Data.Forms.get(0).FormData.FormDetails.Fields.size()"
						+ sdr.Data.Forms.get(0).FormData.FormDetails.Fields.size());
				if (sdr.Data.Forms.get(0).FormData.FormDetails.Fields.get(i).Name.equalsIgnoreCase(fieldName)) {
					shortName = sdr.Data.Forms.get(0).FormData.FormDetails.Fields.get(i).ShortName;
					logInfo("Short Name= " + shortName);
					return shortName;
				}

			}
			return shortName;

		} catch (Exception e) {
			logInfo("Unable to fetch field Short Name");
			return shortName;
		}
	}

	public String getFieldShortName(String formName, String fieldName, String formType) {
		String shortName = null;
		int sectionsCount = 0, fieldsCount = 0;
		
		apiUtils = new ApiUtils();
		FormDesigner formDesigner = new FormDesigner();
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("Accept", "application/json, text/plain, /");
		headers.put("Authorization", "Bearer " + accessToken);
		Gson gson = new GsonBuilder().create();
		try {
			String getFormValuesJson = "  {\r\n" + "    \"Take\": 100,\r\n" + "    \"Skip\": \"\",\r\n"
					+ "    \"Filter\": {\r\n" + "      \"filters\": [\r\n" + "        {\r\n"
					+ "         \"field\": \"FormName\",\r\n" + "         \"operator\": \"equals\",\r\n"
					+ "          \"value\": \"" + formName + "\"\r\n" + "        }\r\n" + "       ]\r\n" + "    }\r\n"
					+ "  }\r\n" + "";

			Response getFormDefintionResponse = apiUtils.submitRequest(METHOD_TYPE.POST, getFormValuesJson, headers,
					formDesigner.DptGetFormDefinitionUrl);
			
			MultipleDataResponse sdr = gson.fromJson(getFormDefintionResponse.asString(), MultipleDataResponse.class);

			if(formType == null || !(formType.equalsIgnoreCase(DPT_FORM_TYPES.AUDIT))) {
				for (int i = 0; i < sdr.Data.get(0).Fields.size(); i++) {
					if (sdr.Data.get(0).Fields.get(i).Name.equalsIgnoreCase(fieldName)) {
						shortName = sdr.Data.get(0).Fields.get(i).ShortName;
						logInfo("Short Name= " + shortName);
						return shortName;
					}
				}
			}
			else {
				sectionsCount = sdr.Data.get(0).Fields.size();
				
				for (int i = 0; i < sectionsCount; i++) {
					fieldsCount = sdr.Data.get(0).Fields.get(i).Fields.size();
					for (int j = 0; j < fieldsCount; j++) {
						if (sdr.Data.get(0).Fields.get(i).Fields.get(j).Name.equalsIgnoreCase(fieldName)) {
							shortName = sdr.Data.get(0).Fields.get(i).Fields.get(j).ShortName;
							logInfo("Short Name= " + shortName);
							return shortName;
						}
					}
				}
			}
			return shortName;

		} catch (Exception e) {
			logInfo("Unable to fetch field Short Name");
			return shortName;
		}
	}

}
