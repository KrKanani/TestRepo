package com.project.safetychain.mobileapp.testcases;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;
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
import com.project.safetychain.api.models.support.Support.TaskParams;
import com.project.safetychain.api.models.support.TCG_FormCreation_Wrapper;
import com.project.safetychain.api.models.support.TCG_TaskCreation_Wrapper;
import com.project.safetychain.mobileapp.pageobjects.DisableFormFeaturePage;
import com.project.safetychain.mobileapp.pageobjects.HomePage;
import com.project.safetychain.mobileapp.pageobjects.LoginPage;
import com.project.safetychain.mobileapp.pageobjects.REG_SubmissionsPage;
import com.project.safetychain.mobileapp.pageobjects.SavedPage;
import com.project.safetychain.webapp.pageobjects.CommonPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserDocumentsPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserFormsPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage;
import com.project.safetychain.webapp.pageobjects.FormsManagerPage;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FormDesignParams;
import com.project.safetychain.webapp.pageobjects.FormsManagerPage.FM_FIELDS;
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

public class TCG_REG_DisableFormFeature extends TestBase {
	ControlActions_MobileApp mobControlActions;
	ApiUtils apiUtils = null;
	FormDesignerPage formDesignerPage;
	ControlActions controlActions;
	FSQABrowserPage fbp;
	FSQABrowserFormsPage fbForms;
	FSQABrowserDocumentsPage fbDocuments;
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
	FormsManagerPage fm;

	public static String locationCategoryValue;
	public static String locationInstanceValue1;
	public static String locationInstanceValue2;
	public static String resourceCategoryValue;
	public static String resourceInstanceValue1;
	public static String resourceInstanceValue2;
	public static String chkFreeTxt, ParaTxt, chkSelectOne, SelectMultiple, Numeric, chkDate, chkTime, chkDateTime;

	public static String formName = "API_Quest_15.10_17.19.19";
	public static String resourceName = "RInst1_15.10_13.47.04";
	public static String LocationName = "LInst1_15.10_13.47.04";
	String formtype = "Check";
	String modifiedby;
	public static String FormName;
	public static String TaskName;
	public static String WGName;

	public static final String fresult = "com.safetychain.SCM.M2:id/resultView_num";
	@AndroidFindBy(id = fresult)
	public WebElement NTxt;

