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

public class FSQABrowserForms extends Auth{

	public String submitForm = baseURI + FSQABrowserFormsURLs.SUBMIT_RECORD;
	public String getForms = baseURI + FSQABrowserFormsURLs.GET_FORMS;
	public String getSavedForms = baseURI + FSQABrowserFormsURLs.GET_SAVED_FORMS;
	public String SaveForm = baseURI + FSQABrowserFormsURLs.SAVE_FORM_WIP;
	public String GetFormData = baseURI + FSQABrowserFormsURLs.GET_FORM_DATA;
	public String getForm =  baseURI + FSQABrowserFormsURLs.GET_FORM;
	public String addQuestForm = baseURI + FSQABrowserFormsURLs.ADD_QUEST_FORM;
	public String AddFormTask = baseURI + FSQABrowserFormsURLs.ADD_FORM_TASK_URL;

	ApiUtils apiUtils = new ApiUtils();
	Gson gson = new GsonBuilder().create();

	public String formrefID = null;
	public String formTypeID = null;


	/**
	 * This method is used to get formId
	 * @author choubey_a
	 * @param formname
	 * @param resourceTypeId - ID of the resource type in which form is created
	 * @return formID
	 */

	public String getForms(String formName, String resourceTypeId) {

		apiUtils = new ApiUtils();

		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("Accept", "application/json, text/plain, /");
		headers.put("Authorization", "Bearer " + accessToken);
		Gson gson = new GsonBuilder().create();

		try {

			String request = "{\r\n" + 
					"   \"take\":100,\r\n" + 
					"   \"skip\":0,\r\n" + 
					"   \"page\":1,\r\n" + 
					"   \"pageSize\":100,\r\n" + 
					"   \"filter\":{\r\n" + 
					"      \"logic\":\"and\",\r\n" + 
					"      \"filters\":[\r\n" + 
					"         {\r\n" + 
					"            \"field\":\"Name\",\r\n" + 
					"            \"operator\":\"contains\",\r\n" + 
					"            \"value\":\""+formName+"\"\r\n" + 
					"         }\r\n" + 
					"      ]\r\n" + 
					"   },\r\n" + 
					"   \"Parameters\":{\r\n" + 
					"      \"Node\":{\r\n" + 
					"         \"Type\":\""+resourceTypeId+"\",\r\n" + 
					"         \"Node\":\""+resourceTypeId+"\",\r\n" + 
					"         \"IsParent\":true\r\n" + 
					"      },\r\n" + 
					"      \"FromDate\":\"\",\r\n" + 
					"      \"ToDate\":\"\",\r\n" + 
					"      \"Resource\":[\r\n" + 
					"         \r\n" + 
					"      ],\r\n" + 
					"      \"FormName\":[\r\n" + 
					"         \r\n" + 
					"      ],\r\n" + 
					"      \"FormType\":[\r\n" + 
					"         \r\n" + 
					"      ],\r\n" + 
					"      \"Program\":[\r\n" + 
					"         \r\n" + 
					"      ],\r\n" + 
					"      \"ModifiedBy\":[\r\n" + 
					"         \r\n" + 
					"      ],\r\n" + 
					"      \"FirstRowId\":null,\r\n" + 
					"      \"LastRowId\":null\r\n" + 
					"   },\r\n" + 
					"   \"FirstRowId\":null,\r\n" + 
					"   \"LastRowId\":null\r\n" + 
					"}";

			Response response = apiUtils.submitRequest(METHOD_TYPE.POST, request, headers, getForms);
			SingleDataResponse sdr = gson.fromJson(response.asString(), SingleDataResponse.class);
			for(int i=0;i< sdr.Data.Rows.size();i++) {
				if(sdr.Data.Rows.get(i).Name.equals(formName)) {
					formrefID = sdr.Data.Rows.get(i).ID;
				}
			}
			return formrefID;
		} catch (Exception e) {
			logInfo("Unable to get Form Id -"+e.getMessage());
			return null;
		}
	}

	/**
	 * This method is used to get formrefId
	 * @author choubey_a
	 * @param formId
	 * @return formrefID
	 */

	public String getForm(String formId) {

		apiUtils = new ApiUtils();

		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("Accept", "application/json, text/plain, /");
		headers.put("Authorization", "Bearer " + accessToken);
		Gson gson = new GsonBuilder().create();

		try {

			String request = "{\"Id\":\""+formId+"\"}";

			Response response = apiUtils.submitRequest(METHOD_TYPE.POST, request, headers, getForm);
			SingleDataResponse sdr = gson.fromJson(response.asString(), SingleDataResponse.class);

			formrefID = sdr.Data.FormId;

			return formrefID;
		} catch (Exception e) {
			logInfo("Unable to get Form Id -"+e.getMessage());
			return null;
		}
	}

	/**
	 * This method is used to get formData
	 * @author choubey_a
	 * @param formId,resourceinstanceId and resourceInstanceName
	 * @return list of details of the form data
	 */

	public List<FormData> getFormData(String formId, String resourceId, String resourceName) {

		apiUtils = new ApiUtils();
		List<FormData> data = new ArrayList<>();

		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("Accept", "application/json, text/plain, /");
		headers.put("Authorization", "Bearer " + accessToken);
		Gson gson = new GsonBuilder().create();

		try {

			String request = "{\"FormId\":\""+formId+"\","
					+ "\"Resource\":{\"Id\":\""+resourceId+"\",\"Name\":\""+resourceName+"\"}}";

			Response response = apiUtils.submitRequest(METHOD_TYPE.POST, request, headers, GetFormData);
			SingleDataResponse sdr = gson.fromJson(response.asString(), SingleDataResponse.class);			
			formTypeID = sdr.Data.ID;
			for(int i=0;i< sdr.Data.FormDetails.size();i++) {
				FormData fd1 = new FormData();
				fd1.fieldId = sdr.Data.FormDetails.get(i).Id;
				fd1.fieldname = sdr.Data.FormDetails.get(i).Name;				
				fd1.shortname = sdr.Data.FormDetails.get(i).ShortName;
				fd1.Type = sdr.Data.FormDetails.get(i).Type;
				try {
					fd1.Options = new ArrayList<Options>();					
					for(int j=0;j< sdr.Data.FormDetails.get(i).Options.size();j++) {
						Options fieldOptions = new Options();
						fieldOptions.Id = sdr.Data.FormDetails.get(i).Options.get(j).Id;
						fieldOptions.Name = sdr.Data.FormDetails.get(i).Options.get(j).Name;
						fd1.Options.add(fieldOptions);
					}
				}catch(Exception e) {
					logInfo("Options are not available -"+e.getMessage());
				}
				data.add(fd1);				
			}
			return data;
		} catch (Exception e) {
			logInfo("Unable to get Form Data -"+e.getMessage());
			return null;
		}
	}

