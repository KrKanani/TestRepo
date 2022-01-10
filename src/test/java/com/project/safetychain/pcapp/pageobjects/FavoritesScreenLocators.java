package com.project.safetychain.pcapp.pageobjects;

public class FavoritesScreenLocators {

	public static final String FORM_SEARCH = "//*[@AutomationId='SearchText']";
	public static final String FAVORITE_FORM = "//*[@LocalizedControlType='text' and @Name='FORM_NAME']";
	public static final String FAVORITES_COUNT = "//*[@Name='Favorites']/following-sibling::*[@ClassName='TextBlock']";

	public static final String VERSION_DETAILS = "//*[@Name='Version :']/following-sibling::*[@ClassName='TextBlock']";
	public static final String MODIFIED_BY_DETAILS = "//*[@Name='Modified By :']/following-sibling::*[@ClassName='TextBlock']";
	public static final String MODIFIED_ON_DETAILS = "//*[@Name='Modified On :']/following-sibling::*[@ClassName='TextBlock']";

	public static final String SELECT_FORM = "//*[@ClassName='TextBlock' and @Name='FORM_NAME']";
	public static final String REMOVE_FAVORITE_FORM_TOGGLE =  "//*[@AutomationId='RemoveFavorite']";
	
	public static final String NO_FAVOURITES_LBL = "//*[@Name='No Favorite found.']";

}
