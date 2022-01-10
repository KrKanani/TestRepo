package com.project.safetychain.mobileapp.testcases;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.project.safetychain.api.models.support.Support.DPT_FIELD_TYPES;
import com.project.safetychain.api.models.support.Support.DPT_FORM_TYPES;
import com.project.safetychain.api.models.support.Support.FormFieldParams;
import com.project.safetychain.api.models.support.Support.FormParams;
import com.project.safetychain.api.models.support.Support.RESOURCE_TYPES;
import com.project.safetychain.api.models.support.TCG_FormCreation_Wrapper;
import com.project.safetychain.mobileapp.pageobjects.HomePage;
import com.project.safetychain.mobileapp.pageobjects.LoginPage;
import com.project.safetychain.mobileapp.pageobjects.SavedPage;
import com.project.safetychain.mobileapp.pageobjects.UploadImage;
import com.project.safetychain.webapp.pageobjects.CommonPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserRecordsPage;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage;
import com.project.safetychain.webapp.pageobjects.ManageLocationPage;
import com.project.safetychain.webapp.pageobjects.ManageResourcePage;
import com.project.safetychain.webapp.pageobjects.ResourceDesignerPage;
import com.project.safetychain.webapp.testcases.support.FormOperations;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.COLUMNHEADER;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.utilities.ApiUtils;
import com.project.utilities.CommonMethods;
import com.project.utilities.ControlActions;
import com.project.utilities.ControlActions_MobileApp;

import io.appium.java_client.pagefactory.AndroidFindBy;

public class TCG_SMK_FormUploadImage extends TestBase {
	ApiUtils apiUtils = null;
	FormDesignerPage formDesignerPage;
	ControlActions controlActions;
	CommonPage mainPage;
	public static String locationCategoryValue;
	public static String locationInstanceValue1;
	public static String locationInstanceValue2;
	public static String resourceCategoryValue;
	public static String resourceInstanceValue1;
	public static String resourceInstanceValue2;

	com.project.safetychain.webapp.pageobjects.HomePage hp;
	com.project.safetychain.webapp.pageobjects.LoginPage lp;
	ManageResourcePage manageResource;
	ManageLocationPage locations;
	ResourceDesignerPage resourceDesigner;

	public static String formName = "Automation_CheckForm_21.01";
	public static String resourceName = "RInst2_15.10_13.47.04";
	public static String locationName = "LInst1_15.10_13.47.04";
	public static final String fresult = "com.safetychain.SCM.M2:id/resultView_num";
	public static final String Form_btnSubmit = "submitBtn";
	public static String chkFreeTxt, ParaTxt, chkSelectOne, SelectMultiple, Numeric, chkDate, chkTime, chkDateTime;

	String formtype = "Check";
	String modifiedby;
	public static String FormName;

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

			resourceDesigner = new ResourceDesignerPage(driver);
			mainPage = new CommonPage(driver);
			// resourceCategoryValue = CommonMethods.dynamicText("Equip_Cat");

			locationCategoryValue = CommonMethods.dynamicText("LCat");
			locationInstanceValue1 = CommonMethods.dynamicText("LInst1");
			locationInstanceValue2 = CommonMethods.dynamicText("LInst2");
			resourceCategoryValue = CommonMethods.dynamicText("RCat");
			resourceInstanceValue1 = CommonMethods.dynamicText("RInst1");
			resourceInstanceValue2 = CommonMethods.dynamicText("RInst2");
			System.out.println("started execution");

			lp = new com.project.safetychain.webapp.pageobjects.LoginPage(driver);
			controlActions = new ControlActions(driver);
			System.out.println("Driver loaded");

