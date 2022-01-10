package com.project.safetychain.webapp.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.project.utilities.ControlActions;

public class VerificationsPage extends CommonPage{
	WebDriver driver;
	WebDriverWait wait;
	ControlActions controlActions;

	public VerificationsPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 40);
		controlActions = new ControlActions(driver);
	}

	/**
	 * Page Objects
	 */
	@FindBy(xpath = VerificationsPageLocators.ADDNEWVERIFICATION_BTN)
	public WebElement AddNewVerificationBtn;

	@FindBy(xpath = VerificationsPageLocators.ENABLEVERIFICATION_BTN)
	public WebElement EnableVerificationBtn;

	@FindBy(xpath = VerificationsPageLocators.ENABLECOMMENTN_BTN)
	public WebElement EnableCommentBtn;

	@FindBy(xpath = VerificationsPageLocators.DELETECOMMENT_LNK)
	public WebElement DeleteCommentlnk;

	@FindBy(xpath = VerificationsPageLocators.VERIFICATIONNAME_TEXTBOX)
	public WebElement VerificatinNameTxtbx;

	@FindBy(xpath = VerificationsPageLocators.VERIFICATIONNAME_TEXTBOX)
	public WebElement VerificationRoleTxtbx;

	@FindBy(xpath = VerificationsPageLocators.SEARCHEDROLE_CHECKBOX)
	public WebElement SearchedRoleCheckbox;
	
	//@FindBy(xpath = VerificationsPageLocators.SELECTROLECHECKBOX1)
	//public WebElement SELECTROLECHECKBOX1;

	@FindBy(xpath = VerificationsPageLocators.SELECTROLE_CHECKBOX)
	public WebElement SelectRoleCheckbox;

	@FindBy(xpath = VerificationsPageLocators.ADDCOMMENT_LNK)
	public WebElement AddCommentLnk;
	
	@FindBy(xpath = VerificationsPageLocators.DELETECOMMENT_LNK)
	public WebElement DeleteCommentLnk;
	
	@FindBy(xpath = VerificationsPageLocators.SEARCHROLE_TXT)
	public WebElement SearchRoleTxt;
	
	@FindBy(xpath = VerificationsPageLocators.SEARCHROLE_BTN)
	public WebElement SearchRoleBtn;

	@FindBy(xpath = VerificationsPageLocators.SAVE_BTN)
	public WebElement SaveBtn;
	
	@FindBy(xpath = VerificationsPageLocators.CLEARBTN)
	public WebElement ClearBtn;
	
	@FindBy(xpath = VerificationsPageLocators.UPDATE_BTN)
	public WebElement UpdateBtn;

	@FindBy(xpath = VerificationsPageLocators.VERIFY_ENABLE_DISABLE_VERFTN_BTN)
	public WebElement VerifyEnableDisableVerftnBtn;	

	@FindBy(xpath = VerificationsPageLocators.UPDATEVEIFICATION_ROLE_BTN)
	public WebElement UpdateVerificationRoleBtn;


	/**
	 * This method is used to click on ADD NEW VERIFICATION button.
	 * @author dahale_p
	 * @param none
	 * @return boolean This returns true if ADD NEW VERIFICATION button is clicked.
	 */
	public boolean clickAddNewVerificationBtn() { // name Changed by PD
		try {
			controlActions.WaitforelementToBeClickable(AddNewVerificationBtn);
			controlActions.clickOnElement(AddNewVerificationBtn);
			logInfo("Clicked on ADD NEW VERIFICATION button");
			Sync();
			return true;
		}
		catch(Exception e) {
			logError("Failed to click on ADD NEW VERIFICATION button "
					+ e.getMessage());
			return false;
		}		
	}


	/**
	 * This method is used to set New Verification Name
	 * @author dahale_p
	 * @param 0
	 * @return boolean This returns boolean true after setting value to text box
	 */
	public boolean setVerificationName(String verificationName) {
		try {
			controlActions.actionClearTextBox(VerificatinNameTxtbx);
			controlActions.WaitforelementToBeClickable(VerificatinNameTxtbx);		
			controlActions.clickOnElement(VerificatinNameTxtbx);
			controlActions.sendKeys(VerificatinNameTxtbx, verificationName);
			Sync();
			logInfo("The VerificatinName Text box" +" entered: "+ verificationName);
			return true;
		}
		catch(Exception e) {
			logError("Failed to set value for field " + VerificatinNameTxtbx + " :"
					+ e.getMessage());
			return false;
		}	
	}


	/**
	 * This method is used to enable verification 
	 * @author dahale_p
	 * @param 0
	 * @return boolean This returns boolean true after Clicking ENABLE VRIFICATION button
	 */
	public boolean clickEnableVerification() {
		try {
			controlActions.WaitforelementToBeClickable(EnableVerificationBtn);		
			controlActions.clickOnElement(EnableVerificationBtn);
			logInfo("Enable Verification Button" +" is Clicked ");
			return true;
		}
		catch(Exception e) {
			logError("Failed to click element  " + "Enable Verification Button" + " :"
					+ e.getMessage());
			return false;
		}	
	}


	/**
	 * This method is used to enable comments 
	 * @author dahale_p
	 * @param 0
	 * @return boolean This returns boolean true after Clicking ENABLE COMMENTS button
	 */
	public boolean clickEnableComment() { 
		try {
			controlActions.WaitforelementToBeClickable(EnableCommentBtn);		
			controlActions.clickOnElement(EnableCommentBtn);
			logInfo("Enable Comment Button" +" is Clicked ");
			return true;
		}
		catch(Exception e) {
			logError("Failed to click element  " + "Enable Comment Button" + " :"
					+ e.getMessage());
			return false;
		}	
	}
	/**
	 * This method is used to enable comments 
	 * @author dahale_p
	 * @param 0
	 * @return boolean This returns boolean true after Clicking ENABLE COMMENTS button
	 */
	public boolean clickAddComment() {
		try {
			controlActions.WaitforelementToBeClickable(AddCommentLnk);		
			controlActions.clickOnElement(AddCommentLnk);
			Sync();
			logInfo("Clicked on Add Comment link");
			return true;
		}
		catch(Exception e) {
			logError("Failed to click on Add Comment link + "
					+ e.getMessage());
			return false;
		}	
	}
	
	/**
	 * This method is used to add Comment
	 * @author dahale_p
	 * @param Commentnumber
	 * @param commenttext
	 * @return boolean This returns boolean true after adding new comment
	 */
	public boolean addComment(List<String> comment) {
		WebElement commentTxt = null;
		String commentText = null;
		try {
			

			
			for(int i = 0; i < comment.size(); i++) {
				if(!clickAddComment()) {
					return false;
				}

				commentTxt = controlActions.perform_GetElementByXPath(VerificationsPageLocators.COMMENT_TXT, 
																		  "COMMENTNUMBER", Integer.toString(i+1));
				
				commentText = comment.get(i);
				controlActions.WaitforelementToBeClickable(commentTxt);	
				commentTxt.clear();
				controlActions.clickOnElement(commentTxt);
				
				controlActions.sendKeys(commentTxt, commentText);
				logInfo("Comment set as - " + commentText);
			}
			logInfo(comment.size() + " comments set as per requirement");
			return true;
		}
		catch(Exception e) {
			logError("Failed to  :"
					+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to Save and verify added Verification
	 * @author dahale_p
	 * @param verificationname :-- takes verification name provided by user
	 * @return boolean This returns boolean true after Clicking ENABLE COMMENTS button
	 */
	public boolean saveVerification() {

		try {
			controlActions.WaitforelementToBeClickable(SaveBtn);		
			controlActions.clickOnElement(SaveBtn);
			logInfo("Save Button" +" is Clicked and Verification is added successfully ");
			Sync();

			return true;
		}
		catch(Exception e) {
			logError(" Failed to save verification "+ " :"
					+ e.getMessage());
			return false;
		}	
	}
	
	public boolean updateVerificationButton() {
		try {
			controlActions.WaitforelementToBeClickable(UpdateBtn);		
			controlActions.clickOnElement(UpdateBtn);
			Sync();
			logInfo("Save Button" +" is Clicked and Verification is updated successfully ");
			return true;
		}
		catch(Exception e) {
			logError(" Failed to save verification "+ " :" +
						e.getMessage());
			return false;
		}	
	}
	
	
	
	
		/**
	 * This method is used to search and select Verification Role
	 * @author dahale_p
	 * @param rolename
	 * @return boolean This returns boolean true after selecting searched roll
	 */
//	public boolean searchAndSetRole(String rolename) {
//		
//		WebElement rolenameChk = null;
//		try {
//			
//			controlActions.WaitforelementToBeClickable(SearchRoleTxt);
//			controlActions.clickOnElement(SearchRoleTxt);	
//			controlActions.sendKeys(SearchRoleTxt,rolename);
//			logInfo("Verification Role text set as : "+ rolename);
//			controlActions.WaitforelementToBeClickable(SearchRoleBtn);
//			controlActions.clickOnElement(SearchRoleBtn);
//			Sync();
//			logInfo("Clicked on search button");
//			rolenameChk = controlActions.perform_GetElementByXPath(VerificationsPageLocators.SELECTROLE_CHECKBOX, "ROLENAME", rolename);
////			if(!SearchedRoleCheckbox.isSelected())
////				controlActions.clickOnElement(SearchedRoleCheckbox);
//			Sync();
//			controlActions.WaitforelementToBeClickable(UpdateVerificationRoleBtn);
//			controlActions.clickOnElement(UpdateVerificationRoleBtn);
//			Sync();
//			
//			logInfo("Selected the checkbox for role:"+ rolename);
//			
//			return true;
//		}
//		catch(Exception e) {
//			logError("Failed to click   " +rolenameChk+SearchedRoleCheckbox
//					+ e.getMessage());
//			return false;
//		}	
//		
//	}
//	

	/**
 * This method is used to search and select Verification Role
 * @author dahale_p
 * @param rolename
 * @return boolean This returns boolean true after selecting searched roll
 */

	public boolean searchAndSetRole(String rolename) {
		
		WebElement rolenameChk = null;
		try {
			
			controlActions.WaitforelementToBeClickable(SearchRoleTxt);
			controlActions.clickOnElement(SearchRoleTxt);	
			controlActions.sendKeys(SearchRoleTxt,rolename);
			logInfo("Verification Role text set as : "+ rolename);
			controlActions.WaitforelementToBeClickable(SearchRoleBtn);
			controlActions.clickOnElement(SearchRoleBtn);
			Sync();
			logInfo("Clicked on search button");
			rolenameChk = controlActions.perform_GetElementByXPath(VerificationsPageLocators.SELECTROLE_CHECKBOX, "ROLENAME", rolename);
			controlActions.clickOnElement(SearchedRoleCheckbox);
			Sync();
			logInfo("Selected the checkbox for role:"+ rolename);
			
			return true;
		}
		catch(Exception e) {
			logError("Failed to click   " +rolenameChk+SearchedRoleCheckbox
					+ e.getMessage());
			return false;
		}	
		
	}
	
		
		/**
		 * This method is used to verify whether Verification & Role is get linked 
		 * @author dahale_p
		 * @param rolename
		 * @return boolean This returns boolean true after selecting searched roll
		 */
	public boolean verifyRoleIsSet(String rolename) {
		WebElement rolenameChk;
		
			try {
				rolenameChk = controlActions.perform_GetElementByXPath(VerificationsPageLocators.SELECTROLE_CHECKBOX, "ROLENAME", rolename);

				if(rolenameChk.isSelected()) {
					logInfo("Verification - '"+"' is linked with - '"+rolename+"'");
					return true;
				}
				
				logInfo("Role Linking' COMPLETED");
				return false;
			}catch(Exception e) {
				logError("ERROR Role Linking'  - "
						+e.getMessage());
				return false;
			}
		
		
	}
	
	

	
	/**
	* This method is used to create wrapper function of creation of Verification
	* @author dahale_p
	* @param none 
	* @return boolean This returns true if 
	*/
	public boolean createVerification(VerificationDetsParams vdp) {
		try {		
			if(!clickAddNewVerificationBtn()) {
				return false;
			}
			if(vdp.VerificationName != null) {
				if(!setVerificationName(vdp.VerificationName)) {
					return false;
				}
			}
					
			if(vdp.EnableVerification) {
				if(!clickEnableVerification()) {
					return false;
				}
			}
			
			if(vdp.AddComments != null) {
				if(!clickEnableComment()) {
					return false;
				}

				if(!addComment(vdp.AddComments)) {
					return false;
				}
			}
			
			if(vdp.Rolename != null) {
				if(!searchAndSetRole(vdp.Rolename)) {
					return false;
				}
			}
				
			if(!saveVerification()) {
				return false;
			}
			
			logInfo("Created verification - " + vdp.VerificationName);
			return true;
		}
		catch(Exception e) {
			logError("Failed to create verification - " + vdp.VerificationName
					+ e.getMessage());
			return false;
		}	
	}
	
	/**
	* This method is used to open an existing Verification
	* @author hingorani_a
	* @param verificationName The name of Verification to be opened 
	* @return boolean This returns true when verification is opened
	*/
	public boolean openExistingVerification(String verificationName) {
		WebElement VerificationLabel;

		try {
			VerificationLabel = controlActions.perform_GetElementByXPath(VerificationsPageLocators.VERIFICATION_LABEL,
					"VERIFICATION", verificationName);
			controlActions.perform_ClickWithJavaScriptExecutor(VerificationLabel);
			Sync();

			logInfo("Opened verification " + verificationName);
			return true;
		}
		catch(Exception e) {
			logError("Failed to open verification " + verificationName
					+ e.getMessage());
			return false;
		}
	}
	
	/**
	 * This method is used to enable or disable an existing verification 
	 * @author hingorani_a
	 * @param disable If set as true -> you will disable a verification
	 * 				  If set as false -> you will enable a verification
	 * @return boolean This returns boolean true after enabling/disabling the verification
	 */
	public boolean enableDisableVerification(boolean disable) {
		String verificationStatus = null;
		try {
			
			verificationStatus = VerifyEnableDisableVerftnBtn.getAttribute("aria-checked");
			if(disable && verificationStatus.equals("true")) {
				if(!clickEnableVerification())
					return false;
				logInfo("Disabled the verification");
			}
			else if (!disable && verificationStatus.equals("false")) {
				if(!clickEnableVerification())
					return false;
				logInfo("Enabled the verification");
			}
			return true;
		}
		catch(Exception e) {
			logError("Failed to enable/disable verification"
					+ e.getMessage());
			return false;
		}	
	}
	
	/**
	 * This method is used to search and enable or disable an existing verification 
	 * @author hingorani_a
	 * @param verificationName The name of Verification to be opened 
	 * @param disable If set as true -> you will disable a verification
	 * 				  If set as false -> you will enable a verification
	 * @return boolean This returns boolean true after enabling/disabling the verification
	 */
	public boolean searchAndEnableDisableVerification(String verificationName, boolean disable) {
		try {
			if(!openExistingVerification(verificationName))
				return false;
			
			if(!enableDisableVerification(disable))
				return false;
			
			if(!updateVerificationButton())
				return false;
			
			logInfo("The verification " + verificationName + " is enabled/disabled");
			return true;
		}
		catch(Exception e) {
			logError("Failed to enable/disable verification " + verificationName
					+ e.getMessage());
			return false;
		}	
	}
	
	/**
	 * This method is used to set New Verification Name
	 * @author dahale_p
	 * @param 0
	 * @return boolean This returns boolean true after setting value to text box
	 */
	public boolean updateVerificationName(String verificationName,String verificationName1) {
		try {
			controlActions.actionClearTextBox(VerificatinNameTxtbx);
			controlActions.WaitforelementToBeClickable(VerificatinNameTxtbx);		
			controlActions.clickOnElement(VerificatinNameTxtbx);
			controlActions.sendKeys(VerificatinNameTxtbx, verificationName1);
			Sync();
			logInfo("The VerificatinName Text box" +" entered: "+ verificationName1);
			return true;
		}
		catch(Exception e) {
			logError("Failed to set value for field " + VerificatinNameTxtbx + " :"
					+ e.getMessage());
			return false;
		}	
	}
	
	/**
	 * This method is used to click
	 * @author dahale_p
	 * @param 0
	 * @return boolean This returns boolean true after clicking on clear button
	 */
	public boolean clickClearButton() {
		try {
			controlActions.WaitforelementToBeClickable(ClearBtn);
			Sync();
			controlActions.clickOnElement(ClearBtn);
			Sync();
			logInfo("The Clear button has been clicked :");
			return true;
		}
		catch(Exception e) {
			logError("Failed to click  clear button " + VerificatinNameTxtbx + " :"
					+ e.getMessage());
			return false;
		}	
	}
	
	/**
	 * This method is used to enable comments 
	 * @author dahale_p
	 * @param 0
	 * @return boolean This returns boolean true after Clicking ENABLE COMMENTS button
	 */
	public boolean deleteCommentLnk(String commentNumber) {
		try {
			int a =135;
			 WebElement deleteComment = controlActions.perform_GetElementByXPath(VerificationsPageLocators.DELETECOMMENT_LNK,
					"COMMENTNUMBER", commentNumber);
			 Sync(); 
			controlActions.WaitforelementToBeClickable(deleteComment);		
			controlActions.clickOnElement(deleteComment);
			Sync();
			logInfo("Clicked on Delete Comment link");
			return true;
		}
		catch(Exception e) {
			logError("Failed to click on delete Comment link + "
					+ e.getMessage());
			return false;
		}	
	}
	
	
	/**
	 * This method is used to verify whether Verification & Role is get linked 
	 * @author dahale_p
	 * @param rolename
	 * 25 Nov 2021
	 * @return boolean This returns boolean true after selecting searched roll
	 */
	public boolean setRoleForVerification(String rolename) {
		WebElement rolenameChk;
		
			try {
				rolenameChk = controlActions.perform_GetElementByXPath(VerificationsPageLocators.SELECTROLE_CHECKBOX, "ROLENAME", rolename);

				if(rolenameChk.isSelected()) {
					logInfo("Verification - '"+"' is linked with - '"+rolename+"'");
					
				}
					else {
						
					controlActions.click(rolenameChk);
					controlActions.WaitforelementToBeClickable(UpdateBtn);		
					controlActions.clickOnElement(UpdateBtn);
					Sync();
					
					logInfo("Verification - Direct Obseravtion'"+"' is set to - '"+rolename+"'");
				
				}
				
				return true;
			}catch(Exception e) {
				logError("ERROR Role Linking'  - "
						+e.getMessage());
				return false;
			}
		
		
	}
	
	/**
	* This method is used to create wrapper function of creation of Verification
	* @author dahale_p
	* @param none 
	* @return boolean This returns true if 
	*/
	public boolean createVerificationWithoutRole(VerificationDetsParams vdp) {
		try {		
			if(!clickAddNewVerificationBtn()) {
				return false;
			}
			if(vdp.VerificationName != null) {
				if(!setVerificationName(vdp.VerificationName)) {
					return false;
				}
			}
					
			if(vdp.EnableVerification) {
				if(!clickEnableVerification()) {
					return false;
				}
			}
			
								
			if(!saveVerification()) {
				return false;
			}
			
			logInfo("Created verification - " + vdp.VerificationName);
			return true;
		}
		catch(Exception e) {
			logError("Failed to create verification - " + vdp.VerificationName
					+ e.getMessage());
			return false;
		}	
	}
	
	
	/**
 * This method is used to search and select Verification Role
 * @author dahale_p
 * @param rolenameNew
 * @return boolean This returns boolean true after selecting searched roll
 */

	public boolean searchRole(String rolenameNew) {
		//WebElement rolenameChk = null;
		try {
			controlActions.WaitforelementToBeClickable(SearchRoleTxt);
			controlActions.clickOnElement(SearchRoleTxt);	
			controlActions.sendKeys(SearchRoleTxt,rolenameNew);
			logInfo("Verification Role text set as : "+ rolenameNew);
			controlActions.WaitforelementToBeClickable(SearchRoleBtn);
			Sync();
			controlActions.clickOnElement(SearchRoleBtn);
			logInfo("Clicked on search button");
			Thread.sleep(2000);
			return true;
		}
		catch(Exception e) {
			logError("Is Role name Not available  " + rolenameNew +SearchedRoleCheckbox
					+ e.getMessage());
			return false;
		}	
		
	}
	
	/** This method used to check for role present in the roles list under the search box
	 * @author ahmed_tw
	 * @param roleName [String] : Name of the Role
	 * @return [boolean] : True if the role is present else false
	 */
	public boolean isRolePresent(String roleName) { 
		try {
			//int i =11;
			WebElement role = controlActions.perform_GetElementByXPath(VerificationsPageLocators.ROLE_LABEL, "ROLE_NAME", roleName);
			if(role.isDisplayed()) {
				logInfo("Role is Present " + roleName);
				return true;
			}
			logInfo("Role is NOT Present " + roleName);
			return false;
		}
		catch(Exception e) {
			logError("Could not find role is present or not  " + roleName+ e.getMessage());
			return false;
		}	
	}
	
	public static class VerificationDetsParams{
		public String VerificationName;
		public Boolean EnableVerification = true;
		public List<String> AddComments ;
		public String Rolename;
		
	}

}