	public List<FormData> getSectionFormData(String formId, String resourceId, String resourceName) {

		apiUtils = new ApiUtils();
		List<FormData> data = new ArrayList<>();

		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("Accept", "application/json, text/plain, /");
		headers.put("Authorization", "Bearer " + accessToken);
		Gson gson = new GsonBuilder().create();

		try {

			String request = "{\"FormId\":\""+formId+"\","
					+ "\"Resource\":{\"Id\":\""+resourceId+"\",\"Name\":\""+resourceName+"\"}}";

			Response response = apiUtils.submitRequest(METHOD_TYPE.POST, request, headers, GetFormData);
			SimpleDataResponse1 sdr = gson.fromJson(response.asString(), SimpleDataResponse1.class);			
			formTypeID = sdr.Data.ID;

			for(int i=0;i< sdr.Data.FormDetails.size();i++) {	
				FormData fd1 = new FormData();
				for(int j=0;j< sdr.Data.FormDetails.get(i).SectionElements.size();j++) {	
					fd1 = new FormData();
					fd1.sectionFieldId = sdr.Data.FormDetails.get(i).Id;
					fd1.sectionName= sdr.Data.FormDetails.get(i).Name;
					fd1.sectionShortname = sdr.Data.FormDetails.get(i).ShortName;
					fd1.fieldId = sdr.Data.FormDetails.get(i).SectionElements.get(j).Id;
					fd1.fieldname = sdr.Data.FormDetails.get(i).SectionElements.get(j).Name;		
					fd1.shortname = sdr.Data.FormDetails.get(i).SectionElements.get(j).ShortName;
					fd1.Type = sdr.Data.FormDetails.get(i).SectionElements.get(j).Type;
					try {
						fd1.Options = new ArrayList<Options>();					
						for(int k=0;k< sdr.Data.FormDetails.get(i).SectionElements.get(j).Options.size();k++) {
							Options fieldOptions = new Options();
							fieldOptions.Id = sdr.Data.FormDetails.get(i).SectionElements.get(j).Options.get(k).Id;
							fieldOptions.Name = sdr.Data.FormDetails.get(i).SectionElements.get(j).Options.get(k).Name;
							fd1.Options.add(fieldOptions);
						}
					}catch(Exception e) {
						logInfo("Options are not available -"+e.getMessage());
					}
					data.add(fd1);
				}				
			}
			return data;
		} catch (Exception e) {
			logInfo("Unable to get Form Data -"+e.getMessage());
			return null;
		}
	}

	/**
	 * This method is used to get formTypeId
	 * @author choubey_a
	 * @param formId,resourceinstanceId and resourceInstanceName
	 * @return formTypeId
	 */

	public String getFormTypeId(String formId, String resourceId, String resourceName) {

		apiUtils = new ApiUtils();

		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("Accept", "application/json, text/plain, /");
		headers.put("Authorization", "Bearer " + accessToken);
		Gson gson = new GsonBuilder().create();

		try {

			String request = "{\"FormId\":\""+formId+"\","
					+ "\"Resource\":{\"Id\":\""+resourceId+"\",\"Name\":\""+resourceName+"\"}}";

			Response response = apiUtils.submitRequest(METHOD_TYPE.POST, request, headers, GetFormData);
			SingleDataResponse sdr = gson.fromJson(response.asString(), SingleDataResponse.class);			
			formTypeID = sdr.Data.ID;			
			return formTypeID;
		} catch (Exception e) {
			logInfo("Unable to get Form Type Id -"+e.getMessage());
			return null;
		}
	}

	/**
	 * This method is used to get numeric field body
	 * @author choubey_a
	 * @param value,shortname,fieldId and fieldName
	 * @return numeric body
	 */

	public String numericField(String value, String shortname,String fieldId, String fieldname) {
		String request = " {\r\n" + 
				"                \"AllowAttachments\": false,\r\n" + 
				"                \"AllowComments\": false,\r\n" + 
				"                \"AllowCorrection\": false,\r\n" + 
				"                \"Attachments\": [\r\n" + 
				"                ],\r\n" + 
				"                \"Comment\": null,\r\n" + 
				"                \"Configuration\": [\r\n" + 
				"                ],\r\n" + 
				"                \"DataSourceId\": \"00000000-0000-0000-0000-000000000000\",\r\n" + 
				"                \"DataSourceName\": null,\r\n" + 
				"                \"Default\": null,\r\n" + 
				"                \"DependencyRule\": null,\r\n" + 
				"                \"ElementType\": \"Field\",\r\n" + 
				"                \"ExpressionRule\": null,\r\n" + 
				"                \"FailsForm\": true,\r\n" + 
				"                \"GroupID\": \"00000000-0000-0000-0000-000000000000\",\r\n" + 
				"                \"Hint\": null,\r\n" + 
				"                \"Id\": \""+fieldId+"\",\r\n" + 
				"                \"IsFieldCompliant\": -2,\r\n" + 
				"                \"IsIntegrated\": false,\r\n" + 
				"                \"IsRequired\": true,\r\n" + 
				"                \"Name\": \""+fieldname+"\",\r\n" + 
				"                \"Order\": 0,\r\n" + 
				"                \"PredefinedCorrections\": null,\r\n" + 
				"                \"QuestionWeight\": null,\r\n" + 
				"                \"Repeat\": null,\r\n" + 
				"                \"RepeatField\": false,\r\n" + 
				"                \"ShortName\": \""+shortname+"\",\r\n" + 
				"                \"ShowField\": true,\r\n" + 
				"                \"ShowHint\": false,\r\n" + 
				"                \"ShowIsResolved\": false,\r\n" + 
				"                \"ShowMinMax\": true,\r\n" + 
				"                \"ShowOnCOA\": true,\r\n" + 
				"                \"ShowPointsEarned\": null,\r\n" + 
				"                \"ShowPointsPossible\": null,\r\n" + 
				"                \"ShowTarget\": false,\r\n" + 
				"                \"StepperTypeId\": null,\r\n" + 
				"                \"StepperTypeName\": null,\r\n" + 
				"                \"Type\": \"Numeric\",\r\n" + 
				"                \"Value\": \""+value+"\"\r\n" + 
				"            }\r\n";
		return request;
	}

