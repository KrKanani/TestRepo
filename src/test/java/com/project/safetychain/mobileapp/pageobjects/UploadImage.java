package com.project.safetychain.mobileapp.pageobjects;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.project.testbase.TestBase;
import com.project.utilities.ControlActions_MobileApp;
// import org.openqa.selenium.android.AndroidDriver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

//@SuppressWarnings("unused")
public class UploadImage extends TestBase {
	AppiumDriver<MobileElement> appiumDriver;
	WebDriverWait wait;
	ControlActions_MobileApp mobControlActions;

	public UploadImage(AppiumDriver<MobileElement> driver) {
		this.appiumDriver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		mobControlActions = new ControlActions_MobileApp(this.appiumDriver);
	}

	@FindBy(xpath = HomePageLocators.Form_Name)
	public WebElement formNameSearched;

	@FindBy(id = UploadImageLocators.Camera_Button)
	public WebElement MainCameraBtn;

	@FindBy(id = UploadImageLocators.dgCameraBtn)
	public WebElement cameraBtn;

	@FindBy(id = UploadImageLocators.dgGalleryBtn)
	public WebElement galleryBtn;

	@FindBy(xpath = UploadImageLocators.dgCameraBtnOk)
	public WebElement cameraBtnOk;

	@FindBy(xpath = UploadImageLocators.gTextView)
	public WebElement GalleryTextView;

	@FindBy(xpath = UploadImageLocators.dgCameraOk)
	public WebElement cameraOkBtn;

	@FindBy(xpath = UploadImageLocators.CAMERA_SHUTTER)
	public WebElement Camera_Shutter;

	@FindBy(id = UploadImageLocators.dgCameraSubmit)
	public WebElement camerasubmits;

	@FindBy(xpath = UploadImageLocators.gViewImages)
	public WebElement viewImages;

	@FindBy(className = UploadImageLocators.gSelectImages)
	public WebElement selectImages;

	@FindBy(xpath = UploadImageLocators.gSelectImagesBtn)
	public WebElement selectImageBtn;

	@FindBy(id = UploadImageLocators.gCloseImagesBtn)
	public WebElement closeImage;

	@FindBy(id = UploadImageLocators.vFormDate)
	public WebElement formDate;

	@FindBy(id = UploadImageLocators.vFormStatus)
	public WebElement formStatus;

	@FindBy(id = UploadImageLocators.chkStatus)
	public WebElement chkStatus;

	@FindBy(id = UploadImageLocators.permissionAllowBtn)
	public WebElement permissionAllow;

	@FindBy(id = UploadImageLocators.PHOTO_SECTION_TITLE)
	public WebElement photoTitle;

	@FindBy(id = UploadImageLocators.FORMS_PHOTO)
	public WebElement selectPhoto;

	@FindBy(id = UploadImageLocators.FORMS_PHOTO_BACK_BTN)
	public WebElement photoBckBtn;

	@FindBy(id = UploadImageLocators.OPENED_IMG)
	public WebElement openedImg;

	@FindBy(id = UploadImageLocators.FORMS_PHOTO_DLETE)
	public WebElement formsPhotoDelete;

	public boolean ModifyPin() {
		try {

			WebElement BtnPin = appiumDriver.findElement(By.id(UploadImageLocators.Pin_Button));
			BtnPin.click();

			return true;
		} catch (Exception ex) {
			logInfo("Failed to click on pin button" + ex.getMessage());
			return false;
		}

	}

	public boolean ModifyPinDetails() {
		try {

			WebElement TxtUser = appiumDriver.findElement(By.id(UploadImageLocators.Pin_User));
			TxtUser.sendKeys("mobAuto02");
			WebElement BtnPin = appiumDriver.findElement(By.id(UploadImageLocators.Pin_Pass));
			BtnPin.sendKeys("1234");

			WebElement BtnCompli = appiumDriver.findElement(By.id(UploadImageLocators.Pin_Button_Complaint));
			BtnCompli.click();
			WebElement TxtComments = appiumDriver.findElement(By.id(UploadImageLocators.Pin_Comments));
			TxtComments.sendKeys("Compliant form");

			return true;
		} catch (Exception ex) {
			logInfo("Failed to modify on pin" + ex.getMessage());
			return false;
		}

	}

	public boolean SavePin() {
		try {

			WebElement BtnPinSave = appiumDriver.findElement(By.id(UploadImageLocators.Pin_Button_Save));
			BtnPinSave.click();

			return true;
		} catch (Exception ex) {
			logInfo("Failed to save on pin" + ex.getMessage());
			return false;
		}
	}

	public boolean CameraClick() {
		try {

			ControlActions_MobileApp.waitForVisibility(MainCameraBtn, 50);
			Thread.sleep(5000);
			if (MainCameraBtn.isDisplayed()) {
				logInfo("Element is visible");
				MainCameraBtn.click();

			}
			logInfo("Camera icon clicked on Forms Page");
			return true;
		} catch (Exception ex) {
			logInfo("Failed to click camera" + ex.getMessage());
			return false;
		}

	}

	public boolean OpenGallery() {
		try {
			ControlActions_MobileApp.waitForVisibility(galleryBtn, 50);
			ControlActions_MobileApp.click(galleryBtn);
			return true;
		} catch (Exception ex) {
			logInfo("Failed to open gallery" + ex.getMessage());
			return false;

		}

	}

