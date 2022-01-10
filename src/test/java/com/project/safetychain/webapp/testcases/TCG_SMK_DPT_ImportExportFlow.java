package com.project.safetychain.webapp.testcases;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.project.safetychain.api.models.support.TCG_FormCreation_Wrapper;
import com.project.safetychain.api.models.support.Support.Compliance;
import com.project.safetychain.api.models.support.Support.DPT_FIELD_TYPES;
import com.project.safetychain.api.models.support.Support.DPT_FORM_TYPES;
import com.project.safetychain.api.models.support.Support.FormParams;
import com.project.safetychain.api.models.support.Support.RESOURCE_TYPES;
import com.project.safetychain.webapp.pageobjects.CommonPage;
import com.project.safetychain.webapp.pageobjects.DataImportExportPage;
import com.project.safetychain.webapp.pageobjects.DataImportExportPage.ColumnNames;
import com.project.safetychain.webapp.pageobjects.DataImportExportPage.ExportLocationFields;
import com.project.safetychain.webapp.pageobjects.DataImportExportPage.ExportResourceFields;
import com.project.safetychain.webapp.pageobjects.DataImportExportPage.ExtractType;
import com.project.safetychain.webapp.pageobjects.DataImportExportPage.ImportFormCompliance;
import com.project.safetychain.webapp.pageobjects.DataImportExportPage.ImportFormDefinitionFields;
import com.project.safetychain.webapp.pageobjects.DataImportExportPage.ImportLocationFields;
import com.project.safetychain.webapp.pageobjects.DataImportExportPage.ImportRecordFields;
import com.project.safetychain.webapp.pageobjects.DataImportExportPage.ImportResourceFields;
import com.project.safetychain.webapp.pageobjects.DataImportExportPage.ImportType;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.COLUMNHEADER;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.FSQARESOURCE;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.FSQATAB;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage;
import com.project.safetychain.webapp.pageobjects.HomePage;
import com.project.safetychain.webapp.pageobjects.LoginPage;
import com.project.safetychain.webapp.pageobjects.ManageLocationPage;
import com.project.safetychain.webapp.pageobjects.ManageResourcePage;
import com.project.safetychain.webapp.pageobjects.ResourceDesignerPage;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.ElementProperties;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FIELD_TYPES;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FORMRESOURCETYPES;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FORMTYPES;
import com.project.safetychain.webapp.pageobjects.ManageLocationPage.LocationInstanceParams;
import com.project.safetychain.webapp.pageobjects.ResourceDesignerPage.CATEGORYTYPES;
import com.project.safetychain.webapp.pageobjects.UserManagerPage;
import com.project.safetychain.webapp.pageobjects.UserManagerPage.USERFIELDS;
import com.project.safetychain.webapp.testcases.support.FormOperations;
import com.project.safetychain.webapp.testcases.support.FormOperations.FormDetails;
import com.project.safetychain.api.models.support.Support.FormFieldParams;
import com.project.safetychain.api.models.support.Support.FormFieldParamsCompliance;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.utilities.ApiUtils;
import com.project.utilities.CommonMethods;
import com.project.utilities.ControlActions;
import com.project.utilities.ExcelParsingFromMergedCells;

public class TCG_SMK_DPT_ImportExportFlow  extends TestBase{
	ControlActions controlActions;

	//Pages
	LoginPage login;
	HomePage homePage;
	CommonPage mainPage;
	ResourceDesignerPage resourceDesigner;
	ManageResourcePage manageResource;
	ManageLocationPage locations;
	FormDesignerPage formDesignerPage;
	FSQABrowserPage fsqaBrowser;
	FormOperations formOperations;
	DataImportExportPage dataImportExport;
	UserManagerPage manageUserPage;
	FormFieldParams ffp;


	LocationInstanceParams locationInstance1, locationInstance2;
	//FormDesignParams fp;
	FormDetails formDetails;
	ApiUtils apiUtils = null;

	//Data
	public static String locationCategoryValue1;
	public static String locationInstanceValue1;
	public static String itemCategoryValue1,itemCategoryValue2;
	public static String itemResourceInstanceValue1;
	public static String itemResource2InstanceValue1, itemResource2InstanceValue2;
	public static String formType;
	public static String formName1, formName2,newformName1;
	public static String fieldShortName;
	public static List<String> itemNames = new ArrayList<String>();
	public static String dowanloadPath;
	public static String fieldMin, fieldTar, fieldMax, fieldUOM ;

