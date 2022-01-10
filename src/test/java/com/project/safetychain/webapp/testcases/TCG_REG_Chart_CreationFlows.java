package com.project.safetychain.webapp.testcases;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.project.safetychain.webapp.pageobjects.ChartBuilderPage;
import com.project.safetychain.webapp.pageobjects.ChartBuilderPage.ChartTypes;
import com.project.safetychain.webapp.testcases.support.FormOperations.FormDetails;
import com.project.safetychain.webapp.pageobjects.CommonPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage;
import com.project.safetychain.webapp.pageobjects.FormsManagerChartsPage;
import com.project.safetychain.webapp.pageobjects.FormsManagerChartsPage.FieldSelection;
import com.project.safetychain.webapp.pageobjects.FormsManagerChartsPage.FilterSelection;
import com.project.safetychain.webapp.pageobjects.FormsManagerChartsPage.OutputValues;
import com.project.safetychain.webapp.pageobjects.FormsManagerChartsPage.ResourceSelection;
import com.project.safetychain.webapp.pageobjects.FormsManagerPage;
import com.project.safetychain.webapp.pageobjects.HomePage;
import com.project.safetychain.webapp.pageobjects.LoginPage;
import com.project.safetychain.webapp.pageobjects.ManageLocationPage;
import com.project.safetychain.webapp.pageobjects.ManageResourcePage;
import com.project.safetychain.webapp.pageobjects.ResourceDesignerPage;
import com.project.safetychain.webapp.testcases.support.FormOperations;
import com.project.safetychain.api.models.support.Support.FormFieldParams;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.utilities.ApiUtils;
import com.project.utilities.CommonMethods;
import com.project.utilities.ControlActions;

public class TCG_REG_Chart_CreationFlows extends TestBase{
	ControlActions controlActions;
	CommonPage mainPage;	
	LoginPage lp;
	HomePage hp;
	ChartBuilderPage cbp;
	FormsManagerPage fmp;
	FormsManagerChartsPage fmcp;
	FormDesignerPage formDesignerPage;
	ResourceDesignerPage resourceDesigner;
	ManageResourcePage manageResource;
	ManageLocationPage locations;
	FSQABrowserPage fbp;
	FormOperations foperations;
	FieldSelection fselect;
	FilterSelection filter;
	ResourceSelection res;
	OutputValues output;
	ApiUtils apiUtils = null;
	FormDetails formDetails;
	FormFieldParams ffp;

	public static String formName;
	public static String locationCategoryValue;
	public static String resourceCategoryValue;
	public static String resourceInstanceValue;
	public static String locationInstanceValue;
	public static String formtaskname;
	public static String resourceInstances;
	public static String numeric1;
	public static String numeric2;
	public static String identifier1;
	public static String identifier2;
	
	public static String XiMrChart_37740;
	public static String NpChart1001;

	@BeforeClass(alwaysRun = true)
	public void groupInit() throws InterruptedException {

		formName = CommonMethods.dynamicText("Automation_CheckForm");
		locationCategoryValue = CommonMethods.dynamicText("Loc_Cat");
		resourceCategoryValue = CommonMethods.dynamicText("Equip_Cat");
		resourceInstanceValue = CommonMethods.dynamicText("Equip_Inst");		
		locationInstanceValue = CommonMethods.dynamicText("Loc_Inst");

		numeric1 = "Pieces";
		numeric2 = "Sample";
		identifier1 = "Line";
		identifier2 = "Shift";

		driver = launchbrowser();
		controlActions = new ControlActions(driver);
		controlActions.getUrl(applicationUrl);
		lp = new LoginPage(driver);
		hp = new HomePage(driver);
		cbp = new ChartBuilderPage(driver);
		fmp = new FormsManagerPage(driver);
		fmcp = new FormsManagerChartsPage(driver);
		fbp = new FSQABrowserPage(driver);
		foperations = new FormOperations(driver);
		//fdparams = new FormDesignParams();
		//ffparams1 = new FormFieldParams();
		//ffparams2 = new FormFieldParams();
		fselect = new FieldSelection();
		filter = new FilterSelection();
		//formdetails1 = new FormDetails();
		//formdetails2 = new FormDetails();
		res = new ResourceSelection();
		output = new OutputValues();
		formDesignerPage = new FormDesignerPage(driver);
		resourceDesigner =  new ResourceDesignerPage(driver);
		manageResource = new ManageResourcePage(driver);
		locations =  new ManageLocationPage(driver);
		controlActions.getUrl(applicationUrl);
		hp = lp.performLogin(adminUsername, adminPassword);
		if(hp.error) {
			TCGFailureMsg = "Could NOT login to application";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}	
		
		// Helper Chart Creation for TC 37669
//		hp.clickChartBuilderMenu();
//		ChartForTC37669 = CommonMethods.dynamicText("Ch37669");
//		boolean createAveragechart = cbp.addNewChart(ChartForTC37669,ChartTypes.AVERAGECHART);
//		TestValidation.IsTrue(createAveragechart, 
//							 "Average Chart Created successfully", 
//							 "Failed to create Average Chart");

		/*String resourceInstances[][] = {{resourceInstanceValue,"true"}};

		HashMap<String,String> resourceCategories = new HashMap<String,String>();
		resourceCategories.put(CATEGORYTYPES.LOCATION, locationCategoryValue);
		resourceCategories.put(CATEGORYTYPES.EQUIPMENT, resourceCategoryValue);

		HashMap<String, List<String>> selectResources = new HashMap<String, List<String>>();
		selectResources.put(FORMRESOURCETYPES.EQUIPMENT,Arrays.asList(resourceCategoryValue));

		HashMap<String,List<String>> fieldDetails1 = new HashMap<String,List<String>>();
		fieldDetails1.put(FIELD_TYPES.NUMERIC, Arrays.asList(numeric1,numeric2));
		fieldDetails1.put(FIELD_TYPES.IDENTIFIER, Arrays.asList(identifier1));

		HashMap<String,List<String>> fieldDetails2 = new HashMap<String,List<String>>();
		fieldDetails2.put(FIELD_TYPES.IDENTIFIER, Arrays.asList(identifier2));


		ffparams1.FieldDetails = fieldDetails1;		
		ffparams1.IdentiferType = IDENTIFIER_INPUT_TYPE.SELECT_ONE;
		ffparams1.IdentifierValue = Arrays.asList("LINE1","LINE2","LINE3");

		ffparams2.FieldDetails = fieldDetails2;	
		ffparams2.IdentiferType = IDENTIFIER_INPUT_TYPE.SELECT_ONE;		
		ffparams2.IdentifierValue = Arrays.asList("SHIFT1","SHIFT2","SHIFT3");

		fdparams.FormType = FORMTYPES.CHECK;
		fdparams.FormName = formName;
		fdparams.SelectResources = selectResources;
		fdparams.DesignFields = Arrays.asList(ffparams1,ffparams2);
		fdparams.ReleaseForm = true;

		if(!resourceDesigner.createCategory(resourceCategories)) {
			TCGFailureMsg = "Could NOT create location and equipment category- ";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		if(!locations.createLocationInstanceLinkUser(locationCategoryValue,locationInstanceValue,adminUsername)) {
			TCGFailureMsg = "Could NOT create location instance - " + locationCategoryValue;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		if(!manageResource.addResourceInstances("Equipment", resourceCategoryValue, resourceInstances, true, locationInstanceValue)) {
			TCGFailureMsg = "Could NOT create resource " + resourceInstances + " & link Location - "+ locationInstanceValue;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		hp.clickFormDesignerMenu();
		if(!formDesignerPage.createAndReleaseForm(fdparams)) {
			TCGFailureMsg = "Could NOT create and release form - " + formName;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		hp.clickFSQABrowserMenu();
		if(!fbp.selectResourceDropDownandNavigate(FSQARESOURCE.EQUIPMENT,FSQATAB.FORMS)) {
			TCGFailureMsg = "For equipment category, could NOT navigate to FSQABrowser > Forms tab";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		if(!fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, formName)) {
			TCGFailureMsg = "Could not filter form - " + formName;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		HashMap<String, List<String>> fielddetails = new HashMap<String, List<String>>();
		fielddetails.put(numeric1,Arrays.asList("4"));
		fielddetails.put(numeric2,Arrays.asList("8"));
		fielddetails.put(identifier1,Arrays.asList("LINE1"));
		fielddetails.put(identifier2,Arrays.asList("SHIFT1"));

		HashMap<String, List<String>> fielddetails1 = new HashMap<String, List<String>>();
		fielddetails1.put(numeric1,Arrays.asList("4"));
		fielddetails1.put(numeric2,Arrays.asList("8"));
		fielddetails1.put(identifier1,Arrays.asList("LINE2"));
		fielddetails1.put(identifier2,Arrays.asList("SHIFT2"));

		formdetails1.fieldDetails = fielddetails;
		formdetails1.resourceName = resourceInstanceValue;
		formdetails1.locationName = locationInstanceValue;
		formdetails2.fieldDetails = fielddetails1;
		formdetails2.resourceName = resourceInstanceValue;
		formdetails2.locationName = locationInstanceValue;

		if(!foperations.submitData(formdetails1)) {
			TCGFailureMsg = "Failed to submit form";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		fbp.selectForm(formName);
		if(!foperations.submitData(formdetails1)) {
			TCGFailureMsg = "Failed to submit form";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		fbp.selectForm(formName);
		if(!foperations.submitData(formdetails2)) {
			TCGFailureMsg = "Failed to submit form";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}*/
	}

	 
	@Test(description="Chart Builder || Verify the creation of new \"NpChart\" chart type")
	public void TestCase_37644() {

		String NpChart = CommonMethods.dynamicText("NpChart");

		hp.clickChartBuilderMenu();
		
		boolean clickAddNewChart = cbp.clickAddNewChartButton();
		TestValidation.IsTrue(clickAddNewChart, 
							  "Clicked on 'Add New Chart' button", 
				  			  "Could NOT click on 'Add New Chart' button");
		
		boolean openChartTypeDropDown = cbp.clickChartTypeDropDownButton();
		TestValidation.IsTrue(openChartTypeDropDown, 
				  			  "Clicked and Opened 'Chart Types' Drop Down List", 
				  			  "Could NOT Click and Open 'Chart Types' Drop Down List");
		
		boolean verifyAllChartTypeDropDown = cbp.verifyChartTypeDropDownList();
		TestValidation.IsTrue(verifyAllChartTypeDropDown, 
							  "Verified all chart types  displayed in \"CHART TYPE\" drop down list", 	
							  "Could NOT verify all chart types displayed in \"CHART TYPE\" drop down list");	
		
		boolean selectNpChart = cbp.selectChartType(ChartTypes.NPCHART);
		TestValidation.IsTrue(selectNpChart, 
				  			  "Selected chart type - " + ChartTypes.NPCHART, 
				  			  "Could NOT select chart type - " + ChartTypes.NPCHART);
		
		boolean addChartname = cbp.addChartName(NpChart);
		TestValidation.IsTrue(addChartname, 
				  			  "Entered chart name as - " + NpChart, 
				  			  "Could NOT enter chart name");	
		
		boolean enableChart = cbp.toggleOnEnableChart();
		TestValidation.IsTrue(enableChart, 
				  		      "Enabled Chart -" + NpChart, 
				              "Could NOT Enable Chart");
		
		boolean setPrimaryRules = cbp.selectPrimaryChartRules(Arrays.asList("4","2","1","7"));
		TestValidation.IsTrue(setPrimaryRules, 
				  			  "Successfully set Primary Chart Rules 4,2,1,7", 
							  "Could NOT set primary chart rules");
		
		boolean clickSaveButton = cbp.clickSaveButton();
		TestValidation.IsTrue(clickSaveButton, 
							  "Clicked on Save Button", 
				  			  "Could NOT click on Save Button");
	}
	