	public String rejectednumericField(String value, String shortname,String fieldId, String fieldname) {
		String request = " {\r\n" + 
				"                \"AllowAttachments\": false,\r\n" + 
				"                \"AllowComments\": false,\r\n" + 
				"                \"AllowCorrection\": false,\r\n" + 
				"                \"Attachments\": [\r\n" + 
				"                ],\r\n" + 
				"                \"Comment\": null,\r\n" + 
				"                \"Configuration\": [\r\n" + 
				"                ],\r\n" + 
				"                \"DataSourceId\": \"ebc1926c-17ad-4c96-bfd4-a1c0f4bb404b\",\r\n" + 
				"                \"DataSourceName\": \"Numeric\",\r\n" + 
				"                \"DecimalPrecision\": null,\r\n" + 
				"                \"Default\": null,\r\n" + 
				"                \"DependencyRule\": null,\r\n" + 
				"                \"ElementType\": \"Field\",\r\n" + 
				"                \"ExpressionRule\": null,\r\n" + 
				"                \"FailsForm\": true,\r\n" + 
				"                \"GroupID\": \"00000000-0000-0000-0000-000000000000\",\r\n" + 
				"                \"Hint\": null,\r\n" + 
				"                \"Id\": \""+fieldId+"\",\r\n" + 
				"                \"IsFieldCompliant\": -2,\r\n" + 
				"                \"IsIntegrated\": false,\r\n" + 
				"                \"IsRequired\": true,\r\n" + 
				"                \"MaxValue\": null,\r\n" + 
				"                \"MinValue\": null,\r\n" + 
				"                \"Name\": \""+fieldname+"\",\r\n" + 
				"                \"Order\": 0,\r\n" + 
				"                \"PredefinedCorrections\": null,\r\n" + 
				"                \"QuestionWeight\": null,\r\n" + 
				"                \"Repeat\": null,\r\n" + 
				"                \"RepeatField\": false,\r\n" + 
				"                \"ShortName\": \""+shortname+"\",\r\n" + 
				"                \"ShowField\": true,\r\n" + 
				"                \"ShowFieldValidation\": null,\r\n" + 
				"                \"ShowHint\": false,\r\n" + 
				"                \"ShowIsResolved\": false,\r\n" + 
				"                \"ShowMinMax\": true,\r\n" + 
				"                \"ShowOnCOA\": true,\r\n" + 
				"                \"ShowPointsEarned\": null,\r\n" + 
				"                \"ShowPointsPossible\": null,\r\n" + 
				"                \"ShowSignificantDigit\": null,\r\n" + 
				"                \"ShowTarget\": false,\r\n" + 
				"                \"StepperTypeId\": \"ebc1926c-17ad-4c96-bfd4-a1c0f4bb404b\",\r\n" + 
				"                \"StepperTypeName\": \"Numeric\",\r\n" + 
				"                \"Type\": \"Numeric\",\r\n" + 
				"                \"Value\": "+value+"\r\n" + 
				"            }";
		return request;
	}

	/**
	 * This method is used to get free text field body
	 * @author choubey_a
	 * @param value,shortname,fieldId and fieldName
	 * @return free text body
	 */

	public String freeTextField(String value, String shortname, String fieldId, String fieldname) {
		String request = " {\r\n" + 
				"                \"AllowAttachments\": false,\r\n" + 
				"                \"AllowComments\": false,\r\n" + 
				"                \"AllowCorrection\": false,\r\n" + 
				"                \"Attachments\": [\r\n" + 
				"                ],\r\n" + 
				"                \"Comment\": null,\r\n" + 
				"                \"Configuration\": [\r\n" + 
				"                ],\r\n" + 
				"                \"DataSourceId\": \"00000000-0000-0000-0000-000000000000\",\r\n" + 
				"                \"DataSourceName\": null,\r\n" + 
				"                \"Default\": null,\r\n" + 
				"                \"DependencyRule\": null,\r\n" + 
				"                \"ElementType\": \"Field\",\r\n" + 
				"                \"ExpressionRule\": null,\r\n" + 
				"                \"FailsForm\": true,\r\n" + 
				"                \"GroupID\": \"00000000-0000-0000-0000-000000000000\",\r\n" + 
				"                \"Hint\": null,\r\n" + 
				"                \"Id\": \""+fieldId+"\",\r\n" + 
				"                \"IsFieldCompliant\": -2,\r\n" + 
				"                \"IsRequired\": true,\r\n" + 
				"                \"Name\": \""+fieldname+"\",\r\n" + 
				"                \"Order\": 0,\r\n" + 
				"                \"PredefinedCorrections\": null,\r\n" + 
				"                \"QuestionWeight\": null,\r\n" + 
				"                \"Repeat\": null,\r\n" + 
				"                \"RepeatField\": false,\r\n" + 
				"                \"ShortName\": \""+shortname+"\",\r\n" + 
				"                \"ShowField\": true,\r\n" + 
				"                \"ShowHint\": false,\r\n" + 
				"                \"ShowIsResolved\": false,\r\n" + 
				"                \"ShowOnCOA\": true,\r\n" + 
				"                \"ShowPointsEarned\": null,\r\n" + 
				"                \"ShowPointsPossible\": null,\r\n" + 
				"                \"Type\": \"FreeText\",\r\n" + 
				"                \"Value\": \""+value+"\"\r\n" + 
				"            }";
		return request;
	}

	/**
	 * This method is used to get selectone field body
	 * @author choubey_a
	 * @param shortname,fieldId,fieldName and list of options
	 * @return selectone body
	 */

