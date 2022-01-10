package com.project.safetychain.pcapp.pageobjects;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.project.utilities.ControlActions_PCApp;
import com.project.utilities.DateTime;

import io.appium.java_client.windows.WindowsDriver;

public class SavedScreen extends CommonScreen{
	WindowsDriver<WebElement> PCAppDriver;
	WebDriverWait wait;
	ControlActions_PCApp desktopControlActions; 
	DateTime datetime;


	public SavedScreen(WindowsDriver<WebElement> driver) {
		super(driver);
		this.PCAppDriver = driver;
		PageFactory.initElements(this.PCAppDriver, this);
		wait = new WebDriverWait(driver, 30);
		desktopControlActions = new ControlActions_PCApp(this.PCAppDriver);
		datetime = new DateTime();
	}

	@FindBy(xpath = SavedScreenLocators.SAVED_FORM_SEARCH_TXT) 
	public WebElement SeachFormTxt;

	@FindBy(xpath = SavedScreenLocators.SAVED_SEARCH) 
	public WebElement SeachFormInp;

	@FindBy(xpath = SavedScreenLocators.NEXT_BTN) 
	public WebElement NextBtn;

	@FindBy(xpath = SavedScreenLocators.DELETE_BTN) 
	public WebElement DeleteBtn;

	@FindBy(xpath = SavedScreenLocators.DELETE_YES_BTN) 
	public WebElement DeleteYesBtn;

	@FindBy(xpath = SavedScreenLocators.SAVE_COUNT) 
	public WebElement SaveCount;

	@FindBy(xpath = SavedScreenLocators.DUSTBIN_BTN) 
	public WebElement DustbinBtn;

	@FindBy(xpath = SavedScreenLocators.NO_FORMS_LBL) 
	public WebElement NoFormLbl;

	@FindBy(xpath = SavedScreenLocators.SAVED_FORM_COUNT) 
	public WebElement SavedFormCount;

	@FindBy(xpath = SavedScreenLocators.SAVE_BTN) 
	public WebElement SaveBtn;

	@FindBy(xpath = SavedScreenLocators.SAVED_FORM_REFRESH_BTN) 
	public WebElement FormsRefreshBtn;

	@FindBy(xpath = SavedScreenLocators.REFRESH_YES_BTN) 
	public WebElement RefreshYesBtn;

	@FindBy(xpath = SavedScreenLocators.SAVED_FORM_NAME_LBL) 
	public WebElement SavedFormName;

	@FindBy(xpath = SavedScreenLocators.SAVED_ON_DATA) 
	public WebElement SavedFormSavedOn;

	@FindBy(xpath = SavedScreenLocators.LOCATION_DATA) 
	public WebElement SavedFormLocation;

	@FindBy(xpath = SavedScreenLocators.RESOURCE_DATA) 
	public WebElement SavedFormResource;

	@FindBy(xpath = SavedScreenLocators.PASSWORD_RESET_INP) 
	public WebElement PasswordResetInput;

	@FindBy(xpath = SavedScreenLocators.LOGIN_BTN) 
	public WebElement LoginBtn;

