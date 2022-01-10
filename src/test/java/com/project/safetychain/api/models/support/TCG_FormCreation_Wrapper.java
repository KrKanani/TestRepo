package com.project.safetychain.api.models.support;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.BeforeClass;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.project.safetychain.apiproject.models.Auth;
import com.project.safetychain.api.models.Auth.SimpleDataResponse;
import com.project.safetychain.api.models.Auth.SingleDataResponse;
import com.project.safetychain.api.models.FormDesigner;
import com.project.safetychain.api.models.ManageLocation;
import com.project.safetychain.api.models.ManageResources;
import com.project.safetychain.api.models.ResourceDesigner;
import com.project.safetychain.api.models.support.Support.FormFieldParamsCompliance;
import com.project.safetychain.api.models.support.Support.FormParams;
import com.project.safetychain.api.models.support.Support.RESOURCE_FIELDS;
import com.project.safetychain.api.models.support.Support.RESOURCE_TYPES;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.utilities.ApiUtils;
import com.project.utilities.ApiUtils.METHOD_TYPE;
import com.project.utilities.CommonMethods;

import io.restassured.response.Response;

public class TCG_FormCreation_Wrapper extends TestBase {

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
	public static String createdInstId = null;
	public static String createdFormId = null;
	public static String formName = null;
	public static String nameId = null;
	public static String modifiedById = null;
	public static String lastModifiedId = null;
	public static String statusId = null;
	public static String statusValue = "true";
	public static String text = "abc", number = "3";

	public TCG_FormCreation_Wrapper() {

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
		// GetResourceCreationInputFieldIds();

	}

	@BeforeClass(alwaysRun = true)
	public void groupInit() {

	}

	// @Test(description = "Get Resource Field Ids")
	public void GetResourceCreationInputFieldIds() {

		nameId = resourceDesigner.getResourceFieldId(RESOURCE_FIELDS.NAME, catID,createdCustCatId);
		modifiedById = resourceDesigner.getResourceFieldId(RESOURCE_FIELDS.MODIFIED_BY, catID,createdCustCatId);
		lastModifiedId = resourceDesigner.getResourceFieldId(RESOURCE_FIELDS.LAST_MODIFIED, catID,createdCustCatId);
		statusId = resourceDesigner.getResourceFieldId(RESOURCE_FIELDS.STATUS, catID,createdCustCatId);

	}

	// @Test(description = "Create Location category")
	public String createResourceCategory_Location(String locCatName) {

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
		logInfo("Location Cat Id =   " + createdLocCatId);
		TestValidation.IsTrue((sdr.Status), "Location Category " + locCatName + " is created",
				"Could NOT create location category " + locCatName);
		return createdLocCatId;

	}

	// @Test(description = "Create Location instance")
	public String createResourceInstance_Location(String locInstName) {

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
		TestValidation.IsTrue((sdr.Status), "Location Instance " + createdLocInstId + " is created",
				"Could NOT create location Instance " + createdLocInstId);
		return createdLocInstId;

	}

	//	@Test(description = "Create Customers category")
	public void createResourceCategory(String resourceCategory, String resourceType) {

		apiUtils = new ApiUtils();
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("Accept", "application/json, text/plain, /");
		headers.put("Authorization", "Bearer " + accessToken);
		// String catId = null;
		switch (resourceType) {
		case RESOURCE_TYPES.CUSTOMERS:
			catID = customersCatID;
			break;
		case RESOURCE_TYPES.ITEMS:
			catID = itemsCatID;
			break;
		case RESOURCE_TYPES.EQUIPMENT:
			catID = equipmentCatID;
			break;
		case RESOURCE_TYPES.SUPPLIERS:
			catID = suppliersCatID;
			break;

		}

		GetResourceCreationInputFieldIds();

		String addCatJson = "{\r\n" + "    \"BaseTypeID\": \"" + catID + "\",\r\n" + "    \"Name\": \""
				+ resourceCategory + "\",\r\n" + "    \"ID\": null,\r\n" + "    \"ResourceTypeID\": \"" + catID
				+ "\"\r\n" + "}";

		Response response = apiUtils.submitRequest(METHOD_TYPE.POST, addCatJson, headers,
				resourceDesigner.AddResourceCatUrl);
		Gson gson = new GsonBuilder().create();
		SingleDataResponse sdr = gson.fromJson(response.asString(), SingleDataResponse.class);
		createdCustCatId = sdr.Data.Id;
		TestValidation.IsTrue((sdr.Status), "Customers Category " + custCatName + " is created",
				"Could NOT create customers category " + custCatName);

	}

