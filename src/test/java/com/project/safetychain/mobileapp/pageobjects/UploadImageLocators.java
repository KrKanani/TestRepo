package com.project.safetychain.mobileapp.pageobjects;

public class UploadImageLocators {

	public static final String searchBtn = "searchBtn";
	public static final String searchName = "search_by_name";
	public static final String searchresource = "search_by_resource";
	public static final String searchTxtView = "searchTextView";

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
	public static final String SELECT_FIELD = "//android.widget.TextView[@text='SHORTNAME *']/ancestor::android.widget.LinearLayout[1]//android.widget.Button[@text='FIELDVALUE']";

	// Calender Elements
	public static final String HEADERDATE = "android:id/date_picker_header_date";
	public static final String DATE_SELECTED = "//android.view.View[contains(@text,'DATE')]";
	public static final String DONE_BTN = "doneBtn";

	public static final String Submissions_Form_Status = "//android.widget.Button[@text='RECEIVED']";
	public static final String Form_Save = "saveBtn";

	public static final String SAVED_TIMESTAMP = "//android.widget.TextView[contains(@text,'Saved on')]";

	public static final String Pin_Button = "btnPinVerify";
	public static final String Pin_Button_Save = "btnSave";
	public static final String Pin_Button_Complaint = "btnCompliant";
	public static final String Pin_Button_NonComplaint = "btnNonCompliant";

	public static final String Pin_User = "txtUsername";
	public static final String Pin_Pass = "txtPin";
	public static final String Pin_Comments = "txtComments";

	// Camera/Gallery

	public static final String Camera_Button = "cameraBtn";

	public static final String dgCameraBtn = "dialogcameraBtn";
	public static final String dgGalleryBtn = "dailoggalleryBtn";

	public static final String dgCameraBtnOk = "(//GLButton[@content-desc=\'NONE\'])[4]";
	public static final String gTextView = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.TextView[2]";
	public static final String CAMERA_SHUTTER = "//android.widget.ImageView[@content-desc='Shutter']";

	public static final String dgCameraOk = "//android.widget.ImageButton[@content-desc='Done']";
	public static final String dgCameraSubmit = "submitBtn";

	public static final String gViewImages = "//android.widget.TextView[@text='Camera']";
	public static final String gSelectImages = "android.widget.CheckBox";

	public static final String gSelectImagesBtn = "//android.widget.TextView[@text='Done']";// done_button
	public static final String gCloseImagesBtn = "okBtn";

	// Forms Photo Validation
	public static final String PHOTO_SECTION_TITLE = "photoSectionTitle";
	public static final String FORMS_PHOTO = "photoBtn";
	public static final String FORMS_PHOTO_BACK_BTN = "headBackBtn";
	public static final String FORMS_PHOTO_DLETE = "deleteBtn";
	public static final String OPENED_IMG = "imageview";

	public static final String TITLE_PHOTOS = "titleOnForm";

	public static final String vFormDate = "formDate";
	public static final String vFormStatus = "formstatusBtn";
	public static final String chkStatus = "RECEIVED";

	public static final String permissionAllowBtn = "permission_allow_button";

}
