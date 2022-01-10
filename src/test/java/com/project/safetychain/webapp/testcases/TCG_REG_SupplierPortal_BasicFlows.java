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
import com.project.safetychain.api.models.support.Support.FormFieldParams;
import com.project.safetychain.api.models.support.Support.FormParams;
import com.project.safetychain.api.models.support.Support.RESOURCE_TYPES;
import com.project.safetychain.webapp.pageobjects.CommonPage;
import com.project.safetychain.webapp.pageobjects.DocumentManagementPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserFormsPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage;
import com.project.safetychain.webapp.pageobjects.HomePage;
import com.project.safetychain.webapp.pageobjects.LoginPage;
import com.project.safetychain.webapp.pageobjects.ManageLocationPage;
import com.project.safetychain.webapp.pageobjects.ManageRequirementPage;
import com.project.safetychain.webapp.pageobjects.ManageResourcePage.RESOURCETYPES;
import com.project.safetychain.webapp.pageobjects.ManageResourcePage.ResourceDetailParams;
import com.project.safetychain.webapp.pageobjects.ManageResourcePage;
import com.project.safetychain.webapp.pageobjects.ResourceDesignerPage;
import com.project.safetychain.webapp.pageobjects.UserManagerPage;
import com.project.safetychain.webapp.pageobjects.WorkGroupsPage;
import com.project.safetychain.webapp.pageobjects.ManageLocationPage.LocationInstanceParams;
import com.project.safetychain.webapp.pageobjects.ResourceDesignerPage.CATEGORYTYPES;
import com.project.safetychain.webapp.pageobjects.SupplierPortalPage;
import com.project.safetychain.webapp.pageobjects.SupplierPortalPageLocators;
import com.project.safetychain.webapp.pageobjects.SupplierPortalPage.SUPP_PRTL_FIELDS;
import com.project.safetychain.webapp.pageobjects.UserManagerPage.UsrDetailParams;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.testbase.SupportingClasses.ExecutionMode;
import com.project.utilities.ApiUtils;
import com.project.utilities.CommonMethods;
import com.project.utilities.ControlActions;
import com.project.utilities.DateTime;
import com.project.utilities.CommonMethods.SORTING;

public class TCG_REG_SupplierPortal_BasicFlows extends TestBase {
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

	public static String locationCategoryValue;
	public static String locationInstanceValue;
	public static String suppCategoryValue;
	public static String suppInstanceValue;
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
	public static String ackreqname;
	public static String docreqname;
	public static String formreqname;
	public static String supptempname;
	
	public static String currentDate;
	public static String formDueByDate;
	public static String docDueByDate;
	public static String ackDueByDate;
	public static String timezoneCode = null;


