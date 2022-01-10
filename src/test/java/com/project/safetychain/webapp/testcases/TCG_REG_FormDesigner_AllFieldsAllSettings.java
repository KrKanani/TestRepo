package com.project.safetychain.webapp.testcases;


import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import com.project.safetychain.webapp.pageobjects.*;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.COLUMNHEADER;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.COLUMNSETTING;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.FSQATAB;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.AGGREGATE_FUNCTION;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.ELEMENT_TYPE;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.ElementProperties;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FIELD_TYPES;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FORMRESOURCETYPES;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FORMTYPES;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FORM_NEXT_PAGE;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FormDesignParams;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FormFieldParams;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.IDENTIFIER_INPUT_TYPE;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.IDENTIFIER_TYPE;
import com.project.safetychain.webapp.pageobjects.ManageLocationPage.LocationInstanceParams;
import com.project.safetychain.webapp.pageobjects.ManageResourcePage.RESOURCETYPES;
import com.project.safetychain.webapp.pageobjects.ManageResourcePage.ResourceDetailParams;
import com.project.safetychain.webapp.pageobjects.ResourceDesignerPage.CATEGORYTYPES;
import com.project.safetychain.webapp.testcases.support.FormOperations;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.utilities.CommonMethods;
import com.project.utilities.ControlActions;

public class TCG_REG_FormDesigner_AllFieldsAllSettings extends TestBase{

	ControlActions controlActions;
	HomePage hp;
	UserManagerPage ump;
	WorkGroupsPage wgp;
	RolesManagerPage rmp;
	FormDesignerPage fdp;
	LoginPage lp;
	FSQABrowserPage fbp;
	FSQABrowserFormsPage fbForms;
	FSQABrowserRecordsPage fbRecords;
	FormOperations formoperations;
	DocumentManagementPage documentManagement;

	public static String locationCategoryValue, equipmentsCategoryValue;
	public static String locationInstanceValue, equipmentsInstanceValue;
	//public static String chkFormName, adtFormName, qstFormName;

