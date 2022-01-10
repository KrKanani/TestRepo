package com.project.safetychain.api.models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.project.safetychain.api.models.support.Support;
import com.project.safetychain.api.models.support.Support.DPT_FIELD_TYPES;
import com.project.safetychain.api.models.support.Support.FormFieldParams;
import com.project.safetychain.api.models.support.Support.IDENTIFIER_TYPES;
import com.project.safetychain.api.models.support.Support.TaskParams;
import com.project.testbase.TestValidation;
import com.project.utilities.ApiUtils;
import com.project.utilities.ApiUtils.METHOD_TYPE;

import io.restassured.response.Response;

public class TaskFlow extends Auth {

	/** Web APIs */
	public String SaveWorkGroupUrl = baseURI + TaskUrls.SAVE_WORK_GROUP_URL;
	public String AddFormTaskUrl = baseURI + TaskUrls.ADD_FORM_TASK_URL;
	public String AddSchedulerTaskUrl = baseURI + TaskUrls.ADD_SCHEDULER_TASK_URL;
	public String GetScheduleIntervalUrl = baseURI + TaskUrls.GET_SCHEDULE_INTERVAL_URL;
	public String GetExpirationIntervalUrl = baseURI + TaskUrls.GET_EXPIRATION_INTERVAL_URL;
	public String RecallTaskUrl = baseURI + TaskUrls.RECALL_TASK;
	public String ReassignTaskUrl = baseURI + TaskUrls.REASSIGN_TASK;

	/** DPT APIs */
	public String DptSaveFormDesignUrl = baseURI + FormDesignerUrls.DPT_SAVE_FORM_DESIGN_URL;

	ApiUtils apiUtils = new ApiUtils();
	Gson gson = new GsonBuilder().create();
	Random rand = new Random();
	String taskId;

	// 0 references
	public String updateResFieldsJson(List<String> listOfEnabledFields, String instanceName, String number,
			String text) {
		String fieldDets = "", appendFieldDets = null;

		try {
			for (int i = 0; i < listOfEnabledFields.size(); i++) {

				String[] details = listOfEnabledFields.get(i).split("\\|");
				if (i != (listOfEnabledFields.size() - 1)) {
					if (details[2].equalsIgnoreCase("SingleSelect")) {
						appendFieldDets = "        {\r\n" + "            \"FieldId\": \"" + details[0] + "\",\r\n"
								+ "            \"Value\": {\r\n" + "                \"Name\": \"" + details[3]
										+ "\"\r\n" + "            },\r\n" + "            \"Type\": \"" + details[2] + "\",\r\n"
										+ "            \"FieldName\": \"" + details[1] + "\"\r\n" + "        },\r\n";
					} else if (details[2].equalsIgnoreCase("Textbox")) {
						if (details[1].equalsIgnoreCase("SCSName")) {
							appendFieldDets = "        {\r\n" + "            \"FieldId\": \"" + details[0] + "\",\r\n"
									+ "            \"Value\": \"" + instanceName + "\",\r\n"
									+ "            \"Type\": \"" + details[2] + "\",\r\n"
									+ "            \"FieldName\": \"" + details[1] + "\"\r\n" + "        },\r\n";
						} else {
							appendFieldDets = "        {\r\n" + "            \"FieldId\": \"" + details[0] + "\",\r\n"
									+ "            \"Value\": \"" + text + "\",\r\n" + "            \"Type\": \""
									+ details[2] + "\",\r\n" + "            \"FieldName\": \"" + details[1] + "\"\r\n"
									+ "        },\r\n";
						}
					} else if (details[2].equalsIgnoreCase("Numeric")) {
						appendFieldDets = "        {\r\n" + "            \"FieldId\": \"" + details[0] + "\",\r\n"
								+ "            \"Value\": \"" + number + "\",\r\n" + "            \"Type\": \""
								+ details[2] + "\",\r\n" + "            \"FieldName\": \"" + details[1] + "\"\r\n"
								+ "        },\r\n";
					}
				} else {
					if (details[2].equalsIgnoreCase("SingleSelect")) {
						appendFieldDets = "        {\r\n" + "            \"FieldId\": \"" + details[0] + "\",\r\n"
								+ "            \"Value\": {\r\n" + "                \"Name\": \"" + details[3]
										+ "\"\r\n" + "            },\r\n" + "            \"Type\": \"" + details[2] + "\",\r\n"
										+ "            \"FieldName\": \"" + details[1] + "\"\r\n" + "        }\r\n";
					} else if (details[2].equalsIgnoreCase("Textbox")) {
						if (details[1].equalsIgnoreCase("SCSName")) {
							appendFieldDets = "        {\r\n" + "            \"FieldId\": \"" + details[0] + "\",\r\n"
									+ "            \"Value\": \"" + instanceName + "\",\r\n"
									+ "            \"Type\": \"" + details[2] + "\",\r\n"
									+ "            \"FieldName\": \"" + details[1] + "\"\r\n" + "        }\r\n";
						} else {
							appendFieldDets = "        {\r\n" + "            \"FieldId\": \"" + details[0] + "\",\r\n"
									+ "            \"Value\": \"" + text + "\",\r\n" + "            \"Type\": \""
									+ details[2] + "\",\r\n" + "            \"FieldName\": \"" + details[1] + "\"\r\n"
									+ "        }\r\n";
						}
					} else if (details[2].equalsIgnoreCase("Numeric")) {
						appendFieldDets = "        {\r\n" + "            \"FieldId\": \"" + details[0] + "\",\r\n"
								+ "            \"Value\": \"" + number + "\",\r\n" + "            \"Type\": \""
								+ details[2] + "\",\r\n" + "            \"FieldName\": \"" + details[1] + "\"\r\n"
								+ "        }\r\n";
					}
				}

				fieldDets = fieldDets + appendFieldDets;
			}
			return fieldDets;
		} catch (Exception e) {
			logError("Failed to set resource IDs for Categories - " + e.getMessage());
			return fieldDets;
		}
	}

	// ******************************************** DPT
	// ********************************************

