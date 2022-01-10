package com.project.safetychain.api.models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.project.safetychain.api.models.support.Support.ChartBuilder;
import com.project.safetychain.api.models.support.Support.ChartResources;
import com.project.testbase.TestValidation;
import com.project.utilities.ApiUtils;
import com.project.utilities.ApiUtils.METHOD_TYPE;

import io.restassured.response.Response;

public class ChartBuilderFlow extends Auth {

	public String saveChartUrl = baseURI + ChartBuilderUrls.SAVE_CHART_URL;
	public String saveFormsChartRelationUrl = baseURI + ChartBuilderUrls.SAVE_FORMS_CHART_RELATION_URL;
	public String getAllChartTypeUrl = baseURI + ChartBuilderUrls.GET_ALL_CHART_TYPE_URL;

	ApiUtils apiUtils = new ApiUtils();
	Gson gson = new GsonBuilder().create();
	Random rand = new Random();

	public String getChartId(String chartName) {
		String chartId = null;
		apiUtils = new ApiUtils();

		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("Accept", "application/json, text/plain, /");
		headers.put("Authorization", "Bearer " + accessToken);

		Gson gson = new GsonBuilder().create();
		try {

			Response getChartListResponse = apiUtils.submitRequest(METHOD_TYPE.GET, null, headers, getAllChartTypeUrl);
			MultipleDataResponse sdr = gson.fromJson(getChartListResponse.asString(), MultipleDataResponse.class);

			for (int i = 0; i < sdr.Data.size(); i++) {
				System.out.println(" sdr.Data.size()" + sdr.Data.size());
				if (sdr.Data.get(i).Name.equalsIgnoreCase(chartName)) {
					chartId = sdr.Data.get(i).Id;
					logInfo("Short Name= " + chartId);
					return chartId;
				}

			}
			return chartId;

		} catch (Exception e) {
			logInfo("Unable to fetch chartId");
			return chartId;
		}
	}

	public String createChart(ChartBuilder cb) {
		String chartId = null;
		String fields = null;
		String CreatedchartId = null;
		apiUtils = new ApiUtils();

		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("Accept", "application/json, text/plain, /");
		headers.put("Authorization", "Bearer " + accessToken);
		Gson gson = new GsonBuilder().create();
		chartId = getChartId(cb.chartTemplateType);
		fields = appendFormElements(cb.chartConfList);
		try {

			String addCatJson = "{\r\n" + "    \"ChartType\": {\r\n" + "        \"Id\": \"" + chartId + "\",\r\n"
					+ "        \"Name\": \"" + cb.chartTemplateType + "\"\r\n" + "    },\r\n"
					+ "    \"PropertyValues\": [\r\n" + "        {\r\n" + "            \"ControlType\": \"Group\",\r\n"
					+ "            \"DisplayName\": \"Chart Run Rules\",\r\n" + "            \"Contents\": [\r\n"
					+ fields + "            ]\r\n" + "        }\r\n" + "    ],\r\n" + "    \"Id\": null,\r\n"
					+ "    \"Name\": \"" + cb.Name + "\",\r\n" + "    \"IsEnable\": " + cb.IsEnable + "\r\n" + "}";
			Response response = apiUtils.submitRequest(METHOD_TYPE.POST, addCatJson, headers, saveChartUrl);

			SimpleDataResponse sdr = gson.fromJson(response.asString(), SimpleDataResponse.class);
			CreatedchartId = sdr.Data;
			TestValidation.IsTrue((sdr.Status), "Chart  is created", "Could NOT create Chart ");

			return CreatedchartId;

		} catch (Exception e) {
			logInfo("Unable to create Chart chartId");
			return CreatedchartId;

		}
	}

	public String appendFormElements(List<String> listOfElements) {
		String addField = "";

		try {
			for (int i = 0; i < listOfElements.size(); i++) {
				if (i != (listOfElements.size() - 1)) {
					addField += updateFormElements(listOfElements.get(i), false);
				} else {
					addField += updateFormElements(listOfElements.get(i), true);
				}
			}
			return addField;
		} catch (Exception e) {
			logError("Failed to set form element for - " + e.getMessage());
			return addField;
		}
	}

	public String updateFormElements(String Chart_Rule, boolean lastField) {
		String addField = null;

		try {

			addField = "{\r\n" + "\"Name\": \"" + Chart_Rule + "\",\r\n" + "\"Value\": true\r\n" + "}";

			if (!lastField) {

				addField += "                ,\r\n";
			} else {
				addField += "                \r\n";
			}

			return addField;
		} catch (Exception e) {
			logError("Failed to set form element for - " + Chart_Rule + e.getMessage());
			return addField;
		}
	}

