package com.project.safetychain.webapp.testcases;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.project.safetychain.webapp.pageobjects.*;
import com.project.safetychain.webapp.pageobjects.CommonPage.TIMEZONE;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.COLUMNHEADER;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.COLUMNSETTING;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.FSQARESOURCE;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.FSQATAB;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FIELD_TYPES;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FORMRESOURCETYPES;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FORMTYPES;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FORM_NEXT_PAGE;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FormDesignParams;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FormFieldParams;
import com.project.safetychain.webapp.pageobjects.FormsManagerChartsPage.ChartTypes;
import com.project.safetychain.webapp.pageobjects.ManageLocationPage.LocationInstanceParams;
import com.project.safetychain.webapp.pageobjects.ManageResourcePage.RESOURCETYPES;
import com.project.safetychain.webapp.pageobjects.ResourceDesignerPage.CATEGORYTYPES;
import com.project.safetychain.webapp.pageobjects.RolesManagerPage.COLUMN_HEADER;
import com.project.safetychain.webapp.pageobjects.RolesManagerPage.ROLE_ACTION;
import com.project.safetychain.webapp.pageobjects.RolesManagerPage.ROLE_PERMISSION;
import com.project.safetychain.webapp.pageobjects.UserManagerPage.UsrDetailParams;
import com.project.safetychain.webapp.pageobjects.VerificationsPage.VerificationDetsParams;
import com.project.safetychain.webapp.testcases.support.FormOperations;
import com.project.safetychain.webapp.testcases.support.FormOperations.FormDetails;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.safetychain.webapp.pageobjects.FormsManagerChartsPage;


import com.project.utilities.CommonMethods;
import com.project.utilities.ControlActions;




public class TCG_REG_FormManager_Charts_Resources extends TestBase {
	String av = null;
	ControlActions controlActions;
	FSQABrowserPage fbp;
	CommonPage mainPage;
	FSQABrowserFormsPage fbForms;
	ManageResourcePage mrp ;
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
	ManageRequirementPage mrpp;
	ManageLocationPage mlp;
	ChartBuilderPage cbp;
	FormsManagerChartsPage fmcp;
	RolesManagerPage rmp;
	ManageResourcePage mrp1;
	ManageResourcePage mrp2;
	ManageResourcePage mrp3;

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
	public static String customersCategoryValue2;
	public static String disableCustomersInstanceValue;
	public static String customersCategoryValue41198;
	public static String deleteCustInstValue;
	public static String appendNewCustInstValue;
	public static String customersCategoryValueAppend;

	public static String resourceList;
	public static String resourceList1;
	public static String customersInstanceValue;
	public static String customersInstanceValue41198;
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
	public static String numFieldName1;
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
	public static String checkFormNameOverviewChart38713;
	public static String checkFormNameOverviewChart38714;
	public static String checkFormNameOverviewChart41198;
	public static String checkFormNameOverviewChart41199;
	public static String checkFormNameOverviewChart41200;
	public static String checkFormNameOverviewChart37672;
	public static String ChkForm37670;
	public static String ChkForm48415;
	
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
	public static String XiMrChartNew41199;
	public static String XiMrChartNew41200;
	public static String XiMrChartNew37672;
	public static String Option;
	
	public static String NpChart38670;
	public static String NpChart48415;
	
	public static String roleName;
	//New Add start
	public static String userUN;
	public static String userFN;
	public static String userLN;
	public static List<String> userLocation;
	public static List<String> userRole;
	public static String username, firstName, lastName;
	//New Add Ends
	
