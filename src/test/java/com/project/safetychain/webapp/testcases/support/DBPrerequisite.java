package com.project.safetychain.webapp.testcases.support;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.project.safetychain.webapp.pageobjects.CommonPage;
import com.project.safetychain.webapp.pageobjects.DocumentManagementPage;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FIELD_TYPES;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FORMRESOURCETYPES;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FORMTYPES;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FormDesignParams;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FormFieldParams;
import com.project.safetychain.webapp.pageobjects.ManageLocationPage.LocationInstanceParams;
import com.project.safetychain.webapp.pageobjects.ManageResourcePage.RESOURCETYPES;
import com.project.safetychain.webapp.pageobjects.ManageResourcePage.ResourceDetailParams;
import com.project.safetychain.webapp.pageobjects.HomePage;
import com.project.safetychain.webapp.pageobjects.LoginPage;
import com.project.safetychain.webapp.pageobjects.ManageLocationPage;
import com.project.safetychain.webapp.pageobjects.ManageResourcePage;
import com.project.safetychain.webapp.pageobjects.ResourceDesignerPage;
import com.project.safetychain.webapp.pageobjects.ResourceDesignerPage.CATEGORYTYPES;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.utilities.CommonMethods;
import com.project.utilities.ControlActions;

public class DBPrerequisite extends TestBase
{

	// Pages 
	ControlActions controlActions;
	CommonPage mainPage;
	LoginPage lgnPge;
	ResourceDesignerPage resourceDesigner;
	ManageResourcePage manageResource;
	ManageLocationPage locations;
	HomePage hp;
	DocumentManagementPage dmp;
	FormDesignerPage fdp;
	
	//Classes
	FormDesignParams fp;
	FormFieldParams ffp;
	
	//Data
	public static String customersCategoryValue;
	public static String customersInstanceValue1;
	
	public static String equipmentCategoryValue;
	public static String equipmentInstanceValue1;
	
	public static String itemsCategoryValue;
	public static String itemsInstanceValue1;
	
	public static String suppliersCategoryValue;
	public static String suppliersInstanceValue1;
	
	public static String locationInstanceValue;
	public static String locationCategoryValue;
	

	public static String doctype;
	public static String image;
	public static String uploadfile;
	public static String checkFormName1;

