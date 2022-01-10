package com.project.safetychain.mobileapp.pageobjects;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.project.safetychain.api.models.support.TCG_TaskCreation_Wrapper;
import com.project.safetychain.mobileapp.testcases.TCG_SMK_FormSavedFlow;
import com.project.safetychain.api.models.support.Support.FormFieldParams;
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
import com.project.utilities.ControlActions;
import com.project.utilities.ControlActions_MobileApp;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class REG_ResultSummary extends TestBase {

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
	REG_ResultSummary afsaved;
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

	// public static String formName = "Automation_CheckForm_15.10_17.19.19";
	// public static String resourceName = "RInst1_15.10_13.47.04";
	// public static String LocationName = "LInst1_15.10_13.47.04";
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

	@FindBy(xpath = REG_ResultSummaryLocators.RESULTSMRYLINK)
	public WebElement ResultSummaryLink;

	@FindBy(id = REG_ResultSummaryLocators.CLEARBTNRSUM)
	public WebElement clearRSBtn;
	@FindBy(id = REG_ResultSummaryLocators.SUBMITRS)
	public WebElement submitRSBtn;
	@FindBy(xpath = REG_ResultSummaryLocators.FROMDATETXT)
	public WebElement fromDateText;
	@FindBy(xpath = REG_ResultSummaryLocators.TODATETXT)
	public WebElement toDateText;
	@FindBy(xpath = REG_ResultSummaryLocators.FORMTXT)
	public WebElement formTextVal;
	@FindBy(xpath = REG_ResultSummaryLocators.BATCHLETTERTXT)
	public WebElement batchTextVal;

	@FindBy(xpath = REG_ResultSummaryLocators.PLUTXT)
	public WebElement pluTextVal;
	@FindBy(xpath = REG_ResultSummaryLocators.FROMDTTXT)
	public WebElement fromDateTextVal;

	@FindBy(xpath = REG_ResultSummaryLocators.TODTTXT)
	public WebElement toDateTextVal;

	@FindBy(xpath = REG_ResultSummaryLocators.FORMBOX)
	public WebElement formBox;
	@FindBy(xpath = REG_ResultSummaryLocators.BLBOX)
	public WebElement blBox;
	@FindBy(xpath = REG_ResultSummaryLocators.PLUBOX)
	public WebElement pluBox;
	@FindBy(id = REG_ResultSummaryLocators.RESULTFILTERHEADER)
	public WebElement resultHeader;
	@FindBy(id = REG_ResultSummaryLocators.RSBACKBTN)
	public WebElement RSBackBtn;

	@FindBy(xpath = REG_ResultSummaryLocators.FROMDTPICK)
	public WebElement fromDateValPick;
	@FindBy(xpath = REG_ResultSummaryLocators.TODTPICK)
	public WebElement toDateValPick;

	public static final String fresult = "com.safetychain.SCM.M2:id/resultView_num";
	@AndroidFindBy(id = fresult)
	public static final String TEXTVALUE = "//android.widget.TextView[@text='ABC']//parent::android.widget.LinearLayout//following-sibling::android.widget.EditText";
	public WebElement NTxt;
	TCG_TaskCreation_Wrapper taskCreationWrapper = null;
	HashMap<String, String> locResMap = null;
	String formId = null;
	String dateTime = null;
	String dateSelected = null;

	public REG_ResultSummary(AppiumDriver<MobileElement> driver) {
		this.appiumDriver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		mobControlActions = new ControlActions_MobileApp(this.appiumDriver);
	}

	public void ResultSummaryValidation() throws ParseException, InterruptedException {
		HomePage homePage = new HomePage(appiumDriver);
		SavedPage SavedPageObj = new SavedPage(appiumDriver); 
		boolean clickRS = homePage.ClickSubMenu(ResultSummaryLink);
		TestValidation.IsTrue(clickRS, "Clicked on Result summary succesfully", "Failed to click on Result summary");

		ControlActions_MobileApp.waitForVisibility(resultHeader, 30);
		boolean clearSub = false;
		if (clearRSBtn.isDisplayed() && submitRSBtn.isDisplayed()
				&& resultHeader.getText().equalsIgnoreCase("Results Filters")) {
			clearSub = true;
		}
		TestValidation.IsTrue(clearSub, "CLEAR and SUMBIT Button is visible and Header is as Expected",
				"CLEAR and SUMBIT Button is not visible and Header is not as expected ");

		boolean filterVal = false;
		if (formBox.getText().equalsIgnoreCase("") && blBox.getText().equalsIgnoreCase("")
				&& pluBox.getText().equalsIgnoreCase("") && toDateTextVal.getText().equalsIgnoreCase("")
				&& fromDateTextVal.getText().equalsIgnoreCase("")) {
			filterVal = true;
		}
		TestValidation.IsTrue(filterVal, "Filter values are blank as expected", "Filter Values are not blank");

		boolean form = enterFieldValue("Form", "Result Summary Form");
		TestValidation.IsTrue(form, "Form value entered successfully", "Failed to enter Form value");

		boolean BL = enterFieldValue("Batch Letter", "ABCD");
		TestValidation.IsTrue(BL, "Batch Letter value entered successfully", "Failed to enter Batch Letter value");

		boolean PLU = enterFieldValue("PLU Produced", "20");
		TestValidation.IsTrue(PLU, "PLU Produced value entered successfully", "Failed to enter PLU Produced value");

		String Fromdate = ControlActions_MobileApp.AddDaystoToddaysDate(0);
		Boolean SeFromdate = SavedPageObj.clickFieldType(fromDateValPick, Fromdate);
		TestValidation.IsTrue(SeFromdate, "Date Selected successfully", "Failed to select Date value");
		String dateSelected = ControlActions_MobileApp.AddDaystoToddaysDateInGivenFormat("M/d/YY", 0);
		logInfo("Date Field Value = " + dateSelected);

		String Todate = ControlActions_MobileApp.AddDaystoToddaysDate(0);
		Boolean SeTodate = SavedPageObj.clickFieldType(toDateValPick, Todate);
		TestValidation.IsTrue(SeTodate, "Date Selected successfully", "Failed to select Date value");
		String dateSelected1 = ControlActions_MobileApp.AddDaystoToddaysDateInGivenFormat("M/d/YY", 0);
		logInfo("Date Field Value = " + dateSelected1);
		ControlActions_MobileApp.WaitforelementToBeClickable(submitRSBtn);
		submitRSBtn.click();
		Thread.sleep(5000);
		boolean filter = false;
		ControlActions_MobileApp.waitForVisibility(resultHeader, 30);
		ArrayList<WebElement> columns = new ArrayList<>(
				appiumDriver.findElements(By.xpath("//android.widget.LinearLayout//android.widget.TextView")));

		if (resultHeader.getText().equalsIgnoreCase("Results Summary")
				&& columns.get(1).getText().equalsIgnoreCase("Form")
				&& columns.get(2).getText().equalsIgnoreCase("Batch Letter")
				&& columns.get(3).getText().equalsIgnoreCase("PLU Produced")
				&& columns.get(4).getText().equalsIgnoreCase("Submitted By")
				&& columns.get(5).getText().equalsIgnoreCase("Submission Date")) {

			filter = true;

		}
		TestValidation.IsTrue(filter, "Filtered value successfully", "Failed to filter values");

		boolean results = false;
		if (appiumDriver.findElement(By.id("headerLabel")).getText().equalsIgnoreCase("No Results Found")) {

			results = true;
		}

		TestValidation.IsTrue(results, "No results found for applied filter :PASS",
				"Results found for applied filter:FAIL");
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

	public void linkVerification() throws InterruptedException {
		LoginPage loginPage = new LoginPage(appiumDriver);
		mobControlActions = new ControlActions_MobileApp(appiumDriver);
		if (!(loginPage.tenantTxt.getText().equalsIgnoreCase("waynefarms"))) {
			ControlActions_MobileApp.waitForVisibility(loginPage.profileSettingBtn, 100);
			ControlActions_MobileApp.click(loginPage.profileSettingBtn);
			loginPage.updateTenantName("waynefarms");
		}

		boolean login = loginPage.LoginForLinkFunctionality(waynefarms_UserName, waynefarms_Password);
		TestValidation.IsTrue(login, "logged into the application with User " + mobileAppUsername, "Failed to log in");

		// Click on Hamburger menu

		verifyLinkOptionPage verifyLinkOptionPageObj = new verifyLinkOptionPage(appiumDriver);
		Boolean Hamburger = verifyLinkOptionPageObj.Hamburger();
		TestValidation.IsTrue(Hamburger, "Hamburger clicked successfully", "Failed to click Hamburger");

		// Click on link
		Boolean ClickLink = verifyLinkOptionPageObj.Link();
		TestValidation.IsTrue(ClickLink, "Link clicked successfully", "Failed to click link");

		// check for page load

		Boolean sidebar = verifyLinkOptionPageObj.Linkpage();
		TestValidation.IsTrue(sidebar, "Sidebar is visible", "sidebar not visible");

	}
}