	String aa = "{\r\n" + "  \"Operation\": \"Insert\",\r\n" + "  \"PartialSuccess\": false,\r\n" +
			//			"  \"RequestNo\": \"24 Jul'2020\",\r\n" + 
			"  \"Data\":  [\r\n" + "         {\r\n" +
			//			"            \"FormId\": \"244a24bc-f5ed-44a1-8086-bc39cc7e2961\",\r\n" + 
			"            \"FormName\": \"00Check_1401_Update\",\r\n" + // Update
			//			"            \"Type\": 0,\r\n" + 
			"            \"Version\": \"1.0\",\r\n" + "            \"Resources\": [\r\n" + "                {\r\n"
			+ "                    \"Id\": \"9806621c-7e0e-4665-b7f5-890931aaafee\",\r\n" + // Update
			//			"                    \"Name\": \"Customers > 28JanResource > 28JanR1\"\r\n" + 
			"                }\r\n" + "            ],\r\n" + "            \"Fields\": [\r\n" + "                {\r\n"
			+ "                    \"Type\": 5,\r\n" + "                    \"DependencyRule\": null,\r\n"
			+ "                    \"ExpressionRule\": null,\r\n" + "                    \"FailsForm\": true,\r\n"
			+ "                    \"AllowCorrection\": false,\r\n"
			+ "                    \"PredefinedCorrections\": null,\r\n"
			+ "                    \"ShowIsResolved\": false,\r\n" + "                    \"Configuration\": null,\r\n"
			+ "                    \"IsCompliant\": null,\r\n" + "                    \"DefaultCompliance\": null,\r\n"
			+ "                    \"DataSourceId\": \"00000000-0000-0000-0000-000000000000\",\r\n"
			+ "                    \"DataSourceName\": null,\r\n" + "                    \"DataSourceType\": 0,\r\n"
			+ "                    \"RepeatField\": false,\r\n" + "                    \"ElementType\": 0,\r\n"
			+ "                    \"IsRequired\": true,\r\n" + "                    \"AllowComments\": false,\r\n"
			+ "                    \"Comment\": null,\r\n" + "                    \"AllowAttachments\": false,\r\n"
			+ "                    \"Attachments\": null,\r\n" + "                    \"ShowOnCOA\": true,\r\n"
			+ "                    \"ShowHint\": false,\r\n" + "                    \"Hint\": null,\r\n"
			+ "                    \"ShowPointsPossible\": null,\r\n"
			+ "                    \"ShowPointsEarned\": null,\r\n"
			+ "                    \"QuestionWeight\": null,\r\n" + "                    \"Order\": 0,\r\n"
			+ "                    \"Repeat\": null,\r\n" + "                    \"RepeatIds\": null,\r\n"
			+ "                    \"GroupID\": \"00000000-0000-0000-0000-000000000000\",\r\n"
			+ "                    \"ShowField\": true,\r\n"
			+ "                    \"FieldBankId\": \"00000000-0000-0000-0000-000000000000\",\r\n"
			+ "                    \"ParentId\": null,\r\n" + "                    \"Value\": null,\r\n"
			+ "                    \"Default\": null,\r\n"
			+ "                    \"Id\": \"85ded524-8105-437e-bffe-18ae1aa91250\",\r\n"
			+ "                    \"Name\": \"FT1\",\r\n" + "                    \"ShortName\": \"FT1\"\r\n"
			+ "                },\r\n" + "                {\r\n" + "                    \"Type\": 6,\r\n"
			+ "                    \"DependencyRule\": null,\r\n" + "                    \"Configuration\": null,\r\n"
			+ "                    \"IsCompliant\": null,\r\n" + "                    \"ElementType\": 0,\r\n"
			+ "                    \"IsRequired\": true,\r\n" + "                    \"AllowComments\": false,\r\n"
			+ "                    \"Comment\": null,\r\n" + "                    \"AllowAttachments\": false,\r\n"
			+ "                    \"Attachments\": null,\r\n" + "                    \"ShowOnCOA\": true,\r\n"
			+ "                    \"ShowHint\": false,\r\n" + "                    \"Hint\": null,\r\n"
			+ "                    \"ShowPointsPossible\": null,\r\n"
			+ "                    \"ShowPointsEarned\": null,\r\n"
			+ "                    \"QuestionWeight\": null,\r\n" + "                    \"Order\": 0,\r\n"
			+ "                    \"Repeat\": null,\r\n" + "                    \"RepeatIds\": null,\r\n"
			+ "                    \"GroupID\": \"00000000-0000-0000-0000-000000000000\",\r\n"
			+ "                    \"ShowField\": true,\r\n"
			+ "                    \"FieldBankId\": \"00000000-0000-0000-0000-000000000000\",\r\n"
			+ "                    \"ParentId\": null,\r\n" + "                    \"RepeatField\": false,\r\n"
			+ "                    \"Value\": null,\r\n" + "                    \"Default\": null,\r\n"
			+ "                    \"Id\": \"4220aa5f-0389-4c9d-83ba-b1d2af391d15\",\r\n"
			+ "                    \"Name\": \"PT1\",\r\n" + "                    \"ShortName\": \"PT1\"\r\n"
			+ "                },\r\n" + "                {\r\n" + "                    \"Type\": 7,\r\n"
			+ "                    \"ControlType\": 0,\r\n" + "                    \"Options\": [\r\n"
			+ "                        {\r\n" + "                            \"Value\": null,\r\n"
			+ "                            \"Code\": null,\r\n"
			+ "                            \"Id\": \"b3c8721c-7ec8-4387-a0eb-f09024af76c5\",\r\n"
			+ "                            \"Name\": \"10\"\r\n" + "                        },\r\n"
			+ "                        {\r\n" + "                            \"Value\": null,\r\n"
			+ "                            \"Code\": null,\r\n"
			+ "                            \"Id\": \"9bd6b905-f149-46fe-8b3f-e1a33b19f122\",\r\n"
			+ "                            \"Name\": \"20\"\r\n" + "                        }\r\n"
			+ "                    ],\r\n" + "                    \"DependencyRule\": null,\r\n"
			+ "                    \"ExpressionRule\": null,\r\n" + "                    \"FailsForm\": true,\r\n"
			+ "                    \"AllowCorrection\": false,\r\n"
			+ "                    \"PredefinedCorrections\": null,\r\n"
			+ "                    \"ShowIsResolved\": false,\r\n" + "                    \"Configuration\": null,\r\n"
			+ "                    \"IsCompliant\": null,\r\n" + "                    \"DefaultCompliance\": null,\r\n"
			+ "                    \"IncludeInExport\": false,\r\n"
			+ "                    \"DataSourceId\": \"00000000-0000-0000-0000-000000000000\",\r\n"
			+ "                    \"DataSourceName\": null,\r\n" + "                    \"DataSourceType\": 0,\r\n"
			+ "                    \"RepeatField\": false,\r\n" + "                    \"ElementType\": 0,\r\n"
			+ "                    \"IsRequired\": true,\r\n" + "                    \"AllowComments\": false,\r\n"
			+ "                    \"Comment\": null,\r\n" + "                    \"AllowAttachments\": false,\r\n"
			+ "                    \"Attachments\": null,\r\n" + "                    \"ShowOnCOA\": true,\r\n"
			+ "                    \"ShowHint\": false,\r\n" + "                    \"Hint\": null,\r\n"
			+ "                    \"ShowPointsPossible\": false,\r\n"
			+ "                    \"ShowPointsEarned\": false,\r\n" + "                    \"QuestionWeight\": 0,\r\n"
			+ "                    \"Order\": 0,\r\n" + "                    \"Repeat\": null,\r\n"
			+ "                    \"RepeatIds\": null,\r\n"
			+ "                    \"GroupID\": \"00000000-0000-0000-0000-000000000000\",\r\n"
			+ "                    \"ShowField\": true,\r\n"
			+ "                    \"FieldBankId\": \"00000000-0000-0000-0000-000000000000\",\r\n"
			+ "                    \"ParentId\": null,\r\n" + "                    \"Value\": null,\r\n"
			+ "                    \"Default\": null,\r\n"
			+ "                    \"Id\": \"5b28481b-9087-45b2-8afc-6ea471866518\",\r\n"
			+ "                    \"Name\": \"SO1\",\r\n" + "                    \"ShortName\": \"SO1\"\r\n"
			+ "                },\r\n" + "                {\r\n" + "                    \"Type\": 8,\r\n"
			+ "                    \"ControlType\": 0,\r\n" + "                    \"Options\": [\r\n"
			+ "                        {\r\n" + "                            \"Value\": null,\r\n"
			+ "                            \"Code\": null,\r\n"
			+ "                            \"Id\": \"6462d5ad-375d-4d28-a8af-3f5ebddf1d14\",\r\n"
			+ "                            \"Name\": \"1\"\r\n" + "                        },\r\n"
			+ "                        {\r\n" + "                            \"Value\": null,\r\n"
			+ "                            \"Code\": null,\r\n"
			+ "                            \"Id\": \"d3f4cb8b-73f0-435b-be94-10b92a5fdd4c\",\r\n"
			+ "                            \"Name\": \"2\"\r\n" + "                        },\r\n"
			+ "                        {\r\n" + "                            \"Value\": null,\r\n"
			+ "                            \"Code\": null,\r\n"
			+ "                            \"Id\": \"9d690e54-cee4-4762-bd7b-e1ebfa3cace3\",\r\n"
			+ "                            \"Name\": \"3\"\r\n" + "                        }\r\n"
			+ "                    ],\r\n" + "                    \"DependencyRule\": null,\r\n"
			+ "                    \"ExpressionRule\": null,\r\n" + "                    \"FailsForm\": true,\r\n"
			+ "                    \"AllowCorrection\": false,\r\n"
			+ "                    \"PredefinedCorrections\": null,\r\n"
			+ "                    \"ShowIsResolved\": false,\r\n" + "                    \"Configuration\": null,\r\n"
			+ "                    \"IsCompliant\": null,\r\n" + "                    \"DefaultCompliance\": null,\r\n"
			+ "                    \"RepeatField\": false,\r\n" + "                    \"ElementType\": 0,\r\n"
			+ "                    \"IsRequired\": true,\r\n" + "                    \"AllowComments\": false,\r\n"
			+ "                    \"Comment\": null,\r\n" + "                    \"AllowAttachments\": false,\r\n"
			+ "                    \"Attachments\": null,\r\n" + "                    \"ShowOnCOA\": true,\r\n"
			+ "                    \"ShowHint\": false,\r\n" + "                    \"Hint\": null,\r\n"
			+ "                    \"ShowPointsPossible\": false,\r\n"
			+ "                    \"ShowPointsEarned\": false,\r\n" + "                    \"QuestionWeight\": 0,\r\n"
			+ "                    \"Order\": 0,\r\n" + "                    \"Repeat\": null,\r\n"
			+ "                    \"RepeatIds\": null,\r\n"
			+ "                    \"GroupID\": \"00000000-0000-0000-0000-000000000000\",\r\n"
			+ "                    \"ShowField\": true,\r\n"
			+ "                    \"FieldBankId\": \"00000000-0000-0000-0000-000000000000\",\r\n"
			+ "                    \"ParentId\": null,\r\n" + "                    \"Value\": null,\r\n"
			+ "                    \"Default\": null,\r\n"
			+ "                    \"Id\": \"51a207f8-db44-445c-bb7b-e852dc0c366a\",\r\n"
			+ "                    \"Name\": \"SM1\",\r\n" + "                    \"ShortName\": \"SM1\"\r\n"
			+ "                },\r\n" + "                {\r\n" + "                    \"Type\": 9,\r\n"
			+ "                    \"ShowMinMax\": true,\r\n" + "                    \"ShowTarget\": false,\r\n"
			+ "                    \"IsIntegrated\": false,\r\n" + "                    \"DependencyRule\": null,\r\n"
			+ "                    \"ExpressionRule\": null,\r\n" + "                    \"FailsForm\": true,\r\n"
			+ "                    \"AllowCorrection\": false,\r\n"
			+ "                    \"PredefinedCorrections\": null,\r\n"
			+ "                    \"ShowIsResolved\": false,\r\n" + "                    \"Configuration\": null,\r\n"
			+ "                    \"IsCompliant\": null,\r\n" + "                    \"DefaultCompliance\": null,\r\n"
			+ "                    \"StepperTypeId\": null,\r\n" + "                    \"StepperTypeName\": null,\r\n"
			+ "                    \"DataSourceId\": \"00000000-0000-0000-0000-000000000000\",\r\n"
			+ "                    \"DataSourceName\": null,\r\n" + "                    \"DataSourceType\": 0,\r\n"
			+ "                    \"RepeatField\": false,\r\n" + "                    \"ElementType\": 0,\r\n"
			+ "                    \"IsRequired\": true,\r\n" + "                    \"AllowComments\": false,\r\n"
			+ "                    \"Comment\": null,\r\n" + "                    \"AllowAttachments\": false,\r\n"
			+ "                    \"Attachments\": null,\r\n" + "                    \"ShowOnCOA\": true,\r\n"
			+ "                    \"ShowHint\": false,\r\n" + "                    \"Hint\": null,\r\n"
			+ "                    \"ShowPointsPossible\": null,\r\n"
			+ "                    \"ShowPointsEarned\": null,\r\n"
			+ "                    \"QuestionWeight\": null,\r\n" + "                    \"Order\": 0,\r\n"
			+ "                    \"Repeat\": null,\r\n" + "                    \"RepeatIds\": null,\r\n"
			+ "                    \"GroupID\": \"00000000-0000-0000-0000-000000000000\",\r\n"
			+ "                    \"ShowField\": true,\r\n"
			+ "                    \"FieldBankId\": \"00000000-0000-0000-0000-000000000000\",\r\n"
			+ "                    \"ParentId\": null,\r\n" + "                    \"Value\": null,\r\n"
			+ "                    \"Default\": null,\r\n"
			+ "                    \"Id\": \"3e464a2b-30cc-4aac-86d0-d2172e9a3284\",\r\n"
			+ "                    \"Name\": \"N1\",\r\n" + "                    \"ShortName\": \"N1\"\r\n"
			+ "                },\r\n" + "                {\r\n" + "                    \"Type\": 10,\r\n"
			+ "                    \"DataSourceId\": \"00000000-0000-0000-0000-000000000000\",\r\n"
			+ "                    \"DataSourceName\": null,\r\n" + "                    \"DataSourceType\": 0,\r\n"
			+ "                    \"DependencyRule\": null,\r\n" + "                    \"ExpressionRule\": null,\r\n"
			+ "                    \"FailsForm\": true,\r\n" + "                    \"AllowCorrection\": false,\r\n"
			+ "                    \"PredefinedCorrections\": null,\r\n"
			+ "                    \"ShowIsResolved\": false,\r\n" + "                    \"Configuration\": null,\r\n"
			+ "                    \"IsCompliant\": null,\r\n" + "                    \"ElementType\": 0,\r\n"
			+ "                    \"IsRequired\": true,\r\n" + "                    \"AllowComments\": false,\r\n"
			+ "                    \"Comment\": null,\r\n" + "                    \"AllowAttachments\": false,\r\n"
			+ "                    \"Attachments\": null,\r\n" + "                    \"ShowOnCOA\": true,\r\n"
			+ "                    \"ShowHint\": false,\r\n" + "                    \"Hint\": null,\r\n"
			+ "                    \"ShowPointsPossible\": null,\r\n"
			+ "                    \"ShowPointsEarned\": null,\r\n"
			+ "                    \"QuestionWeight\": null,\r\n" + "                    \"Order\": 0,\r\n"
			+ "                    \"Repeat\": null,\r\n" + "                    \"RepeatIds\": null,\r\n"
			+ "                    \"GroupID\": \"00000000-0000-0000-0000-000000000000\",\r\n"
			+ "                    \"ShowField\": true,\r\n"
			+ "                    \"FieldBankId\": \"00000000-0000-0000-0000-000000000000\",\r\n"
			+ "                    \"ParentId\": null,\r\n" + "                    \"RepeatField\": false,\r\n"
			+ "                    \"Value\": null,\r\n" + "                    \"Default\": null,\r\n"
			+ "                    \"Id\": \"78c788f0-3376-4646-ba51-325d167e6a94\",\r\n"
			+ "                    \"Name\": \"D1\",\r\n" + "                    \"ShortName\": \"D1\"\r\n"
			+ "                },\r\n" + "                {\r\n" + "                    \"Type\": 11,\r\n"
			+ "                    \"DependencyRule\": null,\r\n" + "                    \"ExpressionRule\": null,\r\n"
			+ "                    \"FailsForm\": true,\r\n" + "                    \"AllowCorrection\": false,\r\n"
			+ "                    \"PredefinedCorrections\": null,\r\n"
			+ "                    \"ShowIsResolved\": false,\r\n" + "                    \"Configuration\": null,\r\n"
			+ "                    \"IsCompliant\": null,\r\n"
			+ "                    \"DataSourceId\": \"00000000-0000-0000-0000-000000000000\",\r\n"
			+ "                    \"DataSourceName\": null,\r\n" + "                    \"DataSourceType\": 0,\r\n"
			+ "                    \"RepeatField\": false,\r\n" + "                    \"ElementType\": 0,\r\n"
			+ "                    \"IsRequired\": true,\r\n" + "                    \"AllowComments\": false,\r\n"
			+ "                    \"Comment\": null,\r\n" + "                    \"AllowAttachments\": false,\r\n"
			+ "                    \"Attachments\": null,\r\n" + "                    \"ShowOnCOA\": true,\r\n"
			+ "                    \"ShowHint\": false,\r\n" + "                    \"Hint\": null,\r\n"
			+ "                    \"ShowPointsPossible\": null,\r\n"
			+ "                    \"ShowPointsEarned\": null,\r\n"
			+ "                    \"QuestionWeight\": null,\r\n" + "                    \"Order\": 0,\r\n"
			+ "                    \"Repeat\": null,\r\n" + "                    \"RepeatIds\": null,\r\n"
			+ "                    \"GroupID\": \"00000000-0000-0000-0000-000000000000\",\r\n"
			+ "                    \"ShowField\": true,\r\n"
			+ "                    \"FieldBankId\": \"00000000-0000-0000-0000-000000000000\",\r\n"
			+ "                    \"ParentId\": null,\r\n" + "                    \"Value\": null,\r\n"
			+ "                    \"Default\": null,\r\n"
			+ "                    \"Id\": \"af7d47e9-3b9c-429d-82a3-18ccc6622367\",\r\n"
			+ "                    \"Name\": \"T1\",\r\n" + "                    \"ShortName\": \"T1\"\r\n"
			+ "                },\r\n" + "                {\r\n" + "                    \"Type\": 12,\r\n"
			+ "                    \"DependencyRule\": null,\r\n" + "                    \"ExpressionRule\": null,\r\n"
			+ "                    \"FailsForm\": true,\r\n" + "                    \"AllowCorrection\": false,\r\n"
			+ "                    \"PredefinedCorrections\": null,\r\n"
			+ "                    \"ShowIsResolved\": false,\r\n" + "                    \"Configuration\": null,\r\n"
			+ "                    \"IsCompliant\": null,\r\n"
			+ "                    \"DataSourceId\": \"00000000-0000-0000-0000-000000000000\",\r\n"
			+ "                    \"DataSourceName\": null,\r\n" + "                    \"DataSourceType\": 0,\r\n"
			+ "                    \"ElementType\": 0,\r\n" + "                    \"IsRequired\": true,\r\n"
			+ "                    \"AllowComments\": false,\r\n" + "                    \"Comment\": null,\r\n"
			+ "                    \"AllowAttachments\": false,\r\n" + "                    \"Attachments\": null,\r\n"
			+ "                    \"ShowOnCOA\": true,\r\n" + "                    \"ShowHint\": false,\r\n"
			+ "                    \"Hint\": null,\r\n" + "                    \"ShowPointsPossible\": null,\r\n"
			+ "                    \"ShowPointsEarned\": null,\r\n"
			+ "                    \"QuestionWeight\": null,\r\n" + "                    \"Order\": 0,\r\n"
			+ "                    \"Repeat\": null,\r\n" + "                    \"RepeatIds\": null,\r\n"
			+ "                    \"GroupID\": \"00000000-0000-0000-0000-000000000000\",\r\n"
			+ "                    \"ShowField\": true,\r\n"
			+ "                    \"FieldBankId\": \"00000000-0000-0000-0000-000000000000\",\r\n"
			+ "                    \"ParentId\": null,\r\n" + "                    \"RepeatField\": false,\r\n"
			+ "                    \"Value\": null,\r\n" + "                    \"Default\": null,\r\n"
			+ "                    \"Id\": \"e198e41e-a9d9-410c-b278-0cc3c0a7e637\",\r\n"
			+ "                    \"Name\": \"DT1\",\r\n" + "                    \"ShortName\": \"DT1\"\r\n"
			+ "                },\r\n" + "                {\r\n" + "                    \"Type\": 15,\r\n"
			+ "                    \"InputType\": {\r\n" + "                        \"Id\": \"FreeText\",\r\n"
			+ "                        \"Name\": \"Free Text\"\r\n" + "                    },\r\n"
			+ "                    \"InputMask\": \"9009\",\r\n" + "                    \"IsFilterable\": true,\r\n"
			+ "                    \"DependencyRule\": null,\r\n"
			+ "                    \"IdentifierOption\": null,\r\n"
			+ "                    \"DataSourceId\": \"00000000-0000-0000-0000-000000000000\",\r\n"
			+ "                    \"DataSourceName\": null,\r\n" + "                    \"DataSourceType\": 0,\r\n"
			+ "                    \"RepeatField\": false,\r\n" + "                    \"Configuration\": null,\r\n"
			+ "                    \"IsCompliant\": null,\r\n" + "                    \"ElementType\": 0,\r\n"
			+ "                    \"IsRequired\": true,\r\n" + "                    \"AllowComments\": false,\r\n"
			+ "                    \"Comment\": null,\r\n" + "                    \"AllowAttachments\": false,\r\n"
			+ "                    \"Attachments\": null,\r\n" + "                    \"ShowOnCOA\": true,\r\n"
			+ "                    \"ShowHint\": false,\r\n" + "                    \"Hint\": null,\r\n"
			+ "                    \"ShowPointsPossible\": null,\r\n"
			+ "                    \"ShowPointsEarned\": null,\r\n"
			+ "                    \"QuestionWeight\": null,\r\n" + "                    \"Order\": 0,\r\n"
			+ "                    \"Repeat\": null,\r\n" + "                    \"RepeatIds\": null,\r\n"
			+ "                    \"GroupID\": \"00000000-0000-0000-0000-000000000000\",\r\n"
			+ "                    \"ShowField\": true,\r\n"
			+ "                    \"FieldBankId\": \"00000000-0000-0000-0000-000000000000\",\r\n"
			+ "                    \"ParentId\": null,\r\n" + "                    \"Value\": null,\r\n"
			+ "                    \"Default\": null,\r\n"
			+ "                    \"Id\": \"853b0832-b8a0-4330-a178-85d6f4b323b5\",\r\n"
			+ "                    \"Name\": \"ID1\",\r\n" + "                    \"ShortName\": \"ID1\"\r\n"
			+ "                },\r\n" + "                {\r\n"
			+ "                    \"Id\": \"082c0063-5acf-4682-880a-297a4f447902\",\r\n"
			+ "                    \"Name\": \"FG1\",\r\n" + "                    \"ShortName\": \"FG1\",\r\n"
			+ "                    \"ElementType\": 1,\r\n" + "                    \"Order\": 0,\r\n"
			+ "                    \"Fields\": [\r\n" + "                        {\r\n"
			+ "                            \"Type\": 9,\r\n" + "                            \"ShowMinMax\": true,\r\n"
			+ "                            \"ShowTarget\": false,\r\n"
			+ "                            \"IsIntegrated\": false,\r\n"
			+ "                            \"DependencyRule\": null,\r\n"
			+ "                            \"ExpressionRule\": null,\r\n"
			+ "                            \"FailsForm\": true,\r\n"
			+ "                            \"AllowCorrection\": false,\r\n"
			+ "                            \"PredefinedCorrections\": null,\r\n"
			+ "                            \"ShowIsResolved\": false,\r\n"
			+ "                            \"Configuration\": null,\r\n"
			+ "                            \"IsCompliant\": null,\r\n"
			+ "                            \"DefaultCompliance\": null,\r\n"
			+ "                            \"StepperTypeId\": null,\r\n"
			+ "                            \"StepperTypeName\": null,\r\n"
			+ "                            \"DataSourceId\": \"00000000-0000-0000-0000-000000000000\",\r\n"
			+ "                            \"DataSourceName\": null,\r\n"
			+ "                            \"DataSourceType\": 0,\r\n"
			+ "                            \"RepeatField\": false,\r\n"
			+ "                            \"ElementType\": 0,\r\n"
			+ "                            \"IsRequired\": true,\r\n"
			+ "                            \"AllowComments\": false,\r\n"
			+ "                            \"Comment\": null,\r\n"
			+ "                            \"AllowAttachments\": false,\r\n"
			+ "                            \"Attachments\": null,\r\n"
			+ "                            \"ShowOnCOA\": true,\r\n"
			+ "                            \"ShowHint\": false,\r\n" + "                            \"Hint\": null,\r\n"
			+ "                            \"ShowPointsPossible\": null,\r\n"
			+ "                            \"ShowPointsEarned\": null,\r\n"
			+ "                            \"QuestionWeight\": null,\r\n"
			+ "                            \"Order\": 0,\r\n" + "                            \"Repeat\": null,\r\n"
			+ "                            \"RepeatIds\": null,\r\n"
			+ "                            \"GroupID\": \"00000000-0000-0000-0000-000000000000\",\r\n"
			+ "                            \"ShowField\": true,\r\n"
			+ "                            \"FieldBankId\": \"00000000-0000-0000-0000-000000000000\",\r\n"
			+ "                            \"ParentId\": null,\r\n" + "                            \"Value\": null,\r\n"
			+ "                            \"Default\": null,\r\n"
			+ "                            \"Id\": \"3a106949-263f-4a07-a222-3bfdf2ea813e\",\r\n"
			+ "                            \"Name\": \"N2\",\r\n"
			+ "                            \"ShortName\": \"N2\"\r\n" + "                        }\r\n"
			+ "                    ],\r\n" + "                    \"ShowGroupName\": true,\r\n"
			+ "                    \"ShowHint\": false,\r\n" + "                    \"Hint\": null,\r\n"
			+ "                    \"Repeat\": null,\r\n" + "                    \"RepeatIds\": null,\r\n"
			+ "                    \"GroupID\": \"00000000-0000-0000-0000-000000000000\",\r\n"
			+ "                    \"ShowField\": true,\r\n" + "                    \"DependencyRule\": null,\r\n"
			+ "                    \"ParentId\": null\r\n" + "                },\r\n" + "                {\r\n"
			+ "                    \"Type\": 3,\r\n" + "                    \"ShowMinMax\": true,\r\n"
			+ "                    \"ShowTarget\": false,\r\n" + "                    \"SelectedFunction\": {\r\n"
			+ "                        \"Id\": \"cc5b5dde-0d62-43b5-9632-dc95bee89af0\",\r\n"
			+ "                        \"Name\": \"Sum Range\"\r\n" + "                    },\r\n"
			+ "                    \"SelectedSource\": {\r\n"
			+ "                        \"Id\": \"00000000-0000-0000-0000-000000000000\",\r\n"
			+ "                        \"Name\": null\r\n" + "                    },\r\n"
			+ "                    \"SelectedFunctionParameters\": [\r\n" + "                        {\r\n"
			+ "                            \"Key\": \"Min\",\r\n" + "                            \"Value\": 1\r\n"
			+ "                        },\r\n" + "                        {\r\n"
			+ "                            \"Key\": \"Max\",\r\n" + "                            \"Value\": 10000\r\n"
			+ "                        }\r\n" + "                    ],\r\n"
			+ "                    \"SelectedFunctionDetails\": {\r\n" + "                        \"Type\": 1,\r\n"
			+ "                        \"CastType\": \"Number\"\r\n" + "                    },\r\n"
			+ "                    \"SelectedSourceCollection\": [\r\n" + "                        {\r\n"
			+ "                            \"Id\": \"3e464a2b-30cc-4aac-86d0-d2172e9a3284\",\r\n"
			+ "                            \"Name\": \"N1\",\r\n"
			+ "                            \"ShortName\": \"N1\"\r\n" + "                        },\r\n"
			+ "                        {\r\n"
			+ "                            \"Id\": \"3a106949-263f-4a07-a222-3bfdf2ea813e\",\r\n"
			+ "                            \"Name\": \"N2\",\r\n"
			+ "                            \"ShortName\": \"N2\"\r\n" + "                        }\r\n"
			+ "                    ],\r\n" + "                    \"DependencyRule\": null,\r\n"
			+ "                    \"FailsForm\": true,\r\n" + "                    \"AllowCorrection\": false,\r\n"
			+ "                    \"PredefinedCorrections\": null,\r\n"
			+ "                    \"ShowIsResolved\": false,\r\n" + "                    \"Configuration\": null,\r\n"
			+ "                    \"IsCompliant\": null,\r\n" + "                    \"DefaultCompliance\": null,\r\n"
			+ "                    \"ElementType\": 0,\r\n" + "                    \"IsRequired\": false,\r\n"
			+ "                    \"AllowComments\": false,\r\n" + "                    \"Comment\": null,\r\n"
			+ "                    \"AllowAttachments\": false,\r\n" + "                    \"Attachments\": null,\r\n"
			+ "                    \"ShowOnCOA\": true,\r\n" + "                    \"ShowHint\": false,\r\n"
			+ "                    \"Hint\": null,\r\n" + "                    \"ShowPointsPossible\": null,\r\n"
			+ "                    \"ShowPointsEarned\": null,\r\n"
			+ "                    \"QuestionWeight\": null,\r\n" + "                    \"Order\": 0,\r\n"
			+ "                    \"Repeat\": null,\r\n" + "                    \"RepeatIds\": null,\r\n"
			+ "                    \"GroupID\": \"00000000-0000-0000-0000-000000000000\",\r\n"
			+ "                    \"ShowField\": true,\r\n"
			+ "                    \"FieldBankId\": \"00000000-0000-0000-0000-000000000000\",\r\n"
			+ "                    \"ParentId\": null,\r\n" + "                    \"RepeatField\": false,\r\n"
			+ "                    \"Value\": null,\r\n" + "                    \"Default\": null,\r\n"
			+ "                    \"Id\": \"09b65277-857c-44a5-83d7-1b2c2e965f5a\",\r\n"
			+ "                    \"Name\": \"AGG1\",\r\n" + "                    \"ShortName\": \"AGG1\"\r\n"
			+ "                }\r\n" + "            ],\r\n"
			+ "            \"ReleaseDate\": \"2021-01-14T05:34:10.6870000Z\"\r\n" + "        }\r\n" + "      \r\n"
			+ "    ]\r\n" + "}";

