package com.project.safetychain.webapp.testcases;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import com.project.safetychain.webapp.pageobjects.*;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.ELEMENT_TYPE;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FIELD_TYPES;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FORMRESOURCETYPES;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FORM_ELEMENTS;
import com.project.safetychain.webapp.pageobjects.ManageLocationPage.LocationInstanceParams;
import com.project.safetychain.webapp.pageobjects.ManageResourcePage.RESOURCETYPES;
import com.project.safetychain.webapp.pageobjects.ManageResourcePage.ResourceDetailParams;
import com.project.safetychain.webapp.pageobjects.ResourceDesignerPage.CATEGORYTYPES;
import com.project.safetychain.webapp.testcases.support.FormOperations;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.utilities.CommonMethods;
import com.project.utilities.ControlActions;

public class TCG_REG_FormDesigner_RepeatNCopyFlows extends TestBase{

	ControlActions controlActions;
	HomePage hp;
	UserManagerPage ump;
	FormDesignerPage fdp;
	LoginPage lp;
	ManageResourcePage manageResource;
	FormOperations formoperations;

	public static String locationCategoryValue, supplierCategoryValue;
	public static String locationInstanceValue, supplierInstanceValue;
	public static String chkFormName, adtFormName, qstFormName;
	
	@BeforeClass(alwaysRun = true)
	public void groupInit() throws InterruptedException {

		
		locationCategoryValue = CommonMethods.dynamicText("LocCat_");
		supplierCategoryValue = CommonMethods.dynamicText("SuppCat_");
		locationInstanceValue = CommonMethods.dynamicText("LocInst_");
		supplierInstanceValue = CommonMethods.dynamicText("SuppInst_");
		chkFormName = CommonMethods.dynamicText("CHK");
		adtFormName = CommonMethods.dynamicText("AuditForm_");
		qstFormName = CommonMethods.dynamicText("Qstn_");

		driver = launchbrowser();
		controlActions = new ControlActions(driver);
		controlActions.getUrl(applicationUrl);

		hp = new HomePage(driver);
		ump = new UserManagerPage(driver);
		fdp = new FormDesignerPage(driver);
		lp = new LoginPage(driver);
		manageResource = new ManageResourcePage(driver);
		formoperations = new FormOperations(driver);

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
		categories.put(CATEGORYTYPES.SUPPLIERS, supplierCategoryValue);
		ResourceDesignerPage rdp = hp.clickResourceDesignerMenu();
		if(!rdp.createCategory(categories)) {
			TCGFailureMsg = "Could NOT create Resource categories for - " + locationCategoryValue  + supplierCategoryValue;
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
		HashMap<String,Boolean> suppInstance = new HashMap<String, Boolean>();
		suppInstance.put(supplierInstanceValue, true);

		ResourceDetailParams rd2 = new ResourceDetailParams();
		rd2.CategoryName = supplierCategoryValue;
		rd2.CategoryType = RESOURCETYPES.SUPPLIERS;
		rd2.NumberFieldValue = 15;
		rd2.TextFieldValue = "LMN";
		rd2.InstanceNames = suppInstance;
		rd2.LocationInstanceValue = locationInstanceValue;	

		ManageResourcePage mrp = hp.clickResourcesMenu();
		if(!mrp.createResourceLinkLocation(rd2)) {
			TCGFailureMsg = "Could NOT create Supplier Instance for resource - " + supplierCategoryValue;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
		
		class Forms {
			String formName;
			WebElement formTypeElememt;
			
			Forms(String formName, WebElement formTypeElement){
				this.formName = formName;
				this.formTypeElememt = formTypeElement;
			}
		}
		
		Forms form1 = new Forms (chkFormName, fdp.SelectCheckFormLnk);
		Forms form2 = new Forms (qstFormName, fdp.SelectQuestionnaireFormLnk);
		Forms form3 = new Forms (adtFormName, fdp.SelectAuditFormLnk);
		
		
		List<Forms> formsToCreate = new ArrayList<Forms>();
		formsToCreate.add(form1);
		formsToCreate.add(form2);
		formsToCreate.add(form3);
		
		for (Forms form : formsToCreate) {
			//Open 'Form Designer'
			if(hp.clickFormDesignerMenu().error) {
				TCGFailureMsg = "Could NOT Open Form Designer";
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}

			controlActions.clickElement(form.formTypeElememt);
			
			//Selection of resource
			if(!fdp.selectResource(FORMRESOURCETYPES.SUPPLIERS,supplierCategoryValue,supplierInstanceValue)) {
				TCGFailureMsg = "Could NOT select resource";
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}
			
			Thread.sleep(2000);		
			//Go to 'Form Design'
			if(!fdp.clickOnNextButton(fdp.DesignFormPg,"Design Form")) {
				TCGFailureMsg = "Could NOT Navigate to 'Design Form'";
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}

			controlActions.sendKeys(fdp.FormNameTxt, form.formName);

			//Save the form
			if(!fdp.clickSaveButton()) {
				TCGFailureMsg = "Could NOT Save Form";
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}
		
	}

}

	
	// FIELDS in FG or QUES GP - REPEAT NOT SET =====================================================//
	
	@Test(description = " Questionnaire- Validate user is not able to set repeat property for field elements if repeat property is set for parent field/question group ")
	public void TestCase_38506() throws Exception {
		String groupName = "Repeated Group";
		String groupInnerFieldName = "Field inside group";

		boolean editFormDesign = fdp.navigateToReleaseForm_EditForm(qstFormName);
		TestValidation.IsTrue(editFormDesign, 
							  "OPENED form - '"+qstFormName+"' in edit mode", 
							  "Could NOT open form - '"+qstFormName+"' in edit mode"); 

		controlActions.dragdrop(fdp.GroupDbl, fdp.FormLevel);
		boolean setGroupName = fdp.setFieldName("Field Group",groupName);
		TestValidation.IsTrue(setGroupName, 
							 "Dragged and Dropped Field Group in form level - " + groupName,
							 "Failed to Drag and drop Field Group at form level " + groupName);

		boolean numericFieldInGroup = fdp.dragDropElementInSectionGroup(ELEMENT_TYPE.FIELD_TYPES,FIELD_TYPES.NUMERIC, groupName);
		TestValidation.IsTrue(numericFieldInGroup, 
						      "Added 'Numeric' field in 'Group'", 
							  "Could NOT add 'Numeric' field in 'Group'");
		
		boolean setNumericFieldName = fdp.setFieldName(FIELD_TYPES.NUMERIC,groupInnerFieldName);
		TestValidation.IsTrue(setNumericFieldName, 
							 "Setted 'Numeric' field name", 
							 "Could NOT set 'Numeric' field name");
		
		boolean setGroupRepeatCount = fdp.setRepeatCountForField(groupName,"2");
		TestValidation.IsTrue(setGroupRepeatCount, 
							 "Setted 'Group' repeat count", 
					 		 "Could NOT set 'Group' repeat count");

		boolean verifyNoRepeatStatus = fdp.verifyNoRepeatStatus(groupInnerFieldName);
		TestValidation.IsTrue(verifyNoRepeatStatus, 
							 "VERIFIED field's No- Repeat status", 
							 "Could NOT verify field's No- Repeat status");

		boolean exitFromFormDesigner = fdp.exitFormDesigner();
		TestValidation.IsTrue(exitFromFormDesigner, 
							 "EXITED from 'Form Designer'", 
							 "Could NOT exit from 'Form Designer'");
	}
	
	@Test(description = " Audit - Validate user is not able to set repeat property for field elements if repeat property is set for parent field/question group ")
	public void TestCase_38507() throws Exception {
		String sectionName = "Section Name";
		String groupName = "Repeated Group";
		String repatCount = "2";
		String groupInnerFieldName = "Field inside group";

		boolean editFormDesign = fdp.navigateToReleaseForm_EditForm(adtFormName);
		TestValidation.IsTrue(editFormDesign, 
							 "OPENED form - '"+adtFormName+"' in edit mode", 
							 "Could NOT open form - '"+adtFormName+"' in edit mode"); 

		WebElement SectionElement1 = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FORM_ELEMENT,"FORMELEMENT","Section");
		controlActions.dragdrop(SectionElement1, fdp.FieldTypeDropAreaFormLevel);
		
		boolean setSectionName = fdp.setFieldName("Section",sectionName);
		TestValidation.IsTrue(setSectionName, 
							  "Setted 'Section' name",  
							  "Could NOT set 'Section' name");
		
		boolean addQuesGroupInSection = fdp.dragDropElementInSectionGroup(ELEMENT_TYPE.FORM_ELEMENTS,FORM_ELEMENTS.QUESTION_GROUP, sectionName);
		TestValidation.IsTrue(addQuesGroupInSection, 
							  "Dragged and Dropped Question Group in Section -" + sectionName, 
							  "Could NOT Drag and Drop Question Group in Section -" + sectionName);

		boolean setQuesGroupName = fdp.setFieldName(FORM_ELEMENTS.QUESTION_GROUP,groupName);
		TestValidation.IsTrue(setQuesGroupName, 
				             "Setted Question Group name as -" + groupName, 
				             "Could NOT set  Question Group name");
		
//		controlActions.dragdrop(fdp.GroupDbl, fdp.FormLevel);
//		boolean setGroupName = fdp.setFieldName("Field Group",groupName);
//		TestValidation.IsTrue(setGroupName, 
//				"Setted 'Group' name", 
//				"Could NOT set 'Group' name");

		boolean setGroupRepeatCount = fdp.setRepeatCountForField(groupName,repatCount);
		TestValidation.IsTrue(setGroupRepeatCount, 
	               			  "Setted 'Ques Group' repeat count to -" + repatCount, 
	                          "Could NOT set 'Ques Group' repeat count to -" + repatCount);

		boolean numericFieldInGroup = fdp.dragDropElementInSectionGroup(ELEMENT_TYPE.FIELD_TYPES,FIELD_TYPES.NUMERIC, groupName);
		TestValidation.IsTrue(numericFieldInGroup, 
	              			  "Added 'Numeric' field in ' Question Group' -" + groupName, 
	                          "Could NOT add 'Numeric' field in 'Question Group' -" + groupName);
		
		boolean setNumericFieldName = fdp.setFieldName(FIELD_TYPES.NUMERIC,groupInnerFieldName);
		TestValidation.IsTrue(setNumericFieldName, 
				 			 "Setted 'Numeric' field name as- " + groupInnerFieldName, 
				             "Could NOT set 'Numeric' field name");

		boolean verifyNoRepeatStatus = fdp.verifyNoRepeatStatus(groupInnerFieldName);
		TestValidation.IsTrue(verifyNoRepeatStatus, 
							 "VERIFIED field's No- Repeat status", 
				             "Could NOT verify field's No- Repeat status");

		boolean exitFromFormDesigner = fdp.exitFormDesigner();
		TestValidation.IsTrue(exitFromFormDesigner, 
							  "EXITED from 'Form Designer'", 
					          "Could NOT exit from 'Form Designer'");

	}
	
	@Test(description = " Check - Validate user is not able to set repeat property for field elements if repeat property is set for parent field/question group ")
	public void TestCase_38317() throws Exception {
		String groupName = "Repeated Group";
		String groupInnerFieldName = "Field inside group";
		String repeatCount = "2";

		boolean editFormDesign = fdp.navigateToReleaseForm_EditForm(chkFormName);
		TestValidation.IsTrue(editFormDesign, 
							  "OPENED form - '"+chkFormName+"' in edit mode", 
							  "Could NOT open form - '"+chkFormName+"' in edit mode"); 

		controlActions.dragdrop(fdp.GroupDbl, fdp.FormLevel);
		boolean setGroupName = fdp.setFieldName("Field Group",groupName);
		TestValidation.IsTrue(setGroupName, 
							 "Dragged and Dropped Field Group in form level - " + groupName,
							 "Failed to Drag and drop Field Group at form level " + groupName);

		boolean numericFieldInGroup = fdp.dragDropElementInSectionGroup(ELEMENT_TYPE.FIELD_TYPES,FIELD_TYPES.NUMERIC, groupName);
		TestValidation.IsTrue(numericFieldInGroup, 
						      "Added 'Numeric' field in 'Group'", 
							  "Could NOT add 'Numeric' field in 'Group'");
		
		boolean setNumericFieldName = fdp.setFieldName(FIELD_TYPES.NUMERIC,groupInnerFieldName);
		TestValidation.IsTrue(setNumericFieldName, 
							 "Setted 'Numeric' field name -" + groupInnerFieldName, 
							 "Could NOT set 'Numeric' field name");
		
		boolean setGroupRepeatCount = fdp.setRepeatCountForField(groupName,repeatCount);
		TestValidation.IsTrue(setGroupRepeatCount, 
							 "Setted 'Group' repeat count to -" + repeatCount, 
					 		 "Could NOT set 'Group' repeat count");

		boolean verifyNoRepeatStatus = fdp.verifyNoRepeatStatus(groupInnerFieldName);
		TestValidation.IsTrue(verifyNoRepeatStatus, 
							 "VERIFIED field's No- Repeat status", 
							 "Could NOT verify field's No- Repeat status");

		boolean exitFromFormDesigner = fdp.exitFormDesigner();
		TestValidation.IsTrue(exitFromFormDesigner, 
							 "EXITED from 'Form Designer'", 
							 "Could NOT exit from 'Form Designer'");
		
	}
	//=================================== FIELDS in FG or QUES GP - REPEAT NOT SET 
	
	// FIELD GP and QUES GP - REPEAT NOT SET if there are fields inside with repeat property ========================//
	
	@Test(description = " Audit - Validate user is not able to set repeat property for field/question group if repeat property is set for element in that field/ question group  ")
	public void TestCase_38509() throws Exception {
		String sectionName = "Section Name";
		String groupName = "Repeated Group";
		String groupInnerFieldName = "Field inside group";

		try {
		boolean editFormDesign = fdp.navigateToReleaseForm_EditForm(adtFormName);
		TestValidation.IsTrue(editFormDesign, 
							 "OPENED form - '"+adtFormName+"' in edit mode", 
							 "Could NOT open form - '"+adtFormName+"' in edit mode"); 

		WebElement SectionElement1 = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FORM_ELEMENT,"FORMELEMENT","Section");
		controlActions.dragdrop(SectionElement1, fdp.FieldTypeDropAreaFormLevel);
		
		boolean setSectionName = fdp.setFieldName("Section",sectionName);
		TestValidation.IsTrue(setSectionName, 
							 "Setted 'Section' name", 
							 "Could NOT set 'Section' name");
		
		boolean addQuesGroupInSection = fdp.dragDropElementInSectionGroup(ELEMENT_TYPE.FORM_ELEMENTS,FORM_ELEMENTS.QUESTION_GROUP, sectionName);
		TestValidation.IsTrue(addQuesGroupInSection, 
							 "Added 'Numeric' field in 'Section'", 
							 "Could NOT add 'Numeric' field in 'Section'");

		boolean setQuesGroupName = fdp.setFieldName(FORM_ELEMENTS.QUESTION_GROUP,groupName);
		TestValidation.IsTrue(setQuesGroupName, 
							 "Setted 'Numeric' field name", 
							 "Could NOT set 'Numeric' field name");
		

		boolean numericFieldInGroup = fdp.dragDropElementInSectionGroup(ELEMENT_TYPE.FIELD_TYPES,FIELD_TYPES.NUMERIC, groupName);
		TestValidation.IsTrue(numericFieldInGroup, 
							  "Added 'Numeric' field in 'Group'", 
							  "Could NOT add 'Numeric' field in 'Group'");
		
		boolean setNumericFieldName = fdp.setFieldName(FIELD_TYPES.NUMERIC,groupInnerFieldName);
		TestValidation.IsTrue(setNumericFieldName, 
							 "Setted 'Numeric' field name", 
							 "Could NOT set 'Numeric' field name");
		
		boolean setFieldRepeatCount = fdp.setRepeatCountForField(groupInnerFieldName,"2");
		TestValidation.IsTrue(setFieldRepeatCount, 
							 "Setted 'Numeric Field' repeat count", 
							 "Could NOT set 'Numeric Field' repeat count");
		

		boolean verifyNoRepeatStatus = fdp.verifyNoRepeatStatus(groupName);
		TestValidation.IsTrue(verifyNoRepeatStatus, 
							 "VERIFIED field's No- Repeat status", 
							 "Could NOT verify field's No- Repeat status");

	}finally {
		boolean exitFromFormDesigner = fdp.exitFormDesigner();
		TestValidation.IsTrue(exitFromFormDesigner, 
							 "EXITED from 'Form Designer'", 
							 "Could NOT exit from 'Form Designer'");
		}
	}
	
