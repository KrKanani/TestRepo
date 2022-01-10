package com.project.safetychain.webapp.testcases;


import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.project.safetychain.api.models.support.TCG_FormCreation_Wrapper;
import com.project.safetychain.api.models.support.Support.DPT_FIELD_TYPES;
import com.project.safetychain.api.models.support.Support.DPT_FORM_TYPES;
import com.project.safetychain.api.models.support.Support.Element_Types;
import com.project.safetychain.api.models.support.Support.FormFieldParams;
import com.project.safetychain.api.models.support.Support.FormParams;
import com.project.safetychain.api.models.support.Support.RESOURCE_TYPES;
import com.project.safetychain.webapp.pageobjects.CommonPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserFormsPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage;
import com.project.safetychain.webapp.pageobjects.HomePage;
import com.project.safetychain.webapp.pageobjects.InboxPage;
import com.project.safetychain.webapp.pageobjects.LoginPage;
import com.project.safetychain.webapp.pageobjects.ManageLocationPage;
import com.project.safetychain.webapp.pageobjects.ManageResourcePage;
import com.project.safetychain.webapp.pageobjects.ResourceDesignerPage;
import com.project.safetychain.webapp.pageobjects.WorkGroupsPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.COLUMNHEADER;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.COLUMNSETTING;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.TASKDETAILS;
import com.project.safetychain.webapp.pageobjects.ResourceDesignerPage.CATEGORYTYPES;
import com.project.safetychain.webapp.testcases.support.FormOperations;
import com.project.safetychain.webapp.testcases.support.FormOperations.FormDetails;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.testbase.SupportingClasses.ExecutionMode;
import com.project.utilities.ApiUtils;
import com.project.utilities.CommonMethods;
import com.project.utilities.ControlActions;
import com.project.utilities.DateTime;


public class TCG_SMK_FormFlow extends TestBase{

	ControlActions controlActions;
	FSQABrowserPage fbp;
	CommonPage mainPage;
	FSQABrowserFormsPage fbForms;
	ManageResourcePage manageResource;
	ManageLocationPage locations;
	FormDesignerPage formDesignerPage;
	HomePage hp;
	ResourceDesignerPage resourceDesigner;
	LoginPage lp;
	InboxPage inp;
	WorkGroupsPage wgp;
	TASKDETAILS tsd;
	FormOperations formoperations;
	FormDetails formdetails,formdetails1,formdetails2,formdetails3,formdetails4;
	DateTime datetime;
	ApiUtils apiUtils = null;

	public static String formName;
	public static String locationCategoryValue;
	public static String resourceCategoryValue;
	public static String resourceInstanceValue;
	public static String locationInstanceValue;
	public static String formtaskname;
	public static String workGroupName;
	public static String resourceInstances;
	public static String dynamicfromname;

	String formtype = "Audit";	
	String version = "1.0";
	String version1 = "2.0";
	String modifiedby;
	//String FName = "";

