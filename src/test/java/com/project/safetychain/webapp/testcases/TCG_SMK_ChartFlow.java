package com.project.safetychain.webapp.testcases;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.project.safetychain.api.models.support.TCG_FormCreation_Wrapper;
import com.project.safetychain.api.models.support.TCG_FormSubmission_Wrapper;
import com.project.safetychain.api.models.support.Support.Compliance;
import com.project.safetychain.api.models.support.Support.DPT_FIELD_TYPES;
import com.project.safetychain.api.models.support.Support.DPT_FORM_TYPES;
import com.project.safetychain.api.models.support.Support.FormFieldParamsCompliance;
import com.project.safetychain.api.models.support.Support.FormParams;
import com.project.safetychain.api.models.support.Support.IDENTIFIER_TYPES;
import com.project.safetychain.api.models.support.Support.RESOURCE_TYPES;
import com.project.safetychain.webapp.pageobjects.ChartBuilderPage;
import com.project.safetychain.webapp.pageobjects.ChartBuilderPage.ChartTypes;
import com.project.safetychain.webapp.pageobjects.ResourceDesignerPage.CATEGORYTYPES;
import com.project.safetychain.webapp.testcases.support.FormOperations.FormDetails;
import com.project.safetychain.webapp.pageobjects.CommonPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.COLUMNHEADER;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.FSQARESOURCE;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.FSQATAB;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FIELD_TYPES;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FORMRESOURCETYPES;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FORMTYPES;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.IDENTIFIER_INPUT_TYPE;
import com.project.safetychain.webapp.pageobjects.FormsManagerChartsPage;
import com.project.safetychain.webapp.pageobjects.FormsManagerChartsPage.FieldSelection;
import com.project.safetychain.webapp.pageobjects.FormsManagerChartsPage.FilterSelection;
import com.project.safetychain.webapp.pageobjects.FormsManagerChartsPage.OutputValues;
import com.project.safetychain.webapp.pageobjects.FormsManagerChartsPage.ResourceSelection;
import com.project.safetychain.webapp.pageobjects.FormsManagerPage;
import com.project.safetychain.webapp.pageobjects.HomePage;
import com.project.safetychain.webapp.pageobjects.LoginPage;
import com.project.safetychain.webapp.pageobjects.ManageLocationPage;
import com.project.safetychain.webapp.pageobjects.ManageResourcePage;
import com.project.safetychain.webapp.pageobjects.ResourceDesignerPage;
import com.project.safetychain.webapp.testcases.support.FormOperations;
import com.project.safetychain.webapp.testcases.support.FormOperations.FormDetails;
import com.project.safetychain.api.models.support.Support.FormFieldParams;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.utilities.ApiUtils;
import com.project.utilities.CommonMethods;
import com.project.utilities.ControlActions;

public class TCG_SMK_ChartFlow extends TestBase{
	ControlActions controlActions;
	CommonPage mainPage;	
	LoginPage lp;
	HomePage hp;
	ChartBuilderPage cbp;
	FormsManagerPage fmp;
	FormsManagerChartsPage fmcp;
	FormDesignerPage formDesignerPage;
	ResourceDesignerPage resourceDesigner;
	ManageResourcePage manageResource;
	ManageLocationPage locations;
	FSQABrowserPage fbp;
	FormOperations foperations;
	//FormDesignParams fdparams;
	//FormFieldParams ffparams1;
	//FormFieldParams ffparams2;
	FieldSelection fselect;
	FilterSelection filter;
	ResourceSelection res;
	OutputValues output;
	//FormDetails formdetails1;
	//FormDetails formdetails2;
	ApiUtils apiUtils = null;
	FormDetails formDetails;
	FormFieldParams ffp;

	public static String formName;
	public static String locationCategoryValue;
	public static String resourceCategoryValue;
	public static String resourceInstanceValue;
	public static String locationInstanceValue;
	public static String formtaskname;
	public static String resourceInstances;
	public static String numeric1;
	public static String numeric2;
	public static String identifier1;
	public static String identifier2;

