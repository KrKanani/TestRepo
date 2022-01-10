package com.project.safetychain.webapp.testcases;

import java.util.Arrays;
import java.util.List;

import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.project.safetychain.webapp.pageobjects.FSQABrowserFormsPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage;
import com.project.safetychain.webapp.pageobjects.HomePage;
import com.project.safetychain.webapp.pageobjects.LoginPage;
import com.project.safetychain.webapp.pageobjects.ManageLocationPage;
import com.project.safetychain.webapp.pageobjects.ManageResourcePage;
import com.project.safetychain.webapp.pageobjects.ResourceDesignerPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.COLUMNHEADER;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.FSQARESOURCE;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.FSQATAB;
import com.project.safetychain.webapp.testcases.support.FormOperations;
import com.project.safetychain.webapp.testcases.support.FormOperations.FieldProperties;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.utilities.ControlActions;

public class TCG_UT_FormDesigner_CmplxSubmitFlows extends TestBase{
	ControlActions controlActions;
	LoginPage lgnPge;
	ResourceDesignerPage resourceDesigner;
	ManageResourcePage manageResource;
	ManageLocationPage locations;
	FormDesignerPage fdp;
	HomePage hp;

//	Where	i stands for Items
//	s stands for Suppliers
//	c stands for Customers
//	e stands for Equipment
	public static String iFormName1 = "HUT - Prepared Foods Core Form - Metal Detection";
	public static String iForm1Resource = "10000024670 - PTX FC PATTY 2.0 OZ";

