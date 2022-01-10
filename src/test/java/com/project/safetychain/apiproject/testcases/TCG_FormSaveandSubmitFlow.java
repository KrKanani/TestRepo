package com.project.safetychain.apiproject.testcases;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.project.safetychain.apiproject.models.Auth;
import com.project.safetychain.apiproject.models.FSQABrowserForms;
import com.project.safetychain.apiproject.models.FormDesigner;
import com.project.safetychain.apiproject.models.ResourceDesigner;
import com.project.safetychain.apiproject.models.Resources;
import com.project.safetychain.apiproject.models.FSQABrowserForms.FormData;
import com.project.safetychain.apiproject.models.FSQABrowserForms.FormSubDetails;
import com.project.safetychain.apiproject.models.FormDesigner.FormFields;
import com.project.safetychain.apiproject.models.FormDesigner.FormTypes;
import com.project.safetychain.apiproject.models.ResourceDesigner.ResourceTypes;
import com.project.safetychain.apiproject.models.Resources.ResourcesData;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.utilities.CommonMethods;
import com.project.utilities.DateTime;

public class TCG_FormSaveandSubmitFlow extends TestBase{


	Auth auth = null;
	ResourceDesigner resourcedesigner = null;
	Resources resources = null;
	FormDesigner designer = null;
	FormFields fields = null;
	DateTime dt = null;
	FSQABrowserForms forms = null;
	FormSubDetails details = null;

	public String resourceInstanceId = null;
	public String doctype = null;
	public String createdDocTypeId = null;
	public String uploadedDocId = null;
	public String locationInstanceId = null;
	public static String checkformId;
	public static String auditformId;
	public static String questformId = null;
	String suppCat = CommonMethods.dynamicText("SuppC");
	String suppInstance = CommonMethods.dynamicText("SuppI");
	String questformname = CommonMethods.dynamicText("QForm");
	String checkformname = CommonMethods.dynamicText("CForm");
	String auditformname = CommonMethods.dynamicText("AForm");
	String locationInstance = CommonMethods.dynamicText("LocI");
	String locationCat = CommonMethods.dynamicText("LocC");

