package com.project.safetychain.apiauto.testcases;


import java.util.ArrayList;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.project.safetychain.api.models.Auth;
import com.project.safetychain.api.models.Auth.SimpleDataResponse;


import com.project.safetychain.api.models.ManageLocation;
import com.project.safetychain.api.models.ManageResources;
import com.project.safetychain.api.models.ResourceDesigner;
import com.project.safetychain.api.models.support.Support.RESOURCE_TYPES;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.utilities.ApiUtils;
import com.project.utilities.ApiUtils.METHOD_TYPE;
import com.project.utilities.CommonMethods;
import com.project.utilities.ExcelReader;
import com.project.utilities.JSONUtils;
import com.project.safetychain.apiauto.pageobjects.FormDesigner;
import com.project.safetychain.apiauto.support.commonAPIs;

import io.restassured.response.Response;


public class TCG_FormCreateSumit_Dynamic extends TestBase {

	Auth auth = null;
	ApiUtils apiUtils = null;
	ResourceDesigner resourceDesigner = null;
	ManageLocation manageLocation = null;
	ManageResources manageResources = null;
	FormDesigner formDesigner = null;
	commonAPIs commonAPIs = null;
	public static String locCatName = null;
	public static String resCatName = null;
	public static String locInstName1 = null;
	public static String locInstName2 = null;
	public static String resInstName1 = null;
	public static String resInstName2 = null;
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
	public static List<String> userList = null;
	public static String text = "abc", number = "3";
	public static String FormName = null;
	String jsonOrgParams = "";
	JSONUtils jsnUtils = null;
	ExcelReader excel = null;
	String FormId = "";
	public static String resource1 = null,resource2 = null;

	@SuppressWarnings("rawtypes")
	HashMap<String,HashMap> excelValuesMap = new HashMap<String,HashMap>();


	@SuppressWarnings("rawtypes")
	@BeforeClass(alwaysRun = true)
	public void groupInit() {

		auth = new Auth();
		auth.setAccessToken();
		manageLocation = new ManageLocation();
		manageResources = new ManageResources();
		formDesigner = new FormDesigner();
		resourceDesigner = new ResourceDesigner();
		commonAPIs = new commonAPIs();
		locCatName = CommonMethods.dynamicText("API Loc_Cat");
		resCatName = CommonMethods.dynamicText("API Res_Cat");
		locInstName1 = CommonMethods.dynamicText("API Loc_Inst1");
		locInstName2 = CommonMethods.dynamicText("API Loc_Inst2");
		resInstName1 = CommonMethods.dynamicText("API Res_Inst1");
		resInstName2 = CommonMethods.dynamicText("API Res_Inst2");


		jsnUtils = new JSONUtils();
		excel = new ExcelReader();
		apiUtils = new ApiUtils();
		excelValuesMap = new HashMap<String, HashMap>();

		userList = Arrays.asList(username);

		commonAPIs.setResourceCatIDs();

		try {

		} catch (Exception e) {
			logError("Couldnt read data from json and Excel");
		}

	}

	@DataProvider(name = "data-provider")
	public Iterator<String> dpMethod() throws Exception {

		String jsonPath = "D:\\APITestDataJson\\CreateForm\\Poultry - DSI Portion Raw Dimensions.txt";
		String excelPath1 = "D:\\APITestDataJson\\formData.xlsx";

		jsonOrgParams = apiUtils.readRequestFromFile(jsonPath);
		// System.out.println("Test name: " + method.getName());
		FormId = apiUtils.getUUID();

		List<String> data=new ArrayList<String>();

		data=ExcelReader.GetDataFromExcelColumn(excelPath1,"FormName", "Sheet1");

		System.out.print("Data = "+data);


		return data.iterator();
	}