	@Test(description="Chart Builder || Verify the creation of new \"XBARS\" chart type")
	public void TestCase_37645() {

		String XBarSChart = CommonMethods.dynamicText("XBarSChart");

		hp.clickChartBuilderMenu();
		
		boolean clickAddNewChart = cbp.clickAddNewChartButton();
		TestValidation.IsTrue(clickAddNewChart, 
							  "Clicked on 'Add New Chart' button", 
				  			  "Could NOT click on 'Add New Chart' button");
		
		boolean openChartTypeDropDown = cbp.clickChartTypeDropDownButton();
		TestValidation.IsTrue(openChartTypeDropDown, 
				  			  "Clicked and Opened 'Chart Types' Drop Down List", 
				  			  "Could NOT Click and Open 'Chart Types' Drop Down List");
		
		boolean verifyAllChartTypeDropDown = cbp.verifyChartTypeDropDownList();
		TestValidation.IsTrue(verifyAllChartTypeDropDown, 
							  "Verified all chart types  displayed in \"CHART TYPE\" drop down list", 	
							  "Could NOT verify all chart types displayed in \"CHART TYPE\" drop down list");	
		
		boolean selectNpChart = cbp.selectChartType(ChartTypes.XBARS);
		TestValidation.IsTrue(selectNpChart, 
				  			  "Selected chart type - " + ChartTypes.XBARS, 
				  			  "Could NOT select chart type - " + ChartTypes.XBARS);
		
		boolean addChartname = cbp.addChartName(XBarSChart);
		TestValidation.IsTrue(addChartname, 
				  			  "Entered chart name as - " + XBarSChart, 
				  			  "Could NOT enter chart name");	
		
		boolean enableChart = cbp.toggleOnEnableChart();
		TestValidation.IsTrue(enableChart, 
				  		      "Enabled Chart -" + XBarSChart, 
				              "Could NOT Enable Chart");
		
		boolean setPrimaryRules = cbp.selectPrimaryChartRules(Arrays.asList("4","2","1","7"));
		TestValidation.IsTrue(setPrimaryRules, 
				  			  "Successfully set Primary Chart Rules 4,2,1,7", 
							  "Could NOT set primary chart rules");
		
		boolean setSecondaryRules = cbp.selectSecondaryChartRules(Arrays.asList("1","8","5"));
		TestValidation.IsTrue(setSecondaryRules, 
				  			  "Successfully set Secondary Chart Rules 1,8,5", 
							  "Could NOT set Secodary chart rules");
		
		boolean clickSaveButton = cbp.clickSaveButton();
		TestValidation.IsTrue(clickSaveButton, 
							  "Clicked on Save Button", 
				  			  "Could NOT click on Save Button");
	}
	
	@Test(description="Chart Builder || Verify the creation of new \"SumChart\" chart type")
	public void TestCase_37538() {

		String SumChart = CommonMethods.dynamicText("SumChart");
		
		hp.clickChartBuilderMenu();
		
		boolean clickAddNewChart = cbp.clickAddNewChartButton();
		TestValidation.IsTrue(clickAddNewChart, 
							  "Clicked on 'Add New Chart' button", 
				  			  "Could NOT click on 'Add New Chart' button");
		
		boolean openChartTypeDropDown = cbp.clickChartTypeDropDownButton();
		TestValidation.IsTrue(openChartTypeDropDown, 
				  			  "Clicked and Opened 'Chart Types' Drop Down List", 
				  			  "Could NOT Click and Open 'Chart Types' Drop Down List");
		
		boolean verifyAllChartTypeDropDown = cbp.verifyChartTypeDropDownList();
		TestValidation.IsTrue(verifyAllChartTypeDropDown, 
							  "Verified all chart types  displayed in \"CHART TYPE\" drop down list", 	
							  "Could NOT verify all chart types displayed in \"CHART TYPE\" drop down list");	
		
		boolean selectNpChart = cbp.selectChartType(ChartTypes.SUMCHART);
		TestValidation.IsTrue(selectNpChart, 
				  			  "Selected chart type - " + ChartTypes.SUMCHART, 
				  			  "Could NOT select chart type - " + ChartTypes.SUMCHART);
		
		boolean addChartname = cbp.addChartName(SumChart);
		TestValidation.IsTrue(addChartname, 
				  			  "Entered chart name as - " + SumChart, 
				  			  "Could NOT enter chart name");	
		
		boolean enableChart = cbp.toggleOnEnableChart();
		TestValidation.IsTrue(enableChart, 
				  		      "Enabled Chart -" + SumChart, 
				              "Could NOT Enable Chart");
		
		boolean clickSaveButton = cbp.clickSaveButton();
		TestValidation.IsTrue(clickSaveButton, 
							  "Clicked on Save Button", 
				  			  "Could NOT click on Save Button");
	}
	
	@Test(description="Chart Builder || Verify user should not able to create duplicate name chart template")
	public void TestCase_37656() {

		String NpChart = CommonMethods.dynamicText("NpChart");

		hp.clickChartBuilderMenu();
		
		// Creating 1st Chart  
		boolean clickAddNewChart = cbp.clickAddNewChartButton();
		TestValidation.IsTrue(clickAddNewChart, 
							  "Clicked on 'Add New Chart' button", 
				  			  "Could NOT click on 'Add New Chart' button");
		
		boolean openChartTypeDropDown = cbp.clickChartTypeDropDownButton();
		TestValidation.IsTrue(openChartTypeDropDown, 
				  			  "Clicked and Opened 'Chart Types' Drop Down List", 
				  			  "Could NOT Click and Open 'Chart Types' Drop Down List");
		
		boolean verifyAllChartTypeDropDown = cbp.verifyChartTypeDropDownList();
		TestValidation.IsTrue(verifyAllChartTypeDropDown, 
							  "Verified all chart types  displayed in \"CHART TYPE\" drop down list", 	
							  "Could NOT verify all chart types displayed in \"CHART TYPE\" drop down list");	
		
		boolean selectNpChart = cbp.selectChartType(ChartTypes.NPCHART);
		TestValidation.IsTrue(selectNpChart, 
				  			  "Selected chart type - " + ChartTypes.NPCHART, 
				  			  "Could NOT select chart type - " + ChartTypes.NPCHART);
		
		boolean addChartname = cbp.addChartName(NpChart);
		TestValidation.IsTrue(addChartname, 
				  			  "Entered chart name as - " + NpChart, 
				  			  "Could NOT enter chart name");	
		
		boolean setPrimaryRules = cbp.selectPrimaryChartRules(Arrays.asList("1"));
		TestValidation.IsTrue(setPrimaryRules, 
				  			  "Successfully set Primary Chart Rule 1", 
							  "Could NOT set primary chart rules");
		
		boolean clickSaveButton = cbp.clickSaveButton();
		TestValidation.IsTrue(clickSaveButton, 
							  "Clicked on Save Button", 
				  			  "Could NOT click on Save Button");
			
		// Creating 2nd Chart with the same Name  
		boolean clickAddNewChart2 = cbp.clickAddNewChartButton();
		TestValidation.IsTrue(clickAddNewChart2, 
				  			  "Clicked on 'Add New Chart' button", 
							  "Could NOT click on 'Add New Chart' button");
		
		boolean addChartname2 = cbp.addChartName(NpChart);
		TestValidation.IsTrue(addChartname2, 
	  			  			  "Entered chart name as - " + NpChart, 
	  			  			  "Could NOT enter chart name");
		
		boolean clickSaveButton2 = cbp.clickSaveButton();
		TestValidation.IsTrue(clickSaveButton2, 
				  			  "Clicked on Save Button", 
	  			  			  "Could NOT click on Save Button");
		
		boolean handleDuplicateNameError = cbp.verifyAndHandleDuplicateNamePopup();
		TestValidation.IsTrue(handleDuplicateNameError, 
				  			  "Verified an error pop up saying Chart name must be unique. And user is not able to create the new chart template",
				  			  "Could NOT verify Unique Name Error popup ");
	}
	
