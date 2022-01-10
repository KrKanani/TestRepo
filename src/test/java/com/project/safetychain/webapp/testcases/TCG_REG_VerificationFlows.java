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
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.FSQARESOURCE;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.FSQATAB;
import com.project.safetychain.webapp.pageobjects.FSQABrowserRecordsPage.HistoryDetails;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FormDesignParams;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FormFieldParams;
import com.project.safetychain.webapp.pageobjects.ManageLocationPage.LocationInstanceParams;
import com.project.safetychain.webapp.pageobjects.RecordSignoffPage.FILTERTYPES;
import com.project.safetychain.webapp.pageobjects.ResourceDesignerPage.CATEGORYTYPES;
import com.project.safetychain.webapp.pageobjects.VerificationsPage.VerificationDetsParams;
import com.project.safetychain.webapp.testcases.support.FormOperations;
import com.project.safetychain.webapp.testcases.support.FormOperations.FormDetails;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.utilities.CommonMethods;
import com.project.utilities.ControlActions;
import com.project.utilities.DateTime;




	public class TCG_REG_VerificationFlows extends TestBase  {
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
	VerificationsPage vp1;


	// Data

	public static String formName1;
	public static String formName2;
	
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

	
	public static String checkFormNameOverviewChart38268;

	public static String checkform27871;
	public static String checkform27872;
	public static String checkform27873;
	public static String checkform28017;
	public static String checkform28019;
	public static String checkform28020;
	public static String checkform28023;
	public static String checkform28455;
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
	
	public static String verificationName;
	public static String verificationName27872;
	public static String verificationName27874;
	public static String verificationName27875;
	public static String verificationName27873;
	public static String verificationName31121;
	public static String verificationNameUpdated;
	public static String verificationComment1;
	public static String verificationComment2;
	public static String verificationComment27873_1;
	public static String verificationComment27873_2;
	public static String verificationComment28017;
	public static String verificationComment28019;
	public static String verificationComment28020;
	public static String verificationComment_NVN;
	public static String verificationName28017;
	public static String verificationName28019;
	public static String verificationName28020;
	public static String verificationName28455;
	public static String verificationComment28020_1;
	public static String verificationComment28020_2;
	
	//public static String roleName;
	public static String verificationName28023;
	public static String verificationComment28023_1;
	public static String verificationComment28023_2;
	public static String verificationComment28455_1;
	public static String verificationComment28455_2;
	public static String verificationName27911;
	
	public static String roleName = "SuperAdmin";
	public static String roleName27911 = "Role_27911";
	public static String roleNameAdd ;
	public static String newRole_27911;
	public static String signOffAllCmmt;
	public static String signOffAllCmmt28023;
	public static String signOffAllCmmt28455;


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
		verificationComment_NVN = CommonMethods.dynamicText("AutoCommt_NVN");
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
		
		roleNameAdd = CommonMethods.dynamicText("SuperAdmin_101");
		signOffAllCmmt28023 = CommonMethods.dynamicText("AllCmmt_28023");
		signOffAllCmmt28023 = CommonMethods.dynamicText("AllCmmt_28455");





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
		
		verification = hp.clickVerificationsMenu(); 
		//To check
		verification.openExistingVerification(directobservation);
		verification.setRoleForVerification(roleName);
		
		vdp.VerificationName = verificationName;
		vdp.VerificationName = verificationName27874;
		vdp.EnableVerification = true;
		vdp.AddComments = new ArrayList<String>();
		vdp.AddComments.add(verificationComment1);
		vdp.AddComments.add(verificationComment2);
		vdp.Rolename = roleName;
		verification = new VerificationsPage(driver);

		verificationName = CommonMethods.dynamicText("AutoVN");
		verificationName27874 = CommonMethods.dynamicText("AutoVN_27874");
		verificationComment1=CommonMethods.dynamicText("AutoComment1");
		verificationComment2=CommonMethods.dynamicText("AutoComment2");
		verificationNameUpdated = CommonMethods.dynamicText("AutoVN_updated");
		
		VerificationsPage vp = hp.clickVerificationsMenu();			  
		VerificationDetsParams vdp = new VerificationDetsParams();
		vdp.VerificationName = verificationName;
		vdp.EnableVerification = true;
		vdp.AddComments = new ArrayList<String>();
		vdp.AddComments.add(verificationComment1);
		vdp.AddComments.add(verificationComment2);
		vdp.Rolename = roleName;
		if (!vp.createVerification(vdp)){
			TCGFailureMsg = "Could NOT able to create verification"; 
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg); 
		}
		

		threadsleep(2000);
	//To uncomment	
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

