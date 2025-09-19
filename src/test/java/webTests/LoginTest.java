package webTests;

import base.BaseTest;
import org.testng.Assert;
import pages.DownloadFilePage;
import pages.UploadFilePage;
import utils.DriverFactory;
import dataprovider.LoginPageDataProvider;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.ConfigReader;
import utils.ExtentTestManager;

import java.util.Map;

public class LoginTest extends BaseTest {

    @Test(dataProvider = "loginUserData", dataProviderClass = LoginPageDataProvider.class)
    public void testLogin(Map<String, String> userData) {
        // Always access WebDriver through DriverFactory
        WebDriver driver = DriverFactory.getDriver();
        System.out.println("Thread ID: " + Thread.currentThread().getId() +
                " - Driver Hash: " + driver.hashCode() +
                " - Testing user: " + userData.get("username"));
        driver.get(ConfigReader.get("web.baseUrl"));
        LoginPage loginPage = new LoginPage(driver);
        ExtentTestManager.getTest().info("Navigating to login page");
        String user = userData.get("username");
        String pass = userData.get("password");
        loginPage.login(user, pass);
        // Simple assertion for success message (if needed)
         Assert.assertEquals(loginPage.getLoggingSuccessMessage(), "Logged In Successfully");
    }
    ////a[@type='button' and @href='/upload' and contains(@class, 'btn-outline-primary')]

    @Test
    public void uploadFile() {
        // Always access WebDriver through DriverFactory
        WebDriver driver = DriverFactory.getDriver();
        driver.get(ConfigReader.get("web.baseUrl.uploadFile"));
        UploadFilePage uploadFilePage = new UploadFilePage(driver);
        ExtentTestManager.getTest().info("Navigating to upload file page");
        String relativeFilePath = "src/test/resources/testdata/dve.png";
        String attributeToWait = "data-gtm-form-interact-field-id";
        int timeoutInSeconds = 30;//
        uploadFilePage.uploadFileAndClick(relativeFilePath, attributeToWait, timeoutInSeconds);
        Assert.assertEquals(uploadFilePage.getUploadSuccessMessage(), "File Uploaded!", "Upload message mismatch!");
    }

    @Test
    public void downloadFile() throws InterruptedException {
        // Always access WebDriver through DriverFactory
        WebDriver driver = DriverFactory.getDriver();
        driver.get(ConfigReader.get("web.baseUrl.downloadFile"));
        DownloadFilePage downloadFilePage = new DownloadFilePage(driver);
        downloadFilePage.clickOnDownloadFileBtn();
        Thread.sleep(100000);
//        ExtentTestManager.getTest().info("Clicked on download button");


    }
}
