package com.project.safetychain.webapp.pageobjects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.project.testbase.TestBase;
import com.project.utilities.ControlActions;
import com.project.utilities.ControlActions.WINDOW_TAB;

public class CommonPage extends TestBase{
	WebDriver driver;
	WebDriverWait wait;
	ControlActions controlActions;

	public static String fileName = "ABC1.csv";
	public static String filePath = System.getProperty("user.dir") + "\\test-data-files\\UploadDocuments\\"+fileName; 

	public CommonPage() {
	}

	List<String> Angular6Pages = new ArrayList<>();

	public CommonPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 40);
		controlActions = new ControlActions(driver);
		Angular6Pages = Arrays.asList("Users", "Forms Manager");

	}	


	@FindBy(xpath = CommonPageLocators.HAMBURGER_MNU)
	public WebElement HamburgerMnu;

	@FindBy(id = CommonPageLocators.SC_LOGO)
	public WebElement SCLogo;

	@FindBy(xpath =CommonPageLocators.USER_ICON)
	public WebElement UserIcon;

	@FindBy(xpath =CommonPageLocators.LOGOUT)
	public WebElement Logout;

	@FindBy(xpath = CommonPageLocators.DASHBOARD_MNU)
	public WebElement DashboardMnu;

	@FindBy(xpath = CommonPageLocators.LINK_MNU)
	public WebElement LinkMnu;
	
	@FindBy(xpath = CommonPageLocators.BROWSER_MNU)
	public WebElement BrowserMnu;

	@FindBy(xpath = CommonPageLocators.RECORD_SIGNOFF_MNU)
	public WebElement RecordSignOffMnu;

	@FindBy(xpath = CommonPageLocators.DOCUMENT_MANAGEMENT)
	public WebElement DocumentManagement;

	@FindBy(xpath = CommonPageLocators.TASKSCHEDULER_MNU)
	public WebElement TaskSchedulerMnu;

	@FindBy(xpath = CommonPageLocators.INBOX)
	public WebElement inbox;

	@FindBy(xpath = CommonPageLocators.ADMINTOOLS_MNU)
	public WebElement AdminToolsMnu;

	@FindBy(xpath = CommonPageLocators.ADMINTOOLS_CONTAINER)
	public WebElement AdminToolsContainer;

	@FindBy(xpath = CommonPageLocators.PROGRAMS_MNU)
	public WebElement ProgramsMnu;



	@FindBy(xpath = CommonPageLocators.USERS_MNU)
	public WebElement UsersMnu;

	@FindBy(xpath = CommonPageLocators.WORKGROUP_MNU)
	public WebElement WorkGroupMnu;	

	@FindBy(xpath = CommonPageLocators.RESOURCE_DESIGNER_MNU)
	public WebElement ResourceDesignerMnu;	

	@FindBy(xpath = CommonPageLocators.RESOURCES_MNU)
	public WebElement ResourcesMnu;	

	@FindBy(xpath = CommonPageLocators.REQUIREMENTS_MNU)
	public WebElement RequirementsMnu;	

	@FindBy(xpath = CommonPageLocators.LOCATIONS_MNU)
	public WebElement LocationMnu;	

	@FindBy(xpath = CommonPageLocators.ROLES_MNU)
	public WebElement RolesMnu;

	@FindBy(xpath = CommonPageLocators.FORM_DESIGNER_MNU)
	public WebElement FormDesignerMnu;

	@FindBy(xpath = CommonPageLocators.USER_DETAILS_LBL)
	public WebElement UserDetailsLbl;

	@FindBy(xpath =CommonPageLocators.VALIDATIONS_MNU)
	public WebElement ValidationsMnu;

	@FindBy(xpath =CommonPageLocators.VERIFICATIONS_MNU)
	public WebElement VerificationsMnu;

	@FindBy(xpath =CommonPageLocators.FORMS_MANAGER_MNU)
	public WebElement FormsManagerMnu;

	@FindBy(xpath =CommonPageLocators.CHART_BUILDER_MNU)
	public WebElement ChartBuilderMnu;

	@FindBy(xpath =CommonPageLocators.DEVICES_MNU)
	public WebElement DevicesMnu;

	@FindBy(xpath = CommonPageLocators.PROGRAM_DESIGNER_MNU)
	public WebElement ProgramDesignerMnu;

	@FindBy(xpath = CommonPageLocators.DATA_IMPORT_EXPORT_MNU)
	public WebElement DataImportExportMnu;

	//Submit WebElements

	@FindBy(xpath = CommonPageLocators.FORM_SELECTION)
	public WebElement SelectForm;

	@FindBy(xpath = CommonPageLocators.FORM_LEVEL_ATTACHMENT)
	public WebElement FormLevelAttachment;

	@FindBy(xpath = CommonPageLocators.FORM_LEVEL_ATTACHMENT_CLOSE_BTN)
	public WebElement FormLevelAttachmentCloseBtn;

	@FindBy(xpath = CommonPageLocators.FORM_LEVEL_ATTACHMENT_SELECT_BTN)
	public WebElement FormLevelAttachmentSelectBtn;

	@FindBy(xpath = CommonPageLocators.FORM_LEVEL_ATTACHMENT_INP)
	public WebElement FormLevelAttachmentInp;

	@FindBy(xpath = CommonPageLocators.FORM_LEVEL_ATTACHMENT_STATUS)
	public WebElement FormLevelAttachmentStatus;

	@FindBy(xpath = CommonPageLocators.FORM_LEVEL_ATTACHMENT_RETRY)
	public WebElement FormLevelAttachmentRetry;


	@FindBy(xpath = CommonPageLocators.ALL_FIELDS)
	List<WebElement> AllFieldsLst;

	@FindBy(xpath = CommonPageLocators.SUBMIT_FORM_BTN)
	public WebElement SubmitFormBtn;

	@FindBy(xpath = CommonPageLocators.SUBMIT_REPEAT_BUTTON)
	public WebElement SubmitRepeatFormBtn;

	@FindBy(xpath = CommonPageLocators.POPUP_OK_BTN)
	public WebElement PopUpOkBtn;

	@FindBy(xpath = CommonPageLocators.SUBMISSION_POP_UP_OK_BTN)
	public WebElement SubmissionOKBtn;

	@FindBy(xpath = CommonPageLocators.SAVE_BTN)
	public WebElement SaveBtn;

	@FindBy(xpath = CommonPageLocators.NON_COMPLIANCE)
	public WebElement NonCompliance;

	@FindBy(xpath = CommonPageLocators.COMPLIANCE)
	public WebElement Compliance;

	@FindBy(xpath = FSQABrowserFormsPageLocators.FORM_CANCEL_BTN)
	public WebElement FormCancelBtn;
	
	@FindBy(xpath = CommonPageLocators.INBOX_FILTERS_TEXT)
	public WebElement InboxFiltersText;

	@FindBy(xpath = CommonPageLocators.HAMBURGER_MNU_LST)
	public List<WebElement> HamburgerMnuLst;

	@FindBy(xpath =CommonPageLocators.CHNG_PSSWRD_CALLOUT_OPTN)
	public WebElement ChangePasswordCalloutOption;
	
	@FindBy(xpath =CommonPageLocators.CHNG_PSSWRD_POPUP)
	public WebElement ChangePasswordPopUp;
	
	@FindBy(xpath =CommonPageLocators.NEW_PASSWORD_INPUT)
	public WebElement NewPasswordInput;
	
	@FindBy(xpath =CommonPageLocators.CONFIRM_PASSWORD_INPUT)
	public WebElement ConfirmPasswordInput;
	
	@FindBy(xpath =CommonPageLocators.CHNG_PSSWRD_YES_BUTTON)
	public WebElement ChangePasswordYesButton;

	/**
	 * This method is used to click on Hamburger Menu.
	 * @author hingorani_a
	 * @param none
	 * @return boolean This returns true if the Hamburger Menu is clicked.
	 */

	public boolean clickHamburgerMenu() {
		try {			
			Sync();	
			controlActions.WaitforelementToBeClickable(HamburgerMnu);
			controlActions.clickElement(HamburgerMnu);
			logInfo("Clicked on Hamburger Menu");
			threadsleep(5000);
			return true;
		}
		catch(Exception e) {
			logError("Failed to click on Hamburger Menu - "
					+ e.getMessage());
			return false;
		}		
	}
	
	/**
	 * This method is used to click on Hamburger Menu > Link Menu.
	 * @author hingorani_a
	 * @return LinkPage This returns object with error variable as false
	 * if Link menu is clicked.
	 */
	public LinkPage clickLinkMenu() {
		LinkPage lp = new LinkPage(driver);
		
		try {
			if(!clickHamburgerMenu()) {
				logError("Failed to click on Hamburgur Menu");
				lp.error = true;
				return lp;
			}

			controlActions.perform_scrollToElement_ByElement(LinkMnu);
			controlActions.clickOnElement(LinkMnu);
			Sync();
			
			if(!controlActions.switchToTab(WINDOW_TAB.SECOND)) {
				logError("Failed to switch tab to Link window");
				lp.error = true;
				return lp;
			}

			LinkSync();
			logInfo("Clicked on Link Menu");
			return new LinkPage(driver);			
		}
		catch(Exception e) {
			logError("Failed to click on Link Menu - "
					+ e.getMessage());
			lp.error = true;
			return lp;
		}	
	}

	/**
	 * This method is used to click on Hamburger Menu > Inbox Menu
	 * @author choubey_a
	 * @param none
	 * @return InboxPage This returns object with error variable as false
	 * if Browser menu is clicked.
	 */

	public InboxPage clickInboxMenu() {
		InboxPage inp = new InboxPage(driver);
		try {
			if(!clickHamburgerMenu()) {
				logError("Failed to click on Hamberger Menu");
				inp.error = true;
				return inp;
			}
			controlActions.perform_scrollToElement_ByElement(inbox);
			//pcontrolActions.WaitforelementToBeClickable(inbox);
			controlActions.clickOnElement(inbox);
			Sync();
			logInfo("Clicked on Inbox");
			return inp;
		}
		catch(Exception e) {
			logError("Failed to click on Inbox - "
					+ e.getMessage());
			inp.error= true;
			return inp;
		}	
	}


	/**
	 * This method is used to click on Hamburger Menu > Browser Menu.
	 * @author hingorani_a
	 * @param none
	 * @return FSQABrowserPage This returns object with error variable as false
	 * if Browser menu is clicked.
	 */

	public FSQABrowserPage clickFSQABrowserMenu() {
		try {
			if(!clickHamburgerMenu()) {
				logError("Failed to click on Hamburgur Menu");
				FSQABrowserPage fbp = new FSQABrowserPage(driver);
				fbp.error = true;
				return fbp;
			}

			controlActions.perform_scrollToElement_ByElement(BrowserMnu);
			controlActions.clickOnElement(BrowserMnu);
			Sync();
			logInfo("Clicked on FSQA Browser Menu");
			return new FSQABrowserPage(driver);			
		}
		catch(Exception e) {
			logError("Failed to click on FSQA Broswer Menu - "
					+ e.getMessage());
			FSQABrowserPage fbp = new FSQABrowserPage(driver);
			fbp.error = true;
			return fbp;
		}	
	}

	/**
	 * This method is used to click on Hamburger Menu > Record Signoff Menu.
	 * @author hingorani_a
	 * @param none
	 * @return RecordSignoffPage This returns object with error variable as false
	 * if Record Signoff menu is clicked.
	 */

	public RecordSignoffPage clickRecordSignoffMenu() {
		try {
			if(!clickHamburgerMenu()) {
				logError("Failed to click on Hamburgur Menu");
				RecordSignoffPage rsp = new RecordSignoffPage(driver);
				rsp.error = true;
				return rsp;
			}

			controlActions.perform_scrollToElement_ByElement(RecordSignOffMnu);
			RecordSignOffMnu.click();
			Sync();
			logInfo("Clicked on Record Signoff Menu");
			return new RecordSignoffPage(driver);			
		}
		catch(Exception e) {
			logError("Failed to click on Record Signoff Menu - "
					+ e.getMessage());
			RecordSignoffPage rsp = new RecordSignoffPage(driver);
			rsp.error = true;
			return rsp;
		}	
	}

	/**
	 * This method is used to click on Hamburger Menu > Admin Tools > Programs 
	 * @author hingorani_a
	 * @param none
	 * @return ProgramViewerPage This returns object with error variable as false
	 * if Programs menu is clicked.
	 */

	public ProgramViewerPage clickProgramsMenu() {
		ProgramViewerPage pdp = new ProgramViewerPage(driver);
		try {
			if(!clickHamburgerMenu()) {
				logError("Failed to click on Hamburgur Menu");
				pdp.error = true;
				return pdp;
			}

			controlActions.WaitforelementToBeClickable(ProgramsMnu);
			controlActions.perform_scrollToElement_ByElement(ProgramsMnu);
			controlActions.clickOnElement(ProgramsMnu);
			Sync();
			logInfo("Clicked on Programs Menu");
			return pdp;
		} 
		catch (Exception e) {
			logError("Failed to click on Programs Menu - " + e.getMessage());
			pdp.error = true;
			return pdp;
		}
	}


	/**
	 * This method is used to click on Hamburger Menu > Documents Menu
	 * @author choubey_a
	 * @param none
	 * @return DocumentManagementPage This returns object with error variable as false
	 * if Documents menu is clicked.
	 */

	public DocumentManagementPage clickdocumentsmenu() {
		DocumentManagementPage dmp = new DocumentManagementPage(driver);
		try {
			if(!clickHamburgerMenu()) {
				logError("Failed to click on Hamberger Menu");
				dmp.error = true;
				return dmp;
			}
			controlActions.WaitforelementToBeClickable(DocumentManagement);
			controlActions.clickOnElement(DocumentManagement);
			Sync();
			logInfo("Clicked on Documents");
			return dmp;
		}
		catch(Exception e) {
			logError("Failed to click on Documents - "
					+ e.getMessage());
			dmp.error= true;
			return dmp;
		}	
	}

	/**
	 * This method is used to click on Hamburger Menu > Admin Tools > Task SchedulerPage Menu.
	 * @author dahale_p
	 * @return TaskSchedulerPage This returns object with error variable as false
	 * if Task Scheduler Menu is clicked.
	 */

	public TaskSchedulerPage clickTaskSchedulerMenu() {
		TaskSchedulerPage tsp= new TaskSchedulerPage (driver);

		try {

			if(!clickHamburgerMenu()) {
				logError("Failed to click on Hamberger Menu");
				tsp.error = true;
				return tsp;
			}
			controlActions.perform_scrollToElement_ByElement(TaskSchedulerMnu);
			controlActions.clickElement(TaskSchedulerMnu);
			Sync();
			logInfo("Clicked on Task Scheduler Menu");
			return tsp;			
		}catch(Exception e) {
			logError("Failed to click Task Scheduler Menu - "+ e.getMessage());
			tsp.error = true;
			return tsp;
		}
	}	




	/**
	 * This method is used to click on Hamburger Menu > Admin Tools Option.
	 * @author hingorani_a
	 * @param none
	 * @return boolean This returns true if Admin Tools is clicked.
	 */

	public boolean clickAdminToolsMenu() {
		try {			
			if(!clickHamburgerMenu()) {
				logError("Failed to click on Hamburger Menu");
				return false;
			}

			if(AdminToolsContainer.getAttribute("class").contains("ng-hide")) {
				controlActions.WaitforelementToBeClickable(AdminToolsMnu);
				controlActions.clickOnElement(AdminToolsMnu);
				logInfo("Clicked on Admin Tools Menu");
			}else if(AdminToolsContainer.getAttribute("hidden") != null && AdminToolsContainer.getAttribute("hidden").equals("true") ) {
				controlActions.WaitforelementToBeClickable(AdminToolsMnu);
				controlActions.clickOnElement(AdminToolsMnu);
				logInfo("Clicked on Admin Tools Menu");
			}
			else {
				logInfo("Admin Tools Menu is already open");
			}
			threadsleep(5000);
			return true;			
		}
		catch(Exception e) {
			logError("Failed to click on Admin Tools Menu - "
					+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to click on Hamburger Menu > Admin Tools > Users Menu.
	 * @author hingorani_a
	 * @param none
	 * @return UserManagerPage This returns object with error variable as false
	 * if Users menu is clicked.
	 */

	public UserManagerPage clickUsersMenu() {
		UserManagerPage ump = new UserManagerPage(driver);
		try {

			if(clickFSQABrowserMenu().error){
				ump.error =true;
				return ump;
			}

			if(!clickAdminToolsMenu()) {
				logError("Failed to click on Admin Tools menu");
				ump.error = true;
				return ump;
			}

			controlActions.perform_scrollToElement_ByElement(UsersMnu);
			UsersMnu.click();
			Sync();
			logInfo("Clicked on Users Menu");
			return new UserManagerPage(driver);			
		}
		catch(Exception e) {
			logError("Failed to click on Admin Tools > Users Menu - "
					+ e.getMessage());
			ump.error = true;
			return ump;
		}	
	}


	/**
	 * This method is used to click on Hamburger Menu > Admin Tools > Roles Menu.
	 * @author hingorani_a
	 * @param none
	 * @return RolesManagerPage This returns object with error variable as false
	 * if Roles menu is clicked.
	 */

	public RolesManagerPage clickRolesMenu() {
		try {
			if(!clickAdminToolsMenu()) {
				logError("Failed to click on Admin Tools menu");
				RolesManagerPage rmp = new RolesManagerPage(driver);
				rmp.error = true;
				return rmp;
			}

			controlActions.perform_scrollToElement_ByElement(RolesMnu);
			controlActions.clickOnElement(RolesMnu);
			logInfo("Clicked on Roles Menu");
			Sync();
			return new RolesManagerPage(driver);			
		}
		catch(Exception e) {
			logError("Failed to click on Admin Tools > Roles Menu - "
					+ e.getMessage());
			RolesManagerPage rmp = new RolesManagerPage(driver);
			rmp.error = true;
			return rmp;
		}	
	}

	/**
	 * This method is used to click on Hamburger Menu > Admin Tools > Work Groups Menu.
	 * @author hingorani_a
	 * @param none
	 * @return WorkGroupsPage This returns object with error variable as false
	 * if Work Groups menu is clicked.
	 */

	public WorkGroupsPage clickWorkGroupsMenu() {
		try {
			if(!clickAdminToolsMenu()) {
				logError("Failed to click on Admin Tools Menu");
				WorkGroupsPage wgp = new WorkGroupsPage(driver);
				wgp.error = true;
				return wgp;
			}

			controlActions.perform_scrollToElement_ByElement(WorkGroupMnu);
			controlActions.clickOnElement(WorkGroupMnu);
			logInfo("Clicked on Work Groups Menu");
			threadsleep(5000);
			return new WorkGroupsPage(driver);			
		}
		catch(Exception e) {
			logError("Failed to click on Admin Tools > Work Groups Menu - "
					+ e.getMessage());
			WorkGroupsPage wgp = new WorkGroupsPage(driver);
			wgp.error = true;
			return wgp;
		}	
	}

	/** This method is used to click on Hamburger Menu > Admin Tools > Resource Menu
	 * @author pashine_a
	 * @param none
	 * @return 'ManageResourcePage' object with error status
	 * IF clicked on 'Admin Tools -> Resources Menu' THEN object.error - false ELSE object.error - true
	 */

	public ManageResourcePage clickResourcesMenu() {
		ManageResourcePage mlp = new ManageResourcePage(driver);
		try {
			if(!clickAdminToolsMenu()) {
				mlp.error = true;
				return mlp;
			}
			controlActions.perform_scrollToElement_ByElement(ResourcesMnu);
			controlActions.clickElement(ResourcesMnu);
			Sync();
			logInfo("Clicked on 'Admin Tools -> Resources' Menu");
			return mlp;			
		}catch(Exception e) {
			logError("Failed to click - 'Admin Tools -> Resources' Menu - "+ e.getMessage());
			mlp.error = true;
			return mlp;
		}	
	}


	/** 
	 * This method is used to click on Hamburger Menu > Admin Tools > Requirements Menu.
	 * @author choubey_a 
	 * @param none
	 * @return 'ManageRewuirementPage' object with error status
	 * IF clicked on 'Admin Tools -> Requirements Menu' THEN object.error - false ELSE object.error - true
	 */

	public ManageRequirementPage clickRequirementsMenu() {
		ManageRequirementPage mrp = new ManageRequirementPage(driver);
		try {
			if(!clickAdminToolsMenu()) {
				mrp.error = true;
				return mrp;
			}
			controlActions.perform_scrollToElement_ByElement(RequirementsMnu);
			controlActions.clickElement(RequirementsMnu);
			Sync();
			logInfo("Clicked on 'Admin Tools -> Requirements' Menu");
			return mrp;			
		}catch(Exception e) {
			logError("Failed to click - 'Admin Tools -> Requirements' Menu - "+ e.getMessage());
			mrp.error = true;
			return mrp;
		}	
	}


	/** This method is used to click on to Hamburger Menu > Admin Tools > Locations Menu.
	 * @author pashine_a
	 * @param none
	 * @return 'ManageLocationPage' object with error status
	 * IF clicked on 'Admin Tools -> Locations Menu' THEN object.error - false ELSE object.error - true
	 */

	public ManageLocationPage clickLocationsMenu() {
		ManageLocationPage mlp = new ManageLocationPage(driver);;
		try {
			if(!clickAdminToolsMenu()) {
				mlp.error = true;
				return mlp;
			}
			controlActions.perform_scrollToElement_ByElement(LocationMnu);
			controlActions.clickElement(LocationMnu);
			Sync();
			logInfo("Clicked on 'Admin Tools -> Locations' Menu");
			return mlp;			
		}catch(Exception e) {
			logError("Failed to click - 'Admin Tools -> Locations' Menu - "+ e.getMessage());
			mlp.error = true;
			return mlp;
		}	
	}

	/**
	 * This method is used to click on Hamburger Menu > Admin Tools > Validations Menu.
	 * @author hingorani_a
	 * @param none
	 * @return ValidationsPage This returns object with error variable as false
	 * if Validations menu is clicked.
	 */
	public ValidationsPage clickValidationsMenu() {
		ValidationsPage vp= new ValidationsPage(driver);;
		try {

			if(clickFSQABrowserMenu().error){
				vp.error =true;
				return vp;
			}

			if(!clickAdminToolsMenu()) {
				vp.error = true;
				return vp;
			}

			controlActions.perform_scrollToElement_ByElement(ValidationsMnu);
			controlActions.click(ValidationsMnu);
			Sync();
			logInfo("Clicked on 'Admin Tools -> Validations Menu'");
			return vp;			
		}
		catch(Exception e) {
			logError("Failed to click - 'Admin Tools -> Validations Menu' - "+ e.getMessage());
			vp.error = true;
			return vp;
		}	
	}

	/**
	 * This method is used to click on Hamburger Menu > Admin Tools > Verification Menu.
	 * @author dahale_p
	 * @param none
	 * @return Verification page This returns object with error variable as false
	 * if Browser menu is clicked.
	 */

	public VerificationsPage clickVerificationsMenu() {
		VerificationsPage vp= new VerificationsPage (driver);;
		try {

			if(clickFSQABrowserMenu().error){
				vp.error =true;
				return vp;
			}

			if(!clickAdminToolsMenu()) {
				vp.error = true;
				return vp;
			}
			controlActions.perform_scrollToElement_ByElement(VerificationsMnu);
			controlActions.clickElement(VerificationsMnu);
			Sync();
			logInfo("Clicked on 'Admin Tools -> Verification Menu'");
			return vp;			
		}catch(Exception e) {
			logError("Failed to click - 'Admin Tools -> Verification Menu' - "+ e.getMessage());
			vp.error = true;
			return vp;
		}	
	}

	/**
	 * This method is used to click on Hamburger Menu > Admin Tools > Forms Manager Menu.
	 * @author dahale_p
	 * @param none 
	 * @return Verification page This returns object with error variable as false
	 * if Browser menu is clicked.
	 */

	public FormsManagerPage clickFormsManagerMenu() {
		FormsManagerPage fm= new FormsManagerPage (driver);

		try {

			if(clickFSQABrowserMenu().error){
				fm.error =true;
				return fm;
			}

			if(!clickAdminToolsMenu()) {
				fm.error = true;
				return fm;
			}

			controlActions.perform_scrollToElement_ByElement(FormsManagerMnu);
			controlActions.clickElement(FormsManagerMnu);
			Sync();
			logInfo("Clicked on 'Admin Tools -> Forms Manager' Menu");
			return fm;			
		}catch(Exception e) {
			logError("Failed to click - 'Admin Tools -> Forms Manager' Menu - "+ e.getMessage());
			fm.error = true;
			return fm;
		}
	}	


	/**
	 * This method is used to click on Hamburger Menu > Admin Tools > Chart Builder
	 * @author choubey_a
	 * @param none
	 * @return true if Chart Builder menu is clicked
	 */

	public ChartBuilderPage clickChartBuilderMenu() {
		ChartBuilderPage cbp = new ChartBuilderPage(driver);
		try {

			if(clickFSQABrowserMenu().error){
				cbp.error =true;
				return cbp;
			}

			if(!clickAdminToolsMenu()) {
				cbp.error = true;
				return cbp;
			}
			controlActions.perform_scrollToElement_ByElement(ChartBuilderMnu);
			controlActions.clickOnElement(ChartBuilderMnu);
			logInfo("Clicked on 'Admin Tools -> Chart Builder Menu'");
			Sync();
			return cbp;			
		}catch(Exception e) {
			logError("Failed to click - 'Admin Tools -> Chart Builder Menu' - "+ e.getMessage());
			cbp.error = true;
			return cbp;
		}	
	}

	/** This method is used to click on to Hamburger Menu > Admin Tools > Form Designer Menu
	 * @author pashine_a
	 * @param none
	 * @return 'FormDesignerPage' object with error status
	 * IF clicked on 'Admin Tools -> Form Designer Menu' THEN object.error - false ELSE object.error - true
	 */

	public FormDesignerPage clickFormDesignerMenu() {
		FormDesignerPage fdp = new FormDesignerPage(driver);
		try {
			if(!clickAdminToolsMenu()) {
				fdp.error = true;
				return fdp;
			}
			controlActions.perform_scrollToElement_ByElement(FormDesignerMnu);
			controlActions.clickElement(FormDesignerMnu);
			Sync();
			logInfo("Selected 'Admin Tools -> Form Designer' Menu");
			return fdp;			
		}catch(Exception e) {
			logError("Failed to click - 'Admin Tools -> Form Designer' Menu - "+ e.getMessage());
			fdp.error = true;
			return fdp;
		}	
	}


	/** This method is used to click on Hamburger Menu > Admin Tools > Resource Designer Menu
	 * @author pashine_a
	 * @param none
	 * @return 'ResourceDesignerPage' object with error status
	 * IF clicked on 'Admin Tools -> Resource Designer Menu' THEN object.error - false ELSE object.error - true
	 */

	public ResourceDesignerPage clickResourceDesignerMenu() {
		ResourceDesignerPage rdp = new ResourceDesignerPage(driver);
		try {
			if(!clickAdminToolsMenu()) {
				rdp.error = true;
				return rdp;
			}	
			controlActions.perform_scrollToElement_ByElement(ResourceDesignerMnu);
			controlActions.clickElement(ResourceDesignerMnu);
			Sync();
			logInfo("Clicked on 'Admin Tools -> Resource Designer' Menu");
			return rdp;			
		}
		catch(Exception e) {
			logError("Failed to click - 'Admin Tools -> Resource Designer' Menu - "+ e.getMessage());
			rdp.error = true;
			return rdp;
		}	
	}


	/**
	 * This method is used to click on Hamburger Menu > Admin Tools > Program Designer 
	 * @author hingorani_a
	 * @param none
	 * @return ProgramDesignerPage This returns object with error variable as false
	 * if Program Designer menu is clicked.
	 */

	public ProgramDesignerPage clickProgramDesignerMenu() {
		try {
			if (!clickAdminToolsMenu()) {
				logError("Failed to click on Admin Tools menu");
				ProgramDesignerPage pdp = new ProgramDesignerPage(driver);
				pdp.error = true;
				return pdp;
			}

			controlActions.perform_scrollToElement_ByElement(ProgramDesignerMnu);
			ProgramDesignerMnu.click();
			Sync();
			logInfo("Clicked on Program Designer Sub Menu");
			return new ProgramDesignerPage(driver);
		} 
		catch (Exception e) {
			logError("Failed to click on Admin Tools > Program Designer - " + e.getMessage());
			ProgramDesignerPage pdp = new ProgramDesignerPage(driver);
			pdp.error = true;
			return pdp;
		}
	}

	/**
	 * This method is used to logout
	 * @author choubey_a
	 * @param none
	 * @return boolean This returns true if user is successfully logged out
	 */


	public boolean userLogout() {
		try {			
			controlActions.WaitforelementToBeClickable(UserIcon);
			controlActions.clickOnElement(UserIcon);
			threadsleep(3000);
			controlActions.WaitforelementToBeClickable(Logout);
			controlActions.clickOnElement(Logout);			
			logInfo("User logged out");
			threadsleep(5000);
			return true;
		}
		catch(Exception e) {
			logError("Failed to log out - "
					+ e.getMessage());
			return false;
		}		
	}


	/**
	 * This method is used to get Timezone code equivalent to value
	 * @author hingorani_a
	 * @param timezone Use Class TIMEZONE to provide timezone value
	 * @return String This returns equivalent value for timezone 
	 */

	public String getTimezoneCode(String timezone) {
		switch(timezone) {
		case TIMEZONE.USEASTERN:
			return "EDT";
		case TIMEZONE.USPACIFIC:
			return "PDT";
		case TIMEZONE.REPUBLICOFINDIA:
			return "IST";
		case TIMEZONE.GREENWICHMEANTIME:
			return "GMT";
		case TIMEZONE.UTC:
			return "UTC";
		default:
			logError("Incorrect timezone option");
			return null;
		}
	}

	/** This method is used to show the logged in user details(First & Last name)
	 * @author pashine_a
	 * @param none
	 * @return boolean status
	 * IF got user details THEN true ELSE false
	 */

	public boolean showLoggedInUserDetails() {
		try {
			controlActions.WaitforelementToBeClickable(UserDetailsLbl);
			threadsleep(2000);
			logInfo("Name of logged in user - "+UserDetailsLbl.getText());
			return true;
		}catch(Exception e) {
			logError("Failed to get logged in user details - " + e.getMessage());
			return false;
		}
	}

	/** This method is used to show the logged in user details(First & Last name)
	 * @author pashine_a
	 * @param none
	 * @return String - Name of user
	 * IF got user details THEN name ELSE null
	 */

	public String getLoggedInUserDetails() {
		try {
			controlActions.WaitforelementToBeClickable(UserDetailsLbl);
			threadsleep(2000);
			String name = UserDetailsLbl.getText();
			logInfo("Name of logged in user - "+name);
			return name;
		}catch(Exception e) {
			logError("Failed to get logged in user details - " + e.getMessage());
			return null;
		}
	}



	/**
	 * This method is used to wait until loading element is invisible
	 * @author hingorani_a
	 * @param none
	 * @return none
	 */
	public void Sync() {
		WaitForLoad();
		WebDriverWait wait = new WebDriverWait(driver, 180);
		wait.until(ExpectedConditions.and(
				//ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'loading')]")),
				ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@class,'loading')]"))
				));
	}
	
	/**
	 * This method is used to wait until loading element is visible on Link page
	 * @author hingorani_a
	 */
	public void LinkSync() {
		WaitForLoad();
		WebDriverWait wait = new WebDriverWait(driver, 180);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@style,'rdWaitAll.gif')]")));
	}
	

