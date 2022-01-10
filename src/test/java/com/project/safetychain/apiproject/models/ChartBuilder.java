package com.project.safetychain.apiproject.models;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.project.safetychain.apiproject.models.Auth;
import com.project.utilities.ApiUtils;
import com.project.utilities.ApiUtils.METHOD_TYPE;

import io.restassured.response.Response;

public class ChartBuilder extends Auth{

	public String AddChartUrl = baseURI + ChartBuilderURLs.SAVE_CHART_URL;
	public String getChartListUrl = baseURI + ChartBuilderURLs.GET_ALL_CHARTS_URL;

	/**
	 * This method is used to create chart template(Admin Tools -> Chart Builder) with provided details
	 * @author pashine_a
	 * @param chartParams (Chart template configuration details)
	 * @return boolean. It will return boolean result true if new chart template has been added
	 */
	public boolean createChart(ChartParams chartParams) {
		try {

			String createdChartID = null;

			ApiUtils apiUtils = new ApiUtils();
			Gson gson = new GsonBuilder().create();

			Map<String, String> headers = new HashMap<String, String>();
			headers.put("Content-Type", "application/json");
			headers.put("Accept", "application/json, text/plain, /");
			headers.put("Authorization", "Bearer " + accessToken);

			String saveChartJSON = getsaveChart(chartParams.chartType, chartParams.chartName, chartParams.chartRules, chartParams.chartEnable);

			Response createChartResponse = apiUtils.submitRequest(METHOD_TYPE.POST, saveChartJSON, headers,
					AddChartUrl);

			SimpleDataResponse response = gson.fromJson(createChartResponse.asString(),
					SimpleDataResponse.class);

			createdChartID = response.Data;

			if(!response.Status) {
				logError("Failed to add chart - "+chartParams.chartName);
				return false;
			}
			if(createdChartID==null) {
				logError("Not got to chart ID - "+chartParams.chartName);
				return false;
			}

			logInfo("Added chart - '"+chartParams.chartName+"' of type -> "+chartParams.chartType);
			return true;

		} catch (Exception e) {
			logError("Failed to create chart - '"+chartParams.chartName+" ' - "+ e.getMessage());
			return false;
		}
	}


