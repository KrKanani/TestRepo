package com.project.safetychain.apiproject.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.project.utilities.ApiUtils;
import com.project.utilities.ApiUtils.METHOD_TYPE;

import io.restassured.response.Response;

public class FormsManager extends Auth {

	public String GetAllFormsURL = baseURI + FormsManagerURL.GET_ALL_FORMS;
	public String updateFormsURL = baseURI + FormsManagerURL.UPDATE_FORM_STATUS;
	public String saveVerificationURL = baseURI + FormsManagerURL.SAVE_VERIFICATION;
	public String GetVerificationDetails1URL = baseURI + FormsManagerURL.GET_VERIFICATION_FORM_DETAILS1;
	public String GetFormDataURL = baseURI + FormsManagerURL.GET_FORM_DATA;
	public String SaveFormsChartRelationURL = baseURI + FormsManagerURL.SAVE_FORM_CHART_RELATION;

	/**
	 * This method is used to update form enable status
	 * @author pashine_a
	 * @param formDetails & enableStatus
	 * @return boolean. It will return boolean result true if form status updated
	 */
	public boolean updateFormStatus(HashMap<String, String> formDetails, boolean enableStatus) {
		try {

			String updateFormStatusJSON = null;
			List<String> formIDs = new ArrayList<>();

			ApiUtils apiUtils = new ApiUtils();
			Gson gson = new GsonBuilder().create();

			Map<String, String> headers = new HashMap<String, String>();
			headers.put("Content-Type", "application/json");
			headers.put("Accept", "application/json, text/plain, /");
			headers.put("Authorization", "Bearer " + accessToken);

			for (Map.Entry<String, String> entry : formDetails.entrySet()) {
				formIDs.add(entry.getValue().toString());
			}

			if(enableStatus) {
				updateFormStatusJSON = "{"
						+ "  \"EnableForms\": "
						+ getFormIDs(formIDs)
						+ ",  \"DisableForms\":["
						+ "]}";
			}else {
				updateFormStatusJSON = "{"
						+ "  \"EnableForms\": ["
						+ "],  \"DisableForms\":"
						+ getFormIDs(formIDs)
						+ "}";
			}

			Response updateFormStatusResponse = apiUtils.submitRequest(METHOD_TYPE.POST, updateFormStatusJSON, headers,
					updateFormsURL);			

			SingleDataResponse response = gson.fromJson(updateFormStatusResponse.asString(),
					SingleDataResponse.class);

			if(!response.Status) {
				logError("Failed to update form status");
				return false;
			}

			logInfo("Updated form status");
			return true;

		} catch (Exception e) {
			logError("Failed to update form status");
			return false;
		}
	}

	/**
	 * This method is used to get form details
	 * @author pashine_a
	 * @param formName & property
	 * @return String. It will return value of provided property
	 */
	public String getFormDetails(String formName, String property) {
		try {
			String getFormDetailsJSON = null;

			ApiUtils apiUtils = new ApiUtils();
			Gson gson = new GsonBuilder().create();

			Map<String, String> headers = new HashMap<String, String>();
			headers.put("Content-Type", "application/json");
			headers.put("Accept", "application/json, text/plain, /");
			headers.put("Authorization", "Bearer " + accessToken);

			getFormDetailsJSON = "{"
					+ "  \"skip\": 0,"
					+ "  \"take\": 1000,"
					+ "  \"Parameters\": {"
					+ "    \"SearchString\": null"
					+ "  },"
					+ "  \"pageSize\": 1000"
					+ "}";

			Response updateFormStatusResponse = apiUtils.submitRequest(METHOD_TYPE.POST, getFormDetailsJSON, headers,
					GetAllFormsURL);			

			MultipleDataResponse response = gson.fromJson(updateFormStatusResponse.asString(),
					MultipleDataResponse.class);

			if(response.Data==null) {
				logError("Failed to get form details");
				return null;
			}

			String propertyValue = null;
			switch (property.toUpperCase()) {
			case "ID":
				for (int i = 0; i < response.Data.Rows.size(); i++) {
					if (response.Data.Rows.get(i).Name.equalsIgnoreCase(formName)) {
						propertyValue = response.Data.Rows.get(i).Id;
						break;
					}
				}
				break;

			case "ISENABLED":
				for (int i = 0; i < response.Data.Rows.size(); i++) {
					if (response.Data.Rows.get(i).Name.equalsIgnoreCase(formName)) {
						propertyValue = response.Data.Rows.get(i).IsEnabled.toString();
						break;
					}
				}
				break;

			default:
				break;
			}

			logInfo("Got '"+property+"' value -> "+propertyValue+" for form -> "+formName);
			return propertyValue;

		} catch (Exception e) {
			logError("Failed to get form details");
			return null;
		}
	}


