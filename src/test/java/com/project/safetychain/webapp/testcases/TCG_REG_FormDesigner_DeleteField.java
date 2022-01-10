package com.project.safetychain.webapp.testcases;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.project.safetychain.webapp.pageobjects.CommonPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage;
import com.project.safetychain.webapp.pageobjects.FormDesignerPageLocator;
import com.project.safetychain.webapp.pageobjects.HomePage;
import com.project.safetychain.webapp.pageobjects.LoginPage;
import com.project.safetychain.webapp.pageobjects.ManageLocationPage;
import com.project.safetychain.webapp.pageobjects.ManageLocationPage.LocationInstanceParams;
import com.project.safetychain.webapp.pageobjects.ManageResourcePage;
import com.project.safetychain.webapp.pageobjects.ManageResourcePage.RESOURCETYPES;
import com.project.safetychain.webapp.pageobjects.ManageResourcePage.ResourceDetailParams;
import com.project.safetychain.webapp.pageobjects.ResourceDesignerPage;
import com.project.safetychain.webapp.pageobjects.ResourceDesignerPage.CATEGORYTYPES;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.ELEMENT_TYPE;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FIELD_TYPES;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.utilities.CommonMethods;
import com.project.utilities.ControlActions;

public class TCG_REG_FormDesigner_DeleteField extends TestBase{
	ControlActions controlActions;
	CommonPage mainPage;
	LoginPage lgnPge;
	ResourceDesignerPage resourceDesigner;
	ManageResourcePage manageResource;
	ManageLocationPage locations;
	FormDesignerPage fdp;
	HomePage hp;

	//Data
	public static String checkFormName;
	public static String checkFormName1;
	public static String checkFormName2;
	public static String locationCategoryValue;
	public static String resourceType = "Customers";
	public static String resourceCategoryValue;
	public static String resourceInstanceValue1,resourceInstanceValue2;
	public static String locationInstanceValue;

	@BeforeClass(alwaysRun = true)
	public void groupInit() throws Exception {

		checkFormName = CommonMethods.dynamicText("Automation_CheckForm");
		checkFormName1 = CommonMethods.dynamicText("Automation_CheckForm1");
		checkFormName2 = CommonMethods.dynamicText("Automation_CheckForm2");
		
		locationCategoryValue = CommonMethods.dynamicText("Location_Cat");
		resourceCategoryValue = CommonMethods.dynamicText("Resource_Cat");
		resourceInstanceValue1 = CommonMethods.dynamicText("Resource_Inst1");
		resourceInstanceValue2 = CommonMethods.dynamicText("Resource_Inst2");
		locationInstanceValue = CommonMethods.dynamicText("Location_Inst");

		driver = launchbrowser();
		controlActions = new ControlActions(driver);
		controlActions.getUrl(applicationUrl);
		mainPage = new CommonPage(driver);
		lgnPge = new LoginPage(driver);
		resourceDesigner = new ResourceDesignerPage(driver);
		manageResource = new ManageResourcePage(driver);
		locations = new ManageLocationPage(driver);
		fdp = new FormDesignerPage(driver);

		//login
		HomePage hp = lgnPge.performLogin(adminUsername, adminPassword);
		if(hp.error) {
			TCGFailureMsg = "Could NOT login to application";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
			
		//Category creation
		HashMap<String,String> categories = new HashMap<String, String>();
		categories.put(CATEGORYTYPES.CUSTOMERS, resourceCategoryValue);
		categories.put(CATEGORYTYPES.LOCATION, locationCategoryValue);
		ResourceDesignerPage rdp = hp.clickResourceDesignerMenu();
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
		instances.put(resourceInstanceValue1, true);
		instances.put(resourceInstanceValue2, false);
		ResourceDetailParams rd = new ResourceDetailParams();
		rd.CategoryName = resourceCategoryValue;
		rd.CategoryType = RESOURCETYPES.CUSTOMERS;
		rd.NumberFieldValue = 30;
		rd.TextFieldValue = "ABC";
		rd.InstanceNames = instances;
		rd.LocationInstanceValue = locationInstanceValue;
		ManageResourcePage mrp = hp.clickResourcesMenu();
		if(!mrp.createResourceLinkLocation(rd)) {
		TCGFailureMsg = "Could NOT create Instances for resource - " + resourceCategoryValue;
		logFatal(TCGFailureMsg);
		throw new SkipException(TCGFailureMsg);
		}	
	}

	@Test(description = "Dynamic Forms - Form Designer - User should not be able to delete a field if it is used in some form Rule")
	public void TestCase_5126() {
		
		//Open 'Form Designer'
		fdp = mainPage.clickFormDesignerMenu();

		controlActions.clickElement(fdp.SelectCheckFormLnk);

		//Selection of resource		
		boolean selectResource = fdp.selectResource(resourceType,resourceCategoryValue,resourceInstanceValue1);
		TestValidation.IsTrue(selectResource, 
				"Selected Resource successfully", 
				"Failed to Select Resource");

		//Go to 'Form Design'
		boolean clickOnNextButton = fdp.clickOnNextButton(fdp.DesignFormPg,"Design Form");
		TestValidation.IsTrue(clickOnNextButton, 
				"Clicked On Next Button successfully", 
				"Failed to Click On Next Button");


		controlActions.sendKeys(fdp.FormNameTxt, checkFormName);
		
		String numericFieldName = "Numeric1";
		WebElement NumericField = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_TYPE,"FIELDTYPE",FIELD_TYPES.NUMERIC);
		controlActions.dragdrop(NumericField, fdp.FieldTypeDropAreaFormLevel);
		boolean setNumericFieldValue1 = fdp.setTextBoxValue(FIELD_TYPES.NUMERIC, numericFieldName);		
		TestValidation.IsTrue(setNumericFieldValue1, 
				"'Numeric' field added successfully", 
				"Failed to Add Numeric field");		
		
		String numericFieldName1 = "Numeric2";
		WebElement NumericField1 = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_TYPE,"FIELDTYPE",FIELD_TYPES.NUMERIC);
		controlActions.dragdrop(NumericField1, fdp.FieldTypeDropAreaFormLevel);
		boolean setNumericFieldValue2 = fdp.setTextBoxValue(FIELD_TYPES.NUMERIC, numericFieldName1);		
		TestValidation.IsTrue(setNumericFieldValue2, 
				"'Numeric' field added successfully", 
				"Failed to Add Numeric field");	
				
