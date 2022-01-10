package com.project.safetychain.pcapp.testcases;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.utilities.ApiUtils;
import com.project.utilities.CommonMethods;
import com.project.utilities.ControlActions;
import com.project.utilities.ControlActions_PCApp;
import com.project.utilities.DateTime;
import com.project.safetychain.api.models.support.TCG_FormCreation_Wrapper;
import com.project.safetychain.api.models.support.TCG_UserFlows_Wrapper;
import com.project.safetychain.api.models.support.Support.DPT_FIELD_TYPES;
import com.project.safetychain.api.models.support.Support.DPT_FORM_TYPES;
import com.project.safetychain.api.models.support.Support.FormFieldParams;
import com.project.safetychain.api.models.support.Support.FormParams;
import com.project.safetychain.api.models.support.Support.IDENTIFIER_TYPES;
import com.project.safetychain.api.models.support.Support.UserParams;
import com.project.safetychain.pcapp.pageobjects.CommonScreen;
import com.project.safetychain.pcapp.pageobjects.FavoritesScreen;
import com.project.safetychain.pcapp.pageobjects.FormViewScreen;
import com.project.safetychain.pcapp.pageobjects.FormsScreen;
import com.project.safetychain.pcapp.pageobjects.FormsScreen.FormDetails;
import com.project.safetychain.pcapp.pageobjects.LoginScreen;
import com.project.safetychain.pcapp.pageobjects.SavedScreen;
import com.project.safetychain.pcapp.pageobjects.SettingsScreen;
import com.project.safetychain.pcapp.pageobjects.FormViewScreen.FormDetailsPC;
import com.project.safetychain.webapp.pageobjects.CommonPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage;
import com.project.safetychain.webapp.pageobjects.HomePage;
import com.project.safetychain.webapp.pageobjects.LoginPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.COLUMNHEADER;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.COLUMNSETTING;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.FSQATAB;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FORMRESOURCETYPES;

public class TCG_REG_Forms_CheckFormFlows extends TestBase{

	ApiUtils apiUtils = null;

	FormsScreen forms;
	FormViewScreen formview; 
	SavedScreen savedScreen;

	DateTime datetime;

	FormDetailsPC formDetailsPC;

	ControlActions controlActionsWeb;
	CommonPage mainPage;
	LoginPage webLoginPage;
	HomePage webHomePage;
	FSQABrowserPage webfsqaFormsPage;
	FormDesignerPage webformDesignerPage;


	public static String locationCategoryValue1;
	public static String locationInstanceValue1;
	public static String resourceCategoryType1 = FORMRESOURCETYPES.ITEMS;
	public static String resourceInstanceValue1;
	public static String resourceCategoryValue1;
	String locInstId, resInstId;

	public static String checkFormName1;
	String NumericFieldName1 = "Num 1";
	String FreeTextIdentifierField = "IDN FreeText", 
			FreeTextInputMaskIdentifierField = "IDN InputMask", 
			SelectOneIdentifierField = "IDN SelectOne";

	public static String newUserName, newUserPassword, newUserNewPassword;

	ControlActions_PCApp controlActionsPC;
	CommonScreen commonScreen;

	LoginScreen loginScreen;
	FormsScreen formsTab;

	int formCount = 1;

