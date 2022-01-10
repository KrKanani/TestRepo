package com.project.safetychain.webapp.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FORMRESOURCETYPES;
import com.project.safetychain.webapp.pageobjects.ManageRequirementPage.Sorting;
import com.project.utilities.ControlActions;
import com.project.utilities.DateTime;

public class FSQABrowserPage extends CommonPage{

	WebDriver driver;
	WebDriverWait wait;
	ControlActions controlActions;
	DateTime datetime;

	public FSQABrowserPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 40);
		controlActions = new ControlActions(driver);	
		datetime= new DateTime(driver);;
	}	

	/**
	 * Page Objects
	 */

	@FindBy(id = FSQABrowserPageLocators.VIEW_RECORDS_BTN)
	public WebElement ViewRecordsBtn;

	@FindBy(id = FSQABrowserPageLocators.SIGN_RECORDS_BTN)
	public WebElement SignRecordsBtn;

	@FindBy(id = FSQABrowserPageLocators.SELECT_ALL_CHK)
	public WebElement SelectAllChk;

	@FindBy(id = FSQABrowserPageLocators.UPDATE_DUEBY_INPUT)
	public WebElement UpdateDuebyInput;

	@FindBy(id = FSQABrowserPageLocators.DATE_RANGE_CALNDR_BTN)
	public WebElement DateRangeCalndrBtn;

	@FindBy(id = FSQABrowserPageLocators.DATE_RANGE_FROM_TXT)
	public WebElement DateRangeFromTxt;

	@FindBy(id = FSQABrowserPageLocators.DATE_RANGE_TO_TXT)
	public WebElement DateRangeToTxt;

	@FindBy(className = FSQABrowserPageLocators.DATE_RANGE_CLEAR_LNK)
	public WebElement DateRangeClearLnk;

	@FindBy(xpath = FSQABrowserPageLocators.FORM_TASKNAME_INPUT)
	public WebElement FormTaskNameInput;

	@FindBy(xpath = FSQABrowserPageLocators.NAVIGATION_TABS)
	public List<WebElement> NavigationTabs;

	@FindBy(xpath = FSQABrowserPageLocators.GRID_COLUMNS)
	public List<WebElement> GridColumns;

	@FindBy(xpath = FSQABrowserPageLocators.FILTER_TXT)
	public List<WebElement> FilterTxt;

	@FindBy(xpath = FSQABrowserPageLocators.FILTER_BTN)
	public List<WebElement> FilterBtn;	

	@FindBy(xpath = FSQABrowserPageLocators.CLEAR_BTN)
	public List<WebElement> ClearBtn;

	@FindBy(xpath = FSQABrowserPageLocators.DROPDOWN_ARROW)
	public WebElement DropdownArrow;

	@FindBy(xpath = FSQABrowserPageLocators.FORM_ROW)
	public WebElement FormRow;

	@FindBy(xpath = FSQABrowserPageLocators.CALLOUT_MNU)
	public WebElement CalloutMenu;

	@FindBy(xpath = FSQABrowserPageLocators.EDIT_FORM_MNU)
	public WebElement EditFormMenu;

	@FindBy(xpath = FSQABrowserPageLocators.ASSIGN_TASK_MNU)
	public WebElement AssignTaskMenu;

	@FindBy(xpath = FSQABrowserPageLocators.YES_BTN)
	public WebElement YesBtn;

	@FindBy(xpath = FSQABrowserPageLocators.ASSIGN_BTN)
	public WebElement AssignBtn;

	@FindBy(xpath = FSQABrowserPageLocators.RECALL_MNU)
	public WebElement RecallMnu;

	@FindBy(xpath = FSQABrowserPageLocators.RECALL_YES_BTN)
	public WebElement RecallYesBtn;

	@FindBy(xpath = FSQABrowserPageLocators.TOTAL_FORM_LST)
	public List<WebElement> TotalFormsLst;

	@FindBy(xpath = FSQABrowserPageLocators.TOTAL_RECORD_LST)
	public List<WebElement> TotalRecordsLst;

	@FindBy(xpath = FSQABrowserPageLocators.TOTAL_TASK_LST)
	public List<WebElement> TotalTasksLst;

	@FindBy(xpath = FSQABrowserPageLocators.RECORDS_BREADCRUMB)
	public WebElement RecordBreadcrumb;

	@FindBy(xpath = FSQABrowserPageLocators.DUEBY_INPUT)
	public WebElement DueByInput;

	@FindBy(xpath = FSQABrowserPageLocators.RECORD_SIGN_ICON)
	public WebElement RecordSignIcon; 

	@FindBy(xpath = FSQABrowserPageLocators.RECORD_SIGNOFF_DETAILS)
	public List<WebElement> RecordSignoffDetails;

	@FindBy(xpath = RecordSignoffPageLocators.RECORD_SIGNOFF_CLOSE_BTN)
	public WebElement RecordSignoffCloseBtn;

	@FindBy(xpath = FSQABrowserPageLocators.FORM_TASK_NOTES_INPUT)
	public WebElement FormTaskNotesInput;

	@FindBy(xpath = FSQABrowserPageLocators.CHNGE_DUE_BY_TASK_MNU)
	public WebElement ChngeDueByTaskMnu;

	@FindBy(xpath = FSQABrowserPageLocators.UPDATE_TASK_NAME_TXT)
	public WebElement UpdateTaskNameTxt;

	@FindBy(xpath = FSQABrowserPageLocators.SAVE_TASK_BTN)
	public WebElement SaveTaskBtn;

	@FindBy(xpath = FSQABrowserPageLocators.REASSIGN_TASK_MNU)
	public WebElement ReassignTaskMnu;

	@FindBy(xpath = FSQABrowserPageLocators.REASSIGN_TASK_BTN)
	public WebElement ReassignTaskBtn;

	@FindBy(xpath = FSQABrowserPageLocators.RECORD_GRID_CHK)
	public List<WebElement> RecordGridChk;

	@FindBy(xpath = FSQABrowserPageLocators.DATE_RANGE_CLEAR_BTN)
	public WebElement DateRangeClearBtn;

	@FindBy(xpath = FSQABrowserPageLocators.DATE_RANGE_FILTER_BTN)
	public WebElement DateRangeFilterBtn;

	@FindBy(xpath = FSQABrowserPageLocators.DATE_RANGE_FROM_LBL)
	public WebElement DateRangeFromLbl;

	@FindBy(xpath = FSQABrowserPageLocators.DATE_RANGE_TO_LBL)
	public WebElement DateRangeToLbl;

	@FindBy(xpath = FSQABrowserPageLocators.GRIDHEADERS)
	public List<WebElement> GridHeaders;

	@FindBy(xpath = FormsManagerPageLocators.VERIFY_FORM_BTN)
	public WebElement VerifyFormBtn;

	@FindBy(xpath = FormsManagerPageLocators.DIRECTOBS_USERNAME_TXT)
	public WebElement DirectObsUserNameTxt;

	@FindBy(xpath = FormsManagerPageLocators.VERIFICATION_PIN_TXT)
	public WebElement VerificationPinTxt;

	@FindBy(xpath = FormsManagerPageLocators.ISCOMPLIANT_TOGGLE_YES)
	public WebElement IsCompliantToggleYes;

	@FindBy(xpath =FSQABrowserPageLocators.DIR_OBS_VERIFY_SAVE_BTN)
	public WebElement DirObsVerifySaveBtn;
	
	@FindBy(xpath =FSQABrowserTaskPageLocators.TASK_SUMMARY_TITLE)
	public WebElement TaskSummaryTitle;
	
	@FindBy(xpath =FSQABrowserTaskPageLocators.TASK_STATUS)
	public WebElement TaskStatus;
	
	@FindBy(xpath =FSQABrowserTaskPageLocators.OUTER_DONUT)
	public WebElement OuterDonut;
	
	@FindBy(xpath =FSQABrowserTaskPageLocators.OUTER_DONUT1)
	public WebElement OuterDonut1;
	
	@FindBy(xpath =FSQABrowserTaskPageLocators.INNER_DONUT)
	public WebElement InnerDonut;
	
	@FindBy(xpath =FSQABrowserTaskPageLocators.TOOL_TIP)
	public WebElement ToolTip;
	
	@FindBy(xpath =FSQABrowserTaskPageLocators.STARTED)
	public WebElement Started;
	
	@FindBy(xpath =FSQABrowserTaskPageLocators.NON_STARTED)
	public WebElement NonStarted;
	
	@FindBy(xpath =FSQABrowserTaskPageLocators.PAST_DUE)
	public WebElement PastDue;
	
	@FindBy(xpath =FSQABrowserTaskPageLocators.DUE_TODAY)
	public WebElement DueToday;
	
	@FindBy(xpath =FSQABrowserTaskPageLocators.DUE_LATER)
	public WebElement DueLater;
	
	@FindBy(xpath =FSQABrowserTaskPageLocators.NO_DUE_DATE)
	public WebElement NoDueDate;

	@FindBy(xpath =FSQABrowserPageLocators.LOCATIONS_Tab)
	public WebElement LocationsTab;
	
	@FindBy(xpath =FSQABrowserPageLocators.LOCATIONS_LISTS)
	public List<WebElement> LocationsLists;
	
	@FindBy(xpath =FSQABrowserPageLocators.NO_DATA_FOUND)
	public WebElement NoDataFound;
	
	@FindBy(id =FSQABrowserPageLocators.SEARCH_TEXT)
	public WebElement SearchText;
	
	@FindBy(id =FSQABrowserPageLocators.TOOL_TIP_CONTENT)
	public WebElement ToolTipContent;
	
	@FindBy(xpath = FSQABrowserFormsPageLocators.SELCTED_LOCATION_LBL)
	public WebElement SelectedLocationLbl;
	


	/**
	 * Functions
	 */

	/** Start - FSQA Browser */

	/**
	 * This method is used to click on FSQA tabs that is, Details, Forms, Documents,
	 * Tasks and Records
	 * @author hingorani_a
	 * @param tabName Use Class FSQATAB to set value for tab name
	 * @return boolean This returns true once we are navigated to required tab
	 */
	public boolean navigateToFSQATab(String tabName) {
		try {
			//			Sync();
			for(WebElement tab : NavigationTabs) {
				if(tab.getText().equalsIgnoreCase(tabName)) {
					controlActions.WaitforelementToBeClickable(tab);
					//					controlActions.clickOnElement(tab);
					tab.click();
					Sync();
					logInfo("Clicked on FSQA tab " + tabName);
					return true;
				}
			}		
			logInfo("Could not click on FSQA tab " + tabName );
			return false;
		}
		catch(Exception e) {
			logError("Failed to click on FSQA tab : " + tabName + " - "
					+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to click on Settings dropdown link for a column
	 * @author hingorani_a
	 * @param columnName Use Class COLUMNHEADER to set value for column name
	 * @return boolean This returns true once we click on Settings dropdown
	 */
	public boolean clickColumnDropDown(String columnName) {
		WebElement ColumnSettingsLnk = null;
		try {
			ColumnSettingsLnk = controlActions.perform_GetElementByXPath(FSQABrowserPageLocators.COLUMN_SETTINGS_LNK, 
					"COLUMNNAME", 
					columnName);
			Sync();
			controlActions.WaitforelementToBeClickable(ColumnSettingsLnk);
			//			controlActions.clickOnElement(ColumnSettingsLnk);
			ColumnSettingsLnk.click();
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
	 * This method is used to apply settings like Sorting, Filtering on column
	 * @author hingorani_a
	 * @param settingName Use Class COLUMNSETTING to set value for column name
	 * @param settingValue If you want to use Filter Setting, provide the text
	 * to be used for filter else for other settings keep it as null.
	 * @return boolean This returns true after applying setting
	 */
	public boolean applySettingsForColumn(String settingName, String settingValue) {
		List<WebElement> PopupOptionMnu = null;
		try {
			Sync();
			PopupOptionMnu = controlActions.perform_GetListOfElementsByXPath(FSQABrowserPageLocators.POPUP_OPTION_MNU, 
					"POPUPOPTION", 
					settingName);

			switch(settingName) {
			case COLUMNSETTING.SORTASCENDING:
			case COLUMNSETTING.SORTDESCENDING:
				for(WebElement option : PopupOptionMnu) {
					if(controlActions.isElementDisplay(option)) {
						controlActions.WaitforelementToBeClickable(option);
						//						controlActions.clickOnElement(option);
						option.click();
						logInfo("Applied " + settingName + " to column");
						return true;
					}
				}
				return false;

			case COLUMNSETTING.COLUMNS:
				// yet to code
				logInfo("No code for Setting - " + settingName);
				return true;

			case COLUMNSETTING.FILTER:
				for(WebElement option : PopupOptionMnu) {
					if(controlActions.isElementDisplay(option)) {
						controlActions.WaitforelementToBeClickable(option);
						controlActions.clickOnElement(option);
						//						option.click();
					}
				}
				for(WebElement filterTxt : FilterTxt) {
					if(controlActions.isElementDisplay(filterTxt)) {
						controlActions.WaitforelementToBeClickable(filterTxt);
						filterTxt.clear();
						//						controlActions.sendKeys(filterTxt, settingValue);
						filterTxt.sendKeys(settingValue);
					}
				}
				for(WebElement filterBtn : FilterBtn) {
					if(controlActions.isElementDisplay(filterBtn)) {
						controlActions.WaitforelementToBeClickable(filterBtn);
						//						controlActions.clickOnElement(filterBtn);
						filterBtn.click();
					}
				}
				logInfo("Applied " + settingName + " on column with " + settingValue);
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
	 * This method is used to open settings popup and then apply settings
	 *  like Sorting, Filtering on column
	 * @author hingorani_a
	 * @param columnName Use Class COLUMNHEADER to set value for column name
	 * @param settingName Use Class COLUMNSETTING to set value for column name
	 * @param settingValue If you want to use Filter Setting, provide the text
	 * to be used for filter else for other settings keep it as null.
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
	 * @author choubey_a
	 */

	public boolean openAndApplySettingsForTaskColumn(String columnName,String settingName, String taskname[]) {
		try {
			for(int i=0;i<taskname.length;i++) {

				if(!clickColumnDropDown(columnName)) {
					return false;
				}

				if(!applySettingsForColumn(settingName, taskname[i])) {
					return false;
				}
				WebElement Task = controlActions.perform_GetElementByXPath(FSQABrowserPageLocators.TASK_ROW,"TASKNAME", taskname[i]);
				if(!controlActions.isElementDisplay(Task)) {
					logError("Task is not visible");
					return false;
				}
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
	 * This method is used to find Column Index for the column present in FSQA Grid
	 * @author hingorani_a
	 * @param columnName Column name whose Index you need. Use Class COLUMNHEADER to pass column name.
	 * This Index is in turn used in creating dynamic xpaths. 
	 * @return int This returns value of column index. 
	 * Returns 0, if column is not found
	 */
	public int getColumnIndex(String columnName) {
		int columnIndex = 0; 
		try {
			for(WebElement column : GridColumns) {
				if(column.getText().equalsIgnoreCase(columnName)) {
					columnIndex++;
					break;
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
	 * This method is used to apply filter and then open the displayed record entry from Grid
	 * @author hingorani_a
	 * @param columnName Use Class COLUMNHEADER to set value for column name
	 * @param value The text to filter and then open the displayed record entry
	 * @return boolean This returns true after opening the displayed record entry
	 */
	public boolean applyFilterAndOpenForDetails(String columnName, String value) {
		List<WebElement> ColumnValue = null;
		try {
			int columnIndex = getColumnIndex(columnName);
			if(columnIndex == 0) {
				logError("Failed to get correct column index : " + columnIndex + " for column " + columnName);
				return false;
			}
			else {
				if(!openAndApplySettingsForColumn(columnName, COLUMNSETTING.FILTER, value)) {
					return false;
				}

				ColumnValue = controlActions.perform_GetListOfElementsByXPath(FSQABrowserPageLocators.GRID_COLUMN_VALUE, 
						"COLUMNINDEXNO", 
						Integer.toString(columnIndex));
				for(WebElement colVal : ColumnValue) {
					if(controlActions.isElementDisplay(colVal)) {
						controlActions.WaitforelementToBeClickable(colVal);
						//						Sync();
						controlActions.doubleClick(colVal);
						Sync();
						logInfo("Opened details for Column - " + columnName + " with value - " + value);
						return true;
					}
				}	
				logInfo("Could not open details for Column - " + columnName + " with value - " + value);
				return false;
			}
		}
		catch(Exception e) {
			logError("Failed to open record for value : " + value + " for column : " + columnName + " - "
					+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to verify the column detail of the displayed record entry from Grid
	 * @author hingorani_a
	 * @param columnName Use Class COLUMNHEADER to set value for column name
	 * @param value The text to you need to verify against the record on Grid
	 * @return boolean This returns true after verifying the column detail 
	 */
	public boolean verifyGridValuesForColumn(String columnName, String value) {
		WebElement ColumnValue = null; 
		try {
			int columnIndex = getColumnIndex(columnName);
			if(columnIndex == 0) {
				logError("Failed to get correct column index : " + columnIndex + " for column " + columnName);
				return false;
			}
			else {		
				//				threadsleep(3000);
				ColumnValue = controlActions.perform_GetElementByXPath(FSQABrowserPageLocators.GRID_COLUMN_VALUE, 
						"COLUMNINDEXNO", 
						Integer.toString(columnIndex));
				//				threadsleep(3000);
				if(controlActions.isElementDisplay(ColumnValue)) {
					Sync();
					if(ColumnValue.getText().equalsIgnoreCase(value)) {
						logInfo("Verified for - " + columnName + " value is - " + value);
						return true;
					}
				}	
				logInfo("Could not verify for - " + columnName + " value is - " + value);
				return false;
			}
		}
		catch(Exception e) {
			logError("Failed to open verify for - " + columnName + " value is - " + value + " - "
					+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to select resource drop down value
	 * @author choubey_a
	 * @param resourcename The name of the resource to be selected
	 * @return boolean This returns true when drop down value is selected
	 */
	public boolean selectDropDownValue(String resourcename) {
		//		WebElement resourceSelection = null;
		try {
			//			controlActions.clickOnElement(DropdownArrow);
			//			Sync();
			//			resourceSelection = controlActions.perform_GetElementByXPath(FSQABrowserPageLocators.DROPDOWN_LST,"RESOURCES",resourcename);
			//			controlActions.clickOnElement(resourceSelection);
			//			Sync();
			String select = "li[class='k-item ng-scope'][role='option']";
			controlActions.JSSetValueFromList(driver, select, resourcename);
			Sync();
			logInfo("Drop Down value" +  resourcename  + "selected");
			return true;
		}
		catch(Exception e) {
			logError("Failed to set value for Drop Down element " + resourcename + " :"
					+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to select resource drop down value and navigate to mentioned Tab
	 * @author choubey_a
	 * @param res The name of resource
	 * @param fsqatab The tab name to be selected
	 * @return boolean This returns true when it the values is selected and navigation to mentioned tab is done
	 */
	public boolean selectResourceDropDownandNavigate(String res,String fsqatab) {

		try {
			Sync();
			logInfo("Selecting resource"+ res + fsqatab);

			if(res.equalsIgnoreCase("CUSTOMERS")) {
				if(!selectDropDownValue(FORMRESOURCETYPES.CUSTOMERS)) {
					return false;
				}
			}

			else if(res.equalsIgnoreCase("EQUIPMENT")) {
				if(!selectDropDownValue(FORMRESOURCETYPES.EQUIPMENT)) {
					return false;
				}
			}

			else if(res.equalsIgnoreCase("ITEMS")) {
				if(!selectDropDownValue(FORMRESOURCETYPES.ITEMS)) {
					return false;
				}
			}

			else if(res.equalsIgnoreCase("SUPPLIERS")) {
				if(!selectDropDownValue(FORMRESOURCETYPES.SUPPLIERS)) {
					return false;
				}
			}


			if(fsqatab.equalsIgnoreCase("DETAILS")) {
				if(!navigateToFSQATab(FSQATAB.DETAILS)) {
					return false;
				}
			}

			else if(fsqatab.equalsIgnoreCase("DOCUMENTS")) {
				if(!navigateToFSQATab(FSQATAB.DOCUMENTS)) {
					return false;
				}
			}

			else if(fsqatab.equalsIgnoreCase("FORMS")) {
				if(!navigateToFSQATab(FSQATAB.FORMS)) {
					return false;
				}
			}

			else if(fsqatab.equalsIgnoreCase("TASKS")) {
				if(!navigateToFSQATab(FSQATAB.TASKS)) {
					return false;
				}
			}

			else if(fsqatab.equalsIgnoreCase("RECORDS")) {
				if(!navigateToFSQATab(FSQATAB.RECORDS)) {
					return false;
				}
			}			
			logInfo("Switched to category : " + res + " and tab : " + fsqatab);
			return true ;			
		}
		catch(Exception e) {
			logError("Failed to switch to category : " + res + " and tab : " + fsqatab
					+ e.getMessage());
			return false;
		}
	}	

	/**
	 * This method is filter FSQA Record and select the check-box for the same
	 * @author hingorani_a
	 * @param columnName Use Class COLUMNHEADER to set value for column name
	 * @param value The text to filter and then select the displayed FSQA Record
	 * @return boolean This returns true after selecting FSQA Record's check-box
	 */
	public boolean filterAndSelectRecordInGrid(String columnName, String value, boolean selectAll) {
		WebElement GridRecordChk = null;
		try {
			if(!openAndApplySettingsForColumn(columnName, COLUMNSETTING.FILTER, value)) {
				return false;
			}
			if(selectAll) {
				controlActions.WaitforelementToBeClickable(SelectAllChk);
				controlActions.clickOnElement(SelectAllChk);
				logInfo("Selected all checkbox for Record with value - " + value);	
				return true;
			}
			else {
				GridRecordChk = controlActions.perform_GetElementByXPath(FSQABrowserPageLocators.GRID_RECORD_CHK, 
						"TASKPARAMETER", 
						value);
				controlActions.WaitforelementToBeClickable(GridRecordChk);
				controlActions.clickOnElement(GridRecordChk);
				Sync();
				logInfo("Selected checkbox for Record with value - " + value);	
				return true;
			}
		}
		catch(Exception e) {
			logError("Failed to select records with value - " + value + " - " 
					+ e.getMessage());
			return false;
		}	
	}

	/** This method is used to click on Select All checkbox of grid
	 * @author hingorani_a
	 * @param none
	 * @return boolean true when Select All checkbox is clicked
	 */	
	public boolean clickSelectAllChk() {
		try {
			if(!SelectAllChk.isSelected()) {
				controlActions.click(SelectAllChk);
				logInfo("Clicked on Select All checkbox");
				return true;
			}

			logInfo("Could not click on Select All checkbox");
			return false;
		}
		catch(Exception e) {
			logError("Failed to click on Select All checkbox - " + e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to get the column detail of the displayed record entry from Grid
	 * @author hingorani_a
	 * @param columnName Use Class COLUMNHEADER to set value for column name
	 * @return String This returns true after verifying the column detail 
	 */
	public String getGridValueForColumn(String columnName) {
		WebElement ColumnValue = null; 
		String gridValue = null;

		try {
			int columnIndex = getColumnIndex(columnName);
			if(columnIndex == 0) {
				logError("Failed to get correct column index : " + columnIndex + " for column " + columnName);
				return null;
			}
			else {		
				//				threadsleep(3000);
				ColumnValue = controlActions.perform_GetElementByXPath(FSQABrowserPageLocators.GRID_COLUMN_VALUE, 
						"COLUMNINDEXNO", 
						Integer.toString(columnIndex));
				//				threadsleep(3000);
				if(controlActions.isElementDisplay(ColumnValue)) {
					gridValue = ColumnValue.getText();
					logInfo("Value for - " + columnName + " is - " + gridValue);
					return gridValue;
				}	
				logInfo("Could not get value for - " + columnName);
				return null;
			}
		}
		catch(Exception e) {
			logError("Failed to get value for - " + columnName 
					+ e.getMessage());
			return null;
		}	
	}

	/**
	 * This method is used to clear applied Filter settings on column
	 * @author hingorani_a
	 * @param none 
	 * @return boolean This returns true after clearing applied filter 
	 */
	public boolean clearAppliedFilterForColumn() {
		List<WebElement> PopupOptionMnu = null;
		try {
			Sync();
			PopupOptionMnu = controlActions.perform_GetListOfElementsByXPath(FSQABrowserPageLocators.POPUP_OPTION_MNU, 
					"POPUPOPTION", 
					COLUMNSETTING.FILTER);


			for(WebElement option : PopupOptionMnu) {
				if(controlActions.isElementDisplay(option)) {
					controlActions.WaitforelementToBeClickable(option);
					controlActions.clickOnElement(option);
				}
			}
			for(WebElement clearBtn : ClearBtn) {
				if(controlActions.isElementDisplay(clearBtn)) {
					controlActions.WaitforelementToBeClickable(clearBtn);
					controlActions.clickOnElement(clearBtn);
				}
			}
			logInfo("Applied filter is cleared");
			return true;

		}
		catch(Exception e) {
			logError("Failed to clear applied filter : "
					+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to open settings popup and then clear applied filter for a column
	 * @author hingorani_a
	 * @param columnName Use Class COLUMNHEADER to set value for column name
	 * @return boolean This returns true after clearing applied filter
	 */
	public boolean openAndClearAppliedFilterForColumn(String columnName) {
		try {
			if(!clickColumnDropDown(columnName)) {
				return false;
			}

			if(!clearAppliedFilterForColumn()) {
				return false;
			}

			Sync();
			logInfo("Successfully cleared applied setting of column " + columnName);
			return true;
		}
		catch(Exception e) {
			logError("Failed to clear filter of column " + columnName + " - "
					+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to open the displayed record entry from Grid
	 * @author hingorani_a
	 * @param none
	 * @return boolean This returns true after opening the displayed record entry
	 */
	public boolean openForDetails() {
		WebElement ColumnValue = null;
		try {
			ColumnValue = controlActions.perform_GetElementByXPath(FSQABrowserPageLocators.GRID_COLUMN_VALUE, 
					"COLUMNINDEXNO", "3");

			controlActions.WaitforelementToBeClickable(ColumnValue);
			controlActions.doubleClick(ColumnValue);
			Sync();
			logInfo("Opened details for Grid record");
			return true;
		}
		catch(Exception e) {
			logError("Failed to open details for Grid record "
					+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to open the mentioned number of record entry from Grid
	 * @author hingorani_a
	 * @param numberOfRecord The number of record in Grid to be opened
	 * @return boolean This returns true after opening the record entry
	 */
	public boolean openForDetails(int numberOfRecord) {
		List<WebElement> ColumnValue = null;
		try {

			ColumnValue = controlActions.perform_GetListOfElementsByXPath(FSQABrowserPageLocators.GRID_COLUMN_VALUE, 
					"COLUMNINDEXNO", "3");

			if(!ColumnValue.isEmpty()) {
				controlActions.WaitforelementToBeClickable(ColumnValue.get(numberOfRecord-1));
				controlActions.doubleClick(ColumnValue.get(numberOfRecord-1));
				Sync();
				logInfo("Opened details for Grid record at " + numberOfRecord + " entry");
				return true;
			}

			logError("Could not open details for Grid record at " + numberOfRecord + " entry");
			return false;
		}
		catch(Exception e) {
			logError("Failed to open details for Grid record at " + numberOfRecord + " entry"
					+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to search and then select unique category/subcategory/instance
	 * @author ahmed_tw
	 * @param String : Name of category/subcategory/instance that needs to be searched
	 * @return boolean This returns true if given unique category/subcategory/instance is searched and selected
	 */
	public boolean searchSelect(String toSearch) {
		try {

			WebElement mainfilter = driver.findElement(By.id("filterText"));
			controlActions.sendKeys(mainfilter, toSearch);
			Sync();
			WebElement instanceSearchResult = controlActions.perform_GetElementByXPath(
					FSQABrowserPageLocators.INSTANCE_SEARCH_RESULT, "INSTANCE_NAME", toSearch);
			controlActions.clickElement(instanceSearchResult);
			logInfo("Searched and Selected instance - > " + toSearch);
		} catch (Exception e) {
			logError("Failed searching and selecting - " + e.getMessage());
			return false;

		}
		Sync();
		return true;
	}

	/**
	 * This method is used to get the column detail of the mentioned record entry from Grid
	 * @author hingorani_a
	 * @param columnName Use Class COLUMNHEADER to set value for column name
	 * @param index The number of record to be fetched from Grid. Starts from 1
	 * @return String This returns true after verifying the column detail 
	 */
	public String getGridValueForColumn(String columnName, int index) {
		List<WebElement> ColumnValue = null; 
		String gridValue = null;

		try {
			int columnIndex = getColumnIndex(columnName);
			if(columnIndex == 0) {
				logError("Failed to get correct column index : " + columnIndex + " for column " + columnName);
				return null;
			}
			else {		
				ColumnValue = controlActions.perform_GetListOfElementsByXPath(FSQABrowserPageLocators.GRID_COLUMN_VALUE, 
						"COLUMNINDEXNO", 
						Integer.toString(columnIndex));
				if(controlActions.isElementDisplayed(ColumnValue.get(index-1))) {
					gridValue = ColumnValue.get(index-1).getText();
					logInfo("Value for - " + columnName + " is - " + gridValue);
					return gridValue;
				}	
				logInfo("Could not get value for - " + columnName);
				return null;
			}
		}
		catch(Exception e) {
			logError("Failed to get value for - " + columnName 
					+ e.getMessage());
			return null;
		}	
	}
	
	/**
	 * This method is used to search unique category/subcategory/instance
	 * @author jadhav_akan
	 * @param toSearch : Name of category/subcategory/instance that needs to be searched
	 * @return boolean This returns true if given unique category/subcategory/instance is searched and not found
	 */
	public boolean searchNoData(String toSearch) {
		try {
			WebElement mainfilter = SearchText;
			controlActions.sendKeys(mainfilter, toSearch);
			WebElement noDataPopup = NoDataFound;
			
				if(noDataPopup.isDisplayed()) {
					logInfo("Succesfully no Data found popup is displayed for" + toSearch);
					return true;
				}
		    logInfo("Failed to display mo data found popup");
		    return false;
		    }
		catch(Exception e) {
			logError("Failed to display mo data found popup" + e.getMessage());
			return false;
		}
		
	}

	/** End - FSQA Browser */

	/** Start - FSQA Forms */

	/**
	 * This method is used to edit forms
	 * @author choubey_a
	 * @param formName is the name of the form to edit
	 * @param fdp 
	 * @boolean will return true when the form will be edited
	 */
	public boolean editForm(String formName,FormDesignerPage fdp) {
		try {
			controlActions.action.moveToElement(FormRow).build().perform();
			threadsleep(2000);
			controlActions.clickOnElement(CalloutMenu);
			controlActions.WaitforelementToBeClickable(EditFormMenu);
			threadsleep(3000);
			controlActions.clickOnElement(EditFormMenu);
			threadsleep(3000);
			fdp.clickOnNextButton(fdp.ReleaseFormPg,"Release Form");
			threadsleep(3000);
			fdp.releaseForm(formName);
			logInfo("Form Name" +  formName  + "edited");
			return true;
		}
		catch(Exception e) {
			logError("Failed to edit form" + formName
					+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to edit forms
	 * @author choubey_a
	 * @param formName is the name of the form to edit
	 * @boolean will return true when the form will open in edit mode
	 */

	public boolean editSelectForm(String formName) {
		try {
			
			controlActions.action.moveToElement(FormRow).build().perform();
			threadsleep(2000);
			controlActions.clickOnElement(CalloutMenu);
			controlActions.WaitforelementToBeClickable(EditFormMenu);
			threadsleep(3000);
			controlActions.clickOnElement(EditFormMenu);
			Sync();
			logInfo("Form opened in edit mode");
			return true;
		}catch(Exception e) {
			logError("Failed to edit form" + formName
					+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to deleted saved forms
	 * @author choubey_a
	 * @param formName is the name of the saved form
	 * @boolean will return true when the saved form will not be deleted
	 */
	public boolean deleteSavedForm(String formName) {
		try {
			WebElement SelectSavedForm = controlActions.perform_GetElementByXPath(FSQABrowserPageLocators.SAVED_FORM_DGD,"FORM_NAME", formName);
			controlActions.action.moveToElement(SelectSavedForm).build().perform();
			threadsleep(2000);
			WebElement DeleteSavedForm = controlActions.perform_GetElementByXPath(FSQABrowserPageLocators.DELETE_BTN,"FORM_NAME", formName);
			controlActions.clickOnElement(DeleteSavedForm);
			controlActions.clickOnElement(YesBtn);
			threadsleep(3000);	
			return true;
		}
		catch(Exception e) {
			return false;
		}	
	}

	/**
	 * This method is used to select form to fill
	 * @author Choubey_a
	 * @param formname
	 * @return boolean This returns true if form is selected
	 */

	public boolean selectForm(String formname) {
		try {
			controlActions.WaitforelementToBeClickable(FormRow);
			controlActions.doubleClick(FormRow);
			Sync();
			logInfo("Selected Form");
			return true;
		}catch(Exception e) {
			logError("Failed to select form");
			return false;
		}
	}

	/**
	 * This method is used to verify that only single form should be shown to the user
	 * @author pashine_a
	 * @param String formName
	 * @return boolean This returns true if single given form is found
	 */
	public boolean verifySingleForm(String formName) {
		try {
			WebElement formSelect = controlActions.perform_GetElementByXPath(FSQABrowserPageLocators.FIRST_FORM_DATA,"FORM",formName);
			if(!controlActions.isElementDisplayedOnPage(formSelect)){
				logError("Form is not present");
				return false;
			}
			if(TotalFormsLst.size()!=1) {
				logError("Getting more than 1 form");
				return false;
			}
			logInfo("Verified the form on FSQA Browser - Forms Tab");
			return true;
		}catch(Exception e) {
			logError("Failed verify form - " + e.getMessage());
			return false;
		}
	}
	
	/**
	 * This method is used to verify that form should be shown/displayed to the user
	 * @author jadhav_akan
	 * @param String formName
	 * @return boolean This returns true if form is found
	 */
	public boolean verifyFormIsDisplayed(String formName) {
		try {
			WebElement formSelect = controlActions.perform_GetElementByXPath(FSQABrowserPageLocators.FIRST_FORM_DATA,"FORM",formName);
			if(controlActions.isElementDisplayedOnPage(formSelect)){
				logInfo("Form - "+formName+" is present");
				return true;
			}
			else {
				logError("Form - "+formName+" is not present");
				return false;
			}
		}catch(Exception e) {
			logError("Failed verify form -  "+formName+"" + e.getMessage());
			return false;
		}
	}
	
	/**
	 * This method is used to verify that form should not shown/displayed to the user
	 * @author jadhav_akan
	 * @param String formName
	 * @return boolean This returns true if form is not found
	 */
	public boolean verifyFormIsNotDisplayed(String formName) {
		try {
			WebElement formSelect = controlActions.perform_GetElementByXPath(FSQABrowserPageLocators.FIRST_FORM_DATA,"FORM",formName);
			if(controlActions.isElementDisplayedOnPage(formSelect)){
				logError("Form - "+formName+" is present");
				return false;
			}
			logInfo("Verified the form - "+formName+" is not present on FSQA Browser");
			return true;
		}catch(Exception e) {
			logError("Failed to verify form -  "+formName+" is not present" + e.getMessage());
			return false;
		}
	}
	/**
	 * This method is used to verify that form is displayed to the user in Saved Forms
	 * @author jadhav_akan
	 * @param String formName
	 * @return boolean This returns true if form is found
	 */
	public boolean verifyFormIsDisplayedInSavedForms(String formName) {
		try {
			WebElement formSelect = controlActions.perform_GetElementByXPath(FSQABrowserPageLocators.FORM_IN_SAVED_FORMS,"FORM",formName);
			if(controlActions.isElementDisplayedOnPage(formSelect)){
				logInfo("Form - "+formName+" is present in Saved Forms");
				return true;
			}
			else {
				logError("Form - "+formName+" is not present saved Forms");
				return false;
			}
		}catch(Exception e) {
			logError("Failed to verify form -  "+formName+" is present in Saved Forms" + e.getMessage());
			return false;
		}
	}
	/**
	 * This method is used to open/select form in Saved Forms
	 * @author jadhav_akan
	 * @param String formName
	 * @return boolean This returns true if form is open
	 */
	public boolean openFormInSavedForms(String formName) {
		try {
			WebElement formSelect = controlActions.perform_GetElementByXPath(FSQABrowserPageLocators.FORM_IN_SAVED_FORMS,"FORM",formName);
			if(controlActions.isElementDisplayedOnPage(formSelect)){
				controlActions.clickElement(formSelect);
				Sync();
				logInfo("Form - "+formName+" is opened from Saved Forms");
				return true;
			}
			else {
				logError("Form - "+formName+" is not open from saved Forms");
				return false;
			}
		}catch(Exception e) {
			logError("Failed to Open form -  "+formName+" from Saved Forms" + e.getMessage());
			return false;
		}
	}
	/** End - FSQA Forms */


	/** Start - FSQA Records */

	/**
	 * This method is used to click on View button for FSQA Records 
	 * @author hingorani_a
	 * @param none
	 * @return FSQARecordsPage This returns object with error variable as false
	 * if View button is clicked.
	 */
	public FSQABrowserRecordsPage clickViewRecordsBtn() {
		try {			
			controlActions.WaitforelementToBeClickable(ViewRecordsBtn);
			controlActions.clickOnElement(ViewRecordsBtn);
			logInfo("Clicked on View Records button");
			Sync();
			return new FSQABrowserRecordsPage(driver);
		}
		catch(Exception e) {
			logError("Failed to click on View Records button - "
					+ e.getMessage());
			FSQABrowserRecordsPage frp = new FSQABrowserRecordsPage(driver);
			frp.error = true;
			return frp;
		}		
	}

	/**
	 * This method is used to click on Sign button for FSQA Records 
	 * @author hingorani_a
	 * @param none
	 * @return RecordSignoffPage This returns object with error variable as false
	 * if Sign button is clicked.
	 */
	public RecordSignoffPage clickSignRecordsBtn() {
		try {			
			controlActions.WaitforelementToBeClickable(SignRecordsBtn);
			controlActions.clickOnElement(SignRecordsBtn);
			logInfo("Clicked on Sign Records button");
			Sync();
			return new RecordSignoffPage(driver);
		}
		catch(Exception e) {
			logError("Failed to click on Sign Records button - "
					+ e.getMessage());
			RecordSignoffPage rsp = new RecordSignoffPage(driver);
			rsp.error = true;
			return rsp;
		}		
	}

	/**
	 * This method is used to verify that only single record should be shown to the user
	 * @author pashine_a
	 * @param String recordName
	 * @return boolean This returns true if single given record is found
	 */
	public boolean verifySingleRecord(String recordName) {
		try {
			WebElement recordSelect = controlActions.perform_GetElementByXPath(FSQABrowserPageLocators.FIRST_RECORD_DATA,"RECORD",recordName);
			if(!controlActions.isElementDisplayedOnPage(recordSelect)){
				logError("Record is not present");
				return false;
			}
			if(TotalRecordsLst.size()!=1) {
				logError("Getting more than 1 record");
				return false;
			}
			logInfo("Verified the record on FSQA Browser - Records Tab");
			return true;
		}catch(Exception e) {
			logError("Failed verify record - " + e.getMessage());
			return false;
		}
	}

	/** This method is used to import the data for the parameters provided -  for Records
	 * @author choubey_a
	 * @param none
	 * @return boolean true when records breadcrumb is clicked
	 */	
	public boolean clickRecordsBreadCrumb() {
		try {
			controlActions.click(RecordBreadcrumb);
			Sync();
			logInfo("Click on Records Breadcrumb");
			return true;
		}catch(Exception e) {
			logError("Failed to click records breadcrumb - " + e.getMessage());
			return false;
		}
	}

	/** This method is used to get latest Sign off details after hovering on Sign Icon -  for Records
	 * @author hingorani_a
	 * @param none
	 * @return String returns value of the Signoff details
	 */	
	public String clickRecordsSignIcon() {
		String signoffDetail = null;
		try {
			controlActions.hoverElement(RecordSignIcon);
			Sync();
			if(RecordSignoffDetails.size() == 1) {
				signoffDetail = RecordSignoffDetails.get(0).getText();
			}
			logInfo("Sign off details - " + signoffDetail);
			return signoffDetail;
		}catch(Exception e) {
			logError("Failed to get Signoff details - " + e.getMessage());
			return signoffDetail;
		}
	}

	/**
	 * This method is used to close Record Signoff popup 
	 * @author hingorani_a
	 * @param none
	 * @return boolean This returns true if Record Signoff popup is closed
	 */
	public boolean closeRecordSignoffPopup() {
		try {			
			controlActions.WaitforelementToBeClickable(RecordSignoffCloseBtn);
			RecordSignoffCloseBtn.click();
			//			Sync();
			logInfo("Closed Record Signoff popup");	
			return true;
		}
		catch(Exception e) {
			logError("Failed to close Record Signoff popup - "
					+ e.getMessage());
			return false;
		}		
	}

	/** This method is used to select a record by given index 
	 * @author hingorani_a
	 * @param numberOfRecord The number of record in Grid to be selected
	 * @return boolean true when mentioned record is selected
	 */	
	public boolean selectRecordByIndex(int numberOfRecord) {
		int index = numberOfRecord - 1;
		try {

			if(!RecordGridChk.get(index).isSelected()) {
				RecordGridChk.get(index).click();
				logInfo("Selected a record at index " + numberOfRecord);
				return true;
			}

			logInfo("Could not select a record at index " + numberOfRecord);
			return false;
		}
		catch(Exception e) {
			logError("Failed to select a record at index " + numberOfRecord 
					+ e.getMessage());
			return false;
		}
	}

	/** This method is used to click on Date Range's Calendar icon
	 * @author hingorani_a
	 * @param none
	 * @return boolean true when Date Range's Calendar icon is clicked
	 */	
	public boolean clickDateRangeCalendarIcon() {
		try {
			controlActions.WaitforelementToBeClickable(DateRangeCalndrBtn);
			DateRangeCalndrBtn.click();
			logInfo("Clicked on Calendar button");
			return true;
		}
		catch(Exception e) {
			logError("Failed to click on Calendar button - " + e.getMessage());
			return false;
		}
	}

	/** This method is used to click on Date Range's Clear button
	 * @author hingorani_a
	 * @param none
	 * @return boolean true when Date Range's Clear button is clicked
	 */	
	public boolean clickDateRangeClearBtn() {
		try {
			controlActions.WaitforelementToBeClickable(DateRangeClearBtn);
			DateRangeClearBtn.click();
			Sync();
			logInfo("Clicked on Clear button");
			return true;
		}
		catch(Exception e) {
			logError("Failed to click on Clear button - " + e.getMessage());
			return false;
		}
	}

	/** This method is used to click on Date Range's Filter button
	 * @author hingorani_a
	 * @param none
	 * @return boolean true when Date Range's Filter button is clicked
	 */	
	public boolean clickDateRangeFilterBtn() {
		try {
			controlActions.WaitforelementToBeClickable(DateRangeFilterBtn);
			DateRangeFilterBtn.click();
			Sync();
			logInfo("Clicked on Filter button");
			return true;
		}
		catch(Exception e) {
			logError("Failed to click on Filter button - " + e.getMessage());
			return false;
		}
	}

	/** This method is used to click on Date Range's From textbox's Label
	 * @author hingorani_a
	 * @param date The date to be set for From textbox
	 * @return boolean true when Date Range's From textbox's Label is clicked
	 */	
	public boolean setDateRangeFromText(String date) {
		try {
			controlActions.WaitforelementToBeClickable(DateRangeFromTxt);
			DateRangeFromTxt.clear();
			DateRangeFromTxt.sendKeys(date);
			logInfo("Date Range's From textbox set to - " + date);
			return true;
		}
		catch(Exception e) {
			logError("Failed to set Date Range's From textbox - " + e.getMessage());
			return false;
		}
	}

	/** This method is used to click on Date Range's To textbox's Label
	 * @author hingorani_a
	 * @param date The date to be set for To textbox
	 * @return boolean true when Date Range's To textbox's Label is clicked
	 */	
	public boolean setDateRangeToText(String date) {
		try {
			controlActions.WaitforelementToBeClickable(DateRangeToTxt);
			DateRangeToTxt.clear();
			DateRangeToTxt.sendKeys(date);
			logInfo("Date Range's To textbox set to - " + date);
			return true;
		}
		catch(Exception e) {
			logError("Failed to set Date Range's To textbox - " + e.getMessage());
			return false;
		}
	}

	/** This method is used to click on Date Range's From label
	 * @author hingorani_a
	 * @param none
	 * @return boolean true when Date Range's From label is clicked
	 */	
	public boolean clickDateRangeFromLabel() {
		try {
			controlActions.WaitforelementToBeClickable(DateRangeFromLbl);
			DateRangeFromLbl.click();
			logInfo("Clicked on Date Range's From label");
			return true;
		}
		catch(Exception e) {
			logError("Failed to click on Date Range's From label - " + e.getMessage());
			return false;
		}
	}

	/** This method is used to click on Date Range's From label
	 * @author hingorani_a
	 * @param none
	 * @return boolean true when Date Range's From label is clicked
	 */	
	public boolean applyDateRangeFilter(String fromDate, String toDate) {
		try {
			if(!clickDateRangeCalendarIcon())
				return false;

			if(!setDateRangeFromText(fromDate))
				return false;

			if(!setDateRangeToText(toDate))
				return false;

			if(!clickDateRangeFromLabel())
				return false;

			if(!clickDateRangeFilterBtn())
				return false;

			logInfo("Applied Date Range filter");
			return true;
		}
		catch(Exception e) {
			logError("Failed to apply Date Range filter - " + e.getMessage());
			return false;
		}
	}

	/** This method is used to click on Date Range's Clear link
	 * @author hingorani_a
	 * @param none
	 * @return boolean true when Date Range's Clear link is clicked
	 */	
	public boolean clickDateRangeClearLink() {
		try {
			controlActions.WaitforelementToBeClickable(DateRangeClearLnk);
			DateRangeClearLnk.click();
			Sync();
			logInfo("Clicked on Clear link");
			return true;
		}
		catch(Exception e) {
			logError("Failed to click on Clear link - " + e.getMessage());
			return false;
		}
	}
	
	/**
	 * This method is used to verify that record should be shown/displayed to the user
	 * @author jadhav_akan
	 * @param String recordName
	 * @return boolean This returns true if record is displayed
	 */
	public boolean verifyRecordIsDisplayed(String recordName) {
		try {
			WebElement recordSelect = controlActions.perform_GetElementByXPath(FSQABrowserPageLocators.FIRST_RECORD_DATA,"RECORD",recordName);
			if(controlActions.isElementDisplayedOnPage(recordSelect)){
				logInfo("Record  "+recordName+" is present");
				return true;
			}
			logInfo("Verified the record - "+recordName+" on FSQA Browser - Records Tab");
			return true;
		}catch(Exception e) {
			logError("Failed verify record - "+recordName+" " + e.getMessage());
			return false;
		}
	} 
	/**
	 * This method is used to verify that record should not be shown/displayed to the user
	 * @author jadhav_akan
	 * @param String recordName
	 * @return boolean This returns true if record is not displayed
	 */
	public boolean verifyRecordIsNotDisplayed(String recordName) {
		try {
			WebElement recordSelect = controlActions.perform_GetElementByXPath(FSQABrowserPageLocators.FIRST_RECORD_DATA,"RECORD",recordName);
			if(controlActions.isElementDisplayedOnPage(recordSelect)){
				logError("Recoed - "+recordName+" is present");
				return false;
			}
			logInfo("Verified the record - "+recordName+" is not present on FSQA Browser");
			return true;
		}catch(Exception e) {
			logError("Failed to verify record -  "+recordName+" is not present" + e.getMessage());
			return false;
		}
	}

	/** End - FSQA Records */

	/** Start - FSQA Task */

	/**
	 * This method is used to assign form task
	 * @author choubey_a
	 * @param formName The name of form 
	 * @param location The name of location associated 
	 * @param workgroup The name of workgroup associated
	 * @param resourcename The name of resource associated
	 * @boolean will return true when the form task will not be assigned
	 */
	public boolean assignFormTask (TASKDETAILS tsd) {
		try {
			controlActions.refreshPage();
			Sync();
			controlActions.action.moveToElement(FormRow).build().perform();
			threadsleep(2000);
			controlActions.clickOnElement(CalloutMenu);
			controlActions.WaitforelementToBeClickable(AssignTaskMenu);
			threadsleep(2000);
			controlActions.clickOnElement(AssignTaskMenu);
			Sync();
			FormTaskNameInput.clear();
			controlActions.sendKeys(FormTaskNameInput, tsd.TaskName);
			Sync();
			String select1 = "li[class='k-item ng-scope'][role='option']";
			controlActions.JSSetValueFromList(driver, select1, tsd.Workgroup);
			threadsleep(3000);
			String select = "li[class='k-item ng-scope'][role='option']";
			controlActions.JSSetValueFromList(driver, select, tsd.Location);
			threadsleep(3000);
			String select2 = "li[class='k-item ng-scope'][role='option']";
			controlActions.JSSetValueFromList(driver, select2, tsd.Resource, tsd.IndexNo);
			threadsleep(3000);
			tsd.TaskType = tsd.TaskType.toUpperCase();
			switch(tsd.TaskType) {
			case TASK_TYPE.PAST_DUE:
				datetime.setDateTime(DueByInput, "Day", TASK_VALUE.PAST_DUE, TASK_VALUE.PAST_DUE_HOUR, 
						TASK_VALUE.PAST_DUE_MIN, false, false);
				break;
			case TASK_TYPE.DUE_TODAY:
				datetime.setDateTime(DueByInput, "Day", TASK_VALUE.DUE_TODAY, TASK_VALUE.DUE_TODAY_HOUR, 
						TASK_VALUE.DUE_TODAY_MIN, false, false);
				break;
			case TASK_TYPE.DUE_LATER:
				datetime.setDateTime(DueByInput, "Day", TASK_VALUE.DUE_LATER, TASK_VALUE.DUE_LATER_HOUR, 
						TASK_VALUE.DUE_LATER_MIN, false, false);
				break;
			default:
				logInfo("No due date selected");
			}
			if(tsd.Notes!=null) {
				controlActions.sendKeys(FormTaskNotesInput,tsd.Notes);
			}
			controlActions.click(AssignBtn);
			Sync();
			logInfo("Form Task Assigned- " + tsd.TaskName);			
			return true;
		}
		catch(Exception e) {
			logError("Failed to assign form task - " + tsd.TaskName + " - " 
					+ e.getMessage());
			return false;
		}	
	}


	/**
	 * This method is used to recall task
	 * @author choubey_a
	 * @param taskname The name to be provided for Task
	 * @boolean will return true when the tasks will be recalled
	 */
	public boolean recallTask (String taskname) {
		try {
			WebElement selectTask = controlActions.perform_GetElementByXPath(FSQABrowserPageLocators.TASK_ROW,"TASKNAME", taskname);
			controlActions.action.moveToElement(selectTask).build().perform();
			threadsleep(2000);
			controlActions.clickOnElement(CalloutMenu);
			threadsleep(2000);
			controlActions.WaitforelementToBeClickable(RecallMnu);			
			controlActions.clickOnElement(RecallMnu);
			threadsleep(3000);
			controlActions.clickOnElement(RecallYesBtn);
			Sync();
			logInfo("Task recalled");			
			return true;
		}
		catch(Exception e) {
			logError("Failed to recall task" 
					+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to verify that only single task should be shown to the user
	 * @author pashine_a
	 * @param String taskName
	 * @return boolean This returns true if single given task is found
	 */
	public boolean verifySingleTask(String taskName) {
		try {
			WebElement taskSelect = controlActions.perform_GetElementByXPath(FSQABrowserPageLocators.FIRST_TASK_DATA,"TASK",taskName);
			if(!controlActions.isElementDisplayedOnPage(taskSelect)){
				logError("Task is not present");
				return false;
			}
			if(TotalTasksLst.size()!=1) {
				logError("Getting more than 1 task");
				return false;
			}
			logInfo("Verified the task on FSQA Browser - Tasks Tab");
			return true;
		}catch(Exception e) {
			logError("Failed verify task - " + e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to change Due by of an assigned form task
	 * @author hingorani_a
	 * @param taskName The name of the task to be updated
	 * @param date The date of the task to be added for update/change
	 * @return boolean - This returns true when the form task due by is changed
	 */
	public boolean changeDueByOfTask(String taskName, String date, String updatedTaskName) {
		try {
			WebElement selectTask = controlActions.perform_GetElementByXPath(FSQABrowserPageLocators.TASK_ROW,
					"TASKNAME", taskName);
			controlActions.action.moveToElement(selectTask).build().perform();

			controlActions.clickOnElement(CalloutMenu);
			controlActions.WaitforelementToBeClickable(ChngeDueByTaskMnu);			
			ChngeDueByTaskMnu.click();
			logInfo("Clicked on Change Due Date menu option");

			if(updatedTaskName!=null) {
				UpdateTaskNameTxt.clear();
				UpdateTaskNameTxt.sendKeys(taskName);
				logInfo("Set Task Name as - " + taskName);
			}

			UpdateDuebyInput.clear();
			UpdateDuebyInput.sendKeys(date);
			logInfo("Set Task Due By as - " + date);

			SaveTaskBtn.click();
			Sync();
			logInfo("Assigned task's due date changed to - " + date);			
			return true;
		}
		catch(Exception e) {
			logError("Failed to change assigned task's due date to - " + date + " - " 
					+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to reassign form task
	 * @author hingorani_a
	 * @param tsd Use Class TASKDETAILS to set details like task name, notes, workgroup name, etc
	 * @return boolean - This returns true when the form task is reassigned
	 */
	public boolean reassignFormTask (TASKDETAILS tsd) {

		try {

			WebElement selectTask = controlActions.perform_GetElementByXPath(FSQABrowserPageLocators.TASK_ROW,
					"TASKNAME", tsd.TaskName);
			controlActions.action.moveToElement(selectTask).build().perform();

			controlActions.clickOnElement(CalloutMenu);
			controlActions.WaitforelementToBeClickable(ReassignTaskMnu);			
			ReassignTaskMnu.click();
			logInfo("Clicked on Reassign Task menu option");
			Sync();

			if(tsd.Workgroup!=null) {
				String selector = "ul[id='scs-workgroup-dropdown_listbox'] > li";
				controlActions.JSSetValueFromList(driver, selector, tsd.Workgroup);
				threadsleep(2000);
				logInfo("Set Task Workgroup as - " + tsd.Workgroup);
			}

			if(tsd.UpdatedTaskName!=null) {
				UpdateTaskNameTxt.clear();
				UpdateTaskNameTxt.sendKeys(tsd.UpdatedTaskName);
				logInfo("Set Task Name By as - " + tsd.UpdatedTaskName);
			}

			if(tsd.DueByDate!=null) {
				UpdateDuebyInput.clear();
				UpdateDuebyInput.sendKeys(tsd.DueByDate);
				logInfo("Set Task Due By as - " + tsd.DueByDate);
			}

			if(tsd.Notes!=null) {
				FormTaskNotesInput.sendKeys(tsd.Notes);
				logInfo("Set Task Note as - " + tsd.Notes);
			}

			controlActions.clickOnElement(ReassignTaskBtn);
			Sync();
			logInfo("Form Task reassigned- " + tsd.TaskName);			
			return true;
		}
		catch(Exception e) {
			logError("Failed to reassign form task - " + tsd.TaskName + " - " 
					+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to set the Search the Location from Click form> Location Tab 
	 * @author dahale_p
	 * @param none 
	 * @date 07 April 2021
	 * @return boolean This returns true if location is get searched is done
	 * */
	public boolean clickDirectObservationVerification(String directobservation,String adminverificationPinTxt) {

		try {	
			Sync();
			controlActions.click(VerifyFormBtn);
			controlActions.click(DirectObsUserNameTxt);
			controlActions.sendKeys(DirectObsUserNameTxt,directobservation);
			controlActions.actionEnter();
			controlActions.click(VerificationPinTxt);
			controlActions.sendKeys(VerificationPinTxt,adminverificationPinTxt);
			controlActions.actionEnter();

			controlActions.click(IsCompliantToggleYes);
			Sync();
			controlActions.WaitforelementToBeClickable(DirObsVerifySaveBtn);
			controlActions.clickOnElement(DirObsVerifySaveBtn);
			Sync();
			logInfo("Clicked on Save button");
			controlActions.click(SelectedLocationLbl);
			Sync();
			return true;

		}
		catch(Exception e) {
			logError("Failed to click on Save button"
					+ e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to  sort column by providing column name
	 * @author choubey_a
	 * @param columnName
	 * @return boolean This returns true if column is sorted
	 */

	public String sortColumn(String columnName) {
		List <WebElement> ColumnValue = null; 
		WebElement headerName = null;
		int columnIndex = 0; 
		String columnHeaderPath = null;
		int listLimit = 8;
		try {
			columnIndex = 0;			
			for(WebElement column : GridHeaders) {
				if(column.getText().equalsIgnoreCase(columnName)) {
					columnIndex++;
					headerName = column;
					break;
				}
				else {
					columnIndex++;
				}
			}
			logInfo("For column : " + columnName + " the index is : " + columnIndex);

			if(columnIndex == 0) {
				logError("Failed to get correct column index : " + columnIndex + " for column " + columnName);
				return null;
			}
			else {
				columnHeaderPath = FSQABrowserPageLocators.COLUMN_LST;
				controlActions.clickElement(headerName);
				Sync();
				ColumnValue = controlActions.perform_GetListOfElementsByXPath(columnHeaderPath, 
						"COLUMN_INDEX", Integer.toString(columnIndex));
				if(ColumnValue.size()<8)
					listLimit = ColumnValue.size();

				if(!sortSwitch(ColumnValue,listLimit,true)) {
					logError("Sorting is ascending is not completed");
					return Sorting.ASC;
				}
				controlActions.clickElement(headerName);
				Sync();
				ColumnValue = controlActions.perform_GetListOfElementsByXPath(columnHeaderPath, 
						"COLUMN_INDEX", Integer.toString(columnIndex));
				if(ColumnValue.size()<8)
					listLimit = ColumnValue.size();

				if(!sortSwitch(ColumnValue,listLimit,false)) {						
					logError("Sorting is descending is not completed");
					return Sorting.DSC;
				}
				logInfo("Verified sorting for column " + columnName);
				return null;
			}
		}
		catch(Exception e) {
			logError("Failed to sort data" + columnName 
					+ e.getMessage());
			return null;
		}	
	}

	/**
	 * This method is used to  sort column in desc and asc
	 * @author choubey_a
	 * @param columnName
	 * @return boolean This returns true if column is sorted
	 */

	public boolean sortSwitch(List <WebElement> ColumnValue,int listLimit,boolean sort) {

		int compare = 0,ascCount = 0,dscCount = 0,sortCount =0;

		try {
			for(int l=0;l<listLimit;l++) {
				compare = ColumnValue.get(l).getText().compareToIgnoreCase(ColumnValue.get(l+1).getText());
				if(compare==0) {
					sortCount++;
					logInfo("Equal value for -) " + ColumnValue.get(l).getText());
				}

				else if(compare>0) {
					dscCount++;
					logInfo("Descending value for - ) " + ColumnValue.get(l).getText());
				}

				else if(compare<0) {
					ascCount++;
					logInfo("Ascending value for - " + ColumnValue.get(l).getText());
				}
			}

			if(sort) {
				return (sortCount+ascCount==listLimit);
			}else {
				return (sortCount+dscCount==listLimit);
			}
		}
		catch(Exception e) {
			logError("Failed to switch sort data"
					+ e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to  verify due by date of task
	 * @author choubey_a
	 * @param date
	 * @return boolean This returns true if due by date is verified for a task
	 */

	public boolean verifyDueDate(String date) {
		try {
			WebElement selectTask = controlActions.perform_GetElementByXPath(FSQABrowserPageLocators.DUEBYDATE_GRD,
					"DATE", date);
			if(!controlActions.isElementDisplayed(selectTask)) {
				logError("Due By Date not verified");
				return false;
			}
			logInfo("Due By Date verified");
			return true;
		}catch(Exception e) {
			logError("Failed to verify due by date"
					+ e.getMessage());
			return false;
		}

	}

	/**
	 * This method is used to verify saved form availability
	 * @author pashine_a
	 * @param formName. It is the name of the saved form
	 * @@return boolean. It will return true when the saved form is available in 'FSQA Browser - Forms Tab'
	 */
	public boolean verifySavedFormAvailability(String formName) {
		try {
			String savedFormPath = controlActions.perform_GetDynamicXPath(FSQABrowserPageLocators.SAVED_FORM_DGD,"FORM_NAME", formName);
			if(controlActions.isElementAvailable(savedFormPath)) {
				logInfo("Found saved form - "+formName);
				return true;
			}else {
				logInfo("Not found saved form - "+formName);
				return false;
			}
		}
		catch(Exception e) {
			logError("Unable to verify saved form('"+formName+"') availability - "+e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to verify Analytical chart is present below Task Summary Title
	 * @author pednekar_pr
	 * @return boolean. It will return true when location of Analytical chart is below Task Summary Title
	 */
	public boolean verifyLocationOfAnalyticalChart()
	{
		try {
				if (!controlActions.isElementDisplay(TaskSummaryTitle))
					return false;
				if(!((TaskSummaryTitle.getText()).equalsIgnoreCase("Task Summary")))
					return false;
					
					
				logInfo("Task Summary title is displayed");
			return true;

		}
		catch(Exception e) {
			logError("Unable to verify saved form availability - "+e.getMessage());
			return false;
		}	
	}

	
	/**
	 * This method is used to verify Analytical chart contains two donut shapes
	 * @author pednekar_pr
	 * @return boolean. It will return true when Analytical chart contains two donut shapes
	 */
	public boolean verifyStructureOfAnalyticalChart() {
		try {
			
			if (!controlActions.isElementDisplay(OuterDonut))
				return false;
			
			if (!controlActions.isElementDisplay(InnerDonut))
				return false;
			
			logInfo("Two donut's are displayed ");
			return true;

		}
		catch(Exception e) {
			logError("Failed to verify whether two donut's are displayed or not"+e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to verify "Task Status" text is present above Analytical chart
	 * @author pednekar_pr
	 * @return boolean. It will return true when Analytical chart contains two donut shapes
	 */
	public boolean verifyTitleOfAnalyticalChart() {
		try {
			if (!controlActions.isElementDisplay(TaskStatus))
				return false;
			if(!(TaskStatus.getText()=="Task Status"))
				return false;
				
			logInfo("Task Status title is displayed");
		return true;
		}
		catch(Exception e) {
			logError("Unable to verify saved form availability - "+e.getMessage());
			return false;
		}	
	}	


	/**
	 * This method is used to verify on hovering over Outer donut shows tooltip and percentage value
	 * @author pednekar_pr
	 * @return boolean. It will return true when tooltip is shown and percentage value shown is correct
	 */
	public boolean verifyOuterDonut() {

		try {
			int flag=0;
			if(!controlActions.isElementDisplay(OuterDonut))
			{
				controlActions.perform_ClickWithJavaScriptExecutor(OuterDonut);
			
			if(!controlActions.isElementDisplay(ToolTip))
				return false;
			
			String color= ToolTip.getCssValue("background-color");
			
			
			if (color=="rgb(103, 145, 190)")
					{
				flag=1;
				logInfo("No Due Date is displayed ");
					}
			
			else if(color=="rgb(240, 111, 111)")
			{
				flag=2;
				logInfo("Past Due is displayed ");
			}
				
			else if(color=="rgb(146, 211, 110)") {
				flag=3;
				logInfo("Due Later is displayed ");
			}
			
			else if(color=="rgb(240, 111, 111)")
			{
				flag=4;
				logInfo("Due Today is displayed ");
			}
			
			String percentTxt=ToolTip.getText();
			
			//break and get %value
			
			String txt =NoDueDate.getText()	;
			 String[] arrOfStr = txt.split("-");
			 int NDD=Integer.parseInt(arrOfStr[1]);
			
			txt=PastDue.getText();
			 String[] arrOfStr1 = txt.split("-");
			 int PD=Integer.parseInt(arrOfStr1[1]);
			
			txt=DueLater.getText();
			 String[] arrOfStr2 = txt.split("-");
			 int DL=Integer.parseInt(arrOfStr2[1]);
			
			txt=DueToday.getText();
			 String[] arrOfStr3 = txt.split("-");
			 int DT=Integer.parseInt(arrOfStr3[1]);
			
			 float cal=calculatePercentage(NDD,PD,DL,DT, flag);
			 String s=String.valueOf(cal);
			 
			//calculate %value
			boolean isPresent=percentTxt.contains(s);
			logInfo("Percentage displayed is correct");
			
			if (isPresent==true)
			return true;
			
			else
				return false;

		}
			else
				return false;
		}
		catch(Exception e) {
			logError("Unable to verify saved form  availability - "+e.getMessage());
			return false;
		}	
	}	

	/**
	 * This method is used to verify on hovering over Inner donut shows tooltip and percentage value
	 * @author pednekar_pr
	 * @return boolean. It will return true when tooltip is shown and percentage value shown is correct
	 */
	public boolean verifyInnerDonut() {
		try {
			int flag=0;
			controlActions.hoverElement(InnerDonut);
			if(!controlActions.isElementDisplay(ToolTip))
				return false;
			
			String color= ToolTip.getCssValue("background-color");
			
			if (color=="rgb(153, 153, 153)")
			{
				flag=1;
					logInfo("Started is displayed ");
			}
			
			else if(color=="rgb(192, 192, 192)")
				{
				flag=2;
				logInfo("Not Started is displayed ");
				}
			
			String percentTxt=ToolTip.getText();
				
				//break and get %value
				String txt =NonStarted.getText()	;
				 String[] arrOfStr = txt.split("-");
				 int NS=Integer.parseInt(arrOfStr[1]);
				
				txt=Started.getText();
				 String[] arrOfStr1 = txt.split("-");
				 int S=Integer.parseInt(arrOfStr1[1]);
				
				 float cal=calculatePercentage(0,0,NS,S, flag);
				 String s=String.valueOf(cal);
				 
				//calculate %value
				boolean isPresent=percentTxt.contains(s);
				logInfo("Percentage displayed is correct");
				
				if (isPresent==true)
				return true;
				
				else
					return false;

		}
		catch(Exception e) {
			logError("Unable to verify saved form availability - "+e.getMessage());
			return false;
		}	
	}	

	/**
	 * This method is used to verify values after changing resources
	 * @author pednekar_pr
	 * @return boolean. It will return true when tooltip values changes after changing resource
	 */
	public boolean viewChartValues() {
		try {

			Sync();
			verifyInnerDonut();
			Sync();
			verifyOuterDonut();
			return true;

		}
		catch(Exception e) {
			logError("Unable to verify saved form availability - "+e.getMessage());
			return false;
		}	
	}
	

	/**
	 * This method is used to calculate percentage value using values num1, num2, num3, num4 and flag
	 * @author pednekar_pr
	 * @param num1 It stores integer value of num1
	 * @param num2 It stores integer value of num2
	 * @param num3 It stores integer value of num3
	 * @param num4 It stores integer value of num4
	 * @param flag It stores flag value
	 * if flag==1 than calculate percentage wrt num1
	 * if flag==2 than calculate percentage wrt num2
	 * if flag==3 than calculate percentage wrt num3
	 * if flag==4 than calculate percentage wrt num4
	 * @return float. It will return percentage value
	 */
	public float calculatePercentage( int num1, int num2, int num3, int num4, int flag) {

			float val = 0;
			int total=(num1+num2+num3+num4);
			switch (flag)
			{
			case 1:
				{
					val=(num1*100)/total;
				break;
				}	
			case 2:
			{
				val=(num2*100)/total;
			break;
			}
				
			case 3:
			{
				val=(num3*100)/total;
			break;
			}
				
			case 4:
			{
				val=(num4*100)/total;
			break;
			}
			
			}
			
			return val;
	}

	/**
	 * This method is used to hover Outstanding task and observe tooltip
	 * @author pednekar_pr
	 * @param taskname It stores string value of taskname
	 * @return float. It will returns false if tooltip isn't found on hovering action
	 */
	public boolean hoverOutStandingTask( String taskname) {
		WebElement TaskToolTipText = null;
		WebElement TaskToolTip = null;
		try {
			TaskToolTip=controlActions.perform_GetElementByXPath(FSQABrowserPageLocators.TASK_TOOL_TIP, "TASKNAME", taskname);
			if(!controlActions.isElementDisplay(TaskToolTip))
			{
				logInfo("Tooltip not found");
				return false;
			}
			controlActions.hoverElement(TaskToolTip);
			TaskToolTipText = driver.findElement(By.xpath("//div[@role='tooltip']/div[@class='k-tooltip-content']"));
			String toolTipText=TaskToolTipText.getText();
			logInfo("Tooltip found " +toolTipText);
			if(!verifyToolTipText(toolTipText))
				return false;
			
			return true;

		}
		catch(Exception e) {
			logError("Unable to verify saved form availability - "+e.getMessage());
			return false;
		}	
}

	/**
	 * This method is used to view the change of mouse cursor type to pointer on hovering circle indicator area
	 * @author pednekar_pr
	 * @param taskname It stores string value of Task
	 * @return float. It will return true when cursor type changes to pointer 
	 */
	
	public boolean changeCursor(String taskname ) {
		try {

			WebElement TaskToolTip=controlActions.perform_GetElementByXPath(FSQABrowserPageLocators.TASK_TOOL_TIP, "TASKNAME", taskname);
		//	WebElement TaskRowDate=controlActions.perform_GetElementByXPath(FSQABrowserPageLocators.TASK_ROW_DATE, "TASKNAME", taskname);
			
			//logInfo("Cursor before hovering on: " + TaskRowDate.getCssValue("cursor"));

			logInfo("Hovering on the element...");

			//Hover the mouse over that element
			Actions builder = new Actions(driver);
			builder.moveToElement(TaskToolTip);
			builder.build().perform();

			//Check that the TaskToolTip does not change to pointer
			String cursorTypeAfter = TaskToolTip.getCssValue("cursor");
			logInfo("Cursor after hovering on: " + cursorTypeAfter);

			//Verify that the cursor type has not changed to 'pointer'
			if(cursorTypeAfter.equals("pointer"))
				return true;
			else
				return false;

		}
		catch(Exception e) {
			logError("Failed to hover outside CircleIndicator of outstanding Task  - "+e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to hover Outstanding task and verify text of tooltip 
	 * @author pednekar_pr
	 * @param toolTipText It stores string value of toolTipText obtained after hovering on outstanding task
	 * @return float. It will returns true when tooltip text is verified
	 */
	
	public boolean verifyToolTipText(String toolTipText ) {
		try {
			//Verification if tooltip text is matching expected value
			
			//due later versions
			if(toolTipText.matches("Due Later in (.*) hours (.*) minutes")){
				logInfo("Matches pattern of message : Due Later ");
				return true;
			}
			else if(toolTipText.matches("Due Later in (.*) day (.*) hours")){
				logInfo("Matches pattern of message : Due Later ");
				return true;
			}
			else if(toolTipText.matches("Due Later in (.*) day (.*) hours (.*) minutes")){
				logInfo("Matches pattern of message : Due Later ");
				return true;
			}
			else if(toolTipText.matches("Due Later in (.*) day (.*) hours (.*) minutes (.*) seconds")){
				logInfo("Matches pattern of message : Due Later ");
				return true;
			}
			// past due versions 
			else if (toolTipText.matches("Past Due by (.*) hours (.*) minutes")){
				logInfo("Matches pattern of message : Past Due by ");
				return true;
				}
			else if(toolTipText.matches("Past Due by (.*) day (.*) hours")){
				logInfo("Matches pattern of message : Past Due by");
				return true;
			}
			else if(toolTipText.matches("Past Due by (.*) day (.*) hours (.*) minutes")){
				logInfo("Matches pattern of message : Past Due by");
				return true;
			}
			else if(toolTipText.matches("Past Due by (.*) day (.*) hours (.*) minutes (.*) seconds")){
				logInfo("Matches pattern of message : Past Due by");
				return true;
			}
			else{
				logInfo("Matches pattern of message");
				return false;
			}
		}
		catch(Exception e) {
			logError("Failed to verify ToolTip format for given task "+e.getMessage());
			return false;
		}	
}
	public boolean verifyTaskIsNotVisible(String columnName,String settingName, String taskname) {
		try {

				if(!clickColumnDropDown(columnName)) {
					return false;
				}

				if(!applySettingsForColumn(settingName, taskname)) {
					return false;
				}
				WebElement Task = controlActions.perform_GetElementByXPath(FSQABrowserPageLocators.TASK_ROW,"TASKNAME", taskname);
				if(controlActions.isElementDisplay(Task)) {
					logError("Task - "+taskname+" is visible");
					return false;
				}
			logInfo("Task - "+taskname+" is Not Visible");
			return true;
		}
		catch(Exception e) {
			logError("Failed to Verify Task is not Visible "+taskname+ " "
					+ e.getMessage());
			return false;
		}	
	}

	
	/** End - FSQA Task */
	
	/** Start - FSQA Details */
	
	/**
	 * This method is used to click on location button and check selected location is displayed
	 * @author jadhav_akan
	 * @param locationInstance : Name of Instance that needs to be selected
	 * @return boolean This returns true if resources are associated with Location
	 */

	public boolean selectLocationAndCheckLocationIsDisplayed(String locationInstance) {
		try {
			System.out.println(locationInstance + "Location to search");
			controlActions.clickElement(LocationsTab);
			List<WebElement> locList1 = LocationsLists;
			for(WebElement list : locList1) {
			String text	= list.getText();
			System.out.println(text);
				if(text.contains(locationInstance)) {
					logInfo("Succesfully resources are revealed to locations");
					return true;
				}
			}
			
			logInfo("Failed to Reveal resources to locations ");
			return false;
		}
		catch(Exception e) {
			logError("Failed to Reveal resources to locations "+ e.getMessage());
			return false;
		}
	}
	/**
	 * This method is used to click on location button and check selected location is displayed
	 * @author jadhav_akan
	 * @param locationInstance : Name of Instance that needs to be selected
	 * @return boolean This returns true if resources are associated with Location
	 */

	public boolean selectLocationAndCheckLocationIsNotDisplayed(String locationInstance) {
		try {
			System.out.println(locationInstance + "Location to search");
			controlActions.clickElement(LocationsTab);
			List<WebElement> locList1 = LocationsLists;
			for(WebElement list : locList1) {
			String text	= list.getText();
			System.out.println(text);
				if(!text.contains(locationInstance)) {
					logInfo("Verified resource is not associated to location");
					return true;
				}
			}
			
			logInfo("Resource is associated to location ");
			return false;
		}
		catch(Exception e) {
			logError("Resource is associated to location "+ e.getMessage());
			return false;
		}
	}
	
	/** End - FSQA Details */
	
	/** Start - FSQA Documents */
	
	/**
	 * This method is used to verify that Document should be shown/displayed to the user
	 * @author jadhav_akan
	 * @param String documentName
	 * @return boolean This returns true if Document is found
	 */
	public boolean verifyDocumentIsDisplayed(String documentName) {
		try {
			WebElement docSelect = controlActions.perform_GetElementByXPath(FSQABrowserPageLocators.FIRST_DOC_DATA,"DOCUMENT",documentName);
			if(controlActions.isElementDisplayedOnPage(docSelect)){
				logInfo("Document - "+documentName+" is present");
				return true;
			}
			else {
				logError("Document - "+documentName+" is not present");
				return false;
			}
		}catch(Exception e) {
			logError("Failed verify Document -  "+documentName+"" + e.getMessage());
			return false;
		}
	}
	/**
	 * This method is used to verify that Document should not be shown/displayed to the user
	 * @author jadhav_akan
	 * @param String documentName
	 * @return boolean This returns true if Document is not found
	 */
	public boolean verifyDocumentIsNotDisplayed(String documentName) {
		try {
			WebElement docSelect = controlActions.perform_GetElementByXPath(FSQABrowserPageLocators.FIRST_DOC_DATA,"DOCUMENT",documentName);
			if(controlActions.isElementDisplayedOnPage(docSelect)){
				logError("Document - "+documentName+" is present");
				return false;
			}
			
			logInfo("Document - "+documentName+" is not present");
			return true;
		}catch(Exception e) {
			logError("Failed to verify Document -  "+documentName+" is not present" + e.getMessage());
			return false;
		}
	}
	/** End - FSQA Documents */

	public static class FSQATAB{
		public static final String DETAILS = "Details";
		public static final String DOCUMENTS = "Documents";
		public static final String FORMS = "Forms";
		public static final String TASKS = "Tasks";
		public static final String RECORDS = "Records";	
	}

	public static class COLUMNHEADER{
		public static final String RESOURCE = "Resource";
		public static final String FORMNAME = "Form Name";
		public static final String COMPLETEDBY = "Completed By";
		public static final String COMPLETEDON = "Completed On";
		public static final String LOCATION = "Location";	
		public static final String SIGNOFF = "SignOff";	
		public static final String FORMTYPE = "Form Type";	
		public static final String MODIFIEDBY = "Modified By";	
		public static final String MODIFIEDON = "Modified On";	
		public static final String VERSION = "Version";	
		public static final String TASKNAME = "Task Name";
		public static final String DOCUMENTNAME = "Document Name";
		public static final String DOCUMENTTYPE = "Document Type";
		public static final String ASSIGNEDTO = "Assigned To";
		public static final String DUEBY = "Due By";

	}

	public static class COLUMNSETTING{
		public static final String SORTASCENDING = "Sort Ascending";
		public static final String SORTDESCENDING = "Sort Descending";
		public static final String COLUMNS = "Columns";
		public static final String FILTER = "Filter";
	}

	public static class FSQARESOURCE{
		public static final String CUSTOMERS = "Customers";
		public static final String EQUIPMENT = "Equipment";
		public static final String ITEMS = "Items";
		public static final String SUPPLIERS = "Suppliers";
	}

	public static class TASKDETAILS{
		public String Workgroup;
		public String Resource;
		public String TaskName;
		public String TaskType = "NODUEDATE";
		public String Location;
		public String Notes = null;
		public String DueByDate;
		public String UpdatedTaskName;
		public int IndexNo = 0;
	}

	public static class TASK_TYPE{
		public final static String NO_DUE_DATE = "NODUEDATE";
		public final static String PAST_DUE = "PASTDUE";
		public final static String DUE_TODAY = "DUETODAY";
		public final static String DUE_LATER = "DUELATER";
	}

	public static class TASK_VALUE{
		public final static int PAST_DUE = -3;
		public final static int DUE_TODAY = 0;
		public final static int DUE_LATER = 2;

		public final static int PAST_DUE_HOUR = 0;
		public final static int DUE_TODAY_HOUR = 1;
		public final static int DUE_LATER_HOUR = 1;

		public final static int PAST_DUE_MIN = 0;
		public final static int DUE_TODAY_MIN = 15;
		public final static int DUE_LATER_MIN = 1;
	}

	public static class FORM_TYPES{
		public static final String CHECK = "Check";
		public static final String AUDIT = "Audit";
		public static final String QUESTIONNAIRE = "Questionnaire";
	}
}