package com.project.safetychain.webapp.pageobjects;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FORMRESOURCETYPES;
import com.project.safetychain.webapp.pageobjects.ManageResourcePage.SelectTAB;
import com.project.safetychain.webapp.testcases.TCG_SMK_DocumentManagement;
import com.project.utilities.ControlActions;

public class DocumentManagementPage extends CommonPage{
	DocumentManagementPageLocators document_locators;
	TCG_SMK_DocumentManagement dms;
	WebDriver driver;
	WebDriverWait wait;
	ControlActions controlActions;
	public Actions action;
	public DocumentManagementPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 40);
		controlActions = new ControlActions(driver);
		document_locators = new DocumentManagementPageLocators();
	}	

	/**
	 * Page Objects
	 */

	@FindBy(xpath = DocumentManagementPageLocators.PLUS_BUTTON)
	public WebElement PlusButton;
	@FindBy(xpath = DocumentManagementPageLocators.DOC_TYPE_TEXTBOX)
	public WebElement DocTypeTextBox ;
	@FindBy(xpath = DocumentManagementPageLocators.UPLOAD_BUTTON)
	public WebElement UploadButton;
	@FindBy(xpath = DocumentManagementPageLocators.UPLOAD_INPUT)
	public WebElement UploadInput;
	@FindBy(xpath = DocumentManagementPageLocators.DOWNLOAD_BTN)
	public WebElement DownloadBtn;
	@FindBy(xpath = DocumentManagementPageLocators.BROWSE_INPUT)
	public WebElement BrowserInput;
	@FindBy(xpath = DocumentManagementPageLocators.UPLOAD_BTN)
	public WebElement UploadBtn;
	@FindBy(xpath = DocumentManagementPageLocators.DELETE_YES_BTN)
	public WebElement DelYesBtn;
	@FindBy(xpath = DocumentManagementPageLocators.SCSDRD_LIST)
	public WebElement ScsDrdList;
	@FindBy(xpath = DocumentManagementPageLocators.RESTORE_YES_BTN)
	public WebElement RestoreYes;
	@FindBy(xpath = DocumentManagementPageLocators.TASKNAME_INPUT)
	public WebElement TaskName;
	@FindBy(xpath = DocumentManagementPageLocators.ASSIGN_BTN)
	public WebElement AssignBTN;
	@FindBy(xpath = DocumentManagementPageLocators.TABS_OPTIONS)
	public List<WebElement> TabOptions;
	@FindBy(xpath = DocumentManagementPageLocators.GRID_VALUE_SELECTION)
	public List<WebElement>GridValue;
	@FindBy(xpath = DocumentManagementPageLocators.SAVE_BTN)
	public WebElement SaveBtn;
	@FindBy(xpath = DocumentManagementPageLocators.DOCUMENT_NAME_COLUMN)
	public WebElement DocumentNameCol;
	@FindBy(xpath = DocumentManagementPageLocators.FILTER_SELECT)
	public WebElement FilterSelect;
	@FindBy(xpath = DocumentManagementPageLocators.FILTER_INPUT)
	public WebElement FilterInput;
	@FindBy(xpath = DocumentManagementPageLocators.FILTER_BTN)
	public WebElement FilterBtn;
	@FindBy(xpath = DocumentManagementPageLocators.EDIT_DOC_NAME)
	public WebElement EditDocName;
	@FindBy(xpath = DocumentManagementPageLocators.EDIT_DOC_TYPE)
	public WebElement EditDocType;
	@FindBy(xpath = DocumentManagementPageLocators.EDIT_DOC_DESC)
	public WebElement EditDocDescription;
	@FindBy(xpath = DocumentManagementPageLocators.EDIT_DOC_EXPD)
	public WebElement EditDocExpiryDate;
	@FindBy(xpath = DocumentManagementPageLocators.MANAGE_DOC_TYPE_GEAR_BTN)
	public WebElement ManageDocTypeGearButton;
	@FindBy(xpath = DocumentManagementPageLocators.DOC_NEW_NAME)
	public WebElement DocTypeNewName;
	@FindBy(xpath = DocumentManagementPageLocators.	DOCUMENT_TYPE_NAME)
	public static WebElement DocumentTypeName;
	@FindBy(xpath = DocumentManagementPageLocators.YES_BTN)
	public WebElement YesButton;
	@FindBy(xpath = DocumentManagementPageLocators.CANCEL_BTN)
	public WebElement CancelButton;
	@FindBy(xpath = DocumentManagementPageLocators.FILES_UPLOAD_SECTION)
	public WebElement FilesUploadSection;
	
	
	//UploadNew
	@FindBy(xpath = DocumentManagementPageLocators.UPLOAD_NEW_BROWSE)
	public WebElement UploadNewBrowse;
	@FindBy(xpath = DocumentManagementPageLocators.UPLOAD_NEW_FILE_NAME)
	public WebElement UploadNewFileName;
	@FindBy(xpath = DocumentManagementPageLocators.UPLOAD_NEW_COMMENT)
	public WebElement UploadNewComment;
	@FindBy(xpath = DocumentManagementPageLocators.UPLOAD_NEW_EXPD)
	public WebElement UploadNewExpiryDate;
	@FindBy(xpath = DocumentManagementPageLocators.FILE_EXTENSION_MISMATCH_YES)
	public WebElement UploadNewFileExtnMissMatchYesBtn;
	@FindBy(xpath = DocumentManagementPageLocators.UPLOAD_NEW_UPLOAD_BTN)
	public WebElement UploadNewUploadButton;
	
	
	@FindBy(xpath = DocumentManagementPageLocators.INFO_ICON)
	public WebElement InfoIcon;
	@FindBy(id = DocumentManagementPageLocators.DOC_DETAILS)
	public WebElement DocumentInfoSection;
	@FindBy(xpath = DocumentManagementPageLocators.DOC_DETAILS_EXPS)
	public WebElement DocumentExpiryStatus;
	@FindBy(xpath = DocumentManagementPageLocators.DOC_DETAILS_DOC_NAME)
	public WebElement DocumentName;
	@FindBy(xpath = DocumentManagementPageLocators.DOC_DETAILS_CROSS)
	public WebElement DocumentInfoCross;
	@FindBy(xpath = DocumentManagementPageLocators.ALL_DOC_DETAILS_FROM_GRID)
	public List<WebElement>AllDocDetailsFromGrid;

	// DMS->DOCUMENT VIEWER || INFO - > LINKS TAB
	@FindBy(xpath = DocumentManagementPageLocators.MANAGE_DOC_LINKS_BTN)
	public WebElement ManageDocumentLinks;
	@FindBy(xpath = DocumentManagementPageLocators.GRID_FIRST_ROW)
	public List<WebElement> GridFirstRow;
	@FindBy(xpath = DocumentManagementPageLocators.TABS_CONTAINER)
	public WebElement TabsContainer;
	
	@FindBy(xpath = DocumentManagementPageLocators.DOCS_GRID_LST)
	public List<WebElement> DocsGridLst;

	@FindBy(xpath = DocumentManagementPageLocators.SEARCH_MANAGELINK)
	public WebElement SearchManageLink;	
	@FindBy(xpath = DocumentManagementPageLocators.CUSTOMERS_MANAGELINK)
	public WebElement CustomersManageLink;	
	@FindBy(xpath = DocumentManagementPageLocators.ITEM_MANAGELINK)
	public WebElement ItemManageLink;	
	@FindBy(xpath = DocumentManagementPageLocators.SUPPLIERS_MANAGELINK)
	public WebElement SuppliersManageLink;	
	@FindBy(xpath = DocumentManagementPageLocators.EQUIPMENTS_MANAGELINK)
	public WebElement EquipmentsManageLink;	
	@FindBy(xpath = DocumentManagementPageLocators.LOCATIONS_MANAGELINK)
	public WebElement LocationsManageLink;	
	@FindBy(xpath = DocumentManagementPageLocators.USERS_MANAGELINK)
	public WebElement UsersManageLink;	
	@FindBy(xpath = DocumentManagementPageLocators.SEARCH_MANAGE_POPUP)
	public WebElement SearchManagePopup;	
	@FindBy(xpath = DocumentManagementPageLocators.SAVE_MANAGELINK)
	public WebElement SaveManageLink;	
	@FindBy(xpath = DocumentManagementPageLocators.TOTAL_DOCUMENT_LIST)
	public List<WebElement> TotalDocumentList;
	
	public static String linkedResource = null;
	public static String linkedResourceType = null;
	public static String uploadNewFileName = null;
	
	/**
	 * Functions
	 */

	/**
	 * This method is used to set create Document Type
	 * @author choubey_a
	 * @param none
	 * @return boolean This returns true if Document Type is created
	 */


	public boolean createDocType(String docTypeName) {
		try {
			controlActions.refreshPage();
			Sync();
			controlActions.WaitforelementToBeClickable(PlusButton);
//			controlActions.clickOnElement(PlusButton);
			PlusButton.click();
			controlActions.WaitforelementToBeClickable(DocTypeTextBox);
//			controlActions.clickOnElement(DocTypeTextBox);
			DocTypeTextBox.click();
//			controlActions.sendKeys(DocTypeTextBox,docTypeName);
			DocTypeTextBox.sendKeys(docTypeName);
//			threadsleep(5000);
//			Sync();
			controlActions.actionEnter();
			Sync();
			logInfo("Document Type " + docTypeName + " is created");
			return true;
		}catch(Exception e) {
			logError("Error while creating a document type " +e.getMessage());
			return false;
		}

	}

	/**
	 * This method is used to set upload Document 
	 * @author choubey_a
	 * @param addfile the path of the document being uploaded
	 * @return boolean This returns true if Document is uploaded
	 */

	public boolean uploadDocument(String addfile,String doctype) {		
		try {
			WebElement selectdoctype = controlActions.perform_GetElementByXPath(DocumentManagementPageLocators.DOCTYPE_SELECT, "DOCTYPE", doctype);
//			controlActions.clickElement(selectdoctype);
			selectdoctype.click();
			Thread.sleep(3000);
			controlActions.WaitforelementToBeClickable(UploadButton);
//			controlActions.sendKeys(UploadInput,addfile);
			UploadInput.sendKeys(addfile);
			Sync();
			threadsleep(5000);	
			logInfo("Document upload of file is done");
			return true;

		}catch(Exception e) {
			logError("Error while uploading a file " +e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to verify the document upload
	 * @author choubey_a
	 * @param image - the name of the document that has been uploaded
	 * @return boolean This returns true if Document is verified
	 */

	public boolean verifyDocUpload(String image) {
		WebElement DocName = null;
		try {
			Sync();
			DocName = controlActions.perform_GetElementByXPath(DocumentManagementPageLocators.UPLOADED_DOC,"DOCNAME",image);
			controlActions.isElementDisplayed(DocName);
			logInfo("Document upload of file name" + image + " is verified");
			return true;
		}catch(Exception e) {
			logError("Document upload of file name " + image + " is not verified " +e.getMessage());
			return false;

		}
	}

	/**
	 * This method is used to verify the document upload
	 * @author choubey_a
	 * @param docTypeName - the name of the document type
	 * @param addfile - name of the file to be uploaded
	 * @return boolean This returns true if Document type is created and document is uploaded 
	 */

	public boolean docUploadinDocType (String docTypeName,String addfile) {
		try {
			if(!createDocType(docTypeName)) {
				return false;
			}
			threadsleep(3000);
			if(!uploadDocument(addfile,docTypeName)) {
				return false;
			}
			logInfo("Document uploaded in doctype");
			return true;
		}catch(Exception e) {
			logError("Document" +addfile+ "upload in doc file" +docTypeName+ "failed " +e.getMessage());
			return false;
		}

	}

	/**
	 * This method is used to upload documents to different Document Types
	 * @author hingorani_a
	 * @param addFiles Where, HashMap's key is Name of the Document Type and 
	 * value is List of documents to be added 
	 * @return boolean This returns true after creating document types
	 *  with respective documents to be uploaded
	 */
	public boolean uploadDocInDocType(HashMap<String,List<String>> addFiles) {
		try {
			for (Map.Entry<String, List<String>> entry : addFiles.entrySet()) {
				String docTypeName = entry.getKey();
				List<String> uploadfile = entry.getValue();

				if(!createDocType(docTypeName)) {
					return false;
				}

				threadsleep(3000);

				for(String file : uploadfile) {
					if(!uploadDocument(file,docTypeName)) {
						return false;
					}
					logInfo("Document " + file + " uploaded in document type " + docTypeName);
				}
				logInfo("Added files " + uploadfile + " to Document type - " + docTypeName);
			}
			return true;
		}
		catch(Exception e) {
			logError("Failed to add files " + addFiles + " to Document type - "
					+ e.getMessage());
			return false;
		}

	}
	/**
	 * This method is used to download the document
	 * @author choubey_a
	 * @param fineName - the name of the file downloaded
	 * @return boolean This returns true if Document is downloaded
	 */

	public boolean docDownload (String addfile,String image,String doctype) {
		WebElement DocName = null;
		try {			
			uploadDocument(addfile,doctype);
			controlActions.refreshPage();
			DocName = controlActions.perform_GetElementByXPath(DocumentManagementPageLocators.UPLOADED_DOC,"DOCNAME",image);
			controlActions.doubleClick(DocName);
			controlActions.perform_waitUntilPresent(By.xpath(DocumentManagementPageLocators.CONTENT));
			threadsleep(3000);
			controlActions.WaitforelementToBeClickable(DownloadBtn);
			controlActions.clickOnElement(DownloadBtn);
			Thread.sleep(3000);
			logInfo("Document" + image + " downloaded successfully");
			Sync();
			return true;
		}catch(Exception e) {
			logError("Document Download Failed" + e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to verify the document download
	 * @author choubey_a
	 * @param downloadPath - the folder where file is downloaded
	 * @return boolean This returns true if Document download is verified
	 */

	public boolean verifyFileDownloaded (String downloadPath, String fileName) {
		File dir = new File(downloadPath);
		File[] dir_contents = dir.listFiles();
		try {
			for (int i = 0; i < dir_contents.length; i++)
				dir_contents[i].getName().equals(fileName);
			Thread.sleep(3000);
			logInfo("Document" + fileName + " download verified");
			return true;
		}catch(Exception e) {
			logError("Document download not verified" +e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to upload a new version of the document
	 * @author choubey_a
	 * @param addfile - the file path 
	 * @param doctype - the name of the document type 
	 * @param newimage - the name of the document
	 * @return boolean This returns true if new version Document is uploaded
	 */

	public boolean uploadNewVersion(String addfile, String doctype, String newimage) {
		WebElement DocName = null;
		try {
			uploadDocument(addfile,doctype);
			controlActions.refreshPage();
			DocName = controlActions.perform_GetElementByXPath(DocumentManagementPageLocators.UPLOADED_DOC,"DOCNAME",newimage);
			controlActions.clickElement(DocName);
			WebElement uploadnew = controlActions.perform_GetElementByXPath(DocumentManagementPageLocators.HEADER_ICONS, "TOOLTIP", TOOLTIPOPTIONS.UPLOADNEW);
			controlActions.clickOnElement(uploadnew);
			Thread.sleep(3000);
			controlActions.sendKeys(BrowserInput,addfile);
			Thread.sleep(2000);
			controlActions.clickOnElement(UploadBtn);
			Sync();
			logInfo("Document" + newimage + " uploaded");
			return true;
		}catch(Exception e) {
			logError("Upload new version failed" +e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to verify the version of the new version of the document
	 * @author choubey_a
	 * @param newimage - the name of the document
	 * @return boolean This returns true if version is verified
	 */

	public boolean verifyDocumentVersion(String newimage ) {
		WebElement newdocselect = null;
		WebElement newdocversion = null;
		try {
			newdocselect = controlActions.perform_GetElementByXPath(DocumentManagementPageLocators.UPLOADED_DOC,"DOCNAME",newimage);
			controlActions.clickElement(newdocselect);
			Sync();
			newdocversion = controlActions.perform_GetElementByXPath(DocumentManagementPageLocators.VERSION_TD,"DOCUMENT",newimage);
			controlActions.isElementDisplay(newdocversion);		
			logInfo("New document upload version verified");
			return true;
		}catch(Exception e) {
			logError("Verification of new document upload version failed" +e.getMessage());
			return false;
		}

	}

	/**
	 * This method is used to delete the document
	 * @author choubey_a
	 * @param addfile,doctype,image
	 * @return boolean This returns true if document is deleted
	 */


	public boolean deleteDocument(String addfile,String doctype, String image) {
		WebElement DocName = null;
		try {
			uploadDocument(addfile,doctype);
			controlActions.refreshPage();			
			WebElement selectdoctype = controlActions.perform_GetElementByXPath(DocumentManagementPageLocators.DOCTYPE_SELECT,"DOCTYPE", doctype);
			controlActions.clickElement(selectdoctype);
			Sync();
			DocName = controlActions.perform_GetElementByXPath(DocumentManagementPageLocators.UPLOADED_DOC,"DOCNAME",image);
			controlActions.clickOnElement(DocName);
			Sync();
			WebElement delete = controlActions.perform_GetElementByXPath(DocumentManagementPageLocators.HEADER_ICONS,"TOOLTIP",TOOLTIPOPTIONS.DELETE);
			controlActions.clickOnElement(delete);	
			Sync();
			controlActions.clickOnElement(DelYesBtn);
			logInfo("Document deleted");
			return true;
		}catch(Exception e) {
			logError("Document deletion failed" +e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to restore the document
	 * @author choubey_a
	 * @param doctype,image
	 * @return boolean This returns true if document is restored
	 */

	public boolean restoreDocument(String doctype, String image) {
		WebElement DocName = null;
		try {
			Sync();
			controlActions.clickOnElement(ScsDrdList);
			Thread.sleep(2000);
			WebElement selectdocoptions = controlActions.perform_GetElementByXPath(DocumentManagementPageLocators.SCSDRD_SELECT,"DROPDOWN", SCSOPTIONS.DELETEDDOCUMENTS);
			controlActions.clickElement(selectdocoptions);
			Sync();
			WebElement selectdoctype = controlActions.perform_GetElementByXPath(DocumentManagementPageLocators.DOCTYPE_SELECT,"DOCTYPE", doctype);
			controlActions.clickElement(selectdoctype);
			Sync();
			DocName = controlActions.perform_GetElementByXPath(DocumentManagementPageLocators.UPLOADED_DOC,"DOCNAME",image);
			controlActions.clickElement(DocName);
			Sync();	
			WebElement restore = controlActions.perform_GetElementByXPath(DocumentManagementPageLocators.HEADER_ICONS,"TOOLTIP", TOOLTIPOPTIONS.RESTORE);
			controlActions.clickOnElement(restore);
			Sync();
			controlActions.clickOnElement(RestoreYes);
			logInfo("Document restored");
			return true;
		}catch(Exception e) {
			logError("Document restoration failed" +e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to restore the document
	 * @author choubey_a
	 * @param doctype,image
	 * @return boolean This returns true if document restoration is verified
	 */

	public boolean verifyRestoration(String doctype, String image) {	
		WebElement DocName = null;
		try {
			Sync();
			controlActions.clickOnElement(ScsDrdList);
			Thread.sleep(2000);
			WebElement selectdocoptions = controlActions.perform_GetElementByXPath(DocumentManagementPageLocators.SCSDRD_SELECT,"DROPDOWN", SCSOPTIONS.DOCUMENTTYPES);
			controlActions.clickElement(selectdocoptions);
			Sync();
			WebElement selectdoctype = controlActions.perform_GetElementByXPath(DocumentManagementPageLocators.DOCTYPE_SELECT,"DOCTYPE", doctype);
			controlActions.clickElement(selectdoctype);
			Sync();
			DocName = controlActions.perform_GetElementByXPath(DocumentManagementPageLocators.UPLOADED_DOC,"DOCNAME",image);
			controlActions.isElementDisplay(DocName);
			logInfo("Document restoration verified");
			return true;
		}catch(Exception e) {
			logError("Document restoration verification failed" +e.getMessage());
			return false;
		}	
	}


	/**
	 * This method is used to assign document task 
	 * @author choubey_a
	 * @param addfile,doctype,image, DocTaskName, wgname
	 * @return boolean This returns true if document task is assigned
	 */	

	public boolean assignDocumentTask(String addfile,String doctype, String image, String DocTaskName, String wgname){
		WebElement DocName = null;
		try {
			uploadDocument(addfile,doctype);
			controlActions.refreshPage();			
			WebElement selectdoctype = controlActions.perform_GetElementByXPath(DocumentManagementPageLocators.DOCTYPE_SELECT,"DOCTYPE", doctype);
			controlActions.clickElement(selectdoctype);
			Sync();
			DocName = controlActions.perform_GetElementByXPath(DocumentManagementPageLocators.UPLOADED_DOC,"DOCNAME",image);
			controlActions.clickOnElement(DocName);
			Sync();
			WebElement assign = controlActions.perform_GetElementByXPath(DocumentManagementPageLocators.HEADER_ICONS,"TOOLTIP",TOOLTIPOPTIONS.ASSIGNTASK);
			controlActions.clickOnElement(assign);	
			Sync();
			TaskName.clear();
			controlActions.sendKeys(TaskName, DocTaskName);
			String select = "li[class='k-item ng-scope'][role='option']";
			controlActions.JSSetValueFromList(driver, select, wgname);	
			Thread.sleep(3000);
			controlActions.clickOnElement(AssignBTN);
			Sync();
			logInfo("Document task assigned");
			return true;
		}
		catch(Exception e) {
			logError("Failed to assign document task : "
					+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to manage document link 
	 * @author choubey_a
	 * @param addfile,doctype,image
	 * @return boolean This returns true if document is linked 
	 */	

	public boolean manageLink(String addfile,String doctype,String image){
		WebElement DocName = null;
		String taboptions;
		try {
			uploadDocument(addfile,doctype);
			controlActions.refreshPage();
			WebElement selectdoctype = controlActions.perform_GetElementByXPath(DocumentManagementPageLocators.DOCTYPE_SELECT,"DOCTYPE", doctype);
			controlActions.clickElement(selectdoctype);
			DocName = controlActions.perform_GetElementByXPath(DocumentManagementPageLocators.UPLOADED_DOC,"DOCNAME",image);
			controlActions.clickOnElement(DocName);
			WebElement link = controlActions.perform_GetElementByXPath(DocumentManagementPageLocators.HEADER_ICONS,"TOOLTIP",TOOLTIPOPTIONS.MANAGELINKS);
			controlActions.clickOnElement(link);	
			Sync();
			for(int i=0;i<TabOptions.size();i++) {
				controlActions.clickOnElement(TabOptions.get(i));
				taboptions = TabOptions.get(i).getAttribute("data-res-name");
				SCSOPTIONS.DROPDOWNTYPE=taboptions;
				Sync();
				if(!GridValue.isEmpty()) {
					controlActions.clickOnElement(GridValue.get(0));
					break;
				}
			}			
			controlActions.clickOnElement(SaveBtn);
			logInfo("Linked document");
			return true;
		}
		catch(Exception e) {
			logError("Failed to assign document task : "
					+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to verify document linking 
	 * @author choubey_a
	 * @param image
	 * @return boolean This returns true if document linking is verified
	 */	


	public boolean verifyManageLink(String image){
		try {	
			//Sync();
			controlActions.perform_waitUntilClickable(ScsDrdList);
			ScsDrdList.click();
			Thread.sleep(2000);
			WebElement selectdocoptions = controlActions.perform_GetElementByXPath(DocumentManagementPageLocators.SCSDRD_SELECT,"DROPDOWN",SCSOPTIONS.DROPDOWNTYPE);
			controlActions.clickElement(selectdocoptions);
			Sync();
			controlActions.clickOnElement(DocumentNameCol);
			Sync();
			controlActions.clickOnElement(FilterSelect);
			Thread.sleep(2000);
			controlActions.actionSendKeys(FilterInput,image);
			Thread.sleep(2000);
			controlActions.clickOnElement(FilterBtn);
			Sync();
			WebElement document = controlActions.perform_GetElementByXPath(DocumentManagementPageLocators.DOCUMENTNAME_COLUMNDATA,"DOCUMENT",image);
			controlActions.isElementDisplay(document);	
			Thread.sleep(2000);
			logInfo("Document Linking verified");
			return true;
		}
		catch(Exception e) {
			logError("Document Linking failed "
					+ e.getMessage());
			return false;
		}	
	}

	//===========ahmed_tw
	
	
	/**
	 * This method is used to select the document type
	 * @author ahmed_tw
	 * @param documentTypeName
	 * @return boolean This returns true after selecting the document type, else returns False if fails to do so
	 */	
	public boolean selectDocumentType(String documentTypeName) {
		try {
			
			WebElement selectdoctype = controlActions.perform_GetElementByXPath(DocumentManagementPageLocators.DOCTYPE_SELECT, "DOCTYPE", documentTypeName);
			controlActions.clickElement(selectdoctype);
			Sync();
			Thread.sleep(2000);
			
			logInfo("Selected Document Type -> " + documentTypeName);
			return true;
			
		} catch (Exception e) {
			logError("Could Not Select Document Type -> " + documentTypeName);
			return false;
		}
	}
	
	
	
	/** 'verifyDocAbsence' method is used to verify Absence of document for resource
	 * @author ahmed_tw
	 * @param String - 'documentName' - Name of Document
	 * @return boolean - 'TRUE' if Document is NOT found for that resource else 'FALSE'
	 */	
	public boolean verifyDocAbsence(String documentName) {
		WebElement DocName = null;
		try {
			Sync();
			DocName = controlActions.perform_GetElementByXPath(DocumentManagementPageLocators.UPLOADED_DOC,"DOCNAME",documentName);
			if(DocName!=null) {
				logError("Document delete of file name " + documentName + " is not verified");
				return false;
			}
			logInfo("Document delete of file name" + documentName + " is verified");
			return true;
		}catch(Exception e) {
			logError("Document delete of file name " + documentName + " is not verified");
			return false;

		}
	}
	
	
	/** 'printDocument' method is used to upload a document and perform Print from DMS
	 * @author ahmed_tw
	 * @return boolean True if print pop up is handled successfully else, False
	 */	
	public boolean printDocument(String documentType, String documentPath, String documentName) {
		WebElement printWindow = null;
		try {
			uploadDocument(documentPath,documentType);
			controlActions.refreshPage();
			WebElement selectdoctype = controlActions.perform_GetElementByXPath(DocumentManagementPageLocators.DOCTYPE_SELECT,"DOCTYPE", documentType);
			controlActions.clickElement(selectdoctype);
			Sync();
			WebElement DocName = controlActions.perform_GetElementByXPath(DocumentManagementPageLocators.UPLOADED_DOC,"DOCNAME",documentName);
			controlActions.clickOnElement(DocName);
			Sync();
			WebElement print = controlActions.perform_GetElementByXPath(DocumentManagementPageLocators.HEADER_ICONS,"TOOLTIP",TOOLTIPOPTIONS.PRINT);
			controlActions.clickOnElement(print);	
			
//			for(int i =0; i <3; i++) {
//				Thread.sleep(5000);
//				if(controlActions.is)
//			}
			
			logInfo("Opened Pint Dialogue");
			driver.switchTo().window(driver.getWindowHandles().toArray()[1].toString());
			printWindow = driver.findElement(By.tagName("print-preview-app"));
			if(printWindow!=null) {
				printWindow.sendKeys(Keys.TAB);
				printWindow.sendKeys(Keys.ENTER);
				logInfo("Closed Pint Dialogue");
			}else {
				logInfo("Print Dialogue did not opened");
				driver.switchTo().window(driver.getWindowHandles().toArray()[0].toString());
				return false;
			}
			driver.switchTo().window(driver.getWindowHandles().toArray()[0].toString());
		} catch (Exception e) {
			logError("Failed to perform Print" + e.getMessage());
			return false;
		}
		return true;
	}
	
	/**
	 * 'dmsToolbarDownload' method is used to download a document of a document type from DMS toolbar
	 * @param documentType - String : Name of Document Type
	 * @param downloadFileName - String : Name of Document in that Document Type
	 * @return 'boolean' returns true after downloading the document, else false
	 */
	public boolean dmsToolbarDownload(String documentType, String downloadFileName) {
		try {
			WebElement selectdoctype = controlActions.perform_GetElementByXPath(DocumentManagementPageLocators.DOCTYPE_SELECT,"DOCTYPE", documentType);
			controlActions.clickElement(selectdoctype);
			Sync();
			WebElement DocName = controlActions.perform_GetElementByXPath(DocumentManagementPageLocators.UPLOADED_DOC,"DOCNAME",downloadFileName);
			controlActions.clickOnElement(DocName);
			Sync();
			WebElement download = controlActions.perform_GetElementByXPath(DocumentManagementPageLocators.HEADER_ICONS,"TOOLTIP",TOOLTIPOPTIONS.DOWNLOAD);
			controlActions.clickOnElement(download);	
			Sync();
			logInfo("Downloaded the document - > " + downloadFileName);
		} catch (Exception e) {
			logError("Failed to Download the document - > " + downloadFileName);
			return false;
		}
		return true;
	}
	
	/**
	 * 'selectDmsToolbarOptions' This method is used to Select DMS toolbar option
	 * @param option-String : The DMS toolbar option to be selected 
	 * @return-boolean : True after on successful selection of toolbar option , else false
	 */
	public boolean selectDmsToolbarOptions(String option) {
		try {
			WebElement action = controlActions.perform_GetElementByXPath(DocumentManagementPageLocators.HEADER_ICONS,"TOOLTIP", option);
			controlActions.clickOnElement(action);	
			Sync();
			logInfo("Selected option -> " + option);
			
		} catch (Exception e) {
			logError("Failed to Select option -> " + option + e.getMessage());
			return false;
		}
		return true;
	}
	
	/**
	 * 'selectDmsToolbarOptionForDocument' This method is used to first select a document from grid and then given option form toolbar in DMS
	 * @param documentName - String : The name of Document for which the DMS toolbar option is to be selected
	 * @param option - String : Name of DMS Toolbar option to be selected
	 * @return - boolean : True after selecting DMS toolbar option for the specified document, else false
	 */
	public boolean selectDmsToolbarOptionForDocument(String documentName,String option) {
		try {
			selectDocument(documentName);
			logInfo("Selected document -> " + documentName);
			WebElement action = controlActions.perform_GetElementByXPath(DocumentManagementPageLocators.HEADER_ICONS,"TOOLTIP", option);
			controlActions.clickOnElement(action);	
			Sync();
			logInfo("Selected option -> " + option);
		} catch (Exception e) {
			logError("Failed to select - option -> " + option + " for document -> " + documentName + e.getMessage());
			return false;
		}
		return true;
	}
	
	/**
	 * This method is used to upload Document 
	 * @author ahmed_tw
	 * @param filePath the path of the document being uploaded
	 * @return boolean This returns true if Document is uploaded
	 */
	public boolean uploadDocument(String filePath) {
		try {
			controlActions.WaitforelementToBeClickable(UploadButton);
			controlActions.sendKeys(UploadInput,filePath);
			Sync();	
			logInfo("Document upload of file is done");
			return true;

		}catch(Exception e) {
			logError("Error while uploading a file " +e.getMessage());
			return false;
		}
	}
	
	/**
	 * 'selectDrpDwnAndDocType' Used to select 'Document Types' from drop down then the specified document type
	 * @param docType - String : Name of Document Type to be selected after selecting 'Document Types' from dropdown
	 * @return boolean : true post selecting 'Document Types' and the specific Document Type, else False
	 */
	public boolean selectDrpDwnAndDocType(String docType ) {
		try {
			controlActions.clickOnElement(ScsDrdList);
			Thread.sleep(2000);
			WebElement selectdocoptions = controlActions.perform_GetElementByXPath(DocumentManagementPageLocators.SCSDRD_SELECT,"DROPDOWN", SCSOPTIONS.DOCUMENTTYPES);
			controlActions.clickElement(selectdocoptions);
			Sync();
			WebElement selectdoctype = controlActions.perform_GetElementByXPath(DocumentManagementPageLocators.DOCTYPE_SELECT,"DOCTYPE", docType);
			controlActions.clickElement(selectdoctype);
			Sync();
			logInfo("Selected " + docType + " from 'Document Types'" );
		} catch (Exception e) {
			logInfo("Failed select " + docType + " from 'Document Types'" );			
			return false;
		}
		return true;
	}
	
	/** 'getDocumentVersion' method is used to get the version of Document
	 * @author ahmed_tw
	 * @param String - 'documentName' : Document whose version is needed
	 * @return double - The Version
	 */	
	public double getDocumentVersion(String documentName) {
		String version = null;
		try {
			WebElement documentVersion = controlActions.perform_GetElementByXPath(DocumentManagementPageLocators.DOCUMENT_VERSION,"DOCEMENT_NAME",documentName);
			version =  documentVersion.getText();
			logInfo("Document Version is - " + version);
			return Double.parseDouble(version);
		}catch(Exception e) {
			logError("Failed to get Document Version for document name - "+documentName+" - "
					+ e.getMessage());
			return 0.0;
		}
	}
	
	/** 'editAllDetails' method is used to Edit all details for a Document after "Edit Details" pop up is opened
	 * @author ahmed_tw
	 * @param String - 'name'  : value for 'Document Name'
	 * @param String - 'type'  : value for 'Document Type' 
	 * @param String - 'description' : value for 'Description'
	 * @param String - 'expiryDate'  : value for 'Expiration Date' 
	 * @return boolean - 'TRUE' if all the details are successfully Edited else 'FALSE'
	 */	
	public boolean editAllDetails(String name, String type, String description, String expiryDate) {

		try {
			Sync();
			controlActions.perform_waitUntilVisibility(By.id(DocumentManagementPageLocators.EDIT_DOC_POP_UP));
			
			if (!editDocName(name)) {
				return false;
			}
			if (!editDocumentType(type)) {
				return false;
			}
			if (!editDocDescription(description)) {
				return false;
			}
			if (!editDocExpiryDate(expiryDate)) {
				return false;
			}

			controlActions.clickbutton(SaveBtn);
			
			Sync();

		} catch (Exception e) {
			logError("Failed to Edit Details " + e.getMessage());
			return false;
		}

		return true;
	}
	
	/** 'editDocName' method is used to Edit Document Name when "Edit Details" pop up is opened
	 * @author ahmed_tw
	 * @param String - 'newName' : value for 'Document Name' option
	 * @return boolean - 'TRUE' if Name is edited else 'FALSE'
	 */	
	public boolean editDocName(String newName) {
		try {
			controlActions.sendKeys(EditDocName, newName);
			logInfo("Edited Document name - " + newName);
		} catch (Exception e) {
			logError("Failed to edit name - " + e.getMessage());
			return false;
		}
		return true;
	}
	
	/** 'editDocumentType' method is used to select Document Type of Document when "Edit Details" pop up is opened
	 * @author ahmed
	 * @param String - 'documentType'
	 * @return boolean true post selecting document type
	 */	
	public boolean editDocumentType(String documentType) {
		try {
			Sync();
//			controlActions.clickOnElement(EditDocType);
//			Thread.sleep(3000);
//			WebElement DocumentType = controlActions.perform_GetElementByXPath(DocumentManagementPageLocators.SELECT_DOCUMENT_TYPE, "DOCUMENTTYPE",documentType );
//			controlActions.clickOnElement(DocumentType);
			String select = "ul[id='scs-document-type_listbox']>li[class='k-item ng-scope'][role='option']";
			controlActions.JSSetValueFromList(driver, select, documentType);	
			Thread.sleep(2000);
			logInfo("Selected Document Type- "+documentType);
			return true;
		}catch(Exception e) {
			logError("Failed to select Document Type - "+documentType+" - "	+ e.getMessage());
			return false;
		}
	}

	/** 'editDocDescription' method is used to Edit Document Description after the "Edit Details" pop up is opened
	 * @author ahmed_tw
	 * @param String - 'description' : value for 'Description' option
	 * @return boolean - 'TRUE' if description is edited else 'FALSE'
	 */
	public boolean editDocDescription(String description) {
		try {
			controlActions.sendKeys(EditDocDescription, description);
			logInfo("Edited Document description - " + description);
		} catch (Exception e) {
			logError("Failed to edit description - " + e.getMessage());
			return false;
		}
		return true;
	}

	/** 'editDocExpiryDate' method is used to Edit Document Expiration Date after the "Edit Details" pop up is opened
	 * @author ahmed_tw
	 * @param String - 'expDate' : value for 'Expiration Date' option
	 * @return boolean - 'TRUE' if Name is edited else 'FALSE'
	 */
	public boolean editDocExpiryDate(String expDate) {
		try {
			controlActions.sendKeys(EditDocExpiryDate, expDate);
			logInfo("Edited Document Expiry Date - " + expDate);
		} catch (Exception e) {
			logError("Failed to edit expiry date - " + e.getMessage());
			return false;
		}
		return true;
	}

	/** 'clickSaveButton' method is used to click on Save button
	 * @author ahmed.tw
	 * @return boolean true post clicking on Save button
	 */	
	public boolean clickSaveButton() {
		try {
			controlActions.clickOnElement(SaveBtn);	
			Sync();
			logInfo("Clicked on Save button");
			return true;
		}catch(Exception e) {
			logError("Failed to click on Save button - "
					+ e.getMessage());
			return false;
		}
	}
	
	/**
	 * This method is use to rename a Document Type 
	 * @param docTypeName - String : The Existing name of Document Type
	 * @param docTypeNewName - String : The New Name for that Document Type
	 * @return - boolean : True after successfully renaming the document type, else false
	 */
	public boolean renameDocumentTypeName(String docTypeName, String docTypeNewName) {
		try {
		selectDrpDwnAndDocType(docTypeName);
		selectManageDocTypeOption(MANAGEDOCTYPE_OPTIONS.EDIT_NAME);
		DocTypeNewName.sendKeys(Keys.chord(Keys.CONTROL, "a"), docTypeNewName);
		controlActions.actionEnter();
		Sync();
		logInfo("Renamed document -> " + docTypeName + " as -> " + docTypeNewName);
		} catch (Exception e) {
			logError("Could Not Rename document -> " + docTypeName + " as -> " + docTypeNewName + e.getMessage());
		}
		return true;
	}
	
	/**
	 * This method is used to select options from 'Manage Document Type' dropdown which open after clicking on Gear button at top right  
	 * @param option - String : The option to be selected
	 * @return - boolean : True after selecting the specified option, else false
	 */
	public boolean selectManageDocTypeOption(String option) {
		try {
			controlActions.clickElement(ManageDocTypeGearButton);
			WebElement manageOption = controlActions.perform_GetElementByXPath(DocumentManagementPageLocators.MANAGE_DOC_TYPE_OPTIONS, "OPTION",option );
			controlActions.clickElement(manageOption);
			Sync();
			logInfo("Selected option -> " + option + " from Manage Document Type");
		} catch (Exception e) {
			logInfo("Faild to Select option -> " + option + " from Manage Document Type" + e.getMessage());
			return false;
		}
		return true;
	}
	
	/**
	 * This method is used to Disable a Document Type
	 * @param docTypeName - String : The name of document type to be disabled
	 * @return - boolean : True after disabling the document type, else false
	 */
	public boolean disableDocumentType(String docTypeName) {
		try {
		selectDocumentType(docTypeName);
		selectManageDocTypeOption(MANAGEDOCTYPE_OPTIONS.DISABLE);
		controlActions.perform_waitUntilClickable(By.xpath(DocumentManagementPageLocators.DISABLE_DIALOG));
		controlActions.clickbutton(YesButton);
		Sync();
		logInfo("Disabled document type -> " + docTypeName);
		} catch (Exception e) {
			logError("Could NOT Disable Documnet Type - >" + docTypeName + e.getMessage());
			return  false;
		}
		return true;
	}
	
	/**
	 * This method is used to Enable a Document Type
	 * @param docTypeName - String : The name of document type to be enables
	 * @return - boolean : True after enabling the document type, else false
	 */
	public boolean enableDocumentType(String docTypeName) {
		try {
		selectDocumentType(docTypeName);
		selectManageDocTypeOption(MANAGEDOCTYPE_OPTIONS.ENABLE);
		controlActions.perform_waitUntilVisibility(By.xpath(DocumentManagementPageLocators.ENABLE_DIALOG));
		controlActions.clickbutton(YesButton);
		Sync();
		logInfo("Enabled document type -> " + docTypeName);
		} catch (Exception e) {
			logError("Could NOT Enable Documnet Type - >" + docTypeName + e.getMessage());
			return  false;
		}
		return true;
	}
	
	/**
	 * This method is used to verify if the document type is disabled or not
	 * @param documentType - String : the name of the document 
	 * @return - boolean: True if the verificaion of disabled document is successful, else false
	 */
	public boolean verifyDisabledDocType(String documentType) {
		String docTypeNameFromHeader = DocumentTypeName.getText();
		System.out.println(docTypeNameFromHeader);
		boolean disablePrefix;
		boolean filesUploadSection;
		try {
			selectDocumentType(documentType);
			disablePrefix = DocumentTypeName.getText().contains("Disabled");
			filesUploadSection = controlActions.isElementDisplay(FilesUploadSection);
			
			if(disablePrefix)
				logInfo("Verified 'Disable' prefix in document type name at header");
			else
				logInfo("Could NOT Verify 'Disable' prefix in document type name at header");
			
			if(!filesUploadSection)
				logInfo("Verified absence of 'Files Upload' Section");
			else
				logInfo("Could NOT Verify absence of 'Files Upload' Section");
			
			if(disablePrefix || !filesUploadSection)
				logInfo("Verified DISABLE Status for Document Type -> " + documentType);
			else {
				logInfo("Could NOT Verify DISABLE Status for Document Type -> " + documentType);
				return false;
			}
		} catch (Exception e) {
			logError("Could NOT Verify DISABLE Status for Document Type -> " + documentType + e.getMessage());
			return false;
		}
		return true;
	}
	
	/** 'viewDocument' method is used to open the document in document viewer for viewing
	 * @author ahmed_tw
	 * @return boolean True if document is opened in doc viewer successfully else, False
	 */	
	public boolean viewDocument(String documentName) {
		try {
			
			Sync();
			WebElement document = controlActions.perform_GetElementByXPath(DocumentManagementPageLocators.DOCUMENT_NAME, "DOCUMENT_NAME",documentName );
			controlActions.doubleClickElement(document);
			controlActions.perform_waitUntilPresent(By.xpath(DocumentManagementPageLocators.DOC_VIEWER_CONTENT));
			Sync();
			Thread.sleep(2000);
			
			logInfo("Opened Document for Viewing, in Document Viewer");
			
		} catch (Exception e) {
			logInfo("Failed to Opened Document-  " + documentName + "  for Viewing, in Document Viewer");
			return false;
		}
		
		return true;
	}
	
	/** 'isToolsOptionPresent' method is used to check is the option is present or not in the caret toolbar of a document
	 * @author ahmed_tw
	 * @param String - 'documentName' : Document Name for which the caret option is to be clicked
	 * @param String - 'toolsOption'  : Particular Tools Option which is to be checked
	 * @return boolean True is the tools option is preset post clicking the caret button of document, else False
	 */	
	public boolean isToolbarOptionPresent(String documentName, String option) {
		try {
			selectDocument(documentName);
			WebElement optionPresent = controlActions.perform_GetElementByXPath(DocumentManagementPageLocators.HEADER_ICONS,"TOOLTIP", option);
			if(controlActions.isElementDisplay(optionPresent)) {
				logInfo("The option - " + optionPresent + " IS present");
				return true;
			}
			
		} catch (Exception e) {
			logError("Failed to check if option - " + option + "  is present or not."
					+ e.getMessage());
			return false;
		}
		logInfo("The option - " + option + " is NOT present");
		return false;
	}
	
	/**
	 * This method is used to select the document 
	 * @param documentName - Document to be selected
	 * @return boolean - True after selecting the document, else false 
	 */
	public boolean selectDocument(String documentName) {
		try {
			WebElement DocName = controlActions.perform_GetElementByXPath(DocumentManagementPageLocators.DOCUMENTNAME_COLUMNDATA,"DOCUMENT",documentName);
			controlActions.clickOnElement(DocName);
			logInfo("Selected document - " + documentName);
			Sync();
		} catch (Exception e) {
			logError("Could not select document - " + documentName + e.getMessage());
			return false;
		}
		
		return true;
	}
	
	/**
	 * This method is used to search and then select from the searched result 
	 * @author ahmed_tw
	 * @param String : The value to be searched
	 * @return boolean This returns true if given value is searched and selected
	 */
	public boolean searchSelect(String toSearch) {
		try {
			WebElement search = driver.findElement(By.id(DocumentManagementPageLocators.SEARCH_BOX));
			controlActions.sendKeys(search, toSearch);
			Sync();
			WebElement instanceSearchResult = controlActions.perform_GetElementByXPath(
					DocumentManagementPageLocators.SEARCH_RESULT, "SEARCH_RESULT", toSearch);
			controlActions.clickElement(instanceSearchResult);
			logInfo("Searched and Selected  - > " + toSearch);
		} catch (Exception e) {
			logError("Failed searching and selecting - " + e.getMessage());
			return false;

		}
		Sync();
		return true;
	}
	
	
	/**
	 * This method is used to select Resource type from the Drop Down
	 * @param resourceType - String: Name of the resource type
	 * @return boolean: True after selecting the resource type, else false
	 */
	public boolean selectResourceFrmDrpDwn(String resourceType) {
		try {
			controlActions.clickOnElement(ScsDrdList);
			Thread.sleep(2000);
			WebElement selectdocoptions = controlActions.perform_GetElementByXPath(DocumentManagementPageLocators.SCSDRD_SELECT,"DROPDOWN", resourceType);
			controlActions.clickElement(selectdocoptions);
			Sync();
			logInfo("Selected " + resourceType + " from DropDown");
		} catch (Exception e) {
			logError("Could Not Select " + resourceType + " from DropDown" + e.getMessage());
			return false;
		}
		return true;
	}
	
	/**
	 * This method is used to select Resource type from the dropdown and then the resource instance
	 * @param resourceType - Name of Resource type o be selected
	 * @param instanceName - Name of Resource Instance to be selected
	 * @return boolean : True after selecting respective resource type and instance, else false
	 */
	public boolean selectResourceAndInstance(String resourceType, String instanceName) {
		try {
			
			selectResourceFrmDrpDwn(resourceType);
			searchSelect(instanceName);
			logInfo("Selected Resource Type -> " + resourceType + " and resource instance ->" + instanceName);
		} catch (Exception e) {
			logInfo(" Could Not select Resource Type -> " + resourceType + " and resource instance ->" + instanceName + e.getMessage());
			return false;
		}
		return true;
	}
	
	
	
	/**
	 * This method is used to verify if a particular document is present or not
	 * @author ahmed_tw
	 * @param image - the name of the document 
	 * @return boolean This returns true if Document presence is verified
	 */
	public boolean isDocumentPresent(String documentName) {
		WebElement DocName = null;
		try {
			Sync();
			applyDocNameFilter(documentName);
			DocName = controlActions.perform_GetElementByXPath(DocumentManagementPageLocators.DOCUMENTNAME_COLUMNDATA,"DOCUMENT",documentName);
			controlActions.isElementDisplayed(DocName);
			logInfo("Document Present of file name" + documentName);
			return true;
		}catch(Exception e) {
			logError("Document NOT Present of file name " + documentName +e.getMessage());
			return false;

		}
	}
	
	/**
	 * This method is use to apply Document Name filter
	 * @param filterText - String : The filter text
	 * @return - boolean: True after applying filter, else false
	 */
	public boolean applyDocNameFilter(String filterText) {
		try {
			controlActions.clickOnElement(DocumentNameCol);
			Sync();
			controlActions.clickOnElement(FilterSelect);
			Thread.sleep(2000);
			controlActions.actionSendKeys(FilterInput,filterText);
			Thread.sleep(2000);
			controlActions.clickOnElement(FilterBtn);
			Sync();
			logInfo("Applied filter " + filterText + "for doc name filter");
		} catch (Exception e) {
			logInfo("Could not apply filter " + filterText + "for doc name filter" + e.getMessage());
			return false;
		}
		
		return true;
	}
	
	
	/** 'uploadNewVersion' method is used to upload new version file for a Document with comment and expiry date
	 * @author ahmed_tw
	 * @param String - 'documentName' : Document to which new file is to be uploaded
	 * @param String - 'newFilePath'  : File path of the new file
	 * @param String - 'comment'      : Comment for new version file upload
	 * @param String - 'expiryDate'   : Expiration date for new version file upload
	 * @return boolean - 'TRUE' if new version file is successfully uploaded, else 'FALSE'
	 */	
	public boolean uploadNewVersion(String documentName, String newFilePath, String comment, String expiryDate) {
		try {
			
			selectDmsToolbarOptionForDocument(documentName, TOOLTIPOPTIONS.UPLOADNEW);
			controlActions.perform_waitUntilVisibility(By.id(DocumentManagementPageLocators.UPLOAD_NEW_POP_UP));
			logInfo("Opened Upload New Version Pop Up for Document " + documentName);
			
			controlActions.sendKeys(UploadNewBrowse, newFilePath);
			uploadNewFileName = UploadNewFileName.getText();
			logInfo("Browse and Selected New File " + fileName);
			
			controlActions.sendKeys(UploadNewComment, comment);
			controlActions.sendKeys(UploadNewExpiryDate, expiryDate);
			logInfo("Entered Details : \n Comment -  " + comment + "\n ExpiryDate - " + expiryDate);
			
			if(controlActions.isElementDisplay(UploadNewFileExtnMissMatchYesBtn))
				controlActions.clickElement(UploadNewFileExtnMissMatchYesBtn);
			
			controlActions.clickElement(UploadNewUploadButton);
			logInfo("Uploaded New File" + fileName);
			
			Sync();
			
		} catch (Exception e) {
			logError("Failed to Upload new version file - " + fileName + " for document  -" + documentName + e.getMessage());
			return false;
		}
		
		return true;
	}
	
	/**
	 * This method is use to get all the information about a document visible on DMS Grid, including the Document Type
	 * @param documentName - String : The name of document for which details to be fetched
	 * @return - List<String> : Returns a list of string with all the fetched details
	 */
	public List<String> getAllDetailsOfDocFromGrid(String documentName){
		 List<String> documentDetails = new ArrayList<>();
		 List<WebElement> allDocDetails = null;
		 try {
			 allDocDetails = controlActions.perform_GetListOfElementsByXPath(DocumentManagementPageLocators.ALL_DOC_DETAILS_FROM_GRID, "DOCUMENT_NAME", documentName);
			 int length = allDocDetails.size();
			 for(int i = 0; i <length; i++) {
				 documentDetails.add(allDocDetails.get(i).getText());
			 }
			
		} catch (Exception e) {
			logError("Could not fetch the details for document " + documentName +e.getMessage());
			return null;
		}
		 documentDetails.add(DocumentTypeName.getText());
		 logInfo("Fetched all  the details of the document " +documentName);
		 return documentDetails;
	}
	
	//========================================= DMS - DOC VIEWER ========================//
	
	/** 'openDocumentDetails' method is used to open the Document Details Section in the Document Viewer
	 * @author ahmed_tw
	 * @return boolean true post opening Documents Details Section
	 */	
	public boolean openDocumentDetailsSection() {
		try {
			
			if(!DocumentInfoSection.isDisplayed()) {
			controlActions.perform_waitUntilClickable(InfoIcon);
			//controlActions.perform_waitUntilPresent(By.xpath(DocumentManagementPageLocators.DOC_VIEWER_CONTENT));
			//Thread.sleep(2000);
			controlActions.clickOnElement(InfoIcon);
			Thread.sleep(1800);
			controlActions.perform_waitUntilVisibility(By.id(DocumentManagementPageLocators.DOC_DETAILS));
			}
			logInfo("Clicked on Info Icon");
			return true;
		}catch(Exception e) {
			logError("Failed to click on Info Icon - "
					+ e.getMessage());
			return false;
		}
	}
	
	/** 'verifyAllChanges' method is used to Verify ALL changes done to document via "Edit Details" option. Verification is done from the 'Details' tab in Document Viewer.
	 * @author ahmed_tw
	 * @param String - 'name'  : value of 'Document Name'
	 * @param String - 'type'  : value of 'Document Type' 
	 * @param String - 'description' : value of 'Description'
	 * @param String - 'expiryDate'  : value of 'Expiration Date' 
	 * @return boolean - 'TRUE' if all changed details are successfully VERIFIED else 'FALSE'
	 */	
	public boolean verifyAllChanges(String name, String type, String description, String expiryDate) {
		try {

			if (!verifyDocumentsDetails(DOC_VIEWER_DETAILS_TAB.DOC_NAME,name)) {
				logInfo("Could NOT Verify Name - " + name );
				return false;
			}
			if (!verifyDocumentsDetails(DOC_VIEWER_DETAILS_TAB.DOC_TYPE,type)) {
				logInfo("Could NOT Verify Type - " + type );
				return false;
			}
			if (!verifyDocumentsDetails(DOC_VIEWER_DETAILS_TAB.DESCRIPTION,description)) {
				logInfo("Could NOT Verify Description - " + description );
				return false;
			}
			if (!verifyDocumentsDetails(DOC_VIEWER_DETAILS_TAB.EXP_DATE,expiryDate)) {
				logInfo("Could NOT Verify Expiry Date - " + expiryDate );
				return false;
			}

		} catch (Exception e) {
			logError("Failed to verify ALL VALUES " + e.getMessage());
			return false;
		}
		return true;
	}

	/** 'verifyDocumentsDetails' method is used to Verify SPECIFIC detail for a Document from the 'Details' tab in Document Viewer
	 * @author ahmed_tw
	 * @param String - 'fieldName' : Field name to be verified
	 * @param String - 'value'  :   value for verification
	 * @return boolean - 'TRUE' if the detail is successfully VERIFIED else 'FALSE'
	 */	
	public boolean verifyDocumentsDetails(String fieldName, String value) {
		String fieldValue = null;
		try {
			
			if(!DocumentInfoSection.isDisplayed()) {
				openDocumentDetailsSection();
			}
			
			if(fieldName.equalsIgnoreCase(DOC_VIEWER_DETAILS_TAB.EXP_DATE)) {
				if(!(DocumentExpiryStatus.getText().equalsIgnoreCase("No Expiration"))) {
					//System.out.println(DocumentExpiryStatus.getText());
					// Handle Expiry
					WebElement FieldValue = controlActions.perform_GetElementByXPath(DocumentManagementPageLocators.DOC_DETAILS_EXPD, "EXP_DATE_FIELD",DOC_VIEWER_DETAILS_TAB.EXP_DATE);
					fieldValue =  FieldValue.getText();
				}else {
					logInfo("The is no Expiry Date for this Document");
					//closeDocumentDetails();
					return true;
				}
			}else if (fieldName.equalsIgnoreCase(DOC_VIEWER_DETAILS_TAB.DOC_NAME)){
					  fieldValue =  DocumentName.getText();
			}else if(fieldName.equalsIgnoreCase(DOC_VIEWER_DETAILS_TAB.STATUS)){
					fieldValue =DocumentExpiryStatus.getText();
			}else {
				// handle rest
				WebElement FieldValue = controlActions.perform_GetElementByXPath(DocumentManagementPageLocators.DOC_DETAILS_FIELD, "DETAIL_FIELD",fieldName);
				fieldValue =  FieldValue.getText();
				//System.out.println(fieldValue);
			}
			
			if(fieldValue.equalsIgnoreCase(value)) {
				logInfo("VERIFIED value - " + value+ "for field - " +fieldName);
				return true;
			}
			
		} catch (Exception e) {
			logError("Failed to verify value - " + value+ "for field -" +fieldName+ e.getMessage());
			return false;
		}
		
		return true;
	}
	
	/**
	 * This method is used to verify all the fields present in the Details Tab of DMS->Document Viewer
	 * @param docName - String : Document Name
	 * @param docType - String : Document Type of document
	 * @param fileName - String : File Name of document
	 * @param size - String : Size of the document
	 * @param description - String : Description of document
	 * @param expDate - String : Expiry Date of document
	 * @param status - String : Status for the document
	 * @return boolean: True after verifying all the fields value in Details Tab of DMS->Document Viewer, else false
	 */
	public boolean verifyDetailsTab(String docName,  String docType, String fileName, String size, String description, String expDate, String status) {
		
		try {
			if (!verifyDocumentsDetails(DOC_VIEWER_DETAILS_TAB.DOC_NAME,docName)) {
				logInfo("Could NOT Verify Document Name - " + docName );
				return false;
			}
			if (!verifyDocumentsDetails(DOC_VIEWER_DETAILS_TAB.DOC_TYPE,docType)) {
				logInfo("Could NOT Verify Type - " + docType );
				return false;
			}			
			if (!verifyDocumentsDetails(DOC_VIEWER_DETAILS_TAB.FILE_NAME,fileName)) {
				logInfo("Could NOT Verify File Name - " + fileName );
				return false;
			}
			if (!verifyDocumentsDetails(DOC_VIEWER_DETAILS_TAB.SIZE,size)) {
				logInfo("Could NOT Verify Size - " + size );
				return false;
			}
			if (!verifyDocumentsDetails(DOC_VIEWER_DETAILS_TAB.DESCRIPTION,description)) {
				logInfo("Could NOT Verify Description - " + description );
				return false;
			}
			if (!verifyDocumentsDetails(DOC_VIEWER_DETAILS_TAB.EXP_DATE,expDate)) {
				logInfo("Could NOT Verify Exp Date - " + expDate );
				return false;
			}
			if (!verifyDocumentsDetails(DOC_VIEWER_DETAILS_TAB.STATUS,status)) {
				logInfo("Could NOT Verify Status - " + status );
				return false;
			}
		} catch (Exception e) {
			logError("Could Not verify details in the Details Tab of DMS->Document Viewer" + e.getMessage());
			return false;
		}
		logInfo("Verified all the details in the Details Tab of DMS->Document Viewer");
		return true;
	}
	
	
	
	/** 'verifyHistoryTabDetails' method is used to Verify SPECIFIC detail for a Document from the 'History' tab in Document Viewer
	 * @author ahmed_tw
	 * @param String - 'fieldName' : Field name to be verified
	 * @param String - 'value'  :   value for verification
	 * @return boolean - 'TRUE' if the detail is successfully VERIFIED else 'FALSE'
	 */	
	public boolean verifyHistoryTabDetails(String fieldName, String value) {
		String fieldValue = null;
		WebElement FieldValue = null;
		try {
			
			if(fieldName.equalsIgnoreCase(DOC_VIEWER_HISTORY_TAB.FILE_NAME)) {
				FieldValue = controlActions.perform_GetElementByXPath(DocumentManagementPageLocators.HIS_TAB_FILE_NAME);
				fieldValue =  FieldValue.getText();
				System.out.println(fieldValue);
			}
			else {
				FieldValue = controlActions.perform_GetElementByXPath(DocumentManagementPageLocators.HIS_TAB_DETAIL_FIELDS, "FIELD",fieldName);
				fieldValue =  FieldValue.getText();
				System.out.println(fieldValue);
			}
			
			if(fieldValue.equalsIgnoreCase(value)) {
				logInfo("VERIFIED value - " + value+ "for field - " +fieldName);
				return true;
			}
			
		} catch (Exception e) {
			logError("Failed to verify value - " + value+ "for field -" +fieldName+ e.getMessage());
			return false;
		}
		
		return true;
	}
	
	/**
	 * This method is used to verify all the fields present in the History Tab of DMS->Document Viewer
	 * @param docName - String : Document Name
	 * @param modifiedBy - String : Modified By User Name
	 * @param fileName - String : File Name for the document
	 * @param size - String : Size of the document
	 * @param comment - String : Comment for the Document
	 * @param expDate - String : Expiry Date of document
	 * @param docType - String : Document Type of document
	 * @return boolean: True after verifying all the field values in History Tab of DMS->Document Viewer, else false
	 */
	public boolean verifyHistoryTab(String docName,  String modifiedBy, String fileName, String size, String comment, String expDate, String docType) {
		
		try {
			if (!verifyHistoryTabDetails(DOC_VIEWER_HISTORY_TAB.DOC_NAME,docName)) {
				logInfo("Could NOT Verify Document Name - " + docName );
				return false;
			}
			if (!verifyHistoryTabDetails(DOC_VIEWER_HISTORY_TAB.DOC_TYPE,docType)) {
				logInfo("Could NOT Verify Type - " + docType );
				return false;
			}			
			if (!verifyHistoryTabDetails(DOC_VIEWER_HISTORY_TAB.FILE_NAME,fileName)) {
				logInfo("Could NOT Verify File Name - " + fileName );
				return false;
			}
			if (!verifyHistoryTabDetails(DOC_VIEWER_HISTORY_TAB.SIZE,size)) {
				logInfo("Could NOT Verify Size - " + size );
				return false;
			}
			if (!verifyHistoryTabDetails(DOC_VIEWER_HISTORY_TAB.COMMENT,comment)) {
				logInfo("Could NOT Verify Description - " + comment );
				return false;
			}
			if (!verifyHistoryTabDetails(DOC_VIEWER_HISTORY_TAB.EXP_DATE,expDate)) {
				logInfo("Could NOT Verify Exp Date - " + expDate );
				return false;
			}
		} catch (Exception e) {
			logError("Could Not verify details in the History Tab of DMS->Document Viewer" + e.getMessage());
			return false;
		}
		logInfo("Verified all the details in the Details Tab of DMS->Document Viewer");
		return true;
	}
	
	
	
	/** 'clickTabInDocumentDetailsSection' method is used to click Tab in Document Details Section
	 * @author ahmed_tw
	 * @param String - 'tabName'
	 * @return boolean true post clicking Tab in Document Details Section
	 */	
	public boolean clickTabInDocumentDetailsSection(String tabName) {
		try {
			controlActions.perform_waitUntilPresent(By.xpath(DocumentManagementPageLocators.DOC_VIEWER_CONTENT));
			Sync();
			WebElement DocDetailsTabName = controlActions.perform_GetElementByXPath(DocumentManagementPageLocators.DOC_DETAILS_TAB_NAME, "TABNAME",tabName );
			controlActions.clickOnElement(DocDetailsTabName);
			Sync();
			logInfo("click Tab in Document Details Section -" + tabName);
			return true;
		}catch(Exception e) {
			logError("Failed to click Tab in Document Details Section - "+tabName+" - "
					+ e.getMessage());
			return false;
		}
	}
	
	//MANAGE LINKS ======== start
	/** 'clickManageDocumentsLinks' method is used to click on 'Manage Documents Links' Button in Links Tab in Document Viewer
	 * @author ahmed_tw
	 * @return boolean true post clicking on 'Manage Documents Links Button'
	 */	
	public boolean clickManageDocumentsLinks() {
		try {
			controlActions.perform_waitUntilVisibility(By.id(DocumentManagementPageLocators.DOC_VIEWER_MANAGE_SECTION));
			controlActions.clickOnElement(ManageDocumentLinks);	
			Sync();
			logInfo("Clicked on 'Manage Documents Links' Button");
			return true;
		}catch(Exception e) {
			logError("Failed to click on 'Manage Documents Links' Button "
					+ e.getMessage());
			return false;
		}
	}
	
	
	/**
	 * This method is used to manage document link 
	 * @author ahmed_tw
	 * @param addfile,doctype,image
	 * @return boolean This returns true if document is linked 
	 */	
	public boolean manageLink(){
		try {
			Sync();
			controlActions.perform_waitUntilVisibility(By.id(DocumentManagementPageLocators.MANAGE_LINKS_POP_UP));
			for(int i=0;i<TabOptions.size();i++) {
				controlActions.clickOnElement(TabOptions.get(i));
				linkedResourceType = TabOptions.get(i).getAttribute("data-res-name");
				Sync();
				if(!GridValue.isEmpty()) {
					controlActions.clickOnElement(GridFirstRow.get(0).findElement(By.tagName("input")));
					linkedResource =  GridFirstRow.get(1).getText();
					System.out.println(linkedResource);
					break;
				}
			}			
			controlActions.clickOnElement(SaveBtn);
			logInfo("Linked document to resource - "  + linkedResource);
			return true;
		}
		catch(Exception e) {
			logError("Failed to Link document to resource - "  + linkedResource
					+ e.getMessage());
			return false;
		}	
	}
	
	/** 'verifyLink' method is used to verify linked resource for a document
	 * @author ahmed_tw
	 * @param String - 'resourceName' : Resource whose linking is to be verified
	 * @return boolean - 'TRUE' if linking is verified, else 'FALSE'
	 */	
	public boolean verifyLink(String resourceName) {
		WebElement link = null;
		try {
			link = controlActions.perform_GetElementByXPath(DocumentManagementPageLocators.LINKED_INSTANCES, "INSTANCE_NAME",resourceName);
			if(controlActions.isElementDisplay(link))
				logInfo("Verified Linked resource - > " + resourceName);
			else
				logInfo("Could not Verify Linked resource - > " + resourceName);
			
		} catch (Exception e) {
			logError("Failed to verify Linked resource  -" +resourceName+ e.getMessage());
			return false;
		}
		return true;
	}
	//MANAGE LINKS ======== END
	
	
	//ASSIGN TASK ========== START
	/** 'assignTaskSelectLocation' method is used to select Location of Document when Assign Task popup is opened
	 * @author ahmed_tw
	 * @param String - 'locationInstance'
	 * @return boolean true post selecting location instance
	 */	
	public boolean assignTaskSelectLocation(String locationInstance) {
		try {
			Thread.sleep(1000);
			WebElement location = controlActions.perform_GetElementByXPath(DocumentManagementPageLocators.ASS_TSK_LOCATION, "LOCATION",locationInstance );
			controlActions.clickOnElement(location);
			logInfo("Selected Location - "+locationInstance);
			return true;
		}catch(Exception e) {
			logError("Failed to select Location - "+locationInstance+" - "	+ e.getMessage());
			return false;
		}
	}
	
	/** 'assignTaskSelectWorkgroup' method is used to select WorkGroup of Document when Assign Task popup is opened
	 * @author ahmed_tw
	 * @param String - 'workGroup'
	 * @return boolean true post selecting workGroup
	 */	
	public boolean assignTaskSelectWorkgroup(String workGroup) {
		try {
			Thread.sleep(1000);
			WebElement workgroup = controlActions.perform_GetElementByXPath(DocumentManagementPageLocators.ASS_TSK_WRKGP, "WORKGROUP",workGroup );
			controlActions.clickOnElement(workgroup);
			logInfo("Selected work group - "+workGroup);
			return true;
		}catch(Exception e) {
			logError("Failed to select work group - "+workGroup+" - "	+ e.getMessage());
			return false;
		}
	}
	
	/** 'assignTaskFillDetails' method is used to fill details for respective field in 'Assign Task' pop up
	 * @author ahmed_tw
	 * @param String - 'fieldName'  : Field for which value is to be set 
	 * @param String - 'fieldValue' : The value to be set for field
	 * @return boolean 'TRUE' after filling the values in respective fields else, 'FALSE'
	 */	
	public boolean assignTaskFillDetails(String fieldName, String fieldValue) {
		WebElement field = null;
		try {
			if(fieldName.equals(ASSIGN_TASK_FIELDS.TASK_NAME) || fieldName.equals(ASSIGN_TASK_FIELDS.DUE_BY)){
				field = controlActions.perform_GetElementByXPath(DocumentManagementPageLocators.ASSIGN_TASK_FIELDS, "FILED_NAME",fieldName );
				controlActions.sendKeys(field, fieldValue);
			}else if(fieldName.equals(ASSIGN_TASK_FIELDS.LOCATION)) {
				String locationXpath = DocumentManagementPageLocators.ASSIGN_TASK_FIELDS.replaceAll("input", "span[2]");
				field = controlActions.perform_GetElementByXPath(locationXpath, "FILED_NAME",fieldName);
				controlActions.clickElement(field);
				assignTaskSelectLocation(fieldValue);
			}else if(fieldName.equals(ASSIGN_TASK_FIELDS.ASSIGN_TO)) {
				String wGXpath = DocumentManagementPageLocators.ASSIGN_TASK_FIELDS.replaceAll("input", "span[2]");
				field = controlActions.perform_GetElementByXPath(wGXpath, "FILED_NAME",fieldName);
				controlActions.clickElement(field);
				assignTaskSelectWorkgroup(fieldValue);
			}else if(fieldName.equals(ASSIGN_TASK_FIELDS.NOTE)) {
				String notesXpath = DocumentManagementPageLocators.ASSIGN_TASK_FIELDS.replaceAll("input", "textarea");
				field = controlActions.perform_GetElementByXPath(notesXpath, "FILED_NAME",fieldName);
				controlActions.sendKeys(field, fieldValue);
			}
			logInfo("Filled value - " + fieldValue + " for Field - " + fieldName);
			return true;
			
		} catch (Exception e) {
			logError("Failed to fill Assign Task Details - " + " for Field - " + fieldName + " as -  " + fieldValue + e.getMessage());
			return false;
			
		}
	}

	
	/** 'assignTask' method is used to assign task from DMS->DOCUMENT VIWER for a document (with dueBy)
	 * @author ahmed_tw
	 * @param String - 'document'  : The document for which task needs to be assigned
	 * @param String - 'taskName'  : The value to be set for field - Task Name
	 * @param String - 'location'  : The value to be set for field - Location
	 * @param String - 'workGroup' : The value to be set for field - Work Group
	 * @param String - 'dueBy' 	   : The value to be set for field - Due By
	 * @param String - 'note'      : The value to be set for field - Note
	 * @return boolean 'TRUE' after assigning Task to the document else, 'FALSE'
	 */	
	public boolean assignTask(String document, String taskName, String workGroup) {
		try {
			
			controlActions.perform_waitUntilVisibility(By.xpath(DocumentManagementPageLocators.POPUP_TITLE));
			
//			if(!selectOptionFromTools(document, TOOLBAR_OPTIONS.ASSIGN_TASK)){
//				logError("Failed to open Assign Task Dialogue from toolbar");
//				throw new Exception();
//			}
			
			if(!assignTaskFillDetails(ASSIGN_TASK_FIELDS.TASK_NAME, taskName))
				return false;
		
//			if(!assignTaskFillDetails(ASSIGN_TASK_FIELDS.LOCATION, location)) 
//				return false;
			
			if(!assignTaskFillDetails(ASSIGN_TASK_FIELDS.ASSIGN_TO, workGroup))
				return false;
			
//			if(!assignTaskFillDetails(ASSIGN_TASK_FIELDS.DUE_BY, dueBy))
//				return false;
			
//			if(!assignTaskFillDetails(ASSIGN_TASK_FIELDS.NOTE, note))
//				return false;
			
			controlActions.clickElement(AssignBTN);
			Sync();
			Thread.sleep(1000);
			
		} catch (Exception e) {
			logError("Failed to Assign Task for document -  " + document + e.getMessage());
			return false;
		}
	return true;
	}
	//ASSIGN TASK ========== END
	
	
	/** 'closeDocumentDetails' method is used to close Documents Details Section
	 * @author ahmed_tw
	 * @return boolean true post closing Documents Details Section
	 */	
	public boolean closeDocumentDetails() {
		try {
			controlActions.clickOnElement(InfoIcon);
			Thread.sleep(3000);
			//controlActions.perform_waitUntilClickable(DocumentInfoCross);
			//controlActions.clickElement(DocumentInfoCross);	
			controlActions.perform_waitUntilNotVisible(By.id(DocumentManagementPageLocators.DOC_DETAILS));
			logInfo("Closed Document Details Section");
			return true;
		}catch(Exception e) {
			logError("Failed to Close Document Details Section - "
					+ e.getMessage());
			return false;
		}
	}
	

	/** 'clickVerticleToolbarOptions' method is used to click on Verticle toolbar option in Document Viewer
	 * @author ahmed_tw
	 * @param String - 'option' : Option to be clicked
	 * @return boolean - 'TRUE' if option is successfully clicked, else 'FALSE'
	 */	
	public boolean selectDocViewerToolbarOptions(String option) {
		String optionNumber = null;
		try {
			switch(option) {
			case DOC_VIEWER_TOOLBAR.EDIT_DETAILS:
				optionNumber = "1"; break;
			case DOC_VIEWER_TOOLBAR.UPLOAD_NEW_VERSION:
				optionNumber = "2"; break;
			case DOC_VIEWER_TOOLBAR.ASSIGN_TASK:
				optionNumber = "3"; break;
			case DOC_VIEWER_TOOLBAR.DOWNLOAD:
				optionNumber = "4"; break;
			case DOC_VIEWER_TOOLBAR.PRINT:
				optionNumber = "5"; break;
			case DOC_VIEWER_TOOLBAR.DELETE:
				optionNumber = "6"; break;
			}
			WebElement toolbarOption = controlActions.perform_GetElementByXPath(DocumentManagementPageLocators.VERTICLE_TOOLBAR_OPTIONS, "OPTION", optionNumber);
			controlActions.clickElement(toolbarOption);
			Sync();
			return true;
		} catch (Exception e) {
			logError("Failed to Click on Verticle toolbar option - " + option + " " + e.getMessage());
			return false;
		}
	}

	
	/** 'handlePrint' method is used to handle the print pop up of Chrome
	 * @author ahmed_tw
	 * @return boolean True if print pop up is handled successfully else, False
	 */	
	public boolean handlePrint() {
		WebElement printWindow = null;
		try {
			selectDocViewerToolbarOptions("Print");
			logInfo("Opened Pint Dialogue");
			Thread.sleep(5000);
			driver.switchTo().window(driver.getWindowHandles().toArray()[1].toString());
			printWindow = driver.findElement(By.tagName("print-preview-app"));
			
			if(printWindow!=null) {
				printWindow.sendKeys(Keys.TAB);
				printWindow.sendKeys(Keys.ENTER);
				logInfo("Closed Pint Dialogue");
			}else {
				logInfo("Print Dialogue did not opened");
				driver.switchTo().window(driver.getWindowHandles().toArray()[0].toString());
				return false;
			}
			driver.switchTo().window(driver.getWindowHandles().toArray()[0].toString());
			
//			WebElement webElement1 = driver.findElement(By.xpath("//div[@id='headerContainer']/h1[@class='title']"));
//			System.out.println(webElement1.getText());
		} catch (Exception e) {
			logError("Failed to perform Print" + e.getMessage());
			return false;
		}
		return true;
	}
	
	/**
	 * This method is used to perform delete a file form DMS->DOCUMNET Viewer, verticle Toolbar
	 * @author ahmed_tw
	 * @param filename - String: Name of the file to be deleted
	 * @return - boolean: True after performing the delete operation for the file, else false
	 */
	public boolean dmsDocViewerDelete(String filename) {
		try {
			selectDocViewerToolbarOptions(DOC_VIEWER_TOOLBAR.DELETE);
			controlActions.perform_waitUntilVisibility(DelYesBtn);
			controlActions.clickElement(DelYesBtn);
			Sync();
			logInfo("Performed delete operation for file - "+ filename);
		} catch (Exception e) {
			logError("Could NOT delete operation for file - "+ filename + e.getMessage());
			return false;
		}
		return true;
	}
	
	/** This method is used to open the document in document viewer for viewing
	 * @author ahmed_tw
	 * @return boolean True if document is opened in doc viewer successfully else, False
	 */	
	public boolean viewQuestionnaireDocument(String questionnaireDoc) {
		try {
			Sync();
			WebElement document = controlActions.perform_GetElementByXPath(DocumentManagementPageLocators.DOCUMENT_NAME, "DOCUMENT_NAME",questionnaireDoc);
			controlActions.doubleClickElement(document);
			Sync();
			Thread.sleep(2000);
			logInfo("Opened Questionnaire Document for Viewing, in Document Viewer");
		} catch (Exception e) {
			logInfo("Failed to Opened Questionnaire Document-  " + questionnaireDoc + "  for Viewing, in Document Viewer");
			return false;
		}
		return true;
	}
	
	/**
	 * This method is used to verify if the field is Marked as "Required" i,e have * symbol or not while viewing this record.
	 * @author ahmed_tw
	 * @param fieldName - name of the field
	 * @return True is the field is marked as "Required" else false
	 */
	public boolean verifyIsFieldMarkedAsRequiredQstnDoc(String fieldName) {
		WebElement reqSymbol = null;
		try {
			reqSymbol = controlActions.perform_GetElementByXPath(DocumentManagementPageLocators.FIELD_REQUIRED_ASTERISK, "FIELD_NAME", fieldName);
			if(reqSymbol.isDisplayed()) {
				logInfo("Verified that the field -" + fieldName + " is marked as 'Required");
				return true;
			}
			logInfo("The field -" + fieldName + " is OT marked as 'Required'");
			return false;
		} catch (Exception e) {
			logError("Could not verify whether the field -" + fieldName+ " is Marked as 'Required' or Not" + e.getMessage());
			return false;
		}
	}
	
	/**
	 * This method is used to verify if the proper hint is displayed for the field while Viewing the Record
	 * @author ahmed_tw
	 * @param fieldName - Name of the field
	 * @param hint - The hint to be verified
	 * @return True if the hint for the field is verified else false
	 */
	public boolean verifyHintForFieldQstnDoc(String fieldName, String hint) {
		WebElement displayedHint = null ;
		try {
			displayedHint = controlActions.perform_GetElementByXPath(DocumentManagementPageLocators.FIELD_HINT, "FIELD_NAME", fieldName);
			
			if(displayedHint.isDisplayed() ) {
				logInfo("Verified that hint is displayed for field -" + fieldName);
			}else {
				logInfo("Hint is NOT displayed for field -" + fieldName);
				return false;
			}
			
			if( displayedHint.getText().equals(hint)) {
				logInfo("Verified that correct hint is displayed for field -" + fieldName);
				return true;
			}else {
				logInfo("Correct hint is NOT displayed for field -" + fieldName);
				return false;
			}
		} catch (Exception e) {
			logError("Could Not verify the display of proper hint for the field -" + fieldName + e.getMessage());
			return false;
		}
		
	}
	
	/**
	 * This Method is used to verify the comment for the field.
	 * @author ahmed_tw
	 * @param fieldName - Name of the field
	 * @param comment - Expected comment for the field
	 * @return True if the comment is verified, else false
	 */
	public boolean verifyCommentForFieldQstnDoc(String fieldName, String comment) {
		WebElement displayedComment = null ;
		try {
			displayedComment = controlActions.perform_GetElementByXPath(DocumentManagementPageLocators.FIELD_COMMENT, "FIELD_NAME", fieldName);
			if(displayedComment.getText().equals(comment)) {
				logInfo("Comment -" + comment + " for field -" + fieldName + " is Verified");
				return true;
			}
			
			logInfo("Comment -" + comment + " for field -" + fieldName + " is Not Verified");
			return false;
		} catch (Exception e) {
			logError("Could not verify the comment -" +  comment + " for field -" + fieldName + e.getMessage());
			return false;
		}
	}
	
	/**
	 * This method is used to check if there is a particular attachment file with the field or not.
	 * @author ahmed_tw
	 * @param fieldName - Name of the Field 
	 * @param fileName - Name of the Attachment File
	 * @return True if the attachment is there, else false
	 */
	public boolean verifyAttachmentFileNameForFieldQstnDoc(String fieldName, String fileName) {
		List<WebElement> displayedListOfAttachments = null ;
		try {
			displayedListOfAttachments = controlActions.perform_GetListOfElementsByXPath(DocumentManagementPageLocators.FIELD_ATTACHMENTS_NAME, "FIELD_NAME", fieldName);

			for(WebElement element : displayedListOfAttachments) {
				if(element.getText().equals(fileName)) {
					logInfo("Verified that the attachment -" + fileName + " is attached with field -" + fieldName );
					return true;
				}
			}
			logInfo("The attachment -" + fileName + " is NOT attached with field -" + fieldName );
			return false;
		} catch (Exception e) {
			logError("Could NOT Verify that the attachment -" + fileName + " is attached with field -" + fieldName );
			return false;
		}
		
	}
	
	/**
	 * This method is used to verify the correction for the field. 
	 * @author ahmed_tw
	 * @param fieldName - Field Name
	 * @param correction - The Expected correction for that field
	 * @return True if the correction is verified, else false
	 */
	public boolean verifyCorrectionForFieldQstnDoc(String fieldName, String correction) {
		WebElement displayedCorretion = null ;
		try {
			displayedCorretion = controlActions.perform_GetElementByXPath(DocumentManagementPageLocators.FIELD_CORRECTION, "FIELD_NAME", fieldName);

			if(displayedCorretion.getText().equals(correction)) {
				logInfo("Correction -" + correction + " for field -" + fieldName + " is Verified");
				return true;
			}
			
			logInfo("Correction -" + correction + " for field -" + fieldName + " is Not Verified");
			return false;
		} catch (Exception e) {
			logError("Could not verify the Correction -" +  correction + " for field -" + fieldName + e.getMessage());
			return false;
		}
	}
	
	/**
	 * This method is used to verify Is Resolved (Mark As Resolvd) Yes or No
	 * @author ahmed_tw
	 * @param fieldName - Name of Field
	 * @param yesOrNo - Set Yes or No value
	 * @return True after verifying the Yes/No value, else false
	 */
	public boolean verifyIsResolvedForFieldQstnDoc(String fieldName, String yesOrNo) {
		WebElement displayedResolvedValue = null ;
		try {
			displayedResolvedValue = controlActions.perform_GetElementByXPath(DocumentManagementPageLocators.FIELD_RESOLVED, "FIELD_NAME", fieldName);
			if(displayedResolvedValue.getText().equals(yesOrNo)) {
				logInfo("Is Resolved value -" + yesOrNo + " for field -" + fieldName + " is Verified");
				return true;
			}
			
			logInfo("Is Resolved value -" + yesOrNo + " for field -" + fieldName + " is Not Verified");
			return false;
		} catch (Exception e) {
			logError("Could not verify the Is Resolved -" +  yesOrNo + " for field -" + fieldName + e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to verify Is Resolved (Mark As Resolvd) Yes or No
	 * @author pednekar_pr
	 * @param addfile This stores path of image
	 * @param doctype This stores value of document type
	 * @param image This stores name of file to be uploaded
	 * @param val This stores integer value of tab
	 * val =0 Customer tab
	 * val=1 Items tab
	 * val=2 Suppliers tab
	 * val=3 Equipment tab
	 * val=4 Location tab
	 * val=5 Users tab
	 * @param locName This stores value of location Instance
	 * @return This returns true when doctype is successfully linked to given locName
	 */
	public boolean manageLocationLink(String addfile,String doctype,String image, int val, String locName){
		WebElement DocName = null;
		String taboptions;
		try {
			uploadDocument(addfile,doctype);
			logInfo("Succesfully uploaded file");
			
			controlActions.refreshPage();
			WebElement selectdoctype = controlActions.perform_GetElementByXPath(DocumentManagementPageLocators.DOCTYPE_SELECT,"DOCTYPE", doctype);
			controlActions.clickElement(selectdoctype);
			DocName = controlActions.perform_GetElementByXPath(DocumentManagementPageLocators.UPLOADED_DOC,"DOCNAME",image);
			controlActions.clickOnElement(DocName);
			WebElement link = controlActions.perform_GetElementByXPath(DocumentManagementPageLocators.HEADER_ICONS,"TOOLTIP",TOOLTIPOPTIONS.MANAGELINKS);
			controlActions.clickOnElement(link);	
			Sync();
			for(int i=val;i<TabOptions.size();i++) {
				controlActions.clickOnElement(TabOptions.get(i));
				taboptions = TabOptions.get(i).getAttribute("data-res-name");
				SCSOPTIONS.DROPDOWNTYPE=taboptions;
				Sync();
				if(!GridValue.isEmpty()) {
					controlActions.sendKeys(SearchManageLink, locName);
					controlActions.sendKey(SearchManageLink, Keys.ENTER);
					controlActions.clickOnElement(GridValue.get(0));
					break;
				}
			}			
			controlActions.clickOnElement(SaveBtn);
			logInfo("Succesfully Linked document to given location Instance");
			return true;
		}
		catch(Exception e) {
			logError("Failed to link document to location Instance "
					+ e.getMessage());
			return false;
		}	
	}
	/**
	 * This method is used to select particular document and open manage links popup
	 * @author jadhav_akan
	 * @param image - the name of the document 
	 * @return boolean This returns true if clicked on Document & open Popup
	 */
	public boolean selectDocumentAndOpenManageLinks(String documentName) {
		WebElement DocName = null;
		try {
			
			Sync();
			//applyDocNameFilter(documentName);
			DocName = controlActions.perform_GetElementByXPath(DocumentManagementPageLocators.DOCUMENTNAME_COLUMNDATA,"DOCUMENT",documentName);
			controlActions.clickElement(DocName);
			WebElement link = controlActions.perform_GetElementByXPath(DocumentManagementPageLocators.HEADER_ICONS,"TOOLTIP",TOOLTIPOPTIONS.MANAGELINKS);
			controlActions.clickOnElement(link);	
			Sync();
			logInfo("Successfully selected document -" + documentName+ " and Opened Manage Links popUp");
			return true;
		}catch(Exception e) {
			logError("Failed to select document - " + documentName + "and open Manage Links popUp" +e.getMessage());
			return false;

		}
	}
	/**
	 * This method is used to select resources tab in Manage Links PopUp
	 * @author jadhav_akan
	 * @param tabName : Name of the tab to be select
	 * @return boolean This returns true if mentioned resource category tab is selected
	 */
	public boolean selectTabInManageLinksPopUp(String tabName) {
		WebElement tab = null;
		try {
			switch (tabName) {
			case SelectTAB.CUSTOMERS : tab = CustomersManageLink;break;
			case SelectTAB.EQUIPMENT : tab = EquipmentsManageLink;break;
			case SelectTAB.ITEMS 	 : tab = ItemManageLink;break;
			case SelectTAB.SUPPLIERS : tab = SuppliersManageLink;break;
			case SelectTAB.LOCATIONS : tab = LocationsManageLink;break;
			case SelectTAB.USERS : tab = UsersManageLink;break;
			}
			controlActions.click(tab);
			logInfo("Selected resource category tab -" + tabName);
			return true;
		}
		catch(Exception e) {
			logError("Failed to switch to category tab: " + tabName
					+ e.getMessage());
			return false;
		}
	}
	
	/** This method is used to select Resource Category Tab and link the Resource
	 * @author jadhav_akan
	 * @param String - selecttab
	 * @param String - res
	 * @return boolean status
	 * IF Resource is linked THEN true ELSE false
	 */	
	public boolean selectCategorylinkResource(String selecttab, String res) {
		
		try {
			
			if(selecttab.equalsIgnoreCase("CUSTOMERS")) {
				if(!selectTabInManageLinksPopUp(SelectTAB.CUSTOMERS)) {
					return false;
				}
			}

			else if(selecttab.equalsIgnoreCase("EQUIPMENT")) {
				if(!selectTabInManageLinksPopUp(SelectTAB.EQUIPMENT)) {
					return false;
				}
			}

			else if(selecttab.equalsIgnoreCase("ITEMS")) {
				if(!selectTabInManageLinksPopUp(SelectTAB.ITEMS)) {
					return false;
				}
			}

			else if(selecttab.equalsIgnoreCase("SUPPLIERS")) {
				if(!selectTabInManageLinksPopUp(SelectTAB.SUPPLIERS)) {
					return false;
				}
			}
			
			else if(selecttab.equalsIgnoreCase("LOCATIONS")) {
				if(!selectTabInManageLinksPopUp(SelectTAB.LOCATIONS)) {
					return false;
				}
			}
			
			else if(selecttab.equalsIgnoreCase("USERS")) {
				if(!selectTabInManageLinksPopUp(SelectTAB.USERS)) {
					return false;
				}
			}

			controlActions.sendKeys(SearchManagePopup,res);
			controlActions.sendKey(SearchManagePopup,Keys.ENTER);
			threadsleep(4000);
			WebElement resNameChk = controlActions.perform_GetElementByXPath(DocumentManagementPageLocators.RES_INST_TO_LINK, "INSTANCE_NAME",res);
			controlActions.clickElement(resNameChk);
			controlActions.clickElement(SaveManageLink);
				
			logInfo("Succesfully Linked Resource - " +res+ " with Document");
			return true;
		}catch(Exception e) {
			logError("FAILED to Linked Resource - " +res+" with Document "+e.getMessage());
			return false;
		}
	}
	/**
	 * This method is used to verify that only single Document is present
	 * @author jadhav_akan
	 * @param String documentName
	 * @return boolean This returns true if single Document is found
	 */
	public boolean verifySingleDocIsPresent(String documentName) {
		try {
			
			WebElement docSelect = controlActions.perform_GetElementByXPath(DocumentManagementPageLocators.DOCUMENTNAME_COLUMNDATA,"DOCUMENT",documentName);
			if(!controlActions.isElementDisplayedOnPage(docSelect)){
				logError("Document is not present");
				return false;
			}
			if(TotalDocumentList.size()!=1) {
				logError("Getting more than 1 Document");
				return false;
			}
			logInfo("Verified only single Document is Present.");
			return true;
		}catch(Exception e) {
			logError("Failed to verify Document is present - " + e.getMessage());
			return false;
		}
	}
	

	public static class MANAGEDOCTYPE_OPTIONS{
		public static final String EDIT_NAME = "Edit Name";
		public static final String DISABLE = "Disable";
		public static final String ENABLE = "Enable";
		
	}
	
	public static class TOOLTIPOPTIONS{
		public static final String VIEWDOCUMENT = "'View Document'";
		public static final String EDITDETAILS = "'Edit Details'";
		public static final String MANAGELINKS = "'Manage Links'";
		public static final String UPLOADNEW = "'Upload New'";
		public static final String ASSIGNTASK = "'Assign Task'";
		public static final String DOWNLOAD  = "'Download'";
		public static final String PRINT = "'Print'";
		public static final String RESTORE = "'Restore'";
		public static final String DELETE = "'Delete'";
	}

	public static class SCSOPTIONS{
		public static String DROPDOWNTYPE;
		public static final String DOCUMENTTYPES = "Document Types";
		public static final String CUSTOMERS = "Customers";
		public static final String EQUIPMENT = "Equipment";
		public static final String ITEMS = "Items";
		public static final String SUPPLIERS = "Suppliers";
		public static final String DELETEDDOCUMENTS = "Deleted Documents";
		public static final String LOCATIONS = "Locations";
		public static final String USERS = "Users";
	}

		//=============== DMS _ Document Viewer ==========//
	
	public static class TABS_IN_DOC_DETAILS_SCECTION{
		public final static String DETAILS = "Details";
		public final static String HISTORY = "History";
		public final static String LINKS = "Links";
		
	}

	public static class DOC_VIEWER_TOOLBAR{
		public final static String EDIT_DETAILS = "Edit Details";
		public final static String UPLOAD_NEW_VERSION = "Upload New Version";
		public final static String ASSIGN_TASK = "Assign Task";
		public final static String DOWNLOAD = "Download";
		public final static String PRINT = "Print";
		public final static String DELETE = "Delete";
	}
	
	public static class DOC_VIEWER_DETAILS_TAB{
		public final static String DOC_NAME = "Document Name";
		public final static String FILE_NAME = "File Name";
		public final static String SIZE = "Size";
		public final static String DOC_TYPE = "Document Type";
		public final static String DESCRIPTION = "Description";
		public final static String EXP_DATE = "Expiration Date";
		public final static String STATUS = "Status";
	}
	
	public static class DOC_VIEWER_HISTORY_TAB{
		public final static String DOC_NAME = "Current Version";
		public final static String FILE_NAME = "File Name";
		public final static String MODIFIED_BY = "Modified By";
		public final static String SIZE = "Size";
		public final static String COMMENT = "Comment";
		public final static String DOC_TYPE = "Document Type";
		public final static String EXP_DATE = "Expiration Date";
	}
	
	public static class ASSIGN_TASK_FIELDS{
		public final static String TASK_NAME = "Task Name";
		public final static String LOCATION = "Location";
		public final static String ASSIGN_TO = "Assign To";
		public final static String DUE_BY = "Due By";
		public final static String NOTE = "Note";
	}

	public static class SelectTAB{
		public static final String CUSTOMERS = "Customers";
		public static final String EQUIPMENT = "Equipment";
		public static final String ITEMS = "Items";
		public static final String SUPPLIERS = "Suppliers";
		public static final String LOCATIONS = "Locations";
		public static final String USERS = "Users";
			
	}
}

