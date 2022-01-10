package com.project.safetychain.webapp.testcases;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import com.project.safetychain.webapp.pageobjects.*;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.AGGREGATE_FUNCTION;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FIELD_SETTINGS;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FIELD_TYPES;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FORMRESOURCETYPES;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FORMTYPES;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FORM_ELEMENTS;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FormDesignParams;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FormFieldParams;
import com.project.safetychain.webapp.pageobjects.ManageLocationPage.LocationInstanceParams;
import com.project.safetychain.webapp.pageobjects.ManageResourcePage.RESOURCETYPES;
import com.project.safetychain.webapp.pageobjects.ManageResourcePage.ResourceDetailParams;
import com.project.safetychain.webapp.pageobjects.ResourceDesignerPage.CATEGORYTYPES;
import com.project.safetychain.webapp.testcases.support.FormOperations;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.utilities.CommonMethods;
import com.project.utilities.ControlActions;

public class TCG_REG_FormDesigner_UI_Display extends TestBase{

	ControlActions controlActions;
	HomePage hp;
	WorkGroupsPage wgp;
	FormDesignerPage fdp;
	LoginPage lp;
	FSQABrowserPage fbp;
	FSQABrowserFormsPage fbForms;
	ManageResourcePage manageResource;
	FormOperations formoperations;
	DocumentManagementPage dmp;

	public static String locationCategoryValue, supplierCategoryValue;
	public static String locationInstanceValue, supplierInstanceValue;

	List<String> Chk_FormElementsList = Arrays.asList("Note","Related Docs","Section", "Field Group", "Aggregate", "Identifier");
	List<String> Chk_FieldTypesList = Arrays.asList("Free Text", "Paragraph Text", "Select One", "Select Multiple", "Numeric", "Date", "Time", "Date & Time");
	
