package com.project.safetychain.webapp.pageobjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.project.utilities.ControlActions;

public class ProgramDesignerPage extends CommonPage {

	WebDriver driver;
	WebDriverWait wait;
	ControlActions controlActions;

	public ProgramDesignerPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 40);
		controlActions = new ControlActions(driver);
	}

	/**
	 * Page Objects
	 */
	
	@FindBy(xpath = ProgramDesignerPageLocators.FRMS_SELECT_ALL_CHK)
	public WebElement FrmsSelectAllChk;
	
	@FindBy(xpath = ProgramDesignerPageLocators.ADD_PROGRAM_LNK)
	public WebElement AddProgramLnk;

	@FindBy(xpath = ProgramDesignerPageLocators.ADD_PROGRAM_TXT)
	public WebElement AddProgramTxt;

	@FindBy(xpath = ProgramDesignerPageLocators.CREATE_ELEMENT_OPTN)
	public WebElement CreateElementOptn;

	@FindBy(xpath = ProgramDesignerPageLocators.CREATE_PROGRAM_ELEMENT)
	public WebElement CreateProgramElement;

	@FindBy(xpath = ProgramDesignerPageLocators.CREATE_PROGRAM_HEADER)
	public WebElement CreateProgramHeader;

	@FindBy(xpath = ProgramDesignerPageLocators.PROGRAM_ELEMENT_TXT)
	public WebElement ProgramElementTxt;

	@FindBy(id = ProgramDesignerPageLocators.SEARCH_PROGRAM_TXT)
	public WebElement SearchProgramTxt;

	@FindBy(xpath = ProgramDesignerPageLocators.SEARCH_PRGM_LST)
	public WebElement SearchPrgmLst;

	@FindBy(xpath = ProgramDesignerPageLocators.DETAILS_TAB_FRA)
	public WebElement DetailsTabFra;

	@FindBy(xpath = ProgramDesignerPageLocators.DETAILS_TAB_TXT)
	public WebElement DetailsTabTxt;

	@FindBy(xpath = ProgramDesignerPageLocators.DETAILS_TAB_SAVE_BTN)
	public WebElement DetailsTabSaveBtn;

	@FindBy(xpath = ProgramDesignerPageLocators.DOCUMENTS_TAB)
	public WebElement DocumentsTab;

	@FindBy(id = ProgramDesignerPageLocators.DOCUMENT_TYPE_SAVE_BTN)
	public WebElement DocumentTypeSaveBtn;

	@FindBy(xpath = ProgramDesignerPageLocators.DOCUMENTS_SUB_TAB)
	public WebElement DocumentsSubTab;

	@FindBy(xpath = ProgramDesignerPageLocators.DOCUMENTS_SELECT_NEW_TAB)
	public WebElement DocumentsSelectNewTab;

	@FindBy(xpath = ProgramDesignerPageLocators.DOCUMENTS_SAVE_BTN)
	public WebElement DocumentsSaveBtn;

	@FindBy(xpath = ProgramDesignerPageLocators.FORMS_TAB)
	public WebElement FormsTab;

	@FindBy(xpath = ProgramDesignerPageLocators.FORMS_SAVE_BTN)
	public WebElement FormsSaveBtn;

	@FindBy(xpath = ProgramDesignerPageLocators.POPUP_CONFRM_BTN)
	public WebElement PopUpConfrmBtn;

	@FindBy(xpath = ProgramDesignerPageLocators.SELCTD_DOC_LST)
	public List<WebElement> SelectDocLst;

	@FindBy(xpath = ProgramDesignerPageLocators.FILTER_TXT)
	public List<WebElement> FilterTxt;

	@FindBy(xpath = ProgramDesignerPageLocators.FILTER_BTN)
	public List<WebElement> FilterBtn;
 
	@FindBy(xpath = ProgramDesignerPageLocators.FRMS_SEARCH_TXT)
	public WebElement FrmsSearchTxt;
	
	@FindBy(xpath = ProgramDesignerPageLocators.FRMS_SEARCH_BTN)
	public WebElement FrmsSearchBtn;

	/**
	 * Functions
	 */

	/**
	 * This method is used to set Program Tabs like Documents, Forms or Document Type
	 * @author hingorani_a
	 * @param tabName Use Class PGDSGNTABS to set tab name
	 * @return boolean This returns true if Program tab is clicked successfully.
	 */
	public boolean clickProgramDesignerTab(String tabName) {
		try {
			switch(tabName) {
			case PGDSGNTABS.DOCUMENTTYPE:
				controlActions.perform_ClickWithJavaScriptExecutor(DocumentsTab);
				Sync();
				logInfo("Clicked on Program Designer > Document Type tab");
				return true;

			case PGDSGNTABS.DOCUMENTS:
				controlActions.perform_ClickWithJavaScriptExecutor(DocumentsSubTab);
				Sync();
				logInfo("Clicked on Program Designer > Documents tab");
				return true;

			case PGDSGNTABS.FORMS:
				controlActions.perform_ClickWithJavaScriptExecutor(FormsTab);
				Sync();
				logInfo("Clicked on Program Designer > Forms tab");
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
	 * This method is used to set Program name
	 * @author hingorani_a
	 * @param pgName The Program name
	 * @return boolean This returns true if Program name is set successfully.
	 */
	public boolean setProgramName(String pgName) {
		try {
			controlActions.WaitforelementToBeClickable(AddProgramLnk);
			controlActions.clickOnElement(AddProgramLnk);
			controlActions.sendKeys(AddProgramTxt, pgName);
			controlActions.actionEnter();
			Sync();
			logInfo("Programs named entered: " + pgName);
			return true;
		} catch (Exception e) {
			logError("Failed to enter Program name - " + pgName + " - " + e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to Add Program Element name
	 * @author hingorani_a
	 * @param pgeName The program Element name
	 * @return boolean This returns true if Program Element name is set
	 * successfully.
	 */
	public boolean addProgramElement(String pgeEleName) {
		try {
			controlActions.perform_ClickWithJavaScriptExecutor(CreateElementOptn);
			Sync();
			controlActions.perform_ClickWithJavaScriptExecutor(CreateProgramElement);
			controlActions.sendKeys(ProgramElementTxt, pgeEleName);
			controlActions.actionEnter();
			Sync();
			logInfo("Program Element name entered: " + pgeEleName);
			return true;
		} catch (Exception e) {
			logError("Failed to enter Program Element name - " + pgeEleName + " - " + e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to Add Program Header
	 * @author hingorani_a
	 * @param phName The program Header name
	 * @return boolean This returns true if Program header name is set successfully.
	 */
	public boolean addProgramHeader(String phName) {
		try {
			controlActions.perform_ClickWithJavaScriptExecutor(CreateElementOptn);
			Sync();
			controlActions.perform_ClickWithJavaScriptExecutor(CreateProgramHeader);
			controlActions.sendKeys(ProgramElementTxt, phName);
			controlActions.actionEnter();
			Sync();
			logInfo("Program Header name entered: " + phName);
			return true;
		} catch (Exception e) {
			logError("Failed to enter Program Header name - " + phName + " - " + e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to search a program
	 * @author hingorani_a
	 * @param programName The Program name
	 * @return boolean This returns true if Program is searched successfully.
	 */
	public boolean searchProgram(String programName) {
		try {
			SearchProgramTxt.clear();
			controlActions.sendKeys(SearchProgramTxt, programName);
			if(controlActions.isElementDisplay(SearchPrgmLst)) {
				controlActions.perform_ClickWithJavaScriptExecutor(SearchPrgmLst);
				Sync();
				return true;
			}
			logInfo("Program " + programName + " could not be found");
			return false;
		} 
		catch (Exception e) {
			logError("Failed to search for program : " + programName + " - " 
					+ e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to verify Program Element or Header
	 * @author hingorani_a
	 * @param pep An object of Class ProgramEleParams, in order to set
	 * different fields like Name, Element, Header or Type for a program.
	 * @return boolean This returns true if program element/Header is Verified
	 * successfully.
	 */
	public boolean verifyProgramElementHeader(ProgramEleParams pep) {
		WebElement SelctdProgEleName = null;
		WebElement ExpandProgNode = null;
		String elementToVerify = null;
		try {
			
			if(!searchProgram(pep.ProgramName)) {
				return false;
			}

			switch(pep.Type.toUpperCase()) {
			case PROGRAMFIELD.PROGRAM:
				SelctdProgEleName = controlActions.perform_GetElementByXPath(ProgramDesignerPageLocators.SELCTD_PROG_ELE_NAME, 
						"ELEMENTNAME", pep.ProgramName);
				elementToVerify = pep.ProgramName;
				break;

			case PROGRAMFIELD.HEADER:
				SelctdProgEleName = controlActions.perform_GetElementByXPath(ProgramDesignerPageLocators.SELCTD_PROG_ELE_NAME, 
						"ELEMENTNAME", pep.HeaderName);
				elementToVerify = pep.HeaderName;
				break;

			case PROGRAMFIELD.ELEMENT: 
				ExpandProgNode = controlActions.perform_GetElementByXPath(ProgramDesignerPageLocators.EXPAND_PROG_NODE, 
						"ELEMENTNAME", pep.HeaderName);
				controlActions.clickOnElement(ExpandProgNode);
				Sync();
				logInfo("Header " + pep.HeaderName + "'s expand has clicked");

				SelctdProgEleName = controlActions.perform_GetElementByXPath(ProgramDesignerPageLocators.SELCTD_PROG_ELE_NAME, 
						"ELEMENTNAME", pep.ElementName);
				elementToVerify = pep.ElementName;
				break;

			default:
				logError("Invalid option for type of element - " + pep.Type);
				return false;
			}

			if (SelctdProgEleName.isDisplayed()) {
				logInfo("Valid Program element " + elementToVerify + " is present under Program designer");
				return true;
			} else {
				return false;
			}
		} 
		catch (Exception e) {
			logError("Failed to verify program Element/Header : " + elementToVerify + " - " + e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to set description of Details Tab
	 * @author hingorani_a
	 * @param description Data to be set as Description
	 * @return boolean This returns true if description is set successfully.
	 */
	public boolean setDescription(String description) {
		try {
			controlActions.switchToframeByIFrameElement(DetailsTabFra);
			controlActions.sendKeys(DetailsTabTxt, description);
			logInfo("Description has entered on Details Tab: " + description);
			Sync();
			controlActions.switchToDefault();
			controlActions.WaitforelementToBeClickable(DetailsTabSaveBtn);
			controlActions.clickOnElement(DetailsTabSaveBtn);
			Sync();
			logInfo("Clicked on save button ");
			return true;
		} catch (Exception e) {
			logError("Failed to enter Description - " + description + " - " + e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to select Document Types
	 * @author hingorani_a
	 * @param documentTypeLst Document Types to be selected from Documents Tab of Designer
	 * @return boolean This returns true if Doc types are selected successfully.
	 */
	public boolean selectDocumentType(List<String> documentTypeLst) {
		WebElement DocumentTypeChk = null;
		try {
			
			if(!clickProgramDesignerTab(PGDSGNTABS.DOCUMENTTYPE)) {
				return false;
			}
			
			Sync();
			int DocSize = documentTypeLst.size();
			for (int i = 0; i < DocSize; i++) {
				DocumentTypeChk = controlActions.perform_GetElementByXPath(ProgramDesignerPageLocators.DOCUMENTS_TYPE_CHK,
						"DOCUMENTTYPE", documentTypeLst.get(i).toString());
				controlActions.clickOnElement(DocumentTypeChk);
				Sync();
				logInfo("Selected Documents Type: " + documentTypeLst.get(i).toString());
			}

			controlActions.clickOnElement(DocumentTypeSaveBtn);
			Sync();
			logInfo("Clicked on Documents Type Save Button");
			return true;
		} 
		catch (Exception e) {
			logError("Failed to select Documents Type: " + documentTypeLst + " - " + e.getMessage());
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
	public boolean applySettingsForColumn(String settingName, String settingValue) {
		List<WebElement> PopupOptionMnu = null;
		try {
			Sync();
			PopupOptionMnu = controlActions.perform_GetListOfElementsByXPath(ProgramDesignerPageLocators.POPUP_OPTION_MNU, 
					"POPUPOPTION",settingName);

			switch(settingName) {
			case PROGCOLUMNSETTING.SORTASCENDING:
			case PROGCOLUMNSETTING.SORTDESCENDING:
				for(WebElement option : PopupOptionMnu) {
					if(controlActions.isElementDisplay(option)) {
						controlActions.WaitforelementToBeClickable(option);
						controlActions.clickOnElement(option);
						logInfo("Applied " + settingName + " to column");
						return true;
					}
				}
				return false;

			case PROGCOLUMNSETTING.COLUMNS:
				// yet to code
				logInfo("No code for Setting - " + settingName);
				return true;

			case PROGCOLUMNSETTING.FILTER:
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
				logError("Incorrect setting provided : " + settingValue);
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
			ColumnSettingsLnk = controlActions.perform_GetElementByXPath(ProgramDesignerPageLocators.COLUMN_SETTINGS_LNK, 
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
	 * This method is used to select and add Documents
	 * @author hingorani_a
	 * @param documentNameLst Documents to be added from Documents Tab
	 * @param docTypeName Documents to be added from the Document Type
	 * @return boolean This returns true if Documents are added successfully.
	 */
	public boolean selectAndSaveDocuments(List<String> documentNameLst, String docTypeName) {
		WebElement DocumentChk = null;
		try {
			
			if(!clickProgramDesignerTab(PGDSGNTABS.DOCUMENTS)) {
				return false;
			}
			
			controlActions.WaitforelementToBeClickable(DocumentsSelectNewTab);
			controlActions.clickOnElement(DocumentsSelectNewTab);
			Sync();
			if(!openAndApplySettingsForColumn(PROGCOLUMNHEADER.DOCUMENTTYPE, PROGCOLUMNSETTING.FILTER, docTypeName)) {
				return false;
			}
			for(String doc : documentNameLst) {
				DocumentChk = controlActions.perform_GetElementByXPath(ProgramDesignerPageLocators.SELECT_DOCUMENTS_CHK,
						"DOCUMENTNAME", doc);
				controlActions.clickOnElement(DocumentChk);
				Sync();
			}
			controlActions.clickOnElement(DocumentsSaveBtn);
			Sync();
			logInfo("Selected Documents : " + documentNameLst);
			return true;
		} 
		catch (Exception e) {
			logError("Failed to select Documents : " + documentNameLst + " - " + e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to select and add Forms
	 * @author hingorani_a
	 * @param formNamesLst List of Forms to be selected under Program
	 * @return boolean This returns true if Forms has Selected Successfully.
	 */
	public boolean selectForms(List<String> formNamesLst) {
		WebElement FormChk = null;
		try {
			
			if(!clickProgramDesignerTab(PGDSGNTABS.FORMS)) {
				return false;
			}
			
//			int FormSize = formNamesLst.size();
//			for (int i = 0; i < FormSize; i++) {
//				
//				FormChk = controlActions.perform_GetElementByXPath(ProgramDesignerPageLocators.FORMS_CHK, "FORMSNAME",
//						formNamesLst.get(i).toString());
//				Sync();
//				controlActions.clickOnElement(FormChk);
//			}
			
			for(String formNm : formNamesLst) {
				if(!searchFormName(formNm))
					return false;
				
				FormChk = controlActions.perform_GetElementByXPath(ProgramDesignerPageLocators.FORMS_CHK, 
						"FORMSNAME", formNm);
						
				controlActions.WaitforelementToBeClickable(FormChk);
				FormChk.click();
				
				controlActions.WaitforelementToBeClickable(FormsSaveBtn);
				FormsSaveBtn.click();
				Sync();
			}

//			controlActions.clickOnElement(FormsSaveBtn);
//			Sync();
			logInfo("Selected Forms : " + formNamesLst);
			return true;
		} 
		catch (Exception e) {
			logError("Failed to select Forms : " + formNamesLst + " - " + e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to verify Forms exists in Program
	 * @author hingorani_a
	 * @param formNamesLst List of Forms to be verified under a Program
	 * @return boolean This returns true if Forms are verified successfully.
	 */
	public boolean verifyFormsFromProgramDesigner(List<String> formNamesLst) {
		WebElement FormChk = null;
		Boolean isChecked = false;
		try {
			
			if(!clickProgramDesignerTab(PGDSGNTABS.FORMS)) {
				return false;
			}
			
			int FormSize = formNamesLst.size();
			for (int i = 0; i < FormSize; i++) {
				FormChk = controlActions.perform_GetElementByXPath(ProgramDesignerPageLocators.FORMS_CHK, "FORMSNAME",
						formNamesLst.get(i).toString());
				Sync();

				if (FormChk.isSelected()) {
					logInfo("Selected Forms is present under Program Designer  : " + formNamesLst);
					isChecked = true;
				} else {
					isChecked = false;
				}
			}
			return isChecked;

		} 
		catch (Exception e) {
			logError("Failed to select Forms : " + formNamesLst + " - " + e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to Verify Doc Type from Program Designer Forms
	 * @author hingorani_a
	 * @param docTypeLst Document type to be verified from Selected Doc Types
	 * @return boolean This returns true if Doc Type verified Successfully.
	 */
	public boolean verifyDocTypeFromProgramDesigner(List<String> docTypeLst) {
		WebElement DocChk = null;
		boolean isVerified = false;
		try {
			
			if(!clickProgramDesignerTab(PGDSGNTABS.DOCUMENTTYPE)) {
				return false;
			}
			
			int docSize = docTypeLst.size();
			for (int i = 0; i < docSize; i++) {
				DocChk = controlActions.perform_GetElementByXPath(ProgramDesignerPageLocators.DOCUMENTS_TYPE_CHK,
						"DOCUMENTTYPE", docTypeLst.get(i).toString());
				if (DocChk.isSelected()) {
					logInfo("Selected Doc Type is present under  : " + docTypeLst.get(i).toString());
					isVerified = true;
				} else {
					isVerified = false;
				}
			}
			return isVerified;
		} 
		catch (Exception e) {
			logError("Failed to select Forms : " + docTypeLst + " - " + e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to Verify Documents from Program Designer Forms
	 * @author hingorani_a
	 * @param documentList Documents to be verified under selected documents
	 * @return boolean This returns true if Documents verified Successfully.
	 */
	public boolean verifyDocumentsFromProgramDesigner(List<String> documentList) {
		WebElement SelctdDocsName = null;
		try {
			
			if(!clickProgramDesignerTab(PGDSGNTABS.DOCUMENTTYPE)) {
				return false;
			}
			
			if(!clickProgramDesignerTab(PGDSGNTABS.DOCUMENTS)) {
				return false;
			}
			
			int size = SelectDocLst.size();
			logInfo("Selected Document number : " + size);
			List<String> ActualDocList = new ArrayList<String>();
			for (int i = 1; i <= size; i++) {
				SelctdDocsName = controlActions.perform_GetElementByXPath(ProgramDesignerPageLocators.SELCTD_DOCS_NAME,
						"COUNT", Integer.toString(i));
				String docName = SelctdDocsName.getText();
				logInfo("DocName : " + docName);
				ActualDocList.add(docName);
			}

			if (ActualDocList.containsAll(documentList)) {
				logInfo("All the selected documents are present under Documents Tab of Program Designer : "
						+ ActualDocList);
				return true;
			} else {
				return false;
			}
		} 
		catch (Exception e) {
			logError("Failed to verify all selected documents from the Document Tab :  - " + e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to Select Program Element name
	 * @author hingorani_a
	 * @param pgEleName The program Element name
	 * @return boolean This returns true if Program Element name is selected
	 * successfully.
	 */
	public boolean selectProgramElementUnderProgram(String pgEleName) {
		WebElement PrgramChk = null;
		try {
			PrgramChk = controlActions.perform_GetElementByXPath(ProgramDesignerPageLocators.SELCTD_PROG_ELE_NAME,
					"ELEMENTNAME", pgEleName);
			if (PrgramChk.isDisplayed()) {
				controlActions.clickOnElement(PrgramChk);
				Sync();
				logInfo("Selected Program Element: " + pgEleName);
				return true;
			} else {
				return false;
			}
		} 
		catch (Exception e) {
			logError("Failed to enter Program Element name - " + pgEleName + " - " + e.getMessage());
			return false;
		}
	}


	/**
	 * This method is used to deselect Documents
	 * @author hingorani_a
	 * @param documentLsts List of documents to be deselected
	 * @return boolean This returns true if Documents are deselected successfully.
	 */
	public boolean deselectDocument(List<String> documentLsts) {
		WebElement SelctdDocName = null;
		WebElement RemvSelctdDoc = null;
		int isDeselected = 0;
		try {
			
			if(!clickProgramDesignerTab(PGDSGNTABS.DOCUMENTTYPE)) {
				return false;
			}
			
			if(!clickProgramDesignerTab(PGDSGNTABS.DOCUMENTS)) {
				return false;
			}
			
			int docSize = documentLsts.size();

			for(String docName : documentLsts) {

				SelctdDocName = controlActions.perform_GetElementByXPath(ProgramDesignerPageLocators.SELCTD_DOC_NAME,
						"DOCNAME", docName);
				controlActions.moveToElementAction(SelctdDocName);
				Sync();
				RemvSelctdDoc = controlActions.perform_GetElementByXPath(ProgramDesignerPageLocators.REMV_SELCTD_DOC,
						"DOCNAME", docName);
				controlActions.perform_ClickWithJavaScriptExecutor(RemvSelctdDoc);
				Sync();
				controlActions.clickOnElement(PopUpConfrmBtn);
				Sync();
				logInfo("Document has deleted Successfully : " + docName);
				isDeselected++;
			}

			if(isDeselected == docSize) {
				logInfo("Deselected the document(s) : " + documentLsts);
				return true;
			}
			else {
				logInfo("Failed to Deselect the document(s) : " + documentLsts);
				return false;
			}
		} 
		catch (Exception e) {
			logError("Failed to deselect Document : " + documentLsts + " - " + e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to deselect Doc Type
	 * @author hingorani_a
	 * @param docTypeLst Document Type list to be deselected under Program
	 * @return boolean This returns true if Doc Type has Deleted Successfully.
	 */
	public boolean deselectDocTypeFromProgramDesigner(List<String> docTypeLst) {
		WebElement DocChk = null;
		int isDeselected = 0;
		try {
			
			if(!clickProgramDesignerTab(PGDSGNTABS.DOCUMENTTYPE)) {
				return false;
			}
			
			Sync();
			int docTypeSize = docTypeLst.size();
			for (int i = 0; i < docTypeSize; i++) {
				DocChk = controlActions.perform_GetElementByXPath(ProgramDesignerPageLocators.DOCUMENTS_TYPE_CHK,
						"DOCUMENTTYPE", docTypeLst.get(i).toString());
				if (DocChk.isSelected()) {
					logInfo("Selected Doc Type is present under  : " + docTypeLst.get(i).toString());
					controlActions.clickOnElement(DocChk);
					Sync();
					controlActions.clickOnElement(DocumentTypeSaveBtn);
					Sync();
					logInfo("Clicked on Documents Type Save Button");
					isDeselected++;
				}
			}
			if(isDeselected == docTypeSize) {
				logInfo("Deselected the document type(s) : " + docTypeLst);
				return true;
			}
			else {
				logInfo("Failed to Deselect the document type(s) : " + docTypeLst);
				return false;
			}
		} 
		catch (Exception e) {
			logError("Failed to deselect Document Type : " + docTypeLst + " - " + e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to deselect Forms
	 * @author hingorani_a
	 * @param formNamesLst to be deleted under Program
	 * @return boolean This returns true if Forms has deleted Successfully.
	 */
	public boolean deselectFormsFromProgramDesigner(List<String> formNamesLst) {
		WebElement FormChk = null;
		int isDeselected = 0;
		try {
			
			if(!clickProgramDesignerTab(PGDSGNTABS.FORMS)) {
				return false;
			}
			
			Sync();
			int formSize = formNamesLst.size();
			for (int i = 0; i < formSize; i++) {
				FormChk = controlActions.perform_GetElementByXPath(ProgramDesignerPageLocators.FORMS_CHK, "FORMSNAME",
						formNamesLst.get(i).toString());
				Sync();

				if (FormChk.isSelected()) {
					logInfo("Selected Forms is present under Program Designer  : " + formNamesLst.get(i).toString());
					controlActions.clickOnElement(FormChk);
					Sync();
					controlActions.clickOnElement(FormsSaveBtn);
					Sync();
					isDeselected++;
				}
			}
			
			if(isDeselected == formSize) {
				logInfo("Deselected the form(s) : " + formNamesLst);
				return true;
			}
			else {
				logInfo("Failed to Deselect the form(s) : " + formNamesLst);
				return false;
			}
		} 
		catch (Exception e) {
			logError("Failed to deselect Forms : " + formNamesLst + " - " + e.getMessage());
			return false;
		}
	}
	
	/**
	 * This method is used to search for form
	 * @author hingorani_a
	 * @param formName The Form name
	 * @return boolean This returns true if Form name is searched successfully.
	 */
	public boolean searchFormName(String formName) {
		try {
			controlActions.WaitforelementToBeClickable(FrmsSearchTxt);
			FrmsSearchTxt.clear();
			FrmsSearchTxt.sendKeys(formName);
			
			FrmsSearchBtn.click();
			Sync();
			logInfo("Form named searched : " + formName);
			return true;
		} 
		catch (Exception e) {
			logError("Failed to search Form name - " + formName + " - " + e.getMessage());
			return false;
		}
	}
	/**
	 * This method is used to Add new Program Element name in Header
	 * @author jadhav_akan
	 * @param pgeName The program Element name
	 * @param headingName The program Heading name
	 * @return boolean This returns true if Program Element name is set
	 * successfully.
	 */
	public boolean addNewProgramElementInProgramHeader(String headingName,String pgeEleName) {
		try {
			
			WebElement selectProgramHeading = controlActions.perform_GetElementByXPath(ProgramDesignerPageLocators.HEADING_NAME,
					"HEADINGNAME", headingName);
			controlActions.action.moveToElement(selectProgramHeading).build().perform();
			controlActions.clickElement(selectProgramHeading);
			Sync();
			controlActions.perform_ClickWithJavaScriptExecutor(CreateElementOptn);
			Sync();
			controlActions.perform_ClickWithJavaScriptExecutor(CreateProgramElement);
			controlActions.sendKeys(ProgramElementTxt, pgeEleName);
			controlActions.actionEnter();
			Sync();
			logInfo("Program Element name entered: " + pgeEleName);
			return true;
		} catch (Exception e) {
			logError("Failed to enter Program Element name - " + pgeEleName + " - " + e.getMessage());
			return false;
		}
	}
	public static class ProgramEleParams{
		public String Type;
		public String ProgramName;
		public String HeaderName;
		public String ElementName;
	}

	public static class PROGRAMFIELD{
		public static final String PROGRAM = "PROGRAM";
		public static final String HEADER = "HEADER";
		public static final String ELEMENT = "ELEMENT";
	}

	public static class PROGCOLUMNHEADER{
		public static final String DOCUMENTNAME = "Document Name";
		public static final String DOCUMENTTYPE = "Document Type";	
	}

	public static class PROGCOLUMNSETTING{
		public static final String SORTASCENDING = "Sort Ascending";
		public static final String SORTDESCENDING = "Sort Descending";
		public static final String COLUMNS = "Columns";
		public static final String FILTER = "Filter";
	}
	
	public static class PGDSGNTABS{
		public static final String DOCUMENTTYPE = "Document Type";
		public static final String DOCUMENTS = "Documents";
		public static final String FORMS = "Forms";
	}
}