	@Test(description=" Chart Builder || Verify the creation of new \"XBARR\" chart type")
	public void TestCase_31890() {

		String XBarRChart = CommonMethods.dynamicText("XBarRChart");
		
		hp.clickChartBuilderMenu();
		
		boolean clickAddNewChart = cbp.clickAddNewChartButton();
		TestValidation.IsTrue(clickAddNewChart, 
							  "Clicked on 'Add New Chart' button", 
				  			  "Could NOT click on 'Add New Chart' button");
		
		boolean openChartTypeDropDown = cbp.clickChartTypeDropDownButton();
		TestValidation.IsTrue(openChartTypeDropDown, 
				  			  "Clicked and Opened 'Chart Types' Drop Down List", 
				  			  "Could NOT Click and Open 'Chart Types' Drop Down List");
		
		boolean verifyAllChartTypeDropDown = cbp.verifyChartTypeDropDownList();
		TestValidation.IsTrue(verifyAllChartTypeDropDown, 
							  "Verified all chart types  displayed in \"CHART TYPE\" drop down list", 	
							  "Could NOT verify all chart types displayed in \"CHART TYPE\" drop down list");	
		
		boolean selectNpChart = cbp.selectChartType(ChartTypes.XBARR);
		TestValidation.IsTrue(selectNpChart, 
				  			  "Selected chart type - " + ChartTypes.XBARR, 
				  			  "Could NOT select chart type - " + ChartTypes.XBARR);
		
		boolean addChartname = cbp.addChartName(XBarRChart);
		TestValidation.IsTrue(addChartname, 
				  			  "Entered chart name as - " + XBarRChart, 
				  			  "Could NOT enter chart name");	
		
		boolean enableChart = cbp.toggleOnEnableChart();
		TestValidation.IsTrue(enableChart, 
				  		      "Enabled Chart -" + XBarRChart, 
				              "Could NOT Enable Chart");
		
		boolean setPrimaryRules = cbp.selectPrimaryChartRules(Arrays.asList("9","3","8"));
		TestValidation.IsTrue(setPrimaryRules, 
				  			  "Successfully set Primary Chart Rules 9,3,8", 
							  "Could NOT set primary chart rules");
		
		boolean setSecondaryRules = cbp.selectSecondaryChartRules(Arrays.asList("1","8","5"));
		TestValidation.IsTrue(setSecondaryRules, 
				  			  "Successfully set Secondary Chart Rules 1,8,5", 
							  "Could NOT set Secodary chart rules");
		
		boolean clickSaveButton = cbp.clickSaveButton();
		TestValidation.IsTrue(clickSaveButton, 
							  "Clicked on Save Button", 
				  			  "Could NOT click on Save Button");
	}
	
	@Test(description="Chart Builder || Verify the creation of new \"UChart\" chart type")
	public void TestCase_37646() {

		String UChart = CommonMethods.dynamicText("UChart");

		hp.clickChartBuilderMenu();
		
		boolean clickAddNewChart = cbp.clickAddNewChartButton();
		TestValidation.IsTrue(clickAddNewChart, 
							  "Clicked on 'Add New Chart' button", 
				  			  "Could NOT click on 'Add New Chart' button");
		
		boolean openChartTypeDropDown = cbp.clickChartTypeDropDownButton();
		TestValidation.IsTrue(openChartTypeDropDown, 
				  			  "Clicked and Opened 'Chart Types' Drop Down List", 
				  			  "Could NOT Click and Open 'Chart Types' Drop Down List");
		
		boolean verifyAllChartTypeDropDown = cbp.verifyChartTypeDropDownList();
		TestValidation.IsTrue(verifyAllChartTypeDropDown, 
							  "Verified all chart types  displayed in \"CHART TYPE\" drop down list", 	
							  "Could NOT verify all chart types displayed in \"CHART TYPE\" drop down list");	
		
		boolean selectNpChart = cbp.selectChartType(ChartTypes.UCHART);
		TestValidation.IsTrue(selectNpChart, 
				  			  "Selected chart type - " + ChartTypes.UCHART, 
				  			  "Could NOT select chart type - " + ChartTypes.UCHART);
		
		boolean addChartname = cbp.addChartName(UChart);
		TestValidation.IsTrue(addChartname, 
				  			  "Entered chart name as - " + UChart, 
				  			  "Could NOT enter chart name");	
		
		boolean enableChart = cbp.toggleOnEnableChart();
		TestValidation.IsTrue(enableChart, 
				  		      "Enabled Chart -" + UChart, 
				              "Could NOT Enable Chart");
		
		boolean setPrimaryRules = cbp.selectPrimaryChartRules(Arrays.asList("9","8","7","3","2"));
		TestValidation.IsTrue(setPrimaryRules, 
				  			  "Successfully set Primary Chart Rules 9,8,7,3,2", 
							  "Could NOT set primary chart rules");
		
		boolean clickSaveButton = cbp.clickSaveButton();
		TestValidation.IsTrue(clickSaveButton, 
							  "Clicked on Save Button", 
				  			  "Could NOT click on Save Button");
	}
	
	@Test(description="Chart Builder || Verify the creation of new \"PChart\" chart type")
	public void TestCase_37647() {

		String PChart = CommonMethods.dynamicText("PChart");
		
		hp.clickChartBuilderMenu();
		
		boolean clickAddNewChart = cbp.clickAddNewChartButton();
		TestValidation.IsTrue(clickAddNewChart, 
							  "Clicked on 'Add New Chart' button", 
				  			  "Could NOT click on 'Add New Chart' button");
		
		boolean openChartTypeDropDown = cbp.clickChartTypeDropDownButton();
		TestValidation.IsTrue(openChartTypeDropDown, 
				  			  "Clicked and Opened 'Chart Types' Drop Down List", 
				  			  "Could NOT Click and Open 'Chart Types' Drop Down List");
		
		boolean verifyAllChartTypeDropDown = cbp.verifyChartTypeDropDownList();
		TestValidation.IsTrue(verifyAllChartTypeDropDown, 
							  "Verified all chart types  displayed in \"CHART TYPE\" drop down list", 	
							  "Could NOT verify all chart types displayed in \"CHART TYPE\" drop down list");	
		
		boolean selectNpChart = cbp.selectChartType(ChartTypes.PCHART);
		TestValidation.IsTrue(selectNpChart, 
				  			  "Selected chart type - " + ChartTypes.PCHART, 
				  			  "Could NOT select chart type - " + ChartTypes.PCHART);
		
		boolean addChartname = cbp.addChartName(PChart);
		TestValidation.IsTrue(addChartname, 
				  			  "Entered chart name as - " + PChart, 
				  			  "Could NOT enter chart name");	
		
		boolean enableChart = cbp.toggleOnEnableChart();
		TestValidation.IsTrue(enableChart, 
				  		      "Enabled Chart -" + PChart, 
				              "Could NOT Enable Chart");
		
		boolean setPrimaryRules = cbp.selectPrimaryChartRules(Arrays.asList("4","5","7"));
		TestValidation.IsTrue(setPrimaryRules, 
				  			  "Successfully set Primary Chart Rules 4,5,7", 
							  "Could NOT set primary chart rules");
		
		boolean clickSaveButton = cbp.clickSaveButton();
		TestValidation.IsTrue(clickSaveButton, 
							  "Clicked on Save Button", 
				  			  "Could NOT click on Save Button");
	}
	
	@Test(description=" Chart Builder || Verify the creation of new \"CChart\" chart type")
	public void TestCase_37648() {

		String CChart = CommonMethods.dynamicText("CChart");
		
		hp.clickChartBuilderMenu();
		
		boolean clickAddNewChart = cbp.clickAddNewChartButton();
		TestValidation.IsTrue(clickAddNewChart, 
							  "Clicked on 'Add New Chart' button", 
				  			  "Could NOT click on 'Add New Chart' button");
		
		boolean openChartTypeDropDown = cbp.clickChartTypeDropDownButton();
		TestValidation.IsTrue(openChartTypeDropDown, 
				  			  "Clicked and Opened 'Chart Types' Drop Down List", 
				  			  "Could NOT Click and Open 'Chart Types' Drop Down List");
		
		boolean verifyAllChartTypeDropDown = cbp.verifyChartTypeDropDownList();
		TestValidation.IsTrue(verifyAllChartTypeDropDown, 
							  "Verified all chart types  displayed in \"CHART TYPE\" drop down list", 	
							  "Could NOT verify all chart types displayed in \"CHART TYPE\" drop down list");	
		
		boolean selectNpChart = cbp.selectChartType(ChartTypes.CCHART);
		TestValidation.IsTrue(selectNpChart, 
				  			  "Selected chart type - " + ChartTypes.CCHART, 
				  			  "Could NOT select chart type - " + ChartTypes.CCHART);
		
		boolean addChartname = cbp.addChartName(CChart);
		TestValidation.IsTrue(addChartname, 
				  			  "Entered chart name as - " + CChart, 
				  			  "Could NOT enter chart name");	
		
		boolean enableChart = cbp.toggleOnEnableChart();
		TestValidation.IsTrue(enableChart, 
				  		      "Enabled Chart -" + CChart, 
				              "Could NOT Enable Chart");

		boolean setPrimaryRules = cbp.selectPrimaryChartRules(Arrays.asList("8","3","2","1"));
		TestValidation.IsTrue(setPrimaryRules, 
				  			  "Successfully set Primary Chart Rules 8,3,2,1", 
							  "Could NOT set primary chart rules");
		
		boolean clickSaveButton = cbp.clickSaveButton();
		TestValidation.IsTrue(clickSaveButton, 
							  "Clicked on Save Button", 
				  			  "Could NOT click on Save Button");
	}
	
