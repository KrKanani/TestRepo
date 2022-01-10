package com.project.safetychain.pcapp.testcases;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.project.safetychain.pcapp.pageobjects.CommonScreen;
import com.project.safetychain.pcapp.pageobjects.FormViewScreen;
import com.project.safetychain.pcapp.pageobjects.FormViewScreen.FormDetailsPC;
import com.project.safetychain.pcapp.pageobjects.FormsScreen;
import com.project.safetychain.pcapp.pageobjects.LoginScreen;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.utilities.ControlActions_PCApp;

public class TCG_TysonFormSubmission1 extends TestBase{

	ControlActions_PCApp controlActionsPC;
	LoginScreen loginScreen;
	CommonScreen commonScreen;
	FormsScreen forms;
	FormViewScreen formview; 
	FormDetailsPC formDetailsPC;

	String formName1, location1, resource1;

	String field1 = "Number of Metal Detectors Being Checked - HUT";

	String groupField1 = "Metal Detector Location";
	String groupField2 = "Is the line running product?";
	String groupField3 = "Production Time";
	String groupField4 = "Ferrous Standard";
	String groupField5 = "How many times are ferrous standards being passed";
	String groupField6 = "Ferrous Sample Detected and Rejected?";
	String groupField7 = "Non-Ferrous Standard";
	String groupField8 = "How many times are non-ferrous standards being passed?";
	String groupField9 = "Non-Ferrous Sample Detected and Rejected?";
	String groupField10 = "Stainless Steel Standard";
	String groupField11 = "How many times are Stainless Steel standards being passed?";
	String groupField12 = "Stainless Steel Sample Detected and Rejected?";
	String groupField13 = "Were Consecutive Samples Run to Ensure Reject Timing is Acceptable?";
	String groupField14 = "Was leading, middle, and trailing edge of the bag checked to ensure the standard is detected at every location? - HUT";