	public String selectOneField(String shortname,String fieldId, String fieldname,List<Options> options) {
		String request;

		request = " {\r\n" + 
				"                \"AllowAttachments\": false,\r\n" + 
				"                \"AllowComments\": false,\r\n" + 
				"                \"AllowCorrection\": false,\r\n" + 
				"                \"Attachments\": [\r\n" + 
				"                ],\r\n" + 
				"                \"Comment\": null,\r\n" + 
				"                \"Configuration\": [\r\n" + 
				"                ],\r\n" + 
				"                \"DataSourceId\": \"00000000-0000-0000-0000-000000000000\",\r\n" + 
				"                \"DataSourceName\": null,\r\n" + 
				"                \"Default\": null,\r\n" + 
				"                \"DependencyRule\": null,\r\n" + 
				"                \"ElementType\": \"Field\",\r\n" + 
				"                \"ExpressionRule\": null,\r\n" + 
				"                \"FailsForm\": true,\r\n" + 
				"                \"GroupID\": \"00000000-0000-0000-0000-000000000000\",\r\n" + 
				"                \"Hint\": null,\r\n" + 
				"                \"Id\": \""+fieldId+"\",\r\n" + 
				"                \"IncludeInExport\": false,\r\n" + 
				"                \"IsFieldCompliant\": -2,\r\n" + 
				"                \"IsRequired\": true,\r\n" + 
				"                \"Name\": \""+fieldname+"\",\r\n" + 
				"                \"Options\": [\r\n" + 
				"                    {\r\n" + 
				"                        \"Code\": null,\r\n" + 
				"                        \"Id\": \""+options.get(0).Id+"\",\r\n" + 
				"                        \"Name\": \""+options.get(0).Name+"\",\r\n" + 
				"                        \"Value\": null\r\n" + 
				"                    },\r\n" + 
				"                    {\r\n" + 
				"                        \"Code\": null,\r\n" + 
				"                        \"Id\": \""+options.get(1).Id+"\",\r\n" + 
				"                        \"Name\": \""+options.get(1).Name+"\",\r\n" + 
				"                        \"Value\": null\r\n" + 
				"                    },\r\n" + 
				"                    {\r\n" + 
				"                        \"Code\": null,\r\n" + 
				"                        \"Id\": \""+options.get(2).Id+"\",\r\n" + 
				"                        \"Name\": \""+options.get(2).Name+"\",\r\n" + 
				"                        \"Value\": null\r\n" + 
				"                    }\r\n" + 
				"                ],\r\n" + 
				"                \"Order\": 0,\r\n" + 
				"                \"PointsEarned\": null,\r\n" + 
				"                \"PredefinedCorrections\": null,\r\n" + 
				"                \"QuestionWeight\": 0,\r\n" + 
				"                \"Repeat\": null,\r\n" + 
				"                \"RepeatField\": false,\r\n" + 
				"                \"ShortName\": \""+shortname+"\",\r\n" + 
				"                \"ShowField\": true,\r\n" + 
				"                \"ShowHint\": false,\r\n" + 
				"                \"ShowIsResolved\": false,\r\n" + 
				"                \"ShowOnCOA\": true,\r\n" + 
				"                \"ShowPointsEarned\": false,\r\n" + 
				"                \"ShowPointsPossible\": false,\r\n" + 
				"                \"Type\": \"SelectOne\",\r\n" + 
				"                \"Value\": {\r\n" + 
				"                    \"Code\": null,\r\n" + 
				"                    \"Id\": \""+options.get(1).Id+"\",\r\n" + 
				"                    \"Name\": \""+options.get(1).Name+"\",\r\n" + 
				"                    \"Value\": null\r\n" + 
				"                }\r\n" + 
				"            }";
		return request;
	}

	/**
	 * This method is used to get select multiple field body
	 * @author choubey_a
	 * @param shortname,fieldId,fieldName and list of options
	 * @return select multiple body
	 */

	public String selectMultipleField(String shortname,String fieldId, String fieldname,List<Options> options) {
		String request = "{\r\n" + 
				"                \"AllowAttachments\": false,\r\n" + 
				"                \"AllowComments\": false,\r\n" + 
				"                \"AllowCorrection\": false,\r\n" + 
				"                \"Attachments\": [\r\n" + 
				"                ],\r\n" + 
				"                \"Comment\": null,\r\n" + 
				"                \"Configuration\": [\r\n" + 
				"                ],\r\n" + 
				"                \"Default\": null,\r\n" + 
				"                \"DependencyRule\": null,\r\n" + 
				"                \"ElementType\": \"Field\",\r\n" + 
				"                \"ExpressionRule\": null,\r\n" + 
				"                \"FailsForm\": true,\r\n" + 
				"                \"GroupID\": \"00000000-0000-0000-0000-000000000000\",\r\n" + 
				"                \"Hint\": null,\r\n" + 
				"                \"Id\": \""+fieldId+"\",\r\n" + 
				"                \"IsFieldCompliant\": -2,\r\n" + 
				"                \"IsRequired\": true,\r\n" + 
				"                \"Name\": \""+fieldname+"\",\r\n" + 
				"                \"Options\": [\r\n" + 
				"                    {\r\n" + 
				"                        \"Code\": null,\r\n" + 
				"                        \"Id\": \""+options.get(0).Id+"\",\r\n" + 
				"                        \"Name\": \"Value1\",\r\n" + 
				"                        \"Value\": null\r\n" + 
				"                    },\r\n" + 
				"                    {\r\n" + 
				"                        \"Code\": null,\r\n" + 
				"                        \"Id\": \""+options.get(1).Id+"\",\r\n" + 
				"                        \"Name\": \"Value2\",\r\n" + 
				"                        \"Value\": null\r\n" + 
				"                    },\r\n" + 
				"                    {\r\n" + 
				"                        \"Code\": null,\r\n" + 
				"                        \"Id\": \""+options.get(2).Id+"\",\r\n" + 
				"                        \"Name\": \"Value3\",\r\n" + 
				"                        \"Value\": null\r\n" + 
				"                    },\r\n" + 
				"                    {\r\n" + 
				"                        \"Code\": null,\r\n" + 
				"                        \"Id\": \""+options.get(3).Id+"\",\r\n" + 
				"                        \"Name\": \"Value4\",\r\n" + 
				"                        \"Value\": null\r\n" + 
				"                    }\r\n" + 
				"                ],\r\n" + 
				"                \"Order\": 0,\r\n" + 
				"                \"PointsEarned\": 0,\r\n" + 
				"                \"PredefinedCorrections\": null,\r\n" + 
				"                \"QuestionWeight\": 0,\r\n" + 
				"                \"Repeat\": null,\r\n" + 
				"                \"RepeatField\": false,\r\n" + 
				"                \"ShortName\": \""+shortname+"\",\r\n" + 
				"                \"ShowField\": true,\r\n" + 
				"                \"ShowHint\": false,\r\n" + 
				"                \"ShowIsResolved\": false,\r\n" + 
				"                \"ShowOnCOA\": true,\r\n" + 
				"                \"ShowPointsEarned\": false,\r\n" + 
				"                \"ShowPointsPossible\": false,\r\n" + 
				"                \"Type\": \"SelectMultiple\",\r\n" + 
				"                \"Value\": [\r\n" + 
				"                    {\r\n" + 
				"                        \"Code\": null,\r\n" + 
				"                        \"Id\": \""+options.get(0).Id+"\",\r\n" + 
				"                        \"Name\": \"Value1\",\r\n" + 
				"                        \"Value\": null\r\n" + 
				"                    }\r\n" + 
				"                ]\r\n" + 
				"            }";
		return request;
	}

	/**
	 * This method is used to get select date field body
	 * @author choubey_a
	 * @param value,shortname,fieldId and fieldName
	 * @return date body
	 */

