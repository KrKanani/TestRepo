package com.project.safetychain.mobileapp.testcases;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.project.safetychain.api.models.support.Support.ChartBuilder;
import com.project.safetychain.api.models.support.Support.ChartResources;
import com.project.safetychain.api.models.support.Support.Chart_Rules;
import com.project.safetychain.api.models.support.Support.Chart_Template_TYPES;
import com.project.safetychain.api.models.support.Support.DPT_FIELD_TYPES;
import com.project.safetychain.api.models.support.Support.DPT_FORM_TYPES;
import com.project.safetychain.api.models.support.Support.DefaultChart;
import com.project.safetychain.api.models.support.Support.FormFieldParams;
import com.project.safetychain.api.models.support.Support.FormParams;
import com.project.safetychain.api.models.support.Support.RESOURCE_TYPES;
import com.project.safetychain.api.models.support.TCG_ChartBuilder_Wrapper;
import com.project.safetychain.api.models.support.TCG_FormCreation_Wrapper;
import com.project.safetychain.mobileapp.pageobjects.ChartsPage;
import com.project.safetychain.mobileapp.pageobjects.HomePage;
import com.project.safetychain.mobileapp.pageobjects.LoginPage;
import com.project.safetychain.mobileapp.pageobjects.SavedPage;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.utilities.ApiUtils;
import com.project.utilities.CommonMethods;
import com.project.utilities.ControlActions_MobileApp;

public class TCG_SMK_ChartsBuilderFlow extends TestBase {
	ApiUtils apiUtils = null;
	public static String locationCategoryValue;
	public static String locationInstanceValue1;
	public static String locationInstanceValue2;
	public static String resourceCategoryValue;
	public static String resourceInstanceValue1;
	public static String resourceInstanceValue2;
	public static String chkFreeTxt, ParaTxt, chkSelectOne, SelectMultiple, Numeric, chkDate, chkTime, chkDateTime;

	public static String formName = "Mob_ChartFlow";
	public static String ResourceName = "RInst1_18.02_15.56.52";
	public static String LocationName = "LInst1_18.02_15.56.52";
	String formtype = "Check";
	String modifiedby;
	public static String FormName;
	public static String ChartName;
	public static String ChartLinkName;

