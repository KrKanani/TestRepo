package com.project.safetychain.api.models;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.project.safetychain.api.models.support.Support;
import com.project.safetychain.api.models.support.Support.AGGREGATE_FUNC_TYPES;
import com.project.safetychain.api.models.support.Support.Compliance;
import com.project.safetychain.api.models.support.Support.DPT_FIELD_TYPES;
import com.project.safetychain.api.models.support.Support.DPT_FORM_TYPES;
import com.project.safetychain.api.models.support.Support.Element_Types;
import com.project.safetychain.api.models.support.Support.FORM_TYPES;
import com.project.safetychain.api.models.support.Support.FormFieldParams;
import com.project.safetychain.api.models.support.Support.FormFieldParamsCompliance;
import com.project.safetychain.api.models.support.Support.FormParams;
import com.project.safetychain.api.models.support.Support.IDENTIFIER_TYPES;
import com.project.safetychain.api.models.support.Support.RESOURCE_TYPES;
import com.project.utilities.ApiUtils;
import com.project.utilities.ApiUtils.METHOD_TYPE;

import io.restassured.response.Response;

public class FormDesigner extends Auth {

	public static String formName;

	/** Web APIs */
	public String SaveFormDesignUrl = baseURI + FormDesignerUrls.SAVE_FORM_DESIGN_URL;
	public String AddFieldUrl = baseURI + FormDesignerUrls.ADD_FIELD_URL;
	public String SubmitFormUrl = baseURI + FormDesignerUrls.SUBMIT_FORM_URL;
	public String ValidateReleaseUrl = baseURI + FormDesignerUrls.VALIDATE_RELEASE_URL;
	public String ReleaseFormUrl = baseURI + FormDesignerUrls.RELEASE_FORM_URL;
	public String GetFormsUrl = baseURI + FormDesignerUrls.GET_FORMS_URL;
	public String VerificationUrl = baseURI + FormDesignerUrls.SAVE_FORMS_VERIFICATION_URL;

	public String GetLocationCategories = baseURI + FormDesignerUrls.GET_ROOT_LOCATIONS_CAT_URL;
	public String manageResourceCategories = baseURI + FormDesignerUrls.MANAGE_RESOURCE_CATEGORIES_URL;

	/** DPT APIs */
	public String DptSaveFormDesignUrl = baseURI + FormDesignerUrls.DPT_SAVE_FORM_DESIGN_URL;
	public String DptAddUpdateComplianceUrl = baseURI + FormDesignerUrls.DPT_ADD_UPDATE_FORM_COMPLIANCE_URL;
	public String DptGetFormComplianceUrl = baseURI + FormDesignerUrls.DPT_GET_FORM_COMPLIANCE_URL;
	public String DptGetFormDefinitionUrl = baseURI + FormDesignerUrls.DPT_GET_FORM_DEFINITION_URL;

	public String otherGetFormsUrl = otherBaseURI + FormDesignerUrls.GET_FORMS_URL;
	public String otherDptSaveFormDesignUrl = otherBaseURI + FormDesignerUrls.DPT_SAVE_FORM_DESIGN_URL;
	public String otherValidateReleaseUrl = otherBaseURI + FormDesignerUrls.VALIDATE_RELEASE_URL;
	public String otherReleaseFormUrl = otherBaseURI + FormDesignerUrls.RELEASE_FORM_URL;
	public String otherGetLocationCategories = otherBaseURI + FormDesignerUrls.GET_ROOT_LOCATIONS_CAT_URL;
	public String otherManageResourceCategories = otherBaseURI + FormDesignerUrls.MANAGE_RESOURCE_CATEGORIES_URL;
	public String otherGetFormLocationUrl = otherBaseURI + FormDesignerUrls.GET_FORM_LOCATION_URL;
	public String otherGetFormResourcesForLocUrl = baseURI + FormDesignerUrls.GET_FORM_RESOURCES_LOC_URL;


	ApiUtils apiUtils = new ApiUtils();
	Gson gson = new GsonBuilder().create();
	Random rand = new Random();

	public static LinkedHashMap<String, String> shortNames = new LinkedHashMap<String, String>();

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