	public String dateField(String value, String shortname, String fieldId,String fieldname) {
		String request = "            {\r\n" + 
				"                \"AllowAttachments\": false,\r\n" + 
				"                \"AllowComments\": false,\r\n" + 
				"                \"AllowCorrection\": false,\r\n" + 
				"                \"Attachments\": [\r\n" + 
				"                ],\r\n" + 
				"                \"Comment\": null,\r\n" + 
				"                \"Configuration\": [\r\n" + 
				"                ],\r\n" + 
				"                \"DataSourceId\": \"00000000-0000-0000-0000-000000000000\",\r\n" + 
				"                \"DataSourceName\": null,\r\n" + 
				"                \"Default\": null,\r\n" + 
				"                \"DependencyRule\": null,\r\n" + 
				"                \"ElementType\": \"Field\",\r\n" + 
				"                \"ExpressionRule\": null,\r\n" + 
				"                \"FailsForm\": true,\r\n" + 
				"                \"GroupID\": \"00000000-0000-0000-0000-000000000000\",\r\n" + 
				"                \"Hint\": null,\r\n" + 
				"                \"Id\": \""+fieldId+"\",\r\n" + 
				"                \"IsFieldCompliant\": -2,\r\n" + 
				"                \"IsRequired\": true,\r\n" + 
				"                \"Name\": \""+fieldname+"\",\r\n" + 
				"                \"Order\": 0,\r\n" + 
				"                \"PredefinedCorrections\": null,\r\n" + 
				"                \"QuestionWeight\": null,\r\n" + 
				"                \"Repeat\": null,\r\n" + 
				"                \"RepeatField\": false,\r\n" + 
				"                \"ShortName\": \""+shortname+"\",\r\n" + 
				"                \"ShowField\": true,\r\n" + 
				"                \"ShowHint\": false,\r\n" + 
				"                \"ShowIsResolved\": false,\r\n" + 
				"                \"ShowOnCOA\": true,\r\n" + 
				"                \"ShowPointsEarned\": null,\r\n" + 
				"                \"ShowPointsPossible\": null,\r\n" + 
				"                \"Type\": \"Date\",\r\n" + 
				"                \"Value\": \""+value+"\"\r\n" + 
				"            }";
		return request;
	}

	/**
	 * This method is used to get select time field body
	 * @author choubey_a
	 * @param value,shortname,fieldId and fieldName
	 * @return time body
	 */

	public String timeField(String value, String shortname, String fieldId,String fieldname) {
		String request = "{\r\n" + 
				"                \"AllowAttachments\": false,\r\n" + 
				"                \"AllowComments\": false,\r\n" + 
				"                \"AllowCorrection\": false,\r\n" + 
				"                \"Attachments\": [\r\n" + 
				"                ],\r\n" + 
				"                \"Comment\": null,\r\n" + 
				"                \"Configuration\": [\r\n" + 
				"                ],\r\n" + 
				"                \"DataSourceId\": \"00000000-0000-0000-0000-000000000000\",\r\n" + 
				"                \"DataSourceName\": null,\r\n" + 
				"                \"Default\": null,\r\n" + 
				"                \"DependencyRule\": null,\r\n" + 
				"                \"ElementType\": \"Field\",\r\n" + 
				"                \"ExpressionRule\": null,\r\n" + 
				"                \"FailsForm\": true,\r\n" + 
				"                \"GroupID\": \"00000000-0000-0000-0000-000000000000\",\r\n" + 
				"                \"Hint\": null,\r\n" + 
				"                \"Id\": \""+fieldId+"\",\r\n" + 
				"                \"IsFieldCompliant\": -2,\r\n" + 
				"                \"IsRequired\": true,\r\n" + 
				"                \"Name\": \""+fieldname+"\",\r\n" + 
				"                \"Order\": 0,\r\n" + 
				"                \"PredefinedCorrections\": null,\r\n" + 
				"                \"QuestionWeight\": null,\r\n" + 
				"                \"Repeat\": null,\r\n" + 
				"                \"RepeatField\": false,\r\n" + 
				"                \"ShortName\": \""+shortname+"\",\r\n" + 
				"                \"ShowField\": true,\r\n" + 
				"                \"ShowHint\": false,\r\n" + 
				"                \"ShowIsResolved\": false,\r\n" + 
				"                \"ShowOnCOA\": true,\r\n" + 
				"                \"ShowPointsEarned\": null,\r\n" + 
				"                \"ShowPointsPossible\": null,\r\n" + 
				"                \"Type\": \"Time\",\r\n" + 
				"                \"Value\": \""+value+"\"\r\n" + 
				"            }";
		return request;
	}

	/**
	 * This method is used to get select datetime field body
	 * @author choubey_a
	 * @param value,shortname,fieldId and fieldName
	 * @return datetime body
	 */

	public String dateTimeField(String value, String shortname,String fieldId, String fieldname) {
		String request = "{\r\n" + 
				"                \"AllowAttachments\": false,\r\n" + 
				"                \"AllowComments\": false,\r\n" + 
				"                \"AllowCorrection\": false,\r\n" + 
				"                \"Attachments\": [\r\n" + 
				"                ],\r\n" + 
				"                \"Comment\": null,\r\n" + 
				"                \"Configuration\": [\r\n" + 
				"                ],\r\n" + 
				"                \"DataSourceId\": \"00000000-0000-0000-0000-000000000000\",\r\n" + 
				"                \"DataSourceName\": null,\r\n" + 
				"                \"Default\": null,\r\n" + 
				"                \"DependencyRule\": null,\r\n" + 
				"                \"ElementType\": \"Field\",\r\n" + 
				"                \"ExpressionRule\": null,\r\n" + 
				"                \"FailsForm\": true,\r\n" + 
				"                \"GroupID\": \"00000000-0000-0000-0000-000000000000\",\r\n" + 
				"                \"Hint\": null,\r\n" + 
				"                \"Id\": \""+fieldId+"\",\r\n" + 
				"                \"IsFieldCompliant\": -2,\r\n" + 
				"                \"IsRequired\": true,\r\n" + 
				"                \"Name\": \""+fieldname+"\",\r\n" + 
				"                \"Order\": 0,\r\n" + 
				"                \"PredefinedCorrections\": null,\r\n" + 
				"                \"QuestionWeight\": null,\r\n" + 
				"                \"Repeat\": null,\r\n" + 
				"                \"RepeatField\": false,\r\n" + 
				"                \"ShortName\": \""+shortname+"\",\r\n" + 
				"                \"ShowField\": true,\r\n" + 
				"                \"ShowHint\": false,\r\n" + 
				"                \"ShowIsResolved\": false,\r\n" + 
				"                \"ShowOnCOA\": true,\r\n" + 
				"                \"ShowPointsEarned\": null,\r\n" + 
				"                \"ShowPointsPossible\": null,\r\n" + 
				"                \"Type\": \"DateTime\",\r\n" + 
				"                \"Value\": \""+value+"\"\r\n" + 
				"            }";
		return request;
	}

	/**
	 * This method is used to get identifier field body
	 * @author choubey_a
	 * @param value,shortname,fieldId and fieldName
	 * @return identifier body
	 */

