package com.project.safetychain.apiproject.testcases;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.json.simple.parser.ParseException;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.project.safetychain.apiproject.models.Auth;
import com.project.safetychain.apiproject.models.ChartBuilder;
import com.project.safetychain.apiproject.models.FormDesigner;
import com.project.safetychain.apiproject.models.FormsManager;
import com.project.safetychain.apiproject.models.FormsManager.ChartConfig;
import com.project.safetychain.apiproject.models.FormsManager.FormDetails;
import com.project.safetychain.apiproject.models.ResourceDesigner;
import com.project.safetychain.apiproject.models.Resources;
import com.project.safetychain.apiproject.models.Roles;
import com.project.safetychain.apiproject.models.Verifications;
import com.project.safetychain.apiproject.models.ChartBuilder.ChartParams;
import com.project.safetychain.apiproject.models.ChartBuilder.ChartRules;
import com.project.safetychain.apiproject.models.ChartBuilder.ChartTypeIDS;
import com.project.safetychain.apiproject.models.ChartBuilder.ChartTypes;
import com.project.safetychain.apiproject.models.FormDesigner.FormFields;
import com.project.safetychain.apiproject.models.FormDesigner.FormTypes;
import com.project.safetychain.apiproject.models.ResourceDesigner.ResourceTypes;
import com.project.safetychain.apiproject.models.Resources.ResourcesData;
import com.project.safetychain.apiproject.models.Verifications.VerificationDetails;
import com.project.safetychain.apiproject.models.Verifications.SingleDataResponse1;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.utilities.ApiUtils;
import com.project.utilities.CommonMethods;
import com.project.utilities.JSONUtils;

public class TCG_FormsManager  extends TestBase{

	Auth auth = null;
	ApiUtils apiUtils = null;
	JSONUtils jsonUtils = null;

	ResourceDesigner resourcedesigner = null;
	Resources resources = null;
	FormDesigner designer = null;
	FormsManager formsManager = null;
	Verifications verification1 = null;
	ChartBuilder chartBuilder = null;
	ChartParams params1;
	FormDetails formDetails = null;
	ChartConfig chartDetails = null;


	public String locationCategory = null;
	public String locationInstance = null;
	String supplierResCategory = null;
	String supplierResInstance = null;

	public String resourceInstanceId = null;
	String formId1 = null;
	String locationId1 = null, resourceId1 = null;

	String checkformname = null;

	Roles roles = null;

	VerificationDetails verification1Details = null;

	public String verification1Name = null, verification1ID = null;
	String XBarRChartName = null;
	String ChartLinkName = null;

	String NumericField1, NumericField2, IdentifierField1, IdentifierField2;