	public String updateFormElements(FormFieldParams ffp, boolean lastField) {
		String addField = null;
		String identifierDets = "";
		String selectOptions = "";
		String predefinedOptions = "";

		try {

			// For Identifier fields
			if (ffp.DPTFieldType.equalsIgnoreCase(DPT_FIELD_TYPES.IDENTIFIER)) {

				if (ffp.identifierId.equalsIgnoreCase(IDENTIFIER_TYPES.FREE_TEXT)) {
					identifierDets = "					 \"InputType\": {\r\n" + "                        \"Id\": \""
							+ ffp.identifierId + "\",\r\n" +
							//									"                        \"Name\": \"Free Text\"\r\n" + 
							"                    },\r\n" + "                    \"InputMask\": \"" + ffp.InputMask
							+ "\",\r\n" + "                    \"IsFilterable\": " + ffp.IsFilterable + ",\r\n"
							+ "                    \"IdentifierOption\": " + ffp.IdentifierOption + ",";
				} else if (ffp.identifierId.equalsIgnoreCase(IDENTIFIER_TYPES.SELECT_ONE)) {
					// to do
				}
			}
			// For Select One/Multiple
			else if (ffp.DPTFieldType.equalsIgnoreCase(DPT_FIELD_TYPES.SELECT_ONE)
					|| ffp.DPTFieldType.equalsIgnoreCase(DPT_FIELD_TYPES.SELECT_MULTIPLE)) {
				selectOptions = "					 \"ControlType\": " + ffp.ControlType + ",\r\n"
						+ "                    \"Options\": [\r\n" + Support.updateNameList(ffp.SelectOptions)
						+ "                    ],";
			}
			if (ffp.AllowCorrection.equalsIgnoreCase("true") && ffp.PredefinedCorrections != null) {
				predefinedOptions = "[\r\n" + Support.updateNameList(ffp.PredefinedCorrections)
				+ "                    ]";
			} else {
				predefinedOptions = "null";
			}

			addField = "                {\r\n" + "                    \"Type\": " + ffp.DPTFieldType + ",\r\n" + // Update
					identifierDets + selectOptions + "                    \"DependencyRule\": " + ffp.DependencyRule
					+ ",\r\n" + "                    \"ExpressionRule\": " + ffp.ExpressionRule + ",\r\n"
					+ "                    \"FailsForm\": " + ffp.FailsForm + ",\r\n"
					+ "                    \"AllowCorrection\": " + ffp.AllowCorrection + ",\r\n"
					+ "                    \"PredefinedCorrections\": " + predefinedOptions + ",\r\n"
					+ "                    \"ShowIsResolved\": " + ffp.ShowIsResolved + ",\r\n"
					+ "                    \"Configuration\": null,\r\n"
					+ "                    \"IsCompliant\": null,\r\n"
					+ "                    \"DefaultCompliance\": null,\r\n"
					+ "                    \"DataSourceId\": \"00000000-0000-0000-0000-000000000000\",\r\n"
					+ "                    \"DataSourceName\": null,\r\n"
					+ "                    \"DataSourceType\": 0,\r\n" + "                    \"RepeatField\": "
					+ ffp.RepeatField + ",\r\n" + "                    \"ElementType\": " + ffp.DPTElementType + ",\r\n"
					+ "                    \"IsRequired\": " + ffp.IsRequired + ",\r\n"
					+ "                    \"AllowComments\": " + ffp.AllowComments + ",\r\n"
					+ "                    \"Comment\": null,\r\n" + "                    \"AllowAttachments\": "
					+ ffp.AllowAttachments + ",\r\n" + "                    \"Attachments\": null,\r\n"
					+ "                    \"ShowOnCOA\": " + ffp.ShowOnCOA + ",\r\n"
					+ "                    \"ShowHint\": " + ffp.ShowHint + ",\r\n"
					+ "                    \"Hint\": null,\r\n"
					+ "                    \"ShowPointsPossible\": null,\r\n"
					+ "                    \"ShowPointsEarned\": null,\r\n"
					+ "                    \"QuestionWeight\": null,\r\n" + "                    \"Order\": 0,\r\n"
					+ "                    \"Repeat\": null,\r\n" + "                    \"RepeatIds\": null,\r\n"
					+ "                    \"GroupID\": \"00000000-0000-0000-0000-000000000000\",\r\n"
					+ "                    \"ShowField\": true,\r\n"
					+ "                    \"FieldBankId\": \"00000000-0000-0000-0000-000000000000\",\r\n"
					+ "                    \"ParentId\": null,\r\n" + "                    \"Value\": null,\r\n"
					+ "                    \"Default\": null,\r\n" + "                    \"Name\": \"" + ffp.Name
					+ "\",\r\n" + "                    \"ShortName\": \"" + ffp.Name.replaceAll(" ", "")
					+ rand.nextInt(50000) + "\"\r\n";

			if (!lastField) {

				addField += "                },\r\n";
			} else {
				addField += "                }\r\n";
			}

			return addField;
		} catch (Exception e) {
			logError("Failed to set form element for - " + ffp.Name + e.getMessage());
			return addField;
		}
	}