			TCG_FormCreation_Wrapper formCreationWrapper = new TCG_FormCreation_Wrapper();

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
			paraTextField.AllowAttachments = "true";
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

	}

	@Test(description = "Capture images from Camera at field or general form level")
	public void TestCase_36795() throws Exception {
		appiumDriver = launchMobileApp();
		LoginPage loginPage = new LoginPage(appiumDriver);
		UploadImage oUploadImage = new UploadImage(appiumDriver);
		SavedPage SavedPageObj = new SavedPage(appiumDriver);
		HomePage homePage = new HomePage(appiumDriver);
		boolean login = loginPage.Login(mobileAppUsername, mobileAppPassword);
		TestValidation.IsTrue(login, "logged into the application with User " + mobileAppUsername, "Failed to log in");

		boolean searchForm = homePage.SearchForm(FormName);
		TestValidation.IsTrue(searchForm, "Form Searched successfully", "Failed to search Form");
		boolean clickForm = homePage.ClickForm(FormName);
		TestValidation.IsTrue(clickForm, "Clicked on Form Successfully", "Failed to click on Form");
		System.out.println("before SearchResource");
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
		Boolean SedateTime = SavedPageObj.selectDateTimeFieldValues(homePage.dateTimePicker, date);
		String dateTimeSel = homePage.dateTimePicker.getText();
		logInfo("Date&Time Field Value = " + dateTimeSel);
		TestValidation.IsTrue(SedateTime, "DateTime value selected successfully", "Failed to Select DateTime value");

		Boolean SeMultiple = SavedPageObj.selectOneOrMultipleFieldValues("MutiSelect", "10");
		TestValidation.IsTrue(SeMultiple, "Numeric Field value entered successfully", "Failed to enter field value");
		Boolean SeMultiple1 = SavedPageObj.selectOneOrMultipleFieldValues("MutiSelect", "30");
		TestValidation.IsTrue(SeMultiple1, "Numeric Field value entered successfully", "Failed to enter field value");
		oUploadImage.CameraClick();
		oUploadImage.OpenCamera();
		oUploadImage.CameraShutter();
		oUploadImage.CloseCamera();
		oUploadImage.verifySelectedImageThroughGallery();

		boolean submitForm = homePage.submitForm();
		TestValidation.IsTrue(submitForm, "Form Submitted Successfully", "Failed to Submit Form from Forms SubTab");
		boolean clickSubmissionsMenu = homePage.ClickSubMenu(homePage.submissionsSubMenu);
		TestValidation.IsTrue(clickSubmissionsMenu, "Clicked on Submissions submenu Successfully",
				"Failed to click Submissions subMenu");
		ControlActions_MobileApp.click(SavedPageObj.sortBtn);
		homePage.SearchForm(FormName);
		searchForm = homePage.checkFormStatusFromSubmissionsSubMenu(FormName);
		TestValidation.IsTrue(searchForm, "Form Searched Successfully", "Failed to Search Form from Forms subMenu");
		boolean formStatus = SavedPageObj.clickFormFromSubmissionsScreen(FormName);
		TestValidation.IsTrue(formStatus, "Verified Form Status Successfully",
				"Failed to verify Form Status on Submissions");
		oUploadImage.verifySelectedImageThroughGallery();

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
		FormOperations fo = new FormOperations(driver);
		// boolean fieldImage = fo.verifySingleAttachmentForField("Paragraph",
		// "0.png");
		// TestValidation.IsTrue(fieldImage, "Attachment from mobile for field
		// paragraph is present at bottom on Web",
		// "Attachment from mobile for field paragraph is not present at bottom
		// on Web");
		boolean AttachmentMob = (FileActual.equals("0.png") || FileActual.equals("1.png"));
		TestValidation.IsTrue(AttachmentMob, "Attachment from mobile is present at bottom on Web",
				"Attachment  from mobile is not present at bottom on Web");

	}

	@Test(description = " Verify after Upload images from gallery at field or general form level")
	public void TestCase_36796() throws Exception {
		appiumDriver = launchMobileApp();
		LoginPage loginPage = new LoginPage(appiumDriver);
		HomePage homePage = new HomePage(appiumDriver);
		boolean login = loginPage.Login(mobileAppUsername, mobileAppPassword);
		TestValidation.IsTrue(login, "logged into the application with User " + mobileAppUsername, "Failed to log in");

		UploadImage oUploadImage = new UploadImage(appiumDriver);
		SavedPage SavedPageObj = new SavedPage(appiumDriver);
		homePage.SearchForm(FormName);
		homePage.ClickForm(FormName);
		System.out.println("before SearchResource");
		Boolean searchLocation = SavedPageObj.SearchResource(locationInstanceValue1);
		TestValidation.IsTrue(searchLocation, "Location Searched successfully", "Failed to search Location");
		Boolean searchResource = SavedPageObj.SearchResource(resourceInstanceValue1);
		TestValidation.IsTrue(searchResource, "Resource Searched successfully", "Failed to Resource Form");

		Boolean txtNumeric = SavedPageObj.enterFieldValue(SavedPageObj.NumericTxt, "8");
		TestValidation.IsTrue(txtNumeric, "Numeric Field value entered successfully", "Failed to enter field value");

		Boolean txtPara = SavedPageObj.enterFieldValueforAttachment(SavedPageObj.paragraphTxt, "Para");
		TestValidation.IsTrue(txtPara, "Numeric Field value entered successfully", "Failed to enter field value");
		Thread.sleep(4000);
		oUploadImage.CameraClick();
		Thread.sleep(3000);
		oUploadImage.OpenGallery();
		Thread.sleep(3000);
		oUploadImage.SelectImage();
		oUploadImage.CloseGallery();
		ControlActions_MobileApp.performTabAction();

		Boolean txtFT = SavedPageObj.enterFieldValue(SavedPageObj.freeText, "FreeText");
		TestValidation.IsTrue(txtFT, "Numeric Field value entered successfully", "Failed to enter field value");

		Boolean selectOne = SavedPageObj.selectOneOrMultipleFieldValues("SelectOne", "GOOD");
		TestValidation.IsTrue(selectOne, "Numeric Field value entered successfully", "Failed to enter field value");

		String date = ControlActions_MobileApp.AddDaystoToddaysDate(0);
		Boolean Sedate = SavedPageObj.clickFieldType(homePage.datePicker, date);
		TestValidation.IsTrue(Sedate, "Date Selected successfully", "Failed to select Date value");
		Boolean SedateTime = SavedPageObj.selectDateTimeFieldValues(homePage.dateTimePicker, date);
		String dateTimeSel = homePage.dateTimePicker.getText();
		logInfo("Date&Time Field Value = " + dateTimeSel);
		TestValidation.IsTrue(SedateTime, "DateTime value selected successfully", "Failed to Select DateTime value");
		ControlActions_MobileApp.swipeScreen("UP");
		Boolean SeMultiple = SavedPageObj.selectOneOrMultipleFieldValues("MutiSelect", "10");
		TestValidation.IsTrue(SeMultiple, "Numeric Field value entered successfully", "Failed to enter field value");

		Boolean SeMultiple1 = SavedPageObj.selectOneOrMultipleFieldValues("MutiSelect", "30");
		TestValidation.IsTrue(SeMultiple1, "Numeric Field value entered successfully", "Failed to enter field value");

		boolean camIcnClick = oUploadImage.CameraClick();
		Thread.sleep(2000);
		TestValidation.IsTrue(camIcnClick, "Camera Icon from Forms page clicked successfully",
				"Failed to click camera icon on Forms page");

		boolean openGallery = oUploadImage.OpenGallery();
		Thread.sleep(4000);
		TestValidation.IsTrue(openGallery, "Gallery opened successfully", "Failed to open gallery folder on Mobile");
		boolean selImage = oUploadImage.SelectImage();
		TestValidation.IsTrue(selImage, "Camera Icon from Forms page clicked successfully",
				"Failed to click camera icon on Forms page");
		boolean closeGallery = oUploadImage.CloseGallery();
		TestValidation.IsTrue(closeGallery, "Gallery closed successfully", "Failed to close Gallery");
		ControlActions_MobileApp.performTabAction();
		oUploadImage.verifySelectedImageThroughGallery();

		homePage.submitForm();
		homePage.ClickSubMenu(homePage.submissionsSubMenu);
		ControlActions_MobileApp.click(SavedPageObj.sortBtn);
		homePage.SearchForm(FormName);
		homePage.checkFormStatusFromSubmissionsSubMenu(FormName);

		SavedPageObj.clickFormFromSubmissionsScreen(FormName);
		oUploadImage.verifySelectedImageThroughGallery();

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

		FSQABrowserRecordsPage fo = new FSQABrowserRecordsPage(driver);
		boolean fieldImage = fo.verifyAttachmentFileNameForField("Paragraph", "0.png");
		TestValidation.IsTrue(fieldImage, "Attachment from mobile for field paragraph is present at bottom on Web",
				"Attachment  from mobile for field paragraph is not present at bottom on Web");
		boolean AttachmentMob = (FileActual.equals("0.png") || FileActual.equals("1.png"));
		TestValidation.IsTrue(AttachmentMob, "Attachment from mobile is present at bottom on Web",
				"Attachment  from mobile is not present at bottom on Web");

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
