package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ConfigReader;

import java.io.File;
import java.time.Duration;
import java.util.List;

/**
 * Base page class that provides common functionality and explicit waits
 * for all page objects in the framework.
 */
public abstract class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    
    // Default wait timeout from config, fallback to 10 seconds
    private static final int DEFAULT_WAIT_TIMEOUT = Integer.parseInt(
        ConfigReader.get("web.wait.timeout", "10")
    );
    
    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_WAIT_TIMEOUT));
    }
    
    /**
     * Wait for element to be visible and return it
     */
    protected WebElement waitForElementToBeVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    
    /**
     * Wait for element to be visible with custom timeout
     */
    protected WebElement waitForElementToBeVisible(By locator, int timeoutInSeconds) {
        WebDriverWait customWait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        return customWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    
    /**
     * Wait for element to be clickable and return it
     */
    protected WebElement waitForElementToBeClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
    
    /**
     * Wait for element to be clickable with custom timeout
     */
    protected WebElement waitForElementToBeClickable(By locator, int timeoutInSeconds) {
        WebDriverWait customWait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        return customWait.until(ExpectedConditions.elementToBeClickable(locator));
    }
    
    /**
     * Wait for element to be present in DOM
     */
    protected WebElement waitForElementToBePresent(By locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }
    
    /**
     * Wait for element to be present with custom timeout
     */
    protected WebElement waitForElementToBePresent(By locator, int timeoutInSeconds) {
        WebDriverWait customWait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        return customWait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }
    
    /**
     * Wait for element to disappear (not visible)
     */
    protected boolean waitForElementToDisappear(By locator) {
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }
    
    /**
     * Wait for element to disappear with custom timeout
     */
    protected boolean waitForElementToDisappear(By locator, int timeoutInSeconds) {
        WebDriverWait customWait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        return customWait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }
    
    /**
     * Wait for text to be present in element
     */
    protected boolean waitForTextToBePresentInElement(By locator, String text) {
        return wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
    }
    
    /**
     * Wait for text to be present in element with custom timeout
     */
    protected boolean waitForTextToBePresentInElement(By locator, String text, int timeoutInSeconds) {
        WebDriverWait customWait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        return customWait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
    }
    
    /**
     * Wait for URL to contain specific text
     */
    protected boolean waitForUrlToContain(String urlFragment) {
        return wait.until(ExpectedConditions.urlContains(urlFragment));
    }
    
    /**
     * Wait for URL to contain specific text with custom timeout
     */
    protected boolean waitForUrlToContain(String urlFragment, int timeoutInSeconds) {
        WebDriverWait customWait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        return customWait.until(ExpectedConditions.urlContains(urlFragment));
    }
    
    /**
     * Wait for page title to contain specific text
     */
    protected boolean waitForTitleToContain(String titleFragment) {
        return wait.until(ExpectedConditions.titleContains(titleFragment));
    }
    
    /**
     * Wait for page title to contain specific text with custom timeout
     */
    protected boolean waitForTitleToContain(String titleFragment, int timeoutInSeconds) {
        WebDriverWait customWait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        return customWait.until(ExpectedConditions.titleContains(titleFragment));
    }
    
    /**
     * Wait for alert to be present
     */
    protected Alert waitForAlert() {
        return wait.until(ExpectedConditions.alertIsPresent());
    }
    
    /**
     * Wait for alert to be present with custom timeout
     */
    protected Alert waitForAlert(int timeoutInSeconds) {
        WebDriverWait customWait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        return customWait.until(ExpectedConditions.alertIsPresent());
    }
    
    /**
     * Wait for all elements matching locator to be visible
     */
    protected List<WebElement> waitForAllElementsToBeVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }
    
    /**
     * Wait for all elements matching locator to be visible with custom timeout
     */
    protected List<WebElement> waitForAllElementsToBeVisible(By locator, int timeoutInSeconds) {
        WebDriverWait customWait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        return customWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }
    
    /**
     * Wait for element to be selected (for checkboxes, radio buttons)
     */
    protected boolean waitForElementToBeSelected(By locator) {
        return wait.until(ExpectedConditions.elementToBeSelected(locator));
    }
    
    /**
     * Wait for element to be selected with custom timeout
     */
    protected boolean waitForElementToBeSelected(By locator, int timeoutInSeconds) {
        WebDriverWait customWait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        return customWait.until(ExpectedConditions.elementToBeSelected(locator));
    }
    
    /**
     * Wait for element attribute to contain specific value
     */
    protected boolean waitForElementAttributeToContain(By locator, String attribute, String value) {
        return wait.until(ExpectedConditions.attributeContains(locator, attribute, value));
    }
    
    /**
     * Wait for element attribute to contain specific value with custom timeout
     */
    protected boolean waitForElementAttributeToContain(By locator, String attribute, String value, int timeoutInSeconds) {
        WebDriverWait customWait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        return customWait.until(ExpectedConditions.attributeContains(locator, attribute, value));
    }
    
    /**
     * Safe click method that waits for element to be clickable before clicking
     */
    protected void safeClick(By locator) {
        WebElement element = waitForElementToBeClickable(locator);
        element.click();
    }
    
    /**
     * Safe click method with custom timeout
     */
    protected void safeClick(By locator, int timeoutInSeconds) {
        WebElement element = waitForElementToBeClickable(locator, timeoutInSeconds);
        element.click();
    }
    
    /**
     * Safe send keys method that waits for element to be visible before typing
     */
    protected void safeSendKeys(By locator, String text) {
        WebElement element = waitForElementToBeVisible(locator);
        element.clear();
        element.sendKeys(text);
    }

    /**
     * Uploads a file to the given file input element and waits for a specific attribute to appear.
     *
     * @param fileInputLocator Locator for the <input type="file"> element
     * @param relativeFilePath Relative path from project root to the file (e.g. "src/test/resources/testdata/dve.png")
     * @param attributeName Attribute name to wait for after upload (can be null to skip wait)
     * @param timeoutInSeconds Timeout in seconds to wait for the attribute (ignored if attributeName is null)
     */
    protected void uploadFileAndWaitForAttribute(By fileInputLocator, String relativeFilePath, String attributeName, int timeoutInSeconds) {
        String filePath = System.getProperty("user.dir") + "/" + relativeFilePath;

        File file = new File(filePath);
        System.out.println("Uploading file from path: " + filePath);
        if (!file.exists()) {
            throw new RuntimeException("File not found at path: " + filePath);
        }

        driver.findElement(fileInputLocator).sendKeys(file.getAbsolutePath());

        if (attributeName != null) {
            WebDriverWait customWait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
            customWait.until(driver -> {
                WebElement element = driver.findElement(fileInputLocator);
                String attrValue = element.getAttribute(attributeName);
                return attrValue != null;
            });
        }
    }
    
    /**
     * Safe send keys method with custom timeout
     */
    protected void safeSendKeys(By locator, String text, int timeoutInSeconds) {
        WebElement element = waitForElementToBeVisible(locator, timeoutInSeconds);
        element.clear();
        element.sendKeys(text);
    }
    
    /**
     * Safe get text method that waits for element to be visible before getting text
     */
    protected String safeGetText(By locator) {
        WebElement element = waitForElementToBeVisible(locator);
        return element.getText();
    }
    
    /**
     * Safe get text method with custom timeout
     */
    protected String safeGetText(By locator, int timeoutInSeconds) {
        WebElement element = waitForElementToBeVisible(locator, timeoutInSeconds);
        return element.getText();
    }
    
    /**
     * Check if element is present without waiting
     */
    protected boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
    
    /**
     * Check if element is visible without waiting
     */
    protected boolean isElementVisible(By locator) {
        try {
            WebElement element = driver.findElement(locator);
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
    
    /**
     * Wait for page to load completely
     */
    protected void waitForPageToLoad() {
        wait.until(webDriver -> 
            ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete")
        );
    }
    
    /**
     * Wait for page to load completely with custom timeout
     */
    protected void waitForPageToLoad(int timeoutInSeconds) {
        WebDriverWait customWait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        customWait.until(webDriver -> 
            ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete")
        );
    }
    
    /**
     * Get the current page title
     */
    protected String getPageTitle() {
        return driver.getTitle();
    }
    
    /**
     * Get the current page title and wait for it to contain specific text
     */
    protected String getPageTitle(String expectedText) {
        waitForTitleToContain(expectedText);
        return driver.getTitle();
    }
    
    /**
     * Get the current page title and wait for it to contain specific text with custom timeout
     */
    protected String getPageTitle(String expectedText, int timeoutInSeconds) {
        waitForTitleToContain(expectedText, timeoutInSeconds);
        return driver.getTitle();
    }
}
