package com.project.safetychain.apiproject.testcases;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.project.safetychain.apiproject.models.Auth;
import com.project.safetychain.apiproject.models.Documents;
import com.project.safetychain.apiproject.models.FormDesigner;
import com.project.safetychain.apiproject.models.Documents.DocType;
import com.project.safetychain.apiproject.models.FSQABrowserForms;
import com.project.safetychain.apiproject.models.FSQABrowserForms.FormData;
import com.project.safetychain.apiproject.models.FSQABrowserForms.FormSubDetails;
import com.project.safetychain.apiproject.models.FormDesigner.DocumentDetails;
import com.project.safetychain.apiproject.models.FormDesigner.FormFields;
import com.project.safetychain.apiproject.models.FormDesigner.FormTypes;
import com.project.safetychain.apiproject.models.Program;
import com.project.safetychain.apiproject.models.ProgramViewer;
import com.project.safetychain.apiproject.models.ResourceDesigner;
import com.project.safetychain.apiproject.models.ResourceDesigner.ResourceTypes;
import com.project.safetychain.apiproject.models.Resources.ResourcesData;
import com.project.safetychain.apiproject.models.WorkGroups.WorkGroupDetails;
import com.project.safetychain.apiproject.models.Resources;
import com.project.safetychain.apiproject.models.WorkGroups;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.utilities.CommonMethods;
import com.project.utilities.DateTime;

public class TCG_ProgramFlow extends TestBase {

	Auth auth = null;
	Documents documents = null;
	ResourceDesigner resourcedesigner = null;
	Resources resources = null;
	String doctype, doctypeId;
	FSQABrowserForms forms = null;

	FormDesigner designer = null;
	FormFields fields = null;
	DocumentDetails details = null;
	
	WorkGroupDetails workGroupDetails = null;

	Program program = null;
	ProgramViewer pv = null;
	WorkGroups wg = null;
	DateTime dt = null;

	String suppCat = CommonMethods.dynamicText("SCat");
	String suppInstance = CommonMethods.dynamicText("SInst");

	public String resourceInstanceId = null;
	public String resourceInstance = null;
	public String locationCat = null;
	public String locationInstance = null;
	public String locationInstanceId = null;
	public String resourceCat = null;
	public String formname = null;
	public String createdDocTypeId = null;
	public String uploadedDocId = null;
	public String checkFormId = null;
	