	@BeforeClass
	public void groupInit() {

		apiUtils = new ApiUtils();

		TCG_FormCreation_Wrapper formCreationWrapper = new TCG_FormCreation_Wrapper();
		TCG_UserFlows_Wrapper userCreationWrapper = new TCG_UserFlows_Wrapper();

		datetime = new DateTime();

		locationCategoryValue1 = CommonMethods.dynamicText("PC_LCat");
		locationInstanceValue1 = CommonMethods.dynamicText("PC_LInst1");
		resourceCategoryValue1 = CommonMethods.dynamicText("PC_RCat");
		resourceInstanceValue1 = CommonMethods.dynamicText("PC_RInst1");

		checkFormName1 = CommonMethods.dynamicText("PC_FormFlowForm1");

		newUserName =  CommonMethods.dynamicText("PC_FormsFlowUser1");
		newUserPassword = GenericPassword;
		newUserNewPassword = GenericNewPassword;

		HashMap<String, List<String>> resourceCatMap = new HashMap<String, List<String>>();
		resourceCatMap.put(resourceCategoryValue1, Arrays.asList(resourceInstanceValue1));

		HashMap<String, List<String>> LocationMap = new HashMap<String, List<String>>();
		LocationMap.put(locationCategoryValue1, Arrays.asList(locationInstanceValue1));

		List<String> userList = Arrays.asList(pcAppUsername);

		HashMap<String, String> locResMap = formCreationWrapper.Resource_Location_CreationWrapper(resourceCatMap,
				LocationMap, true, userList, resourceCategoryType1);

		locInstId = locResMap.get(locationInstanceValue1);
		resInstId = locResMap.get(resourceInstanceValue1);

		FormFieldParams NumericField1 = new FormFieldParams();
		NumericField1.DPTFieldType = DPT_FIELD_TYPES.NUMERIC;
		NumericField1.Name = NumericFieldName1;
		NumericField1.AllowAttachments = "true";

		FormFieldParams IdentifierField1 = new FormFieldParams();
		IdentifierField1.DPTFieldType = DPT_FIELD_TYPES.IDENTIFIER;
		IdentifierField1.identifierId = IDENTIFIER_TYPES.FREE_TEXT;
		IdentifierField1.Name = FreeTextIdentifierField;

		FormFieldParams IdentifierField2 = new FormFieldParams();
		IdentifierField2.DPTFieldType = DPT_FIELD_TYPES.IDENTIFIER;
		IdentifierField2.identifierId = IDENTIFIER_TYPES.FREE_TEXT;
		IdentifierField2.InputMask = "0000";
		IdentifierField2.Name = FreeTextInputMaskIdentifierField;

		FormFieldParams IdentifierField3 = new FormFieldParams();
		IdentifierField3.DPTFieldType = DPT_FIELD_TYPES.IDENTIFIER;
		IdentifierField3.identifierId = IDENTIFIER_TYPES.SELECT_ONE;
		IdentifierField3.IdentifierOption = Arrays.asList("IDN Option 1", "IDN Option 2");
		IdentifierField3.Name = SelectOneIdentifierField;

		String formId1 = apiUtils.getUUID();

		FormParams fp1 = new FormParams();
		fp1.FormId = formId1;
		fp1.type = DPT_FORM_TYPES.CHECK;
		fp1.ResourceType = resourceCategoryType1;
		fp1.ResourceCategoryNm = resourceCategoryValue1;
		fp1.ItemsResources = resourceCatMap;
		fp1.FormName = checkFormName1;
		fp1.formElements = Arrays.asList(NumericField1, IdentifierField1, IdentifierField2, IdentifierField3);

		boolean formcreation = false;

		try {
			formCreationWrapper.API_Wrapper_Forms(fp1);
			formcreation = true;
			logInfo("'"+checkFormName1+"' Form is created");
		} catch (InterruptedException e) {
			logError("Error while '"+checkFormName1+"' form creation");
			formcreation = false;
		}

		TestValidation.IsTrue(formcreation, 
				"CREATED form - " + checkFormName1, 
				"Could NOT create form - " + checkFormName1);

		UserParams userDetails = new UserParams();
		userDetails.Username = newUserName;
		userDetails.ClearPassword = newUserPassword;
		userDetails.FirstName = "UWP Forms";
		userDetails.LastName = "Auto User 1";
		userDetails.Email = "formsflowuser.pcapp@scauto.com";
		userDetails.TimeZone = "U.S. Pacific";
		userDetails.LocationCat = locationCategoryValue1;
		userDetails.LocationNmIds = Arrays.asList(locationInstanceValue1);
		userDetails.Roles = Arrays.asList("SuperAdmin");
		userDetails.EmployeeId = "Test";
		userDetails.Phone = "1234567891";
		boolean userCreation = false;

		try {
			userCreationWrapper.createUser_Wrapper(userDetails);
			userCreation = true;
			logInfo("'"+newUserName+"' User is created");
		} catch (InterruptedException e) {
			logError("Error while '"+newUserName+"' user creation");
			userCreation = false;
		}
		TestValidation.IsTrue(userCreation, 
				"CREATED User - " + newUserName, 
				"Could NOT create user - " + newUserName);

		try {
			driver = launchbrowser();
			controlActionsWeb = new ControlActions(driver);
			webLoginPage = new LoginPage(driver);

			controlActionsWeb.getUrl(pcAppTenantURL);
			webLoginPage.performLogin(newUserName, newUserPassword, newUserNewPassword);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		PCAppDriver = launchPCApp();
		controlActionsPC = new ControlActions_PCApp(PCAppDriver);
		commonScreen = new CommonScreen(PCAppDriver);

		formsTab = new FormsScreen(PCAppDriver);

		loginScreen = new LoginScreen(PCAppDriver);

		commonScreen = loginScreen.Login(pcAppTenantname, newUserName, newUserNewPassword);
		if(commonScreen.error) {
			TCGFailureMsg = "COULD not login to the application";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);	
		}

	}