	public String appendFormElements(List<FormFieldParams> listOfElements) {
		String addField = "";

		try {
			for (int i = 0; i < listOfElements.size(); i++) {
				if (i != (listOfElements.size() - 1)) {
					addField += updateFormElements(listOfElements.get(i), false);
				} else {
					addField += updateFormElements(listOfElements.get(i), true);
				}
			}
			return addField;
		} catch (Exception e) {
			logError("Failed to set form element for - " + e.getMessage());
			return addField;
		}
	}

	public boolean createTask(TaskParams fp, String workGroupId) {
		try {

			apiUtils = new ApiUtils();
			Gson gson = new GsonBuilder().create();
			Map<String, String> headers = new HashMap<String, String>();
			headers.put("Content-Type", "application/json");
			headers.put("Accept", "application/json, text/plain, /");
			headers.put("Authorization", "Bearer " + accessToken);
			// 4. Create Task
			String createTaskJson = "{\r\n" + "    \"Type\": \"Form\",\r\n" + "    \"Status\": \"Unread\",\r\n"
					+ "    \"TimelineStatus\": \"OnTrack\",\r\n" + "    \"AssignmentType\": \"WorkGroup\",\r\n"
					+ "    \"FormInfo\": {\r\n" + "        \"Id\": \"" + fp.FormId + "\",\r\n" + "        \"Name\": \""
					+ fp.FormName + "\",\r\n" + "        \"Version\": {\r\n" + "            \"major\": 1\r\n"
					+ "        },\r\n" + "        \"Type\": \"Check\"\r\n" + "    },\r\n" + "    \"Resource\": {\r\n"
					+ "        \"Id\": \"" + fp.ResourceInstanceId + "\",\r\n" + "        \"Name\": \""
					+ fp.ResourceInstanceNm + "\",\r\n" + "        \"DisplayName\": \"" + fp.ResourceInstanceNm
					+ "\"\r\n" + "    },\r\n" + "    \"Version\": \"1.0\",\r\n" + "    \"Name\": \"" + fp.TaskName
					+ "\",\r\n" + "    \"DueBy\": {\r\n" + "        \"Date\": \"" + fp.DueBy + "\",\r\n"
					+ "        \"TimeZone\": null\r\n" + "    },\r\n" + "    \"Note\": null,\r\n"
					+ "    \"AssignmentTo\": {\r\n" + "        \"TaskCount\": 0,\r\n" + "        \"Id\": \""
					+ workGroupId + "\",\r\n" + "        \"Name\": \"" + fp.WorkGroupName + "\"\r\n" + "    },\r\n"
					+ "    \"LocationDetails\": {\r\n" + "        \"Id\": \"" + fp.LocationInstanceId + "\",\r\n"
					+ "        \"Name\": \"" + fp.LocationInstanceNm + "\"\r\n" + "    }\r\n" + "}";

			Response createTaskResponse = apiUtils.submitRequest(METHOD_TYPE.POST, createTaskJson, headers,
					AddFormTaskUrl);
			SimpleDataResponse validateResponse1 = gson.fromJson(createTaskResponse.asString(),
					SimpleDataResponse.class);
			TestValidation.IsTrue((validateResponse1.Status), "Task named " + fp.TaskName + " is Created",
					"Could NOT Create Task " + fp.TaskName);
			return true;
		} catch (Exception e) {
			logError("Failed to create Task - " + e.getMessage());
			return false;
		}

	}

