package com.project.safetychain.webapp.testcases;

import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.project.safetychain.webapp.pageobjects.HomePage;
import com.project.safetychain.webapp.pageobjects.LoginPage;
import com.project.safetychain.webapp.pageobjects.DataImportExportPage.COLUMN_SETTING;
import com.project.safetychain.webapp.pageobjects.DataImportExportPage.ColumnNames;
import com.project.safetychain.webapp.pageobjects.DataImportExportPage.ExtractType;
import com.project.safetychain.webapp.pageobjects.DataImportExportPage;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.utilities.CommonMethods;
import com.project.utilities.ControlActions;
import com.project.utilities.DateTime;
import com.project.utilities.CommonMethods.SORTING;
import com.project.utilities.DateTime.ComparisonValue;

public class TCG_REG_DPT_GenericFlows extends TestBase{
	ControlActions controlActions;
	HomePage hp;
	DateTime dt = new DateTime();
	
	@BeforeClass(alwaysRun = true)
	public void groupInit() throws Exception {

		driver = launchbrowser();
		controlActions = new ControlActions(driver);
		controlActions.getUrl(applicationUrl);
		hp = new HomePage(driver);

		LoginPage lp = new LoginPage(driver);
		hp = lp.performLogin(adminUsername, adminPassword);
		if(hp.error) {
			TCGFailureMsg = "Could NOT login to application";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
				
	}

	@Test(description = "DPT || Verify if no data selected while extraction of \"Form Compliance\""
			+ " and \"Records\" then warning message \"Please select all required data\" will be shown.")
	public void TestCase_38308() {
		
		try {
			HomePage hp = new HomePage(driver);			
			DataImportExportPage diep = hp.clickDataImportExportMenu();
			
			boolean exportBtn = diep.selectExportTab();
			TestValidation.IsTrue(exportBtn, 
								  "CLICKED on Export tab", 
								  "Failed to Click on Export tab");
			
			boolean openPopup = diep.openExtractDataPopUp();
			TestValidation.IsTrue(openPopup, 
								  "OPENED Export Data popup", 
								  "Failed to Open Export Data popup");
			
			boolean setType1 = diep.selectExtractType(ExtractType.FORM_COMPLIANCE);
			TestValidation.IsTrue(setType1, 
								  "Extract type SET to " + ExtractType.FORM_COMPLIANCE, 
								  "Failed to Set Extract type to " + ExtractType.FORM_COMPLIANCE);
			
			boolean clickExtract1 = diep.clickDataExtract();
			TestValidation.IsTrue(clickExtract1, 
								  "CLICKED on Data Extract button on popup", 
								  "Failed to Click on Data Extract button on popup");
			
			String expectedPopupMsg = "Please select all required data";
			boolean verifyMsg1 = diep.PopupModalMsg.getText().equalsIgnoreCase(expectedPopupMsg);
			TestValidation.IsTrue(verifyMsg1, 
								  "VERIFIED the message displayed is " + expectedPopupMsg, 
								  "Failed to Verify the displayed message");
			
			boolean closePopup1 = diep.closeModalPopup();
			TestValidation.IsTrue(closePopup1, 
								  "CLOSED Warning popup modal", 
								  "Failed to Close Warning popup modal");
			
			boolean setType2 = diep.selectExtractType(ExtractType.RECORDS);
			TestValidation.IsTrue(setType2, 
								  "Extract type SET to " + ExtractType.RECORDS, 
								  "Failed to Set Extract type to " + ExtractType.RECORDS);
			
			boolean clickExtract2 = diep.clickDataExtract();
			TestValidation.IsTrue(clickExtract2, 
								  "CLICKED on Data Extract button on popup", 
								  "Failed to Click on Data Extract button on popup");
			
			boolean verifyMsg2 = diep.PopupModalMsg.getText().equalsIgnoreCase(expectedPopupMsg);
			TestValidation.IsTrue(verifyMsg2, 
								  "VERIFIED the message displayed is " + expectedPopupMsg, 
								  "Failed to Verify the displayed message");
			
			boolean closePopup2 = diep.closeModalPopup();
			TestValidation.IsTrue(closePopup2, 
								  "CLOSED Warning popup modal", 
								  "Failed to Close Warning popup modal");
			
		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				TestValidation.Fail(TCGFailureMsg);
			}
		}
	}
	
