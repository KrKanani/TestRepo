package com.project.safetychain.api.models.support;

import org.testng.annotations.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.project.safetychain.api.models.Auth;
import com.project.safetychain.api.models.DMS;
import com.project.safetychain.api.models.ManageRequirement;
import com.project.testbase.TestBase;
import com.project.utilities.ApiUtils;
import com.project.utilities.CommonMethods;

public class TCG_ManageRequirement_Wrapper extends TestBase {

	Auth auth = null;
	ApiUtils apiUtils = null;
	Gson gson = new GsonBuilder().create();
	ManageRequirement mr = null;
	DMS dm = null;

	public String SuppTempName;
	public String DocTypeName;

	@Test(description = "Supplier template Creation")
	public void create_SupplierTemp() {

		mr = new ManageRequirement();
		SuppTempName = CommonMethods.dynamicText("Supp_Temp");
		auth = new Auth();
		auth.setAccessToken();


		String createSuppTemp = mr.createSuppTemp(SuppTempName);
		logInfo(createSuppTemp);

	}

	@Test(description = "Doc Type Creation")
	public void create_DocType() {

		dm = new DMS();
		DocTypeName = CommonMethods.dynamicText("API");
		auth = new Auth();
		auth.setAccessToken();

		String createdocType = dm.createDocType(DocTypeName);
		logInfo(createdocType);
		
		String createdm = dm.uploadDoc(DocTypeName);
		logInfo(createdm);

	}
}