	public boolean linkChartWithForm(ChartBuilder cb, String ChartTemplateId) {
		String chartTypeId = null;
		String resourceList = null;
		String NumericfieldList = null;
		apiUtils = new ApiUtils();

		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("Accept", "application/json, text/plain, /");
		headers.put("Authorization", "Bearer " + accessToken);
		Gson gson = new GsonBuilder().create();
		chartTypeId = getChartId(cb.chartTemplateType);
		resourceList = appendResourceElements(cb.chartResourceList);
		NumericfieldList = appendFieldElements(cb.ChartFieldsList, cb);
		try {

			String addCatJson = "{\r\n" + "    \"FormEntityId\": \"" + cb.formId + "\",\r\n"
					+ "    \"ChartType\": {\r\n" + "        \"Id\": \"" + chartTypeId + "\",\r\n"
					+ "        \"Name\": \"" + cb.chartTemplateType + "\"\r\n" + "    },\r\n"
					+ "    \"ChartTemplate\": {\r\n" + "       \"Id\": \"" + ChartTemplateId + "\",\r\n"
					+ "        \"Name\": \"" + cb.Name + "\"\r\n" + "    },\r\n" + "    \"Properties\": [\r\n"
					+ "   \r\n" + "    ],\r\n" + "    \"ConfigurationValues\": [\r\n" + "        {\r\n"
					+ "            \"Id\": \"d9841aac-81e1-43c9-addc-168067fb314b\",\r\n"
					+ "            \"ControlType\": \"Group\",\r\n"
					+ "            \"DisplayName\": \"Apply Aggregate Field(s)\",\r\n"
					+ "            \"Contents\": [\r\n" + "                {\r\n"
					+ "                    \"Name\": \"Fields\",\r\n" + "                    \"DisplayName\": \"\",\r\n"
					+ "                    \"Hint\": null,\r\n"
					+ "                    \"ControlType\": \"CheckboxList\",\r\n"
					+ "                    \"DataType\": null,                   \r\n"
					+ "                    \"IsRequired\": false,\r\n" + "                    \"Dependents\": null,\r\n"
					+ "                    \"Value\": [\r\n" + NumericfieldList + "                    ]\r\n"
					+ "                }\r\n" + "            ]\r\n" + "        }\r\n" + "    ],\r\n"
					+ "    \"ControlLimitConfig\": {\r\n" + "        \"ShowMean\": true,\r\n"
					+ "        \"ShowVariation\": true,\r\n" + "        \"ShowSampleSize\": false\r\n" + "    },\r\n"
					+ "    \"Filters\": null,\r\n" + "    \"ControlLimitField\": null,\r\n"
					+ "    \"IdentifierFilterName1\": \""+cb.IdentifierFilterName1+"\",\r\n" + "    \"IdentifierFilterValue1\": \""+cb.IdentifierFilterValue1+"\",\r\n"
					+ "    \"IdentifierFilterName2\": \""+cb.IdentifierFilterName2+"\",\r\n" + "    \"IdentifierFilterValue2\": \""+cb.IdentifierFilterValue2+"\",\r\n"
					+ "    \"Created\": {\r\n" + "        \"Date\": \"2021-03-02T07:28:43.2570000Z\",\r\n"
					+ "        \"User\": { \r\n" + "            \"UserType\": \"TenantUser\", \r\n"
					+ "            \"Id\": \"6fdc9383-e117-4a5b-8c9f-90e885793f7c\",\r\n"
					+ "            \"Name\": \"Auto User\"\r\n" + "        }\r\n" + "    },\r\n"
					+ "    \"Modified\": {\r\n" + "        \"Date\": \"2021-03-02T07:28:49.8670000Z\",\r\n"
					+ "        \"User\": {          \r\n" + "            \"UserType\": \"TenantUser\",\r\n"
					+ "            \"Id\": \"6fdc9383-e117-4a5b-8c9f-90e885793f7c\",\r\n"
					+ "            \"Name\": \"Auto User\"\r\n" + "        }\r\n" + "    },\r\n"
					+ "    \"PropertyValues\": null,\r\n" + "    \"ResourceConfigs\": [\r\n" + "        {\r\n"
					+ "            \"LocationId\": \"" + cb.locationId + "\",\r\n"
					+ "            \"ControlLimit\": {\r\n" + "                \"Default\": {\r\n"
					+ "                    \"Mean\": " + cb.defaultChart.Mean + ",\r\n"
					+ "                    \"Variation\": " + cb.defaultChart.Variation + ",\r\n"
					+ "                    \"Comment\": " + cb.defaultChart.Comment + ",\r\n"
					+ "                    \"IsDefault\": " + cb.defaultChart.IsDefault + "\r\n"
					+ "                },\r\n" + "                \"ControlLimitColl\": [\r\n" + resourceList
					+ "                ]\r\n" + "            }\r\n" + "        }\r\n" + "    ],\r\n"
					+ "    \"IsEnabled\": false,\r\n" + "    \"IsEnable\": true,  \r\n" + "    \"Name\": \""
					+ cb.chartLinkingName + "\"\r\n" + "}";
			Response response = apiUtils.submitRequest(METHOD_TYPE.POST, addCatJson, headers,
					saveFormsChartRelationUrl);

			SimpleDataResponse sdr = gson.fromJson(response.asString(), SimpleDataResponse.class);

			TestValidation.IsTrue((sdr.Status), "Chart  is created", "Could NOT create Chart ");

			return true;

		} catch (Exception e) {
			logInfo("Unable to create Chart chartId");
			return false;

		}
	}

