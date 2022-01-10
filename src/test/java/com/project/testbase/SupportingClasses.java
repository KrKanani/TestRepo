package com.project.testbase;

public class SupportingClasses extends TestBase{
	
	/*******************************
	 * Execution Mode Types for TCG's pre-requisite
	 ****************************/
	public static class ExecutionMode {
		public static final String API = "API";
		public static final String UI = "UI";
		public static final String EXISTING = "EXISTING";
	}
	
	/*******************************
	 * Existing Mode for TCG's pre-requisite
	 ****************************/
	public static class PrereqData {
		public static final String FORM_NAME = "Form Name";
		public static final String LOCATION_CATEGORY = "Location Category";
		public static final String LOCATION_INSTANCE = "Location Instance";
		public static final String CUSTOMER_RESOURCE_CATEGORY = "Customer Resource Category";
		public static final String CUSTOMER_RESOURCE_INSTANCE = "Customer Resource Instance";
		public static final String CUSTOMER_RESOURCE_CATEGORY_1 = "Customer Resource Category 1";
		public static final String CUSTOMER_RESOURCE_INSTANCE_1 = "Customer Resource Instance 1";
		public static final String CUSTOMER_RESOURCE_CATEGORY_2 = "Customer Resource Category 2";
		public static final String CUSTOMER_RESOURCE_INSTANCE_2 = "Customer Resource Instance 2";
	}
	
	public static class SheetNames {
		public static final String SMOKE_FLOWS = "Smoke_Flows";
	}
	
}
