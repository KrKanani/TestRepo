package com.project.safetychain.webapp.testcases;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.project.safetychain.webapp.pageobjects.CommonPage;
import com.project.safetychain.webapp.pageobjects.DocumentManagementPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserDocumentsPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserFormsPage;
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
import com.project.safetychain.webapp.testcases.support.FormOperations;
import com.project.safetychain.webapp.testcases.support.FormOperations.FormDetails;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.PreviewFormDetails;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.COLUMNHEADER;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.AGGREGATE_FUNCTION;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.ELEMENT_TYPE;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FIELD_TYPES;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FORM_ELEMENTS;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.IDENTIFIER_INPUT_TYPE;
import com.project.safetychain.webapp.pageobjects.ResourceDesignerPage.CATEGORYTYPES;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.utilities.CommonMethods;
import com.project.utilities.ControlActions;

public class TCG_SMK_QuestionnaireFormFlow extends TestBase {

	ControlActions controlActions;
	FormDesignerPage fdp;
	HomePage hp;
	ResourceDesignerPage resourceDesigner;
	ManageResourcePage manageResource;
	ManageLocationPage locations;
	CommonPage mainPage;
	DocumentManagementPage dmp;
	FSQABrowserDocumentsPage fbdp;
	FSQABrowserFormsPage fbfp ;	
	FormOperations fop;
	PreviewFormDetails pfd;

	public static String questionnaireFormName1;
	public static String questionnaireFormName2;
	public static String questionnaireFormName3;
	public static String questionnaireFormName4;
	public static String questionnaireFormName5;
	public static String questionnaireFormName6;
	public static String locationCategoryValue;
	public static String resourceCategoryValue;
	public static String locationInstanceValue;
	public static String resourceInstanceValue;

