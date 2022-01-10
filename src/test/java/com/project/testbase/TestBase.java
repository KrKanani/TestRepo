package com.project.testbase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import org.apache.commons.codec.binary.Base64;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.IAlterSuiteListener;
import org.testng.ITestContext;
import org.testng.SkipException;
import org.testng.annotations.*;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeSuite;
import org.testng.asserts.SoftAssert;
import org.testng.xml.XmlSuite;

import com.project.safetychain.api.models.support.API_ManageLocation_Wrapper;
import com.project.thread.constants.TC;
import com.project.thread.constants.ThreadConstant;
import com.project.thread.constants.ThreadInst;

import com.project.utilities.Constants;
import com.project.utilities.DynamicTestNGFile;
import com.project.utilities.ExcelReader;
import com.project.utilities.SendMailResults;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.windows.WindowsDriver;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

@Listeners()
public class TestBase implements IAlterSuiteListener {

	// Variable Declaration
	public String externalDate;
	public static String application_url, defaultItemOption, monthlyPayment, erroMsg, Testcase, persona, sheetName,
	path, screenshotPath, stream, skip = null;
	public String browser, OS;
	public static String randomNumber;
	public static String TCGFailureMsg;
	public static String GenericPassword = "SafetyChain123#";
	public static String GenericNewPassword = "SafetyChain1234#";
	public static String GenericEmail;
	public static int genericNumber, lineNumber;
	public static String fullClassName, className, methodName;

	public WebDriver driver;
	public WindowsDriver<WebElement> PCAppDriver;
	protected static AppiumDriver<MobileElement> appiumDriver;
	public String extendReportName, extendReportName1, executionReportPath;
	static SimpleDateFormat formatter = new SimpleDateFormat("_yyyy_MM_dd_HH_mm_ss");
	static Date sysdate = new Date();
	public static ExtentReports extent;
	public static ExtentTest test;
	public static Properties prop = new Properties();
	public static Map<String, List<String>> ExecutionList;
	public static Iterator<Entry<String, List<String>>> executionListIterator;
	public static String CycleId;
	public static String issueKey;
	public static ExcelReader excel;
	public static File f;
	protected static SoftAssert softAssertion = new SoftAssert(); // addedforUIvalidationcases
	public static Instant start;
	public static Instant finish;
	public static long timeElapsed;
	public static String environment;
	public boolean error;
	//	// DOCKER - START
	//	public static String downloadPath = System.getProperty("user.dir") + "/test-data-files/DownloadedDocuments/";
	//	public static String uploadPath = System.getProperty("user.dir") + "/test-data-files/UploadDocuments";
	//	public static String logsPath = System.getProperty("user.dir") + "/log/RunLogs/";
	//	// DOCKER - END
	public static String downloadPath = System.getProperty("user.dir") + "\\test-data-files\\DownloadedDocuments\\";
	public static String uploadPath = System.getProperty("user.dir") + "\\test-data-files\\UploadDocuments";
	public static String logsPath = System.getProperty("user.dir") + "\\log\\RunLogs\\";
	public static String uploadDPTFilePath = System.getProperty("user.dir")
			+ "\\test-data-files\\UploadDocuments\\DPT\\";
	public static String pcAppDataPath = System.getProperty("user.dir")
			+ "\\test-data-files\\DownloadedDocuments\\PCAppData\\CreatedData.xlsx";
	public static boolean createPCPreReqData = false;
	// ************** Application Variables from config.properties file *****
	public static String applicationUrl = null;
	public static String adminUsername = null;
	public static String adminPassword = null;
	public static String adminTimezone = null;
	public static String executionMode = "";
	public static String locationName = null;
	public static String smokePrereqExcel = "SMK_Prereq_Data.xlsx";
	
	public static String pcAppTenantname = null;
	public static String pcAppUsername = null;
	public static String pcAppPassword = null;
	public static String pcAppVersion = null;
	
	public static String mobileAppUsername = null;
	public static String mobileAppPassword = null;
	public static String mobileApp_Tenant = null;
	public static String waynefarms_UserName = null;
	public static String waynefarms_Password = null;
	public static String mobileApp_FNameLName = null;
	public static String mobileAppUsername02 = null;
	public static String mobileAppPassword02 = null;
	
	public static String emailIDTo = null;
	public static String emailIDFrom = null;
	public static String emailIDPassword = null;
	public static String emailIDUsername = null;
	public static String mailServer = null;
	
	public static String pcAppTenantURL = null;
	public static String pcAppSSOTenantname = null;
	public static String pcAppSSOUsername = null;
	public static String pcAppSSOPassword = null;
	public static String pcAppSSOTenantname_DesktopView = null;
	public static String pcAppSSOTenantname_WebView = null;
	public static String test1STAGEUsername = null;
	public static String test1STAGEPassword = null;

