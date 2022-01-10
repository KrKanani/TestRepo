package com.project.safetychain.webapp.testcases;

import java.util.ArrayList;
import java.util.List;

import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.project.safetychain.webapp.pageobjects.HomePage;
import com.project.safetychain.webapp.pageobjects.LoginPage;
import com.project.safetychain.webapp.pageobjects.VerificationsPage;
import com.project.safetychain.webapp.pageobjects.VerificationsPage.VerificationDetsParams;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.utilities.CommonMethods;
import com.project.utilities.ControlActions;


public class TCG_SMK_VerificationFlows extends TestBase  {

	ControlActions controlActions;
	CommonMethods common;
	VerificationsPage verification;
	LoginPage lp;
	HomePage hp;
	public static String verificationName;
	public static String verificationComment1;
	public static String verificationComment2;
	public static String roleName;

	@BeforeClass(alwaysRun = true)                       
	public void groupInit() throws InterruptedException {
		driver = launchbrowser();
		controlActions = new ControlActions(driver);
		controlActions.getUrl(applicationUrl);
		hp=new HomePage(driver);
		lp = new LoginPage(driver);
		common = new CommonMethods(driver);
		verification = new VerificationsPage(driver);

		verificationName = CommonMethods.dynamicText("AutoVN");
		verificationComment1=CommonMethods.dynamicText("AutoComment1");
		verificationComment2=CommonMethods.dynamicText("AutoComment2");
		roleName = "SuperAdmin";

		hp = lp.performLogin(adminUsername, adminPassword);
		if(hp.error) {
			TCGFailureMsg = "Could NOT login to application";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		hp.clickVerificationsMenu();	
		VerificationDetsParams vdp = new VerificationDetsParams();
		vdp.VerificationName = verificationName;
		boolean verificationCreated = verification.createVerification(vdp);
		if(!verificationCreated) {
			TCGFailureMsg = "Could NOT create verification" + vdp.VerificationName;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

	}

	@Test(description="Verify user is able to add Comments to verification")
	public void TestCase_31123() throws Exception{

		boolean clickEnableCommentToggle = verification.clickEnableComment();
		TestValidation.IsTrue(clickEnableCommentToggle, 
				"Clicked on Enable Comment Toggle " + verificationName, 
				"Failed to click on Enable Comment Toggle " + verificationName); 

		List<String> commentList = new ArrayList<String>();
		commentList.add(verificationComment1);
		commentList.add(verificationComment2);
		boolean setAddComment = verification.addComment(commentList);
		TestValidation.IsTrue(setAddComment, 
				"Able to Add " + commentList.size() + " Comment", 
				"Failed to Add Comment: " + verificationComment1 + " " + verificationComment2); 

		boolean updateVerification = verification.updateVerificationButton();
		TestValidation.IsTrue(updateVerification, 
				"Able to UPDATE THE verification " + verificationName, 
				"Failed to UPDATE verification:" +verificationName); 

	}


	@Test(description="Add Role from Verification")
	public void TestCase_31124() throws Exception{

		boolean setRole = verification.searchAndSetRole(roleName);
		TestValidation.IsTrue(setRole, 
				"Able to add Role from Verification: " + roleName, 
				"Failed to add Role from Verification: " + roleName); 

		boolean updateVerification = verification.updateVerificationButton();
		TestValidation.IsTrue(updateVerification, 
				"Able to UPDATE verification " + verificationName, 
				"Failed to UPDATE verification:" +verificationName); 

		boolean roleExists = verification.verifyRoleIsSet(roleName);
		TestValidation.IsTrue(roleExists, 
				"VERIFIED Role - " + roleName + " exists", 
				"Failed to verify Role - " + roleName + " exists");

	}

	@AfterClass(alwaysRun = true)
	public void closeBrowser() throws InterruptedException {
		driver.close();
	}
}