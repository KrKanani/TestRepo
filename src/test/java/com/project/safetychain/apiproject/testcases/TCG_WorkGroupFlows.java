package com.project.safetychain.apiproject.testcases;

import java.util.Arrays;

import org.json.simple.parser.ParseException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.project.safetychain.apiproject.models.Auth;
import com.project.safetychain.apiproject.models.WorkGroups;
import com.project.safetychain.apiproject.models.WorkGroups.WorkGroupDetails;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.utilities.ApiUtils;
import com.project.utilities.CommonMethods;
import com.project.utilities.JSONUtils;

public class TCG_WorkGroupFlows extends TestBase{

	Auth auth = null;
	ApiUtils apiUtils = null;
	JSONUtils jsonUtils = null;

	WorkGroups workgroup = null;

	WorkGroupDetails workGroupDetails = null;

	public String createWorkGroupName, copyWorkGroupName;

	@BeforeClass(alwaysRun = true)
	public void groupInit() {

		auth = new Auth();
		auth.setAccessToken();

		jsonUtils = new JSONUtils();
		apiUtils = new ApiUtils();

		createWorkGroupName = CommonMethods.dynamicText("API_CreateWorkGroup");
		copyWorkGroupName = CommonMethods.dynamicText("API_CopyWorkGroup");

	}

	@Test(description = "Work Groups || Create WorkGroup")
	public void Create_WorkGroup() throws InterruptedException, ParseException {


		workgroup = new WorkGroups();

		workGroupDetails = new WorkGroupDetails();
		workGroupDetails.workgroupName = createWorkGroupName;
		workGroupDetails.userIDs = Arrays.asList(WorkGroups.SUPERADMIN_USER_ID);

		boolean createWorkGroup = workgroup.createWorkGroup(workGroupDetails);
		TestValidation.IsTrue(createWorkGroup, "CREATED WorkGroup - "+createWorkGroupName, "FAILED to create WorkGroup - "+createWorkGroupName);

	}


	@Test(description = "Work Groups || Enable/Disable WorkGroup", dependsOnMethods = { "Create_WorkGroup" })
	public void EnableDisable_WorkGroup() throws InterruptedException, ParseException {

		workgroup = new WorkGroups();

		String getWorkGroupID = workgroup.getWorkGroupDetails(createWorkGroupName, "Id");

		boolean disableWorkGroup = workgroup.enableWorkGroup(getWorkGroupID, false);
		TestValidation.IsTrue(disableWorkGroup, "DISABLED WorkGroup - "+createWorkGroupName, "FAILED to disable WorkGroup - "+createWorkGroupName);

		boolean getWorkGroupEnableStatus = Boolean.parseBoolean(workgroup.getWorkGroupDetails(createWorkGroupName, "IsEnable"));
		TestValidation.IsFalse(getWorkGroupEnableStatus, "VERIFIED disabled WorkGroup status for - "+createWorkGroupName, "FAILED to verify disabled WorkGroup status for - "+createWorkGroupName);

		boolean enableWorkGroup = workgroup.enableWorkGroup(getWorkGroupID, true);
		TestValidation.IsTrue(enableWorkGroup, "ENABLED WorkGroup - "+createWorkGroupName, "FAILED to enable WorkGroup - "+createWorkGroupName);

		getWorkGroupEnableStatus = Boolean.parseBoolean(workgroup.getWorkGroupDetails(createWorkGroupName, "IsEnable"));
		TestValidation.IsTrue(getWorkGroupEnableStatus, "VERIFIED enabled WorkGroup status for - "+createWorkGroupName, "FAILED to verify enabled WorkGroup status for - "+createWorkGroupName);

	}


	@Test(description = "Work Groups || Copy WorkGroup")
	public void Copy_WorkGroup() throws InterruptedException, ParseException {

		workgroup = new WorkGroups();

		workGroupDetails = new WorkGroupDetails();
		workGroupDetails.workgroupName = copyWorkGroupName;
		workGroupDetails.userIDs = Arrays.asList(WorkGroups.SUPERADMIN_USER_ID);

		boolean createWorkGroup = workgroup.copyWorkGroup(workGroupDetails);
		TestValidation.IsTrue(createWorkGroup, "CREATED WorkGroup(Copy) - "+copyWorkGroupName, "FAILED to create WorkGroup(Copy) - "+copyWorkGroupName);

		boolean verifyUserSelection = Boolean.parseBoolean(workgroup.getUserDetails(copyWorkGroupName, WorkGroups.SUPERADMIN_USER_NAME, "IsChecked"));
		TestValidation.IsTrue(verifyUserSelection, "VERIFIED user association with - "+copyWorkGroupName, "FAILED to verify user association with - "+copyWorkGroupName);

	}
}