		boolean DependencyRule = fdp.AddDependencyRule(numericFieldName, "5");
		TestValidation.IsTrue(DependencyRule, 
				"Field Type Added", 
				"Could NOT Add Dependency Rule");
		
		boolean deleteField = fdp.deleteField(numericFieldName);
		TestValidation.IsTrue(deleteField, 
				"Clicked on delete field icon for field " + numericFieldName, 
				"Could NOT click on delete field icon for field" + numericFieldName);
		
		boolean popUpDisplayed = fdp.fieldCannotBeDeletedPopupDisplayed();		
		TestValidation.IsTrue(popUpDisplayed, 
				"Field Cannot Be Deleted Popup is Displayed", 
				"Field Cannot Be Deleted Popup is NOT Displayed" + numericFieldName);
		
		boolean deleteRule = fdp.deleteRule(numericFieldName1, numericFieldName1+" DependencyRule");
		TestValidation.IsTrue(deleteRule, 
				"Deleted rule " + numericFieldName1+" DependencyRule" + " Successfully", 
				"Could NOT Deleted rule " + numericFieldName1 +" DependencyRule");
		
		boolean deleteField1 = fdp.deleteField(numericFieldName);
		TestValidation.IsTrue(deleteField1, 
				"Deleted field successfully - " + numericFieldName, 
				"Could NOT Delete field " + numericFieldName);

		boolean selectField = fdp.selectField(numericFieldName1);
		TestValidation.IsTrue(selectField, 
				"Selected field successfully - " + numericFieldName1, 
				"Could NOT Select field " + numericFieldName1);
		
		boolean DependencyRule1 = fdp.AddDependencyRule(numericFieldName, "5");
		TestValidation.IsTrue(DependencyRule1, 
				"Adding Dependency Rule ", 
				"Unable to configure Dependency Rule");
		
		boolean invalidRuleConfirgurationErrorDisplayed = fdp.invalidRuleConfirgurationErrorDisplayed();
		TestValidation.IsTrue(invalidRuleConfirgurationErrorDisplayed, 
				"Failed to add Dependency rule ", 
				"Added Dependency Rule");
		
		boolean saveForm = fdp.clickSaveButton();
		TestValidation.IsTrue(saveForm, 
				"Saved form Successfully", 
				"Could NOT Save Form");
		
		boolean nextToReleaseForm = fdp.clickOnNextButton(fdp.ReleaseFormPg,"Release Form");
		TestValidation.IsTrue(nextToReleaseForm, 
				"NAVIGATED to 'Release Form'", 
				"Could NOT Navigate to 'Release Form'");

		boolean releaseForm = fdp.releaseForm(checkFormName);
		TestValidation.IsTrue(releaseForm, 
				"RELEASED form - "+checkFormName, 
				"Could NOT release form'");

		FSQABrowserPage fbp = mainPage.clickFSQABrowserMenu();