	@BeforeClass(alwaysRun = true)
	public void groupInit() throws InterruptedException {

		formName = CommonMethods.dynamicText("Automation_AuditForm");
		locationCategoryValue = CommonMethods.dynamicText("Loc_Cat");
		resourceCategoryValue = CommonMethods.dynamicText("Equip_Cat");
		resourceInstanceValue = CommonMethods.dynamicText("Equip_Inst");
		locationInstanceValue = CommonMethods.dynamicText("Loc_Inst");
		formtaskname = CommonMethods.dynamicText("FormTask");
		workGroupName = CommonMethods.dynamicText("WG");
		dynamicfromname = "OEE Events";

		if(executionMode.equalsIgnoreCase(ExecutionMode.API)) {

			// ------------------------------------------------------------------------------------------------
			// API Implementation

			TCG_FormCreation_Wrapper formCreationWrapper = new TCG_FormCreation_Wrapper();
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
			// API - Form Creation - Audit

			Element_Types Section1 = new Element_Types();
			Section1.ElementType = DPT_FIELD_TYPES.SECTION;
			Section1.Name = "Section";

			FormFieldParams numericField = new FormFieldParams();
			numericField.DPTFieldType = DPT_FIELD_TYPES.NUMERIC;
			numericField.Name = "Number";

			Section1.formFieldParams = Arrays.asList(numericField);		

			String formId = apiUtils.getUUID();
			logInfo("FormId = " + formId);
			FormParams fp = new FormParams();
			fp.FormId = formId;
			fp.FormName = formName;
			logInfo("Form Name = " + formName);
			fp.type = DPT_FORM_TYPES.AUDIT;
			fp.ResourceType = RESOURCE_TYPES.EQUIPMENT;
			fp.ResourceCategoryNm = resourceCategoryValue;
			fp.ResourceInstanceNm = resourceInstanceValue;
			fp.SectionElements = Arrays.asList(Section1);
			fp.EquipmentResources = resCatMap;
			logInfo("fp.SectionElements = " + fp.SectionElements);	

			formCreationWrapper.API_Wrapper_Forms(fp);	

			// ------------------------------------------------------------------------------------------------
			// WEB Application code starts here

			driver = launchbrowser();
			controlActions = new ControlActions(driver);
			mainPage = new CommonPage(driver);
			controlActions.getUrl(applicationUrl);
			fbp = new FSQABrowserPage(driver);
			fbForms = new FSQABrowserFormsPage(driver);
			lp= new LoginPage(driver);
			locations = new ManageLocationPage(driver);
			manageResource = new ManageResourcePage(driver);
			formDesignerPage = new FormDesignerPage(driver);
			resourceDesigner = new ResourceDesignerPage(driver);
			inp = new InboxPage(driver);
			wgp = new WorkGroupsPage(driver);
			tsd = new TASKDETAILS();
			datetime = new DateTime();
			formoperations = new FormOperations(driver);
			hp = lp.performLogin(adminUsername, adminPassword);
			if(hp.error) {
				TCGFailureMsg = "Could NOT login to application";
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}

			modifiedby = mainPage.getLoggedInUserDetails();

		}
		else if(executionMode.equalsIgnoreCase(ExecutionMode.UI)) {

			// ------------------------------------------------------------------------------------------------
			// Only WEB Application code starts here

			driver = launchbrowser();
			controlActions = new ControlActions(driver);
			mainPage = new CommonPage(driver);
			controlActions.getUrl(applicationUrl);
			fbp = new FSQABrowserPage(driver);
			fbForms = new FSQABrowserFormsPage(driver);
			lp= new LoginPage(driver);
			locations = new ManageLocationPage(driver);
			manageResource = new ManageResourcePage(driver);
			formDesignerPage = new FormDesignerPage(driver);
			resourceDesigner = new ResourceDesignerPage(driver);
			inp = new InboxPage(driver);
			wgp = new WorkGroupsPage(driver);
			tsd = new TASKDETAILS();
			datetime = new DateTime();
			formoperations = new FormOperations(driver);
			hp = lp.performLogin(adminUsername, adminPassword);
			if(hp.error) {
				TCGFailureMsg = "Could NOT login to application";
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}

			modifiedby = mainPage.getLoggedInUserDetails();
			String resourceInstances[][] = {{resourceInstanceValue,"true"}};

			HashMap<String,String> resourceCategories = new HashMap<String,String>();
			resourceCategories.put(CATEGORYTYPES.LOCATION, locationCategoryValue);
			resourceCategories.put(CATEGORYTYPES.EQUIPMENT, resourceCategoryValue);


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

			if(!manageResource.addResourceInstances("Equipment", resourceCategoryValue, 
					resourceInstances, true, locationInstanceValue)) {
				TCGFailureMsg = "Could NOT create resource " + resourceInstances + " & link Location - "+ locationInstanceValue;
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}

			if(!formDesignerPage.createAndReleaseForm(formtype, formName,"Equipment", resourceCategoryValue,
					resourceInstanceValue)) {
				TCGFailureMsg = "Could NOT create and release form - " + formName;
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}

			if(!wgp.createWorkGroup(workGroupName, adminUsername)) {
				TCGFailureMsg = "Could not create workgroup-  " + workGroupName;
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}
		}
		else {
			TCGFailureMsg = "Improper Execution Mode is set - " + executionMode;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

	}

	@Test(description="Verification of form details",priority = -1)
	public void TestCase_31241 () throws Exception{

		hp.clickHomepageLink();
		boolean navigate = fbp.selectResourceDropDownandNavigate("EQUIPMENT","Forms");
		TestValidation.IsTrue(navigate, 
				"Navigated to FSQABrowser>FormsTab", 
				"Failed to navigate to FSQABrowser>Forms Tab");

		boolean applyfilter = fbp.openAndApplySettingsForColumn(COLUMNHEADER.FORMNAME, COLUMNSETTING.FILTER,formName);
		TestValidation.IsTrue(applyfilter, 
				"Applied Filter on" + COLUMNHEADER.FORMNAME, 
				"Failed to apply filter on" + COLUMNHEADER.FORMNAME);

		boolean verifyformtype = fbp.verifyGridValuesForColumn(COLUMNHEADER.FORMTYPE,formtype);
		TestValidation.IsTrue(verifyformtype, 
				"Verified form type value" + formtype, 
				"Failed to verify form type" + formtype);

		boolean verifymodifiedby = fbp.verifyGridValuesForColumn(COLUMNHEADER.MODIFIEDBY, modifiedby);
		TestValidation.IsTrue(verifymodifiedby, 
				"Verified modified by value" + modifiedby, 
				"Failed to verify modified by value" + modifiedby );

		boolean verifyversion = fbp.verifyGridValuesForColumn(COLUMNHEADER.VERSION, version);
		TestValidation.IsTrue(verifyversion, 
				"Verified version" + version, 
				"Failed to verifiy version"+ version);

	}

	@Test(description="Form Edit and Verification of Version")
	public void TestCase_31183() throws Exception{

		hp.clickFSQABrowserMenu();

		boolean navigate = fbp.selectResourceDropDownandNavigate("EQUIPMENT","Forms");
		TestValidation.IsTrue(navigate, 
				"Navigated to FSQABrowser>FormsTab", 
				"Failed to navigate to FSQABrowser>Forms Tab");

		boolean applyfilter = fbp.openAndApplySettingsForColumn(COLUMNHEADER.FORMNAME, COLUMNSETTING.FILTER,formName);
		TestValidation.IsTrue(applyfilter, 
				"Applied Filter on" + COLUMNHEADER.FORMNAME, 
				"Failed to apply filter on" + COLUMNHEADER.FORMNAME);

		boolean editform = fbp.editForm(formName,formDesignerPage);
		TestValidation.IsTrue(editform, 
				"Form Edited", 
				"Failed to edit form");

		hp.clickFSQABrowserMenu();

		boolean navigate1 = fbp.selectResourceDropDownandNavigate("EQUIPMENT","Forms");
		TestValidation.IsTrue(navigate1, 
				"Navigated to FSQABrowser>FormsTab", 
				"Failed to navigate to FSQABrowser>Forms Tab");

		boolean applyfilter1 = fbp.openAndApplySettingsForColumn(COLUMNHEADER.FORMNAME, COLUMNSETTING.FILTER,formName);
		TestValidation.IsTrue(applyfilter1, 
				"Applied Filter on" + COLUMNHEADER.FORMNAME, 
				"Failed to apply filter on" + COLUMNHEADER.FORMNAME);

		boolean verifyversion = fbp.verifyGridValuesForColumn(COLUMNHEADER.VERSION, version1);
		TestValidation.IsTrue(verifyversion, 
				"Verified version" + version1, 
				"Failed to verifiy version"+ version1);
		mainPage.Sync();		

	}

	@Test(description="Form Save,Delete and Submit")
	public void TestCase_31243() throws Exception{

		hp.clickFSQABrowserMenu();

		boolean navigate = fbp.selectResourceDropDownandNavigate("EQUIPMENT","Forms");
		TestValidation.IsTrue(navigate, 
				"Navigated to FSQABrowser>FormsTab", 
				"Failed to navigate to FSQABrowser>Forms Tab");

		boolean applyfilter = fbp.openAndApplySettingsForColumn(COLUMNHEADER.FORMNAME, COLUMNSETTING.FILTER,formName);
		TestValidation.IsTrue(applyfilter, 
				"Applied Filter on" + COLUMNHEADER.FORMNAME, 
				"Failed to apply filter on" + COLUMNHEADER.FORMNAME);
		threadsleep(5000);
		boolean saveform = fbForms.saveForm(formName);
		TestValidation.IsTrue(saveform, 
				"Form saved" + formName, 
				"Failed to save form" + formName);
		boolean deleteform = fbp.deleteSavedForm(formName);
		TestValidation.IsTrue(deleteform, 
				"Form Deleted" + formName, 
				"Failed to delete form" + formName);

		boolean submit = fbForms.saveAndSubmitForm(formName);
		TestValidation.IsTrue(submit, 
				"Form submitted" + formName, 
				"Failed to submit form" + formName);
	}

	@Test(description="Complete Form Task")
	public void TestCase_34933() throws Exception{

		hp.clickFSQABrowserMenu();

		boolean navigate = fbp.selectResourceDropDownandNavigate("EQUIPMENT","Forms");
		TestValidation.IsTrue(navigate, 
				"Navigated to FSQABrowser>FormsTab", 
				"Failed to navigate to FSQABrowser>Forms Tab");

		boolean applyfilter = fbp.openAndApplySettingsForColumn(COLUMNHEADER.FORMNAME, COLUMNSETTING.FILTER,formName);
		TestValidation.IsTrue(applyfilter, 
				"Applied Filter on" + COLUMNHEADER.FORMNAME, 
				"Failed to apply filter on" + COLUMNHEADER.FORMNAME);

		tsd.Location = locationInstanceValue;
		tsd.Resource = resourceInstanceValue;
		tsd.TaskName = formtaskname;
		tsd.Workgroup = workGroupName;

		boolean assigntask = fbp.assignFormTask(tsd);
		TestValidation.IsTrue(assigntask, 
				"Form task assigned" + formtaskname, 
				"Failed to assign form task" + formtaskname);

		hp.clickInboxMenu();

		boolean completetask = inp.selectFormTask(formtaskname);		
		TestValidation.IsTrue(completetask, 
				"Form task selected" + formtaskname, 
				"Failed to select form task" + formtaskname);

		boolean submit = fbForms.submitData(false,false,false,true,false);
		TestValidation.IsTrue(submit, 
				"Form submitted" + formName, 
				"Failed to submit form" + formName);

	}

	@Test(description="Dynamic Flow Functionality")
	public void TestCase_43730() throws Exception{

		hp.clickFSQABrowserMenu();

		boolean navigate = fbp.selectResourceDropDownandNavigate("EQUIPMENT","Forms");
		TestValidation.IsTrue(navigate, 
				"Navigated to FSQABrowser>FormsTab", 
				"Failed to navigate to FSQABrowser>Forms Tab");

		boolean applyfilter = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME,dynamicfromname);
		TestValidation.IsTrue(applyfilter, 
				"Applied Filter on" + COLUMNHEADER.FORMNAME, 
				"Failed to apply filter on" + COLUMNHEADER.FORMNAME);


		formdetails = new FormDetails();
		HashMap<String, List<String>> fillData = new LinkedHashMap<String, List<String>>();
		String batchID = CommonMethods.dynamicStrictText("ID1");
		fillData.put("BatchID",Arrays.asList(batchID));
		fillData.put("Shift",Arrays.asList("Shift 1"));
		fillData.put("Event Type",Arrays.asList("Actual Production Start"));
		String datetime1 = datetime.getDateTime("Day",0,4,30,true,true);
		fillData.put("Actual Production Start Time",Arrays.asList(datetime1));
		formdetails.fieldDetails = fillData;
		formdetails.resourceName = "OEE Equipment Migration resource";
		formdetails.locationName = "OEE";

		boolean submitform = formoperations.submitData(formdetails);
		TestValidation.IsTrue(submitform, 
				"OEE Form submitted successfully", 
				"Failed to submit OEE Form");

		hp.clickInboxMenu();

		boolean openInboxTask = inp.selectFormTask("OEEEvent_"+batchID);
		TestValidation.IsTrue(openInboxTask, 
				"Openend oee form task", 
				"Failed to open OEE Form Task");

		formdetails1 = new FormDetails();
		HashMap<String, List<String>> fillData1 = new LinkedHashMap<String, List<String>>();		
		formdetails1.locationName = "OEE";
		formdetails1.resourceName = "OEE Equipment Migration resource";
		formdetails1.fieldDetails = fillData1;
		formdetails1.newFieldValue = true;		
		String batchID1 = CommonMethods.dynamicStrictText("ID1");
		fillData1.put("BatchID",Arrays.asList(batchID1));
		fillData1.put("Shift",Arrays.asList("Shift 2"));
		fillData1.put("Event Type",Arrays.asList("Throughput Quantity"));
		fillData1.put("Quantity Produced",Arrays.asList("12"));
		fillData1.put("Quantity Date & Time", Arrays.asList(datetime1));


		boolean submitformtask1 = formoperations.submitData(formdetails1);
		TestValidation.IsTrue(submitformtask1, 
				"OEE Form Task submitted successfully", 
				"Failed to submit OEE Form Task");

		controlActions.refreshPage();

		boolean openInboxTask1 = inp.selectFormTask("OEEEvent_"+batchID1);
		TestValidation.IsTrue(openInboxTask1, 
				"Openend oee form task", 
				"Failed to open OEE Form Task");	

		formdetails2 = new FormDetails();
		HashMap<String, List<String>> fillData2 = new LinkedHashMap<String, List<String>>();		
		formdetails2.locationName = "OEE";
		formdetails2.resourceName = "OEE Equipment Migration resource";
		formdetails2.fieldDetails = fillData2;
		formdetails2.newFieldValue = true;		
		String batchID2 = CommonMethods.dynamicStrictText("ID1");
		fillData2.put("BatchID",Arrays.asList(batchID2));
		fillData2.put("Shift",Arrays.asList("Shift 3"));
		fillData2.put("Event Type",Arrays.asList("Unplanned Downtime"));
		fillData2.put("Downtime Event Type",Arrays.asList("Start"));	
		fillData2.put("Event Start Time",Arrays.asList(datetime1));	

		boolean submitformtask2 = formoperations.submitData(formdetails2);
		TestValidation.IsTrue(submitformtask2, 
				"OEE Form Task submitted successfully", 
				"Failed to submit OEE Form Task");

		controlActions.refreshPage();

		boolean openInboxTask2 = inp.selectFormTask("OEEEvent_"+batchID2);
		TestValidation.IsTrue(openInboxTask2, 
				"Openend oee form task", 
				"Failed to open OEE Form Task");	

		formdetails3 = new FormDetails();
		HashMap<String, List<String>> fillData3 = new LinkedHashMap<String, List<String>>();		
		formdetails3.locationName = "OEE";
		formdetails3.resourceName = "OEE Equipment Migration resource";
		formdetails3.fieldDetails = fillData3;
		formdetails3.newFieldValue = true;		
		String batchID3 = CommonMethods.dynamicStrictText("ID1");
		fillData3.put("BatchID",Arrays.asList(batchID3));
		fillData3.put("Shift",Arrays.asList("Shift 1"));
		fillData3.put("Event Type",Arrays.asList("Quality"));
		fillData3.put("Quality Event Type",Arrays.asList("Start up Rejects"));
		fillData3.put("Rejects on Start up Reason", Arrays.asList("Raw material"));
		fillData3.put("Event Quantity Rejected", Arrays.asList("5"));
		fillData3.put("Quantity Rejected Time", Arrays.asList(datetime1));

		boolean submitformtask3 = formoperations.submitData(formdetails3);
		TestValidation.IsTrue(submitformtask3, 
				"OEE Form Task submitted successfully", 
				"Failed to submit OEE Form Task");

		controlActions.refreshPage();

		hp.clickInboxMenu();

		boolean openInboxTask3 = inp.selectFormTask("OEEEvent_"+batchID3);
		TestValidation.IsTrue(openInboxTask3, 
				"Openend oee form task", 
				"Failed to open OEE Form Task");	

		formdetails4 = new FormDetails();
		HashMap<String, List<String>> fillData4 = new LinkedHashMap<String, List<String>>();		
		formdetails4.locationName = "OEE";
		formdetails4.resourceName = "OEE Equipment Migration resource";
		formdetails4.fieldDetails = fillData4;
		formdetails4.newFieldValue = true;		
		String batchID4 = CommonMethods.dynamicStrictText("ID1");
		fillData4.put("BatchID",Arrays.asList(batchID4));
		fillData4.put("Shift",Arrays.asList("Shift 1"));
		fillData4.put("Event Type",Arrays.asList("Actual Production End"));
		fillData4.put("Actual Production End Time",Arrays.asList(datetime1));
		fillData4.put("Actual Quantity Produced",Arrays.asList("12"));
		fillData4.put("Actual Quantity Rejected",Arrays.asList("5"));

		boolean submitformtask4 = formoperations.submitData(formdetails4);
		TestValidation.IsTrue(submitformtask4, 
				"OEE Form Task submitted successfully", 
				"Failed to submit OEE Form Task");


	}


	@AfterClass(alwaysRun = true)
	public void closeBrowser() throws InterruptedException {
		driver.close();
	}
}

