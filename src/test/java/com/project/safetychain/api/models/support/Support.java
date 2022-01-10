package com.project.safetychain.api.models.support;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.project.safetychain.api.models.Auth;
import com.project.utilities.ApiUtils;

public class Support extends Auth {
	ApiUtils apiUtils = new ApiUtils();

	public static String updateIdList(List<String> ids, String idType) {
		String fieldDets = "", appendFieldDets = null;

		try {

			if (idType.equals("Id")) {
				for (int i = 0; i < ids.size(); i++) {

					if (i != (ids.size() - 1)) {
						appendFieldDets = "    {\r\n" + "      \"Id\": \"" + ids.get(i) + "\"\r\n" + "    },\r\n";
					} else {
						appendFieldDets = "    {\r\n" + "      \"Id\": \"" + ids.get(i) + "\"\r\n" + "    }\r\n";
					}

					fieldDets = fieldDets + appendFieldDets;
				}
			} else if (idType.equals("ID")) {
				for (int i = 0; i < ids.size(); i++) {

					if (i != (ids.size() - 1)) {
						appendFieldDets = "    {\r\n" + "      \"ID\": \"" + ids.get(i) + "\"\r\n" + "    },\r\n";
					} else {
						appendFieldDets = "    {\r\n" + "      \"ID\": \"" + ids.get(i) + "\"\r\n" + "    }\r\n";
					}

					fieldDets = fieldDets + appendFieldDets;
				}
			}

			return fieldDets;
		} catch (Exception e) {
			logError("Failed to set list of IDs - " + e.getMessage());
			return fieldDets;
		}
	}

	public static String updateNameList(List<String> names) {
		String fieldDets = "", appendFieldDets = null;

		try {
			for (int i = 0; i < names.size(); i++) {

				if (i != (names.size() - 1)) {
					appendFieldDets = "      {\r\n" + "        \"Name\": \"" + names.get(i) + "\"\r\n" + "      },\r\n";
				} else {
					appendFieldDets = "      {\r\n" + "        \"Name\": \"" + names.get(i) + "\"\r\n" + "      }\r\n";
				}

				fieldDets = fieldDets + appendFieldDets;
			}
			return fieldDets;
		} catch (Exception e) {
			logError("Failed to set list of names - " + e.getMessage());
			return fieldDets;
		}
	}

	public static String updateNameListWithQuestionWeight(List<String> names) {
		String fieldDets = "", appendFieldDets = null;

		try {
			for (int i = 0; i < names.size(); i++) {
				List<String> NamesWeight;
				if (names.get(i).contains(",")) {
					NamesWeight = Arrays.asList(names.get(i).split(","));

					if (i != (names.size() - 1)) {
						appendFieldDets = "      {\r\n" + "        \"Name\": \"" + NamesWeight.get(0) + "\",\r\n"
								+ "                            \"Value\": \"" + NamesWeight.get(1) + "\"\r\n"
								+ "      },\r\n";
					} else {
						appendFieldDets = "      {\r\n" + "        \"Name\": \"" + NamesWeight.get(0) + "\",\r\n"
								+ "                            \"Value\": \"" + NamesWeight.get(1) + "\",\r\n"
								+ "      },\r\n";
					}
				} else {
					if (i != (names.size() - 1)) {
						appendFieldDets = "      {\r\n" + "        \"Name\": \"" + names.get(i) + "\"\r\n"
								+ "      },\r\n";
					} else {
						appendFieldDets = "      {\r\n" + "        \"Name\": \"" + names.get(i) + "\"\r\n"
								+ "      }\r\n";
					}

				}

				fieldDets = fieldDets + appendFieldDets;
			}
			return fieldDets;
		} catch (Exception e) {
			logError("Failed to set list of names - " + e.getMessage());
			return fieldDets;
		}
	}

	public static String updateResourceList(HashMap<String, List<String>> names, String resouceType) {
		String fieldDets = "", appendFieldDets = "";
		int catCount = 0;
		int resCount = 0;

		try {
			for (Map.Entry<String, List<String>> entry : names.entrySet()) {
				String categoryName = entry.getKey();
				List<String> resourceNames = entry.getValue();

				catCount++;
				for (String resName : resourceNames) {
					resCount++;
					if (catCount == names.entrySet().size() && resCount == resourceNames.size()) {

						appendFieldDets += "{\r\n" + "                    \"Name\": \"" + resouceType + " > "
								+ categoryName + " > " + resName + "\"\r\n" + "                }\r\n";
					} else {
						appendFieldDets += "{\r\n" + "                    \"Name\": \"" + resouceType + " > "
								+ categoryName + " > " + resName + "\"\r\n" + "                },\r\n";
					}
				}

				resCount = 0;
				fieldDets = fieldDets + appendFieldDets;
			}
			return fieldDets;
		} catch (Exception e) {
			logError("Failed to set list of names - " + e.getMessage());
			return fieldDets;
		}
	}

