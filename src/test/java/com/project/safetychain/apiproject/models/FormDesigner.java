package com.project.safetychain.apiproject.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.project.safetychain.apiproject.models.Auth;
import com.project.utilities.ApiUtils;
import com.project.utilities.ApiUtils.METHOD_TYPE;
import io.restassured.response.Response;

public class FormDesigner extends Auth{


	public String GetResourceTypes = baseURI + FormDesignerURLs.GET_RESOURCE_TYPES;
	public String FormDesignerHeader = baseURI + FormDesignerURLs.FORM_DESIGNER_HEADERS;
	public String GetHierarchy = baseURI + FormDesignerURLs.GET_HIERARCHY;
	public String GetChildResources = baseURI + FormDesignerURLs.GET_CHILD_RESOURCES;
	public String GetFormElements = baseURI + FormDesignerURLs.GET_FORM_ELEMENTS;
	public String SaveFormDesign = baseURI + FormDesignerURLs.SAVE_FORM_DESIGN;
	public String AddField = baseURI + FormDesignerURLs.ADD_FIELD;
	public String UpdateField = baseURI + FormDesignerURLs.UPDATE_FIELD;
	public String SubmitFormDesign = baseURI + FormDesignerURLs.SUBMIT_FORM_DESIGN;
	public String ValidateReleaseForm = baseURI + FormDesignerURLs.VALIDATE_RELEASE_FORM;
	public String ReleaseForm = baseURI + FormDesignerURLs.RELEASE_FORM;


	ApiUtils apiUtils = new ApiUtils();
	Gson gson = new GsonBuilder().create();
	Auth auth = new Auth();
	Requirements req = new Requirements();

	String resourceTreeId = null; 
	String resourceTreeName = null;
	String resourceCatId =null;

	/**
	 * This method is used to add field body
	 * @author choubey_a	
	 * @param fieldName - name if the field
	 * @param shortname - shortname of the field 
	 * @param formId - id of the form 
	 * @param fieldType - type of the field
	 * @param parentId - id of section or field Group
	 * @return request body for the field added
	 */

