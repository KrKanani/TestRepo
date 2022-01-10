package com.project.safetychain.mobileapp.pageobjects;
 
import java.util.Arrays; 
import java.util.HashMap; 
import java.util.LinkedHashMap;
import java.util.List; 
import org.openqa.selenium.By; 
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement; 
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions; 
import org.openqa.selenium.support.ui.WebDriverWait; 
import com.project.safetychain.api.models.support.TCG_FormCreation_Wrapper;
import com.project.safetychain.api.models.support.TCG_TaskCreation_Wrapper;
import com.project.safetychain.mobileapp.testcases.TCG_SMK_FormSavedFlow; 
import com.project.safetychain.api.models.support.Support.Compliance;
import com.project.safetychain.api.models.support.Support.DPT_FIELD_TYPES;
import com.project.safetychain.api.models.support.Support.DPT_FORM_TYPES;
import com.project.safetychain.api.models.support.Support.FormFieldParams;
import com.project.safetychain.api.models.support.Support.FormFieldParamsCompliance;
import com.project.safetychain.api.models.support.Support.FormParams;
import com.project.safetychain.api.models.support.Support.RESOURCE_TYPES; 
import com.project.safetychain.api.models.support.Support.TaskParams;
import com.project.safetychain.webapp.pageobjects.CommonPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserFormsPage; 
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPageLocators;
import com.project.safetychain.webapp.pageobjects.FSQABrowserRecordsPage;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage; 
import com.project.safetychain.webapp.pageobjects.ManageLocationPage;
import com.project.safetychain.webapp.pageobjects.ManageResourcePage;
import com.project.safetychain.webapp.pageobjects.ResourceDesignerPage; 
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FormDesignParams; 
import com.project.safetychain.webapp.testcases.support.FormOperations;
import com.project.safetychain.webapp.testcases.support.FormOperations.FormDetails;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.utilities.ApiUtils;
import com.project.utilities.CommonMethods;
import com.project.utilities.ControlActions;
import com.project.utilities.ControlActions_MobileApp; 
import io.appium.java_client.AppiumDriver; 
import io.appium.java_client.MobileElement; 
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator; 

public class REG_Notifications extends TestBase {

	AppiumDriver<MobileElement> appiumDriver;
	WebDriverWait wait;
	ControlActions_MobileApp mobControlActions;
	public WebDriver driver;
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
	HomePage homePage;
	REG_Notifications afsaved;
	FSQABrowserRecordsPage fbrp;
	TCG_SMK_FormSavedFlow savedTc;

	public REG_Notifications(AppiumDriver<MobileElement> driver) {
		this.appiumDriver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		mobControlActions = new ControlActions_MobileApp(this.appiumDriver);
	}

	@AndroidFindBy(id = REG_AllFormsSavedLocators.REFRESH_ALL_FORMS)
	public WebElement RefreshAllForms;

	@AndroidFindBy(xpath = REG_AllFormsSavedLocators.REFRESHBTN)
	public WebElement RefreshPopup;

	@AndroidFindBy(id = REG_AllFormsSavedLocators.AllFORMSBTN)
	public WebElement AllFormsBottomClick;

	@AndroidFindBy(xpath = REG_AllFormsSavedLocators.SEARCH_FORMS_TEXTBOX)
	public WebElement SearchFormsTextbox;

	@AndroidFindBy(id = REG_AllFormsSavedLocators.SAVEDBTN)
	public WebElement SavedBottomClick;

	@AndroidFindBy(id = REG_AllFormsSavedLocators.FORMSAVEBTN)
	public WebElement FormSaveClick;

	@AndroidFindBy(xpath = REG_AllFormsSavedLocators.SEARCH_SAVED_FORM_TEXTBOX)
	public WebElement SearchSavedFormTextbox;

	@AndroidFindBy(id = REG_AllFormsSavedLocators.FORMSUBMITBTN)
	public WebElement FormSubmitClick;

	@AndroidFindBy(id = REG_AllFormsSavedLocators.FAVBTN)
	public WebElement FavouriteClick;

	@AndroidFindBy(id = REG_AllFormsSavedLocators.ADDTOFAV)
	public WebElement AddToFavourite;

	@AndroidFindBy(xpath = REG_AllFormsSavedLocators.SEARCH_FAVOURITES_TEXTBOX)
	public WebElement SearchFavouriteTextbox;

