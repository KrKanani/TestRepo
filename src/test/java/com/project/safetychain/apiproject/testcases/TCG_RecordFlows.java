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
import com.project.safetychain.apiproject.models.FSQABrowserRecords;
import com.project.safetychain.apiproject.models.FormDesigner.FormFields;
import com.project.safetychain.apiproject.models.FormDesigner.FormTypes;
import com.project.safetychain.apiproject.models.ResourceDesigner.ResourceTypes;
import com.project.safetychain.apiproject.models.Resources.ResourcesData;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.utilities.CommonMethods;
import com.project.utilities.DateTime;

public class TCG_RecordFlows extends TestBase{

	Auth auth = null;
	ResourceDesigner resourcedesigner = null;
	Resources resources = null;
	FormDesigner designer = null;
	FormFields fields = null;
	FSQABrowserForms forms = null;
	FormSubDetails details = null;
	FSQABrowserRecords record = null;

	public String resourceInstanceId = null;
	public String doctype = null;
	public String createdDocTypeId = null;
	public String uploadedDocId = null;
	public String locationCat = null;
	public String locationInstanceId = null;
	public static String checkformId;
	public static String auditformId;
	public static String questformId = null;
	String checkRecordId = null;
	String auditRecordId = null;
	String suppCat = CommonMethods.dynamicText("SCat");
	String suppInstance = CommonMethods.dynamicText("SInst");
	String questformname = CommonMethods.dynamicText("QForm");
	String checkformname = CommonMethods.dynamicText("CForm");
	String auditformname = CommonMethods.dynamicText("AForm");
	String locationInstance = CommonMethods.dynamicText("LI");

	DateTime dt = new DateTime();
	String timeformat = "hh:mm:ss.000";
	String time = dt.getTime(timeformat);
	String date = dt.getDate();
	String datetime = date+"T"+time+"Z";


	@BeforeClass(alwaysRun = true)
	public void groupInit() {

		auth = new Auth();
		auth.setAccessToken();
		resourcedesigner = new ResourceDesigner();
		resources = new Resources();
		designer = new FormDesigner();

		forms = new FSQABrowserForms();
		record = new FSQABrowserRecords();
		locationCat = CommonMethods.dynamicText("LC");


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

		checkRecordId = forms.saveform(formData,fielddetails,details,true,false);

		auditformId = designer.createForm(auditformname, FormTypes.AUDIT, formfield1, fieldnames, true, false);

		String formrefId1 = forms.getForm(auditformId);

		String formTypeId1 = forms.getFormTypeId(auditformId, resourceInstanceId, suppInstance);

		List<FormData> formData1 = forms.getSectionFormData(auditformId, resourceInstanceId, suppInstance);

		FormSubDetails details1 = new FormSubDetails();

		details1.formId = auditformId;
		details1.formName = auditformname;
		details1.formrefId = formrefId1;
		details1.formTypeId = formTypeId1;
		details1.ResourceId = resourceInstanceId;
		details1.ResourceName = suppInstance;
		details1.locationId = locationInstanceId;
		details1.locationName = locationInstance;
		details1.comment = "Testing form Submission via API Automation";
		details1.formtype = "Audit";

		auditRecordId = forms.saveform(formData1,fielddetails,details1,true,true);

	}

	@Test(description = "Verify Check Form Record")
	public void Verify_Check_Record() throws InterruptedException{

		String suppTypeId = resourcedesigner.getResourceTypeId(ResourceTypes.SUPPLIERS);
		boolean verifyRecordId = record.verifyRecord(suppTypeId, resourceInstanceId, checkRecordId, checkformname);
		TestValidation.IsTrue(verifyRecordId, 
				"Verified Check Form Record Id", 
				"FAILED to verify Check Form Record Id");	

		LinkedHashMap<String,Object> fielddetails = new LinkedHashMap<String,Object>();

		fielddetails.put("Number", 12.0);
		fielddetails.put("Text", "Test");
		fielddetails.put("Date",datetime);
		fielddetails.put("Time",datetime);
		fielddetails.put("DT",datetime);
		fielddetails.put("Identifier","Test1");

		LinkedHashMap<String,String> fielddetails1 = new LinkedHashMap<String,String>();

		fielddetails1.put("One","Value2");

		LinkedHashMap<String,String> fielddetails2 = new LinkedHashMap<String,String>();

		fielddetails1.put("Multiple","Value1");

		boolean verify = record.verifyRecordData(checkRecordId, fielddetails);
		TestValidation.IsTrue(verify, 
				"Verified Check Form Record", 
				"FAILED to verify Check Form Record");

		boolean verify1=record.verifySelectOneMultipleRecordData(checkRecordId, fielddetails1, 3, FormFields.SELECTONE);
		TestValidation.IsTrue(verify1, 
				"Verified SelectOne Field Values in Check Form Record", 
				"FAILED to verify SelectOne Field Values in Check Form Record");

		boolean verify2=record.verifySelectOneMultipleRecordData(checkRecordId, fielddetails2, 4,  FormFields.SELECTMULTIPLE);
		TestValidation.IsTrue(verify2, 
				"Verified SelectMultiple Field Values in Check Form Record", 
				"FAILED to verify SelectMultiple Field Values in Check Form Record");
	}

	@Test(description = "Verify Audit Form Record")
	public void Verify_Audit_Record() throws InterruptedException{

		String suppTypeId = resourcedesigner.getResourceTypeId(ResourceTypes.SUPPLIERS);
		boolean verifyRecordId = record.verifyRecord(suppTypeId, resourceInstanceId, auditRecordId, auditformname);
		TestValidation.IsTrue(verifyRecordId, 
				"Verified Audit Form Record Id", 
				"FAILED to verify Audit Form Record Id");

		LinkedHashMap<String,String> fielddetails = new LinkedHashMap<String,String>();

		fielddetails.put("Number", "12.0");
		fielddetails.put("Text", "Test");
		fielddetails.put("One","Value2");
		fielddetails.put("Multiple","Value1");
		fielddetails.put("Date",datetime);
		fielddetails.put("Time",datetime);
		fielddetails.put("DT",datetime);
		fielddetails.put("Identifier","Test1");

		List<String> fields = Arrays.asList(FormFields.NUMERIC,FormFields.SINGLELINETEXT,
				FormFields.SELECTONE,FormFields.SELECTMULTIPLE,FormFields.DATE,FormFields.TIME,FormFields.DATETIME,FormFields.IDENTIFIER);

		boolean verify = record.verifySectionRecordData(auditRecordId, fielddetails,fields);
		TestValidation.IsTrue(verify, 
				"Verified Audit Form Record", 
				"FAILED to verify Audit Form Record");

	}
}