	/**
	 * This method is used to save verification for the provided form(s)
	 * @author pashine_a
	 * @param formDetails, verificationID & addVerification
	 * @return boolean. It will return boolean result true if verification is linked with the form(s)
	 */
	public boolean saveVerification(HashMap<String, String> formDetails, String verificationID, boolean addVerification) {
		try {

			String addVerificationJSON = null;
			List<String> formIDs = new ArrayList<>();

			ApiUtils apiUtils = new ApiUtils();
			Gson gson = new GsonBuilder().create();

			Map<String, String> headers = new HashMap<String, String>();
			headers.put("Content-Type", "application/json");
			headers.put("Accept", "application/json, text/plain, /");
			headers.put("Authorization", "Bearer " + accessToken);

			for (Map.Entry<String, String> entry : formDetails.entrySet()) {
				formIDs.add(entry.getValue().toString());
			}

			if(addVerification) {
				addVerificationJSON = "{"
						+ "  \"VeriicationId\": \""+verificationID+"\","
						+ "  \"RemovedFormIds\": ["
						+ "  ],"
						+ "  \"AddedFormIds\": "
						+ getFormIDs(formIDs)
						+ "}";
			}else {
				addVerificationJSON = "{"
						+ "  \"VeriicationId\": \""+verificationID+"\","
						+ "  \"RemovedFormIds\":"
						+ getFormIDs(formIDs)
						+ ","
						+ "  \"AddedFormIds\": ["
						+ "  ]}";
			}

			Response updateFormStatusResponse = apiUtils.submitRequest(METHOD_TYPE.POST, addVerificationJSON, headers,
					saveVerificationURL);			

			SingleDataResponse response = gson.fromJson(updateFormStatusResponse.asString(),
					SingleDataResponse.class);

			if(!response.Status) {
				logError("Failed to add Verification for form");
				return false;
			}

			logInfo("Added Verification for forms(s)");
			return true;

		} catch (Exception e) {
			logError("Failed to add Verification to form");
			return false;
		}
	}

	/**
	 * This method is used to get form details for verification
	 * @author pashine_a
	 * @param formName & property
	 * @return String. It will return value of provided property
	 */
	public String getVerificationDetails(String formName, String verificationID, String property) {
		try {
			String getFormDetailsJSON = null;

			ApiUtils apiUtils = new ApiUtils();
			Gson gson = new GsonBuilder().create();

			Map<String, String> headers = new HashMap<String, String>();
			headers.put("Content-Type", "application/json");
			headers.put("Accept", "application/json, text/plain, /");
			headers.put("Authorization", "Bearer " + accessToken);

			getFormDetailsJSON = "{"
					+ "  \"skip\": 0,"
					+ "  \"take\": 1000,"
					+ "  \"Parameters\": {"
					+ "  \"Id\": \""+verificationID+"\","
					+ "    \"SearchString\": null"
					+ "  },"
					+ "  \"pageSize\": 1000"
					+ "}";

			Response updateFormStatusResponse = apiUtils.submitRequest(METHOD_TYPE.POST, getFormDetailsJSON, headers,
					GetVerificationDetails1URL);			

			MultipleDataResponse response = gson.fromJson(updateFormStatusResponse.asString(),
					MultipleDataResponse.class);

			if(response.Data==null) {
				logError("Failed to get verification details");
				return null;
			}

			String propertyValue = null;
			switch (property.toUpperCase()) {
			case "ID":
				for (int i = 0; i < response.Data.Rows.size(); i++) {
					if (response.Data.Rows.get(i).Name.equalsIgnoreCase(formName)) {
						propertyValue = response.Data.Rows.get(i).ID;
						break;
					}
				}
				break;

			case "ISENABLED":
				for (int i = 0; i < response.Data.Rows.size(); i++) {
					if (response.Data.Rows.get(i).Name.equalsIgnoreCase(formName)) {
						propertyValue = response.Data.Rows.get(i).IsEnabled.toString();
						break;
					}
				}
				break;

			default:
				break;
			}

			logInfo("Got '"+property+"' value -> "+propertyValue+" for form -> "+formName);
			return propertyValue;

		} catch (Exception e) {
			logError("Failed to get verification details");
			return null;
		}
	}