	/**
	 * This method is used to verify availability of saved form
	 * @author pashine_a
	 * @param formName
	 * @return boolean
	 */
	public boolean verifySavedFormAvailability(String formName) {
		String savedForm;
		try {
			if(desktopControlActions.isElementAvailable(SavedScreenLocators.NO_FORMS_LBL)) {
				logInfo("No saved forms found");
				return false;
			}
			desktopControlActions.sendKeys(SeachFormInp, formName);
			threadsleep(2000);
			savedForm = desktopControlActions.getDynamicXPath(SavedScreenLocators.SAVED_FORM, "FORM_NAME", formName);
			if(!desktopControlActions.isElementDisplayed(savedForm)) {
				logInfo("Searched form is not shown in Saved");
				return false;
			}
			logInfo("Found form - "+formName);
			desktopControlActions.clear(SeachFormInp);
			return true;
		}catch(Exception e) {
			logError("Failed to get form - '"+formName+"' - "+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to select & open saved form
	 * @author pashine_a
	 * @param formName
	 * @return boolean
	 */
	public boolean selectOpenForm(String formName) {
		WebElement Selectform = null;
		try {
			desktopControlActions.sendKeys(SeachFormTxt, formName);
			threadsleep(2000);
			Selectform = desktopControlActions.getDynamicElement(SavedScreenLocators.SELECT_FORM, "FORM_NAME", formName);
			desktopControlActions.click(Selectform);
			desktopControlActions.waitForElementToBeVisisble(NextBtn);
			threadsleep(2000);
			logInfo("Opened the form - "+formName);
			return true;
		}catch(Exception e) {
			logError("Failed to open form - '"+formName+"' - "+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to delete the saved form
	 * @author pashine_a
	 * @param formName
	 * @return boolean
	 */
	public boolean deleteForm(String formName) {
		try {
			desktopControlActions.click(DeleteBtn);
			desktopControlActions.click(DeleteYesBtn);
			threadsleep(2000);
			if(verifySavedFormAvailability(formName)) {
				logError("Deleted form is shown");
				return false;
			}
			logInfo("Deleted the form");
			return true;
		}catch(Exception e) {
			logError("Failed to delete form - '"+formName+"' - "+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to verify the task count of 'Saved'
	 * @author choubey_a
	 * @param count
	 * @return true if count is verified
	 */
	public boolean verifyCount(int count) {
		String savedCount = new Integer(count).toString();
		System.out.println("Count - "+SaveCount.getAttribute("Name"));
		try {
			if(!SaveCount.getAttribute("Name").equals(savedCount)){
				logError("Saved count is not correct");
				return false;
			}
			logInfo("Verified saved count - "+savedCount);
			return true;
		}catch (Exception e) {
			logError("Failed to verify saved count - "+e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to delete the saved form
	 * @author choubey_a
	 * @param formName
	 * @return true if form is deleted
	 */
	public boolean deleteUnopenendForm(String formName) {
		try {
			desktopControlActions.sendKeys(SeachFormTxt, formName);
			threadsleep(2000);
			desktopControlActions.click(DustbinBtn);
			desktopControlActions.click(DeleteYesBtn);
			threadsleep(2000);
			if(verifySavedFormAvailability(formName)) {
				logError("Deleted form is shown");
				return false;
			}
			logInfo("Deleted the form");
			desktopControlActions.clear(SeachFormInp);
			return true;
		}catch(Exception e) {
			logError("Failed to delete form - '"+formName+"' - "+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to verify no saved task
	 * @author choubey_a
	 * @param none
	 * @return boolean
	 */
	public boolean verifyNoSaveForm() {
		try {
			if(!desktopControlActions.isElementDisplayed(NoFormLbl)){
				logError("Saved from exists");
				return false;
			}
			logInfo("Verifiied no saved task");
			return true;
		}catch(Exception e) {
			logError("Failed to verify saved form unavailability - "+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to verify the saved form count of 'Saved'
	 * @author pashine_a
	 * @param count
	 * @return boolean
	 */
	public boolean verifySavedCount(byte count) {
		String saveFormCount = new Byte(count).toString();
		try {
			threadsleep(4000);
			if(!SavedFormCount.getAttribute("Name").equals(saveFormCount)){
				logError("Submission count is not correct");
				return false;
			}
			logInfo("Verified saved form count - "+saveFormCount);
			return true;
		}catch (Exception e) {
			logError("Failed to verify saved form count - "+e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to save the form
	 * @author pashine_a
	 * @param null
	 * @return boolean
	 */
	public boolean saveForm() {
		try {
			threadsleep(2000);
			desktopControlActions.click(SaveBtn);
			threadsleep(4000);
			if(!desktopControlActions.isElementAvailable(CommonScreenLocators.FORM_MNU)){
				logError("Failed to navigate to main screen");
				return false;
			}
			logInfo("Form is saved & navigated to main screen");
			return true;
		}catch(Exception e) {
			logError("Failed to save form & navigate to main screen "+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to refresh the saved form
	 * @author pashine_a
	 * @param null
	 * @return boolean 
	 */
	public boolean refreshSavedForm() {
		try {
			desktopControlActions.click(FormsRefreshBtn);
			desktopControlActions.click(RefreshYesBtn);	
			threadsleep(2000);
			desktopControlActions.waitForElementToBeVisisble(SeachFormTxt);
			logInfo("Refreshed Saved form");
			return true;
		}catch(Exception e) {
			logError("Failed to refresh saved form - "+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to perform double click on 'Saved' Tab
	 * @author pashine_a
	 * @param null
	 * @return boolean. If nothing is impacting on double click then returns TRUE
	 */
	public boolean doubleClickSavedTab() {
		try {
			desktopControlActions.doubleClick(SavedMnu);
			if(!desktopControlActions.isElementDisplayed(NoFormLbl)) {
				logError("Failed to double click on Saved");
				return false;
			}
			logInfo("Multiple clicks performed on 'Saved' Tab");
			return true;
		}catch(Exception e) {
			logError("Failed to double click on Saved - "+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to delete the saved form
	 * @author pashine_a
	 * @param formName
	 * @return boolean. If form is deleted on double click then returns TRUE
	 */
	public boolean doubleClickDelete(String formName) {
		try {
			desktopControlActions.doubleClick(DeleteBtn);
			desktopControlActions.click(DeleteYesBtn);
			threadsleep(2000);
			if(verifySavedFormAvailability(formName)) {
				logError("Deleted form is shown");
				return false;
			}
			logInfo("Deleted the form");
			return true;
		}catch(Exception e) {
			logError("Failed to delete form - '"+formName+"' - "+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to verify data in Saved Form
	 * @author pashine_a
	 * @param formName
	 * @return boolean. If data is correct then returns TRUE
	 */
	public boolean verifySavedFormData(HashMap <String, String> fieldDetails) {
		String fieldName = null, fieldValue = null;
		String fieldNamePath = null;
		WebElement fieldElement = null;
		try {
			HashMap<String, String> savedFormdetails = fieldDetails;
			for (Map.Entry<String, String> entry : savedFormdetails.entrySet()) {
				fieldName = entry.getKey();
				fieldValue = entry.getValue();

				fieldNamePath = desktopControlActions.getDynamicXPath(SavedScreenLocators.FIELD_DATA_LBL, "FIELD_NAME", fieldName);

				fieldElement = desktopControlActions.getDynamicElement(fieldNamePath, "FIELD_VALUE", fieldValue);

				if(desktopControlActions.isElementDisplayed(fieldElement)) {
					desktopControlActions.clickElement(NextBtn);
					logInfo("Verified field details for field - "+fieldName+" having field value - "+fieldValue);
				}else {
					logError("Failed to verify field details for field - "+fieldName+" having field value - "+fieldValue);
					return false;
				}
			}
			logInfo("Verified the field details");
			return true;
		}catch(Exception e) {
			logError("Failed to verify field details "+ e.getMessage());
			return false;
		}	
	}


	/**
	 * This method is used to verify data in Saved Form
	 * @author pashine_a
	 * @param savedFormDetails & savedOnDetails
	 * @return boolean. If saved form details are correct then returns TRUE
	 */
	public boolean verifySavedFormDetails(SavedFormDetails savedFormDetails, SavedOnDetails savedOnDetails) {
		String formSavedOnDetails = null,savedDay = null, savedHour = null;
		WebElement DateElement = null;
		try {
			desktopControlActions.sendKeys(SeachFormTxt, savedFormDetails.formName);
			threadsleep(2000);

			DateElement = desktopControlActions.getDynamicElement(SavedScreenLocators.DATE_LBL,"DATE" , savedFormDetails.date);
			formSavedOnDetails = SavedFormSavedOn.getAttribute("Name");

			String dateTimeSeprator[] = formSavedOnDetails.split(" ");
			String timeSeprator[] = dateTimeSeprator[1].split(":");

			savedDay = dateTimeSeprator[0];
			savedHour = timeSeprator[0];

			if(!desktopControlActions.isElementDisplayed(DateElement)) {
				logError("Issue in Header Date");
				return false;
			}

			if(!savedFormDetails.formName.equals(SavedFormName.getAttribute("Name"))) {
				logError("Issue in Saved Form Name");
				return false;
			}

			if(!savedFormDetails.location.equals(SavedFormLocation.getAttribute("Name"))) {
				logError("Issue in Saved Form's Location Name");
				return false;
			}

			if(!savedFormDetails.resource.equals(SavedFormResource.getAttribute("Name"))) {
				logError("Issue in Saved Form's Resource Name");
				return false;
			}

			if(!savedFormDetails.savedOnDetails.currentDay.equals(savedDay)) {
				logError("Issue in Saved Form's Saved Date");
				return false;
			}

			if(!savedFormDetails.savedOnDetails.currentHour.equals(savedHour)) {
				logError("Issue in Saved Form's Saved Time");
				return false;
			}

			if(!datetime.verifyDateTimeFormat(savedFormDetails.savedOnDetails.format, formSavedOnDetails)) {
				logError("Issue in Saved Form's Saved On Date-Time format");
				return false;
			}

			logInfo("Verified all saved form details for form - "+savedFormDetails.formName+" saved on - "+formSavedOnDetails);
			return true;
		}catch(Exception e) {
			logError("Failed to verify saved form details for form "+savedFormDetails.formName+" - "+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to verify login of already logged in user(offline mode) with updated password
	 * @author pashine_a
	 * @param null
	 * @return returns true if login with updated password is performed successfully
	 */
	public boolean loginOnPasswordChange(String password) {
		try {			
			threadsleep(4000);
			if(!desktopControlActions.isElementDisplayed(SavedScreenLocators.PASSWORD_INPUT_LBL)) {
				logError("Password input label is not shown");
				return false;
			}	
			threadsleep(2000);
			desktopControlActions.sendKeys(PasswordResetInput, password);
			desktopControlActions.clickElement(LoginBtn);
			if(!waitToLoadData()) {
				logError("Failed to load data");
			}
			logInfo("Logged in with updated password");	
			return true;
		}catch(Exception e) {
			logError("Could not login with updated password" +e.getMessage());
			return false;
		}
	}

	public static class SavedFormDetails{
		public String formName;
		public SavedOnDetails savedOnDetails;
		public String location;
		public String resource;
		public String date;
	}

	public static class SavedOnDetails{
		public String savedOnData;
		public String format;
		public String currentDay;
		public String currentHour;
	}

}