	public boolean OpenCamera() {
		try {
			ControlActions_MobileApp.waitForVisibility(cameraBtn, 50);
			ControlActions_MobileApp.click(cameraBtn);

			logInfo("Camera Opened");
			return true;

		} catch (Exception ex) {
			logInfo("Failed to open camera" + ex.getMessage());
			return false;
		}

	}

	public boolean SelectImage() {
		try {
			ControlActions_MobileApp.waitForVisibility(viewImages, 50);
			ControlActions_MobileApp.click(viewImages);

			ControlActions_MobileApp.waitForVisibility(selectImages, 50);
			ControlActions_MobileApp.click(selectImages);

			return true;
		} catch (Exception ex) {
			logInfo("Failed to load image" + ex.getMessage());
			return false;
		}

	}

	public boolean CloseGallery() {
		try {

			ControlActions_MobileApp.waitForVisibility(selectImageBtn, 50);
			ControlActions_MobileApp.click(selectImageBtn);

			ControlActions_MobileApp.waitForVisibility(closeImage, 50);
			ControlActions_MobileApp.click(closeImage);

			return true;
		} catch (Exception ex) {
			logInfo("Failed to close gallery" + ex.getMessage());
			return false;
		}

	}

	public boolean verifySelectedImageThroughGallery() {
		Boolean isPresent = false;
		try {

			ControlActions_MobileApp.waitForVisibility(photoTitle, 50);
			String imageTitle = photoTitle.getText();
			System.out.println("Image name present is: " + imageTitle);
			if (imageTitle.equals("GENERAL PHOTOS")) {
				logInfo("General Level Image Folder Name verified" + imageTitle);
				ControlActions_MobileApp.waitForVisibility(selectPhoto, 50);
				ControlActions_MobileApp.click(selectPhoto);
				Thread.sleep(4000);
				ControlActions_MobileApp.waitForVisibility(photoBckBtn, 50);
				ControlActions_MobileApp.click(photoBckBtn);
				isPresent = true;

			} else {
				isPresent = false;
				logInfo("General Level Image Folder is not present");
			}

			return isPresent;
		} catch (Exception e) {
			logError("Failed to Verify Images Captured/Selected through Camera/Images- " + e.getMessage());
			return isPresent;
		}
	}

	public boolean verifySelectedImageThroughCamera(String FieldValue) {
		Boolean isPresent = false;
		try {

			String fieldValue = ControlActions_MobileApp
					.perform_GetElementByXPath2(REG_SubmissionPageLocators.IMAGE_BTN, "FIELDVALUE", FieldValue);
			WebElement Field = appiumDriver.findElement(By.xpath(fieldValue));
			ControlActions_MobileApp.waitForVisibility(Field, 50);
			ControlActions_MobileApp.click(Field);
			Thread.sleep(4000);

			ControlActions_MobileApp.waitForVisibility(openedImg, 50);

			ControlActions_MobileApp.waitForVisibility(photoBckBtn, 50);
			ControlActions_MobileApp.click(photoBckBtn);
			isPresent = true;

			return isPresent;
		} catch (Exception e) {
			logError("Failed to Verify Images Captured/Selected through Camera/Images- " + e.getMessage());
			return isPresent;
		}
	}

	public boolean CameraShutter() {
		try {
			ControlActions_MobileApp.waitForVisibility(Camera_Shutter, 80);
			ControlActions_MobileApp.click(Camera_Shutter);
			logInfo("Camera Shutter Button Clicked");
			ControlActions_MobileApp.waitForVisibility(cameraOkBtn, 80);
			ControlActions_MobileApp.click(cameraOkBtn);
			logInfo("Captured Image selected");
			return true;
		} catch (Exception ex) {
			logInfo("Failed to shut camera" + ex.getMessage());
			return false;
		}
	}

	public boolean CloseCamera() {
		try {

			ControlActions_MobileApp.waitForVisibility(closeImage, 80);
			ControlActions_MobileApp.click(closeImage);
			return true;
		} catch (Exception ex) {
			logInfo("Failed to close camera" + ex.getMessage());
			return false;
		}

	}

	public boolean verifyFormDate() {

		try {

			WebElement verifyStatus = appiumDriver.findElement(By.id(UploadImageLocators.vFormStatus));
			String strStatus = verifyStatus.getText();
			if (strStatus == UploadImageLocators.chkStatus) {
				SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
				Date date = new Date();
				@SuppressWarnings("unused")
				String strrDate = formatter.format(date);
				WebElement verifyDate = appiumDriver.findElement(By.id(UploadImageLocators.vFormDate));
				String strDate = verifyDate.getText();
				StringTokenizer stTokens = new StringTokenizer(strDate);
				@SuppressWarnings("unused")
				String curDate = null, curTime = null;
				for (int i = 1; stTokens.hasMoreTokens(); i++) {
					stTokens.nextToken(" ");
					if (i == 2) {
						curDate = stTokens.nextToken();
					} else if (i == 4) {
						curTime = stTokens.nextToken();

					}
				}
			}

			return true;
		}

		catch (Exception ex) {
			logInfo("Failed to verify status" + ex.getMessage());
			return false;
		}

	}

}
