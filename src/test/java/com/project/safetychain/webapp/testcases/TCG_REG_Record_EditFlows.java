package com.project.safetychain.webapp.testcases;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.project.safetychain.webapp.pageobjects.DocumentManagementPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserRecordsPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserRecordsPageLocators;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage;
import com.project.safetychain.webapp.pageobjects.HomePage;
import com.project.safetychain.webapp.pageobjects.LoginPage;
import com.project.safetychain.webapp.pageobjects.ManageLocationPage;
import com.project.safetychain.webapp.pageobjects.ManageResourcePage;
import com.project.safetychain.webapp.pageobjects.ResourceDesignerPage;
import com.project.safetychain.webapp.pageobjects.UserManagerPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.COLUMNHEADER;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.FSQARESOURCE;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.FSQATAB;
import com.project.safetychain.webapp.pageobjects.FSQABrowserRecordsPage.FIELDTYPE;
import com.project.safetychain.webapp.pageobjects.FSQABrowserRecordsPage.FIELD_OTHER_TYPES;
import com.project.safetychain.webapp.pageobjects.FSQABrowserRecordsPage.HISTORY_EDIT_TYPES;
import com.project.safetychain.webapp.pageobjects.FSQABrowserRecordsPage.HistDetailedInfo;
import com.project.safetychain.webapp.pageobjects.FSQABrowserRecordsPage.HistoryDetails;
import com.project.safetychain.webapp.pageobjects.FSQABrowserRecordsPage.RecFieldProps;
import com.project.safetychain.webapp.pageobjects.FSQABrowserRecordsPage.UpdateFieldDets;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.ElementProperties;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FIELD_TYPES;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FORMRESOURCETYPES;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FORMTYPES;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FormDesignParams;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FormFieldParams;
import com.project.safetychain.webapp.pageobjects.ManageLocationPage.LocationInstanceParams;
import com.project.safetychain.webapp.pageobjects.ManageResourcePage.RESOURCETYPES;
import com.project.safetychain.webapp.pageobjects.ManageResourcePage.ResourceDetailParams;
import com.project.safetychain.webapp.pageobjects.ResourceDesignerPage.CATEGORYTYPES;
import com.project.safetychain.webapp.pageobjects.UserManagerPage.USERFIELDS;
import com.project.safetychain.webapp.testcases.support.FormOperations;
import com.project.safetychain.webapp.testcases.support.FormOperations.FieldProperties;
import com.project.safetychain.webapp.testcases.support.FormOperations.FormDetails;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.utilities.CommonMethods;
import com.project.utilities.ControlActions;
import com.project.utilities.ControlActions.WINDOW_TAB;
import com.project.utilities.DateTime;


public class TCG_REG_Record_EditFlows extends TestBase{
	
	ControlActions controlActions;
	public static String formName;
	public static String voidRecCmmt;
	public static String locationCategoryValue;	
	public static String locationInstanceValue;	
	public static String equipmentCategoryValue;
	public static String equipmentInstanceValue;
	public static String signOffCmmt;
	public static String docTypeName;
	public static String displayName = null;
	public static String usrTimezone = null;
	public static String timezoneCode = null;
	public static String numFieldName = "Numeric";
	public static String documentName1 = "ABC1.csv";
	public static String documentName2 = "upload.png";
	public static String documentName3 = "Sample - Records.pdf";
	public static String newAttachmentName = "uploadnew.jpg";
	public static String submitRecordCmmt = "update existing record";
	public static String showMoreMsg = "show more";
	public static String showLessMsg = "show less";
	public static String filePath1 = System.getProperty("user.dir") + "\\test-data-files\\UploadDocuments\\"+documentName1;
	public static String filePath2 = System.getProperty("user.dir") + "\\test-data-files\\UploadDocuments\\"+documentName2;
	public static String docFilePath = System.getProperty("user.dir") + "\\test-data-files\\UploadDocuments\\"+documentName3;
	public static String attachmentFilePath = System.getProperty("user.dir") + "\\test-data-files\\UploadDocuments\\"+newAttachmentName;
	public static String fieldMin = "1", fieldTar = "2", fieldMax = "3", fieldUOM = "KG";
	public static String newNumFieldValue, newCommentValue, newCorrectionValue, newAttachmentValue, newResolveValue;
	public static String oldNumFieldValue, oldCommentValue, oldCorrectionValue, oldAttachmentValue, oldResolveValue;
	public static String currentDate;
	public static String downloadPath = System.getProperty("user.dir") + "\\test-data-files\\DownloadedDocuments\\";