	@Test(description = "DPT || Verify while extracting Resources/Users/SupplierUsers/WorkGroup/Roles/FormDefinition/ControlLimits"
			+ " if no selection is made then a warning message \"All data will be extracted. Do you want to proceed?\" will be shown.")
	public void TestCase_38309() {
		
		try {
			HomePage hp = new HomePage(driver);			
			DataImportExportPage diep = hp.clickDataImportExportMenu();
			
			boolean exportBtn = diep.selectExportTab();
			TestValidation.IsTrue(exportBtn, 
								  "CLICKED on Export tab", 
								  "Failed to Click on Export tab");
			
			boolean openPopup = diep.openExtractDataPopUp();
			TestValidation.IsTrue(openPopup, 
								  "OPENED Export Data popup", 
								  "Failed to Open Export Data popup");
			
			boolean setType1 = diep.selectExtractType(ExtractType.RESOURCE_CUSTOMERS);
			TestValidation.IsTrue(setType1, 
								  "Extract type SET to " + ExtractType.RESOURCE_CUSTOMERS, 
								  "Failed to Set Extract type to " + ExtractType.RESOURCE_CUSTOMERS);
			
			boolean clickExtract1 = diep.clickDataExtract();
			TestValidation.IsTrue(clickExtract1, 
								  "CLICKED on Data Extract button on popup", 
								  "Failed to Click on Data Extract button on popup");
			
			String expectedPopupMsg = "All data will be extracted. Do you want to proceed?";
			boolean verifyMsg1 = diep.PopupModalMsg.getText().equalsIgnoreCase(expectedPopupMsg);
			TestValidation.IsTrue(verifyMsg1, 
								  "VERIFIED the message displayed is " + expectedPopupMsg, 
								  "Failed to Verify the displayed message");
			
			boolean closePopup1 = diep.closeModalPopup();
			TestValidation.IsTrue(closePopup1, 
								  "CLOSED Warning popup modal", 
								  "Failed to Close Warning popup modal");
			
			boolean setType2 = diep.selectExtractType(ExtractType.RESOURCE_EQUIPMENTS);
			TestValidation.IsTrue(setType2, 
								  "Extract type SET to " + ExtractType.RESOURCE_EQUIPMENTS, 
								  "Failed to Set Extract type to " + ExtractType.RESOURCE_EQUIPMENTS);
			
			boolean clickExtract2 = diep.clickDataExtract();
			TestValidation.IsTrue(clickExtract2, 
								  "CLICKED on Data Extract button on popup", 
								  "Failed to Click on Data Extract button on popup");
			
			boolean verifyMsg2 = diep.PopupModalMsg.getText().equalsIgnoreCase(expectedPopupMsg);
			TestValidation.IsTrue(verifyMsg2, 
								  "VERIFIED the message displayed is " + expectedPopupMsg, 
								  "Failed to Verify the displayed message");
			
			boolean closePopup2 = diep.closeModalPopup();
			TestValidation.IsTrue(closePopup2, 
								  "CLOSED Warning popup modal", 
								  "Failed to Close Warning popup modal");
			
			boolean setType3 = diep.selectExtractType(ExtractType.RESOURCE_ITEMS);
			TestValidation.IsTrue(setType3, 
								  "Extract type SET to " + ExtractType.RESOURCE_ITEMS, 
								  "Failed to Set Extract type to " + ExtractType.RESOURCE_ITEMS);
			
			boolean clickExtract3 = diep.clickDataExtract();
			TestValidation.IsTrue(clickExtract3, 
								  "CLICKED on Data Extract button on popup", 
								  "Failed to Click on Data Extract button on popup");
			
			boolean verifyMsg3 = diep.PopupModalMsg.getText().equalsIgnoreCase(expectedPopupMsg);
			TestValidation.IsTrue(verifyMsg3, 
								  "VERIFIED the message displayed is " + expectedPopupMsg, 
								  "Failed to Verify the displayed message");
			
			boolean closePopup3 = diep.closeModalPopup();
			TestValidation.IsTrue(closePopup3, 
								  "CLOSED Warning popup modal", 
								  "Failed to Close Warning popup modal");
			
			boolean setType4 = diep.selectExtractType(ExtractType.RESOURCE_SUPPLIERS);
			TestValidation.IsTrue(setType4, 
								  "Extract type SET to " + ExtractType.RESOURCE_SUPPLIERS, 
								  "Failed to Set Extract type to " + ExtractType.RESOURCE_SUPPLIERS);
			
			boolean clickExtract4 = diep.clickDataExtract();
			TestValidation.IsTrue(clickExtract4, 
								  "CLICKED on Data Extract button on popup", 
								  "Failed to Click on Data Extract button on popup");
			
			boolean verifyMsg4 = diep.PopupModalMsg.getText().equalsIgnoreCase(expectedPopupMsg);
			TestValidation.IsTrue(verifyMsg4, 
								  "VERIFIED the message displayed is " + expectedPopupMsg, 
								  "Failed to Verify the displayed message");
			
			boolean closePopup4 = diep.closeModalPopup();
			TestValidation.IsTrue(closePopup4, 
								  "CLOSED Warning popup modal", 
								  "Failed to Close Warning popup modal");
			
			boolean setType5 = diep.selectExtractType(ExtractType.USERS);
			TestValidation.IsTrue(setType5, 
								  "Extract type SET to " + ExtractType.USERS, 
								  "Failed to Set Extract type to " + ExtractType.USERS);
			
			boolean clickExtract5 = diep.clickDataExtract();
			TestValidation.IsTrue(clickExtract5, 
								  "CLICKED on Data Extract button on popup", 
								  "Failed to Click on Data Extract button on popup");
			
			boolean verifyMsg5 = diep.PopupModalMsg.getText().equalsIgnoreCase(expectedPopupMsg);
			TestValidation.IsTrue(verifyMsg5, 
								  "VERIFIED the message displayed is " + expectedPopupMsg, 
								  "Failed to Verify the displayed message");
			
			boolean closePopup5 = diep.closeModalPopup();
			TestValidation.IsTrue(closePopup5, 
								  "CLOSED Warning popup modal", 
								  "Failed to Close Warning popup modal");
			
			boolean setType6 = diep.selectExtractType(ExtractType.SUPPLIER_USERS);
			TestValidation.IsTrue(setType6, 
								  "Extract type SET to " + ExtractType.SUPPLIER_USERS, 
								  "Failed to Set Extract type to " + ExtractType.SUPPLIER_USERS);
			
			boolean clickExtract6 = diep.clickDataExtract();
			TestValidation.IsTrue(clickExtract6, 
								  "CLICKED on Data Extract button on popup", 
								  "Failed to Click on Data Extract button on popup");
			
			boolean verifyMsg6 = diep.PopupModalMsg.getText().equalsIgnoreCase(expectedPopupMsg);
			TestValidation.IsTrue(verifyMsg6, 
								  "VERIFIED the message displayed is " + expectedPopupMsg, 
								  "Failed to Verify the displayed message");
			
			boolean closePopup6 = diep.closeModalPopup();
			TestValidation.IsTrue(closePopup6	, 
								  "CLOSED Warning popup modal", 
								  "Failed to Close Warning popup modal");
			
			boolean setType7 = diep.selectExtractType(ExtractType.WORKGROUPS);
			TestValidation.IsTrue(setType7, 
								  "Extract type SET to " + ExtractType.WORKGROUPS, 
								  "Failed to Set Extract type to " + ExtractType.WORKGROUPS);
			
			boolean clickExtract7= diep.clickDataExtract();
			TestValidation.IsTrue(clickExtract7, 
								  "CLICKED on Data Extract button on popup", 
								  "Failed to Click on Data Extract button on popup");
			
			boolean verifyMsg7 = diep.PopupModalMsg.getText().equalsIgnoreCase(expectedPopupMsg);
			TestValidation.IsTrue(verifyMsg7, 
								  "VERIFIED the message displayed is " + expectedPopupMsg, 
								  "Failed to Verify the displayed message");
			
			boolean closePopup7 = diep.closeModalPopup();
			TestValidation.IsTrue(closePopup7, 
								  "CLOSED Warning popup modal", 
								  "Failed to Close Warning popup modal");
			
			boolean setType8 = diep.selectExtractType(ExtractType.LOCATIONS);
			TestValidation.IsTrue(setType8, 
								  "Extract type SET to " + ExtractType.LOCATIONS, 
								  "Failed to Set Extract type to " + ExtractType.LOCATIONS);
			
			boolean clickExtract8 = diep.clickDataExtract();
			TestValidation.IsTrue(clickExtract8, 
								  "CLICKED on Data Extract button on popup", 
								  "Failed to Click on Data Extract button on popup");
			
			boolean verifyMsg8 = diep.PopupModalMsg.getText().equalsIgnoreCase(expectedPopupMsg);
			TestValidation.IsTrue(verifyMsg8, 
								  "VERIFIED the message displayed is " + expectedPopupMsg, 
								  "Failed to Verify the displayed message");
			
			boolean closePopup8 = diep.closeModalPopup();
			TestValidation.IsTrue(closePopup8, 
								  "CLOSED Warning popup modal", 
								  "Failed to Close Warning popup modal");
			
			boolean setType9 = diep.selectExtractType(ExtractType.ROLES);
			TestValidation.IsTrue(setType9, 
								  "Extract type SET to " + ExtractType.ROLES, 
								  "Failed to Set Extract type to " + ExtractType.ROLES);
			
			boolean clickExtract9 = diep.clickDataExtract();
			TestValidation.IsTrue(clickExtract9, 
								  "CLICKED on Data Extract button on popup", 
								  "Failed to Click on Data Extract button on popup");
			
			boolean verifyMsg9 = diep.PopupModalMsg.getText().equalsIgnoreCase(expectedPopupMsg);
			TestValidation.IsTrue(verifyMsg9, 
								  "VERIFIED the message displayed is " + expectedPopupMsg, 
								  "Failed to Verify the displayed message");
			
			boolean closePopup9 = diep.closeModalPopup();
			TestValidation.IsTrue(closePopup9, 
								  "CLOSED Warning popup modal", 
								  "Failed to Close Warning popup modal");
			
			boolean setType10 = diep.selectExtractType(ExtractType.FORM_DEFINITION);
			TestValidation.IsTrue(setType10, 
								  "Extract type SET to " + ExtractType.FORM_DEFINITION, 
								  "Failed to Set Extract type to " + ExtractType.FORM_DEFINITION);
			
			boolean clickExtract10 = diep.clickDataExtract();
			TestValidation.IsTrue(clickExtract10, 
								  "CLICKED on Data Extract button on popup", 
								  "Failed to Click on Data Extract button on popup");
			
			boolean verifyMsg10 = diep.PopupModalMsg.getText().equalsIgnoreCase(expectedPopupMsg);
			TestValidation.IsTrue(verifyMsg10, 
								  "VERIFIED the message displayed is " + expectedPopupMsg, 
								  "Failed to Verify the displayed message");
			
			boolean closePopup10 = diep.closeModalPopup();
			TestValidation.IsTrue(closePopup10, 
								  "CLOSED Warning popup modal", 
								  "Failed to Close Warning popup modal");
			
			boolean setType11 = diep.selectExtractType(ExtractType.CONTROL_LIMITS);
			TestValidation.IsTrue(setType11, 
								  "Extract type SET to " + ExtractType.CONTROL_LIMITS, 
								  "Failed to Set Extract type to " + ExtractType.CONTROL_LIMITS);
			
			boolean clickExtract11 = diep.clickDataExtract();
			TestValidation.IsTrue(clickExtract11, 
								  "CLICKED on Data Extract button on popup", 
								  "Failed to Click on Data Extract button on popup");
			
			boolean verifyMsg11 = diep.PopupModalMsg.getText().equalsIgnoreCase(expectedPopupMsg);
			TestValidation.IsTrue(verifyMsg11, 
								  "VERIFIED the message displayed is " + expectedPopupMsg, 
								  "Failed to Verify the displayed message");
			
			boolean closePopup11 = diep.closeModalPopup();
			TestValidation.IsTrue(closePopup11, 
								  "CLOSED Warning popup modal", 
								  "Failed to Close Warning popup modal");
			
		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				TestValidation.Fail(TCGFailureMsg);
			}
		}
	}
	
	@Test(description = "DPT || Export - Verify the sorting (Ascending/Descending) for each columns for form compliance.")
	public void TestCase_24273() {
		
		try {
			HomePage hp = new HomePage(driver);			
			DataImportExportPage diep = hp.clickDataImportExportMenu();
			
			boolean exportBtn = diep.selectExportTab();
			TestValidation.IsTrue(exportBtn, 
								  "CLICKED on Export tab", 
								  "Failed to Click on Export tab");
			
			boolean openPopup = diep.openExtractDataPopUp();
			TestValidation.IsTrue(openPopup, 
								  "OPENED Export Data popup", 
								  "Failed to Open Export Data popup");
			
			boolean setType = diep.selectExtractType(ExtractType.FORM_COMPLIANCE);
			TestValidation.IsTrue(setType, 
								  "Extract type SET to " + ExtractType.FORM_COMPLIANCE, 
								  "Failed to Set Extract type to " + ExtractType.FORM_COMPLIANCE);
			
			boolean sortNameAsc = diep.openAndApplySettingsForColumn(ColumnNames.FORM_NAME, COLUMN_SETTING.SORTASCENDING, null);
			TestValidation.IsTrue(sortNameAsc, 
								  "For Form Name column, APPLIED the column setting as " + COLUMN_SETTING.SORTASCENDING, 
								  "Failed to apply the column setting as " + COLUMN_SETTING.SORTASCENDING 
								  	+ " for Form Name column");
			
			boolean verifySortNameAsc = CommonMethods.verifySortingForColumnHavingSpecialChars(diep.getListOfElementsForDPTExtractData(
										ColumnNames.FORM_NAME), false);
			TestValidation.IsTrue(verifySortNameAsc, 
								  "VERIFIED values of the column Form Name are sorted in Ascending", 
								  "Failed to verify that values of the column Form Name are sorted in Ascending");
			
			boolean sortNameDsc = diep.openAndApplySettingsForColumn(ColumnNames.FORM_NAME, COLUMN_SETTING.SORTDESCENDING, null);
			TestValidation.IsTrue(sortNameDsc, 
								  "For Form Name column, APPLIED the column setting as " + COLUMN_SETTING.SORTDESCENDING, 
								  "Failed to apply the column setting as " + COLUMN_SETTING.SORTDESCENDING + " for Form Name column");
			
			boolean verifySortNameDsc = CommonMethods.verifySortingForColumnHavingSpecialChars(diep.getListOfElementsForDPTExtractData(
										ColumnNames.FORM_NAME), true);
			TestValidation.IsTrue(verifySortNameDsc, 
								  "VERIFIED values of the column Form Name are sorted in Descending", 
								  "Failed to verify that values of the column Form Name are sorted in Descending");
			
			boolean sortTypeAsc = diep.openAndApplySettingsForColumn(ColumnNames.FORM_TYPE, COLUMN_SETTING.SORTASCENDING, null);
			TestValidation.IsTrue(sortTypeAsc, 
								  "For Form Type column, APPLIED the column setting as " + COLUMN_SETTING.SORTASCENDING, 
								  "Failed to apply the column setting as " + COLUMN_SETTING.SORTASCENDING 
								  	+ " for Form Type column");
			
			String verifySortTypeAsc = CommonMethods.verifySortingForColumn(diep.getListOfElementsForDPTExtractData(
										ColumnNames.FORM_TYPE));
			TestValidation.IsTrue(verifySortTypeAsc.equals(SORTING.ASC) || verifySortTypeAsc.equals(SORTING.EQL), 
								  "VERIFIED values of the column Form Type are sorted in Ascending", 
								  "Failed to verify that values of the column Form Type are sorted in Ascending");
			
			boolean sortTypeDsc = diep.openAndApplySettingsForColumn(ColumnNames.FORM_TYPE, COLUMN_SETTING.SORTDESCENDING, null);
			TestValidation.IsTrue(sortTypeDsc, 
								  "For Form Type column, APPLIED the column setting as " + COLUMN_SETTING.SORTDESCENDING, 
								  "Failed to apply the column setting as " + COLUMN_SETTING.SORTDESCENDING 
								  + " for Form Type column");
			
			String verifySortTypeDsc = CommonMethods.verifySortingForColumn(diep.getListOfElementsForDPTExtractData(
										ColumnNames.FORM_TYPE));
			TestValidation.IsTrue(verifySortTypeDsc.equals(SORTING.DSC) || verifySortTypeDsc.equals(SORTING.EQL), 
								  "VERIFIED values of the column Form Type are sorted in Descending", 
								  "Failed to verify that values of the column Form Type are sorted in Descending");
			
			boolean sortModByAsc = diep.openAndApplySettingsForColumn(ColumnNames.MODIFIED_BY, COLUMN_SETTING.SORTASCENDING, null);
			TestValidation.IsTrue(sortModByAsc, 
								  "For Modified By column, APPLIED the column setting as " + COLUMN_SETTING.SORTASCENDING, 
								  "Failed to apply the column setting as " + COLUMN_SETTING.SORTASCENDING 
								  	+ " for Modified By column");
			
			boolean verifySortModByAsc = CommonMethods.verifySortingForColumnHavingSpecialChars(diep.getListOfElementsForDPTExtractData(
										ColumnNames.MODIFIED_BY), false);
			TestValidation.IsTrue(verifySortModByAsc, 
								  "VERIFIED values of the column Modified By are sorted in Ascending", 
								  "Failed to verify that values of the column Modified By are sorted in Ascending");
			
			boolean sortModByDsc = diep.openAndApplySettingsForColumn(ColumnNames.MODIFIED_BY, COLUMN_SETTING.SORTDESCENDING, null);
			TestValidation.IsTrue(sortModByDsc, 
								  "For Modified By column, APPLIED the column setting as " + COLUMN_SETTING.SORTDESCENDING, 
								  "Failed to apply the column setting as " + COLUMN_SETTING.SORTDESCENDING 
								  + " for Modified By column");
			
			boolean verifySortModByDsc = CommonMethods.verifySortingForColumnHavingSpecialChars(diep.getListOfElementsForDPTExtractData(
										ColumnNames.MODIFIED_BY), true);
			TestValidation.IsTrue(verifySortModByDsc, 
								  "VERIFIED values of the column Modified By are sorted in Descending", 
								  "Failed to verify that values of the column Modified By are sorted in Descending");
		
			boolean sortModOnAsc = diep.openAndApplySettingsForColumn(ColumnNames.MODIFIED_ON, COLUMN_SETTING.SORTASCENDING, null);
			TestValidation.IsTrue(sortModOnAsc, 
								  "For Modified On column, APPLIED the column setting as " + COLUMN_SETTING.SORTASCENDING, 
								  "Failed to apply the column setting as " + COLUMN_SETTING.SORTASCENDING 
								  	+ " for Modified On column");
			
			DateTime dt = new DateTime();
			ComparisonValue verifySortModOnAsc = dt.verifySortingForColumn(diep.getListOfElementsForDPTExtractData(
										ColumnNames.MODIFIED_ON), false, false, false, true, false);
			TestValidation.IsTrue(verifySortModOnAsc.equals(ComparisonValue.LESSER) || verifySortModOnAsc.equals(ComparisonValue.EQUALS), 
								  "VERIFIED values of the column Modified On are sorted in Ascending", 
								  "Failed to verify that values of the column Modified On are sorted in Ascending");
			
			boolean sortModOnDsc = diep.openAndApplySettingsForColumn(ColumnNames.MODIFIED_ON, COLUMN_SETTING.SORTDESCENDING, null);
			TestValidation.IsTrue(sortModOnDsc, 
								  "For Modified On column, APPLIED the column setting as " + COLUMN_SETTING.SORTDESCENDING, 
								  "Failed to apply the column setting as " + COLUMN_SETTING.SORTDESCENDING 
								  + " for Modified On column");
			
			ComparisonValue verifySortModOnDsc = dt.verifySortingForColumn(diep.getListOfElementsForDPTExtractData(
										ColumnNames.MODIFIED_ON), false, false, false, true, false);
			TestValidation.IsTrue(verifySortModOnDsc.equals(ComparisonValue.GREATER) || verifySortModOnDsc.equals(ComparisonValue.EQUALS), 
								  "VERIFIED values of the column Modified On are sorted in Descending", 
								  "Failed to verify that values of the column Modified On are sorted in Descending");
		
			boolean sortVersionAsc = diep.openAndApplySettingsForColumn(ColumnNames.VERSION, COLUMN_SETTING.SORTASCENDING, null);
			TestValidation.IsTrue(sortVersionAsc, 
								  "For Version column, APPLIED the column setting as " + COLUMN_SETTING.SORTASCENDING, 
								  "Failed to apply the column setting as " + COLUMN_SETTING.SORTASCENDING 
								  	+ " for Version column");
			
			String verifySortVersionAsc = CommonMethods.verifySortingForColumn(diep.getListOfElementsForDPTExtractData(
										ColumnNames.VERSION));
			TestValidation.IsTrue(verifySortVersionAsc.equals(SORTING.ASC) || verifySortVersionAsc.equals(SORTING.EQL), 
								  "VERIFIED values of the column Version are sorted in Ascending", 
								  "Failed to verify that values of the column Version are sorted in Ascending");
			
			boolean sortVersionDsc = diep.openAndApplySettingsForColumn(ColumnNames.VERSION, COLUMN_SETTING.SORTDESCENDING, null);
			TestValidation.IsTrue(sortVersionDsc, 
								  "For Version column, APPLIED the column setting as " + COLUMN_SETTING.SORTDESCENDING, 
								  "Failed to apply the column setting as " + COLUMN_SETTING.SORTDESCENDING 
								  + " for Version column");
			
			String verifySortVersionDsc = CommonMethods.verifySortingForColumn(diep.getListOfElementsForDPTExtractData(
										ColumnNames.VERSION));
			TestValidation.IsTrue(verifySortVersionDsc.equals(SORTING.DSC) || verifySortVersionDsc.equals(SORTING.EQL), 
								  "VERIFIED values of the column Version are sorted in Descending", 
								  "Failed to verify that values of the column Version are sorted in Descending");
			
		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				TestValidation.Fail(TCGFailureMsg);
			}
		}
	}
	
	@Test(description = "DPT || Export- Verify the sorting (Ascending/Descending) for each columns for form definition.")
	public void TestCase_38515() {
		
		try {
			HomePage hp = new HomePage(driver);			
			DataImportExportPage diep = hp.clickDataImportExportMenu();
			
			boolean exportBtn = diep.selectExportTab();
			TestValidation.IsTrue(exportBtn, 
								  "CLICKED on Export tab", 
								  "Failed to Click on Export tab");
			
			boolean openPopup = diep.openExtractDataPopUp();
			TestValidation.IsTrue(openPopup, 
								  "OPENED Export Data popup", 
								  "Failed to Open Export Data popup");
			
			boolean setType = diep.selectExtractType(ExtractType.FORM_DEFINITION);
			TestValidation.IsTrue(setType, 
								  "Extract type SET to " + ExtractType.FORM_DEFINITION, 
								  "Failed to Set Extract type to " + ExtractType.FORM_DEFINITION);
			
			boolean sortNameAsc = diep.openAndApplySettingsForColumn(ColumnNames.FORM_NAME, COLUMN_SETTING.SORTASCENDING, null);
			TestValidation.IsTrue(sortNameAsc, 
								  "For Form Name column, APPLIED the column setting as " + COLUMN_SETTING.SORTASCENDING, 
								  "Failed to apply the column setting as " + COLUMN_SETTING.SORTASCENDING 
								  	+ " for Form Name column");
			
			boolean verifySortNameAsc = CommonMethods.verifySortingForColumnHavingSpecialChars(diep.getListOfElementsForDPTExtractData(
										ColumnNames.FORM_NAME), false);
			TestValidation.IsTrue(verifySortNameAsc, 
								  "VERIFIED values of the column Form Name are sorted in Ascending", 
								  "Failed to verify that values of the column Form Name are sorted in Ascending");
			
			boolean sortNameDsc = diep.openAndApplySettingsForColumn(ColumnNames.FORM_NAME, COLUMN_SETTING.SORTDESCENDING, null);
			TestValidation.IsTrue(sortNameDsc, 
								  "For Form Name column, APPLIED the column setting as " + COLUMN_SETTING.SORTDESCENDING, 
								  "Failed to apply the column setting as " + COLUMN_SETTING.SORTDESCENDING + " for Form Name column");
			
			boolean verifySortNameDsc = CommonMethods.verifySortingForColumnHavingSpecialChars(diep.getListOfElementsForDPTExtractData(
										ColumnNames.FORM_NAME), true);
			TestValidation.IsTrue(verifySortNameDsc, 
								  "VERIFIED values of the column Form Name are sorted in Descending", 
								  "Failed to verify that values of the column Form Name are sorted in Descending");
			
			boolean sortTypeAsc = diep.openAndApplySettingsForColumn(ColumnNames.FORM_TYPE, COLUMN_SETTING.SORTASCENDING, null);
			TestValidation.IsTrue(sortTypeAsc, 
								  "For Form Type column, APPLIED the column setting as " + COLUMN_SETTING.SORTASCENDING, 
								  "Failed to apply the column setting as " + COLUMN_SETTING.SORTASCENDING 
								  	+ " for Form Type column");
			
			boolean verifySortTypeAsc = CommonMethods.verifySortingForColumnHavingSpecialChars(diep.getListOfElementsForDPTExtractData(
										ColumnNames.FORM_TYPE), false);
			TestValidation.IsTrue(verifySortTypeAsc, 
								  "VERIFIED values of the column Form Type are sorted in Ascending", 
								  "Failed to verify that values of the column Form Type are sorted in Ascending");
			
			boolean sortTypeDsc = diep.openAndApplySettingsForColumn(ColumnNames.FORM_TYPE, COLUMN_SETTING.SORTDESCENDING, null);
			TestValidation.IsTrue(sortTypeDsc, 
								  "For Form Type column, APPLIED the column setting as " + COLUMN_SETTING.SORTDESCENDING, 
								  "Failed to apply the column setting as " + COLUMN_SETTING.SORTDESCENDING 
								  + " for Form Type column");
			
			boolean verifySortTypeDsc = CommonMethods.verifySortingForColumnHavingSpecialChars(diep.getListOfElementsForDPTExtractData(
										ColumnNames.FORM_TYPE), true);
			TestValidation.IsTrue(verifySortTypeDsc, 
								  "VERIFIED values of the column Form Type are sorted in Descending", 
								  "Failed to verify that values of the column Form Type are sorted in Descending");
			
			boolean sortModByAsc = diep.openAndApplySettingsForColumn(ColumnNames.MODIFIED_BY, COLUMN_SETTING.SORTASCENDING, null);
			TestValidation.IsTrue(sortModByAsc, 
								  "For Modified By column, APPLIED the column setting as " + COLUMN_SETTING.SORTASCENDING, 
								  "Failed to apply the column setting as " + COLUMN_SETTING.SORTASCENDING 
								  	+ " for Modified By column");
			
			boolean verifySortModByAsc = CommonMethods.verifySortingForColumnHavingSpecialChars(diep.getListOfElementsForDPTExtractData(
											ColumnNames.MODIFIED_BY), false);
			TestValidation.IsTrue(verifySortModByAsc, 
								  "VERIFIED values of the column Modified By are sorted in Ascending", 
								  "Failed to verify that values of the column Modified By are sorted in Ascending");
			
			boolean sortModByDsc = diep.openAndApplySettingsForColumn(ColumnNames.MODIFIED_BY, COLUMN_SETTING.SORTDESCENDING, null);
			TestValidation.IsTrue(sortModByDsc, 
								  "For Modified By column, APPLIED the column setting as " + COLUMN_SETTING.SORTDESCENDING, 
								  "Failed to apply the column setting as " + COLUMN_SETTING.SORTDESCENDING 
								  + " for Modified By column");
			
			boolean verifySortModByDsc = CommonMethods.verifySortingForColumnHavingSpecialChars(diep.getListOfElementsForDPTExtractData(
											ColumnNames.MODIFIED_BY), true);
			TestValidation.IsTrue(verifySortModByDsc, 
								  "VERIFIED values of the column Modified By are sorted in Descending", 
								  "Failed to verify that values of the column Modified By are sorted in Descending");
		
			boolean sortModOnAsc = diep.openAndApplySettingsForColumn(ColumnNames.MODIFIED_ON, COLUMN_SETTING.SORTASCENDING, null);
			TestValidation.IsTrue(sortModOnAsc, 
								  "For Modified On column, APPLIED the column setting as " + COLUMN_SETTING.SORTASCENDING, 
								  "Failed to apply the column setting as " + COLUMN_SETTING.SORTASCENDING 
								  	+ " for Modified On column");
			
			DateTime dt = new DateTime();
			ComparisonValue verifySortModOnAsc = dt.verifySortingForColumn(diep.getListOfElementsForDPTExtractData(
										ColumnNames.MODIFIED_ON), false, false, false, true, false);
			TestValidation.IsTrue(verifySortModOnAsc.equals(ComparisonValue.LESSER) || verifySortModOnAsc.equals(ComparisonValue.EQUALS), 
								  "VERIFIED values of the column Modified On are sorted in Ascending", 
								  "Failed to verify that values of the column Modified On are sorted in Ascending");
			
			boolean sortModOnDsc = diep.openAndApplySettingsForColumn(ColumnNames.MODIFIED_ON, COLUMN_SETTING.SORTDESCENDING, null);
			TestValidation.IsTrue(sortModOnDsc, 
								  "For Modified On column, APPLIED the column setting as " + COLUMN_SETTING.SORTDESCENDING, 
								  "Failed to apply the column setting as " + COLUMN_SETTING.SORTDESCENDING 
								  + " for Modified On column");
			
			ComparisonValue verifySortModOnDsc = dt.verifySortingForColumn(diep.getListOfElementsForDPTExtractData(
										ColumnNames.MODIFIED_ON), false, false, false, true, false);
			TestValidation.IsTrue(verifySortModOnDsc.equals(ComparisonValue.GREATER) || verifySortModOnDsc.equals(ComparisonValue.EQUALS), 
								  "VERIFIED values of the column Modified On are sorted in Descending", 
								  "Failed to verify that values of the column Modified On are sorted in Descending");
		
			boolean sortVersionAsc = diep.openAndApplySettingsForColumn(ColumnNames.VERSION, COLUMN_SETTING.SORTASCENDING, null);
			TestValidation.IsTrue(sortVersionAsc, 
								  "For Version column, APPLIED the column setting as " + COLUMN_SETTING.SORTASCENDING, 
								  "Failed to apply the column setting as " + COLUMN_SETTING.SORTASCENDING 
								  	+ " for Version column");
			
			String verifySortVersionAsc = CommonMethods.verifySortingForColumn(diep.getListOfElementsForDPTExtractData(
										ColumnNames.VERSION));
			TestValidation.IsTrue(verifySortVersionAsc.equals(SORTING.ASC) || verifySortVersionAsc.equals(SORTING.EQL), 
								  "VERIFIED values of the column Version are sorted in Ascending", 
								  "Failed to verify that values of the column Version are sorted in Ascending");
			
			boolean sortVersionDsc = diep.openAndApplySettingsForColumn(ColumnNames.VERSION, COLUMN_SETTING.SORTDESCENDING, null);
			TestValidation.IsTrue(sortVersionDsc, 
								  "For Version column, APPLIED the column setting as " + COLUMN_SETTING.SORTDESCENDING, 
								  "Failed to apply the column setting as " + COLUMN_SETTING.SORTDESCENDING 
								  + " for Version column");
			
			String verifySortVersionDsc = CommonMethods.verifySortingForColumn(diep.getListOfElementsForDPTExtractData(
										ColumnNames.VERSION));
			TestValidation.IsTrue(verifySortVersionDsc.equals(SORTING.DSC) || verifySortVersionDsc.equals(SORTING.EQL), 
								  "VERIFIED values of the column Version are sorted in Descending", 
								  "Failed to verify that values of the column Version are sorted in Descending");
			
		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				TestValidation.Fail(TCGFailureMsg);
			}
		}
	}
	
	@Test(description = "DPT || Export - Verify the sorting (Ascending/Descending) for each columns for records.")
	public void TestCase_38516() {
		
		try {
			HomePage hp = new HomePage(driver);			
			DataImportExportPage diep = hp.clickDataImportExportMenu();
			
			boolean exportBtn = diep.selectExportTab();
			TestValidation.IsTrue(exportBtn, 
								  "CLICKED on Export tab", 
								  "Failed to Click on Export tab");
			
			boolean openPopup = diep.openExtractDataPopUp();
			TestValidation.IsTrue(openPopup, 
								  "OPENED Export Data popup", 
								  "Failed to Open Export Data popup");
			
			boolean setType = diep.selectExtractType(ExtractType.RECORDS);
			TestValidation.IsTrue(setType, 
								  "Extract type SET to " + ExtractType.RECORDS, 
								  "Failed to Set Extract type to " + ExtractType.RECORDS);
			
			boolean sortLoctnAsc = diep.openAndApplySettingsForColumn(ColumnNames.LOCATION_NAME, COLUMN_SETTING.SORTASCENDING, null);
			TestValidation.IsTrue(sortLoctnAsc, 
								  "For Location Name column, APPLIED the column setting as " + COLUMN_SETTING.SORTASCENDING, 
								  "Failed to apply the column setting as " + COLUMN_SETTING.SORTASCENDING 
								  	+ " for Location Name column");
			
			boolean verifySortLoctnAsc = CommonMethods.verifySortingForColumnHavingSpecialChars(diep.getListOfElementsForDPTExtractData(
										ColumnNames.LOCATION_NAME), false);
			TestValidation.IsTrue(verifySortLoctnAsc, 
								  "VERIFIED values of the column Location Name are sorted in Ascending", 
								  "Failed to verify that values of the column Location Name are sorted in Ascending");
			
			boolean sortLoctnDsc = diep.openAndApplySettingsForColumn(ColumnNames.LOCATION_NAME, COLUMN_SETTING.SORTDESCENDING, null);
			TestValidation.IsTrue(sortLoctnDsc, 
								  "For Location Name column, APPLIED the column setting as " + COLUMN_SETTING.SORTDESCENDING, 
								  "Failed to apply the column setting as " + COLUMN_SETTING.SORTDESCENDING 
								  + " for Location Name column");
			
			boolean verifySortLoctnDsc = CommonMethods.verifySortingForColumnHavingSpecialChars(diep.getListOfElementsForDPTExtractData(
										ColumnNames.LOCATION_NAME), true);
			TestValidation.IsTrue(verifySortLoctnDsc, 
								  "VERIFIED values of the column Location Name are sorted in Descending", 
								  "Failed to verify that values of the column Location Name are sorted in Descending");
		
			boolean sortRsrcAsc = diep.openAndApplySettingsForColumn(ColumnNames.RESOURCE_NAME, COLUMN_SETTING.SORTASCENDING, null);
			TestValidation.IsTrue(sortRsrcAsc, 
								  "For Resource Name column, APPLIED the column setting as " + COLUMN_SETTING.SORTASCENDING, 
								  "Failed to apply the column setting as " + COLUMN_SETTING.SORTASCENDING 
								  	+ " for Resource Name column");
			
			boolean verifySortRsrcAsc = CommonMethods.verifySortingForColumnHavingSpecialChars(diep.getListOfElementsForDPTExtractData(
					ColumnNames.RESOURCE_NAME), false);
			TestValidation.IsTrue(verifySortRsrcAsc, 
								  "VERIFIED values of the column Resource Name are sorted in Ascending", 
								  "Failed to verify that values of the column Resource Name are sorted in Ascending");
			
			boolean sortRsrcDsc = diep.openAndApplySettingsForColumn(ColumnNames.RESOURCE_NAME, COLUMN_SETTING.SORTDESCENDING, null);
			TestValidation.IsTrue(sortRsrcDsc, 
								  "For Resource Name column, APPLIED the column setting as " + COLUMN_SETTING.SORTDESCENDING, 
								  "Failed to apply the column setting as " + COLUMN_SETTING.SORTDESCENDING 
								  + " for Resource Name column");
			
			boolean verifySortRsrcDsc = CommonMethods.verifySortingForColumnHavingSpecialChars(diep.getListOfElementsForDPTExtractData(
					ColumnNames.RESOURCE_NAME), true);
			TestValidation.IsTrue(verifySortRsrcDsc, 
								  "VERIFIED values of the column Resource Name are sorted in Descending", 
								  "Failed to verify that values of the column Resource Name are sorted in Descending");
			
			boolean sortNameAsc = diep.openAndApplySettingsForColumn(ColumnNames.FORM_NAME, COLUMN_SETTING.SORTASCENDING, null);
			TestValidation.IsTrue(sortNameAsc, 
								  "For Form Name column, APPLIED the column setting as " + COLUMN_SETTING.SORTASCENDING, 
								  "Failed to apply the column setting as " + COLUMN_SETTING.SORTASCENDING 
								  	+ " for Form Name column");
			
			boolean verifySortNameAsc = CommonMethods.verifySortingForColumnHavingSpecialChars(diep.getListOfElementsForDPTExtractData(
										ColumnNames.FORM_NAME), false);
			TestValidation.IsTrue(verifySortNameAsc, 
								  "VERIFIED values of the column Form Name are sorted in Ascending", 
								  "Failed to verify that values of the column Form Name are sorted in Ascending");
			
			boolean sortNameDsc = diep.openAndApplySettingsForColumn(ColumnNames.FORM_NAME, COLUMN_SETTING.SORTDESCENDING, null);
			TestValidation.IsTrue(sortNameDsc, 
								  "For Form Name column, APPLIED the column setting as " + COLUMN_SETTING.SORTDESCENDING, 
								  "Failed to apply the column setting as " + COLUMN_SETTING.SORTDESCENDING + " for Form Name column");
			
			boolean verifySortNameDsc = CommonMethods.verifySortingForColumnHavingSpecialChars(diep.getListOfElementsForDPTExtractData(
										ColumnNames.FORM_NAME), true);
			TestValidation.IsTrue(verifySortNameDsc, 
								  "VERIFIED values of the column Form Name are sorted in Descending", 
								  "Failed to verify that values of the column Form Name are sorted in Descending");
			
			boolean sortVersionAsc = diep.openAndApplySettingsForColumn(ColumnNames.VERSION, COLUMN_SETTING.SORTASCENDING, null);
			TestValidation.IsTrue(sortVersionAsc, 
								  "For Version column, APPLIED the column setting as " + COLUMN_SETTING.SORTASCENDING, 
								  "Failed to apply the column setting as " + COLUMN_SETTING.SORTASCENDING 
								  	+ " for Version column");
			
			String verifySortVersionAsc = CommonMethods.verifySortingForColumn(diep.getListOfElementsForDPTExtractData(
										ColumnNames.VERSION));
			TestValidation.IsTrue(verifySortVersionAsc.equals(SORTING.ASC) || verifySortVersionAsc.equals(SORTING.EQL), 
								  "VERIFIED values of the column Version are sorted in Ascending", 
								  "Failed to verify that values of the column Version are sorted in Ascending");
			
			boolean sortVersionDsc = diep.openAndApplySettingsForColumn(ColumnNames.VERSION, COLUMN_SETTING.SORTDESCENDING, null);
			TestValidation.IsTrue(sortVersionDsc, 
								  "For Version column, APPLIED the column setting as " + COLUMN_SETTING.SORTDESCENDING, 
								  "Failed to apply the column setting as " + COLUMN_SETTING.SORTDESCENDING 
								  + " for Version column");
			
			String verifySortVersionDsc = CommonMethods.verifySortingForColumn(diep.getListOfElementsForDPTExtractData(
										ColumnNames.VERSION));
			TestValidation.IsTrue(verifySortVersionDsc.equals(SORTING.DSC) || verifySortVersionDsc.equals(SORTING.EQL), 
								  "VERIFIED values of the column Version are sorted in Descending", 
								  "Failed to verify that values of the column Version are sorted in Descending");
			
		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				TestValidation.Fail(TCGFailureMsg);
			}
		}
	}
	
	@Test(description = "DPT || Export - Verify the sorting (Ascending/Descending) for each columns for control limits.")
	public void TestCase_38517() {
		
		try {
			HomePage hp = new HomePage(driver);			
			DataImportExportPage diep = hp.clickDataImportExportMenu();
			
			boolean exportBtn = diep.selectExportTab();
			TestValidation.IsTrue(exportBtn, 
								  "CLICKED on Export tab", 
								  "Failed to Click on Export tab");
			
			boolean openPopup = diep.openExtractDataPopUp();
			TestValidation.IsTrue(openPopup, 
								  "OPENED Export Data popup", 
								  "Failed to Open Export Data popup");
			
			boolean setType = diep.selectExtractType(ExtractType.CONTROL_LIMITS);
			TestValidation.IsTrue(setType, 
								  "Extract type SET to " + ExtractType.CONTROL_LIMITS, 
								  "Failed to Set Extract type to " + ExtractType.CONTROL_LIMITS);
			
			boolean sortNameAsc = diep.openAndApplySettingsForColumn(ColumnNames.FORM_NAME, COLUMN_SETTING.SORTASCENDING, null);
			TestValidation.IsTrue(sortNameAsc, 
								  "For Form Name column, APPLIED the column setting as " + COLUMN_SETTING.SORTASCENDING, 
								  "Failed to apply the column setting as " + COLUMN_SETTING.SORTASCENDING 
								  	+ " for Form Name column");
			
			boolean verifySortNameAsc = CommonMethods.verifySortingForColumnHavingSpecialChars(diep.getListOfElementsForDPTExtractData(
										ColumnNames.FORM_NAME), false);
			TestValidation.IsTrue(verifySortNameAsc, 
								  "VERIFIED values of the column Form Name are sorted in Ascending", 
								  "Failed to verify that values of the column Form Name are sorted in Ascending");
			
			boolean sortNameDsc = diep.openAndApplySettingsForColumn(ColumnNames.FORM_NAME, COLUMN_SETTING.SORTDESCENDING, null);
			TestValidation.IsTrue(sortNameDsc, 
								  "For Form Name column, APPLIED the column setting as " + COLUMN_SETTING.SORTDESCENDING, 
								  "Failed to apply the column setting as " + COLUMN_SETTING.SORTDESCENDING + " for Form Name column");
			
			boolean verifySortNameDsc = CommonMethods.verifySortingForColumnHavingSpecialChars(diep.getListOfElementsForDPTExtractData(
										ColumnNames.FORM_NAME), true);
			TestValidation.IsTrue(verifySortNameDsc, 
								  "VERIFIED values of the column Form Name are sorted in Descending", 
								  "Failed to verify that values of the column Form Name are sorted in Descending");
			
			boolean sortChartNameAsc = diep.openAndApplySettingsForColumn(ColumnNames.CHART_NAME, COLUMN_SETTING.SORTASCENDING, null);
			TestValidation.IsTrue(sortChartNameAsc, 
								  "For Chart Name column, APPLIED the column setting as " + COLUMN_SETTING.SORTASCENDING, 
								  "Failed to apply the column setting as " + COLUMN_SETTING.SORTASCENDING 
								  	+ " for Chart Name column");
			
			boolean verifySortChartNameAsc = CommonMethods.verifySortingForColumnHavingSpecialChars(diep.getListOfElementsForDPTExtractData(
										ColumnNames.CHART_NAME), false);
			TestValidation.IsTrue(verifySortChartNameAsc, 
								  "VERIFIED values of the column Chart Name are sorted in Ascending", 
								  "Failed to verify that values of the column Chart Name are sorted in Ascending");
			
			boolean sortChartNameDsc = diep.openAndApplySettingsForColumn(ColumnNames.CHART_NAME, COLUMN_SETTING.SORTDESCENDING, null);
			TestValidation.IsTrue(sortChartNameDsc, 
								  "For Chart Name column, APPLIED the column setting as " + COLUMN_SETTING.SORTDESCENDING, 
								  "Failed to apply the column setting as " + COLUMN_SETTING.SORTDESCENDING + " for Chart Name column");
			
			boolean verifySortChartNameDsc = CommonMethods.verifySortingForColumnHavingSpecialChars(diep.getListOfElementsForDPTExtractData(
										ColumnNames.CHART_NAME), true);
			TestValidation.IsTrue(verifySortChartNameDsc, 
								  "VERIFIED values of the column Chart Name are sorted in Descending", 
								  "Failed to verify that values of the column Chart Name are sorted in Descending");
			
			boolean sortLoctnAsc = diep.openAndApplySettingsForColumn(ColumnNames.LOCATION, COLUMN_SETTING.SORTASCENDING, null);
			TestValidation.IsTrue(sortLoctnAsc, 
								  "For Location column, APPLIED the column setting as " + COLUMN_SETTING.SORTASCENDING, 
								  "Failed to apply the column setting as " + COLUMN_SETTING.SORTASCENDING 
								  	+ " for Location column");
			
			boolean verifySortLoctnAsc = CommonMethods.verifySortingForColumnHavingSpecialChars(diep.getListOfElementsForDPTExtractData(
										ColumnNames.LOCATION), false);
			TestValidation.IsTrue(verifySortLoctnAsc, 
								  "VERIFIED values of the column Location are sorted in Ascending", 
								  "Failed to verify that values of the column Location are sorted in Ascending");
			
			boolean sortLoctnDsc = diep.openAndApplySettingsForColumn(ColumnNames.LOCATION, COLUMN_SETTING.SORTDESCENDING, null);
			TestValidation.IsTrue(sortLoctnDsc, 
								  "For Location column, APPLIED the column setting as " + COLUMN_SETTING.SORTDESCENDING, 
								  "Failed to apply the column setting as " + COLUMN_SETTING.SORTDESCENDING 
								  + " for Location column");
			
			String verifySortLoctnDsc = CommonMethods.verifySortingForColumn(diep.getListOfElementsForDPTExtractData(
										ColumnNames.LOCATION));
			TestValidation.IsTrue(verifySortLoctnDsc.equals(SORTING.DSC) || verifySortLoctnDsc.equals(SORTING.EQL), 
								  "VERIFIED values of the column Location are sorted in Descending", 
								  "Failed to verify that values of the column Location are sorted in Descending");
			
		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				TestValidation.Fail(TCGFailureMsg);
			}
		}
	}
	
	@Test(description = "DPT || Export- Verify the sorting (Ascending/Descending) for each columns for resource_items.")
	public void TestCase_38519() {
		
		try {
			HomePage hp = new HomePage(driver);			
			DataImportExportPage diep = hp.clickDataImportExportMenu();
			
			boolean exportBtn = diep.selectExportTab();
			TestValidation.IsTrue(exportBtn, 
								  "CLICKED on Export tab", 
								  "Failed to Click on Export tab");
			
			boolean openPopup = diep.openExtractDataPopUp();
			TestValidation.IsTrue(openPopup, 
								  "OPENED Export Data popup", 
								  "Failed to Open Export Data popup");
			
			boolean setType = diep.selectExtractType(ExtractType.RESOURCE_ITEMS);
			TestValidation.IsTrue(setType, 
								  "Extract type SET to " + ExtractType.RESOURCE_ITEMS, 
								  "Failed to Set Extract type to " + ExtractType.RESOURCE_ITEMS);
			
			boolean sortResrcAsc = diep.openAndApplySettingsForColumn(ColumnNames.RESOURCE, COLUMN_SETTING.SORTASCENDING, null);
			TestValidation.IsTrue(sortResrcAsc, 
								  "For Resource column, APPLIED the column setting as " + COLUMN_SETTING.SORTASCENDING, 
								  "Failed to apply the column setting as " + COLUMN_SETTING.SORTASCENDING 
								  	+ " for Resource column");
			
			String verifySortResrcAsc = CommonMethods.verifySortingForColumn(diep.getListOfElementsForDPTExtractData(
										ColumnNames.RESOURCE));
			TestValidation.IsTrue(verifySortResrcAsc.equals(SORTING.ASC) || verifySortResrcAsc.equals(SORTING.EQL), 
								  "VERIFIED values of the column Resource are sorted in Ascending", 
								  "Failed to verify that values of the column Resource are sorted in Ascending");
			
			boolean sortResrcDsc = diep.openAndApplySettingsForColumn(ColumnNames.RESOURCE, COLUMN_SETTING.SORTDESCENDING, null);
			TestValidation.IsTrue(sortResrcDsc, 
								  "For Resource column, APPLIED the column setting as " + COLUMN_SETTING.SORTDESCENDING, 
								  "Failed to apply the column setting as " + COLUMN_SETTING.SORTDESCENDING 
								  + " for Resource column");
			
			String verifySortResrcDsc = CommonMethods.verifySortingForColumn(diep.getListOfElementsForDPTExtractData(
										ColumnNames.RESOURCE));
			TestValidation.IsTrue(verifySortResrcDsc.equals(SORTING.DSC) || verifySortResrcDsc.equals(SORTING.EQL), 
								  "VERIFIED values of the column Resource are sorted in Descending", 
								  "Failed to verify that values of the column Resource are sorted in Descending");
			
			boolean sortHierarchyAsc = diep.openAndApplySettingsForColumn(ColumnNames.HIERARCHY, COLUMN_SETTING.SORTASCENDING, null);
			TestValidation.IsTrue(sortHierarchyAsc, 
								  "For Hierarchy column, APPLIED the column setting as " + COLUMN_SETTING.SORTASCENDING, 
								  "Failed to apply the column setting as " + COLUMN_SETTING.SORTASCENDING 
								  	+ " for Hierarchy column");
			
			String verifySortHierarchyAsc = CommonMethods.verifySortingForColumn(diep.getListOfElementsForDPTExtractData(
										ColumnNames.HIERARCHY));
			TestValidation.IsTrue(verifySortHierarchyAsc.equals(SORTING.ASC) || verifySortHierarchyAsc.equals(SORTING.EQL), 
								  "VERIFIED values of the column Hierarchy are sorted in Ascending", 
								  "Failed to verify that values of the column Hierarchy are sorted in Ascending");
			
			boolean sortHierarchyDsc = diep.openAndApplySettingsForColumn(ColumnNames.HIERARCHY, COLUMN_SETTING.SORTDESCENDING, null);
			TestValidation.IsTrue(sortHierarchyDsc, 
								  "For Hierarchy column, APPLIED the column setting as " + COLUMN_SETTING.SORTDESCENDING, 
								  "Failed to apply the column setting as " + COLUMN_SETTING.SORTDESCENDING 
								  + " for Hierarchy column");
			
			String verifySortHierarchyDsc = CommonMethods.verifySortingForColumn(diep.getListOfElementsForDPTExtractData(
										ColumnNames.HIERARCHY));
			TestValidation.IsTrue(verifySortHierarchyDsc.equals(SORTING.DSC) || verifySortHierarchyDsc.equals(SORTING.EQL), 
								  "VERIFIED values of the column Hierarchy are sorted in Descending", 
								  "Failed to verify that values of the column Hierarchy are sorted in Descending");
		
		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				TestValidation.Fail(TCGFailureMsg);
			}
		}
	}
	
	@Test(description = "DPT || Export - Verify the sorting (Ascending/Descending) for each columns for resource_equipments.")
	public void TestCase_38520() {
		
		try {
			HomePage hp = new HomePage(driver);			
			DataImportExportPage diep = hp.clickDataImportExportMenu();
			
			boolean exportBtn = diep.selectExportTab();
			TestValidation.IsTrue(exportBtn, 
								  "CLICKED on Export tab", 
								  "Failed to Click on Export tab");
			
			boolean openPopup = diep.openExtractDataPopUp();
			TestValidation.IsTrue(openPopup, 
								  "OPENED Export Data popup", 
								  "Failed to Open Export Data popup");
			
			boolean setType = diep.selectExtractType(ExtractType.RESOURCE_EQUIPMENTS);
			TestValidation.IsTrue(setType, 
								  "Extract type SET to " + ExtractType.RESOURCE_EQUIPMENTS, 
								  "Failed to Set Extract type to " + ExtractType.RESOURCE_EQUIPMENTS);
			
			boolean sortResrcAsc = diep.openAndApplySettingsForColumn(ColumnNames.RESOURCE, COLUMN_SETTING.SORTASCENDING, null);
			TestValidation.IsTrue(sortResrcAsc, 
								  "For Resource column, APPLIED the column setting as " + COLUMN_SETTING.SORTASCENDING, 
								  "Failed to apply the column setting as " + COLUMN_SETTING.SORTASCENDING 
								  	+ " for Resource column");
			
			String verifySortResrcAsc = CommonMethods.verifySortingForColumn(diep.getListOfElementsForDPTExtractData(
										ColumnNames.RESOURCE));
			TestValidation.IsTrue(verifySortResrcAsc.equals(SORTING.ASC) || verifySortResrcAsc.equals(SORTING.EQL), 
								  "VERIFIED values of the column Resource are sorted in Ascending", 
								  "Failed to verify that values of the column Resource are sorted in Ascending");
			
			boolean sortResrcDsc = diep.openAndApplySettingsForColumn(ColumnNames.RESOURCE, COLUMN_SETTING.SORTDESCENDING, null);
			TestValidation.IsTrue(sortResrcDsc, 
								  "For Resource column, APPLIED the column setting as " + COLUMN_SETTING.SORTDESCENDING, 
								  "Failed to apply the column setting as " + COLUMN_SETTING.SORTDESCENDING 
								  + " for Resource column");
			
			String verifySortResrcDsc = CommonMethods.verifySortingForColumn(diep.getListOfElementsForDPTExtractData(
										ColumnNames.RESOURCE));
			TestValidation.IsTrue(verifySortResrcDsc.equals(SORTING.DSC) || verifySortResrcDsc.equals(SORTING.EQL), 
								  "VERIFIED values of the column Resource are sorted in Descending", 
								  "Failed to verify that values of the column Resource are sorted in Descending");
			
			boolean sortHierarchyAsc = diep.openAndApplySettingsForColumn(ColumnNames.HIERARCHY, COLUMN_SETTING.SORTASCENDING, null);
			TestValidation.IsTrue(sortHierarchyAsc, 
								  "For Hierarchy column, APPLIED the column setting as " + COLUMN_SETTING.SORTASCENDING, 
								  "Failed to apply the column setting as " + COLUMN_SETTING.SORTASCENDING 
								  	+ " for Hierarchy column");
			
			String verifySortHierarchyAsc = CommonMethods.verifySortingForColumn(diep.getListOfElementsForDPTExtractData(
										ColumnNames.HIERARCHY));
			TestValidation.IsTrue(verifySortHierarchyAsc.equals(SORTING.ASC) || verifySortHierarchyAsc.equals(SORTING.EQL), 
								  "VERIFIED values of the column Hierarchy are sorted in Ascending", 
								  "Failed to verify that values of the column Hierarchy are sorted in Ascending");
			
			boolean sortHierarchyDsc = diep.openAndApplySettingsForColumn(ColumnNames.HIERARCHY, COLUMN_SETTING.SORTDESCENDING, null);
			TestValidation.IsTrue(sortHierarchyDsc, 
								  "For Hierarchy column, APPLIED the column setting as " + COLUMN_SETTING.SORTDESCENDING, 
								  "Failed to apply the column setting as " + COLUMN_SETTING.SORTDESCENDING 
								  + " for Hierarchy column");
			
			String verifySortHierarchyDsc = CommonMethods.verifySortingForColumn(diep.getListOfElementsForDPTExtractData(
										ColumnNames.HIERARCHY));
			TestValidation.IsTrue(verifySortHierarchyDsc.equals(SORTING.DSC) || verifySortHierarchyDsc.equals(SORTING.EQL), 
								  "VERIFIED values of the column Hierarchy are sorted in Descending", 
								  "Failed to verify that values of the column Hierarchy are sorted in Descending");
		
		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				TestValidation.Fail(TCGFailureMsg);
			}
		}
	}
	
	@Test(description = "DPT || Export - Verify the sorting (Ascending/Descending) for each columns for resource_suppliers.")
	public void TestCase_38521() {
		
		try {
			HomePage hp = new HomePage(driver);			
			DataImportExportPage diep = hp.clickDataImportExportMenu();
			
			boolean exportBtn = diep.selectExportTab();
			TestValidation.IsTrue(exportBtn, 
								  "CLICKED on Export tab", 
								  "Failed to Click on Export tab");
			
			boolean openPopup = diep.openExtractDataPopUp();
			TestValidation.IsTrue(openPopup, 
								  "OPENED Export Data popup", 
								  "Failed to Open Export Data popup");
			
			boolean setType = diep.selectExtractType(ExtractType.RESOURCE_SUPPLIERS);
			TestValidation.IsTrue(setType, 
								  "Extract type SET to " + ExtractType.RESOURCE_SUPPLIERS, 
								  "Failed to Set Extract type to " + ExtractType.RESOURCE_SUPPLIERS);
			
			boolean sortResrcAsc = diep.openAndApplySettingsForColumn(ColumnNames.RESOURCE, COLUMN_SETTING.SORTASCENDING, null);
			TestValidation.IsTrue(sortResrcAsc, 
								  "For Resource column, APPLIED the column setting as " + COLUMN_SETTING.SORTASCENDING, 
								  "Failed to apply the column setting as " + COLUMN_SETTING.SORTASCENDING 
								  	+ " for Resource column");
			
			boolean verifySortResrcAsc = CommonMethods.verifySortingForColumnHavingSpecialChars(
					diep.getListOfElementsForDPTExtractData(ColumnNames.RESOURCE), false);
			TestValidation.IsTrue(verifySortResrcAsc, 
								  "VERIFIED values of the column Resource are sorted in Ascending", 
								  "Failed to verify that values of the column Resource are sorted in Ascending");
			
			boolean sortResrcDsc = diep.openAndApplySettingsForColumn(ColumnNames.RESOURCE, COLUMN_SETTING.SORTDESCENDING, null);
			TestValidation.IsTrue(sortResrcDsc, 
								  "For Resource column, APPLIED the column setting as " + COLUMN_SETTING.SORTDESCENDING, 
								  "Failed to apply the column setting as " + COLUMN_SETTING.SORTDESCENDING 
								  + " for Resource column");
			
			boolean verifySortResrcDsc = CommonMethods.verifySortingForColumnHavingSpecialChars(
					diep.getListOfElementsForDPTExtractData(ColumnNames.RESOURCE), true);
			TestValidation.IsTrue(verifySortResrcDsc, 
								  "VERIFIED values of the column Resource are sorted in Descending", 
								  "Failed to verify that values of the column Resource are sorted in Descending");
			
			boolean sortHierarchyAsc = diep.openAndApplySettingsForColumn(ColumnNames.HIERARCHY, COLUMN_SETTING.SORTASCENDING, null);
			TestValidation.IsTrue(sortHierarchyAsc, 
								  "For Hierarchy column, APPLIED the column setting as " + COLUMN_SETTING.SORTASCENDING, 
								  "Failed to apply the column setting as " + COLUMN_SETTING.SORTASCENDING 
								  	+ " for Hierarchy column");
			
			boolean verifySortHierarchyAsc = CommonMethods.verifySortingForColumnHavingSpecialChars(
					diep.getListOfElementsForDPTExtractData(ColumnNames.HIERARCHY), false);
			TestValidation.IsTrue(verifySortHierarchyAsc, 
								  "VERIFIED values of the column Hierarchy are sorted in Ascending", 
								  "Failed to verify that values of the column Hierarchy are sorted in Ascending");
			
			boolean sortHierarchyDsc = diep.openAndApplySettingsForColumn(ColumnNames.HIERARCHY, COLUMN_SETTING.SORTDESCENDING, null);
			TestValidation.IsTrue(sortHierarchyDsc, 
								  "For Hierarchy column, APPLIED the column setting as " + COLUMN_SETTING.SORTDESCENDING, 
								  "Failed to apply the column setting as " + COLUMN_SETTING.SORTDESCENDING 
								  + " for Hierarchy column");
			
			boolean verifySortHierarchyDsc = CommonMethods.verifySortingForColumnHavingSpecialChars(
					diep.getListOfElementsForDPTExtractData(ColumnNames.HIERARCHY), true);
			TestValidation.IsTrue(verifySortHierarchyDsc, 
								  "VERIFIED values of the column Hierarchy are sorted in Descending", 
								  "Failed to verify that values of the column Hierarchy are sorted in Descending");
		
		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				TestValidation.Fail(TCGFailureMsg);
			}
		}
	}
	
	@Test(description = "DPT || Export - Verify the sorting (Ascending/Descending) for each columns for resource_customers.")
	public void TestCase_38518() {
		
		try {
			HomePage hp = new HomePage(driver);			
			DataImportExportPage diep = hp.clickDataImportExportMenu();
			
			boolean exportBtn = diep.selectExportTab();
			TestValidation.IsTrue(exportBtn, 
								  "CLICKED on Export tab", 
								  "Failed to Click on Export tab");
			
			boolean openPopup = diep.openExtractDataPopUp();
			TestValidation.IsTrue(openPopup, 
								  "OPENED Export Data popup", 
								  "Failed to Open Export Data popup");
			
			boolean setType = diep.selectExtractType(ExtractType.RESOURCE_CUSTOMERS);
			TestValidation.IsTrue(setType, 
								  "Extract type SET to " + ExtractType.RESOURCE_CUSTOMERS, 
								  "Failed to Set Extract type to " + ExtractType.RESOURCE_CUSTOMERS);
			
			boolean sortResrcAsc = diep.openAndApplySettingsForColumn(ColumnNames.RESOURCE, COLUMN_SETTING.SORTASCENDING, null);
			TestValidation.IsTrue(sortResrcAsc, 
								  "For Resource column, APPLIED the column setting as " + COLUMN_SETTING.SORTASCENDING, 
								  "Failed to apply the column setting as " + COLUMN_SETTING.SORTASCENDING 
								  	+ " for Resource column");
			
			String verifySortResrcAsc = CommonMethods.verifySortingForColumn(diep.getListOfElementsForDPTExtractData(
										ColumnNames.RESOURCE));
			TestValidation.IsTrue(verifySortResrcAsc.equals(SORTING.ASC) || verifySortResrcAsc.equals(SORTING.EQL), 
								  "VERIFIED values of the column Resource are sorted in Ascending", 
								  "Failed to verify that values of the column Resource are sorted in Ascending");
			
			boolean sortResrcDsc = diep.openAndApplySettingsForColumn(ColumnNames.RESOURCE, COLUMN_SETTING.SORTDESCENDING, null);
			TestValidation.IsTrue(sortResrcDsc, 
								  "For Resource column, APPLIED the column setting as " + COLUMN_SETTING.SORTDESCENDING, 
								  "Failed to apply the column setting as " + COLUMN_SETTING.SORTDESCENDING 
								  + " for Resource column");
			
			String verifySortResrcDsc = CommonMethods.verifySortingForColumn(diep.getListOfElementsForDPTExtractData(
										ColumnNames.RESOURCE));
			TestValidation.IsTrue(verifySortResrcDsc.equals(SORTING.DSC) || verifySortResrcDsc.equals(SORTING.EQL), 
								  "VERIFIED values of the column Resource are sorted in Descending", 
								  "Failed to verify that values of the column Resource are sorted in Descending");
			
			boolean sortHierarchyAsc = diep.openAndApplySettingsForColumn(ColumnNames.HIERARCHY, COLUMN_SETTING.SORTASCENDING, null);
			TestValidation.IsTrue(sortHierarchyAsc, 
								  "For Hierarchy column, APPLIED the column setting as " + COLUMN_SETTING.SORTASCENDING, 
								  "Failed to apply the column setting as " + COLUMN_SETTING.SORTASCENDING 
								  	+ " for Hierarchy column");
			
			String verifySortHierarchyAsc = CommonMethods.verifySortingForColumn(diep.getListOfElementsForDPTExtractData(
										ColumnNames.HIERARCHY));
			TestValidation.IsTrue(verifySortHierarchyAsc.equals(SORTING.ASC) || verifySortHierarchyAsc.equals(SORTING.EQL), 
								  "VERIFIED values of the column Hierarchy are sorted in Ascending", 
								  "Failed to verify that values of the column Hierarchy are sorted in Ascending");
			
			boolean sortHierarchyDsc = diep.openAndApplySettingsForColumn(ColumnNames.HIERARCHY, COLUMN_SETTING.SORTDESCENDING, null);
			TestValidation.IsTrue(sortHierarchyDsc, 
								  "For Hierarchy column, APPLIED the column setting as " + COLUMN_SETTING.SORTDESCENDING, 
								  "Failed to apply the column setting as " + COLUMN_SETTING.SORTDESCENDING 
								  + " for Hierarchy column");
			
			String verifySortHierarchyDsc = CommonMethods.verifySortingForColumn(diep.getListOfElementsForDPTExtractData(
										ColumnNames.HIERARCHY));
			TestValidation.IsTrue(verifySortHierarchyDsc.equals(SORTING.DSC) || verifySortHierarchyDsc.equals(SORTING.EQL), 
								  "VERIFIED values of the column Hierarchy are sorted in Descending", 
								  "Failed to verify that values of the column Hierarchy are sorted in Descending");
		
		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				TestValidation.Fail(TCGFailureMsg);
			}
		}
	}
	
	@Test(description = "DPT || Export - Verify the sorting (Ascending/Descending) for each columns for locations.")
	public void TestCase_38522() {
		
		try {
			HomePage hp = new HomePage(driver);			
			DataImportExportPage diep = hp.clickDataImportExportMenu();
			
			boolean exportBtn = diep.selectExportTab();
			TestValidation.IsTrue(exportBtn, 
								  "CLICKED on Export tab", 
								  "Failed to Click on Export tab");
			
			boolean openPopup = diep.openExtractDataPopUp();
			TestValidation.IsTrue(openPopup, 
								  "OPENED Export Data popup", 
								  "Failed to Open Export Data popup");
			
			boolean setType = diep.selectExtractType(ExtractType.LOCATIONS);
			TestValidation.IsTrue(setType, 
								  "Extract type SET to " + ExtractType.LOCATIONS, 
								  "Failed to Set Extract type to " + ExtractType.LOCATIONS);
			
			boolean sortHierarchyAsc = diep.openAndApplySettingsForColumn(ColumnNames.HIERARCHY, COLUMN_SETTING.SORTASCENDING, null);
			TestValidation.IsTrue(sortHierarchyAsc, 
								  "For Hierarchy column, APPLIED the column setting as " + COLUMN_SETTING.SORTASCENDING, 
								  "Failed to apply the column setting as " + COLUMN_SETTING.SORTASCENDING 
								  	+ " for Hierarchy column");
			
			String verifySortHierarchyAsc = CommonMethods.verifySortingForColumn(diep.getListOfElementsForDPTExtractData(
										ColumnNames.HIERARCHY));
			TestValidation.IsTrue(verifySortHierarchyAsc.equals(SORTING.ASC) || verifySortHierarchyAsc.equals(SORTING.EQL), 
								  "VERIFIED values of the column Hierarchy are sorted in Ascending", 
								  "Failed to verify that values of the column Hierarchy are sorted in Ascending");
			
			boolean sortHierarchyDsc = diep.openAndApplySettingsForColumn(ColumnNames.HIERARCHY, COLUMN_SETTING.SORTDESCENDING, null);
			TestValidation.IsTrue(sortHierarchyDsc, 
								  "For Hierarchy column, APPLIED the column setting as " + COLUMN_SETTING.SORTDESCENDING, 
								  "Failed to apply the column setting as " + COLUMN_SETTING.SORTDESCENDING 
								  + " for Hierarchy column");
			
			String verifySortHierarchyDsc = CommonMethods.verifySortingForColumn(diep.getListOfElementsForDPTExtractData(
										ColumnNames.HIERARCHY));
			TestValidation.IsTrue(verifySortHierarchyDsc.equals(SORTING.DSC) || verifySortHierarchyDsc.equals(SORTING.EQL), 
								  "VERIFIED values of the column Hierarchy are sorted in Descending", 
								  "Failed to verify that values of the column Hierarchy are sorted in Descending");
		
			boolean sortLoctnAsc = diep.openAndApplySettingsForColumn(ColumnNames.LOCATION_NAME, COLUMN_SETTING.SORTASCENDING, null);
			TestValidation.IsTrue(sortLoctnAsc, 
								  "For Location Name column, APPLIED the column setting as " + COLUMN_SETTING.SORTASCENDING, 
								  "Failed to apply the column setting as " + COLUMN_SETTING.SORTASCENDING 
								  	+ " for Location Name column");
			
			boolean verifySortLoctnAsc = CommonMethods.verifySortingForColumnHavingSpecialChars(diep.getListOfElementsForDPTExtractData(
										ColumnNames.LOCATION_NAME), false);
			TestValidation.IsTrue(verifySortLoctnAsc, 
								  "VERIFIED values of the column Location Name are sorted in Ascending", 
								  "Failed to verify that values of the column Location Name are sorted in Ascending");
			
			boolean sortLoctnDsc = diep.openAndApplySettingsForColumn(ColumnNames.LOCATION_NAME, COLUMN_SETTING.SORTDESCENDING, null);
			TestValidation.IsTrue(sortLoctnDsc, 
								  "For Location Name column, APPLIED the column setting as " + COLUMN_SETTING.SORTDESCENDING, 
								  "Failed to apply the column setting as " + COLUMN_SETTING.SORTDESCENDING 
								  + " for Location Name column");
			
			boolean verifySortLoctnDsc = CommonMethods.verifySortingForColumnHavingSpecialChars(diep.getListOfElementsForDPTExtractData(
										ColumnNames.LOCATION_NAME), true);
			TestValidation.IsTrue(verifySortLoctnDsc, 
								  "VERIFIED values of the column Location Name are sorted in Descending", 
								  "Failed to verify that values of the column Location Name are sorted in Descending");
			
		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				TestValidation.Fail(TCGFailureMsg);
			}
		}
	}
	
	@Test(description = "DPT || Export - Verify the sorting (Ascending/Descending) for each columns for users.")
	public void TestCase_38523() {
		
		try {
			HomePage hp = new HomePage(driver);			
			DataImportExportPage diep = hp.clickDataImportExportMenu();
			
			boolean exportBtn = diep.selectExportTab();
			TestValidation.IsTrue(exportBtn, 
								  "CLICKED on Export tab", 
								  "Failed to Click on Export tab");
			
			boolean openPopup = diep.openExtractDataPopUp();
			TestValidation.IsTrue(openPopup, 
								  "OPENED Export Data popup", 
								  "Failed to Open Export Data popup");
			
			boolean setType = diep.selectExtractType(ExtractType.USERS);
			TestValidation.IsTrue(setType, 
								  "Extract type SET to " + ExtractType.USERS, 
								  "Failed to Set Extract type to " + ExtractType.USERS);
			
			boolean sortNameAsc = diep.openAndApplySettingsForColumn(ColumnNames.USER_NAME, COLUMN_SETTING.SORTASCENDING, null);
			TestValidation.IsTrue(sortNameAsc, 
								  "For User Name column, APPLIED the column setting as " + COLUMN_SETTING.SORTASCENDING, 
								  "Failed to apply the column setting as " + COLUMN_SETTING.SORTASCENDING 
								  	+ " for User Name column");
			
			String verifySortNameAsc = CommonMethods.verifySortingForColumn(diep.getListOfElementsForDPTExtractData(
										ColumnNames.USER_NAME));
			TestValidation.IsTrue(verifySortNameAsc.equals(SORTING.ASC), 
								  "VERIFIED values of the column User Name are sorted in Ascending", 
								  "Failed to verify that values of the column User Name are sorted in Ascending");
			
			boolean sortNameDsc = diep.openAndApplySettingsForColumn(ColumnNames.USER_NAME, COLUMN_SETTING.SORTDESCENDING, null);
			TestValidation.IsTrue(sortNameDsc, 
								  "For User Name column, APPLIED the column setting as " + COLUMN_SETTING.SORTDESCENDING, 
								  "Failed to apply the column setting as " + COLUMN_SETTING.SORTDESCENDING + " for User Name column");
			
			String verifySortNameDsc = CommonMethods.verifySortingForColumn(diep.getListOfElementsForDPTExtractData(
										ColumnNames.USER_NAME));
			TestValidation.IsTrue(verifySortNameDsc.equals(SORTING.DSC), 
								  "VERIFIED values of the column User Name are sorted in Descending", 
								  "Failed to verify that values of the column User Name are sorted in Descending");
			
			boolean sortEmpIdAsc = diep.openAndApplySettingsForColumn(ColumnNames.EMPLOYEE_ID, COLUMN_SETTING.SORTASCENDING, null);
			TestValidation.IsTrue(sortEmpIdAsc, 
								  "For Employee Id column, APPLIED the column setting as " + COLUMN_SETTING.SORTASCENDING, 
								  "Failed to apply the column setting as " + COLUMN_SETTING.SORTASCENDING 
								  	+ " for Employee Id column");
			
			String verifySortEmpIdAsc = CommonMethods.verifySortingForColumn(diep.getListOfElementsForDPTExtractData(
										ColumnNames.EMPLOYEE_ID));
			TestValidation.IsTrue(verifySortEmpIdAsc.equals(SORTING.ASC) || verifySortEmpIdAsc.equals(SORTING.EQL), 
								  "VERIFIED values of the column Employee Id are sorted in Ascending", 
								  "Failed to verify that values of the column Employee Id are sorted in Ascending");
			
			boolean sortEmpIdDsc = diep.openAndApplySettingsForColumn(ColumnNames.EMPLOYEE_ID, COLUMN_SETTING.SORTDESCENDING, null);
			TestValidation.IsTrue(sortEmpIdDsc, 
								  "For Employee Id column, APPLIED the column setting as " + COLUMN_SETTING.SORTDESCENDING, 
								  "Failed to apply the column setting as " + COLUMN_SETTING.SORTDESCENDING 
								  + " for Employee Id column");
			
			String verifySortEmpIdDsc = CommonMethods.verifySortingForColumn(diep.getListOfElementsForDPTExtractData(
										ColumnNames.EMPLOYEE_ID));
			TestValidation.IsTrue(verifySortEmpIdDsc.equals(SORTING.DSC) || verifySortEmpIdDsc.equals(SORTING.EQL), 
								  "VERIFIED values of the column Employee Id are sorted in Descending", 
								  "Failed to verify that values of the column Employee Id are sorted in Descending");
			
			boolean sortFNameAsc = diep.openAndApplySettingsForColumn(ColumnNames.FIRST_NAME, COLUMN_SETTING.SORTASCENDING, null);
			TestValidation.IsTrue(sortFNameAsc, 
								  "For First Name column, APPLIED the column setting as " + COLUMN_SETTING.SORTASCENDING, 
								  "Failed to apply the column setting as " + COLUMN_SETTING.SORTASCENDING 
								  	+ " for First Name column");
			
			String verifySortFNameAsc = CommonMethods.verifySortingForColumn(diep.getListOfElementsForDPTExtractData(
										ColumnNames.FIRST_NAME));
			TestValidation.IsTrue(verifySortFNameAsc.equals(SORTING.ASC) || verifySortFNameAsc.equals(SORTING.EQL), 
								  "VERIFIED values of the column First Name are sorted in Ascending", 
								  "Failed to verify that values of the column First Name are sorted in Ascending");
			
			boolean sortFNameDsc = diep.openAndApplySettingsForColumn(ColumnNames.FIRST_NAME, COLUMN_SETTING.SORTDESCENDING, null);
			TestValidation.IsTrue(sortFNameDsc, 
								  "For First Name column, APPLIED the column setting as " + COLUMN_SETTING.SORTDESCENDING, 
								  "Failed to apply the column setting as " + COLUMN_SETTING.SORTDESCENDING 
								  + " for First Name column");
			
			String verifySortFNameDsc = CommonMethods.verifySortingForColumn(diep.getListOfElementsForDPTExtractData(
										ColumnNames.FIRST_NAME));
			TestValidation.IsTrue(verifySortFNameDsc.equals(SORTING.DSC) || verifySortFNameDsc.equals(SORTING.EQL), 
								  "VERIFIED values of the column First Name are sorted in Descending", 
								  "Failed to verify that values of the column First Name are sorted in Descending");

			boolean sortLNameAsc = diep.openAndApplySettingsForColumn(ColumnNames.LAST_NAME, COLUMN_SETTING.SORTASCENDING, null);
			TestValidation.IsTrue(sortLNameAsc, 
								  "For Last Name column, APPLIED the column setting as " + COLUMN_SETTING.SORTASCENDING, 
								  "Failed to apply the column setting as " + COLUMN_SETTING.SORTASCENDING 
								  	+ " for Last Name column");
			
			String verifySortLNameAsc = CommonMethods.verifySortingForColumn(diep.getListOfElementsForDPTExtractData(
										ColumnNames.LAST_NAME));
			TestValidation.IsTrue(verifySortLNameAsc.equals(SORTING.ASC) || verifySortLNameAsc.equals(SORTING.EQL), 
								  "VERIFIED values of the column Last Name are sorted in Ascending", 
								  "Failed to verify that values of the column Last Name are sorted in Ascending");
			
			boolean sortLNameDsc = diep.openAndApplySettingsForColumn(ColumnNames.LAST_NAME, COLUMN_SETTING.SORTDESCENDING, null);
			TestValidation.IsTrue(sortLNameDsc, 
								  "For Last Name column, APPLIED the column setting as " + COLUMN_SETTING.SORTDESCENDING, 
								  "Failed to apply the column setting as " + COLUMN_SETTING.SORTDESCENDING 
								  + " for Last Name column");
			
			String verifySortLNameDsc = CommonMethods.verifySortingForColumn(diep.getListOfElementsForDPTExtractData(
										ColumnNames.LAST_NAME));
			TestValidation.IsTrue(verifySortLNameDsc.equals(SORTING.DSC) || verifySortLNameDsc.equals(SORTING.EQL), 
								  "VERIFIED values of the column Last Name are sorted in Descending", 
								  "Failed to verify that values of the column Last Name are sorted in Descending");
					
			boolean sortTimezoneAsc = diep.openAndApplySettingsForColumn(ColumnNames.TIMEZONE, COLUMN_SETTING.SORTASCENDING, null);
			TestValidation.IsTrue(sortTimezoneAsc, 
								  "For Timezone column, APPLIED the column setting as " + COLUMN_SETTING.SORTASCENDING, 
								  "Failed to apply the column setting as " + COLUMN_SETTING.SORTASCENDING 
								  	+ " for Timezone column");
			
			String verifySortTimezoneAsc = CommonMethods.verifySortingForColumn(diep.getListOfElementsForDPTExtractData(
										ColumnNames.TIMEZONE));
			TestValidation.IsTrue(verifySortTimezoneAsc.equals(SORTING.ASC) || verifySortTimezoneAsc.equals(SORTING.EQL), 
								  "VERIFIED values of the column Timezone are sorted in Ascending", 
								  "Failed to verify that values of the column Timezone are sorted in Ascending");
			
			boolean sortTimezoneDsc = diep.openAndApplySettingsForColumn(ColumnNames.TIMEZONE, COLUMN_SETTING.SORTDESCENDING, null);
			TestValidation.IsTrue(sortTimezoneDsc, 
								  "For Timezone column, APPLIED the column setting as " + COLUMN_SETTING.SORTDESCENDING, 
								  "Failed to apply the column setting as " + COLUMN_SETTING.SORTDESCENDING 
								  + " for Timezone column");
			
			String verifySortTimezoneDsc = CommonMethods.verifySortingForColumn(diep.getListOfElementsForDPTExtractData(
										ColumnNames.TIMEZONE));
			TestValidation.IsTrue(verifySortTimezoneDsc.equals(SORTING.DSC) || verifySortTimezoneDsc.equals(SORTING.EQL), 
								  "VERIFIED values of the column Timezone are sorted in Descending", 
								  "Failed to verify that values of the column Timezone are sorted in Descending");
			
			boolean sortEmailAsc = diep.openAndApplySettingsForColumn(ColumnNames.EMAIL, COLUMN_SETTING.SORTASCENDING, null);
			TestValidation.IsTrue(sortEmailAsc, 
								  "For Email column, APPLIED the column setting as " + COLUMN_SETTING.SORTASCENDING, 
								  "Failed to apply the column setting as " + COLUMN_SETTING.SORTASCENDING 
								  	+ " for Email column");
			
			String verifySortEmailAsc = CommonMethods.verifySortingForColumn(diep.getListOfElementsForDPTExtractData(
										ColumnNames.EMAIL));
			TestValidation.IsTrue(verifySortEmailAsc.equals(SORTING.ASC) || verifySortEmailAsc.equals(SORTING.EQL), 
								  "VERIFIED values of the column Email are sorted in Ascending", 
								  "Failed to verify that values of the column Email are sorted in Ascending");
			
			boolean sortEmailDsc = diep.openAndApplySettingsForColumn(ColumnNames.EMAIL, COLUMN_SETTING.SORTDESCENDING, null);
			TestValidation.IsTrue(sortEmailDsc, 
								  "For Email column, APPLIED the column setting as " + COLUMN_SETTING.SORTDESCENDING, 
								  "Failed to apply the column setting as " + COLUMN_SETTING.SORTDESCENDING 
								  + " for Email column");
			
			String verifySortEmailDsc = CommonMethods.verifySortingForColumn(diep.getListOfElementsForDPTExtractData(
										ColumnNames.EMAIL));
			TestValidation.IsTrue(verifySortEmailDsc.equals(SORTING.DSC) || verifySortEmailDsc.equals(SORTING.EQL), 
								  "VERIFIED values of the column Email are sorted in Descending", 
								  "Failed to verify that values of the column Email are sorted in Descending");
			
			boolean sortDefLocnAsc = diep.openAndApplySettingsForColumn(ColumnNames.DEFAULT_LOCATION, COLUMN_SETTING.SORTASCENDING, null);
			TestValidation.IsTrue(sortDefLocnAsc, 
								  "For Default Location column, APPLIED the column setting as " + COLUMN_SETTING.SORTASCENDING, 
								  "Failed to apply the column setting as " + COLUMN_SETTING.SORTASCENDING 
								  	+ " for Default Location column");
			
			String verifySortDefLocnAsc = CommonMethods.verifySortingForColumn(diep.getListOfElementsForDPTExtractData(
										ColumnNames.DEFAULT_LOCATION));
			TestValidation.IsTrue(verifySortDefLocnAsc.equals(SORTING.ASC) || verifySortDefLocnAsc.equals(SORTING.EQL), 
								  "VERIFIED values of the column Default Location are sorted in Ascending", 
								  "Failed to verify that values of the column Default Location are sorted in Ascending");
			
			boolean sortDefLocnDsc = diep.openAndApplySettingsForColumn(ColumnNames.DEFAULT_LOCATION, COLUMN_SETTING.SORTDESCENDING, null);
			TestValidation.IsTrue(sortDefLocnDsc, 
								  "For Default Location column, APPLIED the column setting as " + COLUMN_SETTING.SORTDESCENDING, 
								  "Failed to apply the column setting as " + COLUMN_SETTING.SORTDESCENDING 
								  + " for Default Location column");
			
			String verifySortDefLocnDsc = CommonMethods.verifySortingForColumn(diep.getListOfElementsForDPTExtractData(
										ColumnNames.DEFAULT_LOCATION));
			TestValidation.IsTrue(verifySortDefLocnDsc.equals(SORTING.DSC) || verifySortDefLocnDsc.equals(SORTING.EQL), 
								  "VERIFIED values of the column Default Location are sorted in Descending", 
								  "Failed to verify that values of the column Default Location are sorted in Descending");
			
		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				TestValidation.Fail(TCGFailureMsg);
			}
		}
	}
	
	@Test(description = "DPT || Export - Verify the sorting (Ascending/Descending) for each columns for supplier users.")
	public void TestCase_38524() {
		
		try {
			HomePage hp = new HomePage(driver);			
			DataImportExportPage diep = hp.clickDataImportExportMenu();
			
			boolean exportBtn = diep.selectExportTab();
			TestValidation.IsTrue(exportBtn, 
								  "CLICKED on Export tab", 
								  "Failed to Click on Export tab");
			
			boolean openPopup = diep.openExtractDataPopUp();
			TestValidation.IsTrue(openPopup, 
								  "OPENED Export Data popup", 
								  "Failed to Open Export Data popup");
			
			boolean setType = diep.selectExtractType(ExtractType.SUPPLIER_USERS);
			TestValidation.IsTrue(setType, 
								  "Extract type SET to " + ExtractType.SUPPLIER_USERS, 
								  "Failed to Set Extract type to " + ExtractType.SUPPLIER_USERS);
			
			boolean sortNameAsc = diep.openAndApplySettingsForColumn(ColumnNames.USER_NAME, COLUMN_SETTING.SORTASCENDING, null);
			TestValidation.IsTrue(sortNameAsc, 
								  "For User Name column, APPLIED the column setting as " + COLUMN_SETTING.SORTASCENDING, 
								  "Failed to apply the column setting as " + COLUMN_SETTING.SORTASCENDING 
								  	+ " for User Name column");
			
			String verifySortNameAsc = CommonMethods.verifySortingForColumn(diep.getListOfElementsForDPTExtractData(
										ColumnNames.USER_NAME));
			TestValidation.IsTrue(verifySortNameAsc.equals(SORTING.ASC), 
								  "VERIFIED values of the column User Name are sorted in Ascending", 
								  "Failed to verify that values of the column User Name are sorted in Ascending");
			
			boolean sortNameDsc = diep.openAndApplySettingsForColumn(ColumnNames.USER_NAME, COLUMN_SETTING.SORTDESCENDING, null);
			TestValidation.IsTrue(sortNameDsc, 
								  "For User Name column, APPLIED the column setting as " + COLUMN_SETTING.SORTDESCENDING, 
								  "Failed to apply the column setting as " + COLUMN_SETTING.SORTDESCENDING + " for User Name column");
			
			String verifySortNameDsc = CommonMethods.verifySortingForColumn(diep.getListOfElementsForDPTExtractData(
										ColumnNames.USER_NAME));
			TestValidation.IsTrue(verifySortNameDsc.equals(SORTING.DSC), 
								  "VERIFIED values of the column User Name are sorted in Descending", 
								  "Failed to verify that values of the column User Name are sorted in Descending");
			
			boolean sortEmpIdAsc = diep.openAndApplySettingsForColumn(ColumnNames.EMPLOYEE_ID, COLUMN_SETTING.SORTASCENDING, null);
			TestValidation.IsTrue(sortEmpIdAsc, 
								  "For Employee Id column, APPLIED the column setting as " + COLUMN_SETTING.SORTASCENDING, 
								  "Failed to apply the column setting as " + COLUMN_SETTING.SORTASCENDING 
								  	+ " for Employee Id column");
			
			String verifySortEmpIdAsc = CommonMethods.verifySortingForColumn(diep.getListOfElementsForDPTExtractData(
										ColumnNames.EMPLOYEE_ID));
			TestValidation.IsTrue(verifySortEmpIdAsc.equals(SORTING.ASC) || verifySortEmpIdAsc.equals(SORTING.EQL), 
								  "VERIFIED values of the column Employee Id are sorted in Ascending", 
								  "Failed to verify that values of the column Employee Id are sorted in Ascending");
			
			boolean sortEmpIdDsc = diep.openAndApplySettingsForColumn(ColumnNames.EMPLOYEE_ID, COLUMN_SETTING.SORTDESCENDING, null);
			TestValidation.IsTrue(sortEmpIdDsc, 
								  "For Employee Id column, APPLIED the column setting as " + COLUMN_SETTING.SORTDESCENDING, 
								  "Failed to apply the column setting as " + COLUMN_SETTING.SORTDESCENDING 
								  + " for Employee Id column");
			
			String verifySortEmpIdDsc = CommonMethods.verifySortingForColumn(diep.getListOfElementsForDPTExtractData(
										ColumnNames.EMPLOYEE_ID));
			TestValidation.IsTrue(verifySortEmpIdDsc.equals(SORTING.DSC) || verifySortEmpIdDsc.equals(SORTING.EQL), 
								  "VERIFIED values of the column Employee Id are sorted in Descending", 
								  "Failed to verify that values of the column Employee Id are sorted in Descending");
			
			boolean sortFNameAsc = diep.openAndApplySettingsForColumn(ColumnNames.FIRST_NAME, COLUMN_SETTING.SORTASCENDING, null);
			TestValidation.IsTrue(sortFNameAsc, 
								  "For First Name column, APPLIED the column setting as " + COLUMN_SETTING.SORTASCENDING, 
								  "Failed to apply the column setting as " + COLUMN_SETTING.SORTASCENDING 
								  	+ " for First Name column");
			
			String verifySortFNameAsc = CommonMethods.verifySortingForColumn(diep.getListOfElementsForDPTExtractData(
										ColumnNames.FIRST_NAME));
			TestValidation.IsTrue(verifySortFNameAsc.equals(SORTING.ASC) || verifySortFNameAsc.equals(SORTING.EQL), 
								  "VERIFIED values of the column First Name are sorted in Ascending", 
								  "Failed to verify that values of the column First Name are sorted in Ascending");
			
			boolean sortFNameDsc = diep.openAndApplySettingsForColumn(ColumnNames.FIRST_NAME, COLUMN_SETTING.SORTDESCENDING, null);
			TestValidation.IsTrue(sortFNameDsc, 
								  "For First Name column, APPLIED the column setting as " + COLUMN_SETTING.SORTDESCENDING, 
								  "Failed to apply the column setting as " + COLUMN_SETTING.SORTDESCENDING 
								  + " for First Name column");
			
			String verifySortFNameDsc = CommonMethods.verifySortingForColumn(diep.getListOfElementsForDPTExtractData(
										ColumnNames.FIRST_NAME));
			TestValidation.IsTrue(verifySortFNameDsc.equals(SORTING.DSC) || verifySortFNameDsc.equals(SORTING.EQL), 
								  "VERIFIED values of the column First Name are sorted in Descending", 
								  "Failed to verify that values of the column First Name are sorted in Descending");

			boolean sortLNameAsc = diep.openAndApplySettingsForColumn(ColumnNames.LAST_NAME, COLUMN_SETTING.SORTASCENDING, null);
			TestValidation.IsTrue(sortLNameAsc, 
								  "For Last Name column, APPLIED the column setting as " + COLUMN_SETTING.SORTASCENDING, 
								  "Failed to apply the column setting as " + COLUMN_SETTING.SORTASCENDING 
								  	+ " for Last Name column");
			
			String verifySortLNameAsc = CommonMethods.verifySortingForColumn(diep.getListOfElementsForDPTExtractData(
										ColumnNames.LAST_NAME));
			TestValidation.IsTrue(verifySortLNameAsc.equals(SORTING.ASC) || verifySortLNameAsc.equals(SORTING.EQL), 
								  "VERIFIED values of the column Last Name are sorted in Ascending", 
								  "Failed to verify that values of the column Last Name are sorted in Ascending");
			
			boolean sortLNameDsc = diep.openAndApplySettingsForColumn(ColumnNames.LAST_NAME, COLUMN_SETTING.SORTDESCENDING, null);
			TestValidation.IsTrue(sortLNameDsc, 
								  "For Last Name column, APPLIED the column setting as " + COLUMN_SETTING.SORTDESCENDING, 
								  "Failed to apply the column setting as " + COLUMN_SETTING.SORTDESCENDING 
								  + " for Last Name column");
			
			String verifySortLNameDsc = CommonMethods.verifySortingForColumn(diep.getListOfElementsForDPTExtractData(
										ColumnNames.LAST_NAME));
			TestValidation.IsTrue(verifySortLNameDsc.equals(SORTING.DSC) || verifySortLNameDsc.equals(SORTING.EQL), 
								  "VERIFIED values of the column Last Name are sorted in Descending", 
								  "Failed to verify that values of the column Last Name are sorted in Descending");
			
			boolean sortTimezoneAsc = diep.openAndApplySettingsForColumn(ColumnNames.TIMEZONE, COLUMN_SETTING.SORTASCENDING, null);
			TestValidation.IsTrue(sortTimezoneAsc, 
								  "For Timezone column, APPLIED the column setting as " + COLUMN_SETTING.SORTASCENDING, 
								  "Failed to apply the column setting as " + COLUMN_SETTING.SORTASCENDING 
								  	+ " for Timezone column");
			
			String verifySortTimezoneAsc = CommonMethods.verifySortingForColumn(diep.getListOfElementsForDPTExtractData(
										ColumnNames.TIMEZONE));
			TestValidation.IsTrue(verifySortTimezoneAsc.equals(SORTING.ASC) || verifySortTimezoneAsc.equals(SORTING.EQL), 
								  "VERIFIED values of the column Timezone are sorted in Ascending", 
								  "Failed to verify that values of the column Timezone are sorted in Ascending");
			
			boolean sortTimezoneDsc = diep.openAndApplySettingsForColumn(ColumnNames.TIMEZONE, COLUMN_SETTING.SORTDESCENDING, null);
			TestValidation.IsTrue(sortTimezoneDsc, 
								  "For Timezone column, APPLIED the column setting as " + COLUMN_SETTING.SORTDESCENDING, 
								  "Failed to apply the column setting as " + COLUMN_SETTING.SORTDESCENDING 
								  + " for Timezone column");
			
			String verifySortTimezoneDsc = CommonMethods.verifySortingForColumn(diep.getListOfElementsForDPTExtractData(
										ColumnNames.TIMEZONE));
			TestValidation.IsTrue(verifySortTimezoneDsc.equals(SORTING.DSC) || verifySortTimezoneDsc.equals(SORTING.EQL), 
								  "VERIFIED values of the column Timezone are sorted in Descending", 
								  "Failed to verify that values of the column Timezone are sorted in Descending");
			
			boolean sortEmailAsc = diep.openAndApplySettingsForColumn(ColumnNames.EMAIL, COLUMN_SETTING.SORTASCENDING, null);
			TestValidation.IsTrue(sortEmailAsc, 
								  "For Email column, APPLIED the column setting as " + COLUMN_SETTING.SORTASCENDING, 
								  "Failed to apply the column setting as " + COLUMN_SETTING.SORTASCENDING 
								  	+ " for Email column");
			
			String verifySortEmailAsc = CommonMethods.verifySortingForColumn(diep.getListOfElementsForDPTExtractData(
										ColumnNames.EMAIL));
			TestValidation.IsTrue(verifySortEmailAsc.equals(SORTING.ASC) || verifySortEmailAsc.equals(SORTING.EQL), 
								  "VERIFIED values of the column Email are sorted in Ascending", 
								  "Failed to verify that values of the column Email are sorted in Ascending");
			
			boolean sortEmailDsc = diep.openAndApplySettingsForColumn(ColumnNames.EMAIL, COLUMN_SETTING.SORTDESCENDING, null);
			TestValidation.IsTrue(sortEmailDsc, 
								  "For Email column, APPLIED the column setting as " + COLUMN_SETTING.SORTDESCENDING, 
								  "Failed to apply the column setting as " + COLUMN_SETTING.SORTDESCENDING 
								  + " for Email column");
			
			String verifySortEmailDsc = CommonMethods.verifySortingForColumn(diep.getListOfElementsForDPTExtractData(
										ColumnNames.EMAIL));
			TestValidation.IsTrue(verifySortEmailDsc.equals(SORTING.DSC) || verifySortEmailDsc.equals(SORTING.EQL), 
								  "VERIFIED values of the column Email are sorted in Descending", 
								  "Failed to verify that values of the column Email are sorted in Descending");
			
			boolean sortTitleAsc = diep.openAndApplySettingsForColumn(ColumnNames.TITLE, COLUMN_SETTING.SORTASCENDING, null);
			TestValidation.IsTrue(sortTitleAsc, 
								  "For Title column, APPLIED the column setting as " + COLUMN_SETTING.SORTASCENDING, 
								  "Failed to apply the column setting as " + COLUMN_SETTING.SORTASCENDING 
								  	+ " for Title column");
			
			String verifySortTitleAsc = CommonMethods.verifySortingForColumn(diep.getListOfElementsForDPTExtractData(
										ColumnNames.TITLE));
			TestValidation.IsTrue(verifySortTitleAsc.equals(SORTING.ASC) || verifySortTitleAsc.equals(SORTING.EQL), 
								  "VERIFIED values of the column Title are sorted in Ascending", 
								  "Failed to verify that values of the column Title are sorted in Ascending");
			
			boolean sortTitleDsc = diep.openAndApplySettingsForColumn(ColumnNames.TITLE, COLUMN_SETTING.SORTDESCENDING, null);
			TestValidation.IsTrue(sortTitleDsc, 
								  "For Title column, APPLIED the column setting as " + COLUMN_SETTING.SORTDESCENDING, 
								  "Failed to apply the column setting as " + COLUMN_SETTING.SORTDESCENDING 
								  + " for Title column");
			
			String verifySortTitleDsc = CommonMethods.verifySortingForColumn(diep.getListOfElementsForDPTExtractData(
										ColumnNames.TITLE));
			TestValidation.IsTrue(verifySortTitleDsc.equals(SORTING.DSC) || verifySortTitleDsc.equals(SORTING.EQL), 
								  "VERIFIED values of the column Title are sorted in Descending", 
								  "Failed to verify that values of the column Title are sorted in Descending");
			
		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				TestValidation.Fail(TCGFailureMsg);
			}
		}
	}	
	
	@Test(description = "DPT || Export - Verify the sorting (Ascending/Descending) for each columns for workgroup.")
	public void TestCase_38525() {
		
		try {
			HomePage hp = new HomePage(driver);			
			DataImportExportPage diep = hp.clickDataImportExportMenu();
			
			boolean exportBtn = diep.selectExportTab();
			TestValidation.IsTrue(exportBtn, 
								  "CLICKED on Export tab", 
								  "Failed to Click on Export tab");
			
			boolean openPopup = diep.openExtractDataPopUp();
			TestValidation.IsTrue(openPopup, 
								  "OPENED Export Data popup", 
								  "Failed to Open Export Data popup");
			
			boolean setType = diep.selectExtractType(ExtractType.WORKGROUPS);
			TestValidation.IsTrue(setType, 
								  "Extract type SET to " + ExtractType.WORKGROUPS, 
								  "Failed to Set Extract type to " + ExtractType.WORKGROUPS);
			
			boolean sortNameAsc = diep.openAndApplySettingsForColumn(ColumnNames.WORKGROUP_NAME, COLUMN_SETTING.SORTASCENDING, null);
			TestValidation.IsTrue(sortNameAsc, 
								  "For Workgroup Name column, APPLIED the column setting as " + COLUMN_SETTING.SORTASCENDING, 
								  "Failed to apply the column setting as " + COLUMN_SETTING.SORTASCENDING 
								  	+ " for Workgroup Name column");
			
			String verifySortNameAsc = CommonMethods.verifySortingForColumn(diep.getListOfElementsForDPTExtractData(
										ColumnNames.WORKGROUP_NAME));
			TestValidation.IsTrue(verifySortNameAsc.equals(SORTING.ASC), 
								  "VERIFIED values of the column Workgroup Name are sorted in Ascending", 
								  "Failed to verify that values of the column Workgroup Name are sorted in Ascending");
			
			boolean sortNameDsc = diep.openAndApplySettingsForColumn(ColumnNames.WORKGROUP_NAME, COLUMN_SETTING.SORTDESCENDING, null);
			TestValidation.IsTrue(sortNameDsc, 
								  "For Workgroup Name column, APPLIED the column setting as " + COLUMN_SETTING.SORTDESCENDING, 
								  "Failed to apply the column setting as " + COLUMN_SETTING.SORTDESCENDING + " for Workgroup Name column");
			
			String verifySortNameDsc = CommonMethods.verifySortingForColumn(diep.getListOfElementsForDPTExtractData(
										ColumnNames.WORKGROUP_NAME));
			TestValidation.IsTrue(verifySortNameDsc.equals(SORTING.DSC), 
								  "VERIFIED values of the column Workgroup Name are sorted in Descending", 
								  "Failed to verify that values of the column Workgroup Name are sorted in Descending");
			
			boolean sortIsEnableAsc = diep.openAndApplySettingsForColumn(ColumnNames.IS_ENABLE, COLUMN_SETTING.SORTASCENDING, null);
			TestValidation.IsTrue(sortIsEnableAsc, 
								  "For Is Enable column, APPLIED the column setting as " + COLUMN_SETTING.SORTASCENDING, 
								  "Failed to apply the column setting as " + COLUMN_SETTING.SORTASCENDING 
								  	+ " for Is Enable column");
			
			String verifySortIsEnableAsc = CommonMethods.verifySortingForColumn(diep.getListOfElementsForDPTExtractData(
										ColumnNames.IS_ENABLE));
			TestValidation.IsTrue(verifySortIsEnableAsc.equals(SORTING.ASC) || verifySortIsEnableAsc.equals(SORTING.EQL), 
								  "VERIFIED values of the column Is Enable are sorted in Ascending", 
								  "Failed to verify that values of the column Is Enable are sorted in Ascending");
			
			boolean sortIsEnableDsc = diep.openAndApplySettingsForColumn(ColumnNames.IS_ENABLE, COLUMN_SETTING.SORTDESCENDING, null);
			TestValidation.IsTrue(sortIsEnableDsc, 
								  "For Is Enable column, APPLIED the column setting as " + COLUMN_SETTING.SORTDESCENDING, 
								  "Failed to apply the column setting as " + COLUMN_SETTING.SORTDESCENDING 
								  + " for Is Enable column");
			
			String verifySortIsEnableDsc = CommonMethods.verifySortingForColumn(diep.getListOfElementsForDPTExtractData(
										ColumnNames.IS_ENABLE));
			TestValidation.IsTrue(verifySortIsEnableDsc.equals(SORTING.DSC) || verifySortIsEnableDsc.equals(SORTING.EQL), 
								  "VERIFIED values of the column Is Enable are sorted in Descending", 
								  "Failed to verify that values of the column Is Enable are sorted in Descending");
			
		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				TestValidation.Fail(TCGFailureMsg);
			}
		}
	}
	
	@Test(description = "DPT || Export - Verify the sorting (Ascending/Descending) for each columns for roles.")
	public void TestCase_38526() {
		
		try {
			HomePage hp = new HomePage(driver);			
			DataImportExportPage diep = hp.clickDataImportExportMenu();
			
			boolean exportBtn = diep.selectExportTab();
			TestValidation.IsTrue(exportBtn, 
								  "CLICKED on Export tab", 
								  "Failed to Click on Export tab");
			
			boolean openPopup = diep.openExtractDataPopUp();
			TestValidation.IsTrue(openPopup, 
								  "OPENED Export Data popup", 
								  "Failed to Open Export Data popup");
			
			boolean setType = diep.selectExtractType(ExtractType.ROLES);
			TestValidation.IsTrue(setType, 
								  "Extract type SET to " + ExtractType.ROLES, 
								  "Failed to Set Extract type to " + ExtractType.ROLES);
			
			boolean sortNameAsc = diep.openAndApplySettingsForColumn(ColumnNames.ROLE_NAME, COLUMN_SETTING.SORTASCENDING, null);
			TestValidation.IsTrue(sortNameAsc, 
								  "For Role Name column, APPLIED the column setting as " + COLUMN_SETTING.SORTASCENDING, 
								  "Failed to apply the column setting as " + COLUMN_SETTING.SORTASCENDING 
								  	+ " for Role Name column");
			
			String verifySortNameAsc = CommonMethods.verifySortingForColumn(diep.getListOfElementsForDPTExtractData(
										ColumnNames.ROLE_NAME));
			TestValidation.IsTrue(verifySortNameAsc.equals(SORTING.ASC), 
								  "VERIFIED values of the column Role Name are sorted in Ascending", 
								  "Failed to verify that values of the column Role Name are sorted in Ascending");
			
			boolean sortNameDsc = diep.openAndApplySettingsForColumn(ColumnNames.ROLE_NAME, COLUMN_SETTING.SORTDESCENDING, null);
			TestValidation.IsTrue(sortNameDsc, 
								  "For Role Name column, APPLIED the column setting as " + COLUMN_SETTING.SORTDESCENDING, 
								  "Failed to apply the column setting as " + COLUMN_SETTING.SORTDESCENDING + " for Role Name column");
			
			String verifySortNameDsc = CommonMethods.verifySortingForColumn(diep.getListOfElementsForDPTExtractData(
										ColumnNames.ROLE_NAME));
			TestValidation.IsTrue(verifySortNameDsc.equals(SORTING.DSC), 
								  "VERIFIED values of the column Role Name are sorted in Descending", 
								  "Failed to verify that values of the column Role Name are sorted in Descending");
			
			boolean sortIsEnableAsc = diep.openAndApplySettingsForColumn(ColumnNames.IS_ENABLE, COLUMN_SETTING.SORTASCENDING, null);
			TestValidation.IsTrue(sortIsEnableAsc, 
								  "For Is Enable column, APPLIED the column setting as " + COLUMN_SETTING.SORTASCENDING, 
								  "Failed to apply the column setting as " + COLUMN_SETTING.SORTASCENDING 
								  	+ " for Is Enable column");
			
			String verifySortIsEnableAsc = CommonMethods.verifySortingForColumn(diep.getListOfElementsForDPTExtractData(
										ColumnNames.IS_ENABLE));
			TestValidation.IsTrue(verifySortIsEnableAsc.equals(SORTING.ASC) || verifySortIsEnableAsc.equals(SORTING.EQL), 
								  "VERIFIED values of the column Is Enable are sorted in Ascending", 
								  "Failed to verify that values of the column Is Enable are sorted in Ascending");
			
			boolean sortIsEnableDsc = diep.openAndApplySettingsForColumn(ColumnNames.IS_ENABLE, COLUMN_SETTING.SORTDESCENDING, null);
			TestValidation.IsTrue(sortIsEnableDsc, 
								  "For Is Enable column, APPLIED the column setting as " + COLUMN_SETTING.SORTDESCENDING, 
								  "Failed to apply the column setting as " + COLUMN_SETTING.SORTDESCENDING 
								  + " for Is Enable column");
			
			String verifySortIsEnableDsc = CommonMethods.verifySortingForColumn(diep.getListOfElementsForDPTExtractData(
										ColumnNames.IS_ENABLE));
			TestValidation.IsTrue(verifySortIsEnableDsc.equals(SORTING.DSC) || verifySortIsEnableDsc.equals(SORTING.EQL), 
								  "VERIFIED values of the column Is Enable are sorted in Descending", 
								  "Failed to verify that values of the column Is Enable are sorted in Descending");
			
		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				TestValidation.Fail(TCGFailureMsg);
			}
		}
	}
	
	@AfterClass(alwaysRun = true)
	public void closeBrowser() throws InterruptedException {
		driver.close();
	}
}