	public String getScheduleInterval(String intervalUnit, String interval) {
		try {
			String intervalId = "";
			apiUtils = new ApiUtils();
			Gson gson = new GsonBuilder().create();
			Map<String, String> headers = new HashMap<String, String>();
			headers.put("Content-Type", "application/json");
			headers.put("Accept", "application/json, text/plain, /");
			headers.put("Authorization", "Bearer " + accessToken);

			//String getScheduleIntervalJson = null;
			Response getScheduleIntervalResponse = apiUtils.submitRequest(METHOD_TYPE.GET, null,
					headers, GetScheduleIntervalUrl);
			MultipleDataResponse mdr = gson.fromJson(getScheduleIntervalResponse.asString(),
					MultipleDataResponse.class);

			for (int i = 0; i < mdr.Data.size(); i++) {
				// logInfo("location instance= " + sdr.Data.get(i).Name);

				if (mdr.Data.get(i).Type.equalsIgnoreCase(intervalUnit)
						&& mdr.Data.get(i).Value.toString().equalsIgnoreCase(interval)) {

					logInfo("location instance is already present= " + mdr.Data.get(i).Id);
					intervalId = mdr.Data.get(i).Id;
					break;
				}

			}


			return intervalId;
		} catch (Exception e) {
			logError("Failed to create Task - " + e.getMessage());
			return null;
		}

	}


