package com.project.safetychain.pcapp.testcases;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
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
import com.project.safetychain.pcapp.pageobjects.CommonScreen;
import com.project.safetychain.pcapp.pageobjects.FavoritesScreen;
import com.project.safetychain.pcapp.pageobjects.FormViewScreen;
import com.project.safetychain.pcapp.pageobjects.FormsScreen;
import com.project.safetychain.pcapp.pageobjects.LoginScreen;
import com.project.safetychain.pcapp.pageobjects.SavedScreen;
import com.project.safetychain.pcapp.pageobjects.SettingsScreen;
import com.project.safetychain.pcapp.pageobjects.SubmissionsScreen;
import com.project.safetychain.pcapp.pageobjects.FormViewScreen.FormDetailsPC;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FORMRESOURCETYPES;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.utilities.ApiUtils;
import com.project.safetychain.api.models.support.Support.UserParams;
import com.project.utilities.CommonMethods;
import com.project.utilities.ControlActions_PCApp;

public class TCG_REG_Favorites_FavoriteFormFlow extends TestBase {

	ApiUtils apiUtils = null;
	FormDesignerPage formDesignerPage;

	ControlActions_PCApp controlActionsPC;
	CommonScreen commonScreen;
	LoginScreen loginScreen;
	FormsScreen forms;
	FormViewScreen formview; 
	SavedScreen savedScreen;

	FormDetailsPC formDetailsPC;

	public static String locationCategoryValue1;
	public static String locationInstanceValue1;
	public static String resourceCategoryType1 = FORMRESOURCETYPES.ITEMS;
	public static String resourceInstanceValue1;
	public static String resourceCategoryValue1;

	public static String checkFormName;
	String NumericFieldName1 = "Num 1";

	public static String newUserName, newUserPassword, newWGName;