	public static String newResourceCategory, newResourceName;
	public static String newResourceCategory1, newResourceName1, newResourceName2;
	public static String newLocationName1;

	public static String internalUserName, supplierUserName;
	public static String suppleirUserInstance = "ALL"; 


	@BeforeClass(alwaysRun = true)
	public void groupInit() throws InterruptedException {

		formType = "Check";
		fieldShortName = "Num1";
		fieldUOM = "KG";

		formName1 = CommonMethods.dynamicText("SingleResource");
		formName2 = CommonMethods.dynamicText("MultipleResource");
		newformName1 = CommonMethods.dynamicText("NewForm");


		locationCategoryValue1 = CommonMethods.dynamicText("LocCat1");
		locationInstanceValue1 = CommonMethods.dynamicText("LocInst1");

		locationInstance1 = new LocationInstanceParams();
		HashMap<String,Boolean> locationInstance = new HashMap<String,Boolean>();
		locationInstance.put(locationInstanceValue1, true);
		locationInstance1.CategoryName = locationCategoryValue1;
		locationInstance1.TextFieldValue = "Test 1";
		locationInstance1.NumberFieldValue = 2;
		locationInstance1.InstanceName = locationInstance;

		itemCategoryValue1 = CommonMethods.dynamicText("itemCat1");
		itemResourceInstanceValue1 = CommonMethods.dynamicText("itemInst1");
		itemCategoryValue2 = CommonMethods.dynamicText("itemCat2");		
		itemResource2InstanceValue1 = CommonMethods.dynamicText("item2Inst1");
		itemResource2InstanceValue2 = CommonMethods.dynamicText("item2Inst2");

		newResourceCategory = CommonMethods.dynamicText("SingleResCat");
		newResourceName = CommonMethods.dynamicText("SingleResIns");

		newResourceCategory1 = CommonMethods.dynamicText("MultileResCat1");
		newResourceName1 = CommonMethods.dynamicText("MultileResIns1");
		newResourceName2 = CommonMethods.dynamicText("MultileResIns2");
		newLocationName1 = CommonMethods.dynamicText("NewLocation");

		internalUserName = CommonMethods.dynamicText("InternalDPTUser");
		supplierUserName = CommonMethods.dynamicText("SupplierDPTUser");;

		String itemResourceInstances1[][] = {{itemResourceInstanceValue1,"true"}};
		String itemResourceInstances2[][] = {{itemResource2InstanceValue1,"true"}, {itemResource2InstanceValue2,"true"}};

		
		// ------------------------------------------------------------------------------------------------
		// API Implementation

		TCG_FormCreation_Wrapper formCreationWrapper = new TCG_FormCreation_Wrapper();
		apiUtils = new ApiUtils();

		// ------------------------------------------------------------------------------------------------
		// API - User , Location & Resource Creation and Linking

		List<String> userList = Arrays.asList(adminUsername);		

		HashMap<String, List<String>> item1CatMap = new HashMap<String, List<String>>();
		item1CatMap.put(itemCategoryValue1, Arrays.asList(itemResourceInstanceValue1));

		HashMap<String, List<String>> LocationMap = new HashMap<String, List<String>>();
		LocationMap.put(locationCategoryValue1, Arrays.asList(locationInstanceValue1));

		formCreationWrapper.Resource_Location_CreationWrapper(item1CatMap, LocationMap, true, userList,
				RESOURCE_TYPES.ITEMS);


		// ------------------------------------------------------------------------------------------------
		// API - Form Creation - Check

		FormFieldParams numericField = new FormFieldParams();
		numericField.DPTFieldType = DPT_FIELD_TYPES.NUMERIC;
		numericField.Name = "Number";
		numericField.ShowMinMax = "true";
		numericField.ShowTarget = "true";

		String formId = apiUtils.getUUID();
		logInfo("FormId = " + formId);
		FormParams fp = new FormParams();
		logInfo("Form Name = " + formId);
		fp.FormId = formId;
		fp.FormName = formName1;
		logInfo("Form Name = " + formName1);
		fp.type = DPT_FORM_TYPES.CHECK;
		fp.ResourceType = RESOURCE_TYPES.ITEMS;
		fp.ResourceCategoryNm = itemCategoryValue1;
		fp.ResourceInstanceNm = itemResourceInstanceValue1;
		fp.formElements = Arrays.asList(numericField);
		fp.ItemsResources = item1CatMap;
		logInfo("fp.formElements = " + fp.formElements);	

		formCreationWrapper.API_Wrapper_Forms(fp);		


		// ------------------------------------------------------------------------------------------------
		// API - Compliance Addition 

		HashMap<String, Compliance> complianceValuesMap = new HashMap<String, Compliance>();
		String resource = "Items > " + itemCategoryValue1 + " > " + itemResourceInstanceValue1;
		Compliance compliance = new Compliance();
		compliance.Min = "10";
		compliance.Max = "100";
		compliance.Target = "50";
		compliance.UOM = "KG";
		compliance.Name = numericField.Name;
		compliance.fieldType = DPT_FIELD_TYPES.NUMERIC;

		complianceValuesMap.put(resource, compliance);
		complianceValuesMap.put("Default", compliance);

		logInfo("resource List " + resource);
		logInfo("Compliance Values " + complianceValuesMap);

		FormFieldParamsCompliance ffpc = new FormFieldParamsCompliance();
		ffpc.fieldNames = Arrays.asList(numericField.Name);
		ffpc.complianceList = complianceValuesMap;

		formCreationWrapper.API_Wrapper_Forms_Compliance(fp, ffpc);	
		
		// ------------------------------------------------------------------------------------------------
		// API - User , Location & Resource Creation and Linking

		HashMap<String, List<String>> item2CatMap = new HashMap<String, List<String>>();
		item2CatMap.put(itemCategoryValue2, Arrays.asList(itemResource2InstanceValue1,itemResource2InstanceValue2));

		TCG_FormCreation_Wrapper formCreationWrapper1 = new TCG_FormCreation_Wrapper();
		formCreationWrapper1.Resource_Location_CreationWrapper(item2CatMap, LocationMap, true, userList,
				RESOURCE_TYPES.ITEMS);

		// ------------------------------------------------------------------------------------------------
		// API - Form Creation - Check

		String formId1 = apiUtils.getUUID();
		logInfo("FormId = " + formId1);
		FormParams fp1 = new FormParams();
		logInfo("Form Name = " + formId1);
		fp1.FormId = formId1;
		fp1.FormName = formName2;
		logInfo("Form Name = " + formName2);
		fp1.type = DPT_FORM_TYPES.CHECK;
		fp1.ResourceType = RESOURCE_TYPES.ITEMS;
		fp1.ItemsResources = item2CatMap;

		fp1.ResourceCategoryNm = itemCategoryValue2;
		fp1.ResourceInstanceNm = itemResource2InstanceValue1;
		fp1.formElements = Arrays.asList(numericField);
		logInfo("fp.formElements = " + fp1.formElements);

		formCreationWrapper.API_Wrapper_Forms(fp1);

		// ------------------------------------------------------------------------------------------------
		// API - Compliance Addition 

		HashMap<String, Compliance> complianceValuesMap1 = new HashMap<String, Compliance>();
		String resource1 = "Items > " + itemCategoryValue2 + " > " + itemResource2InstanceValue1;
		String resource2 = "Items > " + itemCategoryValue2 + " > " + itemResource2InstanceValue2;

		complianceValuesMap1.put(resource1, compliance);
		complianceValuesMap1.put(resource2, compliance);
		complianceValuesMap1.put("Default", compliance);

		logInfo("resource List " + resource1);
		logInfo("Compliance Values " + complianceValuesMap1);

		FormFieldParamsCompliance ffpc1 = new FormFieldParamsCompliance();
		ffpc1.fieldNames = Arrays.asList(numericField.Name);
		ffpc1.complianceList = complianceValuesMap1;

		formCreationWrapper.API_Wrapper_Forms_Compliance(fp1, ffpc1);	
		
		

		// ------------------------------------------------------------------------------------------------
		// WEB Application code starts here

		driver = launchbrowser();
		controlActions = new ControlActions(driver);
		mainPage = new CommonPage(driver);
		fsqaBrowser = new FSQABrowserPage(driver);
		login = new LoginPage(driver);
		resourceDesigner = new ResourceDesignerPage(driver);
		locations = new ManageLocationPage(driver);
		manageResource = new ManageResourcePage(driver);
		formDesignerPage = new FormDesignerPage(driver);
		formOperations = new FormOperations(driver);
		dataImportExport = new DataImportExportPage(driver);
		manageUserPage = new UserManagerPage(driver);


		controlActions.getUrl(applicationUrl);

		homePage = login.performLogin(adminUsername, adminPassword);
		if(homePage.error) {
			TCGFailureMsg = "Could NOT login to application";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		/*HashMap<String,String> resourceCategories1 = new HashMap<String,String>();
		resourceCategories1.put(CATEGORYTYPES.LOCATION, locationCategoryValue1);
		resourceCategories1.put(CATEGORYTYPES.ITEMS, itemCategoryValue1);

		HashMap<String,String> resourceCategories2 = new HashMap<String,String>();
		resourceCategories2.put(CATEGORYTYPES.ITEMS, itemCategoryValue2);

		if(!resourceDesigner.createCategory(resourceCategories1)) {
			TCGFailureMsg = "Could NOT create Locations/Items category 1";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
		if(!resourceDesigner.createCategory(resourceCategories2)) {
			TCGFailureMsg = "Could NOT create Items category 2";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
		if(!locations.createLocationInstanceLinkUser(locationCategoryValue1,locationInstanceValue1,adminUsername)) {
			TCGFailureMsg = "Could NOT create location instance - " + locationInstanceValue1;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
		if(!manageResource.addResourceInstances("Items", itemCategoryValue1, itemResourceInstances1, true, locationInstanceValue1)) {
			TCGFailureMsg = "Could NOT create resource " + itemResourceInstanceValue1;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
		if(!manageResource.addResourceInstances("Items", itemCategoryValue2, itemResourceInstances2, true, locationInstanceValue1)) {
			TCGFailureMsg = "Could NOT create resources " + itemResource2InstanceValue1+" & "+itemResource2InstanceValue1;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		HashMap<String, List<String>> fields = new HashMap<String, List<String>>();
		fields.put(FIELD_TYPES.NUMERIC, Arrays.asList(fieldShortName));
		ffp = new FormFieldParams();
		ffp.FieldDetails = fields;

		HashMap<String, List<String>> resource = new HashMap<String, List<String>>();
		resource.put(FORMRESOURCETYPES.ITEMS, Arrays.asList(itemCategoryValue1));

		ElementProperties settings;
		HashMap<String, ElementProperties> fieldSettings = new HashMap<String, ElementProperties>(); 

		settings = new ElementProperties();
		settings.COMPLIANCE_CONFIG = true;
		settings.SHOW_TARGET = true;
		settings.COMPLIANCE_VALUES = new String[]{fieldMin, fieldTar, fieldMax, null};
		fieldSettings.put(fieldShortName, settings);

		fp = new FormDesignParams();
		fp.FormType = FORMTYPES.CHECK;
		fp.FormName = formName1;
		fp.SelectResources = resource;
		fp.DesignFields = Arrays.asList(ffp);
		fp.ReleaseForm = false;

		formDesignerPage = homePage.clickFormDesignerMenu();
		if(formDesignerPage.error) {
			TCGFailureMsg = "Could NOT navigate to Admin Tools - Form Designer";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
		if(!formDesignerPage.createAndReleaseForm(fp)) {
			TCGFailureMsg = "Could NOT create form - " + formName1;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
		if(!formDesignerPage.navigateToReleaseForm_EditForm(formName1)) {
			TCGFailureMsg = "Could NOT open edit form - " + formName1;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
		if(!formDesignerPage.setFieldProperties(fieldSettings)) {
			TCGFailureMsg = "Could NOT set field properties for form - " + formName1;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
		if(!formDesignerPage.releaseForm(formName1)) {
			TCGFailureMsg = "Could NOT release form - "+formName1;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		resource = new HashMap<String, List<String>>();
		resource.put(FORMRESOURCETYPES.ITEMS, Arrays.asList(itemCategoryValue2));

		fp = new FormDesignParams();
		fp.FormType = FORMTYPES.CHECK;
		fp.FormName = formName2;
		fp.SelectResources = resource;
		fp.DesignFields = Arrays.asList(ffp);
		fp.ReleaseForm = false;


		formDesignerPage = homePage.clickFormDesignerMenu();
		if(formDesignerPage.error) {
			TCGFailureMsg = "Could NOT navigate to Admin Tools - Form Designer";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
		if(!formDesignerPage.createAndReleaseForm(fp)) {
			TCGFailureMsg = "Could NOT create form - " + formName2;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
		if(!formDesignerPage.navigateToReleaseForm_EditForm(formName2)) {
			TCGFailureMsg = "Could NOT open edit form - " + formName2;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
		if(!formDesignerPage.setFieldProperties(fieldSettings)) {
			TCGFailureMsg = "Could NOT set field properties for form - " + formName2;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
		if(!formDesignerPage.releaseForm(formName2)) {
			TCGFailureMsg = "Could NOT release form - "+formName2;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		dataImportExport = mainPage.clickDataImportExportMenu();
		if(dataImportExport.error) {
			TCGFailureMsg = "Could NOT navigate to Admin Tools - Data Import/Export ";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}*/


	}


	@Test(description = "Data export of single form compliance",priority=-1 )
	public void TestCase_31225() throws Exception{
		
		fieldMin = "10";
		fieldTar = "50";		
		fieldMax = "100";
		
		dataImportExport = mainPage.clickDataImportExportMenu();	
		TestValidation.IsFalse(dataImportExport.error, 
				"Navigated to Admin Tools - Data Import/Export ",
				"Could NOT navigate to Admin Tools - Data Import/Export ");


		itemNames.add(formName1);		
		boolean exportDataFormCompliance = dataImportExport.exportDataFormCompliance(ExtractType.FORM_COMPLIANCE, itemNames);
		TestValidation.IsTrue(exportDataFormCompliance, 
				ExtractType.FORM_COMPLIANCE +" is sucessfully EXPORTED", 
				"COULD NOT export " + ExtractType.FORM_COMPLIANCE);

		boolean exportResource = dataImportExport.downloadExportedData(ExtractType.FORM_COMPLIANCE);	
		TestValidation.IsTrue(exportResource, 
				"Downloaded exported data successfully", 
				"COULD NOT download exported data");

		downloadPath = System.getProperty("user.dir") + "\\test-data-files\\DownloadedDocuments\\";
		String filePath = downloadPath + dataImportExport.FirstExtractedFileTxt.getText();

		ExcelParsingFromMergedCells e = new ExcelParsingFromMergedCells();
		String minValue = e.getFieldData(filePath,FORMRESOURCETYPES.ITEMS + " > " + itemCategoryValue1 + " > " + itemResourceInstanceValue1, "Numeric", "Min");
		String maxValue = e.getFieldData(filePath,FORMRESOURCETYPES.ITEMS + " > " + itemCategoryValue1 + " > " + itemResourceInstanceValue1, "Numeric", "Max");
		String targetComplianceValue = e.getFieldData(filePath,FORMRESOURCETYPES.ITEMS + " > " + itemCategoryValue1 + " > " + itemResourceInstanceValue1, "Numeric", "Target/Compliance Value");
		//String UOMValue = e.getFieldData(filePath,FORMRESOURCETYPES.ITEMS + " > " + itemCategoryValue1 + " > " + itemResourceInstanceValue1, "Numeric", "UOM");
		boolean minCompare = minValue.contains(fieldMin);
		boolean maxCompare = maxValue.contains(fieldMax);
		boolean targetCompare = targetComplianceValue.contains(fieldTar);
		//boolean UOMCompare = UOMValue.contains(fieldUOM);
		TestValidation.IsTrue(minCompare && maxCompare && targetCompare, 
				"Validated data extracted successfully with data entered", 
				"COULD NOT validate extracted data");	

	}

	@Test(description="Verify import of form compliance")
	public void TestCase_31226() throws Exception{


		ImportFormCompliance importFormComplainceData = new  ImportFormCompliance();
		importFormComplainceData.FormName = Arrays.asList(formName1);

		dataImportExport = mainPage.clickDataImportExportMenu();
		TestValidation.IsFalse(dataImportExport.error, "Navigated to Admin Tools - Data Import/Export ",
				"Could NOT navigate to Admin Tools - Data Import/Export ");

		boolean importformcompliance = dataImportExport.importFormCompliance(ImportType.FORM_COMPLIANCE, ColumnNames.FORM_NAME, importFormComplainceData);
		TestValidation.IsTrue(importformcompliance, "Form compliance IMPORTED", "COULD NOT import form compliance");

		boolean release = formDesignerPage.navigateToReleaseForm();
		TestValidation.IsTrue(release, "Navigated to release form", "COULD NOT navigate to release forms");

		boolean verify = formDesignerPage.verifyComplainceData(formName1);	
		TestValidation.IsTrue(verify, "Verified the compliance value changes", "COULD NOT verify the compliance value changes");
	}

	@Test(description = "Data export of single resource" )
	public void TestCase_31224() throws Exception{

		ExportResourceFields exportResourceData =  new ExportResourceFields();
		exportResourceData.ResourceType = "Items";
		exportResourceData.Category = itemCategoryValue1;
		exportResourceData.Resources = Arrays.asList(itemResourceInstanceValue1);

		dataImportExport = mainPage.clickDataImportExportMenu();
		TestValidation.IsFalse(dataImportExport.error, "Navigated to Admin Tools - Data Import/Export ",
				"Could NOT navigate to Admin Tools - Data Import/Export ");

		boolean exportResource = dataImportExport.exportResource(ImportType.RESOURCE_ITEMS, ColumnNames.RESOURCE, exportResourceData);
		TestValidation.IsTrue(exportResource, "Single resource is sucessfully EXPORTED", "COULD NOT export single resource");

	}

	@Test(description = "Data import of single resource" )
	public void TestCase_31229() throws Exception{

		ImportResourceFields importResourceData =  new ImportResourceFields();
		importResourceData.ResourceType = "Items";
		importResourceData.Category = itemCategoryValue1;
		importResourceData.Resources = Arrays.asList(itemResourceInstanceValue1);
		importResourceData.NewCategory = newResourceCategory;
		importResourceData.NewResources = Arrays.asList(newResourceName);

		dataImportExport = mainPage.clickDataImportExportMenu();
		TestValidation.IsFalse(dataImportExport.error, "Navigated to Admin Tools - Data Import/Export ",
				"Could NOT navigate to Admin Tools - Data Import/Export ");

		boolean importResource = dataImportExport.importResource(ImportType.RESOURCE_ITEMS, ColumnNames.RESOURCE, importResourceData);
		TestValidation.IsTrue(importResource, "Single resource is sucessfully IMPORTED", "COULD NOT import single resource");

		manageResource = mainPage.clickResourcesMenu();
		TestValidation.IsFalse(dataImportExport.error, "Navigated to Admin Tools - Resources",
				"Could NOT navigate to Admin Tools - Resources");

		boolean verifyImportedResource = manageResource.verifyResourceAvailability("Items", importResourceData.NewCategory, importResourceData.NewResources);
		TestValidation.IsTrue(verifyImportedResource, "VERFIED imported Single resource",
				"COULD NOT verify imported single resource");

	}

	@Test(description = "Data export of one location" )
	public void TestCase_36979() throws Exception{
		ExportLocationFields exportLocationData =  new ExportLocationFields();
		exportLocationData.locationCategory = locationCategoryValue1;
		exportLocationData.locations = Arrays.asList(locationInstanceValue1);

		dataImportExport = mainPage.clickDataImportExportMenu();
		TestValidation.IsFalse(dataImportExport.error, "Navigated to Admin Tools - Data Import/Export ",
				"Could NOT navigate to Admin Tools - Data Import/Export ");

		boolean exportLocation = dataImportExport.exportLocations(ExtractType.LOCATIONS, ColumnNames.LOCATION_NAME, exportLocationData);
		TestValidation.IsTrue(exportLocation, "Single location is sucessfully EXPORTED", "COULD NOT export single location");

	}


	@Test(description = "Data import of one location" )
	public void TestCase_36978() throws Exception{

		ImportLocationFields importLocationData =  new ImportLocationFields();
		importLocationData.locationCategory = locationCategoryValue1;
		importLocationData.locations = Arrays.asList(locationInstanceValue1);
		importLocationData.newLocations = Arrays.asList(newLocationName1);


		dataImportExport = mainPage.clickDataImportExportMenu();
		TestValidation.IsFalse(dataImportExport.error, "Navigated to Admin Tools - Data Import/Export ",
				"Could NOT navigate to Admin Tools - Data Import/Export ");

		boolean exportLocation = dataImportExport.importLocations(ImportType.LOCATIONS, ColumnNames.LOCATION_NAME, importLocationData);
		TestValidation.IsTrue(exportLocation, "Single location is sucessfully IMPORTED", "COULD NOT import single location");

		locations = mainPage.clickLocationsMenu();
		TestValidation.IsFalse(locations.error, "Navigated to Admin Tools - Locations",
				"Could NOT navigate to Admin Tools - Locations");

		boolean verifyImportedLocation = locations.verifyAvailableLocation(locationCategoryValue1, newLocationName1);
		TestValidation.IsTrue(verifyImportedLocation, "VERIFIED imported location", "COULD NOT verify imported location");

	} 

	@Test(description = "Data import of single internal user" )
	public void TestCase_31230() throws Exception{
		dataImportExport = mainPage.clickDataImportExportMenu();
		TestValidation.IsFalse(dataImportExport.error, "Navigated to Admin Tools - Data Import/Export ",
				"Could NOT navigate to Admin Tools - Data Import/Export ");

		boolean importUser = dataImportExport.importInternalUser(ImportType.INTERNAL_USER, internalUserName, locationInstanceValue1);
		TestValidation.IsTrue(importUser, "Internal user is sucessfully IMPORTED", "COULD NOT import internal user");

		manageUserPage = mainPage.clickUsersMenu();
		TestValidation.IsFalse(manageUserPage.error, "Navigated to Admin Tools - Users ",
				"Could NOT navigate to Admin Tools - Users");

		boolean userExists = manageUserPage.searchAndVerifyWithUsrDetails(USERFIELDS.USERNAME, internalUserName);
		TestValidation.IsTrue(userExists, 
				"VERIFIED User " + internalUserName + " exists", 
				"Could NOT verify if User " + internalUserName + " exists");
	}


	@Test(description = "Data import of single supplier user" )
	public void TestCase_36980() throws Exception{

		dataImportExport = mainPage.clickDataImportExportMenu();
		TestValidation.IsFalse(dataImportExport.error, "Navigated to Admin Tools - Data Import/Export ",
				"Could NOT navigate to Admin Tools - Data Import/Export ");

		boolean importUser = dataImportExport.importSupplierUser(ImportType.SUPPLIER_USER, supplierUserName, suppleirUserInstance);
		TestValidation.IsTrue(importUser, "Supplier user is sucessfully IMPORTED", "COULD NOT import supplier user");

		manageUserPage = mainPage.clickUsersMenu();
		TestValidation.IsFalse(manageUserPage.error, "Navigated to Admin Tools - Users ",
				"Could NOT navigate to Admin Tools - Users");

		boolean selectSupplierTab = manageUserPage.clickSupplierTab();
		TestValidation.IsTrue(selectSupplierTab, 
				"SELECTED 'Supplier' tab", 
				"Could NOT select 'Supplier' tab");

		boolean userExists = manageUserPage.searchAndVerifyWithSuppUsrDetails(USERFIELDS.USERNAME, supplierUserName);
		TestValidation.IsTrue(userExists, 
				"VERIFIED User " + supplierUserName + " exists", 
				"Could NOT verify if User " + supplierUserName + " exists");
	}

	@Test(description = "Data export of records" )
	public void TestCase_36981() throws Exception{

		List<String> items = Arrays.asList(formName1,formName2);

		dataImportExport = mainPage.clickDataImportExportMenu();
		TestValidation.IsFalse(dataImportExport.error, "Navigated to Admin Tools - Data Import/Export ",
				"Could NOT navigate to Admin Tools - Data Import/Export ");

		boolean exportRecord = dataImportExport.exportData(ExtractType.RECORDS,ColumnNames.FORM_NAME,items);
		TestValidation.IsTrue(exportRecord, "Multiple records EXPORTED", "COULD NOT export multiple record");

	}


	@Test(description = "Data import of records" )
	public void TestCase_36982() throws Exception{

		ImportRecordFields recordsfields = new ImportRecordFields();
		recordsfields.FormName = Arrays.asList(formName1,formName2);
		recordsfields.FieldValue = Arrays.asList("2","20.09","3");

		dataImportExport = mainPage.clickDataImportExportMenu();
		TestValidation.IsFalse(dataImportExport.error, "Navigated to Admin Tools - Data Import/Export ",
				"Could NOT navigate to Admin Tools - Data Import/Export ");

		boolean importRecord = dataImportExport.importRecords(ImportType.RECORDS, ColumnNames.FORM_NAME, recordsfields);
		TestValidation.IsTrue(importRecord, "Multiple records IMPORTED", "COULD NOT import multiple record");

		FSQABrowserPage fbp = mainPage.clickFSQABrowserMenu();
		boolean navigateToRecords = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.ITEMS,FSQATAB.RECORDS);
		TestValidation.IsTrue(navigateToRecords, 
				"For items category, NAVIGATED to FSQABrowser > Records tab", 
				"Failed to navigate to FSQABrowser > Records tab");

		boolean verifyrecord = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.RESOURCE,itemResourceInstanceValue1);
		TestValidation.IsTrue(verifyrecord, 
				"OPENED record with resource name - " + itemResourceInstanceValue1, 
				"Failed to open record with resource name - " + itemResourceInstanceValue1);

