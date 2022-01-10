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
import com.project.safetychain.api.models.support.TCG_TaskCreation_Wrapper;
import com.project.safetychain.api.models.support.TCG_UserFlows_Wrapper;
import com.project.safetychain.api.models.support.Support.DPT_FIELD_TYPES;
import com.project.safetychain.api.models.support.Support.DPT_FORM_TYPES;
import com.project.safetychain.api.models.support.Support.FormParams;
import com.project.safetychain.api.models.support.Support.UserParams;
import com.project.safetychain.webapp.pageobjects.CommonPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserFormsPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage;
import com.project.safetychain.webapp.pageobjects.HomePage;
import com.project.safetychain.webapp.pageobjects.InboxPage;
import com.project.safetychain.webapp.pageobjects.LoginPage;
import com.project.safetychain.webapp.pageobjects.UserManagerPage;
import com.project.safetychain.webapp.pageobjects.WorkGroupsPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.FSQARESOURCE;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.FSQATAB;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.TASKDETAILS;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FORMRESOURCETYPES;
import com.project.safetychain.api.models.support.Support.FormFieldParams;
import com.project.safetychain.webapp.testcases.support.FormOperations;
import com.project.safetychain.webapp.testcases.support.FormOperations.FormDetails;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.utilities.ApiUtils;
import com.project.utilities.CommonMethods;
import com.project.utilities.ControlActions;

public class TCG_SMK_DataSecurity_VerifyData extends TestBase{

	ApiUtils apiUtils = null;

	ControlActions controlActions;

	//Pages
	LoginPage login;
	HomePage homePage;
	CommonPage mainPage;
	FormDesignerPage formDesignerPage;
	FSQABrowserPage fsqaBrowser;
	FSQABrowserFormsPage fsqaForms;
	UserManagerPage manageUser;
	WorkGroupsPage workgroup;
	InboxPage inbox;
	FormOperations formOperations;
	TASKDETAILS tsd;

	//Classes
	FormDetails formDetails;

	//Data
	public static String resourceCategoryType1;
	public static String locationCategoryValue1,locationCategoryValue2;
	public static String locationInstanceValue1,locationInstanceValue2;
	public static String equipmentCategoryValue1,equipmentCategoryValue2;
	public static String equipmentResourceInstanceValue1,equipmentResourceInstanceValue2;
	public static String userPassword, userNewPassword;
	public static String formType;
	public static String formName;
	public static String NumericFieldName1;
	public static String workGroupName;
	public static String taskName;

	//Data values
	public static String roleName;
	public static String userUN;
	public static String userFN;
	public static String userLN;
	public static List<String> userLocation;
	public static List<String> userRole;

