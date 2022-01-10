package com.project.safetychain.mobileapp.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.project.testbase.TestBase;
import com.project.utilities.CommonMethods;
import com.project.utilities.ControlActions_MobileApp;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class ChartsPage extends TestBase {

	public class HomepageLocators {

		public final String formName = null;
		public final String FORM_NAME = null;

		public void main(String[] args) { 
		}

	}
 

	AppiumDriver<MobileElement> appiumDriver;
	WebDriverWait wait;
	ControlActions_MobileApp mobControlActions;
	CommonMethods commonmethods;

	public ChartsPage(AppiumDriver<MobileElement> driver) {
		this.appiumDriver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		mobControlActions = new ControlActions_MobileApp(this.appiumDriver);
		commonmethods = new CommonMethods(this.appiumDriver);
	}

	@FindBy(id = ChartsPageLocators.FORM_NM)
	public WebElement chartHeader;

	@FindBy(id = ChartsPageLocators.CHART_NM)
	public WebElement chartName;

	@FindBy(xpath = ChartsPageLocators.NEXT_BTN)
	public WebElement chartNxtBtn;

	@FindBy(xpath = ChartsPageLocators.PREVIOUS_BTN)
	public WebElement chartPreBtn;

	@FindBy(xpath = ChartsPageLocators.CLOSE_BTN)
	public WebElement chartCloseBtn;

	@FindBy(xpath = ChartsPageLocators.CHART_YCORDINATE)
	public WebElement chartYCordinate;

	@FindBy(id = ChartsPageLocators.FORM_BCK_BTN)
	public WebElement formBackBtn;

	@FindBy(xpath = ChartsPageLocators.CHART_ICN)
	public WebElement chartIcn;

	@FindBy(xpath = ChartsPageLocators.CHART_BTN)
	public WebElement chartBtn;

	public void actionEnter() { 
	}

	public boolean VerifyChartDetails(String formName, String ChartName, String YCordinate) {
		try {
			ControlActions_MobileApp.waitForVisibility(chartHeader, 1000);

			if (chartHeader.getText().equals(formName)) {
				logInfo("chart template Name Verified " + chartHeader.getText());
				WebElement ChartNm = null;
				ChartNm = ControlActions_MobileApp.perform_GetElementByXPath(ChartsPageLocators.CHART_NM, "CHARTNAME",
						ChartName);
				ControlActions_MobileApp.waitForVisibility(ChartNm, 10000);
				if (ChartNm.getText().equals(ChartName)) {
					logInfo("chart is Present " + ChartNm.getText());
					WebElement formCheck = null;
					formCheck = ControlActions_MobileApp.perform_GetElementByXPath(ChartsPageLocators.CHART_YCORDINATE,
							"VALUE", YCordinate);
					ControlActions_MobileApp.waitForVisibility(formCheck, 10000);

					if (formCheck.getText().equals(YCordinate)) {
						logInfo("Y Co-ordinate has verified" + YCordinate);
						return true;
					}
				}
			}
			return true;
		} catch (Exception e) {
			logError("Failed to click -Task - " + e.getMessage());
			return false;
		}
	}

	public boolean clikNxtButton(WebElement EleToBeClicked) {
		try {
			ControlActions_MobileApp.waitForVisibility(EleToBeClicked, 60);
			ControlActions_MobileApp.click(EleToBeClicked);

			return true;
		} catch (Exception ex) {
			logInfo("Failed to click Next button on Charts Page" + ex.getMessage());
			return false;
		}
	}

	public boolean selectChartToBeVerified(String chartTemplate) {
		try {
			ControlActions_MobileApp.waitForVisibility(chartHeader, 60);
			ControlActions_MobileApp.waitForVisibility(chartName, 60);

			WebElement formCheck = null;
			@SuppressWarnings("unused")
			String ChartName = ControlActions_MobileApp.perform_GetElementByXPath2(ChartsPageLocators.CHART_NM, "CHARTNAME",
					"P-chart");
			ControlActions_MobileApp.waitForVisibility(formCheck, 60);
			String chartDispalyed = chartName.getText();
			switch (chartDispalyed) {
			case "P-chart":
				VerifyChartDetails(chartTemplate, "P-chart", "Defects");
				break;
			case "X-Bar":
				VerifyChartDetails(chartTemplate, "X-Bar", "Range");
				VerifyChartDetails(chartTemplate, "X-Bar", "Mean (oz)");

				break;
			}
			return true;
		} catch (Exception e) {
			logError("Failed to click -Task - " + e.getMessage());
			return false;
		}
	}

	public boolean checkFormIconFromSubmissionsSubMenu(String FormName) {
		try {

			WebElement resource = null;
			Thread.sleep(5000);

			resource = ControlActions_MobileApp.perform_GetElementByXPath(SavedPageLocators.Form_Name, "FormNameLocator",
					FormName);

			ControlActions_MobileApp.waitForVisibility(resource, 60);
			logInfo("Form is Present");

			if (ControlActions_MobileApp.isElementDisplayed(chartIcn)) {

				logInfo("chart Icon is present on Submissions screen");
			} else {
				Assert.fail("chart Icon is not present on Submissions screen");
			}

			return true;
		} catch (Exception ex) {
			logInfo("Failed to Search resource" + ex.getMessage());
			return false;
		}
	}

}