	@BeforeClass(alwaysRun = true)
	public void groupInit() {
		try {

			formName1 = "HUT - Prepared Foods Core Form - Metal Detection";
			location1 = "SITE";
			resource1 = "10000016959 - SBF PRK BSGE FCPAT 2.0OZ";

			PCAppDriver = launchPCApp();

			controlActionsPC = new ControlActions_PCApp(PCAppDriver);

			forms = new FormsScreen(PCAppDriver);
			formview = new FormViewScreen(PCAppDriver);
			loginScreen = new LoginScreen(PCAppDriver);

			commonScreen = loginScreen.Login(pcAppTenantname, pcAppUsername, pcAppPassword);
			if(commonScreen.error) {
				TCGFailureMsg = "COULD not login to the application";
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}

		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Test(description="Form Submission - 'HUT - Prepared Foods Core Form - Metal Detection'")
	public void submitForm1() {	

		HashMap<String, List<String>> allFields = new LinkedHashMap<String, List<String>>();
		allFields.put("Number of Metal Detectors Being Checked - HUT 1",Arrays.asList("4"));

		allFields.put("Metal Detector Location 1",Arrays.asList("Line 3 Packaging 1st Metal Detector"));
		allFields.put("Is the line running product? 1",Arrays.asList("Yes"));
		allFields.put("Production Time 1",Arrays.asList("During Production"));
		allFields.put("Ferrous Standard 1",Arrays.asList("5.0 mm"));
		allFields.put("How many times are ferrous standards being passed 1",Arrays.asList("2"));
		allFields.put("Ferrous Sample Detected and Rejected? 1",Arrays.asList("Pass"));
		allFields.put("Non-Ferrous Standard 1",Arrays.asList("4.0 mm"));
		allFields.put("How many times are non-ferrous standards being passed? 1",Arrays.asList("1"));
		allFields.put("Non-Ferrous Sample Detected and Rejected? 1",Arrays.asList("Pass"));
		allFields.put("Stainless Steel Standard 1",Arrays.asList("2.0 mm"));
		allFields.put("How many times are Stainless Steel standards being passed? 1",Arrays.asList("1"));
		allFields.put("Stainless Steel Sample Detected and Rejected? 1",Arrays.asList("Pass"));
		allFields.put("Were Consecutive Samples Run to Ensure Reject Timing is Acceptable? 1",Arrays.asList("Yes"));
		allFields.put("Was leading, middle, and trailing edge of the bag checked to ensure the standard is detected at every location? - HUT 1",Arrays.asList("Yes"));

		allFields.put("Metal Detector Location 2",Arrays.asList("Line 3 Packaging 1st Metal Detector"));
		allFields.put("Is the line running product? 2",Arrays.asList("Yes"));
		allFields.put("Production Time 2",Arrays.asList("During Production"));
		allFields.put("Ferrous Standard 2",Arrays.asList("5.0 mm"));
		allFields.put("How many times are ferrous standards being passed 2",Arrays.asList("2"));
		allFields.put("Ferrous Sample Detected and Rejected? 2",Arrays.asList("Pass"));
		allFields.put("Non-Ferrous Standard 2",Arrays.asList("4.0 mm"));
		allFields.put("How many times are non-ferrous standards being passed? 2",Arrays.asList("1"));
		allFields.put("Non-Ferrous Sample Detected and Rejected? 2",Arrays.asList("Pass"));
		allFields.put("Stainless Steel Standard 2",Arrays.asList("2.0 mm"));
		allFields.put("How many times are Stainless Steel standards being passed? 2",Arrays.asList("1"));
		allFields.put("Stainless Steel Sample Detected and Rejected? 2",Arrays.asList("Pass"));
		allFields.put("Were Consecutive Samples Run to Ensure Reject Timing is Acceptable? 2",Arrays.asList("Yes"));
		allFields.put("Was leading, middle, and trailing edge of the bag checked to ensure the standard is detected at every location? - HUT 2",Arrays.asList("Yes"));


		allFields.put("Metal Detector Location 3",Arrays.asList("Line 3 Packaging 1st Metal Detector"));
		allFields.put("Is the line running product? 3",Arrays.asList("Yes"));
		allFields.put("Production Time 3",Arrays.asList("During Production"));
		allFields.put("Ferrous Standard 3",Arrays.asList("5.0 mm"));
		allFields.put("How many times are ferrous standards being passed 3",Arrays.asList("2"));
		allFields.put("Ferrous Sample Detected and Rejected? 3",Arrays.asList("Pass"));
		allFields.put("Non-Ferrous Standard 3",Arrays.asList("4.0 mm"));
		allFields.put("How many times are non-ferrous standards being passed? 3",Arrays.asList("1"));
		allFields.put("Non-Ferrous Sample Detected and Rejected? 3",Arrays.asList("Pass"));
		allFields.put("Stainless Steel Standard 3",Arrays.asList("2.0 mm"));
		allFields.put("How many times are Stainless Steel standards being passed? 3",Arrays.asList("1"));
		allFields.put("Stainless Steel Sample Detected and Rejected? 3",Arrays.asList("Pass"));
		allFields.put("Were Consecutive Samples Run to Ensure Reject Timing is Acceptable? 3",Arrays.asList("Yes"));
		allFields.put("Was leading, middle, and trailing edge of the bag checked to ensure the standard is detected at every location? - HUT 3",Arrays.asList("Yes"));


		allFields.put("Metal Detector Location 4",Arrays.asList("Line 3 Packaging 1st Metal Detector"));
		allFields.put("Is the line running product? 4",Arrays.asList("Yes"));
		allFields.put("Production Time 4",Arrays.asList("During Production"));
		allFields.put("Ferrous Standard 4",Arrays.asList("5.0 mm"));
		allFields.put("How many times are ferrous standards being passed 4",Arrays.asList("2"));
		allFields.put("Ferrous Sample Detected and Rejected? 4",Arrays.asList("Pass"));
		allFields.put("Non-Ferrous Standard 4",Arrays.asList("4.0 mm"));
		allFields.put("How many times are non-ferrous standards being passed? 4",Arrays.asList("1"));
		allFields.put("Non-Ferrous Sample Detected and Rejected? 4",Arrays.asList("Pass"));
		allFields.put("Stainless Steel Standard 4",Arrays.asList("2.0 mm"));
		allFields.put("How many times are Stainless Steel standards being passed? 4",Arrays.asList("1"));
		allFields.put("Stainless Steel Sample Detected and Rejected? 4",Arrays.asList("Pass"));
		allFields.put("Were Consecutive Samples Run to Ensure Reject Timing is Acceptable? 4",Arrays.asList("Yes"));
		allFields.put("Was leading, middle, and trailing edge of the bag checked to ensure the standard is detected at every location? - HUT 4",Arrays.asList("Yes"));

		allFields.put("Metal Detector Location 5",Arrays.asList("Line 3 Packaging 1st Metal Detector"));
		allFields.put("Is the line running product? 5",Arrays.asList("Yes"));
		allFields.put("Production Time 5",Arrays.asList("During Production"));
		allFields.put("Ferrous Standard 5",Arrays.asList("5.0 mm"));
		allFields.put("How many times are ferrous standards being passed 5",Arrays.asList("2"));
		allFields.put("Ferrous Sample Detected and Rejected? 5",Arrays.asList("Pass"));
		allFields.put("Non-Ferrous Standard 5",Arrays.asList("4.0 mm"));
		allFields.put("How many times are non-ferrous standards being passed? 5",Arrays.asList("1"));
		allFields.put("Non-Ferrous Sample Detected and Rejected? 5",Arrays.asList("Pass"));
		allFields.put("Stainless Steel Standard 5",Arrays.asList("2.0 mm"));
		allFields.put("How many times are Stainless Steel standards being passed? 5",Arrays.asList("1"));
		allFields.put("Stainless Steel Sample Detected and Rejected? 5",Arrays.asList("Pass"));
		allFields.put("Were Consecutive Samples Run to Ensure Reject Timing is Acceptable? 5",Arrays.asList("Yes"));
		allFields.put("Was leading, middle, and trailing edge of the bag checked to ensure the standard is detected at every location? - HUT 5",Arrays.asList("Yes"));

		formDetailsPC = new FormDetailsPC();
		formDetailsPC.locationName = location1;
		formDetailsPC.resourceName = resource1;
		formDetailsPC.fieldDetails = allFields;

		FormsScreen selectFormsTab  = commonScreen.selectForms();
		TestValidation.IsFalse(selectFormsTab.error,
				"OPENED Forms Tab", 
				"COULD not open Forms Tab");

		boolean openForm = selectFormsTab.selectOpenForm(formName1);
		TestValidation.IsTrue(openForm, 
				"SELECTED & opened form - "+formName1, 
				"FAILED open form - "+formName1);

		boolean submitData = formview.fillDataInForm1(formDetailsPC);
		TestValidation.IsTrue(submitData, 
				"SUBMITTED data for form - "+formName1, 
				"FAILED to submit data for form - "+formName1);
	}

	@AfterClass(alwaysRun = true)
	public void closeBrowser() throws InterruptedException {
		PCAppDriver.close();
	}

}
