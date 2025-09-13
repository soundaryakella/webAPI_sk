package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
    private By username = By.id("username");
    private By password = By.id("password");
    private By loginBtn = By.id("submit");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Login with username and password using explicit waits
     */
    public LoginPage login(String user, String pass) {
        // driver.findElement(username).sendKeys(user);
        // driver.findElement(password).sendKeys(pass);
        // driver.findElement(loginBtn).click();
        safeSendKeys(username,user);
        safeSendKeys(password,pass);
        safeClick(loginBtn, 40);
        return this;
    }

    public String getLoggingSuccessMessage() {
        return safeGetText(By.className("post-title"),10);
//        WebElement successMsg = driver.findElement(By.className("post-title"));
//        return successMsg.getText();
    }


}