	@BeforeClass(alwaysRun = true)
	public void groupInit() {

		supplierResCategory = CommonMethods.dynamicText("ResCat1");
		supplierResInstance = CommonMethods.dynamicText("ResInst1");
		locationCategory = CommonMethods.dynamicText("LocCat1");
		locationInstance = CommonMethods.dynamicText("LocInst1");
		checkformname = CommonMethods.dynamicText("FormsManagerForm1");
		verification1Name = CommonMethods.dynamicText("FormsManagerVerification");
		XBarRChartName = CommonMethods.dynamicText("API_XBarRChart");
		ChartLinkName = CommonMethods.dynamicText("API_LinkChart");

		NumericField1 = "Num 1";
		NumericField2 = "Num 2";
		IdentifierField1 = "IDN 1";
		IdentifierField2 = "IDN 2";

		auth = new Auth();
		auth.setAccessToken();
		jsonUtils = new JSONUtils();
		apiUtils = new ApiUtils();

		auth.setAccessToken();

		resourcedesigner = new ResourceDesigner();
		resources = new Resources();
		designer = new FormDesigner ();	
		formsManager = new FormsManager();

		String locationTypeId = resourcedesigner.getResourceTypeId(ResourceTypes.LOCATIONS);
		resourcedesigner.createResourceCategory(locationTypeId,locationTypeId,locationCategory);

		String locationCategoryId = resources.getLocCatId(locationCategory);
		List <String> enalbledFieldIds = resourcedesigner.getEnabledResourceTypeFields(locationTypeId);
		List <String> fieldValues = resourcedesigner.getFieldValue(enalbledFieldIds, ResourceTypes.LOCATIONS);
		String resourceTypeFields = resources.getResourceTypeFields(fieldValues,locationInstance);
		String locationInstanceId = resources.createLocInstance(locationCategoryId,locationCategory, locationInstance, resourceTypeFields);

		resources.getLocation(locationCategoryId, locationInstanceId, locationTypeId);

		resourcedesigner = new ResourceDesigner();
		resources = new Resources();

		String suppTypeId = resourcedesigner.getResourceTypeId(ResourceTypes.SUPPLIERS);
		resourcedesigner.getResourceDesignerFields(suppTypeId);
		resourcedesigner.createResourceCategory(suppTypeId,suppTypeId,supplierResCategory);

		String suppCatId = resources.getResourceCategoryId(suppTypeId,supplierResCategory);
		List <String> enabledTypeFieldIds = resourcedesigner.getEnabledResourceTypeFields(suppTypeId);
		List <String> fieldValues1 = resourcedesigner.getFieldValue(enabledTypeFieldIds, ResourceTypes.SUPPLIERS);

		String fieldDetails = resources.getResourceTypeFields(fieldValues1, supplierResInstance);
		resourceInstanceId = resources.createResourceInstance(fieldDetails,suppTypeId,supplierResInstance, suppCatId, "true");

		ResourcesData data = new ResourcesData();

		data.locationCatName = locationCategory;
		data.locationTypeId = locationTypeId;
		data.locationInstanceId = locationInstanceId;
		data.locationInstname = locationInstance;
		data.resourceTypeId = suppTypeId;
		data.resourceInstanceId = resourceInstanceId;

		resources.linkResourceLocation(data);
		designer.getResourceTree(resourceInstanceId,supplierResCategory,supplierResInstance);

		List<String> formfield = Arrays.asList(FormFields.NUMERIC, FormFields.NUMERIC, FormFields.IDENTIFIER, FormFields.IDENTIFIER);
		HashMap<String,String> fieldnames = new LinkedHashMap<String,String>();
		fieldnames.put(NumericField1, NumericField1);
		fieldnames.put(NumericField2, NumericField2);
		fieldnames.put(IdentifierField1, IdentifierField1);
		fieldnames.put(IdentifierField2, IdentifierField2);

		formId1 = designer.createForm(checkformname, FormTypes.CHECK, formfield, fieldnames, false, true);
		locationId1 = locationInstanceId;
		resourceId1 = resourceInstanceId;
		if(formId1==null) {
			TCGFailureMsg = "COULD not create a form";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

	}

	@Test (description = "Forms Manager || Enable/Disable Form")
	public void EnableDisable_Forms() {

		HashMap<String, String> formDetails = new HashMap<String, String>();
		formDetails.put(checkformname, formId1);

		boolean disableForm = formsManager.updateFormStatus(formDetails, false);
		TestValidation.IsTrue(disableForm, "DISABLED forms", "FAILED to disable forms");

		boolean formStatus = Boolean.valueOf(formsManager.getFormDetails(checkformname, "IsEnabled"));
		TestValidation.IsFalse(formStatus, "VERIFIED form disabled status", "FAILED to verify form disable status");

		boolean enableForm = formsManager.updateFormStatus(formDetails, true);
		TestValidation.IsTrue(enableForm, "ENABLED forms", "FAILED to enable forms");

		formStatus = Boolean.valueOf(formsManager.getFormDetails(checkformname, "IsEnabled"));
		TestValidation.IsTrue(formStatus, "VERIFIED form disabled status", "FAILED to verify form disable status");

	}

	@Test(description = "Forms Manager || Verify Verification Linking")
	public void LinkVerification_Forms() {

		String roleDetails [][] = {{Verifications.SUPERADMIN_ROLE_NAME,Verifications.SUPERADMIN_ROLE_ID}};

		verification1 = new Verifications();
		verification1Details = new VerificationDetails();
		verification1Details.VerificationName = verification1Name;
		verification1Details.IsEnable = true;
		verification1Details.IsCommentsEnabled = true;
		verification1Details.comments = Arrays.asList("API - Comment 1", "API - Comment 2");
		verification1Details.roles = roleDetails;

		SingleDataResponse1 createVerification = verification1.createVerification(verification1Details);
		verification1Details.VerificationID =  createVerification.Data;
		TestValidation.IsTrue(createVerification.Status, "CREATED Verification - "+verification1Name, "FAILED to create Verification - "+verification1Name);

		HashMap<String, String> formDetails = new HashMap<String, String>();
		formDetails.put(checkformname, formId1);

		boolean addVerification = formsManager.saveVerification(formDetails,verification1Details.VerificationID, true);
		TestValidation.IsTrue(addVerification, "ADDED verification for form(s)", "FAILED to add verification for form(s)");

		boolean formStatus = Boolean.valueOf(formsManager.getVerificationDetails(checkformname, verification1Details.VerificationID, "IsEnabled"));
		TestValidation.IsTrue(formStatus, "VERIFIED form enabled for Verification", "FAILED to verify form status for Verification");

	}

	@Test(description = "Forms Manager - Charts || Verify Chart Linking")
	public void Link_FormChart() throws InterruptedException, ParseException {

		chartBuilder = new ChartBuilder();

		params1 = new ChartParams();
		params1.chartType = ChartTypes.XBAR_R;
		params1.chartTypeID = ChartTypeIDS.XBAR_R;
		params1.chartName = XBarRChartName;
		params1.chartEnable = true;

		params1.chartRules = Arrays.asList(ChartRules.PRIMARY_RULE1, ChartRules.PRIMARY_RULE2, ChartRules.PRIMARY_RULE3, ChartRules.PRIMARY_RULE4, ChartRules.PRIMARY_RULE5, ChartRules.PRIMARY_RULE6,
				ChartRules.PRIMARY_RULE7, ChartRules.PRIMARY_RULE8, ChartRules.PRIMARY_RULE9, ChartRules.SECONDARY_RULE1, ChartRules.SECONDARY_RULE4, ChartRules.SECONDARY_RULE5, ChartRules.SECONDARY_RULE8);

		boolean createChart = chartBuilder.createChart(params1);
		TestValidation.IsTrue(createChart, 
				"CREATED chart - "+XBarRChartName, 
				"FAILED to create chart - "+XBarRChartName);

		String chartID = chartBuilder.getChartDetails(XBarRChartName, "Id");


		FormDetails formDetails = new FormDetails();
		ChartConfig chartDetails= new ChartConfig();

		HashMap<String, String> ResourceDetails = new HashMap<String, String>();
		ResourceDetails.put(supplierResInstance, resourceId1);

		formDetails.FormId = formId1;
		formDetails.ResourceID = resourceId1;
		formDetails.ResourceName = supplierResInstance;

		formDetails.FieldNames = Arrays.asList(NumericField1, NumericField2);

		List<String> NumericFieldIDs = new ArrayList<>();
		NumericFieldIDs = formsManager.getFormDetails(formDetails);

		formDetails.FieldIDs = NumericFieldIDs;

		chartDetails.FormEntityID = formId1;
		chartDetails.ChartTypeID = ChartTypeIDS.XBAR_R;
		chartDetails.ChartTypeName = ChartTypes.XBAR_R;
		chartDetails.ChartTemplateID = chartID;
		chartDetails.ChartTemplateName = XBarRChartName;
		chartDetails.FormChartLinkName = ChartLinkName;

		chartDetails.FieldIDs = NumericFieldIDs;
		chartDetails.IdentifierFilterName1 = IdentifierField1;
		chartDetails.IdentifierFilterValue1 = "Test 1";
		chartDetails.IdentifierFilterName2 = IdentifierField2;
		chartDetails.IdentifierFilterValue2 = "Test 2";
		chartDetails.LocationID = locationId1;
		chartDetails.ResourceDetails = ResourceDetails;

		boolean associateNewChart = formsManager.linkChartToForm(chartDetails);
		TestValidation.IsTrue(associateNewChart, 
				"ASSOCIATED chart - "+XBarRChartName+" with form - "+checkformname, 
				"FAILED to associate chart - "+XBarRChartName+" with form - "+checkformname);

	}


}
