package com.project.thread.constants;

import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

//This is used to keep Static objects,variables
public class ThreadConstant {
	
	public WebDriver driver;
	public String browser;
	public String OS;
	public ExtentReports extent;
	public ExtentTest test;
	public String CycleId;
	public String issueKey;
	public int totalTestPassed = 0, totalTestFailed = 0, totalTestSkipped = 0, totalTestCount;

}
