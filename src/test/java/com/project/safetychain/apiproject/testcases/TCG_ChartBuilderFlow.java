package com.project.safetychain.apiproject.testcases;

import java.util.Arrays;

import org.json.simple.parser.ParseException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.project.safetychain.apiproject.models.Auth;
import com.project.safetychain.apiproject.models.ChartBuilder;
import com.project.safetychain.apiproject.models.ChartBuilder.ChartParams;
import com.project.safetychain.apiproject.models.ChartBuilder.ChartRules;
import com.project.safetychain.apiproject.models.ChartBuilder.ChartTypeIDS;
import com.project.safetychain.apiproject.models.ChartBuilder.ChartTypes;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.utilities.ApiUtils;
import com.project.utilities.CommonMethods;
import com.project.utilities.JSONUtils;

public class TCG_ChartBuilderFlow  extends TestBase{

	Auth auth = null;
	ApiUtils apiUtils = null;
	ChartBuilder chartBuilder = null;
	JSONUtils jsonUtils = null;

	public ChartParams params1;

	String XiMRChartName = CommonMethods.dynamicText("API_XiMrChart");
	String NPChartName = CommonMethods.dynamicText("API_NpChart");
	String SumChartName = CommonMethods.dynamicText("API_SumChart");
	String XBarSChartName = CommonMethods.dynamicText("API_XBarSChart");

	@BeforeClass(alwaysRun = true)
	public void groupInit() {

		auth = new Auth();
		auth.setAccessToken();

		jsonUtils = new JSONUtils();
		apiUtils = new ApiUtils();

	}

	@Test(description = "Chart Builder || Create Chart of 'XiMr', 'NpChart' & 'SumChart' chart types")
	public void Create_Chart() throws InterruptedException, ParseException {

		chartBuilder = new ChartBuilder();

		//XiMr Chart type creation
		params1 = new ChartParams();
		params1.chartType = ChartTypes.XI_MR;
		params1.chartName = XiMRChartName;
		params1.chartRules = Arrays.asList(ChartRules.PRIMARY_RULE1, ChartRules.PRIMARY_RULE2, ChartRules.PRIMARY_RULE3, ChartRules.PRIMARY_RULE4, ChartRules.PRIMARY_RULE5, ChartRules.PRIMARY_RULE6,
				ChartRules.PRIMARY_RULE7, ChartRules.PRIMARY_RULE8, ChartRules.PRIMARY_RULE9, ChartRules.SECONDARY_RULE1, ChartRules.SECONDARY_RULE4, ChartRules.SECONDARY_RULE5, ChartRules.SECONDARY_RULE8);

		boolean createChart = chartBuilder.createChart(params1);
		TestValidation.IsTrue(createChart, 
				"CREATED chart - "+XiMRChartName, 
				"FAILED to create chart - "+XiMRChartName);

		//NpChart Chart type creation
		params1 = new ChartParams();
		params1.chartType = ChartTypes.NP_CHART;
		params1.chartName = NPChartName;
		params1.chartRules = Arrays.asList(ChartRules.PRIMARY_RULE1, ChartRules.PRIMARY_RULE2, ChartRules.PRIMARY_RULE3, ChartRules.PRIMARY_RULE4, ChartRules.PRIMARY_RULE5, ChartRules.PRIMARY_RULE6,
				ChartRules.PRIMARY_RULE7, ChartRules.PRIMARY_RULE8, ChartRules.PRIMARY_RULE9);

		createChart = chartBuilder.createChart(params1);
		TestValidation.IsTrue(createChart, 
				"CREATED chart - "+NPChartName, 
				"FAILED to create chart - "+NPChartName);

		//SumChart Chart type creation
		params1 = new ChartParams();
		params1.chartType = ChartTypes.SUM_CHART;
		params1.chartName = SumChartName;

		createChart = chartBuilder.createChart(params1);
		TestValidation.IsTrue(createChart, 
				"CREATED chart - "+SumChartName, 
				"FAILED to create chart - "+SumChartName);

	}

	@Test(description = "Chart Builder || Verify Chart Disable & Chart Enable")
	public void Disable_Enable_Chart() throws InterruptedException, ParseException {

		chartBuilder = new ChartBuilder();

		//XBarS Chart type creation
		params1 = new ChartParams();
		params1.chartType = ChartTypes.XBAR_S;
		params1.chartTypeID = ChartTypeIDS.XBAR_S;
		params1.chartName = XBarSChartName;
		params1.chartEnable = true;

		params1.chartRules = Arrays.asList(ChartRules.PRIMARY_RULE1, ChartRules.PRIMARY_RULE2, ChartRules.PRIMARY_RULE3, ChartRules.PRIMARY_RULE4, ChartRules.PRIMARY_RULE5, ChartRules.PRIMARY_RULE6,
				ChartRules.PRIMARY_RULE7, ChartRules.PRIMARY_RULE8, ChartRules.PRIMARY_RULE9, ChartRules.SECONDARY_RULE1, ChartRules.SECONDARY_RULE4, ChartRules.SECONDARY_RULE5, ChartRules.SECONDARY_RULE8);

		boolean createChart = chartBuilder.createChart(params1);
		TestValidation.IsTrue(createChart, 
				"CREATED chart - "+XBarSChartName, 
				"FAILED to create chart - "+XBarSChartName);

		String chartID = chartBuilder.getChartDetails(XBarSChartName, "Id");

		params1.chartID = chartID;
		params1.chartEnable = false;
		boolean disableChart = chartBuilder.enableChart(params1);
		TestValidation.IsTrue(disableChart, 
				"DISABLED chart - "+XBarSChartName, 
				"FAILED to disable chart - "+XBarSChartName);

		boolean chartEnableStatus = Boolean.getBoolean(chartBuilder.getChartDetails(XBarSChartName, "Id"));
		TestValidation.IsFalse(chartEnableStatus, 
				"VERIFIED chart status as Disabled", 
				"FAILED to verify chart status as Disabled");

		params1.chartEnable = true;
		boolean enableChart = chartBuilder.enableChart(params1);
		TestValidation.IsTrue(enableChart, 
				"ENABLED chart - "+XBarSChartName, 
				"FAILED to enable chart - "+XBarSChartName);

		chartEnableStatus = Boolean.getBoolean(chartBuilder.getChartDetails(XBarSChartName, "Id"));
		TestValidation.IsFalse(chartEnableStatus, 
				"VERIFIED chart status as Enabled", 
				"FAILED to verify chart status as Enabled");

	}


}
