package com.project.safetychain.webapp.pageobjects;

public class VerificationsPageLocators {

	
	/**id*/
	
	/**className*/
	
	/**xpath*/
	//BTN
	public static final String  ADDNEWVERIFICATION_BTN="//div[contains(text(),'Add New Verification')]";
	public static final String  ENABLEVERIFICATION_BTN="//span[contains(text(),'Enable verification')]/..//span[@class='k-switch-handle']";
	public static final String  ENABLECOMMENTN_BTN="//span[contains(text(),'Enable Comment List')]/..//span[@class='k-switch-handle']";
	public static final String 	ADDCOMMENT_LNK="//div[contains(text(),'Add Comment')]";
	//public static final String 	DELETECOMMENT_LNK="//div[@class='scs-manage-verification-delete-comment'][COMMENTNUMBER]";
	public static final String 	DELETECOMMENT_LNK="//div[@id = 'scs-verifications-comment-section']/div[COMMENTNUMBER]/div";
	public static final String 	DELETECOMMENT_LNK1="//div[@class='scs-manage-verification-each-config']//div[2]//div[1]";
	public static final String SAVE_BTN = "//span[contains(text(),'SAVE')]";
	public static final String UPDATE_BTN = "//span[contains(text(),'UPDATE')]";
	public static final String CLEARBTN = "//div[@class='scs-button scs-gray-button scs-admin-tool-footer-button']";
	public static final String UPDATEVEIFICATION_ROLE_BTN = "//span[contains(text(),'UPDATE')]";
	//TEXT BOX
	public static final String VERIFICATIONNAME_TEXTBOX = "//div[@id='scs-verfication-name-section']//input";
	public static final String VERIFICATIONROLE_TEXTBOX = "//input[@id='scs-search-input']";
	public static final String COMMENT_TXT ="//div[@id='scs-verifications-comment-section']//div[COMMENTNUMBER]//input";
	
	//CHECK BOX
	public static final String SEARCHROLE_BTN = "//*[@id='scs-search-button']/span";
	public static final String SEARCHROLE_TXT = "//*[@id='scs-search-input']";
	public static final String SEARCHEDROLE_CHECKBOX="//label[@class='k-checkbox-label']";
	public static final String SELECTROLE_CHECKBOX  = "//*[@id='scs-admin-manage-verification-roles']//label[contains(text(), 'ROLENAME')]/preceding-sibling::input";
	public static final String VERIFICATION_LABEL = "//div[@id='scs-admin-manage-verification-list']/div[2]/scs-list-view/div/div/div/div//span[contains(text(),\"VERIFICATION\")]";
	public static final String VERIFY_ENABLE_DISABLE_VERFTN_BTN = "//span[contains(text(),'Enable verification')]/..//span[@class='k-switch-container']";
	public static final String ROLE_LABEL = "//label[text()='ROLE_NAME']";
	
	
	
	

}