	@BeforeClass(alwaysRun = true)
	public void groupInit() throws Exception {
		questionnaireFormName1	= CommonMethods.dynamicText("Automation_QuestionnaireForm1");
		questionnaireFormName2	= CommonMethods.dynamicText("Automation_QuestionnaireForm2");
		questionnaireFormName3	= CommonMethods.dynamicText("Automation_QuestionnaireForm3");
		questionnaireFormName4	= CommonMethods.dynamicText("Automation_QuestionnaireForm4");
		questionnaireFormName5	= CommonMethods.dynamicText("Automation_QuestionnaireForm5");
		questionnaireFormName6	= CommonMethods.dynamicText("Automation_QuestionnaireForm6");
		locationCategoryValue	= CommonMethods.dynamicText("Loc_Cat");
		resourceCategoryValue	= CommonMethods.dynamicText("Customers_Cat");
		locationInstanceValue	= CommonMethods.dynamicText("Loc_Inst");
		resourceInstanceValue 	= CommonMethods.dynamicText("Cust_Inst1");

		driver = launchbrowser();
		controlActions = new ControlActions(driver);
		controlActions.getUrl(applicationUrl);

		resourceDesigner = new ResourceDesignerPage(driver);
		mainPage = new CommonPage(driver);
		locations = new ManageLocationPage(driver);
		manageResource = new ManageResourcePage(driver);
		fbfp = new FSQABrowserFormsPage(driver);		
		fop = new FormOperations(driver);

		LoginPage lp = new LoginPage(driver);
		hp = lp.performLogin(adminUsername, adminPassword);
		if(hp.error) {
			TCGFailureMsg = "Could NOT login to application";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
		
		HashMap<String,String> resourceCategories = new HashMap<String,String>();
		resourceCategories.put(CATEGORYTYPES.LOCATION, locationCategoryValue);
		resourceCategories.put(CATEGORYTYPES.CUSTOMERS, resourceCategoryValue);

		HashMap<String,Boolean> locInstance = new HashMap<String, Boolean>();
		locInstance.put(locationInstanceValue, true);
		LocationInstanceParams lip = new LocationInstanceParams();
		lip.CategoryName = locationCategoryValue;
		lip.NumberFieldValue = 10;
		lip.TextFieldValue = "XYZ";
		lip.InstanceName = locInstance;

		HashMap<String,Boolean> instances = new HashMap<String, Boolean>();
		instances.put(resourceInstanceValue, true);
		ResourceDetailParams rd = new ResourceDetailParams();
		rd.CategoryName = resourceCategoryValue;
		rd.CategoryType = RESOURCETYPES.CUSTOMERS;
		rd.NumberFieldValue = 30;
		rd.TextFieldValue = "ABC";
		rd.InstanceNames = instances;
		rd.LocationInstanceValue = locationInstanceValue;	

		if(!resourceDesigner.createCategory(resourceCategories)) {
			TCGFailureMsg = "Could NOT create Location and Customers category";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		ManageLocationPage mlp = hp.clickLocationsMenu();
		if(!mlp.createLocation(lip)) {
			TCGFailureMsg = "Could NOT create Location instance";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		ManageResourcePage mrp = hp.clickResourcesMenu();
		if(!mrp.createResourceLinkLocation(rd)) {
			TCGFailureMsg = "Could NOT create Instances for resource - " + resourceCategoryValue;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
		
	}

	@Test(description="Add fields in the Section, Group and in a Group which is inside Section and release the form")
	public void TestCase_31148() {

		FormDesignerPage fdp = new FormDesignerPage(driver);

		//Open 'Form Designer'
		fdp = hp.clickFormDesignerMenu();

		controlActions.clickElement(fdp.SelectQuestionnaireFormLnk);

		//Selection of resource
		boolean selectResource = fdp.selectResource("Customers",resourceCategoryValue,resourceInstanceValue);
		TestValidation.IsTrue(selectResource, 
				"Selected Resource successfully", 
				"Failed to Select Resource");

		//Go to 'Form Design'
		boolean clickOnNextButton = fdp.clickOnNextButton(fdp.DesignFormPg,"Design Form");
		TestValidation.IsTrue(clickOnNextButton, 
				"Clicked On Next Button successfully", 
				"Failed to Click On Next Button");

		controlActions.sendKeys(fdp.FormNameTxt, questionnaireFormName1);

		WebElement NumericField = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_TYPE,"FIELDTYPE","Numeric");
		controlActions.dragdrop(NumericField, fdp.FieldTypeDropAreaFormLevel);
		boolean setNumericFieldValue1 = fdp.setElementValueTextArea("Numeric", "Numeric111");		
		TestValidation.IsTrue(setNumericFieldValue1, 
				"'Numeric' field added successfully", 
				"Failed to Add Numeric field");		


		//Add Field in Group

		WebElement GroupElement = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FORM_ELEMENT,"FORMELEMENT","Field Group");
		controlActions.dragdrop(GroupElement, fdp.FormLevel);
		boolean setGroupFieldValue = fdp.setElementValueTextArea("Field Group", "FieldGroup1");		
		TestValidation.IsTrue(setGroupFieldValue, 
				"'Field Group' added successfully", 
				"Failed to Add Field Group");

		boolean dragDropNumericField = fdp.dragDropElementInSectionGroup(ELEMENT_TYPE.FIELD_TYPES, FIELD_TYPES.NUMERIC, "FieldGroup1");
		TestValidation.IsTrue(dragDropNumericField, 
				"Drag-Drop Numeric field in Section successfully", 
				"Failed to Drag-Drop Numeric field in Section");

		boolean setNumericFieldValue2 = fdp.setElementValueTextArea(FIELD_TYPES.NUMERIC, "Numeric1");
		TestValidation.IsTrue(setNumericFieldValue2, 
				"Set name to Numeric field successfully", 
				"Failed to Set name to Numeric field");


		//Add Field in Section

		WebElement SectionElement1 = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FORM_ELEMENT,"FORMELEMENT","Section");
		controlActions.dragdrop(SectionElement1, fdp.FieldTypeDropAreaFormLevel);
		boolean setSectionFieldValue1 = fdp.setElementValueTextArea("Section", "Section1");		
		TestValidation.IsTrue(setSectionFieldValue1, 
				"'Section' added successfully", 
				"Failed to Add Section");

		boolean dragDropSingleLineTextField = fdp.dragDropElementInSectionGroup(ELEMENT_TYPE.FIELD_TYPES, FIELD_TYPES.SINGLE_LINE_TEXT, "Section1");
		TestValidation.IsTrue(dragDropSingleLineTextField, 
				"Drag-Drop Single Line Text field in Section successfully", 
				"Failed to Drag-Drop Single Line Text field in Section");

		boolean setSingleLineTextFieldValue = fdp.setElementValueTextArea(FIELD_TYPES.SINGLE_LINE_TEXT, "SingleLineText1");
		TestValidation.IsTrue(setSingleLineTextFieldValue, 
				"Set name to Single Line Text field successfully", 
				"Failed to Set name to Single Line Text field");


		//Add Field in Group in Section

		WebElement SectionElement2 = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FORM_ELEMENT,"FORMELEMENT","Section");
		controlActions.dragdrop(SectionElement2, fdp.FieldTypeDropAreaFormLevel);
		boolean setSectionFieldValue2 = fdp.setElementValueTextArea("Section", "Section2");		
		TestValidation.IsTrue(setSectionFieldValue2, 
				"'Section' added successfully", 
				"Failed to Add Section");		

		boolean dragDropFieldGroupElement = fdp.dragDropElementInSectionGroup(ELEMENT_TYPE.FORM_ELEMENTS,FORM_ELEMENTS.FIELD_GROUP, "Section2");
		TestValidation.IsTrue(dragDropFieldGroupElement, 
				"Drag-Drop Field Group in Section successfully", 
				"Failed to Drag-Drop Field Group in Section");

		boolean setFieldGroupValue = fdp.setElementValueTextArea(FORM_ELEMENTS.FIELD_GROUP, "FieldGroup2");
		TestValidation.IsTrue(setFieldGroupValue, 
				"Set name to Field Group Element successfully", 
				"Failed to Set name to Field Group Element");		

		boolean dragDropDateField = fdp.dragDropElementInSectionGroup(ELEMENT_TYPE.FIELD_TYPES, FIELD_TYPES.DATE, "FieldGroup2");
		TestValidation.IsTrue(dragDropDateField, 
				"Drag-Drop Date field in Field Group successfully", 
				"Failed to Drag-Drop Date field in Field Group");

		boolean setDateFieldValue = fdp.setElementValueTextArea(FIELD_TYPES.DATE, "Date1");
		TestValidation.IsTrue(setDateFieldValue, 
				"Set name to Date field successfully", 
				"Failed to Set name to Date field");		

		boolean saveForm = fdp.clickSaveButton();
		TestValidation.IsTrue(saveForm, 
				"Saved form Successfully", 
				"Could NOT Save Form");

		boolean nextToReleaseForm = fdp.clickOnNextButton(fdp.ReleaseFormPg,"Release Form");
		TestValidation.IsTrue(nextToReleaseForm, 
				"NAVIGATED to 'Release Form'", 
				"Could NOT Navigate to 'Release Form'");

		boolean releaseForm = fdp.releaseForm(questionnaireFormName1);
		TestValidation.IsTrue(releaseForm, 
				"RELEASED form - "+questionnaireFormName1, 
				"Could NOT release form'");
	}	

