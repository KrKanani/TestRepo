package com.project.safetychain.webapp.pageobjects;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.project.utilities.ControlActions;

public class ProgramViewerPage extends CommonPage {

	WebDriver driver;
	WebDriverWait wait;
	ControlActions controlActions;

	public ProgramViewerPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 40);
		controlActions = new ControlActions(driver);
	}

	/**
	 * Page Objects
	 */

	@FindBy(id = ProgramViewerPageLocators.PRGRMS_RECORD_ALL_CHK)
	public WebElement PrgrmsRecordAllChk;

	@FindBy(id = ProgramViewerPageLocators.PROGRAMS_RCRDS_VIEW_BTN)
	public WebElement PrgrmsRecordViewBtn;

	@FindBy(xpath = ProgramViewerPageLocators.PROGRAM_NAME_ON_DDL)
	public WebElement ProgramNameOnDdl;

	@FindBy(xpath = ProgramViewerPageLocators.DOCUMENT_LST)
	public List<WebElement> DocumentList;

	@FindBy(xpath = ProgramViewerPageLocators.PROGRAMS_FORMS_TAB)
	public WebElement ProgramsFormsTab;

	@FindBy(xpath = ProgramViewerPageLocators.VIEW_DETAILS_OPTN)
	public WebElement ViewDetailsOptn;

	@FindBy(xpath = ProgramViewerPageLocators.VIEW_PROGRAM_NAME)
	public WebElement ViewProgramName;

	@FindBy(xpath = ProgramViewerPageLocators.VIEW_PRGRM_DETAILS)
	public WebElement ViewPrgrmDetails;

	@FindBy(xpath = ProgramViewerPageLocators.PROGRAMS_RECORDS_TAB)
	public WebElement ProgramsRecordsTab;

	@FindBy(xpath = ProgramViewerPageLocators.PROGRAMS_DOCUMENTS_TAB)
	public WebElement ProgramsDocumentsTab;

	@FindBy(xpath = ProgramViewerPageLocators.FILTER_TXT)
	public List<WebElement> FilterTxt;

	@FindBy(xpath = ProgramViewerPageLocators.FILTER_BTN)
	public List<WebElement> FilterBtn;
	
	@FindBy(xpath = ProgramViewerPageLocators.DOCS_COLUMN_NAMES_TBL)
	public List<WebElement> DocsColumnNamesTbl;
	
	@FindBy(xpath = ProgramViewerPageLocators.FORMS_COLUMN_NAMES_TBL)
	public List<WebElement> FormsColumnNamesTbl;
	
	@FindBy(xpath = ProgramViewerPageLocators.RECS_COLUMN_NAMES_TBL)
	public List<WebElement> RecsColumnNamesTbl;
	@FindBy(xpath = ProgramViewerPageLocators.FORM_CLOSE)
	public WebElement FormClose;
	@FindBy(xpath = ProgramViewerPageLocators.RECORDS_IN_HEADER)
	public WebElement RecordsInHeaders;

	@FindBy(id =ProgramViewerPageLocators.SEARCH_TEXT)
	public WebElement SearchText;
	/**
	 * Functions
	 */

	/**
	 * This method is used to set Program Tabs like Documents, Forms or Records
	 * @author hingorani_a
	 * @param tabName Use Class PGVIEWTABS to set tab name
	 * @return boolean This returns true if Program tab is clicked successfully.
	 */
	public boolean clickProgramViewTab(String tabName) {
		try {
			switch(tabName) {
			case PGVIEWTABS.DOCUMENTS:
				controlActions.perform_ClickWithJavaScriptExecutor(ProgramsDocumentsTab);
				Sync();
				logInfo("Clicked on Programs > Documents tab");
				return true;

			case PGVIEWTABS.FORMS:
				controlActions.perform_ClickWithJavaScriptExecutor(ProgramsFormsTab);
				Sync();
				logInfo("Clicked on Programs > Forms tab");
				return true;

			case PGVIEWTABS.RECORDS:
				controlActions.perform_ClickWithJavaScriptExecutor(ProgramsRecordsTab);
				Sync();
				logInfo("Clicked on Programs > Records tab");
				return true;

			default:
				logError("Invalid Option for tab - " + tabName);
				return false;
			}
		}
		catch (Exception e) {
			logError("Failed to click on Program tab - " + tabName + " - " + e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to Select Program
	 * @author hingorani_a
	 * @param FormName to be Selected under Program
	 * @return boolean This returns true if Forms has Added Successfully.
	 */
	public boolean selectPrograms(String programName) {
		try {
			String selectedPg = ProgramNameOnDdl.getText();
			logInfo("Selected Program : " + selectedPg);

			if (selectedPg.equals(programName)) {
				logInfo("Program already been selected - " + programName);
				return true;
			} 
			else {
				String select = "li[class='k-item ng-scope'][role='option']";
				controlActions.JSSetValueFromList(driver, select, programName);
				Sync();
				logInfo("Program set to " + programName);
				return true;
			}
		} 
		catch (Exception e) {
			logError("Failed to select Program : " + programName + " - " + e.getMessage());
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
	public int getColumnIndex(String tabName, String columnName) {
		int columnIndex = 0; 
		List<WebElement> ColumnNamesTbl = null;
		try {
			switch(tabName) {
			case PGVIEWTABS.DOCUMENTS:
				ColumnNamesTbl = DocsColumnNamesTbl;
				break;

			case PGVIEWTABS.FORMS:
				ColumnNamesTbl = FormsColumnNamesTbl;
				break;

			case PGVIEWTABS.RECORDS:
				ColumnNamesTbl = RecsColumnNamesTbl;
				break;

			default:
				logError("Invalid Option for tab - " + tabName);
				break;
			}
			
			for(WebElement column : ColumnNamesTbl) {
				if(controlActions.perform_CheckIfElementTextContains(column, columnName)) 
				{
					columnIndex++;
					break;
				}
				else if (controlActions.perform_CheckIfElementTextEquals(column, "")) {
					columnIndex++;
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
	 * This method is used to open settings popup and then apply settings
	 *  like Sorting, Filtering on column
	 * @author hingorani_a
	 * @param columnName Use Class PROGCOLUMNHEADER to set value for column name
	 * @param settingName Use Class PROGCOLUMNSETTING to set value for column name
	 * @param settingValue If you want to use Filter Setting, provide the text
	 * to be used for filter else for other settings keep it as null.
	 * @return boolean This returns true after applying setting
	 */
	public boolean applySettingsForColumn(String settingName, String settingValue) {
		List<WebElement> PopupOptionMnu = null;
		try {
			Sync();
			PopupOptionMnu = controlActions.perform_GetListOfElementsByXPath(ProgramViewerPageLocators.POPUP_OPTION_MNU, 
					"POPUPOPTION",settingName);

			switch(settingName) {
			case PGVIEWCOLSETTING.SORTASCENDING:
			case PGVIEWCOLSETTING.SORTDESCENDING:
				for(WebElement option : PopupOptionMnu) {
					if(controlActions.isElementDisplay(option)) {
						controlActions.WaitforelementToBeClickable(option);
						controlActions.clickOnElement(option);
						logInfo("Applied " + settingName + " to column");
						return true;
					}
				}
				return false;

			case PGVIEWCOLSETTING.COLUMNS:
				// yet to code
				logInfo("No code for Setting - " + settingName);
				return true;

			case PGVIEWCOLSETTING.FILTER:
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
	 * This method is used to click on Settings dropdown link for a column
	 * @author hingorani_a
	 * @param columnName Use Class PROGCOLUMNHEADER to set value for column name
	 * @return boolean This returns true once we click on Settings dropdown
	 */
	public boolean clickColumnDropDown(String columnName) {
		WebElement ColumnSettingsLnk = null;
		try {
			ColumnSettingsLnk = controlActions.perform_GetElementByXPath(ProgramViewerPageLocators.COLUMN_SETTINGS_LNK, 
					"COLUMNNAME", columnName);
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
	 * This method is used to open settings popup and then apply settings
	 *  like Sorting, Filtering on column
	 * @author hingorani_a
	 * @param columnName Use Class PROGCOLUMNHEADER to set value for column name
	 * @param settingName Use Class PROGCOLUMNSETTING to set value for column name
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
	 * This method is used to verify documents in Program
	 * @author hingorani_a
	 * @param docsList Where, HashMap's key is Document Type name and 
	 * value is List of Document/File names to verify
	 * @return boolean This returns true if documents are verified successfully
	 */
	public boolean verifyDocumentsFromPrograms(HashMap<String, List<String>> docsList) {
		WebElement DocsGridData = null;
		int docsCount = 0, docsListCount = 0;
		try {
			
			if(!clickProgramViewTab(PGVIEWTABS.DOCUMENTS)) {
				return false;
			}
			
			int docNameIndex = getColumnIndex(PGVIEWTABS.DOCUMENTS, PGVIEWERCOLHEADER.DOCUMENTNAME);
			if(docNameIndex == 0) {
				logError("Failed to get correct column index : " + docNameIndex + " for column " + PGVIEWERCOLHEADER.DOCUMENTNAME);
				return false;
			}

			int docTypeIndex = getColumnIndex(PGVIEWTABS.DOCUMENTS, PGVIEWERCOLHEADER.DOCUMENTTYPE);
			if(docTypeIndex == 0) {
				logError("Failed to get correct column index : " + docTypeIndex + " for column " + PGVIEWERCOLHEADER.DOCUMENTTYPE);
				return false;
			}

			for (Map.Entry<String, List<String>> entry : docsList.entrySet()) {
				String docType = entry.getKey();
				List<String> docNames = entry.getValue();

				docsListCount = docsListCount + docNames.size();

				for(String name : docNames) {
					if(!openAndApplySettingsForColumn(PGVIEWERCOLHEADER.DOCUMENTNAME, PGVIEWCOLSETTING.FILTER, name)) {
						return false;
					}

					DocsGridData = controlActions.perform_GetElementByXPath(ProgramViewerPageLocators.DOCS_GRID_DATA, 
							"COLUMNINDEXNO", Integer.toString(docNameIndex));
					if(controlActions.perform_CheckIfElementTextEquals(DocsGridData, name)) {
						logInfo("Verified name of document : " + name);

						DocsGridData = controlActions.perform_GetElementByXPath(ProgramViewerPageLocators.DOCS_GRID_DATA, 
								"COLUMNINDEXNO", Integer.toString(docTypeIndex));
						if(controlActions.perform_CheckIfElementTextEquals(DocsGridData, docType)) {
							logInfo("Verified name of document type : " + docType);
							docsCount++;
						}
					}
				}
			}


			if (docsCount == docsListCount) {
				logInfo("All the selected Documents are present under program");
				return true;
			} else {
				logError("All the selected Documents are not present under program");
				return false;
			}

		} 
		catch (Exception e) {
			logError("Verification of the Documents selected under programs failed" + e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to verify Forms in Program
	 * @author hingorani_a
	 * @param formsNameLst List of Form's name to be verified under Program
	 * @return boolean This returns true if forms are verified Successfully.
	 */
	public boolean verifyFormsFromPrograms(List<String> formsNameLst) {
		WebElement FormsGridData = null;
		int formsCount = 0;
		try {

			if(!clickProgramViewTab(PGVIEWTABS.FORMS)) {
				return false;
			}
			
			int formNameIndex = getColumnIndex(PGVIEWTABS.FORMS,PGVIEWERCOLHEADER.FORMNAME);
			if(formNameIndex == 0) {
				logError("Failed to get correct column index : " + formNameIndex + " for column " + PGVIEWERCOLHEADER.FORMNAME);
				return false;
			}

			for (String form : formsNameLst) {
				if(!openAndApplySettingsForColumn(PGVIEWERCOLHEADER.FORMNAME, PGVIEWCOLSETTING.FILTER, form)) {
					return false;
				}

				FormsGridData = controlActions.perform_GetElementByXPath(ProgramViewerPageLocators.FORMS_GRID_DATA, 
						"COLUMNINDEXNO", Integer.toString(formNameIndex));
				if(controlActions.perform_CheckIfElementTextEquals(FormsGridData, form)) {
					logInfo("Verified name of form : " + form);
					formsCount++;
				}
			}

			if (formsCount == formsNameLst.size()) {
				logInfo("All the selected Forms are present under program");
				return true;
			} else {
				logError("All the selected Forms are not present under program");
				return false;
			}

		}
		catch (Exception e) {
			logError("Verification of the Documents selected under programs failed" + e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to verify Details Tab from Program
	 * @author hingorani_a
	 * @param description to be verified under Program
	 * @return boolean This returns true if Program Details is verified successfully.
	 */
	public boolean verifyProgramDetailsTabFromPrograms(String description) {
		try {
			controlActions.perform_ClickWithJavaScriptExecutor(ViewDetailsOptn);
			Sync();

			String ActualProgramElement = ViewProgramName.getText();
			String ActualProgramDesc = ViewPrgrmDetails.getText();

			if (ActualProgramDesc.equals(description)) {
				logInfo("Program Description name has verified sucessfully: " + ActualProgramElement);
				return true;
			} else {
				logError("Program Description Verification Failed: " + ActualProgramElement);
				return false;
			}
		} 
		catch (Exception e) {
			logError("Verification of the Details Tab under programs failed" + e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to Verify Records under Program
	 * @author hingorani_a
	 * @param formsNameLst to be verified under Program
	 * @return boolean This returns true if Records has verified Successfully.
	 */
	public boolean verifyRecordsFromPrograms(List<String> formsNameLst) {
		WebElement RecordsGridData = null;
		int recCount = 0;
		try {
			
			if(!clickProgramViewTab(PGVIEWTABS.RECORDS)) {
				return false;
			}
			
			int recNameIndex = getColumnIndex(PGVIEWTABS.RECORDS, PGVIEWERCOLHEADER.FORMNAME);
			if(recNameIndex == 0) {
				logError("Failed to get correct column index : " + recNameIndex + " for column " + PGVIEWERCOLHEADER.FORMNAME);
				return false;
			}

			for (String form : formsNameLst) {
				if(!openAndApplySettingsForColumn(PGVIEWERCOLHEADER.FORMNAME, PGVIEWCOLSETTING.FILTER, form)) {
					return false;
				}

				RecordsGridData = controlActions.perform_GetElementByXPath(ProgramViewerPageLocators.RECORDS_GRID_DATA, 
						"COLUMNINDEXNO", Integer.toString(recNameIndex));
				if(controlActions.perform_CheckIfElementTextEquals(RecordsGridData, form)) {
					logInfo("Verified name of form : " + form);
					recCount++;
				}
			}

			if (recCount == formsNameLst.size()) {
				logInfo("All " + recCount + " selected records are present under program");
				return true;
			} else {
				logError("All " + recCount + " selected records are not present under program");
				return false;
			}
		} catch (Exception e) {
			logError("Verification of the Documents selected under programs failed" + e.getMessage());
			return false;
		}
	}
	
	/**
	 * This method is used to select all the listed record to open in Viewer
	 * @author hingorani_a
	 * @param none
	 * @return boolean This returns true if Records are open in Viewer Successfully.
	 */
	public FSQABrowserRecordsPage selectRecordsForViewer() {
		try {
			controlActions.perform_ClickWithJavaScriptExecutor(PrgrmsRecordAllChk);
			Sync();
			controlActions.perform_ClickWithJavaScriptExecutor(PrgrmsRecordViewBtn);
			Sync();
			return new FSQABrowserRecordsPage(driver);

		} catch (Exception e) {
			logError("Verification of the Documents selected under programs failed" + e.getMessage());
			FSQABrowserRecordsPage frp = new FSQABrowserRecordsPage(driver);
			frp.error = true;
			return frp;
		}
	}
	/**
	 * This 'openForm' method is used open the form
	 * @author jadhav_akan
	 * @param formName is the name of the form that will opened
	 * @boolean will return true when the form gets opened
	 */

	public boolean openForm(String formName) {
		try {
			WebElement openForm = controlActions.perform_GetElementByXPath(ProgramViewerPageLocators.FORM_SELECTION, "FORM_NAME", formName);
			controlActions.doubleClick(openForm);
			threadsleep(4000);
			WebElement verifyOpenedForm = controlActions.perform_GetElementByXPath(ProgramViewerPageLocators.FORM_OPENED, "FORM_NAME", formName);
			if(!controlActions.isElementDisplayedOnPage(verifyOpenedForm)) {
				logError("Failed to Verify opened form");
				return false;
			}
			logInfo("Form is opened");
			return true;
		}catch(Exception e) {
			logError("Failed to select & open the form - "+e.getMessage());
			return false;
		}	
	}
	/**
	 * This 'closeForm' method is used open the form
	 * @author jadhav_akan
	 * @boolean will return true when the form gets opened
	 */

	public boolean closeForm() {
		try {
			controlActions.clickElement(FormClose);
			logInfo("Clicked on close");
			return true;
			}
			catch(Exception e) {
				logError("Failed to click on Close "+ e.getMessage());
				return false;
			}
	}
	/**
	 * This method is used to verify Form in Program
	 * @author jadhav_akan
	 * @param formName name of the form to be verified under Program
	 * @return boolean This returns true if form is not displayed.
	 */
	public boolean verifyFormisNotDisplayedFromPrograms(String formName) {
		try {

			if(!clickProgramViewTab(PGVIEWTABS.FORMS)) {
				return false;
			}
			
				if(!openAndApplySettingsForColumn(PGVIEWERCOLHEADER.FORMNAME, PGVIEWCOLSETTING.FILTER, formName)) {
					return false;
				}
			
			WebElement formSelect = controlActions.perform_GetElementByXPath(ProgramViewerPageLocators.FORM_SELECTION,"FORM_NAME",formName);
			if(controlActions.isElementDisplayedOnPage(formSelect)){
				logError("Form - "+formName+" is present");
				return false;
			}
			logInfo("Verified the form - "+formName+" is not present under Program");
			return true;
		}
		catch (Exception e) {
			logError("Failed to verify form -  "+formName+" is not present " + e.getMessage());
			return false;
		}
	}
	/**
	 * This method is used open the records tab from header of Program viewer
	 * @author jadhav_akan
	 * @boolean will return true if click on record
	 */

	public boolean clickOnRecords() {
		try {
			controlActions.clickElement(RecordsInHeaders);
			logInfo("Clicked on Records");
			return true;
			}
			catch(Exception e) {
				logError("Failed to click on Records "+ e.getMessage());
				return false;
			}
	}
	/**
	 * This 'openRecord' method is used open the record
	 * @author jadhav_akan
	 * @param formName is the name of the form that will opened
	 * @boolean will return true when the form gets opened
	 */

	public boolean openRecord(String formName) {
		try {
			WebElement openRecord = controlActions.perform_GetElementByXPath(ProgramViewerPageLocators.RECORD_SELECTION, "FORM_NAME", formName);
			controlActions.doubleClick(openRecord);
			threadsleep(4000);
			
			logInfo("Record is opened "+formName);
			return true;
		}catch(Exception e) {
			logError("Failed to select & open the Record - " +formName+ ""+e.getMessage());
			return false;
		}	
	}
	/**
	 * This method is used to verify Record in Program
	 * @author jadhav_akan
	 * @param formName name of the form to be verified under Program
	 * @return boolean This returns true if Record is not present/displayed.
	 */
	public boolean verifyRecordIsNotDisplayedFromPrograms(String formName) {
		try {

			if(!clickProgramViewTab(PGVIEWTABS.RECORDS)) {
				return false;
			}
			
				if(!openAndApplySettingsForColumn(PGVIEWERCOLHEADER.FORMNAME, PGVIEWCOLSETTING.FILTER, formName)) {
					return false;
				}
			
			WebElement record = controlActions.perform_GetElementByXPath(ProgramViewerPageLocators.RECORD_SELECTION,"FORM_NAME",formName);
			if(controlActions.isElementDisplayedOnPage(record)){
				logError("Record - "+formName+" is present");
				return false;
			}
			logInfo("Verified the Record - "+formName+" is not present under Program");
			return true;
		}
		catch (Exception e) {
			logError("Failed to verify Record -  "+formName+" is not present " + e.getMessage());
			return false;
		}
	}
	/**
	 * This method is used to verify Record in Program
	 * @author jadhav_akan
	 * @param formName name of the form to be verified under Program
	 * @return boolean This returns true if Record is present/displayed.
	 */
	public boolean verifyRecordIsDisplayedFromPrograms(String formName) {
		try {

			if(!clickProgramViewTab(PGVIEWTABS.RECORDS)) {
				return false;
			}
			
				if(!openAndApplySettingsForColumn(PGVIEWERCOLHEADER.FORMNAME, PGVIEWCOLSETTING.FILTER, formName)) {
					return false;
				}
			
			WebElement record = controlActions.perform_GetElementByXPath(ProgramViewerPageLocators.RECORD_SELECTION,"FORM_NAME",formName);
			if(controlActions.isElementDisplayedOnPage(record)){
				logInfo("Verified Record - "+formName+" is present");
				return true;
			}
			
			logError("Record - "+formName+" is not present under Program");
			return false;
		}
		catch (Exception e) {
			logError("Failed to verify Record -  "+formName+" is present " + e.getMessage());
			return false;
		}
	}
		/**
		 * This method is used to search Program Element
		 * @author jadhav_akan
		 * @param toSearch : Name of the Program Element need to be search 
		 * @return boolean This returns true if given Program Element is searched and selected
		 */
		public boolean searchSelectProgramElement(String toSearch) {
			try {
				
				WebElement mainfilter = SearchText;
				controlActions.sendKeys(mainfilter, toSearch);
				Sync();
				WebElement search = controlActions.perform_GetElementByXPath(
						ProgramViewerPageLocators.PE_SEARCH_RESULT, "PENAME", toSearch);
				controlActions.clickElement(search);
				logInfo("Searched and Selected Program Element - > " + toSearch);
			    return true;
			    }
			catch(Exception e) {
				logError("Failed to search and select Program Element - > "+toSearch+" "+ e.getMessage());
				return false;
			}
	}
	
	public static class PGVIEWERCOLHEADER{
		public static final String DOCUMENTNAME = "Document Name";
		public static final String DOCUMENTTYPE = "Document Type";	
		public static final String MODIFIEDBY = "Modified By";	
		public static final String MODIFIEDON = "Modified On";	
		public static final String VERISION = "Version";	
		public static final String FORMNAME = "Form Name";	
		public static final String FORMTYPE = "Form Type";	
		public static final String RESOURCE = "Resource";	
	}

	public static class PGVIEWCOLSETTING{
		public static final String SORTASCENDING = "Sort Ascending";
		public static final String SORTDESCENDING = "Sort Descending";
		public static final String COLUMNS = "Columns";
		public static final String FILTER = "Filter";
	}

	public static class PGVIEWTABS{
		public static final String DOCUMENTS = "Documents";
		public static final String FORMS = "Forms";
		public static final String RECORDS = "Records";
	}

}
