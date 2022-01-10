package com.project.safetychain.mobileapp.testcases;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.project.safetychain.api.models.support.Support.AGGREGATE_FUNC_TYPES;
import com.project.safetychain.api.models.support.Support.DPT_FIELD_TYPES;
import com.project.safetychain.api.models.support.Support.DPT_FORM_TYPES;
import com.project.safetychain.api.models.support.Support.Element_Types;
import com.project.safetychain.api.models.support.Support.FormFieldParams;
import com.project.safetychain.api.models.support.Support.FormParams;
import com.project.safetychain.api.models.support.Support.IDENTIFIER_TYPES;
import com.project.safetychain.api.models.support.Support.RESOURCE_TYPES;
import com.project.safetychain.api.models.support.TCG_FormCreation_Wrapper;
import com.project.safetychain.mobileapp.pageobjects.HomePage;
import com.project.safetychain.mobileapp.pageobjects.LoginPage;
import com.project.safetychain.mobileapp.pageobjects.REG_SubmissionsPage;
import com.project.safetychain.mobileapp.pageobjects.SavedPage;
import com.project.safetychain.mobileapp.pageobjects.UpdatedFormVersionPage;
import com.project.safetychain.webapp.pageobjects.CommonPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserFormsPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.COLUMNHEADER;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.COLUMNSETTING;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FormDesignParams;
import com.project.safetychain.webapp.pageobjects.ManageLocationPage;
import com.project.safetychain.webapp.pageobjects.ManageResourcePage;
import com.project.safetychain.webapp.pageobjects.ResourceDesignerPage;
import com.project.safetychain.webapp.testcases.support.FormOperations;
import com.project.safetychain.webapp.testcases.support.FormOperations.FormDetails;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.utilities.ApiUtils;
import com.project.utilities.CommonMethods;
import com.project.utilities.ControlActions;
import com.project.utilities.ControlActions_MobileApp;

import io.appium.java_client.pagefactory.AndroidFindBy;

public class TCG_REG_FormSubmissionsFlow extends TestBase {
	ControlActions_MobileApp mobControlActions;
	ApiUtils apiUtils = null;
	FormDesignerPage formDesignerPage;
	ControlActions controlActions;
	FSQABrowserPage fbp;
	FSQABrowserFormsPage fbForms;
	CommonPage mainPage;
	FormFieldParams ffp;
	FormDesignParams fp;
	FormDetails formDetails;
	com.project.safetychain.webapp.pageobjects.HomePage hp;
	com.project.safetychain.webapp.pageobjects.LoginPage lp;
	ManageResourcePage manageResource;
	ManageLocationPage locations;
	ResourceDesignerPage resourceDesigner;
	SavedPage SavedPageObj;
	FormOperations fop;

	public static String locationCategoryValue;
	public static String locationInstanceValue1;
	public static String locationInstanceValue2;
	public static String resourceCategoryValue;
	public static String resourceInstanceValue1;
	public static String resourceInstanceValue2;
	public static String chkFreeTxt, ParaTxt, chkSelectOne, SelectMultiple, Numeric, chkDate, chkTime, chkDateTime;

	public static String formName = "Automation_CheckForm_15.10_17.19.19";
	public static String resourceName = "RInst1_15.10_13.47.04";
	public static String LocationName = "LInst1_15.10_13.47.04";
	String formtype = "Check";
	String modifiedby;
	public static String FormName;

	public static final String fresult = "com.safetychain.SCM.M2:id/resultView_num";
	@AndroidFindBy(id = fresult)
	public WebElement NTxt;

