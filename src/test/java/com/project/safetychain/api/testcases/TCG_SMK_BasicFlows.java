package com.project.safetychain.api.testcases;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.project.safetychain.api.models.Auth;
import com.project.safetychain.api.models.Auth.SimpleDataResponse;
import com.project.safetychain.api.models.Auth.SingleDataResponse;
import com.project.safetychain.api.models.FormDesigner;
import com.project.safetychain.api.models.ManageLocation;
import com.project.safetychain.api.models.ManageResources;
import com.project.safetychain.api.models.ResourceDesigner;
import com.project.safetychain.api.models.support.Support;
import com.project.safetychain.api.models.support.Support.DPT_FIELD_TYPES;
import com.project.safetychain.api.models.support.Support.DPT_FORM_TYPES;
import com.project.safetychain.api.models.support.Support.FormFieldParams;
import com.project.safetychain.api.models.support.Support.FormParams;
import com.project.safetychain.api.models.support.Support.IDENTIFIER_TYPES;
import com.project.safetychain.api.models.support.Support.RESOURCE_TYPES;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.utilities.ApiUtils;
import com.project.utilities.ApiUtils.METHOD_TYPE;
import com.project.utilities.CommonMethods;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TCG_SMK_BasicFlows extends TestBase {

	Auth auth = null;
	ApiUtils apiUtils = null;
	ResourceDesigner resourceDesigner = null;
	ManageLocation manageLocation = null;
	ManageResources manageResources = null;
	FormDesigner formDesigner = null;
	public static String locCatName = null;
	public static String custCatName = null;
	public static String locInstName = null;
	public static String custInstName = null;
	public static String createdLocCatId = null;
	public static String createdCustCatId = null;
	public static String createdLocInstId = null;
	public static String createdCustInstId = null;
	public static String createdFormId = null;
	public static String formName = null;
	public static String text = "abc", number = "3";

	@BeforeClass(alwaysRun = true)
	public void groupInit() {

		manageLocation = new ManageLocation();
		manageResources = new ManageResources();
		formDesigner = new FormDesigner();

		locCatName = CommonMethods.dynamicText("API Loc_Cat");
		custCatName = CommonMethods.dynamicText("API Cust_Cat");
		locInstName = CommonMethods.dynamicText("API Loc_Inst");
		custInstName = CommonMethods.dynamicText("API Cust_Inst");
		formName = CommonMethods.dynamicText("API Chk");

		auth = new Auth();
		auth.setAccessToken();

		resourceDesigner = new ResourceDesigner();
		resourceDesigner.setResourceCatIDs();

//		if(hp.error) {
//			TCGFailureMsg = "Could NOT login to application";
//			logFatal(TCGFailureMsg);
//			throw new SkipException(TCGFailureMsg);
//		}

	}

	@Test(description = "Create Location category")
	public void createResourceCategory_Location() {

		apiUtils = new ApiUtils();
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("Accept", "application/json, text/plain, /");
		headers.put("Authorization", "Bearer " + accessToken);

		String addCatJson = "{\r\n" + "    \"BaseTypeID\": \"" + locationCatID + "\",\r\n" + "    \"Name\": \""
				+ locCatName + "\",\r\n" + "    \"ID\": null,\r\n" + "    \"ResourceTypeID\": \"" + locationCatID
				+ "\"\r\n" + "}";
		Response response = apiUtils.submitRequest(METHOD_TYPE.POST, addCatJson, headers,
				resourceDesigner.AddResourceCatUrl);
		Gson gson = new GsonBuilder().create();
		SingleDataResponse sdr = gson.fromJson(response.asString(), SingleDataResponse.class);
		createdLocCatId = sdr.Data.Id;
		TestValidation.IsTrue((sdr.Status), "Location Category " + locCatName + " is created",
				"Could NOT create location category " + locCatName);

	}

	@Test(description = "Create Location instance")
	public void createResourceInstance_Location() {

//		createdLocCatId="1bbbb840-2945-4d3d-9406-4459f5b89eeb";

		List<String> listOfEnabledFields = new ArrayList<String>();
		listOfEnabledFields = resourceDesigner.createResFieldsJson(RESOURCE_TYPES.LOCATION);
		String fieldDets = resourceDesigner.updateResFieldsJson(listOfEnabledFields, locInstName, number, text);

		apiUtils = new ApiUtils();
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("Accept", "application/json, text/plain, /");
		headers.put("Authorization", "Bearer " + accessToken);

		String addInstJson = "{\r\n" + "    \"ParentBusinessObjectId\": \"" + createdLocCatId + "\",\r\n"
				+ "    \"Name\": \"" + locInstName + "\",\r\n" + "    \"ResourceTypeId\": \"" + locationCatID
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
				manageLocation.AddLocationUrl);
		Gson gson = new GsonBuilder().create();
		SingleDataResponse sdr = gson.fromJson(response.asString(), SingleDataResponse.class);
		createdLocInstId = sdr.Data.Id;
		TestValidation.IsTrue((sdr.Status), "Location Instance " + locCatName + " is created",
				"Could NOT create location Instance " + locCatName);

	}

	@Test(description = "Create Customers category")
	public void createResourceCategory_Customers() {

		apiUtils = new ApiUtils();
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("Accept", "application/json, text/plain, /");
		headers.put("Authorization", "Bearer " + accessToken);

		String addCatJson = "{\r\n" + "    \"BaseTypeID\": \"" + customersCatID + "\",\r\n" + "    \"Name\": \""
				+ custCatName + "\",\r\n" + "    \"ID\": null,\r\n" + "    \"ResourceTypeID\": \"" + customersCatID
				+ "\"\r\n" + "}";
		Response response = apiUtils.submitRequest(METHOD_TYPE.POST, addCatJson, headers,
				resourceDesigner.AddResourceCatUrl);
		Gson gson = new GsonBuilder().create();
		SingleDataResponse sdr = gson.fromJson(response.asString(), SingleDataResponse.class);
		createdCustCatId = sdr.Data.Id;
		TestValidation.IsTrue((sdr.Status), "Customers Category " + custCatName + " is created",
				"Could NOT create customers category " + custCatName);

	}

	@Test(description = "Create Customers instance")
	public void createResourceInstance_Customers() {

		// name of cust cat = API Cust_Cat_12.11_15.46.39
//		createdCustCatId="2441cb07-9807-4a7c-a661-9dedfa1d176a";

		List<String> listOfEnabledFields = new ArrayList<String>();
		listOfEnabledFields = resourceDesigner.createResFieldsJson(RESOURCE_TYPES.CUSTOMERS);
		String fieldDets = resourceDesigner.updateResFieldsJson(listOfEnabledFields, custInstName, number, text);

		apiUtils = new ApiUtils();
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("Accept", "application/json, text/plain, /");
		headers.put("Authorization", "Bearer " + accessToken);

		String addInstJson = "{\r\n" + "    \"BaseTypeID\": \"" + createdCustCatId + "\",\r\n" + "    \"Name\": \""
				+ custInstName + "\",\r\n" + "    \"ResourceTypeID\": \"" + customersCatID + "\",\r\n"
				+ "    \"ResourceValue\": [\r\n" + "        {\r\n" + "            \"FieldName\": \"SCSModifiedBy\",\r\n"
				+ "            \"FieldId\": \"4537e8bb-7456-4268-9a27-70e0d373d8e9\",\r\n"
				+ "            \"Value\": null,\r\n" + "            \"Type\": \"Label\"\r\n" + "        },\r\n"
				+ "        {\r\n" + "            \"FieldName\": \"SCSLastModified\",\r\n"
				+ "            \"FieldId\": \"5ab261b4-7eaf-4628-b082-935f7a7f87db\",\r\n"
				+ "            \"Value\": null,\r\n" + "            \"Type\": \"Label\"\r\n" + "        },\r\n"
				+ "		 {\r\n" + "			 \"FieldName\":\"SCSStatus\",\r\n"
				+ "			 \"FieldId\":\"a4e02f2d-9205-4434-ac53-425f11aa0dfc\",\r\n"
				+ "			 \"Value\":null,\r\n" + "			 \"Type\":\"Toggle\"\r\n" + "        },\r\n" + fieldDets
				+ "    ]\r\n" + "}";
		Response response = apiUtils.submitRequest(METHOD_TYPE.POST, addInstJson, headers,
				manageResources.AddNewResourceUrl);
		Gson gson = new GsonBuilder().create();
		SingleDataResponse sdr = gson.fromJson(response.asString(), SingleDataResponse.class);
		createdCustInstId = sdr.Data.Id;
		TestValidation.IsTrue((sdr.Status), "Location Instance " + locCatName + " is created",
				"Could NOT create location Instance " + locCatName);

	}

	@Test(description = "Create and Release Check Form")
	public void createAndReleaseForm_Check_2() {

		// Numeric, Free Text, 2 Identifiers
		apiUtils = new ApiUtils();
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Accept-Language", "en-US,en;q=0.5");
		headers.put("Accept", "application/json, text/plain, /");
		headers.put("Content-Type", "application/json;charset=utf-8");
		headers.put("Authorization", "Bearer " + accessToken);

//			Timezone	-330
//			DNT	1
//			Accept-Encoding	gzip, deflate, br
//			User-A/gent	Mozilla/5.0 (Windows NT 6.1; WOW64; rv:54.0) Gecko/20100101 Firefox/54.0
//			Authorization	Bearer ${JWToken}

//		headers.put("Authorization", "Bearer eyJhbGciOiJSUzI1NiIsImtpZCI6IjAyNkIzMUM5MDJEQ0VCNzQ4NkIxNURGMTRGQ0M1NEU0MjUzMzM4RTciLCJ0eXAiOiJhdCtqd3QiLCJ4NXQiOiJBbXN4eVFMYzYzU0dzVjN4VDh4VTVDVXpPT2MifQ.eyJuYmYiOjE2MDY3MzkwMzAsImV4cCI6MTYwNjczOTMzMCwiaXNzIjoiaHR0cHM6Ly9xYS5pZGVudGl0eS5zYWZldHljaGFpbi5jb20iLCJhdWQiOiJ3ZWJhcGkiLCJjbGllbnRfaWQiOiJzY3NXZWJBcHBfdGVzdDEiLCJzdWIiOiI3ZTgwYzQ3NS05MTkyLTRhNDMtODY5My0wNWFiNWQ3YTkyYWUiLCJhdXRoX3RpbWUiOjE2MDY3MzkwMjksImlkcCI6ImxvY2FsIiwicHJlZmVycmVkX3VzZXJuYW1lIjoiYXV0b3VzZXIwMSIsInRpbWV6b25lIjoiVVMvUGFjaWZpYyIsInRlbmFudCI6InRlc3QxIiwiaXNwYXJ0bmVycG9ydGFsdXNlciI6ImZhbHNlIiwicm9sZSI6IlN1cGVyQWRtaW4iLCJmaXJzdG5hbWUiOiJBdXRvIiwibGFzdG5hbWUiOiJVc2VyIiwiaGFzYWxsbG9jYXRpb25zIjoiVHJ1ZSIsImhhc2FsbHN1cHBsaWVycyI6IkZhbHNlIiwic2NvcGUiOlsib3BlbmlkIiwicHJvZmlsZSIsIndlYmFwaSJdLCJhbXIiOlsicHdkIl19.RscDu29ULv16tSx982Z6OafTfYuGjF-a257OXLZRi_3srAlHq5bu9T3blCsFb0aP_ysMiZhQzSl4dK7EdMF64awNZLOEGGFrZ25gLWsgNEU2flZ4TLZaKPPdNK0320yyXY3LdgdiZGBMBGybkfuJsoziNnoKS_yudhvVC6caizKHz7dmdqtKtvf7ppZaqPHU9KpZaKPoglF_i9X7qxzKoWraShgrwsjFfJ-jObmB678yiB2Zg3RAdG3TEeqj_RsvgHhQO9QHeJL8DTmSDynkrcgeuz9MRYS_dinewtWzWR3PaiTqOtTSSOL40gGgzmib3C3vHII-x66h-0npPTUyQg");

		// 1. Save Form - Name and Resources
//		List<String> ids = new ArrayList<String>();
//		ids.add("9806621c-7e0e-4665-b7f5-890931aaafee");
//		ids.add("f6c52945-46d7-4d53-be6e-d38acee7b29a");
//		String fieldDets = Support.updateIdList(ids, "ID");

//		String saveFormJson = "{\r\n" + 
//				"  \"Id\": null,\r\n" + 
//				"  \"Name\": \""+formName+"\",\r\n" + 
//				"  \"Type\": \""+FORM_TYPES.CHECK+"\",\r\n" + 
//				"  \"Resources\": [\r\n" + 
//				fieldDets +
//				"  ]\r\n" + 
//				"}";

		String saveFormJson = "{\r\n" + "	\"Id\": null,\r\n" + "	\"Name\": \"AH13Jan Test3\",\r\n"
				+ "	\"Type\": \"Check\",\r\n" + "	\"Resources\": [{\r\n"
				+ "		\"ID\": \"7eea543c-55a4-443a-97de-d676a05366cb\",\r\n"
				+ "		\"Name\": \"Customers > AH21OctCust > AH21OctCust\"\r\n" + "	}]\r\n" + "}";
		Response saveResponse = apiUtils.submitRequest(METHOD_TYPE.POST, saveFormJson, headers,
				formDesigner.SaveFormDesignUrl);
		Gson gson = new GsonBuilder().create();
		SimpleDataResponse saveRes = gson.fromJson(saveResponse.asString(), SimpleDataResponse.class);
		createdFormId = saveRes.Data;

//		//2. Add Fields - c30e956f-9877-4089-983b-fe08b0ed0cb3
		// Numeric
		FormFieldParams field1 = new FormFieldParams();
//		field1.FormId=createdFormId;
//		field1.type=FIELD_TYPES.NUMERIC;
		field1.Name = "Number";
//		String field1ID = formDesigner.addFieldToForm(field1);

		String addField = "{\r\n" + "	\"FormId\": \"" + createdFormId + "\",\r\n" + "	\"HasCompliance\": false,\r\n"
				+ "	\"Field\": {\r\n" + "		\"level\": \"form\",\r\n" + "		\"type\": \"FreeText\",\r\n"
				+ "		\"value\": null,\r\n" + "		\"children\": [],\r\n" + "		\"HasCompliance\": false,\r\n"
				+ "		\"ElementType\": \"Field\",\r\n" + "		\"fieldHasCompliance\": true,\r\n"
				+ "		\"ShortName\": \"Text\",\r\n" + "		\"Default\": null,\r\n"
				+ "		\"DataSource\": null,\r\n" + "		\"Repeat\": null,\r\n" + "		\"FailsForm\": true,\r\n"
				+ "		\"IsRequired\": true,\r\n" + "		\"AllowComments\": false,\r\n"
				+ "		\"AllowAttachments\": false,\r\n" + "		\"ShowOnCOA\": true,\r\n"
				+ "		\"ShowHint\": false,\r\n" + "		\"Hint\": null,\r\n"
				+ "		\"AllowCorrection\": false,\r\n" + "		\"ShowIsResolved\": false,\r\n"
				+ "		\"PredefinedCorrections\": null,\r\n" + "		\"RepeatField\": false,\r\n"
				+ "		\"DependencyRule\": null,\r\n" + "		\"ExpressionRule\": null,\r\n"
				+ "		\"isNonRepeatable\": false,\r\n" + "		\"ParentId\": null,\r\n"
				+ "		\"Name\": \"Text\"\r\n" + "	}\r\n" + "}";
		Response response = apiUtils.submitRequest(METHOD_TYPE.POST, addField, headers, formDesigner.AddFieldUrl);
		// Gson gson = new GsonBuilder().create();
		SimpleDataResponse sdr = gson.fromJson(response.asString(), SimpleDataResponse.class);
		String field1ID = sdr.Data;

//		//Free Text
//		FormFieldParams field2 = new FormFieldParams();
//		field2.FormId=createdFormId;
//		field2.type=FIELD_TYPES.FREE_TEXT;
//		field2.Name="Text";
//		String field2ID = formDesigner.addFieldToForm(field2);
//		
//		//Identifier 1 - Free Text
//		FormFieldParams field3 = new FormFieldParams();
//		field3.FormId=createdFormId;
//		field3.type=FIELD_TYPES.IDENTIFIER;
//		field3.Name="ID1";
//		field3.identifierId=IDENTIFIER_TYPES.FREE_TEXT;
//		field3.InputMask="000";
//		String field3ID = formDesigner.addFieldToForm(field3);
//		
//		//Identifier 2 - Select One
//		FormFieldParams field4 = new FormFieldParams();
//		field4.FormId=createdFormId;
//		field4.type=FIELD_TYPES.IDENTIFIER;
//		field4.Name="ID2";
//		field4.identifierId=IDENTIFIER_TYPES.SELECT_ONE;
//		field4.IdentifierOption="[\"S1\",\"S2\"]";
//		String field4ID = formDesigner.addFieldToForm(field4);

		// 3. Save Form - With Fields
		List<String> formFields = new ArrayList<String>();
		formFields.add(field1ID);
//		formFields.add(field2ID);
//		formFields.add(field3ID);
//		formFields.add(field4ID);
		Support.updateIdList(formFields, "Id");

//		String submitFormJson = "{\r\n" + 
//				"  \"formId\": \""+createdFormId+"\",\r\n" + 
//				"  \"formVersionId\": \"\",\r\n" + 
//				"  \"formLevel\": [\r\n" + 
//				saveDets + 
//				"  ],\r\n" + 
//				"  \"jobLevel\": null,\r\n" + 
//				"  \"taskLevel\": null,\r\n" + 
//				"  \"headerLevel\": []\r\n" + 
//				"}";

		String submitFormJson = "{\r\n" + "	\"formId\": \"" + createdFormId + "\",\r\n"
				+ "	\"formVersionId\": \"\",\r\n" + "	\"formLevel\": [{\r\n" + "		\"Id\": \"" + field1ID
				+ "\",\r\n" + "		\"Children\": []\r\n" + "	}],\r\n" + "	\"jobLevel\": null,\r\n"
				+ "	\"taskLevel\": null,\r\n" + "	\"headerLevel\": []\r\n" + "}";

		Response submitResponse = apiUtils.submitRequest(METHOD_TYPE.POST, submitFormJson, headers,
				formDesigner.SubmitFormUrl);
		SimpleDataResponse submitRes = gson.fromJson(submitResponse.asString(), SimpleDataResponse.class);
		TestValidation.IsTrue((submitRes.Status), "Form named " + formName + " is saved",
				"Could NOT save form " + formName);

		// 4. Validate Saved Form
		String validateAndReleaseFormJson = "{\r\n" + "  \"Data\": {\r\n" + "    \"Id\": \"" + createdFormId + "\"\r\n"
				+ "  }\r\n" + "}";
		Response validateResponse = apiUtils.submitRequest(METHOD_TYPE.POST, validateAndReleaseFormJson, headers,
				formDesigner.ValidateReleaseUrl);
		SimpleDataResponse validateRes = gson.fromJson(validateResponse.asString(), SimpleDataResponse.class);
		TestValidation.IsTrue((validateRes.Status), "Form named " + formName + " is validated",
				"Could NOT validate form " + formName);

		// 5. Release Saved Form
		Response releaseResponse = apiUtils.submitRequest(METHOD_TYPE.POST, validateAndReleaseFormJson, headers,
				formDesigner.ReleaseFormUrl);
		SimpleDataResponse releaseRes = gson.fromJson(releaseResponse.asString(), SimpleDataResponse.class);
		TestValidation.IsTrue((releaseRes.Status), "Form named " + formName + " is released",
				"Could NOT release form " + formName);
	}

	@Test(description = "Compliance Release form - Check")
	public void createAndReleaseForm_Check_AllFields() {

		createResourceCategory_Location();
		createResourceInstance_Location();
		createResourceCategory_Customers();
		createResourceInstance_Customers();

		// Numeric, Free Text, 2 Identifiers
		apiUtils = new ApiUtils();
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("Accept", "application/json, text/plain, /");
		headers.put("Authorization", "Bearer " + accessToken);

		// Fields
//		FormFieldParams freeTextField = new FormFieldParams();
//		freeTextField.DPTFieldType = DPT_FIELD_TYPES.FREE_TEXT;
//		freeTextField.Name = "Free Text";
//		
//		FormFieldParams paraTextField = new FormFieldParams();
//		paraTextField.DPTFieldType = DPT_FIELD_TYPES.PARAGRAPH_TEXT;
//		paraTextField.Name = "Para Text";
//		
//		FormFieldParams numericField = new FormFieldParams();
//		numericField.DPTFieldType = DPT_FIELD_TYPES.NUMERIC;
//		numericField.Name = "Numeric";
//		
//		FormFieldParams dateField = new FormFieldParams();
//		dateField.DPTFieldType = DPT_FIELD_TYPES.DATE;
//		dateField.Name = "Date";
//		
//		FormFieldParams timeField = new FormFieldParams();
//		timeField.DPTFieldType = DPT_FIELD_TYPES.TIME;
//		timeField.Name = "Time";
//		
//		FormFieldParams dateTimeField = new FormFieldParams();
//		dateTimeField.DPTFieldType = DPT_FIELD_TYPES.DATE_TIME;
//		dateTimeField.Name = "Date Time";

		FormFieldParams identifierField = new FormFieldParams();
		identifierField.DPTFieldType = DPT_FIELD_TYPES.IDENTIFIER;
		identifierField.Name = "Identifier";
		identifierField.identifierId = IDENTIFIER_TYPES.FREE_TEXT;
		identifierField.InputMask = "000";

		FormFieldParams selectOneField = new FormFieldParams();
		selectOneField.DPTFieldType = DPT_FIELD_TYPES.SELECT_ONE;
		selectOneField.Name = "Select One";
		selectOneField.SelectOptions = Arrays.asList("1", "2", "3");

		FormFieldParams selectMultipleField = new FormFieldParams();
		selectMultipleField.DPTFieldType = DPT_FIELD_TYPES.SELECT_MULTIPLE;
		selectMultipleField.Name = "Select Multiple";
		selectMultipleField.SelectOptions = Arrays.asList("1", "2", "3");

		String formId = apiUtils.getUUID();
		// Form details
		FormParams fp = new FormParams();
		fp.FormId = formId;
		fp.FormName = formName;
		fp.type = DPT_FORM_TYPES.CHECK;
		fp.ResourceType = RESOURCE_TYPES.CUSTOMERS;
//		fp.ResourceCategoryNm = "28JanResource";
//		fp.ResourceInstanceNm = "28JanR1";
		fp.ResourceCategoryNm = custCatName;
		fp.ResourceInstanceNm = custInstName;
		fp.formElements = Arrays.asList(identifierField, selectOneField, selectMultipleField);
//		fp.formElements = Arrays.asList(freeTextField, paraTextField, numericField, dateField, timeField,
//				dateTimeField, identifierField);

		// level 2 try for Select One/Multiple and Aggregate
		new GsonBuilder().create();
		boolean createForm = formDesigner.createUnreleasedForm(fp);
		TestValidation.IsTrue(createForm, "Form named " + formName + " is saved", "Could NOT save form " + formName);

		// 4. Validate Saved Form
		String validateAndReleaseFormJson = "{\r\n" + "  \"Data\": {\r\n" + "    \"Id\": \"" + formId + "\"\r\n"
				+ "  }\r\n" + "}";

		apiUtils.submitRequest(METHOD_TYPE.POST, validateAndReleaseFormJson, headers,
				formDesigner.ValidateReleaseUrl);
//		SimpleDataResponse validateRes = gson.fromJson(validateResponse.asString(), SimpleDataResponse.class);
//		TestValidation.IsTrue((validateRes.Status), "Form named " + formName + " is validated",
//				"Could NOT validate form " + formName);

		// 5. Release Saved Form
		apiUtils.submitRequest(METHOD_TYPE.POST, validateAndReleaseFormJson, headers,
				formDesigner.ReleaseFormUrl);
		// 6. Verify form is released
		boolean verifyForm = formDesigner.verifyIsFormCreated(RESOURCE_TYPES.CUSTOMERS, formName, headers);
		TestValidation.IsTrue(verifyForm, "Form named " + formName + " is released",
				"Could NOT release form " + formName);

	}

	@Test(description = "Create and Release Check Form")
	public void createAndReleaseForm_Check_3() {

		// Numeric, Free Text, 2 Identifiers
		apiUtils = new ApiUtils();
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("Accept", "application/json, text/plain, /");
		headers.put("Authorization", "Bearer " + accessToken);
//		headers.put("Authorization", "Bearer eyJhbGciOiJSUzI1NiIsImtpZCI6IjAyNkIzMUM5MDJEQ0VCNzQ4NkIxNURGMTRGQ0M1NEU0MjUzMzM4RTciLCJ0eXAiOiJhdCtqd3QiLCJ4NXQiOiJBbXN4eVFMYzYzU0dzVjN4VDh4VTVDVXpPT2MifQ.eyJuYmYiOjE2MDY3MzkwMzAsImV4cCI6MTYwNjczOTMzMCwiaXNzIjoiaHR0cHM6Ly9xYS5pZGVudGl0eS5zYWZldHljaGFpbi5jb20iLCJhdWQiOiJ3ZWJhcGkiLCJjbGllbnRfaWQiOiJzY3NXZWJBcHBfdGVzdDEiLCJzdWIiOiI3ZTgwYzQ3NS05MTkyLTRhNDMtODY5My0wNWFiNWQ3YTkyYWUiLCJhdXRoX3RpbWUiOjE2MDY3MzkwMjksImlkcCI6ImxvY2FsIiwicHJlZmVycmVkX3VzZXJuYW1lIjoiYXV0b3VzZXIwMSIsInRpbWV6b25lIjoiVVMvUGFjaWZpYyIsInRlbmFudCI6InRlc3QxIiwiaXNwYXJ0bmVycG9ydGFsdXNlciI6ImZhbHNlIiwicm9sZSI6IlN1cGVyQWRtaW4iLCJmaXJzdG5hbWUiOiJBdXRvIiwibGFzdG5hbWUiOiJVc2VyIiwiaGFzYWxsbG9jYXRpb25zIjoiVHJ1ZSIsImhhc2FsbHN1cHBsaWVycyI6IkZhbHNlIiwic2NvcGUiOlsib3BlbmlkIiwicHJvZmlsZSIsIndlYmFwaSJdLCJhbXIiOlsicHdkIl19.RscDu29ULv16tSx982Z6OafTfYuGjF-a257OXLZRi_3srAlHq5bu9T3blCsFb0aP_ysMiZhQzSl4dK7EdMF64awNZLOEGGFrZ25gLWsgNEU2flZ4TLZaKPPdNK0320yyXY3LdgdiZGBMBGybkfuJsoziNnoKS_yudhvVC6caizKHz7dmdqtKtvf7ppZaqPHU9KpZaKPoglF_i9X7qxzKoWraShgrwsjFfJ-jObmB678yiB2Zg3RAdG3TEeqj_RsvgHhQO9QHeJL8DTmSDynkrcgeuz9MRYS_dinewtWzWR3PaiTqOtTSSOL40gGgzmib3C3vHII-x66h-0npPTUyQg");

		String saveFormJson = "{\r\n" + "	\"Id\": null,\r\n" + "	\"Name\": \"30thNov Testing-134\",\r\n"
				+ "	\"Type\": \"Check\",\r\n" + "	\"Resources\": [{\r\n"
				+ "		\"ID\": \"7ec289d5-6023-4465-83c0-0ba4a885bd46\",\r\n"
				+ "		\"Name\": \"Customers > AH07SepCust > NazTest1\"\r\n" + "	}]\r\n" + "}";
		Response saveResponse = apiUtils.submitRequest(METHOD_TYPE.POST, saveFormJson, headers,
				formDesigner.SaveFormDesignUrl);
		Gson gson = new GsonBuilder().create();
		SimpleDataResponse saveRes = gson.fromJson(saveResponse.asString(), SimpleDataResponse.class);
		createdFormId = saveRes.Data;

//		//2. Add Fields - c30e956f-9877-4089-983b-fe08b0ed0cb3

		String addField = "{\r\n" + "	\"FormId\": \"" + createdFormId + "\",\r\n" + "	\"HasCompliance\": false,\r\n"
				+ "	\"Field\": {\r\n" + "		\"level\": \"form\",\r\n" + "		\"type\": \"FreeText\",\r\n"
				+ "		\"value\": null,\r\n" + "		\"children\": [],\r\n" + "		\"HasCompliance\": false,\r\n"
				+ "		\"ElementType\": \"Field\",\r\n" + "		\"fieldHasCompliance\": true,\r\n"
				+ "		\"ShortName\": \"Text\",\r\n" + "		\"Default\": null,\r\n"
				+ "		\"DataSource\": null,\r\n" + "		\"Repeat\": null,\r\n" + "		\"FailsForm\": true,\r\n"
				+ "		\"IsRequired\": true,\r\n" + "		\"AllowComments\": false,\r\n"
				+ "		\"AllowAttachments\": false,\r\n" + "		\"ShowOnCOA\": true,\r\n"
				+ "		\"ShowHint\": false,\r\n" + "		\"Hint\": null,\r\n"
				+ "		\"AllowCorrection\": false,\r\n" + "		\"ShowIsResolved\": false,\r\n"
				+ "		\"PredefinedCorrections\": null,\r\n" + "		\"RepeatField\": false,\r\n"
				+ "		\"DependencyRule\": null,\r\n" + "		\"ExpressionRule\": null,\r\n"
				+ "		\"isNonRepeatable\": false,\r\n" + "		\"ParentId\": null,\r\n"
				+ "		\"Name\": \"Text\"\r\n" + "	}\r\n" + "}";
		Response response = apiUtils.submitRequest(METHOD_TYPE.POST, addField, headers, formDesigner.AddFieldUrl);
		// Gson gson = new GsonBuilder().create();
		SimpleDataResponse sdr = gson.fromJson(response.asString(), SimpleDataResponse.class);
		String field1ID = sdr.Data;

		// 3. Save Form - With Fields
		String submitFormJson = "{\r\n" + "	\"formId\": \"" + createdFormId + "\",\r\n"
				+ "	\"formVersionId\": \"\",\r\n" + "	\"formLevel\": [{\r\n" + "		\"Id\": \"" + field1ID
				+ "\",\r\n" + "		\"Children\": []\r\n" + "	}],\r\n" + "	\"jobLevel\": null,\r\n"
				+ "	\"taskLevel\": null,\r\n" + "	\"headerLevel\": []\r\n" + "}";

		Response submitResponse = apiUtils.submitRequest(METHOD_TYPE.POST, submitFormJson, headers,
				formDesigner.SubmitFormUrl);
		SimpleDataResponse submitRes = gson.fromJson(submitResponse.asString(), SimpleDataResponse.class);
		TestValidation.IsTrue((submitRes.Status), "Form named " + formName + " is saved",
				"Could NOT save form " + formName);

		// 4. Validate Saved Form
		String validateAndReleaseFormJson = "{\r\n" + "  \"Data\": {\r\n" + "    \"Id\": \"" + createdFormId + "\"\r\n"
				+ "  }\r\n" + "}";
		Response validateResponse = apiUtils.submitRequest(METHOD_TYPE.POST, validateAndReleaseFormJson, headers,
				formDesigner.ValidateReleaseUrl);
		SimpleDataResponse validateRes = gson.fromJson(validateResponse.asString(), SimpleDataResponse.class);
		TestValidation.IsTrue((validateRes.Status), "Form named " + formName + " is validated",
				"Could NOT validate form " + formName);

		// 5. Release Saved Form
		Response releaseResponse = apiUtils.submitRequest(METHOD_TYPE.POST, validateAndReleaseFormJson, headers,
				formDesigner.ReleaseFormUrl);
		SimpleDataResponse releaseRes = gson.fromJson(releaseResponse.asString(), SimpleDataResponse.class);
		TestValidation.IsTrue((releaseRes.Status), "Form named " + formName + " is released",
				"Could NOT release form " + formName);
	}

	@Test(description = "Create and Release - Check Form")
	public void createAndReleaseForm_Check() {

		RequestSpecification request = RestAssured.given();

		// Numeric, Free Text, 2 Identifiers
		apiUtils = new ApiUtils();
		request.header("Content-Type", "application/json");
		request.header("Accept", "application/json, text/plain, /");
		request.header("Authorization", "Bearer " + accessToken);

		String saveFormJson = "{\r\n" + "	\"Id\": null,\r\n" + "	\"Name\": \"1stDec Testing-1\",\r\n"
				+ "	\"Type\": \"Check\",\r\n" + "	\"Resources\": [{\r\n"
				+ "		\"ID\": \"7ec289d5-6023-4465-83c0-0ba4a885bd46\",\r\n"
				+ "		\"Name\": \"Customers > AH07SepCust > NazTest1\"\r\n" + "	}]\r\n" + "}";
		request.body(saveFormJson);
		Response response = request.post(formDesigner.SaveFormDesignUrl);
		System.out.println("****************************START****************************");
		System.out.println(response.getBody().asString());
		System.out.println("*****************************END*****************************");
		Gson gson = new GsonBuilder().create();
		SimpleDataResponse saveRes = gson.fromJson(response.asString(), SimpleDataResponse.class);
		createdFormId = saveRes.Data;

//		//2. Add Fields - c30e956f-9877-4089-983b-fe08b0ed0cb3
		String addField = "{\r\n" + "	\"FormId\": \"" + createdFormId + "\",\r\n" + "	\"HasCompliance\": false,\r\n"
				+ "	\"Field\": {\r\n" + "		\"level\": \"form\",\r\n" + "		\"type\": \"FreeText\",\r\n"
				+ "		\"value\": null,\r\n" + "		\"children\": [],\r\n" + "		\"HasCompliance\": false,\r\n"
				+ "		\"ElementType\": \"Field\",\r\n" + "		\"fieldHasCompliance\": true,\r\n"
				+ "		\"ShortName\": \"Text\",\r\n" + "		\"Default\": null,\r\n"
				+ "		\"DataSource\": null,\r\n" + "		\"Repeat\": null,\r\n" + "		\"FailsForm\": true,\r\n"
				+ "		\"IsRequired\": true,\r\n" + "		\"AllowComments\": false,\r\n"
				+ "		\"AllowAttachments\": false,\r\n" + "		\"ShowOnCOA\": true,\r\n"
				+ "		\"ShowHint\": false,\r\n" + "		\"Hint\": null,\r\n"
				+ "		\"AllowCorrection\": false,\r\n" + "		\"ShowIsResolved\": false,\r\n"
				+ "		\"PredefinedCorrections\": null,\r\n" + "		\"RepeatField\": false,\r\n"
				+ "		\"DependencyRule\": null,\r\n" + "		\"ExpressionRule\": null,\r\n"
				+ "		\"isNonRepeatable\": false,\r\n" + "		\"ParentId\": null,\r\n"
				+ "		\"Name\": \"Text\"\r\n" + "	}\r\n" + "}";
		request.body(addField);
		Response addFieldResponse = request.post(formDesigner.AddFieldUrl);
		System.out.println("****************************START****************************");
		System.out.println(addFieldResponse.getBody().asString());
		System.out.println("*****************************END*****************************");
		SimpleDataResponse sdr = gson.fromJson(addFieldResponse.asString(), SimpleDataResponse.class);
		String field1ID = sdr.Data;

		// 3. Save Form - With Fields
		String submitFormJson = "{\r\n" + "	\"formId\": \"" + createdFormId + "\",\r\n"
				+ "	\"formVersionId\": \"\",\r\n" + "	\"formLevel\": [{\r\n" + "		\"Id\": \"" + field1ID
				+ "\",\r\n" + "		\"Children\": []\r\n" + "	}],\r\n" + "	\"jobLevel\": null,\r\n"
				+ "	\"taskLevel\": null,\r\n" + "	\"headerLevel\": []\r\n" + "}";

		request.body(submitFormJson);
		Response submitResponse = request.post(formDesigner.SubmitFormUrl);
		System.out.println("****************************START****************************");
		System.out.println(submitResponse.getBody().asString());
		System.out.println("*****************************END*****************************");
		SimpleDataResponse submitRes = gson.fromJson(submitResponse.asString(), SimpleDataResponse.class);
		TestValidation.IsTrue((submitRes.Status), "Form named " + formName + " is saved",
				"Could NOT save form " + formName);

//		//4. Validate Saved Form
//		String validateAndReleaseFormJson = "{\r\n" + 
//				"  \"Data\": {\r\n" + 
//				"    \"Id\": \""+createdFormId+"\"\r\n" + 
//				"  }\r\n" + 
//				"}";
//		Response validateResponse = apiUtils.submitRequest(METHOD_TYPE.POST, validateAndReleaseFormJson, headers, 
//				formDesigner.ValidateReleaseUrl);
//		SimpleDataResponse validateRes = gson.fromJson(validateResponse.asString(), SimpleDataResponse.class);
//		TestValidation.IsTrue((validateRes.Status), 
//							  "Form named " + formName +" is validated", 
//							  "Could NOT validate form " + formName); 
//		
//		//5. Release Saved Form
//		Response releaseResponse = apiUtils.submitRequest(METHOD_TYPE.POST, validateAndReleaseFormJson, headers, 
//				formDesigner.ReleaseFormUrl);
//		SimpleDataResponse releaseRes = gson.fromJson(releaseResponse.asString(), SimpleDataResponse.class);
//		TestValidation.IsTrue((releaseRes.Status), 
//							  "Form named " + formName +" is released", 
//							  "Could NOT release form " + formName); 
	}

}