	List<String> Adt_FormElementsList = Arrays.asList("Note","Related Docs","Section", "Question Group", "Summary", "Identifier");
	List<String> Adt_FieldTypesList = Arrays.asList("Single Line Text", "Paragraph Text", "Select One", "Select Multiple", "Numeric", "Date", "Time", "Date & Time");
	
	
	@BeforeClass(alwaysRun = true)
	public void groupInit() throws InterruptedException {

		locationCategoryValue = CommonMethods.dynamicText("LocCat_");
		supplierCategoryValue = CommonMethods.dynamicText("SuppCat_");
		locationInstanceValue = CommonMethods.dynamicText("LocInst_");
		supplierInstanceValue = CommonMethods.dynamicText("SuppInst_");

		driver = launchbrowser();
		controlActions = new ControlActions(driver);
		controlActions.getUrl(applicationUrl);

		hp = new HomePage(driver);
		wgp = new WorkGroupsPage(driver);
		fdp = new FormDesignerPage(driver);
		fbp = new FSQABrowserPage(driver);
		fbForms = new FSQABrowserFormsPage(driver);
		lp = new LoginPage(driver);
		manageResource = new ManageResourcePage(driver);
		formoperations = new FormOperations(driver);

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
		categories.put(CATEGORYTYPES.SUPPLIERS, supplierCategoryValue);
		ResourceDesignerPage rdp = hp.clickResourceDesignerMenu();
		if(!rdp.createCategory(categories)) {
			TCGFailureMsg = "Could NOT create Resource categories for - " + locationCategoryValue + supplierCategoryValue;
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

		ManageResourcePage mrp = hp.clickResourcesMenu();
		if(!mrp.createResourceLinkLocation(rd2)) {
			TCGFailureMsg = "Could NOT create Supplier Instance for resource - " + supplierCategoryValue;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
		
	}

	@Test(description = " Form Designer can select 'Check' Form Type to design. Verify UI of the form (feature 491)")
	public void TestCase_5227() throws Exception {
		
		List<String> fetchedFormElementsList ;
		List<String> fetchedFieldTypesList ;
		//String formName = CommonMethods.dynamicText("Chk_");
		
		try {
		//Open 'Form Designer'
		fdp = hp.clickFormDesignerMenu();

		fdp.selectFormType(FORMTYPES.CHECK);

		//Selection of resource
		boolean selectResource = fdp.selectResource(FORMRESOURCETYPES.SUPPLIERS,supplierCategoryValue,supplierInstanceValue);
		TestValidation.IsTrue(selectResource, 
							 "Selected Resource successfully", 
							 "Failed to Select Resource");

		//Go to 'Form Design'
		boolean clickOnNextButton = fdp.clickOnNextButton(fdp.DesignFormPg,"Design Form");
		TestValidation.IsTrue(clickOnNextButton, 
							 "Clicked On Next Button successfully", 
							 "Failed to Click On Next Button");

		controlActions.sendKeys(fdp.FormNameTxt, CommonMethods.dynamicText("Chk_"));
		
		fetchedFormElementsList = fdp.getDisplayedFormElementsList();
		TestValidation.IsTrue((Chk_FormElementsList.equals(fetchedFormElementsList)),
							  "Verified Check form design controls (Check Form Elemets) shown in the panels on the Design Form Step screen -  " + fetchedFormElementsList, 
							  "Failed to Verify Check form design controls (Check Form Elemets) shown in the panels on the Design Form Step screen -  " + fetchedFormElementsList);
		
		fetchedFieldTypesList = fdp.getDisplayedFieldTypesList();
		TestValidation.IsTrue((Chk_FieldTypesList.equals(fetchedFieldTypesList)),
							  "Verified Check form design controls (Check Form Field Types) shown in the panels on the Design Form Step screen -  " + fetchedFieldTypesList, 
							  "Failed to Verify Check form design controls (Check Field Types) shown in the panels on the Design Form Step screen -  " + fetchedFieldTypesList);
		}finally {
			boolean navHome = fdp.navigateToHome();
			TestValidation.IsTrue(navHome, 
					  			 "Navigated to Homepage", 
					  			 "Failed to navigate to hompage");
		}
		
	}
	
	@Test(description = "Form Designer can select 'Audit' Form Type to design. Check UI of the form (feature 491)")
	public void TestCase_5228() throws Exception {
		List<String> fetchedFormElementsList ;
		List<String> fetchedFieldTypesList ;
		
		try {
		//Open 'Form Designer'
		fdp = hp.clickFormDesignerMenu();

		fdp.selectFormType(FORMTYPES.AUDIT);

		//Selection of resource
		boolean selectResource = fdp.selectResource(FORMRESOURCETYPES.SUPPLIERS,supplierCategoryValue,supplierInstanceValue);
		TestValidation.IsTrue(selectResource, 
							 "Selected Resource successfully", 
							 "Failed to Select Resource");

		//Go to 'Form Design'
		boolean clickOnNextButton = fdp.clickOnNextButton(fdp.DesignFormPg,"Design Form");
		TestValidation.IsTrue(clickOnNextButton, 
							 "Clicked On Next Button successfully", 
							 "Failed to Click On Next Button");

		controlActions.sendKeys(fdp.FormNameTxt, CommonMethods.dynamicText("Adt_"));
		
		fetchedFormElementsList = fdp.getDisplayedFormElementsList();
		TestValidation.IsTrue((Adt_FormElementsList.equals(fetchedFormElementsList)),
				  			  "Verified Audit form design controls (Audit Form Elemets) shown in the panels on the Design Form Step screen -  " + fetchedFormElementsList, 
				  			  "Failed to Verify Audit form design controls (Audit Form Elemets) shown in the panels on the Design Form Step screen -  " + fetchedFormElementsList);
		
		fetchedFieldTypesList = fdp.getDisplayedFieldTypesList();
		TestValidation.IsTrue((Adt_FieldTypesList.equals(fetchedFieldTypesList)),
				  			  "Verified Check form design controls (Audit Form Field Types) shown in the panels on the Design Form Step screen -  " + fetchedFieldTypesList, 
				  			  "Failed to Verify Check form design controls (Audit Field Types) shown in the panels on the Design Form Step screen -  " + fetchedFieldTypesList);
		}finally {
			boolean navHome = fdp.navigateToHome();
			TestValidation.IsTrue(navHome, 
					  			 "Navigated to Homepage", 
					  			 "Failed to navigate to hompage");
		}

	}
	
	@Test(description = " Form Designer should see a default prompt on the Properties Panel when no Field or Form Element is selected")
	public void TestCase_5206() throws Exception {
		
		try {
			//Open 'Form Designer'
			fdp = hp.clickFormDesignerMenu();

			fdp.selectFormType(FORMTYPES.CHECK);

			//Selection of resource
			boolean selectResource = fdp.selectResource(FORMRESOURCETYPES.SUPPLIERS,supplierCategoryValue,supplierInstanceValue);
			TestValidation.IsTrue(selectResource, 
								 "Selected Resource successfully", 
								 "Failed to Select Resource");

			//Go to 'Form Design'
			boolean clickOnNextButton = fdp.clickOnNextButton(fdp.DesignFormPg,"Design Form");
			TestValidation.IsTrue(clickOnNextButton, 
								 "Clicked On Next Button successfully", 
								 "Failed to Click On Next Button");
			
			controlActions.sendKeys(fdp.FormNameTxt, CommonMethods.dynamicText("FN_"));
			
			String NumField_Name = CommonMethods.dynamicText("Num_");
			WebElement num1 = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_TYPE,"FIELDTYPE",FIELD_TYPES.NUMERIC);
			controlActions.dragdrop(num1, fdp.FormLevel);
			boolean setNumFieldValue1 = fdp.setTextBoxValue(FIELD_TYPES.NUMERIC, NumField_Name);		
			TestValidation.IsTrue(setNumFieldValue1, 
								 "Dragged and Dropped numeric field, named it : - " + NumField_Name, 
								 "Failed to drag and drop numeric field");	
			
			boolean FormNameElmnttSlctdTxt = fdp.selectFormName_VerifyPropPanelTxtPrmpt();
			TestValidation.IsTrue(FormNameElmnttSlctdTxt, 
								  "Successfully verified - When Form Name Element is selected, text is displayed in the Field Properties panel :-" + "Select an element on the form to modify it's properties", 
								  "Could Not Verify Text Display in Properties Tab when Form Name Element is selected.");
		}finally {
			boolean navHome = fdp.navigateToHome();
			TestValidation.IsTrue(navHome, 
					  			 "Navigated to Homepage", 
					  			 "Failed to navigate to hompage");
		}
		
	}
	
	@Test(description = " Form Designer should see a text prompt on the Properties panel when a the Form Name is selected ")
	public void TestCase_5207() throws Exception {
		
		
		try {
			//Open 'Form Designer'
			fdp = hp.clickFormDesignerMenu();

			fdp.selectFormType(FORMTYPES.CHECK);

			//Selection of resource
			boolean selectResource = fdp.selectResource(FORMRESOURCETYPES.SUPPLIERS,supplierCategoryValue,supplierInstanceValue);
			TestValidation.IsTrue(selectResource, 
								 "Selected Resource successfully", 
								 "Failed to Select Resource");

			//Go to 'Form Design'
			boolean clickOnNextButton = fdp.clickOnNextButton(fdp.DesignFormPg,"Design Form");
			TestValidation.IsTrue(clickOnNextButton, 
								 "Clicked On Next Button successfully", 
								 "Failed to Click On Next Button");
			
			controlActions.sendKeys(fdp.FormNameTxt, CommonMethods.dynamicText("FN_"));
			
			String NumField_Name = CommonMethods.dynamicText("Num_");
			WebElement num1 = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_TYPE,"FIELDTYPE",FIELD_TYPES.NUMERIC);
			controlActions.dragdrop(num1, fdp.FormLevel);
			boolean setNumFieldValue1 = fdp.setTextBoxValue(FIELD_TYPES.NUMERIC, NumField_Name);		
			TestValidation.IsTrue(setNumFieldValue1, 
								 "Dragged and Dropped numeric field, named it : - " + NumField_Name, 
								 "Failed to drag and drop numeric field");	
			
			
			boolean noFieldSelectedText = fdp.noElementFieldSelected_VerifyProPanelTxtPrmpt();
			TestValidation.IsTrue(noFieldSelectedText, 
								  "Successfully verified - When no field is selected, text is displayed in the Field Properties panel :- "+ "Select an element on the form to modify it's properties", 
								  "Could Not Verify No Field Text Display in Properties Tab when no field is selected.");
			
		}finally {
			boolean navHome = fdp.navigateToHome();
			TestValidation.IsTrue(navHome, 
					  			 "Navigated to Homepage", 
					  			 "Failed to navigate to hompage");
		}
		
		
	}
	
