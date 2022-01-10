package com.project.safetychain.mobileapp.pageobjects;

public class REG_NotificationsLocators {

	public static final String usernameTxtFld = "username";
	public static final String passwordtxtFld = "password";
	public static final String LoginBtn = "loginBtn";

	public static final String USERNAME = "//android.widget.EditText[@text='Username']";
	public static final String PASSWORD = "//android.widget.EditText[@text='Password']";

	public static final String Main_menu = "ellipsisBtn";

	public static final String Close_Main_Menu = "close_menu";

	// SSO Login

	public static final String SSO_USRNM = "//android.widget.EditText[@resource-id='okta-signin-username']";
	public static final String SSO_PASS = "//android.widget.EditText[@resource-id='okta-signin-password']";
	public static final String SSO_SUBMIT = "//android.widget.Button[@resource-id='okta-signin-submit']";

	// LogOut Confirmation
	public static final String LOGOUT = "//android.widget.TextView[@text='Logout']";
	public static final String LOGOUT_POPUP_CONFIRM = "//android.widget.Button[@text='YES']";
	public static final String LOGOUT_POPUP_CANCEL = "//android.widget.Button[@text='CANCEL']";

 
	// SubMenuCount
	public static final String INBOX_COUNT = "//android.widget.RelativeLayout[@index='0']//android.widget.TextView[@text='Inbox']/following::android.widget.TextView[1]";
	public static final String SAVED_COUNT = "//android.widget.RelativeLayout[@index='2']//android.widget.TextView[@text='Saved']/following::android.widget.TextView[1]";
	public static final String FORMS_COUNT = "//android.widget.RelativeLayout[@index='4']//android.widget.TextView[@text='Forms']/following::android.widget.TextView[1]";

	public static final String FAVOURITES_COUNT = "//android.widget.RelativeLayout[@index='3']//android.widget.TextView[@text='Favorites']/following::android.widget.TextView[1]";
	public static final String SUBMISSIONS_COUNT = "//android.widget.RelativeLayout[@index='1']//android.widget.TextView[@text='Submissions']/following::android.widget.TextView[1]";

	public static final String LOCATIONTEXT = "//android.widget.TextView[@text='Location:']";
	public static final String RESOURCETEXT = "//android.widget.TextView[@text='Resource:']";
	public static final String Resource_Search_Field = "//android.widget.EditText[@text='Search']";

	public static final String NUMVALUE = "resultView_num";

	public static final String Form_Save = "saveBtn";
	public static final String SAVED_BADGE_BTNCOUNT = "savebadgeBtn";

	public static final String TASK_COUNT = "taskCount";

	public static final String SUBMISSION_COUNT = "submissionCount";

	public static final String menuListItems = "itemName";

	public static final String TASKCOUNTBADGE = "taskCount";

	public static final String TASKTITLE = "taskTitle";

	public static final String SUBMISSIONSCOUNTBADGE = "submissionCount";

	public static final String SUBMISSIONSBADGE = "submissionCountTitle";

	public static final String SEARCHBOX = "searchTextView";

	public static final String listForms = "//android.widget.ListView[@id='formlistView']//android.widget.LinearLayout";

	public static final String SEARCHNAME = "search_by_name";
	public static final String SEARCHRESOURCE = "search_by_resource";

	public static final String FORMNAME = "formName";

	public static final String FORMRESOURCE = "formResource";

	public static final String CLOSESEARCH = "closeBtn";

	public static final String NORESULTSFOUND = "headerLabel";

	public static final String LOCATIONS = "resourceName";

	public static final String FORMSAVEDDETAILS = "formVersion";
 

	// Inbox footers

	public static final String WORKGRPBTN = "workgroupButton";
	public static final String WRKGRRTEXT = "workgroupText";
	public static final String STATUSBTN = "statusButton";
	public static final String STATUSTEXT = "statusText";
	public static final String DUEBYBTN = "duebyButton";
	public static final String DUEBYTEXT = "duebyText";

	public static final String LOCATIONLIST = "//kendo-grid-list//span";
	public static final String LOCCHECKBOX = "//kendo-grid-list//span[text()='LInst1_01.03_10.35.28']//preceding:: td//input[@type='checkbox']";

	
	public static final String ADDATTACHMENT="//*[@id='attachments']/i[@class='fa fa-paperclip']";
	
	public static final String ATTACHEDFILE="k-file-name";
	
	public static final String UPLOAD="k-button k-upload-button";
	
	public static final String UPLOADPERCENT="k-upload-pct";
	public static final String CLOSEUPLOAD="//button[text()='CLOSE']";
	
	public static final String FORMBACK="headBackBtn";
	
	public static final String CLICKHEADER="srcHeader";
	
	public static final String CLICKFORMNAME="formnameHeader";
}