		fbp.clickRecordsBreadCrumb();		
		boolean verifyrecord1 = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.RESOURCE,itemResource2InstanceValue1);
		TestValidation.IsTrue(verifyrecord1, 
				"OPENED record with resource name - " + itemResource2InstanceValue1, 
				"Failed to open record with resource name - " + itemResource2InstanceValue1);

		fbp.clickRecordsBreadCrumb();
		boolean verifyrecord2 = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.RESOURCE,itemResource2InstanceValue2);
		TestValidation.IsTrue(verifyrecord2, 
				"OPENED record with resource name - " + itemResource2InstanceValue2, 
				"Failed to open record with resource name - " + itemResource2InstanceValue2);

	}

	@Test(description = "Data export of multiple form defination" )
	public void TestCase_31227() throws Exception{

		List<String> items = Arrays.asList(formName1,formName2);

		dataImportExport = mainPage.clickDataImportExportMenu();
		TestValidation.IsFalse(dataImportExport.error, "Navigated to Admin Tools - Data Import/Export ",
				"Could NOT navigate to Admin Tools - Data Import/Export ");

		boolean exportRecord = dataImportExport.exportData(ExtractType.FORM_DEFINITION,ColumnNames.FORM_NAME,items);
		TestValidation.IsTrue(exportRecord, "Multiple form defination EXPORTED", "COULD NOT export multiple form defination");

	}

	@Test(description = "Data import single form defination" )
	public void TestCase_31228() throws Exception{

		ImportFormDefinitionFields importFormdefination = new ImportFormDefinitionFields();
		importFormdefination.FormName = Arrays.asList(formName1);
		importFormdefination.newFormName = newformName1;

		dataImportExport = mainPage.clickDataImportExportMenu();
		TestValidation.IsFalse(dataImportExport.error, "Navigated to Admin Tools - Data Import/Export ",
				"Could NOT navigate to Admin Tools - Data Import/Export ");

		boolean importformdefinition = dataImportExport.importFormDefination(ImportType.FORM_DEFINITION, ColumnNames.FORM_NAME, importFormdefination);
		TestValidation.IsTrue(importformdefinition, "Form definition IMPORTED", "COULD NOT import form definition");

		boolean release = formDesignerPage.navigateToReleaseForm();
		TestValidation.IsTrue(release, "Navigated to release form", "COULD NOT navigate to release forms");

		boolean releaseform = formDesignerPage.releaseForm(newformName1);
		TestValidation.IsTrue(releaseform, "Released form", "COULD NOT release form");

	}


	@AfterClass(alwaysRun = true)
	public void closeBrowser() throws InterruptedException {
		driver.close();
	}
}