	@Test(description = " Check - Validate user is not able to set repeat property for field/question group if repeat property is set for element in that field/ question group ")
	public void TestCase_38318() throws Exception {
		String groupName = "Repeated Group";
		String groupInnerFieldName = "Field inside group";

		try {
		boolean editFormDesign = fdp.navigateToReleaseForm_EditForm(chkFormName);
		TestValidation.IsTrue(editFormDesign, 
							  "OPENED form - '"+chkFormName+"' in edit mode", 
							  "Could NOT open form - '"+chkFormName+"' in edit mode"); 

		controlActions.dragdrop(fdp.GroupDbl, fdp.FormLevel);
		boolean setGroupName = fdp.setFieldName("Field Group",groupName);
		TestValidation.IsTrue(setGroupName, 
							 "Dragged and Dropped Field Group in form level - " + groupName,
							 "Failed to Drag and drop Field Group at form level " + groupName);

		boolean numericFieldInGroup = fdp.dragDropElementInSectionGroup(ELEMENT_TYPE.FIELD_TYPES,FIELD_TYPES.NUMERIC, groupName);
		TestValidation.IsTrue(numericFieldInGroup, 
						      "Added 'Numeric' field in 'Group'", 
							  "Could NOT add 'Numeric' field in 'Group'");
		
		boolean setNumericFieldName = fdp.setFieldName(FIELD_TYPES.NUMERIC,groupInnerFieldName);
		TestValidation.IsTrue(setNumericFieldName, 
							 "Setted 'Numeric' field name", 
							 "Could NOT set 'Numeric' field name");
		
		boolean setFieldRepeatCount = fdp.setRepeatCountForField(groupInnerFieldName,"2");
		TestValidation.IsTrue(setFieldRepeatCount, 
							 "Setted 'Numeric Field' repeat count", 
							 "Could NOT set 'Numeric Field' repeat count");
		
		boolean verifyNoRepeatStatus = fdp.verifyNoRepeatStatus(groupName);
		TestValidation.IsTrue(verifyNoRepeatStatus, 
							 "VERIFIED field's No- Repeat status", 
							 "Could NOT verify field's No- Repeat status");

	}finally {
		boolean exitFromFormDesigner = fdp.exitFormDesigner();
		TestValidation.IsTrue(exitFromFormDesigner, 
							 "EXITED from 'Form Designer'", 
							 "Could NOT exit from 'Form Designer'");
	}
	}
	