	@BeforeClass(alwaysRun = true)
	public void groupInit() throws Exception {
		
		driver = launchbrowser();
		controlActions = new ControlActions(driver);
		controlActions.getUrl(applicationUrl);
		mainPage = new CommonPage(driver);
		lgnPge = new LoginPage(driver);
		resourceDesigner = new ResourceDesignerPage(driver);
		manageResource = new ManageResourcePage(driver);
		locations = new ManageLocationPage(driver);
		dmp = new DocumentManagementPage(driver);

		//login
		hp = lgnPge.performLogin(adminUsername, adminPassword);
		if(hp.error) {
			TCGFailureMsg = "Could NOT login to application";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}		
		
	}
	

	
	@Test(description="Add fields at form level of check form")
	public void TestCase_1() {
			
		
		// Create a variable for the connection string.
//      String connectionUrl = "jdbc:sqlserver://<server>:<port>;databaseName=AdventureWorks;user=<user>;password=<password>";
      String connectionUrl = "jdbc:sqlserver://stagesqlserver1.westus.cloudapp.azure.com;databaseName=stagesqlserver1;user=safetychain\\qauser110920"
      		+ ";password=Qual!ty@SCS";

      try {
    	  Connection connection = DriverManager.getConnection(connectionUrl); 
      	Statement stmt = connection.createStatement();
          String SQL = "SELECT TOP 10 [Id]\r\n" + 
          		",[EntityId]\r\n" + 
          		",[NotificationType]\r\n" + 
          		",[Recipients]\r\n" + 
          		",[MailSentOnDate]\r\n" + 
          		",[EmailBody]\r\n" + 
          		",[NotificationName]\r\n" + 
          		"FROM [SCS_Tenant_Reporting_test1].[NotificationManagement].[EmailTracker] order by MailSentOnDate desc;";
          ResultSet rs = stmt.executeQuery(SQL);

          // Iterate through the data in the result set and display it.
          while (rs.next()) {
              System.out.println(rs.getString("category_name") + " " + rs.getString("category_type"));
          }
      }
      // Handle any errors that may have occurred.
      catch (SQLException e) {
          e.printStackTrace();
      }
		
		locationCategoryValue = CommonMethods.dynamicText("Location_Cat");
		customersCategoryValue = CommonMethods.dynamicText("Customers_Cat");
		itemsCategoryValue = CommonMethods.dynamicText("Items_Cat");
		equipmentCategoryValue = CommonMethods.dynamicText("Equipment_Cat");
		suppliersCategoryValue = CommonMethods.dynamicText("Suppliers_Cat");
		
		//Category creation
		HashMap<String,String> categories = new HashMap<String, String>();
		categories.put(CATEGORYTYPES.CUSTOMERS, customersCategoryValue);
		categories.put(CATEGORYTYPES.EQUIPMENT, equipmentCategoryValue);
		categories.put(CATEGORYTYPES.ITEMS, itemsCategoryValue);
		categories.put(CATEGORYTYPES.SUPPLIERS, suppliersCategoryValue);
		categories.put(CATEGORYTYPES.LOCATION, locationCategoryValue);
		resourceDesigner = hp.clickResourceDesignerMenu();
		boolean createCategory = resourceDesigner.createCategory(categories);		
		TestValidation.IsTrue(createCategory, 
				"Created Resource categories successfully", 
				"Could NOT create Resource categories");
		
		
		locationCategoryValue = CommonMethods.dynamicText("LocCat1");
		locationInstanceValue = CommonMethods.dynamicText("LocInst1");
		
		
		//Location instance
		HashMap<String,Boolean> locInstance = new HashMap<String, Boolean>();
		locInstance.put(locationInstanceValue, true);
		LocationInstanceParams lip = new LocationInstanceParams();
		lip.CategoryName = locationCategoryValue;
		lip.NumberFieldValue = 10;
		lip.TextFieldValue = "XYZ";
		lip.InstanceName = locInstance;
		ManageLocationPage mlp = hp.clickLocationsMenu();		
		boolean createLocation = mlp.createLocation(lip);		
		TestValidation.IsTrue(createLocation, 
				"Created Location instance successfully", 
				"Could NOT create Location instance");

		//Resource creation
		HashMap<String,Boolean> customersInstance = new HashMap<String, Boolean>();
		customersInstance.put(customersInstanceValue1, true);
		ResourceDetailParams rd1 = new ResourceDetailParams();
		rd1.CategoryName = customersCategoryValue;
		rd1.CategoryType = RESOURCETYPES.CUSTOMERS;
		rd1.NumberFieldValue = 30;
		rd1.TextFieldValue = "ABC";
		rd1.InstanceNames = customersInstance;
		rd1.LocationInstanceValue = locationInstanceValue;
		ManageResourcePage mrp = hp.clickResourcesMenu();
		
		boolean createResourceLinkLocation1 = mrp.createResourceLinkLocation(rd1);		
		TestValidation.IsTrue(createResourceLinkLocation1, 
				"Created Instances for resource - " + customersCategoryValue + " successfully", 
				"Could NOT create Instances for resource - " + customersCategoryValue);
		
		
		HashMap<String,Boolean> equipmentInstance = new HashMap<String, Boolean>();
		equipmentInstance.put(equipmentInstanceValue1, true);
		ResourceDetailParams rd2 = new ResourceDetailParams();
		rd2.CategoryName = equipmentCategoryValue;
		rd2.CategoryType = RESOURCETYPES.EQUIPMENT;
		rd2.NumberFieldValue = 30;
		rd2.TextFieldValue = "ABC";
		rd2.InstanceNames = equipmentInstance;
		rd2.LocationInstanceValue = locationInstanceValue;
		mrp = hp.clickResourcesMenu();
		
		boolean createResourceLinkLocation2 = mrp.createResourceLinkLocation(rd2);		
		TestValidation.IsTrue(createResourceLinkLocation2, 
				"Created Instances for resource - " + equipmentCategoryValue + " successfully", 
				"Could NOT create Instances for resource - " + equipmentCategoryValue);
		
		HashMap<String,Boolean> itemsInstance = new HashMap<String, Boolean>();
		itemsInstance.put(itemsInstanceValue1, true);
		ResourceDetailParams rd3 = new ResourceDetailParams();
		rd3.CategoryName = itemsCategoryValue;
		rd3.CategoryType = RESOURCETYPES.ITEMS;
		rd3.NumberFieldValue = 30;
		rd3.TextFieldValue = "ABC";
		rd3.InstanceNames = itemsInstance;
		rd3.LocationInstanceValue = locationInstanceValue;
		mrp = hp.clickResourcesMenu();
		
		boolean createResourceLinkLocation3 = mrp.createResourceLinkLocation(rd3);		
		TestValidation.IsTrue(createResourceLinkLocation3, 
				"Created Instances for resource - " + itemsCategoryValue + " successfully", 
				"Could NOT create Instances for resource - " + itemsCategoryValue);
		
		HashMap<String,Boolean> suppliersInstance = new HashMap<String, Boolean>();
		suppliersInstance.put(suppliersInstanceValue1, true);
		ResourceDetailParams rd4 = new ResourceDetailParams();
		rd4.CategoryName = suppliersCategoryValue;
		rd4.CategoryType = RESOURCETYPES.SUPPLIERS;
		rd4.NumberFieldValue = 30;
		rd4.TextFieldValue = "ABC";
		rd4.InstanceNames = suppliersInstance;
		rd4.LocationInstanceValue = locationInstanceValue;
		mrp = hp.clickResourcesMenu();
		
		boolean createResourceLinkLocation4 = mrp.createResourceLinkLocation(rd4);		
		TestValidation.IsTrue(createResourceLinkLocation4, 
				"Created Instances for resource - " + suppliersCategoryValue + " successfully", 
				"Could NOT create Instances for resource - " + suppliersCategoryValue);
		
		doctype = CommonMethods.dynamicText("Doc");
		dmp = mainPage.clickdocumentsmenu();
		boolean doctypecreation = dmp.createDocType(doctype);
		TestValidation.IsTrue(doctypecreation, 
				"Created document type - " + doctype, 
				"Document Type creation failed");

		image = "upload.png";		
		uploadfile = System.getProperty("user.dir") + "\\test-data-files\\UploadDocuments\\"+image;
		boolean upload = dmp.uploadDocument(uploadfile,doctype);
		TestValidation.IsTrue(upload, 
				"Created document type - " + doctype+ "and uploaded" +uploadfile, 
				"Document Type and document upload failed");
	
		
		// Form creation		
		
		fdp = hp.clickFormDesignerMenu();
		if(fdp.error) {
			TCGFailureMsg = "Could NOT navigate to Admin Tools - Form Designer";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		checkFormName1 = CommonMethods.dynamicText("Automation_CheckForm");

		HashMap<String, List<String>> resource1 = new HashMap<String, List<String>>();
		resource1.put(FORMRESOURCETYPES.CUSTOMERS, Arrays.asList(customersCategoryValue));

		
		String field1 = "FreeText1",
				field2 = "ParagraphText1",
				field3 = "SelectOne1",
				field4 = "SelectMultiple1",
				field5 = "Numeric1",
				field6 = "Date1",
				field7 = "Time1",
				field8 = "DateTime1";
		
		List<String> field4Options = Arrays.asList("10","20","30");
		
		HashMap<String, List<String>> fields = new HashMap<String, List<String>>();
		fields.put(FIELD_TYPES.FREE_TEXT, Arrays.asList(field1));
		fields.put(FIELD_TYPES.PARAGRAPH_TEXT, Arrays.asList(field2));
		fields.put(FIELD_TYPES.SELECT_ONE, Arrays.asList(field3));
		fields.put(FIELD_TYPES.SELECT_MULTIPLE, Arrays.asList(field4));		
		fields.put(FIELD_TYPES.NUMERIC, Arrays.asList(field5));
		fields.put(FIELD_TYPES.DATE, Arrays.asList(field6));
		fields.put(FIELD_TYPES.TIME, Arrays.asList(field7));
		fields.put(FIELD_TYPES.DATE_TIME, Arrays.asList(field8));
		
		ffp = new FormFieldParams();
		ffp.FieldDetails = fields;
		ffp.SelectOneMultipleOptions = field4Options;
		
		fp = new FormDesignParams();
		fp.FormType = FORMTYPES.CHECK;
		fp.FormName = checkFormName1;
		fp.SelectResources = resource1;
		fp.DesignFields = Arrays.asList(ffp);
		fp.ReleaseForm = true;
		
		
		if(!fdp.createAndReleaseForm(fp)) {
			TCGFailureMsg = "Could NOT create form - " + checkFormName1;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
		
	}
	
	
	
	
	
	
}