	@Test (description="Forms || Verify that the all the enabled form should be visible in Forms")
	public void TestCase_30267() {

		FormsScreen selectFormsTab  = commonScreen.selectForms();
		TestValidation.IsFalse(selectFormsTab.error,
				"OPENED Forms Tab", 
				"COULD not open Forms Tab");

		boolean verifyFormAvailability = selectFormsTab.verifyFormName(checkFormName1);
		TestValidation.IsTrue(verifyFormAvailability, 
				"FOUND enabled form - "+checkFormName1, 
				"FAILED verify enabled form availability  - "+checkFormName1);

	}

	@Test (description="Forms || Verify the forms details")
	public void TestCase_30268() {

		FormDetails formDetails = new FormDetails();
		formDetails.FormName = checkFormName1;
		formDetails.Version = "1.0";

		FormsScreen selectFormsTab  = commonScreen.selectForms();
		TestValidation.IsFalse(selectFormsTab.error,
				"OPENED Forms Tab", 
				"COULD not open Forms Tab");

		boolean verifyFormDetails = selectFormsTab.verifyFormDetails(formDetails);
		TestValidation.IsTrue(verifyFormDetails, 
				"VERIFIED form detail for form - "+checkFormName1, 
				"FAILED verify form details for form  - "+checkFormName1);

	}

	@Test (description="Forms || Verify the Forms count")
	public void TestCase_30269() {

		boolean loginWithSuperadminTypeUser = loginScreen.reLogin(pcAppTenantname, pcAppUsername, pcAppPassword);
		TestValidation.IsTrue(loginWithSuperadminTypeUser, 
				"LOGGED in with user - "+pcAppUsername , 
				"FAILED to login with user - "+pcAppUsername);

		FormsScreen selectFormsTab  = commonScreen.selectForms();
		TestValidation.IsFalse(selectFormsTab.error,
				"OPENED Forms Tab", 
				"COULD not open Forms Tab");

		boolean verifyFormCount = selectFormsTab.verifyFormCount(formCount);;
		TestValidation.IsFalse(verifyFormCount, 
				"VERIFIED large form count", 
				"FAILED verify large form count");

		boolean loginWithNormalUser = loginScreen.reLogin(pcAppTenantname, newUserName, newUserNewPassword);
		TestValidation.IsTrue(loginWithNormalUser, 
				"LOGGED in with user - "+newUserName , 
				"FAILED to login with user - "+newUserName);

		selectFormsTab  = commonScreen.selectForms();
		TestValidation.IsFalse(selectFormsTab.error,
				"OPENED Forms Tab", 
				"COULD not open Forms Tab");

		verifyFormCount = selectFormsTab.verifyFormCount(formCount);;
		TestValidation.IsTrue(verifyFormCount, 
				"VERIFIED form count - "+formCount, 
				"FAILED verify form count  - "+formCount);

		boolean verifyRefreshFormStatus = selectFormsTab.verifyFormCountOnRefresh();
		TestValidation.IsTrue(verifyRefreshFormStatus, 
				"VERIFIED form refresh", 
				"FAILED verify form form refresh");

		verifyFormCount = selectFormsTab.verifyFormCount(formCount);;
		TestValidation.IsTrue(verifyFormCount, 
				"VERIFIED form count - "+formCount, 
				"FAILED verify form count  - "+formCount);

	}

