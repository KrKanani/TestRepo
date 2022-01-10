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
import com.project.safetychain.apiproject.models.Requirements;
import com.project.safetychain.apiproject.models.ResourceDesigner;
import com.project.safetychain.apiproject.models.Resources;
import com.project.safetychain.apiproject.models.SupplierPortal;
import com.project.safetychain.apiproject.models.Users;
import com.project.safetychain.apiproject.models.SupplierPortal.RequirementTaskDetails;
import com.project.safetychain.apiproject.models.Users.UserDetails;
import com.project.safetychain.apiproject.models.WorkGroups;
import com.project.safetychain.apiproject.models.Documents.DocType;
import com.project.safetychain.apiproject.models.FSQABrowserForms;
import com.project.safetychain.apiproject.models.FSQABrowserForms.FormData;
import com.project.safetychain.apiproject.models.FormDesigner.DocumentDetails;
import com.project.safetychain.apiproject.models.FormDesigner.FormFields;
import com.project.safetychain.apiproject.models.FormDesigner.FormTypes;
import com.project.safetychain.apiproject.models.Inbox;
import com.project.safetychain.apiproject.models.Inbox.TaskDetails;
import com.project.safetychain.apiproject.models.Requirements.RequirementDetails;
import com.project.safetychain.apiproject.models.ResourceDesigner.ResourceTypes;
import com.project.safetychain.apiproject.models.Resources.ResourcesData;
import com.project.safetychain.apiproject.models.WorkGroups.WorkGroupDetails;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.utilities.ApiUtils;
import com.project.utilities.CommonMethods;
import com.project.utilities.DateTime;

public class TCG_SupplierFlow extends TestBase{

	Auth auth = null;
	ApiUtils apiUtils = null;
	Requirements req = null;
	SupplierPortal portal = null;
	Documents documents = null;
	ResourceDesigner resourcedesigner = null;
	Resources resources = null;
	RequirementDetails reqDetails = null;
	WorkGroups wg = null;
	FormDesigner designer =null;
	FormFields fields = null;
	DocumentDetails details = null;
	Users user = null;
	UserDetails userdetail = null;
	DateTime dt = null;
	FSQABrowserForms forms = null;
	Inbox inbox = null;

	public String resourceInstanceId = null;
	public String itemInstanceId = null;
	public String suppInstance = null;
	public String itemInstance = null;
	public String itemCat = null;
	public String doctype = null;
	public String createdDocTypeId = null;
	public String uploadedDocId = null;
	public String suppTempName = null;
	public String suppTempId = null;
	public String itemTempName = null;
	public String itemTempId = null;
	public String acknowledgmentTaskName = null;
	public String docUploadTaskName = null,docUploadTaskName1 = null;;
	public String formTaskName = null,formTaskName1 = null;
	public String itemacknowledgmentTaskName = null;
	public String itemdocUploadTaskName = null,itemdocUploadTaskName1 = null;
	public String itemformTaskName = null,itemformTaskName1 = null;
	public String wgName = null;
	public String wgId = null;
	public String locationCat = null;
	public String locationInstance = null;
	public String suppCat = null;
	public String formId = null;
	public String ItemDocReqId = null;
	public String suppAckReqId = null, suppDocReqId = null, suppFormReqId = null,suppDocReqId1 = null, suppFormReqId1 = null;
	public String itemAckReqId = null, itemDocReqId = null, itemFormReqId = null,itemDocReqId1 = null, itemFormReqId1 = null;
	public String suppdocmasterId = null,suppformMasterId = null,itemdocMasterId =null, itemformMasterId = null, suppformdocId =null, itemFormDocId =null;

	String questformname = CommonMethods.dynamicText("QForm");
	String supplierUserName = CommonMethods.dynamicText("API_SupplierUser");