	@Test(description = " Questionnaire- Validate user is not able to set repeat property for field/question group if repeat property is set for element in that field/ question group ")
	public void TestCase_38508() throws Exception {
		String groupName = "Repeated Group";
		String groupInnerFieldName = "Field inside group";

		try {
		boolean editFormDesign = fdp.navigateToReleaseForm_EditForm(qstFormName);
		TestValidation.IsTrue(editFormDesign, 
							  "OPENED form - '"+qstFormName+"' in edit mode", 
							  "Could NOT open form - '"+qstFormName+"' in edit mode"); 

		controlActions.dragdrop(fdp.GroupDbl, fdp.FormLevel);
		boolean setGroupName = fdp.setFieldName("Field Group",groupName);
		TestValidation.IsTrue(setGroupName, 
							 "Dragged and Dropped Field Group in form level - " + groupName,
							 "Failed to Drag and drop Field Group at form level " + groupName);

		boolean numericFieldInGroup = fdp.dragDropElementInSectionGroup(ELEMENT_TYPE.FIELD_TYPES,FIELD_TYPES.NUMERIC, groupName);
		TestValidation.IsTrue(numericFieldInGroup, 
						      "Added 'Numeric' field in 'Group'", 
							  "Could NOT add 'Numeric' field in 'Group'");
		
		boolean setNumericFieldName = fdp.setFieldName(FIELD_TYPES.NUMERIC,groupInnerFieldName);
		TestValidation.IsTrue(setNumericFieldName, 
							 "Setted 'Numeric' field name", 
							 "Could NOT set 'Numeric' field name");
		
		boolean setFieldRepeatCount = fdp.setRepeatCountForField(groupInnerFieldName,"2");
		TestValidation.IsTrue(setFieldRepeatCount, 
							 "Setted 'Numeric Field' repeat count", 
							 "Could NOT set 'Numeric Field' repeat count");
		
		boolean verifyNoRepeatStatus = fdp.verifyNoRepeatStatus(groupName);
		TestValidation.IsTrue(verifyNoRepeatStatus, 
							 "VERIFIED field's No- Repeat status", 
							 "Could NOT verify field's No- Repeat status");
		}finally {
		boolean exitFromFormDesigner = fdp.exitFormDesigner();
		TestValidation.IsTrue(exitFromFormDesigner, 
							 "EXITED from 'Form Designer'", 
							 "Could NOT exit from 'Form Designer'");
		}
	}
	
	
//======================================================= END--FIELD GP and QUES GP - REPEAT NOT SET if there are fields inside with repeat property
	