	public void createResourceInstance(String resourceInstance, String resourceType) {

		List<String> listOfEnabledFields = new ArrayList<String>();
		switch (resourceType) {
		case RESOURCE_TYPES.CUSTOMERS:
			listOfEnabledFields = resourceDesigner.createResFieldsJson(RESOURCE_TYPES.CUSTOMERS);
			break;
		case RESOURCE_TYPES.ITEMS:
			listOfEnabledFields = resourceDesigner.createResFieldsJson(RESOURCE_TYPES.ITEMS);
			break;
		case RESOURCE_TYPES.EQUIPMENT:
			listOfEnabledFields = resourceDesigner.createResFieldsJson(RESOURCE_TYPES.EQUIPMENT);
			break;
		case RESOURCE_TYPES.SUPPLIERS:
			listOfEnabledFields = resourceDesigner.createResFieldsJson(RESOURCE_TYPES.SUPPLIERS);
			break;

		}

		String fieldDets = resourceDesigner.updateResFieldsJson(listOfEnabledFields, resourceInstance, number, text);

		apiUtils = new ApiUtils();
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("Accept", "application/json, text/plain, /");
		headers.put("Authorization", "Bearer " + accessToken);

		String addInstJson = "{\r\n" + "    \"BaseTypeID\": \"" + createdCustCatId + "\",\r\n" + "    \"Name\": \""
				+ resourceInstance + "\",\r\n" + "    \"ResourceTypeID\": \"" + catID + "\",\r\n"
				+ "    \"ResourceValue\": [\r\n" + "        {\r\n" + "            \"FieldName\": \"SCSModifiedBy\",\r\n"
				+ "            \"FieldId\": \"" + modifiedById + "\",\r\n" + "            \"Value\": null,\r\n"
				+ "            \"Type\": \"Label\"\r\n" + "        },\r\n" + "        {\r\n"
				+ "            \"FieldName\": \"SCSLastModified\",\r\n" + "            \"FieldId\": \"" + lastModifiedId
				+ "\",\r\n" + "            \"Value\": null,\r\n" + "            \"Type\": \"Label\"\r\n"
				+ "        },\r\n" + "		 {\r\n" + "			 \"FieldName\":\"SCSStatus\",\r\n"
				+ "			 \"FieldId\":\"" + statusId + "\",\r\n" + "			 \"Value\":"+ statusValue +",\r\n"
				+ "			 \"Type\":\"Toggle\"\r\n" + "        },\r\n" + fieldDets + "    ]\r\n" + "}";
		Response response = apiUtils.submitRequest(METHOD_TYPE.POST, addInstJson, headers,
				manageResources.AddNewResourceUrl);
		Gson gson = new GsonBuilder().create();
		SingleDataResponse sdr = gson.fromJson(response.asString(), SingleDataResponse.class);
		createdInstId = sdr.Data.Id;
		TestValidation.IsTrue((sdr.Status), "Location Instance " + locCatName + " is created",
				"Could NOT create location Instance " + locCatName);
		// return createdInstId;

	}