	public String identifierField(String value, String shortname, String fieldId, String fieldname) {
		String request = "{\r\n" + 
				"                \"AllowAttachments\": false,\r\n" + 
				"                \"AllowComments\": false,\r\n" + 
				"                \"Attachments\": [\r\n" + 
				"                ],\r\n" + 
				"                \"Comment\": null,\r\n" + 
				"                \"Configuration\": [\r\n" + 
				"                ],\r\n" + 
				"                \"DataSourceId\": \"00000000-0000-0000-0000-000000000000\",\r\n" + 
				"                \"DataSourceName\": null,\r\n" + 
				"                \"Default\": null,\r\n" + 
				"                \"DependencyRule\": null,\r\n" + 
				"                \"ElementType\": \"Field\",\r\n" + 
				"                \"GroupID\": \"00000000-0000-0000-0000-000000000000\",\r\n" + 
				"                \"Hint\": null,\r\n" + 
				"                \"Id\": \""+fieldId+"\",\r\n" + 
				"                \"IdentifierOption\": null,\r\n" + 
				"                \"InputMask\": null,\r\n" + 
				"                \"InputType\": {\r\n" + 
				"                    \"Id\": \"FreeText\",\r\n" + 
				"                    \"Name\": \"Free Text\"\r\n" + 
				"                },\r\n" + 
				"                \"IsFieldCompliant\": -2,\r\n" + 
				"                \"IsFilterable\": true,\r\n" + 
				"                \"IsRequired\": true,\r\n" + 
				"                \"Name\": \""+fieldname+"\",\r\n" + 
				"                \"Order\": 0,\r\n" + 
				"                \"QuestionWeight\": null,\r\n" + 
				"                \"Repeat\": null,\r\n" + 
				"                \"RepeatField\": false,\r\n" + 
				"                \"ShortName\": \""+shortname+"\",\r\n" + 
				"                \"ShowField\": true,\r\n" + 
				"                \"ShowHint\": false,\r\n" + 
				"                \"ShowOnCOA\": true,\r\n" + 
				"                \"ShowPointsEarned\": null,\r\n" + 
				"                \"ShowPointsPossible\": null,\r\n" + 
				"                \"Type\": \"Identifier\",\r\n" + 
				"                \"Value\": \""+value+"\"\r\n" + 
				"            }";
		return request;
	}

	public String singleLineTextField(String value, String shortname, String fieldId, String fieldname) {
		String request = "{\r\n" + 
				"                \"AllowAttachments\": false,\r\n" + 
				"                \"AllowComments\": false,\r\n" + 
				"                \"AllowCorrection\": false,\r\n" + 
				"                \"Attachments\": [\r\n" + 
				"                ],\r\n" + 
				"                \"Comment\": null,\r\n" + 
				"                \"Configuration\": [\r\n" + 
				"                ],\r\n" + 
				"                \"DataSourceId\": \"00000000-0000-0000-0000-000000000000\",\r\n" + 
				"                \"DataSourceName\": null,\r\n" + 
				"                \"Default\": null,\r\n" + 
				"                \"DependencyRule\": null,\r\n" + 
				"                \"ElementType\": \"Field\",\r\n" + 
				"                \"ExpressionRule\": null,\r\n" + 
				"                \"FailsForm\": true,\r\n" + 
				"                \"GroupID\": \"00000000-0000-0000-0000-000000000000\",\r\n" + 
				"                \"Hint\": null,\r\n" + 
				"                \"Id\": \""+fieldId+"\",\r\n" + 
				"                \"IsFieldCompliant\": -2,\r\n" + 
				"                \"IsRequired\": true,\r\n" + 
				"                \"Name\": \""+fieldname+"\",\r\n" + 
				"                \"Order\": 0,\r\n" + 
				"                \"PredefinedCorrections\": null,\r\n" + 
				"                \"QuestionWeight\": null,\r\n" + 
				"                \"Repeat\": null,\r\n" + 
				"                \"RepeatField\": false,\r\n" + 
				"                \"ShortName\": \""+shortname+"\",\r\n" + 
				"                \"ShowField\": true,\r\n" + 
				"                \"ShowHint\": false,\r\n" + 
				"                \"ShowIsResolved\": false,\r\n" + 
				"                \"ShowOnCOA\": false,\r\n" + 
				"                \"ShowPointsEarned\": null,\r\n" + 
				"                \"ShowPointsPossible\": null,\r\n" + 
				"                \"Type\": \"SingleLineText\",\r\n" + 
				"                \"Value\": \""+value+"\"\r\n" + 
				"            }";
		return request;
	}

	/**
	 * This method is used to get field details
	 * @author choubey_a
	 * @param formdata and data
	 * @return details of the field
	 */

	public String getFieldDetails(List<FormData>FormData, HashMap<String,String> data, boolean reject) {

		String fieldDetails = "", allfieldDetails= "";
		int count = 0;
		try {
			for (Map.Entry<String, String> entry : data.entrySet()) {
				String shortname = entry.getKey();
				String value = entry.getValue();
				fieldDetails = "";
				for(int i =0; i< FormData.size();i++) {
					if(shortname .equals(FormData.get(i).shortname)) {
						switch(FormData.get(i).Type) {
						case FormFields.FREETEXT:						
							fieldDetails = freeTextField(value,shortname,FormData.get(i).fieldId, FormData.get(i).fieldname);
							break;
						case FormFields.SINGLELINETEXT:						
							fieldDetails = singleLineTextField(value,shortname,FormData.get(i).fieldId, FormData.get(i).fieldname);
							break;
						case FormFields.NUMERIC:	
							if(reject) {
								fieldDetails = rejectednumericField(value,shortname,FormData.get(i).fieldId,FormData.get(i).fieldname);	
							}else {
								fieldDetails = numericField(value,shortname,FormData.get(i).fieldId, FormData.get(i).fieldname);
							}
							break;
						case FormFields.SELECTONE:						
							fieldDetails = selectOneField(shortname,FormData.get(i).fieldId, FormData.get(i).fieldname, FormData.get(i).Options);	
							break;
						case FormFields.SELECTMULTIPLE:						
							fieldDetails = selectMultipleField(shortname,FormData.get(i).fieldId, FormData.get(i).fieldname,FormData.get(i).Options);	
							break;
						case FormFields.DATE:						
							fieldDetails= dateField(value,shortname,FormData.get(i).fieldId, FormData.get(i).fieldname);	
							break;
						case FormFields.TIME:						
							fieldDetails= timeField(value,shortname,FormData.get(i).fieldId, FormData.get(i).fieldname);		
							break;
						case FormFields.DATETIME:						
							fieldDetails= dateTimeField(value,shortname,FormData.get(i).fieldId, FormData.get(i).fieldname);
							break;
						case FormFields.IDENTIFIER:						
							fieldDetails= identifierField(value,shortname,FormData.get(i).fieldId, FormData.get(i).fieldname);
							break;
						default:
							logError("Correct Field Type not provided");
						}
					}
				}
				count ++;
				if(data.size() == count) {
					allfieldDetails = allfieldDetails + fieldDetails ;
				}
				else {
					allfieldDetails = allfieldDetails + fieldDetails + "," ;
				}
			}
			System.out.println(allfieldDetails);
			return allfieldDetails;
		}catch(Exception e) {
			return null;
		}

	}