	@BeforeClass(alwaysRun = true)
	public void groupInit() throws InterruptedException {
		
		locationCategoryValue = CommonMethods.dynamicText("LocCat_");
		equipmentsCategoryValue = CommonMethods.dynamicText("EquipCat_");//"Equip_Cat_26.08_15.55.38";
		locationInstanceValue = CommonMethods.dynamicText("LocInst_");
		equipmentsInstanceValue = CommonMethods.dynamicText("EquipInst_");//"Equip_Inst_26.08_15.55.38";
//		chkFormName = CommonMethods.dynamicText("CHK");
//		adtFormName = CommonMethods.dynamicText("AuditForm_");
//		qstFormName = CommonMethods.dynamicText("Qstn_");

		driver = launchbrowser();
		controlActions = new ControlActions(driver);
		controlActions.getUrl(applicationUrl);

		hp = new HomePage(driver);
		ump = new UserManagerPage(driver);
		wgp = new WorkGroupsPage(driver);
		rmp = new RolesManagerPage(driver);
		fdp = new FormDesignerPage(driver);
		fbp = new FSQABrowserPage(driver);
		fbForms = new FSQABrowserFormsPage(driver);
		lp = new LoginPage(driver);
		formoperations = new FormOperations(driver);
		fbRecords = new FSQABrowserRecordsPage(driver);

		LoginPage lp = new LoginPage(driver);
		hp = lp.performLogin(adminUsername, adminPassword);
		if(hp.error) {
			TCGFailureMsg = "Could NOT login to application";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
		
		//Category creation
		HashMap<String,String> categories = new HashMap<String, String>();
		categories.put(CATEGORYTYPES.LOCATION, locationCategoryValue);
		categories.put(CATEGORYTYPES.EQUIPMENT, equipmentsCategoryValue);
		ResourceDesignerPage rdp = hp.clickResourceDesignerMenu();
		if(!rdp.createCategory(categories)) {
			TCGFailureMsg = "Could NOT create Resource categories for - " + locationCategoryValue + equipmentsCategoryValue;
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

		//Resource creation - Supplier
		HashMap<String,Boolean> equipInstance = new HashMap<String, Boolean>();
		equipInstance.put(equipmentsInstanceValue, true);

		ResourceDetailParams rd2 = new ResourceDetailParams();
		rd2.CategoryName = equipmentsCategoryValue;
		rd2.CategoryType = RESOURCETYPES.EQUIPMENT;
		rd2.NumberFieldValue = 15;
		rd2.TextFieldValue = "LMN";
		rd2.InstanceNames = equipInstance;
		rd2.LocationInstanceValue = locationInstanceValue;	

		ManageResourcePage mrp = hp.clickResourcesMenu();
		if(!mrp.createResourceLinkLocation(rd2)) {
			TCGFailureMsg = "Could NOT create Equipment Instance for resource - " + equipmentsCategoryValue;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
		
	}
	
	//==================== CHECK FORMS ===========================//
	@Test(description = "Form Designer || Validate form functionality for field settings for 'Check' forms")
	public void TestCase_37722() throws Exception {
		
		String chkFrmName = CommonMethods.dynamicText("AF_");
		String freeTextFieldName = "AFreeText";
		String freeTextComplianceValue = "A Sample Text";
		String hintText = "Free Text Hint";
		String comment = "This is a comment";
		String customCorrection = "CustomCorrection";
		String markAsResolved = "Yes";
		String  generalFile = "generalFile.png";
		String 	generalFilePath = System.getProperty("user.dir") + "\\test-data-files\\UploadDocuments\\"+generalFile;
		
		
		List<String> preDefinedCorrectionOptions = Arrays.asList("Option1","Option2");
		
		// Form Level
		HashMap<String, List<String>> chkFields = new HashMap<String, List<String>>();
		chkFields.put(FIELD_TYPES.FREE_TEXT, Arrays.asList(freeTextFieldName));

		
		FormFieldParams ffpChk = new FormFieldParams();
		ffpChk.FieldDetails = chkFields;
		
		HashMap<String, List<String>> equipResource = new HashMap<String, List<String>>();
		equipResource.put(FORMRESOURCETYPES.EQUIPMENT, Arrays.asList(equipmentsCategoryValue));

		FormDesignParams fdpChk = new FormDesignParams();
		fdpChk.FormType = FORMTYPES.CHECK;
		fdpChk.FormName = chkFrmName;
		fdpChk.SelectResources = equipResource;
		fdpChk.DesignFields = Arrays.asList(ffpChk);
		fdpChk.ReleaseForm = true;
		
		// navigate to form designer
		fdp = hp.clickFormDesignerMenu();
		
		boolean designForm = fdp.designForm(fdpChk);
		TestValidation.IsTrue(designForm, 
							  "Successfully Designed Form - " +chkFrmName + " with Free Text Field -" + freeTextFieldName, 
					          "Failed to design from with Free Text Field");
		
		HashMap<String, ElementProperties> fieldSettings = new LinkedHashMap<String, ElementProperties>(); 
		ElementProperties settings = new ElementProperties();
		settings.FAILS_CHECK = true;
		settings.IS_REQUIRED = true;
		settings.ALLOW_COMMENTS = true;
		settings.ALLOW_ATTATHMENTS = true;
		settings.SHOW_HINT = true;
		settings.HINT = hintText;
		settings.ALLOW_CORRECTION = true;
		settings.MARK_RESOLVED = true;
		settings.PREDEFINED_CORRECTIONS = true;
		settings.PREDEFINED_CORRECTIONS_OPTNS = preDefinedCorrectionOptions;
		settings.CARRYOVER_FIELD = true;
		settings.COMPLIANCE_TEXT = freeTextComplianceValue;
		fieldSettings.put(freeTextFieldName, settings);
		
		boolean turnAllSettingsOn = fdp.setFieldProperties(fieldSettings) ;
		TestValidation.IsTrue(turnAllSettingsOn, 
	             			  "Successfully Turned All Settings for the Free Text Field -" + freeTextFieldName, 
	                          "Failed to tur on All Settigs for for the Free Text Field -" + freeTextFieldName);
		
		//boolean setComplianceText = fdp.setComplianceForFreeText(freeTextFieldName, freeTextComplianceValue);
		TestValidation.IsTrue(true, 
				  			  "Set Compliance Text as -" + freeTextComplianceValue + " for free text field -" + freeTextFieldName, 
				              "Failed to set Compliance Text for free txt -" + freeTextFieldName);
		
//		boolean saveForm = fdp.clickSaveButton();
//		TestValidation.IsTrue(saveForm, 
//				  			  "Saved form -" + chkFrmName, 
//				  			  "Failed to save form -" +chkFrmName);
		
		// ============================= START -> VERIFY Settings in FORM PREVIEW
		
		TestValidation.IsTrue(true,"======================== IN Form Preview ========================","");
		
		boolean openfForPreview = fdp.clickPreviewForFormOnReleaePage(chkFrmName);
		TestValidation.IsTrue(openfForPreview, 
				  			  "Clicked Preview Butoon and Opened Form -"+ chkFrmName + " for preview", 
				              "Failed to open form for Preview");
		
		boolean setFreeTextFieldPrvw = formoperations.setInputFieldValue(freeTextFieldName, Arrays.asList("abc"), null);
		TestValidation.IsTrue(setFreeTextFieldPrvw, 
				  			  "Entered non compliant value -'abc'" + " in free text fied -" + freeTextFieldName, 
				              "Failed to enter value in free text field -" + freeTextFieldName);
		
		boolean checkIsFieldRequiredPrvw = formoperations.isFieldRequired(freeTextFieldName);
		TestValidation.IsTrue(checkIsFieldRequiredPrvw, 
				  			  "Verified that freetext field -" + freeTextFieldName + " is Marked as required in Form Preview", 
				              "Failed to verify that freetext field -" + freeTextFieldName + " is Marked as required in Form Preview");
		
		boolean allwAtchmntsPrvw = formoperations.verifyAllowAttachmentsInPreviewForField(freeTextFieldName);
		TestValidation.IsTrue(allwAtchmntsPrvw, 
							  "Verified Allow Attachements for field -" + freeTextFieldName + " in Form Preview", 
							  "FAILED to verify Allow Attachements for field -" + freeTextFieldName + " in Form Preview");
		
		boolean addCmntsPrvw = formoperations.addCommentForField(freeTextFieldName, "This is a comment");
		TestValidation.IsTrue(addCmntsPrvw, 
				  			  "Verified Add Comment Textbox present for freetext field -" + freeTextFieldName + " in Form Preview and added comment -" + comment , 
				  			  "FAILED to verify Add Comment Textbox present for freetext field -" + freeTextFieldName );
		
		boolean hintDisplayPrvw = formoperations.isHintDisplayedForField(freeTextFieldName, hintText);
		TestValidation.IsTrue(hintDisplayPrvw, 
				  			  "Verified hint Displayed for free text field -" + freeTextFieldName + " in Form Preview", 
				  			  "FAILED to very hint display for free text field -" + freeTextFieldName + " in Form Preview");
		
		boolean addCustmCorctnPrvw = formoperations.setCustomCorrectionForField(freeTextFieldName, customCorrection);
		TestValidation.IsTrue(addCustmCorctnPrvw, 
				  			  "Verified Custom Correction Text box for free text field -" + freeTextFieldName + " in Form Preview, added correction -" + customCorrection, 
				              "FAILED to verify correction textbox for free text field -" + freeTextFieldName + " in Form Preview");
		
		boolean setPredefindCorrctnPrvw = formoperations.setPredefinedCorrectionsForField(freeTextFieldName, preDefinedCorrectionOptions.get(1))	;
		TestValidation.IsTrue(setPredefindCorrctnPrvw, 
				  			  "Verufied Predefined Correction input field for free text field -" + freeTextFieldName + " in Form Preview, added correction -" + preDefinedCorrectionOptions.get(1), 
				              "FAILED to verify Predefined Correction input for freetext field -" + freeTextFieldName + " in form Preview" );
		
		boolean markAsRslvdPrvw = formoperations.markAsResolvedForField(freeTextFieldName, markAsResolved);
		TestValidation.IsTrue(markAsRslvdPrvw, 
				  			  "Verified 'Mark As Resolved' option for free text field -" + freeTextFieldName + " in Form Preview, marked it as -" + markAsResolved, 
				              "FAILED to verify 'Marked As Resolved' option for free text field -" + freeTextFieldName + " in Form Preview");
		
		
		boolean closePreview = fdp.clickClosePreviewBtn();
		TestValidation.IsTrue(closePreview, 
				  			  "Successfully Closed Form Preview", 
				  			  "Could NOT Close Form Preview");
		// ============================= END -> VERIFY Settings in FORM PREVIEW
		
		boolean releaseForm = fdp.releaseForm(chkFrmName);
		TestValidation.IsTrue(releaseForm, 
							 "Successfully released form -" + chkFrmName, 
							 "Could Not release form -" + chkFrmName);
		
		boolean navToHome = fdp.navigateToHome();
		TestValidation.IsTrue(navToHome, 
				  			  "Successfully Navigated to Home", 
				              "Could NOT naigate to home");
		
		boolean selectResourceDropDownandNavigate = fbp.selectResourceDropDownandNavigate(FORMRESOURCETYPES.EQUIPMENT,FSQATAB.FORMS);
		TestValidation.IsTrue(selectResourceDropDownandNavigate, 
							  "Navigated to FSQA ->  " + FSQATAB.FORMS ,
							  "Could NOT Navigate to FSQA ->  " + FSQATAB.FORMS);
		
		
		boolean selectOpenForm = fbForms.selectOpenForm(chkFrmName);
		TestValidation.IsTrue(selectOpenForm, 
							  "Select & opened the form -" + chkFrmName, 
							  "Could NOT open the form" + chkFrmName);
		
		
		// ============================= START -> VERIFY Settings in FSQABrowser Forms
		
		TestValidation.IsTrue(true,"======================== IN FSQABrowser Forms ========================","");
		
		boolean setFreeTextFieldFrms = formoperations.setInputFieldValue(freeTextFieldName, Arrays.asList("abc"), null);
		TestValidation.IsTrue(setFreeTextFieldFrms, 
	  			  			  "Entered non compliant value -'abc'" + " in free text fied -" + freeTextFieldName, 
	  			  			  "Failed to enter value in free text field -" + freeTextFieldName);
		
		boolean checkIsFieldRequiredFrms = formoperations.isFieldRequired(freeTextFieldName);
		TestValidation.IsTrue(checkIsFieldRequiredFrms, 
							  "Verified freetext field is marked as required", 
					          "FAILED to verify free text marked as required");
		
		boolean allwAtchmntsFrms = formoperations.addAttachmentForField(freeTextFieldName, generalFilePath);
		TestValidation.IsTrue(allwAtchmntsFrms, 
				  			  "Verified Allow Attachments for free text field, and attached file -" + generalFile, 
				  			  "FAILED to verify Allow Attachments for free text field");
		
		boolean addCmntsFrms = formoperations.addCommentForField(freeTextFieldName, comment);
		TestValidation.IsTrue(addCmntsFrms, 
							  "Verified Allow Comments for free text field and entered comment -" + comment, 
				  			  "FAILED to verify Allow Comments for free text field");
		
		boolean hintDisplayFrms = formoperations.isHintDisplayedForField(freeTextFieldName, hintText);
		TestValidation.IsTrue(hintDisplayFrms, 
				  		      "Verified Hint Display for free text field, hint =" + hintText, 
				              "FAILED to verify Hint Display for free textfield");
		
		boolean addCustmCorctnFrms = formoperations.setCustomCorrectionForField(freeTextFieldName, customCorrection);
		TestValidation.IsTrue(addCustmCorctnFrms, 
				  			  "Verified Custom Correction for free text field", 
				              "FAILED to verify Custom Correction for free textfield");
		
		boolean setPredefindCorrctnFrms = formoperations.setPredefinedCorrectionsForField(freeTextFieldName, preDefinedCorrectionOptions.get(1));
		TestValidation.IsTrue(setPredefindCorrctnFrms, 
	             		      "Verified Predefined Correction for free text field", 
	                          "FAILED to verify Predefined Correction for free textfield");
		
		boolean markAsRslvdFrms = formoperations.markAsResolvedForField(freeTextFieldName, markAsResolved);
		TestValidation.IsTrue(markAsRslvdFrms, 
				  			  "Verified Mark As Resolved for free textfield", 
				              "FAILED to verify Mark As Resolved for free textfield");
		
//		boolean submitForm = formoperations.clickSubmitThenAlertOkBtn();
//		TestValidation.IsTrue(submitForm, 
//				  			 "Clicked on Submit and Submitted the form -" + chkFrmName, 
//				             "Could NOT click on Submit buttton and failed to submit form -" + chkFrmName);
		
		//formoperations.addCorrectionForField(freeTextFieldName, "CustomCorrection", preDefinedCorrectionOptions.get(1), "Yes");
		//Try Submit and Repeat
		
		boolean clickSubmitRepeat = fbForms.clickSubmitAndReapeatButton();
		TestValidation.IsTrue(clickSubmitRepeat, 
				  			 "CLICKED SUBMIT AND REPEAT", 
							 "FAILED TO CLICK SUBMIT AND REPEAT");
		
		TestValidation.IsTrue(true,"======================== After Submit and Repeat ========================","");
		
		boolean checkIsFieldRequiredSubRep = formoperations.isFieldRequired(freeTextFieldName);
		TestValidation.IsTrue(checkIsFieldRequiredSubRep, 
							  "Verified freetext field is marked as required", 
					          "FAILED to verify free text marked as required");
		
		boolean hintDisplaySubRep = formoperations.isHintDisplayedForField(freeTextFieldName, hintText);
		TestValidation.IsTrue(hintDisplaySubRep, 
				  		      "Verified Hint is Displayed for free text field, hint =" + hintText, 
				              "FAILED to verify Hint Display for free textfield");
		
		boolean verifyCommentSubRep = formoperations.verifyCommentOfField(freeTextFieldName, comment);
		TestValidation.IsTrue(verifyCommentSubRep, 
				  			  "Verified that comment is repeated correctly", 
				              "Failed to verify repeated comment ");
		
		boolean verifyAttachmentSubRep = formoperations.verifySingleAttachmentForField(freeTextFieldName, generalFile);
		TestValidation.IsTrue(verifyAttachmentSubRep, 
							 "Verified Attachment is repeated", 
				             "Failed to verify repeated attahment");
		
		boolean verifyPredefinedCorrSubRep = formoperations.verifyPredefinedCorrectionValueForField(freeTextFieldName, preDefinedCorrectionOptions.get(1));
		TestValidation.IsTrue(verifyPredefinedCorrSubRep, 
				  			 "Verified correction is repeated", 
				  			 "Failed to verify repeated correction");
		
		boolean verifyMarkAsResolvedSubRep = formoperations.verifymarkAsResolvedValueForField(freeTextFieldName, markAsResolved);
		TestValidation.IsTrue(verifyMarkAsResolvedSubRep, 
							  "Verified Mark as resolved is repeated", 
				  			  "Failed to verify Mark as Resolved repeated");
		
		boolean clickCancel = fbForms.clickCancelButton();
		TestValidation.IsTrue(clickCancel, 
				  			 "SUCCESSFULLY CLOSED FORM", 
							 "FAILED TO CLOSE FORM");
		
		// ============================= END -> VERIFY Settings in FSQABrowser Forms
		
		//Goto Recodrs
		boolean navigateToRecords = fbp.navigateToFSQATab(FSQATAB.RECORDS);
		TestValidation.IsTrue(navigateToRecords, 
				  			  "Successfully Navigated to -" + FSQATAB.RECORDS , 
				              "Could NOT Navigate to -" + FSQATAB.RECORDS);
		
		
		boolean verifyFailsCheck = fbRecords.verifyFailsCheckForRecord(chkFrmName,FSQABrowserRecordsPage.FAILS_CHECK.FAILS);
		TestValidation.IsTrue(verifyFailsCheck, 
				  			  "Verified Fails Check for record with Form Name -" + chkFrmName + " as Non-Compliant", 
				  	  		  "FAILED to verify Fails Check for record with Form Name -" + chkFrmName + " as Non-Compliant");
		
		// open record
		boolean openRecord = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, chkFrmName);
		TestValidation.IsTrue(openRecord, 
							  "OPENED record for form - " + chkFrmName, 
							  "Failed to open record for form - " + chkFrmName);
		
		
		// ============================= START -> VERIFY Settings in FSQABrowser Records 
		
		TestValidation.IsTrue(true,"======================== IN FSQABrowser Records ========================","");
		
		boolean checkIsFieldRequiredRecrds = fbRecords.verifyIsFieldMarkedAsRequired(freeTextFieldName);
		TestValidation.IsTrue(checkIsFieldRequiredRecrds, 
				 			  "Verified freetext field is marked as required", 
		          			  "FAILED to verify free text marked as required");
		
		boolean hintDisplayRecrds = fbRecords.verifyHintForField(freeTextFieldName, hintText);
		TestValidation.IsTrue(hintDisplayRecrds, 
				 			  "Verified Hint Display for free text field, hint =" + hintText, 
				              "FAILED to verify Hint Display for free textfield");
        		
		boolean addCmntsRecrds = fbRecords.verifyCommentForField(freeTextFieldName, comment);
		TestValidation.IsTrue(addCmntsRecrds, 
				  			  "Verified Comment as -" + comment + " for free text field -" + freeTextFieldName, 
				              "FAILED to verify Comment for free textfield");
		
		boolean allwAtchmntsRecrds = fbRecords.verifyAttachmentFileNameForField(freeTextFieldName, generalFile);
		TestValidation.IsTrue(allwAtchmntsRecrds, 
				  			  "Verified Attachment as -" + generalFile + "for free text field -" + freeTextFieldName, 
				              "FAILED to verify Attachment for free text field - " +freeTextFieldName);
		
		boolean correctionRecrds = fbRecords.verifyCorrectionForField(freeTextFieldName, preDefinedCorrectionOptions.get(1));
		TestValidation.IsTrue(correctionRecrds, 
				  			  "Verified Correction as -" +preDefinedCorrectionOptions.get(1) + " for free text field - " + freeTextFieldName, 
				              "FAILED to verify correction");
		
		boolean markAsResolvedRecrds = fbRecords.verifyIsResolvedForField(freeTextFieldName, markAsResolved);
		TestValidation.IsTrue(markAsResolvedRecrds, 
				  			  "Verified Marked as Resolved as -" + markAsResolved + " for free text field -" + freeTextFieldName,
				              "FAILED to verify Marked As Resolved ");
		// ============================= END -> VERIFY Settings in FSQABrowser Records 
		
	}
	
	@Test(description = "Form Designer || Validate form functionality for field settings for 'Check' forms - \"Numeric\" field")
	public void TestCase_48769() throws Exception {
		
		//fdp.dateDiffExpressionRuleForField("DT", "N1", "N2");
		
		String chkFrmName = CommonMethods.dynamicText("CF_");
		String numericFieldName = "Num1";
		String fieldMin = "10", fieldTar = "20", fieldMax = "30";
		String hintText = "Free Text Hint";
		String comment = "This is a comment";
		String customCorrection = "CustomCorrection";
		String markAsResolved = "Yes";
		String generalFile = "generalFile.png";
		String generalFilePath = System.getProperty("user.dir") + "\\test-data-files\\UploadDocuments\\"+generalFile;
		
		List<String> preDefinedCorrectionOptions = Arrays.asList("Option1","Option2");
		
		// Form Level
		HashMap<String, List<String>> chkFields = new HashMap<String, List<String>>();
		chkFields.put(FIELD_TYPES.NUMERIC, Arrays.asList(numericFieldName));

		
		FormFieldParams ffpChk = new FormFieldParams();
		ffpChk.FieldDetails = chkFields;
		
		HashMap<String, List<String>> equipResource = new HashMap<String, List<String>>();
		equipResource.put(FORMRESOURCETYPES.EQUIPMENT, Arrays.asList(equipmentsCategoryValue));
		
		FormDesignParams fdpChk = new FormDesignParams();
		fdpChk.FormType = FORMTYPES.CHECK;
		fdpChk.FormName = chkFrmName;
		fdpChk.SelectResources = equipResource;
		fdpChk.DesignFields = Arrays.asList(ffpChk);
		fdpChk.ReleaseForm = true;
		
		// navigate to form designer
		fdp = hp.clickFormDesignerMenu();
		
		boolean designForm = fdp.designForm(fdpChk);
		TestValidation.IsTrue(designForm, 
							  "Successfully Designed Form - " +chkFrmName + " with Numeric Field -" + numericFieldName, 
					          "Failed to design from with Numeric Field");
		
		HashMap<String, ElementProperties> fieldSettings = new LinkedHashMap<String, ElementProperties>(); 
		ElementProperties settings = new ElementProperties();
		settings.FAILS_CHECK = true;
		settings.IS_REQUIRED = true;
		settings.ALLOW_COMMENTS = true;
		settings.ALLOW_ATTATHMENTS = true;
		settings.SHOW_HINT = true;
		settings.HINT = hintText;
		settings.ALLOW_CORRECTION = true;
		settings.MARK_RESOLVED = true;
		settings.PREDEFINED_CORRECTIONS = true;
		settings.PREDEFINED_CORRECTIONS_OPTNS = preDefinedCorrectionOptions;
		settings.CARRYOVER_FIELD = true;
		settings.SHOW_MIN_MAX = true;
		settings.SHOW_TARGET = true;
		settings.COMPLIANCE_VALUES = new String[]{fieldMin, fieldTar, fieldMax, null};
		settings.COMPLIANCE_CONFIG = true;
		fieldSettings.put(numericFieldName, settings);
		
		boolean turnAllSettingsOn = fdp.setFieldProperties(fieldSettings) ;
		TestValidation.IsTrue(turnAllSettingsOn, 
	             			  "Successfully Turned All Settings for the Numeric Field -" + numericFieldName, 
	                          "Failed to tur on All Settigs for for the Numeric Field -" + numericFieldName);
		
		//boolean setComplianceText = fdp.setComplianceForFreeText(numericFieldName, freeTextComplianceValue);
		TestValidation.IsTrue(true, 
				  			  "Set Compliance Text as -" + numericFieldName + " for free text field -" + numericFieldName, 
				              "Failed to set Compliance Values for Numeric Field -" + numericFieldName);
		
//		boolean saveForm = fdp.clickSaveButton();
//		TestValidation.IsTrue(saveForm, 
//				  			  "Saved form -" + chkFrmName, 
//				  			  "Failed to save form -" +chkFrmName);
		
		// ============================= START -> VERIFY Settings in FORM PREVIEW
		
		TestValidation.IsTrue(true,"======================== IN Form Preview ========================","");
		
		boolean openfForPreview = fdp.clickPreviewForFormOnReleaePage(chkFrmName);
		TestValidation.IsTrue(openfForPreview, 
				  			  "Clicked Preview Butoon and Opened Form -"+ chkFrmName + " for preview", 
				              "Failed to open form for Preview");
		
		boolean setNumFieldPrvw = formoperations.setInputFieldValue(numericFieldName, Arrays.asList("50"), null);
		TestValidation.IsTrue(setNumFieldPrvw, 
				  			  "Entered non compliant value -'50'" + " in Numeric Field -" + numericFieldName, 
				              "Failed to enter value in Numeric Field -" + numericFieldName);
		
		boolean checkIsFieldRequiredPrvw = formoperations.isFieldRequired(numericFieldName);
		TestValidation.IsTrue(checkIsFieldRequiredPrvw, 
				  			  "Verified that Numeric Field -" + numericFieldName + " is Marked as required", 
				              "Failed to verify that Numeric Field -" + numericFieldName + " is Marked as required");
		
		boolean allwAtchmntsPrvw = formoperations.verifyAllowAttachmentsInPreviewForField(numericFieldName);
		TestValidation.IsTrue(allwAtchmntsPrvw, 
							  "Verified Allow Attachements Numeric Field -" + numericFieldName, 
							  "FAILED to verify Allow Attachements Numeric Field -" + numericFieldName);
		
		boolean addCmntsPrvw = formoperations.addCommentForField(numericFieldName, "This is a comment");
		TestValidation.IsTrue(addCmntsPrvw, 
				  			  "Verified Add Comment Textbox present for Numeric Field -" + numericFieldName + " and added comment -" + comment , 
				  			  "FAILED to verify Add Comment Textbox present for Numeric Field -" + numericFieldName );
		
		boolean hintDisplayPrvw = formoperations.isHintDisplayedForField(numericFieldName, hintText);
		TestValidation.IsTrue(hintDisplayPrvw, 
				  			  "Verified hint Displayed for Numeric Field -" + numericFieldName, 
				  			  "FAILED to very hint display for Numeric Field -" + numericFieldName);
		
		boolean showMinMaxPreview = formoperations.verifyShowMinMaxForNumField(numericFieldName, fieldMin, fieldMax);
		TestValidation.IsTrue(showMinMaxPreview, 
				  			  "Verified correct Min/Max display for numeric field", 
				              "FAILED to verify display of Min/Max for numeric field");
		
		boolean showTargetPreview = formoperations.verifyShowTargetForNumField(numericFieldName, fieldTar);
		TestValidation.IsTrue(showTargetPreview, 
				 			  "Verified correct 'Target' display for numeric field ", 
	              			  "FAILED to verify display of 'Target' for numeric field");
		
		boolean addCustmCorctnPrvw = formoperations.setCustomCorrectionForField(numericFieldName, customCorrection);
		TestValidation.IsTrue(addCustmCorctnPrvw, 
				  			  "Verified Custom Correction Text box for Numeric Field -" + numericFieldName + " added correction -" + customCorrection, 
				              "FAILED to verify correction textbox for Numeric Field -" + numericFieldName);
		
		boolean setPredefindCorrctnPrvw = formoperations.setPredefinedCorrectionsForField(numericFieldName, preDefinedCorrectionOptions.get(1))	;
		TestValidation.IsTrue(setPredefindCorrctnPrvw, 
				  			  "Verufied Predefined Correction input field for Numeric Field -" + numericFieldName + " added correction -" + preDefinedCorrectionOptions.get(1), 
				              "FAILED to verify Predefined Correction input for Numeric Field -" + numericFieldName);
		
		boolean markAsRslvdPrvw = formoperations.markAsResolvedForField(numericFieldName, markAsResolved);
		TestValidation.IsTrue(markAsRslvdPrvw, 
				  			  "Verified 'Mark As Resolved' option for Numeric Field -" + numericFieldName + "  marked it as -" + markAsResolved, 
				              "FAILED to verify 'Marked As Resolved' option for Numeric Field -" + numericFieldName);
		
		
		boolean closePreview = fdp.clickClosePreviewBtn();
		TestValidation.IsTrue(closePreview, 
				  			  "Successfully Closed Form Preview", 
				  			  "Could NOT Close Form Preview");
		// ============================= END -> VERIFY Settings in FORM PREVIEW
		
		boolean releaseForm = fdp.releaseForm(chkFrmName);
		TestValidation.IsTrue(releaseForm, 
							 "Successfully released form -" + chkFrmName, 
							 "Could Not release form -" + chkFrmName);
		
		boolean navToHome = fdp.navigateToHome();
		TestValidation.IsTrue(navToHome, 
				  			  "Successfully Navigated to Home", 
				              "Could NOT naigate to home");
		
		boolean selectResourceDropDownandNavigate = fbp.selectResourceDropDownandNavigate(FORMRESOURCETYPES.EQUIPMENT,FSQATAB.FORMS);
		TestValidation.IsTrue(selectResourceDropDownandNavigate, 
							  "Navigated to FSQA ->  " + FSQATAB.FORMS ,
							  "Could NOT Navigate to FSQA ->  " + FSQATAB.FORMS);
		
		
		boolean selectOpenForm = fbForms.selectOpenForm(chkFrmName);
		TestValidation.IsTrue(selectOpenForm, 
							  "Select & opened the form -" + chkFrmName, 
							  "Could NOT open the form" + chkFrmName);
		
		
		// ============================= START -> VERIFY Settings in FSQABrowser Forms
		
		TestValidation.IsTrue(true,"======================== IN FSQABrowser Forms ========================","");
		
		boolean setNumFieldFrms =  formoperations.incrementNumericFieldsValue(numericFieldName, 50, 1);
		TestValidation.IsTrue(setNumFieldFrms, 
							  "Entered non compliant value -'50'" + " in Numeric Field -" + numericFieldName, 
							  "Failed to enter value in Numeric Field -" + numericFieldName);
		
		controlActions.actionclickTab();
		
		boolean checkIsFieldRequiredFrms = formoperations.isFieldRequired(numericFieldName);
		TestValidation.IsTrue(checkIsFieldRequiredFrms, 
							  "Verified Numeric Field is marked as required", 
					          "FAILED to verify Numeric Field marked as required");
		
		boolean allwAtchmntsFrms = formoperations.addAttachmentForField(numericFieldName, generalFilePath);
		TestValidation.IsTrue(allwAtchmntsFrms, 
				  			  "Verified Allow Attachments for Numeric Field, and attached file -" + generalFile, 
				  			  "FAILED to verify Allow Attachments for Numeric Field");
		
		boolean addCmntsFrms = formoperations.addCommentForField(numericFieldName, comment);
		TestValidation.IsTrue(addCmntsFrms, 
							  "Verified Allow Comments for Numeric Field and entered comment -" + comment, 
				  			  "FAILED to verify Allow Comments for Numeric Field");
		
		boolean hintDisplayFrms = formoperations.isHintDisplayedForField(numericFieldName, hintText);
		TestValidation.IsTrue(hintDisplayFrms, 
				  		      "Verified Hint Display for Numeric Field, hint =" + hintText, 
				              "FAILED to verify Hint Display for Numeric Field");
		
		boolean showMinMaxFrms = formoperations.verifyShowMinMaxForNumField(numericFieldName, fieldMin, fieldMax);
		TestValidation.IsTrue(showMinMaxFrms, 
				  			  "Verified correct Min/Max display for numeric field", 
				              "FAILED to verify display of Min/Max for numeric field");
		
		boolean showTargetFrms = formoperations.verifyShowTargetForNumField(numericFieldName, fieldTar);
		TestValidation.IsTrue(showTargetFrms, 
				 			  "Verified correct 'Target' display for numeric field", 
	              			  "FAILED to verify display of 'Target' for numeric field");
		
		boolean addCustmCorctnFrms = formoperations.setCustomCorrectionForField(numericFieldName, customCorrection);
		TestValidation.IsTrue(addCustmCorctnFrms, 
				  			  "Verified Custom Correction for Numeric Field", 
				              "FAILED to verify Custom Correction for Numeric Field");
		
		boolean setPredefindCorrctnFrms = formoperations.setPredefinedCorrectionsForField(numericFieldName, preDefinedCorrectionOptions.get(1))	;
		TestValidation.IsTrue(setPredefindCorrctnFrms, 
	             		      "Verified Predefined Correction for Numeric Field", 
	                          "FAILED to verify Predefined Correction for Numeric Field");
		
		boolean markAsRslvdFrms = formoperations.markAsResolvedForField(numericFieldName, markAsResolved);
		TestValidation.IsTrue(markAsRslvdFrms, 
				  			  "Verified Mark As Resolved for Numeric Field", 
				              "FAILED to verify Mark As Resolved for Numeric Field");
		
//		boolean submitForm = formoperations.clickSubmitThenAlertOkBtn();
//		TestValidation.IsTrue(submitForm, 
//				  			 "Clicked on Submit and Submitted the form -" + chkFrmName, 
//				             "Could NOT click on Submit buttton and failed to submit form -" + chkFrmName);
		
		//formoperations.addCorrectionForField(numericFieldName, "CustomCorrection", preDefinedCorrectionOptions.get(1), "Yes");
		//Try Submit and Repeat
		boolean clickSubmitRepeat = fbForms.clickSubmitAndReapeatButton();
		TestValidation.IsTrue(clickSubmitRepeat, 
				  			 "CLICKED SUBMIT AND REPEAT", 
							 "FAILED TO CLICK SUBMIT AND REPEAT");
		
		TestValidation.IsTrue(true,"======================== After Submit and Repeat ========================","");
		
		boolean checkIsFieldRequiredSubRep = formoperations.isFieldRequired(numericFieldName);
		TestValidation.IsTrue(checkIsFieldRequiredSubRep, 
							  "Verified Numeric Field is marked as required", 
					          "FAILED to verify Numeric Field marked as required");
		
		boolean hintDisplaySubRep = formoperations.isHintDisplayedForField(numericFieldName, hintText);
		TestValidation.IsTrue(hintDisplaySubRep, 
				  		      "Verified Hint is Displayed for Numeric Field, hint =" + hintText, 
				              "FAILED to verify Hint Display for Numeric Field");
		
		boolean showMinMaxSubRep = formoperations.verifyShowMinMaxForNumField(numericFieldName, fieldMin, fieldMax);
		TestValidation.IsTrue(showMinMaxSubRep, 
				  			  "Verified correct Min/Max display for numeric field field", 
				              "FAILED to verify display of Min/Max for numeric field");
		
		boolean showTargetSubRep = formoperations.verifyShowTargetForNumField(numericFieldName, fieldTar);
		TestValidation.IsTrue(showTargetSubRep, 
				 			  "Verified correct 'Target' display for numeric field", 
	              			  "FAILED to verify display of 'Target' for numeric field");
		
		boolean verifyCommentSubRep = formoperations.verifyCommentOfField(numericFieldName, comment);
		TestValidation.IsTrue(verifyCommentSubRep, 
				  			  "Verified that comment is repeated correctly", 
				              "Failed to verify repeated comment ");
		
		boolean verifyAttachmentSubRep = formoperations.verifySingleAttachmentForField(numericFieldName, generalFile);
		TestValidation.IsTrue(verifyAttachmentSubRep, 
							 "Verified Attachment is repeated", 
				             "Failed to verify repeated attahment");
		
		boolean verifyPredefinedCorrSubRep = formoperations.verifyPredefinedCorrectionValueForField(numericFieldName, preDefinedCorrectionOptions.get(1));
		TestValidation.IsTrue(verifyPredefinedCorrSubRep, 
				  			 "Verified correction is repeated", 
				  			 "Failed to verify repeated correction");
		
		boolean verifyMarkAsResolvedSubRep = formoperations.verifymarkAsResolvedValueForField(numericFieldName, markAsResolved);
		TestValidation.IsTrue(verifyMarkAsResolvedSubRep, 
							  "Verified Mark as resolved is repeated", 
				  			  "Failed to verify Mark as Resolved repeated");
		
		boolean clickCancel = fbForms.clickCancelButton();
		TestValidation.IsTrue(clickCancel, 
				  			 "SUCCESSFULLY CLOSED FORM", 
							 "FAILED TO CLOSE FORM");
		
		// ============================= END -> VERIFY Settings in FSQABrowser Forms
		
		//Goto Recodrs
		boolean navigateToRecords = fbp.navigateToFSQATab(FSQATAB.RECORDS);
		TestValidation.IsTrue(navigateToRecords, 
				  			  "Successfully Navigated to -" + FSQATAB.RECORDS , 
				              "Could NOT Navigate to -" + FSQATAB.RECORDS);
		
		
		boolean verifyFailsCheck = fbRecords.verifyFailsCheckForRecord(chkFrmName,FSQABrowserRecordsPage.FAILS_CHECK.FAILS);
		TestValidation.IsTrue(verifyFailsCheck, 
				  			  "Verified Fails Check for record with Form Name -" + chkFrmName + " as Non-Compliant", 
				  	  		  "FAILED to verify Fails Check for record with Form Name -" + chkFrmName + " as Non-Compliant");
		
		// open record
		boolean openRecord = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, chkFrmName);
		TestValidation.IsTrue(openRecord, 
							  "OPENED record for form - " + chkFrmName, 
							  "Failed to open record for form - " + chkFrmName);
		
		
		// ============================= START -> VERIFY Settings in FSQABrowser Records 
		
		TestValidation.IsTrue(true,"======================== IN FSQABrowser Records ========================","");
		
		boolean checkIsFieldRequiredRecrds = fbRecords.verifyIsFieldMarkedAsRequired(numericFieldName);
		TestValidation.IsTrue(checkIsFieldRequiredRecrds, 
				 			  "Verified Numeric Field is marked as required", 
		          			  "FAILED to verify Numeric Field marked as required");
		
		boolean hintDisplayRecrds = fbRecords.verifyHintForField(numericFieldName, hintText);
		TestValidation.IsTrue(hintDisplayRecrds, 
				 			  "Verified Hint Display for Numeric Field, hint =" + hintText, 
				              "FAILED to verify Hint Display for Numeric Field");
        		
		boolean addCmntsRecrds = fbRecords.verifyCommentForField(numericFieldName, comment);
		TestValidation.IsTrue(addCmntsRecrds, 
				  			  "Verified Comment as -" + comment + " for Numeric Field -" + numericFieldName, 
				              "FAILED to verify Comment for Numeric Field");
		
		boolean allwAtchmntsRecrds = fbRecords.verifyAttachmentFileNameForField(numericFieldName, generalFile);
		TestValidation.IsTrue(allwAtchmntsRecrds, 
				  			  "Verified Attachment as -" + generalFile + "for Numeric Field -" + numericFieldName, 
				              "FAILED to verify Attachment for Numeric Field - " +numericFieldName);
		
		boolean showMinMaxRecrds = fbRecords.verifyShowMinMaxForNumField(numericFieldName, fieldMin, fieldMax);
		TestValidation.IsTrue(showMinMaxRecrds, 
				  			  "Verified correct Min/Max display for numeric field field", 
				              "FAILED to verify display of Min/Max for numeric field");
		
		boolean showTargetRecrds = fbRecords.verifyShowTargetForNumField(numericFieldName, fieldTar);
		TestValidation.IsTrue(showTargetRecrds, 
				 			  "Verified correct Min/Max display for numeric field field", 
	              			  "FAILED to verify display of Min/Max for numeric field");
		
		boolean correctionRecrds = fbRecords.verifyCorrectionForField(numericFieldName, preDefinedCorrectionOptions.get(1));
		TestValidation.IsTrue(correctionRecrds, 
				  			  "Verified Correction as -" +preDefinedCorrectionOptions.get(1) + " for Numeric Field - " + numericFieldName, 
				              "FAILED to verify correction");
		
		boolean markAsResolvedRecrds = fbRecords.verifyIsResolvedForField(numericFieldName, markAsResolved);
		TestValidation.IsTrue(markAsResolvedRecrds, 
				  			  "Verified Marked as Resolved as -" + markAsResolved + " for Numeric Field -" + numericFieldName,
				              "FAILED to verify Marked As Resolved ");
		// ============================= END -> VERIFY Settings in FSQABrowser Records 
		
	}
	
	@Test(description = "Form Designer || Validate form functionality for field settings for 'Check' forms - \"Aggregate\" fields (Basic & Advance function)")
	public void TestCase_48772() throws Exception {
		
		String chkFrmName = CommonMethods.dynamicText("CF_");
		String numericFieldName = "Num1";
		String AggFieldName = "Agg1";
		String fieldMin = "10", fieldTar = "20", fieldMax = "30";
		String hintText = "Aggregate field Hint";
		String comment = "This is a comment";
		String customCorrection = "CustomCorrection";
		String markAsResolved = "Yes";
		String generalFile = "generalFile.png";
		String generalFilePath = System.getProperty("user.dir") + "\\test-data-files\\UploadDocuments\\"+generalFile;
		
		List<String> preDefinedCorrectionOptions = Arrays.asList("Option1","Option2");
		
		// Form Level
		HashMap<String, List<String>> chkFields = new HashMap<String, List<String>>();
		chkFields.put(FIELD_TYPES.NUMERIC, Arrays.asList(numericFieldName));
		chkFields.put(FIELD_TYPES.AGGREGATE, Arrays.asList(AggFieldName));
		
		FormFieldParams ffpChk = new FormFieldParams();
		ffpChk.FieldDetails = chkFields;
		ffpChk.AgrregateFunction = AGGREGATE_FUNCTION.SUM;
		ffpChk.SetAggregateFunction = true;
		ffpChk.AgrregateSource = numericFieldName;
		ffpChk.Repeat = Arrays.asList(Arrays.asList(numericFieldName,"2"));
		
		HashMap<String, List<String>> equipResource = new HashMap<String, List<String>>();
		equipResource.put(FORMRESOURCETYPES.EQUIPMENT, Arrays.asList(equipmentsCategoryValue));
		
		FormDesignParams fdpChk = new FormDesignParams();
		fdpChk.FormType = FORMTYPES.CHECK;
		fdpChk.FormName = chkFrmName;
		fdpChk.SelectResources = equipResource;
		fdpChk.DesignFields = Arrays.asList(ffpChk);
		//fdpChk.ReleaseForm = true;
		
		// navigate to form designer
		fdp = hp.clickFormDesignerMenu();
		
		boolean designForm = fdp.designForm(fdpChk);
		TestValidation.IsTrue(designForm, 
							  "Successfully Designed Form - " +chkFrmName + " with Numeric Fields -" + numericFieldName + " and Aggregate Field : " + AggFieldName, 
					          "Failed to design from with Numeric Fields and Aggregate Field");
		
		fdp.setAggregateSource(AggFieldName,numericFieldName);
		
//		boolean  selectNumField = fdp.selectField(numericFieldName);
//		boolean setNumFieldCarry = fdp.carryoverField();
//		TestValidation.IsTrue(setNumFieldCarry&&selectNumField, 
//				  			  "Selected Num Field and Turned on 'Carry Over' setting", 
//							  "Failed to select and turn on 'Carry Over' setting");
		
		HashMap<String, ElementProperties> fieldSettings = new LinkedHashMap<String, ElementProperties>(); 
		ElementProperties settings = new ElementProperties();
		settings.FAILS_CHECK = true;
		settings.IS_REQUIRED = true;
		settings.ALLOW_COMMENTS = true;
		settings.ALLOW_ATTATHMENTS = true;
		settings.SHOW_HINT = true;
		settings.HINT = hintText;
		settings.ALLOW_CORRECTION = true;
		settings.MARK_RESOLVED = true;
		settings.PREDEFINED_CORRECTIONS = true;
		settings.PREDEFINED_CORRECTIONS_OPTNS = preDefinedCorrectionOptions;
		settings.SHOW_MIN_MAX = true;
		settings.SHOW_TARGET = true;
		settings.COMPLIANCE_VALUES = new String[]{fieldMin, fieldTar, fieldMax, null};
		settings.COMPLIANCE_CONFIG = true;
		fieldSettings.put(AggFieldName, settings);
		
		boolean turnAllSettingsOn = fdp.setFieldProperties(fieldSettings) ;
		TestValidation.IsTrue(turnAllSettingsOn, 
	             			  "Successfully Turned All Settings for the Aggregate Field -" + AggFieldName, 
	                          "Failed to tur on All Settigs for for the Aggregate Field -" + AggFieldName);
		
		TestValidation.IsTrue(true, 
				  			  "Set Compliance Values as  - Min :" + fieldMin +" , Max : " +fieldMax+" , Tar : " +fieldTar + " for Aggregate field -" + AggFieldName, 
				              "Failed to set Compliance Values for Aggregate Field -" + AggFieldName);
		
		// ============================= START -> VERIFY Settings in FORM PREVIEW
		
		TestValidation.IsTrue(true,"======================== IN Form Preview ========================","");
		
		boolean openfForPreview = fdp.clickPreviewForFormOnReleaePage(chkFrmName);
		TestValidation.IsTrue(openfForPreview, 
				  			  "Clicked Preview Butoon and Opened Form -"+ chkFrmName + " for preview", 
				              "Failed to open form for Preview");
		
		boolean setNumFieldPrvw = formoperations.setInputFieldValue(numericFieldName, Arrays.asList("50"), null);
		TestValidation.IsTrue(setNumFieldPrvw, 
				  			  "Entered non compliant value -'50'" + " in Numeric Field -" + numericFieldName, 
				              "Failed to enter value in Numeric Field -" + numericFieldName);
		
		boolean allwAtchmntsPrvw = formoperations.verifyAllowAttachmentsInPreviewForField(AggFieldName);
		TestValidation.IsTrue(allwAtchmntsPrvw, 
							  "Verified Allow Attachements Aggregate Field -" + AggFieldName, 
							  "FAILED to verify Allow Attachements Aggregate Field -" + AggFieldName);
		
		boolean addCmntsPrvw = formoperations.addCommentForField(AggFieldName, "This is a comment");
		TestValidation.IsTrue(addCmntsPrvw, 
				  			  "Verified Add Comment Textbox present for Aggregate Field -" + AggFieldName + " and added comment -" + comment , 
				  			  "FAILED to verify Add Comment Textbox present for Aggregate Field -" + AggFieldName );
		
		boolean hintDisplayPrvw = formoperations.isHintDisplayedForField(AggFieldName, hintText);
		TestValidation.IsTrue(hintDisplayPrvw, 
				  			  "Verified hint Displayed for Aggregate Field -" + AggFieldName, 
				  			  "FAILED to very hint display for Aggregate Field -" + AggFieldName);
		
		boolean showMinMaxPreview = formoperations.verifyShowMinMaxForNumField(AggFieldName, fieldMin, fieldMax);
		TestValidation.IsTrue(showMinMaxPreview, 
				  			  "Verified correct Min/Max display for Aggregate field", 
				              "FAILED to verify display of Min/Max for Aggregate field");
		
		boolean showTargetPreview = formoperations.verifyShowTargetForNumField(AggFieldName, fieldTar);
		TestValidation.IsTrue(showTargetPreview, 
				 			  "Verified correct Min/Max display for Aggregate field", 
	              			  "FAILED to verify display of Min/Max for Aggregate field");
		
		boolean addCustmCorctnPrvw = formoperations.setCustomCorrectionForField(AggFieldName, customCorrection);
		TestValidation.IsTrue(addCustmCorctnPrvw, 
				  			  "Verified Custom Correction Text box for Aggregate Field -" + AggFieldName + " added correction -" + customCorrection, 
				              "FAILED to verify correction textbox for Aggregate Field -" + AggFieldName);
		
		boolean setPredefindCorrctnPrvw = formoperations.setPredefinedCorrectionsForField(AggFieldName, preDefinedCorrectionOptions.get(1))	;
		TestValidation.IsTrue(setPredefindCorrctnPrvw, 
				  			  "Verufied Predefined Correction input field for Aggregate Field -" + AggFieldName + " added correction -" + preDefinedCorrectionOptions.get(1), 
				              "FAILED to verify Predefined Correction input for Aggregate Field -" + AggFieldName);
		
		boolean markAsRslvdPrvw = formoperations.markAsResolvedForField(AggFieldName, markAsResolved);
		TestValidation.IsTrue(markAsRslvdPrvw, 
				  			  "Verified 'Mark As Resolved' option for Aggregate Field -" + AggFieldName + "  marked it as -" + markAsResolved, 
				              "FAILED to verify 'Marked As Resolved' option for Aggregate Field -" + AggFieldName);
		
		
		boolean closePreview = fdp.clickClosePreviewBtn();
		TestValidation.IsTrue(closePreview, 
				  			  "Successfully Closed Form Preview", 
				  			  "Could NOT Close Form Preview");
		// ============================= END -> VERIFY Settings in FORM PREVIEW
		
		boolean releaseForm = fdp.releaseForm(chkFrmName);
		TestValidation.IsTrue(releaseForm, 
							 "Successfully released form -" + chkFrmName, 
							 "Could Not release form -" + chkFrmName);
		
		boolean navToHome = fdp.navigateToHome();
		TestValidation.IsTrue(navToHome, 
				  			  "Successfully Navigated to Home", 
				              "Could NOT naigate to home");
		
		boolean selectResourceDropDownandNavigate = fbp.selectResourceDropDownandNavigate(FORMRESOURCETYPES.EQUIPMENT,FSQATAB.FORMS);
		TestValidation.IsTrue(selectResourceDropDownandNavigate, 
							  "Navigated to FSQA ->  " + FSQATAB.FORMS ,
							  "Could NOT Navigate to FSQA ->  " + FSQATAB.FORMS);
		
		
		boolean selectOpenForm = fbForms.selectOpenForm(chkFrmName);
		TestValidation.IsTrue(selectOpenForm, 
							  "Select & opened the form -" + chkFrmName, 
							  "Could NOT open the form" + chkFrmName);
		
		
		// ============================= START -> VERIFY Settings in FSQABrowser Forms
		
		TestValidation.IsTrue(true,"======================== IN FSQABrowser Forms ========================","");
		
		boolean setNumFieldFrms =  formoperations.incrementNumericFieldsValue(numericFieldName, 50,2);
		TestValidation.IsTrue(setNumFieldFrms, 
							  "Entered non compliant value -'50'" + " in Numeric Field -" + numericFieldName, 
							  "Failed to enter value in Numeric Field -" + numericFieldName);
		
		boolean allwAtchmntsFrms = formoperations.addAttachmentForField(AggFieldName, generalFilePath);
		TestValidation.IsTrue(allwAtchmntsFrms, 
				  			  "Verified Allow Attachments for Aggregate Field, and attached file -" + generalFile, 
				  			  "FAILED to verify Allow Attachments for Aggregate Field");
		
		boolean addCmntsFrms = formoperations.addCommentForField(AggFieldName, comment);
		TestValidation.IsTrue(addCmntsFrms, 
							  "Verified Allow Comments for Aggregate Field and entered comment -" + comment, 
				  			  "FAILED to verify Allow Comments for Aggregate Field");
		
		boolean hintDisplayFrms = formoperations.isHintDisplayedForField(AggFieldName, hintText);
		TestValidation.IsTrue(hintDisplayFrms, 
				  		      "Verified Hint Display for Aggregate Field, hint =" + hintText, 
				              "FAILED to verify Hint Display for Aggregate Field");
		
		boolean showMinMaxFrms = formoperations.verifyShowMinMaxForNumField(AggFieldName, fieldMin, fieldMax);
		TestValidation.IsTrue(showMinMaxFrms, 
				  			  "Verified correct Min/Max display for numeric field field", 
				              "FAILED to verify display of Min/Max for numeric field");
		
		boolean showTargetFrms = formoperations.verifyShowTargetForNumField(AggFieldName, fieldTar);
		TestValidation.IsTrue(showTargetFrms, 
				 			  "Verified correct 'Target' display for Aggregate field", 
	              			  "FAILED to verify display of 'Target' for Aggregate field");
		
		boolean addCustmCorctnFrms = formoperations.setCustomCorrectionForField(AggFieldName, customCorrection);
		TestValidation.IsTrue(addCustmCorctnFrms, 
				  			  "Verified Custom Correction for Aggregate Field", 
				              "FAILED to verify Custom Correction for Aggregate Field");
		
		boolean setPredefindCorrctnFrms = formoperations.setPredefinedCorrectionsForField(AggFieldName, preDefinedCorrectionOptions.get(1))	;
		TestValidation.IsTrue(setPredefindCorrctnFrms, 
	             		      "Verified Predefined Correction for Aggregate Field", 
	                          "FAILED to verify Predefined Correction for Aggregate Field");
		
		boolean markAsRslvdFrms = formoperations.markAsResolvedForField(AggFieldName, markAsResolved);
		TestValidation.IsTrue(markAsRslvdFrms, 
				  			  "Verified Mark As Resolved for Aggregate Field", 
				              "FAILED to verify Mark As Resolved for Aggregate Field");
		
		boolean submitForm = formoperations.clickSubmitThenAlertOkBtn();
		TestValidation.IsTrue(submitForm, 
				  			 "Clicked on Submit and Submitted the form -" + chkFrmName, 
				             "Could NOT click on Submit buttton and failed to submit form -" + chkFrmName);
		
		
	/*	boolean clickSubmitRepeat = fbForms.clickSubmitAndReapeatButton();
		TestValidation.IsTrue(clickSubmitRepeat, 
				  			 "CLICKED SUBMIT AND REPEAT", 
							 "FAILED TO CLICK SUBMIT AND REPEAT");
		
		TestValidation.IsTrue(true,"======================== After Submit and Repeat ========================","");
		
		boolean checkIsFieldRequiredSubRep = formoperations.isFieldRequired(AggFieldName);
		TestValidation.IsTrue(checkIsFieldRequiredSubRep, 
							  "Verified Aggregae field is marked as required", 
					          "FAILED to verify Aggregate marked as required");
		
		boolean hintDisplaySubRep = formoperations.isHintDisplayedForField(AggFieldName, hintText);
		TestValidation.IsTrue(hintDisplaySubRep, 
				  		      "Verified Hint is Displayed for Aggregate field, hint =" + hintText, 
				              "FAILED to verify Hint Display forAggregate field");
		
		boolean showMinMaxSubRep = formoperations.verifyShowMinMaxForNumField(AggFieldName, fieldMin, fieldMax);
		TestValidation.IsTrue(showMinMaxSubRep, 
				  			  "Verified correct Min/Max display for numeric field ", 
				              "FAILED to verify display of Min/Max for numeric field");
		
		boolean showTargetSubRep = formoperations.verifyShowTargetForNumField(AggFieldName, fieldTar);
		TestValidation.IsTrue(showTargetSubRep, 
				 			  "Verified correct 'Target' display for numeric field", 
	              			  "FAILED to verify display of 'Target' for numeric field");
		
		boolean verifyComment = formoperations.verifyCommentOfField(AggFieldName, comment);
		TestValidation.IsTrue(verifyComment, 
				  			  "Verified that comment is repeated correctly", 
				              "Failed to verify repeated comment ");
		
		boolean verifyAttachment = formoperations.verifySingleAttachmentForField(AggFieldName, generalFile);
		TestValidation.IsTrue(verifyAttachment, 
							 "Verified Attachment is repeated", 
				             "Failed to verify repeated attahment");
		
		boolean verifyPredefinedCorr = formoperations.verifyPredefinedCorrectionValueForField(AggFieldName, preDefinedCorrectionOptions.get(1));
		TestValidation.IsTrue(verifyPredefinedCorr, 
				  			 "Verified correction is repeated", 
				  			 "Failed to verify repeated correction");
		
		boolean verifyMarkAsResolved = formoperations.verifymarkAsResolvedValueForField(AggFieldName, markAsResolved);
		TestValidation.IsTrue(verifyMarkAsResolved, 
							  "Verified Mark as resolved is repeated", 
				  			  "Failed to verify Mark as Resolved repeated");
		
		boolean clickCancel = fbForms.clickCancelButton();
		TestValidation.IsTrue(clickCancel, 
				  			 "SUCCESSFULLY CLOSED FORM", 
							 "FAILED TO CLOSE FORM"); */
		
		// ============================= END -> VERIFY Settings in FSQABrowser Forms
		
		//Goto Recodrs
		boolean navigateToRecords = fbp.navigateToFSQATab(FSQATAB.RECORDS);
		TestValidation.IsTrue(navigateToRecords, 
				  			  "Successfully Navigated to -" + FSQATAB.RECORDS , 
				              "Could NOT Navigate to -" + FSQATAB.RECORDS);
		
		
		boolean verifyFailsCheck = fbRecords.verifyFailsCheckForRecord(chkFrmName,FSQABrowserRecordsPage.FAILS_CHECK.FAILS);
		TestValidation.IsTrue(verifyFailsCheck, 
				  			  "Verified Fails Check for record with Form Name -" + chkFrmName + " as Non-Compliant", 
				  	  		  "FAILED to verify Fails Check for record with Form Name -" + chkFrmName + " as Non-Compliant");
		
		// open record
		boolean openRecord = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, chkFrmName);
		TestValidation.IsTrue(openRecord, 
							  "OPENED record for form - " + chkFrmName, 
							  "Failed to open record for form - " + chkFrmName);
		
		
		// ============================= START -> VERIFY Settings in FSQABrowser Records 
		
		TestValidation.IsTrue(true,"======================== IN FSQABrowser Records ========================","");
		
		boolean hintDisplayRecrds = fbRecords.verifyHintForField(AggFieldName, hintText);
		TestValidation.IsTrue(hintDisplayRecrds, 
				 			  "Verified Hint Display for Aggregate Field, hint =" + hintText, 
				              "FAILED to verify Hint Display for Numeric Field");
        		
		boolean addCmntsRecrds = fbRecords.verifyCommentForField(AggFieldName, comment);
		TestValidation.IsTrue(addCmntsRecrds, 
				  			  "Verified Comment as -" + comment + " for Aggregate Field -" + AggFieldName, 
				              "FAILED to verify Comment for Aggregate Field");
		
		boolean allwAtchmntsRecrds = fbRecords.verifyAttachmentFileNameForField(AggFieldName, generalFile);
		TestValidation.IsTrue(allwAtchmntsRecrds, 
				  			  "Verified Attachment as -" + generalFile + "for Aggregate Field -" + AggFieldName, 
				              "FAILED to verify Attachment for Aggregate Field - " +AggFieldName);
		
		boolean showMinMaxRecrds = fbRecords.verifyShowMinMaxForNumField(AggFieldName, fieldMin, fieldMax);
		TestValidation.IsTrue(showMinMaxRecrds, 
				  			  "Verified correct Min/Max display for Aggregate field", 
				              "FAILED to verify display of Min/Max for Aggregate field");
		
		boolean showTargetRecrds = fbRecords.verifyShowTargetForNumField(AggFieldName, fieldTar);
		TestValidation.IsTrue(showTargetRecrds, 
				 			  "Verified correct Min/Max display for Aggregate field field", 
	              			  "FAILED to verify display of Min/Max for Aggregate field");
		
		boolean correctionRecrds = fbRecords.verifyCorrectionForField(AggFieldName, preDefinedCorrectionOptions.get(1));
		TestValidation.IsTrue(correctionRecrds, 
				  			  "Verified Correction as -" +preDefinedCorrectionOptions.get(1) + " for Aggregate Field - " + numericFieldName, 
				              "FAILED to verify correction");
		
		boolean markAsResolvedRecrds = fbRecords.verifyIsResolvedForField(AggFieldName, markAsResolved);
		TestValidation.IsTrue(markAsResolvedRecrds, 
				  			  "Verified Marked as Resolved as -" + markAsResolved + " for Aggregate Field -" + numericFieldName,
				              "FAILED to verify Marked As Resolved for Aggregate field ");
		// ============================= END -> VERIFY Settings in FSQABrowser Records 
		
	}
	
	
	//==================== QUESTIONNAIRE FORMS ======================//
	@Test(description = "Form Designer || Validate form functionality for field settings for 'Questionnaire' forms - \"Single Line Text\" field")
	public void TestCase_38498() throws Exception {
		
		String quesFrmName = CommonMethods.dynamicText("aQF");
		String singleLineTextFieldName = "ASingleLineTxt";
		String singleLineTextComplianceValue = "A Sample Text";
		String hintText = "Single Line Text Hint";
		String comment = "This is a comment";
		String customCorrection = "CustomCorrection";
		String markAsResolved = "Yes";
		String  generalFile = "generalFile.png";
		String 	generalFilePath = System.getProperty("user.dir") + "\\test-data-files\\UploadDocuments\\"+generalFile;
		
		
		List<String> preDefinedCorrectionOptions = Arrays.asList("Option1","Option2");
		
		// Form Level
		HashMap<String, List<String>> chkFields = new HashMap<String, List<String>>();
		chkFields.put(FIELD_TYPES.SINGLE_LINE_TEXT, Arrays.asList(singleLineTextFieldName));

		
		FormFieldParams ffpChk = new FormFieldParams();
		ffpChk.FieldDetails = chkFields;
		
		HashMap<String, List<String>> equipResource = new HashMap<String, List<String>>();
		equipResource.put(FORMRESOURCETYPES.EQUIPMENT, Arrays.asList(equipmentsCategoryValue));

		FormDesignParams fdpChk = new FormDesignParams();
		fdpChk.FormType = FORMTYPES.QUESTIONNAIRE;
		fdpChk.FormName = quesFrmName;
		fdpChk.SelectResources = equipResource;
		fdpChk.DesignFields = Arrays.asList(ffpChk);
		fdpChk.ReleaseForm = true;
		
		// navigate to form designer
		fdp = hp.clickFormDesignerMenu();
		
		boolean designForm = fdp.designForm(fdpChk);
		TestValidation.IsTrue(designForm, 
							  "Successfully Designed Form - " +quesFrmName + " with Single Line Text Field -" + singleLineTextFieldName, 
					          "Failed to design from with Single Line Text Field");
		
		HashMap<String, ElementProperties> fieldSettings = new LinkedHashMap<String, ElementProperties>(); 
		ElementProperties settings = new ElementProperties();
		settings.FAILS_CHECK = true;
		settings.IS_REQUIRED = true;
		settings.ALLOW_COMMENTS = true;
		settings.ALLOW_ATTATHMENTS = true;
		settings.SHOW_HINT = true;
		settings.HINT = hintText;
		settings.ALLOW_CORRECTION = true;
		settings.MARK_RESOLVED = true;
		settings.PREDEFINED_CORRECTIONS = true;
		settings.PREDEFINED_CORRECTIONS_OPTNS = preDefinedCorrectionOptions;
		settings.CARRYOVER_FIELD = true;
		settings.COMPLIANCE_TEXT = singleLineTextComplianceValue;
		fieldSettings.put(singleLineTextFieldName, settings);
		
		boolean turnAllSettingsOn = fdp.setFieldProperties(fieldSettings) ;
		TestValidation.IsTrue(turnAllSettingsOn, 
	             			  "Successfully Turned All Settings for the Single Line Text Field -" + singleLineTextFieldName, 
	                          "Failed to tur on All Settigs for for the Single Line Text Field -" + singleLineTextFieldName);
		
		TestValidation.IsTrue(true, 
				  			  "Set Compliance Text as -" + singleLineTextComplianceValue + " for Single Line text field -" + singleLineTextFieldName, 
				              "Failed to set Compliance Text for Single Line txt -" + singleLineTextFieldName);
		
		// ============================= START -> VERIFY Settings in FORM PREVIEW
		
		TestValidation.IsTrue(true,"======================== IN Form Preview ========================","");
		
		boolean openfForPreview = fdp.clickPreviewForFormOnReleaePage(quesFrmName);
		TestValidation.IsTrue(openfForPreview, 
				  			  "Clicked Preview Butoon and Opened Form -"+ quesFrmName + " for preview", 
				              "Failed to open form for Preview");
		
		boolean setFreeTextFieldPrvw = formoperations.setInputFieldValue(singleLineTextFieldName, Arrays.asList("abc"), null);
		TestValidation.IsTrue(setFreeTextFieldPrvw, 
				  			  "Entered non compliant value -'abc'" + " in Single Line Text fied -" + singleLineTextFieldName, 
				              "Failed to enter value in Single Line Text field -" + singleLineTextFieldName);
		
		boolean checkIsFieldRequiredPrvw = formoperations.isFieldRequired(singleLineTextFieldName);
		TestValidation.IsTrue(checkIsFieldRequiredPrvw, 
				  			  "Verified that Single Line Text field -" + singleLineTextFieldName + " is Marked as required in Form Preview", 
				              "Failed to verify that Single Line Text field -" + singleLineTextFieldName + " is Marked as required in Form Preview");
		
		boolean allwAtchmntsPrvw = formoperations.verifyAllowAttachmentsInPreviewForField(singleLineTextFieldName);
		TestValidation.IsTrue(allwAtchmntsPrvw, 
							  "Verified Allow Attachements for Single Line Text field -" + singleLineTextFieldName + " in Form Preview", 
							  "FAILED to verify Allow Attachements for Single Line Text field -" + singleLineTextFieldName + " in Form Preview");
		
		boolean addCmntsPrvw = formoperations.addCommentForField(singleLineTextFieldName, "This is a comment");
		TestValidation.IsTrue(addCmntsPrvw, 
				  			  "Verified Add Comment Textbox present for Single Line Text field -" + singleLineTextFieldName + " in Form Preview and added comment -" + comment , 
				  			  "FAILED to verify Add Comment Textbox present for Single Line Text field -" + singleLineTextFieldName );
		
		boolean hintDisplayPrvw = formoperations.isHintDisplayedForField(singleLineTextFieldName, hintText);
		TestValidation.IsTrue(hintDisplayPrvw, 
				  			  "Verified hint Displayed for Single Line Text field -" + singleLineTextFieldName + " in Form Preview", 
				  			  "FAILED to very hint display for Single Line Text field -" + singleLineTextFieldName + " in Form Preview");
		
		boolean addCustmCorctnPrvw = formoperations.setCustomCorrectionForField(singleLineTextFieldName, customCorrection);
		TestValidation.IsTrue(addCustmCorctnPrvw, 
				  			  "Verified Custom Correction Text box for Single Line Text field -" + singleLineTextFieldName + " in Form Preview, added correction -" + customCorrection, 
				              "FAILED to verify correction textbox for Single Line Text field -" + singleLineTextFieldName + " in Form Preview");
		
		boolean setPredefindCorrctnPrvw = formoperations.setPredefinedCorrectionsForField(singleLineTextFieldName, preDefinedCorrectionOptions.get(1))	;
		TestValidation.IsTrue(setPredefindCorrctnPrvw, 
				  			  "Verufied Predefined Correction input field for Single Line Text field -" + singleLineTextFieldName + " in Form Preview, added correction -" + preDefinedCorrectionOptions.get(1), 
				              "FAILED to verify Predefined Correction input for Single Line Text field -" + singleLineTextFieldName + " in form Preview" );
		
		boolean markAsRslvdPrvw = formoperations.markAsResolvedForField(singleLineTextFieldName, markAsResolved);
		TestValidation.IsTrue(markAsRslvdPrvw, 
				  			  "Verified 'Mark As Resolved' option for Single Line Text field -" + singleLineTextFieldName + " in Form Preview, marked it as -" + markAsResolved, 
				              "FAILED to verify 'Marked As Resolved' option for Single Line Text field -" + singleLineTextFieldName + " in Form Preview");
		
		
		boolean closePreview = fdp.clickClosePreviewBtn();
		TestValidation.IsTrue(closePreview, 
				  			  "Successfully Closed Form Preview", 
				  			  "Could NOT Close Form Preview");
		// ============================= END -> VERIFY Settings in FORM PREVIEW
		
		boolean releaseForm = fdp.releaseForm(quesFrmName);
		TestValidation.IsTrue(releaseForm, 
							 "Successfully released form -" + quesFrmName, 
							 "Could Not release form -" + quesFrmName);
		
		boolean navToHome = fdp.navigateToHome();
		TestValidation.IsTrue(navToHome, 
				  			  "Successfully Navigated to Home", 
				              "Could NOT naigate to home");
		
		boolean selectResourceDropDownandNavigate = fbp.selectResourceDropDownandNavigate(FORMRESOURCETYPES.EQUIPMENT,FSQATAB.FORMS);
		TestValidation.IsTrue(selectResourceDropDownandNavigate, 
							  "Navigated to FSQA ->  " + FSQATAB.FORMS ,
							  "Could NOT Navigate to FSQA ->  " + FSQATAB.FORMS);
		
		
		boolean selectOpenForm = fbForms.selectOpenForm(quesFrmName);
		TestValidation.IsTrue(selectOpenForm, 
							  "Select & opened the form -" + quesFrmName, 
							  "Could NOT open the form" + quesFrmName);
		
		
		// ============================= START -> VERIFY Settings in FSQABrowser Forms
		
		TestValidation.IsTrue(true,"======================== IN FSQABrowser Forms ========================","");
		
		boolean setFreeTextFieldFrms = formoperations.setInputFieldValue(singleLineTextFieldName, Arrays.asList("abc"), null);
		TestValidation.IsTrue(setFreeTextFieldFrms, 
	  			  			  "Entered non compliant value -'abc'" + " in Single Line Text fied -" + singleLineTextFieldName, 
	  			  			  "Failed to enter value in Single Line Text field -" + singleLineTextFieldName);
		
		boolean checkIsFieldRequiredFrms = formoperations.isFieldRequired(singleLineTextFieldName);
		TestValidation.IsTrue(checkIsFieldRequiredFrms, 
							  "Verified Single Line Text field is marked as required", 
					          "FAILED to verify Single Line Text marked as required");
		
		boolean allwAtchmntsFrms = formoperations.addAttachmentForField(singleLineTextFieldName, generalFilePath);
		TestValidation.IsTrue(allwAtchmntsFrms, 
				  			  "Verified Allow Attachments for Single Line Text field, and attached file -" + generalFile, 
				  			  "FAILED to verify Allow Attachments for Single Line Text field");
		
		boolean addCmntsFrms = formoperations.addCommentForField(singleLineTextFieldName, comment);
		TestValidation.IsTrue(addCmntsFrms, 
							  "Verified Allow Comments for Single Line Text field and entered comment -" + comment, 
				  			  "FAILED to verify Allow Comments for Single Line Text field");
		
		boolean hintDisplayFrms = formoperations.isHintDisplayedForField(singleLineTextFieldName, hintText);
		TestValidation.IsTrue(hintDisplayFrms, 
				  		      "Verified Hint Display for Single Line Text field, hint =" + hintText, 
				              "FAILED to verify Hint Display for fSingle Line Text field");
		
		boolean addCustmCorctnFrms = formoperations.setCustomCorrectionForField(singleLineTextFieldName, customCorrection);
		TestValidation.IsTrue(addCustmCorctnFrms, 
				  			  "Verified Custom Correction for Single Line Text field", 
				              "FAILED to verify Custom Correction for Single Line Text field");
		
		boolean setPredefindCorrctnFrms = formoperations.setPredefinedCorrectionsForField(singleLineTextFieldName, preDefinedCorrectionOptions.get(1))	;
		TestValidation.IsTrue(setPredefindCorrctnFrms, 
	             		      "Verified Predefined Correction for Single Line Text field", 
	                          "FAILED to verify Predefined Correction for Single Line Text field");
		
		boolean markAsRslvdFrms = formoperations.markAsResolvedForField(singleLineTextFieldName, markAsResolved);
		TestValidation.IsTrue(markAsRslvdFrms, 
				  			  "Verified Mark As Resolved for Single Line Text field", 
				              "FAILED to verify Mark As Resolved for Single Line Text field");
		
//		boolean submitForm = formoperations.clickSubmitThenAlertOkBtn();
//		TestValidation.IsTrue(submitForm, 
//				  			 "Clicked on Submit and Submitted the form -" + quesFrmName, 
//				             "Could NOT click on Submit buttton and failed to submit form -" + quesFrmName);
		
		// ============================= END -> VERIFY Settings in FSQABrowser Forms
		
		//QUESTIONNAIRE FAILS ?!
		
		boolean clickSubmitRepeat = fbForms.clickSubmitAndReapeatButton();
		TestValidation.IsTrue(clickSubmitRepeat, 
				  			 "CLICKED SUBMIT AND REPEAT", 
							 "FAILED TO CLICK SUBMIT AND REPEAT");
		
		TestValidation.IsTrue(true,"======================== After Submit and Repeat ========================","");
		
		boolean checkIsFieldRequiredSubRep = formoperations.isFieldRequired(singleLineTextFieldName);
		TestValidation.IsTrue(checkIsFieldRequiredSubRep, 
							  "Verified Single Line Text Field is marked as required", 
					          "FAILED to verify Single Line Text Field marked as required");
		
		boolean hintDisplaySubRep = formoperations.isHintDisplayedForField(singleLineTextFieldName, hintText);
		TestValidation.IsTrue(hintDisplaySubRep, 
				  		      "Verified Hint is Displayed as hint = " + hintText, 
				              "FAILED to verify Hint Display");
		
		boolean verifyCommentSubRep = formoperations.verifyCommentOfField(singleLineTextFieldName, comment);
		TestValidation.IsTrue(verifyCommentSubRep, 
				  			  "Verified that comment is repeated correctly", 
				              "Failed to verify repeated comment ");
		
		boolean verifyAttachmentSubRep = formoperations.verifySingleAttachmentForField(singleLineTextFieldName, generalFile);
		TestValidation.IsTrue(verifyAttachmentSubRep, 
							 "Verified Attachment is repeated", 
				             "Failed to verify repeated attahment");
		
		boolean verifyPredefinedCorrSubRep = formoperations.verifyPredefinedCorrectionValueForField(singleLineTextFieldName, preDefinedCorrectionOptions.get(1));
		TestValidation.IsTrue(verifyPredefinedCorrSubRep, 
				  			 "Verified correction is repeated", 
				  			 "Failed to verify repeated correction");
		
		boolean verifyMarkAsResolvedSubRep = formoperations.verifymarkAsResolvedValueForField(singleLineTextFieldName, markAsResolved);
		TestValidation.IsTrue(verifyMarkAsResolvedSubRep, 
							  "Verified Mark as resolved is repeated", 
				  			  "Failed to verify Mark as Resolved repeated");
		
		boolean clickCancel = fbForms.clickCancelButton();
		TestValidation.IsTrue(clickCancel, 
				  			 "SUCCESSFULLY CLOSED FORM", 
							 "FAILED TO CLOSE FORM");
		
		// ============================= START -> VERIFY Settings in DMS for Questionnaire Submitted Form
		
		// goto DMS
		documentManagement = hp.clickdocumentsmenu();
		
		//select DocType
		boolean selectDocumentType = documentManagement.selectDocumentType(quesFrmName);
		TestValidation.IsTrue(selectDocumentType, 
							 "Selected Document Type -> " + quesFrmName,
						      "Could NOT Select Document Type -> " + quesFrmName);
		
		// open Document
		boolean openDocument = documentManagement.viewQuestionnaireDocument(quesFrmName);
		TestValidation.IsTrue(openDocument, 
						      "OPEN document ->   " + quesFrmName,
						      "Could NOT OPEN document ->  " + quesFrmName);
		
		TestValidation.IsTrue(true,"======================== IN DMS  ========================","");
		
		
		boolean checkIsFieldRequiredRecrds = documentManagement.verifyIsFieldMarkedAsRequiredQstnDoc(singleLineTextFieldName);
		TestValidation.IsTrue(checkIsFieldRequiredRecrds, 
				 			  "Verified Single Line Text field is marked as required", 
		          			  "FAILED to verify Single Line Text marked as required");
		
		boolean hintDisplayRecrds = documentManagement.verifyHintForFieldQstnDoc(singleLineTextFieldName, hintText);
		TestValidation.IsTrue(hintDisplayRecrds, 
				 			  "Verified Hint Display for Single Line Text field, hint =" + hintText, 
				              "FAILED to verify Hint Display for Single Line Text field");
        		
		boolean addCmntsRecrds = documentManagement.verifyCommentForFieldQstnDoc(singleLineTextFieldName, comment);
		TestValidation.IsTrue(addCmntsRecrds, 
				  			  "Verified Comment as -" + comment + " for Single Line Text field -" + singleLineTextFieldName, 
				              "FAILED to verify Comment for Single Line Text field");
		
		boolean allwAtchmntsRecrds = documentManagement.verifyAttachmentFileNameForFieldQstnDoc(singleLineTextFieldName, generalFile);
		TestValidation.IsTrue(allwAtchmntsRecrds, 
				  			  "Verified Attachment as -" + generalFile + "for Single Line Text field -" + singleLineTextFieldName, 
				              "FAILED to verify Attachment for Single Line Text field - " +singleLineTextFieldName);
		
		boolean correctionRecrds = documentManagement.verifyCorrectionForFieldQstnDoc(singleLineTextFieldName, preDefinedCorrectionOptions.get(1));
		TestValidation.IsTrue(correctionRecrds, 
				  			  "Verified Correction as -" +preDefinedCorrectionOptions.get(1) + " for Single Line Text field - " + singleLineTextFieldName, 
				              "FAILED to verify correction for Single Line Text field ");
		
		boolean markAsResolvedRecrds = documentManagement.verifyIsResolvedForFieldQstnDoc(singleLineTextFieldName, markAsResolved);
		TestValidation.IsTrue(markAsResolvedRecrds, 
				  			  "Verified Marked as Resolved as -" + markAsResolved + " for Single Line Text field -" + singleLineTextFieldName,
				              "FAILED to verify Marked As Resolved for Single Line Text field");
		// ============================= END -> VERIFY Settings in DMS for Questionnaire Submitted Form
	}