	@BeforeClass(alwaysRun = true)
	public void groupInit() throws InterruptedException {

		formName = CommonMethods.dynamicText("RViewer");
		locationCategoryValue = CommonMethods.dynamicText("Loc_Cat");	
		locationInstanceValue = CommonMethods.dynamicText("Loc_Inst");	
		equipmentCategoryValue = CommonMethods.dynamicText("Eqp_Cat");
		equipmentInstanceValue = CommonMethods.dynamicText("Eqp_Inst");
		signOffCmmt = CommonMethods.dynamicText("Cmmt1");
		docTypeName = CommonMethods.dynamicText("RDoc");

		driver = launchbrowser();
		controlActions = new ControlActions(driver);
		controlActions.getUrl(applicationUrl);
		
		LoginPage lp = new LoginPage(driver);
		HomePage hp = lp.performLogin(adminUsername, adminPassword);
		if(hp.error) {
			TCGFailureMsg = "Could NOT login to application";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
		
		displayName = hp.getLoggedInUserDetails();
		if(displayName == ""){
			TCGFailureMsg = "Could NOT get display name for user - " + adminUsername;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
		
		UserManagerPage ump = hp.clickUsersMenu();
		if(!ump.searchUsrWithDetails(USERFIELDS.USERNAME, adminUsername)) {
			TCGFailureMsg = "Failed to search user - " + adminUsername;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
		
		usrTimezone = ump.getUsrDetails(USERFIELDS.TIMEZONE);
		if(usrTimezone == ""){
			TCGFailureMsg = "Failed to get timezone for user - " + adminUsername;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
		
		timezoneCode = ump.getTimezoneCode(usrTimezone);
		if(timezoneCode == ""){
			TCGFailureMsg = "Failed to convert timezone - " + usrTimezone+ " for user - " + adminUsername;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
		
		hp.clickFSQABrowserMenu();
		//Category creation
		HashMap<String,String> categories = new HashMap<String, String>();
		categories.put(CATEGORYTYPES.EQUIPMENT, equipmentCategoryValue);
		categories.put(CATEGORYTYPES.LOCATION, locationCategoryValue);
		ResourceDesignerPage rdp = hp.clickResourceDesignerMenu();
		if(!rdp.createCategory(categories)) {
			TCGFailureMsg = "Could NOT create Resource categories";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		//Location instance
		HashMap<String,Boolean> locInstance = new HashMap<String, Boolean>();
		locInstance.put(locationInstanceValue, true);
		LocationInstanceParams lip = new LocationInstanceParams();
		lip.CategoryName = locationCategoryValue;
		lip.NumberFieldValue = 10;
		lip.TextFieldValue = "XYZ";
		lip.InstanceName = locInstance;
		ManageLocationPage mlp = hp.clickLocationsMenu();
		if(!mlp.createLocation(lip)) {
			TCGFailureMsg = "Could NOT create Location instance";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		//Resource creation
		HashMap<String,Boolean> instances = new HashMap<String, Boolean>();
		instances.put(equipmentInstanceValue, true);
		ResourceDetailParams rd = new ResourceDetailParams();
		rd.CategoryName = equipmentCategoryValue;
		rd.CategoryType = RESOURCETYPES.EQUIPMENT;
		rd.NumberFieldValue = 30;
		rd.TextFieldValue = "ABC";
		rd.InstanceNames = instances;
		rd.LocationInstanceValue = locationInstanceValue;	
		ManageResourcePage mrp = hp.clickResourcesMenu();
		if(!mrp.createResourceLinkLocation(rd)) {
			TCGFailureMsg = "Could NOT create Instances for resource - " + equipmentCategoryValue;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
		
		DocumentManagementPage dmp = hp.clickdocumentsmenu();
		if(dmp.error) {
			TCGFailureMsg = "Could NOT open 'Documents'";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
		
		if(!dmp.docUploadinDocType(docTypeName,docFilePath)) {
			TCGFailureMsg = "Could NOT add document type";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		//FORM - Creation and Release
		HashMap<String, List<String>> fields = new HashMap<String, List<String>>();
		fields.put(FIELD_TYPES.NUMERIC, Arrays.asList(numFieldName));

		FormFieldParams ffp = new FormFieldParams();
		ffp.FieldDetails = fields;

		HashMap<String, List<String>> resource = new HashMap<String, List<String>>();
		resource.put(FORMRESOURCETYPES.EQUIPMENT, Arrays.asList(equipmentCategoryValue));

		FormDesignParams fdpDets = new FormDesignParams();
		fdpDets.FormType = FORMTYPES.CHECK;
		fdpDets.FormName = formName;
		fdpDets.SelectResources = resource;
		fdpDets.DesignFields = Arrays.asList(ffp);
		fdpDets.ReleaseForm = false;

		FormDesignerPage fdp = hp.clickFormDesignerMenu();
		if(!fdp.createAndReleaseForm(fdpDets)) {
			TCGFailureMsg = "Could NOT create unreleased form - " + formName;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
		
		if (!fdp.navigateToReleaseForm_EditForm(formName)) {
			TCGFailureMsg = "Could NOT open edit form - " + formName;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
		
		if(!fdp.addRelatedDocument(docTypeName, documentName3)) {
			TCGFailureMsg = "Could NOT add document 'Related Docs'";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		ElementProperties settings = new ElementProperties();
		settings.COMPLIANCE_CONFIG = true;
		settings.COMPLIANCE_VALUES = new String[] { fieldMin, fieldTar, fieldMax, null };
		settings.ALLOW_CORRECTION = true;
		settings.MARK_RESOLVED = true;
		settings.ALLOW_ATTATHMENTS = true;
		settings.ALLOW_COMMENTS = true;
		
		HashMap<String, ElementProperties> fieldSettings = new HashMap<String, ElementProperties>();
		fieldSettings.put(numFieldName, settings);

		if (!fdp.setFieldProperties(fieldSettings)) {
			TCGFailureMsg = "Could NOT set compliance properties for field - " + numFieldName;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		if (!fdp.releaseForm(formName)) {
			TCGFailureMsg = "Could NOT release form - " + formName;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
		
		FSQABrowserPage fbp = hp.clickFSQABrowserMenu();
		if(!fbp.selectResourceDropDownandNavigate(FSQARESOURCE.EQUIPMENT,FSQATAB.FORMS)) {
			TCGFailureMsg = "For equipment category, could NOT navigate to FSQABrowser > Forms tab";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
	
		if(!fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, formName)) {
			TCGFailureMsg = "Could not filter form - " + formName;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
		
		HashMap<String, List<String>> fillData = new HashMap<String, List<String>>();
		fillData.put(numFieldName,Arrays.asList("5"));
		
		//Field Property objects
		FieldProperties fp = new FieldProperties();
		fp.fieldName = numFieldName;
		fp.allowCorrectionsCheck = true;
		fp.allowCorrectionsValue = "Test Value";
		fp.isResolvedCheck = true;
		fp.isResolvedStatus = "Yes";
		fp.allowAttachmenstCheck = true;
		fp.uploadFilePath = filePath2;
		fp.complianceStatusCheck = true;
		fp.complianceStatus = "Non-Compliant";
		fp.allowCommentsCheck = true;
		fp.commentText = "Comment";

		//Form object
		FormDetails fd = new FormDetails();
		//Form level attachment
		fd.formLevelAttachmenttCheck = true;
		fd.uploadFormLevelFilePath = filePath1; 
		fd.fieldDetails = fillData;
		//Field property assignment  
		fd.fieldProperties = Arrays.asList(fp);
		FormOperations fo = new FormOperations(driver);
		if(!fo.submitData(fd)) {
			TCGFailureMsg = "Could NOT enter data and submit form for second time " + formName;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);			
		}
		
	}

	@Test(description="12235 : Multi-Record Signoff - Records Management - Record History - An event is shown "
			+ "in the Record History whenever a record is edited")
	public void TestCase_16360() throws Exception{
		
		HomePage hp = new HomePage(driver);	
		FSQABrowserPage fbp = hp.clickFSQABrowserMenu();
		boolean navigateToRecords = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.EQUIPMENT,FSQATAB.RECORDS);
		TestValidation.IsTrue(navigateToRecords, 
							  "For equipment category, NAVIGATED to FSQABrowser > Records tab", 
							  "Failed to navigate to FSQABrowser > Records tab");
		
		boolean openRecord = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, formName);
		TestValidation.IsTrue(openRecord, 
				 			  "OPENED record with form name - " + formName, 
				 			  "Failed to open record with form name - " + formName);
		
		FSQABrowserRecordsPage frp = new FSQABrowserRecordsPage(driver);
		oldNumFieldValue = frp.getFieldSpecificValue(numFieldName,FIELD_OTHER_TYPES.FIELD);
		TestValidation.IsTrue((oldNumFieldValue!="" || oldNumFieldValue!=null), 
							  "Retrieved value for the field " + numFieldName + " as " + oldNumFieldValue, 
							  "Failed to retrieve value for the field " + numFieldName );
		
		boolean editRecord = frp.clickEditRecordBtn();
		TestValidation.IsTrue(editRecord, 
							  "CLICKED on edit record", 
							  "Failed to click on edit");
		
		newNumFieldValue = "101";
		boolean updateValue = frp.updateFieldValues(numFieldName, FIELDTYPE.NUMERIC, newNumFieldValue, false);
		TestValidation.IsTrue(updateValue, 
			  	  			  "UPDATED value for field type Numeric as " + newNumFieldValue, 
				  			  "Failed to update value for field type Numeric as " + newNumFieldValue);
		
		boolean submitRecord = frp.submitRecord(submitRecordCmmt);
		TestValidation.IsTrue(submitRecord, 
	  			  			  "SUBMITTED record with comment " + submitRecordCmmt, 
	  			  			  "Failed to submit record with comment " + submitRecordCmmt);

		DateTime dt = new DateTime(driver);
		currentDate = dt.getTodayDate("MM/dd/YYYY", dt.getTimezone(usrTimezone));
		
		HashMap<String, String> histDetail = new HashMap<String, String>();
		histDetail.put(HISTORY_EDIT_TYPES.RECORD_EDITED,
				HISTORY_EDIT_TYPES.RECORD_EDITED + "|" + displayName + "|" + timezoneCode + "|" + currentDate);
		
		HistDetailedInfo hdi = new HistDetailedInfo();
		hdi.editType = FIELD_OTHER_TYPES.FIELD;
		hdi.fieldName = numFieldName;
		hdi.newFieldValue = newNumFieldValue;
		hdi.oldFieldValue = oldNumFieldValue;
		
		HistoryDetails hd = new HistoryDetails();
		hd.HeaderDetail = histDetail;
		hd.detailedInfoList = Arrays.asList(hdi);
		
		boolean verifyHistory = frp.verifyHistoryPopupDetails(hd);	
		TestValidation.IsTrue(verifyHistory, 
				  			  "VERIFIED the changes for " + numFieldName + " field updated with value " + newNumFieldValue , 
				  			  "Failed to verify changes for " + numFieldName + " field");
	}
	
	@Test(description="12236 : Multi-Record Signoff - Records Management - Record History - An event is "
			+ "shown in the Record History whenever a record edit changes are accepted",
			dependsOnMethods = { "TestCase_16360" })
	public void TestCase_16361() throws Exception{
		
		HomePage hp = new HomePage(driver);	
		FSQABrowserPage fbp = hp.clickFSQABrowserMenu();
		boolean navigateToRecords = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.EQUIPMENT,FSQATAB.RECORDS);
		TestValidation.IsTrue(navigateToRecords, 
							  "For equipment category, NAVIGATED to FSQABrowser > Records tab", 
							  "Failed to navigate to FSQABrowser > Records tab");
		
		boolean selectAllRecord = fbp.clickSelectAllChk();
		TestValidation.IsTrue(selectAllRecord, 
				 			  "SELECTED all records in grid", 
				 			  "Failed to select all records in grid");
		
		FSQABrowserRecordsPage frp = fbp.clickViewRecordsBtn();
		TestValidation.Equals(false,
							  frp.error, 
							  "For customer category, NAVIGATED to FSQABrowser > Records tab", 
							  "Failed to navigate to FSQABrowser > Records tab");
		
		boolean selectByFormNm = frp.searchAndSelectRecordInViewSignMode(formName);
		TestValidation.IsTrue(selectByFormNm, 
				  			  "SELECTED record for form - " + formName + " using name - " + displayName, 
				  			  "Failed to select record for form - " + formName);
		
		HashMap<String, String> histDetail = new HashMap<String, String>();
		histDetail.put(null,
				HISTORY_EDIT_TYPES.CHANGES_ACCEPTED + "|" + displayName + "|" + timezoneCode + "|" + currentDate + "|" 
				+ submitRecordCmmt);
		
		HistoryDetails hd = new HistoryDetails();
		hd.HeaderDetail = histDetail;
		
		boolean verifyHistory = frp.verifyHistoryPopupDetails(hd);	
		TestValidation.IsTrue(verifyHistory, 
				  			  "VERIFIED the accept changes for " + numFieldName + " field" , 
				  			  "Failed to verify changes for " + numFieldName + " field");
		
	}
	
	@Test(description="12237 : Multi-Record Signoff - Records Management - Record History - An event is shown"
			+ " in the Record History whenever a record is signed")
	public void TestCase_16362() throws Exception{
		
		HomePage hp = new HomePage(driver);	
		FSQABrowserPage fbp = hp.clickFSQABrowserMenu();
		boolean navigateToRecords = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.EQUIPMENT,FSQATAB.RECORDS);
		TestValidation.IsTrue(navigateToRecords, 
							  "For equipment category, NAVIGATED to FSQABrowser > Records tab", 
							  "Failed to navigate to FSQABrowser > Records tab");
		
		boolean selectAllRecord = fbp.clickSelectAllChk();
		TestValidation.IsTrue(selectAllRecord, 
				 			  "SELECTED all records in grid", 
				 			  "Failed to select all records in grid");
		
		FSQABrowserRecordsPage frp = fbp.clickViewRecordsBtn();
		TestValidation.Equals(false,
							  frp.error, 
							  "For customer category, NAVIGATED to FSQABrowser > Records tab", 
							  "Failed to navigate to FSQABrowser > Records tab");
		
		boolean selectByFormNm = frp.searchAndSelectRecordInViewSignMode(formName);
		TestValidation.IsTrue(selectByFormNm, 
				  			  "SELECTED record for form - " + formName + " using name - " + displayName, 
				  			  "Failed to select record for form - " + formName);

		boolean signOffRecord = frp.signoffRecord(signOffCmmt);
		TestValidation.IsTrue(signOffRecord, 
							  "SIGNED record for form - " + formName, 
							  "Failed to sign record for form " + formName);

		HashMap<String, String> histDetail = new HashMap<String, String>();
		histDetail.put(null,
				HISTORY_EDIT_TYPES.RECORD_SIGNED + "|" + displayName + "|" + timezoneCode + "|" + currentDate + "|" 
				+ signOffCmmt);
		
		HistoryDetails hd = new HistoryDetails();
		hd.HeaderDetail = histDetail;
		
		boolean verifyHistory = frp.verifyHistoryPopupDetails(hd);	
		TestValidation.IsTrue(verifyHistory, 
				  			  "VERIFIED the signed by for record " + formName, 
				  			  "Failed to verify the signed by for record " + formName);

	}
	
	@Test(description="12380 : Multi-Record Signoff - Records Management - Record History - Each field level edit is "
			+ "reflected in the collapsible section of a Record Edited event")
	public void TestCase_16418() throws Exception{
		
			try {
			HomePage hp = new HomePage(driver);	
			FSQABrowserPage fbp = hp.clickFSQABrowserMenu();
			boolean navigateToRecords = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.EQUIPMENT,FSQATAB.RECORDS);
			TestValidation.IsTrue(navigateToRecords, 
								  "For equipment category, NAVIGATED to FSQABrowser > Records tab", 
								  "Failed to navigate to FSQABrowser > Records tab");
			
			boolean openRecord = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, formName);
			TestValidation.IsTrue(openRecord, 
					 			  "OPENED record with form name - " + formName, 
					 			  "Failed to open record with form name - " + formName);
			
			FSQABrowserRecordsPage frp = new FSQABrowserRecordsPage(driver);
			oldNumFieldValue = frp.getFieldSpecificValue(numFieldName,FIELD_OTHER_TYPES.FIELD);
			TestValidation.IsTrue((oldNumFieldValue!="" || oldNumFieldValue!=null), 
								  "Retrieved value for the field " + numFieldName + " as " + oldNumFieldValue, 
								  "Failed to retrieve value for the field " + numFieldName );
			
			oldAttachmentValue = frp.getFieldSpecificValue(numFieldName,FIELD_OTHER_TYPES.ATTACHMENT);
			TestValidation.IsTrue((oldAttachmentValue!="" || oldAttachmentValue!=null), 
								  "Retrieved value for the field " + numFieldName + " for attachment as " + oldAttachmentValue, 
								  "Failed to retrieve value for the field " + numFieldName + " for attachment");
			
			oldCommentValue = frp.getFieldSpecificValue(numFieldName,FIELD_OTHER_TYPES.COMMENT);
			TestValidation.IsTrue((oldCommentValue!="" || oldCommentValue!=null), 
								  "Retrieved value for the field " + numFieldName + " for comment as " + oldCommentValue, 
								  "Failed to retrieve value for the field " + numFieldName + " for comment");
			
			oldResolveValue = frp.getFieldSpecificValue(numFieldName,FIELD_OTHER_TYPES.RESOLVED);
			TestValidation.IsTrue((oldResolveValue!="" || oldResolveValue!=null), 
								  "Retrieved value for the field " + numFieldName + " for Resolved as " + oldResolveValue, 
								  "Failed to retrieve value for the field " + numFieldName + " for Resolved");
			
			oldCorrectionValue = frp.getFieldSpecificValue(numFieldName,FIELD_OTHER_TYPES.CORRECTION);
			TestValidation.IsTrue((oldCorrectionValue!="" || oldCorrectionValue!=null), 
								  "Retrieved value for the field " + numFieldName + " for correction as " + oldCorrectionValue, 
								  "Failed to retrieve value for the field " + numFieldName + " for correction");
			
			boolean editRecord = frp.clickEditRecordBtn();
			TestValidation.IsTrue(editRecord, 
								  "CLICKED on edit record", 
								  "Failed to click on edit");
			
			newNumFieldValue = "321";
			newAttachmentValue = attachmentFilePath;
			newCommentValue = "Update comment";
			newCorrectionValue = "Update correction";
			RecFieldProps fp = new RecFieldProps();
			fp.fieldName = numFieldName;
			fp.fieldValue = newNumFieldValue;
			fp.commentsText = newCommentValue;
			fp.correctionText = newCorrectionValue;
			fp.uploadAttachment = newAttachmentValue;
			fp.noResolve = true;
			
			UpdateFieldDets ufd = new UpdateFieldDets();
			ufd.recFieldProps = Arrays.asList(fp);
			boolean updateValue = frp.updateFieldValues(ufd);
			TestValidation.IsTrue(updateValue, 
				  	  			  "UPDATED values for field type Numeric as " + newNumFieldValue, 
					  			  "Failed to update values for field type Numeric as " + newNumFieldValue);
			
			boolean submitRecord = frp.submitRecord(submitRecordCmmt);
			TestValidation.IsTrue(submitRecord, 
		  			  			  "SUBMITTED record with comment " + submitRecordCmmt, 
		  			  			  "Failed to submit record with comment " + submitRecordCmmt);
	
			DateTime dt = new DateTime(driver);
			currentDate = dt.getTodayDate("MM/dd/YYYY", dt.getTimezone(usrTimezone));
			
			HashMap<String, String> histDetail1 = new HashMap<String, String>();
			histDetail1.put(HISTORY_EDIT_TYPES.RECORD_EDITED,
					HISTORY_EDIT_TYPES.RECORD_EDITED + "|" + displayName + "|" + timezoneCode + "|" + currentDate);
			
			HistDetailedInfo hdi1 = new HistDetailedInfo();
			hdi1.editType = FIELD_OTHER_TYPES.FIELD;
			hdi1.fieldName = numFieldName;
			hdi1.newFieldValue = newNumFieldValue;
			hdi1.oldFieldValue = oldNumFieldValue;
			
			HistoryDetails hd1 = new HistoryDetails();
			hd1.HeaderDetail = histDetail1;
			hd1.detailedInfoList = Arrays.asList(hdi1);
			hd1.closeHistoryPopup = false;
			
			boolean verifyFieldHistory = frp.verifyHistoryPopupDetails(hd1);	
			TestValidation.IsTrue(verifyFieldHistory, 
					  			  "VERIFIED the changes for " + numFieldName + " field updated with value " + newNumFieldValue , 
					  			  "Failed to verify changes for " + numFieldName + " field");
			
			HashMap<String, String> histDetail2 = new HashMap<String, String>();
			histDetail2.put(HISTORY_EDIT_TYPES.RECORD_EDITED,null);
			
			HistDetailedInfo hdi2 = new HistDetailedInfo();
			hdi2.editType = FIELD_OTHER_TYPES.ATTACHMENT;
			hdi2.fieldName = numFieldName;
			hdi2.newFieldValue = newAttachmentName;
			
			HistoryDetails hd2 = new HistoryDetails();
			hd2.HeaderDetail = histDetail2;
			hd2.detailedInfoList = Arrays.asList(hdi2);
			hd2.openHistoryPopup = false;
			hd2.showMoreLink = false;
			hd2.closeHistoryPopup = false;
			
			boolean verifyAttchmntHistory = frp.verifyHistoryPopupDetails(hd2);	
			TestValidation.IsTrue(verifyAttchmntHistory, 
					  			  "VERIFIED the changes for " + numFieldName + " field updated with Attachment value as " + newAttachmentName, 
					  			  "Failed to verify attachment changes for " + numFieldName + " field");
			
			HistDetailedInfo hdi3 = new HistDetailedInfo();
			hdi3.editType = FIELD_OTHER_TYPES.RESOLVED;
			hdi3.fieldName = numFieldName;
			hdi3.newFieldValue = "No";
			hdi3.oldFieldValue = "Yes";
			
			HistoryDetails hd3 = new HistoryDetails();
			hd3.HeaderDetail = histDetail2;
			hd3.detailedInfoList = Arrays.asList(hdi3);
			hd3.openHistoryPopup = false;
			hd3.showMoreLink = false;
			hd3.closeHistoryPopup = false;
			
			boolean verifyRslvHistory = frp.verifyHistoryPopupDetails(hd3);	
			TestValidation.IsTrue(verifyRslvHistory, 
					  			  "VERIFIED the changes for " + numFieldName + " field updated with Resolve value as No", 
					  			  "Failed to verify changes for " + numFieldName + " field");
			
			HistDetailedInfo hdi4 = new HistDetailedInfo();
			hdi4.editType = FIELD_OTHER_TYPES.CORRECTION;
			hdi4.fieldName = numFieldName;
			hdi4.newFieldValue = newCorrectionValue;
			hdi4.oldFieldValue = oldCorrectionValue;
			
			HistoryDetails hd4 = new HistoryDetails();
			hd4.HeaderDetail = histDetail2;
			hd4.detailedInfoList = Arrays.asList(hdi4);
			hd4.openHistoryPopup = false;
			hd4.showMoreLink = false;
			hd4.closeHistoryPopup = false;
			
			boolean verifyCorrtnHistory = frp.verifyHistoryPopupDetails(hd4);	
			TestValidation.IsTrue(verifyCorrtnHistory, 
					  			  "VERIFIED the changes for " + numFieldName + " field updated with Correction value as " + newCorrectionValue, 
					  			  "Failed to verify changes for " + numFieldName + " field");
			
			HistDetailedInfo hdi5 = new HistDetailedInfo();
			hdi5.editType = FIELD_OTHER_TYPES.COMMENT;
			hdi5.fieldName = numFieldName;
			hdi5.newFieldValue = newCommentValue;
			hdi5.oldFieldValue = oldCommentValue;
			
			HistoryDetails hd5 = new HistoryDetails();
			hd5.HeaderDetail = histDetail2;
			hd5.detailedInfoList = Arrays.asList(hdi5);
			hd5.openHistoryPopup = false;
			hd5.showMoreLink = false;
			
			boolean verifyCmmtHistory = frp.verifyHistoryPopupDetails(hd5);	
			TestValidation.IsTrue(verifyCmmtHistory, 
					  			  "VERIFIED the changes for " + numFieldName + " field updated with Comment value as " + newCommentValue , 
					  			  "Failed to verify changes for " + numFieldName + " field");
	    }
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}
		}
	}
	
	@Test(description="12240 : Multi-Record Signoff - Records Management - Record History - A summary of edits is "
			+ "shown in a collapsible section below the Record Edited event in the the Record History",
			dependsOnMethods = { "TestCase_16418" })
	public void TestCase_16363() {
		
		try {
			HomePage hp = new HomePage(driver);	
			WebElement ShowMoreHistHeader = null;
			
			FSQABrowserPage fbp = hp.clickFSQABrowserMenu();
			boolean navigateToRecords = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.EQUIPMENT,FSQATAB.RECORDS);
			TestValidation.IsTrue(navigateToRecords, 
								  "For equipment category, NAVIGATED to FSQABrowser > Records tab", 
								  "Failed to navigate to FSQABrowser > Records tab");
			
			boolean openRecord = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, formName);
			TestValidation.IsTrue(openRecord, 
					 			  "OPENED record with form name - " + formName, 
					 			  "Failed to open record with form name - " + formName);
			
			FSQABrowserRecordsPage frp = new FSQABrowserRecordsPage(driver);
			boolean openHistPopup1 = frp.clickHistoryIcon();
			TestValidation.IsTrue(openHistPopup1, 
					 			  "OPENED History popup for record - " + formName, 
					 			  "Failed to History popup for record - " + formName);
			
			WebElement ShowMoreHistBtn = frp.ShowMoreHistBtn.get(0);
			boolean verifyCollapsed = ShowMoreHistBtn.getText().contains(showMoreMsg);
			TestValidation.IsTrue(verifyCollapsed, 
					 			  "VERIFIED Show More link is collapsed", 
					 			  "Failed to verify status of Show More link");
			
			ShowMoreHistHeader = controlActions.perform_GetElementByXPath(
					FSQABrowserRecordsPageLocators.SHOW_MORE_HIST_HEADER, "INDEX_NO", "1");
			TestValidation.IsTrue(ShowMoreHistHeader==null, 
					  			  "VERIFIED history for edit record changes is hidden", 
					  			  "Failed to verify history for edit record changes is hidden");
			
			controlActions.clickOnElement(ShowMoreHistBtn);
			boolean verifyExpanded = ShowMoreHistBtn.getText().contains(showLessMsg);
			TestValidation.IsTrue(verifyExpanded, 
					 			  "VERIFIED Show More link is expanded", 
					 			  "Failed to verify status of Show More link");
			
			ShowMoreHistHeader = controlActions.perform_GetElementByXPath(
					FSQABrowserRecordsPageLocators.SHOW_MORE_HIST_HEADER, "INDEX_NO", "1");
			TestValidation.IsTrue(ShowMoreHistHeader!=null, 
					  			  "VERIFIED history for edit record changes is not hidden anymore", 
					  			  "Failed to verify history for edit record changes is not hidden");
		}
		finally {
			FSQABrowserRecordsPage frp = new FSQABrowserRecordsPage(driver);
			if(controlActions.isElementDisplayed(frp.CloseHistoryPopup)) {
				frp.closeHistoryPopup();
			}
		}
	}
	
	@Test(description="9660 : Multi-Record Signoff - Records Management - Users can download any attachment files"
			+ " associated with the record")
	public void TestCase_33213() throws Exception{
		
		HomePage hp = new HomePage(driver);
		FSQABrowserPage fbp = hp.clickFSQABrowserMenu();
		boolean navigateToRecords = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.EQUIPMENT,FSQATAB.RECORDS);
		TestValidation.IsTrue(navigateToRecords, 
							  "For equipment category, NAVIGATED to FSQABrowser > Records tab", 
							  "Failed to navigate to FSQABrowser > Records tab");
		
		boolean selectAllRecord = fbp.clickSelectAllChk();
		TestValidation.IsTrue(selectAllRecord, 
				 			  "SELECTED all records in grid", 
				 			  "Failed to select all records in grid");
		
		FSQABrowserRecordsPage frp = fbp.clickViewRecordsBtn();
		TestValidation.Equals(false,
							  frp.error, 
							  "For customer category, NAVIGATED to FSQABrowser > Records tab", 
							  "Failed to navigate to FSQABrowser > Records tab");
		
		boolean selectByFormNm = frp.searchAndSelectRecordInViewSignMode(formName);
		TestValidation.IsTrue(selectByFormNm, 
				  			  "SELECTED record for form - " + formName, 
				  			  "Failed to select record for form - " + formName);
		
		boolean openDoc = frp.clickAndOpenHeaderDoc(documentName3);
		TestValidation.IsTrue(openDoc, 
				  			  "OPENED document - " + documentName3 , 
				  			  "Failed to open document - " + documentName3);
		
		boolean switchTab = controlActions.closeCurrentAndSwitchToTab(WINDOW_TAB.SECOND);
		TestValidation.IsTrue(switchTab, 
				  			  "SWITCHED to main page/tab", 
				  			  "Failed to switch to main page/tab");
		
		boolean downloadAttchmnt1 = frp.downloadAttachmentForField(numFieldName,documentName2);
		TestValidation.IsTrue(downloadAttchmnt1, 
				  			  "DOWNLOADED document - " + documentName2 , 
				  			  "Failed to download document - " + documentName2);
		
		boolean verifyFileDownloaded1 = controlActions.verifyFileDownloaded(downloadPath, documentName2);
		TestValidation.IsTrue(verifyFileDownloaded1, 
							  "Verified " + documentName2 + " file is downloaded successfully ", 
							  "Failed to verify that document " + documentName2 + "  is downloadeded"); 
		
		boolean downloadAttchmnt2 = frp.downloadAttachmentFormLevel(documentName1);
		TestValidation.IsTrue(downloadAttchmnt2, 
				  			  "DOWNLOADED document - " + documentName1 , 
				  			  "Failed to download document - " + documentName1);
		
		boolean verifyFileDownloaded2 = controlActions.verifyFileDownloaded(downloadPath, documentName1);
		TestValidation.IsTrue(verifyFileDownloaded2, 
							  "Verified " + documentName1 + " file is downloaded successfully ", 
							  "Failed to verify that document " + documentName1 + "  is downloadeded"); 
	}
	
	@AfterClass(alwaysRun = true)
	public void closeBrowser() throws InterruptedException {
		driver.close();
	}
}