	@Test(description="Forms || Verify the Favorite status of the form")
	public void TestCase_30270() {

		FormsScreen selectFormsTab  = commonScreen.selectForms();
		TestValidation.IsFalse(selectFormsTab.error,
				"OPENED 'Forms' Tab", 
				"COULD not open 'Forms' Tab");

		boolean selectFormFavorite = selectFormsTab.selectFormFavorite(checkFormName1);
		TestValidation.IsTrue(selectFormFavorite, 
				"FAVORITED the form'", 
				"FAILED to select form for Favorites");

		FavoritesScreen selectFavoritesTab = commonScreen.selectFavorites();
		TestValidation.IsFalse(selectFavoritesTab.error,
				"OPENED 'Favorites' Tab", 
				"COULD not open 'Favorites' Tab");

		boolean verifyFavoriteForm = selectFavoritesTab.verifyFavoriteFormAvailability(checkFormName1);
		TestValidation.IsTrue(verifyFavoriteForm, 
				checkFormName1+" - FAVORITED FORM is available in Favorite screen", 
				checkFormName1+" - FAILED to get form in Favorites screen");

		selectFormsTab  = commonScreen.selectForms();
		TestValidation.IsFalse(selectFormsTab.error,
				"OPENED 'Forms' Tab", 
				"COULD not open 'Forms' Tab");

		boolean removeFormFavorite = selectFormsTab.selectFormFavorite(checkFormName1);
		TestValidation.IsTrue(removeFormFavorite, 
				"UN-FAVORITED the form'", 
				"FAILED to un-Favorite the form");

		selectFavoritesTab = commonScreen.selectFavorites();
		TestValidation.IsFalse(selectFavoritesTab.error,
				"OPENED 'Favorites' Tab", 
				"COULD not open 'Favorites' Tab");

		verifyFavoriteForm = selectFavoritesTab.verifyFavoriteFormAvailability(checkFormName1);
		TestValidation.IsFalse(verifyFavoriteForm, 
				checkFormName1+" - UN-FAVORITED FORM is NOT available in Favorite screen", 
				checkFormName1+" - UN-FAVORITED FORM is available in Favorite screen");

	}


	@Test(description="Forms || Verify the results after searching a form & resource")
	public void TestCase_30271() {

		FormsScreen selectFormsTab  = commonScreen.selectForms();
		TestValidation.IsFalse(selectFormsTab.error,
				"OPENED 'Forms' Tab", 
				"COULD not open 'Forms' Tab");

		boolean verifyFormAvailability = selectFormsTab.searchForm(checkFormName1);
		TestValidation.IsTrue(verifyFormAvailability, 
				"FOUND enabled form - "+checkFormName1, 
				"FAILED verify enabled form availability  - "+checkFormName1);

		boolean searchResourceToogle = selectFormsTab.searchResourceToggle();
		TestValidation.IsTrue(searchResourceToogle, 
				"CLICKED 'Search Resource' toogle", 
				"FAILED to click 'Search Resource' toogle");

		verifyFormAvailability = selectFormsTab.verifyFormName(checkFormName1);
		TestValidation.IsTrue(verifyFormAvailability, 
				"FOUND form - "+checkFormName1, 
				"FAILED verify form availability  - "+checkFormName1);

		boolean searchNameToogle = selectFormsTab.searchFormToggle();
		TestValidation.IsTrue(searchNameToogle, 
				"CLICKED 'Search Name' toogle", 
				"FAILED to click 'Search Name' toogle");

		boolean verifyResourceAvailability = selectFormsTab.verifyResourceHierarchy(resourceCategoryType1, resourceCategoryValue1, resourceInstanceValue1);
		TestValidation.IsTrue(verifyResourceAvailability, 
				"FOUND resource for the form - "+checkFormName1, 
				"FAILED verify resource availability for form  - "+checkFormName1);

		searchNameToogle = selectFormsTab.searchFormToggle();
		TestValidation.IsTrue(searchNameToogle, 
				"CLICKED 'Search Name' toogle", 
				"FAILED to click 'Search Name' toogle");

		verifyFormAvailability = selectFormsTab.verifyFormName(checkFormName1);
		TestValidation.IsTrue(verifyFormAvailability, 
				"FOUND form - "+checkFormName1, 
				"FAILED verify form availability  - "+checkFormName1);

		verifyResourceAvailability = selectFormsTab.verifyResourceHierarchy(resourceCategoryType1, resourceCategoryValue1, resourceInstanceValue1);
		TestValidation.IsTrue(verifyResourceAvailability, 
				"FOUND resource for the form - "+checkFormName1, 
				"FAILED verify resource availability for form  - "+checkFormName1);

	}

	@Test(description="Forms || Verify the refresh data functionality", priority=2)
	public void TestCase_30273() {
		FormsScreen selectFormsTab  = commonScreen.selectForms();
		TestValidation.IsFalse(selectFormsTab.error,
				"OPENED Forms Tab", 
				"COULD not open Forms Tab");

		boolean openForm = selectFormsTab.verifyFormCountOnRefresh();
		TestValidation.IsTrue(openForm, 
				"VERIFIED form count", 
				"FAILED to verify form count");
	}

