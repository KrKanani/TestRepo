package com.project.safetychain.webapp.testcases.support;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.session.W3CPlatformNameNormaliser;
import org.openqa.selenium.support.PageFactory;

import com.project.testbase.TestBase;
import com.project.utilities.ControlActions;
import com.project.safetychain.pcapp.pageobjects.FormViewScreen.FieldInputs;
import com.project.safetychain.webapp.pageobjects.*;

public class FormOperations extends TestBase{
	ControlActions controlActions;
	FSQABrowserFormsPage formsPage;
	CommonPage mainPage;
	FormDesignerPage formDesignerPage;

	public static String fileName = "ABC1.csv";
	public static String filePath = System.getProperty("user.dir") + "\\test-data-files\\UploadDocuments\\"+fileName; 


	public FormOperations(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		controlActions = new ControlActions(driver);
		mainPage = new CommonPage(driver);
		formsPage = new FSQABrowserFormsPage(driver);
		formDesignerPage = new FormDesignerPage(driver);
	}



	public boolean submitData(FormDetails formDetails) {
		HashMap<String, List<String>> details;
		String fieldName;
		List<String> fieldValue;
		List<FieldProperties> fieldProperties;

		try {
			details = formDetails.fieldDetails;
			fieldProperties = formDetails.fieldProperties;

			formsPage.Sync();
			if(formDetails.locationName != null ||  formDetails.resourceName != null) {
				if(!selectLocationResource(formDetails.locationName, formDetails.resourceName)) {
					logError("Failed to select location/resource");
					return false;
				}
			}

			if(formDetails.formLevelAttachmenttCheck) {
				if(!formsPage.addFormLevelAttachment(formDetails.uploadFormLevelFilePath)){
					logError("Fail to add attachment at form level");
					return false;
				}
			}
			if(formDetails.fieldDetails!=null) {
				for (Map.Entry<String, List<String>> entry : details.entrySet()) {
					fieldName = entry.getKey();
					fieldValue = entry.getValue();
					if(!fillData(fieldName, fieldValue, fieldProperties, formDetails.newFieldValue)) {
						logError("Failed to fill form");
						return false;
					}
				}
				logInfo("Filled data in provided field(s)");
			}
			if(formDetails.isSubmit) {
				controlActions.clickElement(formsPage.SubmitFormBtn);
				formsPage.Sync();
				logInfo("Clicked on 'SUBMIT' form button");
				controlActions.clickElement(formsPage.SubmissionOKBtn);
				formsPage.Sync();
				logInfo("The form is filled & submitted sucessfully");
			}
			else {
				if(formDetails.isDelete) {
					if(!formsPage.deleteSavedForm()) {
						logInfo("Failed to delete the saved form/task");
						return false;
					}
					logInfo("Deleted the saved form/task");
				}else {
					if(formDetails.doNotSaveOrSubmit) {
						// do nothing. Nor Submitting or Saving of Form
					}else {
						controlActions.clickElement(formsPage.SaveBtn);
						formsPage.Sync();
						logInfo("The form is filled & saved sucessfully");
					}
				}
			}
			return true;
		}catch (Exception e) {
			logError("Failed to fill data in form - "+e.getMessage());
			return false;
		}
	}

	public boolean fillData(String fieldName, List<String> fieldValue, List<FieldProperties> fieldProperties,boolean newFieldValue) {
		WebElement fieldElement, fieldStatus;
		String fieldType = null;

		try {
			fieldElement = controlActions.perform_GetElementByXPath(FSQABrowserFormsPageLocators.FIELD_TYPE, "FIELD_NAME", fieldName);
			fieldStatus = controlActions.perform_GetElementByXPath(FSQABrowserFormsPageLocators.FIELD_FILLED_STATUS_LBL, "FIELD_NAME", fieldName);
			fieldType =  fieldElement.getAttribute("data-fieldtype");

			if(fieldStatus.getAttribute("class").equals("fa fa-circle-o") || fieldStatus.getAttribute("class").equals("fa") || newFieldValue) {
				switch(fieldType) {
				case "Paragraph":
					if(!setTextAreaFieldValue(fieldName, fieldValue,fieldProperties)) {
						return false;
					}
					break;

				case "SelectMultiple":
					if(!setSelecMultipleFieldValue(fieldName, fieldValue,fieldProperties)){
						return false;
					}
					break;

				case "Aggregate":
					if(!setAggregateProperties(fieldName, fieldProperties)){
						return false;
					}
					break;

				default:
					if(!setInputFieldValue(fieldName, fieldValue, fieldProperties)) {
						return false;
					}

				}
				logInfo("Data is filled in field - '"+fieldName+"' for '"+fieldType+"' field type");
			}else {
				logInfo("Data is already filled in field");
			}

			return true;
		}catch (Exception e) {
			logError("Failed to set value in '"+fieldName+"' field for '"+fieldType+"'field type"+e.getMessage());	
			return false;
		}
	}

	public boolean setSelecMultipleFieldValue(String fieldName, List<String> fieldValue, List<FieldProperties> fieldProperties) {
		List<WebElement> fieldElement, fieldElementLbl,  fieldElementDetails;
		String fieldID;
		try {
			fieldElement = controlActions.perform_GetListOfElementsByXPath(FieldInputs.INPUT_FIELD, "FIELD_NAME", fieldName);
			fieldElementLbl =  controlActions.perform_GetListOfElementsByXPath(FSQABrowserFormsPageLocators.FIELD_LBL, "FIELD_NAME", fieldName);
			fieldElementDetails = controlActions.perform_GetListOfElementsByXPath(FSQABrowserFormsPageLocators.FIELD_TYPE, "FIELD_NAME", fieldName);

			if(fieldValue.size()==1) {
				for(int i=0;i<fieldElement.size();i++) {
					String options[] = fieldValue.get(0).split(";");
					for(int j=0;j<options.length;j++) {
						controlActions.sendKeys(fieldElement.get(i),options[j]);
						controlActions.sendKey(fieldElement.get(i),Keys.ARROW_DOWN);
						controlActions.sendKey(fieldElement.get(i),Keys.ENTER);		
					}		
					controlActions.clickElement(fieldElementLbl.get(i));
					fieldID = fieldElementDetails.get(i).getAttribute("data-field");
					if(!setFieldInfo(fieldName,fieldID,fieldProperties)) {
						logError("Failed while performing field property operations");
						return false;
					}
				}
			}else {
				for(int i=0;i<fieldElement.size();i++) {
					String options[] = fieldValue.get(i).split(";");
					for(int j=0;j<options.length;j++) {
						controlActions.sendKeys(fieldElement.get(i),options[j]);
						controlActions.sendKey(fieldElement.get(i),Keys.ARROW_DOWN);
						controlActions.sendKey(fieldElement.get(i),Keys.ENTER);		
					}
					controlActions.clickElement(fieldElementLbl.get(i));
					fieldID = fieldElementDetails.get(i).getAttribute("data-field");
					if(!setFieldInfo(fieldName,fieldID,fieldProperties)) {
						logError("Failed while performing field property operations");
						return false;
					}
				}
			}
			logInfo("Setted 'Select One' field's value");
			return true;
		}catch (Exception e) {
			logError("Failed to set 'Select One' field's value - "+e.getMessage());	
			return false;
		}
	}

	public boolean setInputFieldValue(String fieldName, List<String> fieldValue, List<FieldProperties> fieldProperties) {
		List<WebElement> fieldElement, fieldElementLbl, fieldElementDetails;
		String fieldID;
		try {
			fieldElement = controlActions.perform_GetListOfElementsByXPath(FieldInputs.INPUT_FIELD, "FIELD_NAME", fieldName);
			fieldElementLbl =  controlActions.perform_GetListOfElementsByXPath(FSQABrowserFormsPageLocators.FIELD_LBL, "FIELD_NAME", fieldName);
			fieldElementDetails = controlActions.perform_GetListOfElementsByXPath(FSQABrowserFormsPageLocators.FIELD_TYPE, "FIELD_NAME", fieldName);
			if(fieldValue.size()==1) {
				for(int i=0;i<fieldElement.size();i++) {
					controlActions.sendKeys(fieldElement.get(i),fieldValue.get(0));
					controlActions.clickElement(fieldElementLbl.get(i));
					fieldID = fieldElementDetails.get(i).getAttribute("data-field");
					if(!setFieldInfo(fieldName,fieldID,fieldProperties)) {
						logError("Failed while performing field property operations");
						return false;
					}
				}
			}else {
				for(int i=0;i<fieldElement.size();i++) {
					controlActions.sendKeys(fieldElement.get(i),fieldValue.get(i));
					controlActions.clickElement(fieldElementLbl.get(i));
					fieldID = fieldElementDetails.get(i).getAttribute("data-field");
					if(!setFieldInfo(fieldName,fieldID,fieldProperties)) {
						logError("Failed while performing field property operations");
						return false;
					}
				}
			}
			logInfo("Setted  field's value");
			return true;
		}catch (Exception e) {
			logError("Failed to set field input's value - "+e.getMessage());
			return false;
		}
	}

	public boolean setAggregateProperties(String fieldName, List<FieldProperties> fieldProperties) {
		WebElement fieldElementDetails;
		String fieldID;
		try {
			fieldElementDetails = controlActions.perform_GetElementByXPath(FSQABrowserFormsPageLocators.FIELD_TYPE, "FIELD_NAME", fieldName);
			fieldID = fieldElementDetails.getAttribute("data-field");
			if(!setFieldInfo(fieldName,fieldID,fieldProperties)) {
				logError("Failed while performing field property operations");
				return false;
			}

			logInfo("Set field's properties");
			return true;
		}catch (Exception e) {
			logError("Failed to set field input's properties - "+e.getMessage());
			return false;
		}
	}

