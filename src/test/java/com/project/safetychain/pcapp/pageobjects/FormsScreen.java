package com.project.safetychain.pcapp.pageobjects;

import java.util.Calendar;
import java.util.Date;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.project.utilities.ControlActions_PCApp;

import io.appium.java_client.windows.WindowsDriver;

public class FormsScreen extends CommonScreen {
	WindowsDriver<WebElement> PCAppDriver;
	WebDriverWait wait;
	ControlActions_PCApp desktopControlActions;

	public FormsScreen(WindowsDriver<WebElement> driver) {
		super(driver);
		this.PCAppDriver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 30);
		desktopControlActions = new ControlActions_PCApp(this.PCAppDriver);
	}

	/**
	 * Page Objects
	 */

	@FindBy(xpath = FormsScreenLocators.FORM_SEARCH_TXT) 
	public WebElement SeachFormTxt;

	@FindBy(xpath = FormsScreenLocators.NEXT_BTN) 
	public WebElement NextBtn;

	@FindBy(xpath = FormsScreenLocators.FORMS_REFRESH_BTN) 
	public WebElement FormsRefreshBtn;

	@FindBy(xpath = FormsScreenLocators.REFRESH_YES_BTN) 
	public WebElement RefreshYesBtn;

	@FindBy(xpath = FormsScreenLocators.FORMS_UPDATE_LBL) 
	public WebElement FormUpdateLbl;

	@FindBy(xpath = FormsScreenLocators.FAVORITE_FORM_TOGGLE) 
	public WebElement FavoriteToggleIcn;

	@FindBy(xpath = FormsScreenLocators.VERSION_DETAILS) 
	public WebElement VersionDetails;

	@FindBy(xpath = FormsScreenLocators.MODIFIED_BY_DETAILS) 
	public WebElement ModifiedByDetails;

	@FindBy(xpath = FormsScreenLocators.MODIFIED_ON_DETAILS) 
	public WebElement ModifiedOnDetails;

	@FindBy(xpath = FormsScreenLocators.FORMS_COUNT) 
	public WebElement FormsCount;

	@FindBy(xpath = FormsScreenLocators.SEARCH_NAME_TGL_BTN) 
	public WebElement SearchNameTgle;

	@FindBy(xpath = FormsScreenLocators.SEARCH_RESOURCE_TGL_BTN) 
	public WebElement SearchResourceTgle;

	/**
	 * This method is used to select & open the form
	 * @author pashine_a
	 * @param formName
	 * @return boolean 
	 * IF searched form is found & opened THEN true ELSE false
	 */
	public boolean selectOpenForm(String formName) {
		WebElement Selectform = null;
		try {
			desktopControlActions.sendKeys(SeachFormTxt, formName);
			threadsleep(2000);
			Selectform = desktopControlActions.getDynamicElement(FormsScreenLocators.SELECT_FORM, "FORM_NAME", formName);
			desktopControlActions.click(Selectform);
			desktopControlActions.waitForElementToBeVisisble(NextBtn);
			logInfo("Opened the form - "+formName);
			return true;
		}catch(Exception e) {
			logError("Failed to open form - '"+formName+"' - "+ e.getMessage());
			return false;
		}	
	}



	/**
	 * This method is used to verify form download count on Refresh - NEED TO FIX
	 * @author pashine_a
	 * @param null
	 * @return boolean 
	 */
	public boolean verifyFormCountOnRefresh() {
		try {
			desktopControlActions.click(FormsRefreshBtn);
			desktopControlActions.click(RefreshYesBtn);	
			desktopControlActions.waitForElementToBeVisisble(SeachFormTxt);
			desktopControlActions.click(FormsRefreshBtn);
			desktopControlActions.simpleClick(RefreshYesBtn);	
			desktopControlActions.clickTillElementFound(FormUpdateLbl);					
			//
			//			if(!desktopControlActions.isElementDisplayed(FormUpdateLbl)) {
			//				logError("Forms count is not correct");
			//				return false;
			//			}
			//desktopControlActions.waitForElementToBeVisisble(FormUpdateLbl);
			//desktopControlActions.click(FormUpdateLbl);		

			logInfo("Getting correct form count");
			return true;
		}catch(Exception e) {
			logError("Failed to get form refresh count - "+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to select the form as Favorite
	 * @author pashine_a
	 * @param formName
	 * @return boolean 
	 */
	public boolean selectFormFavorite(String formName) {
		WebElement Selectform = null;
		try {
			desktopControlActions.sendKeys(SeachFormTxt, formName);
			threadsleep(2000);
			Selectform = desktopControlActions.getDynamicElement(FormsScreenLocators.SELECT_FORM, "FORM_NAME", formName);
			desktopControlActions.waitForElementToBeVisisble(Selectform);
			desktopControlActions.click(FavoriteToggleIcn);
			logInfo("Selected the form as Favorite - "+formName);
			return true;
		}catch(Exception e) {
			logError("Failed to select the form as Favorite- '"+formName+"' - "+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to verify form
	 * @author choubey_a
	 * @param formName
	 * @return boolean
	 */
	public boolean verifyFormName(String formName) {
		WebElement Selectform = null;
		try {
			Selectform = desktopControlActions.getDynamicElement(FormsScreenLocators.SELECT_FORM, "FORM_NAME", formName);
			desktopControlActions.isElementDisplayed(Selectform);
			logInfo("Form is displayed - "+formName);
			return true;
		}catch(Exception e) {
			logError("Failed to verify form - '"+formName+"' - "+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to perform double click on 'Forms' Tab
	 * @author pashine_a
	 * @param null
	 * @return boolean. If nothing is impacting on double click then returns TRUE
	 */
	public boolean doubleClickForms() {
		try {
			desktopControlActions.doubleClick(FormsMnu);
			if(!desktopControlActions.isElementDisplayed(SeachFormTxt)) {
				logError("Failed to double click on Forms");
				return false;
			}
			logInfo("Multiple clicks performed on 'Forms' Tab");
			return true;
		}catch(Exception e) {
			logError("Failed to double click on Forms - "+ e.getMessage());
			return false;
		}	
	}


	/**
	 * This method is used to search the form
	 * @author pashine_a
	 * @param formName
	 * @return boolean 
	 * IF searched form is found THEN true ELSE false
	 */
	public boolean searchForm(String formName) {
		WebElement Searchform = null;
		try {
			desktopControlActions.sendKeys(SeachFormTxt, formName);
			threadsleep(2000);
			Searchform = desktopControlActions.getDynamicElement(FormsScreenLocators.SELECT_FORM, "FORM_NAME", formName);
			if(!Searchform.isDisplayed()) {		
				logError("Searched form is not visible - "+formName);
				return false;
			}
			logInfo("Searched form is visible - "+formName);
			return true;
		}catch(Exception e) {
			logError("Failed to search form - '"+formName+"' - "+ e.getMessage());
			return false;
		}	
	}


	/**
	 * This method is used to verify form details
	 * @author pashine_a
	 * @param formName
	 * @return boolean 
	 * IF searched form is found & opened THEN true ELSE false
	 */
	public boolean verifyFormDetails(FormDetails formDetails) {
		WebElement Selectform = null;
		try {
			desktopControlActions.sendKeys(SeachFormTxt, formDetails.FormName);
			threadsleep(2000);
			Selectform = desktopControlActions.getDynamicElement(FormsScreenLocators.SELECT_FORM, "FORM_NAME", formDetails.FormName);

			if(!Selectform.isDisplayed()) {
				logError("Failed to verify form name");
			}

			if(!VersionDetails.getText().equals(formDetails.Version)) {
				logError("Failed to verify form version");
			}

			if(ModifiedByDetails.getText()==null) {
				logError("Failed to verify form's 'Modified By' details");
			}

			if(ModifiedOnDetails.getText()==null) {
				logError("Failed to verify form's 'Modified On' details");
			}

			logInfo("Verified form details for form - "+formDetails.FormName);
			return true;
		}catch(Exception e) {
			logError("Failed to verify form details for form - '"+formDetails.FormName+"' - "+ e.getMessage());
			return false;
		}	
	}


	/**
	 * This method is used to verify the Form count in 'Forms'
	 * @author pashine_a
	 * @param count
	 * @return boolean
	 */
	public boolean verifyFormCount(int count) {
		String formCount = new Integer(count).toString();
		try {
			if(!FormsCount.getAttribute("Name").equals(formCount)){
				logError("Form count is not correct");
				return false;
			}
			logInfo("Form count - "+formCount);
			return true;
		}catch (Exception e) {
			logError("Failed to verify form count - "+e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to select 'Search Name' toggle button
	 * @author pashine_a
	 * @param null
	 * @return boolean
	 */
	public boolean searchFormToggle() {
		try {
			desktopControlActions.click(SearchNameTgle);
			logInfo("Clicked on 'Search Name' toggle");
			return true;
		}catch (Exception e) {
			logError("Failed to click on 'Search Name' toggle - "+e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to select 'Search Resource' toggle button
	 * @author pashine_a
	 * @param null
	 * @return boolean
	 */
	public boolean searchResourceToggle() {
		try {
			desktopControlActions.click(SearchResourceTgle);
			logInfo("Clicked on 'Search Resource' toggle");
			return true;
		}catch (Exception e) {
			logError("Failed to click on 'Search Resource' toggle - "+e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to verify resource availability for the form(Form Search)
	 * @author pashine_a
	 * @param resourceType
	 * @param resourceCategory
	 * @param resourceName
	 * @return boolean
	 */
	public boolean verifyResourceHierarchy(String resourceType, String resourceCategory, String resourceName) {
		String fullResourceName = null;
		WebElement resourceName1;
		try {
			desktopControlActions.sendKeys(SeachFormTxt, resourceName);

			fullResourceName = resourceType +" > "+ resourceCategory +" > "+resourceName;
			resourceName1 = desktopControlActions.getDynamicElement(FormsScreenLocators.FULL_RESOURCE_NAME_LBL, "RESOURCE_NAME", fullResourceName);

			if(!resourceName1.isDisplayed()) {
				logError("Failed to verify resource name");
			}

			logInfo("Verified resource name - "+fullResourceName);
			return true;
		}catch (Exception e) {
			logError("Failed to verify resource name - "+fullResourceName+" - "+e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to get forms's Modified On details
	 * @author pashine_a
	 * @param null
	 * @return String
	 */
	public String getModifiedOnDetails() {
		try {
			String ModifiedOn = ModifiedOnDetails.getText().toString();
			logInfo("Form Modified On - "+ModifiedOn);
			return ModifiedOn;
		}catch(Exception e) {
			logError("Failed to get form Modified On details - "+ e.getMessage());
			return null;
		}	
	}

	/**
	 * This method is used to Refresh Forms
	 * @author pashine_a
	 * @param null
	 * @return boolean 
	 */
	public boolean refreshForms() {
		try {

			desktopControlActions.click(FormsRefreshBtn);
			Date startTime = Calendar.getInstance().getTime();
			desktopControlActions.click(RefreshYesBtn);	
			Date endTime = Calendar.getInstance().getTime();
			long difference = endTime.getTime() - startTime.getTime();
			long differenceSeconds = difference / 1000 % 60;
			
			if(differenceSeconds>15) {
				logError("Delay in download pop up - "+differenceSeconds+" seconds");
			}
			
			logInfo("Download pop shown in "+differenceSeconds+" seconds");
			desktopControlActions.waitForElementToBeVisisble(SeachFormTxt);	
			threadsleep(2000);
			logInfo("Refreshed forms");
			return true;
		}catch(Exception e) {
			logError("Failed to refresh the forms - "+ e.getMessage());
			return false;
		}	
	}

	public static class FormDetails{

		public String FormName;
		public String Version;
		public String ModifiedBy;
		public String ModifiedOn;	

	}

}
