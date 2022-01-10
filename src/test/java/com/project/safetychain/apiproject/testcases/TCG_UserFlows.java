package com.project.safetychain.apiproject.testcases;

import org.json.simple.parser.ParseException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.project.safetychain.apiproject.models.Auth;

import com.project.safetychain.apiproject.models.Users;
import com.project.safetychain.apiproject.models.Users.UserDetails;
import com.project.safetychain.apiproject.models.Users.UserType;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.utilities.ApiUtils;
import com.project.utilities.CommonMethods;
import com.project.utilities.JSONUtils;

public class TCG_UserFlows  extends TestBase{

	Auth auth = null;
	ApiUtils apiUtils = null;
	JSONUtils jsonUtils = null;

	Users internalUser = null, supplierUser = null;

	UserDetails userDetails = null;

	public String internalUserName, supplierUserName;

	@BeforeClass(alwaysRun = true)
	public void groupInit() {

		auth = new Auth();
		auth.setAccessToken();

		jsonUtils = new JSONUtils();
		apiUtils = new ApiUtils();

		internalUserName = CommonMethods.dynamicText("API_InternalUser");
		supplierUserName = CommonMethods.dynamicText("API_SupplierUser");

	}

	@Test(description = "User || Create Internal User")
	public void Create_InternalUser() throws InterruptedException, ParseException {

		String locationsDetails [][] = {{Users.ALL_LOCATION_NAME,Users.ALL_LOCATION_ID}};
		String roleDetails [][] = {{Users.SUPERADMIN_ROLE_NAME,Users.SUPERADMIN_ROLE_ID}};

		internalUser = new Users();

		userDetails = new UserDetails();
		userDetails.userType = UserType.INTERNAL_USER;
		userDetails.username = internalUserName;
		userDetails.locations = locationsDetails;
		userDetails.roles = roleDetails;
		userDetails.pin = "1234";

		boolean createInternalUser = internalUser.createUser(userDetails);
		TestValidation.IsTrue(createInternalUser, "CREATED Internal User - "+internalUserName, "FAILED to create internal user - "+internalUserName);

	}

	@Test(description = "User || Create Supplier User")
	public void Create_SupplierUser() throws InterruptedException, ParseException {

		String supplierDetails [][] = {{Users.ALL_SUPPLIER_NAME,Users.ALL_SUPPLIER_ID}};

		supplierUser = new Users();

		userDetails = new UserDetails();
		userDetails.userType = UserType.SUPPLIER_USER;
		userDetails.username = supplierUserName;
		userDetails.suppliers = supplierDetails;

		boolean createInternalUser = supplierUser.createUser(userDetails);
		TestValidation.IsTrue(createInternalUser, "CREATED Supplier User - "+supplierUserName, "FAILED to create supplier user - "+supplierUserName);

	}

}