	@Test(description = "Check form - Verify functionality of copy and delete icon present at field level in form design phase")
	public void TestCase_38320() throws Exception {
		
		try {
		String paragraphTextFieldName = "ParagraphText";
		String numericFieldName = "Numeric";
		String copynumericFieldName = "Numeric1";
		String note = "This is a note";
		
		// check form

		boolean editFormDesign = fdp.navigateToReleaseForm_EditForm(chkFormName);
		TestValidation.IsTrue(editFormDesign, 
				"OPENED form - '"+chkFormName+"' in edit mode", 
				"Could NOT open form - '"+chkFormName+"' in edit mode"); 
		
		// an element at header level
		// 3 different elements at form level
		
		boolean noteElement = fdp.addHeaderLevelFields(note, null, null, false);
		TestValidation.IsTrue(noteElement, 
							  "Successfully added note - " + note + " at header level " , 
							  "Could Not add note - " + note + " at header level " );
		
		
		WebElement ParagraphTextFieldType = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_TYPE,"FIELDTYPE",FIELD_TYPES.PARAGRAPH_TEXT);
		controlActions.dragdrop(ParagraphTextFieldType, fdp.FormLevel);
		boolean setParagraphText = fdp.setTextBoxValue(FIELD_TYPES.PARAGRAPH_TEXT, paragraphTextFieldName);		
		TestValidation.IsTrue(setParagraphText, 
							  "'Paragraph Text field' added successfully - " + paragraphTextFieldName, 
							   "Failed to Add Paragraph Text Field " + paragraphTextFieldName);
		
		WebElement NumericFieldType = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_TYPE,"FIELDTYPE",FIELD_TYPES.NUMERIC);
		controlActions.dragdrop(NumericFieldType, fdp.FormLevel);
		boolean setNumeric = fdp.setTextBoxValue(FIELD_TYPES.NUMERIC, numericFieldName);		
		TestValidation.IsTrue(setNumeric, 
							  "'Numeric field' added successfully - " + numericFieldName, 
							  "Failed to Add Numeric Field - " + numericFieldName);
		