	@BeforeClass(alwaysRun = true)
	public void groupInit() {

		auth = new Auth();
		auth.setAccessToken();
		resourcedesigner = new ResourceDesigner();
		resources = new Resources();
		designer = new FormDesigner();
		dt = new DateTime();
		forms = new FSQABrowserForms();

		String locationTypeId = resourcedesigner.getResourceTypeId(ResourceTypes.LOCATIONS);

		resourcedesigner.createResourceCategory(locationTypeId,locationTypeId,locationCat);

		String locationCatId = resources.getLocCatId(locationCat);

		List <String> enalbledFieldIds = resourcedesigner.getEnabledResourceTypeFields(locationTypeId);

		List <String> fieldValues = resourcedesigner.getFieldValue(enalbledFieldIds, ResourceTypes.LOCATIONS);

		String resourceTypeFields = resources.getResourceTypeFields(fieldValues,locationInstance);

		locationInstanceId = resources.createLocInstance(locationCatId,locationCat, locationInstance, resourceTypeFields);

		boolean status = resources.getLocation(locationCatId, locationInstanceId, locationTypeId);

		TestValidation.IsTrue(status, 
				"Location Instance created - "+locationInstance,
				"FAILED to create Location Instance - "+locationInstance);

		resourcedesigner = new ResourceDesigner();
		resources = new Resources();

		String suppTypeId = resourcedesigner.getResourceTypeId(ResourceTypes.SUPPLIERS);

		resourcedesigner.getResourceDesignerFields(suppTypeId);

		resourcedesigner.createResourceCategory(suppTypeId,suppTypeId,suppCat);

		String suppCatId = resources.getResourceCategoryId(suppTypeId,suppCat);

		List <String> enabledTypeFieldIds = resourcedesigner.getEnabledResourceTypeFields(suppTypeId);

		List <String> fieldValues1 = resourcedesigner.getFieldValue(enabledTypeFieldIds, ResourceTypes.SUPPLIERS);

		String fieldDetails = resources.getResourceTypeFields(fieldValues1, suppInstance);

		resourceInstanceId = resources.createResourceInstance(fieldDetails,suppTypeId,suppInstance, suppCatId, "true");

		ResourcesData data = new ResourcesData();

		data.locationCatName = locationCat;
		data.locationTypeId = locationTypeId;
		data.locationInstanceId = locationInstanceId;
		data.locationInstname = locationInstance;
		data.resourceTypeId = suppTypeId;
		data.resourceInstanceId = resourceInstanceId;

		boolean link = resources.linkResourceLocation(data);
		TestValidation.IsTrue(link, 
				"Location Resource linked",
				"FAILED to link Location Resource");	

		auth.setAccessToken();

		boolean resourcetree = designer.getResourceTree(resourceInstanceId,suppCat,suppInstance);
		TestValidation.IsTrue(resourcetree, 
				"Resource Tree retrived",
				"FAILED to get resource tree details");		

		List<String> formfield = Arrays.asList(FormFields.NUMERIC,FormFields.FREETEXT,FormFields.SELECTONE,FormFields.SELECTMULTIPLE,
				FormFields.DATE,FormFields.TIME,FormFields.DATETIME,FormFields.IDENTIFIER);

		HashMap<String,String> fieldnames = new LinkedHashMap<String,String>();

		fieldnames.put("Number", "Number");
		fieldnames.put("Text", "Text");
		fieldnames.put("SelectOne", "One");
		fieldnames.put("SelectMultiple", "Multiple");
		fieldnames.put("Date", "Date");
		fieldnames.put("Time", "Time");
		fieldnames.put("DateandTime", "DT");
		fieldnames.put("Identifier","Identifier");

		List<String> formfield1 = Arrays.asList(FormFields.NUMERIC,FormFields.SINGLELINETEXT,FormFields.SELECTONE,FormFields.SELECTMULTIPLE,
				FormFields.DATE,FormFields.TIME,FormFields.DATETIME,FormFields.IDENTIFIER);

		checkformId = designer.createForm(checkformname, FormTypes.CHECK, formfield, fieldnames, false, false);

		auditformId = designer.createForm(auditformname, FormTypes.AUDIT, formfield1, fieldnames, true, false);

		questformId = designer.createForm(questformname, FormTypes.QUESTIONNAIRE, formfield1, fieldnames, false, false);

	}

	@Test(description = "Save Form")
	public void Save_Form() throws InterruptedException{

		String timeformat = "hh:mm:ss.000";
		String time = dt.getTime(timeformat);
		String date = dt.getDate();

		String datetime = date+"T"+time+"Z";

		String formrefId = forms.getForm(checkformId);

		String formTypeId = forms.getFormTypeId(checkformId, resourceInstanceId, suppInstance);

		HashMap<String,String> fielddetails = new LinkedHashMap<String,String>();

		fielddetails.put("Number", "12");
		fielddetails.put("Text", "Test");
		fielddetails.put("One","");
		fielddetails.put("Multiple","");
		fielddetails.put("Date",datetime);
		fielddetails.put("Time",datetime);
		fielddetails.put("DT", datetime);
		fielddetails.put("Identifier","Test1");

		List<FormData> formData = forms.getFormData(checkformId, resourceInstanceId, suppInstance);

		FormSubDetails details = new FormSubDetails();

		details.formId = checkformId;
		details.formName = checkformname;
		details.formrefId = formrefId;
		details.formTypeId = formTypeId;
		details.ResourceId = resourceInstanceId;
		details.ResourceName = suppInstance;
		details.locationId = locationInstanceId;
		details.locationName = locationInstance;
		details.comment = "Testing form Submission via API Automation";
		details.formtype = "Check";

		forms.saveform(formData,fielddetails,details,false,false);

	}

