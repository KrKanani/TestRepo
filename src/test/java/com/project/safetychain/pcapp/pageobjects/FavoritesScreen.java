package com.project.safetychain.pcapp.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.project.utilities.ControlActions_PCApp;

import io.appium.java_client.windows.WindowsDriver;

public class FavoritesScreen extends CommonScreen{

	WindowsDriver<WebElement> PCAppDriver;
	WebDriverWait wait;
	ControlActions_PCApp desktopControlActions;

	public FavoritesScreen(WindowsDriver<WebElement> driver) {
		super(driver);
		this.PCAppDriver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 30);
		desktopControlActions = new ControlActions_PCApp(this.PCAppDriver);
	}


	@FindBy(xpath = FavoritesScreenLocators.FORM_SEARCH) 
	public WebElement SeachFormInp;

	@FindBy(xpath = FavoritesScreenLocators.FAVORITES_COUNT) 
	public WebElement FavoritesCount;

	@FindBy(xpath = FavoritesScreenLocators.VERSION_DETAILS) 
	public WebElement VersionDetails;

	@FindBy(xpath = FavoritesScreenLocators.MODIFIED_BY_DETAILS) 
	public WebElement ModifiedByDetails;

	@FindBy(xpath = FavoritesScreenLocators.MODIFIED_ON_DETAILS) 
	public WebElement ModifiedOnDetails;

	@FindBy(xpath = FavoritesScreenLocators.REMOVE_FAVORITE_FORM_TOGGLE) 
	public WebElement RemoveFavoriteForm;

	@FindBy(xpath = FavoritesScreenLocators.NO_FAVOURITES_LBL) 
	public WebElement NoFavouritesLbl;



	/**
	 * This method is used to check for form availability in 'Favorites' screen
	 * @author pashine_a
	 * @param formName
	 * @return boolean
	 */
	public boolean verifyFavoriteFormAvailability(String formName) {
		String favoriteForm;
		try {

			if(!desktopControlActions.isElementAvailable(FavoritesScreenLocators.FORM_SEARCH)) {
				logInfo("No Favorite Form Available");
				return false;
			}

			desktopControlActions.sendKeys(SeachFormInp, formName);
			threadsleep(2000);
			favoriteForm = desktopControlActions.getDynamicXPath(FavoritesScreenLocators.FAVORITE_FORM, "FORM_NAME", formName);
			if(!desktopControlActions.isElementAvailable(favoriteForm)) {
				logInfo("Favorite Form not shown");
				return false;
			}
			logInfo("Found form - "+formName);
			return true;
		}catch(Exception e) {
			logError("Failed to get form - '"+formName+"' - "+ e.getMessage());
			return false;
		}	
	}


	/**
	 * This method is used to verify the Favorite Form count in 'Favorites'
	 * @author pashine_a
	 * @param count
	 * @return boolean
	 */
	public boolean verifyFavoriteCount(int count) {
		String formCount = new Integer(count).toString();
		try {
			if(!FavoritesCount.getAttribute("Name").equals(formCount)){
				logError("Favorite form count is not correct");
				return false;
			}
			logInfo("Verified Favorite form count - "+formCount);
			return true;
		}catch (Exception e) {
			logError("Failed to verify Favorite form count - "+e.getMessage());
			return false;
		}
	}


	/**
	 * This method is used to verify the Favorite Form details in 'Favorites'
	 * @author pashine_a
	 * @param null
	 * @return boolean
	 */
	public boolean verifyFavoriteDetails() {
		try {
			String version = VersionDetails.getAttribute("Name");
			String modifiedBy = ModifiedByDetails.getAttribute("Name");
			String modifiedOn = ModifiedOnDetails.getAttribute("Name");

			if(version==null){
				logError("Unable to get Favorite Form version details");
				return false;
			}
			if(modifiedBy==null){
				logError("Unable to get Favorite Form version details");
				return false;
			}
			if(modifiedOn==null){
				logError("Unable to get Favorite Form version details");
				return false;
			}

			logInfo("Favorite form Version - "+version);
			logInfo("Favorite form Modified By - "+modifiedBy);
			logInfo("Favorite form Modified On - "+modifiedOn);
			return true;
		}catch (Exception e) {
			logError("Failed to verify Favorite form details - "+e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to unselect the the Favorite Form in 'Favorites'
	 * @author pashine_a
	 * @param formName
	 * @return boolean
	 */
	public boolean unselectFormFavorite(String formName) {
		WebElement Selectform = null;
		try {
			desktopControlActions.sendKeys(SeachFormInp, formName);
			threadsleep(2000);
			Selectform = desktopControlActions.getDynamicElement(FavoritesScreenLocators.SELECT_FORM, "FORM_NAME", formName);
			desktopControlActions.waitForElementToBeVisisble(Selectform);
			desktopControlActions.click(RemoveFavoriteForm);
			logInfo("Unselected the form from Favorites - "+formName);
			return true;
		}catch(Exception e) {
			logError("Failed to unselect the form from Favorites - '"+formName+"' - "+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to verify no favourites
	 * @author choubey_a
	 * @param 
	 * @return boolean
	 */
	public boolean verifyNoFavourites() {
		try {
			desktopControlActions.isElementDisplayed(NoFavouritesLbl);
			logInfo("Verified no favourites");
			return true;
		}catch(Exception e) {
			logError("Failed to verify no favourites - "+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to perform double click on 'Favorites' Tab
	 * @author pashine_a
	 * @param null
	 * @return boolean. If nothing is impacting on double click then returns TRUE
	 */
	public boolean doubleClickFavorites() {
		try {
			desktopControlActions.doubleClick(FavoritesMnu);
			if(!desktopControlActions.isElementDisplayed(NoFavouritesLbl)) {
				logError("Failed to double click on Favorites");
				return false;
			}
			logInfo("Multiple clicks performed on 'Favorites' Tab");
			return true;
		}catch(Exception e) {
			logError("Failed to double click on Favorites - "+ e.getMessage());
			return false;
		}	
	}

}
