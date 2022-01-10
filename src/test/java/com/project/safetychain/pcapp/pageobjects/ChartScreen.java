package com.project.safetychain.pcapp.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.project.utilities.ControlActions_PCApp;

import io.appium.java_client.windows.WindowsDriver;

public class ChartScreen extends CommonScreen {

	WindowsDriver<WebElement> PCAppDriver;
	WebDriverWait wait;
	ControlActions_PCApp desktopControlActions;

	public ChartScreen(WindowsDriver<WebElement> driver) {
		super(driver);
		this.PCAppDriver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 30);
		desktopControlActions = new ControlActions_PCApp(this.PCAppDriver);
	}


	/**
	 * This method is used to verify chart elements
	 * @author pashine_a
	 * @param null
	 * @return boolean
	 */
	public boolean verifyChartElements() {
		try {
			threadsleep(2000);
			if(!desktopControlActions.isElementDisplayed(ChartScreenLocators.SPC_DOCUMENT_POINT)) {
				logInfo("Chart is not shown");
				return false;
			}
			if(!desktopControlActions.isElementDisplayed(ChartScreenLocators.SPC_COMMENT_LNK)) {
				logInfo("'Add Comment' link is not shown");
				return false;
			}
			if(!desktopControlActions.isElementDisplayed(ChartScreenLocators.SPC_RESULTS_LBL)) {
				logInfo("'Results' label is not shown");
				return false;
			}
			if(!desktopControlActions.isElementDisplayed(ChartScreenLocators.SPC_MEAN_LBL)) {
				logInfo("'Mean' label is not shown");
				return false;
			}
			logInfo("Verified chart elements");
			return true;
		}catch(Exception e) {
			logError("Failed to verify chart elements - "+ e.getMessage());
			return false;
		}	
	}

}