//
//
//		//FORM - Creation and Release
//		HashMap<String, List<String>> fields = new HashMap<String, List<String>>();
//		fields.put(FIELD_TYPES.NUMERIC, Arrays.asList(numFieldName,numFieldName2));
//		//fields.put(FIELD_TYPES.NUMERIC, Arrays.asList(numFieldName2));
//
//		FormFieldParams ffp = new FormFieldParams();
//		ffp.FieldDetails = fields;
//
//		HashMap<String, List<String>> resource = new HashMap<String, List<String>>();
//		resource.put(FORMRESOURCETYPES.CUSTOMERS, Arrays.asList(customersCategoryValue));
//
//		FormDesignParams fdpDets = new FormDesignParams();
//		fdpDets.FormType = FORMTYPES.CHECK;
//		fdpDets.FormName = checkFormNameOverviewChart38268;
//		fdpDets.SelectResources = resource;
//		fdpDets.DesignFields = Arrays.asList(ffp);
//		//fdpDets.ReleaseForm = true;
//
//		FormDesignerPage fdp = hp.clickFormDesignerMenu();
//		if(!fdp.createAndReleaseForm(fdpDets)) {
//			TCGFailureMsg = "Could NOT create unreleased form - " + checkFormNameOverviewChart38268;
//			logFatal(TCGFailureMsg);
//			throw new SkipException(TCGFailureMsg);
//		}
// To uncomment stop


	}


	@Test(description="Test Case 31121: Verify Adding New Verification Type")
	public void TestCase_31121() throws Exception{
		
		verificationName31121 = CommonMethods.dynamicText("AutoVN_31121");
		verificationComment1=CommonMethods.dynamicText("AutoComment1");
		//verificationComment2=CommonMethods.dynamicText("AutoComment2");
		
		
		VerificationsPage vp = hp.clickVerificationsMenu();	
		//VerificationsPage vp1 = hp.clickVerificationsMenu();	
		VerificationDetsParams vdp = new VerificationDetsParams();
		vdp.VerificationName = verificationName31121;
		vdp.EnableVerification = true;
		vdp.AddComments = new ArrayList<String>();
		vdp.AddComments.add(verificationComment1);
		vdp.Rolename = roleName;
		
		boolean newVf= vp.createVerification(vdp);
		TestValidation.IsTrue(newVf, 
				"Able to add new verification " + verificationName31121, 
				"Failed to add new verification:" +verificationName31121); 
		
	}
	



	@Test(description="Verify user is able to add Comments to verification")
	public void TestCase_31123() throws Exception{

		boolean clickEnableCommentToggle = verification.clickEnableComment();
		TestValidation.IsTrue(clickEnableCommentToggle, 
				"Clicked on Enable Comment Toggle " + verificationName, 
				"Failed to click on Enable Comment Toggle " + verificationName); 

		List<String> commentList = new ArrayList<String>();
		commentList.add(verificationComment1);
		commentList.add(verificationComment2);
		boolean setAddComment = verification.addComment(commentList);
		TestValidation.IsTrue(setAddComment, 
				"Able to Add " + commentList.size() + " Comment", 
				"Failed to Add Comment: " + verificationComment1 + " " + verificationComment2); 

		boolean updateVerification = verification.updateVerificationButton();
		TestValidation.IsTrue(updateVerification, 
				"Able to UPDATE THE verification " + verificationName, 
				"Failed to UPDATE verification:" +verificationName); 

	}


	@Test(description="Add Role from Verification")
	public void TestCase_31124() throws Exception{
		
		

		boolean setRole = verification.searchAndSetRole(roleName);
		TestValidation.IsTrue(setRole, 
				"Able to add Role from Verification: " + roleName, 
				"Failed to add Role from Verification: " + roleName); 

		boolean updateVerification = verification.updateVerificationButton();
		TestValidation.IsTrue(updateVerification, 
				"Able to UPDATE verification " + verificationName, 
				"Failed to UPDATE verification:" +verificationName); 

		boolean roleExists = verification.verifyRoleIsSet(roleName);
		TestValidation.IsTrue(roleExists, 
				"VERIFIED Role - " + roleName + " exists", 
				"Failed to verify Role - " + roleName + " exists");

	}

	
	@Test(description=" Manage Verification || Select Verification, edit the verification name and update it & verify it in form manager also.")
	public void TestCase_27871()throws Exception{
		
		try {
		
		//hp.clickFormsManagerMenu();
		
		checkform27871 = CommonMethods.dynamicText("Auto_Chk_FM_27871");
		
		hp.clickFSQABrowserMenu();

		boolean createAndReleaseForm = fdp.createAndReleaseForm("Check", checkform27871,"Customers", customersCategoryValue,
				customersInstanceValue); 
		TestValidation.IsTrue(createAndReleaseForm, 
				"Able to create and release form:" + checkform27871,
				"Could NOT able to create and release forms:" +checkform27871);

		hp.clickFormsManagerMenu();
	
		boolean formLinkedwithVerifications = fm.linkVerificationwithForm(verificationName, checkform27871);
		if (!formLinkedwithVerifications) {
			TCGFailureMsg = "Could NOT Linked verification"
					+ fm.linkVerificationwithForm(verificationName, checkform27871);
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
					}
		hp.clickVerificationsMenu();
		
		boolean opneoldVerification = verification.openExistingVerification(verificationName);
		TestValidation.IsTrue(opneoldVerification, 
				"Able to UPDATE verification " + verificationName, 
				"Failed to UPDATE verification:" +verificationName); 
		
		
		boolean updateVerificationName = verification.updateVerificationName(verificationName,verificationNameUpdated);
		TestValidation.IsTrue(updateVerificationName, 
				"Able to UPDATE verification Name " + verificationName, 
				"Failed to UPDATE verification:" +verificationName); 
		

		
		boolean updateVerification = verification.updateVerificationButton();
		TestValidation.IsTrue(updateVerification, 
				"Able to UPDATE verification " + verificationName, 
				"Failed to UPDATE verification:" +verificationName); 
		
		hp.clickFormsManagerMenu();
		
		boolean verifyUpdateVerificationName = fm.chkUpdatedVerificationwithForm(verificationNameUpdated);
		TestValidation.IsTrue(verifyUpdateVerificationName, 
				"Able to check the " + verificationName + "Updated to" +verificationNameUpdated, 
				"Failed to UPDATE " + verificationName  + "to" + verificationNameUpdated); 
		

		}

		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				TestValidation.Fail(TCGFailureMsg);
			}
			
		}
	}
	
	@Test(description="Test Case 27875: Manage Verification || Select verification, search for role, add and remove the roles and check the update functionality")
	public void TestCase_27875() throws Exception{
		
		verificationName27875 = CommonMethods.dynamicText("AutoVN_27875");
		verificationComment1=CommonMethods.dynamicText("AutoComment1");
		verificationComment2=CommonMethods.dynamicText("AutoComment2");
		
		
		VerificationsPage vp = hp.clickVerificationsMenu();			  
		VerificationDetsParams vdp = new VerificationDetsParams();
		vdp.VerificationName = verificationName27875;
		vdp.EnableVerification = true;
		vdp.AddComments = new ArrayList<String>();
		vdp.AddComments.add(verificationComment1);
		vdp.AddComments.add(verificationComment2);
		vdp.Rolename = roleName;
		if (!vp.createVerification(vdp)){
			TCGFailureMsg = "Could NOT able to create verification"; 
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg); 
		}
		
		
		
		boolean opneoldVerification = verification.openExistingVerification(verificationName27875);
		TestValidation.IsTrue(opneoldVerification, 
				"Able to open verification " + verificationName27875, 
				"Failed to open verification:" +verificationName27875); 
		
		
		boolean setRole = verification.searchAndSetRole(roleName);
		TestValidation.IsTrue(setRole, 
				"Able to add Role from Verification: " + roleName, 
				"Failed to add Role from Verification: " + roleName); 
		
		boolean updateVerification1 = verification.updateVerificationButton();
		TestValidation.IsTrue(updateVerification1, 
				"Able to UPDATE verification role of  " + verificationName27875, 
				"Failed to UPDATE verification role of :" +verificationName27875); 
	
		boolean setRole1 = verification.searchAndSetRole(roleName);
		TestValidation.IsTrue(setRole1, 
				"Able to add Role from Verification: " + roleName, 
				"Failed to add Role from Verification: " + roleName); 
		
		boolean updateVerification2 = verification.updateVerificationButton();
		TestValidation.IsTrue(updateVerification2, 
				"Able to UPDATE verification role of  " + verificationName27875, 
				"Failed to UPDATE verification role of :" +verificationName27875); 

	}

	
	
	@Test(description="Test Case 27874: Manage Verification || Select verification, search for role, add and remove the roles and check the clear functionality")
	public void TestCase_27874() throws Exception{
		try {
		
		verificationName27874 = CommonMethods.dynamicText("AutoVN_27874");
		verificationComment1=CommonMethods.dynamicText("AutoComment1");
		verificationComment2=CommonMethods.dynamicText("AutoComment2");
		
		
		VerificationsPage vp = hp.clickVerificationsMenu();	
		//VerificationsPage vp1 = hp.clickVerificationsMenu();	
		VerificationDetsParams vdp = new VerificationDetsParams();
		vdp.VerificationName = verificationName27874;
		vdp.EnableVerification = true;
		vdp.AddComments = new ArrayList<String>();
		vdp.AddComments.add(verificationComment1);
		vdp.AddComments.add(verificationComment2);
		vdp.Rolename = roleName;
		if (!vp.createVerification(vdp)){
			TCGFailureMsg = "Could NOT able to create verification"; 
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg); 
		}
		
		RolesManagerPage rmp = hp.clickRolesMenu();
		
		boolean newrole= rmp.createRole(roleNameAdd);
		TestValidation.IsTrue(newrole, 
				"Able to add new Role " + roleNameAdd, 
				"Failed to add new Role:" +roleNameAdd); 
		
		
		hp.clickFSQABrowserMenu();
		hp.clickVerificationsMenu();	
		
		boolean opneoldVerification = verification.openExistingVerification(verificationName27874);
		TestValidation.IsTrue(opneoldVerification, 
				"Able to open verification " + verificationName27874, 
				"Failed to open verification:" +verificationName27874); 
		
		
		boolean setRole = verification.searchAndSetRole(roleName);
		TestValidation.IsTrue(setRole, 
				"Able to add Role from Verification: " + roleName, 
				"Failed to add Role from Verification: " + roleName); 
		
		boolean updateVerification74 = verification.clickClearButton();
		TestValidation.IsTrue(updateVerification74, 
				"Able to UPDATE verification role of  " + verificationName27874, 
				"Failed to UPDATE verification role of :" +verificationName27874); 
	
	
		boolean setRole1 = verification.searchAndSetRole(roleNameAdd);
		TestValidation.IsTrue(setRole1, 
				"Able to add Role from Verification: " + roleNameAdd, 
				"Failed to add Role from Verification: " + roleNameAdd); 
		
		boolean updateVerificationClr = verification.clickClearButton();
		TestValidation.IsTrue(updateVerificationClr, 
				"Able to UPDATE verification role of  " + verificationName27874, 
				"Failed to UPDATE verification role of :" +verificationName27874); 
		
	}
		finally {
			if (!controlActions.refreshDisplayedPage()) {
		TCGFailureMsg = "Could NOT refresh application's page";
		TestValidation.Fail(TCGFailureMsg);
		}
	
 }

	}

	@Test(description=" Manage Verification || Select verification, Enable/Disable the verification and verify it in Form Manager -> Verification and Record SignOff.")
	public void TestCase_27872()throws Exception{
		
		try {
		
		verificationName27872 = CommonMethods.dynamicText("AutoVN_27872");
		verificationComment1=CommonMethods.dynamicText("AutoComment1");
		//verificationComment2=CommonMethods.dynamicText("AutoComment2");
		
		
		VerificationsPage vp = hp.clickVerificationsMenu();	
		//VerificationsPage vp1 = hp.clickVerificationsMenu();	
		VerificationDetsParams vdp = new VerificationDetsParams();
		vdp.VerificationName = verificationName27872;
		vdp.EnableVerification = true;
		vdp.AddComments = new ArrayList<String>();
		vdp.AddComments.add(verificationComment1);
		vdp.Rolename = roleName;
		
		boolean newVf= vp.createVerification(vdp);
		TestValidation.IsTrue(newVf, 
				"Able to add new verification " + verificationName27872, 
				"Failed to add new verification:" +verificationName27872); 
		
		
		//hp.clickFormsManagerMenu();
		
		checkform27872 = CommonMethods.dynamicText("Auto_Chk_FM_27872");
		
		hp.clickFSQABrowserMenu();

		boolean createAndReleaseForm = fdp.createAndReleaseForm("Check", checkform27872,"Customers", customersCategoryValue,
				customersInstanceValue); 
		TestValidation.IsTrue(createAndReleaseForm, 
				"Able to create and release form:" + checkform27872,
				"Could NOT able to create and release forms:" +checkform27872);

		hp.clickFormsManagerMenu();
		boolean formLinkedwithVerifications = fm.linkVerificationwithForm(verificationName27872, checkform27872);
		if (!formLinkedwithVerifications) {
			TCGFailureMsg = "Could NOT Linked verification"
					+ fm.linkVerificationwithForm(verificationName27872, checkform27872);
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
					}
		
		hp.clickVerificationsMenu();
		boolean opneoldVerification = verification.openExistingVerification(verificationName27872);
		TestValidation.IsTrue(opneoldVerification, 
				"Able to UPDATE verification " + verificationName27872, 
				"Failed to UPDATE verification:" +verificationName27872); 
		
		
		boolean disableVerificationName = verification.searchAndEnableDisableVerification(verificationName27872,true);
		TestValidation.IsTrue(disableVerificationName, 
				"Able to UPDATE verification Name " + verificationName27872, 
				"Failed to UPDATE verification:" +verificationName27872); 
		

		
		boolean updateVerification = verification.updateVerificationButton();
		TestValidation.IsTrue(updateVerification, 
				"Able to UPDATE verification " + verificationName27872, 
				"Failed to UPDATE verification:" +verificationName27872); 
		
		hp.clickFormsManagerMenu();
		boolean verifyUpdateVerificationName = fm.chkUpdatedVerificationwithForm(verificationName27872);
		TestValidation.IsFalse(verifyUpdateVerificationName, 
				"Not Able to check the " + verificationName + "Updated to" +verificationName27872, 
				"Able to find " + verificationName  + "to" + verificationName27872); 
		
		
		hp.clickFSQABrowserMenu();
		
		
		boolean navigateToforms = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.CUSTOMERS, FSQATAB.FORMS);
			TestValidation.IsTrue(navigateToforms, "For customer category, NAVIGATED to FSQABrowser > Forms tab",
								"Failed to navigate to FSQABrowser > Forms tab");

		boolean applyFilterAndOpenForm = fbp.applyFilterAndOpenForDetails("Form Name", checkform27872);
			TestValidation.IsTrue(applyFilterAndOpenForm, "Verify form is displayed in Forms Tab",
								"Form is NOT displayed in Forms Tab");

		boolean submitDataTest = fbForms.submitData(false, false, false, true, false);
			TestValidation.IsTrue(submitDataTest, "Able to submit the form with data:" + checkform27872,
								"Could NOT able to submit the form with data:" + checkform27872);

		hp.clickFSQABrowserMenu();
			
		//Menu>Record Signoff page
		hp.clickFSQABrowserMenu();
		RecordSignoffPage rsp = hp.clickRecordSignoffMenu();
		TestValidation.Equals(false,
				  			  rsp.error,
				  			  "NAVIGATED to Record Signoff page", 
				  		 	  "Failed to navigate to Record Signoff page");
		
		boolean closePopup = rsp.closeMsgPopup();
		TestValidation.IsTrue(closePopup, 
				  			  "CLOSED the 24 hours message popup", 
			  				  "Failed to close the 24 hours message popup");
		
		boolean applyFilter1 = rsp.applyFilterBySearch(FILTERTYPES.FORMS, checkform27872);
		TestValidation.IsTrue(applyFilter1, 
							  "APPLIED filter for forms for form - " + checkform27872, 
				  			  "Failed to apply filter for forms for form - " + checkform27872);

//		boolean applyFilter2 = rsp.applyFilterBySearch(FILTERTYPES.FORMS, formName2);
//		TestValidation.IsTrue(applyFilter2, 
//							  "APPLIED filter for forms for form - " + checkform27872, 
//				  			  "Failed to apply filter for forms for form - " + checkform27872);
//		
		boolean clickNextBtn = rsp.clickNextBtn();
		TestValidation.IsTrue(clickNextBtn, 
	  			  			  "CLICKED on Next button", 
				  			  "Failed to click on Next button");
		
		FSQABrowserPage fbp = rsp.signoffAllRecords(verificationComment1);
		TestValidation.Equals(false,
							  fbp.error, 
	  			  			  "SIGNED OFF records on Record Signoff page", 
	  		 	  			  "Failed to Sign off records on Record Signoff page");
		}
			
		finally {
					if (!controlActions.refreshDisplayedPage()) {
				TCGFailureMsg = "Could NOT refresh application's page";
				TestValidation.Fail(TCGFailureMsg);
				}
			
		 }
		
		
	}
	
	
	@Test(description=" Test Case 27873: Manage Verification || Select verification, Disable/Enable the comment list and add/remove comments in it.")
	public void TestCase_27873()throws Exception{
		
		try {
		
		
		verificationName27873 = CommonMethods.dynamicText("AutoVN_27873");
		verificationComment27873_1=CommonMethods.dynamicText("AutoComment_27873-1");
		verificationComment27873_2=CommonMethods.dynamicText("AutoComment_27873-2");
		//verificationComment2=CommonMethods.dynamicText("AutoComment2");
		
		
		VerificationsPage vp = hp.clickVerificationsMenu();	
		//VerificationsPage vp1 = hp.clickVerificationsMenu();	
		VerificationDetsParams vdp = new VerificationDetsParams();
		vdp.VerificationName = verificationName27873;
		vdp.EnableVerification = true;
		vdp.AddComments = new ArrayList<String>();
		vdp.AddComments.add(verificationComment1);
		vdp.AddComments.add(verificationComment2);
		vdp.Rolename = roleName;
		
		boolean newVf= vp.createVerification(vdp);
		TestValidation.IsTrue(newVf, 
				"Able to add new verification " + verificationName27873, 
				"Failed to add new verification:" +verificationName27873); 
		
		
		//hp.clickFormsManagerMenu();
		
		checkform27873 = CommonMethods.dynamicText("Auto_Chk_FM_27873");
		
		hp.clickFSQABrowserMenu();

		boolean createAndReleaseForm = fdp.createAndReleaseForm("Check", checkform27873,"Customers", customersCategoryValue,
				customersInstanceValue); 
		TestValidation.IsTrue(createAndReleaseForm, 
				"Able to create and release form:" + checkform27873,
				"Could NOT able to create and release forms:" +checkform27873);

		hp.clickFormsManagerMenu();
		boolean formLinkedwithVerifications = fm.linkVerificationwithForm(verificationName27873, checkform27873);
		if (!formLinkedwithVerifications) {
			TCGFailureMsg = "Could NOT Linked verification"
					+ fm.linkVerificationwithForm(verificationName27873, checkform27873);
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
					}
		
		hp.clickVerificationsMenu();
		boolean opneoldVerification = verification.openExistingVerification(verificationName27873);
		TestValidation.IsTrue(opneoldVerification, 
				"Able to UPDATE verification " + verificationName27873, 
				"Failed to UPDATE verification:" +verificationName27873); 
		
		
		boolean deleteVerificationCmmt = verification.deleteCommentLnk("2");
		TestValidation.IsTrue(deleteVerificationCmmt, 
				"Able to UPDATE verification Name " + verificationName27873, 
				"Failed to UPDATE verification:" +verificationName27873); 
		
		boolean updateVerification = verification.updateVerificationButton();
		TestValidation.IsTrue(updateVerification, 
				"Able to UPDATE THE verification " + verificationName27873, 
				"Failed to UPDATE verification:" +verificationName27873); 
		
		hp.clickFSQABrowserMenu();
		
		
		boolean navigateToforms = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.CUSTOMERS, FSQATAB.FORMS);
			TestValidation.IsTrue(navigateToforms, "For customer category, NAVIGATED to FSQABrowser > Forms tab",
								"Failed to navigate to FSQABrowser > Forms tab");

		boolean applyFilterAndOpenForm = fbp.applyFilterAndOpenForDetails("Form Name", checkform27873);
			TestValidation.IsTrue(applyFilterAndOpenForm, "Verify form is displayed in Forms Tab",
								"Form is NOT displayed in Forms Tab");

		boolean submitDataTest = fbForms.submitData(false, false, false, true, false);
			TestValidation.IsTrue(submitDataTest, "Able to submit the form with data:" + checkform27873,
								"Could NOT able to submit the form with data:" + checkform27873);

		hp.clickFSQABrowserMenu();
	
		boolean navigateToRecords = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.CUSTOMERS,FSQATAB.RECORDS);
		TestValidation.IsTrue(navigateToRecords, 
				"For customer category, NAVIGATED to FSQABrowser > Records tab", 
				"Failed to navigate to FSQABrowser > Records tab");


		boolean openRecord = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, checkform27873);
		TestValidation.IsTrue(openRecord, 
				"OPENED record for form - " + checkform27873, 
				"Failed to open record for form - " + checkform27873);

		boolean signOffRecord = frp.signoffRecordWithVerfication(verificationName27873,verificationComment1);
		TestValidation.IsTrue(signOffRecord, 
				"SIGNED record for form - " + checkform27873, 
				"Failed to sign record for form " + checkform27873);
		
		// passed till here
		
		hp.clickVerificationsMenu();
		boolean opneVerification27873 = verification.openExistingVerification(verificationName27873);
		TestValidation.IsTrue(opneVerification27873, 
				"Able to UPDATE verification " + verificationName27873, 
				"Failed to UPDATE verification:" +verificationName27873); 
		
		
		boolean disableVerificationName27873 = verification.searchAndEnableDisableVerification(verificationName27873,true);
		TestValidation.IsTrue(disableVerificationName27873, 
				"Able to UPDATE verification Name " + verificationName27873, 
				"Failed to UPDATE verification:" +verificationName27873); 
		

		
		boolean updateVerification27873 = verification.updateVerificationButton();
		TestValidation.IsTrue(updateVerification27873, 
				"Able to UPDATE verification " + verificationName27873, 
				"Failed to UPDATE verification:" +verificationName27873); 
		
		hp.clickFSQABrowserMenu();
		
		boolean navigateToRecords1 = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.CUSTOMERS,FSQATAB.RECORDS);
		TestValidation.IsTrue(navigateToRecords1, 
				"For customer category, NAVIGATED to FSQABrowser > Records tab", 
				"Failed to navigate to FSQABrowser > Records tab");


		boolean openRecord1 = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, checkform27873);
		TestValidation.IsTrue(openRecord1, 
				"OPENED record for form - " + checkform27873, 
				"Failed to open record for form - " + checkform27873);

		boolean signOffRecord1 = frp.signoffRecord(verificationComment_NVN);
		TestValidation.IsTrue(signOffRecord1, 
				"SIGNED record for form - " + checkform27873, 
				"Failed to sign record for form " + checkform27873);
		}
		
		finally {
			if (!controlActions.refreshDisplayedPage()) {
		TCGFailureMsg = "Could NOT refresh application's page";
		TestValidation.Fail(TCGFailureMsg);
			}
	
		}
		
	}
	
	
	@Test(description="Record Signoff || Click on next, select the signoff(verification) from dropdown, all the related selected records attached to the verification should be shown.")
	public void TestCase_28017()throws Exception{
		
		try {
		
		
		
		verificationName28017 = CommonMethods.dynamicText("AutoVN_28017");
		verificationComment28017=CommonMethods.dynamicText("AutoComment28017");
		//verificationComment2=CommonMethods.dynamicText("AutoComment2");
		
		
		VerificationsPage vp = hp.clickVerificationsMenu();	
		//VerificationsPage vp1 = hp.clickVerificationsMenu();	
		VerificationDetsParams vdp = new VerificationDetsParams();
		vdp.VerificationName = verificationName28017;
		vdp.EnableVerification = true;
		vdp.AddComments = new ArrayList<String>();
		vdp.AddComments.add(verificationComment28017);
		vdp.Rolename = roleName;
		
		boolean newVf= vp.createVerification(vdp);
		TestValidation.IsTrue(newVf, 
				"Able to add new verification " + verificationName28017, 
				"Failed to add new verification:" +verificationName28017); 
		
		
		//hp.clickFormsManagerMenu();
		
		checkform28017 = CommonMethods.dynamicText("Auto_Chk_FM_28017");
		
		hp.clickFSQABrowserMenu();

		boolean createAndReleaseForm = fdp.createAndReleaseForm("Check", checkform28017,"Customers", customersCategoryValue,
				customersInstanceValue); 
		TestValidation.IsTrue(createAndReleaseForm, 
				"Able to create and release form:" + checkform28017,
				"Could NOT able to create and release forms:" +checkform28017);

		hp.clickFormsManagerMenu();
		boolean formLinkedwithVerifications = fm.linkVerificationwithForm(verificationName28017, checkform28017);
		if (!formLinkedwithVerifications) {
			TCGFailureMsg = "Could NOT Linked verification"
					+ fm.linkVerificationwithForm(verificationName28017, checkform28017);
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
					}
	
		
		hp.clickFSQABrowserMenu();
		
		
		boolean navigateToforms = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.CUSTOMERS, FSQATAB.FORMS);
			TestValidation.IsTrue(navigateToforms, "For customer category, NAVIGATED to FSQABrowser > Forms tab",
								"Failed to navigate to FSQABrowser > Forms tab");

		boolean applyFilterAndOpenForm = fbp.applyFilterAndOpenForDetails("Form Name", checkform28017);
			TestValidation.IsTrue(applyFilterAndOpenForm, "Verify form is displayed in Forms Tab",
								"Form is NOT displayed in Forms Tab");

		boolean submitDataTest = fbForms.submitData(false, false, false, true, false);
			TestValidation.IsTrue(submitDataTest, "Able to submit the form with data:" + checkform28017,
								"Could NOT able to submit the form with data:" + checkform28017);	
			
		boolean navigateToforms1 = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.CUSTOMERS, FSQATAB.FORMS);
			TestValidation.IsTrue(navigateToforms1, "For customer category, NAVIGATED to FSQABrowser > Forms tab",
								"Failed to navigate to FSQABrowser > Forms tab");

		boolean applyFilterAndOpenForm1 = fbp.applyFilterAndOpenForDetails("Form Name", checkform28017);
			TestValidation.IsTrue(applyFilterAndOpenForm1, "Verify form is displayed in Forms Tab",
								"Form is NOT displayed in Forms Tab");
	

		boolean submitDataTest2 = fbForms.submitData(false, false, false, true, false);
			TestValidation.IsTrue(submitDataTest2, "Able to submit the form with data:" + checkform28017,
								"Could NOT able to submit the form with data:" + checkform28017);
		
		boolean navigateToforms2 = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.CUSTOMERS, FSQATAB.FORMS);
			TestValidation.IsTrue(navigateToforms2, "For customer category, NAVIGATED to FSQABrowser > Forms tab",
								"Failed to navigate to FSQABrowser > Forms tab");

		boolean applyFilterAndOpenForm2 = fbp.applyFilterAndOpenForDetails("Form Name", checkform28017);
			TestValidation.IsTrue(applyFilterAndOpenForm2, "Verify form is displayed in Forms Tab",
								"Form is NOT displayed in Forms Tab");
	

		boolean submitDataTest3 = fbForms.submitData(false, false, false, true, false);
			TestValidation.IsTrue(submitDataTest3, "Able to submit the form with data:" + checkform28017,
								"Could NOT able to submit the form with data:" + checkform28017);

		hp.clickFSQABrowserMenu();
			
		//Menu>Record Signoff page
		hp.clickFSQABrowserMenu();
		RecordSignoffPage rsp = hp.clickRecordSignoffMenu();
		TestValidation.Equals(false,
				  			  rsp.error,
				  			  "NAVIGATED to Record Signoff page", 
				  		 	  "Failed to navigate to Record Signoff page");
		
		boolean closePopup = rsp.closeMsgPopup();
		TestValidation.IsTrue(closePopup, 
				  			  "CLOSED the 24 hours message popup", 
			  				  "Failed to close the 24 hours message popup");
		
		boolean applyFilter1 = rsp.applyFilterBySearch(FILTERTYPES.FORMS, checkform28017);
		TestValidation.IsTrue(applyFilter1, 
							  "APPLIED filter for forms for form - " + checkform28017, 
				  			  "Failed to apply filter for forms for form - " + checkform28017);

		boolean clickNextBtn1 = rsp.clickNextBtn();
		TestValidation.IsTrue(clickNextBtn1, 
	  			  			  "CLICKED on Next button", 
				  			  "Failed to click on Next button");