	@Test(description="Form Designer || Add Identifier field at the Form Level of the form")
	public void TestCase_31127() {

		FormDesignerPage fdp = new FormDesignerPage(driver);

		//Open 'Form Designer'
		fdp = hp.clickFormDesignerMenu();

		controlActions.clickElement(fdp.SelectQuestionnaireFormLnk);

		//Selection of resource
		boolean selectResource = fdp.selectResource("Customers",resourceCategoryValue,resourceInstanceValue);
		TestValidation.IsTrue(selectResource, 
				"Selected Resource successfully", 
				"Failed to Select Resource");

		//Go to 'Form Design'
		boolean clickOnNextButton = fdp.clickOnNextButton(fdp.DesignFormPg,"Design Form");
		TestValidation.IsTrue(clickOnNextButton, 
				"Clicked On Next Button successfully", 
				"Failed to Click On Next Button");

		controlActions.sendKeys(fdp.FormNameTxt, questionnaireFormName2);

		WebElement IdentifierField1 = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FORM_ELEMENT,"FORMELEMENT","Identifier");
		controlActions.dragdrop(IdentifierField1, fdp.FieldTypeDropAreaFormLevel);
		boolean setIdentifierFieldValue1 = fdp.setElementValueTextArea("Identifier", "Identifier1");		
		TestValidation.IsTrue(setIdentifierFieldValue1, 
				"'Identifier' field added successfully", 
				"Failed to Add Identifier field");		

		boolean selectIdentifierInputType1 = fdp.selectIdentifierInputType(IDENTIFIER_INPUT_TYPE.FREE_TEXT);		
		TestValidation.IsTrue(selectIdentifierInputType1, 
				"Selected Identifier Input Type successfully", 
				"Failed to Select Identifier Input Type");