	@BeforeClass(alwaysRun = true)
	public void groupInit() throws InterruptedException {

		locationCategoryValue = locationName;
		locationInstanceValue = locationName;
		suppCategoryValue = CommonMethods.dynamicText("Supp_Cat");
		suppInstanceValue = CommonMethods.dynamicText("00000SuppInst");
		suppUN = CommonMethods.dynamicText("SuppUser");
		formName = CommonMethods.dynamicText("Automation_QuestForm");	
		workGroupName = CommonMethods.dynamicText("WG");
		doctype = CommonMethods.dynamicText("Doc");
		supptempname = CommonMethods.dynamicText("SuppReqmt"); 
		ackreqname = CommonMethods.dynamicText("Ack");		
		docreqname = CommonMethods.dynamicText("DocUpload");
		formreqname = CommonMethods.dynamicText("Form");

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
			resourceCatMap.put(suppCategoryValue, Arrays.asList(suppInstanceValue));

			formCreationWrapper.Resource_Location_CreationWrapper(resourceCatMap, LocationMap, true, userList,
					RESOURCE_TYPES.SUPPLIERS);

			// ------------------------------------------------------------------------------------------------
			// API - Form Creation - Check
			// API - Add fields to form
			FormFieldParams numericField = new FormFieldParams();
			numericField.DPTFieldType = DPT_FIELD_TYPES.NUMERIC;
			numericField.Name = "Numeric";

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
			fp.ResourceInstanceNm = suppInstanceValue;
			fp.formElements = Arrays.asList(numericField);
			fp.SuppliersResources = resourceCatMap;

			formCreationWrapper.API_Wrapper_Forms(fp);

			// ------------------------------------------------------------------------------------------------
			// To create a Work Group
			taskCreationWrapper.create_WorkGroup(workGroupName);

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
			if(!mrqp.createSupplierRequirement(supptempname,doctype,ackreqname,workGroupName,docreqname,formName,
					formreqname,suppInstanceValue)) {
				TCGFailureMsg = "Could not create requirement for supplier " + suppInstanceValue;
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}
			
			UsrDetailParams udp = new UsrDetailParams(); 
			udp.Suppliers = Arrays.asList(suppInstanceValue);
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
			categories.put(CATEGORYTYPES.LOCATION, locationCategoryValue);
			categories.put(CATEGORYTYPES.SUPPLIERS, suppCategoryValue);
			rdp = hp.clickResourceDesignerMenu();
			if(!rdp.createCategory(categories)) {
				TCGFailureMsg = "Could NOT create Resource categories";
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}

			//Location instance
			HashMap<String,Boolean> locInstance = new HashMap<String, Boolean>();
			locInstance.put(locationInstanceValue, true);
			LocationInstanceParams lip = new LocationInstanceParams();
			lip.CategoryName = locationCategoryValue;
			lip.NumberFieldValue = 10;
			lip.TextFieldValue = "XYZ";
			lip.InstanceName = locInstance;
			ManageLocationPage mlp = hp.clickLocationsMenu();
			if(!mlp.createLocation(lip)) {
				TCGFailureMsg = "Could NOT create Location instance";
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}
			
			//Resource creation
			HashMap<String,Boolean> instances = new HashMap<String, Boolean>();
			instances.put(suppInstanceValue, true);
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
			if(!fdp.createAndReleaseForm(formType, formName,"Suppliers", suppCategoryValue, suppInstanceValue)) {
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

			// Create requirement for Supplier instance
			mrqp = hp.clickRequirementsMenu();	
			if(!mrqp.createSupplierRequirement(supptempname,doctype,ackreqname,workGroupName,docreqname,formName,
					formreqname,suppInstanceValue)) {
				TCGFailureMsg = "Could not create requirement for supplier " + suppInstanceValue;
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}

			UsrDetailParams udp = new UsrDetailParams(); 
			udp.Suppliers = Arrays.asList(suppInstanceValue);
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
		}
		else {
			TCGFailureMsg = "Improper Execution Mode is set - " + executionMode;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
		
	}

	@Test(description="Supplier Portal || Questionnaire - Restrict Supplier 'Resource' value")	
	public void TestCase_10097() throws Exception{
		try {

			WebElement SuppPrtlTaskDets = null;
			
			SupplierPortalPage spd = new SupplierPortalPage(driver);
			boolean searchForm = spd.searchTaskInSuppPortal(formreqname);
			TestValidation.IsTrue(searchForm, 
								  "SEARCHED for form task " + formreqname, 
								  "Failed to Search for form task " + formreqname);
	
			String getRsrcName1 = spd.getSuppTaskDetails(SUPP_PRTL_FIELDS.RESOURCE);
			boolean verifyRsrcName1 = getRsrcName1.equals(suppInstanceValue);
			TestValidation.IsTrue(verifyRsrcName1, 
								  "VERIFIED Supplier resource name " + suppInstanceValue + " for Form Task", 
								  "Failed to Verify Supplier resource name " + suppInstanceValue + " for Form Task");
			
			SuppPrtlTaskDets = controlActions.perform_GetElementByXPath(SupplierPortalPageLocators.SUPP_PRTL_TASK_DETS, 
					"TASKDETAIL", suppInstanceValue);
			boolean verifyRsrcTag = SuppPrtlTaskDets.getTagName().equals("span");
			TestValidation.IsTrue(verifyRsrcTag, 
								  "VERIFIED resource name is un-editable text", 
								  "Failed to Verify resource name is un-editable text");
			
			boolean searchDoc = spd.searchTaskInSuppPortal(docreqname);
			TestValidation.IsTrue(searchDoc, 
								  "SEARCHED for document task " + docreqname, 
								  "Failed to Search for document task " + docreqname);
	
			String getRsrcName2 = spd.getSuppTaskDetails(SUPP_PRTL_FIELDS.RESOURCE);
			boolean verifyRsrcName2 = getRsrcName2.equals(suppInstanceValue);
			TestValidation.IsTrue(verifyRsrcName2, 
								  "VERIFIED Supplier resource name " + suppInstanceValue + " for Document Task", 
								  "Failed to Verify Supplier resource name " + suppInstanceValue + " for Document Task");
	
			boolean searchAck = spd.searchTaskInSuppPortal(ackreqname);
			TestValidation.IsTrue(searchAck, 
								  "SEARCHED for acknowledgment task " + ackreqname, 
								  "Failed to Search for acknowledgment task " + ackreqname);
	
			String getRsrcName3 = spd.getSuppTaskDetails(SUPP_PRTL_FIELDS.RESOURCE);
			boolean verifyRsrcName3 = getRsrcName3.equals(suppInstanceValue);
			TestValidation.IsTrue(verifyRsrcName3, 
								  "VERIFIED Supplier resource name " + suppInstanceValue + " for Acknowledgment Task", 
								  "Failed to Verify Supplier resource name " + suppInstanceValue + " for Acknowledgment Task");
		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				TestValidation.Fail(TCGFailureMsg);
			}
		}
	}	
	