	// @Test(description = "Link Resource Location Instances")
	public void Link_Resource_LocationInstance(String createdCustInstId, String locCatName, String createdLocInstId,
			String locInstName) {

		apiUtils = new ApiUtils();
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("Accept", "application/json, text/plain, /");
		headers.put("Authorization", "Bearer " + accessToken);

		String linkResourceJson = "{\r\n" + "\"ResourceTypeID\":\"" + catID + "\",\r\n" + "\"BusinessObjectID\":\""
				+ createdCustInstId + "\",\r\n" + "\"LinksWithResourceType\":[\r\n" + "{\r\n" + "\"Id\":\""
				+ locationCatID + "\",\r\n" + "\"Name\":\"Locations\",\r\n" + "\"Collection\":[\r\n" + "{\r\n"
				+ "\"Id\":\"" + createdLocInstId + "\",\r\n" + "\"Name\":\"Locations > " + locCatName + " > "
				+ locInstName + "\"\r\n" + "}\r\n" + "]\r\n" + "}\r\n" + "],\r\n"
				+ "\"RemovedLinksWithResoueceType\":[\r\n" + "{\r\n" + "\"Id\":\"" + locationCatID + "\",\r\n"
				+ "\"Name\":\"Locations\",\r\n" + "\"Collection\":[\r\n" + "]\r\n" + "}\r\n" + "]\r\n" + "}";
		Response response = apiUtils.submitRequest(METHOD_TYPE.POST, linkResourceJson, headers,
				manageResources.LinkResourceUrl);
		Gson gson = new GsonBuilder().create();
		SingleDataResponse sdr = gson.fromJson(response.asString(), SingleDataResponse.class);
		// createdCustInstId = sdr.Data.;
		TestValidation.IsTrue((sdr.Status), "Location Instance " + locCatName + " is created",
				"Could NOT create location Instance " + locCatName);

	}

	// @Test(description = "Link Location Instances with User ")
	public void Link_LocationInstance_User(String searchString, String locCatId, String locInstId) {

		String userId = resourceDesigner.getUserFieldId(searchString);

		//		List<String> listOfEnabledFields = new ArrayList<String>();
		//		listOfEnabledFields = resourceDesigner.createResFieldsJson(CATEGORY_TYPES.CUSTOMERS);
		//		String fieldDets = resourceDesigner.updateResFieldsJson(listOfEnabledFields, custInstName, number, text);

		apiUtils = new ApiUtils();
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("Accept", "application/json, text/plain, /");
		headers.put("Authorization", "Bearer " + accessToken);

		// Link User with Location

		String linkResourceJson = "{\r\n" + "   \"ParentBusinessObjectId\":\"" + locCatId + "\",\r\n"
				+ "   \"Name\":null,\r\n" + "   \"ResourceTypeId\":\"" + locationCatID + "\",\r\n"
				+ "   \"FieldValues\":[\r\n" + "      \r\n" + "   ],\r\n" + "   \"Id\":\"" + locInstId + "\",\r\n"
				+ "   \"IsEnabled\":true,\r\n" + "   \"BusinessObjectRefsWithResourceType\":[\r\n" + "      \r\n"
				+ "   ],\r\n" + "   \"RemovedBusinessObjectRefsWithResourceType\":[\r\n" + "      \r\n" + "   ],\r\n"
				+ "   \"AddedUsers\":[\r\n" + "      {\r\n" + "         \"Id\":\"" + userId + "\"\r\n" + "      }\r\n"
				+ "   ],\r\n" + "   \"RemovedUsers\":[\r\n" + "      \r\n" + "   ]\r\n" + "}\r\n" + "	";
		Response response = apiUtils.submitRequest(METHOD_TYPE.POST, linkResourceJson, headers,
				manageResources.EditLocationForUsersUrl);

		Gson gson = new GsonBuilder().create();
		SingleDataResponse sdr = gson.fromJson(response.asString(), SingleDataResponse.class);
		TestValidation.IsTrue((sdr.Status), "Location Instance " + locCatName + " is created",
				"Could NOT create location Instance " + locCatName);

	}

