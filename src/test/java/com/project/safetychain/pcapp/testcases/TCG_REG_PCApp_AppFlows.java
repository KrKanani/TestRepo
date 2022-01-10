package com.project.safetychain.pcapp.testcases;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.project.safetychain.api.models.support.TCG_FormCreation_Wrapper;
import com.project.safetychain.api.models.support.TCG_UserFlows_Wrapper;
import com.project.safetychain.api.models.support.Support.DPT_FIELD_TYPES;
import com.project.safetychain.api.models.support.Support.DPT_FORM_TYPES;
import com.project.safetychain.api.models.support.Support.FormFieldParams;
import com.project.safetychain.api.models.support.Support.FormParams;
import com.project.safetychain.api.models.support.Support.UserParams;
import com.project.safetychain.pcapp.pageobjects.CommonScreen;
import com.project.safetychain.pcapp.pageobjects.DeviceManagementScreen;
import com.project.safetychain.pcapp.pageobjects.FavoritesScreen;
import com.project.safetychain.pcapp.pageobjects.FormViewScreen;
import com.project.safetychain.pcapp.pageobjects.FormsScreen;
import com.project.safetychain.pcapp.pageobjects.InboxScreen;
import com.project.safetychain.pcapp.pageobjects.LINKScreen;
import com.project.safetychain.pcapp.pageobjects.LoginScreen;
import com.project.safetychain.pcapp.pageobjects.SavedScreen;
import com.project.safetychain.pcapp.pageobjects.SettingsScreen;
import com.project.safetychain.pcapp.pageobjects.SubmissionsScreen;
import com.project.safetychain.pcapp.pageobjects.FormViewScreen.FormDetailsPC;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FORMRESOURCETYPES;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.utilities.ApiUtils;
import com.project.utilities.CommonMethods;
import com.project.utilities.ControlActions_PCApp;

public class TCG_REG_PCApp_AppFlows extends TestBase{

	ControlActions_PCApp controlActionsPC;

	CommonScreen commonScreen;
	LoginScreen loginScreen;

	InboxScreen inboxScreen;
	SubmissionsScreen submissionsScreen;
	SavedScreen savedScreen;
	FavoritesScreen favoritesScreen;
	FormsScreen formsScreen;
	DeviceManagementScreen deviceScreen;
	LINKScreen linkScreen;
	SettingsScreen settingsScreen;
	FormViewScreen formview; 


	FormDetailsPC formDetailsPC;



	ApiUtils apiUtils =  null;
	TCG_FormCreation_Wrapper formCreationWrapper = null;
	TCG_UserFlows_Wrapper userCreationWrapper = null;

	public static String resourceCategoryType;

	public static String locationCategoryValue;
	public static String locationInstanceValue;

	public static String resourceCategoryValue;
	public static String resourceInstanceValue;

	public static String checkFormName;
	String NumericFieldName1;

	public static String formId1;
	public static String locInstId,ResInstId;
	public static String username,password;
	public static String version;
	public static String username1;