		boolean selectResourceDropDownandNavigate =  fbp.selectResourceDropDownandNavigate(resourceType,"Forms");	
		TestValidation.IsTrue(selectResourceDropDownandNavigate, 
				"NAVIGATED to FSQA -> Forms Tab", 
				"Could NOT Navigate to FSQA -> Forms Tab");

		boolean applyFilterAndOpenForDetails = fbp.applyFilterAndOpenForDetails("Form Name", checkFormName);
		TestValidation.IsTrue(applyFilterAndOpenForDetails, 
				"Verify form is displayed in Forms Tab", 
				"Form is NOT displayed in Forms Tab");
		
	}
	
	@Test(description = "Validate user is able to delete Aggregate field which has count range function prior to saving form")
	public void TestCase_38698() {
		
		//Open 'Form Designer'
		fdp = mainPage.clickFormDesignerMenu();

		controlActions.clickElement(fdp.SelectCheckFormLnk);

		//Selection of resource
		boolean selectResource = fdp.selectResource(resourceType,resourceCategoryValue,resourceInstanceValue1);
		TestValidation.IsTrue(selectResource, 
				"Selected Resource successfully", 
				"Failed to Select Resource");

		//Go to 'Form Design'
		boolean clickOnNextButton = fdp.clickOnNextButton(fdp.DesignFormPg,"Design Form");
		TestValidation.IsTrue(clickOnNextButton, 
				"Clicked On Next Button successfully", 
				"Failed to Click On Next Button");


		controlActions.sendKeys(fdp.FormNameTxt, checkFormName1);
	
		String numericFieldName = "Numeric1";
		WebElement NumericField = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_TYPE,"FIELDTYPE",FIELD_TYPES.NUMERIC);
		controlActions.dragdrop(NumericField, fdp.FieldTypeDropAreaFormLevel);
		boolean setNumericFieldValue1 = fdp.setTextBoxValue(FIELD_TYPES.NUMERIC, numericFieldName);		
		TestValidation.IsTrue(setNumericFieldValue1, 
				"'Numeric' field added successfully", 
				"Failed to Add Numeric field");		
		
		fdp.Sync();

		String numericFieldName1 = "Numeric2";
		WebElement NumericField1 = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_TYPE,"FIELDTYPE",FIELD_TYPES.NUMERIC);
		controlActions.dragdrop(NumericField1, fdp.FieldTypeDropAreaFormLevel);
		boolean setNumericFieldValue2 = fdp.setTextBoxValue(FIELD_TYPES.NUMERIC, numericFieldName1);		
		TestValidation.IsTrue(setNumericFieldValue2, 
				"'Numeric' field added successfully", 
				"Failed to Add Numeric field");	
		
		fdp.Sync();

		String numericFieldName2 = "Numeric3";
		WebElement NumericField2 = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_TYPE,"FIELDTYPE",FIELD_TYPES.NUMERIC);
		controlActions.dragdrop(NumericField2, fdp.FieldTypeDropAreaFormLevel);
		boolean setNumericFieldValue3 = fdp.setTextBoxValue(FIELD_TYPES.NUMERIC, numericFieldName2);		
		TestValidation.IsTrue(setNumericFieldValue3, 
				"'Numeric' field added successfully", 
				"Failed to Add Numeric field");	
		
		fdp.Sync();

		String aggregateFieldName = "AggregateCR";
		WebElement AggregateField = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FORM_ELEMENT,"FORMELEMENT","Aggregate");
		controlActions.dragdrop(AggregateField, fdp.FieldTypeDropAreaFormLevel);
		boolean setAggregateFieldValue1 = fdp.setTextBoxValue("Aggregate", aggregateFieldName);		
		TestValidation.IsTrue(setAggregateFieldValue1, 
				"'Aggregate' field added successfully", 
				"Failed to Add Aggregate field");	
		
		List<String> aggregateSourceList = new ArrayList<String>(); ;
		aggregateSourceList.add(numericFieldName);
		aggregateSourceList.add(numericFieldName1);
		aggregateSourceList.add(numericFieldName2);
		boolean configureAggregateFunction = fdp.configureAggregateFunction("Count Range", aggregateSourceList, "1", "5");
		TestValidation.IsTrue(configureAggregateFunction, 
				"Configured Aggregate field for 'Count Range Fucntion Successfully'", 
				"Failed to Configure Aggregate field");	
		
		boolean deleteField = fdp.deleteField(aggregateFieldName);
		TestValidation.IsTrue(deleteField, 
				"Deleted Aggregate Field Successfully'", 
				"Failed to Delete Aggregate field");	
		
		boolean saveForm = fdp.clickSaveButton();
		TestValidation.IsTrue(saveForm, 
				"Saved form Successfully", 
				"Could NOT Save Form");
				
	}

	@Test(description = "Validate user is able to delete Aggregate field which has count range function after saving form")
	public void TestCase_38699() {
		
		//Open 'Form Designer'
		fdp = mainPage.clickFormDesignerMenu();

		controlActions.clickElement(fdp.SelectCheckFormLnk);

		//Selection of resource
		boolean selectResource = fdp.selectResource(resourceType,resourceCategoryValue,resourceInstanceValue1);
		TestValidation.IsTrue(selectResource, 
				"Selected Resource successfully", 
				"Failed to Select Resource");

		//Go to 'Form Design'
		boolean clickOnNextButton = fdp.clickOnNextButton(fdp.DesignFormPg,"Design Form");
		TestValidation.IsTrue(clickOnNextButton, 
				"Clicked On Next Button successfully", 
				"Failed to Click On Next Button");


		controlActions.sendKeys(fdp.FormNameTxt, checkFormName2);
	
		String numericFieldName = "Numeric1";
		WebElement NumericField = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_TYPE,"FIELDTYPE",FIELD_TYPES.NUMERIC);
		controlActions.dragdrop(NumericField, fdp.FieldTypeDropAreaFormLevel);
		boolean setNumericFieldValue1 = fdp.setTextBoxValue(FIELD_TYPES.NUMERIC, numericFieldName);		
		TestValidation.IsTrue(setNumericFieldValue1, 
				"'Numeric' field added successfully", 
				"Failed to Add Numeric field");		
		
		fdp.Sync();
		
		String numericFieldName1 = "Numeric2";
		WebElement NumericField1 = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_TYPE,"FIELDTYPE",FIELD_TYPES.NUMERIC);
		controlActions.dragdrop(NumericField1, fdp.FieldTypeDropAreaFormLevel);
		boolean setNumericFieldValue2 = fdp.setTextBoxValue(FIELD_TYPES.NUMERIC, numericFieldName1);		
		TestValidation.IsTrue(setNumericFieldValue2, 
				"'Numeric' field added successfully", 
				"Failed to Add Numeric field");	
		
		fdp.Sync();
		
		String numericFieldName2 = "Numeric3";
		WebElement NumericField2 = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_TYPE,"FIELDTYPE",FIELD_TYPES.NUMERIC);
		controlActions.dragdrop(NumericField2, fdp.FieldTypeDropAreaFormLevel);
		boolean setNumericFieldValue3 = fdp.setTextBoxValue(FIELD_TYPES.NUMERIC, numericFieldName2);		
		TestValidation.IsTrue(setNumericFieldValue3, 
				"'Numeric' field added successfully", 
				"Failed to Add Numeric field");	
		
		fdp.Sync();
		
		String aggregateFieldName = "AggregateCR";
		WebElement AggregateField = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FORM_ELEMENT,"FORMELEMENT","Aggregate");
		controlActions.dragdrop(AggregateField, fdp.FieldTypeDropAreaFormLevel);
		boolean setAggregateFieldValue1 = fdp.setTextBoxValue("Aggregate", aggregateFieldName);		
		TestValidation.IsTrue(setAggregateFieldValue1, 
				"'Aggregate' field added successfully", 
				"Failed to Add Aggregate field");	
		
		fdp.Sync();
		
		List<String> aggregateSourceList = new ArrayList<String>(); ;
		aggregateSourceList.add(numericFieldName);
		aggregateSourceList.add(numericFieldName1);
		aggregateSourceList.add(numericFieldName2);
		boolean configureAggregateFunction = fdp.configureAggregateFunction("Count Range", aggregateSourceList, "1", "5");
		TestValidation.IsTrue(configureAggregateFunction, 
				"Configured Aggregate field for 'Count Range Fucntion Successfully'", 
				"Failed to Configure Aggregate field");			
		
		boolean saveForm = fdp.clickSaveButton();
		TestValidation.IsTrue(saveForm, 
				"Saved form Successfully", 
				"Could NOT Save Form");
		
		boolean deleteField = fdp.deleteField(aggregateFieldName);
		TestValidation.IsTrue(deleteField, 
				"Deleted Aggregate Field Successfully'", 
				"Failed to Delete Aggregate field");
		
		boolean saveForm1 = fdp.clickSaveButton();
		TestValidation.IsTrue(saveForm1, 
				"Saved form Successfully", 
				"Could NOT Save Form");		
	}
	
	@AfterClass(alwaysRun = true)
	public void closeBrowser() throws InterruptedException {
		driver.close();
	}
}