		WebElement IdentifierField2 = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FORM_ELEMENT,"FORMELEMENT","Identifier");
		controlActions.dragdrop(IdentifierField2, fdp.FieldTypeDropAreaFormLevel);
		boolean setIdentifierFieldValue2 = fdp.setElementValueTextArea("Identifier", "Identifier2");		
		TestValidation.IsTrue(setIdentifierFieldValue2, 
				"'Identifier' field added successfully", 
				"Failed to Add Identifier field");		

		boolean selectIdentifierInputType2 = fdp.selectIdentifierInputType(IDENTIFIER_INPUT_TYPE.SELECT_ONE);		
		TestValidation.IsTrue(selectIdentifierInputType2, 
				"Selected Identifier Input Type successfully", 
				"Failed to Select Identifier Input Type");

		boolean setValuesToElement1 = fdp.setValuesToElement("ID1");
		TestValidation.IsTrue(setValuesToElement1, 
				"Set value for Identifier field successfully", 
				"Failed to Set value for Identifier field");

		boolean setValuesToElement2 = fdp.setValuesToElement("ID2");
		TestValidation.IsTrue(setValuesToElement2, 
				"Set value for Identifier field successfully", 
				"Failed to Set value for Identifier field");

		WebElement IdentifierField3 = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FORM_ELEMENT,"FORMELEMENT","Identifier");
		controlActions.dragdrop(IdentifierField3, fdp.FieldTypeDropAreaFormLevel);
		boolean setIdentifierFieldValue3 = fdp.setElementValueTextArea("Identifier", "Identifier3");		
		TestValidation.IsTrue(setIdentifierFieldValue3, 
				"'Identifier' field added successfully", 
				"Failed to Add Identifier field");		

		boolean selectIdentifierInputType3 = fdp.selectIdentifierInputType(IDENTIFIER_INPUT_TYPE.FREE_TEXT);		
		TestValidation.IsTrue(selectIdentifierInputType3, 
				"Selected Identifier Input Type successfully", 
				"Failed to Select Identifier Input Type");

		boolean setIdentifierInputMask = fdp.setIdentifierInputMask("L0");		
		TestValidation.IsTrue(setIdentifierInputMask, 
				"Identifier Input Mask set successfully", 
				"Failed to set Identifier Input Mask");

		boolean saveForm = fdp.clickSaveButton();
		TestValidation.IsTrue(saveForm, 
				"Saved form Successfully", 
				"Could NOT Save Form");

		boolean nextToReleaseForm = fdp.clickOnNextButton(fdp.ReleaseFormPg,"Release Form");
		TestValidation.IsTrue(nextToReleaseForm, 
				"NAVIGATED to 'Release Form'", 
				"Could NOT Navigate to 'Release Form'");

		boolean releaseForm = fdp.releaseForm(questionnaireFormName2);
		TestValidation.IsTrue(releaseForm, 
				"RELEASED form - "+questionnaireFormName2, 
				"Could NOT release form'");

	}	

	@Test(description="Form Designer || Check user should be able to create and release \"Questionnaire\" type form")
	public void TestCase_31179() {

		FormDesignerPage fdp = new FormDesignerPage(driver);

		//Open 'Form Designer'
		fdp = hp.clickFormDesignerMenu();

		controlActions.clickOnElement(fdp.SelectQuestionnaireFormLnk);

		//Selection of resource
		boolean selectResource = fdp.selectResource("Customers",resourceCategoryValue,resourceInstanceValue);
		TestValidation.IsTrue(selectResource, 
				"Selected Resource successfully", 
				"Failed to Select Resource");

		//Go to 'Form Design'
		boolean clickOnNextButton = fdp.clickOnNextButton(fdp.DesignFormPg,"Design Form");
		TestValidation.IsTrue(clickOnNextButton, 
				"Clicked On Next Button successfully", 
				"Failed to Click On Next Button");

		controlActions.sendKeys(fdp.FormNameTxt, questionnaireFormName3);

		WebElement NumericField = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_TYPE,"FIELDTYPE","Numeric");
		controlActions.dragdrop(NumericField, fdp.FieldTypeDropAreaFormLevel);
		boolean setNumericFieldValue1 = fdp.setElementValueTextArea("Numeric", "Numeric111");		
		TestValidation.IsTrue(setNumericFieldValue1, 
				"'Numeric' field added successfully", 
				"Failed to Add Numeric field");		

		boolean saveForm = fdp.clickSaveButton();
		TestValidation.IsTrue(saveForm, 
				"Saved form Successfully", 
				"Could NOT Save Form");

		boolean nextToReleaseForm = fdp.clickOnNextButton(fdp.ReleaseFormPg,"Release Form");
		TestValidation.IsTrue(nextToReleaseForm, 
				"NAVIGATED to 'Release Form'", 
				"Could NOT Navigate to 'Release Form'");

		boolean releaseForm = fdp.releaseForm(questionnaireFormName3);
		TestValidation.IsTrue(releaseForm, 
				"RELEASED form - "+questionnaireFormName2, 
				"Could NOT release form'");

		FSQABrowserPage fbp = hp.clickFSQABrowserMenu();

		boolean selectResourceDropDownandNavigate =  fbp.selectResourceDropDownandNavigate("Customers","Forms");	
		TestValidation.IsTrue(selectResourceDropDownandNavigate, 
				"NAVIGATED to FSQA -> Forms Tab", 
				"Could NOT Navigate to FSQA -> Forms Tab");

		boolean applyFilterAndOpenForDetails = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, questionnaireFormName3);
		TestValidation.IsTrue(applyFilterAndOpenForDetails, 
				"Verified form is displayed in Forms Tab", 
				"Form is NOT displayed in Documents Tab");
	}	

	@Test(description="Form Designer || Add Aggregate type field in the form and verify the value in it")
	public void TestCase_31151() {

		FormDesignerPage fdp = new FormDesignerPage(driver);

		//Open 'Form Designer'
		fdp = hp.clickFormDesignerMenu();		
		controlActions.clickOnElement(fdp.SelectQuestionnaireFormLnk);

		//Selection of resource
		boolean selectResource = fdp.selectResource("Customers",resourceCategoryValue,resourceInstanceValue);
		TestValidation.IsTrue(selectResource, 
				"Selected Resource successfully", 
				"Failed to Select Resource");


		//Go to 'Form Design'
		boolean clickOnNextButton = fdp.clickOnNextButton(fdp.DesignFormPg,"Design Form");
		TestValidation.IsTrue(clickOnNextButton, 
				"Clicked On Next Button successfully", 
				"Failed to Click On Next Button");

		controlActions.sendKeys(fdp.FormNameTxt, questionnaireFormName4);

		WebElement NumericField1 = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_TYPE,"FIELDTYPE","Numeric");
		controlActions.dragdrop(NumericField1, fdp.FieldTypeDropAreaFormLevel);
		boolean setNumericFieldValue1 = fdp.setElementValueTextArea("Numeric", "Numeric1");		
		TestValidation.IsTrue(setNumericFieldValue1, 
				"'Numeric' field added successfully", 
				"Failed to Add Numeric field");		

		WebElement NumericField2 = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_TYPE,"FIELDTYPE","Numeric");
		controlActions.dragdrop(NumericField2, fdp.FieldTypeDropAreaFormLevel);
		boolean setNumericFieldValue2 = fdp.setElementValueTextArea("Numeric", "Numeric1");		
		TestValidation.IsTrue(setNumericFieldValue2, 
				"'Numeric' field added successfully", 
				"Failed to Add Numeric field");		

		// SUM		
		WebElement Aggregate1 = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FORM_ELEMENT,"FORMELEMENT","Aggregate");
		controlActions.dragdrop(Aggregate1, fdp.FormLevel);
		boolean setAggregateFieldValue1 = fdp.setElementValueTextArea("Aggregate", "Aggregate1");		
		TestValidation.IsTrue(setAggregateFieldValue1, 
				"Aggregate Field added successfully", 
				"Failed to Add Aggregate Field");

		boolean selectAggregateFunction1 = fdp.selectAggregateFunction(AGGREGATE_FUNCTION.SUM);
		TestValidation.IsTrue(selectAggregateFunction1, 
				"Aggregate Function "+ AGGREGATE_FUNCTION.SUM +" selected successfully", 
				"Failed to select Aggregate Function "+ AGGREGATE_FUNCTION.SUM);

		boolean selectAggregateSource1 = fdp.setAggregateSource("Numeric1");
		TestValidation.IsTrue(selectAggregateSource1, 
				"Aggregate Source - Numeric1 selected successfully", 
				"Failed to select Aggregate Source - Numeric1");


		// % PASS		
		WebElement Aggregate2 = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FORM_ELEMENT,"FORMELEMENT","Aggregate");
		controlActions.dragdrop(Aggregate2, fdp.FormLevel);
		boolean setAggregateFieldValue2 = fdp.setElementValueTextArea("Aggregate", "Aggregate2");		
		TestValidation.IsTrue(setAggregateFieldValue2, 
				"Aggregate Field added successfully", 
				"Failed to Add Aggregate Field");

		boolean selectAggregateFunction2 = fdp.selectAggregateFunction(AGGREGATE_FUNCTION.PERCENT_PASS);
		TestValidation.IsTrue(selectAggregateFunction2, 
				"Aggregate Function "+ AGGREGATE_FUNCTION.PERCENT_PASS +" selected successfully", 
				"Failed to select Aggregate Function "+ AGGREGATE_FUNCTION.PERCENT_PASS);

		boolean selectAggregateSource2 = fdp.setAggregateSource("Numeric1");
		TestValidation.IsTrue(selectAggregateSource2, 
				"Aggregate Source - Numeric1 selected successfully", 
				"Failed to select Aggregate Source - Numeric1");

		boolean setComplianceForNumericField1 = fdp.setComplianceForNumericAggragateField("Numeric1", "1", "3", "5", null);
		TestValidation.IsTrue(setComplianceForNumericField1, 
				"Set Compliance For Numeric Field - Numeric1 successfully", 
				"Failed to Set Compliance For Numeric Field - Numeric1");

		boolean setComplianceForNumericField2 = fdp.setComplianceForNumericAggragateField("Numeric11", "1", "3", "5", null);
		TestValidation.IsTrue(setComplianceForNumericField2, 
				"Set Compliance For Numeric Field - Numeric2 successfully", 
				"Failed to Set Compliance For Numeric Field - Numeric2");


		// SAVE NEXT RELEASE
		boolean saveForm = fdp.clickSaveButton();
		TestValidation.IsTrue(saveForm, 
				"Saved form Successfully", 
				"Could NOT Save Form");

		boolean nextToReleaseForm = fdp.clickOnNextButton(fdp.ReleaseFormPg,"Release Form");
		TestValidation.IsTrue(nextToReleaseForm, 
				"NAVIGATED to 'Release Form'", 
				"Could NOT Navigate to 'Release Form'");

		boolean releaseForm = fdp.releaseForm(questionnaireFormName4);
		TestValidation.IsTrue(releaseForm, 
				"RELEASED form - "+questionnaireFormName4, 
				"Could NOT release form'");

		FSQABrowserPage fbp = hp.clickFSQABrowserMenu();

		boolean selectResourceDropDownandNavigate =  fbp.selectResourceDropDownandNavigate("Customers","Forms");	
		TestValidation.IsTrue(selectResourceDropDownandNavigate, 
				"NAVIGATED to FSQA -> Forms Tab", 
				"Could NOT Navigate to FSQA -> Forms Tab");		

		boolean applyFilterAndOpenForDetails = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, questionnaireFormName4);
		TestValidation.IsTrue(applyFilterAndOpenForDetails, 
				"Verified form is displayed in Forms Tab", 
				"Form is NOT displayed in Documents Tab");		

		FormDetails fd = new FormDetails();
		HashMap<String, List<String>> map1 = new HashMap<String, List<String>>();
		map1.put("Numeric1",Arrays.asList("2"));

		fd.fieldDetails = map1;
		fd.resourceName = resourceInstanceValue;
		fd.locationName = locationInstanceValue;
		boolean submit1 = fop.submitData(fd);
		TestValidation.IsTrue(submit1, 
				"Entered data and Submitted form successfully", 
				"Could NOT Enter data and Submit form");				

		boolean selectResourceDropDownandNavigate1 =  fbp.selectResourceDropDownandNavigate("Customers","Documents");	
		TestValidation.IsTrue(selectResourceDropDownandNavigate1, 
				"NAVIGATED to FSQA -> Documents Tab", 
				"Could NOT Navigate to FSQA -> Documents Tab");

		fbdp = new FSQABrowserDocumentsPage(driver);		
		boolean selectResource1 = fbdp.selectResource("Customers", resourceCategoryValue, resourceInstanceValue);
		TestValidation.IsTrue(selectResource1, 
				"Selected Resource successfully - "+resourceInstanceValue, 
				"Could NOT Select Resource - "+resourceInstanceValue);

		boolean selectOptionFromTools = fbdp.selectOptionFromTools(questionnaireFormName4, "View Document");
		TestValidation.IsTrue(selectOptionFromTools, 
				"Selected View Document  option from Tools successfully ", 
				"Could NOT select View Document option from Tools "); 

		String fieldValue1 = fbdp.getFieldValueFormDetails("Aggregate1");
		boolean compare1 = fieldValue1.contains("4");
		String fieldValue2 = fbdp.getFieldValueFormDetails("Aggregate2");
		boolean compare2 = fieldValue2.contains("100");
		TestValidation.IsTrue(compare1 && compare2, 
				"Aggregate field Value is populated correctly ", 
				"Could NOT verfiy Aggregate field value");

	}

	@Test(description = "Creation of Expression Rule")
	public void TestCase_31246() {

		FormDesignerPage fdp = new FormDesignerPage(driver);

		//Open 'Form Designer'
		fdp = hp.clickFormDesignerMenu();

		controlActions.clickElement(fdp.SelectQuestionnaireFormLnk);

		//Selection of resource
		boolean selectResource = fdp.selectResource("Customers",resourceCategoryValue,resourceInstanceValue);
		TestValidation.IsTrue(selectResource, 
				"Selected Resource successfully", 
				"Failed to Select Resource");

		//Go to 'Form Design'
		boolean clickOnNextButton = fdp.clickOnNextButton(fdp.DesignFormPg,"Design Form");
		TestValidation.IsTrue(clickOnNextButton, 
				"Clicked On Next Button successfully", 
				"Failed to Click On Next Button");

		controlActions.sendKeys(fdp.FormNameTxt, questionnaireFormName5);

		List<String> Values = Arrays.asList("Good", "Bad");

		WebElement NumericField = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_TYPE,"FIELDTYPE","Numeric");
		controlActions.dragdrop(NumericField, fdp.FieldTypeDropAreaFormLevel);
		boolean setNumericFieldValue1 = fdp.setElementValueTextArea("Numeric", "Numeric1");		
		TestValidation.IsTrue(setNumericFieldValue1, 
				"'Numeric' field added successfully", 
				"Failed to Add Numeric field");		

		WebElement SelectOneField = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_TYPE,"FIELDTYPE","Select One");
		controlActions.dragdrop(SelectOneField, fdp.FieldTypeDropAreaFormLevel);
		boolean setSelectOneFieldValue1 = fdp.setElementValueTextArea(FIELD_TYPES.SELECT_ONE, "SelectOne1");		
		TestValidation.IsTrue(setSelectOneFieldValue1, 
				"'SelectOne' field added successfully", 
				"Failed to Add SelectOne field");	

		boolean SelectDrpDwn = fdp.addValuesToSelectDropDown(Values);
		TestValidation.IsTrue(SelectDrpDwn, "Add Values To the DropDown", "Could NOT Add Values to Value Field Type");

		boolean setExprRule = fdp.ifExpressionRuleUsingValue("Numeric1", "7", "Good", "Bad");
		TestValidation.IsTrue(setExprRule, 
				  			  "Successfully Added IF expression rule on field - SelectOne1", 
				  			  "Failed to add IF expression rule on field - SelectOne1");

		boolean saveForm = fdp.clickSaveButton();
		TestValidation.IsTrue(saveForm, 
				"Saved form Successfully", 
				"Could NOT Save Form");
	}

	@Test(description = "Rule Builder || Add dependency rule on a field and on a group and verify the form")
	public void TestCase_31245() {
		
		FormDesignerPage fdp = new FormDesignerPage(driver);

		//Open 'Form Designer'
		fdp = hp.clickFormDesignerMenu();

		controlActions.clickElement(fdp.SelectQuestionnaireFormLnk);

		//Selection of resource
		boolean selectResource = fdp.selectResource("Customers",resourceCategoryValue,resourceInstanceValue);
		TestValidation.IsTrue(selectResource, 
				"Selected Resource successfully", 
				"Failed to Select Resource");

		//Go to 'Form Design'
		boolean clickOnNextButton = fdp.clickOnNextButton(fdp.DesignFormPg,"Design Form");
		TestValidation.IsTrue(clickOnNextButton, 
				"Clicked On Next Button successfully", 
				"Failed to Click On Next Button");

		controlActions.sendKeys(fdp.FormNameTxt, questionnaireFormName6);

		WebElement NumericField = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_TYPE,"FIELDTYPE",FIELD_TYPES.NUMERIC);
		controlActions.dragdrop(NumericField, fdp.FieldTypeDropAreaFormLevel);
		boolean setNumericFieldValue1 = fdp.setElementValueTextArea("Numeric", "Numeric1");		
		TestValidation.IsTrue(setNumericFieldValue1, 
				"'Numeric' field added successfully", 
				"Failed to Add Numeric field");		

		WebElement SingleLineTextField = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_TYPE,"FIELDTYPE",FIELD_TYPES.SINGLE_LINE_TEXT);
		controlActions.dragdrop(SingleLineTextField, fdp.FieldTypeDropAreaFormLevel);
		boolean setSingleLineTextFieldValue1 = fdp.setElementValueTextArea(FIELD_TYPES.SINGLE_LINE_TEXT, "SingleLineText1");		
		TestValidation.IsTrue(setSingleLineTextFieldValue1, 
				"'Single Line Text' field added successfully", 
				"Failed to Add Single Line Text Text field");	
		
		boolean DependencyRule = fdp.addDependencyRuleUsingValue("Numeric1", "5");
		TestValidation.IsTrue(DependencyRule, 
				"Field Type Added", 
				"Could NOT Add Dependency Rule");
		
		WebElement GroupElement = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FORM_ELEMENT,"FORMELEMENT","Field Group");
		controlActions.dragdrop(GroupElement, fdp.FormLevel);
		boolean setGroupFieldValue = fdp.setElementValueTextArea("Field Group", "FieldGroup1");		
		TestValidation.IsTrue(setGroupFieldValue, 
				"'Field Group' added successfully", 
				"Failed to Add Field Group");

		boolean DependencyRule1 = fdp.addDependencyRuleUsingValue("Numeric1", "5");
		TestValidation.IsTrue(DependencyRule1, 
				"Field Type Added", 
				"Could NOT Add Dependency Rule");	
		
		boolean dragDropNumericField = fdp.dragDropElementInSectionGroup(ELEMENT_TYPE.FIELD_TYPES, FIELD_TYPES.NUMERIC, "FieldGroup1");
		TestValidation.IsTrue(dragDropNumericField, 
				"Drag-Drop Numeric field in Section successfully", 
				"Failed to Drag-Drop Numeric field in Section");

		boolean setNumericFieldValue2 = fdp.setElementValueTextArea(FIELD_TYPES.NUMERIC, "Numeric2");
		TestValidation.IsTrue(setNumericFieldValue2, 
				"Set name to Numeric field successfully", 
				"Failed to Set name to Numeric field");
		
		boolean saveForm = fdp.clickSaveButton();
		TestValidation.IsTrue(saveForm, 
				"Saved form Successfully", 
				"Could NOT Save Form");

		boolean clickPreviewButton = fdp.clickPreviewButton();
		TestValidation.IsTrue(clickPreviewButton, 
				"Clicked Preview button Successfully", 
				"Could NOT click Preview button");
		
		PreviewFormDetails pfd = new PreviewFormDetails();
		LinkedHashMap<String, List<String>> map1 = new LinkedHashMap<String, List<String>>();
		map1.put("Numeric1",Arrays.asList("5"));
		map1.put("SingleLineText1",Arrays.asList("ABC"));
		map1.put("Numeric2",Arrays.asList("7"));
		
		pfd.fieldDetails = map1;
		pfd.resourceName = resourceInstanceValue;
		boolean enterDataForPreviewForm = fdp.enterDataForPreviewForm(pfd);
		TestValidation.IsTrue(enterDataForPreviewForm, 
				"Entered data for Preview form successfully", 
				"Could NOT Enter data for Preview form");
	}	
	
	@AfterClass(alwaysRun = true)
	public void closeBrowser() throws InterruptedException {
		driver.close();
	}

}	
