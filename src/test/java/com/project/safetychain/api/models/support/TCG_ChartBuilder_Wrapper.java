package com.project.safetychain.api.models.support;

import org.testng.annotations.BeforeClass;

import com.project.safetychain.api.models.Auth;
import com.project.safetychain.api.models.ChartBuilderFlow;
import com.project.safetychain.api.models.FormDesigner;
import com.project.safetychain.api.models.ManageLocation;
import com.project.safetychain.api.models.ManageResources;
import com.project.safetychain.api.models.ResourceDesigner;
import com.project.safetychain.api.models.support.Support.ChartBuilder;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.utilities.ApiUtils;
import com.project.utilities.CommonMethods;

public class TCG_ChartBuilder_Wrapper extends TestBase {

	Auth auth = null;
	ApiUtils apiUtils = null;
	ChartBuilderFlow chartBuilderFlow = null;
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
	public static String workGroupId = null;
	public static String text = "abc", number = "3";

	public TCG_ChartBuilder_Wrapper() {

		manageLocation = new ManageLocation();
		manageResources = new ManageResources();
		formDesigner = new FormDesigner();
		chartBuilderFlow = new ChartBuilderFlow();
		locCatName = CommonMethods.dynamicText("API Loc_Cat");
		custCatName = CommonMethods.dynamicText("API Cust_Cat");
		locInstName = CommonMethods.dynamicText("API Loc_Inst");
		custInstName = CommonMethods.dynamicText("API Cust_Inst");
		formName = CommonMethods.dynamicText("API Chk");

		auth = new Auth();
		auth.setAccessToken();

		// resourceDesigner = new ResourceDesigner();
		// resourceDesigner.setResourceCatIDs();

		// GetResourceCreationInputFieldIds();

	}

	@BeforeClass(alwaysRun = true)
	//	public void groupInit() {
	//
	//		manageLocation = new ManageLocation();
	//		manageResources = new ManageResources();
	//		formDesigner = new FormDesigner();
	//
	//		locCatName = CommonMethods.dynamicText("API Loc_Cat");
	//		custCatName = CommonMethods.dynamicText("API Cust_Cat");
	//		locInstName = CommonMethods.dynamicText("API Loc_Inst");
	//		custInstName = CommonMethods.dynamicText("API Cust_Inst");
	//		formName = CommonMethods.dynamicText("API Chk");
	//
	//		auth = new Auth();
	//		auth.setAccessToken();
	//		auth.setAccessToken(true);
	//
	//		// resourceDesigner = new ResourceDesigner();
	//		resourceDesigner.setResourceCatIDs();
	////Resource Creation
	//		GetResourceCreationInputFieldIds();
	//
	//	}

