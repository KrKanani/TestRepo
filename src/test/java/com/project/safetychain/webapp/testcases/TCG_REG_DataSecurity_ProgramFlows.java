package com.project.safetychain.webapp.testcases;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;


import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.project.safetychain.webapp.pageobjects.HomePage;
import com.project.safetychain.webapp.pageobjects.LoginPage;
import com.project.safetychain.webapp.pageobjects.ManageLocationPage;
import com.project.safetychain.webapp.pageobjects.ManageLocationPage.LocationInstanceParams;
import com.project.safetychain.webapp.pageobjects.ManageResourcePage;
import com.project.safetychain.webapp.pageobjects.ProgramDesignerPage;
import com.project.safetychain.webapp.pageobjects.ProgramViewerPage;
import com.project.safetychain.webapp.pageobjects.ManageResourcePage.RESOURCETYPES;
import com.project.safetychain.webapp.pageobjects.ManageResourcePage.ResourceDetailParams;
import com.project.safetychain.webapp.pageobjects.ProgramViewerPage.PGVIEWTABS;
import com.project.safetychain.webapp.pageobjects.ResourceDesignerPage;
import com.project.safetychain.webapp.pageobjects.ResourceDesignerPage.CATEGORYTYPES;
import com.project.safetychain.webapp.pageobjects.UserManagerPage;
import com.project.safetychain.webapp.pageobjects.CommonPage.TIMEZONE;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.COLUMNHEADER;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.FSQARESOURCE;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.FSQATAB;
import com.project.safetychain.webapp.pageobjects.FSQABrowserRecordsPage.RecordFormDetails;
import com.project.safetychain.webapp.pageobjects.DocumentManagementPage;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FIELD_TYPES;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FORMRESOURCETYPES;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FORMTYPES;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FormDesignParams;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FormFieldParams;
import com.project.safetychain.webapp.pageobjects.UserManagerPage.UsrDetailParams;
import com.project.safetychain.webapp.testcases.support.FormOperations;
import com.project.safetychain.webapp.testcases.support.FormOperations.FormDetails;
import com.project.safetychain.webapp.pageobjects.FSQABrowserFormsPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserRecordsPage;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.utilities.CommonMethods;
import com.project.utilities.ControlActions;
import com.project.utilities.DateTime;


public class TCG_REG_DataSecurity_ProgramFlows extends TestBase{
	ControlActions controlActions;
	HomePage hp;
	LoginPage lp;
	FSQABrowserPage fbp;
	ManageLocationPage mlp;
	ManageResourcePage mrp;
	UserManagerPage ump;
	FormDesignerPage fdp;
	FSQABrowserFormsPage fbForms;
	DocumentManagementPage dmp;
	ResourceDesignerPage rdp;
	ProgramViewerPage pvp;
	FSQABrowserFormsPage ffp;
	FormOperations fo;
	DateTime dt = new DateTime();

	public static String workGroupName;
	public static String roleName;
	public static String userUN, newUserUN, newUserUN1, userUN1, userUN2;
	public static String userFN;
	public static String userLN;

	public static List<String> userLocation;
	public static List<String> userRole;

	public static String locationCategoryValue, customerCategoryValue, 
	equipmentCategoryValue, itemCategoryValue, supplierCategoryValue;
	public static String locationInstanceValue, customerInstanceValue,locationInstanceValue1,
	equipmentInstanceValue, itemInstanceValue, supplierInstanceValue;
	public static String resourceCategory;
	public static String newlocationInstanceValue, newlocationInstanceValue1, newlocationInstanceValue2, newlocationInstanceValue3;
	public static String newLocationCategoryValue, newCustomerCategoryValue, newEquipmentCategoryValue;
	public static String newCustomerInstanceValue, newEquipmentInstanceValue, newCustomerInstanceValue1, newEquipmentInstanceValue1;

	public static String formName, formName1,formName2,checkFormName,newcheckFormName,newcheckFormName1,checkFormName17321, checkFormName17322,
	questionnaireFormName, questionnaireFormName17322,auditFormName,auditFormName17322, numFieldName, numericFieldName, singleLineTxtFN, section;

	public static List<String> userLocation2;
	public static List<String> userRole2;
	public static List<String> defaultLocation;
	public static String pin;

