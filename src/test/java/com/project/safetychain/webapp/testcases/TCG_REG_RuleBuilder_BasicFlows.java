package com.project.safetychain.webapp.testcases;

import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.testbase.SupportingClasses.ExecutionMode;
import com.project.utilities.CommonMethods;
import com.project.utilities.ControlActions;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.project.safetychain.api.models.support.TCG_FormCreation_Wrapper;
import com.project.safetychain.api.models.support.Support.RESOURCE_TYPES;
import com.project.safetychain.webapp.pageobjects.*;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FIELD_TYPES;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FieldType;
import com.project.safetychain.webapp.pageobjects.ManageLocationPage.LocationInstanceParams;
import com.project.safetychain.webapp.pageobjects.ManageResourcePage.RESOURCETYPES;
import com.project.safetychain.webapp.pageobjects.ManageResourcePage.ResourceDetailParams;
import com.project.safetychain.webapp.pageobjects.ResourceDesignerPage.CATEGORYTYPES;

public class TCG_REG_RuleBuilder_BasicFlows extends TestBase {

	ControlActions controlActions;
	CommonPage mainPage;
	LoginPage lgnPge;
	ResourceDesignerPage resourceDesigner;
	ManageResourcePage manageResource;
	ManageLocationPage locations;
	FormDesignerPage fdp;
	HomePage hp;

	// Data
	public static String checkFormName;
	public static String locationCategoryValue;
	public static String resourceType = "Customers";
	public static String resourceCategoryValue;
	public static String resourceInstanceValue1, resourceInstanceValue2;
	public static String locationInstanceValue;