	public static String updateUserList(List<String> userIds) {
		String fieldDets = "", appendFieldDets = "";

		try {

			for (int i = 0; i < userIds.size(); i++) {

				if (i < userIds.size() - 1) {

					appendFieldDets += "{\"Id\":\"" + userIds.get(i) + "\"},";
				} else {
					appendFieldDets += "{\"Id\":\"" + userIds.get(i) + "\"}";
				}
			}

			fieldDets = fieldDets + appendFieldDets;
			logInfo("User List " + fieldDets);
			return fieldDets;
		} catch (Exception e) {
			logError("Failed to set list of names - " + e.getMessage());
			return fieldDets;
		}
	}

	public static String updateIdentifierListForSelectOne(List<String> userIds) {
		String fieldDets = "", appendFieldDets = "";

		try {

			for (int i = 0; i < userIds.size(); i++) {

				if (i < userIds.size() - 1) {

					appendFieldDets += " \"" + userIds.get(i) + "\",";
				} else {
					appendFieldDets += " \"" + userIds.get(i) + "\"";
				}
			}

			fieldDets = fieldDets + appendFieldDets;
			logInfo("User List " + fieldDets);
			return fieldDets;
		} catch (Exception e) {
			logError("Failed to set list of names - " + e.getMessage());
			return fieldDets;
		}
	}

	public String updateRepeatIdsListForSelectOne(String repeat) {
		String fieldDets = "", appendrepeatIds = "";

		try {

			int repeatNo = Integer.parseInt(repeat);

			for (int i = 0; i < repeatNo; i++) {

				if (i < repeatNo - 1) {

					appendrepeatIds += " \"" + apiUtils.getUUID() + "\",";
				} else {
					appendrepeatIds += " \"" + apiUtils.getUUID() + "\"";
				}
			}

			fieldDets = fieldDets + appendrepeatIds;
			logInfo("Repeat List " + fieldDets);
			return fieldDets;
		} catch (Exception e) {
			logError("Failed to set list of repeatIds - " + e.getMessage());
			return fieldDets;
		}
	}

	public static String updateUserFieldValues(HashMap<String, String> fields) {
		String fieldDets = "", appendFieldDets = "";

		try {

			int count = 1;

			for (Map.Entry<String, String> entry : fields.entrySet()) {

				String fieldName = entry.getKey();
				String fieldId = entry.getValue();

				if (count < fields.size()) {
					appendFieldDets += "{\r\n" + "\"Id\": \"" + fieldId + "\",\r\n" + "\"Name\": \"" + fieldName
							+ "\"\r\n" + "},";
					count++;
				} else {
					appendFieldDets += "{\r\n" + "\"Id\": \"" + fieldId + "\",\r\n" + "\"Name\": \"" + fieldName
							+ "\"\r\n" + "}";

				}

			}

			fieldDets = fieldDets + appendFieldDets;
			logInfo("User Field List " + fieldDets);
			return fieldDets;
		} catch (Exception e) {
			logError("Failed to set list of names - " + e.getMessage());
			return fieldDets;
		}
	}

	//	public static String updateAsArrayList(List<String> list) {
	//		String fieldDets = "", appendFieldDets = null;
	//		
	//		try {
	//			for(int i = 0; i < list.size(); i++) {
	//				
	//				 if(i==0) {
	//						appendFieldDets = "[\""+list.get(i)+"\",\r\n";
	//				}
	//				else if(i != (list.size()-1)) {
	//					appendFieldDets = "\""+list.get(i)+"\",\r\n";
	//				}
	//				else {
	//					appendFieldDets = "\""+list.get(i)+"\"],\r\n";
	//				}
	//				
	//				fieldDets= fieldDets+appendFieldDets;
	//			}
	//			return fieldDets;
	//		} 
	//		catch (Exception e) {
	//			logError("Failed to set list as array - " + e.getMessage());
	//			return fieldDets;
	//		}
	//	}

