package com.project.safetychain.webapp.pageobjects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.project.utilities.ControlActions;

public class ChartBuilderPage extends CommonPage{
	ChartBuilderPageLocators chartbuilder_locators;
	WebDriver driver;
	WebDriverWait wait;
	ControlActions controlActions;
	public Actions action;
	public ChartBuilderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 40);
		controlActions = new ControlActions(driver);
		chartbuilder_locators = new ChartBuilderPageLocators();
	}	


	@FindBy(xpath = ChartBuilderPageLocators.ADD_NEW_CHART_BTN)
	public WebElement AddNewChartBtn;

	@FindBy(xpath = ChartBuilderPageLocators.CHART_TYPE_INPUT)
	public WebElement ChartTypeInput;

	@FindBy(xpath = ChartBuilderPageLocators.ENABLE_CHART)
	public WebElement EnableChart;

	@FindBy(xpath = ChartBuilderPageLocators.SAVE_BTN)
	public WebElement SaveBtn;
	
	@FindBy(xpath = ChartBuilderPageLocators.CHART_TYPE_DRP_DWN_BTN)
	public WebElement ChartTypeDropDownButton;
	
	@FindBy(xpath = ChartBuilderPageLocators.ENABLE_DISABLE_BUTTON)
	public WebElement EnableChartToggleButton;
	
	@FindBy(xpath = ChartBuilderPageLocators.CLEAR_BTN)
	public WebElement ClearButton;

	/**
	 * This method is used to set values to Dropdown
	 * @author hingorani_a
	 * @param locatorXpath Generic xpath of the element to be set
	 * @param selector The JavaScript selector to click on dropdown so that 
	 * dropdown list is opened
	 * @param fieldValue List of values to be set
	 * @return boolean This returns boolean true after setting value to Dropdown
	 */

	public boolean setDropDownValue(String locatorXpath, String selector, String fieldValue) {
		String finalXpath = null;
		try {
			finalXpath = controlActions.perform_GetDynamicXPath(locatorXpath, "DDLOPTIONVALUE", fieldValue);
			if(!controlActions.setKendoDropDownValue(driver, selector, finalXpath)) {
				return false;
			}
			threadsleep(5000);
			return true;
		}
		catch(Exception e) {
			logError("Failed to set value for Drop Down element " + finalXpath + " :"
					+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to select all rules for primary chart
	 * @author choubey_a
	 * @return boolean This returns boolean true after selecting all run rule for primary chart
	 */


	public boolean selectAllPrimaryChartRule() {
		try {			
			WebElement primary1 = controlActions.perform_GetElementByXPath(ChartBuilderPageLocators.RULE_SELECT,"RULES",ChartRules.PRIMARYRULE1);
			controlActions.perform_ClickWithJavaScriptExecutor(primary1);
			Thread.sleep(2000);
			WebElement primary2 = controlActions.perform_GetElementByXPath(ChartBuilderPageLocators.RULE_SELECT,"RULES",ChartRules.PRIMARYRULE2);
			controlActions.perform_ClickWithJavaScriptExecutor(primary2);
			Thread.sleep(2000);
			WebElement primary3 = controlActions.perform_GetElementByXPath(ChartBuilderPageLocators.RULE_SELECT,"RULES",ChartRules.PRIMARYRULE3);
			controlActions.perform_ClickWithJavaScriptExecutor(primary3);
			Thread.sleep(2000);
			WebElement primary4 = controlActions.perform_GetElementByXPath(ChartBuilderPageLocators.RULE_SELECT,"RULES",ChartRules.PRIMARYRULE4);
			controlActions.perform_ClickWithJavaScriptExecutor(primary4);
			Thread.sleep(2000);
			WebElement primary5 = controlActions.perform_GetElementByXPath(ChartBuilderPageLocators.RULE_SELECT,"RULES",ChartRules.PRIMARYRULE5);
			controlActions.perform_ClickWithJavaScriptExecutor(primary5);
			Thread.sleep(2000);
			WebElement primary6 = controlActions.perform_GetElementByXPath(ChartBuilderPageLocators.RULE_SELECT,"RULES",ChartRules.PRIMARYRULE6);
			controlActions.perform_ClickWithJavaScriptExecutor(primary6);
			Thread.sleep(2000);
			WebElement primary7 = controlActions.perform_GetElementByXPath(ChartBuilderPageLocators.RULE_SELECT,"RULES",ChartRules.PRIMARYRULE7);
			controlActions.perform_ClickWithJavaScriptExecutor(primary7);
			Thread.sleep(2000);
			WebElement primary8 = controlActions.perform_GetElementByXPath(ChartBuilderPageLocators.RULE_SELECT,"RULES",ChartRules.PRIMARYRULE8);
			controlActions.perform_ClickWithJavaScriptExecutor(primary8);
			Thread.sleep(2000);
			WebElement primary9 = controlActions.perform_GetElementByXPath(ChartBuilderPageLocators.RULE_SELECT,"RULES",ChartRules.PRIMARYRULE9);
			controlActions.perform_ClickWithJavaScriptExecutor(primary9);
			Thread.sleep(2000);
			logInfo("All primary rules selected");
			return true;
		}
		catch(Exception e) {
			logError("Failed to select all primary rules"
					+ e.getMessage());
			return false;
		}			
	}

	/**
	 * This method is used to select all rules for secondary chart
	 * @author choubey_a
	 * @return boolean This returns boolean true after selecting all run rule for secondary chart
	 */


	public boolean selectAllSecondaryChartRule() {
		try {			
			WebElement secondary1 = controlActions.perform_GetElementByXPath(ChartBuilderPageLocators.RULE_SELECT,"RULES",ChartRules.SECONDARYRULE1);
			controlActions.perform_ClickWithJavaScriptExecutor(secondary1);
			Thread.sleep(2000);
			WebElement secondary4 = controlActions.perform_GetElementByXPath(ChartBuilderPageLocators.RULE_SELECT,"RULES",ChartRules.SECONDARYRULE4);
			controlActions.perform_ClickWithJavaScriptExecutor(secondary4);
			Thread.sleep(2000);
			WebElement secondary5 = controlActions.perform_GetElementByXPath(ChartBuilderPageLocators.RULE_SELECT,"RULES",ChartRules.SECONDARYRULE5);
			controlActions.perform_ClickWithJavaScriptExecutor(secondary5);
			Thread.sleep(2000);
			WebElement secondary8 = controlActions.perform_GetElementByXPath(ChartBuilderPageLocators.RULE_SELECT,"RULES",ChartRules.SECONDARYRULE8);
			controlActions.perform_ClickWithJavaScriptExecutor(secondary8);
			Thread.sleep(2000);
			logInfo("All secondary rules selected");
			return true;
		}
		catch(Exception e) {
			logError("Failed to select all secondary rules"
					+ e.getMessage());
			return false;
		}					
	}

	/**
	 * This method is used to add a chart in Chart Builder
	 * @author choubey_a
	 * @param chartname - name of the chart to be created
	 * @param charttype - type of the chart to be created
	 * @return boolean This returns boolean true after selecting all run rule for primary chart
	 */



	public boolean addNewChart(String chartname, String charttype ) {
		try {

			controlActions.WaitforelementToBeClickable(AddNewChartBtn);
			controlActions.clickOnElement(AddNewChartBtn); 
			Thread.sleep(3000);
			String selector = "kendo-dropdownlist[formcontrolname='ChartType'] > span";
			if(!setDropDownValue(ChartBuilderPageLocators.CHART_TYPE_LST,selector,charttype)) {
				logError("Chart Type " +charttype+ " not selected  ");
				return false;
			}
			Sync();
			controlActions.clickOnElement(ChartTypeInput);
			controlActions.actionSendKeys(ChartTypeInput,chartname);
			Thread.sleep(3000);
			controlActions.clickOnElement(EnableChart);	

			switch(charttype) {
			case "XiMr":
				selectAllPrimaryChartRule();
				selectAllSecondaryChartRule();
				break;
			case "NpChart":
				selectAllPrimaryChartRule();
				break;
			case "XBarS":
				selectAllPrimaryChartRule();
				selectAllSecondaryChartRule();
				break;
			case "XBarR":
				selectAllPrimaryChartRule();
				selectAllSecondaryChartRule();
				break;
			case "SumChart":
				Thread.sleep(2000);
				break;
			case "UChart":
				selectAllPrimaryChartRule();
				break;
			case "PChart":
				selectAllPrimaryChartRule();
				break;
			case "CChart":
				selectAllPrimaryChartRule();
				break;
			case "AverageChart":
				Thread.sleep(3000);
				break;
			default:
				logError("Selected Chart is INVALID");
				return false;			
			}

			Thread.sleep(3000);
			controlActions.clickOnElement(SaveBtn);
			Sync();
			logInfo("Created Chart of charttype - " +charttype);
			return true;
		}
		catch(Exception e) {
			logError("Failed to create chart of charttype -  " + charttype
					+ e.getMessage());
			return false;
		}	

	}


	/**This method is used to click on 'Add New Chart' button
	 * @author ahmed_tw
	 * @return [boolean] - True after successfully clicking on the 'Add New Chart' button. False if failed to do so.
	 */
	public boolean clickAddNewChartButton() {
		try {
			controlActions.clickElement(AddNewChartBtn);
			logInfo("Clicked Add New Chart button");
			return true;
		}catch(Exception e) {
			logError("Failed to Clicked Add New Chart button "+ e.getMessage());
			return false;
		}
	}
	
	/**This method is used to click 'Drop Down Arrow' of the 'Chart Type' dropdown. This opens the Chart Type DropDown.
	 * @author ahmed_tw
	 * @return [boolean] : True after successfully clicking on the 'Drop Down Arrow' button. False if failed to do so.
	 */
	public boolean clickChartTypeDropDownButton() {
		try {
			controlActions.clickElement(ChartTypeDropDownButton);
			logInfo("Clicked and open chart type dropdown");
			return true;
		}catch(Exception e) {
			logError("Failed to Clicked and open chart type dropdown"+ e.getMessage());
			return false;
		}
	}
	
	/**This method is used to select the chart type after the Chart Type Drop Down List is opened.
	 * @author ahmed_tw
	 * @param chartType [String] : Name of the Chart Type
	 * @return [boolean] : True after selecting the chart type. False if fails to do so.
	 */
	public boolean selectChartType(String chartType) {
		try {
			WebElement chartTypeOption = controlActions.perform_GetElementByXPath(ChartBuilderPageLocators.CHART_TYPE_LIST_OPTN, "OPTION", chartType);
			controlActions.clickElement(chartTypeOption);
			logInfo("Selected Chart Type - " + chartType);
		} catch (Exception e) {
			logError("Could Not Select the Chart Type - " + chartType + e.getMessage());
			return false;
		}
		return true;
	}
	
	/**This method is used to add the chart name in 'Chart Name' input textbox.
	 * @author ahmed_tw
	 * @param chartName [String] : Name for the Chart
	 * @return [boolean] True after entering the chart name in the 'Chart Name' input textbox. False if failed to do so.
	 */
	public boolean addChartName(String chartName) {
		try {
			controlActions.clickOnElement(ChartTypeInput);
			ChartTypeInput.clear();
			controlActions.actionSendKeys(ChartTypeInput,chartName);
			logInfo("Entered Chart Name - " + chartName + " in the 'Chart Name' input textbox");
		} catch (Exception e) {
			logError("Could Not enter the chart in the 'Chart Name' input textbox");
			return false;
		}
		return true;
	}

	/**This method is used to click on the 'SAVE' button
	 * @author ahmed_tw
	 * @return [boolean] : True after successfully clicking on the 'SAVE' button. False if failed to do so.
	 */
	public boolean clickSaveButton() {
		try {
			controlActions.clickOnElement(SaveBtn);
			Sync();
			logInfo("Successfully clicked on the 'SAVE' button");
		} catch (Exception e) {
			logError("Could Not click on the 'SAVE' button ");
			return false;
		}
		return true;
	}

	/**This method is used to verify all chart types are displayed in "CHART TYPE" drop down list
	 * @author ahmed_tw
	 * @return [boolean] : True if all chart types are displayed else false.
	 */
	public boolean verifyChartTypeDropDownList(){
		List<WebElement> chartTypeListElement = null;
		List<String> chartTypeListFromApplication = new ArrayList<String>();
		
		List<String> chartTypesList = Arrays.asList("XiMr","NpChart","XBarS","XBarR","SumChart","UChart","PChart","CChart","AverageChart");
		
		try {
			chartTypeListElement = controlActions.perform_GetListOfElementsByXPath(ChartBuilderPageLocators.CHART_TYPE_LIST);
			for(WebElement fieldElement :chartTypeListElement ) {
				chartTypeListFromApplication.add(fieldElement.getText());
				logInfo("Fetched chart type name " + fieldElement.getText() + " from chart type dropdown");
			}
			
			if(chartTypeListFromApplication.equals(chartTypesList)) {
				logInfo("Verifid all chart types");
			}
			return true;
			
		} catch (Exception e) {
			logError("Fetched chart type names from chart type dropdown "+e.getMessage());
			return false;	
		}
	}
	
	/**This method is used to select any Primary Rules that are passed in the argument.
	 * @author ahmed_tw
	 * @param ruleNumbersToSelect [List<String>] : List of rule numbers to be selected. e.g ["1","4","3"]
	 * @return [boolean] : True after selecting all the rules corresponding to the rule numbers in the parameter. False if failed to do so.
	 */
	public boolean selectPrimaryChartRules(List<String> ruleNumbersToSelect) {
		String rule = "PrimaryChartRule";
		String ruleToSelect ; 
		try {			
			for(String ruleNumber : ruleNumbersToSelect) {
				ruleToSelect = rule+ruleNumber;
				WebElement primaryRule = controlActions.perform_GetElementByXPath(ChartBuilderPageLocators.RULE_SELECT,"RULES",ruleToSelect);
				controlActions.perform_ClickWithJavaScriptExecutor(primaryRule);
				Thread.sleep(2000);
				logInfo("Selected Primary Rule - " + ruleNumber);
			}
			return true;
		}
		catch(Exception e) {
			logError("Could Not select primary rules" + e.getMessage());
			return false;
		}			
	}


	/**This method is used to select any Secondary Rules that are passed in the argument.
	 * @author ahmed_tw
	 * @param ruleNumbersToSelect [List<String>] : List of rule numbers to be selected. e.g ["1","4","3"]
	 * @return [boolean] : True after selecting all the rules corresponding to the rule numbers in the parameter. False if failed to do so.
	 */
	public boolean selectSecondaryChartRules(List<String> ruleNumbersToSelect) {
		String rule = "SecondaryChartRule";
		String ruleToSelect ; 
		try {			
			for(String ruleNumber : ruleNumbersToSelect) {
				ruleToSelect = rule+ruleNumber;
				WebElement secondaryRule = controlActions.perform_GetElementByXPath(ChartBuilderPageLocators.RULE_SELECT,"RULES",ruleToSelect);
				controlActions.perform_ClickWithJavaScriptExecutor(secondaryRule);
				Thread.sleep(2000);
				logInfo("Selected Secondary Rule - " + ruleNumber);
			}
			return true;
		}
		catch(Exception e) {
			logError("Could Not select Secondary rules" + e.getMessage());
			return false;
		}			
	}

	/**This method is used to verify the Duplicate Name pop up Error and close that pop up clicking on it's OK button. 
	 * @author ahmed_tw
	 * @return [boolean] : True after verifying Duplicate Name Popup Error and closing it. False if failed to do so.
	 */
	public boolean verifyAndHandleDuplicateNamePopup() {
		String error = "Chart name must be unique.";
		try {
			WebElement popupMsgBody = controlActions.perform_GetElementByXPath(ChartBuilderPageLocators.POPUP_MSG);
			logInfo("Verified the Display of a Popup");
			if(popupMsgBody.getText().equals(error)) {
				logInfo("Verified the Display of Error Popup for Duplicate Name");
				controlActions.perform_GetElementByXPath(ChartBuilderPageLocators.POPUP_OK_BTN).click();
				logInfo("CLosed Duplicate Name Popup");
			}
		} catch (Exception e) {
			logError("Could NOT verify the Display of Error Popup for Duplicate Name ");
			return false;
		}	
		return true;
	}
	
	/**This method is used to click the 'Enable Chart' Toggle Button
	 * @author ahmed_tw
	 * @return [Boolean] : True after successfully clicking the 'Enable Chart' Toggle button. False if fails to do so.
	 */
	public boolean clickEnableChartToggelButton(){
		try {
			controlActions.clickOnElement(EnableChartToggleButton);
			logInfo("Clicked Enable Disable Toggle button");
			return true;
		}catch(Exception e) {
			logError("Failed to Click Enable Disable Toggle button "+ e.getMessage());
			return false;
		}
	}
	
	/**This method is used to get the status of 'Enable Chart' Toggle Button 
	 * @author ahmed_tw
	 * @return [String] : "ON" or "OFF" status of 'Enable Chart' Toggle Button
	 */
	public String getEnableChartToggleButtonStatus() {
		try {
			WebElement enableChartToggleButton = controlActions.perform_GetElementByXPath(ChartBuilderPageLocators.ENABLE_DISABLE_STATUS);
			if(enableChartToggleButton.getAttribute("aria-checked").equals("false")) {
				logInfo("Enable Chart Toggle is OFF");
				return "OFF";
			}
			if(enableChartToggleButton.getAttribute("aria-checked").equals("true")) {
				logInfo("Enable Chart Toggle is ON");
				return "ON";
			}
		} catch (Exception e) {
			logError("Could not determine the status of Enable Chart Toggle Button " + e.getMessage());
			return null;
		}
		return null;
	}
	
	/**This method is used to TURN ON 'Enable Chart' Toggle Button
	 * @author ahmed_tw
	 * @return [boolean] : True after turning ON 'Enable Chart' Toggle Button or if it is already ON. False if fails to turn toggle OFF.
	 */
	public boolean toggleOnEnableChart() {
		try {
			String toggleStatus = getEnableChartToggleButtonStatus();
			if(toggleStatus.equalsIgnoreCase("off")) {
				clickEnableChartToggelButton();
				logInfo("Turned ON Enable Chart Toggle Button");
				return true;
			}else if(toggleStatus.equalsIgnoreCase("on")) {
				logInfo("Enable Chart Toggle is Already ON");
				return true;
			}
		} catch (Exception e) {
			logError("Could Not Turn On Enable Chart Toggle Button" + e.getMessage());
			return false;
		}
		logInfo("Could Not Turn On Enable Chart Toggle Button");
		return false;

	}
	
	/**This method is used to TURN OFF 'Enable Chart' Toggle Button
	 * @author ahmed_tw
	 * @return [boolean] : True after turning OFF 'Enable Chart' Toggle Button or if it is already OFF. False if fails to turn toggle OFF.
	 */
	public boolean toggleOffEnableChart() {
		try {
			String toggleStatus = getEnableChartToggleButtonStatus();
			if(toggleStatus.equalsIgnoreCase("on")) {
				clickEnableChartToggelButton();
				logInfo("Turned OFF Enable Chart Toggle Button");
				return true;
			}else if(toggleStatus.equalsIgnoreCase("off")) {
				logInfo("Enable Chart Toggle is Already OFF");
				return true;
			}
		} catch (Exception e) {
			logError("Could Not Turn OFF Enable Chart Toggle Button" + e.getMessage());
			return false;
		}
		logInfo("Could Not Turn OFF Enable Chart Toggle Button");
		return false;

	}
	
	/**This method is used to check if a chart template is present or not in the charts list
	 * @author ahmed_tw
	 * @param chartName [String] : Name of the Chart Template
	 * @return [boolean] : True if Chart Template is present else false.
	 */
	public boolean isChartTemplatePresent(String chartName) {
		WebElement chartInList = null;
		try {
			chartInList = controlActions.perform_GetElementByXPath(ChartBuilderPageLocators.ANY_CHART_TMPLT_IN_LIST, "CHART_NAME", chartName);
			if(chartInList.isDisplayed()) {
				logInfo("Chart Template IS Present in Chart List -" + chartName);
				return true;
			}
			logInfo("Chart Template IS NOT Present in Chart List -" + chartName);
			return false;
		} catch (Exception e) {
			logError("Could NOT check if  Chart Template is present or not -" + chartName + e.getMessage());
			return false;
		}
	}
	
	/**This method is used to select a chart template form the list of created chart templates
	 * @author ahmed_tw
	 * @param chartName [String] : Name of Chart Template to select
	 * @return [boolean] : True after selecting the Chart Template, false if fails to do so
	 */
	public boolean selectChartTemplate(String chartName) {
		WebElement chartInList = null;
		try {
			chartInList = controlActions.perform_GetElementByXPath(ChartBuilderPageLocators.ANY_CHART_TMPLT_IN_LIST, "CHART_NAME", chartName);
			controlActions.clickElement(chartInList);
			logInfo("Selected Chart Template -" + chartName);
			return true;
		} catch (Exception e) {
			logError("Could NOT Select Chart Template -" + chartName + e.getMessage());
			return false;
		}
	}
	
//	public boolean verifySelectedChartTemplateTitle(String chartType, String chartName) {
//		try {
//			
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//	}
//	
//	
//	public boolean verifyChartTypeInChartsTemplateList() {
//		
//	}
//	 
//	public boolean verifyChartTypeInChartTitle() {
//		
//	}
	
	/**This method is used to get the current entered name in the Chart Name Input Field
	 * @author ahmed_tw
	 * @return [String] : Name from the Chart Name Input Field
	 */
	public String getChartNameFromInputField() {
		String nameInputValue = null;
		try {
			nameInputValue = ChartTypeInput.getAttribute("value");
			return nameInputValue;
		} catch (Exception e) {
			logError("Could Not get the Chart name Input Value " + e.getMessage());
			return null;
		}
	}
	
	/**This method is used to get all the selected Primary Rules in numbers like 1,3,5,2 
	 * @author ahmed_tw
	 * @return [List<String>] : Numbers of selected primary rules
	 */
	public List<String> getSelectedPrimaryRules(){
		List<String> selectedPRules = new ArrayList<String>(); 
		try {
			List<WebElement> allPRules = driver.findElements(By.xpath(ChartBuilderPageLocators.ALL_PRIMARY_RULE_CHKBOXES));//controlActions.perform_GetListOfElementsByXPath(ChartBuilderPageLocators.ALL_PRIMARY_RULE_CHKBOXES);
			if(allPRules.size() == 0) {
				selectedPRules = null;
			}else {
			for(WebElement checkbox : allPRules) {
				if(checkbox.isSelected()) {
					WebElement label = checkbox.findElement(By.xpath(".//following-sibling::label"));
				logInfo("Selected rule is - " + label.getText());
				selectedPRules.add(label.getText().substring(label.getText().length()-1));
					}
				}
			}
			return selectedPRules;
		} catch (Exception e) {
			logError("Could NOT get the primary selected rules - " + e.getMessage());
			return null;
		}
	}
	
	/**This method is used to get all the selected Secondary Rules in numbers like 1,3,5,2 
	 * @author ahmed_tw
	 * @return [List<String>] : Numbers of selected secondary rules
	 */
	public List<String> getSelectedSecondaryRules(){
		List<String> selectedSRules = new ArrayList<String>(); 
		try {
			List<WebElement> allSRules = controlActions.perform_GetListOfElementsByXPath(ChartBuilderPageLocators.ALL_SECONDARY_RULE_CHKBOXES);
			
			for(WebElement checkbox : allSRules) {
				if(checkbox.isSelected()) {
					WebElement label = checkbox.findElement(By.xpath(".//following-sibling::label"));
				logInfo("Selected rule is - " + label.getText());
				selectedSRules.add(label.getText().substring(label.getText().length()-1));
				}
			}
			return selectedSRules;
		} catch (Exception e) {
			logError("Could NOT get the secondary selected rules -" + e.getMessage());
			return null;
		}
		
	}
	
	/**This method is used to click on the CLEAR button
	 * @author ahmed_tw
	 * @return [boolean] : True post successfully clicking on the CLEAR button otherwise false.
	 */
	public boolean clickClearButton() {
		try {
			controlActions.clickOnElement(ClearButton);
			Sync();
			logInfo("Successfully clicked on the 'CLEAR' button");
		} catch (Exception e) {
			logError("Could Not click on the 'CLEAR' button ");
			return false;
		}
		return true;
	}
	
	/**This method is used to verify the Pending Changes pop up Error and close that pop up clicking on it's OK button. 
	 * @author ahmed_tw
	 * @return [boolean] : True after verifying Pending Changes Popup Error and closing it. False if failed to do so.
	 */
	public boolean verifyAndHandlePendingChangesPopup() {
		String errorTitle = "Pending Changes";
		String errorMessage = "Please Clear or Save your changes before leaving this view.";
		try {
			WebElement popupTitle = controlActions.perform_GetElementByXPath(ChartBuilderPageLocators.POPUP_TITLE);
			WebElement popupMsgBody = controlActions.perform_GetElementByXPath(ChartBuilderPageLocators.POPUP_MSG);
			logInfo("Verified the Display of a 'Pending Changes' Popup");
			
			if(popupMsgBody.getText().equals(errorMessage)) {
				logInfo("Verified the Display of Error Popup Message for 'Pending Changes'");
			}else {
				logInfo("Error Popup Message for 'Pending Changes' is NOT Displayed");
				return false;
			}
			
			if(popupTitle.getText().equals(errorTitle)) {
				logInfo("Verified the Display of Error Popup Title for 'Pending Changes'");
			}else {
				logInfo("Error Popup Title for 'Pending Changes' is NOT Displayed");
				return false;
			}
			
			controlActions.perform_GetElementByXPath(ChartBuilderPageLocators.POPUP_OK_BTN).click();
			logInfo("CLosed Duplicate Name Popup");
			return true;
		} catch (Exception e) {
			logError("Could NOT verify the Display of Error Popup for Duplicate Name ");
			return false;
		}	
	}
	
	/**This method is used to verify the Alert pop up after editing and saving changes to a disabled chart then close that pop up clicking on it's OK button. 
	 * @author ahmed_tw
	 * @return [boolean] : True after verifying Alert pop up after editing and saving changes to a disabled chart then closing it. False if failed to do so.
	 */
	public boolean verifyAndHandleAlertForDisableChartEdit() {
		String errorTitle = "Alert!";
		String errorMessage = "Disabled chart cannot be updated. Kindly Enable chart to update or clear changes.";
		try {
			WebElement popupTitle = controlActions.perform_GetElementByXPath(ChartBuilderPageLocators.POPUP_TITLE);
			WebElement popupMsgBody = controlActions.perform_GetElementByXPath(ChartBuilderPageLocators.POPUP_MSG);
			logInfo("Verified the Display of a 'Pending Changes' Popup");
			
			if(popupMsgBody.getText().equals(errorMessage)) {
				logInfo("Verified the Display of Error Popup Message for 'Pending Changes'");
			}else {
				logInfo("Error Popup Message for 'Pending Changes' is NOT Displayed");
				return false;
			}
			
			if(popupTitle.getText().equals(errorTitle)) {
				logInfo("Verified the Display of Error Popup Title for 'Pending Changes'");
			}else {
				logInfo("Error Popup Title for 'Pending Changes' is NOT Displayed");
				return false;
			}
			
			controlActions.perform_GetElementByXPath(ChartBuilderPageLocators.POPUP_OK_BTN).click();
			logInfo("CLosed Duplicate Name Popup");
			return true;
		} catch (Exception e) {
			logError("Could NOT verify the Display of Error Popup for Duplicate Name ");
			return false;
		}	
	}
	
	/**This method is used to get 'Created Date' of the chart template once it is selected.    
	 * @author ahmed_tw
	 * @return [String] : Date from the 'Created By' label  
	 */
	public String getCreatedDate() {
		String createdDate = null;
		try {
			WebElement createdMsg = controlActions.perform_GetElementByXPath(ChartBuilderPageLocators.CREATED_BY_LABEL);
			String[] splitCreatedMsg = createdMsg.getText().split(" ");
			
			createdDate = splitCreatedMsg[2];
			logInfo("Fetched Created date is - " + createdDate);
			return createdDate;
					
		} catch (Exception e) {
			logError("Could not get created date" + e.getMessage());
			return createdDate;
		}
	}
	
	/**This method is used to get 'Created Time' of the chart template once it is selected.    
	 * @author ahmed_tw
	 * @return [String] : Time from the 'Created By' label  
	 */
	public String getCreatedTime() {
		String createdTime = null;
		try {
			WebElement createdMsg = controlActions.perform_GetElementByXPath(ChartBuilderPageLocators.CREATED_BY_LABEL);
			String[] splitCreatedMsg = createdMsg.getText().split(" ");
			
			createdTime = splitCreatedMsg[3];
			logInfo("Fetched Created time is - " + createdTime);
			return createdTime;
					
		} catch (Exception e) {
			logError("Could not get created time" + e.getMessage());
			return createdTime;
		}
	}
	
	/**This method is used to get 'Modified Date' of the chart template once it is selected.    
	 * @author ahmed_tw
	 * @return [String] : Date from the 'Modified By' label  
	 */
	public String getModifiedDate() {
		String modifiedDate = null;
		try {
			WebElement modifiedMsg = controlActions.perform_GetElementByXPath(ChartBuilderPageLocators.MODIFIED_BY_LABEL);
			String[] splitModifiedMsg = modifiedMsg.getText().split(" ");
			
			modifiedDate = splitModifiedMsg[3];
			logInfo("Fetched Modified Date is - " + modifiedDate);
			return modifiedDate;
			
		} catch (Exception e) {
			logError("Could not get modified date " + e.getMessage());
			return modifiedDate;
		}
	}
	
	/**This method is used to get 'Modified Time' of the chart template once it is selected.    
	 * @author ahmed_tw
	 * @return [String] : Time from the 'Modified By' label  
	 */
	public String getModifiedTime() {
		String modifiedTime = null;
		try {
			WebElement modifiedMsg = controlActions.perform_GetElementByXPath(ChartBuilderPageLocators.MODIFIED_BY_LABEL);
			String[] splitModifiedMsg = modifiedMsg.getText().split(" ");
			
			modifiedTime = splitModifiedMsg[4];
			logInfo("Fetched Modified Time is - " + modifiedTime);
			return modifiedTime;
			
		} catch (Exception e) {
			logError("Could not get modified time " + e.getMessage());
			return modifiedTime;
		}
	}
	
	/**This method is used to get 'Created By User name' of the chart template once it is selected.    
	 * @author ahmed_tw
	 * @return [String] : Name of the User from the 'Created By' label  
	 */
	public String getCreatedByUser() {
		String createdByUser = null;
		try {
			WebElement createdMsg = controlActions.perform_GetElementByXPath(ChartBuilderPageLocators.CREATED_BY_LABEL);
			String[] splitCreatedMsg = createdMsg.getText().split(" ");
			createdByUser = splitCreatedMsg[splitCreatedMsg.length-2] + " " + splitCreatedMsg[splitCreatedMsg.length-1];
			logInfo("Fetched created user name as - " + createdByUser);
			return createdByUser;
		} catch (Exception e) {
			logError("Could not get Created User Name " + e.getMessage());
			return createdByUser;
		}
	}
	
	/**This method is used to get 'Modified By User name' of the chart template once it is selected.    
	 * @author ahmed_tw
	 * @return [String] : Name of the User from the 'Modified By' label  
	 */
	public String getModifiedByUser() {
		String modifiedUser = null;
		try {
			WebElement modifiedMsg = controlActions.perform_GetElementByXPath(ChartBuilderPageLocators.MODIFIED_BY_LABEL);
			String[] splitModifiedMsg = modifiedMsg.getText().split(" ");
			
			modifiedUser = splitModifiedMsg[splitModifiedMsg.length-2] + " " + splitModifiedMsg[splitModifiedMsg.length-1];
			logInfo("Fetched Modified User name as - " + modifiedUser);
			return modifiedUser;
			
		} catch (Exception e) {
			logError("Could not get Modified User Name " + e.getMessage());
			return modifiedUser;
		}
	}
	
	/**This method is used to get 'Logged In User Name From the Application'    
	 * @author ahmed_tw
	 * @return [String] : Name of the User currently logged in, in the application
	 */
	public String getLoggedInUserName() {
		try {
			WebElement loggedInUsername = controlActions.perform_GetElementByXPath(ChartBuilderPageLocators.LOGGED_IN_USERNAME);
			String loggedInUser = loggedInUsername.getText();
			logInfo("Fetched currently logged in User name as - " + loggedInUser);
			return loggedInUser;
		} catch (Exception e) {
			logError("Could not get currently logged in User Name " + e.getMessage());
			return null;
		}
	}
	
	/** This method is used to get the complete "Created by..." message from the application
 	 * @author ahmed_tw
 	 * @return [String] - "Created by.." message from the application
	 */
	public String getCreatedByMsg() {
		try {
			WebElement createdByLabel = controlActions.perform_GetElementByXPath(ChartBuilderPageLocators.CREATED_BY_LABEL);
			String completeCreatedByMessage = createdByLabel.getText();
			logInfo("Fetched 'Created by...' label as - " + completeCreatedByMessage);
			return completeCreatedByMessage;
		} catch (Exception e) {
			logError("Could not get 'Created by...' label " + e.getMessage());
			return null;
		}
	}
	
	/** This method is used to get the complete "Modified by..." message from the application
 	 * @author ahmed_tw
 	 * @return [String] - "Modified by..." message from the application
	 */
	public String getModifiedByMsg() {
		try {
			WebElement modifiedByLabel = controlActions.perform_GetElementByXPath(ChartBuilderPageLocators.MODIFIED_BY_LABEL);
			String completeModifiedByMessage = modifiedByLabel.getText();
			logInfo("Fetched 'Modified by...' label as - " + completeModifiedByMessage);
			return completeModifiedByMessage;
		} catch (Exception e) {
			logError("Could not get 'Modified by...' label " + e.getMessage());
			return null;
		}
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

	public static class ChartRules {
		public static String PRIMARYRULE1 = "PrimaryChartRule1";
		public static String PRIMARYRULE2 = "PrimaryChartRule2";
		public static String PRIMARYRULE3 = "PrimaryChartRule3";
		public static String PRIMARYRULE4 = "PrimaryChartRule4";
		public static String PRIMARYRULE5 = "PrimaryChartRule5";
		public static String PRIMARYRULE6 = "PrimaryChartRule6";
		public static String PRIMARYRULE7 = "PrimaryChartRule7";
		public static String PRIMARYRULE8 = "PrimaryChartRule8";
		public static String PRIMARYRULE9 = "PrimaryChartRule9";
		public static String SECONDARYRULE1 = "SecondaryChartRule1";
		public static String SECONDARYRULE4 = "SecondaryChartRule4";
		public static String SECONDARYRULE5 = "SecondaryChartRule5";
		public static String SECONDARYRULE8 = "SecondaryChartRule8";
	}
	
	public static class PrimaryRules{
		String RULE1 = "Rule 1";
		String RULE2 = "Rule 2";
		String RULE3 = "Rule 3";
		String RULE4 = "Rule 4";
		String RULE5 = "Rule 5";
		String RULE6 = "Rule 6";
		String RULE7 = "Rule 7";
		String RULE8 = "Rule 8";
		String RULE9 = "Rule 9";
	}
	public static class SecondaryRules{
		String RULE1 = "Rule 1";
		String RULE4 = "Rule 4";
		String RULE5 = "Rule 5";
		String RULE8 = "Rule 8";
	}
}