	@Test(description="Forms || Verify refresh form functionality with form search", priority=2)
	public void TestCase_38166() {
		FormsScreen selectFormsTab  = commonScreen.selectForms();
		TestValidation.IsFalse(selectFormsTab.error,
				"OPENED Forms Tab", 
				"COULD not open Forms Tab");

		boolean searchForm = selectFormsTab.searchForm(checkFormName1);
		TestValidation.IsTrue(searchForm, 
				"FOUND enabled form - "+checkFormName1, 
				"FAILED verify enabled form availability  - "+checkFormName1);

		boolean openForm = selectFormsTab.verifyFormCountOnRefresh();
		TestValidation.IsTrue(openForm, 
				"VERIFIED form count", 
				"FAILED to verify form count");

		boolean verifyFormAvailability = selectFormsTab.verifyFormName(checkFormName1);
		TestValidation.IsTrue(verifyFormAvailability, 
				"FOUND form after search(Form Search Maintained) - "+checkFormName1, 
				"FAILED verify form availability  - "+checkFormName1);
	}


	@Test(description="View attached images || Verify user is able to view image attachment while form submission")
	public void TestCase_48005() {

		formDetailsPC = new FormDetailsPC();
		formview = new FormViewScreen(PCAppDriver);

		HashMap<String, List<String>> allFields1 = new LinkedHashMap<String, List<String>>();
		allFields1.put(NumericFieldName1,Arrays.asList("2"));
		allFields1.put(FreeTextIdentifierField,Arrays.asList("Text 1"));
		allFields1.put(FreeTextInputMaskIdentifierField,Arrays.asList("1234"));
		allFields1.put(SelectOneIdentifierField,Arrays.asList("IDN Option 1"));

		formDetailsPC.locationName = locationInstanceValue1;
		formDetailsPC.resourceName = resourceInstanceValue1;
		formDetailsPC.selectLocationResource = false;
		formDetailsPC.allowAttachmentCheck = true;
		formDetailsPC.propertyfieldName = NumericFieldName1;
		formDetailsPC.fieldDetails = allFields1;

		FormsScreen selectFormsTab  = commonScreen.selectForms();
		TestValidation.IsFalse(selectFormsTab.error,
				"OPENED Forms Tab", 
				"COULD not open Forms Tab");

		boolean openForm = selectFormsTab.selectOpenForm(checkFormName1);
		TestValidation.IsTrue(openForm, 
				"SELECTED & opened form - "+checkFormName1, 
				"FAILED open form - "+checkFormName1);

		boolean addFormLevelAttachment = formview.addFormLevelAttachment();
		TestValidation.IsTrue(addFormLevelAttachment, 
				"ADDED form level attachment", 
				"FAILED to add form level attachment");

		boolean fillData = formview.fillDataInForm1(formDetailsPC);
		TestValidation.IsTrue(fillData, 
				"VERIFIED field level attachment & SUBMITTED the form - "+checkFormName1, 
				"FAILED to verify field level attachment & submit the form - "+checkFormName1);
	}

