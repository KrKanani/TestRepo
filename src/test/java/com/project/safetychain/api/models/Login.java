package com.project.safetychain.api.models;

public class Login extends Auth {

	/*******************************
	 * Json Path Builder - Start
	 ****************************/
	public class LoginResponse {
		public Data Data;
		public boolean Status;
		public Errors Errors;
		public boolean RefreshToken;
		public String Message;
	}
	
	public class Data {
		public String IsSuccessfull;
		public String RemainingLoginAttempts;
		public String Token;
		public boolean IsFirstLogin;
		public boolean IsPartnerPortalUser;
		public String PartnerInfo;
		public String UserId;
		public boolean PasswordExpired;
		public String UserCurrentStatus;
		public String UserType;
	}
	
	public class Errors {
		
	}
	
	/*******************************
	 * Json Path Builder - End
	 ****************************/
}
