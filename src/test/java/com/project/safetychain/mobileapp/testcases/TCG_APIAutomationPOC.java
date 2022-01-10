package com.project.safetychain.mobileapp.testcases;
 
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement; 
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.project.safetychain.api.models.support.Support.Compliance;
import com.project.safetychain.api.models.support.Support.DPT_FIELD_TYPES;
import com.project.safetychain.api.models.support.Support.DPT_FORM_TYPES;
import com.project.safetychain.api.models.support.Support.FormFieldParams;
import com.project.safetychain.api.models.support.Support.FormFieldParamsCompliance;
import com.project.safetychain.api.models.support.Support.FormParams;
import com.project.safetychain.api.models.support.Support.RESOURCE_TYPES;
import com.project.safetychain.api.models.support.TCG_FormCreation_Wrapper;
import com.project.safetychain.api.models.support.TCG_FormSubmission_Wrapper; 
import com.project.safetychain.mobileapp.pageobjects.SavedPage;
import com.project.safetychain.webapp.pageobjects.CommonPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserFormsPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage; 
import com.project.safetychain.webapp.pageobjects.FormDesignerPage; 
import com.project.safetychain.webapp.pageobjects.ManageLocationPage;
import com.project.safetychain.webapp.pageobjects.ManageResourcePage;
import com.project.safetychain.webapp.pageobjects.ResourceDesignerPage;
import com.project.safetychain.webapp.testcases.support.FormOperations;
import com.project.safetychain.webapp.testcases.support.FormOperations.FormDetails;
import com.project.testbase.TestBase; 
import com.project.utilities.ApiUtils;
import com.project.utilities.CommonMethods;
import com.project.utilities.ControlActions;
import com.project.utilities.ControlActions_MobileApp;

import io.appium.java_client.pagefactory.AndroidFindBy;

public class TCG_APIAutomationPOC extends TestBase {
	ControlActions_MobileApp mobControlActions;
	ApiUtils apiUtils = null;

	FormDesignerPage formDesignerPage;
	ControlActions controlActions;
	FSQABrowserPage fbp;
	FSQABrowserFormsPage fbForms;
	CommonPage mainPage;
	FormFieldParams ffp; 
	FormDetails formDetails;
	com.project.safetychain.webapp.pageobjects.HomePage hp;
	com.project.safetychain.webapp.pageobjects.LoginPage lp;
	ManageResourcePage manageResource;
	ManageLocationPage locations;
	ResourceDesignerPage resourceDesigner;
	SavedPage SavedPageObj;
	FormOperations fop;

	public static String locationCategoryValue;
	public static String locationInstanceValue1;
	public static String locationInstanceValue2;
	public static String resourceCategoryValue;
	public static String resourceInstanceValue1;
	public static String resourceInstanceValue2;
	public static String chkFreeTxt, ParaTxt, chkSelectOne, SelectMultiple, Numeric, chkDate, chkTime, chkDateTime;

	public static String formName = "Automation_CheckForm_17.12_14.17.08";
	public static String ResourceName = "RInst2_17.12_14.17.12";
	public static String LocationName = "LInst1_17.12_14.17.12";
	String formtype = "Check";
	String modifiedby;
	public static String FormName;
	public static String TaskName;
	public static String WGName;
	public static String fieldMin = "1", fieldTar = "2", fieldMax = "3", fieldUOM = "KG";
	public static String flComments = "Numeric Field Comments", flCorrection = "OPTION - 1";

	public static final String fresult = "com.safetychain.SCM.M2:id/resultView_num";
	@AndroidFindBy(id = fresult)
	public WebElement NTxt;
	FormParams fp;

