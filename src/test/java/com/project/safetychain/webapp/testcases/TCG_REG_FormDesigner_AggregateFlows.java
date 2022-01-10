package com.project.safetychain.webapp.testcases;

import java.util.ArrayList;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import com.project.safetychain.api.models.FormDesigner;
import com.project.safetychain.webapp.pageobjects.*;
import com.project.safetychain.webapp.pageobjects.CommonPage.TIMEZONE;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.COLUMNHEADER;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.COLUMNSETTING;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.FSQARESOURCE;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.FSQATAB;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.AGGREGATE_FUNCTION;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.ELEMENT_INPUT_TYPES;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.ELEMENT_TYPE;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.ElementProperties;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FIELD_INPUT_TYPES;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FIELD_SETTINGS;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FIELD_TYPES;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FORMRESOURCETYPES;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FORMTYPES;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FORM_ELEMENTS;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FormDesignParams;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FormFieldParams;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.IDENTIFIER_INPUT_TYPE;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.PROPERTIES_TABS;
import com.project.safetychain.webapp.pageobjects.ManageLocationPage.LocationInstanceParams;
import com.project.safetychain.webapp.pageobjects.ManageResourcePage.RESOURCETYPES;
import com.project.safetychain.webapp.pageobjects.ManageResourcePage.ResourceDetailParams;
import com.project.safetychain.webapp.pageobjects.ResourceDesignerPage.CATEGORYTYPES;
import com.project.safetychain.webapp.pageobjects.TaskSchedulerPage.TaskScheduleDetails;
import com.project.safetychain.webapp.pageobjects.UserManagerPage.UsrDetailParams;
import com.project.safetychain.webapp.testcases.support.FormOperations;
import com.project.safetychain.webapp.testcases.support.FormOperations.FormDetails;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.utilities.CommonMethods;
import com.project.utilities.ControlActions;

public class TCG_REG_FormDesigner_AggregateFlows extends TestBase{

	ControlActions controlActions;
	HomePage hp;
	UserManagerPage ump;
	WorkGroupsPage wgp;
	RolesManagerPage rmp;
	FormDesignerPage fdp;
	ManageRequirementPage mrp;
	SupplierPortalPage supplierPage;
	LoginPage lp;
	InboxPage inbox;
	DocumentManagementPage dmp;
	TaskScheduleDetails tsd;	
	TaskSchedulerPage taskScheduler;
	FSQABrowserPage fbp;
	FSQABrowserFormsPage fbForms;
	ManageResourcePage manageResource;
	FormOperations formoperations;

	public static String locationCategoryValue, itemCategoryValue, supplierCategoryValue;
	public static String locationInstanceValue, itemInstanceValue, supplierInstanceValue;
	public static String noteText, docTypeName;
	public static String chkFormName, adtFormName, qstFormName;
	public static String chkFreeTxt, ParaTxt, chkSelectOne, SelectMultiple, Numeric, chkDate, chkTime, chkDateTime;
	public static String chkAggregate, chkIdentifier, chkGroup1,chkGroup2, section1,section2;
	public static String fieldMin = "1", fieldTar = "2", fieldMax = "3", fieldUOM = "KG";
	public static String expressionThen = "Good", expressionElse = "Bad";
	public static String field4optionswithWeight[][] = {{"1","200"},{"2","400"}};

	//requirement type creation//
	public static String suppdocreqname,suppdocreqname1,suppformreqname,suppformreqname1;
	public static String itemdocreqname,itemdocreqname1,itemformreqname,itemformreqname1;
	public static String supptempname,itemtempname,ackreqname;
	
	//MY WORK
	List<String> FormLvlNumFields = new ArrayList<String>();
	List<String> Group1LvlNumFields = new ArrayList<String>();
	List<String> Group2LvlNumFields = new ArrayList<String>();
	List<String> Section1LvlNumFields = new ArrayList<String>();
	List<String> Section2LvlNumFields = new ArrayList<String>();
	
	List<String> ActualAggFunc = Arrays.asList("Average","Sum","% Pass","% Fail","Count Pass","Count Fail","Standard Deviation","Min Value","Max Value","Median","Range","Count Range","Sum Range");
	
	public static String FormLvlAggField = "FormAgg";
	public static String Group1LvlAggField = "Group1Agg";
	public static String Group2LvlAggField = "Group2Agg";
	public static String Section1LvlAggField = "Section1Agg";
	public static String Section2LvlAggField = "Section2Agg";
	
	public static String srcError = "Selected Source is not set";
	public static String functionError = "Selected Function is not set";
	