	public String appendResourceElements(List<ChartResources> listOfElements) {
		String addField = "";

		try {
			for (int i = 0; i < listOfElements.size(); i++) {
				if (i != (listOfElements.size() - 1)) {
					addField += updateresourceElements(listOfElements.get(i), false);
				} else {
					addField += updateresourceElements(listOfElements.get(i), true);
				}
			}
			return addField;
		} catch (Exception e) {
			logError("Failed to set form element for - " + e.getMessage());
			return addField;
		}
	}

	public String updateresourceElements(ChartResources ccr, boolean lastField) {
		String addField = null;

		try {

			addField = "{\r\n" + "                     \r\n" + "                        \"Resource\": {\r\n"
					+ "                            \"Id\": \"" + ccr.ResourceId + "\",\r\n"
					+ "                            \"Name\": \"" + ccr.ResourceName + "\"\r\n"
					+ "                        },\r\n" + "                        \"IsDefault\": " + ccr.IsDefault
					+ ",\r\n" + "                        \"IsEnabled\": true,\r\n"
					+ "                        \"Mean\": " + ccr.Mean + ",\r\n"
					+ "                        \"Variation\": " + ccr.Variation + ",\r\n"
					+ "                        \"SampleSize\": " + ccr.SampleSize + ",\r\n"
					+ "                        \"Comment\": " + ccr.Comment + ",\r\n"
					+ "                        \"Modified\": null,\r\n"
					+ "                        \"isRowDisabled\": false,\r\n"
					+ "                        \"IsDefault-scsui\": true,\r\n"
					+ "                        \"isRowSelected\": true\r\n" + "}";

			if (!lastField) {

				addField += "                ,\r\n";
			} else {
				addField += "                \r\n";
			}

			return addField;
		} catch (Exception e) {
			logError("Failed to set form element for - " + ccr.ResourceName + e.getMessage());
			return addField;
		}
	}

	public String appendFieldElements(List<String> listOfElements, ChartBuilder cb) {
		String addField = "";

		try {
			for (int i = 0; i < listOfElements.size(); i++) {
				if (i != (listOfElements.size() - 1)) {
					addField += updateFieldElements(listOfElements.get(i), cb, false);
				} else {
					addField += updateFieldElements(listOfElements.get(i), cb, true);
				}
			}
			return addField;
		} catch (Exception e) {
			logError("Failed to set form element for - " + e.getMessage());
			return addField;
		}
	}

	public String updateFieldElements(String fieldName, ChartBuilder cb, boolean lastField) {
		String addField = null;
		HashMap<String, String> fieldDetails = new HashMap<String, String>();

		fieldDetails = getFieldDetailsUsingGetFormDefinition(cb.formName, fieldName);
		String fieldId = fieldDetails.get("fieldId");
		String fieldShortName = fieldDetails.get("shortName");

		try {

			addField = "{ \"Id\": \"" + fieldId + "\",\r\n" + "\"ShortName\": \"" + fieldShortName + "\",\r\n"
					+ "\"Value\": \"true\" }";

			if (!lastField) {

				addField += "                ,\r\n";
			} else {
				addField += "                \r\n";
			}

			return addField;
		} catch (Exception e) {
			logError("Failed to set form element for - " + fieldName + e.getMessage());
			return addField;
		}
	}

