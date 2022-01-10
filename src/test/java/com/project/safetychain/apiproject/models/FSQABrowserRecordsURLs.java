package com.project.safetychain.apiproject.models;

public class FSQABrowserRecordsURLs {

	public static final String GET_RECORDS = "/api/api/Records/GetRecords";
	public static final String GET_RECORD = "/api/api/Records/GetRecord";


	/** Json XPath - Start */

	public static final String GET_FIRST_MULTIPLE_VALUE= "Data.RecordData.FormDetails[COUNT].Value[0].Name";
	public static final String GET_SINGLE_VALUE = "Data.RecordData.FormDetails[COUNT].Value.Name";	
	public static final String GET_SHORTNAME = "Data.RecordData.FormDetails[COUNT].ShortName";
	public static final String GET_FIRST_MULTIPLE_VALUE1= "Data.RecordData.FormDetails[0].SectionElements[COUNT].Value[0].Name";
	public static final String GET_SINGLE_VALUE1 = "Data.RecordData.FormDetails[0].SectionElements[COUNT].Value.Name";	
	public static final String GET_SHORTNAME1 = "Data.RecordData.FormDetails[0].SectionElements[COUNT].ShortName";
	public static final String GET_SECTIONFIELD_VALUE= "Data.RecordData.FormDetails[0].SectionElements[COUNT].Value";
	
	/** Json XPath - End  */ 
}