	@AndroidFindBy(id = REG_AllFormsSavedLocators.mainLayout)
	public WebElement mainLayout;

	@AndroidFindBy(id = REG_AllFormsSavedLocators.SORTDESC)
	public WebElement sortDesc;

	@AndroidFindBy(id = REG_AllFormsSavedLocators.Main_menu)
	public WebElement mainMenu;

	@AndroidFindBy(id = REG_AllFormsSavedLocators.Close_Main_Menu)
	public WebElement CloseMainMenu;

	@AndroidFindBy(xpath = REG_AllFormsSavedLocators.INBOX_COUNT)
	public WebElement subMenuInbxCnt;

	@AndroidFindBy(xpath = REG_AllFormsSavedLocators.SAVED_COUNT)
	public WebElement subMenuSavedCnt;

	@AndroidFindBy(xpath = REG_AllFormsSavedLocators.FORMS_COUNT)
	public WebElement subMenuFormsCnt;

	@AndroidFindBy(xpath = REG_AllFormsSavedLocators.FAVOURITES_COUNT)
	public WebElement subMenuFavouritesCnt;

	@AndroidFindBy(xpath = REG_AllFormsSavedLocators.SUBMISSIONS_COUNT)
	public WebElement subMenuSubmissionsCnt;

	@AndroidFindBy(xpath = REG_AllFormsSavedLocators.LOCATIONTEXT)
	public WebElement LocationText;

	@AndroidFindBy(xpath = REG_AllFormsSavedLocators.RESOURCETEXT)
	public WebElement ResourceText;

	@AndroidFindBy(xpath = REG_AllFormsSavedLocators.Resource_Search_Field)
	public WebElement resourceSearchField;

	@AndroidFindBy(id = REG_AllFormsSavedLocators.Form_Save)
	public WebElement formSave;

	@AndroidFindBy(xpath = REG_AllFormsSavedLocators.listForms)
	public By listOfForms;

	@AndroidFindBy(id = REG_AllFormsSavedLocators.NUMVALUE)
	public WebElement enterNumVal;

	@AndroidFindBy(id = REG_AllFormsSavedLocators.SAVED_BADGE_BTNCOUNT)
	public WebElement SavedBadgeCount;

	@AndroidFindBy(id = REG_AllFormsSavedLocators.menuListItems)
	public WebElement menuItems;

	@AndroidFindBy(id = REG_AllFormsSavedLocators.TASKCOUNTBADGE)
	public WebElement taskCountBadge;

	@AndroidFindBy(id = REG_AllFormsSavedLocators.SUBMISSIONSCOUNTBADGE)
	public WebElement submissionCountBadge;

	@AndroidFindBy(id = REG_AllFormsSavedLocators.SEARCHBOX)
	public WebElement searchBox;

	@AndroidFindBy(id = REG_AllFormsSavedLocators.SEARCHNAME)
	public WebElement searchByName;

	@AndroidFindBy(id = REG_AllFormsSavedLocators.SEARCHRESOURCE)
	public WebElement searchByResource;

	@AndroidFindBy(id = REG_AllFormsSavedLocators.FORMRESOURCE)
	public WebElement resourceName;

	@AndroidFindBy(id = REG_AllFormsSavedLocators.FORMNAME)
	public WebElement FormNameElement;

	@AndroidFindBy(id = REG_AllFormsSavedLocators.CLOSESEARCH)
	public WebElement closeSearchBtn;

	@AndroidFindBy(id = REG_AllFormsSavedLocators.NORESULTSFOUND)
	public WebElement noResultsFound;

	@AndroidFindBy(id = REG_AllFormsSavedLocators.TASK_COUNT)
	public WebElement taskCount;

	@AndroidFindBy(id = REG_AllFormsSavedLocators.TASKTITLE)
	public WebElement taskTab;

	@AndroidFindBy(id = REG_AllFormsSavedLocators.LOCATIONS)
	public By AllLocations;

	@AndroidFindBy(xpath = REG_AllFormsSavedLocators.SEARCH_INBOX_TEXTBOX)
	public WebElement InboxSearchBox;

	@AndroidFindBy(id = REG_AllFormsSavedLocators.FORMSAVEDDETAILS)
	public WebElement formSavedTS;