	/**
	 * This method is used to get form details
	 * @author pashine_a
	 * @param formName & property
	 * @return String. It will return value of provided property
	 */
	public List<String> getFormDetails(FormDetails formDetails) {
		try {
			String getFormDetailsJSON = null;
			List<String> fieldIDs = new ArrayList<>();

			ApiUtils apiUtils = new ApiUtils();
			Gson gson = new GsonBuilder().create();

			Map<String, String> headers = new HashMap<String, String>();
			headers.put("Content-Type", "application/json");
			headers.put("Accept", "application/json, text/plain, /");
			headers.put("Authorization", "Bearer " + accessToken);

			getFormDetailsJSON = "{"
					+ "  \"FormId\": \""+formDetails.FormId+"\","
					+ "  \"Resource\": {"
					+ "    \"Id\": \""+formDetails.ResourceID+"\","
					+ "    \"Name\": \""+formDetails.ResourceName+"\""
					+ "  }"
					+ "}";

			Response updateFormStatusResponse = apiUtils.submitRequest(METHOD_TYPE.POST, getFormDetailsJSON, headers,
					GetFormDataURL);			

			MultipleDataResponse response = gson.fromJson(updateFormStatusResponse.asString(),
					MultipleDataResponse.class);

			if(response.Data==null) {
				logError("Failed to get form details");
				return null;
			}

			for (int i = 0; i <formDetails.FieldNames.size(); i++) {
				for (int j = 0; j < response.Data.FormDetails.size(); j++) {
					if (formDetails.FieldNames.get(i).equalsIgnoreCase(response.Data.FormDetails.get(j).Name)) {
						fieldIDs.add(response.Data.FormDetails.get(j).Id);
						break;
					}
				}
			}

			logInfo("Got field IDs");

			return fieldIDs;

		} catch (Exception e) {
			logError("Failed to get form details");
			return null;
		}
	}

	/**
	 * This method is used to save chart for form with the provided details
	 * @author pashine_a
	 * @param chartConfig
	 * @return boolean. It will return boolean result true if chart is associated with the form(s)
	 */
	public boolean linkChartToForm(ChartConfig chartConfig) {
		try {

			String associateNewChartJSON = null;
			ApiUtils apiUtils = new ApiUtils();
			Gson gson = new GsonBuilder().create();

			Map<String, String> headers = new HashMap<String, String>();
			headers.put("Content-Type", "application/json");
			headers.put("Accept", "application/json, text/plain, /");
			headers.put("Authorization", "Bearer " + accessToken);

			associateNewChartJSON = "{"
					+ "  \"FormEntityId\": \""+chartConfig.FormEntityID+"\","
					+ "  \"ChartType\": {"
					+ "    \"Id\": \""+chartConfig.ChartTypeID+"\","
					+ "    \"Name\": \""+chartConfig.ChartTypeName+"\""
					+ "  },"
					+ "  \"ChartTemplate\": {"
					+ "    \"Id\": \""+chartConfig.ChartTemplateID+"\","
					+ "    \"Name\": \""+chartConfig.ChartTemplateName+"\""
					+ "  },"
					+ "  \"ConfigurationValues\": ["
					+ "    {"
					+ "      \"Contents\": ["
					+ "        {"
					+ "          \"Name\": \"Fields\","
					+ "          \"Value\": ["
					+ getFieldIDs(chartConfig.FieldIDs)
					+ "          ]"
					+ "        }"
					+ "      ]"
					+ "    }"
					+ "  ],"
					+ "  \"ControlLimitConfig\": {"
					+ "    \"ShowMean\": true,"
					+ "    \"ShowVariation\": true,"
					+ "    \"ShowSampleSize\": false"
					+ "  },"
					+ "  \"IdentifierFilterName1\": \""+chartConfig.IdentifierFilterName1+"\","
					+ "  \"IdentifierFilterValue1\": \""+chartConfig.IdentifierFilterValue1+"\","
					+ "  \"IdentifierFilterName2\": \""+chartConfig.IdentifierFilterName2+"\","
					+ "  \"IdentifierFilterValue2\": \""+chartConfig.IdentifierFilterValue2+"\","
					+ "  \"Created\": {"
					+ "    \"User\": {"
					+ "      \"DefaultLocation\": \"00000000-0000-0000-0000-000000000000\","
					+ "    }"
					+ "  },"
					+ "  \"PropertyValues\": null,"
					+ "  \"ResourceConfigs\": ["
					+ "    {"
					+ "      \"LocationId\": \""+chartConfig.LocationID+"\","
					+ "      \"ControlLimit\": {"
					+ "        \"Default\": {"
					+ "          \"Mean\":\""+chartConfig.DefaultMean+"\","
					+ "          \"Variation\":\""+chartConfig.DefaultVariation+"\","
					+ "          \"Comment\":\""+chartConfig.DefaultComment+"\","
					+ "          \"SampleSize\":\""+chartConfig.DefaultSampleSize+"\""
					+ "        },"
					+ "        \"ControlLimitColl\": ["
					+ getResourceDetails(chartConfig)
					+ "        ]"
					+ "      }"
					+ "    }"
					+ "  ],"
					+ "  \"Name\": \""+chartConfig.FormChartLinkName+"\""
					+ "}";

			Response addNewChartResponse = apiUtils.submitRequest(METHOD_TYPE.POST, associateNewChartJSON, headers,
					SaveFormsChartRelationURL);			

			SimpleDataResponse response = gson.fromJson(addNewChartResponse.asString(),
					SimpleDataResponse.class);

			if(!response.Status) {
				logError("Failed to add chart for form");
				return false;
			}

			logInfo("Added chart for forms");
			return true;

		} catch (Exception e) {
			logInfo("Added chart for forms");
			return true;
		}
	}


