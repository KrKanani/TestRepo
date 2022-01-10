package com.project.safetychain.mobileapp.pageobjects;

import java.awt.AWTException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.NoSuchElementException;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.project.safetychain.api.models.support.Support.FormFieldParams;
import com.project.safetychain.api.models.support.TCG_TaskCreation_Wrapper;
import com.project.safetychain.mobileapp.testcases.TCG_SMK_FormSavedFlow;
import com.project.safetychain.webapp.pageobjects.CommonPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserFormsPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPageLocators;
import com.project.safetychain.webapp.pageobjects.FSQABrowserRecordsPage;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage;
import com.project.safetychain.webapp.pageobjects.FormDesignerPageLocator;
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

public class REG_DynamicFlow extends TestBase {

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
	REG_DynamicFlow afsaved;
	FSQABrowserRecordsPage fbrp;
	TCG_SMK_FormSavedFlow savedTc;
	REG_FormsAllValidationLocators afValidate;

	public static String locationCategoryValue;
	public static String locationInstanceValue1;
	public static String locationInstanceValue2;
	public static String resourceCategoryValue;
	public static String resourceInstanceValue1;
	public static String resourceInstanceValue2;
	public static String chkFreeTxt, ParaTxt, chkSelectOne, SelectMultiple, Numeric, chkDate, chkTime, chkDateTime,
			Note, selectOne2, chkDateDiff, D1, D2, selectOneID2, selectOneID1;

	public static String noteText = "Test Mobile Form", documentTypeName, documentName = "upload.png";
	public static String filePath = System.getProperty("user.dir") + "\\test-data-files\\UploadDocuments\\"
			+ documentName;

	String formtype = "Check";
	String modifiedby;
	public static String FormName;
	public static String TaskName;
	public static String WGName;

	@AndroidFindBy(id = REG_FormsAllValidationLocators.MINMAXLIM)
	public WebElement MinMaxLimit;

	@AndroidFindBy(id = REG_FormsAllValidationLocators.UOM)
	public WebElement Uom;

	@AndroidFindBy(id = REG_FormsAllValidationLocators.BACKFORM)
	public WebElement backFormBtn;
	@AndroidFindBy(id = REG_FormsAllValidationLocators.DELETEFORM)
	public WebElement deleteFormBtn;
	@AndroidFindBy(id = REG_FormsAllValidationLocators.DELETEPOPUP)
	public WebElement deletePopup;
	@AndroidFindBy(id = REG_FormsAllValidationLocators.DELETECANCEL)
	public WebElement deleteCancelBtn;
	@AndroidFindBy(id = REG_FormsAllValidationLocators.DELETEYESBTN)
	public WebElement deleteYesBtn;
	@AndroidFindBy(id = REG_FormsAllValidationLocators.DISCARDSAVEPOPUP)
	public WebElement discardSavePopupBtn;
	@AndroidFindBy(id = REG_FormsAllValidationLocators.DISCARDBTN)
	public WebElement discardPopupBtn;

	@AndroidFindBy(id = REG_AllFormsSavedLocators.TASK_COUNT)
	public WebElement taskCount;

	@AndroidFindBy(id = REG_AllFormsSavedLocators.CLOSESEARCH)
	public WebElement closeSearchBtn;
	@AndroidFindBy(id = REG_FormsAllValidationLocators.SUBMITREPEATBTN)
	public WebElement submitAndRepeatBtn;

	@AndroidFindBy(id = REG_AllFormsSavedLocators.NORESULTSFOUND)
	public WebElement noResultsFound;

	@AndroidFindBy(id = UpdatedFormVersionPageLocators.FormVersion)
	public WebElement formVersion;

	@AndroidFindBy(id = REG_FormsAllValidationLocators.FIELDVALUEBOX)
	public WebElement fieldValueEnterBox;