	@Test(description = "Chart Builder || Verify user is able to disable the chart template")
	public void TestCase_37655()  {
		
		String sumChart = CommonMethods.dynamicText("sumChart");

		hp.clickChartBuilderMenu();
		
		boolean createchart = cbp.addNewChart(sumChart,ChartTypes.SUMCHART);
		TestValidation.IsTrue(createchart, 
				              "Chart Created a SumChart -" + sumChart, 
						      "Failed to create Chart");
		
		boolean disableChart = cbp.toggleOffEnableChart();
		TestValidation.IsTrue(disableChart, 
				  			  "Disabled Chart - " + sumChart, 
				  			  "FAILED to disable chart");
		
		boolean clickSave = cbp.clickSaveButton();
		TestValidation.IsTrue(clickSave, 
				  			  "Clicked on Saved Button", 
				              "FAILED to click on Save Button");
		
		String verifyDisable = cbp.getEnableChartToggleButtonStatus();
		TestValidation.IsTrue(verifyDisable.equals("OFF"), 
				              "Verified that the chart - " + sumChart + " is now disabled", 
				              "FAILED to verify that chart - " + sumChart + " is disabled");
	}
	
	@Test(description = "Chart Builder || Verify the \"CLEAR\" functionality")
	public void TestCase_37657() {
		
		// Original Values
		String XBarRChart = CommonMethods.dynamicText("XBarRChart");
		List<String> primaryRules = Arrays.asList("9","3","8");
		Set<String> primaryRulesSet = primaryRules.stream().collect(Collectors.toSet());
		
		List<String> secondaryRules = Arrays.asList("1","8","5");
		Set<String> secondaryRulesSet = secondaryRules.stream().collect(Collectors.toSet());
		
		List<String> primaryRules2 = Arrays.asList("1","2","4");
		List<String> secondaryRules2 = Arrays.asList("4");
		
		// to Edit i.e values to be changed to
		String XBarRChartEdit = "PotatoChart";
		List<String> primaryRulesEdit = Stream.concat(primaryRules.stream(), primaryRules2.stream()).collect(Collectors.toList());
		Set<String> primaryRulesEditSet = primaryRulesEdit.stream().collect(Collectors.toSet());
		
		List<String> secondaryRulesEdit = Stream.concat(secondaryRules.stream(), secondaryRules2.stream()).collect(Collectors.toList());
		Set<String> secondaryRulesEditSet = secondaryRulesEdit.stream().collect(Collectors.toSet());
		
		String fetchedChartName ;
		List<String> fetchedSelectedPrimaryRules;
		List<String> fetchedSelectedSecondaryRules;
		Set<String> fetchedSelectedPrimaryRulesSet;
		Set<String> fetchedSelectedSecondaryRulesSet;
		String fetchedEnableChartStatus ;
		
		hp.clickChartBuilderMenu();
		
		boolean clickAddNewChart = cbp.clickAddNewChartButton();
		TestValidation.IsTrue(clickAddNewChart, 
							  "Clicked on 'Add New Chart' button", 
				  			  "Could NOT click on 'Add New Chart' button");
		
		boolean openChartTypeDropDown = cbp.clickChartTypeDropDownButton();
		TestValidation.IsTrue(openChartTypeDropDown, 
				  			  "Clicked and Opened 'Chart Types' Drop Down List", 
				  			  "Could NOT Click and Open 'Chart Types' Drop Down List");
		
		boolean selectNpChart = cbp.selectChartType(ChartTypes.XBARR);
		TestValidation.IsTrue(selectNpChart, 
				  			  "Selected chart type - " + ChartTypes.XBARR, 
				  			  "Could NOT select chart type - " + ChartTypes.XBARR);
		
		boolean addChartname = cbp.addChartName(XBarRChart);
		TestValidation.IsTrue(addChartname, 
				  			  "Entered chart name as - " + XBarRChart, 
				  			  "Could NOT enter chart name");	
		
		boolean enableChart = cbp.toggleOnEnableChart();
		TestValidation.IsTrue(enableChart, 
				  		      "Enabled Chart -" + XBarRChart, 
				              "Could NOT Enable Chart");
		
		boolean setPrimaryRules = cbp.selectPrimaryChartRules(primaryRules);
		TestValidation.IsTrue(setPrimaryRules, 
				  			  "Successfully set Primary Chart Rules 9,3,8", 
							  "Could NOT set primary chart rules");
		
		boolean setSecondaryRules = cbp.selectSecondaryChartRules(secondaryRules);
		TestValidation.IsTrue(setSecondaryRules, 
				  			  "Successfully set Secondary Chart Rules 1,8,5", 
							  "Could NOT set Secodary chart rules");
		
		boolean clickSaveButton = cbp.clickSaveButton();
		TestValidation.IsTrue(clickSaveButton, 
							  "Clicked on Save Button", 
				  			  "Could NOT click on Save Button");
		
		hp.clickChartBuilderMenu();
		
		boolean selectChart = cbp.selectChartTemplate(XBarRChart);
		TestValidation.IsTrue(selectChart, 
				  			  "Selected Chart - " + XBarRChart, 
				              "Could NOT select chart - " + XBarRChart);
				
		//============ Do the Edit =================//
		boolean addChartnameEdit = cbp.addChartName(XBarRChartEdit);
		TestValidation.IsTrue(addChartnameEdit, 
				  			  "Entered New chart name as - " + XBarRChartEdit, 
				  			  "Could NOT enter New chart name");
		
		boolean disableChart = cbp.toggleOffEnableChart();
		TestValidation.IsTrue(disableChart, 
				  		      "Disabled Chart -" + XBarRChartEdit, 
				              "Could NOT Disable Chart");
		
		boolean setPrimaryRules2 = cbp.selectPrimaryChartRules(primaryRules2);
		TestValidation.IsTrue(setPrimaryRules2, 
				  			  "Successfully selected Primary Chart Rules 1,2,4 ", 
							  "Could NOT selected primary chart rules");
		
		boolean setSecondaryRules2 = cbp.selectSecondaryChartRules(secondaryRules2);
		TestValidation.IsTrue(setSecondaryRules2, 
				  			  "Successfully selected Secondary Chart Rules 4 ", 
							  "Could NOT selected Secodary chart rules");
		
		//============ Fetch Values After Edit ==================//
		fetchedChartName = cbp.getChartNameFromInputField();
		TestValidation.IsTrue(fetchedChartName.equals(XBarRChartEdit), 
							  "Verified that edited chart name is - " + XBarRChartEdit, 
							  "FAILED to verify edited chart name ");
		
		fetchedEnableChartStatus = cbp.getEnableChartToggleButtonStatus();
		TestValidation.IsTrue(fetchedEnableChartStatus.equals("OFF"), 
	              			  "Verified edited chart status is - OFF", 
	              			  "Failed to verify edited chart status as - OFF");
		
		fetchedSelectedPrimaryRules = cbp.getSelectedPrimaryRules();
		fetchedSelectedPrimaryRulesSet = fetchedSelectedPrimaryRules.stream().collect(Collectors.toSet());
		TestValidation.IsTrue(fetchedSelectedPrimaryRulesSet.equals(primaryRulesEditSet),
							  "Verified after edit selected primary rules are - " + fetchedSelectedPrimaryRulesSet,
							  "FAILED to verify after edit selected primary rules");
		
		fetchedSelectedSecondaryRules = cbp.getSelectedSecondaryRules();
		fetchedSelectedSecondaryRulesSet = fetchedSelectedSecondaryRules.stream().collect(Collectors.toSet());
		TestValidation.IsTrue(fetchedSelectedSecondaryRulesSet.equals(secondaryRulesEditSet),
							  "Verified after edit selected secondary rules are - " + fetchedSelectedSecondaryRulesSet,
				  			  "FAILED to verify after edit selected secondary rules");
		
		// ===========  Clear ================================//
		boolean clickClear = cbp.clickClearButton();
		TestValidation.IsTrue(clickClear, 
				  			  "Clicked Clear Button ", 
							  "Could NOT click Clear Button ");
		
		//============ Fetch Values After Clear ==================//
		fetchedChartName = cbp.getChartNameFromInputField();
		TestValidation.IsTrue(fetchedChartName.equals(XBarRChart), 
							  "Verified the chart name is reverted to -" + XBarRChart + " after clear", 
							  "FAILED to verify the effect of clear for chart name");
		
		fetchedEnableChartStatus = cbp.getEnableChartToggleButtonStatus();
		TestValidation.IsTrue(fetchedEnableChartStatus.equals("ON"), 
	              			  "Verified that chart Enabable status is reverted back to - ON after clear"  , 
	              			  "FAILED to verify the effect of clear on 'Enable Chart' status ");
		
		fetchedSelectedPrimaryRules = cbp.getSelectedPrimaryRules();
		fetchedSelectedPrimaryRulesSet = fetchedSelectedPrimaryRules.stream().collect(Collectors.toSet());
		TestValidation.IsTrue(fetchedSelectedPrimaryRulesSet.equals(primaryRulesSet),
							  "Verified that selected primary rules are reverted back to -" + fetchedSelectedPrimaryRulesSet,
							  "FAILED to verify the effect of clear on Primary Chart Rules ");
		
		fetchedSelectedSecondaryRules = cbp.getSelectedSecondaryRules();
		fetchedSelectedSecondaryRulesSet = fetchedSelectedSecondaryRules.stream().collect(Collectors.toSet());
		TestValidation.IsTrue(fetchedSelectedSecondaryRulesSet.equals(secondaryRulesSet),
							  "Verified that selected primary rules are reverted back to -" + fetchedSelectedSecondaryRulesSet,
				  			  "FAILED to verify the effect of clear on Secondary Chart Rules ");
		
	}
	