	@AndroidFindBy(id = REG_AllFormsSavedLocators.SUBMISSIONSBADGE)
	public WebElement submissionBadge;

	@AndroidFindBy(id = REG_AllFormsSavedLocators.SETTINGTITLE)
	public WebElement SettingTitle;
	@AndroidFindBy(id = REG_AllFormsSavedLocators.OFFLINETOGGLE)
	public WebElement offlineToggleBtn;
	@AndroidFindBy(id = REG_AllFormsSavedLocators.LIMITDATATOGGLE)
	public WebElement limitDataToggleBtn;
	@AndroidFindBy(id = REG_AllFormsSavedLocators.ENABLELOGTOGGLE)
	public WebElement enableLogToggleBtn;
	@AndroidFindBy(id = REG_AllFormsSavedLocators.CLEARSUBBTN)
	public WebElement clearSubBtn;
	@AndroidFindBy(id = REG_AllFormsSavedLocators.LIMITDATAITEMS)
	public WebElement limitDataItems;

	@AndroidFindBy(xpath = REG_AllFormsSavedLocators.OFFLINEMODE)
	public WebElement offlineModeText;
	@AndroidFindBy(xpath = REG_AllFormsSavedLocators.LIMITDATA)
	public WebElement limiDataText;
	@AndroidFindBy(xpath = REG_AllFormsSavedLocators.LIMITDATADURATIOM)
	public WebElement limitDurText;
	@AndroidFindBy(xpath = REG_AllFormsSavedLocators.SELECTDURATION)
	public WebElement selectDurationText;
	@AndroidFindBy(id = REG_AllFormsSavedLocators.SELECTDURATIONANGLEBUTTON)
	public WebElement selectDurationAngle;
	@AndroidFindBy(xpath = REG_AllFormsSavedLocators.ENABLELOGS)
	public WebElement enableLogsText;
	@AndroidFindBy(xpath = REG_AllFormsSavedLocators.CLEARSUBMISSIONS)
	public WebElement clearSubText;

	@AndroidFindBy(id = REG_AllFormsSavedLocators.WORKGRPBTN)
	public WebElement workGroupBtn;

	@AndroidFindBy(id = REG_AllFormsSavedLocators.WRKGRRTEXT)
	public WebElement workGroupTxt;

	@AndroidFindBy(id = REG_AllFormsSavedLocators.STATUSBTN)
	public WebElement statusBtn;

	@AndroidFindBy(id = REG_AllFormsSavedLocators.STATUSTEXT)
	public WebElement statusTxt;

	@AndroidFindBy(id = REG_AllFormsSavedLocators.DUEBYBTN)
	public WebElement dueByBtn;

	@AndroidFindBy(id = REG_AllFormsSavedLocators.DUEBYTEXT)
	public WebElement dueByTxt;

	@FindBy(xpath = REG_AllFormsSavedLocators.ADDATTACHMENT)
	public WebElement Attachemnt;

	@FindBy(className = REG_AllFormsSavedLocators.ATTACHEDFILE)
	public WebElement AttachedFile;

	@FindBy(className = REG_AllFormsSavedLocators.UPLOAD)
	public WebElement uploadFileButton;

	@FindBy(className = REG_AllFormsSavedLocators.UPLOADPERCENT)
	public WebElement uploadPercentStatus;

	@FindBy(xpath = REG_AllFormsSavedLocators.CLOSEUPLOAD)
	public WebElement CloseAttachmentBtn;

	@FindBy(id = UploadImageLocators.PHOTO_SECTION_TITLE)
	public WebElement photoTitle;
	@FindBy(id = UploadImageLocators.FORMS_PHOTO)
	public WebElement selectPhoto;

	@FindBy(id = UploadImageLocators.FORMS_PHOTO_BACK_BTN)
	public WebElement photoBckBtn;

	@FindBy(xpath = FSQABrowserPageLocators.FORM_ROW)
	public WebElement FormRow;

	@FindBy(xpath = FSQABrowserPageLocators.CALLOUT_MNU)
	public WebElement CalloutMenu;

	@FindBy(xpath = FSQABrowserPageLocators.EDIT_FORM_MNU)
	public WebElement EditFormMenu;

	@FindBy(id = REG_AllFormsSavedLocators.FORMBACK)
	public WebElement backForm;

	@FindBy(id = REG_AllFormsSavedLocators.CLICKHEADER)
	public WebElement afterClickHeaderName;

