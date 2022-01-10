package com.project.safetychain.pcapp.pageobjects;

public class FormsScreenLocators {

	public static final String FORM_SEARCH_TXT = "//*[@Name='Search']";

	public static final String FORMS_REFRESH_BTN = "//*[@Name='Search']/following-sibling::*[@ClassName='Button']";
	public static final String FORMS_UPDATE_LBL = "//*[@ClassName='TextBlock' and @Name='Forms are up to date!']";
	public static final String REFRESH_YES_BTN = "//*[@ClassName='Button' and @Name='Yes']";

	public static final String FAVORITE_FORM_TOGGLE =  "//*[@AutomationId='ToggleFavorite']";

	public static final String SELECT_FORM = "//*[@ClassName='TextBlock' and @Name='FORM_NAME']";
	public static final String NEXT_BTN = "//*[@ClassName='Button' and @Name='NEXT']";

	public static final String MODIFIED_BY_LBL = "//*[@Name = 'Abhishek Pashine' and @ClassName='TextBlock']";

	public static final String VERSION_DETAILS = "//*[@Name='Version :']/following-sibling::*[@ClassName='TextBlock']";
	public static final String MODIFIED_BY_DETAILS = "//*[@Name='Modified By :']/following-sibling::*[@ClassName='TextBlock']";
	public static final String MODIFIED_ON_DETAILS = "//*[@Name='Modified On :']/following-sibling::*[@ClassName='TextBlock']";

	public static final String FORMS_COUNT = "//*[@Name='Forms']/following-sibling::*[@ClassName='TextBlock']";

	public static final String SEARCH_NAME_TGL_BTN =  "//*[@AutomationId='btnSearchByName']";
	public static final String SEARCH_RESOURCE_TGL_BTN =  "//*[@AutomationId='btnSearchByResource']";

	public static final String FULL_RESOURCE_NAME_LBL = "//*[@ClassName='TextBlock' and @Name='RESOURCE_NAME']";

}