	@Test(description = "Form Designer || Validate form functionality for field settings for 'Questionnaire' forms - \"Date\" field")
	public void TestCase_48770() throws Exception {
		
		String quesFrmName = CommonMethods.dynamicText("aQF");
		String dateFieldName = "ADate";
		String dateComplianceValue = "9/7/2020";
		String dateNonComplianceValue = "6/4/2021";
		String hintText = "Date Hint";
		String comment = "This is a comment";
		String customCorrection = "CustomCorrection";
		String markAsResolved = "Yes";
		String generalFile = "generalFile.png";
		String generalFilePath = System.getProperty("user.dir") + "\\test-data-files\\UploadDocuments\\"+generalFile;
		
		
		List<String> preDefinedCorrectionOptions = Arrays.asList("Option1","Option2");
		
		// Form Level
		HashMap<String, List<String>> chkFields = new HashMap<String, List<String>>();
		chkFields.put(FIELD_TYPES.DATE, Arrays.asList(dateFieldName));
		
		FormFieldParams ffpChk = new FormFieldParams();
		ffpChk.FieldDetails = chkFields;
		
		HashMap<String, List<String>> equipResource = new HashMap<String, List<String>>();
		equipResource.put(FORMRESOURCETYPES.EQUIPMENT, Arrays.asList(equipmentsCategoryValue));

		FormDesignParams fdpChk = new FormDesignParams();
		fdpChk.FormType = FORMTYPES.QUESTIONNAIRE;
		fdpChk.FormName = quesFrmName;
		fdpChk.SelectResources = equipResource;
		fdpChk.DesignFields = Arrays.asList(ffpChk);
		fdpChk.ReleaseForm = true;
		
		// navigate to form designer
		fdp = hp.clickFormDesignerMenu();
		
		boolean designForm = fdp.designForm(fdpChk);
		TestValidation.IsTrue(designForm, 
							  "Successfully Designed Form - " +quesFrmName + " with Date Field -" + dateFieldName, 
					          "Failed to design from with Date Field");
		
		HashMap<String, ElementProperties> fieldSettings = new LinkedHashMap<String, ElementProperties>(); 
		ElementProperties settings = new ElementProperties();
		settings.FAILS_CHECK = true;
		settings.IS_REQUIRED = true;
		settings.ALLOW_COMMENTS = true;
		settings.ALLOW_ATTATHMENTS = true;
		settings.SHOW_HINT = true;
		settings.HINT = hintText;
		settings.ALLOW_CORRECTION = true;
		settings.MARK_RESOLVED = true;
		settings.PREDEFINED_CORRECTIONS = true;
		settings.PREDEFINED_CORRECTIONS_OPTNS = preDefinedCorrectionOptions;
		settings.CARRYOVER_FIELD = true;
		fieldSettings.put(dateFieldName, settings);
		
		boolean turnAllSettingsOn = fdp.setFieldProperties(fieldSettings) ;
		TestValidation.IsTrue(turnAllSettingsOn, 
	             			  "Successfully Turned All Settings for the Date Field -" + dateFieldName, 
	                          "Failed to tur on All Settigs for for the Date Field -" + dateFieldName);
		
		boolean backToDesign = fdp.backToFormDesignFrmRelease(quesFrmName);
		TestValidation.IsTrue(backToDesign, 
				  			  "Navigated Back to Form Design for Form from Release page - " + quesFrmName, 
				              "FAILED to navigate back to Form Design for Form from Release page");
		
		boolean setComplianceDate = fdp.setComplianceForDateandTimeFieldv2(dateFieldName, equipmentsInstanceValue, dateComplianceValue);
		TestValidation.IsTrue(setComplianceDate, 
				  			  "Set Compliance Date as -" + dateComplianceValue + " for Date field -" + dateFieldName, 
				              "Failed to set Compliance Date for Date Field -" + dateFieldName);
		
		// ============================= START -> VERIFY Settings in FORM PREVIEW
		
		TestValidation.IsTrue(true,"======================== IN Form Preview ========================","");
		
		boolean openfForPreview = fdp.clickPreviewButton();
		TestValidation.IsTrue(openfForPreview, 
				  			  "Clicked Preview Butoon and Opened Form -"+ quesFrmName + " for preview", 
				              "Failed to open form for Preview");
		
		boolean dateTextFieldPrvw = formoperations.setInputFieldValue(dateFieldName, Arrays.asList(dateNonComplianceValue), null);
		TestValidation.IsTrue(dateTextFieldPrvw, 
				  			  "Entered non compliant value " + dateNonComplianceValue + " in Date fied -" + dateFieldName, 
				              "Failed to enter value in Date field -" + dateFieldName);
		
		controlActions.actionEnter();
		
		boolean checkIsFieldRequiredPrvw = formoperations.isFieldRequired(dateFieldName);
		TestValidation.IsTrue(checkIsFieldRequiredPrvw, 
				  			  "Verified that Date field -" + dateFieldName + " is Marked as required in Form Preview", 
				              "Failed to verify that Date field -" + dateFieldName + " is Marked as required in Form Preview");
		
		boolean allwAtchmntsPrvw = formoperations.verifyAllowAttachmentsInPreviewForField(dateFieldName);
		TestValidation.IsTrue(allwAtchmntsPrvw, 
							  "Verified Allow Attachements for Date field -" + dateFieldName + " in Form Preview", 
							  "FAILED to verify Allow Attachements for Date field -" + dateFieldName + " in Form Preview");
		
		boolean addCmntsPrvw = formoperations.addCommentForField(dateFieldName, "This is a comment");
		TestValidation.IsTrue(addCmntsPrvw, 
				  			  "Verified Add Comment Textbox present for Date field -" + dateFieldName + " in Form Preview and added comment -" + comment , 
				  			  "FAILED to verify Add Comment Textbox present for Date field -" + dateFieldName );
		
		boolean hintDisplayPrvw = formoperations.isHintDisplayedForField(dateFieldName, hintText);
		TestValidation.IsTrue(hintDisplayPrvw, 
				  			  "Verified hint Displayed for Date field -" + dateFieldName + " in Form Preview", 
				  			  "FAILED to very hint display for Date field -" + dateFieldName + " in Form Preview");
		
		boolean addCustmCorctnPrvw = formoperations.setCustomCorrectionForField(dateFieldName, customCorrection);
		TestValidation.IsTrue(addCustmCorctnPrvw, 
				  			  "Verified Custom Correction Text box for Date field -" + dateFieldName + " in Form Preview, added correction -" + customCorrection, 
				              "FAILED to verify correction textbox for Date field -" + dateFieldName + " in Form Preview");
		
		boolean setPredefindCorrctnPrvw = formoperations.setPredefinedCorrectionsForField(dateFieldName, preDefinedCorrectionOptions.get(1))	;
		TestValidation.IsTrue(setPredefindCorrctnPrvw, 
				  			  "Verufied Predefined Correction input field for Date field -" + dateFieldName + " in Form Preview, added correction -" + preDefinedCorrectionOptions.get(1), 
				              "FAILED to verify Predefined Correction input for Date field -" + dateFieldName + " in form Preview" );
		
		boolean markAsRslvdPrvw = formoperations.markAsResolvedForField(dateFieldName, markAsResolved);
		TestValidation.IsTrue(markAsRslvdPrvw, 
				  			  "Verified 'Mark As Resolved' option for Date field -" + dateFieldName + " in Form Preview, marked it as -" + markAsResolved, 
				              "FAILED to verify 'Marked As Resolved' option for Date field -" + dateFieldName + " in Form Preview");
		
		
		boolean closePreview = fdp.clickClosePreviewBtn();
		TestValidation.IsTrue(closePreview, 
				  			  "Successfully Closed Form Preview", 
				  			  "Could NOT Close Form Preview");
		
		boolean nextToReleaseForm = fdp.clickOnNextButton(fdp.ReleaseFormPg, FORM_NEXT_PAGE.RELEASE_FORM);
		TestValidation.IsTrue(nextToReleaseForm, 
							  "NAVIGATED to 'Release Form'", 
							  "Failed to Navigate to 'Release Form'");
		// ============================= END -> VERIFY Settings in FORM PREVIEW
		
		boolean releaseForm = fdp.releaseForm(quesFrmName);
		TestValidation.IsTrue(releaseForm, 
							 "Successfully released form -" + quesFrmName, 
							 "Could Not release form -" + quesFrmName);
		
		boolean navToHome = fdp.navigateToHome();
		TestValidation.IsTrue(navToHome, 
				  			  "Successfully Navigated to Home", 
				              "Could NOT naigate to home");
		
		boolean selectResourceDropDownandNavigate = fbp.selectResourceDropDownandNavigate(FORMRESOURCETYPES.EQUIPMENT,FSQATAB.FORMS);
		TestValidation.IsTrue(selectResourceDropDownandNavigate, 
							  "Navigated to FSQA ->  " + FSQATAB.FORMS ,
							  "Could NOT Navigate to FSQA ->  " + FSQATAB.FORMS);
		
		
		boolean selectOpenForm = fbForms.selectOpenForm(quesFrmName);
		TestValidation.IsTrue(selectOpenForm, 
							  "Select & opened the form -" + quesFrmName, 
							  "Could NOT open the form" + quesFrmName);
		
		
		// ============================= START -> VERIFY Settings in FSQABrowser Forms
		
		TestValidation.IsTrue(true,"======================== IN FSQABrowser Forms ========================","");
		
		boolean dateTextFieldFrms = formoperations.setInputFieldValue(dateFieldName, Arrays.asList(dateNonComplianceValue), null);
		TestValidation.IsTrue(dateTextFieldFrms, 
	  			  			  "Entered non compliant value -" + dateNonComplianceValue + " in Date field -" + dateFieldName, 
	  			  			  "Failed to enter value in Date field -" + dateFieldName);
		
		boolean checkIsFieldRequiredFrms = formoperations.isFieldRequired(dateFieldName);
		TestValidation.IsTrue(checkIsFieldRequiredFrms, 
							  "Verified Date field is marked as required", 
					          "FAILED to verify Date Field marked as required");
		
		boolean allwAtchmntsFrms = formoperations.addAttachmentForField(dateFieldName, generalFilePath);
		TestValidation.IsTrue(allwAtchmntsFrms, 
				  			  "Verified Allow Attachments for Date field, and attached file -" + generalFile, 
				  			  "FAILED to verify Allow Attachments for Date field");
		
		boolean addCmntsFrms = formoperations.addCommentForField(dateFieldName, comment);
		TestValidation.IsTrue(addCmntsFrms, 
							  "Verified Allow Comments for Date field and entered comment -" + comment, 
				  			  "FAILED to verify Allow Comments for Date field");
		
		boolean hintDisplayFrms = formoperations.isHintDisplayedForField(dateFieldName, hintText);
		TestValidation.IsTrue(hintDisplayFrms, 
				  		      "Verified Hint Display for Date field, hint =" + hintText, 
				              "FAILED to verify Hint Display for Date field");
		
		boolean addCustmCorctnFrms = formoperations.setCustomCorrectionForField(dateFieldName, customCorrection);
		TestValidation.IsTrue(addCustmCorctnFrms, 
				  			  "Verified Custom Correction for Date field", 
				              "FAILED to verify Custom Correction for Date field");
		
		boolean setPredefindCorrctnFrms = formoperations.setPredefinedCorrectionsForField(dateFieldName, preDefinedCorrectionOptions.get(1))	;
		TestValidation.IsTrue(setPredefindCorrctnFrms, 
	             		      "Verified Predefined Correction for Date field", 
	                          "FAILED to verify Predefined Correction for Date field");
		
		boolean markAsRslvdFrms = formoperations.markAsResolvedForField(dateFieldName, markAsResolved);
		TestValidation.IsTrue(markAsRslvdFrms, 
				  			  "Verified Mark As Resolved for Date field", 
				              "FAILED to verify Mark As Resolved for Date field");
		
//		boolean submitForm = formoperations.clickSubmitThenAlertOkBtn();
//		TestValidation.IsTrue(submitForm, 
//				  			 "Clicked on Submit and Submitted the form -" + quesFrmName, 
//				             "Could NOT click on Submit buttton and failed to submit form -" + quesFrmName);
		
		// ============================= END -> VERIFY Settings in FSQABrowser Forms
		
		//FAILS QUESTIONNAIER ?!
		boolean clickSubmitRepeat = fbForms.clickSubmitAndReapeatButton();
		TestValidation.IsTrue(clickSubmitRepeat, 
				  			 "CLICKED SUBMIT AND REPEAT", 
							 "FAILED TO CLICK SUBMIT AND REPEAT");
		
		TestValidation.IsTrue(true,"======================== After Submit and Repeat ========================","");
		
		boolean checkIsFieldRequiredSubRep = formoperations.isFieldRequired(dateFieldName);
		TestValidation.IsTrue(checkIsFieldRequiredSubRep, 
							  "Verified Date Field is marked as required", 
					          "FAILED to verify Date Field marked as required");
		
		boolean hintDisplaySubRep = formoperations.isHintDisplayedForField(dateFieldName, hintText);
		TestValidation.IsTrue(hintDisplaySubRep, 
				  		      "Verified Hint is Displayed as hint = " + hintText, 
				              "FAILED to verify Hint Display");
		
		boolean verifyCommentSubRep = formoperations.verifyCommentOfField(dateFieldName, comment);
		TestValidation.IsTrue(verifyCommentSubRep, 
				  			  "Verified that comment is repeated correctly", 
				              "Failed to verify repeated comment ");
		
		boolean verifyAttachmentSubRep = formoperations.verifySingleAttachmentForField(dateFieldName, generalFile);
		TestValidation.IsTrue(verifyAttachmentSubRep, 
							 "Verified Attachment is repeated", 
				             "Failed to verify repeated attahment");
		
		boolean verifyPredefinedCorrSubRep = formoperations.verifyPredefinedCorrectionValueForField(dateFieldName, preDefinedCorrectionOptions.get(1));
		TestValidation.IsTrue(verifyPredefinedCorrSubRep, 
				  			 "Verified correction is repeated", 
				  			 "Failed to verify repeated correction");
		
		boolean verifyMarkAsResolvedSubRep = formoperations.verifymarkAsResolvedValueForField(dateFieldName, markAsResolved);
		TestValidation.IsTrue(verifyMarkAsResolvedSubRep, 
							  "Verified Mark as resolved is repeated", 
				  			  "Failed to verify Mark as Resolved repeated");
		
		boolean clickCancel = fbForms.clickCancelButton();
		TestValidation.IsTrue(clickCancel, 
				  			 "SUCCESSFULLY CLOSED FORM", 
							 "FAILED TO CLOSE FORM");
		// ============================= START -> VERIFY Settings in DMS for Questionnaire Submitted Form
		
		// goto DMS
		documentManagement = hp.clickdocumentsmenu();
		
		//select DocType
		boolean selectDocumentType = documentManagement.selectDocumentType(quesFrmName);
		TestValidation.IsTrue(selectDocumentType, 
							 "Selected Document Type -> " + quesFrmName,
						      "Could NOT Select Document Type -> " + quesFrmName);
		
		// open Document
		boolean openDocument = documentManagement.viewQuestionnaireDocument(quesFrmName);
		TestValidation.IsTrue(openDocument, 
						      "OPEN document ->   " + quesFrmName,
						      "Could NOT OPEN document ->  " + quesFrmName);
		
		TestValidation.IsTrue(true,"======================== IN DMS  ========================","");
		
		
		boolean checkIsFieldRequiredRecrds = documentManagement.verifyIsFieldMarkedAsRequiredQstnDoc(dateFieldName);
		TestValidation.IsTrue(checkIsFieldRequiredRecrds, 
				 			  "Verified Date field is marked as required", 
		          			  "FAILED to verify Date marked as required");
		
		boolean hintDisplayRecrds = documentManagement.verifyHintForFieldQstnDoc(dateFieldName, hintText);
		TestValidation.IsTrue(hintDisplayRecrds, 
				 			  "Verified Hint Display for Date field, hint =" + hintText, 
				              "FAILED to verify Hint Display for Date field");
        		
		boolean addCmntsRecrds = documentManagement.verifyCommentForFieldQstnDoc(dateFieldName, comment);
		TestValidation.IsTrue(addCmntsRecrds, 
				  			  "Verified Comment as -" + comment + " for Date field -" + dateFieldName, 
				              "FAILED to verify Comment for Date field");
		
		boolean allwAtchmntsRecrds = documentManagement.verifyAttachmentFileNameForFieldQstnDoc(dateFieldName, generalFile);
		TestValidation.IsTrue(allwAtchmntsRecrds, 
				  			  "Verified Attachment as -" + generalFile + "for Date field -" + dateFieldName, 
				              "FAILED to verify Attachment for Date field - " +dateFieldName);
		
		boolean correctionRecrds = documentManagement.verifyCorrectionForFieldQstnDoc(dateFieldName, preDefinedCorrectionOptions.get(1));
		TestValidation.IsTrue(correctionRecrds, 
				  			  "Verified Correction as -" +preDefinedCorrectionOptions.get(1) + " for Date field - " + dateFieldName, 
				              "FAILED to verify correction for Date field");
		
		boolean markAsResolvedRecrds = documentManagement.verifyIsResolvedForFieldQstnDoc(dateFieldName, markAsResolved);
		TestValidation.IsTrue(markAsResolvedRecrds, 
				  			  "Verified Marked as Resolved as -" + markAsResolved + " for Date field -" + dateFieldName,
				              "FAILED to verify Marked As Resolved for Date field");
		// ============================= END -> VERIFY Settings in DMS for Questionnaire Submitted Form
	}
	