	public String formDetailsbody(List<FormData>FormData, HashMap<String,String>data,boolean section) {
		String request = null;
		try {
			if(section ==  true) {
				for (Map.Entry<String, String> entry : data.entrySet()) {
					String shortname = entry.getKey();
					for(int i =0; i< FormData.size();i++) {
						System.out.println(FormData.get(i).shortname);
						if(shortname.contains(FormData.get(i).shortname)) {
							request = "{\r\n" + 
									"				\"Id\": \""+FormData.get(i).sectionFieldId+"\",\r\n" + 
									"				\"Name\": \""+FormData.get(i).sectionName+"\",\r\n" + 
									"				\"ShortName\": \""+FormData.get(i).sectionShortname+"\",\r\n" + 
									"				\"ElementType\": \"Section\",\r\n" + 
									"				\"SectionElements\": ["+getFieldDetails(FormData,data,false)+"],\r\n" + 
									"				\"Order\": 0,\r\n" + 
									"				\"GroupID\": \"00000000-0000-0000-0000-000000000000\",\r\n" + 
									"				\"Repeat\": null,\r\n" + 
									"				\"PointsPossible\": 0.0,\r\n" + 
									"				\"PointsEarned\": 0.0,\r\n" + 
									"				\"Score\": 0.0,\r\n" + 
									"				\"ParentId\": null\r\n" + 
									"			},";
						}
					}
				}
			}

			else {
				getFieldDetails(FormData,data,false);
			}
			return request;

		}catch(Exception e) {
			return null;	
		}
	}

	/**
	 * This method is used to save or submit the form
	 * @author choubey_a
	 * @param formdata ,data, details and submit boolean
	 * @return record Id if form is submitted
	 */

	public String saveform(List<FormData>FormData, HashMap<String,String> data,FormSubDetails details, boolean submit, boolean section) {

		apiUtils = new ApiUtils();
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("Accept", "application/json, text/plain, /");
		headers.put("Authorization", "Bearer " + accessToken);
		Gson gson = new GsonBuilder().create();
		String RecordId = null;

		try {
			String request ="{\r\n" + 
					"    \"Data\": {\r\n" + 
					"        \"Attachments\": [\r\n" + 
					"        ],\r\n" + 
					"        \"Comments\": null,\r\n"+ 
					"      \"FormDetails\":[\r\n" + 
					formDetailsbody(FormData,data,section)+
					"      ],\r\n" + 
					"      \"JobDetails\":[\r\n" + 
					"         \r\n" + 
					"      ],\r\n" + 
					"      \"TaskDetails\":[\r\n" + 
					"         \r\n" + 
					"      ],\r\n" + 
					"      \"FormRef\":{\r\n" + 
					"         \"Id\":\""+details.formId+"\",\r\n" + 
					"         \"FormRefId\":\""+details.formrefId+"\",\r\n" + 
					"         \"Name\":\""+details.formName+"\",\r\n" + 
					"         \"Type\":\""+details.formtype+"\"\r\n" + 
					"      },\r\n" + 
					"      \"FormType\":null,\r\n" + 
					"      \"ID\":\""+details.formTypeId+"\",\r\n" + 
					"      \"Notes\":null,\r\n" + 
					"      \"Program\":{\r\n" + 
					"         \r\n" + 
					"      },\r\n" + 
					"      \"Resource\":{\r\n" + 
					"         \"Id\":\""+details.ResourceId+"\",\r\n" + 
					"         \"Name\":\""+details.ResourceName+"\"\r\n" + 
					"      },\r\n" + 
					"      \"Location\":{\r\n" + 
					"         \"Id\":\""+details.locationId+"\",\r\n" + 
					"         \"Name\":\""+details.locationName+"\"\r\n" + 
					"      },\r\n" + 
					"      \"Comments\":\""+details.comment+"\",\r\n" + 
					"      \"Attachments\":[\r\n" + 
					"         \r\n" + 
					"      ],\r\n" + 
					"      \"IsSavedFormWip\":true,\r\n" + 
					"      \"TaskInfo\":null,\r\n" + 
					"      \"VerifiedBy\":{\r\n" + 
					"         \r\n" + 
					"      },\r\n" + 
					"      \"VerificationUpdated\":false\r\n" + 
					"   }\r\n" + 
					"}";

			if(submit == true) {

				Response response = apiUtils.submitRequest(METHOD_TYPE.POST, request, headers, submitForm);
				SingleDataResponse sdr = gson.fromJson(response.asString(), SingleDataResponse.class);
				if(!sdr.Status) {
					logError("Form not submitted");
					return null;
				}
				RecordId = sdr.Data.ID;
			}

			else 
				if(submit == false) {

					Response response = apiUtils.submitRequest(METHOD_TYPE.POST, request, headers, SaveForm);
					SingleDataResponse sdr = gson.fromJson(response.asString(), SingleDataResponse.class);
					if(!sdr.Status) {
						logError("Form not saved");
						return null;
					}
				}
			return RecordId;
		}catch(Exception e) {
			logInfo("Form is not saved/submitted -"+e.getMessage());
			return null;
		}
	}

	/**
	 * This method is used to submit the quest form
	 * @author choubey_a
	 * @param formdata ,data and details
	 * @return record Id will returned if form submitted 
	 */