	@FindBy(id = REG_AllFormsSavedLocators.CLICKFORMNAME)
	public WebElement clickFormNmaePresent;

	@FindBy(id = REG_AllFormsSavedLocators.REFRESHINBOXMSGBTN)
	public WebElement refreshInboxBtn;
	@FindBy(id = REG_AllFormsSavedLocators.FILTERICON)
	public WebElement FilterIcon;
	@FindBy(id = REG_AllFormsSavedLocators.SEARCHICON)
	public WebElement SearchIcon;
	@FindBy(id = REG_AllFormsSavedLocators.groupHeaderTitle)
	public WebElement TaskHeaderTitle;

	@FindBy(id = REG_AllFormsSavedLocators.INBOXFILTERHEADER)
	public WebElement inboxFilterHeader;
	@FindBy(id = REG_AllFormsSavedLocators.INBOXFILTERCROSS)
	public WebElement InboxFilterCrossBtn;
	@FindBy(id = REG_AllFormsSavedLocators.FILTERCLEARBTN)
	public WebElement filterClear;
	@FindBy(id = REG_AllFormsSavedLocators.FILTERBTNINSIDE)
	public WebElement filter;

	@FindBy(xpath = REG_AllFormsSavedLocators.ASSIGNEDTOFILTER)
	public WebElement assignedToFilter;
	@FindBy(xpath = REG_AllFormsSavedLocators.STATUSFILTER)
	public WebElement statusFilter;
	@FindBy(xpath = REG_AllFormsSavedLocators.DUEBYFILTER)
	public WebElement dueByFilter;
	@FindBy(xpath = REG_AllFormsSavedLocators.ASSIGNEDANGLEBTN)
	public WebElement assignedToDDAngle;

	@FindBy(xpath = REG_AllFormsSavedLocators.STATUSANGLEBTN)
	public WebElement statusDDAngle;
	@FindBy(xpath = REG_AllFormsSavedLocators.DUEBYANGLEBTN)
	public WebElement dueByDDAngle;

	@FindBy(id = REG_AllFormsSavedLocators.RESOURCEHEADER)
	public WebElement resourceHeader;
	@FindBy(id = REG_AllFormsSavedLocators.RESOURCEVALUE)
	public WebElement resourceValue;
	@FindBy(id = REG_AllFormsSavedLocators.LOCATIONHEADER)
	public WebElement locationHeader;
	@FindBy(id = REG_AllFormsSavedLocators.LOCATIONVALUE)
	public WebElement locationValue;

	@FindBy(id = REG_AllFormsSavedLocators.RESOURCESEARCH)
	public WebElement resourceSearchText;
	@FindBy(id = REG_AllFormsSavedLocators.AVAILABLERESOURCES)
	public WebElement availableResources;
	@FindBy(id = REG_AllFormsSavedLocators.RESOURCES)
	public WebElement resources;

	public static String locationCategoryValue;
	public static String locationInstanceValue1;
	public static String locationInstanceValue2;
	public static String resourceCategoryValue;
	public static String resourceInstanceValue1;
	public static String resourceInstanceValue2;
	public static String chkFreeTxt, ParaTxt, chkSelectOne, SelectMultiple, Numeric, chkDate, chkTime, chkDateTime;

	String formtype = "Check";
	String modifiedby;
	public String FormName = null;
	public String TaskName;
	public String WGName;