	@Test(description="Forms || User is not able to submit form with blank value in identifier field ")
	public void TestCase_48186() {

		formDetailsPC = new FormDetailsPC();
		formview = new FormViewScreen(PCAppDriver);


		HashMap<String, List<String>> allFields1 = new LinkedHashMap<String, List<String>>();
		allFields1.put(NumericFieldName1,Arrays.asList("2"));
		allFields1.put(FreeTextIdentifierField,Arrays.asList(" "));
		allFields1.put(FreeTextInputMaskIdentifierField,Arrays.asList(" "));

		formDetailsPC.locationName = locationInstanceValue1;
		formDetailsPC.resourceName = resourceInstanceValue1;
		formDetailsPC.selectLocationResource = false;
		formDetailsPC.fieldDetails = allFields1;
		formDetailsPC.isSubmit = false;

		FormsScreen selectFormsTab  = commonScreen.selectForms();
		TestValidation.IsFalse(selectFormsTab.error,
				"OPENED Forms Tab", 
				"COULD not open Forms Tab");

		boolean openForm = selectFormsTab.selectOpenForm(checkFormName1);
		TestValidation.IsTrue(openForm, 
				"SELECTED & opened form - "+checkFormName1, 
				"FAILED open form - "+checkFormName1);

		boolean fillData = formview.fillDataInForm1(formDetailsPC);
		TestValidation.IsTrue(fillData, 
				"Filled data in the form - "+checkFormName1, 
				"FAILED to fill data in form - "+checkFormName1);

		boolean submitForm = formview.submitForm();
		TestValidation.IsTrue(submitForm, 
				"Perfomed 'SUBMIT' action - "+checkFormName1, 
				"FAILED to perform 'SUBMIT' action - "+checkFormName1);

		boolean closeFormValidationFailedPopup = formview.closeFormValidationFailedPopup();
		TestValidation.IsTrue(closeFormValidationFailedPopup, 
				"VERIFIED CLOSED 'Form Validation Failed' pop up", 
				"FAILED to verify & close 'Form Validation Failed' pop up");

		boolean verifyPleaseEnterAValueLabel = formview.verifyPleaseEnterAValueLabel();
		TestValidation.IsTrue(verifyPleaseEnterAValueLabel, 
				"VERIFIED 'Please enter a value.' label - "+checkFormName1, 
				"FAILED to verify 'Please enter a value.' label - "+checkFormName1);

		allFields1 = new LinkedHashMap<String, List<String>>();
		allFields1.put(NumericFieldName1,Arrays.asList("2"));
		allFields1.put(FreeTextIdentifierField,Arrays.asList("Text 1"));
		allFields1.put(FreeTextInputMaskIdentifierField,Arrays.asList("1234"));
		allFields1.put(SelectOneIdentifierField,Arrays.asList("IDN Option 1"));

		formDetailsPC.fieldDetails = allFields1;
		formDetailsPC.isSubmit = true;

		boolean saveForm = formview.saveForm();
		TestValidation.IsTrue(saveForm, 
				"SAVED form - "+checkFormName1, 
				"FAILED to save the form - "+checkFormName1);

		savedScreen = commonScreen.selectSaved();
		TestValidation.IsFalse(savedScreen.error,
				"OPENED Saved Tab", 
				"COULD not open Saved Tab");

		boolean openSavedForm = savedScreen.selectOpenForm(checkFormName1);
		TestValidation.IsTrue(openSavedForm, 
				"SELECTED & opened saved form - "+checkFormName1, 
				"FAILED to open saved form - "+checkFormName1);

		fillData = formview.fillDataInForm1(formDetailsPC);
		TestValidation.IsTrue(fillData, 
				"Filled data & submitted the form - "+checkFormName1, 
				"FAILED to fill data & submit the form - "+checkFormName1);

	}

	@Test(description="Forms | Camera || Verify PC App working after performing multiple clicks on Camera")
	public void TestCase_48424() {

		formview = new FormViewScreen(PCAppDriver);

		FormsScreen selectFormsTab  = commonScreen.selectForms();
		TestValidation.IsFalse(selectFormsTab.error,
				"OPENED Forms Tab", 
				"COULD not open Forms Tab");

		boolean openForm = selectFormsTab.selectOpenForm(checkFormName1);
		TestValidation.IsTrue(openForm, 
				"SELECTED & opened form - "+checkFormName1, 
				"FAILED open form - "+checkFormName1);

		boolean addFormLevelAttachment = formview.doubleCickOnFormLevelCamera(true);
		TestValidation.IsTrue(addFormLevelAttachment, 
				"CAMERA is WORKING after performing double click on it(Form Level)", 
				"CAMERA is NOT working  after performing double click on it(Form Level)");

		boolean addFieldLevelAttachment = formview.doubleCickOnFieldLevelCamera(true);
		TestValidation.IsTrue(addFieldLevelAttachment, 
				"CAMERA is WORKING after performing double click on it(Field Level)", 
				"CAMERA is NOT working  after performing double click on it(Field Level)");

		boolean discardForm = formview.discardForm(true);
		TestValidation.IsTrue(discardForm, 
				"CLOSED(Discard) the form - "+checkFormName1, 
				"FAILED to close(Discard) the form - "+checkFormName1);
	}