	public String addQuestForm(List<FormData>FormData, HashMap<String,String> data,FormSubDetails details) {

		apiUtils = new ApiUtils();
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("Accept", "application/json, text/plain, /");
		headers.put("Authorization", "Bearer " + accessToken);
		Gson gson = new GsonBuilder().create();
		String RecordId = null;
		try {
			String request = "{\r\n" + 
					"    \"Data\": {\r\n" + 
					"        \"Attachments\": [\r\n" + 
					"        ],\r\n" + 
					"        \"Comments\": null,\r\n" + 
					"        \"FormDetails\": [\r\n" + 
					getFieldDetails(FormData,data,false) +
					"        ],\r\n" + 
					"        \"FormRef\": {\r\n" + 
					"            \"FormRefId\": \""+details.formId+"\",\r\n" + 
					"            \"Id\": \""+details.formrefId+"\",\r\n" + 
					"            \"Name\": \""+details.formName+"\",\r\n" + 
					"            \"Type\": \"Questionnaire\"\r\n" + 
					"        },\r\n" + 
					"        \"FormType\": null,\r\n" + 
					"        \"Header\": [\r\n" + 
					"        ],\r\n" + 
					"        \"ID\": \""+details.formTypeId+"\",\r\n" + 
					"        \"IsSavedFormWip\": false,\r\n" + 
					"        \"JobDetails\": [\r\n" + 
					"        ],\r\n" + 
					"        \"Location\": {\r\n" + 
					"            \"Id\": \""+details.locationId+"\",\r\n" + 
					"            \"Name\": \""+details.locationName+"\"\r\n" + 
					"        },\r\n" + 
					"        \"Notes\": null,\r\n" + 
					"        \"Program\": {\r\n" + 
					"        },\r\n" + 
					"        \"Resource\": {\r\n" + 
					"            \"Id\": \""+details.ResourceId+"\",\r\n" + 
					"            \"Name\": \""+details.ResourceName+"\"\r\n" + 
					"        },\r\n" + 
					"        \"TaskDetails\": [\r\n" + 
					"        ],\r\n" + 
					"        \"VerificationUpdated\": false,\r\n" + 
					"        \"VerifiedBy\": {\r\n" + 
					"        }\r\n" + 
					"    }\r\n" + 
					"}\r\n" + 
					"";

			Response response = apiUtils.submitRequest(METHOD_TYPE.POST, request, headers, addQuestForm);
			SingleDataResponse sdr = gson.fromJson(response.asString(), SingleDataResponse.class);
			if(!sdr.Status) {
				logError("Form not submitted");
				return null;
			}
			RecordId = sdr.Data.Id;
			return RecordId;
		}catch(Exception e) {
			logInfo("Form is not saved/submitted -"+e.getMessage());
			return null;
		}
	}

	/**
	 * This method is used to assign Tasks
	 * @author shelar_d
	 * @param details  
	 * @param startdate 
	 * @param enddate 
	 * @param locationId 
	 * @param AssignedTasks 
	 * @return Tasks Id
	 */

	public String assignTasks(TaskDetails details) {
		try {
			apiUtils = new ApiUtils();
			Gson gson = new GsonBuilder().create();
			Map<String, String> headers = new HashMap<String, String>();
			headers.put("Content-Type", "application/json");
			headers.put("Accept", "application/json, text/plain, /");
			headers.put("Authorization", "Bearer " + accessToken);

			String request ="{\r\n"
					+ "   \"Type\":\"Form\",\r\n"
					+ "   \"Status\":\"Unread\",\r\n"
					+ "   \"TimelineStatus\":\"OnTrack\",\r\n"
					+ "   \"AssignmentType\":\"WorkGroup\",\r\n"
					+ "   \"FormInfo\":{\r\n"
					+ "      \"Id\":\""+details.FormId+"\",\r\n"
					+ "      \"Name\":\""+details.FormName+"\",\r\n"
					+ "      \"Version\":{\r\n"
					+ "         \"major\":1\r\n"
					+ "      },\r\n"
					+ "      \"Type\":\"Check\"\r\n"
					+ "   },\r\n"
					+ "   \"Resource\":{\r\n"
					+ "      \"Id\":\""+details.ResourceId+"\",\r\n"
					+ "      \"Name\":\""+details.ResourceName+"\"\r\n"
					+ "   },\r\n"
					+ "   \"Version\":\"1.0\",\r\n"
					+ "   \"Name\":\""+details.TaskName+"\",\r\n"
					+ "   \"DueBy\":{\r\n"
					+ "      \"Date\":\"12/17/2021 01:30\",\r\n"
					+ "      \"TimeZone\":null\r\n"
					+ "   },\r\n"
					+ "   \"Note\":null,\r\n"
					+ "   \"AssignmentTo\":{\r\n"
					+ "      \"TaskCount\":0,\r\n"
					+ "      \"Id\":\""+details.WorkgroupId+"\",\r\n"
					+ "      \"Name\":\""+details.WorkgroupName+"\"\r\n"
					+ "   },\r\n"
					+ "   \"LocationDetails\":{\r\n"
					+ "      \"Id\":\""+details.LocationId+"\",\r\n"
					+ "      \"Name\":\""+details.LocationName+"\"\r\n"
					+ "   },\r\n"
					+ "   \"TaskExpiration\":{\r\n"
					+ "      \"ExpirationIntervalId\":\"f4cbde30-6a8f-42e0-b50c-3718b9d6eab5\",\r\n"
					+ "      \"ExpirationIntervalType\":\"Day\",\r\n"
					+ "      \"ExpirationIntervalValue\":1\r\n"
					+ "   }\r\n"
					+ "}";


			Response response = apiUtils.submitRequest(METHOD_TYPE.POST,request,headers, AddFormTask);

			SimpleDataResponse sdr = gson.fromJson(response.asString(), SimpleDataResponse.class);

			String TasksId = sdr.Data;			
			logInfo("Task has been assigned");
			return TasksId;
		}catch (Exception e) {			
			logError("Failed to assign task - "+ e.getMessage());
			return null;
		}
	}


	public static class TaskDetails {
		public String FormName;
		public String FormId;
		public String TaskName;
		public String ResourceName;
		public String ResourceId;
		public String LocationName;
		public String LocationId;
		public String WorkgroupName;
		public String WorkgroupId;
		public String DueBy;
	}

	class SimpleDataResponse{
		String Data;
	}


	public class MultipleDataResponse {		
		public Data Id;
		public List<Data> Data;
	}

	public static class SingleDataResponse {
		public Data Data;
		public boolean Status;
	}

	public static class Data {
		public String Id;
		public String Name;
		public List<Rows> Rows;
		public List<FormDetails> FormDetails;
		public String FormId;
		public String ID;
	}

	public static class Rows{
		public String ID;
		public String Name;
	}

	public static class FormDetails{
		public String Id;
		public String Name;
		public String ShortName;
		public List<Options> Options;
		public String Type;
	}

	public static class Options{
		public String Id;
		public String Name;
	}

	public static class FormData{
		public String fieldname;
		public String shortname;
		public String fieldId;
		public List<Options> Options;
		public String Type;
		public String sectionFieldId;
		public String sectionName;
		public String sectionShortname;
	}

	public static class FormFields {
		public static final String NUMERIC = "Numeric";
		public static final String FREETEXT = "FreeText";
		public static final String PARAGRAPH = "Paragraph";
		public static final String DATE = "Date";
		public static final String TIME  = "Time";
		public static final String DATETIME = "DateTime";
		public static final String SELECTONE = "SelectOne";
		public static final String SELECTMULTIPLE = "SelectMultiple";
		public static final String IDENTIFIER = "Identifier";
		public static final String SINGLELINETEXT = "SingleLineText";
		public static final String NOTE = "Note";
		public static final String RELATEDDOCS = "RelatedDocs";
		public static final String SECTION = "Section";
	}

	public static class FormSubDetails {
		public String formrefId;
		public String formTypeId;
		public String formId;
		public String formName;
		public String locationName;
		public String locationId;
		public String ResourceName;
		public String ResourceId;
		public String comment;
		public String formtype;


	}

}