	@BeforeClass(alwaysRun = true)
	public void groupInit() throws InterruptedException {

		formName = CommonMethods.dynamicText("Automation_CheckForm");
		locationCategoryValue = CommonMethods.dynamicText("Loc_Cat");
		resourceCategoryValue = CommonMethods.dynamicText("Equip_Cat");
		resourceInstanceValue = CommonMethods.dynamicText("Equip_Inst");		
		locationInstanceValue = CommonMethods.dynamicText("Loc_Inst");

		numeric1 = "Pieces";
		numeric2 = "Sample";
		identifier1 = "Line";
		identifier2 = "Shift";

		// ------------------------------------------------------------------------------------------------
		// API Implementation

		TCG_FormCreation_Wrapper formCreationWrapper = new TCG_FormCreation_Wrapper();
		TCG_FormSubmission_Wrapper formSubmissionWrapper = new TCG_FormSubmission_Wrapper();
		apiUtils = new ApiUtils();

		// ------------------------------------------------------------------------------------------------
		// API - User , Location & Resource Creation and Linking

		List<String> userList = Arrays.asList(adminUsername);		

		HashMap<String, List<String>> resCatMap = new HashMap<String, List<String>>();
		resCatMap.put(resourceCategoryValue, Arrays.asList(resourceInstanceValue));

		HashMap<String, List<String>> LocationMap = new HashMap<String, List<String>>();
		LocationMap.put(locationCategoryValue, Arrays.asList(locationInstanceValue));

		formCreationWrapper.Resource_Location_CreationWrapper(resCatMap, LocationMap, true, userList,
				RESOURCE_TYPES.EQUIPMENT);


		// ------------------------------------------------------------------------------------------------
		// API - Form Creation - Check

		FormFieldParams numericField = new FormFieldParams();
		numericField.DPTFieldType = DPT_FIELD_TYPES.NUMERIC;
		numericField.Name = numeric1;

		FormFieldParams numericField1 = new FormFieldParams();
		numericField1.DPTFieldType = DPT_FIELD_TYPES.NUMERIC;
		numericField1.Name = numeric2;

		FormFieldParams Identifier1 = new FormFieldParams();
		Identifier1.DPTFieldType = DPT_FIELD_TYPES.IDENTIFIER;
		Identifier1.Name = identifier1;
		Identifier1.identifierId = IDENTIFIER_TYPES.SELECT_ONE;
		Identifier1.IdentifierOption = Arrays.asList("LINE1","LINE2","LINE3");

		FormFieldParams Identifier2 = new FormFieldParams();
		Identifier2.DPTFieldType = DPT_FIELD_TYPES.IDENTIFIER;
		Identifier2.Name = identifier2;
		Identifier2.identifierId = IDENTIFIER_TYPES.SELECT_ONE;
		Identifier2.IdentifierOption = Arrays.asList("SHIFT1","SHIFT2","SHIFT3");


		String formId = apiUtils.getUUID();
		logInfo("FormId = " + formId);
		FormParams fp = new FormParams();
		fp.FormId = formId;
		fp.FormName = formName;
		logInfo("Form Name = " + formName);
		fp.type = DPT_FORM_TYPES.CHECK;
		fp.ResourceType = RESOURCE_TYPES.EQUIPMENT;
		fp.ResourceCategoryNm = resourceCategoryValue;
		fp.ResourceInstanceNm = resourceInstanceValue;
		fp.formElements = Arrays.asList(numericField,numericField1,Identifier1,Identifier2);
		fp.EquipmentResources = resCatMap;
		logInfo("fp.formElements = " + fp.formElements);	

		formCreationWrapper.API_Wrapper_Forms(fp);	

		// ------------------------------------------------------------------------------------------------
		// API - Submit form

		// API - Add values to fields in form
		FormFieldParams submitNumericField = new FormFieldParams();
		submitNumericField.Name = numeric1;
		submitNumericField.Value = "4";

		FormFieldParams submitNumericField1 = new FormFieldParams();
		submitNumericField1.Name = numeric2;
		submitNumericField1.Value = "8";

		FormFieldParams submitIdentifierField1 = new FormFieldParams();
		submitIdentifierField1.Name = identifier1;
		submitIdentifierField1.Value = "LINE1";

		FormFieldParams submitIdentifierField2 = new FormFieldParams();
		submitIdentifierField2.Name = identifier2;
		submitIdentifierField2.Value = "SHIFT1";

		FormFieldParams submitIdentifierField3 = new FormFieldParams();
		submitIdentifierField3.Name = identifier1;
		submitIdentifierField3.Value = "LINE2";

		FormFieldParams submitIdentifierField4 = new FormFieldParams();
		submitIdentifierField4.Name = identifier2;
		submitIdentifierField4.Value = "SHIFT2";




		// API - Form layout details
		FormParams fpSubmit = new FormParams();
		fpSubmit.FormName = formName;
		fpSubmit.ResourceInstanceNm = resourceInstanceValue;
		fpSubmit.LocationInstanceNm = locationInstanceValue;
		fpSubmit.formElements = Arrays.asList(submitNumericField,submitNumericField1,submitIdentifierField1,submitIdentifierField2);

		formSubmissionWrapper.API_Form_Submit_Wrapper(fpSubmit);

		// ------------------------------------------------------------------------------------------------

		// API - Form layout details
		FormParams fpSubmit1 = new FormParams();
		fpSubmit1.FormName = formName;
		fpSubmit1.ResourceInstanceNm = resourceInstanceValue;
		fpSubmit1.LocationInstanceNm = locationInstanceValue;
		fpSubmit1.formElements = Arrays.asList(submitNumericField,submitNumericField1,submitIdentifierField1,submitIdentifierField2);

		formSubmissionWrapper.API_Form_Submit_Wrapper(fpSubmit1);

		// ------------------------------------------------------------------------------------------------

		// API - Form layout details
		FormParams fpSubmit2 = new FormParams();
		fpSubmit2.FormName = formName;
		fpSubmit2.ResourceInstanceNm = resourceInstanceValue;
		fpSubmit2.LocationInstanceNm = locationInstanceValue;
		fpSubmit2.formElements = Arrays.asList(submitNumericField,submitNumericField1,submitIdentifierField3,submitIdentifierField4);

		formSubmissionWrapper.API_Form_Submit_Wrapper(fpSubmit2);

		// ------------------------------------------------------------------------------------------------

		driver = launchbrowser();
		controlActions = new ControlActions(driver);
		controlActions.getUrl(applicationUrl);
		lp = new LoginPage(driver);
		hp = new HomePage(driver);
		cbp = new ChartBuilderPage(driver);
		fmp = new FormsManagerPage(driver);
		fmcp = new FormsManagerChartsPage(driver);
		fbp = new FSQABrowserPage(driver);
		foperations = new FormOperations(driver);
		//fdparams = new FormDesignParams();
		//ffparams1 = new FormFieldParams();
		//ffparams2 = new FormFieldParams();
		fselect = new FieldSelection();
		filter = new FilterSelection();
		//formdetails1 = new FormDetails();
		//formdetails2 = new FormDetails();
		res = new ResourceSelection();
		output = new OutputValues();
		formDesignerPage = new FormDesignerPage(driver);
		resourceDesigner =  new ResourceDesignerPage(driver);
		manageResource = new ManageResourcePage(driver);
		locations =  new ManageLocationPage(driver);
		controlActions.getUrl(applicationUrl);
		hp = lp.performLogin(adminUsername, adminPassword);
		if(hp.error) {
			TCGFailureMsg = "Could NOT login to application";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}	

		/*String resourceInstances[][] = {{resourceInstanceValue,"true"}};

		HashMap<String,String> resourceCategories = new HashMap<String,String>();
		resourceCategories.put(CATEGORYTYPES.LOCATION, locationCategoryValue);
		resourceCategories.put(CATEGORYTYPES.EQUIPMENT, resourceCategoryValue);

		HashMap<String, List<String>> selectResources = new HashMap<String, List<String>>();
		selectResources.put(FORMRESOURCETYPES.EQUIPMENT,Arrays.asList(resourceCategoryValue));

		HashMap<String,List<String>> fieldDetails1 = new HashMap<String,List<String>>();
		fieldDetails1.put(FIELD_TYPES.NUMERIC, Arrays.asList(numeric1,numeric2));
		fieldDetails1.put(FIELD_TYPES.IDENTIFIER, Arrays.asList(identifier1));

		HashMap<String,List<String>> fieldDetails2 = new HashMap<String,List<String>>();
		fieldDetails2.put(FIELD_TYPES.IDENTIFIER, Arrays.asList(identifier2));


		ffparams1.FieldDetails = fieldDetails1;		
		ffparams1.IdentiferType = IDENTIFIER_INPUT_TYPE.SELECT_ONE;
		ffparams1.IdentifierValue = Arrays.asList("LINE1","LINE2","LINE3");

		ffparams2.FieldDetails = fieldDetails2;	
		ffparams2.IdentiferType = IDENTIFIER_INPUT_TYPE.SELECT_ONE;		
		ffparams2.IdentifierValue = Arrays.asList("SHIFT1","SHIFT2","SHIFT3");

		fdparams.FormType = FORMTYPES.CHECK;
		fdparams.FormName = formName;
		fdparams.SelectResources = selectResources;
		fdparams.DesignFields = Arrays.asList(ffparams1,ffparams2);
		fdparams.ReleaseForm = true;

		if(!resourceDesigner.createCategory(resourceCategories)) {
			TCGFailureMsg = "Could NOT create location and equipment category- ";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		if(!locations.createLocationInstanceLinkUser(locationCategoryValue,locationInstanceValue,adminUsername)) {
			TCGFailureMsg = "Could NOT create location instance - " + locationCategoryValue;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		if(!manageResource.addResourceInstances("Equipment", resourceCategoryValue, resourceInstances, true, locationInstanceValue)) {
			TCGFailureMsg = "Could NOT create resource " + resourceInstances + " & link Location - "+ locationInstanceValue;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		hp.clickFormDesignerMenu();
		if(!formDesignerPage.createAndReleaseForm(fdparams)) {
			TCGFailureMsg = "Could NOT create and release form - " + formName;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		hp.clickFSQABrowserMenu();
		if(!fbp.selectResourceDropDownandNavigate(FSQARESOURCE.EQUIPMENT,FSQATAB.FORMS)) {
			TCGFailureMsg = "For equipment category, could NOT navigate to FSQABrowser > Forms tab";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		if(!fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, formName)) {
			TCGFailureMsg = "Could not filter form - " + formName;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		HashMap<String, List<String>> fielddetails = new HashMap<String, List<String>>();
		fielddetails.put(numeric1,Arrays.asList("4"));
		fielddetails.put(numeric2,Arrays.asList("8"));
		fielddetails.put(identifier1,Arrays.asList("LINE1"));
		fielddetails.put(identifier2,Arrays.asList("SHIFT1"));

		HashMap<String, List<String>> fielddetails1 = new HashMap<String, List<String>>();
		fielddetails1.put(numeric1,Arrays.asList("4"));
		fielddetails1.put(numeric2,Arrays.asList("8"));
		fielddetails1.put(identifier1,Arrays.asList("LINE2"));
		fielddetails1.put(identifier2,Arrays.asList("SHIFT2"));

		formdetails1.fieldDetails = fielddetails;
		formdetails1.resourceName = resourceInstanceValue;
		formdetails1.locationName = locationInstanceValue;
		formdetails2.fieldDetails = fielddetails1;
		formdetails2.resourceName = resourceInstanceValue;
		formdetails2.locationName = locationInstanceValue;

		if(!foperations.submitData(formdetails1)) {
			TCGFailureMsg = "Failed to submit form";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		fbp.selectForm(formName);
		if(!foperations.submitData(formdetails1)) {
			TCGFailureMsg = "Failed to submit form";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		fbp.selectForm(formName);
		if(!foperations.submitData(formdetails2)) {
			TCGFailureMsg = "Failed to submit form";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}*/

	}