	@BeforeClass(alwaysRun = true)
	public void groupInit() throws Exception {

		checkFormName = CommonMethods.dynamicText("Automation_CheckForm");
		locationCategoryValue = locationName;
		locationInstanceValue = locationName;

		resourceCategoryValue = CommonMethods.dynamicText("Resource_Cat");
		resourceInstanceValue1 = CommonMethods.dynamicText("Resource_Inst1");
		resourceInstanceValue2 = CommonMethods.dynamicText("Resource_Inst2");

		if (executionMode.equalsIgnoreCase(ExecutionMode.API)) {
			// ------------------------------------------------------------------------------------------------
			// API Implementation
			TCG_FormCreation_Wrapper formCreationWrapper = new TCG_FormCreation_Wrapper();

			// ------------------------------------------------------------------------------------------------
			// API - Location & Resource Creation and Linking
			List<String> userList = Arrays.asList(adminUsername);

			HashMap<String, List<String>> LocationMap = new HashMap<String, List<String>>();
			LocationMap.put(locationCategoryValue, Arrays.asList(locationInstanceValue));

			HashMap<String, List<String>> resourceCatMap = new HashMap<String, List<String>>();
			resourceCatMap.put(resourceCategoryValue, Arrays.asList(resourceInstanceValue1, resourceInstanceValue2));

			HashMap<String, String> resourceStatus = new HashMap<String, String>();
			resourceStatus.put(resourceInstanceValue2, "false");

			formCreationWrapper.Resource_Location_CreationWrapper(resourceCatMap, LocationMap, true, userList,
					RESOURCE_TYPES.CUSTOMERS, resourceStatus);
			// ------------------------------------------------------------------------------------------------
			// WEB Application code starts here

			driver = launchbrowser();
			controlActions = new ControlActions(driver);
			controlActions.getUrl(applicationUrl);
			mainPage = new CommonPage(driver);
			lgnPge = new LoginPage(driver);
			resourceDesigner = new ResourceDesignerPage(driver);
			manageResource = new ManageResourcePage(driver);
			locations = new ManageLocationPage(driver);
			fdp = new FormDesignerPage(driver);

			// login
			hp = lgnPge.performLogin(adminUsername, adminPassword);
			if (hp.error) {
				TCGFailureMsg = "Could NOT login to application";
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}
		} else if (executionMode.equalsIgnoreCase(ExecutionMode.UI)) {
			// ------------------------------------------------------------------------------------------------
			// Only WEB Application code starts here

			driver = launchbrowser();
			controlActions = new ControlActions(driver);
			controlActions.getUrl(applicationUrl);
			mainPage = new CommonPage(driver);
			lgnPge = new LoginPage(driver);
			resourceDesigner = new ResourceDesignerPage(driver);
			manageResource = new ManageResourcePage(driver);
			locations = new ManageLocationPage(driver);
			fdp = new FormDesignerPage(driver);

			// login
			HomePage hp = lgnPge.performLogin(adminUsername, adminPassword);
			if (hp.error) {
				TCGFailureMsg = "Could NOT login to application";
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}

			// Category creation
			HashMap<String, String> categories = new HashMap<String, String>();
			categories.put(CATEGORYTYPES.CUSTOMERS, resourceCategoryValue);
			categories.put(CATEGORYTYPES.LOCATION, locationCategoryValue);
			ResourceDesignerPage rdp = hp.clickResourceDesignerMenu();
			if (!rdp.createCategory(categories)) {
				TCGFailureMsg = "Could NOT create Resource categories";
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}

			// Location instance
			HashMap<String, Boolean> locInstance = new HashMap<String, Boolean>();
			locInstance.put(locationInstanceValue, true);
			LocationInstanceParams lip = new LocationInstanceParams();
			lip.CategoryName = locationCategoryValue;
			lip.NumberFieldValue = 10;
			lip.TextFieldValue = "XYZ";
			lip.InstanceName = locInstance;
			ManageLocationPage mlp = hp.clickLocationsMenu();
			if (!mlp.createLocation(lip)) {
				TCGFailureMsg = "Could NOT create Location instance";
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}

			// Resource creation
			HashMap<String, Boolean> instances = new HashMap<String, Boolean>();
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
			if (!mrp.createResourceLinkLocation(rd)) {
				TCGFailureMsg = "Could NOT create Instances for resource - " + resourceCategoryValue;
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}
		} else {
			TCGFailureMsg = "Improper Execution Mode is set - " + executionMode;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
	}

	@Test(description = "Rule Builder || User can launch Rule Builder to create Dependency Rules")
	public void TestCase_4457() {
		try {
			FormDesignerPage fdp = new FormDesignerPage(driver);
			// Step-1 Open 'Form Designer'
			fdp = mainPage.clickFormDesignerMenu();
			controlActions.clickElement(fdp.SelectCheckFormLnk);

			// Step-1 Selection of resource
			boolean selectResource = fdp.selectResource(resourceType, resourceCategoryValue, resourceInstanceValue1);
			TestValidation.IsTrue(selectResource, "Selected Resource "+resourceInstanceValue1+" successfully",
					"Failed to Select Resource "+resourceInstanceValue1);

			// Step-1 Click on Next > Go to 'Form Design'
			TestValidation.IsTrue(fdp.clickOnNextButton(fdp.DesignFormPg, "Design Form"),
					"Clicked On Next Button successfully", "Failed to Click On Next Button");
			controlActions.sendKeys(fdp.FormNameTxt, checkFormName);

			String freeTextFieldName = "FreeText1";
			WebElement FreeTextFieldType = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_TYPE,
					"FIELDTYPE", FIELD_TYPES.FREE_TEXT);
			controlActions.dragdrop(FreeTextFieldType, fdp.FieldTypeDropAreaFormLevel);
			TestValidation.IsTrue(fdp.setTextBoxValue(FIELD_TYPES.FREE_TEXT, freeTextFieldName),
					"'Free Text field' added successfully", "Failed to Add Free Text Field");

			// Step-2 Verify when there is no dependency rule already exist.
			TestValidation.IsFalse(fdp.checkDependencyRulePresence(freeTextFieldName),
					"'Dependency rule is not present verified successfully'",
					"Failed to check no dependency rule is applied.");
			TestValidation.IsTrue(fdp.checkAddNewButtonEnabledForDependencyRule(),
					"'Add new button is enabled verified successfully'",
					"Failed to check enabled status of Add new button.");

			String freeTextFieldName1 = "FreeText2";
			controlActions.dragdrop(FreeTextFieldType, fdp.FieldTypeDropAreaFormLevel);
			TestValidation.IsTrue(fdp.setTextBoxValue(FIELD_TYPES.FREE_TEXT, freeTextFieldName1),
					"'Free Text field' added successfully", "Failed to Add Free Text Field");
			
			// Add Dependency Rule
			TestValidation.IsTrue(fdp.addDependencyRuleUsingValue(freeTextFieldName, "Test"),
					"'Dependency rule is present verified successfully'",
					"Failed to check dependency rule's presence.");

			// Step-3 Verify when there is dependency rule already exist.
			TestValidation.IsTrue(fdp.checkDependencyRulePresence(freeTextFieldName1),
					"'Dependency rule is present verified successfully'",
					"Failed to check dependency rule's presence.");
			TestValidation.IsTrue(fdp.checkAddNewButtonDisabledDependencyRule(),
					"'Add new button is disabled verified successfully'",
					"Failed to check disabled status of Add new button.");

			// Step-4 Click on the hyperlink of the rule.
			TestValidation.IsTrue(fdp.openExistingDependencyRule(freeTextFieldName1),
					"'Existing Dependency rule is opened verified successfully'",
					"Failed to open existing dependency rule.'");

			// Step-5 click on the "Save & Close" button on the rule builder
			TestValidation.IsTrue(fdp.clickSaveCloseButton(), "'Clicked on Save & Close button verified successfully.'",
					"Failed to peform click action on Save & close button.");

			// Verify when there is dependency rule already exist after save & close action.
			TestValidation.IsTrue(fdp.checkDependencyRulePresence(freeTextFieldName1),
					"'Dependency rule is present verified successfully'",
					"Failed to check dependency rule's presence.");

			// Click on the hyperlink of the rule.
			TestValidation.IsTrue(fdp.openExistingDependencyRule(freeTextFieldName1),
					"'Existing Dependency rule is opened verified successfully'",
					"Failed to open existing dependency rule.'");

			String updatedDependencyRuleName = fdp.updateRuleName("TestRule");
			// Step-6 Click on cancel button
			TestValidation.IsTrue(fdp.clickCancelButton(), "'Clicked on cancel button Verified successfully'",
					"Failed to click on cancel button");
			TestValidation.IsFalse(updatedDependencyRuleName.equals(freeTextFieldName),
					"'Dependecy rule name is not updated verified successfully.'",
					"Failed to check Dependency rule name is updated.");
		} finally {
			if (hp.clickHomepageLinkAndBypassPopup().error) {
				TCGFailureMsg = "Could NOT land on FSQA Browser page after clicking on Home link";
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}
		}
	}