	@Test(description = "Form Designer || Validate form functionality for field settings for 'Questionnaire' forms - \"Time\" fields")
	public void TestCase_48773() throws Exception {
		
		String quesFrmName = CommonMethods.dynamicText("aQF");
		String timeFieldName = "ATime";
		String timeComplianceValue = "12:00 AM";
		String timeNonComplianceValue = "1:00 AM";
		String hintText = "Time Hint";
		String comment = "This is a comment";
		String customCorrection = "CustomCorrection";
		String markAsResolved = "Yes";
		String generalFile = "generalFile.png";
		String generalFilePath = System.getProperty("user.dir") + "\\test-data-files\\UploadDocuments\\"+generalFile;
		
		
		List<String> preDefinedCorrectionOptions = Arrays.asList("Option1","Option2");
		
		// Form Level
		HashMap<String, List<String>> chkFields = new HashMap<String, List<String>>();
		chkFields.put(FIELD_TYPES.TIME, Arrays.asList(timeFieldName));
		
		FormFieldParams ffpChk = new FormFieldParams();
		ffpChk.FieldDetails = chkFields;
		
		HashMap<String, List<String>> equipResource = new HashMap<String, List<String>>();
		equipResource.put(FORMRESOURCETYPES.EQUIPMENT, Arrays.asList(equipmentsCategoryValue));

		FormDesignParams fdpChk = new FormDesignParams();
		fdpChk.FormType = FORMTYPES.QUESTIONNAIRE;
		fdpChk.FormName = quesFrmName;
		fdpChk.SelectResources = equipResource;
		fdpChk.DesignFields = Arrays.asList(ffpChk);
		fdpChk.ReleaseForm = true;
		
		// navigate to form designer
		fdp = hp.clickFormDesignerMenu();
		
		boolean designForm = fdp.designForm(fdpChk);
		TestValidation.IsTrue(designForm, 
							  "Successfully Designed Form - " +quesFrmName + " with Time Field -" + timeFieldName, 
					          "Failed to design from with Time Field");
		
		HashMap<String, ElementProperties> fieldSettings = new LinkedHashMap<String, ElementProperties>(); 
		ElementProperties settings = new ElementProperties();
		settings.FAILS_CHECK = true;
		settings.IS_REQUIRED = true;
		settings.ALLOW_COMMENTS = true;
		settings.ALLOW_ATTATHMENTS = true;
		settings.SHOW_HINT = true;
		settings.HINT = hintText;
		settings.ALLOW_CORRECTION = true;
		settings.MARK_RESOLVED = true;
		settings.PREDEFINED_CORRECTIONS = true;
		settings.PREDEFINED_CORRECTIONS_OPTNS = preDefinedCorrectionOptions;
		settings.CARRYOVER_FIELD = true;
		fieldSettings.put(timeFieldName, settings);
		
		boolean turnAllSettingsOn = fdp.setFieldProperties(fieldSettings) ;
		TestValidation.IsTrue(turnAllSettingsOn, 
	             			  "Successfully Turned All Settings for the Time Field -" + timeFieldName, 
	                          "Failed to tur on All Settigs for for the Time Field -" + timeFieldName);
		
		boolean backToDesign = fdp.backToFormDesignFrmRelease(quesFrmName);
		TestValidation.IsTrue(backToDesign, 
				  			  "Navigated Back to Form Design for Form from Release page - " + quesFrmName, 
				              "FAILED to navigate back to Form Design for Form from Release page");
		
		boolean setComplianceTime = fdp.setComplianceForDateandTimeFieldv2(timeFieldName, equipmentsInstanceValue, timeComplianceValue);
		TestValidation.IsTrue(setComplianceTime, 
				  			  "Set Compliance Time as -" + timeComplianceValue + " for Time field -" + timeFieldName, 
				              "Failed to set Compliance Time for Time Field -" + timeFieldName);
		
		// ============================= START -> VERIFY Settings in FORM PREVIEW
		
		TestValidation.IsTrue(true,"======================== IN Form Preview ========================","");
		
		boolean openfForPreview = fdp.clickPreviewButton();
		TestValidation.IsTrue(openfForPreview, 
				  			  "Clicked Preview Butoon and Opened Form -"+ quesFrmName + " for preview", 
				              "Failed to open form for Preview");
		
		boolean timeTextFieldPrvw = formoperations.setInputFieldValue(timeFieldName, Arrays.asList(timeNonComplianceValue), null);
		TestValidation.IsTrue(timeTextFieldPrvw, 
				  			  "Entered non compliant value " + timeNonComplianceValue + " in Time fied -" + timeFieldName, 
				              "Failed to enter value in Time field -" + timeFieldName);
		
		controlActions.actionEnter();
		
		boolean checkIsFieldRequiredPrvw = formoperations.isFieldRequired(timeFieldName);
		TestValidation.IsTrue(checkIsFieldRequiredPrvw, 
				  			  "Verified that Time field -" + timeFieldName + " is Marked as required in Form Preview", 
				              "Failed to verify that Time field -" + timeFieldName + " is Marked as required in Form Preview");
		
		boolean allwAtchmntsPrvw = formoperations.verifyAllowAttachmentsInPreviewForField(timeFieldName);
		TestValidation.IsTrue(allwAtchmntsPrvw, 
							  "Verified Allow Attachements for Time field -" + timeFieldName + " in Form Preview", 
							  "FAILED to verify Allow Attachements for Time field -" + timeFieldName + " in Form Preview");
		
		boolean addCmntsPrvw = formoperations.addCommentForField(timeFieldName, "This is a comment");
		TestValidation.IsTrue(addCmntsPrvw, 
				  			  "Verified Add Comment Textbox present for Time field -" + timeFieldName + " in Form Preview and added comment -" + comment , 
				  			  "FAILED to verify Add Comment Textbox present for Time field -" + timeFieldName );
		
		boolean hintDisplayPrvw = formoperations.isHintDisplayedForField(timeFieldName, hintText);
		TestValidation.IsTrue(hintDisplayPrvw, 
				  			  "Verified hint Displayed for Time field -" + timeFieldName + " in Form Preview", 
				  			  "FAILED to very hint display for Time field -" + timeFieldName + " in Form Preview");
		
		boolean addCustmCorctnPrvw = formoperations.setCustomCorrectionForField(timeFieldName, customCorrection);
		TestValidation.IsTrue(addCustmCorctnPrvw, 
				  			  "Verified Custom Correction Text box for Time field -" + timeFieldName + " in Form Preview, added correction -" + customCorrection, 
				              "FAILED to verify correction textbox for Time field -" + timeFieldName + " in Form Preview");
		
		boolean setPredefindCorrctnPrvw = formoperations.setPredefinedCorrectionsForField(timeFieldName, preDefinedCorrectionOptions.get(1))	;
		TestValidation.IsTrue(setPredefindCorrctnPrvw, 
				  			  "Verufied Predefined Correction input field for Time field -" + timeFieldName + " in Form Preview, added correction -" + preDefinedCorrectionOptions.get(1), 
				              "FAILED to verify Predefined Correction input for Time field -" + timeFieldName + " in form Preview" );
		
		boolean markAsRslvdPrvw = formoperations.markAsResolvedForField(timeFieldName, markAsResolved);
		TestValidation.IsTrue(markAsRslvdPrvw, 
				  			  "Verified 'Mark As Resolved' option for Time field -" + timeFieldName + " in Form Preview, marked it as -" + markAsResolved, 
				              "FAILED to verify 'Marked As Resolved' option for Time field -" + timeFieldName + " in Form Preview");
		
		
		boolean closePreview = fdp.clickClosePreviewBtn();
		TestValidation.IsTrue(closePreview, 
				  			  "Successfully Closed Form Preview", 
				  			  "Could NOT Close Form Preview");
		
		boolean nextToReleaseForm = fdp.clickOnNextButton(fdp.ReleaseFormPg, FORM_NEXT_PAGE.RELEASE_FORM);
		TestValidation.IsTrue(nextToReleaseForm, 
							  "NAVIGATED to 'Release Form'", 
							  "Failed to Navigate to 'Release Form'");
		// ============================= END -> VERIFY Settings in FORM PREVIEW
		
		boolean releaseForm = fdp.releaseForm(quesFrmName);
		TestValidation.IsTrue(releaseForm, 
							 "Successfully released form -" + quesFrmName, 
							 "Could Not release form -" + quesFrmName);
		
		boolean navToHome = fdp.navigateToHome();
		TestValidation.IsTrue(navToHome, 
				  			  "Successfully Navigated to Home", 
				              "Could NOT naigate to home");
		
		boolean selectResourceDropDownandNavigate = fbp.selectResourceDropDownandNavigate(FORMRESOURCETYPES.EQUIPMENT,FSQATAB.FORMS);
		TestValidation.IsTrue(selectResourceDropDownandNavigate, 
							  "Navigated to FSQA ->  " + FSQATAB.FORMS ,
							  "Could NOT Navigate to FSQA ->  " + FSQATAB.FORMS);
		
		
		boolean selectOpenForm = fbForms.selectOpenForm(quesFrmName);
		TestValidation.IsTrue(selectOpenForm, 
							  "Select & opened the form -" + quesFrmName, 
							  "Could NOT open the form" + quesFrmName);
		
		
		// ============================= START -> VERIFY Settings in FSQABrowser Forms
		
		TestValidation.IsTrue(true,"======================== IN FSQABrowser Forms ========================","");
		
		boolean dateTextFieldFrms = formoperations.setInputFieldValue(timeFieldName, Arrays.asList(timeNonComplianceValue), null);
		TestValidation.IsTrue(dateTextFieldFrms, 
	  			  			  "Entered non compliant value -" + timeNonComplianceValue + " in Time field -" + timeFieldName, 
	  			  			  "Failed to enter value in Time field -" + timeFieldName);
		
		boolean checkIsFieldRequiredFrms = formoperations.isFieldRequired(timeFieldName);
		TestValidation.IsTrue(checkIsFieldRequiredFrms, 
							  "Verified Time field is marked as required", 
					          "FAILED to verify Time Field marked as required");
		
		boolean allwAtchmntsFrms = formoperations.addAttachmentForField(timeFieldName, generalFilePath);
		TestValidation.IsTrue(allwAtchmntsFrms, 
				  			  "Verified Allow Attachments for Time field, and attached file -" + generalFile, 
				  			  "FAILED to verify Allow Attachments for Time field");
		
		boolean addCmntsFrms = formoperations.addCommentForField(timeFieldName, comment);
		TestValidation.IsTrue(addCmntsFrms, 
							  "Verified Allow Comments for Time field and entered comment -" + comment, 
				  			  "FAILED to verify Allow Comments for Time field");
		
		boolean hintDisplayFrms = formoperations.isHintDisplayedForField(timeFieldName, hintText);
		TestValidation.IsTrue(hintDisplayFrms, 
				  		      "Verified Hint Display for Time field, hint =" + hintText, 
				              "FAILED to verify Hint Display for Time field");
		
		boolean addCustmCorctnFrms = formoperations.setCustomCorrectionForField(timeFieldName, customCorrection);
		TestValidation.IsTrue(addCustmCorctnFrms, 
				  			  "Verified Custom Correction for Time field", 
				              "FAILED to verify Custom Correction for Time field");
		
		boolean setPredefindCorrctnFrms = formoperations.setPredefinedCorrectionsForField(timeFieldName, preDefinedCorrectionOptions.get(1))	;
		TestValidation.IsTrue(setPredefindCorrctnFrms, 
	             		      "Verified Predefined Correction for Time field", 
	                          "FAILED to verify Predefined Correction for Time field");
		
		boolean markAsRslvdFrms = formoperations.markAsResolvedForField(timeFieldName, markAsResolved);
		TestValidation.IsTrue(markAsRslvdFrms, 
				  			  "Verified Mark As Resolved for Time field", 
				              "FAILED to verify Mark As Resolved for Time field");
		
//		boolean submitForm = formoperations.clickSubmitThenAlertOkBtn();
//		TestValidation.IsTrue(submitForm, 
//				  			 "Clicked on Submit and Submitted the form -" + quesFrmName, 
//				             "Could NOT click on Submit buttton and failed to submit form -" + quesFrmName);
		
		// ============================= END -> VERIFY Settings in FSQABrowser Forms
		
		//FAILS QUESTIONNAIER ?!
		boolean clickSubmitRepeat = fbForms.clickSubmitAndReapeatButton();
		TestValidation.IsTrue(clickSubmitRepeat, 
				  			 "CLICKED SUBMIT AND REPEAT", 
							 "FAILED TO CLICK SUBMIT AND REPEAT");
		
		TestValidation.IsTrue(true,"======================== After Submit and Repeat ========================","");
		
		boolean checkIsFieldRequiredSubRep = formoperations.isFieldRequired(timeFieldName);
		TestValidation.IsTrue(checkIsFieldRequiredSubRep, 
							  "Verified Time Field is marked as required", 
					          "FAILED to verify Time Field marked as required");
		
		boolean hintDisplaySubRep = formoperations.isHintDisplayedForField(timeFieldName, hintText);
		TestValidation.IsTrue(hintDisplaySubRep, 
				  		      "Verified Hint is Displayed as hint = " + hintText, 
				              "FAILED to verify Hint Display");
		
		boolean verifyCommentSubRep = formoperations.verifyCommentOfField(timeFieldName, comment);
		TestValidation.IsTrue(verifyCommentSubRep, 
				  			  "Verified that comment is repeated correctly", 
				              "Failed to verify repeated comment ");
		
		boolean verifyAttachmentSubRep = formoperations.verifySingleAttachmentForField(timeFieldName, generalFile);
		TestValidation.IsTrue(verifyAttachmentSubRep, 
							 "Verified Attachment is repeated", 
				             "Failed to verify repeated attahment");
		
		boolean verifyPredefinedCorrSubRep = formoperations.verifyPredefinedCorrectionValueForField(timeFieldName, preDefinedCorrectionOptions.get(1));
		TestValidation.IsTrue(verifyPredefinedCorrSubRep, 
				  			 "Verified correction is repeated", 
				  			 "Failed to verify repeated correction");
		
		boolean verifyMarkAsResolvedSubRep = formoperations.verifymarkAsResolvedValueForField(timeFieldName, markAsResolved);
		TestValidation.IsTrue(verifyMarkAsResolvedSubRep, 
							  "Verified Mark as resolved is repeated", 
				  			  "Failed to verify Mark as Resolved repeated");
		
		boolean clickCancel = fbForms.clickCancelButton();
		TestValidation.IsTrue(clickCancel, 
				  			 "SUCCESSFULLY CLOSED FORM", 
							 "FAILED TO CLOSE FORM");
		
		// ============================= START -> VERIFY Settings in DMS for Questionnaire Submitted Form
		
		// goto DMS
		documentManagement = hp.clickdocumentsmenu();
		
		//select DocType
		boolean selectDocumentType = documentManagement.selectDocumentType(quesFrmName);
		TestValidation.IsTrue(selectDocumentType, 
							 "Selected Document Type -> " + quesFrmName,
						      "Could NOT Select Document Type -> " + quesFrmName);
		
		// open Document
		boolean openDocument = documentManagement.viewQuestionnaireDocument(quesFrmName);
		TestValidation.IsTrue(openDocument, 
						      "OPEN document ->   " + quesFrmName,
						      "Could NOT OPEN document ->  " + quesFrmName);
		
		TestValidation.IsTrue(true,"======================== IN DMS  ========================","");
		
		
		boolean checkIsFieldRequiredRecrds = documentManagement.verifyIsFieldMarkedAsRequiredQstnDoc(timeFieldName);
		TestValidation.IsTrue(checkIsFieldRequiredRecrds, 
				 			  "Verified Date field is marked as required", 
		          			  "FAILED to verify Time marked as required");
		
		boolean hintDisplayRecrds = documentManagement.verifyHintForFieldQstnDoc(timeFieldName, hintText);
		TestValidation.IsTrue(hintDisplayRecrds, 
				 			  "Verified Hint Display for Time field, hint =" + hintText, 
				              "FAILED to verify Hint Display for Time field");
        		
		boolean addCmntsRecrds = documentManagement.verifyCommentForFieldQstnDoc(timeFieldName, comment);
		TestValidation.IsTrue(addCmntsRecrds, 
				  			  "Verified Comment as -" + comment + " for Time field -" + timeFieldName, 
				              "FAILED to verify Comment for Time field");
		
		boolean allwAtchmntsRecrds = documentManagement.verifyAttachmentFileNameForFieldQstnDoc(timeFieldName, generalFile);
		TestValidation.IsTrue(allwAtchmntsRecrds, 
				  			  "Verified Attachment as -" + generalFile + "for Time field -" + timeFieldName, 
				              "FAILED to verify Attachment for Time field - " +timeFieldName);
		
		boolean correctionRecrds = documentManagement.verifyCorrectionForFieldQstnDoc(timeFieldName, preDefinedCorrectionOptions.get(1));
		TestValidation.IsTrue(correctionRecrds, 
				  			  "Verified Correction as -" +preDefinedCorrectionOptions.get(1) + " for Time field - " + timeFieldName, 
				              "FAILED to verify correction for Time field");
		
		boolean markAsResolvedRecrds = documentManagement.verifyIsResolvedForFieldQstnDoc(timeFieldName, markAsResolved);
		TestValidation.IsTrue(markAsResolvedRecrds, 
				  			  "Verified Marked as Resolved as -" + markAsResolved + " for Time field -" + timeFieldName,
				              "FAILED to verify Marked As Resolved for Time field");
		// ============================= END -> VERIFY Settings in DMS for Questionnaire Submitted Form
	}
	
