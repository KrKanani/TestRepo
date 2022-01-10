package com.project.safetychain.mobileapp.pageobjects;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.project.safetychain.api.models.support.TCG_ChartBuilder_Wrapper;
import com.project.safetychain.api.models.support.TCG_FormCreation_Wrapper;
import com.project.safetychain.api.models.support.Support.ChartBuilder;
import com.project.safetychain.api.models.support.Support.ChartResources;
import com.project.safetychain.api.models.support.Support.Chart_Rules;
import com.project.safetychain.api.models.support.Support.Chart_Template_TYPES;
import com.project.safetychain.api.models.support.Support.DPT_FIELD_TYPES;
import com.project.safetychain.api.models.support.Support.DPT_FORM_TYPES;
import com.project.safetychain.api.models.support.Support.DefaultChart;
import com.project.safetychain.api.models.support.Support.FormFieldParams;
import com.project.safetychain.api.models.support.Support.FormParams;
import com.project.safetychain.api.models.support.Support.IDENTIFIER_TYPES;
import com.project.safetychain.api.models.support.Support.RESOURCE_TYPES;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.utilities.ApiUtils;
import com.project.utilities.CommonMethods;
import com.project.utilities.ControlActions_MobileApp;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class REG_ChartsPage extends TestBase {
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
	public static String identifierFieldName = "Identifier1";
	public static String filterIdValue = "filter";
	public static String FormName;
	public static String ChartName;
	public static String ChartLinkName;

	public class HomepageLocators {

		public final String formName = null;
		public final String FORM_NAME = null;

		public void main(String[] args) {
		}

	}

	AppiumDriver<MobileElement> appiumDriver;
	WebDriverWait wait;
	ControlActions_MobileApp mobControlActions;
	CommonMethods commonmethods;

	public REG_ChartsPage(AppiumDriver<MobileElement> driver) {
		this.appiumDriver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		mobControlActions = new ControlActions_MobileApp(this.appiumDriver);
		commonmethods = new CommonMethods(this.appiumDriver);
	}

	@FindBy(id = ChartsPageLocators.FORM_NM)
	public WebElement chartHeader;

	@FindBy(id = ChartsPageLocators.CHART_NM)
	public WebElement chartName;

	@FindBy(xpath = ChartsPageLocators.NEXT_BTN)
	public WebElement chartNxtBtn;

	@FindBy(xpath = ChartsPageLocators.PREVIOUS_BTN)
	public WebElement chartPreBtn;

	@FindBy(xpath = ChartsPageLocators.CLOSE_BTN)
	public WebElement chartCloseBtn;

	@FindBy(xpath = ChartsPageLocators.CHART_YCORDINATE)
	public WebElement chartYCordinate;

	@FindBy(id = ChartsPageLocators.FORM_BCK_BTN)
	public WebElement formBackBtn;

	@FindBy(xpath = ChartsPageLocators.CHART_ICN)
	public WebElement chartIcn;

	@FindBy(xpath = ChartsPageLocators.CHART_BTN)
	public WebElement chartBtn;

	@FindBy(id = ChartsPageLocators.POINTDETAILSICON)
	public WebElement pointsDetailGreyIcon;
	@FindBy(id = ChartsPageLocators.POINTMEAN)
	public WebElement meanOnGreyPoint;
	@FindBy(id = ChartsPageLocators.HEADERMEANTEXT)
	public WebElement headerMeanTxt;
	@FindBy(id = ChartsPageLocators.DETAILSVIEWBTN)
	public WebElement detailsViewBtn;
	@FindBy(id = ChartsPageLocators.CANECELDETAILSVIEWBTN)
	public WebElement cancelDetailsViewBtn;
	@FindBy(id = ChartsPageLocators.RULE1)
	public WebElement rule1;
	@FindBy(id = ChartsPageLocators.RULE2)
	public WebElement rule2;
	@FindBy(id = ChartsPageLocators.RULE3)
	public WebElement rule3;
	@FindBy(id = ChartsPageLocators.RULE4)
	public WebElement rule4;
	@FindBy(id = ChartsPageLocators.RULE5)
	public WebElement rule5;
	@FindBy(id = ChartsPageLocators.RULE6)
	public WebElement rule6;
	@FindBy(id = ChartsPageLocators.RULE7)
	public WebElement rule7;
	@FindBy(id = ChartsPageLocators.RULE8)
	public WebElement rule8;
	@FindBy(id = ChartsPageLocators.RULE9)
	public WebElement rule9;
	@FindBy(id = ChartsPageLocators.VR1)
	public WebElement vr1;
	@FindBy(id = ChartsPageLocators.VR4)
	public WebElement vr4;
	@FindBy(id = ChartsPageLocators.VR5)
	public WebElement vr5;
	@FindBy(id = ChartsPageLocators.VR8)
	public WebElement vr8;
	@FindBy(id = ChartsPageLocators.ADDCOMMENTS)
	public WebElement addComments;
	@FindBy(id = ChartsPageLocators.RESULTS)
	public WebElement results;
	@FindBy(id = ChartsPageLocators.MEAN)
	public WebElement mean;
	@FindBy(id = ChartsPageLocators.STD)
	public WebElement stdVar;
	@FindBy(id = ChartsPageLocators.SAMPLESET)
	public WebElement sampleSet;
	@FindBy(id = ChartsPageLocators.CL)
	public WebElement clVal;
	@FindBy(id = ChartsPageLocators.LCL)
	public WebElement lclVal;
	@FindBy(id = ChartsPageLocators.UCL)
	public WebElement uclVal;

	@FindBy(id = ChartsPageLocators.NUMERICVALTXT)
	public WebElement numValTxt;
	@FindBy(id = ChartsPageLocators.CLOSEBTN)
	public WebElement closeBtn;
	@FindBy(id = ChartsPageLocators.PREVIOUSBTN)
	public WebElement prevBtn;

	@FindBy(id = ChartsPageLocators.NEXTBTN)
	public WebElement nextBtn;
	@FindBy(id = ChartsPageLocators.DATETIMETXT)
	public WebElement dateTimeTxt;

	@FindBy(id = ChartsPageLocators.CHARTICON)
	public WebElement chartIcon;

	@FindBy(id = ChartsPageLocators.POINTDETAILSICON)
	public List<WebElement> pointsDetailGreyIconList;
	
	@FindBy(id = ChartsPageLocators.RUNNING_SUM)
	public WebElement runningSum;
	
	public void actionEnter() {
		// TODO Auto-generated method stub

	}

	public String XBarSChartCreation() {
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
			chartBuilder.chartTemplateType = Chart_Template_TYPES.XBarS;
			chartBuilder.chartResourceList = Arrays.asList(chartResources1, chartResources2);
			chartBuilder.ChartFieldsList = Arrays.asList("Numeric1", "Numeric");
			chartBuilder.defaultChart = defaultChart;

			TCG_ChartBuilder_Wrapper chartBuilderWrapper = new TCG_ChartBuilder_Wrapper();

			chartBuilderWrapper.create_Chart_Wrapper(chartBuilder);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return FormName;
	}

	public String NPChartCreation() {
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
			fp.formElements = Arrays.asList(numericField);
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
			defaultChart.SampleSize = "2";
			// chart Resources

			ChartResources chartResources1 = new ChartResources();
			chartResources1.ResourceName = resourceInstanceValue1;
			chartResources1.ResourceId = ResInstId1;
			chartResources1.Mean = "10";
			chartResources1.Variation = "20";
			chartResources1.SampleSize = "2";

			ChartResources chartResources2 = new ChartResources();
			chartResources2.ResourceName = resourceInstanceValue2;
			chartResources2.ResourceId = ResInstId2;
			chartResources2.Mean = "20";
			chartResources2.Variation = "30";
			chartResources2.SampleSize = "2";

			ChartBuilder chartBuilder = new ChartBuilder();
			chartBuilder.chartConfList = Arrays.asList(Chart_Rules.Rule_2, Chart_Rules.Rule_1);
			chartBuilder.Name = ChartName;
			chartBuilder.chartLinkingName = ChartLinkName;
			chartBuilder.formId = formId;
			chartBuilder.formName = FormName;
			chartBuilder.locationId = locInstId;
			chartBuilder.chartTemplateType = Chart_Template_TYPES.NpChart;
			chartBuilder.chartResourceList = Arrays.asList(chartResources1, chartResources2);
			chartBuilder.ChartFieldsList = Arrays.asList("Numeric");
			chartBuilder.defaultChart = defaultChart;

			TCG_ChartBuilder_Wrapper chartBuilderWrapper = new TCG_ChartBuilder_Wrapper();

			chartBuilderWrapper.create_Chart_Wrapper(chartBuilder);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return FormName;
	}

	public String PChartCreation() {
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
			fp.formElements = Arrays.asList(numericField);
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
			chartBuilder.chartTemplateType = Chart_Template_TYPES.PChart;
			chartBuilder.chartResourceList = Arrays.asList(chartResources1, chartResources2);
			chartBuilder.ChartFieldsList = Arrays.asList("Numeric1", "Numeric");
			chartBuilder.defaultChart = defaultChart;

			TCG_ChartBuilder_Wrapper chartBuilderWrapper = new TCG_ChartBuilder_Wrapper();

			chartBuilderWrapper.create_Chart_Wrapper(chartBuilder);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return FormName;
	}

	public String CChartCreation() {
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
			fp.formElements = Arrays.asList(numericField);
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
			chartBuilder.chartTemplateType = Chart_Template_TYPES.CChart;
			chartBuilder.chartResourceList = Arrays.asList(chartResources1, chartResources2);
			chartBuilder.ChartFieldsList = Arrays.asList("Numeric1", "Numeric");
			chartBuilder.defaultChart = defaultChart;

			TCG_ChartBuilder_Wrapper chartBuilderWrapper = new TCG_ChartBuilder_Wrapper();

			chartBuilderWrapper.create_Chart_Wrapper(chartBuilder);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return FormName;
	}

	public void PChartVerification() {
		SavedPage SavedPageObj = new SavedPage(appiumDriver);
		HomePage homePage = new HomePage(appiumDriver);
		ChartsPage chartsPage = new ChartsPage(appiumDriver);

		String FormName = PChartCreation();
		ControlActions_MobileApp.swipeScreen("DOWN");
		Boolean searchFrms = homePage.SearchForm(FormName);

		TestValidation.IsTrue(searchFrms, "Form Searched successfully", "Failed to search Form");

		Boolean clickFrms = homePage.ClickForm(FormName);
		TestValidation.IsTrue(clickFrms, "Form Clicked successfully", "Failed to click Form");
		Boolean searchLocation = SavedPageObj.SearchResource(locationInstanceValue1);
		TestValidation.IsTrue(searchLocation, "Location Searched successfully", "Failed to search Location");
		Boolean searchResource = SavedPageObj.SearchResource(resourceInstanceValue1);
		TestValidation.IsTrue(searchResource, "Resource Searched successfully", "Failed to Resource Form");
		for (WebElement Numeric : SavedPageObj.ListNumericTxt) {
			Boolean txtNumeric = SavedPageObj.enterFieldValue(Numeric, "10");
			TestValidation.IsTrue(txtNumeric, "Numeric Field value entered successfully",
					"Failed to enter field value");

		}

		Boolean submitForm = SavedPageObj.submitForm();
		TestValidation.IsTrue(submitForm, "Form Submitted Successfully", "Failed to Submit Form");

		Boolean isVerifyXchart = chartsPage.VerifyChartDetails(FormName, ChartLinkName, "Defects");
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

		Boolean clickChartBtn = VerifyChartDetailsForXBarS(ChartLinkName, "0.00", FormName, "Defects", "P");
		TestValidation.IsTrue(clickChartBtn, "Verified chart details succesfully for P Chart",
				"Failed to verify chart details for P Chart");

	}

	public void CChartVerification() {
		SavedPage SavedPageObj = new SavedPage(appiumDriver);
		HomePage homePage = new HomePage(appiumDriver);
		ChartsPage chartsPage = new ChartsPage(appiumDriver);
		String FormName = CChartCreation();
		ControlActions_MobileApp.swipeScreen("DOWN");
		Boolean searchFrms = homePage.SearchForm(FormName);

		TestValidation.IsTrue(searchFrms, "Form Searched successfully", "Failed to search Form");

		Boolean clickFrms = homePage.ClickForm(FormName);
		TestValidation.IsTrue(clickFrms, "Form Clicked successfully", "Failed to click Form");
		Boolean searchLocation = SavedPageObj.SearchResource(locationInstanceValue1);
		TestValidation.IsTrue(searchLocation, "Location Searched successfully", "Failed to search Location");
		Boolean searchResource = SavedPageObj.SearchResource(resourceInstanceValue1);
		TestValidation.IsTrue(searchResource, "Resource Searched successfully", "Failed to Resource Form");
		for (WebElement Numeric : SavedPageObj.ListNumericTxt) {
			Boolean txtNumeric = SavedPageObj.enterFieldValue(Numeric, "10");
			TestValidation.IsTrue(txtNumeric, "Numeric Field value entered successfully",
					"Failed to enter field value");

		}

		Boolean submitForm = SavedPageObj.submitForm();
		TestValidation.IsTrue(submitForm, "Form Submitted Successfully", "Failed to Submit Form");

		Boolean isVerifyXchart = chartsPage.VerifyChartDetails(FormName, ChartLinkName, "Defects");
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

		Boolean clickChartBtn = VerifyChartDetailsForXBarS(ChartLinkName, "10.00", FormName, "Defects", "C");
		TestValidation.IsTrue(clickChartBtn, "Verified chart details succesfully for C Chart",
				"Failed to verify chart details for C Chart");

	}

	public void XBarSChartVerification() {
		SavedPage SavedPageObj = new SavedPage(appiumDriver);
		HomePage homePage = new HomePage(appiumDriver);
		ChartsPage chartsPage = new ChartsPage(appiumDriver);
		String FormName = XBarSChartCreation();
		ControlActions_MobileApp.swipeScreen("DOWN");
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

		Boolean clickChartBtn = VerifyChartDetailsForXBarS(ChartLinkName, "8.00", FormName, "Mean (oz)", "XBarS");
		TestValidation.IsTrue(clickChartBtn, "Verified chart details succesfully for XBarS",
				"Failed to verify chart details for XBarS");
	}

	public void NPChartVerification() {
		String FormName = NPChartCreation();
		fillFormNVerifyChartDetails(FormName, "20", "Defects");
		verifyChartDetailsInSubmission(FormName, "20.00", "Defects", "NPChart");
	}

	public boolean VerifyChartDetailsForXBarS(String ChartName, String ExpectedMean, String formName, String YCordinate,
			String ChartType) {
		boolean flag = false;
		boolean chartPage = false;
		try {
			ControlActions_MobileApp.waitForVisibility(chartIcon, 120);
			ControlActions_MobileApp.WaitforelementToBeClickable(chartIcon);
			ControlActions_MobileApp.actionClick(chartIcon);
			logInfo("Clicked on Chart");
			ControlActions_MobileApp.waitForVisibility(chartHeader, 120);

			WebElement ChartNm = null;
			ChartNm = ControlActions_MobileApp.perform_GetElementByXPath(ChartsPageLocators.CHART_NM, "CHARTNAME",
					ChartName);
			ControlActions_MobileApp.waitForVisibility(ChartNm, 10000);
			if (ChartNm.getText().equals(ChartName)) {
				logInfo("chart is Present " + ChartNm.getText());
				WebElement formCheck = null;
				formCheck = ControlActions_MobileApp.perform_GetElementByXPath(ChartsPageLocators.CHART_YCORDINATE,
						"VALUE", YCordinate);
				ControlActions_MobileApp.waitForVisibility(formCheck, 10000);

				if (formCheck.getText().equals(YCordinate)) {
					logInfo("Y Co-ordinate has verified" + YCordinate);
					chartPage = true;
				}

				TestValidation.IsTrue(chartPage, "Verified chart Type and formname succesfully for XBarS",
						"Failed to verify chart Type and formname for XBarS");
			}

			if (chartHeader.getText().equals(formName)) {
				logInfo("chart template Name Verified " + chartHeader.getText());
				ControlActions_MobileApp.waitForVisibility(pointsDetailGreyIcon, 120);
				logInfo("points icon is displayed");
				ControlActions_MobileApp.waitForVisibility(meanOnGreyPoint, 120);
				logInfo("mean on points icon is displayed");
				System.out.println(meanOnGreyPoint.getText());
				if (meanOnGreyPoint.getText().equalsIgnoreCase(ExpectedMean) && closeBtn.isDisplayed()
						&& closeBtn.getText().equalsIgnoreCase("CLOSE") && prevBtn.isDisplayed()
						&& prevBtn.getText().equalsIgnoreCase("PREVIOUS") && nextBtn.isDisplayed()
						&& nextBtn.getText().equalsIgnoreCase("NEXT")

				) {
					logInfo("Chart image page verified succesfully");
					ControlActions_MobileApp.WaitforelementToBeClickable(pointsDetailGreyIcon);
					ControlActions_MobileApp.actionClick(pointsDetailGreyIcon);
					pointsDetailGreyIcon.click();
					ControlActions_MobileApp.waitForVisibility(addComments, 30);
					logInfo("Clicked on points Detail Icon Succesfully");
					if (detailsViewBtn.isDisplayed() && cancelDetailsViewBtn.isDisplayed()
					// && headerMeanTxt.getText().equalsIgnoreCase("8.0")
							&& rule1.isDisplayed() && rule2.isDisplayed() && rule3.isDisplayed() && rule4.isDisplayed()
							&& rule5.isDisplayed() && rule6.isDisplayed() && rule7.isDisplayed() && rule8.isDisplayed()
							&& rule9.isDisplayed() && vr1.isDisplayed() && vr4.isDisplayed() && vr5.isDisplayed()
							&& vr8.isDisplayed()) {

						ControlActions_MobileApp.WaitforelementToBeClickable(detailsViewBtn);
						detailsViewBtn.click();
						if (ChartType.equalsIgnoreCase("XBarS") && mean.isDisplayed() && stdVar.isDisplayed()
								&& sampleSet.isDisplayed() && clVal.isDisplayed() && lclVal.isDisplayed()
								&& uclVal.isDisplayed() && numValTxt.isDisplayed() && closeBtn.isDisplayed()
								&& prevBtn.isDisplayed() && nextBtn.isDisplayed() && dateTimeTxt.isDisplayed()) {

							ControlActions_MobileApp.WaitforelementToBeClickable(closeBtn);
							closeBtn.click();
							if (ControlActions_MobileApp.isElementDisplayed(chartIcn)) {
								flag = true;
							}
						}
						if ((ChartType.equalsIgnoreCase("P") || ChartType.equalsIgnoreCase("C")) && mean.isDisplayed()
								&& sampleSet.isDisplayed() && clVal.isDisplayed() && lclVal.isDisplayed()
								&& uclVal.isDisplayed() && numValTxt.isDisplayed() && closeBtn.isDisplayed()
								&& prevBtn.isDisplayed() && nextBtn.isDisplayed() && dateTimeTxt.isDisplayed()) {

							ControlActions_MobileApp.WaitforelementToBeClickable(closeBtn);
							closeBtn.click();
							if (ControlActions_MobileApp.isElementDisplayed(chartIcn)) {
								flag = true;
							}
						}

						if (ChartType.equalsIgnoreCase("Sum") || ChartType.equalsIgnoreCase("Average")
								&& mean.isDisplayed() && sampleSet.isDisplayed() && clVal.isDisplayed()
								&& lclVal.isDisplayed() && numValTxt.isDisplayed() && closeBtn.isDisplayed()
								&& prevBtn.isDisplayed() && nextBtn.isDisplayed() && dateTimeTxt.isDisplayed()) {
							ControlActions_MobileApp.WaitforelementToBeClickable(closeBtn);
							closeBtn.click();
							if (ControlActions_MobileApp.isElementDisplayed(chartIcn)) {
								flag = true;
							}
						}
					}
				}
			}

		} catch (

		Exception e) {
			logError("Failed to click -Task - " + e.getMessage());
			flag = false;
		}
		return flag;

	}

	public boolean VerifyChartDetails(String formName, String ChartName, String YCordinate) {
		try {
			ControlActions_MobileApp.waitForVisibility(chartHeader, 1000);

			if (chartHeader.getText().equals(formName)) {
				logInfo("chart template Name Verified " + chartHeader.getText());
				WebElement ChartNm = null;
				ChartNm = ControlActions_MobileApp.perform_GetElementByXPath(ChartsPageLocators.CHART_NM, "CHARTNAME",
						ChartName);
				ControlActions_MobileApp.waitForVisibility(ChartNm, 10000);
				if (ChartNm.getText().equals(ChartName)) {
					logInfo("chart is Present " + ChartNm.getText());
					WebElement formCheck = null;
					formCheck = ControlActions_MobileApp.perform_GetElementByXPath(ChartsPageLocators.CHART_YCORDINATE,
							"VALUE", YCordinate);
					ControlActions_MobileApp.waitForVisibility(formCheck, 10000);

					if (formCheck.getText().equals(YCordinate)) {
						logInfo("Y Co-ordinate has verified" + YCordinate);
						return true;
					}
				}
			}
			return true;
		} catch (Exception e) {
			logError("Failed to click -Task - " + e.getMessage());
			return false;
		}
	}

	public boolean clikNxtButton(WebElement EleToBeClicked) {
		try {
			ControlActions_MobileApp.waitForVisibility(EleToBeClicked, 60);
			ControlActions_MobileApp.click(EleToBeClicked);

			return true;
		} catch (Exception ex) {
			logInfo("Failed to click Next button on Charts Page" + ex.getMessage());
			return false;
		}
	}

	public boolean selectChartToBeVerified(String chartTemplate) {
		try {
			ControlActions_MobileApp.waitForVisibility(chartHeader, 60);
			ControlActions_MobileApp.waitForVisibility(chartName, 60);

			WebElement formCheck = null;
			@SuppressWarnings("unused")
			String ChartName = ControlActions_MobileApp.perform_GetElementByXPath2(ChartsPageLocators.CHART_NM,
					"CHARTNAME", "P-chart");
			ControlActions_MobileApp.waitForVisibility(formCheck, 60);
			String chartDispalyed = chartName.getText();
			switch (chartDispalyed) {
			case "P-chart":
				VerifyChartDetails(chartTemplate, "P-chart", "Defects");
				break;
			case "X-Bar":
				VerifyChartDetails(chartTemplate, "X-Bar", "Range");
				VerifyChartDetails(chartTemplate, "X-Bar", "Mean (oz)");

				break;
			}
			return true;
		} catch (Exception e) {
			logError("Failed to click -Task - " + e.getMessage());
			return false;
		}
	}

	public boolean checkFormIconFromSubmissionsSubMenu(String FormName) {
		try {

			WebElement resource = null;
			Thread.sleep(5000);

			resource = ControlActions_MobileApp.perform_GetElementByXPath(SavedPageLocators.Form_Name,
					"FormNameLocator", FormName);

			ControlActions_MobileApp.waitForVisibility(resource, 60);
			logInfo("Form is Present");

			if (ControlActions_MobileApp.isElementDisplayed(chartIcn)) {

				logInfo("chart Icon is present on Submissions screen");
			} else {
				Assert.fail("chart Icon is not present on Submissions screen");
			}

			return true;
		} catch (Exception ex) {
			logInfo("Failed to Search resource" + ex.getMessage());
			return false;
		}
	}

	public boolean clickCloseButton() {
		try {
			ControlActions_MobileApp.WaitforelementToBeClickable(closeBtn);
			closeBtn.click();
			logInfo("Clicked Close Button");
			return true;
		} catch (Exception e) {
			logError("Failed To click Close button " + e.getMessage());
			return false;
		}
	}
	
	// ================== General Functions ====================//

	// CREATE CHART
	public String createChart(String chartType, List<String> rules) {
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

			if (chartType.equals(Chart_Template_TYPES.UChart) || chartType.equals(Chart_Template_TYPES.NpChart)
					|| chartType.equals(Chart_Template_TYPES.XiMr)) {
				FormFieldParams numericField = new FormFieldParams();
				numericField.DPTFieldType = DPT_FIELD_TYPES.NUMERIC;
				numericField.Name = "Numeric";
				fp.formElements = Arrays.asList(numericField);
			} else {

				FormFieldParams numericField = new FormFieldParams();
				numericField.DPTFieldType = DPT_FIELD_TYPES.NUMERIC;
				numericField.Name = "Numeric";

				FormFieldParams numericField1 = new FormFieldParams();
				numericField1.DPTFieldType = DPT_FIELD_TYPES.NUMERIC;
				numericField1.Name = "Numeric1";

				fp.formElements = Arrays.asList(numericField, numericField1);
			}

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
			if (rules != null)
				chartBuilder.chartConfList = rules;
			chartBuilder.Name = ChartName;
			chartBuilder.chartLinkingName = ChartLinkName;
			chartBuilder.formId = formId;
			chartBuilder.formName = FormName;
			chartBuilder.locationId = locInstId;
			chartBuilder.chartTemplateType = chartType;
			chartBuilder.chartResourceList = Arrays.asList(chartResources1, chartResources2);

			if (chartType.equals(Chart_Template_TYPES.UChart) || chartType.equals(Chart_Template_TYPES.NpChart)
					|| chartType.equals(Chart_Template_TYPES.XiMr))
				chartBuilder.ChartFieldsList = Arrays.asList("Numeric");
			else
				chartBuilder.ChartFieldsList = Arrays.asList("Numeric1", "Numeric");

			chartBuilder.defaultChart = defaultChart;

			TCG_ChartBuilder_Wrapper chartBuilderWrapper = new TCG_ChartBuilder_Wrapper();

			chartBuilderWrapper.create_Chart_Wrapper(chartBuilder);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return FormName;
	}

	// FILL FORM AND VERIFY CHART
	public void fillFormNVerifyChartDetails(String FormName, String numFieldValue, String chartAxisLabel) {
		SavedPage SavedPageObj = new SavedPage(appiumDriver);
		HomePage homePage = new HomePage(appiumDriver);
		ChartsPage chartsPage = new ChartsPage(appiumDriver);

		ControlActions_MobileApp.swipeScreen("DOWN");
		Boolean searchFrms = homePage.SearchForm(FormName);

		TestValidation.IsTrue(searchFrms, "Form Searched successfully", "Failed to search Form");

		Boolean clickFrms = homePage.ClickForm(FormName);
		TestValidation.IsTrue(clickFrms, "Form Clicked successfully", "Failed to click Form");
		Boolean searchLocation = SavedPageObj.SearchResource(locationInstanceValue1);
		TestValidation.IsTrue(searchLocation, "Location Searched successfully", "Failed to search Location");
		Boolean searchResource = SavedPageObj.SearchResource(resourceInstanceValue1);
		TestValidation.IsTrue(searchResource, "Resource Searched successfully", "Failed to Resource Form");
		for (WebElement Numeric : SavedPageObj.ListNumericTxt) {
			Boolean txtNumeric = SavedPageObj.enterFieldValue(Numeric, numFieldValue);
			TestValidation.IsTrue(txtNumeric, "Numeric Field value entered successfully",
					"Failed to enter field value");

		}
		Boolean submitForm = SavedPageObj.submitForm();
		TestValidation.IsTrue(submitForm, "Form Submitted Successfully", "Failed to Submit Form");

		Boolean isVerifyXchart = chartsPage.VerifyChartDetails(FormName, ChartLinkName, chartAxisLabel);
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
	}

	// GOTO SUBMISSIONS AND VERIFY
	public boolean verifyChartDetailsInSubmission(String formName, String ExpectedMean, String chartAxisLabel,
			String ChartType) {
		boolean flag = false;
		boolean chartPage = false;
		try {
			ControlActions_MobileApp.waitForVisibility(chartIcon, 120);
			ControlActions_MobileApp.WaitforelementToBeClickable(chartIcon);
			ControlActions_MobileApp.actionClick(chartIcon);
			logInfo("Clicked on Chart");
			ControlActions_MobileApp.waitForVisibility(chartHeader, 120);

			WebElement ChartNm = null;
			ChartNm = ControlActions_MobileApp.perform_GetElementByXPath(ChartsPageLocators.CHART_NM, "CHARTNAME",
					ChartLinkName);
			ControlActions_MobileApp.waitForVisibility(ChartNm, 10000);
			if (ChartNm.getText().equals(ChartName)) {
				logInfo("chart is Present " + ChartNm.getText());
				WebElement formCheck = null;
				formCheck = ControlActions_MobileApp.perform_GetElementByXPath(ChartsPageLocators.CHART_YCORDINATE,
						"VALUE", chartAxisLabel);
				ControlActions_MobileApp.waitForVisibility(formCheck, 10000);

				if (formCheck.getText().equals(chartAxisLabel)) {
					logInfo("Y Co-ordinate has verified" + chartAxisLabel);
					chartPage = true;
				}

				TestValidation.IsTrue(chartPage, "Verified chart Type and formname succesfully for XBarS",
						"Failed to verify chart Type and formname for XBarS");
			}

			if (chartHeader.getText().equals(formName)) {
				logInfo("chart template Name Verified " + chartHeader.getText());
				ControlActions_MobileApp.waitForVisibility(pointsDetailGreyIcon, 120);
				logInfo("points icon is displayed");
				ControlActions_MobileApp.waitForVisibility(meanOnGreyPoint, 120);
				logInfo("mean on points icon is displayed");
				System.out.println(meanOnGreyPoint.getText());
				if (meanOnGreyPoint.getText().equalsIgnoreCase(ExpectedMean) && closeBtn.isDisplayed()
						&& closeBtn.getText().equalsIgnoreCase("CLOSE") && prevBtn.isDisplayed()
						&& prevBtn.getText().equalsIgnoreCase("PREVIOUS") && nextBtn.isDisplayed()
						&& nextBtn.getText().equalsIgnoreCase("NEXT")

				) {
					logInfo("Chart image page verified succesfully");
					ControlActions_MobileApp.WaitforelementToBeClickable(pointsDetailGreyIcon);
					ControlActions_MobileApp.actionClick(pointsDetailGreyIcon);
					pointsDetailGreyIcon.click();
					ControlActions_MobileApp.waitForVisibility(addComments, 30);
					logInfo("Clicked on points Detail Icon Succesfully");
					if (detailsViewBtn.isDisplayed() && cancelDetailsViewBtn.isDisplayed()
					// && headerMeanTxt.getText().equalsIgnoreCase("8.0")
							&& rule1.isDisplayed() && rule2.isDisplayed() && rule3.isDisplayed() && rule4.isDisplayed()
							&& rule5.isDisplayed() && rule6.isDisplayed() && rule7.isDisplayed() && rule8.isDisplayed()
							&& rule9.isDisplayed() && vr1.isDisplayed() && vr4.isDisplayed() && vr5.isDisplayed()
							&& vr8.isDisplayed()) {

						ControlActions_MobileApp.WaitforelementToBeClickable(detailsViewBtn);
						detailsViewBtn.click();
						if (ChartType.equalsIgnoreCase("XBarS") || ChartType.equalsIgnoreCase(Chart_Template_TYPES.XiMr)
								&& mean.isDisplayed() && stdVar.isDisplayed() && sampleSet.isDisplayed()
								&& clVal.isDisplayed() && lclVal.isDisplayed() && uclVal.isDisplayed()
								&& numValTxt.isDisplayed() && closeBtn.isDisplayed() && prevBtn.isDisplayed()
								&& nextBtn.isDisplayed() && dateTimeTxt.isDisplayed()) {

							ControlActions_MobileApp.WaitforelementToBeClickable(closeBtn);
							closeBtn.click();
							if (ControlActions_MobileApp.isElementDisplayed(chartIcn)) {
								flag = true;
							}
						}
						if ((ChartType.equalsIgnoreCase("P") || ChartType.equalsIgnoreCase("C")
								|| ChartType.equalsIgnoreCase("NPChart")
								|| ChartType.equalsIgnoreCase(Chart_Template_TYPES.UChart)) && mean.isDisplayed()
								&& sampleSet.isDisplayed() && clVal.isDisplayed() && lclVal.isDisplayed()
								&& uclVal.isDisplayed() && numValTxt.isDisplayed() && closeBtn.isDisplayed()
								&& prevBtn.isDisplayed() && nextBtn.isDisplayed() && dateTimeTxt.isDisplayed()) {

							ControlActions_MobileApp.WaitforelementToBeClickable(closeBtn);
							closeBtn.click();
							if (ControlActions_MobileApp.isElementDisplayed(chartIcn)) {
								flag = true;
							}
						}

						if (ChartType.equalsIgnoreCase(Chart_Template_TYPES.AverageChart)
								|| ChartType.equalsIgnoreCase(Chart_Template_TYPES.SumChart) && mean.isDisplayed()
										&& sampleSet.isDisplayed() && lclVal.isDisplayed() && numValTxt.isDisplayed()
										&& closeBtn.isDisplayed() && prevBtn.isDisplayed() && nextBtn.isDisplayed()
										&& dateTimeTxt.isDisplayed()) {
							ControlActions_MobileApp.WaitforelementToBeClickable(closeBtn);
							closeBtn.click();
							if (ControlActions_MobileApp.isElementDisplayed(chartIcn)) {
								flag = true;
							}
						}
					}
				}
			}

		} catch (Exception e) {
			logError("Failed to click -Task - " + e.getMessage());
			flag = false;
		}
		return flag;

	}
	
	//================ AVG CHART RUNNING SUM FIELD VERIFY - TestCase_39732_39733 =============================// 
	public boolean verifyAvgChartRunnigSum(String formName) {
		
		//Resource 1
		String val1 = "10", val2 = "20", val3 = "30", val4 = "40",
			   runningSumAfter3rdSubmission = "60.00",
			   runningSumAfter4thSubmission = "100.00";
		
		//Resource 2
		String val5 = "50";
		
		try {
			HomePage homePage = new HomePage(appiumDriver);

			ControlActions_MobileApp.swipeScreen("DOWN");
			
			Boolean searchFrms = homePage.SearchForm(formName);
			TestValidation.IsTrue(searchFrms, "Form Searched successfully", "Failed to search Form");

			// 1st Scenario - Multiple submissions, verify Running Sum
			boolean fillNSubmit1 = openFormAndFillNumfieldsAndSubmit(formName,locationInstanceValue1,resourceInstanceValue1,val1);
			TestValidation.IsTrue(fillNSubmit1, 
					  			  "Successfully filled and submitted the form 1st time ", 
					              "Failed to fill and submit the form 1st time ");
			
			TestValidation.IsTrue(clickCloseButton(), 
					  		     "Closed Chart", 
					             "Fail to close chart");
			
			boolean fillNSubmit2 = openFormAndFillNumfieldsAndSubmit(formName,locationInstanceValue1,resourceInstanceValue1,val2);
			TestValidation.IsTrue(fillNSubmit2, 
								 "Successfully filled and submitted the form 2nd time ", 
					  			  "Failed to fill and submit the form 2nd time ");
			
			TestValidation.IsTrue(clickCloseButton(), 
					  			  "Closed Chart", 
					  			  "Fail to close chart");
			
			boolean fillNSubmit3 = openFormAndFillNumfieldsAndSubmit(formName,locationInstanceValue1,resourceInstanceValue1,val3);
			TestValidation.IsTrue(fillNSubmit3, 
					  			 "Successfully filled and submitted the form 3rd time ", 
					  			  "Failed to fill and submit the form 3rd time ");
			
			ControlActions_MobileApp.WaitforelementToBeClickable(pointsDetailGreyIconList.get(pointsDetailGreyIconList.size()-1));
			ControlActions_MobileApp.actionClick(pointsDetailGreyIconList.get(pointsDetailGreyIconList.size()-1));
			pointsDetailGreyIconList.get(pointsDetailGreyIconList.size()-1).click();
			
			ControlActions_MobileApp.WaitforelementToBeClickable(detailsViewBtn);
			detailsViewBtn.click();
			
			if(runningSum.getText().equals(runningSumAfter3rdSubmission)) {
				TestValidation.IsTrue(true, 
						  			 "Successfully verified sum of Mean values in \"Running Sum\" field for same resource after 3 submissions", 
						  			 "Failed to verify sum of mean values in \"Running Sum\" field");
				logInfo("Verified Running Sum in 1st Scenario");
			}else{
				logInfo("Failed to verify Running Sum in 1st Scenario");
				return false;
			}
			
			TestValidation.IsTrue(clickCloseButton(), 
		  		     			  "Closed Chart", 
		  		                  "Fail to close chart");
			
			// Scenario 2 
			boolean fillNSubmitR2 = openFormAndFillNumfieldsAndSubmit(formName,locationInstanceValue1,resourceInstanceValue2,val5);
			TestValidation.IsTrue(fillNSubmitR2, 
		  			 			  "Successfully filled and submitted the form with other resource", 
		  			  			  "Failed to fill and submit the form with other resource");
			
			TestValidation.IsTrue(clickCloseButton(), 
								  "Closed Chart", 
						          "Fail to close chart");
			
			boolean fillNSubmit4 = openFormAndFillNumfieldsAndSubmit(formName,locationInstanceValue1,resourceInstanceValue1,val4);
			TestValidation.IsTrue(fillNSubmit4, 
		  			 			  "Successfully filled and submitted the form 4th time ", 
		  			              "Failed to fill and submit the form 4th time ");
			
			ControlActions_MobileApp.WaitforelementToBeClickable(pointsDetailGreyIconList.get(pointsDetailGreyIconList.size()-1));
			ControlActions_MobileApp.actionClick(pointsDetailGreyIconList.get(pointsDetailGreyIconList.size()-1));
			pointsDetailGreyIconList.get(pointsDetailGreyIconList.size()-1).click();
			
			ControlActions_MobileApp.WaitforelementToBeClickable(detailsViewBtn);
			detailsViewBtn.click();
			
			if(runningSum.getText().equals(runningSumAfter4thSubmission)) {
				TestValidation.IsTrue(true, 
			  			 "Successfully verified sum of Mean values in \"Running Sum\" field for same resource after 3 submissions", 
			  			 "Failed to verify sum of mean values in \"Running Sum\" field");
				logInfo("Verified Running Sum in 2nd Scenario");
			}else {
				logInfo("Failed to verify Running Sum in 2nd Scenario");
				return false;
			}
			
			logInfo("Both scenario Verified Running sum");
			return true;
		} catch (Exception e) {
			logError("Failed to Verify Running Sum Field" + e.getMessage());
			return false;
		}
	}
	
	public boolean openFormAndFillNumfieldsAndSubmit(String formName,String locInst, String resInst, String numFieldValue) {
		try {
			SavedPage SavedPageObj = new SavedPage(appiumDriver);
			HomePage homePage = new HomePage(appiumDriver);
			
			Boolean clickFrms = homePage.ClickForm(formName);
			TestValidation.IsTrue(clickFrms, "Form Clicked successfully", "Failed to click Form");
			Boolean searchLocation = SavedPageObj.SearchResource(locInst);
			TestValidation.IsTrue(searchLocation, "Location Searched successfully", "Failed to search Location");
			Boolean searchResource = SavedPageObj.SearchResource(resInst);
			TestValidation.IsTrue(searchResource, "Resource Searched successfully", "Failed to Resource Form");
			
			//1st Scenario - Making Multiple Submissions
			for (WebElement Numeric : SavedPageObj.ListNumericTxt) {
				Boolean txtNumeric = SavedPageObj.enterFieldValue(Numeric, numFieldValue);
				TestValidation.IsTrue(txtNumeric, 
									  "Numeric Field value entered successfully",
									   "Failed to enter field value");
			}
			Boolean submitForm = SavedPageObj.submitForm();
			TestValidation.IsTrue(submitForm, "Form Submitted Successfully", "Failed to Submit Form");
			return submitForm;
		} catch (Exception e) {
			logError("Failed to fill NumFields and Submit the form" + e.getMessage());
			return false;
		}
	}
	
	//======================== Chart with FILTER - TC_39734 ==============================//
	public String createChartWithFilter(String chartType, List<String> rules) {
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

//			String identifierFieldName = "Identifier1";
//			String filterIdValue = "filter";
			
			// API Implementation

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

			if (chartType.equals(Chart_Template_TYPES.UChart) || chartType.equals(Chart_Template_TYPES.NpChart)
					|| chartType.equals(Chart_Template_TYPES.XiMr)) {
				FormFieldParams numericField = new FormFieldParams();
				numericField.DPTFieldType = DPT_FIELD_TYPES.NUMERIC;
				numericField.Name = "Numeric";
				fp.formElements = Arrays.asList(numericField);
			} else {

				FormFieldParams numericField = new FormFieldParams();
				numericField.DPTFieldType = DPT_FIELD_TYPES.NUMERIC;
				numericField.Name = "Numeric";

				FormFieldParams numericField1 = new FormFieldParams();
				numericField1.DPTFieldType = DPT_FIELD_TYPES.NUMERIC;
				numericField1.Name = "Numeric1";
				
				FormFieldParams IdentifierField1 = new FormFieldParams();
				IdentifierField1.DPTFieldType = DPT_FIELD_TYPES.IDENTIFIER;
				IdentifierField1.identifierId = IDENTIFIER_TYPES.FREE_TEXT;
				IdentifierField1.InputMask = "aaaaaa";
				IdentifierField1.Name = identifierFieldName;
				
				fp.formElements = Arrays.asList(numericField, numericField1,IdentifierField1);
			}

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
			LinkedHashMap<String, String> shortNames = formCreationWrapper.API_Wrapper_Forms(fp);

			// Chart Default
			DefaultChart defaultChart = new DefaultChart();
			
//			defaultChart.Variation = "10";
//			defaultChart.Mean = "20";
			// chart Resources

			ChartResources chartResources1 = new ChartResources();
			chartResources1.ResourceName = resourceInstanceValue1;
			chartResources1.ResourceId = ResInstId1;
//			chartResources1.Mean = "10";
//			chartResources1.Variation = "20";

			ChartResources chartResources2 = new ChartResources();
			chartResources2.ResourceName = resourceInstanceValue2;
			chartResources2.ResourceId = ResInstId2;
//			chartResources2.Mean = "20";
//			chartResources2.Variation = "30";

			ChartBuilder chartBuilder = new ChartBuilder();
			if (rules != null)
				chartBuilder.chartConfList = rules;
			chartBuilder.Name = ChartName;
			chartBuilder.chartLinkingName = ChartLinkName;
			chartBuilder.formId = formId;
			chartBuilder.formName = FormName;
			chartBuilder.locationId = locInstId;
			chartBuilder.chartTemplateType = chartType;
			chartBuilder.chartResourceList = Arrays.asList(chartResources1, chartResources2);
			chartBuilder.IdentifierFilterName1 = shortNames.get(identifierFieldName);
			chartBuilder.IdentifierFilterValue1 = filterIdValue;
			
//			if(filter!=null) {
//				//chartBuilder.IdentifierFilterName1 = filter.keySet().;
//				filter.forEach((key,value) -> {
//					chartBuilder.IdentifierFilterName1 = key;
//					chartBuilder.IdentifierFilterValue1 = value;
//				});
//				
//			}

			if (chartType.equals(Chart_Template_TYPES.UChart) || chartType.equals(Chart_Template_TYPES.NpChart)
					|| chartType.equals(Chart_Template_TYPES.XiMr))
				chartBuilder.ChartFieldsList = Arrays.asList("Numeric");
			else
				chartBuilder.ChartFieldsList = Arrays.asList("Numeric1", "Numeric");

			chartBuilder.defaultChart = defaultChart;

			TCG_ChartBuilder_Wrapper chartBuilderWrapper = new TCG_ChartBuilder_Wrapper();

			chartBuilderWrapper.create_Chart_Wrapper(chartBuilder);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return FormName;
	}
	
	public boolean openFilterFormFillAndSubmit(String formName,String filterVal, String numFieldValue) {
		try {
			SavedPage SavedPageObj = new SavedPage(appiumDriver);
			HomePage homePage = new HomePage(appiumDriver);
			REG_FormsAllValidation afv = new REG_FormsAllValidation(appiumDriver);
			
			Boolean clickFrms = homePage.ClickForm(formName);
			TestValidation.IsTrue(clickFrms, "Form Clicked successfully", "Failed to click Form");
			Boolean searchLocation = SavedPageObj.SearchResource(locationInstanceValue1);
			TestValidation.IsTrue(searchLocation, "Location Searched successfully", "Failed to search Location");
			Boolean searchResource = SavedPageObj.SearchResource(resourceInstanceValue1);
			TestValidation.IsTrue(searchResource, "Resource Searched successfully", "Failed to Resource Form");
			
			//1st Scenario - Making Multiple Submissions
			for (WebElement Numeric : SavedPageObj.ListNumericTxt) {
				Boolean txtNumeric = SavedPageObj.enterFieldValue(Numeric, numFieldValue);
				TestValidation.IsTrue(txtNumeric, 
									  "Numeric Field value entered successfully",
									   "Failed to enter field value");
			}
			
			// Fill Filter Field
			boolean idFilter = afv.enterIdentifierFieldValue(identifierFieldName+" *", filterVal);
			TestValidation.IsTrue(idFilter, 
								  "Identifier1 Field value entered successfully", 
								  "Failed to enter field value in Identifier1");
			
			Boolean submitForm = SavedPageObj.submitForm();
			TestValidation.IsTrue(submitForm, "Form Submitted Successfully", "Failed to Submit Form");
			return submitForm;
		} catch (Exception e) {
			logError("Failed to fill NumFields and Submit the form" + e.getMessage());
			return false;
		}
		
	}

	public boolean verifyAvgChartRunnigSumWithFilter(String formName) {
		//Resource 1
		String val1 = "10", val2 = "20", val3 = "30",
			   runningSumAfter3rdSubmission = "60.00";
		try {
			HomePage homePage = new HomePage(appiumDriver);

			ControlActions_MobileApp.swipeScreen("DOWN");
			
			Boolean searchFrms = homePage.SearchForm(formName);
			TestValidation.IsTrue(searchFrms, "Form Searched successfully", "Failed to search Form");

			// 1st Scenario - Multiple submissions, verify Running Sum
			boolean fillNSubmit1 = openFilterFormFillAndSubmit(formName,filterIdValue,val1);
			TestValidation.IsTrue(fillNSubmit1, 
					  			  "Successfully filled and submitted the form 1st time ", 
					              "Failed to fill and submit the form 1st time ");
			
			TestValidation.IsTrue(clickCloseButton(), 
					  		     "Closed Chart", 
					             "Fail to close chart");
			
			boolean fillNSubmit2 = openFilterFormFillAndSubmit(formName,filterIdValue,val2);
			TestValidation.IsTrue(fillNSubmit2, 
								 "Successfully filled and submitted the form 2nd time ", 
					  			  "Failed to fill and submit the form 2nd time ");
			
			TestValidation.IsTrue(clickCloseButton(), 
					  			  "Closed Chart", 
					  			  "Fail to close chart");
			
			boolean fillNSubmit3 = openFilterFormFillAndSubmit(formName,filterIdValue,val3);
			TestValidation.IsTrue(fillNSubmit3, 
					  			 "Successfully filled and submitted the form 3rd time ", 
					  			  "Failed to fill and submit the form 3rd time ");
			
			ControlActions_MobileApp.WaitforelementToBeClickable(pointsDetailGreyIconList.get(pointsDetailGreyIconList.size()-1));
			ControlActions_MobileApp.actionClick(pointsDetailGreyIconList.get(pointsDetailGreyIconList.size()-1));
			pointsDetailGreyIconList.get(pointsDetailGreyIconList.size()-1).click();
			
			ControlActions_MobileApp.WaitforelementToBeClickable(detailsViewBtn);
			detailsViewBtn.click();
			
			if(runningSum.getText().equals(runningSumAfter3rdSubmission)) {
				TestValidation.IsTrue(true, 
						  			 "Successfully verified sum of Mean values in \"Running Sum\" field for same resource after 3 submissions", 
						  			 "Failed to verify sum of mean values in \"Running Sum\" field");
				logInfo("Verified Running Sum in 1st Scenario");
			}else{
				logInfo("Failed to verify Running Sum in 1st Scenario");
				return false;
			}
			logInfo("Verified Running sum with Filter Field");
			return true;
		} catch (Exception e) {
			logError("Failed to Verify Running Sum Field" + e.getMessage());
			return false;
		}
	}

}