	@BeforeClass(alwaysRun = true)
	public void groupInit() {

		auth = new Auth();
		auth.setAccessToken();
		apiUtils = new ApiUtils();
		req = new Requirements();
		portal = new SupplierPortal();
		documents = new Documents();
		resourcedesigner = new ResourceDesigner();
		resources = new Resources();	
		req = new Requirements();
		wg = new WorkGroups();
		designer = new FormDesigner();
		user = new Users();
		userdetail = new UserDetails();
		dt = new DateTime();
		forms = new FSQABrowserForms();
		inbox = new Inbox();

		suppCat= CommonMethods.dynamicText("SCat");
		suppInstance = CommonMethods.dynamicText("SInst");
		itemInstance = CommonMethods.dynamicText("IInst");
		itemCat = CommonMethods.dynamicText("ICat");
		suppTempName = CommonMethods.dynamicText("STemp");
		acknowledgmentTaskName = CommonMethods.dynamicText("SATask");
		docUploadTaskName = CommonMethods.dynamicText("SDTask");
		docUploadTaskName1 = CommonMethods.dynamicText("SDTask1");
		formTaskName = CommonMethods.dynamicText("SFTask");
		formTaskName1 = CommonMethods.dynamicText("SFTask1");
		itemacknowledgmentTaskName = CommonMethods.dynamicText("IATask");
		itemdocUploadTaskName = CommonMethods.dynamicText("IDTask");
		itemdocUploadTaskName1 = CommonMethods.dynamicText("IDTask1");
		itemformTaskName = CommonMethods.dynamicText("IFTask");
		itemformTaskName1 = CommonMethods.dynamicText("IFTask1");
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

		doctype = CommonMethods.dynamicText("APIDoc");

		String doctypeId = documents.getDocumentTypeId(DocType.DOCUMENTTYPES);

		createdDocTypeId = documents.createDocType(doctype, doctypeId);

		uploadedDocId = documents.uploadDoc(doctypeId, createdDocTypeId, doctype);

		auth.setAccessToken();

		//String supplierDetails [][] = {{suppInstance,resourceInstanceId}};

		//		UserDetails userDetails = new UserDetails();
		//		userDetails.userType = UserType.SUPPLIER_USER;
		//		userDetails.username = supplierUserName;
		//		userDetails.suppliers = supplierDetails;
		//
		//		boolean createInternalUser = user.createUser(userDetails);
		//		TestValidation.IsTrue(createInternalUser, "CREATED Supplier User - "+supplierUserName, "FAILED to create supplier user - "+supplierUserName);

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

		formId = designer.createForm(questformname, FormTypes.QUESTIONNAIRE, formfield, fieldnames, false, false);

		resourcedesigner = new ResourceDesigner();
		resources = new Resources();

		String itemTypeId = resourcedesigner.getResourceTypeId(ResourceTypes.ITEMS);

		resourcedesigner.getResourceDesignerFields(itemTypeId);

		resourcedesigner.createResourceCategory(itemTypeId,itemTypeId,itemCat);

		String itemCatId = resources.getResourceCategoryId(itemTypeId,itemCat);

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

		ResourcesData data2 = new ResourcesData();

		data2.itemTypeId = itemTypeId;
		data2.suppTypeId = suppTypeId;
		data2.supplierInstanceId = resourceInstanceId;
		data2.supplierInstanceName = suppInstance;
		data2.itemInstanceId = itemInstanceId;

		boolean link2 = resources.ItemSupplierlink(data2);
		TestValidation.IsTrue(link2, 
				"Item Supplier linked",
				"FAILED to link Item Supplier");

		WorkGroupDetails workGroupDetails = new WorkGroupDetails();
		workGroupDetails.workgroupName = wgName;
		workGroupDetails.userIDs = Arrays.asList(WorkGroups.SUPERADMIN_USER_ID);

		wg.createWorkGroup(workGroupDetails);
		wgId = wg.getWorkGroupDetails(wgName, "Id");

		String supptemId = req.createTemp(suppTempName, true);

		RequirementDetails reqDetails = new RequirementDetails();
		reqDetails.supptempname = suppTempName;
		reqDetails.supptempId = supptemId;
		reqDetails.acknowledgmentTaskName = acknowledgmentTaskName;
		reqDetails.docname = "upload.png";
		reqDetails.docTypeName = doctype;
		reqDetails.docuemntuploadId = uploadedDocId;
		reqDetails.documentTypeId = createdDocTypeId;
		reqDetails.wgId = wgId;
		reqDetails.wgName = wgName;
		reqDetails.environment = environment;
		reqDetails.formId = formId;
		reqDetails.formName = questformname;
		reqDetails.formTaskName = formTaskName;
		reqDetails.docUploadTaskName = docUploadTaskName;
		reqDetails.suppResourceId = resourceInstanceId;
		reqDetails.suppResourceName = suppInstance;

		RequirementDetails reqDetails2 = new RequirementDetails();
		reqDetails2.supptempname = suppTempName;
		reqDetails2.supptempId = supptemId;
		reqDetails2.docTypeName = doctype;
		reqDetails2.docuemntuploadId = uploadedDocId;
		reqDetails2.documentTypeId = createdDocTypeId;
		reqDetails2.wgId = wgId;
		reqDetails2.wgName = wgName;
		reqDetails2.formId = formId;
		reqDetails2.formName = questformname;
		reqDetails2.formTaskName = formTaskName1;
		reqDetails2.docUploadTaskName = docUploadTaskName1;
		reqDetails2.suppResourceId = resourceInstanceId;
		reqDetails2.suppResourceName = suppInstance;

		suppAckReqId = req.addSuppAckRequirement(reqDetails);
		suppDocReqId = req.addSuppDocUploadRequirement(reqDetails);
		suppFormReqId = req.addSuppCompleteFormRequirement(reqDetails);
		suppDocReqId1 = req.addSuppDocUploadRequirement(reqDetails2);
		suppFormReqId1 = req.addSuppCompleteFormRequirement(reqDetails2);
		req.addSupplierToTemplate(reqDetails);


		auth.setAccessToken();
		req = new Requirements();

		String itemtemId = req.createTemp(itemTempName, false);

		RequirementDetails reqDetails1 = new RequirementDetails();
		reqDetails1.itemtempId = itemtemId;
		reqDetails1.itemtempName = itemTempName;
		reqDetails1.docname = "upload.png";
		reqDetails1.docTypeName = doctype;
		reqDetails1.docuemntuploadId = uploadedDocId;
		reqDetails1.documentTypeId = createdDocTypeId;
		reqDetails1.wgId = wgId;
		reqDetails1.wgName = wgName;
		reqDetails1.environment = environment;
		reqDetails1.formId = formId;
		reqDetails1.formName = questformname;		
		reqDetails1.itemResourceId= itemInstanceId;
		reqDetails1.itemResourceName = itemInstance;
		reqDetails1.itemacknowledgmentTaskName = itemacknowledgmentTaskName ;
		reqDetails1.itemdocUploadTaskName = itemdocUploadTaskName;		
		reqDetails1.itemformTaskName = itemformTaskName;

		RequirementDetails reqDetails3 = new RequirementDetails();
		reqDetails3.itemtempId = itemtemId;
		reqDetails3.itemtempName = itemTempName;
		reqDetails3.docTypeName = doctype;
		reqDetails3.docuemntuploadId = uploadedDocId;
		reqDetails3.documentTypeId = createdDocTypeId;
		reqDetails3.wgId = wgId;
		reqDetails3.wgName = wgName;
		reqDetails3.formId = formId;
		reqDetails3.formName = questformname;		
		reqDetails3.itemResourceId= itemInstanceId;
		reqDetails3.itemResourceName = itemInstance;
		reqDetails3.itemdocUploadTaskName = itemdocUploadTaskName1;		
		reqDetails3.itemformTaskName = itemformTaskName1;

		itemAckReqId = req.addItemAckRequirement(reqDetails1);
		itemDocReqId = req.addItemDocUploadRequirement(reqDetails1);
		itemFormReqId= req.addItemCompleteFormRequirement(reqDetails1);
		itemDocReqId1 = req.addItemDocUploadRequirement(reqDetails3);
		itemFormReqId1= req.addItemCompleteFormRequirement(reqDetails3);
		req.addItemToTemplate(reqDetails1);

		//auth.setSupplierAccessToken("API_SupplierUser_29.12_15.49.06", userdetail.password);		
		//portal.resetSupplierAccessToken("API_SupplierUser_29.12_15.49.06", userdetail.password, "123#");

	}