	public String updateFormElements(FormFieldParams ffp, boolean lastField) {
		String addField = null;
		String identifierDets = "";
		String selectOptions = "";
		String predefinedOptions = "";
		String DepRule = "";
		String ExpRule = "";
		String shortName = "";
		String repeatIds = "";
		String AggFuct = "";

		shortName = shortNames.get(ffp.Name);

		try {

			// For Identifier fields
			if (ffp.DPTFieldType.equalsIgnoreCase(DPT_FIELD_TYPES.IDENTIFIER)) {

				if (ffp.identifierId.equalsIgnoreCase(IDENTIFIER_TYPES.FREE_TEXT)) {
					identifierDets = "					 \"InputType\": {\r\n" + "                        \"Id\": \""
							+ ffp.identifierId + "\",\r\n" +
							"                        \"Name\": \"Free Text\"\r\n" + 
							"                    },\r\n" + "                    \"InputMask\": \"" + ffp.InputMask
							+ "\",\r\n" + "                    \"IsFilterable\": " + ffp.IsFilterable + ",\r\n"
							+ "                    \"IdentifierOption\": " + ffp.IdentifierOption + ",";
				} else if (ffp.identifierId.equalsIgnoreCase(IDENTIFIER_TYPES.SELECT_ONE)) {
					identifierDets = "					 \"InputType\": {\r\n" + "                        \"Id\": \""
							+ ffp.identifierId + "\",\r\n" + "                        \"Name\": \"Select One\"\r\n"
							+ "                    },\r\n" + "                    \"InputMask\": \"" + ffp.InputMask
							+ "\",\r\n" + "                    \"IsFilterable\": " + ffp.IsFilterable + ",\r\n"
							+ "                    \"IdentifierOption\": ["
							+ Support.updateIdentifierListForSelectOne(ffp.IdentifierOption) + "],";
				}
			}
			// For Select One/Multiple
			else if (ffp.DPTFieldType.equalsIgnoreCase(DPT_FIELD_TYPES.SELECT_ONE)
					|| ffp.DPTFieldType.equalsIgnoreCase(DPT_FIELD_TYPES.SELECT_MULTIPLE)) {
				if(ffp.QuestionWeight.equals("0") ||ffp.QuestionWeight.equals("null"))
				{

					selectOptions = "					 \"ControlType\": " + ffp.ControlType + ",\r\n"
							+ "                    \"Options\": [\r\n" + Support.updateNameList(ffp.SelectOptions)
							+ "                    ],";
				}
				else
				{
					selectOptions = "					 \"ControlType\": " + ffp.ControlType + ",\r\n"
							+ "                    \"Options\": [\r\n" + Support.updateNameListWithQuestionWeight(ffp.SelectOptions)
							+ "                    ],";

				}
			}
			if (ffp.AllowCorrection.equalsIgnoreCase("true") && ffp.PredefinedCorrections != null) {
				predefinedOptions = "[\r\n" + Support.updateNameList(ffp.PredefinedCorrections)
				+ "                    ]";
			} else {
				predefinedOptions = "null";
			}


			if (!ffp.DependencyRule.equalsIgnoreCase("null")) {
				String fieldName = "";

				Pattern pattern = Pattern.compile("if(.*?)=");
				Matcher matcher = pattern.matcher(ffp.DependencyRule);
				if (matcher.find()) {

					fieldName = matcher.group(1);
				}
				fieldName = fieldName.replace("(", "");

				System.out.println("fieldName=" + fieldName);

				for (Map.Entry<String, String> entry : shortNames.entrySet()) {
					String Key = entry.getKey();
					if (Key.equals(fieldName)) {
						System.out.println("Entry Value =" + entry.getValue());
						ffp.DependencyRule = ffp.DependencyRule.replace(fieldName, entry.getValue());

						break;

					}

				}

				logInfo("updated Dependency Rule =" + ffp.DependencyRule);

				DepRule = "{\r\n" + "                        \"Name\": \"DepRule" + rand.nextInt(50000) + "\",\r\n"
						+ "                        \"Rule\": \"" + ffp.DependencyRule + "\"\r\n"
						+ "                    }";
			} else {
				DepRule = "null";
			}

			if (!ffp.ExpressionRule.equalsIgnoreCase("null")) {

				String[] fields = ffp.ExpressionRule.split("[\\s@&.?$+-]+");

				for(String fldname:fields) {
					for (Map.Entry<String, String> entry : shortNames.entrySet()) {
						String Key = entry.getKey();
						if (Key.equals(fldname)) {
							System.out.println("Entry Value =" + entry.getValue());
							ffp.ExpressionRule = ffp.ExpressionRule.replace(fldname, entry.getValue());
							break;

						}
					}
				}

				logInfo("updated Expression Rule =" + ffp.ExpressionRule);

				ExpRule = "{\r\n" + "                        \"Name\": \"ExpRule" + rand.nextInt(50000) + "\",\r\n"
						+ "                        \"Rule\": \"" + ffp.ExpressionRule + "\"\r\n"
						+ "                    }";
			} else {
				ExpRule = "null";
			}


			if (!(ffp.Repeat.equalsIgnoreCase("null"))) {
				Support support = new Support();

				String repeatId = support.updateRepeatIdsListForSelectOne(ffp.Repeat);

				repeatIds = "[" + repeatId + "]";
			} else {
				repeatIds = "null";
			}

			if (ffp.DPTFieldType.equalsIgnoreCase(DPT_FIELD_TYPES.AGGREGATE)) {

				if (ffp.SelectedFunction.equalsIgnoreCase(AGGREGATE_FUNC_TYPES.SUM)) {
					AggFuct = " \"SelectedFunction\": {\r\n"

							+ "                        \"Name\": \"" + ffp.SelectedFunction + "\"\r\n"
							+ "                    },\r\n" + "                    \"SelectedSource\": {\r\n"

							+ "                        \"Name\": \"" + ffp.SelectedSource + "\"\r\n"
							+ "                    },\r\n"
							+ "                    \"SelectedFunctionParameters\": null,\r\n"
							+ "                    \"SelectedFunctionDetails\": {\r\n"
							+ "                        \"Type\": 0,\r\n"
							+ "                        \"CastType\": null\r\n" + "                    }\r\n,"
							+ "                    \"SelectedSourceCollection\": null,";
				} else if (ffp.SelectedFunction.equalsIgnoreCase(AGGREGATE_FUNC_TYPES.COUNT_RANGE)
						|| ffp.SelectedFunction.equalsIgnoreCase(AGGREGATE_FUNC_TYPES.SUM_RANGE)) {
					AggFuct = "              \"SelectedFunction\": {\r\n" + "                         \"Name\": \""
							+ ffp.SelectedFunction + "\"\r\n" + "                    },\r\n"
							+ "                    \"SelectedSource\": {\r\n" + "                       \r\n"
							+ "                        \"Name\": null\r\n" + "                    },\r\n"
							+ "                    \"SelectedFunctionParameters\": [\r\n"
							+ "                        {\r\n" + "                            \"Key\": \"Min\",\r\n"
							+ "                            \"Value\": " + ffp.AGG_MIN + "\r\n"
							+ "                        },\r\n" + "                        {\r\n"
							+ "                            \"Key\": \"Max\",\r\n"
							+ "                            \"Value\": " + ffp.AGG_MAX + "\r\n"
							+ "                        }\r\n" + "                    ],\r\n"
							+ "                    \"SelectedFunctionDetails\": {\r\n"
							+ "                        \"Type\": 1,\r\n"
							+ "                        \"CastType\": \"Number\"\r\n" + "                    },\r\n"
							+ "                    \"SelectedSourceCollection\": [\r\n"
							+ updateSourceCollectionList(ffp.SelectedSourceCollection) + "                    ],";
				} else if (ffp.SelectedFunction.equalsIgnoreCase(AGGREGATE_FUNC_TYPES.AVERAGE)) {
					AggFuct = " \"SelectedFunction\": {\r\n"

							+ "                        \"Name\": \"" + ffp.SelectedFunction + "\"\r\n"
							+ "                    },\r\n" + "                    \"SelectedSource\": {\r\n"

							+ "                        \"Name\": \"" + ffp.SelectedSource + "\"\r\n"
							+ "                    },\r\n"
							+ "                    \"SelectedFunctionParameters\": null,\r\n"
							+ "                    \"SelectedFunctionDetails\": {\r\n"
							+ "                        \"Type\": 0,\r\n"
							+ "                        \"CastType\": null\r\n" + "                    }\r\n,"
							+ "                    \"SelectedSourceCollection\": null,";
				}else if (ffp.SelectedFunction.equalsIgnoreCase(AGGREGATE_FUNC_TYPES.MAX_VAL)) {
					AggFuct = " \"SelectedFunction\": {\r\n"

							+ "                        \"Name\": \"" + ffp.SelectedFunction + "\"\r\n"
							+ "                    },\r\n" + "                    \"SelectedSource\": {\r\n"

							+ "                        \"Name\": \"" + ffp.SelectedSource + "\"\r\n"
							+ "                    },\r\n"
							+ "                    \"SelectedFunctionParameters\": null,\r\n"
							+ "                    \"SelectedFunctionDetails\": {\r\n"
							+ "                        \"Type\": 0,\r\n"
							+ "                        \"CastType\": null\r\n" + "                    }\r\n,"
							+ "                    \"SelectedSourceCollection\": null,";
				}else if (ffp.SelectedFunction.equalsIgnoreCase(AGGREGATE_FUNC_TYPES.MIN_VAL)) {
					AggFuct = " \"SelectedFunction\": {\r\n"

							+ "                        \"Name\": \"" + ffp.SelectedFunction + "\"\r\n"
							+ "                    },\r\n" + "                    \"SelectedSource\": {\r\n"

							+ "                        \"Name\": \"" + ffp.SelectedSource + "\"\r\n"
							+ "                    },\r\n"
							+ "                    \"SelectedFunctionParameters\": null,\r\n"
							+ "                    \"SelectedFunctionDetails\": {\r\n"
							+ "                        \"Type\": 0,\r\n"
							+ "                        \"CastType\": null\r\n" + "                    }\r\n,"
							+ "                    \"SelectedSourceCollection\": null,";
				}else if (ffp.SelectedFunction.equalsIgnoreCase(AGGREGATE_FUNC_TYPES.MEDIAN)) {
					AggFuct = " \"SelectedFunction\": {\r\n"

							+ "                        \"Name\": \"" + ffp.SelectedFunction + "\"\r\n"
							+ "                    },\r\n" + "                    \"SelectedSource\": {\r\n"

							+ "                        \"Name\": \"" + ffp.SelectedSource + "\"\r\n"
							+ "                    },\r\n"
							+ "                    \"SelectedFunctionParameters\": null,\r\n"
							+ "                    \"SelectedFunctionDetails\": {\r\n"
							+ "                        \"Type\": 0,\r\n"
							+ "                        \"CastType\": null\r\n" + "                    }\r\n,"
							+ "                    \"SelectedSourceCollection\": null,";

				}else if (ffp.SelectedFunction.equalsIgnoreCase(AGGREGATE_FUNC_TYPES.RANGE)) {
					AggFuct = " \"SelectedFunction\": {\r\n"

							+ "                        \"Name\": \"" + ffp.SelectedFunction + "\"\r\n"
							+ "                    },\r\n" + "                    \"SelectedSource\": {\r\n"

							+ "                        \"Name\": \"" + ffp.SelectedSource + "\"\r\n"
							+ "                    },\r\n"
							+ "                    \"SelectedFunctionParameters\": null,\r\n"
							+ "                    \"SelectedFunctionDetails\": {\r\n"
							+ "                        \"Type\": 0,\r\n"
							+ "                        \"CastType\": null\r\n" + "                    }\r\n,"
							+ "                    \"SelectedSourceCollection\": null,";
				}else if (ffp.SelectedFunction.equalsIgnoreCase(AGGREGATE_FUNC_TYPES.STANDARD_DEVIATION)) {
					AggFuct = " \"SelectedFunction\": {\r\n"

							+ "                        \"Name\": \"" + ffp.SelectedFunction + "\"\r\n"
							+ "                    },\r\n" + "                    \"SelectedSource\": {\r\n"

							+ "                        \"Name\": \"" + ffp.SelectedSource + "\"\r\n"
							+ "                    },\r\n"
							+ "                    \"SelectedFunctionParameters\": null,\r\n"
							+ "                    \"SelectedFunctionDetails\": {\r\n"
							+ "                        \"Type\": 0,\r\n"
							+ "                        \"CastType\": null\r\n" + "                    }\r\n,"
							+ "                    \"SelectedSourceCollection\": null,";	
				}
			}


			addField = "                {\r\n" + "                    \"Type\": " + ffp.DPTFieldType + ",\r\n" + AggFuct
					+ "" + // Update
					identifierDets + selectOptions + "                    \"DependencyRule\": " + DepRule + ",\r\n"
					+ "                    \"ExpressionRule\": " + ExpRule + ",\r\n"
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
					+ "                    \"ShowMinMax\": " + ffp.ShowMinMax + ",\r\n"
					+ "                    \"ShowTarget\": " + ffp.ShowTarget + ",\r\n"
					+ "                    \"ShowHint\": " + ffp.ShowHint + ",\r\n"
					+ "                    \"Hint\":\" "+ffp.Hint+"\",\r\n"
					+ "                    \"ShowPointsPossible\": null,\r\n"
					+ "                    \"ShowPointsEarned\": null,\r\n"
					+ "                    \"QuestionWeight\": "+ffp.QuestionWeight+",\r\n" + "                    \"Order\": 0,\r\n"
					+ "                    \"Repeat\": " + ffp.Repeat + ",\r\n" + "                    \"RepeatIds\": "
					+ repeatIds + ",\r\n"
					+ "                    \"GroupID\": \"00000000-0000-0000-0000-000000000000\",\r\n"
					+ "                    \"ShowField\": true,\r\n"
					+"                     \"ShowFieldValidation\": "+ffp.ShowFieldValidation+",\r\n"
					+"                     \"MinValue\": 10.0, \r\n"
					+"                     \"MaxValue\": 110.0, \r\n"
					+"                     \"ShowSignificantDigit\": true, \r\n"
					+"                     \"DecimalPrecision\": 1, \r\n"
					+ "                    \"FieldBankId\": \"00000000-0000-0000-0000-000000000000\",\r\n"
					+ "                    \"ParentId\": null,\r\n" + "                    \"Value\": null,\r\n"
					+ "                    \"Default\": null,\r\n" + "                    \"Name\": \"" + ffp.Name
					+ "\",\r\n" + "                    \"ShortName\": \"" + shortName + "\"\r\n";

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

	public String updateGroupElementsInsideSection(Element_Types fields, boolean lastField) {
		String addField = "";
		String updatedField = "";
		String DepRule = "";
		@SuppressWarnings("unused")
		String ExpRule = "";

		try {

			for (int i = 0; i < fields.formFieldParams.size(); i++) {
				if (i != (fields.formFieldParams.size() - 1)) {
					updatedField += updateFormElements(fields.formFieldParams.get(i), false);
				} else {
					updatedField += updateFormElements(fields.formFieldParams.get(i), true);
				}
			}

			if (!fields.DependencyRule.equalsIgnoreCase("null")) {
				String fieldName = "";

				Pattern pattern = Pattern.compile("if(.*?)=");
				Matcher matcher = pattern.matcher(fields.DependencyRule);
				if (matcher.find()) {

					fieldName = matcher.group(1);
				}
				fieldName = fieldName.replace("(", "");

				System.out.println("fieldName=" + fieldName);

				for (Map.Entry<String, String> entry : shortNames.entrySet()) {
					String Key = entry.getKey();
					if (Key.equals(fieldName)) {
						System.out.println("Entry Value =" + entry.getValue());
						fields.DependencyRule = fields.DependencyRule.replace(fieldName, entry.getValue());

						break;

					}

				}

				logInfo("updated Dependency Rule =" + fields.DependencyRule);

				DepRule = "{\r\n" + "                        \"Name\": \"DepRule" + rand.nextInt(50000) + "\",\r\n"
						+ "                        \"Rule\": \"" + fields.DependencyRule + "\"\r\n"
						+ "                    }";
			} else {
				DepRule = "null";
			}

			if (!fields.ExpressionRule.equalsIgnoreCase("null")) {
				String fieldName = "";

				Pattern pattern = Pattern.compile("if(.*?)=");
				Matcher matcher = pattern.matcher(fields.ExpressionRule);
				if (matcher.find()) {

					fieldName = matcher.group(1);
				}
				fieldName = fieldName.replace("(", "");

				System.out.println("fieldName=" + fieldName);

				for (Map.Entry<String, String> entry : shortNames.entrySet()) {
					String Key = entry.getKey();
					if (Key.equals(fieldName)) {
						System.out.println("Entry Value =" + entry.getValue());
						fields.ExpressionRule = fields.ExpressionRule.replace(fieldName, entry.getValue());

						break;

					}

				}

				logInfo("updated Expression Rule =" + fields.ExpressionRule);

				ExpRule = "{\r\n" + "                        \"Name\": \"ExpRule" + rand.nextInt(50000) + "\",\r\n"
						+ "                        \"Rule\": \"" + fields.ExpressionRule + "\"\r\n"
						+ "                    }";
			} else {
				ExpRule = "null";
			}

			addField = "      {  \"Name\": \"" + fields.Name + "\",\r\n" + "                    \"ShortName\": \""
					+ shortNames.get(fields.Name) + "\",\r\n" + "                    \"ElementType\": "
					+ fields.ElementType + ",\r\n" + "                    \"Order\": 0,\r\n"
					+ "                    \"Fields\": [\r\n" + updatedField + "" + "                    ],\r\n"
					+ "                    \"ShowGroupName\": true,\r\n" + "                    \"ShowHint\": "
					+ fields.ShowHint + ",\r\n" + "                    \"Hint\": " + fields.ShowHint + ",\r\n\" "
					+ "Repeat\": " + fields.Repeat + ",\r\n" + "                    \"RepeatIds\": " + fields.RepeatIds
					+ ",\r\n" + "                    \"GroupID\": \"00000000-0000-0000-0000-000000000000\",\r\n"
					+ "                    \"ShowField\": true,\r\n" + "                    \"DependencyRule\": "
					+ DepRule + "," + "                    \"ParentId\": null\r\n" + "                ";

			// For Identifier fields

			if (!lastField) {

				addField += "                },\r\n";
			} else {
				addField += "                }\r\n";
			}

			return addField;
		} catch (Exception e) {
			logError("Failed to set form element for - " + fields.Name + e.getMessage());
			return addField;
		}
	}

	public String updateFieldGroupElements(Element_Types fields, boolean lastField) {
		String addField = "";
		String updatedFieldGroup = "";
		String updatedField = "";
		String DepRule = "";
		@SuppressWarnings("unused")
		String ExpRule = "";

		try {
			if (fields.FieldGroupParams != null) {
				for (int i = 0; i < fields.FieldGroupParams.size(); i++) {
					if (i != (fields.FieldGroupParams.size() - 1)) {
						updatedFieldGroup += updateGroupElementsInsideSection(fields.FieldGroupParams.get(i), false);
					} else {
						updatedFieldGroup += updateGroupElementsInsideSection(fields.FieldGroupParams.get(i), true);
					}
				}
			}
			if (fields.formFieldParams != null) {
				for (int i = 0; i < fields.formFieldParams.size(); i++) {
					if (i != (fields.formFieldParams.size() - 1)) {
						updatedField += updateFormElements(fields.formFieldParams.get(i), false);
					} else {
						updatedField += updateFormElements(fields.formFieldParams.get(i), true);
					}
				}
			}

			if (!fields.DependencyRule.equalsIgnoreCase("null")) {

				String DependencyRule = extractStringUsingRegEx("if(.*?)=", fields.DependencyRule);
				DependencyRule = extractStringUsingRegEx("if(.*?)<", DependencyRule);
				DependencyRule = extractStringUsingRegEx("if(.*?)>", DependencyRule);
				DependencyRule = extractStringUsingRegEx("elseif(.*?)=", DependencyRule);

				logInfo("updated Dependency Rule =" + DependencyRule);

				DepRule = "{\r\n" + "                        \"Name\": \"DepRule" + rand.nextInt(50000) + "\",\r\n"
						+ "                        \"Rule\":\" " + DependencyRule + "\"\r\n" + "                    }";
			} else {
				DepRule = "null";
			}

			if (updatedField != "" && updatedFieldGroup != "") {
				updatedField = updatedField + "," + updatedFieldGroup  ;
			} else if (updatedField == "" && updatedFieldGroup != "") {
				updatedField = updatedFieldGroup;
			}

			if (!fields.ExpressionRule.equalsIgnoreCase("null")) {
				String fieldName = "";

				Pattern pattern = Pattern.compile("if(.*?)=");
				Matcher matcher = pattern.matcher(fields.ExpressionRule);
				if (matcher.find()) {

					fieldName = matcher.group(1);
				}
				fieldName = fieldName.replace("(", "");

				System.out.println("fieldName=" + fieldName);

				for (Map.Entry<String, String> entry : shortNames.entrySet()) {
					String Key = entry.getKey();
					if (Key.equals(fieldName)) {
						System.out.println("Entry Value =" + entry.getValue());
						fields.ExpressionRule = fields.ExpressionRule.replace(fieldName, entry.getValue());

						break;

					}

				}

				logInfo("updated Expression Rule =" + fields.ExpressionRule);

				ExpRule = "{\r\n" + "                        \"Name\": \"ExpRule" + rand.nextInt(50000) + "\",\r\n"
						+ "                        \"Rule\": \"" + fields.ExpressionRule + "\"\r\n"
						+ "                    }";
			} else {
				ExpRule = "null";
			}

			addField = "      {  \"Name\": \"" + fields.Name + "\",\r\n" + "                    \"ShortName\": \""
					+ fields.Name.replaceAll(" ", "") + rand.nextInt(50000) + "\",\r\n"
					+ "                    \"ElementType\": " + fields.ElementType + ",\r\n"
					+ "                    \"Order\": 0,\r\n" + "                    \"Fields\": [\r\n" + updatedField
					+ "" + "                    ],\r\n" + "                    \"ShowGroupName\": true,\r\n"
					+ "                    \"ShowHint\": " + fields.ShowHint + ",\r\n"
					+ "                    \"Hint\": " + fields.ShowHint + ",\r\n\" " + "Repeat\": " + fields.Repeat
					+ ",\r\n" + "                    \"RepeatIds\": " + fields.RepeatIds + ",\r\n"
					+ "                    \"GroupID\": \"00000000-0000-0000-0000-000000000000\",\r\n"
					+ "                    \"ShowField\": true,\r\n" + "                    \"DependencyRule\": "
					+ DepRule + "," + "                    \"ParentId\": null\r\n" + "                ";

			// For Identifier fields

			if (!lastField) {

				addField += "                },\r\n";
			} else {
				addField += "                }\r\n";
			}

			return addField;
		} catch (Exception e) {
			logError("Failed to set form element for - " + fields.Name + e.getMessage());
			return addField;
		}
	}

	public String appendFormElements(List<FormFieldParams> listOfElements) {
		String addField = "";
		try {
			for (int i = 0; i < listOfElements.size(); i++) {
				if (i != (listOfElements.size() - 1)) {
					//String fieldName = listOfElements.get(i).Name;
					addField += updateFormElements(listOfElements.get(i), false);
				} else {
					//String fieldName = listOfElements.get(i).Name;
					addField += updateFormElements(listOfElements.get(i), true);
				}
			}
			return addField;
		} catch (Exception e) {
			logError("Failed to set form element for - " + e.getMessage());
			return addField;
		}
	}

	public String appendSectionGroupElements(List<Element_Types> listOfElements) {
		String addField = "";

		try {
			for (int i = 0; i < listOfElements.size(); i++) {
				if (i != (listOfElements.size() - 1)) {
					addField += updateFieldGroupElements(listOfElements.get(i), false);
				} else {
					addField += updateFieldGroupElements(listOfElements.get(i), true);
				}
			}
			return addField;
		} catch (Exception e) {
			logError("Failed to set form element for - " + e.getMessage());
			return addField;
		}
	}

	public boolean createUnreleasedForm(FormParams fp) {
		String saveFormDesignRequest = null;
		String fields = "";
		String groups = "";
		int typeCount = 0;
		String ResourceName = "";
		formName = fp.FormName;

		try {
			Map<String, String> headers = new HashMap<String, String>();
			headers.put("Content-Type", "application/json");
			headers.put("Accept", "application/json, text/plain, /");
			headers.put("Authorization", "Bearer " + accessToken);

			if (fp.formElements != null) {

				for (int i = 0; i < fp.formElements.size(); i++) {
					String fieldName = fp.formElements.get(i).Name;
					// logInfo("Element To be Added in 1st"+fieldName);
					String shortName = fieldName.replaceAll(" ", "") + rand.nextInt(50000);
					shortNames.put(fieldName, shortName);
				}
			}

			if (fp.SectionElements != null) {

				for (int i = 0; i < fp.SectionElements.size(); i++) {

					String fieldName = fp.SectionElements.get(i).Name;
					// logInfo("Element To be Added in 1st"+fieldName);
					String shortName = fieldName.replaceAll(" ", "") + rand.nextInt(50000);
					shortNames.put(fieldName, shortName);

					if (fp.SectionElements.get(i).formFieldParams != null) {

						for (int j = 0; j < fp.SectionElements.get(i).formFieldParams.size(); j++) {
							String fieldName1 = fp.SectionElements.get(i).formFieldParams.get(j).Name;
							// logInfo("Element To be Added in 1st"+fieldName);
							String shortName1 = fieldName1.replaceAll(" ", "") + rand.nextInt(50000);
							shortNames.put(fieldName1, shortName1);

						}
					}

					if (fp.SectionElements.get(i).FieldGroupParams != null) {
						logInfo("FieldGroup Size" + fp.SectionElements.get(i).FieldGroupParams.size());
						if (fp.SectionElements.get(i).FieldGroupParams.size() != 0) {
							for (int k = 0; k < fp.SectionElements.get(i).FieldGroupParams.size(); k++) {
								String fieldName1 = fp.SectionElements.get(i).FieldGroupParams.get(k).Name;
								// logInfo("Element To be Added in 1st"+fieldName);
								String shortName1 = fieldName1.replaceAll(" ", "") + rand.nextInt(50000);
								shortNames.put(fieldName1, shortName1);

								for (int l = 0; l < fp.SectionElements.get(i).FieldGroupParams.get(k).formFieldParams
										.size(); l++) {
									String fieldName2 = fp.SectionElements.get(i).FieldGroupParams
											.get(k).formFieldParams.get(l).Name;
									// logInfo("Element To be Added in 1st"+fieldName);
									String shortName2 = fieldName2.replaceAll(" ", "") + rand.nextInt(50000);
									shortNames.put(fieldName2, shortName2);

								}

							}
						}
					}
				}

			}

			logInfo("ShortNames " + shortNames);

			if(fp.formElements != null) {
				fields = appendFormElements(fp.formElements);
			}

			if(fp.SectionElements != null) {
				groups = appendSectionGroupElements(fp.SectionElements);

			}

			if (fields == "") {
				fields = fields + groups;
			}else {
				if(groups != "") {
					fields = fields + "," + groups;
				}
			}

			// fields = fields + "," + groups;
			if (fp.CustomerResources != null) {
				if (typeCount > 0) {
					ResourceName += Support.updateResourceList(fp.CustomerResources, RESOURCE_TYPES.CUSTOMERS);
					ResourceName = "," + ResourceName;
				} else {
					ResourceName += Support.updateResourceList(fp.CustomerResources, RESOURCE_TYPES.CUSTOMERS);
				}
				typeCount++;
			}

			if (fp.EquipmentResources != null) {
				if (typeCount > 0) {
					ResourceName += Support.updateResourceList(fp.EquipmentResources, RESOURCE_TYPES.EQUIPMENT);
					ResourceName = "," + ResourceName;
				} else {
					ResourceName += Support.updateResourceList(fp.EquipmentResources, RESOURCE_TYPES.EQUIPMENT);
				}
				typeCount++;
			}

			if (fp.ItemsResources != null) {
				if (typeCount > 0) {
					ResourceName += Support.updateResourceList(fp.ItemsResources, RESOURCE_TYPES.ITEMS);
					ResourceName = "," + ResourceName;
				} else {
					ResourceName += Support.updateResourceList(fp.ItemsResources, RESOURCE_TYPES.ITEMS);
				}
				typeCount++;
			}

			if (fp.SuppliersResources != null) {
				if (typeCount > 0) {
					ResourceName += Support.updateResourceList(fp.SuppliersResources, RESOURCE_TYPES.SUPPLIERS);
					ResourceName = "," + ResourceName;
				} else {
					ResourceName += Support.updateResourceList(fp.SuppliersResources, RESOURCE_TYPES.SUPPLIERS);
				}
				typeCount++;
			}

			saveFormDesignRequest = "{\r\n" + "  \"Operation\": \"Insert\",\r\n" + "  \"PartialSuccess\": false,\r\n"
					+ "  \"Data\":  [\r\n" + "         {\r\n" + "            \"FormId\": \"" + fp.FormId + "\",\r\n"
					+ "            \"FormName\": \"" + fp.FormName + "\",\r\n" + "            \"Type\": " + fp.type
					+ ",\r\n" + "            \"Version\": \"1.0\",\r\n" + "            \"Resources\": [\r\n"

					+ ResourceName

					+ "            ],\r\n" + "            \"Fields\": [\r\n" + fields + "            ]\r\n"
					+ "        }\r\n" + "      \r\n" + "    ]\r\n" + "}";

			Response submitResponse = apiUtils.submitRequest(METHOD_TYPE.POST, saveFormDesignRequest, headers,
					DptSaveFormDesignUrl);
			SimpleDataResponse submitRes = gson.fromJson(submitResponse.asString(), SimpleDataResponse.class);

			if (submitRes.RequestStatus.equalsIgnoreCase("pending")) {
				logInfo("Created unreleased form - " + fp.FormName);
				return true;
			} else {
				logInfo("Could not create unreleased form - " + fp.FormName);
				return false;
			}

		} catch (Exception e) {
			logError("Failed to create unrelease form - " + e.getMessage());
			return false;
		}
	}

	public boolean verifyIsFormCreated(String resourceType, String formName, Map<String, String> headers) {
		String getFormsRequest = null;
		String resourceId = null;

		try {

			ResourceDesigner rd = new ResourceDesigner();
			if (!rd.setResourceCatIDs())
				return false;

			switch (resourceType) {
			case RESOURCE_TYPES.CUSTOMERS:
				resourceId = customersCatID;
				break;
			case RESOURCE_TYPES.EQUIPMENT:
				resourceId = equipmentCatID;
				break;
			case RESOURCE_TYPES.ITEMS:
				resourceId = itemsCatID;
				break;
			case RESOURCE_TYPES.SUPPLIERS:
				resourceId = suppliersCatID;
				break;
			default:
				logError("Failed to set for Resource Id for Resource Type : " + resourceType);
				return false;
			}

			getFormsRequest = "{\r\n" + "	\"take\": 100,\r\n" + "	\"skip\": 0,\r\n" + "	\"page\": 1,\r\n"
					+ "	\"pageSize\": 100,\r\n" + "	\"sort\": [{\r\n" + "		\"field\": \"ModifiedOn\",\r\n"
					+ "		\"dir\": \"desc\"\r\n" + "	}],\r\n" + "	\"filter\": {\r\n"
					+ "		\"logic\": \"and\",\r\n" + "		\"filters\": [{\r\n"
					+ "			\"field\": \"Name\",\r\n" + "			\"operator\": \"contains\",\r\n"
					+ "			\"value\": \"" + formName + "\"\r\n" + "		}]\r\n" + "	},\r\n"
					+ "	\"Parameters\": {\r\n" + "		\"Node\": {\r\n" + "			\"Type\": \"" + resourceId
					+ "\",\r\n" + "			\"Node\": \"" + resourceId + "\",\r\n" + "			\"IsParent\": true\r\n"
					+ "		},\r\n" + "		\"FromDate\": \"\",\r\n" + "		\"ToDate\": \"\",\r\n"
					+ "		\"Resource\": [],\r\n" + "		\"FormName\": [],\r\n" + "		\"FormType\": [],\r\n"
					+ "		\"Program\": [],\r\n" + "		\"ModifiedBy\": [],\r\n"
					+ "		\"FirstRowId\": null,\r\n" + "		\"LastRowId\": null\r\n" + "	},\r\n"
					+ "	\"FirstRowId\": null,\r\n" + "	\"LastRowId\": null\r\n" + "}";
			Response response = apiUtils.submitRequest(METHOD_TYPE.POST, getFormsRequest, headers, GetFormsUrl);
			SingleDataResponse sdr = gson.fromJson(response.asString(), SingleDataResponse.class);
			int count = sdr.Data.Count;
			if (count == 1) {
				logInfo("A created form found for - " + formName);
				return true;
			}
			if (count > 1) {
				logInfo("More than one created form found for - " + formName);
				return false;
			} else {
				logInfo("Could not verify form named - " + formName);
				return false;
			}

		} catch (Exception e) {
			logError("Failed to set resource IDs for Categories - " + e.getMessage());
			return false;
		}
	}

	public boolean createUnreleasedFormWithCompliantValues(FormParams fp, FormFieldParamsCompliance ffp) {
		String saveFormDesignRequest = null;
		String fields = "";
		String resourceList;

		try {
			Map<String, String> headers = new HashMap<String, String>();
			headers.put("Content-Type", "application/json");
			headers.put("Accept", "application/json, text/plain, /");
			headers.put("Authorization", "Bearer " + accessToken);
			fields = createFormTemplateforResources(fp.formElements, fp, ffp);

			resourceList = updateResourceList(ffp);

			if(fp.type.equals(DPT_FORM_TYPES.AUDIT)) {

				// For one or first Section
				String sectionName = fp.SectionElements.get(0).Name;
				HashMap<String, String> section = getSectionDetails(fp.FormName, sectionName);

				saveFormDesignRequest = "{\r\n" + "        \"Operation\": \"Insert\",\r\n"
						+ "        \"PartialSuccess\": \"true\",\r\n" + "        \"RequestNo\": \"\",\r\n" + "\r\n"
						+ "   \"Data\": {\r\n" + "        \"Forms\": [\r\n" + "            {\r\n"
						+ "                \"Type\": \""+fp.type+"\",\r\n" + "                \"FormData\": {\r\n"
						+ "                    \"FormDetails\": {\r\n" +  " \"SectionElements\": [{"
						+ "                        \"Fields\": [\r\n"
						+ fields + "" + "                          \r\n" + "                        ],\r\n"
						+"\"Type\": \"None\",\r\n" + 
						"						\"ElementType\": \"Section\",\r\n" + 
						"						\"ShortName\": \""+section.get("shortName")+"\",\r\n" + 
						"						\"Id\": \""+section.get("fieldId")+"\",\r\n" + 
						"						\"Name\": \""+sectionName+"\"\r\n" + 
						"					}]\r\n"  
						+ "                    }\r\n" + "                },\r\n"
						+ "                \"Version\": \"1.0\",\r\n" + "                \"Resources\": [\r\n"
						+ resourceList + "                ],\r\n" + "                \"isPartial\": false,\r\n"
						+ "                \"Id\": \"" + fp.FormId + "\",\r\n" + "                \"Name\": \""
						+ fp.FormName + "\"\r\n" + "            }\r\n" + "        ]\r\n" + "    }\r\n" + "}";

			}
			else {
				saveFormDesignRequest = "{\r\n" + "        \"Operation\": \"Insert\",\r\n"
						+ "        \"PartialSuccess\": \"true\",\r\n" + "        \"RequestNo\": \"\",\r\n" + "\r\n"
						+ "   \"Data\": {\r\n" + "        \"Forms\": [\r\n" + "            {\r\n"
						+ "                \"Type\": \""+fp.type+"\",\r\n" + "                \"FormData\": {\r\n"
						+ "                    \"FormDetails\": {\r\n" + "                        \"Fields\": [\r\n"
						+ fields + "" + "                          \r\n" + "                        ]\r\n"
						+ "                    }\r\n" + "                },\r\n"
						+ "                \"Version\": \"1.0\",\r\n" + "                \"Resources\": [\r\n"
						+ resourceList + "                ],\r\n" + "                \"isPartial\": false,\r\n"
						+ "                \"ActionType\": null,\r\n" + "                \"ResUpdatedBy\": null,\r\n"
						+ "                \"Id\": \"" + fp.FormId + "\",\r\n" + "                \"Name\": \""
						+ fp.FormName + "\"\r\n" + "            }\r\n" + "        ]\r\n" + "    }\r\n" + "}";
			}	

			Response submitResponse = apiUtils.submitRequest(METHOD_TYPE.POST, saveFormDesignRequest, headers,
					DptAddUpdateComplianceUrl);
			SimpleDataResponse submitRes = gson.fromJson(submitResponse.asString(), SimpleDataResponse.class);

			if (submitRes.RequestStatus.equalsIgnoreCase("pending")) {
				logInfo("Added Form Compliance - " + fp.FormName);
				return true;
			} else {
				logInfo("Could not Add Compliance Values - " + fp.FormName);
				return false;
			}

		} catch (Exception e) {
			logError("Failed to add compliance values into form - " + e.getMessage());
			return false;
		}
	}

	public String createFormTemplateforResources(List<FormFieldParams> listOfElements, FormParams fp,
			FormFieldParamsCompliance fpc) {
		String addField = "";

		try {

			for (int i = 0; i < fpc.fieldNames.size(); i++) {
				if (i != (fpc.fieldNames.size() - 1)) {
					addField += Compliance_updateFormElements(fp, fpc, false);
				} else {
					addField += Compliance_updateFormElements(fp, fpc, true);
				}
			}
			return addField;
		} catch (Exception e) {
			logError("Failed to set form element for - " + e.getMessage());
			return addField;
		}
	}

	public String Compliance_updateFormElements(FormParams ffp, FormFieldParamsCompliance fpc, boolean lastField) {
		String addField = null;
		String complianceArray = "";
		String Name = "";
		String fieldType = "";

		int count = 0;

		try {

			for (Map.Entry<String, Compliance> entry : fpc.complianceList.entrySet()) {
				String resourceName = entry.getKey();
				Name = entry.getValue().Name;
				fieldType = entry.getValue().fieldType;

				count++;

				if (count < fpc.complianceList.size()) {

					complianceArray += " {\"Compliance\": {\r\n" + "\"DataType\": \"" + fieldType + "\",\r\n"
							+ " \"Min\": \"" + entry.getValue().Min + "\",\r\n" + "\"Max\": \"" + entry.getValue().Max
							+ "\",\r\n" + "\"Target\": \"" + entry.getValue().Target + "\",\r\n" + "\"UOM\": \""
							+ entry.getValue().UOM + "\",\r\n" + "\"ComplianceValue\": \""
							+ entry.getValue().ComplianceValue + "\"\r\n" + " }, \"IsVisible\": "
							+ entry.getValue().IsVisible + ",\r\n" + "  \"Subject\": {\r\n \"Name\": \"" + resourceName
							+ "\"\r\n" + "  },\r\n" + "  \"IsValid\": " + entry.getValue().IsValid + ",\r\n"
							+ "   \"IsDefault\": " + (entry.getValue().IsDefault.replace("\"", "")) + "},";
				} else {
					complianceArray += " {\"Compliance\": {\r\n" + "\"DataType\": \"" + fieldType + "\",\r\n"
							+ " \"Min\": \"" + entry.getValue().Min + "\",\r\n" + "\"Max\": \"" + entry.getValue().Max
							+ "\",\r\n" + "\"Target\": \"" + entry.getValue().Target + "\",\r\n" + "\"UOM\": \""
							+ entry.getValue().UOM + "\",\r\n" + "\"ComplianceValue\": \""
							+ entry.getValue().ComplianceValue + "\"\r\n" + " }, \"IsVisible\": "
							+ entry.getValue().IsVisible + ",\r\n" + "  \"Subject\": {\r\n \"Name\": \"" + resourceName
							+ "\"\r\n" + "  },\r\n" + "  \"IsValid\": " + entry.getValue().IsValid + ",\r\n"
							+ "   \"IsDefault\": " + entry.getValue().IsDefault + "}";

				}

			}

			String fieldId = getFieldId(ffp, Name);
			logInfo("Field Name = " + Name + "Id = " + fieldId);

			HashMap<String, String> fieldValues = new HashMap<String, String>();
			if(ffp.type.equalsIgnoreCase(DPT_FORM_TYPES.AUDIT))
				fieldValues = getFieldDetails(ffp.FormName, Name, ffp.SectionElements.get(0).Name);
			else
				fieldValues = getFieldDetails(ffp.FormName, Name, null);

			logInfo("Short Name = " + fieldValues.get("shortName") + "Id = " + fieldValues.get("fieldId"));

			addField = " {\r\n" + " \"Type\": \"" + fieldType + "\",\r\n" + "\"ElementType\": \"Field\",\r\n"
					+ "\"Configuration\": [\r\n" + complianceArray + "],\r\n" + "\"ShortName\": \""
					+ fieldValues.get("shortName") + "\",\r\n" + "\"Id\": \"" + fieldId + "\",\r\n" + "\"Name\": \""
					+ Name + "\"\r\n" + "}";

			if (!lastField) {

				addField += "                ,\r\n";
			} else {
				addField += "                \r\n";
			}

			return addField;
		} catch (Exception e) {
			logError("Failed to set form element for - " + e.getMessage());
			return addField;
		}

	}

	public String updateResourceList(FormFieldParamsCompliance fpc) {
		String fieldDets = "", appendFieldDets = "";
		int catCount = 0;
		try {
			for (Map.Entry<String, Compliance> entry : fpc.complianceList.entrySet()) {
				String resourceName = entry.getKey();

				catCount++;

				if (catCount < fpc.complianceList.size()) {

					appendFieldDets += "{ \"Name\": \"" + resourceName + "\"},";
				} else {
					appendFieldDets += "{ \"Name\": \"" + resourceName + "\"}";
				}
			}

			return appendFieldDets;
		} catch (Exception e) {
			logError("Failed to set list of names - " + e.getMessage());
			return fieldDets;

		}
	}

	public String getFieldId(FormParams ffp, String fieldName) {
		String FieldId = null;
		apiUtils = new ApiUtils();
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("Accept", "application/json, text/plain, /");
		headers.put("Authorization", "Bearer " + accessToken);
		Gson gson = new GsonBuilder().create();
		try {
			String getComplianceValuesJson = "{\"Data\": \r\n" + "  {  \"Take\": 100,\r\n" + "    \"Skip\": \"\",\r\n"
					+ "    \"FormNames\": [\"" + ffp.FormName + "\"]\r\n" + "    }\r\n" + "}";

			Response getComplianceResponse = apiUtils.submitRequest(METHOD_TYPE.POST, getComplianceValuesJson, headers,
					DptGetFormComplianceUrl);
			System.out.println(getComplianceResponse.asString());
			SingleDataResponse sdr = gson.fromJson(getComplianceResponse.asString(), SingleDataResponse.class);

			if(ffp.type== DPT_FORM_TYPES.AUDIT) {
				for (int i = 0; i < sdr.Data.Forms.get(0).FormData.FormDetails.SectionElements.size(); i++) {
					String sectionNameProvidedByReq = ffp.SectionElements.get(i).Name; // Section A then Section B
					String sectionNameFromResPayload = sdr.Data.Forms.get(0).FormData.FormDetails.SectionElements.get(i).Name;

					if(sectionNameProvidedByReq.equalsIgnoreCase(sectionNameFromResPayload)) {
						System.out.println(sdr.Data.Forms.get(0).FormData.FormDetails.SectionElements.get(i).Fields.size());
						for (int j = 0; j < sdr.Data.Forms.get(0).FormData.FormDetails.SectionElements.get(i).Fields.size(); j++) {


							System.out.println("name " + sdr.Data.Forms.get(0).FormData.FormDetails.SectionElements.get(i).Fields.get(j).Name);
							if(sdr.Data.Forms.get(0).FormData.FormDetails.SectionElements.get(i).Fields.get(j).Name
									.equalsIgnoreCase(fieldName)) {
								FieldId = sdr.Data.Forms.get(0).FormData.FormDetails.SectionElements.get(i).Fields.get(j).Id;
								logInfo("Field Id= " + FieldId);
								return FieldId;
							}
						}
					}
				}
			}
			else {
				for (int i = 0; i < sdr.Data.Forms.get(0).FormData.FormDetails.Fields.size(); i++) {

					if (sdr.Data.Forms.get(0).FormData.FormDetails.Fields.get(i).Name.equalsIgnoreCase(fieldName)) {
						FieldId = sdr.Data.Forms.get(0).FormData.FormDetails.Fields.get(i).Id;
						logInfo("Field Id= " + FieldId);
						return FieldId;
					}
				}
			}
			return FieldId;
		} catch (Exception e) {
			logInfo("Unable to fetch field If");
			return FieldId;
		}
	}

	public HashMap<String, String> getFieldDetails(String formName, String fieldName, String sectionName) {
		String FieldId = null;
		String shortName = null;
		HashMap<String, String> fieldDetails = new HashMap<String, String>();
		apiUtils = new ApiUtils();
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("Accept", "application/json, text/plain, /");
		headers.put("Authorization", "Bearer " + accessToken);
		Gson gson = new GsonBuilder().create();
		FormDesigner formDesigner = new FormDesigner();
		try {
			String getComplianceValuesJson = "{\"Data\": \r\n" + "  {  \"Take\": 100,\r\n" + "    \"Skip\": \"\",\r\n"
					+ "    \"FormNames\": [\"" + formName + "\"]\r\n" + "    }\r\n" + "}";

			Response getComplianceResponse = apiUtils.submitRequest(METHOD_TYPE.POST, getComplianceValuesJson, headers,
					formDesigner.DptGetFormComplianceUrl);
			SingleDataResponse sdr = gson.fromJson(getComplianceResponse.asString(), SingleDataResponse.class);

			if(sdr.Data.Forms.get(0).Type.equalsIgnoreCase(FORM_TYPES.AUDIT)) {
				for (int i = 0; i < sdr.Data.Forms.get(0).FormData.FormDetails.SectionElements.size(); i++) {
					String sectionNameFromResPayload = sdr.Data.Forms.get(0).FormData.FormDetails.SectionElements.get(i).Name;

					if(sectionName.equalsIgnoreCase(sectionNameFromResPayload)) {
						System.out.println(sdr.Data.Forms.get(0).FormData.FormDetails.SectionElements.get(i).Fields.size());
						for (int j = 0; j < sdr.Data.Forms.get(0).FormData.FormDetails.SectionElements.get(i).Fields.size(); j++) {


							System.out.println("name " + sdr.Data.Forms.get(0).FormData.FormDetails.SectionElements.get(i).Fields.get(j).Name);
							if(sdr.Data.Forms.get(0).FormData.FormDetails.SectionElements.get(i).Fields.get(j).Name
									.equalsIgnoreCase(fieldName)) {

								FieldId = sdr.Data.Forms.get(0).FormData.FormDetails.SectionElements.get(i).Fields.get(j).Id;
								shortName = sdr.Data.Forms.get(0).FormData.FormDetails.SectionElements.get(i).Fields.get(j).ShortName;
								fieldDetails.put("fieldId", FieldId);
								fieldDetails.put("shortName", shortName);
								return fieldDetails;
							}
						}
					}
				}
			}
			else {
				for (int i = 0; i < sdr.Data.Forms.get(0).FormData.FormDetails.Fields.size(); i++) {

					if (sdr.Data.Forms.get(0).FormData.FormDetails.Fields.get(i).Name.equalsIgnoreCase(fieldName)) {
						FieldId = sdr.Data.Forms.get(0).FormData.FormDetails.Fields.get(i).Id;
						shortName = sdr.Data.Forms.get(0).FormData.FormDetails.Fields.get(i).ShortName;
						logInfo("Field Id= " + FieldId);

						fieldDetails.put("fieldId", FieldId);
						fieldDetails.put("shortName", shortName);
						return fieldDetails;
					}

				}
			}
			return fieldDetails;

		} catch (Exception e) {
			logInfo("Unable to fetch field If");
			return fieldDetails;
		}

	}

	public String extractStringUsingRegEx(String pattern, String DependencyRule) {
		String fieldName = "";
		Pattern regPattern = Pattern.compile(pattern);
		Matcher matcher = regPattern.matcher(DependencyRule);

		if (matcher.find()) {
			fieldName = matcher.group(1);

		}
		fieldName = fieldName.replace("(", "");

		System.out.println("fieldName=" + fieldName);

		for (Map.Entry<String, String> entry : shortNames.entrySet()) {
			String Key = entry.getKey();
			if (Key.equals(fieldName)) {
				DependencyRule = DependencyRule.replace(fieldName, entry.getValue());
				break;

			}

		}

		return DependencyRule;

	}

	public String updateSourceCollectionList(List<String> names) {
		String fieldDets = "", appendFieldDets = null;

		try {
			for (int i = 0; i < names.size(); i++) {
				String shortName = "";

				for (Map.Entry<String, String> entry : shortNames.entrySet()) {
					String Key = entry.getKey();
					if (Key.equals(names.get(i))) {
						System.out.println("Entry Value =" + entry.getValue());
						shortName = entry.getValue();
						break;
					}
				}


				if (i != (names.size() - 1)) {
					appendFieldDets = "{\r\n"
							// + " \"Id\": \"" + fieldId.get("fieldId") + "\",\r\n"
							+ "                            \"Name\": \"" + names.get(i) + "\",\r\n"
							+ "                            \"ShortName\": \"" + shortName + "\"\r\n"
							+ "                        },";
				} else {
					appendFieldDets = "{\r\n"
							// + " \"Id\": \"" + fieldId.get("fieldId") + "\",\r\n"
							+ "                            \"Name\": \"" + names.get(i) + "\",\r\n"
							+ "                            \"ShortName\": \"" + shortName + "\"\r\n"
							+ "                        }";
				}

				fieldDets = fieldDets + appendFieldDets;
			}
			return fieldDets;
		} catch (Exception e) {
			logError("Failed to set list of names - " + e.getMessage());
			return fieldDets;
		}
	}

	public String verifylocationCategory(String locationId, String locationCategory) {
		String locationCatId = "";
		apiUtils = new ApiUtils();
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("Accept", "application/json, text/plain, /");
		headers.put("Authorization", "Bearer " + accessToken);
		Gson gson = new GsonBuilder().create();
		try {
			String getLocationCategoriesJson = "{\"Type\":\""+locationId+"\"}";

			Response getComplianceResponse = apiUtils.submitRequest(METHOD_TYPE.POST, getLocationCategoriesJson,
					headers, GetLocationCategories);
			MultipleDataResponse sdr = gson.fromJson(getComplianceResponse.asString(), MultipleDataResponse.class);
			System.out.println(" sdr.Data.size()" + sdr.Data.size());
			for (int i = 0; i < sdr.Data.size(); i++) {

				if (sdr.Data.get(i).Name.equalsIgnoreCase(locationCategory)) {

					logInfo("location Category is already present= " + sdr.Data.get(i).Name);
					locationCatId = sdr.Data.get(i).Id;
					break;
				}

			}
			return locationCatId;

		} catch (Exception e) {
			logInfo("Couldnt verify location Categoty");
			return locationCatId;
		}
	}

	public String verifylocationInstance(String locationId, String locationCategory, String locationInstance) {
		String locationInstanceId = "";
		apiUtils = new ApiUtils();
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("Accept", "application/json, text/plain, /");
		headers.put("Authorization", "Bearer " + accessToken);
		Gson gson = new GsonBuilder().create();
		try {
			String getLocInstanceValuesJson = "{\r\n" + "    \"Data\": {\r\n" + "        \"IsParent\": true,\r\n"
					+ "        \"Type\": \"" + locationId + "\",\r\n" + "        \"Node\": \"" + locationCategory
					+ "\"\r\n" + "    }\r\n" + "}";

			Response getComplianceResponse = apiUtils.submitRequest(METHOD_TYPE.POST, getLocInstanceValuesJson, headers,
					manageResourceCategories);
			MultipleDataResponse sdr = gson.fromJson(getComplianceResponse.asString(), MultipleDataResponse.class);
			for (int i = 0; i < sdr.Data.size(); i++) {

				if (sdr.Data.get(i).Name.equalsIgnoreCase(locationInstance)) {

					logInfo("location instance is already present= " + sdr.Data.get(i).Name);
					locationInstanceId = sdr.Data.get(i).Id;
					break;
				}

			}
			return locationInstanceId;

		} catch (Exception e) {
			logInfo("Couldnt verify location Instance");
			return locationInstanceId;
		}
	}

	/** This method is used to get details of a Section in a form
	 * @author hingorani_a
	 * @param formName The name of the form
	 * @param sectionName The name of the section
	 * @return HashMap This returns a key value pair with 'fieldId' and 'shortName' for section
	 */	
	public HashMap<String, String> getSectionDetails(String formName, String sectionName) {
		String FieldId = null;
		String shortName = null;
		HashMap<String, String> fieldDetails = new HashMap<String, String>();
		apiUtils = new ApiUtils();
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("Accept", "application/json, text/plain, /");
		headers.put("Authorization", "Bearer " + accessToken);
		Gson gson = new GsonBuilder().create();
		FormDesigner formDesigner = new FormDesigner();
		try {
			String getComplianceValuesJson = "{\"Data\": \r\n" + "  {  \"Take\": 100,\r\n" + "    \"Skip\": \"\",\r\n"
					+ "    \"FormNames\": [\"" + formName + "\"]\r\n" + "    }\r\n" + "}";

			Response getComplianceResponse = apiUtils.submitRequest(METHOD_TYPE.POST, getComplianceValuesJson, headers,
					formDesigner.DptGetFormComplianceUrl);
			SingleDataResponse sdr = gson.fromJson(getComplianceResponse.asString(), SingleDataResponse.class);

			for (int i = 0; i < sdr.Data.Forms.get(0).FormData.FormDetails.SectionElements.size(); i++) {
				String sectionNameFromResPayload = sdr.Data.Forms.get(0).FormData.FormDetails.SectionElements.get(i).Name;

				if(sectionName.equalsIgnoreCase(sectionNameFromResPayload)) {

					FieldId = sdr.Data.Forms.get(0).FormData.FormDetails.SectionElements.get(i).Id;
					shortName = sdr.Data.Forms.get(0).FormData.FormDetails.SectionElements.get(i).ShortName;
					fieldDetails.put("fieldId", FieldId);
					fieldDetails.put("shortName", shortName);
					return fieldDetails;
				}
			}
			return fieldDetails;

		} catch (Exception e) {
			logInfo("Unable to fetch field If");
			return fieldDetails;
		}

	}

	// For other APIs
	public String verifyIsFormCreated(String url, Map<String, String> headers, String formName, String resourceType) {
		String getFormsRequest = null;
		String Data = "";
		String resourceTypeID = "";

		apiUtils = new ApiUtils();

		Gson gson = new GsonBuilder().create();

		try {

			switch(resourceType) {
			case RESOURCE_TYPES.CUSTOMERS:
				resourceTypeID = otherCustomersCatID;
				break;
			case RESOURCE_TYPES.EQUIPMENT:
				resourceTypeID = otherEquipmentCatID;
				break;
			case RESOURCE_TYPES.ITEMS:
				resourceTypeID = otherItemsCatID;
				break;
			case RESOURCE_TYPES.SUPPLIERS:
				resourceTypeID = otherSuppliersCatID;
				break;
			case RESOURCE_TYPES.PLANT_RESOURCE_TYPE:
				resourceTypeID = otherPlantCatID;
				break;
			default:
				logInfo("Category Invalid");
				break;
			}

			getFormsRequest = "{\r\n" + 
					"    \"filter\": {\r\n" + 
					"        \"logic\": \"and\",\r\n" + 
					"        \"filters\": [\r\n" + 
					"            {\r\n" + 
					"                \"field\": \"Name\",\r\n" + 
					"                \"operator\": \"contains\",\r\n" + 
					"                \"value\": \""+formName+"\"\r\n" + 
					"            }\r\n" + 
					"        ]\r\n" + 
					"    },\r\n" + 
					"    \"Parameters\": {\r\n" + 
					"        \"Node\": {\r\n" + 
					"            \"Type\": \""+resourceTypeID+"\",\r\n" + 
					"            \"IsParent\": true\r\n" + 
					"        }\r\n" + 
					"    }\r\n" + 
					"}";
			Response response = apiUtils.submitRequest(METHOD_TYPE.POST, getFormsRequest, headers, url);
			SingleDataResponse sdr = gson.fromJson(response.asString(), SingleDataResponse.class);
			int count = sdr.Data.Count;
			if (count > 0) {
				logInfo("A created form found for - " + formName);
				Data = sdr.Data.toString();

			} else {
				logInfo("Could not found form named - " + formName);
			}

			return Data;

		} catch (Exception e) {
			logError("Failed to get form Data - " + e.getMessage());
			return Data;
		}
	}

	public HashMap<String, String> getResourceName(String url, Map<String, String> headers, String formName) {
		String getFormsRequest = null;

		HashMap<String, String> resourceMap = new HashMap<String, String>();

		apiUtils = new ApiUtils();

		Gson gson = new GsonBuilder().create();

		try {

			getFormsRequest = "  {\r\n" + "    \"Take\": 100,\r\n" + "    \"Skip\": \"\",\r\n" + "    \"Filter\": {\r\n"
					+ "      \"filters\": [\r\n" + "        {\r\n" + "         \"field\": \"FormName\",\r\n"
					+ "         \"operator\": \"equals\",\r\n" + "          \"value\": \"" + formName + "\"\r\n"
					+ "        }\r\n" + "       ]\r\n" + "    }\r\n" + "  }\r\n" + "";
			Response response = apiUtils.submitRequest(METHOD_TYPE.POST, getFormsRequest, headers, url);
			MultipleDataResponse sdr = gson.fromJson(response.asString(), MultipleDataResponse.class);
			int count = sdr.Data.get(0).Resources.size();
			if (count > 0) {
				logInfo("A created form found for - " + formName);

				for (int i = 0; i < count; i++) {
					resourceMap.put(sdr.Data.get(0).Resources.get(i).Id, sdr.Data.get(0).Resources.get(i).Name);
				}

			} else {
				logInfo("Could not found form named - " + formName);
			}

			return resourceMap;

		} catch (Exception e) {
			logError("Failed to get form Data - " + e.getMessage());
			return resourceMap;
		}
	}

	public String getFormId_AlreadyCreated(String url, Map<String, String> headers, String formName) {
		String getFormsRequest = null;
		String formId = "";

		apiUtils = new ApiUtils();

		Gson gson = new GsonBuilder().create();

		try {

			getFormsRequest = "  {\r\n" + "    \"Take\": 100,\r\n" + "    \"Skip\": \"\",\r\n" + "    \"Filter\": {\r\n"
					+ "      \"filters\": [\r\n" + "        {\r\n" + "         \"field\": \"FormName\",\r\n"
					+ "         \"operator\": \"equals\",\r\n" + "          \"value\": \"" + formName + "\"\r\n"
					+ "        }\r\n" + "       ]\r\n" + "    }\r\n" + "  }\r\n" + "";
			Response response = apiUtils.submitRequest(METHOD_TYPE.POST, getFormsRequest, headers, url);
			MultipleDataResponse sdr = gson.fromJson(response.asString(), MultipleDataResponse.class);
			int count = sdr.Data.size();
			if (count > 0) {
				logInfo("A created form found for - " + formName);
				formId = sdr.Data.get(0).FormId;

			} else {
				logInfo("Could not found form named - " + formName);
			}

			return formId;

		} catch (Exception e) {
			logError("Failed to get form Data - " + e.getMessage());
			return formId;
		}
	}

	public HashMap<String, String> getFormLocation(String url, Map<String, String> headers, String formId) {
		String getFormLocRequest = null;
		HashMap<String, String> locMap = new HashMap<String, String>();
		apiUtils = new ApiUtils();

		Gson gson = new GsonBuilder().create();

		try {

			getFormLocRequest = "{\"Id\":\"" + formId + "\"}";
			Response response = apiUtils.submitRequest(METHOD_TYPE.POST, getFormLocRequest, headers,
					url);
			SingleDataResponse sdr = gson.fromJson(response.asString(), SingleDataResponse.class);
			int count = sdr.Data.Locations.size();
			if (count > 0) {

				for (int i = 0; i < sdr.Data.Locations.size(); i++) {
					locMap.put(sdr.Data.Locations.get(i).Id, sdr.Data.Locations.get(i).Name);
				}
			}

			return locMap;

		} catch (Exception e) {
			logError("Failed to get location for the form provided in the request Data - " + e.getMessage());
			return locMap;
		}
	}

	public HashMap<String, String> getFormResourceForLocationMapped(String url, Map<String, String> headers, 
			String formId, String locId) {
		String getFormResForLocRequest = null;
		HashMap<String, String> ResMap = new HashMap<String, String>();
		apiUtils = new ApiUtils();

		Gson gson = new GsonBuilder().create();

		try {

			getFormResForLocRequest = "{\r\n" + "    \"FormId\": \"" + formId + "\",\r\n" + "    \"LocationId\": \""
					+ locId + "\"\r\n" + "}";
			Response response = apiUtils.submitRequest(METHOD_TYPE.POST, getFormResForLocRequest, headers,
					url);
			MultipleDataResponse sdr = gson.fromJson(response.asString(), MultipleDataResponse.class);
			int count = sdr.Data.size();
			if (count > 0) {

				for (int i = 0; i < sdr.Data.size(); i++) {
					ResMap.put(sdr.Data.get(i).Id, sdr.Data.get(i).Name);
				}

			}

			return ResMap;

		} catch (Exception e) {
			logError("Failed to get form Data - " + e.getMessage());
			return ResMap;
		}
	}


}
