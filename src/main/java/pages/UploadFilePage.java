package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UploadFilePage extends BasePage {

    private By uploadBtn = By.id("fileSubmit");
    private By chooseFile = By.cssSelector("[data-testid='file-input']");

    public UploadFilePage(WebDriver driver) {
        super(driver);
    }

    public UploadFilePage uploadFileAndClick(String relativeFilePath, String attributeToWait, int timeoutInSeconds) {
        uploadFileAndWaitForAttribute(chooseFile, relativeFilePath, attributeToWait, timeoutInSeconds);
        safeClick(uploadBtn, 20);
        return this;
    }

    public String getUploadSuccessMessage() {
        return safeGetText(By.tagName("h1"), 10).trim();
    }
}