	public String addField(String fieldName, String shortname,String formId, String fieldType,String parentId) {
		String request = null;
		String parentbody = "\""+parentId+"\"";
		try {
			switch(fieldType) {
			case FormFields.FREETEXT:
				request = "{\"FormId\":\""+formId+"\",\"HasCompliance\":false,"
						+ "\"Field\":{\"level\":\"form\",\"type\":\"FreeText\",\"value\":null,\"children\":[],"
						+ "\"HasCompliance\":false,\"ElementType\":\"Field\",\"fieldHasCompliance\":true,"
						+ "\"ShortName\":\""+shortname+"\",\"Default\":null,\"DataSource\":null,\"Repeat\":null,\"FailsForm\":true,"
						+ "\"IsRequired\":true,\"AllowComments\":false,\"AllowAttachments\":false,\"ShowOnCOA\":true,"
						+ "\"ShowHint\":false,\"Hint\":null,\"AllowCorrection\":false,\"ShowIsResolved\":false,\"PredefinedCorrections\":null,"
						+ "\"RepeatField\":false,\"DependencyRule\":null,\"ExpressionRule\":null,\"isNonRepeatable\":false,\"ParentId\":"+parentbody+","
						+ "\"Name\":\""+fieldName+"\"}}";
				break;
			case FormFields.NUMERIC :
				request = "{\"FormId\":\""+formId+"\",\"HasCompliance\":false,"
						+ "\"Field\":{\"level\":\"form\",\"type\":\"Numeric\",\"value\":null,\"children\":[],\"HasCompliance\":false,"
						+ "\"ElementType\":\"Field\",\"fieldHasCompliance\":true,\"ShortName\":\""+shortname+"\",\"Default\":null,\"DataSource\":null,"
						+ "\"Repeat\":null,\"IsRequired\":true,\"AllowComments\":false,\"AllowAttachments\":false,\"ShowOnCOA\":true,\"ShowHint\":false,"
						+ "\"Hint\":null,\"ShowMinMax\":true,\"ShowTarget\":false,\"ShowFieldValidation\":false,\"MinValue\":null,\"MaxValue\":null,"
						+ "\"ShowSignificantDigit\":false,\"DecimalPrecision\":null,\"FailsForm\":true,\"AllowCorrection\":false,\"ShowIsResolved\":false,"
						+ "\"PredefinedCorrections\":null,\"IsIntegrated\":false,\"RepeatField\":false,\"DependencyRule\":null,\"ExpressionRule\":null,"
						+ "\"isNonRepeatable\":false,\"ParentId\":"+parentbody+",\"Name\":\""+fieldName+"\"}}";
				break;

			case FormFields.SELECTONE:
				request = "{\"FormId\":\""+formId+"\",\"HasCompliance\":false,"
						+ "\"Field\":{\"level\":\"form\",\"type\":\"SelectOne\",\"value\":null,\"children\":[],\"HasCompliance\":false,"
						+ "\"ElementType\":\"Field\",\"fieldHasCompliance\":true,\"ShortName\":\""+shortname+"\",\"DataSource\":null,"
						+ "\"Options\":[{\"Name\":\"Value1\",\"Value\":null},{\"Name\":\"Value2\",\"Value\":null},{\"Name\":\"Value3\",\"Value\":null}],"
						+ "\"Default\":null,\"Repeat\":null,\"IsRequired\":true,\"IncludeInExport\":false,\"AllowComments\":false,\"AllowAttachments\":false,"
						+ "\"ShowOnCOA\":true,\"ShowHint\":false,\"Hint\":null,\"FailsForm\":true,\"AllowCorrection\":false,\"ShowIsResolved\":false,"
						+ "\"PredefinedCorrections\":null,\"RepeatField\":false,\"DependencyRule\":null,\"ExpressionRule\":null,\"isNonRepeatable\":false,"
						+ "\"ParentId\":"+parentbody+",\"Name\":\""+fieldName+"\"}}";
				break;

			case FormFields.SELECTMULTIPLE:
				request = "{\"FormId\":\""+formId+"\",\"HasCompliance\":false,"
						+ "\"Field\":{\"level\":\"form\",\"type\":\"SelectMultiple\",\"value\":null,\"children\":[],\"HasCompliance\":false,"
						+ "\"ElementType\":\"Field\",\"fieldHasCompliance\":true,\"ShortName\":\""+shortname+"\","
						+ "\"Options\":[{\"Name\":\"Value1\",\"Value\":null},{\"Name\":\"Value2\",\"Value\":null},{\"Name\":\"Value3\",\"Value\":null},"
						+ "{\"Name\":\"Value4\",\"Value\":null}],\"Default\":null,\"Repeat\":null,\"IsRequired\":true,\"AllowComments\":false,"
						+ "\"AllowAttachments\":false,\"ShowOnCOA\":true,\"ShowHint\":false,\"Hint\":null,\"FailsForm\":true,\"AllowCorrection\":false,"
						+ "\"ShowIsResolved\":false,\"PredefinedCorrections\":null,\"RepeatField\":false,\"DependencyRule\":null,\"ExpressionRule\":null,"
						+ "\"isNonRepeatable\":false,\"ParentId\":"+parentbody+",\"Name\":\""+fieldName+"\"}}";
				break;

			case FormFields.DATE:
				request = "{\"FormId\":\""+formId+"\",\"HasCompliance\":false,"
						+ "\"Field\":{\"level\":\"form\",\"type\":\"Date\",\"value\":null,\"children\":[],\"HasCompliance\":false,"
						+ "\"ElementType\":\"Field\",\"fieldHasCompliance\":true,\"ShortName\":\""+shortname+"\",\"Default\":null,\"DataSource\":null,"
						+ "\"Repeat\":null,\"FailsForm\":true,\"IsRequired\":true,\"AllowComments\":false,\"AllowAttachments\":false,\"ShowOnCOA\":true,"
						+ "\"ShowHint\":false,\"Hint\":null,\"AllowCorrection\":false,\"ShowIsResolved\":false,\"PredefinedCorrections\":null,\"RepeatField\":false,"
						+ "\"DependencyRule\":null,\"ExpressionRule\":null,\"isNonRepeatable\":false,\"ParentId\":"+parentbody+",\"Name\":\""+fieldName+"\"}}";
				break;

			case FormFields.TIME:
				request = "{\"FormId\":\""+formId+"\",\"HasCompliance\":false,"
						+ "\"Field\":{\"level\":\"form\",\"type\":\"Time\",\"value\":null,\"children\":[],\"HasCompliance\":false,"
						+ "\"ElementType\":\"Field\",\"fieldHasCompliance\":true,\"ShortName\":\""+shortname+"\",\"Default\":null,\"DataSource\":null,"
						+ "\"Repeat\":null,\"IsRequired\":true,\"AllowComments\":false,\"AllowAttachments\":false,\"ShowOnCOA\":true,\"ShowHint\":false,"
						+ "\"Hint\":null,\"FailsForm\":true,\"AllowCorrection\":false,\"ShowIsResolved\":false,\"PredefinedCorrections\":null,"
						+ "\"RepeatField\":false,\"DependencyRule\":null,\"ExpressionRule\":null,\"isNonRepeatable\":false,\"ParentId\":"+parentbody+","
						+ "\"Name\":\""+fieldName+"\"}}";
				break;

			case FormFields.DATETIME:
				request = "{\"FormId\":\""+formId+"\",\"HasCompliance\":false,"
						+ "\"Field\":{\"level\":\"form\",\"type\":\"DateTime\",\"value\":null,\"children\":[],\"HasCompliance\":false,"
						+ "\"ElementType\":\"Field\",\"fieldHasCompliance\":true,\"ShortName\":\""+shortname+"\",\"Default\":null,\"DataSource\":null,"
						+ "\"Repeat\":null,\"IsRequired\":true,\"AllowComments\":false,\"AllowAttachments\":false,\"ShowOnCOA\":true,\"ShowHint\":false,"
						+ "\"Hint\":null,\"FailsForm\":true,\"AllowCorrection\":false,\"ShowIsResolved\":false,\"PredefinedCorrections\":null,"
						+ "\"RepeatField\":false,\"DependencyRule\":null,\"ExpressionRule\":null,\"isNonRepeatable\":false,\"ParentId\":"+parentbody+","
						+ "\"Name\":\""+fieldName+"\"}}";
				break;

			case FormFields.PARAGRAPH:
				request = "{\"FormId\":\""+formId+"\",\"HasCompliance\":false,"
						+ "\"Field\":{\"level\":\"form\",\"type\":\"Paragraph\",\"value\":null,\"children\":[],\"HasCompliance\":false,"
						+ "\"ElementType\":\"Field\",\"fieldHasCompliance\":false,\"ShortName\":\""+shortname+"\",\"Default\":null,\"IsRequired\":true,"
						+ "\"AllowComments\":false,\"AllowAttachments\":false,\"ShowOnCOA\":true,\"ShowHint\":false,\"Hint\":null,\"RepeatField\":false,"
						+ "\"DependencyRule\":null,\"isNonRepeatable\":false,\"ParentId\":"+parentbody+",\"Name\":\""+fieldName+"\"}}";

				break;

			case FormFields.SINGLELINETEXT:
				request = "{\"FormId\":\""+formId+"\",\"HasCompliance\":false,"
						+ "\"Field\":{\"level\":\"form\",\"type\":\"SingleLineText\",\"value\":null,\"children\":[],\"HasCompliance\":false,"
						+ "\"ElementType\":\"Field\",\"fieldHasCompliance\":true,\"ShortName\":\""+shortname+"\",\"Default\":null,\"DataSource\":null,"
						+ "\"Repeat\":null,\"IsRequired\":true,\"AllowComments\":false,\"AllowAttachments\":false,\"ShowHint\":false,\"Hint\":null,"
						+ "\"FailsForm\":true,\"AllowCorrection\":false,\"ShowIsResolved\":false,\"PredefinedCorrections\":null,\"RepeatField\":false,"
						+ "\"DependencyRule\":null,\"ExpressionRule\":null,\"isNonRepeatable\":false,\"ParentId\":"+parentbody+",\"Name\":\""+fieldName+"\"}}";
				break;

			case FormFields.IDENTIFIER:
				request = "{\"FormId\":\""+formId+"\",\"HasCompliance\":false,"
						+ "\"Field\":{\"level\":\"form\",\"type\":\"Identifier\",\"value\":null,\"children\":[],\"HasCompliance\":false,"
						+ "\"ElementType\":\"Field\",\"fieldHasCompliance\":false,\"ShortName\":\""+shortname+"\",\"InputType\":{\"Id\":\"FreeText\","
						+ "\"Name\":\"Free Text\"},\"InputMask\":null,\"DataSource\":null,\"IdentifierOption\":null,\"IsFilterable\":true,"
						+ "\"IsRequired\":true,\"ShowOnCOA\":true,\"ShowHint\":false,\"Hint\":null,\"RepeatField\":false,\"DependencyRule\":null,"
						+ "\"isNonRepeatable\":false,\"ParentId\":"+parentbody+",\"Name\":\""+fieldName+"\"}}";
				break;

			case FormFields.NOTE:
				request = "{\"FormId\":\""+formId+"\",\"HasCompliance\":false,"
						+ "\"Field\":{\"level\":\"header\",\"type\":\"Note\",\"value\":null,\"children\":[],\"HasCompliance\":false,"
						+ "\"ElementType\":\"Field\",\"fieldHasCompliance\":false,\"isNonRepeatable\":false,\"ShortName\":\""+shortname+"\","
						+ "\"ParentId\":null,\"Name\":\""+fieldName+"\"}}";
				break;

			case FormFields.RELATEDDOCS:
				String path = req.docUploadPath(environment);
				request = "{\"FormId\":\""+formId+"\",\"HasCompliance\":false,"
						+ "\"Field\":{\"level\":\"header\",\"type\":\"RelatedDocs\",\"value\":null,\"children\":[],\"HasCompliance\":false,"
						+ "\"ElementType\":\"Field\",\"fieldHasCompliance\":false,\"RelatedDocuments\":[{\"Key\":{\"Name\":\"upload.png\","
						+ "\"Id\":\""+DocumentDetails.documentTypeId+"\",\"DocType\":\""+DocumentDetails.docTypeName+"\","
						+ "\"Url\":\""+path+"/"+DocumentDetails.documentuploadId+"/upload.png\"},"
						+ "\"Value\":[{\"Subject\":{\"ID\":\""+resourceTreeId+"\",\"Name\":\""+resourceTreeName+"\"},"
						+ "\"IsVisible\":true}]}],\"isNonRepeatable\":false,\"ParentId\":null}}";
				break;

			case FormFields.SECTION :
				request = "{\"FormId\":\""+formId+"\",\"HasCompliance\":false,"
						+ "\"Field\":{\"level\":\"form\",\"type\":\"None\",\"value\":null,\"children\":[],\"HasCompliance\":false,"
						+ "\"ElementType\":\"Section\",\"fieldHasCompliance\":false,\"ShortName\":\""+shortname+"\",\"isNonRepeatable\":false,"
						+ "\"ParentId\":null,\"Name\":\""+fieldName+"\"}}";
				break;

			default:
				logError("Correct type not selected");
			}

		}catch(Exception e) {
			logInfo("Not able to get add field payload");
		}
		return request;
	}

