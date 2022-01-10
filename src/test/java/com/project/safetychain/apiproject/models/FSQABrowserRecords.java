package com.project.safetychain.apiproject.models;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.project.safetychain.apiproject.models.FSQABrowserForms.FormFields;
import com.project.utilities.ApiUtils;
import com.project.utilities.ApiUtils.METHOD_TYPE;
import com.project.utilities.JSONUtils;
import com.project.safetychain.apiproject.models.Auth;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class FSQABrowserRecords extends Auth{

	public String getRecords = baseURI + FSQABrowserRecordsURLs.GET_RECORDS;
	public String getRecord = baseURI + FSQABrowserRecordsURLs.GET_RECORD;


	ApiUtils apiUtils = new ApiUtils();
	Gson gson = new GsonBuilder().create();
	JSONUtils jsonutils = new JSONUtils();


	/**
	 * This method is used to verify record
	 * @author choubey_a
	 * @param  resourceTypeId,resourceInstanceId,recordId and formname
	 * @return true if record is verified
	 */

	public boolean verifyRecord(String resourceTypeId,String resourceInstanceId,String recordId, String formname) {

		apiUtils = new ApiUtils();

		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("Accept", "application/json, text/plain, /");
		headers.put("Authorization", "Bearer " + accessToken);
		Gson gson = new GsonBuilder().create();

		try {

			String request = "{\"take\":100,\"skip\":0,\"page\":1,\"pageSize\":100,"
					+ "\"Parameters\":{\"Node\":{\"Type\":\""+resourceTypeId+"\","
					+ "\"Node\":\""+resourceInstanceId+"\",\"IsParent\":true},\"FromDate\":\"\","
					+ "\"ToDate\":\"\",\"IsCompliance\":\"\",\"Resource\":[],\"FormName\":[],\"FormType\":[],"
					+ "\"Program\":[],\"CompletedBy\":[],\"FirstRowId\":null,\"LastRowId\":null},\"FirstRowId\":null,"
					+ "\"LastRowId\":null}";

			Response response = apiUtils.submitRequest(METHOD_TYPE.POST, request, headers, getRecords);
			SingleDataResponse sdr = gson.fromJson(response.asString(), SingleDataResponse.class);
			for(int i=0;i< sdr.Data.Rows.size();i++) {
				if(!(sdr.Data.Rows.get(i).Name.equals(formname) && sdr.Data.Rows.get(i).ID.equals(recordId))) {
					return false;
				}
			}
			return true;
		} catch (Exception e) {
			logInfo("Unable to verify Record -"+e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to verify record
	 * @author choubey_a
	 * @param  resourceTypeId,resourceInstanceId,recordId and formname
	 * @return true if record is verified
	 */

	public boolean verifyRecordData(String recordId, HashMap<String,Object> data) {

		apiUtils = new ApiUtils();

		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("Accept", "application/json, text/plain, /");
		headers.put("Authorization", "Bearer " + accessToken);
		Gson gson = new GsonBuilder().create();
		try {

			String request = "{\"data\":\""+recordId+"\"}";

			Response response = apiUtils.submitRequest(METHOD_TYPE.POST, request, headers, getRecord);
			SingleDataResponse sdr = gson.fromJson(response.asString(), SingleDataResponse.class);

			for (Entry<String, Object> entry : data.entrySet()) {
				String shortname = entry.getKey();
				Object value = entry.getValue();
				for(int i=0;i< sdr.Data.RecordData.FormDetails.size();i++) {
					if(shortname .equals(sdr.Data.RecordData.FormDetails.get(i).ShortName)) {
						switch(sdr.Data.RecordData.FormDetails.get(i).Type) {
						case FormFields.FREETEXT:
							if(!(shortname.equals(sdr.Data.RecordData.FormDetails.get(i).ShortName) && value.equals(sdr.Data.RecordData.FormDetails.get(i).Value))) {
								logError("Record Data is not correct");
								return false;
							}
							break;
						case FormFields.SINGLELINETEXT:						
							if(!(shortname.equals(sdr.Data.RecordData.FormDetails.get(i).ShortName) && value.equals(sdr.Data.RecordData.FormDetails.get(i).Value))) {
								logError("Record Data is not correct");
								return false;
							}
							break;
						case FormFields.NUMERIC:							
							if(!(shortname.equals(sdr.Data.RecordData.FormDetails.get(i).ShortName) && value.equals(sdr.Data.RecordData.FormDetails.get(i).Value))) {
								logError("Record Data is not correct");
								return false;
							}		
							break;
						case FormFields.DATE:							
							if(!(shortname.equals(sdr.Data.RecordData.FormDetails.get(i).ShortName) && value.equals(sdr.Data.RecordData.FormDetails.get(i).Value))) {
								logError("Record Data is not correct");
								return false;
							}	
							break;
						case FormFields.TIME:							
							if(!(shortname.equals(sdr.Data.RecordData.FormDetails.get(i).ShortName) && value.equals(sdr.Data.RecordData.FormDetails.get(i).Value))) {
								logError("Record Data is not correct");
								return false;
							}	
							break;
						case FormFields.DATETIME:					
							if(!(shortname.equals(sdr.Data.RecordData.FormDetails.get(i).ShortName) && value.equals(sdr.Data.RecordData.FormDetails.get(i).Value))) {
								logError("Record Data is not correct");
								return false;
							}
							break;
						case FormFields.IDENTIFIER:								
							if(!(shortname.equals(sdr.Data.RecordData.FormDetails.get(i).ShortName) && value.equals(sdr.Data.RecordData.FormDetails.get(i).Value))) {
								logError("Record Data is not correct");
								return false;
							}
							break;
						default:
							logError("Correct Field Type not provided");
						}
						break;
					}
				}
			}
			return true;
		} catch (Exception e) {
			logInfo("Unable to verify Record Data-"+e.getMessage());
			return false;
		}
	}

	public boolean verifySectionRecordData(String recordId, LinkedHashMap<String,String> data, List<String> field) {

		apiUtils = new ApiUtils();

		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("Accept", "application/json, text/plain, /");
		headers.put("Authorization", "Bearer " + accessToken);
		try {

			String request = "{\"data\":\""+recordId+"\"}";

			Response response = apiUtils.submitRequest(METHOD_TYPE.POST, request, headers, getRecord);
			JsonPath jpath=new JsonPath(response.asString());
			int i=0;
			for (Entry<String, String> entry : data.entrySet()) {
				String shortname = entry.getKey();
				String value = entry.getValue();
				switch(field.get(i).toString()) {
				case FormFields.FREETEXT:
					String sname= jsonutils.perform_GetDynamicJsonPath(FSQABrowserRecordsURLs.GET_SHORTNAME1, "COUNT", Integer.toString(i));					
					String sname1=jpath.getString(sname);
					String val = jsonutils.perform_GetDynamicJsonPath(FSQABrowserRecordsURLs.GET_SECTIONFIELD_VALUE, "COUNT", Integer.toString(i));
					String val1=jpath.getString(val);
					if(!(shortname.equals(sname1) && value.equals(val1))) {
						logError("Free Text Data is not correct");
						return false;	
					}
					break;
				case FormFields.SELECTMULTIPLE:
					String multi= jsonutils.perform_GetDynamicJsonPath(FSQABrowserRecordsURLs.GET_FIRST_MULTIPLE_VALUE1, "COUNT", Integer.toString(i));					
					String selectMulti=jpath.getString(multi);
					String shortNameVal = jsonutils.perform_GetDynamicJsonPath(FSQABrowserRecordsURLs.GET_SHORTNAME1, "COUNT", Integer.toString(i));
					String shortname3=jpath.getString(shortNameVal);
					if(!(shortname.equals(shortname3) && value.equals(selectMulti))) {
						logError("Select Multiple Data is not correct");
						return false;	
					}
					break;
				case FormFields.SINGLELINETEXT:						
					String tname= jsonutils.perform_GetDynamicJsonPath(FSQABrowserRecordsURLs.GET_SHORTNAME1, "COUNT", Integer.toString(i));					
					String tname1=jpath.getString(tname);
					String tval = jsonutils.perform_GetDynamicJsonPath(FSQABrowserRecordsURLs.GET_SECTIONFIELD_VALUE, "COUNT", Integer.toString(i));
					String tval1=jpath.getString(tval);
					if(!(shortname.equals(tname1) && value.equals(tval1))) {
						logError("Single Line Text Data is not correct");
						return false;	
					}
					break;
				case FormFields.NUMERIC:
					String nname= jsonutils.perform_GetDynamicJsonPath(FSQABrowserRecordsURLs.GET_SHORTNAME1, "COUNT", Integer.toString(i));					
					String nname1=jpath.getString(nname);
					String nval = jsonutils.perform_GetDynamicJsonPath(FSQABrowserRecordsURLs.GET_SECTIONFIELD_VALUE.toString(), "COUNT", Integer.toString(i));
					String nval1=jpath.getString(nval);
					if(!(shortname.equals(nname1) && value.equals(nval1))) {
						logError("Numeric Data is not correct");
						return false;	
					}
					break;
				case FormFields.DATE:
					String dname= jsonutils.perform_GetDynamicJsonPath(FSQABrowserRecordsURLs.GET_SHORTNAME1, "COUNT", Integer.toString(i));					
					String dname1=jpath.getString(dname);
					String dval = jsonutils.perform_GetDynamicJsonPath(FSQABrowserRecordsURLs.GET_SECTIONFIELD_VALUE, "COUNT", Integer.toString(i));
					String dval1=jpath.getString(dval);
					if(!(shortname.equals(dname1) && value.equals(dval1))) {
						logError("Date Field Data is not correct");
						return false;	
					}
					break;
				case FormFields.TIME:	
					String timename= jsonutils.perform_GetDynamicJsonPath(FSQABrowserRecordsURLs.GET_SHORTNAME1, "COUNT", Integer.toString(i));					
					String timename1=jpath.getString(timename);
					String timeval = jsonutils.perform_GetDynamicJsonPath(FSQABrowserRecordsURLs.GET_SECTIONFIELD_VALUE, "COUNT", Integer.toString(i));
					String timeval1=jpath.getString(timeval);
					if(!(shortname.equals(timename1) && value.equals(timeval1))) {
						logError("Time Data is not correct");
						return false;	
					}
					break;
				case FormFields.DATETIME:

					String dtname= jsonutils.perform_GetDynamicJsonPath(FSQABrowserRecordsURLs.GET_SHORTNAME1, "COUNT", Integer.toString(i));					
					String dtname1=jpath.getString(dtname);
					String dtval = jsonutils.perform_GetDynamicJsonPath(FSQABrowserRecordsURLs.GET_SECTIONFIELD_VALUE, "COUNT", Integer.toString(i));
					String dtval1=jpath.getString(dtval);
					if(!(shortname.equals(dtname1) && value.equals(dtval1))) {
						logError("Date Time Data is not correct");
						return false;	
					}
					break;
				case FormFields.IDENTIFIER:	

					String iname= jsonutils.perform_GetDynamicJsonPath(FSQABrowserRecordsURLs.GET_SHORTNAME1, "COUNT", Integer.toString(i));					
					String iname1=jpath.getString(iname);
					String ival = jsonutils.perform_GetDynamicJsonPath(FSQABrowserRecordsURLs.GET_SECTIONFIELD_VALUE, "COUNT", Integer.toString(i));
					String ival1=jpath.getString(ival);
					if(!(shortname.equals(iname1) && value.equals(ival1))) {
						logError("Select Multiple Data is not correct");
						return false;	
					}
					break;
				case FormFields.SELECTONE:	
					String one1 = jsonutils.perform_GetDynamicJsonPath(FSQABrowserRecordsURLs.GET_SINGLE_VALUE1, "COUNT", Integer.toString(i));
					String selectone1=jpath.getString(one1);	
					String shortNameValue2 = jsonutils.perform_GetDynamicJsonPath(FSQABrowserRecordsURLs.GET_SHORTNAME1, "COUNT", Integer.toString(i));
					String shortname2=jpath.getString(shortNameValue2);	
					if(!(shortname.equals(shortname2) && value.equals(selectone1))) {
						logError("Select One Data is not correct");
						return false;
					}			
					break;
				default:
					logError("Correct Field Type not provided");
				}
				i++;
			}
			return true;
		} catch (Exception e) {
			logInfo("Unable to verify Record Data-"+e.getMessage());
			return false;
		}
	}

	public boolean verifySelectOneMultipleRecordData(String recordId, HashMap<String,String> data, int count, String fieldType) {

		apiUtils = new ApiUtils();

		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("Accept", "application/json, text/plain, /");
		headers.put("Authorization", "Bearer " + accessToken);

		try {

			String request = "{\"data\":\""+recordId+"\"}";

			Response response = apiUtils.submitRequest(METHOD_TYPE.POST, request, headers, getRecord);		

			JsonPath jpath=new JsonPath(response.asString());

			for (Map.Entry<String, String> entry : data.entrySet()) {
				String shortname = entry.getKey();
				String value = entry.getValue();					
				switch(fieldType) {
				case FormFields.SELECTMULTIPLE:
					String multiple = jsonutils.perform_GetDynamicJsonPath(FSQABrowserRecordsURLs.GET_FIRST_MULTIPLE_VALUE, "COUNT", Integer.toString(count-1));					
					String selectMultiple=jpath.getString(multiple);
					String shortNameValue = jsonutils.perform_GetDynamicJsonPath(FSQABrowserRecordsURLs.GET_SHORTNAME, "COUNT", Integer.toString(count-1));
					String shortname2=jpath.getString(shortNameValue);
					if(!(shortname.equals(shortname2) && value.equals(selectMultiple))) {
						logError("Select Multiple Data is not correct");
						return false;
					}
					break;
				case FormFields.SELECTONE:	
					String one = jsonutils.perform_GetDynamicJsonPath(FSQABrowserRecordsURLs.GET_SINGLE_VALUE, "COUNT", Integer.toString(count-1));
					String selectone=jpath.getString(one);	
					String shortNameValue1 = jsonutils.perform_GetDynamicJsonPath(FSQABrowserRecordsURLs.GET_SHORTNAME, "COUNT", Integer.toString(count-1));
					String shortname1=jpath.getString(shortNameValue1);	
					if(!(shortname.equals(shortname1) && value.equals(selectone))) {
						logError("Select One Data is not correct");
						return false;
					}								
					break;
				default:
					logError("Correct Field Type not provided");
				}
			}
			return true;
		} catch (Exception e) {
			logInfo("Unable to verify Select One/Multiple Record Data-"+e.getMessage());
			return false;
		}
	}

	public class SingleDataResponse {
		public Data Data;
		public boolean Status;
	}

	public class Data {
		public List<Rows> Rows;
		public RecordData RecordData;
	}

	public class RecordData{
		public List<FormDetails> FormDetails;
	}

	public class FormDetails{
		public String ShortName;
		public Object Value;
		public String Type;
		public String Id;
	}

	public class Rows{
		public String ID;
		public String Name;
	}
}
