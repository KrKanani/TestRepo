package com.project.safetychain.webapp.pageobjects;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ResourceDesignerPage extends CommonPage{

	public ResourceDesignerPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 40);
	}



	@FindBy(xpath = ResourceDesignerPageLocators.LOCATION_CATEGORY_TAB)
	public WebElement LocationTab;
	@FindBy(xpath = ResourceDesignerPageLocators.CUSTOMER_CATEGORY_TAB)
	public WebElement CustomerTab;
	@FindBy(xpath = ResourceDesignerPageLocators.EQUIPMENT_CATEGORY_TAB)
	public WebElement EquipmentTab;
	@FindBy(xpath = ResourceDesignerPageLocators.ITEM_CATEGORY_TAB)
	public WebElement ItemsTab;
	@FindBy(xpath = ResourceDesignerPageLocators.SUPPLIER_CATEGORY_TAB)
	public WebElement SuppliersTab;
	@FindBy(xpath = ResourceDesignerPageLocators.CATEGORIES_TAB)
	public WebElement CategoriesTab;
	@FindBy(xpath = ResourceDesignerPageLocators.CATEGORIES_ELEMENT_TAB)
	public WebElement CategoriesELementTab;
	@FindBy(xpath = ResourceDesignerPageLocators.CATEGORY_HEADING_TRE)
	public WebElement CategoriesHeadingTre;
	@FindBy(xpath = ResourceDesignerPageLocators.ADD_CATEGORY_TRE)
	public WebElement AddCategoryTre;
	@FindBy(xpath = ResourceDesignerPageLocators.SET_CATEGORY_NAME_TXT)
	public WebElement SetCategoryNameTxt;
	@FindBy(xpath = ResourceDesignerPageLocators.SAVE_CATEGORY_BTN)
	public WebElement SaveCategoryBtn;
	@FindBy(xpath = ResourceDesignerPageLocators.VERIFY_CREATED_CATEGORY)
	public WebElement VerifyCreatedCategory;
	@FindBy(xpath = ResourceDesignerPageLocators.ADD_CATEGORY_BTN)
	public WebElement AddCategoryButton;


	public void open_resourceDesigner(CommonPage mainPage) {
		controlActions.clickElement(mainPage.HamburgerMnu);
		controlActions.clickElement(mainPage.AdminToolsMnu);
		controlActions.clickElement(mainPage.ResourceDesignerMnu);
	}

	/** "createCustomerCategory" method is used to add the customer category in the Customer tree
	 * @author pashine_a
	 * @param String  - 'CategoryName'
	 * @return boolean status
	 * IF customer category is added THEN true ELSE false
	 */	
	public boolean createCustomersCategory(String categoryName) {
		String categoryClickedStatus = "k-item k-state-default k-last";
		try {
			if(!selectResourceCategory(CustomerTab,"Customers")) {
				return false;
			}
			while(true) {
				controlActions.doubleClickElement(CategoriesTab);
				categoryClickedStatus = CategoriesELementTab.getAttribute("class").toString();
				if(categoryClickedStatus.equals("k-item k-state-default k-last k-tab-on-top k-state-active")) {
					logInfo("Opened 'Category tab'");
					break;
				}
			}
			Sync();
			controlActions.clickElement(CategoriesHeadingTre);
			controlActions.clickElement(AddCategoryTre);
			controlActions.sendKeys(SetCategoryNameTxt,categoryName);
			controlActions.clickElement(SaveCategoryBtn);
			Sync();
			if(!verifyCreatedCategory(categoryName)) {
				return false;
			}
			logInfo("Created customer category");
			return true;
		}catch(Exception e) {
			logError("Error while creating customer category - "+e.getMessage());
			return false;
		}
	}
	/** "createEquipmentCategory" method is used to add the equipment category in the equipment tree
	 * @author pashine_a
	 * @param String - 'CategoryName'
	 * @return boolean status
	 * IF equipment category is added THEN true ELSE false
	 */	
	public boolean createEquipmentCategory(String categoryName) {
		String categoryClickedStatus = "k-item k-state-default k-last";
		try {
			if(!selectResourceCategory(EquipmentTab,"Equipment")) {
				return false;
			}
			while(true) {
				controlActions.doubleClickElement(CategoriesTab);
				categoryClickedStatus = CategoriesELementTab.getAttribute("class").toString();
				if(categoryClickedStatus.equals("k-item k-state-default k-last k-tab-on-top k-state-active")) {
					logInfo("Opened 'Category tab'");
					break;
				}
			}
			Sync();
			controlActions.clickElement(CategoriesHeadingTre);
			controlActions.clickElement(AddCategoryTre);
			controlActions.sendKeys(SetCategoryNameTxt,categoryName);
			controlActions.clickElement(SaveCategoryBtn);
			Sync();
			if(!verifyCreatedCategory(categoryName)) {
				return false;
			}
			logInfo("Created equipment category");
			return true;
		}catch(Exception e) {
			logError("Error while creating equipment category - "+e.getMessage());
			return false;
		}
	}
	/** "createItemsCategory" method is used to add the item category in the Items tree
	 * @author pashine_a
	 * @param String  - 'CategoryName'
	 * @return boolean status
	 * IF item category is added THEN true ELSE false
	 */	
	public boolean createItemsCategory(String categoryName) {	
		String categoryClickedStatus = "k-item k-state-default k-last";
		try {
			if(!selectResourceCategory(ItemsTab,"Items")) {
				return false;
			}
			while(true) {
				controlActions.doubleClickElement(CategoriesTab);
				categoryClickedStatus = CategoriesELementTab.getAttribute("class").toString();
				if(categoryClickedStatus.equals("k-item k-state-default k-last k-tab-on-top k-state-active")) {
					logInfo("Opened 'Category tab'");
					break;
				}
			}
			Sync();
			controlActions.clickElement(CategoriesHeadingTre);
			controlActions.clickElement(AddCategoryTre);
			controlActions.sendKeys(SetCategoryNameTxt,categoryName);
			controlActions.clickElement(SaveCategoryBtn);
			Sync();
			if(!verifyCreatedCategory(categoryName)) {
				return false;
			}
			logInfo("Created item category");
			return true;
		}catch(Exception e) {
			logError("Error while creating items category - "+e.getMessage());
			return false;
		}
	}
	/** "createSuppliersCategory" method is used to add the supplier category in the Suppliers tree
	 * @author pashine_a
	 * @param String - 'CategoryName'
	 * @return boolean status
	 * IF supplier category is added THEN true ELSE false
	 */	
	public boolean createSuppliersCategory(String categoryName) {
		String categoryClickedStatus = "k-item k-state-default k-last";
		try {
			if(!selectResourceCategory(SuppliersTab,"Suppliers")) {
				return false;
			}
			while(true) {
				controlActions.doubleClickElement(CategoriesTab);
				categoryClickedStatus = CategoriesELementTab.getAttribute("class").toString();
				if(categoryClickedStatus.equals("k-item k-state-default k-last k-tab-on-top k-state-active")) {
					logInfo("Opened 'Category tab'");
					break;
				}
			}
			Sync();
			controlActions.clickElement(CategoriesHeadingTre);
			controlActions.clickElement(AddCategoryTre);
			controlActions.sendKeys(SetCategoryNameTxt,categoryName);
			controlActions.clickElement(SaveCategoryBtn);
			Sync();
			if(!verifyCreatedCategory(categoryName)) {
				return false;
			}
			logInfo("Created supplier category");
			return true;
		}catch(Exception e) {
			logError("Failed to create suppliers category - "+e.getMessage());
			return false;
		}
	}
	/** "createLocationCategory" method is used to add the location category in the Locations tree
	 * @author pashine_a
	 * @param String - 'CategoryName'
	 * @return boolean status
	 * IF location category is added THEN true ELSE false
	 */	
	public boolean createLocationCategory(String categoryName) {
		String categoryClickedStatus = "k-item k-state-default k-last";
		try {
			if(!selectResourceCategory(LocationTab,"Locations")) {
				return false;
			}
			while(true) {
				controlActions.doubleClickElement(CategoriesTab);
				categoryClickedStatus = CategoriesELementTab.getAttribute("class").toString();
				if(categoryClickedStatus.equals("k-item k-state-default k-last k-tab-on-top k-state-active")) {
					logInfo("Opened 'Category tab'");
					break;
				}
			}
			Sync();
			controlActions.clickElement(CategoriesHeadingTre);
			controlActions.clickElement(AddCategoryTre);
			controlActions.sendKeys(SetCategoryNameTxt,categoryName);
			controlActions.clickElement(SaveCategoryBtn);
			Sync();
			if(!verifyCreatedCategory(categoryName)) {
				return false;
			}
			Sync();
			logInfo("Created location category");
			return true;
		}catch(Exception e) {
			logError("Error while creating locations category"+e.getMessage());
			return false;
		}
	}
	/** "verifyCreatedCategory" method is used to verify that the category is added in the tree
	 * @author pashine_a
	 * @param It takes string 'categoryName'(that is added) as parameter 
	 * @return boolean status
	 * IF Added category is verified THEN true ELSE false
	 */	
	public boolean verifyCreatedCategory(String categoryName) {
		try {
			WebElement NewCategory = controlActions.perform_GetElementByXPath(ResourceDesignerPageLocators.VERIFY_CREATED_CATEGORY, "CATEGORY_NAME", categoryName);
			if(!controlActions.isElementDisplayedOnPage(NewCategory)) {
				logError("FAILED to VERIFY created category");
				return false;
			}
			logInfo("VERIFIED created category");
			return true;
		}catch(Exception e) {
			logError("FAILED to VERIFY created category"+e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to click on create categories
	 * @author hingorani_a
	 * @param categoryDetail Where, HashMap's key is Category Type and 
	 * value is Name of Category you want to give
	 * Use class CATEGORYTYPES to set Category Type (Key)
	 * @return boolean This returns true after creating mentioned categories
	 */
	public boolean createCategory(HashMap<String,String> categoryDetail) {
		try {
			if(clickResourceDesignerMenu().error) {
				return false;
			}
			for (Map.Entry<String, String> entry : categoryDetail.entrySet()) {
				String categoryType = entry.getKey();
				String categoryName = entry.getValue();

				switch(categoryType.toUpperCase()) {
				case CATEGORYTYPES.CUSTOMERS:
					if(!createCustomersCategory(categoryName)) {
						return false;	
					}
					break;

				case CATEGORYTYPES.EQUIPMENT:
					if(!createEquipmentCategory(categoryName)) {
						return false;	
					}
					break;

				case CATEGORYTYPES.ITEMS:
					if(!createItemsCategory(categoryName)) {
						return false;	
					}
					break;

				case CATEGORYTYPES.SUPPLIERS:
					if(!createSuppliersCategory(categoryName)) {
						return false;			
					}
					break;

				case CATEGORYTYPES.LOCATION:
					if(!createLocationCategory(categoryName)) {
						return false;			
					}
					break;

				default:
					logInfo("INVALID resource category name - " + categoryType);
					return false;
				}
			}	
			logInfo("Resource(s) created successfully");
			return true;
		}
		catch(Exception e) {
			logError("FAILED to create resource(s) - " +e.getMessage());
			return false;
		}
	}

	/** "selectResourceCategory" method is used to select the resource type in Resource Designer & Resource Manager
	 * @author pashine_a
	 * @param WebElement 'category'
	 * @return boolean status
	 * IF category is selected THEN true ELSE false
	 */	
	public boolean selectResourceCategory(WebElement category, String categoryName) {
		String categorySelectedStatus;
		try {
			controlActions.refreshPage();
			Sync();
			while(true) {
				controlActions.clickElement(category);
				Sync();
				threadsleep(2000);
				categorySelectedStatus = category.getAttribute("class");
				if(categorySelectedStatus.contains("title-highlight")) {
					break;
				}
			}
			logInfo(categoryName+" category Tab is selected");
			return true;
		}catch(Exception e) {
			logError("Fail to select the "+categoryName+" category tab");
			return false;
		}
	}
	
	/**
	 * "createSubCategory" method is used to create a category and subcategory for
	 * that resource
	 * 
	 * @author ahmed_tw
	 * @param categoryType    : to select resource type for category
	 * @param categoryName    : creates category with this name
	 * @param subCategoryName : creates subcategory with this name
	 * @return boolean status : IF category and sub category is created THEN true ELSE false
	 */
	public boolean createSubCategory(String categoryType, String categoryName, String subCategoryName) {

		try {

			if(!createACategory(categoryType, categoryName)) {
				logError("Failed to create category "+categoryName);
				return false;
			}

			WebElement categoryFrmTree = controlActions.perform_GetElementByXPath(
					ResourceDesignerPageLocators.CATEGORY_FRM_TREE, "CATEGORY_NAME", categoryName);
			controlActions.clickElement(categoryFrmTree);

			WebElement addEditDelMenu = controlActions.perform_GetElementByXPath(
					ResourceDesignerPageLocators.ADD_EDIT_DEL_MENU, "CATEGORY_NAME", categoryName);
			controlActions.clickElement(addEditDelMenu);

			controlActions.clickElement(AddCategoryButton);

			controlActions.sendKeys(SetCategoryNameTxt, subCategoryName);
			controlActions.clickElement(SaveCategoryBtn);
			Sync();

			return true;

		} catch (Exception e) {
			logError("Error while creating " + categoryName + " category and " + subCategoryName + "subcategory for "
					+ categoryType + " - " + e.getMessage());
			return false;
		}
	}
	
	
	/**
	 * "createACategory" method is used to create a single category
	 * @author ahmed_tw
	 * @param categoryType    : to select resource type for category
	 * @param categoryName    : creates category with this name
	 * @return boolean status : IF A category is created THEN true ELSE false
	 */
	public boolean createACategory(String categoryType, String categoryName) {
		
		try {
			
			switch (categoryType.toUpperCase()) {
			case CATEGORYTYPES.CUSTOMERS:
				if (!createCustomersCategory(categoryName)) {
					return false;
				}
				break;

			case CATEGORYTYPES.EQUIPMENT:
				if (!createEquipmentCategory(categoryName)) {
					return false;
				}
				break;

			case CATEGORYTYPES.ITEMS:
				if (!createItemsCategory(categoryName)) {
					return false;
				}
				break;

			case CATEGORYTYPES.SUPPLIERS:
				if (!createSuppliersCategory(categoryName)) {
					return false;
				}
				break;

			case CATEGORYTYPES.LOCATION:
				if (!createLocationCategory(categoryName)) {
					return false;
				}
				break;

			default:
				logInfo("INVALID resource category name - " + categoryType);
				return false;
			}
			
			
		} catch (Exception e) {
			logError("Error while creating " + categoryName + " category" + e.getMessage());
			return false;
		}
		
		return true;
		
	}
	
	public static class CATEGORYTYPES{
		public static final String CUSTOMERS = "CUSTOMERS";
		public static final String EQUIPMENT = "EQUIPMENT";
		public static final String ITEMS = "ITEMS";
		public static final String SUPPLIERS = "SUPPLIERS";	
		public static final String LOCATION = "LOCATION";	
	}
}