	@BeforeClass(alwaysRun = true)
	public void groupInit() {

		auth = new Auth();
		auth.setAccessToken();
		documents = new Documents();
		resourcedesigner = new ResourceDesigner();
		resources = new Resources();
		program = new Program();
		

		pv = new ProgramViewer();
		workGroupDetails = new WorkGroupDetails();
		wg = new WorkGroups();

		designer = new FormDesigner();
		dt = new DateTime();
		forms = new FSQABrowserForms();

		resourceInstance = CommonMethods.dynamicText("EquipI");
		resourceCat = CommonMethods.dynamicText("EquipC");
		locationInstance = CommonMethods.dynamicText("LocI");
		locationCat = CommonMethods.dynamicText("LocC");
		formname = CommonMethods.dynamicText("CForm");

		String locationTypeId = resourcedesigner.getResourceTypeId(ResourceTypes.LOCATIONS);

		resourcedesigner.createResourceCategory(locationTypeId, locationTypeId, locationCat);

		String locationCatId = resources.getLocCatId(locationCat);

		List<String> enalbledFieldIds = resourcedesigner.getEnabledResourceTypeFields(locationTypeId);

		List<String> fieldValues = resourcedesigner.getFieldValue(enalbledFieldIds, ResourceTypes.LOCATIONS);

		String resourceTypeFields = resources.getResourceTypeFields(fieldValues, locationInstance);

		locationInstanceId = resources.createLocInstance(locationCatId, locationCat, locationInstance,
				resourceTypeFields);

		resourcedesigner = new ResourceDesigner();
		resources = new Resources();

		String resourceTypeId = resourcedesigner.getResourceTypeId(ResourceTypes.EQUIPMENT);

		resourcedesigner.getResourceDesignerFields(resourceTypeId);

		resourcedesigner.createResourceCategory(resourceTypeId, resourceTypeId, resourceCat);

		String resourceCatId = resources.getResourceCategoryId(resourceTypeId, resourceCat);

		List<String> enabledTypeFieldId1 = resourcedesigner.getEnabledResourceTypeFields(resourceTypeId);

		List<String> fieldValues1 = resourcedesigner.getFieldValue(enabledTypeFieldId1, ResourceTypes.EQUIPMENT);

		String fieldDetails = resources.getResourceTypeFields(fieldValues1, resourceInstance);

		resourceInstanceId = resources.createResourceInstance(fieldDetails, resourceTypeId, resourceInstance,
				resourceCatId, "true");

		ResourcesData data = new ResourcesData();

		data.locationCatName = locationCat;
		data.locationTypeId = locationTypeId;
		data.locationInstanceId = locationInstanceId;
		data.locationInstname = locationInstance;
		data.resourceTypeId = resourceTypeId;
		data.resourceInstanceId = resourceInstanceId;

		boolean link = resources.linkResourceLocation(data);
		TestValidation.IsTrue(link, "Location Resource linked", "FAILED to link Location Resource");

		auth.setAccessToken();

		boolean resourcetree = designer.getResourceTree(resourceInstanceId, resourceCat, resourceInstance);
		TestValidation.IsTrue(resourcetree, "Resource Tree retrived", "FAILED to get resource tree details");

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


		checkFormId = designer.createForm(formname, FormTypes.CHECK, formfield, fieldnames, false, false);
		
		String timeformat = "hh:mm:ss.000";
		String time = dt.getTime(timeformat);
		String date = dt.getDate();
		String datetime = date+"T"+time+"Z";

		String formrefId = forms.getForm(checkFormId);

		String formTypeId = forms.getFormTypeId(checkFormId, resourceInstanceId, suppInstance);

		HashMap<String, String> fielddetails = new LinkedHashMap<String, String>();

		fielddetails.put("Number", "12");
		fielddetails.put("Text", "Test");
		fielddetails.put("One", "");
		fielddetails.put("Multiple", "");
		fielddetails.put("Date", datetime);
		fielddetails.put("Time", datetime);
		fielddetails.put("DT", datetime);
		fielddetails.put("Identifier", "Test1");

		List<FormData> formData = forms.getFormData(checkFormId, resourceInstanceId, suppInstance);

		FormSubDetails details = new FormSubDetails();

		details.formId = checkFormId;
		details.formName = formname;
		details.formrefId = formrefId;
		details.formTypeId = formTypeId;
		details.ResourceId = resourceInstanceId;
		details.ResourceName = suppInstance;
		details.locationId = locationInstanceId;
		details.locationName = locationInstance;
		details.comment = "Testing form Submission via API Automation";
		details.formtype = "Check";

		forms.saveform(formData, fielddetails, details, true, false);
		
		doctype = CommonMethods.dynamicText("APIDoc");

		String doctypeId = documents.getDocumentTypeId(DocType.DOCUMENTTYPES);

		createdDocTypeId = documents.createDocType(doctype, doctypeId);

		uploadedDocId = documents.uploadDoc(doctypeId, createdDocTypeId, doctype);

	}

	@Test(description = "Creation of Program Template and Program Element")
	public void Program_Creation() {

		String programTempName = CommonMethods.dynamicText("ProgTempTC");
		String createdProgramTempId = program.createProgramTemp(programTempName);
		String getNewlyProgramTempIntId = program.getProgramTempIntId(programTempName, createdProgramTempId);
		String programElementName = CommonMethods.dynamicText("ProgTempTC");
		program.createProgramElem(programTempName, createdProgramTempId, getNewlyProgramTempIntId, programElementName);

	}