	public HashMap<String, String> Resource_Location_CreationWrapper(HashMap<String, List<String>> custCatName,
			HashMap<String, List<String>> locationCat, boolean linkResource, List<String> userList,
			String resourceType) {

		List<String> locCat = new ArrayList<String>();
		List<String> locInst = new ArrayList<String>();
		List<String> locInstId = new ArrayList<String>();
		String createdLocCatId = "";
		String locId = null;
		String locInstanceId = null;
		List<String> fieldValues = null;
		HashMap<String, String> locResMap = new HashMap<String, String>();
		int flag = 0;


		for (Map.Entry<String, List<String>> entry : locationCat.entrySet()) {
			String locCatName = entry.getKey();

			List<String> locInstances = entry.getValue();
			locId = commonAPIs.verifylocationCategory(locationCatID, locCatName);

			if (locId.equalsIgnoreCase("")) {

				createdLocCatId = commonAPIs.createResourceCategory_Location(locCatName);
				locId = createdLocCatId;

				for (Map.Entry<String, List<String>> entry1 : custCatName.entrySet()) {
					String fieldName = entry1.getKey();
					fieldValues = entry1.getValue();
					commonAPIs.createResourceCategory(fieldName, resourceType);

				}
			}



			for (String locInstance : locInstances) {

				locInstanceId = "";

				locInstanceId = commonAPIs.verifylocationInstance(locationCatID, locId, locInstance);

				if (locInstanceId.equalsIgnoreCase("")) {
					locInstanceId = commonAPIs.createResourceInstance_Location(locInstance);
					flag = 1;
				}
				locCat.add(locCatName);
				locInst.add(locInstance);
				locInstId.add(locInstanceId);
				locResMap.put(locInstance, locInstanceId);				

			}

			if(flag == 1) {
				for (String resInstance : fieldValues) {
					String createdInstId = commonAPIs.createResourceInstance(resInstance, resourceType);
					locResMap.put(resInstance, createdInstId);
					for (int i = 0; i < locInst.size(); i++) {

						commonAPIs.Link_Resource_LocationInstance(createdInstId, locCat.get(i), locInstId.get(i),
								locInst.get(i));
					}

				}
			}
		}

		if (locId.equalsIgnoreCase("") && locInstanceId.equalsIgnoreCase("")) {

			for (int i = 0; i < locInstId.size(); i++) {
				commonAPIs.Link_LocationInstance_User(userList, createdLocCatId, locInstId.get(i));
			}
		}
		else {
			logInfo("Resource is already created and linked with location");
		}
		return locResMap;
	}