	public HashMap<String, String> getFieldDetails(String formName, String fieldName) {
		String FieldId = null;
		String shortName = null;
		HashMap<String, String> fieldDetails = new HashMap<String, String>();
		apiUtils = new ApiUtils();
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("Accept", "application/json, text/plain, /");
		headers.put("Authorization", "Bearer " + accessToken);
		Gson gson = new GsonBuilder().create();
		FormDesigner formDesigner = new FormDesigner();
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
					FieldId = sdr.Data.Forms.get(0).FormData.FormDetails.Fields.get(i).Id;
					shortName = sdr.Data.Forms.get(0).FormData.FormDetails.Fields.get(i).ShortName;
					logInfo("Field Id= " + FieldId);

					fieldDetails.put("fieldId", FieldId);
					fieldDetails.put("shortName", shortName);
					return fieldDetails;
				}

			}
			return fieldDetails;

		} catch (Exception e) {
			logInfo("Unable to fetch field If");
			return fieldDetails;
		}
	}

	public HashMap<String, String> getFieldDetailsUsingGetFormDefinition(String formName, String fieldName) {
		String shortName = "";
		String Id = "";
		HashMap<String, String> fieldDetails = new HashMap<String, String>();
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
			if (sdr.Data.get(0).Fields != null) {

				logInfo("Field Values = " + sdr.Data.get(0).Fields);

				for (int i = 0; i < sdr.Data.get(0).Fields.size(); i++) {
					System.out.println(" sdr.Data.Fields.size()" + sdr.Data.get(0).Fields.size());

					if (sdr.Data.get(0).Fields.get(i).Name.equalsIgnoreCase(fieldName)) {
						shortName = sdr.Data.get(0).Fields.get(i).ShortName;
						Id = sdr.Data.get(0).Fields.get(i).Id;
						logInfo("Short Name= " + shortName);
						logInfo("Id= " + Id);
						fieldDetails.put("fieldId", Id);
						fieldDetails.put("shortName", shortName);
						return fieldDetails;
					}

					if (sdr.Data.get(0).Fields.get(i).Fields != null)

					{

						if (sdr.Data.get(0).Fields.get(i).Fields.size() != 0)

						{

							for (int j = 0; j < sdr.Data.get(0).Fields.get(i).Fields.size(); j++) {

								if (sdr.Data.get(0).Fields.get(i).Fields.get(j).Name.equalsIgnoreCase(fieldName)) {
									shortName = sdr.Data.get(0).Fields.get(i).Fields.get(j).ShortName;
									Id = sdr.Data.get(0).Fields.get(i).Fields.get(j).Id;
									logInfo("Short Name= " + shortName);
									logInfo("Id = " + Id);
									fieldDetails.put("fieldId", Id);
									fieldDetails.put("shortName", shortName);
									return fieldDetails;
								}
								if (sdr.Data.get(0).Fields.get(i).Fields.get(j).Fields != null) {

									for (int k = 0; k < sdr.Data.get(0).Fields.get(i).Fields.get(j).Fields
											.size(); k++) {

										if (sdr.Data.get(0).Fields.get(i).Fields.get(j).Fields.get(k).Name
												.equalsIgnoreCase(fieldName)) {
											shortName = sdr.Data.get(0).Fields.get(i).Fields.get(j).Fields
													.get(k).ShortName;
											Id = sdr.Data.get(0).Fields.get(i).Fields.get(j).Fields.get(k).Id;
											logInfo("Short Name= " + shortName);
											logInfo("Id = " + Id);
											fieldDetails.put("fieldId", Id);
											fieldDetails.put("shortName", shortName);
											return fieldDetails;
										}

									}

								}
							}

						}

					}

				}
			}

			return fieldDetails;
		} catch (Exception e) {
			logInfo("Unable to fetch field Short Name");
			return fieldDetails;
		}
	}


	public String getFieldShortName(String formName, String fieldName) {
		apiUtils = new ApiUtils();
		FormDesigner formDesigner = new FormDesigner();

		String shortName = "";		

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
			if (sdr.Data.get(0).Fields != null) {
				for (int i = 0; i < sdr.Data.get(0).Fields.size(); i++) {
					if (sdr.Data.get(0).Fields.get(i).Name.equalsIgnoreCase(fieldName)) {
						shortName = sdr.Data.get(0).Fields.get(i).ShortName;
						return shortName;
					}
					if (sdr.Data.get(0).Fields.get(i).Fields != null){
						if (sdr.Data.get(0).Fields.get(i).Fields.size() != 0){
							for (int j = 0; j < sdr.Data.get(0).Fields.get(i).Fields.size(); j++) {
								if (sdr.Data.get(0).Fields.get(i).Fields.get(j).Name.equalsIgnoreCase(fieldName)) {
									shortName = sdr.Data.get(0).Fields.get(i).Fields.get(j).ShortName;
									return shortName;
								}
								if (sdr.Data.get(0).Fields.get(i).Fields.get(j).Fields != null) {
									for (int k = 0; k < sdr.Data.get(0).Fields.get(i).Fields.get(j).Fields
											.size(); k++) {
										if (sdr.Data.get(0).Fields.get(i).Fields.get(j).Fields.get(k).Name
												.equalsIgnoreCase(fieldName)) {
											shortName = sdr.Data.get(0).Fields.get(i).Fields.get(j).Fields
													.get(k).ShortName;
											return shortName;
										}
									}

								}
							}
						}
					}
				}
			}
			return shortName;
		} catch (Exception e) {
			logInfo("Unable to fetch field Short Name");
			return null;
		}
	}


}