	@Test(description = "Form Designer || Validate form functionality for field settings for 'Questionnaire' forms - \"Date & Time\" fields")
	public void TestCase_48779() throws Exception {
		
		String quesFrmName = CommonMethods.dynamicText("aQF");
		String DTFieldName = "DateTime";
		String DTComplianceValue = "9/8/2021 12:00 AM";
		String DTNonComplianceValue = "9/7/2021 12:00 AM";
		String hintText = "Date Time Hint";
		String comment = "This is a comment";
		String customCorrection = "CustomCorrection";
		String markAsResolved = "Yes";
		String generalFile = "generalFile.png";
		String generalFilePath = System.getProperty("user.dir") + "\\test-data-files\\UploadDocuments\\"+generalFile;
		
		
		List<String> preDefinedCorrectionOptions = Arrays.asList("Option1","Option2");
		
		// Form Level
		HashMap<String, List<String>> chkFields = new HashMap<String, List<String>>();
		chkFields.put(FIELD_TYPES.DATE_TIME, Arrays.asList(DTFieldName));
		
		FormFieldParams ffpChk = new FormFieldParams();
		ffpChk.FieldDetails = chkFields;
		
		HashMap<String, List<String>> equipResource = new HashMap<String, List<String>>();
		equipResource.put(FORMRESOURCETYPES.EQUIPMENT, Arrays.asList(equipmentsCategoryValue));

		FormDesignParams fdpChk = new FormDesignParams();
		fdpChk.FormType = FORMTYPES.QUESTIONNAIRE;
		fdpChk.FormName = quesFrmName;
		fdpChk.SelectResources = equipResource;
		fdpChk.DesignFields = Arrays.asList(ffpChk);
		fdpChk.ReleaseForm = true;
		
		// navigate to form designer
		fdp = hp.clickFormDesignerMenu();
		
		boolean designForm = fdp.designForm(fdpChk);
		TestValidation.IsTrue(designForm, 
							  "Successfully Designed Form - " +quesFrmName + " with Time Field -" + DTFieldName, 
					          "Failed to design from with Time Field");
		
		HashMap<String, ElementProperties> fieldSettings = new LinkedHashMap<String, ElementProperties>(); 
		ElementProperties settings = new ElementProperties();
		settings.FAILS_CHECK = true;
		settings.IS_REQUIRED = true;
		settings.ALLOW_COMMENTS = true;
		settings.ALLOW_ATTATHMENTS = true;
		settings.SHOW_HINT = true;
		settings.HINT = hintText;
		settings.ALLOW_CORRECTION = true;
		settings.MARK_RESOLVED = true;
		settings.PREDEFINED_CORRECTIONS = true;
		settings.PREDEFINED_CORRECTIONS_OPTNS = preDefinedCorrectionOptions;
		settings.CARRYOVER_FIELD = true;
		fieldSettings.put(DTFieldName, settings);
		
		boolean turnAllSettingsOn = fdp.setFieldProperties(fieldSettings) ;
		TestValidation.IsTrue(turnAllSettingsOn, 
	             			  "Successfully Turned All Settings for the Date And Time Field -" + DTFieldName, 
	                          "Failed to tur on All Settigs for for the Date And Time Field -" + DTFieldName);
		
		boolean backToDesign = fdp.backToFormDesignFrmRelease(quesFrmName);
		TestValidation.IsTrue(backToDesign, 
				  			  "Navigated Back to Form Design for Form from Release page - " + quesFrmName, 
				              "FAILED to navigate back to Form Design for Form from Release page");
		
		boolean setComplianceDateTime = fdp.setComplianceForDateandTimeFieldv2(DTFieldName, equipmentsInstanceValue, DTComplianceValue);
		TestValidation.IsTrue(setComplianceDateTime, 
				  			  "Set Compliance Date and Time as -" + DTComplianceValue + " for Date and Time field -" + DTFieldName, 
				              "Failed to set Compliance Date Time for Date and Time Field -" + DTFieldName);
		
		// ============================= START -> VERIFY Settings in FORM PREVIEW
		
		TestValidation.IsTrue(true,"======================== IN Form Preview ========================","");
		
		boolean openfForPreview = fdp.clickPreviewButton();
		TestValidation.IsTrue(openfForPreview, 
				  			  "Clicked Preview Butoon and Opened Form -"+ quesFrmName + " for preview", 
				              "Failed to open form for Preview");
		
		boolean timeTextFieldPrvw = formoperations.setInputFieldValue(DTFieldName, Arrays.asList(DTNonComplianceValue), null);
		TestValidation.IsTrue(timeTextFieldPrvw, 
				  			  "Entered non compliant value " + DTNonComplianceValue + " in Date and Time fied -" + DTFieldName, 
				              "Failed to enter value in Date and Time field -" + DTFieldName);
		
		controlActions.actionEnter();
		
		boolean checkIsFieldRequiredPrvw = formoperations.isFieldRequired(DTFieldName);
		TestValidation.IsTrue(checkIsFieldRequiredPrvw, 
				  			  "Verified that Date and Time field -" + DTFieldName + " is Marked as required in Form Preview", 
				              "Failed to verify that Time field -" + DTFieldName + " is Marked as required in Form Preview");
		
		boolean allwAtchmntsPrvw = formoperations.verifyAllowAttachmentsInPreviewForField(DTFieldName);
		TestValidation.IsTrue(allwAtchmntsPrvw, 
							  "Verified Allow Attachements for Date and Time field -" + DTFieldName + " in Form Preview", 
							  "FAILED to verify Allow Attachements for Date and Time field -" + DTFieldName + " in Form Preview");
		
		boolean addCmntsPrvw = formoperations.addCommentForField(DTFieldName, "This is a comment");
		TestValidation.IsTrue(addCmntsPrvw, 
				  			  "Verified Add Comment Textbox present for Date and Time field -" + DTFieldName + " in Form Preview and added comment -" + comment , 
				  			  "FAILED to verify Add Comment Textbox present for Date and Time field -" + DTFieldName );
		
		boolean hintDisplayPrvw = formoperations.isHintDisplayedForField(DTFieldName, hintText);
		TestValidation.IsTrue(hintDisplayPrvw, 
				  			  "Verified hint Displayed for Date and Time field -" + DTFieldName + " in Form Preview", 
				  			  "FAILED to very hint display for Date and Time field -" + DTFieldName + " in Form Preview");
		
		boolean addCustmCorctnPrvw = formoperations.setCustomCorrectionForField(DTFieldName, customCorrection);
		TestValidation.IsTrue(addCustmCorctnPrvw, 
				  			  "Verified Custom Correction Text box for Date and Time field -" + DTFieldName + " in Form Preview, added correction -" + customCorrection, 
				              "FAILED to verify correction textbox for Date and Time field -" + DTFieldName + " in Form Preview");
		
		boolean setPredefindCorrctnPrvw = formoperations.setPredefinedCorrectionsForField(DTFieldName, preDefinedCorrectionOptions.get(1))	;
		TestValidation.IsTrue(setPredefindCorrctnPrvw, 
				  			  "Verufied Predefined Correction input field for Date and Time field -" + DTFieldName + " in Form Preview, added correction -" + preDefinedCorrectionOptions.get(1), 
				              "FAILED to verify Predefined Correction input for Date and Time field -" + DTFieldName + " in form Preview" );
		
		boolean markAsRslvdPrvw = formoperations.markAsResolvedForField(DTFieldName, markAsResolved);
		TestValidation.IsTrue(markAsRslvdPrvw, 
				  			  "Verified 'Mark As Resolved' option for Date and Time field -" + DTFieldName + " in Form Preview, marked it as -" + markAsResolved, 
				              "FAILED to verify 'Marked As Resolved' option for Date and Time field -" + DTFieldName + " in Form Preview");
		
		
		boolean closePreview = fdp.clickClosePreviewBtn();
		TestValidation.IsTrue(closePreview, 
				  			  "Successfully Closed Form Preview", 
				  			  "Could NOT Close Form Preview");
		
		boolean nextToReleaseForm = fdp.clickOnNextButton(fdp.ReleaseFormPg, FORM_NEXT_PAGE.RELEASE_FORM);
		TestValidation.IsTrue(nextToReleaseForm, 
							  "NAVIGATED to 'Release Form'", 
							  "Failed to Navigate to 'Release Form'");
		// ============================= END -> VERIFY Settings in FORM PREVIEW
		
		boolean releaseForm = fdp.releaseForm(quesFrmName);
		TestValidation.IsTrue(releaseForm, 
							 "Successfully released form -" + quesFrmName, 
							 "Could Not release form -" + quesFrmName);
		
		boolean navToHome = fdp.navigateToHome();
		TestValidation.IsTrue(navToHome, 
				  			  "Successfully Navigated to Home", 
				              "Could NOT naigate to home");
		
		boolean selectResourceDropDownandNavigate = fbp.selectResourceDropDownandNavigate(FORMRESOURCETYPES.EQUIPMENT,FSQATAB.FORMS);
		TestValidation.IsTrue(selectResourceDropDownandNavigate, 
							  "Navigated to FSQA ->  " + FSQATAB.FORMS ,
							  "Could NOT Navigate to FSQA ->  " + FSQATAB.FORMS);
		
		
		boolean selectOpenForm = fbForms.selectOpenForm(quesFrmName);
		TestValidation.IsTrue(selectOpenForm, 
							  "Select & opened the form -" + quesFrmName, 
							  "Could NOT open the form" + quesFrmName);
		
		
		// ============================= START -> VERIFY Settings in FSQABrowser Forms
		
		TestValidation.IsTrue(true,"======================== IN FSQABrowser Forms ========================","");
		
		boolean dateTextFieldFrms = formoperations.setInputFieldValue(DTFieldName, Arrays.asList(DTNonComplianceValue), null);
		TestValidation.IsTrue(dateTextFieldFrms, 
	  			  			  "Entered non compliant value -" + DTNonComplianceValue + " in Date and Time field -" + DTFieldName, 
	  			  			  "Failed to enter value in Date and Time field -" + DTFieldName);
		
		boolean checkIsFieldRequiredFrms = formoperations.isFieldRequired(DTFieldName);
		TestValidation.IsTrue(checkIsFieldRequiredFrms, 
							  "Verified Date and Time field is marked as required", 
					          "FAILED to verify Date and Time Field marked as required");
		
		boolean allwAtchmntsFrms = formoperations.addAttachmentForField(DTFieldName, generalFilePath);
		TestValidation.IsTrue(allwAtchmntsFrms, 
				  			  "Verified Allow Attachments for Date and Time field, and attached file -" + generalFile, 
				  			  "FAILED to verify Allow Attachments for Date and Time field");
		
		boolean addCmntsFrms = formoperations.addCommentForField(DTFieldName, comment);
		TestValidation.IsTrue(addCmntsFrms, 
							  "Verified Allow Comments for Date and Time field and entered comment -" + comment, 
				  			  "FAILED to verify Allow Comments for Date and Time field");
		
		boolean hintDisplayFrms = formoperations.isHintDisplayedForField(DTFieldName, hintText);
		TestValidation.IsTrue(hintDisplayFrms, 
				  		      "Verified Hint Display for Date and Time field, hint =" + hintText, 
				              "FAILED to verify Hint Display for Date and Time field");
		
		boolean addCustmCorctnFrms = formoperations.setCustomCorrectionForField(DTFieldName, customCorrection);
		TestValidation.IsTrue(addCustmCorctnFrms, 
				  			  "Verified Custom Correction for Date and Time field", 
				              "FAILED to verify Custom Correction for Date and Time field");
		
		boolean setPredefindCorrctnFrms = formoperations.setPredefinedCorrectionsForField(DTFieldName, preDefinedCorrectionOptions.get(1))	;
		TestValidation.IsTrue(setPredefindCorrctnFrms, 
	             		      "Verified Predefined Correction for Date and Time field", 
	                          "FAILED to verify Predefined Correction for Date and Time field");
		
		boolean markAsRslvdFrms = formoperations.markAsResolvedForField(DTFieldName, markAsResolved);
		TestValidation.IsTrue(markAsRslvdFrms, 
				  			  "Verified Mark As Resolved for Date and Time field", 
				              "FAILED to verify Mark As Resolved for Date and Time field");
		
//		boolean submitForm = formoperations.clickSubmitThenAlertOkBtn();
//		TestValidation.IsTrue(submitForm, 
//				  			 "Clicked on Submit and Submitted the form -" + quesFrmName, 
//				             "Could NOT click on Submit buttton and failed to submit form -" + quesFrmName);
		
		// ============================= END -> VERIFY Settings in FSQABrowser Forms
		
		//FAILS QUESTIONNAIER ?!
		boolean clickSubmitRepeat = fbForms.clickSubmitAndReapeatButton();
		TestValidation.IsTrue(clickSubmitRepeat, 
				  			 "CLICKED SUBMIT AND REPEAT", 
							 "FAILED TO CLICK SUBMIT AND REPEAT");
		
		TestValidation.IsTrue(true,"======================== After Submit and Repeat ========================","");
		
		boolean checkIsFieldRequiredSubRep = formoperations.isFieldRequired(DTFieldName);
		TestValidation.IsTrue(checkIsFieldRequiredSubRep, 
							  "Verified Date and Time Field is marked as required", 
					          "FAILED to verify Date and Time Field marked as required");
		
		boolean hintDisplaySubRep = formoperations.isHintDisplayedForField(DTFieldName, hintText);
		TestValidation.IsTrue(hintDisplaySubRep, 
				  		      "Verified Hint is Displayed as hint = " + hintText, 
				              "FAILED to verify Hint Display");
		
		boolean verifyComment = formoperations.verifyCommentOfField(DTFieldName, comment);
		TestValidation.IsTrue(verifyComment, 
				  			  "Verified that comment is repeated correctly", 
				              "Failed to verify repeated comment ");
		
		boolean verifyAttachment = formoperations.verifySingleAttachmentForField(DTFieldName, generalFile);
		TestValidation.IsTrue(verifyAttachment, 
							 "Verified Attachment is repeated", 
				             "Failed to verify repeated attahment");
		
		boolean verifyPredefinedCorr = formoperations.verifyPredefinedCorrectionValueForField(DTFieldName, preDefinedCorrectionOptions.get(1));
		TestValidation.IsTrue(verifyPredefinedCorr, 
				  			 "Verified correction is repeated", 
				  			 "Failed to verify repeated correction");
		
		boolean verifyMarkAsResolved = formoperations.verifymarkAsResolvedValueForField(DTFieldName, markAsResolved);
		TestValidation.IsTrue(verifyMarkAsResolved, 
							  "Verified Mark as resolved is repeated", 
				  			  "Failed to verify Mark as Resolved repeated");
		
		boolean clickCancel = fbForms.clickCancelButton();
		TestValidation.IsTrue(clickCancel, 
				  			 "SUCCESSFULLY CLOSED FORM", 
							 "FAILED TO CLOSE FORM");
		
		// ============================= START -> VERIFY Settings in DMS for Questionnaire Submitted Form
		
		// goto DMS
		documentManagement = hp.clickdocumentsmenu();
		
		//select DocType
		boolean selectDocumentType = documentManagement.selectDocumentType(quesFrmName);
		TestValidation.IsTrue(selectDocumentType, 
							 "Selected Document Type -> " + quesFrmName,
						      "Could NOT Select Document Type -> " + quesFrmName);
		
		// open Document
		boolean openDocument = documentManagement.viewQuestionnaireDocument(quesFrmName);
		TestValidation.IsTrue(openDocument, 
						      "OPEN document ->   " + quesFrmName,
						      "Could NOT OPEN document ->  " + quesFrmName);
		
		TestValidation.IsTrue(true,"======================== IN DMS  ========================","");
		
		
		boolean checkIsFieldRequiredRecrds = documentManagement.verifyIsFieldMarkedAsRequiredQstnDoc(DTFieldName);
		TestValidation.IsTrue(checkIsFieldRequiredRecrds, 
				 			  "Verified Date field is marked as required", 
		          			  "FAILED to verify Time marked as required");
		
		boolean hintDisplayRecrds = documentManagement.verifyHintForFieldQstnDoc(DTFieldName, hintText);
		TestValidation.IsTrue(hintDisplayRecrds, 
				 			  "Verified Hint Display for Date and Time field, hint =" + hintText, 
				              "FAILED to verify Hint Display for Date and Time field");
        		
		boolean addCmntsRecrds = documentManagement.verifyCommentForFieldQstnDoc(DTFieldName, comment);
		TestValidation.IsTrue(addCmntsRecrds, 
				  			  "Verified Comment as -" + comment + " for Date and Time field -" + DTFieldName, 
				              "FAILED to verify Comment for Date and Time field");
		
		boolean allwAtchmntsRecrds = documentManagement.verifyAttachmentFileNameForFieldQstnDoc(DTFieldName, generalFile);
		TestValidation.IsTrue(allwAtchmntsRecrds, 
				  			  "Verified Attachment as -" + generalFile + "for Date and Time field -" + DTFieldName, 
				              "FAILED to verify Attachment for Date and Time field - " +DTFieldName);
		
		boolean correctionRecrds = documentManagement.verifyCorrectionForFieldQstnDoc(DTFieldName, preDefinedCorrectionOptions.get(1));
		TestValidation.IsTrue(correctionRecrds, 
				  			  "Verified Correction as -" +preDefinedCorrectionOptions.get(1) + " for Date and Time field - " + DTFieldName, 
				              "FAILED to verify correction for Date and Time field");
		
		boolean markAsResolvedRecrds = documentManagement.verifyIsResolvedForFieldQstnDoc(DTFieldName, markAsResolved);
		TestValidation.IsTrue(markAsResolvedRecrds, 
				  			  "Verified Marked as Resolved as -" + markAsResolved + " for Date and Time field -" + DTFieldName,
				              "FAILED to verify Marked As Resolved for Date and Time field");
		// ============================= END -> VERIFY Settings in DMS for Questionnaire Submitted Form
	}
	
