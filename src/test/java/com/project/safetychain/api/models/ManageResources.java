package com.project.safetychain.api.models;

public class ManageResources extends Auth {

	public String AddNewResourceUrl = baseURI + ManageResourcesUrls.ADD_NEW_RESOURCE_URL;
	public String LinkResourceUrl = baseURI + ManageResourcesUrls.LINK_RESOURCE_URL;
	public String ResourceDesignerFieldsUrl = baseURI + ManageResourcesUrls.RESOURCE_FIELD_URL;
	public String EditLocationForUsersUrl = baseURI + ManageResourcesUrls.EDIT_LOCATION_FOR_USERS_URL;

	public String otherAddNewResourceUrl = otherBaseURI + ManageResourcesUrls.ADD_NEW_RESOURCE_URL;
	public String otherLinkResourceUrl = otherBaseURI + ManageResourcesUrls.LINK_RESOURCE_URL;
	public String otherEditLocationForUsersUrl = otherBaseURI + ManageResourcesUrls.EDIT_LOCATION_FOR_USERS_URL;

	public class Data {
		public boolean IsEnable;
		public String Id;
		public String Name;
	}

}