	@Test(description = "Linking of Program Element With Details, Doc, DocType and Form")
	public void Program_Element_Linking() {

		String programTempName = CommonMethods.dynamicText("ProgTempTC");
		String createdProgramTempId = program.createProgramTemp(programTempName);
		String getNewlyProgramTempIntId = program.getProgramTempIntId(programTempName, createdProgramTempId);
		String programElementName = CommonMethods.dynamicText("ProgTempTC");
		String createdProgramElemId = program.createProgramElem(programTempName, createdProgramTempId,
				getNewlyProgramTempIntId, programElementName);
		boolean addDocType = program.AddDocTypeToProgramElem(createdProgramTempId, createdProgramElemId,
				createdDocTypeId);
		TestValidation.IsTrue(addDocType, "Added DocType ", "FAILED to Add Doc Type ");

		boolean addDocs = program.AddDocsToProgramElem(createdProgramTempId, createdProgramElemId, uploadedDocId);
		TestValidation.IsTrue(addDocs, "Added Docs ", "FAILED to Add Docs ");

		boolean progDetails = program.programDetails("These are program details", createdProgramElemId,
				createdProgramTempId);
		TestValidation.IsTrue(progDetails, "Added Program Details ", "FAILED to Add Program Details ");

		boolean addFrom = program.AddFormsToProgramElem(createdProgramTempId, createdProgramElemId, checkFormId);
		TestValidation.IsTrue(addFrom, "Added Form to program Element ", "FAILED to Add Form to program Element");

	}

	@Test(description = "verify Program viewer for Doc, form and Records")
	public void Program_Viewer_Verify() {

		String programTempName = CommonMethods.dynamicText("ProgTempTC");
		String createdProgramTempId = program.createProgramTemp(programTempName);
		String getNewlyProgramTempIntId = program.getProgramTempIntId(programTempName, createdProgramTempId);
		String programElementName = CommonMethods.dynamicText("ProgTempTC");
		String createdProgramElemId = program.createProgramElem(programTempName, createdProgramTempId,
				getNewlyProgramTempIntId, programElementName);
		boolean addDocType = program.AddDocTypeToProgramElem(createdProgramTempId, createdProgramElemId,
				createdDocTypeId);
		TestValidation.IsTrue(addDocType, "Added DocType ", "FAILED to Add Doc Type ");

		boolean addDocs = program.AddDocsToProgramElem(createdProgramTempId, createdProgramElemId, uploadedDocId);
		TestValidation.IsTrue(addDocs, "Added Docs ", "FAILED to Add Docs ");

		boolean progDetails = program.programDetails("These are program details", createdProgramElemId,
				createdProgramTempId);
		TestValidation.IsTrue(progDetails, "Added Program Details ", "FAILED to Add Program Details ");

		boolean addFrom = program.AddFormsToProgramElem(createdProgramTempId, createdProgramElemId, checkFormId);
		TestValidation.IsTrue(addFrom, "Added Form to program Element ", "FAILED to Add Form to program Element");
		

		boolean verfiyDocs =  pv.verifyDocsForProgViewer(createdProgramTempId, programTempName, createdProgramElemId, doctype, "upload.png");
		TestValidation.IsTrue(verfiyDocs, "Verified Documents in program viewer ", "FAILED to verify Documents in program viewer");
		
		boolean verifyForm = pv.verifyFormForProgViewer(createdProgramElemId, formname);
		TestValidation.IsTrue(verifyForm, "Verified Forms in program viewer ", "FAILED to verify Form in program viewer");
		
		boolean verifyRecords = pv.verifyRecordsForProgViewer(createdProgramElemId, formname);
		TestValidation.IsTrue(verifyRecords, "Verified Records in program viewer ", "FAILED to verify Records in program viewer");

	}

}
