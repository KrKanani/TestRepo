package com.project.safetychain.webapp.pageobjects;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.project.utilities.ControlActions;
import com.project.utilities.DateTime;

public class ManageRequirementPage extends CommonPage{

	WebDriver driver;
	WebDriverWait wait;
	ControlActions controlActions;
	DateTime datetime;

	public ManageRequirementPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 40);
		controlActions = new ControlActions(driver);
		datetime = new DateTime();
	}	

	@FindBy(xpath = ManageRequirementPageLocators.PLUS_BTN)
	public WebElement PlusBtn;

	@FindBy(xpath = ManageRequirementPageLocators.TEMP_INPUT)
	public WebElement TempInput;

	@FindBy(xpath = ManageRequirementPageLocators.REQ_DRD)
	public WebElement ReqDrd;

	@FindBy(xpath = ManageRequirementPageLocators.ACK_SELECT)
	public WebElement AckSelect;

	@FindBy(xpath = ManageRequirementPageLocators.DOC_SELECT)
	public WebElement DocSelect;

	@FindBy(xpath = ManageRequirementPageLocators.NEXT_BTN)
	public WebElement NextBtn;

	@FindBy(xpath = ManageRequirementPageLocators.TASKNAME_INPUT)
	public WebElement TaskNameInput;

	@FindBy(xpath = ManageRequirementPageLocators.DUEIN_INPUT)
	public WebElement DueInInput;

	@FindBy(xpath = ManageRequirementPageLocators.DUE_INPUT)
	public WebElement DueInput;

	@FindBy(xpath = ManageRequirementPageLocators.SAVE_BTN)
	public WebElement SaveBtn;

	@FindBy(xpath = ManageRequirementPageLocators.DOCUPLOAD_SELECT)
	public WebElement DocUploadSelect;

	@FindBy(xpath = ManageRequirementPageLocators.SEARCH_INPUT)
	public WebElement SearchInput;

	@FindBy(xpath = ManageRequirementPageLocators.COMPLETE_FRM_SELECT)
	public WebElement CompleteFrmSelect;

	@FindBy(xpath = ManageRequirementPageLocators.SUPPLIER_TAB)
	public WebElement SupplierTab;

	@FindBy(xpath = ManageRequirementPageLocators.ITEMS_TAB)
	public WebElement ItemsTab;

	@FindBy(xpath = ManageRequirementPageLocators.REQUIREMENTS_TAB)
	public WebElement RequirementsTab;

	@FindBy(xpath = ManageRequirementPageLocators.SELECT_NEW_BUTTON)
	public WebElement SelectNewBtn;

	@FindBy(xpath = ManageRequirementPageLocators.NAME_ARROW)
	public WebElement NameArrow;

	@FindBy(xpath = ManageRequirementPageLocators.FILTER)
	public WebElement Filter;

	@FindBy(xpath = ManageRequirementPageLocators.FILTER_INPUT)
	public WebElement FilterInput;

	@FindBy(xpath = ManageRequirementPageLocators.FILTER_BTN)
	public WebElement FilterBtn;

	@FindBy(xpath = ManageRequirementPageLocators.INSTANCE_SELECT)
	public WebElement InstanceSelect;

	@FindBy(xpath = ManageRequirementPageLocators.INSTANCE_SELECT)
	public List<WebElement> InstanceSelectLst;

	@FindBy(xpath = ManageRequirementPageLocators.SAVEINST_BTN)
	public WebElement SaveinstBtn;

	@FindBy(xpath = ManageRequirementPageLocators.RESOURCE_SELECT)
	public WebElement ResourceSelect;

	@FindBy(xpath = ManageRequirementPageLocators.SELECT_ITEMS)
	public WebElement SelectItems;

	@FindBy(xpath = ManageRequirementPageLocators.SELECT_SUPPLIER)
	public WebElement SelectSuppliers;

	@FindBy(xpath = ManageRequirementPageLocators.INSTANCE_SELECTION_NEXT_ICON)
	public WebElement InstanceNextBtn;

	@FindBy(xpath = ManageRequirementPageLocators.MINSTANCE_ELEMENT)
	public List<WebElement> MInstanceLst;

	@FindBy(xpath = ManageRequirementPageLocators.SINSTANCE_ELEMENT)
	public List<WebElement> SInstanceLst;

	@FindBy(xpath = ManageRequirementPageLocators.INSTANCE_ELEMENT_INP)
	public List<WebElement> InstanceLstInp;

	@FindBy(xpath = ManageRequirementPageLocators.MFIRST_INSTANCE)
	public WebElement MInstanceLstFirst;

	@FindBy(xpath = ManageRequirementPageLocators.SFIRST_INSTANCE)
	public WebElement SInstanceLstFirst;

	@FindBy(xpath = ManageRequirementPageLocators.ENABLE_DISABLE_BTN)
	public WebElement EnableDisableBtn;

	@FindBy(xpath = ManageRequirementPageLocators.YES_BTN)
	public WebElement YesBtn;

	@FindBy(xpath = ManageRequirementPageLocators.COPY_BTN)
	public WebElement CopyBtn;

	@FindBy(xpath = ManageRequirementPageLocators.REQ_GRD)
	public List<WebElement> ReqGrdLst;

	@FindBy(xpath = ManageRequirementPageLocators.TEMP_SEARCH_INPUT)
	public WebElement TempSearchInput;

	@FindBy(xpath = ManageRequirementPageLocators.SVE_BTN)
	public WebElement SveBtn;

	@FindBy(xpath = ManageRequirementPageLocators.INCREASE_DUEIN)
	public WebElement IncreaseDueIn;

	@FindBy(xpath = ManageRequirementPageLocators.CLOSE_COMPLETEFORM_POPUP)
	public WebElement CloseCompleteFormPopUp;

	@FindBy(xpath = ManageRequirementPageLocators.GRID_HEADERS)
	public List<WebElement> GridHeaders;

	@FindBy(xpath = ManageRequirementPageLocators.RESOURCE_SELECTION_INPUT)
	public WebElement ResourceSelectionInput;

	@FindBy(xpath = ManageRequirementPageLocators.DATE_PICKER)
	public WebElement DatePicker;

	@FindBy(xpath = ManageRequirementPageLocators.RESOURCE_NAME)
	public WebElement ResourceName;

	@FindBy(xpath = ManageRequirementPageLocators.RESOURCE_NAME1)
	public WebElement ResourceName1;

	@FindBy(xpath = ManageRequirementPageLocators.SELECT_ALL_INPUT)
	public WebElement SelectAllInput;

	@FindBy(xpath = ManageRequirementPageLocators.RESOURCE_NAME_HEADING)
	public WebElement ResourceNameHeading;

	@FindBy(xpath = ManageRequirementPageLocators.RESOURCE_LST)
	public List<WebElement> ResourceList;

	@FindBy(xpath = ManageRequirementPageLocators.CLOSE_BTN)
	public WebElement CloseBtn;
	
	@FindBy(xpath = ManageRequirementPageLocators.SUPPLIER_BTN)
	public WebElement SupplierBtn;
	
	@FindBy(xpath = ManageRequirementPageLocators.YES_POPUP_BTN)
	public WebElement YesPopupBtn;


	/**
	 * Functions
	 */

	/**
	 * This method is used to add supplier template
	 * @author choubey_a
	 * @param supptempname - name of the supplier template
	 * @return boolean This returns true if supplier template is created
	 */

	public boolean addSupplierTemplate(String supptempname) {
		try {
			controlActions.WaitforelementToBeClickable(ResourceSelect);
			controlActions.clickOnElement(ResourceSelect);
			Thread.sleep(3000);
			controlActions.clickOnElement(SelectSuppliers);
			Sync();
			controlActions.WaitforelementToBeClickable(PlusBtn);
			controlActions.clickOnElement(PlusBtn);
			Sync();
			controlActions.actionSendKeys(TempInput,supptempname);
			controlActions.clickOnElement(TempInput);
			Thread.sleep(5000);
			controlActions.actionEnter();
			Sync();
			controlActions.WaitforelementToBeClickable(RequirementsTab);
			controlActions.clickOnElement(RequirementsTab);
			Sync();
			logInfo("Supplier template created" + supptempname );
			return true;		
		}
		catch(Exception e) {
			logError("Failed to create supplier template " + supptempname + " - "
					+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to add item template
	 * @author choubey_a
	 * @param itemtempname - name of the item template
	 * @return boolean This returns true if item template is created
	 */


	public boolean addItemTemplate(String itemtempname) {
		try {
			controlActions.WaitforelementToBeClickable(ResourceSelect);
			controlActions.clickOnElement(ResourceSelect);
			Thread.sleep(3000);
			controlActions.clickOnElement(SelectItems);
			Sync();
			controlActions.WaitforelementToBeClickable(PlusBtn);
			controlActions.clickOnElement(PlusBtn);
			Sync();
			controlActions.actionSendKeys(TempInput,itemtempname);
			controlActions.clickOnElement(TempInput);
			Thread.sleep(5000);
			controlActions.actionEnter();
			Sync();
			controlActions.WaitforelementToBeClickable(RequirementsTab);
			controlActions.clickOnElement(RequirementsTab);
			Sync();
			logInfo("Item template created" + itemtempname );
			return true;		
		}
		catch(Exception e) {
			logError("Failed to create item template " + itemtempname + " - "
					+ e.getMessage());
			return false;
		}	

	}

	/**
	 * This method is used to add acknowledgement requirement
	 * @author choubey_a
	 * @param doctype - name of doc type
	 * @param ackreqname - name of the acknowledgement task
	 * @param workgroup - name of the work group
	 * @return boolean This returns true if acknowledgment requirement is created
	 */

	public boolean addAcknowledgmentRequirement(String doctype,String ackreqname,String workgrpoup) {
		try {
			controlActions.WaitforelementToBeClickable(RequirementsTab);
			controlActions.clickOnElement(RequirementsTab);
			Sync();
			controlActions.WaitforelementToBeClickable(ReqDrd);
			controlActions.clickOnElement(ReqDrd);
			Thread.sleep(3000);
			controlActions.clickOnElement(AckSelect);
			Sync();
			String select = "li[class='k-item'][role='option']";
			controlActions.JSSetValueFromList(driver, select, doctype);	
			Thread.sleep(3000);
			controlActions.perform_ClickWithJavaScriptExecutor(DocSelect);
			Thread.sleep(3000);
			controlActions.clickOnElement(NextBtn);
			controlActions.clickOnElement(NextBtn);
			Thread.sleep(3000);
			TaskNameInput.clear();
			controlActions.actionSendKeys(TaskNameInput,ackreqname);
			Thread.sleep(3000);
			String select1 = "li[class='k-item'][role='option']";
			controlActions.JSSetValueFromList(driver, select1, workgrpoup);
			Thread.sleep(3000);
			controlActions.clickOnElement(DueInInput);
			controlActions.actionSendKeys(DueInput,"1");
			Thread.sleep(3000);
			controlActions.clickOnElement(SaveBtn);
			Sync();
			logInfo("Acknowledgement Task added" + ackreqname );
			return true;	
		}
		catch(Exception e) {
			logError("Failed to add acknowledgement task " + ackreqname + " - "
					+ e.getMessage());
			return false;
		}	

	}

	/**
	 * This method is used to add document upload requirement
	 * @author choubey_a
	 * @param doctype - name of doc type
	 * @param ackreqname - name of the document upload task
	 * @param workgroup - name of the work group
	 * @return boolean This returns true if document upload requirement is created
	 */

	public boolean addDocUploadRequirement(String doctype,String docreqname,String workgrpoup) {
		try {
			controlActions.WaitforelementToBeClickable(RequirementsTab);
			controlActions.clickOnElement(RequirementsTab);
			Sync();
			controlActions.WaitforelementToBeClickable(ReqDrd);
			controlActions.clickOnElement(ReqDrd);
			Thread.sleep(3000);
			controlActions.clickOnElement(DocUploadSelect);
			Sync();
			controlActions.clickOnElement(SearchInput);
			controlActions.sendKeys(SearchInput,doctype);			
			Thread.sleep(3000);
			controlActions.clickOnElement(SearchInput);
			Thread.sleep(2000);
			controlActions.actionEnter();
			Thread.sleep(3000);
			controlActions.perform_ClickWithJavaScriptExecutor(DocSelect);
			Thread.sleep(3000);
			controlActions.WaitforelementToBeClickable(NextBtn);
			controlActions.clickOnElement(NextBtn);
			Thread.sleep(3000);
			TaskNameInput.clear();
			controlActions.sendKeys(TaskNameInput,docreqname);
			Thread.sleep(3000);
			String select1 = "li[class='k-item'][role='option']";
			controlActions.JSSetValueFromList(driver, select1, workgrpoup);
			Thread.sleep(3000);
			controlActions.clickOnElement(DueInInput);
			controlActions.sendKeys(DueInput,"2");
			Thread.sleep(3000);
			controlActions.clickOnElement(SaveBtn);
			Sync();
			logInfo("Document Requirement added" + docreqname );
			return true;	
		}
		catch(Exception e) {
			logError("Failed to add document requirement " + docreqname + " - "
					+ e.getMessage());
			return false;
		}	

	}

	/**
	 * This method is used to add complete form requirement
	 * @author choubey_a
	 * @param doctype - name of doc type
	 * @param formname - name of the form 
	 * @param workgroup - name of the work group
	 * @return boolean This returns true if complete form requirement is created
	 */

	public boolean addCompleteFormRequirement(String formname,String formreqname,String workgrpoup) {
		try {
			controlActions.WaitforelementToBeClickable(RequirementsTab);
			controlActions.clickOnElement(RequirementsTab);
			Sync();
			controlActions.WaitforelementToBeClickable(ReqDrd);
			controlActions.clickOnElement(ReqDrd);
			Thread.sleep(3000);
			controlActions.clickOnElement(CompleteFrmSelect);
			Sync();
			controlActions.clickOnElement(SearchInput);
			controlActions.sendKeys(SearchInput,formname);			
			Thread.sleep(3000);
			controlActions.clickOnElement(SearchInput);
			controlActions.actionEnter();
			Sync();
			controlActions.perform_ClickWithJavaScriptExecutor(DocSelect);
			Thread.sleep(3000);
			controlActions.clickOnElement(NextBtn);
			Thread.sleep(3000);
			TaskNameInput.clear();
			controlActions.sendKeys(TaskNameInput,formreqname);
			Thread.sleep(3000);
			String select1 = "li[class='k-item'][role='option']";
			controlActions.JSSetValueFromList(driver, select1, workgrpoup);
			Thread.sleep(3000);
			controlActions.clickOnElement(DueInInput);
			controlActions.sendKeys(DueInput,"3");
			Thread.sleep(3000);
			controlActions.clickOnElement(SaveBtn);
			Sync();
			logInfo("Complete Form Requirement added" + formreqname );
			return true;	
		}
		catch(Exception e) {		
			logError("Failed to add complete form requirement " + formreqname + " - "
					+ e.getMessage());
			return false;
		}	

	}


	/**
	 * This method is used to add instance
	 * @author choubey_a
	 * @param instance - name of instance to be added
	 * @return boolean This returns true if complete form requirement is created
	 */

	public boolean addInstance(String instance) {
		String instanceElement;
		WebElement instanceListFirstElement;
		List<WebElement> InstanceLst;
		int flag = 0;
		try {			
			controlActions.clickElement(SelectNewBtn);
			Sync();
			if(controlActions.isElementAvailable(ManageRequirementPageLocators.MULTIPLE_FIELDS_CHECK)) {
				instanceElement = ManageRequirementPageLocators.MINSTANCE_ELEMENT;
				instanceListFirstElement = MInstanceLstFirst;
				InstanceLst = MInstanceLst;
			}else {
				instanceElement = ManageRequirementPageLocators.SINSTANCE_ELEMENT;
				instanceListFirstElement = SInstanceLstFirst;
				InstanceLst = SInstanceLst;
			}
			while(true) {
				String instancePresent = controlActions.perform_GetDynamicXPath(ManageRequirementPageLocators.INSTANCE_AVAILABLE, "INSTANCE_NAME", instance);
				controlActions.clickElement(instanceListFirstElement);
				if(controlActions.isElementAvailable(instancePresent)) {
					InstanceLst = controlActions.getElements(instanceElement);
					InstanceLstInp = controlActions.getElements(ManageRequirementPageLocators.INSTANCE_ELEMENT_INP);
					for(int i=0;i<InstanceLst.size();i++) {
						controlActions.hoverElement(InstanceLst.get(i));
						if(InstanceLst.get(i).getText().toString().equals(instance)) {
							controlActions.moveclickElement(InstanceLstInp.get(i));
							flag = 1;
							break;
						}
					}
				}else {
					if(InstanceNextBtn.getAttribute("class").contains("disabled")) {
						logError("Instance not found");
						return false;
					}
					controlActions.clickElement(InstanceNextBtn);
					Sync();
					threadsleep(8000);
				}
				if(flag==1) {
					break;
				}
			}
			controlActions.clickElement(SaveinstBtn);
			Sync();
			controlActions.refreshPage();
			Sync();
			logInfo("Instance selected: " + instance);	
			return true;
		}

		catch(Exception e) {
			logError("Failed to Select instance  - " +instance 
					+ e.getMessage());
			e.printStackTrace();
			return false;
		}	
	}

	/**
	 * This method is used to add instance for the requirement
	 * @author choubey_a
	 * @param Resource -Suppliers/Items
	 * @param instance - name of the instance to be added
	 * @return boolean This returns true if instance is added to the selected resource
	 */

	public boolean addInstancetoRequirement(String Resource,String instance) {
		try {
			Resource = Resource.toUpperCase();
			switch(Resource) {
			case "SUPPLIERS":
				controlActions.clickElement(SupplierTab);	
				Sync();
				if(!addInstance(instance)){
					return false;	
				}								
				break;

			case "ITEMS":
				controlActions.clickElement(ItemsTab);
				Sync();
				if(!addInstance(instance)){
					return false;	
				}								
				break;
			default:
				logInfo("INVALID instance");
				return false;
			}
			logInfo("Instance added for -" + Resource);
			return true;
		}catch(Exception e) {
			logError("FAILED to add instance for - "+ Resource +e.getMessage());
			return false;
		}		

	}

	/**
	 * This method is used to create supplier requirement
	 * @author choubey_a
	 * @param supptempname,doctype,ackreqname,workgrpoup
	 * @param docreqname,formname,formreqname,suppinstance
	 * @return boolean This returns true if supplier requirement is created
	 */

	public boolean createSupplierRequirement(String supptempname,String doctype,String ackreqname,String workgrpoup,String docreqname,String formname,String formreqname,String suppinstance) {
		try {
			if(!addSupplierTemplate(supptempname)) {
				return false;	
			}			
			if(!addAcknowledgmentRequirement(doctype,ackreqname,workgrpoup)) {
				return false;	
			}
			if(!addDocUploadRequirement(doctype,docreqname,workgrpoup)) {
				return false;	
			}
			if(!addCompleteFormRequirement(formname,formreqname,workgrpoup)) {
				return false;	
			}
			if(!addInstancetoRequirement("Suppliers",suppinstance)) {
				return false;
			}
			logInfo("Requirement Created for-" + supptempname);
			return true;
		}catch(Exception e) {
			logError("FAILED to create requirement for - "+ supptempname +e.getMessage());
			return false;
		}

	}

	/**
	 * This method is used to create item requirement
	 * @author choubey_a
	 * @param itemtempname,doctype,ackreqname,workgrpoup
	 * @param docreqname,formname,formreqname,iteminstance
	 * @return boolean This returns true if item requirement is created
	 */


	public boolean createItemRequirement(String itemtempname,String doctype,String ackreqname,String workgrpoup,String docreqname,String formname,String formreqname,String iteminstance) {
		try {
			if(!addItemTemplate(itemtempname)) {
				return false;	
			}	
			if(!addAcknowledgmentRequirement(doctype,ackreqname,workgrpoup)) {
				return false;	
			}				
			if(!addDocUploadRequirement(doctype,docreqname,workgrpoup)) {
				return false;	
			}
			if(!addCompleteFormRequirement(formname,formreqname,workgrpoup)) {
				return false;	
			}
			if(!addInstancetoRequirement("Items",iteminstance)) {
				return false;
			}
			logInfo("Requirement Created for-" + itemtempname);
			return true;
		}catch(Exception e) {
			logError("FAILED to create requirement for - "+ itemtempname +e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to create two doc upload and complete form requirement for suppliers
	 * @author choubey_a
	 * @param itemtempname,doctype,workgrpoup
	 * @param docreqname,formname,formreqname,iteminstance
	 * @return boolean This returns true if supplier requirement is created
	 */

	public boolean createSuppReq(String supptempname,String doctype,String workgrpoup,String docreqname,String docreqname1,String formname,String formreqname,String formreqname1,String suppinstance ) {
		try {
			if(!addSupplierTemplate(supptempname)) {
				return false;	
			}				
			if(!addDocUploadRequirement(doctype,docreqname,workgrpoup)) {
				return false;	
			}
			if(!addDocUploadRequirement(doctype,docreqname1,workgrpoup)) {
				return false;	
			}
			if(!addCompleteFormRequirement(formname,formreqname,workgrpoup)) {
				return false;	
			}
			if(!addCompleteFormRequirement(formname,formreqname1,workgrpoup)) {
				return false;	
			}
			if(!addInstancetoRequirement("Suppliers",suppinstance)) {
				return false;
			}
			logInfo("Requirement Created for-" + suppinstance);
			return true;
		}catch(Exception e) {
			logError("FAILED to create requirement for - "+ suppinstance +e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to create two doc upload and complete form requirement for items
	 * @author choubey_a
	 * @param itemtempname,doctype,workgrpoup
	 * @param docreqname,formname,formreqname,iteminstance
	 * @return boolean This returns true if item requirement is created
	 */

	public boolean createitemreq(String itemtempname,String doctype,String workgrpoup,String docreqname,String docreqname1,String formname,String formreqname,String formreqname1,String iteminstance ) {
		try {
			if(!addItemTemplate(itemtempname)) {
				return false;	
			}				
			if(!addDocUploadRequirement(doctype,docreqname,workgrpoup)) {
				return false;	
			}
			if(!addDocUploadRequirement(doctype,docreqname1,workgrpoup)) {
				return false;	
			}
			if(!addCompleteFormRequirement(formname,formreqname,workgrpoup)) {
				return false;	
			}
			if(!addCompleteFormRequirement(formname,formreqname1,workgrpoup)) {
				return false;	
			}
			if(!addInstancetoRequirement("Items",iteminstance)) {
				return false;
			}
			logInfo("Requirement Created for-" + iteminstance);
			return true;
		}catch(Exception e) {
			logError("FAILED to create requirement for - "+ iteminstance +e.getMessage());
			return false;
		}
	}

	public boolean selectResourceType(String resource) {
		try {
			resource = resource.toUpperCase();
			switch(resource) {
			case "SUPPLIERS":
				controlActions.WaitforelementToBeClickable(ResourceSelect);
				controlActions.clickOnElement(ResourceSelect);
				Thread.sleep(3000);
				controlActions.clickOnElement(SelectSuppliers);
				break;
			case "ITEMS":
				controlActions.WaitforelementToBeClickable(ResourceSelect);
				controlActions.clickOnElement(ResourceSelect);
				Thread.sleep(3000);
				controlActions.clickOnElement(SelectItems);
				break;
			default:
				logInfo("Resource name is invalid");
				return false;			
			}			
			Sync();
			logInfo("Resource selected");
			return true;
		}catch(Exception e) {
			logError("FAILED to select resource -" +e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to enable/disable item/supplier template
	 * @author choubey_a
	 * @param none
	 * @return boolean This returns true if template is enabled/disabled
	 */

	public boolean enabledisabletemplate(String templatename,String resource, boolean enable) {
		try {
			selectResourceType(resource);
			if(enable==true){
				WebElement select = controlActions.perform_GetElementByXPath(ManageRequirementPageLocators.DISABLE_TEMP_SELECT, "TEMPLATE_NAME", templatename);
				controlActions.clickElement(select);	
			}
			else if(enable==false){
				WebElement select = controlActions.perform_GetElementByXPath(ManageRequirementPageLocators.TEMPLATE_SELECT, "TEMPLATE_NAME", templatename);
				controlActions.clickElement(select);
			}
			Sync();
			controlActions.clickOnElement(EnableDisableBtn);
			Thread.sleep(3000);
			controlActions.clickElement(YesBtn);
			Sync();
			logInfo("Template enable/disable");
			return true;
		}catch(Exception e) {
			logError("FAILED to enable/disable template -" +e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to verify disabled item/supplier template
	 * @author choubey_a
	 * @param templatename
	 * @return boolean This returns true if template is disabled verified
	 */

	public boolean verifydisabledtemplate(String templatename) {
		try {
			WebElement templateselect = controlActions.perform_GetElementByXPath(ManageRequirementPageLocators.VERIFY_DISABLE_TEMPLATE, "TEMPLATE_NAME", templatename);
			if(!controlActions.isElementDisplay(templateselect)) {
				logError("Could not Verify Template is disabled" + templatename);
			}
			logInfo("Verified template is disabled");
			return true;
		}catch(Exception e) {
			logError("FAILED to verify enable/disable template -" +e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to copy item/supplier template
	 * @author choubey_a
	 * @param none
	 * @return boolean This returns true if template is copied
	 */

	public boolean copytemplate(String templatename, String resource) {
		try {
			selectResourceType(resource);
			WebElement select = controlActions.perform_GetElementByXPath(ManageRequirementPageLocators.TEMPLATE_SELECT, "TEMPLATE_NAME", templatename);
			controlActions.clickElement(select);
			Sync();
			controlActions.clickOnElement(CopyBtn);
			Thread.sleep(3000);
			controlActions.clickElement(YesBtn);
			Sync();
			logInfo("Template Copied");
			return true;
		}catch(Exception e) {
			logError("FAILED to copy template -" +e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to verify copy item/supplier template
	 * @author choubey_a
	 * @param copytemplatename, requirement
	 * @return boolean This returns true if copy template is verified
	 */


	public boolean verifycopytemplate(String copytemplatename, String requirement[]) {
		try {
			WebElement templateselect = controlActions.perform_GetElementByXPath(ManageRequirementPageLocators.TEMPLATE_SELECT, "TEMPLATE_NAME", copytemplatename);
			if(!controlActions.isElementDisplay(templateselect)) {
				logError("Template not copied");
				return false;
			}
			for(int i=0;i<requirement.length;i++) {
				if(!ReqGrdLst.get(i).getText().equals(requirement[i])) {
					logError("Requirement" +requirement[i]+ "not copied");
					return false;
				}
			}
			logInfo("Template Copy verified");
			return true;
		}catch(Exception e) {
			logError("FAILED to verify copy template -" +e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to search item/supplier template
	 * @author choubey_a
	 * @param templatename, resource
	 * @return boolean This returns true if template is searched
	 */

	public boolean searchtemplate(String templatename,String resource) {
		try {
			selectResourceType(resource);
			Sync();
			controlActions.sendKeys(TempSearchInput,templatename);
			controlActions.clickElement(TempSearchInput);
			Thread.sleep(3000);
			WebElement select = controlActions.perform_GetElementByXPath(ManageRequirementPageLocators.TEMPLATE_LST, "TEMPLATE", templatename);
			controlActions.click(select);
			logInfo("Template Searched");
			return true;
		}catch(Exception e) {
			logError("FAILED to search template -" + e.getMessage());
			return false;
		}
	}


	/**
	 * This method is used to delete item/supplier template
	 * @author choubey_a
	 * @param templatename, resource, requirementname
	 * @return boolean This returns true if requirement is deleted
	 */

	public boolean deleterequirement(String templatename,String resource,String requirementname) {
		try {
			selectResourceType(resource);
			WebElement select = controlActions.perform_GetElementByXPath(ManageRequirementPageLocators.TEMPLATE_SELECT, "TEMPLATE_NAME", templatename);
			controlActions.clickElement(select);
			WebElement selectreq = controlActions.perform_GetElementByXPath(ManageRequirementPageLocators.REQ_SELECT, "REQ_NAME", requirementname);
			controlActions.action.moveToElement(selectreq).build().perform();
			threadsleep(2000);	
			WebElement deletereq = controlActions.perform_GetElementByXPath(ManageRequirementPageLocators.DELETE_BTN, "ACK_TASK", requirementname);
			controlActions.clickElement(deletereq);
			controlActions.clickElement(YesBtn);
			Sync();
			if(controlActions.isElementDisplay(selectreq)){			
				logError("Requirement -"+requirementname+" is not deleted");
				return false;
			}
			logInfo("Requirement Deleted");
			return true;
		}catch(Exception e) {
			logError("FAILED to delete requirement -" +e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to add complete form requirement
	 * @author dahale_p
	 * created date 27 Jan 2021
	 * @param doctype - name of doc type
	 * @param formname - name of the form 
	 * @param workgroup - name of taddCompleteFormRequirementhe work group
	 * @return boolean This returns true if complete form requirement is created
	 */

	public boolean addCompleteFormRequirementblank(String formname,String formreqname) {
		try {
			controlActions.WaitforelementToBeClickable(RequirementsTab);
			controlActions.clickOnElement(RequirementsTab);
			Sync();
			controlActions.WaitforelementToBeClickable(ReqDrd);
			controlActions.clickOnElement(ReqDrd);
			Thread.sleep(3000);
			controlActions.clickOnElement(CompleteFrmSelect);
			Sync();
			controlActions.clickOnElement(SearchInput);
			controlActions.sendKeys(SearchInput,formname);			
			Thread.sleep(3000);
			controlActions.clickOnElement(SearchInput);
			controlActions.actionEnter();

			// to do 

			//String SavedForm1 = ManageRequirementPageLocators.SAVED_FORM.replace("FORM_NAME", formName);
			String SavedForm1 = controlActions.perform_GetDynamicXPath(ManageRequirementPageLocators.COMPLETEFORM, "FORMNAME", formname);
			if(controlActions.isElementDisplayed(SavedForm1))
			{
				logInfo( formname + " is displayed in grid forms");
				controlActions.WaitforelementToBeClickable(CloseCompleteFormPopUp);
				controlActions.clickOnElement(CloseCompleteFormPopUp);
				logInfo("Closed CloseCompleteFormPopUp popup");
				Sync();
				return true;
			}

			else {
				logInfo("Form not found : " +  formname);
				controlActions.WaitforelementToBeClickable(CloseCompleteFormPopUp);
				controlActions.clickOnElement(CloseCompleteFormPopUp);
				logInfo("Closed CloseCompleteFormPopUp popup");
				Sync();
				return false;
			}
		}
		catch(Exception e) {
			logError("Failed to add complete form requirement " + formreqname + " - "
					+ e.getMessage());
			return false;
		}	

	} 

	/**
	 * This method is used to edit item/supplier template
	 * @author choubey_a
	 * @param templatename, resource, requirementname
	 * @return boolean This returns true if requirement is edited
	 */

	public boolean editrequirement(String templatename,String resource,String requirementname) {
		try {
			selectResourceType(resource);
			WebElement select = controlActions.perform_GetElementByXPath(ManageRequirementPageLocators.TEMPLATE_SELECT, "TEMPLATE_NAME", templatename);
			controlActions.clickElement(select);
			WebElement selectreq = controlActions.perform_GetElementByXPath(ManageRequirementPageLocators.REQ_SELECT, "REQ_NAME", requirementname);
			controlActions.action.moveToElement(selectreq).build().perform();
			threadsleep(2000);	
			WebElement editreq = controlActions.perform_GetElementByXPath(ManageRequirementPageLocators.EDIT_BTN, "FORM_TASK", requirementname);
			controlActions.clickElement(editreq);
			Sync();		
			controlActions.clickElement(IncreaseDueIn);
			controlActions.clickElement(SveBtn);
			Sync();
			WebElement editedreq = controlActions.perform_GetElementByXPath(ManageRequirementPageLocators.VERIFY_EDIT, "FORM_TASK", requirementname);
			if(!controlActions.isElementDisplay(editedreq)){			
				logError("Requirement -"+requirementname+" is not edited");
				return false;
			}
			logInfo("Requirement Edited");
			return true;
		}catch(Exception e) {
			logError("FAILED to edit requirement -" +e.getMessage());
			return false;
		}
	}


	/**
	 * This method is used to  sort column by providing column name
	 * @author choubey_a
	 * @param columnName
	 * @return boolean This returns true if column is sorted
	 */

	public String sortColumn(String columnName) {
		List <WebElement> ColumnValue = null; 
		WebElement headerName = null;
		int columnIndex = 0; 
		String columnHeaderPath = null;
		int listLimit = 8;
		try {
			columnIndex = 0;			
			for(WebElement column : GridHeaders) {
				if(column.getText().equalsIgnoreCase(columnName)) {
					columnIndex++;
					headerName = column;
					break;
				}
				else {
					columnIndex++;
				}
			}
			logInfo("For column : " + columnName + " the index is : " + columnIndex);

			if(columnIndex == 0) {
				logError("Failed to get correct column index : " + columnIndex + " for column " + columnName);
				return null;
			}
			else {

				switch(columnName) {

				case COLUMNHEADER1.APPROVER:
					columnHeaderPath = ManageRequirementPageLocators.COLUMN_LST1;
					break;
				case COLUMNHEADER1.ADDED_BY:
					columnHeaderPath = ManageRequirementPageLocators.COLUMN_LST1;
					break;
				default:
					columnHeaderPath = ManageRequirementPageLocators.COLUMN_LST;

				}

				controlActions.clickElement(headerName);
				Sync();

				ColumnValue = controlActions.perform_GetListOfElementsByXPath(columnHeaderPath, 
						"COLUMN_INDEX", Integer.toString(columnIndex));
				if(ColumnValue.size()<8)
					listLimit = ColumnValue.size();

				if(!sortSwitch(ColumnValue,listLimit,true)) {
					logInfo("Sorting is ascending is completed");
					return Sorting.ASC;
				}

				controlActions.clickElement(headerName);
				Sync();

				ColumnValue = controlActions.perform_GetListOfElementsByXPath(columnHeaderPath, 
						"COLUMN_INDEX", Integer.toString(columnIndex));
				if(ColumnValue.size()<8)
					listLimit = ColumnValue.size();

				if(!sortSwitch(ColumnValue,listLimit,false)) {						
					logInfo("Sorting is descending is completed");
					return Sorting.DSC;
				}

			}
			logError("Could not verify sorting for column " + columnName);
			return null;
		}
		catch(Exception e) {
			logError("Failed to sort data" + columnName 
					+ e.getMessage());
			return null;
		}	
	}

	/**
	 * This method is used to  sort column in desc and asc
	 * @author choubey_a
	 * @param columnName
	 * @return boolean This returns true if column is sorted
	 */


	public boolean sortSwitch(List <WebElement> ColumnValue,int listLimit,boolean sort) {

		int compare = 0,ascCount = 0,dscCount = 0,sortCount =0;

		try {
			for(int l=0;l<listLimit;l++) {
				compare = ColumnValue.get(l).getText().compareTo(ColumnValue.get(l+1).getText());
				if(compare==0) {
					sortCount++;
					logInfo("Equal value for - 1) " + ColumnValue.get(l) + " 2) " + ColumnValue.get(l+1));
				}

				else if(compare>0) {
					dscCount++;
					logInfo("Descending value for - 1) " + ColumnValue.get(l) + " 2) " + ColumnValue.get(l+1));
				}

				else if(compare<0) {
					ascCount++;
					logInfo("Ascending value for - 1) " + ColumnValue.get(l) + " 2) " + ColumnValue.get(l+1));
				}
			}

			if(sort) {
				return (sortCount+ascCount==listLimit);
			}else {
				return (sortCount+dscCount==listLimit);
			}
		}
		catch(Exception e) {
			logError("Failed to sort data"
					+ e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to  sort column of resource pop up
	 * @author choubey_a
	 * @param columnName
	 * @return boolean This returns true if column is sorted
	 */

	public String sortResourceColumn() {
		int listLimit = 8;
		try {

			controlActions.clickElement(ResourceNameHeading);
			Sync();
			if(ResourceList.size()<8)
				listLimit = ResourceList.size();

			if(!sortSwitch(ResourceList,listLimit,true)) {
				logInfo("Sorting is ascending is completed");
				return Sorting.ASC;
			}

			controlActions.clickElement(ResourceNameHeading);
			Sync();

			if(!sortSwitch(ResourceList,listLimit,false)) {						
				logInfo("Sorting is descending is completed");
				return Sorting.DSC;
			}
			return null;
		}
		catch(Exception e) {
			logError("Failed to verify sorting column Name"
					+ e.getMessage());
			return null;
		}	
	}

	/**
	 * This method used to navigate to requirements tab
	 * @author choubey_a
	 * @param columnName, templatename, resource
	 * @return boolean This returns true if navigated to requirements tab
	 */

	public boolean navigateToRequirement(String templatename,String resource) {
		try {			
			selectResourceType(resource);
			controlActions.actionSendKeys(TempSearchInput,templatename);			
			String select = "#scs-list-view-search-input_listbox > li";
			controlActions.JSSetValueFromList(driver, select, templatename);			
			controlActions.WaitforelementToBeClickable(RequirementsTab);
			controlActions.clickOnElement(RequirementsTab);
			logInfo("Navigated to requirements tab");
			return true;
		}
		catch(Exception e) {
			logError("Failed to navigate to requirements tab"
					+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method used to navigate to resources tab.
	 * @author choubey_a
	 * @param columnName, templatename, resource
	 * @return boolean This returns true if navigated to resources tab
	 */

	public boolean navigateToResourceTab(String templatename,String resource) {
		try {
			selectResourceType(resource);
			WebElement select = controlActions.perform_GetElementByXPath(ManageRequirementPageLocators.TEMPLATE_SELECT, "TEMPLATE_NAME", templatename);
			controlActions.clickElement(select);
			Sync();
			resource = resource.toUpperCase();
			switch(resource) {
			case "SUPPLIERS":
				controlActions.clickElement(SupplierTab);
				break;
			case "ITEMS":
				controlActions.clickElement(ItemsTab);
				Sync();		
				break;
			default:
				logInfo("INVALID instance");
				return false;
			}
			Sync();
			controlActions.clickElement(SelectNewBtn);
			Sync();
			controlActions.clickElement(SelectAllInput);
			controlActions.clickElement(SaveinstBtn);
			Sync();
			controlActions.refreshPage();
			Sync();
			logInfo("Navigated to resource tab and selected all resources");
			return true;
		}
		catch(Exception e) {
			logError("Failed to navigated to resource tab and selected all resources"
					+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method used to navigate to select new tab
	 * @author choubey_a
	 * @param columnName, templatename, resource
	 * @return boolean This returns true if navigated to select new tab
	 */


	public boolean navigateToSelectNewAndSort(String templatename,String resource) {
		try {
			selectResourceType(resource);
			WebElement select = controlActions.perform_GetElementByXPath(ManageRequirementPageLocators.TEMPLATE_SELECT, "TEMPLATE_NAME", templatename);
			controlActions.clickElement(select);
			Sync();
			resource = resource.toUpperCase();
			switch(resource) {
			case "SUPPLIERS":
				controlActions.clickElement(SupplierTab);
				break;
			case "ITEMS":
				controlActions.clickElement(ItemsTab);
				Sync();		
				break;
			default:
				logInfo("INVALID instance");
				return false;
			}
			Sync();
			controlActions.clickElement(SelectNewBtn);
			Sync();
			sortResourceColumn();			
			controlActions.clickElement(CloseBtn);
			logInfo("Navigated to SelectNew Tab on resource tab");
			return true;
		}
		catch(Exception e) {
			logError("Failed to navigateto SelectNew Tab on resource tab"
					+ e.getMessage());
			return false;
		}	
	}


	/**
	 * This method used to set effective date of a resource while adding for a requirement
	 * @author choubey_a
	 * @param resource
	 * @return boolean This returns true if effective date is added and verified
	 */

	public boolean setEffectiveDate(String Resource,String templatename) {
		String resourcename = null;		
		try {
			Resource = Resource.toUpperCase();
			switch(Resource) {
			case "SUPPLIERS":
				controlActions.clickElement(SupplierTab);
				break;

			case "ITEMS":
				controlActions.clickElement(ItemsTab);
				Sync();		
				break;
			default:
				logInfo("INVALID instance");
				return false;
			}
			Sync();
			controlActions.clickElement(SelectNewBtn);
			Sync();
			if(controlActions.isElementAvailable(ManageRequirementPageLocators.MULTIPLE_FIELDS_CHECK)) {
				resourcename = ResourceName.getText();
			}
			else { 
				resourcename = ResourceName1.getText();
			}
			controlActions.clickElement(ResourceSelectionInput);
			DatePicker.clear();
			controlActions.setDate(DatePicker, "DAY", 1);
			controlActions.clickElement(SaveinstBtn);
			Sync();
			WebElement select1 = controlActions.perform_GetElementByXPath(ManageRequirementPageLocators.RESOURCE_ADDED, "RESOURCE", resourcename);
			if(controlActions.isElementDisplayed(select1)) {
				WebElement date = controlActions.perform_GetElementByXPath(ManageRequirementPageLocators.EFFECTIVE_ON_COL,"RESOURCE", resourcename);
				String effectiveon = date.getText().toString();
				WebElement date1 = controlActions.perform_GetElementByXPath(ManageRequirementPageLocators.ADDED_ON_COL,"RESOURCE", resourcename);
				String addedondate = date1.getText().toString();
				if(!effectiveon .equals(datetime.getDate("DAY", 1).toString())) {
					logError("Effective on is not properly set");
					return false;
				}
				if(!addedondate .equals(datetime.getDate("DAY", 0).toString())) {
					logError("Added on is not properly set");
					return false;
				}
			}
			logInfo("Effective date added and verified" + Resource);
			return true;
		}catch(Exception e) {
			logError("Failed to add and verify effective date -" +e.getMessage());
			return false;
		}		
	}

	/**
	 * This method is a wrapper class for selecting the template and setting the effective date
	 * @author choubey_a
	 * @param resource, templatename
	 * @return boolean This returns true if template is selected and effective date is added and verified
	 */

	public boolean effectiveDateForRequirement(String templatename,String resource) {
		try {
			selectResourceType(resource);
			WebElement select = controlActions.perform_GetElementByXPath(ManageRequirementPageLocators.TEMPLATE_SELECT, "TEMPLATE_NAME", templatename);
			controlActions.clickElement(select);
			Sync();
			if(!setEffectiveDate(resource,templatename)) {
				logError("Failed to set and verify effective date");
				return false;
			}
			return true;
		}catch(Exception e) {
			logError("Failed to add and verify effective date -" +e.getMessage());
			return false;
		}		
	}

	public String addResourceToRequirement(String Resource,String templatename) {
		String resourcename = null;		
		try {
			if(!selectResourceType(Resource)) {
				logError("Not able to select "+Resource);
				return null;
			}
			WebElement select = controlActions.perform_GetElementByXPath(ManageRequirementPageLocators.TEMPLATE_SELECT, "TEMPLATE_NAME", templatename);
			controlActions.clickElement(select);
			Sync();
			Resource = Resource.toUpperCase();
			switch(Resource) {
			case "SUPPLIERS":
				controlActions.clickElement(SupplierTab);
				Sync();
				break;

			case "ITEMS":
				controlActions.clickElement(ItemsTab);
				Sync();		
				break;
			default:
				logInfo("INVALID instance");
				return null;
			}
			Sync();
			controlActions.clickElement(SelectNewBtn);
			Sync();
			if(controlActions.isElementAvailable(ManageRequirementPageLocators.MULTIPLE_FIELDS_CHECK)) {
				resourcename = ResourceName.getText();
			}
			else { 
				resourcename = ResourceName1.getText();
			}
			controlActions.clickElement(ResourceSelectionInput);
			controlActions.clickElement(SaveinstBtn);
			Sync();
			return resourcename;
		}catch(Exception e) {
			logError("Failed to add and verify effective date -" +e.getMessage());
			return null;
		}
	}
	
	/**
	 * This method is used to Create requirement in an existing Supplier Requirement Template
	 * @author hingorani_a
	 * @param suppTempName The name of the existing Supplier Requirement Template
	 * @param docType The name of Document type
	 * @param ackReqName The name for Acknowledgement Requirement
	 * @param workGroup The name of WorkGroup to be associated with Requirement
	 * @param docReqName The name of Upload Document Requirement
	 * @param formName The name of form to be associated with Requirement
	 * @param formReqName The name of Completed Form Requirement
	 * @param suppInstances The list of Supplier instances/resources to be added 
	 * NOTE: ackReqName, docReqName, formReqName, suppInstances are not compulsory
	 * Pass them as null if not required
	 * @return boolean This returns true if supplier requirement is created
	 */
	public boolean createSuppReqInExistingTemplate(String suppTempName, String docType, String ackReqName, String workGroup,
			String docReqName, String formName, String formReqName, List<String> suppInstances) {
		try {
			
			if(!searchtemplate(suppTempName, REQUIREMENT_RESOURCE_TYPE.SUPPLIERS)) 
				return false;	
					
			if(ackReqName!=null) {
				if(!addAcknowledgmentRequirement(docType,ackReqName,workGroup))
					return false;	
			}
			
			if(docReqName!=null) {
				if(!addDocUploadRequirement(docType,docReqName,workGroup))
					return false;
			}
			
			if(formReqName!=null) {
				if(!addCompleteFormRequirement(formName,formReqName,workGroup)) 
					return false;	
			}
			
			if(suppInstances!=null) {
				for(String suppInstance : suppInstances) {
					if(!addInstancetoRequirement(REQUIREMENT_RESOURCE_TYPE.SUPPLIERS,suppInstance)) 
						return false;
				}
			}
			
			logInfo("Requirements added to existing template - " + suppTempName);
			return true;
		}
		catch(Exception e) {
			logError("Failed to add requirements to existing template - " + suppTempName 
					+ e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to Create requirement in an existing Item Requirement Template
	 * @author hingorani_a
	 * @param suppTempName The name of the existing Item Requirement Template
	 * @param docType The name of Document type
	 * @param ackReqName The name for Acknowledgement Requirement
	 * @param workGroup The name of WorkGroup to be associated with Requirement
	 * @param docReqName The name of Upload Document Requirement
	 * @param formName The name of form to be associated with Requirement
	 * @param formReqName The name of Completed Form Requirement
	 * @param itemInstances The list of Item instances/resources to be added 
	 * NOTE: ackReqName, docReqName, formReqName, itemInstances are not compulsory
	 * Pass them as null if not required
	 * @return boolean This returns true if item requirement is created
	 */
	public boolean createItemReqInExistingTemplate(String itemTempName, String docType, String ackReqName, String workGroup,
			String docReqName, String formName, String formReqName, List<String> itemInstances) {
		try {
			
			if(!searchtemplate(itemTempName, REQUIREMENT_RESOURCE_TYPE.ITEMS)) 
				return false;	
					
			if(ackReqName!=null) {
				if(!addAcknowledgmentRequirement(docType,ackReqName,workGroup))
					return false;	
			}
			
			if(docReqName!=null) {
				if(!addDocUploadRequirement(docType,docReqName,workGroup))
					return false;
			}
			
			if(formReqName!=null) {
				if(!addCompleteFormRequirement(formName,formReqName,workGroup)) 
					return false;	
			}
			
			if(itemInstances!=null) {
				for(String itemInstance : itemInstances) {
					if(!addInstancetoRequirement(REQUIREMENT_RESOURCE_TYPE.ITEMS,itemInstance)) 
						return false;
				}
			}
			
			logInfo("Requirements added to existing template - " + itemTempName);
			return true;
		}
		catch(Exception e) {
			logError("Failed to add requirements to existing template - " + itemTempName 
					+ e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to remove item/supplier requirement instance
	 * @author pednekar_pr
	 * @param templatename, resource, instancename
	 * @return boolean This returns true if requirement instance is removed
	 */

	public boolean removeInstance(String templatename,String resource,String instancename) {
		try {
			selectResourceType(resource);
			WebElement select = controlActions.perform_GetElementByXPath(ManageRequirementPageLocators.TEMPLATE_SELECT, "TEMPLATE_NAME", templatename);
			controlActions.clickElement(select);
			//click supplier btn
			controlActions.clickElement(SupplierBtn);
			//select task instance to be removed
			
			WebElement deletereq = controlActions.perform_GetElementByXPath(ManageRequirementPageLocators.DELETE_INSTANCE_BTN, "INSTANCE_NAME", instancename);
			
			controlActions.perform_ClickWithJavaScriptExecutor(deletereq);
			controlActions.clickElement(YesPopupBtn);
			Sync();
			WebElement selectinst = controlActions.perform_GetElementByXPath(ManageRequirementPageLocators.INSTANCE_NAME, "INSTANCE_NAME", instancename);
			if(controlActions.isElementDisplay(selectinst)){			
				logError("Requirement -"+instancename+" is not deleted");
				return false;
			}
			logInfo("Requirement Deleted");
			return true;
		}catch(Exception e) {
			logError("FAILED to delete requirement -" +e.getMessage());
			return false;
		}
	}
	
	public static class COLUMNHEADER1{
		public static final String NAME = "Name";
		public static final String EFFECTIVE_ON = "Effective On";
		public static final String ADDED_ON = "Added On";
		public static final String ADDED_BY = "Added By";
		public static final String TASK_NAME ="Task Name";
		public static final String DUE_IN = "Due In";
		public static final String APPROVER = "Approver";
	}

	public static class Sorting {
		public static final String ASC = "ASC";
		public static final String DSC = "DSC";
	}
	
	public static class REQUIREMENT_RESOURCE_TYPE {
		public static final String SUPPLIERS = "Suppliers";
		public static final String ITEMS = "Items";
	}
}


