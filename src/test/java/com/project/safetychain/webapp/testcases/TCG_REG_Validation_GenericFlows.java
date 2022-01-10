package com.project.safetychain.webapp.testcases;

import java.util.Arrays;

import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.project.safetychain.webapp.pageobjects.*;
import com.project.safetychain.webapp.pageobjects.ValidationsPage.COLUMN_SETTING;
import com.project.safetychain.webapp.pageobjects.ValidationsPage.VLDTN_FIELDS;
import com.project.safetychain.webapp.pageobjects.ValidationsPage.VLDTN_PAGES;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.utilities.CommonMethods;
import com.project.utilities.CommonMethods.SORTING;
import com.project.utilities.DateTime.ComparisonValue;
import com.project.utilities.ControlActions;
import com.project.utilities.DateTime;

public class TCG_REG_Validation_GenericFlows extends TestBase{

	ControlActions controlActions;
	HomePage hp;
	LoginPage lp;
	public final static int COLUMN_COUNT = 5;
	public final static int PAGE_SIZE_COUNT = 3;
	public final static int VLDTN_TYPE_COUNT= 7;
	public static String dateFormat = "MM/dd/yyyy HH:mm";

	@BeforeClass(alwaysRun = true)
	public void groupInit() throws InterruptedException {
		
		driver = launchbrowser();
		controlActions = new ControlActions(driver);
		controlActions.getUrl(applicationUrl);
		hp = new HomePage(driver);
		lp = new LoginPage(driver);
		
		hp = lp.performLogin(adminUsername, adminPassword);
		if(hp.error) {
			TCGFailureMsg = "Could NOT login to application";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
		
	}

	@Test(description="Validations || Verify the \"Validations\" screen features")
	public void TestCase_34016() {
		
		ValidationsPage vp = hp.clickValidationsMenu();
		TestValidation.Equals(vp.error, 
							  false, 
							  "CLICKED on Validations menu", 
							  "Could NOT click on Validations menu"); 
		
		boolean verifyBreadcrumb = vp.VldtnBreadcrumb.getText().contains("Manage Validations");
		TestValidation.IsTrue(verifyBreadcrumb, 
							  "VERIFIED the breadcrumb has value as 'Manage Validations'", 
							  "Failed to verify that the breadcrumb has value as 'Manage Validations'");
		
		boolean verifyTitle = vp.VldtnTitle.getText().equalsIgnoreCase("Validations");
		TestValidation.IsTrue(verifyTitle, 
							  "VERIFIED the title has value as 'Validations'", 
							  "Failed to verify that the title has value as 'Validations'");
		
		boolean verifyLocn = vp.VldtnLocnFilter.getText().contains("ALL");
		TestValidation.IsTrue(verifyLocn, 
							  "VERIFIED the if no default location is set, location is set to 'ALL'", 
							  "Failed to verify the location that is set");
		
		int columnCount = vp.verifyColumnHeaders();
		TestValidation.IsTrue(columnCount==COLUMN_COUNT, 
							  "VERIFIED all columns are displayed on Validation grid", 
							  "Failed to verify all columns displayed on Validation grid");
		
		boolean newVldtnBtnDisplayed = controlActions.isElementDisplayed(vp.NewVldtnBtn);
		TestValidation.IsTrue(newVldtnBtnDisplayed, 
							  "VERIFIED 'New Validation' button is displayed", 
							  "Failed to verify 'New Validation' button is displayed");
		
	}
	
	@Test(description="Validations || Verify pagination should work as per the limit set for the page")
	public void TestCase_35307() {
		
		ValidationsPage vp = hp.clickValidationsMenu();
		TestValidation.Equals(vp.error, 
							  false, 
							  "CLICKED on Validations menu", 
							  "Could NOT click on Validations menu"); 
		
		int listOfVldtn1 = vp.VldtnGridNameLst.size();
		TestValidation.IsTrue(listOfVldtn1<=100, 
							  "VERIFIED by default, at max 100 validations are displayed on Validation grid", 
							  "Failed to verify that at max 100 validations are displayed on Validation grid");
		
		int pageSizeCount = vp.verifyPaginationSizes();
		TestValidation.IsTrue(pageSizeCount==PAGE_SIZE_COUNT, 
							  "VERIFIED all pagination sizes are displayed on Validation grid", 
							  "Failed to verify all pagination sizes are displayed on Validation grid");
		
		boolean previousBtnStatus = vp.PreviousPagintnLnk.getAttribute("className").contains("disabled");
		TestValidation.IsTrue(previousBtnStatus, 
	  			  			  "VERIFIED Previous button is disabled on Validation grid", 
	  			  			  "Failed to verify Previous button is disabled on Validation grid");
		
		boolean firstBtnStatus = vp.FirstPagintnLnk.getAttribute("className").contains("disabled");
		TestValidation.IsTrue(firstBtnStatus, 
	  			  			  "VERIFIED First button is disabled on Validation grid", 
	  			  			  "Failed to verify First button is disabled on Validation grid");
		
		boolean nextBtnStatus1 = vp.NextPagintnLnk.getAttribute("className").contains("disabled");
		if(!nextBtnStatus1) {
			TestValidation.IsFalse(nextBtnStatus1, 
					  			  "VERIFIED Next button is enabled on Validation grid", 
					  			  "Failed to verify Next button is enabled on Validation grid");
			
			boolean clickNextBtn = vp.clickNextPaginationButton();
			TestValidation.IsTrue(clickNextBtn, 
								  "CLICKED on Next pagination button", 
		  			  			  "Failed to click on Next pagination button");
			
			int afterListOfVldtn = vp.VldtnGridNameLst.size();
			TestValidation.IsTrue(afterListOfVldtn<=100, 
								  "VERIFIED " + afterListOfVldtn + " validations are displayed on Validation grid", 
								  "Failed to verify that validations are displayed on Validation grid");
			
			boolean clickPreviousBtn = vp.clickPreviousPaginationButton();
			TestValidation.IsTrue(clickPreviousBtn, 
								  "CLICKED on Previous pagination button", 
		  			  			  "Failed to click on Previous pagination button");
			
		}
		else {
			TestValidation.IsTrue(nextBtnStatus1, 
		  			  			  "VERIFIED Next button is disabled as " + listOfVldtn1 + " Validations are displayed on grid", 
		  			  			  "Failed to verify Next button is disabled on Validation grid");
		}
		
		boolean setPageTo200 = controlActions.selectDropdown(VLDTN_PAGES.SIZE_200,vp.PageSizeDdl);
		hp.Sync();
		TestValidation.IsTrue(setPageTo200, 
	  			  			  "Validation grid's page size SET to 200", 
	  			  			  "Failed to set Validation grid's page size to 200");
		
		int listOfVldtn2 = vp.VldtnGridNameLst.size();
		TestValidation.IsTrue(listOfVldtn2<=200, 
							  "VERIFIED by default, at max 200 validations are displayed on Validation grid", 
							  "Failed to verify that at max 200 validations are displayed on Validation grid");
		
		boolean nextBtnStatus2 = vp.NextPagintnLnk.getAttribute("className").contains("disabled");
		if(!nextBtnStatus2) {
			TestValidation.IsFalse(nextBtnStatus2, 
					  			  "VERIFIED Next button is enabled on Validation grid", 
					  			  "Failed to verify Next button is enabled on Validation grid");
			
			boolean clickNextBtn = vp.clickNextPaginationButton();
			TestValidation.IsTrue(clickNextBtn, 
								  "CLICKED on Next pagination button", 
		  			  			  "Failed to click on Next pagination button");
			
			int afterListOfVldtn = vp.VldtnGridNameLst.size();
			TestValidation.IsTrue(afterListOfVldtn<=200, 
								  "VERIFIED " + afterListOfVldtn + " validations are displayed on Validation grid", 
								  "Failed to verify that validations are displayed on Validation grid");
			
		}
		else {
			TestValidation.IsTrue(nextBtnStatus2, 
		  			  			  "VERIFIED Next button is disabled as " + listOfVldtn2 + " Validations are displayed on grid", 
		  			  			  "Failed to verify Next button is disabled on Validation grid");
		}
	}
	
	@Test(description="Validations || Verify ascending and descending sorting works fine for validation")
	public void TestCase_35308() {
		
		ValidationsPage vp = hp.clickValidationsMenu();
		TestValidation.Equals(vp.error, 
							  false, 
							  "CLICKED on Validations menu", 
							  "Could NOT click on Validations menu"); 
		
		boolean sortNameAsc = vp.openAndApplySettingsForColumn(VLDTN_FIELDS.NAME, COLUMN_SETTING.SORTASCENDING, null);
		TestValidation.IsTrue(sortNameAsc, 
							  "For Name column, APPLIED the column setting as " + COLUMN_SETTING.SORTASCENDING, 
							  "Failed to apply the column setting as " + COLUMN_SETTING.SORTASCENDING + " for Name column");
		
		boolean verifySortNameAsc = CommonMethods.verifySortingForColumnHavingSpecialChars(
						vp.getListOfElementsForVldtn(VLDTN_FIELDS.NAME), false);
		TestValidation.IsTrue(verifySortNameAsc, 
							  "VERIFIED values of the column Name are sorted in Ascending", 
							  "Failed to verify that values of the column Name are sorted in Ascending");
		
		boolean sortNameDsc = vp.openAndApplySettingsForColumn(VLDTN_FIELDS.NAME, COLUMN_SETTING.SORTDESCENDING, null);
		TestValidation.IsTrue(sortNameDsc, 
							  "For Name column, APPLIED the column setting as " + COLUMN_SETTING.SORTDESCENDING, 
							  "Failed to apply the column setting as " + COLUMN_SETTING.SORTDESCENDING + " for Name column");
		
		boolean verifySortNameDsc = CommonMethods.verifySortingForColumnHavingSpecialChars(
										vp.getListOfElementsForVldtn(VLDTN_FIELDS.NAME), true);
		TestValidation.IsTrue(verifySortNameDsc, 
							  "VERIFIED values of the column Name are sorted in Descending", 
							  "Failed to verify that values of the column Name are sorted in Descending");
		
		boolean sortStatusAsc = vp.openAndApplySettingsForColumn(VLDTN_FIELDS.STATUS, COLUMN_SETTING.SORTASCENDING, null);
		TestValidation.IsTrue(sortStatusAsc, 
							  "For Status column, APPLIED the column setting as " + COLUMN_SETTING.SORTASCENDING, 
							  "Failed to apply the column setting as " + COLUMN_SETTING.SORTASCENDING + " for Status column");
		
		String verifySortStatusAsc = CommonMethods.verifySortingForColumn(vp.getListOfElementsForVldtn(VLDTN_FIELDS.STATUS));
		TestValidation.IsTrue(verifySortStatusAsc.equals(SORTING.ASC) || verifySortStatusAsc.equals(SORTING.EQL), 
							  "VERIFIED values of the column Status are sorted in Ascending", 
							  "Failed to verify that values of the column Status are sorted in Ascending");
		
		boolean sortStatusDsc = vp.openAndApplySettingsForColumn(VLDTN_FIELDS.STATUS, COLUMN_SETTING.SORTDESCENDING, null);
		TestValidation.IsTrue(sortStatusDsc, 
							  "For Status column, APPLIED the column setting as " + COLUMN_SETTING.SORTDESCENDING, 
							  "Failed to apply the column setting as " + COLUMN_SETTING.SORTDESCENDING + " for Status column");
		
		String verifySortStatusDsc = CommonMethods.verifySortingForColumn(vp.getListOfElementsForVldtn(VLDTN_FIELDS.STATUS));
		TestValidation.IsTrue(verifySortStatusDsc.equals(SORTING.DSC) || verifySortStatusDsc.equals(SORTING.EQL), 
							  "VERIFIED values of the column Status are sorted in Descending", 
							  "Failed to verify that values of the column Status are sorted in Descending");
		
		boolean sortCreatedAsc = vp.openAndApplySettingsForColumn(VLDTN_FIELDS.CREATED, COLUMN_SETTING.SORTASCENDING, null);
		TestValidation.IsTrue(sortCreatedAsc, 
							  "For Created column, APPLIED the column setting as " + COLUMN_SETTING.SORTASCENDING, 
							  "Failed to apply the column setting as " + COLUMN_SETTING.SORTASCENDING + " for Created column");
		
		DateTime dt = new DateTime();
		ComparisonValue verifySortCreatedOnAsc = dt.verifySortingForColumn(vp.getListOfElementsForVldtn(VLDTN_FIELDS.CREATED), 
				dateFormat, false, true, false);
		TestValidation.IsTrue(verifySortCreatedOnAsc.equals(ComparisonValue.LESSER) || verifySortCreatedOnAsc.equals(ComparisonValue.EQUALS),
							  "VERIFIED values of the column Created are sorted in Ascending", 
							  "Failed to verify that values of the column Created are sorted in Ascending");
		
		boolean sortCreatedDsc = vp.openAndApplySettingsForColumn(VLDTN_FIELDS.CREATED, COLUMN_SETTING.SORTDESCENDING, null);
		TestValidation.IsTrue(sortCreatedDsc, 
							  "For Created column, APPLIED the column setting as " + COLUMN_SETTING.SORTDESCENDING, 
							  "Failed to apply the column setting as " + COLUMN_SETTING.SORTDESCENDING + " for Created column");
		
		ComparisonValue verifySortCreatedOnDsc = dt.verifySortingForColumn(vp.getListOfElementsForVldtn(VLDTN_FIELDS.CREATED), 
				dateFormat, false, true, false);
		TestValidation.IsTrue(verifySortCreatedOnDsc.equals(ComparisonValue.GREATER) || verifySortCreatedOnDsc.equals(ComparisonValue.EQUALS),
							  "VERIFIED values of the column Created are sorted in Descending", 
							  "Failed to verify that values of the column Created are sorted in Descending");
		
		boolean sortLastModifdAsc = vp.openAndApplySettingsForColumn(VLDTN_FIELDS.LAST_MODIFIED, COLUMN_SETTING.SORTASCENDING, null);
		TestValidation.IsTrue(sortLastModifdAsc, 
							  "For Last Modified column, APPLIED the column setting as " + COLUMN_SETTING.SORTASCENDING, 
							  "Failed to apply the column setting as " + COLUMN_SETTING.SORTASCENDING + " for Last Modified column");
		
		ComparisonValue verifySortLastModAsc = dt.verifySortingForColumn(vp.getListOfElementsForVldtn(VLDTN_FIELDS.LAST_MODIFIED), 
				dateFormat, false, true, false);
		TestValidation.IsTrue(verifySortLastModAsc.equals(ComparisonValue.LESSER) || verifySortLastModAsc.equals(ComparisonValue.EQUALS),
							  "VERIFIED values of the column Last Modified are sorted in Ascending", 
							  "Failed to verify that values of the column Last Modified are sorted in Ascending");
		
		boolean sortLastModifdDsc = vp.openAndApplySettingsForColumn(VLDTN_FIELDS.LAST_MODIFIED, COLUMN_SETTING.SORTDESCENDING, null);
		TestValidation.IsTrue(sortLastModifdDsc, 
							  "For Last Modified column, APPLIED the column setting as " + COLUMN_SETTING.SORTDESCENDING, 
							  "Failed to apply the column setting as " + COLUMN_SETTING.SORTDESCENDING + " for Last Modified column");
		
		ComparisonValue verifySortLastModDsc = dt.verifySortingForColumn(vp.getListOfElementsForVldtn(VLDTN_FIELDS.LAST_MODIFIED), 
				dateFormat, false, true, false);
		TestValidation.IsTrue(verifySortLastModDsc.equals(ComparisonValue.GREATER) || verifySortLastModDsc.equals(ComparisonValue.EQUALS),
							  "VERIFIED values of the column Last Modified are sorted in Descending", 
							  "Failed to verify that values of the column Last Modified are sorted in Descending");

	}
	
	@Test(description="Validations || Verify columns can be added as well as removed from the validation grid")
	public void TestCase_35309() {
		try {
			ValidationsPage vp = hp.clickValidationsMenu();
			TestValidation.Equals(vp.error, 
								  false, 
								  "CLICKED on Validations menu", 
								  "Could NOT click on Validations menu"); 
			
			// "Name-Check|Status-Uncheck"
			String columnSetting1 = VLDTN_FIELDS.NAME + "-" + COLUMN_SETTING.UNCHECK;
			boolean applySettings1 = vp.openAndApplySettingsForColumn(VLDTN_FIELDS.NAME, COLUMN_SETTING.COLUMNS, columnSetting1);
			TestValidation.IsTrue(applySettings1, 
								  "DESELECTED Name column from Validations grid", 
								  "Failed to deselect Name column from Validations grid");
			
			int columnCount1 = vp.verifyColumnHeaders();
			TestValidation.IsTrue(columnCount1==4, 
								  "VERIFIED 4 columns are displayed on Validation grid", 
								  "Failed to verify 4 columns displayed on Validation grid");
			
			int verifyColumnCount1 = vp.verifyColumnHeaders(Arrays.asList(VLDTN_FIELDS.STATUS,VLDTN_FIELDS.CREATED, 
					VLDTN_FIELDS.LAST_MODIFIED, VLDTN_FIELDS.IDENTIFIERS));
			TestValidation.IsTrue(verifyColumnCount1==4, 
								  "VERIFIED all columns except Name are displayed on Validation grid", 
								  "Failed to verify all columns except Name displayed on Validation grid");
			
			String columnSetting2 = VLDTN_FIELDS.STATUS + "-" + COLUMN_SETTING.UNCHECK + "|" 
									+ VLDTN_FIELDS.CREATED + "-" + COLUMN_SETTING.UNCHECK + "|" 
									+ VLDTN_FIELDS.LAST_MODIFIED + "-" + COLUMN_SETTING.UNCHECK;
			boolean applySettings2 = vp.openAndApplySettingsForColumn(VLDTN_FIELDS.STATUS, COLUMN_SETTING.COLUMNS, columnSetting2);
			TestValidation.IsTrue(applySettings2, 
								  "DESELECTED all columns except Identifiers from Validations grid", 
								  "Failed to deselect all columns except Identifiers from Validations grid");
			
			int columnCount2 = vp.verifyColumnHeaders();
			TestValidation.IsTrue(columnCount2==1, 
								  "VERIFIED 1 column is displayed on Validation grid", 
								  "Failed to verify 1 column is displayed on Validation grid");
			
			int verifyColumnCount2 = vp.verifyColumnHeaders(Arrays.asList(VLDTN_FIELDS.IDENTIFIERS));
			TestValidation.IsTrue(verifyColumnCount2==1, 
								  "VERIFIED only Identifiers column is displayed on Validation grid", 
								  "Failed to verify only Identifiers column is displayed on Validation grid");
			
			String columnSetting3 = VLDTN_FIELDS.IDENTIFIERS + "-" + COLUMN_SETTING.UNCHECK;
			boolean applySettings3 = vp.openAndApplySettingsForColumn(VLDTN_FIELDS.IDENTIFIERS, COLUMN_SETTING.COLUMNS, columnSetting3);
			TestValidation.IsFalse(applySettings3, 
								  "VERIFIED that we cannot deselect columns on Validations grid", 
								  "Failed to verify that we cannot deselect columns on Validations grid");

			hp.SCLogo.click();
			String columnSetting4 = VLDTN_FIELDS.STATUS + "-" + COLUMN_SETTING.CHECK + "|" 
					+ VLDTN_FIELDS.CREATED + "-" + COLUMN_SETTING.CHECK + "|" 
					+ VLDTN_FIELDS.LAST_MODIFIED + "-" + COLUMN_SETTING.CHECK + "|" 
					+ VLDTN_FIELDS.NAME + "-" + COLUMN_SETTING.CHECK;
			boolean applySettings4 = vp.openAndApplySettingsForColumn(VLDTN_FIELDS.IDENTIFIERS, COLUMN_SETTING.COLUMNS, columnSetting4);
			TestValidation.IsTrue(applySettings4, 
							  	  "SELECTED all columns to display for Validations grid", 
							  	  "Failed to select all columns to display for Validations grid");
			
			int columnCount4 = vp.verifyColumnHeaders();
			TestValidation.IsTrue(columnCount4==COLUMN_COUNT, 
							  	  "VERIFIED 5 columns are displayed on Validation grid", 
							  	  "Failed to verify 5 columns are displayed on Validation grid");
			
			int verifyColumnCount4 = vp.verifyColumnHeaders(Arrays.asList(VLDTN_FIELDS.NAME,VLDTN_FIELDS.STATUS,VLDTN_FIELDS.CREATED, 
					VLDTN_FIELDS.LAST_MODIFIED, VLDTN_FIELDS.IDENTIFIERS));
			TestValidation.IsTrue(verifyColumnCount4==COLUMN_COUNT, 
								  "VERIFIED all columns are displayed on Validation grid", 
								  "Failed to verify all columns are displayed on Validation grid");
		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could refresh Validations page";
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}
		}
	}
	