//		
//		boolean selectVfSignOff = rsp.selectVerficationForSignOff(verificationName28017);
//		TestValidation.IsTrue(selectVfSignOff, 
//							  "Able to select verification from dropdown  - " + verificationName28017, 
//				  			  "Failed to apply filter for forms for form - " + verificationName28017);
//		
//		
//		FSQABrowserPage fbp = rsp.signoffAllRecords(verificationName28017);
//		TestValidation.Equals(false,
//							  fbp.error, 
//	  			  			  "SIGNED OFF records on Record Signoff page", 
//	  		 	  			  "Failed to Sign off records on Record Signoff page");
//		
		
		}
		
		finally {
			if (!controlActions.refreshDisplayedPage()) {
		TCGFailureMsg = "Could NOT refresh application's page";
		TestValidation.Fail(TCGFailureMsg);
			}
	
		}
		
	}
	
	@Test(description="Record SignOff || In 'Record Signoff' popup, 'SELECT SIGNOFF' dropdown should be disabled and 'SELECT COMMENT' dropdown should show the list of comments according to verification selected.")
	public void TestCase_28019()throws Exception{
		
		try {
		
		
		verificationComment28019=CommonMethods.dynamicText("AutoComment28019");
		verificationName28019 = CommonMethods.dynamicText("AutoVN_28019");
		verificationComment1=CommonMethods.dynamicText("AutoComment1");
		//verificationComment2=CommonMethods.dynamicText("AutoComment2");
		
		
		VerificationsPage vp = hp.clickVerificationsMenu();	
		//VerificationsPage vp1 = hp.clickVerificationsMenu();	
		VerificationDetsParams vdp = new VerificationDetsParams();
		vdp.VerificationName = verificationName28019;
		vdp.EnableVerification = true;
		vdp.AddComments = new ArrayList<String>();
		vdp.AddComments.add(verificationComment28019);
		vdp.Rolename = roleName;
		
		boolean newVf= vp.createVerification(vdp);
		TestValidation.IsTrue(newVf, 
				"Able to add new verification " + verificationName28019, 
				"Failed to add new verification:" +verificationName28019); 
		
		
		//hp.clickFormsManagerMenu();
		
		checkform28019 = CommonMethods.dynamicText("Auto_Chk_FM_28019");
		
		hp.clickFSQABrowserMenu();

		boolean createAndReleaseForm = fdp.createAndReleaseForm("Check", checkform28019,"Customers", customersCategoryValue,
				customersInstanceValue); 
		TestValidation.IsTrue(createAndReleaseForm, 
				"Able to create and release form:" + checkform28019,
				"Could NOT able to create and release forms:" +checkform28019);

		hp.clickFormsManagerMenu();
		boolean formLinkedwithVerifications = fm.linkVerificationwithForm(verificationName28019, checkform28019);
		if (!formLinkedwithVerifications) {
			TCGFailureMsg = "Could NOT Linked verification"
					+ fm.linkVerificationwithForm(verificationName28019, checkform28019);
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
					}
	
		
		hp.clickFSQABrowserMenu();
		
		
		boolean navigateToforms = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.CUSTOMERS, FSQATAB.FORMS);
			TestValidation.IsTrue(navigateToforms, "For customer category, NAVIGATED to FSQABrowser > Forms tab",
								"Failed to navigate to FSQABrowser > Forms tab");

		boolean applyFilterAndOpenForm = fbp.applyFilterAndOpenForDetails("Form Name", checkform28019);
			TestValidation.IsTrue(applyFilterAndOpenForm, "Verify form is displayed in Forms Tab",
								"Form is NOT displayed in Forms Tab");

		boolean submitDataTest = fbForms.submitData(false, false, false, true, false);
			TestValidation.IsTrue(submitDataTest, "Able to submit the form with data:" + checkform28019,
								"Could NOT able to submit the form with data:" + checkform28019);	
			
		boolean navigateToforms1 = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.CUSTOMERS, FSQATAB.FORMS);
			TestValidation.IsTrue(navigateToforms1, "For customer category, NAVIGATED to FSQABrowser > Forms tab",
								"Failed to navigate to FSQABrowser > Forms tab");

		boolean applyFilterAndOpenForm1 = fbp.applyFilterAndOpenForDetails("Form Name", checkform28019);
			TestValidation.IsTrue(applyFilterAndOpenForm1, "Verify form is displayed in Forms Tab",
								"Form is NOT displayed in Forms Tab");
	

		boolean submitDataTest2 = fbForms.submitData(false, false, false, true, false);
			TestValidation.IsTrue(submitDataTest2, "Able to submit the form with data:" + checkform28019,
								"Could NOT able to submit the form with data:" + checkform28019);
		
		boolean navigateToforms2 = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.CUSTOMERS, FSQATAB.FORMS);
			TestValidation.IsTrue(navigateToforms2, "For customer category, NAVIGATED to FSQABrowser > Forms tab",
								"Failed to navigate to FSQABrowser > Forms tab");

		boolean applyFilterAndOpenForm2 = fbp.applyFilterAndOpenForDetails("Form Name", checkform28019);
			TestValidation.IsTrue(applyFilterAndOpenForm2, "Verify form is displayed in Forms Tab",
								"Form is NOT displayed in Forms Tab");
	

		boolean submitDataTest3 = fbForms.submitData(false, false, false, true, false);
			TestValidation.IsTrue(submitDataTest3, "Able to submit the form with data:" + checkform28019,
								"Could NOT able to submit the form with data:" + checkform28019);

		hp.clickFSQABrowserMenu();
			
		//Menu>Record Signoff page
		hp.clickFSQABrowserMenu();
		RecordSignoffPage rsp = hp.clickRecordSignoffMenu();
		TestValidation.Equals(false,
				  			  rsp.error,
				  			  "NAVIGATED to Record Signoff page", 
				  		 	  "Failed to navigate to Record Signoff page");
		
		boolean closePopup = rsp.closeMsgPopup();
		TestValidation.IsTrue(closePopup, 
				  			  "CLOSED the 24 hours message popup", 
			  				  "Failed to close the 24 hours message popup");
		
		boolean applyFilter1 = rsp.applyFilterBySearch(FILTERTYPES.FORMS, checkform28019);
		TestValidation.IsTrue(applyFilter1, 
							  "APPLIED filter for forms for form - " + checkform28019, 
				  			  "Failed to apply filter for forms for form - " + checkform28019);

		boolean clickNextBtn1 = rsp.clickNextBtn();
		TestValidation.IsTrue(clickNextBtn1, 
	  			  			  "CLICKED on Next button", 
				  			  "Failed to click on Next button");
		
		boolean selectVfSignOff = rsp.selectVerficationForSignOff(verificationName28019);
		TestValidation.IsTrue(selectVfSignOff, 
							  "Able to select verification from dropdown  - " + verificationName28019, 
				  			  "Failed to apply filter for forms for form - " + verificationName28019);
		
		
		FSQABrowserPage fbp = rsp.signoffAllRecords(verificationName28019);
		TestValidation.Equals(false,
							  fbp.error, 
	  			  			  "SIGNED OFF records on Record Signoff page", 
	  		 	  			  "Failed to Sign off records on Record Signoff page");
		
		
		}
		
		finally {
			if (!controlActions.refreshDisplayedPage()) {
		TCGFailureMsg = "Could NOT refresh application's page";
		TestValidation.Fail(TCGFailureMsg);
			}
	
		}
		
	}
	
	@Test(description="Record SignOff || In 'Record Signoff' popup, 'SELECT SIGNOFF' dropdown should be disabled and 'SELECT COMMENT' dropdown should be display 'No data found' message.")
	public void TestCase_28020()throws Exception{
		
		try {
		
		
		verificationName28020 = CommonMethods.dynamicText("AutoVN_28020");
		verificationComment28020_1=CommonMethods.dynamicText("AutoComment_28020-1");
		verificationComment28020_2=CommonMethods.dynamicText("AutoComment_28020-2");
		//verificationComment2=CommonMethods.dynamicText("AutoComment2");
		
		
		VerificationsPage vp = hp.clickVerificationsMenu();	
		//VerificationsPage vp1 = hp.clickVerificationsMenu();	
		VerificationDetsParams vdp = new VerificationDetsParams();
		vdp.VerificationName = verificationName28020;
		vdp.EnableVerification = true;
		vdp.AddComments = new ArrayList<String>();
		vdp.AddComments.add(verificationComment28020_1);
		vdp.AddComments.add(verificationComment28020_2);
		vdp.Rolename = roleName;
		
		boolean newVf= vp.createVerification(vdp);
		TestValidation.IsTrue(newVf, 
				"Able to add new verification " + verificationName28020, 
				"Failed to add new verification:" +verificationName28020); 
		
		
		//hp.clickFormsManagerMenu();
		
		checkform28020 = CommonMethods.dynamicText("Auto_Chk_FM_28020");
		
		hp.clickFSQABrowserMenu();

		boolean createAndReleaseForm = fdp.createAndReleaseForm("Check", checkform28020,"Customers", customersCategoryValue,
				customersInstanceValue); 
		TestValidation.IsTrue(createAndReleaseForm, 
				"Able to create and release form:" + checkform28020,
				"Could NOT able to create and release forms:" +checkform28020);

		hp.clickFormsManagerMenu();
		boolean formLinkedwithVerifications = fm.linkVerificationwithForm(verificationName28020, checkform28020);
		if (!formLinkedwithVerifications) {
			TCGFailureMsg = "Could NOT Linked verification"
					+ fm.linkVerificationwithForm(verificationName28020, checkform28020);
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
					}
					
		hp.clickVerificationsMenu();
		boolean opneoldVerification = verification.openExistingVerification(verificationName28020);
		TestValidation.IsTrue(opneoldVerification, 
				"Able to UPDATE verification " + verificationName28020, 
				"Failed to UPDATE verification:" +verificationName28020); 
		
		boolean deleteVerificationCmmt = verification.deleteCommentLnk("1");
		TestValidation.IsTrue(deleteVerificationCmmt, 
				"Able to UPDATE verification Name " + verificationName28020, 
				"Failed to UPDATE verification:" +verificationName28020); 
		
		
		boolean deleteVerificationCmmt2 = verification.deleteCommentLnk("1");
		TestValidation.IsTrue(deleteVerificationCmmt2, 
				"Able to UPDATE verification Name " + verificationName28020, 
				"Failed to UPDATE verification:" +verificationName28020); 
		
		boolean updateVerification = verification.updateVerificationButton();
		TestValidation.IsTrue(updateVerification, 
				"Able to UPDATE THE verification " + verificationName28020, 
				"Failed to UPDATE verification:" +verificationName28020); 
		
		hp.clickFSQABrowserMenu();			
	
		
		boolean navigateToforms = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.CUSTOMERS, FSQATAB.FORMS);
			TestValidation.IsTrue(navigateToforms, "For customer category, NAVIGATED to FSQABrowser > Forms tab",
								"Failed to navigate to FSQABrowser > Forms tab");

		boolean applyFilterAndOpenForm = fbp.applyFilterAndOpenForDetails("Form Name", checkform28020);
			TestValidation.IsTrue(applyFilterAndOpenForm, "Verify form is displayed in Forms Tab",
								"Form is NOT displayed in Forms Tab");

		boolean submitDataTest = fbForms.submitData(false, false, false, true, false);
			TestValidation.IsTrue(submitDataTest, "Able to submit the form with data:" + checkform28020,
								"Could NOT able to submit the form with data:" + checkform28020);	
			
		boolean navigateToforms1 = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.CUSTOMERS, FSQATAB.FORMS);
			TestValidation.IsTrue(navigateToforms1, "For customer category, NAVIGATED to FSQABrowser > Forms tab",
								"Failed to navigate to FSQABrowser > Forms tab");

		boolean applyFilterAndOpenForm1 = fbp.applyFilterAndOpenForDetails("Form Name", checkform28020);
			TestValidation.IsTrue(applyFilterAndOpenForm1, "Verify form is displayed in Forms Tab",
								"Form is NOT displayed in Forms Tab");
	

		boolean submitDataTest2 = fbForms.submitData(false, false, false, true, false);
			TestValidation.IsTrue(submitDataTest2, "Able to submit the form with data:" + checkform28020,
								"Could NOT able to submit the form with data:" + checkform28020);
		
		boolean navigateToforms2 = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.CUSTOMERS, FSQATAB.FORMS);
			TestValidation.IsTrue(navigateToforms2, "For customer category, NAVIGATED to FSQABrowser > Forms tab",
								"Failed to navigate to FSQABrowser > Forms tab");

		boolean applyFilterAndOpenForm2 = fbp.applyFilterAndOpenForDetails("Form Name", checkform28020);
			TestValidation.IsTrue(applyFilterAndOpenForm2, "Verify form is displayed in Forms Tab",
								"Form is NOT displayed in Forms Tab");
	

		boolean submitDataTest3 = fbForms.submitData(false, false, false, true, false);
			TestValidation.IsTrue(submitDataTest3, "Able to submit the form with data:" + checkform28020,
								"Could NOT able to submit the form with data:" + checkform28020);

		hp.clickFSQABrowserMenu();
			
		//Menu>Record Signoff page
		hp.clickFSQABrowserMenu();
		RecordSignoffPage rsp = hp.clickRecordSignoffMenu();
		TestValidation.Equals(false,
				  			  rsp.error,
				  			  "NAVIGATED to Record Signoff page", 
				  		 	  "Failed to navigate to Record Signoff page");
		
		boolean closePopup = rsp.closeMsgPopup();
		TestValidation.IsTrue(closePopup, 
				  			  "CLOSED the 24 hours message popup", 
			  				  "Failed to close the 24 hours message popup");
		
		boolean applyFilter1 = rsp.applyFilterBySearch(FILTERTYPES.FORMS, checkform28020);
		TestValidation.IsTrue(applyFilter1, 
							  "APPLIED filter for forms for form - " + checkform28020, 
				  			  "Failed to apply filter for forms for form - " + checkform28020);

		boolean clickNextBtn1 = rsp.clickNextBtn();
		TestValidation.IsTrue(clickNextBtn1, 
	  			  			  "CLICKED on Next button", 
				  			  "Failed to click on Next button");
							  
		boolean selectVfSignOff = rsp.selectVerficationForSignOff(verificationName28020);
		TestValidation.IsTrue(selectVfSignOff, 
							  "Able to select verification from dropdown  - " + verificationName28020, 
				  			  "Failed to apply filter for forms for form - " + verificationName28020);
		
		
		FSQABrowserPage fbp = rsp.signoffAllRecords(verificationName28020);
		TestValidation.Equals(false,
							  fbp.error, 
	  			  			  "SIGNED OFF records on Record Signoff page", 
	  		 	  			  "Failed to Sign off records on Record Signoff page");
							  

		
		}
		
		finally {
			if (!controlActions.refreshDisplayedPage()) {
		TCGFailureMsg = "Could NOT refresh application's page";
		TestValidation.Fail(TCGFailureMsg);
			}
	
		}
		
	}
	
		@Test(description="Record SignOff || In Record Signoff popup select verification(signoff), add additional comment, sign the record(s) and verify in record history of each record selected and also verify by hovering sign tooltip on record(s) in  record tab grid.")
		public void TestCase_28023()throws Exception{
		
		try {
		
		
		verificationName28023 = CommonMethods.dynamicText("AutoVN_28023");
		verificationComment28023_1=CommonMethods.dynamicText("AutoComment_28023-1");
		verificationComment28023_2=CommonMethods.dynamicText("AutoComment_28023-2");
		//verificationComment2=CommonMethods.dynamicText("AutoComment2");
		
		
		VerificationsPage vp = hp.clickVerificationsMenu();	
		//VerificationsPage vp1 = hp.clickVerificationsMenu();	
		VerificationDetsParams vdp = new VerificationDetsParams();
		vdp.VerificationName = verificationName28023;
		vdp.EnableVerification = true;
		vdp.AddComments = new ArrayList<String>();
		vdp.AddComments.add(verificationComment28023_1);
		vdp.AddComments.add(verificationComment28023_2);
		vdp.Rolename = roleName;
		
		boolean newVf= vp.createVerification(vdp);
		TestValidation.IsTrue(newVf, 
				"Able to add new verification " + verificationName28023, 
				"Failed to add new verification:" +verificationName28023); 
		
		
		//hp.clickFormsManagerMenu();
		
		checkform28023 = CommonMethods.dynamicText("Auto_Chk_FM_28023");
		
		hp.clickFSQABrowserMenu();

		boolean createAndReleaseForm = fdp.createAndReleaseForm("Check", checkform28023,"Customers", customersCategoryValue,
				customersInstanceValue); 
		TestValidation.IsTrue(createAndReleaseForm, 
				"Able to create and release form:" + checkform28023,
				"Could NOT able to create and release forms:" +checkform28023);

		hp.clickFormsManagerMenu();
		boolean formLinkedwithVerifications = fm.linkVerificationwithForm(verificationName28023, checkform28023);
		if (!formLinkedwithVerifications) {
			TCGFailureMsg = "Could NOT Linked verification"
					+ fm.linkVerificationwithForm(verificationName28023, checkform28023);
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
					}
					
		hp.clickVerificationsMenu();
		boolean opneoldVerification = verification.openExistingVerification(verificationName28023);
		TestValidation.IsTrue(opneoldVerification, 
				"Able to UPDATE verification " + verificationName28023, 
				"Failed to UPDATE verification:" +verificationName28023); 
		
		boolean deleteVerificationCmmt = verification.deleteCommentLnk("1");
		TestValidation.IsTrue(deleteVerificationCmmt, 
				"Able to UPDATE verification Name " + verificationName28023, 
				"Failed to UPDATE verification:" +verificationName28023); 
		
		
		boolean deleteVerificationCmmt2 = verification.deleteCommentLnk("1");
		TestValidation.IsTrue(deleteVerificationCmmt2, 
				"Able to UPDATE verification Name " + verificationName28023, 
				"Failed to UPDATE verification:" +verificationName28023); 
		
		boolean updateVerification = verification.updateVerificationButton();
		TestValidation.IsTrue(updateVerification, 
				"Able to UPDATE THE verification " + verificationName28023, 
				"Failed to UPDATE verification:" +verificationName28023); 
		
		hp.clickFSQABrowserMenu();			
	
		
		boolean navigateToforms4 = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.CUSTOMERS, FSQATAB.FORMS);
			TestValidation.IsTrue(navigateToforms4, "For customer category, NAVIGATED to FSQABrowser > Forms tab",
								"Failed to navigate to FSQABrowser > Forms tab");

		boolean applyFilterAndOpenForm = fbp.applyFilterAndOpenForDetails("Form Name", checkform28023);
			TestValidation.IsTrue(applyFilterAndOpenForm, "Verify form is displayed in Forms Tab",
								"Form is NOT displayed in Forms Tab");

		boolean submitDataTest = fbForms.submitData(false, false, false, true, false);
			TestValidation.IsTrue(submitDataTest, "Able to submit the form with data:" + checkform28023,
								"Could NOT able to submit the form with data:" + checkform28023);	
			
		boolean navigateToforms5 = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.CUSTOMERS, FSQATAB.FORMS);
			TestValidation.IsTrue(navigateToforms5, "For customer category, NAVIGATED to FSQABrowser > Forms tab",
								"Failed to navigate to FSQABrowser > Forms tab");

		boolean applyFilterAndOpenForm1 = fbp.applyFilterAndOpenForDetails("Form Name", checkform28023);
			TestValidation.IsTrue(applyFilterAndOpenForm1, "Verify form is displayed in Forms Tab",
								"Form is NOT displayed in Forms Tab");
	

		boolean submitDataTest2 = fbForms.submitData(false, false, false, true, false);
			TestValidation.IsTrue(submitDataTest2, "Able to submit the form with data:" + checkform28023,
								"Could NOT able to submit the form with data:" + checkform28023);
		
		boolean navigateToforms6 = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.CUSTOMERS, FSQATAB.FORMS);
			TestValidation.IsTrue(navigateToforms6, "For customer category, NAVIGATED to FSQABrowser > Forms tab",
								"Failed to navigate to FSQABrowser > Forms tab");

		boolean applyFilterAndOpenForm2 = fbp.applyFilterAndOpenForDetails("Form Name", checkform28023);
			TestValidation.IsTrue(applyFilterAndOpenForm2, "Verify form is displayed in Forms Tab",
								"Form is NOT displayed in Forms Tab");
	

		boolean submitDataTest3 = fbForms.submitData(false, false, false, true, false);
			TestValidation.IsTrue(submitDataTest3, "Able to submit the form with data:" + checkform28023,
								"Could NOT able to submit the form with data:" + checkform28023);

		hp.clickFSQABrowserMenu();
			
		//Menu>Record Signoff page
		hp.clickFSQABrowserMenu();
		RecordSignoffPage rsp = hp.clickRecordSignoffMenu();
		TestValidation.Equals(false,
				  			  rsp.error,
				  			  "NAVIGATED to Record Signoff page", 
				  		 	  "Failed to navigate to Record Signoff page");
		
		boolean closePopup = rsp.closeMsgPopup();
		TestValidation.IsTrue(closePopup, 
				  			  "CLOSED the 24 hours message popup", 
			  				  "Failed to close the 24 hours message popup");
		
		boolean applyFilter1 = rsp.applyFilterBySearch(FILTERTYPES.FORMS, checkform28023);
		TestValidation.IsTrue(applyFilter1, 
							  "APPLIED filter for forms for form - " + checkform28023, 
				  			  "Failed to apply filter for forms for form - " + checkform28023);

		boolean clickNextBtn1 = rsp.clickNextBtn();
		TestValidation.IsTrue(clickNextBtn1, 
	  			  			  "CLICKED on Next button", 
				  			  "Failed to click on Next button");
							  
		boolean selectVfSignOff = rsp.selectVerficationForSignOff(verificationName28023);
		TestValidation.IsTrue(selectVfSignOff, 
							  "Able to select verification from dropdown  - " + verificationName28023, 
				  			  "Failed to apply filter for forms for form - " + verificationName28023);
		
		
		FSQABrowserPage fbp = rsp.signoffAllRecords(signOffAllCmmt28023);
		TestValidation.Equals(false,
							  fbp.error, 
	  			  			  "SIGNED OFF records on Record Signoff page", 
	  		 	  			  "Failed to Sign off records on Record Signoff page");
							  

		
		}
		
		finally {
			if (!controlActions.refreshDisplayedPage()) {
		TCGFailureMsg = "Could NOT refresh application's page";
		TestValidation.Fail(TCGFailureMsg);
			}
	
		}
		
	}
	
		@Test(description="Verify the behavior when direct observation is performed while submitting record.")
		public void TestCase_28455()throws Exception{
		
		try {
		
		
		verificationName28455 = CommonMethods.dynamicText("AutoVN_28455");
		verificationComment28455_1=CommonMethods.dynamicText("AutoComment_28455-1");
		verificationComment28455_2=CommonMethods.dynamicText("AutoComment_28455-2");
		//verificationComment2=CommonMethods.dynamicText("AutoComment2");
		
		
		VerificationsPage vp = hp.clickVerificationsMenu();	
		//VerificationsPage vp1 = hp.clickVerificationsMenu();	
		VerificationDetsParams vdp = new VerificationDetsParams();
		vdp.VerificationName = verificationName28455;
		vdp.EnableVerification = true;
		vdp.AddComments = new ArrayList<String>();
		vdp.AddComments.add(verificationComment28455_1);
		vdp.AddComments.add(verificationComment28455_2);
		vdp.Rolename = roleName;
		
		boolean newVf= vp.createVerification(vdp);
		TestValidation.IsTrue(newVf, 
				"Able to add new verification " + verificationName28455, 
				"Failed to add new verification:" +verificationName28455); 
		
		
		//hp.clickFormsManagerMenu();
		
		checkform28455 = CommonMethods.dynamicText("Auto_Chk_FM_28455");
		
		hp.clickFSQABrowserMenu();

		boolean createAndReleaseForm = fdp.createAndReleaseForm("Check", checkform28455,"Customers", customersCategoryValue,
				customersInstanceValue); 
		TestValidation.IsTrue(createAndReleaseForm, 
				"Able to create and release form:" + checkform28455,
				"Could NOT able to create and release forms:" +checkform28455);

		hp.clickFormsManagerMenu();
		boolean formLinkedwithVerifications = fm.linkVerificationwithForm(verificationName28455, checkform28455);
		if (!formLinkedwithVerifications) {
			TCGFailureMsg = "Could NOT Linked verification"
					+ fm.linkVerificationwithForm(verificationName28455, checkform28455);
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
					}
					
//		hp.clickVerificationsMenu();
//		boolean opneoldVerification = verification.openExistingVerification(verificationName28455);
//		TestValidation.IsTrue(opneoldVerification, 
//				"Able to UPDATE verification " + verificationName28455, 
//				"Failed to UPDATE verification:" +verificationName28455); 
//		
//		boolean deleteVerificationCmmt = verification.deleteCommentLnk("1");
//		TestValidation.IsTrue(deleteVerificationCmmt, 
//				"Able to UPDATE verification Name " + verificationName28455, 
//				"Failed to UPDATE verification:" +verificationName28455); 
//		
//		
//		boolean deleteVerificationCmmt2 = verification.deleteCommentLnk("1");
//		TestValidation.IsTrue(deleteVerificationCmmt2, 
//				"Able to UPDATE verification Name " + verificationName28455, 
//				"Failed to UPDATE verification:" +verificationName28455); 
//		
//		boolean updateVerification = verification.updateVerificationButton();
//		TestValidation.IsTrue(updateVerification, 
//				"Able to UPDATE THE verification " + verificationName28455, 
//				"Failed to UPDATE verification:" +verificationName28455); 
//		
		hp.clickFSQABrowserMenu();			
	
		
		boolean navigateToforms4 = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.CUSTOMERS, FSQATAB.FORMS);
			TestValidation.IsTrue(navigateToforms4, "For customer category, NAVIGATED to FSQABrowser > Forms tab",
								"Failed to navigate to FSQABrowser > Forms tab");

		boolean applyFilterAndOpenForm = fbp.applyFilterAndOpenForDetails("Form Name", checkform28455);
			TestValidation.IsTrue(applyFilterAndOpenForm, "Verify form is displayed in Forms Tab",
								"Form is NOT displayed in Forms Tab");
			
		boolean submitFormWithDirectObservation = fbp.clickDirectObservationVerification(adminUsername,
					adminverificationPinTxt);
			TestValidation.IsTrue(submitFormWithDirectObservation,
					"Verify form is saved with Direct Observation in Forms Tab",
					"Form is NOT saved with Direct Observation in Forms Tab");
	

		boolean submitDataTest = fbForms.submitData(false, false, false, true, false);
			TestValidation.IsTrue(submitDataTest, "Able to submit the form with data:" + checkform28455,
								"Could NOT able to submit the form with data:" + checkform28455);	
			
		boolean navigateToforms5 = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.CUSTOMERS, FSQATAB.FORMS);
			TestValidation.IsTrue(navigateToforms5, "For customer category, NAVIGATED to FSQABrowser > Forms tab",
								"Failed to navigate to FSQABrowser > Forms tab");

		boolean applyFilterAndOpenForm1 = fbp.applyFilterAndOpenForDetails("Form Name", checkform28455);
			TestValidation.IsTrue(applyFilterAndOpenForm1, "Verify form is displayed in Forms Tab",
								"Form is NOT displayed in Forms Tab");
			
		boolean submitFormWithDirectObservation1 = fbp.clickDirectObservationVerification(adminUsername,
					adminverificationPinTxt);
			TestValidation.IsTrue(submitFormWithDirectObservation1,
					"Verify form is saved with Direct Observation in Forms Tab",
					"Form is NOT saved with Direct Observation in Forms Tab");


		boolean submitDataTest2 = fbForms.submitData(false, false, false, true, false);
			TestValidation.IsTrue(submitDataTest2, "Able to submit the form with data:" + checkform28455,
								"Could NOT able to submit the form with data:" + checkform28455);
		
	
		hp.clickFSQABrowserMenu();
		

		boolean navigateToRecords = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.CUSTOMERS,FSQATAB.RECORDS);
		TestValidation.IsTrue(navigateToRecords, 
				"For customer category, NAVIGATED to FSQABrowser > Records tab", 
				"Failed to navigate to FSQABrowser > Records tab");


		boolean openRecord = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, checkform28455);
		TestValidation.IsTrue(openRecord, 
				"OPENED record for form - " + checkform28455, 
				"Failed to open record for form - " + checkform28455);
		DateTime dt = new DateTime();
		String currentDate = dt.getTodayDate("MM/dd/YYYY", dt.getTimezone(adminTimezone));
	
		HashMap<String, String> histDetail = new HashMap<String, String>();
		histDetail.put(null, verificationName + "|" + displayName + "|" + currentDate);

		HistoryDetails hd = new HistoryDetails();
		hd.HeaderDetail = histDetail;

		
		boolean verifyHistory = frp.verifyHistoryPopupDetails(hd);
		TestValidation.IsTrue(verifyHistory,
				"VERIFIED the record has been signed by Direct observation Verification " ,
				"Failed to verify whether record is signed by Direct observation Verification ");


							  

			
		//Menu>Record Signoff page
		//hp.clickFSQABrowserMenu();