//	// NOT USED BUT DON'T DELETE
//	public void Sync12() {
//		System.out.println("TEST1");
//		String elementClassName = null;
//		Long startTime = System.currentTimeMillis();
//		WaitForLoad();
//		threadsleep(2000);
//		while(true) {
//			try {
//				elementClassName = driver.findElement(By.xpath("/html/body/div[1]")).getAttribute("class");
//				if(!className.equals("k-loading-mask")) {
//					break;
//				}
//			}catch(Exception e1) {
//				while(true) {
//					try {
//						elementClassName = driver.findElement(By.xpath("/html/body/scs-main-app/div")).getAttribute("class");
//						if(!className.equals("k-i-loading k-icon ng-star-inserted")) {
//							break;
//						}
//					}catch(Exception e2) {
//						break;
//					}
//				}
//				break;
//			}
//		}
//		long endTime = System.currentTimeMillis();
//		long totalTimeToLoad = endTime - startTime;
//		System.out.println("Total time taken using sync1 :- " + totalTimeToLoad + " milliseconds");
//		threadsleep(2000);
//
//	}
//
//	public void Sync22() {
//		System.out.println("TEST2");
//		String elementClassName = null;
//		Long startTime = System.currentTimeMillis();
//		WaitForLoad();
//		threadsleep(2000);
//		while(true) {
//			while(true) {
//				try {
//					elementClassName = driver.findElement(By.xpath("/html/body/scs-main-app/div")).getAttribute("class");
//					if(!className.equals("k-i-loading k-icon ng-star-inserted")) {
//						break;
//					}
//				}catch(Exception e2) {
//					break;
//				}
//			}
//			break;
//		}
//		long endTime = System.currentTimeMillis();
//		long totalTimeToLoad = endTime - startTime;
//		System.out.println("Total time taken using sync2 :- " + totalTimeToLoad + " milliseconds");
//		threadsleep(2000);
//	}


	/**
	 * This method is used to wait until page is loaded
	 * @author hingorani_a
	 */

	private void WaitForLoad() {
		new WebDriverWait(driver, 180).until((ExpectedCondition<Boolean>) wd ->
		((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
	}



	/** This method is used to set the value in 'Corrections' text area box
	 * @author pashine_a
	 * @param WebElement - 'correctionsBox'
	 * @return boolean status
	 * IF value in set in 'Corrections' text area box THEN true ELSE false
	 */	

	public boolean setCorrectionTextarea(WebElement correctionsBox) {
		try {
			controlActions.sendKeys(correctionsBox, "Test Corrections");
			logInfo("Value setted in 'Corrections'");
			return true;
		}catch(Exception e) {
			logError("Failed to set value in Corrections - "+e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to click on Hamburger Menu > Device Manager Menu.
	 * @author dahale_p
	 * @param none 
	 * @return Devices page This returns object with error variable as false
	 * if Browser menu is clicked.
	 */
	public DevicesPage clickDevicesMenu() { 
		DevicesPage dp = new DevicesPage(driver);
		try {
			if(clickFSQABrowserMenu().error) {
				dp.error = true;
				return dp;
			}
			if(!clickAdminToolsMenu()) {
				dp.error = true;
				return dp;
			}

			controlActions.perform_scrollToElement_ByElement(DevicesMnu);
			controlActions.clickElement(DevicesMnu);
			Sync();
			logInfo("Clicked on 'Admin Tools -> Devices' Menu");
			return dp;			
		}catch(Exception e) {
			logError("Failed to click - 'Admin Tools -> Devicess' Menu - "+ e.getMessage());
			dp.error = true;
			return dp;
		}
	}

	/** This method is used to click on to Hamburger Menu > Admin Tools >  Data Import/Export Menu
	 * @author pashine_a
	 * @param none
	 * @return 'DataImportExportPage' object with error status
	 * IF clicked on 'Admin Tools ->  Data Import/Export Menu' THEN object.error - false ELSE object.error - true
	 */

	public DataImportExportPage clickDataImportExportMenu() {
		DataImportExportPage dix = new DataImportExportPage(driver);
		try {
			if(clickFSQABrowserMenu().error) {
				dix.error = true;
				return dix;
			}
			if(!clickAdminToolsMenu()) {
				dix.error = true;
				return dix;
			}

			controlActions.perform_scrollToElement_ByElement(FormDesignerMnu);
			controlActions.clickElement(DataImportExportMnu);
			Sync();
			logInfo("Selected 'Admin Tools -> Data Import/Export' Menu");
			return dix;			
		}catch(Exception e) {
			logError("Failed to click - 'Admin Tools ->  Data Import/Export' Menu - "+ e.getMessage());
			dix.error = true;
			return dix;
		}	
	}
	
	/**
	 * This method is used to check if label is Task Scheduler.
	 * @author pednekar_pr
	 * @return boolean This returns true if label is Task Scheduler.
	 */
	public boolean verifyLabelTaskSchedularMenu() {

		try {
			if (controlActions.performGetElementByXPath(CommonPageLocators.TASKSCHEDULER_MNU)!=null)
			{
				controlActions.perform_scrollToElement_ByElement(TaskSchedulerMnu);
			controlActions.perform_CheckIfElementTextEquals(TaskSchedulerMnu, "Task Scheduler");
			Sync();
			logInfo("Label of tab is Task Scheduler");
			return true;	
			}
			return false;
		}
		catch(Exception e) {
			logError("Failed to check whether label is Task Scheduler or not - "+ e.getMessage());
			return false;
		}
	}	

	/**
	 * This method is used to verify location of Label present in Hamburger Menu based on previous & next
	 * @author pednekar_pr
	 * @param val The verification of label will be done as follows
	 * if val is set to 1 than the label previous to present will be verified 
	 * if val is set to 2 than the label next to present will be verified 
	 * if val is set to 3 than the label previous and next to present will be verified
	 * @param prev This is the previous value to the present label to be verified
	 * @param nxt This is the next value to the present label to be verified
	 * @param prsnt This is the present label 
	 * @return boolean This returns true if location of present label is verified.
	 */
	public boolean verifyLocationOfLabelInMnu(int val, String prev, String nxt, String prsnt) {
		Boolean checkIfPresent = false;
		try {
			for(int i = 0; i < HamburgerMnuLst.size();i++) {

				if(HamburgerMnuLst.get(i).getText().equals(prsnt))
				{
					// val==1 -> to check above element text
					switch (val)
					{

					case 1:{
						if ( i!=0 && HamburgerMnuLst.get(i-1).getText().equals(prev)) {
							checkIfPresent = true;
							logInfo("Location of "+prsnt+" Menu is below " +prev+ " Menu ");
							break;
						}
					}

					//val==2-> to check below element text
					case 2:
					{
						if (i!=(HamburgerMnuLst.size()-1) && HamburgerMnuLst.get(i+1).getText().equals(nxt)) {
							checkIfPresent = true;
							logInfo("Location of "+prsnt+" Menu is above " + nxt+ " Menu ");
							break;
						}
					}
					//val==3 -> to check above and below element text
					case 3:
					{
						if (i!=(HamburgerMnuLst.size()-1) && i!=0  && HamburgerMnuLst.get(i+1).getText().equals(nxt) && HamburgerMnuLst.get(i-1).getText().equals(prev)) {
							checkIfPresent = true;
							logInfo("Location of "+prsnt+" Menu is between " +prev+ " Menu & " + nxt+ " Menu ");
							break;
						}
					}

					default:
					{
						logError("Invalid input for verification of "+prsnt);
						checkIfPresent = false;
						break;
					}

					}
				}
			}
		}

		catch(Exception e) {
			logError("Failed to verify the location of label- "+prsnt+ e.getMessage());
		}
		return checkIfPresent;
	}

	/**
	 * This method is used to check if icon matches Task Scheduler icon
	 * @author pednekar_pr
	 * @return boolean This returns true if  icon matches Task Scheduler icon.
	 */
	public boolean verifyIconTaskSchedularMenu() {
		try {

			String	hamburgerMnuImg = controlActions.perform_GetDynamicXPath(CommonPageLocators.HAMBURGER_MNU_IMG, "MENU_ITEM", 
					HAMBURGER_MENU_TITLE.TASKSCHEDULERMNU);
			controlActions.isElementDisplayed(hamburgerMnuImg);
			logInfo("Icon matches Task Scheduler Menu's icon");
			return true;			
		}
		catch(Exception e) {
			logError("Failed to verify whether Icon matches Task Scheduler Menu's icon - "+ e.getMessage());
			return false;
		}
	}
	
	/**
	 * This method is used to click & navigate to TaskSchedular Menu 
	 * @author pednekar_pr
	 * @return TaskSchedulerPage This returns object with error variable as false
	 * if Task Scheduler Menu is clicked.
	 */
	
	public TaskSchedulerPage clickTaskSchedulerTab() {
		TaskSchedulerPage tsp= new TaskSchedulerPage (driver);

		try {
			//controlActions.perform_scrollToElement_ByElement(TaskSchedulerMnu);
			controlActions.clickElement(TaskSchedulerMnu);
			Sync();
			logInfo("Clicked on Task Scheduler Menu");
			return tsp;			
		}
		catch(Exception e) {
			logError("Failed to click Task Scheduler Menu - "+ e.getMessage());
			tsp.error = true;
			return tsp;
		}
	}	
	
	
	
	/**
	 * This method is used to click & navigate to TaskSchedular Menu 
	 * @author pednekar_pr
	 * @return TaskSchedulerPage This returns object with error variable as false
	 * if Task Scheduler Menu is clicked.
	 */
	
	public boolean verifyInboxPage() {
		try {
			//controlActions.perform_scrollToElement_ByElement(TaskSchedulerMnu);
			if(controlActions.isElementDisplay(InboxFiltersText))
			{	logInfo("Navigated to Inbox Page");
				return true;			
			}
			else
			{
				logError("Failed to verify navigated to Inbox Page ");
				return false;
			}
			
		}
		catch(Exception e) {
			logError("Failed to verify navigated to Inbox Page  - "+ e.getMessage());
			return false;
		}
	}	
	
	/**This method is used to change the current User's Password.
	 * @author ahmed_tw
	 * @param newPassword [String] : The new password to be set
	 * @return  [boolean] : True after successfully changing the password, false if fails to do so
	 */
	public boolean changePassword(String newPassword) {
		try {			
			controlActions.WaitforelementToBeClickable(UserIcon);
			controlActions.clickOnElement(UserIcon);
			logInfo("Clicked 'User Icon'");
			threadsleep(3000);
			controlActions.WaitforelementToBeClickable(ChangePasswordCalloutOption);
			controlActions.clickOnElement(ChangePasswordCalloutOption);			
			logInfo("Clicked On 'Change Password' option");
			threadsleep(5000);
			controlActions.waitforElementToBeDisplayed(ChangePasswordPopUp);
			controlActions.waitforElementToBeDisplayed(NewPasswordInput);
			controlActions.waitforElementToBeDisplayed(ConfirmPasswordInput);

			NewPasswordInput.sendKeys(newPassword);
			ConfirmPasswordInput.sendKeys(newPassword);
			
			controlActions.clickbutton(ChangePasswordYesButton);
			
			logInfo("Successfully Changed the Password");
			return true;
		}
		catch(Exception e) {
			logError("Could not Change password - "+ e.getMessage());
			return false;
		}		
	}
	

	
public static class TIMEZONE{
	public static final String USEASTERN = "U.S. Eastern";
	public static final String USPACIFIC = "U.S. Pacific";
	public static final String REPUBLICOFINDIA = "Republic of India";
	public static final String GREENWICHMEANTIME = "Greenwich Mean Time";
	public static final String UTC = "UTC";
}


public static class HAMBURGER_MENU_TITLE{

	public static final String DASHBOARD = "Dashboard";		
	public static final String BROWSERMNU = "Browser";
	public static final String RECORDSIGNOFFMNU = "Record Signoff";
	public static final String DOCUMENTMANAGEMENT = "Documents";
	public static final String TASKSCHEDULERMNU = "Task Scheduler";
	public static final String INBOX = "Inbox";
	public static final String ADMINTOOLSMNU = "Admin Tools";
	public static final String USERSMNU = "Users";
	public static final String WORKGROUPMNU = "Work Groups";
	public static final String RESOURCEDESIGNERMNU = "Resource Designer";
	public static final String RESOURCESMNU = "Resources";
	public static final String REQUIREMENTSMNU = "Requirements";
	public static final String LOCATIONMNU = "Locations";
	public static final String ROLESMNU = "Roles";
	public static final String FORMDESIGNERMNU = "Form Designer";
	public static final String VALIDATIONSMNU = "Validations";
	public static final String VERIFICATIONSMNU = "Verifications";
	public static final String FORMSMANAGERMNU = "Form Manager";
	public static final String CHARTBUILDERMNU = "Chart Builder";
	public static final String DEVICESMNU = "Devices";
	public static final String PROGRAMDESIGNERMNU = "Program Designer";
	public static final String DATAIMPORTEXPORTMNU = "Data Import / Export";

}

public enum PreDefinedCorrectionOptions {
	Option1, Option2, Option3, Option4 
}  



}
