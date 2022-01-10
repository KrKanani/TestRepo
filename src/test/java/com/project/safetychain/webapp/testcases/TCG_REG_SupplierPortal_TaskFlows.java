package com.project.safetychain.webapp.testcases;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.project.safetychain.api.models.support.TCG_FormCreation_Wrapper;
import com.project.safetychain.api.models.support.TCG_TaskCreation_Wrapper;
import com.project.safetychain.api.models.support.Support.DPT_FIELD_TYPES;
import com.project.safetychain.api.models.support.Support.DPT_FORM_TYPES;
import com.project.safetychain.api.models.support.Support.FormParams;
import com.project.safetychain.api.models.support.Support.RESOURCE_TYPES;
import com.project.safetychain.webapp.pageobjects.CommonPage;
import com.project.safetychain.webapp.pageobjects.DocumentManagementPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserFormsPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage;
import com.project.safetychain.webapp.pageobjects.HomePage;
import com.project.safetychain.webapp.pageobjects.InboxPage;
import com.project.safetychain.webapp.pageobjects.LoginPage;
import com.project.safetychain.webapp.pageobjects.ManageLocationPage;
import com.project.safetychain.webapp.pageobjects.ManageRequirementPage;
import com.project.safetychain.webapp.pageobjects.ManageResourcePage.RESOURCETYPES;
import com.project.safetychain.webapp.pageobjects.ManageResourcePage.ResourceDetailParams;
import com.project.safetychain.webapp.pageobjects.ManageResourcePage;
import com.project.safetychain.webapp.pageobjects.ResourceDesignerPage;
import com.project.safetychain.webapp.pageobjects.UserManagerPage;
import com.project.safetychain.webapp.pageobjects.WorkGroupsPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserRecordsPage.RecFieldProps;
import com.project.safetychain.webapp.pageobjects.FSQABrowserRecordsPage.UpdateFieldDets;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.ElementProperties;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FIELD_TYPES;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FORMRESOURCETYPES;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FORMTYPES;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FormDesignParams;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FormFieldParams;
import com.project.safetychain.webapp.pageobjects.ResourceDesignerPage.CATEGORYTYPES;
import com.project.safetychain.webapp.pageobjects.SupplierPortalPage;
import com.project.safetychain.webapp.pageobjects.SupplierPortalPage.SUPP_PRTL_FIELDS;
import com.project.safetychain.webapp.pageobjects.SupplierPortalPage.SUPP_TASK_TYPE;
import com.project.safetychain.webapp.pageobjects.SupplierPortalPageLocators;
import com.project.safetychain.webapp.pageobjects.UserManagerPage.UsrDetailParams;
import com.project.safetychain.webapp.testcases.support.FormOperations;
import com.project.safetychain.webapp.testcases.support.FormOperations.FieldInputs;
import com.project.safetychain.webapp.testcases.support.FormOperations.FieldProperties;
import com.project.safetychain.webapp.testcases.support.FormOperations.FormDetails;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.testbase.SupportingClasses.ExecutionMode;
import com.project.utilities.ApiUtils;
import com.project.utilities.CommonMethods;
import com.project.utilities.ControlActions;
import com.project.utilities.DateTime;

public class TCG_REG_SupplierPortal_TaskFlows extends TestBase {
	ControlActions controlActions;
	FSQABrowserPage fbp;
	CommonPage mainPage;
	FSQABrowserFormsPage ffp;
	ManageResourcePage mrp;
	ManageLocationPage mlp;
	FormDesignerPage fdp;
	ResourceDesignerPage rdp;
	ManageRequirementPage mrqp;
	WorkGroupsPage wgp;
	UserManagerPage ump;
	HomePage hp;
	LoginPage lp;
	UsrDetailParams udp;

	public static String locationCategoryValue;
	public static String locationInstanceValue;
	public static String itemCategoryValue;
	public static String itemInstanceValue;
	public static String suppCategoryValue;
	public static String suppInstance1Value;
	public static String disableSuppInstanceValue;
	public static String suppUN;
	
	public static String formType = "Questionnaire";
	public static String formName;
	//Work group//
	public static String workGroupName;
	//document upload// 
	public static String image = "upload.png";
	public static String document = System.getProperty("user.dir") + "\\test-data-files\\UploadDocuments\\"+image;
	public static String doctype;
	//requirement type creation//
	public static String ackReqName1;
	public static String ackReqName2;
	public static String ackReqName3;
	public static String docReqName1;
	public static String docReqName2;
	public static String formReqName1;
	public static String formReqName2;
	public static String formReqName3;
	public static String formReqName4;
	public static String suppTempName1;
	public static String suppTempName2;
	public static String itemTempName;
	public static String itemAckReqName;
	public static String itemDocReqName;
	public static String itemFormReqName;
	
	public static String currentDate;
	public static String formDueByDate;
	public static String docDueByDate;
	public static String ackDueByDate;
	public static String submissionComment;
	public static String timezoneCode = null;
	public static String displayName = null;
	public static String numFieldName = "Numeric Field";
	public static String fieldMin = "1", fieldTar = "2", fieldMax = "3", fieldUOM = "KG";
	public static String newAttachmentName = "uploadnew.jpg";
	public static String newAttachmentFilePath = System.getProperty("user.dir") + "\\test-data-files\\UploadDocuments\\"
													+newAttachmentName;