	// **************HASH TABLE & ARRAYLIST/LIST DECLARATION*****
	public static Hashtable<String, String> testDataHT = new Hashtable<String, String>();
	public static Hashtable<String, String> configHT = new Hashtable<String, String>();
	public static List<String> ListFieldData;
	public static List<String> ListOfBiReports;
	public static ArrayList<String> arrListPass = new ArrayList<>();
	public static ArrayList<String> arrListFail = new ArrayList<>();
	public static ArrayList<String> arrListSkip = new ArrayList<>();

	// ************** API - Application related common variables *****
	public static String accessToken = null;
	public static String sessionId = null;
	public static String affinityId = null;
	public static String catID = null;
	public static String locationCatID = null;
	public static String customersCatID = null;
	public static String equipmentCatID = null;
	public static String itemsCatID = null;
	public static String suppliersCatID = null;
	public static String plantCatID = null;
	public static String folderPath = "";
	public static String jsonFilePath = "";
	public static String baseURI = "";
	public static String otherBaseURI = "";
	public static String basePath = "";
	public static String otherAccessToken = null;
	public static String dptAccessToken = null;
	public static String otherCustomersCatID = null;
	public static String otherEquipmentCatID = null;
	public static String otherItemsCatID = null;
	public static String otherLocationCatID = null;
	public static String otherSuppliersCatID = null;
	public static String otherPlantCatID = null;
	public static RequestSpecification requestSpecification = RestAssured.given();
	public static String extentReportName, extentReportName1;// ,
	// executionReportPath
	protected static String username = "";
	protected static String password = "";
	protected static String tenantName = "";
	protected static String otherTenantName = "";
	protected static int retryCount = 0;