	public boolean setTextAreaFieldValue(String fieldName, List<String> fieldValue, List<FieldProperties> fieldProperties) {
		List<WebElement> fieldElement, fieldElementLbl, fieldElementDetails;
		String fieldID;
		try {
			fieldElement = controlActions.perform_GetListOfElementsByXPath(FieldInputs.TEXTAREA_FIELD, "FIELD_NAME", fieldName);
			fieldElementLbl =  controlActions.perform_GetListOfElementsByXPath(FSQABrowserFormsPageLocators.FIELD_LBL, "FIELD_NAME", fieldName);
			fieldElementDetails = controlActions.perform_GetListOfElementsByXPath(FSQABrowserFormsPageLocators.FIELD_TYPE, "FIELD_NAME", fieldName);

			if(fieldValue.size()==1) {
				for(int i=0;i<fieldElement.size();i++) {
					controlActions.sendKeys(fieldElement.get(i),fieldValue.get(0));
					controlActions.clickElement(fieldElementLbl.get(i));
					fieldID = fieldElementDetails.get(i).getAttribute("data-field");
					if(!setFieldInfo(fieldName,fieldID,fieldProperties)) {
						logError("Failed while performing field property operations");
						return false;
					}
				}
			}else {
				for(int i=0;i<fieldElement.size();i++) {
					controlActions.sendKeys(fieldElement.get(i),fieldValue.get(i));
					controlActions.clickElement(fieldElementLbl.get(i));
					fieldID = fieldElementDetails.get(i).getAttribute("data-field");
					if(!setFieldInfo(fieldName,fieldID,fieldProperties)) {
						logError("Failed while performing field property operations");
						return false;
					}			
				}
			}
			logInfo("Setted  field's value");
			return true;
		}catch (Exception e) {
			logError("Failed to set field textarea's value - "+e.getMessage());	
			return false;
		}
	}

	public boolean setFieldInfo(String fieldName,String fieldID, List<FieldProperties> fieldProperties){
		try {
			if(fieldProperties!=null) {
				for(int i=0;i<fieldProperties.size();i++) {
					if(fieldProperties.get(i).fieldName.equals(fieldName)){
						fieldProperties.get(i).fieldID = fieldID;
						if(!setFormFieldProperties(fieldName, fieldProperties.get(i))) {
							logError("Failed to set properties for field - "+fieldProperties.get(i).fieldName);
							return false;
						}
						break;
					}
				}
			}
			logInfo("Sucessfully setted all field properties");
			return true;
		}catch(Exception e) {
			logError("Failed while performing field property operations - "+e.getMessage());
			return false;
		}
	}


	public boolean selectLocationResource(String locationName, String resourceName) {
		WebElement location, resource;
		String selectedLocation, selectedResource;
		try {
			if(controlActions.isElementDisplayedOnPage(formsPage.LocationDdl)) {

				controlActions.click(formsPage.LocationDdl);
				location = controlActions.perform_GetElementByXPath(FSQABrowserFormsPageLocators.LIST_VALUE, "NAME", locationName);
				controlActions.click(location);
				formsPage.Sync();
				logInfo("Selected Location - "+locationName);
			}else {
				selectedLocation = formsPage.SelectedLocationLbl.getText();
				logInfo("Location - '"+selectedLocation+"' is already selected");
			}
			if(controlActions.isElementDisplayedOnPage(formsPage.SelectResourceDdl)) {
				controlActions.clickElement(formsPage.SelectResourceDdl);
				resource = controlActions.perform_GetElementByXPath(FSQABrowserFormsPageLocators.LIST_VALUE, "NAME", resourceName);
				controlActions.clickElement(resource);
				formsPage.Sync();
				logInfo("Selected Resource - "+resourceName);
			}else {
				selectedResource = formsPage.SelectedResourceLbl.getText();
				logInfo("Resource - '"+selectedResource+"' is already selected");
			}
			return true;
		}catch (Exception e) {
			logError("Failed to select Location/Resource - "+e.getMessage());	
			return false;
		}
	}

	public boolean setFormFieldProperties(String fieldName, FieldProperties fieldProperties) {
		WebElement CurrentElementFieldStatus;
		String fieldStatusClassName;
		String complianceStatus;
		try {
			CurrentElementFieldStatus = controlActions.perform_GetElementByXPath(FSQABrowserFormsPageLocators.FIELDS_FILL_STATUS, "FIELDID", fieldProperties.fieldID);
			fieldStatusClassName = CurrentElementFieldStatus.getAttribute("class");

			switch(fieldStatusClassName) {
			case "fa fa-check":
				complianceStatus = "Compliant";
				break;

			case "fa fa-close":
				complianceStatus = "Non-Compliant";
				break;

			case "fa fa-minus":
				complianceStatus = "No Compliance";
				break;

			default:
				complianceStatus = "Filled";
			}

			if(fieldProperties.complianceStatusCheck) {
				if(!fieldProperties.complianceStatus.equals(complianceStatus)) {
					logInfo("Expected : "+fieldProperties.complianceStatus+", Actual :"+complianceStatus);
					logError("Compliant status not matched");
					return false;
				}
			}
			if(fieldProperties.allowCorrectionsCheck) {
				if(!formsPage.setCorections(fieldProperties.fieldID, fieldProperties.allowPreCorrectionsCheck, fieldProperties.allowCorrectionsValue)) {
					logError("Fail to set corrections");
					return false;
				}
				if(fieldProperties.isResolvedCheck) {
					if(!formsPage.setIsResolvedStatus(fieldProperties.fieldID, fieldProperties.isResolvedStatus)) {
						logError("Fail to set 'Is Resolved' field value");
						return false;
					}
				}
			}
			if(fieldProperties.allowCommentsCheck) {
				if(!formsPage.setComments(fieldProperties.fieldID, fieldProperties.commentText)) {
					logError("Fail to set field properties - Comments");
					return false;
				}
			}

			if(fieldProperties.allowAttachmenstCheck) {
				if(!formsPage.addAttachment(fieldName, fieldProperties.fieldID, fieldProperties.uploadFilePath)) {
					logError("Fail to set field properties - Attachments");
					return false;
				}
			}
			logInfo("Setted properties for field - "+fieldProperties.fieldName);
			return true;
		}catch (Exception e) {
			logError("Failed to set properties for field - "+fieldProperties.fieldName+". - "+e.getMessage());	
			return false;
		}
	}

