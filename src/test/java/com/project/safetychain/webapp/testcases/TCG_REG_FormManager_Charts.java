package com.project.safetychain.webapp.testcases;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.project.safetychain.webapp.pageobjects.*;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.COLUMNHEADER;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.COLUMNSETTING;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.FSQARESOURCE;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.FSQATAB;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FIELD_TYPES;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FORMRESOURCETYPES;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FORMTYPES;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FormDesignParams;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FormFieldParams;
import com.project.safetychain.webapp.pageobjects.ManageLocationPage.LocationInstanceParams;
import com.project.safetychain.webapp.pageobjects.ResourceDesignerPage.CATEGORYTYPES;
import com.project.safetychain.webapp.pageobjects.VerificationsPage.VerificationDetsParams;
import com.project.safetychain.webapp.testcases.support.FormOperations;
import com.project.safetychain.webapp.testcases.support.FormOperations.FormDetails;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.utilities.CommonMethods;
import com.project.utilities.ControlActions;
import com.project.safetychain.webapp.pageobjects.ChartBuilderPage;
import com.project.safetychain.webapp.pageobjects.ChartBuilderPage.ChartTypes;
import com.project.safetychain.webapp.pageobjects.CommonPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage;
import com.project.safetychain.webapp.pageobjects.FormsManagerChartsPage;
import com.project.safetychain.webapp.pageobjects.FormsManagerPage;
import com.project.safetychain.webapp.pageobjects.HomePage;
import com.project.safetychain.webapp.pageobjects.LoginPage;
import com.project.safetychain.webapp.pageobjects.ManageLocationPage;
import com.project.safetychain.webapp.pageobjects.ManageResourcePage;
import com.project.safetychain.webapp.pageobjects.ResourceDesignerPage;



public class TCG_REG_FormManager_Charts extends TestBase {
	String av = null;
	ControlActions controlActions;
	FSQABrowserPage fbp;
	CommonPage mainPage;
	FSQABrowserFormsPage fbForms;
	ManageResourcePage manageResource ;
	//ManageLocationPage locations;
	FormDesignerPage fdp;
	VerificationsPage verification;
	HomePage hp;
	ResourceDesignerPage resourceDesigner;
	LoginPage lp;
	InboxPage inp;
	WorkGroupsPage wgp;
	FormsManagerPage fm;
	FSQABrowserRecordsPage frp;
	public static String formName;
	String Status = "Enabled";
	VerificationDetsParams vdp;
	FormDetails formDetails;
	FormFieldParams ffp;
	FormOperations formOperations;
	FormDesignParams fp;
	ManageRequirementPage mrp;
	ManageLocationPage mlp;
	ChartBuilderPage cbp;
	FormsManagerChartsPage fmcp;


	// Data

	public static String verificationName;
	public static String rolename ="SuperAdmin";
	public static String formName1;
	public static String formName2;
	public static String verificationComment1;
	public static String locationCategoryValue1;
	LocationInstanceParams locationInstance1, locationInstance2;
	public static String locationInstanceValue1, locationInstanceValue2;
	public static String locationInstanceValue3,locationInstanceValue4;
	public static String equipmentCategoryValue1, equipmentCategoryValue2;
	public static String equipmentResourceInstanceValue1, customerResourceInstanceValue2;
	public static String chkFreeTxt, ParaTxt, chkSelectOne, SelectMultiple, Numeric, chkDate, chkTime, chkDateTime;
	public static String fieldMin = "1", fieldTar = "2", fieldMax = "3", fieldUOM = "KG";
	public static String changeResourceCmmt;
	public static String locationCategoryValue;
	public static String locationInstanceValue;
	public static String customersCategoryValue;


	public static String resourceList;
	public static String resourceList1;
	public static String customersInstanceValue;
	public static String customersInstance2Value;
	public static String displayName = null;
	public static String programName;
	public static String programElementName;
	public static List<String> formsNMLst= new ArrayList<String>();
	public static String checkFormFM;
	public static String checkFormNamefm;
	public static String checkFormNamefm1;
	public static String checkFormNamefm2;
	public static String checkFormNamefm3;
	public static String checkFormNamefm4;
	public static String checkFormNamefm5;
	public static String checkRegFormName;
	public static String checkFormNamefmFormVersion;
	public static String checkFormNameFMSorting;
	public static String checkFormNameVerification;
	public static String checkFormLocation;
	public static String checkFormOverView;
	public static String checkFormOverViewLocFilter;

	public static String CheckFormVerification;

	public static String resourceType = "Customers";
	public static String resourceCategoryValue;
	public static String resourceInstanceValue, resourceInstanceValue1, resourceInstanceValue2;
	public static String signOffCmmt1;
	public static String questionnaireFormName1;
	public static String numFieldName;
	public static String numFieldName2;
	public static String supptempname;
	public static String supptempname1;
	public static String supplier;
	public static String doctype;
	public static String formreqname;
	public static String workGroupName;
	public static String locationname;
	public static String adminusername = "autouser01";
	public static String adminverificationPinTxt = "12345";
	public static String directobservation = "Direct Observation";
	public static String checkFormNamefmFormViewComment;
	public static String checkdisableFormNameVerification;

	public static String questionnaireFormNamelocation;
	public static String checkFormOverview;

	public static String checkformNameFilter;
	public static String checkFormLocation1 ;
	public static String questionnaireFormNamelocation1;
	public static String customersCategoryValue1;
	public static String customersInstanceValue1;
	public static String questionnaireFormNameOverview;

	public static String checkFormNameOverviewChart;
	public static String checkFormNameOverviewChart11;
	public static String checkFormNameOverviewChart22;
	public static String checkFormNameOverviewChart33;
	public static String checkFormNameOverviewChart38142;
	public static String checkFormNameOverviewChart38133;
	public static String checkFormNameOverviewChart38134;
	public static String checkFormNameOverviewChart38135;
	public static String checkFormNameOverviewChart38136;
	public static String checkFormNameOverviewChart38137;
	public static String checkFormNameOverviewChart38138;
	public static String checkFormNameOverviewChart38139;
	public static String checkFormNameOverviewChart38140;
	public static String checkFormNameOverviewChart38141;
	public static String checkFormNameOverviewChart38143;
	public static String checkFormNameOverviewChart38144;
	public static String checkFormNameOverviewChart101;
	public static String AuditFormNameOverviewChart101;
	public static String QuesttFormNameOverviewChart101;
	public static String checkFormNameOverviewChart38268;
	public static String checkFormNameOverviewChart38714;
	public static String checkFormNameOverviewChart41198;

	public static String checkFormNameOverviewNonNumericChart111;
	public static String AuditFormNameOverviewNonNumericChart111;
	public static String QuesttFormNameOverviewNonNumericChart111;

	public static String AverageChartNew;
	public static String UChartNew;
	public static String CChartNew;
	public static String CChart101;
	public static String XiMrChartNew;
	public static String NpChartNew;
	public static String PChartNew;
	public static String fieldOption;//38133
	public static String XBarSNew;
	public static String XBarRNew;
	public static String CChartNew101;
	public static String SumChartNew;
	public static String SumChartNew101;
	public static String AverageChartNew101;
	public static String XiMrChartNew143;
	public static String XBarRChartNew143;
	public static String XBarSChartNew143;
	public static String NpChartNew144;
	public static String PChartNew144;
	public static String UChartNew144;
	public static String SumChartNew38268;
	public static String XiMrChartNew38714;
	public static String XiMrChartNew41198;
	public static String Option;
	public static String UChart38138;
	public static String PChart38139;

