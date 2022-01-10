package com.project.safetychain.webapp.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.project.utilities.ControlActions;
import com.project.utilities.DateTime;
import com.project.utilities.DateTime.ComparisonValue;

public class RecordSignoffPage extends CommonPage {
	
	WebDriver driver;
	WebDriverWait wait;
	ControlActions controlActions;
	
	public RecordSignoffPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 40);
		controlActions = new ControlActions(driver);
	}	
	
	/**
	* Page Objects
	*/
	
	@FindBy(id = RecordSignoffPageLocators.TOTAL_RECORD_COUNT)
	public WebElement TotalRecordCount;
	
	@FindBy(id = RecordSignoffPageLocators.SIGN_RECORD_POPUP_BTN)
	public WebElement SignRecordPopupBtn;
	
	@FindBy(id = RecordSignoffPageLocators.UNSIGNED_RECS_COUNT)
	public WebElement UnsignedRecsCount;	
	
	@FindBy(id = RecordSignoffPageLocators.SIGNED_RECS_COUNT)
	public WebElement SignedRecsCount;
	
	@FindBy(id = RecordSignoffPageLocators.CORRECTN_LIST_PANEL)
	public WebElement CorrectnListPanel;
	
	@FindBy(id = RecordSignoffPageLocators.RECORD_SIGNOFF_HEADER)
	public WebElement RecordSignoffHeader;
	
	@FindBy(id = RecordSignoffPageLocators.CMPLTD_ON_FROM_TXT)
	public WebElement CmpltdOnFromTxt;
	
	@FindBy(id = RecordSignoffPageLocators.CMPLTD_ON_TO_TXT)
	public WebElement CmpltdOnToTxt;
	
	@FindBy(xpath = RecordSignoffPageLocators.NEXT_BTN)
	public WebElement NextBtn;
	
	@FindBy(xpath = RecordSignoffPageLocators.SIGN_ALL_BTN)
	public WebElement SignAllBtn;
	
	@FindBy(xpath = RecordSignoffPageLocators.MSG_POPUP_CLOSE_BTN)
	public WebElement MsgPopupCloseBtn;
	
	@FindBy(xpath = RecordSignoffPageLocators.FILTER_COLUMNS)
	public List<WebElement> FilterColumns;
	
	@FindBy(xpath = RecordSignoffPageLocators.FILTER_SEARCH_TXT)
	public WebElement FilterSearchTxt;
	
	@FindBy(xpath = RecordSignoffPageLocators.FILTER_SEARCH_BTN)
	public WebElement FilterSearchBtn;
	
	@FindBy(xpath = RecordSignoffPageLocators.APPLY_FILTER_POPUP_BTN)
	public WebElement ApplyFilterPopupBtn;
	
	@FindBy(xpath = RecordSignoffPageLocators.CLEAR_ALL_FILTER_BTN)
	public WebElement ClearAllFilterBtn;
	
	@FindBy(xpath = RecordSignoffPageLocators.SIGNOFF_CMMT_TXA)
	public WebElement SignoffCmmtTxa;
	
	@FindBy(xpath = RecordSignoffPageLocators.RECORD_SUMM_HEADERS)
	public List<WebElement> RecordSummHeaders;
	
	@FindBy(xpath = RecordSignoffPageLocators.COMPLIANT_RECS_COUNT)
	public WebElement CompliantRecsCount;
	
	@FindBy(xpath = RecordSignoffPageLocators.NONCOMPLIANT_RECS_COUNT)
	public WebElement NonCompliantRecsCount;
	
	@FindBy(xpath = RecordSignoffPageLocators.RECORD_SIGN_ICON)
	public WebElement RecordSignIcon;
	
	@FindBy(xpath = RecordSignoffPageLocators.RECORD_SIGNOFF_DETS)
	public List<WebElement> RecordSignoffDets;
	
	@FindBy(xpath = RecordSignoffPageLocators.RECORD_SIGNOFF_CLOSE_BTN)
	public WebElement RecordSignoffCloseBtn;
	
	@FindBy(xpath = RecordSignoffPageLocators.UNRESOLVD_RECS_COUNT)
	public WebElement UnresolvdRecsCount;
	
	@FindBy(xpath = RecordSignoffPageLocators.RESOLVD_RECS_COUNT)
	public WebElement ResolvdRecsCount;
	
	@FindBy(xpath = RecordSignoffPageLocators.SHOW_CORRECTNS_BTN)
	public WebElement ShowCorrectnsBtn;
	
	@FindBy(xpath = RecordSignoffPageLocators.SUMM_DATE_RANGE)
	public List<WebElement> SummDateRange;
	
	@FindBy(xpath = RecordSignoffPageLocators.CMPLTD_ON_FROM_LBL)
	public WebElement CmpltdOnFromLbl;
	
	@FindBy(xpath = RecordSignoffPageLocators.REVW_RECS_LNK)
	public WebElement RevwRecsLnk;
	
	@FindBy(xpath = RecordSignoffPageLocators.CLEAR_FILTER_POPUP_BTN)
	public WebElement ClearFilterPopupBtn;
	
	@FindBy(xpath = RecordSignoffPageLocators.CORRECTNS_PANEL_HEADER)
	public WebElement CorrectnsPanelHeader;
	
	@FindBy(xpath = FSQABrowserRecordsPageLocators.SELECT_SIGNOFF_VERIFICATION_DDL)
	public WebElement selectSignOffVerificationDdl;
	
	
	/**
	* Functions
	*/
	
	/**
	 * This method is used to click on Total records count of selected Records 
	 * @author hingorani_a
	 * @param none
	 * @return FSQABrowserRecordsPage This returns object with error variable as false
	 * if Total records count is clicked.
	 */
	public FSQABrowserRecordsPage clickTotalRecordsCount() {
		try {			
			controlActions.WaitforelementToBeClickable(TotalRecordCount);
			controlActions.clickOnElement(TotalRecordCount);
			Sync();
			logInfo("Clicked on Total Records button");	
			return new FSQABrowserRecordsPage(driver);
		}
		catch(Exception e) {
			logError("Failed to click on Total Records button - "
					+ e.getMessage());
			FSQABrowserRecordsPage frp = new FSQABrowserRecordsPage(driver);
			frp.error = true;
			return frp;
		}		
	}
	
	/**
	 * This method is used to apply filter by selecting a value
	 * @author hingorani_a
	 * @param filterType Use class FILTERTYPES to set the type of filter to be applied for the column
	 * @param filterBy The value to be selected for filter
	 * @return boolean This returns true after applying filter
	 */
	public boolean applyFilterBySearch(String filterType, String filterBy) {
		WebElement FilterValueChk = null;
		ComparisonValue comparisonVal = null;
		String DTParts[] = null;
		String fromDate, fromTime, fromDateTime = null;
		String toDate, toTime, toDateTime = null;
		
		try {			
			for(WebElement column : FilterColumns) {
				if(controlActions.perform_CheckIfElementTextContains(column, filterType)) {
					controlActions.WaitforelementToBeClickable(column);
					controlActions.clickOnElement(column);
					logInfo("Clicked on column - " + filterType);
					Sync();
										
					switch(filterType) {
					case FILTERTYPES.CUSTOMERS:
					case FILTERTYPES.EQUIPMENT:
					case FILTERTYPES.ITEMS:
					case FILTERTYPES.SUPPLIERS:
					case FILTERTYPES.LOCATION:
					case FILTERTYPES.FORMS:
					case FILTERTYPES.COMPLETEDBY:
						controlActions.WaitforelementToBeClickable(FilterSearchTxt);
						FilterSearchTxt.clear();
						controlActions.clickOnElement(FilterSearchTxt);
						controlActions.sendKeys(FilterSearchTxt,filterBy);
						logInfo("Put value to search text box - " + filterBy);
						
						controlActions.WaitforelementToBeClickable(FilterSearchBtn);
						controlActions.clickOnElement(FilterSearchBtn);
						Sync();
						logInfo("Clicked on search button");
						
						FilterValueChk = controlActions.perform_GetElementByXPath(RecordSignoffPageLocators.FILTER_VALUE_CHK, 
																	  "FILTERVALUE", filterBy);
						controlActions.WaitforelementToBeClickable(FilterValueChk);
						controlActions.clickOnElement(FilterValueChk);
						logInfo("Selected checkbox for value - " + filterBy);
						
						controlActions.WaitforelementToBeClickable(ApplyFilterPopupBtn);
						controlActions.clickOnElement(ApplyFilterPopupBtn);
						Sync();
						logInfo("Filter applied for column : " + filterType + " and value : " + filterBy);
						return true;
						
					case FILTERTYPES.COMPLETEDON:
						
						DTParts=SummDateRange.get(0).getText().trim().split(" ");
						fromDate = DTParts[0];
						fromTime = DTParts[1];
						DTParts=SummDateRange.get(1).getText().trim().split(" ");
						toDate = DTParts[0];
						toTime = DTParts[1];
						
						DateTime dt = new DateTime();
						comparisonVal = dt.compareDates(fromDate, toDate, null, null);
						if(comparisonVal.equals(ComparisonValue.LESSER)) {
							String updatedDate = dt.addSubtractDaysFromDate(dt.getDate(fromDate, "MM/dd/yyyy"), 1, "MM/dd/yyyy");
							fromDateTime = updatedDate + " " + fromTime;
							
							CmpltdOnFromTxt.click();
							CmpltdOnFromTxt.clear();
							CmpltdOnFromTxt.sendKeys(fromDateTime);
							logInfo("Completed on From date is set to : " + fromDateTime);
						}
						else if (comparisonVal.equals(ComparisonValue.EQUALS)) {
							toDateTime = toDate + " " + toTime;
							String updatedDateTime = dt.subtractTime(toDateTime, "MM/dd/yyyy HH:mm", 15);
							
							CmpltdOnFromTxt.click();
							CmpltdOnFromTxt.clear();
							CmpltdOnFromTxt.sendKeys(updatedDateTime);
							logInfo("Completed on From date is set to : " + updatedDateTime);
						}
						else if (comparisonVal.equals(ComparisonValue.GREATER)) {
							logError("The from Date should not be greater than To date");
							return false;
						}
						
						CmpltdOnFromLbl.click();
						controlActions.WaitforelementToBeClickable(ApplyFilterPopupBtn);
						ApplyFilterPopupBtn.click();
						Sync();
						logInfo("Filter applied for Completed On column");
						return true;
					
					default:
						logError("Invalid Option");
						return false;
					}
				}	
			}
			logError("Could not apply filter for column - " + filterType);
			return false;
		}
		catch(Exception e) {
			logError("Failed to apply filter for column - " + filterType
					+ e.getMessage());
			return false;
		}
	}
	
	/**
	 * This method is used to click on Next button
	 * @author hingorani_a
	 * @param none
	 * @return boolean This returns true after clicking on Next button
	 */
	public boolean clickNextBtn() {
		try {
			controlActions.WaitforelementToBeClickable(NextBtn);
			controlActions.clickOnElement(NextBtn);
			Sync();
			logInfo("Clicked on Next button");
			return true;
		}
		catch(Exception e) {
			logError("Failed to click on Next button : "
					+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to click on Sign All button
	 * @author hingorani_a
	 * @param none
	 * @return boolean This returns true after clicking on Sign All button
	 */
	public boolean clickSignAllBtn() {
		try {
			controlActions.WaitforelementToBeClickable(SignAllBtn);
			controlActions.clickOnElement(SignAllBtn);
			Sync();
			logInfo("Clicked on Sign All button");
			return true;
		}
		catch(Exception e) {
			logError("Failed to click on Sign All button : "
					+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to click on close button for Message popup when you land on Record Signoff page
	 * @author hingorani_a
	 * @param none
	 * @return boolean This returns true after clicking on close button
	 */
	public boolean closeMsgPopup() {
		try {
			if(controlActions.isElementDisplay(MsgPopupCloseBtn)) {
				controlActions.WaitforelementToBeClickable(MsgPopupCloseBtn);
				MsgPopupCloseBtn.click();
				Sync();
				logInfo("Clicked on Close button on message popup");
				return true;
			}
			logInfo("Could not find Message popup on Records Sign off page");
			return false;
		}
		catch(Exception e) {
			logError("Failed to click on Close button on message popup : "
					+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to Sign off all the records when you land on Record Signoff page
	 * @author hingorani_a
	 * @param comment The comment you want to add while signing off records
	 * @return FSQABrowserPage This returns object with error variable as false
	 * if records are signed off.
	 */
	public FSQABrowserPage signoffAllRecords(String comment) {
		try {
			if(!clickSignAllBtn()) {
				FSQABrowserPage fbp = new FSQABrowserPage(driver);
				fbp.error = true;
				return fbp;
			}
			
			controlActions.WaitforelementToBeClickable(SignoffCmmtTxa);
			SignoffCmmtTxa.clear();
			controlActions.sendKeys(SignoffCmmtTxa, comment);
			logInfo("Added comment to Sign off as - " + comment);
			
			controlActions.WaitforelementToBeClickable(SignRecordPopupBtn);
			controlActions.clickOnElement(SignRecordPopupBtn);
			logInfo("Clicked on Sign off button");
			Sync();
			return new FSQABrowserPage(driver);
		}
		catch(Exception e) {
			logError("Failed to Sign off a record - "
					+ e.getMessage());
			FSQABrowserPage fbp = new FSQABrowserPage(driver);
			fbp.error = true;
			return fbp;
		}	
	}
	
	/**
	 * This method is used to click on Clear All Filter button
	 * @author hingorani_a
	 * @param none
	 * @return boolean This returns true after clicking on Clear All Filter button
	 */
	public boolean clickClearAllFilterBtn() {
		try {
			controlActions.WaitforelementToBeClickable(ClearAllFilterBtn);
			ClearAllFilterBtn.click();
			Sync();
			logInfo("Clicked on Clear filter button");
			return true;
		}
		catch(Exception e) {
			logError("Failed to click on Clear filter button : "
					+ e.getMessage());
			return false;
		}	
	}
	
	/**
	 * This method is used to verify Record Summary details
	 * @author hingorani_a
	 * @param detailType Use class RECORD_SUMMARY_DETS to set detailType like
	 * Customers, Date Range, Forms, etc
	 * @param detailName Name of the detail to be verified
	 * @return boolean This returns true after verifying required Record Summary details
	 */
	public boolean verifyRecordSummaryResourceDets(String detailType, String detailName) {
		WebElement RecordSummHeaderNm = null;
		WebElement RecordSummHeaderDets = null;
		
		try {
			if(RecordSummHeaders.size()==0) {
				logInfo("The size of Record Summary Headers is 0");
				return false;
			}
			else {
				for(int i = 1; i <= RecordSummHeaders.size(); i++) {
					RecordSummHeaderNm = controlActions.perform_GetElementByXPath(RecordSignoffPageLocators.RECORD_SUMM_HEADER_NM, 
							"INDEX_NO", Integer.toString(i));
					if(RecordSummHeaderNm.getText().equalsIgnoreCase(detailType))
					{
						RecordSummHeaderDets = controlActions.perform_GetElementByXPath(
								RecordSignoffPageLocators.RECORD_SUMM_HEADER_DETS, "INDEX_NO", Integer.toString(i));
						if(RecordSummHeaderDets.getText().contains(detailName)) {
							logInfo(detailName + " found under resource type " + detailType);
							return true;
						}
					}
				}
			}
			
			logInfo("Could not find " + detailName + " under resource type " + detailType);
			return false;
		}
		catch(Exception e) {
			logError("Failed to find " + detailName + " under resource type " + detailType
					+ e.getMessage());
			return false;
		}	
	}
	
	/**
	 * This method is used to set Location on Sign records page
	 * @author hingorani_a
	 * @param locName Name of the location to be set
	 * @return boolean This returns true after setting desired location
	 */
	public boolean setLocation(String locName) {
		try {
			String selector = "#scs-select-location_listbox > li";
			controlActions.JSSetValueFromList(driver, selector, locName);
			Sync();
			logInfo("Location set to " + locName);
			return true;
		}
		catch(Exception e) {
			logError("Failed to set location to : " + locName 
					+ e.getMessage());
			return false;
		}	
	}
	
	/** This method is used to get latest Sign off details after hovering on Sign Icon -  for Records
	 * @author hingorani_a
	 * @param none
	 * @return String returns value of the Signoff details
	 */	
	public String clickRecordsSignIcon() {
		String signoffDetail = null;
		try {
			controlActions.hoverElement(RecordSignIcon);
			Sync();
			if(RecordSignoffDets.size() == 1) {
				signoffDetail = RecordSignoffDets.get(0).getText();
			}
			logInfo("Sign off details - " + signoffDetail);
			return signoffDetail;
		}
		catch(Exception e) {
			logError("Failed to get Signoff details - " + e.getMessage());
			return signoffDetail;
		}
	}
	
	/**
	 * This method is used to close Record Signoff popup 
	 * @author hingorani_a
	 * @param none
	 * @return boolean This returns true if Record Signoff popup is closed
	 */
	public boolean closeRecordSignoffPopup() {
		try {			
			controlActions.WaitforelementToBeClickable(RecordSignoffCloseBtn);
			RecordSignoffCloseBtn.click();
//			Sync();
			logInfo("Closed Record Signoff popup");	
			return true;
		}
		catch(Exception e) {
			logError("Failed to close Record Signoff popup - "
					+ e.getMessage());
			return false;
		}		
	}
	
	/**
	 * This method is used to drill down into the mentioned Panel 
	 * @author hingorani_a
	 * @param panelName Use class RECORD_ANALYTIC_PANELS to set a panel to drill down
	 * @return FSQABrowserPage This returns object with error variable as false
	 * if we are able to drill down the mentioned panel.
	 */
	public FSQABrowserRecordsPage drilldownAnalytics(String panelName) {
		FSQABrowserRecordsPage frp = new FSQABrowserRecordsPage(driver);
		
		try {
			
			switch(panelName) {
				case RECORD_ANALYTIC_PANELS.TOTALS:
					TotalRecordCount.click();
					logInfo("Clicked on Record Signoff > Totals panel");
					break;
				case RECORD_ANALYTIC_PANELS.COMPLIANT:
					CompliantRecsCount.click();
					logInfo("Clicked on Record Signoff > Compliant panel");
					break;
				case RECORD_ANALYTIC_PANELS.NON_COMPLIANT:
					NonCompliantRecsCount.click();
					logInfo("Clicked on Record Signoff > Non Compliant panel");
					break;
				case RECORD_ANALYTIC_PANELS.UNSIGNED:
					UnsignedRecsCount.click();
					logInfo("Clicked on Record Signoff > Unsigned panel");
					break;
				case RECORD_ANALYTIC_PANELS.SIGNED:
					SignedRecsCount.click();
					logInfo("Clicked on Record Signoff > Signed panel");
					break;
				case RECORD_ANALYTIC_PANELS.UNRESOLVED:
					if(!clickUnresolvedTab()) {
						frp.error = true;
						return frp;
					}
					
					CorrectnsPanelHeader.click();
					logInfo("Clicked on Record Signoff > Unresolved panel");
					break;
				case RECORD_ANALYTIC_PANELS.RESOLVED:
					if(!clickResolvedTab()) {
						frp.error = true;
						return frp;
					}
						
					CorrectnsPanelHeader.click();
					logInfo("Clicked on Record Signoff > Resolved panel");
					break;
				default:
					frp.error = true;
					logError("Clicked on Record Signoff > Totals panel");
					break;
			}
			
			Sync();
			
			if(controlActions.isElementDisplayed(frp.RViewerRecordsTitle)) {
				logInfo("Navigated to Record Viewer");
				return frp;
			}
			
			logError("Could not navigate to Record Viewer");
			frp.error = true;
			return frp;
		}
		catch(Exception e) {
			logError("Failed to drill down into Record Signoff panel - " + panelName
					+ e.getMessage());
			frp.error = true;
			return frp;
		}		
	}
	
	/**
	 * This method is used to click on Corrections > Unresolved tab  
	 * @author hingorani_a
	 * @param none
	 * @return boolean This returns true after clicking on Unresolved tab
	 */
	public boolean clickUnresolvedTab() {
		try {
			controlActions.WaitforelementToBeClickable(UnresolvdRecsCount);
			UnresolvdRecsCount.click(); 
//			Sync();
			logInfo("Clicked on Corrections > Unresolved tab");
			return true;
		}
		catch(Exception e) {
			logError("Failed to click on Corrections > Unresolved tab : "
					+ e.getMessage());
			return false;
		}	
	}
	
	/**
	 * This method is used to click on Corrections > Resolved tab  
	 * @author hingorani_a
	 * @param none
	 * @return boolean This returns true after clicking on Resolved tab
	 */
	public boolean clickResolvedTab() {
		try {
			controlActions.WaitforelementToBeClickable(ResolvdRecsCount);
			ResolvdRecsCount.click(); 
//			Sync();
			logInfo("Clicked on Corrections > Resolved tab");
			return true;
		}
		catch(Exception e) {
			logError("Failed to click on Corrections > Resolved tab : "
					+ e.getMessage());
			return false;
		}	
	}
	
	/**
	 * This method is used to click on Show Corrections button 
	 * @author hingorani_a
	 * @param none
	 * @return boolean This returns true after clicking on Show Corrections button
	 */
	public boolean clickShowCorrectionsBtn() {
		try {
			controlActions.WaitforelementToBeClickable(ShowCorrectnsBtn);
			ShowCorrectnsBtn.click(); 
			Sync();
			logInfo("Clicked on Show Corrections button");
			return true;
		}
		catch(Exception e) {
			logError("Failed to click on Show Corrections button : "
					+ e.getMessage());
			return false;
		}	
	}
	
	/**
	 * This method is used to click on Review Records link from breadcrumb
	 * @author hingorani_a
	 * @param none
	 * @return boolean This returns true after clicking on Review Records link
	 */
	public boolean clickReviewRecsLink() {
		try {
			controlActions.WaitforelementToBeClickable(RevwRecsLnk);
			RevwRecsLnk.click();
			Sync();
			logInfo("Clicked on Review Records link");
			return true;
		}
		catch(Exception e) {
			logError("Failed to click on Review Records link : "
					+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to click on Clear filter button for a column
	 * @author hingorani_a
	 * @param filterType Use class FILTERTYPES to clear the type of filter applied for the column
	 * @return boolean This returns true after clearing filter
	 */
	public boolean clearAppliedFilterForType(String filterType) {
		 
		try {			
			for(WebElement column : FilterColumns) {
				if(controlActions.perform_CheckIfElementTextContains(column, filterType)) {
					controlActions.WaitforelementToBeClickable(column);
					controlActions.clickOnElement(column);
					logInfo("Clicked on column - " + filterType);
					Sync();

					controlActions.WaitforelementToBeClickable(ClearFilterPopupBtn);
					ClearFilterPopupBtn.click();
					Sync();
					logInfo("Clicked on Clear filter button" );
					return true;
				}	
			}
			logError("Could not click on Clear filter for column - " + filterType);
			return false;
		}
		catch(Exception e) {
			logError("Failed to click on Clear filter for column - " + filterType
					+ e.getMessage());
			return false;
		}
	}
	
	
	/**
	 * This method is used to click on Sign Off a record with Verification
	 * @author dahale_p
	 * 11 Nov 2021
	 * @param comment The comment you want to add while Signing Off 
	 * @return boolean This returns true after Signing off a record
	 */
	public boolean selectVerficationForSignOff(String verificationName) {
		try {
			
			controlActions.clickOnElement(selectSignOffVerificationDdl);
		//	String xpath = "//*[@id='scs-select-signoff_listbox']//li[text()='VERIFICATION_NAME']";
		//	WebElement SelectVerification = controlActions.perform_GetElementByXPath(xpath,"VERIFICATION_NAME",verificationName);
			WebElement SelectVerification = controlActions.perform_GetElementByXPath(FSQABrowserRecordsPageLocators.SELECT_VERIFICATION_NEW,"VERIFICATION_NAME",verificationName);
			controlActions.perform_scrollToElement_ByElement(SelectVerification);
			controlActions.clickOnElement(SelectVerification);
			Sync();
			
			
			logInfo("Clicked on Sign off Verification Dropdown");
			Sync();
			return true;
		}
		catch(Exception e) {
			logError("Failed to click Sign off  dropdown and select the Verification- "
					+ e.getMessage());
			return false;
		}	
	}
	
	public static class FILTERTYPES{
		public static final String CUSTOMERS = "Customers";
		public static final String EQUIPMENT = "Equipment";
		public static final String ITEMS = "Items";
		public static final String SUPPLIERS = "Suppliers";
		public static final String LOCATION = "Location";
		public static final String FORMS = "Forms";
		public static final String COMPLETEDBY = "Completed By";
		public static final String COMPLETEDON = "Completed On";	
	}
	
	public static class RECORD_SUMMARY_DETS{
		public static final String CUSTOMERS = "Customers";
		public static final String EQUIPMENT = "Equipment";
		public static final String ITEMS = "Items";
		public static final String SUPPLIERS = "Suppliers";
		public static final String LOCATION = "Location";
		public static final String DATE_RANGE = "Date Range";
		public static final String FORMS = "Forms";
		public static final String COMPLETEDBY = "Completed By";
	}
	
	public static class RECORD_ANALYTIC_PANELS{
		public static final String TOTALS = "Totals";
		public static final String COMPLIANT = "Compliant";
		public static final String NON_COMPLIANT = "Non-Compliant";
		public static final String UNSIGNED = "Unsigned";
		public static final String SIGNED = "Signed";
		public static final String UNRESOLVED = "Unresolved";
		public static final String RESOLVED = "Resolved";
	}
	
}