	@Test(description="Creation of chart")
	public void TestCase_31890() {

		String xbars = CommonMethods.dynamicText("XBARS");

		hp.clickFSQABrowserMenu();
		hp.clickChartBuilderMenu();
		boolean createchart = cbp.addNewChart(xbars,ChartTypes.XBARS);
		TestValidation.IsTrue(createchart, 
				"Chart Created", 
				"Failed to create Chart");
	}

	@Test(description="Creation of associated chart")
	public void TestCase_31476() {


		String ximr = CommonMethods.dynamicText("XIMR");

		hp.clickFSQABrowserMenu();
		hp.clickChartBuilderMenu();
		boolean createchart = cbp.addNewChart(ximr,ChartTypes.XIMR);
		TestValidation.IsTrue(createchart, 
				"Chart Created", 
				"Failed to create Chart");


		hp.clickFSQABrowserMenu();
		hp.clickFormsManagerMenu();
		fmp.selectFormAndNavigaToCharts(formName);

		boolean associatedchart = fmcp.createAssociateChart(ximr);
		TestValidation.IsTrue(associatedchart, 
				"Associated Chart Created", 
				"Failed to create Associated Chart");

	}

	@Test(description="Linking field values to charts")
	public void TestCase_34937() {

		String sumchart =  CommonMethods.dynamicText("Sum");

		hp.clickFSQABrowserMenu();
		hp.clickChartBuilderMenu();
		boolean createchart = cbp.addNewChart(sumchart,ChartTypes.SUMCHART);
		TestValidation.IsTrue(createchart, 
				"Chart Created", 
				"Failed to create Chart");

		hp.clickFSQABrowserMenu();
		hp.clickFormsManagerMenu();
		fmp.selectFormAndNavigaToCharts(formName);

		boolean associatedchart = fmcp.createAssociateChart(sumchart);
		TestValidation.IsTrue(associatedchart, 
				"Associated Chart Created", 
				"Failed to create Associated Chart");

		fselect.SelectOneField = numeric2;
		fselect.MultiSelect = Arrays.asList(numeric1,numeric2);

		boolean linkfields = fmcp.linkFieldsToCharts(fselect);
		TestValidation.IsTrue(linkfields, 
				"Linked fields to charts", 
				"Failed to Link fields to charts");
	}


