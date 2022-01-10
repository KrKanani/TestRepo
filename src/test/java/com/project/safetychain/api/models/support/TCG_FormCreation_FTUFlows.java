package com.project.safetychain.api.models.support;


import java.util.ArrayList;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.project.safetychain.api.models.Auth;
import com.project.safetychain.api.models.Auth.SimpleDataResponse;
import com.project.safetychain.api.models.ManageLocation;
import com.project.safetychain.api.models.ManageResources;
import com.project.safetychain.api.models.ResourceDesigner;
import com.project.safetychain.api.models.FormDesigner;
import com.project.safetychain.api.models.support.TCG_OtherFormCreation_Wrapper.FormCreation_FTUFlows;
import com.project.safetychain.api.models.support.TCG_OtherFormCreation_Wrapper.FormParamsSetUrls;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.utilities.ApiUtils;
import com.project.utilities.ApiUtils.METHOD_TYPE;
import com.project.utilities.Constants;
import com.project.utilities.ExcelReader;
import com.project.utilities.JSONUtils;

import io.restassured.response.Response;


public class TCG_FormCreation_FTUFlows extends TestBase {

	Auth auth = null;
	ApiUtils apiUtils = null;
	ResourceDesigner resourceDesigner = null;
	ManageLocation manageLocation = null;
	ManageResources manageResources = null;
	FormDesigner formDesigner = null;
	public static String locCatName = null;
	public static String resCatName = null;
	public static String locInstName1 = null;
	public static String locInstName2 = null;
	public static String resInstName1 = null;
	public static String resInstName2 = null;
	public static String excelSheetName = null;
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
	String jsonOrgParams = "";
	JSONUtils jsnUtils = null;
	ExcelReader excel = null;
	String FormId = "";
	public static String resource1 = null,resource2 = null;
	public Map<String, String> headers = null;
	public String resourceType = null;
	
	@SuppressWarnings("rawtypes")
	HashMap<String,HashMap> excelValuesMap = null;


	@SuppressWarnings("rawtypes")
	public TCG_FormCreation_FTUFlows(FormCreation_FTUFlows fcff) {
		resourceType = fcff.resourceType;
		locCatName = fcff.locCatName;
		locInstName1 = fcff.locInstName1;
		locInstName2 = fcff.locInstName2;
		resCatName = fcff.resCatName;
		resInstName1 = fcff.resInstName1;
		resInstName2 = fcff.resInstName2;
		excelSheetName = fcff.excelSheetName;

		manageLocation = new ManageLocation();
		manageResources = new ManageResources();
		formDesigner = new FormDesigner();
		resourceDesigner = new ResourceDesigner();
		jsnUtils = new JSONUtils();
		excel = new ExcelReader();
		apiUtils = new ApiUtils();
		excelValuesMap = new HashMap<String, HashMap>();
		auth = new Auth();

		auth.setAccessToken(auth.otherLoginUrl);

		userList = Arrays.asList(username);
		// ADD RETURN TYPE FOR ALL METHODS
		// MAKE ALL METHODS PARAMETERIZED FOR URLS

		headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("Accept", "application/json, text/plain, /");
		headers.put("Authorization", "Bearer " + otherAccessToken);

		resourceDesigner.setResourceCatIDs(resourceDesigner.otherResourceTypeUrl, headers);
	}

	public List<String> getFormNamesFromExcel() throws Exception {

		String excelPath = Constants.large_form_api_file_path + "FormData.xlsx";

		FormId = apiUtils.getUUID();

		List<String> data=new ArrayList<String>();

		data = ExcelReader.GetDataFromExcelColumn(excelPath,"FormName", excelSheetName);

		System.out.print("Data = " + data);

		return data;
	}

