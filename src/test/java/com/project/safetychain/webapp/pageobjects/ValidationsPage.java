package com.project.safetychain.webapp.pageobjects;

import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.project.utilities.CommonMethods;
import com.project.utilities.ControlActions;

public class ValidationsPage extends CommonPage{

	WebDriver driver;
	WebDriverWait wait;
	ControlActions controlActions;

	public ValidationsPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 40);
		controlActions = new ControlActions(driver);	
	}	

	/**
	 * Page Objects
	 */

	@FindBy(id = ValidationsPageLocators.NEW_VLDTN_BTN)
	public WebElement NewVldtnBtn;
	
	@FindBy(id = ValidationsPageLocators.VLDTN_GRID_NAME)
	public WebElement VldtnGridName;
	
	@FindBy(id = ValidationsPageLocators.VLDTN_GRID_NAME)
	public List<WebElement> VldtnGridNameLst;
	
	@FindBy(id = ValidationsPageLocators.VLDTN_BREADCRUMB)
	public WebElement VldtnBreadcrumb;
	
	@FindBy(id = ValidationsPageLocators.VLDTN_TITLE)
	public WebElement VldtnTitle;
	
	@FindBy(id = ValidationsPageLocators.VLDTN_LOCN_FILTER)
	public WebElement VldtnLocnFilter;

	@FindBy(xpath = ValidationsPageLocators.COLUMN_NAMES_TBL)
	public List<WebElement> ColumnNamesTbl;
	
	@FindBy(xpath = ValidationsPageLocators.GRID_LOCATION_DDL)
	public WebElement GridLocationDdl;
	
	@FindBy(xpath = ValidationsPageLocators.LOCATION_SEARCH_TXT)
	public WebElement LocationSearchTxt;
	
	@FindBy(xpath = ValidationsPageLocators.LOCATION_SEARCH_BTN)
	public WebElement LocationSearchBtn;
	
	@FindBy(xpath = ValidationsPageLocators.CLEAR_GRID_FILTER_BTN)
	public List<WebElement> ClearGridFilterBtn;
	
	@FindBy(xpath = ValidationsPageLocators.GRID_STATUS_DDL)
	public WebElement GridStatusDdl;
	
	@FindBy(xpath = ValidationsPageLocators.GRID_PAGINTN_OPTNS)
	public List<WebElement> GridPagintnOptns;

	@FindBy(xpath = ValidationsPageLocators.NEXT_PAGINTN_LNK)
	public WebElement NextPagintnLnk;
	
	@FindBy(xpath = ValidationsPageLocators.PREVIOUS_PAGINTN_LNK)
	public WebElement PreviousPagintnLnk;
	
	@FindBy(xpath = ValidationsPageLocators.FIRST_PAGINTN_LNK)
	public WebElement FirstPagintnLnk;
	
	@FindBy(xpath = ValidationsPageLocators.PAGE_SIZE_DDL)
	public WebElement PageSizeDdl;
	
	@FindBy(xpath = ValidationsPageLocators.POPUP_COLUMN_APPLY_BTN)
	public WebElement PopupColumnApplyBtn;
	
	
	/**
	 * Functions
	 */

	/**
	 * This method is used to click on New Validation button
	 * @author hingorani_a
	 * @param none 
	 * @return AddEditValidationsPage This returns object with error variable as false
	 * if New Validation button is clicked successfully.
	 */
	public AddEditValidationsPage createNewValidation() {
		AddEditValidationsPage aevp = new AddEditValidationsPage(driver);
		try {
			controlActions.click(NewVldtnBtn);
			Sync();
			logInfo("New Validation button is clicked");
			return aevp;
		}
		catch(Exception e) {
			logError("Failed while clicking on New Validation button - "
					+ e.getMessage());
			aevp.error = true;
			return aevp;
		}	
	}
	
	/**
	 * This method is used to find Column Index for the column present in Validations Grid
	 * @author hingorani_a
	 * @param columnName Column name whose Index you need. Use Class VLDTN_FIELDS to pass column name.
	 * This Index is in turn used in creating dynamic xpaths. 
	 * @return int This returns value of column index. 
	 * Returns 0, if column is not found
	 */
	public int getColumnIndex(String columnName) {
		int columnIndex = 0; 
		try {
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
	 * This method is used to search Validation
	 * @author hingorani_a
	 * @param columnName Column name whose Index you need. Use Class VLDTN_FIELDS to pass column name.
	 * @param value The value you would like to use to search Validation 
	 * @return boolean This returns true if Validation search is performed successfully
	 */
	public boolean searchVldtnWithDetails(String columnName, String value) {
		String finalColumnNameXpath = null; 
		try {
			int columnIndex = getColumnIndex(columnName);
			if(columnIndex == 0) {
				logError("Failed to get correct column index : " + columnIndex + " for column " + columnName);
				return false;
			}
			else {
				finalColumnNameXpath = controlActions.perform_GetDynamicXPath(ValidationsPageLocators.COLUMN_NAMES_TXT, 
						"COLUMNINDEXNO", 
						Integer.toString(columnIndex));

				if(controlActions.perform_PutText(finalColumnNameXpath,value, false)) {
					Sync();
					logInfo("Searched validation with value : " + value + " for column : " + columnName);
					return true;
				}
			}
			return false;
		}
		catch(Exception e) {
			logError("Failed to search validation with value : " + value + " for column : " + columnName + " - "
					+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to search and get Validation detail as per column
	 * @author hingorani_a
	 * @param columnName Column name whose Index you need. Use Class VLDTN_FIELDS to pass column name.
	 * @return String This returns string value with the validation detail if found; else null
	 */
	public String getValidationDetails(String columnName) {
		WebElement ColumnValue = null; 
		try {
			int columnIndex = getColumnIndex(columnName);
			if(columnIndex == 0) {
				logError("Failed to get correct column index : " + columnIndex + " for column " + columnName);
				return null;
			}
			else {
				ColumnValue = controlActions.perform_GetElementByXPath(ValidationsPageLocators.VLDTN_COLUMN_VALUE, 
						"COLUMNINDEXNO", 
						Integer.toString(columnIndex));

				String value = ColumnValue.getText();
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
	 * This method is used to search and open Validation
	 * @author hingorani_a
	 * @param columnName Column name whose Index you need. Use Class VLDTN_FIELDS to pass column name.
	 * @param value The value you would like to use to search Validation 
	 * @return AddEditValidationsPage This returns object with error variable as false
	 * if searched Validation is opened successfully.
	 */
	public AddEditValidationsPage searchAndOpenVldtn(String columnName, String value) {
		AddEditValidationsPage aevp = new AddEditValidationsPage(driver);
		try {
			
			if(!searchVldtnWithDetails(columnName, value)) {
				aevp.error = true;
				return aevp;
			}
			
			controlActions.click(VldtnGridName);
			Sync();
			logInfo("Opened the Validation");
			return aevp;
		}
		catch(Exception e) {
			logError("Failed to open Validation - "
					+ e.getMessage());
			return aevp;
		}	
	}
	
	/** This method is used to select and open a Validation record by given index 
	 * @author hingorani_a
	 * @param numberOfRecord The number of record in Grid to be selected
	 * @return boolean true when mentioned record is opened
	 */	
	public boolean openValidationByIndex(int numberOfRecord) {
		int index = numberOfRecord - 1;

		try {

			VldtnGridNameLst.get(index).click();
			Sync();
			logInfo("Opened a Validation record at index " + numberOfRecord);
			return true;

		}
		catch(Exception e) {
			logError("Failed to open a Validation record at index " + numberOfRecord 
					+ e.getMessage());
			return false;
		}
	}
	
	/**
	 * This method is used to get count of Columns present for Validations Grid
	 * @author hingorani_a
	 * @param none 
	 * @return int This returns value of columns found 
	 */
	public int verifyColumnHeaders() {
		int columnIndex = 0;
		try {
			for(WebElement column : ColumnNamesTbl) {
				if(controlActions.perform_CheckIfElementTextContains(column, VLDTN_FIELDS.NAME)) 
				{
					columnIndex++;
				}
				else if(controlActions.perform_CheckIfElementTextContains(column, VLDTN_FIELDS.STATUS)) 
				{
					columnIndex++;
				}
				else if(controlActions.perform_CheckIfElementTextContains(column, VLDTN_FIELDS.CREATED)) 
				{
					columnIndex++;
				}
				else if(controlActions.perform_CheckIfElementTextContains(column, VLDTN_FIELDS.LAST_MODIFIED)) 
				{
					columnIndex++;
				}
				else if(controlActions.perform_CheckIfElementTextContains(column, VLDTN_FIELDS.IDENTIFIERS)) 
				{
					columnIndex++;
				}
			}	
			
			logInfo("Verified column headers count as " + columnIndex);
			return columnIndex;
		}
		catch(Exception e) {
			logError("Failed to verify column headers count "
					+ e.getMessage());
			return columnIndex;
		}	
	}
	
	/**
	 * This method is used to set a location on Validations grid
	 * @author hingorani_a
	 * @param location The location to be set
	 * @return boolean Return true when location is set successfully
	 */
	public boolean selectLocationForGrid(String location) {
		WebElement DropdwnOptn = null;
		
		try {
			controlActions.perform_ClickWithJavaScriptExecutor(GridLocationDdl);
			controlActions.click(LocationSearchTxt);
			LocationSearchTxt.clear();
			LocationSearchTxt.sendKeys(location);
			Sync();
			DropdwnOptn = controlActions.perform_GetElementByXPath(ValidationsPageLocators.DROPDWN_OPTN, 
					"DROPDOWNOPTION", location);
			controlActions.click(DropdwnOptn);
			logInfo("Location " + location + " is selected");
			Sync();
			return true;
		}
		catch(Exception e) {
			logError("Failed to select Location - " + location + " "
					+ e.getMessage());
			return false;
		}
	}
	
	/**
	 * This method is used to set filters on columns of Validations grid
	 * @author hingorani_a
	 * @param columnName Use class VLDTN_FIELDS to set name of column
	 * @param filterValue The value to be set for filter
	 * @return boolean Return true when filter value is set successfully
	 */
	public boolean setFilterToColumn(String columnName, String filterValue) {
		WebElement DropdwnOptn = null;
		WebElement ColumnNamesTxt = null;
		
		try {
			
			int columnIndex = getColumnIndex(columnName);
			if(columnIndex == 0) {
				logError("Failed to get correct column index : " + columnIndex + " for column " + columnName);
				return false;
			}
			else {
				switch(columnName) {
				case VLDTN_FIELDS.NAME:
				case VLDTN_FIELDS.IDENTIFIERS:
					if(searchVldtnWithDetails(columnName, filterValue))
						return true;
					else
						return false;
					
				case VLDTN_FIELDS.CREATED:
				case VLDTN_FIELDS.LAST_MODIFIED:
					String Date[]=filterValue.split("/");
					ColumnNamesTxt = controlActions.perform_GetElementByXPath(ValidationsPageLocators.COLUMN_NAMES_TXT, 
					"COLUMNINDEXNO", Integer.toString(columnIndex));
					
					ColumnNamesTxt.click();
					ColumnNamesTxt.sendKeys(Keys.chord(Keys.CONTROL, "a"));
					ColumnNamesTxt.sendKeys(Date[0]);
					ColumnNamesTxt.sendKeys(Date[1]);
					ColumnNamesTxt.sendKeys(Date[2]);
					Sync();
					logInfo("Added date - " + filterValue);
					return true;
					
				case VLDTN_FIELDS.STATUS:
					controlActions.perform_ClickWithJavaScriptExecutor(GridStatusDdl);
					DropdwnOptn = controlActions.perform_GetElementByXPath(ValidationsPageLocators.DROPDWN_OPTN, 
							"DROPDOWNOPTION", filterValue);
					controlActions.perform_waitUntilClickable(DropdwnOptn);
					controlActions.perform_ClickWithJavaScriptExecutor(DropdwnOptn);
					Sync();
					logInfo("For column : " + columnName + " filter value set as - " + filterValue);
					return true;
				
				default:
					logError("Incorrect column value - " + columnName);
					return false;
				}
			}
		}
		catch(Exception e) {
			logError("Failed to set filter to column - " + columnName + " "
					+ e.getMessage());
			return false;
		}
	}
	
	/** This method is used to clear applied filter on Validations grid iff one filter is applied
	 * @author hingorani_a
	 * @param none
	 * @return boolean return true when applied filter is cleared
	 */	
	public boolean clearAppliedFilter() {
		try {

			if(ClearGridFilterBtn.size() == 1) {
				ClearGridFilterBtn.get(0).click();
				Sync();
				logInfo("Cleared applied filter");
				return true;
			}
			else {
				logError("Could not clear applied filter as there are more than 1 applied filters");
				return false;
			}
		}
		catch(Exception e) {
			logError("Failed to clear applied filter " + e.getMessage());
			return false;
		}
	}
	
	/** This method is used to clear applied filter on Validations grid for mentioned column
	 * @author hingorani_a
	 * @param columnName Use class VLDTN_FIELDS to set name of column
	 * @return boolean return true when applied filter is cleared
	 */	
	public boolean clearAppliedFilter(String columnName) {
		WebElement ClearColumnFilterBtn = null; 
		
		try {
			int columnIndex = getColumnIndex(columnName);
			if(columnIndex == 0) {
				logError("Failed to get correct column index : " + columnIndex + " for column " + columnName);
				return false;
			}
			else {
				ClearColumnFilterBtn = controlActions.perform_GetElementByXPath(ValidationsPageLocators.CLEAR_COLUMN_FILTER_BTN, 
						"COLUMNINDEXNO", Integer.toString(columnIndex));
				ClearColumnFilterBtn.click();
				Sync();
				logInfo("Cleared applied filter for column - " + columnName);
				return true;
			}
		}
		catch(Exception e) {
			logError("Failed to clear applied filter for column - " + columnName
		+ e.getMessage());
			return false;
		}
	}
	
	/**
	 * This method is used to verify Validation detail against a column
	 * @author hingorani_a
	 * @param columnName Column name whose Index you need. Use Class VLDTN_FIELDS to pass column name.
	 * @param value The value to be compared to the value displayed for Column
	 * @return String This returns true if the value is found for the given column
	 */
	public boolean verifyValidationDetail(String columnName, String value) {
		List<WebElement> ColumnValue = null; 
		try {
			int columnIndex = getColumnIndex(columnName);
			if(columnIndex == 0) {
				logError("Failed to get correct column index : " + columnIndex + " for column " + columnName);
				return false;
			}
			else {
				ColumnValue = controlActions.perform_GetListOfElementsByXPath(ValidationsPageLocators.VLDTN_COLUMN_VALUE, 
						"COLUMNINDEXNO", Integer.toString(columnIndex));

				for(WebElement element : ColumnValue) {
					if(element.getText().contains(value)) {
						logInfo("For column : " + columnName + " value verified as - " + value);
						return true;
					}
				}
			}
			
			logError("Could not verify value for column : " + columnName);
			return false;
		}
		catch(Exception e) {
			logError("Failed to verify value for column : " + columnName + " - "
					+ e.getMessage());
			return false;
		}	
	}
	
	/**
	 * This method is used to get count of Columns present for Validations Grid
	 * @author hingorani_a
	 * @param none 
	 * @return int This returns value of columns found 
	 */
	public int verifyPaginationSizes() {
		int columnIndex = 0;
		String value = null;
		try {
			for(WebElement optn : GridPagintnOptns) {
				value = optn.getAttribute("value");
				if(value.equals(VLDTN_PAGES.SIZE_100)) 
				{
					columnIndex++;
				}
				else if(value.equals(VLDTN_PAGES.SIZE_200)) 
				{
					columnIndex++;
				}
				else if(value.equals(VLDTN_PAGES.SIZE_500)) 
				{
					columnIndex++;
				}
			}	
			
			logInfo("Verified pagination size values with count as " + columnIndex);
			return columnIndex;
		}
		catch(Exception e) {
			logError("Failed to verify pagination size values "
					+ e.getMessage());
			return columnIndex;
		}	
	}
	
	/**
	 * This method is used to click on Next pagination button
	 * @author hingorani_a
	 * @param none 
	 * @return boolean This returns true when Next button is clicked successfully
	 */
	public boolean clickNextPaginationButton() {
		try {
			NextPagintnLnk.click();
			Sync();
			logInfo("Next pagination button is clicked");
			return true;
		}
		catch(Exception e) {
			logError("Failed while clicking on Next pagination button - "
					+ e.getMessage());
			return false;
		}	
	}
	
	/**
	 * This method is used to click on Previous pagination button
	 * @author hingorani_a
	 * @param none 
	 * @return boolean This returns true when Previous button is clicked successfully
	 */
	public boolean clickPreviousPaginationButton() {
		try {
			PreviousPagintnLnk.click();
			Sync();
			logInfo("Previous pagination button is clicked");
			return true;
		}
		catch(Exception e) {
			logError("Failed while clicking on Previous pagination button - "
					+ e.getMessage());
			return false;
		}	
	}
	
	/**
	 * This method is used to apply settings like Sorting on column
	 * @author hingorani_a
	 * @param settingName Use Class COLUMN_SETTING to set value for column name
	 * @param settingValue If you want to check/uncheck any columns, provide the text
	 * to be used with separator '|' else for other settings keep it as null.
	 * Example: "Name-Check|Status-Uncheck"
	 * @return boolean This returns true after applying setting
	 */
	public boolean applySettingsForColumn(String settingName, String settingValue) {
//		List<WebElement> PopupOptionMnu = null;
		WebElement PopupOptionMnu = null;
		WebElement PopupColumnSetOptn = null;
		try {
//			Sync();
//			PopupOptionMnu = controlActions.perform_GetListOfElementsByXPath(ValidationsPageLocators.POPUP_OPTION_MNU, 
//					"POPUPOPTION", settingName);
			PopupOptionMnu = controlActions.perform_GetElementByXPath(ValidationsPageLocators.POPUP_OPTION_MNU, 
					"POPUPOPTION", settingName);

			switch(settingName) {
			case COLUMN_SETTING.SORTASCENDING:
			case COLUMN_SETTING.SORTDESCENDING:
//				for(WebElement option : PopupOptionMnu) {
//					if(controlActions.isElementDisplay(option)) {
//						controlActions.WaitforelementToBeClickable(option);
//						controlActions.clickOnElement(option);
//						logInfo("Applied " + settingName + " to column");
//						return true;
//					}
//				}
				
				controlActions.WaitforelementToBeClickable(PopupOptionMnu);
				PopupOptionMnu.click();
				Sync();
				logInfo("Applied " + settingName + " to column");
				return true;
//				return false;

			case COLUMN_SETTING.COLUMNS:
				
				String[] settings = CommonMethods.splitAndGetString(settingValue);
				String[] settingPairs = null;

				for(int i = 0; i < settings.length; i++) {
					settingPairs = CommonMethods.splitAndGetString(settings[i],"-");
					PopupColumnSetOptn = controlActions.perform_GetElementByXPath(ValidationsPageLocators.POPUP_COLUMN_SET_OPTN, 
							"COLUMNNAME", settingPairs[0]);
					if(settingPairs[1].equalsIgnoreCase(COLUMN_SETTING.UNCHECK) && PopupColumnSetOptn.isSelected()) {
						PopupColumnSetOptn.click();
						logInfo("Deselected for column " + settingPairs[0]);
					}
					else if(settingPairs[1].equalsIgnoreCase(COLUMN_SETTING.CHECK) && !PopupColumnSetOptn.isSelected()) {
						PopupColumnSetOptn.click();
						logInfo("Selected for column " + settingPairs[0]);
					}
				}
				
				PopupColumnApplyBtn.click();
				logInfo("Applied settings for column by clicking on Apply button");
				return true;

			default:
				logInfo("Incorrect setting provided : " + settingValue);
				return false;
			}
		}
		catch(Exception e) {
			logError("Failed to apply : " + settingName + " on column " + " - "
					+ e.getMessage());
			return false;
		}	
	}
	
	/**
	 * This method is used to click on Settings dropdown link for a column
	 * @author hingorani_a
	 * @param columnName Use Class VLDTN_FIELDS to set value for column name
	 * @return boolean This returns true once we click on Settings dropdown
	 */
	public boolean clickColumnDropDown(String columnName) {
		WebElement ColumnSettingsLnk = null;
		try {
			ColumnSettingsLnk = controlActions.perform_GetElementByXPath(ValidationsPageLocators.COLUMN_SETTINGS_LNK, 
					"COLUMNNAME", columnName);
//			Sync();
			controlActions.WaitforelementToBeClickable(ColumnSettingsLnk);
			ColumnSettingsLnk.click();
			Sync();
			logInfo("Clicked on column : " + columnName + " settings link");
			return true;
		}
		catch(Exception e) {
			logError("Failed to click on column : " + columnName + " settings link " + " - "
					+ e.getMessage());
			return false;
		}	
	}
	
	/**
	 * This method is used to open settings popup and then apply settings
	 *  like Sorting, Filtering on column
	 * @author hingorani_a
	 * @param columnName Use Class VLDTN_FIELDS to set value for column name
	 * @param settingName Use Class COLUMN_SETTING to set value for column name
	 * @param settingValue If you want to check/uncheck any columns, provide the text
	 * to be used with separator '|' else for other settings keep it as null.
	 * Example: "Name-Check|Status-Uncheck"
	 * @return boolean This returns true after applying setting
	 */
	public boolean openAndApplySettingsForColumn(String columnName, String settingName, String settingValue) {
		try {
			if(!clickColumnDropDown(columnName)) {
				return false;
			}

			if(!applySettingsForColumn(settingName, settingValue)) {
				return false;
			}

			Sync();
			logInfo("Successfully applied setting " + settingName + " to column " + columnName);
			return true;
		}
		catch(Exception e) {
			logError("Failed to apply setting " + settingName + " to column " + columnName + " - "
					+ e.getMessage());
			return false;
		}	
	}
	
	/**
	 * This method is used to get list of Elements for a column of Validation grid
	 * @author hingorani_a
	 * @param columnName Use Class COLUMNHEADER to set value for column name
	 * @return List<WebElement> This returns list of WebElements for given column
	 */
	public List<WebElement> getListOfElementsForVldtn(String columnName) {
		List<WebElement> ColumnValue = null;
		try {
			int columnIndex = getColumnIndex(columnName);
			if(columnIndex == 0) {
				logError("Failed to get correct column index : " + columnIndex + " for column " + columnName);
				return ColumnValue;
			}
			else {
				ColumnValue = controlActions.perform_GetListOfElementsByXPath(ValidationsPageLocators.GRID_COLUMN_VALUE, 
						"COLUMNINDEXNO", Integer.toString(columnIndex));
				logInfo("Retrieved list of elements for column : " + columnName);
				return ColumnValue;
			}
		}
		catch(Exception e) {
			logError("Failed to get list of elements for column : " + columnName + " - "
					+ e.getMessage());
			return ColumnValue;
		}	
	}
	
	/**
	 * This method is used to get count of Columns present for Validations Grid
	 * @author hingorani_a
	 * @param columnNames List of column names to be verified 
	 * @return int This returns value of columns found 
	 */
	public int verifyColumnHeaders(List<String> columnNames) {
		int columnIndex = 0;
		try {
			
			for(int i = 0; i < ColumnNamesTbl.size(); i++) {
				if(controlActions.perform_CheckIfElementTextContains(ColumnNamesTbl.get(i), columnNames.get(i))) 
				{
					columnIndex++;
				}
			}
			
			logInfo("Verified column headers count as " + columnIndex);
			return columnIndex;
		}
		catch(Exception e) {
			logError("Failed to verify column headers count "
					+ e.getMessage());
			return columnIndex;
		}	
	}
	

	public static class VLDTN_FIELDS{
		public static final String NAME = "Name";
		public static final String STATUS = "Status";
		public static final String CREATED = "Created";
		public static final String LAST_MODIFIED = "Last Modified";
		public static final String IDENTIFIERS = "Identifiers";	
	}
	
	public static class VLDTN_STATUS{
		public static final String IN_PROGRESS = "In Progress";
		public static final String COMPLETE = "Complete";
	}
	
	public static class VLDTN_PAGES{
		public static final String SIZE_100 = "100";
		public static final String SIZE_200 = "200";
		public static final String SIZE_500 = "500";
	}
	
	public static class COLUMN_SETTING{
		public static final String SORTASCENDING = "Sort Ascending";
		public static final String SORTDESCENDING = "Sort Descending";
		public static final String COLUMNS = "Columns";
		public static final String CHECK = "check";
		public static final String UNCHECK = "uncheck";
		
	}
}
