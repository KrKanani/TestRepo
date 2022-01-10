package com.project.safetychain.mobileapp.pageobjects;

public class REG_SubmissionPageLocators {

	public static final String menuBtn = "MenuBtn";
	public static final String inboxBtn = "InboxBtn";
	public static final String dialogTitleTxt = "dialogTitle";
	public static final String InboxHeaderTxt = "InboxHeaderText";
	public static final String formName = "formName";
	public static final String Form_Name = "//android.widget.TextView[@text='FormNameLocator']";

	public static final String Search_Field = "searchTextView";
	public static final String Search_formName_Keyboard = "softkeyboardlayout";

	public static final String Resource_Search_Field1 = "ownsearchLayout";
	public static final String Resource_Name1 = "resourceName";

	public static final String Resource_Search_Field = "//android.widget.EditText[@text='Search']";
	public static final String Resource_Name = "//android.widget.TextView[contains(@text,'ResourceName')]";

	public static final String Form_Submit = "submitBtn";

	// Menu and SubMenu

	public static final String Main_menu = "ellipsisBtn";
	public static final String Submissions_Submenu = "//android.widget.TextView[@text='Submissions']";
	public static final String SAVE_SUBMENU = "//android.widget.TextView[@text='Saved']";
	public static final String Inbox_Submenu = "//android.widget.TextView[@text='Inbox']";
	public static final String Favourites_Submenu = "//android.widget.TextView[@text='Favorites']";
	public static final String Forms_Submenu = "//android.widget.TextView[@text='Forms']";
	public static final String Settings_Submenu = "//android.widget.TextView[@text='Settings']";

	// FieldValues
	public static final String NUMERIC_FIELD = "resultView_num";
	public static final String PARAGRAPH_FIELD = "resultView_para";
	public static final String FREETEXT_FIELD = "resultView_single";
	public static final String DATE_FIELD = "resultView_dt";
	public static final String DATETIME_FIELD = "resultView_dat";
	public static final String SELECTONE_FIELD = "//android.widget.Button[@text='VALUE']";
	public static final String SELECT_FIELD = "//android.widget.TextView[@text='FIELDNAME *']/ancestor::android.widget.LinearLayout[1]//android.widget.Button[@text='FIELDVALUE']";

	public static final String INPUT_FIELD = "//android.widget.TextView[@text='FIELDNAME *']/ancestor::android.widget.RelativeLayout[1]/following-sibling::android.widget.RelativeLayout[1]/child::android.widget.EditText";
	public static final String AGG_FIELD = "//android.widget.TextView[@text='FIELDNAME']/ancestor::android.widget.RelativeLayout[1]/following-sibling::android.widget.RelativeLayout[1]/child::android.widget.EditText";
	public static final String IMAGE_BTN ="//android.widget.EditText[@text='FIELDVALUE']/ancestor::android.widget.RelativeLayout[1]/following-sibling::android.widget.HorizontalScrollView[1]/descendant::android.widget.Button";


	// Calender Elements
	public static final String HEADERDATE = "android:id/date_picker_header_date";
	public static final String DATE_SELECTED = "//android.view.View[contains(@text,'DATE')]";
	public static final String DONE_BTN = "doneBtn";
	public static final String Submissions_Form_Status = "//android.widget.Button[@text='STATUS']";
	public static final String Submissions_Form_Status_Offline = "//android.widget.Button[@text='STATUS']";
	public static final String Form_Save = "saveBtn";
	public static final String SAVED_TIMESTAMP = "//android.widget.TextView[contains(@text,'TIMESTAMP')]";
	public static final String SAVED_TIMESTAMP1 = "formVersion";

	// Terminate App

	public static final String SNAPSHOT = "//com.google.android.apps.nexuslauncher:id/snapshot";
	public static final String CLEARAPP = "clearAnimView";
	public static final String CLEARAPP_SAFETY = "//android.widget.FrameLayout[@content-desc='SafetyChain M2,Unlocked']/android.view.View";

	// Settings menu

	public static final String OFFLINETOGGLE = "offlinetoggle";
	public static final String SETTINGS_BCKBTN = "backBtn";
	public static final String NETWORK_MODE_ICON = "networkModeIcon";

	// Camera Elements

	public static final String CAMERA_BTN = "cameraBtn";
	public static final String DIALOG_CAMERA_BTN = "dialogcameraBtn";
	public static final String DIALOG_GALLERY_BTN = "dailoggalleryBtn";
	public static final String DIALOG_CANCEL_BTN = "dialogcancelBtn";
	public static final String PERMISSION_BTN = "permission_allow_button";
	public static final String PERMISSION_BTN1 = "//android.widget.Button[@text='ALLOW']";
	public static final String CAMERACLICK_BTN = "//android.view.View[@content-desc='Shutter button']";
	public static final String PICTURE_OK = "//android.widget.ImageView[@content-desc='Done']";

	// GALLERY
	public static final String PHOTOS_OPTN = "//android.widget.TextView[@text='Photos']";
	public static final String SELECT_FOLDER_GALLERY = "//android.widget.TextView[@text='FOLDERNM']";
	public static final String SELECT_PHOTO_GALLERY = "(//android.view.ViewGroup[@content-desc='Photo taken on 25-Oct-2020 4:16:46 PM'])[2]";

	// Submissions
	public static final String RESUBMIT_BTN = "resubmitAllButton";
	public static final String RESUBMIT_POPUP_BTN = "btnOk";
	public static final String CLEARALL_BTN = "clearAllButton";
	public static final String FORM_STATUS = "formstatusBtn";
	public static final String FORM_VISIBLE = "headerLabel";

	public static final String RESUBMIT_LONGPRESS_BTN = "//android.widget.Button[@text='RESUBMIT']";

	public static final String DETAIL_LAYOUT = "detailLayout";

	// android.view.ViewGroup[@content-desc="Photo taken on 25-Oct-2020 4:16:46
	// PM"])[2]

	// Verify PIN Details
	public static final String PIN_BTN = "btnPinVerify";// btnPinVerify
	public static final String PIN_BTN_Save = "btnSave";
	public static final String PIN_BTN_Complaint = "btnCompliant";
	public static final String PIN_BTN_NonComplaint = "btnNonCompliant";

	public static final String PIN_USER = "txtUsername";
	public static final String PIN = "txtPin";
	public static final String PIN_COMMENTS = "txtComments";

	// VerifiedPinDetails

	public static final String PIN_OBSERVERDBY = "lblUsername";
	public static final String PIN_COMPLIANT = "txtCompliantVerified";
	public static final String PIN_COMMENT = "txtCommentVerified";

	// Field Level Added Settings

	public static final String FL_COMMENTS = "commentedittext";
	public static final String FL_CORRECTION_TXT = "correctionedittext";
	public static final String FL_CORRECTION_INBUILT = "//android.widget.Button[@text='CORRECTIONVALUE']";
	public static final String FL_RESOLVED_YES = "yesresolvedBtn";
	public static final String FL_RESOLVED_NO = "noresolvedBtn";

	// SavePage

	public static final String SORT_BTN = "sortDescBtn";

	// FieldName

	public static final String FL_NAME = "//android.widget.EditText[@text='FieldName']";

	// Clear All

	public static final String CLOSE_ALL_APPS = "//android.widget.Button[@text='CLEAR ALL']";

	// ClearSearch

	public static final String CLOSE_SEARCH = "closeBtn";
	
	
	
	public static final String FORM_NAME_LIST="//android.widget.TextView[@text='FORMNAME']/ancestor::android.widget.LinearLayout[1]/following-sibling::android.widget.LinearLayout[1]/descendant::android.widget.Button";

}
