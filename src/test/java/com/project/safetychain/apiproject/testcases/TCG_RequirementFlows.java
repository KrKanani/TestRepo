package com.project.safetychain.apiproject.testcases;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.project.safetychain.apiproject.models.Auth;
import com.project.safetychain.apiproject.models.Documents;
import com.project.safetychain.apiproject.models.Requirements;
import com.project.safetychain.apiproject.models.Requirements.RequirementDetails;
import com.project.safetychain.apiproject.models.ResourceDesigner;
import com.project.safetychain.apiproject.models.Resources;
import com.project.safetychain.apiproject.models.WorkGroups;
import com.project.safetychain.apiproject.models.Documents.DocType;
import com.project.safetychain.apiproject.models.FormDesigner;
import com.project.safetychain.apiproject.models.FormDesigner.DocumentDetails;
import com.project.safetychain.apiproject.models.FormDesigner.FormFields;
import com.project.safetychain.apiproject.models.FormDesigner.FormTypes;
import com.project.safetychain.apiproject.models.ResourceDesigner.ResourceTypes;
import com.project.safetychain.apiproject.models.Resources.ResourcesData;
import com.project.safetychain.apiproject.models.WorkGroups.WorkGroupDetails;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.utilities.CommonMethods;

public class TCG_RequirementFlows extends TestBase{

	Auth auth = null;
	Documents documents = null;
	ResourceDesigner resourcedesigner = null;
	Resources resources = null;
	Requirements req = null;
	RequirementDetails reqDetails = null;
	WorkGroups wg = null;
	FormDesigner designer =null;
	FormFields fields = null;
	DocumentDetails details = null;

	public String resourceInstanceId = null;
	public String itemInstanceId = null;
	public String itemCatId = null;
	public String itemCat=null;
	public String suppInstance = null;
	public String itemInstance = null;
	public String doctype = null;
	public String createdDocTypeId = null;
	public String uploadedDocId = null;
	public String suppTempName = null;
	public String suppTempId = null;
	public String itemTempName = null;
	public String itemTempId = null;
	public String acknowledgmentTaskName = null;
	public String docUploadTaskName = null;
	public String formTaskName = null;
	public String itemacknowledgmentTaskName = null;
	public String itemdocUploadTaskName = null;
	public String itemformTaskName = null;
	public String wgName = null;
	public String wgId = null;
	public String locationCat = null;
	public String locationInstance = null;
	public String suppCat = null;
	public String formId = null;

	String questformname = CommonMethods.dynamicText("QForm");

