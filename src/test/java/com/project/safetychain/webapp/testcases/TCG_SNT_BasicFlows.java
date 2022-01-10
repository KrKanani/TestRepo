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
import com.project.safetychain.webapp.pageobjects.CommonPage.TIMEZONE;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.COLUMNHEADER;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.COLUMNSETTING;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.FSQARESOURCE;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.FSQATAB;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.AGGREGATE_FUNCTION;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.ElementProperties;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FIELD_TYPES;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FORMRESOURCETYPES;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FORMTYPES;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FormDesignParams;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FormFieldParams;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.IDENTIFIER_INPUT_TYPE;
import com.project.safetychain.webapp.pageobjects.ManageLocationPage.LocationInstanceParams;
import com.project.safetychain.webapp.pageobjects.ManageResourcePage.RESOURCETYPES;
import com.project.safetychain.webapp.pageobjects.ManageResourcePage.ResourceDetailParams;
import com.project.safetychain.webapp.pageobjects.ResourceDesignerPage.CATEGORYTYPES;
import com.project.safetychain.webapp.pageobjects.TaskSchedulerPage.TaskScheduleDetails;
import com.project.safetychain.webapp.pageobjects.UserManagerPage.UsrDetailParams;
import com.project.safetychain.webapp.testcases.support.FormOperations;
import com.project.safetychain.webapp.testcases.support.FormOperations.FormDetails;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.utilities.CommonMethods;
import com.project.utilities.ControlActions;

public class TCG_SNT_BasicFlows extends TestBase{

	ControlActions controlActions;
	HomePage hp;
	UserManagerPage ump;
	WorkGroupsPage wgp;
	RolesManagerPage rmp;
	FormDesignerPage fdp;
	ManageRequirementPage mrp;
	SupplierPortalPage supplierPage;
	LoginPage lp;
	InboxPage inbox;
	DocumentManagementPage dmp;
	TaskScheduleDetails tsd;	
	TaskSchedulerPage taskScheduler;
	FSQABrowserPage fbp;
	FSQABrowserFormsPage fbForms;
	ManageResourcePage manageResource;

	public static String locationCategoryValue, itemCategoryValue, supplierCategoryValue;
	public static String locationInstanceValue, itemInstanceValue, supplierInstanceValue;
	public static String supplierUserName, supplierPassword, supplierNewPassword;
	public static String userUN, userFN, userLN;
	public static String suppUN, suppFN, suppLN;
	public static List<String> userLocation, userRole;
	public static String workgroupName;
	public static String noteText, docTypeName;
	public static String chkFormName, adtFormName, qstFormName;
	public static String chkFreeTxt, ParaTxt, chkSelectOne, SelectMultiple, Numeric, chkDate, chkTime, chkDateTime;
	public static String chkAggregate, chkIdentifier, chkGroup, section;
	public static String fieldMin = "1", fieldTar = "2", fieldMax = "3", fieldUOM = "KG";
	public static String expressionThen = "Good", expressionElse = "Bad";
	public static String field4optionswithWeight[][] = {{"1","200"},{"2","400"}};

	//requirement type creation//
	public static String suppdocreqname,suppdocreqname1,suppformreqname,suppformreqname1;
	public static String itemdocreqname,itemdocreqname1,itemformreqname,itemformreqname1;
	public static String supptempname,itemtempname,ackreqname;
	//document upload//
	public static String filePath;
	public static String image;
	public static String document;
	public static String uploadassigndoc;
	//document upload task // 	
	public static String taskname;
	public static String documentName = "upload.png";
	public static String assigndoc = "assigntask.png";

	public static String taskschedulertaskname;
	public static String TaskDescription;