	/**
	 * This method is used to get resource tree details
	 * @author choubey_a	
	 * @param resourceId - id of the resource Instance
	 * @param categoryname - name of the resource category
	 * @param resourceInstanceName - name of the resource Instance
	 * @return true of resource tree details are fetched
	 */

	public boolean getResourceTree(String resourceId, String categoryname, String resourceInstanceName) {

		try {

			Map<String, String> headers = new HashMap<String, String>();
			headers.put("Content-Type", "application/json");
			headers.put("Accept", "application/json, text/plain, /");
			headers.put("Authorization", "Bearer " + accessToken);

			String request = "{\"Type\":\""+resourceId+"\","
					+ "\"ItemType\":\"Resource\",\"IsHideDisabled\":true,\"ShowHistoricalData\":false}";

			Response response = apiUtils.submitRequest(METHOD_TYPE.POST, request, headers,
					GetHierarchy);

			MultipleDataResponse mdr = gson.fromJson(response.asString(),
					MultipleDataResponse.class);

			for(int i=0; i < mdr.Data.size() ; i++) {
				if(mdr.Data.get(i).Name.equals(categoryname)) {
					resourceCatId = mdr.Data.get(i).Id;	
					logInfo("Category Id = "+resourceCatId);
				}
			}

			String request7 = "{\"type\":\""+resourceId+"\",\"node\":\""+resourceCatId+"\",\"isparent\":true}";

			Response response7 = apiUtils.submitRequest(METHOD_TYPE.POST, request7, headers,
					GetChildResources);

			MultipleDataResponse mdr7 = gson.fromJson(response7.asString(),
					MultipleDataResponse.class);

			for(int i =0; i < mdr7.Data.size(); i++) {
				if(mdr7.Data.get(i).Name .contains(resourceInstanceName)){
					resourceTreeName = mdr7.Data.get(i).Name;
					resourceTreeId = mdr7.Data.get(i).ID;
				}
			}
			return true;
		}catch(Exception e) {
			logInfo("Not able to get resource Tree Details -"+e.getMessage());
			return false;
		}

	}

