package com.project.safetychain.mobileapp.pageobjects;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.NoSuchElementException;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.InvalidSelectorException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.SkipException;
import com.project.safetychain.api.models.support.TCG_FormCreation_Wrapper;
import com.project.safetychain.api.models.support.TCG_TaskCreation_Wrapper;
import com.project.safetychain.mobileapp.testcases.TCG_SMK_FormSavedFlow;
import com.project.safetychain.api.models.support.Support.AGGREGATE_FUNC_TYPES;
import com.project.safetychain.api.models.support.Support.Compliance;
import com.project.safetychain.api.models.support.Support.DPT_FIELD_TYPES;
import com.project.safetychain.api.models.support.Support.DPT_FORM_TYPES;
import com.project.safetychain.api.models.support.Support.Element_Types;
import com.project.safetychain.api.models.support.Support.FormFieldParams;
import com.project.safetychain.api.models.support.Support.FormFieldParamsCompliance;
import com.project.safetychain.api.models.support.Support.FormParams;
import com.project.safetychain.api.models.support.Support.IDENTIFIER_TYPES;
import com.project.safetychain.api.models.support.Support.RESOURCE_TYPES;
import com.project.safetychain.webapp.pageobjects.CommonPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserFormsPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPageLocators;
import com.project.safetychain.webapp.pageobjects.FSQABrowserRecordsPage;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage;
import com.project.safetychain.webapp.pageobjects.FormDesignerPageLocator;
import com.project.safetychain.webapp.pageobjects.ManageLocationPage;
import com.project.safetychain.webapp.pageobjects.ManageResourcePage;
import com.project.safetychain.webapp.pageobjects.ResourceDesignerPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.COLUMNHEADER;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.COLUMNSETTING;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FormDesignParams;
import com.project.safetychain.webapp.testcases.support.FormOperations;
import com.project.safetychain.webapp.testcases.support.FormOperations.FormDetails;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.utilities.ApiUtils;
import com.project.utilities.CommonMethods;
import com.project.utilities.ControlActions;
import com.project.utilities.ControlActions_MobileApp;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class REG_FormsAllValidation extends TestBase {

	AppiumDriver<MobileElement> appiumDriver;
	WebDriverWait wait;
	ControlActions_MobileApp mobControlActions;
	public WebDriver driver;
	ApiUtils apiUtils = null;
	FormDesignerPage formDesignerPage;
	ControlActions controlActions;
	FSQABrowserPage fbp;
	FSQABrowserFormsPage fbForms;
	CommonPage mainPage;
	FormFieldParams ffp;
	FormDesignParams fp;
	FormDetails formDetails;
	com.project.safetychain.webapp.pageobjects.HomePage hp;
	com.project.safetychain.webapp.pageobjects.LoginPage lp;
	ManageResourcePage manageResource;
	ManageLocationPage locations;
	ResourceDesignerPage resourceDesigner;
	SavedPage SavedPageObj;
	FormOperations fop;
	HomePage homePage;
	REG_FormsAllValidation afsaved;
	FSQABrowserRecordsPage fbrp;
	TCG_SMK_FormSavedFlow savedTc;
	REG_FormsAllValidationLocators afValidate;

	public static String locationCategoryValue;
	public static String locationInstanceValue1;
	public static String locationInstanceValue2;
	public static String resourceCategoryValue;
	public static String resourceInstanceValue1;
	public static String resourceInstanceValue2;
	public static String chkFreeTxt, ParaTxt, chkSelectOne, SelectMultiple, Numeric, chkDate, chkTime, chkDateTime,
			Note, selectOne2, chkDateDiff, D1, D2, selectOneID2, selectOneID1;

	public static String noteText = "Test Mobile Form", documentTypeName, documentName = "upload.png";
	public static String filePath = System.getProperty("user.dir") + "\\test-data-files\\UploadDocuments\\"
			+ documentName;

	// public static String formName = "Automation_CheckForm_15.10_17.19.19";
	// public static String resourceName = "RInst1_15.10_13.47.04";
	// public static String LocationName = "LInst1_15.10_13.47.04";
	String formtype = "Check";
	String modifiedby;
	public static String FormName;
	public static String TaskName;
	public static String WGName;

	public static LinkedHashMap<String, String> shortNamesDateDiff;

	@AndroidFindBy(id = REG_FormsAllValidationLocators.MINMAXLIM)
	public WebElement MinMaxLimit;

	@AndroidFindBy(id = REG_FormsAllValidationLocators.UOM)
	public WebElement Uom;

	@AndroidFindBy(id = REG_FormsAllValidationLocators.BACKFORM)
	public WebElement backFormBtn;
	@AndroidFindBy(id = REG_FormsAllValidationLocators.DELETEFORM)
	public WebElement deleteFormBtn;
	@AndroidFindBy(id = REG_FormsAllValidationLocators.DELETEPOPUP)
	public WebElement deletePopup;
	@AndroidFindBy(id = REG_FormsAllValidationLocators.DELETECANCEL)
	public WebElement deleteCancelBtn;
	@AndroidFindBy(id = REG_FormsAllValidationLocators.DELETEYESBTN)
	public WebElement deleteYesBtn;
	@AndroidFindBy(id = REG_FormsAllValidationLocators.DISCARDSAVEPOPUP)
	public WebElement discardSavePopupBtn;
	@AndroidFindBy(id = REG_FormsAllValidationLocators.DISCARDBTN)
	public WebElement discardPopupBtn;

	@AndroidFindBy(id = REG_FormsAllValidationLocators.SUBMITREPEATBTN)
	public WebElement submitAndRepeatBtn;

	@AndroidFindBy(id = REG_AllFormsSavedLocators.NORESULTSFOUND)
	public WebElement noResultsFound;

	@AndroidFindBy(id = UpdatedFormVersionPageLocators.FormVersion)
	public WebElement formVersion;

	@AndroidFindBy(id = REG_FormsAllValidationLocators.FIELDVALUEBOX)
	public WebElement fieldValueEnterBox;

	@AndroidFindBy(xpath = REG_FormsAllValidationLocators.FIELDLEVELIMAGE)
	public WebElement fieldLevelImage;
	@AndroidFindBy(xpath = REG_FormsAllValidationLocators.FORMLEVELIMAGE)
	public WebElement formLevelImage;
	@AndroidFindBy(id = REG_FormsAllValidationLocators.IMAGEDELETE)
	public WebElement imageDelete;
	@AndroidFindBy(id = REG_FormsAllValidationLocators.IMAGETITLE)
	public WebElement imageTitle;
	@AndroidFindBy(id = REG_FormsAllValidationLocators.IMAGEBACKBTN)
	public WebElement imageBackBtn;
	@AndroidFindBy(id = REG_FormsAllValidationLocators.IMAGE)
	public WebElement openedImage;

	@AndroidFindBy(id = REG_FormsAllValidationLocators.IMAGEDELETEPOPUPMSG)
	public WebElement imageDeletePopupMsg;
	@AndroidFindBy(id = REG_FormsAllValidationLocators.IMAGECANCELBTN)
	public WebElement imageCancelBtn;
	@AndroidFindBy(id = REG_FormsAllValidationLocators.IMAGEDELETEBTN)
	public WebElement imageDeleteBtn;

	@AndroidFindBy(id = REG_FormsAllValidationLocators.FORMNOTELABEL)
	public WebElement noteLabel;
	@AndroidFindBy(id = REG_FormsAllValidationLocators.FORMNOTEEXT)
	public WebElement noteTextPresent;
	@AndroidFindBy(id = REG_FormsAllValidationLocators.RELDOCSLABEL)
	public WebElement RelDocLabel;

	@AndroidFindBy(xpath = REG_FormsAllValidationLocators.RELATEDDOC)
	public WebElement relatedDoc;
	@AndroidFindBy(id = REG_FormsAllValidationLocators.DEFAULTVALUE)
	public WebElement freeTextDefaultVal;

	@AndroidFindBy(xpath = REG_FormsAllValidationLocators.SELECTONEVALUE)
	public WebElement selectOneValue;

	@AndroidFindBy(xpath = REG_FormsAllValidationLocators.SELECTONE2VALUE)
	public WebElement selectOne2Value;

	@AndroidFindBy(xpath = REG_FormsAllValidationLocators.SELECTONEID2VALUE)
	public WebElement selectOneID2Value;

	@AndroidFindBy(xpath = REG_FormsAllValidationLocators.FREETEXTEXPRESULT)
	public WebElement freeTextExpValue;

	@FindBy(xpath = FSQABrowserPageLocators.FORM_ROW)
	public WebElement FormRow;

	@FindBy(xpath = FSQABrowserPageLocators.CALLOUT_MNU)
	public WebElement CalloutMenu;

	@FindBy(xpath = FSQABrowserPageLocators.EDIT_FORM_MNU)
	public WebElement EditFormMenu;

	@FindBy(xpath = FormDesignerPageLocator.NOTE_DBL)
	public WebElement NoteDbl;

	@FindBy(xpath = FormDesignerPageLocator.RELATED_DOCS_DBL)
	public WebElement RelatedDocsDbl;

	@FindBy(xpath = FormDesignerPageLocator.RELEASE_FORM_PG)
	public WebElement ReleaseFormPg;

	@FindBy(xpath = FormDesignerPageLocator.HEADER_LEVEL)
	public WebElement HeaderLevel;

	@FindBy(xpath = FormDesignerPageLocator.NOTE_TXT)
	public WebElement NoteTxt;
	public static final String fresult = "com.safetychain.SCM.M2:id/resultView_num";
	@AndroidFindBy(id = fresult)
	public WebElement NTxt;

	@AndroidFindBy(id = REG_FormsAllValidationLocators.OK_BUTTON)
	public WebElement OKBTN;

	@AndroidFindBy(id = REG_FormsAllValidationLocators.OVERALL_SCORE_VALUE)
	public WebElement OverAllScoreSummaryTable;

	TCG_TaskCreation_Wrapper taskCreationWrapper = null;
	HashMap<String, String> locResMap = null;
	String formId = null;
	String dateTime = null;
	String dateSelected = null;
	public static String numericBox = "//android.widget.TextView[@text='FieldLabel']//parent::android.widget.RelativeLayout//following-sibling::android.widget.RelativeLayout//android.widget.EditText";
	public static String A51Box = "(//android.widget.TextView[@text='FieldLabel']//parent::android.widget.RelativeLayout//following-sibling::android.widget.RelativeLayout//android.widget.EditText)[1]";
	public static String A52Box = "(//android.widget.TextView[@text='FieldLabel']//parent::android.widget.RelativeLayout//following-sibling::android.widget.RelativeLayout//android.widget.EditText)[2]";
	public static String A53Box = "(//android.widget.TextView[@text='FieldLabel']//parent::android.widget.RelativeLayout//following-sibling::android.widget.RelativeLayout//android.widget.EditText)[3]";
	public static String fieldValueCheck = "//android.widget.TextView[@text='AggrgateFunction']//parent::android.widget.RelativeLayout//following-sibling::android.widget.RelativeLayout//android.widget.EditText";
	public static final String TEXTVALUE = "//android.widget.TextView[@text='ABC']//parent::android.widget.RelativeLayout//following-sibling::android.widget.RelativeLayout//android.widget.EditText";
	public static final String FIELD_VALUE = "//android.widget.TextView[@text='FIELD_VALUE *']//parent::android.widget.RelativeLayout//following-sibling::android.widget.RelativeLayout//android.widget.EditText";

	//Same Name Num Fields
	public String Field1 = "(//android.widget.TextView[@text='FieldLabel']//parent::android.widget.RelativeLayout//following-sibling::android.widget.RelativeLayout//android.widget.EditText)[1]";
	public String Field2 = "(//android.widget.TextView[@text='FieldLabel']//parent::android.widget.RelativeLayout//following-sibling::android.widget.RelativeLayout//android.widget.EditText)[2]";
	public String Field3 = "(//android.widget.TextView[@text='FieldLabel']//parent::android.widget.RelativeLayout//following-sibling::android.widget.RelativeLayout//android.widget.EditText)[3]";
	
	
	public REG_FormsAllValidation(AppiumDriver<MobileElement> driver) {
		this.appiumDriver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		mobControlActions = new ControlActions_MobileApp(this.appiumDriver);
	}

	public String groupInitN() {
		try {
			FormName = CommonMethods.dynamicText("Automation_CheckForm");
			driver = launchbrowser();
			formDesignerPage = new FormDesignerPage(driver);
			locations = new ManageLocationPage(driver);
			manageResource = new ManageResourcePage(driver);

			resourceDesigner = new ResourceDesignerPage(driver);
			mainPage = new CommonPage(driver);
			locationCategoryValue = CommonMethods.dynamicText("LCat");
			locationInstanceValue1 = CommonMethods.dynamicText("LInst1");
			locationInstanceValue2 = CommonMethods.dynamicText("LInst2");
			resourceCategoryValue = CommonMethods.dynamicText("RCat");
			resourceInstanceValue1 = CommonMethods.dynamicText("RInst1");
			resourceInstanceValue2 = CommonMethods.dynamicText("RInst2");
			System.out.println("started execution");

			lp = new com.project.safetychain.webapp.pageobjects.LoginPage(driver);
			controlActions = new ControlActions(driver);
			System.out.println("Driver loaded");

			TCG_FormCreation_Wrapper formCreationWrapper = new TCG_FormCreation_Wrapper();

			apiUtils = new ApiUtils();

			locationCategoryValue = CommonMethods.dynamicText("LCat");
			locationInstanceValue1 = CommonMethods.dynamicText("LInst1");
			locationInstanceValue2 = CommonMethods.dynamicText("LInst2");
			resourceCategoryValue = CommonMethods.dynamicText("RCat");
			resourceInstanceValue1 = CommonMethods.dynamicText("RInst1");
			resourceInstanceValue2 = CommonMethods.dynamicText("RInst2");

			// Field Names
			chkFreeTxt = "FreeText";
			ParaTxt = "Paragraph";
			chkSelectOne = "SelectOne2";
			Numeric = "Numeric";
			chkDate = "Date";
			chkTime = "Time";
			chkDateTime = "Date&Time";
			SelectMultiple = "MutiSelect";

			// API Implementation

			FormFieldParams paraTextField = new FormFieldParams();
			paraTextField.DPTFieldType = DPT_FIELD_TYPES.PARAGRAPH_TEXT;
			paraTextField.Name = "Paragraph";
			paraTextField.AllowAttachments = "true";
			// paraTextField.AllowComments = "true";

			FormFieldParams freeTextField = new FormFieldParams();
			freeTextField.DPTFieldType = DPT_FIELD_TYPES.FREE_TEXT;
			freeTextField.Name = chkFreeTxt;

			FormFieldParams numericField = new FormFieldParams();
			numericField.DPTFieldType = DPT_FIELD_TYPES.NUMERIC;
			numericField.Name = Numeric;
			numericField.AllowComments = "true";
			numericField.AllowCorrection = "true";
			numericField.ShowIsResolved = "true";
			numericField.PredefinedCorrections = Arrays.asList("OPTION - 1", "OPTION - 2");
			numericField.ShowMinMax = "true";
			numericField.defaultMin = "8";
			numericField.defaultComplianceValue = "8";

			// Select One & Multiple with 6 or more than 6 values//

			Element_Types fieldGroup2 = new Element_Types();
			fieldGroup2.ElementType = DPT_FIELD_TYPES.FIELD_GROUP;
			fieldGroup2.Name = "Select One or Multiple";

			FormFieldParams selMul1 = new FormFieldParams();
			selMul1.DPTFieldType = DPT_FIELD_TYPES.SELECT_MULTIPLE;
			selMul1.Name = "selectMultiple";
			selMul1.SelectOptions = Arrays.asList("A", "B", "C", "D");

			FormFieldParams selOne2 = new FormFieldParams();
			selOne2.DPTFieldType = DPT_FIELD_TYPES.SELECT_ONE;
			selOne2.Name = chkSelectOne;
			selOne2.SelectOptions = Arrays.asList("Opt1", "Opt2", "Opt3", "Opt4", "Opt5", "Opt6", "Opt7");

			fieldGroup2.formFieldParams = Arrays.asList(selOne2, selMul1);

			// Dependency fields //

			Element_Types fieldGroup3 = new Element_Types();
			fieldGroup3.ElementType = DPT_FIELD_TYPES.FIELD_GROUP;
			fieldGroup3.Name = "Select One";

			FormFieldParams selOne = new FormFieldParams();
			selOne.DPTFieldType = DPT_FIELD_TYPES.SELECT_ONE;
			selOne.Name = "selectOne";
			selOne.SelectOptions = Arrays.asList("Opt1", "Opt2");
			selOne.Hint = "Choose Opt1 for Date and Opt2 for DateTime";

			FormFieldParams Date1 = new FormFieldParams();
			Date1.DPTFieldType = DPT_FIELD_TYPES.DATE;
			Date1.Name = "Date";
			Date1.IsRequired = "true";
			Date1.ShowOnCOA = "true";
			Date1.DependencyRule = "if(selectOne=Opt1;Show)else(Hide)";

			FormFieldParams dateTime = new FormFieldParams();
			dateTime.DPTFieldType = DPT_FIELD_TYPES.DATE_TIME;
			dateTime.Name = chkDateTime;
			dateTime.IsRequired = "true";
			dateTime.ShowOnCOA = "true";
			dateTime.DependencyRule = "if(selectOne=Opt2;Show)else(Hide))";
			fieldGroup3.formFieldParams = Arrays.asList(selOne, Date1, dateTime);

			String formId = apiUtils.getUUID();
			// Form details
			logInfo("FormId = " + formId);
			FormParams fp = new FormParams();

			fp.FormId = formId;
			fp.FormName = FormName;
			logInfo("Form Name 2 = " + FormName);
			fp.type = DPT_FORM_TYPES.CHECK;
			fp.ResourceType = RESOURCE_TYPES.CUSTOMERS;
			fp.ResourceCategoryNm = resourceCategoryValue;
			fp.ResourceInstanceNm = resourceInstanceValue1;
			fp.formElements = Arrays.asList(numericField, freeTextField, paraTextField);
			logInfo("fp.formElements = " + fp.formElements);
			fp.SectionElements = Arrays.asList(fieldGroup2, fieldGroup3);
			logInfo("fp.SectionElements = " + fp.SectionElements);

			HashMap<String, List<String>> resourceCatMap = new HashMap<String, List<String>>();
			resourceCatMap.put(resourceCategoryValue, Arrays.asList(resourceInstanceValue1, resourceInstanceValue2));
			fp.CustomerResources = resourceCatMap;

			HashMap<String, List<String>> LocationMap = new HashMap<String, List<String>>();
			LocationMap.put(locationCategoryValue, Arrays.asList(locationInstanceValue1, locationInstanceValue2));

			List<String> userList = Arrays.asList(mobileAppUsername02, mobileAppUsername);
			formCreationWrapper.Resource_Location_CreationWrapper(resourceCatMap, LocationMap, true, userList,
					fp.ResourceType);

			formCreationWrapper.API_Wrapper_Forms(fp);

			HashMap<String, Compliance> complianceValuesMap = new HashMap<String, Compliance>();
			String resource = "Customers > " + resourceCategoryValue + " > " + resourceInstanceValue1;
			Compliance compliance = new Compliance();
			compliance.Min = "10";
			compliance.Max = "100";
			compliance.Target = "50";
			compliance.UOM = "$";
			compliance.Name = numericField.Name;
			compliance.fieldType = DPT_FIELD_TYPES.NUMERIC;
			complianceValuesMap.put(resource, compliance);
			complianceValuesMap.put("Default", compliance);
			compliance.IsDefault = "true";

			logInfo("resource List " + resource);
			logInfo("Compliance Values " + complianceValuesMap);

			FormFieldParamsCompliance ffpc = new FormFieldParamsCompliance();
			ffpc.fieldNames = Arrays.asList(numericField.Name);
			ffpc.complianceList = complianceValuesMap;
			formCreationWrapper.API_Wrapper_Forms_Compliance(fp, ffpc);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return FormName;
	}

	public String groupInitExpressionDateIdentifier() {
		try {
			FormName = CommonMethods.dynamicText("Automation_CheckForm");
			driver = launchbrowser();
			formDesignerPage = new FormDesignerPage(driver);
			locations = new ManageLocationPage(driver);
			manageResource = new ManageResourcePage(driver);

			resourceDesigner = new ResourceDesignerPage(driver);
			mainPage = new CommonPage(driver);
			locationCategoryValue = CommonMethods.dynamicText("LCat");
			locationInstanceValue1 = CommonMethods.dynamicText("LInst1");
			locationInstanceValue2 = CommonMethods.dynamicText("LInst2");
			resourceCategoryValue = CommonMethods.dynamicText("RCat");
			resourceInstanceValue1 = CommonMethods.dynamicText("RInst1");
			resourceInstanceValue2 = CommonMethods.dynamicText("RInst2");
			System.out.println("started execution");

			lp = new com.project.safetychain.webapp.pageobjects.LoginPage(driver);
			controlActions = new ControlActions(driver);
			System.out.println("Driver loaded");

			TCG_FormCreation_Wrapper formCreationWrapper = new TCG_FormCreation_Wrapper();

			apiUtils = new ApiUtils();

			locationCategoryValue = CommonMethods.dynamicText("LCat");
			locationInstanceValue1 = CommonMethods.dynamicText("LInst1");
			locationInstanceValue2 = CommonMethods.dynamicText("LInst2");
			resourceCategoryValue = CommonMethods.dynamicText("RCat");
			resourceInstanceValue1 = CommonMethods.dynamicText("RInst1");
			resourceInstanceValue2 = CommonMethods.dynamicText("RInst2");

			// Field Names
			chkFreeTxt = "FreeText";
			ParaTxt = "Paragraph";
			chkSelectOne = "SelectOne2";
			Numeric = "Numeric";
			chkDate = "Date";
			chkTime = "Time";
			chkDateTime = "Date&Time";
			SelectMultiple = "MutiSelect";
			chkDateDiff = "DateDifference";
			D1 = "Date1";
			D2 = "Date2";
			selectOneID1 = "SOID1";
			selectOneID2 = "SOID2";
			// API Implementation

			FormFieldParams Date1 = new FormFieldParams();
			Date1.DPTFieldType = DPT_FIELD_TYPES.DATE;
			Date1.Name = D1;

			FormFieldParams Date2 = new FormFieldParams();
			Date2.DPTFieldType = DPT_FIELD_TYPES.DATE;
			Date2.Name = D2;

			FormFieldParams numericField = new FormFieldParams();
			numericField.DPTFieldType = DPT_FIELD_TYPES.NUMERIC;
			numericField.Name = chkDateDiff;

			FormFieldParams freeTextField = new FormFieldParams();
			freeTextField.DPTFieldType = DPT_FIELD_TYPES.FREE_TEXT;
			freeTextField.Name = chkFreeTxt;

			FormFieldParams selID1 = new FormFieldParams();
			selID1.DPTFieldType = DPT_FIELD_TYPES.IDENTIFIER;
			selID1.Name = selectOneID1;
			selID1.identifierId = IDENTIFIER_TYPES.SELECT_ONE;
			selID1.IdentifierOption = Arrays.asList("A", "B", "C", "D");

			FormFieldParams selID2 = new FormFieldParams();
			selID2.DPTFieldType = DPT_FIELD_TYPES.IDENTIFIER;
			selID2.Name = selectOneID2;
			selID2.identifierId = IDENTIFIER_TYPES.SELECT_ONE;
			selID2.IdentifierOption = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9");

			String formId = apiUtils.getUUID();
			// Form details
			logInfo("FormId = " + formId);
			FormParams fp = new FormParams();

			fp.FormId = formId;
			fp.FormName = FormName;
			logInfo("Form Name 2 = " + FormName);
			fp.type = DPT_FORM_TYPES.CHECK;
			fp.ResourceType = RESOURCE_TYPES.CUSTOMERS;
			fp.ResourceCategoryNm = resourceCategoryValue;
			fp.ResourceInstanceNm = resourceInstanceValue1;
			fp.formElements = Arrays.asList(numericField, selID1, selID2, Date1, Date2, freeTextField);
			logInfo("fp.formElements = " + fp.formElements);

			HashMap<String, List<String>> resourceCatMap = new HashMap<String, List<String>>();
			resourceCatMap.put(resourceCategoryValue, Arrays.asList(resourceInstanceValue1, resourceInstanceValue2));
			fp.CustomerResources = resourceCatMap;

			HashMap<String, List<String>> LocationMap = new HashMap<String, List<String>>();
			LocationMap.put(locationCategoryValue, Arrays.asList(locationInstanceValue1, locationInstanceValue2));

			List<String> userList = Arrays.asList(mobileAppUsername02, mobileAppUsername);
			formCreationWrapper.Resource_Location_CreationWrapper(resourceCatMap, LocationMap, true, userList,
					fp.ResourceType);

			shortNamesDateDiff = formCreationWrapper.API_Wrapper_Forms(fp);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return FormName;
	}

	public String deleteForm() {
		try {
			FormName = CommonMethods.dynamicText("Automation_CheckForm");
			driver = launchbrowser();
			formDesignerPage = new FormDesignerPage(driver);
			locations = new ManageLocationPage(driver);
			manageResource = new ManageResourcePage(driver);

			resourceDesigner = new ResourceDesignerPage(driver);
			mainPage = new CommonPage(driver);
			// resourceCategoryValue = CommonMethods.dynamicText("Equip_Cat");

			locationCategoryValue = CommonMethods.dynamicText("LCat");
			locationInstanceValue1 = CommonMethods.dynamicText("LInst1");
			locationInstanceValue2 = CommonMethods.dynamicText("LInst2");
			resourceCategoryValue = CommonMethods.dynamicText("RCat");
			resourceInstanceValue1 = CommonMethods.dynamicText("RInst1");
			resourceInstanceValue2 = CommonMethods.dynamicText("RInst2");
			System.out.println("started execution");

			lp = new com.project.safetychain.webapp.pageobjects.LoginPage(driver);
			controlActions = new ControlActions(driver);
			System.out.println("Driver loaded");

			TCG_FormCreation_Wrapper formCreationWrapper = new TCG_FormCreation_Wrapper();

			apiUtils = new ApiUtils();

			locationCategoryValue = CommonMethods.dynamicText("LCat");
			locationInstanceValue1 = CommonMethods.dynamicText("LInst1");
			locationInstanceValue2 = CommonMethods.dynamicText("LInst2");
			resourceCategoryValue = CommonMethods.dynamicText("RCat");
			resourceInstanceValue1 = CommonMethods.dynamicText("RInst1");
			resourceInstanceValue2 = CommonMethods.dynamicText("RInst2");

			// Field Names
			chkFreeTxt = "FreeText";
			ParaTxt = "Paragraph";
			chkSelectOne = "SelectOne";
			Numeric = "Numeric";
			chkDate = "Date";
			chkTime = "Time";
			chkDateTime = "Date&Time";
			SelectMultiple = "MutiSelect";

			// API Implementation

			FormFieldParams numericField = new FormFieldParams();
			numericField.DPTFieldType = DPT_FIELD_TYPES.NUMERIC;
			numericField.Name = Numeric;

			String formId = apiUtils.getUUID();
			// Form details
			logInfo("FormId = " + formId);
			FormParams fp = new FormParams();

			fp.FormId = formId;
			fp.FormName = FormName;
			logInfo("Form Name 2 = " + FormName);
			fp.type = DPT_FORM_TYPES.CHECK;
			fp.ResourceType = RESOURCE_TYPES.CUSTOMERS;
			fp.ResourceCategoryNm = resourceCategoryValue;
			fp.ResourceInstanceNm = resourceInstanceValue1;
			fp.formElements = Arrays.asList(numericField);
			logInfo("fp.formElements = " + fp.formElements);

			HashMap<String, List<String>> resourceCatMap = new HashMap<String, List<String>>();
			resourceCatMap.put(resourceCategoryValue, Arrays.asList(resourceInstanceValue1, resourceInstanceValue2));
			fp.CustomerResources = resourceCatMap;

			HashMap<String, List<String>> LocationMap = new HashMap<String, List<String>>();
			LocationMap.put(locationCategoryValue, Arrays.asList(locationInstanceValue1, locationInstanceValue2));

			List<String> userList = Arrays.asList(mobileAppUsername02, mobileAppUsername);
			formCreationWrapper.Resource_Location_CreationWrapper(resourceCatMap, LocationMap, true, userList,
					fp.ResourceType);

			formCreationWrapper.API_Wrapper_Forms(fp);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return FormName;
	}

	public String ImageDeleteForm() {
		try {
			FormName = CommonMethods.dynamicText("Automation_CheckForm");
			driver = launchbrowser();
			formDesignerPage = new FormDesignerPage(driver);
			locations = new ManageLocationPage(driver);
			manageResource = new ManageResourcePage(driver);

			resourceDesigner = new ResourceDesignerPage(driver);
			mainPage = new CommonPage(driver);
			// resourceCategoryValue = CommonMethods.dynamicText("Equip_Cat");

			locationCategoryValue = CommonMethods.dynamicText("LCat");
			locationInstanceValue1 = CommonMethods.dynamicText("LInst1");
			locationInstanceValue2 = CommonMethods.dynamicText("LInst2");
			resourceCategoryValue = CommonMethods.dynamicText("RCat");
			resourceInstanceValue1 = CommonMethods.dynamicText("RInst1");
			resourceInstanceValue2 = CommonMethods.dynamicText("RInst2");
			System.out.println("started execution");

			lp = new com.project.safetychain.webapp.pageobjects.LoginPage(driver);
			controlActions = new ControlActions(driver);
			System.out.println("Driver loaded");

			TCG_FormCreation_Wrapper formCreationWrapper = new TCG_FormCreation_Wrapper();

			apiUtils = new ApiUtils();

			locationCategoryValue = CommonMethods.dynamicText("LCat");
			locationInstanceValue1 = CommonMethods.dynamicText("LInst1");
			locationInstanceValue2 = CommonMethods.dynamicText("LInst2");
			resourceCategoryValue = CommonMethods.dynamicText("RCat");
			resourceInstanceValue1 = CommonMethods.dynamicText("RInst1");
			resourceInstanceValue2 = CommonMethods.dynamicText("RInst2");

			// Field Names
			chkFreeTxt = "FreeText";
			ParaTxt = "Paragraph";
			chkSelectOne = "SelectOne";
			Numeric = "Numeric";
			chkDate = "Date";
			chkTime = "Time";
			chkDateTime = "Date&Time";
			SelectMultiple = "MutiSelect";

			// API Implementation

			FormFieldParams paraTextField = new FormFieldParams();
			paraTextField.DPTFieldType = DPT_FIELD_TYPES.PARAGRAPH_TEXT;
			paraTextField.Name = "Paragraph";
			paraTextField.AllowAttachments = "true";

			FormFieldParams freeTextField = new FormFieldParams();
			freeTextField.DPTFieldType = DPT_FIELD_TYPES.FREE_TEXT;
			freeTextField.Name = chkFreeTxt;

			FormFieldParams numericField = new FormFieldParams();
			numericField.DPTFieldType = DPT_FIELD_TYPES.NUMERIC;
			numericField.Name = Numeric;

			String formId = apiUtils.getUUID();
			// Form details
			logInfo("FormId = " + formId);
			FormParams fp = new FormParams();

			fp.FormId = formId;
			fp.FormName = FormName;
			logInfo("Form Name 2 = " + FormName);
			fp.type = DPT_FORM_TYPES.CHECK;
			fp.ResourceType = RESOURCE_TYPES.CUSTOMERS;
			fp.ResourceCategoryNm = resourceCategoryValue;
			fp.ResourceInstanceNm = resourceInstanceValue1;
			fp.formElements = Arrays.asList(freeTextField, paraTextField, numericField);
			logInfo("fp.formElements = " + fp.formElements);

			HashMap<String, List<String>> resourceCatMap = new HashMap<String, List<String>>();
			resourceCatMap.put(resourceCategoryValue, Arrays.asList(resourceInstanceValue1, resourceInstanceValue2));
			fp.CustomerResources = resourceCatMap;

			HashMap<String, List<String>> LocationMap = new HashMap<String, List<String>>();
			LocationMap.put(locationCategoryValue, Arrays.asList(locationInstanceValue1, locationInstanceValue2));

			List<String> userList = Arrays.asList(mobileAppUsername02, mobileAppUsername);
			formCreationWrapper.Resource_Location_CreationWrapper(resourceCatMap, LocationMap, true, userList,
					fp.ResourceType);

			formCreationWrapper.API_Wrapper_Forms(fp);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return FormName;
	}

	public void formAllValidation(String RelatedDocName)
			throws ElementNotVisibleException, InterruptedException, ParseException, AWTException {
		LoginPage loginPage = new LoginPage(appiumDriver);
		HomePage homePage = new HomePage(appiumDriver);
		UploadImage oUploadImage = new UploadImage(appiumDriver);
		SavedPage SavedPageObj = new SavedPage(appiumDriver);
		String FormName = addNoteAndRelatedDocs(RelatedDocName);

		SavedPageObj = new SavedPage(appiumDriver);

		if (mobileApp_Tenant.equals("test1.stage")) {
			boolean SSOLogin = loginPage.SSO_Login(mobileAppUsername, mobileAppPassword);
			TestValidation.IsTrue(SSOLogin, "logged into the application with User " + mobileAppUsername,
					"Failed to log in");

		} else {
			boolean login = loginPage.Login(mobileAppUsername, mobileAppPassword);
			TestValidation.IsTrue(login, "logged into the application with User " + mobileAppUsername,
					"Failed to log in");

		}

		boolean clickSubmissionSub = homePage.ClickSubMenu(homePage.saveSubMenu);
		TestValidation.IsTrue(clickSubmissionSub, "Clicked on Save subMenu Successfully",
				"Failed to click Save subMenu");

		boolean searchForm = homePage.SearchForm(FormName);
		TestValidation.IsTrue(searchForm, "Latest Created form is present in All forms",
				"Latest Created Form is not present in All forms");

		boolean clickForm = homePage.ClickForm(FormName);
		TestValidation.IsTrue(clickForm, "Clicked on Form Successfully", "Failed to click Form");

		boolean formElementsPresent = false;
		if (noteLabel.getText().equalsIgnoreCase("Note:")
				&& noteTextPresent.getText().equalsIgnoreCase("Test Mobile Form")
				&& RelDocLabel.getText().equalsIgnoreCase("Related Docs:") && (// relatedDoc.getText().equalsIgnoreCase("01.PNG")
				// ||
				relatedDoc.getText().equalsIgnoreCase(RelatedDocName))) {
			formElementsPresent = true;
			System.out.println("Note and Related Doc is present");
		}

		TestValidation.IsTrue(formElementsPresent, "Note and Related Doc is present",
				"Note and Related Doc is missing");

		boolean complianceLimit = false;
		String flComments = "Numeric Field Comments", flCorrection = "OPTION - 1";
		ControlActions_MobileApp.waitForVisibility(MinMaxLimit, 60);
		if (MinMaxLimit.getText().trim().equalsIgnoreCase("Min: 10 - Max: 100")
				&& Uom.getText().trim().equalsIgnoreCase("$")) {

			boolean txtNumeric = SavedPageObj.enterFieldValue(SavedPageObj.NumericTxt, "5");
			TestValidation.IsTrue(txtNumeric, "Numeric Field value entered successfully",
					"Failed to enter field value");
			SavedPageObj.enterFieldValue(SavedPageObj.flComments, flComments);
			appiumDriver.hideKeyboard();
			SavedPageObj.selectPreDefinedCorrectionValues("SelectOne", flCorrection);
			ControlActions_MobileApp.swipe(400, 700, 400, 100);
			SavedPageObj.selectResolvedField(SavedPageObj.flResolvedYes);

			complianceLimit = true;
		}

		TestValidation.IsTrue(complianceLimit, "complianceLimit verified succesfully",
				"Failed to verify complianceLimit");
		ControlActions_MobileApp.swipe(400, 700, 400, 100);

		boolean freeTextWithDefault = false;

		if (freeTextDefaultVal.getText().equalsIgnoreCase("Default Value")) {

			freeTextWithDefault = true;
		}

		TestValidation.IsTrue(freeTextWithDefault, "Default value for free text is present",
				"Default value for free text is not present");

		ControlActions_MobileApp.swipe(400, 700, 400, 100);
		boolean txtPara = false;

		try {
			ControlActions_MobileApp.waitForVisibility(SavedPageObj.paragraphTxt, 80);
			ControlActions_MobileApp.ClearText(SavedPageObj.paragraphTxt);
			ControlActions_MobileApp.actionEnterText(SavedPageObj.paragraphTxt, "Para");
			logInfo("Field Value has Entered");
			txtPara = true;
		} catch (Exception ex) {
			logInfo("Failed to Search resource" + ex.getMessage());
			txtPara = false;
		}

		TestValidation.IsTrue(txtPara, "Paragraph Field value entered successfully", "Failed to enter field value");

		boolean retValue = oUploadImage.CameraClick();

		TestValidation.IsTrue(retValue, "Camera Icon clicked succesfully", "Failed to click camera Icon");
		Thread.sleep(3000);
		boolean openGal = oUploadImage.OpenGallery();
		TestValidation.IsTrue(openGal, "Image gallery opened successfully", "Failed to open image gallery");
		Thread.sleep(3000);
		boolean selImage = oUploadImage.SelectImage();
		TestValidation.IsTrue(selImage, "Image selected succesfully", "Failed to select Image from gallery");
		boolean closeGal = oUploadImage.CloseGallery();

		TestValidation.IsTrue(closeGal, "Image gallery closed succesfully", "Failed to close image gallery");

		// ControlActions_MobileApp.performTabAction();

		boolean selectOneFromMultiple = false;
		if (selectOne2Value.getText().equalsIgnoreCase("Opt6")) {
			selectOneFromMultiple = true;
		}

		TestValidation.IsTrue(selectOneFromMultiple, "Select One value is already selected from dropdown",
				"Failed to select value from dropdown for Select One");
		ControlActions_MobileApp.swipe(400, 700, 400, 100);
		logInfo("Selecting multiple Values");
		boolean selectMul1 = SavedPageObj.selectOneOrMultipleFieldValues("SelectMultiple", "A");
		TestValidation.IsTrue(selectMul1, "selectMultiple value entered successfully", "Failed to enter field value");
		boolean selectMul2 = SavedPageObj.selectOneOrMultipleFieldValues("SelectMultiple", "B");
		TestValidation.IsTrue(selectMul2, "selectMultiple value entered successfully", "Failed to enter field value");
		boolean selectMul3 = SavedPageObj.selectOneOrMultipleFieldValues("SelectMultiple", "D");
		TestValidation.IsTrue(selectMul3, "selectMultiple value entered successfully", "Failed to enter field value");

		boolean selectOne2 = SavedPageObj.selectOneOrMultipleFieldValues("SelectOne", "OPT1");
		TestValidation.IsTrue(selectOne2, "selectOne value entered successfully", "Failed to enter field value");
		ControlActions_MobileApp.swipe(400, 700, 400, 100);
		ControlActions_MobileApp.swipe(400, 700, 400, 100);
		String date = ControlActions_MobileApp.AddDaystoToddaysDate(0);
		boolean depShow = false;
		ControlActions_MobileApp.waitForVisibility(homePage.datePicker, 10);
		try {
			if (homePage.dateTimePicker.isDisplayed()) {
				depShow = true;
				logInfo("DateTime field is not visible as expected");
			}

		} catch (NoSuchElementException e) {
			logInfo("DateTime field is not visible : PASS");
		} catch (Exception ex) {
			logInfo("DateTime field is not visible : PASS");
		}

		TestValidation.IsFalse(depShow, "Date option is visible and dateTime id hidden as expected",
				"Dependency rule failed : DateTime is visible");
		ControlActions_MobileApp.swipe(400, 700, 400, 100);
		boolean Sedate = SavedPageObj.clickFieldType(homePage.datePicker, date);
		TestValidation.IsTrue(Sedate, "Date Selected successfully", "Failed to select Date value");

		boolean camIcnClick = oUploadImage.CameraClick();
		Thread.sleep(2000);
		TestValidation.IsTrue(camIcnClick, "Camera Icon from Forms page clicked successfully",
				"Failed to click camera icon on Forms page");

		boolean openGallery = oUploadImage.OpenGallery();
		Thread.sleep(4000);
		TestValidation.IsTrue(openGallery, "Gallery opened successfully", "Failed to open gallery folder on Mobile");
		boolean selImageForm = oUploadImage.SelectImage();
		TestValidation.IsTrue(selImageForm, "Camera Icon from Forms page clicked successfully",
				"Failed to click camera icon on Forms page");
		boolean closeGallery = oUploadImage.CloseGallery();
		TestValidation.IsTrue(closeGallery, "Gallery closed successfully", "Failed to close Gallery");
		ControlActions_MobileApp.performTabAction();
		oUploadImage.verifySelectedImageThroughGallery();

		boolean submitForm = SavedPageObj.submitForm();
		TestValidation.IsTrue(submitForm, "Form Submitted Successfully", "Failed to Submit Form");
		logInfo("Submitted form");
		// Thread.sleep(20000);
		boolean clickSubmissionsSub = homePage.ClickSubMenu(homePage.submissionsSubMenu);
		TestValidation.IsTrue(clickSubmissionsSub, "Clicked on Submissions subMenu Successfully",
				"Failed to click Submissions subMenu");
		ControlActions_MobileApp.click(SavedPageObj.sortBtn);
		searchForm = homePage.SearchForm(FormName);
		TestValidation.IsTrue(searchForm, "Form Searched Successfully", "Failed to Search Form from Forms subMenu");

		boolean formStatus = SavedPageObj.checkFormStatusFromSubmissionsSubMenu(FormName, "RECEIVED");
		TestValidation.IsTrue(formStatus, "Verified Form Status Successfully",
				"Failed to Verify Form Status on Submissions");
		appiumDriver.hideKeyboard();
		boolean clickForm1 = SavedPageObj.clickFormFromSubmissionsScreen(FormName);
		TestValidation.IsTrue(clickForm1, "Clicked on form Successfully", "Failed to click Form on Submissions");

		boolean verifyDetails = SavedPageObj.verifyCorrectionDetailsFromSubmissionScreen(flComments, flCorrection);
		TestValidation.IsTrue(verifyDetails, "Verified Correction Details From Submissions Screen",
				"Failed to Verify Correction Details From Submissions Screen");

	}

	public void ExpressionDateDiffIdentifiers() throws InterruptedException, ParseException {
		LoginPage loginPage = new LoginPage(appiumDriver);
		HomePage homePage = new HomePage(appiumDriver);
		SavedPage SavedPageObj = new SavedPage(appiumDriver);

		String FormName = groupInitExpressionDateIdentifier();

		driver = launchbrowser();

		controlActions = new ControlActions(driver);
		controlActions.getUrl(applicationUrl);
		lp = new com.project.safetychain.webapp.pageobjects.LoginPage(driver);
		hp = lp.performLogin(adminUsername, adminPassword);
		if (hp.error) {
			TCGFailureMsg = "Could NOT login to application";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
		FormDesignerPage formDesignerPage = new FormDesignerPage(driver);
		fop = new FormOperations(driver);
		FSQABrowserPage fbp = new FSQABrowserPage(driver);
		com.project.safetychain.webapp.pageobjects.HomePage hp = new com.project.safetychain.webapp.pageobjects.HomePage(
				driver);

		boolean navigate = fbp.navigateToFSQATab("Forms");
		TestValidation.IsTrue(navigate, "Navigated to FSQABrowser>FormsTab",
				"Failed to navigate to FSQABrowser>Forms Tab");

		boolean applyfilter = fbp.openAndApplySettingsForColumn(COLUMNHEADER.FORMNAME, COLUMNSETTING.FILTER, FormName);
		TestValidation.IsTrue(applyfilter, "Applied Filter on" + COLUMNHEADER.FORMNAME,
				"Failed to apply filter on" + COLUMNHEADER.FORMNAME);

		try {
			Actions act = new Actions(driver);
			act.moveToElement(driver.findElement(By.xpath("//tbody[@role='rowgroup']//tr[1]//td[6]"))).build()
					.perform();
			threadsleep(2000);
			System.out.println("Mouse hovered");
			controlActions.waitforElementToBeDisplayed(
					driver.findElement(By.xpath("//i[contains(@class,'scs-callout-button scs-button-display')]")));
			controlActions.WaitforelementToBeClickable(
					driver.findElement(By.xpath("//i[contains(@class,'scs-callout-button scs-button-display')]")));

			driver.findElement(By.xpath("//i[contains(@class,'scs-callout-button scs-button-display')]")).click();
			System.out.println("Clicked callout successfully");
			// controlActions.clickOnElement(CalloutMenu);
			controlActions.WaitforelementToBeClickable(driver.findElement(By.xpath("//span[text()='Edit Form']")));
			threadsleep(3000);
			driver.findElement(By.xpath("//span[text()='Edit Form']")).click();
			System.out.println("Clicked Edit menu");
			threadsleep(10000);

			driver.findElement(By.xpath("//span[text()='Numeric:']")).click();
			threadsleep(3000);
			boolean dateDiffExpressionRule = formDesignerPage.dateDiffExpressionRuleForField(chkDateDiff,
					shortNamesDateDiff.get(D2), shortNamesDateDiff.get(D1));
			TestValidation.IsTrue(dateDiffExpressionRule, "Succesfully Applied Expression rule for date Difference",
					"Failed to apply expression rule to date Difference");

			driver.findElement(By.xpath("//span[text()='Free Text:']")).click();
			threadsleep(3000);
			boolean FreeTextExpressionRule = formDesignerPage.setExpressionRuleNumeric(chkDateDiff, "3",
					"Date1 and Date2 are as expected", "Date 1 and Date2 are equal");
			TestValidation.IsTrue(FreeTextExpressionRule, "Succesfully Applied Expression rule to free Text",
					"Failed to apply expression rule to free text");
			threadsleep(3000);

			boolean nxtBtnForm = formDesignerPage.clickOnNextButton(formDesignerPage.ReleaseFormPg, "Release Form");
			TestValidation.IsTrue(nxtBtnForm, "Clicked on next button succesfully", "Failed to click on next button");
			threadsleep(3000);

			boolean relForm = formDesignerPage.releaseForm(FormName);
			TestValidation.IsTrue(relForm, "Released updated form succesfully", "Failed to release updated form");
			logInfo("Form Name" + FormName + "edited");
		} catch (

		Exception e) {
			logError("Failed to edit form" + FormName + e.getMessage());
		}

		hp.clickFSQABrowserMenu();

		boolean navigate1 = fbp.navigateToFSQATab("Forms");
		TestValidation.IsTrue(navigate1, "Navigated to FSQABrowser>FormsTab",
				"Failed to navigate to FSQABrowser>Forms Tab");

		boolean applyfilter1 = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, FormName);
		TestValidation.IsTrue(applyfilter1, "Applied Filter on" + COLUMNHEADER.FORMNAME,
				"Failed to apply filter on" + COLUMNHEADER.FORMNAME);
		threadsleep(3000);

		FormDetails fd = new FormDetails();
		HashMap<String, List<String>> map1 = new HashMap<String, List<String>>();
		map1.put(selectOneID2, Arrays.asList("8"));

		fd.fieldDetails = map1;
		fd.resourceName = resourceInstanceValue1;
		fd.locationName = locationInstanceValue1;
		fd.isSubmit = false;

		boolean submit1 = fop.submitData(fd);
		TestValidation.IsTrue(submit1, "Entered data and Saved form successfully",
				"Could NOT Enter data and Save form");

		SavedPageObj = new SavedPage(appiumDriver);
		if (mobileApp_Tenant.equals("test1.stage")) {
			boolean SSOLogin = loginPage.SSO_Login(mobileAppUsername, mobileAppPassword);
			TestValidation.IsTrue(SSOLogin, "logged into the application with User " + mobileAppUsername,
					"Failed to log in");

		} else {
			boolean login = loginPage.Login(mobileAppUsername, mobileAppPassword);
			TestValidation.IsTrue(login, "logged into the application with User " + mobileAppUsername,
					"Failed to log in");

		}

		boolean clickSubmissionSub = homePage.ClickSubMenu(homePage.saveSubMenu);
		TestValidation.IsTrue(clickSubmissionSub, "Clicked on Save subMenu Successfully",
				"Failed to click Save subMenu");

		boolean searchForm = homePage.SearchForm(FormName);// FormName

		TestValidation.IsTrue(searchForm, "Latest Created form is present in All forms",
				"Latest Created Form is not present in All forms");

		boolean clickForm = homePage.ClickForm(FormName);// FormName
		TestValidation.IsTrue(clickForm, "Clicked on Form Successfully", "Failed to click Form");

		boolean selectID2 = SavedPageObj.selectOneOrMultipleFieldValues("SelectOne", "C");
		TestValidation.IsTrue(selectID2, "selectMultiple value entered successfully", "Failed to enter field value");

		ControlActions_MobileApp.swipeScreen("UP");

		boolean selectOneIDFromMultiple = false;
		if (selectOneID2Value.getText().equalsIgnoreCase("8")) {
			selectOneIDFromMultiple = true;
		}

		;
		// freeTextExpValue;

		TestValidation.IsTrue(selectOneIDFromMultiple, "Select One Identifier value is already selected from dropdown",
				"Failed to select Identifier from dropdown for Select One");
		ControlActions_MobileApp.swipe(400, 700, 400, 100);
		logInfo("Selecting multiple Values");

		boolean freeTextDefault = false;
		if (freeTextExpValue.getText().equalsIgnoreCase("Date 1 and Date2 are equal")) {
			freeTextDefault = true;
		}

		TestValidation.IsTrue(freeTextDefault, "Free text value before selecting date is as expected",
				"Free text value before selecting date is not as expected");

		String date = ControlActions_MobileApp.AddDaystoToddaysDate(0);
		String date1 = ControlActions_MobileApp.AddDaystoToddaysDate(3);
		ControlActions_MobileApp.swipe(400, 700, 400, 100);
		// ControlActions_MobileApp.swipeScreen("UP");

		ArrayList<WebElement> dates = new ArrayList<>(appiumDriver.findElementsById("resultView_dt"));

		Boolean Sedate1 = SavedPageObj.clickFieldType(dates.get(0), date);
		TestValidation.IsTrue(Sedate1, "Date1 Selected successfully", "Failed to select Date1 value");

		Boolean Sedate2 = SavedPageObj.clickFieldType(dates.get(1), date1);
		TestValidation.IsTrue(Sedate2, "Date2 Selected successfully", "Failed to select Date2 value");

		boolean freeTextAfter = false;
		if (freeTextExpValue.getText().trim().equalsIgnoreCase("Date1 and Date2 are as expected")) {
			freeTextAfter = true;
		}

		TestValidation.IsTrue(freeTextAfter, "Free text value after selecting date is as expected",
				"Free text value after selecting date is not as expected");

		ControlActions_MobileApp.ScrollIntoViewWithoutMaxSwipes("DateDifference");
		WebElement dateDiffNum = appiumDriver.findElement(By.id("resultView_num"));

		boolean dateDiff = false;
		if (dateDiffNum.getText().equalsIgnoreCase("3")) {
			dateDiff = true;
		}

		TestValidation.IsTrue(dateDiff, "Date difference expression rule validated succesfully",
				"Failed to validate Date difference expression rule");

	}

	public boolean addFormElements(String noteText, String DocumentTypeName, String DocumentName) {

		FormDesignerPage formDesignerPage = new FormDesignerPage(driver);
		try {
			threadsleep(5000);
			controlActions.WaitForAnElementToBeClickable(formDesignerPage.NoteDbl);
			controlActions.dragdrop(formDesignerPage.NoteDbl, formDesignerPage.HeaderLevel);
			controlActions.sendKeys(formDesignerPage.NoteTxt, noteText);

			if (!formDesignerPage.addRelatedDocument(DocumentTypeName, DocumentName)) {
				logError("Fail to configure 'Related Docs' field");
			}

			boolean defaultVal = false;
			controlActions.waitforElementToBeDisplayed(driver.findElement(By.xpath("//span[text()='Free Text:']")));
			System.out.println(driver.findElement(By.xpath("//span[text()='Free Text:']")).isDisplayed());
			if (driver.findElement(By.xpath("//span[text()='Free Text:']")).isDisplayed()) {
				controlActions
						.WaitForAnElementToBeClickable(driver.findElement(By.xpath("//span[text()='Free Text:']")));
				driver.findElement(By.xpath("//span[text()='Free Text:']")).click();
				threadsleep(5000);
				controlActions.actionSendKeys(driver.findElement(By.xpath("//input[@id='field-FreeText-0']")),
						"Default Value");
				threadsleep(5000);
				// System.out.println(driver.findElement(By.xpath("//input[@id='field-FreeText-0']")).getText());
				System.out
						.println(driver.findElement(By.xpath("//input[@id='field-FreeText-0']")).getAttribute("value"));
				if (driver.findElement(By.xpath("//input[@id='field-FreeText-0']")).getAttribute("value").trim()
						.equals("Default Value")) {
					defaultVal = true;
				}
			}

			TestValidation.IsTrue(defaultVal, "Set default value succesfully for free text",
					"Failed to set default value for free text");

			logInfo("Added form elements");
			return true;
		} catch (Exception e) {
			logError("Failed to add form elements" + e.getMessage());
			return false;
		}
	}

	public String addNoteAndRelatedDocs(String RelatedDocName) throws InterruptedException {
		String FormName = groupInitN();
		;

		System.out.println(resourceInstanceValue1);
		System.out.println(locationInstanceValue1);
		driver = launchbrowser();

		controlActions = new ControlActions(driver);
		controlActions.getUrl(applicationUrl);
		lp = new com.project.safetychain.webapp.pageobjects.LoginPage(driver);
		hp = lp.performLogin(adminUsername, adminPassword);
		if (hp.error) {
			TCGFailureMsg = "Could NOT login to application";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
		FormDesignerPage formDesignerPage = new FormDesignerPage(driver);
		fop = new FormOperations(driver);
		FSQABrowserPage fbp = new FSQABrowserPage(driver);
		com.project.safetychain.webapp.pageobjects.HomePage hp = new com.project.safetychain.webapp.pageobjects.HomePage(
				driver);

		boolean navigate = fbp.navigateToFSQATab("Forms");
		TestValidation.IsTrue(navigate, "Navigated to FSQABrowser>FormsTab",
				"Failed to navigate to FSQABrowser>Forms Tab");

		boolean applyfilter = fbp.openAndApplySettingsForColumn(COLUMNHEADER.FORMNAME, COLUMNSETTING.FILTER, FormName);
		TestValidation.IsTrue(applyfilter, "Applied Filter on" + COLUMNHEADER.FORMNAME,
				"Failed to apply filter on" + COLUMNHEADER.FORMNAME);

		try {
			Actions act = new Actions(driver);
			act.moveToElement(driver.findElement(By.xpath("//tbody[@role='rowgroup']//tr[1]//td[6]"))).build()
					.perform();
			threadsleep(2000);
			System.out.println("Mouse hovered");
			controlActions.waitforElementToBeDisplayed(
					driver.findElement(By.xpath("//i[contains(@class,'scs-callout-button scs-button-display')]")));
			controlActions.WaitforelementToBeClickable(
					driver.findElement(By.xpath("//i[contains(@class,'scs-callout-button scs-button-display')]")));

			driver.findElement(By.xpath("//i[contains(@class,'scs-callout-button scs-button-display')]")).click();
			System.out.println("Clicked callout successfully");
			// controlActions.clickOnElement(CalloutMenu);
			controlActions.WaitforelementToBeClickable(driver.findElement(By.xpath("//span[text()='Edit Form']")));
			threadsleep(3000);
			driver.findElement(By.xpath("//span[text()='Edit Form']")).click();
			System.out.println("Clicked Edit menu");
			threadsleep(10000);

			boolean formElements = addFormElements(noteText, "No Document Type", RelatedDocName);
			TestValidation.IsTrue(formElements, "Added note and related doc succesfully",
					"Failed to add note and Related Docs");

			boolean nxtBtnForm = formDesignerPage.clickOnNextButton(formDesignerPage.ReleaseFormPg, "Release Form");
			TestValidation.IsTrue(nxtBtnForm, "Clicked on next button succesfully", "Failed to click on next button");
			threadsleep(3000);

			boolean relForm = formDesignerPage.releaseForm(FormName);
			TestValidation.IsTrue(relForm, "Released updated form succesfully", "Failed to release updated form");
			logInfo("Form Name" + FormName + "edited");
		} catch (

		Exception e) {
			logError("Failed to edit form" + FormName + e.getMessage());
		}

		hp.clickFSQABrowserMenu();

		boolean navigate1 = fbp.navigateToFSQATab("Forms");
		TestValidation.IsTrue(navigate1, "Navigated to FSQABrowser>FormsTab",
				"Failed to navigate to FSQABrowser>Forms Tab");

		boolean applyfilter1 = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, FormName);
		TestValidation.IsTrue(applyfilter1, "Applied Filter on" + COLUMNHEADER.FORMNAME,
				"Failed to apply filter on" + COLUMNHEADER.FORMNAME);
		threadsleep(3000);

		FormDetails fd = new FormDetails();
		HashMap<String, List<String>> map1 = new HashMap<String, List<String>>();
		map1.put(chkSelectOne, Arrays.asList("Opt6"));

		fd.fieldDetails = map1;
		fd.resourceName = resourceInstanceValue1;
		fd.locationName = locationInstanceValue1;
		fd.isSubmit = false;

		boolean submit1 = fop.submitData(fd);
		TestValidation.IsTrue(submit1, "Entered data and Saved form successfully",
				"Could NOT Enter data and Save form");

		return FormName;
	}

	public void imageDeleteFieldAndFormLevel() throws InterruptedException {
		HomePage homePage = new HomePage(appiumDriver);
		UploadImage oUploadImage = new UploadImage(appiumDriver);
		SavedPage SavedPageObj = new SavedPage(appiumDriver);
		String FormName = ImageDeleteForm();
		ControlActions_MobileApp.swipeScreen("DOWN");
		Thread.sleep(10000);

		boolean searchForm = homePage.SearchForm(FormName);
		TestValidation.IsTrue(searchForm, "Latest Created form is present in All forms",
				"Latest Created Form is not present in All forms");

		boolean clickForm = homePage.ClickForm(FormName);
		TestValidation.IsTrue(clickForm, "Clicked on Form Successfully", "Failed to click Form");
		boolean searchLocation = SavedPageObj.SearchResource(locationInstanceValue1);
		TestValidation.IsTrue(searchLocation, "Location Searched Successfully",
				"Failed to Search Location from Forms subMenu");
		boolean searchResource = SavedPageObj.SearchResource(resourceInstanceValue1);
		TestValidation.IsTrue(searchResource, "Resource Searched Successfully",
				"Failed to Search Resource from Forms subMenu");

		boolean txtFT = SavedPageObj.enterFieldValue(SavedPageObj.freeText, "FreeText");
		TestValidation.IsTrue(txtFT, "Numeric Field value entered successfully", "Failed to enter field value");

		boolean txtPara = false;

		try {
			ControlActions_MobileApp.waitForVisibility(SavedPageObj.paragraphTxt, 80);
			ControlActions_MobileApp.ClearText(SavedPageObj.paragraphTxt);
			ControlActions_MobileApp.actionEnterText(SavedPageObj.paragraphTxt, "Para");
			logInfo("Field Value has Entered");
			txtPara = true;
		} catch (Exception ex) {
			logInfo("Failed to Search resource" + ex.getMessage());
			txtPara = false;
		}

		TestValidation.IsTrue(txtPara, "Paragraph Field value entered successfully", "Failed to enter field value");

		boolean retValue = oUploadImage.CameraClick();

		TestValidation.IsTrue(retValue, "Camera Icon clicked succesfully", "Failed to click camera Icon");
		Thread.sleep(3000);
		boolean openGal = oUploadImage.OpenGallery();
		TestValidation.IsTrue(openGal, "Image gallery opened successfully", "Failed to open image gallery");
		Thread.sleep(3000);
		boolean selImage = oUploadImage.SelectImage();
		TestValidation.IsTrue(selImage, "Image selected succesfully", "Failed to select Image from gallery");
		boolean closeGal = oUploadImage.CloseGallery();

		TestValidation.IsTrue(closeGal, "Image gallery closed succesfully", "Failed to close image gallery");

		ControlActions_MobileApp.performTabAction();

		boolean txtNumeric = SavedPageObj.enterFieldValue(SavedPageObj.NumericTxt, "5");
		TestValidation.IsTrue(txtNumeric, "Numeric Field value entered successfully", "Failed to enter field value");
		boolean camIcnClick = oUploadImage.CameraClick();
		Thread.sleep(2000);
		TestValidation.IsTrue(camIcnClick, "Camera Icon from Forms page clicked successfully",
				"Failed to click camera icon on Forms page");

		boolean openGallery = oUploadImage.OpenGallery();
		Thread.sleep(4000);
		TestValidation.IsTrue(openGallery, "Gallery opened successfully", "Failed to open gallery folder on Mobile");
		boolean selImageForm = oUploadImage.SelectImage();
		TestValidation.IsTrue(selImageForm, "Camera Icon from Forms page clicked successfully",
				"Failed to click camera icon on Forms page");
		boolean closeGallery = oUploadImage.CloseGallery();
		TestValidation.IsTrue(closeGallery, "Gallery closed successfully", "Failed to close Gallery");
		ControlActions_MobileApp.performTabAction();
		oUploadImage.verifySelectedImageThroughGallery();

		Thread.sleep(5000);
		boolean deletePresenceFieldLevel = OpenImageVerifyDeletePresence(fieldLevelImage);
		TestValidation.IsTrue(deletePresenceFieldLevel, "Delete option is present to delete field level image",
				"DeleteOption is not present to delete field level image");
		Thread.sleep(5000);
		deleteAttachedImage();
		Thread.sleep(3000);
		ControlActions_MobileApp.waitForVisibility(SavedPageObj.NumericTxt, 30);
		verifyDeletedImage(fieldLevelImage);
		boolean deletePresenceFormLevel = OpenImageVerifyDeletePresence(formLevelImage);
		TestValidation.IsTrue(deletePresenceFormLevel, "Delete option is present to delete form level image",
				"DeleteOption is not present to delete form level image");
		Thread.sleep(5000);
		deleteAttachedImage();
		Thread.sleep(3000);
		ControlActions_MobileApp.waitForVisibility(SavedPageObj.NumericTxt, 30);
		verifyDeletedImage(formLevelImage);
		boolean txtPara1 = false;

		try {
			ControlActions_MobileApp.waitForVisibility(SavedPageObj.paragraphTxt, 80);
			ControlActions_MobileApp.ClearText(SavedPageObj.paragraphTxt);
			ControlActions_MobileApp.actionEnterText(SavedPageObj.paragraphTxt, "Paragraph");
			logInfo("Field Value has Entered");
			txtPara1 = true;
		} catch (Exception ex) {
			logInfo("Failed to Search resource" + ex.getMessage());
			txtPara1 = false;
		}

		TestValidation.IsTrue(txtPara1, "Paragraph Field value entered successfully", "Failed to enter field value");

		boolean retValue1 = oUploadImage.CameraClick();

		TestValidation.IsTrue(retValue1, "Camera Icon clicked succesfully", "Failed to click camera Icon");
		Thread.sleep(3000);
		boolean openGal1 = oUploadImage.OpenGallery();
		TestValidation.IsTrue(openGal1, "Image gallery opened successfully", "Failed to open image gallery");
		Thread.sleep(3000);
		boolean selImage1 = oUploadImage.SelectImage();
		TestValidation.IsTrue(selImage1, "Image selected succesfully", "Failed to select Image from gallery");
		boolean closeGal1 = oUploadImage.CloseGallery();

		TestValidation.IsTrue(closeGal1, "Image gallery closed succesfully", "Failed to close image gallery");

		ControlActions_MobileApp.performTabAction();
		SavedPageObj.NumericTxt.click();
		boolean camIcnClick1 = oUploadImage.CameraClick();
		Thread.sleep(2000);
		TestValidation.IsTrue(camIcnClick1, "Camera Icon from Forms page clicked successfully",
				"Failed to click camera icon on Forms page");

		boolean openGallery1 = oUploadImage.OpenGallery();
		Thread.sleep(4000);
		TestValidation.IsTrue(openGallery1, "Gallery opened successfully", "Failed to open gallery folder on Mobile");
		boolean selImageForm1 = oUploadImage.SelectImage();
		TestValidation.IsTrue(selImageForm1, "Camera Icon from Forms page clicked successfully",
				"Failed to click camera icon on Forms page");
		boolean closeGallery1 = oUploadImage.CloseGallery();
		TestValidation.IsTrue(closeGallery1, "Gallery closed successfully", "Failed to close Gallery");
		ControlActions_MobileApp.performTabAction();
		oUploadImage.verifySelectedImageThroughGallery();

		boolean saveForm = SavedPageObj.saveForm();
		TestValidation.IsTrue(saveForm, "Form Saved Successfully", "Failed to Save Form from Forms SubTab");
		ControlActions_MobileApp.click(SavedPageObj.sortBtn);
		searchForm = homePage.SearchForm(FormName);
		TestValidation.IsTrue(searchForm, "Form Searched Successfully", "Failed to Search Form from Forms subMenu");

		boolean clickForm1 = homePage.ClickForm(FormName);
		TestValidation.IsTrue(clickForm1, "Clicked on Form Successfully", "Failed to click Form");

		Thread.sleep(5000);
		boolean deletePresenceFieldLevel1 = OpenImageVerifyDeletePresence(fieldLevelImage);
		TestValidation.IsFalse(deletePresenceFieldLevel1,
				"Delete option is not present to delete field level image as expected: PASS",
				"DeleteOption is present to delete field level image :FAIL");
		Thread.sleep(5000);
		boolean deletePresenceFormLevel1 = OpenImageVerifyDeletePresence(formLevelImage);
		TestValidation.IsFalse(deletePresenceFormLevel1,
				"Delete option is not present to delete Form level image as expected: PASS",
				"DeleteOption is present to delete form level image :FAIL");
		Thread.sleep(5000);

	}

	public boolean OpenImageVerifyDeletePresence(WebElement image) throws InterruptedException {

		boolean openImageVerify = false;
		boolean deleteOption = false;
		ControlActions_MobileApp.WaitforelementToBeClickable(image);
		image.click();
		String delete = "NO";
		try {

			ControlActions_MobileApp.waitForVisibility(imageDelete, 5);
			delete = "YES";
		}

		catch (NoSuchElementException e) {
			System.out.println(e.getMessage());
		} catch (Exception eX) {
			System.out.println(eX.getMessage());
		}
		if (!delete.equalsIgnoreCase("NO")) {

			deleteOption = true;
			if (imageTitle.isDisplayed() && imageTitle.getText().equals("Photo") && imageBackBtn.isDisplayed()) {
				System.out.println("Image title is :" + imageTitle.getText());
				System.out.println("Image Delete Button present :" + imageDelete.isDisplayed());
				System.out.println("Image Back Button present :" + imageBackBtn.isDisplayed());
				openImageVerify = true;

			}

			TestValidation.IsTrue(openImageVerify, "Image is opened and verified succesfully",
					"Failed to open and verify Image");

		}

		else {
			imageBackBtn.click();
		}

		return deleteOption;
	}

	public void deleteAttachedImage() {
		boolean imageDeleting = false;
		ControlActions_MobileApp.waitForVisibility(imageDelete, 30);
		ControlActions_MobileApp.WaitforelementToBeClickable(imageDelete);
		imageDelete.click();

		ControlActions_MobileApp.waitForVisibility(imageDeletePopupMsg, 10);

		if (imageDeletePopupMsg.getText().trim().equals("Are you sure you want to delete?")
				&& imageCancelBtn.isDisplayed() && imageCancelBtn.getText().trim().equals("CANCEL")
				&& imageDeleteBtn.isDisplayed() && imageDeleteBtn.getText().trim().equals("DELETE")) {

			System.out.println("Image delete popup message:" + imageDeletePopupMsg.getText());
			System.out.println("Image cancel button is displayed? " + imageCancelBtn.isDisplayed());
			System.out.println("Iamge cancel button Text is:" + imageCancelBtn.getText());
			System.out.println("Image delete button is displayed? " + imageDeleteBtn.isDisplayed());
			System.out.println("Image delete button Text is:" + imageDeleteBtn.getText());
			imageDeleting = true;

		}
		ControlActions_MobileApp.WaitforelementToBeClickable(imageDeleteBtn);
		imageDeleteBtn.click();
		TestValidation.IsTrue(imageDeleting, "Clicked on Image delete button succesfully",
				"Failed to click on Image delete button");
	}

	public void verifyDeletedImage(WebElement image) throws InterruptedException {
		Thread.sleep(5000);

		boolean deletedImagePresent = false;
		try {
			ControlActions_MobileApp.waitForVisibility(image, 5);

		}

		catch (NoSuchElementException e) {
			System.out.println("Deleted Image is not present as expected");
			deletedImagePresent = true;
		}

		catch (Exception ex) {
			System.out.println("Deleted Image is not present as expected");
			deletedImagePresent = true;
		}

		TestValidation.IsTrue(deletedImagePresent, "Deleted image is not present as expected :PASS",
				"Deleted Image is still present : FAIL");
	}

	public void deleteBackDiscard() throws InterruptedException {

		HomePage homePage = new HomePage(appiumDriver);
		SavedPage SavedPageObj = new SavedPage(appiumDriver);
		String FormName = deleteForm();

		ControlActions_MobileApp.swipeScreen("DOWN");
		Thread.sleep(10000);

		boolean searchForm = homePage.SearchForm(FormName);
		TestValidation.IsTrue(searchForm, "Latest Created form is present in All forms",
				"Latest Created Form is not present in All forms");

		boolean clickForm = homePage.ClickForm(FormName);
		TestValidation.IsTrue(clickForm, "Clicked on Form Successfully", "Failed to click Form");
		boolean searchLocation = SavedPageObj.SearchResource(locationInstanceValue1);
		TestValidation.IsTrue(searchLocation, "Location Searched Successfully",
				"Failed to Search Location from Forms subMenu");
		boolean searchResource = SavedPageObj.SearchResource(resourceInstanceValue1);
		TestValidation.IsTrue(searchResource, "Resource Searched Successfully",
				"Failed to Search Resource from Forms subMenu");

		boolean txtNumeric = SavedPageObj.enterFieldValue(SavedPageObj.NumericTxt, "10");
		TestValidation.IsTrue(txtNumeric, "Numeric Field value entered successfully", "Failed to enter field value");
		boolean saveForm = SavedPageObj.saveForm();
		TestValidation.IsTrue(saveForm, "Form Saved Successfully", "Failed to Save Form from Forms SubTab");
		ControlActions_MobileApp.click(SavedPageObj.sortBtn);
		searchForm = homePage.SearchForm(FormName);
		TestValidation.IsTrue(searchForm, "Form Searched Successfully", "Failed to Search Form from Forms subMenu");

		String savedOnBefore = formVersion.getText();
		boolean clickForm1 = homePage.ClickForm(FormName);
		TestValidation.IsTrue(clickForm1, "Clicked on Form Successfully", "Failed to click Form");

		boolean txtNumeric1 = SavedPageObj.enterFieldValue(SavedPageObj.NumericTxt, "15");
		TestValidation.IsTrue(txtNumeric1, "Numeric Field value entered successfully", "Failed to enter field value");

		ControlActions_MobileApp.waitForVisibility(backFormBtn, 20);
		ControlActions_MobileApp.WaitforelementToBeClickable(backFormBtn);
		backFormBtn.click();

		ControlActions_MobileApp.waitForVisibility(deletePopup, 20);
		boolean discardpopup = false;
		if (deletePopup.getText().trim().equalsIgnoreCase("Do you want to save?") && (discardSavePopupBtn.isDisplayed())
				&& discardPopupBtn.isDisplayed()) {
			discardpopup = true;
		}

		TestValidation.IsTrue(discardpopup, "Discard popup is displayed after clicking on back form button",
				"Failed to click on back form button for discard");

		ControlActions_MobileApp.WaitforelementToBeClickable(discardPopupBtn);
		discardPopupBtn.click();

		searchForm = homePage.SearchForm(FormName);
		TestValidation.IsTrue(searchForm, "Form Searched Successfully", "Failed to Search Form from Forms subMenu");

		String savedOnAfter = formVersion.getText();

		boolean clickForm2 = homePage.ClickForm(FormName);
		TestValidation.IsTrue(clickForm2, "Clicked on Form Successfully", "Failed to click Form");

		ControlActions_MobileApp.waitForVisibility(SavedPageObj.NumericTxt, 20);
		boolean discardChanges = false;
		if (SavedPageObj.NumericTxt.getText().equals("10") && (savedOnAfter.equals(savedOnBefore))) {
			discardChanges = true;
		}

		TestValidation.IsTrue(discardChanges, "Discard option validated succesfully",
				"Failed to validate discard option");

		// Discard with mobile back button

		boolean txtNumeric2 = SavedPageObj.enterFieldValue(SavedPageObj.NumericTxt, "15");
		TestValidation.IsTrue(txtNumeric2, "Numeric Field value entered successfully", "Failed to enter field value");

		appiumDriver.hideKeyboard();
		appiumDriver.navigate().back();

		ControlActions_MobileApp.waitForVisibility(deletePopup, 20);
		boolean discardpopup1 = false;
		if (deletePopup.getText().trim().equalsIgnoreCase("Do you want to save?") && (discardSavePopupBtn.isDisplayed())
				&& discardPopupBtn.isDisplayed()) {
			discardpopup1 = true;
		}

		TestValidation.IsTrue(discardpopup1, "Discard popup is displayed after clicking on back form button",
				"Failed to click on back form button for discard");

		ControlActions_MobileApp.WaitforelementToBeClickable(discardPopupBtn);
		discardPopupBtn.click();
		searchForm = homePage.SearchForm(FormName);
		TestValidation.IsTrue(searchForm, "Form Searched Successfully", "Failed to Search Form from Forms subMenu");
		String savedOnAfterWBack = formVersion.getText();
		boolean clickForm3 = homePage.ClickForm(FormName);
		TestValidation.IsTrue(clickForm3, "Clicked on Form Successfully", "Failed to click Form");

		ControlActions_MobileApp.waitForVisibility(SavedPageObj.NumericTxt, 20);
		boolean discardChanges1 = false;
		if (SavedPageObj.NumericTxt.getText().equals("10") && savedOnAfterWBack.equals(savedOnBefore)) {
			discardChanges1 = true;
		}

		TestValidation.IsTrue(discardChanges1, "Discard option by windows back button validated succesfully",
				"Failed to validate discard option by windows back button");

		// Delete form validation

		ControlActions_MobileApp.waitForVisibility(deleteFormBtn, 20);
		ControlActions_MobileApp.WaitforelementToBeClickable(deleteFormBtn);
		deleteFormBtn.click();

		ControlActions_MobileApp.waitForVisibility(deletePopup, 20);
		System.out.println("Delete popup text:" + deletePopup.getText());
		System.out.println(deleteCancelBtn.isDisplayed());
		System.out.println(deleteYesBtn.isDisplayed());
		boolean deletepopup = false;
		if (deletePopup.getText().trim().contains("Are you sure you want to delete this form?")
				&& deletePopup.getText().trim().contains("All data will be discarded.")
				&& (deleteCancelBtn.isDisplayed()) && deleteYesBtn.isDisplayed()) {
			deletepopup = true;
		}
		TestValidation.IsTrue(deletepopup, "Delete popup is displayed after clicking on delete form button",
				"Failed to click on delete form button");

		ControlActions_MobileApp.WaitforelementToBeClickable(deleteYesBtn);
		deleteYesBtn.click();

		boolean isFormPresent = false;

		try {
			ControlActions_MobileApp.waitForVisibility(formVersion, 20);
			if (formVersion.getText().contains("Deleted on")) {
				ControlActions_MobileApp.swipeScreen("DOWN");
			}
		} catch (Exception e) {
			e.getMessage();
		}
		ControlActions_MobileApp.waitForVisibility(noResultsFound, 20);
		isFormPresent = (noResultsFound.isDisplayed()
				&& (noResultsFound.getText().trim().equalsIgnoreCase("No Results Found")));

		TestValidation.IsTrue(isFormPresent, "Deleted form not present in Saved list",
				"Deleted form is present in Saved list");

	}

	public String aggregateFieldsForm() {
		try {
			dateSelected = ControlActions_MobileApp.AddDaystoToddaysDateInGivenFormat("M/dd/yyyy h:mm a", 0);
			dateTime = ControlActions_MobileApp.formatdate2(dateSelected, "M/d/YYYY h:mm a");
			FormName = CommonMethods.dynamicText("Auto_CheckForm");
			TaskName = CommonMethods.dynamicText("API_Task");
			WGName = CommonMethods.dynamicText("API_WG");

			driver = launchbrowser();

			// Field Short Names
			chkFreeTxt = "FreeText";
			ParaTxt = "Paragraph";
			chkSelectOne = "SelectOne";
			Numeric = "Numeric";
			chkDate = "Date";
			chkTime = "Time";
			chkDateTime = "Date&Time";
			SelectMultiple = "MultiSelect";

			TCG_FormCreation_Wrapper formCreationWrapper = new TCG_FormCreation_Wrapper();
			taskCreationWrapper = new TCG_TaskCreation_Wrapper();

			apiUtils = new ApiUtils();

			locationCategoryValue = CommonMethods.dynamicText("LCat");
			locationInstanceValue1 = CommonMethods.dynamicText("LInst1");
			locationInstanceValue2 = CommonMethods.dynamicText("LInst2");
			resourceCategoryValue = CommonMethods.dynamicText("RCat");
			resourceInstanceValue1 = CommonMethods.dynamicText("RInst1");
			resourceInstanceValue2 = CommonMethods.dynamicText("RInst2");

			FormFieldParams Numeric1 = new FormFieldParams();
			Numeric1.DPTFieldType = DPT_FIELD_TYPES.NUMERIC;
			Numeric1.Name = "A1";

			FormFieldParams Numeric2 = new FormFieldParams();
			Numeric2.DPTFieldType = DPT_FIELD_TYPES.NUMERIC;
			Numeric2.Name = "A2";

			FormFieldParams Numeric3 = new FormFieldParams();
			Numeric3.DPTFieldType = DPT_FIELD_TYPES.NUMERIC;
			Numeric3.Name = "A3";

			FormFieldParams Numeric4 = new FormFieldParams();
			Numeric4.DPTFieldType = DPT_FIELD_TYPES.NUMERIC;
			Numeric4.Name = "A4";

			FormFieldParams AGG2 = new FormFieldParams();
			AGG2.DPTFieldType = DPT_FIELD_TYPES.AGGREGATE;
			AGG2.Name = "Sum Range";
			AGG2.SelectedFunction = AGGREGATE_FUNC_TYPES.SUM_RANGE;
			AGG2.AGG_MIN = "5";
			AGG2.AGG_MAX = "10";
			AGG2.SelectedSourceCollection = Arrays.asList("A1", "A2", "A3", "A4");

			FormFieldParams countRange = new FormFieldParams();
			countRange.DPTFieldType = DPT_FIELD_TYPES.AGGREGATE;
			countRange.Name = "Count Range";
			countRange.SelectedFunction = AGGREGATE_FUNC_TYPES.COUNT_RANGE;
			countRange.AGG_MIN = "5";
			countRange.AGG_MAX = "20";
			countRange.SelectedSourceCollection = Arrays.asList("A1", "A2", "A3", "A4");
			// countRange.ShowHint = "Min: 5 and Max:20";

			// Section--->

			Element_Types Section2 = new Element_Types();
			Section2.ElementType = DPT_FIELD_TYPES.SECTION;
			Section2.Name = "Section2";

			FormFieldParams Numeric5 = new FormFieldParams();
			Numeric5.DPTFieldType = DPT_FIELD_TYPES.NUMERIC;
			Numeric5.Name = "A5";
			Numeric5.Repeat = "3";

			FormFieldParams AGG1 = new FormFieldParams();
			AGG1.DPTFieldType = DPT_FIELD_TYPES.AGGREGATE;
			AGG1.Name = "SUM";
			AGG1.SelectedFunction = AGGREGATE_FUNC_TYPES.SUM;
			AGG1.SelectedSource = "A5";

			FormFieldParams average = new FormFieldParams();
			average.DPTFieldType = DPT_FIELD_TYPES.AGGREGATE;
			average.Name = "Average";
			average.SelectedFunction = AGGREGATE_FUNC_TYPES.AVERAGE;
			average.SelectedSource = "A5";

			FormFieldParams median = new FormFieldParams();
			median.DPTFieldType = DPT_FIELD_TYPES.AGGREGATE;
			median.Name = "Median";
			median.SelectedFunction = AGGREGATE_FUNC_TYPES.MEDIAN;
			median.SelectedSource = "A5";

			FormFieldParams SD = new FormFieldParams();
			SD.DPTFieldType = DPT_FIELD_TYPES.AGGREGATE;
			SD.Name = "Standard Deviation";
			SD.SelectedFunction = AGGREGATE_FUNC_TYPES.STANDARD_DEVIATION;
			SD.SelectedSource = "A5";

			FormFieldParams min = new FormFieldParams();
			min.DPTFieldType = DPT_FIELD_TYPES.AGGREGATE;
			min.Name = "MIN VALUE";
			min.SelectedFunction = AGGREGATE_FUNC_TYPES.MIN_VAL;
			min.SelectedSource = "A5";

			FormFieldParams max = new FormFieldParams();
			max.DPTFieldType = DPT_FIELD_TYPES.AGGREGATE;
			max.Name = "MAX VALUE";
			max.SelectedFunction = AGGREGATE_FUNC_TYPES.MAX_VAL;
			max.SelectedSource = "A5";

			FormFieldParams range = new FormFieldParams();
			range.DPTFieldType = DPT_FIELD_TYPES.AGGREGATE;
			range.Name = "Range";
			range.SelectedFunction = AGGREGATE_FUNC_TYPES.RANGE;
			range.SelectedSource = "A5";

			Section2.formFieldParams = Arrays.asList(Numeric5, average, min, max, median, SD, range, AGG1);

			formId = apiUtils.getUUID();
			// Form details
			logInfo("FormId = " + formId);
			FormParams fp = new FormParams();
			logInfo("Form Name = " + formId);
			fp.FormId = formId;
			fp.FormName = FormName;
			logInfo("Form Name 2 = " + FormName);
			fp.type = DPT_FORM_TYPES.CHECK;
			fp.ResourceType = RESOURCE_TYPES.CUSTOMERS;

			fp.ResourceCategoryNm = resourceCategoryValue;
			fp.ResourceInstanceNm = resourceInstanceValue1;
			fp.formElements = Arrays.asList(AGG2, countRange, Numeric1, Numeric2, Numeric3, Numeric4);

			fp.SectionElements = Arrays.asList(Section2);
			logInfo("fp.formElements = " + fp.formElements);

			HashMap<String, List<String>> resourceCatMap = new HashMap<String, List<String>>();
			resourceCatMap.put(resourceCategoryValue, Arrays.asList(resourceInstanceValue1, resourceInstanceValue2));
			// fp.CustomerResources = resourceCatMap;
			fp.CustomerResources = resourceCatMap;

			HashMap<String, List<String>> LocationMap = new HashMap<String, List<String>>();
			LocationMap.put(locationCategoryValue, Arrays.asList(locationInstanceValue1, locationInstanceValue2));
			List<String> userList = Arrays.asList(mobileAppUsername02, mobileAppUsername);

			locResMap = formCreationWrapper.Resource_Location_CreationWrapper(resourceCatMap, LocationMap, true,
					userList, fp.ResourceType);
			formCreationWrapper.API_Wrapper_Forms(fp);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return FormName;
	}

	public void aggregateFieldsValidation() throws InterruptedException {

		HomePage homePage = new HomePage(appiumDriver);
		SavedPage SavedPageObj = new SavedPage(appiumDriver);

		String FormName = aggregateFieldsForm();
		ControlActions_MobileApp.swipeScreen("DOWN");
		Thread.sleep(10000);

		boolean searchForm = homePage.SearchForm(FormName);
		TestValidation.IsTrue(searchForm, "Latest Created form is present in All forms",
				"Latest Created Form is not present in All forms");

		boolean clickForm = homePage.ClickForm(FormName);
		TestValidation.IsTrue(clickForm, "Clicked on Form Successfully", "Failed to click Form");
		boolean searchLocation = SavedPageObj.SearchResource(locationInstanceValue1);
		TestValidation.IsTrue(searchLocation, "Location Searched Successfully",
				"Failed to Search Location from Forms subMenu");
		boolean searchResource = SavedPageObj.SearchResource(resourceInstanceValue1);
		TestValidation.IsTrue(searchResource, "Resource Searched Successfully",
				"Failed to Search Resource from Forms subMenu");

		// ControlActions_MobileApp.swipe(400, 700, 400, 100);
		// ControlActions_MobileApp.swipe(400, 700, 400, 100);
		boolean txtNumeric1 = enterValue("A1 *", "5", numericBox);
		;
		TestValidation.IsTrue(txtNumeric1, "Numeric Field value for A1 entered successfully",
				"Failed to enter A1 field value");

		boolean txtNumeric2 = enterValue("A2 *", "10", numericBox);
		;
		TestValidation.IsTrue(txtNumeric2, "Numeric Field value for A2 entered successfully",
				"Failed to enter A2 field value");

		boolean txtNumeric3 = enterValue("A3 *", "15", numericBox);
		;
		TestValidation.IsTrue(txtNumeric3, "Numeric Field value for A3 entered successfully",
				"Failed to enter A3 field value");
		boolean txtNumeric4 = enterValue("A4 *", "8", numericBox);
		;
		TestValidation.IsTrue(txtNumeric4, "Numeric Field value for A4 entered successfully",
				"Failed to enter A4 field value");

//		ControlActions_MobileApp.scrollIntoView("A5 *");
		boolean txtNumeric5 = enterValue("A5 *", "10", A51Box);
		;
		TestValidation.IsTrue(txtNumeric5, "Numeric Field value for A5 entered successfully",
				"Failed to enter A5 field value");
		boolean txtNumeric6 = enterValue("A5 *", "20", A52Box);
		;
		TestValidation.IsTrue(txtNumeric6, "Numeric Field value for A5 entered successfully",
				"Failed to enter A5 field value");
		boolean txtNumeric7 = enterValue("A5 *", "30", A53Box);
		;
		TestValidation.IsTrue(txtNumeric7, "Numeric Field value for A5 entered successfully",
				"Failed to enter A5 field value");

		// ControlActions_MobileApp.swipe(400, 700, 400, 100);

		SavedPageObj.submitForm();
		System.out.println("Form is submitted");
		Boolean clickSubmissions = homePage.ClickSubMenu(homePage.submissionsSubMenu);
		TestValidation.IsTrue(clickSubmissions, "Clicked on submissions subMenu Successfully",
				"Failed to click Submissions subMenu");
		ControlActions_MobileApp.click(SavedPageObj.sortBtn);
		Boolean searchForm1 = homePage.SearchForm(FormName);
		TestValidation.IsTrue(searchForm1, "form Searched Successfully",
				"Failed to search form from submissions screen");
		SavedPageObj.clickFormFromSubmissionsScreen(FormName);

		REG_SubmissionsPage SavedPageObj1 = new REG_SubmissionsPage(appiumDriver);

		// ControlActions_MobileApp.scrollIntoView("Count Range");
		//
		// boolean countRangeValue = aggrgateAutoCalculatedValueCheck("4",
		// "Count Range", "Count Range");
		// TestValidation.IsTrue(countRangeValue, "Count Range value is as
		// expected :PASS",
		// "Count Range value is as not expected : FAIL");
		// boolean sumRangeValue = aggrgateAutoCalculatedValueCheck("23", "Sum
		// Range", "Sum Range");
		// TestValidation.IsTrue(sumRangeValue, "Sum Range value is as expected
		// :PASS",
		// "Sum Range value is as not expected : FAIL");
		// boolean averageValue = aggrgateAutoCalculatedValueCheck("20",
		// "Average");
		// TestValidation.IsTrue(averageValue, "Average value is as expected
		// :PASS",
		// "Average value is as not expected : FAIL");
		// boolean minValue = aggrgateAutoCalculatedValueCheck("10", "MIN
		// VALUE");
		// TestValidation.IsTrue(minValue, "MIN VALUE is as expected :PASS",
		// "MIN VALUE is as not expected : FAIL");
		// boolean maxValue = aggrgateAutoCalculatedValueCheck("30", "MAX
		// VALUE");
		// TestValidation.IsTrue(maxValue, "MAX VALUE is as expected :PASS",
		// "MAX VALUE is as not expected : FAIL");
		//
		// boolean medianValue = aggrgateAutoCalculatedValueCheck("20",
		// "Median");
		// TestValidation.IsTrue(medianValue, "Median value is as expected
		// :PASS",
		// "Median value is as not expected : FAIL");
		// boolean SDValue = aggrgateAutoCalculatedValueCheck("10", "Standard
		// Deviation");
		// TestValidation.IsTrue(SDValue, "Standard Deviation value is as
		// expected :PASS",
		// "Standard Deviation value is as not expected : FAIL");
		//
		// boolean rangeValue = aggrgateAutoCalculatedValueCheck("20", "Range");
		// TestValidation.IsTrue(rangeValue, "Range value is as expected :PASS",
		// "Range value is as not expected : FAIL");
		//
		// boolean sumValue = aggrgateAutoCalculatedValueCheck("60", "SUM");
		// TestValidation.IsTrue(sumValue, "SUM value is as expected :PASS",
		// "SUM value is as not expected : FAIL");

		LinkedHashMap<String, String> FieldTypeValues = new LinkedHashMap<String, String>();
		FieldTypeValues.put("Sum Range", "23");
		FieldTypeValues.put("Count Range", "4");
		FieldTypeValues.put("Average", "20");
		FieldTypeValues.put("MIN VALUE", "10");
		FieldTypeValues.put("MAX VALUE", "30");
		FieldTypeValues.put("Median", "20");
		FieldTypeValues.put("Standard Deviation", "10");
		FieldTypeValues.put("Range", "20");
		FieldTypeValues.put("SUM", "60");
		boolean verifyUpdatedValue = SavedPageObj1.verifyFieldValues(FieldTypeValues);

		logInfo("Field Values Map" + FieldTypeValues);

		TestValidation.IsTrue(verifyUpdatedValue, "VERIFIED updated value for field type Numeric as " + FieldTypeValues,
				"Failed to verify updated value for field type Numeric as " + FieldTypeValues);

		driver = launchbrowser();
		fbp = new FSQABrowserPage(driver);
		controlActions = new ControlActions(driver);
		lp = new com.project.safetychain.webapp.pageobjects.LoginPage(driver);
		controlActions.getUrl(applicationUrl);
		hp = lp.performLogin(adminUsername, adminPassword);
		if (hp.error) {
			TCGFailureMsg = "Could NOT login to application";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		hp.clickFSQABrowserMenu();

		boolean navigate = fbp.selectResourceDropDownandNavigate("CUSTOMERS", "Records");
		TestValidation.IsTrue(navigate, "Navigated to FSQABrowser>RecordsTab",
				"Failed to navigate to FSQABrowser>Records Tab");

		boolean openRecord = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, FormName);
		TestValidation.IsTrue(openRecord, "OPENED record for form - " + FormName,
				"Failed to open record for form - " + FormName);

		

		FSQABrowserRecordsPage frp = new FSQABrowserRecordsPage(driver);

		HashMap<String, List<String>> WebFieldTypeValues = new HashMap<String, List<String>>();
		WebFieldTypeValues.put("A1", Arrays.asList("5"));
		WebFieldTypeValues.put("A2", Arrays.asList("10"));
		WebFieldTypeValues.put("A3", Arrays.asList("15"));
		WebFieldTypeValues.put("A4", Arrays.asList("8"));
		WebFieldTypeValues.put("A5", Arrays.asList("10"));
		WebFieldTypeValues.put("A5", Arrays.asList("20"));
		WebFieldTypeValues.put("A5", Arrays.asList("30"));
		WebFieldTypeValues.put("Sum Range", Arrays.asList("23"));
		WebFieldTypeValues.put("Count Range", Arrays.asList("4"));
		WebFieldTypeValues.put("Average", Arrays.asList("20"));
		WebFieldTypeValues.put("MIN VALUE", Arrays.asList("10"));
		WebFieldTypeValues.put("MAX VALUE", Arrays.asList("30"));
		WebFieldTypeValues.put("Median", Arrays.asList("20"));
		WebFieldTypeValues.put("Standard Deviation", Arrays.asList("10"));
		WebFieldTypeValues.put("Range", Arrays.asList("20"));
		WebFieldTypeValues.put("SUM", Arrays.asList("60"));

		;

		boolean verifyWebUpdatedValue = frp.verifyFieldValues(WebFieldTypeValues);
		TestValidation.IsTrue(verifyWebUpdatedValue, "VERIFIED updated value on Web as " + WebFieldTypeValues,
				"Failed to verify updated value on Web as " + WebFieldTypeValues);

	}

	public void decimalAggregateFieldsValidation() throws InterruptedException {

		HomePage homePage = new HomePage(appiumDriver);
		SavedPage SavedPageObj = new SavedPage(appiumDriver);
		String FormName = aggregateFieldsForm();
		ControlActions_MobileApp.swipeScreen("DOWN");
		Thread.sleep(10000);

		boolean searchForm = homePage.SearchForm(FormName);
		TestValidation.IsTrue(searchForm, "Latest Created form is present in All forms",
				"Latest Created Form is not present in All forms");

		boolean clickForm = homePage.ClickForm(FormName);
		TestValidation.IsTrue(clickForm, "Clicked on Form Successfully", "Failed to click Form");
		boolean searchLocation = SavedPageObj.SearchResource(locationInstanceValue1);
		TestValidation.IsTrue(searchLocation, "Location Searched Successfully",
				"Failed to Search Location from Forms subMenu");
		boolean searchResource = SavedPageObj.SearchResource(resourceInstanceValue1);
		TestValidation.IsTrue(searchResource, "Resource Searched Successfully",
				"Failed to Search Resource from Forms subMenu");

		// SUMRANGE : MIN :5,MAX:10
		boolean txtNumeric1 = enterValue("A1 *", "5.2", numericBox);
		;
		TestValidation.IsTrue(txtNumeric1, "Numeric Field value for A1 entered successfully",
				"Failed to enter A1 field value");

		boolean txtNumeric2 = enterValue("A2 *", "10.5", numericBox);
		;
		TestValidation.IsTrue(txtNumeric2, "Numeric Field value for A2 entered successfully",
				"Failed to enter A2 field value");

		boolean txtNumeric3 = enterValue("A3 *", "9.5", numericBox);
		;
		TestValidation.IsTrue(txtNumeric3, "Numeric Field value for A3 entered successfully",
				"Failed to enter A3 field value");
		boolean txtNumeric4 = enterValue("A4 *", "27.6", numericBox);
		;
		TestValidation.IsTrue(txtNumeric4, "Numeric Field value for A4 entered successfully",
				"Failed to enter A4 field value");

		boolean countRangeValue = aggrgateAutoCalculatedValueCheck("3", "Count Range");
		TestValidation.IsTrue(countRangeValue, "Count Range value is as expected :PASS",
				"Count Range value is as not expected : FAIL");

		boolean sumRangeValue = aggrgateAutoCalculatedValueCheck("14.7", "Sum Range");
		TestValidation.IsTrue(sumRangeValue, "Sum Range value is as expected :PASS",
				"Sum Range value is as not expected : FAIL");
		boolean txtNumeric5 = enterValue("A5 *", "32.4", A51Box);
		;
		TestValidation.IsTrue(txtNumeric5, "Numeric Field value for A5 entered successfully",
				"Failed to enter A5 field value");
		boolean txtNumeric6 = enterValue("A5 *", "65.5", A52Box);
		;
		TestValidation.IsTrue(txtNumeric6, "Numeric Field value for A5 entered successfully",
				"Failed to enter A5 field value");
		boolean txtNumeric7 = enterValue("A5 *", "88.9", A53Box);
		;
		TestValidation.IsTrue(txtNumeric7, "Numeric Field value for A5 entered successfully",
				"Failed to enter A5 field value");

		ControlActions_MobileApp.ScrollIntoViewWithoutMaxSwipes("SUM");

		boolean sumValue = aggrgateAutoCalculatedValueCheck("186.8", "SUM");
		TestValidation.IsTrue(sumValue, "SUM value is as expected :PASS", "SUM value is as not expected : FAIL");

		boolean averageValue = aggrgateAutoCalculatedValueCheck("62.266667", "Average");
		TestValidation.IsTrue(averageValue, "Average value is as expected :PASS",
				"Average value is as not expected : FAIL");
		boolean medianValue = aggrgateAutoCalculatedValueCheck("65.5", "Median");
		TestValidation.IsTrue(medianValue, "Median value is as expected :PASS",
				"Median value is as not expected : FAIL");
		boolean SDValue = aggrgateAutoCalculatedValueCheck("28.388437", "Standard Deviation");
		TestValidation.IsTrue(SDValue, "Standard Deviation value is as expected :PASS",
				"Standard Deviation value is as not expected : FAIL");
		boolean minValue = aggrgateAutoCalculatedValueCheck("32.4", "MIN VALUE");
		TestValidation.IsTrue(minValue, "MIN VALUE is as expected :PASS", "MIN VALUE is as not expected : FAIL");
		boolean maxValue = aggrgateAutoCalculatedValueCheck("88.9", "MAX VALUE");
		TestValidation.IsTrue(maxValue, "MAX VALUE is as expected :PASS", "MAX VALUE is as not expected : FAIL");

		boolean rangeValue = aggrgateAutoCalculatedValueCheck("56.5", "Range");
		TestValidation.IsTrue(rangeValue, "Range value is as expected :PASS", "Range value is as not expected : FAIL");

	}

	public boolean aggrgateAutoCalculatedValueCheck(String ExpectedValue, String AggrgateFunctionName) {
		String selectField = null;
		selectField = ControlActions_MobileApp.perform_GetElementByXPath2(fieldValueCheck, "AggrgateFunction",
				AggrgateFunctionName);

		boolean isVisible = false;
		boolean isPresent = false;
		do {
			try {
				ControlActions_MobileApp.waitForVisibility(appiumDriver.findElement(By.xpath(selectField)), 2);
				if (appiumDriver.findElement(By.xpath(selectField)).isDisplayed()) {
					isVisible = true;
				}
			} catch (NoSuchElementException e) {
				ControlActions_MobileApp.swipe(400, 700, 400, 100);
			} catch (Exception ex) {
				ControlActions_MobileApp.swipe(400, 700, 400, 100);
			}
		} while (!isVisible);
		try {

			if (appiumDriver.findElement(By.xpath(selectField)).getText().equals(ExpectedValue)) {
				isPresent = true;
			}

		} catch (Exception ex) {
			logError("Failed to Scroll " + ex.getMessage());
			System.out.println("Failed to Scroll " + ex.getMessage());
			isPresent = false;
		}
		return isPresent;
	}

	public boolean aggrgateAutoCalculatedValueCheck(String ExpectedValue, String AggrgateFunctionName,
			String visibleText) {
		String selectField = null;
		selectField = ControlActions_MobileApp.perform_GetElementByXPath2(fieldValueCheck, "AggrgateFunction",
				AggrgateFunctionName);

		boolean isPresent = false;
		ControlActions_MobileApp.scrollIntoView(visibleText);
		try {
			if (appiumDriver.findElement(By.xpath(selectField)).getText().equals(ExpectedValue)) {
				isPresent = true;
			}

		} catch (Exception ex) {
			logError("Failed to Scroll " + ex.getMessage());
			System.out.println("Failed to Scroll " + ex.getMessage());
			isPresent = false;
		}
		return isPresent;
	}

	public boolean enterValue(String FieldLabel, String OptionValue, String xpath) {

		String selectField = null;
		selectField = ControlActions_MobileApp.perform_GetElementByXPath2(xpath, "FieldLabel", FieldLabel);

		boolean isVisible = false;
		do {
			try {
				ControlActions_MobileApp.waitForVisibility(appiumDriver.findElement(By.xpath(selectField)), 2);
				if (appiumDriver.findElement(By.xpath(selectField)).isDisplayed()) {
					isVisible = true;
				}
			} catch (NoSuchElementException e) {
				ControlActions_MobileApp.swipe(400, 700, 400, 100);
			} catch (Exception ex) {
				ControlActions_MobileApp.swipe(400, 700, 400, 100);
			}
		} while (!isVisible);
		try {

			ControlActions_MobileApp.waitForVisibility(appiumDriver.findElement(By.xpath(selectField)), 80);
			ControlActions_MobileApp.actionEnterText(appiumDriver.findElement(By.xpath(selectField)), OptionValue);
			ControlActions_MobileApp.performTabAction();

			logInfo("Field Value is Entered");
			return true;

		} catch (Exception ex) {
			logError("Failed to Scroll " + ex.getMessage());
			System.out.println("Failed to Scroll " + ex.getMessage());
			return false;
		}

	}

	public void selectOneValueFromDropdown() throws InterruptedException, AWTException {
		HomePage homePage = new HomePage(appiumDriver);

		SavedPage SavedPageObj = new SavedPage(appiumDriver);
		String FormName = "SelectOneFromEight";
		//
		String resourceInstanceValue1 = " RInst1_08.07_09.58.11";
		ControlActions_MobileApp.swipeScreen("DOWN");
		Thread.sleep(5000);

		boolean searchForm = homePage.SearchForm(FormName);
		TestValidation.IsTrue(searchForm, "Latest Created form is present in All forms",
				"Latest Created Form is not present in All forms");

		boolean clickForm = homePage.ClickForm(FormName);
		TestValidation.IsTrue(clickForm, "Clicked on Form Successfully", "Failed to click Form");
		boolean searchResource = SavedPageObj.SearchResource(resourceInstanceValue1);
		TestValidation.IsTrue(searchResource, "Resource Searched Successfully",
				"Failed to Search Resource from Forms subMenu");
		selectOneValue.click();
		ControlActions_MobileApp.sendKeys(selectOneValue, "PKABC");
		Thread.sleep(3000);
		Robot robot = new Robot();
		Thread.sleep(2000);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyPress(KeyEvent.KEY_RELEASED);
		Thread.sleep(2000);

		boolean submitForm = SavedPageObj.submitForm();
		TestValidation.IsTrue(submitForm, "Form Submitted Successfully", "Failed to Submit Form");
		logInfo("Submitted form");
		// Thread.sleep(20000);
		boolean clickSubmissionsSub = homePage.ClickSubMenu(homePage.submissionsSubMenu);
		TestValidation.IsTrue(clickSubmissionsSub, "Clicked on Submissions subMenu Successfully",
				"Failed to click Submissions subMenu");
		ControlActions_MobileApp.click(SavedPageObj.sortBtn);
		searchForm = homePage.SearchForm(FormName);
		TestValidation.IsTrue(searchForm, "Form Searched Successfully", "Failed to Search Form from Forms subMenu");

	}

	public boolean enterFieldValue(String replaceTo, String FieldValue) {
		try {
			String FieldType1 = perform_GetElementByXPath2(TEXTVALUE, "ABC", replaceTo);
			WebElement FieldType = appiumDriver.findElement(By.xpath(FieldType1));
			ControlActions_MobileApp.waitForVisibility(FieldType, 80);
			ControlActions_MobileApp.ClearText(FieldType);
			ControlActions_MobileApp.actionEnterText(FieldType, FieldValue);
			// ControlActions_MobileApp.performTabAction();
			logInfo("Field Value has Entered");
			appiumDriver.hideKeyboard();
			return true;
		} catch (Exception ex) {
			logInfo("Failed to Search resource" + ex.getMessage());
			return false;
		}

	}

	public static String perform_GetElementByXPath2(String xPath, String replaceWith, String replaceTo) {

		String finalXpath = null;
		try {
			finalXpath = xPath.replaceAll(replaceWith, replaceTo);
		} catch (Exception e) {
			logError("Failed to get element with xpath - " + finalXpath + " - " + e.getMessage());
		}
		return finalXpath;
	}

	/**
	 * This method is used to click on Submit and Repeat button
	 * @author ahmed_tw
	 * @return [boolean] : True after clicking on the Save And Repeat button else false
	 */
	public boolean clickSubmitAndRepeat() {
		try {
			ControlActions_MobileApp.waitForVisibility(submitAndRepeatBtn, 100);
			ControlActions_MobileApp.WaitforelementToBeClickable(submitAndRepeatBtn);
			ControlActions_MobileApp.click(submitAndRepeatBtn);
			logInfo("Clicked Submit and Repeat Button");

			ControlActions_MobileApp.waitForVisibility(OKBTN, 100);
			ControlActions_MobileApp.WaitforelementToBeClickable(OKBTN);
			ControlActions_MobileApp.click(OKBTN);
			logInfo("Clicked OK Button");

			return true;
		} catch (Exception e) {
			logError("Failed to click Submit and Repeat Button" + e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to verify value of a field
	 * @author ahmed_tw
	 * @param fieldName [String] : Field Name
	 * @param value [String] : Value to be verified
	 * @return [boolean] : True if value of the field is verified, else false
	 */
	public boolean verifyValuesOfField(String fieldName, String value) {
		WebElement fieldElement = null;
		try {
			try {
				if (!(appiumDriver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().text(\"" + fieldName + " *" + "\").instance(0)")).isDisplayed()));
			} catch (Exception e) {
				ControlActions_MobileApp.ScrollIntoView(fieldName);
			}

			String fieldXpath = perform_GetElementByXPath2(FIELD_VALUE, "FIELD_VALUE", fieldName);
			fieldElement = appiumDriver.findElement(By.xpath(fieldXpath));
			if (fieldElement.getText().equals(value)) {
				log("Verified The Field Value =" + value);
				return true;
			}

			logInfo("Field Value is NOT Verified");
			return false;

		} catch (Exception e) {
			logError("Could Not verify The Field Value" + e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to Delete the current form which is being filled
	 * @author ahmed_tw
	 * @return [boolean] : True after clicking Delete button and then Yes on Delete Popup
	 */
	public boolean clickDeleteThenOk() {
		try {
			ControlActions_MobileApp.waitForVisibility(deleteFormBtn, 20);
			ControlActions_MobileApp.WaitforelementToBeClickable(deleteFormBtn);
			deleteFormBtn.click();

			ControlActions_MobileApp.waitForVisibility(deletePopup, 20);
			boolean deletepopup = false;
			if (deletePopup.getText().trim().contains("Are you sure you want to delete this form?")
					&& deletePopup.getText().trim().contains("All data will be discarded.")
					&& (deleteCancelBtn.isDisplayed()) && deleteYesBtn.isDisplayed()) {
				ControlActions_MobileApp.WaitforelementToBeClickable(deleteYesBtn);
				deleteYesBtn.click();
				logInfo("Deleted current Form");
				deletepopup = true;
				return deletepopup;
			}
			logInfo("Failed to Delete current Form");
			return false;
		} catch (Exception e) {
			logError("Could Not to click Delete and then Yes Button" + e.getMessage());
			return false;
		}
	}

	/**This method is used to get Points Possible which is displayed below the field  
	 * @author ahmed_tw
	 * @param fieldName [String] : Name of the field
	 * @return [String] : Displayed points possible for that field
	 */
	public String getPointsPossibleBelowField(String fieldName) {
		WebElement pointsPossible = null;
		try {
			String pointsPossibleXpath = perform_GetElementByXPath2(REG_FormsAllValidationLocators.PTS_PSBL_BELOW_FLD,
					"FIELD_NAME", fieldName);
			pointsPossible = appiumDriver.findElement(By.xpath(pointsPossibleXpath));

			System.out.println(pointsPossible.getText());
			return pointsPossible.getText();
		} catch (Exception e) {
			logError("" + e.getMessage());
			return null;
		}
	}

	/**This method is used to get Points Earned which is displayed below the field  
	 * @author ahmed_tw
	 * @param fieldName [String] : Name of the field
	 * @return [String] : Displayed points earned for that field
	 */
	public String getPointsEarnedBelowField(String fieldName) {
		WebElement pointsEarned = null;
		try {
			String pointsEarnedXpath = perform_GetElementByXPath2(REG_FormsAllValidationLocators.PTS_ERND_BELOW_FLD,
					"FIELD_NAME", fieldName);
			pointsEarned = appiumDriver.findElement(By.xpath(pointsEarnedXpath));

			System.out.println(pointsEarned.getText());
			return pointsEarned.getText();
		} catch (Exception e) {
			logError("" + e.getMessage());
			return null;
		}
	}

	/**This method is used to get Overall Score form the Summary table
	 * @author ahmed_tw
	 * @return [String] : Displayed over all score in the summary table
	 */
	public String getOverallScoreSummaryTable() {
		try {
			System.out.println(OverAllScoreSummaryTable.getText());
			return OverAllScoreSummaryTable.getText();
		} catch (Exception e) {
			logError("" + e.getMessage());
			return null;
		}
	}

	public boolean scrollToEnd() {
		try {
			driver.findElement(
					MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollToEnd(10)"));
			return true;
		} catch (InvalidSelectorException e) {
			return false;
		}
	}

	public void waitForVisibilityOfSubmitNRepeat() {
		try {
			ControlActions_MobileApp.waitForVisibility(submitAndRepeatBtn, 100);
			ControlActions_MobileApp.WaitforelementToBeClickable(submitAndRepeatBtn);
		} catch (Exception e) {
			logError("Error while waiting for visibility of Submit and Repeat" + e.getMessage());
		}
	}

	public String CountRangeAggregateFieldsForm() {
		try {
			dateSelected = ControlActions_MobileApp.AddDaystoToddaysDateInGivenFormat("M/dd/yyyy h:mm a", 0);
			dateTime = ControlActions_MobileApp.formatdate2(dateSelected, "M/d/YYYY h:mm a");
			FormName = CommonMethods.dynamicText("Auto_CheckForm");
			TaskName = CommonMethods.dynamicText("API_Task");
			WGName = CommonMethods.dynamicText("API_WG");

			driver = launchbrowser();

			// Field Short Names
			chkFreeTxt = "FreeText";
			ParaTxt = "Paragraph";
			chkSelectOne = "SelectOne";
			Numeric = "Numeric";
			chkDate = "Date";
			chkTime = "Time";
			chkDateTime = "Date&Time";
			SelectMultiple = "MultiSelect";

			TCG_FormCreation_Wrapper formCreationWrapper = new TCG_FormCreation_Wrapper();
			taskCreationWrapper = new TCG_TaskCreation_Wrapper();

			apiUtils = new ApiUtils();

			locationCategoryValue = CommonMethods.dynamicText("LCat");
			locationInstanceValue1 = CommonMethods.dynamicText("LInst1");
			locationInstanceValue2 = CommonMethods.dynamicText("LInst2");
			resourceCategoryValue = CommonMethods.dynamicText("RCat");
			resourceInstanceValue1 = CommonMethods.dynamicText("RInst1");
			resourceInstanceValue2 = CommonMethods.dynamicText("RInst2");

			FormFieldParams Numeric1 = new FormFieldParams();
			Numeric1.DPTFieldType = DPT_FIELD_TYPES.NUMERIC;
			Numeric1.Name = "A1";

			FormFieldParams Numeric2 = new FormFieldParams();
			Numeric2.DPTFieldType = DPT_FIELD_TYPES.NUMERIC;
			Numeric2.Name = "A2";

			FormFieldParams Numeric3 = new FormFieldParams();
			Numeric3.DPTFieldType = DPT_FIELD_TYPES.NUMERIC;
			Numeric3.Name = "A3";

			FormFieldParams Numeric4 = new FormFieldParams();
			Numeric4.DPTFieldType = DPT_FIELD_TYPES.NUMERIC;
			Numeric4.Name = "A4";

			FormFieldParams Numeric5 = new FormFieldParams();
			Numeric5.DPTFieldType = DPT_FIELD_TYPES.NUMERIC;
			Numeric5.Name = "A5";
			FormFieldParams Numeric6 = new FormFieldParams();
			Numeric6.DPTFieldType = DPT_FIELD_TYPES.NUMERIC;
			Numeric6.Name = "A6";
			FormFieldParams Numeric7 = new FormFieldParams();
			Numeric7.DPTFieldType = DPT_FIELD_TYPES.NUMERIC;
			Numeric7.Name = "A7";

			FormFieldParams Numeric8 = new FormFieldParams();
			Numeric8.DPTFieldType = DPT_FIELD_TYPES.NUMERIC;
			Numeric8.Name = "A8";

			FormFieldParams countRangeN = new FormFieldParams();
			countRangeN.DPTFieldType = DPT_FIELD_TYPES.AGGREGATE;
			countRangeN.Name = "NegativeCountRange";
			countRangeN.SelectedFunction = AGGREGATE_FUNC_TYPES.COUNT_RANGE;
			countRangeN.AGG_MIN = "-100";
			countRangeN.AGG_MAX = "-10";
			countRangeN.SelectedSourceCollection = Arrays.asList("A1", "A2", "A3", "A4");
			// countRangeN.ShowHint = "Min: -100 and Max-10";

			FormFieldParams countRangeP = new FormFieldParams();
			countRangeP.DPTFieldType = DPT_FIELD_TYPES.AGGREGATE;
			countRangeP.Name = "PositiveCountRange";
			countRangeP.SelectedFunction = AGGREGATE_FUNC_TYPES.COUNT_RANGE;
			countRangeP.AGG_MIN = "5";
			countRangeP.AGG_MAX = "20";
			countRangeP.SelectedSourceCollection = Arrays.asList("A5", "A6", "A7", "A8");
			// countRangeP.ShowHint = "Min: 5 and Max:20";
			//
			FormFieldParams countRangePN = new FormFieldParams();
			countRangePN.DPTFieldType = DPT_FIELD_TYPES.AGGREGATE;
			countRangePN.Name = "CountRangeNP";
			countRangePN.SelectedFunction = AGGREGATE_FUNC_TYPES.COUNT_RANGE;
			countRangePN.AGG_MIN = "-10";
			countRangePN.AGG_MAX = "10";
			countRangePN.SelectedSourceCollection = Arrays.asList("A2", "A4", "A5", "A7");
			// countRangePN.ShowHint = "Min: 5 and Max:20";
			// (A2,A4,A5,A7)

			formId = apiUtils.getUUID();
			// Form details
			logInfo("FormId = " + formId);
			FormParams fp = new FormParams();
			logInfo("Form Name = " + formId);
			fp.FormId = formId;
			fp.FormName = FormName;
			logInfo("Form Name 2 = " + FormName);
			fp.type = DPT_FORM_TYPES.CHECK;
			fp.ResourceType = RESOURCE_TYPES.CUSTOMERS;

			fp.ResourceCategoryNm = resourceCategoryValue;
			fp.ResourceInstanceNm = resourceInstanceValue1;
			fp.formElements = Arrays.asList(countRangeN, countRangeP, countRangePN, Numeric1, Numeric2, Numeric3,
					Numeric4, Numeric5, Numeric6, Numeric7, Numeric8);

			HashMap<String, List<String>> resourceCatMap = new HashMap<String, List<String>>();
			resourceCatMap.put(resourceCategoryValue, Arrays.asList(resourceInstanceValue1, resourceInstanceValue2));
			// fp.CustomerResources = resourceCatMap;
			fp.CustomerResources = resourceCatMap;

			HashMap<String, List<String>> LocationMap = new HashMap<String, List<String>>();
			LocationMap.put(locationCategoryValue, Arrays.asList(locationInstanceValue1, locationInstanceValue2));
			List<String> userList = Arrays.asList(mobileAppUsername02, mobileAppUsername);

			locResMap = formCreationWrapper.Resource_Location_CreationWrapper(resourceCatMap, LocationMap, true,
					userList, fp.ResourceType);
			formCreationWrapper.API_Wrapper_Forms(fp);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return FormName;
	}

	public String SumRangeAggregateFieldsForm() {
		try {
			dateSelected = ControlActions_MobileApp.AddDaystoToddaysDateInGivenFormat("M/dd/yyyy h:mm a", 0);
			dateTime = ControlActions_MobileApp.formatdate2(dateSelected, "M/d/YYYY h:mm a");
			FormName = CommonMethods.dynamicText("Auto_CheckForm");
			TaskName = CommonMethods.dynamicText("API_Task");
			WGName = CommonMethods.dynamicText("API_WG");

			driver = launchbrowser();

			// Field Short Names
			chkFreeTxt = "FreeText";
			ParaTxt = "Paragraph";
			chkSelectOne = "SelectOne";
			Numeric = "Numeric";
			chkDate = "Date";
			chkTime = "Time";
			chkDateTime = "Date&Time";
			SelectMultiple = "MultiSelect";

			TCG_FormCreation_Wrapper formCreationWrapper = new TCG_FormCreation_Wrapper();
			taskCreationWrapper = new TCG_TaskCreation_Wrapper();

			apiUtils = new ApiUtils();

			locationCategoryValue = CommonMethods.dynamicText("LCat");
			locationInstanceValue1 = CommonMethods.dynamicText("LInst1");
			locationInstanceValue2 = CommonMethods.dynamicText("LInst2");
			resourceCategoryValue = CommonMethods.dynamicText("RCat");
			resourceInstanceValue1 = CommonMethods.dynamicText("RInst1");
			resourceInstanceValue2 = CommonMethods.dynamicText("RInst2");

			FormFieldParams Numeric1 = new FormFieldParams();
			Numeric1.DPTFieldType = DPT_FIELD_TYPES.NUMERIC;
			Numeric1.Name = "A1";

			FormFieldParams Numeric2 = new FormFieldParams();
			Numeric2.DPTFieldType = DPT_FIELD_TYPES.NUMERIC;
			Numeric2.Name = "A2";

			FormFieldParams Numeric3 = new FormFieldParams();
			Numeric3.DPTFieldType = DPT_FIELD_TYPES.NUMERIC;
			Numeric3.Name = "A3";

			FormFieldParams Numeric4 = new FormFieldParams();
			Numeric4.DPTFieldType = DPT_FIELD_TYPES.NUMERIC;
			Numeric4.Name = "A4";

			FormFieldParams Numeric5 = new FormFieldParams();
			Numeric5.DPTFieldType = DPT_FIELD_TYPES.NUMERIC;
			Numeric5.Name = "A5";
			FormFieldParams Numeric6 = new FormFieldParams();
			Numeric6.DPTFieldType = DPT_FIELD_TYPES.NUMERIC;
			Numeric6.Name = "A6";
			FormFieldParams Numeric7 = new FormFieldParams();
			Numeric7.DPTFieldType = DPT_FIELD_TYPES.NUMERIC;
			Numeric7.Name = "A7";

			FormFieldParams Numeric8 = new FormFieldParams();
			Numeric8.DPTFieldType = DPT_FIELD_TYPES.NUMERIC;
			Numeric8.Name = "A8";

			FormFieldParams sumRangeN = new FormFieldParams();
			sumRangeN.DPTFieldType = DPT_FIELD_TYPES.AGGREGATE;
			sumRangeN.Name = "NegativeSumRange";
			sumRangeN.SelectedFunction = AGGREGATE_FUNC_TYPES.SUM_RANGE;
			sumRangeN.AGG_MIN = "-100";
			sumRangeN.AGG_MAX = "-10";
			sumRangeN.SelectedSourceCollection = Arrays.asList("A1", "A2", "A3", "A4");
			// countRangeN.ShowHint = "Min: -100 and Max-10";

			FormFieldParams sumRangeP = new FormFieldParams();
			sumRangeP.DPTFieldType = DPT_FIELD_TYPES.AGGREGATE;
			sumRangeP.Name = "PositiveSumRange";
			sumRangeP.SelectedFunction = AGGREGATE_FUNC_TYPES.SUM_RANGE;
			sumRangeP.AGG_MIN = "20";
			sumRangeP.AGG_MAX = "100";
			sumRangeP.SelectedSourceCollection = Arrays.asList("A5", "A6", "A7", "A8");
			// countRangeP.ShowHint = "Min: 5 and Max:20";
			//
			FormFieldParams sumRangePN = new FormFieldParams();
			sumRangePN.DPTFieldType = DPT_FIELD_TYPES.AGGREGATE;
			sumRangePN.Name = "SumRangeNP";
			sumRangePN.SelectedFunction = AGGREGATE_FUNC_TYPES.SUM_RANGE;
			sumRangePN.AGG_MIN = "-10";
			sumRangePN.AGG_MAX = "10";
			sumRangePN.SelectedSourceCollection = Arrays.asList("A2", "A4", "A5", "A7");
			// countRangePN.ShowHint = "Min: 5 and Max:20";
			// (A2,A4,A5,A7)

			formId = apiUtils.getUUID();
			// Form details
			logInfo("FormId = " + formId);
			FormParams fp = new FormParams();
			logInfo("Form Name = " + formId);
			fp.FormId = formId;
			fp.FormName = FormName;
			logInfo("Form Name 2 = " + FormName);
			fp.type = DPT_FORM_TYPES.CHECK;
			fp.ResourceType = RESOURCE_TYPES.CUSTOMERS;

			fp.ResourceCategoryNm = resourceCategoryValue;
			fp.ResourceInstanceNm = resourceInstanceValue1;
			fp.formElements = Arrays.asList(sumRangeN, sumRangeP, sumRangePN, Numeric1, Numeric2, Numeric3, Numeric4,
					Numeric5, Numeric6, Numeric7, Numeric8);

			HashMap<String, List<String>> resourceCatMap = new HashMap<String, List<String>>();
			resourceCatMap.put(resourceCategoryValue, Arrays.asList(resourceInstanceValue1, resourceInstanceValue2));
			// fp.CustomerResources = resourceCatMap;
			fp.CustomerResources = resourceCatMap;

			HashMap<String, List<String>> LocationMap = new HashMap<String, List<String>>();
			LocationMap.put(locationCategoryValue, Arrays.asList(locationInstanceValue1, locationInstanceValue2));
			List<String> userList = Arrays.asList(mobileAppUsername02, mobileAppUsername);

			locResMap = formCreationWrapper.Resource_Location_CreationWrapper(resourceCatMap, LocationMap, true,
					userList, fp.ResourceType);
			formCreationWrapper.API_Wrapper_Forms(fp);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return FormName;
	}

	public void PositiveNegativeSourceCountRangeValidation() throws InterruptedException {

		HomePage homePage = new HomePage(appiumDriver);
		SavedPage SavedPageObj = new SavedPage(appiumDriver);
		String FormName = CountRangeAggregateFieldsForm();
		ControlActions_MobileApp.swipeScreen("DOWN");
		Thread.sleep(10000);

		boolean searchForm = homePage.SearchForm(FormName);
		TestValidation.IsTrue(searchForm, "Latest Created form is present in All forms",
				"Latest Created Form is not present in All forms");

		boolean clickForm = homePage.ClickForm(FormName);
		TestValidation.IsTrue(clickForm, "Clicked on Form Successfully", "Failed to click Form");
		boolean searchLocation = SavedPageObj.SearchResource(locationInstanceValue1);
		TestValidation.IsTrue(searchLocation, "Location Searched Successfully",
				"Failed to Search Location from Forms subMenu");
		boolean searchResource = SavedPageObj.SearchResource(resourceInstanceValue1);
		TestValidation.IsTrue(searchResource, "Resource Searched Successfully",
				"Failed to Search Resource from Forms subMenu");

		// SUMRANGE : MIN :5,MAX:10
		boolean txtNumeric1 = enterValue("A1 *", "-5", numericBox);
		;
		TestValidation.IsTrue(txtNumeric1, "Numeric Field value for A1 entered successfully",
				"Failed to enter A1 field value");

		boolean txtNumeric2 = enterValue("A2 *", "-10", numericBox);
		;
		TestValidation.IsTrue(txtNumeric2, "Numeric Field value for A2 entered successfully",
				"Failed to enter A2 field value");

		boolean txtNumeric3 = enterValue("A3 *", "-101", numericBox);
		;
		TestValidation.IsTrue(txtNumeric3, "Numeric Field value for A3 entered successfully",
				"Failed to enter A3 field value");
		boolean txtNumeric4 = enterValue("A4 *", "-90", numericBox);
		;
		TestValidation.IsTrue(txtNumeric4, "Numeric Field value for A4 entered successfully",
				"Failed to enter A4 field value");

		boolean txtNumeric5 = enterValue("A5 *", "5", numericBox);
		;
		TestValidation.IsTrue(txtNumeric5, "Numeric Field value for A5 entered successfully",
				"Failed to enter A5 field value");

		boolean txtNumeric6 = enterValue("A6 *", "10", numericBox);
		;
		TestValidation.IsTrue(txtNumeric6, "Numeric Field value for A6 entered successfully",
				"Failed to enter A6 field value");

		boolean txtNumeric7 = enterValue("A7 *", "-7", numericBox);
		;
		TestValidation.IsTrue(txtNumeric7, "Numeric Field value for A7 entered successfully",
				"Failed to enter A7 field value");
		boolean txtNumeric8 = enterValue("A8 *", "20", numericBox);
		;
		TestValidation.IsTrue(txtNumeric8, "Numeric Field value for A8 entered successfully",
				"Failed to enter A8 field value");

		ControlActions_MobileApp.ScrollIntoViewWithoutMaxSwipes("NegativeCountRange");

		boolean countRangeValue = aggrgateAutoCalculatedValueCheck("2", "NegativeCountRange");
		TestValidation.IsTrue(countRangeValue, " Count Range value for negative source fields is as expected :PASS",
				" Count Range value for negative source fields is as not expected : FAIL");

		boolean PCountRangeValue = aggrgateAutoCalculatedValueCheck("3", "PositiveCountRange");
		TestValidation.IsTrue(PCountRangeValue, " Count Range value for positive source fields is as expected :PASS",
				"Count Range value for positive and negative source fields is as not expected : FAIL");

		boolean NPCountRangeValue = aggrgateAutoCalculatedValueCheck("3", "CountRangeNP");
		TestValidation.IsTrue(NPCountRangeValue, "Count Range value for positive and Negative source fields is as expected :PASS",
				"Count Range value for positive and negative source fields is as not expected : FAIL");

		boolean saveForm = SavedPageObj.saveForm();
		TestValidation.IsTrue(saveForm, "Form Saved Successfully", "Failed to Save Form from Forms SubTab");

		boolean clickSaveMenu = homePage.ClickSubMenu(homePage.saveSubMenu);
		TestValidation.IsTrue(clickSaveMenu, "Clicked on Save subMenu Successfully", "Failed to click Save subMenu");
		ControlActions_MobileApp.click(SavedPageObj.sortBtn);
		List<String> dateInFormat = SavedPageObj.TodaysDateinFormat("MM/dd/yyyy HH:mm z");
		searchForm = homePage.SearchForm(FormName);
		String dateSelected = ControlActions_MobileApp.AddDaystoToddaysDateInGivenFormat("M/d/YY", 0);
		logInfo("Date Field Value = " + dateSelected);
		TestValidation.IsTrue(searchForm, "Form Searched Successfully", "Failed to Search Form from Forms subMenu");
		String datespliter[] = dateInFormat.get(0).split(" ");
		String Date = datespliter[0];
		logInfo("Datesplitter[0] : " + Date);
		boolean formStatus = SavedPageObj.checkFormStatusFromSavedSubMenu(FormName, dateSelected);
		TestValidation.IsTrue(formStatus, "Verified Form Status Successfully",
				"Failed to verify Form Status on Submissions");

		String isPresent = ControlActions_MobileApp.perform_GetElementByXPath2(SavedPageLocators.SAVED_TIMESTAMP,
				"TIMESTAMP", dateSelected);

		WebElement Field = appiumDriver.findElement(By.xpath(isPresent));
		String actualTimestamp = Field.getText();
		logInfo("Actual Timestamp = " + actualTimestamp);

		SavedPageObj.clickFormFromSubmissionsScreen(FormName);
		Thread.sleep(20000);
		LinkedHashMap<String, String> FieldTypeValuesMob = new LinkedHashMap<String, String>();

		FieldTypeValuesMob.put("A1", "-5");
		FieldTypeValuesMob.put("A2", "-10");
		FieldTypeValuesMob.put("A3", "-101");
		FieldTypeValuesMob.put("A4", "-90");
		FieldTypeValuesMob.put("A5", "5");
		FieldTypeValuesMob.put("A6", "10");
		FieldTypeValuesMob.put("A7", "-7");
		FieldTypeValuesMob.put("A8", "20");
		REG_SubmissionsPage SavedPageObj1 = new REG_SubmissionsPage(appiumDriver);
		boolean verifyUpdatedValueSaved = SavedPageObj1.verifyFieldValues(FieldTypeValuesMob);

		TestValidation.IsTrue(verifyUpdatedValueSaved,
				"VERIFIED updated value for field type Numeric as " + FieldTypeValuesMob,
				"Failed to verify updated value for field type Numeric as " + FieldTypeValuesMob);

		SavedPageObj.submitForm();
		System.out.println("Form is submitted");
		Boolean clickSubmissions = homePage.ClickSubMenu(homePage.submissionsSubMenu);
		TestValidation.IsTrue(clickSubmissions, "Clicked on submissions subMenu Successfully",
				"Failed to click Submissions subMenu");
		ControlActions_MobileApp.click(SavedPageObj.sortBtn);
		Boolean searchForm1 = homePage.SearchForm(FormName);
		TestValidation.IsTrue(searchForm1, "form Searched Successfully",
				"Failed to search form from submissions screen");
		SavedPageObj.clickFormFromSubmissionsScreen(FormName);

		SavedPageObj1 = new REG_SubmissionsPage(appiumDriver);

		LinkedHashMap<String, String> FieldTypeValues = new LinkedHashMap<String, String>();
		FieldTypeValues.put("NegativeCountRange", "2");
		FieldTypeValues.put("PositiveCountRange", "3");
		FieldTypeValues.put("CountRangeNP", "3");

		boolean verifyUpdatedValue = SavedPageObj1.verifyFieldValues(FieldTypeValues);

		logInfo("Field Values Map" + FieldTypeValues);

		TestValidation.IsTrue(verifyUpdatedValue, "VERIFIED updated value for field type Numeric as " + FieldTypeValues,
				"Failed to verify updated value for field type Numeric as " + FieldTypeValues);

		driver = launchbrowser();
		fbp = new FSQABrowserPage(driver);
		controlActions = new ControlActions(driver);
		lp = new com.project.safetychain.webapp.pageobjects.LoginPage(driver);
		controlActions.getUrl(applicationUrl);
		hp = lp.performLogin(adminUsername, adminPassword);
		if (hp.error) {
			TCGFailureMsg = "Could NOT login to application";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		hp.clickFSQABrowserMenu();

		boolean navigate = fbp.selectResourceDropDownandNavigate("CUSTOMERS", "Records");
		TestValidation.IsTrue(navigate, "Navigated to FSQABrowser>RecordsTab",
				"Failed to navigate to FSQABrowser>Records Tab");

		boolean openRecord = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, FormName);
		TestValidation.IsTrue(openRecord, "OPENED record for form - " + FormName,
				"Failed to open record for form - " + FormName);

		FSQABrowserRecordsPage frp = new FSQABrowserRecordsPage(driver);

		HashMap<String, List<String>> WebFieldTypeValues = new HashMap<String, List<String>>();
		WebFieldTypeValues.put("A1", Arrays.asList("-5"));
		WebFieldTypeValues.put("A2", Arrays.asList("-10"));
		WebFieldTypeValues.put("A3", Arrays.asList("-101"));
		WebFieldTypeValues.put("A4", Arrays.asList("-90"));
		WebFieldTypeValues.put("A5", Arrays.asList("5"));
		WebFieldTypeValues.put("A6", Arrays.asList("10"));
		WebFieldTypeValues.put("A7", Arrays.asList("-7"));
		WebFieldTypeValues.put("A8", Arrays.asList("20"));
		WebFieldTypeValues.put("NegativeCountRange", Arrays.asList("2"));
		WebFieldTypeValues.put("PositiveCountRange", Arrays.asList("3"));
		WebFieldTypeValues.put("CountRangeNP", Arrays.asList("3"));
		;

		boolean verifyWebUpdatedValue = frp.verifyFieldValues(WebFieldTypeValues);
		TestValidation.IsTrue(verifyWebUpdatedValue, "VERIFIED updated value on Web as " + WebFieldTypeValues,
				"Failed to verify updated value on Web as " + WebFieldTypeValues);

	}

	public void PositiveNegativeSourceSumRangeValidation() throws InterruptedException {

		HomePage homePage = new HomePage(appiumDriver);
		SavedPage SavedPageObj = new SavedPage(appiumDriver);
		String FormName = SumRangeAggregateFieldsForm();
		ControlActions_MobileApp.swipeScreen("DOWN");
		Thread.sleep(10000);

		boolean searchForm = homePage.SearchForm(FormName);
		TestValidation.IsTrue(searchForm, "Latest Created form is present in All forms",
				"Latest Created Form is not present in All forms");

		boolean clickForm = homePage.ClickForm(FormName);
		TestValidation.IsTrue(clickForm, "Clicked on Form Successfully", "Failed to click Form");
		boolean searchLocation = SavedPageObj.SearchResource(locationInstanceValue1);
		TestValidation.IsTrue(searchLocation, "Location Searched Successfully",
				"Failed to Search Location from Forms subMenu");
		boolean searchResource = SavedPageObj.SearchResource(resourceInstanceValue1);
		TestValidation.IsTrue(searchResource, "Resource Searched Successfully",
				"Failed to Search Resource from Forms subMenu");

		// Sum Range Positive Source
		// sumRangeP.AGG_MIN = "20";
		// sumRangeP.AGG_MAX = "100";
		//
		// Sum Range PositveNegative Source
		// sumRangePN.AGG_MIN = "-10";
		// sumRangePN.AGG_MAX = "10";
		//
		// Sum Range Negative Source
		// sumRangeN.AGG_MIN = "-100";
		// sumRangeN.AGG_MAX = "-10";

		boolean txtNumeric1 = enterValue("A1 *", "-38", numericBox);
		;
		TestValidation.IsTrue(txtNumeric1, "Numeric Field value for A1 entered successfully",
				"Failed to enter A1 field value");

		boolean txtNumeric2 = enterValue("A2 *", "-1", numericBox);
		;
		TestValidation.IsTrue(txtNumeric2, "Numeric Field value for A2 entered successfully",
				"Failed to enter A2 field value");

		boolean txtNumeric3 = enterValue("A3 *", "-12", numericBox);
		;
		TestValidation.IsTrue(txtNumeric3, "Numeric Field value for A3 entered successfully",
				"Failed to enter A3 field value");
		boolean txtNumeric4 = enterValue("A4 *", "-125", numericBox);
		;
		TestValidation.IsTrue(txtNumeric4, "Numeric Field value for A4 entered successfully",
				"Failed to enter A4 field value");

		boolean txtNumeric5 = enterValue("A5 *", "10", numericBox);
		;
		TestValidation.IsTrue(txtNumeric5, "Numeric Field value for A5 entered successfully",
				"Failed to enter A5 field value");

		boolean txtNumeric6 = enterValue("A6 *", "25", numericBox);
		;
		TestValidation.IsTrue(txtNumeric6, "Numeric Field value for A6 entered successfully",
				"Failed to enter A6 field value");

		boolean txtNumeric7 = enterValue("A7 *", "101", numericBox);
		;
		TestValidation.IsTrue(txtNumeric7, "Numeric Field value for A7 entered successfully",
				"Failed to enter A7 field value");
		boolean txtNumeric8 = enterValue("A8 *", "47", numericBox);
		;
		TestValidation.IsTrue(txtNumeric8, "Numeric Field value for A8 entered successfully",
				"Failed to enter A8 field value");

		// ControlActions_MobileApp.ScrollIntoView("NegativeSumRange");
		//
		// boolean countRangeValue = aggrgateAutoCalculatedValueCheck("-50",
		// "NegativeSumRange");
		// TestValidation.IsTrue(countRangeValue, "Count Range value for
		// negative source fields is as expected :PASS",
		// "Count Range value for negative source fields is as not expected :
		// FAIL");
		//
		// boolean PCountRangeValue = aggrgateAutoCalculatedValueCheck("72",
		// "PositiveSumRange");
		// TestValidation.IsTrue(PCountRangeValue, "Count Range value for
		// positive source fields is as expected :PASS",
		// "Count Range value for positive and negative source fields is as not
		// expected : FAIL");
		//
		// boolean NPCountRangeValue = aggrgateAutoCalculatedValueCheck("9",
		// "SumRangeNP");
		// TestValidation.IsTrue(NPCountRangeValue, "Count Range value for
		// positive source fields is as expected :PASS",
		// "Count Range value for positive and negative source fields is as not
		// expected : FAIL");

		boolean saveForm = SavedPageObj.saveForm();
		TestValidation.IsTrue(saveForm, "Form Saved Successfully", "Failed to Save Form from Forms SubTab");

		boolean clickSaveMenu = homePage.ClickSubMenu(homePage.saveSubMenu);
		TestValidation.IsTrue(clickSaveMenu, "Clicked on Save subMenu Successfully", "Failed to click Save subMenu");
		ControlActions_MobileApp.click(SavedPageObj.sortBtn);
		List<String> dateInFormat = SavedPageObj.TodaysDateinFormat("MM/dd/yyyy HH:mm z");
		searchForm = homePage.SearchForm(FormName);
		String dateSelected = ControlActions_MobileApp.AddDaystoToddaysDateInGivenFormat("M/d/YY", 0);
		logInfo("Date Field Value = " + dateSelected);
		TestValidation.IsTrue(searchForm, "Form Searched Successfully", "Failed to Search Form from Forms subMenu");
		String datespliter[] = dateInFormat.get(0).split(" ");
		String Date = datespliter[0];
		logInfo("Datesplitter[0] : " + Date);
		boolean formStatus = SavedPageObj.checkFormStatusFromSavedSubMenu(FormName, dateSelected);
		TestValidation.IsTrue(formStatus, "Verified Form Status Successfully",
				"Failed to verify Form Status on Submissions");

		String isPresent = ControlActions_MobileApp.perform_GetElementByXPath2(SavedPageLocators.SAVED_TIMESTAMP,
				"TIMESTAMP", dateSelected);

		WebElement Field = appiumDriver.findElement(By.xpath(isPresent));
		String actualTimestamp = Field.getText();
		logInfo("Actual Timestamp = " + actualTimestamp);

		SavedPageObj.clickFormFromSubmissionsScreen(FormName);
		Thread.sleep(20000);
		LinkedHashMap<String, String> FieldTypeValuesMob = new LinkedHashMap<String, String>();
		FieldTypeValuesMob.put("NegativeSumRange", "-50");
		FieldTypeValuesMob.put("PositiveSumRange", "72");
		FieldTypeValuesMob.put("SumRangeNP", "9");
		FieldTypeValuesMob.put("A1", "-38");
		FieldTypeValuesMob.put("A2", "-1");
		FieldTypeValuesMob.put("A3", "-12");
		FieldTypeValuesMob.put("A4", "-125");
		FieldTypeValuesMob.put("A5", "10");
		FieldTypeValuesMob.put("A6", "25");
		FieldTypeValuesMob.put("A7", "101");
		FieldTypeValuesMob.put("A8", "47");
		REG_SubmissionsPage SavedPageObj1 = new REG_SubmissionsPage(appiumDriver);
		boolean verifyUpdatedValueSaved = SavedPageObj1.verifyFieldValues(FieldTypeValuesMob);

		TestValidation.IsTrue(verifyUpdatedValueSaved,
				"VERIFIED updated value for field type Numeric as " + FieldTypeValuesMob,
				"Failed to verify updated value for field type Numeric as " + FieldTypeValuesMob);

		SavedPageObj.submitForm();
		System.out.println("Form is submitted");
		Boolean clickSubmissions = homePage.ClickSubMenu(homePage.submissionsSubMenu);
		TestValidation.IsTrue(clickSubmissions, "Clicked on submissions subMenu Successfully",
				"Failed to click Submissions subMenu");
		ControlActions_MobileApp.click(SavedPageObj.sortBtn);
		Boolean searchForm1 = homePage.SearchForm(FormName);
		TestValidation.IsTrue(searchForm1, "form Searched Successfully",
				"Failed to search form from submissions screen");
		SavedPageObj.clickFormFromSubmissionsScreen(FormName);
 
		LinkedHashMap<String, String> FieldTypeValues = new LinkedHashMap<String, String>();
		FieldTypeValues.put("NegativeSumRange", "-50");
		FieldTypeValues.put("PositiveSumRange", "72");
		FieldTypeValues.put("SumRangeNP", "9");

		boolean verifyUpdatedValue = SavedPageObj1.verifyFieldValues(FieldTypeValues);

		logInfo("Field Values Map" + FieldTypeValues);

		TestValidation.IsTrue(verifyUpdatedValue, "VERIFIED updated value for field type Numeric as " + FieldTypeValues,
				"Failed to verify updated value for field type Numeric as " + FieldTypeValues);

		driver = launchbrowser();
		fbp = new FSQABrowserPage(driver);
		controlActions = new ControlActions(driver);
		lp = new com.project.safetychain.webapp.pageobjects.LoginPage(driver);
		controlActions.getUrl(applicationUrl);
		hp = lp.performLogin(adminUsername, adminPassword);
		if (hp.error) {
			TCGFailureMsg = "Could NOT login to application";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		hp.clickFSQABrowserMenu();

		boolean navigate = fbp.selectResourceDropDownandNavigate("CUSTOMERS", "Records");
		TestValidation.IsTrue(navigate, "Navigated to FSQABrowser>RecordsTab",
				"Failed to navigate to FSQABrowser>Records Tab");

		boolean openRecord = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, FormName);
		TestValidation.IsTrue(openRecord, "OPENED record for form - " + FormName,
				"Failed to open record for form - " + FormName);

		FSQABrowserRecordsPage frp = new FSQABrowserRecordsPage(driver);

		HashMap<String, List<String>> WebFieldTypeValues = new HashMap<String, List<String>>();
		WebFieldTypeValues.put("A1", Arrays.asList("-38"));
		WebFieldTypeValues.put("A2", Arrays.asList("-1"));
		WebFieldTypeValues.put("A3", Arrays.asList("-12"));
		WebFieldTypeValues.put("A4", Arrays.asList("-125"));
		WebFieldTypeValues.put("A5", Arrays.asList("10"));
		WebFieldTypeValues.put("A6", Arrays.asList("25"));
		WebFieldTypeValues.put("A7", Arrays.asList("101"));
		WebFieldTypeValues.put("A8", Arrays.asList("47"));
		WebFieldTypeValues.put("NegativeSumRange", Arrays.asList("-50"));
		WebFieldTypeValues.put("PositiveSumRange", Arrays.asList("72"));
		WebFieldTypeValues.put("SumRangeNP", Arrays.asList("9"));
		;

		boolean verifyWebUpdatedValue = frp.verifyFieldValues(WebFieldTypeValues);
		TestValidation.IsTrue(verifyWebUpdatedValue, "VERIFIED updated value on Web as " + WebFieldTypeValues,
				"Failed to verify updated value on Web as " + WebFieldTypeValues);

	}

	public boolean enterIdentifierFieldValue(String replaceTo, String FieldValue) {
		try {
			String FieldType1 = perform_GetElementByXPath2(TEXTVALUE, "ABC", replaceTo);
			WebElement FieldType = appiumDriver.findElement(By.xpath(FieldType1));
			ControlActions_MobileApp.waitForVisibility(FieldType, 80);
			ControlActions_MobileApp.actionEnterText(FieldType, FieldValue);
			logInfo("Field Value has Entered");
			appiumDriver.hideKeyboard();
			return true;
		} catch (Exception ex) {
			logInfo("Failed to Search resource" + ex.getMessage());
			return false;
		}
	}
}