package com.project.safetychain.pcapp.pageobjects;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class FileOperations extends CommonScreen{

	public FileOperations() {
		super();
	}

	public static String pcAppDataPath = System.getProperty("user.dir") + "\\test-data-files\\PCAppData\\CreatedData.xlsx";

	public AvailableFormDetails getCreatedData() {
		AvailableFormDetails availableData = null;
		List<String> resources;
		InputStream fis;
		Workbook wb = null;
		Sheet sheet = null;
		String fileDetails = null;
		String currentCellData, currentLocation;
		int lastClm,lastRow;
		try {
			fileDetails = pcAppDataPath;
			availableData = new AvailableFormDetails();
			availableData.Location_Resource = new LinkedHashMap<String,List<String>>();
			fis = new FileInputStream(fileDetails);
			wb = new XSSFWorkbook(fis);  

			sheet= wb.getSheet("Location_Resource");
			lastClm = sheet.getRow(0).getLastCellNum();
			lastRow = sheet.getLastRowNum();

			for(int i=1;i<lastClm;i++) {
				currentCellData = sheet.getRow(0).getCell(i).getStringCellValue();
				currentLocation = currentCellData;
				resources = new ArrayList<String>();
				for(int j=1;j<lastRow+1;j++) {
					if(sheet.getRow(j).getCell(i)==null){
						break;
					}else {
						currentCellData = sheet.getRow(j).getCell(i).getStringCellValue();
						resources.add(currentCellData);
					}
				}
				availableData.Location_Resource.put(currentLocation, resources);
			}

			availableData.locationInstanceValue1 = sheet.getRow(0).getCell(1).getStringCellValue();
			availableData.resourceInstanceValue1 = sheet.getRow(1).getCell(1).getStringCellValue();
			availableData.resourceInstanceValue2 = sheet.getRow(2).getCell(1).getStringCellValue();

			sheet= wb.getSheet("Forms");
			currentCellData = sheet.getRow(1).getCell(1).getStringCellValue();
			availableData.CheckForm = currentCellData;
			currentCellData = sheet.getRow(2).getCell(1).getStringCellValue();
			availableData.QuestionnaireForm = currentCellData;
			currentCellData = sheet.getRow(3).getCell(1).getStringCellValue();
			availableData.Audit_Form1 = currentCellData;
			currentCellData = sheet.getRow(4).getCell(1).getStringCellValue();
			availableData.Audit_Form2 = currentCellData;
			wb.close();
			fis.close();
			logInfo("Got available data successfully ");
			return availableData;
		}catch (Exception e) {
			logError("Failed to get available data - "+e.getMessage());	
			return availableData;
		} 
	}

	public AvailableTaskDetails getCreatedTaskData() {
		AvailableTaskDetails availableData = null;
		InputStream fis;
		Workbook wb = null;
		Sheet sheet = null;
		String fileDetails = null;
		String currentCellData;
		try {
			fileDetails = pcAppDataPath;
			availableData = new AvailableTaskDetails();
			fis = new FileInputStream(fileDetails);
			wb = new XSSFWorkbook(fis);  
			sheet= wb.getSheet("Tasks");
			currentCellData = sheet.getRow(1).getCell(1).getStringCellValue();
			availableData.pastDueTask = currentCellData;
			currentCellData = sheet.getRow(2).getCell(1).getStringCellValue();
			availableData.dueTodayTask = currentCellData;
			currentCellData = sheet.getRow(3).getCell(1).getStringCellValue();
			availableData.dueLaterTask = currentCellData;
			currentCellData = sheet.getRow(4).getCell(1).getStringCellValue();
			availableData.noDueDateTask = currentCellData;
			currentCellData = sheet.getRow(5).getCell(1).getStringCellValue();
			availableData.saveTask = currentCellData;
			currentCellData = sheet.getRow(6).getCell(1).getStringCellValue();
			availableData.submitTask = currentCellData;
			currentCellData = sheet.getRow(7).getCell(1).getStringCellValue();
			availableData.workGroupName = currentCellData;
			wb.close();
			fis.close();
			logInfo("Got available data successfully ");
			return availableData;
		}catch (Exception e) {
			logError("Failed to get available data - "+e.getMessage());	
			return availableData;
		} 
	}

	public AvailableUserDetails getCreatedUserData() {
		AvailableUserDetails availableData = null;
		InputStream fis;
		Workbook wb = null;
		Sheet sheet = null;
		String fileDetails = null;
		String currentCellData;
		try {
			fileDetails = pcAppDataPath;
			availableData = new AvailableUserDetails();
			fis = new FileInputStream(fileDetails);
			wb = new XSSFWorkbook(fis);  
			sheet= wb.getSheet("User_Details");
			currentCellData = sheet.getRow(0).getCell(1).getStringCellValue();
			availableData.username = currentCellData;
			currentCellData = sheet.getRow(1).getCell(1).getStringCellValue();
			availableData.password = currentCellData;
			wb.close();
			fis.close();
			logInfo("Got available data successfully ");
			return availableData;
		}catch (Exception e) {
			logError("Failed to get available data - "+e.getMessage());	
			return availableData;
		} 
	}

	public AvailableOtherDetails getOtherData() {
		AvailableOtherDetails availableData = null;
		InputStream fis;
		Workbook wb = null;
		Sheet sheet = null;
		String fileDetails = null;
		String currentCellData;
		try {
			fileDetails = pcAppDataPath;
			availableData = new AvailableOtherDetails();
			fis = new FileInputStream(fileDetails);
			wb = new XSSFWorkbook(fis);  
			sheet= wb.getSheet("Other_Details");
			currentCellData = sheet.getRow(0).getCell(1).getStringCellValue();
			availableData.deviceName = currentCellData;
			wb.close();
			fis.close();
			logInfo("Got available data successfully ");
			return availableData;
		}catch (Exception e) {
			logError("Failed to get available data - "+e.getMessage());	
			return availableData;
		} 
	}

	public boolean setCreatedData(AvailableFormDetails availableData, AvailableTaskDetails availableTaskData, AvailableUserDetails availableUserData, AvailableOtherDetails details) {
		String importFilePath = pcAppDataPath;
		InputStream fis;
		Workbook wb = null;
		Sheet sheet = null;
		Row row = null;
		List<String> resources;
		int locationIndex = 1;
		String locationName;

		try {
			fis = new FileInputStream(importFilePath);
			wb = new XSSFWorkbook(fis);  
			sheet= wb.getSheet("Location_Resource");
			for (Map.Entry<String, List<String>> entry : availableData.Location_Resource.entrySet()) {
				locationName = entry.getKey();
				resources = entry.getValue();
				sheet= wb.getSheet("Location_Resource");
				row  = sheet.getRow(0);
				row.createCell(locationIndex).setCellValue(locationName);
				for(int i=0;i<resources.size();i++) {
					row  = sheet.getRow(i+1);
					row.createCell(locationIndex).setCellValue(resources.get(i));
				}
				locationIndex++;
			}
			sheet= wb.getSheet("Forms");
			sheet.getRow(1).createCell(0).setCellValue("Check");
			sheet.getRow(1).createCell(1).setCellValue(availableData.CheckForm);
			sheet.getRow(2).createCell(0).setCellValue("Questionnaire");
			sheet.getRow(2).createCell(1).setCellValue(availableData.QuestionnaireForm);
			sheet.getRow(3).createCell(0).setCellValue("Audit");
			sheet.getRow(3).createCell(1).setCellValue(availableData.Audit_Form1);
			sheet.getRow(4).createCell(0).setCellValue("Audit");
			sheet.getRow(4).createCell(1).setCellValue(availableData.Audit_Form2);


			sheet= wb.getSheet("Tasks");
			sheet.getRow(1).createCell(0).setCellValue("Past Due Task");
			sheet.getRow(1).createCell(1).setCellValue(availableTaskData.pastDueTask);
			sheet.getRow(2).createCell(0).setCellValue("Due Today Task");
			sheet.getRow(2).createCell(1).setCellValue(availableTaskData.dueTodayTask);
			sheet.getRow(3).createCell(0).setCellValue("Due Later Task");
			sheet.getRow(3).createCell(1).setCellValue(availableTaskData.dueLaterTask);
			sheet.getRow(4).createCell(0).setCellValue("No Due Date Task");
			sheet.getRow(4).createCell(1).setCellValue(availableTaskData.noDueDateTask);
			sheet.getRow(5).createCell(0).setCellValue("Save Task");
			sheet.getRow(5).createCell(1).setCellValue(availableTaskData.saveTask);
			sheet.getRow(6).createCell(0).setCellValue("Submit Task");
			sheet.getRow(6).createCell(1).setCellValue(availableTaskData.submitTask);
			sheet.getRow(7).createCell(0).setCellValue("Workgroup Name");
			sheet.getRow(7).createCell(1).setCellValue(availableTaskData.workGroupName);

			sheet= wb.getSheet("User_Details");
			sheet.getRow(0).createCell(0).setCellValue("User Name");
			sheet.getRow(0).createCell(1).setCellValue(availableUserData.username);
			sheet.getRow(1).createCell(0).setCellValue("Password");
			sheet.getRow(1).createCell(1).setCellValue(availableUserData.password);

			sheet= wb.getSheet("Other_Details");
			sheet.getRow(0).createCell(0).setCellValue("Device Name");
			sheet.getRow(0).createCell(1).setCellValue(details.deviceName);

			FileOutputStream fileOut;
			fileOut = new FileOutputStream(importFilePath);
			wb.write(fileOut); 
			fileOut.close(); 
			logInfo("Set created data successfully ");
			return true;
		}catch(Exception e) {
			logError("Failed to set created data - "+e.getMessage());	
			return false;
		}
	}

	public static class AvailableFormDetails{
		public LinkedHashMap<String, List<String>> Location_Resource;
		public String CheckForm, QuestionnaireForm, Audit_Form1, Audit_Form2;
		public String resourceInstanceValue1,resourceInstanceValue2;
		public String locationInstanceValue1;
	}

	public static class AvailableTaskDetails{
		public String pastDueTask, dueTodayTask, dueLaterTask, noDueDateTask;
		public String saveTask, submitTask;
		public String workGroupName;
	}

	public static class AvailableUserDetails{
		public String username,password;
	}

	public static class AvailableOtherDetails{
		public String deviceName;
	}

}