	@BeforeClass(alwaysRun = true)
	public void groupInit() throws InterruptedException {

		apiUtils = new ApiUtils();

		resourceCategoryType1 = FORMRESOURCETYPES.EQUIPMENT;
		formType = DPT_FORM_TYPES.CHECK;
		formName = CommonMethods.dynamicText("SingleForm");
		taskName = "SingleTask";

		NumericFieldName1 = "Num 1";

		locationCategoryValue1 = CommonMethods.dynamicText("LocCat1");
		locationCategoryValue2 = CommonMethods.dynamicText("LocCat2");
		locationInstanceValue1 = CommonMethods.dynamicText("LocInst1");
		locationInstanceValue2 = CommonMethods.dynamicText("LocInst2");

		equipmentCategoryValue1 = CommonMethods.dynamicText("EquipmentCat1");
		equipmentResourceInstanceValue1 = CommonMethods.dynamicText("EquipmentInst1");
		equipmentCategoryValue2 = CommonMethods.dynamicText("EquipmentCat2");		
		equipmentResourceInstanceValue2 = CommonMethods.dynamicText("EquipmentInst2");

		workGroupName =   CommonMethods.dynamicText("Workgroup");

		TCG_FormCreation_Wrapper formCreationWrapper = new TCG_FormCreation_Wrapper();
		TCG_UserFlows_Wrapper userCreationWrapper = new TCG_UserFlows_Wrapper();
		TCG_TaskCreation_Wrapper taskCreationWrapper = new TCG_TaskCreation_Wrapper();

		HashMap<String, List<String>> resourceCatMap = new HashMap<String, List<String>>();
		resourceCatMap.put(equipmentCategoryValue1, Arrays.asList(equipmentResourceInstanceValue1));

		HashMap<String, List<String>> LocationMap = new HashMap<String, List<String>>();
		LocationMap.put(locationCategoryValue1, Arrays.asList(locationInstanceValue1));

		List<String> userList = Arrays.asList(adminUsername);

		formCreationWrapper.Resource_Location_CreationWrapper(resourceCatMap,
				LocationMap, true, userList, resourceCategoryType1);

		LocationMap = new HashMap<String, List<String>>();
		LocationMap.put(locationCategoryValue2, Arrays.asList(locationInstanceValue2));

		resourceCatMap = new HashMap<String, List<String>>();
		resourceCatMap.put(equipmentCategoryValue2, Arrays.asList(equipmentResourceInstanceValue2));

		formCreationWrapper.Resource_Location_CreationWrapper(resourceCatMap,
				LocationMap, true, userList, resourceCategoryType1);

		FormFieldParams NumericField1 = new FormFieldParams();
		NumericField1.DPTFieldType = DPT_FIELD_TYPES.NUMERIC;
		NumericField1.Name = NumericFieldName1;

		String formId = apiUtils.getUUID();

		FormParams fp1 = new FormParams();
		fp1.FormId = formId;
		fp1.type = formType;
		fp1.ResourceType = resourceCategoryType1;
		fp1.ResourceCategoryNm = equipmentCategoryValue2;
		fp1.EquipmentResources = resourceCatMap;
		fp1.FormName = formName;
		fp1.formElements = Arrays.asList(NumericField1);

		boolean formcreation = false;

		try {
			formCreationWrapper.API_Wrapper_Forms(fp1);
			formcreation = true;
			logInfo("'"+formName+"' Form is created");
		} catch (InterruptedException e) {
			logError("Error while '"+formName+"' form creation");
			formcreation = false;
		}

		if(!formcreation) {
			TCGFailureMsg = "FAILED to create form - "+formName;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		roleName = "superadmin";
		userUN = CommonMethods.dynamicText("DataSecurityFlowUser");
		userFN = userUN;
		userLN = "Auto";
		userLocation = new ArrayList<String>();
		userRole = new ArrayList<String>();
		userPassword = GenericPassword;
		userNewPassword = GenericNewPassword;
		userRole.add(roleName);

		UserParams userDetails = new UserParams();
		userDetails.Username = userUN;
		userDetails.ClearPassword = userPassword;
		userDetails.FirstName = userFN;
		userDetails.LastName = userLN;
		userDetails.Email = "datasecurityflow@webappm.com";
		userDetails.TimeZone = "U.S. Pacific";
		userDetails.LocationCat = locationCategoryValue1;
		userDetails.LocationNmIds = Arrays.asList(locationInstanceValue2);
		userDetails.Roles = userRole;

		boolean userCreation = false;

		try {
			userCreationWrapper.createUser_Wrapper(userDetails);
			userCreation = true;
			logInfo("'"+userUN+"' User is created");
		} catch (InterruptedException e) {
			logError("Error while '"+userUN+"' user creation");
			userCreation = false;
		}

		if(!userCreation) {
			TCGFailureMsg = "FAILED to create user - "+userUN;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		boolean workgroupCreationStatus = false;
		try {
			taskCreationWrapper.link_WG_User(Arrays.asList(workGroupName), Arrays.asList(userUN));
			workgroupCreationStatus = true;
			logInfo("Created Workgroup");
		} catch (InterruptedException e) {
			workgroupCreationStatus = false;
			logError("Failed to create Workgroup - "+e.getMessage());
		}

		if(!workgroupCreationStatus) {
			TCGFailureMsg = "FAILED to create workgroup - "+userUN;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		driver = launchbrowser();
		controlActions = new ControlActions(driver);
		mainPage = new CommonPage(driver);
		fsqaBrowser = new FSQABrowserPage(driver);
		fsqaForms = new FSQABrowserFormsPage(driver);
		login = new LoginPage(driver);
		formDesignerPage = new FormDesignerPage(driver);
		manageUser = new UserManagerPage(driver);
		inbox = new InboxPage(driver);
		workgroup = new WorkGroupsPage(driver);
		homePage = new HomePage(driver);
		formOperations = new FormOperations(driver);

		controlActions.getUrl(applicationUrl);

		if(!login.loginAfterUserCreation(userUN, userPassword, userNewPassword)) {
			TCGFailureMsg = "Could NOT login to application";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

	}

	@Test(description="Verification of the resource access from new user")	
	public void TestCase_32597() {
		FormDesignerPage opnFormDesigner = formDesignerPage.clickFormDesignerMenu();
		TestValidation.IsTrue(!(opnFormDesigner.error), 
				"OPENED 'Form Designer'", 
				"Could NOT open the 'Form Designer'");

		controlActions.clickElement(formDesignerPage.SelectCheckFormLnk);

		boolean verifyDisabledResource = formDesignerPage.verifyAddedResourceStatus(FORMRESOURCETYPES.EQUIPMENT, equipmentCategoryValue1, equipmentResourceInstanceValue1);
		TestValidation.IsFalse(verifyDisabledResource, 
				"VERIFIED resource having location that not associated with user in NOT-ACCESSIBLE", 
				"Could NOT verify resource having location that not associated with user");

		boolean exitFromFormDesigner = formDesignerPage.exitFormDesigner();
		TestValidation.IsTrue(exitFromFormDesigner, 
				"EXITED from 'Form Designer'", 
				"Could NOT exit from 'Form Designer'");
	}

	@Test(description="Verification of forms tab | User should see only that form which he created.")	
	public void TestCase_32598() {
		fsqaBrowser = homePage.clickHomepageLink();
		TestValidation.IsTrue(!fsqaBrowser.error, 
				"NAVIGATED to the 'FSQA Browser' page", 
				"FAILED to navigate to the 'FSQA Browser' page");

		boolean equipmentFormTab = fsqaBrowser.selectResourceDropDownandNavigate(FORMRESOURCETYPES.EQUIPMENT,"Forms");
		TestValidation.IsTrue(equipmentFormTab, 
				"NAVIGATED to Equipment - FSQA Browser > Forms Tab", 
				"FAILED to navigate to Equipment - FSQA Browser > Forms Tab");

		boolean verifyOneForm = fsqaBrowser.verifySingleForm(formName);
		TestValidation.IsTrue(verifyOneForm, 
				"VERIFIED only one form is shown", 
				"FAILED to verify form");
	}

	@Test(description="Verification of records | Only records of newly created form should be displayed on Records tab")	
	public void TestCase_32599() {
		fsqaBrowser = homePage.clickHomepageLink();
		TestValidation.IsTrue(!fsqaBrowser.error, 
				"NAVIGATED to the 'FSQA Browser' page", 
				"FAILED to navigate to the 'FSQA Browser' page");

		boolean equipmentFormTab = fsqaBrowser.selectResourceDropDownandNavigate(FORMRESOURCETYPES.EQUIPMENT,"Forms");
		TestValidation.IsTrue(equipmentFormTab, 
				"NAVIGATED to Equipment - FSQA Browser > Forms Tab", 
				"FAILED to navigate to Equipment - FSQA Browser > Forms Tab");

		boolean selectOpenForm = fsqaForms.selectOpenForm(formName);
		TestValidation.IsTrue(selectOpenForm, 
				"SELECTED & opened the form", 
				"Could NOT open the form");

		formDetails = new FormDetails();
		HashMap<String, List<String>> allFields = new HashMap<String, List<String>>();
		allFields.put(NumericFieldName1,Arrays.asList("2"));
		formDetails.locationName = locationInstanceValue2;
		formDetails.resourceName = equipmentResourceInstanceValue2;
		formDetails.fieldDetails = allFields;

		boolean submitForm = formOperations.submitData(formDetails);
		TestValidation.IsTrue(submitForm, 
				"SUBMITTED form - "+formName, 
				"FAILED to submit form - "+formName);

		boolean navigateToRecords = fsqaBrowser.selectResourceDropDownandNavigate(FSQARESOURCE.EQUIPMENT,FSQATAB.RECORDS);
		TestValidation.IsTrue(navigateToRecords, 
				"For Equipment category, NAVIGATED to FSQA Browser > Records tab", 
				"FAILED to navigate to FSQA Browser > Records tab");

		boolean verifySubmittedRecord = fsqaBrowser.verifySingleRecord(formName);
		TestValidation.IsTrue(verifySubmittedRecord, 
				"VERIFIED only one record is shown", 
				"FAILED to verify record");
	}

	@Test(description="Verification of task | Assign a task to any workgroup | User should be able to see the task under that task tab for that forms only")	
	public void TestCase_32600() {

		fsqaBrowser = homePage.clickHomepageLink();
		TestValidation.IsTrue(!fsqaBrowser.error, 
				"NAVIGATED to the 'FSQA Browser' page", 
				"FAILED to navigate to the 'FSQA Browser' page");

		boolean equipmentFormTab = fsqaBrowser.selectResourceDropDownandNavigate(FORMRESOURCETYPES.EQUIPMENT,"Forms");
		TestValidation.IsTrue(equipmentFormTab, 
				"NAVIGATED to Equipment - FSQA Browser > Forms Tab", 
				"FAILED to navigate to Equipment - FSQA Browser > Forms Tab");

		tsd = new TASKDETAILS();
		tsd.Location = locationInstanceValue2;
		tsd.Resource = equipmentResourceInstanceValue2;
		tsd.TaskName = taskName;
		tsd.Workgroup = workGroupName;

		boolean assigntask = fsqaBrowser.assignFormTask(tsd);
		TestValidation.IsTrue(assigntask, 
				"Form task ASSIGNED" + taskName, 
				"FAILED to assign form task" + taskName);

		boolean equipmentTaskTab = fsqaBrowser.selectResourceDropDownandNavigate(FORMRESOURCETYPES.EQUIPMENT,"Tasks");
		TestValidation.IsTrue(equipmentTaskTab, 
				"NAVIGATED to Equipment - FSQA Browser > Tasks Tab", 
				"FAILED to navigate to Equipment - FSQA Browser > Tasks Tab");

		boolean verifyAssignedOneTaskTab = fsqaBrowser.verifySingleTask(taskName);
		TestValidation.IsTrue(verifyAssignedOneTaskTab, 
				"VERIFIED only one task is shown", 
				"FAILED to verify task");

		inbox = homePage.clickInboxMenu();
		TestValidation.IsTrue(!inbox.error, 
				"NAVIGATED to the 'Inbox'", 
				"FAILED to navigate to the 'Inbox' page");

		boolean verifyAssignedOneTask = inbox.verifySingleTask(taskName);
		TestValidation.IsTrue(verifyAssignedOneTask, 
				"VERIFIED only one task is shown", 
				"FAILED to verify task");
	}

	@AfterClass(alwaysRun = true)
	public void closeBrowser() throws InterruptedException {
		driver.close();
	}

}