	@AndroidFindBy(xpath = REG_FormsAllValidationLocators.FIELDLEVELIMAGE)
	public WebElement fieldLevelImage;
	@AndroidFindBy(xpath = REG_FormsAllValidationLocators.FORMLEVELIMAGE)
	public WebElement formLevelImage;
	@AndroidFindBy(id = REG_FormsAllValidationLocators.IMAGEDELETE)
	public WebElement imageDelete;
	@AndroidFindBy(id = REG_FormsAllValidationLocators.IMAGETITLE)
	public WebElement imageTitle;
	@AndroidFindBy(id = REG_FormsAllValidationLocators.IMAGEBACKBTN)
	public WebElement imageBackBtn;
	@AndroidFindBy(id = REG_FormsAllValidationLocators.IMAGE)
	public WebElement openedImage;

	@AndroidFindBy(id = REG_FormsAllValidationLocators.IMAGEDELETEPOPUPMSG)
	public WebElement imageDeletePopupMsg;
	@AndroidFindBy(id = REG_FormsAllValidationLocators.IMAGECANCELBTN)
	public WebElement imageCancelBtn;
	@AndroidFindBy(id = REG_FormsAllValidationLocators.IMAGEDELETEBTN)
	public WebElement imageDeleteBtn;

	@AndroidFindBy(id = REG_FormsAllValidationLocators.FORMNOTELABEL)
	public WebElement noteLabel;
	@AndroidFindBy(id = REG_FormsAllValidationLocators.FORMNOTEEXT)
	public WebElement noteTextPresent;
	@AndroidFindBy(id = REG_FormsAllValidationLocators.RELDOCSLABEL)
	public WebElement RelDocLabel;

	@AndroidFindBy(xpath = REG_FormsAllValidationLocators.RELATEDDOC)
	public WebElement relatedDoc;
	@AndroidFindBy(id = REG_FormsAllValidationLocators.DEFAULTVALUE)
	public WebElement freeTextDefaultVal;

	@AndroidFindBy(xpath = REG_FormsAllValidationLocators.SELECTONEVALUE)
	public WebElement selectOneValue;

	@AndroidFindBy(xpath = REG_FormsAllValidationLocators.SELECTONE2VALUE)
	public WebElement selectOne2Value;

	@AndroidFindBy(xpath = REG_FormsAllValidationLocators.SELECTONEID2VALUE)
	public WebElement selectOneID2Value;

	@AndroidFindBy(xpath = REG_FormsAllValidationLocators.FREETEXTEXPRESULT)
	public WebElement freeTextExpValue;

	@FindBy(xpath = FSQABrowserPageLocators.FORM_ROW)
	public WebElement FormRow;

	@FindBy(xpath = FSQABrowserPageLocators.CALLOUT_MNU)
	public WebElement CalloutMenu;

	@FindBy(xpath = FSQABrowserPageLocators.EDIT_FORM_MNU)
	public WebElement EditFormMenu;

	@FindBy(xpath = FormDesignerPageLocator.NOTE_DBL)
	public WebElement NoteDbl;

	@FindBy(xpath = FormDesignerPageLocator.RELATED_DOCS_DBL)
	public WebElement RelatedDocsDbl;

	@FindBy(xpath = FormDesignerPageLocator.RELEASE_FORM_PG)
	public WebElement ReleaseFormPg;

	@FindBy(xpath = FormDesignerPageLocator.HEADER_LEVEL)
	public WebElement HeaderLevel;

	@FindBy(xpath = FormDesignerPageLocator.NOTE_TXT)
	public WebElement NoteTxt;

	@AndroidFindBy(id = REG_AllFormsSavedLocators.SAVED_BADGE_BTNCOUNT)
	public WebElement SavedBadgeCount;

	@AndroidFindBy(id = REG_AllFormsSavedLocators.TASKCOUNTBADGE)
	public WebElement taskCountBadge;

	public static final String selOptionPath = "//android.widget.TextView[contains(@text,'FieldLabel')]//parent::android.widget.RelativeLayout";
	public static final String selValue = "//following-sibling::android.widget.LinearLayout//android.widget.Button[contains(@text,'SelectOption')]";
	public static final String TEXTVALUE = "//android.widget.TextView[@text='ABC']//parent::android.widget.RelativeLayout//following-sibling::android.widget.RelativeLayout//android.widget.EditText";

	public static final String fresult = "com.safetychain.SCM.M2:id/resultView_num";
	@AndroidFindBy(id = fresult)
	public WebElement NTxt;
	TCG_TaskCreation_Wrapper taskCreationWrapper = null;
	HashMap<String, String> locResMap = null;
	String formId = null;
	String dateTime = null;
	String dateSelected = null;

