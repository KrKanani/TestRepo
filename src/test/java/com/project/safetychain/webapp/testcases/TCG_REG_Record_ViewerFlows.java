package com.project.safetychain.webapp.testcases;

import java.util.List;

import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.project.safetychain.webapp.pageobjects.FSQABrowserPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.FSQATAB;
import com.project.safetychain.webapp.pageobjects.FSQABrowserRecordsPage;
import com.project.safetychain.webapp.pageobjects.HomePage;
import com.project.safetychain.webapp.pageobjects.LoginPage;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.utilities.ControlActions;

public class TCG_REG_Record_ViewerFlows extends TestBase{
	ControlActions controlActions;
	HomePage hp;
	FSQABrowserPage fbp;
	FSQABrowserRecordsPage frp;

	//Data
	public static String checkFormName;
	
	public static String locationCategoryValue;
	public static String resourceType = "Customers";
	public static String resourceCategoryValue;
	public static String resourceInstanceValue1,resourceInstanceValue2;
	public static String locationInstanceValue;

	@BeforeClass(alwaysRun = true)
	public void groupInit() throws Exception {

		driver = launchbrowser();
		controlActions = new ControlActions(driver);
		controlActions.getUrl(applicationUrl);
		hp = new HomePage(driver);
		fbp = new FSQABrowserPage(driver);
		frp = new FSQABrowserRecordsPage(driver);

		LoginPage lp = new LoginPage(driver);
		hp = lp.performLogin(adminUsername, adminPassword);
		if(hp.error) {
			TCGFailureMsg = "Could NOT login to application";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
				
		if(!fbp.navigateToFSQATab(FSQATAB.RECORDS)) {
			TCGFailureMsg = "Could NOT navigate to FSQA Records tab";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
		
		if(!fbp.clickSelectAllChk()) {
			TCGFailureMsg = "Could NOT click on Select All checkbox";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
		
		FSQABrowserRecordsPage frp = fbp.clickViewRecordsBtn();
		if(frp.error) {
			TCGFailureMsg = "Could NOT navigate to FSQABrowser > Records tab > Record Viewer";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

	}

	@Test(description = "Multi-Record Signoff - Record Viewer - Users can click on any record card "
			+ "from the list to view that record in the viewer panel")
	public void TestCase_16468() {
		
		String selectedCardClass = frp.RViewerRecordCards.get(0).getAttribute("class");
		boolean verifySelectn = selectedCardClass.toLowerCase().contains("selected");
		TestValidation.IsTrue(verifySelectn, 
				  			  "By default, the first card in the list is SELECTED", 
				  			  "Could NOT verify that by default, the first card in the list should be selected"); 
		
		boolean selectCard = frp.selectRecordInRecordViewer(3);
		TestValidation.IsTrue(selectCard, 
	  			  			  "ABLE to select any record from the list by clicking on the card", 
	  			  			  "Could NOT able to select any record from the list by clicking on the card"); 
		
		List<String> getDetails = frp.getSelectedRecordDetsInRecordViewer();
		boolean verifyDets = getDetails.isEmpty();
		TestValidation.IsFalse(verifyDets, 
							   "Details DISPLAYED on the right hand panel", 
	  			  			   "Could NOT verify if details are displayed on right hand panel"); 
	
	}
	
	@Test(description = "9038 : Multi-Record Signoff - Record Viewer - Records in the card list "
			+ "are grouped by Completed On date by default")
	public void TestCase_16446() {
		
		int headerDates = frp.compareCardLayoutHeaderDates();
		boolean verifyHeaderDates = (headerDates==2);
		TestValidation.IsTrue(verifyHeaderDates, 
	  			  			  "VERIFIED records ordered by Completed On and in ascending order", 
	  			  			  "Could NOT verify records ordered by Completed On"); 
		
		boolean verifyRecordTimeAsc = frp.compareCardLayoutCompletedOnDates(2);
		TestValidation.IsTrue(verifyRecordTimeAsc, 
	  			  			  "VERIFIED records ordered by Time in ascending order", 
	  			  			  "Could NOT verify records ordered by Time in ascending order"); 

	}
	
	@Test(description = "Multi-Record Signoff - Record Viewer - The header at the top of the card list "
			+ "shows a count of total records being viewed")
	public void TestCase_16467() {
		
		String topPanelDetails = frp.RViewerRecordsTitle.getText();
		boolean verifyName = topPanelDetails.toLowerCase().contains("record viewer");
		TestValidation.IsTrue(verifyName, 
	  			  			  "VERIFIED name at Top Panel as Record Viewer", 
	  			  			  "Could NOT verify name at Top Panel as Record Viewer"); 
		
		int recordCount = frp.RViewerRecordCards.size();
		boolean verifyCount = topPanelDetails.contains(Integer.toString(recordCount));
		TestValidation.IsTrue(verifyCount, 
	  			  			  "VERIFIED count of records at Top Panel as " + recordCount, 
	  			  			  "Could NOT verify count of records at Top Panel"); 
		
	}
		
	@AfterClass(alwaysRun = true)
	public void closeBrowser() throws InterruptedException {
		driver.close();
	}
}