	@Test(description = "Signing Acknowledgement Task", priority = -1)
	public void Sign_Acknowledgement_Task() {

		auth.setSupplierAccessToken("supplier","123#");

		String taskMasterId = portal.verifyTasks(resourceInstanceId,suppInstance,acknowledgmentTaskName);		
		String[] IDs = taskMasterId.split("\\s+");

		String masterId = IDs[0] ;
		String taskId = IDs[1];

		//		boolean count = portal.verifyTaskCount("3");
		//		TestValidation.IsTrue(count, 
		//				"Total Task Count Verified",
		//				"FAILED to verify task count");		

		RequirementTaskDetails req = new RequirementTaskDetails();

		req.AMasterId = masterId;
		req.ATaskId = taskId;
		req.UploadedDocumentId = uploadedDocId;
		req.DocTypeName = doctype;
		portal.signAcknowledgementTask(req);

		//		boolean count1 = portal.verifyTaskCount("2");
		//		TestValidation.IsTrue(count1, 
		//				"Total Task Count Verified",
		//				"FAILED to verify task count");	

	}

	@Test(description = "Document Upload Task", priority = 0)
	public void Document_Upload_Task() {

		auth.setSupplierAccessToken("supplier","123#");

		String taskMasterId = portal.verifyTasks(resourceInstanceId,suppInstance,docUploadTaskName);

		String[] IDs = taskMasterId.split("\\s+");

		suppdocmasterId = IDs[0];
		String taskId = IDs[1];

		//		boolean count = portal.verifyTaskCount("2");
		//		TestValidation.IsTrue(count, 
		//				"Total Task Count Verified",
		//				"FAILED to verify task count");		

		RequirementTaskDetails req = new RequirementTaskDetails();

		req.DMasterId = suppdocmasterId;
		req.DTaskId = taskId;
		req.docUploadReqId = suppDocReqId;
		req.DocTypeName = doctype;
		req.docTypeId = createdDocTypeId;
		req.supplierInstanceId = resourceInstanceId;
		req.supplierInstanceName = suppInstance;

		portal.completeDocRequirementTask(req,true);

		//		boolean count1 = portal.verifyTaskCount("1");
		//		TestValidation.IsTrue(count1, 
		//				"Total Task Count Verified",
		//				"FAILED to verify task count");	

	}