	@Test(description="Creation of Average chart")
	public void TestCase_37649() {

		String AverageChartNew = CommonMethods.dynamicText("AvergeChart101");

	//	hp.clickFSQABrowserMenu();
		hp.clickChartBuilderMenu();
		boolean createAveragechart = cbp.addNewChart(AverageChartNew,ChartTypes.AVERAGECHART);
		TestValidation.IsTrue(createAveragechart, 
							 "Average Chart Created successfully", 
							 "Failed to create Average Chart");
				
	}
	
	
	
//	@Test(description="Creation of chart")
//	public void TestCase_31890() {
//
//		String xbars = CommonMethods.dynamicText("XBARS");
//
//		hp.clickFSQABrowserMenu();
//		hp.clickChartBuilderMenu();
//		boolean createchart = cbp.addNewChart(xbars,ChartTypes.XBARS);
//		TestValidation.IsTrue(createchart, 
//				"Chart Created", 
//				"Failed to create Chart");
//	}

//	@Test(description="Creation of associated chart")
//	public void TestCase_31476() {
//
//
//		String ximr = CommonMethods.dynamicText("XIMR");
//
//		hp.clickFSQABrowserMenu();
//		hp.clickChartBuilderMenu();
//		boolean createchart = cbp.addNewChart(ximr,ChartTypes.XIMR);
//		TestValidation.IsTrue(createchart, 
//				"Chart Created", 
//				"Failed to create Chart");
//
//
//		hp.clickFSQABrowserMenu();
//		hp.clickFormsManagerMenu();
//		fmp.selectFormAndNavigaToCharts(formName);
//
//		boolean associatedchart = fmcp.createAssociateChart(ximr);
//		TestValidation.IsTrue(associatedchart, 
//				"Associated Chart Created", 
//				"Failed to create Associated Chart");
//
//	}
//
//	@Test(description="Linking field values to charts")
//	public void TestCase_34937() {
//
//		String sumchart =  CommonMethods.dynamicText("Sum");
//
//		hp.clickFSQABrowserMenu();
//		hp.clickChartBuilderMenu();
//		boolean createchart = cbp.addNewChart(sumchart,ChartTypes.SUMCHART);
//		TestValidation.IsTrue(createchart, 
//				"Chart Created", 
//				"Failed to create Chart");
//
//		hp.clickFSQABrowserMenu();
//		hp.clickFormsManagerMenu();
//		fmp.selectFormAndNavigaToCharts(formName);
//
//		boolean associatedchart = fmcp.createAssociateChart(sumchart);
//		TestValidation.IsTrue(associatedchart, 
//				"Associated Chart Created", 
//				"Failed to create Associated Chart");
//
//		fselect.SelectOneField = numeric2;
//		fselect.MultiSelect = Arrays.asList(numeric1,numeric2);
//
//		boolean linkfields = fmcp.linkFieldsToCharts(fselect);
//		TestValidation.IsTrue(linkfields, 
//				"Linked fields to charts", 
//				"Failed to Link fields to charts");
//	}
//
//
//	@Test(description="Selecting filters for chart")
//	public void TestCase_29327() {
//
//
//		String xbarr = CommonMethods.dynamicText("XBARR");
//
//		hp.clickFSQABrowserMenu();
//		hp.clickChartBuilderMenu();
//		boolean createchart = cbp.addNewChart(xbarr,ChartTypes.XBARR);
//		TestValidation.IsTrue(createchart, 
//				"Chart Created", 
//				"Failed to create Chart");
//
//		hp.clickFSQABrowserMenu();
//		hp.clickFormsManagerMenu();
//		fmp.selectFormAndNavigaToCharts(formName);
//
//		boolean associatedchart = fmcp.createAssociateChart(xbarr);
//		TestValidation.IsTrue(associatedchart, 
//				"Associated Chart Created", 
//				"Failed to create Associated Chart");
//
//		filter.lineselect = identifier1;
//		filter.shiftselect = identifier2;
//		filter.lineval = "LINE1";
//		filter.shiftval = "SHIFT1";
//
//		boolean filterselect = fmcp.selectFiltersforCharts(filter);
//		TestValidation.IsTrue(filterselect, 
//				"Linked fields to charts", 
//				"Failed to Link fields to charts");
//	}
//
//	@Test(description="Select resource for chart")
//	public void TestCase_34936() {
//
//		String uchart = CommonMethods.dynamicText("U");
//
//		hp.clickFSQABrowserMenu();
//		hp.clickChartBuilderMenu();
//		boolean createchart = cbp.addNewChart(uchart,ChartTypes.UCHART);
//		TestValidation.IsTrue(createchart, 
//				"Chart Created", 
//				"Failed to create Chart");
//
//		hp.clickFSQABrowserMenu();
//		hp.clickFormsManagerMenu();
//		fmp.selectFormAndNavigaToCharts(formName);
//
//		boolean associatedchart = fmcp.createAssociateChart(uchart);
//		TestValidation.IsTrue(associatedchart, 
//				"Associated Chart Created", 
//				"Failed to create Associated Chart");
//
//		res.resource = resourceInstanceValue;
//
//		boolean resourceselect = fmcp.selectResourceforCharts();
//		TestValidation.IsTrue(resourceselect, 
//				"Linked fields to charts", 
//				"Failed to Link fields to charts");
//
//
//	}
//
//	@Test(description="Calculate mean and variation")
//	public void TestCase_35054() {
//
//
//		String npchart = CommonMethods.dynamicText("NP");
//
//		hp.clickFSQABrowserMenu();
//		hp.clickChartBuilderMenu();
//		boolean createchart = cbp.addNewChart(npchart,ChartTypes.NPCHART);
//		TestValidation.IsTrue(createchart, 
//				"Chart Created", 
//				"Failed to create Chart");
//
//		hp.clickFSQABrowserMenu();
//		hp.clickFormsManagerMenu();
//		fmp.selectFormAndNavigaToCharts(formName);
//
//		boolean associatedchart = fmcp.createAssociateChart(npchart);
//		TestValidation.IsTrue(associatedchart, 
//				"Associated Chart Created", 
//				"Failed to create Associated Chart");
//
//		fselect.SelectOneField = numeric1;
//		fselect.MultiSelect = Arrays.asList(numeric1,numeric2);
//
//		boolean linkfields = fmcp.linkFieldsToCharts(fselect);
//		TestValidation.IsTrue(linkfields, 
//				"Linked fields to charts", 
//				"Failed to Link fields to charts");
//
//		filter.lineselect = identifier1;
//		filter.shiftselect = identifier2;
//		filter.lineval = "LINE1";
//		filter.shiftval = "SHIFT1";
//
//		boolean filterselect = fmcp.selectFiltersforCharts(filter);
//		TestValidation.IsTrue(filterselect, 
//				"Linked fields to charts", 
//				"Failed to Link fields to charts");
//
//		res.resource = resourceInstanceValue;
//
//		boolean resourceselect = fmcp.selectResourceforCharts();
//		TestValidation.IsTrue(resourceselect, 
//				"Linked fields to charts", 
//				"Failed to Link fields to charts");
//
//		boolean sampleinput = fmcp.samplesizeinput(ChartTypes.NPCHART, "300");
//		TestValidation.IsTrue(sampleinput, 
//				"Sample Input provided", 
//				"Failed to provide sample input");
//
//		output.mean = "4";
//		output.variation = "";
//
//		boolean calculate = fmcp.calculate(ChartTypes.NPCHART, "50");
//		TestValidation.IsTrue(calculate, 
//				"Sample Input provided", 
//				"Failed to provide sample input");
//
//
//	}
	
	@Test(description="Verify Chart Run Rules for XiMr chart type template")
	public void TestCase_37740() {

		String XiMrChart_37740 = CommonMethods.dynamicText("XiMrChart37740");

		hp.clickFSQABrowserMenu();
		hp.clickChartBuilderMenu();
		boolean createandviewXiMrchart = cbp.addNewChart(XiMrChart_37740,ChartTypes.XIMR);
		TestValidation.IsTrue(createandviewXiMrchart, 
				"XiMr Chart Created and viewed successfully", 
				"Failed to create and viewed XiMr Chart");
	}
	
	@Test(description="Verify Chart Run Rules for NpChart chart type template")
	public void TestCase_37741() {

		String NpChart1001 = CommonMethods.dynamicText("NpChart37741");

		hp.clickFSQABrowserMenu();
		hp.clickChartBuilderMenu();
		boolean createNpChart = cbp.addNewChart(NpChart1001,ChartTypes.NPCHART);
		TestValidation.IsTrue(createNpChart,
				"Np Chart Created and viewed successfully", 
				"Failed to create and view Np Chart");
	}

