package com.project.safetychain.webapp.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.project.utilities.ControlActions;

public class LinkRecordsPage extends CommonPage{

	WebDriver driver;
	WebDriverWait wait;
	ControlActions controlActions;

	public LinkRecordsPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 40);
		controlActions = new ControlActions(driver);	
	}	

	/**
	 * Page Objects
	 */

	@FindBy(xpath = LinkRecordsPageLocators.RECS_FORM_LST)
	public List<WebElement> RecsFormLst;
	
	@FindBy(xpath = LinkRecordsPageLocators.COLUMN_NAMES_TBL)
	public List<WebElement> ColumnNamesTbl;
	
	@FindBy(xpath = LinkRecordsPageLocators.REC_TBL_TIME_COLUMN)
	public WebElement RecTblTimeColumn;
	
	
	/**
	 * Functions
	 */
	
	/** This method is used find and click on the mentioned Form
	 * @author hingorani_a
	 * @param formName The name of the form
	 * @return boolean This returns true if the form is clicked/selected
	 */
	public boolean findAndClickFormToSelect(String formName) {
		try {
			RecsFormLst = controlActions.WaitUntilElementsAreStale(LinkRecordsPageLocators.RECS_FORM_LST);

			for(WebElement form : RecsFormLst) {
				if(form.getText().contains(formName)) {
					controlActions.WaitforelementToBeClickable(form);
					controlActions.perform_ClickWithJavaScriptExecutor(form);
					// In order to wait
					controlActions.perform_waitUntilNotVisible(form);
					
					logInfo("Clicked on form " + formName);
					return true;
				}
			}
			
			logInfo("Could not click on form " + formName);
			return false;
		}catch(Exception e) {
			logError("Failed to click on form " + formName 
					+ e.getMessage());
			return false;
		}
	}
	
	/**
	 * This method is used to find Column Index for the column present in Records Grid
	 * @author hingorani_a
	 * @param columnName Column name whose Index you need. Use Class RecordFieldNames to pass column name.
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
	 * This method is used to verify value of record with the one passed as 'value' parameter
	 * @author hingorani_a
	 * @param columnName Column name whose Index you need. Use Class RecordFieldNames to pass column name.
	 * @param value The value you would like to verify for Record 
	 * @return boolean This returns true if Record detail is verified
	 */
	public boolean verifyRecordsDetail(String columnName, String value) {
		String columnsNamesTxtXpath = null;
		WebElement ColumnsNamesTxt = null;
		String recordValue = null;
		try {
			int columnIndex = getColumnIndex(columnName);
			if(columnIndex == 0) {
				logError("Failed to get correct column index : " + columnIndex + " for column " + columnName);
				return false;
			}
			else {
				
				columnsNamesTxtXpath = controlActions.perform_GetDynamicXPath(LinkRecordsPageLocators.COLUMN_NAMES_TXT, 
						"COLUMNINDEXNO", 
						Integer.toString(columnIndex+1));
				
				ColumnsNamesTxt = controlActions.WaitUntilElementIsStale(columnsNamesTxtXpath);
				controlActions.perform_waitUntilVisibility(ColumnsNamesTxt);
				
				recordValue = ColumnsNamesTxt.getText();
				if(recordValue.contains(value)) {
					logInfo("Verified value for " + columnName + " is " + value);
					return true;
				}
			}
			
			logInfo("Could not verify value for " + columnName + " is " + value);
			return false;
		}
		catch(Exception e) {
			logError("Failed to verify value for " + columnName + " is " + value + " - "
					+ e.getMessage());
			return false;
		}	
	}
	
	/** This method is used find and click on the Time column of Records table 
	 * to set Descending order
	 * @author hingorani_a
	 * @return boolean This returns true if the Time column is clicked
	 */
	public boolean clickTimeColumnToPerformDescending() {
		try {
			controlActions.WaitforelementToBeClickable(RecTblTimeColumn);
			controlActions.clickElement(RecTblTimeColumn);;
			// In order to wait
			controlActions.WaitforelementToBeClickable(RecTblTimeColumn);
			logInfo("Clicked on Time column of Records table to set Descending order");
			return true;
		}catch(Exception e) {
			logError("Failed to click on Time column of Records table to set Descending order" 
					+ e.getMessage());
			return false;
		}
	}
	
	public static class RecordFieldNames {
		public static final String RECORD = "Record";
		public static final String DATE = "Date";
		public static final String TIME = "Time";
		public static final String TASK = "Task";
		public static final String LOCATION = "Location";
		public static final String FORM = "Form";
		public static final String RESOURCE = "Resource";
		public static final String USER = "User";
		public static final String CHART = "Chart";
		public static final String SIGNED = "Signed";
		public static final String HIST = "Hist";
		public static final String IMG = "Img";
	}
}