	/**
	 * This method is used to get submit form json body
	 * @author choubey_a	
	 * @param FormFieldId -  ID's of the field added in the form
	 * @return AddField - the json body of the submit form design
	 */

	public String submitFormDesignJson(List<String> FormFieldId) {
		String AddField="";

		for(int i=0;i<FormFieldId.size(); i++) {
			if(i== FormFieldId.size()-1) {
				AddField = AddField+"{\"Id\":\""+FormFieldId.get(i)+"\",\"Children\":[]}";
				break;
			}
			AddField = AddField +"{\"Id\":\""+FormFieldId.get(i)+"\",\"Children\":[]},";			
		}

		return AddField;

	}
	
	/**
	 * This method is used to create Form
	 * @author choubey_a	
	 * @param formname, type - formtype
	 * @param FormLevelFields - fields to be added in the form
	 * @param fieldnames - fieldnames and shortnames
	 * @param Section - if needed
	 * @return true if form is created
	 */

	public String createForm(String formname, String type, List<String> FormLevelFields, HashMap<String,String> fieldnames, boolean Section, boolean header) {

		String formId = null;
		List<String> fieldId = new ArrayList<String>();
		String request4 = null;

		try {

			Map<String, String> headers = new HashMap<String, String>();
			headers.put("Content-Type", "application/json");
			headers.put("Accept", "application/json, text/plain, /");
			headers.put("Authorization", "Bearer " + accessToken);

			String request1 = "{\"Id\":null,\"Name\":\""+formname+"\",\"Type\":\""+type+"\","
					+ "\"Resources\":[{\"ID\":\""+resourceTreeId+"\","
					+ "\"Name\":\""+resourceTreeName+"\"}]}";

			Response response1 = apiUtils.submitRequest(METHOD_TYPE.POST, request1, headers,
					SaveFormDesign);

			SingleDataResponse sdr = gson.fromJson(response1.asString(),
					SingleDataResponse.class);

			formId = sdr.Data;

			String fieldIdDetails;

			if(Section == true) {
				String request7= addField("Section", "Section1",formId, FormFields.SECTION , null);
				Response response7 = apiUtils.submitRequest(METHOD_TYPE.POST, request7, headers,
						AddField);
				SingleDataResponse sdr7 = gson.fromJson(response7.asString(),
						SingleDataResponse.class);
				String sectionId = sdr7.Data;

				int i = 0;
				for (Map.Entry<String, String> entry : fieldnames.entrySet()) {
					String FieldName = entry.getKey();
					String ShortName = entry.getValue();			
					String request2 = addField(FieldName, ShortName,formId, FormLevelFields.get(i).toString(), sectionId);
					i++;
					Response response2 = apiUtils.submitRequest(METHOD_TYPE.POST, request2, headers,
							AddField);
					SingleDataResponse sdr1 = gson.fromJson(response2.asString(),
							SingleDataResponse.class);

					fieldId.add(sdr1.Data);

					fieldIdDetails = submitFormDesignJson(fieldId);

					if(header == true) {

						String feildIdDetails = submitFormDesignJson(fieldId);

						String request3 = addField("API Automation Form Notes", "Note",formId, FormFields.NOTE, null);
						Response response3 = apiUtils.submitRequest(METHOD_TYPE.POST, request3, headers,
								AddField);
						SingleDataResponse single = gson.fromJson(response3.asString(),
								SingleDataResponse.class);

						String noteId = single.Data;

						String request5 = addField("DocUpload", "Doc",formId, FormFields.RELATEDDOCS, null);
						Response response5 = apiUtils.submitRequest(METHOD_TYPE.POST, request5, headers,
								AddField);
						SingleDataResponse sdr2 = gson.fromJson(response5.asString(),
								SingleDataResponse.class);

						String relatedDocId = sdr2.Data;

						request4 = "{\r\n" + 
								"    \"formId\": \""+formId+"\",\r\n" + 
								"    \"formLevel\": [\r\n" + 
								"        {\r\n" + 
								"            \"Children\": ["+feildIdDetails+"],\r\n" + 
								"            \"Id\": \""+sectionId+"\"\r\n" + 
								"        }\r\n" + 
								"    ],\r\n" + 
								"    \"formVersionId\": \"\",\r\n" + 
								"    \"headerLevel\": [\r\n" + 
								"        {\r\n" + 
								"            \"Children\": [\r\n" + 
								"            ],\r\n" + 
								"            \"Id\": \""+noteId+"\"\r\n" + 
								"        },\r\n" + 
								"        {\r\n" + 
								"            \"Children\": [\r\n" + 
								"            ],\r\n" + 
								"            \"Id\": \""+relatedDocId+"\"\r\n" + 
								"        }\r\n" + 
								"    ],\r\n" + 
								"    \"jobLevel\": null,\r\n" + 
								"    \"taskLevel\": null\r\n" + 
								"}";	
					}

					else {
						String feildIdDetails = submitFormDesignJson(fieldId);
						request4 = "{\r\n" + 
								"    \"formId\": \""+formId+"\",\r\n" + 
								"    \"formLevel\": [\r\n" + 
								"        {\r\n" + 
								"            \"Children\": ["+feildIdDetails+"],\r\n" + 
								"            \"Id\": \""+sectionId+"\"\r\n" + 
								"        }\r\n" + 
								"    ],\r\n" + 
								"    \"formVersionId\": \"\",\r\n" + 
								"    \"headerLevel\": [\r\n" + 
								"    ],\r\n" + 
								"    \"jobLevel\": null,\r\n" + 
								"    \"taskLevel\": null\r\n" + 
								"}\r\n" + 
								"";
					}
				}
			}

			else {
				int i = 0;
				for (Map.Entry<String, String> entry : fieldnames.entrySet()) {
					String FieldName = entry.getKey();
					String ShortName = entry.getValue();			
					String request2 = addField(FieldName, ShortName,formId, FormLevelFields.get(i).toString(), null);
					i++;
					Response response2 = apiUtils.submitRequest(METHOD_TYPE.POST, request2, headers,
							AddField);
					SingleDataResponse sdr1 = gson.fromJson(response2.asString(),
							SingleDataResponse.class);

					fieldId.add(sdr1.Data);

					fieldIdDetails = submitFormDesignJson(fieldId);

					if(header == true) {

						String request3 = addField("API Automation Form Notes", "Note",formId, FormFields.NOTE, null);
						Response response3 = apiUtils.submitRequest(METHOD_TYPE.POST, request3, headers,
								AddField);
						SingleDataResponse single1 = gson.fromJson(response3.asString(),
								SingleDataResponse.class);

						String noteId = single1.Data;

						String request5 = addField("DocUpload", "Doc",formId, FormFields.RELATEDDOCS, null);
						Response response5 = apiUtils.submitRequest(METHOD_TYPE.POST, request5, headers,
								AddField);
						SingleDataResponse sdr2 = gson.fromJson(response5.asString(),
								SingleDataResponse.class);

						String relatedDocId = sdr2.Data;

						request4 = "{\"formId\":\""+formId+"\",\"formVersionId\":\"\","
								+ "\"formLevel\":["+fieldIdDetails+"],"
								+ "\"jobLevel\":null,\"taskLevel\":null,\"headerLevel\":[{\"Id\":\""+noteId+"\","
								+ "\"Children\":[]},{\"Id\":\""+relatedDocId+"\",\"Children\":[]}]}" ;
					}

					else {

						request4 = "{\"formId\":\""+formId+"\",\"formVersionId\":\"\","
								+ "\"formLevel\":["+fieldIdDetails+"],"
								+ "\"jobLevel\":null,\"taskLevel\":null,\"headerLevel\":[]}" ;


					}
				}
			}

			Response response4 = apiUtils.submitRequest(METHOD_TYPE.POST, request4, headers,
					SubmitFormDesign);

			SingleDataResponse sdr3 = gson.fromJson(response4.asString(),
					SingleDataResponse.class);

			if(!sdr3.Status == true) {
				logError("Form not submitted");
			}

			String request6 = "{\"Data\":{\"Id\":\""+formId+"\",\"Name\":\""+formname+"\","
					+ "\"Type\":\"Check\",\"Version\":\"0.1\",\"Comment\":\"\"}}";

			Response response6 = apiUtils.submitRequest(METHOD_TYPE.POST, request6, headers,
					ReleaseForm);

			SingleDataResponse sdr4 = gson.fromJson(response6.asString(),
					SingleDataResponse.class);

			if(!sdr4.Status == true) {
				logError("Form not released");
			}
			logInfo("Form created "+formname);
			return formId;
		} catch (Exception e) {
			logError("Failed to create Form - "+ e.getMessage());
			return formId;
		}
	}


	public class MultipleDataResponse {		
		public List<Data> Data;
	}

	public class SingleDataResponse{
		public String Data;
		public boolean Status;

	}

	public class SimpleDataResponse{
		public String Data;

	}

	public class Data {
		public String ID;
		public String Id;
		public String Name;
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

	public class FieldDetails {
		public String fieldName;
		public String shortname;
		public String fieldType;
	}

	public static class DocumentDetails{
		public static String documentuploadId;
		public static String documentTypeId;
		public static String docTypeName;
	}

	public static class FormTypes{
		public static final String CHECK = "Check";
		public static final String AUDIT = "Audit";
		public static final String QUESTIONNAIRE = "Questionnaire";
	}

}
