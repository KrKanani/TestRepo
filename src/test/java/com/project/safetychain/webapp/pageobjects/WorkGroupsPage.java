package com.project.safetychain.webapp.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.project.utilities.ControlActions;

public class WorkGroupsPage extends CommonPage {

	WebDriver driver;
	WebDriverWait wait;
	ControlActions controlActions;

	public WorkGroupsPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 40);
		controlActions = new ControlActions(driver);
	}	

	/**
	 * Page Objects
	 */

	@FindBy(className = WorkGroupsPageLocators.SAVE_BTN)
	public WebElement SaveBtn;
	
	@FindBy(xpath = WorkGroupsPageLocators.ADD_WORKGROUP_LNK)
	public WebElement AddWorkgroupLnk;

	@FindBy(xpath = WorkGroupsPageLocators.ADD_WORKGROUP_TXT)
	public WebElement AddWorkgroupTxt;

	@FindBy(xpath = WorkGroupsPageLocators.CANCEL_BTN)
	public WebElement CancelBtn;

	@FindBy(xpath = WorkGroupsPageLocators.VIEW_WORKGROUP_LST)
	public List<WebElement> ViewWorkgroupLst;

	@FindBy(xpath = WorkGroupsPageLocators.SEARCH_USER_TXT)
	public WebElement SearchUserTxt;

	@FindBy(xpath = WorkGroupsPageLocators.SEARCH_USER_BTN)
	public WebElement SearchUserBtn;
	
	@FindBy(xpath = WorkGroupsPageLocators.HOMEPAGE_LINK)
	public WebElement HomePageLink;

	/**
	 * Functions
	 */

	/**
	 * This method is used to set Work Group name
	 * @author hingorani_a
	 * @param wgName The Work Group name
	 * @return boolean This returns true if Work Group name is set successfully.
	 */
	public boolean setWorkGroupName(String wgName) {
		try {
			controlActions.WaitforelementToBeClickable(AddWorkgroupLnk);
			AddWorkgroupLnk.click();
			Sync();
			AddWorkgroupTxt.clear();
			AddWorkgroupTxt.sendKeys(wgName);
//			Sync();
			logInfo("Work Group named entered: " + wgName);
			return true;
		}
		catch(Exception e) {
			logError("Failed to enter WorkGroup name - " + wgName + " - "
					+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to set user to Work Group
	 * @author hingorani_a
	 * @param username Username of user to be added to Work Group
	 * @return boolean This returns true if user is set successfully
	 */
	public boolean searchAndSelectUser(String username) {
		WebElement usernameChk = null;
		try {
			controlActions.WaitforelementToBeClickable(SearchUserTxt);
			controlActions.clickOnElement(SearchUserTxt);	
			controlActions.sendKeys(SearchUserTxt,username);
			logInfo("Add user's name to search textbox: " + username);

			controlActions.WaitforelementToBeClickable(SearchUserBtn);
			controlActions.clickOnElement(SearchUserBtn);	
			Sync();
			logInfo("Clicked on search button");

			usernameChk = controlActions.perform_GetElementByXPath(WorkGroupsPageLocators.ADD_USR_CHK, "USERNAME", username);
			controlActions.clickOnElement(usernameChk);		
			logInfo("Selected user: " + username);				
			return true;
		}
		catch(Exception e) {
			logError("Failed to select user: " + username + " - "
					+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to save Work Group by clicking on Save button
	 * @author hingorani_a
	 * @param none 
	 * @return boolean This returns true if save button is clicked successfully
	 */
	public boolean clickSaveBtn() {
		try {		
			controlActions.WaitforelementToBeClickable(SaveBtn);
			controlActions.clickOnElement(SaveBtn);
			Sync();
			logInfo("Clicked on Save button");
			return true;
		}
		catch(Exception e) {
			logError("Failed to click on Save button - "
					+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to create Work Group and assign it to an user
	 * @author hingorani_a
	 * @param wgName The Work Group name
	 * @param username Username of user to be added to Work Group
	 * @return boolean This returns true if Work Group is created successfully.
	 */
	public boolean createWorkGroup(String wgName, String username) {
		try {
			if(clickWorkGroupsMenu().error) {
				return false;
			}
			if(!setWorkGroupName(wgName)) {
				return false;
			}
			if(!clickSaveBtn()) {
				return false;
			}
			if(!searchAndSelectUser(username)) {
				return false;
			}
			if(!clickSaveBtn()) {
				return false;
			}
			Sync();
			clickHomepageLinkInWG();
			logInfo("Work Group - " + wgName + " created");
			return true;
		}
		catch(Exception e) {
			logError("Failed to create Work Group - " + wgName + " - "
					+ e.getMessage());
			if(controlActions.isElementDisplay(CancelBtn)) {
				controlActions.clickOnElement(CancelBtn);
				Sync();
			}
			return false;
		}	
	}
	
	/**
	 * This method is used to create Work Group.
	 * @author hingorani_a
	 * @param wgName The Work Group name
	 * @return boolean This returns true if Work Group is created successfully.
	 */
	public boolean createWorkGroup(String wgName) {
		try {
			if(clickWorkGroupsMenu().error) {
				return false;
			}
			if(!setWorkGroupName(wgName)) {
				return false;
			}
			if(!clickSaveBtn()) {
				return false;
			}
			Sync();
			clickHomepageLinkInWG();
			logInfo("Work Group - " + wgName + " created");
			return true;
		}
		catch(Exception e) {
			logError("Failed to create Work Group - " + wgName + " - "
					+ e.getMessage());
			if(controlActions.isElementDisplay(CancelBtn)) {
				controlActions.clickOnElement(CancelBtn);
				Sync();
			}
			return false;
		}	
	}
	
	/**
	 * This method is used to click Homepage while in Workgroup module
	 * @author ahmed_tw
	 * @return True after clicking Homepage link, else false
	 */
	public boolean clickHomepageLinkInWG() {
		try {		
			controlActions.WaitforelementToBeClickable(HomePageLink);
			controlActions.clickOnElement(HomePageLink);
			Sync();
			logInfo("Clicked on Homepage Link in WG menu");
			return true;
		}
		catch(Exception e) {
			logError("Failed to click on Homepage Link - "
					+ e.getMessage());
			return false;
		}	
	}
	
}