	@BeforeClass(alwaysRun = true)
	public void groupInit() throws InterruptedException {
		formName = CommonMethods.dynamicText("Chk");
		changeResourceCmmt = CommonMethods.dynamicText("ChangeResCmmt");
		locationCategoryValue = CommonMethods.dynamicText("Loc_Cat");
		locationInstanceValue = CommonMethods.dynamicText("AutoLocation_Inst");
		customersCategoryValue = CommonMethods.dynamicText("Cust_Cat");
		customersInstanceValue = CommonMethods.dynamicText("Cust_Inst");
		customersInstance2Value = CommonMethods.dynamicText("Cust_Inst2");
		programName = CommonMethods.dynamicText("PD");
		programElementName = CommonMethods.dynamicText("PE");
		formsNMLst.add(formName);
		equipmentCategoryValue1 = CommonMethods.dynamicText("EquipmentCat1");
		equipmentCategoryValue2 = CommonMethods.dynamicText("EquipmentCat2");
		supptempname = CommonMethods.dynamicText("SuppTemp"); 
		supptempname = CommonMethods.dynamicText("SuppTemp1"); 
		locationname =CommonMethods.dynamicText("Auto_Location"); 
		workGroupName = CommonMethods.dynamicText("WG");
		formreqname = CommonMethods.dynamicText("Form");
		signOffCmmt1 = CommonMethods.dynamicText("Cmmt1");
		questionnaireFormName1 = CommonMethods.dynamicText("Automation_QuestionnaireForm1");
		verificationComment1 = CommonMethods.dynamicText("AutoComment1");
		customersInstanceValue = CommonMethods.dynamicText("Cust_Inst");
		locationCategoryValue1 = CommonMethods.dynamicText("LocCat11");
		locationInstanceValue = CommonMethods.dynamicText("Location_Inst");
		locationInstanceValue1 = CommonMethods.dynamicText("Location_Inst_11");
		locationInstanceValue2 = CommonMethods.dynamicText("Location_Inst_22");
		locationInstanceValue3 = CommonMethods.dynamicText("Location_Inst_1111");
		locationInstanceValue4 = CommonMethods.dynamicText("Location_Inst_1001");
		locationInstance1 = new LocationInstanceParams();
		verificationName = CommonMethods.dynamicText("AutoVN");
		checkFormNamefm = CommonMethods.dynamicText("Auto_CheckForm_FormMgr_PD");
		numFieldName = CommonMethods.dynamicText("Numeric1");
		numFieldName2 = CommonMethods.dynamicText("Numeric2");



		checkFormFM = CommonMethods.dynamicText("Auto_CheckForm_FormManager_PD");

		checkFormNamefm1 = CommonMethods.dynamicText("Auto_CheckForm_FMChk1_PD");
		checkFormNamefm2 = CommonMethods.dynamicText("Auto_CheckForm_FMChk2_PD");
		checkFormNamefm4 = CommonMethods.dynamicText("Auto_CheckForm_FM_PD");
		checkFormNamefm5  = CommonMethods.dynamicText("Auto_FormManager_PD_CheckForm");
		checkFormNamefmFormVersion = CommonMethods.dynamicText("Auto_FormManager_PD_Chk_ClickFormVersion");
		CheckFormVerification = CommonMethods.dynamicText("Auto_FormManager_PD_Chk_verificationName");
		questionnaireFormName1	= CommonMethods.dynamicText("Automation_QuestionnaireForm1");
		//checkFormNameVerification= CommonMethods.dynamicText("Auto_FormManager_PD_CHECK_verificationName"); 
		locationCategoryValue = CommonMethods.dynamicText("Location_Cat");
		resourceCategoryValue = CommonMethods.dynamicText("Resource_Cat");
		resourceInstanceValue = CommonMethods.dynamicText("Resource_Inst");
		resourceInstanceValue1 = CommonMethods.dynamicText("Resource_Inst1");
		resourceInstanceValue2 = CommonMethods.dynamicText("Resource_Inst2");
		locationInstanceValue = CommonMethods.dynamicText("Location_Inst");
		customersCategoryValue1 = CommonMethods.dynamicText("Customer_Inst"); 
		checkFormNameOverviewChart38268 = CommonMethods.dynamicText("Automation_Check_form_FM_overview_38268");



		checkRegFormName = CommonMethods.dynamicText("Auto_CheckForm_REG_Form_A");
		checkformNameFilter = CommonMethods.dynamicText("Auto_FormManager_FM_checkform_filters");

		//checkFormName1 = = CommonMethods.dynamicText("Auto_CheckForm_FormMGR_REG");
		checkFormNamefmFormViewComment = CommonMethods.dynamicText("Auto_FormManager_PD_Chk_checkFormNamefmFormViewComment");
		checkFormNameFMSorting = CommonMethods.dynamicText("Auto_FormManager_PD_checkform_sorting");
		//checkdisableFormNameVerification = CommonMethods.dynamicText("Auto_FormManager_PD_checkform_DisableVerification");

		driver = launchbrowser();
		controlActions = new ControlActions(driver);
		mainPage = new CommonPage(driver);
		controlActions.getUrl(applicationUrl);

		lp = new LoginPage(driver);
		//FormsManagerPage fm = new FormsManagerPage(driver);
		fdp = new FormDesignerPage(driver);
		fbp = new FSQABrowserPage(driver);
		frp = new FSQABrowserRecordsPage(driver);
		resourceDesigner = new ResourceDesignerPage(driver);
		//	locations = new ManageLocationPage(driver);
		mrp = new ManageRequirementPage(driver);
		fm = new FormsManagerPage(driver);
		mlp = new ManageLocationPage(driver);

		fbForms = new FSQABrowserFormsPage(driver);
		vdp = new VerificationDetsParams();
		cbp = new ChartBuilderPage(driver);
		fmcp = new FormsManagerChartsPage(driver);

		hp = lp.performLogin(adminUsername, adminPassword);

		HashMap<String, List<String>> resourceList = new HashMap<String, List<String>>();
		resourceList.put(CATEGORYTYPES.CUSTOMERS, Arrays.asList(customersInstanceValue));
		HashMap<String, List<String>> resourceList1 = new HashMap<String, List<String>>();
		resourceList1.put(CATEGORYTYPES.CUSTOMERS, Arrays.asList(customersInstanceValue));


		//		boolean location = fm.highlightLocation("Inst_2marchLoc");
		if (hp.error) {
			TCGFailureMsg = "Could NOT login to application";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
		threadsleep(2000);
		vdp.VerificationName = verificationName;
		vdp.EnableVerification = true;
		vdp.AddComments = new ArrayList<String>();
		vdp.AddComments.add(verificationComment1);
		vdp.Rolename = rolename;

		threadsleep(2000);
		
		hp.clickFSQABrowserMenu();
		ManageLocationPage mlp = new ManageLocationPage(driver);
		if(!mlp.createLocation(locationCategoryValue,locationInstanceValue)) {
			TCGFailureMsg = "Could NOT create location category - " + locationCategoryValue;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}


		ManageResourcePage mrp = new ManageResourcePage(driver);
		if(!mrp.createResourceLinkLocation("Customers", customersCategoryValue, 
				customersInstanceValue,locationInstanceValue)) {
			TCGFailureMsg = "Could NOT create resource " + customersInstanceValue + " & link Location - "+ locationInstanceValue;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}



		//FORM - Creation and Release
		HashMap<String, List<String>> fields = new HashMap<String, List<String>>();
		fields.put(FIELD_TYPES.NUMERIC, Arrays.asList(numFieldName,numFieldName2));
		//fields.put(FIELD_TYPES.NUMERIC, Arrays.asList(numFieldName2));

		FormFieldParams ffp = new FormFieldParams();
		ffp.FieldDetails = fields;

		HashMap<String, List<String>> resource = new HashMap<String, List<String>>();
		resource.put(FORMRESOURCETYPES.CUSTOMERS, Arrays.asList(customersCategoryValue));

		FormDesignParams fdpDets = new FormDesignParams();
		fdpDets.FormType = FORMTYPES.CHECK;
		fdpDets.FormName = checkFormNameOverviewChart38268;
		fdpDets.SelectResources = resource;
		fdpDets.DesignFields = Arrays.asList(ffp);
		//fdpDets.ReleaseForm = true;

		FormDesignerPage fdp = hp.clickFormDesignerMenu();
		if(!fdp.createAndReleaseForm(fdpDets)) {
			TCGFailureMsg = "Could NOT create unreleased form - " + checkFormNameOverviewChart38268;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}



	}






	@Test(description="Forms Manager - Charts || Verify 'Close' button functionality")
	public void TestCase_37684() throws Exception{
		
		try {


		hp.clickFSQABrowserMenu();
		checkFormNameOverviewChart = CommonMethods.dynamicText("Automation_Check_form_FM_overview");

		boolean createAndReleaseForm = fdp.createAndReleaseForm("Check", checkFormNameOverviewChart,"Customers", customersCategoryValue,
				customersInstanceValue); 

		TestValidation.IsTrue(createAndReleaseForm, 
				"Able to create and release form:" + checkFormNameOverviewChart,
				"Could NOT able to create and release forms:" +checkFormNameOverviewChart);


		fm = hp.clickFormsManagerMenu();

		TestValidation.Equals(fm.error, 
				false, 
				"CLICKED on Forms manager menu", 
				"Could NOT click on Forms manager menu");


		boolean searchCheckformtoClickChart = fm.selectFormAndNavigaToCharts(checkFormNameOverviewChart);
		TestValidation.IsTrue(searchCheckformtoClickChart, 
				"Opened Form" + checkFormNameOverviewChart, 
				"Unable to open the Form" + checkFormNameOverviewChart);


		boolean clickChartClose = fm.clickChartClose();
		TestValidation.IsTrue(clickChartClose, 
				"Closed the Chart Menu", 
				"Unable to close the Chart Menu" );

		}

		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				TestValidation.Fail(TCGFailureMsg);
			}
			
		}	
		
	}
	@Test(description="Creation of Average chart")
	public void TestCase_37649() {
		
		try {

		AverageChartNew = CommonMethods.dynamicText("AvgChart1");

		hp.clickFSQABrowserMenu();
		hp.clickChartBuilderMenu();
		boolean createAveragechart = cbp.addNewChart(AverageChartNew,ChartTypes.AVERAGECHART);
		TestValidation.IsTrue(createAveragechart, 
				"Average Chart Created successfully", 
				"Failed to create Average Chart");
		}
		
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				TestValidation.Fail(TCGFailureMsg);
			}
			
		}
	}



	@Test(description="Forms Manager - Charts || Verify user should not able to add duplicate chart name")
	public void TestCase_37682() {

		try {

			AverageChartNew = CommonMethods.dynamicText("AvgChart2");

			hp.clickFSQABrowserMenu();
			hp.clickChartBuilderMenu();
			boolean createAveragechart = cbp.addNewChart(AverageChartNew,ChartTypes.AVERAGECHART);
			TestValidation.IsTrue(createAveragechart, 
					"Average Chart Created successfully", 
					"Failed to create Average Chart");

			hp.clickFSQABrowserMenu();
			checkFormNameOverviewChart11 = CommonMethods.dynamicText("Automation_Check_form_FM_overview");

			boolean createAndReleaseForm = fdp.createAndReleaseForm("Check", checkFormNameOverviewChart11,"Customers", customersCategoryValue,
					customersInstanceValue); 

			TestValidation.IsTrue(createAndReleaseForm, 
					"Able to create and release form:" + checkFormNameOverviewChart11,
					"Could NOT able to create and release forms:" +checkFormNameOverviewChart11);


			fm = hp.clickFormsManagerMenu();

			TestValidation.Equals(fm.error, 
					false, 
					"CLICKED on Forms manager menu", 
					"Could NOT click on Forms manager menu"); 

			boolean searchCheckformtoClickChart = fm.selectFormAndNavigaToCharts(checkFormNameOverviewChart11);
			TestValidation.IsTrue(searchCheckformtoClickChart, 
					"Opened Form" + checkFormNameOverviewChart11, 
					"Unable to open the Form" + checkFormNameOverviewChart11 );

			boolean associatedchart = fmcp.createAssociateChart(AverageChartNew);
			TestValidation.IsTrue(associatedchart, 
					"Associated Chart Created", 
					"Failed to create Associated Chart");

			boolean associatedchart1 = fmcp.createAssociateChart(AverageChartNew);
			TestValidation.IsTrue(associatedchart1, 
					"Popup display for Chart instance name must be unique", 
					"Failed to visible the pop up for duplicate instance name");

			boolean clickOkClear = fm.clickOkPopup();
			TestValidation.IsTrue(clickOkClear, 
					"Able to click Ok button on Popup display for Chart instance name must be unique and clear the popup by clicking on Clear button", 
					"Failed to click Ok button on  the pop up for duplicate instance name and clear the popup by clicking on Clear button");
		}

		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				TestValidation.Fail(TCGFailureMsg);
			}

		}

	}



	@Test(description="Forms Manager - Charts || Verify user should be able to delete chart for the form")
	public void TestCase_37675() {
		
		try {

		hp.clickFSQABrowserMenu();
		checkFormNameOverviewChart22 = CommonMethods.dynamicText("Automation_Check_form_FM_overview");

		boolean createAndReleaseForm = fdp.createAndReleaseForm("Check", checkFormNameOverviewChart22,"Customers", customersCategoryValue,
				customersInstanceValue); 

		TestValidation.IsTrue(createAndReleaseForm, 
				"Able to create and release form:" + checkFormNameOverviewChart22,
				"Could NOT able to create and release forms:" +checkFormNameOverviewChart22);


		UChartNew = CommonMethods.dynamicText("UChart101");

		hp.clickFSQABrowserMenu();
		hp.clickChartBuilderMenu();
		boolean createUchart = cbp.addNewChart(UChartNew,ChartTypes.UCHART);
		TestValidation.IsTrue(createUchart, 
				"U Chart Created successfully", 
				"Failed to create U Chart");


		fm = hp.clickFormsManagerMenu();

		TestValidation.Equals(fm.error, 
				false, 
				"CLICKED on Forms manager menu", 
				"Could NOT click on Forms manager menu"); 

		UChartNew = CommonMethods.dynamicText("UChart101");


		boolean searchCheckformtoClickChart = fm.selectFormAndNavigaToCharts(checkFormNameOverviewChart22);
		TestValidation.IsTrue(searchCheckformtoClickChart, 
				"Opened Form" + checkFormNameOverviewChart22, 
				"Unable to open the Form" + checkFormNameOverviewChart22); 

		boolean associateUchart = fmcp.createAssociateChart(UChartNew);
		TestValidation.IsTrue(associateUchart, 
				"U Chart Associated", 
				"Failed to Associate U Chart");

		boolean clickDelete = fm.deleteChart();
		TestValidation.IsTrue(clickDelete, 
				"Able to click delete button to delete the chart", 
				"Failed to click on delete button to delete the chart");
		}
		
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				TestValidation.Fail(TCGFailureMsg);
			}
			
		}


	}

	@Test(description="Forms Manager - Charts || Verify user should be able to see location(s) that are only associated with the user & selected form")
	public void TestCase_37674() {
		
		try {

		hp.clickFSQABrowserMenu();
		checkFormNameOverviewChart33 = CommonMethods.dynamicText("Automation_Check_form_FM_overview");

		boolean createAndReleaseForm = fdp.createAndReleaseForm("Check", checkFormNameOverviewChart33,"Customers", customersCategoryValue,
				customersInstanceValue); 

		TestValidation.IsTrue(createAndReleaseForm, 
				"Able to create and release form:" + checkFormNameOverviewChart33,
				"Could NOT able to create and release forms:" +checkFormNameOverviewChart33);


		CChartNew = CommonMethods.dynamicText("CChart101");

		hp.clickFSQABrowserMenu();
		hp.clickChartBuilderMenu();
		boolean createCchart = cbp.addNewChart(CChartNew,ChartTypes.CCHART);
		TestValidation.IsTrue(createCchart, 
				"C Chart Created successfully", 
				"Failed to create C Chart");



		fm = hp.clickFormsManagerMenu();

		TestValidation.Equals(fm.error, 
				false, 
				"CLICKED on Forms manager menu", 
				"Could NOT click on Forms manager menu"); 



		boolean searchCheckformtoClickChart = fm.selectFormAndNavigaToCharts(checkFormNameOverviewChart33);
		TestValidation.IsTrue(searchCheckformtoClickChart, 
				"Opened Form" + checkFormNameOverviewChart33, 
				"Unable to open the Form" + checkFormNameOverviewChart33); 

		boolean associateCchart = fmcp.createAssociateChart(CChartNew);
		TestValidation.IsTrue(associateCchart, 
				"Associated C Chart Created", 
				"Failed to create Associated C Chart");

		boolean resourceselect = fm.selectResourceTabforCharts();
		TestValidation.IsTrue(resourceselect, 
				"Resource tab to charts", 
				"Failed to click resource tab for charts");



		boolean resourcelocation = fm.clickResourceTabLocation();
		TestValidation.IsTrue(resourcelocation, 
				"Able to view Location Location associated with form", 
				"Failed to click Location Location for charts");
		}
		
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				TestValidation.Fail(TCGFailureMsg);
			}
			
		}


	}

	@Test(description="Test Case 38273: Forms Manager - Charts || Verify chart configuration for different form types")
	public void TestCase_37673() {
		
		try {


		checkFormNameOverviewChart101 = CommonMethods.dynamicText("Automation_Check_form_FM_overview_101");
		AuditFormNameOverviewChart101 = CommonMethods.dynamicText("Auto_Audit_FM_overview");
		QuesttFormNameOverviewChart101 = CommonMethods.dynamicText("Automation_quest_form_FM_overview_101");

		hp.clickFSQABrowserMenu();
		boolean createAndReleaseForm = fdp.createAndReleaseForm("Check", checkFormNameOverviewChart101,"Customers", customersCategoryValue,
				customersInstanceValue); 
		TestValidation.IsTrue(createAndReleaseForm, 
				"Able to create and release form:" + checkFormNameOverviewChart101,
				"Could NOT able to create and release forms:" +checkFormNameOverviewChart101);

		hp.clickFSQABrowserMenu();
		boolean createAndReleaseFormAudit = fdp.createAndReleaseForm("Audit", AuditFormNameOverviewChart101,"Customers", customersCategoryValue,
				customersInstanceValue); 
		TestValidation.IsTrue(createAndReleaseFormAudit, 
				"Able to create and release form:" + AuditFormNameOverviewChart101,
				"Could NOT able to create and release forms:" +AuditFormNameOverviewChart101);

		hp.clickFSQABrowserMenu();
		boolean createAndReleaseFormQuest = fdp.createAndReleaseForm("Questionnaire", QuesttFormNameOverviewChart101,"Customers", customersCategoryValue,
				customersInstanceValue); 
		TestValidation.IsTrue(createAndReleaseFormQuest, 
				"Able to create and release form:" + QuesttFormNameOverviewChart101,
				"Could NOT able to create and release forms:" +QuesttFormNameOverviewChart101);

		fm = hp.clickFormsManagerMenu();


		TestValidation.Equals(fm.error, 
				false, 
				"CLICKED on Forms manager menu", 
				"Could NOT click on Forms manager menu"); 



		boolean searchCheckformtoClickChart = fm.selectFormAndNavigaToCharts(checkFormNameOverviewChart101);
		TestValidation.IsTrue(searchCheckformtoClickChart, 
				"Opened Form" + checkFormNameOverviewChart101, 
				"Unable to open the Form" + checkFormNameOverviewChart101); 

		boolean clickChartClose = fm.clickChartClose();
		TestValidation.IsTrue(clickChartClose, 
				"Closed the Chart Menu", 
				"Unable to close the Chart Menu" );


		boolean searchAuditformtoClickChart = fm.selectFormAndNavigaToCharts(AuditFormNameOverviewChart101);
		TestValidation.IsTrue(searchAuditformtoClickChart, 
				"Opened Form" + createAndReleaseFormAudit, 
				"Unable to open the Form" + createAndReleaseFormAudit); 

		boolean clickChartClose1 = fm.clickChartClose();
		TestValidation.IsTrue(clickChartClose1, 
				"Closed the Chart Menu", 
				"Unable to close the Chart Menu" );


		boolean searchQuesttformtoClickChart = fm.searchAndFormSelection(QuesttFormNameOverviewChart101);
		TestValidation.IsTrue(searchQuesttformtoClickChart, 
				"Opened Form" + createAndReleaseFormAudit, 
				"Unable to open the Form" + createAndReleaseFormAudit); 

		boolean clickChartClose2 = fm.clickOverviewFormClose();
		TestValidation.IsTrue(clickChartClose2, 
				"Closed the Chart Menu", 
				"Unable to close the Chart Menu" );
		}
		
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				TestValidation.Fail(TCGFailureMsg);
			}
			
		}
	}


	@Test(description="Test Case 38271: Forms Manager - Charts || Verify chart association restriction message if form doesn't has Numeric field")
	public void TestCase_37671() {
		
		try {


		checkFormNameOverviewNonNumericChart111 = CommonMethods.dynamicText("Auto_Chk_FM_NonNumeric_111");
		AuditFormNameOverviewNonNumericChart111 = CommonMethods.dynamicText("Auto_Audit_FM_NonNumeric111");


		hp.clickFSQABrowserMenu();
		boolean createAndReleaseForm = fdp.createAndReleaseFormNonNumeric("Check", checkFormNameOverviewNonNumericChart111,"Customers", customersCategoryValue,
				customersInstanceValue); 
		TestValidation.IsTrue(createAndReleaseForm, 
				"Able to create and release form:" + checkFormNameOverviewNonNumericChart111,
				"Could NOT able to create and release forms:" +checkFormNameOverviewNonNumericChart111);


		fm = hp.clickFormsManagerMenu();


		boolean searchCheckformtoClickChart = fm.selectFormAndNavigaToCharts(checkFormNameOverviewNonNumericChart111);
		TestValidation.IsTrue(searchCheckformtoClickChart, 
				"Opened Form" + checkFormNameOverviewNonNumericChart111, 
				"Unable to open the Form" + checkFormNameOverviewNonNumericChart111); 
		
		}
		
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				TestValidation.Fail(TCGFailureMsg);
			}
			
		}
	}



	@Test(description="Test Case 38142: Forms Manager || charts - Resources || Verify Mean column for CChart chart type")
	public void TestCase_38142() {
		
		try {

		hp.clickFSQABrowserMenu();
		checkFormNameOverviewChart38142 = CommonMethods.dynamicText("Automation_Check_form_FM_overview_38142");

		boolean createAndReleaseForm = fdp.createAndReleaseForm("Check", checkFormNameOverviewChart38142,"Customers", customersCategoryValue,
				customersInstanceValue); 

		TestValidation.IsTrue(createAndReleaseForm, 
				"Able to create and release form:" + checkFormNameOverviewChart38142,
				"Could NOT able to create and release forms:" +checkFormNameOverviewChart38142);


		CChartNew = CommonMethods.dynamicText("CChart101");

		hp.clickFSQABrowserMenu();
		hp.clickChartBuilderMenu();
		boolean createCchart = cbp.addNewChart(CChartNew,ChartTypes.CCHART);
		TestValidation.IsTrue(createCchart, 
				"C Chart Created successfully", 
				"Failed to create C Chart");


		fm = hp.clickFormsManagerMenu();


		//CChartNew = CommonMethods.dynamicText("CChart101");


		boolean searchCheckformtoClickChart = fm.selectFormAndNavigaToCharts(checkFormNameOverviewChart38142);
		TestValidation.IsTrue(searchCheckformtoClickChart, 
				"Opened Form" + checkFormNameOverviewChart38142, 
				"Unable to open the Form" + checkFormNameOverviewChart38142); 

		boolean associateCchart = fm.createAssociateChart(CChartNew);
		TestValidation.IsTrue(associateCchart, 
				"C Chart Associated", 
				"Failed to Associate C Chart");


		boolean resourceselect38142 = fm.selectResourceTabforCharts();
		TestValidation.IsTrue(resourceselect38142, 
				"Resource tab to charts", 
				"Failed to click resource tab for charts");
		
		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				TestValidation.Fail(TCGFailureMsg);
			}
		}


	}


	@Test(description="Test Case 38134: Forms Manager || Charts - Fields || Verify field values supported for Np chart type")
	public void TestCase_38134() {
		
		try {

		hp.clickFSQABrowserMenu();
		checkFormNameOverviewChart38134 = CommonMethods.dynamicText("Automation_Check_form_FM_overview_38134");

		boolean createAndReleaseForm = fdp.createAndReleaseForm("Check", checkFormNameOverviewChart38134,"Customers", customersCategoryValue,
				customersInstanceValue); 

		TestValidation.IsTrue(createAndReleaseForm, 
				"Able to create and release form:" + checkFormNameOverviewChart38134,
				"Could NOT able to create and release forms:" +checkFormNameOverviewChart38134);


		NpChartNew = CommonMethods.dynamicText("Np101");

		hp.clickFSQABrowserMenu();
		hp.clickChartBuilderMenu();
		boolean createNpchart = cbp.addNewChart(NpChartNew,ChartTypes.NPCHART);
		TestValidation.IsTrue(createNpchart, 
				"Np Chart Created successfully", 
				"Failed to create Np Chart");


		fm = hp.clickFormsManagerMenu();



		fieldOption = "Numeric Field";


		boolean searchCheckformtoClickChart = fm.selectFormAndNavigaToCharts(checkFormNameOverviewChart38134);
		TestValidation.IsTrue(searchCheckformtoClickChart, 
				"Opened Form" + checkFormNameOverviewChart38134, 
				"Unable to open the Form" + checkFormNameOverviewChart38134); 

		boolean associateNpchart = fm.createAssociateChart(NpChartNew);
		TestValidation.IsTrue(associateNpchart, 
				"Np Chart Associated", 
				"Failed to Associate Np Chart");

		boolean fieldTabchart = fm.selectFieldTabforCharts();
		TestValidation.IsTrue(fieldTabchart, 
				"Field tab is selected", 
				"Failed to select Field Tab");

		boolean selectfieldValuechartNp = fm.selectFieldValue("Numeric Field");
		TestValidation.IsTrue(selectfieldValuechartNp, 
				"Np Chart Field Value selected and saved", 
				"Failed to select and saved Np Chart Field Value ");
		
		boolean closeField1 = fm.fieldTabClose();
		TestValidation.IsTrue(closeField1,
				"Field Tab get closed", 
				"Failed to click close button");
		
		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				TestValidation.Fail(TCGFailureMsg);
			}
		}



	} 


	@Test(description="Test Case 38133: Forms Manager || Charts - Fields || Verify field values supported for XiMr chart type")
	public void TestCase_38133() {
		
		try {

		hp.clickFSQABrowserMenu();
		checkFormNameOverviewChart38133 = CommonMethods.dynamicText("Automation_Check_form_FM_overview_38133");

		boolean createAndReleaseForm = fdp.createAndReleaseForm("Check", checkFormNameOverviewChart38133,"Customers", customersCategoryValue,
				customersInstanceValue); 

		TestValidation.IsTrue(createAndReleaseForm, 
				"Able to create and release form:" + checkFormNameOverviewChart38133,
				"Could NOT able to create and release forms:" +checkFormNameOverviewChart38133);


		XiMrChartNew = CommonMethods.dynamicText("XiMr101");

		hp.clickFSQABrowserMenu();
		hp.clickChartBuilderMenu();
		boolean createCchart = cbp.addNewChart(XiMrChartNew,ChartTypes.XIMR);
		TestValidation.IsTrue(createCchart, 
				"C Chart Created successfully", 
				"Failed to create XiMr Chart");


		fm = hp.clickFormsManagerMenu();

		fieldOption = "Numeric Field";


		boolean searchCheckformtoClickChart = fm.selectFormAndNavigaToCharts(checkFormNameOverviewChart38133);
		TestValidation.IsTrue(searchCheckformtoClickChart, 
				"Opened Form" + checkFormNameOverviewChart38133, 
				"Unable to open the Form" + checkFormNameOverviewChart38133); 

		boolean associateXiMrchart = fm.createAssociateChart(XiMrChartNew);
		TestValidation.IsTrue(associateXiMrchart, 
				"XiMr Chart Associated", 
				"Failed to Associate XiMrChart");

		boolean fieldTabchart = fm.selectFieldTabforCharts();
		TestValidation.IsTrue(fieldTabchart, 
				"Field tab is selected", 
				"Failed to select Field Tab");

		boolean selectfieldValuechart = fm.selectFieldValue("Numeric Field");
		TestValidation.IsTrue(selectfieldValuechart, 
				"XiMr Chart Field Value selected and saved", 
				"Failed to select and saved XiMr Chart Field Value ");
		
		
		boolean closeField = fm.fieldTabClose();
		TestValidation.IsTrue(closeField,
				"Field Tab get closed", 
				"Failed to click close button");
		
		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				TestValidation.Fail(TCGFailureMsg);
			}
		}
		



	} 



	@Test(description="Test Case 38139: Forms Manager || Charts - Fields || Verify field values supported for P chart type")
	public void TestCase_38139() {
		
		try {

		hp.clickFSQABrowserMenu();
		checkFormNameOverviewChart38139 = CommonMethods.dynamicText("Auto_Chk_FM_overview_38139");

		boolean createAndReleaseForm = fdp.createAndReleaseForm("Check", checkFormNameOverviewChart38139,"Customers", customersCategoryValue,
				customersInstanceValue); 

		TestValidation.IsTrue(createAndReleaseForm, 
				"Able to create and release form:" + checkFormNameOverviewChart38139,
				"Could NOT able to create and release forms:" +checkFormNameOverviewChart38139);


		PChart38139= CommonMethods.dynamicText("P101");

		hp.clickFSQABrowserMenu();
		hp.clickChartBuilderMenu();
		boolean createPchart = cbp.addNewChart(PChart38139,ChartTypes.PCHART);
		TestValidation.IsTrue(createPchart, 
				"P Chart Created successfully", 
				"Failed to create P Chart"); 


		fm = hp.clickFormsManagerMenu();


		fieldOption = "Numeric Field";


		boolean searchCheckformtoClickPChart = fm.selectFormAndNavigaToCharts(checkFormNameOverviewChart38139);
		TestValidation.IsTrue(searchCheckformtoClickPChart, 
				"Opened Form" + checkFormNameOverviewChart38139, 
				"Unable to open the Form" + checkFormNameOverviewChart38139); 

		boolean associatePchart = fm.createAssociateChart(PChart38139);
		TestValidation.IsTrue(associatePchart, 
				"P Chart Associated", 
				"Failed to Associate P Chart");

		boolean fieldTabPchart = fm.selectFieldTabforCharts();
		TestValidation.IsTrue(fieldTabPchart, 
				"Field tab is selected", 
				"Failed to select Field Tab");

		boolean selectfieldValuechartP= fm.selectFieldValue("Numeric Field");
		TestValidation.IsTrue(selectfieldValuechartP, 
				"P Chart Field Value selected and saved", 
				"Failed to select and saved P Chart Field Value ");
		
		boolean closeField139 = fm.fieldTabClose();
		TestValidation.IsTrue(closeField139,
				"Field Tab get closed", 
				"Failed to click close button");
				
		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				TestValidation.Fail(TCGFailureMsg);
			}
		}



	} 


	@Test(description="Test Case 38138: Forms Manager || Charts - Fields || Verify field values supported for U chart type")
	public void TestCase_38138() {
		
		try {

		hp.clickFSQABrowserMenu();
		checkFormNameOverviewChart38138 = CommonMethods.dynamicText("Auto_Chk_fm_overview_38138");

		boolean createAndReleaseForm = fdp.createAndReleaseForm("Check", checkFormNameOverviewChart38138,"Customers", customersCategoryValue,
				customersInstanceValue); 

		TestValidation.IsTrue(createAndReleaseForm, 
				"Able to create and release form:" + checkFormNameOverviewChart38138,
				"Could NOT able to create and release forms:" +checkFormNameOverviewChart38138);


		UChart38138 = CommonMethods.dynamicText("U138");

		hp.clickFSQABrowserMenu();
		hp.clickChartBuilderMenu();
		boolean createUchart = cbp.addNewChart(UChart38138,ChartTypes.UCHART);
		TestValidation.IsTrue(createUchart, 
				"U Chart Created successfully", 
				"Failed to create U Chart");


		fm = hp.clickFormsManagerMenu();



		fieldOption = "Numeric Field";


		boolean searchCheckformtoClickUChart = fm.selectFormAndNavigaToCharts(checkFormNameOverviewChart38138);
		TestValidation.IsTrue(searchCheckformtoClickUChart, 
				"Opened Form" + checkFormNameOverviewChart38138, 
				"Unable to open the Form" + checkFormNameOverviewChart38138); 

		boolean associateUchart = fm.createAssociateChart(UChart38138);
		TestValidation.IsTrue(associateUchart, 
				"U Chart Associated", 
				"Failed to Associate U Chart");

		boolean fieldTabUchart = fm.selectFieldTabforCharts();
		TestValidation.IsTrue(fieldTabUchart, 
				"Field tab is selected", 
				"Failed to select Field Tab");

		boolean selectfieldValueUchart= fm.selectFieldValue("Numeric Field");
		TestValidation.IsTrue(selectfieldValueUchart,
				"U Chart Field Value selected and saved", 
				"Failed to select and saved U Chart Field Value ");
		
		boolean closeField = fm.fieldTabClose();
		TestValidation.IsTrue(closeField,
				"Field Tab get closed", 
				"Failed to click close button");
		
		
		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				TestValidation.Fail(TCGFailureMsg);
			}
		}

	} 

	@Test(description="Test Case 38135: Forms Manager || Charts - Fields || Verify field values supported for XBarS chart type")
	public void TestCase_38135() {
		
		try {

		hp.clickFSQABrowserMenu();
		checkFormNameOverviewChart38135 = CommonMethods.dynamicText("Automation_Check_form_FM_overview_38135");

		boolean createAndReleaseForm = fdp.createAndReleaseForm("Check", checkFormNameOverviewChart38135,"Customers", customersCategoryValue,
				customersInstanceValue); 

		TestValidation.IsTrue(createAndReleaseForm, 
				"Able to create and release form:" + checkFormNameOverviewChart38135,
				"Could NOT able to create and release forms:" +checkFormNameOverviewChart38135);


		XBarSNew = CommonMethods.dynamicText("XBarS101");

		hp.clickFSQABrowserMenu();
		hp.clickChartBuilderMenu();
		boolean createXBarSchart = cbp.addNewChart(XBarSNew,ChartTypes.XBARS);
		TestValidation.IsTrue(createXBarSchart, 
				"XBarS Chart Created successfully", 
				"Failed to create XBarS Chart");


		fm = hp.clickFormsManagerMenu();



		fieldOption = "Numeric Field";


		boolean searchCheckformtoClickXBarSChart = fm.selectFormAndNavigaToCharts(checkFormNameOverviewChart38135);
		TestValidation.IsTrue(searchCheckformtoClickXBarSChart, 
				"Opened Form" + checkFormNameOverviewChart38135, 
				"Unable to open the Form" + checkFormNameOverviewChart38135); 

		boolean associateXBarSchart = fm.createAssociateChart(XBarSNew);
		TestValidation.IsTrue(associateXBarSchart, 
				"XBarS Chart Associated", 
				"Failed to Associate XBarS Chart");

		boolean fieldTabXBarSchart = fm.selectFieldTabforCharts();
		TestValidation.IsTrue(fieldTabXBarSchart, 
				"Field tab is selected", 
				"Failed to select Field Tab");


		boolean selectfieldValueXBarSchart= fm.fieldClickCheckBoxdandSave();
		TestValidation.IsTrue(selectfieldValueXBarSchart,
				"XBarS Chart Field Value checked and saved", 
				"Failed to select and checked XBarS Chart Field Value");
		
		
		boolean clickOkPostValidation= fm.fieldClickOkButtonCheckValidation();
		TestValidation.IsTrue(clickOkPostValidation,
				"XBarS Chart Pop up get closed", 
				"Failed to close the Pop up");
		
		boolean clickClearButtonValidation= fm.fieldClickClearButton();
		TestValidation.IsTrue(clickClearButtonValidation,
				"XBarS Chart page get Clear closed", 
				"Failed to click clear button of XBarS");
		
		}
		
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				TestValidation.Fail(TCGFailureMsg);
			}
		}

		

	}


	@Test(description="Test Case 38136: Forms Manager || Charts - Fields || Verify field values supported for XBarR chart type")
	public void TestCase_38136() {// passed

		try {
		hp.clickFSQABrowserMenu();
		checkFormNameOverviewChart38136 = CommonMethods.dynamicText("Automation_Check_form_FM_overview_38136");

		boolean createAndReleaseForm = fdp.createAndReleaseForm("Check", checkFormNameOverviewChart38136,"Customers", customersCategoryValue,
				customersInstanceValue); 

		TestValidation.IsTrue(createAndReleaseForm, 
				"Able to create and release form:" + checkFormNameOverviewChart38136,
				"Could NOT able to create and release forms:" +checkFormNameOverviewChart38136);


		XBarRNew = CommonMethods.dynamicText("XBarR101");

		hp.clickFSQABrowserMenu();
		hp.clickChartBuilderMenu();
		boolean createXBarRchart = cbp.addNewChart(XBarRNew,ChartTypes.XBARR);
		TestValidation.IsTrue(createXBarRchart, 
				"XBarR Chart Created successfully", 
				"Failed to create XBarR Chart");


		fm = hp.clickFormsManagerMenu();



		fieldOption = "Numeric Field";


		boolean searchCheckformtoClickXBarRChart = fm.selectFormAndNavigaToCharts(checkFormNameOverviewChart38136);
		TestValidation.IsTrue(searchCheckformtoClickXBarRChart, 
				"Opened Form" + checkFormNameOverviewChart38136, 
				"Unable to open the Form" + checkFormNameOverviewChart38136); 

		boolean associatePchart = fm.createAssociateChart(XBarRNew);
		TestValidation.IsTrue(associatePchart, 
				"XBarR Chart Associated", 
				"Failed to Associate XBarR Chart");

		boolean fieldTabXBarRchart = fm.selectFieldTabforCharts();
		TestValidation.IsTrue(fieldTabXBarRchart, 
				"Field tab is selected", 
				"Failed to select Field Tab");


		boolean selectfieldValueXBarRchart= fm.fieldClickCheckBoxdandSave();
		TestValidation.IsTrue(selectfieldValueXBarRchart,
				"XBarR Chart Field Value checked and saved", 
				"Failed to select and checked XBarR Chart Field Value");
		
		boolean clearPostValidation1= fm.fieldClickOkButtonCheckValidation();
		TestValidation.IsTrue(clearPostValidation1,
				"XBarR Chart Pop up get cleared and closed", 
				"Failed to close the Pop up");
		
	
		
		boolean clickClearButtonValidation1= fm.fieldClickClearButton();
		TestValidation.IsTrue(clickClearButtonValidation1,
				"XBarR Chart page get Clear closed", 
				"Failed to click Clear button of the XBarR Chart page");
		}

		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				TestValidation.Fail(TCGFailureMsg);
			}
		}

	} 

		
	@Test(description="Test Case 38140: Forms Manager || Charts - Fields || Verify field values supported for C chart type")
	public void TestCase_38140() {
		try {
		
		hp.clickFSQABrowserMenu();
		checkFormNameOverviewChart38140 = CommonMethods.dynamicText("Automation_Check_form_FM_overview_38140");

		boolean createAndReleaseForm = fdp.createAndReleaseForm("Check", checkFormNameOverviewChart38140,"Customers", customersCategoryValue,
				customersInstanceValue); 

		TestValidation.IsTrue(createAndReleaseForm, 
				"Able to create and release form:" + checkFormNameOverviewChart38140,
				"Could NOT able to create and release forms:" +checkFormNameOverviewChart38140);


		CChartNew101 = CommonMethods.dynamicText("CChart101");

		hp.clickFSQABrowserMenu();
		hp.clickChartBuilderMenu();
		boolean createCNewchart = cbp.addNewChart(CChartNew101,ChartTypes.CCHART);
		TestValidation.IsTrue(createCNewchart, 
				"CNew Chart Created successfully", 
				"Failed to create CNew Chart");


		fm = hp.clickFormsManagerMenu();



		fieldOption = "Numeric Field";


		boolean searchCheckformtoClickCChartNew = fm.selectFormAndNavigaToCharts(checkFormNameOverviewChart38140);
		TestValidation.IsTrue(searchCheckformtoClickCChartNew, 
				"Opened Form" + checkFormNameOverviewChart38140, 
				"Unable to open the Form" + checkFormNameOverviewChart38140); 

		boolean associateCNewchart = fm.createAssociateChart(CChartNew101);
		TestValidation.IsTrue(associateCNewchart, 
				"C Chart Associated", 
				"Failed to Associate C Chart");

		boolean fieldTabCchart = fm.selectFieldTabforCharts();
		TestValidation.IsTrue(fieldTabCchart, 
				"Field tab is selected", 
				"Failed to select Field Tab");



		boolean selectfieldValueCchart= fm.clickFieldPopupforMinimum1Field();
		TestValidation.IsTrue(selectfieldValueCchart,
				"C Chart Field Value checked and saved", 
				"Failed to select and checked C Chart Field Value");
		
		boolean clickClearButtonValidationCChart= fm.fieldClickClearButton();
		TestValidation.IsTrue(clickClearButtonValidationCChart,
				"C Chart page get Clear closed", 
				"Failed to clear the C Chart page");
	}

	finally {
		if(!controlActions.refreshDisplayedPage()){
			TCGFailureMsg = "Could NOT refresh application's page";
			TestValidation.Fail(TCGFailureMsg);
		}
	}



	}


	@Test(description="Test Case 38137: Forms Manager || Charts - Fields || Verify field values supported for SumChart chart type")
	public void TestCase_38137() {

		try {
		hp.clickFSQABrowserMenu();
		checkFormNameOverviewChart38137 = CommonMethods.dynamicText("Automation_Check_form_FM_overview_38137");

		boolean createAndReleaseForm = fdp.createAndReleaseForm("Check", checkFormNameOverviewChart38137,"Customers", customersCategoryValue,
				customersInstanceValue); 

		TestValidation.IsTrue(createAndReleaseForm, 
				"Able to create and release form:" + checkFormNameOverviewChart38137,
				"Could NOT able to create and release forms:" +checkFormNameOverviewChart38137);


		SumChartNew101 = CommonMethods.dynamicText("SumChart101");

		hp.clickFSQABrowserMenu();
		hp.clickChartBuilderMenu();
		boolean createSumChartNewchart = cbp.addNewChart(SumChartNew101,ChartTypes.SUMCHART);
		TestValidation.IsTrue(createSumChartNewchart, 
				"SumChartNew Chart Created successfully", 
				"Failed to create SumChartNew Chart");


		fm = hp.clickFormsManagerMenu();



		fieldOption = "Numeric Field";


		boolean searchCheckformtoClickSumChartNew = fm.selectFormAndNavigaToCharts(checkFormNameOverviewChart38137);
		TestValidation.IsTrue(searchCheckformtoClickSumChartNew, 
				"Opened Form" + checkFormNameOverviewChart38137, 
				"Unable to open the Form" + checkFormNameOverviewChart38137); 

		boolean associateSumChartNewchart = fm.createAssociateChart(SumChartNew101);
		TestValidation.IsTrue(associateSumChartNewchart, 
				"SumChart Chart Associated", 
				"Failed to Associate SumChart Chart");
		
		boolean fieldTabSumChart = fm.selectFieldTabforCharts();
		TestValidation.IsTrue(fieldTabSumChart, 
				"Field tab is selected", 
				"Failed to select Field Tab");

		boolean selectfieldValueSumChart= fm.clickFieldPopupforMinimum1Field();
			TestValidation.IsTrue(selectfieldValueSumChart,
				"SumChart Chart Field Value checked and saved", 
				"Failed to select and checked SumChart Chart Field Value");
		
//		boolean clearPostValidationSumC= fm.fieldClickOkButtonCheckValidation();
//		TestValidation.IsTrue(clearPostValidationSumC,
//				"XBarR Chart Pop up get cleared and closed", 
//				"Failed to close the Pop up");
//		

		boolean clickClearButtonValidationSumChart= fm.fieldClickClearButton();
		TestValidation.IsTrue(clickClearButtonValidationSumChart,
				"Sum Chart page get Clear closed", 
				"Failed to clear the Sum Chart page");
		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				TestValidation.Fail(TCGFailureMsg);
			}
		}


	}

	@Test(description="Test Case 38141: Forms Manager || Charts - Fields || Verify field values supported for AverageChart chart type")
	public void TestCase_38141() {

		try {
		hp.clickFSQABrowserMenu();
		checkFormNameOverviewChart38141 = CommonMethods.dynamicText("Automation_Check_form_FM_overview_38141");

		boolean createAndReleaseForm = fdp.createAndReleaseForm("Check", checkFormNameOverviewChart38141,"Customers", customersCategoryValue,
				customersInstanceValue); 

		TestValidation.IsTrue(createAndReleaseForm, 
				"Able to create and release form:" + checkFormNameOverviewChart38141,
				"Could NOT able to create and release forms:" +checkFormNameOverviewChart38141);


		AverageChartNew101 = CommonMethods.dynamicText("AvgChart101");

		hp.clickFSQABrowserMenu();
		hp.clickChartBuilderMenu();
		boolean createAverageChartNewchart = cbp.addNewChart(AverageChartNew101,ChartTypes.AVERAGECHART);
		TestValidation.IsTrue(createAverageChartNewchart, 
				"AverageChartNew Chart Created successfully", 
				"Failed to create AverageChartNew Chart");


		fm = hp.clickFormsManagerMenu();



		fieldOption = "Numeric Field";


		boolean searchCheckformtoClickAverageChartNew = fm.selectFormAndNavigaToCharts(checkFormNameOverviewChart38141);
		TestValidation.IsTrue(searchCheckformtoClickAverageChartNew, 
				"Opened Form" + checkFormNameOverviewChart38141, 
				"Unable to open the Form" + checkFormNameOverviewChart38141); 

		boolean associateAverageChartNewchart = fm.createAssociateChart(AverageChartNew101);
		TestValidation.IsTrue(associateAverageChartNewchart, 
				"AverageChart Chart Associated", 
				"Failed to Associate AverageChart Chart");

		boolean fieldTabAverageChart = fm.selectFieldTabforCharts();
		TestValidation.IsTrue(fieldTabAverageChart, 
				"Field tab is selected", 
				"Failed to select Field Tab");



		boolean selectfieldValueAverageChart= fm.clickFieldPopupforMinimum1Field();
		TestValidation.IsTrue(selectfieldValueAverageChart,
				"AverageChart Chart Field Value checked and saved", 
				"Failed to select and checked AverageChart Chart Field Value");

		
		boolean clickClearButtonValidationAvgC= fm.fieldClickClearButton();
		TestValidation.IsTrue(clickClearButtonValidationAvgC,
				"Average Chart page get Clear closed", 
				"Failed to clear the Average Chart page");
		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				TestValidation.Fail(TCGFailureMsg);
			}
		}


	} 



	@Test(description="Test Case 38133: Forms Manager || Charts - Fields || Verify field values supported for XiMr chart type")
	public void TestCase_38143() {
		try {

		hp.clickFSQABrowserMenu();
		checkFormNameOverviewChart38143 = CommonMethods.dynamicText("Automation_Check_form_FM_overview_38143");

		boolean createAndReleaseFormTwoNumeric = fdp.createAndReleaseForm("Check", checkFormNameOverviewChart38143,"Customers", customersCategoryValue,
				customersInstanceValue); 
		TestValidation.IsTrue(createAndReleaseFormTwoNumeric, 
				"Able to create and release form:" + checkFormNameOverviewChart38143,
				"Could NOT able to create and release forms:" +checkFormNameOverviewChart38143);


		XiMrChartNew143 = CommonMethods.dynamicText("XiMr_38143");
		XBarRChartNew143 = CommonMethods.dynamicText("XBarR_38143");
		XBarSChartNew143 = CommonMethods.dynamicText("XBarS_38143");


		hp.clickFSQABrowserMenu();
		hp.clickChartBuilderMenu();
		boolean createXiMrchart = cbp.addNewChart(XiMrChartNew143,ChartTypes.XIMR);
		TestValidation.IsTrue(createXiMrchart, 
				"XiMr Chart Created successfully", 
				"Failed to create XiMr Chart");
		boolean createXBarRchart = cbp.addNewChart(XBarRChartNew143,ChartTypes.XBARR);
		TestValidation.IsTrue(createXBarRchart, 
				"XBarR Chart Created successfully", 
				"Failed to create XBarR Chart");

		boolean createXBarSchart = cbp.addNewChart(XBarSChartNew143,ChartTypes.XBARS);
		TestValidation.IsTrue(createXBarSchart, 
				"XBarS Chart Created successfully", 
				"Failed to create XBarS Chart");



		fm = hp.clickFormsManagerMenu();

		fieldOption = "Numeric Field";





		boolean searchCheckformtoClickChart = fm.selectFormAndNavigaToCharts(checkFormNameOverviewChart38143);
		TestValidation.IsTrue(searchCheckformtoClickChart, 
				"Opened Form" + checkFormNameOverviewChart38143, 
				"Unable to open the Form" + checkFormNameOverviewChart38143); 

		boolean associateXiMrchart = fm.createAssociateChart(XiMrChartNew143);
		TestValidation.IsTrue(associateXiMrchart, 
				"XiMr Chart Associated", 
				"Failed to Associate XiMrChart");

		boolean resourceselect = fm.selectResourceTabforCharts();
		TestValidation.IsTrue(resourceselect, 
				"Resource tab to charts", 
				"Failed to click resource tab for charts");

		boolean associateXBarRchartNew = fm.createAssociateChart(XBarRChartNew143);
		TestValidation.IsTrue(associateXBarRchartNew, 
				"XBarR Chart Associated", 
				"Failed to Associate XBarR chart");
		boolean resourceselectXBarR = fm.selectResourceTabforCharts();
		TestValidation.IsTrue(resourceselectXBarR, 
				"Resource tab to charts", 
				"Failed to click resource tab for charts");

		boolean associateXBarSchartNew = fm.createAssociateChart(XBarSChartNew143);
		TestValidation.IsTrue(associateXBarSchartNew, 
				"XBarS Chart Associated", 
				"Failed to Associate XBarS chart");

		boolean resourceselectXBarS = fm.selectResourceTabforCharts();
		TestValidation.IsTrue(resourceselectXBarS, 
				"Resource tab to charts", 
				"Failed to click resource tab for charts");
		
		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				TestValidation.Fail(TCGFailureMsg);
			}
		}


	} 

	@Test(description="Test Case 38144: Forms Manager || charts - Resources || Verify Mean & Sample Size column for NpChart UChart & PChart chart type")
	public void TestCase_38144() {
		
		try {
		hp.clickFSQABrowserMenu();
		checkFormNameOverviewChart38144 = CommonMethods.dynamicText("Automation_Check_form_FM_overview_38144");

		boolean createAndReleaseFormTwoNumeric = fdp.createAndReleaseForm("Check", checkFormNameOverviewChart38144,"Customers", customersCategoryValue,
				customersInstanceValue); 
		TestValidation.IsTrue(createAndReleaseFormTwoNumeric, 
				"Able to create and release form:" + checkFormNameOverviewChart38144,
				"Could NOT able to create and release forms:" +checkFormNameOverviewChart38144);


		NpChartNew144 = CommonMethods.dynamicText("NpChart_38144");
		UChartNew144 = CommonMethods.dynamicText("UChart_38144");
		PChartNew144 = CommonMethods.dynamicText("PChart_38144");


		hp.clickFSQABrowserMenu();
		hp.clickChartBuilderMenu();
		boolean createXiMrchart = cbp.addNewChart(NpChartNew144,ChartTypes.NPCHART);
		TestValidation.IsTrue(createXiMrchart, 
				"XiMr Chart Created successfully", 
				"Failed to create XiMr Chart");
		boolean createUChart = cbp.addNewChart(UChartNew144,ChartTypes.UCHART);
		TestValidation.IsTrue(createUChart, 
				"U Chart Created successfully", 
				"Failed to create U Chart");

		boolean createPchart = cbp.addNewChart(PChartNew144,ChartTypes.PCHART);
		TestValidation.IsTrue(createPchart, 
				"P Chart Created successfully", 
				"Failed to create P Chart");



		fm = hp.clickFormsManagerMenu();

		fieldOption = "Numeric Field";





		boolean searchCheckformtoClickChart = fm.selectFormAndNavigaToCharts(checkFormNameOverviewChart38144);
		TestValidation.IsTrue(searchCheckformtoClickChart, 
				"Opened Form" + checkFormNameOverviewChart38144, 
				"Unable to open the Form" + checkFormNameOverviewChart38144); 

		boolean associateNpchart = fm.createAssociateChart(NpChartNew144);
		TestValidation.IsTrue(associateNpchart, 
				"P Chart Associated", 
				"Failed to Associate P Chart");

		boolean resourceselect = fm.selectResourceTabforCharts();
		TestValidation.IsTrue(resourceselect, 
				"Resource tab to charts", 
				"Failed to click resource tab for charts");

		boolean associatePchart = fm.createAssociateChart(PChartNew144);
		TestValidation.IsTrue(associatePchart, 
				"P Chart Associated", 
				"Failed to Associate P chart");
		boolean resourceselectNp = fm.selectResourceTabforCharts();
		TestValidation.IsTrue(resourceselectNp, 
				"Resource tab to charts", 
				"Failed to click resource tab for charts");

		boolean associateUchartNew = fm.createAssociateChart(UChartNew144);
		TestValidation.IsTrue(associateUchartNew, 
				"UChart is get Associated", 
				"Failed to Associate UChart chart");

		boolean resourceselectP = fm.selectResourceTabforCharts();
		TestValidation.IsTrue(resourceselectP, 
				"Resource tab to charts", 
				"Failed to click resource tab for charts");
		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				TestValidation.Fail(TCGFailureMsg);
			}
		}

	} 


	@Test(description="Test Case 38268: Forms Manager || charts - Resources || Verify Mean & Sample Size column for NpChart UChart & PChart chart type")
	public void TestCase_38268() {
		
		try {

		hp.clickFSQABrowserMenu();
		SumChartNew38268 = CommonMethods.dynamicText("SumChart_38268");


		hp.clickFSQABrowserMenu();
		hp.clickChartBuilderMenu();
		boolean createSumchart = cbp.addNewChart(SumChartNew38268,ChartTypes.SUMCHART);
		TestValidation.IsTrue(createSumchart, 
				"Sum Chart Created successfully", 
				"Failed to create Sum Chart");


		fm = hp.clickFormsManagerMenu();

		fieldOption = "Numeric Field";



		boolean searchCheckformtoClickChart268= fm.selectFormAndNavigaToCharts(checkFormNameOverviewChart38268);
		TestValidation.IsTrue(searchCheckformtoClickChart268, 
				"Opened Form" + checkFormNameOverviewChart38268, 
				"Unable to open the Form" + checkFormNameOverviewChart38268); 

		boolean associateSumchart = fm.createAssociateChart(SumChartNew38268);
		TestValidation.IsTrue(associateSumchart, 
				"SUM Chart Associated", 
				"Failed to Associate SUM Chart");

		boolean fieldTabchart = fm.selectFieldTabforCharts();
		TestValidation.IsTrue(fieldTabchart, 
				"Field tab is selected", 
				"Failed to select Field Tab");
		}
		
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				TestValidation.Fail(TCGFailureMsg);
			}
			
		}

	}

	@Test(description="Forms Manager || Charts - Fields || Verify the removed field(from form) in 'Fields' sub tab")
	public void TestCase_38714() throws Exception{
		
		try {

		
		hp.clickFSQABrowserMenu();
		checkFormNameOverviewChart38714 = CommonMethods.dynamicText("Auto_Chk_38714");
		XiMrChartNew38714 = CommonMethods.dynamicText("XiMr38714");
		//creation of form

		HashMap<String, List<String>> fields = new HashMap<String, List<String>>();
		fields.put(FIELD_TYPES.NUMERIC, Arrays.asList(numFieldName,numFieldName2));
		//fields.put(FIELD_TYPES.NUMERIC, Arrays.asList(numFieldName2));

		FormFieldParams ffp11 = new FormFieldParams();
		ffp11.FieldDetails = fields;

		HashMap<String, List<String>> resource1 = new HashMap<String, List<String>>();
		resource1.put(FORMRESOURCETYPES.CUSTOMERS, Arrays.asList(customersCategoryValue));

		FormDesignParams fdpDets1 = new FormDesignParams();
		fdpDets1.FormType = FORMTYPES.CHECK;
		fdpDets1.FormName = checkFormNameOverviewChart38714;
		fdpDets1.SelectResources = resource1;
		fdpDets1.DesignFields = Arrays.asList(ffp11);
		//fdpDets1.ReleaseForm = true;
		
		FormDesignerPage fdp1 = hp.clickFormDesignerMenu();
		boolean createandReleaseform1 = fdp1.createAndReleaseForm(fdpDets1); 
		TestValidation.IsTrue(createandReleaseform1, 
				"Able to create th Form" + checkFormNameOverviewChart38714, 
				"Unable to create and release the Form" + checkFormNameOverviewChart38714); 


							
		//	Option = numFieldName;
		hp.clickFSQABrowserMenu();
		hp.clickChartBuilderMenu();
		boolean createXiMRchart = cbp.addNewChart(XiMrChartNew38714,ChartTypes.XIMR);
		TestValidation.IsTrue(createXiMRchart, 
				"XiMR Chart Created successfully", 
				"Failed to create XiMR Chart");

		fm = hp.clickFormsManagerMenu();

		TestValidation.Equals(fm.error, 
				false, 
				"CLICKED on Forms manager menu", 
				"Could NOT click on Forms manager menu");

		boolean searchCheckformtoClickChart = fm.selectFormAndNavigaToCharts(checkFormNameOverviewChart38714);
		TestValidation.IsTrue(searchCheckformtoClickChart, 
				"Opened Form" + checkFormNameOverviewChart38714, 
				"Unable to open the Form" + checkFormNameOverviewChart38714); 

		boolean associateNpchart = fm.createAssociateChart(XiMrChartNew38714);
		TestValidation.IsTrue(associateNpchart, 
				"XiMR Chart Associated", 
				"Failed to Associate XiMR Chart");


		boolean fieldTabchart = fm.selectFieldTabforCharts();
		TestValidation.IsTrue(fieldTabchart, 
				"Field tab is selected", 
				"Failed to select Field Tab");

		boolean selectfieldValuechart = fm.selectFieldValue(numFieldName);
		TestValidation.IsTrue(selectfieldValuechart, 
				"XiMr Chart Field Value selected and saved", 
				"Failed to select and saved XiMr Chart Field Value ");

		hp.clickFSQABrowserMenu(); 

		boolean navigateToForms2 = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.CUSTOMERS,FSQATAB.FORMS); 
		TestValidation.IsTrue(navigateToForms2, 
				"For customer category, NAVIGATED to FSQABrowser > Forms tab", 
				"Failed to navigate to FSQABrowser > Forms tab");


		boolean applyfilter = fbp.openAndApplySettingsForColumn(COLUMNHEADER.FORMNAME, COLUMNSETTING.FILTER,checkFormNameOverviewChart38714);
		TestValidation.IsTrue(applyfilter, 
				"Applied Filter on" + COLUMNHEADER.FORMNAME, 
				"Failed to apply filter on" + COLUMNHEADER.FORMNAME);


		boolean editForm = fbp.editSelectForm(checkFormNameOverviewChart38714);
		TestValidation.IsTrue(editForm, 
				"Form redirected to Edit form screen", 
				"Failed to redirect edit form screen");

		boolean deleteField = fdp.deleteField(numFieldName2);
		TestValidation.IsTrue(deleteField, 
				"Deleted Numeric Field Successfully'", 
				"Failed to Delete Numeric field");	

		boolean saveForm = fdp.clickSaveButton();
		TestValidation.IsTrue(saveForm, 
				"Saved form Successfully", 
				"Could NOT Save Form");



		boolean nextToReleaseForm = fdp.clickOnNextButton(fdp.ReleaseFormPg,"Release Form");
		TestValidation.IsTrue(nextToReleaseForm, 
				"NAVIGATED to 'Release Form'", 
				"Could NOT Navigate to 'Release Form'");

		boolean releaseForm = fdp.releaseForm(checkFormNameOverviewChart38714);
		TestValidation.IsTrue(releaseForm, 
				"RELEASED form - "+checkFormNameOverviewChart38714, 
				"Could NOT release form'");

		//controlActions.sendKeys(fdp.FormNameTxt, checkFormNameOverviewChart38714);

		
		fm = hp.clickFormsManagerMenu();

		TestValidation.Equals(fm.error, 
				false, 
				"CLICKED on Forms manager menu", 
				"Could NOT click on Forms manager menu");
		boolean searchCheckformtoClickChart1 = fm.selectFormAndNavigaToCharts(checkFormNameOverviewChart38714);
		TestValidation.IsTrue(searchCheckformtoClickChart1, 
				"Opened Form" + checkFormNameOverviewChart38714, 
				"Unable to open the Form" + checkFormNameOverviewChart38714); 


		boolean fieldTabchart1 = fm.selectFieldTabforCharts();
		TestValidation.IsTrue(fieldTabchart1, 
				"Field tab is selected", 
				"Failed to select Field Tab");


		boolean closeFieldTab = fm.fieldTabClose();
		TestValidation.IsTrue(closeFieldTab, 
				"Field Tab closed", 
				"Failed toclose field tab");
		}
		
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				TestValidation.Fail(TCGFailureMsg);
			}
			
		}
	}



	@AfterClass(alwaysRun = true) 
	public void closeBrowser() throws InterruptedException { 
		driver.close(); 
	}

}