	//Tan
	@Test(description="Chart Builder || Verify the creation of new \"XiMr\" chart type")
	public void TestCase_37643() {

		String XiMrChart = CommonMethods.dynamicText("XiMrChart");
		
		hp.clickChartBuilderMenu();
		
		boolean clickAddNewChart = cbp.clickAddNewChartButton();
		TestValidation.IsTrue(clickAddNewChart, 
							  "Clicked on 'Add New Chart' button", 
				  			  "Could NOT click on 'Add New Chart' button");
		
		boolean openChartTypeDropDown = cbp.clickChartTypeDropDownButton();
		TestValidation.IsTrue(openChartTypeDropDown, 
				  			  "Clicked and Opened 'Chart Types' Drop Down List", 
				  			  "Could NOT Click and Open 'Chart Types' Drop Down List");
		
		boolean verifyAllChartTypeDropDown = cbp.verifyChartTypeDropDownList();
		TestValidation.IsTrue(verifyAllChartTypeDropDown, 
							  "Verified all chart types  displayed in \"CHART TYPE\" drop down list", 	
							  "Could NOT verify all chart types displayed in \"CHART TYPE\" drop down list");	
		
		boolean selectNpChart = cbp.selectChartType(ChartTypes.XIMR);
		TestValidation.IsTrue(selectNpChart, 
				  			  "Selected chart type - " + ChartTypes.XIMR, 
				  			  "Could NOT select chart type - " + ChartTypes.XIMR);
		
		boolean addChartname = cbp.addChartName(XiMrChart);
		TestValidation.IsTrue(addChartname, 
				  			  "Entered chart name as - " + XiMrChart, 
				  			  "Could NOT enter chart name");	
		
		boolean enableChart = cbp.toggleOnEnableChart();
		TestValidation.IsTrue(enableChart, 
				  		      "Enabled Chart -" + XiMrChart, 
				              "Could NOT Enable Chart");
		
		boolean setPrimaryRules = cbp.selectPrimaryChartRules(Arrays.asList("4","2","1","7"));
		TestValidation.IsTrue(setPrimaryRules, 
				  			  "Successfully set Primary Chart Rules 4,2,1,7", 
							  "Could NOT set primary chart rules");
		
		boolean setSecondaryRules = cbp.selectSecondaryChartRules(Arrays.asList("1","8","5"));
		TestValidation.IsTrue(setSecondaryRules, 
				  			  "Successfully set Secondary Chart Rules 1,8,5", 
							  "Could NOT set Secodary chart rules");
		
		boolean clickSaveButton = cbp.clickSaveButton();
		TestValidation.IsTrue(clickSaveButton, 
							  "Clicked on Save Button", 
				  			  "Could NOT click on Save Button");
	}
	
	@Test(description="Chart Builder || Verify \"X Bar Chart Run Rules\" & \"S Chart Run Rules\" for \"XBARS\" chart type template")
	public void TestCase_37742() {

		//String XBarRChart = CommonMethods.dynamicText("XBarRChart");
		
		hp.clickChartBuilderMenu();
		
		boolean clickAddNewChart = cbp.clickAddNewChartButton();
		TestValidation.IsTrue(clickAddNewChart, 
							  "Clicked on 'Add New Chart' button", 
				  			  "Could NOT click on 'Add New Chart' button");
		
		boolean openChartTypeDropDown = cbp.clickChartTypeDropDownButton();
		TestValidation.IsTrue(openChartTypeDropDown, 
				  			  "Clicked and Opened 'Chart Types' Drop Down List", 
				  			  "Could NOT Click and Open 'Chart Types' Drop Down List");
		
		boolean verifyAllChartTypeDropDown = cbp.verifyChartTypeDropDownList();
		TestValidation.IsTrue(verifyAllChartTypeDropDown, 
							  "Verified all chart types  displayed in \"CHART TYPE\" drop down list", 	
							  "Could NOT verify all chart types displayed in \"CHART TYPE\" drop down list");	
		
		boolean selectNpChart = cbp.selectChartType(ChartTypes.XBARS);
		TestValidation.IsTrue(selectNpChart, 
				  			  "Selected chart type - " + ChartTypes.XBARS, 
				  			  "Could NOT select chart type - " + ChartTypes.XBARS);
		
//		boolean addChartname = cbp.addChartName(XBarRChart);
//		TestValidation.IsTrue(addChartname, 
//				  			  "Entered chart name as - " + XBarRChart, 
//				  			  "Could NOT enter chart name");	
//		
//		boolean enableChart = cbp.toggleOnEnableChart();
//		TestValidation.IsTrue(enableChart, 
//				  		      "Enabled Chart -" + XBarRChart, 
//				              "Could NOT Enable Chart");
		
		boolean setPrimaryRules = cbp.selectPrimaryChartRules(Arrays.asList("1","2","3","4","5","6","7","8","9"));
		TestValidation.IsTrue(setPrimaryRules, 
				  			  "Successfully Verified \"X Bar Chart Run Rules\" : 1,2,3,4,5,6,7,8,9  for \"XBAS\" chart type template", 
							  "Could NOT Verify \"X Bar Chart Run Rules\"  for \"XBARS\" chart type template");
		
		boolean setSecondaryRules = cbp.selectSecondaryChartRules(Arrays.asList("1","4","8","5"));
		TestValidation.IsTrue(setSecondaryRules, 
							 "Successfully Verified \"S Chart Run Rules\" : 1,4,5,8  for \"XBARS\" chart type template", 
							 "Could NOT Verify \"S Chart Run Rules\"  for \"XBARS\" chart type template");
		
		boolean clickSaveButton = cbp.clickClearButton();
		TestValidation.IsTrue(clickSaveButton, 
							  "Clicked on Clear Button", 
				  			  "Could NOT click on Clear Button");
	}
	
	@Test(description="Chart Builder || Verify \"X Bar Chart Run Rules\" & \"R Chart Run Rules\" for \"XBARR\" chart type template")
	public void TestCase_37743() {

		//String XBarRChart = CommonMethods.dynamicText("XBarRChart");
		
		hp.clickChartBuilderMenu();
		
		boolean clickAddNewChart = cbp.clickAddNewChartButton();
		TestValidation.IsTrue(clickAddNewChart, 
							  "Clicked on 'Add New Chart' button", 
				  			  "Could NOT click on 'Add New Chart' button");
		
		boolean openChartTypeDropDown = cbp.clickChartTypeDropDownButton();
		TestValidation.IsTrue(openChartTypeDropDown, 
				  			  "Clicked and Opened 'Chart Types' Drop Down List", 
				  			  "Could NOT Click and Open 'Chart Types' Drop Down List");
		
		boolean verifyAllChartTypeDropDown = cbp.verifyChartTypeDropDownList();
		TestValidation.IsTrue(verifyAllChartTypeDropDown, 
							  "Verified all chart types  displayed in \"CHART TYPE\" drop down list", 	
							  "Could NOT verify all chart types displayed in \"CHART TYPE\" drop down list");	
		
		boolean selectNpChart = cbp.selectChartType(ChartTypes.XBARR);
		TestValidation.IsTrue(selectNpChart, 
				  			  "Selected chart type - " + ChartTypes.XBARR, 
				  			  "Could NOT select chart type - " + ChartTypes.XBARR);
		
//		boolean addChartname = cbp.addChartName(XBarRChart);
//		TestValidation.IsTrue(addChartname, 
//				  			  "Entered chart name as - " + XBarRChart, 
//				  			  "Could NOT enter chart name");	
//		
//		boolean enableChart = cbp.toggleOnEnableChart();
//		TestValidation.IsTrue(enableChart, 
//				  		      "Enabled Chart -" + XBarRChart, 
//				              "Could NOT Enable Chart");
		
		boolean setPrimaryRules = cbp.selectPrimaryChartRules(Arrays.asList("1","2","3","4","5","6","7","8","9"));
		TestValidation.IsTrue(setPrimaryRules, 
				  			  "Successfully Verified \"X Bar Chart Run Rules\" : 1,2,3,4,5,6,7,8,9  for \"XBARR\" chart type template", 
							  "Could NOT Verify \"X Bar Chart Run Rules\"  for \"XBARR\" chart type template");
		
		boolean setSecondaryRules = cbp.selectSecondaryChartRules(Arrays.asList("1","4","8","5"));
		TestValidation.IsTrue(setSecondaryRules, 
							 "Successfully Verified \"R Chart Run Rules\" : 1,4,5,8  for \"XBARR\" chart type template", 
							 "Could NOT Verify \"R Chart Run Rules\"  for \"XBARR\" chart type template");
		
		boolean clickClearButton = cbp.clickClearButton();
		TestValidation.IsTrue(clickClearButton, 
							  "Clicked on Clear Button", 
				  			  "Could NOT click on Clear Button");
	}

	@Test(description="Chart Builder || Verify \"Chart Run Rules\" for \"UChart\" chart type template")
	public void TestCase_37744() {

		//String UChart = CommonMethods.dynamicText("UChart");

		hp.clickChartBuilderMenu();
		
		boolean clickAddNewChart = cbp.clickAddNewChartButton();
		TestValidation.IsTrue(clickAddNewChart, 
							  "Clicked on 'Add New Chart' button", 
				  			  "Could NOT click on 'Add New Chart' button");
		
		boolean openChartTypeDropDown = cbp.clickChartTypeDropDownButton();
		TestValidation.IsTrue(openChartTypeDropDown, 
				  			  "Clicked and Opened 'Chart Types' Drop Down List", 
				  			  "Could NOT Click and Open 'Chart Types' Drop Down List");
		
		boolean verifyAllChartTypeDropDown = cbp.verifyChartTypeDropDownList();
		TestValidation.IsTrue(verifyAllChartTypeDropDown, 
							  "Verified all chart types  displayed in \"CHART TYPE\" drop down list", 	
							  "Could NOT verify all chart types displayed in \"CHART TYPE\" drop down list");	
		
		boolean selectNpChart = cbp.selectChartType(ChartTypes.UCHART);
		TestValidation.IsTrue(selectNpChart, 
				  			  "Selected chart type - " + ChartTypes.UCHART, 
				  			  "Could NOT select chart type - " + ChartTypes.UCHART);
		
//		boolean addChartname = cbp.addChartName(UChart);
//		TestValidation.IsTrue(addChartname, 
//				  			  "Entered chart name as - " + UChart, 
//				  			  "Could NOT enter chart name");	
//		
//		boolean enableChart = cbp.toggleOnEnableChart();
//		TestValidation.IsTrue(enableChart, 
//				  		      "Enabled Chart -" + UChart, 
//				              "Could NOT Enable Chart");
		
		boolean setPrimaryRules = cbp.selectPrimaryChartRules(Arrays.asList("1","2","3","4","5","6","7","8","9"));
		TestValidation.IsTrue(setPrimaryRules, 
				  			  "Successfully Verified \"Chart Run Rules\" : 1,2,3,4,5,6,7,8,9  for \"UChart\" chart type template", 
							  "Could NOT Verify \"Chart Run Rules\"  for  \"UChart\" chart type template");
		
		
		boolean clickClearButton = cbp.clickClearButton();
		TestValidation.IsTrue(clickClearButton, 
							  "Clicked on Clear Button", 
				  			  "Could NOT click on Clear Button");
	}
	