	public String getExpirationInterval(String intervalFormat) {
		try {
			String intervalId = "";
			apiUtils = new ApiUtils();
			Gson gson = new GsonBuilder().create();
			Map<String, String> headers = new HashMap<String, String>();
			headers.put("Content-Type", "application/json");
			headers.put("Accept", "application/json, text/plain, /");
			headers.put("Authorization", "Bearer " + accessToken);

			//String getExpirationIntervalJson = null;
			Response getExpirationIntervalResponse = apiUtils.submitRequest(METHOD_TYPE.GET, null,
					headers, GetExpirationIntervalUrl);
			MultipleDataResponse mdr = gson.fromJson(getExpirationIntervalResponse.asString(),
					MultipleDataResponse.class);

			for (int i = 0; i < mdr.Data.size(); i++) {
				// logInfo("location instance= " + sdr.Data.get(i).Name);

				if (mdr.Data.get(i).IntervalFormat.equalsIgnoreCase(intervalFormat)) {

					logInfo("location instance is already present= " + mdr.Data.get(i).ExpirationIntervalId);
					intervalId = mdr.Data.get(i).ExpirationIntervalId;
					break;
				}

			}


			return intervalId;
		} catch (Exception e) {
			logError("Failed to create Task - " + e.getMessage());
			return null;
		}

	}


