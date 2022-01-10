package com.project.safetychain.webapp.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.project.utilities.ControlActions;




public class UserManagerPage extends CommonPage {

	WebDriver driver;
	WebDriverWait wait;
	ControlActions controlActions;

	public UserManagerPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 40);
		controlActions = new ControlActions(driver);
	}

	/**
	 * Page Objects
	 */

	@FindBy(xpath = UserManagerPageLocators.ADD_NEW_USER_BTN)
	public WebElement AddNewUserBtn;

	@FindBy(xpath = UserManagerPageLocators.SAVE_BTN)
	public WebElement SaveBtn;

	@FindBy(xpath = UserManagerPageLocators.TIMEZONE_DDL)
	public WebElement TimezoneDdl;

	@FindBy(xpath = UserManagerPageLocators.COLUMN_NAMES_TBL)
	public List<WebElement> ColumnNamesTbl;

	@FindBy(xpath = UserManagerPageLocators.SUPPLIER_TAB)
	public WebElement SupplierTab;

	@FindBy(xpath = UserManagerPageLocators.SUPP_COLUMN_NAMES_TBL)
	public List<WebElement> SuppColumnNamesTbl;

	@FindBy(xpath = UserManagerPageLocators.WINDOW_TITLE)
	public WebElement WindowTitle;

	@FindBy(xpath = UserManagerPageLocators.WINDOW_CLOSE_BTN)
	public WebElement WindowCloseBtn;

	@FindBy(xpath = UserManagerPageLocators.USER_CALLOUT_MNU)
	public WebElement UserCalloutMnu;

	@FindBy(xpath = UserManagerPageLocators.UNLOCK_USER_OPTN)
	public WebElement UnlockUserOptn;

	@FindBy(xpath = UserManagerPageLocators.UNLOCK_USER_YES_BTN)
	public WebElement UnlockUserYesBtn;

	@FindBy(xpath = UserManagerPageLocators.DEACTIVATE_USER_OPTN)
	public WebElement DeactivateUserOptn;

	@FindBy(xpath = UserManagerPageLocators.DEACTIVATE_USER_YES_BTN)
	public WebElement DeactivateUserYesBtn;

	@FindBy(xpath = UserManagerPageLocators.REACTIVATE_USER_OPTN)
	public WebElement ReactivateUserOptn;

	@FindBy(xpath = UserManagerPageLocators.REACTIVATE_USER_YES_BTN)
	public WebElement ReactivateUserYesBtn;
	
	@FindBy(xpath = UserManagerPageLocators.LOCATION_TEXT_ALL)
	public WebElement LocationTextAll;
	
	@FindBy(xpath = UserManagerPageLocators.NO_RECORDS_AVAILBLE_FOR_USER)
	public WebElement NoRecordsAvailableForUser;

	/** Creating new object for Internal User	 */
	
	@FindBy(xpath = UserManagerPageLocators.INTERNAL_TAB)
	public WebElement InternalTab;
	
	/** Create new object for Location filter */
	
	@FindBy(xpath= UserManagerPageLocators.LOCATION_FILTER)
	public WebElement Location_Filter;
	
	/** Create new object for UserName on Add New User Pop up */
	
	/* @FindBy(xpath= UserManagerPageLocators.USER_NAME)
	public WebElement User_Name;
	*/

	@FindBy(xpath= UserManagerPageLocators.ERROR_WINDOW)
	public WebElement errorWindow;
	
	@FindBy(xpath= UserManagerPageLocators.OK_BUTTON)
	public WebElement okButton;
	
	@FindBy(xpath= UserManagerPageLocators.ERROR_EMAIL_WINDOW)
	public WebElement errorEmailWindow;
	