	/**
	 * This method is used to submit 'OEE Event' form (Dynamic flow) 
	 * @author pashine_a
	 * @param eventType
	 * @param batchID
	 * @param formDetailsPC
	 * @return boolean
	 */
	public boolean submitOEEEventsForm(String eventType, String batchID, FormDetails formDetails) {
		try {
			HashMap<String, List<String>> allFields = new LinkedHashMap<String, List<String>>();
			allFields.put("BatchID",Arrays.asList(batchID));
			switch(eventType) {
			case OEEeventType.ACTUAL_PRODUCTION_START:
				allFields.put("Shift ",Arrays.asList("Shift 1"));
				allFields.put("Event Type",Arrays.asList(OEEeventType.ACTUAL_PRODUCTION_START));
				allFields.put("Actual Production Start Time",Arrays.asList("NA"));
				break;

			case OEEeventType.THROUGHPUT_QUANTITY:
				allFields.put("Shift ",Arrays.asList("Shift 2"));
				allFields.put("Event Type",Arrays.asList(OEEeventType.THROUGHPUT_QUANTITY));
				allFields.put("Quantity Produced",Arrays.asList("4"));
				allFields.put("Quantity Date & Time",Arrays.asList("NA"));
				break;

			case OEEeventType.UNPLANNED_DOWNTIME:
				allFields.put("Shift ",Arrays.asList("Shift 3"));
				allFields.put("Event Type",Arrays.asList(OEEeventType.UNPLANNED_DOWNTIME));
				allFields.put("Downtime Event Type",Arrays.asList("Start"));
				allFields.put("Event Start Time",Arrays.asList("NA"));
				break;

			case OEEeventType.QUALITY:
				allFields.put("Shift ",Arrays.asList("Shift 1"));
				allFields.put("Event Type",Arrays.asList(OEEeventType.QUALITY));
				allFields.put("Quality Event Type",Arrays.asList("Production Rejects"));
				allFields.put("Production Rejects Reason ",Arrays.asList("Production Rejects Reason 2"));
				allFields.put("Event Quantity Rejected",Arrays.asList("2"));
				allFields.put("Quantity Rejected Time",Arrays.asList("NA"));
				allFields.put("Other Reason",Arrays.asList("PC App Test Form"));
				break;

			case OEEeventType.ACTUAL_PRODUCTION_END:
				allFields.put("Shift ",Arrays.asList("Shift 2"));
				allFields.put("Event Type",Arrays.asList(OEEeventType.ACTUAL_PRODUCTION_END));
				allFields.put("Actual Production End Time",Arrays.asList("NA"));
				allFields.put("Actual Quantity Produced",Arrays.asList("4"));
				allFields.put("Actual Quantity Rejected",Arrays.asList("2"));
				break;

			default:
				logError("Invalid event type");
				return false;

			}
			formDetails.fieldDetails = allFields;
			if(!submitData(formDetails)) {
				logError("Fail to fill & submit OEE Event form/task");
				return false;
			}
			logInfo("Filled & submitted OEE Event(Dynamic Flow) form/task");
			return true;
		}catch(Exception e) {
			logError("Failed to fill & submit OEE Event(Dynamic flow) form/task - "+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to find Column Index for the column present in Summary form element's Grid
	 * @author hingorani_a
	 * @param columnName Column name whose Index you need. Use Class SUMMARY_TABLE_HEADER to pass column name.
	 * This Index is in turn used in creating dynamic xpaths. 
	 * @return int This returns value of column index. 
	 * Returns 0, if column is not found
	 */
	public int getColumnIndex(String columnName) {
		int columnIndex = 0; 
		try {

			FormDesignerPage fdp = new FormDesignerPage(driver);
			for(WebElement column : fdp.SummryColmnNamesTbl) {
				if(controlActions.perform_CheckIfElementTextContains(column, columnName)) 
				{
					columnIndex++;
					break;
				}
				else if (controlActions.perform_CheckIfElementTextEquals(column, "")) {
					//Do nothing
				}
				else {
					columnIndex++;
				}
			}		
			logInfo("For column : " + columnName + " the index is : " + columnIndex);
			return columnIndex;
		}
		catch(Exception e) {
			logError("Failed to get index value for column : " + columnName + " - "
					+ e.getMessage());
			return columnIndex;
		}	
	}

	/**
	 * This method is used to get data for the Section and the column we mention
	 * @author hingorani_a
	 * @param columnName Column name whose Index you need. Use Class SUMMARY_TABLE_HEADER to pass column name.
	 * @param sectionName The value you would like to use to get for a Section 
	 * @return String This returns value found by intersecting for Section and the Column mentioned
	 */
	public String getSummaryForSecDetails(String columnName, String sectionName) {
		String tempColumnNameXpath = null; 
		WebElement SummryTableSecVal = null;
		String value = null;

		try {
			int columnIndex = getColumnIndex(columnName);
			if(columnIndex == 0) {
				logError("Failed to get correct column index : " + columnIndex + " for column " + columnName);
				return value;
			}
			else {
				tempColumnNameXpath = controlActions.perform_GetDynamicXPath(FormDesignerPageLocator.SUMMRY_TABLE_SEC_VAL, 
						"COLUMNINDEXNO", Integer.toString(columnIndex));

				SummryTableSecVal = controlActions.perform_GetElementByXPath(tempColumnNameXpath, 
						"SECTIONTITLE", sectionName);
				if(SummryTableSecVal!=null) {
					value = SummryTableSecVal.getText();
					logInfo("Value found for Section : " + sectionName + " for column : " + columnName + " is " + value);
					return value;
				}
			}
			return value;
		}
		catch(Exception e) {
			logError("Failed to get value for Section : " + sectionName + " for column : " + columnName + " - "
					+ e.getMessage());
			return value;
		}	
	}


	// might modify this function below to pass the function to be performed e, sum, average, % , count etc.
	/**
	 * This method is used to verify Aggregate field result
	 * @author ahmed_tw
	 * @param aggSrcFields
	 * @param aggResultFields
	 * @param whichSrcFields 
	 * @return TRUE, post verifying field Aggregate field result else false
	 */
	public boolean verifyAggFieldsResult_Sum(String aggSrcFields, String aggResultFields, int[] whichSrcFields) {
		WebElement srcfieldIncrease = null;
		WebElement resultfield = null;
		int length = whichSrcFields.length;
		int fieldValToEnter = 28;
		try {
			for (int i = 0 ; i<length; i++ ) {
				//String positionPath = FieldInputs.EXACT_FIELD_INPUT.replaceAll("#n", Integer.toString(positions[i]));
				String increaseValue = FieldInputs.INCREASE_FILEDVAL.replaceAll("#n", Integer.toString(whichSrcFields[i]));
				//System.out.println(positionPath);
				srcfieldIncrease = controlActions.perform_GetElementByXPath(increaseValue, "FIELD_NAME",aggSrcFields);
				for (int j = 0 ; j<fieldValToEnter; j++)
					srcfieldIncrease.click();
			}

			WebElement xyz = controlActions.perform_GetElementByXPath(FieldInputs.FIELD_LABEL, "FIELD_NAME",aggSrcFields);
			System.out.println(xyz);
			xyz.click();

			String aggFieldPath = FieldInputs.EXACT_FIELD_INPUT.replaceAll("#n", Integer.toString(1));
			resultfield = controlActions.perform_GetElementByXPath(aggFieldPath, "FIELD_NAME",aggResultFields);

			String aggResult = resultfield.getAttribute("value");
			int aggregateValue = Integer.parseInt(aggResult);
			System.out.println(aggregateValue);

			if(aggregateValue!=(fieldValToEnter*length)) {
				logInfo("Failed to Verify Aggregate functionality for fields " + aggSrcFields);
				return false;
			}
		} catch (Exception e) {
			logError("Could nt verify Aggregate field result" + e.getMessage());
			return false;
		}

		return true;
	}

	/**
	 * This method is used to verify if the fields in the form are blank or not
	 * @author ahmed_tw
	 * @param fieldName
	 * @return TRUE post verifying fields are blank, else FALSE
	 */
	public boolean verifyFieldIsBlank(String fieldName) {
		try {
			List<WebElement> fields = controlActions.perform_GetListOfElementsByXPath(FieldInputs.INPUT_FIELD, "FIELD_NAME", fieldName);
			for(WebElement field : fields) {
				if(field.getAttribute("aria-valuenow")!=null){
					logInfo("Failed to verify blank field "+fieldName +" it has value - " + field.getAttribute("aria-valuenow"));
					return false;
				}
			}
		} catch (Exception e) {
			logError("Could not Verify blank field - " +fieldName+e.getMessage());
			return false;
		}
		logInfo("Verified that the field is blank - " + fieldName);
		return true;
	}

	/**
	 * This method is used to get value of mentioned Numeric field from the form
	 * @author hingorani_a
	 * @param fieldName The name of the field for which value is needed
	 * @return String This returns value retrieved for the mentioned field
	 */
	public String getFieldValue(String fieldName) {
		String value = null; 

		try {
			List<WebElement> fields = controlActions.perform_GetListOfElementsByXPath(FieldInputs.INPUT_FIELD, "FIELD_NAME", fieldName);
			if(fields.size()==1) {
				value = fields.get(0).getAttribute("aria-valuenow");
				logInfo("Value found is " + value + " for field " + fieldName);
			}
		} 
		catch (Exception e) {
			logError("Could not get value of field - " + fieldName
					+ e.getMessage());
		}
		return value;
	}

	/**
	 * This method is used to get value of attachment(s) for mentioned field from the form
	 * @author hingorani_a
	 * @param fieldName The name of the field for which value is needed
	 * @return String This returns attachment(s) value retrieved for the mentioned field
	 */
	public String getAttachmentValue(String fieldName) {
		String value = null; 

		try {
			List<WebElement> fields = controlActions.perform_GetListOfElementsByXPath(FieldInputs.ATTACHMENT_LNK, "FIELD_NAME", fieldName);
			if(fields.size()==1) {
				value = fields.get(0).getAttribute("innerText");
				logInfo("Value found is " + value + " for field " + fieldName);
			}
			else {
				for(WebElement field : fields) {
					value += "|" + field.getAttribute("innerText");
				}
				logInfo("Values found are " + value + " for field " + fieldName);
			}
		} 
		catch (Exception e) {
			logError("Could not get value of field - " + fieldName
					+ e.getMessage());
		}
		return value;
	}

	public List<String> getListOfFieldIDsForField(String fieldName) {
		List<WebElement> DisplayedFieldType;
		List<String> fieldIDs = new ArrayList<String>();
		String fieldID = null;
		try {
			DisplayedFieldType =  controlActions.perform_GetListOfElementsByXPath(FSQABrowserFormsPageLocators.DISPLAYED_FIELD_TYPE,
					"FIELD_NAME", fieldName);

			if(DisplayedFieldType.size() == 1) {
				fieldID = DisplayedFieldType.get(0).getAttribute("data-field");
				fieldIDs.add(fieldID);
			}
			else if(DisplayedFieldType.size() > 1) {
				for(int i=0; i<DisplayedFieldType.size(); i++) {
					fieldID = DisplayedFieldType.get(i).getAttribute("data-field");
					fieldIDs.add(fieldID);
				}
			}

			logInfo("Fields Ids found for " + fieldName + " - " + fieldIDs);
			return fieldIDs;
		}
		catch (Exception e) {
			logError("Failed to get Field Ids for " + fieldName
					+ e.getMessage());	
			return fieldIDs;
		}
	}


	/**
	 * This Method is used to verify Repeat count for a Filed in Form
	 * @author ahmed_tw
	 * @param fieldName - The Name of the field
	 * @param count - Number for times repeated
	 * @return True if the fiends are repeated said no. of times else false.
	 */
	public boolean verifyRepeatCountForField(String fieldName, int count) {
		List<WebElement> fieldLabels ;
		List<WebElement> fieldLabels_correspondingInputBox ;
		try {
			fieldLabels = controlActions.perform_GetListOfElementsByXPath(Labels.FIELD_LABEL, "FIELD_NAME", fieldName);
			fieldLabels_correspondingInputBox = controlActions.perform_GetListOfElementsByXPath(FieldInputs.INPUT_FIELD, "FIELD_NAME", fieldName);

			if(fieldLabels.size()==count && fieldLabels_correspondingInputBox.size()==count ) {
				logInfo("Verified that the field " + fieldName + " is repeated - " + count + " times");
				return true;
			}
		} catch (Exception e) {
			logError("Could Not check if field " + fieldName + " is repeated not - " + count + " times " + e.getMessage());
			return false;
		}
		logInfo("Field - " + fieldName + " is NOT Repeated - " + count + " times");
		return false;
	}

	/**
	 * This Method is used to verify Repeat count for a FiledGroup in Form
	 * @author ahmed_tw
	 * @param fieldName - The Name of the FiledGroup
	 * @param count - Number for times repeated
	 * @return True if the FiledGroups are repeated said no. of times else false.
	 */
	public boolean verifyRepeatCountForGroup(String groupName, int count) {
		List<WebElement> groups = null;
		try {
			groups = controlActions.perform_GetListOfElementsByXPath(Labels.GROUP_LABEL, "GROUP_NAME", groupName);
			if(groups.size()==count) {
				logInfo("Verified that the FieldGroup " + groupName + " is repeated - " + count + " times");
				return true;
			}
		} catch (Exception e) {
			logError("Could Not check if FieldGroup " + groupName + " is repeated not - " + count + " times " + e.getMessage());
			return false;
		}
		logInfo("FieldGroup - " + groupName + " is NOT Repeated - " + count + " times");
		return false;
	}

	public boolean verifyCompleteGroupRepeat(String groupName ) {
		return false;
	}

	/**
	 * This method is used to Partially Fill and then submit then save the form - It fills only any one field and then submits the form 
	 * @author ahmed_tw
	 * @param fieldName - Name of the field to be filled
	 * @param value - The value to be filed for the field
	 * @return True after filling that field and then clicking on the submit button and then saving the form, else false
	 */
	public boolean partialFillSubmitNSave(String fieldName, String value) {
		try {
			if(!setInputFieldValue(fieldName, Arrays.asList(value), null)){
				return false;
			}
			controlActions.clickElement(formsPage.SubmitFormBtn);
			logInfo("Clicked on 'SUBMIT' form button");
			controlActions.perform_waitUntilVisibility(formsPage.IncompleteFormSubmitAlert);
			controlActions.clickElement(formsPage.IncompleteFormSubmitAlertOkBtn);
			Thread.sleep(2000);
			logInfo("The form is partially filled & couldn't be submitted");
			controlActions.clickElement(formsPage.SaveBtn);
			formsPage.Sync();
			logInfo("The form saved sucessfully");
			return true;
		} catch (Exception e) {
			logError("Could not perform partial fill, submit and save" + e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to verify the already filled value of a field in the form
	 * @author ahmed_tw
	 * @param fieldName - The name of field whose value needs t be verified
	 * @param value -  The value which needs to be verified 
	 * @return True if the value is verified else false
	 */
	public boolean verifyFieldValue(String fieldName, String value) {
		WebElement fieldElementInput = null;
		try {
			fieldElementInput = controlActions.perform_GetElementByXPath(FieldInputs.INPUT_FIELD, "FIELD_NAME", fieldName);
			if(fieldElementInput.getAttribute("value").equals(value)) {
				logInfo("Verified field value -" + value + " for field -" + fieldName);
				return true;
			}
		} catch (Exception e) {
			logError("Could not verify the value for field -" +fieldName + e.getMessage());
			return false;
		}
		logInfo("The value -" + value + " for field -" + fieldName + " is NOT Verified");
		return false;
	}


	/**
	 * This method is used to check if the field is marked "Required" i.e * or not while in Form Preview or FSQAForms
	 * @author ahmed_tw
	 * @param fieldName - Name of Field
	 * @return True if the field is Marked as "Required" else false
	 */
	public boolean isFieldRequired(String fieldName) {
		WebElement reqSymbol = null;
		try {
			reqSymbol = controlActions.perform_GetElementByXPath(FSQABrowserFormsPageLocators.FIELD_REQUIRED_ASTERISK, "FIELD_NAME", fieldName);
			if(reqSymbol.isDisplayed()) {
				logInfo("The field -" + fieldName + " is Marked as'Required'");
				return true;
			}
			logInfo("The field -" + fieldName + " is NOT Marked as'Required'");
			return false;
		} catch (Exception e) {
			logError("Could Not check if the field -" + fieldName + " is Marked as 'Required'");
			return false;
		}
	}

	/**
	 * This method is used to add comment in comment box for a field while in Form Preview or FSQAForms
	 * @author ahmed_tw
	 * @param fieldName - Name of the field to which comment needs to be added
	 * @param comment - The comment to be added
	 * @return True after adding comment for the field and False if fails to do so
	 */
	public boolean addCommentForField(String fieldName, String comment) {
		try {
			WebElement commentArea = controlActions.perform_GetElementByXPath(FSQABrowserFormsPageLocators.FIELD_COMMENT, "FIELD_NAME", fieldName);
			controlActions.sendKeys(commentArea, comment);
			logInfo("Successfully Added Comment -" + comment + " for field -" + fieldName);
			return true;
		} catch (Exception e) {
			logError("Could not add Comment -" + comment + " for field -" + fieldName);
			return false;
		}
	}

	/**
	 * This method is used to verify Allow Attachments Functionality for a field in FormPreview mode
	 * @author ahmed_tw
	 * @param fieldName - Name of the field for which Allow Attachments needs to be performed.
	 * @return True after successfully handeling and verifying Allow Attachments for a field, else False
	 */
	public boolean verifyAllowAttachmentsInPreviewForField(String fieldName) {
		try {
			WebElement selectfilesButton = controlActions.perform_GetElementByXPath(FSQABrowserFormsPageLocators.FIELD_SELECT_FILES_BTN, "FIELD_NAME", fieldName);
			controlActions.clickElement(selectfilesButton);
			controlActions.perform_waitUntilVisibility(By.xpath(InPreview.IN_PREVIEW_ATTCHMNTS_ERR_POPUP));
			WebElement popUpOkBtn = controlActions.perform_GetElementByXPath(InPreview.IN_PREVIEW_ATTCHMNTS_ERR_POPUP_OKBTN);
			controlActions.clickElement(popUpOkBtn);
			logInfo("Successfully verified allow attachmets functionality in Form Preview for field -" + fieldName);
			return true;
		} catch (Exception e) {
			logError("Could Not verify the allow attachments functionality in Form Preview"+e.getMessage());
			return false;
		}

	}

	/**
	 * This method is used to add Attachments to a field while filling the form in FSQAForms 
	 * @author ahmed_tw
	 * @param fieldName - Name of the field
	 * @param fileLocation - File Location of the file to be attached
	 * @return True after attaching the file, else false
	 */
	public boolean addAttachmentForField(String fieldName, String fileLocation) {
		try {
			WebElement selectfilesButton = controlActions.perform_GetElementByXPath(FSQABrowserFormsPageLocators.FIELD_ATTACHMENT_INPUT, "FIELD_NAME", fieldName);
			controlActions.sendKeys(selectfilesButton, fileLocation);

			WebElement uploadStatusDone = controlActions.perform_GetElementByXPath(FSQABrowserFormsPageLocators.FIELD_ATTACHMENT_UPLD_DONE, "FIELD_NAME", fieldName);
			controlActions.perform_waitUntilVisibility(uploadStatusDone);

			logInfo("Successfully Attached the file -" + fileLocation + " for field -" + fieldName);
			return true;
		} catch (Exception e) {
			logError("Could not attach the file -" + fileLocation + " for field - " + fieldName+e.getMessage());
			return false;
		}

	}

	/**
	 * This method is used to verify if the proper hint is displayed for the field in FormPreview and FSQAForms 
	 * @author ahmed_tw
	 * @param fieldName - Name of the field
	 * @param hint - The hint to be verified
	 * @return True if the hint for the field is verified else false
	 */
	public boolean isHintDisplayedForField(String fieldName, String hint) {
		WebElement displayedHint = null ;
		try {
			displayedHint = controlActions.perform_GetElementByXPath(FSQABrowserFormsPageLocators.FIELD_HINT, "FIELD_NAME", fieldName);
			if(displayedHint.isDisplayed()) {
				logInfo("Verified that hint is displayed for field -" + fieldName);
			}else {
				logInfo("Hint is NOT displayed for field -" + fieldName);
				return false;
			}
			if(displayedHint.getText().equals(hint)) {
				logInfo("Verified that correct hint is displayed for field -" + fieldName);
				return true;
			}else {
				logInfo("Correct hint is NOT displayed for field -" + fieldName);
				return false;
			}
		} catch (Exception e) {
			logError("Could Not verify the display of proper hint for the field -" + fieldName + e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to Add correction for the Field by either choosing from predefined corrections or setting a custom correction, and then Choose Yes or No for Mark As Resolved option
	 * in Form Preview or FSQAForms 
	 * @author ahmed_tw
	 * @param fieldName - Field Name
	 * @param customCorrection - Custom Correction to be set
	 * @param predefinedCorrection - Predefined correction to choose
	 * @param markAsResolved - Yes or No value for Mark As Resolved option
	 * @return True after setting the correction and values for mark as resolved and false if fails to do so 
	 */
	public boolean addCorrectionForField(String fieldName, String customCorrection, String predefinedCorrection, String markAsResolved) {
		try {
			if(customCorrection!=null) {
				if(!setCustomCorrectionForField(fieldName, customCorrection))
					return false;
			}
			if(predefinedCorrection != null) {
				if(!setPredefinedCorrectionsForField(fieldName, predefinedCorrection))
					return false;
			}
			if(markAsResolved != null) {
				if(!markAsResolvedForField(fieldName, markAsResolved))
					return false;
			}
			logInfo("Successfully Set the correction and Mark As Resolved for field -" + fieldName);
			return true;

		} catch (Exception e) {
			logError("Could Not Set the correction and Mark As Resolved for field -" + fieldName + e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to set custom correction for the field in FormsPrevied or FSQAForms 
	 * @author ahmed_tw
	 * @param fieldName - Name of Field
	 * @param customCorrection - The correction text
	 * @return True after successfully setting the custom correction and false if fails to do so
	 */
	public boolean setCustomCorrectionForField(String fieldName, String customCorrection) {
		WebElement correctionTextArea = null;
		WebElement predefinedCorrectionInput = null;
		try {
			predefinedCorrectionInput = controlActions.perform_GetElementByXPath(FSQABrowserFormsPageLocators.FIELD_PREFFERED_CORRCTN_INPUT, "FIELD_NAME", fieldName);
			controlActions.clear(predefinedCorrectionInput);
			Thread.sleep(1000);

			correctionTextArea = controlActions.perform_GetElementByXPath(FSQABrowserFormsPageLocators.FIELD_CORRECTION_TXTAREA, "FIELD_NAME", fieldName);
			controlActions.sendKeys(correctionTextArea, customCorrection);

			logInfo("Successfully set the correction as -" + customCorrection+ " for field -" + fieldName);
			return true;


		} catch (Exception e) {
			logError("Could Not set the correction as -" + customCorrection+ " for field -" + fieldName + e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to set predefined correction for the field in FormsPrevied or FSQAForms
	 * @author ahmed_tw
	 * @param fieldName - name of the field 
	 * @param predefinedCorrection - The option to choose from predefined corrections
	 * @return True after successfully setting the predefined corrections. False If fails to do so
	 */
	public boolean setPredefinedCorrectionsForField(String fieldName, String predefinedCorrection) {
		WebElement correctionTextArea = null;
		WebElement predefinedCorrectionInput = null;
		try {
			correctionTextArea = controlActions.perform_GetElementByXPath(FSQABrowserFormsPageLocators.FIELD_CORRECTION_TXTAREA, "FIELD_NAME", fieldName);
			controlActions.clear(correctionTextArea);

			Thread.sleep(1000);

			predefinedCorrectionInput = controlActions.perform_GetElementByXPath(FSQABrowserFormsPageLocators.FIELD_PREFFERED_CORRCTN_INPUT, "FIELD_NAME", fieldName);
			controlActions.sendKeys(predefinedCorrectionInput, predefinedCorrection);

			controlActions.actionEnter();
			logInfo("Succesfully set the predefined correction option - " + predefinedCorrection+ " for field -" + fieldName);
			return true;


		} catch (Exception e) {
			logError("Could NOT set the predefined correction option - " + predefinedCorrection+ " for field -" + fieldName + e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to set Mark as Resolved Yes or No for the field in FormsPrevied or FSQAForms
	 * @author ahmed_tw
	 * @param fieldName - FieldName
	 * @param yesOrNo - Yes or No Option to set 
	 * @return True after setting value for Mark As Resolved. False if value is not set
	 */
	public boolean markAsResolvedForField(String fieldName, String yesOrNo) {
		WebElement resolvedOption = null;
		try {

			if(yesOrNo.equals("Yes"))
				resolvedOption = controlActions.perform_GetElementByXPath(FSQABrowserFormsPageLocators.FIELD_MARK_AS_RESOLVED_YES, "FIELD_NAME", fieldName);
			else
				resolvedOption = controlActions.perform_GetElementByXPath(FSQABrowserFormsPageLocators.FIELD_MARK_AS_RESOLVED_NO, "FIELD_NAME", fieldName);

			controlActions.clickElement(resolvedOption);

			logInfo("Successfully Set the value as -" + yesOrNo + " for field -" + fieldName);
			return true;

		} catch (Exception e) {
			logError("Could NOT Set the value as -" + yesOrNo + " for field -" + fieldName+e.getMessage());
			return false;	
		}
	}


	/**This method is used to verify if Min/Max values are correctly displayed along the Numeric Field if 'Show Min/Max' setting is ON for that Numeric Field 
	 * @author ahmed_tw
	 * @param fieldName [String ] : Field Name
	 * @param minValue [String] : Min value
	 * @param maxValue [String] : Max value
	 * @return [boolean] : True if the values are displayed else false.
	 */
	public boolean verifyShowMinMaxForNumField(String fieldName, String minValue, String maxValue) {
		try {
			if(verifyShowMinForNumField(fieldName, minValue)&&verifyShowMaxForNumField(fieldName, maxValue)) {
				logInfo("Verified Min/Max Labels with proper values");
				return true;
			}
		} catch (Exception e) {
			logError("Could NOT verify Min/Max  Labels with proper values" + e.getMessage());
			return false;
		}
		logInfo("Min/Max labels with proper values was NOT verified");
		return false;
	}

	/**This method is used to verify if Min value is correctly displayed along the Numeric Field if 'Show Min/Max' setting is ON for that Numeric Field 
	 * @author ahmed_tw
	 * @param fieldNam [String] : Field Name
	 * @param minValue [String] : Min value
	 * @return [boolean] : True if the value is correctly displayed else false.
	 */
	public boolean verifyShowMinForNumField(String fieldName, String minValue) {
		WebElement minLabel = null;
		try {
			String fieldLabel = FSQABrowserFormsPageLocators.MIN_MAX_TARGET_LBL.replaceAll("FIELD_NAME", fieldName);
			minLabel = controlActions.perform_GetElementByXPath(fieldLabel, "MIN_MAX_TARGET", "Min");
			if((minLabel.getText().substring(minLabel.getText().length()-minValue.length())).equals(minValue)) {
				logInfo("Verified Min: label for value -" + minValue);
				return true;
			}
		} catch (Exception e) {
			logError("Could not verify Show Min for Numeric field -" + e.getMessage());
			return false;
		}
		logInfo("Failed verification for Min: label with value -" + minValue);
		return false;
	}

	/**This method is used to verify if Max value is correctly displayed along the Numeric Field if 'Show Min/Max' setting is ON for that Numeric Field 
	 * @author ahmed_tw
	 * @param fieldNam [String] : Field Name
	 * @param maxValue [String] : Max value
	 * @return [boolean] : True if the value is correctly displayed else false.
	 */
	public boolean verifyShowMaxForNumField(String fieldName, String maxValue) {
		WebElement maxLabel = null;
		try {
			String fieldLabel = FSQABrowserFormsPageLocators.MIN_MAX_TARGET_LBL.replaceAll("FIELD_NAME", fieldName);
			maxLabel = controlActions.perform_GetElementByXPath(fieldLabel, "MIN_MAX_TARGET", "Max");
			if((maxLabel.getText().substring(maxLabel.getText().length()-maxValue.length())).equals(maxValue)) {
				logInfo("Verified Max: label for value -" + maxValue);
				return true;
			}
		} catch (Exception e) {
			logError("Could not verify Show Max for Numeric field -" + e.getMessage());
			return false;
		}
		logInfo("Failed verification for Max: label with value -" + maxValue);
		return false;
	}

	/**This method is used to verify if Target value is correctly displayed along the Numeric Field if 'Show Target' setting is ON for that Numeric Field 
	 * @author ahmed_tw
	 * @param fieldNam [String] : Field Name
	 * @param targetValue [String] : Target value
	 * @return [boolean] : True if the value is correctly displayed else false.
	 */
	public boolean verifyShowTargetForNumField(String fieldName, String targetValue) {
		WebElement targetLabel = null;
		try {
			String fieldLabel = FSQABrowserFormsPageLocators.MIN_MAX_TARGET_LBL.replaceAll("FIELD_NAME", fieldName);
			targetLabel = controlActions.perform_GetElementByXPath(fieldLabel, "MIN_MAX_TARGET", "Target");
			if((targetLabel.getText().substring(targetLabel.getText().length()-targetValue.length())).equals(targetValue)) {
				logInfo("Verified Target: label for value -" + targetValue);
				return true;
			}
		} catch (Exception e) {
			logError("Could not verify Show Target for Numeric field -" + e.getMessage());
			return false;
		}
		logInfo("Failed verification for Target: label with value -" + targetValue);
		return false;
	}

	/**This method is used to click Submit button and then Ok for Alert confirming to submit the form.
	 * @author ahmed_tw
	 * @return True after submitting the form, false if fails to to so.
	 */
	public  boolean clickSubmitThenAlertOkBtn() {
		try {
			controlActions.clickElement(formsPage.SubmitFormBtn);
			formsPage.Sync();
			logInfo("Clicked on 'SUBMIT' form button");
			controlActions.clickElement(formsPage.SubmissionOKBtn);
			formsPage.Sync();
			logInfo("The form is filled & submitted sucessfully");
		} catch (Exception e) {
			logError("Could Not Save Form -" + e.getMessage());
			return false;
		}
		return true;
	}


	/**This method is used to increment Numeric field value using the increment button till the desired value.
	 * @author ahmed_tw
	 * @param fieldName [String] : Num Field Name
	 * @param value [int] : Value till which the field has to be incremented.
	 * @return [boolean] : True after incrementing the value to that level, else false.
	 */
	public boolean incrementNumericFieldsValue(String fieldName, int value, int noOfSameNumFields) {
		WebElement srcFieldIncrease = null;
		try {
			for (int i = 1; i <=noOfSameNumFields; i++) {
				String increaseValue = FieldInputs.INCREASE_FILEDVAL.replaceAll("#n", Integer.toString(i));
				srcFieldIncrease = controlActions.perform_GetElementByXPath(increaseValue, "FIELD_NAME",fieldName);
				for (int j = 0 ; j<value; j++)
					srcFieldIncrease.click();
			}
			logInfo("Incremented the numeric field -" + fieldName + " value to- " + value);	
			return true;
		} catch (Exception e) {
			logError("Could Not increment the numeric field value " + e.getMessage());
			return false;
		}
	}

	/**This method is used to enter value in Identifier field
	 * @author ahmed_tw
	 * @param fieldName [String] - Name of Identifier Field
	 * @param value [String] - Value to be entered in identifier field
	 * @return [boolean] - True post entering the value in identifier field, false if fails to do so.
	 */
	public boolean enterValueInIdentifierField(String fieldName, String value) {
		try {
			String toUseXpath = FieldInputs.EXACT_FIELD_INPUT.replaceAll("#n", "1");
			WebElement idFieldInput = controlActions.perform_GetElementByXPath(toUseXpath, "FIELD_NAME", fieldName);
			idFieldInput.click();
			idFieldInput.sendKeys(value);
			logInfo("Entered value in Identifier Field -" + fieldName + " value to- " + value);
			//			controlActions.actionSendKeys(idFieldInput, value);
			return true;
		} catch (Exception e) {
			logError("Could not enter value in identifier field " + e.getMessage());
			return false;
		}
	}


	/**This method is used to verify "Show Points Earned" setting i.e Points Earned is visible. 
	 * @author ahmed_tw
	 * @param fieldName [String] : Name of field
	 * @param pointsEarned [String] : Points Earned
	 * @return True after verifying points earned for the field else false.
	 */
	public boolean verifyShowPointsEarnedForField(String fieldName, String pointsEarned) {
		try {
			WebElement ptsErn = controlActions.perform_GetElementByXPath(FSQABrowserFormsPageLocators.POINTS_EARNED, "FIELD_NAME", fieldName);
			if(ptsErn.getText().equals(pointsEarned)) {
				logInfo("Verified Points Earned as -" + pointsEarned + " for field -" + fieldName);
				return true;
			}
			logInfo("Failed verification for Points Earned as -" + pointsEarned + " for field -" + fieldName);
			return false;

		} catch (Exception e) {
			logError("Could not verify Points Earned for field -" + fieldName);
			return false;
		}
	}

	/**This method is used to verify "Show Points Possible" setting i.e Points Possible is visible. 
	 * @author ahmed_tw
	 * @param fieldName [String] : Name of field
	 * @param pointsPossible [String] : Points Earned
	 * @return True after verifying points possible for the field else false.
	 */
	public boolean verifyShowPointsPossibleForField(String fieldName, String pointsPossible) {
		try {
			WebElement ptsPsbl = controlActions.perform_GetElementByXPath(FSQABrowserFormsPageLocators.POINTS_POSSIBLE, "FIELD_NAME", fieldName);
			if(ptsPsbl.getText().equals(pointsPossible)) {
				logInfo("Verified Points Possible as -" + pointsPossible + " for field -" + fieldName);
				return true;
			}
			logInfo("Failed verification for Points Possible as -" + pointsPossible + " for field -" + fieldName);
			return false;

		} catch (Exception e) {
			logError("Could not verify Points Possible for field -" + fieldName);
			return false;
		}
	}
	/**
	 * This method is used to enter values in Select Multiple or Select One field, in Preview and FSQAForms
	 * @param fieldName [String] : Name of Select Multiple or Select One Field
	 * @param values [List<String>] : Values to be set for 
	 * @return True after entering the values in Select Multiple or Select One Field, false if fails to do so.
	 */
	public boolean setValuesForSelectMultipleOrSelectOne(String fieldName, List<String> values) {
		try {
			String toUseXpath = FieldInputs.EXACT_FIELD_INPUT.replaceAll("#n", "1");
			WebElement SMOrSOFieldInput = controlActions.perform_GetElementByXPath(toUseXpath, "FIELD_NAME", fieldName);
			for(String val : values) {
				controlActions.sendKeys(SMOrSOFieldInput, val); controlActions.actionEnter();
			}
			logInfo("Entered value in Select Multiple -" + fieldName + " value to- " + values);
			return true;
		} catch (Exception e) {
			logError("Could not enter value in Select Multiple field " + e.getMessage());
			return false;
		}
	}

	/**This method is used to click Expand (+) button for a field to unfold comments, attachments etc for that field
	 * @author ahmed_tw
	 * @param fieldName [String] : Name of the field
	 * @return [boolean] : True after Clicking on the Expand (+) button, else false.
	 */
	public boolean clickExpandForField(String fieldName) {
		try {
			WebElement ExpandField = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.EXPAND_COLLAPSE_BTN,"FIELDNAME", fieldName);
			controlActions.clickElement(ExpandField);
			logInfo("Clicked on Expand Field button");
			return true;
		} catch (Exception e) {
			logError("Could Not Click on the Expand button for Field" + e.getMessage());
			return false;
		}
	}

	/**This method is used to set value in Paragraph Text Field 
	 * @author ahmed_tw
	 * @param fieldName [String] : Name of the Paragraph Text Field
	 * @return [boolean] : True after setting value in Paragraph Text Field, false if fails to do so.
	 */
	public boolean setParagraphTextFieldValue(String fieldName, String value) {
		try {
			WebElement pTextInput = controlActions.perform_GetElementByXPath(FieldInputs.EXACT_PARA_TEXT_INPUT,"FIELD_NAME", fieldName);
			pTextInput.sendKeys(value);
			logInfo("Successfully set value- " + value + " in paragraph texh field -" + fieldName);
			return true;
		}catch (Exception e) {
			logError("Cuould Not set value- " + value + " in paragraph texh field -" + fieldName + e.getMessage());
			return false;
		}

	}

	/**This method is used to verify comment for a field.
	 * @author ahmed_tw
	 * @param fieldName  [String] : Name of Field 
	 * @param comment [String] : Comment to verify
	 * @return [boolean] : True if the comment is verified else, false.
	 */
	public boolean verifyCommentOfField(String fieldName, String comment) {
		try {
			WebElement commentArea = controlActions.perform_GetElementByXPath(FSQABrowserFormsPageLocators.FIELD_COMMENT, "FIELD_NAME", fieldName);
			if(commentArea.getAttribute("value").equals(comment)) {
				logInfo("Successfully verified Comment as -" + comment + " for field -" + fieldName);
				return true;
			}
			logInfo("Failed to verify Comment as -" + comment + " for field -" + fieldName);
			return false;
		} catch (Exception e) {
			logError("Could not add Comment -" + comment + " for field -" + fieldName);
			return false;
		}
	}

	/**This method is used to verify ALL attachments of a field.
	 * @author ahmed_tw
	 * @param fieldName [String] : Name of Field
	 * @param attachmentName [String] : File names which are attached
	 * @return [boolean] : True if the files are attached to the field, else false.
	 */
	public boolean verifyAllAttachmentsForField(String fieldName, List<String> attachmentsName) {
		List<String> attachmentsList1 = new ArrayList<String>();
		List<String> attachmentsList2 = new ArrayList<String>();
		try {
			List<WebElement> attachmentsListElement = controlActions.perform_GetListOfElementsByXPath(FSQABrowserFormsPageLocators.FILED_ATTACHMENTS, "FIELD_NAME", fieldName);
			List<WebElement> attachmentsWithUpldStatusElement = controlActions.perform_GetListOfElementsByXPath(FSQABrowserFormsPageLocators.FIELD_ATCHMNTS_WITH_UPLD_STATUS, "FIELD_NAME", fieldName);

			for (WebElement elm : attachmentsListElement)
				attachmentsList1.add(elm.getText()); 

			for (WebElement elm : attachmentsWithUpldStatusElement) 
				attachmentsList2.add(elm.getText()); 

			if(attachmentsList1.containsAll(attachmentsName) && attachmentsList2.containsAll(attachmentsName)) {
				logInfo("Verified the field - " + fieldName + " has ALL attachments -" + attachmentsName);
				return true;
			}

			logInfo("The field - " + fieldName + " does NOT have ALL attachments -" + attachmentsName);
			return false;
		} catch (Exception e) {
			logError("Could not verify of the field -" + fieldName + " has ALL attachments -" + attachmentsName + e.getMessage());
			return false;
		}
	}

	/**This method is used to verify a single attachment of a field.
	 * @author ahmed_tw
	 * @param fieldName [String] : Name of Field
	 * @param attachmentName [String] : File name which is attached
	 * @return [boolean] : True if the file is attached to the field, else false.
	 */
	public boolean verifySingleAttachmentForField(String fieldName, String attachmentName) {
		List<String> attachmentsList1 = new ArrayList<String>();
		List<String> attachmentsList2 = new ArrayList<String>();
		try {
			List<WebElement> attachmentsListElement = controlActions.perform_GetListOfElementsByXPath(FSQABrowserFormsPageLocators.FILED_ATTACHMENTS, "FIELD_NAME", fieldName);
			List<WebElement> attachmentsWithUpldStatusElement = controlActions.perform_GetListOfElementsByXPath(FSQABrowserFormsPageLocators.FIELD_ATCHMNTS_WITH_UPLD_STATUS, "FIELD_NAME", fieldName);

			for (WebElement elm : attachmentsListElement)
				attachmentsList1.add(elm.getText()); 

			for (WebElement elm : attachmentsWithUpldStatusElement)
				attachmentsList2.add(elm.getText()); 

			if(attachmentsList1.contains(attachmentName) && attachmentsList2.contains(attachmentName)) {
				logInfo("Verified the field - " + fieldName + " has attachment -" + attachmentName);
				return true;
			}

			logInfo("The field - " + fieldName + " does NOT have attachment -" + attachmentName);
			return false;
		} catch (Exception e) {
			logError("Could not verify of the field -" + fieldName + " has attachment -" + attachmentName + e.getMessage());
			return false;
		}
	}


	/**
	 * This method is used to verify predefine correction for the field in FormsPreview or FSQAForms
	 * @author ahmed_tw
	 * @param fieldName [String] :  - name of the field 
	 * @param predefinedCorrection [String] :  The predefined correction value to be verified
	 * @return [boolean] :  True after successfully verifying the predefined correction. False If fails to do so
	 */
	public boolean verifyPredefinedCorrectionValueForField(String fieldName, String predefinedCorrection) {
		WebElement predefinedCorrectionInput = null;
		try {
			predefinedCorrectionInput = controlActions.perform_GetElementByXPath(FSQABrowserFormsPageLocators.FIELD_PREFFERED_CORRCTN_INPUT, "FIELD_NAME", fieldName);
			if(predefinedCorrectionInput.getAttribute("value").equals(predefinedCorrection)) {
				logInfo("Succesfully verified the predefined correction option as - " + predefinedCorrection+ " for field -" + fieldName);
				return true;
			}

			logInfo("Predefined correction option - " + predefinedCorrection+ " was NOT verified for field -" + fieldName);
			return false;
		} catch (Exception e) {
			logError("Could NOT verify the predefined correction option as - " + predefinedCorrection+ " for field -" + fieldName + e.getMessage());
			return false;
		}
	}


	/**
	 * This method is used to verify Mark as Resolved values for the field in FormsPrevied or FSQAForms
	 * @author ahmed_tw
	 * @param fieldName [String] : FieldName
	 * @param yesOrNo [String] : Yes or No Option to set 
	 * @return [boolean] :  True after verifying value of Mark As Resolved. False if value is not verified
	 */
	public boolean verifymarkAsResolvedValueForField(String fieldName, String yesOrNo) {
		WebElement resolvedOption = null;
		String selected ="selected";
		String elementClass;
		try {
			if(yesOrNo.equals("Yes"))
				resolvedOption = controlActions.perform_GetElementByXPath(FSQABrowserFormsPageLocators.FIELD_MARK_AS_RESOLVED_YES, "FIELD_NAME", fieldName);
			else
				resolvedOption = controlActions.perform_GetElementByXPath(FSQABrowserFormsPageLocators.FIELD_MARK_AS_RESOLVED_NO, "FIELD_NAME", fieldName);

			elementClass = resolvedOption.getAttribute("class");

			if(elementClass.contains(selected)) {
				logInfo("Verified Mark As resolved values as -" + yesOrNo);
				return true;
			}

			logInfo("Mark As resolved values in Not Verified as -" + yesOrNo);
			return false;
		} catch (Exception e) {
			logError("Could NOT verify the value as -" + yesOrNo + " for field -" + fieldName+e.getMessage());
			return false;	
		}
	}

	public  boolean verifyAllSettingsInPreview_FreeText(String freeTextFieldName) {
		try {
		} catch (Exception e) {
		}
		return true;
	}

	/** This method is used to search Resource and display no data found popUp
	 * @author jadhav_akan
	 * @param resourceName: Name of Resource that needs to be searched
	 * @return boolean status
	 * IF Successfully no Data found popUp is displayed for searched Resource then True else False
	 */	
	public boolean searchSelectResourceNoData(String resourceName) {
		try {
			controlActions.clickElement(formsPage.SelectResourceDdl);
			WebElement	filter = formsPage.DropDownListFilter;
		    controlActions.sendKeys(filter, resourceName);
			
			WebElement noDataPopup = formsPage.NoDataFound;
			
				if(noDataPopup.isDisplayed()) {
					logInfo("Successfully no Data found popup is displayed for- " + resourceName);
				}
		    return true;
		    }
		catch(Exception e) {
			logError("Failed to display no data found popup" + e.getMessage());
			return false;
		}
		
	}
	public boolean fillAndSubmitData(FormDetails formDetails) {
		HashMap<String, List<String>> details;
		String fieldName;
		List<String> fieldValue;
		List<FieldProperties> fieldProperties;

		try {
			details = formDetails.fieldDetails;
			fieldProperties = formDetails.fieldProperties;

			formsPage.Sync();
			if(formDetails.locationName != null ||  formDetails.resourceName != null) {
				if(!selectLocationResource(formDetails.locationName, formDetails.resourceName)) {
					logError("Failed to select location/resource");
					return false;
				}
			}

			if(formDetails.formLevelAttachmenttCheck) {
				if(!formsPage.addFormLevelAttachment(formDetails.uploadFormLevelFilePath)){
					logError("Fail to add attachment at form level");
					return false;
				}
			}
			if(formDetails.fieldDetails!=null) {
				for (Map.Entry<String, List<String>> entry : details.entrySet()) {
					fieldName = entry.getKey();
					fieldValue = entry.getValue();
					if(!fillData(fieldName, fieldValue, fieldProperties, formDetails.newFieldValue)) {
						logError("Failed to fill form");
						return false;
					}
				}
				logInfo("Filled data in provided field(s)");
			}
			if(formDetails.isSubmit) {
				controlActions.clickElement(formsPage.SubmitFormBtn);
				formsPage.Sync();
				logInfo("Clicked on 'SUBMIT' form button");
				controlActions.clickElement(formsPage.SubmissionOKBtn);
				formsPage.Sync();
				logInfo("The form is filled & submitted sucessfully");
			}
			
			return true;
		}catch (Exception e) {
			logError("Failed to fill data and submit form - "+e.getMessage());
			return false;
		}
	}
	/** This method is used to Select Resource in Select Resource DropDown
	 * @author jadhav_akan
	 * @param resourceName: Name of Resource that needs to be search and Select
	 * @return boolean status
	 * IF Resource is Selected then True else False
	 */	
	public boolean selectResource(String resourceName) {
		WebElement resource;
		String selectedResource;
		try {
			if(controlActions.isElementDisplayedOnPage(formsPage.SelectResourceDdl)) {
				controlActions.clickElement(formsPage.SelectResourceDdl);
				resource = controlActions.perform_GetElementByXPath(FSQABrowserFormsPageLocators.LIST_VALUE, "NAME", resourceName);
				controlActions.clickElement(resource);
				formsPage.Sync();
				logInfo("Selected Resource - "+resourceName);
			}else {
				selectedResource = formsPage.SelectedResourceLbl.getText();
				logInfo("Resource - '"+selectedResource+"' is already selected");
			}
			return true;
		}catch (Exception e) {
			logError("Failed to select Resource - "+e.getMessage());	
			return false;
		}
	}
	
	/**
	 *This method is used to verify already selected values for Multiple Select Field value
	 * @author ahmed_tw
	 * @param fieldName [String] - Name of Multiple Select Field value
	 * @param values [List<String>] - List of Values already selected for multiple select field  
	 * @return [boolean] : True is the values are verified else, false
	 */
	public boolean verifySelectMultipleFieldValues(String fieldName, List<String> values) {
		List<String> SMValuesReceived = new ArrayList<String>();
		try {
			List<WebElement> SMFieldValues = controlActions.perform_GetListOfElementsByXPath(FieldInputs.SELECTMUL_VAL_VERIFY, "FIELD_NAME", fieldName);
			
			for(WebElement element : SMFieldValues) {
				SMValuesReceived.add(element.getText());
			}
			
			System.out.println(SMValuesReceived);
			System.out.println(values);
			if(!SMValuesReceived.containsAll(values)) {
				logInfo("Failed to verify already present values " + values +" for Select Multiple Field " + fieldName);
				return false;
			}

			logInfo("Verified already present values " + values +" for Select Multiple Field " + fieldName);
			return true;
		} catch (Exception e) {
			logError("Could not verify already present values " + values +" for Select Multiple Field " + fieldName + e.getMessage());
			return false;
		}
	}
	
	/**This method is used to verify value in Paragraph Text Field 
	 * @author ahmed_tw
	 * @param fieldName [String] : Name of the Paragraph Text Field
	 * @return [boolean] : True after verifying value in Paragraph Text Field, false if fails to do so.
	 */
	public boolean verifyParagraphTextFieldValue(String fieldName, String value) {
		try {
			WebElement pTextInput = controlActions.perform_GetElementByXPath(FieldInputs.EXACT_PARA_TEXT_INPUT,"FIELD_NAME", fieldName);
			if(pTextInput.getAttribute("value").equals(value)){
				logInfo("Successfully verified value- " + value + " in paragraph texh field -" + fieldName);
				return true;
			}
			logInfo("Failed to verify value - " + value + " in paragraph texh field -" + fieldName);
			return false;
		}catch (Exception e) {
			logError("Cuould Not verified value- " + value + " in paragraph texh field -" + fieldName + e.getMessage());
			return false;
		}

	}
	
	/**This method is used to set value in Numeric Field 
	 * @author ahmed_tw
	 * @param fieldName [String] : Name of the Numeric Field
	 * @return [boolean] : True after setting value in Numeric Field, false if fails to do so.
	 */
	public boolean enterNumericFieldValue(String fieldName, String value) {
		try {
			WebElement numericInput1 = controlActions.perform_GetElementByXPath(FieldInputs.NUMERIC_FLD_INPUT1,"FIELD_NAME", fieldName);
			WebElement numericInput2 = controlActions.perform_GetElementByXPath(FieldInputs.NUMERIC_FLD_INPUT2,"FIELD_NAME", fieldName);
			numericInput1.click();
			numericInput2.clear();
			numericInput2.sendKeys(value);
			logInfo("Successfully entered value- " + value + " in numeric field -" + fieldName);
			return true;
		}catch (Exception e) {
			logError("Cuould Not enter value- " + value + " in numeric field -" + fieldName + e.getMessage());
			return false;
		}
	}
	
	/**This method is used to enter the value in Numeric when some values is already entered in that field 
	 * @author ahmed_tw
	 * @param fieldName [String] : Name of the Numeric Field
	 * @param value [String] : Value to be Entered
	 * @return [boolen] : True after re entering the value, else false
	 */
	public boolean reEnterNumericFieldValue(String fieldName, String value) {
		try {
			WebElement numericInput1 = controlActions.perform_GetElementByXPath(FieldInputs.NUMERIC_FLD_INPUT1,"FIELD_NAME", fieldName);
			WebElement numericInput2 = controlActions.perform_GetElementByXPath(FieldInputs.NUMERIC_FLD_INPUT2,"FIELD_NAME", fieldName);
			numericInput1.click();
			numericInput2.clear();
			numericInput1.click();
			numericInput2.sendKeys(value);
			logInfo("Successfully entered value- " + value + " in numeric field -" + fieldName);
			return true;
		}catch (Exception e) {
			logError("Cuould Not enter value- " + value + " in numeric field -" + fieldName + e.getMessage());
			return false;
		}
	}

	
	/**This method is used to clear numeric field 
	 * @author ahmed_tw
	 * @param fieldName [String] : Name of the Numeric Field
	 */
	public void clearNumericInput(String fieldName) {
		try {
			WebElement numericInput1 = controlActions.perform_GetElementByXPath(FieldInputs.NUMERIC_FLD_INPUT1,"FIELD_NAME", fieldName);
			WebElement numericInput2 = controlActions.perform_GetElementByXPath(FieldInputs.NUMERIC_FLD_INPUT2,"FIELD_NAME", fieldName);
			numericInput1.click();
			numericInput2.clear();
			clickSafetyChainLogo();
			logInfo("Successfully cleared numeric field - " + fieldName);
		}catch (Exception e) {
			logError("Cuould Not clear numeric field - " + fieldName + e.getMessage());
		}
	}
	/**This method is used to verify value from Aggregate Field 
	 * @author ahmed_tw
	 * @param fieldName [String] : Name of the Aggregate Field
	 * @return [boolean] : True after verifying value in Aggregate Field, false if fails to do so.
	 */
	public boolean verifyAggregateFieldValue(String fieldName, String value) {
		try {
			String aggFieldPath = FieldInputs.EXACT_FIELD_INPUT.replaceAll("#n", Integer.toString(1));
			WebElement resultfield = controlActions.perform_GetElementByXPath(aggFieldPath, "FIELD_NAME",fieldName);
			//String aggResult = resultfield.getAttribute("value");
			if(resultfield.getAttribute("value").equals(value)){
				logInfo("Successfully verified value- " + value + " in aggregate field -" + fieldName);
				return true;
			}
			logInfo("Failed to verify value - " + value + " in aggregate field -" + fieldName);
			return false;
		}catch (Exception e) {
			logError("Cuould Not verified value- " + value + " in aggregate field -" + fieldName + e.getMessage());
			return false;
		}
	}
	
	/**
	 * This  method is used to click on SacetyChain Logo
	 * @author ahmed_tw
	 */
	public void clickSafetyChainLogo() {
		try {
			WebElement scLogo = driver.findElement(By.id("scs-logo"));
			scLogo.click();
			logInfo("Successfully clicked on SC Logo");
		}catch (Exception e) {
			logError("Cuould Not clicked on SC LOGO" + e.getMessage());
		}
	}
	
	/**
	 * This Method is used to click save Button
	 * @return [boolean] : True after clicking the save button, else false
	 */
	public boolean clickSaveButton(){
		try {
			controlActions.clickElement(formsPage.SaveBtn);
			formsPage.Sync();
			logInfo("Clicked Save button");
			return true;
		} 
		catch (Exception e) {
			logError("Failed to click Save button " + e.getMessage());
			return false;
		}
	}
	public static class FormDetails{
		public HashMap<String, List<String>>fieldDetails = null;
		public List<FieldProperties> fieldProperties;
		public List<String> fieldData;
		public String locationName;
		public String resourceName;
		public boolean isSubmit = true;
		public boolean doNotSaveOrSubmit = false;
		public boolean formLevelAttachmenttCheck = false;
		public String uploadFormLevelFilePath = filePath;
		public boolean newFieldValue = false;
		public boolean isDelete = false;

	}

	public static class FieldProperties{
		public String fieldName;
		public String fieldID;
		public boolean complianceStatusCheck = false;
		public String complianceStatus;
		public boolean allowCorrectionsCheck = false;
		public boolean allowPreCorrectionsCheck = false;
		public String allowCorrectionsValue = "1";
		public boolean isResolvedCheck = false;
		public String isResolvedStatus = "Yes";
		public boolean allowCommentsCheck = false;
		public String commentText = "Test Comments";
		public boolean allowAttachmenstCheck = false;
		public String uploadFilePath = filePath;
	}

	public static class FieldInputs{
		public final static String INPUT_FIELD =  "//field-template/div[div/label[normalize-space(text())='FIELD_NAME']]//input[@class='k-input' or contains(@class,'actualInput') or @class='k-input k-readonly'][1]";

		//public final static String INPUT_FIELD =  "//field-template/div[div/label[normalize-space(text())='FIELD_NAME']]//input[@class='k-input' or contains(@class,'actualInput')][1]";
		public final static String TEXTAREA_FIELD = "//field-template/div[div/label[normalize-space(text())='FIELD_NAME']]//textarea";
		//public final static String INPUT_FIELD = "//field-template/div[div/label[normalize-space(text())='FIELD_NAME']]//input[1]";

		public final static String EXACT_FIELD_INPUT = "(//field-template/div//label[normalize-space(text())='FIELD_NAME']/../..//div[2]//input[1])[#n]";
		public final static String EXACT_PARA_TEXT_INPUT = "(//field-template/div//label[normalize-space(text())='FIELD_NAME']/../..//div[2]//textarea[1])[1]";
		public final static String INCREASE_FILEDVAL= "(//field-template/div//label[normalize-space(text())='FIELD_NAME']/../..//div[2]//span//span[@class='k-link k-link-increase'])[#n]";
		public static final String FIELD_LABEL = "//field-template/div//label[normalize-space(text())='FIELD_NAME']";
		public final static String ATTACHMENT_LNK = "//*[@id='scs-form-level']//div[div/label[normalize-space(text())='FIELD_NAME']]//a[@ng-bind='attachment.name']";
		public final static String NON_CMPLAINT_ICON = "//*[@id='scs-form-level']//div[div/label[normalize-space(text())='FIELD_NAME']]//i[@class='fa fa-close']";
		public final static String CMPLAINT_ICON = "//*[@id='scs-form-level']//div[div/label[normalize-space(text())='FIELD_NAME']]//i[@class='fa fa-check']";
		public final static String NUMERIC_FLD_INPUT1 = "//label[contains(text(),'FIELD_NAME')]/../following-sibling::div//input[1]";
		public final static String NUMERIC_FLD_INPUT2 = "//label[contains(text(),'FIELD_NAME')]/../following-sibling::div//input[2]";
		
		public final static String SELECTMUL_VAL_VERIFY = "//field-template/div[div/label[normalize-space(text())='FIELD_NAME']]//li/span[1]";
	}

	public static class OEEeventType{
		public final static String ACTUAL_PRODUCTION_START = "Actual Production Start";
		public final static String THROUGHPUT_QUANTITY = "Throughput Quantity";
		public final static String UNPLANNED_DOWNTIME = "Unplanned Downtime";
		public final static String QUALITY = "Quality";
		public final static String ACTUAL_PRODUCTION_END = "Actual Production End";
	}

	public static class Labels{
		public static final String FIELD_TEMPLATE = "//field-template/div[div/label[normalize-space(text())='FIELD_NAME']]";
		public static final String FIELD_TEMP_IN_GP = "//div[@class='scs-fieldgroup-fields']/div[contains(text(),'GROUP_NAME')]//..//field-template/div[div/label[normalize-space(text())='FIELD_NAME']]";
		public static final String FIELD_LABEL = "//field-template/div//label[normalize-space(text())='FIELD_NAME']";
		public static final String GROUP_LABEL = "//div[@class='scs-fieldgroup-fields']//div[text()='GROUP_NAME']";
		public static final String FIELD_LABEL_INSIDE_GP = "//div[@class='scs-fieldgroup-fields']//div[text()='GROUP_NAME']//..//field-template/div//label[normalize-space(text())='FIELD_NAME']";
	}

	public static class InPreview{
		public static final String IN_PREVIEW_ATTCHMNTS_ERR_POPUP = "//div[@id='scs-popup']//div[text()='Error']";
		public static final String IN_PREVIEW_ATTCHMNTS_ERR_POPUP_OKBTN = "//div[@id='scs-popup']/following-sibling::div//button[text()='OK']";
	}

}