		controlActions.actionEnter();
		
		// Verify - header level element when selected delete icon is displayed and 
		boolean deleteIconPresent_FElement = fdp.deleteIconPresent_HeaderElements("Note");
		TestValidation.IsTrue(deleteIconPresent_FElement, 
							  "Verified Delete Icon is dsiplayed for header element, when it is slected ", 
							  "Delete Icon is NOT displayed for header element, when it is slected " );
		
		
		// Verify - header level element when selected copy icon is not displayed
		boolean copyIconPresent_FElement = fdp.copyIconPresent_HeaderElements("Note");
		TestValidation.IsFalse(copyIconPresent_FElement, 
				  			  "Verified Copy Icon is dsiplayed for header element, when it is slected ", 
				  			  "Copy Icon is NOT displayed for header element, when it is slected ");		
		
		// Verify - all form level elements when selected have delete icon
		// Verify - all form level elements when selected have copy icon
		
		// Numeric Field
		boolean deleteIconPresent_FField1 = fdp.deleteIconPresent_FormLvlField(numericFieldName);
		boolean copyIconPresent_FField1 = fdp.copyIconPresent_FormLvlField(numericFieldName);
		TestValidation.IsTrue(deleteIconPresent_FField1 && copyIconPresent_FField1 , 
							  "Verified Delete and Copy both icons are displayed when Form level field is selected " + numericFieldName, 
							  "Delete and Copy icons are NOT displayed when Form level field is selected " + numericFieldName);
		
		
		// Paragraph Text
		boolean deleteIconPresent_FField2 = fdp.deleteIconPresent_FormLvlField(paragraphTextFieldName);
		boolean copyIconPresent_FField2 = fdp.copyIconPresent_FormLvlField(paragraphTextFieldName);
		TestValidation.IsTrue(deleteIconPresent_FField2 && copyIconPresent_FField2 , 
							  "Verified Delete and Copy both icons are displayed when Form level field is selected " + numericFieldName, 
							  "Delete and Copy icons are NOT displayed when Form level field is selected " + numericFieldName);
		