	/**
	 * This method is used to get payload for 'SaveChart' API for requested chart type
	 * @author pashine_a
	 * @param chartType (Chart template)
	 * @param chartName (Chart name)
	 * @param rules (Rule list)
	 * @return String. It will return JSON payload as per provided chart template required for 'SaveChart' API
	 */
	public String getsaveChart(String chartType, String chartName, List<String> rules, boolean chartEnable) {
		String primaryGroupName = null, secondaryGroupName = null;
		String chartTypeID = null;

		String requestPayload = null;
		switch(chartType) {
		case ChartTypes.XI_MR:
			secondaryGroupName = "I Chart Run Rules";
			chartTypeID = ChartTypeIDS.XI_MR;
			break;
		case ChartTypes.XBAR_S:
			secondaryGroupName = "S Chart Run Rules";
			chartTypeID = ChartTypeIDS.XBAR_S;
			break;
		case ChartTypes.XBAR_R:
			secondaryGroupName = "R Chart Run Rules";
			chartTypeID = ChartTypeIDS.XBAR_R;
			break;

		case ChartTypes.NP_CHART:
			chartTypeID = ChartTypeIDS.NP_CHART;
			break;
		case ChartTypes.U_CHART:
			chartTypeID = ChartTypeIDS.U_CHART;
			break;
		case ChartTypes.P_CHART:
			chartTypeID = ChartTypeIDS.P_CHART;
			break;
		case ChartTypes.C_CHART:
			chartTypeID = ChartTypeIDS.C_CHART;
			break;

		case ChartTypes.SUM_CHART:
			chartTypeID = ChartTypeIDS.SUM_CHART;
			break;
		case ChartTypes.AVERAGE_CHART:
			chartTypeID = ChartTypeIDS.AVERAGE_CHART;
			break;

		default:
			logError("Chart template type - '"+chartType+"'is incorrect");
			return null;
		}

		switch(chartType) {
		case ChartTypes.XI_MR:
		case ChartTypes.XBAR_S:
		case ChartTypes.XBAR_R:
			primaryGroupName = "X Bar Chart Run Rules";

			requestPayload = "{"
					+ "   \"ChartType\":{"
					+ "      \"Id\":\""+chartTypeID+"\","
					+ "      \"Name\":\""+chartType+"\""
					+ "   },"
					+ "   \"PropertyValues\":["
					+ "      {"
					+ "         \"ControlType\":\"Group\","
					+ "         \"DisplayName\":\""+primaryGroupName+"\","
					+ "         \"Contents\":["
					+ selectPrimaryRules(rules)
					+ "         ]"
					+ "      },"
					+ "      {"
					+ "         \"ControlType\":\"Group\","
					+ "         \"DisplayName\":\""+secondaryGroupName+"\","
					+ "         \"Contents\":["
					+ selectSecondaryRules(rules)
					+ "         ]"
					+ "      }"
					+ "   ],"
					+ "   \"Id\":null,"
					+ "   \"Name\":\""+chartName+"\","
					+ "   \"IsEnable\":"+chartEnable
					+ "}";
			break;

		case ChartTypes.NP_CHART:
		case ChartTypes.U_CHART:
		case ChartTypes.P_CHART:
		case ChartTypes.C_CHART:
			primaryGroupName = "Chart Run Rules";
			requestPayload = "{"
					+ "   \"ChartType\":{"
					+ "      \"Id\":\""+chartTypeID+"\","
					+ "      \"Name\":\""+chartType+"\""
					+ "   },"
					+ "   \"PropertyValues\":["
					+ "      {"
					+ "         \"ControlType\":\"Group\","
					+ "         \"DisplayName\":\""+primaryGroupName+"\","
					+ "         \"Contents\":["
					+ selectPrimaryRules(rules)
					+ "         ]"
					+ "      },"
					+ "   ],"
					+ "   \"Id\":null,"
					+ "   \"Name\":\""+chartName+"\","
					+ "   \"IsEnable\":"+chartEnable
					+ "}";
			break;

		case ChartTypes.SUM_CHART:
		case ChartTypes.AVERAGE_CHART:
			requestPayload = "{"
					+ "   \"ChartType\":{"
					+ "      \"Id\":\""+chartTypeID+"\","
					+ "      \"Name\":\""+chartType+"\""
					+ "   },"
					+ "   \"PropertyValues\":null,"
					+ "   \"Id\":null,"
					+ "   \"Name\":\""+chartName+"\","
					+ "   \"IsEnable\":"+chartEnable
					+ "}";
			break;
		default:
			logError("Chart template type - '"+chartType+"'is incorrect");
			return null;
		}
		return requestPayload;
	}

	/**
	 * This method is used to set status true for rule selection
	 * @author pashine_a
	 * @param ruleName (Rule Name)
	 * @param selectedRules (Rule list)
	 * @return boolean. It will return boolean result true if rule needs to be selected
	 */
	public boolean selectRule(String ruleName, List <String> selectedRules) {
		boolean ruleEnabled = false;
		for(int i=0;i<selectedRules.size();i++) {
			if(ruleName.equalsIgnoreCase(selectedRules.get(i))) {
				ruleEnabled = true;
				break;
			}
		}
		return ruleEnabled;
	}

	/**
	 * This method is used to get primary rule status
	 * @author pashine_a
	 * @param rules (Rule list)
	 * @return String. It will return all the primary rules payload
	 */
	public String selectPrimaryRules( List<String> rules) {
		List<String> ruleNumbers = Arrays.asList("1","2","3","4","5","6","7","8","9");
		String ruleName = null;
		boolean status = false;
		String ruleTemplate = "";   

		for(int i=0;i<ruleNumbers.size();i++) {
			ruleName = "Rule "+ ruleNumbers.get(i);
			status = selectRule(ruleName, rules);
			ruleTemplate = ruleTemplate + "{"
					+ "               \"Name\":\"PrimaryChartRule"+ruleNumbers.get(i)+"\","
					+ "               \"Value\":"+status+","
					+ "            }";
			if(i<ruleNumbers.size()-1) {
				ruleTemplate = ruleTemplate + ",";
			}
		}
		return ruleTemplate;
	}

