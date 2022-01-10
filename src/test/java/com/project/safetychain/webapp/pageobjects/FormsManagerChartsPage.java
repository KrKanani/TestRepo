package com.project.safetychain.webapp.pageobjects;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.project.utilities.ControlActions;
import com.project.utilities.DateTime;

public class FormsManagerChartsPage extends CommonPage {
	ChartBuilderPageLocators chartbuilder_locators;
	WebDriver driver;
	WebDriverWait wait;
	ControlActions controlActions;
	public Actions action;
	public FormsManagerChartsPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 40);
		controlActions = new ControlActions(driver);
		chartbuilder_locators = new ChartBuilderPageLocators();
	}	


	@FindBy(xpath = FormsManagerChartsPageLocators.ASSOCIATE_BTN)
	public WebElement AssociatedBtn;

	@FindBy(xpath = FormsManagerChartsPageLocators.CHARTNAME_INPUT)
	public WebElement ChartNameInput;

	@FindBy(xpath = FormsManagerChartsPageLocators.SAVE_BTN)
	public WebElement SaveBtn;
	
	@FindBy(xpath = FormsManagerChartsPageLocators.ASSOCIATE_NEW_CHART_SAVE_BTN)
	public WebElement AssociateChartSaveBtn;

	@FindBy(xpath = FormsManagerChartsPageLocators.SELECT_MULTI_FIELDS)
	public List<WebElement> SelectFields;

	@FindBy(xpath = FormsManagerChartsPageLocators.CALCULATE_BTN)
	public WebElement CalculateBtn;

	@FindBy(xpath = FormsManagerChartsPageLocators.APPLY_BTN)
	public WebElement ApplyBtn;

	@FindBy(xpath = FormsManagerChartsPageLocators.SEARCH_INPUT)
	public WebElement SearchInput;
	
	
	@FindBy(xpath = FormsManagerChartsPageLocators.FM_OVERVIEW_CHARTS_TAB)
	public WebElement fmOverviewChartsTab ;
	
	@FindBy(xpath = FormsManagerChartsPageLocators.FM_CHART_POPUP_OK_BUTTON)
	public WebElement fmChartPopUpOKButton ;
	
	@FindBy(xpath = FormsManagerChartsPageLocators.FM_CHART_CLOSE)
	public WebElement fmChartClose ;
	
	
	@FindBy(xpath = FormsManagerChartsPageLocators.FM_CHART_DELETE_CHART_BUTTON)
	public WebElement fmChartDeleteChart ;
	
	@FindBy(xpath = FormsManagerChartsPageLocators.FM_CHART_RESOURCE_LOCATION)
	public WebElement fmChartResourceLocation;
	
	@FindBy(xpath = FormsManagerChartsPageLocators.FM_CHART_MEAN_UP_ARROW)
	public WebElement FMChartsMeanUpArrow;
	
	@FindBy(xpath = FormsManagerChartsPageLocators.FM_CHART_VARIANCE_UP_ARROW)
	public WebElement FMChartsVarianceUpArrow;
	
	@FindBy(xpath = FormsManagerChartsPageLocators.FM_CHART_SELECT_RESOURCE)
	public WebElement FMChartSelectResource;
	
	@FindBy(xpath = FormsManagerChartsPageLocators.COLUMN_NAMES_TBL)
	public List<WebElement> ColumnNamesTbl;
	
	@FindBy(xpath = FormsManagerPageLocators.SEARCH_FORMNAME_INPUT)
	public WebElement SearchFormnameInput;
	
		
	@FindBy(xpath = FormsManagerChartsPageLocators.FM_RESOURCETAB_SAVE_BTN)
	public WebElement FMResourceTabSaveBtn;
	
	@FindBy(xpath = FormsManagerChartsPageLocators.FM_RESOURCETAB_FORM_CLOSE_BUTTON)
	public WebElement fmResourceTabFormCloseButton;
	
	@FindBy(xpath = FormsManagerChartsPageLocators.FM_CHART_RESOURCES_DEFAULT_MEAN_UP_ARROW)
	public WebElement fmChartResourcesDefaultMeanUpArrow;
	
	@FindBy(xpath = FormsManagerChartsPageLocators.FM_CHART_RESOURCES_DEFAULT_VARIANCE_UP_ARROW)
	public WebElement fmChartResourcesDefaultVarianceUpArrow;
	 
	@FindBy(xpath = FormsManagerChartsPageLocators.FM_CHART_RESOURCES_DEFAULT_CHECKBOX)
	public WebElement fmChartResourcesDefaultCheckbox;
	
	@FindBy(xpath = FormsManagerChartsPageLocators.FM_CHART_RESOURCES_DEFAULTCHECKPOPUP)
	public WebElement fmChartResourcesDefaultChecKPopUp;
	
	@FindBy(xpath = FormsManagerChartsPageLocators.FM_CHART_RESOURCES_DEFAULT_COMMENT)
	public WebElement fmChartResourcesDefaultComment;
	
	@FindBy(xpath = FormsManagerChartsPageLocators.FM_CHART_RESOURCES_SAMPLESIZE_UP_ARROW)
	public WebElement fmChartResourcesSampleSizeUpArrow;
	
	@FindBy(xpath = FormsManagerChartsPageLocators.BY_DATE)
	public WebElement byDate ;
	

	@FindBy(xpath = FormsManagerChartsPageLocators.START_DATE_TEXT)
	public WebElement startDateText ;
	
	@FindBy(xpath = FormsManagerChartsPageLocators.END_DATE_TEXT)
	public WebElement endDateText ;
	
	@FindBy(xpath = FormsManagerChartsPageLocators.START_TIME)
	public WebElement startTime ;

	@FindBy(xpath = FormsManagerChartsPageLocators.END_TIME)
	public WebElement endTime ;

	

	/**
	 * This method is used to create associated chart 
	 * @author choubey_a
	 * @param formname
	 * @return boolean This returns true when the associated chart will be created
	 */


	public boolean createAssociateChart(String chart) {
		try {

			controlActions.clickOnElement(AssociatedBtn);
			Thread.sleep(3000);
			String selector = "kendo-dropdownlist[formcontrolname='ChartTemplateType'] > span";
			String chartselect = controlActions.perform_GetDynamicXPath(FormsManagerChartsPageLocators.VALUE_SELECT, "VALUE", chart);
			controlActions.setKendoDropDownValue(driver,selector,chartselect);
			Thread.sleep(3000);
			controlActions.clickOnElement(ChartNameInput);
			controlActions.actionSendKeys(ChartNameInput, chart);
			Thread.sleep(3000);
			controlActions.clickOnElement(AssociateChartSaveBtn);
			Sync();	
			logInfo("Associated Chart created with chart type - " +chart );
			return true;
		}
		catch(Exception e) {
			logError("Failed to create associated chart with chart type - " +chart
					+ e.getMessage());
			return false;
		}	

	}

	/**
	 * This method is used to link chart to fields - both single and multi select 
	 * @author choubey_a
	 * @param fselect
	 * @return boolean This returns true when the chart will be linked with the field
	 */

	public boolean linkFieldsToCharts(FieldSelection fselect) {
		try {
			WebElement SubTabOption = controlActions.perform_GetElementByXPath(FormsManagerChartsPageLocators.VALUE_SELECT,"VALUE",SubTabs.FIELDS);
			controlActions.clickOnElement(SubTabOption);
			Sync();
			//WebElement header = controlActions.perform_GetElementByXPath(FormsManagerChartsPageLocators.FIELDS_HEADER,"HEADER",FieldInputTypeHeader.MULTIPLEFIELD);
			//WebElement header1 = controlActions.perform_GetElementByXPath(FormsManagerChartsPageLocators.FIELDS_HEADER,"HEADER",FieldInputTypeHeader.SINGLEFIELD);

			String header = controlActions.perform_GetDynamicXPath(FormsManagerChartsPageLocators.FIELDS_HEADER,"HEADER",FieldInputTypeHeader.MULTIPLEFIELD);

			if(controlActions.isElementDisplayed(header)) {
				for(int i=0;i<FieldSelection.MultiSelect.size();i++) {
					WebElement multi = controlActions.perform_GetElementByXPath(FormsManagerChartsPageLocators.SELECT_MULTI_FIELDS,"FIELDNAME",FieldSelection.MultiSelect.get(i));
					controlActions.perform_ClickWithJavaScriptExecutor(multi);
					Sync();
				}
			}else {
				String selector = "kendo-dropdownlist > span";
				@SuppressWarnings("unused")
				WebElement singlevalue = controlActions.perform_GetElementByXPath(FormsManagerChartsPageLocators.VALUE_SELECT,"VALUE",FieldSelection.SelectOneField);
				String value = controlActions.perform_GetDynamicXPath(FormsManagerChartsPageLocators.VALUE_SELECT,"VALUE",FieldSelection.SelectOneField);
				controlActions.setKendoDropDownValue(driver,selector,value);
				Sync();
			}

			controlActions.clickOnElement(SaveBtn);
			Sync();
			logInfo("Linked fields to chart");
			return true;
		}catch(Exception e) {
			logError("Failed to link chart to fileds - "
					+ e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to select filters for the chart
	 * @author choubey_a
	 * @param filter
	 * @return boolean This returns true when the filters will be selected
	 */

	public boolean selectFiltersforCharts(FilterSelection filter) {
		try {
			WebElement SubTabOption = controlActions.perform_GetElementByXPath(FormsManagerChartsPageLocators.VALUE_SELECT,"VALUE",SubTabs.FILTERS);
			controlActions.clickOnElement(SubTabOption);
			Sync();
			WebElement lineidn = controlActions.perform_GetElementByXPath(FormsManagerChartsPageLocators.FILTER_DRD,"VALUE",FilterSelection.LineIdentifier);
			controlActions.click(lineidn);
			SearchInput.sendKeys(FilterSelection.lineselect);
			controlActions.actionEnter();
			Thread.sleep(2000);
			WebElement linevalue = controlActions.perform_GetElementByXPath(FormsManagerChartsPageLocators.FILTER_DRD,"VALUE",FilterSelection.LineValue);
			controlActions.click(linevalue);
			SearchInput.sendKeys(FilterSelection.lineval);
			controlActions.actionEnter();
			Thread.sleep(2000);
			WebElement shiftidn = controlActions.perform_GetElementByXPath(FormsManagerChartsPageLocators.FILTER_DRD,"VALUE",FilterSelection.ShiftIdentifier);
			controlActions.click(shiftidn);
			SearchInput.sendKeys(FilterSelection.shiftselect);
			controlActions.actionEnter();
			Thread.sleep(2000);
			WebElement shiftvalue = controlActions.perform_GetElementByXPath(FormsManagerChartsPageLocators.FILTER_DRD,"VALUE",FilterSelection.ShiftValue);
			controlActions.click(shiftvalue);
			SearchInput.sendKeys(FilterSelection.shiftval);
			controlActions.actionEnter();
			Sync();
			controlActions.clickOnElement(SaveBtn);
			Sync();
			logInfo("Filters selected for chart");
			return true;
		}catch(Exception e) {
			logError("Failed to select filters - "
					+ e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to select resource for the chart
	 * @author choubey_a
	 * @return boolean This returns true when the resource is selected for chart
	 */

	public boolean selectResourceforCharts() {
		try {
			WebElement SubTabOption = controlActions.perform_GetElementByXPath(FormsManagerChartsPageLocators.VALUE_SELECT,"VALUE",SubTabs.RESOURCES);
			controlActions.clickOnElement(SubTabOption);
			Sync();	
			//WebElement Chart = controlActions.perform_GetElementByXPath(FormsManagerChartsPageLocators.ASSOCIATED_CHART,"ASSOCIATEDCHART",ChartName);
			//controlActions.clickOnElement(Chart);
			WebElement resourceselect = controlActions.perform_GetElementByXPath(FormsManagerChartsPageLocators.RESOURCE_SELECT,"RESOURCE",ResourceSelection.resource);
			controlActions.perform_ClickWithJavaScriptExecutor(resourceselect);	
			Sync();
			controlActions.clickOnElement(SaveBtn);
			Sync();
			logInfo("Resource selected for chart");
			return true;			
		}catch(Exception e) {
			logError("Resource not selected for charts");
			return false;
		}
	}

	/**
	 * This method is used to select resource for the chart
	 * @author choubey_a
	 * @param - charttype 
	 * @param - samplesizevalue
	 * @return boolean This returns true when the resource is selected for chart
	 */

	public boolean samplesizeinput(String charttype,String samplesizevalue) {

		try {
			WebElement samplesize = controlActions.perform_GetElementByXPath(FormsManagerChartsPageLocators.SAMPLE_SIZE_INPUT,"RESOURCE",ResourceSelection.resource);
			switch(charttype) {
			case "XiMr":
				break;
			case "NpChart":
				controlActions.WaitforelementToBeClickable(samplesize);
				controlActions.sendKeys(samplesize,samplesizevalue);
				break;
			case "XBarS":
				break;
			case "XBarR":
				break;
			case "SumChart":
				break;
			case "UChart":
				controlActions.WaitforelementToBeClickable(samplesize);
				controlActions.sendKeys(samplesize,samplesizevalue);
				break;
			case "PChart":
				controlActions.WaitforelementToBeClickable(samplesize);
				controlActions.sendKeys(samplesize,samplesizevalue);
				break;
			case "CChart":
				break;
			case "AverageChart":
				break;
			default:
				logError("Selected Chart is INVALID");
				return false;
			}
			controlActions.clickOnElement(SaveBtn);
			Sync();
			logInfo("Sample size provided for the chart");
			return true;
		}catch(Exception e) {
			logError("Failed to provide sample size" +e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to calculate mean and variation
	 * @author choubey_a
	 * @param - charttype 
	 * @param - count
	 * @return boolean This returns true when the mean and variation will be calculated
	 */

	public boolean calculate(String charttype,String count) {
		try {
		//	 int a =1;
			WebElement calculator = controlActions.perform_GetElementByXPath(FormsManagerChartsPageLocators.CALCULATOR_BTN, "RESOURCE", ResourceSelection.resource);
			controlActions.clickOnElement(calculator);
			Sync();
			String countselect = "#scs-record-count-dropdown > kendo-dropdownlist > span";
			String recordcount = controlActions.perform_GetDynamicXPath(FormsManagerChartsPageLocators.COUNT_NUMBER,"COUNT", count);
			controlActions.setKendoDropDownValue(driver, countselect, recordcount);
			Thread.sleep(3000);
			controlActions.WaitforelementToBeClickable(CalculateBtn);
			controlActions.clickOnElement(CalculateBtn);
			Thread.sleep(3000);
			controlActions.WaitforelementToBeClickable(ApplyBtn);
			controlActions.clickOnElement(ApplyBtn);
			Sync();
			WebElement meancolumn = controlActions.perform_GetElementByXPath(FormsManagerChartsPageLocators.MEAN_INPUT, "RESOURCE", ResourceSelection.resource);
			WebElement variationcolumn = controlActions.perform_GetElementByXPath(FormsManagerChartsPageLocators.VARIATION_INPUT, "RESOURCE", ResourceSelection.resource);
			switch(charttype) {
			case "XiMr":
				if(!(OutputValues.mean == meancolumn.getAttribute("aria-valuenow") && (OutputValues.variation == variationcolumn.getAttribute("aria-valuenow")))){
					logError("Mean and variation not calulated accurately");
				}
				break;
			case "NpChart":
				if(!(OutputValues.mean == meancolumn.getAttribute("aria-valuenow"))){
					logError("Mean not calculate accurately");
				}
				break;
			case "XBarS":
				if(!(OutputValues.mean == meancolumn.getAttribute("aria-valuenow") && (OutputValues.variation == variationcolumn.getAttribute("aria-valuenow")))){
					logError("Mean and variation not calulated accurately");
				}
				break;
			case "XBarR":
				if(!(OutputValues.mean == meancolumn.getAttribute("aria-valuenow") && (OutputValues.variation == variationcolumn.getAttribute("aria-valuenow")))){
					logError("Mean and variation not calulated accurately");
				}
				break;
			case "SumChart":
				if(!(OutputValues.mean == meancolumn.getAttribute("aria-valuenow"))){
					logError("Mean not calulated accurately");
				}
				break;
			case "UChart":
				if(!(OutputValues.mean == meancolumn.getAttribute("aria-valuenow"))){
					logError("Mean not calulated accurately");
				}
				break;
			case "PChart":
				if(!(OutputValues.mean == meancolumn.getAttribute("aria-valuenow"))){
					logError("Mean not calulated accurately");
				}
				break;
			case "CChart":
				if(!(OutputValues.mean == meancolumn.getAttribute("aria-valuenow"))){
					logError("Mean not calulated accurately");
				}
				break;
			case "AverageChart":
				break;
			default:
				logError("Selected Chart is INVALID");
				return false;
			}
			controlActions.clickOnElement(SaveBtn);
			Sync();
			logInfo("Mean nad Variation calculated");
			return true;
		}catch (Exception e) {
			logError("Failed to calculate mean and variation  " +e.getMessage());
			return false;
		}
	}


	/**
	 * This method is used to click on Chart button.
	 * @author dahale_p
	 * @param none
	 * @return boolean This returns true if CHART button is clicked.
	 */
	public boolean clickChartMenu() { 
		try {
			Sync();
			controlActions.WaitforelementToBeClickable(fmOverviewChartsTab);
			Sync();
			controlActions.clickOnElement(fmOverviewChartsTab); 
			logInfo("Clicked on CHART Menu");
			Sync();
			return true;
		}
		catch(Exception e) {
			logError("Failed to click on CHART Menu "
					+ e.getMessage());
			return false;
		}		
	}
	
	
	/**
	 * This method is used to click on CHART menu button.
	 * @author dahale_p
	 * @param none
	 * @return boolean This returns true if CHART button is clicked.
	 */
	public boolean clickChartClose() { 
		try {
			controlActions.WaitforelementToBeClickable(fmOverviewChartsTab);
			Sync();
			controlActions.clickOnElement(fmOverviewChartsTab);
			logInfo("Clicked on close button to Close CHART Menu");
			Sync();
			return true;
		}
		catch(Exception e) {
			logError("Failed to click close button on CHART Menu "
					+ e.getMessage());
			return false;
		}		
	}
	
	
	/**
	 * This method is used to click OK button on Popup menu button.
	 * @author dahale_p
	 * @param none
	 * @return boolean This returns true if CHART button is clicked.
	 */
	public boolean clickOkPopup() { 
		try {
			controlActions.WaitforelementToBeClickable(fmChartPopUpOKButton);
			Sync();
			controlActions.clickOnElement(fmChartPopUpOKButton);
			logInfo("Clicked on Ok button of Pop up CHART Menu");
			Sync();
			return true;
		}
		catch(Exception e) {
			logError("Failed to click ok button on POPUP Menu "
					+ e.getMessage());
			return false;
		}		
	}
	
	
	/**
	 * This method is used to click OK button on Popup menu button.
	 * @author dahale_p
	 * @param none
	 * @return boolean This returns true if CHART button is clicked.
	 */
	public boolean deleteChart() { 
		try {
			controlActions.WaitforelementToBeClickable(fmChartDeleteChart);
			Sync();
			controlActions.clickOnElement(fmChartDeleteChart);
			logInfo("Clicked on Delete Chart button ");
			Sync();
			return true;
		}
		catch(Exception e) {
			logError("Failed to click on Delete Chart button "
					+ e.getMessage());
			return false;
		}		
	}
	
	
	
		
	/**
	 * This method is used to open a Chart for the selected form
	 * @author hingorani_a
	 * @param chartName The name of Chart to be opened
	 * @return boolean This returns true if mentioned Chart is opened.
	 */
	public boolean selectChartForForm(String chartName) { 
		WebElement SelectChartForForm = null;
		try {
			
			SelectChartForForm = controlActions.perform_GetElementByXPath(FormsManagerChartsPageLocators.SELECT_CHART_FOR_FORM, 
					"CHART_NAME", chartName);
			SelectChartForForm.click();
			Sync();
			logInfo("Clicked on Chart " + chartName);
			return true;
		}
		catch(Exception e) {
			logError("Failed to click on CHART " + chartName
					+ e.getMessage());
			return false;
		}		
	}
	
	/**
	 * This method is used to Select a tab from Charts menu like Details, Fields, Filters or Resources
	 * @author hingorani_a
	 * @param tabOption Use Class SubTabs to set the tab to be clicked
	 * @return boolean This returns true when the tab is selected for chart
	 */
	public boolean selectChartsTab(String tabOption) {
		try {
			WebElement SubTabOption = controlActions.perform_GetElementByXPath(FormsManagerChartsPageLocators.VALUE_SELECT,
					"VALUE", tabOption);
			controlActions.clickOnElement(SubTabOption);
			Sync();	
			logInfo("Selected Charts tab " + tabOption);
			return true;			
		}catch(Exception e) {
			logError("Failed to Select Charts tab " + tabOption
					+ e.getMessage());
			return false;
		}
	}
	
	/**
	 * This method is used to find Column Index for the column present in Forms Manager Charts Resource Grid
	 * @author hingorani_a
	 * @param columnName Column name whose Index you need. Use Class CHARTS_FIELDS to pass column name.
	 * This Index is in turn used in creating dynamic xpaths. 
	 * @return int This returns value of column index. 
	 * Returns 0, if column is not found
	 */
	public int getColumnIndex(String columnName) {
		int columnIndex = 0; 
		try {
			
			if(columnName.contains(CHARTS_FIELDS.RESOURCE_SELECT_CHK)) {
				columnIndex = 1;
				return columnIndex;
			}
				
			for(WebElement column : ColumnNamesTbl) {
				if(controlActions.perform_CheckIfElementTextContains(column, columnName)) 
				{
					columnIndex++;
					break;
				}
				else if (controlActions.perform_CheckIfElementTextEquals(column, "")) {
					//Do nothing
				}
				else {
					columnIndex++;
				}
			}		
			logInfo("For column : " + columnName + " the index is : " + columnIndex);
			return columnIndex;
		}
		catch(Exception e) {
			logError("Failed to get index value for column : " + columnName + " - "
					+ e.getMessage());
			return columnIndex;
		}	
	}
	
	/**
	 * This method is used to search and get Chart detail as per column
	 * @author hingorani_a
	 * @param columnName Column name whose Index you need. Use Class CHARTS_FIELDS to pass column name.
	 * @return String This returns string value with the chart detail if found; else null
	 */
	public String getChartResourceGridDetails(String columnName, String resourceName) {
		String tempXpath = null; 
		WebElement ResourceGridInputVal = null;
		String value = null;
		
		try {
			int columnIndex = getColumnIndex(columnName);
			if(columnIndex == 0) {
				logError("Failed to get correct column index : " + columnIndex + " for column " + columnName);
				return null;
			}
			else {
				
				if(columnName.contains(CHARTS_FIELDS.RESOURCE_SELECT_CHK)) 
					columnIndex = 1;
				else if(columnName.contains(CHARTS_FIELDS.DEFAULT))
					columnIndex += 1;
				else
					columnIndex += 2;
						
				tempXpath = controlActions.perform_GetDynamicXPath(FormsManagerChartsPageLocators.RESOURCE_GRID_INPUT_VAL, 
						"COLUMNINDEXNO", Integer.toString(columnIndex));
				
				ResourceGridInputVal = controlActions.perform_GetElementByXPath(tempXpath, 
						"RESOURCE_NAME", resourceName);
				
				if(columnName.equalsIgnoreCase(CHARTS_FIELDS.RESOURCE_SELECT_CHK)
						|| columnName.equalsIgnoreCase(CHARTS_FIELDS.DEFAULT)) {
					if(ResourceGridInputVal.isSelected()) 
						value = "true";
					else
						value = "false";
				}
				else 
					value = ResourceGridInputVal.getAttribute("aria-valuenow");
				
				logInfo("For column : " + columnName + " value retrieved as - " + value);
				return value;
			}
		}
		catch(Exception e) {
			logError("Failed to get value for column : " + columnName + " - "
					+ e.getMessage());
			return null;
		}	
	}
	
	/**
	 * This method is used to search and get Chart detail as per header settings - the default ones
	 * @author hingorani_a
	 * @param columnName Column name whose Index you need. Use Class CHARTS_FIELDS to pass column name.
	 * @return String This returns string value with the chart detail if found; else null
	 */
	public String getChartResourceHeaderDetails(String columnName) {
		WebElement ResourceHeaderInputVal = null;
		String value = null;
		
		try {
			int columnIndex = getColumnIndex(columnName);
			if(columnIndex == 0) {
				logError("Failed to get correct column index : " + columnIndex + " for column " + columnName);
				return null;
			}
			else {
				
				if(columnName.contains(CHARTS_FIELDS.RESOURCE_SELECT_CHK)) 
					columnIndex = 1;
				else
					columnIndex += 1;
				
				ResourceHeaderInputVal = controlActions.perform_GetElementByXPath(FormsManagerChartsPageLocators.RESOURCE_HEADER_INPUT_VAL, 
						"COLUMNINDEXNO", Integer.toString(columnIndex+1));
				
				if(columnName.equalsIgnoreCase(CHARTS_FIELDS.DEFAULT)) {
					if(ResourceHeaderInputVal.isSelected()) 
						value = "true";
					else
						value = "false";
				}
				else 
					value = ResourceHeaderInputVal.getAttribute("aria-valuenow");
					
				logInfo("For column : " + columnName + " value retrieved as - " + value);
				return value;
			}
		}
		catch(Exception e) {
			logError("Failed to get value for column : " + columnName + " - "
					+ e.getMessage());
			return null;
		}	
	}
	
	public boolean increamentMean(int mean, String resourceName) {
		WebElement FMChartMeanUpArrow = null;
		
		
		try {
			Sync();
			
			FMChartMeanUpArrow = controlActions.perform_GetElementByXPath(FormsManagerPageLocators.FM_CHART_MEAN_UP_ARROW, 
					"RESOURCE_NAME", resourceName);
			
			Sync();	
			//set every day value by clicking on upp arrow
			logInfo("Clicking Up arrow to set Mean value equal to "+ mean );
			for(int i=1;i<mean;i++)
			{
				controlActions.click(FMChartMeanUpArrow);
			}

			controlActions.clickOnElement(FMResourceTabSaveBtn);
			Sync();
			logInfo("Mean Has been Incremented");
			return true;
			} catch (Exception e) {
			logError("Failed to set mean - " + e.getMessage());
			return false;
			}
	}
		
		public boolean increamentVariance(int variance,String resourceName) {
			WebElement FMChartVarianceUpArrow = null;
			try {
				FMChartVarianceUpArrow = controlActions.perform_GetElementByXPath(FormsManagerPageLocators.FM_CHART_VARIANCE_UP_ARROW, 
						"RESOURCE_NAME", resourceName);
			Sync();	
				
				logInfo("Clicking Up arrow to set Variance value equal to "+ variance );
				for(int i=1;i<variance;i++)
				{
					controlActions.click(FMChartVarianceUpArrow);
				}

				controlActions.clickOnElement(FMResourceTabSaveBtn);
				Sync();
				logInfo("Variance Has been Incremented");
				return true;
				} catch (Exception e) {
				logError("Failed to set mean - " + e.getMessage());
				return false;
			}
		
	}
			
	
	
		/**
		 * This method is used to set Mean and Variance increment value.
		 * @author dahale_p
		 * @param none
		 * @return boolean This returns true if set Mean and Variance increment value.
		 */

		public boolean increamentMeanvariance(int mean,int variance) {
			//WebElement mean= null;
			try {
				controlActions.WaitforelementToBeClickable(FMChartSelectResource);
				Sync();
				controlActions.clickOnElement(FMChartSelectResource);
				Sync();
				controlActions.WaitforelementToBeClickable(FMChartsMeanUpArrow);
				Sync();	
		
				logInfo("Clicking Up arrow to set Mean value equal to "+ mean );
				for(int i=1;i<mean;i++)
				{
					controlActions.click(FMChartsMeanUpArrow);
				}
				
				controlActions.WaitforelementToBeClickable(FMChartsVarianceUpArrow);
				Sync();	
				
				logInfo("Clicking Up arrow to set Variance value equal to "+ variance );
				for(int i=1;i<variance;i++)
				{
					controlActions.click(FMChartsVarianceUpArrow);
				}

				controlActions.clickOnElement(SaveBtn);
				Sync();
				logInfo("Mean Has been Incremented");
				return true;
				} catch (Exception e) {
				logError("Failed to set mean - " + e.getMessage());
				return false;
				}
		}
	
		 /**
		 * This method is used to search a form in Form Manager and navigate to Charts Tab
		 * @author dahale_p
		 * @param formname 
		 * @return boolean This returns true if form is selected and navigation to charts tab is done
		 */

		public boolean selectFormAndToCheckCharts(String formname) {
			try {	
				Sync();
				controlActions.click(SearchFormnameInput);
				controlActions.sendKeys(SearchFormnameInput, formname);
				controlActions.actionEnter();
				Sync();
				WebElement formselect = controlActions.perform_GetElementByXPath(FormsManagerPageLocators.SELECT_FORM, "FORMNAME",formname);
				controlActions.doubleClick(formselect);
				Sync();
				logInfo("Selected a form  and check charts tab");
				return true;
			}
			catch(Exception e) {
				logError("Failed to select form and to check charts tab "
						+ e.getMessage());
				return false;
			}	
		}
		
		/**
		 * This method is used to click on Close the form with Close button.
		 * @author dahale_p
		 * @param none
		 * @return boolean This returns true if CHART button is clicked.
		 */
		public boolean clickResourceTabFormClose() { 
			try {
				controlActions.WaitforelementToBeClickable(fmResourceTabFormCloseButton);
				Sync();
				controlActions.clickOnElement(fmResourceTabFormCloseButton);
				Sync();
				
				logInfo("Clicked on close button to Close Form ");
				Sync();
				return true;
			}
			catch(Exception e) {
				logError("Failed to click close button to close form "
						+ e.getMessage());
				return false;
			}		
		}
		
		/**
		 * This method is used to set Mean and Variance increment value.
		 * @author dahale_p
		 * @param none
		 * @return boolean This returns true if set Default Mean increment value.
		 */

		public boolean increamentDefaultValMean(int mean) {
			//WebElement FMChartMeanUpArrow = null;
			
			
			try {
				Sync();
				controlActions.WaitforelementToBeClickable(fmChartResourcesDefaultMeanUpArrow);
				Sync();
				controlActions.clickOnElement(fmChartResourcesDefaultMeanUpArrow);
				
				Sync();	
				//set every day value by clicking on up arrow
				logInfo("Clicking Default Up arrow to set Mean value equal to "+ mean );
				for(int i=1;i<mean;i++)
				{
					controlActions.click(fmChartResourcesDefaultMeanUpArrow);
				}

				controlActions.clickOnElement(FMResourceTabSaveBtn);
				Sync();
				logInfo("Default Mean has been Incremented");
				return true;
				} catch (Exception e) {
				logError("Failed to set default mean - " + e.getMessage());
				return false;
				}
		}
		
		/**
		 * This method is used to set Default Variance increment value.
		 * @author dahale_p
		 * @param none
		 * @return boolean This returns true if set Default Variance increment value.
		 */

		
		public boolean increamentDefaultValVariance(int variance) {
			//WebElement FMChartVarianceUpArrow = null;
			
			
			try {
				Sync();
				controlActions.WaitforelementToBeClickable(fmChartResourcesDefaultVarianceUpArrow);
				Sync();
				controlActions.clickOnElement(fmChartResourcesDefaultVarianceUpArrow);
				
				Sync();	
				//set every day value by clicking on up arrow
				logInfo("Clicking Default Up arrow to set Mean value equal to "+ variance );
				for(int i=1;i<variance;i++)
				{
					controlActions.click(fmChartResourcesDefaultVarianceUpArrow);
				}

				controlActions.clickOnElement(FMResourceTabSaveBtn);
				Sync();
				logInfo("Default Mean has been Incremented");
				return true;
				} catch (Exception e) {
				logError("Failed to set default mean - " + e.getMessage());
				return false;
				}
		}
		
		/**
		 * This method is used to set Default check box for mean and variance value for resources.
		 * @author dahale_p
		 * @param none
		 * @return boolean This returns true if set Default check box is checked for resources.
		 */
		
		public boolean clickDefaultCheckboxWithPopup() {
			
			try {
				Sync();
				controlActions.WaitforelementToBeClickable(fmChartResourcesDefaultCheckbox);
				Sync();
				controlActions.clickOnElement(fmChartResourcesDefaultCheckbox);
				Sync();	
				controlActions.WaitforelementToBeClickable(fmChartResourcesDefaultChecKPopUp);
				Sync();
				controlActions.clickOnElement(fmChartResourcesDefaultChecKPopUp);
				logInfo("Clicking on OK button of Default Pop up" );
				
				controlActions.clickOnElement(FMResourceTabSaveBtn);
				Sync();
				logInfo("Default Values of Mean and Variance has been set");
				return true;
				} catch (Exception e) {
				logError("Failed to Default Values of Mean and Variance - " + e.getMessage());
				return false;
				}
		}
		
		
		/**
		 * This method is used to set Default check box for mean and variance value for resources.
		 * @author dahale_p
		 * @param none
		 * @return boolean This returns true if set Default check box is checked for resources.
		 */
		
		public boolean addDefaultComment(String defaultComment) {
			
			try {
				Sync();
				controlActions.WaitforelementToBeClickable(fmChartResourcesDefaultComment);
				Sync();
				controlActions.clickOnElement(fmChartResourcesDefaultComment);
				Sync();	
				controlActions.sendKeys(fmChartResourcesDefaultComment,defaultComment);
				Sync();
				controlActions.clickOnElement(FMResourceTabSaveBtn);
				Sync();	
				logInfo("Default Comment has been saved" );
				return true;
				} 
			catch (Exception e) {
				logError("Failed to set Default Comment - " + e.getMessage());
				return false;
				}
		}
		
		/**
		 * This method is used to set Sample Size increment value.
		 * @author dahale_p
		 * @param none
		 * @return boolean This returns true if set Mean and Variance increment value.
		 */
		
		public boolean increamentSampleSize(int sampleSize, String resourceName) {
			WebElement FMChartSampleSizeUpArrow = null;
			
			
			try {
				Sync();
				
				FMChartSampleSizeUpArrow = controlActions.perform_GetElementByXPath(FormsManagerChartsPageLocators.FM_CHART_RESOURCES_SAMPLESIZE_UP_ARROW,
						"RESOURCE_NAME", resourceName);
				
				Sync();	
				//set every day value by clicking on upp arrow
				logInfo("Clicking Up arrow to set Mean value equal to "+ sampleSize );
				for(int i=1;i<sampleSize;i++)
				{
					controlActions.click(FMChartSampleSizeUpArrow);
				}

				controlActions.clickOnElement(FMResourceTabSaveBtn);
				Sync();
				logInfo("Sample Size Has been Incremented");
				return true;
				} catch (Exception e) {
				logError("Failed to set mean - " + e.getMessage());
				return false;
				}
		}
			
		
		/**
		 * This method is used to calculate mean and variation
		 * @author dahale_p
		 * @param - charttype 
		 * @param - count
		 * @return boolean This returns true when the mean and variation will be calculated
		 */

		public boolean calculateByDate(String charttype,String count,String resourceName) {
			try {
				
				int a =2100;
				Sync();
				WebElement calculator = controlActions.perform_GetElementByXPath(FormsManagerChartsPageLocators.CALCULATOR_BTN, "RESOURCE",resourceName );
				Sync();
				controlActions.clickOnElement(calculator);
				Sync();
				controlActions.WaitforelementToBeClickable(byDate);
				Sync();
				controlActions.clickOnElement(byDate);
//			//to uncomment	
				int p=22;
				DateTime dt = new DateTime();
			//	DateTime subdt = new DateTime();
			//	subdt.subtractTime(count, resourceName, p)
				
				String currentDate  = dt.getTodayDate("MM/dd/YYYY", dt.getTimezone(adminTimezone));
				String currentTime  = dt.getTodayDate("HH:mm", dt.getTimezone(adminTimezone));
				String startDate = dt.AddDaystoToddaysDate(-1, "MM/dd/yyyy");
				//String startDate2 = dt.addSubtractDaysFromDate(new Date() , 1, "MM/dd/yyyy")	;			
			//	String subtractTime ;
				//String subtractTime  = dt.subtractTime(currentDate, "HH:mm",40);
				
				System.out.println(currentDate);
				System.out.println(currentTime);
				System.out.println(startDate);
				//System.out.println(startDate2);
				
				
				//if(!setKendoDate(startDateText, currentDate));

				if(!setKendoDate(startDateText,startDate));
				if(!setKendoTime(startTime, currentTime));
				
				if(!setKendoDate(endDateText, currentDate));
				Thread.sleep(3000);

				if(!setKendoTime(endTime, currentTime));
				int m= 22;
	//			dateTime.setDate(startDate, "Day", 0);
				//StartDateTxt
				Thread.sleep(3000);
				controlActions.WaitforelementToBeClickable(CalculateBtn);
				controlActions.clickOnElement(CalculateBtn);
				Thread.sleep(3000);
				controlActions.WaitforelementToBeClickable(ApplyBtn);
				controlActions.clickOnElement(ApplyBtn);
				Sync();
				WebElement meancolumn = controlActions.perform_GetElementByXPath(FormsManagerChartsPageLocators.MEAN_INPUT, "RESOURCE", ResourceSelection.resource);
//			WebElement variationcolumn = controlActions.perform_GetElementByXPath(FormsManagerChartsPageLocators.VARIATION_INPUT, "RESOURCE", ResourceSelection.resource);
//				switch(charttype) {
//				case "XiMr":
//					if(!(OutputValues.mean == meancolumn.getAttribute("aria-valuenow") && (OutputValues.variation == variationcolumn.getAttribute("aria-valuenow")))){
//						logError("Mean and variation not calulated accurately");
//					}
//					break;
//				case "NpChart":
//					if(!(OutputValues.mean == meancolumn.getAttribute("aria-valuenow"))){
//						logError("Mean not calculate accurately");
//					}
//					break;
//				case "XBarS":
//					if(!(OutputValues.mean == meancolumn.getAttribute("aria-valuenow") && (OutputValues.variation == variationcolumn.getAttribute("aria-valuenow")))){
//						logError("Mean and variation not calulated accurately");
//					}
//					break;
//				case "XBarR":
//					if(!(OutputValues.mean == meancolumn.getAttribute("aria-valuenow") && (OutputValues.variation == variationcolumn.getAttribute("aria-valuenow")))){
//						logError("Mean and variation not calulated accurately");
//					}
//					break;
//				case "SumChart":
//					if(!(OutputValues.mean == meancolumn.getAttribute("aria-valuenow"))){
//						logError("Mean not calulated accurately");
//					}
//					break;
//				case "UChart":
//					if(!(OutputValues.mean == meancolumn.getAttribute("aria-valuenow"))){
//						logError("Mean not calulated accurately");
//					}
//					break;
//				case "PChart":
//					if(!(OutputValues.mean == meancolumn.getAttribute("aria-valuenow"))){
//						logError("Mean not calulated accurately");
//					}
//					break;
//				case "CChart":
//					if(!(OutputValues.mean == meancolumn.getAttribute("aria-valuenow"))){
//						logError("Mean not calulated accurately");
//					}
//					break;
//				case "AverageChart":
//					break;
//				default:
//					logError("Selected Chart is INVALID");
//					return false;
//				}
				controlActions.clickOnElement(SaveBtn);
				Sync();
				logInfo("Mean nad Variation calculated");
				return true;
			}catch (Exception e) {
				logError("Failed to calculate mean and variation  " +e.getMessage());
				return false;
			}
		}
		
		/**
		 * This method is used to input date time to Validation's Kendo DateTime picker
		 * @author dahale_p
		 * @param element The WebElement to be set with Date
		 * @param date The value of Date Time to be set
		 * @return boolean This returns as true if date time value is set successfully.
		 */
		public boolean setKendoDate(WebElement element, String date) {
			try {
				int a=66;
				String DTParts[]=date.split(" ");
				String Date[]=DTParts[0].split("/");
				//String Time[]=DTParts[1].split(":");
//				for(int i = 0; i < 3; i++) {
//		        	if(Date[i].startsWith("0")){
//		        		Date[i]=Date[i].replace("0", "");
//			        }
//			        else{
//			        	Date[i]=Date[i];
//			        }
//		        }
				System.out.println(Arrays.toString(Date));
				element.click();
				element.sendKeys(Keys.chord(Keys.CONTROL, "a"));
				element.sendKeys(Date[0]);
				element.sendKeys(Date[1]);
				element.sendKeys(Date[2]);
				
				element.sendKeys(DTParts[2]);//TBD
				logInfo("Added date  - " + date);
				return true;
			}
			catch(Exception e) {
				logError("Failed to add date time - "
						+ e.getMessage());
				return false;
			}	
		}

		/**
		 * This method is used to input date time to Validation's Kendo DateTime picker
		 * @author dahale_p
		 * @param element The WebElement to be set with Date
		 * @param date The value of Date Time to be set
		 * @return boolean This returns as true if date time value is set successfully.
		 */
		public boolean setKendoTime(WebElement element, String time) {
			try {
				String DTParts[]=time.split(" ");
				System.out.println(Arrays.toString(DTParts));
//				String Date[]=DTParts[0].split("/");
				String Time[]=DTParts[0].split(":");
//				for(int i = 0; i < 3; i++) {
//		        	if(Date[i].startsWith("0")){
//		        		Date[i]=Date[i].replace("0", "");
//			        }
//			        else{
//			        	Date[i]=Date[i];
//			        }
//		        }
				//System.out.println(Time);
				int s =15544;
				System.out.println(Arrays.toString(Time));
				element.click();
				element.sendKeys(Keys.chord(Keys.CONTROL, "a"));
				element.sendKeys(Time[0]);
				element.sendKeys(Time[1]);
				//element.sendKeys(Date[2]);
				
				element.sendKeys(DTParts[0]);
				logInfo("Added  time - " + time);
				return true;
			}
			catch(Exception e) {
				logError("Failed to add time - "
						+ e.getMessage());
				return false;
			}	
		}

	
	public static class CHARTS_FIELDS{
		public static final String RESOURCE_SELECT_CHK = "Resource Select Chk";
		public static final String RESOURCES = "Resources";
		public static final String DEFAULT = "Default";
		public static final String MEAN = "Mean";
		public static final String VARIATION = "Variation";
		public static final String COMMENT = "Comment";	
	}

	public static class FieldSelection { 
		public static String SelectOneField;
		public static List<String> MultiSelect;
	}

	public static class FilterSelection {
		public static String LineIdentifier = "LINE INDENTIFIER ";
		public static String ShiftIdentifier = "SHIFT INDENTIFIER ";
		public static String LineValue = "LINE VALUE ";
		public static String ShiftValue = "SHIFT VALUE ";
		public static String lineselect;
		public static String shiftselect;
		public static String lineval;
		public static String shiftval;
	}

	public static class ResourceSelection{
		public static String resource;
	}

	public static class SubTabs {
		public static final String DETAILS = "Details";
		public static final String FIELDS = "Fields";
		public static final String FILTERS ="Filters";
		public static final String RESOURCES = "Resources";

	}

	public static class FieldInputTypeHeader {
		public static final String MULTIPLEFIELD = "Field(s)";
		public static final String SINGLEFIELD = "Single";
	}

	public static class OutputValues {
		public static String mean;
		public static String variation;
	}


	public static class ChartTypes {

		public static String XIMR = "XiMr";
		public static String NPCHART = "NpChart";		
		public static String XBARS = "XBarS";
		public static String XBARR = "XBarR"; 
		public static String SUMCHART = "SumChart";
		public static String UCHART = "UChart";
		public static String PCHART = "PChart";
		public static String CCHART = "CChart";		
		public static String AVERAGECHART = "AverageChart";
	}

}