	public boolean createSchedulerTask(TaskParams tp, String workGroupId) {
		try {

			String intervalId="";
			String expirationIntervalId="";

			if(tp.intervalUnit !=null)
			{

				intervalId=getScheduleInterval(tp.intervalUnit.toString(),tp.Interval);
			}
			expirationIntervalId=getExpirationInterval(tp.ExpirationIntervalType);

			logInfo("Interval Id = "+intervalId);
			logInfo("Expiration Interval Id = "+expirationIntervalId);

			apiUtils = new ApiUtils();
			Gson gson = new GsonBuilder().create();
			Map<String, String> headers = new HashMap<String, String>();
			headers.put("Content-Type", "application/json");
			headers.put("Accept", "application/json, text/plain, /");
			headers.put("Authorization", "Bearer " + accessToken);


			// 4. Create Task
			String createScheduleTaskJson = "{\r\n" + "    \"Template\": {\r\n"
					+ "        \"TaskName\": \""+tp.TaskName+"\",\r\n" + "        \"TaskType\": \"Form\",\r\n"
					+ "        \"FormEntityId\": \"" + tp.FormId + "\",\r\n" + "        \"DocumentId\": null,\r\n"
					+ "        \"LocationId\": \"" + tp.LocationInstanceId + "\",\r\n"
					+ "        \"TaskExpiration\": {\r\n"
					+ "            \"ExpirationIntervalId\": \""+expirationIntervalId+"\",\r\n"
					+ "            \"ExpirationIntervalType\": \""+tp.ExpirationIntervalType+"\"\r\n" + "        }\r\n" + "    },\r\n"
					+ "    \"Schedule\": {\r\n" + "        \"FirstDueDate\": \""+tp.FirstDueDate+"\",\r\n"
					+ "        \"ResourceId\": \"" + tp.ResourceInstanceId + "\",\r\n"
					+ "        \"AssignedToWorkGroupId\": \"" + workGroupId + "\",\r\n"
					+ "        \"AssignTimeBeforeTaskIsDue\": {\r\n" + "            \"Months\": 0,\r\n"
					+ "            \"Weeks\":"+tp.AssignTimeBeforeTaskIsDueWeeks+",\r\n" + "            \"Days\":"+tp.AssignTimeBeforeTaskIsDueDays+",\r\n"
					+ "            \"Hours\": "+tp.AssignTimeBeforeTaskIsDueHours+",\r\n" + "            \"Minutes\":"+tp.AssignTimeBeforeTaskIsDueMinutes+"\r\n" + "        },\r\n"
					+ "        \"TimeZone\": {\r\n" + "            \"Id\": \"Asia/Kolkata\",\r\n"
					+ "            \"DisplayName\": \""+tp.Timezone+"\"\r\n" + "        },\r\n"
					+ "        \"DayOfMonthTaskOccures\": {},\r\n" + "        \"DaysOfWeek\": [],\r\n"
					+ "         \"IntervalStartDate\": \""+tp.IntervalStartDate+"\",\r\n"
					+ "        \"IntervalEndDate\": \""+tp.IntervalEndDate+"\",\r\n"
					+ "        \"IntervalStartTime\": \""+tp.IntervalStartTime+"\",\r\n" + "        \"IntervalEndTime\": \""+tp.IntervalEndTime+"\","
					+ "         \"IsRandom\": false,\r\n" + "        \"IsCarryOver\": false,\r\n"
					+ "        \"IsSchedulePreRandomize\": false," + "        \"IsResourceRandomize\": false,\r\n"
					+ " 			\"IntervalUnit\": \""+tp.intervalUnit+"\",\r\n" + "        \"Interval\": \""+tp.Interval+"\",\r\n"
					+ "        \"IntervalId\": \""+intervalId+"\"" + "    },\r\n"
					+ "    \"ParentSchedule\": {\r\n" + "        \"Occurrence\": \""+tp.Occurrence+"\",\r\n"
					+ "        \"Repetition\": 1\r\n" + "    }\r\n" + "}";

			Response createTaskResponse = apiUtils.submitRequest(METHOD_TYPE.POST, createScheduleTaskJson, headers,
					AddSchedulerTaskUrl);
			SimpleDataResponse validateResponse1 = gson.fromJson(createTaskResponse.asString(),
					SimpleDataResponse.class);
			TestValidation.IsTrue((validateResponse1.Status), "Task named " + tp.TaskName + " is Created",
					"Could NOT Create Task " + tp.TaskName);
			return true;
		} catch (Exception e) {
			logError("Failed to create Task - " + e.getMessage());
			return false;
		}

	}