	/**
	 * This method is used to get secondary rule status
	 * @author pashine_a
	 * @param rules (Rule list)
	 * @return String. It will return all the secondary rules payload
	 */
	public String selectSecondaryRules( List<String> rules) {
		List<String> ruleNumbers = Arrays.asList("1","4", "5","8");
		String ruleName = null;
		boolean status = false;
		String ruleTemplate = "";   

		for(int i=0;i<ruleNumbers.size();i++) {
			ruleName = "Secondary Rule "+ ruleNumbers.get(i);
			status = selectRule(ruleName, rules);
			ruleTemplate = ruleTemplate + "{"
					+ "               \"Name\":\"SecondaryChartRule"+ruleNumbers.get(i)+"\","
					+ "               \"Value\":"+status+","
					+ "            }";
			if(i<ruleNumbers.size()-1) {
				ruleTemplate = ruleTemplate + ",";
			}
		}
		return ruleTemplate;
	}

	/**
	 * This method is used to get chart details for provided chart name
	 * @author pashine_a
	 * @param chartName
	 * @param dataType
	 * @return String. It will return property value for given chart name & property name
	 */
	public String getChartDetails(String chartName, String proprtyName) {
		String chartDetail = null;

		ApiUtils apiUtils = new ApiUtils();
		Gson gson = new GsonBuilder().create();

		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("Accept", "application/json, text/plain, /");
		headers.put("Authorization", "Bearer " + accessToken);

		String request = "";

		Response createChartResponse = apiUtils.submitRequest(METHOD_TYPE.GET, request, headers,
				getChartListUrl);

		MultipleDataResponse dataResponse = gson.fromJson(createChartResponse.asString(), MultipleDataResponse.class);

		if(!dataResponse.Status) {
			logError("Failed to get chart list - "+chartName);
			return chartDetail;
		}

		switch(proprtyName.toUpperCase()) {
		case "ID":
			for (int i = 0; i < dataResponse.Data.size(); i++) {
				if (dataResponse.Data.get(i).Name.equalsIgnoreCase(chartName)) {
					chartDetail = dataResponse.Data.get(i).Id;
					break;
				}
			}
			break;

		case "ISENABLE":
			for (int i = 0; i < dataResponse.Data.size(); i++) {
				if (dataResponse.Data.get(i).Name.equalsIgnoreCase(chartName)) {
					chartDetail = Boolean.toString(dataResponse.Data.get(i).IsEnable);
					break;
				}
			}
			break;
		default :

			logError("Property type is incorrect");
			return chartDetail;
		}

		logInfo("Chart '"+proprtyName+"' value for chart - "+chartName+" is " +chartDetail);
		return chartDetail;
	}