	public static class RESOURCE_TYPES {
		public static final String CUSTOMERS = "Customers";
		public static final String EQUIPMENT = "Equipment";
		public static final String ITEMS = "Items";
		public static final String SUPPLIERS = "Suppliers";
		public static final String PLANT_RESOURCE_TYPE = "PlantResourceType";
		public static final String LOCATION = "Locations";
	}

	public static class FormFieldParams {
		public String Name;
		public String HasCompliance = "false";
		public String level = "form";
		public String ElementType = "Field";
		public String fieldHasCompliance = "true";
		public String identifierId;
		public String InputMask = "";
		public List<String> IdentifierOption;
		public String IsFilterable = "true";
		public String IsRequired = "true";
		public String AllowComments = "false";
		public String AllowAttachments = "false";
		public String ShowOnCOA = "true";
		public String ShowHint = "false";
		public String ShowMinMax = "false";
		public String ShowTarget = "false";
		public String FailsForm = "true";
		public String AllowCorrection = "false";
		public String ShowIsResolved = "false";
		public List<String> PredefinedCorrections;
		public List<String> SelectOptions;
		public String IsIntegrated = "false";
		public String RepeatField = "false";
		public String isNonRepeatable = "false";
		public String defaultMin = "null";
		public String defaultMax = "null";
		public String defaultComplianceValue = "null";
		public String isDefaultAll = "true";
		public String isDefaultAllActionPerformed = "true";
		public String isResourceAll = "true";
		public String isResourceAllActionPerformed = "false";
		public String DependencyRule = "null";
		public String ExpressionRule = "null";
		public String ExpressionfieldType="null";
		public String DPTElementType = "0";
		public String DPTFieldType; // Set using DPT_FIELD_TYPES
		public Element_Types GroupElementType;
		public String ControlType = "0";
		public String Repeat = "0";
		// public String RepeatIds = "0";

		public String Hint = "null";
		public String InputType = "SelectOne";

		// AGGREGATE Functions
		public String SelectedFunction;
		public String SelectedSource;
		public String SelectedFunctionParameters;
		public String AGG_MIN;
		public String AGG_MAX;
		public List<String> SelectedSourceCollection;

		// Form Submission

		public String Value = "null";

		// Questinare and Audit Form QuestionWeight

		public String QuestionWeight = "0";
		public String QuestionValue = "null";
		public boolean ShowFieldValidation = false;


	}

	public static class FormParams {
		public String FormId;
		public String type;
		public String FormName;
		public String ResourceType;
		public String ResourceCategoryNm;
		public String ResourceInstanceNm;
		public String LocationInstanceNm;
		public List<FormFieldParams> formElements;
		public List<Element_Types> SectionElements;
		public HashMap<String, List<String>> CustomerResources;
		public HashMap<String, List<String>> ItemsResources;
		public HashMap<String, List<String>> EquipmentResources;
		public HashMap<String, List<String>> SuppliersResources;

	}

	public static class FORM_TYPES {
		public static final String CHECK = "Check";
		public static final String QUESTIONNAIRE = "Questionnaire";
		public static final String AUDIT = "Audit";
	}

	public static class DPT_FORM_TYPES {
		public static final String CHECK = "0";
		public static final String QUESTIONNAIRE = "2";
		public static final String AUDIT = "1";
	}

	public static class FIELD_TYPES {
		public static final String NOTE = "Note";
		public static final String RELATED_DOCS = "RelatedDocs";
		public static final String FREE_TEXT = "FreeText";
		public static final String PARAGRAPH_TEXT = "Paragraph";
		public static final String SELECT_ONE = "SelectOne";
		public static final String SELECT_MULTIPLE = "SelectMultiple";
		public static final String NUMERIC = "Numeric";
		public static final String DATE = "Date";
		public static final String TIME = "Time";
		public static final String DATE_TIME = "DateTime";
		public static final String SINGLE_LINE_TEXT = "SingleLineText";
		public static final String AGGREGATE = "Aggregate";
		public static final String IDENTIFIER = "Identifier";

	}

	public static class Element_Types {
		public String ElementType = "null";
		public String Name = "null";
		public String ShowHint = "false";
		public String Hint = "null";
		public String Repeat = "null";
		public String RepeatIds = "null";
		public String DependencyRule = "null";
		public String ExpressionRule = "null";

		public List<FormFieldParams> formFieldParams;
		public List<Element_Types> FieldGroupParams;

	}

