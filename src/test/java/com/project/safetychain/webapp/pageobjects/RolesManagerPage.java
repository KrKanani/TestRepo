package com.project.safetychain.webapp.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.COLUMNSETTING;
import com.project.utilities.ControlActions;

public class RolesManagerPage extends CommonPage{
	
	WebDriver driver;
	WebDriverWait wait;
	ControlActions controlActions;
	
	public RolesManagerPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 40);
		controlActions = new ControlActions(driver);	
	}	
	
	/**
	* Page Objects
	*/
	
	@FindBy(className = RolesManagerPageLocators.SEARCH_BTN)
	public WebElement SearchBtn;
	
	@FindBy(xpath = RolesManagerPageLocators.ADD_ROLES_LNK)
	public WebElement AddRolesLnk;
	
	@FindBy(xpath = RolesManagerPageLocators.ADD_ROLES_TXT)
	public WebElement AddRolesTxt;
	
	@FindBy(xpath = RolesManagerPageLocators.SAVE_BTN)
	public WebElement SaveBtn;
	
	@FindBy(xpath = RolesManagerPageLocators.VIEW_ROLES_LST)
	public WebElement ViewRolesLst;
	
	@FindBy(xpath = RolesManagerPageLocators.SEARCH_TXT)
	public WebElement SearchTxt;
	
	@FindBy(xpath = RolesManagerPageLocators.GRID_COLUMNS)
	public List<WebElement> GridColumns;

	@FindBy(xpath = RolesManagerPageLocators.FILTER_TXT)
	public List<WebElement> FilterTxt;

	@FindBy(xpath = RolesManagerPageLocators.FILTER_BTN)
	public List<WebElement> FilterBtn;	
	
	/**
	* Functions
	*/
	
	/**
	* This method is used to set Role name
	* @author hingorani_a
	* @param roleName The Role name
	* @return boolean This returns true if Role name is set successfully.
	*/
	public boolean setRoleName(String roleName) {
		try {
			controlActions.WaitforelementToBeClickable(AddRolesLnk);
			controlActions.clickOnElement(AddRolesLnk);	
			controlActions.sendKeys(AddRolesTxt,roleName);
			Sync();
			logInfo("Role named entered: " + roleName);
			return true;
		}
		catch(Exception e) {
			logError("Failed to enter Role name - " + roleName + " - "
					+ e.getMessage());
			return false;
		}	
	}
	
	/**
	* This method is used to save Role by clicking on Save button
	* @author hingorani_a
	* @param none 
	* @return boolean This returns true if save button is clicked successfully
	*/
	public boolean clickSaveBtn() {
		try {
			controlActions.WaitforelementToBeClickable(SaveBtn);
			SaveBtn.click();
			Sync(); 
			logInfo("Clicked on Save button");
			return true;
		}
		catch(Exception e) {
			logError("Failed to click on Save button - "
					+ e.getMessage());
			return false;
		}	
	}
	
	/**
	* This method is used to create Role.
	* @author hingorani_a
	* @param roleName The Role name
	* @return boolean This returns true if Role is created successfully.
	*/
	public boolean createRole(String roleName) {
		try {
			if(!setRoleName(roleName)) {
				return false;
			}
			
			if(!clickSaveBtn()) {
				return false;
			}
			
			Sync();
			logInfo("Role - " + roleName + " is created");
			return true;
		}
		catch(Exception e) {
			logError("Failed to create Role - " + roleName + " - "
					+ e.getMessage());
			return false;
		}	
	}
	
	/**
	 * This method is used to select a role
	 * @author hingorani_a
	 * @param roleName Name of the role
	 * @return boolean This returns true if role is selected successfully
	 */
	public boolean selectRole(String roleName) {
		WebElement ViewRolesLst = null;
		try {

			ViewRolesLst = controlActions.perform_GetElementByXPath(RolesManagerPageLocators.VIEW_ROLES_LST, 
					"ROLESNAME", roleName);
			ViewRolesLst.click();	
			Sync();
			logInfo("Selected role: " + roleName);				
			return true;
		}
		catch(Exception e) {
			logError("Failed to select role: " + roleName + " - "
					+ e.getMessage());
			return false;
		}	
	}
	
	/**
	 * This method is used to search for a role
	 * @author hingorani_a
	 * @param searchTxt Text to be searched
	 * @return boolean This returns true if search is done successfully
	 */
	public boolean searchTextForRole(String searchTxt) {

		try {
			controlActions.WaitforelementToBeClickable(SearchTxt);
			SearchTxt.clear();
			SearchTxt.sendKeys(searchTxt);
			logInfo("Text for search set as - " + searchTxt);				

			SearchBtn.click();
			Sync();
			logInfo("Searched applied for role");				
			return true;
		}
		catch(Exception e) {
			logError("Failed to apply search with text " + searchTxt + " - "
					+ e.getMessage());
			return false;
		}	
	}
	
	/**
	 * This method is used to find Column Index for the column present in Roles Grid
	 * @author hingorani_a
	 * @param columnName Column name whose Index you need. Use Class COLUMN_HEADER to pass column name.
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
	 * This method is used to click on Settings dropdown link for a column
	 * @author hingorani_a
	 * @param columnName Use Class COLUMN_HEADER to set value for column name
	 * @return boolean This returns true once we click on Settings dropdown
	 */
	public boolean clickColumnDropDown(String columnName) {
		WebElement ColumnSettingsLnk = null;
		try {
			ColumnSettingsLnk = controlActions.perform_GetElementByXPath(RolesManagerPageLocators.COLUMN_SETTINGS_LNK, 
					"COLUMNNAME", columnName);
			Sync();
			controlActions.WaitforelementToBeClickable(ColumnSettingsLnk);
			controlActions.clickOnElement(ColumnSettingsLnk);
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
	 * @param settingName Use Class COLUMN_SETTING to set value for column name
	 * @param settingValue If you want to use Filter Setting, provide the text
	 * to be used for filter else for other settings keep it as null.
	 * @return boolean This returns true after applying setting
	 */
	public boolean applySettingsForColumn(String settingName, String settingValue) {
		List<WebElement> PopupOptionMnu = null;
		try {
			Sync();
			PopupOptionMnu = controlActions.perform_GetListOfElementsByXPath(RolesManagerPageLocators.POPUP_OPTION_MNU, 
					"POPUPOPTION", settingName);

			switch(settingName) {
			case COLUMNSETTING.SORTASCENDING:
			case COLUMNSETTING.SORTDESCENDING:
				for(WebElement option : PopupOptionMnu) {
					if(controlActions.isElementDisplay(option)) {
						controlActions.WaitforelementToBeClickable(option);
						controlActions.clickOnElement(option);
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
					}
				}
				for(WebElement filterTxt : FilterTxt) {
					if(controlActions.isElementDisplay(filterTxt)) {
						controlActions.WaitforelementToBeClickable(filterTxt);
						filterTxt.clear();
						controlActions.sendKeys(filterTxt, settingValue);
					}
				}
				for(WebElement filterBtn : FilterBtn) {
					if(controlActions.isElementDisplay(filterBtn)) {
						controlActions.WaitforelementToBeClickable(filterBtn);
						controlActions.clickOnElement(filterBtn);
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
	 * @param columnName Use Class COLUMN_HEADER to set value for column name
	 * @param settingName Use Class COLUMN_SETTING to set value for column name
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
	 * This method is used to get the column detail of the displayed record entry from Grid
	 * @author hingorani_a
	 * @param columnName Use Class COLUMN_HEADER to set value for column name
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
				ColumnValue = controlActions.perform_GetElementByXPath(RolesManagerPageLocators.GRID_COLUMN_VALUE, 
						"COLUMNINDEXNO", Integer.toString(columnIndex));
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
	 * This method is used to set user to Work Group
	 * @author hingorani_a
	 * @param roleName Name of the role
	 * @param searchTxt Text to be searched
	 * @param filterColumn Use Class COLUMN_SETTING to set value for column name
	 * @param filterValue Provide the text to be used for filter
	 * @param permissionValue Use Class ROLE_PERMISSION to set value for permission
	 * @return boolean This returns true if user is set successfully
	 */
	public boolean searchAndSetRoleForModule(String roleName, String searchTxt, String filterColumn, String filterValue,
			String permissionValue) {
		String finalXpath = null;
		String selector = null;
		try {
			
			if(roleName!=null) {
				if(!selectRole(roleName))
					return false;
			}
			
			if(searchTxt!=null) {
				if(!searchTextForRole(searchTxt))
					return false;
			}
			
			if(filterColumn!=null) {
				if(!openAndApplySettingsForColumn(filterColumn, COLUMN_SETTING.FILTER, filterValue))
					return false;
			}
			
			finalXpath = controlActions.perform_GetDynamicXPath(RolesManagerPageLocators.PERMISSION_DDL_LST, 
					"PERMISSION", permissionValue);
			selector = "span[role='listbox']";
			if(!controlActions.setKendoDropDownValue(driver, selector, finalXpath))
				return false;
			logInfo("Permission set to " + permissionValue);
			
			if(!clickSaveBtn())
				return false;
			
			logInfo("Role " + roleName + " is updated");
			return true;
		}
		catch(Exception e) {
			logError("Failed to update role: " + roleName + " - "
					+ e.getMessage());
			return false;
		}	
	}
	
	
	public static class ROLES_TYPES {
		public static final String SUPERADMIN = "SuperAdmin";
		public static final String ADMIN_TOOL_USER = "Admin Tool User";
		public static final String DOCUMENTS_ADMIN = "Documents Admin";
	}
	
	public static class ROLE_PERMISSION {
		public static final String YES = "Yes";
		public static final String NO = "No";
	}
	
	public static class COLUMN_HEADER {
		public static final String MODULE = "Module";
		public static final String ACTION = "Action";
		public static final String PERMISSION = "Permission";
	}
	
	public static class COLUMN_SETTING {
		public static final String SORTASCENDING = "Sort Ascending";
		public static final String SORTDESCENDING = "Sort Descending";
		public static final String COLUMNS = "Columns";
		public static final String FILTER = "Filter";
	}
	
	public static class ROLE_ACTION {
		public static final String ACCESS_SUBSYSTEM = "Access Subsystem";
		public static final String ADDEDIT_VALIDATION = "Add/Edit Validation";
		public static final String COMPLETE_VALIDATION = "Complete Validation";
		public static final String SCHEDULE_TASK = "Schedule Task";
		public static final String DELETE_TASK_SCHEDULE = "Delete Task Schedule";
		public static final String EDIT_TASK_SCHEDULE = "Edit Task Schedule";
		public static final String CHARTS_ACCESS_SUBSYSTEM = "Charts Access Subsystem";
		
	}

	
	
}

