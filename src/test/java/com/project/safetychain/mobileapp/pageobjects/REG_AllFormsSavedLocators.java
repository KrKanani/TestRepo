package com.project.safetychain.mobileapp.pageobjects;
 

public class REG_AllFormsSavedLocators {

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

	// Refreshing All Forms

	public static final String AllFORMSBTN = "allformButton";

	public static final String SEARCH_FORMS_TEXTBOX = "//android.widget.EditText[@text='Search Forms']";
	public static final String REFRESH_ALL_FORMS = "refreshTextView";
	public static final String REFRESHBTN = "//android.widget.Button[@text='REFRESH']";

	public static final String SEARCH_SAVED_FORM_TEXTBOX = "//android.widget.EditText[@text='Search Saved Forms']";
	public static final String SAVEDBTN = "savedButton";
	public static final String FORMSAVEBTN = "saveBtn";

	public static final String FORMSUBMITBTN = "submitBtn";

	public static final String FAVBTN = "favButton";
	public static final String ADDTOFAV = "formfavBtn";
	public static final String SEARCH_FAVOURITES_TEXTBOX = "//android.widget.EditText[@text='Search Favorite Forms']";
	public static final String SEARCH_INBOX_TEXTBOX = "//android.widget.EditText[@text='Search Inbox']";
	public static final String mainLayout = "formmainLayout";

	public static final String SORTDESC = "sortDescBtn";

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

	// setting tab

	public static final String SETTINGTITLE = "headTitle";

	public static final String OFFLINEMODE = "//android.widget.TextView[@text='Offline Mode']";

	public static final String LIMITDATA = "//android.widget.TextView[@text='Limit Data']";

	public static final String LIMITDATADURATIOM = " //android.widget.TextView[@text='Limit Data load to the selected duration']";
	public static final String SELECTDURATION = "//android.widget.TextView[@text='Select Duration']";
	public static final String SELECTDURATIONANGLEBUTTON = "select_duration_indicator";
	public static final String ENABLELOGS = "//android.widget.TextView[@text='Enable Logs']";

	public static final String CLEARSUBMISSIONS = "//android.widget.TextView[@text='Clear Submissions']";

	public static final String OFFLINETOGGLE = "offlinetoggle";
	public static final String LIMITDATATOGGLE = "limitdatatoggle";
	public static final String ENABLELOGTOGGLE = "enablelogtoggle";
	public static final String CLEARSUBBTN = "clearsubmissionbtn";

	public static final String LIMITDATAITEMS = "limitdataitem";

	public static final String CLEARSUBMSG = "clearsubmissionmsgId";
	public static final String CANCELSUBMSSN = "cancelsubmissionbtn";
	public static final String CLEARLOGS = "clearLogsBtn";
	public static final String CLEARLOGMSG="clear_logs_msg";
	// Inbox footers

	public static final String WORKGRPBTN = "workgroupButton";
	public static final String WRKGRRTEXT = "workgroupText";
	public static final String STATUSBTN = "statusButton";
	public static final String STATUSTEXT = "statusText";
	public static final String DUEBYBTN = "duebyButton";
	public static final String DUEBYTEXT = "duebyText";

	public static final String LOCATIONLIST = "//kendo-grid-list//span";
	public static final String LOCCHECKBOX = "//kendo-grid-list//span[text()='LInst1_01.03_10.35.28']//preceding:: td//input[@type='checkbox']";

	public static final String ADDATTACHMENT = "//*[@id='attachments']/i[@class='fa fa-paperclip']";

	public static final String ATTACHEDFILE = "k-file-name";

	public static final String UPLOAD = "k-button k-upload-button";

	public static final String UPLOADPERCENT = "k-upload-pct";
	public static final String CLOSEUPLOAD = "//button[text()='CLOSE']";

	public static final String FORMBACK = "headBackBtn";

	public static final String CLICKHEADER = "srcHeader";

	public static final String CLICKFORMNAME = "formnameHeader";

	public static final String REFRESHINBOXMSGBTN = "messageBtn";

	public static final String FILTERICON = "handle";
	public static final String SEARCHICON = "searchBtn";

	public static final String groupHeaderTitle = "groupHeader";

	public static final String INBOXFILTERHEADER = "inboxFilterTitle";
	public static final String INBOXFILTERCROSS = "closeInboxPanel";
	public static final String FILTERCLEARBTN = "clearBtn";
	public static final String FILTERBTNINSIDE = "filterBtn";

	public static final String ASSIGNEDTOFILTER = "//android.widget.Button[@text='ASSIGNED TO']";
	public static final String STATUSFILTER = "//android.widget.Button[@text='STATUS']";
	public static final String DUEBYFILTER = "//android.widget.Button[@text='DUE BY']";
	public static final String ASSIGNEDANGLEBTN = "//android.widget.Button[@text='ASSIGNED TO']//following-sibling::android.widget.ImageView";
	public static final String STATUSANGLEBTN = "//android.widget.Button[@text='STATUS']//following-sibling::android.widget.ImageView";

	public static final String DUEBYANGLEBTN = "//android.widget.Button[@text='DUE BY']//following-sibling::android.widget.ImageView";

	public static final String RESOURCEHEADER = "resName";
	public static final String RESOURCEVALUE = "resNameValue";
	public static final String LOCATIONHEADER = "locationHeader";
	public static final String LOCATIONVALUE = "locationValue";

	public static final String RESOURCESEARCH = "resSearchName";
	public static final String AVAILABLERESOURCES = "resSearchName";
	public static final String RESOURCES = "resourceName";
	public static final String ACCESSTODOWNLOAD = "//android.widget.Button[contains(@text,'ALLOW ACCESS TO')]";
	public static final String ALLOW = "//android.widget.Button[@text='ALLOW']";

}