	@BeforeClass(alwaysRun = true)
	public void groupInit() throws InterruptedException {

		locationCategoryValue = locationName;
		locationInstanceValue = locationName;
		itemCategoryValue = CommonMethods.dynamicText("Item_Cat") ;
		itemInstanceValue = CommonMethods.dynamicText("000000IInst");
		suppCategoryValue = CommonMethods.dynamicText("Supp_Cat");
		suppInstance1Value = CommonMethods.dynamicText("000000SInst1");
		disableSuppInstanceValue = CommonMethods.dynamicText("00000DisableSInst");
		suppUN = CommonMethods.dynamicText("SuppUser");
		formName = CommonMethods.dynamicText("Automation_QuestForm");	
		workGroupName = CommonMethods.dynamicText("WG");
		doctype = CommonMethods.dynamicText("Doc");
		suppTempName1 = CommonMethods.dynamicText("SuppReqmt1"); 
		suppTempName2 = CommonMethods.dynamicText("SuppReqmt2"); 
		itemTempName = CommonMethods.dynamicText("ItemReqmt1"); 
		ackReqName1 = CommonMethods.dynamicText("SAck1");		
		ackReqName2 = CommonMethods.dynamicText("SAck2");
		ackReqName3 = CommonMethods.dynamicText("SAck3");		
		itemAckReqName = CommonMethods.dynamicText("IAck");
		docReqName1 = CommonMethods.dynamicText("SDocUpload1");
		docReqName2 = CommonMethods.dynamicText("SDocUpload2");
		itemDocReqName = CommonMethods.dynamicText("IDocUpload");
		formReqName1 = CommonMethods.dynamicText("SForm1");
		formReqName2 = CommonMethods.dynamicText("SForm2");
		formReqName3 = CommonMethods.dynamicText("SForm3");
		formReqName4 = CommonMethods.dynamicText("SForm4");
		itemFormReqName = CommonMethods.dynamicText("IForm");
		submissionComment = CommonMethods.dynamicText("Comment");	


		if(executionMode.equalsIgnoreCase(ExecutionMode.API)) {

			// ------------------------------------------------------------------------------------------------
			// API Implementation
			TCG_FormCreation_Wrapper formCreationWrapper = new TCG_FormCreation_Wrapper();
			TCG_TaskCreation_Wrapper taskCreationWrapper = new TCG_TaskCreation_Wrapper();
			ApiUtils apiUtils = new ApiUtils();

			// ------------------------------------------------------------------------------------------------
			// API - Location & Resource Creation and Linking
			List<String> userList = Arrays.asList(adminUsername);

			HashMap<String, List<String>> LocationMap = new HashMap<String, List<String>>();
			LocationMap.put(locationCategoryValue, Arrays.asList(locationInstanceValue));

			HashMap<String, List<String>> resourceCatMap = new HashMap<String, List<String>>();
			resourceCatMap.put(suppCategoryValue, Arrays.asList(suppInstance1Value, disableSuppInstanceValue));

			formCreationWrapper.Resource_Location_CreationWrapper(resourceCatMap, LocationMap, true, userList,
					RESOURCE_TYPES.SUPPLIERS);

			// ------------------------------------------------------------------------------------------------
			// API - Form Creation - Check
			// API - Add fields to form
			com.project.safetychain.api.models.support.Support.FormFieldParams numericField 
			= new com.project.safetychain.api.models.support.Support.FormFieldParams();
			numericField.DPTFieldType = DPT_FIELD_TYPES.NUMERIC;
			numericField.Name = numFieldName;

			// API - Form details
			// API - Generate unique ID for form to be created
			String formId = apiUtils.getUUID();

			// API - Form layout details
			FormParams fp = new FormParams();
			fp.FormId = formId;
			fp.FormName = formName;
			fp.type = DPT_FORM_TYPES.QUESTIONNAIRE;
			fp.ResourceType = RESOURCE_TYPES.SUPPLIERS;
			fp.ResourceCategoryNm = suppCategoryValue;
			fp.ResourceInstanceNm = suppInstance1Value;
			fp.formElements = Arrays.asList(numericField);
			fp.SuppliersResources = resourceCatMap;

			formCreationWrapper.API_Wrapper_Forms(fp);

			// ------------------------------------------------------------------------------------------------
			// To create a Work Group
			taskCreationWrapper.link_WG_User(Arrays.asList(workGroupName), Arrays.asList(username));

			// ------------------------------------------------------------------------------------------------
			// Supporting WEB Application code starts here
			driver = launchbrowser();
			controlActions = new ControlActions(driver);
			controlActions.getUrl(applicationUrl);

			fbp = new FSQABrowserPage(driver);
			ffp = new FSQABrowserFormsPage(driver);
			rdp = new ResourceDesignerPage(driver);
			mlp = new ManageLocationPage(driver);
			mrp = new ManageResourcePage(driver);
			fdp = new FormDesignerPage(driver);
			mrqp = new ManageRequirementPage(driver);
			wgp = new WorkGroupsPage(driver);
			ump = new UserManagerPage(driver);
			hp = new HomePage(driver);
			lp = new LoginPage(driver);

			hp = lp.performLogin(adminUsername, adminPassword);
			if(hp.error) {
				TCGFailureMsg = "Could NOT login to application";
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}

			// Create a Doc type with document for 'Acknowledgement, Document upload' requirement
			DocumentManagementPage dmp = hp.clickdocumentsmenu();
			if(!dmp.docUploadinDocType (doctype,document)) {
				TCGFailureMsg = "Could not upload document in doctype- " + doctype;
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}

			// Create requirement for Supplier instance
			mrqp = hp.clickRequirementsMenu();	
			if(!mrqp.createSupplierRequirement(suppTempName1,doctype,ackReqName1,workGroupName,docReqName1,formName,
					formReqName1,suppInstance1Value)) {
				TCGFailureMsg = "Could not create requirement for supplier " + suppInstance1Value;
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}

			udp = new UsrDetailParams(); 
			udp.Suppliers = Arrays.asList(suppInstance1Value, disableSuppInstanceValue);
			udp.Username = suppUN;
			udp.EmployeeId = "X12345";
			udp.FirstName = "Supplier";
			udp.LastName = "User";
			udp.Email = "test@test.com";
			udp.Password = GenericPassword;
			udp.Title = "Supplier Title";
			udp.Timezone = adminTimezone;
			if(!ump.supplierUserCreation(udp)) {
				TCGFailureMsg = "Could not create Supplier user - " + suppUN;
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}

			if(!hp.userLogout()) {
				TCGFailureMsg = "Could not log out from internal user";
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}

			// First time login using Supplier User
			if(!lp.performLogin(suppUN,GenericPassword,GenericNewPassword)) {
				TCGFailureMsg = "Could not log in from supplier user   "+ suppUN;
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}

			if(!hp.userLogout()) {
				TCGFailureMsg = "Could not log out from internal user";
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}
		}

		else if(executionMode.equalsIgnoreCase(ExecutionMode.UI)) {
			// ------------------------------------------------------------------------------------------------
			// Only WEB Application code starts here

			driver = launchbrowser();
			controlActions = new ControlActions(driver);
			controlActions.getUrl(applicationUrl);

			fbp = new FSQABrowserPage(driver);
			ffp = new FSQABrowserFormsPage(driver);
			rdp = new ResourceDesignerPage(driver);
			mlp = new ManageLocationPage(driver);
			mrp = new ManageResourcePage(driver);
			fdp = new FormDesignerPage(driver);
			mrqp = new ManageRequirementPage(driver);
			wgp = new WorkGroupsPage(driver);
			ump = new UserManagerPage(driver);
			hp = new HomePage(driver);
			lp = new LoginPage(driver);

			hp = lp.performLogin(adminUsername, adminPassword);
			if(hp.error) {
				TCGFailureMsg = "Could NOT login to application";
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}

			//Category creation
			HashMap<String,String> categories = new HashMap<String, String>();
			categories.put(CATEGORYTYPES.SUPPLIERS, suppCategoryValue);
			rdp = hp.clickResourceDesignerMenu();
			if(!rdp.createCategory(categories)) {
				TCGFailureMsg = "Could NOT create Resource categories";
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}

			//Resource creation
			HashMap<String,Boolean> instances = new HashMap<String, Boolean>();
			instances.put(suppInstance1Value, true);
			instances.put(disableSuppInstanceValue, true);
			ResourceDetailParams rd = new ResourceDetailParams();
			rd.CategoryName = suppCategoryValue;
			rd.CategoryType = RESOURCETYPES.SUPPLIERS;
			rd.NumberFieldValue = 30;
			rd.TextFieldValue = "ABC";
			rd.InstanceNames = instances;
			rd.LocationInstanceValue = locationInstanceValue;
			ManageResourcePage mrp = hp.clickResourcesMenu();
			if(!mrp.createResourceLinkLocation(rd)) {
				TCGFailureMsg = "Could NOT create Instances for resource - " + suppCategoryValue;
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}

			// Create form for 'Complete form' requirement
			if(!fdp.createAndReleaseForm(formType, formName,"Suppliers", suppCategoryValue, suppInstance1Value)) {
				TCGFailureMsg = "Could NOT create and release form - " + formName;
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}

			// Create a Doc type with document for 'Acknowledgement, Document upload' requirement
			DocumentManagementPage dmp = hp.clickdocumentsmenu();
			if(!dmp.docUploadinDocType (doctype,document)) {
				TCGFailureMsg = "Could not upload document in doctype- " + doctype;
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}

			if(!wgp.createWorkGroup(workGroupName, adminUsername)) {
				TCGFailureMsg = "Could not create workgroup - " + workGroupName;
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}

			// Create requirement for Supplier instance
			mrqp = hp.clickRequirementsMenu();	
			if(!mrqp.createSupplierRequirement(suppTempName1,doctype,ackReqName1,workGroupName,docReqName1,formName,
					formReqName1,suppInstance1Value)) {
				TCGFailureMsg = "Could not create requirement for supplier " + suppInstance1Value;
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}

			udp = new UsrDetailParams(); 
			udp.Suppliers = Arrays.asList(suppInstance1Value, disableSuppInstanceValue);
			udp.Username = suppUN;
			udp.EmployeeId = "X12345";
			udp.FirstName = "Supplier";
			udp.LastName = "User";
			udp.Email = "test@test.com";
			udp.Password = GenericPassword;
			udp.Title = "Supplier Title";
			udp.Timezone = adminTimezone;
			if(!ump.supplierUserCreation(udp)) {
				TCGFailureMsg = "Could not create Supplier user - " + suppUN;
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}

			if(!hp.userLogout()) {
				TCGFailureMsg = "Could not log out from internal user";
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}

			// First time login using Supplier User
			if(!lp.performLogin(suppUN,GenericPassword,GenericNewPassword)) {
				TCGFailureMsg = "Could not log in from supplier user   "+ suppUN;
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}

			if(!hp.userLogout()) {
				TCGFailureMsg = "Could not log out from internal user";
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
	
	@Test(description="Supplier Portal || Supplier User can select a supplier resource for the Inbox view, "
			+ "if the supplier user is associated with more than one supplier resource")	
	public void TestCase_18514() throws Exception{
		try {
			
			hp = lp.performLogin(adminUsername, adminPassword);
			TestValidation.IsTrue(!hp.error, 
								  "LOGGED IN with Admin user " + adminUsername, 
								  "Failed to login with Admin user " + adminUsername);
		
			// Create requirement for Supplier instance - disableSuppInstanceValue
			mrqp = hp.clickRequirementsMenu();	
			boolean createReq = mrqp.createSupplierRequirement(suppTempName2,doctype,ackReqName2,workGroupName,docReqName2,formName,
					formReqName2,disableSuppInstanceValue);
			TestValidation.IsTrue(createReq, 
								  "CREATED Supplier requirement " + suppTempName2 + " for " + disableSuppInstanceValue + " with all 3 tasks"
								  		+ " - Acknowledgement, Document Upload and Complete Form", 
								  "Failed to Create Supplier requirement " + suppTempName2 + " with all 3 tasks");
			
			boolean logOutFromSC1 = hp.userLogout();
			TestValidation.IsTrue(logOutFromSC1, 
								  "User successfully LOGGED OUT from Safety Chain application", 
								  "Failed to Logout user from Safety Chain application");
			
			hp = lp.performLogin(suppUN, GenericNewPassword);
			TestValidation.IsTrue(!hp.error, 
								  "LOGGED IN with Supplier user " + suppUN, 
								  "Failed to login with Supplier user " + suppUN);
			
			SupplierPortalPage spd = new SupplierPortalPage(driver);
			List<String> beforeValues = CommonMethods.getListOfValuesFromElements(spd.RsrcInstOptns);
			boolean verifyInstances = beforeValues.contains(suppInstance1Value) 
									  && beforeValues.contains(disableSuppInstanceValue);
			TestValidation.IsTrue(verifyInstances, 
								  "VERIFIED Supplier resources associated are displayed in header drop down list", 
								  "Failed to Verify associated Supplier resources displayed");
			
			boolean selectInst1 = spd.selectResourceInstance(suppInstance1Value);
			TestValidation.IsTrue(selectInst1, 
								  "SELECTED Supplier resource " + suppInstance1Value + " from drop down list", 
								  "Failed to Select Supplier resource " + suppInstance1Value + " from drop down list");
			
			int taskCount1 = Integer.parseInt(spd.SuppPrtlTaskCount.getText());
			TestValidation.IsTrue(taskCount1>=3, 
								  "VERIFIED Tasks count displayed as " + taskCount1, 
								  "Failed to Verify Tasks count displayed");
			
			List<String> taskList1 = spd.getListOfValuesForSupp(SUPP_PRTL_FIELDS.TASK_NAME);
			for(String task : taskList1) {
				logInfo("task = "+ task);
			}
			boolean verifyTasks1 = taskList1.contains(ackReqName1) && taskList1.contains(docReqName1) 
									&& taskList1.contains(formReqName1);
			TestValidation.IsTrue(verifyTasks1, 
								  "VERIFIED All 3 tasks assigned to Template - " + suppTempName1, 
								  "Failed to Verify All 3 tasks assigned to Template - " + suppTempName1);
			
			boolean selectInst2 = spd.selectResourceInstance(disableSuppInstanceValue);
			TestValidation.IsTrue(selectInst2, 
								  "SELECTED Supplier resource " + disableSuppInstanceValue + " from drop down list", 
								  "Failed to Select Supplier resource " + disableSuppInstanceValue + " from drop down list");
			
			List<String> taskList2 = spd.getListOfValuesForSupp(SUPP_PRTL_FIELDS.TASK_NAME);	
			boolean verifyTasks2 = taskList2.contains(ackReqName2) && taskList2.contains(docReqName2) 
									&& taskList2.contains(formReqName2);
			TestValidation.IsTrue(verifyTasks2, 
								  "VERIFIED All 3 tasks assigned to Template - " + suppTempName2, 
								  "Failed to Verify All 3 tasks assigned to Template - " + suppTempName2);
			
			boolean logOutFromSP = hp.userLogout();
			TestValidation.IsTrue(logOutFromSP, 
								  "User successfully LOGGED OUT from Supplier Portal", 
								  "Failed to Logout user from Supplier Portal");
			
			hp = lp.performLogin(adminUsername, adminPassword);
			TestValidation.IsTrue(!hp.error, 
								  "LOGGED IN with Admin user " + adminUsername, 
								  "Failed to login with Admin user " + adminUsername);
			
			mrp = hp.clickResourcesMenu();
			boolean disableRsrc = mrp.searchAndDisableResource(RESOURCETYPES.SUPPLIERS, disableSuppInstanceValue);
			TestValidation.IsTrue(disableRsrc, 
								  "Successfully DISABLED resource " + disableSuppInstanceValue, 
								  "Failed to Disable resource " + disableSuppInstanceValue);
			
			boolean logOutFromSC2 = hp.userLogout();
			TestValidation.IsTrue(logOutFromSC2, 
								  "User successfully LOGGED OUT from Safety Chain application", 
								  "Failed to Logout user from Safety Chain application");
			
			hp = lp.performLogin(suppUN, GenericNewPassword);
			TestValidation.IsTrue(!hp.error, 
								  "LOGGED IN with Supplier user " + suppUN, 
								  "Failed to login with Supplier user " + suppUN);
			
			boolean verifyEnabledInstance = spd.SingleSuppUsrHeader.getText().contains(suppInstance1Value);
			TestValidation.IsTrue(verifyEnabledInstance, 
								  "VERIFIED Supplier resource " + suppInstance1Value + " associated is displayed"
								  		+ "and Disabled resource " + itemInstanceValue + " is not displayed", 
								  "Failed to Verify associated Supplier resources displayed");
			
			List<String> afterValues = CommonMethods.getListOfValuesFromElements(spd.RsrcInstOptns);
			TestValidation.IsTrue(afterValues.isEmpty(), 
								  "VERIFIED header dropdown list is not displayed", 
								  "Failed to Verify presence of header dropdown list");
			
			List<String> taskList3 = spd.getListOfValuesForSupp(SUPP_PRTL_FIELDS.TASK_NAME);	
			boolean verifyTasks3 = taskList3.contains(ackReqName1) && taskList3.contains(docReqName1) 
									&& taskList3.contains(formReqName1);
			TestValidation.IsTrue(verifyTasks3, 
								  "VERIFIED All 3 tasks assigned to Template - " + suppTempName1 + " are displayed", 
								  "Failed to Verify All 3 tasks assigned to Template - " + suppTempName1);
			
			boolean verifyTasks4 = taskList3.contains(ackReqName2) && taskList3.contains(docReqName2) 
									&& taskList3.contains(formReqName2);
			TestValidation.IsFalse(verifyTasks4, 
								  "VERIFIED All 3 tasks assigned to Template - " + suppTempName2  + " are NOT displayed", 
								  "Failed to Verify All 3 tasks assigned to Template - " + suppTempName2);
			
		}
		finally {
			suppTempName2 = CommonMethods.dynamicText("SuppReqmt2");
			TestValidation.IsTrue(hp.userLogout(), 
								  "User successfully LOGGED OUT from application", 
								  "Failed to Logout user from application");
		}
	}

	@Test(description="Supplier Portal || Verify the Item resource tasks should not be displayed in Grid if the resource instance is disabled.")	
	public void TestCase_48527() throws Exception{
		try {
			
			hp = lp.performLogin(adminUsername, adminPassword);
			TestValidation.IsTrue(!hp.error, 
								  "LOGGED IN with Admin user " + adminUsername, 
								  "Failed to login with Admin user " + adminUsername);
			
			//Category creation
			HashMap<String,String> categories = new HashMap<String, String>();
			categories.put(CATEGORYTYPES.ITEMS, itemCategoryValue);
			rdp = hp.clickResourceDesignerMenu();
			boolean itemCategory = rdp.createCategory(categories);			
			TestValidation.IsTrue(itemCategory, 
								  "CREATED Item category " + itemCategoryValue, 
								  "Failed to Create Item category " + itemCategoryValue);

			// Add Item resource instance
			HashMap<String,Boolean> instances = new HashMap<String, Boolean>();
			instances.put(itemInstanceValue, true);
			ResourceDetailParams rd = new ResourceDetailParams();
			rd.CategoryName = itemCategoryValue;
			rd.CategoryType = RESOURCETYPES.ITEMS;
			rd.NumberFieldValue = 30;
			rd.TextFieldValue = "ABC";
			rd.InstanceNames = instances;
			rd.LocationInstanceValue = locationInstanceValue;
			ManageResourcePage mrp = hp.clickResourcesMenu();
			boolean createItemInst = mrp.createResourceLinkLocation(rd);
			TestValidation.IsTrue(createItemInst, 
								  "CREATED Item resource instance " + itemInstanceValue + " under category " + itemCategoryValue, 
								  "Failed to Create Item resource instance " + itemInstanceValue + " under category " + itemCategoryValue);
			
			// Add Link to Supplier resource
			boolean searchAndOpenInst = mrp.searchResourceAsPerType(RESOURCETYPES.SUPPLIERS, suppInstance1Value);
			TestValidation.IsTrue(searchAndOpenInst, 
								  "OPENED Supplier resource instance " + suppInstance1Value, 
								  "Failed to Open Supplier resource instance " + suppInstance1Value);
			
			boolean linkItemToSupp = mrp.linkItemSupplier(itemInstanceValue);
			TestValidation.IsTrue(linkItemToSupp, 
								  "LINKED Supplier resource instance " + suppInstance1Value + " with item resource " + itemInstanceValue, 
								  "Failed to Link Supplier resource instance " + suppInstance1Value + " with item resource " + itemInstanceValue);
			
			// Create requirement for Item instance 
			mrqp = hp.clickRequirementsMenu();	
			boolean createReq = mrqp.createItemRequirement(itemTempName,doctype,itemAckReqName,workGroupName,itemDocReqName,formName,
					itemFormReqName,itemInstanceValue);
			TestValidation.IsTrue(createReq, 
								  "CREATED Item requirement " + itemTempName + " for " + itemInstanceValue + " with all 3 tasks"
								  		+ " - Acknowledgement, Document Upload and Complete Form", 
								  "Failed to Create Item requirement " + itemTempName + " with all 3 tasks");
			
			boolean logOutFromSC1 = hp.userLogout();
			TestValidation.IsTrue(logOutFromSC1, 
								  "User successfully LOGGED OUT from Safety Chain application", 
								  "Failed to Logout user from Safety Chain application");
			
			hp = lp.performLogin(suppUN, GenericNewPassword);
			TestValidation.IsTrue(!hp.error, 
								  "LOGGED IN with Supplier user " + suppUN, 
								  "Failed to login with Supplier user " + suppUN);
			
			SupplierPortalPage spd = new SupplierPortalPage(driver);
			
			WebElement SuppRsrcDdl = controlActions.performGetElementByXPath(SupplierPortalPageLocators.SUPP_RSRC_DDL);
			if(SuppRsrcDdl!=null) {
				boolean selectInst = spd.selectResourceInstance(suppInstance1Value);
				TestValidation.IsTrue(selectInst, 
									  "SELECTED Supplier resource " + suppInstance1Value + " from drop down list", 
									  "Failed to Select Supplier resource " + suppInstance1Value + " from drop down list");
			}
			
			List<String> taskList1 = spd.getListOfValuesForSupp(SUPP_PRTL_FIELDS.TASK_NAME);	
			boolean verifyTasks1 = taskList1.contains(itemAckReqName) && taskList1.contains(itemDocReqName)
									&& taskList1.contains(itemFormReqName);
			TestValidation.IsTrue(verifyTasks1, 
								  "VERIFIED All 3 tasks assigned to Template - " + itemTempName + " are displayed", 
								  "Failed to Verify All 3 tasks assigned to Template - " + itemTempName);
			
			boolean logOutFromSP = hp.userLogout();
			TestValidation.IsTrue(logOutFromSP, 
								  "User successfully LOGGED OUT from Supplier Portal", 
								  "Failed to Logout user from Supplier Portal");
			
			hp = lp.performLogin(adminUsername, adminPassword);
			TestValidation.IsTrue(!hp.error, 
								  "LOGGED IN with Admin user " + adminUsername, 
								  "Failed to login with Admin user " + adminUsername);
			
			mrp = hp.clickResourcesMenu();
			boolean disableRsrc = mrp.searchAndDisableResource(RESOURCETYPES.ITEMS, itemInstanceValue);
			TestValidation.IsTrue(disableRsrc, 
								  "Successfully DISABLED resource " + itemInstanceValue, 
								  "Failed to Disable resource " + itemInstanceValue);
			
			boolean logOutFromSC2 = hp.userLogout();
			TestValidation.IsTrue(logOutFromSC2, 
								  "User successfully LOGGED OUT from Safety Chain application", 
								  "Failed to Logout user from Safety Chain application");
			
			hp = lp.performLogin(suppUN, GenericNewPassword);
			TestValidation.IsTrue(!hp.error, 
								  "LOGGED IN with Supplier user " + suppUN, 
								  "Failed to login with Supplier user " + suppUN);
			
			boolean verifyEnabledInstance = spd.SingleSuppUsrHeader.getText().contains(suppInstance1Value);
			TestValidation.IsTrue(verifyEnabledInstance, 
							  "VERIFIED Supplier resource " + suppInstance1Value + " associated is displayed"
							  		+ "and Disabled resource " + itemInstanceValue + " is not displayed", 
							  "Failed to Verify associated Supplier resources displayed");
			
			int taskCount2 = Integer.parseInt(spd.SuppPrtlTotalTaskCount.getText());
			TestValidation.IsTrue(taskCount2>=3, 
								  "VERIFIED total Tasks count displayed as " + taskCount2, 
								  "Failed to Verify total Tasks count displayed");	
			
			List<String> taskList2 = spd.getListOfValuesForSupp(SUPP_PRTL_FIELDS.TASK_NAME);	
			boolean verifyTasks2 = taskList2.contains(itemAckReqName) && taskList2.contains(itemDocReqName) 
									&& taskList2.contains(itemFormReqName);
			TestValidation.IsFalse(verifyTasks2, 
								  "VERIFIED All 3 tasks assigned to Template - " + itemTempName + " are NOT displayed", 
								  "Failed to Verify All 3 tasks assigned to Template - " + itemTempName);
			
		}
		finally {
			itemTempName = CommonMethods.dynamicText("ItemReqmt1"); 
			TestValidation.IsTrue(hp.userLogout(), 
								  "User successfully LOGGED OUT from application", 
								  "Failed to Logout user from application");
		}
	}
	
	@Test(description="Supplier Portal - Inbox - Total number in the badge updated accordingly when the task list is refreshed.")	
	public void TestCase_32996() throws Exception{
		try {
			
			hp = lp.performLogin(suppUN, GenericNewPassword);
			TestValidation.IsTrue(!hp.error, 
								  "LOGGED IN with Supplier user " + suppUN, 
								  "Failed to login with Supplier user " + suppUN);
			
			SupplierPortalPage spd = new SupplierPortalPage(driver);
			List<String> taskList1 = spd.getListOfValuesForSupp(SUPP_PRTL_FIELDS.TASK_NAME);	
			boolean verifyTasks1 = taskList1.contains(ackReqName1) && taskList1.contains(docReqName1) && taskList1.contains(formReqName1);
			TestValidation.IsTrue(verifyTasks1, 
								  "VERIFIED All 3 tasks assigned to Template - " + suppTempName1 + " are displayed", 
								  "Failed to Verify All 3 tasks assigned to Template - " + suppTempName1);
			
			boolean taskCntHeading = spd.SuppPrtlTotalCntHeading.getText().contains("Task Total");
			TestValidation.IsTrue(taskCntHeading, 
								  "VERIFIED Tasks count heading is 'Task Total'", 
								  "Failed to Verify displayed Tasks count heading");
			
			int oldTaskCount = Integer.parseInt(spd.SuppPrtlTaskCount.getText());
			TestValidation.IsTrue(oldTaskCount!=0, 
								  "VERIFIED Tasks count displayed as " + oldTaskCount, 
								  "Failed to Verify Tasks count displayed");
			
			int oldTotalTaskCount = Integer.parseInt(spd.SuppPrtlTotalTaskCount.getText());
			TestValidation.IsTrue(oldTotalTaskCount!=0, 
								  "VERIFIED Total Tasks count displayed as " + oldTotalTaskCount, 
								  "Failed to Verify Total Tasks count displayed");
			
			boolean logOutFromSP = hp.userLogout();
			TestValidation.IsTrue(logOutFromSP, 
								  "User successfully LOGGED OUT from Supplier Portal", 
								  "Failed to Logout user from Supplier Portal");
			
			hp = lp.performLogin(adminUsername, adminPassword);
			TestValidation.IsTrue(!hp.error, 
								  "LOGGED IN with Admin user " + adminUsername, 
								  "Failed to login with Admin user " + adminUsername);
		
			// Create requirement for Supplier instance - disableSuppInstanceValue
			mrqp = hp.clickRequirementsMenu();	
			boolean updateReq = mrqp.createSuppReqInExistingTemplate(suppTempName1,
					doctype,ackReqName3,workGroupName,null,null,null,null);
			TestValidation.IsTrue(updateReq, 
								  "UPDATED Supplier requirement " + suppTempName1 + " with Acknowledgement task", 
								  "Failed to Update Supplier requirement " + suppTempName1 + " with Acknowledgement task");
			
			boolean logOutFromSC1 = hp.userLogout();
			TestValidation.IsTrue(logOutFromSC1, 
								  "User successfully LOGGED OUT from Safety Chain application", 
								  "Failed to Logout user from Safety Chain application");
			
			hp = lp.performLogin(suppUN, GenericNewPassword);
			TestValidation.IsTrue(!hp.error, 
								  "LOGGED IN with Supplier user " + suppUN, 
								  "Failed to login with Supplier user " + suppUN);
			
			int newTaskCount = Integer.parseInt(spd.SuppPrtlTaskCount.getText());
			TestValidation.IsTrue(newTaskCount==oldTaskCount+1, 
								  "VERIFIED Tasks count displayed as " + newTaskCount, 
								  "Failed to Verify Tasks count displayed");
			
			int newTotalTaskCount = Integer.parseInt(spd.SuppPrtlTotalTaskCount.getText());
			TestValidation.IsTrue(newTotalTaskCount==oldTotalTaskCount+1, 
								  "VERIFIED Total Tasks count displayed as " + newTotalTaskCount, 
								  "Failed to Verify Total Tasks count displayed");
			
			List<String> taskList2 = spd.getListOfValuesForSupp(SUPP_PRTL_FIELDS.TASK_NAME);	
			boolean verifyTasks2 = taskList2.contains(ackReqName1) && taskList2.contains(docReqName1) 
									&& taskList2.contains(formReqName1) && taskList2.contains(ackReqName3);
			TestValidation.IsTrue(verifyTasks2, 
								  "VERIFIED All 4 tasks assigned to Template - " + suppTempName1 + " are displayed", 
								  "Failed to Verify All 4 tasks assigned to Template - " + suppTempName1);
			
		}
		finally {
			TestValidation.IsTrue(hp.userLogout(), 
								  "User successfully LOGGED OUT from application", 
								  "Failed to Logout user from application");
		}
	}

	@Test(description="SC Gaps - Supplier Portal - Users can revise a previously submitted Questionnaire"
			+ " for a rejected Complete Form Task - 9073")	
	public void TestCase_10254() throws Exception{
		try {
			String formName = CommonMethods.dynamicText("FormSettings");
			String originalNumFieldVal = "7";
			String updatedNumFieldVal = "2";
			String correctionVal = "Corrections Test Value";
			String commentVal = "Comment Test Value";
			String updatedCommentVal = "Updated Comment Test Value";
			
			hp = lp.performLogin(adminUsername, adminPassword);
			TestValidation.IsTrue(!hp.error, 
								  "LOGGED IN with Admin user " + adminUsername, 
								  "Failed to login with Admin user " + adminUsername);
			
			//FORM - Creation and Release
			HashMap<String, List<String>> fields = new HashMap<String, List<String>>();
			fields.put(FIELD_TYPES.NUMERIC, Arrays.asList(numFieldName));

			FormFieldParams ffp = new FormFieldParams();
			ffp.FieldDetails = fields;

			HashMap<String, List<String>> resource = new HashMap<String, List<String>>();
			resource.put(FORMRESOURCETYPES.SUPPLIERS, Arrays.asList(suppCategoryValue));

			FormDesignParams fdpDets = new FormDesignParams();
			fdpDets.FormType = FORMTYPES.QUESTIONNAIRE;
			fdpDets.FormName = formName;
			fdpDets.SelectResources = resource;
			fdpDets.DesignFields = Arrays.asList(ffp);
			fdpDets.ReleaseForm = false;
			FormDesignerPage fdp = hp.clickFormDesignerMenu();
			boolean saveForm = fdp.createAndReleaseForm(fdpDets);
			TestValidation.IsTrue(saveForm,
								  "SAVED Questionnaire form - " + formName,
								  "Failed to Save Questionnaire form - " + formName); 
			
			boolean editForm = fdp.navigateToReleaseForm_EditForm(formName);
			TestValidation.IsTrue(editForm, 
								  "OPENED form in edit mode", 
								  "Failed to Open form in edit mode");
			
			ElementProperties settings = new ElementProperties();
			settings.COMPLIANCE_CONFIG = true;
			settings.COMPLIANCE_VALUES = new String[] { fieldMin, fieldTar, fieldMax, null };
			settings.ALLOW_CORRECTION = true;
			settings.ALLOW_ATTATHMENTS = true;
			settings.ALLOW_COMMENTS = true;
			
			HashMap<String, ElementProperties> fieldSettings = new HashMap<String, ElementProperties>();
			fieldSettings.put(numFieldName, settings);

			boolean updateSettings = fdp.setFieldProperties(fieldSettings);
			TestValidation.IsTrue(updateSettings, 
								  "'Allow Attachments' setting is SET for all fields present in form " + formName, 
								  "Failed to set 'Allow Attachments' setting for all fields present in form " + formName);

			boolean releaseForm = fdp.releaseForm(formName);
			TestValidation.IsTrue(releaseForm, 
								  "RELEASED form - " + formName, 
								  "Failed to Release form - " + formName);
			
			// Create requirement for Supplier instance 
			mrqp = hp.clickRequirementsMenu();	
			boolean updateReq = mrqp.createSuppReqInExistingTemplate(suppTempName1,
					null,null,workGroupName,null,formName,formReqName3,null);
			TestValidation.IsTrue(updateReq, 
								  "UPDATED Supplier requirement " + suppTempName1 + " with Form task", 
								  "Failed to Update Supplier requirement " + suppTempName1 + " with Form task");
			
			boolean logOutFromSC1 = hp.userLogout();
			TestValidation.IsTrue(logOutFromSC1, 
								  "User successfully LOGGED OUT from Safety Chain application", 
								  "Failed to Logout user from Safety Chain application");
			
			hp = lp.performLogin(suppUN, GenericNewPassword);
			TestValidation.IsTrue(!hp.error, 
								  "LOGGED IN with Supplier user " + suppUN, 
								  "Failed to login with Supplier user " + suppUN);
			
			SupplierPortalPage spd = new SupplierPortalPage(driver);
			
			HashMap<String, List<String>> fillData1 = new HashMap<String, List<String>>();
			fillData1.put(numFieldName,Arrays.asList(originalNumFieldVal));
			
			//Field Property objects
			FieldProperties fp1 = new FieldProperties();
			fp1.fieldName = numFieldName;
			fp1.allowCorrectionsCheck = true;
			fp1.allowCorrectionsValue = correctionVal;
			fp1.allowAttachmenstCheck = true;
			fp1.uploadFilePath = document;
			fp1.complianceStatusCheck = true;
			fp1.complianceStatus = "Non-Compliant";
			fp1.allowCommentsCheck = true;
			fp1.commentText = commentVal;

			//Form object
			FormDetails fd1 = new FormDetails();
			fd1.fieldDetails = fillData1;
			//Field property assignment  
			fd1.fieldProperties = Arrays.asList(fp1);
			fd1.isSubmit = false;
			fd1.doNotSaveOrSubmit = true;
			
			String [] formTaskName = {formReqName3};
			boolean submitFormTask1 = spd.submitformTask(formTaskName, fd1, null);
			TestValidation.IsTrue(submitFormTask1, 
								  "SUBMITTED Form task " + formReqName3, 
								  "Failed to Submit Form task " + formReqName3);
			
			boolean logOutFromSP1 = hp.userLogout();
			TestValidation.IsTrue(logOutFromSP1, 
								  "User successfully LOGGED OUT from Supplier Portal", 
								  "Failed to Logout user from Supplier Portal");
			
			hp = lp.performLogin(adminUsername, adminPassword);
			TestValidation.IsTrue(!hp.error, 
								  "LOGGED IN with Admin user " + adminUsername, 
								  "Failed to login with Admin user " + adminUsername);
			
			InboxPage ip = hp.clickInboxMenu();
			boolean reject =  ip.actionOnTask(formTaskName, false, true);
			TestValidation.IsTrue(reject, 
								  "Task " + formTaskName + " has been REJECTED", 
								  "Failed to Reject task " + formTaskName);
			
			boolean logOutFromSC2 = hp.userLogout();
			TestValidation.IsTrue(logOutFromSC2, 
								  "User successfully LOGGED OUT from Safety Chain application", 
								  "Failed to Logout user from Safety Chain application");

			hp = lp.performLogin(suppUN, GenericNewPassword);
			TestValidation.IsTrue(!hp.error, 
								  "LOGGED IN with Supplier user " + suppUN, 
								  "Failed to login with Supplier user " + suppUN);
			
			boolean openTask1 = spd.openTask(formReqName3, SUPP_TASK_TYPE.COMPLETE_FORM);
			TestValidation.IsTrue(openTask1, 
								  "Task " + formReqName3 + " is opened", 
								  "Failed to Open the task " + formReqName3);
			
			FormOperations fo = new FormOperations(driver);
			WebElement NonCmpliantIcon1 = controlActions.perform_GetElementByXPath(FieldInputs.NON_CMPLAINT_ICON, 
					"FIELD_NAME", numFieldName);
			boolean verifyCompliance1 = NonCmpliantIcon1.isDisplayed();
			TestValidation.IsTrue(verifyCompliance1, 
								  "VERIFIED Numeric field " + numFieldName + " is Non Compliant", 
								  "Failed to Verify Compliance of Numeric field " + numFieldName);
			
			String numFieldValue1 = fo.getFieldValue(numFieldName);
			boolean verifyNumFieldValue1 = numFieldValue1.equals(originalNumFieldVal);
			TestValidation.IsTrue(verifyNumFieldValue1, 
								  "VERIFIED Numeric field " + numFieldName + " value as" + numFieldValue1, 
								  "Failed to Verify Numeric field " + numFieldName + " value");
			
			String AttchmntValue1 = fo.getAttachmentValue(numFieldName);
			boolean verifyAttchmntValue1 = AttchmntValue1.equals(image);
			TestValidation.IsTrue(verifyAttchmntValue1, 
								  "VERIFIED Numeric field " + numFieldName + " Attachment value as" + AttchmntValue1, 
								  "Failed to Verify Numeric field " + numFieldName + " Attachment value");
			
			RecFieldProps fp = new RecFieldProps();
			fp.fieldName = numFieldName;
			fp.fieldValue = updatedNumFieldVal;
			fp.commentsText = updatedCommentVal;
			fp.uploadAttachment = newAttachmentFilePath;
			
			UpdateFieldDets ufd = new UpdateFieldDets();
			ufd.recFieldProps = Arrays.asList(fp);
			boolean updateValue1 = spd.refillFormTask(ufd);
			TestValidation.IsTrue(updateValue1, 
				  	  			  "UPDATED values for field type Numeric as " + updatedNumFieldVal, 
					  			  "Failed to update values for field type Numeric as " + updatedNumFieldVal);
			
			WebElement CmpliantIcon1 = controlActions.perform_GetElementByXPath(FieldInputs.CMPLAINT_ICON, 
					"FIELD_NAME", numFieldName);
			boolean verifyCompliance2 = CmpliantIcon1.isDisplayed();
			TestValidation.IsTrue(verifyCompliance2, 
								  "VERIFIED Numeric field " + numFieldName + " is Compliant", 
								  "Failed to Verify Compliance of Numeric field " + numFieldName);
			
			String numFieldValue2 = fo.getFieldValue(numFieldName);
			boolean verifyNumFieldValue2 = numFieldValue2.equals(updatedNumFieldVal);
			TestValidation.IsTrue(verifyNumFieldValue2, 
								  "VERIFIED Numeric field " + numFieldName + " value as" + numFieldValue2, 
								  "Failed to Verify Numeric field " + numFieldName + " value");
			
			boolean closeForm = spd.clickFormCancelButton();
			TestValidation.IsTrue(closeForm, 
								  "CLOSED form task " + formReqName3, 
								  "Failed to Close form task " + formReqName3);
			
			boolean openTask2 = spd.openTask(formReqName3, SUPP_TASK_TYPE.COMPLETE_FORM);
			TestValidation.IsTrue(openTask2, 
								  "Task " + formReqName3 + " is opened", 
								  "Failed to Open the task " + formReqName3);
			
			WebElement NonCmpliantIcon2 = controlActions.perform_GetElementByXPath(FieldInputs.NON_CMPLAINT_ICON, 
					"FIELD_NAME", numFieldName);
			boolean verifyCompliance3 = NonCmpliantIcon2.isDisplayed();
			TestValidation.IsTrue(verifyCompliance3, 
								  "VERIFIED Numeric field " + numFieldName + " is Non Compliant", 
								  "Failed to Verify Compliance of Numeric field " + numFieldName);
			
			String numFieldValue3 = fo.getFieldValue(numFieldName);
			boolean verifyNumFieldValue3 = numFieldValue3.equals(originalNumFieldVal);
			TestValidation.IsTrue(verifyNumFieldValue3, 
								  "VERIFIED Numeric field " + numFieldName + " value as" + numFieldValue1, 
								  "Failed to Verify Numeric field " + numFieldName + " value");
			
			boolean updateValue2 = spd.refillFormTask(ufd);
			TestValidation.IsTrue(updateValue2, 
				  	  			  "UPDATED values for field type Numeric as " + updatedNumFieldVal, 
					  			  "Failed to update values for field type Numeric as " + updatedNumFieldVal);
			
			boolean saveTask = spd.clickSaveFormButton();
			TestValidation.IsTrue(saveTask, 
								  "SAVED Form task " + formReqName3, 
								  "Failed to Save Form task " + formReqName3);
			
			boolean openTask3 = spd.openTask(formReqName3, SUPP_TASK_TYPE.COMPLETE_FORM);
			TestValidation.IsTrue(openTask3, 
								  "Task " + formReqName3 + " is opened", 
								  "Failed to Open the task " + formReqName3);
			
			WebElement CmpliantIcon2 = controlActions.perform_GetElementByXPath(FieldInputs.CMPLAINT_ICON, 
					"FIELD_NAME", numFieldName);
			boolean verifyCompliance4 = CmpliantIcon2.isDisplayed();
			TestValidation.IsTrue(verifyCompliance4, 
								  "VERIFIED Numeric field " + numFieldName + " is Compliant", 
								  "Failed to Verify Compliance of Numeric field " + numFieldName);
			
			String numFieldValue4 = fo.getFieldValue(numFieldName);
			boolean verifyNumFieldValue4 = numFieldValue4.equals(updatedNumFieldVal);
			TestValidation.IsTrue(verifyNumFieldValue4, 
								  "VERIFIED Numeric field " + numFieldName + " value as" + numFieldValue4, 
								  "Failed to Verify Numeric field " + numFieldName + " value");
			
			String AttchmntValue2 = fo.getAttachmentValue(numFieldName);
			boolean verifyAttchmntValue2 = AttchmntValue2.contains(newAttachmentName);
			TestValidation.IsTrue(verifyAttchmntValue2, 
								  "VERIFIED Numeric field " + numFieldName + " Attachment value as" + AttchmntValue2, 
								  "Failed to Verify Numeric field " + numFieldName + " Attachment value");
			
			boolean submitForm1 = spd.clickSubmitFormButton();
			TestValidation.IsTrue(submitForm1, 
								  "CLICKED on Submit Form button", 
								  "Failed to Click on Submit Form button");
			
			boolean verifyPopupTitle = spd.SubmitPopupTitle.getText().contains("Submit Form");
			TestValidation.IsTrue(verifyPopupTitle, 
								  "VERIFIED Submit popup's title is 'Submit Form'", 
								  "Failed to Verify Submit popup's title is 'Submit Form'");
			
			String expectedPopupMsg = "Would you like to add a note along with this submission? If not, simply click OK now.";
			boolean verifyPopupMsg = spd.SubmitPopupMsg.getText().contains(expectedPopupMsg);
			TestValidation.IsTrue(verifyPopupMsg, 
								  "VERIFIED Submit popup's message as - " + expectedPopupMsg, 
								  "Failed to Verify Submit popup's message");
			
			boolean closePopup = spd.closeSubmitPopup();
			TestValidation.IsTrue(closePopup, 
								  "CLOSED Submit popup", 
								  "Failed to Close Submit popup");
			
			WebElement SubmitPopupTitle = controlActions.perform_GetElementByClassName("scs-popup-inside-title");
			TestValidation.IsTrue(SubmitPopupTitle==null, 
								  "VERIFIED Submit popup is closed", 
								  "Failed to Verify Submit popup is closed");
			
			boolean submitForm2 = spd.clickSubmitFormButton();
			TestValidation.IsTrue(submitForm2, 
								  "CLICKED on Submit Form button", 
								  "Failed to Click on Submit Form button");
			
			boolean verifyPopupNote = spd.SubmitPopupNote.getText().contains("Note");
			TestValidation.IsTrue(verifyPopupNote, 
								  "VERIFIED Submit popup's note is 'Note'", 
								  "Failed to Verify Submit popup's note is 'Note'");
			
			boolean submitFormTask2 = spd.clickSubmitPopupOkButton();
			TestValidation.IsTrue(submitFormTask2, 
								  "SUBMITTED Form task " + formReqName3, 
								  "Failed to Submit Form task " + formReqName3);
			
			boolean openTask4 = spd.openTask(formReqName3, SUPP_TASK_TYPE.COMPLETE_FORM);
			TestValidation.IsFalse(openTask4, 
								  "VERIFIED Task " + formReqName3 + " is NOT present as it is submitted", 
								  "Failed to Verify presence of the task " + formReqName3);
			
			boolean logOutFromSP2 = hp.userLogout();
			TestValidation.IsTrue(logOutFromSP2, 
								  "User successfully LOGGED OUT from Supplier Portal", 
								  "Failed to Logout user from Supplier Portal");
			
			hp = lp.performLogin(adminUsername, adminPassword);
			TestValidation.IsTrue(!hp.error, 
								  "LOGGED IN with Admin user " + adminUsername, 
								  "Failed to login with Admin user " + adminUsername);
			
			ip = hp.clickInboxMenu();
			boolean verifyTaskExist = ip.selectFormTask(formReqName3);		
			TestValidation.IsTrue(verifyTaskExist, 
								  "OPENED Form task - " + formReqName3, 
								  "Failed to Open form task - " + formReqName3);

		}
		finally {
			TestValidation.IsTrue(hp.userLogout(), 
								  "User successfully LOGGED OUT from application", 
								  "Failed to Logout user from application");
		}
	}
	
	@Test(description="Inbox || Task History - Users can view Task History for any task in their inbox "
			+ "by clicking on the history icon")	
	public void TestCase_12052() throws Exception{
		try {
			
			DateTime dt = new DateTime();
			formDueByDate = dt.AddDaystoToddaysDate(3, "MM/dd/YYYY");
			timezoneCode = ump.getTimezoneCode(adminTimezone);
			currentDate = dt.getTodayDate("MM/dd/YYYY", dt.getTimezone(adminTimezone));
			
			hp = lp.performLogin(adminUsername, adminPassword);
			TestValidation.IsTrue(!hp.error, 
								  "LOGGED IN with Admin user " + adminUsername, 
								  "Failed to login with Admin user " + adminUsername);
			
			displayName = hp.getLoggedInUserDetails();
			if(displayName == ""){
				TCGFailureMsg = "Could NOT get display name for user - " + adminUsername;
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}
		
			// Create requirement for Supplier instance 
			mrqp = hp.clickRequirementsMenu();	
			boolean updateReq = mrqp.createSuppReqInExistingTemplate(suppTempName1,
					null,null,workGroupName,null,formName,formReqName4,null);
			TestValidation.IsTrue(updateReq, 
								  "UPDATED Supplier requirement " + suppTempName1 + " with Form task", 
								  "Failed to Update Supplier requirement " + suppTempName1 + " with Form task");
			
			boolean logOutFromSC1 = hp.userLogout();
			TestValidation.IsTrue(logOutFromSC1, 
								  "User successfully LOGGED OUT from Safety Chain application", 
								  "Failed to Logout user from Safety Chain application");
			
			hp = lp.performLogin(suppUN, GenericNewPassword);
			TestValidation.IsTrue(!hp.error, 
								  "LOGGED IN with Supplier user " + suppUN, 
								  "Failed to login with Supplier user " + suppUN);
			
			SupplierPortalPage spd = new SupplierPortalPage(driver);
			boolean searchTasks = spd.searchTaskInSuppPortal(formReqName4);
			TestValidation.IsTrue(searchTasks, 
								  "SEARCHED for Form task " + formReqName4, 
								  "Failed to Search for Form task " + formReqName4);
			
			boolean openHistory = spd.clickHistoryIcon();
			TestValidation.IsTrue(openHistory, 
								  "OPENED History for Form task " + formReqName4, 
								  "Failed to Open history for Form task " + formReqName4);
			
			hp.Sync();
			boolean verifyWindowTitle = spd.HistWindowTitle.getText().contains("Task History");
			TestValidation.IsTrue(verifyWindowTitle, 
								  "VERIFIED History window's title is 'Task History'", 
								  "Failed to Verify History window's title is 'Task History'");
			
			String expectedAssgnMsg = "Assigned to " + suppInstance1Value + " by " + displayName;
			boolean verifyAssgnMsg = spd.HistAssgnmntInfo.getText().contains(expectedAssgnMsg);
			TestValidation.IsTrue(verifyAssgnMsg, 
								  "VERIFIED Task assignment history info - " + expectedAssgnMsg, 
								  "Failed to Verify Task assignment history info");
			
			boolean verifyAssgnmntDate = spd.HistDateInfo.getText().contains(currentDate);
			TestValidation.IsTrue(verifyAssgnmntDate, 
								  "VERIFIED Task assignment date info - " + currentDate, 
								  "Failed to Verify Task assignment date info");
			
			boolean verifyAssgnmntTimezone = spd.HistTimeInfo.getText().contains(timezoneCode);
			TestValidation.IsTrue(verifyAssgnmntTimezone, 
								  "VERIFIED Task assignment timezone - " + timezoneCode, 
								  "Failed to Verify Task assignment timezone");
			
			boolean verifyDueByDate = spd.HistDueByInfo.getText().contains(formDueByDate);
			TestValidation.IsTrue(verifyDueByDate, 
								  "VERIFIED Task assignment Due by info - " + formDueByDate, 
								  "Failed to Verify Task assignment Due by info");
			
			boolean closeSuppHistWindow = spd.clickCloseHistButton();
			TestValidation.IsTrue(closeSuppHistWindow, 
								  "CLOSED History window opened for Form task " + formReqName4, 
								  "Failed to Close history window");
			
			String [] formTaskName = {formReqName4};
			
			HashMap<String, List<String>> fillData = new HashMap<String, List<String>>();
			fillData.put(numFieldName,Arrays.asList("10"));
			
			//Form object
			FormDetails fd = new FormDetails();
			fd.fieldDetails = fillData;
			//Field property assignment
			fd.isSubmit = false;
			fd.doNotSaveOrSubmit = true;
			boolean fillFormTask = spd.submitformTask(formTaskName, fd, submissionComment);
			TestValidation.IsTrue(fillFormTask, 
								  "FILLED Form task " + formReqName4, 
								  "Failed to Fill Form task " + formReqName4);
			
			boolean logOutFromSP = hp.userLogout();
			TestValidation.IsTrue(logOutFromSP, 
								  "User successfully LOGGED OUT from Supplier Portal", 
								  "Failed to Logout user from Supplier Portal");
			
			hp = lp.performLogin(adminUsername, adminPassword);
			TestValidation.IsTrue(!hp.error, 
								  "LOGGED IN with Admin user " + adminUsername, 
								  "Failed to login with Admin user " + adminUsername);
			
			InboxPage ip = hp.clickInboxMenu();
			boolean searchTask = ip.searchTaskName(formReqName4);
			TestValidation.IsTrue(searchTask, 
								  "SEARCHED Form task " + formReqName4, 
								  "Failed to Search Form task " + formReqName4);
			
			boolean openInboxHistory = ip.clickHistoryIcon();
			TestValidation.IsTrue(openInboxHistory, 
								  "OPENED History for Form task " + formReqName4, 
								  "Failed to Open history for Form task " + formReqName4);
			
			boolean verifyInboxWindowTitle = ip.HistWindowTitle.getText().contains("Task History");
			TestValidation.IsTrue(verifyInboxWindowTitle, 
								  "VERIFIED History window's title is 'Task History'", 
								  "Failed to Verify History window's title is 'Task History'");
			
			String expectedMsg1 = "Assigned to " + suppInstance1Value + " by " + displayName + "|" +
					 				timezoneCode  + "|" + currentDate  + "|" + formDueByDate;
			boolean verifyActualMsg1 = ip.verifyTaskHistoryPopupDetails(expectedMsg1);
			TestValidation.IsTrue(verifyActualMsg1, 
					  			  "VERIFIED Task History details whenever a task is Assigned", 
					  			  "Failed to verify the Task History details whenever a task is Assigned");
			
			String expectedMsg2 = "Submitted by " + udp.FirstName + " " + udp.LastName + " , " + udp.Title 
					+ " at " + suppInstance1Value + "|" + timezoneCode  + "|" + currentDate  + "|" + 
					formName + "|" + submissionComment;
			boolean verifyActualMsg2 = ip.verifyTaskHistoryPopupDetails(expectedMsg2);
			TestValidation.IsTrue(verifyActualMsg2, 
					  			  "VERIFIED the Task History details whenever a task is Submitted by Supplier user", 
					  			  "Failed to verify the Task History details whenever a task is Submitted by Supplier user");
			
			boolean closeInboxHistWindow = ip.closeHistoryPopup();
			TestValidation.IsTrue(closeInboxHistWindow, 
								  "CLOSED History window opened for Form task " + formReqName4, 
								  "Failed to Close history window");
			
		}
		finally {
			formReqName4 = CommonMethods.dynamicText("SForm4");
			TestValidation.IsTrue(hp.userLogout(), 
								  "User successfully LOGGED OUT from application", 
								  "Failed to Logout user from application");
		}
	}
	
	@AfterClass(alwaysRun = true)
	public void closeBrowser() throws InterruptedException {
		driver.close();
	}

}
