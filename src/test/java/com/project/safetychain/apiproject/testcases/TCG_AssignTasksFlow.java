package com.project.safetychain.apiproject.testcases;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.project.safetychain.apiproject.models.Auth;
import com.project.safetychain.apiproject.models.FSQABrowserForms;
import com.project.safetychain.apiproject.models.FSQABrowserForms.TaskDetails;
import com.project.safetychain.apiproject.models.FormDesigner;
import com.project.safetychain.apiproject.models.ResourceDesigner;
import com.project.safetychain.apiproject.models.Resources;
import com.project.safetychain.apiproject.models.WorkGroups;
import com.project.safetychain.apiproject.models.FormDesigner.FormFields;
import com.project.safetychain.apiproject.models.FormDesigner.FormTypes;
import com.project.safetychain.apiproject.models.ResourceDesigner.ResourceTypes;
import com.project.safetychain.apiproject.models.Resources.ResourcesData;
import com.project.safetychain.apiproject.models.WorkGroups.WorkGroupDetails;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.utilities.CommonMethods;


public class TCG_AssignTasksFlow extends TestBase{


	Auth auth = null;
	ResourceDesigner resourcedesigner = null;
	Resources resources = null;
	WorkGroups wg = null;
	FormDesigner designer =null;
	FormFields fields = null;
	FSQABrowserForms forms = null;


	public String resourceInstanceId = null;
	public String resourceInstance = null;
	public String wgName = null;
	public String wgId = null;
	public String locationCat = null;
	public String locationInstance = null;
	public String locationInstanceId = null;
	public String formEntityId = null;
	public String resourceCat = null;
	public String formname = null;
	public String formId = null;

	String taskname = CommonMethods.dynamicText("CheckTask");



	@BeforeClass(alwaysRun = true)
	public void groupInit() {

		auth = new Auth();
		auth.setAccessToken();
		resourcedesigner = new ResourceDesigner();
		resources = new Resources();
		wg = new WorkGroups();
		designer = new FormDesigner();
		forms = new FSQABrowserForms();

		resourceInstance = CommonMethods.dynamicText("EquipI");
		resourceCat = CommonMethods.dynamicText("EquipC");
		locationInstance = CommonMethods.dynamicText("LocI");
		locationCat = CommonMethods.dynamicText("LocC");
		formname = CommonMethods.dynamicText("CForm");
		wgName = CommonMethods.dynamicText("AW");

		String locationTypeId = resourcedesigner.getResourceTypeId(ResourceTypes.LOCATIONS);

		resourcedesigner.createResourceCategory(locationTypeId,locationTypeId,locationCat);

		String locationCatId = resources.getLocCatId(locationCat);

		List <String> enalbledFieldIds = resourcedesigner.getEnabledResourceTypeFields(locationTypeId);

		List <String> fieldValues = resourcedesigner.getFieldValue(enalbledFieldIds, ResourceTypes.LOCATIONS);

		String resourceTypeFields = resources.getResourceTypeFields(fieldValues,locationInstance);

		locationInstanceId = resources.createLocInstance(locationCatId,locationCat, locationInstance, resourceTypeFields);

		resourcedesigner = new ResourceDesigner();
		resources = new Resources();

		String resourceTypeId = resourcedesigner.getResourceTypeId(ResourceTypes.EQUIPMENT);

		resourcedesigner.getResourceDesignerFields(resourceTypeId);

		resourcedesigner.createResourceCategory(resourceTypeId,resourceTypeId,resourceCat);

		String resourceCatId = resources.getResourceCategoryId(resourceTypeId,resourceCat);

		List <String> enabledTypeFieldId1 = resourcedesigner.getEnabledResourceTypeFields(resourceTypeId);

		List <String> fieldValues1 = resourcedesigner.getFieldValue(enabledTypeFieldId1, ResourceTypes.EQUIPMENT);

		String fieldDetails = resources.getResourceTypeFields(fieldValues1, resourceInstance);

		resourceInstanceId = resources.createResourceInstance(fieldDetails,resourceTypeId,resourceInstance, resourceCatId, "true");

		ResourcesData data = new ResourcesData();

		data.locationCatName = locationCat;
		data.locationTypeId = locationTypeId;
		data.locationInstanceId = locationInstanceId;
		data.locationInstname = locationInstance;
		data.resourceTypeId = resourceTypeId;
		data.resourceInstanceId = resourceInstanceId;

		boolean link = resources.linkResourceLocation(data);
		TestValidation.IsTrue(link, 
				"Location Resource linked",
				"FAILED to link Location Resource");

		auth.setAccessToken();

		boolean resourcetree = designer.getResourceTree(resourceInstanceId,resourceCat,resourceInstance);
		TestValidation.IsTrue(resourcetree, 
				"Resource Tree retrived",
				"FAILED to get resource tree details");		

		List<String> formfield = Arrays.asList(FormFields.NUMERIC,FormFields.FREETEXT,FormFields.SELECTONE);

		HashMap<String,String> fieldnames = new LinkedHashMap<String,String>();

		fieldnames.put("Number", "Number");
		fieldnames.put("Text", "Text");
		fieldnames.put("SelectOne", "One");

		formId = designer.createForm(formname, FormTypes.CHECK, formfield, fieldnames, false, false);

		WorkGroupDetails workGroupDetails = new WorkGroupDetails();
		workGroupDetails.workgroupName = wgName;
		workGroupDetails.userIDs = Arrays.asList(WorkGroups.SUPERADMIN_USER_ID);

		wg.createWorkGroup(workGroupDetails);
		wgId = wg.getWorkGroupDetails(wgName, "Id");

	}

	@Test(description = "Assign Check Form task")
	public void Assign_Check_Form_Task() throws InterruptedException {

		TaskDetails details = new TaskDetails();
		details.FormId = formId;
		details.FormName = formname;
		details.WorkgroupId = wgId;
		details.WorkgroupName = wgName;
		details.LocationId = locationInstanceId;
		details.LocationName = locationInstance;
		details.ResourceId = resourceInstanceId;
		details.ResourceName = resourceInstance;
		details.TaskName = taskname;

		String taskId = forms.assignTasks(details);
		TestValidation.Equals(taskId, taskId, "Task Created", "Failed to create task");

	}

}