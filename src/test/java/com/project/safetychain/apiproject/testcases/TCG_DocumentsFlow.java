package com.project.safetychain.apiproject.testcases;


import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.project.safetychain.apiproject.models.Auth;
import com.project.safetychain.apiproject.models.Documents;
import com.project.safetychain.apiproject.models.FSQABrowserForms;
import com.project.safetychain.apiproject.models.FormDesigner;
import com.project.safetychain.apiproject.models.Documents.DocType;
import com.project.safetychain.apiproject.models.FSQABrowserForms.FormData;
import com.project.safetychain.apiproject.models.FSQABrowserForms.FormSubDetails;
import com.project.safetychain.apiproject.models.FormDesigner.FormFields;
import com.project.safetychain.apiproject.models.FormDesigner.FormTypes;
import com.project.safetychain.apiproject.models.ResourceDesigner;
import com.project.safetychain.apiproject.models.ResourceDesigner.ResourceTypes;
import com.project.safetychain.apiproject.models.Resources.ResourcesData;
import com.project.safetychain.apiproject.models.Resources;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.utilities.CommonMethods;
import com.project.utilities.DateTime;

public class TCG_DocumentsFlow extends TestBase{

	Auth auth = null;
	Documents documents = null;
	ResourceDesigner resourcedesigner = null;
	Resources resources = null;
	FormDesigner designer = null;
	FormFields fields = null;
	DateTime dt = null;
	FSQABrowserForms forms = null;
	FormSubDetails details = null;

	public String resourceInstanceId = null;
	public String locationCat = null;
	public String locationInstance = null;
	public String questformId = null;
	public String locationInstanceId = null;
	String suppCat = CommonMethods.dynamicText("SCat");
	String suppInstance = CommonMethods.dynamicText("SInst");
	String questformname = CommonMethods.dynamicText("QForm");


	@BeforeClass(alwaysRun = true)
	public void groupInit() {

		auth = new Auth();
		auth.setAccessToken();
		documents = new Documents();
		resourcedesigner = new ResourceDesigner();
		resources = new Resources();
		designer = new FormDesigner ();	
		dt = new DateTime();
		forms = new FSQABrowserForms();

		locationCat = CommonMethods.dynamicText("LC");
		locationInstance = CommonMethods.dynamicText("LI");


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

		List<String> formfield = Arrays.asList(FormFields.NUMERIC,FormFields.SINGLELINETEXT,FormFields.SELECTONE,FormFields.SELECTMULTIPLE,
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

		questformId = designer.createForm(questformname, FormTypes.QUESTIONNAIRE, formfield, fieldnames, false, false);
		TestValidation.Equals(questformId, questformId, "Form Created", "Failed to create form");

	}

	@Test(description = "Create Document Type")
	public void Create_DocType() {

		String doctype = CommonMethods.dynamicText("APIDoc");

		String doctypeId = documents.getDocumentTypeId(DocType.DOCUMENTTYPES);

		documents.createDocType(doctype, doctypeId);

	}

	@Test(description = "Upload Document in Docuemnt Type")
	public void Upload_Document() {

		String doctype = CommonMethods.dynamicText("APIDoc");

		String doctypeId = documents.getDocumentTypeId(DocType.DOCUMENTTYPES);

		String createdDocTypeId = documents.createDocType(doctype, doctypeId);

		documents.uploadDoc(doctypeId, createdDocTypeId, doctype);

	}

	@Test(description = "Upload Document in Document Type")
	public void Upload_Document_Resources() {

		String equipmentTypeId = null;		
		String equipmentCat = CommonMethods.dynamicText("ECat");
		String equipmentInstance = CommonMethods.dynamicText("EInst");

		equipmentTypeId = resourcedesigner.getResourceTypeId(ResourceTypes.EQUIPMENT);

		resourcedesigner.getResourceDesignerFields(equipmentTypeId);

		resourcedesigner.createResourceCategory(equipmentTypeId,equipmentTypeId,equipmentCat);

		String equipmentCatId = resources.getResourceCategoryId(equipmentTypeId,equipmentCat);

		List <String> enabledTypeFieldIds = resourcedesigner.getEnabledResourceTypeFields(equipmentTypeId);

		List <String> fieldValues = resourcedesigner.getFieldValue(enabledTypeFieldIds, ResourceTypes.EQUIPMENT);

		String fieldDetails = resources.getResourceTypeFields(fieldValues, equipmentInstance);

		String resourceInstanceId = resources.createResourceInstance(fieldDetails,equipmentTypeId,equipmentInstance, equipmentCatId, "true");

		String doctypeId = documents.getDocumentTypeId(DocType.EQUIPMENT);

		documents.uploadDoc(doctypeId, resourceInstanceId, equipmentInstance, DocType.EQUIPMENT);

	}

	@Test(description = "Verify questinnaire Form after submission")
	public void Verify_Quest_Form() {

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

		String formId = forms.addQuestForm(formData,fielddetails,details);

		String doctypeId = documents.getDocumentTypeId(DocType.DOCUMENTTYPES);

		String createddoctypeId = documents.searchDocType(questformname, doctypeId);

		boolean verify = documents.verifyDocument(questformname, createddoctypeId, doctypeId,formId);
		TestValidation.IsTrue(verify, "Quest Form Submission Verified", "Could Not verify Quest Form Submission");

	}
}