	@Test(description = "Submit Form")
	public void Submit_Check_Form() throws InterruptedException{

		String timeformat = "hh:mm:ss.000";
		String time = dt.getTime(timeformat);
		String date = dt.getDate();
		String datetime = date+"T"+time+"Z";

		String formrefId = forms.getForm(checkformId);

		String formTypeId = forms.getFormTypeId(checkformId, resourceInstanceId, suppInstance);

		HashMap<String,String> fielddetails = new LinkedHashMap<String,String>();

		fielddetails.put("Number", "12");
		fielddetails.put("Text", "Test");
		fielddetails.put("One","");
		fielddetails.put("Multiple","");
		fielddetails.put("Date",datetime);
		fielddetails.put("Time",datetime);
		fielddetails.put("DT", datetime);
		fielddetails.put("Identifier","Test1");

		List<FormData> formData = forms.getFormData(checkformId, resourceInstanceId, suppInstance);

		FormSubDetails details = new FormSubDetails();

		details.formId = checkformId;
		details.formName = checkformname;
		details.formrefId = formrefId;
		details.formTypeId = formTypeId;
		details.ResourceId = resourceInstanceId;
		details.ResourceName = suppInstance;
		details.locationId = locationInstanceId;
		details.locationName = locationInstance;
		details.comment = "Testing form Submission via API Automation";
		details.formtype = "Check";

		forms.saveform(formData,fielddetails,details,true,false);

	}

	@Test(description = "Submit Form")
	public void Submit_Audit_Form() throws InterruptedException{

		String timeformat = "hh:mm:ss.000";
		String time = dt.getTime(timeformat);
		String date = dt.getDate();
		String datetime = date+"T"+time+"Z";

		String formrefId = forms.getForm(auditformId);

		String formTypeId = forms.getFormTypeId(auditformId, resourceInstanceId, suppInstance);

		HashMap<String,String> fielddetails = new LinkedHashMap<String,String>();

		fielddetails.put("Number", "12");
		fielddetails.put("Text", "Test");
		fielddetails.put("One","");
		fielddetails.put("Multiple","");
		fielddetails.put("Date",datetime);
		fielddetails.put("Time",datetime);
		fielddetails.put("DT", datetime);
		fielddetails.put("Identifier","Test1");

		List<FormData> formData = forms.getFormData(auditformId, resourceInstanceId, suppInstance);

		FormSubDetails details = new FormSubDetails();

		details.formId = auditformId;
		details.formName = auditformname;
		details.formrefId = formrefId;
		details.formTypeId = formTypeId;
		details.ResourceId = resourceInstanceId;
		details.ResourceName = suppInstance;
		details.locationId = locationInstanceId;
		details.locationName = locationInstance;
		details.comment = "Testing form Submission via API Automation";
		details.formtype = "Audit";

		forms.saveform(formData,fielddetails,details,true,true);

	}

	@Test(description = "Submit Form")
	public void Submit_Quest_Form() throws InterruptedException{

		String timeformat = "hh:mm:ss.000";
		String time = dt.getTime(timeformat);
		String date = dt.getDate();
		String datetime = date+"T"+time+"Z";

		String formrefId = forms.getForm(questformId);

		String formTypeId = forms.getFormTypeId(questformId, resourceInstanceId, suppInstance);

		HashMap<String,String> fielddetails = new LinkedHashMap<String,String>();

		fielddetails.put("Number", "12");
		fielddetails.put("Text", "Test");
		fielddetails.put("One","");
		fielddetails.put("Multiple","");
		fielddetails.put("Date",datetime);
		fielddetails.put("Time",datetime);
		fielddetails.put("DT", datetime);
		fielddetails.put("Identifier","Test1");

		List<FormData> formData = forms.getFormData(questformId, resourceInstanceId, suppInstance);

		FormSubDetails details = new FormSubDetails();

		details.formId = questformId;
		details.formName = questformname;
		details.formrefId = formrefId;
		details.formTypeId = formTypeId;
		details.ResourceId = resourceInstanceId;
		details.ResourceName = suppInstance;
		details.locationId = locationInstanceId;
		details.locationName = locationInstance;
		details.comment = "Testing form Submission via API Automation";
		details.formtype = "Questionnaire";

		forms.addQuestForm(formData,fielddetails,details);

	}
}