	// @Test(description = "Link Location Instances with User ")
	public void Link_LocationInstance_User(List<String> searchString, String locCatId, String locInstId) {
		List<String> userIds = new ArrayList<String>();

		for (String userName : searchString) {
			String userId = resourceDesigner.getUserFieldId(userName);
			userIds.add(userId);
		}

		apiUtils = new ApiUtils();
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("Accept", "application/json, text/plain, /");
		headers.put("Authorization", "Bearer " + accessToken);

		// Link User with Location

		String linkResourceJson = "{\r\n" + "   \"ParentBusinessObjectId\":\"" + locCatId + "\",\r\n"
				+ "   \"Name\":null,\r\n" + "   \"ResourceTypeId\":\"" + locationCatID + "\",\r\n"
				+ "   \"FieldValues\":[\r\n" + "      \r\n" + "   ],\r\n" + "   \"Id\":\"" + locInstId + "\",\r\n"
				+ "   \"IsEnabled\":true,\r\n" + "   \"BusinessObjectRefsWithResourceType\":[\r\n" + "      \r\n"
				+ "   ],\r\n" + "   \"RemovedBusinessObjectRefsWithResourceType\":[\r\n" + "      \r\n" + "   ],\r\n"
				+ "   \"AddedUsers\":[" + Support.updateUserList(userIds) + " ],\r\n" + "   \"RemovedUsers\":[\r\n"
				+ "      \r\n" + "   ]\r\n" + "}\r\n" + "	";

		Response response = apiUtils.submitRequest(METHOD_TYPE.POST, linkResourceJson, headers,
				manageResources.EditLocationForUsersUrl);

		Gson gson = new GsonBuilder().create();
		SingleDataResponse sdr = gson.fromJson(response.asString(), SingleDataResponse.class);
		TestValidation.IsTrue((sdr.Status), "Location Instance " + locCatName + " is created",
				"Could NOT create location Instance " + locCatName);

	}

	public HashMap<String, String> Resource_Location_CreationWrapper(HashMap<String, List<String>> custCatName,
			HashMap<String, List<String>> locationCat, boolean linkResource, List<String> userList,
			String resourceType) {

		List<String> locCat = new ArrayList<String>();
		List<String> locInst = new ArrayList<String>();
		List<String> locInstId = new ArrayList<String>();
		HashMap<String, String> locResMap = new HashMap<String, String>();
		for (Map.Entry<String, List<String>> entry : locationCat.entrySet()) {
			String locCatName = entry.getKey();

			List<String> locInstances = entry.getValue();
			String locId = formDesigner.verifylocationCategory(locationCatID, locCatName);

			if (locId.equalsIgnoreCase("")) {

				createResourceCategory_Location(locCatName);
			}
			else
				createdLocCatId = locId;

			for (String locInstance : locInstances) {
				String locInstanceId = "";
				locInstanceId = formDesigner.verifylocationInstance(locationCatID, locId, locInstance);

				if (locInstanceId.equalsIgnoreCase("")) {
					locInstanceId = createResourceInstance_Location(locInstance);
				}
				else
					createdLocInstId = locInstanceId;
				
				locCat.add(locCatName);
				locInst.add(locInstance);
				locInstId.add(locInstanceId);
				// resourceIds.put(resourceId, instance);
				locResMap.put(locInstance, locInstanceId);

			}
		}

	//	logInfo("locInst ======== " + locInst);

		for (Map.Entry<String, List<String>> entry : custCatName.entrySet()) {
			String fieldName = entry.getKey();
			List<String> fieldValues = entry.getValue();
			createResourceCategory(fieldName, resourceType);
			//logInfo("No of Cust Instances ======== " + fieldValues.size());
			for (String resInstance : fieldValues) {
				createResourceInstance(resInstance, resourceType);
				locResMap.put(resInstance, createdInstId);
				logInfo("locInst Size ======== " + locInst.size());
				for (int i = 0; i < locInst.size(); i++) {
					// resourceIds.put(resourceId, instance);
					//logInfo("locInst value ======== " + locInst.get(i));
					//logInfo("Location Instance Id value ======== " + locInstId.get(i));

					Link_Resource_LocationInstance(createdInstId, locCat.get(i), locInstId.get(i), locInst.get(i));
				}

			}
		}
		/// Link Location with User

		for (int i = 0; i < locInstId.size(); i++) {
			// resourceIds.put(resourceId, instance);
			logInfo("locInst value ======== " + locInst.get(i));

			Link_LocationInstance_User(userList, createdLocCatId, locInstId.get(i));

		}
		return locResMap;

	}

