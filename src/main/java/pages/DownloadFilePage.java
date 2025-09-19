package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DownloadFilePage extends BasePage {

    private By downloadBtn = By.xpath("//strong[contains(text(), 'csv_sample_file_1MB.csv')]/ancestor::div[@class='card-body d-flex justify-content-between align-items-center']/a");
    private By chooseFile = By.cssSelector("[data-testid='file-input']");

    public DownloadFilePage(WebDriver driver) {
        super(driver);
    }

    public DownloadFilePage clickOnDownloadFileBtn() {
        safeClick(downloadBtn, 20);
        return this;
    }

    public String getUploadSuccessMessage() {
        return safeGetText(By.tagName("h1"), 10).trim();
    }
}
