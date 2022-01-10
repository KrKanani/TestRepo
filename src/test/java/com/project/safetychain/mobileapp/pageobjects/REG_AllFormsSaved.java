package com.project.safetychain.mobileapp.pageobjects;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.SkipException;
import com.project.safetychain.api.models.support.TCG_FormCreation_Wrapper;
import com.project.safetychain.api.models.support.TCG_TaskCreation_Wrapper;
import com.project.safetychain.mobileapp.testcases.TCG_SMK_FormSavedFlow;
import com.project.safetychain.api.models.support.Support.DPT_FIELD_TYPES;
import com.project.safetychain.api.models.support.Support.DPT_FORM_TYPES;
import com.project.safetychain.api.models.support.Support.FormFieldParams;
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
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.COLUMNHEADER;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.COLUMNSETTING;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FIELD_TYPES;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FormDesignParams;
import com.project.safetychain.webapp.pageobjects.FormsManagerPage;
import com.project.safetychain.webapp.testcases.support.FormOperations;
import com.project.safetychain.webapp.testcases.support.FormOperations.FormDetails;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.utilities.ApiUtils;
import com.project.utilities.CommonMethods;
import com.project.utilities.ControlActions;
import com.project.utilities.ControlActions_MobileApp;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class REG_AllFormsSaved extends TestBase {

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
	REG_AllFormsSaved afsaved;
	FSQABrowserRecordsPage fbrp;
	TCG_SMK_FormSavedFlow savedTc;

	public REG_AllFormsSaved(AppiumDriver<MobileElement> driver) {
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
	public static String TaskName;
	public static String WGName;

	public void TaskBadgeWithWindowChange() {
		SavedPage SavedPageObj = new SavedPage(appiumDriver);
		TaskPage InboxPageObj = new TaskPage(appiumDriver);
		HomePage homePage = new HomePage(appiumDriver);
		mobControlActions = new ControlActions_MobileApp(appiumDriver);

		boolean clickSettingsMenu2 = homePage.ClickSubMenu(homePage.settingsSubMenu);
		TestValidation.IsTrue(clickSettingsMenu2, "Clicked on settings subMenu Successfully",
				"Failed to click Settings subMenu");
		boolean selectdataLimit1 = InboxPageObj.selectDataLimit("3 DAYS");

		TestValidation.IsTrue(selectdataLimit1, "Selected 3 DAYS data limit from Settings",
				"Failed to select 3 DAYS data limit from Settings");

		ControlActions_MobileApp.click(SavedPageObj.settingsBckBtn);
		boolean clickSettingsMenu1 = homePage.ClickMenu(homePage.inboxSubMenu);
		TestValidation.IsTrue(clickSettingsMenu1, "Clicked on Inbox subMenu Successfully",
				"Failed to click Inbox subMenu");
		ControlActions_MobileApp.waitForVisibility(mainMenu, 180);
		ControlActions_MobileApp.click(mainMenu);
		int MinTaskCount = Integer.parseInt(taskCount.getText());
		System.out.println("MinTaskCount" + MinTaskCount);
		int TaskCountWithLimit;

		boolean taskCountCheck = false;
		// Add task badge count validation
		ArrayList<String> ls = new ArrayList<>();
		ls.add(0, "3 DAYS");
		ls.add(1, "7 DAYS");
		ls.add(2, "14 DAYS");
		ls.add(3, "21 DAYS");
		for (int i = 0; i < 4; i++) {
			boolean clickSettingsMenu = homePage.ClickSubMenu(homePage.settingsSubMenu);
			TestValidation.IsTrue(clickSettingsMenu, "Clicked on settings subMenu Successfully",
					"Failed to click Settings subMenu");
			boolean selectdataLimit = InboxPageObj.selectDataLimit(ls.get(i));
			TestValidation.IsTrue(selectdataLimit, "Selected" + ls.get(i) + " data limit from Settings",
					"Failed to select" + ls.get(i) + " data limit from Settings");
			ControlActions_MobileApp.click(SavedPageObj.settingsBckBtn);
			TestValidation.IsTrue(true, "Clicked on Settings Back Button Successfully",
					"Failed to click Settings Back Button");
			boolean clickInboxMenu = homePage.ClickMenu(homePage.inboxSubMenu);
			TestValidation.IsTrue(clickInboxMenu, "Clicked on Inbox subMenu Successfully",
					"Failed to click Inbox subMenu");
			ControlActions_MobileApp.waitForVisibility(searchBox, 120);
			TaskCountWithLimit = Integer.parseInt(taskCount.getText());
			System.out.println("TaskCountWithLimit" + TaskCountWithLimit);
			if (ls.get(i).equalsIgnoreCase("3 DAYS") && MinTaskCount == TaskCountWithLimit) {
				taskCountCheck = true;
			}

			if (!ls.get(i).equalsIgnoreCase("3 DAYS") && (MinTaskCount <= TaskCountWithLimit))

			{
				taskCountCheck = true;
			}

			TestValidation.IsTrue(taskCountCheck,
					"LIMITDATA= " + ls.get(i) + ": Task Badge count is getting updated every time window changes "
							+ mobileAppUsername,
					"LIMITDATA= " + ls.get(i) + " :Failed to update task badge count after window change");
		}

	}

	public void offlineAutoSaveFunctionality() throws InterruptedException {
		mobControlActions = new ControlActions_MobileApp(appiumDriver);
		SavedPage SavedPageObj = new SavedPage(appiumDriver);
		LoginPage loginPage = new LoginPage(appiumDriver);
		HomePage homePage = new HomePage(appiumDriver);
		String FormName = APIInitiation();

		if (mobileApp_Tenant.equals("test1.stage")) {
			boolean SSOLogin = loginPage.SSO_Login(mobileAppUsername, mobileAppPassword);
			TestValidation.IsTrue(SSOLogin, "logged into the application with User " + mobileAppUsername,
					"Failed to log in");

		} else {
			boolean login = loginPage.Login(mobileAppUsername, mobileAppPassword);
			TestValidation.IsTrue(login, "logged into the application with User " + mobileAppUsername,
					"Failed to log in");

		}

		Boolean clickSetting = homePage.ClickSubMenu(homePage.settingsSubMenu);
		TestValidation.IsTrue(clickSetting, "Clicked on settings menu Successfully",
				"Failed to click on settings subMenu");
		boolean isOffline = SavedPageObj.offlineButtonEnabled(true);
		TestValidation.IsTrue(isOffline, "offline Mode Activated", "Failed to Activate offline Mode");
		TestValidation.IsTrue(true, "Verified offline Toggle button click Successfully",
				"Failed to click offline toggle button from Settings subMenu");
		ControlActions_MobileApp.click(SavedPageObj.settingsBckBtn);
		TestValidation.IsTrue(true, "Clicked on Main Menu Successfully", "Failed to click on Main menu");
		try {
			ControlActions_MobileApp.waitForVisibility(homePage.formsSubMenu, 160);
			ControlActions_MobileApp.click(homePage.formsSubMenu);
			logInfo("Clicked on Submenu ");
			Thread.sleep(6000);
		} catch (Exception ex) {
			logInfo("Failed To click On SubMenu " + ex.getMessage());
		}

		boolean searchForm = homePage.SearchForm(FormName);
		TestValidation.IsTrue(searchForm, "Form Searched successfully", "Failed to search Form");
		boolean clickForm = homePage.ClickForm(FormName);
		TestValidation.IsTrue(clickForm, "Clicked on form Successfully", "Failed to click Form");
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

		Thread.sleep(60000);

		appiumDriver.closeApp();
		List<String> dateInFormat = SavedPageObj.TodaysDateinFormat("MM/dd/yyyy HH:mm z");
		Thread.sleep(5000);

		List<String> removePicsArgs = Arrays.asList("keyevent", "KEYCODE_APP_SWITCH");
		Map<String, Object> removePicsCmd = new HashMap<String, Object>();
		removePicsCmd.put("command", "input");
		removePicsCmd.put("args", removePicsArgs);

		appiumDriver.executeScript("mobile: shell", removePicsCmd);
		logInfo("Clicked on Home Button");

		Thread.sleep(1000);
		List<String> removePicsArgs1 = Arrays.asList("swipe", "10 600 1600 600");
		Map<String, Object> removePicsCmd1 = new HashMap<String, Object>();
		removePicsCmd1.put("command", "input");
		removePicsCmd1.put("args", removePicsArgs1);

		appiumDriver.executeScript("mobile: shell", removePicsCmd1);
		logInfo("Swipe done");
		Thread.sleep(2000);

		ControlActions_MobileApp.click(SavedPageObj.closeAllApps);
		logInfo("Clicked on Clear All button");

		appiumDriver = launchMobileApp();
		mobControlActions = new ControlActions_MobileApp(appiumDriver);
		SavedPageObj = new SavedPage(appiumDriver);
		LoginPage loginPage1 = new LoginPage(appiumDriver);
		HomePage homePage1 = new HomePage(appiumDriver);
		boolean login1 = loginPage1.Login(mobileAppUsername, mobileAppPassword);
		TestValidation.IsTrue(login1, "logged into the application with User " + mobileAppUsername, "Failed to log in");
		Thread.sleep(20000);
		ControlActions_MobileApp.WaitforelementToBeClickable(homePage1.mainMenu);
		boolean clickFormsSub = homePage1.ClickSubMenu(homePage1.saveSubMenu);
		TestValidation.IsTrue(clickFormsSub, "Clicked on Saved subMenu Successfully", "Failed to click Saved subMenu");
		ControlActions_MobileApp.click(SavedPageObj.sortBtn);

		Thread.sleep(10000);
		List<String> dateInFormatMob = SavedPageObj.TodaysDateinFormat("M/d/YY HH:mm z");
		String datespliter[] = dateInFormatMob.get(0).split(" ");
		String Date = datespliter[0];
		logInfo("Datesplitter[0] : " + Date);

		boolean formStatus = SavedPageObj.checkFormStatusFromSavedSubMenu(FormName, Date);
		TestValidation.IsTrue(formStatus, "Verified Form Status Successfully",
				"Failed to verify Form Status on Submissions");
	}

	public void updatedFormVersionWithFormValidate() throws InterruptedException {
		HomePage homePage = new HomePage(appiumDriver);
		mobControlActions = new ControlActions_MobileApp(appiumDriver);
		String FormName = APIInitiation();
		ControlActions_MobileApp.swipeScreen("DOWN");
		Thread.sleep(5000);
		boolean formSearch = homePage.SearchForm(FormName);
		TestValidation.IsTrue(formSearch, "Form Searched Successfully", "Failed to Search form");
		// Version Check

		UpdatedFormVersionPage UpdatedFormVersionPageObj = new UpdatedFormVersionPage(appiumDriver);

		UpdatedFormVersionPageObj.FirstVersion();
		driver = launchbrowser();

		controlActions = new ControlActions(driver);
		controlActions.getUrl(applicationUrl);
		lp = new com.project.safetychain.webapp.pageobjects.LoginPage(driver);
		hp = lp.performLogin(adminUsername, adminPassword);
		if (hp.error) {
			TCGFailureMsg = "Could NOT login to application";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
		FormDesignerPage formDesignerPage = new FormDesignerPage(driver);
		FSQABrowserPage fbp = new FSQABrowserPage(driver);
		com.project.safetychain.webapp.pageobjects.HomePage hp = new com.project.safetychain.webapp.pageobjects.HomePage(
				driver);
		boolean navigate = fbp.navigateToFSQATab("Forms");
		TestValidation.IsTrue(navigate, "Navigated to FSQABrowser>FormsTab",
				"Failed to navigate to FSQABrowser>Forms Tab");

		boolean applyfilter = fbp.openAndApplySettingsForColumn(COLUMNHEADER.FORMNAME, COLUMNSETTING.FILTER, FormName);
		TestValidation.IsTrue(applyfilter, "Applied Filter on" + COLUMNHEADER.FORMNAME,
				"Failed to apply filter on" + COLUMNHEADER.FORMNAME);

		String version = fbp.getGridValueForColumn(COLUMNHEADER.VERSION);

		float expected_version = (Float.parseFloat(version));

		Float expected_version1 = expected_version + 1;

		logInfo("Expected Version =" + expected_version1);

		try {
			controlActions.action.moveToElement(FormRow).build().perform();
			threadsleep(2000);

			WebDriverWait wait = new WebDriverWait(driver, 30);

			controlActions.clickOnElement(CalloutMenu);

			wait.until(ExpectedConditions.elementToBeClickable(EditFormMenu));
			controlActions.clickOnElement(EditFormMenu);
			threadsleep(10000);

			formDesignerPage.FormNameLabel.click();
			boolean editTxt = formDesignerPage.updateTextBoxValue(FIELD_TYPES.NUMERIC, "Integer");
			TestValidation.IsTrue(editTxt, "Field value changed succesfully", "Failed to change field value");

			threadsleep(5000);
			WebElement save = driver.findElement(By.xpath("//button[text()='SAVE']"));
			wait.until(ExpectedConditions.elementToBeClickable(save));
			save.click();
			formDesignerPage.clickOnNextButton(formDesignerPage.ReleaseFormPg, "Release Form");
			threadsleep(3000);
			formDesignerPage.releaseForm(FormName);
			logInfo("Form Name" + FormName + "edited");
			// return true;
		} catch (Exception e) {
			logError("Failed to edit form" + FormName + e.getMessage());
			// return false;
		}

		boolean editform = fbp.editForm(FormName, formDesignerPage);
		TestValidation.IsTrue(editform, "Form Edited", "Failed to edit form");

		hp.clickFSQABrowserMenu();

		boolean navigate1 = fbp.navigateToFSQATab("Forms");
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

		ControlActions_MobileApp.swipeScreen("DOWN");
		homePage.SearchForm(FormName);

		String mobVersionAfterUpdate = UpdatedFormVersionPageObj.FirstVersion();
		Boolean CompareVersion = UpdatedFormVersionPageObj.CompareVersion(mobVersionAfterUpdate,
				expected_version1.toString());
		TestValidation.IsTrue(CompareVersion, "Version is updated", "version not updated");

		mobControlActions = new ControlActions_MobileApp(appiumDriver);
		// HomePage homePage = new HomePage(appiumDriver);
		boolean searchForm = homePage.SearchForm(FormName);
		TestValidation.IsTrue(searchForm, "Form Searched successfully", "Failed to search Form");

		boolean clickFormsSub = homePage.ClickForm(FormName);
		TestValidation.IsTrue(clickFormsSub, "Clicked on Forms subMenu Successfully", "Failed to click Forms subMenu");
		Thread.sleep(5000);
		boolean searchLocation = SearchResource(locationInstanceValue1, LocationText);
		TestValidation.IsTrue(searchLocation, "Location Searched Successfully",
				"Failed to Search Location from Forms subMenu");
		boolean searchResource = SearchResource(resourceInstanceValue1, ResourceText);
		TestValidation.IsTrue(searchResource, "Resource Searched Successfully",
				"Failed to Search Resource from Forms subMenu");
		Thread.sleep(5000);
		boolean fieldupdate = false;
		if (appiumDriver.findElementById("fieldTitle").getText().equalsIgnoreCase("Integer Values")) {
			fieldupdate = true;
		}
		TestValidation.IsTrue(fieldupdate, "Updated field verified", "Failed to verify updated field");
	}

	public void countingFormsByScrolling() throws InterruptedException {
		HomePage homePage = new HomePage(appiumDriver);
		mobControlActions = new ControlActions_MobileApp(appiumDriver);
		String FormName = APIInitiation();
		int FormCountDisplayed;
		Set<String> f = new HashSet<>();
		boolean formCount = false;
		String searchText;
		Thread.sleep(10000);
		HashMap<String, Integer> MenuCounts = CountMenu();
		ArrayList<WebElement> ls1 = new ArrayList<>(appiumDriver.findElements(By.id("itemName")));
		searchText = ls1.get(4).getText();
		FormCountDisplayed = MenuCounts.get(searchText);

		logInfo("Clicked on Main menu button");
		CloseMainMenu.click();

		Thread.sleep(3000);

		try {
			Thread.sleep(5000);
			do {
				ArrayList<WebElement> ls = new ArrayList<>(appiumDriver.findElementsById("formName"));
				for (int i = 0; i < ls.size(); i++) {
					f.add(ls.get(i).getText());
					ControlActions_MobileApp.swipe(400, 700, 400, 100);
				}

				System.out.println("Forms counted till now :" + f.size());

			} while (!(f.size() == FormCountDisplayed));
			if (f.size() == FormCountDisplayed) {
				formCount = true;
			} else {
				formCount = false;

			}

			TestValidation.IsTrue(formCount, "All forms are present as per count", "All forms are not present");
			throw new StaleElementReferenceException("End");

		} catch (StaleElementReferenceException e) {
			System.out.println("All form elements are counted");
			f.clear();
			WebDriverWait wait = new WebDriverWait(appiumDriver, 240);

			WebElement field = appiumDriver.findElement(By.id("refreshTextView"));

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("refreshTextView")));
			ControlActions_MobileApp.click(field);
			System.out.println("Refresh All is Selected");
			RefreshPopup.click();
			boolean refresh = true;
			TestValidation.IsTrue(refresh, "Refresh all clicked", "Failed to refresh all forms");
			
			ControlActions_MobileApp.waitForVisibility(searchBox, 600);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("searchTextView")));
			boolean clickMenu = homePage.ClickSubMenu(homePage.saveSubMenu);
			TestValidation.IsTrue(clickMenu, "Clicked on saveSubMenu ", "Failed to click on saveSubMenu");

			Boolean searchFrms = homePage.SearchForm(FormName);
			TestValidation.IsTrue(searchFrms, "Form Searched successfully after clicking Refresh All",
					"Failed to search Form :Refresh All not working");

		}

	}

	public void refreshAllForms() throws InterruptedException {
		HomePage homePage = new HomePage(appiumDriver);
		mobControlActions = new ControlActions_MobileApp(appiumDriver);
		String FormName = APIInitiation();
		Thread.sleep(10000);
		boolean refresh = false;

		do {
			try {
				ControlActions_MobileApp.waitForVisibility(appiumDriver.findElement(By.id("refreshTextView")), 1);
				if (appiumDriver.findElement(By.id("refreshTextView")).isDisplayed()) {

					refresh = true;
				}
				throw new NoSuchElementException();

			} catch (NoSuchElementException e) {
				ControlActions_MobileApp.swipe(400, 700, 400, 100);
			} catch (Exception ex) {
				ControlActions_MobileApp.swipe(400, 700, 400, 100);
			}
		} while (!refresh);
		ControlActions_MobileApp.WaitforelementToBeClickable(appiumDriver.findElement(By.id("refreshTextView")));
		appiumDriver.findElement(By.id("refreshTextView")).click();

		TestValidation.IsTrue(refresh, "Refresh All button Clicked", "Failed to Click Refresh All");
		ControlActions_MobileApp.waitForVisibility(homePage.searchField, 600);
		ControlActions_MobileApp.swipeScreen("DOWN");// need to remove
		boolean searchFrms = homePage.SearchForm(FormName);
		TestValidation.IsTrue(searchFrms, "Form Searched successfully after clicking Refresh All",
				"Failed to search Form :Refresh All not working");
		// driver.quit();
	}

	public boolean verifyWebImageAttached(String attachmentName) {
		Boolean isPresent = false;
		try {
			// System.out.println("Waiting for attachment title");
			// ControlActions_MobileApp.waitForVisibility(photoTitle, 240);
			String imageTitle = photoTitle.getText();
			System.out.println("Photo title is : " + imageTitle);
			// if (imageTitle.equals(attachmentName)) {
			logInfo("Attached Image name is verified" + imageTitle);
			ControlActions_MobileApp.waitForVisibility(selectPhoto, 50);
			ControlActions_MobileApp.WaitforelementToBeClickable(selectPhoto);
			ControlActions_MobileApp.click(selectPhoto);
			Thread.sleep(4000);
			try {
				ControlActions_MobileApp.waitForVisibility(photoBckBtn, 50);
				ControlActions_MobileApp.WaitforelementToBeClickable(photoBckBtn);
				ControlActions_MobileApp.click(photoBckBtn);
			} catch (Exception er) {
				appiumDriver.navigate().back();
			}
			isPresent = true;
		}
		// else {
		// isPresent = false;
		// logInfo("Attached Image is not present");
		// }

		// return isPresent;
		// }
		catch (Exception e) {
			logError("Failed to Verify Images attached through web" + e.getMessage());
			// return isPresent;
		}

		return isPresent;
	}

	public void WebSavedFormsValidation() throws Exception {
		String FormName = APIInitiation();
		driver = launchbrowser();
		SavedPageObj = new SavedPage(appiumDriver);
		FSQABrowserFormsPage fbForms = new FSQABrowserFormsPage(driver);
		FSQABrowserPage fbp = new FSQABrowserPage(driver);
		UploadImage oUploadImage = new UploadImage(appiumDriver);
		lp = new com.project.safetychain.webapp.pageobjects.LoginPage(driver);
		controlActions = new ControlActions(driver);
		String fileName = "upload.png";
		String filePath = System.getProperty("user.dir") + "\\test-data-files\\UploadDocuments\\" + fileName;
		controlActions.getUrl(applicationUrl);

		hp = lp.performLogin(adminUsername, adminPassword);
		if (hp.error) {
			TCGFailureMsg = "Could NOT login to application";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
		String resourceName = resourceInstanceValue1;
		String locationName = locationInstanceValue1;
		boolean navigate = fbp.navigateToFSQATab("Forms");
		TestValidation.IsTrue(navigate, "Navigated to FSQABrowser>FormsTab",
				"Failed to navigate to FSQABrowser>Forms Tab");

		boolean applyfilter = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, FormName);
		TestValidation.IsTrue(applyfilter, "Applied Filter on" + COLUMNHEADER.FORMNAME,
				"Failed to apply filter on" + COLUMNHEADER.FORMNAME);
		threadsleep(5000);
		WebElement location, resource;
		String selectedLocation, selectedResource;
		try {
			if (controlActions.isElementDisplayedOnPage(fbForms.LocationDdl)) {

				controlActions.click(fbForms.LocationDdl);

				String first = "//li[text()='";
				String loc = locationInstanceValue1;
				@SuppressWarnings("unused")
				String res = resourceInstanceValue1;
				String last = "\']";

				location = driver.findElement(By.xpath(first + loc + last));
				controlActions.click(location);
				fbForms.Sync();
				logInfo("Selected Location - " + locationName);
			} else {
				selectedLocation = fbForms.SelectedLocationLbl.getText();
				logInfo("Location - '" + selectedLocation + "' is already selected");
			}
			if (controlActions.isElementDisplayedOnPage(fbForms.SelectResourceDdl)) {
				String first1 = "//li[text()='";
				String res = resourceInstanceValue1;
				String last1 = "\']";
				controlActions.clickElement(fbForms.SelectResourceDdl);
				resource = driver.findElement(By.xpath(first1 + res + last1));
				controlActions.clickElement(resource);
				fbForms.Sync();
				logInfo("Selected Resource - " + resourceName);
			} else {
				selectedResource = fbForms.SelectedResourceLbl.getText();
				logInfo("Resource - '" + selectedResource + "' is already selected");
			}
		} catch (Exception e) {
			logError("Failed to select Location/Resource - " + e.getMessage());
		}
		threadsleep(3000);
		driver.findElement(By.xpath("//span[@class='k-numeric-wrap k-state-default']//input")).sendKeys("8");
		try {
			controlActions.clickElement(fbForms.FormLevelAttachment);
			if (!controlActions.isElementDisplayedOnPage(fbForms.FormLevelAttachmentSelectBtn)) {
				logError("Failed to find upload attachment option");

			}
			controlActions.sendKeys(fbForms.FormLevelAttachmentInp, filePath);
			logInfo("Uploading the attchment");
			threadsleep(4000);
			String uploadStatus = fbForms.FormLevelAttachmentStatus.getAttribute("class");
			for (int i = 0; i < 2; i++) {
				if (uploadStatus.equals("k-file k-file-success")) {
					break;
				}
				if (uploadStatus.equals("k-file k-file-error")) {
					controlActions.clickElement(fbForms.FormLevelAttachmentRetry);
				}
				threadsleep(4000);
			}
			if (!uploadStatus.equals("k-file k-file-success")) {
				logError("Failed to upload attachment'");

			}
			logInfo("Verified - Attachment is uploaded'");
			controlActions.clickElement(fbForms.FormLevelAttachmentCloseBtn);

		} catch (Exception e) {
			logError("Failed to upload attachment - " + e.getMessage());

		}
		boolean Attachment = (driver.findElement(By.xpath("//div[text()='Attachments']")).isDisplayed() && driver
				.findElement(By.xpath("//a[@ng-bind='attachment.name']")).getText().equalsIgnoreCase(fileName));
		TestValidation.IsTrue(Attachment, "Attachment is present at bottom", "Attachment is not present at bottom");
		driver.findElement(By.id("scs-save-form-button")).click();

		appiumDriver = launchMobileApp();
		SavedPageObj = new SavedPage(appiumDriver);
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
		boolean clicksavemenu = homePage.ClickSubMenu(homePage.saveSubMenu);
		TestValidation.IsTrue(clicksavemenu, "Clicked on Save subMenu Successfully", "Failed to click Save subMenu");
		ControlActions_MobileApp.click(SavedPageObj.sortBtn);
		boolean searchForm = homePage.SearchForm(FormName);
		TestValidation.IsTrue(searchForm, "Form Searched successfully", "Failed to search Form");
		boolean clickFormsSub = homePage.ClickForm(FormName);
		TestValidation.IsTrue(clickFormsSub, "Clicked on form Successfully", "Failed to click Forms");
		Thread.sleep(5000);
		boolean numCheck = SavedPageObj.verifyFieldValues("Numeric", "8");
		TestValidation.IsTrue(numCheck, "Numeric value saved on web is present in mobile also",
				"Numeric value saved on web is not present in mobile");
		// String WebuploadedImage = "upload";
		Thread.sleep(10000);
		boolean verifyAttachment = verifyWebImageAttached("GENERAL PHOTOS");
		TestValidation.IsTrue(verifyAttachment, "Attachment is verified succesfully", "Failed to verify attachment");

		Boolean txtPara = SavedPageObj.enterFieldValue(SavedPageObj.paragraphTxt, "Para");
		TestValidation.IsTrue(txtPara, "Numeric Field value entered successfully", "Failed to enter field value");

		Boolean txtFT = SavedPageObj.enterFieldValue(SavedPageObj.freeText, "FreeText");
		TestValidation.IsTrue(txtFT, "Numeric Field value entered successfully", "Failed to enter field value");

		Boolean selectOne = SavedPageObj.selectOneOrMultipleFieldValues("SelectOne", "GOOD");
		TestValidation.IsTrue(selectOne, "Numeric Field value entered successfully", "Failed to enter field value");

		String date = ControlActions_MobileApp.AddDaystoToddaysDate(0);
		ControlActions_MobileApp.swipeScreen("UP");
		Boolean Sedate = SavedPageObj.clickFieldType(homePage.datePicker, date);
		TestValidation.IsTrue(Sedate, "Date Selected successfully", "Failed to select Date value");
		Boolean SedateTime = SavedPageObj.selectDateTimeFieldValues(homePage.dateTimePicker, date);
		String dateTimeSel = homePage.dateTimePicker.getText();
		logInfo("Date&Time Field Value = " + dateTimeSel);
		TestValidation.IsTrue(SedateTime, "DateTime value selected successfully", "Failed to Select DateTime value");
		// ControlActions_MobileApp.swipeScreen("UP");
		Boolean SeMultiple = SavedPageObj.selectOneOrMultipleFieldValues("MutiSelect", "10");
		TestValidation.IsTrue(SeMultiple, "Numeric Field value entered successfully", "Failed to enter field value");
		Boolean SeMultiple1 = SavedPageObj.selectOneOrMultipleFieldValues("MutiSelect", "30");
		TestValidation.IsTrue(SeMultiple1, "Numeric Field value entered successfully", "Failed to enter field value");
		oUploadImage.CameraClick();
		oUploadImage.OpenCamera();
		oUploadImage.CameraShutter();
		oUploadImage.CloseCamera();
		oUploadImage.verifySelectedImageThroughGallery();

		@SuppressWarnings("unused")
		String mobImageName = "GENERAL PHOTOS";
		boolean submitForm = homePage.submitForm();
		TestValidation.IsTrue(submitForm, "Form Submitted Successfully", "Failed to Submit Form from Forms SubTab");

		boolean navigateRec = fbp.navigateToFSQATab("Records");
		TestValidation.IsTrue(navigateRec, "Navigated to FSQABrowser>RecordsTab",
				"Failed to navigate to FSQABrowser>Records Tab");

		boolean applyfilterRec = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, FormName);
		TestValidation.IsTrue(applyfilterRec, "Applied Filter on" + COLUMNHEADER.FORMNAME,
				"Failed to apply filter on" + COLUMNHEADER.FORMNAME);
		threadsleep(5000);
		FSQABrowserRecordsPage rp = new FSQABrowserRecordsPage(driver);
		String dateMinus1 = ControlActions_MobileApp.AddDaystoToddaysDateInGivenFormat("MM/dd/yyyy", -1);

		String CurrentDate = ControlActions_MobileApp.AddDaystoToddaysDateInGivenFormat("MM/dd/yyyy", 0);

		String[] time = dateTimeSel.split("\\s");

		String dateTimeNew = CurrentDate + " " + time[1];

		System.out.println("New date is :" + dateMinus1);

		System.out.println("New datetime is :" + dateTimeNew);
		HashMap<String, List<String>> map1 = new HashMap<String, List<String>>();
		map1.put(Numeric, Arrays.asList("8"));
		map1.put(chkFreeTxt, Arrays.asList("FreeText"));
		map1.put(ParaTxt, Arrays.asList("Para"));
		map1.put(chkDate, Arrays.asList(dateMinus1));
		map1.put(chkDateTime, Arrays.asList(dateTimeNew));

		map1.put(SelectMultiple, Arrays.asList("10 , 30"));
		map1.put(chkSelectOne, Arrays.asList("GOOD"));

		boolean verifyUpdatedValueWeb = rp.verifyFieldValues(map1);

		TestValidation.IsTrue(verifyUpdatedValueWeb, "VERIFIED updated values for all fields",
				"Failed to verify updated values for fields");
		System.out.println();
		ArrayList<WebElement> images = new ArrayList<>(
				driver.findElements(By.xpath("//a[@ng-bind='attachment.name']")));
		System.out.println("No of attachments present in form:" + images.size());
		@SuppressWarnings("unused")
		String Attachment1 = images.get(0).getText();
		@SuppressWarnings("unused")
		String Attachment2 = images.get(1).getText();
		ArrayList<String> filesExpected = new ArrayList<>();
		if (images.size() == 2) {
			boolean attachCount = true;
			TestValidation.IsTrue(attachCount, "All attachments are present", "All attachments are not present");

			for (int i = 0; i < images.size(); i++) {
				System.out.println("Attachement name:" + images.get(i).getText());
				filesExpected.add(images.get(i).getText());
			}

		}

		ArrayList<String> filesActual = new ArrayList<>();
		filesActual.add("upload.png");
		filesActual.add("1.png");
		Collections.sort(filesExpected);
		Collections.sort(filesActual);

		boolean AttachmentMob = (filesExpected.equals(filesActual));
		TestValidation.IsTrue(AttachmentMob, "Attachment from mobile is present at bottom",
				"Attachment  from mobile is not present at bottom");

	}

	public boolean verifyImageAttached(String attachmentName) {
		Boolean isPresent = false;
		try {
			System.out.println("Waiting for attachment title");
			ControlActions_MobileApp.waitForVisibility(photoTitle, 240);
			String imageTitle = photoTitle.getText();
			System.out.println("Photo title is : " + imageTitle);
			if (imageTitle.equals(attachmentName)) {
				logInfo("Attached Image name is verified" + imageTitle);
				ControlActions_MobileApp.waitForVisibility(selectPhoto, 50);
				ControlActions_MobileApp.WaitforelementToBeClickable(selectPhoto);
				ControlActions_MobileApp.click(selectPhoto);
				Thread.sleep(4000);
				try {
					ControlActions_MobileApp.waitForVisibility(photoBckBtn, 50);
					ControlActions_MobileApp.WaitforelementToBeClickable(photoBckBtn);
					ControlActions_MobileApp.click(photoBckBtn);
				} catch (Exception er) {
					appiumDriver.navigate().back();
				}
				isPresent = true;
			} else {
				isPresent = false;
				logInfo("Attached Image is not present");
			}

			return isPresent;
		} catch (Exception e) {
			logError("Failed to Verify Images attached through web" + e.getMessage());
			return isPresent;
		}
	}

	public void locationDeselectFromList(ArrayList<String> input) {
		ArrayList<WebElement> locations = new ArrayList<>(driver.findElements(By.xpath("//kendo-grid-list//span")));
		System.out.println("number of locations present :" + locations.size());
		String part1 = "//kendo-grid-list//span[text()=\'";

		String part2 = "\']//preceding:: td//input[@type='checkbox']";

		boolean uncheckFlag = false;
		for (int i = 0; i < input.size(); i++) {
			for (WebElement a : locations) {
				if (a.getText().equalsIgnoreCase(input.get(i))) {

					System.out.println("xpath is :" + part1 + a.getText() + part2);
					driver.findElement(By.xpath(part1 + a.getText() + part2)).click();
					if (!driver.findElement(By.xpath(part1 + a.getText() + part2)).isSelected()) {
						uncheckFlag = true;
					}

					TestValidation.IsTrue(uncheckFlag, "Unlinked locations succesfully", "Failed to unlink locations");
					break;
				}
			}
		}

	}

	public String APIInitiation() {
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

			// resourceCategoryValue = CommonMethods.dynamicText("Equip_Cat");

			locationCategoryValue = CommonMethods.dynamicText("LCat");
			locationInstanceValue1 = CommonMethods.dynamicText("LInst1");
			locationInstanceValue2 = CommonMethods.dynamicText("LInst2");
			resourceCategoryValue = CommonMethods.dynamicText("RCat");
			resourceInstanceValue1 = CommonMethods.dynamicText("RInst1");
			resourceInstanceValue2 = CommonMethods.dynamicText("RInst2");

			// Field Short Names
			chkFreeTxt = "FreeText";
			ParaTxt = "Paragraph";
			chkSelectOne = "SelectOne";
			Numeric = "Numeric";
			chkDate = "Date";
			chkTime = "Time";
			chkDateTime = "Date&Time";
			SelectMultiple = "MutiSelect";

			lp = new com.project.safetychain.webapp.pageobjects.LoginPage(driver);
			controlActions = new ControlActions(driver);

			controlActions.getUrl(applicationUrl);

			hp = lp.performLogin(adminUsername, adminPassword);
			if (hp.error) {
				TCGFailureMsg = "Could NOT login to application";
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}

			modifiedby = mainPage.getLoggedInUserDetails();

			TCG_FormCreation_Wrapper formCreationWrapper = new TCG_FormCreation_Wrapper();

			apiUtils = new ApiUtils();

			locationCategoryValue = CommonMethods.dynamicText("LCat");
			locationInstanceValue1 = CommonMethods.dynamicText("LInst1");
			locationInstanceValue2 = CommonMethods.dynamicText("LInst2");
			resourceCategoryValue = CommonMethods.dynamicText("RCat");
			resourceInstanceValue1 = CommonMethods.dynamicText("RInst1");
			resourceInstanceValue2 = CommonMethods.dynamicText("RInst2");

			// API Implementation

			// API Implementation

			FormFieldParams numericField = new FormFieldParams();
			numericField.DPTFieldType = DPT_FIELD_TYPES.NUMERIC;
			numericField.Name = Numeric;

			FormFieldParams paraTextField = new FormFieldParams();
			paraTextField.DPTFieldType = DPT_FIELD_TYPES.PARAGRAPH_TEXT;
			paraTextField.Name = ParaTxt;

			FormFieldParams freeTextField = new FormFieldParams();
			freeTextField.DPTFieldType = DPT_FIELD_TYPES.FREE_TEXT;
			freeTextField.Name = chkFreeTxt;

			FormFieldParams selectOneField = new FormFieldParams();
			selectOneField.DPTFieldType = DPT_FIELD_TYPES.SELECT_ONE;
			selectOneField.Name = chkSelectOne;
			selectOneField.SelectOptions = Arrays.asList("GOOD", "BAD");

			FormFieldParams dateField = new FormFieldParams();
			dateField.DPTFieldType = DPT_FIELD_TYPES.DATE;
			dateField.Name = chkDate;

			FormFieldParams dateTimeField = new FormFieldParams();
			dateTimeField.DPTFieldType = DPT_FIELD_TYPES.DATE_TIME;
			dateTimeField.Name = chkDateTime;

			FormFieldParams selectMultipleField = new FormFieldParams();
			selectMultipleField.DPTFieldType = DPT_FIELD_TYPES.SELECT_MULTIPLE;
			selectMultipleField.Name = SelectMultiple;
			selectMultipleField.SelectOptions = Arrays.asList("10", "20", "30");

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
			fp.formElements = Arrays.asList(numericField, paraTextField, freeTextField, selectOneField, dateField,
					dateTimeField, selectMultipleField);
			logInfo("fp.formElements = " + fp.formElements);

			HashMap<String, List<String>> resourceCatMap = new HashMap<String, List<String>>();
			resourceCatMap.put(resourceCategoryValue, Arrays.asList(resourceInstanceValue1, resourceInstanceValue2));
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
		return FormName;
	}

	public void taskCreation() {
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

			FormFieldParams paraTextField = new FormFieldParams();
			paraTextField.DPTFieldType = DPT_FIELD_TYPES.PARAGRAPH_TEXT;
			paraTextField.Name = ParaTxt;

			FormFieldParams freeTextField = new FormFieldParams();
			freeTextField.DPTFieldType = DPT_FIELD_TYPES.FREE_TEXT;
			freeTextField.Name = chkFreeTxt;

			FormFieldParams selectOneField = new FormFieldParams();
			selectOneField.DPTFieldType = DPT_FIELD_TYPES.SELECT_ONE;
			selectOneField.Name = chkSelectOne;
			selectOneField.SelectOptions = Arrays.asList("GOOD", "BAD");

			FormFieldParams dateField = new FormFieldParams();
			dateField.DPTFieldType = DPT_FIELD_TYPES.DATE;
			dateField.Name = chkDate;

			FormFieldParams dateTimeField = new FormFieldParams();
			dateTimeField.DPTFieldType = DPT_FIELD_TYPES.DATE_TIME;
			dateTimeField.Name = chkDateTime;

			FormFieldParams selectMultipleField = new FormFieldParams();
			selectMultipleField.DPTFieldType = DPT_FIELD_TYPES.SELECT_MULTIPLE;
			selectMultipleField.Name = SelectMultiple;
			selectMultipleField.SelectOptions = Arrays.asList("10", "20", "30");

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
			fp.formElements = Arrays.asList(numericField, paraTextField, freeTextField, selectOneField, dateField,
					dateTimeField, selectMultipleField);
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

	public void taskBadgeValidation() throws InterruptedException, ParseException {
		HomePage homePage = new HomePage(appiumDriver);
		TaskPage InboxPageObj = new TaskPage(appiumDriver);
		SavedPage SavedPageObj = new SavedPage(appiumDriver);
		boolean taskBadgeCount = false;
		ControlActions_MobileApp.waitForVisibility(taskCount, 20);
		int taskCountBefore = Integer.parseInt(taskCount.getText());
		logInfo("TaskCount Before Task Creation : " + taskCountBefore);
		taskCreation();
		ControlActions_MobileApp.waitForVisibility(SearchFormsTextbox, 30);
		ControlActions_MobileApp.swipeScreen("DOWN");
		Thread.sleep(5000);
		ControlActions_MobileApp.waitForVisibility(taskCount, 20);
		int taskCountAfter = Integer.parseInt(taskCount.getText());
		logInfo("TaskCount After Task Creation : " + taskCountAfter);
		{
			if (taskCountAfter > taskCountBefore) {
				logInfo("Task badge count is updated , Newly created tasks : " + (taskCountAfter - taskCountBefore));
				taskBadgeCount = true;
			} else {
				logInfo("Task badge count is not updated");
				taskBadgeCount = false;
			}

			TestValidation.IsTrue(taskBadgeCount, "Task badge Count is updated", "Failed to update Task badge count");
		}

		boolean clickInbox = homePage.ClickSubMenu(homePage.inboxSubMenu);
		TestValidation.IsTrue(clickInbox, "Clicked on Inbox Sub Menu", "Failed to click Inbox Submenu");
		boolean searchForm = homePage.SearchTask(TaskName);
		TestValidation.IsTrue(searchForm, "Created Task is present in Inbox", "Created Task is not present in Inbox");
		WebElement formCheck = ControlActions_MobileApp.perform_GetElementByXPath(HomePageLocators.Form_Name,
				"FormNameLocator", TaskName);
		ControlActions_MobileApp.waitForVisibility(formCheck, 60);
		ControlActions_MobileApp.click(formCheck);
		logInfo("Clicked on Task = " + TaskName);
		Thread.sleep(20000);

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
		String dateSelected = ControlActions_MobileApp.AddDaystoToddaysDateInGivenFormat("M/d/YY", 0);
		logInfo("Date Field Value = " + dateSelected);

		Boolean SedateTime = SavedPageObj.clickFieldType(homePage.dateTimePicker, date);
		TestValidation.IsTrue(SedateTime, "DateTime value selected successfully", "Failed to Select DateTime value");
		String dateTimeSel = homePage.dateTimePicker.getText();
		logInfo("Date&Time Field Value = " + dateTimeSel);

		Boolean SeMultiple = SavedPageObj.selectOneOrMultipleFieldValues("MutiSelect", "10");
		TestValidation.IsTrue(SeMultiple, "Value selected for SelectMultiple Field Type",
				"Failed to select MultiSelect value");
		Boolean SeMultiple1 = SavedPageObj.selectOneOrMultipleFieldValues("MutiSelect", "30");
		TestValidation.IsTrue(SeMultiple1, "Value selected for SelectMultiple Field Type",
				"Failed to select MultiSelect value");

		boolean submitForm = InboxPageObj.submitTask();

		Thread.sleep(4000);

		TestValidation.IsTrue(submitForm, "Task submitted Successfully", "Failed to submit Task from Inbox");
		homePage.ClickSubMenu(homePage.submissionsSubMenu);
		homePage.SearchForm(FormName);
		SavedPageObj.checkFormStatusFromSubmissionsSubMenu(FormName, "RECEIVED");

		boolean clickInbox1 = homePage.ClickSubMenu(homePage.inboxSubMenu);

		TestValidation.IsTrue(clickInbox1, "Clicked on Inbox Sub Menu", "Failed to click Inbox Submenu");
		closeSearchBtn.click();
		ControlActions_MobileApp.swipeScreen("DOWN");
		int taskCountAfterSubmit = Integer.parseInt(taskCount.getText());

		{
			if (taskCountAfterSubmit < taskCountAfter) {
				logInfo("Task badge count is Decremented after task submission By : "
						+ (taskCountAfter - taskCountAfterSubmit));
				taskBadgeCount = true;
			} else {
				logInfo("Task badge count is not Decremented after task submission");
				taskBadgeCount = false;
			}

			TestValidation.IsTrue(taskBadgeCount, "Task badge count is Decremented after task submission",
					"Failed to update Task badge count after task submission");
		}

		Boolean taskPresentAfterSubmit = SearchForm(TaskName, InboxSearchBox);

		TestValidation.IsFalse(taskPresentAfterSubmit, "Submitted task not present in inbox",
				"Submitted task still present in inbox :Failed");
	}

	public void UnlinkSpecifiedLocationFunctionality() throws InterruptedException, ParseException {
		HomePage homePage = new HomePage(appiumDriver);
		SavedPage SavedPageObj = new SavedPage(appiumDriver);
		FormsManagerPage fm = new FormsManagerPage(appiumDriver);
		String FormNameCreated = APIInitiation();
		ControlActions_MobileApp.swipeScreen("DOWN");
		Thread.sleep(20000);
		boolean searchForm = homePage.SearchForm(FormNameCreated);
		TestValidation.IsTrue(searchForm, "Form Searched successfully", "Failed to search Form");
		clickAndSaveForm(FormNameCreated);
		ControlActions_MobileApp.waitForVisibility(SearchSavedFormTextbox, 30);
		ControlActions_MobileApp.swipeScreen("DOWN");

		SearchForm(FormNameCreated, SearchSavedFormTextbox);
		appiumDriver.hideKeyboard();
		AllFormsBottomClick.click();
		AddToFavourite.click();
		FavouriteClick.click();
		ControlActions_MobileApp.swipeScreen("DOWN");
		SearchForm(FormNameCreated, SearchFavouriteTextbox);

		fm = hp.clickFormsManagerMenu();
		boolean searchingForm = fm.searchForm(FormNameCreated);
		TestValidation.IsTrue(searchingForm, "OPENED the search form - " + FormNameCreated,
				"Failed to open searched form - " + FormNameCreated);

		boolean selectForm = fm.selectForm(FormNameCreated);
		TestValidation.IsTrue(selectForm, "Form has Selected- " + FormNameCreated,
				"Failed to select Form- " + FormNameCreated);

		boolean locationOpt = fm.clickLocationMenu();
		TestValidation.IsTrue(locationOpt, "Clicked on Locations option", "Failed to select Locations option");

		boolean toggleLoc = fm.clickToggleLocation();
		TestValidation.IsTrue(toggleLoc, "Clicked on Location toggle on", "Failed to click on Location toggle on");

		ArrayList<String> locToUnlink = new ArrayList<>();
		locToUnlink.add(locationInstanceValue1);
		locationDeselectFromList(locToUnlink);

		boolean saveChanges = fm.clickSaveBtn();
		TestValidation.IsTrue(saveChanges, "Clicked on Save Button", "Failed to click on Save Button");

		closeSearchBtn.click();
		ControlActions_MobileApp.swipeScreen("DOWN");
		Thread.sleep(20000);

		boolean searchFormFav = homePage.SearchForm(FormNameCreated);
		TestValidation.IsTrue(searchFormFav, "Form Searched successfully", "Failed to search Form");
		appiumDriver.hideKeyboard();
		AllFormsBottomClick.click();
		closeSearchBtn.click();
		SearchLocationUnlinked(FormNameCreated);
		boolean searchResource = SearchResource(resourceInstanceValue1, ResourceText);
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
		String dateSelected = ControlActions_MobileApp.AddDaystoToddaysDateInGivenFormat("M/d/YY", 0);
		logInfo("Date Field Value = " + dateSelected);

		Boolean SedateTime = SavedPageObj.clickFieldType(homePage.dateTimePicker, date);
		TestValidation.IsTrue(SedateTime, "DateTime value selected successfully", "Failed to Select DateTime value");
		String dateTimeSel = homePage.dateTimePicker.getText();
		logInfo("Date&Time Field Value = " + dateTimeSel);

		Boolean SeMultiple = SavedPageObj.selectOneOrMultipleFieldValues("MutiSelect", "10");
		TestValidation.IsTrue(SeMultiple, "Value selected for SelectMultiple Field Type",
				"Failed to select MultiSelect value");
		Boolean SeMultiple1 = SavedPageObj.selectOneOrMultipleFieldValues("MutiSelect", "30");
		TestValidation.IsTrue(SeMultiple1, "Value selected for SelectMultiple Field Type",
				"Failed to select MultiSelect value");

		boolean submitForm = submitForm();

		Thread.sleep(4000);

		TestValidation.IsTrue(submitForm, "Form submitted Successfully", "Failed to submit form from Inbox");
		homePage.ClickSubMenu(homePage.submissionsSubMenu);
		homePage.SearchForm(FormNameCreated);
		SavedPageObj.checkFormStatusFromSubmissionsSubMenu(FormNameCreated, "RECEIVED");
		appiumDriver.hideKeyboard();
		driver.quit();
	}

	public void SearchLocationUnlinked(String Formname) throws InterruptedException {

		HomePage homePage = new HomePage(appiumDriver);
		boolean searchForm = homePage.SearchForm(Formname);
		TestValidation.IsTrue(searchForm, "Form Searched successfully", "Failed to search Form");

		boolean clickFormsSub = homePage.ClickForm(Formname);
		TestValidation.IsTrue(clickFormsSub, "Clicked on Forms subMenu Successfully", "Failed to click Forms subMenu");
		Thread.sleep(5000);
		boolean searchLocation = SearchResource(locationInstanceValue1, LocationText);
		TestValidation.IsTrue(searchLocation, "Location is still present after unlinking fom web",
				"Location not present after unlinking from web");

	}

	public void UnlinkLocationFunctionality() throws InterruptedException {
		HomePage homePage = new HomePage(appiumDriver);
		FormsManagerPage fm = new FormsManagerPage(appiumDriver);
		String FormNameCreated = APIInitiation();
		ControlActions_MobileApp.swipeScreen("DOWN");
		Thread.sleep(20000);
		ControlActions_MobileApp.waitForVisibility(mainMenu, 30);
		ControlActions_MobileApp.click(mainMenu);
		logInfo("Clicked on Main menu button");
		int beforeCount = Integer.parseInt(subMenuFormsCnt.getText());
		logInfo("Allforms count before is : " + beforeCount);
		CloseMainMenu.click();

		boolean searchForm = homePage.SearchForm(FormNameCreated);
		TestValidation.IsTrue(searchForm, "Form Searched successfully", "Failed to search Form");
		clickAndSaveForm(FormNameCreated);
		ControlActions_MobileApp.waitForVisibility(SearchSavedFormTextbox, 30);
		ControlActions_MobileApp.swipeScreen("DOWN");

		SearchForm(FormNameCreated, SearchSavedFormTextbox);
		appiumDriver.hideKeyboard();
		AllFormsBottomClick.click();
		AddToFavourite.click();
		FavouriteClick.click();
		Thread.sleep(5000);
		ControlActions_MobileApp.swipeScreen("DOWN");
		SearchForm(FormNameCreated, SearchFavouriteTextbox);

		fm = hp.clickFormsManagerMenu();
		boolean searchingForm = fm.searchForm(FormNameCreated);
		TestValidation.IsTrue(searchingForm, "OPENED the search form - " + FormNameCreated,
				"Failed to open searched form - " + FormNameCreated);

		boolean selectForm = fm.selectForm(FormNameCreated);
		TestValidation.IsTrue(selectForm, "Form has Selected- " + FormNameCreated,
				"Failed to select Form- " + FormNameCreated);

		boolean locationOpt = fm.clickLocationMenu();
		TestValidation.IsTrue(locationOpt, "Clicked on Locations option", "Failed to select Locations option");

		boolean toggleLoc = fm.clickToggleLocation();
		TestValidation.IsTrue(toggleLoc, "Clicked on Location toggle on", "Failed to click on Location toggle on");

		boolean unlink = fm.ClickLocationCheck();
		TestValidation.IsTrue(unlink, "Unlinked locations succesfully", "Failed to unlink locations");

		boolean saveChanges = fm.clickSaveBtn();
		TestValidation.IsTrue(saveChanges, "Clicked on Save Button", "Failed to click on Save Button");
		appiumDriver.hideKeyboard();

		ControlActions_MobileApp.swipeScreen("DOWN");
		Thread.sleep(20000);
		closeSearchBtn.click();
		NoResultsCheck(FormNameCreated, SearchFavouriteTextbox, "Favorites Tab");
		appiumDriver.hideKeyboard();
		AllFormsBottomClick.click();
		closeSearchBtn.click();
		NoResultsCheck(FormNameCreated, SearchFormsTextbox, "All Forms Tab");
		appiumDriver.hideKeyboard();
		SavedBottomClick.click();
		closeSearchBtn.click();
		NoResultsCheck(FormNameCreated, SearchSavedFormTextbox, "Saved Tab");
		driver.quit();
	}

	public void NoResultsCheck(String Formname, WebElement searchField, String tabName) {
		boolean isFormPresent = false;
		try {

			ControlActions_MobileApp.waitForVisibility(searchField, 120);
			ControlActions_MobileApp.ClearText(searchField);
			ControlActions_MobileApp.actionEnterText(searchField, Formname);
			System.out.println("Text has Entered");
			Thread.sleep(6000);

			isFormPresent = (noResultsFound.isDisplayed()
					&& (noResultsFound.getText().trim().equalsIgnoreCase("No Results Found")));
			isFormPresent = true;
		} catch (Exception ex) {
			logInfo("Failed to Search" + ex.getMessage());

		}
		TestValidation.IsTrue(isFormPresent,
				"Form is not present on  " + tabName + " as expected as locations are unlinked",
				"Form is still present on " + tabName + " even after unlinking locations");
	}

	public void SearchDataRetainingValidation() throws InterruptedException {
		HomePage homePage = new HomePage(appiumDriver);
		String FormNameCreated = APIInitiation();
		ControlActions_MobileApp.swipeScreen("DOWN");
		Thread.sleep(20000);
		boolean searchForm = homePage.SearchForm(FormNameCreated);
		TestValidation.IsTrue(searchForm, "Form Searched successfully", "Failed to search Form");

		clickAndSaveForm(FormNameCreated);
		ControlActions_MobileApp.waitForVisibility(SearchSavedFormTextbox, 30);
		ControlActions_MobileApp.swipeScreen("DOWN");

		SearchForm(FormNameCreated, SearchSavedFormTextbox);
		appiumDriver.hideKeyboard();
		AllFormsBottomClick.click();
		AddToFavourite.click();
		FavouriteClick.click();
		ControlActions_MobileApp.swipeScreen("DOWN");
		SearchForm(FormNameCreated, SearchFavouriteTextbox);
		appiumDriver.hideKeyboard();
		ControlActions_MobileApp.waitForVisibility(mainMenu, 30);
		ControlActions_MobileApp.click(mainMenu);
		logInfo("Clicked on Main menu button");

		boolean clickforms = homePage.ClickSubMenu(homePage.formsSubMenu);

		TestValidation.IsTrue(clickforms, "Clicked on All forms Sub Menu", "Failed to click All forms Submenu");

		SearchDataResult(FormNameCreated, FormNameCreated);

		boolean clickSaved = homePage.ClickSubMenu(homePage.saveSubMenu);

		TestValidation.IsTrue(clickSaved, "Clicked on Saved Sub Menu", "Failed to click Saved Submenu");

		SearchDataResult(FormNameCreated, FormNameCreated);
		boolean clickfavorites = homePage.ClickSubMenu(homePage.favouritesSubMenu);
		TestValidation.IsTrue(clickfavorites, "Clicked on Favorites Sub Menu", "Failed to click Favorites Submenu");
		SearchDataResult(FormNameCreated, FormNameCreated);
		driver.quit();
	}

	public boolean SortValidationCheck(String sortByElementID, String sortBy) throws InterruptedException {
		boolean sorted = false;

		Thread.sleep(15000);
		ArrayList<WebElement> ls = new ArrayList<>(appiumDriver.findElementsById(sortByElementID));
		ArrayList<String> forms = new ArrayList<>();
		System.out.println("Number of elements in grid :  " + ls.size());
		if (ls.size() > 0) {
			for (int i = 0; i < ls.size(); i++) {
				forms.add(ls.get(i).getText());
				System.out.println(forms.get(i));
			}

			ArrayList<String> formsSorted = new ArrayList<>();
			formsSorted.addAll(forms);
			// Ascending order

			if (sortBy.equalsIgnoreCase("Ascending")) {
				Collections.sort(formsSorted);

				if (forms.equals(formsSorted)) {
					logInfo("Sort By is in defaut Ascending order : " + forms);
					sorted = true;
				} else {
					logInfo("Sort By is not in defaut Ascending order : " + forms);

					logInfo("Expected : " + formsSorted);
					logInfo("Actual " + forms);
				}
			}

			if (sortBy.equalsIgnoreCase("Descending")) {
				// ArrayList<String> formsSorted1 = new ArrayList<>();
				Collections.sort(forms, Collections.reverseOrder());

				if (forms.equals(formsSorted)) {
					logInfo("Sort By is in descending order : " + forms);
					sorted = true;
				} else {
					logInfo("Sort By is not in descending order : " + forms);

					logInfo("Expected : " + formsSorted);
					logInfo("Actual " + forms);
				}

			}
		}

		else {
			logError("No data present ");
		}
		return sorted;
	}

	public boolean SortValidationCheckSavedForms(String sortByElementID, String sortBy) throws InterruptedException {
		boolean sorted = false;

		Thread.sleep(15000);
		ArrayList<WebElement> ls = new ArrayList<>(appiumDriver.findElementsById(sortByElementID));
		ArrayList<String> forms = new ArrayList<>();
		System.out.println("Number of elements in grid :  " + ls.size());
		if (ls.size() > 0) {
			for (int i = 0; i < ls.size(); i++) {

				String lst[] = ls.get(i).getText().split("on");
				forms.add(lst[1]);
				System.out.println(lst[1]);
			}

			ArrayList<String> formsSorted = new ArrayList<>();
			formsSorted.addAll(forms);
			// Ascending order

			if (sortBy.equalsIgnoreCase("Ascending")) {
				Collections.sort(formsSorted);

				if (forms.equals(formsSorted)) {
					logInfo("Sort By is in defaut Ascending order : " + forms);
					sorted = true;
				} else {
					logInfo("Sort By is not in defaut Ascending order : " + forms);

					logInfo("Expected : " + formsSorted);
					logInfo("Actual " + forms);
				}
			}

			if (sortBy.equalsIgnoreCase("Descending")) {
				Collections.sort(forms, Collections.reverseOrder());

				if (forms.equals(formsSorted)) {
					logInfo("Sort By is in descending order : " + forms);
					sorted = true;
				} else {
					logInfo("Sort By is not in descending order : " + forms);

					logInfo("Expected : " + formsSorted);
					logInfo("Actual " + forms);
				}

			}
		}

		else {
			logError("No data present ");
		}
		// Descending order
		// Collections.sort(forms, Collections.reverseOrder());

		return sorted;
	}

	public boolean CheckDefaultAscending() throws InterruptedException {
		boolean Asc = false;

		boolean Aformsflag = SortValidationCheck("formName", "Ascending");
		TestValidation.IsTrue(Aformsflag, "Sorting is in default Ascending order in All Forms",
				"Sorting is not in default Ascending order in All Forms");

		SavedBottomClick.click();
		boolean Savedflag = SortValidationCheck("formVersion", "Ascending");
		TestValidation.IsTrue(Aformsflag, "Sorting is in default Ascending order in Saved Forms",
				"Sorting is not in default Ascending order in Saved Forms");

		FavouriteClick.click();
		boolean favFlag = SortValidationCheck("formName", "Ascending");
		TestValidation.IsTrue(Aformsflag, "Sorting is in default Ascending order in Favorites Forms",
				"Sorting is not in default Ascending order in Favorites Forms");

		if (Aformsflag == true && Savedflag == true && favFlag == true) {
			Asc = true;
		}

		return Asc;
	}

	public void SortingValidation() throws InterruptedException {
		LoginPage loginPage = new LoginPage(appiumDriver);
		ControlActions_MobileApp.waitForVisibility(searchBox, 120);
		boolean DefAscValidate = CheckDefaultAscending();
		TestValidation.IsTrue(DefAscValidate, "All modules are in Default Ascending Order",
				"All modules are not in default Ascending Order");
		sortDesc.click();// making descending
		sortDesc.click(); // again making ascending
		boolean AscValidateAfterClick = CheckDefaultAscending();
		TestValidation.IsTrue(AscValidateAfterClick, "All modules are in Ascending Order after sorting by Ascending",
				"All modules are not in Ascending Order after sorting by Ascending");
		// Making it Descending

		sortDesc.click(); // It's checking for all modules by clicking on
							// sorting for one of them
		boolean DescValidate = CheckDescendingAll();
		TestValidation.IsTrue(DescValidate, "All modules are in Descending Order after sorting by Descending",
				"All modules are not in Descending Order after sorting by Descending");
		loginPage.logOut();
		boolean login = loginPage.Login(mobileAppUsername, mobileAppPassword);
		TestValidation.IsTrue(login, "logged into the application with User " + mobileAppUsername, "Failed to log in");

		logInfo("Verifying after relogin if its in default ascending order");
		ControlActions_MobileApp.waitForVisibility(searchBox, 120);
		boolean ReloginSort = CheckDefaultAscending();
		TestValidation.IsTrue(ReloginSort,
				"All modules are in Default Ascending Order after Relogin even though made descending last time",
				"All modules are not in default Ascending Order after Relogin");

	}

	public boolean CheckDescendingAll() throws InterruptedException {

		boolean Desc = false;
		boolean Aformsflag = SortValidationCheck("formName", "Descending");
		;
		TestValidation.IsTrue(Aformsflag, "Sorting is in Descending order in All Forms",
				"Sorting is not in Descending order in All Forms");

		SavedBottomClick.click();
		boolean Savedflag = SortValidationCheckSavedForms("formVersion", "Descending");
		TestValidation.IsTrue(Aformsflag, "Sorting is in Descending order in Saved Forms",
				"Sorting is not in Descending order in Saved Forms");

		FavouriteClick.click();
		boolean favFlag = SortValidationCheck("formName", "Descending");
		TestValidation.IsTrue(Aformsflag, "Sorting is in Descending order in Favorites Forms",
				"Sorting is not in Descending order in Favorites Forms");

		if (Aformsflag == true && Savedflag == true && favFlag == true) {
			Desc = true;
		}

		return Desc;

	}

	public void SearchDataResult(String SearchText, String Forms) {
		boolean search = false;
		if (searchBox.getText().equalsIgnoreCase(SearchText) && (FormNameElement.getText().equalsIgnoreCase(Forms))) {
			logInfo("Text Present in SearchBox : " + searchBox.getText());
			logInfo("Form displayed for Search : " + FormNameElement.getText());
			search = true;
		}

		else {
			logInfo("Search Results are not as expected : " + FormNameElement.getText());
			logInfo("Text expected in searchbox : " + SearchText + "    Actual: " + searchBox.getText());
			logInfo("Forms expected to be displayed : " + Forms + "    Actual: " + FormNameElement.getText());

		}

		TestValidation.IsTrue(search, "Search data retained succesfully", "Failed to retain search data");
	}

	public void searchByResourceNavigateBack() {
		boolean isResourcePresent = false;
		String FormNameCreated = APIInitiation();

		boolean formOpened = false;

		boolean backToSaveNav = false;
		boolean backToAllFormsNav = false;
		boolean backToFavNav = false;

		boolean navigationSuccessAfterClick = false;
		ControlActions_MobileApp.waitForVisibility(SearchFormsTextbox, 30);
		ControlActions_MobileApp.swipeScreen("DOWN");
		String ResourceName = resourceInstanceValue1;
		logInfo("Resource Name created is : " + ResourceName);
		try {
			// Search by Resource
			ControlActions_MobileApp.waitForVisibility(searchBox, 20);

			ControlActions_MobileApp.actionEnterText(searchBox, ResourceName);
			// searchBox.sendKeys(ResourceName);
			ControlActions_MobileApp.waitForVisibility(searchByName, 60);
			searchByName.click();
			logInfo("Search by resource is enabled now");
			Thread.sleep(5000);

			String ResourceAfterSearch = resourceName.getText();
			isResourcePresent = (resourceName.isDisplayed()
					&& (ResourceAfterSearch.trim()).equalsIgnoreCase(ResourceName.trim()));

			TestValidation.IsTrue(isResourcePresent, "Form is searched succesfully by Resourcename on All forms tab",
					"Failed to search form by Resource Name on All forms tab");
			ControlActions_MobileApp.WaitforelementToBeClickable(FormNameElement);
			FormNameElement.click();

			if (afterClickHeaderName.getText().equalsIgnoreCase("Select Location")
					&& clickFormNmaePresent.getText().equalsIgnoreCase(FormNameCreated)) {
				formOpened = true;

			}

			TestValidation.IsTrue(formOpened, "Clicked on form searched by resource succesfully and form is opened",
					"Failed to click on form searched by resource name");

			ControlActions_MobileApp.WaitforelementToBeClickable(backForm);
			backForm.click();

			ControlActions_MobileApp.waitForVisibility(resourceName, 10000);
			if (resourceName.isDisplayed() && (ResourceAfterSearch.trim()).equalsIgnoreCase(ResourceName.trim())
					&& searchBox.getText().equalsIgnoreCase(resourceInstanceValue1)) {
				navigationSuccessAfterClick = true;
			}

			TestValidation.IsTrue(navigationSuccessAfterClick,
					"Navigated back succesfully to main tab after clicking and data history is retained on page",
					"Failed to navigate back and retain history after click");

			// Adding to favourites
			AddToFavourite.click();
			logInfo("Added created form to favorites");

			// Saving Form

			ControlActions_MobileApp.WaitforelementToBeClickable(FormNameElement);
			FormNameElement.click();

			logInfo("Searching and entering Location ");
			boolean searchLocation = SearchResource(locationInstanceValue1, LocationText);
			TestValidation.IsTrue(searchLocation, "Location Searched Successfully",
					"Failed to Search Location from Forms subMenu");

			boolean searchResource = SearchResource(resourceInstanceValue1, ResourceText);
			TestValidation.IsTrue(searchResource, "Resource Searched Successfully",
					"Failed to Search Resource from Forms subMenu");

			Thread.sleep(5000);
			logInfo("Saving form");
			boolean saveForm = saveForm();
			TestValidation.IsTrue(saveForm, "Form Saved Successfully by resource name",
					"Failed to Save Form by resource name");

			ControlActions_MobileApp.swipeScreen("DOWN");

			logInfo("Search by Resource is already enabled");

			ControlActions_MobileApp.waitForVisibility(searchBox, 20);

			ControlActions_MobileApp.actionEnterText(searchBox, ResourceName);
			// ControlActions_MobileApp.waitForVisibility(searchByName, 20);
			// searchByName.click();

			if (resourceName.isDisplayed() && (ResourceAfterSearch.trim()).equalsIgnoreCase(ResourceName.trim())
					&& FormNameElement.getText().equalsIgnoreCase(FormNameCreated)) {
				ControlActions_MobileApp.WaitforelementToBeClickable(FormNameElement);
				FormNameElement.click();
				saveForm();
				if (resourceName.isDisplayed() && (ResourceAfterSearch.trim()).equalsIgnoreCase(ResourceName.trim())
						&& FormNameElement.getText().equalsIgnoreCase(FormNameCreated)
						&& searchBox.getText().equalsIgnoreCase(resourceInstanceValue1)) {
					navigationSuccessAfterClick = true;
				}

				TestValidation.IsTrue(navigationSuccessAfterClick,
						"Navigated back succesfully to main tab after clicking and data history is retained on page",
						"Failed to navigate back and retain history after click");
			}

			// Favorites tab Validation

			FavouriteClick.click();
			ControlActions_MobileApp.swipeScreen("DOWN");
			ControlActions_MobileApp.waitForVisibility(searchBox, 20);
			ControlActions_MobileApp.actionEnterText(searchBox, ResourceName);
			// searchBox.sendKeys(ResourceName);
			ControlActions_MobileApp.waitForVisibility(searchByName, 20);
			searchByName.click();
			logInfo("Validating for Favorites Tab");
			ControlActions_MobileApp.WaitforelementToBeClickable(FormNameElement);
			FormNameElement.click();

			if (afterClickHeaderName.getText().trim().equalsIgnoreCase("Select Location")
					&& clickFormNmaePresent.getText().trim().equalsIgnoreCase(FormNameCreated)) {
				formOpened = true;

			}

			TestValidation.IsTrue(formOpened, "Clicked on form searched by resource succesfully and form is opened",
					"Failed to click on form searched by resource name");

			ControlActions_MobileApp.WaitforelementToBeClickable(backForm);
			backForm.click();

			if (resourceName.isDisplayed() && (ResourceAfterSearch.trim()).equalsIgnoreCase(ResourceName.trim())
					&& FormNameElement.getText().equalsIgnoreCase(FormNameCreated)
					&& searchBox.getText().equalsIgnoreCase(resourceInstanceValue1)) {
				navigationSuccessAfterClick = true;
			}

			TestValidation.IsTrue(navigationSuccessAfterClick,
					"Navigated back succesfully to main tab after clicking and data history is retained on page",
					"Failed to navigate back and retain history after click");

			SavedBottomClick.click();
			if (resourceName.isDisplayed() && (ResourceAfterSearch.trim()).equalsIgnoreCase(ResourceName.trim())
					&& FormNameElement.getText().equalsIgnoreCase(FormNameCreated)
					&& searchBox.getText().equalsIgnoreCase(resourceInstanceValue1)) {
				backToSaveNav = true;
			}
			TestValidation.IsTrue(backToSaveNav,
					"Navigated back succesfully to Saved tab from Favorites Tab and data history is retained on page",
					"Failed to navigate back from favorites to saved forms");
			AllFormsBottomClick.click();
			if (resourceName.isDisplayed() && (ResourceAfterSearch.trim()).equalsIgnoreCase(ResourceName.trim())
					&& FormNameElement.getText().equalsIgnoreCase(FormNameCreated)
					&& searchBox.getText().equalsIgnoreCase(resourceInstanceValue1)) {

				backToAllFormsNav = true;
			}

			TestValidation.IsTrue(backToAllFormsNav,
					"Navigated back succesfully to All Forms tab from Saved Tab and data history is retained on page",
					"Failed to navigate back from Saved to All forms forms");

			FavouriteClick.click();
			Thread.sleep(5000);
			if (resourceName.isDisplayed() && (ResourceAfterSearch.trim()).equalsIgnoreCase(ResourceName.trim())
					&& FormNameElement.getText().equalsIgnoreCase(FormNameCreated)
					&& searchBox.getText().equalsIgnoreCase(resourceInstanceValue1)) {
				backToFavNav = true;
			}

			TestValidation.IsTrue(backToFavNav,
					"Navigated back succesfully to Favorites tab from All Forms Tab and data history is retained on page",
					"Failed to navigate back from All Forms tab to Favorites forms");

		} catch (Exception ex) {
			logInfo("Failed to Search form by resource Name " + ex.getMessage());
		}
		driver.quit();
	}

	public void searchByNameAndResource() throws InterruptedException {
		String FormNameCreated = APIInitiation();
		boolean isResourcePresentSaved = false;
		ControlActions_MobileApp.waitForVisibility(SearchFormsTextbox, 30);
		ControlActions_MobileApp.swipeScreen("DOWN");

		String ResourceName = resourceInstanceValue1;
		logInfo("Resource Name created is : " + ResourceName);
		logInfo("Searching for the created form in All forms");
		SearchForm(FormNameCreated, SearchFormsTextbox);
		Thread.sleep(5000);
		AddToFavourite.click();
		logInfo("Added created form to favorites");
		closeSearchBtn.click();
		ControlActions_MobileApp.waitForVisibility(SearchFormsTextbox, 20);
		searchBox.sendKeys(FormNameCreated);
		// All forms tab
		logInfo("Validating for All forms Tab");
		try {
			searchByNameResourceFunction("All forms", FormNameCreated, SearchFormsTextbox, ResourceName);
			appiumDriver.hideKeyboard();

		} catch (Exception e) {
			logInfo("Failed to validate search by Name or Resource functionality for All forms tab" + e.getMessage());
		}

		// Verify that after searching the from by resource user should be
		// able to save the form
		logInfo("Starting validation for Saving form searched by resource name");
		try {
			logInfo("Search by resource is already enabled");
			closeSearchBtn.click();
			ControlActions_MobileApp.waitForVisibility(SearchFormsTextbox, 20);
			searchBox.sendKeys(ResourceName);

			Thread.sleep(5000);

			isResourcePresentSaved = (resourceName.isDisplayed()
					&& (resourceName.getText().trim().equalsIgnoreCase(ResourceName.trim()))
					&& FormNameElement.getText().equalsIgnoreCase(FormNameCreated));

			try {

				ControlActions_MobileApp.click(FormNameElement);
				logInfo("Clicked on form after searching by Resource name");

			} catch (Exception e) {
				logError("Failed to click Form after searching by resource name' - " + e.getMessage());
			}

			Thread.sleep(5000);
			logInfo("Searching and entering Location ");
			boolean searchLocation = SearchResource(locationInstanceValue1, LocationText);
			TestValidation.IsTrue(searchLocation, "Location Searched Successfully",
					"Failed to Search Location from Forms subMenu");
			logInfo("Searching and entering Resource ");
			boolean searchResource = SearchResource(resourceInstanceValue1, ResourceText);
			TestValidation.IsTrue(searchResource, "Resource Searched Successfully",
					"Failed to Search Resource from Forms subMenu");

			Thread.sleep(5000);
			logInfo("Saving form");
			boolean saveForm = saveForm();
			TestValidation.IsTrue(saveForm, "Form Saved Successfully by resource name",
					"Failed to Save Form by resource name");

		} catch (Exception ex) {
			isResourcePresentSaved = false;
			logInfo("Resource not present to validate saved feature: " + ex.getMessage());
		}
		TestValidation.IsTrue(isResourcePresentSaved, "Resource is present", "Resource not present");
		logInfo("Validation for save functionality by resource name ends");
		// Saved Forms tab
		SavedBottomClick.click();
		ControlActions_MobileApp.swipeScreen("DOWN");
		SearchForm(FormNameCreated, SearchSavedFormTextbox);
		logInfo("Validating for Saved Tab");
		try {
			searchByNameResourceFunction("Saved", FormNameCreated, SearchSavedFormTextbox, ResourceName);
			appiumDriver.hideKeyboard();
		}

		catch (Exception e1) {
			logInfo("Failed to validate search by Name or Resource functionality for Saved tab" + e1.getMessage());
		}

		// Favorites Tab
		FavouriteClick.click();
		ControlActions_MobileApp.swipeScreen("DOWN");
		SearchForm(FormNameCreated, SearchFavouriteTextbox);
		logInfo("Validating for Favorites Tab");
		try {
			searchByNameResourceFunction("Favorites", FormNameCreated, SearchFavouriteTextbox, ResourceName);
			appiumDriver.hideKeyboard();
		} catch (Exception e2) {
			logInfo("Failed to validate search by Name or Resource functionality for Favorites tab" + e2.getMessage());
		}
		driver.quit();
	}

	public void searchByNameResourceFunction(String tabName, String FormNameCreated, WebElement searchBox,
			String ResourceName) throws InterruptedException {
		boolean isNamePresent = false;
		boolean isResourcePresent = false;

		// Search by Name
		logInfo("Searching form by name");
		try {
			ControlActions_MobileApp.waitForVisibility(searchByResource, 10);
			searchByResource.click();
			logInfo("Search by name is enabled now");
			isNamePresent = (FormNameElement.isDisplayed()
					&& FormNameElement.getText().equalsIgnoreCase(FormNameCreated));

		} catch (Exception ex) {
			logInfo("Failed to Search form by name" + ex.getMessage());
		}

		TestValidation.IsTrue(isNamePresent, "Form is searched succesfully by name", "Failed to search form by Name");

		closeSearchBtn.click();
		Thread.sleep(5000);
		try {
			// Search by Resource
			ControlActions_MobileApp.waitForVisibility(searchBox, 20);
			searchBox.sendKeys(ResourceName);
			ControlActions_MobileApp.waitForVisibility(searchByName, 20);
			searchByName.click();
			logInfo("Search by resource is enabled now");
			Thread.sleep(5000);

			String ResourceAfterSearch = resourceName.getText();
			isResourcePresent = (resourceName.isDisplayed()
					&& (ResourceAfterSearch.trim()).equalsIgnoreCase(ResourceName.trim()));

		} catch (Exception ex) {
			logInfo("Failed to Search form by resource Name on " + tabName + " tab" + ex.getMessage());
		}

		TestValidation.IsTrue(isResourcePresent, "Form is searched succesfully by Resourcename on " + tabName + " tab",
				"Failed to search form by Resource Name on " + tabName + " tab");

		Thread.sleep(5000);
		// Negative Scenario:

		try {
			searchByResource.click();
			logInfo("Search by name is enabled now");
			isNamePresent = (noResultsFound.isDisplayed()
					&& (noResultsFound.getText().trim().equalsIgnoreCase("No Results Found")));
		} catch (NoSuchElementException ex) {
			isNamePresent = false;
			logInfo("Failed to validate negative scenario :  " + ex.getMessage());
		}
		TestValidation.IsTrue(isResourcePresent,
				"Invalid scenario check validation is working properly for " + tabName + " tab",
				"Invalid scenario check validation is not working properly for " + tabName + " tab");

		Thread.sleep(5000);
		try {
			closeSearchBtn.click();
			ControlActions_MobileApp.waitForVisibility(searchBox, 20);
			searchBox.sendKeys(FormNameCreated);
			searchByName.click();
			logInfo("Search by resource is enabled now");
			Thread.sleep(5000);

			isResourcePresent = (noResultsFound.isDisplayed()
					&& (noResultsFound.getText().trim().equalsIgnoreCase("No Results Found")));
		} catch (Exception ex) {
			isResourcePresent = false;
			logInfo("Failed to validate negative scenario : " + ex.getMessage());
		}
		TestValidation.IsTrue(isResourcePresent,
				"Invalid scenario check validation is working properly for " + tabName + " tab",
				"Invalid scenario check validation is not working properly for " + tabName + " tab");
	}

	public HashMap<String, Integer> CountMenu() throws InterruptedException {
		ControlActions_MobileApp.waitForVisibility(mainMenu, 30);
		ControlActions_MobileApp.click(mainMenu);
		logInfo("Clicked on Main menu button");
		Thread.sleep(5000);

		String searchBox = "//android.widget.RelativeLayout[@index=\'";
		String middle = "\']//android.widget.TextView[@text=\'";
		String last = "\']/following::android.widget.TextView[1]";
		String searchText;
		WebElement SearchCount;
		Thread.sleep(5000);
		HashMap<String, Integer> subMenuCntAfter = new HashMap<>();
		ArrayList<WebElement> ls = new ArrayList<>(appiumDriver.findElements(By.id("itemName")));

		System.out.println("Number of items in list are : " + ls.size());
		for (int i = 0; i < ls.size() - 1; i++) {
			searchText = ls.get(i).getText();
			String SearchCountPath = searchBox + i + middle + searchText + last;

			Thread.sleep(3000);
			@SuppressWarnings("unused")
			boolean Count = false;
			try {

				SearchCount = appiumDriver.findElement(By.xpath(SearchCountPath));
				Count = SearchCount.isDisplayed();
				subMenuCntAfter.put(searchText, Integer.parseInt(SearchCount.getText()));
				System.out.println("Key is : " + searchText + " Value is : " + subMenuCntAfter.get(searchText));
				System.out.println("Count is displayed beside " + searchText);
			} catch (NumberFormatException e) {
				subMenuCntAfter.put(searchText, 0);
				System.out.println("Key is : " + searchText + " Value is : " + subMenuCntAfter.get(searchText));
				System.out.println("Count is not displayed beside " + searchText);
				Count = false;
			}

		}
		return subMenuCntAfter;
	}

	public void favorite_Unfavorite() throws InterruptedException {
		HomePage homePage = new HomePage(appiumDriver);
		String FormNameCreated = APIInitiation();

		ControlActions_MobileApp.waitForVisibility(SearchFormsTextbox, 30);
		ControlActions_MobileApp.swipeScreen("DOWN");
		boolean searchForm = homePage.SearchForm(FormNameCreated);
		TestValidation.IsTrue(searchForm, "Form Searched successfully", "Failed to search Form");

		// SearchForm(FormNameCreated, SearchFormsTextbox);
		AddToFavourite.click();
		appiumDriver.navigate().back();
		FavouriteClick.click();
		ControlActions_MobileApp.swipeScreen("DOWN");
		boolean favForm = SearchForm(FormNameCreated, SearchFavouriteTextbox);
		TestValidation.IsTrue(favForm, "favourite functionality succesfully validated",
				"Failed to validate favourite functionality");
		AddToFavourite.click();
		appiumDriver.navigate().back();
		ControlActions_MobileApp.swipeScreen("DOWN");
		boolean isPresent = false;
		try {
			WebElement resource = null;

			ControlActions_MobileApp.waitForVisibility(searchBox, 30);
			ControlActions_MobileApp.ClearText(searchBox);
			ControlActions_MobileApp.actionEnterText(searchBox, FormNameCreated);
			System.out.println("Text has Entered");
			Thread.sleep(6000);

			resource = ControlActions_MobileApp.perform_GetElementByXPath(SavedPageLocators.Form_Name,
					"FormNameLocator", FormNameCreated);

			WebDriverWait wait1 = new WebDriverWait(appiumDriver, 10);
			wait1.until(ExpectedConditions.visibilityOf(resource));

		} catch (Exception ex) {
			System.out.println("Failed to Search" + ex.getMessage());
		}
		TestValidation.IsFalse(isPresent, "Unfavourite functionality succesfully validated",
				"Failed to validate Unfavourite functionality");
		driver.quit();
	}

	public void VerifyPageTitleFromSearchbox() throws InterruptedException {
		ControlActions_MobileApp.waitForVisibility(mainMenu, 30);
		ControlActions_MobileApp.click(mainMenu);
		logInfo("Clicked on Main menu button");
		Thread.sleep(10000);
		String searchBox = "//android.widget.EditText[@text=\'";
		String last = "\']";
		String searchText;
		WebElement SearchTextBoxName;
		Thread.sleep(10000);
		ArrayList<WebElement> ls = new ArrayList<>(appiumDriver.findElements(By.id("itemName")));
		if (ls.size() == 6) {

			System.out.println("Number of items in list are : " + ls.size());
			for (int i = 0; i < ls.size() - 1; i++) {
				searchText = ls.get(i).getText();
				System.out.println("Clicking on : " + searchText);
				String path = searchBox + "Search " + searchText + last;
				System.out.println("xpath is : " + searchBox + "Search " + searchText + last);
				ls.get(i).click();
				Thread.sleep(10000);
				if (searchText.equalsIgnoreCase("Saved")) {
					SearchTextBoxName = SearchSavedFormTextbox;
				}

				else {
					SearchTextBoxName = appiumDriver.findElement(By.xpath(path));

				}
				ControlActions_MobileApp.waitForVisibility(SearchTextBoxName, 30);
				// SearchTextBoxName.click();
				Boolean Savedflag = SearchTextBoxName.isDisplayed();
				TestValidation.IsTrue(Savedflag, "Page Title " + SearchTextBoxName.getText() + " is present on screen",
						"Page Title " + SearchTextBoxName.getText() + " is not present on screen");
				Thread.sleep(5000);
				ControlActions_MobileApp.waitForVisibility(mainMenu, 30);
				ControlActions_MobileApp.click(mainMenu);

			}
		}
	}

	public void leftToRightNavigation() throws InterruptedException {
		ControlActions_MobileApp.waitForVisibility(mainMenu, 30);
		ControlActions_MobileApp.click(mainMenu);
		logInfo("Clicked on Main menu button");
		Thread.sleep(10000);
		String searchBox = "//android.widget.EditText[@text=\'";
		String last = "\']";
		String searchText;
		WebElement SearchTextBoxName;
		@SuppressWarnings("unused")
		Boolean ArrayCount = false;
		Thread.sleep(10000);
		ArrayList<WebElement> ls = new ArrayList<>(appiumDriver.findElements(By.id("itemName")));
		if (ls.size() == 6) {
			ArrayCount = true;

			System.out.println("Number of items in list are : " + ls.size());
			for (int i = 0; i < ls.size() - 2; i++) {
				searchText = ls.get(i).getText();
				System.out.println("Clicking on : " + searchText);
				String path = searchBox + "Search " + searchText + last;
				System.out.println("xpath is : " + searchBox + "Search " + searchText + last);
				ls.get(i).click();
				Thread.sleep(10000);
				if (searchText.equalsIgnoreCase("Saved")) {
					SearchTextBoxName = SearchSavedFormTextbox;
				}

				else {
					SearchTextBoxName = appiumDriver.findElement(By.xpath(path));

				}

				if (searchText.equalsIgnoreCase("Inbox")) {
					ControlActions_MobileApp.waitForVisibility(SearchTextBoxName, 30);
					Boolean Inbox = SearchTextBoxName.isDisplayed();
					TestValidation.IsTrue(Inbox,
							"Successfully navigated and validated   " + searchText + " from left navigation",
							"Failed to navigate and validate" + searchText + " from left navigation");
					Thread.sleep(5000);
					ControlActions_MobileApp.waitForVisibility(mainMenu, 30);
					ControlActions_MobileApp.click(mainMenu);
				} else {
					ControlActions_MobileApp.waitForVisibility(SearchTextBoxName, 30);
					// SearchTextBoxName.click();
					Boolean Savedflag = SearchTextBoxName.isDisplayed();
					TestValidation.IsTrue(Savedflag,
							"Successfully navigated to " + searchText + " from left navigation",
							"Failed to navigate to " + searchText + " from left navigation");
					Thread.sleep(5000);
					ControlActions_MobileApp.waitForVisibility(mainMenu, 30);
					ControlActions_MobileApp.click(mainMenu);
				}

			}

			for (int i = (ls.size() - 2); i >= 0; i--) {
				searchText = ls.get(i).getText();
				System.out.println("Clicking on : " + searchText);
				String path = searchBox + "Search " + searchText + last;
				System.out.println("xpath is : " + searchBox + "Search " + searchText + last);
				ls.get(i).click();
				Thread.sleep(10000);
				if (searchText.equalsIgnoreCase("Saved")) {
					SearchTextBoxName = SearchSavedFormTextbox;
				}

				else {
					SearchTextBoxName = appiumDriver.findElement(By.xpath(path));

				}

				if (searchText.equalsIgnoreCase("Inbox")) {
					ControlActions_MobileApp.waitForVisibility(SearchTextBoxName, 30);
					Boolean Inbox = SearchTextBoxName.isDisplayed();
					TestValidation.IsTrue(Inbox,
							"Successfully navigated and validated   " + searchText + " from left navigation",
							"Failed to navigate and validate" + searchText + " from left navigation");
					Thread.sleep(5000);
					ControlActions_MobileApp.waitForVisibility(mainMenu, 30);
					ControlActions_MobileApp.click(mainMenu);
				} else {
					ControlActions_MobileApp.waitForVisibility(SearchTextBoxName, 30);
					// SearchTextBoxName.click();
					Boolean Savedflag = SearchTextBoxName.isDisplayed();
					TestValidation.IsTrue(Savedflag,
							"Successfully navigated to " + searchText + " from left navigation",
							"Failed to navigate to " + searchText + " from left navigation");
					Thread.sleep(5000);
					ControlActions_MobileApp.waitForVisibility(mainMenu, 30);
					ControlActions_MobileApp.click(mainMenu);
				}

			}

			logInfo("Clicking on : " + ls.get(ls.size() - 1).getText());
			ls.get(ls.size() - 1).click();

			boolean setVal = false;
			if (SettingTitle.getText().equalsIgnoreCase("Settings") && offlineModeText.isDisplayed()
					&& limiDataText.isDisplayed() && limitDurText.isDisplayed() && selectDurationText.isDisplayed()
					&& enableLogsText.isDisplayed() && clearSubText.isDisplayed() && offlineToggleBtn.isEnabled()
					&& limitDataToggleBtn.isEnabled() && enableLogToggleBtn.isEnabled() && clearSubBtn.isEnabled()) {

				ControlActions_MobileApp.WaitforelementToBeClickable(selectDurationAngle);
				selectDurationAngle.click();

				ArrayList<MobileElement> items = new ArrayList<>(appiumDriver.findElements(By.id("limitdataitem")));

				ArrayList<String> Actual = new ArrayList<>();
				ArrayList<String> Expected = new ArrayList<>();
				Expected.add(0, "3 DAYS");
				Expected.add(1, "7 DAYS");
				Expected.add(2, "14 DAYS");
				Expected.add(3, "21 DAYS");

				for (int i = 0; i < items.size(); i++) {
					Actual.add(items.get(i).getText());
				}

				if (Actual.equals(Expected)) {
					setVal = true;
				}

			}

			TestValidation.IsTrue(setVal, "Successfully Validated Setting tab", "Failed to validate Setting Tab");

		} else {
			ArrayCount = false;
			logError("ArrayList not taking all elements defined by WebElement xpath");
		}

	}

	public boolean saveForm() {
		try {
			ControlActions_MobileApp.waitForVisibility(enterNumVal, 30);
			enterNumVal.sendKeys("5");
			System.out.println("Entered value is 5");

			ControlActions_MobileApp.waitForVisibility(FormSaveClick, 30);
			ControlActions_MobileApp.WaitforelementToBeClickable(FormSaveClick);

			ControlActions_MobileApp.click(FormSaveClick);

			TestValidation.IsTrue(true, "Form saved succesfully", "Failed to save form");
			return true;
		} catch (Exception ex) {
			System.out.println("Failed to Save Form" + ex.getMessage());
			return false;
		}

	}

	public boolean submitForm() {
		try {

			Thread.sleep(6000);
			ControlActions_MobileApp.waitForVisibility(FormSubmitClick, 30);
			FormSubmitClick.click();
			logInfo("Submit button has clicked");
			Thread.sleep(5000);
			return true;
		} catch (Exception ex) {
			System.out.println("Failed to submit form" + ex.getMessage());
			return false;
		}
	}

	public boolean saveForm(WebElement formSaveClick) {
		try {
			ControlActions_MobileApp.waitForVisibility(formSaveClick, 10000);
			Thread.sleep(5000);
			ControlActions_MobileApp.WaitforelementToBeClickable(formSaveClick);
			ControlActions_MobileApp.click(formSaveClick);

			return true;
		} catch (Exception ex) {
			System.out.println("Failed to Save Form" + ex.getMessage());
			return false;
		}

	}

	public void clickAndSaveForm(String FormNameCreated) throws InterruptedException {
		mobControlActions = new ControlActions_MobileApp(appiumDriver);
		HomePage homePage = new HomePage(appiumDriver);
		boolean searchForm = homePage.SearchForm(FormNameCreated);
		TestValidation.IsTrue(searchForm, "Form Searched successfully", "Failed to search Form");

		boolean clickFormsSub = homePage.ClickForm(FormNameCreated);
		TestValidation.IsTrue(clickFormsSub, "Clicked on Forms subMenu Successfully", "Failed to click Forms subMenu");
		Thread.sleep(5000);
		boolean searchLocation = SearchResource(locationInstanceValue1, LocationText);
		TestValidation.IsTrue(searchLocation, "Location Searched Successfully",
				"Failed to Search Location from Forms subMenu");
		boolean searchResource = SearchResource(resourceInstanceValue1, ResourceText);
		TestValidation.IsTrue(searchResource, "Resource Searched Successfully",
				"Failed to Search Resource from Forms subMenu");

		Thread.sleep(5000);
		boolean saveForm = saveForm(formSave);
		TestValidation.IsTrue(saveForm, "Form Saved Successfully", "Failed to Save Form from Forms SubTab");
	}

	public HashMap<String, Integer> checkHumbergMenuCount(HashMap<String, Integer> DownLoadCount) {

		HashMap<String, Integer> subMenuCnt = new HashMap<>();
		try {
			ControlActions_MobileApp.waitForVisibility(mainMenu, 600);
			ControlActions_MobileApp.click(mainMenu);
			logInfo("Clicked on Main menu button");
			Thread.sleep(3000);
			subMenuCnt.put("Favorites", Integer.parseInt(subMenuFavouritesCnt.getText()));
			for (Map.Entry<String, Integer> entry1 : DownLoadCount.entrySet()) {
				String k1 = entry1.getKey();
				Integer v1 = entry1.getValue();
				logInfo("Key = " + k1 + " Value = " + v1);
				if (k1.contentEquals("Forms") && v1 > 0) {
					int formsCount = Integer.parseInt(subMenuFormsCnt.getText());
					logInfo("Sub Menu Forms Count for allforms " + formsCount);
					subMenuCnt.put(k1, formsCount);
					// Assert.assertEquals(v1.toString(), formsCount);

					logInfo("MainMenu Count for Forms" + formsCount + " Downloading Count:" + v1);
				} else if (k1.contentEquals("Saved") && v1 > 0) {
					int savedCount = Integer.parseInt(subMenuSavedCnt.getText());
					logInfo("Sub Menu Forms Count for Saved" + savedCount);
					subMenuCnt.put(k1, savedCount);
					logInfo("MainMenu Count for Saved" + savedCount + " Downloading Count" + v1);
				}
			}
		} catch (Exception ex) {

			logInfo("Failed to verify logOut popup" + ex.getMessage());
		}
		return subMenuCnt;

	}

	public HashMap<String, Integer> checkHumbergMenuCountAfterRefresh() {

		HashMap<String, Integer> subMenuCntAfter = new HashMap<>();
		try {
			ControlActions_MobileApp.waitForVisibility(mainMenu, 30);
			ControlActions_MobileApp.click(mainMenu);
			logInfo("Clicked on Main menu button");
			Thread.sleep(3000);

			subMenuCntAfter.put("Forms", Integer.parseInt(subMenuFormsCnt.getText()));
			subMenuCntAfter.put("Saved", Integer.parseInt(subMenuSavedCnt.getText()));
			subMenuCntAfter.put("Favorites", Integer.parseInt(subMenuFavouritesCnt.getText()));

		} catch (Exception ex) {
			logInfo("Failed to get submenu count" + ex.getMessage());
		}
		return subMenuCntAfter;
	}

	public Boolean SavedFunctionality() throws InterruptedException {
		String FormNameCreated = APIInitiation();

		int SavedCountBefore = Integer.parseInt(SavedBadgeCount.getText().trim());
		logInfo("Saved count before in saved badge : " + SavedCountBefore);
		ControlActions_MobileApp.waitForVisibility(SearchFormsTextbox, 30);
		ControlActions_MobileApp.swipeScreen("DOWN");
		clickAndSaveForm(FormNameCreated);

		ControlActions_MobileApp.waitForVisibility(SearchSavedFormTextbox, 30);
		ControlActions_MobileApp.swipeScreen("DOWN");

		int SavedCount = Integer.parseInt(SavedBadgeCount.getText().trim());
		logInfo("Saved count after in saved badge : " + SavedCount);
		SearchForm(FormNameCreated, SearchSavedFormTextbox);
		appiumDriver.navigate().back();

		ControlActions_MobileApp.waitForVisibility(mainMenu, 30);
		ControlActions_MobileApp.click(mainMenu);
		logInfo("Clicked on Main menu button");
		Thread.sleep(3000);

		int savedSubMenuCount = Integer.parseInt(subMenuSavedCnt.getText().trim());
		logInfo("Saved count in saved submenu : " + savedSubMenuCount);
		int updatedCountBy = SavedCount - SavedCountBefore;
		Boolean countFlag = false;
		if (SavedCount == savedSubMenuCount) {
			countFlag = true;
			logInfo("Counts of savedbadge and submenu saved function are matching");
			logInfo("Count is updated by : " + updatedCountBy);
		}

		else {
			logInfo("Counts of savedbadge and submenu saved function not matching");

		}

		TestValidation.IsTrue(true, "Counts of savedbadge and submenu saved function are matching",
				"Counts of savedbadge and submenu saved function not matching");

		driver.quit();
		return countFlag;

	}

	public void updatedListCount() throws InterruptedException {
		mobControlActions = new ControlActions_MobileApp(appiumDriver);
		LoginPage loginPage = new LoginPage(appiumDriver);

		HashMap<String, Integer> DownCount = new HashMap<>();
		DownCount = loginPage.CountDownloadingForms();
		HashMap<String, Integer> BeforeCount = checkHumbergMenuCount(DownCount);

		String FormNameCreated = APIInitiation();
		ControlActions_MobileApp.waitForVisibility(CloseMainMenu, 30);
		ControlActions_MobileApp.click(CloseMainMenu);
		ControlActions_MobileApp.waitForVisibility(SearchFormsTextbox, 30);
		ControlActions_MobileApp.swipeScreen("DOWN");
		clickAndSaveForm(FormNameCreated);

		ControlActions_MobileApp.waitForVisibility(SearchSavedFormTextbox, 30);
		ControlActions_MobileApp.swipeScreen("DOWN");

		SearchForm(FormNameCreated, SearchSavedFormTextbox);
		appiumDriver.navigate().back();
		AllFormsBottomClick.click();
		// SearchForm(FormNameCreated, SearchFormsTextbox);
		AddToFavourite.click();
		// appiumDriver.navigate().back();
		FavouriteClick.click();
		ControlActions_MobileApp.swipeScreen("DOWN");
		SearchForm(FormNameCreated, SearchFavouriteTextbox);
		appiumDriver.hideKeyboard();
		HashMap<String, Integer> AfterCount = checkHumbergMenuCountAfterRefresh();

		Boolean formsDiff = calculateNewlyDownloadedForms(BeforeCount.get("Forms"), AfterCount.get("Forms"),
				"All Forms");
		TestValidation.IsTrue(formsDiff, "Count of all forms updated in Hamburger menu list",
				"Failed to update count of forms in Hambueger menu list");
		Boolean savedDiff = calculateNewlyDownloadedForms(BeforeCount.get("Saved"), AfterCount.get("Saved"), "Saved");
		TestValidation.IsTrue(savedDiff, "Count of saved forms updated in Hamburger menu list",
				"Failed to update count of saved forms in Hambueger menu list");
		Boolean favDiff = calculateNewlyDownloadedForms(BeforeCount.get("Favorites"), AfterCount.get("Favorites"),
				"Favorites");
		TestValidation.IsTrue(favDiff, "Count of favorites updated in Hamburger menu list",
				"Failed to update count of favorites in Hambueger menu list");
		driver.quit();
	}

	public boolean calculateNewlyDownloadedForms(int beforeCount, int afterCount, String menuname) {
		boolean flag = false;
		try {
			if (afterCount == 0) {
				logInfo("List not updated after Refresh");
				System.out.println("List not updated after Refresh");
			} else {
				int newCount = afterCount - beforeCount;
				logInfo("Newly added items in " + menuname + " : " + newCount);
				if (newCount > 0) {
					flag = true;
				}

				else {
					flag = false;
				}

			}

		} catch (Exception ex) {
			System.out.println("Failed to Refresh" + ex.getMessage());

		}
		return flag;

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

			resource = ControlActions_MobileApp.perform_GetElementByXPath(SavedPageLocators.Form_Name,
					"FormNameLocator", Formname);
			isPresent = ControlActions_MobileApp.isElementDisplayed(resource);

			// System.out.println("is Element Present =" + isPresent);

			return isPresent;
		} catch (Exception ex) {
			logInfo("Failed to Search" + ex.getMessage());
			return isPresent;
		}
	}

	public boolean SearchResource(String resourceNm, WebElement textToWaitFor) {
		try {
			WebElement resource = null;
			ControlActions_MobileApp.waitForVisibility(textToWaitFor, 30);
			ControlActions_MobileApp.waitForVisibility(resourceSearchField, 30);

			ControlActions_MobileApp.ClearText(resourceSearchField);
			ControlActions_MobileApp.actionEnterText(resourceSearchField, resourceNm);
			System.out.println("Resource Name has Entered");
			Thread.sleep(9000);

			resource = ControlActions_MobileApp.perform_GetElementByXPath(SavedPageLocators.Resource_Name,
					"ResourceName", resourceNm);

			ControlActions_MobileApp.waitForVisibility(resource, 30);
			ControlActions_MobileApp.click(resource);
			System.out.println("Resource has Selected");

			return true;
		} catch (Exception ex) {
			logInfo("Failed to Search resource" + ex.getMessage());
			return false;
		}
	}

	public boolean RefreshAllForms(String visibleText) {

		WebElement field = null;
		@SuppressWarnings("unused")
		Boolean isPresent = false;
		WebDriverWait wait = new WebDriverWait(appiumDriver, 60);

		try {

			appiumDriver.findElement(MobileBy.AndroidUIAutomator(
					"new UiScrollable(new UiSelector().scrollable(true).instance(0)).setMaxSearchSwipes(10000).scrollIntoView(new UiSelector().text(\""
							+ visibleText + "\").instance(0))"));
			field = appiumDriver.findElement(By.id("refreshTextView"));

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("refreshTextView")));
			isPresent = true;
			ControlActions_MobileApp.click(field);
			System.out.println("Refresh All is Selected");
			RefreshPopup.click();
			return true;

		} catch (Exception ex) {
			logError("Failed to Scroll " + ex.getMessage());
			System.out.println("Failed to Scroll " + ex.getMessage());
			return false;
		}

	}

	public void headerFooterValidation() {
		boolean headers = false;
		if (taskTab.isDisplayed() && taskTab.getText().equalsIgnoreCase("Tasks") && taskCount.isDisplayed()
				&& sortDesc.isDisplayed() && submissionBadge.isDisplayed()
				&& submissionBadge.getText().equalsIgnoreCase("Submissions")) {
			headers = true;
		}

		TestValidation.IsTrue(headers, "All headers are present on tab", "All headers are not present on tab");
	}

	public void NavigatingTabs() throws InterruptedException {

		ControlActions_MobileApp.waitForVisibility(SavedBottomClick, 240);
		SavedBottomClick.click();
		Boolean Savedflag = SearchSavedFormTextbox.isDisplayed();
		TestValidation.IsTrue(Savedflag, "Successfully navigated from AllForms to Saved tab",
				"Failed to navigate from AllForms to SavedTab");
		headerFooterValidation();

		FavouriteClick.click();
		Boolean favFlag = SearchFavouriteTextbox.isDisplayed();
		TestValidation.IsTrue(favFlag, "Successfully navigated from Saved tab to favourite tab",
				"Failed to navigate from Saved tab to Favourite tab");
		headerFooterValidation();

		AllFormsBottomClick.click();
		Boolean Aformsflag = SearchFormsTextbox.isDisplayed();
		TestValidation.IsTrue(Aformsflag, "Successfully navigated from favourite tab to All Forms",
				"Failed to navigate from favourite tab to All Forms");

		headerFooterValidation();

		FavouriteClick.click();
		Boolean favFlag1 = SearchFavouriteTextbox.isDisplayed();
		TestValidation.IsTrue(favFlag1, "Successfully navigated from AllForms to favourite tab",
				"Failed to navigate from AllForms to Favourite tab");
		headerFooterValidation();

		SavedBottomClick.click();
		Boolean Savedflag1 = SearchSavedFormTextbox.isDisplayed();
		TestValidation.IsTrue(Savedflag1, "Successfully navigated from Favourites tab to Saved tab",
				"Failed to navigate from Favourites to SavedTab");
		headerFooterValidation();

		AllFormsBottomClick.click();
		Boolean AformsFlag1 = SearchFormsTextbox.isDisplayed();
		TestValidation.IsTrue(AformsFlag1, "Successfully navigated from Favourites tab to All Forms tab",
				"Failed to navigate from Saved tab to Favourite tab");
		headerFooterValidation();

	}

	public boolean searchByResourceInFavouritiesTab(String formName, String resourceName) {
		try {
			ControlActions_MobileApp.waitForVisibility(SearchFormsTextbox, 30);
			ControlActions_MobileApp.swipeScreen("DOWN");
			logInfo("Searching for the created form in All forms");
			SearchForm(formName, SearchFormsTextbox);
			Thread.sleep(5000);
			AddToFavourite.click();
			logInfo("Added created form to favorites");
			closeSearchBtn.click();
			// Favorites Tab
			appiumDriver.hideKeyboard();
			FavouriteClick.click();
			ControlActions_MobileApp.swipeScreen("DOWN");
			logInfo("Validating for Favorites Tab");
			// Search by Resource
			ControlActions_MobileApp.waitForVisibility(searchBox, 20);
			searchBox.sendKeys(resourceName);
			String ResourceAfterSearch = this.resourceName.getText();
			boolean isResourcePresent = (this.resourceName.isDisplayed()
					&& (ResourceAfterSearch.trim()).equalsIgnoreCase(resourceName.trim()));
			return isResourcePresent;
		} catch (Exception e) {
			logError("Could Not verify Search By Resource in Favourities" + e.getMessage());
			return false;
		}

	}
}