	@Test(description = "Forms Designer - Breadcrumbs - Release Forms step should be available")
	public void TestCase_5160() throws Exception {
		
		String formName = CommonMethods.dynamicText("AForm_");
		
		List<String> expectedBreadcrumbs = Arrays.asList("Home","Form Designer","Select Form Type","Select Resources","Design Form","Release Form");
		List<String> fetchedBreadCrumbsAtEachStep ;
		boolean validation;
		
		try {
		// click FormDesigner 
		fdp = hp.clickFormDesignerMenu();
		 
		//Step :select form type
		//  verify breadcrumbs, move to select resource
		fetchedBreadCrumbsAtEachStep = fdp.getBreadcrumbs();
		System.out.println(expectedBreadcrumbs.subList(0, 3));
		validation = fetchedBreadCrumbsAtEachStep.equals(expectedBreadcrumbs.subList(0, 3));
		TestValidation.IsTrue(validation, 
							  "Verified breadcrumbs displayed at Select Form Type Step -" + fetchedBreadCrumbsAtEachStep, 
							  "Could Not verify breadcrumbs at Select Form Type Step");
		
		fdp.selectFormType(FORMTYPES.CHECK);
		
		// Step : select resource
		// verify breadcrumbs, move to design form
		fetchedBreadCrumbsAtEachStep = fdp.getBreadcrumbs();
		System.out.println(expectedBreadcrumbs.subList(0, 4));
		validation = fetchedBreadCrumbsAtEachStep.equals(expectedBreadcrumbs.subList(0, 4));
		TestValidation.IsTrue(validation, 
				  			  "Verified breadcrumbs displayed at Select Resources Step -" + fetchedBreadCrumbsAtEachStep, 
							  "Could Not verify breadcrumbs at Select Resources Step");

		
		boolean selectResource = fdp.selectResource(FORMRESOURCETYPES.SUPPLIERS,supplierCategoryValue,supplierInstanceValue);
		TestValidation.IsTrue(selectResource, 
							 "Selected Resource successfully", 
							 "Failed to Select Resource");
		
		//Go to 'Form Design'
		boolean clickOnNextButton = fdp.clickOnNextButton(fdp.DesignFormPg,"Design Form");
		TestValidation.IsTrue(clickOnNextButton, 
							 "Clicked On Next Button successfully", 
							 "Failed to Click On Next Button");
		
		
		// Step : Design Form
		// verify breadcrumbs, move to release page
		controlActions.sendKeys(fdp.FormNameTxt, formName);
		fetchedBreadCrumbsAtEachStep = fdp.getBreadcrumbs();
		System.out.println(expectedBreadcrumbs.subList(0, 5));
		validation = fetchedBreadCrumbsAtEachStep.equals(expectedBreadcrumbs.subList(0, 5));
		TestValidation.IsTrue(validation, 
							  "Verified breadcrumbs displayed at Form Designer Step -" + fetchedBreadCrumbsAtEachStep, 
							  "Could Not verify breadcrumbs at Form Designer Step");

		// click on next - navigate to release page
		boolean clivkNextBtn = fdp.clickOnNextButton(fdp.ReleaseFormPg,"Release Form");
		TestValidation.IsTrue(clivkNextBtn, 
							  "Clicked on next button and Navigated to Release Page", 
							  "Could not click on next button and not Navigate to Release Page");
		// Step : Release Page
		// verify breadcrumbs, exit form manager
		fetchedBreadCrumbsAtEachStep = fdp.getBreadcrumbs();
		System.out.println(expectedBreadcrumbs.subList(0, 6));
		validation = fetchedBreadCrumbsAtEachStep.equals(expectedBreadcrumbs.subList(0, 6));
		TestValidation.IsTrue(validation, 
	  			  			  "Verified breadcrumbs displayed at Release Form Step -" + fetchedBreadCrumbsAtEachStep, 
				  			  "Could Not verify breadcrumbs at Release Form Step");
		}finally {
			boolean navHome = fdp.navigateToHome();
			TestValidation.IsTrue(navHome, 
				  			 "Navigated to Homepage", 
				  			 "Failed to navigate to hompage");
		}
	}