	@Test(description="Validations || Verify details on adding new validation")
	public void TestCase_34240() {
		
		ValidationsPage vp = hp.clickValidationsMenu();
		TestValidation.Equals(vp.error, 
							  false, 
							  "CLICKED on Validations menu", 
							  "Could NOT click on Validations menu"); 
		
		AddEditValidationsPage aevp = vp.createNewValidation();
		TestValidation.Equals(aevp.error, 
							  false, 
							  "CLICKED on New Validation button", 
							  "Could NOT click on New Validation button");
		
		boolean verifyTitle = aevp.VldtnTitle.getText().contains("Add New Validation");
		TestValidation.IsTrue(verifyTitle, 
							  "VERIFIED the title has value as 'Add New Validation'", 
							  "Failed to verify that the title has value as 'Add New Validation'");
		
		boolean verifySubSec1 = aevp.VldtnSubdetsTitles.get(0).getText().contains("Details");
		boolean verifySubSec2 = aevp.VldtnSubdetsTitles.get(1).getText().contains("Forms");
		boolean verifySubSec3 = aevp.VldtnSubdetsTitles.get(2).getText().contains("Date Range for Records");
		boolean verifySubSec4 = aevp.VldtnSubdetsTitles.get(3).getText().contains("Filters");
		TestValidation.IsTrue(verifySubSec1 && verifySubSec2 && verifySubSec3 && verifySubSec4, 
							  "VERIFIED all the sub detail's title like 'Details, Forms, Date Range for Records and Filters'", 
							  "Failed to verify all the sub detail's title");
		
		boolean verifyRqdField1 = aevp.ReqdVldtnLbl.get(0).getText().contains("NAME");
		boolean verifyRqdField2 = aevp.ReqdVldtnLbl.get(1).getText().contains("TYPE");
		boolean verifyRqdField3 = aevp.ReqdVldtnLbl.get(2).getText().contains("LOCATION");
		boolean verifyRqdField4 = aevp.ReqdVldtnLbl.get(3).getText().contains("RESOURCE");
		boolean verifyRqdField5 = aevp.ReqdVldtnLbl.get(4).getText().contains("START DATE & TIME");
		boolean verifyRqdField6 = aevp.ReqdVldtnLbl.get(5).getText().contains("END DATE & TIME");
		TestValidation.IsTrue(verifyRqdField1 && verifyRqdField2 && verifyRqdField3 && verifyRqdField4
							  && verifyRqdField5 && verifyRqdField6, 
							  "VERIFIED all the required Fields like 'NAME, TYPE, LOCATION', etc", 
							  "Failed to verify all the required Fields");
		
		boolean verifyDescLbl = controlActions.isElementDisplayed(aevp.VldtnDescptnLbl);
		TestValidation.IsTrue(verifyDescLbl, 
							  "VERIFIED Description optional field is displayed", 
							  "Failed to verify whether Description optional field is displayed or not");
		
		boolean verifyFiltrField1 = aevp.VldtnFilterLbl.get(0).getText().contains("IDENTIFIER FIELD NAME");
		boolean verifyFiltrField2 = aevp.VldtnFilterLbl.get(1).getText().contains("VALUE");
		boolean verifyFiltrField3 = aevp.VldtnFilterLbl.get(2).getText().contains("IDENTIFIER FIELD NAME");
		boolean verifyFiltrField4 = aevp.VldtnFilterLbl.get(3).getText().contains("VALUE");
		TestValidation.IsTrue(verifyFiltrField1 && verifyFiltrField2 && verifyFiltrField3 && verifyFiltrField4,
							  "VERIFIED 2 optional Filters having Name and Value fields are displayed", 
							  "Failed to verify 2 optional Filters having Name and Value fields are displayed");
		
		boolean clearBtn = aevp.ClearVldtnBtn.getAttribute("className").contains("disabled");
		TestValidation.IsTrue(clearBtn, 
							  "VERIFIED Clear button is disabled", 
							  "Failed to verify whether Clear button is disabled or not");
		
		boolean addBtn = aevp.AddVldtnBtn.getAttribute("className").contains("disabled");
		TestValidation.IsTrue(addBtn, 
							  "VERIFIED Add button is disabled", 
							  "Failed to verify whether Add button is disabled or not");
		
	}
	
	@Test(description="Validations || Verify the validation types")
	public void TestCase_34281() {
		
		ValidationsPage vp = hp.clickValidationsMenu();
		TestValidation.Equals(vp.error, 
							  false, 
							  "CLICKED on Validations menu", 
							  "Could NOT click on Validations menu"); 
		
		AddEditValidationsPage aevp = vp.createNewValidation();
		TestValidation.Equals(aevp.error, 
							  false, 
							  "CLICKED on New Validation button", 
							  "Could NOT click on New Validation button");
	
		int optionCount = aevp.verifyValidationTypeOptions();
		TestValidation.IsTrue(optionCount==VLDTN_TYPE_COUNT, 
				  			  "VERIFIED 7 displayed Validation type options", 
				         	  "Failed to verify whether 7 Validation type options are displayed or not");
		
	}
	
	@AfterClass(alwaysRun = true)
	public void closeBrowser() throws InterruptedException {
		driver.close();
	}

}
