package webTests;

import base.BaseTest;
import org.testng.Assert;
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
}
