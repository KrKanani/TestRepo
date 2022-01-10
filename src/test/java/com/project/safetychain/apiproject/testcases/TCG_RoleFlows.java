package com.project.safetychain.apiproject.testcases;


import org.json.simple.parser.ParseException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.project.safetychain.apiproject.models.Auth;
import com.project.safetychain.apiproject.models.Roles;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.utilities.ApiUtils;
import com.project.utilities.CommonMethods;
import com.project.utilities.JSONUtils;

public class TCG_RoleFlows extends TestBase{

	Auth auth = null;
	ApiUtils apiUtils = null;
	Roles roles = null;
	JSONUtils jsnUtils = null;

	String rolename = CommonMethods.dynamicText("Role");
	String rolename1 = CommonMethods.dynamicText("Role1");
	String rolename2 = CommonMethods.dynamicText("Role2");

	@BeforeClass(alwaysRun = true)
	public void groupInit() {

		auth = new Auth();
		auth.setAccessToken();
		roles = new Roles();
		jsnUtils = new JSONUtils();
		apiUtils = new ApiUtils();

	}

	@Test(description = "Create Role")
	public void Create_Role() throws InterruptedException, ParseException {

		String  roleId = roles.addRole(rolename);

		boolean validate = roles.getAllRoles(rolename);
		TestValidation.IsTrue(validate, 
				"Role created - "+rolename, 
				"FAILED to create role - "+rolename);

		boolean check= roles.getAllPermissionsForRole(rolename, roleId);
		TestValidation.IsTrue(check, 
				"Role permissions verified for - "+rolename, 
				"FAILED to verify role permissions for - "+rolename);

	}

	@Test(description = "Enable Role")
	public void Enable_Disable_Role() throws InterruptedException, ParseException {

		String  roleId = roles.addRole(rolename1);

		boolean validate = roles.getAllRoles(rolename1);
		TestValidation.IsTrue(validate, 
				"Role created - "+rolename1, 
				"FAILED to create role - "+rolename1);

		roles.enableDisableRole(roleId, false);

		boolean validate1 = roles.getAllRoles(rolename1);
		TestValidation.IsTrue(validate1, 
				"Role is disabled - "+rolename1, 
				"FAILED to get role status - "+rolename1);

		roles.enableDisableRole(roleId, true);

		boolean validate2 = roles.getAllRoles(rolename1);
		TestValidation.IsTrue(validate2, 
				"Role is enabled - "+rolename1, 
				"FAILED to get role status - "+rolename1);


	}

	@Test(description = "Copy Role")
	public void Copy_Role() throws InterruptedException, ParseException {

		String  roleId = roles.addRole(rolename2);

		boolean validate = roles.getAllRoles(rolename2);
		TestValidation.IsTrue(validate, 
				"Role created - "+rolename2, 
				"FAILED to create role - "+rolename2);

		String copyrolename = roles.copyRole(roleId, rolename2);

		boolean validate1 = roles.getAllRoles(copyrolename);
		TestValidation.IsTrue(validate1,
				"Copied Role created - "+copyrolename, 
				"FAILED to create copied role - "+copyrolename);
	}
}