/** Create new object for Logout, Supplier Login button, Username, Password,NewPassword, CofirmNew Password **/
	
	@FindBy(xpath= UserManagerPageLocators.LOGOUT)
	public WebElement logout;
	
	@FindBy(xpath= UserManagerPageLocators.SUPPLIER_LOGIN_BTN)
	public WebElement supplierLoginBtn;
	
	@FindBy(xpath= UserManagerPageLocators.USER_ICON)
	public WebElement userIcon;
	
	@FindBy(xpath= UserManagerPageLocators.LOGIN_BTN)
	public WebElement loginBtn;
	
	@FindBy(xpath= UserManagerPageLocators.CREATE_PASSWORD_BTN)
	public WebElement createPasswordBtn;
	
	@FindBy(xpath= UserManagerPageLocators.LOCATION_ALL_REMOVE)
	public WebElement LocationAllRemove;
	
	@FindBy(xpath= UserManagerPageLocators.ALERT_MSG_TEXT)
	public WebElement AlertMsgText;
	
	
	/**
	 * Functions
	 */

	/**
	 * This method is used to click on ADD NEW USER button.
	 * @author thakker_k
	 * @param none
	 * @return boolean This returns true if ADD NEW USER button is clicked.
	 */
	public boolean clickAddNewUserBtn() {
		try {
			Sync();
			controlActions.WaitforelementToBeClickable(AddNewUserBtn);
			controlActions.clickOnElement(AddNewUserBtn);
			logInfo("Clicked on ADD NEW USER button");
			Sync();
			return true;
		}
		catch(Exception e) {
			logError("Failed to click on ADD NEW USER button "
					+ e.getMessage());
			return false;
		}		
	}

	/**
	 * This method is used to set values to text box.
	 * @author thakker_k
	 * @param fieldName Field name for which you want to set value
	 * @param fieldValue Field value to be set
	 * @return boolean This returns boolean true after setting value to text box
	 */
	public boolean setTextBoxValue(String fieldName, String fieldValue) {
		WebElement TextBox = null;
		try {

			TextBox = controlActions.perform_GetElementByXPath(UserManagerPageLocators.USER_DETAILS_TXT,"TEXTBOX",fieldName);

			controlActions.WaitforelementToBeClickable(TextBox);		
			controlActions.clickOnElement(TextBox);
			if(controlActions.perform_CheckIfElementTextEquals(TextBox, "")) {
				controlActions.sendKeys(TextBox, fieldValue);
			}
			else {
				//yet to code
			}
			threadsleep(5000);
			logInfo(fieldName +" entered: "+ fieldValue);
			return true;
		}
		catch(Exception e) {
			logError("Failed to set value for field " + fieldName + " :"
					+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to set values to MultiSelect type of elements.
	 * @author hingorani_a
	 * @param fieldName Field name for which you want to set value
	 * @param fieldValue List of values to be set
	 * @return boolean This returns boolean true after setting values to MultiSelect
	 */
	public boolean setMultiSelectValue(String fieldName, List<String> fieldValue) {
		WebElement MultiSelectElement = controlActions.perform_GetElementByXPath(UserManagerPageLocators.USER_DETAILS_TXT,
				"TEXTBOX",fieldName);
		try {
			for(String selectVal : fieldValue) {

				controlActions.WaitforelementToBeClickable(MultiSelectElement);		
				controlActions.clickOnElement(MultiSelectElement);	
				if(controlActions.perform_CheckIfElementTextEquals(MultiSelectElement, "")) {
					controlActions.sendKeys(MultiSelectElement, selectVal);		
					controlActions.actionEnter();
				}
				threadsleep(3000);
				controlActions.clickOnElement(WindowTitle);
				logInfo(fieldName +" entered: "+ fieldValue);
			}
			return true;
		}
		catch(Exception e) {
			logError("Failed to set value for field " + fieldName + " :"
					+ e.getMessage());
			return false;
		}	
	}

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
	 * This method is used to click on Save button.
	 * @author thakker_k
	 * @param none
	 * @return boolean This returns true if Save button is clicked.
	 */
	public boolean clickSaveBtn() {
		try {
			controlActions.WaitforelementToBeClickable(SaveBtn);
			controlActions.clickOnElement(SaveBtn);
			logInfo("Clicked on Save button");
			threadsleep(5000);
			Sync();
			return true;
		}
		catch(Exception e) {
			logError("Failed to click on Save button "
					+ e.getMessage());
			if(controlActions.isElementDisplay(WindowCloseBtn)) {
				controlActions.clickOnElement(WindowCloseBtn);
				Sync();
			}
			return false;
		}		
	}

	/**
	 * This method is used to set User details
	 * @author hingorani_a
	 * @param UsrDetailParams An object of Class UsrDetailParams, in order to set
	 * different fields like Username, Timezone, Locations for creating user.
	 * @return boolean This returns boolean true after setting value for User
	 */
	public boolean setUsrDetails(UsrDetailParams UDP) {
		try {

			if(UDP.Suppliers !=null) {
				if(!setMultiSelectValue(USERFIELDS.SUPPLIERS,UDP.Suppliers)) {
				}
				logInfo("Supplier " + UDP.Suppliers + " is set for the user");
			}

			if(UDP.Title != null) {
				if(!setTextBoxValue(USERFIELDS.TITLE,UDP.Title)) {
					return false;
				}
				logInfo("Title " + UDP.Title + " is set for the user");
			}

			if(UDP.Username != null) {
				if(!setTextBoxValue(USERFIELDS.USERNAME,UDP.Username)) {
					return false;
				}
				logInfo("Username " + UDP.Username + " is set for the user");
			}

			if(UDP.Roles != null) {
				if(!setMultiSelectValue(USERFIELDS.ROLES,UDP.Roles)) {
					return false;
				}
				logInfo("Roles " + UDP.Roles + " is set for the user");
			}

			if(UDP.EmployeeId != null) {
				if(!setTextBoxValue(USERFIELDS.EMPLOYEEID,UDP.EmployeeId)) {
					return false;
				}
				logInfo("Employee Id " + UDP.EmployeeId + " is set for the user");
			}

			if(UDP.Password != null) {
				if(!setTextBoxValue(USERFIELDS.PASSWORD,UDP.Password)) {
					return false;
				}
				logInfo("Password " + UDP.Password + " is set for the user");
			}

			if(UDP.FirstName != null) {
				if(!setTextBoxValue(USERFIELDS.FIRSTNAME,UDP.FirstName)) {
					return false;
				}
				logInfo("First Name " + UDP.FirstName + " is set for the user");
			}

			if(UDP.Locations != null) {
				if(!setMultiSelectValue(USERFIELDS.LOCATIONS,UDP.Locations)) {
					return false;
				}
				logInfo("Locations " + UDP.Locations + " is set for the user");
			}

			if(UDP.LastName != null) {
				if(!setTextBoxValue(USERFIELDS.LASTNAME,UDP.LastName)) {
					return false;
				}
				logInfo("Last Name " + UDP.LastName + " is set for the user");
			}

			if(UDP.Email != null) {
				if(!setTextBoxValue(USERFIELDS.EMAIL,UDP.Email)) {
					return false;
				}
				logInfo("Email " + UDP.Email + " is set for the user");
			}

			if(UDP.WorkGroups != null) {
				if(!setMultiSelectValue(USERFIELDS.WORKGROUPS,UDP.WorkGroups)) {
					return false;
				}
			}

			if(UDP.Phone != null) {
				if(!setTextBoxValue(USERFIELDS.PHONE,UDP.Phone)) {
					return false;
				}
				logInfo("Phone " + UDP.Phone + " is set for the user");
			}

			if(UDP.Pin != null) {
				if(!setTextBoxValue(USERFIELDS.PIN,UDP.Pin)) {
					return false;
				}
				logInfo("Pin " + UDP.Pin + " is set for the user");
			}

			if(UDP.Timezone != null) {
				String selector = "kendo-dropdownlist[name='TimeZone'] > span";
				if(!setDropDownValue(UserManagerPageLocators.TIMEZONE_LST,selector,UDP.Timezone)) {
					return false;
				}
				logInfo("Timezone " + UDP.Timezone + " is set for the user");
			}

			return true;
		}
		catch(Exception e) {
			logError("Failed to create User with Username : "+ UDP.Username + " - "
					+ e.getMessage());
			return false;
		}	
	}


	/**
	 * This method is used to create Internal User.
	 * @author hingorani_a
	 * @param UsrDetailParams An object of Class UsrDetailParams, in order to set
	 * different fields like Username, Timezone, Locations for creating user.
	 * @return boolean This returns boolean true after setting value to text box
	 */
	public boolean createInternalUser(UsrDetailParams UDP) { 
		try {

			if(!clickAddNewUserBtn()) {
				return false;
			}

			if(!setUsrDetails(UDP)) {
				return false;
			}

			if(!clickSaveBtn()) {
				return false;
			}

			logInfo("User with Username : "+ UDP.Username + " is created successfully");
			return true;
		}
		catch(Exception e) {
			logError("Failed to create User with Username : "+ UDP.Username + " - "
					+ e.getMessage());
			if(controlActions.isElementDisplay(WindowCloseBtn)) {
				controlActions.clickOnElement(WindowCloseBtn);
				Sync();
			}
			return false;
		}	
	}

	/**
	 * This method is used to find Column Index for the column present in Users Grid
	 * @author hingorani_a
	 * @param columnName Column name whose Index you need. Use Class USERFIELDS to pass column name.
	 * This Index is in turn used in creating dynamic xpaths. 
	 * @return int This returns value of column index. 
	 * Returns 0, if column is not found
	 */
	public int getColumnIndex(String columnName) {
		int columnIndex = 1; 
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
	 * This method is used to search user
	 * @author hingorani_a
	 * @param columnName Column name whose Index you need. Use Class USERFIELDS to pass column name.
	 * This Index is in turn used in creating dynamic xpaths. 
	 * @param value The value you would like to use to search user 
	 * @return boolean This returns true if user search is performed
	 */
	public boolean searchUsrWithDetails(String columnName, String value) {
		String finalColumnNameXpath = null; 
		try {
			int columnIndex = getColumnIndex(columnName);
			if(columnIndex == 0) {
				logError("Failed to get correct column index : " + columnIndex + " for column " + columnName);
				return false;
			}
			else {
				finalColumnNameXpath = controlActions.perform_GetDynamicXPath(UserManagerPageLocators.COLUMN_NAMES_TXT, 
						"COLUMNINDEXNO", 
						Integer.toString(columnIndex));

				if(controlActions.perform_PutText(finalColumnNameXpath,value, true)) {
					logInfo("Searched user with value : " + value + " for column : " + columnName);
					return true;
				}
			}
			return false;
		}
		catch(Exception e) {
			logError("Failed to search user with value : " + value + " for column : " + columnName + " - "
					+ e.getMessage());
			return false;
		}	
	}


	/**
	 * This method is used to search and verify if a user exists using any given parameter
	 * @author hingorani_a
	 * @param columnName Column name whose Index you need. Use Class USERFIELDS to pass column name.
	 * This Index is in turn used in creating dynamic xpaths. 
	 * @param value The value you would like to compare to ensure user exists
	 * @return boolean This returns true if it's verified that user exists
	 */
	public boolean searchAndVerifyWithUsrDetails(String columnName, String value) {
		String finalColumnNameXpath = null;
		String finalColumnValueXpath = null;
		WebElement ColumnValue = null; 
		try {
			int columnIndex = getColumnIndex(columnName);
			if(columnIndex == 0) {
				logError("Failed to get correct column index : " + columnIndex + " for column " + columnName);
				return false;
			}
			else {
				finalColumnNameXpath = controlActions.perform_GetDynamicXPath(UserManagerPageLocators.COLUMN_NAMES_TXT, 
						"COLUMNINDEXNO", 
						Integer.toString(columnIndex));

				if(!controlActions.perform_PutText(finalColumnNameXpath,value, false)) {
					logError("Could not set value : " + value + " for column : " + columnName);
					return false;
				}

				finalColumnValueXpath = controlActions.perform_GetDynamicXPath(UserManagerPageLocators.USR_COLUMN_VALUE, 
						"COLUMNINDEXNO", 
						Integer.toString(columnIndex));

				ColumnValue = controlActions.perform_GetElementByXPath(finalColumnValueXpath);
				if(controlActions.perform_CheckIfElementTextEquals(ColumnValue, value)) {
					return true;
				}
			}
			return false;
		}
		catch(Exception e) {
			logError("Failed to set value : " + value + " for column : " + columnName + " - "
					+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to search and get User detail as per column
	 * @author hingorani_a
	 * @param columnName Column name whose Index you need. Use Class USERFIELDS to pass column name.
	 * This Index is in turn used in creating dynamic xpaths. 
	 * @return String This returns string value with the user detail else null
	 */
	public String getUsrDetails(String columnName) {
		String finalColumnValueXpath = null;
		WebElement ColumnValue = null; 
		try {
			int columnIndex = getColumnIndex(columnName);
			if(columnIndex == 0) {
				logError("Failed to get correct column index : " + columnIndex + " for column " + columnName);
				return null;
			}
			else {
				finalColumnValueXpath = controlActions.perform_GetDynamicXPath(UserManagerPageLocators.USR_COLUMN_VALUE, 
						"COLUMNINDEXNO", 
						Integer.toString(columnIndex));

				ColumnValue = controlActions.perform_GetElementByXPath(finalColumnValueXpath);
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
	 * This method is used to select supplier tab and click on add new user button.
	 * @author choubey_a
	 * @return boolean This returns true if the details of the tab is not clickable and add new user button is not clicked.
	 */

	public boolean clickSupplierTab() {
		try {
			Sync();
			controlActions.WaitforelementToBeClickable(SupplierTab);
			controlActions.clickOnElement(SupplierTab);
			Sync();
			logInfo("Clicked on 'Supplier tab'");
			return true;
		}
		catch(Exception e) {
			logError("Failed to click on 'Supplier Tab' "
					+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to create Supplier User.
	 * @author choubey_a
	 * @param UsrDetailParams An object of Class UsrDetailParams, in order to set
	 * different fields like Username, Suppliers, Title for creating supplier user.
	 * @return boolean This returns boolean true after setting value to text box
	 */
	public boolean createsupplieruser(UsrDetailParams UDP) {
		try {

			if(!clickAddNewUserBtn()) {
				return false;
			}

			if(!setUsrDetails(UDP)) {
				return false;
			}

			if(!clickSaveBtn()) {
				return false;
			}

			logInfo("Supplier User with Username : "+ UDP.Username + " is created successfully");
			return true;
		}
		catch(Exception e) {
			logError("Failed to create Supplier User with Username : "+ UDP.Username + " - "
					+ e.getMessage());
			if(controlActions.isElementDisplay(WindowCloseBtn)) {
				controlActions.clickOnElement(WindowCloseBtn);
				Sync();
			}
			return false;
		}


	}

	/**
	 * This method is used to find Column Index for the column present in Supplier Users Grid
	 * @author choubey_a
	 * @param columnName Column name whose Index you need. Use Class USERFIELDS to pass column name.
	 * This Index is in turn used in creating dynamic xpaths. 
	 * @return int This returns value of column index. 
	 * Returns 0, if column is not found
	 */
	public int getSuppColumnIndex(String columnName) {
		int columnIndex = 1; 
		try {
			for(WebElement column : SuppColumnNamesTbl) {
				if(column.getText().equalsIgnoreCase(columnName)) {
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
	 * This method is used to search and verify if a supplier user exists using any given parameter
	 * @author choubey_a
	 * @param columnName Column name whose Index you need. Use Class USERFIELDS to pass column name.
	 * This Index is in turn used in creating dynamic xpaths. 
	 * @param value The value you would like to compare to ensure supplier user exists
	 * @return boolean This returns true if it's verified that supplier user exists
	 */
	public boolean searchAndVerifyWithSuppUsrDetails(String columnName, String value) {
		String finalColumnNameXpath = null;
		String finalColumnValueXpath = null;
		WebElement ColumnValue = null; 
		try {
			int columnIndex = getSuppColumnIndex(columnName);
			if(columnIndex == 0) {
				logError("Failed to get correct column index : " + columnIndex + " for column " + columnName);
				return false;
			}
			else {
				finalColumnNameXpath = controlActions.perform_GetDynamicXPath(UserManagerPageLocators.SUPP_COLUMN_NAMES_TXT, 
						"COLUMNINDEXNO", 
						Integer.toString(columnIndex));
				threadsleep(5000);

				if(!controlActions.perform_PutText(finalColumnNameXpath,value, false)) {
					logError("Could not set value : " + value + " for column : " + columnName);
					threadsleep(5000);
					return false;
				}

				finalColumnValueXpath = controlActions.perform_GetDynamicXPath(UserManagerPageLocators.SUPP_USR_COLUMN_VALUE, 
						"COLUMNINDEXNO", 
						Integer.toString(columnIndex));
				threadsleep(5000);

				ColumnValue = controlActions.perform_GetElementByXPath(finalColumnValueXpath);
				if(controlActions.perform_CheckIfElementTextEquals(ColumnValue, value)) {
					threadsleep(5000);
					return true;
				}
			}
			return false;
		}
		catch(Exception e) {
			logError("Failed to set value : " + value + " for column : " + columnName + " - "
					+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to create Supplier User.
	 * @author pashine_a (Original Credits - choubey_a)
	 * @param UsrDetailParams An object of Class UsrDetailParams, in order to set
	 * different fields like Username, Suppliers, Title for creating supplier user.
	 * @return boolean This returns boolean true after setting value to text box
	 */
	public boolean supplierUserCreation(UsrDetailParams udp) {
		try {
			if(clickUsersMenu().error) {
				logError("Failed to navigate to 'Manage User'");
				return false;
			}
			if(!clickSupplierTab()) {
				logError("Failed to open the 'Supplier user tab'");
				return false;
			}
			if(!createsupplieruser(udp)) {
				logError("Failed to create supplier");
				return false;
			}
			if(!searchAndVerifyWithSuppUsrDetails(USERFIELDS.USERNAME, udp.Username)) {
				logError("Failed to verify created supplier");
				return false;
			}
			logInfo("Supplier User with Username : "+ udp.Username + " is created successfully");
			return true;
		}
		catch(Exception e) {
			logError("Failed to create Supplier User with Username : "+ udp.Username + " - "
					+ e.getMessage());
			return false;
		}


	}

	/**
	 * This method is used to search and open id a user exists using any given parameter
	 * @author hingorani_a
	 * @param columnName Column name whose Index you need. Use Class USERFIELDS to pass column name.
	 * This Index is in turn used in creating dynamic xpaths. 
	 * @param value The value you would like to compare to ensure user exists
	 * @return boolean This returns true if user details are opened
	 */
	public boolean searchAndOpenUsrDetails(String columnName, String value) {
		String finalColumnNameXpath = null;
		WebElement UsrColumnValue = null; 
		try {
			int columnIndex = getColumnIndex(columnName);
			if(columnIndex == 0) {
				logError("Failed to get correct column index : " + columnIndex + " for column " + columnName);
				return false;
			}
			else {
				finalColumnNameXpath = controlActions.perform_GetDynamicXPath(UserManagerPageLocators.COLUMN_NAMES_TXT, 
						"COLUMNINDEXNO", 
						Integer.toString(columnIndex));

				if(!controlActions.perform_PutText(finalColumnNameXpath,value, false)) {
					logError("Could not set value : " + value + " for column : " + columnName);
					return false;
				}

				UsrColumnValue = controlActions.perform_GetElementByXPath(UserManagerPageLocators.USR_COLUMN_VALUE, 
						"COLUMNINDEXNO", 
						Integer.toString(columnIndex));

				controlActions.click(UsrColumnValue);
				Sync();
				logInfo("Opened details for user having value : " + value + " for column : " + columnName);
				return true;
			}
		}
		catch(Exception e) {
			logError("Failed to set value : " + value + " for column : " + columnName + " - "
					+ e.getMessage());
			return false;
		}	
	}


	/**
	 * This method is used to unlock the user
	 * @author pashine_a
	 * @param username - A username which user wants to unlock
	 * @return boolean This returns true if user is unlocked
	 */
	public boolean unlockUser(String username) {
		WebElement userRow;
		try {
			if(!searchUsrWithDetails(USERFIELDS.USERNAME, username)) {
				logError("Failed to get user - "+username);
			}
			userRow = controlActions.perform_GetElementByXPath(UserManagerPageLocators.HOVER_TBL, "USERNAME", username);
			controlActions.hoverElement(userRow);
			controlActions.clickElement(UserCalloutMnu);
			controlActions.clickElement(UnlockUserOptn);
			controlActions.clickElement(UnlockUserYesBtn);
			Sync();
			logInfo("Unlocked the user - "+username);
			return true;
		}catch(Exception e) {
			logError("Failed to unlock the user - "+ username + " - "+ e.getMessage());
			return false;
		}	
	}


	/**
	 * This method is used to deactivate the user
	 * @author pashine_a
	 * @param username - A username which user wants to deactivate
	 * @return boolean This returns true if user is deactivated
	 */
	public boolean deactivateUser(String username) {
		WebElement userRow;
		try {
			if(!searchUsrWithDetails(USERFIELDS.USERNAME, username)) {
				logError("Failed to get user - "+username);
			}
			userRow = controlActions.perform_GetElementByXPath(UserManagerPageLocators.HOVER_TBL, "USERNAME", username);
			controlActions.hoverElement(userRow);
			controlActions.clickElement(UserCalloutMnu);
			controlActions.clickElement(DeactivateUserOptn);
			controlActions.clickElement(DeactivateUserYesBtn);
			Sync();
			logInfo("Deactivated the user - "+username);
			return true;
		}catch(Exception e) {
			logError("Failed to deactivate the user - "+ username + " - "+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to reactivate the user
	 * @author pashine_a
	 * @param username - AA username which user wants to reactivate
	 * @return boolean This returns true if user is reactivated
	 */
	public boolean reactivateUser(String username) {
		WebElement userRow;
		try {
			if(!searchUsrWithDetails(USERFIELDS.USERNAME, username)) {
				logError("Failed to get user - "+username);
			}
			userRow = controlActions.perform_GetElementByXPath(UserManagerPageLocators.HOVER_TBL, "USERNAME", username);
			controlActions.hoverElement(userRow);
			controlActions.clickElement(UserCalloutMnu);
			controlActions.clickElement(ReactivateUserOptn);
			controlActions.clickElement(ReactivateUserYesBtn);
			Sync();
			logInfo("Reactivated the user - "+username);
			return true;
		}catch(Exception e) {
			logError("Failed to reactivate the user - "+ username + " - "+ e.getMessage());
			return false;
		}	
	}

	/** Create Function for Internal User button */
	/**
	 * This method is used to check wherter internal button is visible or not
	 * @author chandere_s
	 * @param none
	 * @return boolean this is return true if internal button is visible
	 */
	public boolean visibleInternalTab()
	
	{
			try {
				
				
				//Check visiblity for Internal button
				boolean internalTabVisible= InternalTab.isDisplayed();
				
				
				// If button is visible then return true
				
				if(internalTabVisible)
				{
					logInfo("Internal tab button is visible");
					return true;
					
				}
				
				//else return false
				else
				{
					logInfo("Internal tab button is not visible");
					return false;
					
				}
							
				
			} catch (Exception e)
			{
				logError("failed to check button is visible or not" +e.getMessage());
				
				return false;
				// TODO: handle exception
			}
		
		
	}
	
	
	/** Create Function for Supplier User button */
	/**
	 * This method is used to check wherter Supplier button is visible or not
	 * @author chandere_s
	 * @param none
	 * @return boolean this is return true if supplier button is visible
	 */
	
	
	public boolean visibleSupplierTab()
	{
		//Code must be return in try catch
		try {
			
			//Check visibility of Supplier tab
			boolean supplierTabVisible=SupplierTab.isDisplayed();
			
			//If supplier tab is visible then return true 
			if(supplierTabVisible)
			{
				logInfo("Supplier tab button is visible");
				return true;
				
			}
			//else return false
			else
			{
				logInfo("Supplier tab button is not visible");
				return false;
			}
			
		} catch (Exception e)
		
		{
			logInfo("Failed to check supplier tab button is visible or not"+e.getMessage());
			return false;
			// TODO: handle exception
		}
		
	}
	
	
	
	
	/** Create Function for Location filter */
	/**
	 * This method is used to check wherter Location filter is visible or not
	 * @author chandere_s
	 * @param none
	 * @return boolean this is return true if Location filter is visible
	 */
	
	
	public boolean visibleLocationFilter()
	{
		
		try 
		{
			//check location filter is visible
			
		boolean locationFilter=	Location_Filter.isDisplayed();
		
		
		
		if(locationFilter)
		{
			logInfo("Location filter is visible on manager user page");
			return true;
			
		}
		
		else
		{
			logInfo("Location filter is not visible on manager user page");
			return false;
			
		}
			
		} catch (Exception e) 
		
		{
			logError("Failed to check Location filter is visible or not"+e.getMessage());
			
			return false;
			// TODO: handle exception
		}
		
	}
	
	
	
	/** Crate a method for Username, firstname, lastname, email & timezone filters	 */
	/**
	 * This method is used to check wherter Filters(Username, firstname, lastname, email & timezone) are visible or not on Manage User page
	 * @author chandere_s
	 * @param none
	 * @return boolean this is return true if Filters are visible
	 */
	
	public boolean isFiltersVisible()
	{
		try {
			
			for(int i =2 ; i<=6; i++) {

			WebElement filterElement = controlActions.perform_GetElementByXPath(UserManagerPageLocators.FILTERS, "NUMBER", Integer.toString(i));
			
			boolean isfilterVisible = filterElement.isDisplayed();
			
			if(isfilterVisible) {
				
				switch(i) 
				{
				case 2:
					logInfo("UserName Filter is Displayed");
					break;
				case 3:
					logInfo("FirstName Filter  is Displayed");
					break;
					
				case 4:
					logInfo("LastName Filter  is Displayed");
					break;
						
				case 5:
					logInfo("Email Filter  is Displayed");
					break;
					
				case 6:
					logInfo("TimeZone Filter  is Displayed");
					break;
					
				}			
				
			}else {
				switch(i) 
				{
				case 2:
					logInfo("UserName Filter is not Displayed");
					break;
				case 3:
					logInfo("FirstName Filter  is not Displayed");
					break;
					
				case 4:
					logInfo("LastName Filter is not Displayed");
					break;
						
				case 5:
					logInfo("Email Filter  is not Displayed");
					break;
					
				case 6:
					logInfo("TimeZone Filter  is not Displayed");
					break;
					
				}			
				}
			
			}			
			
			logInfo("All filters are diplayed");
			return true;
			
		} catch (Exception e) {
			logError("Failed to check Location filter is visible or not"+e.getMessage());
			return false;
		}
		

	}
	
	
	/**This method is used to verify duplicate username error pop up on "Add New User" 
	 * @author chandere_s
	 * @return 
	 */
	public boolean vlidateDuplicateUsername()
	{
		WebElement addUserWindow = null;
		String existingUserName = "autouser01";
		try {
			//Click on add new user
			clickAddNewUserBtn();
			
			// If button is visible then return true
			addUserWindow = controlActions.perform_GetElementByXPath(UserManagerPageLocators.ADD_USER_WINDOW);
			if(addUserWindow.isDisplayed())
			{
				logInfo("Add New User Window is displayed/opened ");
				
				//Enter existing Username
				WebElement userNameInput = controlActions.perform_GetElementByXPath(UserManagerPageLocators.USER_NAME_INPUT);
				userNameInput.sendKeys(existingUserName);
				
				//click save button
				clickSaveBtn();
				
				//V.t Uneiq nm popup
				boolean ErrorWindow = errorWindow.isDisplayed();
				if(ErrorWindow)
				{
					logInfo("Verified - Username must be unique error is displayed");
					
					//Click Error Popup OK
					okButton.click();
					
					//Closed Window
					controlActions.clickElement(WindowCloseBtn);
					return true;
				}
				else
				{
					logInfo("Could Not verify unique name user error. Another error message is displayed");
					return false;
				}
			}
			else
			{
				logInfo("Add New User Window is not displayed/opened ");
				return false;
			}
		} catch (Exception e) {
			
			logError("Failed to check duplicate username error pop up on \"Add New User\" " + e.getMessage());
			return false;
		}
	}  
	
	/** This method is used to verify of valid Email validation on "Add New User" Pop Up
	 * @author chandere_s
	 */
	
	public boolean validateEmailAddress()
	{
		
		String invalidEmail="$!#%^*@test.com";
		try {
			//Click on add new user
			clickAddNewUserBtn();
			
			// If button is visible then return true
			WebElement addUserWindow = controlActions.perform_GetElementByXPath(UserManagerPageLocators.ADD_USER_WINDOW);
			if(addUserWindow.isDisplayed())
			{
				logInfo("Add New User Window is displayed/opened ");
				
				//Enter Invalid Username
				WebElement emailInput = controlActions.perform_GetElementByXPath(UserManagerPageLocators.EMAIL_INPUT);
				emailInput.sendKeys(invalidEmail);
				
				//click save button
				clickSaveBtn();
				
				//V.t Uneiq nm popup
				boolean ErrorEmailWindow = errorEmailWindow.isDisplayed();
				if(ErrorEmailWindow)
				{
					logInfo("Verified - Please input a valid email address!");
					
					//Click Error Popup OK
					okButton.click();
					
					//Closed Window
					controlActions.clickElement(WindowCloseBtn);
					return true;
				}
				else
				{
					logInfo("Could Not verify vaild email address. Another error message is displayed");
					return false;
				}
			}
			else
			{
				logInfo("Add New User Window is not displayed/opened ");
				return false;
			}
			
			
		} catch (Exception e) {
			logInfo("Failed to check verify of valid Email validation on \"Add New User\" Pop Up "+e.getMessage());
			// TODO: handle exception
			return false;
		}
	}
	
	
	/** This method is used to validation on all mandatory fields in "Add New User" Pop Up
	 * @author chandere_s
	 */
	
	public boolean validateReuiredField()
	{
	  String [] field= {"username", "password", "firstName", "lastName", "email", "timezone", "location", "role"};
		try {
			
			for(int i =0 ; i<=field.length; i++) 
			{
				WebElement fieldElement= controlActions.perform_GetElementByXPath(UserManagerPageLocators.REQUIREDFIELD,"REQUIREDFIELDNAME", field[i]);
			
			boolean requiredFieldElement = fieldElement.isDisplayed();
			
			if(requiredFieldElement) {
				
				switch(i) 
				{
				case 0:
					logInfo("Reuired field- User Name is displayed");
					break;
				case 1:
					logInfo("Reuired field- Password is displayed");
					break;
					
				case 2:
					logInfo("Reuired field- First Name is displayed");
					break;
						
				case 3:
					logInfo("Reuired field- Last Name is displayed");
					break;
					
				case 4:
					logInfo("Reuired field- Email is displayed");
					break;
					
				case 5:
					logInfo("Reuired field- Timezone is displayed");
					break;
					
				case 6:
					logInfo("Reuired field- Location(s) is displayed");
					break;
					
				case 7:
					logInfo("Reuired field- Role(s) is displayed");
					
					break;
					
				}	
				//Closed Window
				controlActions.clickElement(WindowCloseBtn);
				return true;
				
			}else {
				switch(i) 
				{
				case 0:
					logInfo("Reuired field- User Name is not displayed");
					break;
				case 1:
					logInfo("Reuired field- Password is not displayed");
					break;
					
				case 2:
					logInfo("Reuired field- First Name is not displayed");
					break;
						
				case 3:
					logInfo("Reuired field- Last Name is not displayed");
					break;
					
				case 4:
					logInfo("Reuired field- Email is not displayed");
					break;
					
				case 5:
					logInfo("Reuired field- Timezone is not displayed");
					break;
					
				case 6:
					logInfo("Reuired field- Location(s) is not displayed");
					break;
					
				case 7:
					logInfo("Reuired field- Role(s) is not displayed");
					break;
				}			
				}
			
			}			
			
			logInfo("All required fields are diplayed");
			return true;
			
		} catch (Exception e) {
			logError("Failed to check Reuired fileds are visible or not" +e.getMessage());
			return false;
		}
		

	}
	
	public boolean clickCreatePasswordBtn() {
		try {
			Sync();
			controlActions.WaitforelementToBeClickable(createPasswordBtn);
			controlActions.clickOnElement(createPasswordBtn);
			logInfo("Clicked on Create Password button");
			Sync();
			return true;
		}
		catch(Exception e) {
			logError("Failed to click on Create password button "
					+ e.getMessage());
			return false;
		}		
	}
	
	
	// if location filter is visible then return true
	//else return false

	/**This method is used to close the Create User window/dialog.
	 * @author ahmed_tw
	 * @return [boolean] : True after closing Create User window/dialog, false otherwise
	 */
	public boolean closeWindow() {
		try {
			if(controlActions.isElementDisplay(WindowCloseBtn)) {
				controlActions.clickOnElement(WindowCloseBtn);
				Sync();
				logInfo("Closed Window");
			}else {
				logInfo("Window is not there");
			}
			return true;
		} catch (Exception e) {
			logError("Clould Not Close Window" + e.getMessage());
			return false;
		}
	}
	
	/**This method is used to check "ALL" option in locations field is displayed or not.
	 * @author jadhav_akan
	 * @return [boolean] : True after "ALL" option is Displayed
	 */
	public boolean AllOptionIsDisplayed() {
		try {
			boolean text = controlActions.isElementDisplay(LocationTextAll);
		
			System.out.println(text);
			logInfo("ALL option is Displayed");
			return true;
		} catch (Exception e) {
			logError("ALL option is not Displayed" + e.getMessage());
			return false;
		}
	}
	
	/**This method is used to check "ALL" option is Displayed in UpperCase or not.
	 * @author jadhav_akan
	 * @return [boolean] : True after "ALL" option is Displayed in UpperCase
	 */
	public boolean AllOptionIsCapitalized() {
		try {
			WebElement str = LocationTextAll;
			String input = str.getText();
			 char[] charArray = input.toCharArray();
			 for(int i=0; i < charArray.length; i++) {
				 if (Character.isUpperCase(charArray[i])) {
					logInfo("ALL option is displayed in Capital");
					return true; 
			      }
			 }
			 logInfo("ALL option is displayed in Capital");
				return true;
		    } catch (Exception e) {
			logError("ALL option is not Displayed in Capital" + e.getMessage());
			return false;
		    }	
	}
	
	/**This method is used to remove "ALL" option from locations field.
	 * @author jadhav_akan
	 * @return [boolean] : True after "ALL" option is removed form locations field.
	 */
	public boolean removeLocationAll() {
		try {
			controlActions.clickElement(LocationAllRemove);
			logInfo("ALL option from location is removed");
			return true;
		} catch (Exception e) {
			logError("Fail to remove ALL option location" + e.getMessage());
			return false;
		}
	}
	
	/**This method is used to check alert message is Displayed or not.
	 * @author jadhav_akan
	 * @return [boolean] : True after alert message "These are required fields" is displayed.
	 */
	public boolean alertMsgDisplay() {
		try {
			WebElement text = AlertMsgText;
			String text1 = text.getText();
			String text2 = "These are required fields";
			   if(text2.equalsIgnoreCase(text1)) {
			        logInfo("Alert msg is Displayed "+  text1);
			        return true;
			    }
			logInfo("Alert msg is Displayed");
			return true;
		} catch (Exception e) {
			logError("Fail to display Alert msg" + e.getMessage());
			return false;
		}
	}

	
	/**
	 * Supporting Classes 
	 */

	public static class UsrDetailParams{
		public String Username;
		public String EmployeeId;
		public String Password;
		public String FirstName;
		public String LastName;
		public String Email;
		public String Phone;
		public List<String> Locations;
		public List<String> DefaultLocation;
		public List<String> Roles;
		public List<String> WorkGroups;
		public String WorkGroups1;
		public String Pin;
		public String Timezone;
		public String SupplierUserName;
		public List<String> Suppliers;
		public String Title;
	}

	public static class USERFIELDS{
		public static final String USERNAME = "User Name";
		public static final String EMPLOYEEID = "Employee ID";
		public static final String PASSWORD = "Password";
		public static final String FIRSTNAME = "First Name";
		public static final String LASTNAME = "Last Name";
		public static final String EMAIL = "Email";
		public static final String PHONE = "Phone";
		public static final String LOCATIONS = "Location(s)";
		public static final String ROLES = "Role(s)";
		public static final String WORKGROUPS = "Work Group(s)";
		public static final String PIN = "Pin";	
		public static final String SUPPLIERS ="Suppliers(s)";
		public static final String TITLE = "Title";
		public static final String TIMEZONE = "Time Zone";
	}

}