//		RecordSignoffPage rsp = hp.clickRecordSignoffMenu();
//		TestValidation.Equals(false,
//				  			  rsp.error,
//				  			  "NAVIGATED to Record Signoff page", 
//				  		 	  "Failed to navigate to Record Signoff page");
//		
//		boolean closePopup = rsp.closeMsgPopup();
//		TestValidation.IsTrue(closePopup, 
//				  			  "CLOSED the 24 hours message popup", 
//			  				  "Failed to close the 24 hours message popup");
//		
//		boolean applyFilter1 = rsp.applyFilterBySearch(FILTERTYPES.FORMS, checkform28455);
//		TestValidation.IsTrue(applyFilter1, 
//							  "APPLIED filter for forms for form - " + checkform28455, 
//				  			  "Failed to apply filter for forms for form - " + checkform28455);
//
//		boolean clickNextBtn1 = rsp.clickNextBtn();
//		TestValidation.IsTrue(clickNextBtn1, 
//	  			  			  "CLICKED on Next button", 
//				  			  "Failed to click on Next button");
//							  
//		boolean selectVfSignOff = rsp.selectVerficationForSignOff(verificationName28455);
//		TestValidation.IsTrue(selectVfSignOff, 
//							  "Able to select verification from dropdown  - " + verificationName28455, 
//				  			  "Failed to apply filter for forms for form - " + verificationName28455);
//		hp.clickFSQABrowserMenu();
		