	public static String programName; 
	public static String programElementName;
	public static String programDesc;
	public static String docTypeName1;
	public static String docTypeName2;
	public static String documentName1 = "TestCase_Doc1.png";
	public static String documentName2 = "TestCase_Doc2.png";
	public static String filePath1 = System.getProperty("user.dir") + "\\test-data-files\\UploadDocuments\\"+documentName1;
	public static String filePath2 = System.getProperty("user.dir") + "\\test-data-files\\UploadDocuments\\"+documentName2;
	public static List<String> documentList1 = Arrays.asList(filePath1); 
	public static List<String> documentList2 = Arrays.asList(filePath2); 
	public static HashMap<String,List<String>> addFiles = new HashMap<String,List<String>>();
	public static List<String> documentNMLst = new ArrayList<String>();
	public static List<String> formsNMLst = new ArrayList<String>();
	public static List<String> documentTypeNMLst = new ArrayList<String>();
	public static String displayName = null;

	@BeforeClass(alwaysRun = true)
	public void groupInit() throws Exception {

		workGroupName = CommonMethods.dynamicText("WG");
		roleName = CommonMethods.dynamicText("RL");
		userUN = CommonMethods.dynamicText("UN");
		newUserUN = CommonMethods.dynamicText("User_N");
		newUserUN1 = CommonMethods.dynamicText("New_User_N");
		userUN1 = CommonMethods.dynamicText("UserN");
		userUN2 = CommonMethods.dynamicText("UN17322");
		userFN = CommonMethods.dynamicText("FN");
		userLN = CommonMethods.dynamicText("LN");
		userLocation = new ArrayList<String>();
		userRole = new ArrayList<String>();

		userLocation2 = new ArrayList<String>();
		userRole2 = new ArrayList<String>();

		formName = CommonMethods.dynamicText("Chk");
		formName1 = CommonMethods.dynamicText("Chk1");
		formName2 = CommonMethods.dynamicText("Chk2");
		locationCategoryValue = CommonMethods.dynamicText("Loc_Cat");
		customerCategoryValue = CommonMethods.dynamicText("Cust_Cat");
		equipmentCategoryValue = CommonMethods.dynamicText("Equip_Cat");
		itemCategoryValue = CommonMethods.dynamicText("Item_Cat");
		supplierCategoryValue = CommonMethods.dynamicText("Supp_Cat");
		locationInstanceValue = CommonMethods.dynamicText("Loc_Inst");
		newlocationInstanceValue = CommonMethods.dynamicText("Loc_I");
		newlocationInstanceValue1 = CommonMethods.dynamicText("New_Loc_I");
		newlocationInstanceValue2 = CommonMethods.dynamicText("Loc_Inst_17322");
		customerInstanceValue = CommonMethods.dynamicText("Cust_Inst");
		newCustomerInstanceValue = CommonMethods.dynamicText("Customers_I");
		newCustomerInstanceValue1 = CommonMethods.dynamicText("Cust_In");
		equipmentInstanceValue = CommonMethods.dynamicText("Equip_Inst");
		newEquipmentInstanceValue = CommonMethods.dynamicText("Equipment_I");
		newEquipmentInstanceValue1 = CommonMethods.dynamicText("Equip_In");
		itemInstanceValue = CommonMethods.dynamicText("Item_Inst");
		supplierInstanceValue = CommonMethods.dynamicText("Supp_Inst");

		programName = CommonMethods.dynamicText("PD");
		programElementName = CommonMethods.dynamicText("PE");
		programDesc = CommonMethods.dynamicText("Prog_Desc");
		docTypeName1 = CommonMethods.dynamicText("ProgDoc1");
		docTypeName2 = CommonMethods.dynamicText("ProgDoc2");
		documentNMLst.add(documentName2);
		documentTypeNMLst.add(docTypeName1);
		formsNMLst.add(formName);
		formsNMLst.add(formName1);
		formsNMLst.add(formName2);

		driver = launchbrowser();
		controlActions = new ControlActions(driver);
		controlActions.getUrl(applicationUrl);
		hp = new HomePage(driver);
		mlp = new ManageLocationPage(driver);
		ump = new UserManagerPage(driver);
		mrp = new ManageResourcePage(driver);
		fbForms = new FSQABrowserFormsPage(driver);
		dmp = new DocumentManagementPage(driver);
		fdp = new FormDesignerPage(driver);
		rdp = new ResourceDesignerPage(driver);
		pvp = new ProgramViewerPage(driver);
		ffp = new FSQABrowserFormsPage(driver);
		fo = new FormOperations(driver);
		fbp = new FSQABrowserPage(driver);

		lp = new LoginPage(driver);
		hp = lp.performLogin(adminUsername, adminPassword);
		if(hp.error) {
			TCGFailureMsg = "Could NOT login to application";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
		displayName = hp.getLoggedInUserDetails();
		if(displayName == ""){
			TCGFailureMsg = "Could NOT get display name for user - " + adminUsername;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		//Category creation
		HashMap<String,String> categories = new HashMap<String, String>();
		categories.put(CATEGORYTYPES.LOCATION, locationCategoryValue);
		categories.put(CATEGORYTYPES.CUSTOMERS, customerCategoryValue);
		categories.put(CATEGORYTYPES.EQUIPMENT, equipmentCategoryValue);
		categories.put(CATEGORYTYPES.ITEMS, itemCategoryValue);
		categories.put(CATEGORYTYPES.SUPPLIERS, supplierCategoryValue);
		ResourceDesignerPage rdp = hp.clickResourceDesignerMenu();
		if(!rdp.createCategory(categories)) {
			TCGFailureMsg = "Could NOT create Resource categories for - " + locationCategoryValue + " "
					+ customerCategoryValue + " " + equipmentCategoryValue + " " + itemCategoryValue
					+ " " + supplierCategoryValue;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		//Location instance creation
		HashMap<String,Boolean> locInstance = new HashMap<String, Boolean>();
		locInstance.put(locationInstanceValue, true);
		locInstance.put(newlocationInstanceValue, true);
		//		locInstance.put(newlocationInstanceValue1, true);
		//		locInstance.put(newlocationInstanceValue2, true);

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
		//Resource creation - Customer
		HashMap<String,Boolean> custInstance = new HashMap<String, Boolean>();
		custInstance.put(customerInstanceValue, true);

		ResourceDetailParams rd1 = new ResourceDetailParams();
		rd1.CategoryName = customerCategoryValue;
		rd1.CategoryType = RESOURCETYPES.CUSTOMERS;
		rd1.NumberFieldValue = 15;
		rd1.TextFieldValue = "LMN";
		rd1.InstanceNames = custInstance;
		rd1.LocationInstanceValue = locationInstanceValue;

		ManageResourcePage mrp = hp.clickResourcesMenu();
		if(!mrp.createResourceLinkLocation(rd1)) {
			TCGFailureMsg = "Could NOT create Customer Instance for category - " + customerCategoryValue;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		//Resource creation - Suppliers
		HashMap<String,Boolean> suppInstance = new HashMap<String, Boolean>();
		suppInstance.put(supplierInstanceValue, true);

		ResourceDetailParams rd2 = new ResourceDetailParams();
		rd2.CategoryName = supplierCategoryValue;
		rd2.CategoryType = RESOURCETYPES.SUPPLIERS;
		rd2.NumberFieldValue = 15;
		rd2.TextFieldValue = "LMN";
		rd2.InstanceNames = suppInstance;
		rd2.LocationInstanceValue = newlocationInstanceValue;

		if(!mrp.createResourceLinkLocation(rd2)) {
			TCGFailureMsg = "Could NOT create Supplier Instance for category - " + supplierCategoryValue;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
		//FORM - Creation and Release

		numFieldName    = "Numeric Field";

		HashMap<String, List<String>> fields = new HashMap<String, List<String>>();
		fields.put(FIELD_TYPES.NUMERIC, Arrays.asList(numFieldName));

		FormFieldParams ffp = new FormFieldParams();
		ffp.FieldDetails = fields;

		HashMap<String, List<String>> resource = new HashMap<String, List<String>>();
		resource.put(FORMRESOURCETYPES.CUSTOMERS, Arrays.asList(customerCategoryValue));
		resource.put(FORMRESOURCETYPES.SUPPLIERS, Arrays.asList(supplierCategoryValue));


		FormDesignParams fdpDets = new FormDesignParams();
		fdpDets.FormType = FORMTYPES.CHECK;
		fdpDets.FormName = formName1;
		fdpDets.SelectResources = resource;
		fdpDets.DesignFields = Arrays.asList(ffp);
		fdpDets.ReleaseForm = true;

		hp.clickFormDesignerMenu();
		boolean createAndReleaseForm = fdp.createAndReleaseForm(fdpDets);
		if(!createAndReleaseForm) {
			TCGFailureMsg = "Could NOT create and Release form - " + formName1;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
		//FORM - Creation and Release

		numFieldName    = "Numeric Field";

		HashMap<String, List<String>> fields1 = new HashMap<String, List<String>>();
		fields1.put(FIELD_TYPES.NUMERIC, Arrays.asList(numFieldName));

		FormFieldParams ffp1 = new FormFieldParams();
		ffp1.FieldDetails = fields;

		HashMap<String, List<String>> resource1 = new HashMap<String, List<String>>();
		resource1.put(FORMRESOURCETYPES.CUSTOMERS, Arrays.asList(customerCategoryValue));
		resource1.put(FORMRESOURCETYPES.SUPPLIERS, Arrays.asList(supplierCategoryValue));


		FormDesignParams fdpDets1 = new FormDesignParams();
		fdpDets1.FormType = FORMTYPES.CHECK;
		fdpDets1.FormName = formName2;
		fdpDets1.SelectResources = resource1;
		fdpDets1.DesignFields = Arrays.asList(ffp);
		fdpDets1.ReleaseForm = true;

		hp.clickFormDesignerMenu();
		boolean createAndReleaseForm1 = fdp.createAndReleaseForm(fdpDets1);
		if(!createAndReleaseForm1) {
			TCGFailureMsg = "Could NOT create and Release form - " + formName2;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		//FORM - Creation and Release

		numFieldName    = "Numeric Field";

		HashMap<String, List<String>> fields2 = new HashMap<String, List<String>>();
		fields2.put(FIELD_TYPES.NUMERIC, Arrays.asList(numFieldName));

		FormFieldParams ffp2 = new FormFieldParams();
		ffp2.FieldDetails = fields;

		HashMap<String, List<String>> resource2 = new HashMap<String, List<String>>();
		resource2.put(FORMRESOURCETYPES.SUPPLIERS, Arrays.asList(supplierCategoryValue));

		FormDesignParams fdpDets2 = new FormDesignParams();
		fdpDets2.FormType = FORMTYPES.CHECK;
		fdpDets2.FormName = formName;
		fdpDets2.SelectResources = resource2;
		fdpDets2.DesignFields = Arrays.asList(ffp);
		fdpDets2.ReleaseForm = true;

		hp.clickFormDesignerMenu();
		boolean createAndReleaseForm2 = fdp.createAndReleaseForm(fdpDets2);
		if(!createAndReleaseForm2) {
			TCGFailureMsg = "Could NOT create and Release form - " + formName;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		hp.clickFSQABrowserMenu();
		if(!fbp.selectResourceDropDownandNavigate(FSQARESOURCE.CUSTOMERS,FSQATAB.FORMS)) {
			TCGFailureMsg = "For customer category, could NOT navigate to FSQABrowser > Forms tab";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		if(!fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, formName1)) {
			TCGFailureMsg = "Could not filter form - " + formName1;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		//Submit form
		LinkedHashMap<String, List<String>> fillDetails = new LinkedHashMap<String, List<String>>();
		fillDetails.put("Numeric Field", Arrays.asList("10"));

		FormDetails fd = new FormDetails();
		fd.fieldDetails = fillDetails;
		fd.locationName = locationInstanceValue;
		fd.resourceName = customerInstanceValue;
		fd.isSubmit = true;

		if(!fo.fillAndSubmitData(fd)) {
			TCGFailureMsg = "Could NOT submit form - " + formName1;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		if(!fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, formName2)) {
			TCGFailureMsg = "Could not filter form - " + formName2;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		//Submit form
		LinkedHashMap<String, List<String>> fillDetails1 = new LinkedHashMap<String, List<String>>();
		fillDetails1.put("Numeric Field", Arrays.asList("10"));

		FormDetails fd1 = new FormDetails();
		fd1.fieldDetails = fillDetails;
		fd1.locationName = locationInstanceValue;
		fd1.resourceName = customerInstanceValue;
		fd1.isSubmit = true;

		if(!fo.fillAndSubmitData(fd1)) {
			TCGFailureMsg = "Could NOT submit form - " + formName2;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
		if(!fbp.selectResourceDropDownandNavigate(FSQARESOURCE.SUPPLIERS,FSQATAB.FORMS)) {
			TCGFailureMsg = "For SUPPLIERS category, could NOT navigate to FSQABrowser > Forms tab";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		if(!fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, formName)) {
			TCGFailureMsg = "Could not filter form - " + formName;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
		//Submit form
		LinkedHashMap<String, List<String>> fillDetails2 = new LinkedHashMap<String, List<String>>();
		fillDetails2.put("Numeric Field", Arrays.asList("10"));

		FormDetails fd2 = new FormDetails();
		fd2.fieldDetails = fillDetails;
		fd2.locationName = newlocationInstanceValue;
		fd2.resourceName = supplierInstanceValue;
		fd2.isSubmit = true;

		if(!fo.fillAndSubmitData(fd2)) {
			TCGFailureMsg = "Could NOT submit form - " + formName;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		//		DocumentManagementPage dmp = hp.clickdocumentsmenu();
		//		addFiles.put(docTypeName1, documentList1);
		//		addFiles.put(docTypeName2, documentList2);
		//		if(!dmp.uploadDocInDocType(addFiles)) {
		//			TCGFailureMsg = "Could NOT add files to document type - " + addFiles;
		//			logFatal(TCGFailureMsg);
		//			throw new SkipException(TCGFailureMsg);
		//		}	

		ProgramDesignerPage pdp = hp.clickProgramDesignerMenu();
		if(!pdp.setProgramName(programName)) {
			TCGFailureMsg = "Could NOT set Program name as - " + programName;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		if(!pdp.addProgramElement(programElementName)){
			TCGFailureMsg = "Could NOT set Program element as - " + programElementName;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		if(!pdp.setDescription(programDesc)){
			TCGFailureMsg = "Could NOT add description to the Details Tab - " + programDesc ;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		//		if(!pdp.selectDocumentType(documentTypeNMLst)){
		//			TCGFailureMsg = "Could NOT add Document Type " + documentTypeNMLst + " to Program Designer";
		//			logFatal(TCGFailureMsg);
		//			throw new SkipException(TCGFailureMsg);
		//		}
		//
		//		if(!pdp.selectAndSaveDocuments(documentNMLst, docTypeName2)){
		//			TCGFailureMsg = "Could NOT add Documents " + documentNMLst + " to Program Designer";
		//			logFatal(TCGFailureMsg);
		//			throw new SkipException(TCGFailureMsg);
		//		}

		if(!pdp.selectForms(formsNMLst)){
			TCGFailureMsg = "Could NOT add Forms " + formsNMLst + " to Program Designer";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
		//user Creation
		userLocation.add(locationInstanceValue);
		userRole.add("SuperAdmin");
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



		if(!hp.userLogout()){
			TCGFailureMsg = "Could NOT logout from application";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
		if(!lp.loginAfterUserCreation(userUN, GenericPassword, GenericNewPassword)) {
			TCGFailureMsg = "Could NOT login with user - " + userUN;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}


	}

	@Test(description = "Program Management - Program Viewer - Data Security: The\r\n"
			+ "Forms tab for a program element only shows forms the user has access to based\r\n"
			+ "on data security")
	public void TestCase_17536() {
		pvp = hp.clickProgramsMenu();
		TestValidation.Equals(pvp.error, 
				false, 
				"CLICKED on Programs  menu", 
				"Could NOT click on Programs menu");

		boolean pdSelectPrgram = pvp.selectPrograms(programName);
		TestValidation.IsTrue(pdSelectPrgram,
				"SELECTED program " + programName + " from the Program list",
				"Could NOT select program " + programName + " from the Program list");

		boolean pdSelectTab = pvp.clickProgramViewTab(PGVIEWTABS.RECORDS);
		TestValidation.IsTrue(pdSelectTab,
				"SELECTED program tab as " + PGVIEWTABS.RECORDS + " for the Program",
				"Could NOT select program tab as " + PGVIEWTABS.RECORDS + " for the Program");

		FSQABrowserRecordsPage frp = pvp.selectRecordsForViewer();
		TestValidation.Equals(frp.error, 
				false,
				"SELECTED records for viewing records of form " + formsNMLst,
				"Could NOT select records for viewing records of form " + formsNMLst);

		//Create a new page maybe for Program Viewer
		RecordFormDetails rfd1 = new RecordFormDetails();
		rfd1.FormName = formName1;
		rfd1.CompletedBy = displayName;
		boolean verifyFormDetails1 = frp.searchAndVerifyRecordsDetailsInView(rfd1);
		TestValidation.IsTrue(verifyFormDetails1, 
				"VERIFIED record details for form - " + rfd1.FormName, 
				"Could NOT verify record details for form - " + rfd1.FormName);

		RecordFormDetails rfd2 = new RecordFormDetails();
		rfd2.FormName = formName2;
		rfd2.CompletedBy = displayName;
		boolean verifyFormDetails2 = frp.searchAndVerifyRecordsDetailsInView(rfd2);
		TestValidation.IsTrue(verifyFormDetails2, 
				"VERIFIED record details for form - " + rfd2.FormName, 
				"Could NOT verify record details for form - " + rfd2.FormName);

		boolean pdVerifyForms = pvp.verifyFormsFromPrograms(Arrays.asList(formName1));
		TestValidation.IsTrue(pdVerifyForms, 
				"VERIFIED Form " + formName1 + " in Program  - " + programName,
				"Could NOT verify Form " + formName1 + " in Program  - " + programName);

		boolean selectOpenForm = pvp.openForm(formName1);
		TestValidation.IsTrue(selectOpenForm, 
				"Select & opened the form - "+formName1, 
				"Could NOT open the form"+formName1);

		boolean selectResource = fo.selectResource(customerInstanceValue);
		TestValidation.IsTrue(selectResource, 
				"Verified Resource - "+customerInstanceValue+ " is displayed in SELECT RESOURCE DropDown on a form.", 
				"Failed to verify Resource - "+customerInstanceValue+" is display in SELECT RESOURCE DropDown on a form.");

		boolean searchNodata = fo.searchSelectResourceNoData(supplierInstanceValue);
		TestValidation.IsTrue(searchNodata, 
				"Verified that resource - "+supplierInstanceValue+" for which the access was not provided is not displaying in drop down list " , 
				"Could not able to verify resource - "+supplierInstanceValue+" for which the access was not provided is not displaying in drop down list" );

		boolean closeForm = pvp.closeForm();
		TestValidation.IsTrue(closeForm, 
				"Closed the form - "+formName1, 
				"Could NOT close the form"+formName1);


		boolean pdVerifyForms1 = pvp.verifyFormsFromPrograms(Arrays.asList(formName2));
		TestValidation.IsTrue(pdVerifyForms1, 
				"VERIFIED Form " + formName2 + " in Program  - " + programName,
				"Could NOT verify Form " + formName2 + " in Program  - " + programName);

		boolean selectOpenForm1 = pvp.openForm(formName2);
		TestValidation.IsTrue(selectOpenForm1, 
				"Select & opened the form - "+formName2, 
				"Could NOT open the form"+formName2);

		boolean selectResource1 = fo.selectResource(customerInstanceValue);
		TestValidation.IsTrue(selectResource1, 
				"Verified Resource - "+customerInstanceValue+ " is displayed in SELECT RESOURCE DropDown on a form.", 
				"Failed to verify Resource - "+customerInstanceValue+" is display in SELECT RESOURCE DropDown on a form.");

		boolean searchNodata1 = fo.searchSelectResourceNoData(supplierInstanceValue);
		TestValidation.IsTrue(searchNodata1, 
				"Verified that resource - "+supplierInstanceValue+" for which the access was not provided is not displaying in drop down list " , 
				"Could not able to verify resource - "+supplierInstanceValue+" for which the access was not provided is not displaying in drop down list" );

		boolean closeForm1 = pvp.closeForm();
		TestValidation.IsTrue(closeForm1, 
				"Closed the form - "+formName2, 
				"Could NOT close the form"+formName2);

		boolean verifyFormNotDisplay = pvp.verifyFormisNotDisplayedFromPrograms(formName);
		TestValidation.IsTrue(verifyFormNotDisplay, 
				"VERIFIED Form " + formName + " is not present in Program  - " + programName+" as it is not assigned",
				"Could NOT verify Form " + formName + " is not pressent in Program  - " + programName);

	}

	@Test(description = "Program Management - Program Viewer - Data Security: The\r\n"
			+ "Records tab for a program element only shows records the user has access to\r\n"
			+ "based on data security(16434)")
	public void TestCase_17539() {

		pvp = hp.clickProgramsMenu();
		TestValidation.Equals(pvp.error, 
				false, 
				"CLICKED on Programs  menu", 
				"Could NOT click on Programs menu");

		boolean pdSelectPrgram = pvp.selectPrograms(programName);
		TestValidation.IsTrue(pdSelectPrgram,
				"SELECTED program " + programName + " from the Program list",
				"Could NOT select program " + programName + " from the Program list");

		boolean pdSelectTab = pvp.clickProgramViewTab(PGVIEWTABS.RECORDS);
		TestValidation.IsTrue(pdSelectTab,
				"SELECTED program tab as " + PGVIEWTABS.RECORDS + " for the Program",
				"Could NOT select program tab as " + PGVIEWTABS.RECORDS + " for the Program");

		FSQABrowserRecordsPage frp = pvp.selectRecordsForViewer();
		TestValidation.Equals(frp.error, 
				false,
				"SELECTED records for viewing records of form " + formsNMLst,
				"Could NOT select records for viewing records of form " + formsNMLst);

		//Create a new page maybe for Program Viewer
		RecordFormDetails rfd1 = new RecordFormDetails();
		rfd1.FormName = formName1;
		rfd1.CompletedBy = displayName;
		boolean verifyFormDetails1 = frp.searchAndVerifyRecordsDetailsInView(rfd1);
		TestValidation.IsTrue(verifyFormDetails1, 
				"VERIFIED record details for form - " + rfd1.FormName, 
				"Could NOT verify record details for form - " + rfd1.FormName);

		RecordFormDetails rfd2 = new RecordFormDetails();
		rfd2.FormName = formName2;
		rfd2.CompletedBy = displayName;
		boolean verifyFormDetails2 = frp.searchAndVerifyRecordsDetailsInView(rfd2);
		TestValidation.IsTrue(verifyFormDetails2, 
				"VERIFIED record details for form - " + rfd2.FormName, 
				"Could NOT verify record details for form - " + rfd2.FormName);	

		boolean SelectTab = pvp.clickOnRecords();
		TestValidation.IsTrue(SelectTab,
				"SELECTED program tab as RECORDS for the Program",
				"Could NOT select program tab as RECORDS for the Program");

		boolean verifyRecordIsDisplay = pvp.verifyRecordIsDisplayedFromPrograms(formName1);
		TestValidation.IsTrue(verifyRecordIsDisplay, 
				"VERIFIED Record " + formName1 + " is present in Program  - " + programName+" as it is assigned",
				"Could NOT verify Record " + formName1 + " is pressent in Program  - " + programName);

		boolean verifyRecordNotDisplay = pvp.verifyRecordIsNotDisplayedFromPrograms(formName);
		TestValidation.IsTrue(verifyRecordNotDisplay, 
				"VERIFIED Record " + formName + " is not present in Program  - " + programName+" as it is not assigned",
				"Could NOT verify Record " + formName + " is not pressent in Program  - " + programName);

		hp.userLogout();
		lp.performLogin(adminUsername, adminPassword);
	}
	@Test(description = "Data Security - Program Viewer")
	public void TestCase_31516() {
		
		hp.userLogout();
		lp.performLogin(adminUsername, adminPassword);
		
		String programName = CommonMethods.dynamicText("PD");
		String headerName = CommonMethods.dynamicText("HE");
		String elementName = CommonMethods.dynamicText("PE");
		String newElementName = CommonMethods.dynamicText("New_PE");

		ProgramDesignerPage pdp = hp.clickProgramDesignerMenu();
		TestValidation.Equals(pdp.error, 
							  false, 
							  "CLICKED on Program Designer menu",
							  "Could NOT click on Program Designer menu");

		boolean pgSetName = pdp.setProgramName(programName);
		TestValidation.IsTrue(pgSetName, 
							  "Name SET for Program as - " + programName,
							  "Could NOT set Program name as - " + programName);

		boolean pgeHeaderName = pdp.addProgramHeader(headerName);
		TestValidation.IsTrue(pgeHeaderName, 
						      "Name SET for Program Header as - " + headerName,
						      "Could NOT set Program Header name as - " + headerName);

		boolean pgeSetName = pdp.addProgramElement(elementName);
		TestValidation.IsTrue(pgeSetName, 
							  "Name SET for Program Element as - " + elementName,
							  "Could NOT set Program Element name as - " + elementName);
		
		if(!pdp.setDescription(programDesc)){
			TCGFailureMsg = "Could NOT add description to the Details Tab - " + programDesc ;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
		
		boolean pgeSetName1 = pdp.addNewProgramElementInProgramHeader(headerName,newElementName);
		TestValidation.IsTrue(pgeSetName1, 
							  "Name SET for Program Element as - " + newElementName,
							  "Could NOT set Program Element name as - " + newElementName);
		
		if(!pdp.setDescription(programDesc)){
			TCGFailureMsg = "Could NOT add description to the Details Tab - " + programDesc ;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
		
		boolean pdAddForms = pdp.selectForms(formsNMLst);
		TestValidation.IsTrue(pdAddForms, 
							  "ADDED Forms " + formsNMLst + " to Program Element",
							  "Could NOT add Forms " + formsNMLst + " to Program Element");
		
		hp.userLogout();
		hp = lp.performLogin(userUN, GenericNewPassword);
		TestValidation.Equals(hp.error, 
				false, 
				"Login with user - "+userUN, 
				"Could Not Login with user - "+userUN);

		pvp = hp.clickProgramsMenu();
		TestValidation.Equals(pvp.error, 
				false, 
				"CLICKED on Programs  menu", 
				"Could NOT click on Programs menu");

		boolean pdSelectPrgram = pvp.selectPrograms(programName);
		TestValidation.IsTrue(pdSelectPrgram,
				"SELECTED program " + programName + " from the Program list",
				"Could NOT select program " + programName + " from the Program list");

		boolean selectPrgEle = pvp.searchSelectProgramElement(newElementName);
		TestValidation.IsTrue(selectPrgEle, 
				  "Successfully selected Program Element  - " + newElementName,
				  "Could NOT select Program Element - " + newElementName);
		
		boolean pdVerifyForms = pvp.verifyFormsFromPrograms(Arrays.asList(formName1));
		TestValidation.IsTrue(pdVerifyForms, 
				"VERIFIED Form " + formName1 + " in Program  - " + programName,
				"Could NOT verify Form " + formName1 + " in Program  - " + programName);

		boolean selectOpenForm = pvp.openForm(formName1);
		TestValidation.IsTrue(selectOpenForm, 
				"Select & opened the form - "+formName1, 
				"Could NOT open the form"+formName1);

		boolean selectResource = fo.selectResource(customerInstanceValue);
		TestValidation.IsTrue(selectResource, 
				"Verified Resource - "+customerInstanceValue+ " is displayed in SELECT RESOURCE DropDown on a form.", 
				"Failed to verify Resource - "+customerInstanceValue+" is display in SELECT RESOURCE DropDown on a form.");

		boolean searchNodata = fo.searchSelectResourceNoData(supplierInstanceValue);
		TestValidation.IsTrue(searchNodata, 
				"Verified that resource - "+supplierInstanceValue+" for which the access was not provided is not displaying in drop down list " , 
				"Could not able to verify resource - "+supplierInstanceValue+" for which the access was not provided is not displaying in drop down list" );

		boolean closeForm = pvp.closeForm();
		TestValidation.IsTrue(closeForm, 
				"Closed the form - "+formName1, 
				"Could NOT close the form"+formName1);

		boolean verifyFormNotDisplay = pvp.verifyFormisNotDisplayedFromPrograms(formName);
		TestValidation.IsTrue(verifyFormNotDisplay, 
				"VERIFIED Form " + formName + " is not present in Program  - " + programName+" as it is not assigned",
				"Could NOT verify Form " + formName + " is not pressent in Program  - " + programName);

		boolean verifyRecordIsDisplay = pvp.verifyRecordIsDisplayedFromPrograms(formName1);
		TestValidation.IsTrue(verifyRecordIsDisplay, 
				"VERIFIED Record " + formName1 + " is present in Program  - " + programName+" as it is assigned",
				"Could NOT verify Record " + formName1 + " is pressent in Program  - " + programName);

		boolean verifyRecordNotDisplay = pvp.verifyRecordIsNotDisplayedFromPrograms(formName);
		TestValidation.IsTrue(verifyRecordNotDisplay, 
				"VERIFIED Record " + formName + " is not present in Program  - " + programName+" as it is not assigned",
				"Could NOT verify Record " + formName + " is not pressent in Program  - " + programName);

		boolean selectPrgEle1 = pvp.searchSelectProgramElement(elementName);
		TestValidation.IsTrue(selectPrgEle1, 
				  "Successfully selected Program Element  - " + elementName,
				  "Could NOT select Program Element - " + elementName);
		
	}
	@AfterClass(alwaysRun = true)
	public void closeBrowser() throws InterruptedException {
		driver.close();
	}
}