		//Verify - field should get copied when clicked on copy icon
		boolean copyField = fdp.copyField(numericFieldName);
		TestValidation.IsTrue(copyField, 
							  "Verified that, the field - " + numericFieldName + " gets copied when clicked on copy Icon",
							  "The field does NOT gets copied - " + numericFieldName + " when clicked on copy Icon");
		
		
		// Verify - field should get deleted when clicked on delete icon
		boolean deleteField = fdp.deleteField(copynumericFieldName);
		boolean deleteVerify = fdp.formLevelFieldPresent(copynumericFieldName);
		TestValidation.IsFalse(deleteVerify&&deleteField, 
							  "Verified that, the field - " + numericFieldName + " gets deleted when clicked on Delete Icon",
							  "The field is NOT deleted - " + numericFieldName + " when clicked on delete Icon");
		
	 }finally {
		fdp.navigateToHome();
		}
	}
	
	@Test(description = "Verify functionality of copy and delete icon present at field level in form design phase")
	public void TestCase_38512() throws Exception {
		
		try {
		String paragraphTextFieldName = "ParagraphText";
		String numericFieldName = "Numeric";
		String copynumericFieldName = "Numeric1";
		String note = "This is a note";
		

		boolean editFormDesign = fdp.navigateToReleaseForm_EditForm(qstFormName);
		TestValidation.IsTrue(editFormDesign, 
				"OPENED form - '"+qstFormName+"' in edit mode", 
				"Could NOT open form - '"+qstFormName+"' in edit mode"); 
		
		// an element at header level
		// 3 different elements at form level
		
		boolean noteElement = fdp.addHeaderLevelFields(note, null, null, false);
		TestValidation.IsTrue(noteElement, 
							  "Successfully added note - " + note + " at header level " , 
							  "Could Not add note - " + note + " at header level " );
		
		
		WebElement ParagraphTextFieldType = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_TYPE,"FIELDTYPE",FIELD_TYPES.PARAGRAPH_TEXT);
		controlActions.dragdrop(ParagraphTextFieldType, fdp.FormLevel);
		boolean setParagraphText = fdp.setElementValueTextArea(FIELD_TYPES.PARAGRAPH_TEXT, paragraphTextFieldName);		
		TestValidation.IsTrue(setParagraphText, 
							  "'Paragraph Text field' added successfully - " + paragraphTextFieldName, 
							   "Failed to Add Paragraph Text Field " + paragraphTextFieldName);
		
		WebElement NumericFieldType = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_TYPE,"FIELDTYPE",FIELD_TYPES.NUMERIC);
		controlActions.dragdrop(NumericFieldType, fdp.FormLevel);
		boolean setNumeric = fdp.setElementValueTextArea(FIELD_TYPES.NUMERIC, numericFieldName);		
		TestValidation.IsTrue(setNumeric, 
							  "'Numeric field' added successfully - " + numericFieldName, 
							  "Failed to Add Numeric Field - " + numericFieldName);
		
		controlActions.actionEnter();
		
		// Verify - header level element when selected delete icon is displayed and 
		boolean deleteIconPresent_FElement = fdp.deleteIconPresent_HeaderElements("Note");
		TestValidation.IsTrue(deleteIconPresent_FElement, 
							  "Verified Delete Icon is dsiplayed for header element, when it is slected ", 
							  "Delete Icon is NOT displayed for header element, when it is slected " );
		
		
		// Verify - header level element when selected copy icon is not displayed
		boolean copyIconPresent_FElement = fdp.copyIconPresent_HeaderElements("Note");
		TestValidation.IsFalse(copyIconPresent_FElement, 
				  			  "Verified Copy Icon is dsiplayed for header element, when it is slected ", 
				  			  "Copy Icon is NOT displayed for header element, when it is slected ");		
		
		// Verify - all form level elements when selected have delete icon
		// Verify - all form level elements when selected have copy icon
		
		// Numeric Field
		boolean deleteIconPresent_FField1 = fdp.deleteIconPresent_FormLvlField(numericFieldName);
		boolean copyIconPresent_FField1 = fdp.copyIconPresent_FormLvlField(numericFieldName);
		TestValidation.IsTrue(deleteIconPresent_FField1 && copyIconPresent_FField1 , 
							  "Verified Delete and Copy both icons are displayed when Form level field is selected " + numericFieldName, 
							  "Delete and Copy icons are NOT displayed when Form level field is selected " + numericFieldName);
		
		
		// Paragraph Text
		boolean deleteIconPresent_FField2 = fdp.deleteIconPresent_FormLvlField(paragraphTextFieldName);
		boolean copyIconPresent_FField2 = fdp.copyIconPresent_FormLvlField(paragraphTextFieldName);
		TestValidation.IsTrue(deleteIconPresent_FField2 && copyIconPresent_FField2 , 
							  "Verified Delete and Copy both icons are displayed when Form level field is selected " + numericFieldName, 
							  "Delete and Copy icons are NOT displayed when Form level field is selected " + numericFieldName);
		
		//Verify - field should get copied when clicked on copy icon
		boolean copyField = fdp.copyField(numericFieldName);
		TestValidation.IsTrue(copyField, 
							  "Verified that, the field - " + numericFieldName + " gets copied when clicked on copy Icon",
							  "The field does NOT gets copied - " + numericFieldName + " when clicked on copy Icon");
		
		
		// Verify - field should get deleted when clicked on delete icon
		boolean deleteField = fdp.deleteField(copynumericFieldName);
		boolean deleteVerify = fdp.formLevelFieldPresent(copynumericFieldName);
		TestValidation.IsFalse(deleteVerify&&deleteField, 
							  "Verified that, the field - " + numericFieldName + " gets deleted when clicked on Delete Icon",
							  "The field is NOT deleted - " + numericFieldName + " when clicked on delete Icon");
		
	 }finally {
		fdp.navigateToHome();
		}
		
	}
	
	@Test(description = "Audit form - Verify functionality of copy and delete icon present at field level in form design phase")
	public void TestCase_38513() throws Exception {
		
		try {
		String paragraphTextFieldName = "ParagraphText";
		String numericFieldName = "Numeric";
		String copynumericFieldName = "Numeric1";
		String note = "This is a note";
		String sectionField = "Section";
		
		// check form

		boolean editFormDesign = fdp.navigateToReleaseForm_EditForm(adtFormName);
		TestValidation.IsTrue(editFormDesign, 
				"OPENED form - '"+adtFormName+"' in edit mode", 
				"Could NOT open form - '"+adtFormName+"' in edit mode"); 
		
		// an element at header level
		// 3 different elements at form level
		
		boolean noteElement = fdp.addHeaderLevelFields(note, null, null, false);
		TestValidation.IsTrue(noteElement, 
							  "Successfully added note - " + note + " at header level " , 
							  "Could Not add note - " + note + " at header level " );
		
		
		WebElement SectionElement1 = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FORM_ELEMENT,"FORMELEMENT","Section");
		controlActions.dragdrop(SectionElement1, fdp.FieldTypeDropAreaFormLevel);
		boolean setSectionName = fdp.setFieldName("Section",sectionField);
		TestValidation.IsTrue(setSectionName, 
							  "Setted 'Section' name",  
							  "Could NOT set 'Section' name");
		
		boolean addParagraphTextInSection = fdp.dragDropElementInSectionGroup(ELEMENT_TYPE.FIELD_TYPES,FIELD_TYPES.PARAGRAPH_TEXT, sectionField);
		TestValidation.IsTrue(addParagraphTextInSection, 
							  "Added 'Paragraph Text' field in 'Section'", 
							  "Could NOT add 'Paragraph Text' field in 'Section'");

		boolean setParagraphTextName = fdp.setFieldName(FIELD_TYPES.PARAGRAPH_TEXT,paragraphTextFieldName);
		TestValidation.IsTrue(setParagraphTextName, 
							  "Setted 'Paragraph Text' field name ->" + paragraphTextFieldName, 
							  "Could NOT set 'Paragraph Text' field name -> " + paragraphTextFieldName);
		
		boolean addNumericFieldTextInSection = fdp.dragDropElementInSectionGroup(ELEMENT_TYPE.FIELD_TYPES,FIELD_TYPES.NUMERIC, sectionField);
		TestValidation.IsTrue(addNumericFieldTextInSection, 
							  "Added 'Numeric' field in 'Section'", 
							  "Could NOT add 'Numeric' field in 'Section'");

		boolean setNumericFieldName = fdp.setFieldName(FIELD_TYPES.NUMERIC,numericFieldName);
		TestValidation.IsTrue(setNumericFieldName, 
							  "Setted 'Numeric' field name ->" + numericFieldName, 
							  "Could NOT set 'Numeric' field name -> " + numericFieldName);
		
//		WebElement ParagraphTextFieldType = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_TYPE,"FIELDTYPE",FIELD_TYPES.PARAGRAPH_TEXT);
//		controlActions.dragdrop(ParagraphTextFieldType, fdp.FormLevel);
//		boolean setParagraphText = fdp.setTextBoxValue(FIELD_TYPES.PARAGRAPH_TEXT, paragraphTextFieldName);		
//		TestValidation.IsTrue(setParagraphText, 
//							  "'Paragraph Text field' added successfully - " + paragraphTextFieldName, 
//							   "Failed to Add Paragraph Text Field " + paragraphTextFieldName);
//		
//		WebElement NumericFieldType = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_TYPE,"FIELDTYPE",FIELD_TYPES.NUMERIC);
//		controlActions.dragdrop(NumericFieldType, fdp.FormLevel);
//		boolean setNumeric = fdp.setTextBoxValue(FIELD_TYPES.NUMERIC, numericFieldName);		
//		TestValidation.IsTrue(setNumeric, 
//							  "'Numeric field' added successfully - " + numericFieldName, 
//							  "Failed to Add Numeric Field - " + numericFieldName);
		
		controlActions.actionEnter();
		
		// Verify - header level element when selected delete icon is displayed and 
		boolean deleteIconPresent_FElement = fdp.deleteIconPresent_HeaderElements("Note");
		TestValidation.IsTrue(deleteIconPresent_FElement, 
							  "Verified Delete Icon is dsiplayed for header element, when it is slected ", 
							  "Delete Icon is NOT displayed for header element, when it is slected " );
		
		
		// Verify - header level element when selected copy icon is not displayed
		boolean copyIconPresent_FElement = fdp.copyIconPresent_HeaderElements("Note");
		TestValidation.IsFalse(copyIconPresent_FElement, 
				  			  "Verified Copy Icon is dsiplayed for header element, when it is slected ", 
				  			  "Copy Icon is NOT displayed for header element, when it is slected ");		
		
		// Verify - all form level elements when selected have delete icon
		// Verify - all form level elements when selected have copy icon
		
		// Numeric Field
		boolean deleteIconPresent_FField1 = fdp.deleteIconPresent_FormLvlField(numericFieldName);
		boolean copyIconPresent_FField1 = fdp.copyIconPresent_FormLvlField(numericFieldName);
		TestValidation.IsTrue(deleteIconPresent_FField1 && copyIconPresent_FField1 , 
							  "Verified Delete and Copy both icons are displayed when Form level field is selected " + numericFieldName, 
							  "Delete and Copy icons are NOT displayed when Form level field is selected " + numericFieldName);
		
		// Paragraph Text
		boolean deleteIconPresent_FField2 = fdp.deleteIconPresent_FormLvlField(paragraphTextFieldName);
		boolean copyIconPresent_FField2 = fdp.copyIconPresent_FormLvlField(paragraphTextFieldName);
		TestValidation.IsTrue(deleteIconPresent_FField2 && copyIconPresent_FField2 , 
							  "Verified Delete and Copy both icons are displayed when Form level field is selected " + numericFieldName, 
							  "Delete and Copy icons are NOT displayed when Form level field is selected " + numericFieldName);
		
		//Verify - field should get copied when clicked on copy icon
		boolean copyField = fdp.copyField(numericFieldName);
		TestValidation.IsTrue(copyField, 
							  "Verified that, the field - " + numericFieldName + " gets copied when clicked on copy Icon",
							  "The field does NOT gets copied - " + numericFieldName + " when clicked on copy Icon");
		
		
		// Verify - field should get deleted when clicked on delete icon
		boolean deleteField = fdp.deleteField(copynumericFieldName);
		boolean deleteVerify = fdp.formLevelFieldPresent(copynumericFieldName);
		TestValidation.IsFalse(deleteVerify&&deleteField, 
							  "Verified that, the field - " + numericFieldName + " gets deleted when clicked on Delete Icon",
							  "The field is NOT deleted - " + numericFieldName + " when clicked on delete Icon");
		
	 }finally {
		fdp.navigateToHome();
		}
		
		
		
	}
	
	@Test(description = " Form Designer can create a Copy of a Form from the Release Forms Panel ")
	public void TestCase_5130() throws Exception {
		
		boolean formReleasePage = fdp.navigateToReleaseForm();
		TestValidation.IsTrue(formReleasePage, 
							  "Navigated to Release form successfully", 
							  "Could NOT Navigate to Release form"); 
		
		boolean copyForm = fdp.copyForm(chkFormName);
		TestValidation.IsTrue(copyForm, 
							  "Verified The Form gets Copied" , 
							  "Could Not Verify Copyig of the form");
		
		
	}
	
	@Test(description = "Form Designer can edit Field Group \"Repeat\" number on General Tab whenever Field-Group is selected.")
	public void TestCase_5212() throws Exception {
		
		String groupName = "Agroup";
		String fieldName = "Afield";
		String repeatCount = "3";
		
		boolean editFormDesign = fdp.navigateToReleaseForm_EditForm(chkFormName);
		TestValidation.IsTrue(editFormDesign, 
							 "OPENED form - '"+chkFormName+"' in edit mode", 
							 "Could NOT open form - '"+chkFormName+"' in edit mode");
		
		controlActions.dragdrop(fdp.GroupDbl, fdp.FormLevel);
		boolean setGroupName = fdp.setFieldName("Field Group",groupName);
		TestValidation.IsTrue(setGroupName, 
							 "Dragged and Dropped Field Group in form level - " + groupName, 
							 "Failed to Drag and drop Field Group at form level " + groupName );
		
		boolean numericFieldInGroup = fdp.dragDropElementInSectionGroup(ELEMENT_TYPE.FIELD_TYPES,FIELD_TYPES.NUMERIC, groupName);
		TestValidation.IsTrue(numericFieldInGroup, 
							  "Added 'Numeric' field in 'Group'", 
							  "Could NOT add 'Numeric' field in 'Group'");
		
		boolean setNumericFieldName = fdp.setFieldName(FIELD_TYPES.NUMERIC, fieldName);
		TestValidation.IsTrue(setNumericFieldName, 
							 "Setted 'Numeric' field name" + fieldName, 
							 "Could NOT set 'Numeric' field name" + fieldName);
		
		controlActions.actionEnter();
		
		boolean setGroupRepeatCount = fdp.setRepeatCountForField(groupName,repeatCount);
		TestValidation.IsTrue(setGroupRepeatCount, 
							 "Setted 'Group' repeat count as " + repeatCount + "for group " + groupName, 
							 "Could NOT set 'Group' repeat count as " + repeatCount + "for group " + groupName);
		
		boolean verifyInPreviewForm = fdp.clickPreviewButton();
		TestValidation.IsTrue(verifyInPreviewForm, 
							 "Opened Form In Preview Mode", 
							 "Failed to open Form In Preview Mode");
		
		formoperations = new FormOperations(driver);
		
		boolean verifyGroupCount = formoperations.verifyRepeatCountForGroup(groupName, Integer.parseInt(repeatCount));
		boolean verifyFieldCount = formoperations.verifyRepeatCountForField(fieldName, Integer.parseInt(repeatCount));
		
		TestValidation.IsTrue(verifyGroupCount == verifyFieldCount, 
							 "Successfully verified repeat of Group and it's child elements", 
							 "Could NOT verify repeat of Group and it's child elements");
		
		
	}
	
	@AfterClass(alwaysRun = true)
	public void closeBrowser() throws InterruptedException {
		driver.close();
	}

}