	@Test(description="Selecting filters for chart")
	public void TestCase_29327() {


		String xbarr = CommonMethods.dynamicText("XBARR");

		hp.clickFSQABrowserMenu();
		hp.clickChartBuilderMenu();
		boolean createchart = cbp.addNewChart(xbarr,ChartTypes.XBARR);
		TestValidation.IsTrue(createchart, 
				"Chart Created", 
				"Failed to create Chart");

		hp.clickFSQABrowserMenu();
		hp.clickFormsManagerMenu();
		fmp.selectFormAndNavigaToCharts(formName);

		boolean associatedchart = fmcp.createAssociateChart(xbarr);
		TestValidation.IsTrue(associatedchart, 
				"Associated Chart Created", 
				"Failed to create Associated Chart");

		filter.lineselect = identifier1;
		filter.shiftselect = identifier2;
		filter.lineval = "LINE1";
		filter.shiftval = "SHIFT1";

		boolean filterselect = fmcp.selectFiltersforCharts(filter);
		TestValidation.IsTrue(filterselect, 
				"Linked fields to charts", 
				"Failed to Link fields to charts");
	}

	@Test(description="Select resource for chart")
	public void TestCase_34936() {

		String uchart = CommonMethods.dynamicText("U");

		hp.clickFSQABrowserMenu();
		hp.clickChartBuilderMenu();
		boolean createchart = cbp.addNewChart(uchart,ChartTypes.UCHART);
		TestValidation.IsTrue(createchart, 
				"Chart Created", 
				"Failed to create Chart");

		hp.clickFSQABrowserMenu();
		hp.clickFormsManagerMenu();
		fmp.selectFormAndNavigaToCharts(formName);

		boolean associatedchart = fmcp.createAssociateChart(uchart);
		TestValidation.IsTrue(associatedchart, 
				"Associated Chart Created", 
				"Failed to create Associated Chart");

		res.resource = resourceInstanceValue;

		boolean resourceselect = fmcp.selectResourceforCharts();
		TestValidation.IsTrue(resourceselect, 
				"Linked fields to charts", 
				"Failed to Link fields to charts");


	}