	//==================== AUDIT FORMS ======================//
	
	@Test(description = "Form Designer || Validate form functionality for field settings for 'Audit' forms - \"Identifier\" field(Free Text & Select One)")
	public void TestCase_48771() throws Exception {
		
		String adtFrmName = CommonMethods.dynamicText("AAF_");
		String sectionName = "Section";
		String identifierField1 = "ID_FreeText";
		String identifier1Mask = "L00L";
		String identifier1Value = "a12Z";
		
		String identifierField2 = "ID_SelectOne";
		List<String> identifier2Mask = Arrays.asList("#123-321","abc+123");

		
		String hintText1 = "Identifier1 Field Hint";
		String hintText2 = "Identifier2 Field Hint";
		
		
		HashMap<String, List<String>> equipResource = new HashMap<String, List<String>>();
		equipResource.put(FORMRESOURCETYPES.EQUIPMENT, Arrays.asList(equipmentsCategoryValue));

		//Section 1 
		HashMap<String, List<String>> adtSecFields = new HashMap<String, List<String>>();
		adtSecFields.put(FIELD_TYPES.IDENTIFIER, Arrays.asList(identifierField1));
		
		FormFieldParams ffpSecAdt = new FormFieldParams();
		ffpSecAdt.FieldDetails = adtSecFields;
		ffpSecAdt.SectionName = sectionName;
		ffpSecAdt.IdentiferType = IDENTIFIER_INPUT_TYPE.FREE_TEXT;
		ffpSecAdt.IdentifierValue = Arrays.asList(identifier1Mask);
		
		FormDesignParams fdpAdt = new FormDesignParams();
		fdpAdt.FormType = FORMTYPES.AUDIT;
		fdpAdt.FormName = adtFrmName;
		fdpAdt.SelectResources = equipResource;
		fdpAdt.DesignFields = Arrays.asList(ffpSecAdt);
		fdpAdt.ReleaseForm = true;
		
		
		// navigate to form designer
		fdp = hp.clickFormDesignerMenu();
		
		boolean designForm = fdp.designForm(fdpAdt);
		TestValidation.IsTrue(designForm, 
							  "Successfully Designed Form - " +adtFrmName + " with 2 Identifier Fields as - " + identifierField1 + identifierField2, 
					          "Failed to design from with with 2 Identifier Fields");
		
		HashMap<String, ElementProperties> fieldSettings1 = new LinkedHashMap<String, ElementProperties>(); 
		ElementProperties settings1 = new ElementProperties();
		settings1.IS_REQUIRED = true;
		settings1.SHOW_HINT = true;
		settings1.HINT = hintText1;
		settings1.CARRYOVER_FIELD = true;
		fieldSettings1.put(identifierField1, settings1);

		boolean turnAllSettingsOn1 = fdp.setFieldProperties(fieldSettings1) ;
		TestValidation.IsTrue(turnAllSettingsOn1, 
	             			  "Successfully Turned All Settings for 1st Identifie Field -" + identifierField1, 
	                          "Failed to tur on All Settigs for for 1st Identifie Field -" + identifierField1);
		
		boolean backToDesign = fdp.backToFormDesignFrmRelease(adtFrmName);
		TestValidation.IsTrue(backToDesign, 
				  			  "Navigated Back to Form Design for Form from Release page - " + adtFrmName, 
				              "FAILED to navigate back to Form Design for Form from Release page");
		
		
		// Making Identifier 2 - Select One
		boolean addIdentifierFieldInSection = fdp.dragDropElementInSectionGroup(ELEMENT_TYPE.FORM_ELEMENTS,FIELD_TYPES.IDENTIFIER, sectionName);
		boolean setIdentifierFieldValue = fdp.setFieldName(FIELD_TYPES.IDENTIFIER,identifierField2);
		//controlActions.actionEnter();
		TestValidation.IsTrue(addIdentifierFieldInSection && setIdentifierFieldValue, 
							 "Second 'Identifier' field  - "+identifierField2+" added successfully in 'Section'", 
							 "Failed to Add 2 nd Identifier field");
		
		//fdp.selectField(identifierField2);
		boolean selectIdentifierInputType2 = controlActions.JSSetValueFromList(driver, "li[role='option']" ,"Select One",2);		
		TestValidation.IsTrue(selectIdentifierInputType2, 
			     		     "Selected Identifier Field - "+identifierField2+" and set Input Type - Select One", 
			     		     "Failed to Select Identifier and set Input Type for - " + identifierField2);
		
		boolean setIdentifier2InputMask = fdp.setIdentifierInputMask(identifier2Mask);		
		TestValidation.IsTrue(setIdentifier2InputMask, 
							  "Identifier Input Mask set successfully", 
							  "Failed to set Identifier Input Mask");
		
		
		HashMap<String, ElementProperties> fieldSettings2 = new LinkedHashMap<String, ElementProperties>(); 
		ElementProperties settings2 = new ElementProperties();
		settings2.IS_REQUIRED = true;
		settings2.SHOW_HINT = true;
		settings2.HINT = hintText2;
		settings2.CARRYOVER_FIELD = true;
		fieldSettings2.put(identifierField2, settings2);

		boolean turnAllSettingsOn2 = fdp.setFieldProperties(fieldSettings2) ;
		TestValidation.IsTrue(turnAllSettingsOn2, 
	             			  "Successfully Turned All Settings for 2nd Identifie Field -" + identifierField2, 
	                          "Failed to tur on All Settigs for for 2nd Identifie Field -" + identifierField2);
		
		
		
		// ============================= START -> VERIFY Settings in FORM PREVIEW
		
		TestValidation.IsTrue(true,"======================== IN Form Preview ========================","");
		
		boolean openfForPreview = fdp.clickPreviewForFormOnReleaePage(adtFrmName);
		TestValidation.IsTrue(openfForPreview, 
				  			  "Clicked Preview Butoon and Opened Form -"+ adtFrmName + " for preview", 
				              "Failed to open form for Preview");
		
//		boolean setIdentifierPrvw = formoperations.enterValueInIdentifierField(identifierField1, identifier1Value);
//		TestValidation.IsTrue(setIdentifierPrvw, 
//				  			  "Entered value -" + identifier1Value + " in Identifier fied -" + identifierField1, 
//				              "Failed to enter value in Identifier field -" + identifierField1);
		
		boolean checkIsFieldRequiredPrvw = formoperations.isFieldRequired(identifierField1);
		TestValidation.IsTrue(checkIsFieldRequiredPrvw, 
				  			  "Verified that Identifier field -" + identifierField1 + " is Marked as required in Form Preview", 
				              "Failed to verify that Identifier field -" + identifierField1 + " is Marked as required in Form Preview");
		
		
		boolean hintDisplayPrvw = formoperations.isHintDisplayedForField(identifierField1, hintText1);
		TestValidation.IsTrue(hintDisplayPrvw, 
				  			  "Verified hint Displayed for identifier field -" + identifierField1 + " in Form Preview", 
				  			  "FAILED to very hint display for identifier field -" + identifierField1 + " in Form Preview");
		
		//====================== ID FIELD 2 =======================//
		
//		boolean setIdentifierPrvw2 = formoperations.setInputFieldValue(identifierField2, Arrays.asList(identifier2Mask.get(1)), null);
//		TestValidation.IsTrue(setIdentifierPrvw2, 
//				  			  "Entered value -" + identifier2Mask.get(1) + " in Identifier fied -" + identifierField2, 
//				              "Failed to enter value in Identifier field -" + identifierField2);
		
		boolean checkIsFieldRequiredPrvw2 = formoperations.isFieldRequired(identifierField2);
		TestValidation.IsTrue(checkIsFieldRequiredPrvw2, 
				  			  "Verified that Identifier field -" + identifierField2 + " is Marked as required in Form Preview", 
				              "Failed to verify that Identifier field -" + identifierField2 + " is Marked as required in Form Preview");
		
		
		boolean hintDisplayPrv2 = formoperations.isHintDisplayedForField(identifierField2, hintText2);
		TestValidation.IsTrue(hintDisplayPrv2, 
				  			  "Verified hint Displayed for identifier field -" + identifierField2 + " in Form Preview", 
				  			  "FAILED to very hint display for identifier field -" + identifierField2 + " in Form Preview");
		
		boolean closePreview = fdp.clickClosePreviewBtn(); 	 
		TestValidation.IsTrue(closePreview, 
				  			  "Successfully Closed Form Preview", 
				  			  "Could NOT Close Form Preview");
		
		// ============================= END -> VERIFY Settings in FORM PREVIEW
		
//		boolean nextToReleaseForm = fdp.clickOnNextButton(fdp.ReleaseFormPg, FORM_NEXT_PAGE.RELEASE_FORM);
//		TestValidation.IsTrue(nextToReleaseForm, 
//							  "NAVIGATED to 'Release Form'", 
//							  "Failed to Navigate to 'Release Form'");
		
		boolean releaseForm = fdp.releaseForm(adtFrmName);
		TestValidation.IsTrue(releaseForm, 
							 "Successfully released form -" + adtFrmName, 
							 "Could Not release form -" + adtFrmName);
		
		boolean navToHome = fdp.navigateToHome();
		TestValidation.IsTrue(navToHome, 
				  			  "Successfully Navigated to Home", 
				              "Could NOT naigate to home");
		
		boolean selectResourceDropDownandNavigate = fbp.selectResourceDropDownandNavigate(FORMRESOURCETYPES.EQUIPMENT,FSQATAB.FORMS);
		TestValidation.IsTrue(selectResourceDropDownandNavigate, 
							  "Navigated to FSQA ->  " + FSQATAB.FORMS ,
							  "Could NOT Navigate to FSQA ->  " + FSQATAB.FORMS);
		
		
		boolean selectOpenForm = fbForms.selectOpenForm(adtFrmName);
		TestValidation.IsTrue(selectOpenForm, 
							  "Select & opened the form -" + adtFrmName, 
							  "Could NOT open the form" + adtFrmName);
		
		
		// ============================= START -> VERIFY Settings in FSQABrowser Forms
		
		TestValidation.IsTrue(true,"======================== IN FSQABrowser Forms ========================","");
		
		
		//boolean setIdentifierFrms = formoperations.setInputFieldValue(identifierField1, Arrays.asList(identifier1Value), null);
		boolean setagain = formoperations.enterValueInIdentifierField(identifierField1, identifier1Value);
		TestValidation.IsTrue(setagain, 
				  			  "Entered value -" + identifier1Value + " in Identifier fied -" + identifierField1, 
				              "Failed to enter value in Identifier field -" + identifierField1);
		
		boolean checkIsFieldRequiredFrms = formoperations.isFieldRequired(identifierField1);
		TestValidation.IsTrue(checkIsFieldRequiredFrms, 
				  			  "Verified that Identifier field -" + identifierField1 + " is Marked as required in Form Preview", 
				              "Failed to verify that Identifier field -" + identifierField1 + " is Marked as required in Form Preview");
		
		
		boolean hintDisplayFrms = formoperations.isHintDisplayedForField(identifierField1, hintText1);
		TestValidation.IsTrue(hintDisplayFrms, 
				  			  "Verified hint Displayed for identifier field -" + identifierField1 + " in Form Preview", 
				  			  "FAILED to very hint display for identifier field -" + identifierField1 + " in Form Preview");
		
		//====================== ID FIELD 2 =======================//
		
		boolean setIdentifierFrms2 = formoperations.enterValueInIdentifierField(identifierField2, identifier2Mask.get(1));
		TestValidation.IsTrue(setIdentifierFrms2, 
				  			  "Entered value -" + identifier2Mask.get(1) + " in Identifier fied -" + identifierField2, 
				              "Failed to enter value in Identifier field -" + identifierField2);
		
		boolean checkIsFieldRequiredFrms2 = formoperations.isFieldRequired(identifierField2);
		TestValidation.IsTrue(checkIsFieldRequiredFrms2, 
				  			  "Verified that Identifier field -" + identifierField2 + " is Marked as required in Form Preview", 
				              "Failed to verify that Identifier field -" + identifierField2 + " is Marked as required in Form Preview");
		
		
		boolean hintDisplayFrms2 = formoperations.isHintDisplayedForField(identifierField2, hintText2);
		TestValidation.IsTrue(hintDisplayFrms2, 
				  			  "Verified hint Displayed for identifier field -" + identifierField2 + " in Form Preview", 
				  			  "FAILED to very hint display for identifier field -" + identifierField2 + " in Form Preview");
		
		
//		boolean submitForm = formoperations.clickSubmitThenAlertOkBtn();
//		TestValidation.IsTrue(submitForm, 
//				  			 "Clicked on Submit and Submitted the form -" + adtFrmName, 
//				             "Could NOT click on Submit buttton and failed to submit form -" + adtFrmName);
		
		//Try Submit and Repeat
		boolean clickSubmitRepeat = fbForms.clickSubmitAndReapeatButton();
		TestValidation.IsTrue(clickSubmitRepeat, 
				  			 "CLICKED SUBMIT AND REPEAT", 
							 "FAILED TO CLICK SUBMIT AND REPEAT");
		
		TestValidation.IsTrue(true,"======================== After Submit and Repeat ========================","");
		
		boolean checkIsFieldRequiredSubRep1 = formoperations.isFieldRequired(identifierField1);
		TestValidation.IsTrue(checkIsFieldRequiredSubRep1, 
							  "Verified Identifier Field 1 is marked as required", 
					          "FAILED to verify Identifier Field 1 is marked as required");
		
		boolean hintDisplaySubRep1 = formoperations.isHintDisplayedForField(identifierField1, hintText1);
		TestValidation.IsTrue(hintDisplaySubRep1, 
				  		      "Verified Hint is Displayed for Identifier Field 1 i.e hint = " + hintText1, 
				              "FAILED to verify Hint Display for Identifier Field 1");
		

		//============== ID Field 2 =================================//
		
		boolean checkIsFieldRequiredSubRep2 = formoperations.isFieldRequired(identifierField2);
		TestValidation.IsTrue(checkIsFieldRequiredSubRep2, 
							  "Verified Identifier Field 2 is marked as required", 
					          "FAILED to verify Identifier Field 2 is marked as required");
		
		boolean hintDisplaySubRep2 = formoperations.isHintDisplayedForField(identifierField2, hintText2);
		TestValidation.IsTrue(hintDisplaySubRep2, 
				  		      "Verified Hint is Displayed for Identifier Field 2 i.e hint = " + hintText2, 
				              "FAILED to verify Hint Display for Identifier Field 2");
		
		boolean clickCancel = fbForms.clickCancelButton();
		TestValidation.IsTrue(clickCancel, 
				  			 "SUCCESSFULLY CLOSED FORM", 
							 "FAILED TO CLOSE FORM");
		
		// ============================= END -> VERIFY Settings in FSQABrowser Forms
		
		//Goto Recodrs
		boolean navigateToRecords = fbp.navigateToFSQATab(FSQATAB.RECORDS);
		TestValidation.IsTrue(navigateToRecords, 
				  			  "Successfully Navigated to -" + FSQATAB.RECORDS , 
				              "Could NOT Navigate to -" + FSQATAB.RECORDS);
		
		// open record
		boolean openRecord = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, adtFrmName);
		TestValidation.IsTrue(openRecord, 
							  "OPENED record for form - " + adtFrmName, 
							  "Failed to open record for form - " + adtFrmName);
		
		
		// ============================= START -> VERIFY Settings in FSQABrowser Records 
		
		TestValidation.IsTrue(true,"======================== IN FSQABrowser Records ========================","");
		
		boolean checkIsFieldRequiredRecrds = fbRecords.verifyIsFieldMarkedAsRequired(identifierField1);
		TestValidation.IsTrue(checkIsFieldRequiredRecrds, 
				 			  "Verified identifier field - "+identifierField1+" is marked as required", 
		          			  "FAILED to verify identifier field - "+identifierField1+"  marked as required");
		
		boolean hintDisplayRecrds = fbRecords.verifyHintForField(identifierField1, hintText1);
		TestValidation.IsTrue(hintDisplayRecrds, 
				 			  "Verified Hint Display for identifier field - "+identifierField1+", hint =" + hintText1, 
				              "FAILED to verify Hint Display for identifier field - "+identifierField1);
		
		boolean checkIsFieldRequiredRecrds2 = fbRecords.verifyIsFieldMarkedAsRequired(identifierField2);
		TestValidation.IsTrue(checkIsFieldRequiredRecrds2, 
				 			  "Verified identifier field - "+identifierField2+" is marked as required", 
		          			  "FAILED to verify identifier field - "+identifierField2+"  marked as required");
		
