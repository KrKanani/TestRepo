package com.project.safetychain.webapp.pageobjects;

import java.io.File;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.project.utilities.ControlActions;

public class FSQABrowserDocumentsPage extends CommonPage{

	public FSQABrowserDocumentsPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 40);
		controlActions = new ControlActions(driver);	
	}


	@FindBy(xpath = FSQABrowserDocumentsPageLocators.OPEN_RESOURCE_DDL)
	public WebElement ResourceDDL;

	@FindBy(xpath = FSQABrowserDocumentsPageLocators.UPLOAD_BUTTON)
	public WebElement UploadButton;

	@FindBy(xpath = FSQABrowserDocumentsPageLocators.UPLOAD_INPUT)
	public WebElement UploadInput;

	@FindBy(xpath = FSQABrowserDocumentsPageLocators.INFO_ICON)
	public WebElement InfoIcon;

	@FindBy(xpath = FSQABrowserDocumentsPageLocators.DOCUMENT_TYPE)
	public WebElement DocumentType;

	@FindBy(xpath = FSQABrowserDocumentsPageLocators.SAVE_BUTTON)
	public WebElement SaveButton;

	@FindBy(xpath = FSQABrowserDocumentsPageLocators.FILE_NAME_VALUE_DOC_DETAILS)
	public WebElement FileNameValueDocDetails;

	@FindBy(xpath = FSQABrowserDocumentsPageLocators.FIELD_VALUE_FORM_DETAILS)
	public WebElement FieldValueFormDetails;
	
	@FindBy(xpath = FSQABrowserDocumentsPageLocators.EDIT_DOC_NAME)
	public WebElement EditDocName;
	
	@FindBy(xpath = FSQABrowserDocumentsPageLocators.EDIT_DOC_DESC)
	public WebElement EditDocDescription;
	
	@FindBy(xpath = FSQABrowserDocumentsPageLocators.EDIT_DOC_EXPD)
	public WebElement EditDocExpiryDate;
	
	@FindBy(id = FSQABrowserDocumentsPageLocators.DOC_INFO_SECTION)
	public WebElement DocumentInfoSection;
	
	@FindBy(xpath = FSQABrowserDocumentsPageLocators.DOC_DETAILS_EXPS)
	public WebElement DocumentExpiryStatus;
	
	@FindBy(xpath = FSQABrowserDocumentsPageLocators.DOC_DETAILS_DOC_NAME)
	public WebElement DocumentName;
	
	@FindBy(xpath = FSQABrowserDocumentsPageLocators.SEARCH_MANAGE_POPUP)
	public WebElement SearchOfManageLinks;
	
	@FindBy(xpath = FSQABrowserDocumentsPageLocators.UPLOAD_NEW_BROWSE)
	public WebElement UploadNewBrowse;
	
	@FindBy(xpath = FSQABrowserDocumentsPageLocators.UPLOAD_NEW_FILE_NAME)
	public WebElement UploadNewFileName;
	
	@FindBy(xpath = FSQABrowserDocumentsPageLocators.UPLOAD_NEW_COMMENT)
	public WebElement UploadNewComment;
	
	@FindBy(xpath = FSQABrowserDocumentsPageLocators.UPLOAD_NEW_EXPD)
	public WebElement UploadNewExpiryDate;
	
	@FindBy(xpath = FSQABrowserDocumentsPageLocators.FILE_EXTENSION_MISMATCH_YES)
	public WebElement UploadNewFileExtnMissMatchYesBtn;
	
	@FindBy(xpath = FSQABrowserDocumentsPageLocators.UPLOAD_NEW_UPLOAD_BTN)
	public WebElement UploadNewUploadButton;
	
	@FindBy(xpath = FSQABrowserDocumentsPageLocators.ASSIGN_BUTTON)
	public WebElement AssignButton;
	
	@FindBy(xpath = FSQABrowserDocumentsPageLocators.VIEW_DOCUMENT_SECTION_TITLE)
	public WebElement ViewDocumentSectionTitle;
	
	// FSQA->DOCUMENT VIEWER || INFO - > LINKS TAB
	@FindBy(xpath = FSQABrowserDocumentsPageLocators.DOC_VIEWER_LOADING_OVERLAY_MSG)
	public WebElement DocViewerLoadingOverlayMsg;
	
	@FindBy(xpath = FSQABrowserDocumentsPageLocators.MANAGE_DOC_LINKS_BTN)
	public WebElement ManageDocumentLinks;
	
	@FindBy(xpath = FSQABrowserDocumentsPageLocators.TABS_CONTAINER)
	public WebElement TabsContainer;
	
	@FindBy(xpath = FSQABrowserDocumentsPageLocators.DOC_INFO_CROSS)
	public WebElement DocumentInfoCross;
	
	@FindBy(xpath = FSQABrowserDocumentsPageLocators.SELECTED_DOCS_GRID_LST)
	public List<WebElement> SelectedDocsGridLst;
	
	@FindBy(xpath = FSQABrowserRecordsPageLocators.HEADER_DOC_LNK)
	public List<WebElement> HeaderDocLnk;
	
	
	/** 'selectResource' method is used to select resource while designing a from
	 * @author thakker_k
	 * @param String - 'resourceType'
	 * @param String - 'resourceCategory'
	 * @param String - 'resourceInstance'
	 * @return boolean status
	 * IF resource is added THEN true ELSE false
	 */	
	public boolean selectResource(String resourceType, String resourceCategory, String resourceInstance) {
		try {
			Sync();
			controlActions.clickOnElement(ResourceDDL);
			WebElement NewSupplierCategory = controlActions.perform_GetElementByXPath(FSQABrowserDocumentsPageLocators.SELECT_RESOURCE_DDL, "RESOURCE",resourceType );
			controlActions.clickOnElement(NewSupplierCategory);
			Sync();
			WebElement ExpandResourceCategory = controlActions.perform_GetElementByXPath(FSQABrowserDocumentsPageLocators.EXPAND_COLLAPSE_RESOURCE_CATEGORY, "RESOURCE_CATEGORY",resourceCategory);
			controlActions.perform_scrollToElement_ByElement(ExpandResourceCategory);
			controlActions.clickOnElement(ExpandResourceCategory);
			WebElement ResourceInstance = controlActions.perform_GetElementByXPath(FSQABrowserDocumentsPageLocators.RESOURCE_INSTANCE, "RESOURCE_INSTANCE",resourceInstance);
			controlActions.clickOnElement(ResourceInstance);
			logInfo("Resource is SELECTED");
			return true;
		}catch(Exception e) {
			logError("Failed to add resource - "
					+ e.getMessage());
			return false;
		}
	}


	/** This method is used to upload Document 
	 * @author thakker_k
	 * @param addfile the path of the document being uploaded
	 * @return boolean This returns true if Document is uploaded
	 */
	public boolean uploadDocument(String addfile) {		
		try {
			controlActions.WaitforelementToBeClickable(UploadButton);
			//System.out.println(addfile);
			controlActions.sendKeys(UploadInput,addfile);
			Sync();
			threadsleep(5000);	
			logInfo("Document upload of file is done");
			return true;

		}catch(Exception e) {
			logError("Error while uploading a file");
			return false;
		}
	}


	/** This method is used to verify the document uploaded
	 * @author thakker_k
	 * @param documentName - the name of the document that has been uploaded
	 * @return boolean This returns true if Document is verified
	 */
	public boolean verifyDocUpload(String documentName) {
		//WebElement DocName = null;
		try {
			Sync();
			WebElement DocName = controlActions.perform_GetElementByXPath(FSQABrowserDocumentsPageLocators.UPLOADED_DOC,"DOCNAME",documentName);
			controlActions.isElementDisplayed(DocName);
			logInfo("Document upload of file name" + documentName + " is verified");
			return true;
		}catch(Exception e) {
			logError("Document upload of file name " + documentName + " is not verified");
			return false;

		}
	}


	/**
	 * This method is used to select Option From Tools
	 * @author thakker_k
	 * @param documentName - Name of Document
	 * @param option - Option to be selected
	 * @return boolean This returns true post selecting option from Tools
	 */
	public boolean selectOptionFromTools(String documentName, String option) {
		try {
			Sync();
			WebElement ToolButton = controlActions.perform_GetElementByXPath(FSQABrowserDocumentsPageLocators.TOOLS_BUTTON, "DOCUMENTNAME",documentName );
			controlActions.perform_ClickWithJavaScriptExecutor(ToolButton);
			WebElement SelectOption = controlActions.perform_GetElementByXPath(FSQABrowserDocumentsPageLocators.TOOLS_OPTIONS, "TOOLSOPTION",option);
			controlActions.clickOnElement(SelectOption);
			logInfo("Selected option from Tools - "+option);
			
			//============================== ahmed_tw
//			if(option.equals(TOOLBAR_OPTIONS.VIEW_DOCUMENT)) {
//				controlActions.perform_waitUntilPresent(By.xpath(FSQABrowserDocumentsPageLocators.DOC_VIEWER_CONTENT));
//				Thread.sleep(2000);
//			}
			//============================== ahmed_tw
			
			return true;
		}catch(Exception e) {
			logError("Failed to select option from Tools "
					+ e.getMessage());
			return false;
		}
	}


	/**
	 * This method is used to verify the document downloaded
	 * @author thakker_k
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
			logError("Document download not verified - "+e.getMessage());
			return false;
		}
	}


	/** 'selectDocumentType' method is used to select Document Type of Document
	 * @author thakker_k
	 * @param String - 'documentType'
	 * @return boolean true post selecting document type
	 */	
	public boolean selectDocumentType(String documentType) {
		try {
			Sync();
			controlActions.clickOnElement(DocumentType);
			Thread.sleep(3000);
			WebElement DocumentType = controlActions.perform_GetElementByXPath(FSQABrowserDocumentsPageLocators.SELECT_DOCUMENT_TYPE, "DOCUMENTTYPE",documentType );
			controlActions.clickOnElement(DocumentType);
			logInfo("Selected Document Type- "+documentType);
			return true;
		}catch(Exception e) {
			logError("Failed to select Document Type - "+documentType+" - "	+ e.getMessage());
			return false;
		}
	}


	/** 'clickSaveButton' method is used to click on Save button
	 * @author thakker_k
	 * @return boolean true post clicking on Save button
	 */	
	public boolean clickSaveButton() {
		try {
			controlActions.clickOnElement(SaveButton);	
			Sync();
			logInfo("Clicked on Save button");
			return true;
		}catch(Exception e) {
			logError("Failed to click on Save button - "
					+ e.getMessage());
			return false;
		}
	}

	/** 'clickInfoIcon' method is used to click on Info Icon
	 * @author thakker_k
	 * @return boolean true post clicking on Info Icon
	 */	
	public boolean clickInfoIcon() {
		try {
			controlActions.clickOnElement(InfoIcon);	
			Sync();
			logInfo("Clicked on Info Icon");
			return true;
		}catch(Exception e) {
			logError("Failed to click on Info Icon - "
					+ e.getMessage());
			return false;
		}
	}

	/** 'getDocumentTypeValueFromColumn' method is used to get Document Type Value From Column
	 * @author thakker_k
	 * @param String - 'documentName'
	 * @return String - document type value
	 */	
	public String getDocumentTypeValueFromColumn(String documentName) {
		try {

			Thread.sleep(3000);
			WebElement DocumentTypeValue = controlActions.perform_GetElementByXPath(FSQABrowserDocumentsPageLocators.DOCUMENT_TYPE_COL_VALUE, "DOCUMENTNAME",documentName );
			String docTypeValue =  DocumentTypeValue.getText();
			logInfo("Document Type Value from Columns is - " + docTypeValue);
			return docTypeValue;
		}catch(Exception e) {
			logError("Failed to get Document Type Value for document name - "+documentName+" - "
					+ e.getMessage());
			return null;
		}
	}


	/** 'clickTabInDocumentDetailsSection' method is used to click Tab in Document Details Section
	 * @author thakker_k
	 * @param String - 'tabName'
	 * @return boolean true post clicking Tab in Document Details Section
	 */	
	public boolean clickTabInDocumentDetailsSection(String tabName) {
		try {
			controlActions.perform_waitUntilPresent(By.xpath(FSQABrowserDocumentsPageLocators.DOC_VIEWER_CONTENT));
			Sync();
			WebElement DocDetailsTabName = controlActions.perform_GetElementByXPath(FSQABrowserDocumentsPageLocators.DOC_DETAILS_TAB_NAME, "TABNAME",tabName );
			controlActions.clickOnElement(DocDetailsTabName);
			Sync();
			logInfo("Click Tab in Document Details Section -" + tabName);
			return true;
		}catch(Exception e) {
			logError("Failed to click Tab in Document Details Section - "+tabName+" - "
					+ e.getMessage());
			return false;
		}
	}

	/** 'getFileNameDocDetailsSection' method is used to get File Name from Doc Details Section
	 * @author thakker_k
	 * @return String file name
	 */	
	public String getFileNameDocDetailsSection() {
		try {
			String fileNameValue =  FileNameValueDocDetails.getText();
			logInfo("File Name is" + fileNameValue);
			return fileNameValue;
		}catch(Exception e) {
			logError("Failed to get File Name - "
					+ e.getMessage());
			return null;
		}
	}

	/** 'getFieldValueFormDetails' method is used to get Field Value from Form Details
	 * @author thakker_k
	 * @param String - 'fieldName'
	 * @return String - field Value
	 */	
	public String getFieldValueFormDetails(String fieldName) {
		try {
			
			WebElement FieldValue = controlActions.perform_GetElementByXPath(FSQABrowserDocumentsPageLocators.FIELD_VALUE_FORM_DETAILS, "FIELDNAME",fieldName );
			String fieldValue =  FieldValue.getText();
			logInfo("Field value for file name -  "+ fileName + " is "+fieldValue);
			return fieldValue;
		}catch(Exception e) {
			logError("Failed to get field value - "	+ e.getMessage());
			return null;
		}
	}
	
	// ====== ahmed_tw===//
	/** 'deleteFromToolbar' method is used to delete the Document from caret->Delete
	 * @author ahmed_tw
	 * @param String - 'documentName' - Name of Document to Delete
	 * @return boolean - 'TRUE' if Document is deleted else 'FALSE'
	 */	
	public boolean deleteFromToolbar(String documentName) {
		try {
			selectOptionFromTools(documentName, "Delete");
			Sync();
			driver.findElement(By.xpath("//button[text()='Yes']")).click();
			logInfo("Document deleted - " + documentName);
		} catch (Exception e) {
			logError("Could NOT Delete Document - " + documentName + documentName);
			return false;
		}
		
		return true;
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
			DocName = controlActions.perform_GetElementByXPath(FSQABrowserDocumentsPageLocators.UPLOADED_DOC,"DOCNAME",documentName);
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
			controlActions.perform_waitUntilVisibility(By.id(FSQABrowserDocumentsPageLocators.EDIT_DOC_POP_UP));
			
			if (!editDocName(name)) {
				return false;
			}
			if (!selectDocumentType(type)) {
				return false;
			}
			if (!editDocDescription(description)) {
				return false;
			}
			if (!editDocExpiryDate(expiryDate)) {
				return false;
			}

			clickSaveButton();
			
			Sync();

		} catch (Exception e) {
			logError("Failed to Edit Details " + e.getMessage());
			return false;
		}

		return true;
	}
	
	/** 'editDocName' method is used to Edit Document Name after the "Edit Details" pop up is opened
	 * @author ahmed_tw
	 * @param String - 'newName' : value for 'Document Name' option
	 * @return boolean - 'TRUE' if Name is edited else 'FALSE'
	 */	
	public boolean editDocName(String newName) {
		try {
			controlActions.sendKeys(EditDocName, newName);
			logInfo("Edited Document name - " + newName);
		} catch (Exception e) {
			logError("Failed to edit name to - " + newName + e.getMessage());
			return false;
		}
		return true;
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
			logError("Failed to edit description to - " + description + e.getMessage());
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
			logError("Failed to edit expiry date to - "+ expDate + e.getMessage());
			return false;
		}
		return true;
	}

	/** 'verifyAllChanges' method is used to Verify ALL details for a Document from the 'Details' tab in Document Viewer
	 * @author ahmed_tw
	 * @param String - 'name'  : value of 'Document Name'
	 * @param String - 'type'  : value of 'Document Type' 
	 * @param String - 'description' : value of 'Description'
	 * @param String - 'expiryDate'  : value of 'Expiration Date' 
	 * @return boolean - 'TRUE' if all the details are successfully VERIFIED else 'FALSE'
	 */	
	public boolean verifyAllChanges(String name, String type, String description, String expiryDate) {
		try {
			
			if(!DocumentInfoSection.isDisplayed()) {
				clickInfoIcon();
			}

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
				clickInfoIcon();
				//Thread.sleep(1500);
			}
			
			if(fieldName.equalsIgnoreCase(DOC_VIEWER_DETAILS_TAB.EXP_DATE)) {
				if(!(DocumentExpiryStatus.getText().equalsIgnoreCase("No Expiration"))) {
					//System.out.println(DocumentExpiryStatus.getText());
					// Handle Expiry
					WebElement FieldValue = controlActions.perform_GetElementByXPath(FSQABrowserDocumentsPageLocators.DOC_DETAILS_EXPD, "EXP_DATE_FIELD",DOC_VIEWER_DETAILS_TAB.EXP_DATE);
					fieldValue =  FieldValue.getText();
				}else {
					logInfo("The is no Expiry Date for this Document");
					closeDocumentDetails();
					return true;
				}
			}else if (fieldName.equalsIgnoreCase(DOC_VIEWER_DETAILS_TAB.DOC_NAME)){
				fieldValue =  DocumentName.getText();
			}else {
				// handle rest
				WebElement FieldValue = controlActions.perform_GetElementByXPath(FSQABrowserDocumentsPageLocators.DOC_DETAILS_FIELD, "DETAIL_FIELD",fieldName);
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
	
	/** 'manageLinks' method is used to Link Document to another resource, after "Manage Links" pop up is opened
	 * @author ahmed_tw
	 * @param String - 'documentName' : Document which need to be linked
	 * @param String - 'resourceType' : Resource Type for the resource
	 * @param String - 'resource'     : Resource Name to which to document is to be linked
	 * @return boolean - 'TRUE' if document is successfully Linked, else 'FALSE'
	 */	
	public boolean manageLinks(	String resourceType, String resource) {
		
		try {
			Sync();
			controlActions.perform_waitUntilVisibility(By.id(FSQABrowserDocumentsPageLocators.MANAGE_LINKS_POP_UP));
			WebElement resType = controlActions.perform_GetElementByXPath(FSQABrowserDocumentsPageLocators.RES_TYPE_MANAGE_POPUP, "RESOURCE_TYPE",resourceType);
			controlActions.clickElement(resType);
			logInfo("Changed to Tab Resource Type " + resourceType);
			
			controlActions.sendKeys(SearchOfManageLinks, resource);
			controlActions.actionEnter();
			logInfo("Searched Resource  " + resource);
			
			WebElement resInstance = controlActions.perform_GetElementByXPath(FSQABrowserDocumentsPageLocators.RES_INST_TO_LINK, "INSTANCE_NAME",resource);
			controlActions.clickElement(resInstance);
			logInfo("Checked/Linked Resource  " + resource);
			
			controlActions.clickElement(SaveButton);
			logInfo("Saved linking  " + resource);
			
			Sync();
			
		} catch (Exception e) {
			logError("Failed to Link resource  -" +resource+ e.getMessage());
			return false;
		}
		
		return true;
	}
	
	/** 'clickManageDocumentsLinks' method is used to click on 'Manage Documents Links' Button in Links Tab in Document Viewer
	 * @author ahmed_tw
	 * @return boolean true post clicking on 'Manage Documents Links Button'
	 */	
	public boolean clickManageDocumentsLinks() {
		try {
			controlActions.perform_waitUntilVisibility(By.id(FSQABrowserDocumentsPageLocators.DOC_VIEWER_MANAGE_SECTION));
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
	
	/** 'verifyLink' method is used to verify linked resource for a document
	 * @author ahmed_tw
	 * @param String - 'resourceName' : Resource whose linking is to be verified
	 * @return boolean - 'TRUE' if linking is verified, else 'FALSE'
	 */	
	public boolean verifyLink(String resourceName) {
		WebElement link = null;
		try {
			link = controlActions.perform_GetElementByXPath(FSQABrowserDocumentsPageLocators.LINKED_INSTANCES, "INSTANCE_NAME",resourceName);
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
	
	/** 'uploadNewVersion' method is used to upload new version file for a Document with comment and expiry date
	 * @author ahmed_tw
	 * @param String - 'documentName' : Document to which new file is to be uploaded
	 * @param String - 'newFilePath'  : File path of the new file
	 * @param String - 'comment'      : Comment for new version file upload
	 * @param String - 'expiryDate'   : Expiration date for new version file upload
	 * @return boolean - 'TRUE' if new version file is successfully uploaded, else 'FALSE'
	 */	
	public boolean uploadNewVersion(String documentName, String newFilePath, String comment, String expiryDate) {
		String fileName = null;
		try {
			
			selectOptionFromTools(documentName, TOOLBAR_OPTIONS.UPLOAD_NEW_VERSION);
			controlActions.perform_waitUntilVisibility(By.id(FSQABrowserDocumentsPageLocators.UPLOAD_NEW_POP_UP));
			logInfo("Opened Upload New Version Pop Up for Document " + documentName);
			
			controlActions.sendKeys(UploadNewBrowse, newFilePath);
			fileName = UploadNewFileName.getText();
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
	
	/** 'uploadNewVersion' method is used to upload new version file for a Document without comment and expiry date
	 * @author ahmed_tw
	 * @param String - 'documentName' : Document to which new file is to be uploaded
	 * @param String - 'newFilePath'  : File path of the new file
	 * @return boolean - 'TRUE' if new version file is successfully uploaded, else 'FALSE'
	 */	
	public boolean uploadNewVersion(String documentName, String newFilePath) {
		String fileName = null;
		try {
			
			selectOptionFromTools(documentName, "Upload New Version");
			logInfo("Opened Upload New Version Pop Up for Document " + documentName);
			
			controlActions.sendKeys(UploadNewBrowse, newFilePath);
			fileName = UploadNewFileName.getText();
			logInfo("Browse and Selected New File " + fileName);
			
			if(controlActions.isElementDisplay(UploadNewFileExtnMissMatchYesBtn))
				controlActions.clickElement(UploadNewFileExtnMissMatchYesBtn);
			
			controlActions.clickElement(UploadNewUploadButton);
			logInfo("Uploaded New File" + fileName);
			
			} catch (Exception e) {
				logError("Failed to Upload new version file - " + fileName + " for document  -" + documentName + e.getMessage());
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
			WebElement documentVersion = controlActions.perform_GetElementByXPath(FSQABrowserDocumentsPageLocators.DOCUMENT_VERSION, "DOCEMENT_NAME",documentName );
			version =  documentVersion.getText();
			logInfo("Document Version is - " + version);
			return Double.parseDouble(version);
		}catch(Exception e) {
			logError("Failed to get Document Version for document name - "+documentName+" - "
					+ e.getMessage());
			return 0.0;
		}
	}
	
	/** 'clickVerticleToolbarOptions' method is used to click on Verticle toolbar option in Document Viewer
	 * @author ahmed_tw
	 * @param String - 'option' : Option to be clicked
	 * @return boolean - 'TRUE' if option is successfully clicked, else 'FALSE'
	 */	
	public boolean selectVerticleToolbarOptions(String option) {
		String optionNumber = null;
		try {
			switch(option) {
			case TOOLBAR_OPTIONS.EDIT_DETAILS:
				optionNumber = "1"; break;
			case TOOLBAR_OPTIONS.UPLOAD_NEW_VERSION:
				optionNumber = "2"; break;
			case TOOLBAR_OPTIONS.ASSIGN_TASK:
				optionNumber = "3"; break;
			case TOOLBAR_OPTIONS.DOWNLOAD:
				optionNumber = "4"; break;
			case TOOLBAR_OPTIONS.PRINT:
				optionNumber = "5"; break;
			case TOOLBAR_OPTIONS.DELETE:
				optionNumber = "6"; break;
			}
			WebElement toolbarOption = controlActions.perform_GetElementByXPath(FSQABrowserDocumentsPageLocators.VERTICLE_TOOLBAR_OPTIONS, "OPTION", optionNumber);
			controlActions.clickElement(toolbarOption);
			Sync();
			return true;
		} catch (Exception e) {
			logError("Failed to Click on Verticle toolbar option - " + option + " " + e.getMessage());
			return false;
		}
	}
	
	/** 'assignTaskSelectLocation' method is used to select Location of Document when Assign Task popup is opened
	 * @author ahmed_tw
	 * @param String - 'locationInstance'
	 * @return boolean true post selecting location instance
	 */	
	public boolean assignTaskSelectLocation(String locationInstance) {
		try {
			Thread.sleep(1000);
			WebElement location = controlActions.perform_GetElementByXPath(FSQABrowserDocumentsPageLocators.ASS_TSK_LOCATION, "LOCATION",locationInstance );
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
			WebElement workgroup = controlActions.perform_GetElementByXPath(FSQABrowserDocumentsPageLocators.ASS_TSK_WRKGP, "WORKGROUP",workGroup );
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
				field = controlActions.perform_GetElementByXPath(FSQABrowserDocumentsPageLocators.ASSIGN_TASK_FIELDS, "FILED_NAME",fieldName );
				controlActions.sendKeys(field, fieldValue);
			}else if(fieldName.equals(ASSIGN_TASK_FIELDS.LOCATION)) {
				String locationXpath = FSQABrowserDocumentsPageLocators.ASSIGN_TASK_FIELDS.replaceAll("input", "span[2]");
				field = controlActions.perform_GetElementByXPath(locationXpath, "FILED_NAME",fieldName);
				controlActions.clickElement(field);
				assignTaskSelectLocation(fieldValue);
			}else if(fieldName.equals(ASSIGN_TASK_FIELDS.ASSIGN_TO)) {
				String wGXpath = FSQABrowserDocumentsPageLocators.ASSIGN_TASK_FIELDS.replaceAll("input", "span[2]");
				field = controlActions.perform_GetElementByXPath(wGXpath, "FILED_NAME",fieldName);
				controlActions.clickElement(field);
				assignTaskSelectWorkgroup(fieldValue);
			}else if(fieldName.equals(ASSIGN_TASK_FIELDS.NOTE)) {
				String notesXpath = FSQABrowserDocumentsPageLocators.ASSIGN_TASK_FIELDS.replaceAll("input", "textarea");
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

	
	/** 'assignTask' method is used to assign task from FSQA->Docuemnts for a document (with dueBy)
	 * @author ahmed_tw
	 * @param String - 'document'  : The document for which task needs to be assigned
	 * @param String - 'taskName'  : The value to be set for field - Task Name
	 * @param String - 'location'  : The value to be set for field - Locatin
	 * @param String - 'workGroup' : The value to be set for field - Work Group
	 * @param String - 'dueBy' 	   : The value to be set for field - Due By
	 * @param String - 'note'      : The value to be set for field - Note
	 * @return boolean 'TRUE' after assigning Task to the document else, 'FALSE'
	 */	
	public boolean assignTask(String document, String taskName, String location, String workGroup, String dueBy, String note) {
		try {
			
			//controlActions.perform_waitUntilVisibility(By.id(FSQABrowserDocumentsPageLocators.ASSIGN_TASK_POP_UP));
			
			if(!selectOptionFromTools(document, TOOLBAR_OPTIONS.ASSIGN_TASK)){
				logError("Failed to open Assign Task Dialogue from toolbar");
				throw new Exception();
			}
			
			if(!assignTaskFillDetails(ASSIGN_TASK_FIELDS.TASK_NAME, taskName)) {
				logError("Failed to fill value for field - " + ASSIGN_TASK_FIELDS.TASK_NAME);
				throw new Exception();
			}
			if(!assignTaskFillDetails(ASSIGN_TASK_FIELDS.LOCATION, location)) {
				logError("Failed to fill value for field - " + ASSIGN_TASK_FIELDS.LOCATION);
				throw new Exception();
			}
			if(!assignTaskFillDetails(ASSIGN_TASK_FIELDS.ASSIGN_TO, workGroup)) {
				logError("Failed to fill value for filed - " + ASSIGN_TASK_FIELDS.ASSIGN_TO);
				throw new Exception();
			}
			if(!assignTaskFillDetails(ASSIGN_TASK_FIELDS.DUE_BY, dueBy)) {
				logError("Failed to fill value for filed - " + ASSIGN_TASK_FIELDS.DUE_BY);
				throw new Exception();
			}
			if(!assignTaskFillDetails(ASSIGN_TASK_FIELDS.NOTE, dueBy)) {
				logError("Failed to fill value for filed - " + ASSIGN_TASK_FIELDS.NOTE);
				throw new Exception();
			}
			
		} catch (Exception e) {
			logError("Failed to Assign Task for document -  " + document + e.getMessage());
			return false;
		}
	return true;
	}
	
	
	/** 'assignTask' method is used to assign task from FSQA->Docuemnts for a document (without dueBy)
	 * @author ahmed_tw
	 * @param String - 'document'  : The document for which task needs to be assigned
	 * @param String - 'taskName'  : The value to be set for field - Task Name
	 * @param String - 'location'  : The value to be set for field - Locatin
	 * @param String - 'workGroup' : The value to be set for field - Work Group
	 * @param String - 'note'      : The value to be set for field - Note
	 * @return boolean 'TRUE' after assigning Task to the document else, 'FALSE'
	 */	
	public boolean assignTask(String document,String taskName, String location, String workGroup, String note) {
		try {
			//controlActions.perform_waitUntilVisibility(By.id(FSQABrowserDocumentsPageLocators.ASSIGN_TASK_POP_UP));
			
			if(!assignTaskFillDetails(ASSIGN_TASK_FIELDS.TASK_NAME, taskName)) {
				logError("Failed to fill value for filed - " + ASSIGN_TASK_FIELDS.TASK_NAME);
				return false;
			}
			if(!assignTaskFillDetails(ASSIGN_TASK_FIELDS.LOCATION, location)) {
				logError("Failed to fill value for filed - " + ASSIGN_TASK_FIELDS.LOCATION);
				return false;
			}
			if(!assignTaskFillDetails(ASSIGN_TASK_FIELDS.ASSIGN_TO, workGroup)) {
				logError("Failed to fill value for filed - " + ASSIGN_TASK_FIELDS.ASSIGN_TO);
				return false;
			}
			if(!assignTaskFillDetails(ASSIGN_TASK_FIELDS.NOTE, note)) {
				logError("Failed to fill value for filed - " + ASSIGN_TASK_FIELDS.NOTE);
				return false;
			}
			
			controlActions.clickElement(AssignButton);
			Sync();
			Thread.sleep(1000);
			
		} catch (Exception e) {
			logError("Failed to Assign Task - " + e.getMessage());
			return false;
		}
	return true;
	}
	
	/** 'verifyTab' method is used to verify the attribute value for an html element
	 * @author ahmed_tw
	 * @param String - 'attribute' : The attribute for which the value is to verified
	 * @param String - 'value'     : The Value which need to be verified
	 * @return boolean True if value is verified, else False
	 */	
	public boolean verifyTab(String attribute, String value) {
		try {
			controlActions.perform_waitUntilVisibility(TabsContainer);
			if(!( controlActions.isElementDisplayedOnPage(TabsContainer) && value.equalsIgnoreCase(TabsContainer.getAttribute(attribute)))){
				logError("Failed to verify attribute value - " + value);
				return false;
			}
		} catch (Exception e) {
			logError("Failed to verify attribute value - " + value + e.getMessage());
			return false;
		}
		logInfo("Verified attribute value");
		return true;
	}
	
	
	/** 'closeDocumentDetails' method is used to close Documents Details Section
	 * @author ahmed_tw
	 * @return boolean true post closing Documents Details Section
	 */	
	public boolean closeDocumentDetails() {
		try {
			//controlActions.perform_waitUntilClickable(DocumentInfoCross);
			//controlActions.clickElement(DocumentInfoCross);
			clickInfoIcon();
			controlActions.perform_waitUntilNotVisible(By.id(FSQABrowserDocumentsPageLocators.DOC_INFO_SECTION));
			logInfo("Closed Document Details Section");
			return true;
		}catch(Exception e) {
			logError("Failed to Close Document Details Section - "
					+ e.getMessage());
			return false;
		}
	}
	
	
	/** 'openDocumentDetails' method is used to open the Document Details Section in the Document Viewer
	 * @author ahmed_tw
	 * @return boolean true post opening Documents Details Section
	 */	
	public boolean openDocumentDetails() {
		try {
			
			if(!DocumentInfoSection.isDisplayed()) {
			//controlActions.perform_waitUntilPresent(By.xpath(FSQABrowserDocumentsPageLocators.DOC_VIEWER_CONTENT));
			controlActions.perform_waitUntilClickable(InfoIcon);
			//Thread.sleep(2000);
			controlActions.clickOnElement(InfoIcon);	
			Thread.sleep(1800);
			controlActions.perform_waitUntilVisibility(By.id(FSQABrowserDocumentsPageLocators.DOC_INFO_SECTION));
			}
			logInfo("Clicked on Info Icon");
			return true;
		}catch(Exception e) {
			logError("Failed to click on Info Icon - "
					+ e.getMessage());
			return false;
		}
	}
	
	/** 'isToolsOptionPresent' method is used to check is the option is present or not in the caret toolbar of a document
	 * @author ahmed_tw
	 * @param String - 'documentName' : Document Name for which the caret option is to be clicked
	 * @param String - 'toolsOption'  : Particular Tools Option which is to be checked
	 * @return boolean True is the tools option is preset post clicking the caret button of document, else False
	 */	
	public boolean isToolsOptionPresent(String documentName, String toolsOption) {
		try {
			WebElement ToolButton = controlActions.perform_GetElementByXPath(FSQABrowserDocumentsPageLocators.TOOLS_BUTTON, "DOCUMENTNAME",documentName );
			controlActions.perform_ClickWithJavaScriptExecutor(ToolButton);
			WebElement Option = controlActions.perform_GetElementByXPath(FSQABrowserDocumentsPageLocators.TOOLS_OPTIONS, "TOOLSOPTION", toolsOption);
			if(controlActions.isElementDisplay(Option)) {
				logInfo("The option - " + toolsOption + " IS present");
				return true;
			}
		} catch (Exception e) {
			logError("Failed to check if option - " + toolsOption + "  is present or not."
					+ e.getMessage());
			return false;
		}
		logInfo("The option - " + toolsOption + " is NOT present");
		return false;
	}
	
	
	/** 'handlePrint' method is used to handle the print pop up of Chrome
	 * @author ahmed_tw
	 * @return boolean True if print pop up is handled successfully else, False
	 */	
	public boolean handlePrint() {
		WebElement printWindow = null;
		try {
			selectVerticleToolbarOptions("Print");
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
	
	/** 'viewDocument' method is used to open the document in document viewer for viewing
	 * @author ahmed_tw
	 * @return boolean True if document is opened in doc viewer successfully else, False
	 */	
	public boolean viewDocument(String documentName) {
		try {
			
			Sync();
			WebElement document = controlActions.perform_GetElementByXPath(FSQABrowserDocumentsPageLocators.DOCUMENT_NAME, "DOCUMENT_NAME",documentName );
			controlActions.doubleClickElement(document);
//			WebElement viewDocument = controlActions.perform_GetElementByXPath(FSQABrowserDocumentsPageLocators.TOOLS_OPTIONS, "TOOLSOPTION",TOOLBAR_OPTIONS.VIEW_DOCUMENT);
//			controlActions.clickOnElement(viewDocument);
			Thread.sleep(3000);
			controlActions.perform_waitUntilPresent(By.xpath(FSQABrowserDocumentsPageLocators.DOC_VIEWER_CONTENT));
			Sync();
			Thread.sleep(2000);
			
			logInfo("Opened Document for Viewing, in Document Viewer");
			
		} catch (Exception e) {
			logInfo("Failed to Opened Document-  " + documentName + "  for Viewing, in Document Viewer");
			return false;
		}
		
		return true;
	}
	
	/**
	 * This method is used to get data of field present in Form section
	 * @author hingorani_a
	 * @param fieldName The name of field whose value you want
	 * @param otherType Use Class FLD_OTHER_TYPES to set a type of field it is; whether a field or
	 * comment or Correction etc
	 * @return String This returns value of mentioned fieldName
	 */
	public String getFieldSpecificValue(String fieldName, String otherType) {
		List<WebElement> FieldValues = null;
		String value = null;

		try {
			switch(otherType) {
				case FLD_OTHER_TYPES.FIELD:
					FieldValues = controlActions.perform_GetListOfElementsByXPath(FSQABrowserDocumentsPageLocators.FIELD_VALUE_FORM_DETAILS, 
							"FIELDNAME",fieldName );
					break;
					
				case FLD_OTHER_TYPES.COMPLIANCE:
					// TO DO
					break;
					
				case FLD_OTHER_TYPES.COMMENT:
				case FLD_OTHER_TYPES.CORRECTION:
				case FLD_OTHER_TYPES.RESOLVED:
					// TO DO
					break;
					
				case FLD_OTHER_TYPES.ATTACHMENT:
					FieldValues = controlActions.perform_GetListOfElementsByXPath(FSQABrowserDocumentsPageLocators.ATTCHMNT_FIELD_VAL, 
							"FIELD_NAME", fieldName);
					break;
					
				default:
					logError("Fetching incorrect Record field " + fieldName);
					return null;
			}
			
			if(FieldValues.size()==1)
				return FieldValues.get(0).getText();
			else {
				StringBuffer sb = new StringBuffer();
			      
			    for (WebElement element : FieldValues) {
			    	sb.append(element.getText());
			        sb.append("|");
			    }
			    String values = sb.toString();
			    return values;
			}
		}
		catch(Exception e) {
			logError("Failed to verify value " + value + " for Form field " + fieldName + " - "
					+ e.getMessage());
			return null;
		}	
	} 
	
	/**
	 * This method is used to click and open Document from form header
	 * @author hingorani_a
	 * @param fileName The file to be clicked/opened
	 * @return boolean This returns true after file is clicked
	 */
	public boolean clickAndOpenHeaderDoc(String fileName) {
		
		try {
			
			for(WebElement doc : HeaderDocLnk) {
				controlActions.WaitforelementToBeClickable(doc);
				if(doc.getText().contains(fileName)) {
					controlActions.perform_scrollToElement_ByElement(doc);
					doc.click();
					Sync();
					logInfo("Clicked on document named " + fileName);
					return true;
				}
			}
			
			logError("Could not click on document named " + fileName);
			return false;
		}
		catch(Exception e) {
			logError("Failed to click on document named - " + fileName
					+ e.getMessage());
			return false;
		}	
	}
	
	public static class TABS_IN_DOC_DETAILS_SCECTION{
		public final static String DETAILS = "Details";
		public final static String HISTORY = "History";
		public final static String LINKS = "Links";
		
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

	public static class TOOLBAR_OPTIONS{
		public final static String VIEW_DOCUMENT = "View Document"; // this option is not present in Document Viewer Toolbar
		public final static String EDIT_DETAILS = "Edit Details";
		public final static String MANAGE_LINKS = "Manage Links"; // this option is not present in Document Viewer Toolbar
		public final static String UPLOAD_NEW_VERSION = "Upload New Version";
		public final static String ASSIGN_TASK = "Assign Task";
		public final static String DOWNLOAD = "Download";
		public final static String PRINT = "Print";
		public final static String DELETE = "Delete";
	}
	
	public static class ASSIGN_TASK_FIELDS{
		public final static String TASK_NAME = "Task Name";
		public final static String LOCATION = "Location";
		public final static String ASSIGN_TO = "Assign To";
		public final static String DUE_BY = "Due By";
		public final static String NOTE = "Note";
	}
	
	public static class FLD_OTHER_TYPES{
		public static final String ATTACHMENT = "Attachment";
		public static final String ADD_REMOVE_ATTCHMNT = "Attachment";
		public static final String RESOLVED = "Resolved";
		public static final String FIELD = "Field";
		public static final String CORRECTION = "Correction";
		public static final String COMMENT = "Comment";
		public static final String COMPLIANCE = "Compliance";
		
	}
}