	@BeforeClass(alwaysRun = true)
	public void groupInit() throws InterruptedException {

		resourceCategoryType = FORMRESOURCETYPES.ITEMS;

		locationCategoryValue = CommonMethods.dynamicText("PC_LCat");
		locationInstanceValue = CommonMethods.dynamicText("PC_LInst1");
		resourceCategoryValue = CommonMethods.dynamicText("PC_RCat");
		resourceInstanceValue = CommonMethods.dynamicText("PC_RInst1");

		NumericFieldName1 = "Num 1";

		checkFormName = CommonMethods.dynamicText("PCAppForm1");

		username =  CommonMethods.dynamicText("PCAppUser1");
		password = "Test";

		username1 = "UWP App Flow User";
		version = pcAppVersion;

		apiUtils =  new ApiUtils();
		formCreationWrapper = new TCG_FormCreation_Wrapper();
		userCreationWrapper = new TCG_UserFlows_Wrapper();

		List<String> userList = Arrays.asList(pcAppUsername);	

		HashMap<String, List<String>> LocationMap = new HashMap<String, List<String>>();
		LocationMap.put(locationCategoryValue, Arrays.asList(locationInstanceValue));

		HashMap<String, List<String>> resCatMap = new HashMap<String, List<String>>();
		resCatMap.put(resourceCategoryValue, Arrays.asList(resourceInstanceValue));		

		HashMap<String, String> locResMap = formCreationWrapper.Resource_Location_CreationWrapper(resCatMap, LocationMap, true, userList,
				resourceCategoryType);

		locInstId = locResMap.get(locationInstanceValue);
		ResInstId = locResMap.get(resourceInstanceValue);

		String formId1 = apiUtils.getUUID();

		FormFieldParams NumericField1 = new FormFieldParams();
		NumericField1.DPTFieldType = DPT_FIELD_TYPES.NUMERIC;
		NumericField1.Name = NumericFieldName1;
		NumericField1.AllowAttachments = "true";
		NumericField1.RepeatField = "true";
		NumericField1.IsRequired = "false";
		NumericField1.defaultComplianceValue = "2";

		FormParams fp1 = new FormParams();
		fp1.FormId = formId1;
		fp1.type = DPT_FORM_TYPES.CHECK;
		fp1.ResourceType = resourceCategoryType;
		fp1.ResourceCategoryNm = resourceCategoryValue;
		fp1.ItemsResources = resCatMap;
		fp1.FormName = checkFormName;
		fp1.formElements = Arrays.asList(NumericField1);

		boolean formcreation = false;

		try {
			formCreationWrapper.API_Wrapper_Forms(fp1);
			formcreation = true;
			logInfo("'"+checkFormName+"' Form is created");
		} catch (InterruptedException e) {
			logError("Error while '"+checkFormName+"' form creation");
			formcreation = false;
		}

		TestValidation.IsTrue(formcreation, 
				"CREATED form - " + checkFormName, 
				"Could NOT create form - " + checkFormName);

		UserParams userDetails = new UserParams();
		userDetails.Username = username;
		userDetails.ClearPassword = password;
		userDetails.FirstName = "UWP App Flow";
		userDetails.LastName = "User";
		userDetails.Email = "pcappuser.pcapp@s.com";
		userDetails.TimeZone = "U.S. Pacific";
		userDetails.LocationCat = locationCategoryValue;
		userDetails.LocationNmIds = Arrays.asList(locationInstanceValue);
		userDetails.Roles = Arrays.asList("SuperAdmin");

		boolean userCreation = false;

		try {
			userCreationWrapper.createUser_Wrapper(userDetails);
			userCreation = true;
			logInfo("'"+username+"' User is created");
		} catch (InterruptedException e) {
			logError("Error while '"+username+"' user creation");
			userCreation = false;
		}

		TestValidation.IsTrue(userCreation, 
				"CREATED User - " + username, 
				"Could NOT create user - " + username);

		try {

			PCAppDriver = launchPCApp();
			controlActionsPC = new ControlActions_PCApp(PCAppDriver);
			loginScreen = new LoginScreen(PCAppDriver);
			commonScreen = loginScreen.Login(pcAppTenantname, username, password);

			if(commonScreen.error) {
				TCGFailureMsg = "COULD not login to the application";
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}

			inboxScreen = new InboxScreen(PCAppDriver);
			submissionsScreen = new SubmissionsScreen(PCAppDriver);
			savedScreen = new SavedScreen(PCAppDriver);
			favoritesScreen = new FavoritesScreen(PCAppDriver);
			formsScreen = new FormsScreen(PCAppDriver);;
			deviceScreen = new DeviceManagementScreen(PCAppDriver);
			linkScreen = new LINKScreen(PCAppDriver);
			settingsScreen = new SettingsScreen(PCAppDriver);
			formview = new FormViewScreen(PCAppDriver);

		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Test(description="Link || Verify the Link view",priority = 2)
	public void TestCase_33623() {
		LINKScreen selectLINKTab  = commonScreen.selectLink();
		TestValidation.IsFalse(selectLINKTab.error,
				"OPENED LINKS Tab", 
				"COULD not open LINKS Tab");

		boolean verifyLINKPageElements = selectLINKTab.verifyLINKElements();
		TestValidation.IsTrue(verifyLINKPageElements, 
				"VERIFIED LINK elements", 
				"FAILED to verify LINK elements");
	}

	@Test(description="PC App || Verify hamburger expand and minimize functionality",priority = 1)
	public void TestCase_33622() {

		boolean menu  = commonScreen.clickHamburgerMenu();
		TestValidation.IsTrue(menu,
				"Clicked on hamburger menu", 
				"COULD not click on Hamburger Menu");

		boolean verify = commonScreen.verifyMenuItems();
		TestValidation.IsFalse(verify, 
				"VERIFIED menu items", 
				"FAILED to verify menu items");

		boolean menu1  = commonScreen.clickHamburgerMenu();
		TestValidation.IsTrue(menu1,
				"Clicked on hamburger menu", 
				"COULD not click on Hamburger Menu");
	}

	@Test(description="PC App || Verify that all the menu items should be accessible",priority = 3)
	public void TestCase_30260() {

		InboxScreen inbox = commonScreen.selectInbox();
		TestValidation.IsFalse(inbox.error,
				"Clicked inbox menu", 
				"COULD not click Inbox Menu");

		boolean gridverify = inbox.listView();
		TestValidation.IsTrue(gridverify, 
				"No task is there in Inbox", 
				"Tasks are present");

		SavedScreen save = commonScreen.selectSaved();
		TestValidation.IsFalse(save.error,
				"Clicked save menu", 
				"COULD not click save Menu");

		boolean verify = save.verifyNoSaveForm();
		TestValidation.IsTrue(verify,
				"Verified no saved task", 
				"COULD not verify no saved task");


		SubmissionsScreen submit = commonScreen.selectSubmissions();
		TestValidation.IsFalse(submit.error,
				"Clicked sabmission menu", 
				"COULD not click submission Menu");

		boolean verify1 = submit.verifyNoSubissions();
		TestValidation.IsTrue(verify1,
				"Verified no submissions", 
				"COULD not verify no submissions");

		FavoritesScreen fav = commonScreen.selectFavorites();
		TestValidation.IsFalse(fav.error,
				"Clicked favourites menu", 
				"COULD not click favourites Menu");

		boolean verify2 = fav.verifyNoFavourites();
		TestValidation.IsTrue(verify2,
				"Verified no favourites", 
				"COULD not verify no favourites");

		FormsScreen form = commonScreen.selectForms();
		TestValidation.IsFalse(form.error,
				"Clicked form menu", 
				"COULD not click form Menu");

		boolean verify3 = form.verifyFormName(checkFormName);
		TestValidation.IsTrue(verify3,
				"Verified form", 
				"COULD not verify form");

		DeviceManagementScreen device = commonScreen.selectDeviceManagement();
		TestValidation.IsFalse(device.error,
				"Clicked Device menu", 
				"COULD not click Device Menu");

		boolean verify4 = device.verifyNoDevices();
		TestValidation.IsTrue(verify4,
				"Verified no device", 
				"COULD not verify no device");

		SettingsScreen setting = commonScreen.selectSettings();
		TestValidation.IsFalse(setting.error,
				"Clicked settings menu", 
				"COULD not click settings Menu");

		boolean verify5 = setting.verifyLabels();
		TestValidation.IsFalse(verify5,
				"Verified labels", 
				"COULD not verify labels");

	}

	@Test(description="PC App || Verify that all the elements in UI screen should be visible",priority = 0)
	public void TestCase_30265() {

		boolean verify = commonScreen.verifyloggedInDetails(version, username1);
		TestValidation.IsTrue(verify,
				"Verified logged in details", 
				"COULD not verify logged in details");
	}

	@Test(description="PC App || Verify application when it is minimized",priority = -1)
	public void TestCase_39506() {

		boolean min = commonScreen.minimizeApp();
		TestValidation.IsTrue(min,
				"Minimized SC Application", 
				"COULD not minimize SC Application");

		boolean max = commonScreen.maximizeApp();
		TestValidation.IsTrue(max,
				"Maximized SC Application", 
				"COULD not maximize SC Application");

		FormsScreen navigate = commonScreen.selectForms();
		TestValidation.IsFalse(navigate.error,
				"Navigated to forms Screen", 
				"COULD not navigate to forms screen");
	}

	@Test(description="Application || Verify multiple click actions for different screen tabs", priority = -2)
	public void TestCase_48600() {

		boolean inboxTab = inboxScreen.doubleClickInbox();
		TestValidation.IsTrue(inboxTab,
				"'Inbox' tab is working on performing double click", 
				"ISSUE on performing double click on 'Inbox' Tab");

		boolean submissionsTab = submissionsScreen.doubleClickSubmissionsTab();
		TestValidation.IsTrue(submissionsTab,
				"'Submissions' tab is working on performing double click", 
				"ISSUE on performing double click on 'Submissions' Tab");

		boolean savedTab = savedScreen.doubleClickSavedTab();
		TestValidation.IsTrue(savedTab,
				"'Saved' tab is working on performing double click", 
				"ISSUE on performing double click on 'Saved' Tab");

		boolean favoritesTab = favoritesScreen.doubleClickFavorites();
		TestValidation.IsTrue(favoritesTab,
				"'Favorites' tab is working on performing double click", 
				"ISSUE on performing double click on 'Favorites' Tab");

		boolean formsTab = formsScreen.doubleClickForms();
		TestValidation.IsTrue(formsTab,
				"'Forms' tab is working on performing double click", 
				"ISSUE on performing double click on 'Forms' Tab");

		boolean deviceManagementTab = deviceScreen.doubleClickDeviceManagement();
		TestValidation.IsTrue(deviceManagementTab,
				"'Device Management' tab is working on performing double click", 
				"ISSUE on performing double click on 'Device Management' Tab");

		boolean LINKTab = linkScreen.doubleClickLINK();
		TestValidation.IsTrue(LINKTab,
				"'LINK' tab is working on performing double click", 
				"ISSUE on performing double click on 'LINK' Tab");

		boolean settingsTab = settingsScreen.doubleClickSettings();
		TestValidation.IsTrue(settingsTab,
				"'Settings' tab is working on performing double click", 
				"ISSUE on performing double click on 'Settings' Tab");

	}

	@Test(description="Application || Verify multiple click actions for different buttons", priority = 4)
	public void TestCase_48601() {

		FormsScreen selectFormsTab  = commonScreen.selectForms();
		TestValidation.IsFalse(selectFormsTab.error,
				"OPENED 'Forms' Tab", 
				"COULD not open 'Forms' Tab");

		boolean openForm = selectFormsTab.selectOpenForm(checkFormName);
		TestValidation.IsTrue(openForm, 
				"SELECTED & opened form - "+checkFormName, 
				"FAILED open form - "+checkFormName);

		boolean submitRepeatForm = formview.doubleCickOnSubmitRepeat();
		TestValidation.IsTrue(submitRepeatForm, 
				"'SUBMIT REPEAT' form using double click", 
				"FAILED to 'SUBMIT REPEAT' the form using double click");

		boolean submitForm = formview.doubleCickOnSubmit();
		TestValidation.IsTrue(submitForm, 
				"SUBMITTED form using double click", 
				"FAILED to Submit the form using double click");

		openForm = selectFormsTab.selectOpenForm(checkFormName);
		TestValidation.IsTrue(openForm, 
				"SELECTED & opened form - "+checkFormName, 
				"FAILED open form - "+checkFormName);

		boolean saveForm = formview.doubleCickOnSave();
		TestValidation.IsTrue(saveForm, 
				"SAVED form using double click", 
				"FAILED to save the form using double click");

		savedScreen = commonScreen.selectSaved();
		TestValidation.IsFalse(savedScreen.error,
				"OPENED Saved Tab", 
				"COULD not open Saved Tab");

		boolean openSavedForm = savedScreen.selectOpenForm(checkFormName);
		TestValidation.IsTrue(openSavedForm, 
				"SELECTED & opened saved form - "+checkFormName, 
				"FAILED open saved form - "+checkFormName);

		boolean deleteSavedForm = savedScreen.doubleClickDelete(checkFormName);
		TestValidation.IsTrue(deleteSavedForm, 
				"DELETED saved form using double click - "+checkFormName, 
				"FAILED delete saved form using double click - "+checkFormName);

	}

	@AfterClass(alwaysRun = true)
	public void closeBrowser() throws InterruptedException {
		PCAppDriver.close();
	}
}