	//// *******
	// Purpose - To create log folder day wise for each run & to set the values
	//// in
	//// log4j.properties file
	static {

		SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy_MM_dd");
		SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
		String date = dateFormat1.format(new Date());
		String dateTime = dateFormat2.format(new Date());
		String currentDayLogFolder = "Logs_" + date;
		//		// DOCKER - START
		//		System.out.println("System.getProperty(\"user.dir\") : " + System.getProperty("user.dir"));
		//		String currentRunLogFolder = currentDayLogFolder + "/Log_" + dateTime;
		//		// DOCKER - END
		String currentRunLogFolder = currentDayLogFolder + "\\Log_" + dateTime;
		String currentDayLogFolderPath = logsPath + currentDayLogFolder;
		String currentRunLogFolderPath = logsPath + currentRunLogFolder;

		File file1 = new File(currentDayLogFolderPath);
		//		// DOCKER - START
		//		System.out.println("currentRunLogFolder : " + currentRunLogFolder);
		//		boolean folderGenerated = file1.mkdirs();
		//		// DOCKER - END
		boolean folderGenerated = file1.mkdir();
		try {
			if (!file1.exists()) {
				if (folderGenerated) {
					System.out.println("Log folder for day created successfully " + currentDayLogFolderPath);
				} else {
					System.out.println("Couldn’t create new log folder of a day "  + currentDayLogFolderPath);
				}
			} else {
				System.out.println("Log folder for day already exists" + currentDayLogFolderPath);
			}
			File file2 = new File(currentRunLogFolderPath);
			folderGenerated = file2.mkdir();
			//			// DOCKER - START
			//			folderGenerated = file2.mkdirs();
			//			// DOCKER - END
			if (folderGenerated) {
				System.out.println("Run log folder created successfully " + currentRunLogFolderPath);
			} else {
				System.out.println("Couldn’t create new run log folder " + currentRunLogFolderPath);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		threadsleep(2000);
		System.setProperty("LOG_FOLDER", currentRunLogFolder);
		System.setProperty("current.date.time", dateTime);
		PropertyConfigurator.configure(Constants.LOG_CONFIG_FILE);

		try {
			FileInputStream fis = new FileInputStream(Constants.CONFIG_FILE);
			//			// DOCKER - START
			//			FileInputStream fis = new FileInputStream(Constants.DOCKER_CONFIG_FILE);
			//			// DOCKER - END

			prop.load(fis);
			System.out.println("Configuration file is loaded");
			System.out.println(
					"*******************************************************************************************");
			retryCount = Integer.parseInt(prop.getProperty("retry_count"));

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/************************
	 * @Purpose- Handling logs
	 *******************************/

	public static void logInfo(String message) {
		fullClassName = Thread.currentThread().getStackTrace()[2].getClassName();
		className = fullClassName.substring(fullClassName.lastIndexOf(".") + 1);
		lineNumber = Thread.currentThread().getStackTrace()[2].getLineNumber();
		Logger logger = LogManager.getLogger(className);
		String print = lineNumber + " - " + message;
		logger.info(print);
	}

	public static void logError(String message) {
		fullClassName = Thread.currentThread().getStackTrace()[2].getClassName();
		className = fullClassName.substring(fullClassName.lastIndexOf(".") + 1);
		lineNumber = Thread.currentThread().getStackTrace()[2].getLineNumber();
		Logger logger = LogManager.getLogger(className);
		String print = lineNumber + " - " + message;
		logger.error(print);
	}

	public static void logWarn(String message) {
		fullClassName = Thread.currentThread().getStackTrace()[2].getClassName();
		className = fullClassName.substring(fullClassName.lastIndexOf(".") + 1);
		lineNumber = Thread.currentThread().getStackTrace()[2].getLineNumber();
		Logger logger = LogManager.getLogger(className);
		String print = lineNumber + " - " + message;
		logger.warn(print);
	}

	public static void logDebug(String message) {
		fullClassName = Thread.currentThread().getStackTrace()[2].getClassName();
		className = fullClassName.substring(fullClassName.lastIndexOf(".") + 1);
		lineNumber = Thread.currentThread().getStackTrace()[2].getLineNumber();
		Logger logger = LogManager.getLogger(className);
		String print = lineNumber + " - " + message;
		logger.debug(print);
	}

	public static void logFatal(String message) {
		fullClassName = Thread.currentThread().getStackTrace()[2].getClassName();
		className = fullClassName.substring(fullClassName.lastIndexOf(".") + 1);
		lineNumber = Thread.currentThread().getStackTrace()[2].getLineNumber();
		Logger logger = LogManager.getLogger(className);
		String print = lineNumber + " - " + message;
		logger.fatal(print);
	}

	/************************
	 * @Purpose- Handling extent report logs
	 *******************************/
	public static void logExtentPass(String message) {
		try {
			TC.get().test.log(LogStatus.PASS, message);
		} catch (Exception e) {
			e.getMessage();
		}

	}

	public static void logExtentFail(String message) {
		try {
			TC.get().test.log(LogStatus.FAIL, message);
		} catch (Exception e) {
			e.getMessage();
		}

	}

	public static void logExtentSkip(String message) {
		try {
			TC.get().test.log(LogStatus.SKIP, message);
		} catch (Exception e) {
			e.getMessage();
		}

	}

	public static void skipTestGroup(String message) {
		logExtentSkip(message);
		throw new SkipException(message);
	}

	@Override
	public void alter(List<XmlSuite> suites) {
		if (prop.getProperty("enable_batch").trim().equalsIgnoreCase("Y")) {
			suites.clear();
			try {
				createXmlTest(suites);
			} catch (FileNotFoundException e) {
				throw new IllegalStateException(e);
			}
		}
	}
	private void createXmlTest(List<XmlSuite> suites) throws FileNotFoundException {
		DynamicTestNGFile dynamicTestng = new DynamicTestNGFile();
		String suitename = prop.getProperty("suite_name").trim();
		dynamicTestng.runTestNGTest(suitename, suites);
	}

	@BeforeMethod(enabled=true)
	public void beforeMethod(Method M) {
		logInfo("****************************************************************************************");
		logInfo("                   " + "START OF TESTCASE  - " + M.getName() + "                      ");
		logInfo("****************************************************************************************");
		TC.get().test = TC.get().extent.startTest(M.getName());
	}

	@BeforeTest(enabled=true)
	public void instance(ITestContext context)
	{			
		logInfo("****************************************************************************************");
		logInfo("                          " + "START OF EXECUTION" + "                                     ");
		logInfo("****************************************************************************************");

		externalDate = new SimpleDateFormat().format(new Date());
		extendReportName = "ExecutionReport_" + context.getName() + "_" + System.getProperty("user.name")
		+ formatter.format(sysdate) + ".html";
		executionReportPath = Constants.Execution_REPORT_PATH + extendReportName;

		ThreadConstant tc = ThreadInst.createInstanse();
		TC.set(tc);
		TC.get().browser = prop.getProperty("browser");		
		TC.get().extent = new ExtentReports(executionReportPath, true);

	}

	//@Parameters("env")
	@BeforeSuite(enabled = true)
	public void reportSetUp() throws IOException {
		try {

			killChromedriverInstances();
			
			// System.out.println(
			// "*******************************************************************************************");
			applicationUrl = prop.getProperty("appl_url_qa");
			adminUsername = prop.getProperty("admin_UserName");
			adminPassword = prop.getProperty("admin_Password");
			adminTimezone = prop.getProperty("admin_Timezone");
			executionMode = prop.getProperty("execution_mode");
			
			mobileAppUsername = prop.getProperty("mobileApp_UserName");
			mobileAppPassword = prop.getProperty("mobileApp_Password");
			mobileAppUsername02 = prop.getProperty("mobileApp_UserName02");
			mobileAppPassword02 = prop.getProperty("mobileApp_Password02");
			mobileApp_FNameLName = prop.getProperty("mobileApp_FNameLName");
			waynefarms_UserName = prop.getProperty("waynefarms_UserName");
			waynefarms_Password = prop.getProperty("waynefarms_Password");
			mobileApp_Tenant = prop.getProperty("mobileApp_Tenant");
			
			pcAppTenantname = prop.getProperty("pcApp_Tenant");
			pcAppUsername = prop.getProperty("pcApp_UserName");
			pcAppPassword = prop.getProperty("pcApp_Password");
			pcAppVersion = prop.getProperty("pcAPP_Version");
			pcAppTenantURL = prop.getProperty("pcApp_TenantURL");
			pcAppSSOTenantname =  prop.getProperty("pcApp_SSOTenant");
			pcAppSSOUsername =  prop.getProperty("pcApp_SSOUserName");
			pcAppSSOPassword =  prop.getProperty("pcApp_SSOPassword");
			pcAppSSOTenantname_DesktopView = prop.getProperty("pcApp_SSOTenantDesktopView");;
			pcAppSSOTenantname_WebView = prop.getProperty("pcApp_SSOTenantWebView");;
			test1STAGEUsername =  prop.getProperty("pcApp_UserNameTest1Stage");
			test1STAGEPassword =  prop.getProperty("pcApp_PasswordTest1Stage");

			baseURI = prop.getProperty("api_baseuri");
			otherBaseURI = prop.getProperty("api_otherbaseuri");
			System.out.print("Base Url ="+baseURI);
			username = prop.getProperty("api_username");
			password = prop.getProperty("api_password");
			tenantName = prop.getProperty("api_tenant");
			otherTenantName = prop.getProperty("api_othertenant");
			environment = prop.getProperty("api_environment");

			folderPath = System.getProperty("user.dir") + prop.getProperty("rules_api_folderPath");
			jsonFilePath = System.getProperty("user.dir") + prop.getProperty("rules_api_baseJsonFilePath")
			+ "request.json";
			
			// Place this after API settings are retrieved from config.properties
			locationName = prop.getProperty("location_name");
			if(locationName != null) {
				if(!locationName.isEmpty()) {
					API_ManageLocation_Wrapper locWrapper = new API_ManageLocation_Wrapper();
					if(!locWrapper.prerequisite_Location_CreationWrapper(locationName,locationName,adminUsername)) {
						// Exit from execution
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		createPath();

		// externalDate = new SimpleDateFormat().format(new Date());
		// extendReportName = "ExecutionReport_" + "_" +
		// System.getProperty("user.name")
		// + formatter.format(sysdate)
		// + ".html";
		// executionReportPath = Constants.Execution_REPORT_PATH +
		// extendReportName;
		// extent = new ExtentReports(executionReportPath, true);

		start = Instant.now();//

		// createPathApi();
		// externalDate = new SimpleDateFormat().format(new Date());
		// extentReportName = "ExecutionReportAPI_" + "_" +
		// System.getProperty("user.name") + formatter.format(sysdate)
		// + ".html";
		// executionReportPath = Constants.Execution_REPORT_PATH_API +
		// extentReportName;
		// extent = new ExtentReports(executionReportPath, true);
		// Reading properties from config file

	}
	/*
	 * @Purpose - This method 'launchbrowser' is used to launch browser (chrome
	 * or firefox or IE) based on the values provided in the property file and
	 * return the driver instance
	 */
	public WebDriver launchbrowser() throws InterruptedException {
		log("Launching Browser");		

		if (TC.get().browser.equalsIgnoreCase("chrome")) {
			try {
				if (prop.getProperty("mode").equalsIgnoreCase("remote")) {
					// System.setProperty("webdriver.chrome.driver",
					// System.getProperty("user.dir") +
					// "\\src\\test\\resources\\chromedriver_win32\\chromedriver.exe");
					// For Docker
					System.setProperty("webdriver.chrome.driver", Constants.DOCKER_CHROME_DRIVER_EXE);
					System.setProperty("webdriver.chrome.verboseLogging", "true");
					ChromeOptions options = new ChromeOptions();
					HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
					chromePrefs.put("profile.default_content_settings.popups", 0);
					chromePrefs.put("download.default_directory", downloadPath);
					options.setExperimentalOption("prefs", chromePrefs);
					if (prop.getProperty("incognito").equalsIgnoreCase("Y"))
						options.addArguments("--incognito");
					// options.addArguments("--headless");
					// DesiredCapabilities capabilities =
					// DesiredCapabilities.chrome();
					TC.get().driver = new RemoteWebDriver(new URL("http://selenium-hub:4444/wd/hub"), options);
					// capabilities.setCapability(ChromeOptions.CAPABILITY,
					// options);
					TC.get().driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					Thread.sleep(10);
				} else {
					// killChromedriverInstances();
					System.setProperty("webdriver.chrome.driver", Constants.CHROME_DRIVER_PATH);
					ChromeOptions options = new ChromeOptions();
					HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
					chromePrefs.put("profile.default_content_settings.popups", 0);
					chromePrefs.put("download.default_directory", downloadPath);
					options.setExperimentalOption("prefs", chromePrefs);
					if (prop.getProperty("incognito").equalsIgnoreCase("Y"))
						options.addArguments("--incognito");
					DesiredCapabilities capabilities = DesiredCapabilities.chrome();
					capabilities.setCapability(ChromeOptions.CAPABILITY, options);
					// TC.get().driver = new ChromeDriver(capabilities);
					TC.get().driver = new ChromeDriver(options);
					// TC.get().driver = new ChromeDriver();
					TC.get().driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					Thread.sleep(10);
				}
			} catch (Exception e) {
				e.printStackTrace();
				Assert.fail("Issue with Browser Opening", e.getCause());

			}

		} else if (TC.get().browser.equalsIgnoreCase("firefox")) {
			try {
				System.setProperty("webdriver.gecko.driver", Constants.GECKO_DRIVER_PATH);
				TC.get().driver = new FirefoxDriver();
				TC.get().driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			} catch (Exception e) {
				e.printStackTrace();
				Assert.fail("Issue with Browser Opening", e.getCause());
			}
		} else if (TC.get().browser.equalsIgnoreCase("ie")) {
			try {
				System.setProperty("webdriver.ie.driver", Constants.IE_DRIVER_PATH);
				TC.get().driver = new InternetExplorerDriver();
				TC.get().driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			} catch (Exception e) {
				e.printStackTrace();
				Assert.fail("Issue with Browser Opening", e.getCause());
			}
		}

		driver = TC.get().driver;
		String suitename = this.getClass().getSimpleName();
		logInfo("****************************************************************************************");
		logInfo("                       " + "START OF TEST SUITE  -  " + suitename + "                             ");
		logInfo("****************************************************************************************");
		return driver;
	}

	/*
	 * @Purpose - This method 'passLog' is used to update the pass status in the
	 * extent report wherever this method is called
	 */
	public void passLog(String meg) {
		try {
			TC.get().test.log(LogStatus.PASS, meg);
		} catch (Exception e) {
			e.getMessage();
		}
	}

	/*
	 * @Purpose - This method 'log' is used to update the info status in the
	 * extent report wherever this method is called
	 */
	public void log(String meg) {
		try {
			TC.get().test.log(LogStatus.INFO, meg);
		} catch (Exception e) {
			e.getMessage();
		}
	}

	/*
	 * @Purpose - This method 'log' is used to update the info status in the
	 * extent report wherever this method is called
	 */
	// TODO Explain how to use this method and its usage
	public void failLog(String meg) {
		try {
			TC.get().test.log(LogStatus.FAIL, meg);
		} catch (Exception e) {
			e.getMessage();
		}
	}

	/*
	 * @Purpose - This method 'takeScreenShot' is used to take screenshots
	 * whenever script fails and update the screenshot file name and log the
	 * fails status wherever this method is called Updated By @Author-Sumit
	 * Bhandare ,On 20-APR-2020 updated method with Base64 Image
	 */
	public static void takeScreenShot() {
		SimpleDateFormat formatter = new SimpleDateFormat("_yyyy_MM_dd_HH_mm_ss_ms");
		Date sysdate = new Date();
		// screenshotPath = Constants.SCREENSHOT_PATH_PASS + methodName +
		// formatter.format(sysdate) + ".png";
		screenshotPath = Constants.SCREENSHOT_PATH_FAIL + formatter.format(sysdate) + ".png";
		String encodedBase64 = null;
		try {
			File src = ((TakesScreenshot) TC.get().driver).getScreenshotAs(OutputType.FILE);
			// Files.copy(src, new File (screenshotPath));
			FileUtils.copyFile(src, new File(screenshotPath));
			File finaldest = new File(screenshotPath);
			FileUtils.copyFile(src, finaldest);

			try {
				// test.log(LogStatus.INFO, message);
				@SuppressWarnings("resource")
				FileInputStream Reader = new FileInputStream(finaldest);
				byte[] bytes = new byte[(int) finaldest.length()];
				Reader.read(bytes);
				encodedBase64 = new String(Base64.encodeBase64(bytes));
				TC.get().test.log(LogStatus.FAIL,
						TC.get().test.addBase64ScreenShot("data:image/png;base64," + encodedBase64));

			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (Exception e) {
			e.getMessage();
		}
	}

	/*
	 * @Purpose - This method 'createPath' is used to create path for extent
	 * reports and screenshots
	 * 
	 */
	public void createPath() {

		File exedir, faildir, masterdir;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd");
		Date sysdate = new Date();

		Constants.Execution_REPORT_PATH = Constants.projectpath + "\\Execution_Reports\\" + formatter.format(sysdate)
		+ "\\";
		Constants.SCREENSHOT_PATH_FAIL = Constants.projectpath + "\\Screeshots\\Fail_" + formatter.format(sysdate)
		+ "\\";

		//		// DOCKER - START
		//		Constants.Execution_REPORT_PATH = System.getProperty("user.dir") + "/Execution_Reports/" + formatter.format(sysdate)
		//				+ "/";
		//		System.out.println("Constants.Execution_REPORT_PATH  : " + Constants.Execution_REPORT_PATH);
		//		
		//		File file1 = new File(Constants.Execution_REPORT_PATH );
		//		boolean folderGenerated = file1.mkdirs();
		//		try {
		//			if (!file1.exists()) {
		//				if (folderGenerated) {
		//					System.out.println("Executions folder created successfully " + Constants.Execution_REPORT_PATH );
		//				} else {
		//					System.out.println("Couldn’t create Executions folder "  + Constants.Execution_REPORT_PATH );
		//				}
		//			} else {
		//				System.out.println("Executions folder already exists " + Constants.Execution_REPORT_PATH );
		//			}
		//		} catch (Exception e) {
		//			System.out.println(e.getMessage());
		//		}
		//		// DOCKER - END

		String master = Constants.projectpath + "\\MasterSheet\\" + formatter.format(sysdate) + "\\";
		path = master;
		exedir = new File(Constants.Execution_REPORT_PATH);
		if (!exedir.exists())
			exedir.mkdirs();

		faildir = new File(Constants.SCREENSHOT_PATH_FAIL);
		if (!faildir.exists())
			faildir.mkdirs();

		masterdir = new File(master);
		if (!masterdir.exists())
			masterdir.mkdirs();

	}

	/*
	 * @Purpose - This method 'log4j' is for creation of logger object and
	 * return the object This is used for logging the message
	 */
	public static Logger log4j(String name) {
		Logger logger = Logger.getLogger(name);
		return logger;
	}



	/*
	 * @Purpose - This method 'threadsleep' is created to pause the execution of
	 * current thread based on argument provided
	 */
	public static void threadsleep(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static Logger log4j() {
		Logger logger = Logger.getLogger(TestBase.class);
		return logger;
	}

	/*
	 * @Purpose - This method 'endTestCase' is used to close the driver
	 */
	@AfterMethod
	public void endTestCase(Method M) {
		logInfo("****************************************************************************************");
		logInfo("                         " + "END OF TESTCASE  -  " + M.getName() + "                         ");
		logInfo("****************************************************************************************");
		/*try{
			if(TC.get().driver!=null){
				TC.get().driver.close();
			}
			//logout method
			log("********Execution completed for_" + M.getName() + "_ *****");
			/*extent.endTest(test);
			extent.flush();
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}*/			
	}

	@AfterTest
	public void afterTest() {
		logInfo("****************************************************************************************");
		logInfo("                         " + "END OF EXECUTION" + "                                     ");
		logInfo("****************************************************************************************");

		TC.get().extent.endTest(test);
		TC.get().extent.flush();

		if (TC.get().driver != null) {
			// driver.quit();
			TC.get().driver.quit();
		}
	}

	/*
	 * @Purpose - This method 'tearDown' is used to quit the driver
	 */
	@AfterSuite(alwaysRun = true)
	public void tearDown() {
		// if(driver!=null){
		// driver.quit();
		// }

		// TC.get().extent.close();

		// STATUS REPORT
		mailServer = prop.getProperty("mail_server");
		emailIDFrom = prop.getProperty("emailID_from");
		emailIDPassword = prop.getProperty("emailID_password");
		emailIDUsername = prop.getProperty("emailID_username");
		emailIDTo = prop.getProperty("emailID_to");

		SendMailResults smr = new SendMailResults();
//		// DOCKER - START
//		System.out.println("AFTER SUITE : Constants.Execution_REPORT_PATH " + Constants.Execution_REPORT_PATH);
//		System.out.println("extendReportName " + extendReportName);
//		// DOCKER - END
		smr.mailconfig(Constants.Execution_REPORT_PATH, extendReportName);
	}
	
	/*
	 * @Purpose - This method 'killChromedriverInstances' is used to kill the
	 * chrome driver instances
	 */
	@SuppressWarnings("unused")
	private void killChromedriverInstances() {
		try {
			Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe /T");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * @Purpose - This method 'launchPCApp' is used to launch PC App based on
	 * the values provided in the property file and return the driver instance
	 */
	public WindowsDriver<WebElement> launchPCApp() {

		// start WinApp Driver exe

		log("Instantiating PC app driver");
		String app = prop.getProperty("appID");
		String appiumAddress = prop.getProperty("appiumAddress");
		pcAppTenantname = prop.getProperty("pcApp_Tenant");
		pcAppUsername = prop.getProperty("pcApp_UserName");
		pcAppPassword = prop.getProperty("pcApp_Password");
		logInfo("Application Id : " + app);
		logInfo("Appium address : " + appiumAddress);

		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("app", app);
		//capabilities.setCapability("ms:experimental-webdriver", true);
		//capabilities.setCapability("ms:waitForAppLaunch", 25);
		try {

			PCAppDriver = new WindowsDriver<WebElement>(new URL(appiumAddress), capabilities);
			PCAppDriver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

		} catch (Exception e) {
			logError("Failed to instantiate Windows Driver" + e.getMessage());
			e.printStackTrace();
		}

		return PCAppDriver;
	}

	/*
	 * @Purpose - This method 'launchMobileApp' is used to launch Mobile App
	 * based on the values provided in the property file and
	 *  return the driver
	 * instance
	 */
	public AppiumDriver<MobileElement> launchMobileApp() {
		log("Instantiating mobile app driver");

		try {

			DesiredCapabilities caps = new DesiredCapabilities();
			caps.setCapability("autoGrantPermissions", prop.getProperty("autoGrantPermissions"));
			caps.setCapability(CapabilityType.PLATFORM_NAME, prop.getProperty("PlatformName"));
			caps.setCapability("platformVersion", prop.getProperty("androidplatformVersion"));
			caps.setCapability(MobileCapabilityType.DEVICE_NAME, prop.getProperty("androidDeviceName"));
			caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, prop.getProperty("androidAutomationName"));
			caps.setCapability(MobileCapabilityType.APP, prop.getProperty("androidAppLocation"));
			caps.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "300000");
			// caps.setCapability("appWaitActivity",
			// "*.SplashActivity,*.TenantActivity,*.LoginActivity");

			caps.setCapability("relaxedSecurityEnabled", true);

			caps.setCapability("appWaitPackage", "com.safetychain.SCM.M2");
			caps.setCapability("appWaitActivity", "*.LoginActivity");
			caps.setCapability(MobileCapabilityType.NO_RESET, true);
			caps.setCapability(MobileCapabilityType.FULL_RESET, false);
			caps.setCapability(MobileCapabilityType.APPLICATION_NAME, "com.safetychain.SCM.M2");

			URL url = new URL(prop.getProperty("appiumURL"));
			appiumDriver = new AppiumDriver<MobileElement>(url, caps);
			appiumDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			@SuppressWarnings("unused")
			String sessionId = appiumDriver.getSessionId().toString();

		} catch (Exception e) {
			logError("Failed to instantiate Appium Driver" + e.getMessage());
			e.printStackTrace();
		}

		return appiumDriver;
	}

	public AppiumDriver<MobileElement> launchMobileAppImplicitWait() {
		log("Instantiating mobile app driver");

		try {

			DesiredCapabilities caps = new DesiredCapabilities();
			caps.setCapability("autoGrantPermissions", prop.getProperty("autoGrantPermissions"));
			caps.setCapability(CapabilityType.PLATFORM_NAME, prop.getProperty("PlatformName"));
			caps.setCapability("platformVersion", prop.getProperty("androidplatformVersion"));
			caps.setCapability(MobileCapabilityType.DEVICE_NAME, prop.getProperty("androidDeviceName"));
			caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, prop.getProperty("androidAutomationName"));
			caps.setCapability(MobileCapabilityType.APP, prop.getProperty("androidAppLocation"));
			caps.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "300000");
			// caps.setCapability("appWaitActivity",
			// "*.SplashActivity,*.TenantActivity,*.LoginActivity");

			caps.setCapability("relaxedSecurityEnabled", true);

			caps.setCapability("appWaitPackage", "com.safetychain.SCM.M2");
			caps.setCapability("appWaitActivity", "*.LoginActivity");
			caps.setCapability(MobileCapabilityType.NO_RESET, true);
			caps.setCapability(MobileCapabilityType.FULL_RESET, false);
			caps.setCapability(MobileCapabilityType.APPLICATION_NAME, "com.safetychain.SCM.M2");

			URL url = new URL(prop.getProperty("appiumURL"));
			appiumDriver = new AppiumDriver<MobileElement>(url, caps);
			appiumDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
			@SuppressWarnings("unused")
			String sessionId = appiumDriver.getSessionId().toString();

		} catch (Exception e) {
			logError("Failed to instantiate Appium Driver" + e.getMessage());
			e.printStackTrace();
		}

		return appiumDriver;
	}

	public AppiumDriver<MobileElement> launchMobileAppReinstall()

	{
		log("Instantiating mobile app driver");

		try {

			DesiredCapabilities caps = new DesiredCapabilities();
			caps.setCapability("autoGrantPermissions", prop.getProperty("autoGrantPermissions"));
			caps.setCapability(CapabilityType.PLATFORM_NAME, prop.getProperty("PlatformName"));
			caps.setCapability("platformVersion", prop.getProperty("androidplatformVersion"));
			caps.setCapability(MobileCapabilityType.DEVICE_NAME, prop.getProperty("androidDeviceName"));
			caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, prop.getProperty("androidAutomationName"));
			caps.setCapability(MobileCapabilityType.APP, prop.getProperty("androidAppLocation"));
			caps.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "300000");
			caps.setCapability("appWaitActivity", "*.SplashActivity,*.TenantActivity,*.LoginActivity");
			caps.setCapability("appWaitPackage", "com.safetychain.SCM.M2");
			caps.setCapability("relaxedSecurityEnabled", true);
			
			
			
			caps.setCapability(MobileCapabilityType.APPLICATION_NAME, "com.safetychain.SCM.M2");
			caps.setCapability("appWaitActivity", "*.SplashActivity,*.TenantActivity,*.LoginActivity");

			// caps.setCapability(MobileCapabilityType.NO_RESET, true);
			// caps.setCapability(MobileCapabilityType.APPLICATION_NAME,
			// "com.safetychain.SCM.M2");

			URL url = new URL(prop.getProperty("appiumURL"));
			appiumDriver = new AppiumDriver<MobileElement>(url, caps);
			appiumDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			@SuppressWarnings("unused")
			String sessionId = appiumDriver.getSessionId().toString();

		} catch (Exception e) {
			logError("Failed to instantiate Appium Driver" + e.getMessage());
			e.printStackTrace();
		}

		return appiumDriver;

	}

	public AppiumDriver<MobileElement> launchMobileAppReinstallFunctionality(String appLocation)

	{
		log("Instantiating mobile app driver");

		try {

			DesiredCapabilities caps = new DesiredCapabilities();
			caps.setCapability("autoGrantPermissions", prop.getProperty("autoGrantPermissions"));
			caps.setCapability(CapabilityType.PLATFORM_NAME, prop.getProperty("PlatformName"));
			caps.setCapability("platformVersion", prop.getProperty("androidplatformVersion"));
			caps.setCapability(MobileCapabilityType.DEVICE_NAME, prop.getProperty("androidDeviceName"));
			caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, prop.getProperty("androidAutomationName"));
			caps.setCapability(MobileCapabilityType.APP, appLocation);
			caps.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "300000");
			caps.setCapability("appWaitActivity", "*.SplashActivity,*.TenantActivity,*.LoginActivity");
			caps.setCapability("appWaitPackage", "com.safetychain.SCM.M2");
			caps.setCapability("relaxedSecurityEnabled", true);
			caps.setCapability(MobileCapabilityType.APPLICATION_NAME, "com.safetychain.SCM.M2");
			caps.setCapability("appWaitActivity",
					"*.SplashActivity,*.TenantActivity,*.LoginActivity");

			// caps.setCapability(MobileCapabilityType.NO_RESET, true);
			// caps.setCapability(MobileCapabilityType.APPLICATION_NAME,
			// "com.safetychain.SCM.M2");

			URL url = new URL(prop.getProperty("appiumURL"));
			appiumDriver = new AppiumDriver<MobileElement>(url, caps);
			appiumDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			@SuppressWarnings("unused")
			String sessionId = appiumDriver.getSessionId().toString();

		} catch (Exception e) {
			logError("Failed to instantiate Appium Driver" + e.getMessage());
			e.printStackTrace();
		}

		return appiumDriver;
	}
	
	/**This method is used to get the Desired Capabilities for Android Device
	 * @author ahmed_tw
	 * @return : Object of type 'DesiredCapabilities'
	 */
	public DesiredCapabilities getDesiredCapabilitiesForAndroid() {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        String PLATFORM_NAME = prop.getProperty("PlatformName");
        String PLATFORM_VERSION = prop.getProperty("androidplatformVersion");
        String APP_PATH = prop.getProperty("androidAppLocation");
        String DEVICE_NAME = prop.getProperty("androidDeviceName");
//        String APP_PACKAGE_NAME ="com.safetychain.SCM.M2";
//        String APP_ACTIVITY_NAME = "*.LoginActivity";

        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, DEVICE_NAME);
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, PLATFORM_NAME);
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, PLATFORM_VERSION);
        desiredCapabilities.setCapability(MobileCapabilityType.APP, APP_PATH);
       // desiredCapabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, APP_PACKAGE_NAME);
       // desiredCapabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, APP_ACTIVITY_NAME);
        desiredCapabilities.setCapability(MobileCapabilityType.FULL_RESET, false);
        desiredCapabilities.setCapability(MobileCapabilityType.NO_RESET, true);
       // desiredCapabilities.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS, true);
        desiredCapabilities.setCapability("relaxedSecurityEnabled", true);
        desiredCapabilities.setCapability("appWaitPackage", "com.safetychain.SCM.M2");
        desiredCapabilities.setCapability("appWaitActivity", "*.LoginActivity");
        desiredCapabilities.setCapability(MobileCapabilityType.APPLICATION_NAME, "com.safetychain.SCM.M2");
        desiredCapabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "300000");
        return desiredCapabilities;
    }
	
}