	@BeforeClass(alwaysRun = true)
	public void groupInit() throws InterruptedException {

		FormLvlNumFields.add("FNum");
		FormLvlNumFields.add("FNumAgg1");
		
		Group1LvlNumFields.add("FG1Num");
		Group1LvlNumFields.add("FG1NumAgg1");
		Group1LvlNumFields.add("FG1NumAgg2");
		
		Group2LvlNumFields.add("FG2Num");
		Group2LvlNumFields.add("FG2NumAgg");
		
		Section1LvlNumFields.add("S1Num");
		Section1LvlNumFields.add("Weight"); // for aggregate calculation
		
		Section2LvlNumFields.add("S2Num");
		Section2LvlNumFields.add("S2NumAgg");
		
		
		locationCategoryValue = CommonMethods.dynamicText("LocCat_");
		supplierCategoryValue = CommonMethods.dynamicText("SuppCat_");//"SuppCat__06.06_15.42.07";////CommonMethods.dynamicText("SuppCat_");test1.stage->
		locationInstanceValue = CommonMethods.dynamicText("LocInst_");// "LocInst__01.06_23.35.14";/
		supplierInstanceValue = CommonMethods.dynamicText("SuppInst_");//"SuppInst__06.06_15.42.07";////test1.stage->
		chkFormName = CommonMethods.dynamicText("CHK");//"CHK_17.05_10.31.50";//test1.stage->
		adtFormName = CommonMethods.dynamicText("AuditForm_");
		qstFormName = CommonMethods.dynamicText("Qstn_");
		chkFreeTxt = "FreeText";
		ParaTxt = "Paragraph";
		chkSelectOne = "SelectOne";
		Numeric = "Numeric";
		chkDate = "Date";
		chkTime = "Time";
		chkDateTime = "Date&Time";
		chkAggregate = "Aggregate";
		chkIdentifier = "Identifier";
		chkGroup1 = "Group1";
		chkGroup2 = "Group2";
		section1 = "Section1";
		section2 = "Section2";
		SelectMultiple = "MutiSelect";

		driver = launchbrowser();
		controlActions = new ControlActions(driver);
		controlActions.getUrl(applicationUrl);

		hp = new HomePage(driver);
		ump = new UserManagerPage(driver);
		wgp = new WorkGroupsPage(driver);
		rmp = new RolesManagerPage(driver);
		fdp = new FormDesignerPage(driver);
		dmp = new DocumentManagementPage(driver);
		mrp = new ManageRequirementPage(driver);
		inbox = new InboxPage(driver);
		taskScheduler = new TaskSchedulerPage(driver);
		fbp = new FSQABrowserPage(driver);
		tsd = new TaskScheduleDetails();
		fbForms = new FSQABrowserFormsPage(driver);
		supplierPage = new SupplierPortalPage(driver);
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
			TCGFailureMsg = "Could NOT create Resource categories for - " + locationCategoryValue + " "
					+ itemCategoryValue + " " + supplierCategoryValue;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

//		//Location instance
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
		
		//Create Check Form
		fdp = hp.clickFormDesignerMenu();
		if(fdp.error) {
			TCGFailureMsg = "Could NOT navigate to Admin Tools - Form Designer";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		// Form Level
		HashMap<String, List<String>> chkFields = new HashMap<String, List<String>>();
		chkFields.put(FIELD_TYPES.NUMERIC, FormLvlNumFields);
		chkFields.put(FIELD_TYPES.AGGREGATE, Arrays.asList(FormLvlAggField));


		FormFieldParams ffpChk = new FormFieldParams();
		ffpChk.FieldDetails = chkFields;
		ffpChk.AgrregateFunction = AGGREGATE_FUNCTION.SUM;
		ffpChk.AgrregateSource = FormLvlNumFields.get(1);
		ffpChk.SetAggregateFunction = true;
		ffpChk.Repeat = Arrays.asList(Arrays.asList(FormLvlNumFields.get(1),"3"));

		// Field Group1 Level
		HashMap<String, List<String>> chkGrp1Fields = new HashMap<String, List<String>>();
		chkGrp1Fields.put(FIELD_TYPES.NUMERIC, Group1LvlNumFields);
		chkGrp1Fields.put(FIELD_TYPES.AGGREGATE, Arrays.asList(Group1LvlAggField));

		FormFieldParams ffpGrp1Chk = new FormFieldParams();
		ffpGrp1Chk.FieldDetails = chkGrp1Fields;
		ffpGrp1Chk.GroupName = chkGroup1;
		ffpGrp1Chk.AgrregateFunction = AGGREGATE_FUNCTION.SUM;
		ffpGrp1Chk.AgrregateSource = Group1LvlNumFields.get(1);
		ffpGrp1Chk.SetAggregateFunction = true;
		ffpGrp1Chk.Repeat = Arrays.asList(Arrays.asList(Group1LvlNumFields.get(1),"3"));
		ffpGrp1Chk.Copy = Arrays.asList(Group1LvlNumFields.get(2));
		
		//Section 1 
		HashMap<String, List<String>> chkSec1Fields = new HashMap<String, List<String>>();
		chkSec1Fields.put(FIELD_TYPES.NUMERIC, Section1LvlNumFields);
		chkSec1Fields.put(FIELD_TYPES.AGGREGATE, Arrays.asList(Section1LvlAggField));
		
		FormFieldParams ffpSec1Chk = new FormFieldParams();
		ffpSec1Chk.FieldDetails = chkSec1Fields;
		ffpSec1Chk.SectionName = section1;
		ffpSec1Chk.AgrregateFunction = AGGREGATE_FUNCTION.SUM;
		ffpSec1Chk.AgrregateSource = Section1LvlNumFields.get(1);
		ffpSec1Chk.SetAggregateFunction = true;
		ffpSec1Chk.Repeat = Arrays.asList(Arrays.asList(Section1LvlNumFields.get(1),"3"));
		
//		// Fields for Section
////
////		HashMap<String, List<String>> chkGrp2Fields = new HashMap<String, List<String>>();
////		chkGrp2Fields.put(FIELD_TYPES.NUMERIC, Group2LvlNumFields);
////		chkGrp2Fields.put(FIELD_TYPES.AGGREGATE, Arrays.asList(Group2LvlAggField));
////
////		FormFieldParams sec1Fields = new FormFieldParams();
////		sec1Fields.DragToSectionName = section1;
////		sec1Fields.FieldDetails = chkSec1Fields;
////		sec1Fields.AgrregateFunction = AGGREGATE_FUNCTION.SUM;
////		sec1Fields.AgrregateSource = Section1LvlNumFields.get(1);
////		ffpGrp2Chk.Repeat = Arrays.asList(Arrays.asList(Group2LvlNumFields.get(1),"3"));
//
//		// Section 2 Level
		HashMap<String, List<String>> chkSec2Fields = new HashMap<String, List<String>>();
		chkSec2Fields.put(FIELD_TYPES.NUMERIC, Section2LvlNumFields);
		chkSec2Fields.put(FIELD_TYPES.AGGREGATE, Arrays.asList(Section2LvlAggField));
		
		FormFieldParams ffpSec2Chk = new FormFieldParams();
		ffpSec2Chk.FieldDetails = chkSec2Fields;
		ffpSec2Chk.SectionName = section2;
		ffpSec2Chk.AgrregateFunction = AGGREGATE_FUNCTION.SUM;
		ffpSec2Chk.AgrregateSource = Section2LvlNumFields.get(1);
		ffpSec2Chk.SetAggregateFunction = true;
		ffpSec2Chk.Repeat = Arrays.asList(Arrays.asList(Section2LvlNumFields.get(1),"3"));
		
		HashMap<String, List<String>> suppResource = new HashMap<String, List<String>>();
		suppResource.put(FORMRESOURCETYPES.SUPPLIERS, Arrays.asList(supplierCategoryValue));

		FormDesignParams fdpChk = new FormDesignParams();
		fdpChk.FormType = FORMTYPES.CHECK;
		fdpChk.FormName = chkFormName;
		fdpChk.SelectResources = suppResource;
		fdpChk.DesignFields = Arrays.asList(ffpChk,ffpGrp1Chk,ffpSec1Chk,ffpSec2Chk);//Arrays.asList(ffpGrp1Chk);//
		fdpChk.ReleaseForm = true;
		
		fdp.designForm(fdpChk);

////		fdp.setRepeatCountForField(FormLvlNumFields.get(1),"2"); DONE
//		
////		fdp.setRepeatCountForField(Group1LvlNumFields.get(1),"2"); DONE
////		fdp.copyElement(Group1LvlNumFields.get(2)); DONE
//		
////		fdp.setRepeatCountForField(SectionLvlNumFields.get(1),"2"); DONE
		
		
		//Add Field in Group in Section

		boolean dragDropFieldGroupElement = fdp.dragDropElementInSectionGroup(ELEMENT_TYPE.FORM_ELEMENTS,FORM_ELEMENTS.FIELD_GROUP, section1);
		TestValidation.IsTrue(dragDropFieldGroupElement, 
						"Drag-Drop Field Group in Section successfully", 
						"Failed to Drag-Drop Field Group in Section");
		
		fdp.setNameChkFrmElements(ELEMENT_INPUT_TYPES.FIELD_GROUP, chkGroup2);

		boolean dragDropG2NumField = fdp.dragDropElementInSectionGroup(ELEMENT_TYPE.FIELD_TYPES, FIELD_TYPES.NUMERIC, chkGroup2);
		TestValidation.IsTrue(dragDropG2NumField, 
						"Drag-Drop Numeric field in Field Group successfully", 
						"Failed to Drag-Drop Numeric field in Field Group");

		fdp.setNameChkFrmElements(FIELD_INPUT_TYPES.NUMERIC, Group2LvlNumFields.get(0));
		
		boolean dragDropG2NumAggField = fdp.dragDropElementInSectionGroup(ELEMENT_TYPE.FIELD_TYPES, FIELD_TYPES.NUMERIC, chkGroup2);
		TestValidation.IsTrue(dragDropG2NumAggField, 
						"Drag-Drop Numeric field in Field Group successfully", 
						"Failed to Drag-Drop Numeric field in Field Group");

		fdp.setNameChkFrmElements(FIELD_INPUT_TYPES.NUMERIC, Group2LvlNumFields.get(1));
		
		boolean dragDropG2AggField = fdp.dragDropElementInSectionGroup(ELEMENT_TYPE.FORM_ELEMENTS, FORM_ELEMENTS.AGGREGATE, chkGroup2);
		TestValidation.IsTrue(dragDropG2AggField, 
						"Drag-Drop Numeric field in Field Group successfully", 
						"Failed to Drag-Drop Numeric field in Field Group");
			
		fdp.setNameChkFrmElements(FIELD_INPUT_TYPES.AGGREGATE, Group2LvlAggField);
		fdp.setAggregateFunction("Sum");
		fdp.setRepeatCountForField(Group2LvlNumFields.get(1),"2");

		
		fdp.setAggregateSource(FormLvlAggField,FormLvlNumFields.get(1));
		fdp.setAggregateSource(Group1LvlAggField, Group1LvlNumFields.get(1));
		fdp.setAggregateSource(Group2LvlAggField, Group2LvlNumFields.get(1));
		fdp.setAggregateSource(Section1LvlAggField,Section1LvlNumFields.get(1));
		fdp.setAggregateSource(Section2LvlAggField,Section2LvlNumFields.get(1));
		
		fdp.selectField(Section2LvlNumFields.get(1));
		
		if(!fdp.clickSaveButton()) {
			TCGFailureMsg = "Could NOT Save Form";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
	
	}

	@Test(description=": Form Designer - Aggregate Field \"Source\" drop-down at the Field Group level", priority = 1)
	public void TestCase_7753() throws Exception{
//		
////		hp.clickHomepageLink();
////		boolean navigate = fbp.selectResourceDropDownandNavigate("SUPPLIER","Forms");
////		TestValidation.IsTrue(navigate, 
////				"Navigated to FSQABrowser>FormsTab", 
////				"Failed to navigate to FSQABrowser>Forms Tab");
////
////		boolean applyfilter = fbp.openAndApplySettingsForColumn(COLUMNHEADER.FORMNAME, COLUMNSETTING.FILTER,chkFormName);
////		TestValidation.IsTrue(applyfilter, 
////				"Applied Filter on" + COLUMNHEADER.FORMNAME, 
////				"Failed to apply filter on" + COLUMNHEADER.FORMNAME);
////		
////		boolean editform = fbp.editForm(chkFormName,fdp);
////		TestValidation.IsTrue(editform, 
////				"Form Edited", 
////				"Failed to edit form");
		
		boolean editFormDesign = fdp.navigateToReleaseForm_EditForm(chkFormName);
		TestValidation.IsTrue(editFormDesign, 
				"OPENED form - '"+chkFormName+"' in edit mode", 
				"Could NOT open form - '"+chkFormName+"' in edit mode"); 
		
		List<String> AggSourcesGroup1Level ;
		
		AggSourcesGroup1Level  = fdp.getAggDrpDwnSources(Group1LvlAggField);
		
		//boolean sameGPRepeatField = fdp.repeatedFieldPresentAsAggSrcOrNot(Group1LvlNumFields.get(1), Group1LvlAggField);
		boolean sameGPRepeatField = AggSourcesGroup1Level.contains(Group1LvlNumFields.get(1));
		TestValidation.IsTrue(sameGPRepeatField, 
							"Verified  same group's - "+ chkGroup1 +" - REPEAT field - " + Group1LvlNumFields.get(1) + " - as same Group Aggregate field - "+Group1LvlAggField+" - source", 
							"Could Not Verify  group's - "+ chkGroup1 +" - Repeat field - " + Group1LvlNumFields.get(1) + " - as same Group Aggregate field - "+Group1LvlAggField+" - source");

		//boolean sameGPCopiedField = fdp.repeatedFieldPresentAsAggSrcOrNot(Group1LvlNumFields.get(2), Group1LvlAggField);
		boolean sameGPCopiedField = AggSourcesGroup1Level.contains(Group1LvlNumFields.get(2));
		TestValidation.IsTrue(sameGPCopiedField, 
				"Verified  same group's - "+ chkGroup1 +" - COPY field - " + Group1LvlNumFields.get(2) + " - as same Group Aggregate field - "+Group1LvlAggField+" - source", 
				"Could Not Verify  group's - "+ chkGroup1 +" - COPY field - " + Group1LvlNumFields.get(2) + " - as same Group Aggregate field - "+Group1LvlAggField+" - source");
		
		//boolean sameGPNonRepeatField = fdp.repeatedFieldPresentAsAggSrcOrNot(Group1LvlNumFields.get(0), Group1LvlAggField);
		TestValidation.IsFalse(AggSourcesGroup1Level.contains(Group1LvlNumFields.get(0)), 
				"Verified same group's - "+ chkGroup1 +" - NON REPEAT field - " + Group1LvlNumFields.get(0) + " - NOT Prsent as same Group Aggregate field - "+Group1LvlAggField+" - source", 
				"Could Not Verify  group's - "+ chkGroup1 +" - NON REPEAT field - " + Group1LvlNumFields.get(0) + " Prsent as same Group Aggregate field - "+Group1LvlAggField+" - source");

		
		//boolean differentGPRepeatField = fdp.repeatedFieldPresentAsAggSrcOrNot(Group2LvlNumFields.get(1), Group1LvlAggField);
		TestValidation.IsFalse(AggSourcesGroup1Level.contains(Group2LvlNumFields.get(1)), 
				"Verified  different group's - "+ chkGroup2 +" - REPEAT field - " + Group2LvlNumFields.get(1) + " - NOT Prsent as Group's -> "+chkGroup1+"  Aggregate field - "+Group1LvlAggField+" - source", 
				"Could Not Verify  group's - "+ chkGroup2 +" - REPEAT field - " + Group2LvlNumFields.get(1) + " Prsent as Group's -> "+chkGroup1+"  Aggregate field - "+Group1LvlAggField+" - source");
		
		
		//boolean sectionRepeatField = fdp.repeatedFieldPresentAsAggSrcOrNot(Section1LvlNumFields.get(1), Group1LvlAggField);
		TestValidation.IsFalse(AggSourcesGroup1Level.contains(Section1LvlNumFields.get(1)), 
				"Verified  Section's -> "+ section1 +" - REPEAT field -> " + Section1LvlNumFields.get(1) + " - NOT Prsent as Group's -> "+chkGroup1+" - Aggregate field - "+Group1LvlAggField+" - source", 
				"Could Not Verify  Section's -> "+ section1 +" - REPEAT field -> " + Section1LvlNumFields.get(1) + " Prsent as Group's -> "+chkGroup1+" - Aggregate field - "+Group1LvlAggField+" - source");
		
		
		//boolean formRepeatField = fdp.repeatedFieldPresentAsAggSrcOrNot(FormLvlNumFields.get(1), Group1LvlAggField);
		TestValidation.IsFalse(AggSourcesGroup1Level.contains(FormLvlNumFields.get(1)), 
				"Verified  Form's  -> "+ chkFormName +" - REPEAT field -> " + Section1LvlNumFields.get(1) + " - NOT Prsent as Group's ->  "+chkGroup1+" - Aggregate field - "+Group1LvlAggField+" - source", 
				"Could Not Verify  Form's -> "+ chkFormName +" - REPEAT field -> " + Section1LvlNumFields.get(1) + " Prsent as Group's -> "+chkGroup1+" - Aggregate field - "+Group1LvlAggField+" - source");
		
		
		TestValidation.IsTrue((sameGPRepeatField && sameGPCopiedField ), 
								"Verified that the Aggregate dropdown of - " + Group1LvlAggField + "show the long names of the fields, not the Short name. ", 
								"Could Not Verify that the Aggregate dropdown of - " + Group1LvlAggField + "show the long names of the fields, not the Short name. ");
		
		boolean verifyUniqueAggSrc = fdp.uniqueAggSource(Group1LvlAggField);
		TestValidation.IsTrue(verifyUniqueAggSrc, 
							"Verified All the displayed source field options are unique", 
							"Failed to verify All the displayed source field options are unique");
		
		boolean FG1vsFG2 = fdp.compareDiffAggSrcs(Group2LvlAggField, Group1LvlAggField);
		TestValidation.IsFalse(FG1vsFG2, 
							"Verified that both field group's Aggregate field 1. " + Group1LvlAggField + "and 2. " + Group2LvlAggField + "are not same", 
							"Failed to verify that both field group's Aggregate field 1. " + Group1LvlAggField + "and 2. " + Group2LvlAggField + "are not same");
		
		boolean FG1vsSection = fdp.compareDiffAggSrcs(Section1LvlAggField, Group1LvlAggField);
		TestValidation.IsFalse(FG1vsSection, 
						 	"Verified section Aggregate field " + Section1LvlAggField + "and Field Group Aggregate field " + Group2LvlAggField + "do not have same source fields", 
							"Failed To verify section Aggregate field " + Section1LvlAggField + "and Field Group Aggregate field " + Group2LvlAggField + "do not have same source fields");

	}
	
	@Test(description=" Form Designer - Aggregate Field \"Source\" drop-down at the Form level  ", priority = 2)
	public void TestCase_7755() throws Exception{
		List<String> AggSourceFormlLvl ;
//		List<String> AggSourceGroup1Lvl ;
//		List<String> AggSourceGroup2Lvl ;
//		List<String> AggSourceSec1Lvl ;
//		List<String> AggSourceSec2Lvl ;
		
		
		boolean editFormDesign = fdp.navigateToReleaseForm_EditForm(chkFormName);
		TestValidation.IsTrue(editFormDesign, 
				"OPENED form - '"+chkFormName+"' in edit mode", 
				"Could NOT open form - '"+chkFormName+"' in edit mode"); 
		
		AggSourceFormlLvl = fdp.getAggDrpDwnSources(FormLvlAggField);
//		AggSourceGroup1Lvl = fdp.getAggDrpDwnSources(Group1LvlAggField);
//		AggSourceGroup2Lvl = fdp.getAggDrpDwnSources(Group2LvlAggField);
//		AggSourceSec1Lvl = fdp.getAggDrpDwnSources(Section1LvlAggField);
//		AggSourceSec2Lvl = fdp.getAggDrpDwnSources(Section2LvlAggField);
//		
		// Form level repeating numeric 
		TestValidation.IsTrue(AggSourceFormlLvl.contains(FormLvlNumFields.get(1)), 
							"Verified  form level repeated numeric field - "+ FormLvlNumFields.get(1) +" - present as Aggregate source for form lvl Aggregaate Field - " + FormLvlAggField, 
							"Could Not Verify  form level repeated numeric field - "+ FormLvlNumFields.get(1) +" - present as Aggregate source for form lvl Aggregaate Field - " + FormLvlAggField);
		
		TestValidation.IsTrue(AggSourceFormlLvl.contains(Group2LvlNumFields.get(1)), 
							  "Verified  group level repeated numeric field - "+ Group2LvlNumFields.get(1) +" - present as Aggregate source for form lvl Aggregaate Field - " + FormLvlAggField, 
							  "Could Not Verify  group level repeated numeric field - "+ Group2LvlNumFields.get(1) +" - present as Aggregate source for form lvl Aggregaate Field - " + FormLvlAggField);
		
		TestValidation.IsTrue(AggSourceFormlLvl.contains(Section2LvlNumFields.get(1)), 
							 "Verified  section level repeated numeric field - "+ Section2LvlNumFields.get(1) +" - present as Aggregate source for form lvl Aggregaate Field - " + FormLvlAggField, 
							 "Could Not Verify  section level repeated numeric field - "+ Section2LvlNumFields.get(1) +" - present as Aggregate source for form lvl Aggregaate Field - " + FormLvlAggField);			

		TestValidation.IsTrue(AggSourceFormlLvl.contains(Group1LvlNumFields.get(1)), 
							 "Verified  group nested in section level repeated numeric field - "+ Group1LvlNumFields.get(1) +" - present as Aggregate source for form lvl Aggregaate Field - " + FormLvlAggField, 
							 "Could Not Verify  group nested in section level repeated numeric field - "+ Group1LvlNumFields.get(1) +" - present as Aggregate source for form lvl Aggregaate Field - " + FormLvlAggField);
		
		List<String> allRepeatedFormFieldsLongNames = Arrays.asList(FormLvlNumFields.get(1),Section1LvlNumFields.get(1),Section2LvlNumFields.get(1),Group1LvlNumFields.get(1),Group2LvlNumFields.get(1),Group1LvlNumFields.get(2));
		
		TestValidation.IsTrue(AggSourceFormlLvl.containsAll(allRepeatedFormFieldsLongNames), 
							 "Verified All the source options for form level Aggregate field are Long Names of Field ", 
							 "Could Not Verify All the source options for form level Aggregate field are Long Names of Field ");
		
		boolean verifyUniqueAggSrc = fdp.uniqueAggSource(FormLvlAggField);
		TestValidation.IsTrue(verifyUniqueAggSrc, 
							"Verified all the source options for form level aggregate field - " + FormLvlAggField + " are Unique", 
							"Could Not Verify all the source options for form level aggregate field - " + FormLvlAggField + " are Unique");
		
	}
//
	@Test(description=" Check Form - Test Aggregate field for different function and source combinations ", priority=3)
	public void TestCase_5182() throws Exception{
		
		List<String> GotAggFunc ;
		
//		hp.clickHomepageLink();
//		
//		boolean navigate = fbp.selectResourceDropDownandNavigate("Suppliers","Forms");
//		TestValidation.IsTrue(navigate, 
//				"Navigated to FSQABrowser>FormsTab", 
//				"Failed to navigate to FSQABrowser>Forms Tab");
//		
//		boolean applyfilter = fbp.openAndApplySettingsForColumn(COLUMNHEADER.FORMNAME, COLUMNSETTING.FILTER,chkFormName);
//		TestValidation.IsTrue(applyfilter, 
//				"Applied Filter on" + COLUMNHEADER.FORMNAME, 
//				"Failed to apply filter on" + COLUMNHEADER.FORMNAME);
//		
//		boolean editform = fbp.editSelectForm(chkFormName);
//		TestValidation.IsTrue(editform, 
//				"Form Edited", 
//				"Failed to edit form");
		
		boolean editFormDesign = fdp.navigateToReleaseForm_EditForm(chkFormName);
		TestValidation.IsTrue(editFormDesign, 
				"OPENED form - '"+chkFormName+"' in edit mode", 
				"Could NOT open form - '"+chkFormName+"' in edit mode"); 
		
		GotAggFunc = fdp.getAggDrpDwnFunctions(FormLvlAggField);
		TestValidation.IsTrue(ActualAggFunc.containsAll(GotAggFunc), 
				  "Verified Function dropdown to have all functions - " + GotAggFunc + " for average field -" + FormLvlAggField, 
				  "Could Not Verify Function dropdown to have all functions - " + GotAggFunc + " for average field -" + FormLvlAggField);
		
		boolean selectAggField = fdp.selectField(FormLvlAggField);
		TestValidation.IsTrue(selectAggField,
						 	 "Selected Aggregate Field -" + FormLvlAggField, 
						 	 "Could Not Aggregate Field -" + FormLvlAggField);
		
		boolean setAggFuncAverage  = fdp.selectAggregateFunction("Average");
		TestValidation.IsTrue(setAggFuncAverage, 
							 "Successfully Changed Aggregate Function to - Average", 
							 "Could Not Change Aggregate Function to - Average");
		
		///
		boolean selectAggField1 = fdp.selectField(FormLvlAggField);
		TestValidation.IsTrue(selectAggField1,
						 	 "Selected Aggregate Field -" + FormLvlAggField, 
						 	 "Could Not Aggregate Field -" + FormLvlAggField);
		
		boolean setAggFuncCountPass =  fdp.selectAggregateFunction("Count Pass");
		TestValidation.IsTrue(setAggFuncCountPass, 
							 "Successfully Changed Aggregate Function to - Count Pass", 
				 			 "Could Not Change Aggregate Function to - Count Pass");
		
		
		/////
		boolean selectAggField2 = fdp.selectField(FormLvlAggField);
		TestValidation.IsTrue(selectAggField2,
						 	 "Selected Aggregate Field -" + FormLvlAggField, 
						 	 "Could Not Aggregate Field -" + FormLvlAggField);
		
		boolean setAggFuncSum =  fdp.selectAggregateFunction("Sum");
		TestValidation.IsTrue(setAggFuncSum, 
							 "Successfully Changed Aggregate Function to - Sum", 
				 			 "Could Not Change Aggregate Function to - Sum");
		
		/////
		boolean selectAggField3 = fdp.selectField(FormLvlAggField);
		TestValidation.IsTrue(selectAggField3,
						 	 "Selected Aggregate Field -" + FormLvlAggField, 
						 	 "Could Not Aggregate Field -" + FormLvlAggField);
		
		TestValidation.IsTrue((setAggFuncAverage&&setAggFuncCountPass&&setAggFuncSum), 
							  "Successfully verified Changing of Aggreate Function for Aggregate field - " + FormLvlAggField, 
							  "Could Not Verify Changing of Aggreate Function for Aggregate field - " + FormLvlAggField);
		
		boolean clearAggSrc = fdp.clearAggSource(FormLvlAggField);
		TestValidation.IsTrue(clearAggSrc, 
							 "Successfully Cleared Aggregate Source for Field - "+ FormLvlAggField, 
							 "Could Not Clear Aggregate Source for Field - "+ FormLvlAggField);
		
		boolean clearAggFunc = fdp.clearAggFunction(FormLvlAggField);
		TestValidation.IsTrue(clearAggFunc, 
				 "Successfully Cleared Aggregate Function for Field - "+ FormLvlAggField, 
				 "Could Not Clear Aggregate Function for Field - "+ FormLvlAggField);
		
		boolean clivkNextBtn = fdp.clickOnNextButton(fdp.ReleaseFormPg,"Release Form");
		TestValidation.IsTrue(clivkNextBtn, 
							  "Clicked on next button and Navigated to Release Page", 
							  "Could not click on next button and not Navigate to Release Page");

		boolean verifySrcError = fdp.releaseWithoutAggDetailsError(chkFormName, FormLvlAggField, srcError);
		TestValidation.IsTrue(verifySrcError, 
							 "Verified - " + srcError + " for Aggregate field - " + FormLvlAggField + " while Releasing Form" , 
							 "Could Not Verify - " + srcError + " for Aggregate field - " + FormLvlAggField + " while Releasing Form");
		
		boolean verifyFuncError = fdp.releaseWithoutAggDetailsError(chkFormName, FormLvlAggField, functionError);
		TestValidation.IsTrue(verifyFuncError, 
							 "Verified - " + functionError + " for Aggregate field - " + FormLvlAggField + " while Releasing Form" , 
							 "Could Not Verify - " + functionError + " for Aggregate field - " + FormLvlAggField + " while Releasing Form");
	
		boolean navigateBackToFormDesign = fdp.backToFormDesignFrmRelease(chkFormName);
		TestValidation.IsTrue(navigateBackToFormDesign, 
							 "Successfully Navigated Back to Form Design from Release Page", 
							 "Could Not Navigate Back to Form Design from Release Page");
		
		boolean setAggSrc = fdp.setAggregateSource(FormLvlAggField,FormLvlNumFields.get(1));
		TestValidation.IsTrue(setAggSrc, 
							  "Successfully Set Aggregate Source to field - " + FormLvlNumFields.get(1), 
							  "Could Not Set Aggregate Source to field - " + FormLvlNumFields.get(1));
		
		controlActions.actionEnter();
		
		boolean setAggFunc = fdp.setAggregateFunction("Sum");
		TestValidation.IsTrue(setAggFunc, 
							  "Successfully Set Aggregate Function as-  Sum", 
							  "Could Not Set Aggregate Function as-  Sum");
		
		boolean clivkNextBtn1 = fdp.clickOnNextButton(fdp.ReleaseFormPg,"Release Form");
		TestValidation.IsTrue(clivkNextBtn1, 
							 "Clicked on next button and Navigated to Release Page", 
				  			 "Could not click on next button and not Navigate to Release Page");
		
		boolean releaseForm = fdp.releaseForm(chkFormName);
		TestValidation.IsTrue(releaseForm, 
							 "Successfully released form -" + chkFormName, 
							 "Could Not release form -" + chkFormName);
		
		hp.clickHomepageLink();
		boolean navigate1 = fbp.selectResourceDropDownandNavigate("Suppliers","Forms");
		TestValidation.IsTrue(navigate1, 
							"Navigated to FSQABrowser>FormsTab", 
							"Failed to navigate to FSQABrowser>Forms Tab");

		boolean applyfilter1 = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME,chkFormName);
		TestValidation.IsTrue(applyfilter1, 
				"Applied Filter on" + COLUMNHEADER.FORMNAME, 
				"Failed to apply filter on" + COLUMNHEADER.FORMNAME);
	
		boolean verifyAggregateFunction = formoperations.verifyAggFieldsResult_Sum(FormLvlNumFields.get(1),FormLvlAggField, new int[] {1,2});
		TestValidation.IsTrue(verifyAggregateFunction, 
							 "Verified Sum Function for Aggregate field - " + FormLvlAggField, 
							 "Could Not verify Sum Function for Aggregate field - " + FormLvlAggField);
	}
	
	@Test(description="Form Designer - Aggregate Field \"Source\" drop-down at the Section level ", priority = 4)
	public void TestCase_7754() throws Exception{
		
//		
		hp.clickHomepageLink();
		boolean navigate = fbp.selectResourceDropDownandNavigate("Suppliers","Forms");
		TestValidation.IsTrue(navigate, 
				"Navigated to FSQABrowser>FormsTab", 
				"Failed to navigate to FSQABrowser>Forms Tab");

		boolean applyfilter = fbp.openAndApplySettingsForColumn(COLUMNHEADER.FORMNAME, COLUMNSETTING.FILTER,chkFormName);
		TestValidation.IsTrue(applyfilter, 
				"Applied Filter on" + COLUMNHEADER.FORMNAME, 
				"Failed to apply filter on" + COLUMNHEADER.FORMNAME);
		
		boolean editform = fbp.editSelectForm(chkFormName);
		TestValidation.IsTrue(editform, 
				"Form Edited", 
				"Failed to edit form");
		
//		// Aggregate field Sources related Validations
		
			List<String> AggSourceSection1Lvl ;
		
			AggSourceSection1Lvl = fdp.getAggDrpDwnSources(Section1LvlAggField);
			
			System.out.println(AggSourceSection1Lvl);
			
			boolean sectionRepeatField = AggSourceSection1Lvl.contains(Section1LvlNumFields.get(1));
			TestValidation.IsTrue(sectionRepeatField, 
								 "Verified  Section level repeated numeric field - "+ Section1LvlNumFields.get(1) +" - present as Aggregate source for section lvl Aggregaate Field - " + AggSourceSection1Lvl, 
								 "Could Not Verify  Section level repeated numeric field - "+ Section1LvlNumFields.get(1) +" - present as Aggregate source for section lvl Aggregaate Field - " + AggSourceSection1Lvl);
			
			boolean fieldGpInSecRepeatField = AggSourceSection1Lvl.contains(Group2LvlNumFields.get(1));
			TestValidation.IsTrue(fieldGpInSecRepeatField, 
								 "Verified repeated numeric field in field group - " +Group2LvlNumFields.get(1) +" inside section "+ Section1LvlNumFields.get(1) +" - present as Aggregate source for section Aggregaate Field - " + AggSourceSection1Lvl, 
								 "Could Not Verify  repeated numeric field in field group - "+Group2LvlNumFields.get(1) +" inside section "+ Section1LvlNumFields.get(1) +" - present as Aggregate source for section Aggregaate Field - " + AggSourceSection1Lvl);
			
			List<String> order1 = Arrays.asList(Group2LvlNumFields.get(1),Section1LvlNumFields.get(1));
			List<String> order2 = Arrays.asList(Section1LvlNumFields.get(1),Group2LvlNumFields.get(1));
			
			boolean onlyFGInSecAndSecRepeatFields = (AggSourceSection1Lvl.equals(order1) || AggSourceSection1Lvl.equals(order2)) ;
			TestValidation.IsTrue(onlyFGInSecAndSecRepeatFields,
								 "Verified Only Section's repeated field and Repeated Field in FieldGroup inside the Section are present as Source Fields for Section's Average Field",
								 "Failed to verify that Only Section's repeated field and Repeated Field in FieldGroup inside the Section are  present as Source Fields for Section's Average Field");
		
			boolean otherSectionRepeatField = AggSourceSection1Lvl.contains(Section2LvlNumFields.get(1));
			TestValidation.IsFalse(otherSectionRepeatField, 
								 "Verified  Other Section repeated numeric field - "+ Section2LvlNumFields.get(1) +" - NOT present as Aggregate source for section Aggregaate Field - " + AggSourceSection1Lvl, 
								 "Could Not Verify  Other Section repeated numeric field - "+ Section2LvlNumFields.get(1) +" - IS present as Aggregate source for section Aggregaate Field - " + AggSourceSection1Lvl);
			
			boolean OtherFieldGpRepeatField = AggSourceSection1Lvl.contains(Group1LvlNumFields.get(1));
			TestValidation.IsFalse(OtherFieldGpRepeatField, 
								"Verified repeated numeric field - " +Group1LvlNumFields.get(1) +" from other field group is NOT present as Aggregate source for section Aggregaate Field - " + AggSourceSection1Lvl, 
								"Could Not Verify  numeric field - "+Group1LvlNumFields.get(1) +" from other field group present as Aggregate source for section Aggregaate Field - " + AggSourceSection1Lvl);

			TestValidation.IsTrue((sectionRepeatField&&fieldGpInSecRepeatField), 
								 "Verified the Source DropDown for Aggregate field " + Section1LvlAggField + "shows only the Lng Names of field" , 
								 "Could NOT verify the Source DropDown for Aggregate field " + Section1LvlAggField + "shows only the Lng Names of field");
			
			boolean verifyUniqueAggSrc = fdp.uniqueAggSource(Section1LvlAggField);
			TestValidation.IsTrue(verifyUniqueAggSrc, 
								"Verified all the source options for section aggregate field - " + FormLvlAggField + " are Unique", 
								"Could Not Verify all the source options for for section aggregate field - " + FormLvlAggField + " are Unique");
			
			
		// - > DO : Release the Form
			fdp.clickOnNextButton(fdp.ReleaseFormPg,"Release Form");

			fdp.releaseForm(chkFormName);
		
	hp.clickHomepageLink();
		boolean navigate1 = fbp.selectResourceDropDownandNavigate("Suppliers","Forms");
		TestValidation.IsTrue(navigate1, 
				"Navigated to FSQABrowser>FormsTab", 
				"Failed to navigate to FSQABrowser>Forms Tab");


		//While Submitting the form Validations
		System.out.println(chkFormName);
		boolean applyfilter1 = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME,chkFormName);
		TestValidation.IsTrue(applyfilter1, 
				"Applied Filter on" + COLUMNHEADER.FORMNAME, 
				"Failed to apply filter on" + COLUMNHEADER.FORMNAME);
		
		
		boolean blankField = formoperations.verifyFieldIsBlank(Section1LvlNumFields.get(1));
		TestValidation.IsTrue(blankField, 
							 "Verified fields " + Section1LvlNumFields.get(1) + "having  no default pre-set value are Blank" , 
							 "Failed To verify fields " + Section1LvlNumFields.get(1) + "having  no default pre-set value are Blank");

		boolean fillValInOtherFields = formoperations.setInputFieldValue(FormLvlNumFields.get(1), Arrays.asList("20","","30"), null);
		TestValidation.IsTrue(fillValInOtherFields, 
							 "Successfully filled values in field " + FormLvlNumFields.get(1) + " to check if they are used in section aggregate calculation", 
							 "Could Not fill values in field " + FormLvlNumFields.get(1) + " to check if they are used in section aggregate calculation");
		
		boolean verifyAggSumResult = formoperations.verifyAggFieldsResult_Sum(Section1LvlNumFields.get(1),Section1LvlAggField, new int[] {1,3});
		TestValidation.IsTrue(verifyAggSumResult, 
							 "Successsfully verified Aggregate field " + Section1LvlAggField + "result for 'Sum' Function", 
							 "Could Not Verify Aggregate field " + Section1LvlAggField + "result for 'Sum' Function");
		
		TestValidation.IsTrue(verifyAggSumResult, 
				 			 "Successsfully verified that fields outside of section do not affect Aggregate field " + Section1LvlAggField + "result for 'Sum' Function", 
				 			 "Failed to verify that fields outside of section do not affect Aggregate field " + Section1LvlAggField + "result for 'Sum' Function");
	}
	
