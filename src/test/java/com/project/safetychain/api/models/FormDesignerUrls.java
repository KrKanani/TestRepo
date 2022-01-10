package com.project.safetychain.api.models;

public class FormDesignerUrls {

	/** Web APIs */
	public static final String SAVE_FORM_DESIGN_URL = "api/api/FormDesigner/SaveFormDesign";
	public static final String ADD_FIELD_URL = "api/api/FormDesigner/AddField";
	public static final String SUBMIT_FORM_URL = "api/api/FormDesigner/SubmitFormDesign";
	public static final String VALIDATE_RELEASE_URL = "api/api/Forms/Pending/ValidateReleaseForm";
	public static final String RELEASE_FORM_URL = "api/api/Forms/Pending/ReleaseForm";
	public static final String GET_FORMS_URL = "api/api/Forms/GetForms";
	public static final String SAVE_FORMS_VERIFICATION_URL = "api/api/Forms/SaveFormsVerification";
	public static final String GET_ROOT_LOCATIONS_CAT_URL = "api/api/Location/GetRootLocationsCategories";
	public static final String MANAGE_RESOURCE_CATEGORIES_URL = "api/api/Resources/ManageResourceCategories";
	public static final String GET_FORM_LOCATION_URL = "api/api/Forms/GetForm";
	public static final String GET_FORM_RESOURCES_LOC_URL = "api/api/Forms/GetFormResourcesForLocation";

	
	/** DPT APIs */
	public static final String DPT_SAVE_FORM_DESIGN_URL = "api/integration/saveformdesign";
	public static final String DPT_ADD_UPDATE_FORM_COMPLIANCE_URL = "api/Integration/AddUpdateFormCompliance";
	public static final String DPT_GET_FORM_COMPLIANCE_URL = "api/Integration/GetFormCompliance";
	public static final String DPT_GET_FORM_DEFINITION_URL = "api/integration/GetFormDefinition";
	
}