	/**
	 * This method is used to get items in payload
	 * @author pashine_a
	 * @param formIDs
	 * @return String. It will return payload having form IDs
	 */
	public String getFormIDs(List<String> formIDs) {
		String formsJSON = "[";
		for(int i=0;i<formIDs.size();i++) {
			if (i==formIDs.size()-1) {
				formsJSON = formsJSON + "\"" +formIDs.get(i) + "\"" + "]";
				break;
			}
			formsJSON = formsJSON + "\"" +formIDs.get(i)+ "\"" +",";
		}
		return formsJSON;
	}


	/**
	 * This method is used to get fieldIDs in payload
	 * @author pashine_a
	 * @param fieldIDs
	 * @return String. It will return payload having field IDs
	 */
	public String getFieldIDs(List<String> fieldIDs) {

		String fieldsJSON = "";

		for(int i=0;i<fieldIDs.size();i++) {

			fieldsJSON = fieldsJSON + "{"
					+ "              \"Id\": \""+fieldIDs.get(i)+"\""
					+ "            }";

			if(i!=fieldIDs.size()-1) {
				fieldsJSON = fieldsJSON + ",";
			}
		}

		return fieldsJSON;
	}

	/**
	 * This method is used to get items in payload
	 * @author pashine_a
	 * @param formIDs
	 * @return String. It will return payload having form IDs
	 */
	public String getResourceDetails(ChartConfig chartConfig) {
		String resourceName = null, resourceID = null; 
		String resourceDetailsJson = "";
		int i=0;

		for (Map.Entry<String, String> entry : chartConfig.ResourceDetails.entrySet()) {

			resourceName = entry.getKey();
			resourceID = entry.getValue();

			resourceDetailsJson = resourceDetailsJson + "{"
					+ "            \"Resource\": {"
					+ "              \"Id\": \""+resourceName+"\","
					+ "              \"Name\": \""+resourceID+"\""
					+ "            },"
					+ "            \"IsDefault\": "+chartConfig.DefaultResCheck+","
					+ "            \"IsEnabled\": true,"
					+ "            \"Mean\": \""+chartConfig.Mean+"\","
					+ "            \"Variation\": \""+chartConfig.Variation+"\","
					+ "            \"SampleSize\":\""+chartConfig.SampleSize+"\","
					+ "            \"Comment\": \""+chartConfig.Comment+"\""
					+ "          }";
			i++;
			if(i!=chartConfig.ResourceDetails.size()) {
				resourceDetailsJson = resourceDetailsJson + ",";
			}
		}
		return resourceDetailsJson;
	}


	public class MultipleDataResponse {		
		public Data Data;
		public List<Data> DataList;
	}

	public class SingleDataResponse {
		public Data Data;
		public boolean Status;
	}

	public class Data {
		public String Name;
		public List<Rows> Rows;
		public List<FormDetails> FormDetails;

	}

	public class Rows {
		public String Id;
		public String ID;
		public String Name;
		public String IsEnabled;
	}

	public static class FormDetails {

		public String FormId;
		public String ResourceID;
		public String ResourceName;

		public List<String> FieldNames;
		public List<String> FieldIDs;

		//API - Fields
		public String Id;
		public String Name;

	}

	public static class ChartConfig {

		public String FormEntityID;
		public String ChartTypeID;
		public String ChartTypeName;
		public String ChartTemplateID;
		public String ChartTemplateName;
		public String FormChartLinkName;

		public List<String> FieldIDs;
		public String IdentifierFilterName1 = "";
		public String IdentifierFilterValue1 = "";
		public String IdentifierFilterName2 = "";
		public String IdentifierFilterValue2 = "";
		public String LocationID;
		public HashMap<String, String> ResourceDetails;
		public String Mean;
		public String Variation;
		public String SampleSize;
		public String Comment = "";
		public boolean DefaultResCheck = false;
		public String DefaultMean;
		public String DefaultVariation;
		public String DefaultSampleSize;
		public String DefaultComment = "";

	}
	
	public class SimpleDataResponse{
		public boolean Status;
	}


}