	@BeforeClass(alwaysRun = true)
	public void groupInit() throws InterruptedException {

		locationCategoryValue = CommonMethods.dynamicText("LocCat_");
		itemCategoryValue = CommonMethods.dynamicText("ItemCat_");
		supplierCategoryValue = CommonMethods.dynamicText("SuppCat_");
		locationInstanceValue = CommonMethods.dynamicText("LocInst_");
		itemInstanceValue = CommonMethods.dynamicText("ItemInst_");
		supplierInstanceValue = CommonMethods.dynamicText("SuppInst_");
		userUN = CommonMethods.dynamicText("U_UN");
		userFN = "Internal";
		userLN = "User";
		suppUN = CommonMethods.dynamicText("S_UN");
		suppFN = "Supplier";
		suppLN = "User";
		supplierPassword = GenericPassword;
		supplierNewPassword = GenericNewPassword;
		userLocation = new ArrayList<String>();
		userRole = new ArrayList<String>();
		workgroupName = CommonMethods.dynamicText("WG");
		filePath = System.getProperty("user.dir") + "\\test-data-files\\UploadDocuments\\"+documentName;
		uploadassigndoc = System.getProperty("user.dir") + "\\test-data-files\\UploadDocuments\\"+assigndoc; 
		docTypeName = CommonMethods.dynamicText("Doc");
		taskname = CommonMethods.dynamicText("DocTask");
		noteText = "Note";		
		chkFormName = CommonMethods.dynamicText("CHK");
		adtFormName = CommonMethods.dynamicText("ADT");
		qstFormName = CommonMethods.dynamicText("QST");
		taskschedulertaskname = CommonMethods.dynamicText("DailyTask");
		TaskDescription = "Daily Task Creation for Form Task Flow";
		supptempname = CommonMethods.dynamicText("SupplierTemp");
		itemtempname = CommonMethods.dynamicText("ItemTemp");
		itemdocreqname = CommonMethods.dynamicText("ItemDoc");
		itemdocreqname1 = CommonMethods.dynamicText("ItemDoc1");
		itemformreqname = CommonMethods.dynamicText("ItemForm");
		itemformreqname1  = CommonMethods.dynamicText("ItemForm1");
		suppdocreqname = CommonMethods.dynamicText("SuppDoc");
		suppdocreqname1 = CommonMethods.dynamicText("SuppDoc1");
		suppformreqname = CommonMethods.dynamicText("SuppForm");
		suppformreqname1 = CommonMethods.dynamicText("SuppForm1");
		chkFreeTxt = "FreeText";
		ParaTxt = "Paragraph";
		chkSelectOne = "SelectOne";
		Numeric = "Numeric";
		chkDate = "Date";
		chkTime = "Time";
		chkDateTime = "Date&Time";
		chkAggregate = "Aggregate";
		chkIdentifier = "Identifier";
		chkGroup = "Group";
		section = "Section";
		SelectMultiple = "MutiSelect";

		driver = launchbrowser();
		controlActions = new ControlActions(driver);
		controlActions.getUrl(applicationUrl);

		hp = new HomePage(driver);
		ump = new UserManagerPage(driver);
		wgp = new WorkGroupsPage(driver);
		rmp = new RolesManagerPage(driver);
		fdp = new FormDesignerPage(driver);
		dmp = new DocumentManagementPage(driver);
		mrp = new ManageRequirementPage(driver);
		inbox = new InboxPage(driver);
		taskScheduler = new TaskSchedulerPage(driver);
		fbp = new FSQABrowserPage(driver);
		tsd = new TaskScheduleDetails();
		fbForms = new FSQABrowserFormsPage(driver);
		supplierPage = new SupplierPortalPage(driver);
		lp = new LoginPage(driver);
		manageResource = new ManageResourcePage(driver);

		LoginPage lp = new LoginPage(driver);
		hp = lp.performLogin(adminUsername, adminPassword);
		if(hp.error) {
			TCGFailureMsg = "Could NOT login to application";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		//Category creation
		HashMap<String,String> categories = new HashMap<String, String>();
		categories.put(CATEGORYTYPES.LOCATION, locationCategoryValue);
		categories.put(CATEGORYTYPES.ITEMS, itemCategoryValue);
		categories.put(CATEGORYTYPES.SUPPLIERS, supplierCategoryValue);
		ResourceDesignerPage rdp = hp.clickResourceDesignerMenu();
		if(!rdp.createCategory(categories)) {
			TCGFailureMsg = "Could NOT create Resource categories for - " + locationCategoryValue + " "
					+ itemCategoryValue + " " + supplierCategoryValue;
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

		//Resource creation - Item
		HashMap<String,Boolean> itemInstance = new HashMap<String, Boolean>();
		itemInstance.put(itemInstanceValue, true);

		ResourceDetailParams rd1 = new ResourceDetailParams();
		rd1.CategoryName = itemCategoryValue;
		rd1.CategoryType = RESOURCETYPES.ITEMS;
		rd1.NumberFieldValue = 30;
		rd1.TextFieldValue = "ABC";
		rd1.InstanceNames = itemInstance;
		rd1.LocationInstanceValue = locationInstanceValue;	
		ManageResourcePage mrp = hp.clickResourcesMenu();
		if(!mrp.createResourceLinkLocation(rd1)) {
			TCGFailureMsg = "Could NOT create Item Instance for resource - " + itemInstanceValue;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		//Resource creation - Supplier
		HashMap<String,Boolean> suppInstance = new HashMap<String, Boolean>();
		suppInstance.put(supplierInstanceValue, true);

		ResourceDetailParams rd2 = new ResourceDetailParams();
		rd2.CategoryName = supplierCategoryValue;
		rd2.CategoryType = RESOURCETYPES.SUPPLIERS;
		rd2.NumberFieldValue = 15;
		rd2.TextFieldValue = "LMN";
		rd2.InstanceNames = suppInstance;
		rd2.LocationInstanceValue = locationInstanceValue;	
		mrp = hp.clickResourcesMenu();
		if(!mrp.createResourceLinkLocation(rd2)) {
			TCGFailureMsg = "Could NOT create Supplier Instance for resource - " + supplierCategoryValue;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
		
		if(!manageResource.linkItemSupplier(itemInstanceValue)) {
			TCGFailureMsg = "Could not link supplier resource " + supplierInstanceValue + " with item resource - "+ itemInstanceValue;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		//User creation
		userLocation.add(locationInstanceValue);
		userRole.add("SuperAdmin");
		UsrDetailParams udp = new UsrDetailParams();
		udp.Username = userUN;
		udp.Password = GenericPassword;
		udp.FirstName = userFN;
		udp.LastName = userLN;
		udp.Email = "test@test.com";
		udp.Timezone = TIMEZONE.REPUBLICOFINDIA;
		udp.Locations = userLocation;
		udp.Roles = userRole;
		UserManagerPage ump = hp.clickUsersMenu();
		boolean userCreation = ump.createInternalUser(udp);
		if(!userCreation) {
			TCGFailureMsg = "Could NOT create user - " + userUN;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		//Supplier user creation
		UsrDetailParams sdp = new UsrDetailParams();
		sdp.Suppliers = Arrays.asList(supplierInstanceValue);
		sdp.Username = suppUN;
		sdp.EmployeeId = "X12345";
		sdp.FirstName = suppFN;
		sdp.LastName = suppLN;
		sdp.Email = "test@test.com";
		sdp.Password = GenericPassword;
		sdp.Title = "Supplier Title";
		sdp.Timezone = TIMEZONE.REPUBLICOFINDIA;
		if(!ump.supplierUserCreation(sdp)) {
			TCGFailureMsg = "Could not create supplier user";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		//Workgroup creation
		hp.clickFSQABrowserMenu();
		if(!wgp.createWorkGroup(workgroupName, userUN)) {
			TCGFailureMsg = "Could not create workgroup-  " + workgroupName;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		if(!hp.userLogout()){
			TCGFailureMsg = "Could NOT logout from application";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		if(!(lp.performLogin(userUN, GenericPassword, GenericNewPassword))) {
			TCGFailureMsg = "Could NOT login to application";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		//Create Document Type
		hp.clickdocumentsmenu();
		if(!dmp.docUploadinDocType(docTypeName,filePath)) {
			TCGFailureMsg = "Could NOT add document type - " + docTypeName;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		//Create Check Form
		fdp = hp.clickFormDesignerMenu();
		if(fdp.error) {
			TCGFailureMsg = "Could NOT navigate to Admin Tools - Form Designer";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		HashMap<String, List<String>> chkFields = new HashMap<String, List<String>>();
		chkFields.put(FIELD_TYPES.FREE_TEXT, Arrays.asList(chkFreeTxt));
		chkFields.put(FIELD_TYPES.PARAGRAPH_TEXT, Arrays.asList(ParaTxt));
		chkFields.put(FIELD_TYPES.SELECT_ONE, Arrays.asList(chkSelectOne));
		chkFields.put(FIELD_TYPES.DATE, Arrays.asList(chkDate));
		chkFields.put(FIELD_TYPES.DATE_TIME, Arrays.asList(chkDateTime));

		FormFieldParams ffpChk = new FormFieldParams();
		ffpChk.FieldDetails = chkFields;
		ffpChk.SelectOneMultipleOptions = Arrays.asList("20","30");

		HashMap<String, List<String>> chkGrpFields = new HashMap<String, List<String>>();
		chkGrpFields.put(FIELD_TYPES.IDENTIFIER, Arrays.asList(chkIdentifier));
		chkGrpFields.put(FIELD_TYPES.TIME, Arrays.asList(chkTime));

		FormFieldParams ffpGrpChk = new FormFieldParams();
		ffpGrpChk.FieldDetails = chkGrpFields;
		ffpGrpChk.GroupName = chkGroup;
		ffpGrpChk.IdentiferType = IDENTIFIER_INPUT_TYPE.SELECT_ONE;
		ffpGrpChk.IdentifierValue = Arrays.asList("LINE1","LINE2","LINE3");

		HashMap<String, List<String>> chkSecFields = new HashMap<String, List<String>>();
		chkSecFields.put(FIELD_TYPES.SELECT_MULTIPLE, Arrays.asList(SelectMultiple));
		chkSecFields.put(FIELD_TYPES.NUMERIC, Arrays.asList(Numeric,Numeric));
		chkSecFields.put(FIELD_TYPES.AGGREGATE, Arrays.asList(chkAggregate));

		FormFieldParams ffpSecChk = new FormFieldParams();
		ffpSecChk.FieldDetails = chkSecFields;
		ffpSecChk.SectionName = section;
		ffpSecChk.AgrregateFunction = AGGREGATE_FUNCTION.SUM;
		ffpSecChk.AgrregateSource = Numeric;
		ffpSecChk.SelectOneMultipleOptions = Arrays.asList("40","50","60");

		HashMap<String, List<String>> suppResource = new HashMap<String, List<String>>();
		suppResource.put(FORMRESOURCETYPES.SUPPLIERS, Arrays.asList(supplierCategoryValue));

		FormDesignParams fdpChk = new FormDesignParams();
		fdpChk.FormType = FORMTYPES.CHECK;
		fdpChk.FormName = chkFormName;
		fdpChk.SelectResources = suppResource;
		fdpChk.DesignFields = Arrays.asList(ffpChk, ffpGrpChk, ffpSecChk);
		fdpChk.ReleaseForm = false;

		if(!fdp.createAndReleaseForm(fdpChk)) {
			TCGFailureMsg = "Could NOT save Check form - " + chkFormName;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		if(!fdp.navigateToReleaseForm_EditForm(chkFormName)) {
			TCGFailureMsg = "Could NOT open edit form - " + chkFormName;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		if(!fdp.setRepeatCountForField(chkFreeTxt,"2")) {
			TCGFailureMsg = "Could NOT release form - " + chkFormName;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
		if(!fdp.clickOnNextButton(fdp.ReleaseFormPg,"Release Form")) {
			TCGFailureMsg = "Could NOT click on Next button";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
		if(!fdp.releaseForm(chkFormName)) {
			TCGFailureMsg = "Could NOT release form - " + chkFormName;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		//Create Audit Form

		HashMap<String, List<String>> adtSecFields = new HashMap<String, List<String>>();
		adtSecFields.put(FIELD_TYPES.SELECT_MULTIPLE, Arrays.asList(SelectMultiple));

		FormFieldParams ffpSecAdt = new FormFieldParams();
		ffpSecAdt.FieldDetails = adtSecFields;
		ffpSecAdt.SectionName = section;
		ffpSecAdt.QuestionWeight = "400";
		ffpSecAdt.SelectOneMultipleOptionsWeight = field4optionswithWeight;

		HashMap<String, List<String>> itemResource = new HashMap<String, List<String>>();
		itemResource.put(FORMRESOURCETYPES.SUPPLIERS, Arrays.asList(supplierCategoryValue));

		FormDesignParams fdpAdt = new FormDesignParams();
		fdpAdt.FormType = FORMTYPES.AUDIT;
		fdpAdt.FormName = adtFormName;
		fdpAdt.SelectResources = itemResource;
		fdpAdt.HeaderNoteText = noteText;
		fdpAdt.HeaderDocTypeName = docTypeName;
		fdpAdt.HeaderDocName = documentName;
		fdpAdt.HeaderSummaryField = true;
		fdpAdt.DesignFields = Arrays.asList(ffpSecAdt);
		fdpAdt.ReleaseForm = true;

		hp.clickFormDesignerMenu();
		if(!fdp.createAndReleaseForm(fdpAdt)) {
			TCGFailureMsg = "Could NOT release Audit form - " + adtFormName;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		//Create Questionnaire Form

		HashMap<String, List<String>> qstSecFields = new HashMap<String, List<String>>();
		qstSecFields.put(FIELD_TYPES.NUMERIC, Arrays.asList(Numeric));
		qstSecFields.put(FIELD_TYPES.PARAGRAPH_TEXT, Arrays.asList(ParaTxt));

		FormFieldParams ffpSecQst = new FormFieldParams();
		ffpSecQst.FieldDetails = qstSecFields;
		ffpSecQst.SectionName = section;

		HashMap<String, List<String>> resources = new HashMap<String, List<String>>();
		resources.put(FORMRESOURCETYPES.SUPPLIERS, Arrays.asList(supplierCategoryValue));
		//resources.put(FORMRESOURCETYPES.ITEMS, Arrays.asList(itemCategoryValue));

		FormDesignParams fdpQst = new FormDesignParams();
		fdpQst.FormType = FORMTYPES.QUESTIONNAIRE;
		fdpQst.FormName = qstFormName;
		fdpQst.SelectResources = resources;
		fdpQst.DesignFields = Arrays.asList(ffpSecQst);
		fdpQst.ReleaseForm = false;

		hp.clickFormDesignerMenu();
		if(!fdp.createAndReleaseForm(fdpQst)) {
			TCGFailureMsg = "Could NOT save Questionnaire form - " + qstFormName;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		if(!fdp.navigateToReleaseForm_EditForm(qstFormName)) {
			TCGFailureMsg = "Could NOT open edit form - " + qstFormName;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		HashMap<String, ElementProperties> fieldSettings = new HashMap<String, ElementProperties>(); 
		ElementProperties settings = new ElementProperties();
		settings.COMPLIANCE_CONFIG = true;
		settings.COMPLIANCE_VALUES = new String[]{fieldMin, fieldTar, fieldMax, null};
		fieldSettings.put(Numeric, settings);

		/*if(!fdp.selectField(ParaTxt)) {
			TCGFailureMsg = "Could NOT select field - " + ParaTxt;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		if(!fdp.AddDependencyRule(Numeric, fieldMax)) {
			TCGFailureMsg = "Could NOT set dependency rule for field - " + Numeric;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}*/

		if(!fdp.setFieldProperties(fieldSettings)) {
			TCGFailureMsg = "Could NOT set compliance properties for field - " + Numeric;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		if(!fdp.releaseForm(qstFormName)) {
			TCGFailureMsg = "Could NOT release form - " + qstFormName;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

	}

	@Test(description="Supplier Flow || Verify creation of requirement,addition of supplier/item instance,"
			+ "completion of the task from supplier portal and accepting/rejecting the approval task in Inbox.")
	public void TestCase_36364() throws Exception{

		hp.clickRequirementsMenu();	
		if(!mrp.createSuppReq(supptempname, docTypeName, workgroupName, suppdocreqname, suppdocreqname1, qstFormName, 
				suppformreqname, suppformreqname1, supplierInstanceValue)) {
			TCGFailureMsg = "Could not create requirement for supplier " + supplierInstanceValue;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		if(!mrp.createitemreq(itemtempname, docTypeName, workgroupName, itemdocreqname, itemdocreqname1, qstFormName, 
				itemformreqname, itemformreqname1, itemInstanceValue)) {
			TCGFailureMsg = "Could not create requirement for item " + itemInstanceValue;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		if(!hp.userLogout()) {
			TCGFailureMsg = "Could not log out from internal user";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		if(!lp.performLogin(suppUN,supplierPassword,supplierNewPassword)) {
			TCGFailureMsg = "Could not log in from supplier user   "+ suppUN;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		String [] formtaskname = {suppformreqname,suppformreqname1,itemformreqname,itemformreqname1};
		String [] doctaskname = {suppdocreqname,suppdocreqname1,itemdocreqname,itemdocreqname1};

		if(!supplierPage.submitformTask(formtaskname)) {
			TCGFailureMsg = "Could not submit form task  ";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		if(!supplierPage.uploadDocTask(doctaskname,filePath)) {
			TCGFailureMsg = "Could not upload document task  ";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		if(!hp.userLogout()) {
			TCGFailureMsg = "Could not log out from supplier user";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		lp.performLogin(userUN, GenericNewPassword);
		if(hp.error) {
			TCGFailureMsg = "Could NOT login to application";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		hp.clickInboxMenu();

		String[] accepttaskname = {suppformreqname,suppdocreqname,itemformreqname,itemdocreqname};
		boolean accept = inbox.actionOnTask(accepttaskname, true, false);
		TestValidation.IsTrue(accept, 
				"Task accepted", 
				"Failed to accept task");

		String[] rejecttaskname = {suppformreqname1,suppdocreqname1,itemformreqname1,itemdocreqname1};
		boolean reject =  inbox.actionOnTask(rejecttaskname, false, true);
		TestValidation.IsTrue(reject, 
				"Task rejected", 
				"Failed to reject task");

	}

	@Test(description="Form Submission || Verify saving,submitting and record creation of the form.")
	public void TestCase_36365() throws Exception{

		hp.clickFSQABrowserMenu();

		boolean navigate = fbp.selectResourceDropDownandNavigate("SUPPLIERS","Forms");
		TestValidation.IsTrue(navigate, 
				"Navigated to FSQABrowser>FormsTab", 
				"Failed to navigate to FSQABrowser>Forms Tab");

		boolean applyfilter = fbp.openAndApplySettingsForColumn(COLUMNHEADER.FORMNAME, COLUMNSETTING.FILTER,adtFormName);
		TestValidation.IsTrue(applyfilter, 
				"Applied Filter on" + COLUMNHEADER.FORMNAME, 
				"Failed to apply filter on" + COLUMNHEADER.FORMNAME);
		threadsleep(5000);

		boolean submit = fbForms.saveAndSubmitForm(adtFormName);
		TestValidation.IsTrue(submit, 
				"Form submitted" + adtFormName, 
				"Failed to submit form" + adtFormName);

		boolean navigaterecords = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.SUPPLIERS,FSQATAB.RECORDS);
		TestValidation.IsTrue(navigaterecords, "For supplier category, could NOT navigate to FSQABrowser > Records tab",
				"For supplier category, could NOT navigate to FSQABrowser > Records tab");

		boolean verifyauditform = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, adtFormName);
		TestValidation.IsTrue(verifyauditform,"Verified form - " + adtFormName,
				"Could not verify form - " + adtFormName);

	}

	@Test(description="Task Scheduler || Verify creation of schedule,assignment of the task,"
			+ "completion of the task and creation of the record from the task.")
	public void TestCase_36366() throws Exception{

		hp.clickTaskSchedulerMenu();
		tsd.taskoccure = "Daily";		
		tsd.haveEndDate = true;
		tsd.taskname = taskschedulertaskname;
		tsd.locationvalue = locationInstanceValue;
		tsd.formORdocument = "form";
		tsd.formORdocumentvalue = chkFormName;
		tsd.resource = supplierInstanceValue;
		tsd.workGroup = workgroupName;
		tsd.taskDescription = TaskDescription;

		boolean createTask = taskScheduler.createNewTask(tsd);
		TestValidation.IsTrue(createTask, "Daily Schedule Created" + taskschedulertaskname,
				"Could NOT create Daily schedule" + taskschedulertaskname);
		threadsleep(60000);
		controlActions.refreshPage();
		hp.clickInboxMenu();
		boolean selectformtask = inbox.selectFormTask(taskschedulertaskname);
		TestValidation.IsTrue(selectformtask, "Task Selected - " + taskschedulertaskname,
				"Could NOT select task - " + taskschedulertaskname);
		boolean complete = fbForms.submitData(false, false, false, true,false);
		TestValidation.IsTrue(complete, "Complete Form Task - " +taskschedulertaskname+ " Completed",
				"Could NOT complete form task  - " + taskschedulertaskname);	

		hp.clickFSQABrowserMenu();
		boolean navigate = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.SUPPLIERS,FSQATAB.RECORDS);
		TestValidation.IsTrue(navigate, "For supplier category, could NOT navigate to FSQABrowser > Records tab",
				"For supplier category, could NOT navigate to FSQABrowser > Records tab");

		boolean verifycheckform = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, chkFormName);
		TestValidation.IsTrue(verifycheckform,"Verified form - " + chkFormName,
				"Could not verify form - " + chkFormName);

	}

	@Test(description="Questionnaire Form || Submission of questionnaire form and "
			+ "verify the document created in DMS")
	public void TestCase_36367() throws Exception{

		hp.clickFSQABrowserMenu();

		boolean navigate1 =  fbp.selectResourceDropDownandNavigate(FSQARESOURCE.SUPPLIERS,FSQATAB.FORMS);	
		TestValidation.IsTrue(navigate1, 
				"NAVIGATED to FSQA -> Forms Tab", 
				"Could NOT Navigate to FSQA -> Forms Tab");		

		boolean openForm = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, qstFormName);
		TestValidation.IsTrue(openForm, 
				"Verified form is displayed in Forms Tab", 
				"Form is NOT displayed in Documents Tab");		

		FormDetails fd = new FormDetails();
		HashMap<String, List<String>> fillData = new HashMap<String, List<String>>();
		fillData.put(Numeric,Arrays.asList(fieldMax));
		fillData.put(ParaTxt, Arrays.asList("Test"));
		fd.fieldDetails = fillData;
		fd.resourceName = supplierInstanceValue;
		fd.locationName = locationInstanceValue;
		FormOperations fo = new FormOperations(driver);
		boolean submit1 = fo.submitData(fd);
		TestValidation.IsTrue(submit1, 
				"Entered data and Submitted form successfully", 
				"Could NOT Enter data and Submit form");		

		hp.clickFSQABrowserMenu();
		boolean navigate2 =  fbp.selectResourceDropDownandNavigate(FSQARESOURCE.SUPPLIERS,FSQATAB.DOCUMENTS);	
		TestValidation.IsTrue(navigate2, 
				"NAVIGATED to FSQA -> Documents Tab", 
				"Could NOT Navigate to FSQA -> Documents Tab");		

		boolean openDoc = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.DOCUMENTNAME, qstFormName);
		TestValidation.IsTrue(openDoc, 
				"Verified submitted form is displayed in Documents Tab", 
				"Submitted form is NOT displayed in Documents Tab");	

	}

	@Test(description="DMS || Verify creation of doc type,uploading document,assigning document task and completing "
			+ "the document task from Inbox.")
	public void TestCase_36368() throws Exception{

		hp.clickdocumentsmenu();
		boolean assign = dmp.assignDocumentTask(uploadassigndoc,docTypeName,assigndoc,taskname,workgroupName);
		TestValidation.IsTrue(assign, 
				"Document Task Assigned" ,
				"Document assignment failed");

		hp.clickInboxMenu();
		boolean doctask = inbox.documentUploadTask(taskname,uploadassigndoc);
		TestValidation.IsTrue(doctask, 
				"Document Task Completed" ,
				"Document Task failed");

	}

	@AfterClass(alwaysRun = true)
	public void closeBrowser() throws InterruptedException {
		driver.close();
	}

}