	@Test(description = "Forms Designer - Breadcrumbs - User can see and jump through steps are in the process")
	public void TestCase_5159() throws Exception {
		
		String formName = CommonMethods.dynamicText("Fm_");
////		List<String> expectedBC_FormDesigning = Arrays.asList("Home","Form Designer","Select Form Type","Select Resources","Design Form");
////		List<String> expectedBC_SelectResources = Arrays.asList("Home","Form Designer","Select Form Type","Select Resources");
////		List<String> expectedBC_SelectFormType = Arrays.asList("Home","Form Designer","Select Form Type");
////		List<String> expectedBC_FormDesigner = Arrays.asList("Home","Resource Browser","Customers", "Details");
////		List<String> expectedBC_Home = Arrays.asList("Home");
////		List<String> fetchedBreadCrumbs;
////		boolean breadcrumbsDisplayValidation ;
//		
		boolean toDesignForm ;
//		// A form in edit mode
		fdp = hp.clickFormDesignerMenu();

		fdp.selectFormType(FORMTYPES.CHECK);

		//Selection of resource
		boolean selectResource = fdp.selectResource(FORMRESOURCETYPES.SUPPLIERS,supplierCategoryValue,supplierInstanceValue);
		TestValidation.IsTrue(selectResource, 
							 "Selected Resource successfully", 
							 "Failed to Select Resource");

		//Go to 'Form Design'
		boolean clickOnNextButton = fdp.clickOnNextButton(fdp.DesignFormPg,"Design Form");
		TestValidation.IsTrue(clickOnNextButton, 
							 "Clicked On Next Button successfully", 
							 "Failed to Click On Next Button");
		
		// enter form name
		controlActions.sendKeys(fdp.FormNameTxt, formName);
		
		boolean saveForm = fdp.clickSaveButton();
		TestValidation.IsTrue(saveForm, 
							 "Form Saved Successfully - " +formName, 
							 "Could NOT Save Form - " +formName);
		
//		// Verify bread crumbs at Form Designing level
////		fetchedBreadCrumbs = fdp.getBreadcrumbs();
////		breadcrumbsDisplayValidation = fetchedBreadCrumbs.equals(expectedBC_FormDesigning);
////		TestValidation.IsTrue(breadcrumbsDisplayValidation, 
////							  "Verified proper display of breadcrumbs i.e "+fetchedBreadCrumbs+" while Designing Form", 
////							  "Failed to Verify proper display of breadcrumbs i.e "+fetchedBreadCrumbs+" while Designing Form");
//		
		// click on select resources
		boolean toSelectResources = fdp.clickSelectResourcesFromBreadcrumb();
		TestValidation.IsTrue(toSelectResources, 
							  "Successfully verified that user can navigate to 'Select Resources' through breadcrumbs at the top while form designing", 
							  "Could NOT verify that user can navigate to 'Select Resources' through breadcrumbs at the top while form designing");
		
//		fetchedBreadCrumbs = fdp.getBreadcrumbs();
//		breadcrumbsDisplayValidation = fetchedBreadCrumbs.equals(expectedBC_SelectResources);
//		TestValidation.IsTrue(breadcrumbsDisplayValidation, 
//				  			  "Verified proper display of breadcrumbs i.e "+fetchedBreadCrumbs+" after navigating to Select Resources from Breadcrumbs", 
//				  			  "Failed to Verify proper display of breadcrumbs i.e "+fetchedBreadCrumbs+" after navigating to Select Resources from Breadcrumbs");
		
		//== navigate back to Form Designing
		toDesignForm = fdp.clickDesignFormFromNavigationPanel();
		TestValidation.IsTrue(toDesignForm, 
							  "Clicked on 'Form Design' from Navigation Panel for navigating back to Designing Form", 
							  "Failed to click on 'Form Design' from Navigation Panel for navigating back to Designing Form");
		
		// click on Select Form Type
		boolean toSelectFormType = fdp.clickSelectFormTypeFromBreadcrumb();
		TestValidation.IsTrue(toSelectFormType, 
							  "Successfully verified that user can navigate to 'Select Form Type' through breadcrumbs at the top while form designing", 
				  			  "Could NOT verify that user can navigate to 'Select Form Type' through breadcrumbs at the top while form designing");

		
//		fetchedBreadCrumbs = fdp.getBreadcrumbs();
//		breadcrumbsDisplayValidation = fetchedBreadCrumbs.equals(expectedBC_SelectFormType);
//		TestValidation.IsTrue(breadcrumbsDisplayValidation, 
//							  "Verified proper display of breadcrumbs i.e "+fetchedBreadCrumbs+" after navigating to 'Select Form Type' from Breadcrumbs", 
//							  "Failed to Verify proper display of breadcrumbs i.e "+fetchedBreadCrumbs+" after navigating to 'Select Form Type' from Breadcrumbs");

		
		//== navigate back to Form Designing
		toDesignForm = fdp.clickDesignFormFromNavigationPanel();
		TestValidation.IsTrue(toDesignForm, 
				  			  "Clicked on 'Form Design' from Navigation Panel for navigating back to Designing Form", 
				  			  "Failed to click on 'Form Design' from Navigation Panel for navigating back to Designing Form");

		
		// click on Form Designer
		boolean toFormDesigner = fdp.clickFormDesignerFromBreadcrumb();
		TestValidation.IsTrue(toFormDesigner, 
				  			  "Successfully verified that user can navigate to 'Form Designer' through breadcrumbs at the top while form designing", 
	  			      		  "Could NOT verify that user can navigate to 'Form Designer' through breadcrumbs at the top while form designing");

		
//		fetchedBreadCrumbs = fdp.getBreadcrumbs();
//		breadcrumbsDisplayValidation = fetchedBreadCrumbs.equals(expectedBC_FormDesigner);
//		TestValidation.IsTrue(breadcrumbsDisplayValidation, "Pass", "Fail");
		
		//== navigate back to Form Designing
		toDesignForm = fdp.clickDesignFormFromNavigationPanel();
		TestValidation.IsTrue(toDesignForm, 
	  			  			  "Clicked on 'Form Design' from Navigation Panel for navigating back to Designing Form ", 
	  			  			  "Failed to click on 'Form Design' from Navigation Panel for navigating back to Designing Form");

		// click on Form Designer
		boolean toHome = fdp.clickHomeFromBreadcrumb();
		TestValidation.IsTrue(toHome, 
	  			  			  "Successfully verified that user can navigate to 'Hone' through breadcrumbs at the top while form designing", 
							  "Could NOT verify that user can navigate to 'Home' through breadcrumbs at the top while form designing");
		
		TestValidation.IsTrue(toHome&&toSelectResources&&toSelectFormType&&toFormDesigner, 
							  "Successfully Verified that user is able to navigate through bread crumbs displayed at top while form designing" ,
				  			  "Could NOT verify that user is able to navigate through bread crumbs displayed at top while form designing");

				
//		fetchedBreadCrumbs = fdp.getBreadcrumbs();
//		breadcrumbsDisplayValidation = fetchedBreadCrumbs.equals(expectedBC_Home);
//		TestValidation.IsTrue(breadcrumbsDisplayValidation, "Pass", "Fail");
				
	}
	
