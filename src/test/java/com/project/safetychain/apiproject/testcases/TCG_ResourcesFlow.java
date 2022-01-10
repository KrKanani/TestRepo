package com.project.safetychain.apiproject.testcases;

import java.util.List;

import org.json.simple.parser.ParseException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.project.safetychain.apiproject.models.Auth;
import com.project.safetychain.apiproject.models.ResourceDesigner;
import com.project.safetychain.apiproject.models.Resources;
import com.project.safetychain.apiproject.models.Resources.ResourcesData;
import com.project.safetychain.apiproject.models.ResourceDesigner.ResourceTypes;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.utilities.ApiUtils;
import com.project.utilities.CommonMethods;
import com.project.utilities.JSONUtils;

public class TCG_ResourcesFlow extends TestBase{

	Auth auth = null;
	ApiUtils apiUtils = null;
	Resources resources = null;
	JSONUtils jsnUtils = null;
	ResourceDesigner resourcedesigner = null;
	ResourcesData data = null;

	String locationInstance = null;
	String locationInstance1 = null;
	String equipmentInstance = null;
	String equipmentInstance1 = null;
	String locationCat = null;
	String equipmentCat = null;
	String locCatId = null;
	String equipCatId = null;

	@BeforeClass(alwaysRun = true)
	public void groupInit() {

		auth = new Auth();
		auth.setAccessToken();
		resources = new Resources();
		jsnUtils = new JSONUtils();
		apiUtils = new ApiUtils();
		resourcedesigner = new ResourceDesigner();
		data = new ResourcesData();

		locationCat = CommonMethods.dynamicText("LCat");
		locationInstance = CommonMethods.dynamicText("LInst");
		locationInstance1 = CommonMethods.dynamicText("L1Inst");
		equipmentCat = CommonMethods.dynamicText("ECat");
		equipmentInstance = CommonMethods.dynamicText("EInst");
		equipmentInstance1 = CommonMethods.dynamicText("E1Inst");

		String resourceTypeId = resourcedesigner.getResourceTypeId(ResourceTypes.LOCATIONS);

		resourcedesigner.getResourceDesignerFields(resourceTypeId);

		locCatId = resourcedesigner.createResourceCategory(resourceTypeId,resourceTypeId,locationCat);

		String equipmentTypeId = resourcedesigner.getResourceTypeId(ResourceTypes.EQUIPMENT);

		resourcedesigner.getResourceDesignerFields(equipmentTypeId);

		equipCatId = resourcedesigner.createResourceCategory(equipmentTypeId,equipmentTypeId,equipmentCat);

	}

	@Test(description = "Creation of location instance Creation")
	public void Create_Location_Instance() throws InterruptedException, ParseException {

		resourcedesigner = new ResourceDesigner();
		resources = new Resources();

		String lTypeId = resourcedesigner.getResourceTypeId(ResourceTypes.LOCATIONS);

		String locationCatId = resources.getLocCatId(locationCat);

		List <String> enalbledFieldIds = resourcedesigner.getEnabledResourceTypeFields(lTypeId);

		List <String> fieldValues = resourcedesigner.getFieldValue(enalbledFieldIds, ResourceTypes.LOCATIONS);

		String resourceTypeFields = resources.getResourceTypeFields(fieldValues,locationInstance);

		String locationInstanceId = resources.createLocInstance(locationCatId,locationCat, locationInstance, resourceTypeFields);

		boolean status = resources.getLocation(locationCatId, locationInstanceId, lTypeId);

		TestValidation.IsTrue(status, 
				"Location Instance created - "+locationInstance,
				"FAILED to create Location Instance - "+locationInstance);

	}

	@Test(description = "Creation of equipment instance Creation")
	public void Create_Equipment_Instance() throws InterruptedException, ParseException {

		resourcedesigner = new ResourceDesigner();
		resources = new Resources();

		String equipTypeId = resourcedesigner.getResourceTypeId(ResourceTypes.EQUIPMENT);

		String equipmentCatId = resources.getResourceCategoryId(equipTypeId,equipmentCat);

		List <String> enabledTypeFieldIds = resourcedesigner.getEnabledResourceTypeFields(equipTypeId);

		List <String> fieldValues = resourcedesigner.getFieldValue(enabledTypeFieldIds, ResourceTypes.EQUIPMENT);

		String fieldDetails = resources.getResourceTypeFields(fieldValues, equipmentInstance);

		resources.createResourceInstance(fieldDetails,equipTypeId,equipmentInstance, equipmentCatId, "true");


	}

	@Test(description = "Linking of equipment location linking")
	public void Resource_Location_Linking() throws InterruptedException {

		resourcedesigner = new ResourceDesigner();
		resources = new Resources();

		String locationTypeId = resourcedesigner.getResourceTypeId(ResourceTypes.LOCATIONS);

		List <String> enalbledFieldIds = resourcedesigner.getEnabledResourceTypeFields(locationTypeId);

		List <String> fieldValues = resourcedesigner.getFieldValue(enalbledFieldIds, ResourceTypes.LOCATIONS);

		String resourceTypeFields = resources.getResourceTypeFields(fieldValues,locationInstance1);

		String locationInstanceId = resources.createLocInstance(locCatId,locationCat, locationInstance1, resourceTypeFields);

		resourcedesigner = new ResourceDesigner();
		resources = new Resources();

		String equipTypeId = resourcedesigner.getResourceTypeId(ResourceTypes.EQUIPMENT);

		List <String> enabledTypeFieldIds = resourcedesigner.getEnabledResourceTypeFields(equipTypeId);

		List <String> fieldValues1 = resourcedesigner.getFieldValue(enabledTypeFieldIds, ResourceTypes.EQUIPMENT);

		String fieldDetails = resources.getResourceTypeFields(fieldValues1, equipmentInstance1);

		String instId = resources.createResourceInstance(fieldDetails,equipTypeId,equipmentInstance1, equipCatId, "true");

		ResourcesData data = new ResourcesData();
		
		data.locationCatName = locationCat;
		data.locationTypeId = locationTypeId;
		data.locationInstanceId = locationInstanceId;
		data.locationInstname = locationInstance;
		data.resourceTypeId = equipTypeId;
		data.resourceInstanceId = instId;

		boolean link = resources.linkResourceLocation(data);
		TestValidation.IsTrue(link, 
				"Location Resource linked",
				"FAILED to link Location Resource");

		ResourcesData data1 = new ResourcesData();
		data1.resourceTypeId = equipTypeId;
		data1.resourceInstanceId = instId;

		boolean verify = resources.verifyLinking(data);
		TestValidation.IsTrue(verify, 
				"Verified Location Resource linking",
				"FAILED to verify Location Resource Linking");
		
	}

}