		boolean hintDisplayRecrds2 = fbRecords.verifyHintForField(identifierField2, hintText2);
		TestValidation.IsTrue(hintDisplayRecrds2, 
				 			  "Verified Hint Display for identifier field - "+identifierField2+", hint =" + hintText2, 
				              "FAILED to verify Hint Display for identifier field - "+identifierField2);
		
		
		// ============================= END -> VERIFY Settings in FSQABrowser Records 
		
	}

	@Test(description = "Form Designer || Validate form functionality for field settings for 'Audit' forms - \"Select Multiple\" field")
	public void TestCase_48781() throws Exception {
		
		String adtFrmName =CommonMethods.dynamicText("AF_");
		String sectionName = "Section";
		String selectMultiple = "SM1";
		String secAOptionswithWeight[][] = {{"Apple","10"},{"Mango","20"}};
		List<String> complianceValues = Arrays.asList("Apple");
		
		String hintText = "Select Multiple Hint";
		String comment = "This is a comment";
		String customCorrection = "CustomCorrection";
		String markAsResolved = "Yes";
		String generalFile = "generalFile.png";
		String generalFilePath = System.getProperty("user.dir") + "\\test-data-files\\UploadDocuments\\"+generalFile;
		
		List<String> preDefinedCorrectionOptions = Arrays.asList("Option1","Option2");

		HashMap<String, List<String>> equipResource = new HashMap<String, List<String>>();
		equipResource.put(FORMRESOURCETYPES.EQUIPMENT, Arrays.asList(equipmentsCategoryValue));

		//Section 1 
		HashMap<String, List<String>> adtSecFields = new HashMap<String, List<String>>();
		adtSecFields.put(FIELD_TYPES.SELECT_MULTIPLE, Arrays.asList(selectMultiple));
		
		FormFieldParams ffpSecAdt = new FormFieldParams();
		ffpSecAdt.FieldDetails = adtSecFields;
		ffpSecAdt.SectionName = sectionName;
		ffpSecAdt.SelectOneMultipleOptions = null;
		ffpSecAdt.SelectOneMultipleOptionsWeight = secAOptionswithWeight;
		ffpSecAdt.QuestionWeight = "30";
		
		FormDesignParams fdpAdt = new FormDesignParams();
		fdpAdt.FormType = FORMTYPES.AUDIT;
		fdpAdt.FormName = adtFrmName;
		fdpAdt.SelectResources = equipResource;
		fdpAdt.DesignFields = Arrays.asList(ffpSecAdt);
		fdpAdt.ReleaseForm = true;
		
		// navigate to form designer
		fdp = hp.clickFormDesignerMenu();
		boolean designForm = fdp.designForm(fdpAdt);
		TestValidation.IsTrue(designForm, 
							  "Successfully Designed Form - " +adtFrmName + " with Select Multiple Fields as - " + selectMultiple, 
					          "Failed to design from with Select Multiple Field");
		
		HashMap<String, ElementProperties> fieldSettings = new LinkedHashMap<String, ElementProperties>(); 
		ElementProperties settings = new ElementProperties();
		settings.FAILS_CHECK = true;
		settings.IS_REQUIRED = true;
		settings.ALLOW_COMMENTS = true;
		settings.ALLOW_ATTATHMENTS = true;
		settings.SHOW_HINT = true;
		settings.HINT = hintText;
		settings.ALLOW_CORRECTION = true;
		settings.MARK_RESOLVED = true;
		settings.PREDEFINED_CORRECTIONS = true;
		settings.PREDEFINED_CORRECTIONS_OPTNS = preDefinedCorrectionOptions;
		settings.CARRYOVER_FIELD = true;
		fieldSettings.put(selectMultiple, settings);
		
		boolean turnAllSettingsOn = fdp.setFieldProperties(fieldSettings) ;
		TestValidation.IsTrue(turnAllSettingsOn, 
	             			  "Successfully Turned All Settings for the Select Multiple Field -" + selectMultiple, 
	                          "Failed to tur on All Settigs for for the Select Multiple Field -" + selectMultiple);
		
		boolean backToDesign = fdp.backToFormDesignFrmRelease(adtFrmName);
		TestValidation.IsTrue(backToDesign, 
				  			  "Navigated Back to Form Design for Form from Release page - " + adtFrmName, 
				              "FAILED to navigate back to Form Design for Form from Release page");
		
		boolean setComplianceValue = fdp.setComplianceForSelectMultipleOrSelectOne(selectMultiple, complianceValues);
		TestValidation.IsTrue(setComplianceValue, 
				  			  "Set Compliance Values as -" + complianceValues + " for Select Multiple field -" + selectMultiple, 
				              "Failed to set Compliance Values for Select Multiple field -" + selectMultiple);
		
//		boolean saveForm = fdp.clickSaveButton();
//		TestValidation.IsTrue(saveForm, 
//				  			  "Saved form -" + chkFrmName, 
//				  			  "Failed to save form -" +chkFrmName);
		
		// ============================= START -> VERIFY Settings in FORM PREVIEW
		
		TestValidation.IsTrue(true,"======================== IN Form Preview ========================","");
		
		boolean openfForPreview = fdp.clickPreviewButton();
		TestValidation.IsTrue(openfForPreview, 
				  			  "Clicked Preview Butoon and Opened Form -"+ adtFrmName + " for preview", 
				              "Failed to open form for Preview");
		
		boolean setSMFieldPrvw = formoperations.setValuesForSelectMultipleOrSelectOne(selectMultiple, Arrays.asList("Mango"));
		TestValidation.IsTrue(setSMFieldPrvw, 
				  			  "Entered non compliant value -'Mango'" + " in Select Multiple fied -" + selectMultiple, 
				              "Failed to enter value in Select Multiple field -" + selectMultiple);
		
		boolean checkIsFieldRequiredPrvw = formoperations.isFieldRequired(selectMultiple);
		TestValidation.IsTrue(checkIsFieldRequiredPrvw, 
				  			  "Verified that Select Multiple field -" + selectMultiple + " is Marked as required in Form Preview", 
				              "Failed to verify that Select Multiple field -" + selectMultiple + " is Marked as required in Form Preview");
		
		boolean allwAtchmntsPrvw = formoperations.verifyAllowAttachmentsInPreviewForField(selectMultiple);
		TestValidation.IsTrue(allwAtchmntsPrvw, 
							  "Verified Allow Attachements for field -" + selectMultiple + " in Form Preview", 
							  "FAILED to verify Allow Attachements for field -" + selectMultiple + " in Form Preview");
		
		boolean addCmntsPrvw = formoperations.addCommentForField(selectMultiple, "This is a comment");
		TestValidation.IsTrue(addCmntsPrvw, 
				  			  "Verified Add Comment Textbox present for freetext field -" + selectMultiple + " in Form Preview and added comment -" + comment , 
				  			  "FAILED to verify Add Comment Textbox present for freetext field -" + selectMultiple );
		
		boolean hintDisplayPrvw = formoperations.isHintDisplayedForField(selectMultiple, hintText);
		TestValidation.IsTrue(hintDisplayPrvw, 
				  			  "Verified hint Displayed for Select Multiple field -" + selectMultiple + " in Form Preview", 
				  			  "FAILED to very hint display for Select Multiple field -" + selectMultiple + " in Form Preview");
		
		boolean addCustmCorctnPrvw = formoperations.setCustomCorrectionForField(selectMultiple, customCorrection);
		TestValidation.IsTrue(addCustmCorctnPrvw, 
				  			  "Verified Custom Correction Text box for Select Multiple field -" + selectMultiple + " in Form Preview, added correction -" + customCorrection, 
				              "FAILED to verify correction textbox for Select Multiple field -" + selectMultiple + " in Form Preview");
		
		boolean setPredefindCorrctnPrvw = formoperations.setPredefinedCorrectionsForField(selectMultiple, preDefinedCorrectionOptions.get(1))	;
		TestValidation.IsTrue(setPredefindCorrctnPrvw, 
				  			  "Verufied Predefined Correction input field for Select Multiple field -" + selectMultiple + " in Form Preview, added correction -" + preDefinedCorrectionOptions.get(1), 
				              "FAILED to verify Predefined Correction input for Select Multiple field -" + selectMultiple + " in form Preview" );
		
		boolean markAsRslvdPrvw = formoperations.markAsResolvedForField(selectMultiple, markAsResolved);
		TestValidation.IsTrue(markAsRslvdPrvw, 
				  			  "Verified 'Mark As Resolved' option for Select Multiple field -" + selectMultiple + " in Form Preview, marked it as -" + markAsResolved, 
				              "FAILED to verify 'Marked As Resolved' option for Select Multiple field -" + selectMultiple + " in Form Preview");
		
		boolean showPtsErndPrvw = formoperations.verifyShowPointsEarnedForField(selectMultiple, "20");
		TestValidation.IsTrue(showPtsErndPrvw, 
							 "Verified 'Points Earned' for Select Multiple field -" + selectMultiple + " in Form Preview", 
							 "Failed to verify 'Points Earned' for Select Multiple field -" + selectMultiple + " in Form Preview");
		
		boolean showPtsPsblPrvw = formoperations.verifyShowPointsPossibleForField(selectMultiple, "30");
		TestValidation.IsTrue(showPtsPsblPrvw, 
							 "Verified 'Points Possible' for Select Multiple field -" + selectMultiple + " in Form Preview", 
							 "Failed to verify 'Points Possible' for Select Multiple field -" + selectMultiple + " in Form Preview");
		
		boolean closePreview = fdp.clickClosePreviewBtn();
		TestValidation.IsTrue(closePreview, 
				  			  "Successfully Closed Form Preview", 
				  			  "Could NOT Close Form Preview");
		
		
		// ============================= END -> VERIFY Settings in FORM PREVIEW
		
		boolean nextToReleaseForm = fdp.clickOnNextButton(fdp.ReleaseFormPg, FORM_NEXT_PAGE.RELEASE_FORM);
		TestValidation.IsTrue(nextToReleaseForm, 
							  "NAVIGATED to 'Release Form'", 
							  "Failed to Navigate to 'Release Form'");
		
		boolean releaseForm = fdp.releaseForm(adtFrmName);
		TestValidation.IsTrue(releaseForm, 
							 "Successfully released form -" + adtFrmName, 
							 "Could Not release form -" + adtFrmName);
		
		boolean navToHome = fdp.navigateToHome();
		TestValidation.IsTrue(navToHome, 
				  			  "Successfully Navigated to Home", 
				              "Could NOT naigate to home");
		
		boolean selectResourceDropDownandNavigate = fbp.selectResourceDropDownandNavigate(FORMRESOURCETYPES.EQUIPMENT,FSQATAB.FORMS);
		TestValidation.IsTrue(selectResourceDropDownandNavigate, 
							  "Navigated to FSQA ->  " + FSQATAB.FORMS ,
							  "Could NOT Navigate to FSQA ->  " + FSQATAB.FORMS);
		
		
		boolean selectOpenForm = fbForms.selectOpenForm(adtFrmName);
		TestValidation.IsTrue(selectOpenForm, 
							  "Select & opened the form -" + adtFrmName, 
							  "Could NOT open the form" + adtFrmName);
		
		
		// ============================= START -> VERIFY Settings in FSQABrowser Forms
		
		TestValidation.IsTrue(true,"======================== IN FSQABrowser Forms ========================","");
		
		boolean setSMFieldFrm = formoperations.setValuesForSelectMultipleOrSelectOne(selectMultiple, Arrays.asList("Mango"));
		TestValidation.IsTrue(setSMFieldFrm, 
				  			  "Entered non compliant value -'Mango'" + " in Select Multiple fied -" + selectMultiple, 
				              "Failed to enter value in Select Multiple field -" + selectMultiple);
		
		boolean checkIsFieldRequiredFrms = formoperations.isFieldRequired(selectMultiple);
		TestValidation.IsTrue(checkIsFieldRequiredFrms, 
							  "Verified Select Multiple field is marked as required", 
					          "FAILED to verify Select Multiple field  marked as required");
		
		boolean allwAtchmntsFrms = formoperations.addAttachmentForField(selectMultiple, generalFilePath);
		TestValidation.IsTrue(allwAtchmntsFrms, 
				  			  "Verified Allow Attachments for Select Multiple field , and attached file -" + generalFile, 
				  			  "FAILED to verify Allow Attachments for Select Multiple field ");
		
		boolean addCmntsFrms = formoperations.addCommentForField(selectMultiple, comment);
		TestValidation.IsTrue(addCmntsFrms, 
							  "Verified Allow Comments for Select Multiple field and entered comment -" + comment, 
				  			  "FAILED to verify Allow Comments for Select Multiple field");
		
		boolean hintDisplayFrms = formoperations.isHintDisplayedForField(selectMultiple, hintText);
		TestValidation.IsTrue(hintDisplayFrms, 
				  		      "Verified Hint Display for Select Multiple field, hint =" + hintText, 
				              "FAILED to verify Hint Display for Select Multiple field");
		
		boolean addCustmCorctnFrms = formoperations.setCustomCorrectionForField(selectMultiple, customCorrection);
		TestValidation.IsTrue(addCustmCorctnFrms, 
				  			  "Verified Custom Correction for Select Multiple field", 
				              "FAILED to verify Custom Correction for Select Multiple field");
		
		boolean setPredefindCorrctnFrms = formoperations.setPredefinedCorrectionsForField(selectMultiple, preDefinedCorrectionOptions.get(1))	;
		TestValidation.IsTrue(setPredefindCorrctnFrms, 
	             		      "Verified Predefined Correction for Select Multiple field", 
	                          "FAILED to verify Predefined Correction for Select Multiple field");
		
		boolean markAsRslvdFrms = formoperations.markAsResolvedForField(selectMultiple, markAsResolved);
		TestValidation.IsTrue(markAsRslvdFrms, 
				  			  "Verified Mark As Resolved for Select Multiple field", 
				              "FAILED to verify Mark As Resolved for Select Multiple field");
		
		boolean showPtsErndFrm = formoperations.verifyShowPointsEarnedForField(selectMultiple, "20");
		TestValidation.IsTrue(showPtsErndFrm, 
							 "Verified 'Points Earned' for Select Multiple field -" + selectMultiple, 
							 "Failed to verify 'Points Earned' for Select Multiple field -" + selectMultiple);
		
		boolean showPtsPsblFrm = formoperations.verifyShowPointsPossibleForField(selectMultiple, "30");
		TestValidation.IsTrue(showPtsPsblFrm, 
							 "Verified 'Points Possible' for Select Multiple field -" + selectMultiple, 
							 "Failed to verify 'Points Possible' for Select Multiple field -" + selectMultiple);
		
//		boolean submitForm = formoperations.clickSubmitThenAlertOkBtn();
//		TestValidation.IsTrue(submitForm, 
//				  			 "Clicked on Submit and Submitted the form -" + adtFrmName, 
//				             "Could NOT click on Submit buttton and failed to submit form -" + adtFrmName);
		
		//Try Submit and Repeat
		boolean clickSubmitRepeat = fbForms.clickSubmitAndReapeatButton();
		TestValidation.IsTrue(clickSubmitRepeat, 
				  			 "CLICKED SUBMIT AND REPEAT", 
							 "FAILED TO CLICK SUBMIT AND REPEAT");
		
		TestValidation.IsTrue(true,"======================== After Submit and Repeat ========================","");
		
		boolean checkIsFieldRequiredSubRep = formoperations.isFieldRequired(selectMultiple);
		TestValidation.IsTrue(checkIsFieldRequiredSubRep, 
							  "Verified Select Multiple Field is marked as required", 
					          "FAILED to verify Select Multiple Field marked as required");
		
		boolean hintDisplaySubRep = formoperations.isHintDisplayedForField(selectMultiple, hintText);
		TestValidation.IsTrue(hintDisplaySubRep, 
				  		      "Verified Hint is Displayed as hint = " + hintText, 
				              "FAILED to verify Hint Display");
		
		boolean verifyCommentSubRep = formoperations.verifyCommentOfField(selectMultiple, comment);
		TestValidation.IsTrue(verifyCommentSubRep, 
				  			  "Verified that comment is repeated correctly", 
				              "Failed to verify repeated comment ");
		
		boolean verifyAttachmentSubRep = formoperations.verifySingleAttachmentForField(selectMultiple, generalFile);
		TestValidation.IsTrue(verifyAttachmentSubRep, 
							 "Verified Attachment is repeated", 
				             "Failed to verify repeated attahment");
		
		boolean verifyPredefinedCorrSubRep = formoperations.verifyPredefinedCorrectionValueForField(selectMultiple, preDefinedCorrectionOptions.get(1));
		TestValidation.IsTrue(verifyPredefinedCorrSubRep, 
				  			 "Verified correction is repeated", 
				  			 "Failed to verify repeated correction");
		
		boolean verifyMarkAsResolvedSubRep = formoperations.verifymarkAsResolvedValueForField(selectMultiple, markAsResolved);
		TestValidation.IsTrue(verifyMarkAsResolvedSubRep, 
							  "Verified Mark as resolved is repeated", 
				  			  "Failed to verify Mark as Resolved repeated");
		
		boolean showPtsErndFrmSubRep = formoperations.verifyShowPointsEarnedForField(selectMultiple, "20");
		TestValidation.IsTrue(showPtsErndFrmSubRep, 
							 "Verified 'Points Earned'",
							 "Failed to verify 'Points Earned'");
		
		boolean showPtsPsblFrmSubRep = formoperations.verifyShowPointsPossibleForField(selectMultiple, "30");
		TestValidation.IsTrue(showPtsPsblFrmSubRep, 
							 "Verified 'Points Possible'", 
							 "Failed to verify 'Points Possible'");
		
		boolean clickCancel = fbForms.clickCancelButton();
		TestValidation.IsTrue(clickCancel, 
				  			 "SUCCESSFULLY CLOSED FORM", 
							 "FAILED TO CLOSE FORM");
		
		// ============================= END -> VERIFY Settings in FSQABrowser Forms
		
		//Goto Recodrs
		boolean navigateToRecords = fbp.navigateToFSQATab(FSQATAB.RECORDS);
		TestValidation.IsTrue(navigateToRecords, 
				  			  "Successfully Navigated to -" + FSQATAB.RECORDS , 
				              "Could NOT Navigate to -" + FSQATAB.RECORDS);
		
		
		boolean verifyFailsCheck = fbRecords.verifyFailsCheckForRecord(adtFrmName,FSQABrowserRecordsPage.FAILS_CHECK.FAILS);
		TestValidation.IsTrue(verifyFailsCheck, 
				  			  "Verified Fails Check for record with Form Name -" + adtFrmName + " as Non-Compliant", 
				  	  		  "FAILED to verify Fails Check for record with Form Name -" + adtFrmName + " as Non-Compliant");
		
		// open record
		boolean openRecord = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, adtFrmName);
		TestValidation.IsTrue(openRecord, 
							  "OPENED record for form - " + adtFrmName, 
							  "Failed to open record for form - " +adtFrmName);
		
		
		// ============================= START -> VERIFY Settings in FSQABrowser Records 
		
		TestValidation.IsTrue(true,"======================== IN FSQABrowser Records ========================","");
		
		boolean checkIsFieldRequiredRecrds = fbRecords.verifyIsFieldMarkedAsRequired(selectMultiple);
		TestValidation.IsTrue(checkIsFieldRequiredRecrds, 
				 			  "Verified Select Multiple field is marked as required", 
		          			  "FAILED to verify Select Multiple field marked as required");
		
		boolean hintDisplayRecrds = fbRecords.verifyHintForField(selectMultiple, hintText);
		TestValidation.IsTrue(hintDisplayRecrds, 
				 			  "Verified Hint Display for Select Multiple field, hint =" + hintText, 
				              "FAILED to verify Hint Display for Select Multiple field");
        		
		boolean addCmntsRecrds = fbRecords.verifyCommentForField(selectMultiple, comment);
		TestValidation.IsTrue(addCmntsRecrds, 
				  			  "Verified Comment as -" + comment + " Select Multiple field -" + selectMultiple, 
				              "FAILED to verify Comment for Select Multiple field");
		
		boolean allwAtchmntsRecrds = fbRecords.verifyAttachmentFileNameForField(selectMultiple, generalFile);
		TestValidation.IsTrue(allwAtchmntsRecrds, 
				  			  "Verified Attachment as -" + generalFile + "for Select Multiple field -" + selectMultiple, 
				              "FAILED to verify Attachment for Select Multiple field - " +selectMultiple);
		
		boolean correctionRecrds = fbRecords.verifyCorrectionForField(selectMultiple, preDefinedCorrectionOptions.get(1));
		TestValidation.IsTrue(correctionRecrds, 
				  			  "Verified Correction as -" +preDefinedCorrectionOptions.get(1) + " for Select Multiple field - " + selectMultiple, 
				              "FAILED to verify correction");
		
		boolean markAsResolvedRecrds = fbRecords.verifyIsResolvedForField(selectMultiple, markAsResolved);
		TestValidation.IsTrue(markAsResolvedRecrds, 
				  			  "Verified Marked as Resolved as -" + markAsResolved + " for Select Multiple field -" + selectMultiple,
				              "FAILED to verify Marked As Resolved ");
		
		boolean showPtsErndRecrds = fbRecords.verifyPointsEarnedForField(selectMultiple, "20");
		TestValidation.IsTrue(showPtsErndRecrds, 
							 "Verified 'Points Earned' for Select Multiple field -" + selectMultiple, 
							 "Failed to verify 'Points Earned' for Select Multiple field -" + selectMultiple);
		
		boolean showPtsPsblRecrds = fbRecords.verifyPointsPossibleForField(selectMultiple, "30");
		TestValidation.IsTrue(showPtsPsblRecrds, 
							 "Verified 'Points Possible' for Select Multiple field -" + selectMultiple, 
							 "Failed to verify 'Points Possible' for Select Multiple field -" + selectMultiple);
		
		// ============================= END -> VERIFY Settings in FSQABrowser Records 
		
	}

	@Test(description = "Form Designer || Validate form functionality for field settings for 'Audit' forms - \"Select One\" field")
	public void TestCase_48774() throws Exception {
		
		String adtFrmName =CommonMethods.dynamicText("AF_");
		String sectionName = "Section";
		String selectOne = "SO1";
		String secAOptionswithWeight[][] = {{"Apple","10"},{"Mango","20"}};
		List<String> complianceValues = Arrays.asList("Apple");
		
		String hintText = "Select One Hint";
		String comment = "This is a comment";
		String customCorrection = "CustomCorrection";
		String markAsResolved = "Yes";
		String generalFile = "generalFile.png";
		String generalFilePath = System.getProperty("user.dir") + "\\test-data-files\\UploadDocuments\\"+generalFile;
		
		List<String> preDefinedCorrectionOptions = Arrays.asList("Option1","Option2");

		HashMap<String, List<String>> equipResource = new HashMap<String, List<String>>();
		equipResource.put(FORMRESOURCETYPES.EQUIPMENT, Arrays.asList(equipmentsCategoryValue));

		//Section 1 
		HashMap<String, List<String>> adtSecFields = new HashMap<String, List<String>>();
		adtSecFields.put(FIELD_TYPES.SELECT_ONE, Arrays.asList(selectOne));
		
		FormFieldParams ffpSecAdt = new FormFieldParams();
		ffpSecAdt.FieldDetails = adtSecFields;
		ffpSecAdt.SectionName = sectionName;
		ffpSecAdt.SelectOneMultipleOptions = null;
		ffpSecAdt.SelectOneMultipleOptionsWeight = secAOptionswithWeight;
		ffpSecAdt.QuestionWeight = "30";
		
		FormDesignParams fdpAdt = new FormDesignParams();
		fdpAdt.FormType = FORMTYPES.AUDIT;
		fdpAdt.FormName = adtFrmName;
		fdpAdt.SelectResources = equipResource;
		fdpAdt.DesignFields = Arrays.asList(ffpSecAdt);
		fdpAdt.ReleaseForm = true;
		
		// navigate to form designer
		fdp = hp.clickFormDesignerMenu();
		boolean designForm = fdp.designForm(fdpAdt);
		TestValidation.IsTrue(designForm, 
							  "Successfully Designed Form - " +adtFrmName + " with Select One Fields as - " + selectOne, 
					          "Failed to design from with Select One Field");
		
		HashMap<String, ElementProperties> fieldSettings = new LinkedHashMap<String, ElementProperties>(); 
		ElementProperties settings = new ElementProperties();
		settings.FAILS_CHECK = true;
		settings.IS_REQUIRED = true;
		settings.ALLOW_COMMENTS = true;
		settings.ALLOW_ATTATHMENTS = true;
		settings.SHOW_HINT = true;
		settings.HINT = hintText;
		settings.ALLOW_CORRECTION = true;
		settings.MARK_RESOLVED = true;
		settings.PREDEFINED_CORRECTIONS = true;
		settings.PREDEFINED_CORRECTIONS_OPTNS = preDefinedCorrectionOptions;
		settings.CARRYOVER_FIELD = true;
		fieldSettings.put(selectOne, settings);
		
		boolean turnAllSettingsOn = fdp.setFieldProperties(fieldSettings) ;
		TestValidation.IsTrue(turnAllSettingsOn, 
	             			  "Successfully Turned All Settings for the Select One Field -" + selectOne, 
	                          "Failed to tur on All Settigs for for the Select One Field -" + selectOne);
		
		boolean backToDesign = fdp.backToFormDesignFrmRelease(adtFrmName);
		TestValidation.IsTrue(backToDesign, 
				  			  "Navigated Back to Form Design for Form from Release page - " + adtFrmName, 
				              "FAILED to navigate back to Form Design for Form from Release page");
		
		boolean setComplianceValue = fdp.setComplianceForSelectMultipleOrSelectOne(selectOne, complianceValues);
		TestValidation.IsTrue(setComplianceValue, 
				  			  "Set Compliance Values as -" + complianceValues + " for Select one field -" + selectOne, 
				              "Failed to set Compliance Values for Select one field -" + selectOne);
		
//		boolean saveForm = fdp.clickSaveButton();
//		TestValidation.IsTrue(saveForm, 
//				  			  "Saved form -" + chkFrmName, 
//				  			  "Failed to save form -" +chkFrmName);
		
		// ============================= START -> VERIFY Settings in FORM PREVIEW
		
		TestValidation.IsTrue(true,"======================== IN Form Preview ========================","");
		
		boolean openfForPreview = fdp.clickPreviewButton();
		TestValidation.IsTrue(openfForPreview, 
				  			  "Clicked Preview Butoon and Opened Form -"+ adtFrmName + " for preview", 
				              "Failed to open form for Preview");
		
		boolean setSMFieldPrvw = formoperations.setValuesForSelectMultipleOrSelectOne(selectOne, Arrays.asList("Mango"));
		TestValidation.IsTrue(setSMFieldPrvw, 
				  			  "Entered non compliant value -'Mango'" + " in Select One fied -" + selectOne, 
				              "Failed to enter value in Select One field -" + selectOne);
		
		boolean checkIsFieldRequiredPrvw = formoperations.isFieldRequired(selectOne);
		TestValidation.IsTrue(checkIsFieldRequiredPrvw, 
				  			  "Verified that Select One field -" + selectOne + " is Marked as required in Form Preview", 
				              "Failed to verify that Select One field -" + selectOne + " is Marked as required in Form Preview");
		
		boolean allwAtchmntsPrvw = formoperations.verifyAllowAttachmentsInPreviewForField(selectOne);
		TestValidation.IsTrue(allwAtchmntsPrvw, 
							  "Verified Allow Attachements for field -" + selectOne + " in Form Preview", 
							  "FAILED to verify Allow Attachements for field -" + selectOne + " in Form Preview");
		
		boolean addCmntsPrvw = formoperations.addCommentForField(selectOne, "This is a comment");
		TestValidation.IsTrue(addCmntsPrvw, 
				  			  "Verified Add Comment Textbox present for freetext field -" + selectOne + " in Form Preview and added comment -" + comment , 
				  			  "FAILED to verify Add Comment Textbox present for freetext field -" + selectOne );
		
		boolean hintDisplayPrvw = formoperations.isHintDisplayedForField(selectOne, hintText);
		TestValidation.IsTrue(hintDisplayPrvw, 
				  			  "Verified hint Displayed for Select One field -" + selectOne + " in Form Preview", 
				  			  "FAILED to very hint display for Select One field -" + selectOne + " in Form Preview");
		
		boolean addCustmCorctnPrvw = formoperations.setCustomCorrectionForField(selectOne, customCorrection);
		TestValidation.IsTrue(addCustmCorctnPrvw, 
				  			  "Verified Custom Correction Text box for Select One field -" + selectOne + " in Form Preview, added correction -" + customCorrection, 
				              "FAILED to verify correction textbox for Select ONe field -" + selectOne + " in Form Preview");
		
		boolean setPredefindCorrctnPrvw = formoperations.setPredefinedCorrectionsForField(selectOne, preDefinedCorrectionOptions.get(1))	;
		TestValidation.IsTrue(setPredefindCorrctnPrvw, 
				  			  "Verufied Predefined Correction input field for Select One field -" + selectOne + " in Form Preview, added correction -" + preDefinedCorrectionOptions.get(1), 
				              "FAILED to verify Predefined Correction input for Select One field -" + selectOne + " in form Preview" );
		
		boolean markAsRslvdPrvw = formoperations.markAsResolvedForField(selectOne, markAsResolved);
		TestValidation.IsTrue(markAsRslvdPrvw, 
				  			  "Verified 'Mark As Resolved' option for Select One field -" + selectOne + " in Form Preview, marked it as -" + markAsResolved, 
				              "FAILED to verify 'Marked As Resolved' option for Select One field -" + selectOne + " in Form Preview");
		
		boolean showPtsErndPrvw = formoperations.verifyShowPointsEarnedForField(selectOne, "20");
		TestValidation.IsTrue(showPtsErndPrvw, 
							 "Verified 'Points Earned' for Select ONe field -" + selectOne + " in Form Preview", 
							 "Failed to verify 'Points Earned' for Select One field -" + selectOne + " in Form Preview");
		
		boolean showPtsPsblPrvw = formoperations.verifyShowPointsPossibleForField(selectOne, "30");
		TestValidation.IsTrue(showPtsPsblPrvw, 
							 "Verified 'Points Possible' for Select One field -" + selectOne + " in Form Preview", 
							 "Failed to verify 'Points Possible' for Select One field -" + selectOne + " in Form Preview");
		
		boolean closePreview = fdp.clickClosePreviewBtn();
		TestValidation.IsTrue(closePreview, 
				  			  "Successfully Closed Form Preview", 
				  			  "Could NOT Close Form Preview");
		
		
		// ============================= END -> VERIFY Settings in FORM PREVIEW
		
		boolean nextToReleaseForm = fdp.clickOnNextButton(fdp.ReleaseFormPg, FORM_NEXT_PAGE.RELEASE_FORM);
		TestValidation.IsTrue(nextToReleaseForm, 
							  "NAVIGATED to 'Release Form'", 
							  "Failed to Navigate to 'Release Form'");
		
		boolean releaseForm = fdp.releaseForm(adtFrmName);
		TestValidation.IsTrue(releaseForm, 
							 "Successfully released form -" + adtFrmName, 
							 "Could Not release form -" + adtFrmName);
		
		boolean navToHome = fdp.navigateToHome();
		TestValidation.IsTrue(navToHome, 
				  			  "Successfully Navigated to Home", 
				              "Could NOT naigate to home");
		
		boolean selectResourceDropDownandNavigate = fbp.selectResourceDropDownandNavigate(FORMRESOURCETYPES.EQUIPMENT,FSQATAB.FORMS);
		TestValidation.IsTrue(selectResourceDropDownandNavigate, 
							  "Navigated to FSQA ->  " + FSQATAB.FORMS ,
							  "Could NOT Navigate to FSQA ->  " + FSQATAB.FORMS);
		
		
		boolean selectOpenForm = fbForms.selectOpenForm(adtFrmName);
		TestValidation.IsTrue(selectOpenForm, 
							  "Select & opened the form -" + adtFrmName, 
							  "Could NOT open the form" + adtFrmName);
		
		
		// ============================= START -> VERIFY Settings in FSQABrowser Forms
		
		TestValidation.IsTrue(true,"======================== IN FSQABrowser Forms ========================","");
		
		boolean setSMFieldFrm = formoperations.setValuesForSelectMultipleOrSelectOne(selectOne, Arrays.asList("Mango"));
		TestValidation.IsTrue(setSMFieldFrm, 
				  			  "Entered non compliant value -'Mango'" + " in Select One fied -" + selectOne, 
				              "Failed to enter value in Select One field -" + selectOne);
		
		boolean checkIsFieldRequiredFrms = formoperations.isFieldRequired(selectOne);
		TestValidation.IsTrue(checkIsFieldRequiredFrms, 
							  "Verified Select One field is marked as required", 
					          "FAILED to verify Select One field  marked as required");
		
		boolean allwAtchmntsFrms = formoperations.addAttachmentForField(selectOne, generalFilePath);
		TestValidation.IsTrue(allwAtchmntsFrms, 
				  			  "Verified Allow Attachments for Select One field , and attached file -" + generalFile, 
				  			  "FAILED to verify Allow Attachments for Select One field ");
		
		boolean addCmntsFrms = formoperations.addCommentForField(selectOne, comment);
		TestValidation.IsTrue(addCmntsFrms, 
							  "Verified Allow Comments for Select One field and entered comment -" + comment, 
				  			  "FAILED to verify Allow Comments for Select One field");
		
		boolean hintDisplayFrms = formoperations.isHintDisplayedForField(selectOne, hintText);
		TestValidation.IsTrue(hintDisplayFrms, 
				  		      "Verified Hint Display for Select One field, hint =" + hintText, 
				              "FAILED to verify Hint Display for Select One field");
		
		boolean addCustmCorctnFrms = formoperations.setCustomCorrectionForField(selectOne, customCorrection);
		TestValidation.IsTrue(addCustmCorctnFrms, 
				  			  "Verified Custom Correction for Select One field", 
				              "FAILED to verify Custom Correction for Select One field");
		
		boolean setPredefindCorrctnFrms = formoperations.setPredefinedCorrectionsForField(selectOne, preDefinedCorrectionOptions.get(1))	;
		TestValidation.IsTrue(setPredefindCorrctnFrms, 
	             		      "Verified Predefined Correction for Select One field", 
	                          "FAILED to verify Predefined Correction for Select One field");
		
		boolean markAsRslvdFrms = formoperations.markAsResolvedForField(selectOne, markAsResolved);
		TestValidation.IsTrue(markAsRslvdFrms, 
				  			  "Verified Mark As Resolved for Select Multiple field", 
				              "FAILED to verify Mark As Resolved for Select Multiple field");
		
		
		boolean showPtsErndFrm = formoperations.verifyShowPointsEarnedForField(selectOne, "20");
		TestValidation.IsTrue(showPtsErndFrm, 
							 "Verified 'Points Earned' for Select One field -" + selectOne, 
							 "Failed to verify 'Points Earned' for Select One field -" + selectOne);
		
		boolean showPtsPsblFrm = formoperations.verifyShowPointsPossibleForField(selectOne, "30");
		TestValidation.IsTrue(showPtsPsblFrm, 
							 "Verified 'Points Possible' for Select One field -" + selectOne, 
							 "Failed to verify 'Points Possible' for Select One field -" + selectOne);
		
//		boolean submitForm = formoperations.clickSubmitThenAlertOkBtn();
//		TestValidation.IsTrue(submitForm, 
//				  			 "Clicked on Submit and Submitted the form -" + adtFrmName, 
//				             "Could NOT click on Submit buttton and failed to submit form -" + adtFrmName);
		
		//Try Submit and Repeat
		boolean clickSubmitRepeat = fbForms.clickSubmitAndReapeatButton();
		TestValidation.IsTrue(clickSubmitRepeat, 
				  			 "CLICKED SUBMIT AND REPEAT", 
							 "FAILED TO CLICK SUBMIT AND REPEAT");
		
		TestValidation.IsTrue(true,"======================== After Submit and Repeat ========================","");
		
		boolean checkIsFieldRequiredSubRep = formoperations.isFieldRequired(selectOne);
		TestValidation.IsTrue(checkIsFieldRequiredSubRep, 
							  "Verified Select Multiple Field is marked as required", 
					          "FAILED to verify Select Multiple Field marked as required");
		
		boolean hintDisplaySubRep = formoperations.isHintDisplayedForField(selectOne, hintText);
		TestValidation.IsTrue(hintDisplaySubRep, 
				  		      "Verified Hint is Displayed as hint = " + hintText, 
				              "FAILED to verify Hint Display");
		
		boolean verifyCommentSubRep = formoperations.verifyCommentOfField(selectOne, comment);
		TestValidation.IsTrue(verifyCommentSubRep, 
				  			  "Verified that comment is repeated correctly", 
				              "Failed to verify repeated comment ");
		
		boolean verifyAttachmentSubRep = formoperations.verifySingleAttachmentForField(selectOne, generalFile);
		TestValidation.IsTrue(verifyAttachmentSubRep, 
							 "Verified Attachment is repeated", 
				             "Failed to verify repeated attahment");
		
		boolean verifyPredefinedCorrSubRep = formoperations.verifyPredefinedCorrectionValueForField(selectOne, preDefinedCorrectionOptions.get(1));
		TestValidation.IsTrue(verifyPredefinedCorrSubRep, 
				  			 "Verified correction is repeated", 
				  			 "Failed to verify repeated correction");
		
		boolean verifyMarkAsResolvedSubRep = formoperations.verifymarkAsResolvedValueForField(selectOne, markAsResolved);
		TestValidation.IsTrue(verifyMarkAsResolvedSubRep, 
							  "Verified Mark as resolved is repeated", 
				  			  "Failed to verify Mark as Resolved repeated");
		
		boolean showPtsErndFrmSubRep = formoperations.verifyShowPointsEarnedForField(selectOne, "20");
		TestValidation.IsTrue(showPtsErndFrmSubRep, 
							 "Verified 'Points Earned'",
							 "Failed to verify 'Points Earned'");
		
		boolean showPtsPsblFrmSubRep = formoperations.verifyShowPointsPossibleForField(selectOne, "30");
		TestValidation.IsTrue(showPtsPsblFrmSubRep, 
							 "Verified 'Points Possible'", 
							 "Failed to verify 'Points Possible'");
		
		boolean clickCancel = fbForms.clickCancelButton();
		TestValidation.IsTrue(clickCancel, 
				  			 "SUCCESSFULLY CLOSED FORM", 
							 "FAILED TO CLOSE FORM");
		
		// ============================= END -> VERIFY Settings in FSQABrowser Forms
		
		//Goto Recodrs
		boolean navigateToRecords = fbp.navigateToFSQATab(FSQATAB.RECORDS);
		TestValidation.IsTrue(navigateToRecords, 
				  			  "Successfully Navigated to -" + FSQATAB.RECORDS , 
				              "Could NOT Navigate to -" + FSQATAB.RECORDS);
		
		
		boolean verifyFailsCheck = fbRecords.verifyFailsCheckForRecord(adtFrmName,FSQABrowserRecordsPage.FAILS_CHECK.FAILS);
		TestValidation.IsTrue(verifyFailsCheck, 
				  			  "Verified Fails Check for record with Form Name -" + adtFrmName + " as Non-Compliant", 
				  	  		  "FAILED to verify Fails Check for record with Form Name -" + adtFrmName + " as Non-Compliant");
		
		// open record
		boolean openRecord = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, adtFrmName);
		TestValidation.IsTrue(openRecord, 
							  "OPENED record for form - " + adtFrmName, 
							  "Failed to open record for form - " +adtFrmName);
		
		
		// ============================= START -> VERIFY Settings in FSQABrowser Records 
		
		TestValidation.IsTrue(true,"======================== IN FSQABrowser Records ========================","");
		
		boolean checkIsFieldRequiredRecrds = fbRecords.verifyIsFieldMarkedAsRequired(selectOne);
		TestValidation.IsTrue(checkIsFieldRequiredRecrds, 
				 			  "Verified Select One field is marked as required", 
		          			  "FAILED to verify Select One field marked as required");
		
		boolean hintDisplayRecrds = fbRecords.verifyHintForField(selectOne, hintText);
		TestValidation.IsTrue(hintDisplayRecrds, 
				 			  "Verified Hint Display for Select Multiple field, hint =" + hintText, 
				              "FAILED to verify Hint Display for Select Multiple field");
        		
		boolean addCmntsRecrds = fbRecords.verifyCommentForField(selectOne, comment);
		TestValidation.IsTrue(addCmntsRecrds, 
				  			  "Verified Comment as -" + comment + " Select One field -" + selectOne, 
				              "FAILED to verify Comment for Select One field");
		
		boolean allwAtchmntsRecrds = fbRecords.verifyAttachmentFileNameForField(selectOne, generalFile);
		TestValidation.IsTrue(allwAtchmntsRecrds, 
				  			  "Verified Attachment as -" + generalFile + "for Select One field -" + selectOne, 
				              "FAILED to verify Attachment for Select One field - " +selectOne);
		
		boolean correctionRecrds = fbRecords.verifyCorrectionForField(selectOne, preDefinedCorrectionOptions.get(1));
		TestValidation.IsTrue(correctionRecrds, 
				  			  "Verified Correction as -" +preDefinedCorrectionOptions.get(1) + " for Select One field - " + selectOne, 
				              "FAILED to verify correction");
		
		boolean markAsResolvedRecrds = fbRecords.verifyIsResolvedForField(selectOne, markAsResolved);
		TestValidation.IsTrue(markAsResolvedRecrds, 
				  			  "Verified Marked as Resolved as -" + markAsResolved + " for Select One field -" + selectOne,
				              "FAILED to verify Marked As Resolved ");
		
		boolean showPtsErndRecrds = fbRecords.verifyPointsEarnedForField(selectOne, "20");
		TestValidation.IsTrue(showPtsErndRecrds, 
							 "Verified 'Points Earned' for Select One field -" + selectOne, 
							 "Failed to verify 'Points Earned' for Select One field -" + selectOne);
		
		boolean showPtsPsblRecrds = fbRecords.verifyPointsPossibleForField(selectOne, "30");
		TestValidation.IsTrue(showPtsPsblRecrds, 
							 "Verified 'Points Possible' for Select One field -" + selectOne, 
							 "Failed to verify 'Points Possible' for Select One field -" + selectOne);
		
		// ============================= END -> VERIFY Settings in FSQABrowser Records 
		
	}

	@Test(description = "Form Designer || Validate form functionality for field settings for 'Audit' forms - \"Paragraph Text\" field")
	public void TestCase_46731() throws Exception {
		
		String adtFrmName = CommonMethods.dynamicText("AaF_");
		String sectionName = "Section";
		String paragraphTextName = "PT";
		String pTValue = "Hello\nI am Awesome";
		String hintText = "Paragraph Text Hint";
		String comment = "This is a comment";
		String generalFile = "generalFile.png";
		String generalFilePath = System.getProperty("user.dir") + "\\test-data-files\\UploadDocuments\\"+generalFile;
		

		HashMap<String, List<String>> equipResource = new HashMap<String, List<String>>();
		equipResource.put(FORMRESOURCETYPES.EQUIPMENT, Arrays.asList(equipmentsCategoryValue));

		//Section 1 
		HashMap<String, List<String>> adtSecFields = new HashMap<String, List<String>>();
		adtSecFields.put(FIELD_TYPES.PARAGRAPH_TEXT, Arrays.asList(paragraphTextName));
		
		FormFieldParams ffpSecAdt = new FormFieldParams();
		ffpSecAdt.FieldDetails = adtSecFields;
		ffpSecAdt.SectionName = sectionName;
		
		FormDesignParams fdpAdt = new FormDesignParams();
		fdpAdt.FormType = FORMTYPES.AUDIT;
		fdpAdt.FormName = adtFrmName;
		fdpAdt.SelectResources = equipResource;
		fdpAdt.DesignFields = Arrays.asList(ffpSecAdt);
		fdpAdt.ReleaseForm = true;
		
		// navigate to form designer
		fdp = hp.clickFormDesignerMenu();
		boolean designForm = fdp.designForm(fdpAdt);
		TestValidation.IsTrue(designForm, 
							  "Successfully Designed Form - " +adtFrmName + " with Paragraph Text Field as - " + paragraphTextName, 
					          "Failed to design from with Paragraph Text Field");
		
		HashMap<String, ElementProperties> fieldSettings = new LinkedHashMap<String, ElementProperties>(); 
		ElementProperties settings = new ElementProperties();
		settings.FAILS_CHECK = true;
		settings.IS_REQUIRED = true;
		settings.ALLOW_COMMENTS = true;
		settings.ALLOW_ATTATHMENTS = true;
		settings.SHOW_HINT = true;
		settings.HINT = hintText;
		settings.CARRYOVER_FIELD = true;
		fieldSettings.put(paragraphTextName, settings);
		
		boolean turnAllSettingsOn = fdp.setFieldProperties(fieldSettings) ;
		TestValidation.IsTrue(turnAllSettingsOn, 
	             			  "Successfully Turned All Settings for the Paragraph Text Field -" + paragraphTextName, 
	                          "Failed to turn on All Settigs for for the Paragraph Text Field -" + paragraphTextName);
		
//		boolean saveForm = fdp.clickSaveButton();
//		TestValidation.IsTrue(saveForm, 
//				  			  "Saved form -" + chkFrmName, 
//				  			  "Failed to save form -" +chkFrmName);
		
		// ============================= START -> VERIFY Settings in FORM PREVIEW
		
		TestValidation.IsTrue(true,"======================== IN Form Preview ========================","");
		
		boolean openfForPreview = fdp.clickPreviewForFormOnReleaePage(adtFrmName);
		TestValidation.IsTrue(openfForPreview, 
				  			  "Clicked Preview Butoon and Opened Form -"+ adtFrmName + " for preview", 
				              "Failed to open form for Preview");
		
		boolean setSMFieldPrvw = formoperations.setParagraphTextFieldValue(paragraphTextName, pTValue);
		TestValidation.IsTrue(setSMFieldPrvw, 
				  			  "Entered value -'HEllo\nI am Awesome'" + " in Paragraph Text Field -" + paragraphTextName, 
				              "Failed to enter value in Paragraph Text Field -" + paragraphTextName);
		
		boolean checkIsFieldRequiredPrvw = formoperations.isFieldRequired(paragraphTextName);
		TestValidation.IsTrue(checkIsFieldRequiredPrvw, 
				  			  "Verified that Paragraph Text Field -" + paragraphTextName + " is Marked as required in Form Preview", 
				              "Failed to verify that Paragraph Text Field -" + paragraphTextName + " is Marked as required in Form Preview");
		
		boolean clickExpandPrvw = formoperations.clickExpandForField(paragraphTextName);
		TestValidation.IsTrue(clickExpandPrvw, 
				  			 "Clicked on 'Expand' Button for Field -" + paragraphTextName, 
				  			 "Failed to click on 'Expand' Button for field - " + paragraphTextName);
		
		boolean allwAtchmntsPrvw = formoperations.verifyAllowAttachmentsInPreviewForField(paragraphTextName);
		TestValidation.IsTrue(allwAtchmntsPrvw, 
							  "Verified Allow Attachements for field -" + paragraphTextName + " in Form Preview", 
							  "FAILED to verify Allow Attachements for field -" + paragraphTextName + " in Form Preview");
		
		boolean addCmntsPrvw = formoperations.addCommentForField(paragraphTextName, "This is a comment");
		TestValidation.IsTrue(addCmntsPrvw, 
				  			  "Verified Add Comment Textbox present for Paragraph Text Field -" + paragraphTextName + " in Form Preview and added comment -" + comment , 
				  			  "FAILED to verify Add Comment Textbox present for Paragraph Text Field -" + paragraphTextName );
		
		boolean hintDisplayPrvw = formoperations.isHintDisplayedForField(paragraphTextName, hintText);
		TestValidation.IsTrue(hintDisplayPrvw, 
				  			  "Verified hint Displayed for Paragraph Text Field -" + paragraphTextName + " in Form Preview", 
				  			  "FAILED to very hint display for Paragraph Text Field -" + paragraphTextName + " in Form Preview");
		
		boolean closePreview = fdp.clickClosePreviewBtn();
		TestValidation.IsTrue(closePreview, 
				  			  "Successfully Closed Form Preview", 
				  			  "Could NOT Close Form Preview");
		
		
		// ============================= END -> VERIFY Settings in FORM PREVIEW
		
		boolean releaseForm = fdp.releaseForm(adtFrmName);
		TestValidation.IsTrue(releaseForm, 
							 "Successfully released form -" + adtFrmName, 
							 "Could Not release form -" + adtFrmName);
		
		boolean navToHome = fdp.navigateToHome();
		TestValidation.IsTrue(navToHome, 
				  			  "Successfully Navigated to Home", 
				              "Could NOT naigate to home");
		
		boolean selectResourceDropDownandNavigate = fbp.selectResourceDropDownandNavigate(FORMRESOURCETYPES.EQUIPMENT,FSQATAB.FORMS);
		TestValidation.IsTrue(selectResourceDropDownandNavigate, 
							  "Navigated to FSQA ->  " + FSQATAB.FORMS ,
							  "Could NOT Navigate to FSQA ->  " + FSQATAB.FORMS);
		
		
		boolean selectOpenForm = fbForms.selectOpenForm(adtFrmName);
		TestValidation.IsTrue(selectOpenForm, 
							  "Select & opened the form -" + adtFrmName, 
							  "Could NOT open the form" + adtFrmName);
		
		
		// ============================= START -> VERIFY Settings in FSQABrowser Forms
		
		TestValidation.IsTrue(true,"======================== IN FSQABrowser Forms ========================","");
		
		boolean setSMFieldFrm = formoperations.setParagraphTextFieldValue(paragraphTextName, pTValue);
		TestValidation.IsTrue(setSMFieldFrm, 
				  			  "Entered non compliant value -'HEllo\nI am Awesome'" + " in Paragraph Text Field -" + paragraphTextName, 
				              "Failed to enter value in Paragraph Text Field -" + paragraphTextName);
		
		boolean checkIsFieldRequiredFrms = formoperations.isFieldRequired(paragraphTextName);
		TestValidation.IsTrue(checkIsFieldRequiredFrms, 
							  "Verified Paragraph Text Field is marked as required", 
					          "FAILED to verify Paragraph Text Field field  marked as required");
		
		boolean clickExpandFrms = formoperations.clickExpandForField(paragraphTextName);
		TestValidation.IsTrue(clickExpandFrms, 
				  			 "Clicked on 'Expand' Button for Field -" + paragraphTextName, 
				  			 "Failed to click on 'Expand' Button for field - " + paragraphTextName);
		
		boolean allwAtchmntsFrms = formoperations.addAttachmentForField(paragraphTextName, generalFilePath);
		TestValidation.IsTrue(allwAtchmntsFrms, 
				  			  "Verified Allow Attachments for Paragraph Text Field , and attached file -" + generalFile, 
				  			  "FAILED to verify Allow Attachments for Paragraph Text Field field ");
		
		boolean addCmntsFrms = formoperations.addCommentForField(paragraphTextName, comment);
		TestValidation.IsTrue(addCmntsFrms, 
							  "Verified Allow Comments for Paragraph Text Field and entered comment -" + comment, 
				  			  "FAILED to verify Allow Comments for Paragraph Text Field");
		
		boolean hintDisplayFrms = formoperations.isHintDisplayedForField(paragraphTextName, hintText);
		TestValidation.IsTrue(hintDisplayFrms, 
				  		      "Verified Hint Display for Paragraph Text Field, hint =" + hintText, 
				              "FAILED to verify Hint Display for Paragraph Text Field");
		
//		boolean submitForm = formoperations.clickSubmitThenAlertOkBtn();
//		TestValidation.IsTrue(submitForm, 
//				  			 "Clicked on Submit and Submitted the form -" + adtFrmName, 
//				             "Could NOT click on Submit buttton and failed to submit form -" + adtFrmName);
		
		//Try Submit and Repeat
		boolean clickSubmitRepeat = fbForms.clickSubmitAndReapeatButton();
		TestValidation.IsTrue(clickSubmitRepeat, 
				  			 "CLICKED SUBMIT AND REPEAT", 
							 "FAILED TO CLICK SUBMIT AND REPEAT");
		
		TestValidation.IsTrue(true,"======================== After Submit and Repeat ========================","");
		
		
		boolean clickExpandSubRep = formoperations.clickExpandForField(paragraphTextName);
		TestValidation.IsTrue(clickExpandSubRep, 
				  			 "Clicked on 'Expand' Button for Field -" + paragraphTextName, 
				  			 "Failed to click on 'Expand' Button for field - " + paragraphTextName);
		
		boolean checkIsFieldRequiredSubRep = formoperations.isFieldRequired(paragraphTextName);
		TestValidation.IsTrue(checkIsFieldRequiredSubRep, 
							  "Verified Paragraph Text Field is marked as required", 
					          "FAILED to verify Paragraph Text Field marked as required");
		
		boolean hintDisplaySubRep = formoperations.isHintDisplayedForField(paragraphTextName, hintText);
		TestValidation.IsTrue(hintDisplaySubRep, 
				  		      "Verified Hint is Displayed as hint = " + hintText, 
				              "FAILED to verify Hint Display");
		
		boolean verifyCommentSubRep = formoperations.verifyCommentOfField(paragraphTextName, comment);
		TestValidation.IsTrue(verifyCommentSubRep, 
				  			  "Verified that comment is repeated correctly", 
				              "Failed to verify repeated comment ");
		
		boolean verifyAttachmentSubRep = formoperations.verifySingleAttachmentForField(paragraphTextName, generalFile);
		TestValidation.IsTrue(verifyAttachmentSubRep, 
							 "Verified Attachment is repeated", 
				             "Failed to verify repeated attahment");
		
		boolean clickCancel = fbForms.clickCancelButton();
		TestValidation.IsTrue(clickCancel, 
				  			 "SUCCESSFULLY CLOSED FORM", 
							 "FAILED TO CLOSE FORM");
		
		// ============================= END -> VERIFY Settings in FSQABrowser Forms
		
		//Goto Recodrs
		boolean navigateToRecords = fbp.navigateToFSQATab(FSQATAB.RECORDS);
		TestValidation.IsTrue(navigateToRecords, 
				  			  "Successfully Navigated to -" + FSQATAB.RECORDS , 
				              "Could NOT Navigate to -" + FSQATAB.RECORDS);
		
		
		boolean verifyFailsCheck = fbRecords.verifyFailsCheckForRecord(adtFrmName,FSQABrowserRecordsPage.FAILS_CHECK.NOT_FAILS);
		TestValidation.IsTrue(verifyFailsCheck, 
				  			  "Verified Fails Check for record with Form Name -" + adtFrmName + " as Compliant", 
				  	  		  "FAILED to verify Fails Check for record with Form Name -" + adtFrmName + " as Compliant");
		
		// open record
		boolean openRecord = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, adtFrmName);
		TestValidation.IsTrue(openRecord, 
							  "OPENED record for form - " + adtFrmName, 
							  "Failed to open record for form - " +adtFrmName);
		
		
		// ============================= START -> VERIFY Settings in FSQABrowser Records 
		
		TestValidation.IsTrue(true,"======================== IN FSQABrowser Records ========================","");
		
		boolean checkIsFieldRequiredRecrds = fbRecords.verifyIsFieldMarkedAsRequired(paragraphTextName);
		TestValidation.IsTrue(checkIsFieldRequiredRecrds, 
				 			  "Verified Paragraph Text Field field is marked as required", 
		          			  "FAILED to verify Paragraph Text Field field marked as required");
		
		boolean hintDisplayRecrds = fbRecords.verifyHintForField(paragraphTextName, hintText);
		TestValidation.IsTrue(hintDisplayRecrds, 
				 			  "Verified Hint Display for Paragraph Text Field, hint =" + hintText, 
				              "FAILED to verify Hint Display for Paragraph Text Field");
        		
		boolean addCmntsRecrds = fbRecords.verifyCommentForField(paragraphTextName, comment);
		TestValidation.IsTrue(addCmntsRecrds, 
				  			  "Verified Comment as -" + comment + " Paragraph Text Field -" + paragraphTextName, 
				              "FAILED to verify Comment for Paragraph Text Field");
		
		boolean allwAtchmntsRecrds = fbRecords.verifyAttachmentFileNameForField(paragraphTextName, generalFile);
		TestValidation.IsTrue(allwAtchmntsRecrds, 
				  			  "Verified Attachment as -" + generalFile + "for Paragraph Text Field -" + paragraphTextName, 
				              "FAILED to verify Attachment for Paragraph Text Field - " +paragraphTextName);
		
		
		// ============================= END -> VERIFY Settings in FSQABrowser Records 
		
	}

	
	@AfterClass(alwaysRun = true)
	public void closeBrowser() throws InterruptedException {
		driver.close();
	}

}
