package com.project.safetychain.webapp.testcases;

import java.io.File;

import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.project.safetychain.webapp.pageobjects.DocumentManagementPage;
import com.project.safetychain.webapp.pageobjects.HomePage;
import com.project.safetychain.webapp.pageobjects.InboxPage;
import com.project.safetychain.webapp.pageobjects.LoginPage;
import com.project.safetychain.webapp.pageobjects.WorkGroupsPage;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.utilities.CommonMethods;
import com.project.utilities.ControlActions;
public class TCG_SMK_DocumentManagement extends TestBase{

	ControlActions controlActions;
	public static String workGroupName;
	public static String doctype;
	public static String taskname;
	public static String doctaskname;
	public static String image;
	public static String imagenew;
	public static String assigndoc;
	public static String deletedoc;
	public static String linkdoc;
	public static String uploadfile;
	public static String uploadassigndoc;
	public static String uploaddeletedoc;
	public static String uploadlinkdoc;
	public static String downloadPath;
	public static String uploadnewfile;
	public static String downloadedfile;
	DocumentManagementPage dmp;
	InboxPage inp;
	HomePage hp;


	@BeforeClass(alwaysRun = true)
	public void groupInit() throws InterruptedException {
		workGroupName = CommonMethods.dynamicText("WG");
		doctype = CommonMethods.dynamicText("Doc");
		doctaskname = CommonMethods.dynamicText("DocTask");
		taskname = CommonMethods.dynamicText("DocumentTask");
		//doctype = "Doc_20.07_18.36.59";
		image = "upload.png";		
		imagenew = "uploadnew.jpg";
		assigndoc = "assigntask.png";
		deletedoc = "delete.png";
		linkdoc = "Linkdoc.jpg";
		uploadfile = System.getProperty("user.dir") + "\\test-data-files\\UploadDocuments\\"+image;
		uploadnewfile = System.getProperty("user.dir") + "\\test-data-files\\UploadDocuments\\"+imagenew;
		uploadassigndoc = System.getProperty("user.dir") + "\\test-data-files\\UploadDocuments\\"+assigndoc; 
		uploaddeletedoc = System.getProperty("user.dir") + "\\test-data-files\\UploadDocuments\\"+deletedoc; 
		uploadlinkdoc = System.getProperty("user.dir") + "\\test-data-files\\UploadDocuments\\"+linkdoc; 
		downloadPath = System.getProperty("user.dir") + "\\test-data-files\\DownloadedDocuments\\";
		downloadedfile = System.getProperty("user.dir") + "\\test-data-files\\DownloadedDocuments\\"+image;

		driver = launchbrowser();
		controlActions = new ControlActions(driver);
		controlActions.getUrl(applicationUrl);
		LoginPage lp = new LoginPage(driver);
		hp = lp.performLogin(adminUsername, adminPassword);
		if(hp.error) {
			TCGFailureMsg = "Could NOT login to application";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		WorkGroupsPage wgp = hp.clickWorkGroupsMenu();
		boolean wgcreate = wgp.createWorkGroup(workGroupName, adminUsername);
		TestValidation.IsTrue(wgcreate, 
				"Created workgroup - " + workGroupName, 
				"Workgroup creation failed");

		dmp = hp.clickdocumentsmenu();
		boolean doctypecreation = dmp.createDocType(doctype);
		TestValidation.IsTrue(doctypecreation, 
				"Created document type - " + doctype+ "and uploaded" +uploadfile, 
				"Document Type and document upload failed");
	}

	@Test(description="Uploading document")
	public void TestCase_30741() throws Exception{

		boolean upload = dmp.uploadDocument(uploadfile,doctype);
		TestValidation.IsTrue(upload, 
				"Created document type - " + doctype+ "and uploaded" +uploadfile, 
				"Document Type and document upload failed");

		boolean verify = dmp.verifyDocUpload(image);
		TestValidation.IsTrue(verify, 
				"Document Uploaded " +image+ "Verified",
				"Document uploaded "+image+ "not verified");

	}
	@Test(description="Download and verify downloaded documnet")
	public void TestCase_32564() throws Exception{
		
		boolean download = dmp.docDownload(uploadfile,image,doctype);
		TestValidation.IsTrue(download, 
				"Document Downloaded " +image,
				"Document Downloaded "+image);

		boolean verifydownload = dmp.verifyFileDownloaded(downloadPath,image);
		TestValidation.IsTrue(verifydownload, 
				"Document Download " +image+ "Verified",
				"Document Download "+image+ "not verified");

	}

	@Test(description="Upload new version of the document and verify the version")
	public void TestCase_32554() throws Exception{

		hp.clickdocumentsmenu();
		boolean newupload = dmp.uploadNewVersion(uploadnewfile,doctype,imagenew);
		TestValidation.IsTrue(newupload, 
				"Upload new version done" ,
				"upload new version failed");

		boolean versionverify = dmp.verifyDocumentVersion(imagenew);
		TestValidation.IsTrue(versionverify, 
				"Verified version" ,
				"Version verification failed");

	}

	@Test(description="Assign Document Task")
	public void TestCase_32555() throws Exception{

		dmp = hp.clickdocumentsmenu();
		boolean assign = dmp.assignDocumentTask(uploadassigndoc,doctype,assigndoc,doctaskname,workGroupName);
		TestValidation.IsTrue(assign, 
				"Document Task Assigned" ,
				"Document assignment failed");

	}

	@Test(description="Deleting and restoring document")
	public void TestCase_31236() throws Exception{

		dmp = hp.clickdocumentsmenu();
		boolean delete = dmp.deleteDocument(uploaddeletedoc,doctype,deletedoc);
		TestValidation.IsTrue(delete, 
				"Document deleted" ,
				"Document deletion failed");

		boolean restore = dmp.restoreDocument(doctype, deletedoc);
		TestValidation.IsTrue(restore, 
				"Document restored",
				"Document restoration failed");

		boolean verify = dmp.verifyRestoration(doctype, deletedoc);
		TestValidation.IsTrue(verify, 
				"Document restoration verified",
				"Document restoration verification failed");

	}

	@Test(description="Completing document Task")
	public void TestCase_34934() throws Exception{

		dmp = hp.clickdocumentsmenu();
		boolean assign = dmp.assignDocumentTask(uploadassigndoc,doctype,assigndoc,taskname,workGroupName);
		TestValidation.IsTrue(assign, 
				"Document Task Assigned" ,
				"Document assignment failed");

		inp = hp.clickInboxMenu();
		boolean doctask = inp.documentUploadTask(taskname,uploadassigndoc);
		TestValidation.IsTrue(doctask, 
				"Document Task Completed" ,
				"Document Task failed");

	}

	@Test(description="Linking document and verify")
	public void Testcase_32561() throws Exception{

		dmp = hp.clickdocumentsmenu();
		boolean assign = dmp.manageLink(uploadlinkdoc,doctype,linkdoc);
		TestValidation.IsTrue(assign, 
				"Document Linked" ,
				"Document linking failed");
		boolean verify = dmp.verifyManageLink(linkdoc);
		TestValidation.IsTrue(verify, 
				"Document linking verified" ,
				"Document linking verification failed");

	}


	@AfterClass(alwaysRun = true)
	public void closeBrowser() throws InterruptedException {
		try 
		{
			new File(downloadedfile).delete();	
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		driver.close();
	}
}