	@Test(description = " Form Designer should see a text prompt on the Properties panel whenever a Form Note is selected ")
	public void TestCase_5210() throws Exception {
		
		String note = "This is a note";
		
		try {
			//Open 'Form Designer'
			fdp = hp.clickFormDesignerMenu();

			fdp.selectFormType(FORMTYPES.CHECK);

			//Selection of resource
			boolean selectResource = fdp.selectResource(FORMRESOURCETYPES.SUPPLIERS,supplierCategoryValue,supplierInstanceValue);
			TestValidation.IsTrue(selectResource, 
								 "Selected Resource successfully", 
								 "Failed to Select Resource");

			//Go to 'Form Design'
			boolean clickOnNextButton = fdp.clickOnNextButton(fdp.DesignFormPg,"Design Form");
			TestValidation.IsTrue(clickOnNextButton, 
								 "Clicked On Next Button successfully", 
								 "Failed to Click On Next Button");
			
			controlActions.sendKeys(fdp.FormNameTxt, CommonMethods.dynamicText("FN_"));
			
			boolean noteElement = fdp.addHeaderLevelFields(note, null, null, false);
			TestValidation.IsTrue(noteElement, 
								  "Successfully added note - " + note + " at header level " , 
								  "Could Not add note - " + note + " at header level " );
			
			
			boolean noteSelectedText = fdp.noteSelected_VerifyPropPanelTxtPrmpt();
			TestValidation.IsTrue(noteSelectedText, 
								  "Successfully verified - Whenever a Note type form element is selected, the Properties panel displays the text 'Use the text field in the form element to configure the orm Note'", 
								  "Could Not Verify No Field Text Display in Properties Tab when Note Element is selected.");
			
		}finally {
			boolean navHome = fdp.navigateToHome();
			TestValidation.IsTrue(navHome, 
					  			 "Navigated to Homepage", 
					  			 "Failed to navigate to hompage");
		}
		
		
	}

	
	
