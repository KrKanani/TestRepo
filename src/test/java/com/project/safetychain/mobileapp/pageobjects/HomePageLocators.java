package com.project.safetychain.mobileapp.pageobjects;

public class HomePageLocators {

    public static final String menuBtn = "MenuBtn";
    public static final String inboxBtn = "InboxBtn";
    public static final String dialogTitleTxt = "dialogTitle";
    public static final String InboxHeaderTxt = "InboxHeaderText";
    public static final String formName = "formName";
    public static final String Form_Name = "//android.widget.TextView[@text='FormNameLocator']";

    public static final String Search_Field = "searchTextView";
    public static final String NORESULTSFOUND = "headerLabel";
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
    
    //tan
    public static final String ALL_FORMS_ICON = "allformButton";
    public static final String ALL_FORMS_TEXT = "allformText";
    public static final String SAVED_ICON = "savebadgeBtn";
    public static final String SAVED_TEXT = "savedText";
    public static final String FAVOURITIES_ICON = "favButton";
    public static final String FAVOURITIES_TEXT = "favText";
    public static final String TASK_SUBMENU = "taskTitle";
    public static final String FORMS_LIST_AREA = "formlistView";
    

}