	@Test(description="View Attached Image || Verify user is able to view the attachment in offline mode")
	public void TestCase_48688() {

		formDetailsPC = new FormDetailsPC();
		formview = new FormViewScreen(PCAppDriver);

		HashMap<String, List<String>> allFields1 = new LinkedHashMap<String, List<String>>();
		allFields1.put(NumericFieldName1,Arrays.asList("2"));
		allFields1.put(FreeTextIdentifierField,Arrays.asList("Text 1"));
		allFields1.put(FreeTextInputMaskIdentifierField,Arrays.asList("1234"));
		allFields1.put(SelectOneIdentifierField,Arrays.asList("IDN Option 1"));

		formDetailsPC.locationName = locationInstanceValue1;
		formDetailsPC.resourceName = resourceInstanceValue1;
		formDetailsPC.selectLocationResource = false;
		formDetailsPC.allowAttachmentCheck = true;
		formDetailsPC.propertyfieldName = NumericFieldName1;
		formDetailsPC.fieldDetails = allFields1;


		SettingsScreen selectSettingsTab  = commonScreen.selectSettings();
		TestValidation.IsFalse(selectSettingsTab.error,
				"OPENED 'Settings' Tab", 
				"COULD not open 'Settings' Tab");

		boolean changeOfflineModeToggle = selectSettingsTab.changeOfflineModeToggle(true);
		TestValidation.IsTrue(changeOfflineModeToggle, 
				"CHANGED 'Offline Mode' toggle - ON", 
				"FAILED to change''Offline Mode' toggle - ON");

		FormsScreen selectFormsTab  = commonScreen.selectForms();
		TestValidation.IsFalse(selectFormsTab.error,
				"OPENED Forms Tab", 
				"COULD not open Forms Tab");

		boolean openForm = selectFormsTab.selectOpenForm(checkFormName1);
		TestValidation.IsTrue(openForm, 
				"SELECTED & opened form - "+checkFormName1, 
				"FAILED open form - "+checkFormName1);

		boolean addFormLevelAttachment = formview.addFormLevelAttachment();
		TestValidation.IsTrue(addFormLevelAttachment, 
				"ADDED form level attachment", 
				"FAILED to add form level attachment");

		boolean fillData = formview.fillDataInForm1(formDetailsPC);
		TestValidation.IsTrue(fillData, 
				"VERIFIED field level attachment & SUBMITTED the form - "+checkFormName1, 
				"FAILED to verify field level attachment & submit the form - "+checkFormName1);

		selectSettingsTab  = commonScreen.selectSettings();
		TestValidation.IsFalse(selectSettingsTab.error,
				"OPENED 'Settings' Tab", 
				"COULD not open 'Settings' Tab");

		changeOfflineModeToggle = selectSettingsTab.changeOfflineModeToggle(false);
		TestValidation.IsTrue(changeOfflineModeToggle, 
				"CHANGED 'Offline Mode' toggle - OFF", 
				"FAILED to change''Offline Mode' toggle - OFF");
	}


	@Test (description="Forms || Verify short date on forms screen")
	public void TestCase_51238() {

		FormsScreen selectFormsTab  = commonScreen.selectForms();
		TestValidation.IsFalse(selectFormsTab.error,
				"OPENED Forms Tab", 
				"COULD not open Forms Tab");

		boolean verifyFormDetails = selectFormsTab.searchForm(checkFormName1);
		TestValidation.IsTrue(verifyFormDetails, 
				"SEARCHED form - "+checkFormName1, 
				"FAILED search form - "+checkFormName1);

		String modifiedOn =  selectFormsTab.getModifiedOnDetails();

		boolean verifyDateTimeFormat = datetime.verifyDateTimeFormat("MM/dd/yyyy HH:mm", modifiedOn);
		TestValidation.IsTrue(verifyDateTimeFormat, 
				"VERIFED task due date format for - "+modifiedOn, 
				"FAILED to verify task due date format for - "+modifiedOn);

	}