	public void CompliantNonCompliantTaskCreation() {
		try {

			FormName = CommonMethods.dynamicText("API_Form");
			TaskName = CommonMethods.dynamicText("API_Task");
			WGName = CommonMethods.dynamicText("API_WG");

			TCG_FormCreation_Wrapper formCreationWrapper = new TCG_FormCreation_Wrapper();
			TCG_TaskCreation_Wrapper taskCreationWrapper = new TCG_TaskCreation_Wrapper();

			apiUtils = new ApiUtils();

			locationCategoryValue = CommonMethods.dynamicText("LCat");
			locationInstanceValue1 = CommonMethods.dynamicText("LInst1");
			locationInstanceValue2 = CommonMethods.dynamicText("LInst2");
			resourceCategoryValue = CommonMethods.dynamicText("RCat");
			resourceInstanceValue1 = CommonMethods.dynamicText("RInst1");
			resourceInstanceValue2 = CommonMethods.dynamicText("RInst2");

			// Field Names
			chkFreeTxt = "FreeText";
			ParaTxt = "Paragraph";
			chkSelectOne = "SelectOne";
			Numeric = "Numeric";
			chkDate = "Date";
			chkTime = "Time";
			chkDateTime = "Date&Time";
			SelectMultiple = "MutiSelect";

			// API Implementation

			FormFieldParams numericField = new FormFieldParams();
			numericField.DPTFieldType = DPT_FIELD_TYPES.NUMERIC;
			numericField.Name = Numeric;
			numericField.AllowComments = "true";
			numericField.AllowCorrection = "true";
			numericField.ShowIsResolved = "true";
			numericField.PredefinedCorrections = Arrays.asList("OPTION - 1", "OPTION - 2");
			numericField.ShowMinMax = "true";

			FormFieldParams freeTextField = new FormFieldParams();
			freeTextField.DPTFieldType = DPT_FIELD_TYPES.FREE_TEXT;
			freeTextField.Name = chkFreeTxt;

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
			fp.formElements = Arrays.asList(numericField, freeTextField);
			logInfo("fp.formElements = " + fp.formElements);

			HashMap<String, List<String>> resourceCatMap = new HashMap<String, List<String>>();
			resourceCatMap.put(resourceCategoryValue, Arrays.asList(resourceInstanceValue1, resourceInstanceValue2));
			fp.CustomerResources = resourceCatMap;

			HashMap<String, List<String>> LocationMap = new HashMap<String, List<String>>();
			LocationMap.put(locationCategoryValue, Arrays.asList(locationInstanceValue1, locationInstanceValue2));

			// Location, Resource Creation and linking
			List<String> userList = Arrays.asList(mobileAppUsername02, mobileAppUsername);

			HashMap<String, String> locResMap = formCreationWrapper.Resource_Location_CreationWrapper(resourceCatMap,
					LocationMap, true, userList, fp.ResourceType);

			String locInstId = locResMap.get(locationInstanceValue1);
			String ResInstId = locResMap.get(resourceInstanceValue1);

			// Form Creation and Validation call
			formCreationWrapper.API_Wrapper_Forms(fp);

			HashMap<String, Compliance> complianceValuesMap = new HashMap<String, Compliance>();
			String resource = "Customers > " + resourceCategoryValue + " > " + resourceInstanceValue1;
			Compliance compliance = new Compliance();
			compliance.Min = "10";
			compliance.Max = "100";
			compliance.Target = "50";
			compliance.UOM = "$";
			compliance.Name = numericField.Name;
			compliance.fieldType = DPT_FIELD_TYPES.NUMERIC;
			complianceValuesMap.put(resource, compliance);
			complianceValuesMap.put("Default", compliance);
			compliance.IsDefault = "true";

			logInfo("resource List " + resource);
			logInfo("Compliance Values " + complianceValuesMap);

			FormFieldParamsCompliance ffpc = new FormFieldParamsCompliance();
			ffpc.fieldNames = Arrays.asList(numericField.Name);
			ffpc.complianceList = complianceValuesMap;
			formCreationWrapper.API_Wrapper_Forms_Compliance(fp, ffpc);

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
			taskCreationWrapper.create_Task_Wrapper(tp);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public String OfflineTaskSubmissionFunctionality() throws InterruptedException {
		 
		HomePage homePage = new HomePage(appiumDriver); 
		TaskPage InboxPageObj = new TaskPage(appiumDriver);
		SavedPage SavedPageObj = new SavedPage(appiumDriver);

		// Creating Task
		CompliantNonCompliantTaskCreation();
		ControlActions_MobileApp.waitForVisibility(SearchFormsTextbox, 30);
		ControlActions_MobileApp.swipeScreen("DOWN");
		Thread.sleep(5000);

		Boolean clicksettings = homePage.ClickSubMenu(homePage.settingsSubMenu);
		TestValidation.IsTrue(clicksettings, "Clicked on submissions subMenu Successfully",
				"Failed to click Submissions subMenu");
		boolean isOffline = SavedPageObj.offlineButtonEnabled(true);
		TestValidation.IsTrue(isOffline, "offline Mode Activated", "Failed to Activate offline Mode");
		TestValidation.IsTrue(true, "Verified offline Toggle button click Successfully",
				"Failed to click offline toggle button from Settings subMenu");
		ControlActions_MobileApp.click(SavedPageObj.settingsBckBtn);
		TestValidation.IsTrue(true, "Clicked on Main Menu Successfully", "Failed to click on Main menu");

		boolean clickInboxMenu = homePage.ClickMenu(homePage.inboxSubMenu);

		TestValidation.IsTrue(clickInboxMenu, "Clicked on Inbox Sub Menu", "Failed to click Inbox Submenu");

		boolean searchForm = homePage.SearchTask(TaskName);
		TestValidation.IsTrue(searchForm, "Latest Created Task is present in Inbox",
				"Latest Created Task is not present in Inbox");
		WebElement formCheck = ControlActions_MobileApp.perform_GetElementByXPath(HomePageLocators.Form_Name,
				"FormNameLocator", TaskName);
		ControlActions_MobileApp.waitForVisibility(formCheck, 60);
		ControlActions_MobileApp.click(formCheck);
		logInfo("Clicked on Task = " + TaskName);
		Thread.sleep(5000);

		Boolean txtNumeric = enterFieldValue(SavedPageObj.NumericTxt, "10");
		TestValidation.IsTrue(txtNumeric, "Numeric Field value entered successfully", "Failed to enter field value");

		Boolean txtFT = enterFieldValue(SavedPageObj.freeText, "FreeText");
		TestValidation.IsTrue(txtFT, "Numeric Field value entered successfully", "Failed to enter field value");

		ControlActions_MobileApp.waitForVisibility(InboxPageObj.taskSubmit);
		ControlActions_MobileApp.WaitforelementToBeClickable(InboxPageObj.taskSubmit);
		InboxPageObj.taskSubmit.click();
		LinkedHashMap<String, String> FieldTypeValues = new LinkedHashMap<String, String>(); 
		FieldTypeValues.put(Numeric, "10");
		FieldTypeValues.put(chkFreeTxt, "FreeText");
		boolean isFormPresentInboxOff = (noResultsFound.isDisplayed()
				&& (noResultsFound.getText().trim().equalsIgnoreCase("No Results Found")));
		TestValidation.IsTrue(isFormPresentInboxOff, "After task submission task not present in Inbox",
				"Task still present in inbox even after submission");

		ControlActions_MobileApp.WaitforelementToBeClickable(submissionBadge);
		submissionBadge.click();
		ControlActions_MobileApp.waitForVisibility(searchBox, 20);
		ControlActions_MobileApp.click(SavedPageObj.sortBtn);
		boolean clickSubBadge = searchBox.isDisplayed() && searchBox.getText().equalsIgnoreCase("Search Submissions");

		TestValidation.IsTrue(clickSubBadge, "Clicked on Submission badge", "Failed to click submission badge");

		boolean formStat = SavedPageObj.checkFormStatusFromSubmissionsSubMenu(FormName, "PENDING");

		TestValidation.IsTrue(formStat, "Form status verified succesfully in offline mode",
				"Failed to verify form status in offline mode");

		boolean ResubmitVisibleOffline = false;
		if (SavedPageObj.resubmitBtn.isDisplayed()) {
			ResubmitVisibleOffline = true;
		}
		TestValidation.IsTrue(ResubmitVisibleOffline, "Resubmit All button are present at bottom",
				"Resubmit All  button is not present");
		SavedPageObj.clickFormFromSubmissionsScreen(FormName); 
		boolean verifyUpdatedValueSub = SavedPageObj.verifyFieldValues(FieldTypeValues);
		TestValidation.IsTrue(verifyUpdatedValueSub, "Verified submissions value successfully",
				"Failed to verify submissions value");
		backForm.click();

		Boolean clickSettingsSubMnu = homePage.ClickSubMenu(homePage.settingsSubMenu);
		TestValidation.IsTrue(clickSettingsSubMnu, "clicked on settings subMenu successfully",
				"Failed to click settings submenu");
		ControlActions_MobileApp.click(SavedPageObj.offlineToggle);
		ControlActions_MobileApp.click(SavedPageObj.settingsBckBtn);
		ControlActions_MobileApp.waitForVisibility(homePage.submissionsSubMenu, 160);
		ControlActions_MobileApp.WaitforelementToBeClickable(homePage.submissionsSubMenu);
		ControlActions_MobileApp.click(homePage.submissionsSubMenu);
		Boolean clickResubmitAll = SavedPageObj.clickResubmitAll();
		TestValidation.IsTrue(clickResubmitAll, "Resubmit All button clicked successfully",
				"Failed to click Resubmit all button");
		Thread.sleep(5000);
		boolean formStatOnline = SavedPageObj.checkFormStatusFromSubmissionsSubMenu(FormName, "RECEIVED");

		TestValidation.IsTrue(formStatOnline, "Form status verified succesfully in Online mode",
				"Failed to verify form status in Online mode");

		boolean ResubmitVisible = false;

		try {
			WebDriverWait wait = new WebDriverWait(driver, 5);
			wait.until(ExpectedConditions.visibilityOf(SavedPageObj.resubmitBtn));
		} catch (Exception e) {
			if (SavedPageObj.clearAllBtn.isDisplayed() && SavedPageObj.clearAllBtn.isEnabled()) {
				ResubmitVisible = true;
			}
		}

		TestValidation.IsTrue(ResubmitVisible, "Resubmit All button is not present",
				"Resubmit All button is still present");

		SavedPageObj.clickFormFromSubmissionsScreen(FormName);

		boolean verifyUpdatedValueSubOn = SavedPageObj.verifyFieldValues(FieldTypeValues);
		TestValidation.IsTrue(verifyUpdatedValueSubOn,
				"VERIFIED updated value for  all fields in submitted form in Online mode",
				"Failed to verify updated values for all fields in submitted form in Online mode");
		backForm.click(); 
		ControlActions_MobileApp.WaitforelementToBeClickable(taskCountBadge);
		taskCountBadge.click();
		ControlActions_MobileApp.waitForVisibility(searchBox, 20);
		@SuppressWarnings("unused")
		boolean clickInboxBadge = searchBox.isDisplayed() && searchBox.getText().equalsIgnoreCase(TaskName);

		boolean isFormPresentInbox = (noResultsFound.isDisplayed()
				&& (noResultsFound.getText().trim().equalsIgnoreCase("No Results Found")));
		TestValidation.IsTrue(isFormPresentInbox, "After task submission task not present in Inbox",
				"Task still present in inbox even after submission");
		return TaskName;
	}

	public String TaskSubmissionFunctionality(String numericValue) throws InterruptedException {
	 
		HomePage homePage = new HomePage(appiumDriver);
		 TaskPage InboxPageObj = new TaskPage(appiumDriver);
		SavedPage SavedPageObj = new SavedPage(appiumDriver);

		CompliantNonCompliantTaskCreation();
		ControlActions_MobileApp.waitForVisibility(SearchFormsTextbox, 30);
		ControlActions_MobileApp.swipeScreen("DOWN");
		Thread.sleep(5000);
		boolean clickInboxMenu = homePage.ClickSubMenu(homePage.inboxSubMenu);

		TestValidation.IsTrue(clickInboxMenu, "Clicked on Inbox Sub Menu", "Failed to click Inbox Submenu");

		boolean searchForm = homePage.SearchTask(TaskName);
		TestValidation.IsTrue(searchForm, "Latest Created Task is present in Inbox",
				"Latest Created Task is not present in Inbox");
		WebElement formCheck = ControlActions_MobileApp.perform_GetElementByXPath(HomePageLocators.Form_Name,
				"FormNameLocator", TaskName);
		ControlActions_MobileApp.waitForVisibility(formCheck, 60);
		ControlActions_MobileApp.click(formCheck);
		logInfo("Clicked on Task = " + TaskName);
		Thread.sleep(5000);

		String flComments = "Numeric Field Comments";
		String flCorrection = "OPTION - 1";

		boolean txtNumeric = enterFieldValue(SavedPageObj.NumericTxt, numericValue);
		TestValidation.IsTrue(txtNumeric, "Numeric Field value entered successfully", "Failed to enter field value");
		Thread.sleep(4000);
		enterFieldValue(SavedPageObj.flComments, flComments);
		appiumDriver.hideKeyboard();
		if (Integer.parseInt(numericValue) < 10 || Integer.parseInt(numericValue) >= 100) {
			System.out.println("Non compliant value");
			SavedPageObj.selectPreDefinedCorrectionValues("SelectOne", flCorrection);
			ControlActions_MobileApp.swipe(400, 700, 400, 100);
			SavedPageObj.selectResolvedField(SavedPageObj.flResolvedYes);
		}
		ControlActions_MobileApp.swipe(400, 700, 400, 100);
		Boolean txtFT = enterFieldValue(SavedPageObj.freeText, "FreeText");
		TestValidation.IsTrue(txtFT, "freeText Field value entered successfully", "Failed to enter field value");

		ControlActions_MobileApp.waitForVisibility(InboxPageObj.taskSubmit);
		ControlActions_MobileApp.WaitforelementToBeClickable(InboxPageObj.taskSubmit);
		InboxPageObj.taskSubmit.click();

		Thread.sleep(4000);

		LinkedHashMap<String, String> FieldTypeValues = new LinkedHashMap<String, String>(); 
		FieldTypeValues.put(Numeric, numericValue);
		FieldTypeValues.put(chkFreeTxt, "FreeText");

		ControlActions_MobileApp.WaitforelementToBeClickable(submissionBadge);
		submissionBadge.click();
		ControlActions_MobileApp.waitForVisibility(searchBox, 20);
		boolean clickSubBadge = searchBox.isDisplayed() && searchBox.getText().equalsIgnoreCase("Search Submissions");

		TestValidation.IsTrue(clickSubBadge, "Clicked on Submission badge", "Failed to click submission badge");
		SavedPageObj.sortBtn.click();
		SavedPageObj.checkFormStatusFromSubmissionsSubMenu(FormName, "RECEIVED");
		SavedPageObj.clickFormFromSubmissionsScreen(FormName);

		boolean verifyUpdatedValueSub = SavedPageObj.verifyFieldValues(FieldTypeValues);

		TestValidation.IsTrue(verifyUpdatedValueSub, "Verified Correction Details From Submissions Screen",
				"Failed to Verify Correction Details From Submissions Screen");
		if (Integer.parseInt(numericValue) < 10 && Integer.parseInt(numericValue) >= 100) {
			boolean verifyDetails = SavedPageObj.verifyCorrectionDetailsFromSubmissionScreen(flComments, flCorrection);
			TestValidation.IsTrue(verifyDetails, "Verified Correction Details From Submissions Screen",
					"Failed to Verify Correction Details From Submissions Screen");
		}
		backForm.click(); 
		ControlActions_MobileApp.WaitforelementToBeClickable(taskCountBadge);
		taskCountBadge.click();
		ControlActions_MobileApp.waitForVisibility(searchBox, 20);
		@SuppressWarnings("unused")
		boolean clickInboxBadge = searchBox.isDisplayed() && searchBox.getText().equalsIgnoreCase(TaskName);

		boolean isFormPresentInbox = (noResultsFound.isDisplayed()
				&& (noResultsFound.getText().trim().equalsIgnoreCase("No Results Found")));
		TestValidation.IsTrue(isFormPresentInbox, "After task submission task not present in Inbox",
				"Task still present in inbox even after submission");
		return TaskName;
	}

	public boolean SearchForm(String Formname, WebElement searchField) {
		boolean isPresent = false;
		try {
			WebElement resource = null;

			ControlActions_MobileApp.waitForVisibility(searchField, 60);
			ControlActions_MobileApp.ClearText(searchField);
			ControlActions_MobileApp.actionEnterText(searchField, Formname);
			System.out.println("Text has Entered");
			Thread.sleep(6000);

			resource = ControlActions_MobileApp.perform_GetElementByXPath(SavedPageLocators.Form_Name, "FormNameLocator",
					Formname);
			isPresent = ControlActions_MobileApp.isElementDisplayed(resource);
 
			return isPresent;
		} catch (Exception ex) {
			logInfo("Failed to Search" + ex.getMessage());
			return isPresent;
		}

	}

	public boolean enterFieldValue(WebElement FieldType, String FieldValue) {
		try {

			ControlActions_MobileApp.waitForVisibility(FieldType, 80);
			ControlActions_MobileApp.ClearText(FieldType);
			ControlActions_MobileApp.actionEnterText(FieldType, FieldValue);
			// ControlActions_MobileApp.performTabAction();
			logInfo("Field Value has Entered");

			return true;
		} catch (Exception ex) {
			logInfo("Failed to Search resource" + ex.getMessage());
			return false;
		}
	}

}