	@Test(description = "Complete Form Task", priority = 1)
	public void Complete_Form_Task() {

		auth.setSupplierAccessToken("supplier","123#");

		String taskMasterId = portal.verifyTasks(resourceInstanceId,suppInstance,formTaskName);		
		String[] IDs = taskMasterId.split("\\s+");

		suppformMasterId = IDs[0] ;
		String taskId = IDs[1];
		//
		//		boolean count = portal.verifyTaskCount("1");
		//		TestValidation.IsTrue(count, 
		//				"Total Task Count Verified",
		//				"FAILED to verify task count");		

		String timeformat = "hh:mm:ss.000";
		String time = dt.getTime(timeformat);
		String date = dt.getDate();
		String datetime = date+"T"+time+"Z";

		String formrefId = portal.getForm(formId);
		String formTypeId = portal.getFormTypeId(formId, resourceInstanceId,suppInstance);

		HashMap<String,String> fielddetails = new LinkedHashMap<String,String>();

		fielddetails.put("Number", "12");
		fielddetails.put("Text", "Test");
		fielddetails.put("One","");
		fielddetails.put("Multiple","");
		fielddetails.put("Date",datetime);
		fielddetails.put("Time",datetime);
		fielddetails.put("DT", datetime);
		fielddetails.put("Identifier","Test1");

		List<FormData> formData = portal.getFormData(formId, resourceInstanceId, suppInstance);

		RequirementTaskDetails details = new RequirementTaskDetails();

		details.formId = formId;
		details.questformname = questformname;
		details.formrefId = formrefId;
		details.formTypeId = formTypeId;
		details.supplierInstanceId = resourceInstanceId;
		details.supplierInstanceName = suppInstance;
		details.formReqId = suppFormReqId;
		details.supplierCatName = suppCat;
		details.FMasterId = suppformMasterId;
		details.FTaskId = taskId;

		suppformdocId = portal.addQuestForm(formData,fielddetails,details,true,false);

		//		boolean count1 = portal.verifyTaskCount("0");
		//		TestValidation.IsTrue(count1, 
		//				"Total Task Count Verified",
		//				"FAILED to verify task count");	

	}

	@Test(description = "Inbox Supp Document Task", priority = 2)
	public void Inbox_SuppDocument_Task() {

		auth.setAccessToken();

		String documentId = inbox.getTasks(wgId, docUploadTaskName);
		String[] document = documentId.split("\\s+");

		String taskId = document[0] ;
		String docId = document[1];

		TaskDetails detail = new TaskDetails();
		detail.docTypeId = docId;
		detail.TaskId = taskId;
		detail.MasterTaskId = suppdocmasterId;

		boolean accept = inbox.approveRejectTask(detail, true);
		TestValidation.IsTrue(accept, "Task Accepted",
				"FAILED to accept task ");

		String doctypeId = documents.getDocumentTypeId(DocType.DOCUMENTTYPES);

		boolean verify = documents.verifyDocument(doctype, createdDocTypeId, doctypeId,docId);
		TestValidation.IsTrue(verify, "Quest Form Submission Verified", "Could Not verify Quest Form Submission");

	}

	@Test(description = "Inbox Supp Form Task", priority = 3)
	public void Inbox_SuppForm_Task() {

		auth.setAccessToken();

		String formTaskId = inbox.getTasks(wgId, formTaskName);
		String[] IDs = formTaskId.split("\\s+");

		String taskId = IDs[0] ;

		TaskDetails detail = new TaskDetails();
		detail.docTypeId = suppformdocId;
		detail.TaskId = taskId;
		detail.MasterTaskId = suppformMasterId;

		boolean accept = inbox.approveRejectTask(detail, true);
		TestValidation.IsTrue(accept, "Task Accepted",
				"FAILED to accept task ");

		String doctypeId = documents.getDocumentTypeId(DocType.DOCUMENTTYPES);

		String createddoctypeId = documents.searchDocType(questformname, doctypeId);

		boolean verify = documents.verifyDocument(questformname, createddoctypeId, doctypeId,suppformdocId);
		TestValidation.IsTrue(verify, "Quest Form Submission Verified", "Could Not verify Quest Form Submission");


	}

	@Test(description = "Signing Acknowledgement Task", priority = 4)
	public void Item_Sign_Acknowledgement_Task() {

		auth.setSupplierAccessToken("supplier","123#");

		String taskMasterId = portal.verifyTasks(resourceInstanceId,suppInstance,itemacknowledgmentTaskName);		
		String[] IDs = taskMasterId.split("\\s+");

		String masterId = IDs[0] ;
		String taskId = IDs[1];

		//		boolean count = portal.verifyTaskCount("3");
		//		TestValidation.IsTrue(count, 
		//				"Total Task Count Verified",
		//				"FAILED to verify task count");		

		RequirementTaskDetails req = new RequirementTaskDetails();

		req.AMasterId = masterId;
		req.ATaskId = taskId;
		req.UploadedDocumentId = uploadedDocId;
		req.DocTypeName = doctype;
		portal.signAcknowledgementTask(req);

		//		boolean count1 = portal.verifyTaskCount("2");
		//		TestValidation.IsTrue(count1, 
		//				"Total Task Count Verified",
		//				"FAILED to verify task count");	

	}

