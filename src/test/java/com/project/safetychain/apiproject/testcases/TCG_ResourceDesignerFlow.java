package com.project.safetychain.apiproject.testcases;

import java.util.List;

import org.json.simple.parser.ParseException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.project.safetychain.apiproject.models.Auth;
import com.project.safetychain.apiproject.models.ResourceDesigner;
import com.project.safetychain.apiproject.models.ResourceDesigner.ResourceTypes;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.utilities.ApiUtils;
import com.project.utilities.CommonMethods;
import com.project.utilities.JSONUtils;

public class TCG_ResourceDesignerFlow extends TestBase{
	
	Auth auth = null;
	ApiUtils apiUtils = null;
	ResourceDesigner resource = null;
	JSONUtils jsnUtils = null;
	
	String locationCat = CommonMethods.dynamicText("LCat");
	String supplierCat = CommonMethods.dynamicText("SuppC");
	String supplier1Cat = CommonMethods.dynamicText("S1Cat");
	
	String equipmentCat = CommonMethods.dynamicText("ECat");
	String equipment1Cat = CommonMethods.dynamicText("E1Cat");
	

	@BeforeClass(alwaysRun = true)
	public void groupInit() {

		auth = new Auth();
		auth.setAccessToken();
		resource = new ResourceDesigner();
		jsnUtils = new JSONUtils();
		apiUtils = new ApiUtils();

	}
	
	@Test(description = "CreateResouceCategory")
	public void Create_ResourceCategory() throws InterruptedException, ParseException {

		String resourceTypeId = resource.getResourceTypeId(ResourceTypes.LOCATIONS);
		
		resource.getResourceDesignerFields(resourceTypeId);
	
		resource.createResourceCategory(resourceTypeId,resourceTypeId,locationCat);		

	}
	
	@Test(description = "Enable/Disable Resource Type")
	public void Enable_Disable_ResourceType() throws InterruptedException, ParseException {

		String resourceTypeId = resource.getResourceTypeId(ResourceTypes.CUSTOMERS);
	
		boolean disable = resource.enableDisableResourceType(resourceTypeId, false);
		TestValidation.IsTrue(disable, 
				"Resource Type disabled - "+ResourceTypes.CUSTOMERS,
				"FAILED to disable resource type - "+ResourceTypes.CUSTOMERS);
		
		boolean enable = resource.enableDisableResourceType(resourceTypeId, true);
		TestValidation.IsTrue(enable, 
				"Resource Type enabled - "+ResourceTypes.CUSTOMERS,
				"FAILED to enable resource type - "+ResourceTypes.CUSTOMERS);
		
	}
	
	@Test(description = "Enable/Disable Resource Type Field")
	public void Enable_Disable_Field() throws InterruptedException, ParseException {
		
		String resourceTypeId = resource.getResourceTypeId(ResourceTypes.ITEMS);
		
		List<String> fields = resource.getResourceDesignerFields(resourceTypeId);
		
		String field = fields.get(1);
		
		boolean enable = resource.enableDisableField(resourceTypeId, field, true);
		TestValidation.IsTrue(enable, 
				"Resource Type field enabled - "+ResourceTypes.ITEMS,
				"FAILED to enabled resource type field - "+ResourceTypes.ITEMS);
		
		boolean disable = resource.enableDisableField(resourceTypeId, field, false);
		TestValidation.IsTrue(disable, 
				"Resource Type field disabled - "+ResourceTypes.ITEMS,
				"FAILED to disable resource type field - "+ResourceTypes.ITEMS);
		

	}
	
	@Test(description = "Edit Category")
	public void Edit_Category() throws InterruptedException, ParseException {
		
		String resourceTypeId = resource.getResourceTypeId(ResourceTypes.SUPPLIERS);
		
		resource.getResourceDesignerFields(resourceTypeId);
		
		String categoryId = resource.createResourceCategory(resourceTypeId,resourceTypeId,supplierCat);		
		
		boolean edit = resource.editResourceCategory(categoryId,resourceTypeId, categoryId, supplier1Cat);
		TestValidation.IsTrue(edit, 
				"Resource Category edited - "+ResourceTypes.SUPPLIERS,
				"FAILED to edit resource Category - "+ResourceTypes.SUPPLIERS);
		
	}
	
	@Test(description = "Delete Category")
	public void Delete_Category() throws InterruptedException, ParseException {
		
		String resourceTypeId = resource.getResourceTypeId(ResourceTypes.EQUIPMENT);
		
		resource.getResourceDesignerFields(resourceTypeId);
		
		String categoryId = resource.createResourceCategory(resourceTypeId,resourceTypeId,equipmentCat);	
		
		String categoryId1 = resource.createResourceCategory(categoryId,resourceTypeId,equipment1Cat);	
		
		boolean delete = resource.deleteResourceCategory(resourceTypeId, categoryId1);
		TestValidation.IsTrue(delete, 
				"Resource Category deleted - "+ResourceTypes.EQUIPMENT,
				"FAILED to delete resource Category - "+ResourceTypes.EQUIPMENT);
		
	}
	

}