	@BeforeClass(alwaysRun = true)
	public void groupInit() {

		auth = new Auth();
		auth.setAccessToken();
		documents = new Documents();
		resourcedesigner = new ResourceDesigner();
		resources = new Resources();	
		req = new Requirements();
		wg = new WorkGroups();
		designer = new FormDesigner();

		suppCat= CommonMethods.dynamicText("SCat");
		itemCat =  CommonMethods.dynamicText("ICat");
		suppInstance = CommonMethods.dynamicText("SInst");
		itemInstance = CommonMethods.dynamicText("IInst");
		suppTempName = CommonMethods.dynamicText("STemp");
		acknowledgmentTaskName = CommonMethods.dynamicText("SATask");
		docUploadTaskName = CommonMethods.dynamicText("SDTask");
		formTaskName = CommonMethods.dynamicText("SFTask");
		itemacknowledgmentTaskName = CommonMethods.dynamicText("IATask");
		itemdocUploadTaskName = CommonMethods.dynamicText("IDTask");
		itemformTaskName = CommonMethods.dynamicText("IFTask");
		wgName = CommonMethods.dynamicText("W");
		locationCat = CommonMethods.dynamicText("LC");
		locationInstance = CommonMethods.dynamicText("LI");
		itemTempName = CommonMethods.dynamicText("ITemp");

		String locationTypeId = resourcedesigner.getResourceTypeId(ResourceTypes.LOCATIONS);

		resourcedesigner.createResourceCategory(locationTypeId,locationTypeId,locationCat);

		String locationCatId = resources.getLocCatId(locationCat);

		List <String> enalbledFieldIds = resourcedesigner.getEnabledResourceTypeFields(locationTypeId);

		List <String> fieldValues = resourcedesigner.getFieldValue(enalbledFieldIds, ResourceTypes.LOCATIONS);

		String resourceTypeFields = resources.getResourceTypeFields(fieldValues,locationInstance);

		String locationInstanceId = resources.createLocInstance(locationCatId,locationCat, locationInstance, resourceTypeFields);

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

		DocumentDetails.docTypeName = doctype;
		DocumentDetails.documentTypeId = createdDocTypeId;
		DocumentDetails.documentuploadId = uploadedDocId;

		formId = designer.createForm(questformname, FormTypes.QUESTIONNAIRE, formfield, fieldnames, false, true);

		resourcedesigner = new ResourceDesigner();
		resources = new Resources();

		String itemTypeId = resourcedesigner.getResourceTypeId(ResourceTypes.ITEMS);

		resourcedesigner.getResourceDesignerFields(itemTypeId);

		resourcedesigner.createResourceCategory(itemTypeId,itemTypeId,itemCat);

		itemCatId = resources.getResourceCategoryId(itemTypeId,itemCat);

		List <String> enabledTypeFieldIds1 = resourcedesigner.getEnabledResourceTypeFields(itemTypeId);

		List <String> fieldValues2 = resourcedesigner.getFieldValue(enabledTypeFieldIds1, ResourceTypes.ITEMS);

		String fieldDetails1 = resources.getResourceTypeFields(fieldValues2, itemInstance);

		itemInstanceId = resources.createResourceInstance(fieldDetails1,itemTypeId,itemInstance, itemCatId, "true");


		ResourcesData data1 = new ResourcesData();

		data1.locationCatName = locationCat;
		data1.locationTypeId = locationTypeId;
		data1.locationInstanceId = locationInstanceId;
		data1.locationInstname = locationInstance;
		data1.resourceTypeId = itemTypeId;
		data1.resourceInstanceId = itemInstanceId;

		boolean link1 = resources.linkResourceLocation(data1);
		TestValidation.IsTrue(link1, 
				"Location Resource linked",
				"FAILED to link Location Resource");		


		doctype = CommonMethods.dynamicText("APIDoc");

		String doctypeId = documents.getDocumentTypeId(DocType.DOCUMENTTYPES);

		createdDocTypeId = documents.createDocType(doctype, doctypeId);

		uploadedDocId = documents.uploadDoc(doctypeId, createdDocTypeId, doctype);

		WorkGroupDetails workGroupDetails = new WorkGroupDetails();
		workGroupDetails.workgroupName = wgName;
		workGroupDetails.userIDs = Arrays.asList(WorkGroups.SUPERADMIN_USER_ID);

		wg.createWorkGroup(workGroupDetails);
		wgId = wg.getWorkGroupDetails(wgName, "Id");


		suppTempId = req.createTemp(suppTempName, true);

		Requirements req1 = new Requirements();

		itemTempId = req1.createTemp(itemTempName, false);

	}

	@Test(description = "Supplier Template Creation" , priority = -1)
	public void Supplier_Temp_Creation() {

		req.createTemp(suppTempName, true);

	}

	@Test(description = "Acknowledgement Task" , priority = 0)
	public void Acknowledgment_Task_Creation() {

		RequirementDetails reqDetails = new RequirementDetails();
		reqDetails.supptempname = suppTempName;
		reqDetails.supptempId = suppTempId;
		reqDetails.acknowledgmentTaskName = acknowledgmentTaskName;
		reqDetails.docname = "upload.png";
		reqDetails.docTypeName = doctype;
		reqDetails.docuemntuploadId = uploadedDocId;
		reqDetails.documentTypeId = createdDocTypeId;
		reqDetails.wgId = wgId;
		reqDetails.wgName = wgName;
		reqDetails.environment = environment;
		req.addSuppAckRequirement(reqDetails);

	}

	@Test(description = "Upload Document Requirement" , priority = 1)
	public void DocumentUpload_Task_Creation() {


		RequirementDetails reqDetails =  new RequirementDetails();
		reqDetails.supptempname = suppTempName;
		reqDetails.supptempId = suppTempId;
		reqDetails.docUploadTaskName = docUploadTaskName;
		reqDetails.docTypeName = doctype;
		reqDetails.documentTypeId = createdDocTypeId;
		reqDetails.wgId = wgId;
		reqDetails.wgName = wgName;
		req.addSuppDocUploadRequirement(reqDetails);

	}