	//	@Test(description = "Create Form ")
	@SuppressWarnings("static-access")
	public LinkedHashMap<String, String> API_Wrapper_Forms(FormParams fp) throws InterruptedException {
		// Numeric, Free Text, 2 Identifiers
		apiUtils = new ApiUtils();
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("Accept", "application/json, text/plain, /");
		headers.put("Authorization", "Bearer " + accessToken);

		// level 2 try for Select One/Multiple and Aggregate
		Gson gson = new GsonBuilder().create();
		boolean createForm = formDesigner.createUnreleasedForm(fp);
		TestValidation.IsTrue(createForm, "Form named " + fp.FormName + " is saved",
				"Could NOT save form " + fp.FormName);

		Thread.sleep(10000);

		// 4. Validate Saved Form

		String validate = "{\"Data\":{\"Id\":\""+fp.FormId+"\",\"Name\":\""+fp.FormName+"\"}}";

		Response validateResponse = apiUtils.submitRequest(METHOD_TYPE.POST, validate, headers,
				formDesigner.ValidateReleaseUrl);
		SimpleDataResponse validateRes = gson.fromJson(validateResponse.asString(), SimpleDataResponse.class);
		TestValidation.IsTrue((validateRes.Status), "Form named " + fp.FormName + " is validated",
				"Could NOT validate form " + fp.FormName);

		// 5. Release Saved Form
		String release = "{\"Data\":{\"Id\":\""+fp.FormId+"\",\"Name\":\""+fp.FormName+"\","
				+ "\"Type\":\""+fp.type+"\",\"Version\":\"0.1\",\"Comment\":\"\"}}";
		
		apiUtils.submitRequest(METHOD_TYPE.POST, release, headers,
				formDesigner.ReleaseFormUrl);

		// Enable DirectObservation

		String addVerificatonRequest = "{\r\n" + "    \"VeriicationId\": \"5cf98ec4-ab9b-4ba2-ae6c-4e002aa6a4cd\",\r\n"
				+ "    \"IsCommentEnabled\": true,\r\n" + "    \"RemovedFormIds\": [],\r\n"
				+ "    \"AddedFormIds\": [\r\n" + "        \"" + fp.FormId + "\"\r\n" + "    ]\r\n" + "}";

		apiUtils.submitRequest(METHOD_TYPE.POST, addVerificatonRequest, headers,
				formDesigner.VerificationUrl);

		// 6. Verify form is released
		boolean verifyForm = formDesigner.verifyIsFormCreated(fp.ResourceType, fp.FormName, headers);
		TestValidation.IsTrue(verifyForm, "Form named " + fp.FormName + " is released",
				"Could NOT release form " + fp.FormName);
		
		return formDesigner.shortNames;

	}