	@BeforeClass(alwaysRun = true)
	public void groupInit() {
		try {
			FormName = CommonMethods.dynamicText("API_Form");
			TaskName = CommonMethods.dynamicText("API_Task");
			WGName = CommonMethods.dynamicText("API_WG");

			TCG_FormCreation_Wrapper formCreationWrapper = new TCG_FormCreation_Wrapper(); 

			apiUtils = new ApiUtils();

			locationCategoryValue = CommonMethods.dynamicText("LCat");
			locationInstanceValue1 = CommonMethods.dynamicText("LInst1");
			locationInstanceValue2 = CommonMethods.dynamicText("LInst2");
			resourceCategoryValue = CommonMethods.dynamicText("RCat");
			resourceInstanceValue1 = CommonMethods.dynamicText("RInst1");
			resourceInstanceValue2 = CommonMethods.dynamicText("RInst2");

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
			numericField.AllowComments = "true";
			numericField.AllowCorrection = "true";

			numericField.ShowIsResolved = "true";
			numericField.PredefinedCorrections = Arrays.asList("OPTION - 1", "OPTION - 2");
			numericField.Value="10000";
			// public List<String> SelectOptions;

			FormFieldParams paraTextField = new FormFieldParams();
			paraTextField.DPTFieldType = DPT_FIELD_TYPES.PARAGRAPH_TEXT;
			paraTextField.Name = ParaTxt;
			paraTextField.Value="Paragraph Text Value has Entered through API !#@#%^&**())";

			FormFieldParams freeTextField = new FormFieldParams();
			freeTextField.DPTFieldType = DPT_FIELD_TYPES.FREE_TEXT;
			freeTextField.Name = chkFreeTxt;
			freeTextField.Value="Free Text Value Entered through API";

			FormFieldParams selectOneField = new FormFieldParams();
			selectOneField.DPTFieldType = DPT_FIELD_TYPES.SELECT_ONE;
			selectOneField.Name = chkSelectOne;
			selectOneField.SelectOptions = Arrays.asList("GOOD", "BAD");
			selectOneField.Value="GOOD";

			FormFieldParams dateField = new FormFieldParams();
			dateField.DPTFieldType = DPT_FIELD_TYPES.DATE;
			dateField.Name = chkDate;
			dateField.Value="4/21/2021";

			FormFieldParams dateTimeField = new FormFieldParams();
			dateTimeField.DPTFieldType = DPT_FIELD_TYPES.DATE_TIME;
			dateTimeField.Name = chkDateTime;			
			dateTimeField.Value="4/14/2021 12:00 AM";
			
			FormFieldParams selectMultipleField = new FormFieldParams();
			selectMultipleField.DPTFieldType = DPT_FIELD_TYPES.SELECT_MULTIPLE;
			selectMultipleField.Name = SelectMultiple;
			selectMultipleField.SelectOptions = Arrays.asList("10", "20", "30");
			selectMultipleField.Value="30";

			String formId = apiUtils.getUUID();
			// Form details
			logInfo("FormId = " + formId);
			 fp = new FormParams();
			logInfo("Form Name = " + formId);
			fp.FormId = formId;
			fp.FormName = FormName;
			logInfo("Form Name 2 = " + FormName);
			fp.type = DPT_FORM_TYPES.CHECK;
			fp.ResourceType = RESOURCE_TYPES.CUSTOMERS;

			fp.ResourceCategoryNm = resourceCategoryValue;
			fp.ResourceInstanceNm = resourceInstanceValue1;
			fp.LocationInstanceNm=locationInstanceValue1;
			fp.formElements = Arrays.asList(numericField, paraTextField, freeTextField, selectOneField, dateField,
					dateTimeField, selectMultipleField);
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

			logInfo("resource List " + resource);
			logInfo("Compliance Values " + complianceValuesMap);

			FormFieldParamsCompliance ffpc = new FormFieldParamsCompliance();
			ffpc.fieldNames = Arrays.asList(numericField.Name);
			ffpc.complianceList = complianceValuesMap;
			formCreationWrapper.API_Wrapper_Forms_Compliance(fp, ffpc);
			
			// Form Submission-
			
			

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test(description = "Verify Field Level Comments are displayed after submision and  Field level mandatory Corrective action with Resolved check box")
	public void TestCase_36792_36797() throws Exception {
		
		  TCG_FormSubmission_Wrapper formSubWrapper = new TCG_FormSubmission_Wrapper();
			 
		  formSubWrapper.API_Form_Submit_Wrapper(fp);
		
		
	}

	@AfterMethod(alwaysRun = true)
	public void closeAppium() throws InterruptedException {
 
	}

	@AfterClass(alwaysRun = true)
	public void closeBrowser() throws InterruptedException {
	 
	}
}