	@BeforeClass(alwaysRun = true)
	public void groupInit() throws Exception {

		driver = launchbrowser();
		controlActions = new ControlActions(driver);
		controlActions.getUrl(applicationUrl);
		lgnPge = new LoginPage(driver);
		resourceDesigner = new ResourceDesignerPage(driver);
		manageResource = new ManageResourcePage(driver);
		locations = new ManageLocationPage(driver);
		fdp = new FormDesignerPage(driver);

		//login
		hp = lgnPge.performLogin(adminUsername, adminPassword);
		if(hp.error) {
			TCGFailureMsg = "Could NOT login to application";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
		
	}

	@Test(description="Submit Form - Items : HUT - Prepared Foods Core Form - Metal Detection")
	public void TestCase_01() {
		
//		All Single Select One fields where they start with : 
//		f stands for Field - name of fields ,
//		v stands for Value - value of these fields,
//		cr stands for Corrections - correction value for these fields
//		_Contd at the end of Strings mean continued - there is more text to the field's name 
		
		String fNumberofMetalDetectorsBeingChecked = "Number of Metal Detectors Being Checked - HUT";
		String vNumberofMetalDetectorsBeingChecked = "1";
		String fMetalDetectorLocation = "Metal Detector Location";
		String vMetalDetectorLocation = "Auto-Peel Line 1 MD 1";
		String fIsTheLineRunningProduct = "Is the line running product?";
		String vIsTheLineRunningProduct = "Yes";
		String fProductionTime = "Production Time";
		String vProductionTime = "Start of Production";
		String fFerrousStandard = "Ferrous Standard";
		String vFerrousStandard = "0.8 mm";
		String fHowManyTimesAreFerrousStandardsBeingPassed = "How many times are ferrous standards being passed?";
		String vHowManyTimesAreFerrousStandardsBeingPassed = "2";
		String fFerrousSampleDetectedAndRejected = "Ferrous Sample Detected and Rejected?";
		String vFerrousSampleDetectedAndRejected = "Fail";
		String crFerrousSampleDetectedAndRejected = "Test Correction for Ferrous Sample Detected and Rejected";
		String fNonFerrousStandard = "Non-Ferrous Standard";
		String vNonFerrousStandard = "2.0 mm";
		String fHowManyTimesAreNonFerrousStandardsBeingPassed = "How many times are non-ferrous standards being passed?";
		String vHowManyTimesAreNonFerrousStandardsBeingPassed = "3";
		String fNonFerrousSampleDetectedAndRejected = "Non-Ferrous Sample Detected and Rejected?";
		String vNonFerrousSampleDetectedAndRejected = "Pass";
		String fStainlessSteelStandard  = "Stainless Steel Standard";
		String vStainlessSteelStandard = "Not applicable";
		String fHowManyTimesAreStainlessSteelStandardsBeingPassed = "How many times are Stainless Steel standards being passed?";
		String vHowManyTimesAreStainlessSteelStandardsBeingPassed = "1";
		String fStainlessSteelSampleDetectedAndRejected = "Stainless Steel Sample Detected and Rejected?";
		String vStainlessSteelSampleDetectedAndRejected = "Not Applicable";
		String crStainlessSteelSampleDetectedAndRejected = "This is a correction text for Stainless Steel Sample Detected and Rejected";
		String fWereConsecutiveSamplesRunToEnsureRejectTimingIsAcceptable = "Were Consecutive Samples Run to Ensure Reject Timing is Acceptable?";
		String vWereConsecutiveSamplesRunToEnsureRejectTimingIsAcceptable = "No";
		String fWasLeadingMiddleAndTrailingEdgeOfTheBagChecked_Contd = "Was leading, middle, and trailing edge of the bag checked to ensure the standard is detected at every location? - HUT";
		String vWasLeadingMiddleAndTrailingEdgeOfTheBagChecked_Contd = "N/A";
		
		String v2MetalDetectorLocation = "Toppings Formulation";
		String v2IsTheLineRunningProduct = "No";
		String v2ProductionTime = "End of Production";
		String v2FerrousStandard = "8.5 mm";
		String v2HowManyTimesAreFerrousStandardsBeingPassed = "1";
		String v2FerrousSampleDetectedAndRejected = "Pass";
		String v2NonFerrousStandard = "Not Applicable";
		String v2HowManyTimesAreNonFerrousStandardsBeingPassed = "2";
		String v2NonFerrousSampleDetectedAndRejected = "Fail";
		String v2StainlessSteelStandard = "2.5 mm";
		String v2HowManyTimesAreStainlessSteelStandardsBeingPassed = "3";
		String v2StainlessSteelSampleDetectedAndRejected = "Not Applicable";
		String v2WereConsecutiveSamplesRunToEnsureRejectTimingIsAcceptable = "Yes";
		String v2WasLeadingMiddleAndTrailingEdgeOfTheBagChecked_Contd = "Yes";
		
		try {
			
			FSQABrowserPage fbp = hp.clickFSQABrowserMenu();
			boolean navigateToFSQAForms = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.ITEMS,FSQATAB.FORMS);
			TestValidation.IsTrue(navigateToFSQAForms, 
								  "For Items resource, NAVIGATED to FSQABrowser > Forms tab",
								  "Failed to Navigate to FSQABrowser > Forms tab");

			boolean openForm = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, iFormName1);
			TestValidation.IsTrue(openForm, 
					 			  "OPENED form - " + iFormName1, 
					 			  "Failed to open form - " + iFormName1);
			
			FormOperations fo = new FormOperations(driver);
			boolean setResrc = fo.selectLocationResource(null, iForm1Resource);
			TestValidation.IsTrue(setResrc, 
								  "Resource SET to - " + iForm1Resource, 
								  "Failed to set Resource to - " + iForm1Resource);
			
			boolean fillSOData1 = fo.fillData(fNumberofMetalDetectorsBeingChecked, 
					Arrays.asList(vNumberofMetalDetectorsBeingChecked), null, false);
			TestValidation.IsTrue(fillSOData1, 
								  "APPLIED value " + vNumberofMetalDetectorsBeingChecked 
								  		+ " to field - " + fNumberofMetalDetectorsBeingChecked, 
								  "Failed to apply value to field - " + fNumberofMetalDetectorsBeingChecked);
			
			boolean fillSOData2 = fo.fillData(fMetalDetectorLocation, Arrays.asList(vMetalDetectorLocation, v2MetalDetectorLocation)
					, null, false);
			TestValidation.IsTrue(fillSOData2, 
								  "APPLIED values " + vMetalDetectorLocation + " & " + v2MetalDetectorLocation + " to field - " + fMetalDetectorLocation, 
								  "Failed to apply values to field - " + fMetalDetectorLocation);
			
			boolean fillSOData3 = fo.fillData(fIsTheLineRunningProduct, Arrays.asList(vIsTheLineRunningProduct, v2IsTheLineRunningProduct)
					, null, false);
			TestValidation.IsTrue(fillSOData3, 
								  "APPLIED value " + vIsTheLineRunningProduct + " to field - " + fIsTheLineRunningProduct, 
								  "Failed to apply value to field - " + fIsTheLineRunningProduct);
			
			boolean fillSOData4 = fo.fillData(fProductionTime, Arrays.asList(vProductionTime, v2ProductionTime)
					, null, false);
			TestValidation.IsTrue(fillSOData4, 
								  "APPLIED value " + vProductionTime + " to field - " + fProductionTime, 
								  "Failed to apply value to field - " + fProductionTime);
			
			boolean fillSOData5 = fo.fillData(fFerrousStandard, Arrays.asList(vFerrousStandard, v2FerrousStandard)
					, null, false);
			TestValidation.IsTrue(fillSOData5, 
								  "APPLIED value " + vFerrousStandard + " to field - " + fFerrousStandard, 
								  "Failed to apply value to field - " + fFerrousStandard);
			
			boolean fillSOData6 = fo.fillData(fHowManyTimesAreFerrousStandardsBeingPassed, 
					Arrays.asList(vHowManyTimesAreFerrousStandardsBeingPassed, v2HowManyTimesAreFerrousStandardsBeingPassed), null, false);
			TestValidation.IsTrue(fillSOData6, 
								  "APPLIED value " + vHowManyTimesAreFerrousStandardsBeingPassed 
								  		+ " to field - " + fHowManyTimesAreFerrousStandardsBeingPassed, 
								  "Failed to apply value to field - " + fHowManyTimesAreFerrousStandardsBeingPassed);
			
			boolean fillSOData7 = fo.fillData(fFerrousSampleDetectedAndRejected, 
					Arrays.asList(vFerrousSampleDetectedAndRejected, v2FerrousSampleDetectedAndRejected), null, false);
			TestValidation.IsTrue(fillSOData7, 
								  "APPLIED value " + vFerrousSampleDetectedAndRejected 
								  		+ " to field - " + fFerrousSampleDetectedAndRejected, 
								  "Failed to apply value to field - " + fFerrousSampleDetectedAndRejected);
			
			List<String> idFerrousSampleDetectedAndRejected = fo.getListOfFieldIDsForField(fFerrousSampleDetectedAndRejected);
			FieldProperties complianceChk1 = new FieldProperties();
			complianceChk1.fieldID = idFerrousSampleDetectedAndRejected.get(0);
			complianceChk1.complianceStatusCheck = true;
			complianceChk1.complianceStatus = "Non-Compliant";
			boolean verifyCompliance1 = fo.setFormFieldProperties(null, complianceChk1);
			TestValidation.IsTrue(verifyCompliance1, 
								  "VERIFIED field " + fFerrousSampleDetectedAndRejected + " is Non Compliant", 
								  "Failed to Verify compliance for field - " + fFerrousSampleDetectedAndRejected);
			
			FieldProperties setCorrectn1 = new FieldProperties();
			setCorrectn1.fieldID = idFerrousSampleDetectedAndRejected.get(0);
			setCorrectn1.allowCorrectionsCheck = true;
			setCorrectn1.allowCorrectionsValue = crFerrousSampleDetectedAndRejected;
			boolean fillCorrection1 = fo.setFormFieldProperties(null, setCorrectn1);
			TestValidation.IsTrue(fillCorrection1, 
								  "SET correction for field " + fFerrousSampleDetectedAndRejected 
								  		+ " as " + crFerrousSampleDetectedAndRejected, 
								  "Failed to Set correction for field - " + fFerrousSampleDetectedAndRejected);
			
			FieldProperties complianceChk5 = new FieldProperties();
			complianceChk5.fieldID = idFerrousSampleDetectedAndRejected.get(1);
			complianceChk5.complianceStatusCheck = true;
			complianceChk5.complianceStatus = "No Compliance";
			boolean verifyCompliance5 = fo.setFormFieldProperties(null, complianceChk5);
			TestValidation.IsTrue(verifyCompliance5, 
								  "VERIFIED field " + fFerrousSampleDetectedAndRejected + " is having No Compliance", 
								  "Failed to Verify compliance for field - " + fFerrousSampleDetectedAndRejected);
			
			boolean fillSOData8 = fo.fillData(fNonFerrousStandard, Arrays.asList(vNonFerrousStandard, v2NonFerrousStandard)
					, null, false);
			TestValidation.IsTrue(fillSOData8, 
								  "APPLIED value " + vNonFerrousStandard + " to field - " + fNonFerrousStandard, 
								  "Failed to apply value to field - " + fNonFerrousStandard);
			
			boolean fillSOData9 = fo.fillData(fHowManyTimesAreNonFerrousStandardsBeingPassed, 
					Arrays.asList(vHowManyTimesAreNonFerrousStandardsBeingPassed, v2HowManyTimesAreNonFerrousStandardsBeingPassed), null, false);
			TestValidation.IsTrue(fillSOData9, 
								  "APPLIED value " + vHowManyTimesAreNonFerrousStandardsBeingPassed 
								  		+ " to field - " + fHowManyTimesAreNonFerrousStandardsBeingPassed, 
								  "Failed to apply value to field - " + fHowManyTimesAreNonFerrousStandardsBeingPassed);
			
			boolean fillSOData10 = fo.fillData(fNonFerrousSampleDetectedAndRejected, 
					Arrays.asList(vNonFerrousSampleDetectedAndRejected, v2NonFerrousSampleDetectedAndRejected), null, false);
			TestValidation.IsTrue(fillSOData10, 
								  "APPLIED value " + vNonFerrousSampleDetectedAndRejected 
								  		+ " to field - " + fNonFerrousSampleDetectedAndRejected, 
								  "Failed to apply value to field - " + fNonFerrousSampleDetectedAndRejected);
			
			List<String> idNonFerrousSampleDetectedAndRejected = fo.getListOfFieldIDsForField(fNonFerrousSampleDetectedAndRejected);
			FieldProperties complianceChk2 = new FieldProperties();
			complianceChk2.fieldID = idNonFerrousSampleDetectedAndRejected.get(0);
			complianceChk2.complianceStatusCheck = true;
			complianceChk2.complianceStatus = "Compliant";
			boolean verifyCompliance2 = fo.setFormFieldProperties(null, complianceChk2);
			TestValidation.IsTrue(verifyCompliance2, 
								  "VERIFIED field " + fFerrousSampleDetectedAndRejected + " is Compliant", 
								  "Failed to Verify compliance for field - " + fFerrousSampleDetectedAndRejected);
			
			FieldProperties complianceChk6 = new FieldProperties();
			complianceChk6.fieldID = idNonFerrousSampleDetectedAndRejected.get(1);
			complianceChk6.complianceStatusCheck = true;
			complianceChk6.complianceStatus = "No Compliance";
			boolean verifyCompliance6 = fo.setFormFieldProperties(null, complianceChk2);
			TestValidation.IsTrue(verifyCompliance6, 
								  "VERIFIED field " + fFerrousSampleDetectedAndRejected + " is having No Compliance", 
								  "Failed to Verify compliance for field - " + fFerrousSampleDetectedAndRejected);
			
			boolean fillSOData11 = fo.fillData(fStainlessSteelStandard, Arrays.asList(vStainlessSteelStandard, v2StainlessSteelStandard)
					, null, false);
			TestValidation.IsTrue(fillSOData11, 
								  "APPLIED value " + vStainlessSteelStandard + " to field - " + fStainlessSteelStandard, 
								  "Failed to apply value to field - " + fStainlessSteelStandard);
			
			boolean fillSOData12 = fo.fillData(fHowManyTimesAreStainlessSteelStandardsBeingPassed, 
					Arrays.asList(vHowManyTimesAreStainlessSteelStandardsBeingPassed, v2HowManyTimesAreStainlessSteelStandardsBeingPassed), null, false);
			TestValidation.IsTrue(fillSOData12, 
								  "APPLIED value " + vHowManyTimesAreStainlessSteelStandardsBeingPassed 
								  		+ " to field - " + fHowManyTimesAreStainlessSteelStandardsBeingPassed, 
								  "Failed to apply value to field - " + fHowManyTimesAreStainlessSteelStandardsBeingPassed);
			
			boolean fillSOData13 = fo.fillData(fStainlessSteelSampleDetectedAndRejected, 
					Arrays.asList(vStainlessSteelSampleDetectedAndRejected, v2StainlessSteelSampleDetectedAndRejected), null, false);
			TestValidation.IsTrue(fillSOData13, 
								  "APPLIED value " + vStainlessSteelSampleDetectedAndRejected 
								  		+ " to field - " + fStainlessSteelSampleDetectedAndRejected, 
								  "Failed to apply value to field - " + fStainlessSteelSampleDetectedAndRejected);
			
			List<String> idStainlessSteelSampleDetectedAndRejected = fo.getListOfFieldIDsForField(fStainlessSteelSampleDetectedAndRejected);
			FieldProperties complianceChk3 = new FieldProperties();
			complianceChk3.fieldID = idStainlessSteelSampleDetectedAndRejected.get(0);
			complianceChk3.complianceStatusCheck = true;
			complianceChk3.complianceStatus = "Non-Compliant";
			boolean verifyCompliance3 = fo.setFormFieldProperties(null, complianceChk3);
			TestValidation.IsTrue(verifyCompliance3, 
								  "VERIFIED field " + fStainlessSteelSampleDetectedAndRejected + " is Non Compliant", 
								  "Failed to Verify compliance for field - " + fStainlessSteelSampleDetectedAndRejected);
			
			FieldProperties complianceChk7 = new FieldProperties();
			complianceChk7.fieldID = idStainlessSteelSampleDetectedAndRejected.get(1);
			complianceChk7.complianceStatusCheck = true;
			complianceChk7.complianceStatus = "No Compliance";
			boolean verifyCompliance7 = fo.setFormFieldProperties(null, complianceChk7);
			TestValidation.IsTrue(verifyCompliance7, 
								  "VERIFIED field " + fStainlessSteelSampleDetectedAndRejected + " is having No Compliance", 
								  "Failed to Verify compliance for field - " + fStainlessSteelSampleDetectedAndRejected);
			
			FieldProperties setCorrectn2 = new FieldProperties();
			setCorrectn2.fieldID = idStainlessSteelSampleDetectedAndRejected.get(0);
			setCorrectn2.allowCorrectionsCheck = true;
			setCorrectn2.allowCorrectionsValue = crStainlessSteelSampleDetectedAndRejected;
			boolean fillCorrection2 = fo.setFormFieldProperties(null, setCorrectn2);
			TestValidation.IsTrue(fillCorrection2, 
								  "SET correction for field " + fStainlessSteelSampleDetectedAndRejected 
								  		+ " as " + crStainlessSteelSampleDetectedAndRejected, 
								  "Failed to Set correction for field - " + fStainlessSteelSampleDetectedAndRejected);
			
			boolean fillSOData14 = fo.fillData(fWereConsecutiveSamplesRunToEnsureRejectTimingIsAcceptable, 
					Arrays.asList(vWereConsecutiveSamplesRunToEnsureRejectTimingIsAcceptable, 
					v2WereConsecutiveSamplesRunToEnsureRejectTimingIsAcceptable), null, false);
			TestValidation.IsTrue(fillSOData14, 
								  "APPLIED value " + vWereConsecutiveSamplesRunToEnsureRejectTimingIsAcceptable 
								  		+ " to field - " + fWereConsecutiveSamplesRunToEnsureRejectTimingIsAcceptable, 
								  "Failed to apply value to field - " + fWereConsecutiveSamplesRunToEnsureRejectTimingIsAcceptable);
			
			boolean fillSOData15 = fo.fillData(fWasLeadingMiddleAndTrailingEdgeOfTheBagChecked_Contd, 
					Arrays.asList(vWasLeadingMiddleAndTrailingEdgeOfTheBagChecked_Contd, 
					v2WasLeadingMiddleAndTrailingEdgeOfTheBagChecked_Contd), null, false);
			TestValidation.IsTrue(fillSOData15, 
								  "APPLIED value " + vWasLeadingMiddleAndTrailingEdgeOfTheBagChecked_Contd 
								  		+ " to field - " + fWasLeadingMiddleAndTrailingEdgeOfTheBagChecked_Contd, 
								  "Failed to apply value to field - " + fWasLeadingMiddleAndTrailingEdgeOfTheBagChecked_Contd);
			
			List<String> idWasLeadingMiddleAndTrailingEdgeOfTheBagChecked_Contd = fo.getListOfFieldIDsForField(fWasLeadingMiddleAndTrailingEdgeOfTheBagChecked_Contd);
			FieldProperties complianceChk4 = new FieldProperties();
			complianceChk4.fieldID = idWasLeadingMiddleAndTrailingEdgeOfTheBagChecked_Contd.get(0);
			complianceChk4.complianceStatusCheck = true;
			complianceChk4.complianceStatus = "Compliant";
			boolean verifyCompliance4 = fo.setFormFieldProperties(null, complianceChk4);
			TestValidation.IsTrue(verifyCompliance4, 
								  "VERIFIED field " + fWasLeadingMiddleAndTrailingEdgeOfTheBagChecked_Contd + " is Compliant", 
								  "Failed to Verify compliance for field - " + fWasLeadingMiddleAndTrailingEdgeOfTheBagChecked_Contd);
			
			FieldProperties complianceChk8 = new FieldProperties();
			complianceChk8.fieldID = idWasLeadingMiddleAndTrailingEdgeOfTheBagChecked_Contd.get(1);
			complianceChk8.complianceStatusCheck = true;
			complianceChk8.complianceStatus = "No Compliance";
			boolean verifyCompliance8 = fo.setFormFieldProperties(null, complianceChk8);
			TestValidation.IsTrue(verifyCompliance8, 
								  "VERIFIED field " + fWasLeadingMiddleAndTrailingEdgeOfTheBagChecked_Contd 
								  		+ " is having No Compliance", 
								  "Failed to Verify compliance for field - " + fWasLeadingMiddleAndTrailingEdgeOfTheBagChecked_Contd);
		
			FSQABrowserFormsPage ffp = new FSQABrowserFormsPage(driver);
			boolean submitForm = ffp.submitForm();
			TestValidation.IsTrue(submitForm, 
								  "SUBMITTED form - " + iFormName1, 
								  "Failed to submit form - " + iFormName1);
			
			boolean navigateToRecs = fbp.navigateToFSQATab(FSQATAB.RECORDS);
			TestValidation.IsTrue(navigateToRecs, 
								  "For customer category, NAVIGATED to FSQABrowser > Records tab", 
								  "Failed to navigate to FSQABrowser > Records tab");
					
			boolean applyFilterAndOpenRec = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, iFormName1);
			TestValidation.IsTrue(applyFilterAndOpenRec, 
								  "OPENED record - " + iFormName1, 
								  "Failed to open record - " + iFormName1);
			
		}
		finally {
			if(hp.clickHomepageLinkAndBypassPopup().error){
				TCGFailureMsg = "Could NOT land on FSQA Browser page after clicking on Home link";
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}
		}
	}	
	@AfterClass(alwaysRun = true)
	public void closeBrowser() throws InterruptedException {
		driver.close();
	}
}