	// @Test(description = "Create Form Compliance")
	public void API_Wrapper_Forms_Compliance(FormParams fp, FormFieldParamsCompliance fpc) throws InterruptedException {
		// Numeric, Free Text, 2 Identifiers
		apiUtils = new ApiUtils();
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("Accept", "application/json, text/plain, /");
		headers.put("Authorization", "Bearer " + accessToken);
		Gson gson = new GsonBuilder().create();

		// Get Compliance Values

		// Compliance Values

		Thread.sleep(10000);

		boolean createForm = formDesigner.createUnreleasedFormWithCompliantValues(fp, fpc);
		TestValidation.IsTrue(createForm, "Form named " + fp.FormName + " is saved",
				"Could NOT save form " + fp.FormName);

		Thread.sleep(15000);

		// 4. Validate Saved Form
		String validateAndReleaseFormJson = "{\"Data\":{\"Id\":\""+fp.FormId+"\",\"Name\":\""+fp.FormName+"\"}}";

		Response validateResponse = apiUtils.submitRequest(METHOD_TYPE.POST, validateAndReleaseFormJson, headers,
				formDesigner.ValidateReleaseUrl);
		SimpleDataResponse validateRes = gson.fromJson(validateResponse.asString(), SimpleDataResponse.class);
		TestValidation.IsTrue((validateRes.Status), "Form named " + fp.FormName + " is validated",
				"Could NOT validate form " + fp.FormName);

		// 5. Release Saved Form
		String release = "{\"Data\":{\"Id\":\""+fp.FormId+"\",\"Name\":\""+fp.FormName+"\","
				+ "\"Type\":\""+fp.type+"\",\"Version\":\"0.1\",\"Comment\":\"\"}}";
		
		apiUtils.submitRequest(METHOD_TYPE.POST, release, headers,
				formDesigner.ReleaseFormUrl);

		// Enable DirectObservation

		String addVerificatonRequest = "{\r\n" + "    \"VeriicationId\": \"5cf98ec4-ab9b-4ba2-ae6c-4e002aa6a4cd\",\r\n"
				+ "    \"IsCommentEnabled\": true,\r\n" + "    \"RemovedFormIds\": [],\r\n"
				+ "    \"AddedFormIds\": [\r\n" + "        \"" + fp.FormId + "\"\r\n" + "    ]\r\n" + "}";

		apiUtils.submitRequest(METHOD_TYPE.POST, addVerificatonRequest, headers,
				formDesigner.VerificationUrl);

		// 6. Verify form is released
		boolean verifyForm = formDesigner.verifyIsFormCreated(fp.ResourceType, fp.FormName, headers);
		TestValidation.IsTrue(verifyForm, "Form named " + fp.FormName + " is released",
				"Could NOT release form " + fp.FormName);

	}
	
	/**This method is used to create unreleased form with given form properties
	 * @author hingorani_a
	 * @param fp Use Class FormParams to set field properties like fieldName, field settings etc.
	 * @return LinkedHashMap This returns a set of short names for the field in the created unreleased form.
	 */
	@SuppressWarnings("static-access")
	public LinkedHashMap<String, String> API_Wrapper_CreateUnreleasedForms(FormParams fp) throws InterruptedException {
		apiUtils = new ApiUtils();
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("Accept", "application/json, text/plain, /");
		headers.put("Authorization", "Bearer " + accessToken);

		boolean createForm = formDesigner.createUnreleasedForm(fp);
		TestValidation.IsTrue(createForm, "Form named " + fp.FormName + " is saved",
				"Could NOT save form " + fp.FormName);

		Thread.sleep(10000);

		return formDesigner.shortNames;
		
	}
	
