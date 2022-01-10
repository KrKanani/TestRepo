package com.project.safetychain.webapp.testcases;

import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.project.safetychain.webapp.pageobjects.HomePage;
import com.project.safetychain.webapp.pageobjects.LoginPage;
import com.project.safetychain.webapp.pageobjects.DataImportExportPage.ExtractType;
import com.project.safetychain.webapp.pageobjects.DataImportExportPage;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.utilities.ControlActions;
import com.project.utilities.DateTime;

public class TCG_REG_DPT_GenericFlows2 extends TestBase{
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
	
	@Test(description = "DPT || Export>Data Extract - Verify the pagination for customer resource.")
	public void TestCase_38558() {
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
			
			String frstBtnClass1 = diep.DataExtractPgnFrstBtn.getAttribute("class");
			boolean verifyFrstBtnClass1 = frstBtnClass1.contains("disabled");
			TestValidation.IsTrue(verifyFrstBtnClass1, 
								  "VERIFIED that the First button pagination is disabled", 
								  "Failed to Verify whether the First button pagination is disabled or not");
			
			String prvsBtnClass1 = diep.DataExtractPgnPrvsBtn.getAttribute("class");
			boolean verifyPrvsBtnClass1 = prvsBtnClass1.contains("disabled");
			TestValidation.IsTrue(verifyPrvsBtnClass1, 
								  "VERIFIED that the Previous button pagination is disabled", 
								  "Failed to Verify whether the Previous button pagination is disabled or not");
			
			int rowCount = diep.DataExtractPopupRowsTbl.size();
			if(rowCount == 25) {
				
				boolean clickNextBtn = diep.clickDataExtractNextPgNav();
				TestValidation.IsTrue(clickNextBtn, 
									  "CLICKED the Next pagination button", 
									  "Failed to Click the Next pagination button");
				
				String frstBtnClass2 = diep.DataExtractPgnFrstBtn.getAttribute("class");
				boolean verifyFrstBtnClass2 = frstBtnClass2.contains("disabled");
				TestValidation.IsFalse(verifyFrstBtnClass2, 
									  "VERIFIED that the First button pagination is not disabled now", 
									  "Failed to Verify whether the First button pagination is disabled or not");
				
				String prvsBtnClass2 = diep.DataExtractPgnPrvsBtn.getAttribute("class");
				boolean verifyPrvsBtnClass2 = prvsBtnClass2.contains("disabled");
				TestValidation.IsFalse(verifyPrvsBtnClass2, 
									  "VERIFIED that the Previous button pagination is not disabled now", 
									  "Failed to Verify whether the Previous button pagination is disabled or not");
				
			}
			else if(rowCount < 25) {
				
				String nxtBtnClass = diep.DataExtractPgnFrstBtn.getAttribute("class");
				boolean verifyNxtBtnClass = nxtBtnClass.contains("disabled");
				TestValidation.IsTrue(verifyNxtBtnClass, 
									  "VERIFIED that the Next button pagination is disabled as entries are lesser than 25", 
									  "Failed to Verify whether the Next button pagination is disabled or not");
				
			}
			else {
				
				TestValidation.Fail("Failed as the row count found is " + rowCount);
				
			}
		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				TestValidation.Fail(TCGFailureMsg);
			}
		}
	}
	
	@Test(description = "DPT || Export>Data Extract - Verify the pagination for suppliers resource.")
	public void TestCase_38561() {
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
			
			String frstBtnClass1 = diep.DataExtractPgnFrstBtn.getAttribute("class");
			boolean verifyFrstBtnClass1 = frstBtnClass1.contains("disabled");
			TestValidation.IsTrue(verifyFrstBtnClass1, 
								  "VERIFIED that the First button pagination is disabled", 
								  "Failed to Verify whether the First button pagination is disabled or not");
			
			String prvsBtnClass1 = diep.DataExtractPgnPrvsBtn.getAttribute("class");
			boolean verifyPrvsBtnClass1 = prvsBtnClass1.contains("disabled");
			TestValidation.IsTrue(verifyPrvsBtnClass1, 
								  "VERIFIED that the Previous button pagination is disabled", 
								  "Failed to Verify whether the Previous button pagination is disabled or not");
			
			int rowCount = diep.DataExtractPopupRowsTbl.size();
			if(rowCount == 25) {
				
				boolean clickNextBtn = diep.clickDataExtractNextPgNav();
				TestValidation.IsTrue(clickNextBtn, 
									  "CLICKED the Next pagination button", 
									  "Failed to Click the Next pagination button");
				
				String frstBtnClass2 = diep.DataExtractPgnFrstBtn.getAttribute("class");
				boolean verifyFrstBtnClass2 = frstBtnClass2.contains("disabled");
				TestValidation.IsFalse(verifyFrstBtnClass2, 
									  "VERIFIED that the First button pagination is not disabled now", 
									  "Failed to Verify whether the First button pagination is disabled or not");
				
				String prvsBtnClass2 = diep.DataExtractPgnPrvsBtn.getAttribute("class");
				boolean verifyPrvsBtnClass2 = prvsBtnClass2.contains("disabled");
				TestValidation.IsFalse(verifyPrvsBtnClass2, 
									  "VERIFIED that the Previous button pagination is not disabled now", 
									  "Failed to Verify whether the Previous button pagination is disabled or not");
				
				boolean clickPrvsBtn = diep.clickDataExtractPreviousPgNav();
				TestValidation.IsTrue(clickPrvsBtn, 
									  "CLICKED the Previous pagination button", 
									  "Failed to Click the Previous pagination button");
				
				String frstBtnClass3 = diep.DataExtractPgnFrstBtn.getAttribute("class");
				boolean verifyFrstBtnClass3 = frstBtnClass3.contains("disabled");
				TestValidation.IsTrue(verifyFrstBtnClass3, 
									  "VERIFIED that the First button pagination is disabled", 
									  "Failed to Verify whether the First button pagination is disabled or not");
				
				String prvsBtnClass3 = diep.DataExtractPgnPrvsBtn.getAttribute("class");
				boolean verifyPrvsBtnClass3 = prvsBtnClass3.contains("disabled");
				TestValidation.IsTrue(verifyPrvsBtnClass3, 
									  "VERIFIED that the Previous button pagination is disabled", 
									  "Failed to Verify whether the Previous button pagination is disabled or not");
				
				String nextBtnClass = diep.DataExtractPgnNxtBtn.getAttribute("class");
				boolean verifyNextBtnClass = nextBtnClass.contains("disabled");
				TestValidation.IsFalse(verifyNextBtnClass, 
									  "VERIFIED that the Previous button pagination is not disabled", 
									  "Failed to Verify whether the Previous button pagination is disabled or not");
				
			}
			else if(rowCount < 25) {
				
				String nxtBtnClass = diep.DataExtractPgnFrstBtn.getAttribute("class");
				boolean verifyNxtBtnClass = nxtBtnClass.contains("disabled");
				TestValidation.IsTrue(verifyNxtBtnClass, 
									  "VERIFIED that the Next button pagination is disabled as entries are lesser than 25", 
									  "Failed to Verify whether the Next button pagination is disabled or not");
				
			}
			else {
				
				TestValidation.Fail("Failed as the row count found is " + rowCount);
				
			}
		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				TestValidation.Fail(TCGFailureMsg);
			}
		}
	}
	
	@Test(description = "DPT || Export>Data Extract - Verify the pagination for items resource.")
	public void TestCase_38560() {
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
			
			String frstBtnClass1 = diep.DataExtractPgnFrstBtn.getAttribute("class");
			boolean verifyFrstBtnClass1 = frstBtnClass1.contains("disabled");
			TestValidation.IsTrue(verifyFrstBtnClass1, 
								  "VERIFIED that the First button pagination is disabled", 
								  "Failed to Verify whether the First button pagination is disabled or not");
			
			String prvsBtnClass1 = diep.DataExtractPgnPrvsBtn.getAttribute("class");
			boolean verifyPrvsBtnClass1 = prvsBtnClass1.contains("disabled");
			TestValidation.IsTrue(verifyPrvsBtnClass1, 
								  "VERIFIED that the Previous button pagination is disabled", 
								  "Failed to Verify whether the Previous button pagination is disabled or not");
			
			int rowCount = diep.DataExtractPopupRowsTbl.size();
			if(rowCount == 25) {
				
				boolean clickNextBtn = diep.clickDataExtractNextPgNav(2);
				TestValidation.IsTrue(clickNextBtn, 
									  "CLICKED the Next pagination button", 
									  "Failed to Click the Next pagination button");
				
				String frstBtnClass2 = diep.DataExtractPgnFrstBtn.getAttribute("class");
				boolean verifyFrstBtnClass2 = frstBtnClass2.contains("disabled");
				TestValidation.IsFalse(verifyFrstBtnClass2, 
									  "VERIFIED that the First button pagination is not disabled now", 
									  "Failed to Verify whether the First button pagination is disabled or not");
				
				String prvsBtnClass2 = diep.DataExtractPgnPrvsBtn.getAttribute("class");
				boolean verifyPrvsBtnClass2 = prvsBtnClass2.contains("disabled");
				TestValidation.IsFalse(verifyPrvsBtnClass2, 
									  "VERIFIED that the Previous button pagination is not disabled now", 
									  "Failed to Verify whether the Previous button pagination is disabled or not");
				
				boolean clickFirstBtn = diep.clickDataExtractFirstPgNav();
				TestValidation.IsTrue(clickFirstBtn, 
									  "CLICKED the First pagination button", 
									  "Failed to Click the First pagination button");
				
				String frstBtnClass3 = diep.DataExtractPgnFrstBtn.getAttribute("class");
				boolean verifyFrstBtnClass3 = frstBtnClass3.contains("disabled");
				TestValidation.IsTrue(verifyFrstBtnClass3, 
									  "VERIFIED that the First button pagination is disabled", 
									  "Failed to Verify whether the First button pagination is disabled or not");
				
				String prvsBtnClass3 = diep.DataExtractPgnPrvsBtn.getAttribute("class");
				boolean verifyPrvsBtnClass3 = prvsBtnClass3.contains("disabled");
				TestValidation.IsTrue(verifyPrvsBtnClass3, 
									  "VERIFIED that the Previous button pagination is disabled", 
									  "Failed to Verify whether the Previous button pagination is disabled or not");
				
				String nextBtnClass = diep.DataExtractPgnNxtBtn.getAttribute("class");
				boolean verifyNextBtnClass = nextBtnClass.contains("disabled");
				TestValidation.IsFalse(verifyNextBtnClass, 
									  "VERIFIED that the Previous button pagination is not disabled", 
									  "Failed to Verify whether the Previous button pagination is disabled or not");
				
			}
			else if(rowCount < 25) {
				
				String nxtBtnClass = diep.DataExtractPgnFrstBtn.getAttribute("class");
				boolean verifyNxtBtnClass = nxtBtnClass.contains("disabled");
				TestValidation.IsTrue(verifyNxtBtnClass, 
									  "VERIFIED that the Next button pagination is disabled as entries are lesser than 25", 
									  "Failed to Verify whether the Next button pagination is disabled or not");
				
			}
			else {
				
				TestValidation.Fail("Failed as the row count found is " + rowCount);
				
			}
		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				TestValidation.Fail(TCGFailureMsg);
			}
		}
	}
	
	@Test(description = "DPT || Export>Data Extract - Verify the pagination for equipment resource.")
	public void TestCase_38559() {
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
			
			String frstBtnClass1 = diep.DataExtractPgnFrstBtn.getAttribute("class");
			boolean verifyFrstBtnClass1 = frstBtnClass1.contains("disabled");
			TestValidation.IsTrue(verifyFrstBtnClass1, 
								  "VERIFIED that the First button pagination is disabled", 
								  "Failed to Verify whether the First button pagination is disabled or not");
			
			String prvsBtnClass1 = diep.DataExtractPgnPrvsBtn.getAttribute("class");
			boolean verifyPrvsBtnClass1 = prvsBtnClass1.contains("disabled");
			TestValidation.IsTrue(verifyPrvsBtnClass1, 
								  "VERIFIED that the Previous button pagination is disabled", 
								  "Failed to Verify whether the Previous button pagination is disabled or not");
			
			int rowCount = diep.DataExtractPopupRowsTbl.size();
			if(rowCount == 25) {
				
				int clickNextBtnCnt = diep.verifyDataExtractNextPgNavClicked(2);
				TestValidation.IsTrue(clickNextBtnCnt!=0, 
									  "CLICKED the Next pagination button", 
									  "Failed to Click the Next pagination button");
				
				String frstBtnClass2 = diep.DataExtractPgnFrstBtn.getAttribute("class");
				boolean verifyFrstBtnClass2 = frstBtnClass2.contains("disabled");
				TestValidation.IsFalse(verifyFrstBtnClass2, 
									  "VERIFIED that the First button pagination is not disabled now", 
									  "Failed to Verify whether the First button pagination is disabled or not");
				
				String prvsBtnClass2 = diep.DataExtractPgnPrvsBtn.getAttribute("class");
				boolean verifyPrvsBtnClass2 = prvsBtnClass2.contains("disabled");
				TestValidation.IsFalse(verifyPrvsBtnClass2, 
									  "VERIFIED that the Previous button pagination is not disabled now", 
									  "Failed to Verify whether the Previous button pagination is disabled or not");
				
				boolean clickPreviousBtn = diep.clickDataExtractPreviousPgNav();
				TestValidation.IsTrue(clickPreviousBtn, 
									  "CLICKED the Previous pagination button", 
									  "Failed to Click the Previous pagination button");
				
				if(clickNextBtnCnt == 1) {
					
					String frstBtnClass3 = diep.DataExtractPgnFrstBtn.getAttribute("class");
					boolean verifyFrstBtnClass3 = frstBtnClass3.contains("disabled");
					TestValidation.IsTrue(verifyFrstBtnClass3, 
										  "VERIFIED that the First button pagination is disabled", 
										  "Failed to Verify whether the First button pagination is disabled or not");
					
					String prvsBtnClass3 = diep.DataExtractPgnPrvsBtn.getAttribute("class");
					boolean verifyPrvsBtnClass3 = prvsBtnClass3.contains("disabled");
					TestValidation.IsTrue(verifyPrvsBtnClass3, 
										  "VERIFIED that the Previous button pagination is disabled", 
										  "Failed to Verify whether the Previous button pagination is disabled or not");
					
					String nextBtnClass = diep.DataExtractPgnNxtBtn.getAttribute("class");
					boolean verifyNextBtnClass = nextBtnClass.contains("disabled");
					TestValidation.IsFalse(verifyNextBtnClass, 
										  "VERIFIED that the Previous button pagination is not disabled", 
										  "Failed to Verify whether the Previous button pagination is disabled or not");
				}
				else if(clickNextBtnCnt > 1) {
					
					String frstBtnClass3 = diep.DataExtractPgnFrstBtn.getAttribute("class");
					boolean verifyFrstBtnClass3 = frstBtnClass3.contains("disabled");
					TestValidation.IsFalse(verifyFrstBtnClass3, 
										  "VERIFIED that the First button pagination is not disabled", 
										  "Failed to Verify whether the First button pagination is disabled or not");
					
					String prvsBtnClass3 = diep.DataExtractPgnPrvsBtn.getAttribute("class");
					boolean verifyPrvsBtnClass3 = prvsBtnClass3.contains("disabled");
					TestValidation.IsFalse(verifyPrvsBtnClass3, 
										  "VERIFIED that the Previous button pagination is not disabled", 
										  "Failed to Verify whether the Previous button pagination is disabled or not");
					
					String nextBtnClass = diep.DataExtractPgnNxtBtn.getAttribute("class");
					boolean verifyNextBtnClass = nextBtnClass.contains("disabled");
					TestValidation.IsFalse(verifyNextBtnClass, 
										  "VERIFIED that the Previous button pagination is not disabled", 
										  "Failed to Verify whether the Previous button pagination is disabled or not");
				}
			}
			else if(rowCount < 25) {
				
				String nxtBtnClass = diep.DataExtractPgnFrstBtn.getAttribute("class");
				boolean verifyNxtBtnClass = nxtBtnClass.contains("disabled");
				TestValidation.IsTrue(verifyNxtBtnClass, 
									  "VERIFIED that the Next button pagination is disabled as entries are lesser than 25", 
									  "Failed to Verify whether the Next button pagination is disabled or not");
				
			}
			else {
				
				TestValidation.Fail("Failed as the row count found is " + rowCount);
				
			}
		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				TestValidation.Fail(TCGFailureMsg);
			}
		}
	}
	
	@Test(description = "DPT || Export>Data Extract - Verify the pagination for users.")
	public void TestCase_38556() {
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
			
			String frstBtnClass1 = diep.DataExtractPgnFrstBtn.getAttribute("class");
			boolean verifyFrstBtnClass1 = frstBtnClass1.contains("disabled");
			TestValidation.IsTrue(verifyFrstBtnClass1, 
								  "VERIFIED that the First button pagination is disabled", 
								  "Failed to Verify whether the First button pagination is disabled or not");
			
			String prvsBtnClass1 = diep.DataExtractPgnPrvsBtn.getAttribute("class");
			boolean verifyPrvsBtnClass1 = prvsBtnClass1.contains("disabled");
			TestValidation.IsTrue(verifyPrvsBtnClass1, 
								  "VERIFIED that the Previous button pagination is disabled", 
								  "Failed to Verify whether the Previous button pagination is disabled or not");
			
			int rowCount = diep.DataExtractPopupRowsTbl.size();
			if(rowCount == 25) {
				
				boolean clickNextBtn = diep.clickDataExtractNextPgNav();
				TestValidation.IsTrue(clickNextBtn, 
									  "CLICKED the Next pagination button", 
									  "Failed to Click the Next pagination button");
				
				String frstBtnClass2 = diep.DataExtractPgnFrstBtn.getAttribute("class");
				boolean verifyFrstBtnClass2 = frstBtnClass2.contains("disabled");
				TestValidation.IsFalse(verifyFrstBtnClass2, 
									  "VERIFIED that the First button pagination is not disabled now", 
									  "Failed to Verify whether the First button pagination is disabled or not");
				
				String prvsBtnClass2 = diep.DataExtractPgnPrvsBtn.getAttribute("class");
				boolean verifyPrvsBtnClass2 = prvsBtnClass2.contains("disabled");
				TestValidation.IsFalse(verifyPrvsBtnClass2, 
									  "VERIFIED that the Previous button pagination is not disabled now", 
									  "Failed to Verify whether the Previous button pagination is disabled or not");
				
			}
			else if(rowCount < 25) {
				
				String nxtBtnClass = diep.DataExtractPgnFrstBtn.getAttribute("class");
				boolean verifyNxtBtnClass = nxtBtnClass.contains("disabled");
				TestValidation.IsTrue(verifyNxtBtnClass, 
									  "VERIFIED that the Next button pagination is disabled as entries are lesser than 25", 
									  "Failed to Verify whether the Next button pagination is disabled or not");
				
			}
			else {
				
				TestValidation.Fail("Failed as the row count found is " + rowCount);
				
			}
		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				TestValidation.Fail(TCGFailureMsg);
			}
		}
	}
	
	@Test(description = "DPT || Export>Data Extract - Verify the pagination for control limits.")
	public void TestCase_38553() {
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
			
			String frstBtnClass1 = diep.DataExtractPgnFrstBtn.getAttribute("class");
			boolean verifyFrstBtnClass1 = frstBtnClass1.contains("disabled");
			TestValidation.IsTrue(verifyFrstBtnClass1, 
								  "VERIFIED that the First button pagination is disabled", 
								  "Failed to Verify whether the First button pagination is disabled or not");
			
			String prvsBtnClass1 = diep.DataExtractPgnPrvsBtn.getAttribute("class");
			boolean verifyPrvsBtnClass1 = prvsBtnClass1.contains("disabled");
			TestValidation.IsTrue(verifyPrvsBtnClass1, 
								  "VERIFIED that the Previous button pagination is disabled", 
								  "Failed to Verify whether the Previous button pagination is disabled or not");
			
			int rowCount = diep.DataExtractPopupRowsTbl.size();
			if(rowCount == 25) {
				
				boolean clickNextBtn = diep.clickDataExtractNextPgNav();
				TestValidation.IsTrue(clickNextBtn, 
									  "CLICKED the Next pagination button", 
									  "Failed to Click the Next pagination button");
				
				String frstBtnClass2 = diep.DataExtractPgnFrstBtn.getAttribute("class");
				boolean verifyFrstBtnClass2 = frstBtnClass2.contains("disabled");
				TestValidation.IsFalse(verifyFrstBtnClass2, 
									  "VERIFIED that the First button pagination is not disabled now", 
									  "Failed to Verify whether the First button pagination is disabled or not");
				
				String prvsBtnClass2 = diep.DataExtractPgnPrvsBtn.getAttribute("class");
				boolean verifyPrvsBtnClass2 = prvsBtnClass2.contains("disabled");
				TestValidation.IsFalse(verifyPrvsBtnClass2, 
									  "VERIFIED that the Previous button pagination is not disabled now", 
									  "Failed to Verify whether the Previous button pagination is disabled or not");
				
				boolean clickPrvsBtn = diep.clickDataExtractPreviousPgNav();
				TestValidation.IsTrue(clickPrvsBtn, 
									  "CLICKED the Previous pagination button", 
									  "Failed to Click the Previous pagination button");
				
				String frstBtnClass3 = diep.DataExtractPgnFrstBtn.getAttribute("class");
				boolean verifyFrstBtnClass3 = frstBtnClass3.contains("disabled");
				TestValidation.IsTrue(verifyFrstBtnClass3, 
									  "VERIFIED that the First button pagination is disabled", 
									  "Failed to Verify whether the First button pagination is disabled or not");
				
				String prvsBtnClass3 = diep.DataExtractPgnPrvsBtn.getAttribute("class");
				boolean verifyPrvsBtnClass3 = prvsBtnClass3.contains("disabled");
				TestValidation.IsTrue(verifyPrvsBtnClass3, 
									  "VERIFIED that the Previous button pagination is disabled", 
									  "Failed to Verify whether the Previous button pagination is disabled or not");
				
				String nextBtnClass = diep.DataExtractPgnNxtBtn.getAttribute("class");
				boolean verifyNextBtnClass = nextBtnClass.contains("disabled");
				TestValidation.IsFalse(verifyNextBtnClass, 
									  "VERIFIED that the Previous button pagination is not disabled", 
									  "Failed to Verify whether the Previous button pagination is disabled or not");
				
			}
			else if(rowCount < 25) {
				
				String nxtBtnClass = diep.DataExtractPgnFrstBtn.getAttribute("class");
				boolean verifyNxtBtnClass = nxtBtnClass.contains("disabled");
				TestValidation.IsTrue(verifyNxtBtnClass, 
									  "VERIFIED that the Next button pagination is disabled as entries are lesser than 25", 
									  "Failed to Verify whether the Next button pagination is disabled or not");
				
			}
			else {
				
				TestValidation.Fail("Failed as the row count found is " + rowCount);
				
			}
		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				TestValidation.Fail(TCGFailureMsg);
			}
		}
	}
	
	@Test(description = "DPT || Export>Data Extract - Verify the pagination for form compliance.")
	public void TestCase_24272() {
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
			
			String frstBtnClass1 = diep.DataExtractPgnFrstBtn.getAttribute("class");
			boolean verifyFrstBtnClass1 = frstBtnClass1.contains("disabled");
			TestValidation.IsTrue(verifyFrstBtnClass1, 
								  "VERIFIED that the First button pagination is disabled", 
								  "Failed to Verify whether the First button pagination is disabled or not");
			
			String prvsBtnClass1 = diep.DataExtractPgnPrvsBtn.getAttribute("class");
			boolean verifyPrvsBtnClass1 = prvsBtnClass1.contains("disabled");
			TestValidation.IsTrue(verifyPrvsBtnClass1, 
								  "VERIFIED that the Previous button pagination is disabled", 
								  "Failed to Verify whether the Previous button pagination is disabled or not");
			
			int rowCount = diep.DataExtractPopupRowsTbl.size();
			if(rowCount == 25) {
				
				boolean clickNextBtn = diep.clickDataExtractNextPgNav(2);
				TestValidation.IsTrue(clickNextBtn, 
									  "CLICKED the Next pagination button", 
									  "Failed to Click the Next pagination button");
				
				String frstBtnClass2 = diep.DataExtractPgnFrstBtn.getAttribute("class");
				boolean verifyFrstBtnClass2 = frstBtnClass2.contains("disabled");
				TestValidation.IsFalse(verifyFrstBtnClass2, 
									  "VERIFIED that the First button pagination is not disabled now", 
									  "Failed to Verify whether the First button pagination is disabled or not");
				
				String prvsBtnClass2 = diep.DataExtractPgnPrvsBtn.getAttribute("class");
				boolean verifyPrvsBtnClass2 = prvsBtnClass2.contains("disabled");
				TestValidation.IsFalse(verifyPrvsBtnClass2, 
									  "VERIFIED that the Previous button pagination is not disabled now", 
									  "Failed to Verify whether the Previous button pagination is disabled or not");
				
				boolean clickFirstBtn = diep.clickDataExtractFirstPgNav();
				TestValidation.IsTrue(clickFirstBtn, 
									  "CLICKED the First pagination button", 
									  "Failed to Click the First pagination button");
				
				String frstBtnClass3 = diep.DataExtractPgnFrstBtn.getAttribute("class");
				boolean verifyFrstBtnClass3 = frstBtnClass3.contains("disabled");
				TestValidation.IsTrue(verifyFrstBtnClass3, 
									  "VERIFIED that the First button pagination is disabled", 
									  "Failed to Verify whether the First button pagination is disabled or not");
				
				String prvsBtnClass3 = diep.DataExtractPgnPrvsBtn.getAttribute("class");
				boolean verifyPrvsBtnClass3 = prvsBtnClass3.contains("disabled");
				TestValidation.IsTrue(verifyPrvsBtnClass3, 
									  "VERIFIED that the Previous button pagination is disabled", 
									  "Failed to Verify whether the Previous button pagination is disabled or not");
				
				String nextBtnClass = diep.DataExtractPgnNxtBtn.getAttribute("class");
				boolean verifyNextBtnClass = nextBtnClass.contains("disabled");
				TestValidation.IsFalse(verifyNextBtnClass, 
									  "VERIFIED that the Previous button pagination is not disabled", 
									  "Failed to Verify whether the Previous button pagination is disabled or not");
				
			}
			else if(rowCount < 25) {
				
				String nxtBtnClass = diep.DataExtractPgnFrstBtn.getAttribute("class");
				boolean verifyNxtBtnClass = nxtBtnClass.contains("disabled");
				TestValidation.IsTrue(verifyNxtBtnClass, 
									  "VERIFIED that the Next button pagination is disabled as entries are lesser than 25", 
									  "Failed to Verify whether the Next button pagination is disabled or not");
				
			}
			else {
				
				TestValidation.Fail("Failed as the row count found is " + rowCount);
				
			}
		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				TestValidation.Fail(TCGFailureMsg);
			}
		}
	}
	
	@Test(description = "DPT || Export>Data Extract - Verify the pagination for form definition.")
	public void TestCase_38551() {
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
			
			String frstBtnClass1 = diep.DataExtractPgnFrstBtn.getAttribute("class");
			boolean verifyFrstBtnClass1 = frstBtnClass1.contains("disabled");
			TestValidation.IsTrue(verifyFrstBtnClass1, 
								  "VERIFIED that the First button pagination is disabled", 
								  "Failed to Verify whether the First button pagination is disabled or not");
			
			String prvsBtnClass1 = diep.DataExtractPgnPrvsBtn.getAttribute("class");
			boolean verifyPrvsBtnClass1 = prvsBtnClass1.contains("disabled");
			TestValidation.IsTrue(verifyPrvsBtnClass1, 
								  "VERIFIED that the Previous button pagination is disabled", 
								  "Failed to Verify whether the Previous button pagination is disabled or not");
			
			int rowCount = diep.DataExtractPopupRowsTbl.size();
			if(rowCount == 25) {
				
				int clickNextBtnCnt = diep.verifyDataExtractNextPgNavClicked(2);
				TestValidation.IsTrue(clickNextBtnCnt!=0, 
									  "CLICKED the Next pagination button", 
									  "Failed to Click the Next pagination button");
				
				String frstBtnClass2 = diep.DataExtractPgnFrstBtn.getAttribute("class");
				boolean verifyFrstBtnClass2 = frstBtnClass2.contains("disabled");
				TestValidation.IsFalse(verifyFrstBtnClass2, 
									  "VERIFIED that the First button pagination is not disabled now", 
									  "Failed to Verify whether the First button pagination is disabled or not");
				
				String prvsBtnClass2 = diep.DataExtractPgnPrvsBtn.getAttribute("class");
				boolean verifyPrvsBtnClass2 = prvsBtnClass2.contains("disabled");
				TestValidation.IsFalse(verifyPrvsBtnClass2, 
									  "VERIFIED that the Previous button pagination is not disabled now", 
									  "Failed to Verify whether the Previous button pagination is disabled or not");
				
				boolean clickPreviousBtn = diep.clickDataExtractPreviousPgNav();
				TestValidation.IsTrue(clickPreviousBtn, 
									  "CLICKED the Previous pagination button", 
									  "Failed to Click the Previous pagination button");
				
				if(clickNextBtnCnt == 1) {
					
					String frstBtnClass3 = diep.DataExtractPgnFrstBtn.getAttribute("class");
					boolean verifyFrstBtnClass3 = frstBtnClass3.contains("disabled");
					TestValidation.IsTrue(verifyFrstBtnClass3, 
										  "VERIFIED that the First button pagination is disabled", 
										  "Failed to Verify whether the First button pagination is disabled or not");
					
					String prvsBtnClass3 = diep.DataExtractPgnPrvsBtn.getAttribute("class");
					boolean verifyPrvsBtnClass3 = prvsBtnClass3.contains("disabled");
					TestValidation.IsTrue(verifyPrvsBtnClass3, 
										  "VERIFIED that the Previous button pagination is disabled", 
										  "Failed to Verify whether the Previous button pagination is disabled or not");
					
					String nextBtnClass = diep.DataExtractPgnNxtBtn.getAttribute("class");
					boolean verifyNextBtnClass = nextBtnClass.contains("disabled");
					TestValidation.IsFalse(verifyNextBtnClass, 
										  "VERIFIED that the Previous button pagination is not disabled", 
										  "Failed to Verify whether the Previous button pagination is disabled or not");
				}
				else if(clickNextBtnCnt > 1) {
					
					String frstBtnClass3 = diep.DataExtractPgnFrstBtn.getAttribute("class");
					boolean verifyFrstBtnClass3 = frstBtnClass3.contains("disabled");
					TestValidation.IsFalse(verifyFrstBtnClass3, 
										  "VERIFIED that the First button pagination is not disabled", 
										  "Failed to Verify whether the First button pagination is disabled or not");
					
					String prvsBtnClass3 = diep.DataExtractPgnPrvsBtn.getAttribute("class");
					boolean verifyPrvsBtnClass3 = prvsBtnClass3.contains("disabled");
					TestValidation.IsFalse(verifyPrvsBtnClass3, 
										  "VERIFIED that the Previous button pagination is not disabled", 
										  "Failed to Verify whether the Previous button pagination is disabled or not");
					
					String nextBtnClass = diep.DataExtractPgnNxtBtn.getAttribute("class");
					boolean verifyNextBtnClass = nextBtnClass.contains("disabled");
					TestValidation.IsFalse(verifyNextBtnClass, 
										  "VERIFIED that the Previous button pagination is not disabled", 
										  "Failed to Verify whether the Previous button pagination is disabled or not");
				}
			}
			else if(rowCount < 25) {
				
				String nxtBtnClass = diep.DataExtractPgnFrstBtn.getAttribute("class");
				boolean verifyNxtBtnClass = nxtBtnClass.contains("disabled");
				TestValidation.IsTrue(verifyNxtBtnClass, 
									  "VERIFIED that the Next button pagination is disabled as entries are lesser than 25", 
									  "Failed to Verify whether the Next button pagination is disabled or not");
				
			}
			else {
				
				TestValidation.Fail("Failed as the row count found is " + rowCount);
				
			}
		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				TestValidation.Fail(TCGFailureMsg);
			}
		}
	}
	
	@Test(description = "DPT || Export>Data Extract - Verify the pagination for roles.")
	public void TestCase_38555() {
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
			
			String frstBtnClass1 = diep.DataExtractPgnFrstBtn.getAttribute("class");
			boolean verifyFrstBtnClass1 = frstBtnClass1.contains("disabled");
			TestValidation.IsTrue(verifyFrstBtnClass1, 
								  "VERIFIED that the First button pagination is disabled", 
								  "Failed to Verify whether the First button pagination is disabled or not");
			
			String prvsBtnClass1 = diep.DataExtractPgnPrvsBtn.getAttribute("class");
			boolean verifyPrvsBtnClass1 = prvsBtnClass1.contains("disabled");
			TestValidation.IsTrue(verifyPrvsBtnClass1, 
								  "VERIFIED that the Previous button pagination is disabled", 
								  "Failed to Verify whether the Previous button pagination is disabled or not");
			
			int rowCount = diep.DataExtractPopupRowsTbl.size();
			if(rowCount == 25) {
				
				boolean clickNextBtn = diep.clickDataExtractNextPgNav();
				TestValidation.IsTrue(clickNextBtn, 
									  "CLICKED the Next pagination button", 
									  "Failed to Click the Next pagination button");
				
				String frstBtnClass2 = diep.DataExtractPgnFrstBtn.getAttribute("class");
				boolean verifyFrstBtnClass2 = frstBtnClass2.contains("disabled");
				TestValidation.IsFalse(verifyFrstBtnClass2, 
									  "VERIFIED that the First button pagination is not disabled now", 
									  "Failed to Verify whether the First button pagination is disabled or not");
				
				String prvsBtnClass2 = diep.DataExtractPgnPrvsBtn.getAttribute("class");
				boolean verifyPrvsBtnClass2 = prvsBtnClass2.contains("disabled");
				TestValidation.IsFalse(verifyPrvsBtnClass2, 
									  "VERIFIED that the Previous button pagination is not disabled now", 
									  "Failed to Verify whether the Previous button pagination is disabled or not");
				
			}
			else if(rowCount < 25) {
				
				String nxtBtnClass = diep.DataExtractPgnFrstBtn.getAttribute("class");
				boolean verifyNxtBtnClass = nxtBtnClass.contains("disabled");
				TestValidation.IsTrue(verifyNxtBtnClass, 
									  "VERIFIED that the Next button pagination is disabled as entries are lesser than 25", 
									  "Failed to Verify whether the Next button pagination is disabled or not");
				
			}
			else {
				
				TestValidation.Fail("Failed as the row count found is " + rowCount);
				
			}
		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				TestValidation.Fail(TCGFailureMsg);
			}
		}
	}
	
	@Test(description = "DPT || Export>Data Extract - Verify the pagination for workgroups.")
	public void TestCase_38554() {
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
			
			String frstBtnClass1 = diep.DataExtractPgnFrstBtn.getAttribute("class");
			boolean verifyFrstBtnClass1 = frstBtnClass1.contains("disabled");
			TestValidation.IsTrue(verifyFrstBtnClass1, 
								  "VERIFIED that the First button pagination is disabled", 
								  "Failed to Verify whether the First button pagination is disabled or not");
			
			String prvsBtnClass1 = diep.DataExtractPgnPrvsBtn.getAttribute("class");
			boolean verifyPrvsBtnClass1 = prvsBtnClass1.contains("disabled");
			TestValidation.IsTrue(verifyPrvsBtnClass1, 
								  "VERIFIED that the Previous button pagination is disabled", 
								  "Failed to Verify whether the Previous button pagination is disabled or not");
			
			int rowCount = diep.DataExtractPopupRowsTbl.size();
			if(rowCount == 25) {
				
				boolean clickNextBtn = diep.clickDataExtractNextPgNav();
				TestValidation.IsTrue(clickNextBtn, 
									  "CLICKED the Next pagination button", 
									  "Failed to Click the Next pagination button");
				
				String frstBtnClass2 = diep.DataExtractPgnFrstBtn.getAttribute("class");
				boolean verifyFrstBtnClass2 = frstBtnClass2.contains("disabled");
				TestValidation.IsFalse(verifyFrstBtnClass2, 
									  "VERIFIED that the First button pagination is not disabled now", 
									  "Failed to Verify whether the First button pagination is disabled or not");
				
				String prvsBtnClass2 = diep.DataExtractPgnPrvsBtn.getAttribute("class");
				boolean verifyPrvsBtnClass2 = prvsBtnClass2.contains("disabled");
				TestValidation.IsFalse(verifyPrvsBtnClass2, 
									  "VERIFIED that the Previous button pagination is not disabled now", 
									  "Failed to Verify whether the Previous button pagination is disabled or not");
				
				boolean clickPrvsBtn = diep.clickDataExtractPreviousPgNav();
				TestValidation.IsTrue(clickPrvsBtn, 
									  "CLICKED the Previous pagination button", 
									  "Failed to Click the Previous pagination button");
				
				String frstBtnClass3 = diep.DataExtractPgnFrstBtn.getAttribute("class");
				boolean verifyFrstBtnClass3 = frstBtnClass3.contains("disabled");
				TestValidation.IsTrue(verifyFrstBtnClass3, 
									  "VERIFIED that the First button pagination is disabled", 
									  "Failed to Verify whether the First button pagination is disabled or not");
				
				String prvsBtnClass3 = diep.DataExtractPgnPrvsBtn.getAttribute("class");
				boolean verifyPrvsBtnClass3 = prvsBtnClass3.contains("disabled");
				TestValidation.IsTrue(verifyPrvsBtnClass3, 
									  "VERIFIED that the Previous button pagination is disabled", 
									  "Failed to Verify whether the Previous button pagination is disabled or not");
				
				String nextBtnClass = diep.DataExtractPgnNxtBtn.getAttribute("class");
				boolean verifyNextBtnClass = nextBtnClass.contains("disabled");
				TestValidation.IsFalse(verifyNextBtnClass, 
									  "VERIFIED that the Previous button pagination is not disabled", 
									  "Failed to Verify whether the Previous button pagination is disabled or not");
				
			}
			else if(rowCount < 25) {
				
				String nxtBtnClass = diep.DataExtractPgnFrstBtn.getAttribute("class");
				boolean verifyNxtBtnClass = nxtBtnClass.contains("disabled");
				TestValidation.IsTrue(verifyNxtBtnClass, 
									  "VERIFIED that the Next button pagination is disabled as entries are lesser than 25", 
									  "Failed to Verify whether the Next button pagination is disabled or not");
				
			}
			else {
				
				TestValidation.Fail("Failed as the row count found is " + rowCount);
				
			}
		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				TestValidation.Fail(TCGFailureMsg);
			}
		}
	}
	
	@Test(description = "DPT || Export>Data Extract - Verify the pagination for locations.")
	public void TestCase_38562() {
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
			
			String frstBtnClass1 = diep.DataExtractPgnFrstBtn.getAttribute("class");
			boolean verifyFrstBtnClass1 = frstBtnClass1.contains("disabled");
			TestValidation.IsTrue(verifyFrstBtnClass1, 
								  "VERIFIED that the First button pagination is disabled", 
								  "Failed to Verify whether the First button pagination is disabled or not");
			
			String prvsBtnClass1 = diep.DataExtractPgnPrvsBtn.getAttribute("class");
			boolean verifyPrvsBtnClass1 = prvsBtnClass1.contains("disabled");
			TestValidation.IsTrue(verifyPrvsBtnClass1, 
								  "VERIFIED that the Previous button pagination is disabled", 
								  "Failed to Verify whether the Previous button pagination is disabled or not");
			
			int rowCount = diep.DataExtractPopupRowsTbl.size();
			if(rowCount == 25) {
				
				boolean clickNextBtn = diep.clickDataExtractNextPgNav(2);
				TestValidation.IsTrue(clickNextBtn, 
									  "CLICKED the Next pagination button", 
									  "Failed to Click the Next pagination button");
				
				String frstBtnClass2 = diep.DataExtractPgnFrstBtn.getAttribute("class");
				boolean verifyFrstBtnClass2 = frstBtnClass2.contains("disabled");
				TestValidation.IsFalse(verifyFrstBtnClass2, 
									  "VERIFIED that the First button pagination is not disabled now", 
									  "Failed to Verify whether the First button pagination is disabled or not");
				
				String prvsBtnClass2 = diep.DataExtractPgnPrvsBtn.getAttribute("class");
				boolean verifyPrvsBtnClass2 = prvsBtnClass2.contains("disabled");
				TestValidation.IsFalse(verifyPrvsBtnClass2, 
									  "VERIFIED that the Previous button pagination is not disabled now", 
									  "Failed to Verify whether the Previous button pagination is disabled or not");
				
				boolean clickFirstBtn = diep.clickDataExtractFirstPgNav();
				TestValidation.IsTrue(clickFirstBtn, 
									  "CLICKED the First pagination button", 
									  "Failed to Click the First pagination button");
				
				String frstBtnClass3 = diep.DataExtractPgnFrstBtn.getAttribute("class");
				boolean verifyFrstBtnClass3 = frstBtnClass3.contains("disabled");
				TestValidation.IsTrue(verifyFrstBtnClass3, 
									  "VERIFIED that the First button pagination is disabled", 
									  "Failed to Verify whether the First button pagination is disabled or not");
				
				String prvsBtnClass3 = diep.DataExtractPgnPrvsBtn.getAttribute("class");
				boolean verifyPrvsBtnClass3 = prvsBtnClass3.contains("disabled");
				TestValidation.IsTrue(verifyPrvsBtnClass3, 
									  "VERIFIED that the Previous button pagination is disabled", 
									  "Failed to Verify whether the Previous button pagination is disabled or not");
				
				String nextBtnClass = diep.DataExtractPgnNxtBtn.getAttribute("class");
				boolean verifyNextBtnClass = nextBtnClass.contains("disabled");
				TestValidation.IsFalse(verifyNextBtnClass, 
									  "VERIFIED that the Previous button pagination is not disabled", 
									  "Failed to Verify whether the Previous button pagination is disabled or not");
				
			}
			else if(rowCount < 25) {
				
				String nxtBtnClass = diep.DataExtractPgnFrstBtn.getAttribute("class");
				boolean verifyNxtBtnClass = nxtBtnClass.contains("disabled");
				TestValidation.IsTrue(verifyNxtBtnClass, 
									  "VERIFIED that the Next button pagination is disabled as entries are lesser than 25", 
									  "Failed to Verify whether the Next button pagination is disabled or not");
				
			}
			else {
				
				TestValidation.Fail("Failed as the row count found is " + rowCount);
				
			}
		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				TestValidation.Fail(TCGFailureMsg);
			}
		}
	}
	
	@Test(description = "DPT || Export>Data Extract - Verify the pagination for records.")
	public void TestCase_38552() {
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
			
			String frstBtnClass1 = diep.DataExtractPgnFrstBtn.getAttribute("class");
			boolean verifyFrstBtnClass1 = frstBtnClass1.contains("disabled");
			TestValidation.IsTrue(verifyFrstBtnClass1, 
								  "VERIFIED that the First button pagination is disabled", 
								  "Failed to Verify whether the First button pagination is disabled or not");
			
			String prvsBtnClass1 = diep.DataExtractPgnPrvsBtn.getAttribute("class");
			boolean verifyPrvsBtnClass1 = prvsBtnClass1.contains("disabled");
			TestValidation.IsTrue(verifyPrvsBtnClass1, 
								  "VERIFIED that the Previous button pagination is disabled", 
								  "Failed to Verify whether the Previous button pagination is disabled or not");
			
			int rowCount = diep.DataExtractPopupRowsTbl.size();
			if(rowCount == 25) {
				
				int clickNextBtnCnt = diep.verifyDataExtractNextPgNavClicked(2);
				TestValidation.IsTrue(clickNextBtnCnt!=0, 
									  "CLICKED the Next pagination button", 
									  "Failed to Click the Next pagination button");
				
				String frstBtnClass2 = diep.DataExtractPgnFrstBtn.getAttribute("class");
				boolean verifyFrstBtnClass2 = frstBtnClass2.contains("disabled");
				TestValidation.IsFalse(verifyFrstBtnClass2, 
									  "VERIFIED that the First button pagination is not disabled now", 
									  "Failed to Verify whether the First button pagination is disabled or not");
				
				String prvsBtnClass2 = diep.DataExtractPgnPrvsBtn.getAttribute("class");
				boolean verifyPrvsBtnClass2 = prvsBtnClass2.contains("disabled");
				TestValidation.IsFalse(verifyPrvsBtnClass2, 
									  "VERIFIED that the Previous button pagination is not disabled now", 
									  "Failed to Verify whether the Previous button pagination is disabled or not");
				
				boolean clickPreviousBtn = diep.clickDataExtractPreviousPgNav();
				TestValidation.IsTrue(clickPreviousBtn, 
									  "CLICKED the Previous pagination button", 
									  "Failed to Click the Previous pagination button");
				
				if(clickNextBtnCnt == 1) {
					
					String frstBtnClass3 = diep.DataExtractPgnFrstBtn.getAttribute("class");
					boolean verifyFrstBtnClass3 = frstBtnClass3.contains("disabled");
					TestValidation.IsTrue(verifyFrstBtnClass3, 
										  "VERIFIED that the First button pagination is disabled", 
										  "Failed to Verify whether the First button pagination is disabled or not");
					
					String prvsBtnClass3 = diep.DataExtractPgnPrvsBtn.getAttribute("class");
					boolean verifyPrvsBtnClass3 = prvsBtnClass3.contains("disabled");
					TestValidation.IsTrue(verifyPrvsBtnClass3, 
										  "VERIFIED that the Previous button pagination is disabled", 
										  "Failed to Verify whether the Previous button pagination is disabled or not");
					
					String nextBtnClass = diep.DataExtractPgnNxtBtn.getAttribute("class");
					boolean verifyNextBtnClass = nextBtnClass.contains("disabled");
					TestValidation.IsFalse(verifyNextBtnClass, 
										  "VERIFIED that the Previous button pagination is not disabled", 
										  "Failed to Verify whether the Previous button pagination is disabled or not");
				}
				else if(clickNextBtnCnt > 1) {
					
					String frstBtnClass3 = diep.DataExtractPgnFrstBtn.getAttribute("class");
					boolean verifyFrstBtnClass3 = frstBtnClass3.contains("disabled");
					TestValidation.IsFalse(verifyFrstBtnClass3, 
										  "VERIFIED that the First button pagination is not disabled", 
										  "Failed to Verify whether the First button pagination is disabled or not");
					
					String prvsBtnClass3 = diep.DataExtractPgnPrvsBtn.getAttribute("class");
					boolean verifyPrvsBtnClass3 = prvsBtnClass3.contains("disabled");
					TestValidation.IsFalse(verifyPrvsBtnClass3, 
										  "VERIFIED that the Previous button pagination is not disabled", 
										  "Failed to Verify whether the Previous button pagination is disabled or not");
					
					String nextBtnClass = diep.DataExtractPgnNxtBtn.getAttribute("class");
					boolean verifyNextBtnClass = nextBtnClass.contains("disabled");
					TestValidation.IsFalse(verifyNextBtnClass, 
										  "VERIFIED that the Previous button pagination is not disabled", 
										  "Failed to Verify whether the Previous button pagination is disabled or not");
				}
			}
			else if(rowCount < 25) {
				
				String nxtBtnClass = diep.DataExtractPgnFrstBtn.getAttribute("class");
				boolean verifyNxtBtnClass = nxtBtnClass.contains("disabled");
				TestValidation.IsTrue(verifyNxtBtnClass, 
									  "VERIFIED that the Next button pagination is disabled as entries are lesser than 25", 
									  "Failed to Verify whether the Next button pagination is disabled or not");
				
			}
			else {
				
				TestValidation.Fail("Failed as the row count found is " + rowCount);
				
			}
		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				TestValidation.Fail(TCGFailureMsg);
			}
		}
	}
		
	@Test(description = "DPT || Export>Data Extract - Verify the pagination for supplier users.")
	public void TestCase_38557() {
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
			
			String frstBtnClass1 = diep.DataExtractPgnFrstBtn.getAttribute("class");
			boolean verifyFrstBtnClass1 = frstBtnClass1.contains("disabled");
			TestValidation.IsTrue(verifyFrstBtnClass1, 
								  "VERIFIED that the First button pagination is disabled", 
								  "Failed to Verify whether the First button pagination is disabled or not");
			
			String prvsBtnClass1 = diep.DataExtractPgnPrvsBtn.getAttribute("class");
			boolean verifyPrvsBtnClass1 = prvsBtnClass1.contains("disabled");
			TestValidation.IsTrue(verifyPrvsBtnClass1, 
								  "VERIFIED that the Previous button pagination is disabled", 
								  "Failed to Verify whether the Previous button pagination is disabled or not");
			
			int rowCount = diep.DataExtractPopupRowsTbl.size();
			if(rowCount == 25) {
				
				boolean clickNextBtn = diep.clickDataExtractNextPgNav();
				TestValidation.IsTrue(clickNextBtn, 
									  "CLICKED the Next pagination button", 
									  "Failed to Click the Next pagination button");
				
				String frstBtnClass2 = diep.DataExtractPgnFrstBtn.getAttribute("class");
				boolean verifyFrstBtnClass2 = frstBtnClass2.contains("disabled");
				TestValidation.IsFalse(verifyFrstBtnClass2, 
									  "VERIFIED that the First button pagination is not disabled now", 
									  "Failed to Verify whether the First button pagination is disabled or not");
				
				String prvsBtnClass2 = diep.DataExtractPgnPrvsBtn.getAttribute("class");
				boolean verifyPrvsBtnClass2 = prvsBtnClass2.contains("disabled");
				TestValidation.IsFalse(verifyPrvsBtnClass2, 
									  "VERIFIED that the Previous button pagination is not disabled now", 
									  "Failed to Verify whether the Previous button pagination is disabled or not");
				
			}
			else if(rowCount < 25) {
				
				String nxtBtnClass = diep.DataExtractPgnFrstBtn.getAttribute("class");
				boolean verifyNxtBtnClass = nxtBtnClass.contains("disabled");
				TestValidation.IsTrue(verifyNxtBtnClass, 
									  "VERIFIED that the Next button pagination is disabled as entries are lesser than 25", 
									  "Failed to Verify whether the Next button pagination is disabled or not");
				
			}
			else {
				
				TestValidation.Fail("Failed as the row count found is " + rowCount);
				
			}
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