	@Test(description = "Complete Form Requirement" , priority = 2)
	public void Complete_Form_Task_Creation() {



		RequirementDetails reqDetails =  new RequirementDetails();
		reqDetails.supptempname = suppTempName;
		reqDetails.supptempId = suppTempId;
		reqDetails.formName = questformname;
		reqDetails.formTaskName = formTaskName;
		reqDetails.suppResourceId = resourceInstanceId;
		reqDetails.suppResourceName = suppInstance;
		reqDetails.wgId = wgId;
		reqDetails.wgName = wgName;
		reqDetails.formId = formId;

		req.addSuppCompleteFormRequirement(reqDetails);

	}

	@Test(description = "Add Supplier to Template" , priority = 3)
	public void Add_Supplier_to_Template() {

		RequirementDetails reqDetails =  new RequirementDetails();
		reqDetails.supptempname = suppTempName;
		reqDetails.supptempId = suppTempId;
		reqDetails.suppResourceId = resourceInstanceId;
		reqDetails.suppResourceName = suppInstance;

		req.getResourceDetails(reqDetails, true);

		req.addSupplierToTemplate(reqDetails);

	}
	
	@Test(description = "Item Template Creation" , priority = 4)
	public void Item_Temp_Creation() {

		req.createTemp(itemTempName, true);

	}

	@Test(description = "Add Acknowledgement Task" , priority = 5)
	public void Item_Acknowledgment_Task() {

		RequirementDetails reqDetails =  new RequirementDetails();
		reqDetails.itemtempName = itemTempName;
		reqDetails.itemtempId = itemTempId;
		reqDetails.itemacknowledgmentTaskName = itemacknowledgmentTaskName;
		reqDetails.docname = "upload.png";
		reqDetails.docTypeName = doctype;
		reqDetails.docuemntuploadId = uploadedDocId;
		reqDetails.documentTypeId = createdDocTypeId;
		reqDetails.wgId = wgId;
		reqDetails.wgName = wgName;
		reqDetails.environment = environment;
		req.addSuppAckRequirement(reqDetails);

		req.addItemAckRequirement(reqDetails);

	}

	@Test(description = "Upload Document Requirement" , priority = 6)
	public void Item_DocUpload_Task() {

		RequirementDetails reqDetails =  new RequirementDetails();
		reqDetails.itemtempName = itemTempName;
		reqDetails.itemtempId = itemTempId;
		reqDetails.itemdocUploadTaskName = itemdocUploadTaskName;
		reqDetails.docTypeName = doctype;
		reqDetails.documentTypeId = createdDocTypeId;
		reqDetails.wgId = wgId;
		reqDetails.wgName = wgName;
		req.addSuppDocUploadRequirement(reqDetails);

		req.addItemDocUploadRequirement(reqDetails);

	}

	@Test(description = "Complete Form Requirement" , priority = 7)
	public void Item_FormTask() {

		RequirementDetails reqDetails =  new RequirementDetails();
		reqDetails.itemtempName = itemTempName;
		reqDetails.itemtempId = itemTempId;
		reqDetails.formName = questformname;
		reqDetails.itemformTaskName = itemformTaskName;
		reqDetails.suppResourceId = resourceInstanceId;
		reqDetails.suppResourceName = suppInstance;
		reqDetails.wgId = wgId;
		reqDetails.wgName = wgName;
		reqDetails.formId = formId;

		req.addItemCompleteFormRequirement(reqDetails);

	}

	@Test(description = "Add Item to Template" , priority = 8)
	public void Add_Item_to_Template() {


		RequirementDetails reqDetails =  new RequirementDetails();
		reqDetails.itemtempName = itemTempName;
		reqDetails.itemtempId = itemTempId;
		reqDetails.itemResourceId = itemInstanceId;
		reqDetails.itemResourceName = itemInstance;

		req.getResourceDetails(reqDetails, false);

		req.addItemToTemplate(reqDetails);

	}

}