	@BeforeClass(alwaysRun = true)
	public void groupInit() throws InterruptedException {
		formName = CommonMethods.dynamicText("Chk");
		changeResourceCmmt = CommonMethods.dynamicText("ChangeResCmmt");
		locationCategoryValue = CommonMethods.dynamicText("Loc_Cat");
		locationInstanceValue = CommonMethods.dynamicText("AutoLocation_Inst");
		customersCategoryValue = CommonMethods.dynamicText("Cust_Cat");
		customersInstanceValue = CommonMethods.dynamicText("Cust_Inst");
		customersInstance2Value = CommonMethods.dynamicText("Cust_Inst2");
		customersInstanceValue41198 = CommonMethods.dynamicText("Cust_Inst_41198");
		customersCategoryValueAppend = CommonMethods.dynamicText("Cust_Cat_Append");
		
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
		numFieldName1 = CommonMethods.dynamicText("Numeric Field");
		roleName = CommonMethods.dynamicText("Auto_Role");//To comment



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
		customersCategoryValue2= CommonMethods.dynamicText("Customer_Inst222"); 
		disableCustomersInstanceValue = CommonMethods.dynamicText("Disable_Customer_Inst");
		deleteCustInstValue = CommonMethods.dynamicText("Deleted_Cust_inst");
		appendNewCustInstValue  = CommonMethods.dynamicText("Append_Cust_inst");
		



		checkRegFormName = CommonMethods.dynamicText("Auto_CheckForm_REG_Form_A");
		checkformNameFilter = CommonMethods.dynamicText("Auto_FormManager_FM_checkform_filters");

		//checkFormName1 = = CommonMethods.dynamicText("Auto_CheckForm_FormMGR_REG");
		checkFormNamefmFormViewComment = CommonMethods.dynamicText("Auto_FormManager_PD_Chk_checkFormNamefmFormViewComment");
		checkFormNameFMSorting = CommonMethods.dynamicText("Auto_FormManager_PD_checkform_sorting");
		//checkdisableFormNameVerification = CommonMethods.dynamicText("Auto_FormManager_PD_checkform_DisableVerification");
//		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/DD/YYYY");
//		LocalDateTime now = LocalDateTime.now();
//		String date = dtf.format(now);
//		System.out.println(date);
//		
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
		mrpp = new ManageRequirementPage(driver);
		fm = new FormsManagerPage(driver);
		mlp = new ManageLocationPage(driver);
		mrp = new ManageResourcePage(driver);

		fbForms = new FSQABrowserFormsPage(driver);
		vdp = new VerificationDetsParams();
		cbp = new ChartBuilderPage(driver);
		fmcp = new FormsManagerChartsPage(driver);
		
		//New Add start
		userUN = CommonMethods.dynamicText("UN");
		userFN = CommonMethods.dynamicText("FN");
		userLN = CommonMethods.dynamicText("LN");
		userLocation = new ArrayList<String>();
		userRole = new ArrayList<String>();
		//New Add ends
		
		
		
		hp = lp.performLogin(adminUsername, adminPassword);
//------------------To uncomment===================================
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
		
		String customerInstances[][]= {{disableCustomersInstanceValue,"true"}};
		String customerInstances2[][]= {{deleteCustInstValue,"true"}};
		//String customerInstances3[][]= {{appendNewCustInstValue,"true"}};
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
		
		
		ManageResourcePage mrp4 = new ManageResourcePage(driver);
		if(!mrp4.createResourceLinkLocation("Customers", customersCategoryValueAppend, 
				appendNewCustInstValue,locationInstanceValue)) {
			TCGFailureMsg = "Could NOT create resource " + appendNewCustInstValue + " & link Location - "+ locationInstanceValue;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
		//To uncomment
		ManageResourcePage mrp1 = new ManageResourcePage(driver);
		if(!mrp1.addResourceInstances("Customers", customersCategoryValue, customerInstances,true,locationInstanceValue)) {
			TCGFailureMsg = "Could NOT create resource " + disableCustomersInstanceValue + " & link Location - "+ disableCustomersInstanceValue;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
		
		ManageResourcePage mrp2 = new ManageResourcePage(driver);
		if(!mrp2.addResourceInstances("Customers", customersCategoryValue, customerInstances2,true,locationInstanceValue)) {
			TCGFailureMsg = "Could NOT create resource " + deleteCustInstValue  + " & link Location - "+ deleteCustInstValue;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

	}




		@Test(description="Forms Manager || Charts - Fields || Verify the removed field(from form) in 'Fields' sub tab")
			public void TestCase_41198() throws Exception{
			
			try {
			
			int mean=2;
			int variance=2;	
			
			//FormDesignParams fdpDets = new FormDesignParams();
			hp.clickFSQABrowserMenu();
			checkFormNameOverviewChart41198 = CommonMethods.dynamicText("Automation_Check_form_FM_overview_41198");
			XiMrChartNew41198 = CommonMethods.dynamicText("XiMr41198");
			//creation of form
			
			HashMap<String, List<String>> fields = new HashMap<String, List<String>>();
			fields.put(FIELD_TYPES.NUMERIC, Arrays.asList(numFieldName));
			//fields.put(FIELD_TYPES.NUMERIC, Arrays.asList(numFieldName2));

			FormFieldParams ffp11 = new FormFieldParams();
			ffp11.FieldDetails = fields;

			HashMap<String, List<String>> resource1 = new HashMap<String, List<String>>();
			resource1.put(FORMRESOURCETYPES.CUSTOMERS, Arrays.asList(customersCategoryValue));
			//resource2.put(FORMRESOURCETYPES.CUSTOMERS, Arrays.asList(customersCategoryValue2));


			FormDesignParams fdpDets1 = new FormDesignParams();
			fdpDets1.FormType = FORMTYPES.CHECK;
			fdpDets1.FormName = checkFormNameOverviewChart41198;
			fdpDets1.SelectResources = resource1;
			fdpDets1.DesignFields = Arrays.asList(ffp11);
			//fdpDets1.ReleaseForm = true;

			FormDesignerPage fdp1 = hp.clickFormDesignerMenu();
			boolean createandReleaseform1 = fdp1.createAndReleaseForm(fdpDets1); 
			TestValidation.IsTrue(createandReleaseform1, 
					"Able to create the Form" + checkFormNameOverviewChart41198, 
					"Unable to create and release the Form" + checkFormNameOverviewChart41198); 
			
			
			Option = numFieldName;
			hp.clickFSQABrowserMenu();
			hp.clickChartBuilderMenu();
			boolean createXiMRchart = cbp.addNewChart(XiMrChartNew41198,ChartTypes.XIMR);
			TestValidation.IsTrue(createXiMRchart, 
					"XiMR Chart Created successfully", 
					"Failed to create XiMR Chart");
			
			fm = hp.clickFormsManagerMenu();
			
			TestValidation.Equals(fm.error, 
					false, 
					"CLICKED on Forms manager menu", 
					"Could NOT click on Forms manager menu");

			boolean searchCheckformtoClickChart = fm.selectFormAndNavigaToCharts(checkFormNameOverviewChart41198);
			TestValidation.IsTrue(searchCheckformtoClickChart, 
					"Opened Form" + checkFormNameOverviewChart41198, 
					"Unable to open the Form" + checkFormNameOverviewChart41198); 
			
			boolean associateXiMrchart = fm.createAssociateChart(XiMrChartNew41198); 
			TestValidation.IsTrue(associateXiMrchart, 
					"XiMR Chart Associated", 
					"Failed to Associate XiMR Chart");
			
			
			boolean resourceselect41198 = fm.selectResourceTabforResourcesCharts();
			TestValidation.IsTrue(resourceselect41198, 
					"Resource tab to charts", 
					"Failed to click resource tab for charts");
		
			boolean incrementMeanFieldValueChart = fm.increamentMean(mean, disableCustomersInstanceValue);
			TestValidation.IsTrue(incrementMeanFieldValueChart, 
					"XiMr Chart Mean Value selected and saved", 
					"Failed to set Mean and saved XiMr Chart Field Value ");
			
			boolean incrementVarianceFieldValueChart = fm.increamentVariance(variance, disableCustomersInstanceValue);
			TestValidation.IsTrue(incrementVarianceFieldValueChart, 
					"XiMr Chart Variance Value selected and saved", 
					"Failed to set Variance and saved XiMr Chart Field Value ");
			
			
			// Go to Admin tools
			 
			 hp.clickFSQABrowserMenu();
			// hp.clickAdminToolsMenu();
			hp.clickResourcesMenu();
			
			boolean selectfieldValuechart1 = mrp.searchAndDisableResource(RESOURCETYPES.CUSTOMERS,disableCustomersInstanceValue);
			TestValidation.IsTrue(selectfieldValuechart1, 
					"The resource" + disableCustomersInstanceValue +"get Disabled", 
					"Failed to Disable the resource " + disableCustomersInstanceValue );
			
			 hp.clickFSQABrowserMenu();
			 
			 hp.clickFormsManagerMenu();
				
				boolean searchCheckformtoClickChart1 = fm.selectFormAndNavigaToCharts(checkFormNameOverviewChart41198);
				TestValidation.IsTrue(searchCheckformtoClickChart1, 
						"Opened Form" + checkFormNameOverviewChart41198, 
						"Unable to open the Form" + checkFormNameOverviewChart41198); 
				
				boolean resourceselect = fm.selectResourceTabforResourcesCharts();
				TestValidation.IsTrue(resourceselect, 
						"Resource tab to charts", 
						"Failed to click resource tab for charts");
				
				boolean incrementMeanFieldValueChart1 = fm.increamentMean(mean, customersInstanceValue);
				TestValidation.IsTrue(incrementMeanFieldValueChart1, 
						"XiMr Chart Mean Value selected and saved", 
						"Failed to set Mean and saved XiMr Chart Field Value ");
				
				boolean incrementVarianceFieldValueChart1 = fm.increamentVariance(variance, customersInstanceValue);
				TestValidation.IsTrue(incrementVarianceFieldValueChart1, 
						"XiMr Chart Variance Value selected and saved", 
						"Failed to set Variance and saved XiMr Chart Field Value ");
				
				boolean closebutton = fm.clickResourceTabFormClose();
				TestValidation.IsTrue(closebutton, 
						"Form get close", 
						"Failed to close");
			}
		
		finally {
			if (!controlActions.refreshDisplayedPage()) {
				TCGFailureMsg = "Could NOT refresh application's page";
				TestValidation.Fail(TCGFailureMsg);
				}
			
		       }
		}
			
				
		 
		
	
		@Test(description="Forms Manager - Charts || Verify Charts Access Subsystem action permission for Admin Tools > Form Manager")
		public void TestCase_38713() throws Exception{
		try {
			
			RolesManagerPage rmp = hp.clickRolesMenu();
			if(!rmp.createRole(roleName)) {
				TCGFailureMsg = "Could not create role - " + roleName;
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}
			
			userLocation.add("ALL");
			userRole.add(roleName);
			UserManagerPage ump = hp.clickUsersMenu();

			UsrDetailParams udp = new UsrDetailParams();
			udp.Username = userUN;
			udp.Password = GenericPassword;
			udp.FirstName = userFN;
			udp.LastName = userLN;
			udp.Email = "test@test.com";
			udp.Timezone = TIMEZONE.REPUBLICOFINDIA;
			udp.Locations = userLocation;
			udp.Roles = userRole;
			boolean userCreation = ump.createInternalUser(udp);
			if(!userCreation) {
				TCGFailureMsg = "Could NOT create user - " + userUN;
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}

			if(!lp.loginAfterUserCreation(userUN, GenericPassword, GenericNewPassword)) {
				TCGFailureMsg = "Could NOT login with user - " + userUN;
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}	
		
		hp.clickFSQABrowserMenu();
		rmp= new RolesManagerPage(driver);
		checkFormNameOverviewChart38713 = CommonMethods.dynamicText("Automation_Check_form_FM_overview38713");
		
		//FORM - Creation and Release
		HashMap<String, List<String>> fields = new HashMap<String, List<String>>();
		fields.put(FIELD_TYPES.NUMERIC, Arrays.asList(numFieldName));
		//fields.put(FIELD_TYPES.NUMERIC, Arrays.asList(numFieldName2));

		FormFieldParams ffp = new FormFieldParams();
		ffp.FieldDetails = fields;

		HashMap<String, List<String>> resource = new HashMap<String, List<String>>();
		resource.put(FORMRESOURCETYPES.CUSTOMERS, Arrays.asList(customersCategoryValue));

		FormDesignParams fdpDets = new FormDesignParams();
		fdpDets.FormType = FORMTYPES.CHECK;
		fdpDets.FormName = checkFormNameOverviewChart38713;
		fdpDets.SelectResources = resource;
		fdpDets.DesignFields = Arrays.asList(ffp);
		//fdpDets.ReleaseForm = true;

		FormDesignerPage fdp = hp.clickFormDesignerMenu();
		if(!fdp.createAndReleaseForm(fdpDets)) {
			TCGFailureMsg = "Could NOT create unreleased form - " + checkFormNameOverviewChart38713;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
		
		hp.clickFSQABrowserMenu();
		rmp = hp.clickRolesMenu();  
		//roleName = "SuperAdmin";
		TestValidation.Equals(rmp.error, 
				false, 
				"CLICKED on Roles menu", 
				"Could NOT click on Roles menu"); 
		
		boolean  AccessChartSystem=rmp.searchAndSetRoleForModule(roleName, "Admin Tools > Form Manager", COLUMN_HEADER.ACTION,
				ROLE_ACTION.CHARTS_ACCESS_SUBSYSTEM, ROLE_PERMISSION.NO);
		TestValidation.IsTrue(AccessChartSystem, 
				"Chart access is set to NO", 
				"Failed to set Chart Access to NO");

		boolean logout = hp.userLogout();
		TestValidation.IsTrue(logout, 
				"Log out from application", 
				"Failed to Log out from application");
		
		hp = lp.performLogin(userUN, GenericNewPassword);
		TestValidation.IsTrue(!hp.error, 
				"LOGGED IN with Admin user " + userUN, 
				"Failed to login with Admin user " + userUN);


		hp.clickFormsManagerMenu();
		
		boolean searchCheckformtoClickChart = fm.selectFormAndToCheckCharts(checkFormNameOverviewChart38713);
		TestValidation.IsTrue(searchCheckformtoClickChart, 
				"Opened Form" + checkFormNameOverviewChart38713 +"Chart Tab is not visible", 
				"Unable to open the Form" + checkFormNameOverviewChart38713); 
		//to close
		
		boolean closebutton = fm.clickOverviewFormClose();
		TestValidation.IsTrue(closebutton, 
				"Form get close", 
				"Failed to close");
		

		//Permission to set YES
		hp.clickFSQABrowserMenu();
		rmp = hp.clickRolesMenu();
		//hp.clickResourcesMenu();
		TestValidation.Equals(rmp.error, 
				false, 
				"CLICKED on Roles menu", 
				"Could NOT click on Roles menu"); 
		
		boolean  AccessChartSystemYes=rmp.searchAndSetRoleForModule(roleName, "Admin Tools > Form Manager", COLUMN_HEADER.ACTION,
				ROLE_ACTION.CHARTS_ACCESS_SUBSYSTEM, ROLE_PERMISSION.YES);
		TestValidation.IsTrue(AccessChartSystemYes, 
				"Chart access is set to YES", 
				"Failed to set Chart Access to YES");

		boolean logout1 = hp.userLogout();
		TestValidation.IsTrue(logout1, 
				"Log out from application", 
				"Failed to Log out from application");

		hp = lp.performLogin(userUN, GenericNewPassword);
		TestValidation.IsTrue(!hp.error, 
				"LOGGED IN with Admin user " + userUN, 
				"Failed to login with Admin user " + userUN);


		hp.clickFormsManagerMenu();
		
		boolean searchCheckformtoClickChart1 = fm.selectFormAndToCheckCharts(checkFormNameOverviewChart38713);
		TestValidation.IsTrue(searchCheckformtoClickChart1, 
				"Opened Form" + checkFormNameOverviewChart38713 +"Chart Tab is  visible", 
				"Unable to open the Form" + checkFormNameOverviewChart38713); 

		boolean closeFormbutton = fm.clickOverviewFormClose();
		TestValidation.IsTrue(closeFormbutton, 
				"Form get close", 
				"Failed to close");
		}
		
		finally {
			if (!controlActions.refreshDisplayedPage()) {
				TCGFailureMsg = "Could NOT refresh application's page";
				TestValidation.Fail(TCGFailureMsg);
				}
			
		       }
		
	}
	
	

	@Test(description="Forms Manager - Charts - Resources tab || Verify user should be able to perform save operation in resource grid after removing the resource from form that was already present in the resource grid")
		public void TestCase_41199() throws Exception{
		
		try {
		
		int mean=2;
		int variance=2;	
		
		//FormDesignParams fdpDets = new FormDesignParams();
		hp.clickFSQABrowserMenu();
		checkFormNameOverviewChart41199 = CommonMethods.dynamicText("Auto_Chk_form_FM_41199");
		XiMrChartNew41199 = CommonMethods.dynamicText("XiMr41199");
		
		//creation of form
		
		
		HashMap<String, List<String>> fields = new HashMap<String, List<String>>();
		fields.put(FIELD_TYPES.NUMERIC, Arrays.asList(numFieldName));
		//fields.put(FIELD_TYPES.NUMERIC, Arrays.asList(numFieldName2));

		FormFieldParams ffp11 = new FormFieldParams();
		ffp11.FieldDetails = fields;

		HashMap<String, List<String>> resource1 = new HashMap<String, List<String>>();
		resource1.put(FORMRESOURCETYPES.CUSTOMERS, Arrays.asList(customersCategoryValue));
		

		FormDesignParams fdpDets1 = new FormDesignParams();
		fdpDets1.FormType = FORMTYPES.CHECK;
		fdpDets1.FormName = checkFormNameOverviewChart41199;
		fdpDets1.SelectResources = resource1;
		fdpDets1.DesignFields = Arrays.asList(ffp11);
		//fdpDets1.ReleaseForm = true;

		FormDesignerPage fdp1 = hp.clickFormDesignerMenu();
		boolean createandReleaseform1 = fdp1.createAndReleaseForm(fdpDets1); 
		TestValidation.IsTrue(createandReleaseform1, 
				"Able to create the Form" + checkFormNameOverviewChart41199, 
				"Unable to create and release the Form" + checkFormNameOverviewChart41199); 
		
		
		Option = numFieldName;
		hp.clickFSQABrowserMenu();
		hp.clickChartBuilderMenu();
		boolean createXiMRchart = cbp.addNewChart(XiMrChartNew41199,ChartTypes.XIMR);
		TestValidation.IsTrue(createXiMRchart, 
				"XiMR Chart Created successfully", 
				"Failed to create XiMR Chart");
		
		fm = hp.clickFormsManagerMenu();
		
		TestValidation.Equals(fm.error, 
				false, 
				"CLICKED on Forms manager menu", 
				"Could NOT click on Forms manager menu");

		boolean searchCheckformtoClickChart = fm.selectFormAndNavigaToCharts(checkFormNameOverviewChart41199);
		TestValidation.IsTrue(searchCheckformtoClickChart, 
				"Opened Form" + checkFormNameOverviewChart41199, 
				"Unable to open the Form" + checkFormNameOverviewChart41199); 
		
	
		
		boolean associateXiMrchart41199 = fm.createAssociateChart(XiMrChartNew41199); 
		TestValidation.IsTrue(associateXiMrchart41199, 
				"XiMR Chart Associated", 
				"Failed to Associate XiMR Chart");
		
		
		boolean resourceselect41199 = fm.selectResourceTabforResourcesCharts();
		TestValidation.IsTrue(resourceselect41199, 
				"Resource tab to charts", 
				"Failed to click resource tab for charts");
	
		boolean incrementMeanFieldValueChartNew = fm.increamentMean(mean, customersInstanceValue);
		TestValidation.IsTrue(incrementMeanFieldValueChartNew, 
				"XiMr Chart Mean Value selected and saved", 
				"Failed to set Mean and saved XiMr Chart Field Value ");
		
		boolean incrementVarianceFieldValueChartN1 = fm.increamentVariance(variance, customersInstanceValue);
		TestValidation.IsTrue(incrementVarianceFieldValueChartN1, 
				"XiMr Chart Variance Value selected and saved", 
				"Failed to set Variance and saved XiMr Chart Field Value ");
		
		
		// Go to Admin tools
		 
		// hp.clickFSQABrowserMenu();
		// hp.clickAdminToolsMenu();
		 FSQABrowserPage fbp = hp.clickFSQABrowserMenu();

		

		boolean navigateToforms = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.CUSTOMERS,FSQATAB.FORMS);
		TestValidation.IsTrue(navigateToforms, 
				"For customer category, NAVIGATED to FSQABrowser > Forms tab", 
				"Failed to navigate to FSQABrowser > Forms tab");
		
		boolean applyfilter = fbp.openAndApplySettingsForColumn(COLUMNHEADER.FORMNAME, COLUMNSETTING.FILTER,checkFormNameOverviewChart41199);
		TestValidation.IsTrue(applyfilter, 
				"Applied Filter on" + COLUMNHEADER.FORMNAME, 
				"Failed to apply filter on" + COLUMNHEADER.FORMNAME);


		boolean editForm = fbp.editSelectForm(checkFormNameOverviewChart41199);
		TestValidation.IsTrue(editForm, 
				"Form redirected to Edit form screen", 
				"Failed to redirect edit form screen");
		


		boolean deleteResource1 =fdp.deleteResource(deleteCustInstValue);
				TestValidation.IsTrue(deleteResource1, 
						"Able to Delete the resource", 
						"Failed to Delete the resource");
				
		//To Release the form with updated version
				
		boolean clickOnNextButton = fdp.clickOnNextButton(fdp.DesignFormPg,FORM_NEXT_PAGE.DESIGN_FORM);
				TestValidation.IsTrue(clickOnNextButton, 
									 "Clicked On Next Button successfully", 
									 "Failed to Click On Next Button");		

		boolean nextToReleaseForm = fdp.clickOnNextButton(fdp.ReleaseFormPg, FORM_NEXT_PAGE.RELEASE_FORM);
			 TestValidation.IsTrue(nextToReleaseForm, 
									  "NAVIGATED to 'Release Form'", 
									  "Failed to Navigate to 'Release Form'");
			 
		 boolean releaseForm = fdp.releaseForm(checkFormNameOverviewChart41199);
				TestValidation.IsTrue(releaseForm, 
									 "Successfully released form -" + checkFormNameOverviewChart41199, 
									 "Could Not release form -" + checkFormNameOverviewChart41199);

		
	
		
		 hp.clickFSQABrowserMenu();
		 
		 hp.clickFormsManagerMenu();
			
			boolean searchCheckformtoClickChart9 = fm.selectFormAndNavigaToCharts(checkFormNameOverviewChart41199);
			TestValidation.IsTrue(searchCheckformtoClickChart9, 
					"Opened Form" + checkFormNameOverviewChart41199, 
					"Unable to open the Form" + checkFormNameOverviewChart41199); 
			
			boolean resourceselects = fm.selectResourceTabforResourcesCharts();
			TestValidation.IsTrue(resourceselects, 
					"Resource tab to charts", 
					"Failed to click resource tab for charts");
			
			boolean incrementMeanFieldValueChart11 = fm.increamentMean(mean, customersInstanceValue);
			TestValidation.IsTrue(incrementMeanFieldValueChart11, 
					"XiMr Chart Mean Value selected and saved", 
					"Failed to set Mean and saved XiMr Chart Field Value ");
			
			boolean incrementVarianceFieldValueChart12 = fm.increamentVariance(variance, customersInstanceValue);
			TestValidation.IsTrue(incrementVarianceFieldValueChart12, 
					"XiMr Chart Variance Value selected and saved", 
					"Failed to set Variance and saved XiMr Chart Field Value ");
			
			boolean closebutton = fm.clickResourceTabFormClose();
			TestValidation.IsTrue(closebutton, 
					"Form get close", 
					"Failed to close");
		}
		
		finally {
			if (!controlActions.refreshDisplayedPage()) {
				TCGFailureMsg = "Could NOT refresh application's page";
				TestValidation.Fail(TCGFailureMsg);
				}
			
		       }
		
			
	} 
	
	
	@Test(description="Forms Manager - Charts - Resources tab || Verify Default check should get update in resource grid for newly added resource in the form.")
	public void TestCase_41200() throws Exception{
		
	try {
	
	int mean=2;
	int variance=2;	
	
	//FormDesignParams fdpDets = new FormDesignParams();
	hp.clickFSQABrowserMenu();
	checkFormNameOverviewChart41200 = CommonMethods.dynamicText("Auto_Chk_form_FM_41200");
	XiMrChartNew41200 = CommonMethods.dynamicText("XiMr_41200");
	//XiMrChartTemp41199 = CommonMethods.dynamicText("XiMrTemp99");
	//creation of form
	
	
	HashMap<String, List<String>> fields = new HashMap<String, List<String>>();
	fields.put(FIELD_TYPES.NUMERIC, Arrays.asList(numFieldName));
	//fields.put(FIELD_TYPES.NUMERIC, Arrays.asList(numFieldName2));

	FormFieldParams ffp11 = new FormFieldParams();
	ffp11.FieldDetails = fields;

	HashMap<String, List<String>> resource1 = new HashMap<String, List<String>>();
	resource1.put(FORMRESOURCETYPES.CUSTOMERS, Arrays.asList(customersCategoryValue));
	
	///NEW
	HashMap<String, List<String>> resource2 = new HashMap<String, List<String>>();
	resource2.put(FORMRESOURCETYPES.CUSTOMERS, Arrays.asList(customersCategoryValueAppend));
	

	FormDesignParams fdpDets1 = new FormDesignParams();
	fdpDets1.FormType = FORMTYPES.CHECK;
	fdpDets1.FormName = checkFormNameOverviewChart41200;
	fdpDets1.SelectResources = resource1;
	fdpDets1.DesignFields = Arrays.asList(ffp11);
	//fdpDets1.ReleaseForm = true;

	FormDesignerPage fdp1 = hp.clickFormDesignerMenu();
	boolean createandReleaseform1 = fdp1.createAndReleaseForm(fdpDets1); 
	TestValidation.IsTrue(createandReleaseform1, 
			"Able to create the Form" + checkFormNameOverviewChart41200, 
			"Unable to create and release the Form" + checkFormNameOverviewChart41200); 
	
	
	Option = numFieldName;
	hp.clickFSQABrowserMenu();
	hp.clickChartBuilderMenu();
	boolean createXiMRchart = cbp.addNewChart(XiMrChartNew41200,ChartTypes.XIMR);
	TestValidation.IsTrue(createXiMRchart, 
			"XiMR Chart Created successfully", 
			"Failed to create XiMR Chart");
	
	fm = hp.clickFormsManagerMenu();
	
	TestValidation.Equals(fm.error, 
			false, 
			"CLICKED on Forms manager menu", 
			"Could NOT click on Forms manager menu");

	boolean searchCheckformtoClickChart = fm.selectFormAndNavigaToCharts(checkFormNameOverviewChart41200);
	TestValidation.IsTrue(searchCheckformtoClickChart, 
			"Opened Form" + checkFormNameOverviewChart41200, 
			"Unable to open the Form" + checkFormNameOverviewChart41200); 
	

	
	boolean associateXiMrchart41200 = fm.createAssociateChart(XiMrChartNew41200); 
	TestValidation.IsTrue(associateXiMrchart41200, 
			"XiMR Chart Associated", 
			"Failed to Associate XiMR Chart");
	
	
	boolean resourceselect41200 = fm.selectResourceTabforResourcesCharts();
	TestValidation.IsTrue(resourceselect41200, 
			"Resource tab to charts", 
			"Failed to click resource tab for charts");

	boolean incrementDefaultMeanFieldValueChart = fmcp.increamentDefaultValMean(mean);
	TestValidation.IsTrue(incrementDefaultMeanFieldValueChart, 
			"XiMr Chart Default Mean Value selected and saved", 
			"Failed to set Default Mean and saved XiMr Chart Field Value ");
	
	boolean incrementDefaultVarianceFieldValueChart = fmcp.increamentDefaultValVariance(variance);
	TestValidation.IsTrue(incrementDefaultVarianceFieldValueChart, 
			"XiMr Chart Default Variance Value selected and saved", 
			"Failed to set Default Variance and saved XiMr Chart Field Value ");
	
	boolean defaultPopup = fmcp.clickDefaultCheckboxWithPopup();
	TestValidation.IsTrue(defaultPopup, 
			"XiMr Chart have set Default Mean and Variance Value for all resources", 
			"Failed to set Default Mean and Variance Value for all resources");
	
	
	
	// Go to Admin tools
	 
	// hp.clickFSQABrowserMenu();
	// hp.clickAdminToolsMenu();
	 FSQABrowserPage fbp = hp.clickFSQABrowserMenu();

	

	boolean navigateToforms = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.CUSTOMERS,FSQATAB.FORMS);
	TestValidation.IsTrue(navigateToforms, 
			"For customer category, NAVIGATED to FSQABrowser > Forms tab", 
			"Failed to navigate to FSQABrowser > Forms tab");
	
	boolean applyfilter = fbp.openAndApplySettingsForColumn(COLUMNHEADER.FORMNAME, COLUMNSETTING.FILTER,checkFormNameOverviewChart41200);
	TestValidation.IsTrue(applyfilter, 
			"Applied Filter on" + COLUMNHEADER.FORMNAME, 
			"Failed to apply filter on" + COLUMNHEADER.FORMNAME);


	boolean editForm = fbp.editSelectForm(checkFormNameOverviewChart41200);
	TestValidation.IsTrue(editForm, 
			"Form redirected to Edit form screen", 
			"Failed to redirect edit form screen");
	

	boolean clicktoSelectResources =fdp.clickSelectResourcesFromBreadcrumb();
	TestValidation.IsTrue(clicktoSelectResources, 
			"Able to click on the select resource", 
			"Failed to click on the select resource");
	
	//categoryChanged
	boolean selectResource = fdp.selectResource("Customers",customersCategoryValueAppend,appendNewCustInstValue);
	TestValidation.IsTrue(selectResource, 
						  "SELECTED Resource '" + customersCategoryValueAppend + " > " + appendNewCustInstValue + "' successfully", 
						  "Failed to Select resource '" + customersCategoryValueAppend + " > " + appendNewCustInstValue + "'");



	
			
	//To Release the form with updated version
			
	boolean clickOnNextButton = fdp.clickOnNextButton(fdp.DesignFormPg,FORM_NEXT_PAGE.DESIGN_FORM);
			TestValidation.IsTrue(clickOnNextButton, 
								 "Clicked On Next Button successfully", 
								 "Failed to Click On Next Button");		

	boolean nextToReleaseForm = fdp.clickOnNextButton(fdp.ReleaseFormPg, FORM_NEXT_PAGE.RELEASE_FORM);
		 TestValidation.IsTrue(nextToReleaseForm, 
								  "NAVIGATED to 'Release Form'", 
								  "Failed to Navigate to 'Release Form'");
		 
	 boolean releaseForm = fdp.releaseForm(checkFormNameOverviewChart41200);
			TestValidation.IsTrue(releaseForm, 
								 "Successfully released form -" + checkFormNameOverviewChart41200, 
								 "Could Not release form -" + checkFormNameOverviewChart41200);

	

	
	 hp.clickFSQABrowserMenu();
	 
	 hp.clickFormsManagerMenu();
		
		boolean searchCheckformtoClickChart9 = fm.selectFormAndNavigaToCharts(checkFormNameOverviewChart41200);
		TestValidation.IsTrue(searchCheckformtoClickChart9, 
				"Opened Form" + checkFormNameOverviewChart41200, 
				"Unable to open the Form" + checkFormNameOverviewChart41200); 
		
		boolean resourceselects = fm.selectResourceTabforResourcesCharts();
		TestValidation.IsTrue(resourceselects, 
				"Resource tab to charts", 
				"Failed to click resource tab for charts");
		
		boolean defaultPopupWithNewResources = fm.clickDefaultCheckboxWithPopup();
		TestValidation.IsTrue(defaultPopupWithNewResources, 
				"XiMr Chart have set Default Mean and Variance Value for all resources", 
				"Failed to set Default Mean and Variance Value for all resources");
	
		boolean closebutton = fm.clickResourceTabFormClose();
		TestValidation.IsTrue(closebutton, 
				"Form get close", 
				"Failed to close");
	}
	
	finally {
		if (!controlActions.refreshDisplayedPage()) {
			TCGFailureMsg = "Could NOT refresh application's page";
			TestValidation.Fail(TCGFailureMsg);
			}
		
	       }
	
		
} 

	
	@Test(description="Forms Manager - Charts || Verify user should be able to add default value in Mean,Variation & Comment column")
			public void TestCase_37672() throws Exception{
		
			try {
			
			int mean=2;
			int variance=2;	
			String defaultComment = null;
			
			//FormDesignParams fdpDets = new FormDesignParams();
			hp.clickFSQABrowserMenu();
			checkFormNameOverviewChart37672 = CommonMethods.dynamicText("Auto_Chk_form_FM_37672");
			XiMrChartNew37672 = CommonMethods.dynamicText("XiMr_37672");
			//XiMrChartTemp41199 = CommonMethods.dynamicText("XiMrTemp37672");
			//creation of form
			
			
			HashMap<String, List<String>> fields = new HashMap<String, List<String>>();
			fields.put(FIELD_TYPES.NUMERIC, Arrays.asList(numFieldName));
			//fields.put(FIELD_TYPES.NUMERIC, Arrays.asList(numFieldName2));

			FormFieldParams ffp11 = new FormFieldParams();
			ffp11.FieldDetails = fields;

			HashMap<String, List<String>> resource1 = new HashMap<String, List<String>>();
			resource1.put(FORMRESOURCETYPES.CUSTOMERS, Arrays.asList(customersCategoryValue));
			
		

			FormDesignParams fdpDets1 = new FormDesignParams();
			fdpDets1.FormType = FORMTYPES.CHECK;
			fdpDets1.FormName = checkFormNameOverviewChart37672;
			fdpDets1.SelectResources = resource1;
			fdpDets1.DesignFields = Arrays.asList(ffp11);
			

			FormDesignerPage fdp1 = hp.clickFormDesignerMenu();
			boolean createandReleaseform1 = fdp1.createAndReleaseForm(fdpDets1); 
			TestValidation.IsTrue(createandReleaseform1, 
					"Able to create the Form" + checkFormNameOverviewChart37672, 
					"Unable to create and release the Form" + checkFormNameOverviewChart37672); 
			
			
			Option = numFieldName;
			defaultComment ="auto_DefaultComment";
			hp.clickFSQABrowserMenu();
			hp.clickChartBuilderMenu();
			boolean createXiMRchart = cbp.addNewChart(XiMrChartNew37672,ChartTypes.XIMR);
			TestValidation.IsTrue(createXiMRchart, 
					"XiMR Chart Created successfully", 
					"Failed to create XiMR Chart");
			
			fm = hp.clickFormsManagerMenu();
			
			TestValidation.Equals(fm.error, 
					false, 
					"CLICKED on Forms manager menu", 
					"Could NOT click on Forms manager menu");

			boolean searchCheckformtoClickChart = fm.selectFormAndNavigaToCharts(checkFormNameOverviewChart37672);
			TestValidation.IsTrue(searchCheckformtoClickChart, 
					"Opened Form" + checkFormNameOverviewChart37672, 
					"Unable to open the Form" + checkFormNameOverviewChart37672); 
			

			
			boolean associateXiMrchart37672 = fm.createAssociateChart(XiMrChartNew37672); 
			TestValidation.IsTrue(associateXiMrchart37672, 
					"XiMR Chart Associated", 
					"Failed to Associate XiMR Chart");
			
			
			boolean resourceselect37672 = fm.selectResourceTabforResourcesCharts();
			TestValidation.IsTrue(resourceselect37672, 
					"Resource tab to charts", 
					"Failed to click resource tab for charts");

			boolean incrementDefaultMeanFieldValueChart = fmcp.increamentDefaultValMean(mean);
			TestValidation.IsTrue(incrementDefaultMeanFieldValueChart, 
					"XiMr Chart Default Mean Value selected and saved", 
					"Failed to set Default Mean and saved XiMr Chart Field Value ");
			
			boolean incrementDefaultVarianceFieldValueChart = fmcp.increamentDefaultValVariance(variance);
			TestValidation.IsTrue(incrementDefaultVarianceFieldValueChart, 
					"XiMr Chart Default Variance Value selected and saved", 
					"Failed to set Default Variance and saved XiMr Chart Field Value ");
					
			boolean addComment = fmcp.addDefaultComment(defaultComment);
			TestValidation.IsTrue(addComment, 
					"XiMr Chart Default Comment has been saved successfully", 
					"Failed to set Default Comment ");
					
			
			boolean defaultPopup = fmcp.clickDefaultCheckboxWithPopup();
			TestValidation.IsTrue(defaultPopup, 
					"XiMr Chart have set Default Mean and Variance Value for all resources", 
					"Failed to set Default Mean and Variance Value for all resources");
			
			
			boolean closebutton = fm.clickResourceTabFormClose();
			TestValidation.IsTrue(closebutton, 
					"Form get close", 
					"Failed to close");
			}
			finally {
			  if (!controlActions.refreshDisplayedPage()) {
					TCGFailureMsg = "Could NOT refresh application's page";
					TestValidation.Fail(TCGFailureMsg);
					}
				
			 }
			
				
		} 
				
	
	@Test(description = " Forms Manager - Charts || Verify Mean and Variation calculation by Date ")
	public void TestCase_37670() throws Exception {

		
		ChkForm37670 =  CommonMethods.dynamicText("Auto_Chk_37670");
		NpChart38670 = CommonMethods.dynamicText("NPC_37670");
		
		int sampleSize =2;
		
		//creation of form

				HashMap<String, List<String>> fields = new HashMap<String, List<String>>();
				//fields.put(FIELD_TYPES.NUMERIC, Arrays.asList(numFieldName1));
				fields.put(FIELD_TYPES.NUMERIC, Arrays.asList("Numeric Field"));
				
				//fields.put(FIELD_TYPES.NUMERIC, Arrays.asList(numFieldName2));

				FormFieldParams ffp11 = new FormFieldParams();
				ffp11.FieldDetails = fields;

				HashMap<String, List<String>> resource1 = new HashMap<String, List<String>>();
				resource1.put(FORMRESOURCETYPES.CUSTOMERS, Arrays.asList(customersCategoryValue));

				FormDesignParams fdpDets1 = new FormDesignParams();
				fdpDets1.FormType = FORMTYPES.CHECK;
				fdpDets1.FormName = ChkForm37670;
				fdpDets1.SelectResources = resource1;
				fdpDets1.DesignFields = Arrays.asList(ffp11);
				//fdpDets1.ReleaseForm = true;
				
				FormDesignerPage fdp1 = hp.clickFormDesignerMenu();
				boolean createandReleaseform1 = fdp1.createAndReleaseForm(fdpDets1); 
		    TestValidation.IsTrue(createandReleaseform1, 
						"Able to create th Form" + ChkForm37670, 
						"Unable to create and release the Form" + ChkForm37670); 


									
			
		hp.clickFSQABrowserMenu();
						
					
		boolean navigateToforms = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.CUSTOMERS, FSQATAB.FORMS);
			TestValidation.IsTrue(navigateToforms, "For customer category, NAVIGATED to FSQABrowser > Forms tab",
								"Failed to navigate to FSQABrowser > Forms tab");

		boolean applyFilterAndOpenForm = fbp.applyFilterAndOpenForDetails("Form Name", ChkForm37670);
			TestValidation.IsTrue(applyFilterAndOpenForm, "Verify form is displayed in Forms Tab",
								"Form is NOT displayed in Forms Tab");

		boolean submitDataTest = fbForms.submitData(false, false, false, true, false);
			TestValidation.IsTrue(submitDataTest, "Able to submit the form with data:" + ChkForm37670,
								"Could NOT able to submit the form with data:" + ChkForm37670);

		hp.clickFSQABrowserMenu();
			
			
			boolean navigateToforms1 = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.CUSTOMERS, FSQATAB.FORMS);
				TestValidation.IsTrue(navigateToforms1, "For customer category, NAVIGATED to FSQABrowser > Forms tab",
									"Failed to navigate to FSQABrowser > Forms tab");

			boolean applyFilterAndOpenForm1 = fbp.applyFilterAndOpenForDetails("Form Name", ChkForm37670);
				TestValidation.IsTrue(applyFilterAndOpenForm1, "Verify form is displayed in Forms Tab",
									"Form is NOT displayed in Forms Tab");

			boolean submitData1 = fbForms.submitData(false, false, false, true, false);
				TestValidation.IsTrue(submitData1, "Able to submit the form with data:" + ChkForm37670,
									"Could NOT able to submit the form with data:" + ChkForm37670);

			hp.clickFSQABrowserMenu();
							
			hp.clickChartBuilderMenu();
			boolean createNpChartchart = cbp.addNewChart(NpChart38670,ChartTypes.NPCHART);
				TestValidation.IsTrue(createNpChartchart, 
									"Np Chart Created successfully", 
									"Failed to create Np Chart");
	
			fm = hp.clickFormsManagerMenu();
			fieldOption = "Numeric Field";
	
			TestValidation.Equals(fm.error, 
					false, 
					"CLICKED on Forms manager menu", 
					"Could NOT click on Forms manager menu");

			boolean searchCheckformtoClickChart = fm.selectFormAndNavigaToCharts(ChkForm37670);
				TestValidation.IsTrue(searchCheckformtoClickChart, "Opened Form" + ChkForm37670,
								"Unable to open the Form" + ChkForm37670);

			boolean associateNpchart = fm.createAssociateChart(NpChart38670);
				TestValidation.IsTrue(associateNpchart, "Np Chart Associated", 
						"Failed to Associate Np Chart");
	
			boolean fieldTabchart = fm.selectFieldTabforCharts();
				TestValidation.IsTrue(fieldTabchart, "Field tab is selected", 
						"Failed to select Field Tab");
	
			boolean selectfieldValuechartNp = fm.selectFieldValue("Numeric Field");
				TestValidation.IsTrue(selectfieldValuechartNp, "Np Chart Field Value selected and saved",
									"Failed to select and saved Np Chart Field Value ");
			
			boolean resourceselect = fm.selectResourceTabforCharts();
				TestValidation.IsTrue(resourceselect, "Resource tab to charts",
								"Failed to click resource tab for charts");
				
			
			boolean incrementSampleSChart = fmcp.increamentSampleSize(sampleSize , customersInstanceValue);
				TestValidation.IsTrue(incrementSampleSChart, 
						"Np Chart Mean Value selected and saved", 
						"Failed to set Mean and saved Np Chart Field Value ");

						
			boolean calculate = fmcp.calculate(ChartTypes.NPCHART, "50");
						TestValidation.IsTrue(calculate, 
								"Sample Input provided", 
								"Failed to provide sample input");

		
	}
	
	
	@Test(description = " Forms Manager - Charts -> Resources Tab || Verify record count in Apply Historical Values if END TIME is same as record submit time.")
	public void TestCase_48415() throws Exception {

		
		ChkForm48415 =  CommonMethods.dynamicText("Auto_Chk_48415_ov_");
		NpChart48415 = CommonMethods.dynamicText("NPC_48415_");
		
		int sampleSize =2;
		
		//creation of form

				HashMap<String, List<String>> fields = new HashMap<String, List<String>>();
				//fields.put(FIELD_TYPES.NUMERIC, Arrays.asList(numFieldName1));
				fields.put(FIELD_TYPES.NUMERIC, Arrays.asList("Numeric Field"));
				
				//fields.put(FIELD_TYPES.NUMERIC, Arrays.asList(numFieldName2));

				FormFieldParams ffp11 = new FormFieldParams();
				ffp11.FieldDetails = fields;

				HashMap<String, List<String>> resource1 = new HashMap<String, List<String>>();
				resource1.put(FORMRESOURCETYPES.CUSTOMERS, Arrays.asList(customersCategoryValue));

				FormDesignParams fdpDets1 = new FormDesignParams();
				fdpDets1.FormType = FORMTYPES.CHECK;
				fdpDets1.FormName = ChkForm48415;
				fdpDets1.SelectResources = resource1;
				fdpDets1.DesignFields = Arrays.asList(ffp11);
				
				
				FormDesignerPage fdp1 = hp.clickFormDesignerMenu();
				boolean createandReleaseform1 = fdp1.createAndReleaseForm(fdpDets1); 
				TestValidation.IsTrue(createandReleaseform1, 
						"Able to create th Form" + ChkForm48415, 
						"Unable to create and release the Form" + ChkForm48415); 

	
			
		hp.clickFSQABrowserMenu();
		//hp.clickFSQABrowserMenu();			
					
		boolean navigateToforms = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.CUSTOMERS, FSQATAB.FORMS);
			TestValidation.IsTrue(navigateToforms, "For customer category, NAVIGATED to FSQABrowser > Forms tab",
								"Failed to navigate to FSQABrowser > Forms tab");