	/**
	 * This method is used to enable/disable chart template as per provided chart enable status
	 * @author pashine_a
	 * @param chartParams (Chart template details)
	 * @return boolean. It will return boolean result true if new chart template has been added
	 */
	public boolean enableChart(ChartParams chartParams) {
		try {

			ApiUtils apiUtils = new ApiUtils();
			Gson gson = new GsonBuilder().create();

			Map<String, String> headers = new HashMap<String, String>();
			headers.put("Content-Type", "application/json");
			headers.put("Accept", "application/json, text/plain, /");
			headers.put("Authorization", "Bearer " + accessToken);

			String saveChartJSON = getEnableChart(chartParams);

			Response createChartResponse = apiUtils.submitRequest(METHOD_TYPE.POST, saveChartJSON, headers,
					AddChartUrl);

			SimpleDataResponse response = gson.fromJson(createChartResponse.asString(),
					SimpleDataResponse.class);

			if(!response.Status) {
				logError("Failed to update chart status for chart - "+chartParams.chartName);
				return false;
			}

			logInfo("Updated chart status for chart - '"+chartParams.chartName+"'");
			return true;

		} catch (Exception e) {
			logError("Failed to update chart status - '"+chartParams.chartName+" ' - "+ e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to set chart enable status & get template
	 * @author pashine_a
	 * @param chartDetails (Chart template details)
	 * @return String. It will return SaveChart payload with provided chart enable status
	 */
	public String getEnableChart(ChartParams chartDetails) {
		String chartEnableTemplate = "";   
		chartEnableTemplate = "{"
				+ "    \"ChartType\": {"
				+ "        \"Id\": \""+chartDetails.chartTypeID+"\","
				+ "        \"Name\": \""+chartDetails.chartType+"\""
				+ "    },"
				+ "    \"Id\": \""+chartDetails.chartID+"\","
				+ "    \"Name\": \""+chartDetails.chartName+"\","
				+ "    \"IsEnable\": "+chartDetails.chartEnable+""
				+ "}";
		return chartEnableTemplate;
	}


	public static class ChartParams{
		public String chartType;
		public String chartTypeID;
		public String chartName;
		public String chartID;
		public List<String> chartRules;
		public boolean chartEnable = true;
	}

	public static class ChartTypes{

		public static final String XI_MR = "XiMr";
		public static final String NP_CHART  = "NpChart";
		public static final String XBAR_S = "XBarS";
		public static final String XBAR_R = "XBarR";
		public static final String SUM_CHART = "SumChart";
		public static final String U_CHART = "UChart";
		public static final String P_CHART = "PChart";
		public static final String C_CHART = "CChart";
		public static final String AVERAGE_CHART = "AverageChart";

	}

	public static class ChartTypeIDS{

		public static final String XI_MR = "a36ca145-ffe6-48cf-97b6-500998cf658a";
		public static final String NP_CHART  = "5c72481d-1c4c-46fb-b786-76be64868f20";
		public static final String XBAR_S = "2b80366c-365f-4228-b9e5-86a1e69e3653";
		public static final String XBAR_R = "86deb70c-7eb3-4cd0-8060-8b3b11d636d4";
		public static final String SUM_CHART = "2d1fc5a9-5fd7-4edd-a8ff-925aff28e354";
		public static final String U_CHART = "e278ab66-2913-4a32-8a7d-92b666f98614";
		public static final String P_CHART = "ca63ff6c-5b77-4d58-b430-bf5c5be435a6";
		public static final String C_CHART = "4fba0688-cbbf-478f-b3ff-c8d4f0c72329";
		public static final String AVERAGE_CHART = "8af1846b-bb32-48cd-8def-dd9f5234c443";
	}

	public static class ChartRules{

		public static final String PRIMARY_RULE1 = "Rule 1";
		public static final String PRIMARY_RULE2 = "Rule 2";
		public static final String PRIMARY_RULE3 = "Rule 3";
		public static final String PRIMARY_RULE4 = "Rule 4";
		public static final String PRIMARY_RULE5 = "Rule 5";
		public static final String PRIMARY_RULE6 = "Rule 6";
		public static final String PRIMARY_RULE7 = "Rule 7";
		public static final String PRIMARY_RULE8 = "Rule 8";
		public static final String PRIMARY_RULE9 = "Rule 9";

		public static final String SECONDARY_RULE1  = "Secondary Rule 1";
		public static final String SECONDARY_RULE4 = "Secondary Rule 4";
		public static final String SECONDARY_RULE5 = "Secondary Rule 5";
		public static final String SECONDARY_RULE8 = "Secondary Rule 8";

	}
	
	public class MultipleDataResponse {		
		public List<Data> Data;
		public boolean Status;
	}
	
	public class SimpleDataResponse {		
		public String Data;
		public boolean Status;
	}
	
	public class Data {

		public String Id;
		public String Name;
		public boolean IsEnable;
	

	}

}