	@Test(description="Calculate mean and variation")
	public void TestCase_35054() {


		String npchart = CommonMethods.dynamicText("NP");

		hp.clickFSQABrowserMenu();
		hp.clickChartBuilderMenu();
		boolean createchart = cbp.addNewChart(npchart,ChartTypes.NPCHART);
		TestValidation.IsTrue(createchart, 
				"Chart Created", 
				"Failed to create Chart");

		hp.clickFSQABrowserMenu();
		hp.clickFormsManagerMenu();
		fmp.selectFormAndNavigaToCharts(formName);

		boolean associatedchart = fmcp.createAssociateChart(npchart);
		TestValidation.IsTrue(associatedchart, 
				"Associated Chart Created", 
				"Failed to create Associated Chart");

		fselect.SelectOneField = numeric1;
		fselect.MultiSelect = Arrays.asList(numeric1,numeric2);

		boolean linkfields = fmcp.linkFieldsToCharts(fselect);
		TestValidation.IsTrue(linkfields, 
				"Linked fields to charts", 
				"Failed to Link fields to charts");

		filter.lineselect = identifier1;
		filter.shiftselect = identifier2;
		filter.lineval = "LINE1";
		filter.shiftval = "SHIFT1";

		boolean filterselect = fmcp.selectFiltersforCharts(filter);
		TestValidation.IsTrue(filterselect, 
				"Linked fields to charts", 
				"Failed to Link fields to charts");

		res.resource = resourceInstanceValue;

		boolean resourceselect = fmcp.selectResourceforCharts();
		TestValidation.IsTrue(resourceselect, 
				"Linked fields to charts", 
				"Failed to Link fields to charts");

		boolean sampleinput = fmcp.samplesizeinput(ChartTypes.NPCHART, "300");
		TestValidation.IsTrue(sampleinput, 
				"Sample Input provided", 
				"Failed to provide sample input");

		output.mean = "4";
		output.variation = "";

		boolean calculate = fmcp.calculate(ChartTypes.NPCHART, "50");
		TestValidation.IsTrue(calculate, 
				"Sample Input provided", 
				"Failed to provide sample input");


	}

	@AfterClass(alwaysRun = true)
	public void closeBrowser() throws InterruptedException {
		driver.close();
	}

}