	@Test(description="Chart Builder || Verify \"Chart Run Rules\" for \"PChart\" chart type template")
	public void TestCase_37745() {

		//String PChart = CommonMethods.dynamicText("PChart");
		
		hp.clickChartBuilderMenu();
		
		boolean clickAddNewChart = cbp.clickAddNewChartButton();
		TestValidation.IsTrue(clickAddNewChart, 
							  "Clicked on 'Add New Chart' button", 
				  			  "Could NOT click on 'Add New Chart' button");
		
		boolean openChartTypeDropDown = cbp.clickChartTypeDropDownButton();
		TestValidation.IsTrue(openChartTypeDropDown, 
				  			  "Clicked and Opened 'Chart Types' Drop Down List", 
				  			  "Could NOT Click and Open 'Chart Types' Drop Down List");
		
		boolean verifyAllChartTypeDropDown = cbp.verifyChartTypeDropDownList();
		TestValidation.IsTrue(verifyAllChartTypeDropDown, 
							  "Verified all chart types  displayed in \"CHART TYPE\" drop down list", 	
							  "Could NOT verify all chart types displayed in \"CHART TYPE\" drop down list");	
		
		boolean selectNpChart = cbp.selectChartType(ChartTypes.PCHART);
		TestValidation.IsTrue(selectNpChart, 
				  			  "Selected chart type - " + ChartTypes.PCHART, 
				  			  "Could NOT select chart type - " + ChartTypes.PCHART);
		
//		boolean addChartname = cbp.addChartName(PChart);
//		TestValidation.IsTrue(addChartname, 
//				  			  "Entered chart name as - " + PChart, 
//				  			  "Could NOT enter chart name");	
//		
//		boolean enableChart = cbp.toggleOnEnableChart();
//		TestValidation.IsTrue(enableChart, 
//				  		      "Enabled Chart -" + PChart, 
//				              "Could NOT Enable Chart");
		
		boolean setPrimaryRules = cbp.selectPrimaryChartRules(Arrays.asList("1","2","3","4","5","6","7","8","9"));
		TestValidation.IsTrue(setPrimaryRules, 
				  			  "Successfully Verified \"Chart Run Rules\" : 1,2,3,4,5,6,7,8,9  for \"PChart\" chart type template", 
							  "Could NOT Verify \"Chart Run Rules\"  for  \"PChart\" chart type template");
		
		boolean clickClearButton = cbp.clickClearButton();
		TestValidation.IsTrue(clickClearButton, 
							  "Clicked on Clear Button", 
				  			  "Could NOT click on Clear Button");
	}
	
	@Test(description="Chart Builder || Verify \"Chart Run Rules\" for \"CChart\" chart type template")
	public void TestCase_37746() {

		//String CChart = CommonMethods.dynamicText("CChart");
		
		hp.clickChartBuilderMenu();
		
		boolean clickAddNewChart = cbp.clickAddNewChartButton();
		TestValidation.IsTrue(clickAddNewChart, 
							  "Clicked on 'Add New Chart' button", 
				  			  "Could NOT click on 'Add New Chart' button");
		
		boolean openChartTypeDropDown = cbp.clickChartTypeDropDownButton();
		TestValidation.IsTrue(openChartTypeDropDown, 
				  			  "Clicked and Opened 'Chart Types' Drop Down List", 
				  			  "Could NOT Click and Open 'Chart Types' Drop Down List");
		
		boolean verifyAllChartTypeDropDown = cbp.verifyChartTypeDropDownList();
		TestValidation.IsTrue(verifyAllChartTypeDropDown, 
							  "Verified all chart types  displayed in \"CHART TYPE\" drop down list", 	
							  "Could NOT verify all chart types displayed in \"CHART TYPE\" drop down list");	
		
		boolean selectNpChart = cbp.selectChartType(ChartTypes.CCHART);
		TestValidation.IsTrue(selectNpChart, 
				  			  "Selected chart type - " + ChartTypes.CCHART, 
				  			  "Could NOT select chart type - " + ChartTypes.CCHART);
		
//		boolean addChartname = cbp.addChartName(CChart);
//		TestValidation.IsTrue(addChartname, 
//				  			  "Entered chart name as - " + CChart, 
//				  			  "Could NOT enter chart name");	
//		
//		boolean enableChart = cbp.toggleOnEnableChart();
//		TestValidation.IsTrue(enableChart, 
//				  		      "Enabled Chart -" + CChart, 
//				              "Could NOT Enable Chart");

		boolean setPrimaryRules = cbp.selectPrimaryChartRules(Arrays.asList("1","2","3","4","5","6","7","8","9"));
		TestValidation.IsTrue(setPrimaryRules, 
				  			  "Successfully Verified \"Chart Run Rules\" : 1,2,3 ,4,5,6,7,8,9  for \"CChart\" chart type template", 
							  "Could NOT Verify \"Chart Run Rules\"  for  \"CChart\" chart type template");
		
		boolean clickClearButton = cbp.clickClearButton();
		TestValidation.IsTrue(clickClearButton, 
							  "Clicked on Clear Button", 
				  			  "Could NOT click on Clear Button");
	}

	@Test(description="Chart Builder || Verify user should get \"Pending Changes\" pop up if user tries to move to another screen without updating/clearing changes")
	public void TestCase_37747() {

		String avgChart = CommonMethods.dynamicText("AvgChart");
		
		String tempChartName = "Amigo";
		
		hp.clickChartBuilderMenu();
		
		boolean createAveragechart = cbp.addNewChart(avgChart,ChartTypes.AVERAGECHART);
		TestValidation.IsTrue(createAveragechart, 
							 "Average Chart Created successfully", 
							 "Failed to create Average Chart");
		
		boolean selectChart = cbp.selectChartTemplate(avgChart);
		TestValidation.IsTrue(selectChart, 
	  			  			  "Selected Chart - " + avgChart, 
	  			  			  "Could NOT select chart - " + avgChart);	
		
		boolean addChartname = cbp.addChartName(tempChartName);
		TestValidation.IsTrue(addChartname, 
				  			  "Entered chart name as - " + tempChartName, 
				  			  "Could NOT enter chart name");	
		
		hp.clickHomepageLink();
		
		boolean handlePendingChangesError = cbp.verifyAndHandlePendingChangesPopup();
		TestValidation.IsTrue(handlePendingChangesError, 
				  			  "Verified an error pop up for Pending Changes",
				  			  "Could NOT verify Pending Changes Error popup ");
		
		boolean clickClearButton = cbp.clickClearButton();
		TestValidation.IsTrue(clickClearButton, 
							  "Clicked on Clear Button", 
				  			  "Could NOT click on Clear Button");
		
		TestValidation.IsTrue(clickClearButton && handlePendingChangesError, 
				             "Verified user gets \"Pending Changes\" pop up when trying to move to another screen without updating/clearing changes", 
	  			  			 "Failed to verify 'Pending Changes Popup when trying to move to another screen without updating/clearing changes ");
	}