	public void createResourceInstance(String resourceInstance, String resourceType, String status) {
		try {
			if(status!=null)
				statusValue = status;
	
			List<String> listOfEnabledFields = new ArrayList<String>();
			switch (resourceType) {
			case RESOURCE_TYPES.CUSTOMERS:
				listOfEnabledFields = resourceDesigner.createResFieldsJson(RESOURCE_TYPES.CUSTOMERS);
				break;
			case RESOURCE_TYPES.ITEMS:
				listOfEnabledFields = resourceDesigner.createResFieldsJson(RESOURCE_TYPES.ITEMS);
				break;
			case RESOURCE_TYPES.EQUIPMENT:
				listOfEnabledFields = resourceDesigner.createResFieldsJson(RESOURCE_TYPES.EQUIPMENT);
				break;
			case RESOURCE_TYPES.SUPPLIERS:
				listOfEnabledFields = resourceDesigner.createResFieldsJson(RESOURCE_TYPES.SUPPLIERS);
				break;
	
			}
	
			String fieldDets = resourceDesigner.updateResFieldsJson(listOfEnabledFields, resourceInstance, number, text);
	
			apiUtils = new ApiUtils();
			Map<String, String> headers = new HashMap<String, String>();
			headers.put("Content-Type", "application/json");
			headers.put("Accept", "application/json, text/plain, /");
			headers.put("Authorization", "Bearer " + accessToken);
	
			String addInstJson = "{\r\n" + "    \"BaseTypeID\": \"" + createdCustCatId + "\",\r\n" + "    \"Name\": \""
					+ resourceInstance + "\",\r\n" + "    \"ResourceTypeID\": \"" + catID + "\",\r\n"
					+ "    \"ResourceValue\": [\r\n" + "        {\r\n" + "            \"FieldName\": \"SCSModifiedBy\",\r\n"
					+ "            \"FieldId\": \"" + modifiedById + "\",\r\n" + "            \"Value\": null,\r\n"
					+ "            \"Type\": \"Label\"\r\n" + "        },\r\n" + "        {\r\n"
					+ "            \"FieldName\": \"SCSLastModified\",\r\n" + "            \"FieldId\": \"" + lastModifiedId
					+ "\",\r\n" + "            \"Value\": null,\r\n" + "            \"Type\": \"Label\"\r\n"
					+ "        },\r\n" + "		 {\r\n" + "			 \"FieldName\":\"SCSStatus\",\r\n"
					+ "			 \"FieldId\":\"" + statusId + "\",\r\n" + "			 \"Value\":"+ statusValue +",\r\n"
					+ "			 \"Type\":\"Toggle\"\r\n" + "        },\r\n" + fieldDets + "    ]\r\n" + "}";
			Response response = apiUtils.submitRequest(METHOD_TYPE.POST, addInstJson, headers,
					manageResources.AddNewResourceUrl);
			Gson gson = new GsonBuilder().create();
			SingleDataResponse sdr = gson.fromJson(response.asString(), SingleDataResponse.class);
			createdInstId = sdr.Data.Id;
			TestValidation.IsTrue((sdr.Status), "Location Instance " + locCatName + " is created",
					"Could NOT create location Instance " + locCatName);
		}
		finally {
			statusValue = "true";
		}
	}
	
	public HashMap<String, String> Resource_Location_CreationWrapper(HashMap<String, List<String>> custCatName,
			HashMap<String, List<String>> locationCat, boolean linkResource, List<String> userList,
			String resourceType, HashMap<String, String> resourceStatus) {

		List<String> locCat = new ArrayList<String>();
		List<String> locInst = new ArrayList<String>();
		List<String> locInstId = new ArrayList<String>();
		HashMap<String, String> locResMap = new HashMap<String, String>();
		for (Map.Entry<String, List<String>> entry : locationCat.entrySet()) {
			String locCatName = entry.getKey();

			List<String> locInstances = entry.getValue();
			String locId = formDesigner.verifylocationCategory(locationCatID, locCatName);

			if (locId.equalsIgnoreCase("")) {

				createResourceCategory_Location(locCatName);
			}
			else
				createdLocCatId = locId;

			for (String locInstance : locInstances) {
				String locInstanceId = "";
				locInstanceId = formDesigner.verifylocationInstance(locationCatID, locId, locInstance);

				if (locInstanceId.equalsIgnoreCase("")) {
					locInstanceId = createResourceInstance_Location(locInstance);
				}
				else
					createdLocInstId = locInstanceId;
				
				locCat.add(locCatName);
				locInst.add(locInstance);
				locInstId.add(locInstanceId);
				locResMap.put(locInstance, locInstanceId);

			}
		}

		for (Map.Entry<String, List<String>> entry : custCatName.entrySet()) {
			String fieldName = entry.getKey();
			List<String> fieldValues = entry.getValue();
			createResourceCategory(fieldName, resourceType);
			String status = null;
			for (String resInstance : fieldValues) {
				
				if(resourceStatus.get(resInstance)!=null)
					status = resourceStatus.get(resInstance);
				else
					status = null;
				
				createResourceInstance(resInstance, resourceType, status);
				locResMap.put(resInstance, createdInstId);
				for (int i = 0; i < locInst.size(); i++) {
					Link_Resource_LocationInstance(createdInstId, locCat.get(i), locInstId.get(i), locInst.get(i));
				}

			}
		}
		
		// Link Location with User
		for (int i = 0; i < locInstId.size(); i++) {
			Link_LocationInstance_User(userList, createdLocCatId, locInstId.get(i));
		}
		return locResMap;

	}

}