	public void SMK_FTUFlows_Create3Forms() throws Exception {
		String jsonPath = "";
		boolean countForResourceCreation = false;

		List<String> formNames = getFormNamesFromExcel();

		for(String formName : formNames) {


			FormId = apiUtils.getUUID();

			// 1. Read all Form Names from the sheet name and coulmn name mentioned in Data Provider
			jsonPath = Constants.large_form_api_file_path + "CreateForm\\" + formName + ".txt";
			jsonOrgParams = apiUtils.readRequestFromFile(jsonPath);

			List<String> xpathList1 = jsnUtils.ExtractxPathFromHeaderCellValue("Data[1].FormName");
			jsonOrgParams = JSONUtils.updateRequestJSON(jsonOrgParams, xpathList1, formName);
			List<String> xpathList2 = jsnUtils.ExtractxPathFromHeaderCellValue("Data[1].FormId");
			jsonOrgParams = JSONUtils.updateRequestJSON(jsonOrgParams, xpathList2, FormId);

			Gson gson = new GsonBuilder().create();

			// 2. Verify whether form exists or not in the Web application
			String FormData = formDesigner.verifyIsFormCreated(formDesigner.otherGetFormsUrl, headers, 
					formName, resourceType);

			// 3. If form does not exist in the Web application, release form with new resources
			if (FormData == "") {
				
				apiUtils = new ApiUtils();

				if(!countForResourceCreation)
				{
					
					// A. Create new two resources
					HashMap<String, List<String>> locMap = new HashMap<String, List<String>>();
					HashMap<String, List<String>> catMap = new HashMap<String, List<String>>();

					if(locInstName1 != null && locInstName2 != null) {
						locMap.put(locCatName, Arrays.asList(locInstName1, locInstName2));
					}
					else if(locInstName1 != null && locInstName2 == null) {
						locMap.put(locCatName, Arrays.asList(locInstName1));
					}
					else if(locInstName1 == null && locInstName2 != null) {
						locMap.put(locCatName, Arrays.asList(locInstName2));
					}

					if(resInstName1 != null && resInstName2 != null) {
						catMap.put(resCatName, Arrays.asList(resInstName1, resInstName2));
					}
					else if(resInstName1 != null && resInstName2 == null) {
						catMap.put(resCatName, Arrays.asList(resInstName1));
					}
					else if(resInstName1 == null && resInstName2 != null) {
						catMap.put(resCatName, Arrays.asList(resInstName2));
					}

					FormParamsSetUrls fpsu = new FormParamsSetUrls();
					fpsu.ResourceCatFieldsUrl = resourceDesigner.otherResourceCatFieldsUrl;
					fpsu.ResourceCatFieldsValUrl = resourceDesigner.otherResourceCatFieldsValUrl;
					fpsu.AddNewResourceUrl = manageResources.otherAddNewResourceUrl;
					fpsu.AddLocationUrl = manageLocation.otherAddLocationUrl;
					fpsu.LinkResourceUrl = manageResources.otherLinkResourceUrl;
					fpsu.UserViewUrl = resourceDesigner.otherUserViewUrl;
					fpsu.EditLocationForUsersUrl = manageResources.otherEditLocationForUsersUrl;
					fpsu.AddResourceCatUrl = resourceDesigner.otherAddResourceCatUrl;
					fpsu.ResourceFieldDetailsUrl = resourceDesigner.otherResourceFieldDetailsUrl;
					fpsu.ManageResourceCategories = formDesigner.otherManageResourceCategories;
					fpsu.GetLocationCategories = formDesigner.otherGetLocationCategories;

					TCG_OtherFormCreation_Wrapper formCreationWrapper = new TCG_OtherFormCreation_Wrapper(headers);
					formCreationWrapper.resource_Location_CreationWrapper(fpsu, catMap, locMap, true, userList, 
							resourceType);
					
					countForResourceCreation = true;
				}

				// B. Set new resources name in Excel
				
				if(resInstName1 != null && resInstName2 != null) {
					resource1 = resourceType + " > " + resCatName + " > " + resInstName1;
					resource2 = resourceType + " > " + resCatName + " > " + resInstName2;
					
					List<String> xpathList3 = jsnUtils.ExtractxPathFromHeaderCellValue("Data[1].Resources[1].Name");
					jsonOrgParams = JSONUtils.updateRequestJSON(jsonOrgParams, xpathList3, resource1);

					List<String> xpathList4 = jsnUtils.ExtractxPathFromHeaderCellValue("Data[1].Resources[2].Name");
					jsonOrgParams = JSONUtils.updateRequestJSON(jsonOrgParams, xpathList4, resource2);
				}
				else if(resInstName1 != null && resInstName2 == null) {
					resource1 = resourceType + " > " + resCatName + " > " + resInstName1;
					
					List<String> xpathList3 = jsnUtils.ExtractxPathFromHeaderCellValue("Data[1].Resources[1].Name");
					jsonOrgParams = JSONUtils.updateRequestJSON(jsonOrgParams, xpathList3, resource1);
				}
				else if(resInstName1 == null && resInstName2 != null) {
					resource2 = resourceType + " > " + resCatName + " > " + resInstName2;
					
					List<String> xpathList4 = jsnUtils.ExtractxPathFromHeaderCellValue("Data[1].Resources[2].Name");
					jsonOrgParams = JSONUtils.updateRequestJSON(jsonOrgParams, xpathList4, resource2);
				}

				System.out.println("Request = " + jsonOrgParams);
				String createFormRequest = jsonOrgParams;

				// C. Send request for releasing form using updated Resources
				Response addRequestResponse = apiUtils.submitRequest(METHOD_TYPE.POST, createFormRequest, headers,
						formDesigner.otherDptSaveFormDesignUrl);

				SimpleDataResponse submitRes = gson.fromJson(addRequestResponse.asString(), SimpleDataResponse.class);
				if (submitRes.RequestStatus.equalsIgnoreCase("pending")) {
					logInfo("Created unreleased form - " + formName);
					// return true;
				} else {
					logError("Could not create unreleased form - " + formName);
					// return false;
				}
				Thread.sleep(10000);

				auth.setAccessToken(auth.otherLoginUrl);

				// D. Validate Saved Form
				String validateAndReleaseFormJson = "{\r\n" + "  \"Data\": {\r\n" + "    \"Id\": \"" + FormId + "\"\r\n"
						+ "  }\r\n" + "}";

				Response validateResponse = apiUtils.submitRequest(METHOD_TYPE.POST, validateAndReleaseFormJson, headers,
						formDesigner.otherValidateReleaseUrl);
				SimpleDataResponse validateRes = gson.fromJson(validateResponse.asString(), SimpleDataResponse.class);
				TestValidation.IsTrue((validateRes.Status), "Form named " + formName + " is validated",
						"Could NOT validate form " + formName);

				// E. Release Saved Form
				apiUtils.submitRequest(METHOD_TYPE.POST, validateAndReleaseFormJson, headers,
						formDesigner.otherReleaseFormUrl);
				
//				resource1 = null;
//				resource2 = null;
				

			}
		}
	}

}