	//=======================================================	
	@Test(description=" Form Designer - Check Forms - User can add Aggregate elements to the form " , priority = 20)
	public void TestCase_6849() throws Exception{
		String checkFormName = CommonMethods.dynamicText("CheckFrm_");
		
		try {
		fdp = hp.clickFormDesignerMenu();

		controlActions.clickElement(fdp.SelectCheckFormLnk);

		//Selection of resource	
		boolean selectResource = fdp.selectResource("Suppliers",supplierCategoryValue,supplierInstanceValue);
		TestValidation.IsTrue(selectResource, 
							 "Selected Resource successfully", 
							 "Failed to Select Resource");
		
		//Go to 'Form Design'
		boolean clickOnNextButton = fdp.clickOnNextButton(fdp.DesignFormPg,"Design Form");
		TestValidation.IsTrue(clickOnNextButton, 
							 "Clicked On Next Button successfully", 
							 "Failed to Click On Next Button");

		controlActions.sendKeys(fdp.FormNameTxt, checkFormName);
		
		String AggSrcField = "N1";
		WebElement numSrc1 = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_TYPE,"FIELDTYPE",FIELD_TYPES.NUMERIC);
		controlActions.dragdrop(numSrc1, fdp.FormLevel);
		boolean setNumFieldValue1 = fdp.setTextBoxValue(FIELD_TYPES.NUMERIC, AggSrcField);		
		TestValidation.IsTrue(setNumFieldValue1, 
							 "'Numeric' field 1 for Source for aggregate field added successfully - " + AggSrcField, 
							 "Failed to Add 1st Numeric Text field");	
		
		WebElement numSrc2 = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_TYPE,"FIELDTYPE",FIELD_TYPES.NUMERIC);
		controlActions.dragdrop(numSrc2, fdp.FormLevel);
		boolean setNumFieldValue2 = fdp.setTextBoxValue(FIELD_TYPES.NUMERIC, AggSrcField);		
		TestValidation.IsTrue(setNumFieldValue2, 
							"'Numeric' field 2 for Source for aggregate field added successfully - " + AggSrcField, 
							"Failed to Add 2nd Numeric Text field");	
		