	@Test(dataProvider = "data-provider", description = "Create Form ")
	public void Form_SelectOne_moreThan6_CreateForm(String FormName) throws Exception {
		String jsonPath = "";
		FormId = apiUtils.getUUID();

		jsonPath = "D:\\APITestDataJson\\CreateForm\\" + FormName + ".txt";
		jsonOrgParams = apiUtils.readRequestFromFile(jsonPath);

		List<String> xpathList1 = jsnUtils.ExtractxPathFromHeaderCellValue("Data[1].FormName");
		jsonOrgParams = JSONUtils.updateRequestJSON(jsonOrgParams, xpathList1, FormName);
		List<String> xpathList2 = jsnUtils.ExtractxPathFromHeaderCellValue("Data[1].FormId");
		jsonOrgParams = JSONUtils.updateRequestJSON(jsonOrgParams, xpathList2, FormId);

		String formId = "";
		String getRecordDefinitionData = "";
		String locId = "";
		String locName = "";
		String resId = "";
		String resName = "";
		Gson gson = new GsonBuilder().create();

		HashMap<String, String> resourceMap = new HashMap<String, String>();
		HashMap<String, String> locationMap = new HashMap<String, String>();

		String FormData = formDesigner.verifyIsFormCreated(FormName);

		if (FormData == "") {

			HashMap<String, List<String>> locMap = new HashMap<String, List<String>>();
			locMap.put(locCatName, Arrays.asList(locInstName1, locInstName2));

			HashMap<String, List<String>> catMap = new HashMap<String, List<String>>();
			catMap.put(resCatName, Arrays.asList(resInstName1, resInstName2));

			Resource_Location_CreationWrapper(catMap, locMap, true, userList, RESOURCE_TYPES.CUSTOMERS);
			// Numeric, Free Text, 2 Identifiers
			apiUtils = new ApiUtils();
			Map<String, String> headers = new HashMap<String, String>();
			headers.put("Content-Type", "application/json");
			headers.put("Accept", "application/json, text/plain, /");
			headers.put("Authorization", "Bearer " + accessToken);

			// level 2 try for Select One/Multiple and Aggregate

			resource1 = "Customers > " + resCatName + " > " + resInstName1;
			resource2 = "Customers > " + resCatName + " > " + resInstName2;

			List<String> xpathList3 = jsnUtils.ExtractxPathFromHeaderCellValue("Data[1].Resources[1].Name");
			jsonOrgParams = JSONUtils.updateRequestJSON(jsonOrgParams, xpathList3, resource1);

			List<String> xpathList4 = jsnUtils.ExtractxPathFromHeaderCellValue("Data[1].Resources[2].Name");
			jsonOrgParams = JSONUtils.updateRequestJSON(jsonOrgParams, xpathList4, resource2);

			System.out.println("Request = " + jsonOrgParams);
			String createFormRequest = jsonOrgParams;

			Response addRequestResponse = apiUtils.submitRequest(METHOD_TYPE.POST, createFormRequest, headers,
					formDesigner.DptSaveFormDesignUrl);

			SimpleDataResponse submitRes = gson.fromJson(addRequestResponse.asString(), SimpleDataResponse.class);

			if (submitRes.RequestStatus.equalsIgnoreCase("pending")) {
				logInfo("Created unreleased form - " + FormName);
				// return true;
			} else {
				logError("Could not create unreleased form - " + FormName);
				// return false;
			}
			Thread.sleep(10000);
			
			auth.setAccessToken();

			//Need To add one API

			// 4. Validate Saved Form
			String validateAndReleaseFormJson = "{\r\n" + "  \"Data\": {\r\n" + "    \"Id\": \"" + FormId + "\"\r\n"
					+ "  }\r\n" + "}";

			Response validateResponse = apiUtils.submitRequest(METHOD_TYPE.POST, validateAndReleaseFormJson, headers,
					formDesigner.ValidateReleaseUrl);
			SimpleDataResponse validateRes = gson.fromJson(validateResponse.asString(), SimpleDataResponse.class);
			TestValidation.IsTrue((validateRes.Status), "Form named " + FormName + " is validated",
					"Could NOT validate form " + FormName);

			// 5. Release Saved Form
			apiUtils.submitRequest(METHOD_TYPE.POST, validateAndReleaseFormJson, headers,
					formDesigner.ReleaseFormUrl);

			// Enable DirectObservation

			String addVerificatonRequest = "{\r\n"
					+ "    \"VeriicationId\": \"5cf98ec4-ab9b-4ba2-ae6c-4e002aa6a4cd\",\r\n"
					+ "    \"IsCommentEnabled\": true,\r\n" + "    \"RemovedFormIds\": [],\r\n"
					+ "    \"AddedFormIds\": [\r\n" + "        \"" + FormId + "\"\r\n" + "    ]\r\n" + "}";

			apiUtils.submitRequest(METHOD_TYPE.POST, addVerificatonRequest, headers,
					formDesigner.VerificationUrl);

		}
		resourceMap = formDesigner.getResourceName(FormName);
		formId = formDesigner.getFormId_AlreadyCreated(FormName);
		locationMap = formDesigner.getFormLocation(formId);

		for (Map.Entry<String, String> entry : locationMap.entrySet()) {
			System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
			// String getRecordDefinitionData = formDesigner.getRecordDefiition(
			// FormName,entry.getValue());
			locId = entry.getKey();
			locName = entry.getValue();
			resourceMap = formDesigner.getFormResourceForLocationMapped(formId, entry.getKey());
			break;
		}

		for (Map.Entry<String, String> entry : resourceMap.entrySet()) {

			resId = entry.getKey();
			resName = entry.getValue();
			if (resName.contains("\"")) {
				resName = resName.replace("\"", "");
			}
			System.out.println("Key = " + entry.getKey() + ", Value = " + resName);
			getRecordDefinitionData = formDesigner.getRecordDefiition(formId, resName);
			break;
		}
		System.out.println("Record Definition =" + getRecordDefinitionData);

		Boolean status = formDesigner.submitForm(getRecordDefinitionData, locId, locName, resId, resName, formId,
				FormName);	


		if (status) {
			logInfo("form submitted Successfully"+FormName);
		} else {
			logInfo("Failed to submit form submitted"+FormName);

		}

		//	String status1 = formDesigner.verifyRecord();

	}