	@Test(description="Chart Builder || Verify user should not able to move to any other page if new chart template addition in progress")
	public void TestCase_38712() {

		String xbarrChart = CommonMethods.dynamicText("XbarRChart");
		
		hp.clickChartBuilderMenu();
		
		boolean clickAddNewChart = cbp.clickAddNewChartButton();
		TestValidation.IsTrue(clickAddNewChart, 
							  "Clicked on 'Add New Chart' button", 
				  			  "Could NOT click on 'Add New Chart' button");
		
		boolean openChartTypeDropDown = cbp.clickChartTypeDropDownButton();
		TestValidation.IsTrue(openChartTypeDropDown, 
				  			  "Clicked and Opened 'Chart Types' Drop Down List", 
				  			  "Could NOT Click and Open 'Chart Types' Drop Down List");
		
		boolean verifyAllChartTypeDropDown = cbp.verifyChartTypeDropDownList();
		TestValidation.IsTrue(verifyAllChartTypeDropDown, 
							  "Verified all chart types  displayed in \"CHART TYPE\" drop down list", 	
							  "Could NOT verify all chart types displayed in \"CHART TYPE\" drop down list");	
		
		boolean selectNpChart = cbp.selectChartType(ChartTypes.XBARR);
		TestValidation.IsTrue(selectNpChart, 
				  			  "Selected chart type - " + ChartTypes.XBARR, 
				  			  "Could NOT select chart type - " + ChartTypes.XBARR);
		
		boolean addChartname = cbp.addChartName(xbarrChart);
		TestValidation.IsTrue(addChartname, 
				  			  "Entered chart name as - " + xbarrChart, 
				  			  "Could NOT enter chart name");	
		
		hp.clickHomepageLink();
		
		boolean handlePendingChangesError = cbp.verifyAndHandlePendingChangesPopup();
		TestValidation.IsTrue(handlePendingChangesError, 
				  			  "Verified an error pop up for Pending Changes",
				  			  "Could NOT verify Pending Changes Error popup ");
		
		boolean clickClearButton = cbp.clickClearButton();
		TestValidation.IsTrue(clickClearButton, 
							  "Clicked on Clear Button", 
				  			  "Could NOT click on Clear Button");
		
		TestValidation.IsTrue(clickClearButton && handlePendingChangesError, 
				             "Verified user gets \"Pending Changes\" pop up when trying to move to another screen without updating/clearing changes", 
	  			  			 "Failed to verify 'Pending Changes Popup when trying to move to another screen without updating/clearing changes ");
	}

	
	@Test(description="Chart Builder || Verify user should not able to edit disabled chart template")
	public void TestCase_37666() {

		String NpChart = CommonMethods.dynamicText("NpChart");

		String newChartName = "Hola!";
		List<String> primaryRules = Arrays.asList("1","4","5");
		List<String> primaryRules2 = Arrays.asList("8","7","9");
		List<String> fetchedSelectedPrimaryRules;
		
		hp.clickChartBuilderMenu();
		
		boolean clickAddNewChart = cbp.clickAddNewChartButton();
		TestValidation.IsTrue(clickAddNewChart, 
							  "Clicked on 'Add New Chart' button", 
				  			  "Could NOT click on 'Add New Chart' button");
		
		boolean openChartTypeDropDown = cbp.clickChartTypeDropDownButton();
		TestValidation.IsTrue(openChartTypeDropDown, 
				  			  "Clicked and Opened 'Chart Types' Drop Down List", 
				  			  "Could NOT Click and Open 'Chart Types' Drop Down List");
		
		boolean selectNpChart = cbp.selectChartType(ChartTypes.NPCHART);
		TestValidation.IsTrue(selectNpChart, 
				  			  "Selected chart type - " + ChartTypes.NPCHART, 
				  			  "Could NOT select chart type - " + ChartTypes.NPCHART);
		
		boolean addChartname = cbp.addChartName(NpChart);
		TestValidation.IsTrue(addChartname, 
				  			  "Entered chart name as - " + NpChart, 
				  			  "Could NOT enter chart name");	
		
		boolean setPrimaryRules = cbp.selectPrimaryChartRules(primaryRules);
		TestValidation.IsTrue(setPrimaryRules, 
				  			  "Successfully set new Primary Chart Rules 1,4,5", 
							  "Could NOT set new primary chart rules");
		
		boolean clickSaveButton = cbp.clickSaveButton();
		TestValidation.IsTrue(clickSaveButton, 
							  "Clicked on Save Button", 
				  			  "Could NOT click on Save Button");
		
		hp.clickHomepageLink();
		hp.clickChartBuilderMenu();
		
		// SELECT DISABLED CHART 
		boolean selectChart = cbp.selectChartTemplate(NpChart);
		TestValidation.IsTrue(selectChart, 
				  			  "Selected Chart - " + NpChart, 
				              "Could NOT select chart - " + NpChart);
		
		// EDIT VALUES
		boolean addNewChartName = cbp.addChartName(newChartName);
		TestValidation.IsTrue(addNewChartName, 
				  			  "Entered new chart name as - " + NpChart, 
				  			  "Could NOT enter new chart name");
		
		boolean setNewPrimaryRules = cbp.selectPrimaryChartRules(primaryRules2);
		TestValidation.IsTrue(setNewPrimaryRules, 
				  			  "Successfully set new Primary Chart Rules 8,7,9", 
							  "Could NOT set new primary chart rules");
		
		//SAVE AND HANDLE ALERT
		boolean clickSaveButton2 = cbp.clickSaveButton();
		TestValidation.IsTrue(clickSaveButton2, 
							  "Clicked on Save Button", 
				  			  "Could NOT click on Save Button");
		
		boolean handleAlertPopup = cbp.verifyAndHandleAlertForDisableChartEdit();
		TestValidation.IsTrue(handleAlertPopup, 
				  			  "Verified an error pop up for Alert pop up after editing and saving a disabled chart",
				  			  "Could NOT verify Alert pop up after editing and saving a disabled chart");
		
		// ClEAR CHANGES
		boolean clickClearButton = cbp.clickClearButton();
		TestValidation.IsTrue(clickClearButton, 
							  "Clicked on Clear Button", 
				  			  "Could NOT click on Clear Button");
		
		// VERIFY PREVIOUS VALUES
		String fetchedChartName = cbp.getChartNameFromInputField();
		TestValidation.IsTrue(fetchedChartName.equals(NpChart), 
							  "Verified that chart name is not Updated and is  - " + NpChart, 
							  "FAILED to verify if chart name is updated or not ");
		
		fetchedSelectedPrimaryRules = cbp.getSelectedPrimaryRules();
		TestValidation.IsTrue(fetchedSelectedPrimaryRules.equals(primaryRules),
							  "Verified selected primary rules are not updated - " + fetchedSelectedPrimaryRules,
							  "FAILED to verify is selected primary rules are updated");
		
		TestValidation.IsTrue(clickClearButton && handleAlertPopup, 
				             "Verified User is not able to update & should get alert message - \"Disabled chart cannot be updated. Kindly Enable chart to update or clear changes.\"", 
	  			  			 "Failed to verify alert message - \"Disabled chart cannot be updated. Kindly Enable chart to update or clear changes.\"");
	}
	
	@Test(description="Chart Builder || Verify the \"Created\" & \"Last Modified\" label")
	public void TestCase_37669() throws Exception{

		hp.clickChartBuilderMenu();
		
		String aChart =  CommonMethods.dynamicText("AChart");
		String editName = CommonMethods.dynamicText("ANewChart");
		DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("HH:mm");
		
		boolean clickAddNewChart = cbp.clickAddNewChartButton();
		TestValidation.IsTrue(clickAddNewChart, 
							  "Clicked on 'Add New Chart' button", 
				  			  "Could NOT click on 'Add New Chart' button");
		
		boolean openChartTypeDropDown = cbp.clickChartTypeDropDownButton();
		TestValidation.IsTrue(openChartTypeDropDown, 
				  			  "Clicked and Opened 'Chart Types' Drop Down List", 
				  			  "Could NOT Click and Open 'Chart Types' Drop Down List");
		
		boolean selectNpChart = cbp.selectChartType(ChartTypes.SUMCHART);
		TestValidation.IsTrue(selectNpChart, 
				  			  "Selected chart type - " + ChartTypes.SUMCHART, 
				  			  "Could NOT select chart type - " + ChartTypes.SUMCHART);
		
		boolean addChartname = cbp.addChartName(aChart);
		TestValidation.IsTrue(addChartname, 
				  			  "Entered chart name as - " + aChart, 
				  			  "Could NOT enter chart name");	
		
		boolean enableChart = cbp.toggleOnEnableChart();
		TestValidation.IsTrue(enableChart, 
				  		      "Enabled Chart -" + aChart, 
				              "Could NOT Enable Chart");

		
		LocalDateTime createdDateTime =  LocalDateTime.now();
		boolean clickSaveButton = cbp.clickSaveButton();
		TestValidation.IsTrue(clickSaveButton, 
							  "Clicked on Save Button", 
				  			  "Could NOT click on Save Button");
		
		TimeUnit.MINUTES.sleep(1);
		
		hp.clickChartBuilderMenu();
		
		boolean selectChart = cbp.selectChartTemplate(aChart);
		TestValidation.IsTrue(selectChart, 
				  			  "Selected Chart - " + aChart, 
				              "Could NOT select chart - " + aChart);
		
		// EDIT VALUES
		boolean addNewChartName = cbp.addChartName(editName);
		TestValidation.IsTrue(addNewChartName, 
				  			  "Entered new chart name as - " + editName, 
				  			  "Could NOT enter new chart name");
		
		LocalDateTime modifiedDateTime =  LocalDateTime.now();
		boolean clickSaveButton2 = cbp.clickSaveButton();
		TestValidation.IsTrue(clickSaveButton2, 
							  "Clicked on Save Button", 
				  			  "Could NOT click on Save Button");
		
		String createdDate = createdDateTime.format(formatterDate);
		String createdTime = createdDateTime.format(formatterTime);
		
		String modifiedDate = modifiedDateTime.format(formatterDate);
		String modifiedTime = modifiedDateTime.format(formatterTime);
		
		String createdUser = cbp.getCreatedByUser();
		String modifiedUser = cbp.getModifiedByUser();
		
		String shouldBeCreatedMessage = String.format("Created on %s %s IST by %s", createdDate,createdTime,createdUser );
		String shouldBeModifiedMessage = String.format("Last modified on %s %s IST by %s", modifiedDate,modifiedTime,modifiedUser );
		
		String createdMsgFromApp = cbp.getCreatedByMsg();
		String ModifiedMsgFromApp = cbp.getModifiedByMsg();
		
		
		TestValidation.IsTrue(createdMsgFromApp.equals(shouldBeCreatedMessage), 
				  			  "Verified 'Created By..' for the Chart", 
				  		      "Failed to verify 'Created By...' For the Chart");
		
		TestValidation.IsTrue(ModifiedMsgFromApp.equals(shouldBeModifiedMessage), 
							  "Verified 'Modified By..' for the Chart", 
	  		      			  "Failed to verify 'Modified By...' For the Chart");
	}
	@AfterClass(alwaysRun = true)
	public void closeBrowser() throws InterruptedException {
		driver.close();
	}

}