	@BeforeClass(alwaysRun = true)
	public void groupInit() {
		try {
			FormName = CommonMethods.dynamicText("API_Form");
			ChartName = CommonMethods.dynamicText("API_Chart");
			ChartLinkName = CommonMethods.dynamicText("API_ChartAsso");

			TCG_FormCreation_Wrapper formCreationWrapper = new TCG_FormCreation_Wrapper();
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
			numericField.Name = "Numeric";

			FormFieldParams numericField1 = new FormFieldParams();
			numericField1.DPTFieldType = DPT_FIELD_TYPES.NUMERIC;
			numericField1.Name = "Numeric1";

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
			fp.formElements = Arrays.asList(numericField, numericField1);
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
			String ResInstId1 = locResMap.get(resourceInstanceValue1);
			String ResInstId2 = locResMap.get(resourceInstanceValue2);
			// Form Creation and Validation call
			formCreationWrapper.API_Wrapper_Forms(fp);

			// Chart Default

			DefaultChart defaultChart = new DefaultChart();

			defaultChart.Variation = "10";
			defaultChart.Mean = "20";
			// chart Resources

			ChartResources chartResources1 = new ChartResources();
			chartResources1.ResourceName = resourceInstanceValue1;
			chartResources1.ResourceId = ResInstId1;
			chartResources1.Mean = "10";
			chartResources1.Variation = "20";

			ChartResources chartResources2 = new ChartResources();
			chartResources2.ResourceName = resourceInstanceValue2;
			chartResources2.ResourceId = ResInstId2;
			chartResources2.Mean = "20";
			chartResources2.Variation = "30";

			ChartBuilder chartBuilder = new ChartBuilder();
			chartBuilder.chartConfList = Arrays.asList(Chart_Rules.Rule_2, Chart_Rules.Rule_1);
			chartBuilder.Name = ChartName;
			chartBuilder.chartLinkingName = ChartLinkName;
			chartBuilder.formId = formId;
			chartBuilder.formName = FormName;
			chartBuilder.locationId = locInstId;
			chartBuilder.chartTemplateType = Chart_Template_TYPES.XBarR;
			chartBuilder.chartResourceList = Arrays.asList(chartResources1, chartResources2);
			chartBuilder.ChartFieldsList = Arrays.asList("Numeric1", "Numeric");
			chartBuilder.defaultChart = defaultChart;

			TCG_ChartBuilder_Wrapper chartBuilderWrapper = new TCG_ChartBuilder_Wrapper();

			chartBuilderWrapper.create_Chart_Wrapper(chartBuilder);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test(description = "chart Builder Flow", priority = 0)
	public void TestCase_36825() throws Exception {
		appiumDriver = launchMobileApp();

		SavedPage SavedPageObj = new SavedPage(appiumDriver);
		HomePage homePage = new HomePage(appiumDriver);
		ChartsPage chartsPage = new ChartsPage(appiumDriver);

		LoginPage loginPage = new LoginPage(appiumDriver);
		if (mobileApp_Tenant.equals("test1.stage")) {
			boolean SSOLogin = loginPage.SSO_Login(mobileAppUsername, mobileAppPassword);
			TestValidation.IsTrue(SSOLogin, "logged into the application with User " + mobileAppUsername,
					"Failed to log in");

		} else {
			boolean login = loginPage.Login(mobileAppUsername, mobileAppPassword);
			TestValidation.IsTrue(login, "logged into the application with User " + mobileAppUsername,
					"Failed to log in");

		}
		Boolean searchFrms = homePage.SearchForm(FormName);

		TestValidation.IsTrue(searchFrms, "Form Searched successfully", "Failed to search Form");

		Boolean clickFrms = homePage.ClickForm(FormName);
		TestValidation.IsTrue(clickFrms, "Form Clicked successfully", "Failed to click Form");
		Boolean searchLocation = SavedPageObj.SearchResource(locationInstanceValue1);
		TestValidation.IsTrue(searchLocation, "Location Searched successfully", "Failed to search Location");
		Boolean searchResource = SavedPageObj.SearchResource(resourceInstanceValue1);
		TestValidation.IsTrue(searchResource, "Resource Searched successfully", "Failed to Resource Form");
		for (WebElement Numeric : SavedPageObj.ListNumericTxt) {
			Boolean txtNumeric = SavedPageObj.enterFieldValue(Numeric, "8");
			TestValidation.IsTrue(txtNumeric, "Numeric Field value entered successfully",
					"Failed to enter field value");

		}

		Boolean submitForm = SavedPageObj.submitForm();
		TestValidation.IsTrue(submitForm, "Form Submitted Successfully", "Failed to Submit Form");

		Boolean isVerifyXchart = chartsPage.VerifyChartDetails(FormName, ChartLinkName, "Mean (oz)");
		TestValidation.IsTrue(isVerifyXchart, "chart verified", "Failed to verify Chart");

		Boolean clickCloseBtn = chartsPage.clikNxtButton(chartsPage.chartCloseBtn);
		TestValidation.IsTrue(clickCloseBtn, "Close button clicked on Charts Page",
				"Failed to click Close button clicked on Charts Page");

		Boolean clickSubmissions = homePage.ClickSubMenu(homePage.submissionsSubMenu);
		TestValidation.IsTrue(clickSubmissions, "Clicked on submissions subMenu Successfully",
				"Failed to click Submissions subMenu");

		ControlActions_MobileApp.waitForVisibility(SavedPageObj.sortBtn);
		SavedPageObj.sortBtn.click();
		Boolean searchForm = homePage.SearchForm(FormName);
		TestValidation.IsTrue(searchForm, "form Searched Successfully",
				"Failed to search form from submissions screen");
		Boolean chkStatus = SavedPageObj.checkFormStatusFromSubmissionsSubMenu(FormName, "RECEIVED");
		TestValidation.IsTrue(chkStatus, "Verified form Status Successfully", "Failed to verify Form status");
		appiumDriver.hideKeyboard();
		Boolean chartStatus = chartsPage.checkFormIconFromSubmissionsSubMenu(FormName);
		TestValidation.IsTrue(chartStatus, "Verified chart Icon Successfully", "Failed to verify chart Icon");
		SavedPageObj.clickFormFromSubmissionsScreen(FormName);

		Boolean clickChartBtn = chartsPage.clikNxtButton(chartsPage.chartBtn);
		TestValidation.IsTrue(clickChartBtn, "Chart button clicked on submissions Page",
				"Failed to click chart button on Submissions Page");

		isVerifyXchart = chartsPage.VerifyChartDetails(FormName, ChartLinkName, "Range");
		TestValidation.IsTrue(isVerifyXchart, "chart verified", "Failed to verify Chart");

		clickCloseBtn = chartsPage.clikNxtButton(chartsPage.chartCloseBtn);
		TestValidation.IsTrue(clickCloseBtn, "Close button clicked on Charts Page",
				"Failed to click Close button clicked on Charts Page");

	}

	@AfterMethod(alwaysRun = true)
	public void closeAppium() throws InterruptedException {

		appiumDriver.quit();
	}

	@AfterClass(alwaysRun = true)
	public void closeBrowser() throws InterruptedException {

	}

}