	public REG_DynamicFlow(AppiumDriver<MobileElement> driver) {
		this.appiumDriver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	public void DyamicFlowValidation()
			throws ElementNotVisibleException, InterruptedException, ParseException, AWTException {

		HomePage homePage = new HomePage(appiumDriver);

		SavedPage SavedPageObj = new SavedPage(appiumDriver);
		String FormName = "OEE Events";
		@SuppressWarnings("unused")
		String resourceInstanceValue1 = "OEE Equipment Migration resource"; 
		String locationInstanceValue1 = "Site";
		Thread.sleep(10000);

		ControlActions_MobileApp.waitForVisibility(taskCount, 1200);
		int taskCountBefore = Integer.parseInt(taskCount.getText());
		logInfo("TaskCount Before Dynamic flow Task Creation : " + taskCountBefore);

		boolean searchForm = homePage.SearchForm(FormName);
		TestValidation.IsTrue(searchForm, "Latest Created form is present in All forms",
				"Latest Created Form is not present in All forms");

		boolean clickForm = homePage.ClickForm(FormName);
		TestValidation.IsTrue(clickForm, "Clicked on Form Successfully", "Failed to click Form");
		boolean searchResource = SavedPageObj.SearchResource(locationInstanceValue1);
		TestValidation.IsTrue(searchResource, "location Searched Successfully",
				"Failed to Search location from Forms subMenu");
		
		boolean searchResource1 = SavedPageObj.SearchResource(resourceInstanceValue1);
		TestValidation.IsTrue(searchResource1, "Resource Searched Successfully",
				"Failed to Search Resource from Forms subMenu");

		String TaskName = CommonMethods.dynamicText("AndroidDF");

		/******************** ACTUAL PRODUCTION START ******************/

		boolean batchID = enterFieldValue("BatchID *", TaskName + "APS");
		TestValidation.IsTrue(batchID, "BatchID value entered successfully", "Failed to enter BatchID value");

		boolean shift = selectOption("Shift", "SHIFT 1");

		TestValidation.IsTrue(shift, "Shift value entered successfully", "Failed to enter Shift value");

		boolean eventType = selectOption("Event Type *", "ACTUAL PRODUCTION START");

		TestValidation.IsTrue(eventType, "Event Type value entered successfully", "Failed to enter Event Type value");

		ControlActions_MobileApp.swipeScreen("UP");
		String date = ControlActions_MobileApp.AddDaystoToddaysDate(0);

		Boolean SedateTime = SavedPageObj.clickFieldType(homePage.dateTimePicker, date);
		String dateTimeSel = homePage.dateTimePicker.getText();
		logInfo("Date&Time Field Value = " + dateTimeSel);

		TestValidation.IsTrue(SedateTime, "DateTime value selected successfully", "Failed to Select DateTime value");

		Boolean submitForm = SavedPageObj.submitForm();
		TestValidation.IsTrue(submitForm, "Form Submitted Successfully", "Failed to Submit Form");

		ControlActions_MobileApp.waitForVisibility(closeSearchBtn, 20);
		ControlActions_MobileApp.WaitforelementToBeClickable(closeSearchBtn);
		closeSearchBtn.click();
		ControlActions_MobileApp.swipeScreen("DOWN");
		Thread.sleep(40000);
		ControlActions_MobileApp.swipeScreen("DOWN");
		Thread.sleep(30000);
		ControlActions_MobileApp.swipeScreen("DOWN");
		Thread.sleep(20000);
		int afterASPCount = Integer.parseInt(taskCount.getText());
		logInfo("Tasks count after submitting form : " + afterASPCount);

		boolean taskCountF = false;
		if (afterASPCount > taskCountBefore) {
			logInfo("task count is increased by :" + (afterASPCount - taskCountBefore));
			taskCountF = true;
		}

		TestValidation.IsTrue(taskCountF, "Task count incremented succesfully after submitting dynamic flow form",
				"Failed to increment Task count after submitting dynamic flow form");

		ControlActions_MobileApp.WaitforelementToBeClickable(taskCountBadge);
		taskCountBadge.click();
		ControlActions_MobileApp.waitForVisibility(homePage.searchField, 60);
		ControlActions_MobileApp.WaitforelementToBeClickable(homePage.searchField);
		Thread.sleep(5000);

		throughputQtySubmit(afterASPCount, TaskName);

	}

	public void throughputQtySubmit(int taskCountBefore, String TaskName) throws InterruptedException, ParseException {

		HomePage homePage = new HomePage(appiumDriver);
		SavedPage SavedPageObj = new SavedPage(appiumDriver);
		Thread.sleep(20000);
		boolean searchFormIn = homePage.SearchTask("OEEEvent_" + TaskName + "APS");
		TestValidation.IsTrue(searchFormIn,
				"OEEEvent_" + TaskName + "APS" + " Task after dynamic Flow form submission is present in Inbox",
				"OEEEvent_" + TaskName + "APS" + " Task after dynamic Flow form submission is not present in Inbox");
		WebElement formCheck = ControlActions_MobileApp.perform_GetElementByXPath(HomePageLocators.Form_Name,
				"FormNameLocator", "OEEEvent_" + TaskName + "APS");
		ControlActions_MobileApp.waitForVisibility(formCheck, 60);
		ControlActions_MobileApp.click(formCheck);
		logInfo("Clicked on Task = " + "OEEEvent_" + TaskName + "APS");
		Thread.sleep(10000);

		// Verify carryForward

		verifyCarryForwardFieldValue(TaskName, "APS");

		/******************** THROUGHPUT QUANTITY ******************/

		boolean batchID = enterFieldValue("BatchID *", TaskName + "TQ");
		TestValidation.IsTrue(batchID, "BatchID value entered successfully", "Failed to enter BatchID value");

		boolean eventType = selectOption("Event Type *", "THROUGHPUT QUANTITY");

		TestValidation.IsTrue(eventType, "Event Type value entered successfully", "Failed to enter Event Type value");
		ControlActions_MobileApp.swipe(400, 700, 400, 100);
		ControlActions_MobileApp.swipe(400, 700, 400, 100);
		ControlActions_MobileApp.swipe(400, 700, 400, 100);
		ControlActions_MobileApp.swipe(400, 700, 400, 100);
		boolean QtyProduced = enterFieldValue("Quantity Produced *", "100");
		TestValidation.IsTrue(QtyProduced, "Quantity Produced value entered successfully",
				"Failed to enter Quantity Produced value");
		ControlActions_MobileApp.swipe(400, 700, 400, 100);
		String date = ControlActions_MobileApp.AddDaystoToddaysDate(0);

		Boolean SedateTime = SavedPageObj.clickFieldType(homePage.dateTimePicker, date);
		String dateTimeSel = homePage.dateTimePicker.getText();
		logInfo("Date&Time Field Value = " + dateTimeSel);

		TestValidation.IsTrue(SedateTime, "DateTime value selected successfully", "Failed to Select DateTime value");

		boolean submitForm = SavedPageObj.submitForm();
		TestValidation.IsTrue(submitForm, "Form Submitted Successfully", "Failed to Submit Form");
		ControlActions_MobileApp.waitForVisibility(closeSearchBtn, 20);
		ControlActions_MobileApp.WaitforelementToBeClickable(closeSearchBtn);
		closeSearchBtn.click();
		ControlActions_MobileApp.swipeScreen("DOWN");
		Thread.sleep(60000);
		int afterASPCount = Integer.parseInt(taskCount.getText());
		logInfo("Tasks count after submitting form : " + afterASPCount);

		boolean taskCountF = false;
		if (afterASPCount == taskCountBefore) {
			logInfo("task count is same :" + (afterASPCount - taskCountBefore));
			taskCountF = true;
		}

		TestValidation.IsTrue(taskCountF, "Task count incremented succesfully after submitting dynamic flow form",
				"Failed to increment Task count after submitting dynamic flow form");

		unplannedDowntimeSubmit(afterASPCount, TaskName);
	}

	public void verifyCarryForwardFieldValue(String TaskName, String Event) {
		SavedPage SavedPageObj = new SavedPage(appiumDriver);
		boolean batchVal = false;

		WebElement batch = appiumDriver.findElementById("resultView_single");
		if (batch.getText().equalsIgnoreCase(TaskName + Event)) {
			batchVal = true;
		}

		TestValidation.IsTrue(batchVal, "Carry forward field value for BatchID is verified succesfully",
				"Failed to verify Carry forward field value for BatchID");

		LinkedHashMap<String, String> FieldTypeValues = new LinkedHashMap<String, String>();
		FieldTypeValues.put("SelectOne", "SHIFT 1");

		boolean verifyUpdatedValue = SavedPageObj.verifyFieldValues(FieldTypeValues);

		TestValidation.IsTrue(verifyUpdatedValue, "Carry forward Shift field values are verified succesfully",
				"Failed to verify Carry forward Shift field values");
	}

	public void unplannedDowntimeSubmit(int taskCountBefore, String TaskName)
			throws ParseException, InterruptedException {

		HomePage homePage = new HomePage(appiumDriver);
		SavedPage SavedPageObj = new SavedPage(appiumDriver);
		Thread.sleep(20000);
		boolean searchFormIn = homePage.SearchTask("OEEEvent_" + TaskName + "TQ");
		TestValidation.IsTrue(searchFormIn,
				"OEEEvent_" + TaskName + "TQ" + " Task after dynamic Flow task submission is present in Inbox",
				"OEEEvent_" + TaskName + "TQ" + " Task after dynamic Flow task submission is not present in Inbox");
		WebElement formCheck = ControlActions_MobileApp.perform_GetElementByXPath(HomePageLocators.Form_Name,
				"FormNameLocator", "OEEEvent_" + TaskName + "TQ");
		ControlActions_MobileApp.waitForVisibility(formCheck, 60);
		ControlActions_MobileApp.click(formCheck);
		logInfo("Clicked on Task = " + "OEEEvent_" + TaskName + "TQ");
		Thread.sleep(10000);

		verifyCarryForwardFieldValue(TaskName, "TQ");

		/******************** UNPLANNED DOWNTIME ******************/

		boolean batchID = enterFieldValue("BatchID *", TaskName + "UD");
		TestValidation.IsTrue(batchID, "BatchID value entered successfully", "Failed to enter BatchID value");

		boolean eventType = selectOption("Event Type *", "UNPLANNED DOWNTIME");

		TestValidation.IsTrue(eventType, "Event Type value entered successfully", "Failed to enter Event Type value");
		ControlActions_MobileApp.swipe(400, 700, 400, 100);
		ControlActions_MobileApp.swipe(400, 700, 400, 100);
		boolean DownTime = selectOption("Downtime Event Type *", "START");
		TestValidation.IsTrue(DownTime, "Downtime Event Type value entered successfully",
				"Failed to enter Downtime Event Type value");
		ControlActions_MobileApp.swipe(400, 700, 400, 100);
		String date = ControlActions_MobileApp.AddDaystoToddaysDate(0);

		Boolean SedateTime = SavedPageObj.clickFieldType(homePage.dateTimePicker, date);
		String dateTimeSel = homePage.dateTimePicker.getText();
		logInfo("Date&Time Field Value = " + dateTimeSel);

		TestValidation.IsTrue(SedateTime, "DateTime value selected successfully", "Failed to Select DateTime value");

		boolean submitForm = SavedPageObj.submitForm();
		TestValidation.IsTrue(submitForm, "Form Submitted Successfully", "Failed to Submit Form");
		ControlActions_MobileApp.waitForVisibility(closeSearchBtn, 20);
		closeSearchBtn.click();
		ControlActions_MobileApp.swipeScreen("DOWN");
		Thread.sleep(60000);
		int afterASPCount = Integer.parseInt(taskCount.getText());
		logInfo("Tasks count after submitting form : " + afterASPCount);

		boolean taskCountF = false;
		if (afterASPCount == taskCountBefore) {
			logInfo("task count is same :" + (afterASPCount - taskCountBefore));
			taskCountF = true;
		}

		TestValidation.IsTrue(taskCountF, "Task count incremented succesfully after submitting dynamic flow form",
				"Failed to increment Task count after submitting dynamic flow form");
		qualitySubmit(afterASPCount, TaskName);
	}

	public void qualitySubmit(int taskCountBefore, String TaskName) throws ParseException, InterruptedException {

		HomePage homePage = new HomePage(appiumDriver);
		SavedPage SavedPageObj = new SavedPage(appiumDriver);
		Thread.sleep(20000);
		boolean searchFormIn = homePage.SearchTask("OEEEvent_" + TaskName + "UD");
		TestValidation.IsTrue(searchFormIn,
				"OEEEvent_" + TaskName + "UD" + " Task after dynamic Flow task submission is present in Inbox",
				"OEEEvent_" + TaskName + "UD" + " Task after dynamic Flow task submission is not present in Inbox");
		WebElement formCheck = ControlActions_MobileApp.perform_GetElementByXPath(HomePageLocators.Form_Name,
				"FormNameLocator", "OEEEvent_" + TaskName + "UD");
		ControlActions_MobileApp.waitForVisibility(formCheck, 60);
		ControlActions_MobileApp.click(formCheck);
		logInfo("Clicked on Task = " + "OEEEvent_" + TaskName + "UD");
		Thread.sleep(10000);

		// Verify carryForward

		verifyCarryForwardFieldValue(TaskName, "UD");

		/******************** QUALITY ******************/

		boolean batchID = enterFieldValue("BatchID *", TaskName + "QUALITY");
		TestValidation.IsTrue(batchID, "BatchID value entered successfully", "Failed to enter BatchID value");
		ControlActions_MobileApp.swipe(400, 700, 400, 100);
		boolean eventType = selectOption("Event Type *", "QUALITY");

		TestValidation.IsTrue(eventType, "Event Type value entered successfully", "Failed to enter Event Type value");
		ControlActions_MobileApp.swipe(400, 700, 400, 100);
		ControlActions_MobileApp.swipe(400, 700, 400, 100);
		boolean qualityEvent = selectOption("Quality Event Type *", "START UP REJECTS");
		TestValidation.IsTrue(qualityEvent, "Quality Event Type value entered successfully",
				"Failed to enter Quality Event Type value");
		boolean reason = selectOption("Rejects on Start up Reason *", "PROCESS FAILURE");
		TestValidation.IsTrue(reason, "Rejects on Start up Reason value entered successfully",
				"Failed to enter Rejects on Start up Reason value");
		ControlActions_MobileApp.swipe(400, 700, 400, 100);
		ControlActions_MobileApp.swipe(400, 700, 400, 100);

		boolean qtyRejected = enterFieldValue("Event Quantity Rejected *", "10");
		TestValidation.IsTrue(qtyRejected, "Event Quantity Rejected value entered successfully",
				"Failed to enter Event Quantity Rejected value");

		ControlActions_MobileApp.swipe(400, 700, 400, 100);
		String date = ControlActions_MobileApp.AddDaystoToddaysDate(0);

		Boolean SedateTime = SavedPageObj.clickFieldType(homePage.dateTimePicker, date);
		String dateTimeSel = homePage.dateTimePicker.getText();
		logInfo("Date&Time Field Value = " + dateTimeSel);

		TestValidation.IsTrue(SedateTime, "DateTime value selected successfully", "Failed to Select DateTime value");

		ControlActions_MobileApp.swipe(400, 700, 400, 100);

		boolean otherReason = enterFieldValue("Other Reason", "NA");
		TestValidation.IsTrue(otherReason, "Other Reason value entered successfully",
				"Failed to enter Other Reason value");

		boolean submitForm = SavedPageObj.submitForm();
		TestValidation.IsTrue(submitForm, "Form Submitted Successfully", "Failed to Submit Form");
		ControlActions_MobileApp.waitForVisibility(closeSearchBtn, 20);
		closeSearchBtn.click();
		ControlActions_MobileApp.swipeScreen("DOWN");
		Thread.sleep(60000);
		int afterASPCount = Integer.parseInt(taskCount.getText());
		logInfo("Tasks count after submitting form : " + afterASPCount);

		boolean taskCountF = false;
		if (afterASPCount == taskCountBefore) {
			logInfo("task count is same :" + (afterASPCount - taskCountBefore));
			taskCountF = true;
		}

		TestValidation.IsTrue(taskCountF, "Task count is same after submitting dynamic flow task chain",
				"Task count is not same after submitting dynamic flow task chain");

		actualProductionEnd(afterASPCount, TaskName);
	}

	public void actualProductionEnd(int taskCountBefore, String TaskName) throws InterruptedException, ParseException {

		HomePage homePage = new HomePage(appiumDriver);
		SavedPage SavedPageObj = new SavedPage(appiumDriver);
		Thread.sleep(20000);
		boolean searchFormIn = homePage.SearchTask("OEEEvent_" + TaskName + "QUALITY");
		TestValidation.IsTrue(searchFormIn,
				"OEEEvent_" + TaskName + "QUALITY" + " Task after dynamic Flow task submission is present in Inbox",
				"OEEEvent_" + TaskName + "QUALITY"
						+ " Task after dynamic Flow task submission is not present in Inbox");
		WebElement formCheck = ControlActions_MobileApp.perform_GetElementByXPath(HomePageLocators.Form_Name,
				"FormNameLocator", "OEEEvent_" + TaskName + "QUALITY");
		ControlActions_MobileApp.waitForVisibility(formCheck, 60);
		ControlActions_MobileApp.click(formCheck);
		logInfo("Clicked on Task = " + "OEEEvent_" + TaskName + "QUALITY");
		Thread.sleep(10000);

		// Verify carryForward

		verifyCarryForwardFieldValue(TaskName, "QUALITY");

		/******************** QUALITY ******************/

		boolean batchID = enterFieldValue("BatchID *", TaskName + "APE");
		TestValidation.IsTrue(batchID, "BatchID value entered successfully", "Failed to enter BatchID value");

		boolean eventType = selectOption("Event Type *", "ACTUAL PRODUCTION END");

		TestValidation.IsTrue(eventType, "Event Type value entered successfully", "Failed to enter Event Type value");
		ControlActions_MobileApp.swipe(400, 700, 400, 100);

		String date = ControlActions_MobileApp.AddDaystoToddaysDate(0);

		Boolean SedateTime = SavedPageObj.clickFieldType(homePage.dateTimePicker, date);
		String dateTimeSel = homePage.dateTimePicker.getText();
		logInfo("Date&Time Field Value = " + dateTimeSel);

		TestValidation.IsTrue(SedateTime, "DateTime value selected successfully", "Failed to Select DateTime value");

		ControlActions_MobileApp.swipe(400, 700, 400, 100);

		boolean actQtyProduced = enterFieldValue("Actual Quantity Produced *", "10");
		TestValidation.IsTrue(actQtyProduced, "Actual Quantity Produced value entered successfully",
				"Failed to enter Actual Quantity Produced value");

		ControlActions_MobileApp.swipe(400, 700, 400, 100);

		boolean actQtyRejected = enterFieldValue("Actual Quantity Rejected *", "2");
		TestValidation.IsTrue(actQtyRejected, "Actual Quantity Rejected value entered successfully",
				"Failed to enter Actual Quantity Rejected value");

		boolean submitForm = SavedPageObj.submitForm();
		TestValidation.IsTrue(submitForm, "Form Submitted Successfully", "Failed to Submit Form");
		ControlActions_MobileApp.waitForVisibility(closeSearchBtn, 20);
		closeSearchBtn.click();
		ControlActions_MobileApp.swipeScreen("DOWN");
		Thread.sleep(60000);
		int afterASPCount = Integer.parseInt(taskCount.getText());
		logInfo("Tasks count after submitting form : " + afterASPCount);

		boolean taskCountF = false;
		if (afterASPCount < taskCountBefore) {
			logInfo("task count is decreased by :" + (afterASPCount - taskCountBefore));
			taskCountF = true;
		}

		TestValidation.IsTrue(taskCountF,
				"Task count is decremented succesfully after actual production end task submission",
				"Failed to decrement count after actual production end task submission");

		boolean searchForm = homePage.SearchTask("OEEEvent_" + TaskName + "APE");
		TestValidation.IsFalse(searchForm,
				"OEEEvent_" + TaskName + "APE"
						+ " Task not created after selecting actual production end in Inbox : PASS",
				"OEEEvent_" + TaskName + "APE"
						+ " Task is created even after selecting actual production end in Inbox : FAIL");
	}

	public boolean enterFieldValue(String replaceTo, String FieldValue) {
		try {

			String FieldType1 = perform_GetElementByXPath2(TEXTVALUE, "ABC", replaceTo);
			WebElement FieldType = appiumDriver.findElement(By.xpath(FieldType1));
			ControlActions_MobileApp.waitForVisibility(FieldType, 80);
			ControlActions_MobileApp.ClearText(FieldType);
			ControlActions_MobileApp.actionEnterText(FieldType, FieldValue);
			// ControlActions_MobileApp.performTabAction();
			logInfo("Field Value has Entered");
			appiumDriver.hideKeyboard();

			return true;
		} catch (

		Exception ex) {
			logInfo("Failed to Search resource" + ex.getMessage());
			return false;
		}

	}

	public static String perform_GetElementByXPath2(String xPath, String replaceWith, String replaceTo) {

		String finalXpath = null;
		try {
			finalXpath = xPath.replaceAll(replaceWith, replaceTo);

		} catch (Exception e) {
			logError("Failed to get element with xpath - " + finalXpath + " - " + e.getMessage());
		}
		return finalXpath;
	}

	public boolean selectOption(String FieldLabel, String OptionValue) {

		String selectField = null;
		String selectField2 = null;
		String selectField1 = null;
		selectField1 = ControlActions_MobileApp.perform_GetElementByXPath2(selOptionPath, "FieldLabel", FieldLabel);
		System.out.println(selectField1);
		selectField2 = ControlActions_MobileApp.perform_GetElementByXPath2(selValue, "SelectOption", OptionValue);
		System.out.println(selectField2);

		selectField = selectField1 + selectField2;
		System.out.println(selectField);
		boolean isVisible = false;
		do {
			try {
				ControlActions_MobileApp.waitForVisibility(appiumDriver.findElement(By.xpath(selectField)), 3);
				if (appiumDriver.findElement(By.xpath(selectField)).isDisplayed()) {

					System.out.println("Element is visible");
					ControlActions_MobileApp
							.WaitforelementToBeClickable(appiumDriver.findElement(By.xpath(selectField)));
					System.out.println("Element is clickable");
					ControlActions_MobileApp.click(appiumDriver.findElement(By.xpath(selectField)));
					logInfo("Field Value has Selected");
					isVisible = true;
				}
			} catch (NoSuchElementException e) {
				ControlActions_MobileApp.swipe(400, 700, 400, 100);
			} catch (Exception ex) {
				ControlActions_MobileApp.swipe(400, 700, 400, 100);

			}
		} while (!isVisible);
		return isVisible;
	}

	public boolean enterValue(String FieldLabel, String OptionValue, String xpath) {

		String selectField = null;
		selectField = ControlActions_MobileApp.perform_GetElementByXPath2(xpath, "FieldLabel", FieldLabel);

		boolean isVisible = false;
		do {
			try {
				ControlActions_MobileApp.waitForVisibility(appiumDriver.findElement(By.xpath(selectField)), 2);
				if (appiumDriver.findElement(By.xpath(selectField)).isDisplayed()) {
					isVisible = true;
				}
			} catch (NoSuchElementException e) {
				ControlActions_MobileApp.swipe(400, 700, 400, 100);
			} catch (Exception ex) {
				ControlActions_MobileApp.swipe(400, 700, 400, 100);
			}
		} while (!isVisible);
		try {

			ControlActions_MobileApp.waitForVisibility(appiumDriver.findElement(By.xpath(selectField)), 80);
			// ControlActions_MobileApp.ClearText(appiumDriver.findElement(By.xpath(selectField)));
			ControlActions_MobileApp.actionEnterText(appiumDriver.findElement(By.xpath(selectField)), OptionValue);
			ControlActions_MobileApp.performTabAction();

			logInfo("Field Value is Entered");
			return true;

		} catch (Exception ex) {
			logError("Failed to Scroll " + ex.getMessage());
			System.out.println("Failed to Scroll " + ex.getMessage());
			return false;
		}

	}

}