	@Test(description = "Document Upload Task", priority = 5)
	public void Item_Document_Upload_Task() {

		auth.setSupplierAccessToken("supplier","123#");

		String taskMasterId = portal.verifyTasks(resourceInstanceId,suppInstance,itemdocUploadTaskName);

		String[] IDs = taskMasterId.split("\\s+");

		itemdocMasterId = IDs[0];
		String taskId = IDs[1];

		//		boolean count = portal.verifyTaskCount("2");
		//		TestValidation.IsTrue(count, 
		//				"Total Task Count Verified",
		//				"FAILED to verify task count");		

		RequirementTaskDetails req = new RequirementTaskDetails();

		req.DMasterId = itemdocMasterId;
		req.DTaskId = taskId;
		req.docUploadReqId = itemDocReqId;
		req.DocTypeName = doctype;
		req.docTypeId = createdDocTypeId;
		req.itemresourceId = itemInstanceId;
		req.itemResourceName = itemInstance;

		portal.completeDocRequirementTask(req,false);

		//		boolean count1 = portal.verifyTaskCount("1");
		//		TestValidation.IsTrue(count1, 
		//				"Total Task Count Verified",
		//				"FAILED to verify task count");	

	}

	@Test(description = "Complete Form Task", priority = 6)
	public void Item_Complete_Form_Task() {

		auth.setSupplierAccessToken("supplier","123#");

		String taskMasterId = portal.verifyTasks(resourceInstanceId,suppInstance,itemformTaskName);		
		String[] IDs = taskMasterId.split("\\s+");

		itemformMasterId = IDs[0] ;
		String taskId = IDs[1];
		//
		//		boolean count = portal.verifyTaskCount("1");
		//		TestValidation.IsTrue(count, 
		//				"Total Task Count Verified",
		//				"FAILED to verify task count");		

		String timeformat = "hh:mm:ss.000";
		String time = dt.getTime(timeformat);
		String date = dt.getDate();
		String datetime = date+"T"+time+"Z";

		String formrefId = portal.getForm(formId);
		String formTypeId = portal.getFormTypeId(formId, itemInstance,itemInstance);

		HashMap<String,String> fielddetails = new LinkedHashMap<String,String>();

		fielddetails.put("Number", "12");
		fielddetails.put("Text", "Test");
		fielddetails.put("One","");
		fielddetails.put("Multiple","");
		fielddetails.put("Date",datetime);
		fielddetails.put("Time",datetime);
		fielddetails.put("DT", datetime);
		fielddetails.put("Identifier","Test1");

		List<FormData> formData = portal.getFormData(formId, itemInstanceId, itemInstance);

		RequirementTaskDetails details = new RequirementTaskDetails();

		details.formId = formId;
		details.questformname = questformname;
		details.formrefId = formrefId;
		details.formTypeId = formTypeId;
		details.supplierInstanceId = resourceInstanceId;
		details.supplierInstanceName = suppInstance;
		details.formReqId = itemFormReqId;
		details.FMasterId = itemformMasterId;
		details.FTaskId = taskId;
		details.itemresourceId = itemInstanceId;
		details.itemResourceName = itemInstance;
		details.itemCatName = itemCat;

		itemFormDocId = portal.addQuestForm(formData,fielddetails,details,false, false);

	}

	@Test(description = "Inbox Supp Document Task", priority = 7)
	public void Inbox_ItemDocument_Task() {

		auth.setAccessToken();

		String docTaskId = inbox.getTasks(wgId, itemdocUploadTaskName);
		String[] itemId = docTaskId.split("\\s+");

		String taskId = itemId[0] ;
		String docId = itemId[1];

		TaskDetails detail = new TaskDetails();
		detail.docTypeId = docId;
		detail.TaskId = taskId;
		detail.MasterTaskId = itemdocMasterId;

		boolean accept = inbox.approveRejectTask(detail, true);
		TestValidation.IsTrue(accept, "Task Accepted",
				"FAILED to accept task ");

		String doctypeId = documents.getDocumentTypeId(DocType.DOCUMENTTYPES);

		boolean verify = documents.verifyDocument(doctype, createdDocTypeId, doctypeId,docId);
		TestValidation.IsTrue(verify, "Quest Form Submission Verified", "Could Not verify Quest Form Submission");


	}

	@Test(description = "Inbox Item Form Task", priority = 8)
	public void Inbox_ItemForm_Task() {

		auth.setAccessToken();

		String formTaskId = inbox.getTasks(wgId, itemformTaskName);
		String[] IDs = formTaskId.split("\\s+");

		String taskId = IDs[0] ;

		TaskDetails detail = new TaskDetails();
		detail.docTypeId = itemFormDocId;
		detail.TaskId = taskId;
		detail.MasterTaskId = itemformMasterId;

		boolean accept = inbox.approveRejectTask(detail, true);
		TestValidation.IsTrue(accept, "Task Accepted",
				"FAILED to accept task ");

		String doctypeId = documents.getDocumentTypeId(DocType.DOCUMENTTYPES);

		String createddoctypeId = documents.searchDocType(questformname, doctypeId);

		boolean verify = documents.verifyDocument(questformname, createddoctypeId, doctypeId,itemFormDocId);
		TestValidation.IsTrue(verify, "Quest Form Submission Verified", "Could Not verify Quest Form Submission");


	}