	public static class DPT_FIELD_TYPES {
		public static final String NOTE = "Note";
		public static final String RELATED_DOCS = "RelatedDocs";
		public static final String FREE_TEXT = "5";
		public static final String PARAGRAPH_TEXT = "6";
		public static final String SELECT_ONE = "7";
		public static final String SELECT_MULTIPLE = "8";
		public static final String NUMERIC = "9";
		public static final String DATE = "10";
		public static final String TIME = "11";
		public static final String DATE_TIME = "12";
		public static final String SINGLE_LINE_TEXT = "4";
		public static final String AGGREGATE = "3";
		public static final String IDENTIFIER = "15";
		public static final String FIELD_GROUP = "1";
		public static final String SECTION = "2";

	}

	public static class IDENTIFIER_TYPES {
		public static final String SELECT_ONE = "SelectOne";
		public static final String FREE_TEXT = "FreeText";
	}

	public static class AGGREGATE_FUNC_TYPES {
		public static final String SUM = "Sum";
		public static final String AVERAGE = "Average";
		public static final String PASS_PERCENT = "% Pass";
		public static final String FAIL_PERCENT = "% Fail";
		public static final String COUNT_PASS = "Count Pass";
		public static final String COUNT_FAIL = "Count Fail";
		public static final String STANDARD_DEVIATION = "Standard Deviation";
		public static final String MIN_VAL = "Min Value";
		public static final String MAX_VAL = "Max Value";
		public static final String MEDIAN = "Median";
		public static final String RANGE = "Range";
		public static final String COUNT_RANGE = "Count Range";
		public static final String SUM_RANGE = "Sum Range";

	}

	//=========Expression Rule Operations ================================

	//	public static class ARITHMATIC_OPERATIONS {
	//		public static final String CHECK = "Check";
	//		public static final String QUESTIONNAIRE = "Questionnaire";
	//		public static final String AUDIT = "Audit";
	//	}

	public static class RESOURCE_FIELDS {
		public static final String NAME = "SCSName";
		public static final String STATUS = "SCSStatus";
		public static final String LAST_MODIFIED = "SCSLastModified";
		public static final String MODIFIED_BY = "SCSModifiedBy";

	}

	public static class TaskParams {
		public String FormId;
		public String type;
		public String FormName;
		public String ResourceInstanceNm;
		public String LocationInstanceNm;
		public String LocationInstanceId;
		public String ResourceInstanceId;
		public String WorkGroupName;
		public String TaskName;
		public String DueBy;
		public String UserName;
		// Scheduler
		public String Occurrence;
		public RemoveTaskAfter removeTaskAfter;
		public String ExpirationIntervalType;
		public String ExpirationIntervalValue;
		public String AssignTimeBeforeTaskIsDueMonths;
		public String AssignTimeBeforeTaskIsDueWeeks;
		public String AssignTimeBeforeTaskIsDueDays;
		public String AssignTimeBeforeTaskIsDueHours;
		public String AssignTimeBeforeTaskIsDueMinutes;
		public String Timezone;
		public List<String> DaysOfWeek;
		public String DayOfMonthTaskOccures;
		public String IntervalStartDate;
		public String IntervalEndDate;
		public String IntervalStartTime;
		public String IntervalEndTime;
		public IntervalUnit intervalUnit = null;
		public String Interval;
		// public String Occurrence;
		public String Repetition;
		public String FirstDueDate;
		public String WorkGroupID = null;


	}

	public static class TaskOccurence {
		public static final String ONETIMEONLY = "OneTimeOnly";
		public static final String INTERVAL = "Interval";
		public static final String DAILY = "Daily";
		public static final String WEEKLY = "Weekly";
		public static final String Monthly = "Monthly";

	}

	public static class IntervalUnit {
		public static final String MINUTE = "Minute";
		public static final String HOUR = "Hour";

	}

	public static class RemoveTaskAfter {
		public static final String NEVER = "Never";
		public static final String DAYS = "Days";
		public static final String WEEKS = "Weeks";
		public static final String MONTHS = "Months";
		public static final String YEARS = "Years";

	}

	/*******************************
	 * Compliance Details
	 ****************************/

	public static class FormFieldParamsCompliance {
		public String Type;
		public String ElementType;
		public String ShortName;
		public String Name;
		public HashMap<String, Compliance> complianceList;
		public List<String> fieldNames;

	}

	public static class Compliance {

		public String DataType = "null";
		public String Min = "null";
		public String Max = "null";
		public String Target = "null";
		public String UOM = "null";
		public String ComplianceValue = "null";
		public String Name = "null";
		public String fieldType = "null";