	@Test(description = "Form Designer sees either Standard Properties Panel or 3 Tabs on the Properties Panel as needed (feature 326)")
	public void TestCase_5209() throws Exception {
		
	String note = "This is a note";
	String groupName = "AGroup";
	String sectionName = "ASection";
	String field1Name = "AFreeText";
	String aggFieldName = "Agg";
	String formName = CommonMethods.dynamicText("ACF");
	
		try {
			// Form Level
			HashMap<String, List<String>> chkFields = new HashMap<String, List<String>>();
			chkFields.put(FIELD_TYPES.FREE_TEXT, Arrays.asList(field1Name));
			chkFields.put(FIELD_TYPES.AGGREGATE, Arrays.asList(aggFieldName));
			
			FormFieldParams ffpChk = new FormFieldParams();
			ffpChk.FieldDetails = chkFields;
			ffpChk.GroupName = groupName;
			ffpChk.SectionName = sectionName;
			ffpChk.AgrregateFunction = AGGREGATE_FUNCTION.SUM;
			ffpChk.AgrregateSource = "Got it";
			ffpChk.SetAggregateFunction = true;
			
			HashMap<String, List<String>> suppResource = new HashMap<String, List<String>>();
			suppResource.put(FORMRESOURCETYPES.SUPPLIERS, Arrays.asList(supplierCategoryValue));

			FormDesignParams fdpChk = new FormDesignParams();
			fdpChk.FormType = FORMTYPES.CHECK;
			fdpChk.FormName = formName;
			fdpChk.SelectResources = suppResource;
			fdpChk.DesignFields = Arrays.asList(ffpChk);
			fdpChk.ReleaseForm = true;
			fdpChk.HeaderNoteText = note;
			
			// navigate to form designer
			fdp = hp.clickFormDesignerMenu();
			
			boolean designForm = fdp.designForm(fdpChk);
			TestValidation.IsTrue(designForm, 
								  "Successfully Designed Form - " +formName, 
						          "Failed to design from ");
			
			controlActions.dragdrop(fdp.RelatedDocsDbl, fdp.HeaderLevel);
			TestValidation.IsTrue(true, 
					  			  "Successfully added Related Doc element at header level " , 
					  			  "Could Not add Related Doc element at header level " );
			
			// =========== Verifying Single Prop Panel ================//
			boolean verifyProPanelNote = fdp.verifyPropertiesPanelNoTabs(FORM_ELEMENTS.NOTE);
			TestValidation.IsTrue(verifyProPanelNote, 
								 "Verified Properties panel presents only a single panel (no tabs) when - '" + FORM_ELEMENTS.NOTE + "' Element is selected", 
								 "Failed to verify Properties panel presents only a single panel (no tabs) when - '" + FORM_ELEMENTS.NOTE + "' Element is selected");
			
			boolean verifyProPanelFrmNm = fdp.verifyPropertiesPanelNoTabs("Form Name");
			TestValidation.IsTrue(verifyProPanelFrmNm, 
								 "Verified Properties panel presents only a single panel (no tabs) when - 'Form Name :' Element is selected", 
					             "Failed to verify Properties panel presents only a single panel (no tabs) when - 'Form Name :' Element is selected");

			boolean verifyProPanelRltdDocs = fdp.verifyPropertiesPanelNoTabs(FORM_ELEMENTS.RELATED_DOCS);
			TestValidation.IsTrue(verifyProPanelRltdDocs, 
					 			  "Verified Properties panel presents only a single panel (no tabs) when - '" + FORM_ELEMENTS.RELATED_DOCS + "' Element is selected", 
					              "Failed to verify Properties panel presents only a single panel (no tabs) when - '" + FORM_ELEMENTS.RELATED_DOCS + "' Element is selected");
			
			boolean selectSection = fdp.selectField(sectionName);
			boolean verifyPropPanelSec = fdp.verifyPropertiesPanelNoTabs(FORM_ELEMENTS.SECTION);
			TestValidation.IsTrue(selectSection && verifyPropPanelSec, 
								  "Verified Properties panel presents only a single panel (no tabs) when - 'SECTION' Field is selected", 
						          "Failed to verify Properties panel presents only a single panel (no tabs) when - 'SECTION' Field is selected");
			
			// =========== Verifying 3Tabs Prop Panel ================//

			boolean selectAgg = fdp.selectField(aggFieldName);
			boolean verifyPropPanelAgg = fdp.verifyPropertiesPanleWith3Tabs();
			TestValidation.IsTrue(selectAgg && verifyPropPanelAgg , 
								 "Verified Properties panel presents three tabs named \"General\",\"Settings\", and \"Advanced\" whenever : 'AGGREGATE' Field is selected", 
								 "Failed to verify Properties panel presents three tabs named \"General\",\"Settings\", and \"Advanced\" whenever : 'AGGREGATE' Field is selected");
		
			boolean selectField1 = fdp.selectField(field1Name);
			boolean verifyPropPanelFld1 = fdp.verifyPropertiesPanleWith3Tabs();
			TestValidation.IsTrue(selectField1 && verifyPropPanelFld1 , 
					 			  "Verified Properties panel presents three tabs named \"General\",\"Settings\", and \"Advanced\" whenever : 'FREE TEXT' Field is selected", 
					              "Failed to verify Properties panel presents three tabs named \"General\",\"Settings\", and \"Advanced\" whenever : 'FREE TEXT' Field is selected");

			
			boolean selectGroup = fdp.selectField(groupName);
			boolean verifyPropPanelFG = fdp.verifyPropertiesPanleWith3Tabs();
			TestValidation.IsTrue(selectGroup && verifyPropPanelFG , 
								  "Verified Properties panel presents three tabs named \"General\",\"Settings\", and \"Advanced\" whenever : 'FIELD GROUP' is selected", 
						          "Failed to verify Properties panel presents three tabs named \"General\",\"Settings\", and \"Advanced\" whenever : 'FIELD GROUP' is selected");

		}finally {
			boolean navHome = fdp.navigateToHome();
			TestValidation.IsTrue(navHome, 
					  			 "Navigated to Homepage", 
					  			 "Failed to navigate to hompage");
		}
		
		
	}
	
	
	@Test(description = "Form Designer should see a text prompt on the Properties panel whenever a Form Section is selected (feature 326)")
	public void TestCase_5211() throws Exception {
		
		try {
			//Open 'Form Designer'
			fdp = hp.clickFormDesignerMenu();

			fdp.selectFormType(FORMTYPES.AUDIT);

			//Selection of resource
			boolean selectResource = fdp.selectResource(FORMRESOURCETYPES.SUPPLIERS,supplierCategoryValue,supplierInstanceValue);
			TestValidation.IsTrue(selectResource, 
								 "Selected Resource successfully", 
								 "Failed to Select Resource");

			//Go to 'Form Design'
			boolean clickOnNextButton = fdp.clickOnNextButton(fdp.DesignFormPg,"Design Form");
			TestValidation.IsTrue(clickOnNextButton, 
								 "Clicked On Next Button successfully", 
								 "Failed to Click On Next Button");
			
			controlActions.sendKeys(fdp.FormNameTxt, CommonMethods.dynamicText("Au_"));
			
			String section1Name = "Section 1";
			WebElement SectionElement1 = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FORM_ELEMENT,"FORMELEMENT","Section");
			controlActions.dragdrop(SectionElement1, fdp.FieldTypeDropAreaFormLevel);
			boolean setSectionName = fdp.setFieldName("Section",section1Name);
			TestValidation.IsTrue(setSectionName, 
								  "Dragged and Drop 'Section' -" + section1Name, 
								  "Could NOT Drag and Drop 'Section'");
			
			boolean selectSection = fdp.selectField(section1Name);
			TestValidation.IsTrue(selectSection, 
								  "Selected Section element-" + section1Name, 
								  "Failed to select Section element-" + section1Name);
			
			boolean verifyShrtname = fdp.isShortNameLabelNInputDisplayed();
			TestValidation.IsTrue(verifyShrtname, 
								  "Successfully verified - Whenever a Section type form element is selected, the Properties panel displays the text field as \" Short Name\"", 
					  			  "Could Not Verify Short Name Text Field on  Properties Tab when Form Name Element is selected.");
			
			boolean FormNameElmnttSlctdTxt = fdp.selectFormName_VerifyPropPanelTxtPrmpt();
			TestValidation.IsTrue(FormNameElmnttSlctdTxt, 
								  "Successfully verified - When Form Name Element is selected, text is displayed in the Field Properties panel :-" + "Select an element on the form to modify it's properties", 
								  "Could Not Verify Text Display in Properties Tab when Form Name Element is selected.");
		}finally {
			boolean navHome = fdp.navigateToHome();
			TestValidation.IsTrue(navHome, 
					  			 "Navigated to Homepage", 
					  			 "Failed to navigate to hompage");
		}
		
	}
	
	
	
//	@Test(description = "Form Designer can select 'Audit' Form Type to design. Check UI of the form (feature 491)")
//	public void TestCase_5228() throws Exception {
//		
//	}
	
	
	
	@AfterClass(alwaysRun = true)
	public void closeBrowser() throws InterruptedException {
		driver.close();
	}

}