	@BeforeClass(alwaysRun = true)
	public void groupInit() {
		try {
			FormName = CommonMethods.dynamicText("Automation_CheckForm");
			driver = launchbrowser();

			formDesignerPage = new FormDesignerPage(driver);
			locations = new ManageLocationPage(driver);
			manageResource = new ManageResourcePage(driver);
			fbp = new FSQABrowserPage(driver);
			fbForms = new FSQABrowserFormsPage(driver);
			resourceDesigner = new ResourceDesignerPage(driver);
			mainPage = new CommonPage(driver);
			fop = new FormOperations(driver);

			fbp = new FSQABrowserPage(driver);
			controlActions = new ControlActions(driver);
			lp = new com.project.safetychain.webapp.pageobjects.LoginPage(driver);

			// Field Short Names
			chkFreeTxt = "FreeText";
			ParaTxt = "Paragraph";
			chkSelectOne = "SelectOne";
			Numeric = "Numeric";
			chkDate = "Date";
			chkTime = "Time";
			chkDateTime = "Date&Time";
			SelectMultiple = "MutiSelect";

			TCG_FormCreation_Wrapper formCreationWrapper = new TCG_FormCreation_Wrapper();

			apiUtils = new ApiUtils();

			locationCategoryValue = CommonMethods.dynamicText("LCat");
			locationInstanceValue1 = CommonMethods.dynamicText("LInst1");
			locationInstanceValue2 = CommonMethods.dynamicText("LInst2");
			resourceCategoryValue = CommonMethods.dynamicText("RCat");
			resourceInstanceValue1 = CommonMethods.dynamicText("RInst1");
			resourceInstanceValue2 = CommonMethods.dynamicText("RInst2");

			// API Implementation

			FormFieldParams numericField = new FormFieldParams();
			numericField.DPTFieldType = DPT_FIELD_TYPES.NUMERIC;
			numericField.Name = Numeric;
			numericField.ShowHint = "true";
			numericField.Hint = "Numeric";

			// numericField.RepeatField = "true";

			FormFieldParams dateField1 = new FormFieldParams();
			dateField1.DPTFieldType = DPT_FIELD_TYPES.DATE;
			dateField1.Name = "Date1";

			FormFieldParams dateField2 = new FormFieldParams();
			dateField2.DPTFieldType = DPT_FIELD_TYPES.DATE;
			dateField2.Name = "Date2";

			FormFieldParams Identifier = new FormFieldParams();
			Identifier.DPTFieldType = DPT_FIELD_TYPES.IDENTIFIER;
			Identifier.Name = "Identifier1";
			Identifier.identifierId = IDENTIFIER_TYPES.SELECT_ONE;
			Identifier.IdentifierOption = Arrays.asList("1", "2", "3", "4");

			FormFieldParams selectOneField = new FormFieldParams();
			selectOneField.DPTFieldType = DPT_FIELD_TYPES.SELECT_ONE;
			selectOneField.Name = chkSelectOne;
			selectOneField.SelectOptions = Arrays.asList("a", "b", "c", "d");

			// Field Group 1-->ID1

			Element_Types fieldGroup = new Element_Types();
			fieldGroup.ElementType = DPT_FIELD_TYPES.FIELD_GROUP;
			fieldGroup.Name = "FieldGroup";
			fieldGroup.DependencyRule = "if(SelectOne=a;Show)else(Hide)";

			FormFieldParams Identifier2 = new FormFieldParams();
			Identifier2.DPTFieldType = DPT_FIELD_TYPES.IDENTIFIER;
			Identifier2.Name = "IDNFieldGroup";
			Identifier2.identifierId = IDENTIFIER_TYPES.FREE_TEXT;
			Identifier2.InputMask = "0000";
			fieldGroup.formFieldParams = Arrays.asList(Identifier2);

			// Field Group 2-->ID2

			Element_Types fieldGroup2 = new Element_Types();
			fieldGroup2.ElementType = DPT_FIELD_TYPES.FIELD_GROUP;
			fieldGroup2.Name = "FieldGroup2";
			fieldGroup2.DependencyRule = "if(SelectOne=b;Show)else(Hide)";

			FormFieldParams Identifier3 = new FormFieldParams();
			Identifier3.DPTFieldType = DPT_FIELD_TYPES.IDENTIFIER;
			Identifier3.Name = "IDNFieldGroup2";
			Identifier3.identifierId = IDENTIFIER_TYPES.SELECT_ONE;
			Identifier3.IdentifierOption = Arrays.asList("Test 1", "Test 2");

			fieldGroup2.formFieldParams = Arrays.asList(Identifier3);

			Element_Types Section1 = new Element_Types();
			Section1.ElementType = DPT_FIELD_TYPES.SECTION;
			Section1.Name = "Section1";

			Element_Types fieldGroup3 = new Element_Types();
			fieldGroup3.ElementType = DPT_FIELD_TYPES.FIELD_GROUP;
			fieldGroup3.Name = "FieldGroup1";
			fieldGroup3.DependencyRule = "if(Identifier1=1;Show)elseif(Identifier1=3;Show)else(Hide)";

			FormFieldParams Numeric1 = new FormFieldParams();
			Numeric1.DPTFieldType = DPT_FIELD_TYPES.NUMERIC;
			Numeric1.Name = "Numeric1";

			fieldGroup3.formFieldParams = Arrays.asList(Numeric1);

			Element_Types fieldGroup4 = new Element_Types();
			fieldGroup4.ElementType = DPT_FIELD_TYPES.FIELD_GROUP;
			fieldGroup4.Name = "FieldGroup3";
			fieldGroup4.DependencyRule = "if(Identifier1=2;Show)elseif(Identifier1=4;Show)else(Hide)";

			FormFieldParams Numeric2 = new FormFieldParams();
			Numeric2.DPTFieldType = DPT_FIELD_TYPES.NUMERIC;
			Numeric2.Name = "Numeric2";

			FormFieldParams selectMultipleField = new FormFieldParams();
			selectMultipleField.DPTFieldType = DPT_FIELD_TYPES.SELECT_MULTIPLE;
			selectMultipleField.Name = SelectMultiple;
			selectMultipleField.SelectOptions = Arrays.asList("1", "a", "2", "b");

			fieldGroup4.formFieldParams = Arrays.asList(Numeric2);
			Section1.FieldGroupParams = Arrays.asList(fieldGroup3, fieldGroup4);
			Section1.formFieldParams = Arrays.asList(selectMultipleField);
			Element_Types Section2 = new Element_Types();
			Section2.ElementType = DPT_FIELD_TYPES.SECTION;
			Section2.Name = "Section2";

			FormFieldParams TimeField1 = new FormFieldParams();
			TimeField1.DPTFieldType = DPT_FIELD_TYPES.TIME;
			TimeField1.Name = "Time1";

			FormFieldParams TimeField2 = new FormFieldParams();
			TimeField2.DPTFieldType = DPT_FIELD_TYPES.TIME;
			TimeField2.Name = "Time2";

			Element_Types fieldGroup5 = new Element_Types();
			fieldGroup5.ElementType = DPT_FIELD_TYPES.FIELD_GROUP;
			fieldGroup5.Name = "FieldGroup3";

			FormFieldParams DateTimeField1 = new FormFieldParams();
			DateTimeField1.DPTFieldType = DPT_FIELD_TYPES.DATE_TIME;
			DateTimeField1.Name = "DateTime1";

			FormFieldParams DateTimeField2 = new FormFieldParams();
			DateTimeField2.DPTFieldType = DPT_FIELD_TYPES.DATE_TIME;
			DateTimeField2.Name = "DateTime2";

			fieldGroup5.formFieldParams = Arrays.asList(DateTimeField1, DateTimeField1);

			FormFieldParams Numeric3 = new FormFieldParams();
			Numeric3.DPTFieldType = DPT_FIELD_TYPES.NUMERIC;
			Numeric3.Name = "Numeric3";

			FormFieldParams Numeric4 = new FormFieldParams();
			Numeric4.DPTFieldType = DPT_FIELD_TYPES.NUMERIC;
			Numeric4.Name = "Numeric4";
			Numeric4.Repeat = "3";

			FormFieldParams Numeric5 = new FormFieldParams();
			Numeric5.DPTFieldType = DPT_FIELD_TYPES.NUMERIC;
			Numeric5.Name = "Numeric5";
			Section2.formFieldParams = Arrays.asList(TimeField1, TimeField1, Numeric3, Numeric4, Numeric5);
			Section2.FieldGroupParams = Arrays.asList(fieldGroup5);

			Section2.FieldGroupParams = Arrays.asList(fieldGroup5);
			Section2.formFieldParams = Arrays.asList(TimeField1, TimeField2, Numeric3, Numeric4, Numeric5);

			FormFieldParams AGG1 = new FormFieldParams();
			AGG1.DPTFieldType = DPT_FIELD_TYPES.AGGREGATE;
			AGG1.Name = "AGG1-SUM";
			AGG1.SelectedFunction = AGGREGATE_FUNC_TYPES.SUM;
			AGG1.SelectedSource = "Numeric4";

			FormFieldParams AGG2 = new FormFieldParams();
			AGG2.DPTFieldType = DPT_FIELD_TYPES.AGGREGATE;
			AGG2.Name = "AGG1-SumRange";
			AGG2.SelectedFunction = AGGREGATE_FUNC_TYPES.SUM_RANGE;
			AGG2.AGG_MIN = "1";
			AGG2.AGG_MAX = "1000";
			AGG2.SelectedSourceCollection = Arrays.asList("Numeric5", "Numeric3");

			String formId = apiUtils.getUUID();
			// Form details
			logInfo("FormId = " + formId);
			FormParams fp = new FormParams();
			logInfo("Form Name = " + formId);
			fp.FormId = formId;
			fp.FormName = FormName;
			logInfo("Form Name 2 = " + FormName);
			fp.type = DPT_FORM_TYPES.CHECK;
			fp.ResourceType = RESOURCE_TYPES.CUSTOMERS;

			fp.ResourceCategoryNm = resourceCategoryValue;
			fp.ResourceInstanceNm = resourceInstanceValue1;
			fp.formElements = Arrays.asList(numericField, dateField1, dateField2, Identifier, selectOneField, Numeric3,
					Numeric4, Numeric5, AGG1, AGG2);

			fp.SectionElements = Arrays.asList(fieldGroup, fieldGroup2, Section1, Section2);

			logInfo("fp.formElements = " + fp.formElements);

			HashMap<String, List<String>> resourceCatMap = new HashMap<String, List<String>>();
			resourceCatMap.put(resourceCategoryValue, Arrays.asList(resourceInstanceValue1, resourceInstanceValue2));
			// fp.CustomerResources = resourceCatMap;
			fp.CustomerResources = resourceCatMap;

			HashMap<String, List<String>> LocationMap = new HashMap<String, List<String>>();
			LocationMap.put(locationCategoryValue, Arrays.asList(locationInstanceValue1, locationInstanceValue2));
			List<String> userList = Arrays.asList(mobileAppUsername02, mobileAppUsername);

			formCreationWrapper.Resource_Location_CreationWrapper(resourceCatMap, LocationMap, true, userList,
					fp.ResourceType);
			formCreationWrapper.API_Wrapper_Forms(fp);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test(description = "Form Submission")
	public void TestCase_36790() throws Exception {
		appiumDriver = launchMobileApp();
		mobControlActions = new ControlActions_MobileApp(appiumDriver);
		REG_SubmissionsPage SavedPageObj = new REG_SubmissionsPage(appiumDriver);
		LoginPage loginPage = new LoginPage(appiumDriver);
		HomePage homePage = new HomePage(appiumDriver);
		if (mobileApp_Tenant.equals("test1.stage")) {
			boolean SSOLogin = loginPage.SSO_Login(mobileAppUsername, mobileAppPassword);
			TestValidation.IsTrue(SSOLogin, "logged into the application with User " + mobileAppUsername,
					"Failed to log in");

		} else {
			boolean login = loginPage.Login(mobileAppUsername, mobileAppPassword);
			TestValidation.IsTrue(login, "logged into the application with User " + mobileAppUsername,
					"Failed to log in");

		}
		Boolean searchFrms = homePage.SearchForm(FormName);

		TestValidation.IsTrue(searchFrms, "Form Searched successfully", "Failed to search Form");

		Boolean clickFrms = homePage.ClickForm(FormName);
		TestValidation.IsTrue(clickFrms, "Form Clicked successfully", "Failed to click Form");
		Boolean searchLocation = SavedPageObj.SearchResource(locationInstanceValue1);
		TestValidation.IsTrue(searchLocation, "Location Searched successfully", "Failed to search Location");
		Boolean searchResource = SavedPageObj.SearchResource(resourceInstanceValue1);
		TestValidation.IsTrue(searchResource, "Resource Searched successfully", "Failed to Resource Form");

		Boolean txtNumeric = SavedPageObj.enterFieldValue(SavedPageObj.NumericTxt, "8");
		TestValidation.IsTrue(txtNumeric, "Numeric Field value entered successfully", "Failed to enter field value");

		Boolean txtPara = SavedPageObj.enterFieldValue(SavedPageObj.paragraphTxt, "Para");
		TestValidation.IsTrue(txtPara, "Numeric Field value entered successfully", "Failed to enter field value");

		Boolean txtFT = SavedPageObj.enterFieldValue(SavedPageObj.freeText, "FreeText");
		TestValidation.IsTrue(txtFT, "Numeric Field value entered successfully", "Failed to enter field value");

		Boolean selectOne = SavedPageObj.selectOneOrMultipleFieldValues("SelectOne", "GOOD");
		TestValidation.IsTrue(selectOne, "Numeric Field value entered successfully", "Failed to enter field value");

		String date = ControlActions_MobileApp.AddDaystoToddaysDate(0);
		Boolean Sedate = SavedPageObj.clickFieldType(homePage.datePicker, date);
		TestValidation.IsTrue(Sedate, "Date Selected successfully", "Failed to select Date value");
		String dateSelected = ControlActions_MobileApp.AddDaystoToddaysDateInGivenFormat("M/d/YYYY", 0);
		logInfo("Date Field Value = " + dateSelected);

		Boolean SedateTime = SavedPageObj.clickFieldType(homePage.dateTimePicker, date);
		String dateTimeSel = homePage.dateTimePicker.getText();
		logInfo("Date&Time Field Value = " + dateTimeSel);
		TestValidation.IsTrue(SedateTime, "DateTime value selected successfully", "Failed to Select DateTime value");

		Boolean SeMultiple = SavedPageObj.selectOneOrMultipleFieldValues("MutiSelect", "10");
		TestValidation.IsTrue(SeMultiple, "Numeric Field value entered successfully", "Failed to enter field value");
		Boolean SeMultiple1 = SavedPageObj.selectOneOrMultipleFieldValues("MutiSelect", "30");
		TestValidation.IsTrue(SeMultiple1, "Numeric Field value entered successfully", "Failed to enter field value");

		Boolean submitForm = SavedPageObj.submitForm();
		TestValidation.IsTrue(submitForm, "Form Submitted Successfully", "Failed to Submit Form");
		Boolean clickSubmissions = homePage.ClickSubMenu(homePage.submissionsSubMenu);
		TestValidation.IsTrue(clickSubmissions, "Clicked on submissions subMenu Successfully",
				"Failed to click Submissions subMenu");
		ControlActions_MobileApp.click(SavedPageObj.sortBtn);
		Boolean searchForm = homePage.SearchForm(FormName);
		TestValidation.IsTrue(searchForm, "form Searched Successfully",
				"Failed to search form from submissions screen");
		Boolean chkStatus = SavedPageObj.checkFormStatusFromSubmissionsSubMenu(FormName, "RECEIVED");
		TestValidation.IsTrue(chkStatus, "Verified form Status Successfully", "Failed to verify Form status");

		SavedPageObj.clickFormFromSubmissionsScreen(FormName);
		LinkedHashMap<String, String> FieldTypeValues = new LinkedHashMap<String, String>();
		FieldTypeValues.put(Numeric, "8");
		FieldTypeValues.put(ParaTxt, "Para");
		FieldTypeValues.put(chkFreeTxt, "FreeText");
		FieldTypeValues.put(chkSelectOne, "GOOD");
		FieldTypeValues.put(chkDate, dateSelected);
		FieldTypeValues.put(chkDateTime, dateTimeSel);
		FieldTypeValues.put(SelectMultiple, "30");

		boolean verifyUpdatedValue = SavedPageObj.verifyFieldValues(FieldTypeValues);

		TestValidation.IsTrue(verifyUpdatedValue, "VERIFIED updated value for field type Numeric as " + FieldTypeValues,
				"Failed to verify updated value for field type Numeric as " + FieldTypeValues);

	}

	@Test(description = "Verify clear button functionality from submission button")
	public void TestCase_36798() throws Exception {
		appiumDriver = launchMobileApp();
		mobControlActions = new ControlActions_MobileApp(appiumDriver);
		REG_SubmissionsPage SavedPageObj = new REG_SubmissionsPage(appiumDriver);
		LoginPage loginPage = new LoginPage(appiumDriver);
		HomePage homePage = new HomePage(appiumDriver);
		if (mobileApp_Tenant.equals("test1.stage")) {
			boolean SSOLogin = loginPage.SSO_Login(mobileAppUsername, mobileAppPassword);
			TestValidation.IsTrue(SSOLogin, "logged into the application with User " + mobileAppUsername,
					"Failed to log in");

		} else {
			boolean login = loginPage.Login(mobileAppUsername, mobileAppPassword);
			TestValidation.IsTrue(login, "logged into the application with User " + mobileAppUsername,
					"Failed to log in");

		}
		boolean searchForm = homePage.SearchForm(FormName);
		TestValidation.IsTrue(searchForm, "Form Searched Successfully", "Failed to Search Form from Forms subMenu");
		boolean clickFormsSub = homePage.ClickForm(FormName);
		TestValidation.IsTrue(clickFormsSub, "Clicked on Forms subMenu Successfully", "Failed to click Forms subMenu");
		boolean searchLocation = SavedPageObj.SearchResource(locationInstanceValue1);
		TestValidation.IsTrue(searchLocation, "Location Searched Successfully",
				"Failed to Search Location from Forms subMenu");
		boolean searchResource = SavedPageObj.SearchResource(resourceInstanceValue1);
		TestValidation.IsTrue(searchResource, "Resource Searched Successfully",
				"Failed to Search Resource from Forms subMenu");

		Boolean txtNumeric = SavedPageObj.enterFieldValue(SavedPageObj.NumericTxt, "8");
		TestValidation.IsTrue(txtNumeric, "Numeric Field value entered successfully", "Failed to enter field value");

		Boolean txtPara = SavedPageObj.enterFieldValue(SavedPageObj.paragraphTxt, "Para");
		TestValidation.IsTrue(txtPara, "Numeric Field value entered successfully", "Failed to enter field value");

		Boolean txtFT = SavedPageObj.enterFieldValue(SavedPageObj.freeText, "FreeText");
		TestValidation.IsTrue(txtFT, "Numeric Field value entered successfully", "Failed to enter field value");

		Boolean selectOne = SavedPageObj.selectOneOrMultipleFieldValues("SelectOne", "GOOD");
		TestValidation.IsTrue(selectOne, "Numeric Field value entered successfully", "Failed to enter field value");

		String date = ControlActions_MobileApp.AddDaystoToddaysDate(0);
		Boolean Sedate = SavedPageObj.clickFieldType(homePage.datePicker, date);
		TestValidation.IsTrue(Sedate, "Date Selected successfully", "Failed to select Date value");
		Boolean SedateTime = SavedPageObj.clickFieldType(homePage.dateTimePicker, date);
		TestValidation.IsTrue(SedateTime, "DateTime value selected successfully", "Failed to Select DateTime value");

		Boolean SeMultiple = SavedPageObj.selectOneOrMultipleFieldValues("MutiSelect", "10");
		TestValidation.IsTrue(SeMultiple, "Value selected for SelectMultiple Field Type",
				"Failed to select MultiSelect value");
		Boolean SeMultiple1 = SavedPageObj.selectOneOrMultipleFieldValues("MutiSelect", "30");
		TestValidation.IsTrue(SeMultiple1, "Value selected for SelectMultiple Field Type",
				"Failed to select MultiSelect value");

		boolean submitForm = SavedPageObj.submitForm();
		TestValidation.IsTrue(submitForm, "Form Submitted Successfully", "Failed to Submit Form from Forms SubTab");
		boolean clickSubmissionsSub = homePage.ClickSubMenu(homePage.submissionsSubMenu);
		TestValidation.IsTrue(clickSubmissionsSub, "Clicked on Submissions subMenu Successfully",
				"Failed to click Submissions subMenu");
		ControlActions_MobileApp.click(SavedPageObj.sortBtn);
		searchForm = homePage.SearchForm(FormName);
		TestValidation.IsTrue(searchForm, "Form Searched Successfully", "Failed to Search Form from Forms subMenu");
		appiumDriver.hideKeyboard();
		boolean formStatus = SavedPageObj.checkFormStatusFromSubmissionsSubMenu(FormName, "RECEIVED");
		TestValidation.IsTrue(formStatus, "Clicked on Clear All Button Successfully",
				"Failed to click Clear All Button on Submissions");
		boolean clickClearAll = SavedPageObj.clickClearAll();
		TestValidation.IsTrue(clickClearAll, "Clicked on Clear All Button Successfully",
				"Failed to click Clear All Button on Submissions");
		boolean checkVisibity = SavedPageObj.checkFormVisibilityOnSubmissionScreen(FormName);
		TestValidation.IsTrue(checkVisibity, "Verified Form Visibility on Submissions Successfully",
				"Failed to Verify Form Visibility on Submissions");

	}

	@Test(description = "Verify Pin")
	public void TestCase_36827() throws Exception {
		String User = mobileAppUsername;
		String Pin = "1234";
		boolean iscompliant = true;
		String comment = "Pin Verification";
		appiumDriver = launchMobileApp();
		REG_SubmissionsPage SavedPageObj = new REG_SubmissionsPage(appiumDriver);
		LoginPage loginPage = new LoginPage(appiumDriver);
		mobControlActions = new ControlActions_MobileApp(appiumDriver);
		HomePage homePage = new HomePage(appiumDriver);
		if (mobileApp_Tenant.equals("test1.stage")) {
			boolean SSOLogin = loginPage.SSO_Login(mobileAppUsername, mobileAppPassword);
			TestValidation.IsTrue(SSOLogin, "logged into the application with User " + mobileAppUsername,
					"Failed to log in");

		} else {
			boolean login = loginPage.Login(mobileAppUsername, mobileAppPassword);
			TestValidation.IsTrue(login, "logged into the application with User " + mobileAppUsername,
					"Failed to log in");

		}
		boolean searchForm = homePage.SearchForm(FormName);
		TestValidation.IsTrue(searchForm, "Form Searched successfully", "Failed to search Form");
		Boolean clickForm = homePage.ClickForm(FormName);
		TestValidation.IsTrue(clickForm, "Clicked on Searched form Successfully",
				"Failed to click on Form on Submissions");
		Boolean searchLocation = SavedPageObj.SearchResource(locationInstanceValue1);
		TestValidation.IsTrue(searchLocation, "Searched Location Successfully", "Failed to search Form on Submissions");
		Boolean searchResource = SavedPageObj.SearchResource(resourceInstanceValue1);
		TestValidation.IsTrue(searchResource, "Searched Location Successfully", "Failed to search Form on Submissions");

		boolean clickPinBtn = SavedPageObj.ModifyPin(appiumDriver);
		TestValidation.IsTrue(clickPinBtn, "Clicked on Pin Button Successfully", "Failed to click on Pin Button");
		Boolean enterPinDetails = SavedPageObj.ModifyPinDetails(User, Pin, iscompliant, comment);
		TestValidation.IsTrue(enterPinDetails, "Pin Details entered Successfully",
				"Failed to enter pin Details on Verification Popup");
		boolean savePin = SavedPageObj.SavePin();
		TestValidation.IsTrue(savePin, "Pin Details Saved Successfully",
				"Failed to save pin Details on Verification Popup");

		Boolean txtNumeric = SavedPageObj.enterFieldValue(SavedPageObj.NumericTxt, "8");
		TestValidation.IsTrue(txtNumeric, "Numeric Field value entered successfully", "Failed to enter field value");

		Boolean txtPara = SavedPageObj.enterFieldValue(SavedPageObj.paragraphTxt, "Para");
		TestValidation.IsTrue(txtPara, "Numeric Field value entered successfully", "Failed to enter field value");

		Boolean txtFT = SavedPageObj.enterFieldValue(SavedPageObj.freeText, "FreeText");
		TestValidation.IsTrue(txtFT, "Numeric Field value entered successfully", "Failed to enter field value");

		Boolean selectOne = SavedPageObj.selectOneOrMultipleFieldValues("SelectOne", "GOOD");
		TestValidation.IsTrue(selectOne, "Numeric Field value entered successfully", "Failed to enter field value");

		String date = ControlActions_MobileApp.AddDaystoToddaysDate(0);
		Boolean Sedate = SavedPageObj.clickFieldType(homePage.datePicker, date);
		TestValidation.IsTrue(Sedate, "Date Selected successfully", "Failed to select Date value");
		Boolean SedateTime = SavedPageObj.clickFieldType(homePage.dateTimePicker, date);
		TestValidation.IsTrue(SedateTime, "DateTime value selected successfully", "Failed to Select DateTime value");

		Boolean SeMultiple = SavedPageObj.selectOneOrMultipleFieldValues("MutiSelect", "10");
		TestValidation.IsTrue(SeMultiple, "Numeric Field value entered successfully", "Failed to enter field value");
		Boolean SeMultiple1 = SavedPageObj.selectOneOrMultipleFieldValues("MutiSelect", "30");
		TestValidation.IsTrue(SeMultiple1, "Numeric Field value entered successfully", "Failed to enter field value");

		Boolean submitForm = SavedPageObj.submitForm();
		TestValidation.IsTrue(submitForm, "Form submitted Successfully", "Failed to submit Form");
		homePage.ClickSubMenu(homePage.submissionsSubMenu);
		TestValidation.IsTrue(submitForm, "Clicked on Submissions Sub Menu Successfully",
				"Failed to click Submissions subMenu ");
		ControlActions_MobileApp.click(SavedPageObj.sortBtn);
		searchForm = homePage.SearchForm(FormName);
		TestValidation.IsTrue(searchForm, "Form Searched Successfully", "Failed to search Form on Submissions");
		appiumDriver.hideKeyboard();
		clickForm = SavedPageObj.clickFormFromSubmissionsScreen(FormName);
		TestValidation.IsTrue(clickForm, "Form clicked Successfully", "Failed to click Form");
		boolean verifyPinetails = SavedPageObj.verifyPinDetailsFromSubmissionScreen(FormName, mobileApp_FNameLName,
				mobileApp_Tenant, iscompliant, comment);
		TestValidation.IsTrue(verifyPinetails, "Pin Details Verified Successfully from Submissions screen",
				"Failed to verify Pin Details from Submissions screen");

	}

	@Test(description = "UpdatedFormVersion")
	public void TestCase_36802() throws Exception {
		appiumDriver = launchMobileApp();
		mobControlActions = new ControlActions_MobileApp(appiumDriver);
		LoginPage loginPage = new LoginPage(appiumDriver);
		HomePage homePage = new HomePage(appiumDriver);
		if (mobileApp_Tenant.equals("test1.stage")) {
			boolean SSOLogin = loginPage.SSO_Login(mobileAppUsername, mobileAppPassword);
			TestValidation.IsTrue(SSOLogin, "logged into the application with User " + mobileAppUsername,
					"Failed to log in");

		} else {
			boolean login = loginPage.Login(mobileAppUsername, mobileAppPassword);
			TestValidation.IsTrue(login, "logged into the application with User " + mobileAppUsername,
					"Failed to log in");

		}
		// Form details

		homePage.SearchForm(FormName);

		// Version Check

		UpdatedFormVersionPage UpdatedFormVersionPageObj = new UpdatedFormVersionPage(appiumDriver);
		UpdatedFormVersionPageObj.FirstVersion();

		// Browser code

		hp.clickFSQABrowserMenu();

		boolean navigate = fbp.selectResourceDropDownandNavigate("CUSTOMERS", "Forms");
		TestValidation.IsTrue(navigate, "Navigated to FSQABrowser>FormsTab",
				"Failed to navigate to FSQABrowser>Forms Tab");

		boolean applyfilter = fbp.openAndApplySettingsForColumn(COLUMNHEADER.FORMNAME, COLUMNSETTING.FILTER, FormName);
		TestValidation.IsTrue(applyfilter, "Applied Filter on" + COLUMNHEADER.FORMNAME,
				"Failed to apply filter on" + COLUMNHEADER.FORMNAME);

		String version = fbp.getGridValueForColumn(COLUMNHEADER.VERSION);

		float expected_version = (Float.parseFloat(version));

		Float expected_version1 = expected_version + 1;

		logInfo("Expected Version =" + expected_version1);

		boolean editform = fbp.editForm(FormName, formDesignerPage);
		TestValidation.IsTrue(editform, "Form Edited", "Failed to edit form");

		hp.clickFSQABrowserMenu();

		boolean navigate1 = fbp.selectResourceDropDownandNavigate("CUSTOMERS", "Forms");
		TestValidation.IsTrue(navigate1, "Navigated to FSQABrowser>FormsTab",
				"Failed to navigate to FSQABrowser>Forms Tab");

		boolean applyfilter1 = fbp.openAndApplySettingsForColumn(COLUMNHEADER.FORMNAME, COLUMNSETTING.FILTER, FormName);
		TestValidation.IsTrue(applyfilter1, "Applied Filter on" + COLUMNHEADER.FORMNAME,
				"Failed to apply filter on" + COLUMNHEADER.FORMNAME);

		boolean verifyversion = fbp.verifyGridValuesForColumn(COLUMNHEADER.VERSION, expected_version1.toString());
		TestValidation.IsTrue(verifyversion, "Verified version" + expected_version1.toString(),
				"Failed to verifiy version" + expected_version1.toString());
		mainPage.Sync();

		// Version Check

		homePage.SearchForm(FormName);
		ControlActions_MobileApp.swipeScreen("DOWN");
		String mobVersionAfterUpdate = UpdatedFormVersionPageObj.FirstVersion();
		Boolean CompareVersion = UpdatedFormVersionPageObj.CompareVersion(mobVersionAfterUpdate,
				expected_version1.toString());
		TestValidation.IsTrue(CompareVersion, "Version is updated", "version not updated");

	}

	@AfterMethod(alwaysRun = true)
	public void closeAppium() throws InterruptedException {

		appiumDriver.closeApp();
	}

	@AfterClass(alwaysRun = true)
	public void closeBrowser() throws InterruptedException {
		driver.quit();

	}

}