//
//		FSQABrowserPage fbp = rsp.signoffAllRecords(signOffAllCmmt28455);
//		TestValidation.Equals(false,
//							  fbp.error, 
//	  			  			  "SIGNED OFF records on Record Signoff page", 
//	  		 	  			  "Failed to Sign off records on Record Signoff page");
		
	
		
		}
		
		finally {
			if (!controlActions.refreshDisplayedPage()) {
		TCGFailureMsg = "Could NOT refresh application's page";
		TestValidation.Fail(TCGFailureMsg);
			}
	
		}
		
	}
		
		@Test(description="Manage Verification || Verify that role in Admin Tools -> Manage Roles should be available in Add or Remove roles section in verification(SignOff)")
		public void TestCase_27911()throws Exception{
		
		String newRole_27911 = CommonMethods.dynamicText("Role");	
		try {
		
		
		verificationName27911 = CommonMethods.dynamicText("AutoVN_27911");
		
		
		VerificationsPage vp = hp.clickVerificationsMenu();	
		VerificationDetsParams vdp = new VerificationDetsParams();
		vdp.VerificationName = verificationName27911;
		vdp.EnableVerification = true;
		vdp.AddComments = new ArrayList<String>();
	
		
		boolean newVf= vp.createVerificationWithoutRole(vdp);
		TestValidation.IsTrue(newVf, 
				"Able to add new verification " + verificationName27911, 
				"Failed to add new verification:" +verificationName27911); 
		

		boolean search1= vp.searchRole(newRole_27911);
		TestValidation.IsTrue(search1, 
				"Searched role " + newRole_27911 + "successfully", 
				"Could not search role " + newRole_27911); 
		
		// Verify is role present 
		boolean rolePresentBefore = vp.isRolePresent(newRole_27911);
		TestValidation.IsFalse(rolePresentBefore, 
						       "Verified Role is not Present", 	
						       "Role is present ");
		
		hp.clickFSQABrowserMenu();
		
		RolesManagerPage rmp = hp.clickRolesMenu();
		
		boolean newrole= rmp.createRole(newRole_27911);
		TestValidation.IsTrue(newrole, 
				"Able to add new Role " + newRole_27911, 
				"Failed to add new Role:" +newRole_27911); 
		
		
		//hp.clickFSQABrowserMenu();
		VerificationsPage vp1 = hp.clickVerificationsMenu();	
		
		boolean opneoldVerification27911 = vp1.openExistingVerification(verificationName27911);
		TestValidation.IsTrue(opneoldVerification27911, 
				"Able to UPDATE verification " + verificationName27911, 
				"Failed to UPDATE verification:" +verificationName27911); 
		

		
		boolean search2 = vp.searchRole(newRole_27911);
		TestValidation.IsTrue(search2, 
				"Searched role " + newRole_27911 + "successfully", 
				"Could not search role " + newRole_27911); 
		
		// Verify is role present 
		boolean rolePresentAfter = vp.isRolePresent(newRole_27911);
		TestValidation.IsTrue(rolePresentAfter, 
						       "Verified Role"+ newRole_27911+ "is  Present", 	
						       "Role" + newRole_27911+ "is NOT present ");
}
		
		finally {
			if (!controlActions.refreshDisplayedPage()) {
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