	//	@Test(description = "submitForm ")
	//	public void submitForm() throws InterruptedException, ParseException {
	//		List<String> xpathList = jsnUtils.ExtractxPathFromHeaderCellValue("Data.FormRef.Id");
	//		jsonOrgParams = JSONUtils.updateRequestJSON(jsonOrgParams, xpathList, FormId);
	//
	//		List<String> xpathList1 = jsnUtils.ExtractxPathFromHeaderCellValue("Data.FormRef.Name");
	//		jsonOrgParams = JSONUtils.updateRequestJSON(jsonOrgParams, xpathList1, FormName);
	//
	//		List<String> xpathList3 = jsnUtils.ExtractxPathFromHeaderCellValue("Data.Location.Name");
	//		jsonOrgParams = JSONUtils.updateRequestJSON(jsonOrgParams, xpathList3, locInstName1);
	//
	//		List<String> xpathList4 = jsnUtils.ExtractxPathFromHeaderCellValue("Data.Resource.Name");
	//		jsonOrgParams = JSONUtils.updateRequestJSON(jsonOrgParams, xpathList4, resInstName1);
	//
	//		// Numeric, Free Text, 2 Identifiers
	//		apiUtils = new ApiUtils();
	//		Map<String, String> headers = new HashMap<String, String>();
	//		headers.put("Content-Type", "application/json");
	//		headers.put("Accept", "application/json, text/plain, /");
	//		headers.put("Authorization", "Bearer " + accessToken);
	//
	//		// level 2 try for Select One/Multiple and Aggregate
	//		Gson gson = new GsonBuilder().create();
	//		System.out.println("Request = " + jsonOrgParams);
	//		String submitFormRequest = jsonOrgParams;
	//
	//		Response addRequestResponse = apiUtils.submitRequest(METHOD_TYPE.POST, submitFormRequest, headers,
	//				formDesigner.SubmitFormUrl);
	//
	//		SimpleDataResponse submitRes = gson.fromJson(addRequestResponse.asString(), SimpleDataResponse.class);
	//
	//	}
	//
	//	@Test(description = "Poultry - DSI Portion Raw Dimensions", enabled = false)
	//	public void API_Poultry() throws InterruptedException {
	//
	//		HashMap<String, List<String>> locMap = new HashMap<String, List<String>>();
	//		locMap.put(locCatName, Arrays.asList(locInstName1, locInstName2));
	//
	//		HashMap<String, List<String>> catMap = new HashMap<String, List<String>>();
	//		catMap.put(resCatName, Arrays.asList(resInstName1, resInstName2));
	//
	//		Resource_Location_CreationWrapper(catMap, locMap, true, userList, RESOURCE_TYPES.CUSTOMERS);
	//		// Numeric, Free Text, 2 Identifiers
	//		apiUtils = new ApiUtils();
	//		Map<String, String> headers = new HashMap<String, String>();
	//		headers.put("Content-Type", "application/json");
	//		headers.put("Accept", "application/json, text/plain, /");
	//		headers.put("Authorization", "Bearer " + accessToken);
	//
	//		// level 2 try for Select One/Multiple and Aggregate
	//		Gson gson = new GsonBuilder().create();
	//		System.out.println("Request = " + jsonOrgParams);
	//		String createFormRequest = jsonOrgParams;
	//
	//		Response addRequestResponse = apiUtils.submitRequest(METHOD_TYPE.POST, createFormRequest, headers,
	//				formDesigner.DptSaveFormDesignUrl);
	//
	//		SimpleDataResponse submitRes = gson.fromJson(addRequestResponse.asString(), SimpleDataResponse.class);
	//
	//		if (submitRes.RequestStatus.equalsIgnoreCase("pending")) {
	//			logInfo("Created unreleased form - " + FormName);
	//			// return true;
	//		} else {
	//			logError("Could not create unreleased form - " + FormName);
	//			// return false;
	//		}
	//		Thread.sleep(10000);
	//
	//		// 4. Validate Saved Form
	//		String validateAndReleaseFormJson = "{\r\n" + "  \"Data\": {\r\n" + "    \"Id\": \"" + FormId + "\"\r\n"
	//				+ "  }\r\n" + "}";
	//
	//		Response validateResponse = apiUtils.submitRequest(METHOD_TYPE.POST, validateAndReleaseFormJson, headers,
	//				formDesigner.ValidateReleaseUrl);
	//		SimpleDataResponse validateRes = gson.fromJson(validateResponse.asString(), SimpleDataResponse.class);
	//		TestValidation.IsTrue((validateRes.Status), "Form named " + FormName + " is validated",
	//				"Could NOT validate form " + FormName);
	//
	//		// 5. Release Saved Form
	//		Response releaseResponse = apiUtils.submitRequest(METHOD_TYPE.POST, validateAndReleaseFormJson, headers,
	//				formDesigner.ReleaseFormUrl);
	//
	//		// Enable DirectObservation
	//
	//		String addVerificatonRequest = "{\r\n" + "    \"VeriicationId\": \"5cf98ec4-ab9b-4ba2-ae6c-4e002aa6a4cd\",\r\n"
	//				+ "    \"IsCommentEnabled\": true,\r\n" + "    \"RemovedFormIds\": [],\r\n"
	//				+ "    \"AddedFormIds\": [\r\n" + "        \"" + FormId + "\"\r\n" + "    ]\r\n" + "}";
	//
	//		Response addVerificatonResponse = apiUtils.submitRequest(METHOD_TYPE.POST, addVerificatonRequest, headers,
	//				formDesigner.VerificationUrl);
	//
	//		// 6. Verify form is released
	////		boolean verifyForm = formDesigner.verifyIsFormCreated(fp.ResourceType, FormName, headers);
	////		TestValidation.IsTrue(verifyForm, "Form named " + FormName + " is released",
	////				"Could NOT release form " + FormName);
	//
	//	}

}
