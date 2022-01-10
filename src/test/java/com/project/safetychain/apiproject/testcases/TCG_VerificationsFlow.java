package com.project.safetychain.apiproject.testcases;

import java.util.Arrays;

import org.json.simple.parser.ParseException;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.project.safetychain.apiproject.models.Auth;
import com.project.safetychain.apiproject.models.Roles;
import com.project.safetychain.apiproject.models.Verifications;
import com.project.safetychain.apiproject.models.Verifications.SingleDataResponse1;
import com.project.safetychain.apiproject.models.Verifications.VerificationDetails;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.utilities.ApiUtils;
import com.project.utilities.CommonMethods;
import com.project.utilities.JSONUtils;

public class TCG_VerificationsFlow extends TestBase{

	Auth auth = null;
	ApiUtils apiUtils = null;
	JSONUtils jsonUtils = null;

	Roles roles = null;
	Verifications verification1 = null;

	VerificationDetails verification1Details = null;

	public String role1Name, roleId;
	public String verification1Name, verification1ID;

	@BeforeClass(alwaysRun = true)
	public void groupInit() {

		auth = new Auth();
		auth.setAccessToken();

		jsonUtils = new JSONUtils();
		apiUtils = new ApiUtils();

		role1Name = CommonMethods.dynamicText("API_Role1");
		verification1Name = CommonMethods.dynamicText("API_Verification1");

		roles = new Roles();
		roleId = roles.addRole(role1Name);

		if(roleId==null) {
			TCGFailureMsg = "COULD not create role";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);	
		}

	}

	@Test(description = "Manage Verifications || Create Verification")
	public void Create_Verification() throws InterruptedException, ParseException {

		String roleDetails [][] = {{role1Name,roleId}, {Verifications.SUPERADMIN_ROLE_NAME,Verifications.SUPERADMIN_ROLE_ID}};

		verification1 = new Verifications();

		verification1Details = new VerificationDetails();

		verification1Details.VerificationName = verification1Name;
		verification1Details.IsEnable = true;
		verification1Details.IsCommentsEnabled = true;
		verification1Details.comments = Arrays.asList("API - Comment 1", "API - Comment 2");
		verification1Details.roles = roleDetails;

		SingleDataResponse1 createVerification = verification1.createVerification(verification1Details);
		verification1Details.VerificationID =  createVerification.Data;;

		TestValidation.IsTrue(createVerification.Status, "CREATED Verification - "+verification1Name, "FAILED to create Verification - "+verification1Name);

		verification1ID = verification1.getVerification(verification1Name);
		TestValidation.IsTrue(verification1ID.equals(verification1Details.VerificationID), "VERIFIED added Verification - "+verification1Name, "FAILED to verify added Verification - "+verification1Name);

	}


	@Test(description = "Manage Verifications || Enable/Disable Verification & Comments",  dependsOnMethods = { "Create_Verification" })
	public void Update_VerificationSettings() throws InterruptedException, ParseException {

		verification1Details.IsEnable = false;
		boolean updateSettings = verification1.updateVerificationSettings(verification1Details);
		TestValidation.IsTrue(updateSettings, "UPDATED Verification Settings ->  IsEnable - false for "+verification1Name, 
				"FAILED to update Verification Settings ->  IsEnable - false for - "+verification1Name);

		verification1Details.IsEnable = true;
		verification1Details.IsCommentsEnabled = false;
		updateSettings = verification1.updateVerificationSettings(verification1Details);
		TestValidation.IsTrue(updateSettings, "UPDATED Verification Settings ->  IsEnable - true &  IsCommentsEnabled - false for "+verification1Name, 
				"FAILED to update Verification Settings ->  IsEnable - true &  IsCommentsEnabled - false for  - "+verification1Name);

		boolean verificationCommentStatus = verification1.getVerificationDetail(verification1ID,"IsCommentsEnabled");
		TestValidation.IsFalse(verificationCommentStatus, "VERIFIED Verification Settings -> IsCommentsEnabled - false for "+verification1Name, 
				"FAILED to verify Verification Settings -> IsCommentsEnabled - false for  - "+verification1Name);

		verification1Details.IsCommentsEnabled = true;
		updateSettings = verification1.updateVerificationSettings(verification1Details);
		TestValidation.IsTrue(updateSettings, "UPDATED Verification Settings -> IsCommentsEnabled - true for "+verification1Name, 
				"FAILED to update Verification Settings -> IsCommentsEnabled - true - "+verification1Name);

		verificationCommentStatus = verification1.getVerificationDetail(verification1ID,"IsCommentsEnabled");
		TestValidation.IsTrue(verificationCommentStatus, "VERIFIED Verification Settings -> IsCommentsEnabled - true for "+verification1Name, 
				"FAILED to verify Verification Settings -> IsCommentsEnabled - true - "+verification1Name);

	}

}