		String Character225Text = "FreeText1AbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijkl";
		WebElement Aggregate = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.ADD_FIELD_ELEMENTS,"FIELDELEMENT",FORM_ELEMENTS.AGGREGATE);
		controlActions.dragdrop(Aggregate, fdp.FormLevel);
		boolean setFreeTextFieldValue1 = fdp.setTextBoxValue(FORM_ELEMENTS.AGGREGATE, Character225Text);		
		TestValidation.IsTrue(setFreeTextFieldValue1, 
							"Added Aggregate Field and verified 255 Character Long Name " + Character225Text , 
							"Failed to Add Aggregate field");		
		
		
		boolean selectAggFunction = fdp.selectAggregateFunctionJSClick(AGGREGATE_FUNCTION.SUM);
		TestValidation.IsTrue(selectAggFunction, 
							"Successfully Selected Aggregate Function - Sum", 
							"Failed to  Selected Aggregate Function");
		
		boolean selectAggregateSource = fdp.selectAggregateSource(AggSrcField);
		TestValidation.IsTrue(selectAggregateSource, 
							 "Successfully Selected Aggregate Source -" + AggSrcField, 
							 "Failed to Selected Aggregate Source");
		
		String aggFieldName = "FreeText1Abcdefghijklmnop";
		String allowCommentsToggleButton = fdp.getStatusOfToggleButton(aggFieldName, "Allow Comments"); 
		boolean compare2 = allowCommentsToggleButton.contains("Off");
		TestValidation.IsTrue(compare2, 
							 "Default state of 'Allow Comments' toggle button is 'Off'", 
							 "Failed to validate default state of 'Allow Comments' toggle button");
		
		String allowAttachmentsToggleButton = fdp.getStatusOfToggleButton(aggFieldName, "Allow Attachments"); 
		boolean compare3 = allowAttachmentsToggleButton.contains("Off");
		TestValidation.IsTrue(compare3, 
							 "Default state of 'Allow Attachments' toggle button is 'Off'", 
							 "Failed to validate default state of 'Allow Attachments' toggle button");
		
		String showOnCOAToggleButton = fdp.getStatusOfToggleButton(aggFieldName, "Show on COA"); 
		boolean compare4 = showOnCOAToggleButton.contains("On");
		TestValidation.IsTrue(compare4, 
							 "Default state of 'Show on COA' toggle button is 'On'", 
							 "Failed to validate default state of 'Show on COA' toggle button");
		
		String showHintToggleButton = fdp.getStatusOfToggleButton(aggFieldName, "Show Hint"); 
		boolean compare5 = showHintToggleButton.contains("Off");
		TestValidation.IsTrue(compare5, 
							 "Default state of 'Show Hint' toggle button is 'Off'", 
							 "Failed to validate default state of 'Show Hint' toggle button");
		
		String allowCorrectionToggleButton = fdp.getStatusOfToggleButton(aggFieldName, "Allow Correction"); 
		boolean compare6 = allowCorrectionToggleButton.contains("Off");
		TestValidation.IsTrue(compare6, 
							 "Default state of 'Allow Correction' toggle button is 'Off'", 
							 "Failed to validate default state of 'Allow Correction' toggle button");
		
		String ShowMInMaxToggleButton = fdp.getStatusOfToggleButton(aggFieldName, "Show Min/Max"); 
		boolean compare7 = ShowMInMaxToggleButton.contains("On");
		TestValidation.IsTrue(compare7, 
							 "Default state of 'Show Min/Max' toggle button is 'On'", 
							 "Failed to validate default state of 'Show Min/Max' toggle button");
		
		String showTargetToggleButton = fdp.getStatusOfToggleButton(aggFieldName, "Show Target"); 
		boolean compare8 = showTargetToggleButton.contains("Off");
		TestValidation.IsTrue(compare8, 
							 "Default state of 'Show Target' toggle button is 'Off'", 
							 "Failed to validate default state of 'Show Min/Max' toggle button");
		
		String failsCheckToggleButton = fdp.getStatusOfToggleButton(aggFieldName, "Fails Check"); 
		boolean compare9 = failsCheckToggleButton.contains("On");
		TestValidation.IsTrue(compare9, 
							 "Default state of 'Fails Check' toggle button is 'On'", 
							 "Failed to validate default state of 'Fails Check' toggle button");
		}finally {
			fdp.navigateToHome();
		}
		
	}
	
	
	@Test(description=" Questionnaire Form - Test Aggregate field for different function and source combinations", priority = 20)
	public void TestCase_38481() throws Exception{
		
		List<String> GotAggFunc ;
		String quesForm = CommonMethods.dynamicText("QF");
		
		hp.clickFormDesignerMenu();

		
		HashMap<String, List<String>> quesFields = new HashMap<String, List<String>>();
		quesFields.put(FIELD_TYPES.NUMERIC, Arrays.asList(FormLvlNumFields.get(1)));
		quesFields.put(FIELD_TYPES.AGGREGATE, Arrays.asList(FormLvlAggField));

		FormFieldParams ffpQues = new FormFieldParams();
		ffpQues.FieldDetails = quesFields;
		ffpQues.AgrregateFunction = AGGREGATE_FUNCTION.MAX_VALUE;
		ffpQues.SetAggregateFunction = true; ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		ffpQues.AgrregateSource = FormLvlNumFields.get(1);
		ffpQues.Repeat = Arrays.asList(Arrays.asList(FormLvlNumFields.get(1),"3"));
		
		HashMap<String, List<String>> suppResource = new HashMap<String, List<String>>();
		suppResource.put(FORMRESOURCETYPES.SUPPLIERS, Arrays.asList(supplierCategoryValue));

		FormDesignParams fdpQues = new FormDesignParams();
		fdpQues.FormType = FORMTYPES.QUESTIONNAIRE;
		fdpQues.FormName = quesForm;
		fdpQues.SelectResources = suppResource;
		fdpQues.DesignFields = Arrays.asList(ffpQues);
		
		boolean deignForm = fdp.designForm(fdpQues);
		TestValidation.IsTrue(deignForm, 
							  "Successfully Designed Questionaire Form - " + quesForm , 
							  "Failed to Design Questionaire Form - " + quesForm);
		
		GotAggFunc = fdp.getAggDrpDwnFunctions(FormLvlAggField);
		
		TestValidation.IsTrue(ActualAggFunc.containsAll(GotAggFunc), 
							  "Verified Function dropdown to have all functions - " + GotAggFunc + " for average field -" + FormLvlAggField, 
							  "Could Not Verify Function dropdown to have all functions - " + GotAggFunc + " for average field -" + FormLvlAggField);
		
		controlActions.actionEnter();
		
		boolean setAggFuncAverage  = fdp.selectAggregateFunction("Average");
		TestValidation.IsTrue(setAggFuncAverage, 
							 "Successfully Changed Aggregate Function to - Average", 
							 "Could Not Change Aggregate Function to - Average");
		
		boolean setAggFuncCountPass =  fdp.selectAggregateFunction("Count Pass");
		TestValidation.IsTrue(setAggFuncCountPass, 
							 "Successfully Changed Aggregate Function to - Count Pass", 
				 			 "Could Not Change Aggregate Function to - Count Pass");
		
		boolean setAggFuncSum =  fdp.selectAggregateFunction("Sum");
		TestValidation.IsTrue(setAggFuncSum, 
							 "Successfully Changed Aggregate Function to - Sum", 
				 			 "Could Not Change Aggregate Function to - Sum");
		
		TestValidation.IsTrue((setAggFuncAverage&&setAggFuncCountPass&&setAggFuncSum), 
							  "Successfully verified Changing of Aggreate Function for Aggregate field - " + FormLvlAggField, 
							  "Could Not Verify Changing of Aggreate Function for Aggregate field - " + FormLvlAggField);
		
		fdp.clearAggFunction(FormLvlAggField);
		
		boolean clivkNextBtn = fdp.clickOnNextButton(fdp.ReleaseFormPg,"Release Form");
		TestValidation.IsTrue(clivkNextBtn, 
							  "Clicked on next button and Navigated to Release Page", 
							  "Could not click on next button and not Navigate to Release Page");

		boolean verifySrcError = fdp.releaseWithoutAggDetailsError(quesForm, FormLvlNumFields.get(1), srcError);
		TestValidation.IsTrue(verifySrcError, 
							 "Verified - " + srcError + " for Aggregate field - " + FormLvlAggField + " while Releasing Form" , 
							 "Could Not Verify - " + srcError + " for Aggregate field - " + FormLvlAggField + " while Releasing Form");
		
		boolean verifyFuncError = fdp.releaseWithoutAggDetailsError(quesForm, FormLvlNumFields.get(1), functionError);
		TestValidation.IsTrue(verifyFuncError, 
							 "Verified - " + functionError + " for Aggregate field - " + FormLvlAggField + " while Releasing Form" , 
							 "Could Not Verify - " + functionError + " for Aggregate field - " + FormLvlAggField + " while Releasing Form");
	
		boolean navigateBackToFormDesign = fdp.backToFormDesignFrmRelease(quesForm);
		TestValidation.IsTrue(navigateBackToFormDesign, 
							 "Successfully Navigated Back to Form Design from Release Page", 
							 "Could Not Navigate Back to Form Design from Release Page");
		
		boolean setAggSrc = fdp.setAggregateSource(FormLvlAggField,FormLvlNumFields.get(1));
		TestValidation.IsTrue(setAggSrc, 
							  "Successfully Set Aggregate Source to field - " + FormLvlNumFields.get(1), 
							  "Could Not Set Aggregate Source to field - " + FormLvlNumFields.get(1));
		
		boolean setAggFuncSum1 =  fdp.setAggregateFunction("Sum");
		TestValidation.IsTrue(setAggFuncSum1, 
							 "Successfully Changed Aggregate Function to - Sum", 
				 			 "Could Not Change Aggregate Function to - Sum");
		
		boolean clivkNextBtn1 = fdp.clickOnNextButton(fdp.ReleaseFormPg,"Release Form");
		TestValidation.IsTrue(clivkNextBtn1, 
							 "Clicked on next button and Navigated to Release Page", 
				  			 "Could not click on next button and not Navigate to Release Page");
		
		boolean releaseForm = fdp.releaseForm(quesForm);
		TestValidation.IsTrue(releaseForm, 
							 "Successfully released form -" + quesForm, 
							 "Could Not release form -" + quesForm);
		
		hp.clickHomepageLink();
		boolean navigate1 = fbp.selectResourceDropDownandNavigate("Suppliers","Forms");
		TestValidation.IsTrue(navigate1, 
							"Navigated to FSQABrowser>FormsTab", 
							"Failed to navigate to FSQABrowser>Forms Tab");

		boolean applyfilter1 = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME,quesForm);
		TestValidation.IsTrue(applyfilter1, 
				"Applied Filter on" + COLUMNHEADER.FORMNAME, 
				"Failed to apply filter on" + COLUMNHEADER.FORMNAME);
	
		boolean verifyAggregateFunction = formoperations.verifyAggFieldsResult_Sum(FormLvlNumFields.get(1),FormLvlAggField, new int[] {1,3});
		TestValidation.IsTrue(verifyAggregateFunction, 
							 "Verified Sum Function for Aggregate field - " + FormLvlAggField, 
							 "Could Not verify Sum Function for Aggregate field - " + FormLvlAggField);
		
	}

	@Test(description="  Form Designer - Questionnaire Forms - User can add Aggregate elements to the form ", priority = 20)
	public void TestCase_38401() throws Exception{
		
		String quesFormName = CommonMethods.dynamicText("QstnFrm_");
		String TabName = "Settings";
		
		String AggSrcField = "Number1";
		String Character225Text = "FreeText1AbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijkl";
		
//		String supplierCategoryValue = "Supp_Cat_18.10_22.18.17";
//		String supplierInstanceValue = "Supp_Inst_18.10_22.18.17";
		
		try {
	
		hp.clickFormDesignerMenu();
			
		controlActions.clickElement(fdp.SelectQuestionnaireFormLnk);

		//Selection of resource	
		boolean selectResource = fdp.selectResource("Suppliers",supplierCategoryValue,supplierInstanceValue);
		TestValidation.IsTrue(selectResource, 
							 "Selected Resource successfully", 
							 "Failed to Select Resource");
		
		//Go to 'Form Design'
		boolean clickOnNextButton = fdp.clickOnNextButton(fdp.DesignFormPg,"Design Form");
		TestValidation.IsTrue(clickOnNextButton, 
							 "Clicked On Next Button successfully", 
							 "Failed to Click On Next Button");

		controlActions.sendKeys(fdp.FormNameTxt, quesFormName);
		
		
		WebElement numSrc1 = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_TYPE,"FIELDTYPE",FIELD_TYPES.NUMERIC);
		controlActions.dragdrop(numSrc1, fdp.FieldTypeDropAreaFormLevel);
		boolean setNumFieldValue1 = fdp.setElementValueTextArea(FIELD_TYPES.NUMERIC, AggSrcField);	
		TestValidation.IsTrue(setNumFieldValue1, 
							 "'Numeric' field 1 for Source for aggregate field added successfully - " + AggSrcField, 
							 "Failed to Add 1st Numeric Text field");	
		
		WebElement numSrc2 = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_TYPE,"FIELDTYPE",FIELD_TYPES.NUMERIC);
		controlActions.dragdrop(numSrc2, fdp.FieldTypeDropAreaFormLevel);
		boolean setNumFieldValue2 = fdp.setElementValueTextArea(FIELD_TYPES.NUMERIC, AggSrcField);		
		TestValidation.IsTrue(setNumFieldValue2, 
							"'Numeric' field 2 for Source for aggregate field added successfully - " + AggSrcField, 
							"Failed to Add 2nd Numeric Text field");	
		
		//String Character225Text = "FreeText1AbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijkl";
		WebElement Aggregate = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.ADD_FIELD_ELEMENTS,"FIELDELEMENT",FORM_ELEMENTS.AGGREGATE);
		controlActions.dragdrop(Aggregate, fdp.FieldTypeDropAreaFormLevel);
		boolean setFreeTextFieldValue1 = fdp.setElementValueTextArea(FORM_ELEMENTS.AGGREGATE, Character225Text);
		TestValidation.IsTrue(setFreeTextFieldValue1, 
							"Added Aggregate Field and verified 255 Character Long Name " + Character225Text , 
							"Failed to Add Aggregate field");		
		
		
		String aggFielShortdName = "FreeText1Abcdefghijklmnop";
		
		fdp.selectField(aggFielShortdName);
		
		boolean selectAggFunction = fdp.selectAggregateFunction("Sum");
		TestValidation.IsTrue(selectAggFunction, 
							"Successfully Selected Aggregate Function - Sum", 
							"Failed to  Selected Aggregate Function");
		
		boolean selectAggregateSource = fdp.selectAggregateSource(AggSrcField);
		TestValidation.IsTrue(selectAggregateSource, 
							 "Successfully Selected Aggregate Source -" + AggSrcField, 
							 "Failed to Selected Aggregate Source");
		
		boolean selectSettingsTab = fdp.selectPropertiesTabForField(aggFielShortdName, TabName);
		TestValidation.IsTrue(selectSettingsTab, 
							 "Selected Properties Tab - " + TabName + "For field - " + aggFielShortdName,
							 "Could Not Select Properties Tab - " + TabName + "For field - " + aggFielShortdName);
		
		boolean allowCommentsOff = fdp.verifyStateOfToggleButton("Allow Comments", "Off");
		TestValidation.IsTrue(allowCommentsOff, 
							 "Default state of 'Allow Comments' toggle button is 'Off'", 
							 "Failed to validate default state of 'Allow Comments' toggle button");
		
		boolean allowAttachmentsOff = fdp.verifyStateOfToggleButton("Allow Attachments", "Off");
		TestValidation.IsTrue(allowAttachmentsOff, 
				 			 "Default state of 'Allow Attachments' toggle button is 'Off'", 
							 "Failed to validate default state of 'Allow Attachments' toggle button");
		
		boolean showHintOff = fdp.verifyStateOfToggleButton("Show Hint", "Off");
		TestValidation.IsTrue(showHintOff, 
							 "Default state of 'Show Hint' toggle button is 'Off'", 
							 "Failed to validate default state of 'Show Hint' toggle button");
		
		boolean showMinMaxOn = fdp.verifyStateOfToggleButton("Show Min/Max", "On");
		TestValidation.IsTrue(showMinMaxOn, 
							 "Default state of 'Show Min/Max' toggle button is 'On'", 
							 "Failed to validate default state of 'Show Min/Max' toggle button");
		
		boolean showtargetOff = fdp.verifyStateOfToggleButton("Show Target", "Off");
		TestValidation.IsTrue(showtargetOff, 
							 "Default state of 'Show Target' toggle button is 'Off'", 
							 "Failed to validate default state of 'Show Target' toggle button");
		

		boolean failsQuestionaireOn = fdp.verifyStateOfToggleButton("Fails Questionnaire", "On");
		TestValidation.IsTrue(failsQuestionaireOn, 
							 "Default state of 'Fails Questionnaire' toggle button is 'On'", 
							 "Failed to validate default state of 'Fails Questionnaire' toggle button");
		
		boolean allowCorrectionOff = fdp.verifyStateOfToggleButton("Allow Correction", "Off");
		TestValidation.IsTrue(allowCorrectionOff, 
							 "Default state of 'Allow Correction' toggle button is 'Off'", 
							 "Failed to validate default state of 'Allow Correction' toggle button");
		}
		finally {
			fdp.navigateToHome();
		}
	}
	
	
	@Test(description="  Form Designer can edit Aggregate properties on Settings tab whenever an Aggregate is selected ", priority = 20)
	public void TestCase_5215() throws Exception{
		
		String TabName = "Settings";
		
		hp.clickHomepageLink();
		boolean navigate = fbp.selectResourceDropDownandNavigate("Suppliers","Forms");
		TestValidation.IsTrue(navigate, 
				"Navigated to FSQABrowser>FormsTab", 
				"Failed to navigate to FSQABrowser>Forms Tab");

		boolean applyfilter = fbp.openAndApplySettingsForColumn(COLUMNHEADER.FORMNAME, COLUMNSETTING.FILTER,chkFormName);
		TestValidation.IsTrue(applyfilter, 
				"Applied Filter on" + COLUMNHEADER.FORMNAME, 
				"Failed to apply filter on" + COLUMNHEADER.FORMNAME);
		
		boolean editform = fbp.editSelectForm(chkFormName);
		TestValidation.IsTrue(editform, 
				"Form Edited", 
				"Failed to edit form");
		
		boolean selectSettingsTab = fdp.selectPropertiesTabForField(FormLvlAggField, TabName);
		TestValidation.IsTrue(selectSettingsTab, 
							 "Selected Properties Tab - " + TabName + "For field - " + FormLvlAggField,
							 "Could Not Select Properties Tab - " + TabName + "For field - " + FormLvlAggField);
		
		boolean allwCommntsOnOffCtrl = fdp.fieldSettingOnOffTogglePresent(FIELD_SETTINGS.ALLOW_COMMENTS);
		TestValidation.IsTrue(allwCommntsOnOffCtrl, 
							  "Verified Toggle Contorls On/Off Displayed on Settings Tab for - " + FIELD_SETTINGS.ALLOW_COMMENTS, 
							  "Could Not verify Toggle Contorls On/Off Displayed on Settings Tab for - " + FIELD_SETTINGS.ALLOW_COMMENTS);
		
		boolean allwAttchmntsOnOffCtrl = fdp.fieldSettingOnOffTogglePresent(FIELD_SETTINGS.ALLOW_ATTACHMENTS);
		TestValidation.IsTrue(allwAttchmntsOnOffCtrl, 
							  "Verified Toggle Contorls On/Off Displayed on Settings Tab for - " + FIELD_SETTINGS.ALLOW_ATTACHMENTS, 
							  "Could Not verify Toggle Contorls On/Off Displayed on Settings Tab for - " + FIELD_SETTINGS.ALLOW_ATTACHMENTS);
		
		boolean showOnCOAOnOffCtrl = fdp.fieldSettingOnOffTogglePresent(FIELD_SETTINGS.SHOW_ON_COA);
		TestValidation.IsTrue(showOnCOAOnOffCtrl, 
							  "Verified Toggle Contorls On/Off Displayed on Settings Tab for - " + FIELD_SETTINGS.SHOW_ON_COA, 
							  "Could Not verify Toggle Contorls On/Off Displayed on Settings Tab for - " + FIELD_SETTINGS.SHOW_ON_COA);
		
		boolean showHintOnOffCtrl = fdp.fieldSettingOnOffTogglePresent(FIELD_SETTINGS.SHOW_HINT);
		TestValidation.IsTrue(showHintOnOffCtrl, 
							  "Verified Toggle Contorls On/Off Displayed on Settings Tab for - " + FIELD_SETTINGS.SHOW_HINT, 
							  "Could Not verify Toggle Contorls On/Off Displayed on Settings Tab for - " + FIELD_SETTINGS.SHOW_HINT);
		
		boolean showMinMaxOnOffCtrl = fdp.fieldSettingOnOffTogglePresent(FIELD_SETTINGS.SHOW_MINMAX);
		TestValidation.IsTrue(showMinMaxOnOffCtrl, 
							  "Verified Toggle Contorls On/Off Displayed on Settings Tab for - " + FIELD_SETTINGS.SHOW_MINMAX, 
							  "Could Not verify Toggle Contorls On/Off Displayed on Settings Tab for - " + FIELD_SETTINGS.SHOW_MINMAX);
		
		boolean showTrgtOnOffCtrl = fdp.fieldSettingOnOffTogglePresent(FIELD_SETTINGS.SHOW_TARGET);
		TestValidation.IsTrue(showTrgtOnOffCtrl, 
							  "Verified Toggle Contorls On/Off Displayed on Settings Tab for - " + FIELD_SETTINGS.SHOW_TARGET, 
							  "Could Not verify Toggle Contorls On/Off Displayed on Settings Tab for - " + FIELD_SETTINGS.SHOW_TARGET);
		
		boolean flsChkOnOffCtrl = fdp.fieldSettingOnOffTogglePresent(FIELD_SETTINGS.FAILS_CHECK);
		TestValidation.IsTrue(flsChkOnOffCtrl, 
							  "Verified Toggle Contorls On/Off Displayed on Settings Tab for - " + FIELD_SETTINGS.FAILS_CHECK, 
							  "Could Not verify Toggle Contorls On/Off Displayed on Settings Tab for - " + FIELD_SETTINGS.FAILS_CHECK);
		
		boolean allCrrctnOnOffCtrl = fdp.fieldSettingOnOffTogglePresent(FIELD_SETTINGS.ALLOW_CORRECTION);
		TestValidation.IsTrue(allCrrctnOnOffCtrl, 
							  "Verified Toggle Contorls On/Off Displayed on Settings Tab for - " + FIELD_SETTINGS.ALLOW_CORRECTION, 
							  "Could Not verify Toggle Contorls On/Off Displayed on Settings Tab for - " + FIELD_SETTINGS.ALLOW_CORRECTION);
		
		//TURN ON ALLOW CORRECTION
		WebElement AllowCorrctnOnLbl = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FLD_SETTING_ON_LBL,
				"FIELDSETTING",FIELD_SETTINGS.ALLOW_CORRECTION);
		controlActions.perform_clickElement_ByElement(AllowCorrctnOnLbl);
		
		boolean verifyPredefinedBtn = fdp.PredefinedCorrectionAddBtn.isDisplayed();
		TestValidation.IsTrue(verifyPredefinedBtn, 
							  "VERFIED Add New button is displayed for 'Predefined Corrections'", 
							  "Failed to Verify whether Add New button is/is not displayed for 'Predefined Corrections'");
		
		boolean mrksRslvdOnOffCtrl = fdp.fieldSettingOnOffTogglePresent(FIELD_SETTINGS.MARK_RESOLVED);
		TestValidation.IsTrue(mrksRslvdOnOffCtrl, 
							  "Verified Toggle Contorls On/Off Displayed on Settings Tab for - " + FIELD_SETTINGS.MARK_RESOLVED, 
							  "Could Not verify Toggle Contorls On/Off Displayed on Settings Tab for - " + FIELD_SETTINGS.MARK_RESOLVED);
		
		boolean saveForm = fdp.clickSaveButton();
		TestValidation.IsTrue(saveForm, 
							 "Form Saved Successfully", 
							 "Could NOT Save Form");
		
		boolean clivkNextBtn = fdp.clickOnNextButton(fdp.ReleaseFormPg,"Release Form");
		TestValidation.IsTrue(clivkNextBtn, 
							  "Clicked on next button and Navigated to Release Page", 
							  "Could not click on next button and not Navigate to Release Page");
		
		boolean navigateBackToFormDesign = fdp.backToFormDesignFrmRelease(chkFormName);
		TestValidation.IsTrue(navigateBackToFormDesign, 
							 "Successfully Navigated Back to Form Designer from Release Page", 
							 "Could Not Navigate Back to Form Designer from Release Page");
		
		boolean selectSettingsTab1 = fdp.selectPropertiesTabForField(FormLvlAggField, TabName);
		TestValidation.IsTrue(selectSettingsTab1, 
							 "Selected Properties Tab - " + TabName + "For field - " + FormLvlAggField,
							 "Could Not Select Properties Tab - " + TabName + "For field - " + FormLvlAggField);
		
		boolean verifyPredefinedBtn1 = fdp.PredefinedCorrectionAddBtn.isDisplayed();
		boolean mrksRslvdOnOffCtrl2 = fdp.fieldSettingOnOffTogglePresent(FIELD_SETTINGS.MARK_RESOLVED);
		TestValidation.IsTrue((verifyPredefinedBtn1&&mrksRslvdOnOffCtrl2 ), 
							  "Verified the saved changes were saved as a part of Form Defination AND Predefined Correcetion and Mark Resolved setting are Visible",
							  "Could Not Verify the saved changes were saved as a part of Form Defination AND Predefined Correcetion and Mark Resolved setting are NOT Visible");
		
		
	}
	
	
	@Test(description=" Form Designer can edit Aggregate \"Function\" and \"Source\" on General tab of properties panel whenever Aggregate is selected", priority = 20)
	public void TestCase_5214() throws Exception{
		
		
//		String chkFormName = "CHK_19.09_18.25.40";
//		hp.clickHomepageLink();
//		boolean navigate = fbp.selectResourceDropDownandNavigate("Suppliers","Forms");
//		TestValidation.IsTrue(navigate, 
//							 "Navigated to FSQABrowser>FormsTab", 
//							 "Failed to navigate to FSQABrowser>Forms Tab");
//
//		boolean applyfilter = fbp.openAndApplySettingsForColumn(COLUMNHEADER.FORMNAME, COLUMNSETTING.FILTER,chkFormName);
//		TestValidation.IsTrue(applyfilter, 
//							 "Applied Filter on" + COLUMNHEADER.FORMNAME, 
//							 "Failed to apply filter on" + COLUMNHEADER.FORMNAME);
//		
//		boolean editform = fbp.editSelectForm(chkFormName);
//		TestValidation.IsTrue(editform, 
//							 "Opened Form -> " + chkFormName + " in Design Form Mode for edit  " , 
//							 "Opened Form -> " + chkFormName + " in Design Form Mode for edit  ");
//		
//	
//		boolean selectAggField = fdp.selectField(FormLvlAggField);
//		TestValidation.IsTrue(selectAggField,
//						 	 "Selected Aggregate Field -" + FormLvlAggField, 
//						 	 "Could Not Aggregate Field -" + FormLvlAggField);
//		
//		boolean selectGeneralTab = fdp.selectPropertiesTabs(PROPERTIES_TABS.GENERAL_TAB);
//		TestValidation.IsTrue(selectGeneralTab, 
//				 "Selected Properties Tab - " + PROPERTIES_TABS.GENERAL_TAB + "For field - " + FormLvlAggField,
//				 "Could Not Select Properties Tab - " + PROPERTIES_TABS.GENERAL_TAB + "For field - " + FormLvlAggField);
		
		String formName = CommonMethods.dynamicText("ChkF");
		//Create Check Form
		fdp = hp.clickFormDesignerMenu();

		// Form Level
		HashMap<String, List<String>> chkFields = new HashMap<String, List<String>>();
		chkFields.put(FIELD_TYPES.NUMERIC, FormLvlNumFields);
		chkFields.put(FIELD_TYPES.AGGREGATE, Arrays.asList(FormLvlAggField));

		
		FormFieldParams ffpChk = new FormFieldParams();
		ffpChk.FieldDetails = chkFields;
		ffpChk.AgrregateFunction = AGGREGATE_FUNCTION.SUM;
		ffpChk.AgrregateSource = FormLvlNumFields.get(1);
		ffpChk.SetAggregateFunction = true;
		ffpChk.Repeat = Arrays.asList(Arrays.asList(FormLvlNumFields.get(1),"3"),Arrays.asList(FormLvlNumFields.get(0),"3"));
		
		HashMap<String, List<String>> suppResource = new HashMap<String, List<String>>();
		suppResource.put(FORMRESOURCETYPES.SUPPLIERS, Arrays.asList(supplierCategoryValue));

		FormDesignParams fdpChk = new FormDesignParams();
		fdpChk.FormType = FORMTYPES.CHECK;
		fdpChk.FormName = formName;
		fdpChk.SelectResources = suppResource;
		fdpChk.DesignFields = Arrays.asList(ffpChk);
		fdpChk.ReleaseForm = true;
		
		fdp.designForm(fdpChk);

		controlActions.refreshDisplayedPage();
		
		boolean selectAggField = fdp.selectField(FormLvlAggField);
		TestValidation.IsTrue(selectAggField,
						 	 "Selected Aggregate Field -" + FormLvlAggField, 
						 	 "Could Not Aggregate Field -" + FormLvlAggField);
		
		boolean funcEditable = fdp.SetAggregateFunction.isDisplayed();
		boolean funcDrpDwn =  fdp.AggregateFunction.isDisplayed();
		TestValidation.IsTrue(funcEditable && funcDrpDwn , 
							 "The editable Function drop-down control is Displayed", 
							 "The editable Function drop-down control is NOT Displayed");

		boolean srcEditable = fdp.SetAggregateSource.isDisplayed();
		boolean srcDrpDwn = fdp.AggSourceDropDown.isDisplayed();
		TestValidation.IsTrue(srcEditable && srcDrpDwn , 
							 "The editable Source drop-down control is Displayed", 
							 "The editable Source drop-down control is NOT Displayed");
		
		
		// FUNCTION
		boolean selectAggFuncAverage  = fdp.selectAggregateFunctionJSClick(AGGREGATE_FUNCTION.AVERAGE);
		TestValidation.IsTrue(selectAggFuncAverage, 
							 "Successfully Selected Aggregate Function - " + AGGREGATE_FUNCTION.AVERAGE + " From Function DropDown", 
							 "Could Not Select Aggregate Function to - " + AGGREGATE_FUNCTION.AVERAGE + " From Function DropDown");
		
		String gotAggFunc_select = fdp.getAggFuncExistingVal();
		TestValidation.IsTrue(gotAggFunc_select.equals(AGGREGATE_FUNCTION.AVERAGE), 
				  			 "Confirmed Aggregate Function for Field " + FormLvlAggField + " is SELECTED to - " + AGGREGATE_FUNCTION.AVERAGE + " hence drop down functionality is verified" , 
				  			 "Could NOT Confirm Aggregate Function for Field " + FormLvlAggField + " is SELECTED to - " + AGGREGATE_FUNCTION.AVERAGE + " hence drop down functionality is NOT verified");
		
		boolean setAggFuncSum =  fdp.setAggregateFunction(AGGREGATE_FUNCTION.SUM);
		TestValidation.IsTrue(setAggFuncSum, 
							 "Successfully Edited Aggregate Function to -" + AGGREGATE_FUNCTION.SUM , 
				 			 "Could Not Edit Aggregate Function to - "+ AGGREGATE_FUNCTION.SUM);
		
		String gotAggFunc_set = fdp.getAggFuncExistingVal();
		TestValidation.IsTrue(gotAggFunc_set.equals(AGGREGATE_FUNCTION.SUM), 
				  			 "Confirmed Aggregate Function for Field " + FormLvlAggField + "is SET to - " + AGGREGATE_FUNCTION.SUM + " hence editable functionality is verified" , 
				  			 "Could NOT Confirm Aggregate Function for Field " + FormLvlAggField + "is SET to - " + AGGREGATE_FUNCTION.SUM + " hence editable functionality is NOT verified");
		
		// SOURCE
		boolean selectAggSource1  = fdp.selectAggregateSource(FormLvlNumFields.get(1));
		TestValidation.IsTrue(selectAggSource1, 
							 "Successfully Selected Aggregate Source  - " + FormLvlNumFields.get(1) + "From Source DropDown", 
							 "Could Not Select Aggregate Source to - " + FormLvlNumFields.get(1) + "From Source DropDown");
		
		String gotAggSrc_Afterselect = fdp.getAggSrcExistingVal();
		TestValidation.IsTrue(gotAggSrc_Afterselect.equals(FormLvlNumFields.get(1)), 
				  			 "Confirmed Aggregate Source for Field " + FormLvlAggField + "is SELECTED to - " + FormLvlNumFields.get(1) + " hence drop down functionality is verified" , 
				  			 "Could NOT Confirm Aggregate Function for Field " + FormLvlAggField + "is SELECTED to - " + FormLvlNumFields.get(1) + " hence drop down functionality is NOT verified");
		
		boolean setAggSource2 =  fdp.setAggregateSource(FormLvlNumFields.get(0));
		TestValidation.IsTrue(setAggSource2, 
							 "Successfully Edited Aggregate Function to -" + FormLvlNumFields.get(0) , 
				 			 "Could Not Edit Aggregate Function to - "+ FormLvlNumFields.get(0));
		
		String gotAggFunc_AfterSet = fdp.getAggSrcExistingVal();
		TestValidation.IsTrue(gotAggFunc_AfterSet.equals(FormLvlNumFields.get(0)), 
				  			 "Confirmed Aggregate Source for Field " + FormLvlAggField + "is SET to - " + FormLvlNumFields.get(0) + " hence editable functionality is verified" , 
				  			 "Could NOT Confirm Aggregate Source for Field " + FormLvlAggField + "is SET to - " + FormLvlNumFields.get(0) + " hence editable functionality is NOT verified");

		
		// Save and Click Next
		boolean saveForm = fdp.clickSaveButton();
		TestValidation.IsTrue(saveForm, 
							 "Form Saved Successfully", 
							 "Could NOT Save Form");
		
		boolean clivkNextBtn = fdp.clickOnNextButton(fdp.ReleaseFormPg,"Release Form");
		TestValidation.IsTrue(clivkNextBtn, 
							  "Clicked on next button and Navigated to Release Page", 
							  "Could not click on next button and not Navigate to Release Page");
		
		boolean navigateBackToFormDesign = fdp.backToFormDesignFrmRelease(formName);
		TestValidation.IsTrue(navigateBackToFormDesign, 
							 "Successfully Navigated Back to Form Designer from Release Page", 
							 "Could Not Navigate Back to Form Designer from Release Page");
		
		// Verifying After Saved 
		boolean selectAggField1 = fdp.selectField(FormLvlAggField);
		TestValidation.IsTrue(selectAggField1,
						 	 "Selected Aggregate Field -" + FormLvlAggField, 
						 	 "Could Not Aggregate Field -" + FormLvlAggField);
		
		String gotAggFunc = fdp.getAggFuncExistingVal();
		String gotAggSrc = fdp.getAggSrcExistingVal();
		
		boolean savedChangesVerify = gotAggFunc.equals(AGGREGATE_FUNCTION.SUM) && gotAggSrc.equals(FormLvlNumFields.get(0));
		TestValidation.IsTrue(savedChangesVerify, 
							  "Verified that Saved Changes were saved as athe part of Form Defination as - both Aggregate Source and Function for field " + FormLvlAggField + " is retained to values at the time of Saving i.e - function as "+ AGGREGATE_FUNCTION.SUM + " and Source as " + Group2LvlNumFields.get(1) , 
							  "Could NOT Verify that Saved Changes were saved as athe part of Form Defination as the values for Aggregate Source and Function for field " + FormLvlAggField + " is NOT retained as - function = "+ AGGREGATE_FUNCTION.SUM + " and Source = " + Group2LvlNumFields.get(1));
	
	}

	@AfterClass(alwaysRun = true)
	public void closeBrowser() throws InterruptedException {
		driver.close();
	}

}