	@BeforeClass(alwaysRun = true)
	public void groupInit() {
		try {

			apiUtils = new ApiUtils();

			TCG_FormCreation_Wrapper formCreationWrapper = new TCG_FormCreation_Wrapper();
			TCG_UserFlows_Wrapper userCreationWrapper = new TCG_UserFlows_Wrapper();

			locationCategoryValue1 = CommonMethods.dynamicText("PC_FavLocCat1");
			locationInstanceValue1 = CommonMethods.dynamicText("PC_FavLocInst1");
			resourceCategoryValue1 = CommonMethods.dynamicText("PC_FavResCat1");
			resourceInstanceValue1 = CommonMethods.dynamicText("PC_FavResInst1");

			checkFormName = CommonMethods.dynamicText("PC_FavForm1");

			newUserName =  CommonMethods.dynamicText("PC_FavFormFlowUser");
			newUserPassword = "Test";
			newWGName =  CommonMethods.dynamicText("PC_FavFormFlowWG");

			HashMap<String, List<String>> resourceCatMap = new HashMap<String, List<String>>();
			resourceCatMap.put(resourceCategoryValue1, Arrays.asList(resourceInstanceValue1));

			HashMap<String, List<String>> LocationMap = new HashMap<String, List<String>>();
			LocationMap.put(locationCategoryValue1, Arrays.asList(locationInstanceValue1));

			List<String> userList = Arrays.asList(pcAppUsername);

			formCreationWrapper.Resource_Location_CreationWrapper(resourceCatMap,
					LocationMap, true, userList, resourceCategoryType1);

			FormFieldParams NumericField1 = new FormFieldParams();
			NumericField1.DPTFieldType = DPT_FIELD_TYPES.NUMERIC;
			NumericField1.Name = NumericFieldName1;

			String formId = apiUtils.getUUID();

			FormParams fp1 = new FormParams();
			fp1.FormId = formId;
			fp1.type = DPT_FORM_TYPES.CHECK;
			fp1.ResourceType = resourceCategoryType1;
			fp1.ResourceCategoryNm = resourceCategoryValue1;
			fp1.ItemsResources = resourceCatMap;
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
			userDetails.Username = newUserName;
			userDetails.ClearPassword = newUserPassword;
			userDetails.FirstName = "UWP LOGIN";
			userDetails.LastName = "User";
			userDetails.Email = "favformuser.pcapp@s.com";
			userDetails.TimeZone = "U.S. Pacific";
			userDetails.LocationCat = locationCategoryValue1;
			userDetails.LocationNmIds = Arrays.asList(locationInstanceValue1,"KLKL");
			userDetails.Roles = Arrays.asList("SuperAdmin");
			userDetails.WorkGroupNames = Arrays.asList(newWGName);

			boolean userCreation = false;

			try {
				userCreationWrapper.create_User_Wrapper(userDetails);
				userCreation = true;
				logInfo("'"+newUserName+"' User is created");
			} catch (InterruptedException e) {
				logError("Error while '"+newUserName+"' user creation");
				userCreation = false;
			}
			TestValidation.IsTrue(userCreation, 
					"CREATED User - " + newUserName, 
					"Could NOT create user - " + newUserName);

			PCAppDriver = launchPCApp();

			controlActionsPC = new ControlActions_PCApp(PCAppDriver);
			commonScreen = new CommonScreen(PCAppDriver);
			forms = new FormsScreen(PCAppDriver);
			formview = new FormViewScreen(PCAppDriver);
			savedScreen = new SavedScreen(PCAppDriver);
			loginScreen = new LoginScreen(PCAppDriver);

			commonScreen = loginScreen.Login(pcAppTenantname, newUserName,newUserPassword);
			if(commonScreen.error) {
				TCGFailureMsg = "COULD not login to the application";
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}

		}catch(Exception e){
			e.printStackTrace();
			TCGFailureMsg = "Failed to perform pre-requiste operations for 'TCG_REG_Favorites_FavoriteFormFlow'";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
	}


	@Test(description="Favorites || Verify the form chosen from Forms for favorite should be visible", priority=0)
	public void TestCase_30276() {

		FormsScreen selectFormsTab  = commonScreen.selectForms();
		TestValidation.IsFalse(selectFormsTab.error,
				"OPENED 'Forms' Tab", 
				"COULD not open 'Forms' Tab");

		boolean navigateToMainScren = selectFormsTab.selectFormFavorite(checkFormName);
		TestValidation.IsTrue(navigateToMainScren, 
				"FAVORITED the form'", 
				"FAILED to select form for Favorites");

		FavoritesScreen selectFavoritesTab = commonScreen.selectFavorites();
		TestValidation.IsFalse(selectFavoritesTab.error,
				"OPENED 'Favorites' Tab", 
				"COULD not open 'Favorites' Tab");

		boolean verifyFavoriteForm = selectFavoritesTab.verifyFavoriteFormAvailability(checkFormName);
		TestValidation.IsTrue(verifyFavoriteForm, 
				checkFormName+" - FAVORITED FORM is available in Favorite screen", 
				checkFormName+" - FAILED to get form in Favorites screen");

	}

	@Test(description="Favorites || Verify the forms details")
	public void TestCase_30277() {

		FavoritesScreen selectFavoritesTab = commonScreen.selectFavorites();
		TestValidation.IsFalse(selectFavoritesTab.error,
				"OPENED 'Favorites' Tab", 
				"COULD not open 'Favorites' Tab");

		boolean verifyFavoriteForm = selectFavoritesTab.verifyFavoriteFormAvailability(checkFormName);
		TestValidation.IsTrue(verifyFavoriteForm, 
				"FORM is avaiable on Favorite screen - "+checkFormName, 
				"FAILED to get form on Favorites screen - "+checkFormName);

		boolean checkFavoriteFormDetails = selectFavoritesTab.verifyFavoriteDetails();
		TestValidation.IsTrue(checkFavoriteFormDetails, 
				"FAVORITE FORM details are shown on Favorite screen for form - "+checkFormName, 
				"FAILED to verify favorite form details on  Favorites screen for form - "+checkFormName);

	}

	@Test(description="Favorites || Verify the Favorite form count")
	public void TestCase_30278() {

		FavoritesScreen selectFavoritesTab = commonScreen.selectFavorites();
		TestValidation.IsFalse(selectFavoritesTab.error,
				"OPENED 'Favorites' Tab", 
				"COULD not open 'Favorites' Tab");

		boolean verifyFavoriteFormCountCount  = selectFavoritesTab.verifyFavoriteCount(1);
		TestValidation.IsTrue(verifyFavoriteFormCountCount, 
				"VERIFIED Favorites form count - 1",
				"FAILED to verify Favorites form count  - 1");

	}

	@Test(description="Favorites || Verify the Favorite status of the form")
	public void TestCase_30279() {

		FavoritesScreen selectFavoritesTab = commonScreen.selectFavorites();
		TestValidation.IsFalse(selectFavoritesTab.error,
				"OPENED 'Favorites' Tab", 
				"COULD not open 'Favorites' Tab");

		boolean removeFavoriteForm = selectFavoritesTab.unselectFormFavorite(checkFormName);
		TestValidation.IsTrue(removeFavoriteForm, 
				"UNSELECTED Favorite form on Favorites screen - "+checkFormName, 
				"FAILED to unselect favorite form on Favorites screen - "+checkFormName);

		boolean verifyFavoriteForm = selectFavoritesTab.verifyFavoriteFormAvailability(checkFormName);
		TestValidation.IsFalse(verifyFavoriteForm, 
				"FORM is removed from Favorite screen - "+checkFormName, 
				"FAILED to remove form from Favorites screen - "+checkFormName);

		FormsScreen selectFormsTab  = commonScreen.selectForms();
		TestValidation.IsFalse(selectFormsTab.error,
				"OPENED 'Forms' Tab", 
				"COULD not open 'Forms' Tab");

		boolean navigateToMainScren = selectFormsTab.selectFormFavorite(checkFormName);
		TestValidation.IsTrue(navigateToMainScren, 
				"FAVORITE the form'", 
				"FAILED to select form for Favorites");

		selectFavoritesTab = commonScreen.selectFavorites();
		TestValidation.IsFalse(selectFavoritesTab.error,
				"OPENED 'Favorites' Tab", 
				"COULD not open 'Favorites' Tab");

		verifyFavoriteForm = selectFavoritesTab.verifyFavoriteFormAvailability(checkFormName);
		TestValidation.IsTrue(verifyFavoriteForm, 
				"FORM is avaiable in Favorite screen. User is able to select form as favorite & also unfavorite it - "+checkFormName, 
				"FAILED to get form in Favorites screen. User is not able to select form as favorite & also unfavorite it - "+checkFormName);

	}

	@Test(description="Favorites || Verify the results after searching a favorite form")
	public void TestCase_30280() {

		FavoritesScreen selectFavoritesTab = commonScreen.selectFavorites();
		TestValidation.IsFalse(selectFavoritesTab.error,
				"OPENED 'Favorites' Tab", 
				"COULD not open 'Favorites' Tab");

		boolean verifyFavoriteForm = selectFavoritesTab.verifyFavoriteFormAvailability(checkFormName);
		TestValidation.IsTrue(verifyFavoriteForm, 
				"FORM is avaiable in Favorite screen. Form search is working for form - "+checkFormName, 
				"FAILED to get form in Favorites screen - "+checkFormName);
	}


	@Test(description="Favorites || Verify the selected favorite form should be accessible")
	public void TestCase_30282() {		
		HashMap<String, List<String>> allFields = new LinkedHashMap<String, List<String>>();
		allFields.put(NumericFieldName1,Arrays.asList("2"));

		formDetailsPC = new FormDetailsPC();
		formDetailsPC.locationName = locationInstanceValue1;
		formDetailsPC.resourceName = resourceInstanceValue1;
		formDetailsPC.selectLocationResource = false;

		FavoritesScreen selectFavoritesTab = commonScreen.selectFavorites();
		TestValidation.IsFalse(selectFavoritesTab.error,
				"OPENED 'Favorites' Tab", 
				"COULD not open 'Favorites' Tab");

		boolean openForm = forms.selectOpenForm(checkFormName);
		TestValidation.IsTrue(openForm, 
				"SELECTED & opened form - "+checkFormName, 
				"FAILED open form - "+checkFormName);

		formDetailsPC.fieldDetails = allFields;
		boolean submitData = formview.fillDataInForm1(formDetailsPC);
		TestValidation.IsTrue(submitData, 
				"SUBMITTED data for favorite form - "+checkFormName, 
				"FAILED to submit data for favorite form - "+checkFormName);
	}

	@Test(description="Favorites || Verify the form submitted from Favorites should get Saved and Submitted")
	public void TestCase_33576() {

		HashMap<String, List<String>> allFields = new LinkedHashMap<String, List<String>>();
		allFields.put(NumericFieldName1,Arrays.asList("2"));

		formDetailsPC = new FormDetailsPC();
		formDetailsPC.locationName = locationInstanceValue1;
		formDetailsPC.resourceName = resourceInstanceValue1;

		FavoritesScreen selectFavoritesTab = commonScreen.selectFavorites();
		TestValidation.IsFalse(selectFavoritesTab.error,
				"OPENED Favorites Tab", 
				"COULD not open Favorites Tab");

		boolean openForm = forms.selectOpenForm(checkFormName);
		TestValidation.IsTrue(openForm, 
				"SELECTED & opened form - "+checkFormName, 
				"FAILED open form - "+checkFormName);

		boolean selectLocationResource = formview.selectLocationResource(locationInstanceValue1, resourceInstanceValue1);
		TestValidation.IsTrue(selectLocationResource, 
				"SELECTED Location & Resource", 
				"FAILED to select Location & Resource");

		boolean saveForm = formview.saveForm();
		TestValidation.IsTrue(saveForm, 
				"SAVED form - "+checkFormName, 
				"FAILED to save the form - "+checkFormName);

		savedScreen = commonScreen.selectSaved();
		TestValidation.IsFalse(savedScreen.error,
				"OPENED 'Saved' Tab", 
				"COULD not open 'Saved' Tab");

		formDetailsPC.selectLocationResource = false;

		boolean openSavedForm = savedScreen.selectOpenForm(checkFormName);
		TestValidation.IsTrue(openSavedForm, 
				"SELECTED & opened saved form - "+checkFormName, 
				"FAILED to open saved form - "+checkFormName);

		formDetailsPC.fieldDetails = allFields;
		boolean submitData = formview.fillDataInForm1(formDetailsPC);
		TestValidation.IsTrue(submitData, 
				"SUBMITTED data for saved form - "+checkFormName, 
				"FAILED to submit data for saved form - "+checkFormName);

	}


	@Test(description="Favorite || Verify pending submission details in submissions for favorite form submission ")
	public void TestCase_44535() {

		SettingsScreen selectSettingsTab  = commonScreen.selectSettings();
		TestValidation.IsFalse(selectSettingsTab.error,
				"OPENED 'Settings' Tab", 
				"COULD not open 'Settings' Tab");

		boolean changeOfflineModeToggle = selectSettingsTab.changeOfflineModeToggle(true);
		TestValidation.IsTrue(changeOfflineModeToggle, 
				"CHANGED 'Offline Mode' toggle - ON", 
				"FAILED to change''Offline Mode' toggle - ON");

		HashMap<String, List<String>> allFields = new LinkedHashMap<String, List<String>>();
		allFields.put(NumericFieldName1,Arrays.asList("2"));

		formDetailsPC = new FormDetailsPC();
		formDetailsPC.locationName = locationInstanceValue1;
		formDetailsPC.resourceName = resourceInstanceValue1;
		formDetailsPC.fieldDetails = allFields;
		formDetailsPC.allowAttachmentCheck = true;
		formDetailsPC.selectLocationResource = false;

		FavoritesScreen selectFavoritesTab = commonScreen.selectFavorites();
		TestValidation.IsFalse(selectFavoritesTab.error,
				"OPENED 'Favorites' Tab", 
				"COULD not open 'Favorites' Tab");

		boolean openForm = forms.selectOpenForm(checkFormName);
		TestValidation.IsTrue(openForm, 
				"SELECTED & opened form - "+checkFormName, 
				"FAILED open form - "+checkFormName);

		formDetailsPC.fieldDetails = allFields;
		boolean submitData = formview.fillDataInForm1(formDetailsPC);
		TestValidation.IsTrue(submitData, 
				"SUBMITTED data for favorite form - "+checkFormName, 
				"FAILED to submit data for favorite form - "+checkFormName);

		SubmissionsScreen selectSubmissionsTab = new SubmissionsScreen(PCAppDriver);

		boolean verifyPendingSubmissionCount  = selectSubmissionsTab.verifySubmissionCount(1);
		TestValidation.IsTrue(verifyPendingSubmissionCount, 
				"VERIFIED pending submission count - 1",
				"FAILED to verify pending submission count -  1");

		selectSubmissionsTab  = commonScreen.selectSubmissions();
		TestValidation.IsFalse(selectSubmissionsTab.error,
				"OPENED Submissions Tab", 
				"COULD not open Submissions Tab");

		verifyPendingSubmissionCount  = selectSubmissionsTab.verifySubmissionCount(1);
		TestValidation.IsTrue(verifyPendingSubmissionCount, 
				"VERIFIED pending submission count - 1",
				"FAILED to pending submission count - 1");

		boolean clearSubmission = selectSubmissionsTab.clearVerifySubmission(false);
		TestValidation.IsTrue(clearSubmission, 
				"CLEARED Submissions", 
				"FAILED to Clear Submissions");

		boolean verifySubmission = selectSubmissionsTab.verifySubmissionAvailability(checkFormName);
		TestValidation.IsTrue(verifySubmission, 
				"Form submission is shown - "+checkFormName, 
				"FAILED to verify form submission -"+checkFormName);

		selectSettingsTab  = commonScreen.selectSettings();
		TestValidation.IsFalse(selectSettingsTab.error,
				"OPENED 'Settings' Tab", 
				"COULD not open 'Settings' Tab");

		changeOfflineModeToggle = selectSettingsTab.changeOfflineModeToggle(false);
		TestValidation.IsTrue(changeOfflineModeToggle, 
				"CHANGED 'Offline Mode' toggle - OFF", 
				"FAILED to change''Offline Mode' toggle - OFF");

		threadsleep(5000);

		verifyPendingSubmissionCount  = selectSubmissionsTab.verifySubmissionCount(0);
		TestValidation.IsTrue(verifyPendingSubmissionCount, 
				"VERIFIED submission count",
				"FAILED to submission count");

		selectFavoritesTab = commonScreen.selectFavorites();
		TestValidation.IsFalse(selectFavoritesTab.error,
				"OPENED 'Favorites' Tab", 
				"COULD not open 'Favorites' Tab");

		openForm = forms.selectOpenForm(checkFormName);
		TestValidation.IsTrue(openForm, 
				"SELECTED & opened form - "+checkFormName, 
				"FAILED open form - "+checkFormName);

		submitData = formview.fillDataInForm1(formDetailsPC);
		TestValidation.IsTrue(submitData, 
				"SUBMITTED data for favorite form - "+checkFormName, 
				"FAILED to submit data for favorite form - "+checkFormName);

		threadsleep(5000);

		verifyPendingSubmissionCount  = selectSubmissionsTab.verifySubmissionCount(0);
		TestValidation.IsTrue(verifyPendingSubmissionCount, 
				"VERIFIED submission count",
				"FAILED to submission count");

	}

	@Test(description="View attached image || Verify user is able to view image attachment on favorite form submission")
	public void TestCase_48670() {		
		HashMap<String, List<String>> allFields = new LinkedHashMap<String, List<String>>();
		allFields.put(NumericFieldName1,Arrays.asList("2"));

		formDetailsPC = new FormDetailsPC();
		formDetailsPC.locationName = locationInstanceValue1;
		formDetailsPC.resourceName = resourceInstanceValue1;
		formDetailsPC.selectLocationResource = false;
		formDetailsPC.isSubmit = false;

		FavoritesScreen selectFavoritesTab = commonScreen.selectFavorites();
		TestValidation.IsFalse(selectFavoritesTab.error,
				"OPENED 'Favorites' Tab", 
				"COULD not open 'Favorites' Tab");

		boolean openForm = forms.selectOpenForm(checkFormName);
		TestValidation.IsTrue(openForm, 
				"SELECTED & opened form - "+checkFormName, 
				"FAILED open form - "+checkFormName);

		formDetailsPC.fieldDetails = allFields;
		boolean submitData = formview.fillDataInForm1(formDetailsPC);
		TestValidation.IsTrue(submitData, 
				"SUBMITTED data for favorite form - "+checkFormName, 
				"FAILED to submit data for favorite form - "+checkFormName);

		boolean addFormLevelAttachment = formview.addFormLevelAttachment();
		TestValidation.IsTrue(addFormLevelAttachment, 
				"ADDED attachment at form level", 
				"FAILED to add attachment at form level");

		boolean submitForm = formview.submitForm(formDetailsPC.chartClose);
		TestValidation.IsTrue(submitForm, 
				"SUBMISSION is successfull", 
				"FAILED to submit form");
	}

	@AfterClass(alwaysRun = true)
	public void closeBrowser() throws InterruptedException {
		if(PCAppDriver!=null) {
			PCAppDriver.close();
		}
	}

}