	@Test(description = "Rule Builder ||  User can launch Rule Builder to create Expressions")
	public void TestCase_4460() {
		try {
			FormDesignerPage fdp = new FormDesignerPage(driver);

			// Step-1 Open 'Form Designer'
			fdp = mainPage.clickFormDesignerMenu();
			controlActions.clickElement(fdp.SelectCheckFormLnk);

			// Step-1 Selection of resource
			boolean selectResource = fdp.selectResource(resourceType, resourceCategoryValue, resourceInstanceValue1);
			TestValidation.IsTrue(selectResource, "Selected Resource successfully", "Failed to Select Resource");

			// Step-1 Click on Next > Go to 'Form Design'
			boolean clickOnNextButton = fdp.clickOnNextButton(fdp.DesignFormPg, "Design Form");
			TestValidation.IsTrue(clickOnNextButton, "Clicked On Next Button successfully",
					"Failed to Click On Next Button");
			controlActions.sendKeys(fdp.FormNameTxt, checkFormName);

			String numericFieldName = "Numeric1";
			WebElement NumericField = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_TYPE,
					"FIELDTYPE", FIELD_TYPES.NUMERIC);
			controlActions.dragdrop(NumericField, fdp.FieldTypeDropAreaFormLevel);
			boolean setNumericFieldValue1 = fdp.setTextBoxValue(FIELD_TYPES.NUMERIC, numericFieldName);
			TestValidation.IsTrue(setNumericFieldValue1, "'Numeric' field added successfully",
					"Failed to Add Numeric field");

			String numericFieldName1 = "Numeric2";
			controlActions.dragdrop(NumericField, fdp.FieldTypeDropAreaFormLevel);
			boolean setNumericFieldName = fdp.setFieldName(FIELD_TYPES.NUMERIC, numericFieldName1);
			TestValidation.IsTrue(setNumericFieldName, "Setted 'Numeric' field name",
					"Could NOT set 'Numeric' field name");

			// Step-2 Verify when there is no Expression rule already exist.
			TestValidation.IsFalse(fdp.checkExpressionRulePresence(numericFieldName1),
					"'Expression rule is present verified successfully'",
					"Failed to check Expression rule's presence.");
			TestValidation.IsTrue(fdp.checkAddNewButtonEnabledForExpressionRule(),
					"'Add new button is disabled verified successfully'",
					"Failed to check disability of Add new button.");

			// Step-3 CLick on Add new button for Expression rule
			boolean setExprRule = fdp.ifExpressionRuleUsingValue("Numeric1", "5", "10", "15");
			TestValidation.IsTrue(setExprRule, "Successfully Added IF expression rule on field - Numeric2",
					"Failed to add IF expression rule on field - Numeric2");

			// Step-4 Verify when there is dependency rule already exist.
			TestValidation.IsTrue(fdp.checkExpressionRulePresence(numericFieldName1),
					"'Expression rule is present verified successfully'",
					"Failed to check Expression rule's presence.");
			TestValidation.IsTrue(fdp.checkAddNewButtonDisabledExpressionRule(),
					"'Add new button is disabled verified successfully'",
					"Failed to check disability of Add new button.");

			// Step-5 Click on the hyperlink of the rule.
			boolean existingExpressionRuleOpenOrNot = fdp.openExistingExpressionRule(numericFieldName1);
			TestValidation.IsTrue(existingExpressionRuleOpenOrNot,
					"'Existing Expression rule is opened verified successfully'",
					"Failed to open existing Expression rule.'");

			String updatedExpressionRuleName = fdp.updateRuleName("TestRule");
			// Step-6 Click on cancel button
			TestValidation.IsTrue(fdp.clickCancelButton(), "'Clicked on cancel button Verified successfully'",
					"Failed to click on cancel button");
			TestValidation.IsFalse(updatedExpressionRuleName.equals(numericFieldName1),
					"'Expression rule name is not updated verified successfully.'",
					"Failed to check Expression rule name is updated.");

			// Click on the hyperlink of the rule.
			TestValidation.IsTrue(fdp.openExistingExpressionRule(numericFieldName1),
					"'Existing Expression rule is opened verified successfully'",
					"Failed to open existing Expression rule.'");
			String updatedExpressionRuleName1 = fdp.updateRuleName("TestRule");
			// Step-7 click on the "Save & Close" button on the rule builder
			TestValidation.IsTrue(fdp.clickSaveCloseButton(), "'Clicked on Save & Close button verified successfully.'",
					"Failed to peform click action on Save & close button.");

			// Verify when there is dependency rule already exist after save & close action.
			TestValidation.IsTrue(fdp.checkExpressionRulePresence(updatedExpressionRuleName1),
					"'Expression rule is present verified successfully'",
					"Failed to check Expression rule's presence.");
		} finally {
			if (hp.clickHomepageLinkAndBypassPopup().error) {
				TCGFailureMsg = "Could NOT land on FSQA Browser page after clicking on Home link";
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}
		}
	}

	@Test(description = "Rule Builder || User can launch Rule Builder to create Compliance Rules")
	public void TestCase_4459() {
		try {
			FormDesignerPage fdp = new FormDesignerPage(driver);
			// Open 'Form Designer'
			fdp = mainPage.clickFormDesignerMenu();
			controlActions.clickElement(fdp.SelectCheckFormLnk);
			// Selection of resource
			boolean selectResource = fdp.selectResource(resourceType, resourceCategoryValue, resourceInstanceValue1);
			TestValidation.IsTrue(selectResource, "Selected Resource successfully", "Failed to Select Resource");
			// Click on Next > Go to 'Form Design'
			boolean clickOnNextButton = fdp.clickOnNextButton(fdp.DesignFormPg, "Design Form");
			TestValidation.IsTrue(clickOnNextButton, "Clicked On Next Button successfully",
					"Failed to Click On Next Button");
			controlActions.sendKeys(fdp.FormNameTxt, checkFormName);

			String dateFieldName = "Date1";
			WebElement DateField = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_TYPE,
					"FIELDTYPE", FIELD_TYPES.DATE);
			controlActions.dragdrop(DateField, fdp.FieldTypeDropAreaFormLevel);
			boolean setDateFieldValue1 = fdp.setTextBoxValue(FIELD_TYPES.DATE, dateFieldName);
			TestValidation.IsTrue(setDateFieldValue1, "'Date' field added successfully", " Failed to Add Date field");

			boolean setCompliance = fdp.setComplianceForDateandTimeFieldv2(dateFieldName, resourceInstanceValue1,
					"12/23/2021");
			TestValidation.IsTrue(setCompliance, "Set Compliance For Date Field Successfully",
					"Could NOT Set Compliance For Date Field");

			String timeFieldName = "Time1";
			WebElement TimeField = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_TYPE,
					"FIELDTYPE", FIELD_TYPES.TIME);
			controlActions.dragdrop(TimeField, fdp.FieldTypeDropAreaFormLevel);
			boolean setTimeFieldValue1 = fdp.setTextBoxValue(FIELD_TYPES.TIME, timeFieldName);
			TestValidation.IsTrue(setTimeFieldValue1, "'Time' field added successfully", " Failed to Add Time field");

			boolean setCompliance1 = fdp.setComplianceForDateandTimeFieldv2(timeFieldName, resourceInstanceValue1,
					"01:30");
			TestValidation.IsTrue(setCompliance1, "Set Compliance For Time Field Successfully",
					"Could NOT Set Compliance For Time Field");

			String dateTimeFieldName = "DateTime1";
			WebElement DateTimeField = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_TYPE,
					"FIELDTYPE", FIELD_TYPES.DATE_TIME);
			controlActions.dragdrop(DateTimeField, fdp.FieldTypeDropAreaFormLevel);
			boolean setDateTimeFieldValue1 = fdp.setTextBoxValue(FIELD_TYPES.DATE_TIME, dateTimeFieldName);
			TestValidation.IsTrue(setDateTimeFieldValue1, "'DateTime' field added successfully",
					" Failed to Add DateTime field");

			boolean setCompliance2 = fdp.setComplianceForDateandTimeFieldv2(dateTimeFieldName, resourceInstanceValue1,
					"12/23/2021 00:00");
			TestValidation.IsTrue(setCompliance2, "Set Compliance For DateTime Field Successfully",
					"Could NOT Set Compliance For DateTime Field");

			boolean saveForm = fdp.clickSaveButton();
			TestValidation.IsTrue(saveForm, "Saved form Successfully", "Could NOT Save Form");

			// Verify edit icon next to the rule name text box
			boolean value = fdp.editIconPresenceOnComplianceRuleTextField(dateFieldName, resourceInstanceValue1,
					"12/23/2021");
			TestValidation.IsTrue(value, "Edit icon is present, verified successfully.", "Failed to check Edit icon is not present");

			// Verify user can click on the "Edit Rule" icon to launch
			// the rule builder in a pop-up window
			fdp.clickOnEditIconOfComplianceRuleTextField(resourceInstanceValue1);

			String updatedDependencyRuleName = fdp.updateRuleName("TestRule");
			// Click on the "Save & Close" button on the rule builder
			TestValidation.IsTrue(fdp.clickSaveCloseButton(), "'Clicked on Save & Close button verified successfully.'",
					"Failed to peform click action on Save & close button.");
			TestValidation.IsFalse((dateFieldName + " Compliance").equals(updatedDependencyRuleName),
					"'Compliance rule name is updated verified successfully.'",
					"'Failed to check Compliance rule name is not updated.'");
		} finally {
			if (hp.clickHomepageLinkAndBypassPopup().error) {
				TCGFailureMsg = "Could NOT land on FSQA Browser page after clicking on Home link";
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}
		}
	}
	
	@Test(description = "Rule Builder || Verify IF-THEN-ELSE - User should be able to drag a 'Field' reference to RHS of 'IF' area - acceptance criteria")
	public void TestCase_5037() {
		try {
			FormDesignerPage fdp = new FormDesignerPage(driver);
			// Open 'Form Designer'
			fdp = mainPage.clickFormDesignerMenu();
			controlActions.clickElement(fdp.SelectCheckFormLnk);
			// Selection of resource
			boolean selectResource = fdp.selectResource(resourceType, resourceCategoryValue, resourceInstanceValue1);
			TestValidation.IsTrue(selectResource, "Selected Resource successfully", "Failed to Select Resource");
			// Click on Next > Go to 'Form Design'
			boolean clickOnNextButton = fdp.clickOnNextButton(fdp.DesignFormPg, "Design Form");
			TestValidation.IsTrue(clickOnNextButton, "Clicked On Next Button successfully",
					"Failed to Click On Next Button");
			controlActions.sendKeys(fdp.FormNameTxt, checkFormName);
			
			String freeTextFieldName = "FreeText1";
			WebElement FreeTextFieldType = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_TYPE,
					"FIELDTYPE", FIELD_TYPES.FREE_TEXT);
			controlActions.dragdrop(FreeTextFieldType, fdp.FieldTypeDropAreaFormLevel);
			TestValidation.IsTrue(fdp.setTextBoxValue(FIELD_TYPES.FREE_TEXT, freeTextFieldName),
					"'Free Text field' added successfully", "Failed to Add Free Text Field");
			
			String dateFieldName = "Date1";
			WebElement DateField = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_TYPE,
					"FIELDTYPE", FIELD_TYPES.DATE);
			controlActions.dragdrop(DateField, fdp.FieldTypeDropAreaFormLevel);
			boolean setDateFieldValue1 = fdp.setTextBoxValue(FIELD_TYPES.DATE, dateFieldName);
			TestValidation.IsTrue(setDateFieldValue1, "'Date' field added successfully", " Failed to Add Date field");

			// Add Dependency Rule
			TestValidation.IsTrue(fdp.addDependencyRuleUsingValue(freeTextFieldName, "Test"),
					"'Dependency rule is present verified successfully'",
					"Failed to check dependency rule's presence.");
		
			//  Try to delete dependent field.
		    fdp.deleteFieldType(FieldType.FREETEXT);
		    
		    fdp.checkFieldIsInUseErrorMessage();
			TestValidation.IsTrue(fdp.deleteDependencyRule(), "'Dependency rule is deleted successfully.'", "Failed to delete dependency rule.");
					
		
		} finally {
			if (hp.clickHomepageLinkAndBypassPopup().error) {
				TCGFailureMsg = "Could NOT land on FSQA Browser page after clicking on Home link";
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}
		}
	}
	
	@AfterClass(alwaysRun = true)
	public void closeBrowser() throws InterruptedException {
		driver.close();
	}
}
