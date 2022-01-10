package com.project.safetychain.mobileapp.pageobjects;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
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
import com.project.safetychain.api.models.support.Support.TaskOccurence;
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
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class REG_InboxTaskPage extends TestBase {

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
	REG_InboxTaskPage afsaved;
	FSQABrowserRecordsPage fbrp;
	TCG_SMK_FormSavedFlow savedTc;

	public REG_InboxTaskPage(AppiumDriver<MobileElement> driver) {
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

	public void InboxFilterDisplayedContents() {
		boolean FilterPage = false;
		System.out.println(inboxFilterHeader.getText());
		System.out.println(assignedToFilter.getText());
		System.out.println(statusFilter.getText());
		System.out.println(dueByFilter.getText());
		if (inboxFilterHeader.getText().equalsIgnoreCase("Inbox Filters") && InboxFilterCrossBtn.isDisplayed()
				&& InboxFilterCrossBtn.isEnabled() && filterClear.isDisplayed() && filterClear.isEnabled()
				&& filter.isDisplayed() && filter.isEnabled() && assignedToFilter.isDisplayed()
				&& assignedToFilter.getText().equalsIgnoreCase("ASSIGNED TO") && statusFilter.isDisplayed()
				&& statusFilter.getText().equalsIgnoreCase("STATUS") && dueByFilter.isDisplayed()
				&& dueByFilter.getText().equalsIgnoreCase("DUE BY") && assignedToDDAngle.isDisplayed()
				&& assignedToDDAngle.isEnabled() && statusDDAngle.isDisplayed() && statusDDAngle.isEnabled()
				&& dueByDDAngle.isDisplayed() && dueByDDAngle.isEnabled()) {
			FilterPage = true;
		}
		TestValidation.IsTrue(FilterPage, "Contents of Filter page of Inbox are as expected",
				"Contents of Filter page of Inbox are not as expected ");
	}

	public void StatusFilterValidate() throws InterruptedException, ParseException {
		HomePage homePage = new HomePage(appiumDriver);
		mobControlActions = new ControlActions_MobileApp(appiumDriver);
		TaskPage InboxPageObj = new TaskPage(appiumDriver);
		SavedPage SavedPageObj = new SavedPage(appiumDriver);
		MultipleTaskCreation();
		ControlActions_MobileApp.swipeScreen("DOWN");
		boolean footerSelect = false;
		boolean clickSettingsMenu = homePage.ClickSubMenu(homePage.settingsSubMenu);
		TestValidation.IsTrue(clickSettingsMenu, "Clicked on settings subMenu Successfully",
				"Failed to click Settings subMenu");
		boolean selectdataLimit1 = InboxPageObj.selectDataLimit("21 DAYS");

		TestValidation.IsTrue(selectdataLimit1, "Selected 21 DAYS data limit from Settings",
				"Failed to select 21 DAYS data limit from Settings");

		ControlActions_MobileApp.click(SavedPageObj.settingsBckBtn);

		boolean clickInbox = false;
		try {
			ControlActions_MobileApp.waitForVisibility(homePage.inboxSubMenu, 160);
			ControlActions_MobileApp.click(homePage.inboxSubMenu);
			logInfo("Clicked on Submenu ");
			Thread.sleep(6000);
			clickInbox = true;
		} catch (Exception ex) {
			logInfo("Failed To click On SubMenu " + ex.getMessage());
			clickInbox = false;
		}

		TestValidation.IsTrue(clickInbox, "Clicked on Inbox Sub Menu", "Failed to click Inbox Submenu");
		InboxHeaderFooterValidate();
		if (Arrays.asList("Past Due", "Due Later", "Due Today", "No Due Date").contains(TaskHeaderTitle.getText())) {
			footerSelect = true;
		}

		TestValidation.IsTrue(footerSelect, "Status tab is selected by default in Inbox",
				" Status tab is not selected by Default in Inbox");
		ControlActions_MobileApp.WaitforelementToBeClickable(FilterIcon);
		FilterIcon.click();
		ControlActions_MobileApp.WaitforelementToBeClickable(statusDDAngle);
		statusDDAngle.click();

		InboxFilterDisplayedContents();
		StatusSelectDD();

	}

	public void StatusSelectDD() throws InterruptedException, ParseException {
		mobControlActions = new ControlActions_MobileApp(appiumDriver);
		TaskPage taskPage = new TaskPage(appiumDriver);
		boolean statSelect = false;

		List<MobileElement> Statopt = new CopyOnWriteArrayList<>(appiumDriver.findElementsById("filterOptionBtn"));
		System.out.println("Array size:" + Statopt.size());

		for (int j = 0; j < Statopt.size(); j++) {

			String[] sp = Statopt.get(j).getText().split("-", 2);
			if (!sp[0].equalsIgnoreCase("No Due Date")) {
				int tasks = Integer.parseInt(sp[1].trim());

				System.out.println(tasks);
				System.out.println("Count is:" + tasks);
				System.out.println(Statopt.get(j).getText());
				String opt = sp[0].trim(); // Statopt.get(j).getText();
				System.out.println("Status to search for :" + opt);
				ControlActions_MobileApp.actionClick(Statopt.get(j));
				logInfo("Clicked on Status:" + Statopt.get(j).getText());
				String checkbox = Statopt.get(j).getAttribute("checked");
				// Thread.sleep(5000);
				try {
					if (checkbox.equalsIgnoreCase("true")) {
						statSelect = true;
						logInfo("Selected Status filter");
					}
					TestValidation.IsTrue(statSelect, "Selected STATUS filter succesfully",
							"Failed to select STATUS filter filter");

				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				ControlActions_MobileApp.waitForVisibility(taskPage.CalFilterBtn, 50);
				ControlActions_MobileApp.click(taskPage.CalFilterBtn);
				System.out.println("Clicked on filter button");
				Thread.sleep(5000);
				boolean verTaskFilterResult = gridCompareWithStatus(tasks, opt);
				TestValidation.IsTrue(verTaskFilterResult, "Verified Filter Result Grid",
						"Failed to verify Filter Result Grid");
				try {
					// ControlActions_MobileApp.WaitforelementToBeClickable(FilterIcon);
					FilterIcon.click();
					// ControlActions_MobileApp.WaitforelementToBeClickable(statusDDAngle);
					// statusDDAngle.click();
					// logInfo("Clicked on status angle button");
					// ControlActions_MobileApp.WaitforelementToBeClickable(Statopt.get(j));
					(Statopt.get(j)).click();
					;
					logInfo("Deselected previous Status:" + Statopt.get(j).getText());
				} catch (StaleElementReferenceException e) {
					Thread.sleep(20000);
					ControlActions_MobileApp.waitForVisibility(FilterIcon, 30);
					// ControlActions_MobileApp.WaitforelementToBeClickable(FilterIcon);
					FilterIcon.click();
					// ControlActions_MobileApp.WaitforelementToBeClickable(statusDDAngle);
					// ControlActions_MobileApp.waitForVisibility(statusDDAngle,
					// 30);
					// statusDDAngle.click();
					// ControlActions_MobileApp.WaitforelementToBeClickable(Statopt.get(j));
					(Statopt.get(j)).click();
					;
					logInfo("Deselected previous Status:" + Statopt.get(j).getText());

				} catch (Exception e) {
					Thread.sleep(20000);
					ControlActions_MobileApp.waitForVisibility(FilterIcon, 30);
					// ControlActions_MobileApp.WaitforelementToBeClickable(FilterIcon);
					FilterIcon.click();
					// ControlActions_MobileApp.WaitforelementToBeClickable(statusDDAngle);
					statusDDAngle.click();
					// ControlActions_MobileApp.WaitforelementToBeClickable(Statopt.get(j));
					(Statopt.get(j)).click();
					;
					logInfo("Deselected previous Status:" + Statopt.get(j).getText());
				}

			}

		}
		ControlActions_MobileApp.WaitforelementToBeClickable(submissionBadge);
		submissionBadge.click();
		ControlActions_MobileApp.WaitforelementToBeClickable(taskCountBadge);
		taskCountBadge.click();
	}

	public void DueByFilterValidate() throws InterruptedException, ParseException {

		mobControlActions = new ControlActions_MobileApp(appiumDriver);
		TaskPage taskPage = new TaskPage(appiumDriver);
		Thread.sleep(10000);
		ControlActions_MobileApp.waitForVisibility(taskCountBadge, 120);
		ControlActions_MobileApp.WaitforelementToBeClickable(taskCountBadge);
		taskCountBadge.click();
		Thread.sleep(10000);
		String dueDate = ControlActions_MobileApp.AddDaystoToddaysDateInGivenFormat("MMMM d", 0);
		System.out.println("Due Date is :" + dueDate);
		@SuppressWarnings("unused")
		String dueDate1 = ControlActions_MobileApp.AddDaystoToddaysDateInGivenFormat("MMMM d", 1);

		int totalCount = dueByCount();

		ControlActions_MobileApp.waitForVisibility(dueByFilter, 60);
		ControlActions_MobileApp.WaitforelementToBeClickable(dueByDDAngle);
		dueByDDAngle.click();
		Thread.sleep(5000);
		boolean selDueByFilter = false;
		System.out.println("No.of forms expected for selected Due By date :" + totalCount);

		try {

			WebElement formCheck = null;
			String currentMonth = taskPage.currentMonthTxt.getText();
			logInfo("Current Mnth Selected = " + currentMonth);

			List<String> currentMonthPart = Arrays.asList(currentMonth.split(" "));
			logInfo("Current Month = " + currentMonthPart.get(0) + "Year = " + currentMonthPart.get(1));
			List<String> dateParts = Arrays.asList(dueDate.split(" "));
			do {

				logInfo("Due date to be selected = " + dateParts.get(1) + "MOnth = " + dateParts.get(0));
				if (dateParts.get(0).equals(currentMonthPart.get(0))) {
					formCheck = ControlActions_MobileApp.perform_GetElementByXPath(TaskPageLocators.DAY_TOBE_BTN, "DAY",
							dateParts.get(1));
					ControlActions_MobileApp.waitForVisibility(formCheck, 50);
					ControlActions_MobileApp.WaitforelementToBeClickable(formCheck);
					ControlActions_MobileApp.actionClick(formCheck);
					ControlActions_MobileApp.click(formCheck);
					formCheck.click();
					Thread.sleep(5000);

				} else {
					ControlActions_MobileApp.waitForVisibility(taskPage.nxtMonthBtn, 60);
					ControlActions_MobileApp.click(taskPage.nxtMonthBtn);
				}
			} while (!(dateParts.get(0).equals(currentMonthPart.get(0))));
			selDueByFilter = true;
		} catch (Exception e) {
			logError("Failed to Select AssignTo Filter - " + e.getMessage());
			selDueByFilter = false;
		}

		TestValidation.IsTrue(selDueByFilter, "Selected Due By Filter", "Failed to select Due By Filter");

		ControlActions_MobileApp.waitForVisibility(taskPage.CalFilterBtn, 50);
		ControlActions_MobileApp.click(taskPage.CalFilterBtn);
		System.out.println("Clicked on filter button");
		Thread.sleep(5000);
		ControlActions_MobileApp.WaitforelementToBeClickable(dueByBtn);
		dueByBtn.click();
		boolean verTaskFilterResult = gridCompareWithDueBy(totalCount, dueDate);
		TestValidation.IsTrue(verTaskFilterResult, "Verified Due By Listing succesfully",
				"Failed to verify Due By Listing");

	}

	@SuppressWarnings("unused")
	public int dueByCount() {

		int DueTodCount = 0;
		int PastDueCount = 0;
		int totalCount = 0;

		ControlActions_MobileApp.WaitforelementToBeClickable(FilterIcon);
		FilterIcon.click();

		ControlActions_MobileApp.waitForVisibility(statusFilter, 60);
		ControlActions_MobileApp.WaitforelementToBeClickable(statusDDAngle);
		statusDDAngle.click();

		ArrayList<MobileElement> Statopt = new ArrayList<>(appiumDriver.findElementsById("filterOptionBtn"));
		System.out.println("Array size:" + Statopt.size());
		for (int j = 0; j < Statopt.size(); j++) {

			String[] sp = Statopt.get(0).getText().split("-", 2);
			PastDueCount = Integer.parseInt(sp[1].trim());

			String[] sp1 = Statopt.get(1).getText().split("-", 2);
			DueTodCount = Integer.parseInt(sp1[1].trim());

			totalCount = PastDueCount;
			// PastDueCount + DueTodCount;

			if (sp[0].equalsIgnoreCase("Past Due")) {

				System.out.println(Statopt.get(j).getText());
				String opt = sp[0].trim(); // Statopt.get(j).getText();
				System.out.println("Status to search for :" + opt);
				ControlActions_MobileApp.actionClick(Statopt.get(j));
				logInfo("Clicked on Status:" + Statopt.get(j).getText());
				String checkbox = Statopt.get(j).getAttribute("checked");
				boolean statSel = false;
				try {
					if (checkbox.equalsIgnoreCase("true")) {
						statSel = true;
						logInfo("Selected Status filter");
					}
					TestValidation.IsTrue(statSel, "Selected STATUS filter succesfully",
							"Failed to select STATUS filter filter");

				} catch (Exception e) {
					System.out.println(e.getMessage());
				}

			}
			return totalCount;
		}

		return totalCount;
	}

	public Set<String> DueByGridResultCapture(int TotalCount) throws ParseException, InterruptedException {
		boolean formCount = false;
		ControlActions_MobileApp.waitForVisibility(searchBox, 30);
		Set<String> f = new HashSet<>();
		String firstEle = null;
		try {
			do {
				List<WebElement> ls = new CopyOnWriteArrayList<>(appiumDriver.findElementsById("resDueDate"));
				List<WebElement> res = new CopyOnWriteArrayList<>(appiumDriver.findElementsById("taskName"));
				System.out.println("Number of Elements in current grid are :" + ls.size());
				firstEle = ls.get(0).getText();

				for (int i = 0; i < ls.size(); i++) {

					f.add(ls.get(i).getText() + res.get(i).getText());
					System.out.println(ls.get(i).getText() + res.get(i).getText());
					ControlActions_MobileApp.swipe(400, 700, 400, 100);
				}
				System.out.println("Forms counted till now :" + f.size());

			} while (!(f.size() == TotalCount));
			if (f.size() == TotalCount) {
				formCount = true;
			} else {
				formCount = false;

			}

			TestValidation.IsTrue(formCount, "All forms are present as per count", "All forms are not present");
			throw new StaleElementReferenceException("End");

		} catch (

		StaleElementReferenceException e) {
			System.out.println("All forms counted");
			ControlActions_MobileApp.ScrollIntoView(firstEle);
			Thread.sleep(10000);

		}

		catch (Exception ex) {
			System.out.println(ex.getLocalizedMessage());

		}
		return f;

	}

	public boolean gridCompareWithDueBy(int count, String DueBy) throws ParseException, InterruptedException {
		boolean ResultAsExpected = false;
		Set<String> f = DueByGridResultCapture(count);
		int n = f.size();
		List<String> aList = new ArrayList<String>(n);
		System.out.println("Number of elements present in set are:" + f.size());
		aList.addAll(f);
		int counter = 0;
		System.out.println("Number of elements present in ArrayList  are:" + aList.size());
		String dueDate = ControlActions_MobileApp.AddDaystoToddaysDateInGivenFormat("MMMM d", 0);
		for (int i = 0; i < aList.size(); i++) {
			counter = counter + 1;
			if (DueBy.equalsIgnoreCase(dueDate)) {
				ResultAsExpected = PastDueFilter(aList.get(i));

			}

			System.out.println("forms compared till now:" + counter);

		}

		return ResultAsExpected;
	}

	public void WorkgroupFilterValidate() throws InterruptedException {

		TaskPage taskPage = new TaskPage(appiumDriver);
		@SuppressWarnings("unused")
		boolean sortedAsc = false;
		Thread.sleep(10000);
		ControlActions_MobileApp.waitForVisibility(taskCountBadge, 120);
		ControlActions_MobileApp.WaitforelementToBeClickable(taskCountBadge);
		taskCountBadge.click();
		Thread.sleep(10000);

		MultipleTaskCreation();
		ControlActions_MobileApp.swipeScreen("DOWN");
		System.out.println(WGName);
		ControlActions_MobileApp.WaitforelementToBeClickable(FilterIcon);
		FilterIcon.click();
		ControlActions_MobileApp.waitForVisibility(assignedToFilter, 60);
		ControlActions_MobileApp.WaitforelementToBeClickable(assignedToDDAngle);
		assignedToDDAngle.click();
		ArrayList<String> formsSorted = new ArrayList<>();
		@SuppressWarnings("unused")
		ArrayList<String> Actual = new ArrayList<>();
		@SuppressWarnings("unused")
		ArrayList<String> Expected = new ArrayList<>();
		boolean flag = false;
		boolean selected = false;
		while (!flag) {
			try {

				do {
					List<WebElement> ls = new CopyOnWriteArrayList<>(appiumDriver.findElementsById("filterOptionBtn"));

					System.out.println("Number of Elements in current grid are :" + ls.size());

					WebElement[] arr = new WebElement[ls.size()];
					arr = ls.toArray(arr);

					for (int j = 0; j < arr.length; j++) {

						if (!formsSorted.contains(arr[j].getText())) {
							formsSorted.add(j, arr[j].getText());
							System.out.println("Number of elements present in List are :" + formsSorted.size());
							if (ls.get(j).getText().equalsIgnoreCase(WGName + " - 4")) {

								flag = true;

								logInfo("Selecting assigned to filter");
								ControlActions_MobileApp.WaitforelementToBeClickable(ls.get(j));
								ControlActions_MobileApp.actionClick(ls.get(j));
								logInfo(ls.get(j).getAttribute("checked"));
								if (ls.get(j).getAttribute("checked").equalsIgnoreCase("true")) {
									selected = true;
									logInfo("Selected assigned to filter");
								}
								TestValidation.IsTrue(selected, "Selected Assigned to Workgroup succesfully",
										"Failed to select Assigned to filter");
								logInfo("Clicked on Workgroup");
							}
						}

					}
					ControlActions_MobileApp.swipe(400, 700, 400, 100);
				} while (!flag);

				ControlActions_MobileApp.waitForVisibility(taskPage.CalFilterBtn, 50);
				ControlActions_MobileApp.click(taskPage.CalFilterBtn);
				System.out.println("Clicked on filter button");
				Thread.sleep(5000);
				ControlActions_MobileApp.WaitforelementToBeClickable(workGroupBtn);
				workGroupBtn.click();
				boolean verTaskFilterResult = gridCompareWithWG(4, WGName);
				TestValidation.IsTrue(verTaskFilterResult, "Verified Worgroup Listing succesfully",
						"Failed to verify Workgroup Listing");

			}

			catch (Exception e) {
				System.out.println("Element is not yet visible");
			}
		}
	}

	public boolean gridCompareWithWG(int WGCount, String WGName) throws ParseException, InterruptedException {
		boolean ResultAsExpected = false;

		Set<String> f = WorkGroupGridResultCapture(WGCount);
		int n = f.size();
		List<String> aList = new ArrayList<String>(n);
		System.out.println("Number of elements present in set for workgroup Selected are:" + f.size());
		aList.addAll(f);
		int counter = 0;
		System.out.println("Number of elements present in ArrayList  workgroup Selected are:" + aList.size());
		for (int i = 0; i < aList.size(); i++) {
			counter = counter + 1;
			System.out.println(aList.get(i).substring(0, 21).trim());
			System.out.println(WGName);
			System.out.println(aList.get(i).substring(0, 21).trim().equalsIgnoreCase(WGName));
			ResultAsExpected = aList.get(i).substring(0, 21).trim().equalsIgnoreCase(WGName);

		}

		System.out.println("forms compared till now:" + counter);

		return ResultAsExpected;
	}

	public Set<String> WorkGroupGridResultCapture(int count) throws ParseException, InterruptedException {

		boolean formCount = false;
		ControlActions_MobileApp.waitForVisibility(searchBox, 30);
		Set<String> f = new HashSet<>();
		System.out.println("Total forms are: " + count);
		try {
			do {
				List<WebElement> ls = new CopyOnWriteArrayList<>(appiumDriver.findElementsById("resDueDate"));
				List<WebElement> res = new CopyOnWriteArrayList<>(appiumDriver.findElementsById("resName"));
				List<WebElement> WG = new CopyOnWriteArrayList<>(appiumDriver.findElementsById("groupName"));

				System.out.println("Number of Elements in current grid are :" + ls.size());

				for (int i = 0; i < res.size(); i++) {
					f.add(WG.get(i).getText() + ls.get(i).getText() + res.get(i).getText());
					System.out.println(WG.get(i).getText() + ls.get(i).getText() + res.get(i).getText());

				}

				System.out.println("Forms counted till now :" + f.size());
				ControlActions_MobileApp.swipe(400, 700, 400, 100);

			} while (!(f.size() == count));
			if (f.size() == count) {
				formCount = true;
			} else {
				formCount = false;

			}

			TestValidation.IsTrue(formCount, "All forms are present as per count", "All forms are not present");
			throw new StaleElementReferenceException("End");

		} catch (StaleElementReferenceException e) {
			System.out.println("All forms counted");

		}
		return f;

	}

	public Set<String> StatusGridResultCapture(int count, String Status) throws ParseException, InterruptedException {

		boolean formCount = false;
		ControlActions_MobileApp.waitForVisibility(searchBox, 30);
		Set<String> f = new HashSet<>();
		System.out.println("Total forms are: " + count);
		try {
			do {
				List<WebElement> ls = new CopyOnWriteArrayList<>(appiumDriver.findElementsById("resDueDate"));
				List<WebElement> res = new CopyOnWriteArrayList<>(appiumDriver.findElementsById("taskName"));
				String a = "//android.widget.TextView[@text=\'";
				String b = "\']//preceding-sibling::android.widget.TextView[1]";
				Thread.sleep(5000);
				System.out.println("Number of Elements in current grid are :" + ls.size());

				if (Status.equalsIgnoreCase("Due Today")) {
					for (int i = 0; i < res.size(); i++) {
						Thread.sleep(5000);
						f.add(ls.get(i).getText() + " " + res.get(i).getText());
						System.out.println(ls.get(i).getText() + " " + res.get(i).getText());

						ControlActions_MobileApp.swipe(400, 700, 400, 100);
					}
				} else {
					for (int i = 0; i < ls.size(); i++) {
						Thread.sleep(5000);
						f.add(ls.get(i).getText()
								+ appiumDriver.findElement(By.xpath(a + ls.get(i).getText() + b)).getText());
						System.out.println(ls.get(i).getText() + " "
								+ appiumDriver.findElement(By.xpath(a + ls.get(i).getText() + b)).getText());
						ControlActions_MobileApp.swipe(400, 700, 400, 100);
					}
				}
				System.out.println("Forms counted till now :" + f.size());

			} while (!(f.size() == count));
			if (f.size() == count) {
				formCount = true;
			} else {
				formCount = false;

			}

			TestValidation.IsTrue(formCount, "All forms are present as per count", "All forms are not present");
			throw new StaleElementReferenceException("End");

		} catch (StaleElementReferenceException e) {
			System.out.println("All forms counted");
			ControlActions_MobileApp.ScrollIntoView(Status);
			Thread.sleep(20000);

		} catch (Exception ex) {
			System.out.println(ex.getLocalizedMessage());
			ControlActions_MobileApp.ScrollIntoView(Status);
		}
		return f;

	}

	public boolean PastDueFilter(String taskName) throws ParseException {
		boolean isBefore = false;
		boolean AsExpected = false;
		System.out.println(taskName);
		String dueDate = taskName.substring(5, 23);

		// counter++;
		logInfo("Due Date = " + dueDate);

		DateFormat format = new SimpleDateFormat("MMM dd, yyyy HH:mm");

		Date date = format.parse(dueDate.trim());

		logInfo("Date to be compared = " + date);
		@SuppressWarnings("unused")
		String ExpectedTimestamp = ControlActions_MobileApp.formatdateAndTime("MMM dd, yyyy HH:mm");
		SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy HH:mm");
		String date1 = sdf.format(new Date());
		Date dateA = sdf.parse(date1);
		System.out.println("dateA:" + dateA);
		if (date.before(dateA)) {
			isBefore = true;
			AsExpected = true;
			logInfo("Filtered date is less than Current Date : As Expected");
			System.out.println();
			System.out.println();

		} else {
			isBefore = false;
			logInfo("Filtered date is greater than Current Date for Past due :Failed");
		}
		TestValidation.IsTrue(isBefore, "Filtered date is less than Current Date : As Expected",
				"Filtered date is greater than Current Date for Past due :Failed");
		return AsExpected;
	}

	public boolean DueLaterFilter(String taskName) throws ParseException {

		boolean isAfter = false;
		boolean AsExpected = false;
		System.out.println(taskName);

		String dueDate = taskName.substring(5, 26);

		logInfo("Due Date = " + dueDate);

		DateFormat format = new SimpleDateFormat("MMM dd, yyyy HH:mm");

		Date date = format.parse(dueDate.trim());

		logInfo("Date to be compared = " + date);
		@SuppressWarnings("unused")
		String ExpectedTimestamp = ControlActions_MobileApp.formatdateAndTime("MMM dd, yyyy HH:mm");
		SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy HH:mm");
		String date1 = sdf.format(new Date());
		Date dateA = sdf.parse(date1);
		logInfo("Actual Date = " + dateA);
		logInfo("Date to compared with = " + date);
		if (date.after(dateA)) {
			isAfter = true;
			AsExpected = true;
			logInfo("Filtered date is greater than Current Date : As Expected");

		} else {
			isAfter = false;
			logInfo("Filtered date is less than Current Date for due  Later:Failed");
		}
		TestValidation.IsTrue(isAfter, "Filtered date is greater than Current Date : As Expected",
				"Filtered date is less than Current Date for Past due :Failed");
		return AsExpected;
	}

	public boolean DueTodayFilter(String taskName, int startIndex, int endIndex) throws ParseException {
		boolean isToday = false;
		boolean AsExpected = false;

		System.out.println(taskName);

		String dueDate = taskName.substring(startIndex, endIndex) + " ";
		logInfo("Due Date = " + dueDate);

		DateFormat format = new SimpleDateFormat("MMM dd, yyyy HH:mm");

		Date date = format.parse(dueDate.trim());

		logInfo("Date Converted to format = " + date);
		SimpleDateFormat formatter = new SimpleDateFormat("MMM dd, yyyy");

		String dateInString = taskName.substring(startIndex, endIndex).trim();
		System.out.println("Date to be compared=" + taskName.substring(startIndex, endIndex).trim());
		try {
			Date dateCompare = formatter.parse(dateInString);
			System.out.println(dateCompare);
			System.out.println(formatter.format(dateCompare));
			System.out.println(formatter.format(date));

		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println("System date is :" + formatter.format(formatter.parse(dateInString)));
		System.out.println("Date which is being compared is:" + formatter.format(date));

		if ((formatter.format(date)).equals(formatter.format(formatter.parse(dateInString)))) {
			isToday = true;
			AsExpected = true;
			logInfo("Filtered date is equal to Current Date : As Expected");

		} else {
			isToday = false;
			logInfo("Filtered date is not equal to Current Date for Due Today:Failed");
		}
		TestValidation.IsTrue(isToday, "Filtered date is equal to Current Date : As Expected",
				"Filtered date is not equal to Current Date for Due Today :Failed");
		return AsExpected;
	}

	public boolean gridCompareWithStatus(int count1, String Status1) throws ParseException, InterruptedException {
		boolean ResultAsExpected = false;
		Set<String> f = StatusGridResultCapture(count1, Status1);
		int n = f.size();
		List<String> aList = new ArrayList<String>(n);
		System.out.println("Number of elements present in set for " + Status1 + " are:" + f.size());
		aList.addAll(f);
		int counter = 0;
		System.out.println("Number of elements present in ArrayList  for " + Status1 + " are:" + aList.size());
		for (int i = 0; i < aList.size(); i++) {
			counter = counter + 1;
			if (Status1.equalsIgnoreCase("Past Due")) {
				ResultAsExpected = PastDueFilter(aList.get(i));

			} else if (Status1.equalsIgnoreCase("Due Later")) {
				ResultAsExpected = DueLaterFilter(aList.get(i));
			} else if (Status1.equalsIgnoreCase("Due Today")) {
				ResultAsExpected = DueTodayFilter(aList.get(i), 5, 22);
			}

			System.out.println("forms compared till now:" + counter);
		}

		return ResultAsExpected;

	}

	public void scrollingBetweenFilterOptionsWithSortValidation() throws InterruptedException, ParseException {
		;
		// String AssignedToFilter; // need to take from multipletaskcreation

		MultipleTaskCreation();
		System.out.println("WG is:" + WGName);
		ControlActions_MobileApp.swipeScreen("DOWN");
		mobControlActions = new ControlActions_MobileApp(appiumDriver);
		TaskPage taskPage = new TaskPage(appiumDriver);
		ArrayList<String> formsSorted = new ArrayList<>();
		boolean flag = false;
		boolean selected = false;

		Thread.sleep(10000);
		ControlActions_MobileApp.WaitforelementToBeClickable(taskCountBadge);
		taskCountBadge.click();
		Thread.sleep(10000);
		ArrayList<String> formsBeforeFilter = new ArrayList<>();
		ArrayList<String> formsAfterFilter = new ArrayList<>();

		List<WebElement> ls1 = new CopyOnWriteArrayList<>(appiumDriver.findElementsById("resDueDate"));
		List<WebElement> res = new CopyOnWriteArrayList<>(appiumDriver.findElementsById("resName"));

		for (int i = 0; i < ls1.size(); i++) {
			formsBeforeFilter.add(i, ls1.get(i).getText() + res.get(i).getText());
			System.out.println("Before filter data:" + formsBeforeFilter.get(i));
		}
		ControlActions_MobileApp.WaitforelementToBeClickable(FilterIcon);
		FilterIcon.click();
		ControlActions_MobileApp.waitForVisibility(assignedToFilter, 60);
		ControlActions_MobileApp.WaitforelementToBeClickable(assignedToDDAngle);
		assignedToDDAngle.click();

		while (!flag) {
			try {

				do {
					List<WebElement> ls = new CopyOnWriteArrayList<>(appiumDriver.findElementsById("filterOptionBtn"));

					System.out.println("Number of Elements in current grid are :" + ls.size());

					WebElement[] arr = new WebElement[ls.size()];
					arr = ls.toArray(arr);

					for (int j = 0; j < arr.length; j++) {

						if (!formsSorted.contains(arr[j].getText())) {
							formsSorted.add(j, arr[j].getText());
							System.out.println("Number of elements present in List are :" + formsSorted.size());
							if (ls.get(j).getText().equalsIgnoreCase(WGName + " - 4")) {

								flag = true;

								logInfo("Selecting assigned to filter");
								ControlActions_MobileApp.WaitforelementToBeClickable(ls.get(j));
								ControlActions_MobileApp.actionClick(ls.get(j));
								logInfo(ls.get(j).getAttribute("checked"));
								if (ls.get(j).getAttribute("checked").equalsIgnoreCase("true")) {
									selected = true;
									logInfo("Selected assigned to filter");
								}
								TestValidation.IsTrue(selected, "Selected Assigned to Workgroup succesfully",
										"Failed to select Assigned to filter");
								logInfo("Clicked on Workgroup");
							}
						}

					}
					ControlActions_MobileApp.swipe(400, 700, 400, 100);
				} while (!flag);

			}

			catch (Exception e) {
				System.out.println("Element is not yet visible");
			}
		}
		System.out.println("Status is displayed");

		try {

			do {

				ControlActions_MobileApp.waitForVisibility(statusDDAngle, 1);
				throw new NoSuchElementException();
			} while (!statusDDAngle.isDisplayed());

		} catch (NoSuchElementException e) {
			ControlActions_MobileApp.swipe(400, 700, 400, 100);
		}
		ControlActions_MobileApp.WaitforelementToBeClickable(statusDDAngle);
		statusDDAngle.click();
		Thread.sleep(5000);
		boolean statSelect = false;

		ArrayList<MobileElement> Statopt = new ArrayList<>(appiumDriver.findElementsById("filterOptionBtn"));
		System.out.println("Array size:" + Statopt.size());
		for (int j = 0; j < Statopt.size(); j++) {
			try {
				String[] sp = Statopt.get(j).getText().split("-", 2);
				@SuppressWarnings("unused")
				int tasks = Integer.parseInt(sp[1].trim());

				System.out.println(Statopt.get(j).getText());
				String opt = sp[0].trim(); // Statopt.get(j).getText();

				// Thread.sleep(5000);

				if (opt.equalsIgnoreCase("Due Today")) {

					System.out.println("Status to search for :" + opt);
					ControlActions_MobileApp.WaitforelementToBeClickable(Statopt.get(j));
					ControlActions_MobileApp.actionClick(Statopt.get(j));
					logInfo("Clicked on Status:" + opt);
					String checkbox = Statopt.get(j).getAttribute("checked");
					try {
						if (checkbox.equalsIgnoreCase("true")) {
							statSelect = true;
							logInfo("Selected Status filter");
						}
						TestValidation.IsTrue(statSelect, "Selected STATUS filter succesfully",
								"Failed to select STATUS filter filter");

					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
				}
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
			}
		}
		ControlActions_MobileApp.swipe(400, 700, 400, 100);
		ControlActions_MobileApp.WaitforelementToBeClickable(dueByDDAngle);
		dueByDDAngle.click();
		Thread.sleep(5000);

		String dueDate = ControlActions_MobileApp.AddDaystoToddaysDateInGivenFormat("MMMM d", 0);
		@SuppressWarnings("unused")
		String dueDate1 = ControlActions_MobileApp.AddDaystoToddaysDateInGivenFormat("MMMM d", 1);

		ControlActions_MobileApp.waitForVisibility(dueByFilter, 60);
		ControlActions_MobileApp.WaitforelementToBeClickable(dueByDDAngle);
		dueByDDAngle.click();
		dueByDDAngle.click();
		boolean selDueByFilter = false;

		try {

			WebElement formCheck = null;
			String currentMonth = taskPage.currentMonthTxt.getText();
			logInfo("Current Mnth Selected = " + currentMonth);

			List<String> currentMonthPart = Arrays.asList(currentMonth.split(" "));
			logInfo("Current Month = " + currentMonthPart.get(0) + "Year = " + currentMonthPart.get(1));
			List<String> dateParts = Arrays.asList(dueDate.split(" "));
			do {

				logInfo("Due date to be selected = " + dateParts.get(1) + "MOnth = " + dateParts.get(0));
				if (dateParts.get(0).equals(currentMonthPart.get(0))) {
					formCheck = ControlActions_MobileApp.perform_GetElementByXPath(TaskPageLocators.DAY_TOBE_BTN, "DAY",
							dateParts.get(1));
					ControlActions_MobileApp.waitForVisibility(formCheck, 50);
					ControlActions_MobileApp.click(formCheck);

				} else {
					ControlActions_MobileApp.waitForVisibility(taskPage.nxtMonthBtn, 60);
					ControlActions_MobileApp.click(taskPage.nxtMonthBtn);
				}
			} while (!(dateParts.get(0).equals(currentMonthPart.get(0))));
			selDueByFilter = true;
		} catch (Exception e) {
			logError("Failed to Select Due By Filter - " + e.getMessage());
			selDueByFilter = false;
		}

		TestValidation.IsTrue(selDueByFilter, "Selected Due By Filter", "Failed to select Due By Filter");

		ControlActions_MobileApp.waitForVisibility(taskPage.CalFilterBtn, 50);
		ControlActions_MobileApp.WaitforelementToBeClickable(taskPage.CalFilterBtn);
		ControlActions_MobileApp.click(taskPage.CalFilterBtn);
		Thread.sleep(20000);

		boolean ResultAsExpected = false;
		boolean ResultAsExpectedDueBy = false;
		Set<String> f = WorkGroupGridResultCapture(1);
		int n = f.size();
		List<String> aList = new ArrayList<String>(n);
		System.out.println("Number of elements present for applied filter" + f.size());
		aList.addAll(f);
		int counter = 0;
		System.out.println("Number of elements present in ArrayList  are:" + aList.size());
		for (int i = 0; i < aList.size(); i++) {
			counter = counter + 1;
			System.out.println(aList.get(i).substring(0, 21).trim());
			System.out.println(WGName);
			System.out.println(aList.get(i).substring(0, 21).trim().equalsIgnoreCase(WGName));
			ResultAsExpected = aList.get(i).substring(0, 21).trim().equalsIgnoreCase(WGName);
			TestValidation.IsTrue(ResultAsExpected, "Filter result for assigned to filter is as expected : PASS",
					"Filter result for assigned to filter is not as expected : FAIL");

			ResultAsExpectedDueBy = DueTodayFilter(aList.get(i), 25, 44);
			TestValidation.IsTrue(ResultAsExpectedDueBy, "Filter result for due today and due by is as expected : PASS",
					"Filter result for due today and due by is not as expected :FAIL");
			// need to check for date substring
		}

		ControlActions_MobileApp.WaitforelementToBeClickable(FilterIcon);
		FilterIcon.click();

		ControlActions_MobileApp.WaitforelementToBeClickable(taskPage.clearBtn);
		ControlActions_MobileApp.click(taskPage.clearBtn);
		System.out.println("Clicked on clear filter button");

		List<WebElement> ls2 = new CopyOnWriteArrayList<>(appiumDriver.findElementsById("resDueDate"));
		List<WebElement> res2 = new CopyOnWriteArrayList<>(appiumDriver.findElementsById("resName"));

		for (int j = 0; j < ls2.size(); j++) {
			formsAfterFilter.add(j, ls2.get(j).getText() + res2.get(j).getText());
			System.out.println("After filter data:" + formsAfterFilter.get(j));
		}

		boolean clearfilter = false;

		if (formsAfterFilter.equals(formsBeforeFilter)) {
			clearfilter = true;
		}

		TestValidation.IsTrue(clearfilter, "Clear filter results validated succesfully",
				"Failed to validate clear filter result");

	}

	public void BeforeAfterAssigningFilter() throws InterruptedException, ParseException {
		InboxHeaderFooterValidate();
		@SuppressWarnings("unused")
		ArrayList<String> beforeStat = checkDefaultGridResult("formName");

		ControlActions_MobileApp.WaitforelementToBeClickable(workGroupBtn);
		workGroupBtn.click();
		InboxWGHeaderFooter();
		@SuppressWarnings("unused")
		ArrayList<String> beforeWG = checkDefaultGridResult("formName");
		ControlActions_MobileApp.WaitforelementToBeClickable(dueByBtn);
		dueByBtn.click();
		InboxDueByPageHeaderFooter();
		@SuppressWarnings("unused")
		ArrayList<String> beforeDB = checkDefaultGridResult("formName");

		// Applying filter
		@SuppressWarnings("unused")
		String dueDate = ControlActions_MobileApp.AddDaystoToddaysDateInGivenFormat("MMMM d", -1);
		scrollingBetweenFilterOptionsWithSortValidation();
		ControlActions_MobileApp.actionScrollUp();
		// Checking if filter is still there
		// same result or selction

		// Clearing Filter

	}

	public ArrayList<String> checkDefaultGridResult(String searchById) {
		ArrayList<WebElement> Grid = new ArrayList<>(appiumDriver.findElementsById(searchById));
		ArrayList<String> GridComponents = new ArrayList<>();
		System.out.println("Forms present before filtering :");
		try {
			if (Grid.size() > 0) {
				for (int i = 0; i < Grid.size(); i++) {
					GridComponents.add(Grid.get(i).getText());
					System.out.println(Grid.get(i).getText());
				}
			} else {
				logError("No Elements present in ArrayList");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return GridComponents;
	}

	public void filterAndAssignedToSortValidation() {
		HomePage homePage = new HomePage(appiumDriver);
		mobControlActions = new ControlActions_MobileApp(appiumDriver);
		TaskPage InboxPageObj = new TaskPage(appiumDriver);
		SavedPage SavedPageObj = new SavedPage(appiumDriver);
		// Creating Task
		MultipleTaskCreation();
		ControlActions_MobileApp.swipeScreen("DOWN");

		boolean footerSelect = false;
		boolean clickSettingsMenu = homePage.ClickSubMenu(homePage.settingsSubMenu);
		TestValidation.IsTrue(clickSettingsMenu, "Clicked on settings subMenu Successfully",
				"Failed to click Settings subMenu");
		boolean selectdataLimit1 = InboxPageObj.selectDataLimit("21 DAYS");

		TestValidation.IsTrue(selectdataLimit1, "Selected 21 DAYS data limit from Settings",
				"Failed to select 21 DAYS data limit from Settings");

		ControlActions_MobileApp.click(SavedPageObj.settingsBckBtn);

		boolean clickInbox = false;
		try {
			ControlActions_MobileApp.waitForVisibility(homePage.inboxSubMenu, 160);
			ControlActions_MobileApp.click(homePage.inboxSubMenu);
			logInfo("Clicked on Submenu ");
			Thread.sleep(6000);
			clickInbox = true;
		} catch (Exception ex) {
			logInfo("Failed To click On SubMenu " + ex.getMessage());
			clickInbox = false;
		}

		int taskCount = Integer.parseInt(taskCountBadge.getText());
		if (taskCount > 0) {

			try {
				TestValidation.IsTrue(clickInbox, "Clicked on Inbox Sub Menu", "Failed to click Inbox Submenu");
				InboxHeaderFooterValidate();
				if (Arrays.asList("Past Due", "Due Later", "Due Today", "No Due Date")
						.contains(TaskHeaderTitle.getText())) {
					footerSelect = true;
				}

				TestValidation.IsTrue(footerSelect, "Status tab is selected by default in Inbox",
						" Status tab is not selected by Default in Inbox");
				ControlActions_MobileApp.WaitforelementToBeClickable(FilterIcon);
				FilterIcon.click();

				InboxFilterDisplayedContents();
				String dueDate = ControlActions_MobileApp.AddDaystoToddaysDateInGivenFormat("MMMM d", -1);

				System.out.println("Workgroup name :" + WGName);
				System.out.println("Due Date :" + dueDate);
				scrollingBetweenFilterOptionsWithSortValidation();

			}

			catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} else {
			logError("No Tasks present in Inbox");
		}

	}

	public void InboxHeaderFooterValidate() {
		mobControlActions = new ControlActions_MobileApp(appiumDriver);
		boolean footerValidate = false;
		boolean pageTitle = false;
		boolean header = false;

		try {

			if (InboxSearchBox.getText().equalsIgnoreCase("Search Inbox") && SearchIcon.isDisplayed()) {
				pageTitle = true;
			}

			if (workGroupBtn.isDisplayed() && workGroupTxt.getText().equalsIgnoreCase("Work Group")
					&& statusBtn.isDisplayed() && statusTxt.getText().equalsIgnoreCase("Status")
					&& dueByBtn.isDisplayed() && dueByTxt.getText().equalsIgnoreCase("Due By")) {
				footerValidate = true;
			}

			if (taskTab.isDisplayed() && taskTab.getText().equalsIgnoreCase("Tasks") && submissionBadge.isDisplayed()
					&& submissionBadge.getText().equalsIgnoreCase("Submissions") && FilterIcon.isDisplayed()) {
				header = true;
			}
		} catch (Exception ex) {
			logInfo("Failed To click On Inbox " + ex.getMessage());
		}

		TestValidation.IsTrue(pageTitle, "Inbox page title is present as Search Inbox",
				"Inbox PageTite not present as Search Inbox");
		TestValidation.IsTrue(footerValidate, "All footers Workgroup,Status and Due By are present",
				"All footers are not present");
		TestValidation.IsTrue(header, "All headers are present", "All headers are not present");

	}

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

		boolean clickSettingsMenu1 = homePage.ClickSubMenu(homePage.inboxSubMenu);
		TestValidation.IsTrue(clickSettingsMenu1, "Clicked on Inbox subMenu Successfully",
				"Failed to click Inbox subMenu");
		int MinTaskCount = Integer.parseInt(taskCount.getText());
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

			TaskCountWithLimit = Integer.parseInt(taskCount.getText());
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

	public void taskCreationWithoutResource() {

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
			@SuppressWarnings("unused")
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
			// tp.ResourceInstanceNm = resourceInstanceValue1;
			tp.TaskName = TaskName;
			tp.WorkGroupName = WGName;
			tp.LocationInstanceId = locInstId;
			// tp.ResourceInstanceId = ResInstId;
			tp.UserName = mobileAppUsername;

			taskCreationWrapper.create_Link_workGroup_Wrapper(tp);
			taskCreationWrapper.create_Task_Wrapper(tp);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void MultipleTaskCreation() {
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
			List<String> dueByLst = new ArrayList<String>();

			dueByLst.add(ControlActions_MobileApp.AddDaystoToddaysDateInGivenFormat("MM/dd/YYYY HH:mm", -2));
			dueByLst.add(ControlActions_MobileApp.AddDaystoToddaysDateInGivenFormat("MM/dd/YYYY 23:59", 0));
			dueByLst.add(ControlActions_MobileApp.AddDaystoToddaysDateInGivenFormat("MM/dd/YYYY HH:mm", 7));
			for (int i = 0; i < dueByLst.size(); i++) {
				tp.DueBy = dueByLst.get(i);
				taskCreationWrapper.create_Task_Wrapper(tp);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void UnlinkLocationFunctionalityTaskValidation() throws InterruptedException {
		HomePage homePage = new HomePage(appiumDriver);
		FormsManagerPage fm = new FormsManagerPage(appiumDriver);
		SavedPage SavedPageObj = new SavedPage(appiumDriver);
		taskCreation();
		ControlActions_MobileApp.swipeScreen("DOWN");
		Thread.sleep(20000);
		ControlActions_MobileApp.waitForVisibility(mainMenu, 30);
		ControlActions_MobileApp.click(mainMenu);
		logInfo("Clicked on Main menu button");
		int beforeCount = Integer.parseInt(subMenuInbxCnt.getText());
		logInfo("Tasks count before is : " + beforeCount);
		CloseMainMenu.click();

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

		Boolean txtNumeric = SavedPageObj.enterFieldValue(SavedPageObj.NumericTxt, "8");
		TestValidation.IsTrue(txtNumeric, "Numeric Field value entered successfully", "Failed to enter field value");

		formSave.click();

		// Checking in Saved Forms
		boolean clickSaved = homePage.ClickSubMenu(homePage.saveSubMenu);
		TestValidation.IsTrue(clickSaved, "Clicked on Saved Sub Menu", "Failed to click Saved Submenu");
		ControlActions_MobileApp.swipeScreen("DOWN");

		// Checking details of saved form
		boolean searchSavedTask = homePage.SearchForm(FormName);
		TestValidation.IsTrue(searchSavedTask, "Saved task is present in Saved Forms",
				"Saved Task is not present in Saved Forms");

		appiumDriver.hideKeyboard();
		AllFormsBottomClick.click();
		boolean searchForm1 = homePage.SearchForm(FormName);
		TestValidation.IsTrue(searchForm1, "Created task is present in All Forms",
				"Created Task is not present in All Forms");
		appiumDriver.hideKeyboard();
		AddToFavourite.click();
		FavouriteClick.click();
		Thread.sleep(5000);
		ControlActions_MobileApp.swipeScreen("DOWN");
		SearchForm(FormName, SearchFavouriteTextbox);

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
		com.project.safetychain.webapp.pageobjects.HomePage hp = new com.project.safetychain.webapp.pageobjects.HomePage(
				driver);

		fm = hp.clickFormsManagerMenu();
		boolean searchingForm = fm.searchForm(FormName);
		TestValidation.IsTrue(searchingForm, "OPENED the search form - " + FormName,
				"Failed to open searched form - " + FormName);

		boolean selectForm = fm.selectForm(FormName);
		TestValidation.IsTrue(selectForm, "Form has Selected- " + FormName, "Failed to select Form- " + FormName);

		boolean locationOpt = fm.clickLocationMenu();
		TestValidation.IsTrue(locationOpt, "Clicked on Locations option", "Failed to select Locations option");

		boolean toggleLoc = fm.clickToggleLocation();
		TestValidation.IsTrue(toggleLoc, "Clicked on Location toggle on", "Failed to click on Location toggle on");

		boolean unlink = fm.ClickLocationCheck();
		TestValidation.IsTrue(unlink, "Unlinked locations succesfully", "Failed to unlink locations");

		boolean saveChanges = fm.clickSaveBtn();
		TestValidation.IsTrue(saveChanges, "Clicked on Save Button", "Failed to click on Save Button");

		ControlActions_MobileApp.swipeScreen("DOWN");
		Thread.sleep(10000);

		ControlActions_MobileApp.waitForVisibility(noResultsFound, 20);
		boolean Fav = (noResultsFound.isDisplayed()
				&& (noResultsFound.getText().trim().equalsIgnoreCase("No Results Found")));
		TestValidation.IsTrue(Fav, "Task not present in Favorites after unlinking locations",
				"Task still present in Favorites after unlinking locations");

		appiumDriver.hideKeyboard();
		AllFormsBottomClick.click();
		ControlActions_MobileApp.waitForVisibility(noResultsFound, 20);
		boolean Allforms = (noResultsFound.isDisplayed()
				&& (noResultsFound.getText().trim().equalsIgnoreCase("No Results Found")));
		TestValidation.IsTrue(Allforms, "Task not present in All forms after unlinking locations",
				"Task still present in all forms after unlinking locations");

		SavedBottomClick.click();
		ControlActions_MobileApp.waitForVisibility(noResultsFound, 20);
		boolean Saved = (noResultsFound.isDisplayed()
				&& (noResultsFound.getText().trim().equalsIgnoreCase("No Results Found")));
		TestValidation.IsTrue(Saved, "Task not present in Saved forms after unlinking locations",
				"Task still present in Saved forms after unlinking locations");

		boolean clickInbox = homePage.ClickSubMenu(homePage.inboxSubMenu);

		TestValidation.IsTrue(clickInbox, "Clicked on Inbox Sub Menu", "Failed to click Inbox Submenu");
		int afterCount = Integer.parseInt(taskCount.getText());
		logInfo("Tasks count after unlinking is : " + afterCount);

		ControlActions_MobileApp.waitForVisibility(noResultsFound, 20);
		boolean Inbox = (noResultsFound.isDisplayed()
				&& (noResultsFound.getText().trim().equalsIgnoreCase("No Results Found")));
		TestValidation.IsTrue(Inbox, "Task not present in Inbox after unlinking locations",
				"Task still present in Inbox after unlinking locations");
		boolean taskCountF = false;
		if (beforeCount > afterCount) {
			logInfo("task count is decreased by :" + (beforeCount - afterCount));
			taskCountF = true;
		}

		TestValidation.IsTrue(taskCountF, "Task count decremented succesfully after unlinking locations",
				"Failed to decrement Task count after unlinking locations");
		// driver.quit();
	}

	public void ClosefilterCrossIcon() {
		HomePage homePage = new HomePage(appiumDriver);
		boolean BackToStatus = false;
		boolean clickInbox = homePage.ClickSubMenu(homePage.inboxSubMenu);
		TestValidation.IsTrue(clickInbox, "Clicked on Inbox Sub Menu", "Failed to click Inbox Submenu");
		InboxHeaderFooterValidate();
		ArrayList<String> beforeStat = CheckGrid("Search Inbox", "Status");
		ControlActions_MobileApp.WaitforelementToBeClickable(FilterIcon);
		FilterIcon.click();
		InboxFilterDisplayedContents();

		boolean cross = InboxFilterCrossBtn.isDisplayed();
		TestValidation.IsTrue(cross,
				"Cross Icon is present to close filter after selecting filter icon from Status Tab",
				"Cross Icon not present to close filter after selecting filter icon from Status Tab");
		ControlActions_MobileApp.WaitforelementToBeClickable(InboxFilterCrossBtn);
		InboxFilterCrossBtn.click();
		ArrayList<String> afterStat = CheckGrid("Search Inbox", "Status");
		try {

			if (Arrays.asList("Past Due", "Due Later", "Due Today", "No Due Date").contains(TaskHeaderTitle.getText())
					&& beforeStat.equals(afterStat)) {
				BackToStatus = true;
			}

		}

		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		TestValidation.IsTrue(BackToStatus,
				"Navigated back succesfully to Status tab after closing filter with cross ICon",
				" Failed to Navigate back to Status tab after closing filter");

		// Clicking on Due By

		ControlActions_MobileApp.WaitforelementToBeClickable(dueByBtn);
		dueByBtn.click();
		boolean dueBy = InboxDueByPageHeaderFooter();
		TestValidation.IsTrue(dueBy, " Clicked on Due By Succesfully", " Failed to Click on Due by");
		ArrayList<String> beforeDB = CheckGrid("Search Inbox", "Due By");

		ControlActions_MobileApp.WaitforelementToBeClickable(FilterIcon);
		FilterIcon.click();
		boolean crossDueBy = InboxFilterCrossBtn.isDisplayed();
		TestValidation.IsTrue(crossDueBy,
				"Cross Icon is present to close filter after selecting filter icon from DueBy Tab",
				"Cross Icon not present to close filter after selecting filter icon from DueBy Tab");
		InboxFilterDisplayedContents();
		ControlActions_MobileApp.WaitforelementToBeClickable(InboxFilterCrossBtn);
		InboxFilterCrossBtn.click();
		ArrayList<String> afterDB = CheckGrid("Search Inbox", "Due By");
		boolean DueBy = false;
		try {

			if ((Arrays.asList("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec")
					.contains(TaskHeaderTitle.getText().substring(0, 3)) && taskTab.isDisplayed()
					&& taskTab.getText().equalsIgnoreCase("Tasks") && submissionBadge.isDisplayed()
					&& submissionBadge.getText().equalsIgnoreCase("Submissions") && FilterIcon.isDisplayed())
					&& beforeDB.equals(afterDB)) {
				DueBy = true;
			}

		}

		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		TestValidation.IsTrue(DueBy, "Navigated back succesfully to Due By tab after closing filter with cross ICon",
				" Failed to Navigate back to Due By tab after closing filter");

		// Clicking on Workgroup

		ControlActions_MobileApp.WaitforelementToBeClickable(workGroupBtn);
		workGroupBtn.click();
		boolean wg = InboxWGHeaderFooter();
		TestValidation.IsTrue(wg, "Clicked on Workgroup tab Succesfully", " Failed to Click on Workgroup tab");
		ArrayList<String> beforeWG = CheckGrid("Search Inbox", "Worgroup");
		ControlActions_MobileApp.WaitforelementToBeClickable(FilterIcon);
		FilterIcon.click();
		InboxFilterDisplayedContents();
		boolean crossWG = InboxFilterCrossBtn.isDisplayed();
		TestValidation.IsTrue(crossWG, "Cross Icon is present to close filter after selecting filter icon from WG Tab",
				"Cross Icon not present to close filter after selecting filter icon from WG Tab");
		ControlActions_MobileApp.WaitforelementToBeClickable(InboxFilterCrossBtn);
		InboxFilterCrossBtn.click();
		ArrayList<String> afterWG = CheckGrid("Search Inbox", "Worgroup");
		boolean WGselect = false;
		try {
			System.out.println("Taskheader in WG : " + TaskHeaderTitle.getText().substring(0, 6));
			if ((TaskHeaderTitle.getText().substring(0, 6).equalsIgnoreCase("API_WG") && taskTab.isDisplayed()
					&& taskTab.getText().equalsIgnoreCase("Tasks") && submissionBadge.isDisplayed()
					&& submissionBadge.getText().equalsIgnoreCase("Submissions") && FilterIcon.isDisplayed()
					&& beforeWG.equals(afterWG)) == true) {
				WGselect = true;
			}

		}

		catch (Exception ex) {
			System.out.println("Failed to navigate back to Workgroup from filter panel");
		}

		TestValidation.IsTrue(WGselect,
				"Navigated back succesfully to Workgroup tab after closing filter with cross ICon",
				" Failed to Navigate back to Workgroup tab after closing filter");

	}

	public void InboxHeaderFooterTaskBadgeValidation() throws InterruptedException, ParseException {

		HomePage homePage = new HomePage(appiumDriver);
		UploadImage oUploadImage = new UploadImage(appiumDriver);
		TaskPage InboxPageObj = new TaskPage(appiumDriver);
		SavedPage SavedPageObj = new SavedPage(appiumDriver);
		boolean taskBadgeCount = false;

		boolean clickSettingsMenu = homePage.ClickSubMenu(homePage.settingsSubMenu);
		TestValidation.IsTrue(clickSettingsMenu, "Clicked on settings subMenu Successfully",
				"Failed to click Settings subMenu");
		boolean selectdataLimit = InboxPageObj.selectDataLimit("21 DAYS");
		TestValidation.IsTrue(selectdataLimit, "Selected 21 DAYS data limit from Settings",
				"Failed to select 21 DAYS data limit from Settings");
		ControlActions_MobileApp.click(SavedPageObj.settingsBckBtn);
		TestValidation.IsTrue(true, "Clicked on Settings Back Button Successfully",
				"Failed to click Settings Back Button");
		boolean clickInboxMenu = homePage.ClickMenu(homePage.formsSubMenu);
		TestValidation.IsTrue(clickInboxMenu, "Clicked on All forms subMenu Successfully",
				"Failed to click All forms subMenu");
		ControlActions_MobileApp.waitForVisibility(taskCount, 20);
		int taskCountBefore = Integer.parseInt(taskCount.getText());
		logInfo("TaskCount Before Task Creation : " + taskCountBefore);

		// Creating Task
		taskCreation();
		ControlActions_MobileApp.waitForVisibility(SearchFormsTextbox, 30);
		// Thread.sleep(20000);
		ControlActions_MobileApp.swipeScreen("DOWN");
		Thread.sleep(20000);
		ControlActions_MobileApp.swipeScreen("DOWN");
		Thread.sleep(30000);
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

		boolean clickInbox = false;
		boolean footerValidate = false;
		boolean pageTitle = false;

		try {
			ControlActions_MobileApp.waitForVisibility(mainMenu, 180);
			ControlActions_MobileApp.click(mainMenu);
			ControlActions_MobileApp.waitForVisibility(homePage.inboxSubMenu, 160);
			ControlActions_MobileApp.click(homePage.inboxSubMenu);
			logInfo("Clicked on Submenu ");
			ControlActions_MobileApp.waitForVisibility(InboxSearchBox, 20);
			System.out.println(InboxSearchBox.getText().trim());
			System.out.println(InboxSearchBox.getText().trim());
			if (InboxSearchBox.getText().trim().equalsIgnoreCase("Search Inbox")) {
				pageTitle = true;
			}
			clickInbox = true;
			if (workGroupBtn.isDisplayed() && workGroupTxt.getText().equalsIgnoreCase("Work Group")
					&& statusBtn.isDisplayed() && statusTxt.getText().equalsIgnoreCase("Status")
					&& dueByBtn.isDisplayed() && dueByTxt.getText().equalsIgnoreCase("Due By")) {
				footerValidate = true;
			}

		} catch (Exception ex) {
			logInfo("Failed To click On SubMenu " + ex.getMessage());
		}

		TestValidation.IsTrue(clickInbox, "Clicked on Inbox Sub Menu", "Failed to click Inbox Submenu");
		TestValidation.IsTrue(pageTitle, "Inbox page title is present as Search Inbox",
				"Inbox PageTite not present as Search Inbox");
		TestValidation.IsTrue(footerValidate, "All footers Workgroup,Status and Due By are present",
				"All footers are not present");
		ControlActions_MobileApp.waitForVisibility(searchBox, 60);
		ControlActions_MobileApp.WaitforelementToBeClickable(searchBox);
		boolean searchForm = homePage.SearchTask(TaskName);
		Thread.sleep(10000);
		TestValidation.IsTrue(searchForm, "Latest Created Task is present in Inbox",
				"Latest Created Task is not present in Inbox");
		WebElement formCheck = ControlActions_MobileApp.perform_GetElementByXPath(HomePageLocators.Form_Name,
				"FormNameLocator", TaskName);
		ControlActions_MobileApp.waitForVisibility(formCheck, 60);
		ControlActions_MobileApp.click(formCheck);
		logInfo("Clicked on Task = " + TaskName);
		Thread.sleep(10000);

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

		oUploadImage.CameraClick();
		Thread.sleep(2000);
		oUploadImage.OpenGallery();
		oUploadImage.SelectImage();
		oUploadImage.CloseGallery();

		boolean submitForm = InboxPageObj.submitTask();

		Thread.sleep(4000);

		TestValidation.IsTrue(submitForm, "Task submitted Successfully", "Failed to submit Task from Inbox");
		homePage.ClickSubMenu(homePage.submissionsSubMenu);
		homePage.SearchForm(FormName);
		SavedPageObj.checkFormStatusFromSubmissionsSubMenu(FormName, "RECEIVED");
		SavedPageObj.clickFormFromSubmissionsScreen(FormName);
		LinkedHashMap<String, String> FieldTypeValues = new LinkedHashMap<String, String>();
		FieldTypeValues.put(Numeric, "8");
		FieldTypeValues.put(ParaTxt, "Para");
		FieldTypeValues.put(chkFreeTxt, "FreeText");
		// FieldTypeValues.put(chkSelectOne, "GOOD");
		FieldTypeValues.put(chkDate, dateSelected);
		FieldTypeValues.put(chkDateTime, dateTimeSel);
		FieldTypeValues.put(SelectMultiple, "10");
		FieldTypeValues.put(SelectMultiple, "30");

		boolean verifyUpdatedValue = SavedPageObj.verifyFieldValues(FieldTypeValues);
		boolean verifyImage = oUploadImage.verifySelectedImageThroughGallery();
		TestValidation.IsTrue(verifyUpdatedValue, "VERIFIED updated value for  all fields",
				"Failed to verify updated values for all fields");
		TestValidation.IsTrue(verifyImage, "VERIFIED Image", "Failed to verify Image");
		backForm.click();
		boolean clickInbox1 = homePage.ClickSubMenu(homePage.inboxSubMenu);

		TestValidation.IsTrue(clickInbox1, "Clicked on Inbox Sub Menu", "Failed to click Inbox Submenu");
		// closeSearchBtn.click();
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
		REG_AllFormsSaved afSaved = new REG_AllFormsSaved(appiumDriver);
		closeSearchBtn.click();
		afSaved.NoResultsCheck(TaskName, InboxSearchBox, "Inbox");

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

		FSQABrowserPage fbp = new FSQABrowserPage(driver);
		FSQABrowserRecordsPage rp = new FSQABrowserRecordsPage(driver);

		boolean navigate = fbp.navigateToFSQATab("Records");
		TestValidation.IsTrue(navigate, "Navigated to FSQABrowser>RecordsTab",
				"Failed to navigate to FSQABrowser>Records Tab");

		boolean applyfilter = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, FormName);

		TestValidation.IsTrue(applyfilter, "Applied Filter on" + COLUMNHEADER.FORMNAME,
				"Failed to apply filter on" + COLUMNHEADER.FORMNAME);

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

		TestValidation.IsTrue(verifyUpdatedValueWeb,
				"Submitted form is present on web.VERIFIED updated values for all fields",
				"Failed to verify updated values for fields");
		WebElement image = driver.findElement(By.xpath("//a[@ng-bind='attachment.name']"));
		String FileActual = image.getText();
		System.out.println("ActualFile Present:" + FileActual);

		boolean AttachmentMob = (FileActual.equals("0.png") || FileActual.equals("1.png"));
		TestValidation.IsTrue(AttachmentMob, "Attachment from mobile is present at bottom on Web",
				"Attachment  from mobile is not present at bottom on Web");

	}

	public void TaskSearchAndDataRetainValidation() throws InterruptedException {
		HomePage homePage = new HomePage(appiumDriver);
		mobControlActions = new ControlActions_MobileApp(appiumDriver);
		boolean DBHF = false;
		boolean WGHF = false;
		// Creating Task
		taskCreation();
		// String TaskName = "API_Task_29.04_17.05.03";
		ControlActions_MobileApp.swipeScreen("DOWN");

		boolean clickInbox = false;

		try {
			ControlActions_MobileApp.waitForVisibility(mainMenu, 180);
			ControlActions_MobileApp.click(mainMenu);
			ControlActions_MobileApp.waitForVisibility(homePage.inboxSubMenu, 160);
			ControlActions_MobileApp.click(homePage.inboxSubMenu);
			logInfo("Clicked on Submenu ");
			ControlActions_MobileApp.waitForVisibility(InboxSearchBox, 20);

			if (InboxSearchBox.isDisplayed()) {

				clickInbox = true;
				InboxHeaderFooterValidate();
			}
		} catch (

		Exception ex) {
			logInfo("Failed To click On Inbox " + ex.getMessage());
		}

		TestValidation.IsTrue(clickInbox, "Clicked on Inbox Sub Menu", "Failed to click Inbox Submenu");

		String tsk = new String(TaskName);
		String TaskWithDate = tsk.substring(0, 14);
		String TaskDate = tsk.substring(9, 14);
		String TaskWithTime = tsk.substring(15);
		boolean footerSelect = false;
		// Search in Status
		System.out.println("TaskHeaders are:" + TaskHeaderTitle.getText());
		if (Arrays.asList("Past Due", "Due Later", "Due Today", "No Due Date").contains(TaskHeaderTitle.getText())) {
			footerSelect = true;
		}

		TestValidation.IsTrue(footerSelect, "Status tab is selected by default in Inbox",
				" Status tab is not selected by Default in Inbox");

		boolean searchFormNeg = enterTaskNegativeScenario("ABCD12345");
		TestValidation.IsTrue(searchFormNeg, "Negative Scenarion validated succesfully in Status Tab of Inbox",
				" Failed to validate Negative Scenario  in Status Tab of Inbox");
		closeSearchBtn.click();
		boolean searchForm = enterTask(TaskWithDate);
		TestValidation.IsTrue(searchForm, "Task entered succesfully in Status Tab of Inbox",
				" Failed to enter task in Status Tab of Inbox");

		logInfo("Elements present in Status before Navigation :");
		logInfo("Text entered in status tab :" + TaskWithDate);
		ArrayList<String> BeforeNavStat = SearchInboxData(TaskWithDate, "Status Tab");

		// Search in Workgroup
		boolean WGselect = false;

		ControlActions_MobileApp.WaitforelementToBeClickable(workGroupBtn);
		workGroupBtn.click();
		WGHF = InboxWGHeaderFooter();
		System.out.println("Taskheader in WG : " + TaskHeaderTitle.getText().substring(0, 6));
		if (WGHF == true) {
			WGselect = true;
		}

		TestValidation.IsTrue(WGselect, "Succefully clicked on Workgroup tab  in Inbox",
				" Failed to click Worgroup tab in Inbox");

		boolean searchFormNegWG = enterTaskNegativeScenario("ABCD12345");
		TestValidation.IsTrue(searchFormNegWG, "Negative Scenarion validated succesfully in Workgroup Tab of Inbox",
				" Failed to validate Negative Scenario in Workgroup Tab of Inbox");
		closeSearchBtn.click();

		boolean searchInWorkgroup = enterTask(TaskDate);
		TestValidation.IsTrue(searchInWorkgroup, "Task entered succesfully in WG Tab of Inbox",
				" Failed to enter task in WG Tab of Inbox");
		logInfo("Elements present in WG before Navigation :");
		logInfo("Text entered in status tab :" + TaskDate);
		ArrayList<String> BeforeNavWG = SearchInboxData(TaskDate, "Workgroup Tab");
		// Search in Due By
		ControlActions_MobileApp.WaitforelementToBeClickable(dueByBtn);
		dueByBtn.click();
		boolean DueBySelect = false;
		DBHF = InboxDueByPageHeaderFooter();
		if (DBHF == true) {
			DueBySelect = true;
		}

		TestValidation.IsTrue(DueBySelect, "Succesfully clicked on DueBy Tab in Inbox",
				" Failed to click on Due By tab in Inbox");

		boolean searchFormNegDueBy = enterTaskNegativeScenario("ABCD12345");
		TestValidation.IsTrue(searchFormNegDueBy, "Negative Scenarion validated succesfully in Due By Tab of Inbox",
				" Failed to validate Negative Scenario in Due By Tab of Inbox");
		closeSearchBtn.click();

		boolean searchInDueBy = enterTask(TaskWithTime);
		TestValidation.IsTrue(searchInDueBy, "Task entered succesfully in Due By Tab of Inbox",
				" Failed to enter task in Due By Tab of Inbox");
		logInfo("Elements present in Due By before Navigation :");
		logInfo("Text entered in status tab :" + TaskWithTime);
		ArrayList<String> BeforeNavDueBy = SearchInboxData(TaskWithTime, "Due By Tab");

		// Navigating between footers

		// From DueBy
		boolean searchResults = false;
		ArrayList<String> AfterNav = null;

		if (workGroupBtn.isDisplayed() && workGroupBtn.isEnabled()) {
			workGroupBtn.click();
			logInfo("Elements present in WG after Navigation :");
			AfterNav = SearchInboxData(TaskDate, "WorkGroup Tab");

			if (AfterNav.equals(BeforeNavWG)) {
				searchResults = true;
			}
			TestValidation.IsTrue(searchResults,
					"Succesfully navigated from Due by to WorkGroup and search data is retained as it is",
					"Failed to navigate from Due BY to WorkGroup : Data not retained");

		}
		if (statusBtn.isDisplayed() && statusBtn.isEnabled()) {
			statusBtn.click();
			AfterNav.clear();
			logInfo("Elements present in Status after Navigation :");
			AfterNav = SearchInboxData(TaskWithDate, "Status Tab");
			if (AfterNav.equals(BeforeNavStat)) {
				searchResults = true;

			}
			TestValidation.IsTrue(searchResults,
					"Succesfully navigated from  WorkGroup to Status and search data is retained as it is",
					"Failed to navigate from WorkGroup to Status : Data not retained");
		}

		if (dueByBtn.isDisplayed() && dueByBtn.isEnabled()) {
			dueByBtn.click();
			AfterNav.clear();
			logInfo("Elements present in DueBy after Navigation :");
			AfterNav = SearchInboxData(TaskWithTime, "Due By Tab");
			if (AfterNav.equals(BeforeNavDueBy)) {
				searchResults = true;

			}
			TestValidation.IsTrue(searchResults,
					"Succesfully navigated from Status to Due By and search data is retained as it is",
					"Failed to navigate from Status to Due By : Data not retained");
		}

		if (submissionBadge.isDisplayed() && submissionBadge.isEnabled()) {
			submissionBadge.click();
			taskTab.click();
			AfterNav.clear();
			ControlActions_MobileApp.waitForVisibility(TaskHeaderTitle, 60);
			AfterNav = SearchInboxData(TaskWithDate, "Status Tab");
			logInfo("Text Expected in status search box: " + TaskWithDate);
			String Expected = TaskWithDate.trim();
			String Actual = searchBox.getText().trim();
			System.out.println("Expected :" + TaskWithDate.trim());
			System.out.println("Text present in searchbox currently:" + searchBox.getText().trim());

			Thread.sleep(10000);
			// System.out.println("Actual Text :"+);
			if (Expected.equalsIgnoreCase(Actual) && Arrays.asList("Past Due", "Due Later", "Due Today", "No Due Date")
					.contains(TaskHeaderTitle.getText()) && AfterNav.equals(BeforeNavStat)) {

				footerSelect = true;
			} else {
				footerSelect = false;
			}

			TestValidation.IsTrue(footerSelect,
					"Succesfully navigated from submission to task badge and data is retained",
					" Failed to navigate from submission to task badge");

			// Search in Workgroup
			boolean WGselect1 = false;
			ControlActions_MobileApp.WaitforelementToBeClickable(workGroupBtn);
			workGroupBtn.click();
			AfterNav.clear();
			String ExpectedWGTxt = TaskDate;
			String ActualWGTxt = searchBox.getText();
			System.out.println("Taskheader in WG : " + TaskHeaderTitle.getText().substring(0, 6));
			if (ExpectedWGTxt.equalsIgnoreCase(ActualWGTxt)
					&& TaskHeaderTitle.getText().substring(0, 6).equalsIgnoreCase("API_WG") && taskTab.isDisplayed()
					&& taskTab.getText().equalsIgnoreCase("Tasks") && submissionBadge.isDisplayed()
					&& submissionBadge.getText().equalsIgnoreCase("Submissions") && FilterIcon.isDisplayed()) {
				WGselect1 = true;
			}

			TestValidation.IsTrue(WGselect1,
					"Search is present as expected after navigation from submission tab in WG Tab of Inbox",
					"Present search text is not as expected  in WG Tab of Inbox");
			AfterNav = SearchInboxData(TaskDate, "WorkGroup Tab");

			if (AfterNav.equals(BeforeNavWG)) {
				searchResults = true;
			}
			TestValidation.IsTrue(searchResults,
					"Succesfully navigated from Status to WorkGroup after navigating from submissions and search data is retained as it is",
					"Failed to navigate from Status to WorkGroup after navigating from submissions: Data not retained");
		}

		if (dueByBtn.isDisplayed() && dueByBtn.isEnabled()) {
			dueByBtn.click();
			AfterNav.clear();
			logInfo("Elements present in DueBy after Navigation from submission:");
			String ExpectedDBTxt = TaskWithTime;
			String ActualDBTxt = searchBox.getText();
			AfterNav = SearchInboxData(TaskWithTime, "Due By Tab");
			DBHF = InboxDueByPageHeaderFooter();
			if ((ExpectedDBTxt.equalsIgnoreCase(ActualDBTxt) && AfterNav.equals(BeforeNavDueBy) && DBHF) == true) {
				searchResults = true;

			}
			TestValidation.IsTrue(searchResults,
					"Succesfully navigated from Status to Due By and search data is retained as it is",
					"Failed to navigate from Status to Due By : Data not retained");
		}

	}

	public boolean InboxWGHeaderFooter() {
		boolean WGselect = false;

		System.out.println("Taskheader in WG : " + TaskHeaderTitle.getText().substring(0, 6));
		if (((TaskHeaderTitle.getText().substring(0, 6).equalsIgnoreCase("API_WG")
				|| TaskHeaderTitle.getText().contains("WG")) && taskTab.isDisplayed()
				&& taskTab.getText().equalsIgnoreCase("Tasks") && submissionBadge.isDisplayed()
				&& submissionBadge.getText().equalsIgnoreCase("Submissions") && FilterIcon.isDisplayed()) == true) {
			WGselect = true;
		}

		TestValidation.IsTrue(WGselect,
				"Succefully clicked on Workgroup tab  in Inbox,All headers are present as expected",
				" Failed to click Worgroup tab in Inbox");
		return WGselect;
	}

	public boolean InboxDueByPageHeaderFooter() {
		mobControlActions = new ControlActions_MobileApp(appiumDriver);
		boolean DueBySelect = false;
		if ((Arrays.asList("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec")
				.contains(TaskHeaderTitle.getText().substring(0, 3)) && taskTab.isDisplayed()
				&& taskTab.getText().equalsIgnoreCase("Tasks") && submissionBadge.isDisplayed()
				&& submissionBadge.getText().equalsIgnoreCase("Submissions") && FilterIcon.isDisplayed()) == true) {
			DueBySelect = true;
		}

		TestValidation.IsTrue(DueBySelect,
				"Succesfully clicked on DueBy Tab in Inbox,All headers are present as expected",
				" Failed to click on Due By tab in Inbox");
		return DueBySelect;
	}

	public boolean enterTask(String taskNmae) {
		HomePage homePage = new HomePage(appiumDriver);
		try {
			ControlActions_MobileApp.waitForVisibility(homePage.searchField, 60);
			ControlActions_MobileApp.ClearText(homePage.searchField);
			homePage.searchField.sendKeys(taskNmae);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean enterTaskNegativeScenario(String taskNmae) {
		HomePage homePage = new HomePage(appiumDriver);
		try {
			ControlActions_MobileApp.waitForVisibility(homePage.searchField, 60);
			ControlActions_MobileApp.ClearText(homePage.searchField);
			homePage.searchField.sendKeys(taskNmae);
			if (noResultsFound.isDisplayed() && (noResultsFound.getText().trim().equalsIgnoreCase("No Results Found"))
					&& refreshInboxBtn.isDisplayed() && refreshInboxBtn.isEnabled()
					&& refreshInboxBtn.getText().equalsIgnoreCase("REFRESH INBOX")) {

			}
			return true;

		} catch (Exception e) {
			return false;
		}
	}

	public ArrayList<String> SearchInboxData(String SearchText, String TabName) {
		boolean listPresent = false;
		ArrayList<String> forms = new ArrayList<>();
		if (searchBox.getText().equalsIgnoreCase(SearchText) && closeSearchBtn.isDisplayed()) {

			ArrayList<WebElement> ls = new ArrayList<>(appiumDriver.findElementsById("taskName"));

			// String Con = "API_WG_".concat(NameOfTask.substring(8));
			System.out.println("Number of elements in grid :  " + ls.size());
			if (ls.size() > 0) {
				listPresent = true;
				for (int i = 0; i < ls.size(); i++) {
					forms.add(ls.get(i).getText().concat("API_WG_".concat(ls.get(i).getText().substring(8))));
					System.out.println(forms.get(i));
				}
			}
			TestValidation.IsTrue(listPresent, "Task List is present in " + TabName + " of Inbox",
					" Task List is not present in " + TabName + " of Inbox");

		}
		return forms;
	}

	public ArrayList<String> CheckGrid(String SearchText, String TabName) {
		boolean listPresent = false;
		ArrayList<String> forms = new ArrayList<>();
		if (searchBox.getText().equalsIgnoreCase(SearchText)) {

			ArrayList<WebElement> ls = new ArrayList<>(appiumDriver.findElementsById("taskName"));

			// String Con = "API_WG_".concat(NameOfTask.substring(8));
			System.out.println("Number of elements in grid :  " + ls.size());
			if (ls.size() > 0) {
				listPresent = true;
				for (int i = 0; i < ls.size(); i++) {
					forms.add(ls.get(i).getText().concat("API_WG_".concat(ls.get(i).getText().substring(8))));
					System.out.println(forms.get(i));
				}
			}
			TestValidation.IsTrue(listPresent, "Task List is present in " + TabName + " of Inbox",
					" Task List is not present in " + TabName + " of Inbox");

		}
		return forms;
	}

	public void TaskWithoutResource() throws InterruptedException {
		HomePage homePage = new HomePage(appiumDriver);
		// Creating Task
		taskCreationWithoutResource();
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
		Thread.sleep(10000);

		boolean resPopup = false;

		ControlActions_MobileApp.waitForVisibility(afterClickHeaderName, 20);
		ControlActions_MobileApp.waitForVisibility(resourceSearchText, 20);
		ControlActions_MobileApp.waitForVisibility(availableResources, 20);
		ControlActions_MobileApp.waitForVisibility(resources, 20);

		System.out.println("Resource 1 :" + "Customers > " + resourceCategoryValue + " > " + resourceInstanceValue1);
		System.out.println("Resource 2 :" + "Customers > " + resourceCategoryValue + " > " + resourceInstanceValue2);
		System.out.println("res sel:" + afterClickHeaderName.getText().trim());
		System.out.println("res search:" + resourceSearchText.getText().trim());
		System.out.println("avlbl res:" + availableResources.getText().trim());

		ArrayList<MobileElement> resLs = new ArrayList<>(appiumDriver.findElements(By.id("resourceName")));
		System.out.println("Total Resources:" + resLs.size());
		if (afterClickHeaderName.getText().trim().equalsIgnoreCase("Select Resource")
				&& resourceSearchText.getText().trim().equalsIgnoreCase("Resource:")
				&& availableResources.getText().trim().equalsIgnoreCase("AVAILABLE RESOURCES") && (resLs.size() == 2))
			System.out.println("Ppage contents are as expected");
		{
			if (resLs.get(0).getText().trim()
					.equalsIgnoreCase("Customers > " + resourceCategoryValue + " > " + resourceInstanceValue1)

					&& resLs.get(1).getText().trim()
							.equalsIgnoreCase("Customers > " + resourceCategoryValue + " > " + resourceInstanceValue2))

			{
				resPopup = true;
			}
		}

		TestValidation.IsTrue(resPopup, "Resource popup is appearing for task created without resource",
				"No popup appearing for Resource Selection for task without resource ");
		boolean searchResource = homePage.SearchResource(resourceInstanceValue1);
		TestValidation.IsTrue(searchResource, "Resource Searched Successfully",
				"Failed to Search Resource from Forms subMenu");

		boolean resLoc = false;
		ControlActions_MobileApp.waitForVisibility(resourceHeader, 60);
		System.out.println("Res:" + resourceHeader.getText().trim());
		System.out.println("ResVal:" + resourceValue.getText().trim());
		System.out.println("locHeader:" + locationHeader.getText().trim());
		System.out.println("Res:" + locationValue.getText().trim());
		if (resourceHeader.getText().trim().equalsIgnoreCase("Resource:")
				&& resourceValue.getText().trim().equals(resourceInstanceValue1)
				&& locationHeader.getText().trim().equalsIgnoreCase("Location:")
				&& locationValue.getText().trim().equals(locationInstanceValue1)) {
			logInfo("Task is assigned with resource and location");
			resLoc = true;

		}
		TestValidation.IsTrue(resLoc, "Task already assigned with location & resource",
				"Task is not already assigned with location & resource");

	}

	public void TaskSaveFunctionality() throws InterruptedException, ParseException {
		HomePage homePage = new HomePage(appiumDriver);
		UploadImage oUploadImage = new UploadImage(appiumDriver);
		SavedPage SavedPageObj = new SavedPage(appiumDriver);
		boolean taskBadgeCount = false;

		ControlActions_MobileApp.waitForVisibility(taskCount, 20);
		int taskCountBefore = Integer.parseInt(taskCount.getText());
		logInfo("TaskCount Before Task Creation : " + taskCountBefore);
		int SavedCountBefore = Integer.parseInt(SavedBadgeCount.getText().trim());
		logInfo("Saved Count Before Saving Task : " + SavedCountBefore);

		// Creating Task
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
		Thread.sleep(10000);
		boolean resLoc = false;
		ControlActions_MobileApp.waitForVisibility(resourceHeader, 60);
		if (resourceHeader.getText().equalsIgnoreCase("Resource:")
				&& resourceValue.getText().equals(resourceInstanceValue1)
				&& locationHeader.getText().equalsIgnoreCase("Location:")
				&& locationValue.getText().equals(locationInstanceValue1)) {
			logInfo("Task is assigned with resource and location");
			resLoc = true;

		}
		TestValidation.IsTrue(resLoc, "Task already assigned with location & resource",
				"Task is not already assigned with location & resource");
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

		oUploadImage.CameraClick();
		Thread.sleep(2000);
		oUploadImage.OpenGallery();
		oUploadImage.SelectImage();
		oUploadImage.CloseGallery();
		ControlActions_MobileApp.WaitforelementToBeClickable(formSave);
		formSave.click();

		// Checking in Saved Forms
		homePage.ClickSubMenu(homePage.saveSubMenu);
		ControlActions_MobileApp.swipeScreen("DOWN");

		boolean SavedBadge = false;
		int SavedCountAfter = Integer.parseInt(SavedBadgeCount.getText().trim());
		logInfo("Saved Count After Saving Task : " + SavedCountAfter);
		{
			if (SavedCountAfter > SavedCountBefore) {
				logInfo("Saved badge count is updated , Tasks saved : " + (SavedCountAfter - SavedCountBefore));
				SavedBadge = true;
			} else {
				logInfo("Task badge count is not updated");
				SavedBadge = false;
			}

			TestValidation.IsTrue(SavedBadge, "Saved badge Count is updated", "Failed to update Saved badge count");
		}

		// Checking details of saved form
		boolean searchSavedTask = homePage.SearchForm(FormName);
		TestValidation.IsTrue(searchSavedTask, "Saved task is present in Saved Forms",
				"Saved Task is not present in Saved Forms");
		Thread.sleep(10000);
		boolean clickSavedTask = homePage.ClickForm(FormName);
		logInfo("Clicked on Task = " + FormName);
		TestValidation.IsTrue(clickSavedTask, "Clicked on Saved Task", "Failed to click on saved task");

		LinkedHashMap<String, String> FieldTypeValues = new LinkedHashMap<String, String>();
		FieldTypeValues.put(Numeric, "8");
		FieldTypeValues.put(ParaTxt, "Para");
		FieldTypeValues.put(chkFreeTxt, "FreeText");
		FieldTypeValues.put(chkSelectOne, "GOOD");
		FieldTypeValues.put(chkDate, dateSelected);
		FieldTypeValues.put(chkDateTime, dateTimeSel);
		FieldTypeValues.put(SelectMultiple, "10");
		FieldTypeValues.put(SelectMultiple, "30");

		boolean verifyUpdatedValue = SavedPageObj.verifyFieldValues(FieldTypeValues);
		boolean verifyImage = oUploadImage.verifySelectedImageThroughGallery();
		TestValidation.IsTrue(verifyUpdatedValue, "VERIFIED updated value for  all fields",
				"Failed to verify updated values for all fields");
		TestValidation.IsTrue(verifyImage, "VERIFIED Image", "Failed to verify Image");
		ControlActions_MobileApp.WaitforelementToBeClickable(FormSubmitClick);
		FormSubmitClick.click();
		ControlActions_MobileApp.waitForVisibility(noResultsFound, 20);
		boolean TaskSub = (noResultsFound.isDisplayed()
				&& (noResultsFound.getText().trim().equalsIgnoreCase("No Results Found")));
		TestValidation.IsTrue(TaskSub, "After task submission task not present in Saved forms:Expected",
				"Task still present in saved forms even after submission");

		ControlActions_MobileApp.waitForVisibility(SavedBadgeCount, 10);
		int SavedCountAfterSubmission = Integer.parseInt(SavedBadgeCount.getText().trim());
		boolean SavedCountAfterSubmissionFlag = false;
		logInfo("Saved Count After submitting Task : " + SavedCountAfterSubmission);
		{
			if (SavedCountAfterSubmission < SavedCountAfter) {
				logInfo("Saved badge count is updated after saved task submission by : "
						+ (SavedCountAfter - SavedCountAfterSubmission));
				SavedCountAfterSubmissionFlag = true;
			} else {
				logInfo("Saved badge count is not updated after saved task submission");
				SavedCountAfterSubmissionFlag = false;
			}

			TestValidation.IsTrue(SavedCountAfterSubmissionFlag,
					"Saved badge Count is updated after saved task submission",
					"Failed to update Saved badge count after saved task submission");
		}

		ControlActions_MobileApp.WaitforelementToBeClickable(submissionBadge);
		submissionBadge.click();
		ControlActions_MobileApp.waitForVisibility(searchBox, 20);
		boolean clickSubBadge = searchBox.isDisplayed() && searchBox.getText().equalsIgnoreCase("Search Submissions");

		TestValidation.IsTrue(clickSubBadge, "Clicked on Submission badge", "Failed to click submission badge");

		ControlActions_MobileApp.click(SavedPageObj.sortBtn);

		Boolean searchForm1 = homePage.SearchForm(FormName);
		TestValidation.IsTrue(searchForm1, "Verified Form Search Functionality Successfully",
				"Failed to verify Form Search");

		Boolean formStatus = SavedPageObj.checkFormStatusFromSubmissionsSubMenu(FormName, "RECEIVED");
		TestValidation.IsTrue(formStatus, "Verified form status from submissions screen",
				"Failed to verify form status");

		SavedPageObj.clickFormFromSubmissionsScreen(FormName);
		Thread.sleep(15000);
		boolean verifyUpdatedValueSub = SavedPageObj.verifyFieldValues(FieldTypeValues);
		boolean verifyImageSub = oUploadImage.verifySelectedImageThroughGallery();
		TestValidation.IsTrue(verifyUpdatedValueSub, "VERIFIED updated value for  all fields in submitted form",
				"Failed to verify updated values for all fields in submitted form");
		TestValidation.IsTrue(verifyImageSub, "VERIFIED Image for submitted form",
				"Failed to verify Image for submitted form");
		backForm.click();

		ControlActions_MobileApp.WaitforelementToBeClickable(taskCountBadge);
		taskCountBadge.click();
		ControlActions_MobileApp.waitForVisibility(searchBox, 20);
		boolean clickInboxBadge = searchBox.isDisplayed() && searchBox.getText().equalsIgnoreCase(TaskName);
		TestValidation.IsTrue(clickInboxBadge, "Validated searchbox in inbox succesfully",
				"Failed to verify inbox searcbox");

		boolean isFormPresentInbox = (noResultsFound.isDisplayed()
				&& (noResultsFound.getText().trim().equalsIgnoreCase("No Results Found")));
		TestValidation.IsTrue(isFormPresentInbox, "After task submission task not present in Inbox",
				"Task still present in inbox even after submission");

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

		FSQABrowserPage fbp = new FSQABrowserPage(driver);
		FSQABrowserRecordsPage rp = new FSQABrowserRecordsPage(driver);
		boolean navigate = fbp.navigateToFSQATab("Records");
		TestValidation.IsTrue(navigate, "Navigated to FSQABrowser>RecordsTab",
				"Failed to navigate to FSQABrowser>Records Tab");

		boolean applyfilter = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, FormName);

		TestValidation.IsTrue(applyfilter, "Applied Filter on" + COLUMNHEADER.FORMNAME,
				"Failed to apply filter on" + COLUMNHEADER.FORMNAME);

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

		TestValidation.IsTrue(verifyUpdatedValueWeb,
				"Submitted form is present on web.VERIFIED updated values for all fields",
				"Failed to verify updated values for fields");
		WebElement image = driver.findElement(By.xpath("//a[@ng-bind='attachment.name']"));
		String FileActual = image.getText();

		boolean AttachmentMob = (FileActual.equals("0.png") || FileActual.equals("1.png"));
		TestValidation.IsTrue(AttachmentMob, "Attachment from mobile is present at bottom on Web",
				"Attachment  from mobile is not present at bottom on Web");
		driver.quit();
	}

	public void OfflineTaskSubmissionFunctionality() throws InterruptedException, ParseException {
		HomePage homePage = new HomePage(appiumDriver);
		UploadImage oUploadImage = new UploadImage(appiumDriver);
		TaskPage InboxPageObj = new TaskPage(appiumDriver);
		SavedPage SavedPageObj = new SavedPage(appiumDriver);

		// Creating Task
		taskCreation();
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

		Boolean txtNumeric = SavedPageObj.enterFieldValue(SavedPageObj.NumericTxt, "8");
		TestValidation.IsTrue(txtNumeric, "Numeric Field value entered successfully", "Failed to enter field value");

		Boolean txtPara = SavedPageObj.enterFieldValue(SavedPageObj.paragraphTxt, "Para");
		TestValidation.IsTrue(txtPara, "Numeric Field value entered successfully", "Failed to enter field value");

		Boolean txtFT = SavedPageObj.enterFieldValue(SavedPageObj.freeText, "FreeText");
		TestValidation.IsTrue(txtFT, "Numeric Field value entered successfully", "Failed to enter field value");

		Boolean selectOne = SavedPageObj.selectOneOrMultipleFieldValues("SelectOne", "GOOD");
		TestValidation.IsTrue(selectOne, "Numeric Field value entered successfully", "Failed to enter field value");
		ControlActions_MobileApp.swipe(400, 700, 400, 100);
		String date = ControlActions_MobileApp.AddDaystoToddaysDate(0);
		Boolean Sedate = SavedPageObj.clickFieldType(homePage.datePicker, date);
		TestValidation.IsTrue(Sedate, "Date Selected successfully", "Failed to select Date value");
		String dateSelected = ControlActions_MobileApp.AddDaystoToddaysDateInGivenFormat("M/d/YY", 0);
		logInfo("Date Field Value = " + dateSelected);
		ControlActions_MobileApp.swipe(400, 700, 400, 100);
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
		oUploadImage.CameraClick();
		Thread.sleep(2000);
		oUploadImage.OpenGallery();
		oUploadImage.SelectImage();
		oUploadImage.CloseGallery();

		boolean submitForm = InboxPageObj.submitTask();

		Thread.sleep(4000);

		TestValidation.IsTrue(submitForm, "Task submitted Successfully", "Failed to submit Task from Inbox");

		LinkedHashMap<String, String> FieldTypeValues = new LinkedHashMap<String, String>();
		FieldTypeValues.put(Numeric, "8");
		FieldTypeValues.put(ParaTxt, "Para");
		FieldTypeValues.put(chkFreeTxt, "FreeText");
		FieldTypeValues.put(chkSelectOne, "GOOD");
		FieldTypeValues.put(chkDate, dateSelected);
		FieldTypeValues.put(chkDateTime, dateTimeSel);
		FieldTypeValues.put(SelectMultiple, "10");
		FieldTypeValues.put(SelectMultiple, "30");

		boolean isFormPresentInboxOff = (noResultsFound.isDisplayed()
				&& (noResultsFound.getText().trim().equalsIgnoreCase("No Results Found")));
		TestValidation.IsTrue(isFormPresentInboxOff, "After task submission task not present in Inbox",
				"Task still present in inbox even after submission");

		ControlActions_MobileApp.WaitforelementToBeClickable(submissionBadge);
		submissionBadge.click();
		// homePage.SearchForm(FormName);

		ControlActions_MobileApp.waitForVisibility(searchBox, 20);
		boolean clickSubBadge = searchBox.isDisplayed() && searchBox.getText().equalsIgnoreCase("Search Submissions");

		TestValidation.IsTrue(clickSubBadge, "Clicked on Submission badge", "Failed to click submission badge");
		ControlActions_MobileApp.click(SavedPageObj.sortBtn);
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
		boolean verifyImageSub = oUploadImage.verifySelectedImageThroughGallery();
		TestValidation.IsTrue(verifyUpdatedValueSub, "VERIFIED updated value for  all fields in submitted form",
				"Failed to verify updated values for all fields in submitted form");
		TestValidation.IsTrue(verifyImageSub, "VERIFIED Image for submitted form",
				"Failed to verify Image for submitted form");
		backForm.click();

		Boolean clickSettingsSubMnu = homePage.ClickSubMenu(homePage.settingsSubMenu);
		TestValidation.IsTrue(clickSettingsSubMnu, "clicked on settings subMenu successfully",
				"Failed to click settings submenu");
		ControlActions_MobileApp.click(SavedPageObj.offlineToggle);
		TestValidation.IsTrue(SeMultiple1, "Offline toggle button has clicked",
				"Failed to click offline toggle button");
		ControlActions_MobileApp.click(SavedPageObj.settingsBckBtn);
		TestValidation.IsTrue(SeMultiple1, "Settings back button has clicked", "Failed to click Settings back button");
		homePage.ClickMenu(homePage.submissionsSubMenu);
		TestValidation.IsTrue(SeMultiple1, "Submission sub menu clicked successfully",
				"Failed to Click Submission sub Menu");
		// ControlActions_MobileApp.swipeScreen("DOWN");
		Boolean clickResubmitAll = SavedPageObj.clickResubmitAll();
		TestValidation.IsTrue(clickResubmitAll, "Resubmit All button clicked successfully",
				"Failed to click Resubmit all button");

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
		ControlActions_MobileApp.click(SavedPageObj.sortBtn);
		boolean formStatOnline = SavedPageObj.checkFormStatusFromSubmissionsSubMenu(FormName, "RECEIVED");

		TestValidation.IsTrue(formStatOnline, "Form status verified succesfully in Online mode",
				"Failed to verify form status in Online mode");

		SavedPageObj.clickFormFromSubmissionsScreen(FormName);

		boolean verifyUpdatedValueSubOn = SavedPageObj.verifyFieldValues(FieldTypeValues);
		boolean verifyImageSubOn = oUploadImage.verifySelectedImageThroughGallery();
		TestValidation.IsTrue(verifyUpdatedValueSubOn,
				"VERIFIED updated value for  all fields in submitted form in Online mode",
				"Failed to verify updated values for all fields in submitted form in Online mode");
		TestValidation.IsTrue(verifyImageSubOn, "VERIFIED Image for submitted form in Online mode",
				"Failed to verify Image for submitted form in Online mode");
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

		FSQABrowserPage fbp = new FSQABrowserPage(driver);
		FSQABrowserRecordsPage rp = new FSQABrowserRecordsPage(driver);
		boolean navigate = fbp.navigateToFSQATab("Records");
		TestValidation.IsTrue(navigate, "Navigated to FSQABrowser>RecordsTab",
				"Failed to navigate to FSQABrowser>Records Tab");

		boolean applyfilter = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, FormName);

		TestValidation.IsTrue(applyfilter, "Applied Filter on" + COLUMNHEADER.FORMNAME,
				"Failed to apply filter on" + COLUMNHEADER.FORMNAME);

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

		TestValidation.IsTrue(verifyUpdatedValueWeb,
				"Submitted form is present on web.VERIFIED updated values for all fields",
				"Failed to verify updated values for fields");
		WebElement image = driver.findElement(By.xpath("//a[@ng-bind='attachment.name']"));
		String FileActual = image.getText();

		boolean AttachmentMob = (FileActual.equals("0.png") || FileActual.equals("1.png"));
		TestValidation.IsTrue(AttachmentMob, "Attachment from mobile is present at bottom on Web",
				"Attachment  from mobile is not present at bottom on Web");
		// driver.quit();
	}

	public void TaskSubmissionFunctionality() throws InterruptedException, ParseException {
		HomePage homePage = new HomePage(appiumDriver);
		UploadImage oUploadImage = new UploadImage(appiumDriver);
		TaskPage InboxPageObj = new TaskPage(appiumDriver);
		SavedPage SavedPageObj = new SavedPage(appiumDriver);
		boolean taskBadgeCount = false;

		ControlActions_MobileApp.waitForVisibility(taskCount, 60);
		int taskCountBefore = Integer.parseInt(taskCount.getText());
		logInfo("TaskCount Before Task Creation : " + taskCountBefore);
		int SavedCountBefore = Integer.parseInt(SavedBadgeCount.getText().trim());
		logInfo("Saved Count Before Saving Task : " + SavedCountBefore);

		// Creating Task
		taskCreation();
		ControlActions_MobileApp.waitForVisibility(SearchFormsTextbox, 30);
		ControlActions_MobileApp.swipeScreen("DOWN");
		Thread.sleep(20000);
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
		Thread.sleep(10000);

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
		oUploadImage.CameraClick();
		Thread.sleep(2000);
		oUploadImage.OpenGallery();
		oUploadImage.SelectImage();
		oUploadImage.CloseGallery();

		boolean submitForm = InboxPageObj.submitTask();

		Thread.sleep(4000);

		TestValidation.IsTrue(submitForm, "Task submitted Successfully", "Failed to submit Task from Inbox");

		LinkedHashMap<String, String> FieldTypeValues = new LinkedHashMap<String, String>();
		FieldTypeValues.put(Numeric, "8");
		FieldTypeValues.put(ParaTxt, "Para");
		FieldTypeValues.put(chkFreeTxt, "FreeText");
		FieldTypeValues.put(chkSelectOne, "GOOD");
		FieldTypeValues.put(chkDate, dateSelected);
		FieldTypeValues.put(chkDateTime, dateTimeSel);
		FieldTypeValues.put(SelectMultiple, "10");
		FieldTypeValues.put(SelectMultiple, "30");

		ControlActions_MobileApp.WaitforelementToBeClickable(submissionBadge);
		submissionBadge.click();
		ControlActions_MobileApp.waitForVisibility(searchBox, 20);
		boolean clickSubBadge = searchBox.isDisplayed() && searchBox.getText().equalsIgnoreCase("Search Submissions");

		TestValidation.IsTrue(clickSubBadge, "Clicked on Submission badge", "Failed to click submission badge");
		SavedPageObj.sortBtn.click();
		SavedPageObj.checkFormStatusFromSubmissionsSubMenu(FormName, "RECEIVED");
		SavedPageObj.clickFormFromSubmissionsScreen(FormName);

		boolean verifyUpdatedValueSub = SavedPageObj.verifyFieldValues(FieldTypeValues);
		boolean verifyImageSub = oUploadImage.verifySelectedImageThroughGallery();
		TestValidation.IsTrue(verifyUpdatedValueSub, "VERIFIED updated value for  all fields in submitted form",
				"Failed to verify updated values for all fields in submitted form");
		TestValidation.IsTrue(verifyImageSub, "VERIFIED Image for submitted form",
				"Failed to verify Image for submitted form");
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

		FSQABrowserPage fbp = new FSQABrowserPage(driver);
		FSQABrowserRecordsPage rp = new FSQABrowserRecordsPage(driver);
		boolean navigate = fbp.navigateToFSQATab("Records");
		TestValidation.IsTrue(navigate, "Navigated to FSQABrowser>RecordsTab",
				"Failed to navigate to FSQABrowser>Records Tab");

		boolean applyfilter = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, FormName);

		TestValidation.IsTrue(applyfilter, "Applied Filter on" + COLUMNHEADER.FORMNAME,
				"Failed to apply filter on" + COLUMNHEADER.FORMNAME);

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

		TestValidation.IsTrue(verifyUpdatedValueWeb,
				"Submitted form is present on web.VERIFIED updated values for all fields",
				"Failed to verify updated values for fields");
		WebElement image = driver.findElement(By.xpath("//a[@ng-bind='attachment.name']"));
		String FileActual = image.getText();

		boolean AttachmentMob = (FileActual.equals("0.png") || FileActual.equals("1.png"));
		TestValidation.IsTrue(AttachmentMob, "Attachment from mobile is present at bottom on Web",
				"Attachment  from mobile is not present at bottom on Web");
		driver.quit();
	}

	public void TaskScheduler() {
		try {

			FormName = CommonMethods.dynamicText("API_Form");
			TaskName = CommonMethods.dynamicText("API_TaskScheduler");
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
			String TodaysDuedate = ControlActions_MobileApp.AddDaystoToddaysDateInGivenFormat("MM/dd/YYYY", 1);
			TodaysDuedate = TodaysDuedate + " 00:00";
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
			tp.ExpirationIntervalType = "Never";
			tp.FirstDueDate = TodaysDuedate;
			tp.AssignTimeBeforeTaskIsDueHours = "24";
			tp.Timezone = "Republic of India";
			tp.Occurrence = TaskOccurence.ONETIMEONLY;
			tp.Repetition = "2";

			taskCreationWrapper.create_Link_workGroup_Wrapper(tp);
			taskCreationWrapper.create_Task_TaskScheduler_Wrapper(tp);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void TaskViaTaskScheduler() throws InterruptedException, ParseException {
		HomePage homePage = new HomePage(appiumDriver);
		UploadImage oUploadImage = new UploadImage(appiumDriver);
		TaskPage InboxPageObj = new TaskPage(appiumDriver);
		SavedPage SavedPageObj = new SavedPage(appiumDriver);
		boolean taskBadgeCount = false;

		ControlActions_MobileApp.waitForVisibility(taskCount, 60);
		int taskCountBefore = Integer.parseInt(taskCount.getText());
		logInfo("TaskCount Before Task Creation : " + taskCountBefore);
		int SavedCountBefore = Integer.parseInt(SavedBadgeCount.getText().trim());
		logInfo("Saved Count Before Saving Task : " + SavedCountBefore);

		// Creating Task
		TaskScheduler();
		ControlActions_MobileApp.waitForVisibility(SearchFormsTextbox, 30);

		ControlActions_MobileApp.swipeScreen("DOWN");
		Thread.sleep(20000);
		ControlActions_MobileApp.swipeScreen("DOWN");
		Thread.sleep(10000);
		ControlActions_MobileApp.swipeScreen("DOWN");
		Thread.sleep(10000);
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

		boolean clickInboxMenu = homePage.ClickSubMenu(homePage.inboxSubMenu);

		TestValidation.IsTrue(clickInboxMenu, "Clicked on Inbox Sub Menu", "Failed to click Inbox Submenu");
		System.out.println("Taskname is :" + TaskName);
		boolean searchForm = homePage.SearchTask(TaskName);
		TestValidation.IsTrue(searchForm, "Latest Created Task is present in Inbox",
				"Latest Created Task is not present in Inbox");
		WebElement formCheck = ControlActions_MobileApp.perform_GetElementByXPath(HomePageLocators.Form_Name,
				"FormNameLocator", TaskName);
		ControlActions_MobileApp.waitForVisibility(formCheck, 60);
		ControlActions_MobileApp.click(formCheck);
		logInfo("Clicked on Task = " + TaskName);
		Thread.sleep(10000);

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
		oUploadImage.CameraClick();
		Thread.sleep(2000);
		oUploadImage.OpenGallery();
		oUploadImage.SelectImage();
		oUploadImage.CloseGallery();

		boolean submitForm = InboxPageObj.submitTask();

		Thread.sleep(10000);

		TestValidation.IsTrue(submitForm, "Task submitted Successfully", "Failed to submit Task from Inbox");

		LinkedHashMap<String, String> FieldTypeValues1 = new LinkedHashMap<String, String>();
		FieldTypeValues1.put(Numeric, "8");
		FieldTypeValues1.put(ParaTxt, "Para");
		FieldTypeValues1.put(chkFreeTxt, "FreeText");
		FieldTypeValues1.put(chkSelectOne, "GOOD");
		FieldTypeValues1.put(chkDate, dateSelected);
		FieldTypeValues1.put(chkDateTime, dateTimeSel);
		FieldTypeValues1.put(SelectMultiple, "10");
		FieldTypeValues1.put(SelectMultiple, "30");

		ControlActions_MobileApp.WaitforelementToBeClickable(submissionBadge);
		submissionBadge.click();
		ControlActions_MobileApp.waitForVisibility(searchBox, 20);
		boolean clickSubBadge = searchBox.isDisplayed() && searchBox.getText().equalsIgnoreCase("Search Submissions");

		TestValidation.IsTrue(clickSubBadge, "Clicked on Submission badge", "Failed to click submission badge");
		boolean clickSubBadge1 = homePage.SearchForm(FormName);
		TestValidation.IsTrue(clickSubBadge1, "Searched form succesfully in submissions",
				"Failed to search form in submissions");
		SavedPageObj.checkFormStatusFromSubmissionsSubMenu(FormName, "RECEIVED");
		SavedPageObj.clickFormFromSubmissionsScreen(FormName);
		Thread.sleep(10000);
		boolean verifyUpdatedValueSub = SavedPageObj.verifyFieldValues(FieldTypeValues1);
		TestValidation.IsTrue(verifyUpdatedValueSub, "VERIFIED updated value for  all fields in submitted form",
				"Failed to verify updated values for all fields in submitted form");
		boolean verifyImageSub = oUploadImage.verifySelectedImageThroughGallery();

		TestValidation.IsTrue(verifyImageSub, "VERIFIED Image for submitted form",
				"Failed to verify Image for submitted form");
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

		FSQABrowserPage fbp = new FSQABrowserPage(driver);
		FSQABrowserRecordsPage rp = new FSQABrowserRecordsPage(driver);

		boolean navigate = fbp.navigateToFSQATab("Records");
		TestValidation.IsTrue(navigate, "Navigated to FSQABrowser>RecordsTab",
				"Failed to navigate to FSQABrowser>Records Tab");

		boolean applyfilter = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, FormName);

		TestValidation.IsTrue(applyfilter, "Applied Filter on" + COLUMNHEADER.FORMNAME,
				"Failed to apply filter on" + COLUMNHEADER.FORMNAME);

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

		TestValidation.IsTrue(verifyUpdatedValueWeb,
				"Submitted form is present on web.VERIFIED updated values for all fields",
				"Failed to verify updated values for fields");
		WebElement image = driver.findElement(By.xpath("//a[@ng-bind='attachment.name']"));
		String FileActual = image.getText();

		boolean AttachmentMob = (FileActual.equals("0.png") || FileActual.equals("1.png"));
		TestValidation.IsTrue(AttachmentMob, "Attachment from mobile is present at bottom on Web",
				"Attachment  from mobile is not present at bottom on Web");

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

}