	@Test(description = "Supplier Reject Form Task", priority = 9)
	public void Supplier_Form_Reject_Task() {

		String formdocId = null;

		auth.setSupplierAccessToken("supplier","123#");

		String taskMasterId = portal.verifyTasks(resourceInstanceId,suppInstance,formTaskName1);		
		String[] IDs = taskMasterId.split("\\s+");

		String masterTaskId = IDs[0] ;
		String taskId = IDs[1];

		String timeformat = "hh:mm:ss.000";
		String time = dt.getTime(timeformat);
		String date = dt.getDate();
		String datetime = date+"T"+time+"Z";

		String formrefId = portal.getForm(formId);
		String formTypeId = portal.getFormTypeId(formId, resourceInstanceId, suppInstance);

		HashMap<String,String> fielddetails = new LinkedHashMap<String,String>();

		fielddetails.put("Number", "12");
		fielddetails.put("Text", "Test");
		fielddetails.put("One","");
		fielddetails.put("Multiple","");
		fielddetails.put("Date",datetime);
		fielddetails.put("Time",datetime);
		fielddetails.put("DT", datetime);
		fielddetails.put("Identifier","Test1");

		List<FormData> formData = portal.getFormData(formId, resourceInstanceId, suppInstance);

		RequirementTaskDetails details = new RequirementTaskDetails();

		details.formId = formId;
		details.questformname = questformname;
		details.formrefId = formrefId;
		details.formTypeId = formTypeId;
		details.supplierInstanceId = resourceInstanceId;
		details.supplierInstanceName = suppInstance;
		details.formReqId = suppFormReqId1;
		details.supplierCatName = suppCat;
		details.FMasterId = masterTaskId;
		details.FTaskId = taskId;

		formdocId= portal.addQuestForm(formData,fielddetails,details,true, false);

		auth.setAccessToken();

		String formTaskId = inbox.getTasks(wgId, formTaskName1);
		String[] suppId = formTaskId.split("\\s+");

		String suppformtaskId = suppId[0] ;

		TaskDetails detail = new TaskDetails();
		detail.docTypeId = formdocId;
		detail.TaskId = suppformtaskId;
		detail.MasterTaskId = masterTaskId;		

		boolean reject = inbox.approveRejectTask(detail, false);
		TestValidation.IsTrue(reject, "Task Rejctedted",
				"FAILED to reject task ");

		auth.setSupplierAccessToken("supplier","123#");

		String taskIds = portal.verifyTasks(resourceInstanceId,suppInstance,formTaskName1);		
		String[] ID1 = taskIds.split("\\s+");

		String masterTaskId1 = ID1[0] ;
		String taskId1 = ID1[1];	

		RequirementTaskDetails details1 = new RequirementTaskDetails();

		details1.formId = formId;
		details1.questformname = questformname;
		details1.formrefId = formrefId;
		details1.formTypeId = formTypeId;
		details1.supplierInstanceId = resourceInstanceId;
		details1.supplierInstanceName = suppInstance;
		details1.formReqId = suppFormReqId1;
		details1.supplierCatName = suppCat;
		details1.FMasterId = masterTaskId1;
		details1.FTaskId = taskId1;
		details1.rejectId = formdocId;

		String formDocId1 = portal.addQuestForm(formData,fielddetails,details1,true,true);

		auth.setAccessToken();

		String formTaskId1 = inbox.getTasks(wgId, formTaskName1);
		String[] suppId1 = formTaskId1.split("\\s+");

		String sformtaskId1 = suppId1[0] ;

		TaskDetails detail1 = new TaskDetails();
		detail1.docTypeId = formDocId1;
		detail1.TaskId = sformtaskId1;
		detail1.MasterTaskId = masterTaskId;		

		boolean accept = inbox.approveRejectTask(detail, true);
		TestValidation.IsTrue(accept, "Task Accepted",
				"FAILED to accept task ");

		String doctypeId = documents.getDocumentTypeId(DocType.DOCUMENTTYPES);

		String createddoctypeId = documents.searchDocType(questformname, doctypeId);

		boolean verify = documents.verifyDocument(questformname, createddoctypeId, doctypeId,formDocId1);
		TestValidation.IsTrue(verify, "Quest Form Submission Verified", "Could Not verify Quest Form Submission");

	}

