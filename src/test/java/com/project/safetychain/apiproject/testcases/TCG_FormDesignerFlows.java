package com.project.safetychain.apiproject.testcases;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.project.safetychain.apiproject.models.Auth;
import com.project.safetychain.apiproject.models.Documents;
import com.project.safetychain.apiproject.models.ResourceDesigner;
import com.project.safetychain.apiproject.models.Resources;
import com.project.safetychain.apiproject.models.Documents.DocType;
import com.project.safetychain.apiproject.models.FormDesigner;
import com.project.safetychain.apiproject.models.FormDesigner.DocumentDetails;
import com.project.safetychain.apiproject.models.FormDesigner.FormFields;
import com.project.safetychain.apiproject.models.FormDesigner.FormTypes;
import com.project.safetychain.apiproject.models.ResourceDesigner.ResourceTypes;
import com.project.safetychain.apiproject.models.Resources.ResourcesData;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.utilities.CommonMethods;

public class TCG_FormDesignerFlows extends TestBase{

	Auth auth = null;
	Documents documents = null;
	ResourceDesigner resourcedesigner = null;
	Resources resources = null;
	FormDesigner designer = null;
	FormFields fields = null;
	DocumentDetails details = null;


	public String resourceInstanceId = null;
	public String doctype = null;
	public String createdDocTypeId = null;
	public String uploadedDocId = null;
	public String locationCat = null;
	public String locationInstance = null;
	String suppCat = CommonMethods.dynamicText("SCat");
	String suppInstance = CommonMethods.dynamicText("SInst");
	String questformname = CommonMethods.dynamicText("QForm");
	String checkformname = CommonMethods.dynamicText("CForm");
	String auditformname = CommonMethods.dynamicText("AForm");

	@BeforeClass(alwaysRun = true)
	public void groupInit() {

		auth = new Auth();
		auth.setAccessToken();
		documents = new Documents();
		resourcedesigner = new ResourceDesigner();
		resources = new Resources();
		designer = new FormDesigner ();	

		locationCat = CommonMethods.dynamicText("LC");
		locationInstance = CommonMethods.dynamicText("LI");


		String locationTypeId = resourcedesigner.getResourceTypeId(ResourceTypes.LOCATIONS);

		resourcedesigner.createResourceCategory(locationTypeId,locationTypeId,locationCat);

		String locationCatId = resources.getLocCatId(locationCat);

		List <String> enalbledFieldIds = resourcedesigner.getEnabledResourceTypeFields(locationTypeId);

		List <String> fieldValues = resourcedesigner.getFieldValue(enalbledFieldIds, ResourceTypes.LOCATIONS);

		String resourceTypeFields = resources.getResourceTypeFields(fieldValues,locationInstance);

		String locationInstanceId = resources.createLocInstance(locationCatId,locationCat, locationInstance, resourceTypeFields);

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


		doctype = CommonMethods.dynamicText("APIDoc");

		String doctypeId = documents.getDocumentTypeId(DocType.DOCUMENTTYPES);

		createdDocTypeId = documents.createDocType(doctype, doctypeId);

		uploadedDocId = documents.uploadDoc(doctypeId, createdDocTypeId, doctype);




	}

	@Test(description = "Cretion of QuestForm")
	public void Crete_Quest_Form() throws InterruptedException{

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

		DocumentDetails.docTypeName = doctype;
		DocumentDetails.documentTypeId = createdDocTypeId;
		DocumentDetails.documentuploadId = uploadedDocId;

		String formId = designer.createForm(questformname, FormTypes.QUESTIONNAIRE, formfield, fieldnames, false, true);
		TestValidation.Equals(formId, formId, "Form Created", "Failed to create form");

	}

	@Test(description = "Cretion of Check Form")
	public void Crete_Check_Form() throws InterruptedException{

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

		DocumentDetails.docTypeName = doctype;
		DocumentDetails.documentTypeId = createdDocTypeId;
		DocumentDetails.documentuploadId = uploadedDocId;

		String formId = designer.createForm(checkformname, FormTypes.CHECK, formfield, fieldnames, false, true);
		TestValidation.Equals(formId, formId, "Form Created", "Failed to create form");

	}

	@Test(description = "Cretion of Audit Form")
	public void Crete_Audit_Form() throws InterruptedException{

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

		DocumentDetails.docTypeName = doctype;
		DocumentDetails.documentTypeId = createdDocTypeId;
		DocumentDetails.documentuploadId = uploadedDocId;

		String formId = designer.createForm(auditformname, FormTypes.AUDIT, formfield, fieldnames, true, true);
		TestValidation.Equals(formId, formId, "Form Created", "Failed to create form");
	}

}