		// public Compliance Compliance;
		public String IsVisible = "true";
		public String Subject;
		public String IsValid = "false";
		public String IsDefault = "false";
	}

	// ****************Chart Details****************

	public static class ChartBuilder {
		public String IsEnable = "true";
		public String Name = "null";
		public String chartLinkingName = "null";
		public String formId;
		public String formName;
		public List<String> chartConfList;
		public String chartTemplateType;
		public DefaultChart defaultChart;

		public List<ChartResources> chartResourceList;
		public List<String> ChartFieldsList;

		public String locationId;

		public String IdentifierFilterName1 = "", IdentifierFilterName2 = "";

		public String IdentifierFilterValue1 = "", IdentifierFilterValue2 = "";


	}

	public static class Chart_Conf_Rules {
		public String Chart_Rule;
		public String DisplayName = "null";
		public String isRuleEnable = "true";
	}

	public static class DefaultChart {
		public String Mean = "null";
		public String Variation = "null";
		public String Comment = "null";
		public String SampleSize = "null";
		public String IsDefault = "false";
		// public String Comment = "true";
	}

	// Link chart with Form

	public static class ChartResources {
		public String ResourceName;
		public String ResourceId;
		public String IsDefault = "false";
		public String IsEnabled = "true";
		public String Mean = "null";
		public String Variation = "null";
		public String SampleSize = "null";
		public String Modified = "null";
		public String isRowDisabled = "false";
		public String IsDefault_scsui = "true";
		public String isRowSelected = "true";
		public String Comment = "null";
	}

	public static class ChartFields {
		public String fieldName;
		public String Value = "false";

	}

	public static class Chart_Template_TYPES {
		public static final String XiMr = "XiMr";
		public static final String NpChart = "NpChart";
		public static final String SumChart = "SumChart";
		public static final String XBarR = "XBarR";
		public static final String PChart = "PChart";
		public static final String UChart = "UChart";
		public static final String XBarS = "XBarS";
		public static final String AverageChart = "AverageChart";
		public static final String CChart = "CChart";
	}

	public static class Chart_Rules {
		public static final String Rule_1 = "PrimaryChartRule1";
		public static final String Rule_2 = "PrimaryChartRule2";
		public static final String Rule_3 = "PrimaryChartRule3";
		public static final String Rule_4 = "PrimaryChartRule4";
		public static final String Rule_5 = "PrimaryChartRule5";
		public static final String Rule_6 = "PrimaryChartRule6";
		public static final String Rule_7 = "PrimaryChartRule7";
		public static final String Rule_8 = "PrimaryChartRule8";
		public static final String Rule_9 = "PrimaryChartRule9";

		public static final String Sec_Rule_1 = "SecondaryChartRule1";
		public static final String Sec_Rule_4 = "SecondaryChartRule4";
		public static final String Sec_Rule_5 = "SecondaryChartRule5";
		public static final String Sec_Rule_8 = "SecondaryChartRule8";

	}

	/*******************************
	 * User Details
	 ****************************/
	public static class USER_INFO {
		public static final String USERNAME = "User Name";
		public static final String EMPLOYEE_ID = "Employee ID";
		public static final String FIRST_NAME = "First Name";
		public static final String LAST_NAME = "Last Name";
		public static final String EMAIL = "Email";
		public static final String PHONE = "Phone";
		public static final String TIMEZONE = "Timezone";
		public static final String LOCATIONS = "Location(s)";
		public static final String DEFAULT_LOCATION = "Default Location";
		public static final String ROLES = "Role(s)";
		public static final String WORKGROUPS = "Work Group(s)";
		public static final String PIN = "Pin";
	}

	public static class UserParams {
		public String Username;
		public String ClearPassword;
		public String FirstName;
		public String LastName;
		public String Email;
		public String TimeZone;
		public String Pin;
		public String EmployeeId;
		public String Phone;
		public String DefaultLocation;
		public String LocationCat;
		public List<String> Roles;
		public List<String> LocationNmIds;
		// public List<String> LocationIds;
		public List<String> WorkGroupNames;
		// public String Phone;
	}

	public static class DeviceParams {
		public String DeviceName;
		public boolean IsEnable;
		public String DeviceType;
		public String BrandModel;
		public String BaudRate;
		public String StopBits;
		public String PrintCommand;
		public String DataBits;
		public String Parity;
		public String HandShake;
		public String UserDetails;
	}


}