		boolean applyFilterAndOpenForm = fbp.applyFilterAndOpenForDetails("Form Name", ChkForm48415);
			TestValidation.IsTrue(applyFilterAndOpenForm, "Verify form is displayed in Forms Tab",
								"Form is NOT displayed in Forms Tab");
			
			boolean setRes= fbForms.setResourceForForm(customersInstanceValue);
			TestValidation.IsTrue(setRes, "Verify Resource is set",
					"Resource is not able to set in Forms Tab");


		boolean submitDataTest = fbForms.submitData(false, false, false, true, false);
			TestValidation.IsTrue(submitDataTest, "Able to submit the form with data:" + ChkForm48415,
								"Could NOT able to submit the form with data:" + ChkForm48415);

			hp.clickFSQABrowserMenu();
			
			
			boolean navigateToforms1 = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.CUSTOMERS, FSQATAB.FORMS);
				TestValidation.IsTrue(navigateToforms1, "For customer category, NAVIGATED to FSQABrowser > Forms tab",
									"Failed to navigate to FSQABrowser > Forms tab");

			boolean applyFilterAndOpenForm1 = fbp.applyFilterAndOpenForDetails("Form Name", ChkForm48415);
				TestValidation.IsTrue(applyFilterAndOpenForm1, "Verify form is displayed in Forms Tab",
									"Form is NOT displayed in Forms Tab");
				boolean setRes11= fbForms.setResourceForForm(customersInstanceValue);
				TestValidation.IsTrue(setRes11, "Verify Resource is set",
						"Resource is not able to set in Forms Tab");



			boolean submitData1 = fbForms.submitData(false, false, false, true, false);
				TestValidation.IsTrue(submitData1, "Able to submit the form with data:" + ChkForm48415,
									"Could NOT able to submit the form with data:" + ChkForm48415);

			hp.clickFSQABrowserMenu(); 
							
			hp.clickChartBuilderMenu();
			boolean createNpChartchart = cbp.addNewChart(NpChart48415,ChartTypes.NPCHART);
				TestValidation.IsTrue(createNpChartchart, 
									"Np Chart Created successfully", 
									"Failed to create Np Chart");
	
			fm = hp.clickFormsManagerMenu();
			fieldOption = "Numeric Field";
	
			TestValidation.Equals(fm.error, 
					false, 
					"CLICKED on Forms manager menu", 
					"Could NOT click on Forms manager menu");

			boolean searchCheckformtoClickChart = fm.selectFormAndNavigaToCharts(ChkForm48415);
				TestValidation.IsTrue(searchCheckformtoClickChart, "Opened Form" + ChkForm48415,
								"Unable to open the Form" + ChkForm48415);

			boolean associateNpchart = fm.createAssociateChart(NpChart48415);
				TestValidation.IsTrue(associateNpchart, "Np Chart Associated", 
						"Failed to Associate Np Chart");
	
			boolean fieldTabchart = fm.selectFieldTabforCharts();
				TestValidation.IsTrue(fieldTabchart, "Field tab is selected", 
						"Failed to select Field Tab");
	
			boolean selectfieldValuechartNp = fm.selectFieldValue("Numeric Field");
				TestValidation.IsTrue(selectfieldValuechartNp, "Np Chart Field Value selected and saved",
									"Failed to select and saved Np Chart Field Value ");
			
			boolean resourceselect = fm.selectResourceTabforCharts();
				TestValidation.IsTrue(resourceselect, "Resource tab to charts",
								"Failed to click resource tab for charts");
				
			
			boolean incrementSampleSChart = fmcp.increamentSampleSize(sampleSize , customersInstanceValue);
				TestValidation.IsTrue(incrementSampleSChart, 
						"Np Chart Mean Value selected and saved", 
						"Failed to set Mean and saved Np Chart Field Value ");

						
			boolean calculate = fmcp.calculateByDate(ChartTypes.NPCHART, "50",customersInstanceValue);
						TestValidation.IsTrue(calculate, 
								"Sample Input provided", 
								"Failed to provide sample input");

		
	}
		
	@AfterClass(alwaysRun = true) 
	public void closeBrowser() throws InterruptedException { 
		driver.close(); 
	}

}