	//
	//	public void GetResourceCreationInputFieldIds() {
	//
	//		nameId = resourceDesigner.getResourceFieldId(RESOURCE_FIELDS.NAME, customersCatID);
	//		modifiedById = resourceDesigner.getResourceFieldId(RESOURCE_FIELDS.MODIFIED_BY, customersCatID);
	//		lastModifiedId = resourceDesigner.getResourceFieldId(RESOURCE_FIELDS.LAST_MODIFIED, customersCatID);
	//		statusId = resourceDesigner.getResourceFieldId(RESOURCE_FIELDS.STATUS, customersCatID);
	//		System.out.print("SCS Id= " + nameId + "MOdified By Id = " + modifiedById + "last MOdified Id = "
	//				+ lastModifiedId + "Status Id = " + statusId);
	//
	//	}
	//
	//
	//	public void createResourceCategory_Location(String locCatName) {
	//
	//		apiUtils = new ApiUtils();
	//		Map<String, String> headers = new HashMap<String, String>();
	//		headers.put("Content-Type", "application/json");
	//		headers.put("Accept", "application/json, text/plain, /");
	//		headers.put("Authorization", "Bearer " + accessToken);
	//
	//		String addCatJson = "{\r\n" + "    \"BaseTypeID\": \"" + locationCatID + "\",\r\n" + "    \"Name\": \""
	//				+ locCatName + "\",\r\n" + "    \"ID\": null,\r\n" + "    \"ResourceTypeID\": \"" + locationCatID
	//				+ "\"\r\n" + "}";
	//		Response response = apiUtils.submitRequest(METHOD_TYPE.POST, addCatJson, headers,
	//				resourceDesigner.AddResourceCatUrl);
	//		Gson gson = new GsonBuilder().create();
	//		SingleDataResponse sdr = gson.fromJson(response.asString(), SingleDataResponse.class);
	//		createdLocCatId = sdr.Data.Id;
	//		logInfo("Location Cat Id =   " + createdLocCatId);
	//		TestValidation.IsTrue((sdr.Status), "Location Category " + locCatName + " is created",
	//				"Could NOT create location category " + locCatName);
	//
	//	}
	//
	//
	//	public String createResourceInstance_Location(String locInstName) {
	//
	////		createdLocCatId="1bbbb840-2945-4d3d-9406-4459f5b89eeb";
	//
	//		// String createdLocInstId = null;
	//
	//		List<String> listOfEnabledFields = new ArrayList<String>();
	//		listOfEnabledFields = resourceDesigner.createResFieldsJson(RESOURCE_TYPES.LOCATION);
	//		String fieldDets = resourceDesigner.updateResFieldsJson(listOfEnabledFields, locInstName, number, text);
	//
	//		apiUtils = new ApiUtils();
	//		Map<String, String> headers = new HashMap<String, String>();
	//		headers.put("Content-Type", "application/json");
	//		headers.put("Accept", "application/json, text/plain, /");
	//		headers.put("Authorization", "Bearer " + accessToken);
	//
	//		String addInstJson = "{\r\n" + "    \"ParentBusinessObjectId\": \"" + createdLocCatId + "\",\r\n"
	//				+ "    \"Name\": \"" + locInstName + "\",\r\n" + "    \"ResourceTypeId\": \"" + locationCatID
	//				+ "\",\r\n" + "    \"FieldValues\": [\r\n" + "{\r\n"
	//				+ "         \"FieldId\":\"422052e7-1b84-4d1c-81f1-950149d90512\",\r\n" + "         \"Value\":null,\r\n"
	//				+ "         \"Type\":\"Label\",\r\n" + "         \"FieldName\":\"SCSLastModified\"\r\n" + "      },"
	//				+ "{\r\n" + "         \"FieldId\":\"d7ebfab1-eb2f-48d6-8514-8da3ec2d8a28\",\r\n"
	//				+ "         \"Value\":null,\r\n" + "         \"Type\":\"Label\",\r\n"
	//				+ "         \"FieldName\":\"SCSModifiedBy\"\r\n" + "      }," + fieldDets + "    ],\r\n"
	//				+ "    \"Id\": null,\r\n" + "    \"IsEnabled\": true,\r\n"
	//				+ "    \"BusinessObjectRefsWithResourceType\": [],\r\n"
	//				+ "    \"RemovedBusinessObjectRefsWithResourceType\": [],\r\n" + "    \"AddedUsers\": [],\r\n"
	//				+ "    \"RemovedUsers\": []\r\n" + "}";
	//		Response response = apiUtils.submitRequest(METHOD_TYPE.POST, addInstJson, headers,
	//				manageLocation.AddLocationUrl);
	//		Gson gson = new GsonBuilder().create();
	//		SingleDataResponse sdr = gson.fromJson(response.asString(), SingleDataResponse.class);
	//		createdLocInstId = sdr.Data.Id;
	//		TestValidation.IsTrue((sdr.Status), "Location Instance " + createdLocInstId + " is created",
	//				"Could NOT create location Instance " + createdLocInstId);
	//		return createdLocInstId;
	//
	//	}
	//
	//	@Test(description = "Create Customers category")
	//	public void createResourceCategory_Customers(String resourceCategory) {
	//
	//		apiUtils = new ApiUtils();
	//		Map<String, String> headers = new HashMap<String, String>();
	//		headers.put("Content-Type", "application/json");
	//		headers.put("Accept", "application/json, text/plain, /");
	//		headers.put("Authorization", "Bearer " + accessToken);
	//
	//		String addCatJson = "{\r\n" + "    \"BaseTypeID\": \"" + customersCatID + "\",\r\n" + "    \"Name\": \""
	//				+ resourceCategory + "\",\r\n" + "    \"ID\": null,\r\n" + "    \"ResourceTypeID\": \"" + customersCatID
	//				+ "\"\r\n" + "}";
	//		Response response = apiUtils.submitRequest(METHOD_TYPE.POST, addCatJson, headers,
	//				resourceDesigner.AddResourceCatUrl);
	//		Gson gson = new GsonBuilder().create();
	//		SingleDataResponse sdr = gson.fromJson(response.asString(), SingleDataResponse.class);
	//		createdCustCatId = sdr.Data.Id;
	//		TestValidation.IsTrue((sdr.Status), "Customers Category " + custCatName + " is created",
	//				"Could NOT create customers category " + custCatName);
	//
	//	}
	//
	//	@Test(description = "Create Customers instance")
	//	public void createResourceInstance_Customers(String resourceInstance) {
	//		// String createdInstId = null;
	//
	//		// name of cust cat = API Cust_Cat_12.11_15.46.39
	////		createdCustCatId="2441cb07-9807-4a7c-a661-9dedfa1d176a";
	//
	//		List<String> listOfEnabledFields = new ArrayList<String>();
	//		listOfEnabledFields = resourceDesigner.createResFieldsJson(RESOURCE_TYPES.CUSTOMERS);
	//		String fieldDets = resourceDesigner.updateResFieldsJson(listOfEnabledFields, resourceInstance, number, text);
	//		// String fieldDets = resourceDesigner.updateResFieldsJson(listOfEnabledFields,
	//		// custInstName, number, text);
	//
	//		apiUtils = new ApiUtils();
	//		Map<String, String> headers = new HashMap<String, String>();
	//		headers.put("Content-Type", "application/json");
	//		headers.put("Accept", "application/json, text/plain, /");
	//		headers.put("Authorization", "Bearer " + accessToken);
	//
	//		String addInstJson = "{\r\n" + "    \"BaseTypeID\": \"" + createdCustCatId + "\",\r\n" + "    \"Name\": \""
	//				+ resourceInstance + "\",\r\n" + "    \"ResourceTypeID\": \"" + customersCatID + "\",\r\n"
	//				+ "    \"ResourceValue\": [\r\n" + "        {\r\n" + "            \"FieldName\": \"SCSModifiedBy\",\r\n"
	//				+ "            \"FieldId\": \"" + modifiedById + "\",\r\n" + "            \"Value\": null,\r\n"
	//				+ "            \"Type\": \"Label\"\r\n" + "        },\r\n" + "        {\r\n"
	//				+ "            \"FieldName\": \"SCSLastModified\",\r\n" + "            \"FieldId\": \"" + lastModifiedId
	//				+ "\",\r\n" + "            \"Value\": null,\r\n" + "            \"Type\": \"Label\"\r\n"
	//				+ "        },\r\n" + "		 {\r\n" + "			 \"FieldName\":\"SCSStatus\",\r\n"
	//				+ "			 \"FieldId\":\"" + statusId + "\",\r\n" + "			 \"Value\":null,\r\n"
	//				+ "			 \"Type\":\"Toggle\"\r\n" + "        },\r\n" + fieldDets + "    ]\r\n" + "}";
	//		Response response = apiUtils.submitRequest(METHOD_TYPE.POST, addInstJson, headers,
	//				manageResources.AddNewResourceUrl);
	//		Gson gson = new GsonBuilder().create();
	//		SingleDataResponse sdr = gson.fromJson(response.asString(), SingleDataResponse.class);
	//		createdInstId = sdr.Data.Id;
	//		TestValidation.IsTrue((sdr.Status), "Location Instance " + locCatName + " is created",
	//				"Could NOT create location Instance " + locCatName);
	//		// return createdInstId;
	//
	//	}
	//
	//	@Test(description = "Link Resource Location Instances")
	//	public void Link_Resource_LocationInstance(String createdCustInstId, String locCatName, String createdLocInstId,
	//			String locInstName) {
	//
	//		List<String> listOfEnabledFields = new ArrayList<String>();
	//		listOfEnabledFields = resourceDesigner.createResFieldsJson(RESOURCE_TYPES.CUSTOMERS);
	//		String fieldDets = resourceDesigner.updateResFieldsJson(listOfEnabledFields, custInstName, number, text);
	//
	//		apiUtils = new ApiUtils();
	//		Map<String, String> headers = new HashMap<String, String>();
	//		headers.put("Content-Type", "application/json");
	//		headers.put("Accept", "application/json, text/plain, /");
	//		headers.put("Authorization", "Bearer " + accessToken);
	//
	//		String linkResourceJson = "{\r\n" + "\"ResourceTypeID\":\"" + customersCatID + "\",\r\n"
	//				+ "\"BusinessObjectID\":\"" + createdCustInstId + "\",\r\n" + "\"LinksWithResourceType\":[\r\n"
	//				+ "{\r\n" + "\"Id\":\"" + locationCatID + "\",\r\n" + "\"Name\":\"Locations\",\r\n"
	//				+ "\"Collection\":[\r\n" + "{\r\n" + "\"Id\":\"" + createdLocInstId + "\",\r\n"
	//				+ "\"Name\":\"Locations > " + locCatName + " > " + locInstName + "\"\r\n" + "}\r\n" + "]\r\n" + "}\r\n"
	//				+ "],\r\n" + "\"RemovedLinksWithResoueceType\":[\r\n" + "{\r\n" + "\"Id\":\"" + locationCatID
	//				+ "\",\r\n" + "\"Name\":\"Locations\",\r\n" + "\"Collection\":[\r\n" + "]\r\n" + "}\r\n" + "]\r\n"
	//				+ "}";
	//		Response response = apiUtils.submitRequest(METHOD_TYPE.POST, linkResourceJson, headers,
	//				manageResources.LinkResourceUrl);
	//		Gson gson = new GsonBuilder().create();
	//		SingleDataResponse sdr = gson.fromJson(response.asString(), SingleDataResponse.class);
	//		// createdCustInstId = sdr.Data.;
	//		TestValidation.IsTrue((sdr.Status), "Location Instance " + locCatName + " is created",
	//				"Could NOT create location Instance " + locCatName);
	//
	//	}
	//
	//	@Test(description = "Link Location Instances with User ")
	//	public void Link_LocationInstance_User(String searchString, String locCatId, String locInstId) {
	//
	//		String userId = resourceDesigner.getUserFieldId(searchString);
	//
	//		List<String> listOfEnabledFields = new ArrayList<String>();
	//		listOfEnabledFields = resourceDesigner.createResFieldsJson(RESOURCE_TYPES.CUSTOMERS);
	//		String fieldDets = resourceDesigner.updateResFieldsJson(listOfEnabledFields, custInstName, number, text);
	//
	//		apiUtils = new ApiUtils();
	//		Map<String, String> headers = new HashMap<String, String>();
	//		headers.put("Content-Type", "application/json");
	//		headers.put("Accept", "application/json, text/plain, /");
	//		headers.put("Authorization", "Bearer " + accessToken);
	//
	//		// getUser
	//
	//		String linkResourceJson = "{\r\n" + "   \"ParentBusinessObjectId\":\"" + locCatId + "\",\r\n"
	//				+ "   \"Name\":null,\r\n" + "   \"ResourceTypeId\":\"" + locationCatID + "\",\r\n"
	//				+ "   \"FieldValues\":[\r\n" + "      \r\n" + "   ],\r\n" + "   \"Id\":\"" + locInstId + "\",\r\n"
	//				+ "   \"IsEnabled\":true,\r\n" + "   \"BusinessObjectRefsWithResourceType\":[\r\n" + "      \r\n"
	//				+ "   ],\r\n" + "   \"RemovedBusinessObjectRefsWithResourceType\":[\r\n" + "      \r\n" + "   ],\r\n"
	//				+ "   \"AddedUsers\":[\r\n" + "      {\r\n" + "         \"Id\":\"" + userId + "\"\r\n" + "      }\r\n"
	//				+ "   ],\r\n" + "   \"RemovedUsers\":[\r\n" + "      \r\n" + "   ]\r\n" + "}\r\n" + "	";
	//		Response response = apiUtils.submitRequest(METHOD_TYPE.POST, linkResourceJson, headers,
	//				manageResources.EditLocationForUsersUrl);
	//
	//		Gson gson = new GsonBuilder().create();
	//		SingleDataResponse sdr = gson.fromJson(response.asString(), SingleDataResponse.class);
	//		// createdCustInstId = sdr.Data.;
	//		TestValidation.IsTrue((sdr.Status), "Location Instance " + locCatName + " is created",
	//				"Could NOT create location Instance " + locCatName);
	//
	//	}
	//
	//	public void Resource_Location_CreationWrapper(HashMap<String, List<String>> custCatName,
	//			HashMap<String, List<String>> locationCat, boolean linkResource) {
	//
	//		List<String> locCat = new ArrayList<String>();
	//		List<String> locInst = new ArrayList<String>();
	//		List<String> locInstId = new ArrayList<String>();
	//		logInfo("Location Category  ...................... =" + locationCat.size());
	//		logInfo("Location Category  ...................... =" + locationCat.entrySet());
	//		for (Map.Entry<String, List<String>> entry : locationCat.entrySet()) {
	//			String locCatName = entry.getKey();
	//
	//			List<String> locInstances = entry.getValue();
	//			createResourceCategory_Location(locCatName);
	//			for (String locInstance : locInstances) {
	//				String locInstanceId = createResourceInstance_Location(locInstance);
	//				locCat.add(locCatName);
	//				locInst.add(locInstance);
	//				locInstId.add(locInstanceId);
	//				// resourceIds.put(resourceId, instance);
	//			}
	//		}
	//
	//		logInfo("locInst ======== " + locInst);
	//
	//		for (Map.Entry<String, List<String>> entry : custCatName.entrySet()) {
	//			String fieldName = entry.getKey();
	//			List<String> fieldValues = entry.getValue();
	//			createResourceCategory_Customers(fieldName);
	//			logInfo("No of Cust Instances ======== " + fieldValues.size());
	//			for (String resInstance : fieldValues) {
	//				createResourceInstance_Customers(resInstance);
	//				logInfo("locInst Size ======== " + locInst.size());
	//				for (int i = 0; i < locInst.size(); i++) {
	//					// resourceIds.put(resourceId, instance);
	//					logInfo("locInst value ======== " + locInst.get(i));
	//					logInfo("Location Instance Id value ======== " + locInstId.get(i));
	//
	//					Link_Resource_LocationInstance(createdInstId, locCat.get(i), locInstId.get(i), locInst.get(i));
	//				}
	//
	//			}
	//		}
	//		/// Link Location with User
	//
	//		for (int i = 0; i < locInstId.size(); i++) {
	//			// resourceIds.put(resourceId, instance);
	//			logInfo("locInst value ======== " + locInst.get(i));
	//
	//			Link_LocationInstance_User(mobileAppUsername, createdLocCatId, locInstId.get(i));
	//			// Link_Resource_LocationInstance(createdInstId, locCat.get(i),
	//			// locInstId.get(i), locInst.get(i));
	//		}
	//
	//	}

	//description = "Create Chart"
	public void create_Chart_Wrapper(ChartBuilder cb) throws InterruptedException {
		String createdChartId = chartBuilderFlow.createChart(cb);

		TestValidation.IsTrue(true, "chart has Created with Name " + cb.Name + " Chart Template Type "
				+ cb.chartTemplateType + "Rules Enabled = " + cb.chartConfList, "Could NOT create Chart " + cb.Name);

		boolean isLinked = chartBuilderFlow.linkChartWithForm(cb, createdChartId);
		TestValidation.IsTrue(isLinked, "chart has linked to Form = " + cb.formName + " chartName = " + cb.Name
				+ "Rules Enabled = " + cb.chartConfList, "Could NOT link Chart " + cb.Name);
	}

	public String getFormFieldShortName(String formName, String fieldName) throws InterruptedException {
		return chartBuilderFlow.getFieldShortName(formName, fieldName);
	}

}