	@Test(description = "Supplier Doc Reject Task", priority = 10)
	public void Supplier_Document_Reject_Task() {

		auth.setSupplierAccessToken("supplier","123#");

		String taskMasterId = portal.verifyTasks(resourceInstanceId,suppInstance,docUploadTaskName1);

		String[] IDs = taskMasterId.split("\\s+");

		String masterId = IDs[0];
		String taskId = IDs[1];

		RequirementTaskDetails req = new RequirementTaskDetails();

		req.DMasterId = masterId;
		req.DTaskId = taskId;
		req.docUploadReqId = suppDocReqId1;
		req.DocTypeName = doctype;
		req.docTypeId = createdDocTypeId;
		req.supplierInstanceId = resourceInstanceId;
		req.supplierInstanceName = suppInstance;

		portal.completeDocRequirementTask(req,true);

		auth.setAccessToken();

		String docTaskId = inbox.getTasks(wgId, docUploadTaskName1);
		String[] suppId = docTaskId.split("\\s+");

		String suppdoctaskId = suppId[0] ;
		String suppdocId = suppId[1];

		TaskDetails detail = new TaskDetails();
		detail.docTypeId = suppdocId;
		detail.TaskId = suppdoctaskId;
		detail.MasterTaskId = masterId;

		boolean reject = inbox.approveRejectTask(detail, false);
		TestValidation.IsTrue(reject, "Task Rejected",
				"FAILED to reject task ");

		auth.setSupplierAccessToken("supplier","123#");

		String taskMasterId1 = portal.verifyTasks(resourceInstanceId,suppInstance,docUploadTaskName1);

		String[] ID1 = taskMasterId1.split("\\s+");

		String masterId1 = ID1[0];
		String taskId1 = ID1[1];

		RequirementTaskDetails req1 = new RequirementTaskDetails();

		req1.DMasterId = masterId1;
		req1.DTaskId = taskId1;
		req1.docUploadReqId = suppDocReqId1;
		req1.DocTypeName = doctype;
		req1.docTypeId = createdDocTypeId;
		req1.supplierInstanceId = resourceInstanceId;
		req1.supplierInstanceName = suppInstance;
		req1.rejectId = suppdocId;

		portal.completeDocRequirementTask(req1,true);

		auth.setAccessToken();

		String docTaskId1 = inbox.getTasks(wgId, docUploadTaskName1);
		String[] suppId1 = docTaskId1.split("\\s+");

		String suppdoctaskId1 = suppId1[0] ;
		String suppdocId1 = suppId1[1];

		TaskDetails detail1 = new TaskDetails();
		detail1.docTypeId = suppdocId1;
		detail1.TaskId = suppdoctaskId1;
		detail1.MasterTaskId = masterId;

		boolean accept = inbox.approveRejectTask(detail1, true);
		TestValidation.IsTrue(accept, "Task Accepted",
				"FAILED to accept task ");

		String doctypeId = documents.getDocumentTypeId(DocType.DOCUMENTTYPES);

		boolean verify = documents.verifyDocument(doctype, createdDocTypeId, doctypeId,suppdocId1);
		TestValidation.IsTrue(verify, "Quest Form Submission Verified", "Could Not verify Quest Form Submission");

	}

	@Test(description = "Item doc Reject Task", priority = 11)
	public void Item_Document_Reject_Task() {

		auth.setSupplierAccessToken("supplier","123#");

		String taskMasterId = portal.verifyTasks(resourceInstanceId,suppInstance,itemdocUploadTaskName1);

		String[] IDs = taskMasterId.split("\\s+");

		String masterId = IDs[0];
		String taskId = IDs[1];

		RequirementTaskDetails req = new RequirementTaskDetails();

		req.DMasterId = masterId;
		req.DTaskId = taskId;
		req.docUploadReqId = itemDocReqId1;
		req.DocTypeName = doctype;
		req.docTypeId = createdDocTypeId;
		req.itemresourceId = itemInstanceId;
		req.itemResourceName = itemInstance;

		portal.completeDocRequirementTask(req,false);

		auth.setAccessToken();

		String docTaskId = inbox.getTasks(wgId, itemdocUploadTaskName1);
		String[] itemId = docTaskId.split("\\s+");

		String itemdoctaskId = itemId[0] ;
		String itemdocId = itemId[1];

		TaskDetails detail = new TaskDetails();
		detail.docTypeId = itemdocId;
		detail.TaskId = itemdoctaskId;
		detail.MasterTaskId = masterId;

		boolean reject = inbox.approveRejectTask(detail, false);
		TestValidation.IsTrue(reject, "Task Rejected",
				"FAILED to reject task ");

		auth.setSupplierAccessToken("supplier","123#");

		String taskMasterId1 = portal.verifyTasks(resourceInstanceId,suppInstance,itemdocUploadTaskName1);

		String[] ID1 = taskMasterId1.split("\\s+");

		String masterId1= ID1[0];
		String taskId1 = ID1[1];

		RequirementTaskDetails req1 = new RequirementTaskDetails();

		req1.DMasterId = masterId1;
		req1.DTaskId = taskId1;
		req1.docUploadReqId = itemDocReqId1;
		req1.DocTypeName = doctype;
		req1.docTypeId = createdDocTypeId;
		req1.itemresourceId = itemInstanceId;
		req1.itemResourceName = itemInstance;
		req1.rejectId = itemdocId;

		portal.completeDocRequirementTask(req1,false);

		auth.setAccessToken();

		String docTaskId1 = inbox.getTasks(wgId, itemdocUploadTaskName1);
		String[] suppId1 = docTaskId1.split("\\s+");

		String suppdoctaskId1 = suppId1[0] ;
		String suppdocId1 = suppId1[1];

		TaskDetails detail1 = new TaskDetails();
		detail1.docTypeId = suppdocId1;
		detail1.TaskId = suppdoctaskId1;
		detail1.MasterTaskId = masterId;

		boolean accept = inbox.approveRejectTask(detail, true);
		TestValidation.IsTrue(accept, "Task Accepted",
				"FAILED to accept task ");

		String doctypeId = documents.getDocumentTypeId(DocType.DOCUMENTTYPES);

		boolean verify = documents.verifyDocument(doctype, createdDocTypeId, doctypeId,itemdocId);
		TestValidation.IsTrue(verify, "Quest Form Submission Verified", "Could Not verify Quest Form Submission");		

	}