	@Test(description="Forms || Verify form count after releasing a new version of saved form", priority=2)
	public void TestCase_42499() {

		formDetailsPC = new FormDetailsPC();
		formview = new FormViewScreen(PCAppDriver);

		HashMap<String, List<String>> allFields1 = new LinkedHashMap<String, List<String>>();
		allFields1.put(NumericFieldName1,Arrays.asList("2"));
		allFields1.put(FreeTextIdentifierField,Arrays.asList("Text 1"));
		allFields1.put(FreeTextInputMaskIdentifierField,Arrays.asList("1234"));
		allFields1.put(SelectOneIdentifierField,Arrays.asList("IDN Option 1"));

		formDetailsPC.locationName = locationInstanceValue1;
		formDetailsPC.resourceName = resourceInstanceValue1;
		formDetailsPC.selectLocationResource = false;
		formDetailsPC.fieldDetails = allFields1;

		FormsScreen selectFormsTab  = commonScreen.selectForms();
		TestValidation.IsFalse(selectFormsTab.error,
				"OPENED Forms Tab", 
				"COULD not open Forms Tab");

		boolean openForm = selectFormsTab.selectOpenForm(checkFormName1);
		TestValidation.IsTrue(openForm, 
				"SELECTED & opened form - "+checkFormName1, 
				"FAILED open form - "+checkFormName1);

		boolean saveForm = formview.saveForm();
		TestValidation.IsTrue(saveForm, 
				"SAVED form - "+checkFormName1, 
				"FAILED save the form - "+checkFormName1);

		boolean formRelease = false;
		try {

			driver = launchbrowser();
			mainPage = new CommonPage(driver);
			webLoginPage= new LoginPage(driver);
			webfsqaFormsPage = new FSQABrowserPage(driver);
			webformDesignerPage = new FormDesignerPage(driver);
			controlActionsWeb = new ControlActions(driver);

			controlActionsWeb.getUrl(pcAppTenantURL);
			webHomePage = webLoginPage.performLogin(newUserName, newUserNewPassword);

			if(webHomePage.error) {
				TCGFailureMsg = "Could NOT login to application";
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}

			webHomePage.clickFSQABrowserMenu();
			webfsqaFormsPage.selectResourceDropDownandNavigate(resourceCategoryType1,FSQATAB.FORMS);
			webfsqaFormsPage.openAndApplySettingsForColumn(COLUMNHEADER.FORMNAME, COLUMNSETTING.FILTER,checkFormName1);

			formRelease = webfsqaFormsPage.editForm(checkFormName1,webformDesignerPage);
			TestValidation.IsTrue(formRelease, 
					"FORM Edited & RELEASED with new version", 
					"FAILED to release the form");

		}catch(Exception e) {
			TestValidation.IsTrue(formRelease, 
					"FORM Edited & RELEASED with new version", 
					"FAILED to release the form");
		}finally {
			driver.close();
		}

		boolean refreshFormsTab = selectFormsTab.refreshForms();
		TestValidation.IsTrue(refreshFormsTab, 
				"REFRESHED Forms", 
				"FAILED to refresh Forms");

		boolean verifyformCount = selectFormsTab.verifyFormCount(formCount);
		TestValidation.IsTrue(verifyformCount, 
				"VERIFIED form count - "+formCount, 
				"FAILED to verify form count - "+formCount);

		SavedScreen selectSavedTab  = commonScreen.selectSaved();
		TestValidation.IsFalse(selectSavedTab.error,
				"OPENED Saved Tab", 
				"COULD not open Saved Tab");

		boolean verifySavedformCount = selectSavedTab.verifyCount(1);
		TestValidation.IsTrue(verifySavedformCount, 
				"VERIFIED saved form count - 1", 
				"FAILED to verify saved form count - 1");

		openForm = selectSavedTab.selectOpenForm(checkFormName1);
		TestValidation.IsTrue(openForm, 
				"SELECTED & opened saved form - "+checkFormName1, 
				"FAILED open saved form - "+checkFormName1);

		boolean fillData = formview.fillDataInForm1(formDetailsPC);
		TestValidation.IsTrue(fillData, 
				"SUBMITTED the form - "+checkFormName1, 
				"FAILED to submit the form - "+checkFormName1);

	}


	@Test(description="Forms || Verify User is able to enter text in Identifier field having input mask")
	public void TestCase_54169() {

		formDetailsPC = new FormDetailsPC();
		formview = new FormViewScreen(PCAppDriver);

		HashMap<String, List<String>> allFields1 = new LinkedHashMap<String, List<String>>();
		allFields1.put(NumericFieldName1,Arrays.asList("2"));
		allFields1.put(FreeTextIdentifierField,Arrays.asList("Text 1"));
		allFields1.put(FreeTextInputMaskIdentifierField,Arrays.asList("1234"));
		allFields1.put(SelectOneIdentifierField,Arrays.asList("IDN Option 1"));

		formDetailsPC.locationName = locationInstanceValue1;
		formDetailsPC.resourceName = resourceInstanceValue1;
		formDetailsPC.selectLocationResource = false;
		formDetailsPC.allowAttachmentCheck = true;
		formDetailsPC.propertyfieldName = NumericFieldName1;
		formDetailsPC.fieldDetails = allFields1;

		FormsScreen selectFormsTab  = commonScreen.selectForms();
		TestValidation.IsFalse(selectFormsTab.error,
				"OPENED Forms Tab", 
				"COULD not open Forms Tab");

		boolean openForm = selectFormsTab.selectOpenForm(checkFormName1);
		TestValidation.IsTrue(openForm, 
				"SELECTED & opened form - "+checkFormName1, 
				"FAILED open form - "+checkFormName1);

		boolean fillData = formview.fillDataInForm1(formDetailsPC);
		TestValidation.IsTrue(fillData, 
				"FILLED data in Identifier - Free Text(Input Mask) field & SUBMITTED the form - "+checkFormName1, 
				"FAILED to fill data in Identifier - Free Text(Input Mask) field & submit the form - "+checkFormName1);

	}


	@AfterClass
	public void closeApp() {
		PCAppDriver.close();
	}

}