	public String createTaskId(TaskParams fp, String workGroupId) {
		try {			
			apiUtils = new ApiUtils();
			Gson gson = new GsonBuilder().create();
			Map<String, String> headers = new HashMap<String, String>();
			headers.put("Content-Type", "application/json");
			headers.put("Accept", "application/json, text/plain, /");
			headers.put("Authorization", "Bearer " + accessToken);
			// 4. Create Task
			String createTaskJson = "{\r\n" + "    \"Type\": \"Form\",\r\n" + "    \"Status\": \"Unread\",\r\n"
					+ "    \"TimelineStatus\": \"OnTrack\",\r\n" + "    \"AssignmentType\": \"WorkGroup\",\r\n"
					+ "    \"FormInfo\": {\r\n" + "        \"Id\": \"" + fp.FormId + "\",\r\n" + "        \"Name\": \""
					+ fp.FormName + "\",\r\n" + "        \"Version\": {\r\n" + "            \"major\": 1\r\n"
					+ "        },\r\n" + "        \"Type\": \"Check\"\r\n" + "    },\r\n" + "    \"Resource\": {\r\n"
					+ "        \"Id\": \"" + fp.ResourceInstanceId + "\",\r\n" + "        \"Name\": \""
					+ fp.ResourceInstanceNm + "\",\r\n" + "        \"DisplayName\": \"" + fp.ResourceInstanceNm
					+ "\"\r\n" + "    },\r\n" + "    \"Version\": \"1.0\",\r\n" + "    \"Name\": \"" + fp.TaskName
					+ "\",\r\n" + "    \"DueBy\": {\r\n" + "        \"Date\": \"" + fp.DueBy + "\",\r\n"
					+ "        \"TimeZone\": null\r\n" + "    },\r\n" + "    \"Note\": null,\r\n"
					+ "    \"AssignmentTo\": {\r\n" + "        \"TaskCount\": 0,\r\n" + "        \"Id\": \""
					+ workGroupId + "\",\r\n" + "        \"Name\": \"" + fp.WorkGroupName + "\"\r\n" + "    },\r\n"
					+ "    \"LocationDetails\": {\r\n" + "        \"Id\": \"" + fp.LocationInstanceId + "\",\r\n"
					+ "        \"Name\": \"" + fp.LocationInstanceNm + "\"\r\n" + "    }\r\n" + "}";

			Response createTaskResponse = apiUtils.submitRequest(METHOD_TYPE.POST, createTaskJson, headers,
					AddFormTaskUrl);
			SimpleDataResponse sdr = gson.fromJson(createTaskResponse.asString(), SimpleDataResponse.class);
			TestValidation.IsTrue((sdr.Status), "Task named " + fp.TaskName + " is Created",
					"Could NOT Create Task " + fp.TaskName);
			taskId = sdr.Data;
			return taskId;
		} catch (Exception e) {
			logError("Failed to create Task - " + e.getMessage());
			return taskId;
		}

	}

	public boolean recallTask(String taskId) {		
		try {
			apiUtils = new ApiUtils();
			Gson gson = new GsonBuilder().create();
			Map<String, String> headers = new HashMap<String, String>();
			headers.put("Content-Type", "application/json");
			headers.put("Accept", "application/json, text/plain, /");
			headers.put("Authorization", "Bearer " + accessToken);

			String recalltask ="{"
					+ "\"WorkFlowId\":\"00000000-0000-0000-0000-000000000000\","
					+ "\"TaskId\":\""+taskId+"\","
					+ "\"Type\":\"Form\","
					+ "\"AssignmentType\":\"Workgroup\"}";

			Response recallTaskResponse = apiUtils.submitRequest(METHOD_TYPE.POST, recalltask, headers,
					RecallTaskUrl);
			SimpleDataResponse validateResponse = gson.fromJson(recallTaskResponse.asString(),
					SimpleDataResponse.class);
			TestValidation.IsTrue((validateResponse.Status), "Task Id " + taskId + " is recalled",
					"Could NOT recall Task " + taskId);
			return true;			

		}catch (Exception e) {
			logError("Failed to recall Task - " + e.getMessage());
			return false;
		}
	}

	public boolean reassignTask(TaskDetails tsd,String newWgId) {		
		try {
			apiUtils = new ApiUtils();
			Gson gson = new GsonBuilder().create();
			Map<String, String> headers = new HashMap<String, String>();
			headers.put("Content-Type", "application/json");
			headers.put("Accept", "application/json, text/plain, /");
			headers.put("Authorization", "Bearer " + accessToken);

			String reassigntask = "{"
					+ "\"Task\":{\"Id\":\""+tsd.TaskId+"\","
					+ "\"Name\":\""+tsd.TaskName+"\"},\"DueBy\":{\"Date\":\"\",\"TimeZone\":\"\"},\"Type\":\"Form\","
					+ "\"TaskExpiration\":{\"ExpirationIntervalId\":\"\",\"ExpirationIntervalType\":\"\",\"ExpirationIntervalValue\":null},"
					+ "\"AssignmentTo\":{\"TaskCount\":0,\"Id\":\""+newWgId+"\","
					+ "\"Name\":\""+tsd.ChangedWorkGroupName+"\"},\"Note\":null,\"IsWorkGroupChanged\":true,\"FormOrDocumentId\":\"\"}\"";

			Response reassignTaskResponse = apiUtils.submitRequest(METHOD_TYPE.POST, reassigntask, headers,
					ReassignTaskUrl);
			SimpleDataResponse validateResponse = gson.fromJson(reassignTaskResponse.asString(),
					SimpleDataResponse.class);
			TestValidation.IsTrue((validateResponse.Status), "Task Id " + tsd.TaskId + " is reassigned",
					"Could NOT reassign Task " + tsd.TaskId);
			return true;			

		}catch (Exception e) {
			logError("Failed to reassign Task - " + e.getMessage());
			return false;
		}
	}

	public static class TaskDetails {
		public String TaskId;
		public String TaskName;
		public String WorkgroupId;
		public String WorkGroupName;
		public String ChangedWorkGroupName;
		public String ChangedWorkgroupId;
	}


}