	@Test(description = "Item Form Reject Task", priority = 12)
	public void Item_Form_Reject_Task() {

		String formDocId = null;

		auth.setSupplierAccessToken("supplier","123#");

		String taskMasterId = portal.verifyTasks(resourceInstanceId,suppInstance,itemformTaskName1);		
		String[] IDs = taskMasterId.split("\\s+");

		String masterId = IDs[0] ;
		String taskId = IDs[1];	

		String timeformat = "hh:mm:ss.000";
		String time = dt.getTime(timeformat);
		String date = dt.getDate();
		String datetime = date+"T"+time+"Z";

		String formrefId = portal.getForm(formId);
		String formTypeId = portal.getFormTypeId(formId, itemInstance,itemInstance);

		HashMap<String,String> fielddetails = new LinkedHashMap<String,String>();

		fielddetails.put("Number", "12");
		fielddetails.put("Text", "Test");
		fielddetails.put("One","");
		fielddetails.put("Multiple","");
		fielddetails.put("Date",datetime);
		fielddetails.put("Time",datetime);
		fielddetails.put("DT", datetime);
		fielddetails.put("Identifier","Test1");

		List<FormData> formData = portal.getFormData(formId, itemInstanceId, itemInstance);

		RequirementTaskDetails details = new RequirementTaskDetails();

		details.formId = formId;
		details.questformname = questformname;
		details.formrefId = formrefId;
		details.formTypeId = formTypeId;
		details.supplierInstanceId = resourceInstanceId;
		details.supplierInstanceName = suppInstance;
		details.formReqId = itemFormReqId1;
		details.FMasterId = masterId;
		details.FTaskId = taskId;
		details.itemresourceId = itemInstanceId;
		details.itemResourceName = itemInstance;
		details.itemCatName = itemCat;

		formDocId = portal.addQuestForm(formData,fielddetails,details,false,false);

		auth.setAccessToken();

		String formTaskId = inbox.getTasks(wgId, itemformTaskName1);
		String[] itemId = formTaskId.split("\\s+");

		String itemdoctaskId = itemId[0];

		TaskDetails detail = new TaskDetails();
		detail.docTypeId = formDocId;
		detail.TaskId = itemdoctaskId;
		detail.MasterTaskId = masterId;		

		boolean reject = inbox.approveRejectTask(detail, false);
		TestValidation.IsTrue(reject, "Task Rejected",
				"FAILED to reject task ");

		auth.setSupplierAccessToken("supplier","123#");

		String taskIds = portal.verifyTasks(resourceInstanceId,suppInstance,itemformTaskName1);		
		String[] ID1 = taskIds.split("\\s+");

		String taskId1 = ID1[0] ;
		String masterId1 = ID1[1];	

		RequirementTaskDetails details1 = new RequirementTaskDetails();

		details1.formId = formId;
		details1.questformname = questformname;
		details1.formrefId = formrefId;
		details1.formTypeId = formTypeId;
		details1.supplierInstanceId = resourceInstanceId;
		details1.supplierInstanceName = suppInstance;
		details1.formReqId = itemFormReqId1;
		details1.FMasterId = taskId1;
		details1.FTaskId = masterId1;
		details1.itemresourceId = itemInstanceId;
		details1.itemResourceName = itemInstance;
		details1.itemCatName = itemCat;
		details1.rejectId = formDocId;	

		String formDocId1 = portal.addQuestForm(formData,fielddetails,details1,false,true);

		auth.setAccessToken();

		String docReqId = inbox.getTasks(wgId, itemformTaskName1);
		String[] itemIds = docReqId.split("\\s+");

		String itemdoctaskId1= itemIds[0];

		TaskDetails detail1 = new TaskDetails();
		detail1.docTypeId = formDocId1;
		detail1.TaskId = itemdoctaskId1;
		detail1.MasterTaskId = masterId;		

		boolean accept = inbox.approveRejectTask(detail1, true);
		TestValidation.IsTrue(accept, "Task Accepted",
				"FAILED to accept task");

		String doctypeId = documents.getDocumentTypeId(DocType.DOCUMENTTYPES);


		boolean verify = documents.verifyDocument(doctype, createdDocTypeId, doctypeId,formDocId1);
		TestValidation.IsTrue(verify, "Quest Form Submission Verified", "Could Not verify Quest Form Submission");

	}
}