	@BeforeClass(alwaysRun = true)
	public void groupInit() {
		try {
			FormName = CommonMethods.dynamicText("API_QuestForm");
			FormName = CommonMethods.dynamicText("API_Form");
			TaskName = CommonMethods.dynamicText("API_Task");
			WGName = CommonMethods.dynamicText("API_WG");
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
			SelectMultiple = "MultiSelect";

			TCG_FormCreation_Wrapper formCreationWrapper = new TCG_FormCreation_Wrapper();
			TCG_TaskCreation_Wrapper taskCreationWrapper = new TCG_TaskCreation_Wrapper();

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
			numericField.AllowAttachments = "true";
			numericField.RepeatField = "true";
			numericField.ShowMinMax = "true";
			numericField.ShowTarget = "true";

			FormFieldParams dateField1 = new FormFieldParams();
			dateField1.DPTFieldType = DPT_FIELD_TYPES.DATE;
			dateField1.Name = "Date1";
			dateField1.RepeatField = "true";

			FormFieldParams dateField2 = new FormFieldParams();
			dateField2.DPTFieldType = DPT_FIELD_TYPES.DATE;
			dateField2.Name = "Date2";
			dateField2.RepeatField = "true";

			FormFieldParams Identifier = new FormFieldParams();
			Identifier.DPTFieldType = DPT_FIELD_TYPES.IDENTIFIER;
			Identifier.Name = "Identifier1";
			Identifier.identifierId = IDENTIFIER_TYPES.SELECT_ONE;
			Identifier.IdentifierOption = Arrays.asList("1", "2", "3", "4");
			Identifier.RepeatField = "true";

			FormFieldParams selectOneField = new FormFieldParams();
			selectOneField.DPTFieldType = DPT_FIELD_TYPES.SELECT_ONE;
			selectOneField.Name = chkSelectOne;
			selectOneField.SelectOptions = Arrays.asList("a", "b", "c", "d");
			selectOneField.RepeatField = "true";

			FormFieldParams Paragraph = new FormFieldParams();
			Paragraph.DPTFieldType = DPT_FIELD_TYPES.PARAGRAPH_TEXT;
			Paragraph.Name = "ParaGraph1";
			Paragraph.RepeatField = "true";

			FormFieldParams SingleLineText = new FormFieldParams();
			SingleLineText.DPTFieldType = DPT_FIELD_TYPES.SINGLE_LINE_TEXT;
			SingleLineText.Name = "SingleLineText1";
			SingleLineText.RepeatField = "true";

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
			Identifier2.RepeatField = "true";
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
			// Identifier2.RepeatField = "true";

			fieldGroup2.formFieldParams = Arrays.asList(Identifier3);

			// Section ---> FieldGroup 1--------->Numeric
			// ---> FieldGroup 2--------->Numeric,Select Multiple

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

			// Section---> T1,T2 FG5--(DT1,DT2) ,N3,N4,n5

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
			fieldGroup5.Name = "FieldGroup4";
			FormFieldParams DateTimeField1 = new FormFieldParams();
			DateTimeField1.DPTFieldType = DPT_FIELD_TYPES.DATE_TIME;
			DateTimeField1.Name = "DateTime1";

			FormFieldParams DateTimeField2 = new FormFieldParams();
			DateTimeField2.DPTFieldType = DPT_FIELD_TYPES.DATE_TIME;
			DateTimeField2.Name = "DateTime2";

			fieldGroup5.formFieldParams = Arrays.asList(DateTimeField1, DateTimeField2);

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
			Section2.FieldGroupParams = Arrays.asList(fieldGroup5);
			Section2.formFieldParams = Arrays.asList(TimeField1, TimeField2, Numeric3, Numeric4, Numeric5);

			FormFieldParams AGG1 = new FormFieldParams();
			AGG1.DPTFieldType = DPT_FIELD_TYPES.AGGREGATE;
			AGG1.Name = "AGG1-SUM";
			AGG1.SelectedFunction = AGGREGATE_FUNC_TYPES.SUM;
			AGG1.SelectedSource = "Numeric4";

			FormFieldParams AGG2 = new FormFieldParams();
			AGG2.DPTFieldType = DPT_FIELD_TYPES.AGGREGATE;
			AGG2.Name = "AGG1-CountRange";
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
			fp.type = DPT_FORM_TYPES.QUESTIONNAIRE;
			fp.ResourceType = RESOURCE_TYPES.CUSTOMERS;

			fp.ResourceCategoryNm = resourceCategoryValue;
			fp.ResourceInstanceNm = resourceInstanceValue1;

			fp.formElements = Arrays.asList(numericField, dateField1, dateField2, Identifier, selectOneField, AGG1,
					AGG2, Paragraph, SingleLineText);

			fp.SectionElements = Arrays.asList(fieldGroup, fieldGroup2, Section1, Section2);

			logInfo("fp.formElements = " + fp.formElements);

			HashMap<String, List<String>> resourceCatMap = new HashMap<String, List<String>>();
			resourceCatMap.put(resourceCategoryValue, Arrays.asList(resourceInstanceValue1, resourceInstanceValue2));
			// fp.CustomerResources = resourceCatMap;
			fp.CustomerResources = resourceCatMap;

			HashMap<String, List<String>> LocationMap = new HashMap<String, List<String>>();
			LocationMap.put(locationCategoryValue, Arrays.asList(locationInstanceValue1, locationInstanceValue2));
			List<String> userList = Arrays.asList(mobileAppUsername02, mobileAppUsername);

			HashMap<String, String> locResMap = formCreationWrapper.Resource_Location_CreationWrapper(resourceCatMap,
					LocationMap, true, userList, fp.ResourceType);

			String locInstId = locResMap.get(locationInstanceValue1);
			String ResInstId = locResMap.get(resourceInstanceValue1);
			formCreationWrapper.API_Wrapper_Forms(fp);

			// Task Creation

			String date = ControlActions_MobileApp.AddDaystoToddaysDateInGivenFormat("MM/dd/YYYY HH:mm", 2);
			TaskParams tp = new TaskParams();
			tp.FormId = formId;
			tp.DueBy = date;
			tp.FormName = FormName;
			tp.LocationInstanceNm = locationInstanceValue1;
			tp.ResourceInstanceNm = resourceInstanceValue1;
			tp.TaskName = TaskName;
			tp.WorkGroupName = WGName;
			tp.LocationInstanceId = locInstId;
			tp.ResourceInstanceId = ResInstId;
			tp.UserName = mobileAppUsername;

			taskCreationWrapper.create_Link_workGroup_Wrapper(tp);

			// Multiple task Creation

			List<String> dueByLst = new ArrayList<String>();

			dueByLst.add(ControlActions_MobileApp.AddDaystoToddaysDateInGivenFormat("MM/dd/YYYY HH:mm", 0));

			for (int i = 0; i < dueByLst.size(); i++) {
				tp.DueBy = dueByLst.get(i);
				taskCreationWrapper.create_Task_Wrapper(tp);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test(description = "Verify Forms are not displayed on Form screen,Favouries tab,Saved Forms if the Form is disable from the Web End")
	public void TestCase_37283_37284_37285() throws Exception {
		appiumDriver = launchMobileApp();
		DisableFormFeaturePage DisableFormFeaturePageObj = new DisableFormFeaturePage(appiumDriver);

		REG_SubmissionsPage SavedPageObj = new REG_SubmissionsPage(appiumDriver);

		LoginPage loginPage = new LoginPage(appiumDriver);
		HomePage homePage = new HomePage(appiumDriver);
		boolean login = loginPage.Login(mobileAppUsername, mobileAppPassword);
		TestValidation.IsTrue(login, "logged into the application with User " + mobileAppUsername, "Failed to log in");

		mobControlActions = new ControlActions_MobileApp(appiumDriver);
		// Form details

		boolean searchForm = homePage.SearchForm(FormName);
		TestValidation.IsTrue(searchForm, "Form Searched Successfully", "Failed to Search Form");

		Boolean Fav = DisableFormFeaturePageObj.favoritesStarBtn(); // PK
		logInfo("added to Fav");// PK
		TestValidation.IsTrue(Fav, "Able to add in fav", "Unable to add in fav");
		Boolean clickForm = homePage.ClickForm(FormName);
		TestValidation.IsTrue(clickForm, "Clicked on Searched form Successfully", "Failed to click form");

		SavedPageObj.SearchResource(locationInstanceValue1);
		SavedPageObj.SearchResource(resourceInstanceValue1);
		SavedPageObj.enterFieldValue(SavedPageObj.NumericTxt, "8");

		ControlActions_MobileApp.AddDaystoToddaysDate(0);

		// Saving Form

		boolean saveForm = SavedPageObj.saveForm();
		TestValidation.IsTrue(saveForm, "Form Saved Successfully", "Failed to Save Form ");

		System.out.println("Form is saved");

		boolean clickSaveMenu = homePage.ClickSubMenu(homePage.saveSubMenu);
		TestValidation.IsTrue(clickSaveMenu, "Clicked on Save subMenu Successfully", "Failed to click Save subMenu");
		boolean searchFormSaved = homePage.SearchForm(FormName);
		TestValidation.IsTrue(searchFormSaved, "Form Searched Successfully on saved tab",
				"Failed to Search Form on saved tab");

		// Favourites Tab
		boolean clickfavSubMenu = homePage.ClickSubMenu(homePage.favouritesSubMenu);
		TestValidation.IsTrue(clickfavSubMenu, "Clicked on favorites subMenu Successfully",
				"Failed to click favorites subMenu");
		boolean searchFormFav1 = homePage.SearchForm(FormName);
		TestValidation.IsTrue(searchFormFav1, "Form Searched Successfully on Favorites tab",
				"Failed to Search Form in favorites tab");

		// Submit Form

		boolean clickformSubMenu1 = homePage.ClickSubMenu(homePage.formsSubMenu);
		TestValidation.IsTrue(clickformSubMenu1, "Clicked on form subMenu Successfully",
				"Failed to click form subMenu");

		boolean searchFormSub = homePage.SearchForm(FormName);
		TestValidation.IsTrue(searchFormSub, "Form Searched Successfully", "Failed to Search Form from Forms subMenu");

		Boolean clickForm1 = homePage.ClickForm(FormName);
		TestValidation.IsTrue(clickForm1, "Clicked on Searched form Successfully", "Failed to click form");

		SavedPageObj.SearchResource(locationInstanceValue1);
		SavedPageObj.SearchResource(resourceInstanceValue1);

		Boolean fillFormDetails = SavedPageObj.QuestionareForm_fillFormDetailsWithAttachment();
		TestValidation.IsTrue(fillFormDetails, "Form Details filled successfully", "Failed to fill form details");

		SavedPageObj.submitForm();
		System.out.println("Form is submitted");

		// Submissions Tab
		boolean clickformSubMenuTab = homePage.ClickSubMenu(homePage.submissionsSubMenu);
		TestValidation.IsTrue(clickformSubMenuTab, "Clicked on submission subMenu Successfully",
				"Failed to click submission subMenu");

		boolean searchFormSubTab = homePage.SearchForm(FormName);
		TestValidation.IsTrue(searchFormSubTab, "Form Searched Successfully in Submissions",
				"Failed to Search Form in Submissions");

		// Forms Tab
		boolean clickformsMenuTab = homePage.ClickSubMenu(homePage.formsSubMenu);
		TestValidation.IsTrue(clickformsMenuTab, "Clicked on form subMenu Successfully",
				"Failed to click form subMenu");

		boolean searchForm2 = homePage.SearchForm(FormName);
		TestValidation.IsTrue(searchForm2, "Form Searched Successfully", "Failed to Search Form from Forms subMenu");

		// Inbox Tab
		boolean clickInboxTab = homePage.ClickSubMenu(homePage.inboxSubMenu);
		TestValidation.IsTrue(clickInboxTab, "Clicked on Inbox subMenu Successfully", "Failed to click form subMenu");

		boolean searchTask = homePage.SearchForm(TaskName);
		TestValidation.IsTrue(searchTask, "Task Searched Successfully", "Failed to Search task from Inbox subMenu");

		// Disable code here

		controlActions.getUrl(applicationUrl);

		hp = lp.performLogin(adminUsername, adminPassword);
		if (hp.error) {
			TCGFailureMsg = "Could NOT login to application";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);

		}

		fm = hp.clickFormsManagerMenu();
		boolean searchingForm = fm.searchForm(FormName);
		TestValidation.IsTrue(searchingForm, "OPENED the search form - " + FormName,
				"Failed to open searched form - " + FormName);

		String formStatus = fm.getFormManagerDetails(FM_FIELDS.STATUS);
		TestValidation.Equals(true, true, "For Form Manager category,  Selected Form is fetched and displayed",
				"Failed to navigate to Admin Tools > Form Manager tab");
		logInfo("Status of formstatus," + formStatus);

		boolean selectForm = fm.selectForm(FormName);
		TestValidation.IsTrue(selectForm, "Form has Selected- " + FormName, "Failed to select Form- " + FormName);

		boolean isformStatus = fm.performEnableDisableAction(formStatus);
		TestValidation.IsTrue(isformStatus, "Form Status has Changed- " + FormName,
				"Failed to change Form Status- " + FormName);

		boolean clickformsMenuTab1 = homePage.ClickSubMenu(homePage.formsSubMenu);
		TestValidation.IsTrue(clickformsMenuTab1, "Clicked on forms subMenu Successfully",
				"Failed to click forms subMenu");

		ControlActions_MobileApp.swipeScreen("DOWN");
		boolean isDispalyedForms1 = homePage.NoResultsFoundCheck(FormName);

		if (isDispalyedForms1) {

			logInfo("Validation Passed as form is not visible on Forms after Disable in All forms tab");
		} else {
			logInfo("Validation Failed for All forms Tab");
		}

		TestValidation.IsTrue(isDispalyedForms1,
				"Validation Passed as form is not visible on Forms after Disable in All forms tab",
				"Validation Failed for All forms Tab");

		// Favorites Tab

		boolean clickfavMenuTab = homePage.ClickSubMenu(homePage.favouritesSubMenu);
		TestValidation.IsTrue(clickfavMenuTab, "Clicked on favorites subMenu Successfully",
				"Failed to click favorites subMenu");

		// ControlActions_MobileApp.swipeScreen("DOWN");
		boolean isDispalyedFavourites = homePage.NoResultsFoundCheck(FormName);
		if (isDispalyedFavourites) {
			logInfo("Validation Passed as form is not visible on Favourites after Disable");
		} else {
			logInfo("Validation Failed for Favourites Tab");
		}

		TestValidation.IsTrue(isDispalyedFavourites,
				"Validation Passed as form is not visible on Favourites after Disable",
				"Validation Failed for Favourites tab");

		// Saved Tab

		boolean clickSavedformsMenuTab = homePage.ClickSubMenu(homePage.saveSubMenu);
		TestValidation.IsTrue(clickSavedformsMenuTab, "Clicked on saved form subMenu Successfully",
				"Failed to click saved form subMenu");

		// ControlActions_MobileApp.swipeScreen("DOWN");
		boolean isDispalyedForms = homePage.NoResultsFoundCheck(FormName);
		if (isDispalyedForms) {
			logInfo("Validation Passed as form is not visible on Saved after Disable");
		} else {
			logInfo("Validation Failed for saved Tab");
		}

		TestValidation.IsTrue(isDispalyedForms, "Validation Passed as form is not visible on Saved after Disable",
				"Validation Failed for Saved tab");

		// Submissions Tab
		boolean clickSubformsMenuTab = homePage.ClickSubMenu(homePage.submissionsSubMenu);
		TestValidation.IsTrue(clickSubformsMenuTab, "Clicked on Submissions subMenu Successfully",
				"Failed to click Submissions subMenu");
//		ControlActions_MobileApp.swipeScreen("DOWN");

		boolean isDispalyed = homePage.NoResultsFoundCheck(FormName);
		if (isDispalyed) {
			logInfo("Validation Passed as form is not visible on Submissions after Disable");
		} else {
			logInfo("Validation Failed for Submissions Tab");
		}

		TestValidation.IsTrue(isDispalyed, "Validation Passed as form is not visible on Submissions after Disable",
				"Validation Failed for Submissions tab");

		// Inbox Tab
		boolean clickInboxMenuTab = homePage.ClickSubMenu(homePage.inboxSubMenu);
		TestValidation.IsTrue(clickInboxMenuTab, "Clicked on Inbox subMenu Successfully",
				"Failed to click Inbox subMenu");
//		ControlActions_MobileApp.swipeScreen("DOWN");

		boolean isDispalyedInbox = homePage.NoResultsFoundCheck(TaskName);
		if (isDispalyedInbox) {
			logInfo("Validation Passed as task is not visible on Inbox after Disable");
		} else {
			logInfo("Validation Failed for Inbox Tab");
		}

		TestValidation.IsTrue(isDispalyed, "Validation Passed as task is not visible on Inbox after Disable",
				"Validation Failed for Inbox tab");

	}

	@Test(description = "Verify Form is displayed when user Changes the Form Status from Disable to Enable after refresh")
	public void TestCase_37286() throws Exception {
		fm = hp.clickFormsManagerMenu();
		boolean searchingForm1 = fm.searchForm(FormName);
		TestValidation.IsTrue(searchingForm1, "OPENED the search form - " + FormName,
				"Failed to open searched form - " + FormName);

		String formStatus = fm.getFormManagerDetails(FM_FIELDS.STATUS);
		TestValidation.Equals(true, true, "For Form Manager category,  Selected Form is fetched and displayed",
				"Failed to navigate to Admin Tools > Form Manager tab");
		logInfo("Status of formstatus," + formStatus);

		fm.selectForm(FormName);
		fm.performEnableDisableAction(formStatus);
		logInfo("Form is status is changed");

		appiumDriver = launchMobileApp();
		LoginPage loginPage = new LoginPage(appiumDriver);
		HomePage homePage = new HomePage(appiumDriver);
		boolean login = loginPage.Login(mobileAppUsername, mobileAppPassword);
		TestValidation.IsTrue(login, "logged into the application with User " + mobileAppUsername, "Failed to log in");

		mobControlActions = new ControlActions_MobileApp(appiumDriver);

		// Forms Tab
		boolean clickformsMenuTab = homePage.ClickSubMenu(homePage.formsSubMenu);
		TestValidation.IsTrue(clickformsMenuTab, "Clicked on form subMenu Successfully",
				"Failed to click form subMenu");

		boolean searchForm2 = homePage.SearchForm(FormName);
		TestValidation.IsTrue(searchForm2, "Form Searched Successfully", "Failed to Search Form from Forms subMenu");

		// Favorites Tab

		boolean clickfavMenuTab = homePage.ClickSubMenu(homePage.favouritesSubMenu);
		TestValidation.IsTrue(clickfavMenuTab, "Clicked on favorites subMenu Successfully",
				"Failed to click favorites subMenu");

		boolean searchFormFav = homePage.SearchForm(FormName);
		TestValidation.IsTrue(searchFormFav, "Form Searched Successfully",
				"Failed to Search Form from Favourites subMenu");

		// Saved Tab

		boolean clickSavedformsMenuTab = homePage.ClickSubMenu(homePage.saveSubMenu);
		TestValidation.IsTrue(clickSavedformsMenuTab, "Clicked on saved form subMenu Successfully",
				"Failed to click saved form subMenu");

		boolean searchFormSave = homePage.SearchForm(FormName);
		TestValidation.IsTrue(searchFormSave, "Form Searched Successfully on Saved Tab",
				"Failed to Search Form from Saved subMenu");

		// Submissions Tab
		boolean clickSubformsMenuTab = homePage.ClickSubMenu(homePage.submissionsSubMenu);
		TestValidation.IsTrue(clickSubformsMenuTab, "Clicked on Submissions subMenu Successfully",
				"Failed to click Submissions subMenu");

		boolean searchFormSub = homePage.SearchForm(FormName);
		TestValidation.IsTrue(searchFormSub, "Form Searched Successfully on Submissions Tab",
				"Failed to Search Form from Submissions subMenu");

	}

	@Test(description = "Verify Submit and Repeat form once it is saved and Disabled from web")
	public void TestCase_37288() throws Exception {
		appiumDriver = launchMobileApp();
		REG_SubmissionsPage SavedPageObj = new REG_SubmissionsPage(appiumDriver);

		LoginPage loginPage = new LoginPage(appiumDriver);
		HomePage homePage = new HomePage(appiumDriver);
		boolean login = loginPage.Login(mobileAppUsername, mobileAppPassword);
		TestValidation.IsTrue(login, "logged into the application with User " + mobileAppUsername, "Failed to log in");

		mobControlActions = new ControlActions_MobileApp(appiumDriver);
		// Form details

		boolean searchForm = homePage.SearchForm(FormName);
		TestValidation.IsTrue(searchForm, "Form Searched Successfully", "Failed to Search Form");

		Boolean clickForm1 = homePage.ClickForm(FormName);
		TestValidation.IsTrue(clickForm1, "Clicked on Searched form Successfully", "Failed to click form");

		SavedPageObj.SearchResource(locationInstanceValue1);
		SavedPageObj.SearchResource(resourceInstanceValue1);

		Boolean fillFormDetails = SavedPageObj.QuestionareForm_fillFormDetailsWithAttachment();
		TestValidation.IsTrue(fillFormDetails, "Form Details filled successfully", "Failed to fill form details");

		// SavedPageObj.submitForm();

		SavedPageObj.clickSubmitRepeatForm();
		SavedPageObj.clickPopupOkBtn();
		SavedPageObj.saveForm();

		// Submissions Tab
		boolean clickformSubMenuTab = homePage.ClickSubMenu(homePage.submissionsSubMenu);
		TestValidation.IsTrue(clickformSubMenuTab, "Clicked on submission subMenu Successfully",
				"Failed to click submission subMenu");

		boolean searchFormSubTab = homePage.SearchForm(FormName);
		TestValidation.IsTrue(searchFormSubTab, "Form Searched Successfully in Submissions",
				"Failed to Search Form in Submissions");

		// Saved Tab

		boolean clickSavedformsMenuTab = homePage.ClickSubMenu(homePage.saveSubMenu);
		TestValidation.IsTrue(clickSavedformsMenuTab, "Clicked on saved form subMenu Successfully",
				"Failed to click saved form subMenu");

		boolean searchFormSave = homePage.SearchForm(FormName);
		TestValidation.IsTrue(searchFormSave, "Form Searched Successfully on Saved Tab",
				"Failed to Search Form from Saved subMenu");

		// Forms Tab
		boolean clickformsMenuTab = homePage.ClickSubMenu(homePage.formsSubMenu);
		TestValidation.IsTrue(clickformsMenuTab, "Clicked on form subMenu Successfully",
				"Failed to click form subMenu");

		boolean searchForm2 = homePage.SearchForm(FormName);
		TestValidation.IsTrue(searchForm2, "Form Searched Successfully", "Failed to Search Form from Forms subMenu");

		// Disable code here
		driver = launchbrowser();
		controlActions.getUrl(applicationUrl);

		hp = lp.performLogin(adminUsername, adminPassword);
		if (hp.error) {
			TCGFailureMsg = "Could NOT login to application";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);

		}
		// hp = new com.project.safetychain.webapp.pageobjects.HomePage(driver);
		fm = hp.clickFormsManagerMenu();
		boolean searchingForm = fm.searchForm(FormName);
		TestValidation.IsTrue(searchingForm, "OPENED the search form - " + FormName,
				"Failed to open searched form - " + FormName);

		String formStatus = fm.getFormManagerDetails(FM_FIELDS.STATUS);
		TestValidation.Equals(true, true, "For Form Manager category,  Selected Form is fetched and displayed",
				"Failed to navigate to Admin Tools > Form Manager tab");
		logInfo("Status of formstatus," + formStatus);

		boolean selectForm = fm.selectForm(FormName);
		TestValidation.IsTrue(selectForm, "Form has Selected- " + FormName, "Failed to select Form- " + FormName);

		boolean isformStatus = fm.performEnableDisableAction(formStatus);
		TestValidation.IsTrue(isformStatus, "Form Status has Changed- " + FormName,
				"Failed to change Form Status- " + FormName);

		boolean clickformsMenuTab1 = homePage.ClickSubMenu(homePage.formsSubMenu);
		TestValidation.IsTrue(clickformsMenuTab1, "Clicked on forms subMenu Successfully",
				"Failed to click forms subMenu");

		ControlActions_MobileApp.swipeScreen("DOWN");
		boolean isDispalyedForms1 = homePage.NoResultsFoundCheck(FormName);

		if (isDispalyedForms1) {

			logInfo("Validation Passed as form is not visible on Forms after Disable in All forms tab");
		} else {
			logInfo("Validation Failed for All forms Tab");
		}

		TestValidation.IsTrue(isDispalyedForms1,
				"Validation Passed as form is not visible on Forms after Disable in All forms tab",
				"Validation Failed for All forms Tab");

		// Saved Tab

		boolean clickSavedformsMenu = homePage.ClickSubMenu(homePage.saveSubMenu);
		TestValidation.IsTrue(clickSavedformsMenu, "Clicked on saved form subMenu Successfully",
				"Failed to click saved form subMenu");
		boolean isDispalyedForms = homePage.NoResultsFoundCheck(FormName);
		if (isDispalyedForms) {
			logInfo("Validation Passed as form is not visible on Saved after Disable");
		} else {
			logInfo("Validation Failed for saved Tab");
		}

		TestValidation.IsTrue(isDispalyedForms, "Validation Passed as form is not visible on Saved after Disable",
				"Validation Failed for Saved tab");

		// Submissions Tab
		boolean clickSubformsMenuTab = homePage.ClickSubMenu(homePage.submissionsSubMenu);
		TestValidation.IsTrue(clickSubformsMenuTab, "Clicked on Submissions subMenu Successfully",
				"Failed to click Submissions subMenu");
		ControlActions_MobileApp.swipeScreen("DOWN");

		boolean isDispalyed = homePage.NoResultsFoundCheck(FormName);
		if (isDispalyed) {
			logInfo("Validation Passed as form is not visible on Submissions after Disable");
		} else {
			logInfo("Validation Failed for Submissions Tab");
		}

		TestValidation.IsTrue(isDispalyed, "Validation Passed as form is not visible on Submissions after Disable",
				"Validation Failed for Submissions tab");

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