	@Test(description="Supplier Portal || Received On and Due By date/time should show dates in mm/dd/yyyy format")	
	public void TestCase_16191() throws Exception{
		try {

			DateTime dt = new DateTime();
			formDueByDate = dt.AddDaystoToddaysDate(3, "MM/dd/YYYY");
			docDueByDate = dt.AddDaystoToddaysDate(2, "MM/dd/YYYY");
			ackDueByDate = dt.AddDaystoToddaysDate(1, "MM/dd/YYYY");
			timezoneCode = ump.getTimezoneCode(adminTimezone);
			currentDate = dt.getTodayDate("MM/dd/YYYY", dt.getTimezone(adminTimezone));
			
			SupplierPortalPage spd = new SupplierPortalPage(driver);
			boolean searchForm = spd.searchTaskInSuppPortal(formreqname);
			TestValidation.IsTrue(searchForm, 
								  "SEARCHED for form task " + formreqname, 
								  "Failed to Search for form task " + formreqname);
	
			String getRcvdOnDate1 = spd.getSuppTaskDetails(SUPP_PRTL_FIELDS.RECEIVED_ON);
			boolean verifyRcvdOnDate1 = getRcvdOnDate1.contains(currentDate) && getRcvdOnDate1.contains(timezoneCode);
			TestValidation.IsTrue(verifyRcvdOnDate1, 
								  "VERIFIED Supplier Received on value is '" + getRcvdOnDate1 + "' for Form Task", 
								  "Failed to Verify Supplier Received on value for Form Task");
			
			String getDueByDate1 = spd.getSuppTaskDetails(SUPP_PRTL_FIELDS.DUE_BY);
			boolean verifyDueByDate1 = getDueByDate1.contains(formDueByDate) && getDueByDate1.contains(timezoneCode);
			TestValidation.IsTrue(verifyDueByDate1, 
								  "VERIFIED Supplier Due by value is '" + getDueByDate1 + "' for Form Task", 
								  "Failed to Verify Supplier Due by value for Form Task");
			
			boolean searchDoc = spd.searchTaskInSuppPortal(docreqname);
			TestValidation.IsTrue(searchDoc, 
								  "SEARCHED for document task " + docreqname, 
								  "Failed to Search for document task " + docreqname);
	
			String getRcvdOnDate2 = spd.getSuppTaskDetails(SUPP_PRTL_FIELDS.RECEIVED_ON);
			boolean verifyRcvdOnDate2 = getRcvdOnDate2.contains(currentDate) && getRcvdOnDate2.contains(timezoneCode);
			TestValidation.IsTrue(verifyRcvdOnDate2, 
								  "VERIFIED Supplier Received on value is '" + getRcvdOnDate2 + "' for Document Task", 
								  "Failed to Verify Supplier Received on value for Document Task");
			
			String getDueByDate2 = spd.getSuppTaskDetails(SUPP_PRTL_FIELDS.DUE_BY);
			boolean verifyDueByDate2 = getDueByDate2.contains(docDueByDate) && getDueByDate2.contains(timezoneCode);
			TestValidation.IsTrue(verifyDueByDate2, 
								  "VERIFIED Supplier Due by value is '" + getDueByDate2 + "' for Document Task", 
								  "Failed to Verify Supplier Due by value for Document Task");
	
			boolean searchAck = spd.searchTaskInSuppPortal(ackreqname);
			TestValidation.IsTrue(searchAck, 
								  "SEARCHED for acknowledgment task " + ackreqname, 
								  "Failed to Search for acknowledgment task " + ackreqname);
	
			String getRcvdOnDate3 = spd.getSuppTaskDetails(SUPP_PRTL_FIELDS.RECEIVED_ON);
			boolean verifyRcvdOnDate3 = getRcvdOnDate3.contains(currentDate) && getRcvdOnDate3.contains(timezoneCode);
			TestValidation.IsTrue(verifyRcvdOnDate3, 
								  "VERIFIED Supplier Received on value is '" + getRcvdOnDate3 + "' for Acknowledgment Task", 
								  "Failed to Verify Supplier Received on value for Acknowledgment Task");
			
			String getDueByDate3 = spd.getSuppTaskDetails(SUPP_PRTL_FIELDS.DUE_BY);
			boolean verifyDueByDate3 = getDueByDate3.contains(ackDueByDate) && getDueByDate3.contains(timezoneCode);
			TestValidation.IsTrue(verifyDueByDate3, 
								  "VERIFIED Supplier Due by value is '" + getDueByDate2 + "' for Acknowledgment Task", 
								  "Failed to Verify Supplier Due by value for Acknowledgment Task");
		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				TestValidation.Fail(TCGFailureMsg);
			}
		}
	}	
	
	@Test(description="Supplier Portal - Inbox - User can sort any column in the task list in ascending "
			+ "or descending order by clicking on the column header")	
	public void TestCase_32997() throws Exception{
		try {
			SupplierPortalPage spd = new SupplierPortalPage(driver);
			boolean dueByDesc = spd.DueByColmnName.getAttribute("class").contains("desc");
			TestValidation.IsTrue(dueByDesc, 
								  "VERFIED Due By column is by default sorted in Descending order", 
								  "Failed to Verify Due By column is by default sorted in Descending order");
			
			boolean sortAscTaskName = spd.clickOnSuppColumn(SUPP_PRTL_FIELDS.TASK_NAME);
			TestValidation.IsTrue(sortAscTaskName, 
								  "APPLIED Ascending sort to column - Task Name", 
								  "Failed to Apply Ascending sort to column - Task Name");
	
			String verifySortAscTaskName = CommonMethods.verifySortingForColumn(spd.getListOfElementsForSupp(SUPP_PRTL_FIELDS.TASK_NAME));
			TestValidation.IsTrue(verifySortAscTaskName.equals(SORTING.ASC), 
								  "VERIFIED values of the column - Task Name are sorted in Ascending", 
								  "Failed to verify that values of the column - Task Name are sorted in Ascending");
			
			boolean sortDscTaskName = spd.clickOnSuppColumn(SUPP_PRTL_FIELDS.TASK_NAME);
			TestValidation.IsTrue(sortDscTaskName, 
								  "APPLIED Descending sort to column - Task Name", 
								  "Failed to Apply Descending sort to column - Task Name");
	
			String verifySortDscTaskName = CommonMethods.verifySortingForColumn(spd.getListOfElementsForSupp(SUPP_PRTL_FIELDS.TASK_NAME));
			TestValidation.IsTrue(verifySortDscTaskName.equals(SORTING.DSC), 
								  "VERIFIED values of the column - Task Name are sorted in Descending", 
								  "Failed to verify that values of the column - Task Name are sorted in Descending");
			
			String verifySortEqlRsrcName = CommonMethods.verifySortingForColumn(spd.getListOfElementsForSupp(SUPP_PRTL_FIELDS.RESOURCE));
			TestValidation.IsTrue(verifySortEqlRsrcName.equals(SORTING.EQL), 
								  "VERIFIED values of the column - Resource are equal/same irrespective of sorting", 
								  "Failed to verify that values of the column - Resource");
			
			String verifySortEqlRecvdOn = CommonMethods.verifySortingForColumn(spd.getListOfElementsForSupp(SUPP_PRTL_FIELDS.RECEIVED_ON));
			TestValidation.IsTrue(verifySortEqlRecvdOn.equals(SORTING.EQL), 
								  "VERIFIED values of the column - Received On are equal/same irrespective of sorting", 
								  "Failed to verify that values of the column - Received On");
			
			boolean sortAscDueBy = spd.clickOnSuppColumn(SUPP_PRTL_FIELDS.DUE_BY);
			TestValidation.IsTrue(sortAscDueBy, 
								  "APPLIED Ascending sort to column - Due By", 
								  "Failed to Apply Ascending sort to column - Due By");
	
			String verifySortAscDueBy = CommonMethods.verifySortingForColumn(spd.getListOfElementsForSupp(SUPP_PRTL_FIELDS.DUE_BY));
			TestValidation.IsTrue(verifySortAscDueBy.equals(SORTING.ASC), 
								  "VERIFIED values of the column - Due By are sorted in Ascending", 
								  "Failed to verify that values of the column - Due By are sorted in Ascending");
			
			boolean sortDscDueBy = spd.clickOnSuppColumn(SUPP_PRTL_FIELDS.DUE_BY);
			TestValidation.IsTrue(sortDscDueBy, 
								  "APPLIED Descending sort to column - Due By", 
								  "Failed to Apply Descending sort to column - Due By");
	
			String verifySortDscDueBy = CommonMethods.verifySortingForColumn(spd.getListOfElementsForSupp(SUPP_PRTL_FIELDS.DUE_BY));
			TestValidation.IsTrue(verifySortDscDueBy.equals(SORTING.DSC), 
								  "VERIFIED values of the column - Due By are sorted in Descending", 
								  "Failed to verify that values of the column - Due By are sorted in Descending");
		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				TestValidation.Fail(TCGFailureMsg);
			}
		}
	}

	@Test(description="Supplier Portal - Inbox - The task list has the following columns: "
			+ "Status (icon), Task Type (icon), Task Name, Received On, Due By")	
	public void TestCase_32998() throws Exception{
		try {

			WebElement SuppColmnNames = null;
  			String regex = "^(1[0-2]|0[1-9])/(3[01]|[12][0-9]|0[1-9])/[0-9]{4} ([01]\\d|2[0-3]):?[0-5]\\d" + " [A-Z]{3}$";
  			SupplierPortalPage spd = new SupplierPortalPage(driver);
			
			SuppColmnNames = controlActions.perform_GetElementByXPath(SupplierPortalPageLocators.SUPP_COLMN_NAMES, 
					"COLUMNNAME", SUPP_PRTL_FIELDS.TASK_NAME);
			boolean verifyTaskNameDisplayed = SuppColmnNames.isDisplayed();
			TestValidation.IsTrue(verifyTaskNameDisplayed, 
								  "VERIFIED column - Task Name is displayed", 
								  "Failed to Verify column - Task Name is displayed");
			
			SuppColmnNames = controlActions.perform_GetElementByXPath(SupplierPortalPageLocators.SUPP_COLMN_NAMES, 
					"COLUMNNAME", SUPP_PRTL_FIELDS.RESOURCE);
			boolean verifyRsrcDisplayed = SuppColmnNames.isDisplayed();
			TestValidation.IsTrue(verifyRsrcDisplayed, 
								  "VERIFIED column - Resource is displayed", 
								  "Failed to Verify column - Resource is displayed");
			
			SuppColmnNames = controlActions.perform_GetElementByXPath(SupplierPortalPageLocators.SUPP_COLMN_NAMES, 
					"COLUMNNAME", SUPP_PRTL_FIELDS.RECEIVED_ON);
			boolean verifyRcvdOnDisplayed = SuppColmnNames.isDisplayed();
			TestValidation.IsTrue(verifyRcvdOnDisplayed, 
								  "VERIFIED column - Received On is displayed", 
								  "Failed to Verify column - Received On is displayed");
			
			SuppColmnNames = controlActions.perform_GetElementByXPath(SupplierPortalPageLocators.SUPP_COLMN_NAMES, 
					"COLUMNNAME", SUPP_PRTL_FIELDS.DUE_BY);
			boolean verifyDueByDisplayed = SuppColmnNames.isDisplayed();
			TestValidation.IsTrue(verifyDueByDisplayed, 
								  "VERIFIED column - Due By is displayed", 
								  "Failed to Verify column - Due By is displayed");
			
			boolean verifyReqStatus = spd.SuppStatusColmnSymbl.get(0).getAttribute("class").contains("duelater")
									&& spd.SuppStatusColmnSymbl.get(1).getAttribute("class").contains("duelater")
									&& spd.SuppStatusColmnSymbl.get(2).getAttribute("class").contains("duelater");
			TestValidation.IsTrue(verifyReqStatus, 
								  "VERIFIED requirements' status as - Due Later", 
								  "Failed to Verify requirements' status as - Due Later");
			
			boolean searchForm = spd.searchTaskInSuppPortal(formreqname);
			TestValidation.IsTrue(searchForm, 
								  "SEARCHED for form task " + formreqname, 
								  "Failed to Search for form task " + formreqname);
	
			boolean iconDisplayed1 = spd.FormReqIcon.isDisplayed();
			TestValidation.IsTrue(iconDisplayed1, 
								  "VERIFIED file icon is displayed for Form type requirement", 
								  "Failed to Verify icon is displayed for Form type requirement");
			
			String getRcvdOnDate1 = spd.getSuppTaskDetails(SUPP_PRTL_FIELDS.RECEIVED_ON);
			boolean verifyRcvdOnDatePattern1 = CommonMethods.validateByRegex(getRcvdOnDate1,regex);
			TestValidation.IsTrue(verifyRcvdOnDatePattern1, 
								  "VERIFIED Supplier Received on value is of pattern 'MM/dd/YYYY HH:MM TimezoneCode'", 
								  "Failed to Verify Supplier Received on value's pattern");
			
			String getDueByDate1 = spd.getSuppTaskDetails(SUPP_PRTL_FIELDS.DUE_BY);
			boolean verifyDueByDatePattern1 = CommonMethods.validateByRegex(getDueByDate1,regex);
			TestValidation.IsTrue(verifyDueByDatePattern1, 
								  "VERIFIED Supplier Due by value is of pattern 'MM/dd/YYYY HH:MM TimezoneCode'", 
								  "Failed to Verify Supplier Due by value's pattern");
			
			boolean searchDoc = spd.searchTaskInSuppPortal(docreqname);
			TestValidation.IsTrue(searchDoc, 
								  "SEARCHED for document task " + docreqname, 
								  "Failed to Search for document task " + docreqname);
	
			boolean iconDisplayed2 = spd.DocUploadReqIcon.isDisplayed();
			TestValidation.IsTrue(iconDisplayed2, 
								  "VERIFIED Upload icon is displayed for Document Upload type requirement", 
								  "Failed to Verify icon is displayed for Document Upload type requirement");
			
			String getRcvdOnDate2 = spd.getSuppTaskDetails(SUPP_PRTL_FIELDS.RECEIVED_ON);
			boolean verifyRcvdOnDatePattern2 = CommonMethods.validateByRegex(getRcvdOnDate2,regex);
			TestValidation.IsTrue(verifyRcvdOnDatePattern2, 
								  "VERIFIED Supplier Received on value is of pattern 'MM/dd/YYYY HH:MM TimezoneCode'", 
								  "Failed to Verify Supplier Received on value's pattern");
			
			String getDueByDate2 = spd.getSuppTaskDetails(SUPP_PRTL_FIELDS.DUE_BY);
			boolean verifyDueByDatePattern2 = CommonMethods.validateByRegex(getDueByDate2,regex);
			TestValidation.IsTrue(verifyDueByDatePattern2, 
								  "VERIFIED Supplier Due by value is of pattern 'MM/dd/YYYY HH:MM TimezoneCode'", 
								  "Failed to Verify Supplier Due by value's pattern");
			
			boolean searchAck = spd.searchTaskInSuppPortal(ackreqname);
			TestValidation.IsTrue(searchAck, 
								  "SEARCHED for acknowledgment task " + ackreqname, 
								  "Failed to Search for acknowledgment task " + ackreqname);	
			
			boolean iconDisplayed3 = spd.AckReqIcon.isDisplayed();
			TestValidation.IsTrue(iconDisplayed3, 
								  "VERIFIED flag icon is displayed for Acknowledgement type requirement", 
								  "Failed to Verify icon is displayed for Acknowledgement type requirement");
			
			String getRcvdOnDate3 = spd.getSuppTaskDetails(SUPP_PRTL_FIELDS.RECEIVED_ON);
			boolean verifyRcvdOnDatePattern3 = CommonMethods.validateByRegex(getRcvdOnDate3,regex);
			TestValidation.IsTrue(verifyRcvdOnDatePattern3, 
								  "VERIFIED Supplier Received on value is of pattern 'MM/dd/YYYY HH:MM TimezoneCode'", 
								  "Failed to Verify Supplier Received on value's pattern");
			
			String getDueByDate3 = spd.getSuppTaskDetails(SUPP_PRTL_FIELDS.DUE_BY);
			boolean verifyDueByDatePattern3 = CommonMethods.validateByRegex(getDueByDate3,regex);
			TestValidation.IsTrue(verifyDueByDatePattern3, 
								  "VERIFIED Supplier Due by value is of pattern 'MM/dd/YYYY HH:MM TimezoneCode'", 
								  "Failed to Verify Supplier Due by value's pattern");
		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				TestValidation.Fail(TCGFailureMsg);
			}
		}
	}
	
	@Test(description="Supplier Portal - Inbox - User can search the Task Name column via a universal search")	
	public void TestCase_32999() throws Exception{
		try {
			SupplierPortalPage spd = new SupplierPortalPage(driver);
			
			boolean searchTxtPlaceholder = spd.TaskNameSearchInp.getAttribute("placeholder").equals("Search");
			TestValidation.IsTrue(searchTxtPlaceholder, 
								  "VERIFIED Search text box's placeholder as Search", 
								  "Failed to Verify Search text box's placeholder as Search");
			
			boolean emptySearchTxt = spd.TaskNameSearchInp.getAttribute("class").contains("ng-empty");
			TestValidation.IsTrue(emptySearchTxt, 
								  "VERIFIED Search text box is empty", 
								  "Failed to Verify Search text box is empty");
			
			boolean setTxtForSearch1 = spd.setSearchTxt(formreqname);
			boolean notEmptySearchTxt = spd.TaskNameSearchInp.getAttribute("class").contains("ng-not-empty");
			TestValidation.IsTrue(setTxtForSearch1 && notEmptySearchTxt, 
								  "VERIFIED Search text box is not empty", 
								  "Failed to Verify Search text box is not empty");
			
			boolean clickSpyGlassBtn = spd.clickSearchBtn();
			TestValidation.IsTrue(clickSpyGlassBtn, 
								  "SEARCHED for form task using Spy glass button" + formreqname, 
								  "Failed to Search for form task using Spy glass button" + formreqname);
			
			String getTaskName1 = spd.getSuppTaskDetails(SUPP_PRTL_FIELDS.TASK_NAME);
			boolean verifyTaskName1 = getTaskName1.equals(formreqname);
			TestValidation.IsTrue(verifyTaskName1, 
								  "VERIFIED Supplier task name " + formreqname + " for Form Task", 
								  "Failed to Verify Supplier task name " + formreqname + " for Form Task");
			
			int entryCount1 = spd.SuppGridRowCount.size();
			TestValidation.IsTrue(entryCount1==1, 
								  "VERIFIED Supplier grid is displaying 1 entry", 
								  "Failed to Verify Supplier grid entries");
	
			boolean searchTasks = spd.searchTaskInSuppPortal(suppInstanceValue);
			TestValidation.IsTrue(searchTasks, 
								  "SEARCHED for tasks with resource " + suppInstanceValue, 
								  "Failed to Search for tasks with resource " + suppInstanceValue);	
			
			int entryCount2 = spd.SuppGridRowCount.size();
			TestValidation.IsTrue(entryCount2==3, 
								  "VERIFIED Supplier grid is displaying 3 entries", 
								  "Failed to Verify Supplier grid entries");
			
			boolean setTxtForSearch2 = spd.setSearchTxt(docreqname);
			TestValidation.IsTrue(setTxtForSearch2, 
								  "VERIFIED Search text box set to " + docreqname, 
								  "Failed to Verify Search text box is set to " + docreqname);
			
			controlActions.actionEnter();
			String getTaskName2 = spd.getSuppTaskDetails(SUPP_PRTL_FIELDS.TASK_NAME);
			boolean verifyTaskName2 = getTaskName2.equals(docreqname);
			TestValidation.IsTrue(verifyTaskName2, 
								  "VERIFIED Supplier task name " + docreqname + " for Document Task", 
								  "Failed to Verify Supplier task name " + docreqname + " for Document Task");
			
			int entryCount3 = spd.SuppGridRowCount.size();
			TestValidation.IsTrue(entryCount3==1, 
								  "VERIFIED Supplier grid is displaying 1 entry", 
								  "Failed to Verify Supplier grid entries");
			
			boolean clearSearch = spd.clearSearchText();
			TestValidation.IsTrue(clearSearch, 
								  "CLEARED text from Search textbox", 
								  "Failed to Clear text from Search textbox");
			
			hp.Sync();
			int entryCount4 = spd.SuppGridRowCount.size();
			TestValidation.IsTrue(entryCount4==3, 
								  "VERIFIED Supplier grid is displaying 3 entries", 
								  "Failed to Verify Supplier grid entries");
		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				TestValidation.Fail(TCGFailureMsg);
			}
		}
	}

	@Test(description="Supplier Portal - Inbox - Task Name is bold for unopened tasks")	
	public void TestCase_33001() throws Exception{
		try {
			SupplierPortalPage spd = new SupplierPortalPage(driver);
			WebElement FormTaskCellInGrid1 = controlActions.perform_GetElementByXPath(SupplierPortalPageLocators.TASK_CELL_IN_GRID, 
					"TASK_NAME", formreqname);
			boolean verifyBeforeFormTag = FormTaskCellInGrid1.getTagName().equals("strong");
			TestValidation.IsTrue(verifyBeforeFormTag, 
								  "VERIFIED that task name is in Bold", 
								  "Failed to Verify task name is in Bold or not");
			
			boolean selectedTask = spd.selectTask(formreqname);
			TestValidation.IsTrue(selectedTask, 
								  "SELECTED form task " + formreqname, 
								  "Failed to Select form task " + formreqname);
			
			WebElement TaskRowInGrid1 = controlActions.perform_GetElementByXPath(SupplierPortalPageLocators.TASK_ROW_IN_GRID, 
					"TASK_NAME", formreqname);
			boolean verifySelected1 = TaskRowInGrid1.getAttribute("class").contains("selected");
			TestValidation.IsTrue(verifySelected1, 
								  "VERIFIED form task " + formreqname + " is selected", 
								  "Failed to Verify selection of form task " + formreqname);
			
			WebElement TaskRowInGrid2 = controlActions.perform_GetElementByXPath(SupplierPortalPageLocators.TASK_ROW_IN_GRID, 
					"TASK_NAME", docreqname);
			boolean verifySelected2 = TaskRowInGrid2.getAttribute("class").contains("selected");
			TestValidation.IsFalse(verifySelected2, 
								  "VERIFIED document task " + docreqname + " is NOT selected", 
								  "Failed to Verify selection of form task " + docreqname);
			
			boolean openSuppliersCompleteFormTask = spd.openCompleteForm(formName, formreqname, suppInstanceValue);
			TestValidation.IsTrue(openSuppliersCompleteFormTask, 
								  "OPENED 'Supplier' complete form task", 
								  "Failed to Open 'Suppliers' complete form task");
			
			boolean closeForm = spd.clickFormCancelButton();
			TestValidation.IsTrue(closeForm, 
								  "CLOSED form task " + formreqname, 
								  "Failed to Close form task " + formreqname);
			
			WebElement FormTaskCellInGrid2 = controlActions.perform_GetElementByXPath(SupplierPortalPageLocators.TASK_CELL_IN_GRID, 
					"TASK_NAME", formreqname);
			boolean verifyAfterFormTag = FormTaskCellInGrid2.getTagName().equals("span");
			TestValidation.IsTrue(verifyAfterFormTag, 
								  "VERIFIED that once task is opened the text remains no longer in Bold", 
								  "Failed to Verify task name is in Bold or not");
		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				TestValidation.Fail(TCGFailureMsg);
			}
		}
	}	
	
	@Test(description="Supplier Portal - Inbox - User can manually refresh the task list by clicking the \"Refresh\" button")	
	public void TestCase_33003() throws Exception{
		try {
			SupplierPortalPage spd = new SupplierPortalPage(driver);
			List<String> taskList1 = spd.getListOfValuesForSupp(SUPP_PRTL_FIELDS.TASK_NAME);	
			boolean verifyTasks1 = taskList1.contains(ackreqname) && taskList1.contains(docreqname) && taskList1.contains(formreqname);
			TestValidation.IsTrue(verifyTasks1, 
								  "All 3 tasks assigned to Template - " + supptempname + " are DISPLAYED", 
								  "Failed to if All 3 tasks assigned to Template - " + supptempname + " are displayed");
			
			boolean refreshTasks = spd.clickSuppPortalRefreshButton();
			TestValidation.IsTrue(refreshTasks, 
								  "REFRESHED the Supplier Portal grid", 
								  "Failed to Refresh the Supplier Portal grid");
			
			List<String> taskList2 = spd.getListOfValuesForSupp(SUPP_PRTL_FIELDS.TASK_NAME);	
			boolean verifyTasks2 = taskList2.contains(ackreqname) && taskList2.contains(docreqname) && taskList2.contains(formreqname);
			TestValidation.IsTrue(verifyTasks2, 
								  "VERIFIED All 3 tasks assigned to Template - " + supptempname + " are displayed", 
								  "Failed to Verify All 3 tasks assigned to Template - " + supptempname);
			
			boolean toolipDisplayed = spd.verifySuppPortalRefreshTooltip();
			TestValidation.IsTrue(toolipDisplayed, 
								  "Tooltip is DISPLAYED for Refresh button", 
								  "Tooltip is NOT DISPLAYED for Refresh button");
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
